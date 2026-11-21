package com.sailxx.ember

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

internal enum class EmberThemeMode(val title: String) {
    System("Как на устройстве"),
    Light("Светлая"),
    Dark("Тёмная"),
}

private val Presets = listOf("Мягкий" to 3400, "Тёплый" to 2700, "Свеча" to 1900, "Ночь" to 1200)
private val FadeOptions = listOf(0, 15, 30, 60)
private val ControlShape = RoundedCornerShape(16.dp)
private val PanelShape = RoundedCornerShape(28.dp)
private val NumberStyle = TextStyle(fontFeatureSettings = "tnum")

@Composable
internal fun EmberTheme(dark: Boolean, content: @Composable () -> Unit) {
    val colors = if (dark) {
        darkColorScheme(
            primary = Color(0xFFFF9254), onPrimary = Color(0xFF321706),
            primaryContainer = Color(0xFF39251D), onPrimaryContainer = Color(0xFFFFAF7D),
            background = Color(0xFF151311), onBackground = Color(0xFFFFF5ED),
            surface = Color(0xFF211D1A), onSurface = Color(0xFFFFF5ED),
            surfaceVariant = Color(0xFF352E28), onSurfaceVariant = Color(0xFFCCBEB3),
            outline = Color(0xFF9A877B), outlineVariant = Color(0xFF493D34),
            surfaceContainer = Color(0xFF29221E), surfaceContainerHigh = Color(0xFF322820),
            surfaceContainerHighest = Color(0xFF392E26),
        )
    } else {
        lightColorScheme(
            primary = Color(0xFFA93A05), onPrimary = Color.White,
            primaryContainer = Color(0xFFFFE4D2), onPrimaryContainer = Color(0xFF8A2D03),
            background = Color.White, onBackground = Color(0xFF2B201A),
            surface = Color.White, onSurface = Color(0xFF2B201A),
            surfaceVariant = Color(0xFFF4F2F0), onSurfaceVariant = Color(0xFF68564B),
            outline = Color(0xFF897367), outlineVariant = Color(0xFFE9E3DF),
            surfaceContainer = Color(0xFFFAF8F6), surfaceContainerHigh = Color(0xFFF5F2EF),
            surfaceContainerHighest = Color(0xFFEFECE8),
        )
    }
    MaterialTheme(
        colorScheme = colors.copy(
            secondaryContainer = colors.primaryContainer,
            onSecondaryContainer = colors.onPrimaryContainer,
            surfaceTint = colors.primary,
        ),
        content = content,
    )
}

@Composable
internal fun EmberScreen(
    dark: Boolean,
    themeMode: EmberThemeMode,
    onThemeChange: (EmberThemeMode) -> Unit,
    config: Config,
    window: Window,
    minute: Int,
    status: String,
    active: Boolean,
    hasAccess: Boolean,
    trusted: Boolean,
    locating: Boolean,
    placeNote: String?,
    onLocate: () -> Unit,
    onAccessibilitySettings: () -> Unit,
    onOverlaySettings: () -> Unit,
    onConfigChange: (Config.() -> Config) -> Unit,
) {
    val colors = MaterialTheme.colorScheme
    var showAccess by rememberSaveable { mutableStateOf(false) }
    var timeField by rememberSaveable { mutableStateOf<String?>(null) }

    val scheduleSummary = when {
        !config.auto -> "Всегда"
        config.sun && config.hasPlace -> "По солнцу · ${timeRange(window)}"
        else -> timeRange(window)
    }
    Box(
        Modifier.fillMaxSize().background(colors.background).safeDrawingPadding(),
        contentAlignment = Alignment.TopCenter,
    ) {
        Column(Modifier.widthIn(max = 600.dp).fillMaxSize().padding(horizontal = 16.dp)) {
            ThemeBar(dark, themeMode, onThemeChange)
            Column(
                Modifier.fillMaxWidth().weight(1f).verticalScroll(rememberScrollState())
                    .padding(start = 4.dp, end = 4.dp, top = 12.dp, bottom = 32.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                FilterSwitch(
                    enabled = config.enabled,
                    active = active,
                    status = status,
                    onChange = { enabled -> onConfigChange { copy(enabled = enabled) } },
                )
                if (config.enabled && !hasAccess) {
                    TextButton(onClick = { showAccess = true }) { Text("Настроить доступ") }
                }

                InstrumentPanel(config.kelvin, dark) { value -> onConfigChange { copy(kelvin = value) } }
                Column(
                    Modifier.fillMaxWidth().panel(dark, colors.surface, colors.outlineVariant).padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    SliderSetting("Сила фильтра", config.strength) { value -> onConfigChange { copy(strength = value) } }
                    QuietDivider()
                    SliderSetting("Затемнение", config.dim) { value -> onConfigChange { copy(dim = value) } }
                }

                Column(
                    Modifier.fillMaxWidth().panel(dark, colors.surface, colors.outlineVariant).padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    RowHeadline("Расписание", scheduleSummary)
                    DayProfile(config, window, minute)
                    ScheduleSettings(config, window, locating, placeNote, onLocate, onConfigChange) { timeField = it }
                }

                if (hasAccess && !trusted) {
                    TextButton(onClick = { showAccess = true }) { Text("Разрешения фильтра") }
                }
            }
        }
    }

    if (showAccess) {
        AlertDialog(
            onDismissRequest = { showAccess = false },
            title = { Text("Разрешения фильтра") },
            text = {
                Text("Разрешите Ember менять оттенок поверх приложений. Служба спецвозможностей позволяет нажимать на элементы под фильтром.")
            },
            confirmButton = {
                TextButton(onClick = {
                    showAccess = false
                    onAccessibilitySettings()
                }) {
                    Text("Спецвозможности")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showAccess = false
                    onOverlaySettings()
                }) {
                    Text("Поверх окон")
                }
            },
        )
    }

    timeField?.let { field ->
        val start = field == "on"
        TimeDialog(
            title = if (start) "Включать в" else "Выключать в",
            minute = if (start) config.on else config.off,
            onDismiss = { timeField = null },
            onPick = { value ->
                timeField = null
                onConfigChange { if (start) copy(on = value) else copy(off = value) }
            },
        )
    }
}

