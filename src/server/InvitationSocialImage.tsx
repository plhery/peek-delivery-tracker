import { ImageResponse } from 'next/og';
import { readFileSync } from 'node:fs';
import { join } from 'node:path';
import { invitationInitial } from '../lib/invitationInitial';
import { invitationInitialPath } from '../lib/invitationInitialPath';
import type { Locale } from '../lib/locale';
import { GEIST, NOTO_SANS_SC, pictureFont, PICTURE_FONTS, writable } from './pictureFont';
import { wordsIn } from './sitePreview';

/** The serif the sender's name is set in. The renderer's own sans is loaded beside it, or it would be lost. */
const GELASIO = pictureFont('Gelasio', readFileSync(join(process.cwd(), 'public/fonts/gelasio/Gelasio-SemiBoldItalic.ttf')));
const FONTS = [...PICTURE_FONTS, { name: GELASIO.name, data: GELASIO.data, weight: 600 as const, style: 'italic' as const }];

const graphemes = new Intl.Segmenter(undefined, { granularity: 'grapheme' });

/**
 * A sender's name as the picture writes it. The picture is drawn without the
 * web, so an emoji or a symbol its faces lack is left out. A name with a
 * letter they lack is not written at all, rather than misspelt.
 */
function writtenName(nickname: string | null): string | null {
  let name = '';
  for (const { segment } of graphemes.segment(nickname ?? '')) {
    // What the serif lacks is written in the sans, as the renderer does it.
    if (writable(segment, GELASIO, GEIST, NOTO_SANS_SC) !== null) name += segment;
    else if (/^\p{L}/u.test(segment)) return null;
    else name += ' ';
  }
  return writable(name, GELASIO, GEIST, NOTO_SANS_SC) || null;
}

