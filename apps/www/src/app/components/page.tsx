import type { Metadata } from "next";
import { BarsBezelDemo } from "@/components/demo-chrome-bars";
import { ClimateTileDemo } from "@/components/demo-climate-tile";
import { ComposedScreenDemo } from "@/components/demo-composed-screen";
import { MediaNowPlayingDemo } from "@/components/demo-media-now-playing";
import { KitInspectorDemo } from "@/components/demo-scaffold-host";
import { TwoUpStage } from "@/components/demo-stage";
import {
  GalleryHeader,
  GalleryNav,
  GallerySection,
} from "@/components/gallery-chrome";
import { PageFrame } from "@/components/site-chrome";
import { DOCS_URL, KITCHEN_SINK_URL } from "@/lib/site";
import {
  COMPONENTS_NAV,
  DOCS_CLIMATE_TILE,
  DOCS_MEDIA_NOW,
  DOCS_STATUS_BAR,
  DOCS_SYSTEM_BAR,
} from "@/lib/demo-signal";

export const metadata: Metadata = {
  title: "Components",
  description:
    "Cabin components — Bars, ClimateTile, MediaNowPlaying, Experimental kit chrome, and screens composed from the kit.",
};

export default function ComponentsPage() {
  return (
    <div className="bg-background" data-gallery="components">
      <GalleryHeader
        title="Components"
        summary="Bars, Climate, Media, then kit chrome and screens built from those pieces. One scheme toggle in the header."
      />
      <GalleryNav label="Components sections" items={COMPONENTS_NAV} />

      <PageFrame className="flex flex-col gap-16 py-10 sm:py-12">
        <GallerySection
          id="bars"
          title="Bars"
          body="Status and System in instrument bezel. Container / outline / onContainer from the scheme. Warning and charging stay safety-locked at night."
        >
          <BarsBezelDemo />
          <p className="mt-4 flex flex-wrap gap-x-4 gap-y-1 text-status text-on-surface-variant">
            <a
              href={`${DOCS_URL}${DOCS_STATUS_BAR}`}
              className="text-primary underline-offset-4 hover:underline"
              rel="noreferrer"
            >
              Status bar spec
            </a>
            <a
              href={`${DOCS_URL}${DOCS_SYSTEM_BAR}`}
              className="text-primary underline-offset-4 hover:underline"
              rel="noreferrer"
            >
              System bar spec
            </a>
          </p>
        </GallerySection>

        <GallerySection
          id="climate"
          title="Climate & Media"
          body="Two-up cabin density. Climate accent and mediaAccent as marks only — never washes. Transport uses cabin control glyphs."
        >
          <TwoUpStage
            left={
              <div className="p-4 sm:p-5">
                <p className="kicker mb-3 text-on-surface-variant">
                  ClimateTile
                </p>
                <ClimateTileDemo />
              </div>
            }
            right={
              <div className="p-4 sm:p-5">
                <p className="kicker mb-3 text-on-surface-variant">
                  MediaNowPlaying
                </p>
                <MediaNowPlayingDemo />
              </div>
            }
          />
          <p className="mt-4 flex flex-wrap gap-x-4 gap-y-1 text-status text-on-surface-variant">
            <a
              href={`${DOCS_URL}${DOCS_CLIMATE_TILE}`}
              className="text-primary underline-offset-4 hover:underline"
              rel="noreferrer"
            >
              ClimateTile spec
            </a>
            <a
              href={`${DOCS_URL}${DOCS_MEDIA_NOW}`}
              className="text-primary underline-offset-4 hover:underline"
              rel="noreferrer"
            >
              MediaNowPlaying spec
            </a>
          </p>
        </GallerySection>

        <div id="media" className="sr-only" aria-hidden />

        <GallerySection
          id="kit"
          title="Kit"
          body="Experimental shared chrome for generated Compose / Views stubs — family accent mark, focus ring, Restriction Engine copy. Inspect one row at a time; the Android sample is samples/kitchen-sink."
        >
          <KitInspectorDemo />
        </GallerySection>

        <GallerySection
          id="screens"
          title="Screens"
          body="Cabin surfaces assembled from the kit — dashboard chrome with now playing and climate. Parked / Moving uses the same Restriction Engine as the sample app."
        >
          <ComposedScreenDemo />
        </GallerySection>

        <p className="border-t border-[var(--outline-subtle)] pt-5 text-status text-on-surface-variant">
          <a
            href="/use-cases"
            className="text-on-surface underline-offset-4 hover:underline"
          >
            Use cases
          </a>
          <span aria-hidden className="mx-2">
            ·
          </span>
          <a
            href="/foundations"
            className="text-on-surface underline-offset-4 hover:underline"
          >
            Foundations
          </a>
          <span aria-hidden className="mx-2">
            ·
          </span>
          <a
            href={KITCHEN_SINK_URL}
            className="text-on-surface underline-offset-4 hover:underline"
            rel="noreferrer"
          >
            Kitchen sink sample
          </a>
        </p>
      </PageFrame>
    </div>
  );
}
