"use client";

import { useState, type ReactNode } from "react";
import {
  ArtworkWellMark,
  GlyphNext,
  GlyphPause,
  GlyphPlay,
  GlyphPrev,
} from "@/components/cabin-glyphs";
import { DemoRail } from "@/components/demo-stage";
import {
  dispositionFor,
  formatMediaText,
  isActivatable,
  liveProgressText,
  quietOpacity,
  type DemoSignal,
  type DriveState,
  type SignalKind,
} from "@/lib/demo-signal";

type MediaState = {
  title: DemoSignal<string>;
  artist: DemoSignal<string>;
  sourceLabel: DemoSignal<string>;
  isPlaying: boolean;
  artworkAvailable: boolean;
  positionMs: DemoSignal<number>;
  durationMs: DemoSignal<number>;
  hasSource: boolean;
};

const LIVE: MediaState = {
  title: { kind: "value", value: "Quiet roads" },
  artist: { kind: "value", value: "Cabin Media" },
  sourceLabel: { kind: "value", value: "Bluetooth" },
  isPlaying: true,
  artworkAvailable: true,
  positionMs: { kind: "value", value: 125_000 },
  durationMs: { kind: "value", value: 204_000 },
  hasSource: true,
};

const FIXTURES: Record<SignalKind | "nosource", MediaState> = {
  value: LIVE,
  stale: {
    ...LIVE,
    title: { kind: "stale", last: "Quiet roads" },
    artist: { kind: "stale", last: "Cabin Media" },
    sourceLabel: { kind: "stale", last: "Bluetooth" },
    positionMs: { kind: "unavailable" },
    durationMs: { kind: "unavailable" },
  },
  unavailable: {
    title: { kind: "unavailable" },
    artist: { kind: "unavailable" },
    sourceLabel: { kind: "unavailable" },
    isPlaying: false,
    artworkAvailable: false,
    positionMs: { kind: "unavailable" },
    durationMs: { kind: "unavailable" },
    hasSource: true,
  },
  fault: {
    ...LIVE,
    title: { kind: "fault" },
    artist: { kind: "fault" },
    sourceLabel: { kind: "fault" },
    isPlaying: false,
    artworkAvailable: false,
    positionMs: { kind: "unavailable" },
    durationMs: { kind: "unavailable" },
  },
  nosource: {
    title: { kind: "unavailable" },
    artist: { kind: "unavailable" },
    sourceLabel: { kind: "unavailable" },
    isPlaying: false,
    artworkAvailable: false,
    positionMs: { kind: "unavailable" },
    durationMs: { kind: "unavailable" },
    hasSource: false,
  },
};

type FixtureId = keyof typeof FIXTURES;

/**
 * Web mirror of CabinMediaNowPlaying — cabin density, mediaAccent mark only,
 * SVG transport controls, real art well. Not a Material media card.
 */
