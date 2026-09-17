/**
 * Dense gallery demo stages — full-bleed / two-up, cabin bezel chrome.
 * No sparse right-column void; demos fill the stage.
 */

import type { ReactNode } from "react";

export function DemoStage({
  children,
  className = "",
}: {
  children: ReactNode;
  className?: string;
}) {
  return (
    <div
      className={`overflow-hidden rounded-xl border border-[var(--outline-subtle)] bg-[var(--hmi-bezel)] p-2 shadow-elev1 sm:p-2.5 ${className}`}
    >
      {children}
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
    <DemoStage>
      <div className="overflow-hidden rounded-lg border border-[var(--outline-subtle)] bg-surface">
        {status}
        <div className="min-h-[7.5rem] bg-[var(--surface)] px-4 py-5 sm:min-h-[8.5rem] sm:px-5">
          {children ?? (
            <p className="max-w-md text-status text-on-surface-variant">
              Status above · System below — container / outline / onContainer
              from the scheme.
            </p>
          )}
        </div>
        {system}
      </div>
    </DemoStage>
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
      <p className="text-status font-semibold uppercase tracking-[0.12em] text-on-surface-variant">
        {label}
      </p>
      {children}
    </div>
  );
}
