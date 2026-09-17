"use client";

import { useState } from "react";

/**
 * Web HMI demos of Cabin System / Status bars.
 * Uses generated component tokens — illustrative chrome, not Android SystemUI.
 */

const SYSTEM_SLOTS = [
  { id: "home", label: "Home", region: "leading" as const },
  { id: "maps", label: "Maps", region: "leading" as const },
  { id: "media", label: "Media", region: "center" as const, active: true },
  { id: "hvac", label: "HVAC", region: "center" as const },
  { id: "apps", label: "Apps", region: "trailing" as const, gated: true },
];

export function StatusBarDemo() {
  const [emphasis, setEmphasis] = useState<"normal" | "warning" | "charging">(
    "normal",
  );

  return (
    <div>
      <div
        className="flex items-center justify-between border border-[var(--outline)] bg-[var(--container)] px-[var(--cabin-component-status-bar-item-gap)] text-[var(--on-container)]"
        style={{
          height: "var(--cabin-component-status-bar-height)",
          gap: "var(--cabin-component-status-bar-item-gap)",
        }}
        role="status"
        aria-label="Status bar preview"
      >
        <div
          className="flex items-center"
          style={{ gap: "var(--cabin-component-status-bar-item-gap)" }}
        >
          <span
            className="inline-flex items-center justify-center"
            style={{
              width: "var(--cabin-component-status-bar-icon-size)",
              height: "var(--cabin-component-status-bar-icon-size)",
            }}
            aria-hidden
          >
            <SignalGlyph />
          </span>
          <span className="text-status tabular-nums tracking-wide">P · 72°F</span>
          {emphasis === "warning" ? (
            <span className="inline-flex items-center gap-1.5 text-status text-warning">
              <span className="h-1.5 w-1.5 rounded-full bg-warning" />
              Lane assist
            </span>
          ) : null}
          {emphasis === "charging" ? (
            <span className="inline-flex items-center gap-1.5 text-status text-charging">
              <span className="h-1.5 w-1.5 rounded-full bg-charging" />
              78% charging
            </span>
          ) : (
            <span className="text-status tabular-nums text-[var(--on-container)]/80">
              78%
            </span>
          )}
        </div>
        <div
          className="flex items-center text-[var(--on-container)]/80"
          style={{ gap: "var(--cabin-component-status-bar-item-gap)" }}
        >
          <span className="text-status tabular-nums text-[var(--on-container)]">
            12:41
          </span>
        </div>
      </div>

      <div
        className="mt-4 flex flex-wrap gap-2"
        role="group"
        aria-label="Status emphasis"
      >
        {(
          [
            ["normal", "Normal"],
            ["warning", "Warning locked"],
            ["charging", "Charging locked"],
          ] as const
        ).map(([id, label]) => (
          <button
            key={id}
            type="button"
            onClick={() => setEmphasis(id)}
            aria-pressed={emphasis === id}
            className={`rounded-md px-3 py-2 text-status transition-colors duration-200 ease-cabin ${
              emphasis === id
                ? "bg-primary text-on-primary"
                : "border border-[var(--outline-subtle)] text-on-surface-variant hover:text-on-surface"
            }`}
          >
            {label}
          </button>
        ))}
      </div>
    </div>
  );
}

export function SystemBarDemo() {
  const [drive, setDrive] = useState<"parked" | "moving">("parked");
  const appsBlocked = drive === "moving";

  return (
    <div>
      <div
        className="flex items-stretch border border-[var(--outline)] bg-[var(--container)]"
        style={{
          height: "var(--cabin-component-system-bar-height)",
          gap: "var(--cabin-component-system-bar-gap)",
          paddingInline: "var(--cabin-component-system-bar-gap)",
        }}
        role="navigation"
        aria-label="System bar preview"
      >
        {SYSTEM_SLOTS.map((slot) => {
          const blocked = Boolean(slot.gated && appsBlocked);
          return (
            <button
              key={slot.id}
              type="button"
              disabled={blocked}
              className="flex flex-1 flex-col items-center justify-center gap-1 rounded-md text-[var(--on-container)] transition-opacity duration-200 ease-cabin"
              style={{
                minWidth: "var(--cabin-component-system-bar-item-min-size)",
                opacity: blocked ? 0.7 : 1,
                background: slot.active
                  ? "color-mix(in srgb, var(--primary) 16%, transparent)"
                  : undefined,
              }}
              aria-label={
                blocked ? `${slot.label} (restricted while moving)` : slot.label
              }
            >
              <span
                className="rounded-sm"
                style={{
                  width: "var(--cabin-component-system-bar-icon-size)",
                  height: 4,
                  background: slot.active
                    ? "var(--primary)"
                    : "var(--on-container)",
                  opacity: slot.active ? 1 : 0.55,
                }}
                aria-hidden
              />
              <span className="text-status">{slot.label}</span>
            </button>
          );
        })}
      </div>

      <div
        className="mt-4 flex flex-wrap gap-2"
        role="group"
        aria-label="Drive state for system bar"
      >
        {(["parked", "moving"] as const).map((s) => (
          <button
            key={s}
            type="button"
            onClick={() => setDrive(s)}
            aria-pressed={drive === s}
            className={`rounded-md px-3 py-2 text-status capitalize transition-colors duration-200 ease-cabin ${
              drive === s
                ? "bg-primary text-on-primary"
                : "border border-[var(--outline-subtle)] text-on-surface-variant hover:text-on-surface"
            }`}
          >
            {s}
          </button>
        ))}
      </div>
      <p className="mt-3 text-status text-on-surface-variant">
        Apps slot soft-disables while Moving (OpenComplexApp → Block). Home /
        Media stay activatable for simple navigation.
      </p>
    </div>
  );
}

function SignalGlyph() {
  return (
    <span className="flex h-full w-full items-end justify-center gap-0.5 pb-1">
      {[6, 10, 14, 18].map((h) => (
        <span
          key={h}
          className="w-0.5 rounded-sm bg-current"
          style={{ height: h }}
        />
      ))}
    </span>
  );
}
