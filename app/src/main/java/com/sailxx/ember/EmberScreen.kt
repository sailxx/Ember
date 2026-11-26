package com.sailxx.ember

import android.os.Build
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
import androidx.compose.material3.Typography
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.layer.GraphicsLayer
import androidx.compose.ui.graphics.layer.drawLayer
import androidx.compose.ui.graphics.rememberGraphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

internal enum class EmberThemeMode(val title: Int) {
    System(R.string.theme_system),
    Light(R.string.theme_light),
    Dark(R.string.theme_dark),
}

private val Presets = listOf(R.string.preset_soft to 3400, R.string.preset_warm to 2700, R.string.preset_candle to 1900, R.string.preset_night to 1200)
private val FadeOptions = listOf(0, 15, 30, 60)
private val ControlShape = RoundedCornerShape(16.dp)
private val PanelShape = RoundedCornerShape(28.dp)
private val Golos = FontFamily(Font(R.font.golos))
private val Mono = FontFamily(Font(R.font.jbmono))
private val NumberStyle = TextStyle(fontFamily = Mono, fontFeatureSettings = "tnum")
private val DockHeight = 64.dp
private val DockOffset = 52.dp

@Composable
internal fun EmberTheme(dark: Boolean, content: @Composable () -> Unit) {
    val colors = if (dark) {
        darkColorScheme(
            primary = Color(0xFFFF9254), onPrimary = Color(0xFF321706),
            primaryContainer = Color(0xFF39251D), onPrimaryContainer = Color(0xFFFFAF7D),
            background = Color(0xFF151515), onBackground = Color(0xFFEDEDED),
            surface = Color(0xFF19191B), onSurface = Color(0xFFEDEDED),
            surfaceVariant = Color(0xFF38383A), onSurfaceVariant = Color(0xFFAAA9AA),
            outline = Color(0xFF88888A), outlineVariant = Color(0xFF444446),
            surfaceContainer = Color(0xFF252526), surfaceContainerHigh = Color(0xFF303032),
            surfaceContainerHighest = Color(0xFF3A3A3C),
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
        typography = Typography(
            bodyLarge = Typography().bodyLarge.copy(fontFamily = Golos),
            bodyMedium = Typography().bodyMedium.copy(fontFamily = Golos),
            labelLarge = Typography().labelLarge.copy(fontFamily = Golos),
            headlineSmall = Typography().headlineSmall.copy(fontFamily = Golos),
        ),
        content = content,
    )
}

@Composable
internal fun EmberScreen(
    dark: Boolean,
    themeMode: EmberThemeMode,
    onThemeChange: (EmberThemeMode) -> Unit,
    language: EmberLanguage,
    onLanguageChange: (EmberLanguage) -> Unit,
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
    var showSchedule by rememberSaveable { mutableStateOf(false) }
    val backdrop = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) rememberGraphicsLayer() else null
    var contentPosition by remember { mutableStateOf(Offset.Zero) }
    // The dock samples the scroll content without including itself in the captured layer.
    val captureBackdrop = if (backdrop == null) Modifier else Modifier
        .onGloballyPositioned { contentPosition = it.positionInRoot() }
        .drawWithContent {
            backdrop.record {
                drawRect(colors.background)
                this@drawWithContent.drawContent()
            }
            drawLayer(backdrop)
        }

    val scheduleSummary = if (config.auto) timeRange(window) else stringResource(R.string.all_day)
    val scheduleMode = when {
        !config.auto -> stringResource(R.string.always)
        config.sun -> stringResource(R.string.sun_schedule)
        else -> stringResource(R.string.manual_schedule)
    }
    Box(
        Modifier.fillMaxSize().background(colors.background).safeDrawingPadding(),
        contentAlignment = Alignment.TopCenter,
    ) {
        Column(Modifier.widthIn(max = 492.dp).fillMaxSize().then(captureBackdrop).padding(horizontal = 16.dp)) {
            SettingsBar(themeMode, onThemeChange, language, onLanguageChange)
            Column(
                Modifier.fillMaxWidth().weight(1f).verticalScroll(rememberScrollState())
                    .padding(top = 8.dp, bottom = DockOffset + DockHeight + 24.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                InstrumentPanel(config.kelvin, active, status) { value -> onConfigChange { copy(kelvin = value) } }

                if (config.enabled && !hasAccess) {
                    TextButton(onClick = { showAccess = true }) { Text(stringResource(R.string.set_up_access)) }
                }

                BoxWithConstraints(Modifier.fillMaxWidth().panel(colors.surface, colors.outlineVariant).padding(12.dp)) {
                    if (maxWidth >= 272.dp && LocalDensity.current.fontScale <= 1.25f) {
                        Row(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                            Box(Modifier.weight(1f)) {
                                SliderSetting(stringResource(R.string.strength), config.strength) { value -> onConfigChange { copy(strength = value) } }
                            }
                            Box(Modifier.weight(1f)) {
                                SliderSetting(stringResource(R.string.dimming), config.dim) { value -> onConfigChange { copy(dim = value) } }
                            }
                        }
                    } else {
                        Column {
                            SliderSetting(stringResource(R.string.strength), config.strength) { value -> onConfigChange { copy(strength = value) } }
                            QuietDivider()
                            SliderSetting(stringResource(R.string.dimming), config.dim) { value -> onConfigChange { copy(dim = value) } }
                        }
                    }
                }

                Column(
                    Modifier.fillMaxWidth().panel(colors.surface, colors.outlineVariant).padding(14.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    RowHeadline(stringResource(R.string.day_profile), scheduleSummary)
                    DayProfile(config, window, minute)
                    QuietDivider()
                    SettingLine(stringResource(R.string.schedule), "$scheduleMode  ›", onClick = { showSchedule = true })
                }

                if (hasAccess && !trusted) {
                    TextButton(onClick = { showAccess = true }) { Text(stringResource(R.string.permissions)) }
                }
            }
        }

        Box(
            Modifier.align(Alignment.BottomCenter).widthIn(max = 492.dp).fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, bottom = DockOffset),
        ) {
            FilterDock(dark, config.enabled, backdrop, { contentPosition }) {
                onConfigChange { copy(enabled = !enabled) }
            }
        }
    }

    if (showSchedule) {
        AlertDialog(
            onDismissRequest = { showSchedule = false },
            title = { Text(stringResource(R.string.schedule)) },
            text = {
                Column(Modifier.verticalScroll(rememberScrollState())) {
                    ScheduleSettings(config, window, locating, placeNote, onLocate, onConfigChange) { timeField = it }
                }
            },
            confirmButton = { TextButton(onClick = { showSchedule = false }) { Text(stringResource(R.string.done)) } },
        )
    }

    if (showAccess) {
        AlertDialog(
            onDismissRequest = { showAccess = false },
            title = { Text(stringResource(R.string.permissions)) },
            text = {
                Text(stringResource(R.string.access_help))
            },
            confirmButton = {
                TextButton(onClick = {
                    showAccess = false
                    onAccessibilitySettings()
                }) {
                    Text(stringResource(R.string.accessibility))
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showAccess = false
                    onOverlaySettings()
                }) {
                    Text(stringResource(R.string.overlay))
                }
            },
        )
    }

    timeField?.let { field ->
        val start = field == "on"
        TimeDialog(
            title = if (start) stringResource(R.string.turn_on_at) else stringResource(R.string.turn_off_at),
            minute = if (start) config.on else config.off,
            onDismiss = { timeField = null },
            onPick = { value ->
                timeField = null
                onConfigChange { if (start) copy(on = value) else copy(off = value) }
            },
        )
    }
}

