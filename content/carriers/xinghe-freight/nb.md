---
title: Xinghe Freight-sporing: GZXH…YQ-nummer, statuser og hjelp
description: Spor en pakke fra Xinghe Freight (星和货运) fra Kina: GZXH…YQ-format, hvorfor sporingen går via GZYL, forhåndsskrevne Delivered-trinn og kontakt.
slug: xinghe-freight-sporing
published: 2026-11-27
updated: 2026-11-27
---

Xinghe Freight (星和货运, 广州星和货运代理有限公司) er en speditør i Guangzhou i Kina som ble grunnlagt i 2014 og håndterer sjø-, fly- og landfrakt, skipsagentur og tollklarering. For å spore pakken din limer du inn det 16 tegn lange nummeret på formen `GZXH`, ti siffer og `YQ` i feltet over. Nettstedet til Xinghe Freight sender sporingssøk videre til [GZYL](carrier:gzyl) sin ukrypterte HTTP-sporing, der trinn — inkludert `Delivered` — kan være planlagt på forhånd i stedet for å være virkelige skanninger. Peek leser den sporingen for deg, skiller forhåndsskrevne spådommer fra ekte skanninger og spør sporingstjenester i reserve når det trengs.

## Sporingsnummer hos Xinghe Freight: slik ser det ut

Numrene Peek kjenner igjen som Xinghe Freight består av `GZXH`, ti siffer og `YQ`, til sammen 16 tegn. Hvis den siste bokstaven `Q` mangler (`GZXH`, ti siffer og `Y`, 15 tegn), kjenner Peek likevel igjen nummeret og legger `Q` tilbake før sporingen spørres. Eksempler med oppdiktede siffer:

| Nummer | Hva det er |
| --- | --- |
| `GZXH4829105736YQ` | `GZXH`, ti siffer og `YQ`: Xinghe Freights standardformat. Peek velger Xinghe Freight med én gang. |
| `GZXH4829105736Y` | `GZXH`, ti siffer og `Y`: mangler siste `Q`, men Peek legger den tilbake automatisk. |
| `GZXH482910573YQ` | Bare ni siffer: ikke Xinghe Freights format. Peek avviser nummeret straks så du kan sjekke om et siffer mangler. |
| `YL4829105736YQ` | `YL`, ti siffer og `YQ`: fraktbrevformatet til [GZYL](carrier:gzyl). Samme sporingssystem, men en annen speditør. |
| `JXCAA4829105736YQ` | Slutter også på `YQ`, som mange kinesiske speditører gjør, men begynner på `JXC`: et nummer fra [Juxi Express](carrier:juxi-express). |

Mellomrom, bindestreker og små bokstaver spiller ingen rolle når du limer nummeret inn i Peek.

**Hvor du finner nummeret.** Selgeren bestiller frakten hos Xinghe Freight og gir deg nummeret i forsendelsesbekreftelsen eller på bestillingssiden. Finner du det ikke, spør selgeren.

## Hva statusene hos Xinghe Freight betyr

Xinghe Freights sporing kjører på GZYL sin plattform, så hvert trinn kommer med GZYLs engelske ordlyd, inkludert skrivefeil. Fordi Peek-appen ikke har norsk grensesnitt, vises også Peeks hovedtrinn på engelsk: «Label created» (etikett opprettet), «Received by carrier» (mottatt av transportør), «In transit» (underveis), «At customs» (i tollen) og «Delivered» (levert). Trinnene i vanlig rekkefølge:

| Sporingen skriver | Hva det betyr for deg | Peek viser |
| --- | --- | --- |
| `Order information received` | Selgeren har sendt ordredataene. Ingen har pakken fysisk ennå. | «Label created» |
| `Arrive at the cross border receiving point` | Speditøren har mottatt pakken: første ekte skanning i deres hender. | «Received by carrier» |
| `Goods leave the operation center` | Pakken har forlatt driftssenteret i Kina. | «In transit» |
| `Arrive at the international airport` | På den internasjonale flyplassen i påvente av fly. | «In transit» |
| `Departing from the airport` | Flyet har lettet. | «In transit» |
| `Item arrives at destination airport` | Landet i mottakerlandet. | «In transit» |
| `local carrier Agent Import customs clearance` | En lokal agent klarerer pakken gjennom importtollen. | «At customs» |
| `The import customs keep it for inspection` | Tollen i landet ditt har tatt pakken ut til kontroll. | «At customs» |
| `Customs clearance completed` | Fortollingen er ferdig, og pakken fortsetter videre. | «In transit» |
| `Internationaliy Package received at processing center` | Et behandlingssenter i landet ditt har mottatt den fortollede pakken. Det er ikke en ny innhenting hos avsender. | «In transit» |
| `Leave the email processing center` | Pakken har forlatt det behandlingssenteret. | «In transit» |
| `Arrival sorting, distribution center` | Ankommet et sorterings- eller distribusjonssenter i landet ditt, med stedet etter teksten. | «In transit» |
| `Leaving the sorting center` | Forlater et sorteringssenter, med stedet etter teksten. | «In transit» |
| `Package is on the way` | Underveis videre i landet ditt, med et sted. Det betyr ikke at pakken er ute til levering med bud. | «In transit» |
| `Delivered` | Levering med sted. Kan være en plan og ikke en ekte skanning: se under. | «Delivered», med en merknad om fast plan, med mindre Peek holder trinnet tilbake (se under) |

**Via Hongkong.** En pakke kan også vise `香港处理中心已收寄【Hong Kong】`: en postinnlevering ved behandlingssenteret i Hongkong som sporingen gjengir på kinesisk med byen i klammer. Peek viser linjen som den står under «Received by carrier».

**En linje på kinesisk.** For en ordre uten skanninger ennå skriver plattformen automatisk `货物电子信息已经收到` (elektronisk vareinformasjon mottatt). Selgeren har opprettet ordren, men ingen har skannet pakken. Peek viser linjen på kinesisk under «Label created».

**Klokkeslett.** Peek leser hvert klokkeslett i sporingen som Peking-tid (`UTC+8`), inkludert trinn i Norge, og viser det i din egen tidssone. Om vinteren er 20:00 i Peking det samme som 13:00 norsk tid (`UTC+1`), og om sommeren 14:00 (`UTC+2`).

**Trinn skrevet på forhånd.** På denne sporingsplattformen kan speditører legge inn hele tidsplanen til en pakke på forhånd — fra innhenting til siste trinn i landet ditt — og sporingen viser hvert trinn når tiden kommer. Et slikt trinn er en plan, ikke en skanning av pakken din. På GZYLs sporing avsluttes planen med et `Delivered` som plattformen legger til i en kveldskjøring den dagen den viser: datert dagen etter planens siste trinn, i kontortiden i Peking, uansett hvilket land du bor i. Peek holder disse trinnene tilbake (se under), men en kopi hos en annen sporingstjeneste kan likevel vise dem, så et `Delivered` der beviser ikke at pakken har kommet frem.

## Hvem leverer Xinghe Freight-pakker i Norge

Peek vet ikke hvilket lokalt selskap som leverer Xinghe Freight-pakker i Norge. Trinnene i mottakerlandet nevner ingen transportør, og nettstedet til Xinghe Freight sier ingenting om pakker til nettkunder. Det eneste lokale selskapet Peek kan overføre en Xinghe-pakke til er [Swiss Post](carrier:swiss-post), i Sveits og Liechtenstein, når Xinghes datapost navngir det.

Nettstedet til Xinghe Freight oppgir verken leveringstider, antall leveringsforsøk eller hentesteder: alt dette følger reglene til det lokale leveringsselskapet. Spør selgeren hvilket selskap som leverer bestillingen din i Norge, og be om det selskapets sporingsnummer.

**Toll og avgifter.** Pakker fra Kina til Norge følger norske regler for import og mva. For nettkjøp levert til et EU-land gjelder siden 1. juli 2026 en fast toll på 3 € per varetype på sendinger opptil 150 €, og siden 1. november 2026 et EU-håndteringsgebyr (Union handling fee) på 2 € per varetype uansett verdi. Mer i guiden om [pakker i tollen](guide:customs) (på engelsk).

## Xinghe Freight-pakke som ikke kommer frem eller sporing som står stille

**Bare `Order information received` eller `货物电子信息已经收到`.** Selgeren har opprettet ordren, men ingen har skannet pakken ennå. Skjer det ingenting etter noen dager, spør selgeren om den faktisk er sendt.