private fun Modifier.panel(dark: Boolean, fill: Color, border: Color) =
    shadow(if (dark) 0.dp else 4.dp, PanelShape, ambientColor = Color.Black, spotColor = Color.Black.copy(alpha = 0.16f))
        .background(fill, PanelShape)
        .border(1.dp, border, PanelShape)

@Composable
private fun ThemeBar(dark: Boolean, mode: EmberThemeMode, onChange: (EmberThemeMode) -> Unit) {
    val colors = MaterialTheme.colorScheme
    var open by remember { mutableStateOf(false) }
    Row(
        Modifier.fillMaxWidth().padding(horizontal = 4.dp).heightIn(min = 64.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text("Ember", color = colors.onSurface, fontSize = 25.sp, lineHeight = 32.sp, fontWeight = FontWeight.SemiBold)
        Box {
            ThemeButton(dark, onClick = { open = true })
            DropdownMenu(expanded = open, onDismissRequest = { open = false }) {
                EmberThemeMode.entries.forEach { option ->
                    DropdownMenuItem(
                        text = {
                            Text(option.title, color = if (mode == option) colors.primary else colors.onSurface)
                        },
                        modifier = Modifier.semantics { selected = mode == option },
                        onClick = {
                            open = false
                            onChange(option)
                        },
                    )
                }
            }
        }
    }
}

@Composable
private fun ThemeButton(dark: Boolean, onClick: () -> Unit) {
    val colors = MaterialTheme.colorScheme
    IconButton(
        onClick = onClick,
        modifier = Modifier.size(48.dp).background(colors.surface, CircleShape)
            .border(1.dp, colors.outlineVariant, CircleShape)
            .semantics { contentDescription = "Тема оформления" },
    ) {
        Canvas(Modifier.size(20.dp)) {
            val ink = colors.primary
            val stroke = Stroke(1.7.dp.toPx(), cap = StrokeCap.Round)
            if (dark) {
                drawCircle(ink, size.width * 0.22f, style = stroke)
                repeat(8) { i ->
                    val angle = Math.PI * i / 4
                    fun point(fraction: Float) = center + Offset(
                        (cos(angle) * size.width * fraction).toFloat(),
                        (sin(angle) * size.height * fraction).toFloat(),
                    )
                    drawLine(ink, point(0.36f), point(0.46f), stroke.width, StrokeCap.Round)
                }
            } else {
                val moon = Path().apply {
                    moveTo(size.width * 0.54f, size.height * 0.12f)
                    cubicTo(size.width * 0.25f, size.height * 0.14f, size.width * 0.08f, size.height * 0.37f,
                        size.width * 0.11f, size.height * 0.62f)
                    cubicTo(size.width * 0.16f, size.height * 0.94f, size.width * 0.71f, size.height * 1.00f,
                        size.width * 0.89f, size.height * 0.61f)
                    cubicTo(size.width * 0.59f, size.height * 0.78f, size.width * 0.32f, size.height * 0.43f,
                        size.width * 0.54f, size.height * 0.12f)
                }
                drawPath(moon, ink, style = stroke)
            }
        }
    }
}

@Composable
private fun InstrumentPanel(kelvin: Int, dark: Boolean, onChange: (Int) -> Unit) {
    val colors = MaterialTheme.colorScheme
    Column(
        Modifier.fillMaxWidth().panel(dark, colors.surface, colors.outlineVariant),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Column(Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp)) {
            BoxWithConstraints(
                Modifier.fillMaxWidth().background(colors.primaryContainer, RoundedCornerShape(20.dp)).padding(16.dp),
            ) {
                val showDial = maxWidth >= 248.dp && LocalDensity.current.fontScale <= 1.15f
                val numberSize = if (showDial && maxWidth < 300.dp) 40.sp else 48.sp
                Row(
                    Modifier.fillMaxWidth().heightIn(min = 112.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text("Температура", color = colors.onPrimaryContainer, fontSize = 14.sp)
                        Text(
                            "$kelvin K", color = colors.onPrimaryContainer,
                            fontSize = numberSize, lineHeight = numberSize * 1.1f,
                            fontWeight = FontWeight.SemiBold, style = NumberStyle,
                        )
                    }
                    if (showDial) TemperatureDial(kelvin)
                }
            }
        }
        Column(Modifier.padding(start = 12.dp, end = 12.dp, bottom = 12.dp)) {
            PresetScale(kelvin, onChange)
        }
    }
}

