#!/usr/bin/env bash
# Puts a beta's APK, the Google-free fdroid build signed with Peek's upload key, in the F-Droid repository of the Android
# beta and on GitHub Releases, and tags the commit it was built from for F-Droid's own catalog. Play's own copy doesn't go
# there: Play protects the copies it signs, and they refuse to open on a phone without the Play Store.
#
#   scripts/publish-fdroid.sh <fdroid.apk> <commit> [notes.md]
#
# <fdroid.apk> is `./gradlew :app:assembleFdroidRelease -Ppeek.releaseXcconfig=fdroid/release.xcconfig
# -Ppeek.uploadProperties=<upload.properties>` from a clean checkout of <commit>, the commit the release was built from,
# which its GitHub release, android-<versionName>-<versionCode>, names. PEEK_FDROID_DIR names the repository's folder on
# this Mac, made by `fdroid init`, whose config.yml and key stay outside every git tree. The repository keeps the newest
# beta alone; the older ones stay on GitHub Releases, and an older beta is refused once a newer one is there.
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
# The certificate of Peek's upload key, which signs the builds sent to Google Play too; Play re-signs its own copies.
upload=f433cbf03c68766d1e6b321d1020e8fee51f75780b56c0393ff7420f93103f73
# The APK installed from Google Play or from this repository before 1.1.19 (13) is signed by Google Play, so it has to go
# before this one goes on.
uninstall="If you installed Peek 1.1.18 or earlier from GitHub or the Peek beta F-Droid repository, or Peek from Google Play, uninstall it first: this copy is signed with a different key."

certs=$("$tools/apksigner" verify --print-certs "$apk" 2>/dev/null) || certs=
digests=$(sed -n 's/.*certificate SHA-256 digest: //p' <<<"$certs" | sort -u)
if [ "$digests" != "$upload" ]; then
  echo "$apk is not signed with Peek's upload key alone" >&2
  exit 1
fi
# The fdroid flavor carries no Google library: no Firebase, Play services, ML Kit or Play Core.
manifest=$("$tools/aapt2" dump xmltree --file AndroidManifest.xml "$apk")
if grep -Eq 'com\.google\.(firebase|android\.gms|mlkit|android\.play)' <<<"$manifest"; then
  echo "$apk carries Google's libraries: publish the fdroid build" >&2
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
# Re-running an interrupted older beta would put it in place of the newer one.
for held in "$dir"/repo/"${app}"_*.apk; do
  [ -e "$held" ] || continue
  heldCode=${held##*_}
  heldCode=${heldCode%.apk}
  if [[ $heldCode =~ ^[0-9]+$ ]] && [ "$heldCode" -gt "$code" ]; then
    echo "$dir/repo holds versionCode $heldCode, newer than $code: finish $code's GitHub release by hand" >&2
    exit 1
  fi
done
target=$(git -C "$android" rev-parse --verify "$commit^{commit}")
tag="android-$name-$code"

release=$(mktemp -d)
printf 'Peek for Android %s (%s), the beta, is built from https://github.com/%s/tree/%s\n' "$name" "$code" "$github" "$target" > "$release/README.md"
git -C "$release" init -q -b release
remote="https://github.com/$github.git"
ref="refs/tags/$tag"
existing=$(git ls-remote "$remote" "$ref")
if [ -n "$existing" ]; then
  git -C "$release" fetch -q --no-tags "$remote" "$ref"
  if ! git -C "$release" show FETCH_HEAD:README.md | cmp -s - "$release/README.md"; then
    echo "$tag does not name the expected source commit $target" >&2
    exit 1
  fi
fi

# The index, signed with the repository's key. Peek's icon is the repository's and the app's.
mkdir -p "$dir/metadata/$app/en-US" "$dir/older" "$dir/config"
cp "$android/../public/icons/icon-512.png" "$dir/icon.png"
cp "$android/../public/icons/icon-512.png" "$dir/metadata/$app/en-US/icon.png"
find "$dir/repo" -maxdepth 1 -name "${app}_*.apk" ! -name "${app}_$code.apk" -exec mv {} "$dir/older/" \;
cp "$apk" "$dir/repo/${app}_$code.apk"
cp "$android/fdroid/$app.yml" "$dir/metadata/$app.yml"
cp "$android/fdroid/antiFeatures.yml" "$dir/config/antiFeatures.yml"
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
if [ -z "$existing" ]; then
  git -C "$release" add README.md
  git -C "$release" commit -q -m "Peek for Android $name ($code)"
  git -C "$release" -c credential.helper= -c 'credential.helper=!gh auth git-credential' \
    push -q "$remote" "HEAD:$ref"
fi
asset="peek-$name-$code.apk"
cp "$apk" "$site/$asset"
if assets=$(gh release view "$tag" --repo "$github" --json assets --jq '.assets[] | select(.state == "uploaded") | .name'); then
  if ! grep -Fxq "$asset" <<<"$assets"; then
    gh release upload "$tag" "$site/$asset" --repo "$github" --clobber
  fi
else
  {
    printf 'Peek for Android %s (%s), the beta, built from %s without Google'"'"'s libraries and signed with Peek'"'"'s own key.\n\n' \
      "$name" "$code" "$target"
    printf '%s\n\n' "$uninstall"
    printf 'In F-Droid, add the repository %s\n' "$address"
    if [ -n "$notes" ]; then printf '\n'; cat "$notes"; fi
  } > "$site/notes.md"
  gh release create "$tag" "$site/peek-$name-$code.apk" --repo "$github" --verify-tag --prerelease \
    --title "Peek for Android $name ($code), beta" --notes-file "$site/notes.md"
fi

# F-Droid's own catalog (https://gitlab.com/fdroid/fdroiddata) builds the release's commit from source and finds it by
# this tag, then ships the APK released above once its build matches it, so the tag comes last. It goes over SSH: a token
# without the workflow scope may not create a ref on that commit.
source="android-source-$name-$code"
if ! git ls-remote --exit-code "https://github.com/$github.git" "refs/tags/$source" >/dev/null; then
  git -C "$android" push -q "git@github.com:$github.git" "$target:refs/tags/$source" ||
    echo "Push F-Droid's source tag by hand: git push git@github.com:$github.git $target:refs/tags/$source" >&2
fi
echo "$address"
