import type { Metadata } from "next";
import { Syne, Source_Sans_3 } from "next/font/google";
import { SchemeProvider } from "@/components/scheme-provider";
import { SiteFooter } from "@/components/site-footer";
import { SiteHeader } from "@/components/site-header";
import "./globals.css";

const display = Syne({
  subsets: ["latin"],
  variable: "--font-display",
  display: "swap",
  weight: ["600", "700", "800"],
});

const body = Source_Sans_3({
  subsets: ["latin"],
  variable: "--font-body",
  display: "swap",
});

export const metadata: Metadata = {
  title: {
    default: "Cabin — Design language for the automobile cabin",
    template: "%s · Cabin",
  },
  description:
    "Cabin is an open-source AAOS design language and dual UI kit — glanceable, quiet, and safe by default. Compose and Views as peers, with compliance built in.",
  metadataBase: new URL("https://cabin.decoupled.dev"),
  openGraph: {
    title: "Cabin — Design language for the automobile cabin",
    description:
      "Material Design 3–class craft for Android Automotive OS. Quiet, premium, compliance-first.",
    type: "website",
  },
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html
      lang="en"
      data-scheme="night"
      className={`${display.variable} ${body.variable}`}
      suppressHydrationWarning
    >
      <body className="min-h-screen bg-background font-sans text-on-surface antialiased">
        <SchemeProvider>
          <a
            href="#content"
            className="sr-only focus:not-sr-only focus:absolute focus:left-4 focus:top-4 focus:z-[60] focus:rounded-md focus:bg-primary focus:px-4 focus:py-2 focus:text-on-primary"
          >
            Skip to content
          </a>
          <SiteHeader />
          <main id="content" className="pt-16">
            {children}
          </main>
          <SiteFooter />
        </SchemeProvider>
      </body>
    </html>
  );
}
