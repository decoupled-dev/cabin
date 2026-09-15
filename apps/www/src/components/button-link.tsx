import Link from "next/link";

type Variant = "primary" | "secondary" | "ghost";

const styles: Record<Variant, string> = {
  primary:
    "bg-primary text-on-primary hover:opacity-90 px-6 py-3.5 text-label",
  secondary:
    "border border-outline/40 bg-transparent text-on-surface hover:border-outline hover:bg-[var(--surface-high)] px-6 py-3.5 text-label",
  ghost:
    "text-on-surface-variant hover:text-on-surface underline-offset-4 hover:underline",
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
  const classes = `inline-flex items-center justify-center rounded-lg font-semibold transition-all duration-200 ease-cabin ${styles[variant]} ${className}`;

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
