/**
 * Shared Signal / gate helpers for web HMI demos.
 * Mirrors cabin-compliance Signal honesty + GateVisuals.quietAlpha —
 * presentation only; not a runtime Android dependency.
 */

export type SignalKind = "value" | "stale" | "unavailable" | "fault";

export type DemoSignal<T> =
  | { kind: "value"; value: T }
  | { kind: "stale"; last: T }
  | { kind: "unavailable" }
  | { kind: "fault" };

export type DriveState = "parked" | "moving";

/** Restriction Engine disposition for demo gates. */
export type GateDisposition = "allow" | "substitute" | "block";

export function dispositionFor(
  drive: DriveState,
  interaction: "hvacAdjust" | "mediaTransport" | "mediaComplex",
): GateDisposition {
  if (drive === "parked") return "allow";
  switch (interaction) {
    case "mediaTransport":
      return "allow";
    case "hvacAdjust":
    case "mediaComplex":
      return "block";
  }
}

/**
 * Restriction Engine allow matrix for inventory interaction names.
 * Parked / Moving only on this surface (Idling / Restricted live on Android).
 */
export function dispositionForInteraction(
  drive: DriveState,
  interaction: string,
): GateDisposition {
  if (drive === "parked") return "allow";
  switch (interaction) {
    case "Glance":
    case "NavigateSimple":
    case "MediaTransport":
    case "HvacPeek":
      return "allow";
    case "StatusDeepLinkInformational":
      return "substitute";
    case "HvacAdjust":
    case "MediaComplex":
    case "OpenComplexApp":
    case "OpenKeyboard":
    case "FilterOrSort":
    case "StatusDeepLinkSettings":
    case "ParkedOnly":
    case "VehicleAdjust":
      return "block";
    default:
      return "allow";
  }
}

/** RE-quiet soft-disable — values stay glanceable (Compose GateVisuals.quietAlpha). */
export function quietOpacity(disposition: GateDisposition, enabled = true): number {
  if (!enabled) return 0.7;
  switch (disposition) {
    case "allow":
      return 1;
    case "substitute":
      return 0.75;
    case "block":
      return 0.7;
  }
}

export function isActivatable(
  enabled: boolean,
  disposition: GateDisposition,
): boolean {
  return enabled && disposition === "allow";
}

export function formatTemp(signal: DemoSignal<number>): string {
  switch (signal.kind) {
    case "value":
      return `${signal.value}°`;
    case "stale":
      return `${signal.last}° · stale`;
    case "unavailable":
      return "—";
    case "fault":
      return "Fault";
  }
}

export function formatLevel(signal: DemoSignal<number>, max: number): string {
  switch (signal.kind) {
    case "value":
      return `${signal.value}/${max}`;
    case "stale":
      return `${signal.last}/${max} · stale`;
    case "unavailable":
      return "—";
    case "fault":
      return "Fault";
  }
}

export function formatMediaText(
  signal: DemoSignal<string>,
  fallback: string,
): string {
  switch (signal.kind) {
    case "value":
      return signal.value;
    case "stale":
      return `${signal.last} · stale`;
    case "unavailable":
      return fallback;
    case "fault":
      return "Fault";
  }
}

export function liveProgressText(
  positionMs: DemoSignal<number>,
  durationMs: DemoSignal<number>,
): string | null {
  if (positionMs.kind !== "value" || durationMs.kind !== "value") return null;
  if (durationMs.value <= 0) return null;
  return `${mmss(positionMs.value)} / ${mmss(durationMs.value)}`;
}

function mmss(ms: number): string {
  const totalSec = Math.max(0, Math.floor(ms / 1000));
  const m = Math.floor(totalSec / 60);
  const s = totalSec % 60;
  return `${m}:${String(s).padStart(2, "0")}`;
}

export const COMPONENTS_NAV = [
  { href: "#theme", label: "Theme" },
  { href: "#screens", label: "Screens" },
] as const;

export const DOCS_SYSTEM_BAR = "components/specs/system-bar";
export const DOCS_STATUS_BAR = "components/specs/status-bar";
export const DOCS_CLIMATE_TILE = "components/specs/climate-tile";
export const DOCS_MEDIA_NOW = "components/specs/media-now-playing";
