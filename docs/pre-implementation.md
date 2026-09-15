# Pre-implementation gate

Before writing Android library source, satisfy this checklist. The **v0.1 MVP
is frozen** — see [mvp.md](mvp.md). Do not expand scope while implementing MVP.

> Agents: read [AGENTS.md](../AGENTS.md), then this page, then the linked
> contracts. Do **not** start `cabin-compose` media/HVAC/EV/vehicle-controls
> screens until MVP is done.

## Gate checklist

### Product & scope

- [ ] [MVP](mvp.md) scope understood (tokens + Restriction Engine + Theme Kit +
      System Bar + Status Bar, **Views-first**)
- [ ] Non-goals in MVP respected (no media/HVAC/EV domain screens, no catalog app)
- [ ] Pillars still apply ([product/pillars.md](product/pillars.md))

### Contracts to implement against

- [ ] [Token schema](design-language/token-schema.md) + stub
      [`tokens/cabin.tokens.json`](../tokens/cabin.tokens.json)
- [ ] [Restriction states](compliance/restriction-states.md) + existing
      [compliance](compliance/README.md) suite
- [ ] Specs: [system-bar](components/specs/system-bar.md) ·
      [status-bar](components/specs/status-bar.md)
- [ ] [API contracts](api-contracts.md) (Maven, Soong, packages)
- [ ] [Support matrix](support-matrix.md)
- [ ] [Testing](testing.md) expectations for v0.1
- [ ] ADRs accepted: [adr/](adr/README.md)

### Build & distribution

- [ ] Dual Gradle + Soong path understood ([build-tree](adoption/build-tree.md),
      [ADR 0001](adr/0001-dual-gradle-soong.md))
- [ ] Views-first platform ([ADR 0002](adr/0002-views-first-platform.md))
- [ ] Tokens via overlay/RRO ([ADR 0003](adr/0003-tokens-via-overlay-rro.md))
- [ ] Compliance as separate module ([ADR 0004](adr/0004-compliance-separate-module.md))
- [ ] Thin deps: SystemUI gets tokens + compliance + views only

### Agent affordances (already shipped — use, don’t reinvent)

- [ ] [AGENTS.md](../AGENTS.md)
- [ ] Skills under [`skills/`](../skills/)
- [ ] [docs/agents](agents/README.md)

## Do not start until MVP lands

| Work | Status until MVP done |
| --- | --- |
| `cabin-compose` media / HVAC / EV / vehicle-controls screens | **Blocked** |
| Overlay & Toast as a full pattern pack | Later (bars may use minimal feedback only if required) |
| Catalog app / docs website implementation | Later |
| Kitchen-sink umbrella modules | Never |
| Compose-first SystemUI | Never (Views-first is frozen) |

Compose Theme Kit stubs that mirror Views token resolution may land **with**
MVP only insofar as they prove token parity — not as a path to ship domain
Compose screens early. Prefer Views chrome for v0.1 success criteria.

## Suggested implementation order (post-gate)

```text
1. cabin-tokens (+ codegen from tokens/cabin.tokens.json)
2. cabin-compliance (Restriction Engine per restriction-states.md)
3. Theme Kit (Views theme from tokens; optional Compose mirror)
4. System Bar (Views)
5. Status Bar (Views)
6. Soong bp + thin SystemUI wiring sketches / samples as needed
7. Tests per docs/testing.md
```

## Related

- [MVP](mvp.md)
- [Roadmap](roadmap.md)
- [Architecture](architecture.md)
- [Feature plan](product/features.md)
