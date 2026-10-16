package com.sailxx.ember

import android.content.Context
import android.graphics.PixelFormat
import android.os.Build
import android.view.Gravity
import android.view.WindowManager

object Overlay {
    private const val PAD = 300

    /** Окно больше экрана со сдвигом, чтобы перекрыть строку состояния, навигацию и вырез при любом повороте. */
    fun params(ctx: Context, wm: WindowManager, type: Int, alpha: Float): WindowManager.LayoutParams {
        val b = if (Build.VERSION.SDK_INT >= 30) wm.maximumWindowMetrics.bounds.let { maxOf(it.width(), it.height()) }
        else ctx.resources.displayMetrics.let { maxOf(it.widthPixels, it.heightPixels) }
        val size = b + 2 * PAD
        return WindowManager.LayoutParams(
            size, size, type,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE or
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN or
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            PixelFormat.TRANSLUCENT,
        ).apply {
            gravity = Gravity.TOP or Gravity.START
            x = -PAD
            y = -PAD
            this.alpha = alpha
            if (Build.VERSION.SDK_INT >= 30) {
                fitInsetsTypes = 0
                layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_ALWAYS
            } else if (Build.VERSION.SDK_INT >= 28) {
                layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
            }
        }
    }
}
