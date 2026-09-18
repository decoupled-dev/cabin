"use client";

import type { ReactNode } from "react";
import { PageFrame } from "@/components/site-chrome";

/**
 * Quiet gallery chrome — sticky section anchors.
 * Scheme toggle lives once in the site header — do not duplicate here.
 */

export function GalleryNav({
  label,
  items,
}: {
  label: string;
  items: readonly { href: string; label: string }[];
}) {
  return (
    <nav
      aria-label={label}
      className="sticky top-16 z-30 border-b border-[var(--outline-subtle)] bg-[var(--nav-bg)]"
    >
      <PageFrame className="flex items-center gap-0 overflow-x-auto py-0">
        {items.map((item) => (
          <a
            key={item.href}
            href={item.href}
            className="relative shrink-0 px-4 py-3 text-status text-on-surface-variant transition-colors duration-200 ease-cabin hover:text-on-surface"
          >
            {item.label}
          </a>
        ))}
      </PageFrame>
    </nav>
  );
}

export function GalleryHeader({
  title,
  summary,
}: {
  title: string;
  summary: string;
}) {
  return (
    <header className="border-b border-[var(--outline-subtle)]">
      <PageFrame className="flex gap-5 py-7 sm:py-8">
        <span
          className="mt-1.5 h-8 w-0.5 shrink-0 bg-primary sm:h-9"
          aria-hidden
        />
        <div>
          <h1 className="font-display text-headline text-on-surface">{title}</h1>
          <p className="mt-2 max-w-2xl text-status text-on-surface-variant text-balance">
            {summary}
          </p>
        </div>
      </PageFrame>
    </header>
  );
}

export function GallerySection({
  id,
  title,
  body,
  children,
}: {
  id: string;
  title: string;
  body: string;
  children: ReactNode;
}) {
  return (
    <section id={id} className="scroll-mt-28">
      <div className="flex gap-4">
        <span className="mt-2 h-4 w-0.5 shrink-0 bg-primary" aria-hidden />
        <div>
          <h2 className="font-display text-title text-on-surface">{title}</h2>
          <p className="mt-2 max-w-3xl text-status text-on-surface-variant text-balance">
            {body}
          </p>
        </div>
      </div>
      <div className="mt-6">{children}</div>
    </section>
  );
}
