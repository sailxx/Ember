[English](README.md) · [العربية](README.ar.md) · **简体中文** · [繁體中文](README.zh-TW.md) · [Čeština](README.cs.md) · [Français](README.fr.md) · [Deutsch](README.de.md) · [Ελληνικά](README.el.md) · [Bahasa Indonesia](README.id.md) · [Gaeilge](README.ga.md) · [Italiano](README.it.md) · [日本語](README.ja.md) · [한국어](README.ko.md) · [Polski](README.pl.md) · [Română](README.ro.md) · [Русский](README.ru.md) · [Español](README.es.md) · [Українська](README.uk.md) · [اردو](README.ur.md) · [ئۇيغۇرچە](README.ug.md) · [Tiếng Việt](README.vi.md)

<div align="center">


<br>

<img src="assets/readme/hero-en.svg" width="100%" alt="Ember — Android 的蓝光滤光片">

<a href="https://github.com/sailxx/Ember/releases/latest/download/Ember.apk"><img src="assets/readme/btn-android-en.svg" height="52" alt="下载Android"></a>&nbsp;;<a href="https://github.com/sailxx/Ember/releases/latest"><img src="assets/readme/btn-new-en.svg" height="52" alt="什么是新的"></a>

**Ember是Android的蓝光滤光片。** 晚上屏幕变热，早上又恢复正常。 Ember 自行计算日落和日出，无需网络，无广告。

</div>

<br>

<img src="assets/readme/features-en.svg" width="100%" alt="功能：过滤器、日程表、平铺、隐私">

<details>
<summary><b>有关功能的更多信息</b></summary>

### 🔥 筛选

温暖的半透明层覆盖了整个屏幕。 **力量** 设置它的密度, **调光** 使屏幕变暗（在夜间方便而不是最小亮度）。该图层由辅助服务绘制：系统将其窗口视为可信，因此触摸会在任何地方通过过滤器，即使在受保护的屏幕上也是如此。 Ember 从不读取屏幕。如果没有该服务，过滤器会回退到“在其他应用程序上显示”，其中 Android 12+ 会限制不透明度并阻止某些屏幕上的点击。

### 🌅 日程

三种模式: **靠着太阳** — 点击“地点”旁边的“定位”，Ember 读取您的坐标一次并计算每天的日落和日出（NOAA 算法，就在手机上）; **按时间** — 您自己的上下班时间; **总是** — 过滤器在启用时保持打开状态。在地点确定之前，“By the sun”将使用您自己的时间（默认为 21:00–07:00）。褪色持续 0、15、30 或 60 分钟。主屏幕上的图表显示过滤器加热屏幕的时间和强度。

### 🎛 磁贴和通知

快速设置中的 Ember 磁贴可一键打开和关闭过滤器。通知显示正在发生的事情（“2700 K·直到 06:48”或“等待日落”）并有一个“关闭”按钮。重新启动后，过滤器会自行恢复。

### 🔒 隐私

Ember 有 **没有互联网接入** — 该权限不在清单中。坐标仅存储在应用程序的设置中。

</details>

<br>

<img src="assets/readme/presets-en.svg" width="100%" alt="预设：柔和、温暖、蜡烛、夜晚">

<br>

<img src="assets/readme/numbers-en.svg" width="100%" alt="两种界面语言，无网络，无广告，四种预设，逐步过渡">

## 关于Ember

<img src="assets/readme/about-en.svg" width="100%" alt="关于Ember">

Ember 开始于 **2026 年 10 月 16 日** 作为一个让 Android 屏幕在夜间变得更温暖的小项目。它是免费且非商业性的。没有广告、帐户或分析。 Ember 不会跟踪您，不会收集您的数据，而且永远不会。

您的设置保留在您的手机上。如果您选择将您的位置用于时间表，Ember 会读取一次并在设备上计算出日出和日落。该应用程序没有互联网权限。 [隐私政策](PRIVACY.md)

## 路线图

<img src="assets/readme/roadmap-en.svg" width="100%" alt="路线图">

- [**0.1**](https://github.com/sailxx/Ember/releases/tag/v0.1) — 第一个版本使屏幕变暖，跟随日落和日出，并在图表上显示全天。
- [**0.2**](https://github.com/sailxx/Ember/releases/tag/v0.2) — 无障碍服务让水龙头通过过滤器。
- [**0.3**](https://github.com/sailxx/Ember/releases/tag/v0.3) — 添加了自定义开启和关闭时间，使 Ember 更容易找到您的位置。
- [**0.4**](https://github.com/sailxx/Ember/releases/tag/v0.4) — 重新设计了每日图表，并在点击控件时添加了更清晰的反馈。
- [**0.5**](https://github.com/sailxx/Ember/releases/tag/v0.5) — 从调试密钥切换为 Ember 自己的用于签名版本的密钥。
- [**0.6**](https://github.com/sailxx/Ember/releases/tag/v0.6) — 添加了温度刻度盘、浅色或深色主题的选择以及每日计划的测试。
- [**0.7**](https://github.com/sailxx/Ember/releases/tag/v0.7) — 将温度和过滤器控件带到一个屏幕上，将时间表移至对话框中，并添加一个浮动开关。
- [**0.8**](https://github.com/sailxx/Ember/releases/tag/v0.8) — 在“设置”中将英语设置为默认值，并添加俄语，添加白色圆圈图标，并让辅助服务自行应用过滤器。
- [**1.0**](https://github.com/sailxx/Ember/releases/tag/v1.0) — 简化了温度卡并平滑了滑块。现在，当您拖动时，卡片和过滤器按钮会一起改变颜色。

<details>
<summary><b>安装</b></summary>

1. 下载 [`Ember.apk`](https://github.com/sailxx/Ember/releases/latest/download/Ember.apk).
2. 在手机上打开它并允许从未知来源安装。
3. 打开Ember，点击 **打开过滤器**, 然后选择 **设置访问权限 → 辅助功能** 并启用 Ember 。
   在 Android 13+ 上，对于 Play 之外安装的应用程序，首先打开应用程序信息 → ⋮ →“允许受限设置”。
4. Android 8.0 或更新版本。

</details>

<details>
<summary><b>发展</b></summary>

Kotlin 2.2 + Jetpack Compose（材料 3），无第三方依赖项。

```bash
./gradlew assembleRelease     # APK in app/build/outputs/apk/release/
./gradlew testDebugUnitTest   # sunset, sunrise and the filter curve
./gradlew lintDebug           # Android Lint checks
```

需要 JDK 17+ 和 Android SDK（平台 35）。生成 README 图像 — 编辑 `tools/readme/strings.json` 并运行 `cd tools/readme && npm install && node build.mjs`.

发布之前，请在始终模式下的设备上检查这些步骤:

1. 在关闭两个叠加权限的情况下点击“快速设置”图块。授予其中之一并返回 Ember 。无需再次点击图块即可出现过滤器。
2. 从“快速设置”中切换过滤器，然后从通知中将其关闭。 Ember 与瓷砖应一致。改变强度应该使过滤器关闭。
3. 重新启动并启用过滤器。解锁后，过滤器应返回。分别对辅助功能服务和在其他应用程序上显示的权限重复此操作。

</details>
