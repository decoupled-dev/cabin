import { ButtonLink } from "@/components/button-link";
import { CabinHmiPreview } from "@/components/cabin-hmi-preview";
import { Reveal } from "@/components/reveal";
import { InstrumentWell } from "@/components/instrument-frame";
import { PageFrame, SectionHeading } from "@/components/site-chrome";
import { DOCS_URL } from "@/lib/site";

const reasons = [
  {
    title: "Built for the road",
    body: "Driving state, distraction limits, glance budgets, and day/night cabin light are first-class — not afterthoughts.",
  },
  {
    title: "Compose and Views",
    body: "Two skins, one language. Shared tokens, Restriction Engine, and scaffold chrome so system UI and apps stay in parity.",
  },
  {
    title: "OEM brand without forks",
    body: "Remap semantic tokens and extension slots. Keep safety meaning intact when programs specialize.",
  },
] as const;

const pillars = [
  {
    name: "Glanceable",
    copy: "Readable in under a second — at speed, day or night.",
  },
  {
    name: "Quiet",
    copy: "Fewer elements. Stronger hierarchy. Motion that earns trust.",
  },
  {
    name: "Safe by default",
    copy: "Driving and UX restrictions shape the UI — not a bolt-on.",
  },
  {
    name: "One language, two skins",
    copy: "Compose and Views share meaning; OEM brand sits on tokens.",
  },
  {
    name: "Thin by design",
    copy: "Adopt one module without dragging the rest into the image.",
  },
] as const;

export default function HomePage() {
  return (
    <>
      <section className="relative isolate bg-background px-4 pb-10 pt-5 sm:px-6 sm:pb-12 sm:pt-6 lg:px-8 lg:pb-14 lg:pt-7">
        <CabinHmiPreview>
          <h1 className="font-display text-display text-on-surface animate-brand-in">
            Cabin
          </h1>
          <p
            className="mt-4 max-w-lg font-display text-headline text-on-surface animate-fade-rise text-balance"
            style={{ animationDelay: "60ms" }}
          >
            The design platform for the automobile cabin
          </p>
          <p
            className="mt-4 max-w-md text-body text-on-surface-variant animate-fade-rise text-balance"
            style={{ animationDelay: "100ms" }}
          >
            Material Design 3–class craft for Android Automotive OS — calm,
            legible, inevitable. Dual-stack UI with compliance as design
            material.
          </p>
          <div
            className="mt-8 flex flex-wrap items-center gap-3 animate-fade-rise"
            style={{ animationDelay: "140ms" }}
          >
            <ButtonLink href="/develop">Get started</ButtonLink>
            <ButtonLink href="/use-cases" variant="secondary">
              Use cases
            </ButtonLink>
          </div>
        </CabinHmiPreview>
      </section>

      <section className="border-t border-[var(--outline-subtle)] bg-background">
        <PageFrame className="py-16 sm:py-20">
          <Reveal>
            <SectionHeading
              kicker="Why Cabin"
              title="Not Material with bigger buttons — an automotive-native system Android teams already understand."
            />
          </Reveal>
          <Reveal className="mt-10" delayMs={40}>
            <InstrumentWell>
              <div className="grid md:grid-cols-3">
                {reasons.map((item, i) => (
                  <article
                    key={item.title}
                    className={`px-6 py-7 sm:px-7 ${
                      i > 0
                        ? "border-t border-[var(--outline-subtle)] md:border-t-0 md:border-l"
                        : ""
                    }`}
                  >
                    <p className="text-status tabular-nums text-primary">
                      0{i + 1}
                    </p>
                    <h3 className="mt-4 font-display text-title text-on-surface">
                      {item.title}
                    </h3>
                    <p className="mt-3 text-body text-on-surface-variant">
                      {item.body}
                    </p>
                  </article>
                ))}
              </div>
            </InstrumentWell>
          </Reveal>
        </PageFrame>
      </section>

      <section className="border-t border-[var(--outline-subtle)]">
        <PageFrame className="py-16 sm:py-20">
          <Reveal>
            <SectionHeading
              kicker="Product pillars"
              title="Every surface must pass."
            />
          </Reveal>
          <ol className="mt-10 grid gap-0 border-t border-[var(--outline-subtle)] sm:grid-cols-2 lg:grid-cols-5">
            {pillars.map((pillar, i) => (
              <li
                key={pillar.name}
                className="h-full border-b border-[var(--outline-subtle)] py-6 sm:border-r sm:px-5 lg:border-b-0 first:sm:pl-0 last:sm:border-r-0 last:sm:pr-0"
              >
                <Reveal delayMs={Math.min(i * 30, 90)}>
                  <p className="text-status tabular-nums text-primary">
                    0{i + 1}
                  </p>
                  <h3 className="mt-3 font-display text-title text-on-surface">
                    {pillar.name}
                  </h3>
                  <p className="mt-2 text-status text-on-surface-variant">
                    {pillar.copy}
                  </p>
                </Reveal>
              </li>
            ))}
          </ol>
        </PageFrame>
      </section>

      <section className="border-t border-[var(--outline-subtle)] bg-[var(--hmi-bezel)]">
        <PageFrame className="flex flex-col gap-8 py-16 sm:flex-row sm:items-end sm:justify-between sm:py-20">
          <Reveal>
            <SectionHeading
              kicker="Next"
              title="Explore use cases, foundations, or jump to developer docs."
            />
          </Reveal>
          <Reveal delayMs={40}>
            <div className="flex flex-wrap gap-3">
              <ButtonLink href="/use-cases" variant="secondary">
                Use cases
              </ButtonLink>
              <ButtonLink href={DOCS_URL} external>
                Open docs
              </ButtonLink>
            </div>
          </Reveal>
        </PageFrame>
      </section>
    </>
  );
}
