package com.sailxx.ember

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.view.View
import android.view.WindowManager
import android.view.accessibility.AccessibilityEvent

/**
 * Окно TYPE_ACCESSIBILITY_OVERLAY система считает доверенным: касания проходят сквозь него при любой плотности,
 * в том числе на защищённых экранах (установщик, банки), и оно ложится поверх строки состояния и навигации.
 * Сама служба ничего не читает с экрана — события не подписаны.
 */
class EmberAccessibility : AccessibilityService() {
    private var view: View? = null

    override fun onServiceConnected() {
        instance = this
        if (Prefs.load(this).enabled) FilterService.start(this)
    }

    fun show(color: Int, alpha: Float) {
        val wm = getSystemService(WindowManager::class.java)
        val v = view ?: View(this).also { view = it }
        v.setBackgroundColor(color)
        val p = Overlay.params(this, wm, WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY, alpha)
        if (v.parent == null) wm.addView(v, p) else wm.updateViewLayout(v, p)
    }

    fun hide() {
        view?.let { if (it.parent != null) getSystemService(WindowManager::class.java).removeView(it) }
        view = null
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {}
    override fun onInterrupt() {}

    override fun onUnbind(intent: Intent?): Boolean {
        hide()
        instance = null
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        hide()
        instance = null
        super.onDestroy()
    }

    companion object {
        var instance: EmberAccessibility? = null
            private set
    }
}
