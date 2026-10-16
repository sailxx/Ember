package com.sailxx.ember

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.location.LocationManagerCompat
import androidx.lifecycle.compose.LifecycleResumeEffect
import kotlinx.coroutines.delay

private val Amber = Color(0xFFFFB23E)
private val EmberOrange = Color(0xFFFF6A1F)
private val Line = Color(0xFF241E18)
private val Muted = Color(0xFF8A8178)
private val Text0 = Color(0xFFF5EFE8)
private val Mono = FontFamily.Monospace

private val presets = listOf("Мягкий" to 3400, "Тёплый" to 2700, "Свеча" to 1900, "Ночь" to 1200)
private val fades = listOf(0, 15, 30, 60)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme(
                colorScheme = darkColorScheme(
                    primary = Amber, onPrimary = Color(0xFF2A1000),
                    background = Color.Black, surface = Color.Black, onBackground = Text0, onSurface = Text0,
                )
            ) { Screen() }
        }
    }
}

@Composable
private fun Screen() {
    val ctx = LocalContext.current
    var c by remember { mutableStateOf(Prefs.load(ctx)) }
    var canDraw by remember { mutableStateOf(Settings.canDrawOverlays(ctx)) }
    var trusted by remember { mutableStateOf(EmberAccessibility.instance != null) }
    val ready = canDraw || trusted
    var minute by remember { mutableIntStateOf(Schedule.nowMinute()) }
    var locating by remember { mutableStateOf(false) }

    fun set(n: Config) {
        c = n
        Prefs.save(ctx, n)
        if (n.enabled && ready) FilterService.start(ctx)
    }

    LifecycleResumeEffect(Unit) {
        canDraw = Settings.canDrawOverlays(ctx)
        trusted = EmberAccessibility.instance != null
        c = Prefs.load(ctx)
        if (c.enabled && (canDraw || trusted)) FilterService.start(ctx)
        onPauseOrDispose {}
    }
    LaunchedEffect(Unit) {
        while (true) {
            minute = Schedule.nowMinute()
            delay(30_000)
        }
    }

    val notify = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) {}
    LaunchedEffect(Unit) {
        if (Build.VERSION.SDK_INT >= 33) notify.launch(Manifest.permission.POST_NOTIFICATIONS)
    }
    val place = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { ok ->
        if (!ok) return@rememberLauncherForActivityResult
        locating = true
        locate(ctx) { loc ->
            locating = false
            if (loc != null) set(c.copy(lat = loc.latitude, lon = loc.longitude))
        }
    }

    val w = Schedule.window(c)
    val level = Schedule.level(c, minute, w)
    val title = when {
        !c.enabled -> "Выключено"
        !c.auto -> "Тепло сейчас"
        level > 0f -> "Тепло до ${Schedule.hhmm(w.end)}"
        else -> "Тепло с ${Schedule.hhmm(w.start)}"
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Black)
            .safeDrawingPadding()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Label("Сутки")
        Text(title, fontSize = 28.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(top = 4.dp))

        if (!trusted) {
            Box(
                Modifier
                    .padding(top = 14.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(18.dp))
                    .background(Brush.linearGradient(listOf(Amber, EmberOrange)))
                    .clickable { ctx.startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)) }
                    .padding(16.dp)
            ) {
                Column {
                    Text("Включите Ember в «Спец. возможностях»", color = Color(0xFF2A1000), fontWeight = FontWeight.SemiBold)
                    Text(
                        "Тогда кнопки под фильтром всегда нажимаются, а фильтр ложится и на строку состояния. " +
                            "Ember не читает экран.",
                        color = Color(0xB32A1000), fontSize = 13.sp,
                    )
                }
            }
            if (!canDraw) Text(
                "Или разрешить «поверх других окон» — проще, но часть кнопок может не нажиматься",
                color = Muted, fontSize = 12.sp,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .clickable {
                        ctx.startActivity(
                            Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:${ctx.packageName}"))
                        )
                    },
            )
        }

        DayChart(c, w, minute, Modifier.padding(top = 14.dp).fillMaxWidth().height(170.dp))
        Row(Modifier.fillMaxWidth().padding(top = 6.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            listOf("00", "06", "12", "18", "24").forEach { Text(it, color = Muted, fontSize = 11.sp, fontFamily = Mono) }
        }

        Row(Modifier.fillMaxWidth().padding(top = 16.dp), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            presets.forEach { (name, k) ->
                val on = c.kelvin == k
                Box(
                    Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(12.dp))
                        .background(if (on) Amber.copy(alpha = .14f) else Color.Transparent)
                        .border(1.dp, if (on) Color(0xFF6A4A1C) else Line, RoundedCornerShape(12.dp))
                        .clickable { set(c.copy(kelvin = k)) }
                        .padding(vertical = 10.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(name, color = if (on) Amber else Muted, fontSize = 13.sp)
                        Text("$k K", color = if (on) Amber.copy(alpha = .7f) else Muted.copy(alpha = .6f), fontSize = 10.sp, fontFamily = Mono)
                    }
                }
            }
        }

        SliderRow("Сила", "${c.strength}%", c.strength) { set(c.copy(strength = it)) }
        SliderRow("Затемнение", "${c.dim}%", c.dim) { set(c.copy(dim = it)) }

        Spacer(Modifier.height(8.dp))
        SettingRow("По солнцу") { EmberSwitch(c.auto) { set(c.copy(auto = it)) } }
        if (c.auto) {
            SettingRow(
                if (c.hasPlace) "Закат → рассвет" else "По умолчанию",
                sub = if (locating) "Определяю место…" else if (c.hasPlace) "По вашему месту" else "Нажмите, чтобы взять время заката",
                onClick = { place.launch(Manifest.permission.ACCESS_COARSE_LOCATION) },
            ) { Text("${Schedule.hhmm(w.start)} → ${Schedule.hhmm(w.end)}", color = Muted, fontFamily = Mono) }
            SettingRow("Плавно", onClick = {
                set(c.copy(fade = fades[(fades.indexOf(c.fade) + 1).mod(fades.size)]))
            }) { Text(if (c.fade == 0) "сразу" else "${c.fade} мин", color = Muted, fontFamily = Mono) }
        }
        SettingRow("Включено", divider = false) { EmberSwitch(c.enabled) { set(c.copy(enabled = it)) } }
    }
}

