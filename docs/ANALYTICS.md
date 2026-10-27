# Usage analytics

Optional, self-hosted [Umami](https://umami.is). Web and PWA share one property (tagged
`platform=web` or `platform=pwa`); the iOS app uses a second one (`platform=iphone`,
`ipad`, or `mac` for the iPad app on a Mac). `mode` separates anonymous, demo and account
screens. Neither client sends a persistent identifier or account identity, so visitor
counts are Umami's own estimates.

## Configuration

Runtime environment variables, all four required:

| Variable | Value |
| --- | --- |
| `UMAMI_URL` | HTTPS origin of the collector, no path |
| `UMAMI_APP_ORIGIN` | HTTPS origin of this instance |
| `UMAMI_WEBSITE_ID` | Umami id of the web/PWA property |
| `UMAMI_IOS_WEBSITE_ID` | Umami id of the iPhone property |

Collection only runs with `NODE_ENV=production`. Leave these unset for local builds,
forks, staging and previews. Simulators and demo builds never send events.

The values are public ids, not keys. Clients read them from the uncached
`/api/analytics/config` and check the hostname. The CSP allows only that origin in
`connect-src`, and no remote script is loaded.

When the site has moved ([DEPLOYMENT.md](DEPLOYMENT.md)), `UMAMI_APP_ORIGIN` is the
canonical origin. A legacy host is then answered with its own hostname, so installed apps
that still call it keep reporting to the same properties.

Both clients post to Umami's `/api/send`. The queue holds at most 30 events, and requests
time out after 5 s. Failures are dropped and never delay the user; nothing is stored for
offline replay. The iPhone app uses a Safari-like user agent with a `DeliveryTracker`
suffix, because Umami drops bare app user agents as bots.

## Events

[`shared/analytics.json`](../shared/analytics.json) is the allowlist of screens, actions
and API mappings. `npm run ios:resources` copies it into the iPhone bundle. Screen paths
are virtual names, never real URLs.

- **Navigation**: welcome, front-door, sign-in, deliveries, passport, friends, parcel,
  parcel-link, invitation, account, add-parcel, notifications.
- **Guides** (web): a guides page counts as a screen named by its language and the guide's
  id, never its address (`guides/fr`, `guides/fr/<id>`), and not as an app open. Its mode is
  `account` when the browser holds a saved sign-in. Opening the list of guides from the
  landing's foot is an action. A carrier's page counts the same way
  (`carriers/fr`, `carriers/fr/<id>`).
- **Auth**: sign-in attempts and results, sign-out, demo entry/exit.
- **Parcels**: add, rename, change carrier, archive, restore, delete, mute, refresh, copy,
  open carrier link, paste, scan, share-in.
- **Front door**: each notice or question that stops a lookup, once as it appears: no
  number found, an order number, a pickup code, a placeholder number, a check digit, several numbers or
  carriers to choose from, a carrier asking for a postcode or link, an Amazon account, a
  refused input, a lookup limit, offline, a server error or a failed verification. A paste
  that fails is a paste with outcome `error`. Never the text.
- **Parcel links**: lookup without an account, share or copy the link, change what the
  link shows, stop sharing, open "Notify me", turn a browser's alerts on or off, get the
  iPhone app or open the parcel in it, add the delivery to a calendar, sign in (and apart from it, sign in for the email), keep in the
  account, open the parcel already followed, forget, and the lookup's owner giving the
  postcode another carrier asks for. Sharing a parcel from an account counts under the
  same names.
- **Safari from an app's browser**: where notifications need the Home Screen but an app's
  own browser has no way to it, a tap on "Open in Safari" (`started`, then `error` if the
  page is still in sight a moment later) and copying the link for Safari.
- **Parcel feedback**: an answer that a parcel's page is right or wrong, or naming the
  carrier of a parcel none was found for. Never the reasons, the words or the parcel.
- **Rating** (iPhone): each time the app asks for an App Store rating with the system's
  prompt, on the screen it asks from: `parcel` after a "Yes" there, `deliveries` after a
  parcel seen arriving. Apple doesn't say whether the prompt showed, so these are asks.
- **Discovery**: search used (never the query), filters, sorting.
- **Friends**: profile save, invitation create/revoke/accept/share/copy, a friend's
  card opened, remove, disable.
- **Passport** (web): a stamp opened for its explanation.
- **Settings**: language, appearance, notifications and the App Store link beside them,
  widgets, export, account deletion, app opens.
