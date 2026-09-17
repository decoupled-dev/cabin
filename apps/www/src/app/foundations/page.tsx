import type { Metadata } from "next";
import { ButtonLink } from "@/components/button-link";
import {
  ElevationDemo,
  MotionDemo,
  SpaceScaleDemo,
  TypeScaleDemo,
} from "@/components/foundations-demos";
import { Reveal } from "@/components/reveal";
import { TokenExplorer } from "@/components/token-explorer";
import { DOCS_URL } from "@/lib/site";
import {
  DOCS_FOUNDATIONS,
  DOCS_TOKENS,
  FOUNDATIONS_NAV,
} from "@/lib/tokens";

export const metadata: Metadata = {
  title: "Foundations",
  description:
    "Cabin foundations gallery — live token explorer, color roles, type, space, elevation, and motion from generated design tokens.",
};

function SectionIntro({
  id,
  eyebrow,
  title,
  body,
}: {
  id: string;
  eyebrow: string;
  title: string;
  body: string;
}) {
  return (
    <div id={id} className="scroll-mt-28">
      <p className="text-status font-semibold uppercase tracking-[0.14em] text-on-surface-variant">
        {eyebrow}
      </p>
      <h2 className="mt-3 max-w-2xl font-display text-headline text-on-surface text-balance">
        {title}
      </h2>
      <p className="mt-3 max-w-2xl text-body text-on-surface-variant text-balance">
        {body}
      </p>
    </div>
  );
}

