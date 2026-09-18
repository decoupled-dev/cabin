import type { Metadata } from "next";
import {
  ChargingStage,
  MovingStage,
  ParkedStage,
} from "@/components/use-case-stages";
import {
  GalleryHeader,
  GalleryNav,
  GallerySection,
} from "@/components/gallery-chrome";
import { USE_CASES_NAV } from "@/lib/use-cases";

export const metadata: Metadata = {
  title: "Use cases",
  description:
    "Cabin use cases — Parked climate, Moving media glance, and Charging energy. Live full-bleed HMI stages with day/night.",
};

export default function UseCasesPage() {
  return (
    <div className="bg-background" data-gallery="use-cases">
      <GalleryHeader
        title="Use cases"
        summary="Three automotive HMI stages — Parked, Moving, Charging. Full-bleed bezels; one scheme toggle in the header."
      />
      <GalleryNav label="Use case stages" items={USE_CASES_NAV} />

      <div className="mx-auto flex max-w-6xl flex-col gap-14 px-5 py-10 sm:px-8 sm:py-12">
        <GallerySection
          id="parked"
          title="Parked"
          body="Climate full control — temperature, fan, and seats. Forest night bezel; climate accent mark-only."
        >
          <ParkedStage />
        </GallerySection>

        <GallerySection
          id="moving"
          title="Moving"
          body="Media glance while driving. Transport Allow; source and complex controls RE-quiet. Fail-closed — no distraction chrome."
        >
          <MovingStage />
        </GallerySection>

        <GallerySection
          id="charging"
          title="Charging"
          body="EV energy — honest range and percent. Charging color stays locked. Empty, stale, and fault stay honest."
        >
          <ChargingStage />
        </GallerySection>
      </div>
    </div>
  );
}
