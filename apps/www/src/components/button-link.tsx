import Link from "next/link";

type Variant = "primary" | "secondary" | "ghost";

const styles: Record<Variant, string> = {
  primary:
    "min-h-12 bg-primary px-6 py-3 text-label text-on-primary hover:bg-[color-mix(in_srgb,var(--primary)_88%,white)]",
  secondary:
    "min-h-12 border border-outline bg-transparent px-6 py-3 text-label text-on-surface hover:bg-[var(--surface-high)]",
  ghost:
    "text-on-surface-variant underline-offset-4 hover:text-on-surface hover:underline",
};

export function ButtonLink({
  href,
  children,
  variant = "primary",
  external = false,
  className = "",
}: {
  href: string;
  children: React.ReactNode;
  variant?: Variant;
  external?: boolean;
  className?: string;
}) {
  const classes = `inline-flex items-center justify-center rounded-md font-semibold transition-colors duration-200 ease-cabin ${styles[variant]} ${className}`;

  if (external) {
    return (
      <a href={href} className={classes} rel="noreferrer">
        {children}
      </a>
    );
  }

  return (
    <Link href={href} className={classes}>
      {children}
    </Link>
  );
}
