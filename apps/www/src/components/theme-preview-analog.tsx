"use client";

import type { KitCatalogEntry } from "@/lib/kit-catalog";
import { familyAccent } from "@/lib/kit-catalog";
import { quietOpacity, type GateDisposition } from "@/lib/demo-signal";

export type AnalogPlay = "default" | "focused" | "loading" | "error" | "restricted";

export function analogKind(entry: KitCatalogEntry): AnalogKind {
  const id = entry.id;
  if (id === "climate-tile") return "climate";
  if (id === "media-now-playing" || id === "mini-player") return "media";
  if (id === "charge-session-card" || id.includes("charge") || entry.family === "ev") {
    return "charge";
  }
  if (id.includes("lock") || id.includes("door")) return "lock";
  if (id === "fab") return "fab";
  if (id.includes("button") || id === "hold-to-confirm" || id === "safety-action") {
    return "button";
  }
  if (id === "switch" || id.includes("toggle")) return "switch";
  if (id.includes("checkbox")) return "checkbox";
  if (id.includes("radio")) return "radio";
  if (id.includes("chip")) return "chip";
  if (id === "tabs" || id.includes("stepper") || id === "page-indicator") return "tabs";
  if (id.includes("dock") || id.includes("rail") || id === "bottom-bar") return "dock";
  if (
    id === "card" ||
    id === "tile" ||
    id === "surface" ||
    id === "panel" ||
    id === "banner"
  ) {
    return "card";
  }
  if (id.includes("slider") || id === "fan-speed") return "slider";
  if (id.includes("field") || id.includes("search") || id.includes("keyboard")) {
    return "field";
  }
  if (id.includes("list") || id === "app-grid" || entry.family === "collection") {
    return "list";
  }
  if (id.includes("progress")) return "progress";
  if (id.includes("gauge") || entry.family === "gauges") return "gauge";
  if (id === "snackbar" || id === "toast") return "snackbar";
  if (id === "preference" || entry.family === "settings") return "preference";
  return "scaffold";
}

export type AnalogKind =
  | "button"
  | "fab"
  | "switch"
  | "checkbox"
  | "radio"
  | "chip"
  | "tabs"
  | "dock"
  | "card"
  | "slider"
  | "field"
  | "list"
  | "progress"
  | "gauge"
  | "climate"
  | "media"
  | "charge"
  | "lock"
  | "snackbar"
  | "preference"
  | "scaffold";

export function ThemePreviewAnalog({
  entry,
  play,
  disposition,
  selected,
  onSelect,
}: {
  entry: KitCatalogEntry;
  play: AnalogPlay;
  disposition: GateDisposition;
  selected?: boolean;
  onSelect?: () => void;
}) {
  const accent = familyAccent(entry.family);
  const restricted = play === "restricted" || disposition === "block";
  const focused = play === "focused" || selected;
  const loading = play === "loading";
  const error = play === "error";
  const kind = analogKind(entry);
  const opacity = quietOpacity(restricted ? "block" : disposition);

  return (
    <button
      type="button"
      onClick={onSelect}
      data-testid={`kit-analog-${entry.id}`}
      aria-pressed={selected}
      className="flex w-full flex-col gap-2 p-3 text-left transition-colors duration-200 ease-cabin"
      style={{
        borderRadius: "var(--cabin-shape-corner-md)",
        border: `${focused ? "var(--cabin-focus-ring-width)" : "1px"} solid ${
          error
            ? "var(--error)"
            : focused
              ? "var(--focus-ring)"
              : "color-mix(in srgb, var(--outline) 45%, transparent)"
        }`,
        background: "var(--surface-high)",
        opacity,
      }}
    >
      <div className="flex items-center justify-between gap-2">
        <span className="text-status text-on-surface-variant">{entry.title}</span>
        <span
          className="h-1.5 w-1.5 shrink-0 rounded-full"
          style={{ background: accent }}
          aria-hidden
        />
      </div>
      <div className="min-h-[3.25rem]">
        <AnalogBody
          kind={kind}
          entry={entry}
          accent={accent}
          loading={loading}
          error={error}
          restricted={restricted}
        />
      </div>
    </button>
  );
}

