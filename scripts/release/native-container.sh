#!/bin/sh
set -eu
stage="$1"
format="$2"
export NEXT_TELEMETRY_DISABLED=1
if [ "$format" = deb ]; then
  export DEBIAN_FRONTEND=noninteractive
  apt-get update
  apt-get install -y --no-install-recommends ca-certificates curl xz-utils libatomic1 libstdc++6
  export PATH="/work/tools/node-v26.12.0-linux-x64/bin:$PATH"
else
  apk add --no-cache nodejs-current npm ca-certificates libstdc++ libatomic
fi

case "$stage" in
  build)
    if [ "$format" = deb ]; then
      apt-get install -y --no-install-recommends python3 make g++
    else
      apk add --no-cache python3 make g++
    fi
    mkdir -p /work/source
    tar -C /source --exclude=.git --exclude=node_modules --exclude=.next --exclude=desktop/node_modules \
      --exclude=.env --exclude='.env.*' \
      --exclude=desktop/build --exclude=desktop/dist --exclude=server-dist --exclude=dist --exclude=test-results \
      --exclude=playwright-report --exclude=coverage -cf /work/source.tar .
    tar -C /work/source -xf /work/source.tar
    rm /work/source.tar
    cd /work/source
    node --version
    npm ci
    NEXT_PUBLIC_USE_API=true NEXT_PUBLIC_SUPABASE_URL=https://supabase.invalid \
      NEXT_PUBLIC_SUPABASE_PUBLISHABLE_KEY=sb_publishable_ci NEXT_PUBLIC_AUTH_EMAIL_OTP_ENABLED=true npm run build
    node scripts/test-public-configuration.mjs .next/standalone
    ;;
  package)
    cd /work/source
    # Packaging-only repairs can be exercised against the unchanged built app.
    cp /source/scripts/server-package.mjs scripts/server-package.mjs
    cp -R /source/packaging/server/. packaging/server/
    export PEEK_NFPM=/work/tools/nfpm
    if [ "$format" = apk ]; then
      export PEEK_APK_PRIVATE_KEY=/keys/peek-server-apk.rsa
      export PEEK_APK_PUBLIC_KEY=/keys/peek-server-apk.rsa.pub
    fi
    node scripts/server-package.mjs --format "$format" --arch amd64 \
      --standalone /work/source/.next/standalone --node-runtime /work/tools/node-v26.12.0-linux-x64 --output /work/output
    upgrade_version="$(node --input-type=module -e 'const match = /^(\d+)\.(\d+)\.(\d+)(.*)$/.exec(process.env.PEEK_RELEASE_VERSION); if (!match) throw new Error("Invalid version"); console.log(`${match[1]}.${match[2]}.${Number(match[3]) + 1}${match[4]}`);')"
    printf '%s' "$upgrade_version" > /work/upgrade-version
    PEEK_RELEASE_VERSION="$upgrade_version" node scripts/server-package.mjs --format "$format" --arch amd64 \
      --standalone /work/source/.next/standalone --node-runtime /work/tools/node-v26.12.0-linux-x64 --output /work/upgrade
    ;;
  smoke)
    if [ "$format" = deb ]; then
      apt-get install -y --no-install-recommends bash jq
    else
      apk add --no-cache bash jq curl
    fi
    exec bash /source/scripts/test-server-package.sh "$format" /work/output /work/upgrade "$(cat /work/upgrade-version)"
    ;;
  *) exit 2 ;;
esac
