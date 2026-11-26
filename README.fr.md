[English](README.md) · [العربية](README.ar.md) · [简体中文](README.zh-CN.md) · [繁體中文](README.zh-TW.md) · [Čeština](README.cs.md) · **Français** · [Deutsch](README.de.md) · [Ελληνικά](README.el.md) · [Bahasa Indonesia](README.id.md) · [Gaeilge](README.ga.md) · [Italiano](README.it.md) · [日本語](README.ja.md) · [한국어](README.ko.md) · [Polski](README.pl.md) · [Română](README.ro.md) · [Русский](README.ru.md) · [Español](README.es.md) · [Українська](README.uk.md) · [اردو](README.ur.md) · [ئۇيغۇرچە](README.ug.md) · [Tiếng Việt](README.vi.md)

<div align="center">


<br>

<img src="assets/readme/hero-en.svg" width="100%" alt="Ember — filtre de lumière bleue pour Android">

<a href="https://github.com/sailxx/Ember/releases/latest/download/Ember.apk"><img src="assets/readme/btn-android-en.svg" height="52" alt="Télécharger pour Android"></a>&nbsp;;<a href="https://github.com/sailxx/Ember/releases/latest"><img src="assets/readme/btn-new-en.svg" height="52" alt="Quoi de neuf"></a>

**Ember est un filtre de lumière bleue pour Android.** Le soir l'écran devient chaud, le matin il revient à la normale. Le Ember calcule seul le coucher et le lever du soleil, sans Internet ni publicité.

</div>

<br>

<img src="assets/readme/features-en.svg" width="100%" alt="Fonctionnalités : filtre, planification, vignette, confidentialité">

<details>
<summary><b>En savoir plus sur les fonctionnalités</b></summary>

### 🔥 Filtre

Une couche translucide chaude recouvre tout l’écran. **Force** définit sa densité, **atténuation** rend l'écran plus sombre (pratique la nuit au lieu d'une luminosité minimale). La couche est dessinée par un service d'accessibilité : le système traite sa fenêtre comme fiable, donc les touches passent partout à travers le filtre, même sur les écrans protégés. Le Ember ne lit jamais l'écran. Sans le service, le filtre revient à « Affichage sur d'autres applications », où Android 12+ limite l'opacité et bloque les clics sur certains écrans.

### 🌅 Calendrier

Trois modes: **Au soleil** — appuyez sur « Localiser » à côté de « Place », le Ember lit vos coordonnées une fois et calcule le coucher et le lever du soleil pour chaque jour (algorithme NOAA, directement sur le téléphone); **Par le temps** — vos propres heures d'entrée et de sortie; **Toujours** — le filtre reste activé lorsqu'il est activé. Jusqu'à ce qu'un lieu soit défini, « Au soleil » utilise vos propres horaires (21h00-07h00 par défaut). Le fondu dure 0, 15, 30 ou 60 minutes. Le graphique sur l'écran principal montre quand et avec quelle intensité le filtre réchauffe l'écran.

### 🎛 Tuile et notification

