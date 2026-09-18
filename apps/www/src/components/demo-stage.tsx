/**
 * Dense gallery demo stages — full-bleed / two-up, cabin bezel chrome.
 * No sparse right-column void; demos fill the stage.
 */

import type { ReactNode } from "react";
import { InstrumentFrame } from "@/components/instrument-frame";

export function DemoStage({
  children,
  className = "",
}: {
  children: ReactNode;
  className?: string;
}) {
  return (
    <div className={className ? `instrument ${className}` : "instrument"}>
      <div className="m-[7px] overflow-hidden rounded-md border border-[var(--outline-subtle)] bg-surface sm:m-2.5">
        {children}
      </div>
    </div>
  );
}

/** Status + content + system bars in one instrument bezel. */
export function BezelChrome({
  status,
  system,
  children,
}: {
  status: ReactNode;
  system: ReactNode;
  children?: ReactNode;
}) {
  return (
    <InstrumentFrame
      status={status}
      system={system}
      contentClassName="min-h-[10rem] bg-[var(--surface)] px-4 py-5 sm:min-h-[12rem] sm:px-5 sm:py-6"
    >
      {children ?? (
        <p className="max-w-md text-status text-on-surface-variant">
          Status above · System below — container / outline / onContainer from
          the scheme.
        </p>
      )}
    </InstrumentFrame>
  );
}

/** Two-up dense stage — climate | media fills width. */
export function TwoUpStage({
  left,
  right,
}: {
  left: ReactNode;
  right: ReactNode;
}) {
  return (
    <div className="grid gap-3 lg:grid-cols-2 lg:gap-4">
      <DemoStage>{left}</DemoStage>
      <DemoStage>{right}</DemoStage>
    </div>
  );
}

export function DemoRail({
  label,
  children,
}: {
  label: string;
  children: ReactNode;
}) {
  return (
    <div className="mt-4 space-y-3">
      <p className="kicker text-on-surface-variant">{label}</p>
      {children}
    </div>
  );
}
