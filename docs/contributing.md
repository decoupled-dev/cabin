# Contributing

Thanks for helping build Cabin. Library code is in active development
(v0.2 track, [ADR 0006](adr/0006-material3-looks-and-tiers.md)); see the
[roadmap](roadmap.md).

## Code of conduct, license, sign-off

See the root [CONTRIBUTING.md](../CONTRIBUTING.md) (Apache-2.0, DCO sign-off,
license headers) and [CODE_OF_CONDUCT.md](../CODE_OF_CONDUCT.md).

## What to contribute now

- Documentation clarity, structure, and cross-links
- Compliance and automotive UX accuracy
- Component inventory gaps and acceptance criteria
- Adoption guidance for AAOS programs
- Website IA improvements

For library source, open an issue first so scope matches the current track.

## Quality bar

Contributions should align with [principles](principles.md):

- Clean Code, SOLID, decoupling
- Dual-stack awareness (Compose **and** Views)
- Compliance as first-class
- Grounding in modern Android guidance and
  [android/skills](https://github.com/android/skills) patterns
- American English; precise, production-minded tone — not marketing fluff

## AI-assisted contributions

Agents (Cursor, Gemini, Claude, etc.) are welcome **when** they follow:

1. [AGENTS.md](../AGENTS.md) hard rules
2. Relevant modular skills under [skills/](../skills/) (see
   [docs/agents](agents/README.md))
3. Existing architecture, compliance, and build-tree docs — do not invent a
   parallel packaging story

Humans still review PRs for pillars, thin deps, dual-stack parity, and
planned-vs-implemented labeling. Agent output is not a substitute for review.

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
