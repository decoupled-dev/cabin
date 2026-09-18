"use client";

import Link from "next/link";
import { usePathname } from "next/navigation";
import { DOCS_URL, GITHUB_URL, NAV } from "@/lib/site";
import { BrandMark, PageFrame } from "@/components/site-chrome";

const GALLERY_PATHS = ["/foundations", "/use-cases", "/components"];

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
      <PageFrame className="flex flex-wrap items-center gap-x-5 gap-y-2 py-5 text-status text-on-surface-variant">
        <Link href="/" className="text-on-surface">
          <BrandMark className="text-status" />
        </Link>
        <a href={DOCS_URL} className="hover:text-on-surface" rel="noreferrer">
          Docs
        </a>
        <a href={GITHUB_URL} className="hover:text-on-surface" rel="noreferrer">
          GitHub
        </a>
      </PageFrame>
    </footer>
  );
}

function MarketingFooter() {
  return (
    <footer className="border-t border-[var(--outline-subtle)] bg-[var(--hmi-bezel)]">
      <PageFrame className="grid gap-10 py-12 md:grid-cols-[1.5fr_1fr_1fr] md:py-14">
        <div>
          <BrandMark className="text-title text-on-surface" />
          <p className="mt-4 max-w-sm text-status text-on-surface-variant">
            Open-source AAOS design language and dual UI kit. Quiet, glanceable,
            safe by default — Compose and Views as peers.
          </p>
        </div>
        <div>
          <p className="kicker text-on-surface-variant">Site</p>
          <ul className="mt-4 space-y-2.5">
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
          <p className="kicker text-on-surface-variant">Elsewhere</p>
          <ul className="mt-4 space-y-2.5">
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
      </PageFrame>
    </footer>
  );
}
