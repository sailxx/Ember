[English](README.md) · [العربية](README.ar.md) · [简体中文](README.zh-CN.md) · [繁體中文](README.zh-TW.md) · [Čeština](README.cs.md) · [Français](README.fr.md) · [Deutsch](README.de.md) · [Ελληνικά](README.el.md) · [Bahasa Indonesia](README.id.md) · [Gaeilge](README.ga.md) · [Italiano](README.it.md) · [日本語](README.ja.md) · [한국어](README.ko.md) · [Polski](README.pl.md) · [Română](README.ro.md) · [Русский](README.ru.md) · **Español** · [Українська](README.uk.md) · [اردو](README.ur.md) · [ئۇيغۇرچە](README.ug.md) · [Tiếng Việt](README.vi.md)

<div align="center">


<br>

<img src="assets/readme/hero-en.svg" width="100%" alt="Ember — filtro de luz azul para Android">

<a href="https://github.com/sailxx/Ember/releases/latest/download/Ember.apk"><img src="assets/readme/btn-android-en.svg" height="52" alt="Descargar para Android"></a>&nbsp;;<a href="https://github.com/sailxx/Ember/releases/latest"><img src="assets/readme/btn-new-en.svg" height="52" alt="Qué hay de nuevo"></a>

**Ember es un filtro de luz azul para Android.** Por la noche la pantalla se calienta, por la mañana vuelve a la normalidad. Ember calcula el atardecer y el amanecer por sí solo, sin Internet ni anuncios.

</div>

<br>

<img src="assets/readme/features-en.svg" width="100%" alt="Características: filtro, programación, mosaico, privacidad">

<details>
<summary><b>Más sobre las características</b></summary>

### 🔥 Filtrar

Una cálida capa translúcida cubre toda la pantalla. **Fortaleza** establece qué tan denso es, **oscurecimiento** oscurece la pantalla (útil por la noche en lugar del brillo mínimo). La capa es dibujada por un servicio de accesibilidad: el sistema trata su ventana como confiable, por lo que los toques pasan por el filtro en todas partes, incluso en pantallas protegidas. Ember nunca lee la pantalla. Sin el servicio, el filtro vuelve a "Mostrar sobre otras aplicaciones", donde Android 12+ limita la opacidad y bloquea los toques en algunas pantallas.

### 🌅 Cronograma

Tres modos: **por el sol** — toque "Ubicar" junto a "Lugar", Ember lee sus coordenadas una vez y calcula el atardecer y el amanecer para cada día (algoritmo NOAA, directamente en el teléfono); **por tiempo** — tus propios horarios de entrada y salida; **Siempre** — el filtro permanece encendido mientras está habilitado. Hasta que se establezca un lugar, “By the sun” utiliza tus propios horarios (de 21:00 a 07:00 de forma predeterminada). El desvanecimiento dura 0, 15, 30 o 60 minutos. El gráfico en la pantalla principal muestra cuándo y con qué fuerza el filtro calienta la pantalla.

### 🎛 Azulejo y notificación

El mosaico Ember en Configuración rápida enciende y apaga el filtro con un solo toque. La notificación muestra lo que está sucediendo (“2700 K · hasta las 06:48” o “Esperando el atardecer”) y tiene un botón de “Apagar”. Después de reiniciar, el filtro vuelve a funcionar por sí solo.

### 🔒 Privacidad

Ember tiene **sin acceso a internet** — el permiso no está en el manifiesto. Las coordenadas se almacenan sólo en la configuración de la aplicación.

</details>

<br>

<img src="assets/readme/presets-en.svg" width="100%" alt="Presets: Suave, Cálido, Vela, Noche">

<br>

<img src="assets/readme/numbers-en.svg" width="100%" alt="Dos idiomas de interfaz, sin Internet ni anuncios, cuatro ajustes preestablecidos y una transición gradual">

## Acerca de Ember

<img src="assets/readme/about-en.svg" width="100%" alt="Acerca de Ember">

Ember comenzó el **16 de octubre de 2026** como un pequeño proyecto para hacer que las pantallas Android sean más cálidas por la noche. Es gratis y no comercial. No hay anuncios, cuentas ni análisis. Ember no lo rastrea, no recopila sus datos y nunca lo hará.

