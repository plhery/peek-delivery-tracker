#!/usr/bin/env bash
# Puts a beta's APK, as Google Play signed it, in the F-Droid repository of the Android beta and on GitHub Releases, and
# tags the commit it was built from for F-Droid's own catalog.
#
#   scripts/publish-fdroid.sh <play-signed.apk> <commit> [notes.md]
#
# <play-signed.apk> is Play Console's signed universal APK of the release (App bundle explorer, the version,
# Downloads). <commit> is the commit the release was built from, which its GitHub release, android-<versionName>-<versionCode>,
# names. PEEK_FDROID_DIR names the repository's folder on this Mac, made by `fdroid init`, whose config.yml and key stay
# outside every git tree. The repository keeps the newest beta alone; the older ones stay on GitHub Releases.
set -euo pipefail

apk=$1
commit=$2
notes=${3:-}
dir=${PEEK_FDROID_DIR:?PEEK_FDROID_DIR names the F-Droid repository folder}
sdk=${ANDROID_HOME:?ANDROID_HOME names the Android SDK}
android=$(cd "$(dirname "$0")/.." && pwd)
tools=$(find "$sdk/build-tools" -mindepth 1 -maxdepth 1 | sort -V | tail -1)
app=com.plhery.peekdeliverytracker
github=plhery/peek-delivery-tracker
# Google Play's app-signing certificate, which src/server/androidAppLinks.ts names too.
play=f155dbe776f45feaf2514f733b984da3fc5f562ada66f2bd651fc589d2bdde60

# Play signs with its v3 key for every device, and adds keys of its own for the newest Android versions.
certs=$("$tools/apksigner" verify --print-certs "$apk" 2>/dev/null)
if ! grep -Eq "^(Signer #1|V3\.0 Signer:) certificate SHA-256 digest: $play$" <<<"$certs"; then
  echo "$apk is not signed by Google Play" >&2
  exit 1
fi
badging=$("$tools/aapt2" dump badging "$apk")
package=${badging%%$'\n'*}
code=$(sed -n "s/^package: name='$app' versionCode='\([0-9]*\)'.*/\1/p" <<<"$package")
name=$(sed -n "s/.* versionName='\([^']*\)'.*/\1/p" <<<"$package")
if [ -z "$code" ] || [ -z "$name" ]; then
  echo "$apk is not $app" >&2
  exit 1
fi
target=$(git -C "$android" rev-parse --verify "$commit^{commit}")
tag="android-$name-$code"

# The index, signed with the repository's key. Peek's icon is the repository's and the app's.
mkdir -p "$dir/metadata/$app/en-US" "$dir/older"
cp "$android/../public/icons/icon-512.png" "$dir/icon.png"
cp "$android/../public/icons/icon-512.png" "$dir/metadata/$app/en-US/icon.png"
find "$dir/repo" -maxdepth 1 -name "${app}_*.apk" ! -name "${app}_$code.apk" -exec mv {} "$dir/older/" \;
cp "$apk" "$dir/repo/${app}_$code.apk"
cp "$android/fdroid/$app.yml" "$dir/metadata/$app.yml"
(cd "$dir" && fdroid update)
fingerprint=$(keytool -printcert -jarfile "$dir/repo/index-v1.jar" | sed -n 's/^[[:space:]]*SHA256: //p' | tr -d ':')
address="https://raw.githubusercontent.com/$github/fdroid/repo?fingerprint=$fingerprint"

# The repository, alone on the branch fdroid, which raw.githubusercontent.com serves; fdroid's status files, which name
# this Mac's paths, stay out.
site=$(mktemp -d)
cp -R "$dir/repo" "$site/repo"
printf '# Peek beta for F-Droid\n\nAdd this repository in F-Droid: %s\n' "$address" > "$site/README.md"
git -C "$site" init -q -b fdroid
git -C "$site" add README.md repo ':!repo/status'
git -C "$site" commit -q -m "Peek $name ($code)"
git -C "$site" -c credential.helper= -c 'credential.helper=!gh auth git-credential' \
  push -q --force "https://github.com/$github.git" fdroid:refs/heads/fdroid

# The same APK on GitHub Releases. The tag sits on a commit of its own that names the release's commit: a new ref on the
# release's commit carries .github/workflows, which a token without the workflow scope may not create. A release made
# already stays as it is.
if gh release view "$tag" --repo "$github" >/dev/null 2>&1; then
  echo "$tag is on GitHub Releases already" >&2
else
  release=$(mktemp -d)
  printf 'Peek for Android %s (%s), the beta, is built from https://github.com/%s/tree/%s\n' "$name" "$code" "$github" "$target" \
    > "$release/README.md"
  git -C "$release" init -q -b release
  git -C "$release" add README.md
  git -C "$release" commit -q -m "Peek for Android $name ($code)"
  git -C "$release" tag "$tag"
  git -C "$release" -c credential.helper= -c 'credential.helper=!gh auth git-credential' \
    push -q "https://github.com/$github.git" "refs/tags/$tag"
  cp "$apk" "$site/peek-$name-$code.apk"
  {
    printf 'Peek for Android %s (%s), the beta: the same APK, signed by Google Play, as Google Play'"'"'s closed test, built from %s.\n\n' \
      "$name" "$code" "$target"
    printf 'In F-Droid, add the repository %s\n' "$address"
    if [ -n "$notes" ]; then printf '\n'; cat "$notes"; fi
  } > "$site/notes.md"
  gh release create "$tag" "$site/peek-$name-$code.apk" --repo "$github" --verify-tag --prerelease \
    --title "Peek for Android $name ($code), beta" --notes-file "$site/notes.md"
fi

# F-Droid's own catalog (https://gitlab.com/fdroid/fdroiddata) builds the release's commit from source and finds it by
# this tag. It goes over SSH: a token without the workflow scope may not create a ref on that commit.
source="android-source-$name-$code"
if ! git ls-remote --exit-code "https://github.com/$github.git" "refs/tags/$source" >/dev/null; then
  git -C "$android" push -q "git@github.com:$github.git" "$target:refs/tags/$source" ||
    echo "Push F-Droid's source tag by hand: git push git@github.com:$github.git $target:refs/tags/$source" >&2
fi
echo "$address"
