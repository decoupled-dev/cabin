# Agents and skills

Cabin ships **AI-agent affordances** so coding agents (Cursor, Gemini, Claude,
and others) implement against the same contracts humans read.

## Affordances

| Artifact | Role |
| --- | --- |
| [AGENTS.md](../../AGENTS.md) | Authoritative hard rules for any agent in this repo |
| [llms.txt](../../llms.txt) | Concise LLM-oriented doc map and reading order |
| [skills/](../../skills/) | Modular Agent Skills (`SKILL.md` per concern) |
| [.cursor/rules/cabin.mdc](../../.cursor/rules/cabin.mdc) | Cursor project rules mirroring hard constraints |

## Skills layout

Follows the open [Agent Skills](https://agentskills.io) pattern (and is aligned
in spirit with [android/skills](https://github.com/android/skills)):

```text
skills/
  cabin-overview/SKILL.md
  cabin-tokens/SKILL.md
  cabin-compliance/SKILL.md
  cabin-compose/SKILL.md
  cabin-views/SKILL.md
  cabin-soong/SKILL.md
  cabin-component-spec/SKILL.md
```

Each `SKILL.md` has YAML frontmatter (`name`, `description` with “use when…”)
plus a focused body. Agents should auto-select by description; humans can
point agents at a skill path explicitly.

## How humans use them

1. Point the agent at [AGENTS.md](../../AGENTS.md) (many tools load this automatically).
2. For a scoped task, attach or invoke the relevant skill under `skills/`.
3. Prefer docs linked from [llms.txt](../../llms.txt) over inventing architecture.
4. Review agent PRs against pillars and hard rules — agents do not replace review
   ([contributing](../contributing.md)).

## Relationship to android/skills

[android/skills](https://github.com/android/skills) provides AI-optimized
modular Android instructions from Google. Cabin:

- Grounds engineering principles in those modern Android patterns
  ([principles](../principles.md))
- Adds **Cabin-specific** skills for automotive compliance, dual-stack parity,
  tokens, and Soong/build-tree adoption — concerns android/skills does not
  encode for this design system

Use both: android/skills for general Android/Compose craft; Cabin skills for
product and platform constraints.

## Related

- [Product pillars](../product/pillars.md)
- [Architecture](../architecture.md)
- [Build-tree](../adoption/build-tree.md)
