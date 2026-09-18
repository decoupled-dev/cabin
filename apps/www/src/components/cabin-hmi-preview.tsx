"use client";

import type { ReactNode } from "react";
import {
  GlyphNext,
  GlyphPause,
  GlyphPrev,
  GlyphSignal,
  SystemGlyph,
  type SystemGlyphId,
} from "@/components/cabin-glyphs";
import { InstrumentFrame } from "@/components/instrument-frame";
import { useScheme } from "@/components/scheme-provider";

const SYSTEM_SLOTS: { id: SystemGlyphId; label: string }[] = [
  { id: "home", label: "Home" },
  { id: "maps", label: "Maps" },
  { id: "media", label: "Media" },
  { id: "hvac", label: "HVAC" },
  { id: "apps", label: "Apps" },
];

/**
 * Hero instrument — one composition: brand story + HMI tiles inside
 * status / content / system chrome. Illustrative; not a live catalog.
 */
export function CabinHmiPreview({ children }: { children: ReactNode }) {
  const { scheme } = useScheme();

  return (
    <div className="animate-fade-rise" style={{ animationDelay: "80ms" }}>
      <InstrumentFrame
        status={<HeroStatusBar />}
        system={<HeroSystemBar />}
        contentClassName="grid lg:grid-cols-[minmax(0,0.92fr)_minmax(0,1.18fr)]"
      >
        <div className="flex flex-col justify-center border-b border-[var(--outline-subtle)] px-6 py-8 sm:px-8 sm:py-10 lg:border-b-0 lg:border-r lg:py-12 lg:pl-10 lg:pr-8">
          {children}
        </div>
        <div className="min-h-[280px] bg-[var(--surface)] p-3.5 sm:min-h-[320px] sm:p-4 lg:min-h-[348px]">
          <CabinHmiTiles />
        </div>
      </InstrumentFrame>
      <p className="mt-3 text-center text-status text-on-surface-variant">
        {scheme === "night" ? "Night scheme" : "Day scheme"} · status · content
        · system
      </p>
    </div>
  );
}

function HeroStatusBar() {
  return (
    <div
      className="flex h-12 items-center justify-between bg-[var(--container)] px-4 text-[var(--on-container)] sm:px-5"
      aria-hidden
    >
      <div className="flex min-w-0 items-center gap-3">
        <span className="inline-flex h-6 w-6 items-center justify-center">
          <GlyphSignal className="h-full w-full" />
        </span>
        <span className="h-1.5 w-1.5 rounded-full bg-success" />
        <span className="text-status tabular-nums tracking-wide">P · 72°F</span>
        <span className="hidden h-3 w-px bg-[var(--outline-subtle)] sm:block" />
        <span className="hidden items-center gap-1.5 sm:inline-flex">
          <span className="h-1.5 w-1.5 rounded-full bg-warning" />
          <span className="text-status text-warning">Lane assist</span>
        </span>
      </div>
      <span className="text-status tabular-nums">12:41</span>
    </div>
  );
}

function HeroSystemBar() {
  return (
    <div
      className="flex h-16 items-stretch justify-around border-t border-[var(--outline-subtle)] bg-[var(--container)] px-1 sm:h-[4.5rem]"
      aria-hidden
    >
      {SYSTEM_SLOTS.map((slot) => {
        const active = slot.id === "media";
        return (
          <div
            key={slot.id}
            className="flex min-h-[48px] min-w-[52px] flex-1 flex-col items-center justify-center gap-1 rounded-md"
            style={{
              background: active
                ? "color-mix(in srgb, var(--primary) 16%, transparent)"
                : undefined,
            }}
          >
            <span
              className="inline-flex h-6 w-6 items-center justify-center sm:h-7 sm:w-7"
              style={{
                color: active ? "var(--primary)" : "var(--on-container)",
                opacity: active ? 1 : 0.72,
              }}
            >
              <SystemGlyph id={slot.id} className="h-full w-full" />
            </span>
            <span
              className={`text-[0.6875rem] leading-none ${
                active ? "text-on-surface" : "text-on-surface-variant"
              }`}
            >
              {slot.label}
            </span>
          </div>
        );
      })}
    </div>
  );
}

