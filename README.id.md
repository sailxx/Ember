[English](README.md) · [العربية](README.ar.md) · [简体中文](README.zh-CN.md) · [繁體中文](README.zh-TW.md) · [Čeština](README.cs.md) · [Français](README.fr.md) · [Deutsch](README.de.md) · [Ελληνικά](README.el.md) · **Bahasa Indonesia** · [Gaeilge](README.ga.md) · [Italiano](README.it.md) · [日本語](README.ja.md) · [한국어](README.ko.md) · [Polski](README.pl.md) · [Română](README.ro.md) · [Русский](README.ru.md) · [Español](README.es.md) · [Українська](README.uk.md) · [اردو](README.ur.md) · [ئۇيغۇرچە](README.ug.md) · [Tiếng Việt](README.vi.md)

<div align="center">


<br>

<img src="assets/readme/hero-en.svg" width="100%" alt="Ember — filter cahaya biru untuk Android">

<a href="https://github.com/sailxx/Ember/releases/latest/download/Ember.apk"><img src="assets/readme/btn-android-en.svg" height="52" alt="Unduh untuk Android"></a>&nbsp; & nbsp;<a href="https://github.com/sailxx/Ember/releases/latest"><img src="assets/readme/btn-new-en.svg" height="52" alt="Apa yang baru"></a>

**Ember adalah filter cahaya biru untuk Android.** Malam harinya layar menjadi hangat, paginya kembali normal. Ember bekerja sendiri saat matahari terbenam dan terbit, tanpa internet dan tanpa iklan.

</div>

<br>

<img src="assets/readme/features-en.svg" width="100%" alt="Fitur: filter, jadwal, ubin, privasi">

<details>
<summary><b>Lebih lanjut tentang fiturnya</b></summary>

### 🔥 Menyaring

Lapisan tembus pandang yang hangat menutupi seluruh layar. **Kekuatan** menentukan seberapa padatnya, **peredupan** membuat layar lebih gelap (berguna di malam hari, bukan kecerahan minimum). Lapisan ini digambar oleh layanan aksesibilitas: sistem memperlakukan jendelanya sebagai jendela tepercaya, sehingga sentuhan melewati filter di mana pun, bahkan di layar yang dilindungi. Ember tidak pernah membaca layar. Tanpa layanan ini, filter akan kembali ke “Tampilkan di atas aplikasi lain”, di mana Android 12+ membatasi opasitas dan memblokir ketukan pada beberapa layar.

### 🌅 Jadwal

Tiga mode: **Oleh matahari** — ketuk "Temukan" di samping "Tempat", Ember membaca koordinat Anda satu kali dan menghitung matahari terbenam dan terbit setiap hari (algoritme NOAA, langsung di ponsel); **Seiring waktu** — waktu hidup dan mati Anda sendiri; **Selalu** — filter tetap menyala saat diaktifkan. Hingga suatu tempat disetel, “Demi matahari” akan menggunakan waktu Anda sendiri (21:00–07:00 secara default). Pudarnya berlangsung 0, 15, 30, atau 60 menit. Bagan di layar utama menunjukkan kapan dan seberapa kuat filter menghangatkan layar.

### 🎛 Ubin dan pemberitahuan

Ubin Ember di Pengaturan Cepat mengaktifkan dan menonaktifkan filter dengan satu ketukan. Notifikasi tersebut menunjukkan apa yang terjadi (“2700 K · hingga 06:48” atau “Menunggu matahari terbenam”) dan memiliki tombol “Matikan”. Setelah reboot, filter akan kembali dengan sendirinya.

### 🔒 Pribadi

Ember memiliki **tidak ada akses internet** — izinnya tidak ada dalam manifes. Koordinat hanya disimpan di pengaturan aplikasi.

</details>

<br>

<img src="assets/readme/presets-en.svg" width="100%" alt="Preset: Lembut, Hangat, Lilin, Malam">

<br>

<img src="assets/readme/numbers-en.svg" width="100%" alt="Dua bahasa antarmuka, tanpa internet atau iklan, empat preset, dan transisi bertahap">

## Tentang Ember

<img src="assets/readme/about-en.svg" width="100%" alt="Tentang Ember">

Ember dimulai **16 Oktober 2026** sebagai proyek kecil untuk membuat layar Android lebih hangat di malam hari. Ini gratis dan non-komersial. Tidak ada iklan, akun, atau analitik. Ember tidak melacak Anda, tidak mengumpulkan data Anda, dan tidak akan pernah melakukannya.

