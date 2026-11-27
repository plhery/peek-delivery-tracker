# Friends

A small, private stamp collection shared by mutual invitation. There are no public profiles,
discovery, contact import, follower counts, leaderboards or delivery feed.

## What is shared

Joining is opt-in. Before the form, a made-up pair of passports shows what Friends looks
like. The user picks a nickname and sees a live preview of exactly what friends will see.
Three switches control:

- **Passport stats**, on for a new profile: delivered count, average journey (rounded up
  to whole days) and earned Passport stamps.
- **Countries**, under Passport stats and only with it: each country the owner's parcels
  went through, with how many, without days or order. On for a new profile; a profile made
  before the switch keeps it off until its owner turns it on. An app that doesn't know the
  switch leaves it as it is.
- **"A parcel arrived this week"**, off for a new profile: a coarse signal with no parcel
  or exact time.

Nothing else crosses the boundary: no parcel names or contents, tracking numbers, carriers,
places finer than a country, emails, photos, active shipments or ids.

## A friend's page

It puts the friend's stamps beside the reader's: those they both have, those only the
friend has (with how far the reader is from each), and those only the reader has; then the
friend's countries, and how many of them the reader has too. The reader's side is worked out
on the device from their own parcels, as their Passport is.

## Invitations

- A link is `/i/<key>`: a random 16-character base64url key (96 bits), called
  `previewId` / `preview_id` for historical reasons. It lets you preview and accept one
  invitation, so treat it as a credential. It grants only the friendship, never account
  or parcel access.
- Links are single-use and expire after seven days. Creating a new link keeps older ones
  valid, up to 20 live links an account: past that, the oldest stops working. **Cancel
  invitation links** (in your own Friends card) revokes all outstanding links without
  touching existing friends.
- Public previews (including social-card GETs and rate-limited POSTs) reveal only the
  sender's nickname and never consume the link. Pages send `Referrer-Policy: no-referrer`,
  clients strip the key from the address bar before sign-in, and analytics never records
  URLs or keys. Social platforms may cache previews on their side.
- The social card's picture is drawn with the server's own fonts
  ([ARCHITECTURE.md](ARCHITECTURE.md)). An emoji in the nickname is left out, and a
  nickname with a letter they lack is not shown: the card then says "A friend".
- A pending invitation survives sign-in, kept in sessionStorage on the web and
  UserDefaults on iOS, for up to seven days. Newcomers choose their sharing settings before
  accepting.
- Reopening an accepted link shows `already_accepted` or `already_friends` to the account
  that accepted it, and creates nothing new. Unavailable links stay closed.
- Older link formats (`/i/key#token`, `/invite?preview=hash#token`) still work, and so do
  links on a host the site has left: they redirect to the same address.
- With the iPhone app installed, a link opens in the app. Personal Team iOS builds can't
  have Associated Domains; there the web page's "Open in the iOS app" button opens it.

Either friend can remove the connection. Turning Friends off deletes the profile,
invitations and both sides of every friendship, and account deletion cascades the same way.

## How it's enforced

- The database's friend functions decide which cards a caller sees and which share stats
  and countries.
  The server then works out each shared card's counts and stamps from the owner's parcels,
  served as the owner's apps get them, with the app's own Passport code
  (`sharedPassport` in [`passport.ts`](../src/lib/passport.ts)). It reads the whole circle's
  parcels at once, then checks again, as it builds the cards, that each owner still shares
  stats, and countries, with the viewer: a card whose owner stopped sharing or left the
  circle meanwhile shows neither. Client-sent statistics are never accepted.
- Friend tables deny direct client access. The anonymous preview returns only the
  nickname of an unexpired invitation, and authenticated functions bind `auth.uid()` and
  return mutual friends only. Parcel RLS is unchanged.
- Friend data is network-only and never cached. It is cleared when the app goes to the
  background or the view closes, and a visible view refreshes every 60 s so privacy changes
  spread.
- Account export includes your own settings and your friends' nicknames and card ids,
  never their stats.

## Stamps

Twelve shareable stamps: the original four plus `acrossBorders`, `aroundWorld`,
`theRegular`, `rightNextDoor`, `worthTheWait`, `busyDoorstep`, `pickedUp` and
`homeForHolidays`. They're worked out as the [Passport](PASSPORT.md) works them out, from
the owner's parcels, archived ones included, and returned only when stats sharing is on.
The passport's newer stamps stay off friend cards, so the apps that know only these twelve
can still read them.

- Repeated delivery scans count once.
- Calendar stamps use UTC, since Friends doesn't know the owner's timezone, so they can
  differ from the local Passport near midnight.

## Demo

Demo friends are local fixtures that use the same response shapes. Nothing reaches a real
person, and demo settings hide the cancel-links action. If the backend is down, the UI says
so and never falls back to demo data.