@Composable
private fun DayChart(c: Config, w: Window, minute: Int, modifier: Modifier) {
    val on = c.enabled
    val stroke = if (on) Amber else Muted
    val fill = if (on) EmberOrange else Muted
    val shape = c.copy(enabled = true)
    Canvas(modifier) {
        val top = size.height * 0.12f
        val bottom = size.height * 0.92f
        fun x(m: Int) = size.width * m / Schedule.DAY
        fun y(l: Float) = bottom - (bottom - top) * l

        val line = Path()
        for (m in 0..Schedule.DAY step 5) {
            val p = Offset(x(m), y(Schedule.level(shape, m % Schedule.DAY, w)))
            if (m == 0) line.moveTo(p.x, p.y) else line.lineTo(p.x, p.y)
        }
        val area = Path().apply {
            addPath(line)
            lineTo(size.width, size.height)
            lineTo(0f, size.height)
            close()
        }
        drawLine(Line, Offset(0f, bottom), Offset(size.width, bottom), 1.dp.toPx())
        drawPath(area, Brush.verticalGradient(listOf(fill.copy(alpha = .55f), fill.copy(alpha = 0f)), top, size.height))
        drawPath(line, stroke, style = Stroke(2.5.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round))

        val nx = x(minute)
        drawLine(
            Color.White.copy(alpha = .5f), Offset(nx, 0f), Offset(nx, size.height), 1.dp.toPx(),
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(6f, 8f)),
        )
        drawCircle(Color.White, 5.dp.toPx(), Offset(nx, y(Schedule.level(shape, minute, w))))
    }
}

@Composable
private fun Label(text: String) =
    Text(text.uppercase(), color = Muted, fontSize = 11.sp, letterSpacing = 1.2.sp)

@Composable
private fun SliderRow(name: String, value: String, v: Int, onChange: (Int) -> Unit) {
    Column(Modifier.padding(top = 16.dp)) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Label(name)
            Text(value, color = Muted, fontSize = 12.sp, fontFamily = Mono)
        }
        Slider(
            value = v.toFloat(), onValueChange = { onChange(it.toInt()) }, valueRange = 0f..100f,
            colors = SliderDefaults.colors(thumbColor = Color.White, activeTrackColor = Amber, inactiveTrackColor = Line),
        )
    }
}

@Composable
private fun SettingRow(
    title: String,
    sub: String? = null,
    divider: Boolean = true,
    onClick: (() -> Unit)? = null,
    trailing: @Composable () -> Unit,
) {
    Row(
        Modifier
            .fillMaxWidth()
            .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier)
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(Modifier.weight(1f)) {
            Text(title, style = TextStyle(fontSize = 16.sp, color = Text0))
            if (sub != null) Text(sub, color = Muted, fontSize = 12.sp)
        }
        trailing()
    }
    if (divider) HorizontalDivider(color = Line)
}

@Composable
private fun EmberSwitch(checked: Boolean, onChange: (Boolean) -> Unit) = Switch(
    checked = checked, onCheckedChange = onChange,
    colors = SwitchDefaults.colors(
        checkedThumbColor = Color.Black, checkedTrackColor = Amber, checkedBorderColor = Amber,
        uncheckedThumbColor = Muted, uncheckedTrackColor = Color.Black, uncheckedBorderColor = Line,
    ),
)

@SuppressLint("MissingPermission")
private fun locate(ctx: Context, done: (Location?) -> Unit) {
    val lm = ctx.getSystemService(LocationManager::class.java)
    val last = lm.getProviders(true).mapNotNull { runCatching { lm.getLastKnownLocation(it) }.getOrNull() }.maxByOrNull { it.time }
    if (last != null) return done(last)
    val provider = listOf(LocationManager.NETWORK_PROVIDER, LocationManager.GPS_PROVIDER).firstOrNull { lm.isProviderEnabled(it) }
        ?: return done(null)
    LocationManagerCompat.getCurrentLocation(
        lm, provider, null as android.os.CancellationSignal?, ContextCompat.getMainExecutor(ctx),
    ) { done(it) }
}