@Composable
private fun TemperatureDial(kelvin: Int) {
    val colors = MaterialTheme.colorScheme
    val progress = ((kelvin - Presets.last().second).toFloat() / (Presets.first().second - Presets.last().second))
        .coerceIn(0f, 1f)
    Canvas(Modifier.size(96.dp)) {
        val outer = size.minDimension * 0.44f
        val dial = size.minDimension * 0.31f
        val start = 140f
        val sweep = 260f
        fun point(angle: Float, radius: Float): Offset {
            val radians = Math.toRadians(angle.toDouble())
            return center + Offset((cos(radians) * radius).toFloat(), (sin(radians) * radius).toFloat())
        }
        for (i in 0..24) {
            val step = i / 24f
            val angle = start + step * sweep
            val major = i % 6 == 0
            val length = if (major) 8.dp.toPx() else 4.dp.toPx()
            val ink = colors.onPrimaryContainer.copy(alpha = if (step <= progress) 0.84f else 0.30f)
            drawLine(ink, point(angle, outer - length), point(angle, outer),
                if (major) 2.dp.toPx() else 1.dp.toPx(), StrokeCap.Round)
        }
        drawArc(
            colors.onPrimaryContainer.copy(alpha = 0.14f), start, sweep, false,
            topLeft = center - Offset(dial, dial), size = Size(dial * 2, dial * 2),
            style = Stroke(4.dp.toPx(), cap = StrokeCap.Round),
        )
        if (progress > 0f) {
            drawArc(
                Color(0xFFF46B29), start, sweep * progress, false,
                topLeft = center - Offset(dial, dial), size = Size(dial * 2, dial * 2),
                style = Stroke(4.dp.toPx(), cap = StrokeCap.Round),
            )
        }
        drawCircle(temperatureColor(kelvin), 11.dp.toPx())
        drawCircle(colors.onPrimaryContainer.copy(alpha = 0.28f), 11.dp.toPx(), style = Stroke(1.dp.toPx()))
        drawCircle(colors.onPrimaryContainer, 3.dp.toPx(), center = point(start + sweep * progress, dial))
    }
}

@Composable
private fun FilterSwitch(enabled: Boolean, active: Boolean, status: String, onChange: (Boolean) -> Unit) {
    val colors = MaterialTheme.colorScheme
    Row(
        Modifier.fillMaxWidth().heightIn(min = 64.dp).clip(ControlShape)
            .toggleable(value = enabled, role = Role.Switch, onValueChange = onChange),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text("Фильтр", color = colors.onSurface, fontSize = 28.sp, lineHeight = 32.sp)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                Box(Modifier.size(8.dp).background(if (active) colors.primary else colors.outline, CircleShape))
                Text(status, color = colors.onSurfaceVariant, fontSize = 14.sp)
            }
        }
        Switch(checked = enabled, onCheckedChange = null)
    }
}

