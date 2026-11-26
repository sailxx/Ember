[English](README.md) · [العربية](README.ar.md) · [简体中文](README.zh-CN.md) · [繁體中文](README.zh-TW.md) · **Čeština** · [Français](README.fr.md) · [Deutsch](README.de.md) · [Ελληνικά](README.el.md) · [Bahasa Indonesia](README.id.md) · [Gaeilge](README.ga.md) · [Italiano](README.it.md) · [日本語](README.ja.md) · [한국어](README.ko.md) · [Polski](README.pl.md) · [Română](README.ro.md) · [Русский](README.ru.md) · [Español](README.es.md) · [Українська](README.uk.md) · [اردو](README.ur.md) · [ئۇيغۇرچە](README.ug.md) · [Tiếng Việt](README.vi.md)

<div align="center">


<br>

<img src="assets/readme/hero-en.svg" width="100%" alt="Ember — filtr modrého světla pro Android">

<a href="https://github.com/sailxx/Ember/releases/latest/download/Ember.apk"><img src="assets/readme/btn-android-en.svg" height="52" alt="Stáhnout pro Android"></a>&nbsp; &nbsp;<a href="https://github.com/sailxx/Ember/releases/latest"><img src="assets/readme/btn-new-en.svg" height="52" alt="Co je nového"></a>

**Ember je filtr modrého světla pro Android.** Večer se obrazovka zahřeje, ráno se vrátí do normálu. Ember funguje západ a východ slunce sám, bez internetu a bez reklam.

</div>

<br>

<img src="assets/readme/features-en.svg" width="100%" alt="Funkce: filtr, plán, dlaždice, soukromí">

<details>
<summary><b>Více o funkcích</b></summary>

### 🔥 Filtr

Teplá průsvitná vrstva pokrývá celou obrazovku. **Pevnost** nastavuje, jak je hustý, **stmívání** ztmaví obrazovku (praktické v noci místo minimálního jasu). Vrstva je nakreslena službou usnadnění: systém považuje své okno za důvěryhodné, takže dotyky procházejí filtrem všude, dokonce i na chráněných obrazovkách. Ember nikdy nečte obrazovku. Bez této služby se filtr vrátí zpět na „Zobrazit přes jiné aplikace“, kde Android 12+ omezuje neprůhlednost a blokuje klepnutí na některé obrazovky.

### 🌅 Naplánovat

Tři režimy: **Na slunci** — klepněte na „Vyhledat“ vedle „Místo“, Ember jednou přečte vaše souřadnice a vypočítá západ a východ slunce pro každý den (algoritmus NOAA přímo v telefonu); **Podle času** — vaše vlastní časy zapnutí a vypnutí; **Vždy** — filtr zůstane zapnutý, když je povolen. Dokud není místo nastaveno, „U slunce“ používá vaše vlastní časy (ve výchozím nastavení 21:00–07:00). Vyblednutí trvá 0, 15, 30 nebo 60 minut. Tabulka na hlavní obrazovce ukazuje, kdy a jak silně filtr zahřívá obrazovku.

### 🎛 Dlaždice a oznámení

Dlaždice Ember v Rychlém nastavení zapíná a vypíná filtr jediným klepnutím. Oznámení ukazuje, co se děje („2700 K · do 06:48“ nebo „Čekání na západ slunce“) a má tlačítko „Vypnout“. Po restartu se filtr sám vrátí.

### 🔒 Soukromí

Ember má **žádný přístup k internetu** — povolení není v manifestu. Souřadnice jsou uloženy pouze v nastavení aplikace.

</details>

<br>

<img src="assets/readme/presets-en.svg" width="100%" alt="Předvolby: Soft, Warm, Candle, Night">

<br>

<img src="assets/readme/numbers-en.svg" width="100%" alt="Dva jazyky rozhraní, žádný internet nebo reklamy, čtyři předvolby a postupný přechod">

## O Ember

<img src="assets/readme/about-en.svg" width="100%" alt="O Ember">

Ember začal **16. října 2026** jako malý projekt na zahřátí obrazovek Android v noci. Je to zdarma a nekomerční. Nejsou zde žádné reklamy, účty ani analýzy. Ember vás nesleduje, neshromažďuje vaše data a nikdy nebude.

