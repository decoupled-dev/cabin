# Cabin marketing site — craft brief

**Audience:** humans and agents editing `apps/www`.  
**Status:** authoritative for visual and copy craft on this surface.  
**Live:** [cabin-www.vercel.app](https://cabin-www.vercel.app) (also planned as cabin.decoupled.dev).

This is **not** a license for generic AI landing pages. It locks craft intent
so the site stays Material-class and cabin-native — instrument chrome, not
SaaS marketing theater.

---

## Purpose

`apps/www` is Cabin’s **user-facing product presence**: Material Design 3–class
polish for OEMs, Tier-1s, and design/product discovery.

| This surface is | This surface is not |
| --- | --- |
| Marketing / product story | OSS docs chrome |
| Night-leaning cabin HMI aesthetic | Generic SaaS / “AI startup” landing |
| Calm, specific, automotive | Hype, buzzwords, vanity metrics |
| Inspired by Cabin tokens (CSS) | A consumer of Android `cabin-*` modules |

**Docs shell** lives in repo `website/` (Docusaurus → GitHub Pages). Do not
conflate the two. See [docs/website/marketing-craft.md](../../docs/website/marketing-craft.md).

Product pillars that every section must respect: **glanceable · quiet · safe by
default · one language two skins · thin by design**
([docs/product/pillars.md](../../docs/product/pillars.md)).

---

## Brand

- **Mood:** calm cabin luxury — forest night, instrument black, glanceable type,
  inevitable HMI chrome (status / content / system). Not loud vibecode wow.
- **Primary:** forest green family. Anchor container ≈ `#0B6E4F`; night primary
  reads as a brighter forest/teal on dark surfaces (see `globals.css`).
- **Never:** Material purple, indigo, or violet as brand or hero wash.
- **Typography:** expressive but disciplined — Syne (display) + Source Sans 3
  (body). Brand name is hero-level; headlines support, they do not overpower
  “Cabin.”
- **Hero visual:** one **instrument composition** — status / content / system
  wrapping brand story and glanceable HMI tiles. Not a separate marketing column
  beside a nested mockup; not stock photography of cars or dashboards.
- **Site chrome:** header is a status analog (forest rail, glance nav, Night/Day
  segmented control, clock). Footer is quiet; galleries stay a strip.
- **Day/Night:** toggle must match token intent (day = glare-legible light cabin;
  night = deep instrument black). Same structure; different scheme tokens.
  One scheme control only, in the site header.
- **Safety colors:** warning, error, and charging stay crisp at night. Do not
  soft-wash them into the forest primary. Lock contrast when tweaking night.
- **Domain accents** (charging / climate / media): accents only — never body
  copy color, never competing hero washes.

Reference implementation: `src/app/globals.css`, `src/components/cabin-hmi-preview.tsx`,
`src/components/instrument-frame.tsx`, `src/components/segmented-control.tsx`,
`tailwind.config.ts`.

---

## Anti-vibecode blacklist

Do **not** introduce these patterns. If a change drifts toward any of them,
reject it before merge.

- Purple / indigo gradients, violet glows, or “AI product” purple-on-white
- Blob meshes, multi-layer glassmorphism stacks, frosted-card grids as layout
- Fake logos, fake avatars, fake customer marks, invented “trusted by” rows
- Vanity metrics (“10× faster,” “99.9%,” “used by N OEMs”) without sourced facts
- Buzzword hero copy: *reimagine*, *seamless*, *next-gen*, *revolutionize*,
  *unlock*, *supercharge*, *delight*, *magic*
- Decorative Lottie / confetti / bounce spam unrelated to state
- Uneven “random” spacing, asymmetric gaps that look accidental
- Too many competing CTAs in one section (or three equal primary buttons)
- Stock Unsplash / Getty car wallpaper, cockpit lifestyle shots, or inset
  rounded media cards as the hero
- Floating promo badges, sticker chips, or callout overlays on the HMI preview
- Dark-mode purple neon, multi-layer drop shadows, pill-cluster icon rows
- Kitchen-sink feature grids that read as a dashboard in the first viewport

---

## Required craft

### Layout and hierarchy

- First viewport reads as **one composition**: brand, one headline, one short
  supporting sentence, one CTA group, one dominant HMI visual — all inside
  status / content / system instrument chrome.
- Restrained type hierarchy: display → headline → title → body → status/label.
  Prefer existing Tailwind type tokens over one-off sizes.
- Whitespace with intent — quiet surfaces, clear section jobs, no filler panels.
- **Max one primary CTA per section.** Secondary links are allowed; they must
  not visually equal the primary.

### Color and schemes

- Keep forest primary; no purple brand remap.
- Day/night schemes stay token-driven (`data-scheme`). Test both before merge.
- **One scheme toggle only** (site header). Do not duplicate Night/Day on gallery
  sticky bars or token explorers.
- Warning / error / charging: verify night contrast stays locked and readable.
- Domain accents stay off body copy and off large text blocks.

### Gallery routes (`/foundations`, `/use-cases`, `/components`)

- Dense two-up / full-bleed demo stages — no sparse right-column void.
- Components: Bars / Climate / Media stay the live Alpha demos; Kit is one
  inspector (not an inventory grid); Screens compose those pieces.
- Real SVG glyphs and art wells — no dash icon stubs, no hyphen artwork, no
  emoji transport.
- Bars render inside instrument bezel chrome (status · content · system).
- Transport uses cabin control surfaces; mediaAccent / climate accents are
  mark-only.
- Quiet gallery footer — strip marketing copy from Foundations / Use cases /
  Components.
- **Use cases** are three live full-bleed HMI stages (Parked / Moving /
  Charging). Day/night via the single site header scheme toggle only.
- Charging control rows use flex — never leave empty grid cells.
- Long token CSS var names in Foundations wrap (`break-all`); do not truncate
  mid-name.

### HMI preview

- Prefer updating the existing cabin HMI preview over replacing it with photos
  or abstract art.
- Preserve glanceable chrome roles (status, content, system). Decorative noise
  inside the preview is a regression.

### Motion

- Short and purposeful: entrance, reveal, subtle ambient atmosphere.
- Prefer Material-like curves already in the app (`cubic-bezier(0.2, 0, 0, 1)`,
  `cabin` / `precise` easings) over bounce, elastic, springy novelty.
- Motion confirms hierarchy or state — it does not entertain.
- Cap ambient loops; avoid stacking many infinite animations in one viewport.

### Copy tone

- Calm, specific, automotive.
- Say what Cabin is and who it is for. Name Compose, Views, compliance, tokens
  when relevant.
- No hype adjectives. Prefer product stance language: calm, legible, inevitable
  ([docs/product/stance.md](../../docs/product/stance.md)).
- American English. Production-minded, not launch-trailer voice.

### Dependencies

- Do **not** add Android `cabin-tokens` / `cabin-compliance` (or other `cabin-*`)
  as runtime deps of this Next app. Cabin color roles come from generated
  `src/styles/cabin.tokens.css` (from `tokens/cabin.tokens.json`).
  Presentation-only vars may use `color-mix` — no hand hex for cabin roles.
- Do not fold samples, catalog, or docs-site packages into marketing deps.

---

## Section job model

Each section: **one purpose**, **one headline**, usually **one short supporting
sentence**. If a section needs cards to make sense as interaction, keep them
minimal; default is no decorative cards.

Good section jobs: pitch, pillars, foundations gallery (color / type / space /
motion), use-case stages (Parked / Moving / Charging), components gallery
(Bars / Climate / Media, kit inspector, composed screens), compliance story,
develop / get started. Bad: “stats strip + logos + testimonials + feature bingo”
in one fold.

**Foundations + Use cases + Components:** calm cabin chrome galleries — quiet
page titles, sticky IA, live demos from generated CSS vars. One scheme toggle
in the site header. No marketing hero noise on those routes.

**Components kit / screens:** shared Experimental scaffold chrome (family mark,
focus, Restriction Engine) and one dashboard assembled from bars + climate +
media. Do not add a 100-row inventory grid. The Android sample is
`samples/kitchen-sink` — mention it; do not embed the catalog.

| Use case | Stage job |
| --- | --- |
| **Parked** | Climate full control (temp / fan / seats); forest night bezel; climate accents mark-only |
| **Moving** | Media glance; transport Allow; source / complex RE-quiet; no distraction chrome |
| **Charging** | Honest SOC / range; locked charging color; empty / stale / fault never invent values |

Domain tiles mirror Android specs (honest Signals, RE-quiet). Do not
kitchen-sink the rest of the site.

---

## Checklist before merge

Editors (human or agent) must pass all of the following:

- [ ] First viewport still reads as one composition (brand + one story + HMI)
- [ ] No purple / indigo brand wash, blob mesh, or glassmorphism stack added
- [ ] Hero remains cabin HMI fidelity — not stock car wallpaper
- [ ] Day and night schemes both checked; safety (warning/error/charging) contrast locked at night
- [ ] Domain accents not used as body copy color
- [ ] At most one primary CTA per section; CTA labels are concrete
- [ ] Type uses the established hierarchy; no random spacing
- [ ] Motion is short and Material-like — no bounce/elastic novelty or Lottie spam
- [ ] Copy is calm and specific — no buzzword hero, no vanity metrics
- [ ] No new `cabin-*` Android library dependencies
- [ ] Gallery IA is Foundations → Use cases → Components; quiet footers on all
  three
- [ ] Use-case stages show RE fail-closed (Moving gated controls) and honest
  Charging empty / stale / fault
- [ ] Docs shell (`website/`) and Vercel/DNS config left alone unless explicitly requested
- [ ] Change still passes product pillars (glanceable, quiet, safe by default, two skins, thin)

---

## Related

- App README: [README.md](./README.md)
- Docs pointer: [docs/website/marketing-craft.md](../../docs/website/marketing-craft.md)
- Site plan: [docs/website/site-plan.md](../../docs/website/site-plan.md)
- Pillars: [docs/product/pillars.md](../../docs/product/pillars.md)
- Stance: [docs/product/stance.md](../../docs/product/stance.md)
- Tokens source of truth (Android / design language): [`tokens/cabin.tokens.json`](../../tokens/cabin.tokens.json)
