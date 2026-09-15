# Support matrix (planned)

Targets Cabin commits to for **v0.1 MVP**. Exact numbers finalize at first
library release; do not invent higher floors without an ADR.

## Android / AAOS

| Item | Planned v0.1 |
| --- | --- |
| Min Android API | **29** (Android 10) baseline for library `minSdk` — confirm against target AAOS programs at implementation |
| AAOS | Automotive images based on API 29+ that SystemUI partners care about |
| `compileSdk` / platform `sdk_version` | Match host tree; document per release ([soong](platforms/soong.md)) |

## Compose (planned; post-MVP for chrome)

| Item | Planned |
| --- | --- |
| Compose BOM | Pin a current stable BOM at `cabin-compose` introduction (TBD at that phase) |
| MVP requirement | **Not required** to ship System/Status bars |

## Build-tree targets (v0.1)

| Target | In v0.1? |
| --- | --- |
| SystemUI (status + system bars via `CabinViews`) | **Yes** |
| CarLauncher (thin Views chrome experiments) | Optional / partner |
| Platform media **screens** | **No** |
| Full HVAC / EV / vehicle-controls **screens** | **No** |
| Catalog / website apps on image | **No** |

## Tooling

| Item | Planned |
| --- | --- |
| Gradle | Version catalog pinned at implementation |
| Kotlin | Current stable aligned with Android Gradle Plugin of record |
| Soong | Host Android branch as documented in release notes |

## Related

- [MVP](mvp.md)
- [API contracts](api-contracts.md)
- [Build-tree](adoption/build-tree.md)
- [Testing](testing.md)
