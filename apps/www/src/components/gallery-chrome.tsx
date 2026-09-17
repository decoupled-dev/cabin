"use client";

import type { ReactNode } from "react";

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
      className="sticky top-16 z-30 border-b border-[var(--outline-subtle)] bg-[var(--nav-bg)] backdrop-blur-md"
    >
      <div className="mx-auto flex max-w-6xl items-center gap-1 overflow-x-auto px-5 py-2.5 sm:px-8">
        {items.map((item) => (
          <a
            key={item.href}
            href={item.href}
            className="shrink-0 rounded-md px-3 py-2 text-status text-on-surface-variant transition-colors duration-200 ease-cabin hover:bg-[var(--surface-high)] hover:text-on-surface"
          >
            {item.label}
          </a>
        ))}
      </div>
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
      <div className="mx-auto max-w-6xl px-5 py-7 sm:px-8 sm:py-8">
        <h1 className="font-display text-headline text-on-surface">{title}</h1>
        <p className="mt-2 max-w-2xl text-status text-on-surface-variant text-balance">
          {summary}
        </p>
      </div>
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
      <h2 className="font-display text-title text-on-surface">{title}</h2>
      <p className="mt-2 max-w-3xl text-status text-on-surface-variant text-balance">
        {body}
      </p>
      <div className="mt-6">{children}</div>
    </section>
  );
}
