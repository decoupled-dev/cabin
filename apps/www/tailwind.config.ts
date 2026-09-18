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
        container: "var(--container)",
        "on-container": "var(--on-container)",
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
        /* Brand-first: display dominates; headline supports, never competes */
        display: [
          "clamp(3.5rem, 9vw, 6.25rem)",
          { lineHeight: "0.92", letterSpacing: "-0.045em", fontWeight: "700" },
        ],
        headline: [
          "clamp(1.625rem, 2.8vw, 2.25rem)",
          { lineHeight: "1.18", letterSpacing: "-0.025em", fontWeight: "600" },
        ],
        title: ["1.25rem", { lineHeight: "1.3", fontWeight: "600" }],
        body: ["1.0625rem", { lineHeight: "1.55", fontWeight: "400" }],
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
          from: { opacity: "0", transform: "translateY(10px)" },
          to: { opacity: "1", transform: "translateY(0)" },
        },
        "brand-in": {
          from: {
            opacity: "0",
            transform: "translateY(12px)",
          },
          to: {
            opacity: "1",
            transform: "translateY(0)",
          },
        },
        "fade-in": {
          from: { opacity: "0" },
          to: { opacity: "1" },
        },
        "hmi-line": {
          from: { transform: "scaleX(0)" },
          to: { transform: "scaleX(1)" },
        },
        "rail-in": {
          from: { transform: "scaleX(0)" },
          to: { transform: "scaleX(1)" },
        },
      },
      animation: {
        /* Short Material-like entrance only — no bounce / elastic / ambient loops */
        "fade-rise": "fade-rise 420ms cubic-bezier(0.2, 0, 0, 1) both",
        "brand-in": "brand-in 480ms cubic-bezier(0.2, 0, 0, 1) both",
        "fade-in": "fade-in 360ms cubic-bezier(0.2, 0, 0, 1) both",
        "hmi-line": "hmi-line 500ms cubic-bezier(0.2, 0, 0, 1) both",
        "rail-in": "rail-in 560ms cubic-bezier(0.2, 0, 0, 1) both",
      },
    },
  },
  plugins: [],
} satisfies Config;
