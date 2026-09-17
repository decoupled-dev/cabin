/**
 * Cabin HMI glyphs for gallery demos — SVG only, currentColor.
 * No emoji transport, no dash placeholders as iconography.
 */

import type { ReactNode } from "react";

type GlyphProps = {
  className?: string;
  title?: string;
};

function Svg({
  children,
  className,
  title,
}: GlyphProps & { children: ReactNode }) {
  return (
    <svg
      viewBox="0 0 24 24"
      fill="none"
      xmlns="http://www.w3.org/2000/svg"
      className={className}
      aria-hidden={title ? undefined : true}
      role={title ? "img" : undefined}
    >
      {title ? <title>{title}</title> : null}
      {children}
    </svg>
  );
}

export function GlyphHome({ className }: GlyphProps) {
  return (
    <Svg className={className}>
      <path
        d="M4 11.5 12 4l8 7.5V20a1 1 0 0 1-1 1h-5.2v-5.5h-3.6V21H5a1 1 0 0 1-1-1v-8.5Z"
        stroke="currentColor"
        strokeWidth="1.75"
        strokeLinejoin="round"
      />
    </Svg>
  );
}

export function GlyphMaps({ className }: GlyphProps) {
  return (
    <Svg className={className}>
      <path
        d="M12 21s6-5.2 6-10.2A6 6 0 0 0 6 10.8C6 15.8 12 21 12 21Z"
        stroke="currentColor"
        strokeWidth="1.75"
        strokeLinejoin="round"
      />
      <circle cx="12" cy="10.5" r="2.1" fill="currentColor" />
    </Svg>
  );
}

export function GlyphMedia({ className }: GlyphProps) {
  return (
    <Svg className={className}>
      <circle cx="12" cy="12" r="8.25" stroke="currentColor" strokeWidth="1.75" />
      <circle cx="12" cy="12" r="2.25" fill="currentColor" />
      <path
        d="M14.2 12V7.6l4 1.1"
        stroke="currentColor"
        strokeWidth="1.75"
        strokeLinecap="round"
        strokeLinejoin="round"
      />
    </Svg>
  );
}

export function GlyphHvac({ className }: GlyphProps) {
  return (
    <Svg className={className}>
      <path
        d="M12 3.5v17M8.2 6.2l7.6 11.6M15.8 6.2 8.2 17.8"
        stroke="currentColor"
        strokeWidth="1.75"
        strokeLinecap="round"
      />
      <circle cx="12" cy="12" r="2" fill="currentColor" />
    </Svg>
  );
}

export function GlyphApps({ className }: GlyphProps) {
  return (
    <Svg className={className}>
      {[5, 11, 17].flatMap((x) =>
        [5, 11, 17].map((y) => (
          <rect
            key={`${x}-${y}`}
            x={x - 1.6}
            y={y - 1.6}
            width="3.2"
            height="3.2"
            rx="0.7"
            fill="currentColor"
          />
        )),
      )}
    </Svg>
  );
}

export function GlyphPrev({ className }: GlyphProps) {
  return (
    <Svg className={className}>
      <path d="M18 6.5v11L9.5 12 18 6.5Z" fill="currentColor" />
      <path d="M6.5 6.5v11" stroke="currentColor" strokeWidth="2" strokeLinecap="round" />
    </Svg>
  );
}

export function GlyphNext({ className }: GlyphProps) {
  return (
    <Svg className={className}>
      <path d="M6 6.5v11L14.5 12 6 6.5Z" fill="currentColor" />
      <path d="M17.5 6.5v11" stroke="currentColor" strokeWidth="2" strokeLinecap="round" />
    </Svg>
  );
}

export function GlyphPlay({ className }: GlyphProps) {
  return (
    <Svg className={className}>
      <path d="M8.5 5.8v12.4L18.2 12 8.5 5.8Z" fill="currentColor" />
    </Svg>
  );
}

export function GlyphPause({ className }: GlyphProps) {
  return (
    <Svg className={className}>
      <rect x="7" y="6" width="3.5" height="12" rx="1" fill="currentColor" />
      <rect x="13.5" y="6" width="3.5" height="12" rx="1" fill="currentColor" />
    </Svg>
  );
}

export function GlyphMinus({ className }: GlyphProps) {
  return (
    <Svg className={className}>
      <path d="M6 12h12" stroke="currentColor" strokeWidth="2.25" strokeLinecap="round" />
    </Svg>
  );
}

export function GlyphPlus({ className }: GlyphProps) {
  return (
    <Svg className={className}>
      <path
        d="M12 6v12M6 12h12"
        stroke="currentColor"
        strokeWidth="2.25"
        strokeLinecap="round"
      />
    </Svg>
  );
}

export function GlyphSignal({ className }: GlyphProps) {
  return (
    <Svg className={className}>
      {[4, 8, 12, 16].map((x, i) => (
        <rect
          key={x}
          x={x}
          y={16 - i * 3.2}
          width="2.4"
          height={4 + i * 3.2}
          rx="0.6"
          fill="currentColor"
        />
      ))}
    </Svg>
  );
}

/** Artwork well placeholder — solid cabin glyph, never a hyphen. */
export function ArtworkWellMark({ className }: GlyphProps) {
  return (
    <Svg className={className} title="Artwork unavailable">
      <rect
        x="3.5"
        y="5"
        width="17"
        height="14"
        rx="2"
        stroke="currentColor"
        strokeWidth="1.5"
        opacity="0.55"
      />
      <circle cx="9" cy="10.5" r="1.6" fill="currentColor" opacity="0.7" />
      <path
        d="M12.5 15.5 9.8 12.2 7 15.5h10.5L14.2 11.8l-1.7 3.7Z"
        fill="currentColor"
        opacity="0.55"
      />
    </Svg>
  );
}

const SYSTEM_GLYPHS = {
  home: GlyphHome,
  maps: GlyphMaps,
  media: GlyphMedia,
  hvac: GlyphHvac,
  apps: GlyphApps,
} as const;

export type SystemGlyphId = keyof typeof SYSTEM_GLYPHS;

export function SystemGlyph({
  id,
  className,
}: {
  id: SystemGlyphId;
  className?: string;
}) {
  const G = SYSTEM_GLYPHS[id];
  return <G className={className} />;
}
