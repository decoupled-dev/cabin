"use client";

import { useMemo, useState } from "react";
import {
  StatusBarDemo,
  SystemBarDemo,
} from "@/components/demo-chrome-bars";
import { ClimateTileDemo } from "@/components/demo-climate-tile";
import { MediaNowPlayingDemo } from "@/components/demo-media-now-playing";
import { InstrumentFrame } from "@/components/instrument-frame";
import { SegmentedControl } from "@/components/segmented-control";
import {
  ThemePreviewAnalog,
  type AnalogPlay,
} from "@/components/theme-preview-analog";
import { useCssVar } from "@/lib/use-css-var";
import {
  KIT_CATALOG,
  KIT_CATALOG_BY_ID,
  KIT_FAMILIES,
  entriesForFamily,
  familyCounts,
  familyMeta,
  themeBoardEntries,
  type KitCatalogEntry,
} from "@/lib/kit-catalog";
import {
  dispositionForInteraction,
  type DriveState,
} from "@/lib/demo-signal";

type FamilyFilter = "theme" | string;

const KEY_COLORS = [
  { name: "Primary", cssVar: "--primary", locked: false },
  { name: "Secondary", cssVar: "--secondary", locked: false },
  { name: "Surface", cssVar: "--surface", locked: false },
  { name: "Error", cssVar: "--error", locked: true },
  { name: "Climate", cssVar: "--climate", locked: false },
  { name: "Charging", cssVar: "--charging", locked: true },
] as const;

export function CabinThemeBuilder() {
  const [family, setFamily] = useState<FamilyFilter>("theme");
  const [selectedId, setSelectedId] = useState("button");
  const [drive, setDrive] = useState<DriveState>("parked");
  const [play, setPlay] = useState<AnalogPlay>("default");
  const counts = useMemo(() => familyCounts(), []);

  const selected =
    KIT_CATALOG_BY_ID.get(selectedId) ?? KIT_CATALOG_BY_ID.get("button")!;
  const board =
    family === "theme" ? themeBoardEntries() : entriesForFamily(family);

  function selectEntry(entry: KitCatalogEntry) {
    setSelectedId(entry.id);
  }

  return (
    <div
      id="theme"
      className="scroll-mt-28 border-y border-[var(--outline-subtle)] bg-[var(--background)]"
      data-testid="cabin-theme-builder"
    >
      <div className="page-frame py-5">
        <p className="kicker text-on-surface-variant">Theme Builder</p>
        <p className="mt-2 max-w-2xl text-status text-on-surface-variant text-balance">
          Key colors follow Night / Day in the header. Forest primary stays
          brand; warning, error, and charging stay locked. Preview is the Cabin
          kit — not a phone mock.
        </p>
      </div>

      <div className="grid border-t border-[var(--outline-subtle)] lg:grid-cols-[16.5rem_minmax(0,1fr)_18rem]">
        <ThemeColorRail
          family={family}
          counts={counts}
          onFamily={(next) => {
            setFamily(next);
            if (next !== "theme") {
              const first = entriesForFamily(next)[0];
              if (first) setSelectedId(first.id);
            }
          }}
          drive={drive}
          onDrive={setDrive}
        />

        <ThemePreviewPane
          family={family}
          board={board}
          selectedId={selected.id}
          play={play}
          drive={drive}
          onSelect={selectEntry}
        />

        <ThemeInspector
          entry={selected}
          play={play}
          onPlay={setPlay}
          drive={drive}
        />
      </div>
    </div>
  );
}

function ThemeColorRail({
  family,
  counts,
  onFamily,
  drive,
  onDrive,
}: {
  family: FamilyFilter;
  counts: Record<string, number>;
  onFamily: (id: FamilyFilter) => void;
  drive: DriveState;
  onDrive: (drive: DriveState) => void;
}) {
  return (
    <aside className="border-b border-[var(--outline-subtle)] lg:sticky lg:top-16 lg:h-[calc(100vh-4rem)] lg:overflow-y-auto lg:border-b-0 lg:border-r">
      <div className="px-5 py-4">
        <p className="kicker text-on-surface-variant">Key colors</p>
        <ul className="mt-3 grid grid-cols-3 gap-2.5">
          {KEY_COLORS.map((role) => (
            <KeyColorWell key={role.name} role={role} />
          ))}
        </ul>
        <p className="mt-2 text-status text-on-surface-variant">
          Token roles. OEM remaps tokens — not this surface.
        </p>
      </div>

      <div className="border-t border-[var(--outline-subtle)] px-5 py-3">
        <p className="kicker mb-2 text-on-surface-variant">Drive</p>
        <SegmentedControl
          ariaLabel="Drive state"
          value={drive}
          onChange={onDrive}
          fill="quiet"
          size="sm"
          options={[
            { id: "parked", label: "Parked" },
            { id: "moving", label: "Moving" },
          ]}
        />
      </div>

      <nav
        aria-label="Component families"
        className="border-t border-[var(--outline-subtle)] px-2 py-3"
      >
        <p className="px-3 pb-2 kicker text-on-surface-variant">Families</p>
        <FamilyRow
          label="Theme"
          meta={`${KIT_CATALOG.length} rows`}
          active={family === "theme"}
          onClick={() => onFamily("theme")}
        />
        {KIT_FAMILIES.map((item) => (
          <FamilyRow
            key={item.id}
            label={item.label}
            meta={String(counts[item.id] ?? 0)}
            accent={item.accent}
            active={family === item.id}
            onClick={() => onFamily(item.id)}
          />
        ))}
      </nav>
    </aside>
  );
}

