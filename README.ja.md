[English](README.md) · [العربية](README.ar.md) · [简体中文](README.zh-CN.md) · [繁體中文](README.zh-TW.md) · [Čeština](README.cs.md) · [Français](README.fr.md) · [Deutsch](README.de.md) · [Ελληνικά](README.el.md) · [Bahasa Indonesia](README.id.md) · [Gaeilge](README.ga.md) · [Italiano](README.it.md) · **日本語** · [한국어](README.ko.md) · [Polski](README.pl.md) · [Română](README.ro.md) · [Русский](README.ru.md) · [Español](README.es.md) · [Українська](README.uk.md) · [اردو](README.ur.md) · [ئۇيغۇرچە](README.ug.md) · [Tiếng Việt](README.vi.md)

<div align="center">


<br>

<img src="assets/readme/hero-en.svg" width="100%" alt="Ember — Android 用ブルーライトフィルター">

<a href="https://github.com/sailxx/Ember/releases/latest/download/Ember.apk"><img src="assets/readme/btn-android-en.svg" height="52" alt="Android用ダウンロード"></a>&nbsp;&nbsp;<a href="https://github.com/sailxx/Ember/releases/latest"><img src="assets/readme/btn-new-en.svg" height="52" alt="新着情報"></a>

**Emberは、Android用のブルーライトカットです。** 夕方になると画面が暖かくなり、朝になると通常の画面に戻ります。 Ember は、インターネットや広告を使用せずに、日没と日の出を自動的に計算します。

</div>

<br>

<img src="assets/readme/features-en.svg" width="100%" alt="機能: フィルター、スケジュール、タイル、プライバシー">

<details>
<summary><b>機能の詳細</b></summary>

### 🔥 フィルター

温かみのある半透明の層が画面全体を覆います。 **強さ** 密度を設定します, **調光** 画面を暗くします (最低輝度の代わりに夜間に便利です)。このレイヤーはアクセシビリティ サービスによって描画されます。システムはそのウィンドウを信頼できるものとして扱うため、タッチはどこでも (保護された画面上であっても) フィルターを通過します。 Emberは画面を読み込むことはありません。サービスがないと、フィルターは「他のアプリの上に表示」に戻り、Android 12+ では不透明度が制限され、一部の画面でのタップがブロックされます。

### 🌅 スケジュール

3つのモード: **太陽のそばで** — 「場所」の横にある「位置特定」をタップすると、Ember が座標を 1 回読み取り、毎日の日没と日の出を計算します (NOAA アルゴリズム、携帯電話で直接); **時間によって** — 自分だけのオンタイムとオフタイム; **いつも** — 有効になっている間、フィルターはオンのままになります。場所が決まるまで、「By the sun」では自分の時間を使用します（デフォルトでは 21:00 ～ 07:00）。フェードは 0、15、30、または 60 分間続きます。メイン画面のグラフには、フィルターがいつ、どのくらい強く画面を温めるかが表示されます。

### 🎛 タイルと通知

クイック設定の Ember タイルは、ワンタップでフィルターのオンとオフを切り替えます。通知には何が起こっているか (「2700 K · 06:48 まで」または「日没を待っています」) が表示され、「オフにする」ボタンが付いています。再起動後、フィルターは自動的に戻ります。

### 🔒 プライバシー

Emberには **インターネットにアクセスできない** — 許可がマニフェストにありません。座標はアプリの設定にのみ保存されます。

</details>

<br>

<img src="assets/readme/presets-en.svg" width="100%" alt="プリセット: ソフト、ウォーム、キャンドル、ナイト">

<br>

<img src="assets/readme/numbers-en.svg" width="100%" alt="2 つのインターフェイス言語、インターネットや広告なし、4 つのプリセット、段階的な移行">

## Emberについて

<img src="assets/readme/about-en.svg" width="100%" alt="Emberについて">

Ember 開始日 **2026 年 10 月 16 日** Android の画面を夜間暖かくするための小さなプロジェクトとして。無料で非営利です。広告、アカウント、分析はありません。 Ember はあなたを追跡したり、データを収集したりすることはなく、今後も収集することはありません。

