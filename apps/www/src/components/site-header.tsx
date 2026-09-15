"use client";

import Link from "next/link";
import { usePathname } from "next/navigation";
import { useState } from "react";
import { DOCS_URL, NAV } from "@/lib/site";
import { useScheme } from "@/components/scheme-provider";

export function SiteHeader() {
  const pathname = usePathname();
  const { scheme, toggle } = useScheme();
  const [open, setOpen] = useState(false);

  return (
    <header className="fixed inset-x-0 top-0 z-50 border-b border-[var(--outline-subtle)] bg-[var(--nav-bg)] backdrop-blur-xl">
      <div className="mx-auto flex h-16 max-w-6xl items-center justify-between gap-4 px-5 sm:px-8">
        <Link
          href="/"
          className="font-display text-lg font-semibold tracking-tight text-on-surface transition-opacity hover:opacity-80"
        >
          Cabin
        </Link>

        <nav className="hidden items-center gap-1 md:flex" aria-label="Primary">
          {NAV.map((item) => {
            const active =
              item.href === "/"
                ? pathname === "/"
                : pathname.startsWith(item.href);
            return (
              <Link
                key={item.href}
                href={item.href}
                className={`rounded-md px-3 py-2 text-status transition-colors ${
                  active
                    ? "bg-[var(--surface-high)] text-on-surface"
                    : "text-on-surface-variant hover:text-on-surface"
                }`}
              >
                {item.label}
              </Link>
            );
          })}
        </nav>

        <div className="flex items-center gap-2">
          <button
            type="button"
            onClick={toggle}
            className="rounded-md border border-[var(--outline-subtle)] px-3 py-2 text-status text-on-surface-variant transition-colors hover:border-outline hover:text-on-surface"
            aria-label={`Switch to ${scheme === "night" ? "day" : "night"} scheme`}
          >
            {scheme === "night" ? "Night" : "Day"}
          </button>
          <a
            href={DOCS_URL}
            className="hidden rounded-md bg-primary px-3.5 py-2 text-status text-on-primary transition-opacity hover:opacity-90 sm:inline-flex"
            rel="noreferrer"
          >
            Docs
          </a>
          <button
            type="button"
            className="inline-flex h-10 w-10 items-center justify-center rounded-md border border-[var(--outline-subtle)] text-on-surface md:hidden"
            aria-expanded={open}
            aria-label="Open menu"
            onClick={() => setOpen((v) => !v)}
          >
            <span className="sr-only">Menu</span>
            <span aria-hidden className="flex flex-col gap-1.5">
              <span className="block h-0.5 w-4 bg-current" />
              <span className="block h-0.5 w-4 bg-current" />
            </span>
          </button>
        </div>
      </div>

      {open ? (
        <nav
          className="border-t border-[var(--outline-subtle)] bg-surface px-5 py-4 md:hidden"
          aria-label="Mobile"
        >
          <ul className="flex flex-col gap-1">
            {NAV.map((item) => (
              <li key={item.href}>
                <Link
                  href={item.href}
                  className="block rounded-md px-3 py-3 text-label text-on-surface"
                  onClick={() => setOpen(false)}
                >
                  {item.label}
                </Link>
              </li>
            ))}
            <li>
              <a
                href={DOCS_URL}
                className="block rounded-md px-3 py-3 text-label text-primary"
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
