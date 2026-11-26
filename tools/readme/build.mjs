// Генерирует SVG-плашки README в цветах интерфейса Ember.
// Шрифты Golos Text и JetBrains Mono встраиваются урезанными под каждый язык (тексты — strings.json).
// Запуск: cd tools/readme && npm install && node build.mjs
import fs from 'node:fs';
import path from 'node:path';
import { fileURLToPath } from 'node:url';
import { createKit, ICONS } from './kit.mjs';

const HERE = path.dirname(fileURLToPath(import.meta.url));
const REPO = path.resolve(HERE, '../..');
const OUT = path.join(REPO, 'assets/readme');
const VERSION = fs.readFileSync(path.join(REPO, 'app/build.gradle.kts'), 'utf8').match(/versionName = "([^"]+)"/)[1];
const STR = JSON.parse(fs.readFileSync(path.join(HERE, 'strings.json'), 'utf8'));

const AMBER = '#FFD1A7', ORANGE = '#E79F69';
const K = createKit({
  golos: path.join(HERE, '.font/golos.ttf'),
  mono: path.join(HERE, '.font/jbmono.ttf'),
  accent: { hi: AMBER, grad: [AMBER, ORANGE], on: '#FFF5ED', btn: ['#75472E', '#553625'] },
});
const { P, T, card, chip, sectionHead, lines, wrap, one, fitSize, fitText, svg } = K;

const logo = (x, y, s) => `<g transform="translate(${x} ${y}) scale(${s / 72})">
<rect width="72" height="72" rx="19" fill="#000"/>
<circle cx="36" cy="36" r="18" fill="#383838"/><circle cx="36" cy="36" r="10" fill="#FFF"/></g>`;
const SCREEN_DEFS = `<linearGradient id="temperature" x2="100%" y2="80%"><stop stop-color="#804521"/><stop offset="1" stop-color="#4B2A1C"/></linearGradient>
<linearGradient id="dock"><stop stop-color="#70422A"/><stop offset="1" stop-color="#5C3727"/></linearGradient>`;

// Кельвины → RGB (Tanner Helland), как Tint.rgb()
function kelvin(k) {
  const t = k / 100;
  const r = t <= 66 ? 255 : 329.698727446 * (t - 60) ** -0.1332047592;
  const g = t <= 66 ? 99.4708025861 * Math.log(t) - 161.1195681661 : 288.1221695283 * (t - 60) ** -0.0755148492;
  const b = t >= 66 ? 255 : t <= 19 ? 0 : 138.5177312231 * Math.log(t - 10) - 305.0447927307;
  return '#' + [r, g, b].map((v) => Math.round(Math.min(255, Math.max(0, v))).toString(16).padStart(2, '0')).join('');
}

function phone(x, y, t) {
  const sc = t.hero.screen;
  const track = (x, y, width, fill) =>
    `<path d="M${x} ${y}h${width}" fill="none" stroke="${fill}" stroke-width="5" stroke-linecap="round"/>`;
  let body = `<rect width="232" height="404" rx="24" fill="#171717" stroke="#55473F"/>
${logo(14, 15, 23)}
${fitText(47, 32, 'Ember', 160, { size: 16, weight: 800 })}
<rect x="13" y="51" width="206" height="122" rx="17" fill="url(#temperature)"/>
${fitText(25, 72, sc.temperature, 107, { size: 9, fill: '#EAD6C8' })}
${fitText(207, 72, sc.status, 82, { size: 8, anchor: 'end' })}
${fitText(116, 115, '2700 K', 150, { size: 31, weight: 800, anchor: 'middle' })}
${track(27, 142, 176, '#97745D')}${track(27, 142, 120, '#FFF5ED')}
<path d="M151 133v18" stroke="#FFF5ED" stroke-width="2" stroke-linecap="round"/>
${fitText(25, 162, '1200 K', 60, { size: 7, mono: true })}
${fitText(207, 162, '3400 K', 60, { size: 7, mono: true, anchor: 'end' })}`;
  sc.presets.forEach((preset, i) => {
    const x = 13 + i * 52;
    body += `<rect x="${x}" y="182" width="50" height="25" rx="7" fill="${i === 1 ? '#593826' : '#242220'}" stroke="#504036"/>
${fitText(x + 25, 198, preset, 45, { size: 8, anchor: 'middle' })}`;
  });
  body += `${card(13, 217, 206, 55, 11)}
${fitText(23, 235, sc.strength, 88, { size: 8 })}
${fitText(122, 235, sc.dimming, 88, { size: 8 })}
${track(24, 253, 77, '#4B423B')}${track(24, 253, 48, ORANGE)}
${track(124, 253, 77, '#4B423B')}${track(124, 253, 31, ORANGE)}
${card(13, 281, 206, 66, 11)}
${fitText(23, 298, sc.profile, 176, { size: 9, weight: 700 })}
<path d="M25 316H68l7 21h101l7-21h23" fill="none" stroke="#F4D8C2" stroke-width="1.5"/>
<rect x="13" y="359" width="206" height="32" rx="13" fill="url(#dock)"/>
${fitText(116, 379, sc.action, 188, { size: 10, weight: 700, anchor: 'middle' })}`;
  return `<g transform="translate(${x} ${y})">${body}</g>`;
}