function AnalogBody({
  kind,
  entry,
  accent,
  loading,
  error,
  restricted,
}: {
  kind: AnalogKind;
  entry: KitCatalogEntry;
  accent: string;
  loading: boolean;
  error: boolean;
  restricted: boolean;
}) {
  const disabled = restricted;
  switch (kind) {
    case "button":
      return (
        <div className="flex flex-wrap gap-1.5">
          {(entry.variants.length ? entry.variants.slice(0, 4) : ["filled"]).map(
            (variant) => (
              <span
                key={variant}
                className="inline-flex min-h-10 items-center px-3 text-status font-semibold"
                style={{
                  borderRadius: "var(--cabin-shape-corner-md)",
                  ...buttonVariantStyle(variant, disabled),
                }}
              >
                {variant === "icon" ? "＋" : variant}
              </span>
            ),
          )}
        </div>
      );
    case "fab":
      return (
        <span
          className="inline-flex h-12 w-12 items-center justify-center text-title font-semibold text-on-primary"
          style={{
            borderRadius: "999px",
            background: disabled
              ? "var(--surface-variant)"
              : "var(--primary)",
            color: disabled ? "var(--on-surface-variant)" : "var(--on-primary)",
          }}
        >
          ＋
        </span>
      );
    case "switch":
      return (
        <span
          className="relative inline-flex h-7 w-12 items-center"
          style={{
            borderRadius: "999px",
            background: disabled
              ? "var(--surface-variant)"
              : "color-mix(in srgb, var(--primary) 70%, var(--surface))",
          }}
        >
          <span
            className="absolute right-0.5 h-6 w-6 rounded-full bg-[var(--on-primary)]"
            aria-hidden
          />
        </span>
      );
    case "checkbox":
      return (
        <span
          className="inline-flex h-7 w-7 items-center justify-center text-on-primary"
          style={{
            borderRadius: "var(--cabin-shape-corner-sm)",
            background: disabled ? "var(--surface-variant)" : "var(--primary)",
          }}
        >
          ✓
        </span>
      );
    case "radio":
      return (
        <span
          className="inline-flex h-7 w-7 items-center justify-center rounded-full border-2"
          style={{ borderColor: disabled ? "var(--outline)" : "var(--primary)" }}
        >
          <span
            className="h-3 w-3 rounded-full"
            style={{
              background: disabled ? "var(--outline)" : "var(--primary)",
            }}
          />
        </span>
      );
    case "chip":
      return (
        <div className="flex flex-wrap gap-1.5">
          {(entry.variants.length ? entry.variants.slice(0, 3) : ["filter"]).map(
            (variant) => (
              <span
                key={variant}
                className="inline-flex min-h-8 items-center px-2.5 text-status"
                style={{
                  borderRadius: "999px",
                  border: "1px solid var(--outline-subtle)",
                  background: "var(--surface)",
                  color: "var(--on-surface)",
                }}
              >
                {variant}
              </span>
            ),
          )}
        </div>
      );
    case "tabs":
      return (
        <div
          className="flex gap-px overflow-hidden"
          style={{
            borderRadius: "var(--cabin-shape-corner-md)",
            background: "var(--outline-subtle)",
          }}
        >
          {["One", "Two", "Three"].map((label, index) => (
            <span
              key={label}
              className="flex-1 px-2 py-2 text-center text-status"
              style={{
                background:
                  index === 0 ? "var(--primary)" : "var(--control-fill)",
                color: index === 0 ? "var(--on-primary)" : "var(--on-surface-variant)",
              }}
            >
              {label}
            </span>
          ))}
        </div>
      );
    case "dock":
      return (
        <div className="flex justify-between gap-2">
          {["Home", "Maps", "Media", "HVAC"].map((label, index) => (
            <span
              key={label}
              className="flex flex-1 flex-col items-center gap-1 py-1 text-[0.6875rem] text-on-surface-variant"
              style={{
                background:
                  index === 2
                    ? "color-mix(in srgb, var(--primary) 16%, transparent)"
                    : undefined,
                borderRadius: "var(--cabin-shape-corner-sm)",
              }}
            >
              <span
                className="h-2 w-2 rounded-full"
                style={{
                  background: index === 2 ? "var(--primary)" : "var(--outline)",
                }}
              />
              {label}
            </span>
          ))}
        </div>
      );
    case "card":
      return (
        <div
          className="px-3 py-2"
          style={{
            borderRadius: "var(--cabin-shape-corner-md)",
            background: "var(--surface)",
            border: "1px solid var(--outline-subtle)",
          }}
        >
          <p className="text-label text-on-surface">{entry.title}</p>
          <p className="mt-1 text-status text-on-surface-variant">
            Surface · glanceable
          </p>
        </div>
      );
    case "slider":
      return (
        <div className="flex flex-col justify-center gap-2 py-2">
          <div className="relative h-1 rounded-sm bg-[var(--surface-variant)]">
            <div
              className="absolute inset-y-0 left-0 w-2/3 rounded-sm"
              style={{
                background: disabled ? "var(--outline)" : "var(--primary)",
              }}
            />
            <span
              className="absolute top-1/2 h-3.5 w-3.5 -translate-y-1/2 rounded-full"
              style={{
                left: "62%",
                background: disabled ? "var(--outline)" : "var(--primary)",
              }}
            />
          </div>
        </div>
      );
    case "field":
      return (
        <div
          className="flex min-h-11 items-center px-3 text-status text-on-surface-variant"
          style={{
            borderRadius: "var(--cabin-shape-corner-md)",
            border: "1px solid var(--outline)",
            background: "var(--surface)",
          }}
        >
          {restricted ? "Unavailable while moving" : entry.title}
        </div>
      );
    case "list":
      return (
        <div className="flex flex-col gap-1.5">
          {["Primary", "Secondary", "Tertiary"].map((row) => (
            <div
              key={row}
              className="flex items-center justify-between gap-2 border-b border-[var(--outline-subtle)] py-1 last:border-b-0"
            >
              <span className="text-status text-on-surface">{row}</span>
              <span className="text-status text-on-surface-variant">›</span>
            </div>
          ))}
        </div>
      );
    case "progress":
      return (
        <div className="flex flex-col justify-center gap-2 py-3">
          <div className="h-1 overflow-hidden rounded-sm bg-[var(--surface-variant)]">
            <div
              className="h-full w-2/5 rounded-sm"
              style={{
                background: loading ? accent : "var(--primary)",
              }}
            />
          </div>
        </div>
      );
    case "gauge":
      return (
        <svg viewBox="0 0 64 36" className="h-9 w-full" aria-hidden>
          <path
            d="M8 32 A24 24 0 0 1 56 32"
            fill="none"
            stroke="var(--surface-variant)"
            strokeWidth="5"
            strokeLinecap="round"
          />
          <path
            d="M8 32 A24 24 0 0 1 40 10"
            fill="none"
            stroke={error ? "var(--error)" : "var(--primary)"}
            strokeWidth="5"
            strokeLinecap="round"
          />
        </svg>
      );
    case "climate":
      return (
        <div className="flex items-center gap-3">
          <span
            className="w-[var(--cabin-space-xs)] self-stretch"
            style={{ background: "var(--climate)" }}
            aria-hidden
          />
          <div>
            <p className="font-display text-title tabular-nums text-on-surface">
              22°
            </p>
            <p className="text-status text-on-surface-variant">Fan 3 · Driver</p>
          </div>
        </div>
      );
    case "media":
      return (
        <div className="flex items-center gap-3">
          <span
            className="h-10 w-10 shrink-0"
            style={{
              background: "var(--surface-variant)",
              borderRadius: "var(--cabin-shape-corner-sm)",
            }}
            aria-hidden
          />
          <div className="min-w-0">
            <p className="truncate text-label text-on-surface">Quiet roads</p>
            <p className="mt-0.5 flex items-center gap-1.5 text-status text-on-surface-variant">
              <span
                className="inline-block h-2 w-[var(--cabin-space-xs)] bg-[var(--media)]"
                aria-hidden
              />
              Cabin Media
            </p>
          </div>
        </div>
      );
    case "charge":
      return (
        <div className="flex flex-col gap-2">
          <p className="font-display text-title tabular-nums text-on-surface">
            78%
          </p>
          <div className="h-1 overflow-hidden rounded-sm bg-[var(--surface-variant)]">
            <div
              className="h-full w-[78%] rounded-sm bg-[var(--charging)]"
              aria-hidden
            />
          </div>
          <p className="text-status text-on-surface-variant">Range 214 mi</p>
        </div>
      );
    case "lock":
      return (
        <div className="flex gap-2">
          {["Lock", "Unlock"].map((label, index) => (
            <span
              key={label}
              className="inline-flex min-h-10 flex-1 items-center justify-center text-status font-semibold"
              style={{
                borderRadius: "var(--cabin-shape-corner-md)",
                background:
                  index === 0 && !disabled
                    ? "color-mix(in srgb, var(--primary) 18%, var(--surface-high))"
                    : "var(--surface)",
                border: "1px solid var(--outline-subtle)",
                color: "var(--on-surface)",
              }}
            >
              {label}
            </span>
          ))}
        </div>
      );
    case "snackbar":
      return (
        <div
          className="flex items-center justify-between gap-2 px-3 py-2 text-status"
          style={{
            borderRadius: "var(--cabin-shape-corner-md)",
            background: "color-mix(in srgb, var(--on-surface) 88%, var(--surface))",
            color: "var(--surface)",
          }}
        >
          <span>{error ? "Error" : "Parked · ready"}</span>
          <span className="font-semibold">OK</span>
        </div>
      );
    case "preference":
      return (
        <div className="flex items-center justify-between gap-3">
          <div>
            <p className="text-label text-on-surface">{entry.title}</p>
            <p className="text-status text-on-surface-variant">Two-pane row</p>
          </div>
          <span
            className="relative inline-flex h-6 w-10"
            style={{
              borderRadius: "999px",
              background: "color-mix(in srgb, var(--primary) 70%, var(--surface))",
            }}
          >
            <span className="absolute right-0.5 top-0.5 h-5 w-5 rounded-full bg-[var(--on-primary)]" />
          </span>
        </div>
      );
    default:
      return (
        <div className="flex items-stretch gap-2">
          <span
            className="w-[var(--cabin-space-xs)] shrink-0 self-stretch"
            style={{ background: accent }}
            aria-hidden
          />
          <div className="min-w-0 flex-1">
            <p className="text-label text-on-surface">{entry.title}</p>
            <p className="mt-1 text-status text-on-surface-variant">
              {loading
                ? "Loading"
                : error
                  ? "Error"
                  : restricted
                    ? "Restricted while driving"
                    : entry.family}
            </p>
          </div>
        </div>
      );
  }
}

function buttonVariantStyle(
  variant: string,
  disabled: boolean,
): { background?: string; color?: string; border?: string } {
  if (disabled) {
    return {
      background: "var(--surface)",
      color: "var(--on-surface-variant)",
      border: "1px solid var(--outline-subtle)",
    };
  }
  switch (variant) {
    case "filled":
      return {
        background: "var(--primary)",
        color: "var(--on-primary)",
      };
    case "tonal":
      return {
        background: "color-mix(in srgb, var(--primary) 18%, var(--surface-high))",
        color: "var(--on-surface)",
      };
    case "outlined":
      return {
        background: "transparent",
        color: "var(--on-surface)",
        border: "1px solid var(--outline)",
      };
    case "text":
    case "icon":
      return {
        background: "transparent",
        color: "var(--primary)",
      };
    default:
      return {
        background: "color-mix(in srgb, var(--primary) 18%, var(--surface-high))",
        color: "var(--on-surface)",
      };
  }
}
