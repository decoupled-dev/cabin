"use client";

import { useState } from "react";
import { useScheme } from "@/components/scheme-provider";
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
        className="relative h-20 w-full overflow-hidden rounded-md border border-[var(--outline-subtle)] transition-[border-color] duration-200 ease-cabin group-hover:border-outline"
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
      <div className="mt-3 flex flex-col gap-1">
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
 * No hand hex; day/night toggle uses the site SchemeProvider.
 */
export function TokenExplorer() {
  const { scheme, setScheme } = useScheme();
  const [tab, setTab] = useState<ExplorerTab>("scheme");
  const tokens = tokensFor(tab);
  const activeHint = TABS.find((t) => t.id === tab)?.hint ?? "";

  return (
    <div className="rounded-xl border border-[var(--outline-subtle)] bg-surface">
      <div className="flex flex-col gap-4 border-b border-[var(--outline-subtle)] px-5 py-5 sm:flex-row sm:items-end sm:justify-between sm:px-6">
        <div>
          <p className="text-status font-semibold uppercase tracking-[0.14em] text-on-surface-variant">
            Live · from codegen
          </p>
          <h3 className="mt-2 font-display text-title text-on-surface">
            Token explorer
          </h3>
          <p className="mt-1 max-w-md text-status text-on-surface-variant">
            {activeHint}
          </p>
        </div>

        <div
          className="inline-flex rounded-lg border border-[var(--outline-subtle)] p-1"
          role="group"
          aria-label="Color scheme"
        >
          {(["night", "day"] as const).map((s) => (
            <button
              key={s}
              type="button"
              onClick={() => setScheme(s)}
              aria-pressed={scheme === s}
              className={`rounded-md px-3.5 py-2 text-status capitalize transition-colors duration-200 ease-cabin ${
                scheme === s
                  ? "bg-[var(--surface-high)] text-on-surface"
                  : "text-on-surface-variant hover:text-on-surface"
              }`}
            >
              {s}
            </button>
          ))}
        </div>
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
              className={`shrink-0 rounded-md px-3.5 py-2 text-status transition-colors duration-200 ease-cabin ${
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
        className="grid gap-6 p-5 sm:grid-cols-2 sm:p-6 lg:grid-cols-3"
        role="tabpanel"
      >
        {tokens.map((token) => (
          <ColorSwatch key={token.cssVar} token={token} />
        ))}
      </ul>

      <p className="border-t border-[var(--outline-subtle)] px-5 py-4 text-status text-on-surface-variant sm:px-6">
        Values resolve from{" "}
        <code className="text-on-surface">cabin.tokens.css</code> generated from{" "}
        <code className="text-on-surface">tokens/cabin.tokens.json</code>. Night
        warning, error, and charging stay safety-locked.
      </p>
    </div>
  );
}
