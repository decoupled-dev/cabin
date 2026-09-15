# ADR 0003: Tokens via overlay + RRO

- **Status:** Accepted
- **Date:** 2026-09-15

## Context

OEMs need brand expression without maintaining private forks of every
component. Platform images already use **Runtime Resource Overlays (RROs)**.
Hardcoding brand in widgets breaks thin adoption and dual-stack parity.

## Decision

- Brand through **token overlays** (apps) and **RROs** / optional Soong token
  overlay modules (platform)
- Machine-readable stub: [`tokens/cabin.tokens.json`](../../tokens/cabin.tokens.json)
- Safety-adjacent token meanings (`warning`, `error`) are lockable and must
  not be remapped for decoration
- Do not treat OEM forks of Cabin core as the default customization path

## Consequences

- Theme Kit resolves tokens → attrs/resources
- Components reference tokens only
- Agents must not invent per-OEM copies of System/Status bar sources

## Links

- [token-schema.md](../design-language/token-schema.md)
- [tokens.md](../design-language/tokens.md)
- [build-tree.md](../adoption/build-tree.md)
- [extension-model.md](../components/extension-model.md)
