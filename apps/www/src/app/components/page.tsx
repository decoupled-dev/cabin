import type { Metadata } from "next";
import { StatusBarDemo, SystemBarDemo } from "@/components/demo-chrome-bars";
import { ClimateTileDemo } from "@/components/demo-climate-tile";
import { MediaNowPlayingDemo } from "@/components/demo-media-now-playing";
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
    "Cabin components — Bars, ClimateTile, and MediaNowPlaying live demos from generated tokens. Day and night.",
};

export default function ComponentsPage() {
  return (
    <div className="bg-background">
      <GalleryHeader
        title="Components"
        summary="Bars, Climate, and Media first — web HMI demos on generated tokens. Day and night in the gallery bar."
      />
      <GalleryNav label="Components sections" items={COMPONENTS_NAV} />

      <div className="mx-auto flex max-w-6xl flex-col gap-16 px-5 py-12 sm:px-8 sm:py-14">
        <GallerySection
          id="bars"
          title="Bars"
          body="Status and System bars. Container / outline / onContainer from the scheme. Warning and charging stay safety-locked at night."
        >
          <div className="grid gap-12 lg:grid-cols-2">
            <div>
              <h3 className="mb-4 text-label text-on-surface">Status bar</h3>
              <StatusBarDemo />
              <a
                href={`${DOCS_URL}${DOCS_STATUS_BAR}`}
                className="mt-4 inline-block text-status text-primary underline-offset-4 hover:underline"
                rel="noreferrer"
              >
                Status bar spec
              </a>
            </div>
            <div>
              <h3 className="mb-4 text-label text-on-surface">System bar</h3>
              <SystemBarDemo />
              <a
                href={`${DOCS_URL}${DOCS_SYSTEM_BAR}`}
                className="mt-4 inline-block text-status text-primary underline-offset-4 hover:underline"
                rel="noreferrer"
              >
                System bar spec
              </a>
            </div>
          </div>
        </GallerySection>

        <GallerySection
          id="climate"
          title="Climate"
          body="ClimateTile — temp, fan, seat heat. Climate accent as mark only. Adjustments Block while Moving; values stay glanceable (RE-quiet)."
        >
          <div className="max-w-xl">
            <ClimateTileDemo />
          </div>
          <a
            href={`${DOCS_URL}${DOCS_CLIMATE_TILE}`}
            className="mt-6 inline-block text-status text-primary underline-offset-4 hover:underline"
            rel="noreferrer"
          >
            ClimateTile Android spec
          </a>
        </GallerySection>

        <GallerySection
          id="media"
          title="Media"
          body="MediaNowPlaying — artwork well, metadata, transport, source. Progress only when both Signals are live. Media accent marks source only."
        >
          <div className="max-w-2xl">
            <MediaNowPlayingDemo />
          </div>
          <a
            href={`${DOCS_URL}${DOCS_MEDIA_NOW}`}
            className="mt-6 inline-block text-status text-primary underline-offset-4 hover:underline"
            rel="noreferrer"
          >
            MediaNowPlaying Android spec
          </a>
        </GallerySection>

        <footer className="border-t border-[var(--outline-subtle)] pt-6">
          <div className="flex flex-wrap items-center gap-x-4 gap-y-2 text-status text-on-surface-variant">
            <a
              href="/foundations#color"
              className="text-on-surface underline-offset-4 hover:underline"
            >
              Foundations
            </a>
            <span aria-hidden>·</span>
            <a
              href={`${DOCS_URL}components/`}
              className="underline-offset-4 hover:underline"
              rel="noreferrer"
            >
              Component specs
            </a>
          </div>
        </footer>
      </div>
    </div>
  );
}
