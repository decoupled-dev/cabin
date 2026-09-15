# Design stance

Cabin exists so the cabin feels **calm**, **legible**, and **inevitable**.
Every surface earns its place. Compliance is material we design with — not a
bolt-on checklist after the visuals land.

## Calm

The cabin is not a phone. Drivers and passengers should not fight chrome,
motion, or competing calls to action. Cabin favors stable layouts, quiet
surfaces, and controls that stay where muscle memory expects them.

Calm is not emptiness. It is deliberate hierarchy: one primary signal, clear
secondary support, nothing that only exists to look busy.

## Legible

At speed, in glare or darkness, meaning must arrive in a glance. Typography,
contrast, and status presentation are product decisions first — encoded later
as tokens and components
([accessibility & glanceability](../compliance/accessibility-glanceability.md)).

If a value needs explanation to be trusted, the design has already failed the
driver.

## Inevitable

Good cabin UI feels obvious in retrospect: the right control is where you
reach; the restricted state removes temptation without theater; brand shows
through without rewriting the grammar of the system.

Inevitability comes from a shared language — Compose and Views as two skins
over the same meaning ([pillars](pillars.md)) — and from OEM theming that
extends rather than forks ([tokens](../design-language/tokens.md),
[extension model](../components/extension-model.md)).

## Compliance as design material

Driving restrictions, touch minima, glance budgets, and fail-safe defaults are
not constraints to work around after mockups. They shape structure from the
first sketch:

- Restricted flows get substitutes, not disabled clutter.
- Safety-critical actions stay available and unmistakable.
- Unknown vehicle signals get honest degraded UI — never invented values.

See [compliance](../compliance/README.md) and
[safety-critical](../compliance/safety-critical.md).

## Craft values

| Value | Product meaning |
| --- | --- |
| **Clarity** | One job per surface; plain language; no decorative ambiguity |
| **Restraint** | Fewer elements; stronger hierarchy; motion only when it earns trust |
| **Essentialism** | Ship the cabin set people need; leave vanity packs out of v1 |
| **Craft** | Pixel and state discipline across day/night and both UI stacks |
| **Human-centered cabin HMI** | Design for drivers and passengers in a moving vehicle — not for desktop demos |

## What we refuse

- Phone-first patterns stretched onto a larger display
- Surfaces that exist only to showcase the toolkit
- Brand expression that remaps safety meaning
- Default forks of Cabin core for “small OEM tweaks”

## Related

- [Pillars](pillars.md)
- [Features](features.md)
- [Vision](../vision.md)
- [Principles](../principles.md)