// ---------- мини-экран под фильтром ----------
function tinted(x, y, k) {
  let s = `<clipPath id="ts${k}"><rect width="150" height="190" rx="16"/></clipPath><g clip-path="url(#ts${k})">`;
  s += `<rect width="150" height="190" fill="#F4F6FA"/>`;
  s += `<rect x="12" y="16" width="70" height="8" rx="3" fill="#1D2330"/><circle cx="134" cy="20" r="6" fill="#3D7BFF"/>`;
  s += `<rect x="12" y="34" width="126" height="64" rx="10" fill="#7FB2FF"/><circle cx="110" cy="56" r="10" fill="#FFFFFF" fill-opacity=".8"/><path d="M12 98l34-30 22 18 18-12 52 24z" fill="#3D7BFF"/>`;
  for (let i = 0; i < 4; i++) s += `<rect x="12" y="${110 + i * 18}" width="${[116, 96, 108, 80][i]}" height="7" rx="3" fill="#A9B1C2"/>`;
  s += `<rect width="150" height="190" fill="${kelvin(k)}" fill-opacity=".55"/><rect width="150" height="190" fill="#000" fill-opacity="${((6500 - k) / 6500 * 0.25).toFixed(2)}"/></g>`;
  s += `<rect x=".5" y=".5" width="149" height="189" rx="16" fill="none" stroke="#2a2418"/>`;
  return `<g transform="translate(${x} ${y})">${s}</g>`;
}

// ---------- HERO ----------
function hero(t) {
  const W = 880, H = 480;
  let b = `<g>${logo(40, 36, 36)}<text x="88" y="62" font-size="22" font-weight="800" letter-spacing="-.3">${T('Ember')}</text></g>`;
  b += `<g>${chip(178, 39, 'v' + VERSION, { upper: false }).s}</g>`;
  const ts = fitSize(t.hero.title, 530, 44, 800);
  b += `<g>${lines(40, 146, t.hero.title.map(one), { size: ts, weight: 800, lh: 50, spacing: '-1' })}</g>`;
  const ss = fitSize(t.hero.sub, 530, 16, 500);
  b += `<g>${lines(40, 236, t.hero.sub.map(one), { size: ss, weight: 500, lh: 24, fill: P.muted })}</g>`;
  t.hero.cards.forEach(([a, d], i) => {
    const x = 40 + (i % 2) * 262, y = 290 + Math.floor(i / 2) * 86;
    b += `<g>${card(x, y, 250, 74)}
<circle cx="${x + 22}" cy="${y + 27}" r="4" fill="${AMBER}"/>
${fitText(x + 34, y + 32, a, 200, { size: 16, weight: 700 })}
${fitText(x + 20, y + 54, d, 214, { size: 13, weight: 500, fill: P.muted })}</g>`;
  });
  b += phone(595, 38, t);
  return svg(W, H, t.hero.alt, b, SCREEN_DEFS);
}