設定は携帯電話に残ります。スケジュールに現在地を使用することを選択した場合、Ember はそれを 1 回読み取り、デバイス上で日の出と日の入りを計算します。アプリにはインターネット権限がありません。 [プライバシーポリシー](PRIVACY.md)

## ロードマップ

<img src="assets/readme/roadmap-en.svg" width="100%" alt="ロードマップ">

- [**0.1**](https://github.com/sailxx/Ember/releases/tag/v0.1) — 最初のバージョンでは、画面が暖かくなり、日没と日の出を追跡し、グラフ上に 1 日全体を表示しました。
- [**0.2**](https://github.com/sailxx/Ember/releases/tag/v0.2) — アクセシビリティ サービスにより、タップがフィルターを通過できるようになります。
- [**0.3**](https://github.com/sailxx/Ember/releases/tag/v0.3) — カスタムのオン時間とオフ時間を追加し、Ember があなたの場所を見つけやすくしました。
- [**0.4**](https://github.com/sailxx/Ember/releases/tag/v0.4) — 日次チャートを再設計し、コントロールをタップしたときのより明確なフィードバックを追加しました。
- [**0.5**](https://github.com/sailxx/Ember/releases/tag/v0.5) — リリース署名用にデバッグ キーから Ember 独自のキーに切り替えました。
- [**0.6**](https://github.com/sailxx/Ember/releases/tag/v0.6) — 温度ダイヤル、明るいテーマまたは暗いテーマの選択、毎日のスケジュールのテストが追加されました。
- [**0.7**](https://github.com/sailxx/Ember/releases/tag/v0.7) — 温度とフィルターの制御を 1 つの画面にまとめ、スケジュールをダイアログに移動し、フローティング スイッチを追加しました。
- [**0.8**](https://github.com/sailxx/Ember/releases/tag/v0.8) — [設定] で英語をデフォルトにしてロシア語にし、白い円のアイコンを追加して、アクセシビリティ サービスが独自にフィルターを適用できるようにします。
- [**1.0**](https://github.com/sailxx/Ember/releases/tag/v1.0) — 温度カードを簡素化し、スライダーを滑らかにしました。ドラッグすると、カードとフィルター ボタンの色が一緒に変化するようになりました。

<details>
<summary><b>インストール</b></summary>

1. ダウンロード [`Ember.apk`](https://github.com/sailxx/Ember/releases/latest/download/Ember.apk).
2. 携帯電話で開き、不明なソースからのインストールを許可します。
3. Ember を開き、 をタップします **フィルターをオンにする**, それから選択してください **アクセスの設定 → アクセシビリティ** Ember を有効にします。
   Android 13+ では、Play 以外でインストールされたアプリの場合、まずアプリ情報 → ⋮ → 「制限付き設定を許可」を開きます。
4. Android 8.0以降。

</details>

<details>
<summary><b>発達</b></summary>

Kotlin 2.2 + Jetpack Compose (マテリアル 3)、サードパーティの依存関係なし。

```bash
./gradlew assembleRelease     # APK in app/build/outputs/apk/release/
./gradlew testDebugUnitTest   # sunset, sunrise and the filter curve
./gradlew lintDebug           # Android Lint checks
```

JDK 17+ および Android SDK (プラットフォーム 35) が必要です。 README 画像の生成 — 編集 `tools/readme/strings.json` そして走ります `cd tools/readme && npm install && node build.mjs`.

リリースする前に、常時モードのデバイスで次の手順を確認してください。:

1. 両方のオーバーレイ権限をオフにして、[クイック設定] タイルをタップします。どちらかを許可して Ember に戻ります。タイルを再度タップしなくてもフィルターが表示されます。
2. クイック設定からフィルターを切り替えて、通知からフィルターをオフにします。 Emberとタイルが一致するはずです。強度を変更する場合は、フィルターをオフのままにする必要があります。
3. フィルターを有効にして再起動します。ロックを解除するとフィルターが戻ります。ユーザー補助サービスと他のアプリ上に表示する権限を個別に繰り返します。

</details>
