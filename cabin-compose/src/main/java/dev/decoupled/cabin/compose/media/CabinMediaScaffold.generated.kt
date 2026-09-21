@file:OptIn(dev.decoupled.cabin.foundation.CabinScaffold::class)

package dev.decoupled.cabin.compose.media

/** GENERATED from components/cabin.components.yaml. Do not edit by hand — run python3 tools/generate_cabin_components.py. */

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.decoupled.cabin.compose.scaffold.CabinScaffoldHost
import dev.decoupled.cabin.foundation.components.media.*

/**
 * Experimental scaffold: Mini player.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinMiniPlayer(
    state: CabinMiniPlayerState = CabinMiniPlayerState(),
    onAction: (CabinMiniPlayerAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Mini player" },
        family = "media",
        testTag = "cabin_mini_player",
        interaction = CabinMiniPlayerInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinMiniPlayerAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Dock player.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinDockPlayer(
    state: CabinDockPlayerState = CabinDockPlayerState(),
    onAction: (CabinDockPlayerAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Dock player" },
        family = "media",
        testTag = "cabin_dock_player",
        interaction = CabinDockPlayerInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinDockPlayerAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Transport controls.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinTransportControls(
    state: CabinTransportControlsState = CabinTransportControlsState(),
    onAction: (CabinTransportControlsAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Transport controls" },
        family = "media",
        testTag = "cabin_transport_controls",
        interaction = CabinTransportControlsInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinTransportControlsAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Media seek bar.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinMediaSeekBar(
    state: CabinMediaSeekBarState = CabinMediaSeekBarState(),
    onAction: (CabinMediaSeekBarAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Media seek bar" },
        family = "media",
        testTag = "cabin_media_seek_bar",
        interaction = CabinMediaSeekBarInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinMediaSeekBarAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Shuffle / repeat.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinShuffleRepeat(
    state: CabinShuffleRepeatState = CabinShuffleRepeatState(),
    onAction: (CabinShuffleRepeatAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Shuffle / repeat" },
        family = "media",
        testTag = "cabin_shuffle_repeat",
        interaction = CabinShuffleRepeatInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinShuffleRepeatAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Rating / like.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinRatingLike(
    state: CabinRatingLikeState = CabinRatingLikeState(),
    onAction: (CabinRatingLikeAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Rating / like" },
        family = "media",
        testTag = "cabin_rating_like",
        interaction = CabinRatingLikeInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinRatingLikeAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Album art.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinAlbumArt(
    state: CabinAlbumArtState = CabinAlbumArtState(),
    onAction: (CabinAlbumArtAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Album art" },
        family = "media",
        testTag = "cabin_album_art",
        interaction = CabinAlbumArtInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinAlbumArtAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Queue list.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinQueueList(
    state: CabinQueueListState = CabinQueueListState(),
    onAction: (CabinQueueListAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Queue list" },
        family = "media",
        testTag = "cabin_queue_list",
        interaction = CabinQueueListInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinQueueListAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Browse tree.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinBrowseTree(
    state: CabinBrowseTreeState = CabinBrowseTreeState(),
    onAction: (CabinBrowseTreeAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Browse tree" },
        family = "media",
        testTag = "cabin_browse_tree",
        interaction = CabinBrowseTreeInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinBrowseTreeAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Source switcher.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinSourceSwitcher(
    state: CabinSourceSwitcherState = CabinSourceSwitcherState(),
    onAction: (CabinSourceSwitcherAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Source switcher" },
        family = "media",
        testTag = "cabin_source_switcher",
        interaction = CabinSourceSwitcherInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinSourceSwitcherAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Audio-zone selector.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinAudioZoneSelector(
    state: CabinAudioZoneSelectorState = CabinAudioZoneSelectorState(),
    onAction: (CabinAudioZoneSelectorAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Audio-zone selector" },
        family = "media",
        testTag = "cabin_audio_zone_selector",
        interaction = CabinAudioZoneSelectorInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinAudioZoneSelectorAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Volume panel.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinVolumePanel(
    state: CabinVolumePanelState = CabinVolumePanelState(),
    onAction: (CabinVolumePanelAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Volume panel" },
        family = "media",
        testTag = "cabin_volume_panel",
        interaction = CabinVolumePanelInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinVolumePanelAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Fade / balance panel.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinFadeBalancePanel(
    state: CabinFadeBalancePanelState = CabinFadeBalancePanelState(),
    onAction: (CabinFadeBalancePanelAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Fade / balance panel" },
        family = "media",
        testTag = "cabin_fade_balance_panel",
        interaction = CabinFadeBalancePanelInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinFadeBalancePanelAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: EQ controls.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinEqControls(
    state: CabinEqControlsState = CabinEqControlsState(),
    onAction: (CabinEqControlsAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "EQ controls" },
        family = "media",
        testTag = "cabin_eq_controls",
        interaction = CabinEqControlsInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinEqControlsAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Audio visualizer.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinAudioVisualizer(
    state: CabinAudioVisualizerState = CabinAudioVisualizerState(),
    onAction: (CabinAudioVisualizerAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Audio visualizer" },
        family = "media",
        testTag = "cabin_audio_visualizer",
        interaction = CabinAudioVisualizerInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinAudioVisualizerAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Radio tuner.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinRadioTuner(
    state: CabinRadioTunerState = CabinRadioTunerState(),
    onAction: (CabinRadioTunerAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Radio tuner" },
        family = "media",
        testTag = "cabin_radio_tuner",
        interaction = CabinRadioTunerInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinRadioTunerAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Radio presets.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinRadioPresets(
    state: CabinRadioPresetsState = CabinRadioPresetsState(),
    onAction: (CabinRadioPresetsAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Radio presets" },
        family = "media",
        testTag = "cabin_radio_presets",
        interaction = CabinRadioPresetsInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinRadioPresetsAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Station list.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinStationList(
    state: CabinStationListState = CabinStationListState(),
    onAction: (CabinStationListAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Station list" },
        family = "media",
        testTag = "cabin_station_list",
        interaction = CabinStationListInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinStationListAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: HD / DAB metadata.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinHdMetadataCard(
    state: CabinHdMetadataCardState = CabinHdMetadataCardState(),
    onAction: (CabinHdMetadataCardAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "HD / DAB metadata" },
        family = "media",
        testTag = "cabin_hd_metadata_card",
        interaction = CabinHdMetadataCardInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinHdMetadataCardAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Podcast controls.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinPodcastControls(
    state: CabinPodcastControlsState = CabinPodcastControlsState(),
    onAction: (CabinPodcastControlsAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Podcast controls" },
        family = "media",
        testTag = "cabin_podcast_controls",
        interaction = CabinPodcastControlsInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinPodcastControlsAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Lyrics view.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinLyricsView(
    state: CabinLyricsViewState = CabinLyricsViewState(),
    onAction: (CabinLyricsViewAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Lyrics view" },
        family = "media",
        testTag = "cabin_lyrics_view",
        interaction = CabinLyricsViewInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinLyricsViewAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Video player chrome.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinVideoPlayerChrome(
    state: CabinVideoPlayerChromeState = CabinVideoPlayerChromeState(),
    onAction: (CabinVideoPlayerChromeAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Video player chrome" },
        family = "media",
        testTag = "cabin_video_player_chrome",
        interaction = CabinVideoPlayerChromeInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinVideoPlayerChromeAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Rear-seat media controls.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinRearSeatMediaControls(
    state: CabinRearSeatMediaControlsState = CabinRearSeatMediaControlsState(),
    onAction: (CabinRearSeatMediaControlsAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Rear-seat media controls" },
        family = "media",
        testTag = "cabin_rear_seat_media_controls",
        interaction = CabinRearSeatMediaControlsInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinRearSeatMediaControlsAction.Activate) },
        modifier = modifier,
    )
}
