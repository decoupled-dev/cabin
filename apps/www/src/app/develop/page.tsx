import type { Metadata } from "next";
import { ButtonLink } from "@/components/button-link";
import { Reveal } from "@/components/reveal";
import { DOCS_URL, GITHUB_URL } from "@/lib/site";

export const metadata: Metadata = {
  title: "Develop",
  description:
    "Get started with Cabin — tokens, Restriction Engine, dual distribution for Gradle apps and Soong build-tree.",
};

const steps = [
  {
    title: "Read the contracts",
    body: "Start with vision, MVP v0.1, and the pre-implementation gate so library scope stays frozen and thin.",
  },
  {
    title: "Tokens + compliance",
    body: "Alpha modules cabin-tokens and cabin-compliance scaffold the Restriction Engine. Theme Kit and Views chrome are planned next.",
  },
  {
    title: "Pick your consumption path",
    body: "Apps: Gradle / Maven coordinates. SystemUI and build-tree: Soong Android.bp — Views-first, never Gradle-implementation into platform chrome.",
  },
] as const;

export default function DevelopPage() {
  return (
    <div className="bg-background">
      <div className="mx-auto max-w-6xl px-5 pb-24 pt-16 sm:px-8 sm:pt-24">
        <Reveal>
          <p className="text-status font-semibold uppercase tracking-[0.14em] text-on-surface-variant">
            Develop
          </p>
          <h1 className="mt-4 max-w-3xl font-display text-display text-on-surface text-balance">
            Build on Cabin
          </h1>
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

        <ol className="mt-20 grid gap-8 md:grid-cols-3">
          {steps.map((step, i) => (
            <Reveal key={step.title} delayMs={Math.min(i * 40, 80)}>
              <li className="border-t border-[var(--outline-subtle)] pt-6">
                <p className="text-status tabular-nums text-primary">
                  Step {i + 1}
                </p>
                <h2 className="mt-3 font-display text-title text-on-surface">
                  {step.title}
                </h2>
                <p className="mt-3 text-body text-on-surface-variant">
                  {step.body}
                </p>
              </li>
            </Reveal>
          ))}
        </ol>

        <Reveal className="mt-20">
          <div className="grid gap-6 border-t border-[var(--outline-subtle)] pt-8 lg:grid-cols-2">
            <div>
              <h2 className="font-display text-headline text-on-surface">
                Dual distribution
              </h2>
              <p className="mt-3 text-body text-on-surface-variant">
                Thin artifacts by design. Samples, catalog, and websites never
                leak into product images.
              </p>
            </div>
            <dl className="grid gap-5 sm:grid-cols-2">
              <div>
                <dt className="text-status font-semibold uppercase tracking-[0.12em] text-on-surface-variant">
                  App developers
                </dt>
                <dd className="mt-2 text-body text-on-surface">
                  Gradle → Maven
                  <br />
                  <span className="text-status text-on-surface-variant">
                    cabin-tokens · cabin-compliance · …
                  </span>
                </dd>
              </div>
              <div>
                <dt className="text-status font-semibold uppercase tracking-[0.12em] text-on-surface-variant">
                  Platform / SystemUI
                </dt>
                <dd className="mt-2 text-body text-on-surface">
                  Soong → Android.bp
                  <br />
                  <span className="text-status text-on-surface-variant">
                    CabinTokens · CabinCompliance · CabinViews
                  </span>
                </dd>
              </div>
            </dl>
          </div>
        </Reveal>
      </div>
    </div>
  );
}
