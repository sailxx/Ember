[English](README.md) · [العربية](README.ar.md) · [简体中文](README.zh-CN.md) · [繁體中文](README.zh-TW.md) · [Čeština](README.cs.md) · [Français](README.fr.md) · [Deutsch](README.de.md) · [Ελληνικά](README.el.md) · [Bahasa Indonesia](README.id.md) · [Gaeilge](README.ga.md) · [Italiano](README.it.md) · [日本語](README.ja.md) · **한국어** · [Polski](README.pl.md) · [Română](README.ro.md) · [Русский](README.ru.md) · [Español](README.es.md) · [Українська](README.uk.md) · [اردو](README.ur.md) · [ئۇيغۇرچە](README.ug.md) · [Tiếng Việt](README.vi.md)

<div align="center">


<br>

<img src="assets/readme/hero-en.svg" width="100%" alt="Ember — Android용 블루라이트 필터">

<a href="https://github.com/sailxx/Ember/releases/latest/download/Ember.apk"><img src="assets/readme/btn-android-en.svg" height="52" alt="Android용 다운로드"></a>&nbsp; &nbsp;<a href="https://github.com/sailxx/Ember/releases/latest"><img src="assets/readme/btn-new-en.svg" height="52" alt="새로운 소식"></a>

**Ember는 Android용 블루라이트 필터입니다.** 저녁에는 화면이 따뜻해졌고, 아침에는 다시 정상으로 돌아옵니다. Ember는 인터넷이나 광고 없이 일몰과 일출을 자체적으로 측정합니다.

</div>

<br>

<img src="assets/readme/features-en.svg" width="100%" alt="기능: 필터, 일정, 타일, 개인 정보 보호">

<details>
<summary><b>기능에 대해 자세히 알아보기</b></summary>

### 🔥 필터

따뜻한 반투명 레이어가 화면 전체를 덮습니다. **힘** 밀도를 설정합니다, **디밍** 화면을 더 어둡게 만듭니다(최소 밝기 대신 밤에 편리함). 레이어는 접근성 서비스에 의해 그려집니다. 시스템은 해당 창을 신뢰할 수 있는 것으로 처리하므로 보호된 화면에서도 터치가 모든 곳에서 필터를 통과합니다. Ember는 화면을 읽지 않습니다. 서비스가 없으면 필터는 "다른 앱 위에 표시"로 대체됩니다. 여기서 Android 12+는 불투명도를 제한하고 일부 화면에서 탭을 차단합니다.

### 🌅 일정

세 가지 모드: **태양으로** — "장소" 옆에 있는 "찾기"를 탭하면 Ember가 좌표를 한 번 읽고 매일 일몰과 일출을 계산합니다(전화에서 바로 NOAA 알고리즘).; **시간별** — 나만의 온오프 시간; **언제나** — 활성화된 동안 필터는 계속 켜져 있습니다. 장소가 정해지기 전까지는 'By the sun'은 원하는 시간(기본적으로 21:00~07:00)을 사용합니다. 페이드 효과는 0분, 15분, 30분 또는 60분 동안 지속됩니다. 메인 화면의 차트는 필터가 화면을 따뜻하게 하는 시기와 강도를 보여줍니다.

### 🎛 타일 ​​및 알림

빠른 설정의 Ember 타일은 한 번의 탭으로 필터를 켜고 끕니다. 알림에는 현재 상황('2700K · 06:48까지' 또는 '일몰 대기 중')이 표시되며 '끄기' 버튼이 있습니다. 재부팅하면 필터가 자체적으로 다시 나타납니다.

### 🔒 은둔

Ember에는 **인터넷 접속 불가** — 권한이 매니페스트에 없습니다. 좌표는 앱 설정에만 저장됩니다.

</details>

<br>

<img src="assets/readme/presets-en.svg" width="100%" alt="사전 설정: 부드러움, 따뜻함, 촛불, 밤">

<br>

<img src="assets/readme/numbers-en.svg" width="100%" alt="2개의 인터페이스 언어, 인터넷이나 광고 없음, 4개의 사전 설정 및 점진적 전환">

## Ember 소개

<img src="assets/readme/about-en.svg" width="100%" alt="Ember 소개">

Ember가 시작되었습니다. **2026년 10월 16일** Android 화면을 밤에 더 따뜻하게 만들기 위한 작은 프로젝트입니다. 무료이며 비상업적입니다. 광고, 계정 또는 분석이 없습니다. Ember는 귀하를 추적하지 않으며, 귀하의 데이터를 수집하지 않으며, 앞으로도 절대 수집하지 않습니다.

