"use client";

import { useState } from "react";
import {
  DOMAIN_COLOR_TOKENS,
  SCHEME_COLOR_TOKENS,
  SEMANTIC_COLOR_TOKENS,
  type TokenEntry,
} from "@/lib/tokens";
import { useCssVar } from "@/lib/use-css-var";

type ExplorerTab = "scheme" | "semantic" | "domain";

const TABS: { id: ExplorerTab; label: string; hint: string }[] = [
  {
    id: "scheme",
    label: "Scheme",
    hint: "Day / night chrome — outline, container, locked signals",
  },
  {
    id: "semantic",
    label: "Semantic",
    hint: "Brand and feedback roles shared across Compose and Views",
  },
  {
    id: "domain",
    label: "Domain",
    hint: "Charging, climate, media — accents only, never body copy",
  },
];

function tokensFor(tab: ExplorerTab): TokenEntry[] {
  switch (tab) {
    case "scheme":
      return SCHEME_COLOR_TOKENS;
    case "semantic":
      return SEMANTIC_COLOR_TOKENS;
    case "domain":
      return DOMAIN_COLOR_TOKENS;
  }
}

function ColorSwatch({ token }: { token: TokenEntry }) {
  const resolved = useCssVar(token.cssVar);

  return (
    <li className="group flex flex-col">
      <div
        className="relative h-16 w-full overflow-hidden rounded-md border border-[var(--outline-subtle)] transition-[border-color] duration-200 ease-cabin group-hover:border-outline"
        style={{ background: `var(${token.cssVar})` }}
        role="img"
        aria-label={`${token.name} swatch`}
      >
        {token.locked ? (
          <span className="absolute bottom-2 left-2 rounded-sm bg-[var(--scrim)] px-1.5 py-0.5 text-[0.6875rem] font-semibold uppercase tracking-[0.08em] text-on-surface">
            Locked
          </span>
        ) : null}
      </div>
      <div className="mt-2.5 flex flex-col gap-0.5">
        <div className="flex items-baseline justify-between gap-2">
          <p className="text-label text-on-surface">{token.name}</p>
          {token.locked ? (
            <span className="text-[0.6875rem] font-medium uppercase tracking-[0.1em] text-warning">
              Safety
            </span>
          ) : null}
        </div>
        <code className="truncate text-status text-on-surface-variant">
          {token.cssVar}
        </code>
        <p className="font-mono text-status tabular-nums text-on-surface-variant">
          {resolved}
        </p>
        {token.note ? (
          <p className="text-status text-on-surface-variant/80">{token.note}</p>
        ) : null}
      </div>
    </li>
  );
}

/**
 * Live token explorer — values resolve from generated CSS vars under data-scheme.
 * Scheme toggle lives in the site header only.
 */
export function TokenExplorer() {
  const [tab, setTab] = useState<ExplorerTab>("scheme");
  const tokens = tokensFor(tab);
  const activeHint = TABS.find((t) => t.id === tab)?.hint ?? "";

  return (
    <div className="rounded-lg border border-[var(--outline-subtle)] bg-surface">
      <div className="border-b border-[var(--outline-subtle)] px-5 py-4 sm:px-6">
        <h3 className="font-display text-title text-on-surface">
          Token explorer
        </h3>
        <p className="mt-1 max-w-xl text-status text-on-surface-variant">
          {activeHint}. Use Night / Day in the header to flip schemes.
        </p>
      </div>

      <div className="border-b border-[var(--outline-subtle)] px-5 sm:px-6">
        <div
          className="flex gap-1 overflow-x-auto py-3"
          role="tablist"
          aria-label="Token groups"
        >
          {TABS.map((t) => (
            <button
              key={t.id}
              type="button"
              role="tab"
              aria-selected={tab === t.id}
              onClick={() => setTab(t.id)}
              className={`shrink-0 rounded-md px-3 py-2 text-status transition-colors duration-200 ease-cabin ${
                tab === t.id
                  ? "bg-primary text-on-primary"
                  : "text-on-surface-variant hover:bg-[var(--surface-high)] hover:text-on-surface"
              }`}
            >
              {t.label}
            </button>
          ))}
        </div>
      </div>

      <ul
        className="grid gap-5 p-5 sm:grid-cols-2 sm:p-6 lg:grid-cols-3 xl:grid-cols-4"
        role="tabpanel"
      >
        {tokens.map((token) => (
          <ColorSwatch key={token.cssVar} token={token} />
        ))}
      </ul>

      <p className="border-t border-[var(--outline-subtle)] px-5 py-3 text-status text-on-surface-variant sm:px-6">
        From <code className="text-on-surface">cabin.tokens.css</code>. Night
        warning, error, and charging stay safety-locked.
      </p>
    </div>
  );
}
