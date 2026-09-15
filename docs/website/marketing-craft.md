# Marketing site craft

Cabin has **two public web surfaces**. Do not mix their roles or visual systems.

| Surface | Path | Host | Role |
| --- | --- | --- | --- |
| **Docs shell** | `website/` (Docusaurus) | GitHub Pages (`decoupled-dev.github.io/cabin`) | Developer reference, IA from the [site plan](site-plan.md) |
| **Marketing** | `apps/www` (Next.js) | [cabin-www.vercel.app](https://cabin-www.vercel.app) / cabin.decoupled.dev | User-facing Material-class product presence |

When editing the marketing site, follow the authoritative craft brief:

→ **[apps/www/DESIGN.md](../../apps/www/DESIGN.md)** (anti-vibecode / craft checklist)

That brief locks brand (night cabin + forest primary), bans generic AI landing
patterns, and requires day/night, HMI preview fidelity, and calm automotive copy.
The docs shell stays restrained OSS documentation chrome — it is not governed by
the marketing DESIGN.md.
