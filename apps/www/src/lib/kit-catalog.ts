/**
 * Marketing Theme Builder catalog — inventory rows plus family chrome.
 * Values come from generated kit-catalog.generated.ts (YAML inventory).
 */

import {
  KIT_CATALOG,
  type KitCatalogEntry,
} from "@/lib/kit-catalog.generated";

export { KIT_CATALOG };
export type { KitCatalogEntry };

export type KitFamilyId = (typeof KIT_FAMILIES)[number]["id"];

export const KIT_FAMILIES = [
  { id: "action", label: "Action", accent: "var(--primary)" },
  { id: "selection", label: "Selection", accent: "var(--primary)" },
  { id: "navigation", label: "Navigation", accent: "var(--secondary)" },
  { id: "surface", label: "Surface", accent: "var(--secondary)" },
  { id: "input", label: "Input", accent: "var(--primary)" },
  { id: "collection", label: "Collection", accent: "var(--secondary)" },
  { id: "feedback", label: "Feedback", accent: "var(--warning)" },
  { id: "media", label: "Media", accent: "var(--media)" },
  { id: "hvac", label: "HVAC", accent: "var(--climate)" },
  { id: "ev", label: "EV", accent: "var(--charging)" },
  { id: "vehicle", label: "Vehicle", accent: "var(--primary)" },
  { id: "comms", label: "Comms", accent: "var(--primary)" },
  { id: "voice", label: "Voice", accent: "var(--privacy)" },
  { id: "nav", label: "Maps", accent: "var(--primary)" },
  { id: "launcher", label: "Launcher", accent: "var(--secondary)" },
  { id: "settings", label: "Settings", accent: "var(--secondary)" },
  { id: "user", label: "User", accent: "var(--privacy)" },
  { id: "rse", label: "Rear seat", accent: "var(--primary)" },
  { id: "systemui", label: "System UI", accent: "var(--secondary)" },
  { id: "adas", label: "ADAS", accent: "var(--adas-active)" },
  { id: "layout", label: "Layout", accent: "var(--secondary)" },
  { id: "gauges", label: "Gauges", accent: "var(--primary)" },
] as const;

/** Curated Theme preview — Material Theme Builder’s component board, Cabin kit. */
export const THEME_BOARD_IDS = [
  "button",
  "fab",
  "switch",
  "checkbox",
  "chip",
  "card",
  "tabs",
  "navigation-dock",
  "slider",
  "text-field",
  "climate-tile",
  "fan-speed",
  "media-now-playing",
  "mini-player",
  "charge-session-card",
  "lock-control",
  "linear-progress",
  "snackbar",
  "radial-gauge",
  "list-item",
  "app-grid",
  "preference",
  "map-controls",
  "dialer",
  "suggestion-chips",
] as const;

export const KIT_CATALOG_BY_ID: ReadonlyMap<string, KitCatalogEntry> = new Map(
  KIT_CATALOG.map((entry) => [entry.id, entry]),
);

export function familyMeta(family: string) {
  return (
    KIT_FAMILIES.find((item) => item.id === family) ?? {
      id: family,
      label: family,
      accent: "var(--secondary)",
    }
  );
}

export function familyAccent(family: string): string {
  return familyMeta(family).accent;
}

export function entriesForFamily(family: string): KitCatalogEntry[] {
  return KIT_CATALOG.filter((entry) => entry.family === family);
}

export function themeBoardEntries(): KitCatalogEntry[] {
  return THEME_BOARD_IDS.map((id) => KIT_CATALOG_BY_ID.get(id)).filter(
    (entry): entry is KitCatalogEntry => Boolean(entry),
  );
}

export function familyCounts(): Record<string, number> {
  const counts: Record<string, number> = {};
  for (const entry of KIT_CATALOG) {
    counts[entry.family] = (counts[entry.family] ?? 0) + 1;
  }
  return counts;
}