**«Waiting for Xinghe Freight».** Peek har ingen bekreftet skanning for nummeret ditt ennå: enten kjenner ikke GZYLs sporing nummeret, eller så inneholder den bare forhåndsskrevne trinn (som kan slutte med `Delivered`), og sporingstjenestene har heller ingenting. Peek fortsetter å sjekke. For et ukjent nummer viser GZYLs sporing én tom rad med nummeret som referanse; Peek tolker det som ikke funnet, viser «Waiting for Xinghe Freight» og to dager senere «Check the tracking number». En forhåndsskrevet plan teller derimot som at Xinghe har ordren, så «Waiting for Xinghe Freight» blir stående uten oppfordring til å kontrollere nummeret. Sammenlign likevel nummeret tegn for tegn med meldingen fra selgeren.

**Står fast i tollen.** Blir du bedt om å betale importavgifter, se guiden om [pakker i tollen](guide:customs) (på engelsk). Blir pakken stående i flere dager, gi beskjed til selgeren.

**Sporingen eller en annen side viser flere trinn enn Peek.** De ekstra linjene — inkludert `Delivered` — kan være en forhåndsskrevet plan som Peek holder tilbake. Følg leveringsanslaget butikken oppga og kontakt selgeren når det har passert. Mer i guiden om [sporing av pakker fra Kina](guide:tracking-from-china) (på engelsk).

**`Delivered`, men ingenting kom.** Trinnet kan være en del av en fast tidsplan. Med mindre Swiss Post leverer, viser Peek under «Delivered»: «Xinghe Freight updates on a fixed schedule, with no signature. If nothing came, ask the seller.» Gi selgeren beskjed om at pakken ikke har kommet, og be om navn og nummer til det lokale leveringsselskapet. Mer i guiden om [levert, men ikke mottatt](guide:delivered-not-received) (på engelsk).

**Mistet eller returnert.** Det er selgeren som bestilte frakten hos Xinghe Freight, så be selgeren eller markedsplassen om refusjon eller ny sending.

## Kontakt og kundeservice hos Xinghe Freight

På Xinghe Freights kinesiske nettsted (`xinghe-express.cn`) oppgis:

- **Døgnåpen linje (24/7):** `19924483103` (`+86 199 2448 3103` fra utlandet), oppgitt som åpen hele døgnet hver dag.
- **E-post:** `3975605292@qq.com` for bedriftshenvendelser og pristilbud.
- **Adresse:** Guangzhou, Guangdong-provinsen, uten gateadresse (besøkende bes ta kontakt på forhånd).

Nettstedet er bare på kinesisk og har ingen støtte for mottakere i utlandet: kontaktskjemaet åpner bare en e-post til adressen over. Xinghes kunde er selgeren, så spør selgeren først, og markedsplassen hvis selgeren ikke svarer.

## Spor en Xinghe Freight-pakke med Peek

[Peek](/) kjenner straks igjen et `GZXH…YQ`-nummer (og et avkortet `GZXH…Y`-nummer) som Xinghe Freight med «Detected carrier». Fikk du et nummer med en annen form av selgeren, lim det inn likevel: Peek følger mer enn 4 500 transportører. For å velge Xinghe Freight manuelt trykker du på «Change» og søker etter Xinghe Freight (eller `星和货运`), eller bruker «Change carrier» på en pakke du allerede har lagt til.

Peek spør GZYLs sporing direkte, akkurat som søkefeltet på Xinghes nettsted. Det viser status, hvert trinn med sted og mottakerland når det står i posten. Klokkeslett leses på Peking-tid og vises i din egen tidssone («Times are in your time zone:» nederst i historikken). Peek utelater navn, adresse, postnummer, vekt, betalingsdetaljer, signatur og leveringsbevis og viser ingen forventet leveringsdato siden sporingen ikke gir noen.

Når sporingen ikke kjenner nummeret ditt, spør Peek sporingstjenester og viser deres kopi. Så lenge ingen av dem har noe trinn, vises «Waiting for Xinghe Freight», og etter to dager «Check the tracking number».

Peek holder tilbake trinn skrevet mer enn 12 timer før klokkeslettet de viser, trinn skrevet i samme sekund som et trinn skrevet mer enn en halvtime før sin tid (bortsett fra ordreregistreringen) og trinn datert etter kontrolltidspunktet i Peking-tid. På en post med forhåndsskrevne trinn holdes også `Delivered` tilbake, selv om det ble skrevet samme dag. Peek går etter trinnene det beholder, ikke sammendragsstatusen, og sorterer også bort kopier av disse planlagte trinnene hos sporingstjenester (`Delivered` inkludert). Hvis sporingen ikke har noen post eller ikke svarer, kan Peek derimot ikke skille et planlagt trinn hos en ekstern tjeneste fra en ekte skanning. Når Peek holder tilbake spådommer, starter historikken frem til levering eller retur med «Other sites may show more steps, but they’re only predictions.», og «Show them» viser dem i grått med dagen fra sporingen («Predicted for Mon 12 Oct»). Slike trinn endrer ikke status og sender ingen varsler.

