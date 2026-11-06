# Security policy

Cabin shapes what drivers see and can touch while a vehicle is moving. We
treat two kinds of report as security issues:

- vulnerabilities in the code (for example, in the `CarUxRestrictions` adapter)
- **safety-gate bypasses**: any way for a Cabin component to allow an
  interaction that the Restriction Engine blocks, or to show a locked safety
  color (warning, error, charging) in a different color

## Reporting

Please **do not** open a public issue. Use GitHub's private vulnerability
reporting: **Security → Report a vulnerability** on this repository.

Include:

- the affected module and version or commit
- the steps to reproduce it
- the drive state involved (Parked, Idling, Moving or Restricted)

We aim to acknowledge reports within 5 business days.

## Supported versions

Cabin is pre-1.0. Fixes land on `main` and in the next release.