private fun Modifier.panel(fill: Color, border: Color) = background(fill, PanelShape).border(1.dp, border, PanelShape)

@Composable
private fun SettingsBar(
    mode: EmberThemeMode,
    onChange: (EmberThemeMode) -> Unit,
    language: EmberLanguage,
    onLanguageChange: (EmberLanguage) -> Unit,
) {
    val colors = MaterialTheme.colorScheme
    val settingsLabel = stringResource(R.string.settings)
    var open by remember { mutableStateOf(false) }
    Row(
        Modifier.fillMaxWidth().heightIn(min = 56.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Box(Modifier.size(18.dp).background(colors.onSurface.copy(alpha = .22f), CircleShape), contentAlignment = Alignment.Center) {
                Box(Modifier.size(10.dp).background(colors.onSurface, CircleShape))
            }
            Text("Ember", color = colors.onSurface, fontSize = 23.sp, style = NumberStyle, fontWeight = FontWeight.Bold)
        }
        Box {
            IconButton(onClick = { open = true }, modifier = Modifier.semantics { contentDescription = settingsLabel }) {
                Canvas(Modifier.size(22.dp)) {
                    for ((y, knob) in listOf(.32f to .66f, .68f to .34f)) {
                        drawLine(colors.onSurface, Offset(1.dp.toPx(), size.height * y),
                            Offset(size.width - 1.dp.toPx(), size.height * y), 1.7.dp.toPx(), StrokeCap.Round)
                        val center = Offset(size.width * knob, size.height * y)
                        drawCircle(colors.onSurface, 3.dp.toPx(), center)
                        drawCircle(colors.background, 1.6.dp.toPx(), center)
                    }
                }
            }
            DropdownMenu(expanded = open, onDismissRequest = { open = false }) {
                Text(
                    stringResource(R.string.theme),
                    Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                    color = colors.onSurfaceVariant,
                )
                EmberThemeMode.entries.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(stringResource(option.title), color = if (mode == option) colors.primary else colors.onSurface) },
                        modifier = Modifier.semantics { selected = mode == option },
                        onClick = { open = false; onChange(option) },
                    )
                }
                HorizontalDivider()
                Text(
                    stringResource(R.string.language),
                    Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                    color = colors.onSurfaceVariant,
                )
                EmberLanguage.entries.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option.label, color = if (language == option) colors.primary else colors.onSurface) },
                        modifier = Modifier.semantics { selected = language == option },
                        onClick = { open = false; onLanguageChange(option) },
                    )
                }
            }
        }
    }
}

