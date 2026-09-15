# Principles

Cabin’s engineering and design principles keep the platform adoptable in
production AAOS programs: safe by default, modular under OEM brand pressure,
and consistent across Compose and Views.

## 1. Clean Code and readable contracts

- Prefer small modules with one reason to change
  ([architecture](architecture.md)).
- Name APIs for cabin intent (`CabinDrivingGate`, `CabinTouchTarget`) not
  framework leftovers.
- Document behavior in the same PR as the API; these docs are the source of
  truth until code lands.
- Avoid clever abstractions that OEMs cannot debug in a vehicle bring-up.

## 2. SOLID in a design-system context

| Principle | Cabin application |
| --- | --- |
| **S**ingle responsibility | Tokens, compliance, Compose UI, and Views UI are separate artifacts |
| **O**pen/closed | OEM branding and custom screens via tokens + extension points, not core edits ([extension model](components/extension-model.md)) |
| **L**iskov | Dual-stack components honor the same state/behavior contracts |
| **I**nterface segregation | Apps depend on narrow modules (`cabin-compose` without `cabin-views`) |
| **D**ependency inversion | UI depends on token/compliance abstractions, not OEM brand hardcodes |

## 3. Decoupling and anti-bloat

- **No kitchen-sink AAR.** Published artifacts stay focused
  ([packaging](adoption/packaging.md)).
- **Tokens and compliance have zero UI toolkit dependency** so both stacks
  can share them.
- **Samples, catalog, and website never leak into runtime** dependencies.
- Prefer composition of small components over mega-widgets that force unused
  surface area into the APK/system image.

## 4. Safety-critical UI posture

Cabin treats cabin UI as safety-adjacent:

- Deterministic defaults when vehicle signals are missing or stale
  ([safety-critical](compliance/safety-critical.md)).
- Fail closed on ambiguous driving-restriction state when policy says so
  ([driving restrictions](compliance/driving-restrictions.md)).
- No animation or interaction that competes with glanceability during motion
  ([accessibility & glanceability](compliance/accessibility-glanceability.md)).
- Explicit distinction between **informational**, **convenience**, and
  **safety-critical** controls in component taxonomy
  ([components](components/README.md)).

## 5. Modern Android and Compose guidelines

Cabin aligns with current Android platform guidance:

- Jetpack libraries, Kotlin-first APIs, coroutines/Flow for async vehicle
  signals where appropriate.
- Compose: unidirectional data flow, stable parameters, theming via Cabin
  theme — not ad-hoc `Modifier` soup ([compose](platforms/compose.md)).
- Views: styleable attributes, predictable inflation, no hidden global
  singletons for theme ([views](platforms/views.md)).
- Accessibility: content descriptions, focus order, and contrast as
  compliance requirements — not polish.

## 6. Alignment with android/skills

Cabin’s docs and (future) implementation patterns are grounded in
[android/skills](https://github.com/android/skills) — Google’s AI-optimized
modular Android instructions. Practically, Cabin commits to:

| android/skills theme | Cabin encoding |
| --- | --- |
| Modular, task-scoped guidance | Docs split by concern (compliance, tokens, platforms, adoption) |
| Modern toolkit defaults | Compose + Views parity with current Jetpack practices |
| Explicit quality bars | Lint/API guidelines called out in contributing and platform docs |
| Avoid outdated patterns | No encouraging AppCompat-only dead ends; Views path is intentional for AAOS system UI, not nostalgia |
| Testable architecture | Compliance gates designed as injectable policies, not static globals |

When implementing library code in later phases, contributors should consult
relevant android/skills modules for Compose, architecture, and testing — and
prefer those patterns unless automotive constraints require a documented
exception.

Cabin also ships **in-repo Agent Skills** (`skills/*/SKILL.md`) and
[AGENTS.md](../AGENTS.md) so coding agents stay aligned with these principles
(thin modules, compliance-first, dual-stack, Views-first SystemUI). See
[docs/agents](agents/README.md).

## 7. Dual-stack parity

Parity is a principle, not a backlog item:

- Shared **semantic tokens** define appearance.
- Shared **compliance policies** define allowed interaction.
- Per-stack implementations may differ in API shape (`Composable` vs `View`)
  but not in states, minimum sizes, or gating behavior.
- Catalog tests (planned) assert parity for critical components.

See [compose](platforms/compose.md) and [views](platforms/views.md).

## 8. OEM brand without fork

- Brand through [tokens](design-language/tokens.md).
- Add product-specific screens through the
  [extension model](components/extension-model.md).
- If a change requires editing Cabin core for one OEM, the design failed —
  open an issue to extend the public API instead.

## 9. Production-ready documentation

Until code exists, docs must be implementable:

- Planned APIs and module coordinates are labeled **planned**.
- Acceptance criteria appear on compliance and component pages.
- Examples show integration footprints realistic for AAOS images.

## Related

- [Vision](vision.md)
- [Architecture](architecture.md)
- [Contributing](contributing.md)
- [Compliance](compliance/README.md)
- [Agents](agents/README.md) · [AGENTS.md](../AGENTS.md)
