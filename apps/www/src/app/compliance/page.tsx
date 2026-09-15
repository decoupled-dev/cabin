import type { Metadata } from "next";
import { ButtonLink } from "@/components/button-link";
import { Reveal } from "@/components/reveal";
import { DOCS_URL } from "@/lib/site";

export const metadata: Metadata = {
  title: "Compliance",
  description:
    "Cabin compliance — driving restrictions, UX minima, glanceability, and safety-critical defaults as shared library contracts.",
};

const layers = [
  {
    title: "Driving restrictions",
    body: "Motion and distraction gating while the vehicle is in motion. Restricted flows get honest substitutes — not disabled clutter.",
  },
  {
    title: "UX restrictions",
    body: "Touch targets, density, contrast, and day/night profiles that both UI stacks honor from the same policy.",
  },
  {
    title: "Glanceability",
    body: "Typography and layout budgets so primary values arrive in under a second without scanning the whole screen.",
  },
  {
    title: "Safety-critical",
    body: "Deterministic affordances and fail-safe presentation when vehicle signals are missing — never invented values.",
  },
] as const;

export default function CompliancePage() {
  return (
    <div className="bg-background">
      <div className="mx-auto max-w-6xl px-5 pb-24 pt-16 sm:px-8 sm:pt-24">
        <Reveal>
          <p className="text-status font-semibold uppercase tracking-[0.14em] text-on-surface-variant">
            Compliance
          </p>
          <h1 className="mt-4 max-w-3xl font-display text-display text-on-surface text-balance">
            Safe by default
          </h1>
          <p className="mt-5 max-w-2xl text-body text-on-surface-variant text-balance">
            Compliance is design material — a first-class Restriction Engine
            consumed by Compose and Views. Programs tighten policy without
            forking components.
          </p>
        </Reveal>

        <Reveal className="mt-14" delayMs={40}>
          <div className="overflow-hidden border border-[var(--outline-subtle)] bg-surface">
            <div className="grid border-b border-[var(--outline-subtle)] sm:grid-cols-3">
              {[
                { label: "Vehicle state", value: "Driving · 48 km/h" },
                { label: "Policy", value: "Limit string · No keyboard" },
                { label: "Outcome", value: "Browse substituted" },
              ].map((cell) => (
                <div
                  key={cell.label}
                  className="border-[var(--outline-subtle)] px-5 py-5 sm:border-r sm:last:border-r-0"
                >
                  <p className="text-status uppercase tracking-[0.12em] text-on-surface-variant">
                    {cell.label}
                  </p>
                  <p className="mt-2 font-display text-title text-on-surface">
                    {cell.value}
                  </p>
                </div>
              ))}
            </div>
            <div className="flex items-center gap-3 px-5 py-4">
              <span className="h-2 w-2 shrink-0 rounded-full bg-warning" />
              <p className="text-status text-on-surface-variant">
                Illustrative restriction path — widgets query policy; they do
                not hardcode OEM if/else trees.
              </p>
            </div>
          </div>
        </Reveal>

        <div className="mt-16 grid gap-10 md:grid-cols-2">
          {layers.map((layer, i) => (
            <Reveal key={layer.title} delayMs={Math.min(i * 40, 80)}>
              <article className="h-full border-t border-primary/35 pt-6">
                <h2 className="font-display text-title text-on-surface">
                  {layer.title}
                </h2>
                <p className="mt-3 text-body text-on-surface-variant">
                  {layer.body}
                </p>
              </article>
            </Reveal>
          ))}
        </div>

        <Reveal className="mt-20">
          <div className="flex flex-wrap gap-3">
            <ButtonLink href={`${DOCS_URL}compliance/`} external>
              Compliance docs
            </ButtonLink>
            <ButtonLink href="/develop" variant="secondary">
              Start developing
            </ButtonLink>
          </div>
        </Reveal>
      </div>
    </div>
  );
}
