# Passport

The passport is worked out on the device from the parcels you still have, archived ones
included, so deleting a parcel can take a stamp or a country away. The web
([`passport.ts`](../src/lib/passport.ts)), the iPhone and Android engines replay the same
cases ([`passport-cases.json`](../shared/passport-cases.json)).

- **Deliveries.** A parcel counts once it is delivered, at its first delivered scan. Its
  journey time runs from the first physical scan to that one; a parcel with an undated scan
  counts but is never timed.
- **Countries.** Every country a delivered parcel was scanned in before its delivery, transit
  countries included: the scan's own country field, or else the country the server placed
  the scan in. Registration and pending scans don't count. Each country keeps the first day
  a parcel reached it and takes the next number in the order they arrived.
- **Stamps.** Deliveries are replayed in the order they arrived; a stamp's day is the
  delivery that first made its rule true. Calendar stamps use the device's time zone.
  Carriers count only real ones, never the `unknown` or `intl-post` placeholders.
- **Shared data.** [`passport.json`](../shared/passport.json) holds the stamps in page order,
  their glyphs and tones, each country's continent, ink and tilt, and the continent
  outlines. Paths use absolute `M L C Z` commands only, so every platform draws them alike.
- **New.** What was earned since the passport was last opened is new: a line under the
  delivered or archived card that earned it, and a dot on the Passport tab. The list of
  seen stamps and countries stays on the device, one per account and one for a guest; the
  first time a passport is read on a device, everything it holds counts as seen.