@Composable
private fun RowHeadline(label: String, value: String) {
    val colors = MaterialTheme.colorScheme
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(label, Modifier.weight(1f), color = colors.onSurface, fontSize = 18.sp, fontWeight = FontWeight.Medium)
        Text(
            value, Modifier.weight(1f),
            color = colors.onSurfaceVariant, fontSize = 13.sp, style = NumberStyle, textAlign = TextAlign.End,
        )
    }
}

private fun temperatureColor(kelvin: Int): Color {
    val (r, g, b) = Tint.rgb(kelvin)
    return Color(r, g, b)
}

@Composable
private fun PresetScale(kelvin: Int, onChange: (Int) -> Unit) {
    val colors = MaterialTheme.colorScheme
    Column(Modifier.selectableGroup()) {
        Presets.forEach { (label, value) ->
            val selected = value == kelvin
            Row(
                Modifier.fillMaxWidth().heightIn(min = 48.dp).clip(ControlShape)
                    .background(if (selected) colors.primaryContainer else Color.Transparent)
                    .selectable(selected, role = Role.RadioButton, onClick = { onChange(value) })
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(Modifier.size(width = 24.dp, height = 12.dp).background(temperatureColor(value), RoundedCornerShape(4.dp)))
                Text(
                    label, Modifier.weight(1f), color = colors.onSurface, fontSize = 15.sp,
                    fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
                )
                Text("$value K", color = if (selected) colors.onPrimaryContainer else colors.onSurfaceVariant,
                    fontSize = 14.sp, style = NumberStyle)
            }
        }
    }
}

