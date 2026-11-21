package com.sailxx.ember

import android.Manifest
import android.content.Intent
import android.content.SharedPreferences
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.edit
import androidx.core.location.LocationManagerCompat
import androidx.core.net.toUri
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.LifecycleResumeEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val appearance = getPreferences(MODE_PRIVATE)
        setContent {
            var themeMode by rememberSaveable {
                mutableStateOf(
                    EmberThemeMode.entries.firstOrNull { it.name == appearance.getString("theme_mode", null) }
                        ?: EmberThemeMode.System
                )
            }
            val dark = when (themeMode) {
                EmberThemeMode.System -> isSystemInDarkTheme()
                EmberThemeMode.Light -> false
                EmberThemeMode.Dark -> true
            }
            LaunchedEffect(dark) {
                val bars = WindowCompat.getInsetsController(window, window.decorView)
                bars.isAppearanceLightStatusBars = !dark
                bars.isAppearanceLightNavigationBars = !dark
            }
            EmberTheme(dark) {
                Screen(dark, themeMode) { mode ->
                    themeMode = mode
                    appearance.edit { putString("theme_mode", mode.name) }
                }
            }
        }
    }
}

@Composable
private fun Screen(dark: Boolean, themeMode: EmberThemeMode, onThemeChange: (EmberThemeMode) -> Unit) {
    val ctx = LocalContext.current
    var c by remember { mutableStateOf(Prefs.load(ctx)) }
    var canDraw by remember { mutableStateOf(Settings.canDrawOverlays(ctx)) }
    val accessibility by EmberAccessibility.connected.collectAsStateWithLifecycle()
    val trusted = accessibility != null
    val serviceRunning by FilterService.running.collectAsStateWithLifecycle()
    var minute by remember { mutableIntStateOf(Schedule.nowMinute()) }
    var locating by remember { mutableStateOf(false) }

    fun set(change: Config.() -> Config) {
        val wasEnabled = Prefs.load(ctx).enabled
        val next = Prefs.update(ctx, change)
        if (next.enabled && !wasEnabled && (canDraw || EmberAccessibility.instance != null)) {
            FilterService.start(ctx)
        }
    }

    DisposableEffect(ctx) {
        val prefs = Prefs.sp(ctx)
        val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, _ -> c = Prefs.load(ctx) }
        prefs.registerOnSharedPreferenceChangeListener(listener)
        c = Prefs.load(ctx)
        onDispose { prefs.unregisterOnSharedPreferenceChangeListener(listener) }
    }

    LifecycleResumeEffect(Unit) {
        canDraw = Settings.canDrawOverlays(ctx)
        c = Prefs.load(ctx)
        if (c.enabled && (canDraw || EmberAccessibility.instance != null)) FilterService.start(ctx)
        onPauseOrDispose {}
    }
    LaunchedEffect(Unit) {
        while (true) {
            minute = Schedule.nowMinute()
            delay(30_000)
        }
    }

    val notify = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) {}
    var notificationAsked by rememberSaveable { mutableStateOf(false) }
    LaunchedEffect(c.enabled, canDraw, trusted) {
        if (Build.VERSION.SDK_INT >= 33 && c.enabled && (canDraw || trusted) && !notificationAsked) {
            notificationAsked = true
            notify.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }
    var placeNote by remember { mutableStateOf<String?>(null) }
    var cancelLocation by remember { mutableStateOf<(() -> Unit)?>(null) }
    DisposableEffect(Unit) {
        onDispose { cancelLocation?.invoke() }
    }
    val place = rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { res ->
        if (res.values.none { it }) {
            placeNote = "Нет доступа к местоположению — нажмите, чтобы открыть настройки Ember"
            return@rememberLauncherForActivityResult
        }
        locating = true
        placeNote = null
        cancelLocation = Place.locate(ctx) { loc, err ->
            cancelLocation = null
            locating = false
            placeNote = when (err) {
                Place.Error.OFF -> "Геолокация выключена — включите её и нажмите ещё раз"
                Place.Error.NO_FIX -> "Не удалось определить место. Попробуйте ещё раз или задайте время вручную"
                null -> null
            }
            if (loc != null) set { copy(lat = loc.latitude, lon = loc.longitude) }
        }
    }
    fun onPlaceClick() {
        if (locating) return
        val lm = ctx.getSystemService(LocationManager::class.java)
        when {
            placeNote?.startsWith("Нет доступа") == true -> ctx.startActivity(
                Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, "package:${ctx.packageName}".toUri())
            ).also { placeNote = null }
            !LocationManagerCompat.isLocationEnabled(lm) -> ctx.startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
            else -> place.launch(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION))
        }
    }

    val window = Schedule.window(c)
    val status = FilterState.current(c, minute, window, trusted, canDraw)
    val level = Schedule.level(c, minute, window)
    val ready = canDraw || trusted
    EmberScreen(
        dark = dark,
        themeMode = themeMode,
        onThemeChange = onThemeChange,
        config = c,
        window = window,
        minute = minute,
        status = when {
            status.alpha > 0f && !serviceRunning -> "Фильтр не запущен"
            status.alpha > 0f && level >= 1f -> "Фильтр активен"
            status.alpha > 0f -> "Плавный переход · ${(level * 100).toInt()}%"
            else -> status.text
        },
        active = status.alpha > 0f && serviceRunning,
        hasAccess = ready,
        trusted = trusted,
        locating = locating,
        placeNote = placeNote,
        onLocate = ::onPlaceClick,
        onAccessibilitySettings = { ctx.startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)) },
        onOverlaySettings = {
            ctx.startActivity(Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, "package:${ctx.packageName}".toUri()))
        },
        onConfigChange = ::set,
    )
}
