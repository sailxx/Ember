[English](README.md) · [العربية](README.ar.md) · [简体中文](README.zh-CN.md) · [繁體中文](README.zh-TW.md) · [Čeština](README.cs.md) · [Français](README.fr.md) · [Deutsch](README.de.md) · [Ελληνικά](README.el.md) · [Bahasa Indonesia](README.id.md) · [Gaeilge](README.ga.md) · [Italiano](README.it.md) · [日本語](README.ja.md) · [한국어](README.ko.md) · **Polski** · [Română](README.ro.md) · [Русский](README.ru.md) · [Español](README.es.md) · [Українська](README.uk.md) · [اردو](README.ur.md) · [ئۇيغۇرچە](README.ug.md) · [Tiếng Việt](README.vi.md)

<div align="center">


<br>

<img src="assets/readme/hero-en.svg" width="100%" alt="Ember — filtr światła niebieskiego do Android">

<a href="https://github.com/sailxx/Ember/releases/latest/download/Ember.apk"><img src="assets/readme/btn-android-en.svg" height="52" alt="Pobierz dla Android"></a>&nbsp; &nbsp;<a href="https://github.com/sailxx/Ember/releases/latest"><img src="assets/readme/btn-new-en.svg" height="52" alt="Co nowego"></a>

**Ember to filtr światła niebieskiego dla Android.** Wieczorem ekran się nagrzewa, rano wraca do normy. Ember samodzielnie oblicza zachód i wschód słońca, bez Internetu i reklam.

</div>

<br>

<img src="assets/readme/features-en.svg" width="100%" alt="Funkcje: filtr, harmonogram, kafelek, prywatność">

<details>
<summary><b>Więcej o funkcjach</b></summary>

### 🔥 Filtr

Ciepła półprzezroczysta warstwa pokrywa cały ekran. **Wytrzymałość** określa jego gęstość, **ściemnianie** przyciemnia ekran (przydaje się w nocy zamiast minimalnej jasności). Warstwa jest rysowana przez usługę dostępności: system traktuje swoje okno jako zaufane, więc dotknięcia przechodzą przez filtr wszędzie, nawet na chronionych ekranach. Ember nigdy nie czyta ekranu. Bez tej usługi filtr powraca do opcji „Wyświetlaj na innych aplikacjach”, gdzie Android 12+ ogranicza krycie i blokuje dotknięcia na niektórych ekranach.

### 🌅 Harmonogram

Trzy tryby: **Przez słońce** — dotknij „Zlokalizuj” obok „Miejsca”, Ember odczyta Twoje współrzędne raz i obliczy zachód i wschód słońca na każdy dzień (algorytm NOAA, bezpośrednio w telefonie); **Do czasu** — własne godziny włączenia i wyłączenia; **Zawsze** — filtr pozostaje włączony, gdy jest włączony. Do czasu ustalenia miejsca „Pod słońcem” obowiązują Twoje własne godziny (domyślnie 21:00–07:00). Zanikanie trwa 0, 15, 30 lub 60 minut. Wykres na ekranie głównym pokazuje, kiedy i jak mocno filtr nagrzewa ekran.

### 🎛 Kafelek i powiadomienie

Kafelek Ember w Szybkich ustawieniach włącza i wyłącza filtr jednym dotknięciem. Powiadomienie pokazuje, co się dzieje („2700 K · do 06:48” lub „Czekam na zachód słońca”) i posiada przycisk „Wyłącz”. Po ponownym uruchomieniu filtr sam się włącza.

### 🔒 Prywatność

Ember ma **brak dostępu do Internetu** — pozwolenia nie ma w manifeście. Współrzędne zapisywane są wyłącznie w ustawieniach aplikacji.

</details>

<br>

<img src="assets/readme/presets-en.svg" width="100%" alt="Ustawienia wstępne: Miękkie, Ciepłe, Świeca, Noc">

<br>

<img src="assets/readme/numbers-en.svg" width="100%" alt="Dwa języki interfejsu, brak Internetu i reklam, cztery ustawienia wstępne i stopniowe przejście">

## O Ember

<img src="assets/readme/about-en.svg" width="100%" alt="O Ember">

Ember uruchomił się **16 października 2026 r** jako mały projekt mający na celu ocieplenie ekranów Android w nocy. Jest darmowy i niekomercyjny. Nie ma reklam, kont ani analiz. Ember nie śledzi Cię, nie zbiera Twoich danych i nigdy tego nie zrobi.

