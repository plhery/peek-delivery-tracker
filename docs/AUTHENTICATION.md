# Authentication

Sign-in goes through Supabase Auth: Google, Sign in with Apple (off by default),
email one-time codes, or a configured OpenID Connect (OIDC) provider. SMTP sends
email codes; deployments using only OIDC do not need it.

## Configuration

Frontend build values:

```dotenv
NEXT_PUBLIC_SUPABASE_URL=https://supabase.example.com
NEXT_PUBLIC_SUPABASE_PUBLISHABLE_KEY=sb_publishable_example
NEXT_PUBLIC_AUTH_GOOGLE_ENABLED=true
NEXT_PUBLIC_AUTH_APPLE_ENABLED=false
NEXT_PUBLIC_AUTH_EMAIL_OTP_ENABLED=false
```

Server runtime values:

```dotenv
SUPABASE_URL=https://supabase.example.com
SUPABASE_PUBLIC_URL=https://supabase.example.com
SUPABASE_PUBLISHABLE_KEY=sb_publishable_example
SUPABASE_SERVICE_ROLE_KEY=server-only-service-role-key
```

The URL and publishable key are public; RLS protects the rows. The service-role key
bypasses RLS. It must never get a `NEXT_PUBLIC_` prefix or appear in logs, screenshots,
build arguments or git.

The iPhone app reads the same public values from the gitignored
`ios/Configuration/Local.xcconfig`. It completes PKCE in `ASWebAuthenticationSession` and
keeps the session in Keychain. Add its callback to the Supabase redirect allow list:

```text
peekdeliverytracker://auth-callback
```

On self-hosted Supabase, configure GoTrue through environment variables (site URL, allow
list, SMTP, templates). The hosted dashboard doesn't configure a self-hosted Auth server.

Turn off the methods the apps don't use. The self-hosted template turns on phone sign-up
with auto-confirm, which hands out a confirmed account for any phone number and a
password, with no text message. It also turns on anonymous sign-ins, and manual linking,
which lets a signed-in session attach another sign-in method. Peek never links by hand: a
sign-in whose email the provider has verified joins the account with that email by itself.

```dotenv
GOTRUE_EXTERNAL_PHONE_ENABLED=false
GOTRUE_SMS_AUTOCONFIRM=false
GOTRUE_EXTERNAL_ANONYMOUS_USERS_ENABLED=false
GOTRUE_SECURITY_MANUAL_LINKING_ENABLED=false
```

Web sign-in with Google, Apple or OIDC returns to the origin it started on, which must be the
Site URL or in the allow list. The return carries no path or query: a parcel the address
asked for (`/?parcel=<id>`, as a delivery email links it) is noted for the tab before
leaving and opened after the return. When the site answers on more than one host
([DEPLOYMENT.md](DEPLOYMENT.md)), set `GOTRUE_SITE_URL` to the canonical origin and list
every origin in `GOTRUE_URI_ALLOW_LIST`, next to the iPhone callback. Providers return
through the Supabase callback, so their consoles don't name the app's host as a redirect.

## OpenID Connect

An identity provider such as Authentik or Keycloak can be a sign-in method, or the only
one. Supabase creates the Peek account on first sign-in and keeps the session; enrollment,
password recovery and MFA are the provider's.

