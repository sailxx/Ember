package com.sailxx.ember

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(ctx: Context, intent: Intent) {
        if (intent.action != Intent.ACTION_BOOT_COMPLETED && intent.action != Intent.ACTION_MY_PACKAGE_REPLACED) return
        // Служба спецвозможностей может подключиться уже после загрузки.
        if (Prefs.load(ctx).enabled) FilterService.start(ctx)
    }
}
