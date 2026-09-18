"use client";

import { useState } from "react";
import { DemoRail } from "@/components/demo-stage";
import { SegmentedControl } from "@/components/segmented-control";
import {
  type DemoSignal,
  type SignalKind,
} from "@/lib/demo-signal";

type EvState = {
  socPercent: DemoSignal<number>;
  rangeKm: DemoSignal<number>;
  powerKw: DemoSignal<number>;
  sessionLabel: DemoSignal<string>;
  charging: boolean;
};

const LIVE: EvState = {
  socPercent: { kind: "value", value: 78 },
  rangeKm: { kind: "value", value: 312 },
  powerKw: { kind: "value", value: 48 },
  sessionLabel: { kind: "value", value: "DC fast · target 80%" },
  charging: true,
};

const FIXTURES: Record<SignalKind, EvState> = {
  value: LIVE,
  stale: {
    ...LIVE,
    socPercent: { kind: "stale", last: 78 },
    rangeKm: { kind: "stale", last: 312 },
    powerKw: { kind: "stale", last: 48 },
    sessionLabel: { kind: "stale", last: "DC fast · target 80%" },
  },
  unavailable: {
    socPercent: { kind: "unavailable" },
    rangeKm: { kind: "unavailable" },
    powerKw: { kind: "unavailable" },
    sessionLabel: { kind: "unavailable" },
    charging: false,
  },
  fault: {
    socPercent: { kind: "fault" },
    rangeKm: { kind: "fault" },
    powerKw: { kind: "unavailable" },
    sessionLabel: { kind: "fault" },
    charging: false,
  },
};

function formatSoc(signal: DemoSignal<number>): string {
  switch (signal.kind) {
    case "value":
      return `${signal.value}%`;
    case "stale":
      return `${signal.last}% · stale`;
    case "unavailable":
      return "—";
    case "fault":
      return "Fault";
  }
}

function formatRange(signal: DemoSignal<number>): string {
  switch (signal.kind) {
    case "value":
      return `${signal.value} km`;
    case "stale":
      return `${signal.last} km · stale`;
    case "unavailable":
      return "Range unavailable";
    case "fault":
      return "Range fault";
  }
}

function formatPower(signal: DemoSignal<number>): string {
  switch (signal.kind) {
    case "value":
      return `${signal.value} kW`;
    case "stale":
      return `${signal.last} kW · stale`;
    case "unavailable":
      return "—";
    case "fault":
      return "Fault";
  }
}

function formatSession(signal: DemoSignal<string>): string {
  switch (signal.kind) {
    case "value":
      return signal.value;
    case "stale":
      return `${signal.last} · stale`;
    case "unavailable":
      return "No session";
    case "fault":
      return "Charge fault — check cable";
  }
}

function socFill(signal: DemoSignal<number>): number | null {
  switch (signal.kind) {
    case "value":
      return signal.value;
    case "stale":
      return signal.last;
    default:
      return null;
  }
}