export function MediaNowPlayingDemo({
  compact = false,
}: {
  compact?: boolean;
}) {
  const [fixture, setFixture] = useState<FixtureId>("value");
  const [drive, setDrive] = useState<DriveState>("parked");
  const [state, setState] = useState<MediaState>(LIVE);

  const transportGate = dispositionFor(drive, "mediaTransport");
  const complexGate = dispositionFor(drive, "mediaComplex");
  const progress = liveProgressText(state.positionMs, state.durationMs);

  function applyFixture(id: FixtureId) {
    setFixture(id);
    setState(FIXTURES[id]);
  }

  const titleText = formatMediaText(state.title, "No title");
  const artistText = formatMediaText(state.artist, "Unknown artist");
  const sourceText = formatMediaText(state.sourceLabel, "No source");
  const sourceActivatable = isActivatable(state.hasSource, complexGate);
  const transportEnabled = state.hasSource;

  return (
    <div>
      <div
        className="bg-[var(--surface)]"
        style={{
          padding: "var(--cabin-component-media-now-playing-padding)",
          borderRadius: "var(--cabin-component-media-now-playing-corner-radius)",
        }}
        data-testid="cabin-media-now-playing"
      >
        <div
          className="flex items-center"
          style={{ gap: "var(--cabin-component-media-now-playing-gap)" }}
        >
          <ArtWell available={state.artworkAvailable} />

          <div className="min-w-0 flex-1">
            <p className="truncate font-display text-title font-semibold text-on-surface">
              {titleText}
            </p>
            <p className="mt-1 truncate text-body text-on-surface-variant">
              {artistText}
            </p>
            <button
              type="button"
              disabled={!sourceActivatable}
              onClick={() => undefined}
              className="mt-2 inline-flex max-w-full items-center gap-2 text-left text-label text-on-surface disabled:cursor-not-allowed"
              style={{
                opacity: !state.hasSource
                  ? quietOpacity("block")
                  : quietOpacity(complexGate),
              }}
              aria-label={`Source ${sourceText}`}
            >
              <span
                className="shrink-0 bg-[var(--media)]"
                style={{
                  width: "var(--cabin-space-xs)",
                  height: "var(--cabin-component-media-now-playing-gap)",
                }}
                aria-hidden
                data-testid="media-accent-mark"
              />
              <span className="truncate">{sourceText}</span>
            </button>
          </div>
        </div>

        <div
          className="mt-[var(--cabin-component-media-now-playing-gap)] flex items-center justify-center"
          style={{ gap: "var(--cabin-space-sm)" }}
        >
          <TransportButton
            description="Previous"
            enabled={transportEnabled}
            disposition={transportGate}
            onClick={() => undefined}
          >
            <GlyphPrev className="h-7 w-7" />
          </TransportButton>
          <TransportButton
            description="Play or pause"
            enabled={transportEnabled}
            disposition={transportGate}
            primary
            onClick={() =>
              setState((s) => ({ ...s, isPlaying: !s.isPlaying }))
            }
          >
            {state.isPlaying ? (
              <GlyphPause className="h-7 w-7" />
            ) : (
              <GlyphPlay className="h-7 w-7" />
            )}
          </TransportButton>
          <TransportButton
            description="Next"
            enabled={transportEnabled}
            disposition={transportGate}
            onClick={() => undefined}
          >
            <GlyphNext className="h-7 w-7" />
          </TransportButton>
        </div>

        {progress ? (
          <div className="mt-[var(--cabin-component-media-now-playing-gap)]">
            <div className="h-1 overflow-hidden rounded-sm bg-[var(--surface-variant)]">
              <div
                className="h-full rounded-sm bg-[var(--on-surface)]/55"
                style={{ width: "61%" }}
                aria-hidden
              />
            </div>
            <p className="mt-2 text-center text-status tabular-nums text-on-surface-variant">
              {progress}
            </p>
          </div>
        ) : null}
      </div>

      {!compact ? (
        <div className="mt-4 space-y-3">
          <DemoRail label="Signal">
            <div
              className="flex flex-wrap gap-2"
              role="group"
              aria-label="Media signal fixture"
            >
              {(
                [
                  ["value", "Live"],
                  ["stale", "Stale"],
                  ["unavailable", "Empty"],
                  ["nosource", "No source"],
                  ["fault", "Fault"],
                ] as const
              ).map(([id, label]) => (
                <button
                  key={id}
                  type="button"
                  onClick={() => applyFixture(id)}
                  aria-pressed={fixture === id}
                  className={`rounded-md px-3 py-2 text-status transition-colors duration-200 ease-cabin ${
                    fixture === id
                      ? "bg-primary text-on-primary"
                      : "border border-[var(--outline-subtle)] text-on-surface-variant hover:text-on-surface"
                  }`}
                >
                  {label}
                </button>
              ))}
            </div>
          </DemoRail>
          <DemoRail label="Drive">
            <div className="flex flex-wrap gap-2" role="group" aria-label="Drive state">
              {(["parked", "moving"] as const).map((s) => (
                <button
                  key={s}
                  type="button"
                  onClick={() => setDrive(s)}
                  aria-pressed={drive === s}
                  className={`rounded-md px-3 py-2 text-status capitalize transition-colors duration-200 ease-cabin ${
                    drive === s
                      ? "bg-[var(--surface-high)] text-on-surface"
                      : "border border-[var(--outline-subtle)] text-on-surface-variant hover:text-on-surface"
                  }`}
                >
                  {s}
                </button>
              ))}
            </div>
            <p className="text-status text-on-surface-variant">
              {drive === "moving"
                ? "Moving: transport Allow; source soft-disables. Media accent is mark-only."
                : "Parked: source and transport Allow. Media accent is mark-only."}
            </p>
          </DemoRail>
        </div>
      ) : null}
    </div>
  );
}

function ArtWell({ available }: { available: boolean }) {
  return (
    <div
      className="relative flex shrink-0 items-center justify-center overflow-hidden border border-outline"
      style={{
        width: "var(--cabin-component-media-now-playing-artwork-size)",
        height: "var(--cabin-component-media-now-playing-artwork-size)",
        borderRadius: "var(--cabin-component-media-now-playing-corner-radius)",
        background: available
          ? "color-mix(in srgb, var(--surface-variant) 78%, var(--primary))"
          : "var(--surface-variant)",
      }}
      role="img"
      aria-label={available ? "Album artwork" : "Artwork unavailable"}
    >
      {available ? (
        <div className="absolute inset-0" aria-hidden>
          <div
            className="absolute inset-0 opacity-40"
            style={{
              background:
                "radial-gradient(ellipse at 30% 28%, color-mix(in srgb, var(--on-surface) 18%, transparent), transparent 55%)",
            }}
          />
          <div
            className="absolute bottom-0 left-0 right-0 h-1/3"
            style={{
              background:
                "linear-gradient(to top, color-mix(in srgb, var(--surface) 55%, transparent), transparent)",
            }}
          />
          <span
            className="absolute left-2.5 top-2.5 bg-[var(--media)]"
            style={{
              width: "var(--cabin-space-xs)",
              height: "var(--cabin-space-md)",
            }}
            data-testid="media-art-accent-mark"
          />
        </div>
      ) : (
        <ArtworkWellMark className="relative h-10 w-10 text-on-surface-variant" />
      )}
    </div>
  );
}

function TransportButton({
  description,
  enabled,
  disposition,
  onClick,
  children,
  primary = false,
}: {
  description: string;
  enabled: boolean;
  disposition: ReturnType<typeof dispositionFor>;
  onClick: () => void;
  children: ReactNode;
  primary?: boolean;
}) {
  const can = isActivatable(enabled, disposition);
  return (
    <button
      type="button"
      aria-label={description}
      disabled={!can}
      onClick={onClick}
      className="inline-flex items-center justify-center text-on-surface transition-[opacity,background-color] duration-200 ease-cabin disabled:cursor-not-allowed"
      style={{
        width: "var(--cabin-component-media-now-playing-transport-min-size)",
        height: "var(--cabin-component-media-now-playing-transport-min-size)",
        opacity: quietOpacity(disposition, enabled),
        borderRadius: "var(--cabin-component-media-now-playing-corner-radius)",
        background: primary
          ? "color-mix(in srgb, var(--primary) 18%, var(--surface-high))"
          : "var(--surface-high)",
        border: primary
          ? "1px solid color-mix(in srgb, var(--primary) 35%, transparent)"
          : "1px solid var(--outline-subtle)",
      }}
    >
      {children}
    </button>
  );
}