export default function FoundationsPage() {
  return (
    <div className="bg-background">
      {/* One composition: brand-adjacent foundations pitch + explorer CTA */}
      <section className="border-b border-[var(--outline-subtle)]">
        <div className="mx-auto max-w-6xl px-5 pb-14 pt-16 sm:px-8 sm:pb-16 sm:pt-24">
          <Reveal>
            <p className="text-status font-semibold uppercase tracking-[0.14em] text-on-surface-variant">
              Foundations
            </p>
            <h1 className="mt-4 max-w-3xl font-display text-display text-on-surface text-balance">
              The grammar of the cabin
            </h1>
            <p className="mt-5 max-w-2xl text-body text-on-surface-variant text-balance">
              Color, type, space, elevation, and motion — packaged as tokens so
              Compose and Views resolve the same meaning. Explore the live
              scheme below; docs stay one click away.
            </p>
            <div className="mt-8 flex flex-wrap items-center gap-3">
              <ButtonLink href="#explorer">Open token explorer</ButtonLink>
              <ButtonLink
                href={`${DOCS_URL}${DOCS_FOUNDATIONS}`}
                variant="secondary"
                external
              >
                Foundations docs
              </ButtonLink>
            </div>
          </Reveal>
        </div>
      </section>

      {/* Quiet in-page anchors — one job: orient within foundations */}
      <nav
        aria-label="Foundations sections"
        className="sticky top-16 z-30 border-b border-[var(--outline-subtle)] bg-[var(--nav-bg)] backdrop-blur-md"
      >
        <div className="mx-auto flex max-w-6xl gap-1 overflow-x-auto px-5 py-2.5 sm:px-8">
          {FOUNDATIONS_NAV.map((item) => (
            <a
              key={item.href}
              href={item.href}
              className="shrink-0 rounded-md px-3 py-2 text-status text-on-surface-variant transition-colors duration-200 ease-cabin hover:bg-[var(--surface-high)] hover:text-on-surface"
            >
              {item.label}
            </a>
          ))}
        </div>
      </nav>

      <div className="mx-auto max-w-6xl px-5 pb-24 pt-14 sm:px-8 sm:pt-16">
        <Reveal>
          <SectionIntro
            id="explorer"
            eyebrow="Live tokens"
            title="Scheme roles update with day and night"
            body="Toggle the scheme to see outline, container, and safety-locked warning, error, and charging resolve from generated CSS — no hand hex."
          />
          <div className="mt-10">
            <TokenExplorer />
          </div>
        </Reveal>

        <Reveal className="mt-24">
          <SectionIntro
            id="color"
            eyebrow="Color"
            title="Semantic meaning before brand paint"
            body="Primary carries forest brand. Outline and container keep chrome legible. Warning, error, and charging stay locked at night — never soft-washed into primary."
          />
          <ul className="mt-10 grid gap-3 sm:grid-cols-2 lg:grid-cols-4">
            {(
              [
                {
                  label: "Primary",
                  css: "var(--primary)",
                  on: "var(--on-primary)",
                },
                {
                  label: "Container",
                  css: "var(--container)",
                  on: "var(--on-container)",
                },
                {
                  label: "Outline",
                  css: "var(--outline)",
                  on: "var(--on-surface)",
                },
                {
                  label: "Warning · locked",
                  css: "var(--warning)",
                  on: "var(--on-surface)",
                },
              ] as const
            ).map((swatch) => (
              <li
                key={swatch.label}
                className="overflow-hidden rounded-lg border border-[var(--outline-subtle)]"
              >
                <div
                  className="flex h-24 items-end p-3"
                  style={{ background: swatch.css, color: swatch.on }}
                >
                  <span className="text-status font-semibold">{swatch.label}</span>
                </div>
              </li>
            ))}
          </ul>
          <p className="mt-6 text-status text-on-surface-variant">
            Domain accents (charging, climate, media) stay off body copy. See{" "}
            <a
              href={`${DOCS_URL}${DOCS_TOKENS}`}
              className="text-primary underline-offset-4 hover:underline"
              rel="noreferrer"
            >
              tokens docs
            </a>
            .
          </p>
        </Reveal>

        <Reveal className="mt-24">
          <SectionIntro
            id="type"
            eyebrow="Typography"
            title="Glance roles from display to status"
            body="Sizes and weights come from cabin type-role tokens. Tabular figures for range, speed, and temperature. Marketing display type sits on top; token scale drives product UI."
          />
          <div className="mt-10 rounded-xl border border-[var(--outline-subtle)] bg-surface px-5 py-6 sm:px-6">
            <TypeScaleDemo />
          </div>
        </Reveal>

        <Reveal className="mt-24">
          <SectionIntro
            id="space"
            eyebrow="Space & touch"
            title="Steps that preserve automotive minima"
            body="Spacing tokens keep driver-zone separation generous. Touch minimum stays a hard floor — dense chrome never shrinks targets below compliance baselines."
          />
          <div className="mt-10 rounded-xl border border-[var(--outline-subtle)] bg-surface px-5 py-2 sm:px-6">
            <SpaceScaleDemo />
          </div>
        </Reveal>

        <Reveal className="mt-24">
          <SectionIntro
            id="elevation"
            eyebrow="Elevation"
            title="Flat layers over deep shadow stacks"
            body="Cabin prefers high-contrast planes. Elevation tokens are spare; presentation shadows on this site mix from surface roles — not decorative glass."
          />
          <div className="mt-10">
            <ElevationDemo />
          </div>
        </Reveal>

        <Reveal className="mt-24">
          <SectionIntro
            id="motion"
            eyebrow="Motion"
            title="Mechanical and precise"
            body="Fast for press, medium for park transitions, slow only when earned. Decorative motion yields while driving — compliance shapes the timeline."
          />
          <div className="mt-10 rounded-xl border border-[var(--outline-subtle)] bg-surface px-5 py-2 sm:px-6">
            <MotionDemo />
          </div>
        </Reveal>

        <Reveal className="mt-24">
          <div className="flex flex-col gap-6 border-t border-[var(--outline-subtle)] pt-8 sm:flex-row sm:items-center sm:justify-between">
            <div>
              <h2 className="font-display text-title text-on-surface">
                Same names in Android, Compose, and CSS
              </h2>
              <p className="mt-2 max-w-xl text-status text-on-surface-variant">
                Codegen keeps semantic parity. Regenerate with{" "}
                <code className="text-on-surface">
                  python3 tools/generate_cabin_tokens.py
                </code>
                .
              </p>
            </div>
            <div className="flex flex-wrap gap-3">
              <ButtonLink href="/components" variant="secondary">
                Components gallery
              </ButtonLink>
              <ButtonLink href={`${DOCS_URL}${DOCS_FOUNDATIONS}`} external>
                Read foundations docs
              </ButtonLink>
            </div>
          </div>
        </Reveal>
      </div>
    </div>
  );
}
