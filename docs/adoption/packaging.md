# Packaging

Gradle modules, published artifacts, and how to pick **only what you need**.
Coordinates are **planned**.

## Planned published artifacts

| Artifact | Coordinate | Typical consumer |
| --- | --- | --- |
| Tokens | `dev.decoupled.cabin:cabin-tokens` | All; also OEM overlays |
| Compliance | `dev.decoupled.cabin:cabin-compliance` | Apps + system UI |
| Compose UI | `dev.decoupled.cabin:cabin-compose` | Feature apps |
| Views UI | `dev.decoupled.cabin:cabin-views` | System UI, legacy apps |

Group ID `dev.decoupled.cabin` is illustrative and may be finalized at first
publish.

## What is not published as product runtime

- `samples/*`
- `catalog`
- `website`
- Internal screenshot test fixtures (may ship as `cabin-*-test` later —
  opt-in)

## Dependency graph (reminder)

```text
cabin-tokens
     ▲
cabin-compliance
     ▲
 ┌───┴───┐
compose  views
```

`cabin-compose` ⊀ `cabin-views` (no dependency either way).

## Selecting modules

### Compose-only app

```kotlin
implementation("dev.decoupled.cabin:cabin-compose:<version>")
```

### Views-only system UI

```kotlin
implementation("dev.decoupled.cabin:cabin-views:<version>")
```

### OEM overlay library

```kotlin
implementation("dev.decoupled.cabin:cabin-tokens:<version>")
// optionally: compliance if overlay ships default policies
```

### Full cabin feature with chrome previews

```kotlin
implementation("dev.decoupled.cabin:cabin-compose:<version>")
implementation("dev.decoupled.cabin:cabin-views:<version>")
```

Use sparingly in a single APK; prefer split across system vs app partitions.

## Versioning (planned)

- SemVer once stable.
- Compliance + tokens may version independently but UI releases declare
  compatible token/compliance ranges.
- Document breaking token renames prominently — tokens are the long-term
  contract.

## AAOS image considerations

| Partition / image piece | Suggested Cabin content |
| --- | --- |
| System UI APK | `cabin-views`, tokens, compliance |
| Bundled media app | `cabin-compose` or views per stack |
| Vendor overlay | `oem-tokens` only |

Do not install the catalog app on production user images.

## Anti-bloat rules

1. No “umbrella” AAR that pulls both stacks and samples by default.
2. Optional domain packs (future: `cabin-compose-media`) only if metrics show
   need — start coarse (`cabin-compose`) and split later carefully.
3. Keep transitive deps minimal (no forcing full Material into system UI
   without review).
4. Prefer generating resources from tokens over shipping large unused brand
   packs.

## Related

- [Architecture](../architecture.md)
- [Integration](integration.md)
- [Roadmap](../roadmap.md)
