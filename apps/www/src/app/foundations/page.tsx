import type { Metadata } from "next";
import {
  MotionDemo,
  SpaceScaleDemo,
  TypeScaleDemo,
} from "@/components/foundations-demos";
import { DemoStage } from "@/components/demo-stage";
import {
  GalleryHeader,
  GalleryNav,
  GallerySection,
} from "@/components/gallery-chrome";
import { TokenExplorer } from "@/components/token-explorer";
import { DOCS_URL } from "@/lib/site";
import { DOCS_FOUNDATIONS, DOCS_TOKENS, FOUNDATIONS_NAV } from "@/lib/tokens";

export const metadata: Metadata = {
  title: "Foundations",
  description:
    "Cabin foundations — color, type, space, and motion from generated design tokens. Live day/night demos.",
};

export default function FoundationsPage() {
  return (
    <div className="bg-background" data-gallery="foundations">
      <GalleryHeader
        title="Foundations"
        summary="Color, type, space, and motion — live from generated tokens. Flip Night / Day in the header."
      />
      <GalleryNav label="Foundations sections" items={FOUNDATIONS_NAV} />

      <div className="mx-auto flex max-w-6xl flex-col gap-14 px-5 py-10 sm:px-8 sm:py-12">
        <GallerySection
          id="color"
          title="Color"
          body="Scheme and semantic roles from cabin.tokens.css. Outline, container, and safety-locked warning / error / charging update with day and night — no hand hex."
        >
          <TokenExplorer />
          <p className="mt-4 text-status text-on-surface-variant">
            Domain accents stay off body copy.{" "}
            <a
              href={`${DOCS_URL}${DOCS_TOKENS}`}
              className="text-primary underline-offset-4 hover:underline"
              rel="noreferrer"
            >
              Tokens docs
            </a>
          </p>
        </GallerySection>

        <GallerySection
          id="type"
          title="Type & Space"
          body="Glance type roles beside spacing steps. Sizes resolve from cabin type and space tokens."
        >
          <div className="grid gap-3 lg:grid-cols-2 lg:gap-4">
            <DemoStage>
              <div className="rounded-lg bg-surface px-5 py-5 sm:px-6">
                <p className="mb-4 text-status font-semibold uppercase tracking-[0.12em] text-on-surface-variant">
                  Type
                </p>
                <TypeScaleDemo />
              </div>
            </DemoStage>
            <DemoStage>
              <div className="rounded-lg bg-surface px-5 py-5 sm:px-6">
                <p className="mb-4 text-status font-semibold uppercase tracking-[0.12em] text-on-surface-variant">
                  Space
                </p>
                <SpaceScaleDemo />
              </div>
            </DemoStage>
          </div>
        </GallerySection>

        {/* Keep #space for deep links */}
        <div id="space" className="sr-only" aria-hidden />

        <GallerySection
          id="motion"
          title="Motion"
          body="Fast, medium, slow durations from tokens. Mechanical and precise — decorative motion yields while driving."
        >
          <DemoStage>
            <div className="rounded-lg bg-surface px-5 py-4 sm:px-6">
              <MotionDemo />
            </div>
          </DemoStage>
        </GallerySection>

        <p className="border-t border-[var(--outline-subtle)] pt-5 text-status text-on-surface-variant">
          <a
            href="/components"
            className="text-on-surface underline-offset-4 hover:underline"
          >
            Components
          </a>
          <span aria-hidden className="mx-2">
            ·
          </span>
          <a
            href={`${DOCS_URL}${DOCS_FOUNDATIONS}`}
            className="underline-offset-4 hover:underline"
            rel="noreferrer"
          >
            Foundations docs
          </a>
        </p>
      </div>
    </div>
  );
}
