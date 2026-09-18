"use client";

import { useState } from "react";
import {
  GlyphSignal,
  SystemGlyph,
  type SystemGlyphId,
} from "@/components/cabin-glyphs";
import { BezelChrome, DemoRail } from "@/components/demo-stage";
import { SegmentedControl } from "@/components/segmented-control";

/**
 * Web HMI demos of Cabin System / Status bars in bezel chrome.
 * Real glyphs — not dash stubs. Generated tokens only.
 */

const SYSTEM_SLOTS: {
  id: SystemGlyphId;
  label: string;
  active?: boolean;
  gated?: boolean;
}[] = [
  { id: "home", label: "Home" },
  { id: "maps", label: "Maps" },
  { id: "media", label: "Media", active: true },
  { id: "hvac", label: "HVAC" },
  { id: "apps", label: "Apps", gated: true },
];

export function StatusBarDemo({ embedded = false }: { embedded?: boolean }) {
  const [emphasis, setEmphasis] = useState<"normal" | "warning" | "charging">(
    "normal",
  );

  const bar = (
    <div
      className="flex items-center justify-between bg-[var(--container)] px-[var(--cabin-component-status-bar-item-gap)] text-[var(--on-container)]"
      style={{
        height: "var(--cabin-component-status-bar-height)",
        gap: "var(--cabin-component-status-bar-item-gap)",
      }}
      role="status"
      aria-label="Status bar preview"
    >
      <div
        className="flex min-w-0 items-center"
        style={{ gap: "var(--cabin-component-status-bar-item-gap)" }}
      >
        <span
          className="inline-flex shrink-0 items-center justify-center text-[var(--on-container)]"
          style={{
            width: "var(--cabin-component-status-bar-icon-size)",
            height: "var(--cabin-component-status-bar-icon-size)",
          }}
          aria-hidden
        >
          <GlyphSignal className="h-full w-full" />
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
        className="flex shrink-0 items-center text-[var(--on-container)]/80"
        style={{ gap: "var(--cabin-component-status-bar-item-gap)" }}
      >
        <span className="text-status tabular-nums text-[var(--on-container)]">
          12:41
        </span>
      </div>
    </div>
  );

  if (embedded) return bar;

  return (
    <div>
      {bar}
      <DemoRail label="Status emphasis">
        <SegmentedControl
          ariaLabel="Status emphasis"
          value={emphasis}
          onChange={setEmphasis}
          options={[
            { id: "normal", label: "Normal" },
            { id: "warning", label: "Warning locked" },
            { id: "charging", label: "Charging locked" },
          ]}
        />
      </DemoRail>
    </div>
  );
}

export function SystemBarDemo({
  embedded = false,
  drive,
  onDrive,
}: {
  embedded?: boolean;
  drive?: "parked" | "moving";
  onDrive?: (d: "parked" | "moving") => void;
}) {
  const [localDrive, setLocalDrive] = useState<"parked" | "moving">("parked");
  const driveState = drive ?? localDrive;
  const setDrive = onDrive ?? setLocalDrive;
  const appsBlocked = driveState === "moving";

  const bar = (
    <div
      className="flex items-stretch border-t border-[var(--outline-subtle)] bg-[var(--container)]"
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
            className="flex flex-1 flex-col items-center justify-center gap-1.5 rounded-md text-[var(--on-container)] transition-opacity duration-200 ease-cabin"
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
              className="inline-flex items-center justify-center"
              style={{
                width: "var(--cabin-component-system-bar-icon-size)",
                height: "var(--cabin-component-system-bar-icon-size)",
                color: slot.active
                  ? "var(--primary)"
                  : "var(--on-container)",
                opacity: slot.active ? 1 : 0.72,
              }}
              aria-hidden
            >
              <SystemGlyph id={slot.id} className="h-full w-full" />
            </span>
            <span className="text-status">{slot.label}</span>
          </button>
        );
      })}
    </div>
  );

  if (embedded) return bar;

  return (
    <div>
      {bar}
      <DemoRail label="Drive state">
        <SegmentedControl
          ariaLabel="Drive state for system bar"
          value={driveState}
          onChange={setDrive}
          options={[
            { id: "parked", label: "Parked" },
            { id: "moving", label: "Moving" },
          ]}
        />
        <p className="text-status text-on-surface-variant">
          Apps soft-disables while Moving. Home / Media stay activatable.
        </p>
      </DemoRail>
    </div>
  );
}

/** Combined bars demo — Status + System in instrument bezel. */
export function BarsBezelDemo() {
  const [emphasis, setEmphasis] = useState<"normal" | "warning" | "charging">(
    "normal",
  );
  const [drive, setDrive] = useState<"parked" | "moving">("parked");

  return (
    <div>
      <BezelChrome
        status={<StatusBarInner emphasis={emphasis} />}
        system={<SystemBarDemo embedded drive={drive} onDrive={setDrive} />}
      >
        <div className="flex flex-wrap items-end justify-between gap-4">
          <div>
            <p className="kicker text-on-surface-variant">Cabin chrome</p>
            <p className="mt-1.5 font-display text-title text-on-surface">
              Status · content · system
            </p>
          </div>
          <p className="text-status tabular-nums text-on-surface-variant">
            {drive === "moving" ? "Moving" : "Parked"}
          </p>
        </div>
      </BezelChrome>

      <div className="mt-4 grid gap-4 sm:grid-cols-2">
        <DemoRail label="Status emphasis">
          <SegmentedControl
            ariaLabel="Status emphasis"
            value={emphasis}
            onChange={setEmphasis}
            options={[
              { id: "normal", label: "Normal" },
              { id: "warning", label: "Warning" },
              { id: "charging", label: "Charging" },
            ]}
          />
        </DemoRail>
        <DemoRail label="Drive state">
          <SegmentedControl
            ariaLabel="Drive state"
            value={drive}
            onChange={setDrive}
            options={[
              { id: "parked", label: "Parked" },
              { id: "moving", label: "Moving" },
            ]}
          />
        </DemoRail>
      </div>
    </div>
  );
}

function StatusBarInner({
  emphasis,
}: {
  emphasis: "normal" | "warning" | "charging";
}) {
  return (
    <div
      className="flex items-center justify-between bg-[var(--container)] px-[var(--cabin-component-status-bar-item-gap)] text-[var(--on-container)]"
      style={{
        height: "var(--cabin-component-status-bar-height)",
        gap: "var(--cabin-component-status-bar-item-gap)",
      }}
      role="status"
      aria-label="Status bar preview"
    >
      <div
        className="flex min-w-0 items-center"
        style={{ gap: "var(--cabin-component-status-bar-item-gap)" }}
      >
        <span
          className="inline-flex shrink-0 items-center justify-center"
          style={{
            width: "var(--cabin-component-status-bar-icon-size)",
            height: "var(--cabin-component-status-bar-icon-size)",
          }}
          aria-hidden
        >
          <GlyphSignal className="h-full w-full" />
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
      <span className="text-status tabular-nums text-[var(--on-container)]">
        12:41
      </span>
    </div>
  );
}
