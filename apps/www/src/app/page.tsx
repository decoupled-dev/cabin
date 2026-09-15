import { ButtonLink } from "@/components/button-link";
import { CabinHmiPreview } from "@/components/cabin-hmi-preview";
import { Reveal } from "@/components/reveal";
import { DOCS_URL } from "@/lib/site";

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
      <section className="relative isolate min-h-[calc(100svh-4rem)] overflow-hidden">
        {/* Full-bleed atmosphere */}
        <div className="pointer-events-none absolute inset-0 -z-10">
          <div className="absolute inset-0 bg-background" />
          <div className="absolute inset-0 bg-[linear-gradient(160deg,transparent_40%,rgba(42,212,143,0.06)_100%)]" />
          <div className="absolute -left-1/4 top-[-10%] h-[75vmin] w-[75vmin] animate-ambient-drift rounded-full bg-[radial-gradient(circle,var(--hero-glow-a),transparent_68%)] blur-3xl" />
          <div
            className="absolute -right-[12%] top-[20%] h-[50vmin] w-[50vmin] animate-soft-pulse rounded-full bg-[radial-gradient(circle,var(--hero-glow-c),transparent_70%)] blur-3xl"
          />
          <div
            className="absolute -right-1/5 bottom-[-5%] h-[58vmin] w-[58vmin] animate-ambient-drift rounded-full bg-[radial-gradient(circle,var(--hero-glow-b),transparent_70%)] blur-3xl"
            style={{ animationDelay: "-5s" }}
          />
          <div className="absolute inset-0 cabin-grid opacity-40" />
          <div className="absolute inset-x-0 bottom-0 h-48 bg-gradient-to-t from-background to-transparent" />
        </div>

        <div className="mx-auto grid max-w-6xl items-center gap-12 px-5 pb-20 pt-14 sm:px-8 sm:pt-20 lg:grid-cols-[minmax(0,1.05fr)_minmax(0,1fr)] lg:gap-10 lg:pb-16 lg:pt-16 xl:gap-14">
          <div className="max-w-xl lg:max-w-none">
            <h1 className="font-display text-display font-extrabold text-on-surface animate-brand-in">
              Cabin
            </h1>
            <p
              className="mt-5 max-w-xl font-display text-headline text-on-surface animate-fade-rise text-balance"
              style={{ animationDelay: "120ms" }}
            >
              The design platform for the automobile cabin
            </p>
            <p
              className="mt-4 max-w-md text-body text-on-surface-variant animate-fade-rise text-balance"
              style={{ animationDelay: "220ms" }}
            >
              Material Design 3–class craft for Android Automotive OS — calm,
              legible, inevitable. Dual-stack UI with compliance as design
              material.
            </p>
            <div
              className="mt-8 flex flex-wrap items-center gap-3 animate-fade-rise"
              style={{ animationDelay: "320ms" }}
            >
              <ButtonLink href="/develop">Get started</ButtonLink>
              <ButtonLink href="/foundations" variant="secondary">
                Foundations
              </ButtonLink>
            </div>
          </div>

          <CabinHmiPreview />
        </div>
      </section>

      <section className="border-t border-[var(--outline-subtle)] bg-surface">
        <div className="mx-auto max-w-6xl px-5 py-20 sm:px-8">
          <Reveal>
            <p className="text-status font-semibold uppercase tracking-[0.14em] text-on-surface-variant">
              Why Cabin
            </p>
            <h2 className="mt-3 max-w-2xl font-display text-headline text-on-surface text-balance">
              Not Material with bigger buttons — an automotive-native system
              Android teams already understand.
            </h2>
          </Reveal>

          <div className="mt-14 grid gap-10 md:grid-cols-3">
            {[
              {
                title: "Built for the road",
                body: "Driving state, distraction limits, glance budgets, and day/night cabin light are first-class — not afterthoughts.",
              },
              {
                title: "Compose and Views",
                body: "Two skins, one language. Shared tokens and compliance so system UI and apps stay in parity.",
              },
              {
                title: "OEM brand without forks",
                body: "Remap semantic tokens and extension slots. Keep safety meaning intact when programs specialize.",
              },
            ].map((item, i) => (
              <Reveal key={item.title} delayMs={i * 80}>
                <h3 className="font-display text-title text-on-surface">
                  {item.title}
                </h3>
                <p className="mt-3 text-body text-on-surface-variant">
                  {item.body}
                </p>
              </Reveal>
            ))}
          </div>
        </div>
      </section>

      <section className="border-t border-[var(--outline-subtle)]">
        <div className="mx-auto max-w-6xl px-5 py-20 sm:px-8">
          <Reveal>
            <p className="text-status font-semibold uppercase tracking-[0.14em] text-on-surface-variant">
              Product pillars
            </p>
            <h2 className="mt-3 font-display text-headline text-on-surface">
              Every surface must pass.
            </h2>
          </Reveal>
          <ol className="mt-12 grid gap-6 sm:grid-cols-2 lg:grid-cols-5">
            {pillars.map((pillar, i) => (
              <Reveal key={pillar.name} delayMs={i * 60}>
                <li className="h-full border-t border-primary/40 pt-5">
                  <p className="text-status tabular-nums text-primary">
                    0{i + 1}
                  </p>
                  <h3 className="mt-3 font-display text-title text-on-surface">
                    {pillar.name}
                  </h3>
                  <p className="mt-2 text-status text-on-surface-variant">
                    {pillar.copy}
                  </p>
                </li>
              </Reveal>
            ))}
          </ol>
        </div>
      </section>

      <section className="border-t border-[var(--outline-subtle)] bg-surface">
        <div className="mx-auto flex max-w-6xl flex-col gap-8 px-5 py-20 sm:flex-row sm:items-end sm:justify-between sm:px-8">
          <Reveal>
            <p className="text-status font-semibold uppercase tracking-[0.14em] text-on-surface-variant">
              Next
            </p>
            <h2 className="mt-3 max-w-xl font-display text-headline text-on-surface text-balance">
              Explore foundations, compliance, or jump to developer docs.
            </h2>
          </Reveal>
          <Reveal delayMs={100}>
            <div className="flex flex-wrap gap-3">
              <ButtonLink href="/compliance" variant="secondary">
                Compliance story
              </ButtonLink>
              <ButtonLink href={DOCS_URL} external>
                Open docs
              </ButtonLink>
            </div>
          </Reveal>
        </div>
      </section>
    </>
  );
}