@Composable
private fun InstrumentPanel(kelvin: Int, active: Boolean, status: String, onChange: (Int) -> Unit) {
    val temperatureLabel = stringResource(R.string.temperature)
    val ink = Color(0xFFFFF7F0)
    val min = Presets.last().second
    val max = Presets.first().second
    val warmth = (max - kelvin.coerceIn(min, max)).toFloat() / (max - min)
    val warmStart = lerp(Color(0xFF79531F), Color(0xFFA33C24), warmth)
    val warmEnd = lerp(Color(0xFF473518), Color(0xFF541D21), warmth)
    val gradient = if (active) {
        listOf(warmStart, warmEnd)
    } else {
        listOf(
            lerp(Color(0xFF34343B), warmStart, .32f),
            lerp(Color(0xFF212127), warmEnd, .32f),
        )
    }
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Column(
            Modifier.fillMaxWidth().background(
                Brush.linearGradient(gradient), PanelShape,
            ).border(1.dp, Color.White.copy(alpha = .23f), PanelShape).padding(horizontal = 18.dp, vertical = 14.dp),
        ) {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Box(Modifier.size(30.dp).background(Color.White.copy(alpha = .15f), CircleShape), contentAlignment = Alignment.Center) {
                    Box(Modifier.size(10.dp).border(1.dp, ink, CircleShape))
                }
                Text(stringResource(R.string.temperature), Modifier.weight(1f), color = ink, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                Row(Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.End)) {
                    Box(Modifier.size(6.dp).then(if (active) Modifier.background(ink, CircleShape) else Modifier.border(1.dp, ink, CircleShape)))
                    Text(status, color = ink, fontSize = 11.sp, lineHeight = 14.sp, style = NumberStyle, textAlign = TextAlign.End)
                }
            }
            BoxWithConstraints(Modifier.fillMaxWidth().padding(top = 4.dp), contentAlignment = Alignment.Center) {
                val fontSize = (maxWidth.value / (3.1f * LocalDensity.current.fontScale)).coerceAtMost(62f)
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(kelvin.toString(), color = ink, fontSize = fontSize.sp, lineHeight = (fontSize + 4).sp,
                        fontFamily = Golos, fontWeight = FontWeight.Bold, letterSpacing = (-3).sp)
                    Text(" K", Modifier.padding(bottom = 7.dp), color = ink, fontSize = (fontSize * .35f).sp, style = NumberStyle)
                }
            }
            Slider(
                value = kelvin.coerceIn(min, max).toFloat(),
                onValueChange = { onChange((it / 50).roundToInt() * 50) },
                valueRange = min.toFloat()..max.toFloat(),
                colors = SliderDefaults.colors(thumbColor = ink, activeTrackColor = ink, inactiveTrackColor = ink.copy(alpha = .28f)),
                modifier = Modifier.fillMaxWidth().semantics { contentDescription = temperatureLabel },
            )
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("$min K", color = ink, fontSize = 10.sp, style = NumberStyle)
                Text("$max K", color = ink, fontSize = 10.sp, style = NumberStyle)
            }
        }
        PresetScale(kelvin, onChange)
    }
}

