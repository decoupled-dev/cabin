# Cabin website

Public documentation site for **Cabin** (Docusaurus).

This package is a **thin docs shell** in a Google-style monorepo layout: it
lives under `website/` with its own Node tooling and **must not** depend on
Android library modules (`cabin-tokens`, `cabin-compliance`, Compose/Views
kits, or Gradle artifacts).

Repository markdown under [`docs/`](../docs/) remains the content source of
truth. Docusaurus reads that tree directly (`docs.path = ../docs`).

## Requirements

- Node.js 18+ (20+ recommended)
- npm (lockfile committed for reproducible installs)

## Install

```bash
cd website
npm install
```

## Local development

```bash
npm start
```

Opens the site at [http://localhost:3000/cabin/](http://localhost:3000/cabin/)
(`baseUrl` is `/cabin/` for GitHub Pages readiness).

## Production build

```bash
npm run build
```

Static output is written to `website/build/`. Preview with:

```bash
npm run serve
```

## Deploy

GitHub Pages publish is handled by
[`.github/workflows/website.yml`](../.github/workflows/website.yml)
(isolated from Android `unit-tests.yml`). It builds from `website/` with
`npm ci` / `npm run build` and deploys via `actions/upload-pages-artifact` +
`actions/deploy-pages`.

| Target | Notes |
| --- | --- |
| **GitHub Pages** | Live URL: [https://decoupled-dev.github.io/cabin/](https://decoupled-dev.github.io/cabin/). One-time repo setting: **Settings → Pages → Source = GitHub Actions**. |
| **Custom host** | Serve `website/build/` as static files. Change `url` / `baseUrl` if the site is not under `/cabin/`. |

Triggers: push to `main` touching `website/**` or `docs/**`, plus
`workflow_dispatch`. Android library workflows stay independent.

## Information architecture

Aligned with [`docs/website/site-plan.md`](../docs/website/site-plan.md), shipped
thin:

| Nav | Source |
| --- | --- |
| Home | `src/pages/index.tsx` (vision / README pitch) |
| Foundations | `docs/design-language/*`, product pillars |
| Styles | tokens + token schema |
| Components / Patterns | **Planned stubs** under `shell-docs/` |
| Compliance | `docs/compliance/*` |
| Develop | platforms + adoption |
| Versions | blog stub + roadmap links |

Interactive catalog and dual-stack demos are explicitly marked **not
implemented** on the Components / Patterns stubs.

## Package boundary

- ✅ Own `package.json`, Docusaurus, React (docs UI only)
- ✅ May read / ingest `docs/` (and link to GitHub for assets outside docs)
- ❌ No dependency on `cabin-*` Android modules, AAR/Maven coords at runtime, or Gradle
- ❌ Android modules must not depend on this website package

## Related

- [Site plan](../docs/website/site-plan.md)
- [Vision](../docs/vision.md)
- [Roadmap](../docs/roadmap.md)
- [Contributing](../docs/contributing.md)
