# Contributing to Cabin

Thanks for helping. Cabin is licensed under the [Apache License 2.0](LICENSE).
By contributing, you agree that your contributions are licensed under the same
terms (Apache-2.0, section 5).

## Before you start

- Read [AGENTS.md](AGENTS.md). Its hard rules apply to humans and AI agents.
- For changes to architecture, compliance contracts or module dependencies,
  open an issue first and cite the relevant ADR in [docs/adr](docs/adr/README.md).
- More detail: [docs/contributing.md](docs/contributing.md).

## Developer Certificate of Origin

Every commit must be signed off, certifying the
[Developer Certificate of Origin 1.1](https://developercertificate.org/):

```bash
git commit -s -m "feat(compose): add CabinStepper"
```

This adds `Signed-off-by: Your Name <you@example.com>`, using your real name.

## License headers

New source files need the Apache-2.0 header. The tool adds it for you:

```bash
python3 tools/license_headers.py --fix
```

Generated files get it from their generators. CI runs `--check`.

## Quality pipeline

Run the same checks as CI before you push, and enable the hooks once per clone:

```bash
git config core.hooksPath .githooks   # pre-commit: fast gates; commit-msg: Conventional Commits
tools/quality.sh                      # everything CI runs
```

Rules, baselines and how to fix findings: [docs/quality.md](docs/quality.md).

## Pull request checklist

- [ ] `tools/quality.sh` passes (gates, detekt, lint, public API, unit tests)
- [ ] Public API changes are intended and the `api/*.api` dump is updated (`./gradlew apiDump`)
- [ ] Tokens only: no color literals; sizes from tokens or `CabinKitMetrics`; strings are resources
- [ ] Interactions are gated through the Restriction Engine
- [ ] Docs updated; planned APIs are labeled as planned
- [ ] Commits follow Conventional Commits and are signed off (DCO)

## Commit style

[Conventional Commits](https://www.conventionalcommits.org/):
`feat(tokens): …`, `fix(compliance): …`, `docs: …`.

## Reporting bugs and security issues

Open a GitHub issue for bugs. Report security or safety-gate issues privately;
see [SECURITY.md](SECURITY.md).
