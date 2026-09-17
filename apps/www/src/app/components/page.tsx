import type { Metadata } from "next";
import { BarsBezelDemo } from "@/components/demo-chrome-bars";
import { ClimateTileDemo } from "@/components/demo-climate-tile";
import { MediaNowPlayingDemo } from "@/components/demo-media-now-playing";
import { TwoUpStage } from "@/components/demo-stage";
import {
  GalleryHeader,
  GalleryNav,
  GallerySection,
} from "@/components/gallery-chrome";
import { DOCS_URL } from "@/lib/site";
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
    "Cabin components — Bars, ClimateTile, and MediaNowPlaying live demos from generated tokens.",
};

export default function ComponentsPage() {
  return (
    <div className="bg-background" data-gallery="components">
      <GalleryHeader
        title="Components"
        summary="Bars, Climate, and Media — denser HMI stages on generated tokens. One scheme toggle in the header."
      />
      <GalleryNav label="Components sections" items={COMPONENTS_NAV} />

      <div className="mx-auto flex max-w-6xl flex-col gap-14 px-5 py-10 sm:px-8 sm:py-12">
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
              <div>
                <p className="mb-3 px-1 text-status font-semibold uppercase tracking-[0.12em] text-on-surface-variant">
                  ClimateTile
                </p>
                <ClimateTileDemo />
              </div>
            }
            right={
              <div>
                <p className="mb-3 px-1 text-status font-semibold uppercase tracking-[0.12em] text-on-surface-variant">
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

        {/* Keep #media anchor for deep links from prior nav */}
        <div id="media" className="sr-only" aria-hidden />
      </div>
    </div>
  );
}
