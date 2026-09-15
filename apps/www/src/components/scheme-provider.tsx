"use client";

import {
  createContext,
  useCallback,
  useContext,
  useEffect,
  useMemo,
  useSyncExternalStore,
} from "react";

export type Scheme = "night" | "day";

type SchemeContextValue = {
  scheme: Scheme;
  setScheme: (scheme: Scheme) => void;
  toggle: () => void;
};

const SchemeContext = createContext<SchemeContextValue | null>(null);

const STORAGE_KEY = "cabin-www-scheme";
const listeners = new Set<() => void>();

function emit() {
  listeners.forEach((listener) => listener());
}

function subscribe(onStoreChange: () => void) {
  listeners.add(onStoreChange);
  const onStorage = (event: StorageEvent) => {
    if (event.key === STORAGE_KEY || event.key === null) onStoreChange();
  };
  window.addEventListener("storage", onStorage);
  return () => {
    listeners.delete(onStoreChange);
    window.removeEventListener("storage", onStorage);
  };
}

function readStoredScheme(): Scheme {
  const stored = window.localStorage.getItem(STORAGE_KEY);
  return stored === "day" || stored === "night" ? stored : "night";
}

function writeScheme(scheme: Scheme) {
  window.localStorage.setItem(STORAGE_KEY, scheme);
  document.documentElement.setAttribute("data-scheme", scheme);
  emit();
}

export function SchemeProvider({ children }: { children: React.ReactNode }) {
  const scheme = useSyncExternalStore(
    subscribe,
    readStoredScheme,
    () => "night" as Scheme,
  );

  useEffect(() => {
    document.documentElement.setAttribute("data-scheme", scheme);
  }, [scheme]);

  const setScheme = useCallback((next: Scheme) => {
    writeScheme(next);
  }, []);

  const toggle = useCallback(() => {
    writeScheme(readStoredScheme() === "night" ? "day" : "night");
  }, []);

  const value = useMemo(
    () => ({ scheme, setScheme, toggle }),
    [scheme, setScheme, toggle],
  );

  return (
    <SchemeContext.Provider value={value}>{children}</SchemeContext.Provider>
  );
}

export function useScheme() {
  const ctx = useContext(SchemeContext);
  if (!ctx) {
    throw new Error("useScheme must be used within SchemeProvider");
  }
  return ctx;
}