Pengaturan Anda tetap ada di ponsel Anda. Jika Anda memilih untuk menggunakan lokasi Anda untuk jadwal, Ember membacanya satu kali dan menghitung matahari terbit dan terbenam di perangkat. Aplikasi ini tidak memiliki izin internet. [Kebijakan privasi](PRIVACY.md)

## Peta jalan

<img src="assets/readme/roadmap-en.svg" width="100%" alt="Peta jalan">

- [**0.1**](https://github.com/sailxx/Ember/releases/tag/v0.1) — Versi pertama menghangatkan layar, mengikuti matahari terbenam dan terbit, dan menampilkan sehari penuh pada grafik.
- [**0.2**](https://github.com/sailxx/Ember/releases/tag/v0.2) — Layanan aksesibilitas membiarkan ketukan melewati filter.
- [**0.3**](https://github.com/sailxx/Ember/releases/tag/v0.3) — Menambahkan waktu hidup dan mati khusus dan memudahkan Ember menemukan lokasi Anda.
- [**0.4**](https://github.com/sailxx/Ember/releases/tag/v0.4) — Mendesain ulang grafik harian dan menambahkan masukan yang lebih jelas saat Anda mengetuk kontrol.
- [**0.5**](https://github.com/sailxx/Ember/releases/tag/v0.5) — Beralih dari kunci debug ke kunci Ember untuk menandatangani rilis.
- [**0.6**](https://github.com/sailxx/Ember/releases/tag/v0.6) — Menambahkan tombol suhu, pilihan tema terang atau gelap, dan tes untuk jadwal harian.
- [**0.7**](https://github.com/sailxx/Ember/releases/tag/v0.7) — Membawa kontrol suhu dan filter ke satu layar, memindahkan jadwal ke dalam dialog, dan menambahkan tombol mengambang.
- [**0.8**](https://github.com/sailxx/Ember/releases/tag/v0.8) — Menjadikan bahasa Inggris sebagai default dengan bahasa Rusia di Pengaturan, menambahkan ikon lingkaran putih, dan membiarkan layanan aksesibilitas menerapkan filternya sendiri.
- [**1.0**](https://github.com/sailxx/Ember/releases/tag/v1.0) — Menyederhanakan kartu suhu dan menghaluskan penggeser. Tombol kartu dan filter sekarang berubah warna bersamaan saat Anda menyeret.

<details>
<summary><b>Memasang</b></summary>

1. Unduh [`Ember.apk`](https://github.com/sailxx/Ember/releases/latest/download/Ember.apk).
2. Buka di ponsel Anda dan izinkan penginstalan dari sumber yang tidak dikenal.
3. Buka Ember, ketuk **Aktifkan filter**, lalu pilih **Atur akses → Aksesibilitas** dan aktifkan Ember .
   Pada Android 13+ untuk aplikasi yang dipasang di luar Play, pertama-tama buka Info aplikasi → ⋮ → “Izinkan pengaturan terbatas”.
4. Android 8.0 atau lebih baru.

</details>

<details>
<summary><b>Perkembangan</b></summary>

Kotlin 2.2 + Jetpack Compose (Materi 3), tidak ada ketergantungan pihak ketiga.

```bash
./gradlew assembleRelease     # APK in app/build/outputs/apk/release/
./gradlew testDebugUnitTest   # sunset, sunrise and the filter curve
./gradlew lintDebug           # Android Lint checks
```

Membutuhkan JDK 17+ dan Android SDK (platform 35). Gambar README dihasilkan — edit `tools/readme/strings.json` dan lari `cd tools/readme && npm install && node build.mjs`.

Sebelum melepaskan, periksa langkah-langkah berikut pada perangkat dalam mode Selalu:

1. Ketuk ubin Pengaturan Cepat dengan kedua izin overlay nonaktif. Berikan salah satunya dan kembali ke Ember . Filter akan muncul tanpa mengetuk ubin lagi.
2. Alihkan filter dari Pengaturan Cepat, lalu matikan dari notifikasi. Ember dan ubin harus setuju. Mengubah kekuatan akan membuat filter mati.
3. Nyalakan ulang dengan filter diaktifkan. Setelah membuka kunci, filter akan kembali. Ulangi dengan layanan aksesibilitas dan izin tampilan di atas aplikasi lain secara terpisah.

</details>
