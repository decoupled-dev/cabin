import type { Metadata } from "next";
import {
  MotionDemo,
  SpaceScaleDemo,
  TypeScaleDemo,
} from "@/components/foundations-demos";
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
    <div className="bg-background">
      <GalleryHeader
        title="Foundations"
        summary="Color, type, space, and motion — live from generated tokens. Day and night resolve the same roles."
      />
      <GalleryNav label="Foundations sections" items={FOUNDATIONS_NAV} />

      <div className="mx-auto flex max-w-6xl flex-col gap-16 px-5 py-12 sm:px-8 sm:py-14">
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
          title="Type"
          body="Glance roles from display to status. Sizes and weights are cabin type-role tokens — flip day/night above to confirm contrast."
        >
          <div className="rounded-lg border border-[var(--outline-subtle)] bg-surface px-5 py-6 sm:px-6">
            <TypeScaleDemo />
          </div>
        </GallerySection>

        <GallerySection
          id="space"
          title="Space"
          body="Spacing steps and touch minimum from tokens. Automotive density floor stays fixed across schemes."
        >
          <div className="rounded-lg border border-[var(--outline-subtle)] bg-surface px-5 py-2 sm:px-6">
            <SpaceScaleDemo />
          </div>
        </GallerySection>

        <GallerySection
          id="motion"
          title="Motion"
          body="Fast, medium, slow durations from tokens. Mechanical and precise — decorative motion yields while driving."
        >
          <div className="rounded-lg border border-[var(--outline-subtle)] bg-surface px-5 py-2 sm:px-6">
            <MotionDemo />
          </div>
        </GallerySection>

        <footer className="border-t border-[var(--outline-subtle)] pt-6">
          <div className="flex flex-wrap items-center gap-x-4 gap-y-2 text-status text-on-surface-variant">
            <a
              href="/components"
              className="text-on-surface underline-offset-4 hover:underline"
            >
              Components
            </a>
            <span aria-hidden>·</span>
            <a
              href={`${DOCS_URL}${DOCS_FOUNDATIONS}`}
              className="underline-offset-4 hover:underline"
              rel="noreferrer"
            >
              Foundations docs
            </a>
          </div>
        </footer>
      </div>
    </div>
  );
}