export function EvEnergyTile({ state }: { state: EvState }) {
  const fill = socFill(state.socPercent);
  const isFault = state.socPercent.kind === "fault";
  const isEmpty = state.socPercent.kind === "unavailable";
  const isStale = state.socPercent.kind === "stale";

  return (
    <div
      className="bg-[var(--surface)]"
      style={{
        padding: "var(--cabin-space-lg)",
        borderRadius: "var(--cabin-component-climate-tile-corner-radius)",
      }}
      data-testid="cabin-ev-energy"
    >
      <div className="flex items-center gap-[var(--cabin-space-sm)]">
        <span
          className="bg-charging"
          style={{
            width: "var(--cabin-space-xs)",
            height: "var(--cabin-space-md)",
          }}
          aria-hidden
          data-testid="charging-accent-mark"
        />
        <p className="text-status font-semibold uppercase tracking-[0.12em] text-on-surface-variant">
          Energy
        </p>
        {state.charging && !isFault && !isEmpty ? (
          <span className="ml-auto inline-flex items-center gap-1.5 text-status text-charging">
            <span className="h-1.5 w-1.5 rounded-full bg-charging" />
            Charging
          </span>
        ) : null}
        {isFault ? (
          <span className="ml-auto inline-flex items-center gap-1.5 text-status text-error">
            <span className="h-1.5 w-1.5 rounded-full bg-error" />
            Fault
          </span>
        ) : null}
        {isEmpty ? (
          <span className="ml-auto text-status text-on-surface-variant">
            Empty
          </span>
        ) : null}
        {isStale ? (
          <span className="ml-auto text-status text-warning">Stale</span>
        ) : null}
      </div>

      <div className="mt-[var(--cabin-space-md)] flex flex-wrap items-end gap-x-8 gap-y-4">
        <div>
          <p
            className={`font-display text-display tabular-nums leading-none ${
              isFault ? "text-error" : "text-on-surface"
            }`}
          >
            {formatSoc(state.socPercent)}
          </p>
          <p className="mt-2 text-status text-on-surface-variant">
            State of charge
          </p>
        </div>
        <div>
          <p
            className={`font-display text-headline tabular-nums leading-none ${
              isFault ? "text-error" : "text-on-surface"
            }`}
          >
            {formatRange(state.rangeKm)}
          </p>
          <p className="mt-2 text-status text-on-surface-variant">
            Estimated range
          </p>
        </div>
      </div>

      <div
        className="mt-[var(--cabin-space-md)] h-2 overflow-hidden rounded-sm bg-[var(--surface-variant)]"
        role="img"
        aria-label={
          fill != null
            ? `Charge level ${fill} percent`
            : isFault
              ? "Charge level fault"
              : "Charge level unavailable"
        }
      >
        {fill != null ? (
          <div
            className="h-full rounded-sm bg-charging transition-[width] duration-300 ease-cabin"
            style={{
              width: `${fill}%`,
              opacity: isStale ? 0.7 : 1,
            }}
          />
        ) : isFault ? (
          <div className="h-full w-full bg-error/35" />
        ) : null}
      </div>

      {/* Control row — flex only, no empty grid cells */}
      <div
        className="mt-[var(--cabin-space-md)] flex flex-wrap items-center gap-x-6 gap-y-3 border-t border-[var(--outline-subtle)] pt-[var(--cabin-space-md)]"
        data-testid="ev-control-row"
      >
        <div>
          <p className="text-status text-on-surface-variant">Power</p>
          <p className="mt-1 text-label tabular-nums text-on-surface">
            {formatPower(state.powerKw)}
          </p>
        </div>
        <div className="min-w-0 flex-1">
          <p className="text-status text-on-surface-variant">Session</p>
          <p
            className={`mt-1 truncate text-label ${
              isFault ? "text-error" : "text-on-surface"
            }`}
          >
            {formatSession(state.sessionLabel)}
          </p>
        </div>
      </div>
    </div>
  );
}

export function EvSignalRails({
  fixture,
  onFixture,
}: {
  fixture: SignalKind;
  onFixture: (kind: SignalKind) => void;
}) {
  return (
    <DemoRail label="Signal">
      <SegmentedControl
        ariaLabel="EV signal fixture"
        value={fixture}
        onChange={onFixture}
        options={[
          { id: "value", label: "Live" },
          { id: "stale", label: "Stale" },
          { id: "unavailable", label: "Empty" },
          { id: "fault", label: "Fault" },
        ]}
      />
      <p className="text-status text-on-surface-variant">
        Honest empty / stale / fault — no invented SOC. Charging color stays
        locked.
      </p>
    </DemoRail>
  );
}

/**
 * Web EV energy stage — honest SOC / range / session from Signal fixtures.
 * Charging color is safety-locked; accent marks only. Not a Material gauge clone.
 */
export function EvEnergyDemo({
  compact = false,
}: {
  compact?: boolean;
}) {
  const [fixture, setFixture] = useState<SignalKind>("value");
  const [state, setState] = useState<EvState>(LIVE);

  function applyFixture(kind: SignalKind) {
    setFixture(kind);
    setState(FIXTURES[kind]);
  }

  return (
    <div>
      <EvEnergyTile state={state} />
      {!compact ? (
        <EvSignalRails fixture={fixture} onFixture={applyFixture} />
      ) : null}
    </div>
  );
}

export { FIXTURES, LIVE };
export type { EvState };
