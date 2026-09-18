"use client";

import {
  MOTION_TOKENS,
  SPACE_TOKENS,
  TYPE_TOKENS,
  type TokenEntry,
} from "@/lib/tokens";
import { useCssVar } from "@/lib/use-css-var";

function MeasureRow({ token }: { token: TokenEntry }) {
  const value = useCssVar(token.cssVar);
  const isSpace = token.group === "space";
  const isMotion = token.group === "motion";
  const px =
    isSpace && value.endsWith("px") ? Number.parseFloat(value) : undefined;

  return (
    <li className="flex flex-col gap-2 border-t border-[var(--outline-subtle)] py-3.5 first:border-t-0 first:pt-0 sm:flex-row sm:items-center sm:justify-between sm:gap-6">
      <div className="min-w-0 flex-1">
        <div className="flex flex-wrap items-baseline gap-x-3 gap-y-1">
          <p className="text-label text-on-surface">{token.name}</p>
          <code className="break-all text-status text-on-surface-variant">
            {token.cssVar}
          </code>
        </div>
        {token.note ? (
          <p className="mt-1 text-status text-on-surface-variant">
            {token.note}
          </p>
        ) : null}
      </div>

      <div className="flex items-center gap-4">
        {isSpace && px !== undefined && !Number.isNaN(px) ? (
          <div
            className="h-2 rounded-sm bg-primary/80"
            style={{ width: Math.min(px * 1.5, 160) }}
            aria-hidden
          />
        ) : null}
        {isMotion ? (
          <span
            className="inline-block h-2 w-2 rounded-full bg-primary"
            style={{
              animation: `cabin-pulse ${value !== "—" ? value : "200ms"} cubic-bezier(0.2, 0, 0, 1) infinite alternate`,
            }}
            aria-hidden
          />
        ) : null}
        <span className="min-w-[4.5rem] text-right font-mono text-status tabular-nums text-on-surface">
          {value}
        </span>
      </div>
    </li>
  );
}

export function TypeScaleDemo() {
  return (
    <ul className="flex flex-col">
      {TYPE_TOKENS.map((role) => (
        <TypeRoleRow key={role.name} role={role} />
      ))}
    </ul>
  );
}

function TypeRoleRow({ role }: { role: TokenEntry }) {
  const sizeVar = role.cssVar;
  const weightVar = sizeVar.replace("-size", "-weight");
  const lhVar = sizeVar.replace("-size", "-line-height");
  const size = useCssVar(sizeVar);
  const weight = useCssVar(weightVar);

  return (
    <li className="border-t border-[var(--outline-subtle)] py-4 first:border-t-0 first:pt-0">
      <div className="flex flex-wrap items-baseline justify-between gap-2">
        <p className="kicker text-on-surface-variant">{role.name}</p>
        <code className="text-status text-on-surface-variant">
          {size} / {weight}
        </code>
      </div>
      <p
        className="mt-2 max-w-2xl text-on-surface text-balance"
        style={{
          fontSize: `var(${sizeVar})`,
          fontWeight: `var(${weightVar})`,
          lineHeight: `var(${lhVar})`,
          fontFamily: "var(--font-display), system-ui, sans-serif",
        }}
      >
        Glanceable at cabin distance
      </p>
    </li>
  );
}

export function SpaceScaleDemo() {
  return (
    <ul>
      {SPACE_TOKENS.map((token) => (
        <MeasureRow key={token.cssVar} token={token} />
      ))}
    </ul>
  );
}

export function MotionDemo() {
  return (
    <ul>
      {MOTION_TOKENS.map((token) => (
        <MeasureRow key={token.cssVar} token={token} />
      ))}
    </ul>
  );
}
