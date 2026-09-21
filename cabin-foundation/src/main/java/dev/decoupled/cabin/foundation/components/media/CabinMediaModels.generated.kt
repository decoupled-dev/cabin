@file:OptIn(dev.decoupled.cabin.foundation.CabinScaffold::class)

package dev.decoupled.cabin.foundation.components.media

/** GENERATED from components/cabin.components.yaml. Do not edit by hand — run python3 tools/generate_cabin_components.py. */

import dev.decoupled.cabin.compliance.CabinInteraction
import dev.decoupled.cabin.foundation.CabinComponentUiState
import dev.decoupled.cabin.foundation.CabinScaffold

@CabinScaffold
data class CabinMiniPlayerState(
    val label: String = "Mini player",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinMiniPlayerAction {
    data object Activate : CabinMiniPlayerAction
}

val CabinMiniPlayerInteraction: CabinInteraction = CabinInteraction.MediaTransport

@CabinScaffold
data class CabinDockPlayerState(
    val label: String = "Dock player",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinDockPlayerAction {
    data object Activate : CabinDockPlayerAction
}

val CabinDockPlayerInteraction: CabinInteraction = CabinInteraction.MediaTransport

@CabinScaffold
data class CabinTransportControlsState(
    val label: String = "Transport controls",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinTransportControlsAction {
    data object Activate : CabinTransportControlsAction
}

val CabinTransportControlsInteraction: CabinInteraction = CabinInteraction.MediaTransport

@CabinScaffold
data class CabinMediaSeekBarState(
    val label: String = "Media seek bar",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinMediaSeekBarAction {
    data object Activate : CabinMediaSeekBarAction
}

val CabinMediaSeekBarInteraction: CabinInteraction = CabinInteraction.MediaComplex

@CabinScaffold
data class CabinShuffleRepeatState(
    val label: String = "Shuffle / repeat",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinShuffleRepeatAction {
    data object Activate : CabinShuffleRepeatAction
}

val CabinShuffleRepeatInteraction: CabinInteraction = CabinInteraction.MediaTransport

@CabinScaffold
data class CabinRatingLikeState(
    val label: String = "Rating / like",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinRatingLikeAction {
    data object Activate : CabinRatingLikeAction
}

val CabinRatingLikeInteraction: CabinInteraction = CabinInteraction.MediaTransport

@CabinScaffold
data class CabinAlbumArtState(
    val label: String = "Album art",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinAlbumArtAction {
    data object Activate : CabinAlbumArtAction
}

val CabinAlbumArtInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinQueueListState(
    val label: String = "Queue list",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinQueueListAction {
    data object Activate : CabinQueueListAction
}

val CabinQueueListInteraction: CabinInteraction = CabinInteraction.MediaComplex

@CabinScaffold
data class CabinBrowseTreeState(
    val label: String = "Browse tree",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinBrowseTreeAction {
    data object Activate : CabinBrowseTreeAction
}

val CabinBrowseTreeInteraction: CabinInteraction = CabinInteraction.MediaComplex

@CabinScaffold
data class CabinSourceSwitcherState(
    val label: String = "Source switcher",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinSourceSwitcherAction {
    data object Activate : CabinSourceSwitcherAction
}

val CabinSourceSwitcherInteraction: CabinInteraction = CabinInteraction.MediaComplex

@CabinScaffold
data class CabinAudioZoneSelectorState(
    val label: String = "Audio-zone selector",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinAudioZoneSelectorAction {
    data object Activate : CabinAudioZoneSelectorAction
}

val CabinAudioZoneSelectorInteraction: CabinInteraction = CabinInteraction.MediaComplex

@CabinScaffold
data class CabinVolumePanelState(
    val label: String = "Volume panel",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinVolumePanelAction {
    data object Activate : CabinVolumePanelAction
}

val CabinVolumePanelInteraction: CabinInteraction = CabinInteraction.MediaTransport

@CabinScaffold
data class CabinFadeBalancePanelState(
    val label: String = "Fade / balance panel",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinFadeBalancePanelAction {
    data object Activate : CabinFadeBalancePanelAction
}

val CabinFadeBalancePanelInteraction: CabinInteraction = CabinInteraction.OpenComplexApp

@CabinScaffold
data class CabinEqControlsState(
    val label: String = "EQ controls",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinEqControlsAction {
    data object Activate : CabinEqControlsAction
}

val CabinEqControlsInteraction: CabinInteraction = CabinInteraction.OpenComplexApp

@CabinScaffold
data class CabinAudioVisualizerState(
    val label: String = "Audio visualizer",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinAudioVisualizerAction {
    data object Activate : CabinAudioVisualizerAction
}

val CabinAudioVisualizerInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinRadioTunerState(
    val label: String = "Radio tuner",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinRadioTunerAction {
    data object Activate : CabinRadioTunerAction
}

val CabinRadioTunerInteraction: CabinInteraction = CabinInteraction.MediaTransport

@CabinScaffold
data class CabinRadioPresetsState(
    val label: String = "Radio presets",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinRadioPresetsAction {
    data object Activate : CabinRadioPresetsAction
}

val CabinRadioPresetsInteraction: CabinInteraction = CabinInteraction.MediaTransport

@CabinScaffold
data class CabinStationListState(
    val label: String = "Station list",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinStationListAction {
    data object Activate : CabinStationListAction
}

val CabinStationListInteraction: CabinInteraction = CabinInteraction.MediaComplex

@CabinScaffold
data class CabinHdMetadataCardState(
    val label: String = "HD / DAB metadata",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinHdMetadataCardAction {
    data object Activate : CabinHdMetadataCardAction
}

val CabinHdMetadataCardInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinPodcastControlsState(
    val label: String = "Podcast controls",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinPodcastControlsAction {
    data object Activate : CabinPodcastControlsAction
}

val CabinPodcastControlsInteraction: CabinInteraction = CabinInteraction.MediaComplex

@CabinScaffold
data class CabinLyricsViewState(
    val label: String = "Lyrics view",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinLyricsViewAction {
    data object Activate : CabinLyricsViewAction
}

val CabinLyricsViewInteraction: CabinInteraction = CabinInteraction.ParkedOnly

@CabinScaffold
data class CabinVideoPlayerChromeState(
    val label: String = "Video player chrome",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinVideoPlayerChromeAction {
    data object Activate : CabinVideoPlayerChromeAction
}

val CabinVideoPlayerChromeInteraction: CabinInteraction = CabinInteraction.ParkedOnly

@CabinScaffold
data class CabinRearSeatMediaControlsState(
    val label: String = "Rear-seat media controls",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinRearSeatMediaControlsAction {
    data object Activate : CabinRearSeatMediaControlsAction
}

val CabinRearSeatMediaControlsInteraction: CabinInteraction = CabinInteraction.MediaTransport
