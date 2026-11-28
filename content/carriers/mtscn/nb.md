---
title: Meiteshun-sporing (MTSCN): AMTS…FS-nummer og statuser
description: Spor en pakke fra Meiteshun Logistics (MTSCN / 美特顺) fra Shenzhen: AMTS…FS-format, hvorfor Delivered skrives på forhånd, lokal levering og kontakt.
slug: meiteshun-sporing
published: 2026-11-27
updated: 2026-11-27
---

Meiteshun Logistics (MTSCN, 深圳市美特顺物流有限公司) er en grensekryssende speditør i Shenzhen i Kina med fraktbrev som består av `AMTS`, ni siffer og `FS`. Lim inn `AMTS…FS`-nummeret ditt i feltet over for å spore bestillingen og filtrere bort forhåndsskrevne trinn: Meiteshun sin sporing legger inn hele pakkens tidslinje — inkludert `Delivered` — allerede når ordren opprettes, så en `Delivered`-linje på siden deres beviser ikke alene at pakken har kommet frem.

## Sporingsnummer hos Meiteshun: slik ser det ut

Meiteshun sine fraktbrev består av `AMTS`, ni siffer og `FS`, totalt 15 tegn. Eksempler med oppdiktede siffer:

| Nummer | Hva det er |
| --- | --- |
| `AMTS482910517FS` | `AMTS`, ni siffer og `FS`: et standard Meiteshun-fraktbrev. |
| `AMTS48291051FS` | Bare åtte siffer: ikke Meiteshuns format. Sjekk om det mangler et siffer. |
| `RR482910576CN` | To bokstaver, ni siffer og `CN`: et [China Post](carrier:china-post)-nummer, ikke Meiteshun. |

Små bokstaver og mellomrom spiller ingen rolle når du limer nummeret inn i Peek.

**Hvor du finner nummeret.** Selgeren bestiller frakten hos Meiteshun og sender deg sporingsnummeret i forsendelsesvarslingen eller på bestillingssiden. Finner du det ikke, kontakt selgeren.

**Selgers referanse.** Meiteshun sin sporing finner også en ordre på selgerens eget referansenummer hvis det skrives nøyaktig slik det ble oppgitt, med bindestreker, og viser da `AMTS`-nummeret.

**En lenke med tall i stedet for domenenavn.** Meiteshun sin sporing kjører på en ren serveradresse over ukryptert HTTP uten domenenavn. En lenke dit har en talladresse i stedet for et nettstednavn og inneholder `trackIndex.htm`, `documentCode=` og nummeret eller referansen din: du kan lime lenken rett inn i Peek.

## Hva statusene hos Meiteshun betyr

Hver Meiteshun-ordre følger det samme manuskriptet, og de fleste trinnene skrives inn på forhånd (se under). Fordi Peek-appen ikke har norsk grensesnitt, vises Peeks egne trinn på engelsk: «Label created» (etikett opprettet), «Received by carrier» (mottatt av transportør), «In transit» (underveis), «At customs» (i tollen) og «Delivered» (levert). Formuleringene i rekkefølgen manuskriptet kjører:

| Meiteshun skriver | Hva det betyr for deg | Peek viser |
| --- | --- | --- |
| `The package has been collected and is ready to be sent to the distribution center` | Første trinn: Meiteshuns innhenting i Shenzhen. | «Received by carrier» |
| `item inbound in sorting center` | Ved et sorteringssenter, med stedet `ShenZhen`. | «In transit» |
| `item outbound in sorting center` | Forlater det sorteringssenteret. | «In transit» |
| `Arriving at the terminal` | Ved en flyplassterminal, med stedet `Hong Kong`. | «In transit» |
| `Leaving the terminal, the airline will take off` | Flyet forlater Hongkong. | «In transit» |
| `Arriving at the destination country` | Landet i mottakerlandet, oppgitt med tobokstavers landkode. | «In transit» |
| `Customs clearanceisin progress` | Importfortolling i landet ditt (de manglende mellomrommene er Meiteshuns egne). | «At customs» |
| `Customs clearance completed` | Ferdig fortollet og på vei videre. | «In transit» |
| `Arriving at the mail processing center` | Ved et behandlingssenter i landet ditt. | «In transit» |
| `Arrive at the sorting and distribution center` | Ved et sorteringssenter i nærheten av deg, med by eller region. | «In transit» |
| `package is on the way` | Siste trinn før levering. Det betyr ikke at pakken er ute til levering med bud. | «In transit» |
| `Delivered` | Siste trinn i manuskriptet (se under). | «Delivered», med mindre Peek holder trinnet tilbake (se under) |

For en ordre uten trinn ennå skriver plattformen automatisk `货物电子信息已经收到` (elektronisk vareinformasjon mottatt). Selgeren har opprettet ordren, men ingen har skannet pakken; Peek viser linjen på kinesisk under «Label created».