La vignette Ember dans Paramètres rapides active et désactive le filtre en un seul clic. La notification montre ce qui se passe (« 2700 K · jusqu'à 06h48 » ou « En attente du coucher du soleil ») et comporte un bouton « Éteindre ». Après un redémarrage, le filtre revient tout seul.

### 🔒 Confidentialité

Ember a **pas d'accès internet** — l'autorisation n'est pas dans le manifeste. Les coordonnées sont stockées uniquement dans les paramètres de l'application.

</details>

<br>

<img src="assets/readme/presets-en.svg" width="100%" alt="Préréglages : Doux, Chaud, Bougie, Nuit">

<br>

<img src="assets/readme/numbers-en.svg" width="100%" alt="Deux langues d&#x27;interface, pas d&#x27;Internet ni de publicité, quatre préréglages et une transition progressive">

## À propos du Ember

<img src="assets/readme/about-en.svg" width="100%" alt="À propos du Ember">

Ember a démarré le **16 octobre 2026** comme un petit projet pour rendre les écrans Android plus chauds la nuit. C'est gratuit et non commercial. Il n'y a pas de publicités, de comptes ou d'analyses. Ember ne vous suit pas, ne collecte pas vos données et ne le fera jamais.

Vos paramètres restent sur votre téléphone. Si vous choisissez d'utiliser votre emplacement pour le programme, le Ember le lit une fois et calcule le lever et le coucher du soleil sur l'appareil. L'application n'a aucune autorisation Internet. [Politique de confidentialité](PRIVACY.md)

## Feuille de route

<img src="assets/readme/roadmap-en.svg" width="100%" alt="Feuille de route">

- [**0.1**](https://github.com/sailxx/Ember/releases/tag/v0.1) — La première version réchauffait l’écran, suivait le coucher et le lever du soleil et affichait la journée complète sur une carte.
- [**0.2**](https://github.com/sailxx/Ember/releases/tag/v0.2) — Le service d’accessibilité laisse passer les robinets à travers le filtre.
- [**0.3**](https://github.com/sailxx/Ember/releases/tag/v0.3) — Ajout d'heures d'activation et de désactivation personnalisées et permet au Ember de trouver plus facilement votre emplacement.
- [**0.4**](https://github.com/sailxx/Ember/releases/tag/v0.4) — Refonte du graphique quotidien et ajout de commentaires plus clairs lorsque vous appuyez sur une commande.
- [**0.5**](https://github.com/sailxx/Ember/releases/tag/v0.5) — Passé d'une clé de débogage à la propre clé du Ember pour signer les versions.
- [**0.6**](https://github.com/sailxx/Ember/releases/tag/v0.6) — Ajout d'un cadran de température, d'un choix de thème clair ou sombre et de tests pour le programme quotidien.
- [**0.7**](https://github.com/sailxx/Ember/releases/tag/v0.7) — Ajout des commandes de température et de filtre sur un seul écran, déplacement du calendrier dans une boîte de dialogue et ajout d'un commutateur flottant.
- [**0.8**](https://github.com/sailxx/Ember/releases/tag/v0.8) — L'anglais est devenu la valeur par défaut avec le russe dans les paramètres, a ajouté l'icône en forme de cercle blanc et a laissé le service d'accessibilité appliquer le filtre lui-même.
- [**1.0**](https://github.com/sailxx/Ember/releases/tag/v1.0) — Simplification de la carte de température et lissage du curseur. La carte et le bouton de filtre changent désormais de couleur ensemble à mesure que vous les faites glisser.

<details>
<summary><b>Installer</b></summary>

1. Télécharger [`Ember.apk`](https://github.com/sailxx/Ember/releases/latest/download/Ember.apk).
2. Ouvrez-le sur votre téléphone et autorisez l'installation à partir de sources inconnues.
3. Ouvrez le Ember, appuyez sur **Activer le filtre**, puis choisis **Configurer l'accès → Accessibilité** et activez Ember.
   Sur le Android 13+ pour les applications installées en dehors de Play, ouvrez d'abord Informations sur l'application → ⋮ → « Autoriser les paramètres restreints ».
4. Android 8.0 ou plus récent.

</details>

<details>
<summary><b>Développement</b></summary>

Kotlin 2.2 + Jetpack Compose (Matériau 3), aucune dépendance tierce.

```bash
./gradlew assembleRelease     # APK in app/build/outputs/apk/release/
./gradlew testDebugUnitTest   # sunset, sunrise and the filter curve
./gradlew lintDebug           # Android Lint checks
```

Nécessite JDK 17+ et Android SDK (plateforme 35). Les images README sont générées — modifier `tools/readme/strings.json` et courir `cd tools/readme && npm install && node build.mjs`.

Avant de publier, vérifiez ces étapes sur un appareil en mode Toujours:

1. Appuyez sur la vignette Paramètres rapides avec les deux autorisations de superposition désactivées. Accordez l'un ou l'autre et retournez à Ember. Le filtre devrait apparaître sans appuyer à nouveau sur la vignette.
2. Activez le filtre depuis les paramètres rapides, puis désactivez-le depuis la notification. Ember et le carreau devraient être d'accord. Changer la force devrait laisser le filtre désactivé.
3. Redémarrez avec le filtre activé. Après le déverrouillage, le filtre devrait revenir. Répétez l’opération avec le service d’accessibilité et avec l’autorisation d’affichage sur d’autres applications séparément.

</details>