Vaše nastavení zůstanou v telefonu. Pokud se rozhodnete pro plán použít svou polohu, Ember ji jednou přečte a zjistí východ a západ slunce na zařízení. Aplikace nemá oprávnění k internetu. [Zásady ochrany osobních údajů](PRIVACY.md)

## Cestovní mapa

<img src="assets/readme/roadmap-en.svg" width="100%" alt="Cestovní mapa">

- [**0.1**](https://github.com/sailxx/Ember/releases/tag/v0.1) — První verze zahřála obrazovku, sledovala západ a východ slunce a ukazovala celý den na grafu.
- [**0.2**](https://github.com/sailxx/Ember/releases/tag/v0.2) — Služba přístupnosti nechá kohoutky projít filtrem.
- [**0.3**](https://github.com/sailxx/Ember/releases/tag/v0.3) — Přidány vlastní časy zapnutí a vypnutí a usnadnily Ember nalezení vaší polohy.
- [**0.4**](https://github.com/sailxx/Ember/releases/tag/v0.4) — Byl přepracován denní graf a přidána jasnější zpětná vazba, když klepnete na ovládací prvek.
- [**0.5**](https://github.com/sailxx/Ember/releases/tag/v0.5) — Přepnuto z ladícího klíče na vlastní klíč Ember pro podepisování vydání.
- [**0.6**](https://github.com/sailxx/Ember/releases/tag/v0.6) — Přidán volič teploty, výběr světlého nebo tmavého motivu a testy pro denní rozvrh.
- [**0.7**](https://github.com/sailxx/Ember/releases/tag/v0.7) — Přinesl ovládání teploty a filtru na jednu obrazovku, přesunul plán do dialogu a přidal plovoucí přepínač.
- [**0.8**](https://github.com/sailxx/Ember/releases/tag/v0.8) — Nastavila angličtinu jako výchozí s ruštinou v Nastavení, přidala ikonu s bílým kruhem a nechala službu usnadnění, aby použila filtr sama.
- [**1.0**](https://github.com/sailxx/Ember/releases/tag/v1.0) — Zjednodušila teplotní kartu a vyhladila posuvník. Karta a tlačítko filtru nyní při tažení společně mění barvu.

<details>
<summary><b>Instalovat</b></summary>

1. Stáhnout [`Ember.apk`](https://github.com/sailxx/Ember/releases/latest/download/Ember.apk).
2. Otevřete jej v telefonu a povolte instalaci z neznámých zdrojů.
3. Otevřete Ember, klepněte na **Zapněte filtr**, pak si vyberte **Nastavit přístup → Usnadnění** a povolte Ember .
   Na Android 13+ pro aplikace nainstalované mimo Play nejprve otevřete Informace o aplikaci → ⋮ → „Povolit omezená nastavení“.
4. Android 8.0 nebo novější.

</details>

<details>
<summary><b>Rozvoj</b></summary>

Kotlin 2.2 + Jetpack Compose (materiál 3), žádné závislosti na třetích stranách.

```bash
./gradlew assembleRelease     # APK in app/build/outputs/apk/release/
./gradlew testDebugUnitTest   # sunset, sunrise and the filter curve
./gradlew lintDebug           # Android Lint checks
```

Vyžaduje JDK 17+ a Android SDK (platforma 35). Jsou generovány obrázky README — upravit `tools/readme/strings.json` a běžet `cd tools/readme && npm install && node build.mjs`.

Před uvolněním zkontrolujte tyto kroky na zařízení v režimu Vždy:

1. Klepněte na dlaždici Rychlé nastavení s vypnutými oběma oprávněními k překrytí. Udělte jeden a vraťte se do Ember. Filtr by se měl objevit bez dalšího poklepání na dlaždici.
2. Přepněte filtr z Rychlého nastavení a poté jej vypněte v oznámení. Ember a dlaždice by měly souhlasit. Změna síly by měla nechat filtr vypnutý.
3. Restartujte se zapnutým filtrem. Po odblokování by se měl filtr vrátit. Opakujte se službou usnadnění a s oprávněním zobrazení přes jiné aplikace samostatně.

</details>