Stedet for et trinn står foran et komma i starten av teksten, og trinn i mottakerlandet legger til by/region og postnummer etter teksten. Hvert klokkeslett står i Peking-tid (`UTC+8`), også for trinn i Norge: kl. 21.00 på Meiteshun sin sporing tilsvarer kl. 14.00 norsk vintertid (`UTC+1`) og kl. 15.00 sommertid (`UTC+2`).

**Trinn skrevet på forhånd.** Meiteshun kjører sporingen sin på Hualei (华磊), en speditørplattform som også andre kinesiske speditører som [Pengyou](carrier:pengyou) bruker. Hos Meiteshun blir alle trinnene for en ordre skrevet inn idet ordren opprettes, hvert med sin planlagte tid fra innhenting til `Delivered`. Dette er en prognose, ikke faktiske skanninger av pakken din: de viser bare hvor pakken var ment å være på en bestemt dato. Sporingstjenester som kopierer data fra Meiteshun viser de samme trinnene.

## Hvem leverer Meiteshun-pakker i Norge

Meiteshuns trinn i mottakerlandet oppgir aldri navnet på det lokale leveringsselskapet. Tjenestenavnet i datapostene er `MTS全球小包` (MTS global small parcel). På Meiteshun sin sporingsside kan et nummer under `渠道转单号` (overføringsnummer), eller under `跟踪号码` (sporingsnummer) når det ikke er ditt `AMTS`-nummer, være det lokale leveringsselskapets eget nummer: legg det inn i Peek som en egen pakke.

Peek fant ingen leveringstider fra Meiteshun og ingen regler for leveringsforsøk, hentesteder eller hentefrister. Spør selgeren hvilket lokalt selskap som leverer ordren din og be om det lokale sporingsnummeret — det er det selskapets sporing og regler som gjelder. Pakker fra Kina til Norge følger norske import- og mva.-regler (mens nettbestillinger til EU-land siden 1. november 2026 også får EUs håndteringsgebyr på 2 € per varetype uansett verdi); les mer i guiden om [pakker i tollen](guide:customs) (på engelsk).

## Meiteshun-pakke som ikke kommer frem eller sporing som står stille

**«Delivered», men ingenting kom.** Trinnet kan være manuskriptets prognose, ikke en reell skanning. Si fra til selgeren at pakken ikke har kommet, og be om sporingsnummeret til den lokale transportøren. Mer i guiden om [levert, men ikke mottatt](guide:delivered-not-received) (på engelsk).

**Alle trinn i rute, men ingen pakke.** Trinnene ble skrevet da ordren ble opprettet, så de viser planen og ikke hvor pakken faktisk er. Følg butikkens estimerte leveringstid og kontakt selgeren når den har passert.

**Står fast i Shenzhen i Peek.** Hvis Peek bare viser de første trinnene i Shenzhen mens en sporingslenke fra selgeren viser flere, ble de manglende trinnene skrevet på forhånd eller er datert etter Peeks kontroll (se under). Et forhåndsskrevet trinn teller aldri som skanning: det endrer verken status eller forventet dato og sender ingen varsler. Frem til pakken er levert eller returnert åpner historikken i Peek med «Other sites may show more steps, but they’re only predictions.», og «Show them» viser de tilbakeholdte trinnene i grått med sin dag («Predicted for Mon 12 Oct»). Se etter et lokalt sporingsnummer på Meiteshuns side eller spør selgeren. Mer i guiden om [pakker fra Kina](guide:tracking-from-china) (på engelsk).

**Ingenting funnet.** For et ukjent nummer viser Meiteshuns sporing nummeret som referanse med alle andre felt tomme. Peek tolker det som ikke funnet: først «Waiting for Meiteshun Logistics», og to dager senere «Check the tracking number». Sjekk nummeret med selgeren.

**Mistet, skadet eller returnert.** Det er selgeren som bestilte pakken hos Meiteshun, så be selgeren eller markedsplassen om refusjon eller ny sending.

## Kontakt og kundeservice hos Meiteshun

For alt som gjelder bestillingen din bør du spørre selgeren først, siden selgeren bestilte frakten hos Meiteshun. Svarer ikke selgeren, kontakt markedsplassen. Hvis ingen av dem hjelper, oppgir 17TRACKs side om Meiteshun e-postadressen szmeiteshun@163.com, nettstedet `mtswl.com.cn` og et kontor i Longgang-distriktet i Shenzhen, men intet telefonnummer: oppgi `AMTS`-nummeret ditt og navnet på selgeren hvis du skriver dit.

## Spor en Meiteshun-pakke med Peek

[Peek](/) gjenkjenner et Meiteshun-nummer direkte på formen: `AMTS`, ni siffer og `FS`, som ingen annen transportør i katalogen bruker. En lenke til Meiteshuns sporing med `documentCode=` og nummeret eller referansen fungerer også. En selgerreferanse har ingen unik form, men du kan lime den inn likevel — Peek følger over 4 500 transportører — og velge Meiteshun Logistics via «Change» (søk på `美特顺` eller `MTSCN`) eller «Change carrier» på en allerede lagt til pakke; da viser Peek også ordrens `AMTS`-nummer. Merk at Peek fjerner bindestreker, så referanser med bindestrek må søkes opp direkte på Meiteshuns sporing.

