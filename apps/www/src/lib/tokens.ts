/**
 * Token catalog for the Foundations explorer.
 * Names and CSS variable references only — values come from generated
 * `cabin.tokens.css` (no hand hex).
 */

export type TokenGroup =
  | "scheme"
  | "semantic"
  | "feedback"
  | "domain"
  | "type"
  | "space"
  | "elevation"
  | "motion";

export type TokenEntry = {
  /** Human label */
  name: string;
  /** CSS custom property (without var()) */
  cssVar: string;
  group: TokenGroup;
  /** Kind of preview in the explorer */
  kind: "color" | "measure" | "type";
  /** Safety-adjacent lock from token extensions */
  locked?: boolean;
  note?: string;
};

/** Live scheme roles — flip with data-scheme day/night */
export const SCHEME_COLOR_TOKENS: TokenEntry[] = [
  {
    name: "Surface",
    cssVar: "--cabin-color-scheme-surface",
    group: "scheme",
    kind: "color",
    note: "Base chrome / app background",
  },
  {
    name: "On surface",
    cssVar: "--cabin-color-scheme-on-surface",
    group: "scheme",
    kind: "color",
    note: "Body text — neutral only",
  },
  {
    name: "Surface variant",
    cssVar: "--cabin-color-scheme-surface-variant",
    group: "scheme",
    kind: "color",
    note: "Recessed panels / wells",
  },
  {
    name: "Outline",
    cssVar: "--cabin-color-scheme-outline",
    group: "scheme",
    kind: "color",
    note: "Separators, unselected chrome",
  },
  {
    name: "Container",
    cssVar: "--cabin-color-scheme-container",
    group: "scheme",
    kind: "color",
    note: "System / Status bar fill",
  },
  {
    name: "On container",
    cssVar: "--cabin-color-scheme-on-container",
    group: "scheme",
    kind: "color",
    note: "Content on bar container",
  },
  {
    name: "Warning",
    cssVar: "--cabin-color-scheme-warning",
    group: "scheme",
    kind: "color",
    locked: true,
    note: "Safety-locked — no soft-wash",
  },
  {
    name: "Error",
    cssVar: "--cabin-color-scheme-error",
    group: "scheme",
    kind: "color",
    locked: true,
    note: "Safety-locked — no soft-wash",
  },
  {
    name: "Charging",
    cssVar: "--cabin-color-scheme-charging",
    group: "scheme",
    kind: "color",
    locked: true,
    note: "Night contrast locked",
  },
];

export const SEMANTIC_COLOR_TOKENS: TokenEntry[] = [
  {
    name: "Primary",
    cssVar: "--cabin-color-semantic-primary",
    group: "semantic",
    kind: "color",
    note: "Brand action; OEM-overridable",
  },
  {
    name: "On primary",
    cssVar: "--cabin-color-semantic-on-primary",
    group: "semantic",
    kind: "color",
  },
  {
    name: "Secondary",
    cssVar: "--cabin-color-semantic-secondary",
    group: "semantic",
    kind: "color",
  },
  {
    name: "On secondary",
    cssVar: "--cabin-color-semantic-on-secondary",
    group: "semantic",
    kind: "color",
  },
  {
    name: "Success",
    cssVar: "--cabin-color-semantic-success",
    group: "feedback",
    kind: "color",
  },
  {
    name: "Warning",
    cssVar: "--cabin-color-semantic-warning",
    group: "feedback",
    kind: "color",
    locked: true,
  },
  {
    name: "Error",
    cssVar: "--cabin-color-semantic-error",
    group: "feedback",
    kind: "color",
    locked: true,
  },
  {
    name: "Scrim",
    cssVar: "--cabin-color-semantic-scrim",
    group: "semantic",
    kind: "color",
  },
];

export const DOMAIN_COLOR_TOKENS: TokenEntry[] = [
  {
    name: "Charging",
    cssVar: "--cabin-color-semantic-charging",
    group: "domain",
    kind: "color",
    note: "Accent only — never body copy",
  },
  {
    name: "Climate",
    cssVar: "--cabin-color-semantic-climate",
    group: "domain",
    kind: "color",
    note: "Accent only — never body copy",
  },
  {
    name: "Media accent",
    cssVar: "--cabin-color-semantic-media-accent",
    group: "domain",
    kind: "color",
    note: "Domain accent — not brand wash",
  },
];

export const TYPE_TOKENS: TokenEntry[] = [
  {
    name: "Display",
    cssVar: "--cabin-type-role-display-size",
    group: "type",
    kind: "type",
  },
  {
    name: "Headline",
    cssVar: "--cabin-type-role-headline-size",
    group: "type",
    kind: "type",
  },
  {
    name: "Title",
    cssVar: "--cabin-type-role-title-size",
    group: "type",
    kind: "type",
  },
  {
    name: "Body",
    cssVar: "--cabin-type-role-body-size",
    group: "type",
    kind: "type",
  },
  {
    name: "Label",
    cssVar: "--cabin-type-role-label-size",
    group: "type",
    kind: "type",
  },
  {
    name: "Status",
    cssVar: "--cabin-type-role-status-size",
    group: "type",
    kind: "type",
  },
];

export const SPACE_TOKENS: TokenEntry[] = [
  { name: "XS", cssVar: "--cabin-space-xs", group: "space", kind: "measure" },
  { name: "SM", cssVar: "--cabin-space-sm", group: "space", kind: "measure" },
  { name: "MD", cssVar: "--cabin-space-md", group: "space", kind: "measure" },
  { name: "LG", cssVar: "--cabin-space-lg", group: "space", kind: "measure" },
  { name: "XL", cssVar: "--cabin-space-xl", group: "space", kind: "measure" },
  {
    name: "Touch minimum",
    cssVar: "--cabin-size-touch-minimum",
    group: "space",
    kind: "measure",
    note: "Automotive touch floor",
  },
];

export const ELEVATION_TOKENS: TokenEntry[] = [
  {
    name: "Level 0",
    cssVar: "--cabin-elevation-level0",
    group: "elevation",
    kind: "measure",
  },
  {
    name: "Level 1",
    cssVar: "--cabin-elevation-level1",
    group: "elevation",
    kind: "measure",
  },
  {
    name: "Level 2",
    cssVar: "--cabin-elevation-level2",
    group: "elevation",
    kind: "measure",
  },
];

export const MOTION_TOKENS: TokenEntry[] = [
  {
    name: "Fast",
    cssVar: "--cabin-motion-fast-duration",
    group: "motion",
    kind: "measure",
    note: "Press feedback",
  },
  {
    name: "Medium",
    cssVar: "--cabin-motion-medium-duration",
    group: "motion",
    kind: "measure",
    note: "Panel transitions (Park)",
  },
  {
    name: "Slow",
    cssVar: "--cabin-motion-slow-duration",
    group: "motion",
    kind: "measure",
    note: "Rare; avoid while driving",
  },
];

export const FOUNDATIONS_NAV = [
  { href: "#explorer", label: "Token explorer" },
  { href: "#color", label: "Color" },
  { href: "#type", label: "Type" },
  { href: "#space", label: "Space" },
  { href: "#elevation", label: "Elevation" },
  { href: "#motion", label: "Motion" },
] as const;

export const DOCS_FOUNDATIONS = "design-language/foundations";
export const DOCS_TOKENS = "design-language/tokens";
export const DOCS_TOKEN_CODEGEN = "design-language/token-codegen";
