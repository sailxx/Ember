[English](README.md) · [العربية](README.ar.md) · [简体中文](README.zh-CN.md) · [繁體中文](README.zh-TW.md) · [Čeština](README.cs.md) · [Français](README.fr.md) · [Deutsch](README.de.md) · [Ελληνικά](README.el.md) · [Bahasa Indonesia](README.id.md) · [Gaeilge](README.ga.md) · **Italiano** · [日本語](README.ja.md) · [한국어](README.ko.md) · [Polski](README.pl.md) · [Română](README.ro.md) · [Русский](README.ru.md) · [Español](README.es.md) · [Українська](README.uk.md) · [اردو](README.ur.md) · [ئۇيغۇرچە](README.ug.md) · [Tiếng Việt](README.vi.md)

<div align="center">


<br>

<img src="assets/readme/hero-en.svg" width="100%" alt="Ember — filtro luce blu per Android">

<a href="https://github.com/sailxx/Ember/releases/latest/download/Ember.apk"><img src="assets/readme/btn-android-en.svg" height="52" alt="Scarica per Android"></a>&nbsp; &nbsp;<a href="https://github.com/sailxx/Ember/releases/latest"><img src="assets/readme/btn-new-en.svg" height="52" alt="Cosa c&#x27;è di nuovo"></a>

**Ember è un filtro luce blu per Android.** La sera lo schermo si riscalda, al mattino torna alla normalità. Ember calcola da solo il tramonto e l'alba, senza Internet e senza pubblicità.

</div>

<br>

<img src="assets/readme/features-en.svg" width="100%" alt="Funzionalità: filtro, pianificazione, riquadro, privacy">

<details>
<summary><b>Maggiori informazioni sulle funzionalità</b></summary>

### 🔥 Filtro

Un caldo strato traslucido copre l'intero schermo. **Forza** imposta quanto è denso, **oscuramento** rende lo schermo più scuro (utile di notte invece della luminosità minima). Il livello è disegnato da un servizio di accessibilità: il sistema tratta la sua finestra come attendibile, quindi i tocchi passano attraverso il filtro ovunque, anche sugli schermi protetti. Ember non legge mai lo schermo. Senza il servizio il filtro torna a "Visualizza su altre app", dove Android 12+ limita l'opacità e blocca i tocchi su alcune schermate.

### 🌅 Programma

Tre modalità: **Al sole** — tocca "Localizza" accanto a "Luogo", Ember legge le tue coordinate una volta e calcola il tramonto e l'alba per ogni giorno (algoritmo NOAA, direttamente sul telefono); **Per tempo** — i tuoi orari di accensione e spegnimento; **Sempre** — il filtro rimane attivo mentre è abilitato. Fino a quando non viene impostato un luogo, "By the sun" utilizza i tuoi orari (21:00–07:00 per impostazione predefinita). La dissolvenza dura 0, 15, 30 o 60 minuti. Il grafico sulla schermata principale mostra quando e quanto fortemente il filtro riscalda lo schermo.

### 🎛 Riquadro e notifica

Il riquadro Ember nelle Impostazioni rapide attiva e disattiva il filtro con un solo tocco. La notifica mostra cosa sta succedendo (“2700 K · fino alle 06:48” o “In attesa del tramonto”) e ha un pulsante “Spegni”. Dopo un riavvio il filtro ritorna da solo.

### 🔒 Privacy

Ember ha **nessun accesso a Internet** — l'autorizzazione non è nel manifest. Le coordinate vengono memorizzate solo nelle impostazioni dell'app.

</details>

<br>

<img src="assets/readme/presets-en.svg" width="100%" alt="Preimpostazioni: Morbido, Caldo, Candela, Notte">

<br>

<img src="assets/readme/numbers-en.svg" width="100%" alt="Due lingue di interfaccia, niente Internet o pubblicità, quattro preimpostazioni e una transizione graduale">

## Informazioni su Ember

<img src="assets/readme/about-en.svg" width="100%" alt="Informazioni su Ember">

Ember è iniziato **16 ottobre 2026** come piccolo progetto per rendere gli schermi Android più caldi di notte. È gratuito e non commerciale. Non ci sono pubblicità, account o analisi. Ember non ti traccia, non raccoglie i tuoi dati e non lo farà mai.

