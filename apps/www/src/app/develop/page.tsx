import type { Metadata } from "next";
import { ButtonLink } from "@/components/button-link";
import { Reveal } from "@/components/reveal";
import { InstrumentWell } from "@/components/instrument-frame";
import { PageFrame, SectionHeading } from "@/components/site-chrome";
import { DOCS_URL, GITHUB_URL, KITCHEN_SINK_URL } from "@/lib/site";

export const metadata: Metadata = {
  title: "Develop",
  description:
    "Get started with Cabin — tokens, Restriction Engine, Theme Kit, Experimental kit scaffold, dual Gradle/Soong distribution.",
};

const steps = [
  {
    title: "Read the contracts",
    body: "Start with vision, MVP v0.1, and the pre-implementation gate so library scope stays frozen and thin.",
  },
  {
    title: "Tokens, compliance, chrome",
    body: "Alpha: cabin-tokens, cabin-compliance, cabin-views (Theme Kit + System/Status bars). Experimental: cabin-foundation, cabin-compose kit, cabin-gauges.",
  },
  {
    title: "Pick your consumption path",
    body: "Apps: Gradle / Maven. SystemUI and build-tree: Soong Android.bp — Views-first, never Gradle-implementation into platform chrome. Inspect rows in samples/kitchen-sink (sample only).",
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
                      cabin-compose
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
                  </dd>
                </div>
              </dl>
            </div>
          </InstrumentWell>
        </Reveal>

        <Reveal className="mt-10">
          <p className="max-w-2xl text-body text-on-surface-variant text-balance">
            Inspect inventory rows and screens built from the kit in{" "}
            <a
              href={KITCHEN_SINK_URL}
              className="text-on-surface underline-offset-4 hover:underline"
              rel="noreferrer"
            >
              samples/kitchen-sink
            </a>
            . Gradle sample only — never a cabin-* or SystemUI dependency.
          </p>
        </Reveal>
      </PageFrame>
    </div>
  );
}