Tus configuraciones permanecen en tu teléfono. Si elige utilizar su ubicación para el horario, Ember la lee una vez y calcula el amanecer y el atardecer en el dispositivo. La aplicación no tiene permiso de Internet. [Política de privacidad](PRIVACY.md)

## Hoja de ruta

<img src="assets/readme/roadmap-en.svg" width="100%" alt="Hoja de ruta">

- [**0.1**](https://github.com/sailxx/Ember/releases/tag/v0.1) — La primera versión calentaba la pantalla, seguía el atardecer y el amanecer y mostraba el día completo en un gráfico.
- [**0.2**](https://github.com/sailxx/Ember/releases/tag/v0.2) — El servicio de accesibilidad dejó pasar los grifos por el filtro.
- [**0.3**](https://github.com/sailxx/Ember/releases/tag/v0.3) — Se agregaron horarios de encendido y apagado personalizados y se facilitó que Ember encuentre su ubicación.
- [**0.4**](https://github.com/sailxx/Ember/releases/tag/v0.4) — Rediseñamos el gráfico diario y agregamos comentarios más claros cuando tocas un control.
- [**0.5**](https://github.com/sailxx/Ember/releases/tag/v0.5) — Se cambió de una clave de depuración a la clave propia de Ember para firmar versiones.
- [**0.6**](https://github.com/sailxx/Ember/releases/tag/v0.6) — Se agregó un dial de temperatura, una opción de tema claro u oscuro y pruebas para el horario diario.
- [**0.7**](https://github.com/sailxx/Ember/releases/tag/v0.7) — Llevé los controles de temperatura y filtro a una pantalla, moví el programa a un cuadro de diálogo y agregué un interruptor flotante.
- [**0.8**](https://github.com/sailxx/Ember/releases/tag/v0.8) — Hizo que el inglés fuera el predeterminado junto con el ruso en Configuración, agregó el ícono de círculo blanco y dejó que el servicio de accesibilidad aplicara el filtro por sí solo.
- [**1.0**](https://github.com/sailxx/Ember/releases/tag/v1.0) — Simplificó la tarjeta de temperatura y suavizó el control deslizante. La tarjeta y el botón de filtro ahora cambian de color juntos al arrastrar.

<details>
<summary><b>Instalar</b></summary>

1. Descargar [`Ember.apk`](https://github.com/sailxx/Ember/releases/latest/download/Ember.apk).
2. Ábrelo en tu teléfono y permite la instalación desde fuentes desconocidas.
3. Abra Ember, toque **Activar filtro**, entonces elige **Configurar acceso → Accesibilidad** y habilite Ember.
   En Android 13+ para aplicaciones instaladas fuera de Play, primero abra Información de la aplicación → ⋮ → “Permitir configuraciones restringidas”.
4. Android 8.0 o más reciente.

</details>

<details>
<summary><b>Desarrollo</b></summary>

Kotlin 2.2 + Jetpack Compose (Material 3), sin dependencias de terceros.

```bash
./gradlew assembleRelease     # APK in app/build/outputs/apk/release/
./gradlew testDebugUnitTest   # sunset, sunrise and the filter curve
./gradlew lintDebug           # Android Lint checks
```

Requiere JDK 17+ y Android SDK (plataforma 35). Se generan imágenes README - editar `tools/readme/strings.json` y correr `cd tools/readme && npm install && node build.mjs`.

Antes de soltar, comprueba estos pasos en un dispositivo en modo Siempre:

1. Toque el mosaico de Configuración rápida con ambos permisos de superposición desactivados. Concede cualquiera de los dos y regresa a Ember. El filtro debería aparecer sin volver a tocar el mosaico.
2. Cambie el filtro desde Configuración rápida y luego desactívelo desde la notificación. Ember y el mosaico deben coincidir. Cambiar la fuerza debería dejar el filtro apagado.
3. Reinicie con el filtro habilitado. Después de desbloquearlo, el filtro debería regresar. Repita con el servicio de accesibilidad y con el permiso de visualización sobre otras aplicaciones por separado.

</details>
