"use client";

/**
 * Cabin control surface — connected segments, not a chip cluster.
 * Used for scheme, token groups, and HMI fixture rails.
 */

export type SegmentOption<T extends string> = {
  id: T;
  label: string;
};

export function SegmentedControl<T extends string>({
  ariaLabel,
  value,
  onChange,
  options,
  size = "md",
  fill = "primary",
}: {
  ariaLabel: string;
  value: T;
  onChange: (id: T) => void;
  options: readonly SegmentOption<T>[];
  size?: "sm" | "md";
  /** Active fill: primary action vs quiet surface. */
  fill?: "primary" | "quiet";
}) {
  const pad = size === "sm" ? "px-3 py-1.5" : "px-3.5 py-2";

  return (
    <div
      className="inline-flex max-w-full flex-wrap gap-px overflow-hidden rounded-md border border-[var(--outline-subtle)] bg-[var(--outline-subtle)] p-px"
      role="group"
      aria-label={ariaLabel}
    >
      {options.map((option) => {
        const active = option.id === value;
        const activeClass =
          fill === "primary"
            ? "bg-primary text-on-primary"
            : "bg-[var(--control-fill)] text-on-surface";
        return (
          <button
            key={option.id}
            type="button"
            aria-pressed={active}
            onClick={() => onChange(option.id)}
            className={`${pad} min-h-[2.5rem] shrink-0 text-status transition-colors duration-200 ease-cabin ${
              active
                ? activeClass
                : "bg-[var(--control-fill)] text-on-surface-variant hover:text-on-surface"
            }`}
          >
            {option.label}
          </button>
        );
      })}
    </div>
  );
}
