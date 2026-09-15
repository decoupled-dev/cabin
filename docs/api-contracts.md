# API contracts (planned)

Frozen naming targets for **v0.1 MVP** and forward. Nothing here is published
until implementation lands.

## Maven coordinates (Gradle apps)

Group: `dev.decoupled.cabin` (illustrative; finalize at first publish).

| Artifact | Coordinate | MVP |
| --- | --- | --- |
| Tokens | `dev.decoupled.cabin:cabin-tokens` | **Yes** |
| Compliance | `dev.decoupled.cabin:cabin-compliance` | **Yes** |
| Views | `dev.decoupled.cabin:cabin-views` | **Yes** (Theme Kit + System/Status bars Alpha) |
| Compose | `dev.decoupled.cabin:cabin-compose` | Post-MVP (**Experimental** Theme + bars) |

## Soong module names (build-tree)

| Module | MVP SystemUI |
| --- | --- |
| `CabinTokens` | Required |
| `CabinCompliance` | Required |
| `CabinViews` | Required |
| `CabinCompose` | **Do not** depend from SystemUI |

See [build-tree](adoption/build-tree.md).

## Kotlin / Java package roots (planned)

| Module | Package root |
| --- | --- |
| `cabin-tokens` | `dev.decoupled.cabin.tokens` |
| `cabin-compliance` | `dev.decoupled.cabin.compliance` |
| `cabin-views` | `dev.decoupled.cabin.views` |
| `cabin-compose` | `dev.decoupled.cabin.compose` |

Views Theme Kit types: `dev.decoupled.cabin.views.theme.CabinThemes`,
`…CabinThemeResolver`. Views chrome: `…CabinSystemBarView`,
`…CabinStatusBarView` (Alpha).

Compose Theme + bars (Experimental): `dev.decoupled.cabin.compose.theme.CabinTheme`,
`…CabinSystemBar`, `…CabinStatusBar`.

## Stability

| Label | Meaning |
| --- | --- |
| **Experimental** | May change without notice; Compose bar parity starts here |
| **Alpha / Beta** | Tracking to stable; migration notes required |
| **Stable** | SemVer; tests green; pillars satisfied |

v0.1 library drop may ship Tokens + Compliance + Views bars as **Alpha**.

## SemVer policy (sketch)

- **MAJOR** — breaking token renames, removed public types, Soong name changes
- **MINOR** — additive components/APIs, new optional interactions
- **PATCH** — bugfixes, doc-equivalent behavior fixes

Token renames are high-cost; prefer additive aliases then deprecate.
Platform pins Cabin by manifest **tag/revision**, not floating main
([soong](platforms/soong.md)).

## Dependency rules (contract)

```text
cabin-tokens
     ▲
cabin-compliance
     ▲
cabin-views     cabin-compose   (Views Alpha bars; Compose Experimental bars)
```

Theme Kit Alpha in `cabin-views` depends on **tokens only**; bars also wire
`cabin-compliance`. Compose Theme + bars depend on tokens + compliance and
must not depend on `cabin-views`.

- Tokens & compliance: **no** UI toolkit deps
- Views ⊀ Compose and Compose ⊀ Views
- SystemUI static_libs: tokens + compliance + views only (never `CabinCompose`)

## Related

- [MVP](mvp.md)
- [Architecture](architecture.md)
- [Packaging](adoption/packaging.md)
- [Support matrix](support-matrix.md)
