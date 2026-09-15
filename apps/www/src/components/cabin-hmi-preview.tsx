"use client";

import { useScheme } from "@/components/scheme-provider";

/**
 * Stylized cabin HMI preview — product visual for the hero.
 * Illustrative only; not a live catalog.
 */
export function CabinHmiPreview() {
  const { scheme } = useScheme();

  return (
    <div
      className="relative mx-auto w-full max-w-4xl animate-fade-rise"
      style={{ animationDelay: "180ms" }}
      aria-hidden
    >
      <div className="absolute -inset-8 rounded-[2rem] bg-[radial-gradient(ellipse_at_center,var(--hero-glow-a),transparent_65%)] opacity-80 blur-2xl" />
      <div className="relative overflow-hidden rounded-[1.35rem] border border-[var(--outline-subtle)] bg-[var(--hmi-bezel)] shadow-elev2">
        {/* Status bar */}
        <div className="flex h-11 items-center justify-between border-b border-[var(--outline-subtle)] bg-[var(--surface)] px-4">
          <div className="flex items-center gap-3">
            <span className="h-2 w-2 rounded-full bg-success" />
            <span className="font-display text-status tracking-wide text-on-surface">
              72° · Park
            </span>
          </div>
          <div className="flex items-center gap-3 text-on-surface-variant">
            <SignalBars />
            <span className="text-status tabular-nums">12:41</span>
            <Battery />
          </div>
        </div>

        {/* Content plane */}
        <div className="relative min-h-[280px] bg-[var(--surface)] sm:min-h-[340px]">
          <div className="absolute inset-0 cabin-grid opacity-40" />
          <div className="absolute inset-0 bg-[radial-gradient(ellipse_at_30%_20%,var(--hero-glow-c),transparent_50%),radial-gradient(ellipse_at_80%_70%,var(--hero-glow-b),transparent_45%)]" />

          <div className="relative grid gap-4 p-5 sm:grid-cols-[1.1fr_0.9fr] sm:p-6">
            <div
              className="rounded-xl border border-[var(--outline-subtle)] bg-[var(--hmi-glass)] p-5 backdrop-blur-sm animate-fade-rise"
              style={{ animationDelay: "320ms" }}
            >
              <p className="text-status font-semibold uppercase tracking-[0.14em] text-on-surface-variant">
                Now playing
              </p>
              <p className="mt-3 font-display text-headline text-on-surface">
                Quiet roads
              </p>
              <p className="mt-1 text-status text-on-surface-variant">
                Cabin Media · glance layout
              </p>
              <div className="mt-6 h-1.5 overflow-hidden rounded-full bg-[var(--surface-variant)]">
                <div
                  className="h-full w-[62%] origin-left rounded-full bg-[var(--media)] animate-hmi-line"
                  style={{ animationDelay: "500ms" }}
                />
              </div>
              <div className="mt-5 flex items-center gap-3">
                <ControlChip label="Prev" />
                <ControlChip label="Play" accent="media" />
                <ControlChip label="Next" />
              </div>
            </div>

            <div className="grid gap-4">
              <div
                className="rounded-xl border border-[var(--outline-subtle)] bg-[var(--hmi-glass)] p-5 animate-fade-rise"
                style={{ animationDelay: "420ms" }}
              >
                <p className="text-status font-semibold uppercase tracking-[0.14em] text-on-surface-variant">
                  Climate
                </p>
                <div className="mt-3 flex items-end justify-between">
                  <p className="font-display text-[2.75rem] leading-none tabular-nums text-on-surface">
                    21°
                  </p>
                  <span
                    className="mb-1 h-2.5 w-2.5 rounded-full bg-climate"
                    style={{ boxShadow: "0 0 16px var(--climate)" }}
                  />
                </div>
                <p className="mt-2 text-status text-on-surface-variant">
                  Driver zone · locked meaning
                </p>
              </div>

              <div
                className="rounded-xl border border-[var(--outline-subtle)] bg-[var(--hmi-glass)] p-5 animate-fade-rise"
                style={{ animationDelay: "520ms" }}
              >
                <p className="text-status font-semibold uppercase tracking-[0.14em] text-on-surface-variant">
                  Charge
                </p>
                <div className="mt-3 flex items-baseline gap-2">
                  <p className="font-display text-headline tabular-nums text-on-surface">
                    78%
                  </p>
                  <span className="text-status text-charging">Charging</span>
                </div>
                <div className="mt-4 h-1.5 overflow-hidden rounded-full bg-[var(--surface-variant)]">
                  <div
                    className="h-full w-[78%] origin-left rounded-full bg-charging animate-hmi-line"
                    style={{ animationDelay: "640ms" }}
                  />
                </div>
              </div>
            </div>
          </div>
        </div>

        {/* System bar */}
        <div className="flex h-16 items-center justify-around border-t border-[var(--outline-subtle)] bg-[var(--surface-variant)] px-2">
          {["Home", "Maps", "Media", "HVAC", "Apps"].map((label, i) => (
            <div
              key={label}
              className="flex min-h-[48px] min-w-[56px] flex-col items-center justify-center gap-1 rounded-lg px-2"
              style={{
                background:
                  i === 2 ? "color-mix(in srgb, var(--primary) 18%, transparent)" : undefined,
              }}
            >
              <span
                className="h-2 w-2 rounded-sm"
                style={{
                  background:
                    i === 2 ? "var(--primary)" : "var(--on-surface-variant)",
                }}
              />
              <span
                className={`text-[0.6875rem] ${
                  i === 2 ? "text-on-surface" : "text-on-surface-variant"
                }`}
              >
                {label}
              </span>
            </div>
          ))}
        </div>
      </div>

      <p className="mt-4 text-center text-status text-on-surface-variant">
        {scheme === "night" ? "Night scheme" : "Day scheme"} · illustrative HMI
        chrome
      </p>
    </div>
  );
}

function ControlChip({
  label,
  accent,
}: {
  label: string;
  accent?: "media" | "climate" | "charging";
}) {
  const bg =
    accent === "media"
      ? "bg-[var(--media)] text-white"
      : "bg-[var(--surface-variant)] text-on-surface";
  return (
    <span className={`rounded-md px-3 py-2 text-status ${bg}`}>{label}</span>
  );
}

function SignalBars() {
  return (
    <span className="flex items-end gap-0.5">
      {[4, 7, 10, 13].map((h) => (
        <span
          key={h}
          className="w-0.5 rounded-sm bg-current"
          style={{ height: h }}
        />
      ))}
    </span>
  );
}

function Battery() {
  return (
    <span className="relative inline-flex h-3 w-5 items-center rounded-[2px] border border-current px-px">
      <span className="h-1.5 w-3 rounded-[1px] bg-success" />
      <span className="absolute -right-1 top-1/2 h-1.5 w-0.5 -translate-y-1/2 rounded-r-sm bg-current" />
    </span>
  );
}
