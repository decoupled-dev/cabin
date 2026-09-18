import type { ReactNode } from "react";

/**
 * Instrument bezel — status · content · system.
 * Marketing hero and gallery stages share this chrome grammar.
 */

export function InstrumentFrame({
  status,
  system,
  children,
  className = "",
  contentClassName = "",
}: {
  status?: ReactNode;
  system?: ReactNode;
  children: ReactNode;
  className?: string;
  contentClassName?: string;
}) {
  return (
    <div className={`instrument ${className}`}>
      <div className="overflow-hidden rounded-[0.65rem] m-[7px] sm:m-2.5 border border-[var(--outline-subtle)] bg-surface">
        {status}
        <div className={contentClassName}>{children}</div>
        {system}
      </div>
    </div>
  );
}

export function InstrumentWell({
  children,
  className = "",
}: {
  children: ReactNode;
  className?: string;
}) {
  return <div className={`instrument-well ${className}`}>{children}</div>;
}
