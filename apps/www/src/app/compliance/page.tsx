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

const MODES = ["Parked", "Idling", "Moving", "Restricted"] as const;

type Gate = "Allow" | "Substitute" | "Block";

/** Mirrors CabinRestrictionEngine's default matrix (cabin-compliance). */
const matrix: readonly { task: string; detail: string; gates: readonly Gate[] }[] = [
  {
    task: "Single-tap climate and body",
    detail: "Temp step, fan, seat heat, defrost, lock, lights, drive mode",
    gates: ["Allow", "Allow", "Allow", "Allow"],
  },
  {
    task: "Media transport",
    detail: "Play, pause, skip",
    gates: ["Allow", "Allow", "Allow", "Allow"],
  },
  {
    task: "Multi-step setup",
    detail: "Climate zones and schedules, windows, mirrors, seat position",
    gates: ["Allow", "Allow", "Block", "Block"],
  },
  {
    task: "Media source and browse",
    detail: "Source picker, seek, queue",
    gates: ["Allow", "Substitute", "Block", "Block"],
  },
  {
    task: "Keyboard and filtering",
    detail: "Text entry, sort, filter",
    gates: ["Allow", "Block", "Block", "Block"],
  },
  {
    task: "Parked-only content",
    detail: "Video, lyrics, long-form setup",
    gates: ["Allow", "Block", "Block", "Block"],
  },
];

const gateClass: Record<Gate, string> = {
  Allow: "text-on-surface",
  Substitute: "text-on-surface-variant italic",
  Block: "text-on-surface-variant line-through decoration-[var(--outline-subtle)]",
};

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
          <SectionHeading
            kicker="Restriction Engine"
            title="One matrix, four drive states."
          />
          <p className="mt-4 max-w-2xl text-body text-on-surface-variant text-balance">
            Widgets declare an interaction and ask the engine. Single-tap
            controls stay available at speed — defrost is never blocked —
            while multi-step tasks fail closed. On AAOS, an adapter maps
            CarUxRestrictions into these states; if the car service is
            missing, the state is unknown and treated as Restricted.
          </p>
        </Reveal>

        <Reveal className="mt-8" delayMs={40}>
          <InstrumentWell>
            <div className="overflow-x-auto">
              <table className="w-full min-w-[40rem] border-collapse text-left">
                <thead>
                  <tr className="border-b border-[var(--outline-subtle)]">
                    <th scope="col" className="kicker px-5 py-4 font-semibold text-on-surface-variant">
                      Task
                    </th>
                    {MODES.map((mode) => (
                      <th
                        key={mode}
                        scope="col"
                        className="kicker px-4 py-4 font-semibold text-on-surface-variant"
                      >
                        {mode}
                      </th>
                    ))}
                  </tr>
                </thead>
                <tbody>
                  {matrix.map((row) => (
                    <tr
                      key={row.task}
                      className="border-b border-[var(--outline-subtle)] last:border-b-0"
                    >
                      <th scope="row" className="px-5 py-4 font-normal">
                        <span className="block text-label text-on-surface">
                          {row.task}
                        </span>
                        <span className="mt-1 block text-status text-on-surface-variant">
                          {row.detail}
                        </span>
                      </th>
                      {row.gates.map((gate, i) => (
                        <td
                          key={MODES[i]}
                          className={`px-4 py-4 text-status ${gateClass[gate]}`}
                        >
                          {gate}
                        </td>
                      ))}
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
            <p className="border-t border-[var(--outline-subtle)] px-5 py-4 text-status text-on-surface-variant">
              Default policy. Programs can tighten it without forking widgets.
              Unknown vehicle state uses the Restricted column.
            </p>
          </InstrumentWell>
        </Reveal>

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
