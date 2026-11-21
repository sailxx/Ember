package com.sailxx.ember

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.ServiceInfo
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/** Держит полноэкранный тёплый оверлей и раз в 30 секунд пересчитывает его по расписанию. */
class FilterService : Service(), SharedPreferences.OnSharedPreferenceChangeListener {
    private lateinit var wm: WindowManager
    private var view: View? = null
    private var lastText = ""
    private val handler = Handler(Looper.getMainLooper())
    private val applyPreferences = Runnable { update() }
    private val tick = object : Runnable {
        override fun run() {
            update()
            handler.postDelayed(this, 30_000)
        }
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        wm = getSystemService(WindowManager::class.java)
        getSystemService(NotificationManager::class.java).createNotificationChannel(
            NotificationChannel(CHANNEL, "Фильтр", NotificationManager.IMPORTANCE_LOW).apply { setShowBadge(false) }
        )
        val n = notification("Ember")
        if (Build.VERSION.SDK_INT >= 34) startForeground(ID, n, ServiceInfo.FOREGROUND_SERVICE_TYPE_SPECIAL_USE)
        else startForeground(ID, n)
        Prefs.sp(this).registerOnSharedPreferenceChangeListener(this)
        handler.postDelayed(tick, 30_000)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent?.action == ACTION_OFF) {
            Prefs.update(this) { copy(enabled = false) }
            stopSelf()
            return START_NOT_STICKY
        }
        update()
        serviceRunning.value = true
        return START_STICKY
    }

    override fun onSharedPreferenceChanged(sp: SharedPreferences?, key: String?) {
        handler.removeCallbacks(applyPreferences)
        handler.post(applyPreferences)
    }

    private fun update() {
        val c = Prefs.load(this)
        if (!c.enabled) {
            stopSelf()
            return
        }
        val w = Schedule.window(c)
        val a11y = EmberAccessibility.instance
        val state = FilterState.current(c, Schedule.nowMinute(), w, a11y != null, Settings.canDrawOverlays(this))
        if (a11y != null) {
            // Доверенное окно службы спец. возможностей: касания проходят всегда, плотность не ограничена 0.8.
            removeView()
            if (state.alpha == 0f) a11y.hide() else a11y.show(state.color, state.alpha)
        } else {
            if (state.alpha == 0f) removeView() else showView(state.color, state.alpha)
        }

        if (state.text != lastText) {
            lastText = state.text
            getSystemService(NotificationManager::class.java).notify(ID, notification(state.text))
        }
    }

    private fun showView(color: Int, alpha: Float) {
        val v = view ?: View(this).also { view = it }
        v.setBackgroundColor(color)
        val p = Overlay.params(this, wm, WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY, alpha.coerceAtMost(Tint.MAX_ALPHA))
        if (v.parent == null) wm.addView(v, p) else wm.updateViewLayout(v, p)
    }

    private fun removeView() {
        view?.let { if (it.parent != null) wm.removeView(it) }
        view = null
    }

    private fun notification(text: String): Notification {
        val open = PendingIntent.getActivity(
            this, 0, Intent(this, MainActivity::class.java), PendingIntent.FLAG_IMMUTABLE,
        )
        val off = PendingIntent.getService(
            this, 1, Intent(this, FilterService::class.java).setAction(ACTION_OFF), PendingIntent.FLAG_IMMUTABLE,
        )
        return Notification.Builder(this, CHANNEL)
            .setSmallIcon(R.drawable.ic_tile)
            .setContentTitle("Ember")
            .setContentText(text)
            .setContentIntent(open)
            .setOngoing(true)
            .addAction(Notification.Action.Builder(null, "Выключить", off).build())
            .build()
    }

    override fun onDestroy() {
        serviceRunning.value = false
        handler.removeCallbacks(tick)
        handler.removeCallbacks(applyPreferences)
        Prefs.sp(this).unregisterOnSharedPreferenceChangeListener(this)
        removeView()
        EmberAccessibility.instance?.hide()
        super.onDestroy()
    }

    companion object {
        private val serviceRunning = MutableStateFlow(false)
        val running = serviceRunning.asStateFlow()
        private const val ID = 1
        private const val CHANNEL = "filter"
        private const val ACTION_OFF = "com.sailxx.ember.OFF"

        /** false — система не дала запустить сервис из фона. */
        fun start(ctx: Context): Boolean = runCatching {
            ContextCompat.startForegroundService(ctx, Intent(ctx, FilterService::class.java))
        }.onFailure { Log.w("EmberFilter", "Unable to start filter service", it) }.isSuccess
    }
}
