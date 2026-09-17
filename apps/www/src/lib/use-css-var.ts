"use client";

import { useSyncExternalStore } from "react";
import { useScheme } from "@/components/scheme-provider";

function readCssVar(cssVar: string): string {
  return getComputedStyle(document.documentElement)
    .getPropertyValue(cssVar)
    .trim();
}

function subscribeScheme(onStoreChange: () => void) {
  const root = document.documentElement;
  const observer = new MutationObserver(onStoreChange);
  observer.observe(root, { attributes: true, attributeFilter: ["data-scheme"] });
  return () => observer.disconnect();
}

/** Live CSS custom-property value from generated cabin.tokens.css. */
export function useCssVar(cssVar: string): string {
  useScheme();
  return useSyncExternalStore(
    subscribeScheme,
    () => readCssVar(cssVar) || "—",
    () => "—",
  );
}
