// Генерирует SVG-плашки README в стиле Okto Calc: чёрный фон, свечения и оранжевый акцент Ember.
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

const AMBER = '#FFB23E', ORANGE = '#FF6A1F'; // те же цвета, что в MainActivity.kt
const K = createKit({
  golos: path.join(HERE, '.font/golos.ttf'),
  mono: path.join(HERE, '.font/jbmono.ttf'),
  accent: { hi: AMBER, grad: ['#FFC45A', ORANGE], on: '#2A1000', glow: ORANGE, glow2: AMBER, glowOpacity: 0.26, btn: ['#FF9A3C', ORANGE] },
});
const { P, T, card, chip, sectionHead, badge, lines, wrap, one, fitSize, fitText, delay, svg } = K;

// Логотип — тот же рисунок, что у иконки (ic_launcher_foreground.xml): раскалённое ядро и свечение
const logo = (x, y, s) => `<g transform="translate(${x} ${y}) scale(${s / 108})">
<rect width="108" height="108" rx="26" fill="#000" stroke="#2a2018" stroke-width="2"/>
<circle cx="54" cy="54" r="46" fill="url(#lgw)"/><circle cx="54" cy="54" r="21" fill="url(#lgc)"/></g>`;
const LOGO_DEFS = `<radialGradient id="lgw" cx=".5" cy=".5" r=".5"><stop offset="0" stop-color="${ORANGE}" stop-opacity=".75"/><stop offset="1" stop-color="${ORANGE}" stop-opacity="0"/></radialGradient>
<radialGradient id="lgc" cx=".5" cy=".42" r=".6"><stop offset="0" stop-color="#FFF2D6"/><stop offset=".35" stop-color="${AMBER}"/><stop offset="1" stop-color="${ORANGE}"/></radialGradient>
<linearGradient id="cf" x1="0" y1="0" x2="0" y2="1"><stop offset="0" stop-color="${ORANGE}" stop-opacity=".55"/><stop offset="1" stop-color="${ORANGE}" stop-opacity="0"/></linearGradient>`;

// Та же кривая, что Schedule.level(): плавно растёт после заката, гаснет после рассвета
const DAY = 1440, START = 19 * 60 + 12, END = 6 * 60 + 48, FADE = 30;
const mod = (a) => ((a % DAY) + DAY) % DAY;
function level(m) {
  const len = mod(END - START), pos = mod(m - START);
  if (pos < len) return Math.min(1, pos / FADE);
  if (pos < len + FADE) return 1 - (pos - len) / FADE;
  return 0;
}

// Кельвины → RGB (Tanner Helland), как Tint.rgb()
function kelvin(k) {
  const t = k / 100;
  const r = t <= 66 ? 255 : 329.698727446 * (t - 60) ** -0.1332047592;
  const g = t <= 66 ? 99.4708025861 * Math.log(t) - 161.1195681661 : 288.1221695283 * (t - 60) ** -0.0755148492;
  const b = t >= 66 ? 255 : t <= 19 ? 0 : 138.5177312231 * Math.log(t - 10) - 305.0447927307;
  return '#' + [r, g, b].map((v) => Math.round(Math.min(255, Math.max(0, v))).toString(16).padStart(2, '0')).join('');
}

const toggle = (x, y) => `<rect x="${x}" y="${y}" width="22" height="12" rx="6" fill="${AMBER}"/><circle cx="${x + 16}" cy="${y + 6}" r="4" fill="#000"/>`;

