---
title: Tracking number formats: which carrier is it?
description: Tracking number formats for USPS, UPS, FedEx, DHL, Royal Mail, Evri and more, and how to tell which carrier a number belongs to from its letters and length.
slug: tracking-number-formats-by-carrier
picture: Three tracking-number labels split into coloured parts, a magnifying glass on the country code CH, and a curious Pip.
published: 2026-10-09
updated: 2026-10-27
---

A tracking number's letters and length often tell you which carrier has your parcel or package. `1Z` followed by 16 letters and digits is UPS, 22 digits starting `92` to `95` is USPS, and 13 characters shaped like `RR000000005CH` (two letters, nine digits, two letters) usually belong to a national post, with the issuing country in the last two letters. Plain digits are the hard case: 10, 12 or 14 digits each fit several carriers, so the shipping email, or a tracker that checks the candidates, has the final word.

:::checker
:::

## How do I tell which carrier a tracking number is from?

Four checks, in this order:

:::steps
- Read the letters | They narrow it down most. `1Z` at the start is UPS; two letters at the end usually mean a post.
- Count the characters | Length rarely decides on its own, but it rules carriers out. Leave out the spaces: they're printed to help you read, not part of the number.
- Check the email | The shop's shipping email or text usually names the carrier or links to its tracking page. That beats any guess.
- Try the candidates | Enter the number on each likely carrier's site. The one that shows scans for it has your parcel.
:::

If you'd rather skip the detective work, paste the number, a carrier link or the whole shipping email into [Peek](/): it picks out the number and detects the carrier among more than 3,500.

## Tracking number formats by carrier

Here's what the carriers and the postal standard say about the numbers you're most likely to meet in the US, the UK, Ireland, Canada and Australia:

| Carrier | What the number looks like |
| --- | --- |
| [UPS](carrier:ups) | `1Z` + 16 letters and digits, 18 characters in all, such as `1Z999AA10123456784`, an example that passes UPS's check digit |
| [USPS](carrier:usps) | 22 digits starting `92`, `93`, `94` or `95`, printed in groups of four, such as `9212 3912 3456 7812 3456 70`; 26 digits on some labels; international items can carry the postal format ending `US` |
| [FedEx](carrier:fedex) | 12 digits on FedEx Ground Economy labels since 2022, where some shippers used to get 15-digit Ground numbers |
| DHL | [DHL Express](carrier:dhl-express): 10 digits, never letters. [DHL in Germany](carrier:dhl): 10 to 39 characters |
| [Royal Mail](carrier:royal-mail), [Parcelforce](carrier:parcelforce) | the postal format ending `GB`. In Peek's carrier data, those starting `E` and any letter, `CP` or `GI` are Parcelforce's, and so is `PB` + two letters + 10 digits, 14 characters in all, such as `PBAB1234567890`; Royal Mail also has 16- or 21-character references made of digits and the letters A to F |
| [Evri](carrier:evri) | 16 characters (Evri's page says both "16 digit" and "16 character") |
| DPD | 14 characters, says [DPD Germany](carrier:dpd-de); 14 digits, say DPD Austria and Belgium. [DPD UK](carrier:dpd-uk)'s help page gives no length and asks for your postcode too |
| [XDP](carrier:xdp) | no published format; its numbers are `ZDB`, three letters and six digits, such as `ZDBABC123456`, sometimes with a leading `0`; its tracker asks for the delivery postcode too |
| [APC Overnight](carrier:apc-overnight) | a 7-digit consignment number, used with the delivery postcode; shops often send a 22-digit number that starts with the booking date (20YYMMDD) and ends with those 7 digits |
| [InPost](carrier:inpost) | in the UK, `JD` or `JJD` + 16 digits, or `8YDR` + 9 digits (InPost UK says its numbers usually start with `JD`, `JJD` or `8`); `JD` or `JJD` + 18 digits is DHL |
| [An Post](carrier:an-post) | two letters, nine digits, two letters ending `IE`, such as `RR000000005IE` |
| [Canada Post](carrier:canada-post) | 16 digits (online labels) or 13 characters ending `CA`; 12 digits for Priority Worldwide |
| [Australia Post](carrier:australia-post) | its help page gives no format and calls it a "tracking number, or article ID"; numbers in the postal format end `AU` |
| [YunExpress](carrier:yunexpress) | `YT` + 16 digits (`YT` + 13 digits is YTO Express) |
| [Yanwen](carrier:yanwen) | `BYS` + 9 digits, or two letters, nine digits and `YP` (often starting `UL` or `UK`); Yanwen publishes no example number. `YW`, three letters and 12 digits is Yanwen's own US delivery service |
| [4PX](carrier:4px) | `4PX` + 13 digits + `CN`; 4PX publishes no example number, and its site takes 5 to 50 letters and digits. `LP` + 13 digits + `CN` is likely 4PX too |
| [Cainiao](carrier:cainiao), for AliExpress | no published format; its site takes letters and digits only, and its own example is a postal number ending `HK`. `LP` + 14 digits is likely Cainiao, though not certain, and so is `CNG` + 14 digits |
| [China Post](carrier:china-post), EMS | the postal format ending `CN`; international EMS numbers start with `E` |
| Amazon | `TBA`, `TBC` or `TBM` + 12 digits in Peek's carrier data, covered below |

Elsewhere in Europe, the pages on [Chronopost](carrier:chronopost), [DPD France](carrier:dpd-fr), [GLS France](carrier:gls-fr), [Relais Colis](carrier:relais-colis), [Heppner](carrier:heppner), [GEODIS](carrier:geodis) and [C Chez Vous](carrier:c-chez-vous) (France), [Delivengo](carrier:delivengo) (La Poste parcels from France to other countries), [Colis Privé](carrier:colis-prive) (France, Belgium and Luxembourg), [Mondial Relay](carrier:mondial-relay) (France and Belgium), [bpost](carrier:bpost) (Belgium), [DHL eCommerce Netherlands](carrier:dhl-ecommerce-nl) (Netherlands and Belgium), [Vinted Go](carrier:vinted-go) (parcels sold on Vinted), [BRT](carrier:brt), [GLS Italy](carrier:gls-it) and [Poste Italiane](carrier:poste-italiane) (Italy) explain their numbers. For Germany, see [GLS Germany](carrier:gls-de) and [Hermes Germany](carrier:hermes-de); for Austria, [Austrian Post](carrier:austrian-post). For Spain and Portugal, see [Correos Express](carrier:correos-express), [NACEX](carrier:nacex), [SEUR](carrier:seur), [MRW](carrier:mrw), [TIPSA](carrier:tipsa), [GLS Spain](carrier:gls-es), [DHL eCommerce Iberia](carrier:dhl-ecommerce-es), [CTT](carrier:ctt), [CTT Express](carrier:ctt-express), [Ecoscooting](carrier:ecoscooting), [Dachser](carrier:dachser) and [Paack](carrier:paack), which also delivers in France and Italy. For Poland, see [Poczta Polska](carrier:poczta-polska), [ORLEN Paczka](carrier:orlen-paczka), [DPD Poland](carrier:dpd-pl), [DHL eCommerce Poland](carrier:dhl-ecommerce-pl) and [Packeta](carrier:packeta); for Czechia, [PPL](carrier:ppl) and [Česká pošta](carrier:ceska-posta); for Slovakia, [Slovak Parcel Service](carrier:slovak-parcel-service) and [Slovenská pošta](carrier:slovenska-posta); for Hungary, [Express One](carrier:express-one) and [Foxpost](carrier:foxpost); for Romania, [Sameday](carrier:sameday), [Cargus](carrier:cargus) and [FAN Courier](carrier:fan-courier); for Bulgaria, [Speedy](carrier:speedy) and [Econt](carrier:econt); for Slovenia, [Pošta Slovenije](carrier:posta-slovenije); for Croatia, [Hrvatska pošta](carrier:hrvatska-posta); for Serbia, [Pošta Srbije](carrier:posta-srbije); for parcels to and from Ukraine, [Ukrposhta](carrier:ukrposhta), [Nova Poshta](carrier:nova-poshta) and [Meest](carrier:meest). For the Nordic and Baltic countries, see [PostNord](carrier:postnord), [Bring](carrier:bring), [Posti](carrier:posti) and [Omniva](carrier:omniva); for Denmark, [DAO](carrier:dao); for Norway, [Helthjem](carrier:helthjem); for Finland, [Matkahuolto](carrier:matkahuolto); for Lithuania, [Lietuvos paštas](carrier:lietuvos-pastas) and [Venipak](carrier:venipak), which also delivers in Latvia and Estonia; for Latvia, [Latvijas Pasts](carrier:latvijas-pasts); for Greece, [ACS Courier](carrier:acs-courier), [Geniki Taxydromiki](carrier:geniki-taxydromiki) and [ELTA Hellenic Post](carrier:elta); for Cyprus, [Cyprus Post](carrier:cyprus-post); for Greece, Cyprus, Bulgaria and Croatia, [BOX NOW](carrier:box-now). For Switzerland, see [Swiss Post](carrier:swiss-post), [Quickpac](carrier:quickpac), [Planzer](carrier:planzer), [Swiss Post Cargo](carrier:swiss-post-cargo), [DPD Switzerland](carrier:dpd-ch) and [GLS Switzerland](carrier:gls-ch). For Luxembourg, see [POST Luxembourg](carrier:post-luxembourg); for Malta, [MaltaPost](carrier:maltapost). For the UK and Ireland, see also [DHL eCommerce UK](carrier:dhl-ecommerce-uk), [Whistl](carrier:whistl) and [DPD Ireland](carrier:dpd-ie).

### Whose tracking number starts with 9 or 420?

A 22-digit number starting `92` to `95`, or a 26-digit one starting `92` to `94`, is USPS. If you scanned the barcode and the digits start with `420`, that part is `420` plus the destination ZIP Code (5 or 9 digits). USPS uses it for routing and leaves it out of the printed number, so your tracking number starts at the `92`, `93`, `94` or `95` that follows.

## How many digits is a tracking number?

There's no single length. These are the lengths carriers give on their own pages, and the overlaps show why a bare number can fit several:

| Length | Who uses it |
| --- | --- |
| 10 digits | DHL Express; Mondial Relay (8, 10 or 12) |
| 12 digits | FedEx Ground Economy; Canada Post Priority Worldwide; Mondial Relay |
| 14 digits | DPD in Austria and Belgium (DPD Germany says 14 characters); [La Poste](carrier:la-poste) in France |
| 15 digits | FedEx Ground numbers: FedEx told Ground Economy shippers receiving them that the length would change from 31 May 2022; Canada Post's Delivery Notice Card |
| 16 digits | Canada Post online labels; Evri (16 characters) |
| 22 digits | USPS; Portugal's CTT; Austrian Post; TIPSA |
| 26 digits | USPS, on some labels |
| 13, 14, 16 or 21 characters | Royal Mail (13, 16 or 21) and Parcelforce (13 or 14), in Peek's carrier data |
| 10 to 39 characters | DHL in Germany |

## Whose tracking number starts with TBA?

Amazon. In the carrier data Peek uses, `TBA`, `TBC` or `TBM` followed by 12 digits means [Amazon Logistics](carrier:amazon-logistics), Amazon's own delivery service (or [Amazon Shipping](carrier:amazon-shipping) when Amazon carried the parcel for another shop). Amazon's own guide to tracking a package doesn't mention the number: it sends you to Your Orders, then Track Package.

## The postal format: two letters, nine digits, two letters

National posts share one 13-character format, set by the Universal Postal Union (UPU) in its standard S10. Here it is taken apart:

:::anatomy RR 00000000 5 CH
- RR | Type of item: R for a registered letter
- 00000000 | Serial number, eight digits
- 5 | Check digit, worked out from the serial
- CH | Country that issued the number: Switzerland
:::

For mail crossing a border, the first letter tells you the kind of item. Posts may use the letters differently at home.

- `E`: EMS, the posts' express service
- `C`: parcel
- `R`: registered letter
- `L`: tracked letter
- `V`: insured letter
- `U`: goods sent as a letter, without tracking for you

The last two letters are not always where the parcel started. The UPU says the code "cannot be used as a reliable indicator of the geographic origin of an item": Swiss Post uses `CH` on items it sends from its offices abroad, and Portugal's CTT gives codes ending `PT` to items its contract customers send from Spain, China and elsewhere.

The check digit catches typos. To test a number:

1. Multiply the eight serial digits by 8, 6, 4, 2, 3, 5, 9 and 7, and add the results.
2. Divide by 11 and take the remainder away from 11.
3. A result of 10 becomes 0, and 11 becomes 5.

For `RR000000005CH`, all eight serial digits are 0, so the sum is 0 and leaves 0, and 11 − 0 = 11, which becomes 5. If a postal number you copied by hand fails this test, look for a mistyped or swapped digit.

### Whose tracking number starts with UL or UU?

If it has 13 characters and ends in two letters, such as `CN`, it's a postal number from the `U` range: goods sent as a letter. The last two letters show which country's post issued it. A longer number starting `UU` isn't in that format: check the shipping email for the carrier.

> On mail from abroad, a postal number starting with `U` (`UA` to `UZ`) is not meant for you to follow. The UPU uses that range for goods sent as letters "without customer-oriented tracking", and bpost says plainly that you cannot track such a parcel. Don't count on it to start updating later.

## Numbers that are not your tracking number

Other numbers travel with a parcel. Some lead back to it, some don't:

- **Order number.** The shop's own reference. DHL reminds you that a tracking number is not an order number.
- **Missed-delivery cards.** A FedEx door tag (`DT` + 12 digits, US and Canada only), an Evri calling card (8 digits), a Canada Post Delivery Notice Card (15 digits) and the barcode on a USPS PS Form 3849 all lead back to the parcel in their carrier's tracking. [What to do after a missed delivery](guide:missed-delivery) depends on the carrier.
- **An Post's Trans Ref ID.** It only records your transaction with An Post and tracks nothing.

Have only an order number? [Where to find a tracking number](guide:find-tracking-number) shows where to look in your order.

## Why your tracking number can change on the way

A parcel that crosses a border often changes hands, and each company can print its own label. The UPU lets the receiving post add its own barcode next to the original, as long as it isn't in the 13-character postal format.

:::journey
- shop | Seller | Prints the label and the first number
- plane | Long haul | Travels on that first number
- customs | Customs | Checked in your country
- handover | Local carrier | Can add its own label and number
- home | Your door | The last scans may sit under the new number
:::

- **Postal items** are the easy case: the UPU allows only one S10 number per item, so it usually works on both posts' sites.
- **FedEx Ground Economy** shipments in the US get a 12-digit FedEx number and a USPS tracking number as well.
- **AliExpress economy shipping:** Cainiao says tracking stops at the handover to the local carrier, and that the seller can tell you how to reach that carrier. More in [tracking a parcel from China](guide:tracking-from-china).

Some trackers join the two numbers once the first carrier's data shows the new one: see [universal parcel tracking](guide:universal-tracker).

A number that returns nothing isn't always mistyped. Portugal's CTT gives two reasons: the code exists only for customs, or another company delivers the item. If a valid number simply hasn't moved yet, read [why tracking stops updating](guide:tracking-not-updating).

:::sources
- [UPU: S10 standard, Identification of postal items](https://www.upu.int/UPU/media/upu/files/postalSolutions/programmesAndServices/standards/S10-12.pdf) – the postal format, its letters, check digit and country code
- [USPS: Publication 199, Intelligent Mail package barcode](https://postalpro.usps.com/pub199) – 22 and 26 digits, 92 to 95, the hidden 420 and ZIP Code
- [USPS: How to find your tracking number](https://faq.usps.com/s/article/How-to-find-your-tracking-number) – the PS Form 3849 barcode
- [UPS: sample package label](https://www.pld-certify.ups.com/CerttoolHelp/PLD0200/WebHelp_pld0200/LeadPackage.htm) – the 1Z number
- [FedEx Developer: FedEx Ground Economy announcement](https://developer.fedex.com/api/en-us/announcements/Apr2022-FGEAnnouncements.html) – 12 and 15 digits, the USPS number alongside
- [FedEx Developer: Track API](https://developer.fedex.com/api/en-us/catalog/track/docs.html) – door tag numbers
- [DHL Express Netherlands: DHL Express or DHL eCommerce number](https://www.dhlexpress.nl/en/consumer/faq/express-account-zendingsnummer/my-shipment-number-dhl-express-or-dhl-ecommerce) – 10 digits
- [DHL: Sendungsverfolgung, Probleme und Lösungen](https://www.dhl.de/de/privatkunden/hilfe-kundenservice/sendungsverfolgung/probleme-loesungen.html) – 10 to 39 characters, not an order number
- [Evri: Track a parcel](https://www.evri.com/track-a-parcel) – 16 characters and the calling card
- [InPost UK: Yodel by InPost help](https://inpost.co.uk/yodel-help) – numbers starting JD, JJD or 8
- [DPD UK: How can we help](https://www.dpd.co.uk/content/how-can-we-help/index.jsp) – reference number and postcode
- [DPD Germany: Wo finde ich meine Paketnummer?](https://www.dpd.com/de/de/faq/wo-finde-ich-meine-paketnummer/) – 14 characters
- [DPD Austria: Wo finde ich die Paketnummer?](https://www.dpd.com/at/de/faq/wo-finde-ich-die-paketnummer-2/) – 14 digits
- [DPD Belgium: Where do I find the parcel number?](https://www.dpd.com/be/en/faq/where-do-i-find-the-parcel-number-2/) – 14 digits
- [Canada Post: How to use Track](https://www.canadapost-postescanada.ca/cpc/en/support/kb/tracking/how-to-use-track.page) – 16 digits, 13 characters, the Delivery Notice Card
- [Canada Post: Find your tracking number](https://www.canadapost-postescanada.ca/cpc/en/support/kb/tracking/find-your-tracking-number.page) – lengths by product
- [Australia Post: Help with tracking](https://auspost.com.au/help/parcel-tracking/tracking-support/help-with-tracking) – tracking number or article ID
- [An Post: Track a parcel](https://www.anpost.com/Post-Parcels/Track/Search) – the format and the Trans Ref ID
- [Austrian Post: FAQ](https://www.post.at/en/p/c/faq) – 22 digits starting 10
- [XDP: Track](https://xdp.co.uk/track/) – consignment number and postcode
- [APC: APC Choice tracking and FAQ](https://apcchoice.apc-overnight.com/track-parcel) – 7-digit consignment number and postcode
- [TIPSA: Delivery locator](https://www.tip-sa.com/en/delivery-locator) – 22 digits
- [YunExpress: YunTrack](https://www.yuntrack.com/) – YunExpress's own tracking site
- [4PX: Global Order Tracking](https://track.4px.com/) – 5 to 50 letters and digits
- [Yanwen: YW Tracking](https://track.yw56.com.cn/en/index) – Yanwen's own tracking site, no example number
- [Cainiao: Help centre](https://global.cainiao.com/helpDoc.htm) – no tracking after the handover with economy shipping
- [Cainiao: Global Express Tracking](https://global.cainiao.com/) – example number
- [Cainiao: Guidance](https://global.cainiao.com/helpDoc.htm?slug=ofzig4) – letters and digits
- [bpost: Puis-je suivre mon colis en ligne ?](https://www.bpost.be/fr/faq/puis-je-suivre-mon-colis-en-ligne) – barcodes starting with U cannot be tracked
- [CTT: Encontrar o código de envio](https://www.ctt.pt/ajuda/particulares/seguir-ou-alterar-entrega/seguir/encontrar-o-codigo-de-envio) – 22 digits, PT codes from abroad, codes that return nothing
- [Mondial Relay Belgium: Suivi de colis](https://www.mondialrelay.be/fr-be/suivi-de-colis/) – 8, 10 or 12 digits
- [La Poste: Comment suivre mon colis ou ma lettre](https://aide.laposte.fr/professionnel/contenu/comment-suivre-mon-colis-ou-ma-lettre) – 14 digits
- [About Amazon UK: How to track an Amazon package](https://www.aboutamazon.co.uk/news/retail/how-to-track-an-amazon-package) – Your Orders, then Track Package
- [Universal Parcel Scraper: carrier catalog](https://github.com/plhery/universal-parcel-scraper/blob/main/data/catalog.json) – the TBA, YT, LP and CNG shapes in Peek's carrier data, InPost's JD, JJD and 8YDR shapes, 4PX's 4PX and LP shapes, Yanwen's BYS, YP and YW shapes, XDP's ZDB shape, APC's 22-digit shape, and Royal Mail's and Parcelforce's shapes
:::
