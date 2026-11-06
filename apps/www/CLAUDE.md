# CLAUDE.md — `apps/www` (marketing site)

Next.js 16 (App Router, Turbopack) + React 19 + Tailwind 3 + TypeScript.
Product/marketing surface for OEMs and designers — **not** the developer docs
(that is `website/`, Docusaurus). Deployed on Vercel with this folder as the
Root Directory.

The craft brief is authoritative and imported below. Read its anti-vibecode
blacklist and merge checklist before any visual or copy change.

@DESIGN.md

## Commands

Run from `apps/www/` (Node 20+):

```bash
npm install
npm run dev     # http://localhost:3000
npm run lint    # eslint, zero warnings allowed
npm run build   # must pass before finishing
```

## Layout

| Path | What |
| --- | --- |
| `src/app/` | Routes: `/`, `/foundations`, `/use-cases`, `/components`, `/compliance`, `/develop` |
| `src/app/globals.css` | Scheme tokens (`data-scheme` day/night), brand colors |
| `src/styles/cabin.tokens.css` | **Generated** — never hand-edit; change `tokens/cabin.tokens.json` and run `python3 tools/generate_cabin_tokens.py` from repo root |
| `src/components/` | Site chrome (`site-header`, `instrument-frame`, `segmented-control`, `scheme-provider`) and HMI demos (`demo-*`, `cabin-hmi-preview`, `use-case-stages`) |
| `src/lib/` | `site.ts` (nav/meta), `tokens.ts`, `use-cases.ts`, `demo-signal.ts` |

## Rules for edits here

- No dependency on Android `cabin-*` modules; tokens arrive only as generated CSS.
- One day/night toggle only (site header). Verify both schemes after any visual change.
- Safety colors (warning / error / charging) keep locked contrast at night; never brand-remap them.
- No purple/indigo, glassmorphism, fake logos, vanity metrics, or buzzword copy (see blacklist above).
- Max one primary CTA per section. Real SVG glyphs only (`cabin-glyphs.tsx`), no emoji.
- Demos must not claim unshipped Cabin APIs — label planned work as planned.
- American English, calm and specific copy.
