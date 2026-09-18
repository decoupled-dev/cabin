import type { ReactNode } from "react";

export function PageFrame({
  children,
  className = "",
}: {
  children: ReactNode;
  className?: string;
}) {
  return <div className={`page-frame ${className}`}>{children}</div>;
}

export function Kicker({
  children,
  className = "",
}: {
  children: ReactNode;
  className?: string;
}) {
  return (
    <p className={`kicker text-on-surface-variant ${className}`}>{children}</p>
  );
}

export function BrandMark({
  className = "",
}: {
  className?: string;
}) {
  return (
    <span className={`inline-flex items-center gap-2.5 ${className}`}>
      <span
        className="h-1.5 w-1.5 shrink-0 rounded-full bg-primary"
        aria-hidden
      />
      <span className="font-display font-semibold tracking-tight">Cabin</span>
    </span>
  );
}

export function SectionHeading({
  kicker,
  title,
  as: Tag = "h2",
  size = "headline",
  children,
}: {
  kicker: string;
  title: string;
  as?: "h1" | "h2";
  size?: "headline" | "display";
  children?: ReactNode;
}) {
  return (
    <div className="max-w-3xl">
      <Kicker>{kicker}</Kicker>
      <Tag
        className={`mt-3 font-display text-on-surface text-balance ${
          size === "display" ? "text-display" : "text-headline"
        }`}
      >
        {title}
      </Tag>
      {children}
    </div>
  );
}
