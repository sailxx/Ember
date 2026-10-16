package com.sailxx.ember

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Settings

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(ctx: Context, intent: Intent) {
        if (Prefs.load(ctx).enabled && Settings.canDrawOverlays(ctx)) FilterService.start(ctx)
    }
}
