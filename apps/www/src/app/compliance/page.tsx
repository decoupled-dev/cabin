import type { Metadata } from "next";
import { ButtonLink } from "@/components/button-link";
import { Reveal } from "@/components/reveal";
import { InstrumentWell } from "@/components/instrument-frame";
import { PageFrame, SectionHeading } from "@/components/site-chrome";
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
      <PageFrame className="pb-24 pt-14 sm:pt-20">
        <Reveal>
          <SectionHeading
            as="h1"
            size="display"
            kicker="Compliance"
            title="Safe by default"
          />
          <p className="mt-5 max-w-2xl text-body text-on-surface-variant text-balance">
            Compliance is design material — a first-class Restriction Engine
            consumed by Compose, Views, and Experimental kit hosts. Programs
            tighten policy without forking components.
          </p>
        </Reveal>

        <Reveal className="mt-12" delayMs={40}>
          <InstrumentWell>
            <div className="grid sm:grid-cols-3">
              {[
                { label: "Vehicle state", value: "Driving · 48 km/h" },
                { label: "Policy", value: "Limit string · No keyboard" },
                { label: "Outcome", value: "Browse substituted" },
              ].map((cell, i) => (
                <div
                  key={cell.label}
                  className={`px-5 py-6 ${
                    i > 0
                      ? "border-t border-[var(--outline-subtle)] sm:border-t-0 sm:border-l"
                      : ""
                  }`}
                >
                  <p className="kicker text-on-surface-variant">{cell.label}</p>
                  <p className="mt-3 font-display text-title text-on-surface">
                    {cell.value}
                  </p>
                </div>
              ))}
            </div>
            <div className="flex items-center gap-3 border-t border-[var(--outline-subtle)] px-5 py-4">
              <span className="h-2 w-2 shrink-0 rounded-full bg-warning" />
              <p className="text-status text-on-surface-variant">
                Illustrative restriction path — widgets query policy; they do
                not hardcode OEM if/else trees.
              </p>
            </div>
          </InstrumentWell>
        </Reveal>

        <div className="mt-14 grid gap-0 md:grid-cols-2">
          {layers.map((layer, i) => (
            <Reveal key={layer.title} delayMs={Math.min(i * 40, 80)}>
              <article
                className={`h-full border-[var(--outline-subtle)] py-8 ${
                  i < 2 ? "border-b" : ""
                } ${i % 2 === 1 ? "md:border-l md:pl-8" : "md:pr-8"}`}
              >
                <p className="text-status tabular-nums text-primary">
                  0{i + 1}
                </p>
                <h2 className="mt-3 font-display text-title text-on-surface">
                  {layer.title}
                </h2>
                <p className="mt-3 text-body text-on-surface-variant">
                  {layer.body}
                </p>
              </article>
            </Reveal>
          ))}
        </div>

        <Reveal className="mt-16">
          <div className="flex flex-wrap gap-3">
            <ButtonLink href={`${DOCS_URL}compliance/`} external>
              Compliance docs
            </ButtonLink>
            <ButtonLink href="/develop" variant="secondary">
              Start developing
            </ButtonLink>
          </div>
        </Reveal>
      </PageFrame>
    </div>
  );
}
