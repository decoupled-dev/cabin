"use client";

import Link from "next/link";
import { usePathname } from "next/navigation";
import { DOCS_URL, GITHUB_URL, NAV } from "@/lib/site";

const GALLERY_PATHS = ["/foundations", "/components"];

function isGalleryRoute(pathname: string): boolean {
  return GALLERY_PATHS.some(
    (p) => pathname === p || pathname.startsWith(`${p}/`),
  );
}

/**
 * Site footer. Gallery routes get a quiet strip — no marketing copy.
 */
export function SiteFooter() {
  const pathname = usePathname();
  if (isGalleryRoute(pathname)) {
    return <GalleryFooter />;
  }
  return <MarketingFooter />;
}

function GalleryFooter() {
  return (
    <footer className="border-t border-[var(--outline-subtle)] bg-background">
      <div className="mx-auto flex max-w-6xl flex-wrap items-center gap-x-4 gap-y-2 px-5 py-6 text-status text-on-surface-variant sm:px-8">
        <Link
          href="/"
          className="font-display text-on-surface underline-offset-4 hover:underline"
        >
          Cabin
        </Link>
        <span aria-hidden>·</span>
        <a
          href={DOCS_URL}
          className="underline-offset-4 hover:underline"
          rel="noreferrer"
        >
          Docs
        </a>
        <a
          href={GITHUB_URL}
          className="underline-offset-4 hover:underline"
          rel="noreferrer"
        >
          GitHub
        </a>
      </div>
    </footer>
  );
}

function MarketingFooter() {
  return (
    <footer className="border-t border-[var(--outline-subtle)] bg-surface">
      <div className="mx-auto grid max-w-6xl gap-10 px-5 py-14 sm:px-8 md:grid-cols-[1.4fr_1fr_1fr]">
        <div>
          <p className="font-display text-title text-on-surface">Cabin</p>
          <p className="mt-3 max-w-sm text-status text-on-surface-variant">
            Open-source AAOS design language and dual UI kit. Quiet, glanceable,
            safe by default — Compose and Views as peers.
          </p>
        </div>
        <div>
          <p className="text-status font-semibold uppercase tracking-[0.12em] text-on-surface-variant">
            Site
          </p>
          <ul className="mt-4 space-y-2">
            {NAV.map((item) => (
              <li key={item.href}>
                <Link
                  href={item.href}
                  className="text-status text-on-surface-variant transition-colors hover:text-on-surface"
                >
                  {item.label}
                </Link>
              </li>
            ))}
          </ul>
        </div>
        <div>
          <p className="text-status font-semibold uppercase tracking-[0.12em] text-on-surface-variant">
            Elsewhere
          </p>
          <ul className="mt-4 space-y-2">
            <li>
              <a
                href={DOCS_URL}
                className="text-status text-on-surface-variant transition-colors hover:text-on-surface"
                rel="noreferrer"
              >
                Developer docs
              </a>
            </li>
            <li>
              <a
                href={GITHUB_URL}
                className="text-status text-on-surface-variant transition-colors hover:text-on-surface"
                rel="noreferrer"
              >
                GitHub
              </a>
            </li>
          </ul>
          <p className="mt-8 text-status text-on-surface-variant">
            Marketing site · separate from{" "}
            <code className="text-[0.75rem]">website/</code> docs shell
          </p>
        </div>
      </div>
    </footer>
  );
}