설정은 휴대전화에 그대로 유지됩니다. 일정에 위치를 사용하기로 선택한 경우 Ember는 이를 한 번 읽고 장치에서 일출과 일몰을 계산합니다. 앱에 인터넷 권한이 없습니다. [개인 정보 보호 정책](PRIVACY.md)

## 로드맵

<img src="assets/readme/roadmap-en.svg" width="100%" alt="로드맵">

- [**0.1**](https://github.com/sailxx/Ember/releases/tag/v0.1) — 첫 번째 버전은 화면을 따뜻하게 하고 일몰과 일출을 거쳐 하루 종일 차트에 표시했습니다.
- [**0.2**](https://github.com/sailxx/Ember/releases/tag/v0.2) — 접근성 서비스를 사용하면 탭이 필터를 통과할 수 있습니다.
- [**0.3**](https://github.com/sailxx/Ember/releases/tag/v0.3) — 사용자 지정 켜기 및 끄기 시간을 추가하고 Ember가 사용자의 위치를 ​​더 쉽게 찾을 수 있도록 했습니다.
- [**0.4**](https://github.com/sailxx/Ember/releases/tag/v0.4) — 일일 차트를 다시 디자인하고 컨트롤을 탭할 때 더 명확한 피드백을 추가했습니다.
- [**0.5**](https://github.com/sailxx/Ember/releases/tag/v0.5) — 릴리스 서명을 위해 디버그 키에서 Ember의 자체 키로 전환했습니다.
- [**0.6**](https://github.com/sailxx/Ember/releases/tag/v0.6) — 온도 다이얼, 밝은 테마 또는 어두운 테마 선택, 일일 일정 테스트가 추가되었습니다.
- [**0.7**](https://github.com/sailxx/Ember/releases/tag/v0.7) — 온도 및 필터 제어를 한 화면으로 가져오고 일정을 대화 상자로 이동했으며 플로팅 스위치를 추가했습니다.
- [**0.8**](https://github.com/sailxx/Ember/releases/tag/v0.8) — 설정에서 영어를 러시아어로 기본 설정하고 흰색 원 아이콘을 추가했으며 접근성 서비스가 자체적으로 필터를 적용하도록 했습니다.
- [**1.0**](https://github.com/sailxx/Ember/releases/tag/v1.0) — 온도 카드를 단순화하고 슬라이더를 부드럽게 했습니다. 이제 드래그하면 카드와 필터 버튼의 색상이 함께 변경됩니다.

<details>
<summary><b>설치하다</b></summary>

1. 다운로드 [`Ember.apk`](https://github.com/sailxx/Ember/releases/latest/download/Ember.apk).
2. 휴대폰에서 열고 알 수 없는 소스로부터의 설치를 허용하세요.
3. Ember를 열고 **필터 켜기**, 그런 다음 선택 **접근 설정 → 접근성** Ember를 활성화합니다.
   Play 외부에 설치된 앱의 경우 Android 13+에서 먼저 앱 정보 → ⋮ → '제한된 설정 허용'을 엽니다.
4. Android 8.0 이상.

</details>

<details>
<summary><b>개발</b></summary>

Kotlin 2.2 + Jetpack Compose(Material 3), 타사 종속 항목 없음.

```bash
./gradlew assembleRelease     # APK in app/build/outputs/apk/release/
./gradlew testDebugUnitTest   # sunset, sunrise and the filter curve
./gradlew lintDebug           # Android Lint checks
```

JDK 17+ 및 Android SDK(플랫폼 35)가 필요합니다. README 이미지가 생성됩니다 - 편집 `tools/readme/strings.json` 그리고 달리다 `cd tools/readme && npm install && node build.mjs`.

출시하기 전에 항상 모드의 기기에서 다음 단계를 확인하세요.:

1. 두 오버레이 권한이 모두 꺼진 상태에서 빠른 설정 타일을 탭하세요. 둘 중 하나를 부여하고 Ember로 돌아갑니다. 타일을 다시 탭하지 않고도 필터가 나타나야 합니다.
2. 빠른 설정에서 필터를 전환한 다음 알림에서 필터를 끄세요. Ember와 타일이 일치해야 합니다. 강도를 변경하면 필터가 꺼진 상태로 유지되어야 합니다.
3. 필터를 활성화한 상태로 재부팅합니다. 잠금을 해제한 후 필터가 반환되어야 합니다. 접근성 서비스와 다른 앱 위에 표시 권한을 별도로 반복합니다.

</details>
