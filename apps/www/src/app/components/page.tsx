import type { Metadata } from "next";
import { ButtonLink } from "@/components/button-link";
import { Reveal } from "@/components/reveal";
import { DOCS_URL } from "@/lib/site";

export const metadata: Metadata = {
  title: "Components",
  description:
    "Cabin components — planned dual-stack chrome and cabin surfaces. Interactive catalog comes later.",
};

const planned = [
  {
    name: "System bar",
    domain: "Chrome",
    note: "Views-first platform chrome · MVP",
  },
  {
    name: "Status bar",
    domain: "Chrome",
    note: "Glance status · compliance-gated",
  },
  {
    name: "Media now-playing",
    domain: "Media",
    note: "Post-MVP · Compose + Views parity",
  },
  {
    name: "Climate zones",
    domain: "HVAC",
    note: "Planned · driving substitutes",
  },
  {
    name: "Charge session",
    domain: "EV",
    note: "Planned · domain accent locked",
  },
  {
    name: "Vehicle controls",
    domain: "Controls",
    note: "Planned · safety-critical paths",
  },
] as const;

export default function ComponentsPage() {
  return (
    <div className="relative isolate overflow-hidden">
      <div className="pointer-events-none absolute inset-0 -z-10">
        <div className="absolute inset-0 bg-background" />
        <div className="absolute bottom-[-10%] right-[-5%] h-[50vmin] w-[50vmin] rounded-full bg-[radial-gradient(circle,var(--hero-glow-a),transparent_70%)] blur-3xl opacity-60" />
      </div>

      <div className="mx-auto max-w-6xl px-5 pb-24 pt-16 sm:px-8 sm:pt-24">
        <Reveal>
          <div className="inline-flex items-center gap-2 rounded-md border border-[var(--outline-subtle)] bg-surface px-3 py-1.5">
            <span className="h-1.5 w-1.5 rounded-full bg-warning" />
            <span className="text-status text-on-surface-variant">
              Planned — no interactive catalog in v1
            </span>
          </div>
          <h1 className="mt-6 max-w-3xl font-display text-display text-on-surface text-balance">
            Components
          </h1>
          <p className="mt-5 max-w-2xl text-body text-on-surface-variant text-balance">
            Dual-stack cabin surfaces with shared states, actions, and
            compliance gates. Specs land in docs first; live demos follow MVP
            chrome.
          </p>
        </Reveal>

        <ul className="mt-16 grid gap-4 sm:grid-cols-2 lg:grid-cols-3">
          {planned.map((item, i) => (
            <Reveal key={item.name} delayMs={i * 50}>
              <li className="h-full rounded-xl border border-[var(--outline-subtle)] bg-surface/80 p-5">
                <p className="text-status uppercase tracking-[0.12em] text-on-surface-variant">
                  {item.domain}
                </p>
                <h2 className="mt-2 font-display text-title text-on-surface">
                  {item.name}
                </h2>
                <p className="mt-2 text-status text-on-surface-variant">
                  {item.note}
                </p>
              </li>
            </Reveal>
          ))}
        </ul>

        <Reveal className="mt-16">
          <div className="flex flex-wrap gap-3">
            <ButtonLink href={`${DOCS_URL}components/`} external>
              Component specs
            </ButtonLink>
            <ButtonLink href="/foundations" variant="secondary">
              Foundations teaser
            </ButtonLink>
          </div>
        </Reveal>
      </div>
    </div>
  );
}