Bortsett fra Swiss Post følger ikke Peek pakken videre til lokal transportør: får du det lokale nummeret fra selgeren, legg det til som en egen pakke. Mens pakken er åpen, eller på leveringsdagen med varsler på, sjekker Peek opptil hvert 10. minutt; ellers og når pakken er stille, én gang i timen. Du kan velge varsler for «Every scan», «Important steps» (de to tolltrinnene og `Delivered`, ikke fly eller sortering) eller «Delivery only». Peek kan ikke kontakte Xinghe Freight, GZYL eller selgeren for deg.

## Spørsmål om sporing hos Xinghe Freight

### Hva er Xinghe Freight (星和货运)?

广州星和货运代理有限公司 (Guangzhou Xinghe Freight Forwarding) er en speditør i Guangzhou i Kina grunnlagt i 2014 som tilbyr sjø-, fly- og landfrakt, skipsagentur og tollklarering. Pakkenumrene har 16 tegn: `GZXH`, ti siffer og `YQ`.

### Hvorfor viser Xinghe Freight-pakken min sporing fra GZYL?

Søkefeltet på Xinghe Freights nettsted videresender søk direkte til GZYLs sporing, så ordlyden i hvert trinn er GZYLs egen. Peek beholder pakken under Xinghe Freight og leser den sporingen for deg.

### Hvem leverer Xinghe Freight-pakker i Norge?

Verken Xinghes nettsted eller sporingen oppgir lokal leveringspartner i Norge (Peek kan bare overføre automatisk til Swiss Post i Sveits og Liechtenstein når den er oppgitt). Spør selgeren hvilket lokalt selskap som leverer pakken din og be om sporingsnummeret.

### Hvorfor viser Peek færre trinn eller ingen sporing for GZXH-nummeret mitt?

Enten har sporingen ikke registrert nummeret ennå, eller så inneholder posten forhåndsskrevne trinn — inkludert et planlagt `Delivered` — som Peek skjuler bak «Show them» i stedet for å telle dem som skanninger. Sjekk nummeret mot selgerens e-post og ta kontakt med selgeren hvis ingenting skjer etter noen dager.

### Hvordan kontakter jeg Xinghe Freight?

Spør selgeren eller markedsplassen først, siden selgeren bestilte frakten. Det kinesiske nettstedet (`xinghe-express.cn`) oppgir døgnlinjen `19924483103` (`+86 199 2448 3103` fra utlandet) og bedriftse-posten `3975605292@qq.com`, uten egen kundestøtte for utenlandske mottakere.

:::sources
- [Xinghe Freight: 广州星和货运代理有限公司 (forside, på kinesisk)](http://xinghe-express.cn/) – selskapsnavn, spedisjonstjenester, søkefelt mot GZYLs sporing, telefon og e-post
- [Xinghe Freight: 关于我们 (om oss, på kinesisk)](http://xinghe-express.cn/about.html) – grunnlagt 2014, sjø-, fly- og landfrakt, skipsagentur og tollklarering
- [Xinghe Freight: 产品服务 (tjenester, på kinesisk)](http://xinghe-express.cn/services.html) – tjenestetilbud, ingenting om pakker til nettkunder
- [Xinghe Freight: 联系我们 (kontakt, på kinesisk)](http://xinghe-express.cn/contact.html) – 7×24-linje, bedriftse-post, adresse bare Guangzhou, skjema som åpner e-post
- [Europakommisjonen: EU Customs Reform (på engelsk)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 3 € fast toll per varetype opptil 150 € siden 1. juli 2026, 2 € EU-håndteringsgebyr siden 1. november 2026
- [Europakommisjonen: Guidance on the EUR 3 customs duty and the Union handling fee (PDF, på engelsk)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € gebyr uansett verdi og definisjon av varetype
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – navn, formatene `GZXH…YQ` og avkortet `GZXH…Y`, avvisning av feil lengde, GZYLs sporing, trinnformuleringer, mottak i Hongkong, kinesisk linje, Peking-tid, forhåndsskrevne trinn, sporingstjenester i reserve, overføring til Swiss Post og hva Peek viser, holder tilbake og utelater
:::
