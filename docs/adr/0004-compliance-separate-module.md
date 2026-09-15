# ADR 0004: Compliance as a separate module

- **Status:** Accepted
- **Date:** 2026-09-15

## Context

Driving and UX restrictions must be consistent across Compose and Views and
across apps and SystemUI. Embedding policy inside widgets causes drift and
forces UI toolkit dependencies into shared logic.

## Decision

- Ship **`cabin-compliance` / `CabinCompliance`** as its own artifact
- **No** Compose or Views widget dependencies in compliance
- Widgets declare interactions; policy returns allow / substitute / block
- v0.1 implements the state model and chrome matrix in
  [restriction-states.md](../compliance/restriction-states.md)

## Consequences

- Tokens and compliance remain thin; both UI stacks depend upward
- Unit tests for gating run without inflating views
- Hard rule: never hardcode driving policy inside bar widgets

## Links

- [compliance/README.md](../compliance/README.md)
- [restriction-states.md](../compliance/restriction-states.md)
- [architecture.md](../architecture.md)
- [mvp.md](../mvp.md)