Register the provider with the
[Supabase Auth admin API](https://supabase.com/docs/guides/self-hosting/self-hosted-custom-oauth-providers)
(Auth v2.196.0 or later): issuer, client ID, client secret and the `openid profile email`
scopes. The secret stays in Supabase; Peek only needs the provider's `custom:` identifier
and the name on the button. In the identity provider, allow the redirect
`https://supabase.example.com/auth/v1/callback`.

Supabase Auth checks two things:

- The provider's discovery, token and key endpoints are HTTPS and resolve to a public
  address. A provider only reachable on the LAN is refused.
- The signed ID token carries `sub`, `email` and `email_verified: true`. Email from the
  userinfo endpoint alone is not enough; in Authentik, turn on **Include claims in
  id_token**.

Web build values:

```dotenv
NEXT_PUBLIC_AUTH_OIDC_PROVIDER=custom:authentik
NEXT_PUBLIC_AUTH_OIDC_NAME=Authentik
```

iPhone values, in `ios/Configuration/Local.xcconfig`:

```text
SDT_OIDC_PROVIDER = custom:authentik
SDT_OIDC_NAME = Authentik
```

Set both or neither. With the other methods switched off, the sign-in page shows only the
provider:

<img src="images/oidc-sign-in.png" width="390" alt="Peek sign-in with only Continue with Authentik" />

An OIDC-only deployment needs no SMTP for sign-in: also set
`GOTRUE_EXTERNAL_EMAIL_ENABLED=false`. Parcel emails still need SMTP.

## Email codes

Enable the Email provider with sign-ups, set the Site URL to the production origin, and
allow only real redirect origins.

The app serves a branded template at `/auth-emails/magic-link.html`. It shows the six-digit
`{{ .Token }}` and picks its language from the `locale` user metadata the apps save
(English by default). While auto-confirm is off, new accounts get the *confirmation* email
instead of the magic-link one, so point both at the same template:

```dotenv
GOTRUE_MAILER_TEMPLATES_MAGIC_LINK=https://peek.example.com/auth-emails/magic-link.html
GOTRUE_MAILER_TEMPLATES_CONFIRMATION=https://peek.example.com/auth-emails/magic-link.html
GOTRUE_MAILER_SUBJECTS_MAGIC_LINK={{ if eq .Data.locale "de" }}Dein Anmeldecode für Peek{{ else if eq .Data.locale "fr" }}Ton code de connexion Peek{{ else if eq .Data.locale "it" }}Il tuo codice di accesso a Peek{{ else if eq .Data.locale "es" }}Tu código de acceso a Peek{{ else if eq .Data.locale "pt" }}O teu código de acesso ao Peek{{ else if eq .Data.locale "pt-br" }}Seu código de acesso ao Peek{{ else if eq .Data.locale "pl" }}Twój kod logowania do Peek{{ else if eq .Data.locale "ru" }}Твой код для входа в Peek{{ else if eq .Data.locale "zh" }}你的 Peek 登录验证码{{ else }}Your Peek sign-in code{{ end }}
GOTRUE_MAILER_SUBJECTS_CONFIRMATION=<same value as GOTRUE_MAILER_SUBJECTS_MAGIC_LINK>
```

Auth caches the template and keeps the last good copy if a later fetch fails. The UI calls
`signInWithOtp` then `verifyOtp` (type `email`). It never uses magic-link callbacks.

**Use your own SMTP before enabling email-code sign-ups.** Supabase's default sender only reaches team
addresses and is heavily rate-limited. Any SMTP service works. Use a dedicated sender
address, publish SPF, DKIM and DMARC, and turn off link tracking.

Limits count a visitor's network: an IPv4 address, or an IPv6 /64, since a line is given
a whole /64. Run [ops/kong/auth-limits.lua](../ops/kong/auth-limits.lua) as a Kong
`post-function` in the access phase of the `auth-v1` and `auth-v1-open` services. It
reads the network from `CF-Connecting-IP`, so Cloudflare must be the only way in. It passes
the network on in `X-Client-Network`, and caps the emails each network can ask for every
5 minutes, hour and day. Set `GOTRUE_RATE_LIMIT_HEADER=X-Client-Network` so Auth's own
limits, such as code checks, count networks too. Without that header Auth limits no
network: it only waits a minute between emails to the same inbox and caps all emails at
`GOTRUE_RATE_LIMIT_EMAIL_SENT` an hour (30 by default), which one sender can spend. Set
`GOTRUE_MAILER_OTP_EXP=1800` so a code lasts 30 minutes. The clients send no CAPTCHA
token, so leave Auth's CAPTCHA off.

## Google

Create a **Web application** OAuth client. Add the app origin as a JavaScript origin and
the Supabase callback as the redirect URI:

```text
https://supabase.example.com/auth/v1/callback
```

Give the credentials to GoTrue only, never to the app container or the browser:

```dotenv
GOTRUE_EXTERNAL_GOOGLE_ENABLED=true
GOTRUE_EXTERNAL_GOOGLE_CLIENT_ID=google-client-id
GOTRUE_EXTERNAL_GOOGLE_SECRET=server-only-client-secret
GOTRUE_EXTERNAL_GOOGLE_REDIRECT_URI=https://supabase.example.com/auth/v1/callback
```

Set `GOTRUE_SITE_URL` and `GOTRUE_URI_ALLOW_LIST`, restart Auth, and check that
`/auth/v1/settings` lists Google.

## Sign in with Apple

Implemented on web and iOS, off until configured. It needs a paid Apple Developer
membership, because a Personal Team can't enable the capability.

1. Enable **Sign in with Apple** on the app ID `com.plhery.PeekDeliveryTracker` (or yours).
2. Create a Sign in with Apple key and keep the `.p8` outside the repo. Give it to the app
   server so it can revoke Apple's tokens when an account is deleted (below).
3. Configure GoTrue. The iPhone app only needs the bundle ID as an ID-token audience. Keep
   nonce verification on.

   ```env
   GOTRUE_EXTERNAL_APPLE_ENABLED=true
   GOTRUE_EXTERNAL_APPLE_CLIENT_ID=com.plhery.PeekDeliveryTracker
   ```

4. For the web, also create a Services ID (e.g. `com.plhery.PeekDeliveryTracker.web`), with
   your Supabase host as its domain and `https://supabase.example.com/auth/v1/callback` as
   its return URL. List it first in `GOTRUE_EXTERNAL_APPLE_CLIENT_ID`, and set
   `GOTRUE_EXTERNAL_APPLE_SECRET` to a client secret JWT signed with the key (Apple caps
   its lifetime at six months) and `GOTRUE_EXTERNAL_APPLE_REDIRECT_URI` to the callback.
5. Register your email sender with Apple's private relay so Hide My Email addresses
   receive codes.
6. Turn the buttons on:
   - Web: build with `NEXT_PUBLIC_AUTH_APPLE_ENABLED=true`.
   - iOS: set `SDT_APPLE_AUTH_ENABLED = YES` in `Local.xcconfig`, and sign with the paid
     team and a profile that includes `com.apple.developer.applesignin`. The Personal Team
     script turns Apple sign-in off.

The web uses Supabase OAuth with PKCE. iOS uses the native sheet (email scope only) and
exchanges the identity token plus the original nonce with Supabase. Pending friend
invitations survive the redirect.

Production's web client secret lasts 180 days. A daily job on the host checks it with
Apple and, 30 days before it expires, signs a new one with the key in the app server's
environment and recreates `supabase-auth`. Grafana alerts 14 days before it expires or as
soon as Apple refuses it ([OBSERVABILITY.md](OBSERVABILITY.md)).

Both platforms show Google first, then Apple, at the same height, corner radius and title
size. iOS uses Apple's own button and sizes Google's title to match it. The web draws a
custom Apple button with Apple's proportions: the title is 43% of the button's height (less
on a screen too narrow for it on one line), and the logo is the one Apple's web button uses. Google's "G" is its official artwork, in
`public/icons/` and the iPhone asset catalog.

Apple asks apps to revoke its tokens when an account is deleted. Before the iPhone app
deletes an account that has an Apple identity, it signs in with Apple again: that refreshes
the session for the ten-minute rule, and refuses another Apple Account. It sends the
one-time authorization code with the deletion. The server trades the code for Apple's
tokens and revokes them, then deletes the account; a failed revocation is logged as
`apple_sign_in_revocation` and doesn't stop the deletion. The server needs the key for
that:

```env
APPLE_SIGN_IN_TEAM_ID=ABCDE12345
APPLE_SIGN_IN_KEY_ID=KEY1234567
APPLE_SIGN_IN_PRIVATE_KEY=-----BEGIN PRIVATE KEY-----\n...\n-----END PRIVATE KEY-----
APPLE_SIGN_IN_CLIENT_ID=com.plhery.PeekDeliveryTracker
```

An account deleted on the web isn't revoked with Apple: Supabase doesn't keep Apple's tokens.

## Sessions

The client keeps the Supabase session and refreshes the short-lived access token. Sessions
don't expire by default, so users stay signed in until they sign out or delete their
account. Keep the access-token lifetime short (about an hour); refresh tokens carry the
long session. If you enable time-box or inactivity limits, use at least 30 days.

## Checklist

1. Request a code for a non-team address; the branded email arrives once.
2. Sign in, reload, reopen the installed PWA and relaunch the iPhone app.
3. A second account can't see, refresh, archive, export or get notifications for the first
   account's parcels.
4. After sign-out, private API calls return `401`.
5. Export data, then delete a disposable account. Deletion needs a sign-in from the last
   ten minutes.
6. For Apple: first and repeat sign-in, Hide My Email, cancel, sign-out, and returning to a
   pending invitation, on both platforms.

References: Supabase [email OTP](https://supabase.com/docs/guides/auth/auth-email-passwordless),
[templates](https://supabase.com/docs/guides/auth/auth-email-templates),
[SMTP](https://supabase.com/docs/guides/auth/auth-smtp),
[sessions](https://supabase.com/docs/guides/auth/sessions),
[Apple](https://supabase.com/docs/guides/auth/social-login/auth-apple); Apple
[web configuration](https://developer.apple.com/help/account/capabilities/configure-sign-in-with-apple-for-the-web/).