function CabinHmiTiles() {
  return (
    <div className="grid h-full gap-3 sm:grid-cols-[1.15fr_0.85fr] sm:gap-3.5">
      <div className="flex flex-col rounded-md border border-[var(--outline-subtle)] bg-[var(--surface-high)] p-4 sm:p-5">
        <div className="flex items-start gap-3">
          <div
            className="relative h-[4.5rem] w-[4.5rem] shrink-0 overflow-hidden rounded-md border border-outline"
            style={{
              background:
                "radial-gradient(circle at 32% 28%, color-mix(in srgb, var(--on-surface) 18%, transparent), transparent 46%), radial-gradient(circle at 78% 76%, color-mix(in srgb, var(--primary) 26%, transparent), transparent 50%), var(--surface-variant)",
            }}
          >
            <span
              className="absolute bottom-2 left-2 h-3 w-1 bg-[var(--media)]"
              data-testid="media-art-accent-mark"
            />
          </div>
          <div className="min-w-0 pt-0.5">
            <p className="kicker text-on-surface-variant">Now playing</p>
            <p className="mt-2 font-display text-title text-on-surface">
              Quiet roads
            </p>
            <p className="mt-1 text-status text-on-surface-variant">
              Cabin Media
            </p>
          </div>
        </div>
        <div className="mt-5 h-1 overflow-hidden rounded-sm bg-[var(--surface-variant)]">
          <div
            className="h-full w-[62%] origin-left rounded-sm bg-[var(--on-surface)]/55 animate-hmi-line"
            style={{ animationDelay: "200ms" }}
          />
        </div>
        <div className="mt-auto flex items-center justify-center gap-2 pt-4">
          <TransportChip>
            <GlyphPrev className="h-5 w-5" />
          </TransportChip>
          <TransportChip accent>
            <GlyphPause className="h-5 w-5" />
          </TransportChip>
          <TransportChip>
            <GlyphNext className="h-5 w-5" />
          </TransportChip>
        </div>
      </div>

      <div className="grid gap-3 sm:gap-3.5">
        <div className="rounded-md border border-[var(--outline-subtle)] bg-[var(--surface-high)] p-4">
          <div className="flex items-center justify-between">
            <p className="kicker text-on-surface-variant">Climate</p>
            <span className="h-2 w-1 rounded-sm bg-climate" />
          </div>
          <p className="mt-2 font-display text-[2.5rem] leading-none tabular-nums text-on-surface">
            21°
          </p>
          <p className="mt-2 text-status text-on-surface-variant">
            Driver zone
          </p>
        </div>

        <div className="rounded-md border border-[var(--outline-subtle)] bg-[var(--surface-high)] p-4">
          <div className="flex items-center justify-between">
            <p className="kicker text-on-surface-variant">Charge</p>
            <span className="inline-flex items-center gap-1.5 text-status text-charging">
              <span className="h-1.5 w-1.5 rounded-full bg-charging" />
              Charging
            </span>
          </div>
          <div className="mt-2 flex items-baseline gap-2">
            <p className="font-display text-title tabular-nums text-on-surface">
              78%
            </p>
            <span className="text-status text-on-surface-variant">312 km</span>
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
  );
}

function TransportChip({
  children,
  accent = false,
}: {
  children: ReactNode;
  accent?: boolean;
}) {
  return (
    <span
      className="inline-flex h-10 w-10 items-center justify-center rounded-md text-on-surface"
      style={{
        background: accent
          ? "color-mix(in srgb, var(--primary) 18%, var(--surface-high))"
          : "var(--surface-variant)",
        border: accent
          ? "1px solid color-mix(in srgb, var(--primary) 35%, transparent)"
          : "1px solid var(--outline-subtle)",
      }}
    >
      {children}
    </span>
  );
}