function KeyColorWell({
  role,
}: {
  role: (typeof KEY_COLORS)[number];
}) {
  const resolved = useCssVar(role.cssVar);
  return (
    <li className="flex flex-col items-start gap-1.5">
      <span
        className="h-10 w-10 rounded-full border border-[var(--outline-subtle)] sm:h-11 sm:w-11"
        style={{ background: `var(${role.cssVar})` }}
        role="img"
        aria-label={`${role.name} ${resolved}`}
      />
      <span className="text-[0.6875rem] font-medium uppercase tracking-[0.08em] text-on-surface">
        {role.name}
        {role.locked ? " · lock" : ""}
      </span>
    </li>
  );
}

function FamilyRow({
  label,
  meta,
  accent,
  active,
  onClick,
}: {
  label: string;
  meta: string;
  accent?: string;
  active: boolean;
  onClick: () => void;
}) {
  return (
    <button
      type="button"
      onClick={onClick}
      aria-current={active ? "true" : undefined}
      className={`flex w-full items-center justify-between gap-2 rounded-md px-3 py-2 text-left text-status transition-colors duration-200 ease-cabin ${
        active
          ? "bg-[color-mix(in_srgb,var(--primary)_16%,transparent)] text-on-surface"
          : "text-on-surface-variant hover:text-on-surface"
      }`}
    >
      <span className="flex items-center gap-2">
        {accent ? (
          <span
            className="h-1.5 w-1.5 rounded-full"
            style={{ background: accent }}
            aria-hidden
          />
        ) : null}
        {label}
      </span>
      <span className="tabular-nums text-on-surface-variant">{meta}</span>
    </button>
  );
}

function ThemePreviewPane({
  family,
  board,
  selectedId,
  play,
  drive,
  onSelect,
}: {
  family: FamilyFilter;
  board: KitCatalogEntry[];
  selectedId: string;
  play: AnalogPlay;
  drive: DriveState;
  onSelect: (entry: KitCatalogEntry) => void;
}) {
  const heading =
    family === "theme"
      ? "Preview"
      : familyMeta(family).label;

  return (
    <section className="min-w-0 border-b border-[var(--outline-subtle)] bg-[var(--hmi-bezel)] lg:border-b-0 lg:border-r">
      <div className="flex items-baseline justify-between gap-3 px-5 py-4">
        <h2 className="font-display text-title text-on-surface">{heading}</h2>
        <p className="text-status text-on-surface-variant">
          {board.length} {board.length === 1 ? "component" : "components"}
        </p>
      </div>
      <div className="px-3 pb-5 sm:px-5">
        <InstrumentFrame
          status={<StatusBarDemo embedded />}
          system={<SystemBarDemo embedded drive={drive} />}
          contentClassName="bg-[var(--surface)] p-3 sm:p-4"
        >
          <div
            className="grid gap-3 sm:grid-cols-2 xl:grid-cols-3"
            data-testid="theme-preview-board"
          >
            {board.map((entry) => (
              <ThemePreviewAnalog
                key={entry.id}
                entry={entry}
                play={selectedId === entry.id ? play : "default"}
                disposition={dispositionForInteraction(drive, entry.interaction)}
                selected={selectedId === entry.id}
                onSelect={() => onSelect(entry)}
              />
            ))}
          </div>
        </InstrumentFrame>
      </div>
    </section>
  );
}

function ThemeInspector({
  entry,
  play,
  onPlay,
  drive,
}: {
  entry: KitCatalogEntry;
  play: AnalogPlay;
  onPlay: (play: AnalogPlay) => void;
  drive: DriveState;
}) {
  const meta = familyMeta(entry.family);
  const disposition = dispositionForInteraction(drive, entry.interaction);
  const stacks = entry.stacks
    .map((stack) => (stack === "compose" ? "Compose" : "Views"))
    .join(" · ");

  return (
    <aside className="px-5 py-5 lg:sticky lg:top-16 lg:h-[calc(100vh-4rem)] lg:overflow-y-auto">
      <p className="kicker text-on-surface-variant">Component</p>
      <h3 className="mt-2 font-display text-title text-on-surface">
        {entry.title}
      </h3>
      <p className="mt-1 text-status text-on-surface-variant">
        {meta.label} · {entry.handwritten ? "Alpha" : "Experimental"} · {stacks}
        {entry.module === "gauges" ? " · gauges" : ""}
      </p>
      <p className="mt-3 text-status text-on-surface-variant">
        {entry.interaction}
        {disposition === "block"
          ? " — Restricted while driving"
          : disposition === "substitute"
            ? " — Substitute while moving"
            : ""}
      </p>

      {entry.variants.length ? (
        <p className="mt-3 text-status text-on-surface-variant">
          Variants: {entry.variants.join(", ")}
        </p>
      ) : null}

      <div className="mt-5">
        <p className="kicker mb-3 text-on-surface-variant">State</p>
        <SegmentedControl
          ariaLabel="Scaffold state"
          value={play}
          onChange={onPlay}
          fill="quiet"
          size="sm"
          options={[
            { id: "default", label: "Default" },
            { id: "focused", label: "Focused" },
            { id: "loading", label: "Loading" },
            { id: "error", label: "Error" },
            { id: "restricted", label: "Restricted" },
          ]}
        />
      </div>

      <div className="mt-6" data-testid="theme-inspector-stage">
        {entry.id === "climate-tile" ? (
          <ClimateTileDemo compact lockedDrive={drive} />
        ) : entry.id === "media-now-playing" ? (
          <MediaNowPlayingDemo compact lockedDrive={drive} />
        ) : (
          <ThemePreviewAnalog
            entry={entry}
            play={play}
            disposition={disposition}
            selected={play === "focused"}
          />
        )}
      </div>
    </aside>
  );
}
