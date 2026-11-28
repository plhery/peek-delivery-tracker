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
- **Numbers.** Besides the counts and the two times: the busiest month, the calendar month
  the most parcels were added in (two at least, the later month on a tie), and the carriers
  you've used, every parcel's real carrier with the one used most.
- **Pages.** Under the cover, three pages: Countries (an ink stamp for each, in its
  continent's outline), Stamps and Numbers. Labels or a swipe turn them; on a wide screen the
  book lies open on two. The passport opens on the page with news, and a page's news is
  pressed in the first time it is shown.
- **New.** What was earned since the passport was last opened is new: a line under the
  delivered or archived card that earned it, and a dot on the Passport tab. The list of
  seen stamps and countries stays on the device, one per account, one for a guest and, in the
  apps, one for the demo; the first time a passport is read on a device, once its parcels have
  loaded without an error, everything it holds counts as seen. A list kept before countries
  counted as news takes the countries it can see then as seen.
- **The year's card.** In December, once a parcel was delivered that year, the passport
  offers "Your <year> in parcels": a 1080×1350 picture made on the device and handed to the
  share sheet or saved. It carries counts, the countries that year's parcels went through
  (six at most, each dated by the year's first delivery through it), the stamps they earned
  and the fastest time; never a parcel's name, a
  shop, a carrier, a tracking number or a place below the country.