// ---------- ВОЗМОЖНОСТИ ----------
function features(t) {
  const W = 880;
  const head = sectionHead(t.features.chip, t.features.title);
  let b = head.s;
  const items = t.features.items.map(([title, ls]) => [title, ls.flatMap((l) => wrap(l, 344, 15, 500))]);
  const ch = 92 + Math.max(...items.map(([, ls]) => ls.length)) * 22 - 4;
  items.forEach(([title, ls], i) => {
    const x = 40 + (i % 2) * 408, y = head.y + Math.floor(i / 2) * (ch + 16);
    b += `<g>${card(x, y, 392, ch, 20)}
${fitText(x + 24, y + 48, title, 344, { size: 20, weight: 700 })}
${lines(x + 24, y + 92, ls, { size: 15, weight: 500, lh: 22, fill: P.muted })}</g>`;
  });
  return svg(W, head.y + 2 * ch + 16 + 36, t.features.alt, b);
}

// ---------- ПРЕСЕТЫ ----------
function presets(t) {
  const W = 880;
  const head = sectionHead(t.presets.chip, t.presets.title);
  let b = head.s;
  const ks = [3400, 2700, 1900, 1200];
  t.presets.cols.forEach(([name, d], i) => {
    const x = 40 + i * 204, y = head.y;
    b += `<g>${card(x, y, 188, 290, 20)}${tinted(x + 19, y + 20, ks[i])}
<circle cx="${x + 27}" cy="${y + 242}" r="7" fill="${kelvin(ks[i])}"/>
${fitText(x + 42, y + 248, name, 130, { size: 19, weight: 800 })}
${fitText(x + 20, y + 272, d, 150, { size: 13, weight: 600, fill: P.muted, mono: true })}</g>`;
  });
  return svg(W, head.y + 290 + 36, t.presets.alt, b);
}

// ---------- В ЦИФРАХ ----------
function numbers(t) {
  const W = 880;
  const head = sectionHead(t.numbers.chip, t.numbers.title);
  let b = head.s;
  const w = (800 - 4 * 12) / 5;
  t.numbers.items.forEach(([n, unit, ls], i) => {
    const x = 40 + i * (w + 12), y = head.y;
    b += `<g>${card(x, y, w, 124, 18)}
<text class="m" x="${x + 20}" y="${y + 56}" font-size="38" font-weight="700" letter-spacing="-1" fill="url(#tg)">${T(n)}<tspan font-size="16" fill="${P.muted}">${T(unit)}</tspan></text>
${ls.map((l, k) => fitText(x + 20, y + 86 + k * 18, l, w - 30, { size: 13, weight: 500, fill: P.muted })).join('\n')}</g>`;
  });
  return svg(W, head.y + 124 + 34, t.numbers.alt, b);
}

function banner(data) {
  const body = `<rect x="0" y="0" width="6" height="114" rx="3" fill="#AD6B40"/>
${fitText(40, 51, data.title, 800, { size: 31, weight: 800 })}
${fitText(41, 83, data.subtitle, 796, { size: 16, fill: P.muted })}`;
  return svg(880, 114, `${data.title}. ${data.subtitle}`, body);
}

// ---------- сборка ----------
const langs = {};
for (const [lang, t] of Object.entries(STR)) {
  langs[lang] = () => ({
    [`hero-${lang}.svg`]: hero(t),
    [`btn-android-${lang}.svg`]: K.button(t.buttons.android, ICONS.android, true),
    [`btn-new-${lang}.svg`]: K.button(t.buttons.whatsNew, ICONS.spark, false),
    [`features-${lang}.svg`]: features(t),
    [`presets-${lang}.svg`]: presets(t),
    [`numbers-${lang}.svg`]: numbers(t),
    [`about-${lang}.svg`]: banner(t.about),
    [`roadmap-${lang}.svg`]: banner(t.roadmap),
  });
}
await K.build(OUT, langs, 'vEmber.');