@Composable
private fun Choice(label: String, selected: Boolean, onClick: () -> Unit) {
    val colors = MaterialTheme.colorScheme
    val border = if (selected) colors.outline else colors.onSurface.copy(alpha = 0.12f)
    Row(
        Modifier.heightIn(min = 48.dp).clip(ControlShape)
            .background(if (selected) colors.primaryContainer else Color.Transparent)
            .border(1.dp, border, ControlShape)
            .selectable(selected, role = Role.RadioButton, onClick = onClick)
            .padding(horizontal = 10.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(label, color = if (selected) colors.onSurface else colors.onSurfaceVariant, fontSize = 13.sp)
    }
}

@Composable
private fun SliderSetting(label: String, value: Int, onChange: (Int) -> Unit) {
    val colors = MaterialTheme.colorScheme
    Column {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(label, color = colors.onSurface, fontSize = 14.sp)
            Text("$value%", color = colors.onSurfaceVariant, fontSize = 14.sp, style = NumberStyle)
        }
        Slider(
            value = value.toFloat(),
            onValueChange = { onChange(it.roundToInt()) },
            valueRange = 0f..100f,
            colors = SliderDefaults.colors(inactiveTrackColor = colors.surfaceVariant),
            modifier = Modifier.fillMaxWidth().semantics { contentDescription = label },
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ScheduleSettings(
    config: Config,
    window: Window,
    locating: Boolean,
    placeNote: String?,
    onLocate: () -> Unit,
    onConfigChange: (Config.() -> Config) -> Unit,
    onPickTime: (String) -> Unit,
) {
    Column(Modifier.fillMaxWidth().padding(top = 8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        FlowRow(
            Modifier.fillMaxWidth().selectableGroup(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Choice("По солнцу", config.auto && config.sun, onClick = { onConfigChange { copy(auto = true, sun = true) } })
            Choice("По времени", config.auto && !config.sun, onClick = { onConfigChange { copy(auto = true, sun = false) } })
            Choice("Всегда", !config.auto, onClick = { onConfigChange { copy(auto = false, sun = false) } })
        }

        if (!config.auto) return@Column
        Column {
            if (config.sun) {
                SettingLine(
                    title = "Место",
                    value = if (locating) "Поиск…" else if (config.hasPlace) "Обновить" else "Определить",
                    description = placeNote ?: if (config.hasPlace) {
                        "%.2f, %.2f".format(config.lat, config.lon)
                    } else {
                        "Пока место не задано, действует время ниже"
                    },
                    onClick = if (locating) null else onLocate,
                )
                if (config.hasPlace) SettingLine("Закат и рассвет", timeRange(window))
            }
            if (!config.sun || !config.hasPlace) {
                SettingLine("Включать", Schedule.hhmm(config.on), onClick = { onPickTime("on") })
                SettingLine("Выключать", Schedule.hhmm(config.off), onClick = { onPickTime("off") })
            }
            SettingLine(
                "Плавный переход",
                if (config.fade == 0) "Сразу" else "${config.fade} мин",
                onClick = { onConfigChange { copy(fade = FadeOptions[(FadeOptions.indexOf(fade) + 1).mod(FadeOptions.size)]) } },
            )
        }
    }
}

@Composable
private fun SettingLine(
    title: String,
    value: String,
    description: String? = null,
    onClick: (() -> Unit)? = null,
) {
    val colors = MaterialTheme.colorScheme
    Row(
        Modifier.fillMaxWidth().heightIn(min = 48.dp)
            .then(if (onClick != null) Modifier.clickable(role = Role.Button, onClick = onClick) else Modifier)
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(title, color = colors.onSurface, fontSize = 15.sp)
            if (description != null) Text(description, color = colors.onSurfaceVariant, fontSize = 13.sp)
        }
        Text(
            value, Modifier.widthIn(max = 144.dp),
            color = colors.onSurfaceVariant, fontSize = 13.sp, style = NumberStyle, textAlign = TextAlign.End,
        )

    }
    QuietDivider()
}

@Composable
private fun QuietDivider() {
    HorizontalDivider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.10f))
}

private fun timeRange(window: Window) = "${Schedule.hhmm(window.start)}–${Schedule.hhmm(window.end)}"

@Composable
private fun DayProfile(config: Config, window: Window, minute: Int) {
    val colors = MaterialTheme.colorScheme
    val plot = if (config.enabled) colors.primary else colors.onSurfaceVariant
    val grid = colors.onSurface.copy(alpha = 0.12f)
    val levels = remember(config.auto, config.fade, window) {
        val planned = config.copy(enabled = true)
        FloatArray(Schedule.DAY) { Schedule.level(planned, it, window) }
    }

    Column(
        Modifier.fillMaxWidth().background(colors.surfaceVariant.copy(alpha = 0.55f), ControlShape).padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text("Сейчас ${Schedule.hhmm(minute)}", color = colors.onSurfaceVariant, fontSize = 13.sp, style = NumberStyle)
        Canvas(
            Modifier.fillMaxWidth().height(88.dp)
                .semantics {
                    contentDescription = if (config.auto) "Профиль фильтра, ${timeRange(window)}" else "Профиль фильтра на весь день"
                },
        ) {
            val inset = 4.dp.toPx()
            val bottom = size.height - inset
            val range = bottom - inset
            fun x(m: Int) = inset + (size.width - 2 * inset) * m / Schedule.DAY
            fun y(m: Int) = bottom - range * levels[m % Schedule.DAY]

            for (step in 0..4) {
                val x = x(step * Schedule.DAY / 4)
                drawLine(grid, Offset(x, inset), Offset(x, bottom), 1.dp.toPx())
            }
            drawLine(grid, Offset(inset, bottom), Offset(size.width - inset, bottom), 1.dp.toPx())
            val profile = Path().apply {
                moveTo(x(0), y(0))
                for (time in 1..Schedule.DAY) lineTo(x(time), y(time))
            }
            val area = Path().apply {
                addPath(profile)
                lineTo(x(Schedule.DAY), bottom)
                lineTo(x(0), bottom)
                close()
            }
            drawPath(area, plot.copy(alpha = 0.14f))
            drawPath(profile, plot, style = Stroke(2.dp.toPx()))
            drawLine(colors.onSurfaceVariant, Offset(x(minute), inset), Offset(x(minute), bottom), 1.dp.toPx())
            drawCircle(plot, radius = inset, center = Offset(x(minute), y(minute)))
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            listOf("00", "06", "12", "18", "24").forEach {
                Text(it, color = colors.onSurfaceVariant, fontSize = 12.sp, style = NumberStyle)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TimeDialog(title: String, minute: Int, onDismiss: () -> Unit, onPick: (Int) -> Unit) {
    val time = rememberTimePickerState(initialHour = minute / 60, initialMinute = minute % 60, is24Hour = true)
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(title) },
        text = { TimePicker(state = time) },
        confirmButton = {
            TextButton(onClick = { onPick(time.hour * 60 + time.minute) }) { Text("Готово") }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Отмена") } },
    )
}
