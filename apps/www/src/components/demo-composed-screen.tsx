"use client";

import { useState } from "react";
import { ClimateTileDemo } from "@/components/demo-climate-tile";
import {
  StatusBarDemo,
  SystemBarDemo,
} from "@/components/demo-chrome-bars";
import { MediaNowPlayingDemo } from "@/components/demo-media-now-playing";
import { BezelChrome, DemoRail } from "@/components/demo-stage";
import { SegmentedControl } from "@/components/segmented-control";
import type { DriveState } from "@/lib/demo-signal";

/**
 * Web analog of kitchen-sink composed screens — bars + climate + media
 * in one instrument. Not a use-case stage and not an inventory grid.
 */
export function ComposedScreenDemo() {
  const [drive, setDrive] = useState<DriveState>("parked");

  return (
    <div>
      <BezelChrome
        status={<StatusBarDemo embedded />}
        system={<SystemBarDemo embedded drive={drive} />}
      >
        <div
          className="grid gap-4 lg:grid-cols-2"
          data-testid="composed-dashboard"
        >
          <div>
            <p className="kicker mb-3 text-on-surface-variant">Now playing</p>
            <MediaNowPlayingDemo compact lockedDrive={drive} />
          </div>
          <div>
            <p className="kicker mb-3 text-on-surface-variant">Climate</p>
            <ClimateTileDemo compact lockedDrive={drive} />
          </div>
        </div>
      </BezelChrome>

      <DemoRail label="Drive">
        <SegmentedControl
          ariaLabel="Drive state for composed screen"
          value={drive}
          onChange={setDrive}
          fill="quiet"
          options={[
            { id: "parked", label: "Parked" },
            { id: "moving", label: "Moving" },
          ]}
        />
        <p className="text-status text-on-surface-variant">
          {drive === "moving"
            ? "Moving: transport Allow; HVAC and Apps RE-quiet. Same Restriction Engine as the kit."
            : "Parked: climate, source, and apps Allow."}
        </p>
      </DemoRail>
    </div>
  );
}
