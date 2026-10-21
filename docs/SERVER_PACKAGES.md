# Native server packages

The release POC builds `peek-server` and `peek-server-nightly` for Debian 13 amd64
(`.deb`) and Alpine 3.24 x86_64 (`.apk`). These packages run Peek's web app, API and
background worker directly. The desktop DEB is a separate client application.
Docker is not a runtime dependency. CI uses isolated distribution containers to
build and test the packages.

Debian packages include the pinned Node runtime and use systemd. Alpine packages
use the distribution's `nodejs-current` package, version 26.5.1 or newer, and OpenRC.
Each distribution gets its own native dependency build. Both require Chromium,
CA certificates and Tini; the package manager installs their dependencies.

## Installer contract

A Community Scripts installer runs inside the created container. It can install a
verified release package, write configuration, and enable its service through the
normal distribution tools. See the upstream [installer authoring guide](https://community-scripts.org/docs/install/detailed_guide).
This POC does not add a script to the Community Scripts catalog.

1. Select one complete release and the server asset for the container's distribution
   and architecture. Use `release-manifest.json` and `SHA256SUMS` to verify its exact
   filename and digest before installation. Do not select the desktop DEB.
2. Install the DEB with `apt install` or the APK with `apk add`. For Alpine, establish
   trust in the release signing public key before installing; verify its fingerprint
   through an owner-controlled channel. A key downloaded beside a package is not an
   independent trust anchor. Do not bypass signature verification for public releases.
3. Configure `/etc/peek-server/server.env`. The file uses Node dotenv syntax; do not
   execute it as shell code. Keep its service-role key private. A fresh installation
   creates a disabled service and does not start an unconfigured server.
4. Provision the Supabase backend and apply the matching migrations as described in
   [Deployment](DEPLOYMENT.md). The package includes the matching SQL under
   `/usr/share/peek-server/migrations`. It does not provision Auth/PostgREST/Postgres,
   apply migrations, or restore a database.
5. Configure HTTPS through a reverse proxy, then enable and start the service. The
   default listener is `127.0.0.1:3000`; change `HOSTNAME` and `PORT` when the network
   design requires it. Verify `/health/live` for the process and `/health` for readiness.

For the stable service:

```sh
# Debian
sudo systemctl enable --now peek-server
sudo systemctl status peek-server

# Alpine, as root
rc-update add peek-server default
rc-service peek-server start
rc-service peek-server status
```

Nightly packages use `peek-server-nightly` for the service, user and directory names.
Stable and nightly share the default port; configure different ports to run both.

## Runtime configuration

Set `SUPABASE_URL` to the server's backend origin and `SUPABASE_PUBLIC_URL` to the
origin reachable by browsers. Set `SUPABASE_PUBLISHABLE_KEY` and the private
`SUPABASE_SERVICE_ROLE_KEY`. Configure auth providers in Supabase before enabling
their corresponding `NEXT_PUBLIC_AUTH_*` settings. Email-code sign-in is enabled
by default. Restart the service after changing configuration.

The server passes only the public auth settings and optional `NEXT_PUBLIC_IOS_APP_URL`
to the browser at request time. A prebuilt package can serve different instances
without rebuilding its JavaScript. `NEXT_PUBLIC_USE_API` is a build choice; server
packages are built in API mode. Browser configuration never includes the service-role
key, SMTP credentials or other backend secrets.

## Updates and removal

Use the same release manifest and checksum verification for upgrades. Back up the
backend and apply required migrations before installing a version that needs them.
Package upgrades preserve configuration and operator data, and restart only a service
that was already running. A package downgrade is not a database rollback.

Removing the package stops its service. Operator state remains under
`/var/lib/peek-server`, with cache and logs under `/var/cache/peek-server` and
`/var/log/peek-server`. Purging a Debian package removes its packaged configuration;
it does not delete the backend database or operator state.

Validate installation, restart, reboot, upgrades and removal in an unprivileged
Proxmox container before adding an installer to the catalog. Also validate backend
readiness, sign-in, tracking Chromium, HTTPS and shutdown on that host. Distribution
container tests do not establish Proxmox kernel or AppArmor compatibility.
