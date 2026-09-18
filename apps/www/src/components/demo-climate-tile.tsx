"use client";

import { useState, type ReactNode } from "react";
import { GlyphMinus, GlyphPlus } from "@/components/cabin-glyphs";
import { DemoRail } from "@/components/demo-stage";
import { SegmentedControl } from "@/components/segmented-control";
import {
  dispositionFor,
  formatLevel,
  formatTemp,
  isActivatable,
  quietOpacity,
  type DemoSignal,
  type DriveState,
  type SignalKind,
} from "@/lib/demo-signal";

type ClimateState = {
  zoneLabel: string;
  temperatureC: DemoSignal<number>;
  fanLevel: DemoSignal<number>;
  fanMax: number;
  seatHeatLevel: DemoSignal<number>;
  seatHeatMax: number;
  powerOn: boolean;
};

const LIVE: ClimateState = {
  zoneLabel: "Driver",
  temperatureC: { kind: "value", value: 22 },
  fanLevel: { kind: "value", value: 3 },
  fanMax: 5,
  seatHeatLevel: { kind: "value", value: 1 },
  seatHeatMax: 3,
  powerOn: true,
};

const FIXTURES: Record<SignalKind, ClimateState> = {
  value: LIVE,
  stale: {
    ...LIVE,
    temperatureC: { kind: "stale", last: 22 },
    fanLevel: { kind: "stale", last: 3 },
    seatHeatLevel: { kind: "stale", last: 1 },
  },
  unavailable: {
    zoneLabel: "Driver",
    temperatureC: { kind: "unavailable" },
    fanLevel: { kind: "unavailable" },
    seatHeatLevel: { kind: "unavailable" },
    fanMax: 5,
    seatHeatMax: 3,
    powerOn: true,
  },
  fault: {
    ...LIVE,
    temperatureC: { kind: "fault" },
    fanLevel: { kind: "fault" },
    seatHeatLevel: { kind: "fault" },
  },
};

/**
 * Web mirror of CabinClimateTile — cabin density, climate accent mark only,
 * cabin control glyphs. Not a Material card.
 */
export function ClimateTileDemo({
  compact = false,
  lockedDrive,
}: {
  compact?: boolean;
  /** When set, drive state is fixed (use-case stages). */
  lockedDrive?: DriveState;
}) {
  const [fixture, setFixture] = useState<SignalKind>("value");
  const [drive, setDrive] = useState<DriveState>(lockedDrive ?? "parked");
  const [state, setState] = useState<ClimateState>(LIVE);
  const driveState = lockedDrive ?? drive;
  const disposition = dispositionFor(driveState, "hvacAdjust");

  function applyFixture(kind: SignalKind) {
    setFixture(kind);
    setState(FIXTURES[kind]);
  }

  function bumpTemp(delta: number) {
    if (state.temperatureC.kind !== "value") return;
    setState({
      ...state,
      temperatureC: {
        kind: "value",
        value: Math.min(32, Math.max(16, state.temperatureC.value + delta)),
      },
    });
  }

  function bumpFan(delta: number) {
    if (state.fanLevel.kind !== "value") return;
    setState({
      ...state,
      fanLevel: {
        kind: "value",
        value: Math.min(
          state.fanMax,
          Math.max(0, state.fanLevel.value + delta),
        ),
      },
    });
  }

  function bumpSeat(delta: number) {
    if (state.seatHeatLevel.kind !== "value") return;
    setState({
      ...state,
      seatHeatLevel: {
        kind: "value",
        value: Math.min(
          state.seatHeatMax,
          Math.max(0, state.seatHeatLevel.value + delta),
        ),
      },
    });
  }

  const tempEnabled =
    state.powerOn && state.temperatureC.kind === "value";
  const fanEnabled = state.powerOn && state.fanLevel.kind === "value";
  const seatEnabled =
    state.powerOn && state.seatHeatLevel.kind === "value";

  const fanValue =
    state.fanLevel.kind === "value"
      ? state.fanLevel.value
      : state.fanLevel.kind === "stale"
        ? state.fanLevel.last
        : 0;
  const seatValue =
    state.seatHeatLevel.kind === "value"
      ? state.seatHeatLevel.value
      : state.seatHeatLevel.kind === "stale"
        ? state.seatHeatLevel.last
        : 0;

  return (
    <div>
      <div
        className="bg-[var(--surface)]"
        style={{
          padding: "var(--cabin-component-climate-tile-padding)",
          borderRadius: "var(--cabin-component-climate-tile-corner-radius)",
        }}
        data-testid="cabin-climate-tile"
      >
        <div
          className="flex items-center"
          style={{ gap: "var(--cabin-component-climate-tile-gap)" }}
        >
          <span
            className="bg-climate"
            style={{
              width: "var(--cabin-space-xs)",
              height: "calc(var(--cabin-component-climate-tile-control-min-size) / 3)",
            }}
            aria-hidden
            data-testid="climate-accent-mark"
          />
          <p className="text-label font-semibold text-on-surface">
            {state.zoneLabel}
          </p>
        </div>

        <div
          className="mt-[var(--cabin-component-climate-tile-gap)] flex flex-col"
          style={{ gap: "var(--cabin-component-climate-tile-gap)" }}
        >
          <StepperRow
            valueText={formatTemp(state.temperatureC)}
            enabled={tempEnabled}
            disposition={disposition}
            onDown={() => bumpTemp(-1)}
            onUp={() => bumpTemp(1)}
            downLabel="Decrease temperature"
            upLabel="Increase temperature"
            valueClass="font-display text-title tabular-nums"
          />

          <div
            className="flex flex-wrap items-center"
            style={{ gap: "var(--cabin-component-climate-tile-gap)" }}
          >
            <span className="min-w-[3rem] text-status font-semibold text-on-surface">
              Fan
            </span>
            <StepperRow
              valueText={formatLevel(state.fanLevel, state.fanMax)}
              enabled={fanEnabled}
              disposition={disposition}
              onDown={() => bumpFan(-1)}
              onUp={() => bumpFan(1)}
              downLabel="Decrease fan"
              upLabel="Increase fan"
              valueClass="text-label tabular-nums"
            />
            <LevelPips
              value={fanValue}
              max={state.fanMax}
              accent="climate"
              label="Fan level"
            />
          </div>

          <div
            className="flex flex-wrap items-center"
            style={{ gap: "var(--cabin-component-climate-tile-gap)" }}
          >
            <span className="min-w-[3rem] text-status font-semibold text-on-surface">
              Seat
            </span>
            <StepperRow
              valueText={formatLevel(state.seatHeatLevel, state.seatHeatMax)}
              enabled={seatEnabled}
              disposition={disposition}
              onDown={() => bumpSeat(-1)}
              onUp={() => bumpSeat(1)}
              downLabel="Decrease seat heat"
              upLabel="Increase seat heat"
              valueClass="text-label tabular-nums"
            />
            <LevelPips
              value={seatValue}
              max={state.seatHeatMax}
              accent="climate"
              label="Seat heat level"
            />
          </div>
        </div>
      </div>

      {!compact ? (
        <DemoControls
          fixture={fixture}
          drive={driveState}
          onFixture={applyFixture}
          onDrive={setDrive}
          driveLocked={Boolean(lockedDrive)}
          note={
            driveState === "moving"
              ? "Moving: HvacAdjust Block — steppers RE-quiet; values stay glanceable."
              : "Parked: adjustments Allow. Climate accent is mark-only."
          }
        />
      ) : null}
    </div>
  );
}