- **Delivery email**: switched on or off in Settings, the one-time offer accepted or
  declined, the example opened, and the email's opt-out page used. That page counts
  nothing until its button is pressed.

API actions report one final `outcome`: `success`, `error`, or `accepted` for a queued
(HTTP 202) request. `accepted` means the work was queued, not that carriers answered.
Background polling, detection, token renewal and invitation previews aren't tracked.
Demo actions never count as account API successes.

## Funnel

The way from arriving to following a parcel. Each step is a page view at a screen's virtual
path, such as `/front-door`, or an action:

1. **Arrival**: `front-door`, or `parcel-link` at a parcel's address. On the web a guide or
   carrier page can come first. `welcome` is no step: the web never shows it, and on the
   iPhone it stands for the launch.
2. **Lookup**: on the web `parcel-lookup`, and a `door-*` action for each notice that stops
   it; on the iPhone `add-parcel`. Someone signed in who adds a parcel counts `parcel-add`.
3. **Parcel**: `parcel-link` for a parcel's page, `parcel` for one opened from the deliveries.
4. **Account**: `parcel-link-sign-in` (and `parcel-link-email`), the sign-in actions and
   `sign-in-complete`, then `parcel-link-keep` as the parcel joins the account.
5. **Alerts**: `notifications-enable` and `email-offer-accept`; on the web also a parcel
   page's `parcel-link-alerts-open` and `parcel-link-alerts`. Where the alerts offer the Peek
   app on an iPhone, `parcel-link-app-get` follows its App Store link (campaign `web-notify`)
   and `parcel-link-app-open`, offered once that link was followed, opens the parcel in the app.
   Settings and the deliveries' invitation offer the app to an account in Safari outside the
   Home Screen: `notifications-app-get` (campaigns `web-settings` and `web-invite`, screens
   `notifications` and `deliveries`).

Umami's funnel report chains these steps within one property, by its own estimate of a
visitor: neither client sends an identifier, so steps join per visit, never per person.
`platform` separates `web`, `pwa`, `iphone`, `ipad` and `mac`, and `mode` the visitor
(`anonymous`), the sample parcel (`demo`) and the account; signing in turns `anonymous`
into `account` midway.

Gaps:

- Web and iPhone are separate properties, so nothing joins one person across them, or
  across browsers and devices.
- A visitor's lookup on the iPhone sends nothing, so `add-parcel` doesn't say whether it
  found the parcel.
- A parcel page opened from a share and one reached by a lookup are the same `parcel-link`;
  only a `parcel-lookup` just before tells them apart.
- `parcel-link-keep` counts requests, not parcels: one keeps the parcel on its page, one
  brings a device's parcels along after sign-in, which the iPhone does on its own.
- Installing the web app isn't counted; later events just carry `platform=pwa`. A web
  notification opened isn't counted either; the iPhone counts `notification-open`.

Downloads of the iPhone app are App Store Connect's to count, not Umami's. Each link to the
App Store carries a campaign token (`ct`) naming its place on the site, listed in
[`src/lib/appStore.ts`](../src/lib/appStore.ts); Safari's banner is `web-banner`, the
README's link `github-readme`.

## Privacy

Never sent: emails, account or parcel ids, parcel link ids or owner keys, the token of an
email's opt-out link, labels and names, gift notes, push addresses, tracking numbers,
postcodes, invitation codes, errors, search
terms, or any query string but the campaign below. A parcel page is the `parcel-link` screen, whatever its address; the sample parcel's is in `demo` mode. Umami still
sees the IP, user agent, basic device data and language.

On the web, every event carries the app's language (`locale`) when the page declares one.
A page load's first page view also says where the visitor came from, since Umami counts
sources from page views only: the `referrer`, as origin and path without query or fragment,
when it is another site (or an Android app), and the landing address's `utm_source`,
`utm_medium` and `utm_campaign` as the view's query. When the browser gave no referrer and
`utm_source` names an AI assistant (ChatGPT, Perplexity, Copilot, Gemini, Claude), the
referrer is that assistant's site, so Umami lists it under referrers and its AI channel.

The web sends nothing under DNT, GPC or `umami.disabled=1`, nor from a browser driven by
automation (`navigator.webdriver`), such as a headless crawler, which still gets the same
page. Both clients also read a
stored preference (`sdt.analytics.enabled`) that turns collection off; no screen sets
it. Retention is described in the public privacy notice.

To check a deployment, open the app, change a harmless setting, and confirm the page view
and event in Umami. iPhone changes need a new app build.
