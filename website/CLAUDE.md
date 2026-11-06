# CLAUDE.md — `website/` (developer docs site)

Docusaurus 3 + React 19 + TypeScript. Thin static docs shell published to
GitHub Pages at https://decoupled-dev.github.io/cabin/ (`baseUrl: /cabin/`).
Not the marketing site — that is `apps/www` (Next.js, Vercel).

## Content sources

| Source | Route | Notes |
| --- | --- | --- |
| `../docs/` (repo root) | `/cabin/docs/...` | **Content source of truth.** Edit docs there, not copies here. Sidebars in `sidebars.ts` |
| `shell-docs/` | `/cabin/guide/...` | Site-only pages (get-started, components, patterns stubs). Sidebar in `sidebarsShell.ts` |
| `blog/` | `/cabin/blog` | "Versions & notes"; authors/tags in `authors.yml` / `tags.yml` |
| `src/pages/index.tsx` | `/cabin/` | Home page |
| `src/css/` | — | Theme overrides |

Info architecture plan: `docs/website/site-plan.md`.

## Commands

Run from `website/` (Node 20 matches CI):

```bash
npm ci
npm start          # http://localhost:3000/cabin/
npm run build      # static output in build/ — must pass before finishing
npm run typecheck
npm run serve      # preview the build
```

Deploy is automatic: `.github/workflows/website.yml` builds and publishes on
push to `main` touching `website/**` or `docs/**`. Do not run `npm run deploy`.

## Rules for edits here

- No dependency on Android `cabin-*` modules, Gradle, or Maven artifacts — and
  nothing in the Android build may depend on this package.
- Links from `docs/` to files outside it (AGENTS.md, skills/, LICENSE) resolve
  on GitHub; `onBrokenLinks` is `warn`, so read build warnings rather than
  assuming links work.
- Components / Patterns pages are **planned** stubs — do not present unshipped
  APIs or an interactive catalog as implemented.
- When adding a new doc under `docs/`, add it to the right sidebar in `sidebars.ts`.
- American English; restrained, production-minded tone (same as repo docs).