@Composable
private fun FilterDock(
    dark: Boolean,
    enabled: Boolean,
    backdrop: GraphicsLayer?,
    contentPosition: () -> Offset,
    onClick: () -> Unit,
) {
    val gradient = when {
        dark && enabled -> listOf(Color(0xCE795340), Color(0xE0442B23))
        dark -> listOf(Color(0xCE515153), Color(0xE02D2D30))
        enabled -> listOf(Color(0xECFFF5EA), Color(0xDDEACABA))
        else -> listOf(Color(0xECFFFFFF), Color(0xDDE0E0DF))
    }
    val ink = if (dark) Color(0xFFFFF7F0) else Color(0xFF2E1A13)
    val lens = if (dark) Color.White.copy(alpha = .12f) else Color.White.copy(alpha = .55f)
    var dockPosition by remember { mutableStateOf(Offset.Zero) }
    Box(
        Modifier.fillMaxWidth().heightIn(min = DockHeight).shadow(10.dp, PanelShape)
            .onGloballyPositioned { dockPosition = it.positionInRoot() }
            .clip(PanelShape)
            .border(1.dp, ink.copy(alpha = .27f), PanelShape)
            .clickable(role = Role.Button, onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        if (backdrop != null) {
            Canvas(Modifier.matchParentSize().blur(18.dp)) {
                val shift = contentPosition() - dockPosition
                translate(shift.x, shift.y) { drawLayer(backdrop) }
            }
        }
        Box(Modifier.matchParentSize().background(Brush.linearGradient(gradient)))
        Row(
            Modifier.fillMaxWidth().padding(5.dp).clip(RoundedCornerShape(22.dp))
                .background(Brush.verticalGradient(listOf(lens, Color.Transparent)))
                .border(1.dp, lens, RoundedCornerShape(22.dp))
                .padding(horizontal = 14.dp, vertical = 13.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Canvas(Modifier.size(19.dp)) {
                val stroke = 1.6.dp.toPx()
                drawArc(ink, -51f, 282f, false, topLeft = Offset(stroke, stroke),
                    size = Size(size.width - 2 * stroke, size.height - 2 * stroke), style = Stroke(stroke, cap = StrokeCap.Round))
                drawLine(ink, Offset(center.x, stroke), Offset(center.x, center.y), stroke, StrokeCap.Round)
            }
            Text(if (enabled) stringResource(R.string.disable_filter) else stringResource(R.string.enable_filter), color = ink, fontSize = 16.sp,
                fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
        }
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

@Composable
private fun PresetScale(kelvin: Int, onChange: (Int) -> Unit) {
    val colors = MaterialTheme.colorScheme
    Column(Modifier.selectableGroup(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Presets.chunked(2).forEach { presets ->
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                presets.forEach { (label, value) ->
                    val selected = value == kelvin
                    val edge = if (selected) Color(0xFFB87048) else colors.outlineVariant
                    Row(
                        Modifier.weight(1f).heightIn(min = 48.dp).clip(ControlShape)
                            .background(if (selected) colors.surfaceContainerHighest else colors.surface)
                            .border(1.dp, edge, ControlShape)
                            .selectable(selected, role = Role.RadioButton, onClick = { onChange(value) })
                            .padding(horizontal = 9.dp, vertical = 5.dp),
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Box(Modifier.size(8.dp).then(
                            if (selected) Modifier.background(Color(0xFFFFA76E), CircleShape)
                            else Modifier.border(1.dp, Color(0xFFC18766), CircleShape),
                        ))
                        Text(stringResource(label), Modifier.weight(1f), color = colors.onSurface, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                        Text("$value K", color = colors.onSurfaceVariant, fontSize = 10.sp, style = NumberStyle)
                    }
                }
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
            Choice(stringResource(R.string.sun_schedule), config.auto && config.sun, onClick = { onConfigChange { copy(auto = true, sun = true) } })
            Choice(stringResource(R.string.manual_schedule), config.auto && !config.sun, onClick = { onConfigChange { copy(auto = true, sun = false) } })
            Choice(stringResource(R.string.always), !config.auto, onClick = { onConfigChange { copy(auto = false, sun = false) } })
        }

        if (!config.auto) return@Column
        Column {
            if (config.sun) {
                SettingLine(
                    title = stringResource(R.string.location),
                    value = if (locating) stringResource(R.string.locating) else if (config.hasPlace) stringResource(R.string.refresh_location) else stringResource(R.string.find_location),
                    description = placeNote ?: if (config.hasPlace) {
                        "%.2f, %.2f".format(config.lat, config.lon)
                    } else {
                        stringResource(R.string.location_fallback)
                    },
                    onClick = if (locating) null else onLocate,
                )
                if (config.hasPlace) SettingLine(stringResource(R.string.sun_times), timeRange(window))
            }
            if (!config.sun || !config.hasPlace) {
                SettingLine(stringResource(R.string.turn_on), Schedule.hhmm(config.on), onClick = { onPickTime("on") })
                SettingLine(stringResource(R.string.turn_off), Schedule.hhmm(config.off), onClick = { onPickTime("off") })
            }
            SettingLine(
                stringResource(R.string.fade),
                if (config.fade == 0) stringResource(R.string.immediate) else stringResource(R.string.minutes, config.fade),
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
    val profileDescription = if (config.auto) {
        stringResource(R.string.profile_range, timeRange(window))
    } else {
        stringResource(R.string.profile_all_day)
    }
    val colors = MaterialTheme.colorScheme
    val plot = if (config.enabled) colors.onSurface else colors.onSurfaceVariant
    val grid = colors.onSurface.copy(alpha = 0.12f)
    val levels = remember(config.auto, config.fade, window) {
        val planned = config.copy(enabled = true)
        FloatArray(Schedule.DAY) { Schedule.level(planned, it, window) }
    }

    Column(
        Modifier.fillMaxWidth().padding(top = 4.dp, bottom = 4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(stringResource(R.string.now, Schedule.hhmm(minute)), color = colors.onSurfaceVariant, fontSize = 13.sp, style = NumberStyle)
        Canvas(
            Modifier.fillMaxWidth().height(54.dp)
                .semantics {
                    contentDescription = profileDescription
                },
        ) {
            val inset = 7.dp.toPx()
            val bottom = size.height - inset
            val range = bottom - inset
            fun x(m: Int) = inset + (size.width - 2 * inset) * m / Schedule.DAY
            fun y(m: Int) = bottom - range * levels[m % Schedule.DAY]

            val dashes = PathEffect.dashPathEffect(floatArrayOf(4.dp.toPx(), 4.dp.toPx()))
            for (step in 0..2) {
                val y = bottom - range * step / 2
                drawLine(grid, Offset(inset, y), Offset(size.width - inset, y), 1.dp.toPx(), pathEffect = dashes)
            }
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
            val marker = Offset(x(minute), y(minute))
            drawCircle(plot.copy(alpha = .28f), radius = 6.dp.toPx(), center = marker)
            drawCircle(plot, radius = 3.5.dp.toPx(), center = marker)
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
            TextButton(onClick = { onPick(time.hour * 60 + time.minute) }) { Text(stringResource(R.string.done)) }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text(stringResource(R.string.cancel)) } },
    )
}
