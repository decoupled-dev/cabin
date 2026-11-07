import type { Metadata } from "next";
import { ButtonLink } from "@/components/button-link";
import { Reveal } from "@/components/reveal";
import { InstrumentWell } from "@/components/instrument-frame";
import { PageFrame, SectionHeading } from "@/components/site-chrome";
import { CATALOG_URL, DOCS_URL, GITHUB_URL } from "@/lib/site";

export const metadata: Metadata = {
  title: "Develop",
  description:
    "Get started with Cabin — tokens, Restriction Engine, Theme Kit, the Material 3 Compose kit, reference screens, and dual Gradle/Soong distribution.",
};

const steps = [
  {
    title: "Read the contracts",
    body: "Start with vision, MVP v0.1, and ADR 0006 (Material 3 base, six looks, three tiers) so library scope stays deliberate and thin.",
  },
  {
    title: "Tokens, compliance, chrome",
    body: "Alpha: cabin-tokens, cabin-compliance, cabin-views (Theme Kit + System/Status bars). Experimental: cabin-foundation, cabin-compose (Material 3 theme, looks, kit), cabin-screens (Home / Media / Climate), cabin-gauges.",
  },
  {
    title: "Pick your consumption path",
    body: "Apps: Gradle / Maven. SystemUI and build-tree: Soong Android.bp — Views-first, never Gradle-implementation into platform chrome. Compose and screens are app dependencies only.",
  },
] as const;

export default function DevelopPage() {
  return (
    <div className="bg-background">
      <PageFrame className="pb-24 pt-14 sm:pt-20">
        <Reveal>
          <SectionHeading
            as="h1"
            size="display"
            kicker="Develop"
            title="Build on Cabin"
          />
          <p className="mt-5 max-w-2xl text-body text-on-surface-variant text-balance">
            Developer documentation lives on the docs site — this marketing
            surface stays focused on the product story. Deep-link when you are
            ready to integrate.
          </p>
          <div className="mt-9 flex flex-wrap gap-3">
            <ButtonLink href={DOCS_URL} external>
              Open developer docs
            </ButtonLink>
            <ButtonLink href={GITHUB_URL} variant="secondary" external>
              View on GitHub
            </ButtonLink>
          </div>
        </Reveal>

        <ol className="mt-16 grid gap-0 md:grid-cols-3">
          {steps.map((step, i) => (
            <li
              key={step.title}
              className={`border-[var(--outline-subtle)] py-8 ${
                i > 0
                  ? "border-t md:border-t-0 md:border-l md:pl-8"
                  : "md:pr-8"
              } ${i === 1 ? "md:px-8" : ""}`}
            >
              <Reveal delayMs={Math.min(i * 40, 80)}>
                <p className="text-status tabular-nums text-primary">
                  Step {i + 1}
                </p>
                <h2 className="mt-3 font-display text-title text-on-surface">
                  {step.title}
                </h2>
                <p className="mt-3 text-body text-on-surface-variant">
                  {step.body}
                </p>
              </Reveal>
            </li>
          ))}
        </ol>

        <Reveal className="mt-8">
          <InstrumentWell>
            <div className="grid gap-0 lg:grid-cols-2">
              <div className="border-b border-[var(--outline-subtle)] px-6 py-8 lg:border-b-0 lg:border-r">
                <h2 className="font-display text-headline text-on-surface">
                  Dual distribution
                </h2>
                <p className="mt-3 text-body text-on-surface-variant">
                  Thin artifacts by design. Samples, catalog, and websites never
                  leak into product images.
                </p>
              </div>
              <dl className="grid gap-0 sm:grid-cols-2">
                <div className="border-b border-[var(--outline-subtle)] px-6 py-8 sm:border-b-0 sm:border-r">
                  <dt className="kicker text-on-surface-variant">
                    App developers
                  </dt>
                  <dd className="mt-3 text-body text-on-surface">
                    Gradle → Maven
                    <br />
                    <span className="text-status text-on-surface-variant">
                      cabin-tokens · cabin-compliance · cabin-views ·
                      cabin-compose · cabin-screens
                    </span>
                  </dd>
                </div>
                <div className="px-6 py-8">
                  <dt className="kicker text-on-surface-variant">
                    Platform / SystemUI
                  </dt>
                  <dd className="mt-3 text-body text-on-surface">
                    Soong → Android.bp
                    <br />
                    <span className="text-status text-on-surface-variant">
                      CabinTokens · CabinCompliance · CabinViews
                    </span>
                    <br />
                    <span className="text-status text-on-surface-variant">
                      Apps in the tree: CabinCompose · CabinScreens
                    </span>
                  </dd>
                </div>
              </dl>
            </div>
          </InstrumentWell>
        </Reveal>

        <Reveal className="mt-8">
          <InstrumentWell>
            <div className="px-6 py-8">
              <h2 className="font-display text-headline text-on-surface">
                Toward external/cabin
              </h2>
              <p className="mt-3 max-w-2xl text-body text-on-surface-variant">
                The long-term target is an AOSP drop next to car-ui-lib and the
                AAOS OEM design tokens. Look roles use the same Material 3 names
                as those tokens.
              </p>
            </div>
            <dl className="grid border-t border-[var(--outline-subtle)] sm:grid-cols-3">
              {[
                {
                  label: "Verified",
                  value:
                    "All seven Soong modules compile with an AOSP 17 tree's own toolchain, against AOSP prebuilts only.",
                },
                {
                  label: "Enforced",
                  value:
                    "Apache-2.0 headers, no GMS or bundled fonts, token-only color, thin Soong deps — checked by the quality pipeline in CI.",
                },
                {
                  label: "Planned",
                  value:
                    "A full m build in a clean tree, a public API dump, and one OEM design token RRO per look.",
                },
              ].map((cell, i) => (
                <div
                  key={cell.label}
                  className={`px-6 py-6 ${
                    i > 0
                      ? "border-t border-[var(--outline-subtle)] sm:border-t-0 sm:border-l"
                      : ""
                  }`}
                >
                  <dt className="kicker text-on-surface-variant">{cell.label}</dt>
                  <dd className="mt-3 text-status text-on-surface">{cell.value}</dd>
                </div>
              ))}
            </dl>
          </InstrumentWell>
        </Reveal>

        <Reveal className="mt-10">
          <p className="max-w-2xl text-body text-on-surface-variant text-balance">
            See the kit running in{" "}
            <a
              href={CATALOG_URL}
              className="text-on-surface underline-offset-4 hover:underline"
              rel="noreferrer"
            >
              the catalog Showcase
            </a>
            : Home, Media, and Climate in each look, day and night, stepping
            from Parked to Restricted. Sample only — never a cabin-* or
            SystemUI dependency.
          </p>
        </Reveal>
      </PageFrame>
    </div>
  );
}