Twoje ustawienia pozostaną w telefonie. Jeśli zdecydujesz się użyć swojej lokalizacji w harmonogramie, Ember odczyta ją raz i obliczy wschód i zachód słońca na urządzeniu. Aplikacja nie ma uprawnień do Internetu. [Polityka prywatności](PRIVACY.md)

## Plan działania

<img src="assets/readme/roadmap-en.svg" width="100%" alt="Plan działania">

- [**0.1**](https://github.com/sailxx/Ember/releases/tag/v0.1) — Pierwsza wersja podgrzewała ekran, śledziła zachód i wschód słońca oraz pokazywała cały dzień na wykresie.
- [**0.2**](https://github.com/sailxx/Ember/releases/tag/v0.2) — Usługa dostępności pozwala, aby krany przechodziły przez filtr.
- [**0.3**](https://github.com/sailxx/Ember/releases/tag/v0.3) — Dodano niestandardowe godziny włączania i wyłączania, co ułatwiło Ember znalezienie Twojej lokalizacji.
- [**0.4**](https://github.com/sailxx/Ember/releases/tag/v0.4) — Przeprojektowano wykres dzienny i dodano wyraźniejsze informacje zwrotne po dotknięciu elementu sterującego.
- [**0.5**](https://github.com/sailxx/Ember/releases/tag/v0.5) — Zmieniono klucz debugowania na własny klucz Ember do podpisywania wydań.
- [**0.6**](https://github.com/sailxx/Ember/releases/tag/v0.6) — Dodano pokrętło temperatury, wybór jasnego lub ciemnego motywu oraz testy codziennego harmonogramu.
- [**0.7**](https://github.com/sailxx/Ember/releases/tag/v0.7) — Przeniesiono kontrolę temperatury i filtrów na jeden ekran, przeniesiono harmonogram do okna dialogowego i dodano pływający przełącznik.
- [**0.8**](https://github.com/sailxx/Ember/releases/tag/v0.8) — Ustawiliśmy angielski jako domyślny z rosyjskim w Ustawieniach, dodaliśmy ikonę białego kółka i pozwoliliśmy usłudze dostępności samodzielnie zastosować filtr.
- [**1.0**](https://github.com/sailxx/Ember/releases/tag/v1.0) — Uproszczono kartę temperatur i wygładzono suwak. Karta i przycisk filtra zmieniają teraz kolor razem podczas przeciągania.

<details>
<summary><b>Zainstalować</b></summary>

1. Pobierać [`Ember.apk`](https://github.com/sailxx/Ember/releases/latest/download/Ember.apk).
2. Otwórz go na swoim telefonie i zezwól na instalację z nieznanych źródeł.
3. Otwórz Ember, dotknij **Włącz filtr**, następnie wybierz **Skonfiguruj dostęp → Dostępność** i włącz Ember .
   W przypadku aplikacji zainstalowanych poza Play na Android 13+ najpierw otwórz Informacje o aplikacji → ⋮ → „Zezwalaj na ograniczone ustawienia”.
4. Android 8.0 lub nowszy.

</details>

<details>
<summary><b>Rozwój</b></summary>

Kotlin 2.2 + Jetpack Compose (Materiał 3), brak zależności od stron trzecich.

```bash
./gradlew assembleRelease     # APK in app/build/outputs/apk/release/
./gradlew testDebugUnitTest   # sunset, sunrise and the filter curve
./gradlew lintDebug           # Android Lint checks
```

Wymaga JDK 17+ i Android SDK (platforma 35). Generowane są obrazy README — edycja `tools/readme/strings.json` i biegnij `cd tools/readme && npm install && node build.mjs`.

Przed zwolnieniem sprawdź te kroki na urządzeniu w trybie Zawsze:

1. Stuknij kafelek Szybkie ustawienia z wyłączonymi obydwoma uprawnieniami nakładki. Przyznaj jedno i wróć do Ember. Filtr powinien pojawić się bez ponownego dotknięcia kafelka.
2. Przełącz filtr w Szybkich ustawieniach, a następnie wyłącz go w powiadomieniu. Ember i płytka powinny się zgadzać. Zmiana mocy powinna spowodować wyłączenie filtra.
3. Uruchom ponownie z włączonym filtrem. Po odblokowaniu filtr powinien wrócić. Powtórz tę czynność oddzielnie dla usługi ułatwień dostępu i uprawnienia do wyświetlania na innych aplikacjach.

</details>