Le tue impostazioni rimangono sul tuo telefono. Se scegli di utilizzare la tua posizione per la pianificazione, Ember la legge una volta e calcola l'alba e il tramonto sul dispositivo. L'app non ha l'autorizzazione Internet. [Politica sulla riservatezza](PRIVACY.md)

## Tabella di marcia

<img src="assets/readme/roadmap-en.svg" width="100%" alt="Tabella di marcia">

- [**0.1**](https://github.com/sailxx/Ember/releases/tag/v0.1) — La prima versione riscaldava lo schermo, seguiva il tramonto e l'alba e mostrava l'intera giornata su un grafico.
- [**0.2**](https://github.com/sailxx/Ember/releases/tag/v0.2) — Il servizio di accessibilità lascia passare i rubinetti attraverso il filtro.
- [**0.3**](https://github.com/sailxx/Ember/releases/tag/v0.3) — Aggiunti orari di accensione e spegnimento personalizzati e reso più semplice per Ember trovare la tua posizione.
- [**0.4**](https://github.com/sailxx/Ember/releases/tag/v0.4) — Riprogettato il grafico giornaliero e aggiunto un feedback più chiaro quando tocchi un controllo.
- [**0.5**](https://github.com/sailxx/Ember/releases/tag/v0.5) — Passato da una chiave di debug alla chiave di Ember per la firma delle versioni.
- [**0.6**](https://github.com/sailxx/Ember/releases/tag/v0.6) — Aggiunto un quadrante della temperatura, una scelta di temi chiari o scuri e test per il programma giornaliero.
- [**0.7**](https://github.com/sailxx/Ember/releases/tag/v0.7) — Abbiamo portato i controlli della temperatura e del filtro su un'unica schermata, spostato il programma in una finestra di dialogo e aggiunto un interruttore mobile.
- [**0.8**](https://github.com/sailxx/Ember/releases/tag/v0.8) — Abbiamo impostato l'inglese come predefinito con il russo nelle Impostazioni, aggiunto l'icona del cerchio bianco e consentito al servizio di accessibilità di applicare il filtro da solo.
- [**1.0**](https://github.com/sailxx/Ember/releases/tag/v1.0) — Semplificata la scheda della temperatura e smussato il cursore. La scheda e il pulsante del filtro ora cambiano colore insieme mentre trascini.

<details>
<summary><b>Installare</b></summary>

1. Scaricamento [`Ember.apk`](https://github.com/sailxx/Ember/releases/latest/download/Ember.apk).
2. Aprilo sul tuo telefono e consenti l'installazione da fonti sconosciute.
3. Apri Ember, tocca **Attiva il filtro**, poi scegli **Configura accesso → Accessibilità** e abilitare Ember .
   Su Android 13+ per le app installate al di fuori di Play, aprire prima Informazioni app → ⋮ → “Consenti impostazioni limitate”.
4. Android 8.0 o successivo.

</details>

<details>
<summary><b>Sviluppo</b></summary>

Kotlin 2.2 + Jetpack Compose (Materiale 3), nessuna dipendenza da terze parti.

```bash
./gradlew assembleRelease     # APK in app/build/outputs/apk/release/
./gradlew testDebugUnitTest   # sunset, sunrise and the filter curve
./gradlew lintDebug           # Android Lint checks
```

Richiede JDK 17+ e Android SDK (piattaforma 35). Vengono generate le immagini README: modifica `tools/readme/strings.json` e corri `cd tools/readme && npm install && node build.mjs`.

Prima del rilascio, controlla questi passaggi su un dispositivo in modalità Sempre:

1. Tocca il riquadro Impostazioni rapide con entrambe le autorizzazioni di sovrapposizione disattivate. Concedetene uno e tornate a Ember. Il filtro dovrebbe apparire senza toccare nuovamente il riquadro.
2. Attiva/disattiva il filtro dalle Impostazioni rapide, quindi disattivalo dalla notifica. Ember e la tessera dovrebbero concordare. La modifica dell'intensità dovrebbe lasciare il filtro spento.
3. Riavviare con il filtro abilitato. Dopo lo sblocco, il filtro dovrebbe ritornare. Ripeti l'operazione separatamente con il servizio di accessibilità e con l'autorizzazione di visualizzazione su altre app.

</details>
