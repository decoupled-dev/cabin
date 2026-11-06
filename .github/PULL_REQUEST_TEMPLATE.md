## What and why

<!-- What does this change, and why? Link the issue or ADR. -->

## Checklist

- [ ] `tools/quality.sh` passes (gates, detekt, lint, public API, unit tests)
- [ ] Public API changes are intended and `api/*.api` is updated
- [ ] Tokens only (no hardcoded colors or dimensions in library code)
- [ ] Interactions are gated through the Restriction Engine
- [ ] Docs updated; planned APIs are labeled as planned
- [ ] Commits are signed off (`git commit -s`)
