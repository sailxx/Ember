[English](README.md) · [العربية](README.ar.md) · [简体中文](README.zh-CN.md) · **繁體中文** · [Čeština](README.cs.md) · [Français](README.fr.md) · [Deutsch](README.de.md) · [Ελληνικά](README.el.md) · [Bahasa Indonesia](README.id.md) · [Gaeilge](README.ga.md) · [Italiano](README.it.md) · [日本語](README.ja.md) · [한국어](README.ko.md) · [Polski](README.pl.md) · [Română](README.ro.md) · [Русский](README.ru.md) · [Español](README.es.md) · [Українська](README.uk.md) · [اردو](README.ur.md) · [ئۇيغۇرچە](README.ug.md) · [Tiếng Việt](README.vi.md)

<div align="center">


<br>

<img src="assets/readme/hero-en.svg" width="100%" alt="Ember — Android 的藍光濾光片">

<a href="https://github.com/sailxx/Ember/releases/latest/download/Ember.apk"><img src="assets/readme/btn-android-en.svg" height="52" alt="下載Android"></a>&nbsp;;<a href="https://github.com/sailxx/Ember/releases/latest"><img src="assets/readme/btn-new-en.svg" height="52" alt="什麼是新的"></a>

**Ember是Android的藍光濾光片。** 晚上螢幕變熱，早上又恢復正常。 Ember 自行計算日落和日出，無需網絡，無廣告。

</div>

<br>

<img src="assets/readme/features-en.svg" width="100%" alt="功能：篩選器、行程表、平鋪、隱私">

<details>
<summary><b>有關功能的更多信息</b></summary>

### 🔥 篩選

溫暖的半透明層覆蓋了整個螢幕。 **力量** 設定它的密度, **調光** 使螢幕變暗（在夜間方便而不是最小亮度）。此圖層由輔助服務繪製：系統將其視窗視為可信，因此觸控會在任何地方通過過濾器，即使在受保護的螢幕上也是如此。 Ember 從不讀取螢幕。如果沒有該服務，過濾器會回退到“在其他應用程式上顯示”，其中 Android 12+ 會限制不透明度並阻止某些螢幕上的點擊。

### 🌅 行程

三種模式: **靠著太陽** — 點擊“地點”旁邊的“定位”，Ember 讀取您的座標一次併計算每天的日落和日出（NOAA 演算法，就在手機上）; **按時間** — 您自己的通勤時間; **總是** — 過濾器在啟用時保持開啟。在地點確定之前，「By the sun」將使用您自己的時間（預設為 21:00–07:00）。褪色持續 0、15、30 或 60 分鐘。主螢幕上的圖表顯示濾鏡加熱螢幕的時間和強度。

### 🎛 磁貼和通知

快速設定中的 Ember 磁貼可一鍵開啟和關閉過濾器。通知顯示正在發生的事情（“2700 K·直到 06:48”或“等待日落”）並有一個“關閉”按鈕。重新啟動後，過濾器會自行恢復。

### 🔒 隱私

Ember 有 **沒有網路存取** — 該權限不在清單中。座標僅儲存在應用程式的設定中。

</details>

<br>

<img src="assets/readme/presets-en.svg" width="100%" alt="預設：柔和、溫暖、蠟燭、夜晚">

<br>

<img src="assets/readme/numbers-en.svg" width="100%" alt="兩種介面語言，無網絡，無廣告，四種預設，逐步過渡">

## 關於Ember

<img src="assets/readme/about-en.svg" width="100%" alt="關於Ember">

Ember 開始於 **2026 年 10 月 16 日** 作為一個讓 Android 螢幕在夜間變得更溫暖的小項目。它是免費且非商業性的。沒有廣告、帳號或分析。 Ember 不會追蹤您，不會收集您的數據，而且永遠不會。

您的設定保留在您的手機上。如果您選擇將您的位置用於時間表，Ember 會讀取一次並在裝置上計算出日出和日落。該應用程式沒有互聯網權限。 [隱私權政策](PRIVACY.md)

## 路線圖

<img src="assets/readme/roadmap-en.svg" width="100%" alt="路線圖">

- [**0.1**](https://github.com/sailxx/Ember/releases/tag/v0.1) — 第一個版本使螢幕變暖，跟隨日落和日出，並在圖表上顯示全天。
- [**0.2**](https://github.com/sailxx/Ember/releases/tag/v0.2) — 無障礙服務讓水龍頭通過過濾器。
- [**0.3**](https://github.com/sailxx/Ember/releases/tag/v0.3) — 新增了自訂開啟和關閉時間，讓 Ember 更容易找到您的位置。
- [**0.4**](https://github.com/sailxx/Ember/releases/tag/v0.4) — 重新設計了每日圖表，並在點擊控制項時添加了更清晰的回饋。
- [**0.5**](https://github.com/sailxx/Ember/releases/tag/v0.5) — 從調試金鑰切換為 Ember 自己的用於簽署版本的金鑰。
- [**0.6**](https://github.com/sailxx/Ember/releases/tag/v0.6) — 新增了溫度刻度盤、淺色或深色主題的選擇以及每日計劃的測試。
- [**0.7**](https://github.com/sailxx/Ember/releases/tag/v0.7) — 將溫度和過濾器控制項帶到一個螢幕上，將時間表移至對話方塊中，並添加浮動開關。
- [**0.8**](https://github.com/sailxx/Ember/releases/tag/v0.8) — 在“設定”中將英語設定為預設值，並添加俄語，添加白色圓圈圖標，並讓輔助服務自行套用過濾器。
- [**1.0**](https://github.com/sailxx/Ember/releases/tag/v1.0) — 簡化了溫度卡並平滑了滑塊。現在，當您拖曳時，卡片和濾鏡按鈕會一起改變顏色。

<details>
<summary><b>安裝</b></summary>

1. 下載 [`Ember.apk`](https://github.com/sailxx/Ember/releases/latest/download/Ember.apk).
2. 在手機上打開它並允許從未知來源安裝。
3. 開啟Ember，點選 **打開過濾器**, 然後選擇 **設定存取權限 → 輔助功能** 並啟用 Ember 。
   在 Android 13+ 上，對於 Play 之外安裝的應用程序，首先打開應用程式資訊 → ⋮ →「允許受限設定」。
4. Android 8.0 或更新版本。

</details>

<details>
<summary><b>發展</b></summary>

Kotlin 2.2 + Jetpack Compose（材料 3），無第三方相依性。

```bash
./gradlew assembleRelease     # APK in app/build/outputs/apk/release/
./gradlew testDebugUnitTest   # sunset, sunrise and the filter curve
./gradlew lintDebug           # Android Lint checks
```

需要 JDK 17+ 和 Android SDK（平台 35）。產生 README 影像 — 編輯 `tools/readme/strings.json` 並運行 `cd tools/readme && npm install && node build.mjs`.

在發布之前，請在始終模式下的裝置上檢查這些步驟:

1. 在關閉兩個疊加權限的情況下點選「快速設定」圖塊。授予其中之一併返回 Ember 。無需再次點擊圖塊即可出現過濾器。
2. 從「快速設定」切換過濾器，然後從通知中將其關閉。 Ember 與磁磚應一致。改變強度應該使過濾器關閉。
3. 重新啟動並啟用過濾器。解鎖後，過濾器應返回。分別對輔助功能服務和在其他應用程式上顯示的權限重複此操作。

</details>
