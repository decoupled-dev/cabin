"use client";

import { useState, type CSSProperties, type ReactNode } from "react";
import {
  ArtworkWellMark,
  GlyphDefrost,
  GlyphDriveMode,
  GlyphLights,
  GlyphLock,
  GlyphNext,
  GlyphPause,
  GlyphPlay,
  GlyphPrev,
  GlyphTurnRight,
} from "@/components/cabin-glyphs";
import { DemoRail, DemoStage } from "@/components/demo-stage";
import { useScheme } from "@/components/scheme-provider";
import { SegmentedControl } from "@/components/segmented-control";
import {
  dispositionFor,
  isActivatable,
  quietOpacity,
  type DriveState,
} from "@/lib/demo-signal";

/**
 * Web analog of the catalog Showcase: one Home (H1 Split) layout rendered in
 * each of the six look presets. Roles, type, and shape come from generated
 * `cabin.looks.css`; safety roles are shared across looks (locked tier).
 * Presentation only — not the Compose `CabinTheme`.
 */

type LookId = "quiet" | "precision" | "warm" | "glass" | "graphic" | "mono";

const LOOKS: readonly { id: LookId; label: string; note: string }[] = [
  {
    id: "quiet",
    label: "Quiet",
    note: "Default. Warm graphite, one champagne accent, soft shapes, light numerals.",
  },
  {
    id: "precision",
    label: "Precision",
    note: "Instrument-grade. True black, hairline rules, mono labels.",
  },
  {
    id: "warm",
    label: "Warm",
    note: "Ink blue instead of black, chunky tonal tiles, pill controls.",
  },
  {
    id: "glass",
    label: "Glass",
    note: "Layered panels over the map; depth instead of borders.",
  },
  {
    id: "graphic",
    label: "Graphic",
    note: "Flat color blocks, heavy grotesk type, solid active state.",
  },
  {
    id: "mono",
    label: "Mono",
    note: "Grayscale only, so warning, error, and charging are the only color.",
  },
];

/** Web fonts for the preview only. Cabin libraries ship no fonts. */
const LOOK_FONTS_URL =
  "https://fonts.googleapis.com/css2?family=Figtree:wght@500;600&family=IBM+Plex+Mono:wght@400&family=IBM+Plex+Sans:wght@300;500&family=Instrument+Sans:wght@400;500;600&family=Instrument+Serif&family=Manrope:wght@300;600&family=Plus+Jakarta+Sans:wght@600;700&family=Space+Grotesk:wght@500;700&display=swap";

type QuickId = "lock" | "defrost" | "lights" | "mode";

const QUICK: readonly {
  id: QuickId;
  label: string;
  glyph: (p: { className?: string }) => ReactNode;
  interaction: "vehicleQuick" | "hvacQuick";
}[] = [
  { id: "lock", label: "Lock", glyph: GlyphLock, interaction: "vehicleQuick" },
  { id: "defrost", label: "Defrost", glyph: GlyphDefrost, interaction: "hvacQuick" },
  { id: "lights", label: "Auto lights", glyph: GlyphLights, interaction: "vehicleQuick" },
  { id: "mode", label: "Sport", glyph: GlyphDriveMode, interaction: "vehicleQuick" },
];

const v = (name: string) => `var(--cabin-look-${name})`;

const panel: CSSProperties = {
  background: v("surface"),
  borderRadius: v("radius-large"),
  border: `1px solid ${v("outline")}`,
};

const label: CSSProperties = {
  fontFamily: v("font-label"),
  color: v("on-surface-variant"),
};

const display: CSSProperties = {
  fontFamily: v("font-display"),
  fontWeight: v("display-weight") as CSSProperties["fontWeight"],
};

