---
title: CNE Express sporing: 3A5V-numre, statusser og kontakt
description: Spor en CNE Express-pakke (递一物流) fra Kina: 3A5V-format, statusser som Arrived at destination og told, lokale partnere, leveringstid og kontakt.
slug: cne-express-sporing
published: 2026-11-27
updated: 2026-11-27
---

Spor en CNE Express-pakke (递一物流, CNE EXPRESS CO LTD, grundlagt i Shanghai i 2003) fra Kina ved at indsætte dit `3A5V`-nummer efterfulgt af 9 cifre i feltet ovenfor eller på [CNE’s engelske sporingsside](https://www.cne.com/en/track). CNE fragter grænseoverskridende ordrer for sælgere på eBay, Walmart, TikTok, Temu og SHEIN og overlader den sidste del af leveringen til lokale partnere som USPS, Royal Mail, DHL, La Poste, Evri, GLS eller DPD — tjek `Last mile number` under `Shipment Information` hos CNE, eller lad Peek følge genkendte overdragelser automatisk.

## CNE Express sporingsnummer: hvordan det ser ud

CNE offentliggør intet officielt nummerformat. Den form, Peek kender som CNE’s (med opdigtede cifre):

| Nummer | Hvad det er |
| --- | --- |
| `3A5V482910573` | `3A5V` og 9 cifre: et CNE Express-nummer. |
| `3A5V48291057` | Kun 8 cifre: ikke CNE’s form. Peek nævner ikke CNE og tjekker det hos Colis Privé, hvis 12-tegns numre det matcher, samt sporingstjenester. Tjek for et manglende ciffer. |

Små bogstaver, mellemrum og bindestreger betyder intet i Peek. CNE’s sporing tager flere numre på én gang, adskilt af mellemrum eller kommaer.

**Hvor du finder det.** I sælgerens forsendelsesmail eller på din ordreside. Finder du det ikke, se [sådan finder du dit sporingsnummer](guide:find-tracking-number) (på engelsk), eller guiderne til [Temu-ordrer](guide:temu-orders) og [SHEIN-ordrer](guide:shein-orders) (begge på engelsk).

**Andre numre.** Under `Shipment Information` kan CNE’s sporing også vise sælgerens `Order number` og et `Last mile number`: det lokale fragtfirmas nummer til din pakke sammen med dets webside og telefonnummer.

**CNE’s postpakker.** CNE sælger også China Posts egne tjenester som International E-packet og China Post Packet. For et nummer i China Posts format, se [China Post](carrier:china-post).

## Forklaring på CNE Express sporingsstatusser

CNE’s sporing viser en overordnet status øverst over linjerne. På engelsk, som CNE navngiver dem:

| CNE’s status | Hvad det betyder for dig | Peek viser |
| --- | --- | --- |
| `Shipping` | Undervejs. | den nyeste linjes status |
| `Customs inspection` | Tilbageholdt i tolden (`扣关` på CNE’s kinesiske side). | "At customs" |
| `Delivered` | Leveret. | "Delivered" |
| `Invalid address`, `Lost`, `Exception`, `Destroyed` | Forkert adresse, bortkommet eller ødelagt pakke, eller et andet problem. | "Needs attention" |
| `Returned` | Sendt retur til sælgeren. | "Returned to sender" |
| `Not delivered` | CNE’s kinesiske side siger `未发送` («ikke afsendt»): pakken er ikke sendt endnu. | den nyeste linjes status |

`Time out` og `Unknown` kan også forekomme: der følger Peek de enkelte linjer. Hver linje har et tidspunkt, CNE’s tekst og et sted. De linjer, en pakke typisk møder i rækkefølge:

| CNE skriver | Hvad det betyder for dig | Peek viser |
| --- | --- | --- |
| `Parcel Infomation Received` | Sælgeren sendte pakkedata til CNE (stavefejlen er CNE’s). CNE har den ikke endnu. | "Label created" |
| `Picked Up by Courier` | CNE hentede pakken hos sælgeren i Kina. | "Received by carrier" |
| `Arrived at CNE Facility`, `Inbound scan`, `Shipment Processed`, `Shipment Loaded` | På et CNE-anlæg: indscannet, sorteret, lastet. | "In transit" |
| `Departed CNE Facility`, `Departure Scan`, `Depart From Local Facility` | Forlod anlægget. | "In transit" |
| `Arrived Export Airport`, `Released From Export Customs` | I lufthavnen i Kina og derefter gennem Kinas eksporttold. Ikke dit lands told. | "In transit" |
| `Shipment Departed From Original Country` | Flyet er lettet fra Kina. | "In transit" |
| `Arrived At Transit Airport`, `Shipment Depart From Transit Airport` | Mellemlanding undervejs. | "In transit" |
| `Arrived at Import Airport`, `Arrived at destination` | Landet i dit land. Sortering og told mangler stadig: pakken er endnu ikke på dit lokale udleveringssted. | "In transit" |
| `The shipment is picked up by customs broker` | En toldklarerer har overtaget den til importtoldbehandling. | "At customs" |
| `Released From Import Customs`, `Pickup from airport` | Gennem importtolden og afhentet fra lufthavnen. | "In transit" |
| `Handled by local carrier` | CNE har overdraget den til transportøren, der leverer i dit land. Den lokale transportørs første scanning følger bagefter. | "In transit" |
| `Arrived At The Delivery Company`, `Your shipment has arrived at sorting center`, `Your shipment is in process at sorting center`, `Parcel Unloaded From Container` | Hos det lokale fragtfirma eller på et sorteringscenter før det. | "In transit" |
| `Your shipment has been processed at sorting center and ready for shipping`, `Your shipment has departed from the transit origin`, `Your shipment has arrived at the transit destination` | Sorteret i dit land og sendt videre til den lokale udbringningsstation, hvor den ankommer før omdelingen. «Transit» her er denne sidste etape, ikke et stop i udlandet. | "In transit" |
| `On Board with Courier` | I buddets bil: ude til levering. | "Out for delivery" |
| `Your shipment delivery attempt failed [Recipient not at home]` | Buddet traf ingen hjemme. Leveringen forsøges igen. | "Delivery attempted", eller "Needs attention" mens CNE’s status er `Exception` |
| `Sorting failed []` | Sorteringen fejlede. De tomme klammer er CNE’s. | "Needs attention" |
| `It was returned to customer's address by CNE` | CNE sendte den retur til sælgeren, som er CNE’s kunde. | "Returned to sender" |

Efter overdragelsen vises den lokale transportørs egne linjer i CNE’s historik, ofte som en kort betegnelse fulgt af en forklaring. Peek læser betegnelsen, og for ukendte tekster den status, CNE knytter til linjen. CNE’s sporing viser klokkeslæt uden tidszone, og de bruger ikke alle samme ur (se spørgsmålene nedenfor).

## Hvem leverer CNE Express-pakker

CNE driver egne ruter til Europa, USA, Canada, Australien, Brasilien og andre lande og bruger lokale partnere til udbringningen. Partnersiden nævner blandt andet USPS, Royal Mail, Austrian Post, Correos, Poste Italiane, GLS, La Poste, DHL, Evri, DPD og POST Luxembourg som globale logistikpartnere.

Peek genkender tre af CNE’s koder for lokale leverandører: DHL i Tyskland ([DHL Paket](carrier:dhl)), [Royal Mail](carrier:royal-mail) i Storbritannien og [USPS](carrier:usps) i USA. Når CNE nævner en af dem sammen med dennes eget pakkenummer, viser Peeks pakkekort det (for eksempel "Delivery with USPS") med nummeret. Mens den lokale transportør kun har forhåndsmeldingen uden egen scanning, står der i stedet "Announced to …, not received yet". Uden dette nummer angiver Peek ingen lokal transportør, fordi den lokale transportør sporer på sit eget nummer, ikke CNE’s. Andre koder, såsom US SPX, giver ingen transportør i Peek.

CNE offentliggør ingen regler for leveringsforsøg, afhentningskort eller pakkeshops — dem fastsætter det lokale fragtfirma. Se for eksempel [Evri](carrier:evri), [La Poste / Colissimo](carrier:la-poste), [Correos](carrier:correos-spain), [Poste Italiane](carrier:poste-italiane), [Austrian Post](carrier:austrian-post) eller [POST Luxembourg](carrier:post-luxembourg).

**Told i EU.** Varer købt online uden for EU har siden 1. juli 2026 haft en toldsats på 3 € pr. varetype på pakker op til 150 €, og siden 1. november 2026 et EU-håndteringsgebyr på 2 € pr. varetype uanset pakkens værdi. En varetype er varer med samme toldtarif, beskrivelse og eventuelle oprindelse. I EU’s momsområde betales også importmoms, medmindre den blev opkrævet ved købet via IOSS. CNE meddelte sine sælgere den 22. oktober 2026, at CNE på sine EU-ruter opkræver 2 €-gebyret forud hos sælgerne fra 26. oktober pr. tarifkode i hver pakke — oven i 3 €-tolden, som CNE har opkrævet på samme måde siden juli — og fortolder ud fra sælgerens deklaration. Meddelelsen siger intet om moms eller om gebyrer, den lokale transportør eventuelt opkræver. Se også [pakker i tolden](guide:customs) (på engelsk).

**Forsinkelser.** CNE offentliggør driftsforstyrrelser som strejker eller dårligt vejr i udlandet på sin kinesiske nyhedsside (`新闻公告`).

## CNE Express-pakke opdateres ikke, er bortkommet eller returneret

CNE’s kunder er de sælgere, der bestiller fragten: ved forsinket, bortkommet, beskadiget eller returneret pakke skal du kontakte sælgeren eller markedspladsen først.

**Intet fundet endnu.** Finder CNE’s sporing intet, har CNE endnu ingen registrering af nummeret: Peek viser "Waiting for CNE Express" og bliver ved med at spørge. Hvis CNE stadig ikke kender nummeret efter to dage og ingen sporingstjeneste finder det, skriver Peek: "After two days, the carrier still doesn’t know this number. Check it, choose another carrier or delete the parcel." `Not delivered` betyder, at pakken ikke er sendt endnu (`未发送` på den kinesiske side); Peek viser så nyeste linjes status, for eksempel "Label created" for `Parcel Infomation Received`. Sker der intet efter nogle dage, så tjek nummeret hos sælgeren.

**Ingen opdateringer i flere dage.** Når en pakke undervejs ikke har fået en ny linje i fire dage, markerer Peek den med datoen for sidste linje ("No tracking update since Mon 12 Oct") og bemærker, at pakker ofte er stille på lange flyvninger, i tolden eller hen over weekenden; efter tre uger uden nyt råder Peek til at spørge sælgeren. Så længe Peek følger pakken hos CNE, indeholder noten et "Get help from CNE Express"-link til CNE’s kontaktside. Se [hvorfor sporingen stopper](guide:tracking-not-updating) (på engelsk).

**I tolden.** `Customs inspection` (`扣关`) eller `The shipment is picked up by customs broker` betyder, at tolden har pakken. Da CNE fortolder efter sælgerens angivelser, bør du spørge sælgeren først, hvis pakken bliver hængende.

**Bortkommet, ødelagt, forkert adresse eller retur.** Ved `Lost`, `Destroyed` eller `Invalid address` skal du straks give sælgeren besked og bede om tilbagebetaling eller en ny forsendelse. `Returned` eller `It was returned to customer's address by CNE` betyder, at pakken gik tilbage til sælgeren: bed butikken sende igen eller refundere.

**Leveret, men ikke modtaget.** På CNE’s sporing rummer en linje med `View POD` leveringsbeviset (f.eks. et foto): CNE beder om modtagerens navn eller postnummer for at vise det. Tjek hvor den blev lagt, spørg naboer og kontakt derefter sælgeren. Se [leveret, men ikke modtaget](guide:delivered-not-received) (på engelsk).

## CNE Express kundeservice og kontakt

På CNE’s [kontaktside](https://www.cne.com/en/about/contact):

- **Hotline:** `400-021-5600`, et kinesisk servicenummer (National Service Hotline).
- **Hovedkontor i Shanghai:** `021-5109 6677`, 15/F, Block G, Lane 1777, Hualong Road, Qingpu District, Shanghai.
- **Åbningstider:** 08:30 til 18:00, mandag til søndag. CNE angiver ingen tidszone; sorteringscentrene arbejder døgnet rundt.
- **Onlineformular:** forespørgselsformularen på samme side. Vælg `Complaint` som type og angiv telefon, e-mail, navn, sporingsnummer og besked.

CNE oplyser ingen kundeservice-e-mail; de øvrige kontorer i Kina betjener sælgere. Kontakt sælgeren først om din ordre.

## Spor en CNE Express-pakke med Peek

[Peek](/) genkender straks CNE Express på et `3A5V`-nummer ("Detected carrier"). Et cne.com-sporingslink med `?no=` lægger pakken under CNE Express uanset nummerformat; indeholder linket flere numre, tager Peek det første. For andre CNE-numre indsætter du nummeret alligevel (Peek følger over 4.500 transportører). Passer formen til flere transportører, viser Peek "Checking tracking services…", mens den spørger rundt; passer den ingen, slås nummeret op efter tilføjelse. Vises CNE ikke, tryk "Change" og vælg CNE Express under "All carriers" (søgning på `CNE` eller `递一物流` finder den også), eller brug "Change carrier" på en allerede tilføjet pakke.

Peek læser CNE’s egen sporing: status, hver linje med sted, destinationsland samt den lokale transportør, når CNE opgiver en af de tre ovennævnte med nummer. Den læser `Arrived at destination` og overdragelsen til lokal transportør i Beijing-tid, udleder uret på de øvrige linjer ud fra stedet eller etapen, og viser alle tider i din enheds tidszone. Sælgerens ordrenummer, lokal transportørs webside og telefon samt CNE’s leveringsbevis udelades. CNE angiver ingen forventet leveringsdato eller vægt.

Når CNE opgiver den lokale transportør og dens nummer, spørger Peek også den transportør: en af de tre ovennævnte, dit lands postvæsen for et internationalt postnummer (to bogstaver, ni cifre, to bogstaver), eller den ene transportør nummerformen peger på. Så snart den lokale transportør har en scanning mindst lige så ny som CNE’s seneste, følger Peek pakken der og bevarer CNE’s historik.

Mens du har pakken åben, eller på leveringsdagen med notifikationer tændt, tjekker Peek op til hvert 10. minut og op til hvert 2. minut fra kl. 6:00 lokal tid på dagen, hvor CNE scanner pakken ude til levering, så længe CNE’s egen sporing svarer (via sporingstjenester op til hvert 15. minut); ellers hver time. Overtager en anden transportør leveringen, følger Peek dennes tempo. Efter to dage uden nyt (12 timer ved "Label created" eller "Received by carrier") tjekker Peek hver time. Du kan få notifikationer ved "Every scan", "Important steps" eller "Delivery only". Peek kan ikke kontakte CNE eller sælgeren for dig.

## Spørgsmål om CNE Express sporing

### Er CNE Express (递一物流) en ægte transportør?

Ja. CNE EXPRESS CO LTD (递一国际物流有限公司), grundlagt i 2003 med hovedkontor i Shanghai, driver sorteringscentre i Jiaxing og Dongguan med kapacitet til over 2 millioner forsendelser om dagen siden 2020 og fragter for sælgere på Amazon, eBay, Walmart, TikTok, Temu og SHEIN.

### Hvem leverer CNE Express-pakker, og hvor finder jeg det lokale sporingsnummer?

CNE overlader den endelige levering til en lokal post- eller pakkepartner — partnersiden omfatter USPS, Royal Mail, DHL, La Poste, Correos, Poste Italiane, Austrian Post, GLS, DPD, Evri og POST Luxembourg. Se under `Shipment Information` i CNE’s sporing efter `Last mile number` (Peek følger automatisk DHL Tyskland, Royal Mail, USPS og gyldige nationale postoverdragelser).

### Hvad betyder «Arrived at destination» i CNE Express sporing?

Din pakke er landet i dit land — ikke på dit lokale udleveringssted. Sortering og importtoldbehandling kommer stadig før overdragelse til det lokale fragtfirma. Da CNE registrerer denne scanning på Beijing-tid mens lokale scanninger kan bruge lokal tid, kan klokkeslæt se forskudte ud.

### Hvor lang tid tager levering med CNE Express?

CNE’s produktsider angiver referencetider på 3 til 8 hverdage for CNE Priority Express (til USA, Storbritannien, Frankrig og Tyskland) og 5 til 20 hverdage for CNE Standard Express; følg den leveringsfrist, butikken opgiver.

### Hvordan kontakter jeg CNE Express om en forsinket eller bortkommet pakke?

Spørg sælgeren først, da sælgeren har fragtaftalen med CNE. Vil du kontakte CNE direkte, kan du ringe til den kinesiske hotline `400-021-5600` (eller Shanghai-kontoret `021-5109 6677`, man–søn 08:30–18:00) eller indsende formularen på [cne.com/en/about/contact](https://www.cne.com/en/about/contact) under `Complaint`.

:::sources
- [CNE: Track your parcel](https://www.cne.com/en/track) – tracker, status labels, lines, Shipment Information, View POD
- [CNE: 运单追踪 (tracking, Chinese)](https://www.cne.com/track) – 未发送 and 扣关 behind the English labels
- [CNE: Home](https://www.cne.com/en) – 2003, Shanghai, local partners abroad, destinations, platforms, hotline
- [CNE: 首页 (home, Chinese)](https://www.cne.com/) – 递一物流, 递一国际物流有限公司
- [CNE: About us](https://www.cne.com/en/about/intro) – sorting centres, capacity of over 2 million shipments a day since 2020, platforms
- [CNE: Partner](https://www.cne.com/en/about/partner) – global logistics partners
- [CNE: Contact us](https://www.cne.com/en/about/contact) – hotline, Shanghai office, hours, consulting form
- [CNE: Postal Parcel](https://www.cne.com/en/solutions/economyParcel) – China Post's services sold by CNE
- [CNE: CNE Priority Express](https://www.cne.com/en/solutions/product/36024) – 3 to 8 business days, routes
- [CNE: CNE Standard Express](https://www.cne.com/en/solutions/product/36025) – 5 to 20 business days
- [CNE: 新闻公告 (news and notices)](https://www.cne.com/news/notice) – delay notices by destination
- [CNE: 关于欧盟国家2026年11月税改通知 (EU tax change notice)](https://www.cne.com/news/article/rDe4ANa6KYOW) – €2 collected from sellers in advance from 26 October, on top of the €3, per parcel and tariff code; customs cleared on the seller's declaration
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – €3 duty since 1 July 2026, €2 Union handling fee since 1 November 2026
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – €2 per item whatever the value, what an item is
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – number shape and links, wordings and codes (last-mile sorting, station and missed-delivery lines since 9 November 2026), last-mile suppliers, clocks, hand-over, what Peek reads
:::