/** A still of the welcome parcel: the same kraft paper, face and seal. */
export function invitationSocialImage(nickname: string | null, locale: Locale = 'en'): ImageResponse {
  const t = wordsIn(locale);
  const name = writtenName(nickname);
  // The sentence around the name, as the page splits it; without a name, the invitation alone.
  const [before = '', after = ''] = t('friends.invitationTitle').split('{{name}}').map((part) => part.trim());
  const sentence = name
    ? [...before.split(' ').filter(Boolean).map((word) => ({ word, named: false })), { word: name, named: true }, ...after.split(' ').filter(Boolean).map((word) => ({ word, named: false }))]
    : t('friends.invitationGeneric').split(' ').map((word) => ({ word, named: false }));
  const length = sentence.reduce((sum, { word }) => sum + [...word].length + 1, 0);
  const sentenceSize = length > 52 ? 40 : length > 40 ? 46 : 54;
  const initial = invitationInitial(name);
  const initialPath = invitationInitialPath(initial);
  // An initial with no drawing of its own is set in type, when the faces have it.
  const letter = initialPath ? null : writable(initial, GEIST, GELASIO, NOTO_SANS_SC) || null;
  // Project the seal's SVG coordinates into the parcel's picture: the frame from y 85 down to its foot at 310, where the shadow fades.
  const parcelScale = 300 / 215;
  const parcelHeight = 225 * parcelScale;
  const sealSize = 28 * parcelScale;
  const sealLeft = (183 - 25) * (360 / 250) - sealSize / 2;
  const sealTop = (234 - 85) * parcelScale - sealSize / 2;
  return new ImageResponse(
    <div style={{ width: '100%', height: '100%', display: 'flex', flexDirection: 'column', alignItems: 'center', background: '#F4F5F1', color: '#26372E', padding: '38px 60px', fontFamily: 'Geist' }}>
      <div style={{ display: 'flex', alignItems: 'center', fontSize: 20, color: '#637568', letterSpacing: 1 }}>PEEK</div>
      <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'center', width: 1020, height: 160, marginTop: 4, textAlign: 'center', fontWeight: 700, lineHeight: 1.15 }}>
        <div style={{ display: 'flex', flexWrap: 'wrap', alignItems: 'baseline', justifyContent: 'center', columnGap: sentenceSize * 0.26, fontSize: sentenceSize }}>
          {sentence.map(({ word, named }, index) => <span key={index} style={named ? { fontFamily: 'Gelasio', fontStyle: 'italic', fontWeight: 600 } : undefined}>{word}</span>)}
        </div>
      </div>
      <div style={{ display: 'flex', position: 'relative', width: 360, height: parcelHeight }}>
      <svg width="360" height={parcelHeight} viewBox="25 85 250 225" fill="none">
        <defs>
          <linearGradient id="front" x1="0" y1="0" x2="1" y2="1"><stop stopColor="#ddbc95" /><stop offset="1" stopColor="#c49a6e" /></linearGradient>
          <linearGradient id="side" x1="0" y1="0" x2="1" y2="1"><stop stopColor="#bb946a" /><stop offset="1" stopColor="#a77e55" /></linearGradient>
          <linearGradient id="lid" x1="0" y1="0" x2="1" y2="1"><stop stopColor="#e4c9a6" /><stop offset="1" stopColor="#c9a57e" /></linearGradient>
          <linearGradient id="rear" x1="0" y1="0" x2="1" y2="1"><stop stopColor="#d8b995" /><stop offset="1" stopColor="#be986f" /></linearGradient>
          <linearGradient id="tape" x1="0" y1="0" x2="1" y2="1"><stop stopColor="#f2e5d0" /><stop offset="1" stopColor="#ddc8a9" /></linearGradient>
          <pattern id="fibres" width="11" height="9" patternUnits="userSpaceOnUse"><path d="M1 3h1.4m4 4h1M8 1h.7" stroke="#715232" strokeWidth=".35" opacity=".23" /></pattern>
          <filter id="shadow" filterUnits="userSpaceOnUse" x="0" y="240" width="300" height="92"><feGaussianBlur stdDeviation="9" /></filter>
        </defs>
        <ellipse cx="151" cy="286" rx="90" ry="10" fill="#000" opacity=".08" filter="url(#shadow)" />
        {/* The lid's two closed flaps, as the page folds them. */}
        <g transform="translate(150 95) rotate(26.323298)">
          <polygon points="0,0 105.990566,0 74.058478,42.574544 -32.153805,42.126391" fill="url(#rear)" stroke="#987450" strokeOpacity=".24" strokeWidth=".7" />
        </g>
        <path d="m55 142 95 48v87l-95-48v-87Z" fill="url(#front)" />
        <path d="m150 190 95-48v87l-95 48v-87Z" fill="url(#side)" />
        <path d="m55 142 95 48v87l-95-48v-87Z" fill="#FFF4D6" opacity=".07" />
        <path d="M56 144v84l93 47m2 0 92-46v-84" stroke="#987450" strokeOpacity=".25" strokeWidth=".8" />
        <path d="m55 142 95 48 95-48m-95 48v87" stroke="#FFF2CF" strokeWidth="1" opacity=".4" />
        <path d="m55 142 95 48 95-48v87l-95 48-95-48Z" fill="url(#fibres)" />
        <g transform="matrix(1 0.505263 0 1 55 142)">
          {[32.5, 63.5].map((x) => <g key={x}>
            <ellipse cx={x} cy="36" rx="10" ry="11.5" fill="#FFFDF6" />
            <circle cx={x + 3.5} cy="34.6" r="5.4" fill="#20251E" />
            <circle cx={x + 1.7} cy="32.6" r="1.6" fill="#FFFFFF" />
            <ellipse cx={x - 1} cy="51" rx="6.5" ry="3.2" fill="#E9958F" opacity=".55" />
          </g>)}
          <path d="M42 51Q48 58 54 51" stroke="#20251E" strokeWidth="2.4" strokeLinecap="round" />
        </g>
        <g transform="translate(203.2 207) rotate(-27)"><path d="M10 22V4m-5 5 5-5 5 5" stroke="#735C43" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" /></g>
        <g transform="translate(183 234) rotate(-27)">
          <circle r="14" fill="#DECCE2" />
          <circle r="11.5" stroke="#FFF6FF" strokeWidth="1.2" opacity=".48" />
          {initialPath && <path d={initialPath} stroke="#7C6787" strokeWidth="1.5" strokeLinejoin="round" />}
          {!initialPath && !letter && <path d="M0-7V7m-6-10 12 6M-6 3 6-3" stroke="#7C6787" strokeWidth="2" strokeLinecap="round" />}
        </g>
        <g transform="translate(55 142) rotate(26.805788)">
          <polygon points="0,0 106.437775,0 138.010213,-42.841933 31.797921,-42.395663" fill="url(#lid)" stroke="#987450" strokeOpacity=".24" strokeWidth=".7" />
        </g>
        <path d="m96 122 13-7 95 48-13 7-95-48Z" fill="url(#tape)" />
        <path d="m103 119 94 47" stroke="#AF9474" strokeOpacity=".6" strokeWidth="1" strokeDasharray="3 3" />
      </svg>
      {letter && <div style={{ display: 'flex', position: 'absolute', left: sealLeft, top: sealTop, width: sealSize, height: sealSize, alignItems: 'center', justifyContent: 'center', fontSize: 18 * parcelScale, color: '#7C6787', transform: 'rotate(-27deg)', lineHeight: 1 }}>{letter}</div>}
      </div>
      <div style={{ display: 'flex', alignItems: 'center', marginTop: 16, fontSize: 28, color: '#526E5B' }}>{t('preview.invitation.open')}</div>
    </div>,
    {
      width: 1200, height: 630,
      fonts: FONTS,
      headers: { 'Cache-Control': 'private, no-store', 'X-Robots-Tag': 'noindex, nofollow' },
    },
  );
}
