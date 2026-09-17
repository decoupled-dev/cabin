import type { Metadata } from "next";
import { ButtonLink } from "@/components/button-link";
import { StatusBarDemo, SystemBarDemo } from "@/components/demo-chrome-bars";
import { ClimateTileDemo } from "@/components/demo-climate-tile";
import { MediaNowPlayingDemo } from "@/components/demo-media-now-playing";
import { Reveal } from "@/components/reveal";
import { DOCS_URL } from "@/lib/site";
import {
  COMPONENTS_NAV,
  DOCS_CLIMATE_TILE,
  DOCS_MEDIA_NOW,
  DOCS_STATUS_BAR,
  DOCS_SYSTEM_BAR,
} from "@/lib/demo-signal";

export const metadata: Metadata = {
  title: "Components",
  description:
    "Cabin components gallery — live System/Status bar, ClimateTile, and MediaNowPlaying demos from generated tokens.",
};

const planned = [
  {
    name: "Charge session",
    domain: "EV",
    note: "Domain accent locked · planned",
  },
  {
    name: "Vehicle controls",
    domain: "Controls",
    note: "Safety-critical paths · planned",
  },
  {
    name: "Media browse / queue",
    domain: "Media",
    note: "Driving substitutes · planned",
  },
] as const;

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

export default function ComponentsPage() {
  return (
    <div className="bg-background">
      <section className="border-b border-[var(--outline-subtle)]">
        <div className="mx-auto max-w-6xl px-5 pb-14 pt-16 sm:px-8 sm:pb-16 sm:pt-24">
          <Reveal>
            <p className="text-status font-semibold uppercase tracking-[0.14em] text-on-surface-variant">
              Components
            </p>
            <h1 className="mt-4 max-w-3xl font-display text-display text-on-surface text-balance">
              Cabin surfaces, live
            </h1>
            <p className="mt-5 max-w-2xl text-body text-on-surface-variant text-balance">
              Web HMI demos of Cabin chrome and domain tiles — same semantic
              tokens as Android Views and Compose. Toggle day/night in the
              header; probe drive state and honest Signals below.
            </p>
            <div className="mt-8 flex flex-wrap items-center gap-3">
              <ButtonLink href="#chrome">Browse demos</ButtonLink>
              <ButtonLink href="/foundations" variant="secondary">
                Foundations
              </ButtonLink>
            </div>
          </Reveal>
        </div>
      </section>

      <nav
        aria-label="Components sections"
        className="sticky top-16 z-30 border-b border-[var(--outline-subtle)] bg-[var(--nav-bg)] backdrop-blur-md"
      >
        <div className="mx-auto flex max-w-6xl gap-1 overflow-x-auto px-5 py-2.5 sm:px-8">
          {COMPONENTS_NAV.map((item) => (
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
            id="chrome"
            eyebrow="Chrome · MVP"
            title="System and Status bars"
            body="Persistent wayfinding and glance status. Container / outline / onContainer from scheme tokens. Warning and charging stay safety-locked at night."
          />
          <div className="mt-10 grid gap-12 lg:grid-cols-2">
            <div>
              <h3 className="mb-4 font-display text-title text-on-surface">
                Status bar
              </h3>
              <StatusBarDemo />
              <a
                href={`${DOCS_URL}${DOCS_STATUS_BAR}`}
                className="mt-4 inline-block text-status text-primary underline-offset-4 hover:underline"
                rel="noreferrer"
              >
                Status bar spec
              </a>
            </div>
            <div>
              <h3 className="mb-4 font-display text-title text-on-surface">
                System bar
              </h3>
              <SystemBarDemo />
              <a
                href={`${DOCS_URL}${DOCS_SYSTEM_BAR}`}
                className="mt-4 inline-block text-status text-primary underline-offset-4 hover:underline"
                rel="noreferrer"
              >
                System bar spec
              </a>
            </div>
          </div>
        </Reveal>

        <Reveal className="mt-24">
          <SectionIntro
            id="climate"
            eyebrow="Climate · Alpha"
            title="ClimateTile"
            body="One zone at a glance — temp, fan, seat heat. Climate accent is a thin mark only. Adjustments Block while Moving; values stay glanceable (RE-quiet)."
          />
          <div className="mt-10 max-w-xl">
            <ClimateTileDemo />
          </div>
          <a
            href={`${DOCS_URL}${DOCS_CLIMATE_TILE}`}
            className="mt-6 inline-block text-status text-primary underline-offset-4 hover:underline"
            rel="noreferrer"
          >
            ClimateTile Android spec
          </a>
        </Reveal>

        <Reveal className="mt-24">
          <SectionIntro
            id="media"
            eyebrow="Media · Alpha"
            title="MediaNowPlaying"
            body="Artwork well, metadata, transport, source. Progress only when both Signals are live — never invent telemetry. Media accent marks source; never washes the chrome."
          />
          <div className="mt-10 max-w-2xl">
            <MediaNowPlayingDemo />
          </div>
          <a
            href={`${DOCS_URL}${DOCS_MEDIA_NOW}`}
            className="mt-6 inline-block text-status text-primary underline-offset-4 hover:underline"
            rel="noreferrer"
          >
            MediaNowPlaying Android spec
          </a>
        </Reveal>

        <Reveal className="mt-24">
          <SectionIntro
            id="planned"
            eyebrow="Later"
            title="Not in this gallery yet"
            body="Full component inventory stays out of scope for this wave. Specs land in docs first."
          />
          <ul className="mt-10 grid gap-8 sm:grid-cols-3">
            {planned.map((item) => (
              <li
                key={item.name}
                className="border-t border-[var(--outline-subtle)] pt-5"
              >
                <p className="text-status uppercase tracking-[0.12em] text-on-surface-variant">
                  {item.domain}
                </p>
                <h3 className="mt-2 font-display text-title text-on-surface">
                  {item.name}
                </h3>
                <p className="mt-2 text-status text-on-surface-variant">
                  {item.note}
                </p>
              </li>
            ))}
          </ul>
        </Reveal>

        <Reveal className="mt-24">
          <div className="flex flex-col gap-6 border-t border-[var(--outline-subtle)] pt-8 sm:flex-row sm:items-center sm:justify-between">
            <div>
              <h2 className="font-display text-title text-on-surface">
                Foundations first, then components
              </h2>
              <p className="mt-2 max-w-xl text-status text-on-surface-variant">
                Tokens and schemes feed every demo. Explore the live token
                explorer, then return here.
              </p>
            </div>
            <div className="flex flex-wrap gap-3">
              <ButtonLink href="/foundations#explorer" variant="secondary">
                Token explorer
              </ButtonLink>
              <ButtonLink href={`${DOCS_URL}components/`} external>
                Component specs
              </ButtonLink>
            </div>
          </div>
        </Reveal>
      </div>
    </div>
  );
}
