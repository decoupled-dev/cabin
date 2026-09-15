# Contributing

Thanks for helping build Cabin. This repository is in **Phase 1 (docs)**.
Library code will land in later phases per the [roadmap](roadmap.md).

## Code of conduct

Be respectful and professional. Cabin serves OEMs, Tier-1s, and developers
shipping real vehicles — keep discussion concrete and inclusive.

## What to contribute now

- Documentation clarity, structure, and cross-links
- Compliance and automotive UX accuracy
- Component inventory gaps and acceptance criteria
- Adoption guidance for AAOS programs
- Website IA improvements

**Do not** submit Android library source until Phase 2+ issues/milestones call
for it, unless maintainers ask.

## Quality bar

Contributions should align with [principles](principles.md):

- Clean Code, SOLID, decoupling
- Dual-stack awareness (Compose **and** Views)
- Compliance as first-class
- Grounding in modern Android guidance and
  [android/skills](https://github.com/android/skills) patterns
- American English; precise, production-minded tone — not marketing fluff

## Doc contributions

1. Fork and branch from `main` (`docs/<short-description>`).
2. Edit or add markdown under `docs/` (and root README if needed).
3. Cross-link related pages; update [docs/README.md](README.md) if you add a
   file.
4. Mark unimplemented APIs as **planned**.
5. Open a PR with a clear summary and linked issue (if any).

### Writing checklist

- [ ] Focused headings; one concern per file where possible
- [ ] Dual-stack and compliance mentioned where relevant
- [ ] Links to architecture / tokens / compliance as appropriate
- [ ] No fake “implemented” APIs
- [ ] No binary assets unless discussed with maintainers

## Future code contributions (preview)

When library modules exist:

- Follow module dependency rules in [architecture](architecture.md).
- Prefer small PRs; one component or policy per PR when feasible.
- Include tests (unit + screenshot/parity where applicable).
- Update docs in the same PR as API changes.
- Run formatting/lint as defined by the project (to be added).

### Planned commit style

Use conventional commits:

```text
feat(tokens): add semantic color roles
fix(compliance): fail closed when drive state unknown
docs(components): specify HVAC zone parity
chore: update gitignore for compose metrics
```

## Issue triage labels (planned)

| Label | Use |
| --- | --- |
| `docs` | Documentation |
| `compliance` | Driving / UX / safety |
| `design-language` | Tokens / foundations |
| `compose` / `views` | Stack-specific |
| `adoption` | Integration / packaging |
| `good first issue` | Newcomer-friendly |

## Security and safety

Do not file public issues that describe exploitable vehicle attack details.
For safety-critical UI concerns, mark clearly and prefer deterministic,
fail-safe proposals ([safety-critical](compliance/safety-critical.md)).

## License

By contributing, you agree your contributions are licensed under the
Apache License 2.0 ([LICENSE](../LICENSE)).

## Questions

Open a GitHub Discussion or Issue for design questions. For large proposals
(new component family, compliance model changes), start with an Issue before
a large PR.
