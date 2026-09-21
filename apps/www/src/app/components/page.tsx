import type { Metadata } from "next";
import { ComposedScreenDemo } from "@/components/demo-composed-screen";
import { CabinThemeBuilder } from "@/components/theme-builder";
import {
  GalleryHeader,
  GalleryNav,
  GallerySection,
} from "@/components/gallery-chrome";
import { PageFrame } from "@/components/site-chrome";
import { DOCS_URL, KITCHEN_SINK_URL } from "@/lib/site";
import { COMPONENTS_NAV } from "@/lib/demo-signal";

export const metadata: Metadata = {
  title: "Components",
  description:
    "Cabin Theme Builder — live kit preview against token schemes, families, and Restriction Engine.",
};

export default function ComponentsPage() {
  return (
    <div className="bg-background" data-gallery="components">
      <GalleryHeader
        title="Components"
        summary="Theme Builder for the Cabin kit. Night / Day in the header restyles every analog. Families and screens sit below the rails."
      />
      <GalleryNav label="Components sections" items={COMPONENTS_NAV} />

      <CabinThemeBuilder />

      <PageFrame className="flex flex-col gap-16 py-10 sm:py-12">
        <GallerySection
          id="screens"
          title="Screens"
          body="Cabin surfaces assembled from the kit — dashboard chrome with now playing and climate. Parked / Moving uses the same Restriction Engine as the builder."
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
            href={`${DOCS_URL}components/specs/system-bar`}
            className="text-on-surface underline-offset-4 hover:underline"
            rel="noreferrer"
          >
            Specs
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
