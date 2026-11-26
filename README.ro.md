[English](README.md) · [العربية](README.ar.md) · [简体中文](README.zh-CN.md) · [繁體中文](README.zh-TW.md) · [Čeština](README.cs.md) · [Français](README.fr.md) · [Deutsch](README.de.md) · [Ελληνικά](README.el.md) · [Bahasa Indonesia](README.id.md) · [Gaeilge](README.ga.md) · [Italiano](README.it.md) · [日本語](README.ja.md) · [한국어](README.ko.md) · [Polski](README.pl.md) · **Română** · [Русский](README.ru.md) · [Español](README.es.md) · [Українська](README.uk.md) · [اردو](README.ur.md) · [ئۇيغۇرچە](README.ug.md) · [Tiếng Việt](README.vi.md)

<div align="center">


<br>

<img src="assets/readme/hero-en.svg" width="100%" alt="Ember — filtru de lumină albastră pentru Android">

<a href="https://github.com/sailxx/Ember/releases/latest/download/Ember.apk"><img src="assets/readme/btn-android-en.svg" height="52" alt="Descărcați pentru Android"></a>&nbsp; &nbsp;<a href="https://github.com/sailxx/Ember/releases/latest"><img src="assets/readme/btn-new-en.svg" height="52" alt="Ce este nou"></a>

**Ember este un filtru de lumină albastră pentru Android.** Seara ecranul se încălzește, dimineața revine la normal. Ember rezolvă apusul și răsăritul singur, fără internet și fără reclame.

</div>

<br>

<img src="assets/readme/features-en.svg" width="100%" alt="Caracteristici: filtru, program, țiglă, confidențialitate">

<details>
<summary><b>Mai multe despre caracteristici</b></summary>

### 🔥 Filtra

Un strat cald translucid acoperă întregul ecran. **Rezistenţă** stabilește cât de dens este, **estompare** face ecranul mai întunecat (util pe timp de noapte în loc de luminozitate minimă). Stratul este desenat de un serviciu de accesibilitate: sistemul își tratează fereastra ca fiind de încredere, astfel încât atingerile trec prin filtru peste tot, chiar și pe ecranele protejate. Ember nu citește niciodată ecranul. Fără serviciu, filtrul revine la „Afișare peste alte aplicații”, unde Android 12+ limitează opacitatea și blochează atingerile pe unele ecrane.

### 🌅 Programa

Trei moduri: **La soare** — atingeți „Locați” de lângă „Loc”, Ember vă citește coordonatele o dată și calculează apusul și răsăritul soarelui pentru fiecare zi (algoritmul NOAA, chiar pe telefon); **După timp** — orele tale de pornire și oprire; **Întotdeauna** — filtrul rămâne activat când este activat. Până când se stabilește un loc, „By the sun” folosește propriile ore (21:00–07:00 în mod implicit). Fade-ul durează 0, 15, 30 sau 60 de minute. Graficul de pe ecranul principal arată când și cât de puternic încălzește filtrul ecranul.

### 🎛 Tile și notificare

Tigla Ember din Setări rapide pornește și dezactivează filtrul cu o singură atingere. Notificarea arată ce se întâmplă („2700 K · până la 06:48” sau „Așteptăm apusul”) și are un buton „Oprire”. După o repornire, filtrul revine de la sine.

### 🔒 Confidențialitate

Ember are **fara acces la internet** — permisiunea nu este în manifest. Coordonatele sunt stocate numai în setările aplicației.

</details>

<br>

<img src="assets/readme/presets-en.svg" width="100%" alt="Presetari: Moale, Cald, Lumanare, Noapte">

<br>

<img src="assets/readme/numbers-en.svg" width="100%" alt="Două limbi de interfață, fără internet sau reclame, patru presetări și o tranziție treptată">

## Despre Ember

<img src="assets/readme/about-en.svg" width="100%" alt="Despre Ember">

Ember a pornit **16 octombrie 2026** ca un mic proiect pentru a face ecranele Android mai calde noaptea. Este gratuit și necomercial. Nu există reclame, conturi sau analize. Ember nu vă urmărește, nu vă colectează datele și nu o va face niciodată.

