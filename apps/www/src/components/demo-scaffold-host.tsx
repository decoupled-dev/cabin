"use client";

import { useState } from "react";
import { DemoRail, DemoStage } from "@/components/demo-stage";
import { SegmentedControl } from "@/components/segmented-control";
import { quietOpacity } from "@/lib/demo-signal";

type FamilyId = "action" | "hvac" | "media";
type Playground = "default" | "focused" | "loading" | "error" | "restricted";

const FAMILIES: readonly {
  id: FamilyId;
  title: string;
  family: string;
  accent: string;
}[] = [
  { id: "action", title: "Button", family: "action", accent: "var(--primary)" },
  { id: "hvac", title: "Fan speed", family: "hvac", accent: "var(--climate)" },
  { id: "media", title: "Mini player", family: "media", accent: "var(--media)" },
];

/**
 * Web analog of Experimental CabinScaffoldHost — family mark, focus ring,
 * Restriction Engine copy, Activate. Not a 100-row inventory.
 */
export function KitInspectorDemo() {
  const [familyId, setFamilyId] = useState<FamilyId>("action");
  const [play, setPlay] = useState<Playground>("default");
  const spec = FAMILIES.find((f) => f.id === familyId) ?? FAMILIES[0];

  return (
    <div>
      <DemoStage>
        <div className="bg-[var(--surface)] p-4 sm:p-5">
          <p className="kicker mb-3 text-on-surface-variant">Inspector</p>
          <ScaffoldHost
            title={spec.title}
            family={spec.family}
            accent={spec.accent}
            play={play}
          />
        </div>
      </DemoStage>

      <div className="mt-4 grid gap-4 sm:grid-cols-2">
        <DemoRail label="Family">
          <SegmentedControl
            ariaLabel="Scaffold family"
            value={familyId}
            onChange={setFamilyId}
            options={FAMILIES.map((f) => ({ id: f.id, label: f.title }))}
          />
        </DemoRail>
        <DemoRail label="State">
          <SegmentedControl
            ariaLabel="Scaffold state"
            value={play}
            onChange={setPlay}
            fill="quiet"
            options={[
              { id: "default", label: "Default" },
              { id: "focused", label: "Focused" },
              { id: "loading", label: "Loading" },
              { id: "error", label: "Error" },
              { id: "restricted", label: "Restricted" },
            ]}
          />
        </DemoRail>
      </div>
      <p className="mt-3 text-status text-on-surface-variant">
        Shared Experimental chrome — family accent is a stripe only. Android
        sample: samples/kitchen-sink.
      </p>
    </div>
  );
}

function ScaffoldHost({
  title,
  family,
  accent,
  play,
}: {
  title: string;
  family: string;
  accent: string;
  play: Playground;
}) {
  const restricted = play === "restricted";
  const focused = play === "focused";
  const loading = play === "loading";
  const error = play === "error";
  const activatable = !restricted;
  const status = error
    ? "Error"
    : loading
      ? "Loading"
      : restricted
        ? "Restricted while driving — park to continue"
        : family;
  const border = error
    ? "var(--error)"
    : focused
      ? "var(--focus-ring)"
      : "color-mix(in srgb, var(--outline) 55%, transparent)";
  const fill = error
    ? "var(--surface)"
    : "var(--surface-high)";

  return (
    <div
      className="flex min-h-[var(--cabin-size-touch-minimum)] overflow-hidden"
      style={{
        borderRadius: "var(--cabin-shape-corner-md)",
        border: `${focused ? "var(--cabin-focus-ring-width)" : "1px"} solid ${border}`,
        background: fill,
        opacity: quietOpacity(restricted ? "block" : "allow"),
      }}
      data-testid="cabin-scaffold-host"
    >
      <span
        className="w-[var(--cabin-space-xs)] shrink-0 self-stretch"
        style={{ background: accent }}
        aria-hidden
        data-testid="cabin-scaffold-mark"
      />
      <div className="flex min-w-0 flex-1 flex-col gap-2 p-[var(--cabin-space-sm)] sm:p-3">
        <div className="flex items-baseline justify-between gap-3">
          <p className="font-display text-title text-on-surface">{title}</p>
          <span
            className="shrink-0 border border-[var(--outline-subtle)] px-2 py-0.5 text-status text-on-surface-variant"
            style={{ borderRadius: "var(--cabin-shape-corner-sm)" }}
          >
            Experimental
          </span>
        </div>
        <p
          className={`text-status ${
            error
              ? "text-error"
              : restricted
                ? "text-warning"
                : "text-on-surface-variant"
          }`}
          data-testid="cabin-scaffold-status"
        >
          {status}
        </p>
        {loading ? (
          <span
            className="h-1 w-full overflow-hidden rounded-sm"
            style={{
              background: `color-mix(in srgb, ${accent} 35%, transparent)`,
            }}
            data-testid="cabin-scaffold-loading"
            aria-hidden
          />
        ) : null}
        <button
          type="button"
          disabled={!activatable}
          className="mt-1 inline-flex min-h-[var(--cabin-size-touch-minimum)] items-center justify-center text-label font-semibold text-on-surface disabled:cursor-not-allowed"
          style={{
            borderRadius: "var(--cabin-shape-corner-md)",
            border: activatable
              ? "1px solid color-mix(in srgb, var(--primary) 35%, transparent)"
              : "1px solid var(--outline-subtle)",
            background: activatable
              ? "color-mix(in srgb, var(--primary) 18%, var(--surface-high))"
              : "var(--surface)",
          }}
          data-testid="cabin-scaffold-activate"
        >
          {activatable ? "Activate" : "Unavailable"}
        </button>
      </div>
    </div>
  );
}
