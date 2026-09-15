import type { Metadata } from "next";
import { Outfit, Source_Sans_3 } from "next/font/google";
import { SchemeProvider } from "@/components/scheme-provider";
import { SiteFooter } from "@/components/site-footer";
import { SiteHeader } from "@/components/site-header";
import "./globals.css";

const display = Outfit({
  subsets: ["latin"],
  variable: "--font-display",
  display: "swap",
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
          <SiteHeader />
          <main className="pt-16">{children}</main>
          <SiteFooter />
        </SchemeProvider>
      </body>
    </html>
  );
}
