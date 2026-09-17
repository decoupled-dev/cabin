# Token codegen

`tokens/cabin.tokens.json` is the single source of truth. Codegen emits Android,
Compose, and CSS with the **same semantic names**, then CI fails on drift.

## Outputs

| Output | Path |
| --- | --- |
| Kotlin constants | `cabin-tokens/.../CabinTokens.generated.kt` |
| Android resources | `cabin-tokens/.../res/values/` + `values-night/` |
| Theme.Cabin attrs | `cabin-tokens/.../res/values/cabin_attrs.xml` |
| Compose mappings | `cabin-compose/.../CabinComposeTokens.generated.kt` |
| CSS variables | `apps/www/src/styles/cabin.tokens.css` |
| CSS mirror | `tokens/generated/cabin.tokens.css` |

Catalog consumes the Kotlin/Android outputs through `cabin-tokens` (no separate
catalog codegen).

## Commands

```bash
# Regenerate all outputs
python3 tools/generate_cabin_tokens.py
# or
./gradlew :cabin-tokens:generateCabinTokens

# CI / local drift gate (exit 1 if committed outputs are stale)
python3 tools/generate_cabin_tokens.py --check
./gradlew :cabin-tokens:checkCabinTokenDrift
```

## How to add a token

1. Edit `tokens/cabin.tokens.json` — keep naming `cabin.{category}.{tier}.{name}`.
2. Prefer semantic or component tiers; do not invent call-site hex/`sp`/`dp`.
3. For safety-adjacent color roles (`warning`, `error`) set:
   `"extensions": { "cabin.lock": "safety-adjacent" }`.
4. Night scheme `warning` / `error` / `charging` **must** keep that lock
   (codegen fails closed otherwise — OEMs cannot soft-wash).
5. Run `python3 tools/generate_cabin_tokens.py`.
6. Commit the JSON **and** regenerated Android / Compose / CSS outputs together.
7. Confirm `python3 tools/generate_cabin_tokens.py --check` passes.

### Naming parity

| Layer | Example |
| --- | --- |
| JSON | `cabin.color.semantic.primary` |
| Kotlin | `CabinTokens.Color.Semantic.primary` |
| Android res | `cabin_color_semantic_primary` |
| Compose | `CabinComposeTokens.Semantic.primary` |
| CSS | `--cabin-color-semantic-primary` (+ web alias `--primary`) |

## Safety locks

Codegen requires `extensions["cabin.lock"] = "safety-adjacent"` on:

- `cabin.color.semantic.warning` / `.error`
- `cabin.color.scheme.night.warning` / `.error` / `.charging`

Night locked values must stay high-chroma. Do not remap them for brand wash.

## www / catalog

- `apps/www` imports generated CSS; presentation-only vars may use `color-mix`
  from generated roles — no hand hex for cabin roles.
- Catalog demos bind `CabinTokens` / Theme Kit; they stay out of the publish
  graph.

## Related

- [Token schema](token-schema.md)
- [Tokens overview](tokens.md)
- [Testing](../testing.md)
