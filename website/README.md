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

Typical options:

| Target | Notes |
| --- | --- |
| **GitHub Pages** | `organizationName` / `projectName` are set in `docusaurus.config.ts`. From `website/`: `GIT_USER=<user> npm run deploy` (or wire `peaceiris/actions-gh-pages` in CI). `baseUrl` is `/cabin/`. |
| **Custom host** | Serve `website/build/` as static files. Change `url` / `baseUrl` if the site is not under `/cabin/`. |

CI publish is not required for this shell PR; keep Android library workflows
independent of website install/build.

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
