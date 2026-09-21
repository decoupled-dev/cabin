@file:OptIn(dev.decoupled.cabin.foundation.CabinScaffold::class)

package dev.decoupled.cabin.catalog

/** GENERATED from components/cabin.components.yaml. Do not edit by hand — run python3 tools/generate_cabin_components.py. */

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.decoupled.cabin.compose.action.CabinActionRow
import dev.decoupled.cabin.compose.action.CabinButton
import dev.decoupled.cabin.compose.action.CabinButtonGroup
import dev.decoupled.cabin.compose.action.CabinFab
import dev.decoupled.cabin.compose.action.CabinHoldToConfirmButton
import dev.decoupled.cabin.compose.action.CabinLongPressButton
import dev.decoupled.cabin.compose.action.CabinPressHoldRepeater
import dev.decoupled.cabin.compose.action.CabinSafetyActionButton
import dev.decoupled.cabin.compose.action.CabinSegmentedButton
import dev.decoupled.cabin.compose.action.CabinSplitButton
import dev.decoupled.cabin.compose.action.CabinToggleButton
import dev.decoupled.cabin.compose.adas.CabinAccControls
import dev.decoupled.cabin.compose.adas.CabinAdasStatusChip
import dev.decoupled.cabin.compose.adas.CabinAutoParkFlow
import dev.decoupled.cabin.compose.adas.CabinBlindSpotAlert
import dev.decoupled.cabin.compose.adas.CabinCameraView
import dev.decoupled.cabin.compose.adas.CabinDashcamControls
import dev.decoupled.cabin.compose.adas.CabinDriverAttention
import dev.decoupled.cabin.compose.adas.CabinHitchView
import dev.decoupled.cabin.compose.adas.CabinParkingSensor
import dev.decoupled.cabin.compose.adas.CabinRecordingsList
import dev.decoupled.cabin.compose.adas.CabinViewSelector
import dev.decoupled.cabin.compose.collection.CabinAdaptiveGrid
import dev.decoupled.cabin.compose.collection.CabinAppGrid
import dev.decoupled.cabin.compose.collection.CabinDataTable
import dev.decoupled.cabin.compose.collection.CabinDescriptionList
import dev.decoupled.cabin.compose.collection.CabinEmptyState
import dev.decoupled.cabin.compose.collection.CabinErrorState
import dev.decoupled.cabin.compose.collection.CabinFilterBar
import dev.decoupled.cabin.compose.collection.CabinGroupedList
import dev.decoupled.cabin.compose.collection.CabinInfoRow
import dev.decoupled.cabin.compose.collection.CabinJumpList
import dev.decoupled.cabin.compose.collection.CabinKeyValueRow
import dev.decoupled.cabin.compose.collection.CabinListItem
import dev.decoupled.cabin.compose.collection.CabinLoadingCollection
import dev.decoupled.cabin.compose.collection.CabinMediaGrid
import dev.decoupled.cabin.compose.collection.CabinPagedList
import dev.decoupled.cabin.compose.collection.CabinSearchResultList
import dev.decoupled.cabin.compose.collection.CabinSectionedList
import dev.decoupled.cabin.compose.collection.CabinSortControl
import dev.decoupled.cabin.compose.collection.CabinTimelineList
import dev.decoupled.cabin.compose.comms.CabinBluetoothPairing
import dev.decoupled.cabin.compose.comms.CabinCallControls
import dev.decoupled.cabin.compose.comms.CabinCannedReplyChips
import dev.decoupled.cabin.compose.comms.CabinContactCard
import dev.decoupled.cabin.compose.comms.CabinContactList
import dev.decoupled.cabin.compose.comms.CabinConversationList
import dev.decoupled.cabin.compose.comms.CabinDeviceList
import dev.decoupled.cabin.compose.comms.CabinDialer
import dev.decoupled.cabin.compose.comms.CabinInCallScreen
import dev.decoupled.cabin.compose.comms.CabinIncomingCallHud
import dev.decoupled.cabin.compose.comms.CabinMessageBubble
import dev.decoupled.cabin.compose.comms.CabinProjectionStatus
import dev.decoupled.cabin.compose.comms.CabinVoiceReply
import dev.decoupled.cabin.compose.comms.CabinVoicemail
import dev.decoupled.cabin.compose.ev.CabinChargeLimitSlider
import dev.decoupled.cabin.compose.ev.CabinChargePortStatus
import dev.decoupled.cabin.compose.ev.CabinChargePrecondition
import dev.decoupled.cabin.compose.ev.CabinChargeSessionCard
import dev.decoupled.cabin.compose.ev.CabinChargingSchedule
import dev.decoupled.cabin.compose.ev.CabinChargingStationCard
import dev.decoupled.cabin.compose.ev.CabinConnectorTypeChip
import dev.decoupled.cabin.compose.ev.CabinConsumptionChart
import dev.decoupled.cabin.compose.ev.CabinEnergyFlowDiagram
import dev.decoupled.cabin.compose.ev.CabinFuelLevel
import dev.decoupled.cabin.compose.ev.CabinHybridRangeBreakdown
import dev.decoupled.cabin.compose.ev.CabinRangeEstimator
import dev.decoupled.cabin.compose.ev.CabinRangeOnMap
import dev.decoupled.cabin.compose.ev.CabinRegenIndicator
import dev.decoupled.cabin.compose.ev.CabinTripChargePlanner
import dev.decoupled.cabin.compose.ev.CabinV2xControls
import dev.decoupled.cabin.compose.feedback.CabinBadge
import dev.decoupled.cabin.compose.feedback.CabinCallout
import dev.decoupled.cabin.compose.feedback.CabinCircularProgress
import dev.decoupled.cabin.compose.feedback.CabinContextMenu
import dev.decoupled.cabin.compose.feedback.CabinDialog
import dev.decoupled.cabin.compose.feedback.CabinDrivingRestrictedState
import dev.decoupled.cabin.compose.feedback.CabinInlineMessage
import dev.decoupled.cabin.compose.feedback.CabinLinearProgress
import dev.decoupled.cabin.compose.feedback.CabinNoPermissionState
import dev.decoupled.cabin.compose.feedback.CabinOfflineState
import dev.decoupled.cabin.compose.feedback.CabinSkeleton
import dev.decoupled.cabin.compose.feedback.CabinSnackbar
import dev.decoupled.cabin.compose.feedback.CabinSplashScreen
import dev.decoupled.cabin.compose.feedback.CabinStatusPill
import dev.decoupled.cabin.compose.feedback.CabinStepProgress
import dev.decoupled.cabin.compose.feedback.CabinTag
import dev.decoupled.cabin.compose.feedback.CabinToast
import dev.decoupled.cabin.compose.feedback.CabinTooltip
import dev.decoupled.cabin.compose.hvac.CabinAirQualityIndicator
import dev.decoupled.cabin.compose.hvac.CabinAirflowDirection
import dev.decoupled.cabin.compose.hvac.CabinAmbientLighting
import dev.decoupled.cabin.compose.hvac.CabinAutoAcToggle
import dev.decoupled.cabin.compose.hvac.CabinDefrostToggle
import dev.decoupled.cabin.compose.hvac.CabinEcoToggle
import dev.decoupled.cabin.compose.hvac.CabinFanSpeed
import dev.decoupled.cabin.compose.hvac.CabinFragranceControl
import dev.decoupled.cabin.compose.hvac.CabinHvacOverlay
import dev.decoupled.cabin.compose.hvac.CabinMirrorHeat
import dev.decoupled.cabin.compose.hvac.CabinPersistentHvacBar
import dev.decoupled.cabin.compose.hvac.CabinPreconditioningScheduler
import dev.decoupled.cabin.compose.hvac.CabinRecircToggle
import dev.decoupled.cabin.compose.hvac.CabinSeatClimate
import dev.decoupled.cabin.compose.hvac.CabinSteeringWheelHeat
import dev.decoupled.cabin.compose.hvac.CabinSunroofControl
import dev.decoupled.cabin.compose.hvac.CabinTemperatureControl
import dev.decoupled.cabin.compose.hvac.CabinZoneSelector
import dev.decoupled.cabin.compose.input.CabinAutocomplete
import dev.decoupled.cabin.compose.input.CabinColorPicker
import dev.decoupled.cabin.compose.input.CabinDatePicker
import dev.decoupled.cabin.compose.input.CabinDialKnob
import dev.decoupled.cabin.compose.input.CabinDialPad
import dev.decoupled.cabin.compose.input.CabinDropdown
import dev.decoupled.cabin.compose.input.CabinDurationPicker
import dev.decoupled.cabin.compose.input.CabinGesturePad
import dev.decoupled.cabin.compose.input.CabinListPicker
import dev.decoupled.cabin.compose.input.CabinListeningIndicator
import dev.decoupled.cabin.compose.input.CabinMaskedInput
import dev.decoupled.cabin.compose.input.CabinMultilineText
import dev.decoupled.cabin.compose.input.CabinNumberPicker
import dev.decoupled.cabin.compose.input.CabinNumericKeypad
import dev.decoupled.cabin.compose.input.CabinOptionPicker
import dev.decoupled.cabin.compose.input.CabinOtpField
import dev.decoupled.cabin.compose.input.CabinPinField
import dev.decoupled.cabin.compose.input.CabinRangeSlider
import dev.decoupled.cabin.compose.input.CabinRatingInput
import dev.decoupled.cabin.compose.input.CabinRotaryTextEntry
import dev.decoupled.cabin.compose.input.CabinSearchField
import dev.decoupled.cabin.compose.input.CabinSlider
import dev.decoupled.cabin.compose.input.CabinStepper
import dev.decoupled.cabin.compose.input.CabinTextField
import dev.decoupled.cabin.compose.input.CabinTimePicker
import dev.decoupled.cabin.compose.input.CabinTranscriptionField
import dev.decoupled.cabin.compose.input.CabinVoiceMicButton
import dev.decoupled.cabin.compose.input.CabinWheelPicker
import dev.decoupled.cabin.compose.launcher.CabinAppIcon
import dev.decoupled.cabin.compose.launcher.CabinHomeTemplate
import dev.decoupled.cabin.compose.launcher.CabinLauncherScaffold
import dev.decoupled.cabin.compose.launcher.CabinRecentsSwitcher
import dev.decoupled.cabin.compose.launcher.CabinScreensaver
import dev.decoupled.cabin.compose.launcher.CabinShortcutTile
import dev.decoupled.cabin.compose.launcher.CabinSuggestionCard
import dev.decoupled.cabin.compose.launcher.CabinWallpaperPicker
import dev.decoupled.cabin.compose.launcher.CabinWelcomeScreen
import dev.decoupled.cabin.compose.launcher.CabinWidgetEditMode
import dev.decoupled.cabin.compose.launcher.CabinWidgetHost
import dev.decoupled.cabin.compose.launcher.CabinWidgetPicker
import dev.decoupled.cabin.compose.layout.CabinAdaptiveContainer
import dev.decoupled.cabin.compose.layout.CabinCollapsibleSidePanel
import dev.decoupled.cabin.compose.layout.CabinPaneDivider
import dev.decoupled.cabin.compose.maps.CabinArrivalPanel
import dev.decoupled.cabin.compose.maps.CabinEtaPanel
import dev.decoupled.cabin.compose.maps.CabinFavoritesList
import dev.decoupled.cabin.compose.maps.CabinJunctionView
import dev.decoupled.cabin.compose.maps.CabinLaneGuidance
import dev.decoupled.cabin.compose.maps.CabinMapContainer
import dev.decoupled.cabin.compose.maps.CabinMapControls
import dev.decoupled.cabin.compose.maps.CabinNavSearchBar
import dev.decoupled.cabin.compose.maps.CabinNavSpeedLimit
import dev.decoupled.cabin.compose.maps.CabinNavWidget
import dev.decoupled.cabin.compose.maps.CabinParkingAvailability
import dev.decoupled.cabin.compose.maps.CabinPlaceCard
import dev.decoupled.cabin.compose.maps.CabinPoiChips
import dev.decoupled.cabin.compose.maps.CabinRouteCard
import dev.decoupled.cabin.compose.maps.CabinRouteOptions
import dev.decoupled.cabin.compose.maps.CabinTrafficIndicator
import dev.decoupled.cabin.compose.maps.CabinTripSummary
import dev.decoupled.cabin.compose.maps.CabinTurnInstruction
import dev.decoupled.cabin.compose.maps.CabinWaypointList
import dev.decoupled.cabin.compose.media.CabinAlbumArt
import dev.decoupled.cabin.compose.media.CabinAudioVisualizer
import dev.decoupled.cabin.compose.media.CabinAudioZoneSelector
import dev.decoupled.cabin.compose.media.CabinBrowseTree
import dev.decoupled.cabin.compose.media.CabinDockPlayer
import dev.decoupled.cabin.compose.media.CabinEqControls
import dev.decoupled.cabin.compose.media.CabinFadeBalancePanel
import dev.decoupled.cabin.compose.media.CabinHdMetadataCard
import dev.decoupled.cabin.compose.media.CabinLyricsView
import dev.decoupled.cabin.compose.media.CabinMediaSeekBar
import dev.decoupled.cabin.compose.media.CabinMiniPlayer
import dev.decoupled.cabin.compose.media.CabinPodcastControls
import dev.decoupled.cabin.compose.media.CabinQueueList
import dev.decoupled.cabin.compose.media.CabinRadioPresets
import dev.decoupled.cabin.compose.media.CabinRadioTuner
import dev.decoupled.cabin.compose.media.CabinRatingLike
import dev.decoupled.cabin.compose.media.CabinRearSeatMediaControls
import dev.decoupled.cabin.compose.media.CabinShuffleRepeat
import dev.decoupled.cabin.compose.media.CabinSourceSwitcher
import dev.decoupled.cabin.compose.media.CabinStationList
import dev.decoupled.cabin.compose.media.CabinTransportControls
import dev.decoupled.cabin.compose.media.CabinVideoPlayerChrome
import dev.decoupled.cabin.compose.media.CabinVolumePanel
import dev.decoupled.cabin.compose.navigation.CabinBackAffordance
import dev.decoupled.cabin.compose.navigation.CabinBottomBar
import dev.decoupled.cabin.compose.navigation.CabinBreadcrumbs
import dev.decoupled.cabin.compose.navigation.CabinCarousel
import dev.decoupled.cabin.compose.navigation.CabinDeepLinkHelper
import dev.decoupled.cabin.compose.navigation.CabinFocusArea
import dev.decoupled.cabin.compose.navigation.CabinHardwareBackHandler
import dev.decoupled.cabin.compose.navigation.CabinNavigationDock
import dev.decoupled.cabin.compose.navigation.CabinNavigationRail
import dev.decoupled.cabin.compose.navigation.CabinPageIndicator
import dev.decoupled.cabin.compose.navigation.CabinPager
import dev.decoupled.cabin.compose.navigation.CabinSideDrawer
import dev.decoupled.cabin.compose.navigation.CabinTabs
import dev.decoupled.cabin.compose.navigation.CabinTopBar
import dev.decoupled.cabin.compose.navigation.CabinWizardStepper
import dev.decoupled.cabin.compose.rse.CabinClusterCenterHandoff
import dev.decoupled.cabin.compose.rse.CabinCrossDisplayHandoff
import dev.decoupled.cabin.compose.rse.CabinDisplayLock
import dev.decoupled.cabin.compose.rse.CabinOccupantZoneIndicator
import dev.decoupled.cabin.compose.rse.CabinPassengerScaffold
import dev.decoupled.cabin.compose.rse.CabinRseHome
import dev.decoupled.cabin.compose.rse.CabinSharedContentPattern
import dev.decoupled.cabin.compose.rse.CabinZoneMedia
import dev.decoupled.cabin.compose.rse.CabinZoneVolume
import dev.decoupled.cabin.compose.selection.CabinCheckbox
import dev.decoupled.cabin.compose.selection.CabinChip
import dev.decoupled.cabin.compose.selection.CabinChipGroup
import dev.decoupled.cabin.compose.selection.CabinMultiPositionSelector
import dev.decoupled.cabin.compose.selection.CabinMultiSelectToolbar
import dev.decoupled.cabin.compose.selection.CabinRadio
import dev.decoupled.cabin.compose.selection.CabinRadioGroup
import dev.decoupled.cabin.compose.selection.CabinSelectionCard
import dev.decoupled.cabin.compose.selection.CabinSwitch
import dev.decoupled.cabin.compose.selection.CabinToggleTile
import dev.decoupled.cabin.compose.selection.CabinTriStateToggle
import dev.decoupled.cabin.compose.settings.CabinPreference
import dev.decoupled.cabin.compose.settings.CabinPreferenceScaffold
import dev.decoupled.cabin.compose.settings.CabinSettingsHomepage
import dev.decoupled.cabin.compose.settings.CabinSettingsSearch
import dev.decoupled.cabin.compose.settings.CabinSettingsTile
import dev.decoupled.cabin.compose.surface.CabinAccordion
import dev.decoupled.cabin.compose.surface.CabinBanner
import dev.decoupled.cabin.compose.surface.CabinBottomSheet
import dev.decoupled.cabin.compose.surface.CabinCard
import dev.decoupled.cabin.compose.surface.CabinDivider
import dev.decoupled.cabin.compose.surface.CabinExpandableContainer
import dev.decoupled.cabin.compose.surface.CabinModalDrawer
import dev.decoupled.cabin.compose.surface.CabinPagedScroll
import dev.decoupled.cabin.compose.surface.CabinPanel
import dev.decoupled.cabin.compose.surface.CabinPullFreeRefresh
import dev.decoupled.cabin.compose.surface.CabinScrimOverlay
import dev.decoupled.cabin.compose.surface.CabinScrollButtons
import dev.decoupled.cabin.compose.surface.CabinScrollPositionIndicator
import dev.decoupled.cabin.compose.surface.CabinSectionContainer
import dev.decoupled.cabin.compose.surface.CabinSideSheet
import dev.decoupled.cabin.compose.surface.CabinSpacer
import dev.decoupled.cabin.compose.surface.CabinStickyHeader
import dev.decoupled.cabin.compose.surface.CabinSurface
import dev.decoupled.cabin.compose.surface.CabinTile
import dev.decoupled.cabin.compose.systemui.CabinBrightnessUi
import dev.decoupled.cabin.compose.systemui.CabinCleanModeOverlay
import dev.decoupled.cabin.compose.systemui.CabinDisplayModeSwitcher
import dev.decoupled.cabin.compose.systemui.CabinEcallUi
import dev.decoupled.cabin.compose.systemui.CabinHeadsUpNotification
import dev.decoupled.cabin.compose.systemui.CabinImmersiveIndicator
import dev.decoupled.cabin.compose.systemui.CabinNotificationCenter
import dev.decoupled.cabin.compose.systemui.CabinPrivacyIndicators
import dev.decoupled.cabin.compose.systemui.CabinQuickSettings
import dev.decoupled.cabin.compose.systemui.CabinShutdownUi
import dev.decoupled.cabin.compose.systemui.CabinUpdateProgress
import dev.decoupled.cabin.compose.systemui.CabinVolumeUi
import dev.decoupled.cabin.compose.user.CabinAccountLinking
import dev.decoupled.cabin.compose.user.CabinBiometricPrompt
import dev.decoupled.cabin.compose.user.CabinChildLockNotice
import dev.decoupled.cabin.compose.user.CabinEulaConsent
import dev.decoupled.cabin.compose.user.CabinFactoryResetConfirm
import dev.decoupled.cabin.compose.user.CabinOnboardingWizard
import dev.decoupled.cabin.compose.user.CabinPinPatternPassword
import dev.decoupled.cabin.compose.user.CabinPrivacyNotice
import dev.decoupled.cabin.compose.user.CabinProfileCard
import dev.decoupled.cabin.compose.user.CabinProfileCreate
import dev.decoupled.cabin.compose.user.CabinProfileSwitcher
import dev.decoupled.cabin.compose.vehicle.CabinBattery12v
import dev.decoupled.cabin.compose.vehicle.CabinChargePortDoor
import dev.decoupled.cabin.compose.vehicle.CabinConfirmSafetyAction
import dev.decoupled.cabin.compose.vehicle.CabinConsumptionGraph
import dev.decoupled.cabin.compose.vehicle.CabinDigitalKey
import dev.decoupled.cabin.compose.vehicle.CabinDoorControl
import dev.decoupled.cabin.compose.vehicle.CabinDriveModeSelector
import dev.decoupled.cabin.compose.vehicle.CabinEcoScore
import dev.decoupled.cabin.compose.vehicle.CabinFluidLevels
import dev.decoupled.cabin.compose.vehicle.CabinGuestMode
import dev.decoupled.cabin.compose.vehicle.CabinLightControl
import dev.decoupled.cabin.compose.vehicle.CabinLockControl
import dev.decoupled.cabin.compose.vehicle.CabinMirrorControl
import dev.decoupled.cabin.compose.vehicle.CabinRegenSelector
import dev.decoupled.cabin.compose.vehicle.CabinSeatMemory
import dev.decoupled.cabin.compose.vehicle.CabinServiceReminder
import dev.decoupled.cabin.compose.vehicle.CabinSteeringFeel
import dev.decoupled.cabin.compose.vehicle.CabinSuspensionControl
import dev.decoupled.cabin.compose.vehicle.CabinTelltaleSet
import dev.decoupled.cabin.compose.vehicle.CabinTirePressure
import dev.decoupled.cabin.compose.vehicle.CabinTractionControl
import dev.decoupled.cabin.compose.vehicle.CabinTripComputer
import dev.decoupled.cabin.compose.vehicle.CabinTrunkControl
import dev.decoupled.cabin.compose.vehicle.CabinValetMode
import dev.decoupled.cabin.compose.vehicle.CabinVehicleModelView
import dev.decoupled.cabin.compose.vehicle.CabinVehicleOpenMap
import dev.decoupled.cabin.compose.vehicle.CabinWarningList
import dev.decoupled.cabin.compose.vehicle.CabinWindowControl
import dev.decoupled.cabin.compose.vehicle.CabinWiperControl
import dev.decoupled.cabin.compose.voice.CabinAssistantSurface
import dev.decoupled.cabin.compose.voice.CabinBargeInIndicator
import dev.decoupled.cabin.compose.voice.CabinInvocationButton
import dev.decoupled.cabin.compose.voice.CabinMicPrivacyIndicator
import dev.decoupled.cabin.compose.voice.CabinMultiZoneVoice
import dev.decoupled.cabin.compose.voice.CabinSuggestionChips
import dev.decoupled.cabin.compose.voice.CabinVoiceConfirmCancel
import dev.decoupled.cabin.compose.voice.CabinVoiceResultsCard
import dev.decoupled.cabin.compose.voice.CabinYouCanSayBar
import dev.decoupled.cabin.gauges.CabinArcGauge
import dev.decoupled.cabin.gauges.CabinClusterInfoPanel
import dev.decoupled.cabin.gauges.CabinClusterModeSwitcher
import dev.decoupled.cabin.gauges.CabinClusterThemeSet
import dev.decoupled.cabin.gauges.CabinDigitalSpeed
import dev.decoupled.cabin.gauges.CabinGForce
import dev.decoupled.cabin.gauges.CabinGearIndicator
import dev.decoupled.cabin.gauges.CabinHudPrimitive
import dev.decoupled.cabin.gauges.CabinLinearGauge
import dev.decoupled.cabin.gauges.CabinNeedle
import dev.decoupled.cabin.gauges.CabinPowerMeter
import dev.decoupled.cabin.gauges.CabinRadialGauge
import dev.decoupled.cabin.gauges.CabinSegmentedGauge
import dev.decoupled.cabin.gauges.CabinSpeedLimitIndicator
import dev.decoupled.cabin.gauges.CabinSpeedometer
import dev.decoupled.cabin.gauges.CabinTachometer
import dev.decoupled.cabin.gauges.CabinTelltaleStrip
import dev.decoupled.cabin.gauges.CabinTickScale
import dev.decoupled.cabin.gauges.CabinWarningOverlay

