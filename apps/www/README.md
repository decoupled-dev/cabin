# Cabin marketing site (`apps/www`)

User-facing marketing site for **Cabin** — Material Design 3–class polish for
the automobile cabin story. Dark-first, motion-aware, separate from the
developer docs shell.

| Surface | Path | Audience |
| --- | --- | --- |
| **Marketing (this app)** | `apps/www` | OEMs, Tier-1s, product / design discovery |
| **Developer docs** | repo `website/` → [GitHub Pages](https://decoupled-dev.github.io/cabin/) | Engineers integrating Cabin |

This package has **zero** dependency on Android `cabin-tokens` /
`cabin-compliance` Gradle modules. Visual tokens here are CSS inspired by
[`tokens/cabin.tokens.json` (generated CSS: `src/styles/cabin.tokens.css`)](../../tokens/cabin.tokens.json).

## Pages (v1)

- `/` — Home hero + pillars
- `/foundations` — Color / Type / Space / Motion (live day/night, codegen CSS)
- `/use-cases` — Parked / Moving / Charging full-bleed HMI stages
- `/components` — Bars / Climate / Media, kit inspector, composed screens
- `/compliance` — Safety-by-default story
- `/develop` — Get started → docs

## Local development

Requires Node 20+.

```bash
cd apps/www
npm install
npm run dev
```

Open [http://localhost:3000](http://localhost:3000).

```bash
npm run build   # production build
npm run start   # serve .next
npm run lint
```

## Vercel deploy

Point a Vercel project at **`apps/www`** as the Root Directory.

- Framework preset: Next.js
- Install: `npm install`
- Build: `npm run build`
- `vercel.json` is included for defaults

CI/CD wiring (project + domain) is owned by the platform teammate — this app
only needs a clean `npm run build`.

Suggested production hostname (optional): marketing on a custom domain;
keep docs on `decoupled-dev.github.io/cabin/`.

## Design notes

**Authoritative craft brief:** [DESIGN.md](./DESIGN.md) — read before changing
layout, color, motion, or copy (anti-vibecode).

- Night-leaning default with Day/Night segmented control in the header (Cabin day/night story)
- Semantic color roles aligned with Cabin (primary green, charging, climate)
- Typography: Syne (display) + Source Sans 3 (body)
- Hero visual: one instrument composition (status + content + system) with brand story and HMI tiles
- Motion: short Material-like brand/entrance and reveals — no ambient blob loops
- Palette: night cabin black + forest/teal accents — safety warning/error/charging stay crisp at night; domain accents off body copy; no purple brand wash

## Explicit non-goals

- Does **not** replace or modify `website/` Docusaurus docs
- No full interactive component catalog
- Not a contribution README — product-first IA
