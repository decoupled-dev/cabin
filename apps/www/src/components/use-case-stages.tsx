"use client";

import { useState, type ReactNode } from "react";
import { ClimateTileDemo } from "@/components/demo-climate-tile";
import {
  EvEnergyTile,
  EvSignalRails,
  FIXTURES,
  LIVE,
} from "@/components/demo-ev-energy";
import { MediaNowPlayingDemo } from "@/components/demo-media-now-playing";
import {
  GlyphSignal,
  SystemGlyph,
  type SystemGlyphId,
} from "@/components/cabin-glyphs";
import { BezelChrome } from "@/components/demo-stage";
import type { SignalKind } from "@/lib/demo-signal";

type StageMode = "parked" | "moving" | "charging";

const SYSTEM_SLOTS: {
  id: SystemGlyphId;
  label: string;
  active?: boolean;
  gated?: boolean;
}[] = [
  { id: "home", label: "Home" },
  { id: "maps", label: "Maps" },
  { id: "media", label: "Media" },
  { id: "hvac", label: "HVAC" },
  { id: "apps", label: "Apps", gated: true },
];

function StageStatusBar({
  mode,
  chargeSignal = "value",
}: {
  mode: StageMode;
  /** Charging stage only — keep status SOC honest with EV fixtures. */
  chargeSignal?: SignalKind;
}) {
  const gear = mode === "moving" ? "D" : "P";
  const temp = mode === "moving" ? "68°F" : "72°F";

  let energyChip: React.ReactNode;
  if (mode === "charging") {
    switch (chargeSignal) {
      case "value":
        energyChip = (
          <span className="inline-flex items-center gap-1.5 text-status text-charging">
            <span className="h-1.5 w-1.5 rounded-full bg-charging" />
            78% charging
          </span>
        );
        break;
      case "stale":
        energyChip = (
          <span className="text-status tabular-nums text-warning">
            78% · stale
          </span>
        );
        break;
      case "unavailable":
        energyChip = (
          <span className="text-status text-[var(--on-container)]/80">—</span>
        );
        break;
      case "fault":
        energyChip = (
          <span className="inline-flex items-center gap-1.5 text-status text-error">
            <span className="h-1.5 w-1.5 rounded-full bg-error" />
            Charge fault
          </span>
        );
        break;
    }
  } else if (mode === "moving") {
    energyChip = (
      <span className="text-status tabular-nums text-[var(--on-container)]/80">
        64%
      </span>
    );
  } else {
    energyChip = (
      <span className="text-status tabular-nums text-[var(--on-container)]/80">
        78%
      </span>
    );
  }

  return (
    <div
      className="flex items-center justify-between bg-[var(--container)] px-[var(--cabin-component-status-bar-item-gap)] text-[var(--on-container)]"
      style={{
        height: "var(--cabin-component-status-bar-height)",
        gap: "var(--cabin-component-status-bar-item-gap)",
      }}
      role="status"
      aria-label={`${mode} status bar`}
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
        <span className="text-status tabular-nums tracking-wide">
          {gear} · {temp}
        </span>
        {energyChip}
      </div>
      <span className="text-status tabular-nums text-[var(--on-container)]">
        12:41
      </span>
    </div>
  );
}

function StageSystemBar({ mode }: { mode: StageMode }) {
  const appsBlocked = mode === "moving";
  const activeId: SystemGlyphId =
    mode === "parked" ? "hvac" : mode === "moving" ? "media" : "home";

  return (
    <div
      className="flex items-stretch border-t border-[var(--outline-subtle)] bg-[var(--container)]"
      style={{
        height: "var(--cabin-component-system-bar-height)",
        gap: "var(--cabin-component-system-bar-gap)",
        paddingInline: "var(--cabin-component-system-bar-gap)",
      }}
      role="navigation"
      aria-label={`${mode} system bar`}
    >
      {SYSTEM_SLOTS.map((slot) => {
        const blocked = Boolean(slot.gated && appsBlocked);
        const active = slot.id === activeId;
        return (
          <button
            key={slot.id}
            type="button"
            disabled={blocked}
            className="flex flex-1 flex-col items-center justify-center gap-1.5 rounded-md text-[var(--on-container)] transition-opacity duration-200 ease-cabin"
            style={{
              minWidth: "var(--cabin-component-system-bar-item-min-size)",
              opacity: blocked ? 0.7 : 1,
              background: active
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
                color: active ? "var(--primary)" : "var(--on-container)",
                opacity: active ? 1 : 0.72,
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
}

/** Parked — climate full control, forest night bezel, climate accents mark-only. */
export function ParkedStage() {
  return (
    <div>
      <BezelChrome
        status={<StageStatusBar mode="parked" />}
        system={<StageSystemBar mode="parked" />}
      >
        <div className="mx-auto max-w-md">
          <ClimateTileDemo compact lockedDrive="parked" />
        </div>
      </BezelChrome>
      <p className="mt-4 text-status text-on-surface-variant">
        Parked: HvacAdjust Allow — temp, fan, and seat steppers stay activatable.
        Climate accent is mark-only. Flip Night / Day in the header.
      </p>
    </div>
  );
}

/**
 * Moving — media glance, transport Allow, source/complex RE-quiet.
 * No distraction chrome beyond status · content · system.
 */
export function MovingStage() {
  return (
    <div>
      <BezelChrome
        status={<StageStatusBar mode="moving" />}
        system={<StageSystemBar mode="moving" />}
      >
        <div className="mx-auto max-w-lg">
          <MediaNowPlayingDemo compact lockedDrive="moving" />
        </div>
      </BezelChrome>
      <p className="mt-4 text-status text-on-surface-variant">
        Moving: transport Allow; source soft-disables (MediaComplex Block). Apps
        soft-disables in the system bar. Fail-closed — no distraction chrome.
      </p>
    </div>
  );
}

/**
 * Charging — honest SOC / range, locked charging color, empty/stale/fault.
 * Control row is flex-only (no empty grid cells). Rails sit outside the bezel.
 */
export function ChargingStage() {
  const [fixture, setFixture] = useState<SignalKind>("value");
  const [state, setState] = useState(LIVE);

  function applyFixture(kind: SignalKind) {
    setFixture(kind);
    setState(FIXTURES[kind]);
  }

  return (
    <div>
      <BezelChrome
        status={
          <StageStatusBar mode="charging" chargeSignal={fixture} />
        }
        system={<StageSystemBar mode="charging" />}
      >
        <div className="mx-auto max-w-lg">
          <EvEnergyTile state={state} />
        </div>
      </BezelChrome>
      <EvSignalRails fixture={fixture} onFixture={applyFixture} />
    </div>
  );
}