@Composable
fun CatalogComponentDemo(
    id: String,
    modifier: Modifier = Modifier,
) {
    when (id) {
        "button" -> CabinButton(modifier = modifier)
        "fab" -> CabinFab(modifier = modifier)
        "button-group" -> CabinButtonGroup(modifier = modifier)
        "toggle-button" -> CabinToggleButton(modifier = modifier)
        "segmented-button" -> CabinSegmentedButton(modifier = modifier)
        "split-button" -> CabinSplitButton(modifier = modifier)
        "long-press-button" -> CabinLongPressButton(modifier = modifier)
        "action-row" -> CabinActionRow(modifier = modifier)
        "hold-to-confirm" -> CabinHoldToConfirmButton(modifier = modifier)
        "safety-action" -> CabinSafetyActionButton(modifier = modifier)
        "press-hold-repeater" -> CabinPressHoldRepeater(modifier = modifier)
        "switch" -> CabinSwitch(modifier = modifier)
        "checkbox" -> CabinCheckbox(modifier = modifier)
        "radio" -> CabinRadio(modifier = modifier)
        "radio-group" -> CabinRadioGroup(modifier = modifier)
        "chip" -> CabinChip(modifier = modifier)
        "chip-group" -> CabinChipGroup(modifier = modifier)
        "selection-card" -> CabinSelectionCard(modifier = modifier)
        "multi-select-toolbar" -> CabinMultiSelectToolbar(modifier = modifier)
        "toggle-tile" -> CabinToggleTile(modifier = modifier)
        "tri-state-toggle" -> CabinTriStateToggle(modifier = modifier)
        "multi-position-selector" -> CabinMultiPositionSelector(modifier = modifier)
        "navigation-dock" -> CabinNavigationDock(modifier = modifier)
        "navigation-rail" -> CabinNavigationRail(modifier = modifier)
        "bottom-bar" -> CabinBottomBar(modifier = modifier)
        "side-drawer" -> CabinSideDrawer(modifier = modifier)
        "top-bar" -> CabinTopBar(modifier = modifier)
        "tabs" -> CabinTabs(modifier = modifier)
        "breadcrumbs" -> CabinBreadcrumbs(modifier = modifier)
        "back-affordance" -> CabinBackAffordance(modifier = modifier)
        "hardware-back-handler" -> CabinHardwareBackHandler(modifier = modifier)
        "page-indicator" -> CabinPageIndicator(modifier = modifier)
        "pager" -> CabinPager(modifier = modifier)
        "carousel" -> CabinCarousel(modifier = modifier)
        "wizard-stepper" -> CabinWizardStepper(modifier = modifier)
        "deep-link-helper" -> CabinDeepLinkHelper(modifier = modifier)
        "surface" -> CabinSurface(modifier = modifier)
        "card" -> CabinCard(modifier = modifier)
        "tile" -> CabinTile(modifier = modifier)
        "banner" -> CabinBanner(modifier = modifier)
        "panel" -> CabinPanel(modifier = modifier)
        "section-container" -> CabinSectionContainer(modifier = modifier)
        "expandable-container" -> CabinExpandableContainer(modifier = modifier)
        "accordion" -> CabinAccordion(modifier = modifier)
        "divider" -> CabinDivider(modifier = modifier)
        "spacer" -> CabinSpacer(modifier = modifier)
        "scrim-overlay" -> CabinScrimOverlay(modifier = modifier)
        "paged-scroll" -> CabinPagedScroll(modifier = modifier)
        "scroll-position-indicator" -> CabinScrollPositionIndicator(modifier = modifier)
        "scroll-buttons" -> CabinScrollButtons(modifier = modifier)
        "sticky-header" -> CabinStickyHeader(modifier = modifier)
        "pull-free-refresh" -> CabinPullFreeRefresh(modifier = modifier)
        "bottom-sheet" -> CabinBottomSheet(modifier = modifier)
        "side-sheet" -> CabinSideSheet(modifier = modifier)
        "modal-drawer" -> CabinModalDrawer(modifier = modifier)
        "list-item" -> CabinListItem(modifier = modifier)
        "paged-list" -> CabinPagedList(modifier = modifier)
        "sectioned-list" -> CabinSectionedList(modifier = modifier)
        "grouped-list" -> CabinGroupedList(modifier = modifier)
        "jump-list" -> CabinJumpList(modifier = modifier)
        "app-grid" -> CabinAppGrid(modifier = modifier)
        "media-grid" -> CabinMediaGrid(modifier = modifier)
        "adaptive-grid" -> CabinAdaptiveGrid(modifier = modifier)
        "data-table" -> CabinDataTable(modifier = modifier)
        "key-value-row" -> CabinKeyValueRow(modifier = modifier)
        "info-row" -> CabinInfoRow(modifier = modifier)
        "description-list" -> CabinDescriptionList(modifier = modifier)
        "timeline-list" -> CabinTimelineList(modifier = modifier)
        "filter-bar" -> CabinFilterBar(modifier = modifier)
        "sort-control" -> CabinSortControl(modifier = modifier)
        "search-result-list" -> CabinSearchResultList(modifier = modifier)
        "empty-state" -> CabinEmptyState(modifier = modifier)
        "error-state" -> CabinErrorState(modifier = modifier)
        "loading-collection" -> CabinLoadingCollection(modifier = modifier)
        "text-field" -> CabinTextField(modifier = modifier)
        "search-field" -> CabinSearchField(modifier = modifier)
        "pin-field" -> CabinPinField(modifier = modifier)
        "otp-field" -> CabinOtpField(modifier = modifier)
        "multiline-text" -> CabinMultilineText(modifier = modifier)
        "autocomplete" -> CabinAutocomplete(modifier = modifier)
        "masked-input" -> CabinMaskedInput(modifier = modifier)
        "numeric-keypad" -> CabinNumericKeypad(modifier = modifier)
        "dial-pad" -> CabinDialPad(modifier = modifier)
        "rotary-text-entry" -> CabinRotaryTextEntry(modifier = modifier)
        "slider" -> CabinSlider(modifier = modifier)
        "range-slider" -> CabinRangeSlider(modifier = modifier)
        "stepper" -> CabinStepper(modifier = modifier)
        "number-picker" -> CabinNumberPicker(modifier = modifier)
        "dial-knob" -> CabinDialKnob(modifier = modifier)
        "date-picker" -> CabinDatePicker(modifier = modifier)
        "time-picker" -> CabinTimePicker(modifier = modifier)
        "duration-picker" -> CabinDurationPicker(modifier = modifier)
        "dropdown" -> CabinDropdown(modifier = modifier)
        "list-picker" -> CabinListPicker(modifier = modifier)
        "color-picker" -> CabinColorPicker(modifier = modifier)
        "wheel-picker" -> CabinWheelPicker(modifier = modifier)
        "option-picker" -> CabinOptionPicker(modifier = modifier)
        "voice-mic-button" -> CabinVoiceMicButton(modifier = modifier)
        "listening-indicator" -> CabinListeningIndicator(modifier = modifier)
        "transcription-field" -> CabinTranscriptionField(modifier = modifier)
        "rating-input" -> CabinRatingInput(modifier = modifier)
        "gesture-pad" -> CabinGesturePad(modifier = modifier)
        "dialog" -> CabinDialog(modifier = modifier)
        "snackbar" -> CabinSnackbar(modifier = modifier)
        "toast" -> CabinToast(modifier = modifier)
        "inline-message" -> CabinInlineMessage(modifier = modifier)
        "callout" -> CabinCallout(modifier = modifier)
        "linear-progress" -> CabinLinearProgress(modifier = modifier)
        "circular-progress" -> CabinCircularProgress(modifier = modifier)
        "skeleton" -> CabinSkeleton(modifier = modifier)
        "step-progress" -> CabinStepProgress(modifier = modifier)
        "context-menu" -> CabinContextMenu(modifier = modifier)
        "tooltip" -> CabinTooltip(modifier = modifier)
        "offline-state" -> CabinOfflineState(modifier = modifier)
        "no-permission-state" -> CabinNoPermissionState(modifier = modifier)
        "driving-restricted-state" -> CabinDrivingRestrictedState(modifier = modifier)
        "badge" -> CabinBadge(modifier = modifier)
        "status-pill" -> CabinStatusPill(modifier = modifier)
        "tag" -> CabinTag(modifier = modifier)
        "splash-screen" -> CabinSplashScreen(modifier = modifier)
        "preference" -> CabinPreference(modifier = modifier)
        "preference-scaffold" -> CabinPreferenceScaffold(modifier = modifier)
        "settings-search" -> CabinSettingsSearch(modifier = modifier)
        "settings-tile" -> CabinSettingsTile(modifier = modifier)
        "settings-homepage" -> CabinSettingsHomepage(modifier = modifier)
        "mini-player" -> CabinMiniPlayer(modifier = modifier)
        "dock-player" -> CabinDockPlayer(modifier = modifier)
        "transport-controls" -> CabinTransportControls(modifier = modifier)
        "media-seek-bar" -> CabinMediaSeekBar(modifier = modifier)
        "shuffle-repeat" -> CabinShuffleRepeat(modifier = modifier)
        "rating-like" -> CabinRatingLike(modifier = modifier)
        "album-art" -> CabinAlbumArt(modifier = modifier)
        "queue-list" -> CabinQueueList(modifier = modifier)
        "browse-tree" -> CabinBrowseTree(modifier = modifier)
        "source-switcher" -> CabinSourceSwitcher(modifier = modifier)
        "audio-zone-selector" -> CabinAudioZoneSelector(modifier = modifier)
        "volume-panel" -> CabinVolumePanel(modifier = modifier)
        "fade-balance-panel" -> CabinFadeBalancePanel(modifier = modifier)
        "eq-controls" -> CabinEqControls(modifier = modifier)
        "audio-visualizer" -> CabinAudioVisualizer(modifier = modifier)
        "radio-tuner" -> CabinRadioTuner(modifier = modifier)
        "radio-presets" -> CabinRadioPresets(modifier = modifier)
        "station-list" -> CabinStationList(modifier = modifier)
        "hd-metadata-card" -> CabinHdMetadataCard(modifier = modifier)
        "podcast-controls" -> CabinPodcastControls(modifier = modifier)
        "lyrics-view" -> CabinLyricsView(modifier = modifier)
        "video-player-chrome" -> CabinVideoPlayerChrome(modifier = modifier)
        "rear-seat-media-controls" -> CabinRearSeatMediaControls(modifier = modifier)
        "temperature-control" -> CabinTemperatureControl(modifier = modifier)
        "fan-speed" -> CabinFanSpeed(modifier = modifier)
        "airflow-direction" -> CabinAirflowDirection(modifier = modifier)
        "zone-selector" -> CabinZoneSelector(modifier = modifier)
        "seat-climate" -> CabinSeatClimate(modifier = modifier)
        "steering-wheel-heat" -> CabinSteeringWheelHeat(modifier = modifier)
        "mirror-heat" -> CabinMirrorHeat(modifier = modifier)
        "defrost-toggle" -> CabinDefrostToggle(modifier = modifier)
        "recirc-toggle" -> CabinRecircToggle(modifier = modifier)
        "auto-ac-toggle" -> CabinAutoAcToggle(modifier = modifier)
        "eco-toggle" -> CabinEcoToggle(modifier = modifier)
        "air-quality-indicator" -> CabinAirQualityIndicator(modifier = modifier)
        "fragrance-control" -> CabinFragranceControl(modifier = modifier)
        "preconditioning-scheduler" -> CabinPreconditioningScheduler(modifier = modifier)
        "ambient-lighting" -> CabinAmbientLighting(modifier = modifier)
        "sunroof-control" -> CabinSunroofControl(modifier = modifier)
        "hvac-overlay" -> CabinHvacOverlay(modifier = modifier)
        "persistent-hvac-bar" -> CabinPersistentHvacBar(modifier = modifier)
        "door-control" -> CabinDoorControl(modifier = modifier)
        "window-control" -> CabinWindowControl(modifier = modifier)
        "trunk-control" -> CabinTrunkControl(modifier = modifier)
        "lock-control" -> CabinLockControl(modifier = modifier)
        "mirror-control" -> CabinMirrorControl(modifier = modifier)
        "seat-memory" -> CabinSeatMemory(modifier = modifier)
        "charge-port-door" -> CabinChargePortDoor(modifier = modifier)
        "wiper-control" -> CabinWiperControl(modifier = modifier)
        "light-control" -> CabinLightControl(modifier = modifier)
        "drive-mode-selector" -> CabinDriveModeSelector(modifier = modifier)
        "regen-selector" -> CabinRegenSelector(modifier = modifier)
        "suspension-control" -> CabinSuspensionControl(modifier = modifier)
        "steering-feel" -> CabinSteeringFeel(modifier = modifier)
        "traction-control" -> CabinTractionControl(modifier = modifier)
        "telltale-set" -> CabinTelltaleSet(modifier = modifier)
        "warning-list" -> CabinWarningList(modifier = modifier)
        "service-reminder" -> CabinServiceReminder(modifier = modifier)
        "vehicle-open-map" -> CabinVehicleOpenMap(modifier = modifier)
        "tire-pressure" -> CabinTirePressure(modifier = modifier)
        "fluid-levels" -> CabinFluidLevels(modifier = modifier)
        "battery-12v" -> CabinBattery12v(modifier = modifier)
        "trip-computer" -> CabinTripComputer(modifier = modifier)
        "consumption-graph" -> CabinConsumptionGraph(modifier = modifier)
        "eco-score" -> CabinEcoScore(modifier = modifier)
        "vehicle-model-view" -> CabinVehicleModelView(modifier = modifier)
        "confirm-safety-action" -> CabinConfirmSafetyAction(modifier = modifier)
        "digital-key" -> CabinDigitalKey(modifier = modifier)
        "valet-mode" -> CabinValetMode(modifier = modifier)
        "guest-mode" -> CabinGuestMode(modifier = modifier)
        "range-estimator" -> CabinRangeEstimator(modifier = modifier)
        "range-on-map" -> CabinRangeOnMap(modifier = modifier)
        "charge-session-card" -> CabinChargeSessionCard(modifier = modifier)
        "charge-limit-slider" -> CabinChargeLimitSlider(modifier = modifier)
        "charging-schedule" -> CabinChargingSchedule(modifier = modifier)
        "charge-port-status" -> CabinChargePortStatus(modifier = modifier)
        "charging-station-card" -> CabinChargingStationCard(modifier = modifier)
        "connector-type-chip" -> CabinConnectorTypeChip(modifier = modifier)
        "energy-flow-diagram" -> CabinEnergyFlowDiagram(modifier = modifier)
        "consumption-chart" -> CabinConsumptionChart(modifier = modifier)
        "regen-indicator" -> CabinRegenIndicator(modifier = modifier)
        "charge-precondition" -> CabinChargePrecondition(modifier = modifier)
        "trip-charge-planner" -> CabinTripChargePlanner(modifier = modifier)
        "v2x-controls" -> CabinV2xControls(modifier = modifier)
        "fuel-level" -> CabinFuelLevel(modifier = modifier)
        "hybrid-range-breakdown" -> CabinHybridRangeBreakdown(modifier = modifier)
        "map-container" -> CabinMapContainer(modifier = modifier)
        "map-controls" -> CabinMapControls(modifier = modifier)
        "route-card" -> CabinRouteCard(modifier = modifier)
        "eta-panel" -> CabinEtaPanel(modifier = modifier)
        "turn-instruction" -> CabinTurnInstruction(modifier = modifier)
        "lane-guidance" -> CabinLaneGuidance(modifier = modifier)
        "junction-view" -> CabinJunctionView(modifier = modifier)
        "speed-limit" -> CabinNavSpeedLimit(modifier = modifier)
        "nav-search-bar" -> CabinNavSearchBar(modifier = modifier)
        "place-card" -> CabinPlaceCard(modifier = modifier)
        "favorites-list" -> CabinFavoritesList(modifier = modifier)
        "poi-chips" -> CabinPoiChips(modifier = modifier)
        "arrival-panel" -> CabinArrivalPanel(modifier = modifier)
        "route-options" -> CabinRouteOptions(modifier = modifier)
        "traffic-indicator" -> CabinTrafficIndicator(modifier = modifier)
        "waypoint-list" -> CabinWaypointList(modifier = modifier)
        "trip-summary" -> CabinTripSummary(modifier = modifier)
        "parking-availability" -> CabinParkingAvailability(modifier = modifier)
        "nav-widget" -> CabinNavWidget(modifier = modifier)
        "dialer" -> CabinDialer(modifier = modifier)
        "in-call-screen" -> CabinInCallScreen(modifier = modifier)
        "incoming-call-hud" -> CabinIncomingCallHud(modifier = modifier)
        "call-controls" -> CabinCallControls(modifier = modifier)
        "contact-list" -> CabinContactList(modifier = modifier)
        "contact-card" -> CabinContactCard(modifier = modifier)
        "conversation-list" -> CabinConversationList(modifier = modifier)
        "message-bubble" -> CabinMessageBubble(modifier = modifier)
        "voice-reply" -> CabinVoiceReply(modifier = modifier)
        "canned-reply-chips" -> CabinCannedReplyChips(modifier = modifier)
        "bluetooth-pairing" -> CabinBluetoothPairing(modifier = modifier)
        "device-list" -> CabinDeviceList(modifier = modifier)
        "projection-status" -> CabinProjectionStatus(modifier = modifier)
        "voicemail" -> CabinVoicemail(modifier = modifier)
        "assistant-surface" -> CabinAssistantSurface(modifier = modifier)
        "invocation-button" -> CabinInvocationButton(modifier = modifier)
        "mic-privacy-indicator" -> CabinMicPrivacyIndicator(modifier = modifier)
        "suggestion-chips" -> CabinSuggestionChips(modifier = modifier)
        "you-can-say-bar" -> CabinYouCanSayBar(modifier = modifier)
        "voice-results-card" -> CabinVoiceResultsCard(modifier = modifier)
        "confirm-cancel" -> CabinVoiceConfirmCancel(modifier = modifier)
        "barge-in-indicator" -> CabinBargeInIndicator(modifier = modifier)
        "multi-zone-voice" -> CabinMultiZoneVoice(modifier = modifier)
        "launcher-scaffold" -> CabinLauncherScaffold(modifier = modifier)
        "app-icon" -> CabinAppIcon(modifier = modifier)
        "recents-switcher" -> CabinRecentsSwitcher(modifier = modifier)
        "widget-host" -> CabinWidgetHost(modifier = modifier)
        "widget-picker" -> CabinWidgetPicker(modifier = modifier)
        "widget-edit-mode" -> CabinWidgetEditMode(modifier = modifier)
        "suggestion-card" -> CabinSuggestionCard(modifier = modifier)
        "shortcut-tile" -> CabinShortcutTile(modifier = modifier)
        "home-template" -> CabinHomeTemplate(modifier = modifier)
        "screensaver" -> CabinScreensaver(modifier = modifier)
        "welcome-screen" -> CabinWelcomeScreen(modifier = modifier)
        "wallpaper-picker" -> CabinWallpaperPicker(modifier = modifier)
        "quick-settings" -> CabinQuickSettings(modifier = modifier)
        "notification-center" -> CabinNotificationCenter(modifier = modifier)
        "heads-up-notification" -> CabinHeadsUpNotification(modifier = modifier)
        "volume-ui" -> CabinVolumeUi(modifier = modifier)
        "brightness-ui" -> CabinBrightnessUi(modifier = modifier)
        "clean-mode-overlay" -> CabinCleanModeOverlay(modifier = modifier)
        "shutdown-ui" -> CabinShutdownUi(modifier = modifier)
        "update-progress" -> CabinUpdateProgress(modifier = modifier)
        "display-mode-switcher" -> CabinDisplayModeSwitcher(modifier = modifier)
        "immersive-indicator" -> CabinImmersiveIndicator(modifier = modifier)
        "privacy-indicators" -> CabinPrivacyIndicators(modifier = modifier)
        "ecall-ui" -> CabinEcallUi(modifier = modifier)
        "profile-switcher" -> CabinProfileSwitcher(modifier = modifier)
        "profile-card" -> CabinProfileCard(modifier = modifier)
        "profile-create" -> CabinProfileCreate(modifier = modifier)
        "pin-pattern-password" -> CabinPinPatternPassword(modifier = modifier)
        "biometric-prompt" -> CabinBiometricPrompt(modifier = modifier)
        "onboarding-wizard" -> CabinOnboardingWizard(modifier = modifier)
        "eula-consent" -> CabinEulaConsent(modifier = modifier)
        "account-linking" -> CabinAccountLinking(modifier = modifier)
        "privacy-notice" -> CabinPrivacyNotice(modifier = modifier)
        "child-lock-notice" -> CabinChildLockNotice(modifier = modifier)
        "factory-reset-confirm" -> CabinFactoryResetConfirm(modifier = modifier)
        "passenger-scaffold" -> CabinPassengerScaffold(modifier = modifier)
        "rse-home" -> CabinRseHome(modifier = modifier)
        "zone-media" -> CabinZoneMedia(modifier = modifier)
        "zone-volume" -> CabinZoneVolume(modifier = modifier)
        "cross-display-handoff" -> CabinCrossDisplayHandoff(modifier = modifier)
        "occupant-zone-indicator" -> CabinOccupantZoneIndicator(modifier = modifier)
        "display-lock" -> CabinDisplayLock(modifier = modifier)
        "shared-content-pattern" -> CabinSharedContentPattern(modifier = modifier)
        "cluster-center-handoff" -> CabinClusterCenterHandoff(modifier = modifier)
        "camera-view" -> CabinCameraView(modifier = modifier)
        "view-selector" -> CabinViewSelector(modifier = modifier)
        "hitch-view" -> CabinHitchView(modifier = modifier)
        "parking-sensor" -> CabinParkingSensor(modifier = modifier)
        "auto-park-flow" -> CabinAutoParkFlow(modifier = modifier)
        "adas-status-chip" -> CabinAdasStatusChip(modifier = modifier)
        "driver-attention" -> CabinDriverAttention(modifier = modifier)
        "acc-controls" -> CabinAccControls(modifier = modifier)
        "blind-spot-alert" -> CabinBlindSpotAlert(modifier = modifier)
        "dashcam-controls" -> CabinDashcamControls(modifier = modifier)
        "recordings-list" -> CabinRecordingsList(modifier = modifier)
        "pane-divider" -> CabinPaneDivider(modifier = modifier)
        "collapsible-side-panel" -> CabinCollapsibleSidePanel(modifier = modifier)
        "adaptive-container" -> CabinAdaptiveContainer(modifier = modifier)
        "radial-gauge" -> CabinRadialGauge(modifier = modifier)
        "linear-gauge" -> CabinLinearGauge(modifier = modifier)
        "arc-gauge" -> CabinArcGauge(modifier = modifier)
        "segmented-gauge" -> CabinSegmentedGauge(modifier = modifier)
        "speedometer" -> CabinSpeedometer(modifier = modifier)
        "tachometer" -> CabinTachometer(modifier = modifier)
        "power-meter" -> CabinPowerMeter(modifier = modifier)
        "g-force" -> CabinGForce(modifier = modifier)
        "needle" -> CabinNeedle(modifier = modifier)
        "tick-scale" -> CabinTickScale(modifier = modifier)
        "gear-indicator" -> CabinGearIndicator(modifier = modifier)
        "digital-speed" -> CabinDigitalSpeed(modifier = modifier)
        "speed-limit-indicator" -> CabinSpeedLimitIndicator(modifier = modifier)
        "telltale-strip" -> CabinTelltaleStrip(modifier = modifier)
        "warning-overlay" -> CabinWarningOverlay(modifier = modifier)
        "cluster-info-panel" -> CabinClusterInfoPanel(modifier = modifier)
        "cluster-mode-switcher" -> CabinClusterModeSwitcher(modifier = modifier)
        "hud-primitive" -> CabinHudPrimitive(modifier = modifier)
        "cluster-theme-set" -> CabinClusterThemeSet(modifier = modifier)
        "focus-area" -> CabinFocusArea(modifier = modifier)
        else -> {}
    }
}
