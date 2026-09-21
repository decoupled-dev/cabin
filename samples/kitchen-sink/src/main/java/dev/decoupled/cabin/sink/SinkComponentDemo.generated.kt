@file:OptIn(dev.decoupled.cabin.foundation.CabinScaffold::class)

package dev.decoupled.cabin.sink

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
import dev.decoupled.cabin.foundation.CabinComponentUiState
import dev.decoupled.cabin.foundation.components.action.CabinActionRowState
import dev.decoupled.cabin.foundation.components.action.CabinButtonGroupState
import dev.decoupled.cabin.foundation.components.action.CabinButtonState
import dev.decoupled.cabin.foundation.components.action.CabinFabState
import dev.decoupled.cabin.foundation.components.action.CabinHoldToConfirmButtonState
import dev.decoupled.cabin.foundation.components.action.CabinLongPressButtonState
import dev.decoupled.cabin.foundation.components.action.CabinPressHoldRepeaterState
import dev.decoupled.cabin.foundation.components.action.CabinSafetyActionButtonState
import dev.decoupled.cabin.foundation.components.action.CabinSegmentedButtonState
import dev.decoupled.cabin.foundation.components.action.CabinSplitButtonState
import dev.decoupled.cabin.foundation.components.action.CabinToggleButtonState
import dev.decoupled.cabin.foundation.components.adas.CabinAccControlsState
import dev.decoupled.cabin.foundation.components.adas.CabinAdasStatusChipState
import dev.decoupled.cabin.foundation.components.adas.CabinAutoParkFlowState
import dev.decoupled.cabin.foundation.components.adas.CabinBlindSpotAlertState
import dev.decoupled.cabin.foundation.components.adas.CabinCameraViewState
import dev.decoupled.cabin.foundation.components.adas.CabinDashcamControlsState
import dev.decoupled.cabin.foundation.components.adas.CabinDriverAttentionState
import dev.decoupled.cabin.foundation.components.adas.CabinHitchViewState
import dev.decoupled.cabin.foundation.components.adas.CabinParkingSensorState
import dev.decoupled.cabin.foundation.components.adas.CabinRecordingsListState
import dev.decoupled.cabin.foundation.components.adas.CabinViewSelectorState
import dev.decoupled.cabin.foundation.components.collection.CabinAdaptiveGridState
import dev.decoupled.cabin.foundation.components.collection.CabinAppGridState
import dev.decoupled.cabin.foundation.components.collection.CabinDataTableState
import dev.decoupled.cabin.foundation.components.collection.CabinDescriptionListState
import dev.decoupled.cabin.foundation.components.collection.CabinEmptyStateState
import dev.decoupled.cabin.foundation.components.collection.CabinErrorStateState
import dev.decoupled.cabin.foundation.components.collection.CabinFilterBarState
import dev.decoupled.cabin.foundation.components.collection.CabinGroupedListState
import dev.decoupled.cabin.foundation.components.collection.CabinInfoRowState
import dev.decoupled.cabin.foundation.components.collection.CabinJumpListState
import dev.decoupled.cabin.foundation.components.collection.CabinKeyValueRowState
import dev.decoupled.cabin.foundation.components.collection.CabinListItemState
import dev.decoupled.cabin.foundation.components.collection.CabinLoadingCollectionState
import dev.decoupled.cabin.foundation.components.collection.CabinMediaGridState
import dev.decoupled.cabin.foundation.components.collection.CabinPagedListState
import dev.decoupled.cabin.foundation.components.collection.CabinSearchResultListState
import dev.decoupled.cabin.foundation.components.collection.CabinSectionedListState
import dev.decoupled.cabin.foundation.components.collection.CabinSortControlState
import dev.decoupled.cabin.foundation.components.collection.CabinTimelineListState
import dev.decoupled.cabin.foundation.components.comms.CabinBluetoothPairingState
import dev.decoupled.cabin.foundation.components.comms.CabinCallControlsState
import dev.decoupled.cabin.foundation.components.comms.CabinCannedReplyChipsState
import dev.decoupled.cabin.foundation.components.comms.CabinContactCardState
import dev.decoupled.cabin.foundation.components.comms.CabinContactListState
import dev.decoupled.cabin.foundation.components.comms.CabinConversationListState
import dev.decoupled.cabin.foundation.components.comms.CabinDeviceListState
import dev.decoupled.cabin.foundation.components.comms.CabinDialerState
import dev.decoupled.cabin.foundation.components.comms.CabinInCallScreenState
import dev.decoupled.cabin.foundation.components.comms.CabinIncomingCallHudState
import dev.decoupled.cabin.foundation.components.comms.CabinMessageBubbleState
import dev.decoupled.cabin.foundation.components.comms.CabinProjectionStatusState
import dev.decoupled.cabin.foundation.components.comms.CabinVoiceReplyState
import dev.decoupled.cabin.foundation.components.comms.CabinVoicemailState
import dev.decoupled.cabin.foundation.components.ev.CabinChargeLimitSliderState
import dev.decoupled.cabin.foundation.components.ev.CabinChargePortStatusState
import dev.decoupled.cabin.foundation.components.ev.CabinChargePreconditionState
import dev.decoupled.cabin.foundation.components.ev.CabinChargeSessionCardState
import dev.decoupled.cabin.foundation.components.ev.CabinChargingScheduleState
import dev.decoupled.cabin.foundation.components.ev.CabinChargingStationCardState
import dev.decoupled.cabin.foundation.components.ev.CabinConnectorTypeChipState
import dev.decoupled.cabin.foundation.components.ev.CabinConsumptionChartState
import dev.decoupled.cabin.foundation.components.ev.CabinEnergyFlowDiagramState
import dev.decoupled.cabin.foundation.components.ev.CabinFuelLevelState
import dev.decoupled.cabin.foundation.components.ev.CabinHybridRangeBreakdownState
import dev.decoupled.cabin.foundation.components.ev.CabinRangeEstimatorState
import dev.decoupled.cabin.foundation.components.ev.CabinRangeOnMapState
import dev.decoupled.cabin.foundation.components.ev.CabinRegenIndicatorState
import dev.decoupled.cabin.foundation.components.ev.CabinTripChargePlannerState
import dev.decoupled.cabin.foundation.components.ev.CabinV2xControlsState
import dev.decoupled.cabin.foundation.components.feedback.CabinBadgeState
import dev.decoupled.cabin.foundation.components.feedback.CabinCalloutState
import dev.decoupled.cabin.foundation.components.feedback.CabinCircularProgressState
import dev.decoupled.cabin.foundation.components.feedback.CabinContextMenuState
import dev.decoupled.cabin.foundation.components.feedback.CabinDialogState
import dev.decoupled.cabin.foundation.components.feedback.CabinDrivingRestrictedStateState
import dev.decoupled.cabin.foundation.components.feedback.CabinInlineMessageState
import dev.decoupled.cabin.foundation.components.feedback.CabinLinearProgressState
import dev.decoupled.cabin.foundation.components.feedback.CabinNoPermissionStateState
import dev.decoupled.cabin.foundation.components.feedback.CabinOfflineStateState
import dev.decoupled.cabin.foundation.components.feedback.CabinSkeletonState
import dev.decoupled.cabin.foundation.components.feedback.CabinSnackbarState
import dev.decoupled.cabin.foundation.components.feedback.CabinSplashScreenState
import dev.decoupled.cabin.foundation.components.feedback.CabinStatusPillState
import dev.decoupled.cabin.foundation.components.feedback.CabinStepProgressState
import dev.decoupled.cabin.foundation.components.feedback.CabinTagState
import dev.decoupled.cabin.foundation.components.feedback.CabinToastState
import dev.decoupled.cabin.foundation.components.feedback.CabinTooltipState
import dev.decoupled.cabin.foundation.components.gauges.CabinArcGaugeState
import dev.decoupled.cabin.foundation.components.gauges.CabinClusterInfoPanelState
import dev.decoupled.cabin.foundation.components.gauges.CabinClusterModeSwitcherState
import dev.decoupled.cabin.foundation.components.gauges.CabinClusterThemeSetState
import dev.decoupled.cabin.foundation.components.gauges.CabinDigitalSpeedState
import dev.decoupled.cabin.foundation.components.gauges.CabinGForceState
import dev.decoupled.cabin.foundation.components.gauges.CabinGearIndicatorState
import dev.decoupled.cabin.foundation.components.gauges.CabinHudPrimitiveState
import dev.decoupled.cabin.foundation.components.gauges.CabinLinearGaugeState
import dev.decoupled.cabin.foundation.components.gauges.CabinNeedleState
import dev.decoupled.cabin.foundation.components.gauges.CabinPowerMeterState
import dev.decoupled.cabin.foundation.components.gauges.CabinRadialGaugeState
import dev.decoupled.cabin.foundation.components.gauges.CabinSegmentedGaugeState
import dev.decoupled.cabin.foundation.components.gauges.CabinSpeedLimitIndicatorState
import dev.decoupled.cabin.foundation.components.gauges.CabinSpeedometerState
import dev.decoupled.cabin.foundation.components.gauges.CabinTachometerState
import dev.decoupled.cabin.foundation.components.gauges.CabinTelltaleStripState
import dev.decoupled.cabin.foundation.components.gauges.CabinTickScaleState
import dev.decoupled.cabin.foundation.components.gauges.CabinWarningOverlayState
import dev.decoupled.cabin.foundation.components.hvac.CabinAirQualityIndicatorState
import dev.decoupled.cabin.foundation.components.hvac.CabinAirflowDirectionState
import dev.decoupled.cabin.foundation.components.hvac.CabinAmbientLightingState
import dev.decoupled.cabin.foundation.components.hvac.CabinAutoAcToggleState
import dev.decoupled.cabin.foundation.components.hvac.CabinDefrostToggleState
import dev.decoupled.cabin.foundation.components.hvac.CabinEcoToggleState
import dev.decoupled.cabin.foundation.components.hvac.CabinFanSpeedState
import dev.decoupled.cabin.foundation.components.hvac.CabinFragranceControlState
import dev.decoupled.cabin.foundation.components.hvac.CabinHvacOverlayState
import dev.decoupled.cabin.foundation.components.hvac.CabinMirrorHeatState
import dev.decoupled.cabin.foundation.components.hvac.CabinPersistentHvacBarState
import dev.decoupled.cabin.foundation.components.hvac.CabinPreconditioningSchedulerState
import dev.decoupled.cabin.foundation.components.hvac.CabinRecircToggleState
import dev.decoupled.cabin.foundation.components.hvac.CabinSeatClimateState
import dev.decoupled.cabin.foundation.components.hvac.CabinSteeringWheelHeatState
import dev.decoupled.cabin.foundation.components.hvac.CabinSunroofControlState
import dev.decoupled.cabin.foundation.components.hvac.CabinTemperatureControlState
import dev.decoupled.cabin.foundation.components.hvac.CabinZoneSelectorState
import dev.decoupled.cabin.foundation.components.input.CabinAutocompleteState
import dev.decoupled.cabin.foundation.components.input.CabinColorPickerState
import dev.decoupled.cabin.foundation.components.input.CabinDatePickerState
import dev.decoupled.cabin.foundation.components.input.CabinDialKnobState
import dev.decoupled.cabin.foundation.components.input.CabinDialPadState
import dev.decoupled.cabin.foundation.components.input.CabinDropdownState
import dev.decoupled.cabin.foundation.components.input.CabinDurationPickerState
import dev.decoupled.cabin.foundation.components.input.CabinGesturePadState
import dev.decoupled.cabin.foundation.components.input.CabinListPickerState
import dev.decoupled.cabin.foundation.components.input.CabinListeningIndicatorState
import dev.decoupled.cabin.foundation.components.input.CabinMaskedInputState
import dev.decoupled.cabin.foundation.components.input.CabinMultilineTextState
import dev.decoupled.cabin.foundation.components.input.CabinNumberPickerState
import dev.decoupled.cabin.foundation.components.input.CabinNumericKeypadState
import dev.decoupled.cabin.foundation.components.input.CabinOptionPickerState
import dev.decoupled.cabin.foundation.components.input.CabinOtpFieldState
import dev.decoupled.cabin.foundation.components.input.CabinPinFieldState
import dev.decoupled.cabin.foundation.components.input.CabinRangeSliderState
import dev.decoupled.cabin.foundation.components.input.CabinRatingInputState
import dev.decoupled.cabin.foundation.components.input.CabinRotaryTextEntryState
import dev.decoupled.cabin.foundation.components.input.CabinSearchFieldState
import dev.decoupled.cabin.foundation.components.input.CabinSliderState
import dev.decoupled.cabin.foundation.components.input.CabinStepperState
import dev.decoupled.cabin.foundation.components.input.CabinTextFieldState
import dev.decoupled.cabin.foundation.components.input.CabinTimePickerState
import dev.decoupled.cabin.foundation.components.input.CabinTranscriptionFieldState
import dev.decoupled.cabin.foundation.components.input.CabinVoiceMicButtonState
import dev.decoupled.cabin.foundation.components.input.CabinWheelPickerState
import dev.decoupled.cabin.foundation.components.launcher.CabinAppIconState
import dev.decoupled.cabin.foundation.components.launcher.CabinHomeTemplateState
import dev.decoupled.cabin.foundation.components.launcher.CabinLauncherScaffoldState
import dev.decoupled.cabin.foundation.components.launcher.CabinRecentsSwitcherState
import dev.decoupled.cabin.foundation.components.launcher.CabinScreensaverState
import dev.decoupled.cabin.foundation.components.launcher.CabinShortcutTileState
import dev.decoupled.cabin.foundation.components.launcher.CabinSuggestionCardState
import dev.decoupled.cabin.foundation.components.launcher.CabinWallpaperPickerState
import dev.decoupled.cabin.foundation.components.launcher.CabinWelcomeScreenState
import dev.decoupled.cabin.foundation.components.launcher.CabinWidgetEditModeState
import dev.decoupled.cabin.foundation.components.launcher.CabinWidgetHostState
import dev.decoupled.cabin.foundation.components.launcher.CabinWidgetPickerState
import dev.decoupled.cabin.foundation.components.layout.CabinAdaptiveContainerState
import dev.decoupled.cabin.foundation.components.layout.CabinCollapsibleSidePanelState
import dev.decoupled.cabin.foundation.components.layout.CabinPaneDividerState
import dev.decoupled.cabin.foundation.components.maps.CabinArrivalPanelState
import dev.decoupled.cabin.foundation.components.maps.CabinEtaPanelState
import dev.decoupled.cabin.foundation.components.maps.CabinFavoritesListState
import dev.decoupled.cabin.foundation.components.maps.CabinJunctionViewState
import dev.decoupled.cabin.foundation.components.maps.CabinLaneGuidanceState
import dev.decoupled.cabin.foundation.components.maps.CabinMapContainerState
import dev.decoupled.cabin.foundation.components.maps.CabinMapControlsState
import dev.decoupled.cabin.foundation.components.maps.CabinNavSearchBarState
import dev.decoupled.cabin.foundation.components.maps.CabinNavSpeedLimitState
import dev.decoupled.cabin.foundation.components.maps.CabinNavWidgetState
import dev.decoupled.cabin.foundation.components.maps.CabinParkingAvailabilityState
import dev.decoupled.cabin.foundation.components.maps.CabinPlaceCardState
import dev.decoupled.cabin.foundation.components.maps.CabinPoiChipsState
import dev.decoupled.cabin.foundation.components.maps.CabinRouteCardState
import dev.decoupled.cabin.foundation.components.maps.CabinRouteOptionsState
import dev.decoupled.cabin.foundation.components.maps.CabinTrafficIndicatorState
import dev.decoupled.cabin.foundation.components.maps.CabinTripSummaryState
import dev.decoupled.cabin.foundation.components.maps.CabinTurnInstructionState
import dev.decoupled.cabin.foundation.components.maps.CabinWaypointListState
import dev.decoupled.cabin.foundation.components.media.CabinAlbumArtState
import dev.decoupled.cabin.foundation.components.media.CabinAudioVisualizerState
import dev.decoupled.cabin.foundation.components.media.CabinAudioZoneSelectorState
import dev.decoupled.cabin.foundation.components.media.CabinBrowseTreeState
import dev.decoupled.cabin.foundation.components.media.CabinDockPlayerState
import dev.decoupled.cabin.foundation.components.media.CabinEqControlsState
import dev.decoupled.cabin.foundation.components.media.CabinFadeBalancePanelState
import dev.decoupled.cabin.foundation.components.media.CabinHdMetadataCardState
import dev.decoupled.cabin.foundation.components.media.CabinLyricsViewState
import dev.decoupled.cabin.foundation.components.media.CabinMediaSeekBarState
import dev.decoupled.cabin.foundation.components.media.CabinMiniPlayerState
import dev.decoupled.cabin.foundation.components.media.CabinPodcastControlsState
import dev.decoupled.cabin.foundation.components.media.CabinQueueListState
import dev.decoupled.cabin.foundation.components.media.CabinRadioPresetsState
import dev.decoupled.cabin.foundation.components.media.CabinRadioTunerState
import dev.decoupled.cabin.foundation.components.media.CabinRatingLikeState
import dev.decoupled.cabin.foundation.components.media.CabinRearSeatMediaControlsState
import dev.decoupled.cabin.foundation.components.media.CabinShuffleRepeatState
import dev.decoupled.cabin.foundation.components.media.CabinSourceSwitcherState
import dev.decoupled.cabin.foundation.components.media.CabinStationListState
import dev.decoupled.cabin.foundation.components.media.CabinTransportControlsState
import dev.decoupled.cabin.foundation.components.media.CabinVideoPlayerChromeState
import dev.decoupled.cabin.foundation.components.media.CabinVolumePanelState
import dev.decoupled.cabin.foundation.components.navigation.CabinBackAffordanceState
import dev.decoupled.cabin.foundation.components.navigation.CabinBottomBarState
import dev.decoupled.cabin.foundation.components.navigation.CabinBreadcrumbsState
import dev.decoupled.cabin.foundation.components.navigation.CabinCarouselState
import dev.decoupled.cabin.foundation.components.navigation.CabinDeepLinkHelperState
import dev.decoupled.cabin.foundation.components.navigation.CabinFocusAreaState
import dev.decoupled.cabin.foundation.components.navigation.CabinHardwareBackHandlerState
import dev.decoupled.cabin.foundation.components.navigation.CabinNavigationDockState
import dev.decoupled.cabin.foundation.components.navigation.CabinNavigationRailState
import dev.decoupled.cabin.foundation.components.navigation.CabinPageIndicatorState
import dev.decoupled.cabin.foundation.components.navigation.CabinPagerState
import dev.decoupled.cabin.foundation.components.navigation.CabinSideDrawerState
import dev.decoupled.cabin.foundation.components.navigation.CabinTabsState
import dev.decoupled.cabin.foundation.components.navigation.CabinTopBarState
import dev.decoupled.cabin.foundation.components.navigation.CabinWizardStepperState
import dev.decoupled.cabin.foundation.components.rse.CabinClusterCenterHandoffState
import dev.decoupled.cabin.foundation.components.rse.CabinCrossDisplayHandoffState
import dev.decoupled.cabin.foundation.components.rse.CabinDisplayLockState
import dev.decoupled.cabin.foundation.components.rse.CabinOccupantZoneIndicatorState
import dev.decoupled.cabin.foundation.components.rse.CabinPassengerScaffoldState
import dev.decoupled.cabin.foundation.components.rse.CabinRseHomeState
import dev.decoupled.cabin.foundation.components.rse.CabinSharedContentPatternState
import dev.decoupled.cabin.foundation.components.rse.CabinZoneMediaState
import dev.decoupled.cabin.foundation.components.rse.CabinZoneVolumeState
import dev.decoupled.cabin.foundation.components.selection.CabinCheckboxState
import dev.decoupled.cabin.foundation.components.selection.CabinChipGroupState
import dev.decoupled.cabin.foundation.components.selection.CabinChipState
import dev.decoupled.cabin.foundation.components.selection.CabinMultiPositionSelectorState
import dev.decoupled.cabin.foundation.components.selection.CabinMultiSelectToolbarState
import dev.decoupled.cabin.foundation.components.selection.CabinRadioGroupState
import dev.decoupled.cabin.foundation.components.selection.CabinRadioState
import dev.decoupled.cabin.foundation.components.selection.CabinSelectionCardState
import dev.decoupled.cabin.foundation.components.selection.CabinSwitchState
import dev.decoupled.cabin.foundation.components.selection.CabinToggleTileState
import dev.decoupled.cabin.foundation.components.selection.CabinTriStateToggleState
import dev.decoupled.cabin.foundation.components.settings.CabinPreferenceScaffoldState
import dev.decoupled.cabin.foundation.components.settings.CabinPreferenceState
import dev.decoupled.cabin.foundation.components.settings.CabinSettingsHomepageState
import dev.decoupled.cabin.foundation.components.settings.CabinSettingsSearchState
import dev.decoupled.cabin.foundation.components.settings.CabinSettingsTileState
import dev.decoupled.cabin.foundation.components.surface.CabinAccordionState
import dev.decoupled.cabin.foundation.components.surface.CabinBannerState
import dev.decoupled.cabin.foundation.components.surface.CabinBottomSheetState
import dev.decoupled.cabin.foundation.components.surface.CabinCardState
import dev.decoupled.cabin.foundation.components.surface.CabinDividerState
import dev.decoupled.cabin.foundation.components.surface.CabinExpandableContainerState
import dev.decoupled.cabin.foundation.components.surface.CabinModalDrawerState
import dev.decoupled.cabin.foundation.components.surface.CabinPagedScrollState
import dev.decoupled.cabin.foundation.components.surface.CabinPanelState
import dev.decoupled.cabin.foundation.components.surface.CabinPullFreeRefreshState
import dev.decoupled.cabin.foundation.components.surface.CabinScrimOverlayState
import dev.decoupled.cabin.foundation.components.surface.CabinScrollButtonsState
import dev.decoupled.cabin.foundation.components.surface.CabinScrollPositionIndicatorState
import dev.decoupled.cabin.foundation.components.surface.CabinSectionContainerState
import dev.decoupled.cabin.foundation.components.surface.CabinSideSheetState
import dev.decoupled.cabin.foundation.components.surface.CabinSpacerState
import dev.decoupled.cabin.foundation.components.surface.CabinStickyHeaderState
import dev.decoupled.cabin.foundation.components.surface.CabinSurfaceState
import dev.decoupled.cabin.foundation.components.surface.CabinTileState
import dev.decoupled.cabin.foundation.components.systemui.CabinBrightnessUiState
import dev.decoupled.cabin.foundation.components.systemui.CabinCleanModeOverlayState
import dev.decoupled.cabin.foundation.components.systemui.CabinDisplayModeSwitcherState
import dev.decoupled.cabin.foundation.components.systemui.CabinEcallUiState
import dev.decoupled.cabin.foundation.components.systemui.CabinHeadsUpNotificationState
import dev.decoupled.cabin.foundation.components.systemui.CabinImmersiveIndicatorState
import dev.decoupled.cabin.foundation.components.systemui.CabinNotificationCenterState
import dev.decoupled.cabin.foundation.components.systemui.CabinPrivacyIndicatorsState
import dev.decoupled.cabin.foundation.components.systemui.CabinQuickSettingsState
import dev.decoupled.cabin.foundation.components.systemui.CabinShutdownUiState
import dev.decoupled.cabin.foundation.components.systemui.CabinUpdateProgressState
import dev.decoupled.cabin.foundation.components.systemui.CabinVolumeUiState
import dev.decoupled.cabin.foundation.components.user.CabinAccountLinkingState
import dev.decoupled.cabin.foundation.components.user.CabinBiometricPromptState
import dev.decoupled.cabin.foundation.components.user.CabinChildLockNoticeState
import dev.decoupled.cabin.foundation.components.user.CabinEulaConsentState
import dev.decoupled.cabin.foundation.components.user.CabinFactoryResetConfirmState
import dev.decoupled.cabin.foundation.components.user.CabinOnboardingWizardState
import dev.decoupled.cabin.foundation.components.user.CabinPinPatternPasswordState
import dev.decoupled.cabin.foundation.components.user.CabinPrivacyNoticeState
import dev.decoupled.cabin.foundation.components.user.CabinProfileCardState
import dev.decoupled.cabin.foundation.components.user.CabinProfileCreateState
import dev.decoupled.cabin.foundation.components.user.CabinProfileSwitcherState
import dev.decoupled.cabin.foundation.components.vehicle.CabinBattery12vState
import dev.decoupled.cabin.foundation.components.vehicle.CabinChargePortDoorState
import dev.decoupled.cabin.foundation.components.vehicle.CabinConfirmSafetyActionState
import dev.decoupled.cabin.foundation.components.vehicle.CabinConsumptionGraphState
import dev.decoupled.cabin.foundation.components.vehicle.CabinDigitalKeyState
import dev.decoupled.cabin.foundation.components.vehicle.CabinDoorControlState
import dev.decoupled.cabin.foundation.components.vehicle.CabinDriveModeSelectorState
import dev.decoupled.cabin.foundation.components.vehicle.CabinEcoScoreState
import dev.decoupled.cabin.foundation.components.vehicle.CabinFluidLevelsState
import dev.decoupled.cabin.foundation.components.vehicle.CabinGuestModeState
import dev.decoupled.cabin.foundation.components.vehicle.CabinLightControlState
import dev.decoupled.cabin.foundation.components.vehicle.CabinLockControlState
import dev.decoupled.cabin.foundation.components.vehicle.CabinMirrorControlState
import dev.decoupled.cabin.foundation.components.vehicle.CabinRegenSelectorState
import dev.decoupled.cabin.foundation.components.vehicle.CabinSeatMemoryState
import dev.decoupled.cabin.foundation.components.vehicle.CabinServiceReminderState
import dev.decoupled.cabin.foundation.components.vehicle.CabinSteeringFeelState
import dev.decoupled.cabin.foundation.components.vehicle.CabinSuspensionControlState
import dev.decoupled.cabin.foundation.components.vehicle.CabinTelltaleSetState
import dev.decoupled.cabin.foundation.components.vehicle.CabinTirePressureState
import dev.decoupled.cabin.foundation.components.vehicle.CabinTractionControlState
import dev.decoupled.cabin.foundation.components.vehicle.CabinTripComputerState
import dev.decoupled.cabin.foundation.components.vehicle.CabinTrunkControlState
import dev.decoupled.cabin.foundation.components.vehicle.CabinValetModeState
import dev.decoupled.cabin.foundation.components.vehicle.CabinVehicleModelViewState
import dev.decoupled.cabin.foundation.components.vehicle.CabinVehicleOpenMapState
import dev.decoupled.cabin.foundation.components.vehicle.CabinWarningListState
import dev.decoupled.cabin.foundation.components.vehicle.CabinWindowControlState
import dev.decoupled.cabin.foundation.components.vehicle.CabinWiperControlState
import dev.decoupled.cabin.foundation.components.voice.CabinAssistantSurfaceState
import dev.decoupled.cabin.foundation.components.voice.CabinBargeInIndicatorState
import dev.decoupled.cabin.foundation.components.voice.CabinInvocationButtonState
import dev.decoupled.cabin.foundation.components.voice.CabinMicPrivacyIndicatorState
import dev.decoupled.cabin.foundation.components.voice.CabinMultiZoneVoiceState
import dev.decoupled.cabin.foundation.components.voice.CabinSuggestionChipsState
import dev.decoupled.cabin.foundation.components.voice.CabinVoiceConfirmCancelState
import dev.decoupled.cabin.foundation.components.voice.CabinVoiceResultsCardState
import dev.decoupled.cabin.foundation.components.voice.CabinYouCanSayBarState
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
fun SinkComponentDemo(
    id: String,
    modifier: Modifier = Modifier,
    ui: CabinComponentUiState = CabinComponentUiState(),
    variant: String = "",
) {
    when (id) {
        "button" -> CabinButton(state = CabinButtonState(variant = variant.ifBlank { CabinButtonState().variant }, ui = ui), modifier = modifier)
        "fab" -> CabinFab(state = CabinFabState(variant = variant.ifBlank { CabinFabState().variant }, ui = ui), modifier = modifier)
        "button-group" -> CabinButtonGroup(state = CabinButtonGroupState(variant = variant.ifBlank { CabinButtonGroupState().variant }, ui = ui), modifier = modifier)
        "toggle-button" -> CabinToggleButton(state = CabinToggleButtonState(variant = variant.ifBlank { CabinToggleButtonState().variant }, ui = ui), modifier = modifier)
        "segmented-button" -> CabinSegmentedButton(state = CabinSegmentedButtonState(variant = variant.ifBlank { CabinSegmentedButtonState().variant }, ui = ui), modifier = modifier)
        "split-button" -> CabinSplitButton(state = CabinSplitButtonState(variant = variant.ifBlank { CabinSplitButtonState().variant }, ui = ui), modifier = modifier)
        "long-press-button" -> CabinLongPressButton(state = CabinLongPressButtonState(variant = variant.ifBlank { CabinLongPressButtonState().variant }, ui = ui), modifier = modifier)
        "action-row" -> CabinActionRow(state = CabinActionRowState(variant = variant.ifBlank { CabinActionRowState().variant }, ui = ui), modifier = modifier)
        "hold-to-confirm" -> CabinHoldToConfirmButton(state = CabinHoldToConfirmButtonState(variant = variant.ifBlank { CabinHoldToConfirmButtonState().variant }, ui = ui), modifier = modifier)
        "safety-action" -> CabinSafetyActionButton(state = CabinSafetyActionButtonState(variant = variant.ifBlank { CabinSafetyActionButtonState().variant }, ui = ui), modifier = modifier)
        "press-hold-repeater" -> CabinPressHoldRepeater(state = CabinPressHoldRepeaterState(variant = variant.ifBlank { CabinPressHoldRepeaterState().variant }, ui = ui), modifier = modifier)
        "switch" -> CabinSwitch(state = CabinSwitchState(variant = variant.ifBlank { CabinSwitchState().variant }, ui = ui), modifier = modifier)
        "checkbox" -> CabinCheckbox(state = CabinCheckboxState(variant = variant.ifBlank { CabinCheckboxState().variant }, ui = ui), modifier = modifier)
        "radio" -> CabinRadio(state = CabinRadioState(variant = variant.ifBlank { CabinRadioState().variant }, ui = ui), modifier = modifier)
        "radio-group" -> CabinRadioGroup(state = CabinRadioGroupState(variant = variant.ifBlank { CabinRadioGroupState().variant }, ui = ui), modifier = modifier)
        "chip" -> CabinChip(state = CabinChipState(variant = variant.ifBlank { CabinChipState().variant }, ui = ui), modifier = modifier)
        "chip-group" -> CabinChipGroup(state = CabinChipGroupState(variant = variant.ifBlank { CabinChipGroupState().variant }, ui = ui), modifier = modifier)
        "selection-card" -> CabinSelectionCard(state = CabinSelectionCardState(variant = variant.ifBlank { CabinSelectionCardState().variant }, ui = ui), modifier = modifier)
        "multi-select-toolbar" -> CabinMultiSelectToolbar(state = CabinMultiSelectToolbarState(variant = variant.ifBlank { CabinMultiSelectToolbarState().variant }, ui = ui), modifier = modifier)
        "toggle-tile" -> CabinToggleTile(state = CabinToggleTileState(variant = variant.ifBlank { CabinToggleTileState().variant }, ui = ui), modifier = modifier)
        "tri-state-toggle" -> CabinTriStateToggle(state = CabinTriStateToggleState(variant = variant.ifBlank { CabinTriStateToggleState().variant }, ui = ui), modifier = modifier)
        "multi-position-selector" -> CabinMultiPositionSelector(state = CabinMultiPositionSelectorState(variant = variant.ifBlank { CabinMultiPositionSelectorState().variant }, ui = ui), modifier = modifier)
        "navigation-dock" -> CabinNavigationDock(state = CabinNavigationDockState(variant = variant.ifBlank { CabinNavigationDockState().variant }, ui = ui), modifier = modifier)
        "navigation-rail" -> CabinNavigationRail(state = CabinNavigationRailState(variant = variant.ifBlank { CabinNavigationRailState().variant }, ui = ui), modifier = modifier)
        "bottom-bar" -> CabinBottomBar(state = CabinBottomBarState(variant = variant.ifBlank { CabinBottomBarState().variant }, ui = ui), modifier = modifier)
        "side-drawer" -> CabinSideDrawer(state = CabinSideDrawerState(variant = variant.ifBlank { CabinSideDrawerState().variant }, ui = ui), modifier = modifier)
        "top-bar" -> CabinTopBar(state = CabinTopBarState(variant = variant.ifBlank { CabinTopBarState().variant }, ui = ui), modifier = modifier)
        "tabs" -> CabinTabs(state = CabinTabsState(variant = variant.ifBlank { CabinTabsState().variant }, ui = ui), modifier = modifier)
        "breadcrumbs" -> CabinBreadcrumbs(state = CabinBreadcrumbsState(variant = variant.ifBlank { CabinBreadcrumbsState().variant }, ui = ui), modifier = modifier)
        "back-affordance" -> CabinBackAffordance(state = CabinBackAffordanceState(variant = variant.ifBlank { CabinBackAffordanceState().variant }, ui = ui), modifier = modifier)
        "hardware-back-handler" -> CabinHardwareBackHandler(state = CabinHardwareBackHandlerState(variant = variant.ifBlank { CabinHardwareBackHandlerState().variant }, ui = ui), modifier = modifier)
        "page-indicator" -> CabinPageIndicator(state = CabinPageIndicatorState(variant = variant.ifBlank { CabinPageIndicatorState().variant }, ui = ui), modifier = modifier)
        "pager" -> CabinPager(state = CabinPagerState(variant = variant.ifBlank { CabinPagerState().variant }, ui = ui), modifier = modifier)
        "carousel" -> CabinCarousel(state = CabinCarouselState(variant = variant.ifBlank { CabinCarouselState().variant }, ui = ui), modifier = modifier)
        "wizard-stepper" -> CabinWizardStepper(state = CabinWizardStepperState(variant = variant.ifBlank { CabinWizardStepperState().variant }, ui = ui), modifier = modifier)
        "deep-link-helper" -> CabinDeepLinkHelper(state = CabinDeepLinkHelperState(variant = variant.ifBlank { CabinDeepLinkHelperState().variant }, ui = ui), modifier = modifier)
        "surface" -> CabinSurface(state = CabinSurfaceState(variant = variant.ifBlank { CabinSurfaceState().variant }, ui = ui), modifier = modifier)
        "card" -> CabinCard(state = CabinCardState(variant = variant.ifBlank { CabinCardState().variant }, ui = ui), modifier = modifier)
        "tile" -> CabinTile(state = CabinTileState(variant = variant.ifBlank { CabinTileState().variant }, ui = ui), modifier = modifier)
        "banner" -> CabinBanner(state = CabinBannerState(variant = variant.ifBlank { CabinBannerState().variant }, ui = ui), modifier = modifier)
        "panel" -> CabinPanel(state = CabinPanelState(variant = variant.ifBlank { CabinPanelState().variant }, ui = ui), modifier = modifier)
        "section-container" -> CabinSectionContainer(state = CabinSectionContainerState(variant = variant.ifBlank { CabinSectionContainerState().variant }, ui = ui), modifier = modifier)
        "expandable-container" -> CabinExpandableContainer(state = CabinExpandableContainerState(variant = variant.ifBlank { CabinExpandableContainerState().variant }, ui = ui), modifier = modifier)
        "accordion" -> CabinAccordion(state = CabinAccordionState(variant = variant.ifBlank { CabinAccordionState().variant }, ui = ui), modifier = modifier)
        "divider" -> CabinDivider(state = CabinDividerState(variant = variant.ifBlank { CabinDividerState().variant }, ui = ui), modifier = modifier)
        "spacer" -> CabinSpacer(state = CabinSpacerState(variant = variant.ifBlank { CabinSpacerState().variant }, ui = ui), modifier = modifier)
        "scrim-overlay" -> CabinScrimOverlay(state = CabinScrimOverlayState(variant = variant.ifBlank { CabinScrimOverlayState().variant }, ui = ui), modifier = modifier)
        "paged-scroll" -> CabinPagedScroll(state = CabinPagedScrollState(variant = variant.ifBlank { CabinPagedScrollState().variant }, ui = ui), modifier = modifier)
        "scroll-position-indicator" -> CabinScrollPositionIndicator(state = CabinScrollPositionIndicatorState(variant = variant.ifBlank { CabinScrollPositionIndicatorState().variant }, ui = ui), modifier = modifier)
        "scroll-buttons" -> CabinScrollButtons(state = CabinScrollButtonsState(variant = variant.ifBlank { CabinScrollButtonsState().variant }, ui = ui), modifier = modifier)
        "sticky-header" -> CabinStickyHeader(state = CabinStickyHeaderState(variant = variant.ifBlank { CabinStickyHeaderState().variant }, ui = ui), modifier = modifier)
        "pull-free-refresh" -> CabinPullFreeRefresh(state = CabinPullFreeRefreshState(variant = variant.ifBlank { CabinPullFreeRefreshState().variant }, ui = ui), modifier = modifier)
        "bottom-sheet" -> CabinBottomSheet(state = CabinBottomSheetState(variant = variant.ifBlank { CabinBottomSheetState().variant }, ui = ui), modifier = modifier)
        "side-sheet" -> CabinSideSheet(state = CabinSideSheetState(variant = variant.ifBlank { CabinSideSheetState().variant }, ui = ui), modifier = modifier)
        "modal-drawer" -> CabinModalDrawer(state = CabinModalDrawerState(variant = variant.ifBlank { CabinModalDrawerState().variant }, ui = ui), modifier = modifier)
        "list-item" -> CabinListItem(state = CabinListItemState(variant = variant.ifBlank { CabinListItemState().variant }, ui = ui), modifier = modifier)
        "paged-list" -> CabinPagedList(state = CabinPagedListState(variant = variant.ifBlank { CabinPagedListState().variant }, ui = ui), modifier = modifier)
        "sectioned-list" -> CabinSectionedList(state = CabinSectionedListState(variant = variant.ifBlank { CabinSectionedListState().variant }, ui = ui), modifier = modifier)
        "grouped-list" -> CabinGroupedList(state = CabinGroupedListState(variant = variant.ifBlank { CabinGroupedListState().variant }, ui = ui), modifier = modifier)
        "jump-list" -> CabinJumpList(state = CabinJumpListState(variant = variant.ifBlank { CabinJumpListState().variant }, ui = ui), modifier = modifier)
        "app-grid" -> CabinAppGrid(state = CabinAppGridState(variant = variant.ifBlank { CabinAppGridState().variant }, ui = ui), modifier = modifier)
        "media-grid" -> CabinMediaGrid(state = CabinMediaGridState(variant = variant.ifBlank { CabinMediaGridState().variant }, ui = ui), modifier = modifier)
        "adaptive-grid" -> CabinAdaptiveGrid(state = CabinAdaptiveGridState(variant = variant.ifBlank { CabinAdaptiveGridState().variant }, ui = ui), modifier = modifier)
        "data-table" -> CabinDataTable(state = CabinDataTableState(variant = variant.ifBlank { CabinDataTableState().variant }, ui = ui), modifier = modifier)
        "key-value-row" -> CabinKeyValueRow(state = CabinKeyValueRowState(variant = variant.ifBlank { CabinKeyValueRowState().variant }, ui = ui), modifier = modifier)
        "info-row" -> CabinInfoRow(state = CabinInfoRowState(variant = variant.ifBlank { CabinInfoRowState().variant }, ui = ui), modifier = modifier)
        "description-list" -> CabinDescriptionList(state = CabinDescriptionListState(variant = variant.ifBlank { CabinDescriptionListState().variant }, ui = ui), modifier = modifier)
        "timeline-list" -> CabinTimelineList(state = CabinTimelineListState(variant = variant.ifBlank { CabinTimelineListState().variant }, ui = ui), modifier = modifier)
        "filter-bar" -> CabinFilterBar(state = CabinFilterBarState(variant = variant.ifBlank { CabinFilterBarState().variant }, ui = ui), modifier = modifier)
        "sort-control" -> CabinSortControl(state = CabinSortControlState(variant = variant.ifBlank { CabinSortControlState().variant }, ui = ui), modifier = modifier)
        "search-result-list" -> CabinSearchResultList(state = CabinSearchResultListState(variant = variant.ifBlank { CabinSearchResultListState().variant }, ui = ui), modifier = modifier)
        "empty-state" -> CabinEmptyState(state = CabinEmptyStateState(variant = variant.ifBlank { CabinEmptyStateState().variant }, ui = ui), modifier = modifier)
        "error-state" -> CabinErrorState(state = CabinErrorStateState(variant = variant.ifBlank { CabinErrorStateState().variant }, ui = ui), modifier = modifier)
        "loading-collection" -> CabinLoadingCollection(state = CabinLoadingCollectionState(variant = variant.ifBlank { CabinLoadingCollectionState().variant }, ui = ui), modifier = modifier)
        "text-field" -> CabinTextField(state = CabinTextFieldState(variant = variant.ifBlank { CabinTextFieldState().variant }, ui = ui), modifier = modifier)
        "search-field" -> CabinSearchField(state = CabinSearchFieldState(variant = variant.ifBlank { CabinSearchFieldState().variant }, ui = ui), modifier = modifier)
        "pin-field" -> CabinPinField(state = CabinPinFieldState(variant = variant.ifBlank { CabinPinFieldState().variant }, ui = ui), modifier = modifier)
        "otp-field" -> CabinOtpField(state = CabinOtpFieldState(variant = variant.ifBlank { CabinOtpFieldState().variant }, ui = ui), modifier = modifier)
        "multiline-text" -> CabinMultilineText(state = CabinMultilineTextState(variant = variant.ifBlank { CabinMultilineTextState().variant }, ui = ui), modifier = modifier)
        "autocomplete" -> CabinAutocomplete(state = CabinAutocompleteState(variant = variant.ifBlank { CabinAutocompleteState().variant }, ui = ui), modifier = modifier)
        "masked-input" -> CabinMaskedInput(state = CabinMaskedInputState(variant = variant.ifBlank { CabinMaskedInputState().variant }, ui = ui), modifier = modifier)
        "numeric-keypad" -> CabinNumericKeypad(state = CabinNumericKeypadState(variant = variant.ifBlank { CabinNumericKeypadState().variant }, ui = ui), modifier = modifier)
        "dial-pad" -> CabinDialPad(state = CabinDialPadState(variant = variant.ifBlank { CabinDialPadState().variant }, ui = ui), modifier = modifier)
        "rotary-text-entry" -> CabinRotaryTextEntry(state = CabinRotaryTextEntryState(variant = variant.ifBlank { CabinRotaryTextEntryState().variant }, ui = ui), modifier = modifier)
        "slider" -> CabinSlider(state = CabinSliderState(variant = variant.ifBlank { CabinSliderState().variant }, ui = ui), modifier = modifier)
        "range-slider" -> CabinRangeSlider(state = CabinRangeSliderState(variant = variant.ifBlank { CabinRangeSliderState().variant }, ui = ui), modifier = modifier)
        "stepper" -> CabinStepper(state = CabinStepperState(variant = variant.ifBlank { CabinStepperState().variant }, ui = ui), modifier = modifier)
        "number-picker" -> CabinNumberPicker(state = CabinNumberPickerState(variant = variant.ifBlank { CabinNumberPickerState().variant }, ui = ui), modifier = modifier)
        "dial-knob" -> CabinDialKnob(state = CabinDialKnobState(variant = variant.ifBlank { CabinDialKnobState().variant }, ui = ui), modifier = modifier)
        "date-picker" -> CabinDatePicker(state = CabinDatePickerState(variant = variant.ifBlank { CabinDatePickerState().variant }, ui = ui), modifier = modifier)
        "time-picker" -> CabinTimePicker(state = CabinTimePickerState(variant = variant.ifBlank { CabinTimePickerState().variant }, ui = ui), modifier = modifier)
        "duration-picker" -> CabinDurationPicker(state = CabinDurationPickerState(variant = variant.ifBlank { CabinDurationPickerState().variant }, ui = ui), modifier = modifier)
        "dropdown" -> CabinDropdown(state = CabinDropdownState(variant = variant.ifBlank { CabinDropdownState().variant }, ui = ui), modifier = modifier)
        "list-picker" -> CabinListPicker(state = CabinListPickerState(variant = variant.ifBlank { CabinListPickerState().variant }, ui = ui), modifier = modifier)
        "color-picker" -> CabinColorPicker(state = CabinColorPickerState(variant = variant.ifBlank { CabinColorPickerState().variant }, ui = ui), modifier = modifier)
        "wheel-picker" -> CabinWheelPicker(state = CabinWheelPickerState(variant = variant.ifBlank { CabinWheelPickerState().variant }, ui = ui), modifier = modifier)
        "option-picker" -> CabinOptionPicker(state = CabinOptionPickerState(variant = variant.ifBlank { CabinOptionPickerState().variant }, ui = ui), modifier = modifier)
        "voice-mic-button" -> CabinVoiceMicButton(state = CabinVoiceMicButtonState(variant = variant.ifBlank { CabinVoiceMicButtonState().variant }, ui = ui), modifier = modifier)
        "listening-indicator" -> CabinListeningIndicator(state = CabinListeningIndicatorState(variant = variant.ifBlank { CabinListeningIndicatorState().variant }, ui = ui), modifier = modifier)
        "transcription-field" -> CabinTranscriptionField(state = CabinTranscriptionFieldState(variant = variant.ifBlank { CabinTranscriptionFieldState().variant }, ui = ui), modifier = modifier)
        "rating-input" -> CabinRatingInput(state = CabinRatingInputState(variant = variant.ifBlank { CabinRatingInputState().variant }, ui = ui), modifier = modifier)
        "gesture-pad" -> CabinGesturePad(state = CabinGesturePadState(variant = variant.ifBlank { CabinGesturePadState().variant }, ui = ui), modifier = modifier)
        "dialog" -> CabinDialog(state = CabinDialogState(variant = variant.ifBlank { CabinDialogState().variant }, ui = ui), modifier = modifier)
        "snackbar" -> CabinSnackbar(state = CabinSnackbarState(variant = variant.ifBlank { CabinSnackbarState().variant }, ui = ui), modifier = modifier)
        "toast" -> CabinToast(state = CabinToastState(variant = variant.ifBlank { CabinToastState().variant }, ui = ui), modifier = modifier)
        "inline-message" -> CabinInlineMessage(state = CabinInlineMessageState(variant = variant.ifBlank { CabinInlineMessageState().variant }, ui = ui), modifier = modifier)
        "callout" -> CabinCallout(state = CabinCalloutState(variant = variant.ifBlank { CabinCalloutState().variant }, ui = ui), modifier = modifier)
        "linear-progress" -> CabinLinearProgress(state = CabinLinearProgressState(variant = variant.ifBlank { CabinLinearProgressState().variant }, ui = ui), modifier = modifier)
        "circular-progress" -> CabinCircularProgress(state = CabinCircularProgressState(variant = variant.ifBlank { CabinCircularProgressState().variant }, ui = ui), modifier = modifier)
        "skeleton" -> CabinSkeleton(state = CabinSkeletonState(variant = variant.ifBlank { CabinSkeletonState().variant }, ui = ui), modifier = modifier)
        "step-progress" -> CabinStepProgress(state = CabinStepProgressState(variant = variant.ifBlank { CabinStepProgressState().variant }, ui = ui), modifier = modifier)
        "context-menu" -> CabinContextMenu(state = CabinContextMenuState(variant = variant.ifBlank { CabinContextMenuState().variant }, ui = ui), modifier = modifier)
        "tooltip" -> CabinTooltip(state = CabinTooltipState(variant = variant.ifBlank { CabinTooltipState().variant }, ui = ui), modifier = modifier)
        "offline-state" -> CabinOfflineState(state = CabinOfflineStateState(variant = variant.ifBlank { CabinOfflineStateState().variant }, ui = ui), modifier = modifier)
        "no-permission-state" -> CabinNoPermissionState(state = CabinNoPermissionStateState(variant = variant.ifBlank { CabinNoPermissionStateState().variant }, ui = ui), modifier = modifier)
        "driving-restricted-state" -> CabinDrivingRestrictedState(state = CabinDrivingRestrictedStateState(variant = variant.ifBlank { CabinDrivingRestrictedStateState().variant }, ui = ui), modifier = modifier)
        "badge" -> CabinBadge(state = CabinBadgeState(variant = variant.ifBlank { CabinBadgeState().variant }, ui = ui), modifier = modifier)
        "status-pill" -> CabinStatusPill(state = CabinStatusPillState(variant = variant.ifBlank { CabinStatusPillState().variant }, ui = ui), modifier = modifier)
        "tag" -> CabinTag(state = CabinTagState(variant = variant.ifBlank { CabinTagState().variant }, ui = ui), modifier = modifier)
        "splash-screen" -> CabinSplashScreen(state = CabinSplashScreenState(variant = variant.ifBlank { CabinSplashScreenState().variant }, ui = ui), modifier = modifier)
        "preference" -> CabinPreference(state = CabinPreferenceState(variant = variant.ifBlank { CabinPreferenceState().variant }, ui = ui), modifier = modifier)
        "preference-scaffold" -> CabinPreferenceScaffold(state = CabinPreferenceScaffoldState(variant = variant.ifBlank { CabinPreferenceScaffoldState().variant }, ui = ui), modifier = modifier)
        "settings-search" -> CabinSettingsSearch(state = CabinSettingsSearchState(variant = variant.ifBlank { CabinSettingsSearchState().variant }, ui = ui), modifier = modifier)
        "settings-tile" -> CabinSettingsTile(state = CabinSettingsTileState(variant = variant.ifBlank { CabinSettingsTileState().variant }, ui = ui), modifier = modifier)
        "settings-homepage" -> CabinSettingsHomepage(state = CabinSettingsHomepageState(variant = variant.ifBlank { CabinSettingsHomepageState().variant }, ui = ui), modifier = modifier)
        "mini-player" -> CabinMiniPlayer(state = CabinMiniPlayerState(variant = variant.ifBlank { CabinMiniPlayerState().variant }, ui = ui), modifier = modifier)
        "dock-player" -> CabinDockPlayer(state = CabinDockPlayerState(variant = variant.ifBlank { CabinDockPlayerState().variant }, ui = ui), modifier = modifier)
        "transport-controls" -> CabinTransportControls(state = CabinTransportControlsState(variant = variant.ifBlank { CabinTransportControlsState().variant }, ui = ui), modifier = modifier)
        "media-seek-bar" -> CabinMediaSeekBar(state = CabinMediaSeekBarState(variant = variant.ifBlank { CabinMediaSeekBarState().variant }, ui = ui), modifier = modifier)
        "shuffle-repeat" -> CabinShuffleRepeat(state = CabinShuffleRepeatState(variant = variant.ifBlank { CabinShuffleRepeatState().variant }, ui = ui), modifier = modifier)
        "rating-like" -> CabinRatingLike(state = CabinRatingLikeState(variant = variant.ifBlank { CabinRatingLikeState().variant }, ui = ui), modifier = modifier)
        "album-art" -> CabinAlbumArt(state = CabinAlbumArtState(variant = variant.ifBlank { CabinAlbumArtState().variant }, ui = ui), modifier = modifier)
        "queue-list" -> CabinQueueList(state = CabinQueueListState(variant = variant.ifBlank { CabinQueueListState().variant }, ui = ui), modifier = modifier)
        "browse-tree" -> CabinBrowseTree(state = CabinBrowseTreeState(variant = variant.ifBlank { CabinBrowseTreeState().variant }, ui = ui), modifier = modifier)
        "source-switcher" -> CabinSourceSwitcher(state = CabinSourceSwitcherState(variant = variant.ifBlank { CabinSourceSwitcherState().variant }, ui = ui), modifier = modifier)
        "audio-zone-selector" -> CabinAudioZoneSelector(state = CabinAudioZoneSelectorState(variant = variant.ifBlank { CabinAudioZoneSelectorState().variant }, ui = ui), modifier = modifier)
        "volume-panel" -> CabinVolumePanel(state = CabinVolumePanelState(variant = variant.ifBlank { CabinVolumePanelState().variant }, ui = ui), modifier = modifier)
        "fade-balance-panel" -> CabinFadeBalancePanel(state = CabinFadeBalancePanelState(variant = variant.ifBlank { CabinFadeBalancePanelState().variant }, ui = ui), modifier = modifier)
        "eq-controls" -> CabinEqControls(state = CabinEqControlsState(variant = variant.ifBlank { CabinEqControlsState().variant }, ui = ui), modifier = modifier)
        "audio-visualizer" -> CabinAudioVisualizer(state = CabinAudioVisualizerState(variant = variant.ifBlank { CabinAudioVisualizerState().variant }, ui = ui), modifier = modifier)
        "radio-tuner" -> CabinRadioTuner(state = CabinRadioTunerState(variant = variant.ifBlank { CabinRadioTunerState().variant }, ui = ui), modifier = modifier)
        "radio-presets" -> CabinRadioPresets(state = CabinRadioPresetsState(variant = variant.ifBlank { CabinRadioPresetsState().variant }, ui = ui), modifier = modifier)
        "station-list" -> CabinStationList(state = CabinStationListState(variant = variant.ifBlank { CabinStationListState().variant }, ui = ui), modifier = modifier)
        "hd-metadata-card" -> CabinHdMetadataCard(state = CabinHdMetadataCardState(variant = variant.ifBlank { CabinHdMetadataCardState().variant }, ui = ui), modifier = modifier)
        "podcast-controls" -> CabinPodcastControls(state = CabinPodcastControlsState(variant = variant.ifBlank { CabinPodcastControlsState().variant }, ui = ui), modifier = modifier)
        "lyrics-view" -> CabinLyricsView(state = CabinLyricsViewState(variant = variant.ifBlank { CabinLyricsViewState().variant }, ui = ui), modifier = modifier)
        "video-player-chrome" -> CabinVideoPlayerChrome(state = CabinVideoPlayerChromeState(variant = variant.ifBlank { CabinVideoPlayerChromeState().variant }, ui = ui), modifier = modifier)
        "rear-seat-media-controls" -> CabinRearSeatMediaControls(state = CabinRearSeatMediaControlsState(variant = variant.ifBlank { CabinRearSeatMediaControlsState().variant }, ui = ui), modifier = modifier)
        "temperature-control" -> CabinTemperatureControl(state = CabinTemperatureControlState(variant = variant.ifBlank { CabinTemperatureControlState().variant }, ui = ui), modifier = modifier)
        "fan-speed" -> CabinFanSpeed(state = CabinFanSpeedState(variant = variant.ifBlank { CabinFanSpeedState().variant }, ui = ui), modifier = modifier)
        "airflow-direction" -> CabinAirflowDirection(state = CabinAirflowDirectionState(variant = variant.ifBlank { CabinAirflowDirectionState().variant }, ui = ui), modifier = modifier)
        "zone-selector" -> CabinZoneSelector(state = CabinZoneSelectorState(variant = variant.ifBlank { CabinZoneSelectorState().variant }, ui = ui), modifier = modifier)
        "seat-climate" -> CabinSeatClimate(state = CabinSeatClimateState(variant = variant.ifBlank { CabinSeatClimateState().variant }, ui = ui), modifier = modifier)
        "steering-wheel-heat" -> CabinSteeringWheelHeat(state = CabinSteeringWheelHeatState(variant = variant.ifBlank { CabinSteeringWheelHeatState().variant }, ui = ui), modifier = modifier)
        "mirror-heat" -> CabinMirrorHeat(state = CabinMirrorHeatState(variant = variant.ifBlank { CabinMirrorHeatState().variant }, ui = ui), modifier = modifier)
        "defrost-toggle" -> CabinDefrostToggle(state = CabinDefrostToggleState(variant = variant.ifBlank { CabinDefrostToggleState().variant }, ui = ui), modifier = modifier)
        "recirc-toggle" -> CabinRecircToggle(state = CabinRecircToggleState(variant = variant.ifBlank { CabinRecircToggleState().variant }, ui = ui), modifier = modifier)
        "auto-ac-toggle" -> CabinAutoAcToggle(state = CabinAutoAcToggleState(variant = variant.ifBlank { CabinAutoAcToggleState().variant }, ui = ui), modifier = modifier)
        "eco-toggle" -> CabinEcoToggle(state = CabinEcoToggleState(variant = variant.ifBlank { CabinEcoToggleState().variant }, ui = ui), modifier = modifier)
        "air-quality-indicator" -> CabinAirQualityIndicator(state = CabinAirQualityIndicatorState(variant = variant.ifBlank { CabinAirQualityIndicatorState().variant }, ui = ui), modifier = modifier)
        "fragrance-control" -> CabinFragranceControl(state = CabinFragranceControlState(variant = variant.ifBlank { CabinFragranceControlState().variant }, ui = ui), modifier = modifier)
        "preconditioning-scheduler" -> CabinPreconditioningScheduler(state = CabinPreconditioningSchedulerState(variant = variant.ifBlank { CabinPreconditioningSchedulerState().variant }, ui = ui), modifier = modifier)
        "ambient-lighting" -> CabinAmbientLighting(state = CabinAmbientLightingState(variant = variant.ifBlank { CabinAmbientLightingState().variant }, ui = ui), modifier = modifier)
        "sunroof-control" -> CabinSunroofControl(state = CabinSunroofControlState(variant = variant.ifBlank { CabinSunroofControlState().variant }, ui = ui), modifier = modifier)
        "hvac-overlay" -> CabinHvacOverlay(state = CabinHvacOverlayState(variant = variant.ifBlank { CabinHvacOverlayState().variant }, ui = ui), modifier = modifier)
        "persistent-hvac-bar" -> CabinPersistentHvacBar(state = CabinPersistentHvacBarState(variant = variant.ifBlank { CabinPersistentHvacBarState().variant }, ui = ui), modifier = modifier)
        "door-control" -> CabinDoorControl(state = CabinDoorControlState(variant = variant.ifBlank { CabinDoorControlState().variant }, ui = ui), modifier = modifier)
        "window-control" -> CabinWindowControl(state = CabinWindowControlState(variant = variant.ifBlank { CabinWindowControlState().variant }, ui = ui), modifier = modifier)
        "trunk-control" -> CabinTrunkControl(state = CabinTrunkControlState(variant = variant.ifBlank { CabinTrunkControlState().variant }, ui = ui), modifier = modifier)
        "lock-control" -> CabinLockControl(state = CabinLockControlState(variant = variant.ifBlank { CabinLockControlState().variant }, ui = ui), modifier = modifier)
        "mirror-control" -> CabinMirrorControl(state = CabinMirrorControlState(variant = variant.ifBlank { CabinMirrorControlState().variant }, ui = ui), modifier = modifier)
        "seat-memory" -> CabinSeatMemory(state = CabinSeatMemoryState(variant = variant.ifBlank { CabinSeatMemoryState().variant }, ui = ui), modifier = modifier)
        "charge-port-door" -> CabinChargePortDoor(state = CabinChargePortDoorState(variant = variant.ifBlank { CabinChargePortDoorState().variant }, ui = ui), modifier = modifier)
        "wiper-control" -> CabinWiperControl(state = CabinWiperControlState(variant = variant.ifBlank { CabinWiperControlState().variant }, ui = ui), modifier = modifier)
        "light-control" -> CabinLightControl(state = CabinLightControlState(variant = variant.ifBlank { CabinLightControlState().variant }, ui = ui), modifier = modifier)
        "drive-mode-selector" -> CabinDriveModeSelector(state = CabinDriveModeSelectorState(variant = variant.ifBlank { CabinDriveModeSelectorState().variant }, ui = ui), modifier = modifier)
        "regen-selector" -> CabinRegenSelector(state = CabinRegenSelectorState(variant = variant.ifBlank { CabinRegenSelectorState().variant }, ui = ui), modifier = modifier)
        "suspension-control" -> CabinSuspensionControl(state = CabinSuspensionControlState(variant = variant.ifBlank { CabinSuspensionControlState().variant }, ui = ui), modifier = modifier)
        "steering-feel" -> CabinSteeringFeel(state = CabinSteeringFeelState(variant = variant.ifBlank { CabinSteeringFeelState().variant }, ui = ui), modifier = modifier)
        "traction-control" -> CabinTractionControl(state = CabinTractionControlState(variant = variant.ifBlank { CabinTractionControlState().variant }, ui = ui), modifier = modifier)
        "telltale-set" -> CabinTelltaleSet(state = CabinTelltaleSetState(variant = variant.ifBlank { CabinTelltaleSetState().variant }, ui = ui), modifier = modifier)
        "warning-list" -> CabinWarningList(state = CabinWarningListState(variant = variant.ifBlank { CabinWarningListState().variant }, ui = ui), modifier = modifier)
        "service-reminder" -> CabinServiceReminder(state = CabinServiceReminderState(variant = variant.ifBlank { CabinServiceReminderState().variant }, ui = ui), modifier = modifier)
        "vehicle-open-map" -> CabinVehicleOpenMap(state = CabinVehicleOpenMapState(variant = variant.ifBlank { CabinVehicleOpenMapState().variant }, ui = ui), modifier = modifier)
        "tire-pressure" -> CabinTirePressure(state = CabinTirePressureState(variant = variant.ifBlank { CabinTirePressureState().variant }, ui = ui), modifier = modifier)
        "fluid-levels" -> CabinFluidLevels(state = CabinFluidLevelsState(variant = variant.ifBlank { CabinFluidLevelsState().variant }, ui = ui), modifier = modifier)
        "battery-12v" -> CabinBattery12v(state = CabinBattery12vState(variant = variant.ifBlank { CabinBattery12vState().variant }, ui = ui), modifier = modifier)
        "trip-computer" -> CabinTripComputer(state = CabinTripComputerState(variant = variant.ifBlank { CabinTripComputerState().variant }, ui = ui), modifier = modifier)
        "consumption-graph" -> CabinConsumptionGraph(state = CabinConsumptionGraphState(variant = variant.ifBlank { CabinConsumptionGraphState().variant }, ui = ui), modifier = modifier)
        "eco-score" -> CabinEcoScore(state = CabinEcoScoreState(variant = variant.ifBlank { CabinEcoScoreState().variant }, ui = ui), modifier = modifier)
        "vehicle-model-view" -> CabinVehicleModelView(state = CabinVehicleModelViewState(variant = variant.ifBlank { CabinVehicleModelViewState().variant }, ui = ui), modifier = modifier)
        "confirm-safety-action" -> CabinConfirmSafetyAction(state = CabinConfirmSafetyActionState(variant = variant.ifBlank { CabinConfirmSafetyActionState().variant }, ui = ui), modifier = modifier)
        "digital-key" -> CabinDigitalKey(state = CabinDigitalKeyState(variant = variant.ifBlank { CabinDigitalKeyState().variant }, ui = ui), modifier = modifier)
        "valet-mode" -> CabinValetMode(state = CabinValetModeState(variant = variant.ifBlank { CabinValetModeState().variant }, ui = ui), modifier = modifier)
        "guest-mode" -> CabinGuestMode(state = CabinGuestModeState(variant = variant.ifBlank { CabinGuestModeState().variant }, ui = ui), modifier = modifier)
        "range-estimator" -> CabinRangeEstimator(state = CabinRangeEstimatorState(variant = variant.ifBlank { CabinRangeEstimatorState().variant }, ui = ui), modifier = modifier)
        "range-on-map" -> CabinRangeOnMap(state = CabinRangeOnMapState(variant = variant.ifBlank { CabinRangeOnMapState().variant }, ui = ui), modifier = modifier)
        "charge-session-card" -> CabinChargeSessionCard(state = CabinChargeSessionCardState(variant = variant.ifBlank { CabinChargeSessionCardState().variant }, ui = ui), modifier = modifier)
        "charge-limit-slider" -> CabinChargeLimitSlider(state = CabinChargeLimitSliderState(variant = variant.ifBlank { CabinChargeLimitSliderState().variant }, ui = ui), modifier = modifier)
        "charging-schedule" -> CabinChargingSchedule(state = CabinChargingScheduleState(variant = variant.ifBlank { CabinChargingScheduleState().variant }, ui = ui), modifier = modifier)
        "charge-port-status" -> CabinChargePortStatus(state = CabinChargePortStatusState(variant = variant.ifBlank { CabinChargePortStatusState().variant }, ui = ui), modifier = modifier)
        "charging-station-card" -> CabinChargingStationCard(state = CabinChargingStationCardState(variant = variant.ifBlank { CabinChargingStationCardState().variant }, ui = ui), modifier = modifier)
        "connector-type-chip" -> CabinConnectorTypeChip(state = CabinConnectorTypeChipState(variant = variant.ifBlank { CabinConnectorTypeChipState().variant }, ui = ui), modifier = modifier)
        "energy-flow-diagram" -> CabinEnergyFlowDiagram(state = CabinEnergyFlowDiagramState(variant = variant.ifBlank { CabinEnergyFlowDiagramState().variant }, ui = ui), modifier = modifier)
        "consumption-chart" -> CabinConsumptionChart(state = CabinConsumptionChartState(variant = variant.ifBlank { CabinConsumptionChartState().variant }, ui = ui), modifier = modifier)
        "regen-indicator" -> CabinRegenIndicator(state = CabinRegenIndicatorState(variant = variant.ifBlank { CabinRegenIndicatorState().variant }, ui = ui), modifier = modifier)
        "charge-precondition" -> CabinChargePrecondition(state = CabinChargePreconditionState(variant = variant.ifBlank { CabinChargePreconditionState().variant }, ui = ui), modifier = modifier)
        "trip-charge-planner" -> CabinTripChargePlanner(state = CabinTripChargePlannerState(variant = variant.ifBlank { CabinTripChargePlannerState().variant }, ui = ui), modifier = modifier)
        "v2x-controls" -> CabinV2xControls(state = CabinV2xControlsState(variant = variant.ifBlank { CabinV2xControlsState().variant }, ui = ui), modifier = modifier)
        "fuel-level" -> CabinFuelLevel(state = CabinFuelLevelState(variant = variant.ifBlank { CabinFuelLevelState().variant }, ui = ui), modifier = modifier)
        "hybrid-range-breakdown" -> CabinHybridRangeBreakdown(state = CabinHybridRangeBreakdownState(variant = variant.ifBlank { CabinHybridRangeBreakdownState().variant }, ui = ui), modifier = modifier)
        "map-container" -> CabinMapContainer(state = CabinMapContainerState(variant = variant.ifBlank { CabinMapContainerState().variant }, ui = ui), modifier = modifier)
        "map-controls" -> CabinMapControls(state = CabinMapControlsState(variant = variant.ifBlank { CabinMapControlsState().variant }, ui = ui), modifier = modifier)
        "route-card" -> CabinRouteCard(state = CabinRouteCardState(variant = variant.ifBlank { CabinRouteCardState().variant }, ui = ui), modifier = modifier)
        "eta-panel" -> CabinEtaPanel(state = CabinEtaPanelState(variant = variant.ifBlank { CabinEtaPanelState().variant }, ui = ui), modifier = modifier)
        "turn-instruction" -> CabinTurnInstruction(state = CabinTurnInstructionState(variant = variant.ifBlank { CabinTurnInstructionState().variant }, ui = ui), modifier = modifier)
        "lane-guidance" -> CabinLaneGuidance(state = CabinLaneGuidanceState(variant = variant.ifBlank { CabinLaneGuidanceState().variant }, ui = ui), modifier = modifier)
        "junction-view" -> CabinJunctionView(state = CabinJunctionViewState(variant = variant.ifBlank { CabinJunctionViewState().variant }, ui = ui), modifier = modifier)
        "speed-limit" -> CabinNavSpeedLimit(state = CabinNavSpeedLimitState(variant = variant.ifBlank { CabinNavSpeedLimitState().variant }, ui = ui), modifier = modifier)
        "nav-search-bar" -> CabinNavSearchBar(state = CabinNavSearchBarState(variant = variant.ifBlank { CabinNavSearchBarState().variant }, ui = ui), modifier = modifier)
        "place-card" -> CabinPlaceCard(state = CabinPlaceCardState(variant = variant.ifBlank { CabinPlaceCardState().variant }, ui = ui), modifier = modifier)
        "favorites-list" -> CabinFavoritesList(state = CabinFavoritesListState(variant = variant.ifBlank { CabinFavoritesListState().variant }, ui = ui), modifier = modifier)
        "poi-chips" -> CabinPoiChips(state = CabinPoiChipsState(variant = variant.ifBlank { CabinPoiChipsState().variant }, ui = ui), modifier = modifier)
        "arrival-panel" -> CabinArrivalPanel(state = CabinArrivalPanelState(variant = variant.ifBlank { CabinArrivalPanelState().variant }, ui = ui), modifier = modifier)
        "route-options" -> CabinRouteOptions(state = CabinRouteOptionsState(variant = variant.ifBlank { CabinRouteOptionsState().variant }, ui = ui), modifier = modifier)
        "traffic-indicator" -> CabinTrafficIndicator(state = CabinTrafficIndicatorState(variant = variant.ifBlank { CabinTrafficIndicatorState().variant }, ui = ui), modifier = modifier)
        "waypoint-list" -> CabinWaypointList(state = CabinWaypointListState(variant = variant.ifBlank { CabinWaypointListState().variant }, ui = ui), modifier = modifier)
        "trip-summary" -> CabinTripSummary(state = CabinTripSummaryState(variant = variant.ifBlank { CabinTripSummaryState().variant }, ui = ui), modifier = modifier)
        "parking-availability" -> CabinParkingAvailability(state = CabinParkingAvailabilityState(variant = variant.ifBlank { CabinParkingAvailabilityState().variant }, ui = ui), modifier = modifier)
        "nav-widget" -> CabinNavWidget(state = CabinNavWidgetState(variant = variant.ifBlank { CabinNavWidgetState().variant }, ui = ui), modifier = modifier)
        "dialer" -> CabinDialer(state = CabinDialerState(variant = variant.ifBlank { CabinDialerState().variant }, ui = ui), modifier = modifier)
        "in-call-screen" -> CabinInCallScreen(state = CabinInCallScreenState(variant = variant.ifBlank { CabinInCallScreenState().variant }, ui = ui), modifier = modifier)
        "incoming-call-hud" -> CabinIncomingCallHud(state = CabinIncomingCallHudState(variant = variant.ifBlank { CabinIncomingCallHudState().variant }, ui = ui), modifier = modifier)
        "call-controls" -> CabinCallControls(state = CabinCallControlsState(variant = variant.ifBlank { CabinCallControlsState().variant }, ui = ui), modifier = modifier)
        "contact-list" -> CabinContactList(state = CabinContactListState(variant = variant.ifBlank { CabinContactListState().variant }, ui = ui), modifier = modifier)
        "contact-card" -> CabinContactCard(state = CabinContactCardState(variant = variant.ifBlank { CabinContactCardState().variant }, ui = ui), modifier = modifier)
        "conversation-list" -> CabinConversationList(state = CabinConversationListState(variant = variant.ifBlank { CabinConversationListState().variant }, ui = ui), modifier = modifier)
        "message-bubble" -> CabinMessageBubble(state = CabinMessageBubbleState(variant = variant.ifBlank { CabinMessageBubbleState().variant }, ui = ui), modifier = modifier)
        "voice-reply" -> CabinVoiceReply(state = CabinVoiceReplyState(variant = variant.ifBlank { CabinVoiceReplyState().variant }, ui = ui), modifier = modifier)
        "canned-reply-chips" -> CabinCannedReplyChips(state = CabinCannedReplyChipsState(variant = variant.ifBlank { CabinCannedReplyChipsState().variant }, ui = ui), modifier = modifier)
        "bluetooth-pairing" -> CabinBluetoothPairing(state = CabinBluetoothPairingState(variant = variant.ifBlank { CabinBluetoothPairingState().variant }, ui = ui), modifier = modifier)
        "device-list" -> CabinDeviceList(state = CabinDeviceListState(variant = variant.ifBlank { CabinDeviceListState().variant }, ui = ui), modifier = modifier)
        "projection-status" -> CabinProjectionStatus(state = CabinProjectionStatusState(variant = variant.ifBlank { CabinProjectionStatusState().variant }, ui = ui), modifier = modifier)
        "voicemail" -> CabinVoicemail(state = CabinVoicemailState(variant = variant.ifBlank { CabinVoicemailState().variant }, ui = ui), modifier = modifier)
        "assistant-surface" -> CabinAssistantSurface(state = CabinAssistantSurfaceState(variant = variant.ifBlank { CabinAssistantSurfaceState().variant }, ui = ui), modifier = modifier)
        "invocation-button" -> CabinInvocationButton(state = CabinInvocationButtonState(variant = variant.ifBlank { CabinInvocationButtonState().variant }, ui = ui), modifier = modifier)
        "mic-privacy-indicator" -> CabinMicPrivacyIndicator(state = CabinMicPrivacyIndicatorState(variant = variant.ifBlank { CabinMicPrivacyIndicatorState().variant }, ui = ui), modifier = modifier)
        "suggestion-chips" -> CabinSuggestionChips(state = CabinSuggestionChipsState(variant = variant.ifBlank { CabinSuggestionChipsState().variant }, ui = ui), modifier = modifier)
        "you-can-say-bar" -> CabinYouCanSayBar(state = CabinYouCanSayBarState(variant = variant.ifBlank { CabinYouCanSayBarState().variant }, ui = ui), modifier = modifier)
        "voice-results-card" -> CabinVoiceResultsCard(state = CabinVoiceResultsCardState(variant = variant.ifBlank { CabinVoiceResultsCardState().variant }, ui = ui), modifier = modifier)
        "confirm-cancel" -> CabinVoiceConfirmCancel(state = CabinVoiceConfirmCancelState(variant = variant.ifBlank { CabinVoiceConfirmCancelState().variant }, ui = ui), modifier = modifier)
        "barge-in-indicator" -> CabinBargeInIndicator(state = CabinBargeInIndicatorState(variant = variant.ifBlank { CabinBargeInIndicatorState().variant }, ui = ui), modifier = modifier)
        "multi-zone-voice" -> CabinMultiZoneVoice(state = CabinMultiZoneVoiceState(variant = variant.ifBlank { CabinMultiZoneVoiceState().variant }, ui = ui), modifier = modifier)
        "launcher-scaffold" -> CabinLauncherScaffold(state = CabinLauncherScaffoldState(variant = variant.ifBlank { CabinLauncherScaffoldState().variant }, ui = ui), modifier = modifier)
        "app-icon" -> CabinAppIcon(state = CabinAppIconState(variant = variant.ifBlank { CabinAppIconState().variant }, ui = ui), modifier = modifier)
        "recents-switcher" -> CabinRecentsSwitcher(state = CabinRecentsSwitcherState(variant = variant.ifBlank { CabinRecentsSwitcherState().variant }, ui = ui), modifier = modifier)
        "widget-host" -> CabinWidgetHost(state = CabinWidgetHostState(variant = variant.ifBlank { CabinWidgetHostState().variant }, ui = ui), modifier = modifier)
        "widget-picker" -> CabinWidgetPicker(state = CabinWidgetPickerState(variant = variant.ifBlank { CabinWidgetPickerState().variant }, ui = ui), modifier = modifier)
        "widget-edit-mode" -> CabinWidgetEditMode(state = CabinWidgetEditModeState(variant = variant.ifBlank { CabinWidgetEditModeState().variant }, ui = ui), modifier = modifier)
        "suggestion-card" -> CabinSuggestionCard(state = CabinSuggestionCardState(variant = variant.ifBlank { CabinSuggestionCardState().variant }, ui = ui), modifier = modifier)
        "shortcut-tile" -> CabinShortcutTile(state = CabinShortcutTileState(variant = variant.ifBlank { CabinShortcutTileState().variant }, ui = ui), modifier = modifier)
        "home-template" -> CabinHomeTemplate(state = CabinHomeTemplateState(variant = variant.ifBlank { CabinHomeTemplateState().variant }, ui = ui), modifier = modifier)
        "screensaver" -> CabinScreensaver(state = CabinScreensaverState(variant = variant.ifBlank { CabinScreensaverState().variant }, ui = ui), modifier = modifier)
        "welcome-screen" -> CabinWelcomeScreen(state = CabinWelcomeScreenState(variant = variant.ifBlank { CabinWelcomeScreenState().variant }, ui = ui), modifier = modifier)
        "wallpaper-picker" -> CabinWallpaperPicker(state = CabinWallpaperPickerState(variant = variant.ifBlank { CabinWallpaperPickerState().variant }, ui = ui), modifier = modifier)
        "quick-settings" -> CabinQuickSettings(state = CabinQuickSettingsState(variant = variant.ifBlank { CabinQuickSettingsState().variant }, ui = ui), modifier = modifier)
        "notification-center" -> CabinNotificationCenter(state = CabinNotificationCenterState(variant = variant.ifBlank { CabinNotificationCenterState().variant }, ui = ui), modifier = modifier)
        "heads-up-notification" -> CabinHeadsUpNotification(state = CabinHeadsUpNotificationState(variant = variant.ifBlank { CabinHeadsUpNotificationState().variant }, ui = ui), modifier = modifier)
        "volume-ui" -> CabinVolumeUi(state = CabinVolumeUiState(variant = variant.ifBlank { CabinVolumeUiState().variant }, ui = ui), modifier = modifier)
        "brightness-ui" -> CabinBrightnessUi(state = CabinBrightnessUiState(variant = variant.ifBlank { CabinBrightnessUiState().variant }, ui = ui), modifier = modifier)
        "clean-mode-overlay" -> CabinCleanModeOverlay(state = CabinCleanModeOverlayState(variant = variant.ifBlank { CabinCleanModeOverlayState().variant }, ui = ui), modifier = modifier)
        "shutdown-ui" -> CabinShutdownUi(state = CabinShutdownUiState(variant = variant.ifBlank { CabinShutdownUiState().variant }, ui = ui), modifier = modifier)
        "update-progress" -> CabinUpdateProgress(state = CabinUpdateProgressState(variant = variant.ifBlank { CabinUpdateProgressState().variant }, ui = ui), modifier = modifier)
        "display-mode-switcher" -> CabinDisplayModeSwitcher(state = CabinDisplayModeSwitcherState(variant = variant.ifBlank { CabinDisplayModeSwitcherState().variant }, ui = ui), modifier = modifier)
        "immersive-indicator" -> CabinImmersiveIndicator(state = CabinImmersiveIndicatorState(variant = variant.ifBlank { CabinImmersiveIndicatorState().variant }, ui = ui), modifier = modifier)
        "privacy-indicators" -> CabinPrivacyIndicators(state = CabinPrivacyIndicatorsState(variant = variant.ifBlank { CabinPrivacyIndicatorsState().variant }, ui = ui), modifier = modifier)
        "ecall-ui" -> CabinEcallUi(state = CabinEcallUiState(variant = variant.ifBlank { CabinEcallUiState().variant }, ui = ui), modifier = modifier)
        "profile-switcher" -> CabinProfileSwitcher(state = CabinProfileSwitcherState(variant = variant.ifBlank { CabinProfileSwitcherState().variant }, ui = ui), modifier = modifier)
        "profile-card" -> CabinProfileCard(state = CabinProfileCardState(variant = variant.ifBlank { CabinProfileCardState().variant }, ui = ui), modifier = modifier)
        "profile-create" -> CabinProfileCreate(state = CabinProfileCreateState(variant = variant.ifBlank { CabinProfileCreateState().variant }, ui = ui), modifier = modifier)
        "pin-pattern-password" -> CabinPinPatternPassword(state = CabinPinPatternPasswordState(variant = variant.ifBlank { CabinPinPatternPasswordState().variant }, ui = ui), modifier = modifier)
        "biometric-prompt" -> CabinBiometricPrompt(state = CabinBiometricPromptState(variant = variant.ifBlank { CabinBiometricPromptState().variant }, ui = ui), modifier = modifier)
        "onboarding-wizard" -> CabinOnboardingWizard(state = CabinOnboardingWizardState(variant = variant.ifBlank { CabinOnboardingWizardState().variant }, ui = ui), modifier = modifier)
        "eula-consent" -> CabinEulaConsent(state = CabinEulaConsentState(variant = variant.ifBlank { CabinEulaConsentState().variant }, ui = ui), modifier = modifier)
        "account-linking" -> CabinAccountLinking(state = CabinAccountLinkingState(variant = variant.ifBlank { CabinAccountLinkingState().variant }, ui = ui), modifier = modifier)
        "privacy-notice" -> CabinPrivacyNotice(state = CabinPrivacyNoticeState(variant = variant.ifBlank { CabinPrivacyNoticeState().variant }, ui = ui), modifier = modifier)
        "child-lock-notice" -> CabinChildLockNotice(state = CabinChildLockNoticeState(variant = variant.ifBlank { CabinChildLockNoticeState().variant }, ui = ui), modifier = modifier)
        "factory-reset-confirm" -> CabinFactoryResetConfirm(state = CabinFactoryResetConfirmState(variant = variant.ifBlank { CabinFactoryResetConfirmState().variant }, ui = ui), modifier = modifier)
        "passenger-scaffold" -> CabinPassengerScaffold(state = CabinPassengerScaffoldState(variant = variant.ifBlank { CabinPassengerScaffoldState().variant }, ui = ui), modifier = modifier)
        "rse-home" -> CabinRseHome(state = CabinRseHomeState(variant = variant.ifBlank { CabinRseHomeState().variant }, ui = ui), modifier = modifier)
        "zone-media" -> CabinZoneMedia(state = CabinZoneMediaState(variant = variant.ifBlank { CabinZoneMediaState().variant }, ui = ui), modifier = modifier)
        "zone-volume" -> CabinZoneVolume(state = CabinZoneVolumeState(variant = variant.ifBlank { CabinZoneVolumeState().variant }, ui = ui), modifier = modifier)
        "cross-display-handoff" -> CabinCrossDisplayHandoff(state = CabinCrossDisplayHandoffState(variant = variant.ifBlank { CabinCrossDisplayHandoffState().variant }, ui = ui), modifier = modifier)
        "occupant-zone-indicator" -> CabinOccupantZoneIndicator(state = CabinOccupantZoneIndicatorState(variant = variant.ifBlank { CabinOccupantZoneIndicatorState().variant }, ui = ui), modifier = modifier)
        "display-lock" -> CabinDisplayLock(state = CabinDisplayLockState(variant = variant.ifBlank { CabinDisplayLockState().variant }, ui = ui), modifier = modifier)
        "shared-content-pattern" -> CabinSharedContentPattern(state = CabinSharedContentPatternState(variant = variant.ifBlank { CabinSharedContentPatternState().variant }, ui = ui), modifier = modifier)
        "cluster-center-handoff" -> CabinClusterCenterHandoff(state = CabinClusterCenterHandoffState(variant = variant.ifBlank { CabinClusterCenterHandoffState().variant }, ui = ui), modifier = modifier)
        "camera-view" -> CabinCameraView(state = CabinCameraViewState(variant = variant.ifBlank { CabinCameraViewState().variant }, ui = ui), modifier = modifier)
        "view-selector" -> CabinViewSelector(state = CabinViewSelectorState(variant = variant.ifBlank { CabinViewSelectorState().variant }, ui = ui), modifier = modifier)
        "hitch-view" -> CabinHitchView(state = CabinHitchViewState(variant = variant.ifBlank { CabinHitchViewState().variant }, ui = ui), modifier = modifier)
        "parking-sensor" -> CabinParkingSensor(state = CabinParkingSensorState(variant = variant.ifBlank { CabinParkingSensorState().variant }, ui = ui), modifier = modifier)
        "auto-park-flow" -> CabinAutoParkFlow(state = CabinAutoParkFlowState(variant = variant.ifBlank { CabinAutoParkFlowState().variant }, ui = ui), modifier = modifier)
        "adas-status-chip" -> CabinAdasStatusChip(state = CabinAdasStatusChipState(variant = variant.ifBlank { CabinAdasStatusChipState().variant }, ui = ui), modifier = modifier)
        "driver-attention" -> CabinDriverAttention(state = CabinDriverAttentionState(variant = variant.ifBlank { CabinDriverAttentionState().variant }, ui = ui), modifier = modifier)
        "acc-controls" -> CabinAccControls(state = CabinAccControlsState(variant = variant.ifBlank { CabinAccControlsState().variant }, ui = ui), modifier = modifier)
        "blind-spot-alert" -> CabinBlindSpotAlert(state = CabinBlindSpotAlertState(variant = variant.ifBlank { CabinBlindSpotAlertState().variant }, ui = ui), modifier = modifier)
        "dashcam-controls" -> CabinDashcamControls(state = CabinDashcamControlsState(variant = variant.ifBlank { CabinDashcamControlsState().variant }, ui = ui), modifier = modifier)
        "recordings-list" -> CabinRecordingsList(state = CabinRecordingsListState(variant = variant.ifBlank { CabinRecordingsListState().variant }, ui = ui), modifier = modifier)
        "pane-divider" -> CabinPaneDivider(state = CabinPaneDividerState(variant = variant.ifBlank { CabinPaneDividerState().variant }, ui = ui), modifier = modifier)
        "collapsible-side-panel" -> CabinCollapsibleSidePanel(state = CabinCollapsibleSidePanelState(variant = variant.ifBlank { CabinCollapsibleSidePanelState().variant }, ui = ui), modifier = modifier)
        "adaptive-container" -> CabinAdaptiveContainer(state = CabinAdaptiveContainerState(variant = variant.ifBlank { CabinAdaptiveContainerState().variant }, ui = ui), modifier = modifier)
        "radial-gauge" -> CabinRadialGauge(state = CabinRadialGaugeState(variant = variant.ifBlank { CabinRadialGaugeState().variant }, ui = ui), modifier = modifier)
        "linear-gauge" -> CabinLinearGauge(state = CabinLinearGaugeState(variant = variant.ifBlank { CabinLinearGaugeState().variant }, ui = ui), modifier = modifier)
        "arc-gauge" -> CabinArcGauge(state = CabinArcGaugeState(variant = variant.ifBlank { CabinArcGaugeState().variant }, ui = ui), modifier = modifier)
        "segmented-gauge" -> CabinSegmentedGauge(state = CabinSegmentedGaugeState(variant = variant.ifBlank { CabinSegmentedGaugeState().variant }, ui = ui), modifier = modifier)
        "speedometer" -> CabinSpeedometer(state = CabinSpeedometerState(variant = variant.ifBlank { CabinSpeedometerState().variant }, ui = ui), modifier = modifier)
        "tachometer" -> CabinTachometer(state = CabinTachometerState(variant = variant.ifBlank { CabinTachometerState().variant }, ui = ui), modifier = modifier)
        "power-meter" -> CabinPowerMeter(state = CabinPowerMeterState(variant = variant.ifBlank { CabinPowerMeterState().variant }, ui = ui), modifier = modifier)
        "g-force" -> CabinGForce(state = CabinGForceState(variant = variant.ifBlank { CabinGForceState().variant }, ui = ui), modifier = modifier)
        "needle" -> CabinNeedle(state = CabinNeedleState(variant = variant.ifBlank { CabinNeedleState().variant }, ui = ui), modifier = modifier)
        "tick-scale" -> CabinTickScale(state = CabinTickScaleState(variant = variant.ifBlank { CabinTickScaleState().variant }, ui = ui), modifier = modifier)
        "gear-indicator" -> CabinGearIndicator(state = CabinGearIndicatorState(variant = variant.ifBlank { CabinGearIndicatorState().variant }, ui = ui), modifier = modifier)
        "digital-speed" -> CabinDigitalSpeed(state = CabinDigitalSpeedState(variant = variant.ifBlank { CabinDigitalSpeedState().variant }, ui = ui), modifier = modifier)
        "speed-limit-indicator" -> CabinSpeedLimitIndicator(state = CabinSpeedLimitIndicatorState(variant = variant.ifBlank { CabinSpeedLimitIndicatorState().variant }, ui = ui), modifier = modifier)
        "telltale-strip" -> CabinTelltaleStrip(state = CabinTelltaleStripState(variant = variant.ifBlank { CabinTelltaleStripState().variant }, ui = ui), modifier = modifier)
        "warning-overlay" -> CabinWarningOverlay(state = CabinWarningOverlayState(variant = variant.ifBlank { CabinWarningOverlayState().variant }, ui = ui), modifier = modifier)
        "cluster-info-panel" -> CabinClusterInfoPanel(state = CabinClusterInfoPanelState(variant = variant.ifBlank { CabinClusterInfoPanelState().variant }, ui = ui), modifier = modifier)
        "cluster-mode-switcher" -> CabinClusterModeSwitcher(state = CabinClusterModeSwitcherState(variant = variant.ifBlank { CabinClusterModeSwitcherState().variant }, ui = ui), modifier = modifier)
        "hud-primitive" -> CabinHudPrimitive(state = CabinHudPrimitiveState(variant = variant.ifBlank { CabinHudPrimitiveState().variant }, ui = ui), modifier = modifier)
        "cluster-theme-set" -> CabinClusterThemeSet(state = CabinClusterThemeSetState(variant = variant.ifBlank { CabinClusterThemeSetState().variant }, ui = ui), modifier = modifier)
        "focus-area" -> CabinFocusArea(state = CabinFocusAreaState(variant = variant.ifBlank { CabinFocusAreaState().variant }, ui = ui), modifier = modifier)
        else -> {}
    }
}
