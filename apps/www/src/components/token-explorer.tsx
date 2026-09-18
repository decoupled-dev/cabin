"use client";

import { useState } from "react";
import {
  DOMAIN_COLOR_TOKENS,
  SCHEME_COLOR_TOKENS,
  SEMANTIC_COLOR_TOKENS,
  type TokenEntry,
} from "@/lib/tokens";
import { useCssVar } from "@/lib/use-css-var";
import { SegmentedControl } from "@/components/segmented-control";

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
    <li className="flex flex-col border-b border-r border-[var(--outline-subtle)] p-4">
      <div
        className="relative h-20 w-full overflow-hidden border border-[var(--outline-subtle)]"
        style={{ background: `var(${token.cssVar})` }}
        role="img"
        aria-label={`${token.name} swatch`}
      >
        {token.locked ? (
          <span className="absolute bottom-2 left-2 bg-[var(--scrim)] px-1.5 py-0.5 text-[0.6875rem] font-semibold uppercase tracking-[0.08em] text-on-surface">
            Locked
          </span>
        ) : null}
      </div>
      <div className="mt-3 flex flex-col gap-0.5">
        <div className="flex items-baseline justify-between gap-2">
          <p className="text-label text-on-surface">{token.name}</p>
          {token.locked ? (
            <span className="text-[0.6875rem] font-medium uppercase tracking-[0.1em] text-warning">
              Safety
            </span>
          ) : null}
        </div>
        <code
          className="break-all text-status text-on-surface-variant"
          title={token.cssVar}
        >
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
    <div className="instrument-well">
      <div className="flex flex-col gap-4 border-b border-[var(--outline-subtle)] px-5 py-5 sm:flex-row sm:items-end sm:justify-between sm:px-6">
        <div>
          <h3 className="font-display text-title text-on-surface">
            Token explorer
          </h3>
          <p className="mt-1 max-w-xl text-status text-on-surface-variant">
            {activeHint}. Use Night / Day in the header to flip schemes.
          </p>
        </div>
        <SegmentedControl
          ariaLabel="Token groups"
          value={tab}
          onChange={setTab}
          options={TABS.map((t) => ({ id: t.id, label: t.label }))}
        />
      </div>

      <ul
        className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4"
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
