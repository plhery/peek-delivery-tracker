# Shared translations

`en`, `de`, `fr`, `it`, `es`, `pt` and `pl` JSON files are the source of all web and iOS
copy.

- Add or change a key in **all seven** files, keeping `{{variable}}` placeholders.
- The web imports them directly with typed keys. For iOS, run `npm run ios:resources`,
  which regenerates `Localization.json`. Don't edit the generated file or put strings in
  Swift views.
- `node scripts/generate-ios-resources.mjs --check` verifies key parity, placeholders,
  native references and generated output.
- The iOS app also runs on iPad and Mac. Copy it shows that names the iPhone needs a `<key>.ipad`
  variant, which an iPad reads instead, and a `<key>.mac` one for a Mac; `ios:resources` refuses
  a key without them, unless only an iPhone or an iPad shows it.
- The Android app reads a `<key>.android` variant instead, which names "this device" and
  Android's settings on every phone, foldable and tablet. The Android tests refuse a key the
  app shows that names the iPhone, an Apple app or the iPhone's Home Screen without one.

**Tone.** Informal and concise. Spanish and Portuguese are the European variants; Portuguese
pages and emails declare `pt-PT`. In Portuguese the tracked parcel is always *encomenda*
(feminine, so its adjectives and pronouns agree); *envio* only means sending, as in *e-mail
de envio*. It is *pronta para levantamento* at a *ponto de levantamento* and *levantada* by
the recipient, while a carrier *recolhe* it from the sender; Peek sends *notificações*.
Passport collectibles are postage stamps (sellos, selos, znaczki). Polish counts use `.one`, `.few`
and `.many`, picked from `count`.

## Tracking messages

[`../tracking-messages.json`](../tracking-messages.json) maps the exact descriptions our
adapters and app generate to message keys and values. Web and iOS share it, and unknown
carrier text stays as written. Add new synthesized descriptions here rather than
classifying carrier wording in the UI. See [LOCALIZATION.md](../../docs/LOCALIZATION.md).

## Carrier errors

Typed carrier failures are stored as `carrier:<kind>` in `sync_error`, and the map picks
localized guidance for each. A number the carrier finds several parcels under is stored as
`carrier:several_parcels`, so the owner is asked to add each parcel's own number. A number
the parcel's own carrier does not issue is stored as `carrier:refused_number`, so the owner
is asked to check the number or the carrier; a refused postcode stays `carrier:invalid_input`. A
placeholder number, such as 123456789, is `carrier:placeholder_number`: the owner is asked to
check the number and add the parcel again. Unknown
codes get generic copy. Full diagnostics stay in the server audit. Only offer "edit input"
when the carrier actually has editable inputs.
