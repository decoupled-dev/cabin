"use client";

import Link from "next/link";
import { usePathname } from "next/navigation";
import { useState, useSyncExternalStore } from "react";
import { DOCS_URL, NAV } from "@/lib/site";
import { useScheme } from "@/components/scheme-provider";
import { SegmentedControl } from "@/components/segmented-control";
import { BrandMark } from "@/components/site-chrome";

export function SiteHeader() {
  const pathname = usePathname();
  const { scheme, setScheme } = useScheme();
  const [open, setOpen] = useState(false);
  const [openForPath, setOpenForPath] = useState(pathname);
  if (openForPath !== pathname) {
    setOpenForPath(pathname);
    setOpen(false);
  }

  return (
    <header className="fixed inset-x-0 top-0 z-50 bg-[var(--nav-bg)]">
      <span
        className="pointer-events-none absolute inset-x-0 top-0 h-0.5 origin-left bg-primary animate-rail-in"
        aria-hidden
      />
      <div className="page-frame flex h-16 items-center justify-between gap-4 border-b border-[var(--outline-subtle)]">
        <Link
          href="/"
          className="text-on-surface transition-opacity hover:opacity-80"
          aria-label="Cabin home"
        >
          <BrandMark className="text-[1.0625rem]" />
        </Link>

        <nav
          className="hidden flex-1 items-center justify-center gap-0 lg:flex"
          aria-label="Primary"
        >
          {NAV.map((item) => {
            const active =
              item.href === "/"
                ? pathname === "/"
                : pathname.startsWith(item.href);
            return (
              <Link
                key={item.href}
                href={item.href}
                className={`relative px-3 py-2 text-status transition-colors ${
                  active
                    ? "text-on-surface"
                    : "text-on-surface-variant hover:text-on-surface"
                }`}
              >
                {item.label}
                {active ? (
                  <span
                    className="absolute inset-x-3 -bottom-[0.7rem] h-0.5 bg-primary"
                    aria-hidden
                  />
                ) : null}
              </Link>
            );
          })}
        </nav>

        <div className="flex items-center gap-2 sm:gap-3">
          <SiteClock />
          <SegmentedControl
            ariaLabel="Color scheme"
            value={scheme}
            onChange={setScheme}
            size="sm"
            fill="primary"
            options={[
              { id: "night", label: "Night" },
              { id: "day", label: "Day" },
            ]}
          />
          <a
            href={DOCS_URL}
            className="hidden min-h-10 items-center rounded-md bg-primary px-3.5 py-2 text-status font-semibold text-on-primary transition-colors duration-200 ease-cabin hover:bg-[color-mix(in_srgb,var(--primary)_88%,white)] sm:inline-flex"
            rel="noreferrer"
          >
            Docs
          </a>
          <button
            type="button"
            className="inline-flex h-10 w-10 items-center justify-center rounded-md border border-[var(--outline-subtle)] text-on-surface lg:hidden"
            aria-expanded={open}
            aria-label={open ? "Close menu" : "Open menu"}
            onClick={() => setOpen((v) => !v)}
          >
            <span aria-hidden className="flex flex-col gap-1.5">
              <span
                className={`block h-0.5 w-4 bg-current transition-transform duration-200 ease-cabin ${
                  open ? "translate-y-1 rotate-45" : ""
                }`}
              />
              <span
                className={`block h-0.5 w-4 bg-current transition-transform duration-200 ease-cabin ${
                  open ? "-translate-y-1 -rotate-45" : ""
                }`}
              />
            </span>
          </button>
        </div>
      </div>

      {open ? (
        <nav
          className="border-b border-[var(--outline-subtle)] bg-surface px-5 py-3 lg:hidden"
          aria-label="Mobile"
        >
          <ul className="flex flex-col">
            {NAV.map((item) => {
              const active =
                item.href === "/"
                  ? pathname === "/"
                  : pathname.startsWith(item.href);
              return (
                <li key={item.href}>
                  <Link
                    href={item.href}
                    className={`flex items-center justify-between border-b border-[var(--outline-subtle)] px-1 py-3.5 text-label ${
                      active ? "text-on-surface" : "text-on-surface-variant"
                    }`}
                    onClick={() => setOpen(false)}
                  >
                    {item.label}
                    {active ? (
                      <span className="h-1.5 w-1.5 rounded-full bg-primary" />
                    ) : null}
                  </Link>
                </li>
              );
            })}
            <li>
              <a
                href={DOCS_URL}
                className="block px-1 py-3.5 text-label text-primary"
                rel="noreferrer"
              >
                Developer docs
              </a>
            </li>
          </ul>
        </nav>
      ) : null}
    </header>
  );
}

function formatClock(date = new Date()) {
  return new Intl.DateTimeFormat("en-GB", {
    hour: "2-digit",
    minute: "2-digit",
    hour12: false,
  }).format(date);
}

function subscribeClock(onStoreChange: () => void) {
  const id = window.setInterval(onStoreChange, 15_000);
  return () => window.clearInterval(id);
}

function SiteClock() {
  const now = useSyncExternalStore(subscribeClock, formatClock, () => "");

  return (
    <span
      className="hidden min-w-[3.25rem] text-right text-status tabular-nums text-on-surface-variant md:inline"
      aria-hidden={now ? undefined : true}
    >
      {now || "··:··"}
    </span>
  );
}
