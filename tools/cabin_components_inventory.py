"""Canonical Cabin component inventory (authoring source).

Dumped to components/cabin.components.yaml (JSON subset) by
tools/generate_cabin_components.py. Edit this list, then regenerate.
"""

from __future__ import annotations

# id|typeName|family|title|interaction|stacks|variants|telemetry|handwritten|module
_RAW = """
system-bar|SystemBar|systemui|System bar|NavigateSimple|compose,views||false|true|compose
status-bar|StatusBar|systemui|Status bar|Glance|compose,views||false|true|compose
climate-tile|ClimateTile|hvac|Climate tile|HvacAdjust|compose,views||true|true|compose
media-now-playing|MediaNowPlaying|media|Now playing|MediaTransport|compose,views||true|true|compose
button|Button|action|Button|NavigateSimple|compose,views|filled,tonal,outlined,text,icon|false|false|compose
fab|Fab|action|FAB|NavigateSimple|compose|standard,extended|false|false|compose
button-group|ButtonGroup|action|Button group|NavigateSimple|compose||false|false|compose
toggle-button|ToggleButton|action|Toggle button|NavigateSimple|compose||false|false|compose
segmented-button|SegmentedButton|action|Segmented button|NavigateSimple|compose||false|false|compose
split-button|SplitButton|action|Split button|NavigateSimple|compose||false|false|compose
long-press-button|LongPressButton|action|Long-press button|NavigateSimple|compose||false|false|compose
action-row|ActionRow|action|Rotary action row|NavigateSimple|compose||false|false|compose
hold-to-confirm|HoldToConfirmButton|action|Hold-to-confirm|VehicleAdjust|compose||false|false|compose
safety-action|SafetyActionButton|action|Safety-critical action|VehicleAdjust|compose||false|false|compose
press-hold-repeater|PressHoldRepeater|action|Press-and-hold repeater|NavigateSimple|compose,views||false|false|compose
switch|Switch|selection|Switch|NavigateSimple|compose,views||false|false|compose
checkbox|Checkbox|selection|Checkbox|NavigateSimple|compose||false|false|compose
radio|Radio|selection|Radio|NavigateSimple|compose||false|false|compose
radio-group|RadioGroup|selection|Radio group|NavigateSimple|compose||false|false|compose
chip|Chip|selection|Chip|FilterOrSort|compose|filter,input,assist,suggestion|false|false|compose
chip-group|ChipGroup|selection|Chip group|FilterOrSort|compose||false|false|compose
selection-card|SelectionCard|selection|Selection card|NavigateSimple|compose||false|false|compose
multi-select-toolbar|MultiSelectToolbar|selection|Multi-select toolbar|FilterOrSort|compose||false|false|compose
toggle-tile|ToggleTile|selection|Toggle tile|NavigateSimple|compose||false|false|compose
tri-state-toggle|TriStateToggle|selection|Tri-state toggle|NavigateSimple|compose||false|false|compose
multi-position-selector|MultiPositionSelector|selection|Multi-position selector|NavigateSimple|compose||false|false|compose
navigation-dock|NavigationDock|navigation|Navigation dock|NavigateSimple|compose,views||false|false|compose
navigation-rail|NavigationRail|navigation|Navigation rail|NavigateSimple|compose||false|false|compose
bottom-bar|BottomBar|navigation|Bottom bar|NavigateSimple|compose||false|false|compose
side-drawer|SideDrawer|navigation|Side drawer|OpenComplexApp|compose||false|false|compose
top-bar|TopBar|navigation|Top app bar|NavigateSimple|compose,views||false|false|compose
tabs|Tabs|navigation|Tabs|NavigateSimple|compose|fixed,scrollable,icon-only|false|false|compose
breadcrumbs|Breadcrumbs|navigation|Breadcrumbs|NavigateSimple|compose||false|false|compose
back-affordance|BackAffordance|navigation|Back / up|NavigateSimple|compose,views||false|false|compose
hardware-back-handler|HardwareBackHandler|navigation|Hardware back handler|NavigateSimple|compose||false|false|compose
page-indicator|PageIndicator|navigation|Page indicator|Glance|compose||false|false|compose
pager|Pager|navigation|Pager|NavigateSimple|compose||false|false|compose
carousel|Carousel|navigation|Carousel|NavigateSimple|compose||false|false|compose
wizard-stepper|WizardStepper|navigation|Wizard stepper|OpenComplexApp|compose||false|false|compose
deep-link-helper|DeepLinkHelper|navigation|Deep-link helper|StatusDeepLinkInformational|compose||false|false|compose
surface|Surface|surface|Surface|Glance|compose||false|false|compose
card|Card|surface|Card|Glance|compose|filled,outlined,elevated|false|false|compose
tile|Tile|surface|Tile|NavigateSimple|compose||false|false|compose
banner|Banner|surface|Banner|StatusDeepLinkInformational|compose||false|false|compose
panel|Panel|surface|Panel|Glance|compose||false|false|compose
section-container|SectionContainer|surface|Section container|Glance|compose||false|false|compose
expandable-container|ExpandableContainer|surface|Expandable container|NavigateSimple|compose||false|false|compose
accordion|Accordion|surface|Accordion|NavigateSimple|compose||false|false|compose
divider|Divider|surface|Divider|Glance|compose||false|false|compose
spacer|Spacer|surface|Spacer|Glance|compose||false|false|compose
scrim-overlay|ScrimOverlay|surface|Scrim|Glance|compose||false|false|compose
paged-scroll|PagedScroll|surface|Paged scroll|NavigateSimple|compose,views||false|false|compose
scroll-position-indicator|ScrollPositionIndicator|surface|Scroll position indicator|Glance|compose||false|false|compose
scroll-buttons|ScrollButtons|surface|Scroll buttons|NavigateSimple|compose||false|false|compose
sticky-header|StickyHeader|surface|Sticky header|Glance|compose||false|false|compose
pull-free-refresh|PullFreeRefresh|surface|Pull-free refresh|NavigateSimple|compose||false|false|compose
bottom-sheet|BottomSheet|surface|Bottom sheet|OpenComplexApp|compose||false|false|compose
side-sheet|SideSheet|surface|Side sheet|OpenComplexApp|compose||false|false|compose
modal-drawer|ModalDrawer|surface|Modal drawer|OpenComplexApp|compose||false|false|compose
list-item|ListItem|collection|List item|NavigateSimple|compose,views|one-line,two-line,three-line|false|false|compose
paged-list|PagedList|collection|Paged list|NavigateSimple|compose,views||false|false|compose
sectioned-list|SectionedList|collection|Sectioned list|NavigateSimple|compose||false|false|compose
grouped-list|GroupedList|collection|Grouped list|NavigateSimple|compose||false|false|compose
jump-list|JumpList|collection|Jump list|FilterOrSort|compose||false|false|compose
app-grid|AppGrid|collection|App grid|OpenComplexApp|compose||false|false|compose
media-grid|MediaGrid|collection|Media grid|MediaComplex|compose||false|false|compose
adaptive-grid|AdaptiveGrid|collection|Adaptive grid|NavigateSimple|compose||false|false|compose
data-table|DataTable|collection|Data table|OpenComplexApp|compose||false|false|compose
key-value-row|KeyValueRow|collection|Key-value row|Glance|compose||false|false|compose
info-row|InfoRow|collection|Info row|Glance|compose||false|false|compose
description-list|DescriptionList|collection|Description list|Glance|compose||false|false|compose
timeline-list|TimelineList|collection|Timeline list|Glance|compose||false|false|compose
filter-bar|FilterBar|collection|Filter bar|FilterOrSort|compose||false|false|compose
sort-control|SortControl|collection|Sort control|FilterOrSort|compose||false|false|compose
search-result-list|SearchResultList|collection|Search result list|OpenComplexApp|compose||false|false|compose
empty-state|EmptyState|collection|Empty state|Glance|compose||false|false|compose
error-state|ErrorState|collection|Error state|Glance|compose||false|false|compose
loading-collection|LoadingCollection|collection|Loading collection|Glance|compose||false|false|compose
text-field|TextField|input|Text field|OpenKeyboard|compose|filled,outlined|false|false|compose
search-field|SearchField|input|Search field|OpenKeyboard|compose||false|false|compose
pin-field|PinField|input|PIN field|OpenKeyboard|compose||false|false|compose
otp-field|OtpField|input|OTP field|OpenKeyboard|compose||false|false|compose
multiline-text|MultilineText|input|Multi-line text|OpenKeyboard|compose||false|false|compose
autocomplete|Autocomplete|input|Autocomplete|OpenKeyboard|compose||false|false|compose
masked-input|MaskedInput|input|Masked input|OpenKeyboard|compose||false|false|compose
numeric-keypad|NumericKeypad|input|Numeric keypad|OpenKeyboard|compose||false|false|compose
dial-pad|DialPad|input|Dial pad|OpenKeyboard|compose||false|false|compose
rotary-text-entry|RotaryTextEntry|input|Rotary text entry|OpenKeyboard|compose||false|false|compose
slider|Slider|input|Slider|NavigateSimple|compose,views||false|false|compose
range-slider|RangeSlider|input|Range slider|NavigateSimple|compose||false|false|compose
stepper|Stepper|input|Stepper|NavigateSimple|compose,views||false|false|compose
number-picker|NumberPicker|input|Number picker|NavigateSimple|compose||false|false|compose
dial-knob|DialKnob|input|Dial / knob|NavigateSimple|compose||false|false|compose
date-picker|DatePicker|input|Date picker|OpenComplexApp|compose||false|false|compose
time-picker|TimePicker|input|Time picker|OpenComplexApp|compose||false|false|compose
duration-picker|DurationPicker|input|Duration picker|OpenComplexApp|compose||false|false|compose
dropdown|Dropdown|input|Dropdown|OpenComplexApp|compose||false|false|compose
list-picker|ListPicker|input|List picker|OpenComplexApp|compose||false|false|compose
color-picker|ColorPicker|input|Color picker|OpenComplexApp|compose||false|false|compose
wheel-picker|WheelPicker|input|Wheel picker|NavigateSimple|compose||false|false|compose
option-picker|OptionPicker|input|Option picker|NavigateSimple|compose||false|false|compose
voice-mic-button|VoiceMicButton|input|Voice mic button|NavigateSimple|compose||false|false|compose
listening-indicator|ListeningIndicator|input|Listening indicator|Glance|compose||false|false|compose
transcription-field|TranscriptionField|input|Transcription field|OpenKeyboard|compose||false|false|compose
rating-input|RatingInput|input|Rating input|NavigateSimple|compose||false|false|compose
gesture-pad|GesturePad|input|Gesture pad|NavigateSimple|compose||false|false|compose
dialog|Dialog|feedback|Dialog|OpenComplexApp|compose,views|alert,confirmation,list,input|false|false|compose
snackbar|Snackbar|feedback|Snackbar|Glance|compose||false|false|compose
toast|Toast|feedback|Toast|Glance|compose||false|false|compose
inline-message|InlineMessage|feedback|Inline message|Glance|compose||false|false|compose
callout|Callout|feedback|Callout|Glance|compose||false|false|compose
linear-progress|LinearProgress|feedback|Linear progress|Glance|compose||false|false|compose
circular-progress|CircularProgress|feedback|Circular progress|Glance|compose||false|false|compose
skeleton|Skeleton|feedback|Skeleton|Glance|compose||false|false|compose
step-progress|StepProgress|feedback|Step progress|Glance|compose||false|false|compose
context-menu|ContextMenu|feedback|Context menu|OpenComplexApp|compose||false|false|compose
tooltip|Tooltip|feedback|Tooltip|Glance|compose||false|false|compose
offline-state|OfflineState|feedback|Offline state|Glance|compose||false|false|compose
no-permission-state|NoPermissionState|feedback|No-permission state|Glance|compose||false|false|compose
driving-restricted-state|DrivingRestrictedState|feedback|Driving-restricted state|ParkedOnly|compose||false|false|compose
badge|Badge|feedback|Badge|Glance|compose||false|false|compose
status-pill|StatusPill|feedback|Status pill|Glance|compose||false|false|compose
tag|Tag|feedback|Tag|Glance|compose||false|false|compose
splash-screen|SplashScreen|feedback|Splash / loading screen|Glance|compose||false|false|compose
preference|Preference|settings|Preference row|StatusDeepLinkSettings|compose,views|switch,checkbox,list,slider,edit-text|false|false|compose
preference-scaffold|PreferenceScaffold|settings|Two-pane settings|StatusDeepLinkSettings|compose,views||false|false|compose
settings-search|SettingsSearch|settings|Settings search|OpenKeyboard|compose||false|false|compose
settings-tile|SettingsTile|settings|Settings tile|StatusDeepLinkSettings|compose||false|false|compose
settings-homepage|SettingsHomepage|settings|Settings homepage|StatusDeepLinkSettings|compose||false|false|compose
mini-player|MiniPlayer|media|Mini player|MediaTransport|compose||false|false|compose
dock-player|DockPlayer|media|Dock player|MediaTransport|compose||false|false|compose
transport-controls|TransportControls|media|Transport controls|MediaTransport|compose||false|false|compose
media-seek-bar|MediaSeekBar|media|Media seek bar|MediaComplex|compose||false|false|compose
shuffle-repeat|ShuffleRepeat|media|Shuffle / repeat|MediaTransport|compose||false|false|compose
rating-like|RatingLike|media|Rating / like|MediaTransport|compose||false|false|compose
album-art|AlbumArt|media|Album art|Glance|compose||false|false|compose
queue-list|QueueList|media|Queue list|MediaComplex|compose||false|false|compose
browse-tree|BrowseTree|media|Browse tree|MediaComplex|compose||false|false|compose
source-switcher|SourceSwitcher|media|Source switcher|MediaComplex|compose||false|false|compose
audio-zone-selector|AudioZoneSelector|media|Audio-zone selector|MediaComplex|compose||false|false|compose
volume-panel|VolumePanel|media|Volume panel|MediaTransport|compose||false|false|compose
fade-balance-panel|FadeBalancePanel|media|Fade / balance panel|OpenComplexApp|compose||false|false|compose
eq-controls|EqControls|media|EQ controls|OpenComplexApp|compose||false|false|compose
audio-visualizer|AudioVisualizer|media|Audio visualizer|Glance|compose||false|false|compose
radio-tuner|RadioTuner|media|Radio tuner|MediaTransport|compose||false|false|compose
radio-presets|RadioPresets|media|Radio presets|MediaTransport|compose||false|false|compose
station-list|StationList|media|Station list|MediaComplex|compose||false|false|compose
hd-metadata-card|HdMetadataCard|media|HD / DAB metadata|Glance|compose||false|false|compose
podcast-controls|PodcastControls|media|Podcast controls|MediaComplex|compose||false|false|compose
lyrics-view|LyricsView|media|Lyrics view|ParkedOnly|compose||false|false|compose
video-player-chrome|VideoPlayerChrome|media|Video player chrome|ParkedOnly|compose||false|false|compose
rear-seat-media-controls|RearSeatMediaControls|media|Rear-seat media controls|MediaTransport|compose||false|false|compose
temperature-control|TemperatureControl|hvac|Temperature control|HvacAdjust|compose||false|false|compose
fan-speed|FanSpeed|hvac|Fan speed|HvacAdjust|compose||false|false|compose
airflow-direction|AirflowDirection|hvac|Airflow direction|HvacAdjust|compose||false|false|compose
zone-selector|ZoneSelector|hvac|Zone selector|HvacPeek|compose||false|false|compose
seat-climate|SeatClimate|hvac|Seat heat / vent|HvacAdjust|compose||false|false|compose
steering-wheel-heat|SteeringWheelHeat|hvac|Steering wheel heat|HvacAdjust|compose||false|false|compose
mirror-heat|MirrorHeat|hvac|Mirror heat|HvacAdjust|compose||false|false|compose
defrost-toggle|DefrostToggle|hvac|Defrost|HvacPeek|compose||false|false|compose
recirc-toggle|RecircToggle|hvac|Recirculation|HvacAdjust|compose||false|false|compose
auto-ac-toggle|AutoAcToggle|hvac|Auto / A/C|HvacAdjust|compose||false|false|compose
eco-toggle|EcoToggle|hvac|Eco climate|HvacAdjust|compose||false|false|compose
air-quality-indicator|AirQualityIndicator|hvac|Air quality|Glance|compose||false|false|compose
fragrance-control|FragranceControl|hvac|Fragrance|HvacAdjust|compose||false|false|compose
preconditioning-scheduler|PreconditioningScheduler|hvac|Pre-conditioning scheduler|ParkedOnly|compose||false|false|compose
ambient-lighting|AmbientLighting|hvac|Ambient lighting|OpenComplexApp|compose||false|false|compose
sunroof-control|SunroofControl|hvac|Sunroof / sunshade|VehicleAdjust|compose||false|false|compose
hvac-overlay|HvacOverlay|hvac|HVAC overlay|HvacPeek|compose||false|false|compose
persistent-hvac-bar|PersistentHvacBar|hvac|Persistent HVAC bar|HvacPeek|compose||false|false|compose
door-control|DoorControl|vehicle|Door control|VehicleAdjust|compose||false|false|compose
window-control|WindowControl|vehicle|Window control|VehicleAdjust|compose||false|false|compose
trunk-control|TrunkControl|vehicle|Trunk / frunk|VehicleAdjust|compose||false|false|compose
lock-control|LockControl|vehicle|Lock control|VehicleAdjust|compose||false|false|compose
mirror-control|MirrorControl|vehicle|Mirror control|VehicleAdjust|compose||false|false|compose
seat-memory|SeatMemory|vehicle|Seat memory|VehicleAdjust|compose||false|false|compose
charge-port-door|ChargePortDoor|vehicle|Charge-port door|VehicleAdjust|compose||false|false|compose
wiper-control|WiperControl|vehicle|Wiper control|VehicleAdjust|compose||false|false|compose
light-control|LightControl|vehicle|Light control|VehicleAdjust|compose||false|false|compose
drive-mode-selector|DriveModeSelector|vehicle|Drive-mode selector|VehicleAdjust|compose||false|false|compose
regen-selector|RegenSelector|vehicle|Regen-level selector|VehicleAdjust|compose||false|false|compose
suspension-control|SuspensionControl|vehicle|Suspension / ride height|VehicleAdjust|compose||false|false|compose
steering-feel|SteeringFeel|vehicle|Steering feel|VehicleAdjust|compose||false|false|compose
traction-control|TractionControl|vehicle|Traction control|VehicleAdjust|compose||false|false|compose
telltale-set|TelltaleSet|vehicle|Telltale set|Glance|compose||false|false|compose
warning-list|WarningList|vehicle|Warning list|Glance|compose||false|false|compose
service-reminder|ServiceReminder|vehicle|Service reminder|Glance|compose||false|false|compose
vehicle-open-map|VehicleOpenMap|vehicle|Vehicle-open map|Glance|compose||false|false|compose
tire-pressure|TirePressure|vehicle|Tire pressure|Glance|compose||true|false|compose
fluid-levels|FluidLevels|vehicle|Fluid levels|Glance|compose||true|false|compose
battery-12v|Battery12v|vehicle|12V battery|Glance|compose||true|false|compose
trip-computer|TripComputer|vehicle|Trip computer|Glance|compose||true|false|compose
consumption-graph|ConsumptionGraph|vehicle|Consumption graph|Glance|compose||true|false|compose
eco-score|EcoScore|vehicle|Eco-score|Glance|compose||true|false|compose
vehicle-model-view|VehicleModelView|vehicle|Vehicle model view|ParkedOnly|compose||false|false|compose
confirm-safety-action|ConfirmSafetyAction|vehicle|Confirm safety action|VehicleAdjust|compose||false|false|compose
digital-key|DigitalKey|vehicle|Digital key|ParkedOnly|compose||false|false|compose
valet-mode|ValetMode|vehicle|Valet mode|ParkedOnly|compose||false|false|compose
guest-mode|GuestMode|vehicle|Guest mode|ParkedOnly|compose||false|false|compose
range-estimator|RangeEstimator|ev|Range estimator|Glance|compose||true|false|compose
range-on-map|RangeOnMap|ev|Range on map|Glance|compose||false|false|compose
charge-session-card|ChargeSessionCard|ev|Charge session card|Glance|compose||true|false|compose
charge-limit-slider|ChargeLimitSlider|ev|Charge limit slider|VehicleAdjust|compose||false|false|compose
charging-schedule|ChargingSchedule|ev|Charging schedule|ParkedOnly|compose||false|false|compose
charge-port-status|ChargePortStatus|ev|Charge-port status|Glance|compose||true|false|compose
charging-station-card|ChargingStationCard|ev|Charging-station card|Glance|compose||false|false|compose
connector-type-chip|ConnectorTypeChip|ev|Connector-type chip|Glance|compose||false|false|compose
energy-flow-diagram|EnergyFlowDiagram|ev|Energy flow diagram|Glance|compose||true|false|compose
consumption-chart|ConsumptionChart|ev|Consumption chart|Glance|compose||true|false|compose
regen-indicator|RegenIndicator|ev|Regen indicator|Glance|compose||true|false|compose
charge-precondition|ChargePrecondition|ev|Precondition for charging|HvacAdjust|compose||false|false|compose
trip-charge-planner|TripChargePlanner|ev|Trip charge planner|ParkedOnly|compose||false|false|compose
v2x-controls|V2xControls|ev|V2L / V2H controls|VehicleAdjust|compose||false|false|compose
fuel-level|FuelLevel|ev|Fuel level|Glance|compose||true|false|compose
hybrid-range-breakdown|HybridRangeBreakdown|ev|Hybrid range breakdown|Glance|compose||true|false|compose
map-container|MapContainer|nav|Map container|Glance|compose||false|false|compose
map-controls|MapControls|nav|Map controls|NavigateSimple|compose||false|false|compose
route-card|RouteCard|nav|Route card|Glance|compose||false|false|compose
eta-panel|EtaPanel|nav|ETA panel|Glance|compose||false|false|compose
turn-instruction|TurnInstruction|nav|Turn instruction|Glance|compose||false|false|compose
lane-guidance|LaneGuidance|nav|Lane guidance|Glance|compose||false|false|compose
junction-view|JunctionView|nav|Junction view|Glance|compose||false|false|compose
speed-limit|NavSpeedLimit|nav|Speed-limit indicator|Glance|compose||false|false|compose
nav-search-bar|NavSearchBar|nav|Navigation search|OpenKeyboard|compose||false|false|compose
place-card|PlaceCard|nav|Place card|Glance|compose||false|false|compose
favorites-list|FavoritesList|nav|Favorites / recents|NavigateSimple|compose||false|false|compose
poi-chips|PoiChips|nav|POI category chips|FilterOrSort|compose||false|false|compose
arrival-panel|ArrivalPanel|nav|Arrival panel|Glance|compose||false|false|compose
route-options|RouteOptions|nav|Route options|OpenComplexApp|compose||false|false|compose
traffic-indicator|TrafficIndicator|nav|Traffic indicator|Glance|compose||false|false|compose
waypoint-list|WaypointList|nav|Waypoint list|OpenComplexApp|compose||false|false|compose
trip-summary|TripSummary|nav|Trip summary|OpenComplexApp|compose||false|false|compose
parking-availability|ParkingAvailability|nav|Parking availability|Glance|compose||false|false|compose
nav-widget|NavWidget|nav|Nav widget|Glance|compose||false|false|compose
dialer|Dialer|comms|Dialer|OpenKeyboard|compose||false|false|compose
in-call-screen|InCallScreen|comms|In-call screen|NavigateSimple|compose||false|false|compose
incoming-call-hud|IncomingCallHud|comms|Incoming-call HUD|Glance|compose||false|false|compose
call-controls|CallControls|comms|Call controls|NavigateSimple|compose||false|false|compose
contact-list|ContactList|comms|Contact list|OpenComplexApp|compose||false|false|compose
contact-card|ContactCard|comms|Contact card|Glance|compose||false|false|compose
conversation-list|ConversationList|comms|Conversation list|OpenComplexApp|compose||false|false|compose
message-bubble|MessageBubble|comms|Message bubble|Glance|compose||false|false|compose
voice-reply|VoiceReply|comms|Voice reply|NavigateSimple|compose||false|false|compose
canned-reply-chips|CannedReplyChips|comms|Canned reply chips|NavigateSimple|compose||false|false|compose
bluetooth-pairing|BluetoothPairing|comms|Bluetooth pairing|ParkedOnly|compose||false|false|compose
device-list|DeviceList|comms|Device list|OpenComplexApp|compose||false|false|compose
projection-status|ProjectionStatus|comms|Projection status|Glance|compose||false|false|compose
voicemail|Voicemail|comms|Voicemail|ParkedOnly|compose||false|false|compose
assistant-surface|AssistantSurface|voice|Assistant surface|NavigateSimple|compose||false|false|compose
invocation-button|InvocationButton|voice|Invocation button|NavigateSimple|compose||false|false|compose
mic-privacy-indicator|MicPrivacyIndicator|voice|Mic privacy indicator|Glance|compose||false|false|compose
suggestion-chips|SuggestionChips|voice|Voice suggestion chips|NavigateSimple|compose||false|false|compose
you-can-say-bar|YouCanSayBar|voice|You-can-say bar|Glance|compose||false|false|compose
voice-results-card|VoiceResultsCard|voice|Voice results card|Glance|compose||false|false|compose
confirm-cancel|VoiceConfirmCancel|voice|Voice confirm / cancel|NavigateSimple|compose||false|false|compose
barge-in-indicator|BargeInIndicator|voice|Barge-in indicator|Glance|compose||false|false|compose
multi-zone-voice|MultiZoneVoice|voice|Multi-zone voice|Glance|compose||false|false|compose
launcher-scaffold|LauncherScaffold|launcher|Launcher scaffold|OpenComplexApp|compose||false|false|compose
app-icon|AppIcon|launcher|App icon|OpenComplexApp|compose||false|false|compose
recents-switcher|RecentsSwitcher|launcher|Recents / task switcher|OpenComplexApp|compose||false|false|compose
widget-host|WidgetHost|launcher|Widget host|NavigateSimple|compose||false|false|compose
widget-picker|WidgetPicker|launcher|Widget picker|ParkedOnly|compose||false|false|compose
widget-edit-mode|WidgetEditMode|launcher|Widget edit mode|ParkedOnly|compose||false|false|compose
suggestion-card|SuggestionCard|launcher|Suggestion card|Glance|compose||false|false|compose
shortcut-tile|ShortcutTile|launcher|Shortcut tile|NavigateSimple|compose||false|false|compose
home-template|HomeTemplate|launcher|Home template|Glance|compose||false|false|compose
screensaver|Screensaver|launcher|Screensaver / idle|Glance|compose||false|false|compose
welcome-screen|WelcomeScreen|launcher|Welcome screen|Glance|compose||false|false|compose
wallpaper-picker|WallpaperPicker|launcher|Wallpaper picker|ParkedOnly|compose||false|false|compose
quick-settings|QuickSettings|systemui|Quick settings|StatusDeepLinkSettings|compose,views||false|false|compose
notification-center|NotificationCenter|systemui|Notification center|OpenComplexApp|compose||false|false|compose
heads-up-notification|HeadsUpNotification|systemui|Heads-up notification|Glance|compose||false|false|compose
volume-ui|VolumeUi|systemui|Volume UI|MediaTransport|compose||false|false|compose
brightness-ui|BrightnessUi|systemui|Brightness UI|NavigateSimple|compose||false|false|compose
clean-mode-overlay|CleanModeOverlay|systemui|Clean-mode overlay|NavigateSimple|compose||false|false|compose
shutdown-ui|ShutdownUi|systemui|Power / shutdown UI|ParkedOnly|compose||false|false|compose
update-progress|UpdateProgress|systemui|Update in progress|Glance|compose||false|false|compose
display-mode-switcher|DisplayModeSwitcher|systemui|Display-mode switcher|NavigateSimple|compose||false|false|compose
immersive-indicator|ImmersiveIndicator|systemui|Immersive-mode indicator|Glance|compose||false|false|compose
privacy-indicators|PrivacyIndicators|systemui|Privacy indicators|Glance|compose||false|false|compose
ecall-ui|EcallUi|systemui|Emergency / eCall|VehicleAdjust|compose||false|false|compose
profile-switcher|ProfileSwitcher|user|Profile switcher|ParkedOnly|compose||false|false|compose
profile-card|ProfileCard|user|Profile card|Glance|compose||false|false|compose
profile-create|ProfileCreate|user|Profile creation|ParkedOnly|compose||false|false|compose
pin-pattern-password|PinPatternPassword|user|PIN / pattern / password|ParkedOnly|compose||false|false|compose
biometric-prompt|BiometricPrompt|user|Biometric prompt|ParkedOnly|compose||false|false|compose
onboarding-wizard|OnboardingWizard|user|Onboarding wizard|ParkedOnly|compose||false|false|compose
eula-consent|EulaConsent|user|EULA / consent|ParkedOnly|compose||false|false|compose
account-linking|AccountLinking|user|Account linking|ParkedOnly|compose||false|false|compose
privacy-notice|PrivacyNotice|user|Privacy notice|ParkedOnly|compose||false|false|compose
child-lock-notice|ChildLockNotice|user|Child-lock notice|Glance|compose||false|false|compose
factory-reset-confirm|FactoryResetConfirm|user|Factory reset confirm|ParkedOnly|compose||false|false|compose
passenger-scaffold|PassengerScaffold|rse|Passenger scaffold|OpenComplexApp|compose||false|false|compose
rse-home|RseHome|rse|Rear-seat home|OpenComplexApp|compose||false|false|compose
zone-media|ZoneMedia|rse|Per-zone media|MediaTransport|compose||false|false|compose
zone-volume|ZoneVolume|rse|Per-zone volume|MediaTransport|compose||false|false|compose
cross-display-handoff|CrossDisplayHandoff|rse|Cross-display handoff|NavigateSimple|compose||false|false|compose
occupant-zone-indicator|OccupantZoneIndicator|rse|Occupant-zone indicator|Glance|compose||false|false|compose
display-lock|DisplayLock|rse|Display lock|VehicleAdjust|compose||false|false|compose
shared-content-pattern|SharedContentPattern|rse|Shared vs personal content|Glance|compose||false|false|compose
cluster-center-handoff|ClusterCenterHandoff|rse|Cluster-to-center handoff|NavigateSimple|compose||false|false|compose
camera-view|CameraView|adas|Camera view|Glance|compose||false|false|compose
view-selector|ViewSelector|adas|Camera view selector|NavigateSimple|compose||false|false|compose
hitch-view|HitchView|adas|Hitch / trailer view|Glance|compose||false|false|compose
parking-sensor|ParkingSensor|adas|Parking sensor|Glance|compose||false|false|compose
auto-park-flow|AutoParkFlow|adas|Auto-park flow|VehicleAdjust|compose||false|false|compose
adas-status-chip|AdasStatusChip|adas|ADAS status chip|Glance|compose||false|false|compose
driver-attention|DriverAttention|adas|Driver-attention indicator|Glance|compose||false|false|compose
acc-controls|AccControls|adas|Adaptive-cruise controls|VehicleAdjust|compose||false|false|compose
blind-spot-alert|BlindSpotAlert|adas|Blind-spot alert|Glance|compose||false|false|compose
dashcam-controls|DashcamControls|adas|Dashcam controls|ParkedOnly|compose||false|false|compose
recordings-list|RecordingsList|adas|Recordings list|ParkedOnly|compose||false|false|compose
pane-divider|PaneDivider|layout|Pane divider|NavigateSimple|compose||false|false|compose
collapsible-side-panel|CollapsibleSidePanel|layout|Collapsible side panel|NavigateSimple|compose||false|false|compose
adaptive-container|AdaptiveContainer|layout|Adaptive container|Glance|compose||false|false|compose
radial-gauge|RadialGauge|gauges|Radial gauge|Glance|compose||true|false|gauges
linear-gauge|LinearGauge|gauges|Linear gauge|Glance|compose||true|false|gauges
arc-gauge|ArcGauge|gauges|Arc gauge|Glance|compose||true|false|gauges
segmented-gauge|SegmentedGauge|gauges|Segmented gauge|Glance|compose||true|false|gauges
speedometer|Speedometer|gauges|Speedometer|Glance|compose||true|false|gauges
tachometer|Tachometer|gauges|Tachometer|Glance|compose||true|false|gauges
power-meter|PowerMeter|gauges|Power meter|Glance|compose||true|false|gauges
g-force|GForce|gauges|G-force display|Glance|compose||true|false|gauges
needle|Needle|gauges|Needle primitive|Glance|compose||false|false|gauges
tick-scale|TickScale|gauges|Tick / scale primitive|Glance|compose||false|false|gauges
gear-indicator|GearIndicator|gauges|Gear indicator|Glance|compose||false|false|gauges
digital-speed|DigitalSpeed|gauges|Digital speed|Glance|compose||true|false|gauges
speed-limit-indicator|SpeedLimitIndicator|gauges|Speed-limit indicator|Glance|compose||false|false|gauges
telltale-strip|TelltaleStrip|gauges|Telltale strip|Glance|compose||false|false|gauges
warning-overlay|WarningOverlay|gauges|Warning overlay|Glance|compose||false|false|gauges
cluster-info-panel|ClusterInfoPanel|gauges|Cluster info panel|Glance|compose||false|false|gauges
cluster-mode-switcher|ClusterModeSwitcher|gauges|Cluster mode switcher|NavigateSimple|compose||false|false|gauges
hud-primitive|HudPrimitive|gauges|HUD primitive|Glance|compose||false|false|gauges
cluster-theme-set|ClusterThemeSet|gauges|Cluster theme set|Glance|compose||false|false|gauges
focus-area|FocusArea|navigation|Focus area|NavigateSimple|compose,views||false|false|compose
"""


def load_inventory() -> list[dict]:
    items: list[dict] = []
    seen: set[str] = set()
    for raw_line in _RAW.strip().splitlines():
        line = raw_line.strip()
        if not line or line.startswith("#"):
            continue
        parts = line.split("|")
        if len(parts) != 10:
            raise ValueError(f"Bad inventory row ({len(parts)} cols): {line}")
        (
            ident,
            type_name,
            family,
            title,
            interaction,
            stacks,
            variants,
            telemetry,
            handwritten,
            module,
        ) = parts
        if ident in seen:
            raise ValueError(f"Duplicate component id: {ident}")
        seen.add(ident)
        items.append(
            {
                "id": ident,
                "typeName": type_name,
                "family": family,
                "title": title,
                "interaction": interaction,
                "stacks": [s for s in stacks.split(",") if s],
                "variants": [v for v in variants.split(",") if v],
                "telemetry": telemetry.lower() == "true",
                "handwritten": handwritten.lower() == "true",
                "module": module,
            }
        )
    return items
