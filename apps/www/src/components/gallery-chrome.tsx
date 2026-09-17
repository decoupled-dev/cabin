"use client";

import { useScheme } from "@/components/scheme-provider";

/**
 * Quiet gallery chrome — sticky section anchors + day/night.
 * No marketing hero; scheme drives generated token demos.
 */
export function GalleryNav({
  label,
  items,
}: {
  label: string;
  items: readonly { href: string; label: string }[];
}) {
  const { scheme, setScheme } = useScheme();

  return (
    <nav
      aria-label={label}
      className="sticky top-16 z-30 border-b border-[var(--outline-subtle)] bg-[var(--nav-bg)] backdrop-blur-md"
    >
      <div className="mx-auto flex max-w-6xl items-center gap-3 px-5 py-2.5 sm:px-8">
        <div className="flex min-w-0 flex-1 gap-1 overflow-x-auto">
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
        <div
          className="inline-flex shrink-0 rounded-md border border-[var(--outline-subtle)] p-0.5"
          role="group"
          aria-label="Color scheme"
        >
          {(["night", "day"] as const).map((s) => (
            <button
              key={s}
              type="button"
              onClick={() => setScheme(s)}
              aria-pressed={scheme === s}
              className={`rounded px-2.5 py-1.5 text-status capitalize transition-colors duration-200 ease-cabin ${
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
      <div className="mx-auto max-w-6xl px-5 py-8 sm:px-8 sm:py-10">
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
  children: React.ReactNode;
}) {
  return (
    <section id={id} className="scroll-mt-28">
      <h2 className="font-display text-title text-on-surface">{title}</h2>
      <p className="mt-2 max-w-2xl text-status text-on-surface-variant text-balance">
        {body}
      </p>
      <div className="mt-8">{children}</div>
    </section>
  );
}
