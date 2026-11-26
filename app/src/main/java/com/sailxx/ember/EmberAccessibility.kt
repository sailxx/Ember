package com.sailxx.ember

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.content.SharedPreferences
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.accessibility.AccessibilityEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Окно TYPE_ACCESSIBILITY_OVERLAY система считает доверенным: касания проходят сквозь него при любой плотности,
 * в том числе на защищённых экранах (установщик, банки), и оно ложится поверх строки состояния и навигации.
 * Сама служба ничего не читает с экрана — события не подписаны.
 */
class EmberAccessibility : AccessibilityService(), SharedPreferences.OnSharedPreferenceChangeListener {
    private var view: View? = null

    private val handler = Handler(Looper.getMainLooper())
    private val applyPreferences = Runnable { update() }
    private val tick = object : Runnable {
        override fun run() {
            update()
            handler.postDelayed(this, 30_000)
        }
    }

    override fun onServiceConnected() {
        super.onServiceConnected()
        connection.value = this
        Prefs.sp(this).registerOnSharedPreferenceChangeListener(this)
        handler.removeCallbacks(tick)
        tick.run()
    }

    override fun onSharedPreferenceChanged(prefs: SharedPreferences?, key: String?) {
        handler.removeCallbacks(applyPreferences)
        handler.post(applyPreferences)
    }

    private fun update() {
        if (instance !== this) return
        val config = Prefs.load(this)
        val state = FilterState.current(config, Schedule.nowMinute(), Schedule.window(config), true, false)
        if (state.alpha == 0f) {
            hide()
            overlayVisible.value = false
            return
        }
        overlayVisible.value = try {
            show(state.color, state.alpha)
            true
        } catch (error: WindowManager.BadTokenException) {
            Log.w(TAG, "Accessibility overlay token unavailable", error)
            hide()
            false
        } catch (error: WindowManager.InvalidDisplayException) {
            Log.w(TAG, "Accessibility overlay display unavailable", error)
            hide()
            false
        }
    }

    private fun show(color: Int, alpha: Float) {
        val wm = getSystemService(WindowManager::class.java)
        val v = view ?: View(this).also { view = it }
        v.setBackgroundColor(color)
        val p = Overlay.params(this, wm, WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY, alpha)
        if (v.parent == null) wm.addView(v, p) else wm.updateViewLayout(v, p)
    }

    private fun hide() {
        view?.let { if (it.parent != null) getSystemService(WindowManager::class.java).removeView(it) }
        view = null
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {}
    override fun onInterrupt() {}

    override fun onUnbind(intent: Intent?): Boolean {
        disconnect()
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        disconnect()
        super.onDestroy()
    }

    private fun disconnect() {
        handler.removeCallbacksAndMessages(null)
        Prefs.sp(this).unregisterOnSharedPreferenceChangeListener(this)
        hide()
        if (instance === this) {
            overlayVisible.value = false
            connection.value = null
        }
    }

    companion object {
        private const val TAG = "EmberAccessibility"
        private val connection = MutableStateFlow<EmberAccessibility?>(null)
        private val overlayVisible = MutableStateFlow(false)
        val connected = connection.asStateFlow()
        val rendering = overlayVisible.asStateFlow()
        val instance: EmberAccessibility? get() = connection.value
    }
}