function LevelPips({
  value,
  max,
  accent,
  label,
}: {
  value: number;
  max: number;
  accent: "climate";
  label: string;
}) {
  return (
    <div
      className="ml-auto flex items-end gap-1"
      role="img"
      aria-label={`${label}: ${value} of ${max}`}
    >
      {Array.from({ length: max }, (_, i) => {
        const on = i < value;
        return (
          <span
            key={i}
            className="w-1.5 rounded-sm"
            style={{
              height: 8 + i * 3,
              background: on
                ? accent === "climate"
                  ? "var(--climate)"
                  : "var(--primary)"
                : "var(--outline-subtle)",
            }}
          />
        );
      })}
    </div>
  );
}

function StepperRow({
  valueText,
  enabled,
  disposition,
  onDown,
  onUp,
  downLabel,
  upLabel,
  valueClass,
}: {
  valueText: string;
  enabled: boolean;
  disposition: ReturnType<typeof dispositionFor>;
  onDown: () => void;
  onUp: () => void;
  downLabel: string;
  upLabel: string;
  valueClass: string;
}) {
  return (
    <div className="flex items-center">
      <ControlButton
        description={downLabel}
        enabled={enabled}
        disposition={disposition}
        onClick={onDown}
      >
        <GlyphMinus className="h-6 w-6" />
      </ControlButton>
      <p
        className={`min-w-[var(--cabin-component-climate-tile-control-min-size)] px-2 text-center text-on-surface ${valueClass}`}
      >
        {valueText}
      </p>
      <ControlButton
        description={upLabel}
        enabled={enabled}
        disposition={disposition}
        onClick={onUp}
      >
        <GlyphPlus className="h-6 w-6" />
      </ControlButton>
    </div>
  );
}

function ControlButton({
  description,
  enabled,
  disposition,
  onClick,
  children,
}: {
  description: string;
  enabled: boolean;
  disposition: ReturnType<typeof dispositionFor>;
  onClick: () => void;
  children: ReactNode;
}) {
  const can = isActivatable(enabled, disposition);
  return (
    <button
      type="button"
      aria-label={description}
      disabled={!can}
      onClick={onClick}
      className="inline-flex items-center justify-center text-on-surface transition-opacity duration-200 ease-cabin disabled:cursor-not-allowed"
      style={{
        width: "var(--cabin-component-climate-tile-control-min-size)",
        height: "var(--cabin-component-climate-tile-control-min-size)",
        opacity: quietOpacity(disposition, enabled),
        borderRadius: "var(--cabin-component-climate-tile-corner-radius)",
        background: "var(--surface-high)",
        border: "1px solid var(--outline-subtle)",
      }}
    >
      {children}
    </button>
  );
}

function DemoControls({
  fixture,
  drive,
  onFixture,
  onDrive,
  note,
  driveLocked = false,
}: {
  fixture: SignalKind;
  drive: DriveState;
  onFixture: (k: SignalKind) => void;
  onDrive: (d: DriveState) => void;
  note: string;
  driveLocked?: boolean;
}) {
  return (
    <div className="mt-4 space-y-3">
      <DemoRail label="Signal">
        <SegmentedControl
          ariaLabel="Signal fixture"
          value={fixture}
          onChange={onFixture}
          options={[
            { id: "value", label: "Live" },
            { id: "stale", label: "Stale" },
            { id: "unavailable", label: "Empty" },
            { id: "fault", label: "Fault" },
          ]}
        />
      </DemoRail>
      {!driveLocked ? (
        <DemoRail label="Drive">
          <SegmentedControl
            ariaLabel="Drive state"
            value={drive}
            onChange={onDrive}
            fill="quiet"
            options={[
              { id: "parked", label: "Parked" },
              { id: "moving", label: "Moving" },
            ]}
          />
          <p className="text-status text-on-surface-variant">{note}</p>
        </DemoRail>
      ) : (
        <p className="text-status text-on-surface-variant">{note}</p>
      )}
    </div>
  );
}
