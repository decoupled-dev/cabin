# Safety-critical UI

Some cabin controls and indicators are safety-adjacent: their failure modes,
latency, and ambiguity are unacceptable. Cabin encodes **deterministic
behavior** and **fail-safe defaults** for these patterns.

> Cabin does not claim certification against any specific automotive safety
> standard. It provides UI patterns and library defaults that programs can
> align with their safety processes.

## What counts as safety-critical (UI sense)

Examples (non-exhaustive):

- Hazard lights affordance (when presented in HMI)
- Defrost / critical visibility climate actions
- Parking brake / drive mode indicators that affect driver understanding
- Fault / warning telltales mirrored in center stack
- Charge faults that affect safe charging sessions

Informational EV range estimates are **not** automatically safety-critical;
**charge fault** banners may be. Component docs must classify explicitly
([components](../components/README.md)).

## Principles

1. **Determinism** — same inputs → same UI; no randomized motion or A/B on
   safety surfaces.
2. **Fail-safe defaults** — if vehicle signals are missing, stale, or
   contradictory, show a safe, obvious degraded state — never a blank control
   that looks “off” when it is “unknown.”
3. **Availability** — safety-critical actions remain reachable under driving
   restrictions unless physical hard-keys fully replace them (program decision).
4. **Clarity under stress** — high contrast, plain language, minimal steps.
5. **No dead ends** — never block safety UI behind login, paywalls, or setup.

## Unknown and stale state

| Signal condition | Required UI behavior |
| --- | --- |
| Never received | Explicit “unavailable” treatment; do not invent values |
| Stale beyond TTL | Degraded indicator; freeze last value only if labeled as stale |
| Contradictory | Prefer safe interpretation; surface warning |
| Transport error | Fault pattern; log for diagnostics |

### Planned API sketch

```kotlin
// Planned
sealed interface Signal<out T> {
    data class Value<T>(val value: T, val atMillis: Long) : Signal<T>
    data object Unavailable : Signal<Nothing>
    data class Stale<T>(val last: T, val atMillis: Long) : Signal<T>
    data class Fault(val code: String) : Signal<Nothing>
}
```

Components render `Signal` exhaustively — compile-time when using sealed types.

## Visual patterns

- **Warning / fault** uses semantic tokens reserved for safety meaning; OEM
  brand overlays must not remap them to decorative brand colors
  ([tokens](../design-language/tokens.md)).
- Prefer persistent banners over toast for faults that remain active.
- Use confirmation only when reversing a safety action could cause harm;
  keep confirmations single-step and large-target.

## Interaction rules

- Safety actions: immediate effect where platform allows; visible feedback
  within a tight latency budget (program-defined; UI must not add gratuitous
  delay).
- Debounce carefully — never drop hazard toggles via aggressive debounce.
- Dual-stack: Views and Compose must not disagree on whether a hazard is
  latched on.

## Testing expectations (future)

- Unit tests for all signal states
- Parity golden tests for fault/warning appearances
- Fault injection in samples

## Acceptance criteria

- [ ] Component classified (informational / convenience / safety-critical)
- [ ] Exhaustive handling of unavailable/stale/fault
- [ ] Not gated behind setup or driving denial without documented hard-key
      equivalent
- [ ] Semantic safety colors preserved under brand overlay
- [ ] Dual-stack parity for latched states

## Related

- [Compliance overview](README.md)
- [Driving restrictions](driving-restrictions.md)
- [Vehicle controls](../components/vehicle-controls.md)
- [HVAC](../components/hvac.md)
- [Principles](../principles.md)
