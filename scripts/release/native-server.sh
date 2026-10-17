#!/usr/bin/env bash
set -euo pipefail

stage="${1:?Expected build, package or smoke}"
format="${2:?Expected deb or apk}"
case "$stage" in build|package|smoke) ;; *) exit 2 ;; esac
case "$format" in
  deb) image=debian:trixie-slim@sha256:a29215f6a35e51e22adffa17f89e9d2ef06214e64a2bad10d765c46aea49f11f ;;
  apk) image=alpine:3.24@sha256:294b683cb724975bec92580e1e685676bd4b50bda910ddb8c51d4cabeaec77e6 ;;
  *) exit 2 ;;
esac
: "${RUNNER_TEMP:?An isolated runner temporary directory is required}"
repository="$(git rev-parse --show-toplevel)"
work="$RUNNER_TEMP/peek-native-$format"
if [ "$stage" = build ]; then
  test ! -e "$work/source" || { echo 'Native build workspace must be fresh' >&2; exit 1; }
  mkdir -p "$work/tools" "$work/output"
  curl --fail --silent --show-error --location --retry 2 \
    https://github.com/goreleaser/nfpm/releases/download/v2.47.0/nfpm_2.47.0_Linux_x86_64.tar.gz \
    --output "$work/tools/nfpm.tar.gz"
  printf '%s  %s\n' 0660ca602b2d2d2ae4781a06c692b3eeb9d437ffea05b831d76e41f4a3188783 "$work/tools/nfpm.tar.gz" | sha256sum --check -
  tar -C "$work/tools" -xzf "$work/tools/nfpm.tar.gz" nfpm
  if [ "$format" = deb ]; then
    curl --fail --silent --show-error --location --retry 2 \
      https://nodejs.org/dist/v26.12.0/node-v26.12.0-linux-x64.tar.xz \
      --output "$work/tools/node.tar.xz"
    printf '%s  %s\n' fd47865ecd6391ebb431fc815e01b9135bf83ded5cf373247e9331e196b7aded "$work/tools/node.tar.xz" | sha256sum --check -
    tar -C "$work/tools" -xJf "$work/tools/node.tar.xz"
  fi
fi

mounts=(--volume "$repository:/source:ro" --volume "$work:/work")
if [ "$stage" = package ] && [ "$format" = apk ]; then
  mounts+=(--volume "$RUNNER_TEMP/peek-apk-keys:/keys:ro")
fi
# Containers isolate the CI build/install tests. The resulting packages contain
# native services and have no Docker dependency on the installed machine.
docker run --rm --platform linux/amd64 "${mounts[@]}" \
  --env PEEK_RELEASE_VERSION --env PEEK_RELEASE_CHANNEL --env PEEK_RELEASE_SHA --env PEEK_BUILD_NUMBER \
  "$image" sh /source/scripts/release/native-container.sh "$stage" "$format"
if [ "$stage" = smoke ]; then
  mkdir -p server-dist
  for path in "$work/output/"*; do
    case "$path" in *.deb|*.apk|*.rsa.pub) cp "$path" server-dist/ ;; esac
  done
fi
