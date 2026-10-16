package com.sailxx.ember

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService

class EmberTile : TileService() {
    override fun onStartListening() = refresh()

    override fun onClick() {
        val c = Prefs.load(this).copy(enabled = !Prefs.load(this).enabled)
        if (c.enabled && !Settings.canDrawOverlays(this) && EmberAccessibility.instance == null) return openApp()
        Prefs.save(this, c)
        if (c.enabled && !FilterService.start(this)) openApp()
        refresh()
    }

    @SuppressLint("StartActivityAndCollapseDeprecated")
    private fun openApp() {
        val i = Intent(this, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        if (Build.VERSION.SDK_INT >= 34) {
            startActivityAndCollapse(PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_IMMUTABLE))
        } else {
            @Suppress("DEPRECATION")
            startActivityAndCollapse(i)
        }
    }

    private fun refresh() {
        val c = Prefs.load(this)
        qsTile?.apply {
            state = if (c.enabled) Tile.STATE_ACTIVE else Tile.STATE_INACTIVE
            if (Build.VERSION.SDK_INT >= 29) subtitle = if (c.enabled) "${c.kelvin} K" else null
            updateTile()
        }
    }
}