Setările tale rămân pe telefonul tău. Dacă alegeți să vă folosiți locația pentru program, Ember o citește o dată și calculează răsăritul și apusul soarelui pe dispozitiv. Aplicația nu are permisiunea de internet. [Politica de confidențialitate](PRIVACY.md)

## Foaia de parcurs

<img src="assets/readme/roadmap-en.svg" width="100%" alt="Foaia de parcurs">

- [**0.1**](https://github.com/sailxx/Ember/releases/tag/v0.1) — Prima versiune a încălzit ecranul, a urmat apusul și răsăritul soarelui și a arătat întreaga zi pe un grafic.
- [**0.2**](https://github.com/sailxx/Ember/releases/tag/v0.2) — Serviciul de accesibilitate a lăsat robinetele să treacă prin filtru.
- [**0.3**](https://github.com/sailxx/Ember/releases/tag/v0.3) — S-au adăugat ore personalizate de pornire și oprire și a făcut mai ușor pentru Ember să vă găsească locația.
- [**0.4**](https://github.com/sailxx/Ember/releases/tag/v0.4) — A reproiectat graficul zilnic și a adăugat un feedback mai clar atunci când atingeți un control.
- [**0.5**](https://github.com/sailxx/Ember/releases/tag/v0.5) — Trecut de la o cheie de depanare la cheia proprie a lui Ember pentru semnarea versiunilor.
- [**0.6**](https://github.com/sailxx/Ember/releases/tag/v0.6) — S-au adăugat un cadran de temperatură, o temă deschisă sau întunecată și teste pentru programul zilnic.
- [**0.7**](https://github.com/sailxx/Ember/releases/tag/v0.7) — Am adus controlul temperaturii și filtrului pe un singur ecran, am mutat programul într-un dialog și am adăugat un comutator plutitor.
- [**0.8**](https://github.com/sailxx/Ember/releases/tag/v0.8) — A făcut limba engleză implicită cu rusă în Setări, a adăugat pictograma cerc alb și a lăsat serviciul de accesibilitate să aplice singur filtrul.
- [**1.0**](https://github.com/sailxx/Ember/releases/tag/v1.0) — A simplificat cardul de temperatură și a netezit glisorul. Cardul și butonul de filtru își schimbă acum culoarea împreună pe măsură ce trageți.

<details>
<summary><b>Instala</b></summary>

1. Descărcați [`Ember.apk`](https://github.com/sailxx/Ember/releases/latest/download/Ember.apk).
2. Deschideți-l pe telefon și permiteți instalarea din surse necunoscute.
3. Deschideți Ember, atingeți **Activați filtrul**, apoi alege **Configurați acces → Accesibilitate** și activați Ember.
   Pe Android 13+ pentru aplicațiile instalate în afara Play, deschideți mai întâi Informații aplicație → ⋮ → „Permite setări restricționate”.
4. Android 8.0 sau mai nou.

</details>

<details>
<summary><b>Dezvoltare</b></summary>

Kotlin 2.2 + Jetpack Compose (Material 3), fără dependențe de terți.

```bash
./gradlew assembleRelease     # APK in app/build/outputs/apk/release/
./gradlew testDebugUnitTest   # sunset, sunrise and the filter curve
./gradlew lintDebug           # Android Lint checks
```

Necesită JDK 17+ și Android SDK (platforma 35). Imaginile README sunt generate — editați `tools/readme/strings.json` și fugi `cd tools/readme && npm install && node build.mjs`.

Înainte de a elibera, verificați acești pași pe un dispozitiv în modul Întotdeauna:

1. Atingeți dala Setări rapide cu ambele permisiuni de suprapunere dezactivate. Acordați oricare dintre ele și reveniți la Ember. Filtrul ar trebui să apară fără a atinge din nou țiglă.
2. Comutați filtrul din Setări rapide, apoi dezactivați-l din notificare. Ember și țigla ar trebui să fie de acord. Schimbarea puterii ar trebui să lase filtrul oprit.
3. Reporniți cu filtrul activat. După deblocare, filtrul ar trebui să revină. Repetați separat cu serviciul de accesibilitate și cu permisiunea de afișare peste alte aplicații.

</details>