Peek leser Meiteshuns egen sporing og viser status, hver skanning med sted og mottakerland. Tekstene vises slik Meiteshun skriver dem: `Customs clearanceisin progress` teller som toll («At customs») og `package is on the way` som «In transit», ikke ute til levering. Peek beholder byen fra trinn i landet ditt og utelater postnummeret. Tider leses på Peking-tid og vises i din egen tidssone. Peek utelater navn, adresse, vekt, gebyrer, signatur og leveringsbevis og viser ingen forventet leveringsdato siden Meiteshun ikke oppgir noen.

Peek holder tilbake trinn skrevet mer enn tolv timer før sitt oppgitte klokkeslett, samt alle trinn datert etter kontrolltidspunktet i Peking-tid. Det avgjør statusen etter de beholdte trinnene, ikke etter postens sammendragsstatus. Når hele ordrens manuskript ble skrevet ved opprettelsen, beholder Peek bare trinn datert før det tidspunktet eller inntil seks timer etter, slik at `Delivered` som ble skrevet da holdes tilbake. Når Peek må lese `trackIndex.htm`-siden fordi oppslaget mangler trinn, holder det tilbake alle `Delivered` der siden den siden ikke viser når trinnene ble skrevet. Hvis alle trinn holdes tilbake, viser Peek «Waiting for Meiteshun Logistics» uten å be deg sjekke nummeret. Enten Peek holder tilbake noen eller alle trinn, fjerner det også sporingstjenestenes kopier av dem.

Peek følger pakken på Meiteshuns sporing uten automatisk overføring til lokal transportør, siden Meiteshun aldri navngir den lokale transportøren: legg inn det lokale nummeret fra selgeren som en egen pakke. Mens pakken er åpen, eller på leveringsdagen med varsler på, sjekker Peek opptil hvert 10. minutt, og ellers (samt etter to dager uten nytt eller 12 timer på «Label created» / «Received by carrier») én gang i timen. Varsler kan settes til «Every scan», «Important steps» eller «Delivery only». Peek kan ikke kontakte Meiteshun eller selgeren for deg.

## Spørsmål om sporing hos Meiteshun

### Hva er Meiteshun Logistics (MTSCN / 美特顺)?

Det er 深圳市美特顺物流有限公司 (Shenzhen Meiteshun Logistics), en speditør i Shenzhen i Kina. Fraktbrevnumrene har formatet `AMTS`, ni siffer og `FS`, og i datapostene står tjenestenavnet `MTS全球小包`.

### Kommer et AMTS…FS-nummer fra China Post?

Nei. `AMTS`, ni siffer og `FS` er Meiteshuns format (åtte siffer betyr et manglende siffer). China Posts internasjonale numre består av to bokstaver, ni siffer og `CN`.

### Hvorfor viser Meiteshun «Delivered» når pakken ikke har kommet?

Meiteshuns Hualei-sporing skriver hele den planlagte tidslinjen — inkludert levering — allerede når ordren opprettes. Peek holder tilbake slike forhåndsskrevne trinn slik at de ikke gir falske varsler; gi beskjed til selgeren om at pakken ikke kom, og be om det lokale sporingsnummeret.

### Hvem leverer Meiteshun-pakker (MTSCN) i Norge?

Meiteshuns trinn oppgir aldri den lokale transportøren. Se etter et ekstra nummer under `渠道转单号` (overføringsnummer) eller `跟踪号码` på Meiteshuns sporingsside, eller spør selgeren hvilket selskap som leverer i Norge og legg det nummeret inn i Peek.

### Hvordan kontakter jeg Meiteshun Logistics om en manglende pakke?

Begynn med selgeren eller markedsplassen, siden selgeren bestilte frakten. Får du ikke svar der, oppgir 17TRACK e-posten szmeiteshun@163.com og nettstedet `mtswl.com.cn` (kontor i Longgang i Shenzhen, intet telefonnummer); legg ved `AMTS`-nummeret og selgerens navn.

:::sources
- [17TRACK: MTSCN tracking (på engelsk)](https://www.17track.net/en/carriers/mtscn) – e-postadresse, nettsted og Shenzhen-kontor for Meiteshun, intet telefonnummer
- [Europakommisjonen: EU customs reform (på engelsk)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – EUs håndteringsgebyr på 2 € fra 1. november 2026
- [Europakommisjonen: Guidance on the EUR 3 customs duty and Union handling fee (PDF, på engelsk)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € per varetype uansett verdi
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – selskapsnavn, Hualei-plattform, `AMTS`-format, sporing og lenker, søk på referanse, manuskript og tekstlinjer, steder og Peking-tid, forhåndsskrevne trinn, svar ved ukjent nummer, kinesisk linje, tjenestenavn, kolonnene for overføringsnummer og hva Peek viser, holder tilbake og utelater
:::
