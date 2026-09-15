"use client";

import { useScheme } from "@/components/scheme-provider";

/**
 * Stylized cabin HMI preview — product visual for the hero.
 * Chrome roles: status / content / system. Illustrative only; not a live catalog.
 */
export function CabinHmiPreview() {
  const { scheme } = useScheme();

  return (
    <div
      className="relative mx-auto w-full max-w-xl animate-fade-rise lg:max-w-none"
      style={{ animationDelay: "80ms" }}
      aria-hidden
    >
      <div className="relative overflow-hidden rounded-xl border border-[var(--outline-subtle)] bg-[var(--hmi-bezel)] shadow-elev2">
        {/* Status bar — glance chrome */}
        <div className="flex h-10 items-center justify-between bg-[var(--surface-variant)] px-3.5">
          <div className="flex items-center gap-2.5">
            <span className="h-1.5 w-1.5 rounded-full bg-success" />
            <span className="text-status tabular-nums tracking-wide text-on-surface">
              P · 72°F
            </span>
            <span className="hidden h-3 w-px bg-[var(--outline-subtle)] sm:block" />
            <span className="hidden items-center gap-1.5 sm:inline-flex">
              <span className="h-1.5 w-1.5 rounded-full bg-warning" />
              <span className="text-status text-warning">Lane assist</span>
            </span>
          </div>
          <div className="flex items-center gap-2.5 text-on-surface-variant">
            <SignalBars />
            <span className="text-status tabular-nums text-on-surface">12:41</span>
            <Battery />
          </div>
        </div>

        {/* Content plane — flat high-contrast layers, no glass stack */}
        <div className="min-h-[248px] bg-[var(--surface)] sm:min-h-[288px] lg:min-h-[304px]">
          <div className="grid gap-3 p-3.5 sm:grid-cols-[1.15fr_0.85fr] sm:gap-3.5 sm:p-4">
            <div className="rounded-lg border border-[var(--outline-subtle)] bg-[var(--surface-high)] p-4 sm:p-5">
              <p className="text-status font-semibold uppercase tracking-[0.12em] text-on-surface-variant">
                Now playing
              </p>
              <p className="mt-2.5 font-display text-title text-on-surface">
                Quiet roads
              </p>
              <p className="mt-1 text-status text-on-surface-variant">
                Cabin Media · glance layout
              </p>
              <div className="mt-5 h-1 overflow-hidden rounded-sm bg-[var(--surface-variant)]">
                <div
                  className="h-full w-[62%] origin-left rounded-sm bg-[var(--media)] animate-hmi-line"
                  style={{ animationDelay: "200ms" }}
                />
              </div>
              <div className="mt-4 flex items-center gap-2">
                <ControlChip label="Prev" />
                <ControlChip label="Play" accent="media" />
                <ControlChip label="Next" />
              </div>
            </div>

            <div className="grid gap-3 sm:gap-3.5">
              <div className="rounded-lg border border-[var(--outline-subtle)] bg-[var(--surface-high)] p-4">
                <div className="flex items-center justify-between">
                  <p className="text-status font-semibold uppercase tracking-[0.12em] text-on-surface-variant">
                    Climate
                  </p>
                  <span className="h-2 w-2 rounded-full bg-climate" />
                </div>
                <p className="mt-2 font-display text-[2.25rem] leading-none tabular-nums text-on-surface">
                  21°
                </p>
                <p className="mt-2 text-status text-on-surface-variant">
                  Driver zone
                </p>
              </div>

              <div className="rounded-lg border border-[var(--outline-subtle)] bg-[var(--surface-high)] p-4">
                <p className="text-status font-semibold uppercase tracking-[0.12em] text-on-surface-variant">
                  Charge
                </p>
                <div className="mt-2 flex items-baseline gap-2">
                  <p className="font-display text-title tabular-nums text-on-surface">
                    78%
                  </p>
                  <span className="text-status text-charging">Charging</span>
                </div>
                <div className="mt-3 h-1 overflow-hidden rounded-sm bg-[var(--surface-variant)]">
                  <div
                    className="h-full w-[78%] origin-left rounded-sm bg-charging animate-hmi-line"
                    style={{ animationDelay: "260ms" }}
                  />
                </div>
              </div>
            </div>
          </div>
        </div>

        {/* System bar — AAOS-like dock */}
        <div className="flex h-14 items-stretch justify-around border-t border-[var(--outline-subtle)] bg-[var(--surface-variant)] px-1">
          {["Home", "Maps", "Media", "HVAC", "Apps"].map((label, i) => {
            const active = i === 2;
            return (
              <div
                key={label}
                className="flex min-h-[48px] min-w-[52px] flex-1 flex-col items-center justify-center gap-1 rounded-md"
                style={{
                  background: active
                    ? "color-mix(in srgb, var(--primary) 16%, transparent)"
                    : undefined,
                }}
              >
                <span
                  className="h-1.5 w-4 rounded-sm"
                  style={{
                    background: active
                      ? "var(--primary)"
                      : "var(--on-surface-variant)",
                  }}
                />
                <span
                  className={`text-[0.6875rem] leading-none ${
                    active ? "text-on-surface" : "text-on-surface-variant"
                  }`}
                >
                  {label}
                </span>
              </div>
            );
          })}
        </div>
      </div>

      <p className="mt-3 text-center text-status text-on-surface-variant">
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
      ? "bg-[var(--media)] text-[var(--on-primary)]"
      : "bg-[var(--surface-variant)] text-on-surface";
  return (
    <span className={`rounded-md px-3 py-1.5 text-status ${bg}`}>{label}</span>
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
