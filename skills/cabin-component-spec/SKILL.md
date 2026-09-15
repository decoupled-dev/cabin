---
name: cabin-component-spec
description: >-
  Write or implement a Cabin component specification — states, touch targets,
  compliance gates, token deps, and dual Compose+Views API shapes with parity.
  Use when adding a new component, expanding docs/components, or reviewing
  whether a widget is ready to implement.
license: Apache-2.0
metadata:
  audience: agents
  project: cabin
---

# Cabin component spec

## Before coding

1. Pass [pillars](../../docs/product/pillars.md) (glanceable, quiet, safe, dual skin, thin).
2. Place in [feature layers](../../docs/product/features.md).
3. Classify: informational / convenience / safety-critical.

## Spec checklist

- [ ] Purpose and cabin placement
- [ ] States and transitions (include unavailable / stale / fault if signal-backed)
- [ ] Interaction classes + compliance gates (driving / UX / a11y)
- [ ] Token dependencies (no raw hex/sp)
- [ ] Min touch targets from tokens
- [ ] **Planned** Compose API shape
- [ ] **Planned** Views API shape
- [ ] Parity notes (shared fixtures)
- [ ] Acceptance criteria

Template tone: existing pages under [docs/components/](../../docs/components/README.md).

## Implementation order

```text
tokens → compliance hooks → Compose + Views parity → docs update → catalog later
```

Do not ship Compose-only “source of truth” for components that SystemUI needs
in Views.

## Dual API sketch (planned)

Document both:

```kotlin
// Compose — planned
@Composable fun Example(...)

// Views — planned
class ExampleView : /* ... */ { fun bind(state: ExampleState) }
```

Shared `ExampleState` / actions across stacks.

## Do not

- Skip compliance or parity sections
- Mark APIs implemented when only sketched
- Add kitchen-sink variants for demos

Related: `cabin-compliance` · `cabin-compose` · `cabin-views` · [AGENTS.md](../../AGENTS.md)
