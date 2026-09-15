import type { Config } from "tailwindcss";

export default {
  content: [
    "./src/pages/**/*.{js,ts,jsx,tsx,mdx}",
    "./src/components/**/*.{js,ts,jsx,tsx,mdx}",
    "./src/app/**/*.{js,ts,jsx,tsx,mdx}",
  ],
  darkMode: ["class", '[data-scheme="night"]'],
  theme: {
    extend: {
      colors: {
        background: "var(--background)",
        foreground: "var(--foreground)",
        surface: "var(--surface)",
        "surface-variant": "var(--surface-variant)",
        "on-surface": "var(--on-surface)",
        "on-surface-variant": "var(--on-surface-variant)",
        primary: "var(--primary)",
        "on-primary": "var(--on-primary)",
        secondary: "var(--secondary)",
        outline: "var(--outline)",
        charging: "var(--charging)",
        climate: "var(--climate)",
        warning: "var(--warning)",
        error: "var(--error)",
        success: "var(--success)",
      },
      fontFamily: {
        display: ["var(--font-display)", "system-ui", "sans-serif"],
        sans: ["var(--font-body)", "system-ui", "sans-serif"],
      },
      fontSize: {
        display: [
          "clamp(3.5rem, 9vw, 6.5rem)",
          { lineHeight: "0.95", letterSpacing: "-0.045em", fontWeight: "700" },
        ],
        headline: [
          "clamp(1.85rem, 3.4vw, 2.65rem)",
          { lineHeight: "1.15", letterSpacing: "-0.025em", fontWeight: "600" },
        ],
        title: ["1.375rem", { lineHeight: "1.3", fontWeight: "600" }],
        body: ["1.125rem", { lineHeight: "1.55", fontWeight: "400" }],
        label: ["0.9375rem", { lineHeight: "1.3", fontWeight: "600" }],
        status: ["0.8125rem", { lineHeight: "1.3", fontWeight: "500" }],
      },
      boxShadow: {
        elev1: "var(--elev-1)",
        elev2: "var(--elev-2)",
      },
      transitionTimingFunction: {
        cabin: "cubic-bezier(0.2, 0, 0, 1)",
        precise: "cubic-bezier(0.4, 0, 0.2, 1)",
      },
      keyframes: {
        "fade-rise": {
          from: { opacity: "0", transform: "translateY(28px)" },
          to: { opacity: "1", transform: "translateY(0)" },
        },
        "brand-in": {
          from: {
            opacity: "0",
            transform: "translateY(36px) scale(0.97)",
            filter: "blur(6px)",
          },
          to: {
            opacity: "1",
            transform: "translateY(0) scale(1)",
            filter: "blur(0)",
          },
        },
        "fade-in": {
          from: { opacity: "0" },
          to: { opacity: "1" },
        },
        "ambient-drift": {
          "0%, 100%": { transform: "translate3d(0,0,0) scale(1)" },
          "50%": { transform: "translate3d(3%, -2%, 0) scale(1.08)" },
        },
        "soft-pulse": {
          "0%, 100%": { opacity: "0.4" },
          "50%": { opacity: "0.85" },
        },
        "hmi-line": {
          from: { transform: "scaleX(0)", opacity: "0" },
          to: { transform: "scaleX(1)", opacity: "1" },
        },
        "sheen": {
          "0%": { backgroundPosition: "0% 50%" },
          "100%": { backgroundPosition: "100% 50%" },
        },
      },
      animation: {
        "fade-rise": "fade-rise 900ms cubic-bezier(0.16, 1, 0.3, 1) both",
        "brand-in": "brand-in 1100ms cubic-bezier(0.16, 1, 0.3, 1) both",
        "fade-in": "fade-in 700ms cubic-bezier(0.16, 1, 0.3, 1) both",
        "ambient-drift": "ambient-drift 14s ease-in-out infinite",
        "soft-pulse": "soft-pulse 4.5s ease-in-out infinite",
        "hmi-line": "hmi-line 1100ms cubic-bezier(0.16, 1, 0.3, 1) both",
      },
    },
  },
  plugins: [],
} satisfies Config;