export function LookPreviewDemo() {
  const { scheme } = useScheme();
  const [look, setLook] = useState<LookId>("quiet");
  const [drive, setDrive] = useState<DriveState>("parked");
  const [playing, setPlaying] = useState(true);
  const [quick, setQuick] = useState<Record<QuickId, boolean>>({
    lock: true,
    defrost: false,
    lights: true,
    mode: false,
  });

  const spec = LOOKS.find((l) => l.id === look) ?? LOOKS[0];
  const source = dispositionFor(drive, "mediaComplex");
  const sourceEnabled = isActivatable(true, source);

  return (
    <div>
      <link rel="stylesheet" href={LOOK_FONTS_URL} precedence="default" />
      <DemoStage>
        <div
          data-cabin-look={look}
          data-cabin-scheme={scheme}
          data-testid="look-preview"
          className="p-3 sm:p-4"
          style={{
            fontFamily: v("font-ui"),
            color: v("on-surface"),
            // Flat background on purpose: the Glass backdrop glow leans violet,
            // which the marketing craft brief rules out as a wash.
            background: v("background"),
          }}
        >
          <div className="grid gap-3 lg:grid-cols-[1.7fr_1fr_0.85fr]">
            {/* Map with next turn */}
            <div
              className="relative flex min-h-[13rem] flex-col justify-between p-4"
              style={{
                ...panel,
                backgroundImage: `repeating-linear-gradient(90deg, transparent 0 46px, ${v("outline")} 46px 47px), repeating-linear-gradient(0deg, transparent 0 46px, ${v("outline")} 46px 47px)`,
              }}
              role="img"
              aria-label="Map with next turn"
            >
              <div
                className="flex w-fit items-center gap-3 px-4 py-3"
                style={{
                  background: v("surface-container-high"),
                  borderRadius: v("radius-medium"),
                }}
              >
                <GlyphTurnRight className="h-7 w-7" />
                <div>
                  <p className="text-[1.75rem] leading-none tabular-nums" style={display}>
                    400 m
                  </p>
                  <p className="mt-1 text-status" style={label}>
                    Market St
                  </p>
                </div>
              </div>
              <p
                className="w-fit px-3 py-1.5 text-status tabular-nums"
                style={{
                  ...label,
                  background: v("surface-container-high"),
                  borderRadius: v("radius-control"),
                }}
              >
                Arrive 12:48 · 18 min
              </p>
            </div>

            {/* Now playing */}
            <div className="flex flex-col gap-3 p-4" style={panel}>
              <div
                className="flex aspect-[4/3] items-center justify-center"
                style={{
                  background: v("primary-container"),
                  color: v("on-primary-container"),
                  borderRadius: v("radius-medium"),
                }}
              >
                <ArtworkWellMark className="h-10 w-10" />
              </div>
              <div className="min-w-0">
                <p className="truncate text-title">Quiet roads</p>
                <p className="truncate text-status" style={label}>
                  Cabin Media
                </p>
              </div>
              <div className="mt-auto flex items-center justify-between gap-2">
                <TransportButton label="Previous">
                  <GlyphPrev className="h-5 w-5" />
                </TransportButton>
                <button
                  type="button"
                  aria-label={playing ? "Pause" : "Play"}
                  onClick={() => setPlaying((p) => !p)}
                  className="flex h-12 w-12 items-center justify-center"
                  style={{
                    background: v("primary"),
                    color: v("on-primary"),
                    borderRadius: v("radius-control"),
                  }}
                >
                  {playing ? (
                    <GlyphPause className="h-5 w-5" />
                  ) : (
                    <GlyphPlay className="h-5 w-5" />
                  )}
                </button>
                <TransportButton label="Next">
                  <GlyphNext className="h-5 w-5" />
                </TransportButton>
              </div>
              <button
                type="button"
                aria-disabled={!sourceEnabled}
                className="min-h-[2.5rem] px-3 text-status"
                style={{
                  ...label,
                  background: v("surface-container-high"),
                  borderRadius: v("radius-control"),
                  opacity: quietOpacity(source),
                  cursor: sourceEnabled ? "pointer" : "not-allowed",
                }}
              >
                {sourceEnabled ? "Source" : "Source · when parked"}
              </button>
            </div>

            {/* Energy + quick controls */}
            <div className="flex flex-col gap-3">
              <div className="p-4" style={panel}>
                <p className="text-status" style={label}>
                  Battery
                </p>
                <p className="mt-1 text-[2rem] leading-none tabular-nums" style={display}>
                  72%
                </p>
                <div
                  className="mt-3 h-1.5 overflow-hidden"
                  style={{
                    background: v("surface-container-high"),
                    borderRadius: v("radius-control"),
                  }}
                >
                  <div
                    className="h-full w-[72%]"
                    style={{ background: "var(--cabin-safety-charging)" }}
                  />
                </div>
                <p className="mt-2 text-status tabular-nums" style={label}>
                  318 km range
                </p>
              </div>
              <div className="grid flex-1 grid-cols-2 gap-3">
                {QUICK.map((q) => {
                  const on = quick[q.id];
                  const Glyph = q.glyph;
                  const gate = dispositionFor(drive, q.interaction);
                  return (
                    <button
                      key={q.id}
                      type="button"
                      aria-pressed={on}
                      disabled={!isActivatable(true, gate)}
                      onClick={() =>
                        setQuick((prev) => ({ ...prev, [q.id]: !prev[q.id] }))
                      }
                      className="flex min-h-[4.75rem] flex-col items-start justify-between gap-2 p-3 text-left text-status transition-colors duration-200 ease-cabin"
                      style={{
                        fontFamily: v("font-label"),
                        borderRadius: v("radius-medium"),
                        border: `1px solid ${v("outline")}`,
                        background: on
                          ? v("primary-container")
                          : v("surface"),
                        color: on
                          ? v("on-primary-container")
                          : v("on-surface-variant"),
                      }}
                    >
                      <Glyph className="h-5 w-5" />
                      {q.label}
                    </button>
                  );
                })}
              </div>
            </div>
          </div>

          <div
            className="mt-3 flex items-center gap-2 px-3 py-2 text-status"
            style={{
              background: "var(--cabin-safety-warning-container)",
              color: "var(--cabin-safety-warning)",
              borderRadius: v("radius-medium"),
            }}
          >
            <span
              aria-hidden
              className="h-2 w-2 shrink-0 rounded-full"
              style={{ background: "var(--cabin-safety-warning)" }}
            />
            Tire pressure low · rear left
          </div>
        </div>
      </DemoStage>

      <div className="mt-4 grid gap-4 lg:grid-cols-[1.6fr_1fr]">
        <DemoRail label="Look">
          <SegmentedControl
            ariaLabel="Look preset"
            value={look}
            onChange={setLook}
            options={LOOKS.map((l) => ({ id: l.id, label: l.label }))}
          />
          <p className="text-status text-on-surface-variant">{spec.note}</p>
        </DemoRail>
        <DemoRail label="Drive">
          <SegmentedControl
            ariaLabel="Drive state for look preview"
            value={drive}
            onChange={setDrive}
            fill="quiet"
            options={[
              { id: "parked", label: "Parked" },
              { id: "moving", label: "Moving" },
            ]}
          />
          <p className="text-status text-on-surface-variant">
            {drive === "moving"
              ? "Moving: transport and single-tap controls stay Allow; source picker is Block."
              : "Parked: every control Allow."}
          </p>
        </DemoRail>
      </div>
    </div>
  );
}

function TransportButton({
  label: aria,
  children,
}: {
  label: string;
  children: ReactNode;
}) {
  return (
    <button
      type="button"
      aria-label={aria}
      className="flex h-12 w-12 items-center justify-center"
      style={{
        background: v("surface-container-high"),
        color: v("on-surface"),
        borderRadius: v("radius-control"),
      }}
    >
      {children}
    </button>
  );
}