// ---------- телефон: экран «График суток» ----------
function phone(x, y, t, k = 1) {
  const sc = t.hero.screen;
  let s = `<rect width="150" height="228" rx="20" fill="#000" stroke="#2a2418"/>`;
  s += fitText(12, 34, sc.title, 126, { size: 14, weight: 800, fill: '#F5EFE8' });
  // график суток
  const cx = 10, cw = 130, top = 58, bot = 112;
  const px = (m) => (cx + cw * m / DAY).toFixed(1), py = (l) => (bot - (bot - top) * l).toFixed(1);
  let d = '';
  for (let m = 0; m <= DAY; m += 10) d += `${m ? 'L' : 'M'}${px(m)} ${py(level(m % DAY))}`;
  s += `<path d="${d}L${cx + cw} 124L${cx} 124Z" fill="url(#cf)"/>`;
  s += `<line x1="${cx}" y1="${bot}" x2="${cx + cw}" y2="${bot}" stroke="#241E18"/>`;
  s += `<path d="${d}" fill="none" stroke="${AMBER}" stroke-width="1.6" stroke-linejoin="round"/>`;
  const now = 21 * 60 + 40;
  s += `<line x1="${px(now)}" y1="52" x2="${px(now)}" y2="124" stroke="#fff" stroke-opacity=".5" stroke-dasharray="2 3"/>`;
  s += `<circle cx="${px(now)}" cy="${py(level(now))}" r="3" fill="#fff"/><circle class="pulse" cx="${px(now)}" cy="${py(level(now))}" r="7" fill="#fff"/>`;
  ['00', '06', '12', '18', '24'].forEach((h, i) => { s += `<text class="m" x="${cx + i * cw / 4}" y="132" font-size="5.5" text-anchor="${i === 0 ? 'start' : i === 4 ? 'end' : 'middle'}" fill="#8A8178">${T(h)}</text>`; });
  // пресеты
  sc.presets.forEach((p, i) => {
    const on = i === 1, bx = 10 + i * 44.5;
    s += `<rect x="${bx}" y="140" width="41" height="17" rx="5" fill="${on ? AMBER : 'none'}" fill-opacity="${on ? '.14' : '1'}" stroke="${on ? '#6A4A1C' : '#241E18'}"/>`;
    s += fitText(bx + 20.5, 151, p, 36, { size: 6.5, weight: 500, fill: on ? AMBER : '#8A8178', anchor: 'middle' });
  });
  // строки настроек
  sc.rows.forEach((r, i) => {
    const ry = 176 + i * 24;
    s += fitText(12, ry, r, 100, { size: 8, weight: 500, fill: '#F5EFE8' });
    s += toggle(116, ry - 9);
    if (i === 0) s += `<line x1="10" y1="${ry + 9}" x2="140" y2="${ry + 9}" stroke="#241E18"/>`;
  });
  return `<g transform="translate(${x} ${y}) scale(${k})">${s}</g>`;
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
  let b = `<g class="r">${logo(40, 36, 36)}<text x="88" y="62" font-size="22" font-weight="800" letter-spacing="-.3">${T('Ember')}</text></g>`;
  b += `<g class="r" ${delay(1)}>${chip(178, 39, 'v' + VERSION).s}</g>`;
  const ts = fitSize(t.hero.title, 530, 44, 800);
  b += `<g class="r" ${delay(2)}>${lines(40, 146, t.hero.title.map(one), { size: ts, weight: 800, lh: 50, spacing: '-1' })}</g>`;
  const ss = fitSize(t.hero.sub, 530, 16, 500);
  b += `<g class="r" ${delay(3)}>${lines(40, 236, t.hero.sub.map(one), { size: ss, weight: 500, lh: 24, fill: P.muted })}</g>`;
  t.hero.cards.forEach(([a, d], i) => {
    const x = 40 + (i % 2) * 262, y = 290 + Math.floor(i / 2) * 86;
    b += `<g class="r" ${delay(4 + i)}>${card(x, y, 250, 74)}
<circle cx="${x + 22}" cy="${y + 27}" r="4" fill="${AMBER}"/>
${fitText(x + 34, y + 32, a, 200, { size: 16, weight: 700 })}
${fitText(x + 20, y + 54, d, 214, { size: 13, weight: 500, fill: P.muted })}</g>`;
  });
  b += `<g class="r" ${delay(3)}><ellipse cx="722" cy="244" rx="160" ry="200" fill="url(#g1)"/>${phone(594, 46, t, 1.7)}</g>`;
  return svg(W, H, t.hero.alt, b, LOGO_DEFS);
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
    b += `<g class="r" ${delay(2 + i)}>${card(x, y, 392, ch, 20)}${badge(x + 40, y + 42, i + 1)}
${fitText(x + 68, y + 48, title, 300, { size: 20, weight: 700 })}
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
    b += `<g class="r" ${delay(2 + i)}>${card(x, y, 188, 290, 20)}${tinted(x + 19, y + 20, ks[i])}
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
    b += `<g class="r" ${delay(2 + i)}>${card(x, y, w, 124, 18)}
<text class="m" x="${x + 20}" y="${y + 56}" font-size="38" font-weight="700" letter-spacing="-1" fill="url(#tg)">${T(n)}<tspan font-size="16" fill="${P.muted}">${T(unit)}</tspan></text>
${ls.map((l, k) => fitText(x + 20, y + 86 + k * 18, l, w - 30, { size: 13, weight: 500, fill: P.muted })).join('\n')}</g>`;
  });
  return svg(W, head.y + 124 + 34, t.numbers.alt, b);
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
  });
}
await K.build(OUT, langs, 'vEmber.');
