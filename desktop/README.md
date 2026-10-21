# Peek desktop POC

A desktop client for an existing Peek server. It loads the server’s web UI; it does not install or start the server. Email sign-in stays in that UI. Provider sign-in (including Google, Apple and SSO) is unsupported in this preview: the external browser does not share the desktop session. Provider-only servers require the browser.

Use Node 24 or newer:

```sh
npm --prefix desktop ci
npm --prefix desktop start
```

Enter the HTTPS origin of your server, without a path. Change it from the Peek menu or the window header. The app saves the origin locally and keeps each server’s browser session separate. Stable and nightly installations use separate application identities and settings directories.

The remote UI has no preload or desktop API, runs sandboxed, and cannot navigate the app to another origin. External HTTPS links ask before opening in the browser. Other schemes, downloads and device permissions are blocked. Authentication remains the existing server’s responsibility.

```sh
npm --prefix desktop run typecheck
npm --prefix desktop run lint
npm --prefix desktop test
npm --prefix desktop run test:smoke
```

The smoke test launches Electron against a synthetic loopback fixture. HTTP loopback requires `PEEK_ALLOW_LOOPBACK=1` and works only in an unpackaged development app. `PEEK_TEST_PROFILE` selects an isolated development profile.

For installer targets, release metadata and signing configuration, see [release packaging](../docs/RELEASING.md). Packaging never publishes. Local candidates carry `UNSIGNED` in their filenames. There is no automatic updater.
