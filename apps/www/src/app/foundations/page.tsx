import type { Metadata } from "next";
import { ButtonLink } from "@/components/button-link";
import { Reveal } from "@/components/reveal";
import { DOCS_URL } from "@/lib/site";

export const metadata: Metadata = {
  title: "Foundations",
  description:
    "Cabin foundations — color roles, type, space, elevation, and motion for glanceable cabin HMI.",
};

const foundations = [
  {
    title: "Color",
    body: "Semantic roles for brand, surface, feedback, and domain accents (charging, climate, media). Day and night schemes stay legible; safety-adjacent warning and error meanings stay locked.",
    swatches: [
      { name: "Primary", varName: "var(--primary)" },
      { name: "Charging", varName: "var(--charging)" },
      { name: "Climate", varName: "var(--climate)" },
      { name: "Warning", varName: "var(--warning)" },
    ],
  },
  {
    title: "Typography",
    body: "Glance roles from display to status. Tabular figures for range, speed, and temperature. Highly legible grotesques at cabin distance — OEM-overridable via tokens.",
  },
  {
    title: "Space & touch",
    body: "Spacing steps preserve automotive touch minima. Driver-zone separation stays generous; dense chrome never shrinks targets below compliance baselines.",
  },
  {
    title: "Elevation & motion",
    body: "Flat, high-contrast layers over deep shadow stacks. Motion is mechanical and precise — press feedback and park transitions, not entertainment while driving.",
  },
] as const;

export default function FoundationsPage() {
  return (
    <div className="relative isolate overflow-hidden">
      <div className="pointer-events-none absolute inset-0 -z-10">
        <div className="absolute inset-0 bg-background" />
        <div className="absolute right-[-10%] top-0 h-[50vmin] w-[50vmin] rounded-full bg-[radial-gradient(circle,var(--hero-glow-a),transparent_70%)] blur-3xl opacity-70" />
      </div>

      <div className="mx-auto max-w-6xl px-5 pb-24 pt-16 sm:px-8 sm:pt-24">
        <Reveal>
          <p className="text-status font-semibold uppercase tracking-[0.14em] text-on-surface-variant">
            Foundations
          </p>
          <h1 className="mt-4 max-w-3xl font-display text-display text-on-surface text-balance">
            The grammar of the cabin
          </h1>
          <p className="mt-5 max-w-2xl text-body text-on-surface-variant text-balance">
            Color, type, space, elevation, motion, and iconography — packaged as
            tokens so Compose and Views resolve the same meaning at theme time.
          </p>
        </Reveal>

        <div className="mt-16 grid gap-12 lg:grid-cols-2">
          {foundations.map((item, i) => (
            <Reveal key={item.title} delayMs={i * 70}>
              <article className="border-t border-[var(--outline-subtle)] pt-6">
                <h2 className="font-display text-headline text-on-surface">
                  {item.title}
                </h2>
                <p className="mt-4 text-body text-on-surface-variant">
                  {item.body}
                </p>
                {"swatches" in item && item.swatches ? (
                  <ul className="mt-6 flex flex-wrap gap-3">
                    {item.swatches.map((s) => (
                      <li key={s.name} className="flex items-center gap-2">
                        <span
                          className="h-8 w-8 rounded-md border border-[var(--outline-subtle)]"
                          style={{ background: s.varName }}
                        />
                        <span className="text-status text-on-surface-variant">
                          {s.name}
                        </span>
                      </li>
                    ))}
                  </ul>
                ) : null}
              </article>
            </Reveal>
          ))}
        </div>

        <Reveal className="mt-20">
          <div className="flex flex-col gap-6 rounded-2xl border border-[var(--outline-subtle)] bg-surface p-8 sm:flex-row sm:items-center sm:justify-between">
            <div>
              <h2 className="font-display text-title text-on-surface">
                Day and night are product decisions
              </h2>
              <p className="mt-2 max-w-xl text-status text-on-surface-variant">
                Toggle the scheme in the header. Night keeps luminance for
                signals; day holds contrast under glare assumptions.
              </p>
            </div>
            <ButtonLink href={`${DOCS_URL}design-language/foundations`} external>
              Read foundations docs
            </ButtonLink>
          </div>
        </Reveal>
      </div>
    </div>
  );
}
