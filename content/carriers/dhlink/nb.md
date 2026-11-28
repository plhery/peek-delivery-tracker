---
title: DHLink sporing: numre, statuser og hvem som leverer
description: Spor en DHLink-pakke fra en DHgate-bestilling: hvilke numre DHLink tar imot, hva de engelske skanningene betyr, hvem som leverer og hvem du kontakter.
slug: dhlink-sporing
published: 2026-11-28
updated: 2026-11-28
---

DHLink (驼飞侠) er den grenseoverskridende logistikkplattformen til DHgate (敦煌网). Selskapet ble grunnlagt i 2014, sender bestillinger for DHgate-selgere og andre kunder fra Kina eller fra sine lagre i utlandet, og overleverer dem til en transportør i ditt land for siste etappe. Spor nummeret på DHLinks [sporingsside](https://www.dhlink.com/trackQuery?lang=en) eller i feltet over. Hent nummeret fra DHgate-bestillingen din, eller be selgeren om det.

## DHLink-sporingsnummer: hvordan det ser ut

DHLink publiserer ikke noe fast nummerformat. Sporeren tar opptil 40 numre samtidig og godtar det den kaller Global Tracking No., Logistics Delivery No. og DHLink pickup number. Former som er sett på DHLink-pakker, med oppdiktede sifre:

| Nummer | Hva det er |
| --- | --- |
| `FC4829105736482` | FC og 13 sifre: DHLinks eget nummer, "DHLink No." På enkelte linjer er det også sporingsnummeret. |
| `XZG482910573WL` | XZG, 9 sifre, WL: en linjepartners nummer som DHLink finner som sporingsnummeret. |
| `YLUS4829105736` | YLUS og 10 sifre: en USA-linje som GOFO leverer. |
| `QH482910573648FS` | QH, 12 sifre, FS: en annen USA-linje som GOFO leverer. |
| `EYT4829105736HZ` | EYT, 10 sifre, HZ: en USA-linje som SwiftX leverer. |
| `CYS48291057364` | CYS og 11 sifre: en USA-linje som [UniUni](carrier:uniuni) leverer. |
| `JXCYI4829105736YQ` | JXC, YI, ZO, US eller ZI, 10 sifre, YQ: en form av [Juxi Express](carrier:juxi-express) sine fraktbrev som DHLinks sporer også svarer på for enkelte pakker på en USA-linje. Peek spør DHLink om JXC-numre; dersom selgeren sendte med Juxi, velger du Juxi Express med "Change". |
| `RR482917367CN` | To bokstaver, 9 sifre og CN: et [China Post](carrier:china-post)-nummer. Noen DHLink-pakker reiser med China Post under et slikt nummer. |
| `GFUS48291057364829` | Ikke DHLinks: et [GOFO Express](carrier:gofo)-nummer, siste etappe på enkelte USA-linjer. |

På et resultat viser DHLink "DHLink No." og "Tracking No.", og når en annen transportør leverer, et "Last Leg No." for den transportøren. Hvis sporingsnummeret skiller seg fra DHLink-nummeret, bruk sporingsnummeret: slår du bare opp DHLink-nummeret, vises kanskje ikke en slik pakke. Uten `lang=en` i adressen åpner siden på kinesisk.

## DHLink-sporingsstatuser forklart

Et resultat viser statusen (`In transit`, `Delivered`), landkoder for opprinnelse og destinasjon, linjens kode, en fremdriftslinje med `Original Leaving`, `Destination Arrived`, `Pending delivery` og `Delivered`, samt hver skanning med tidspunkt. En levert pakke viser også "Signed for - Signed for time". Radene kommer fra flere kilder, slik at samme skanning ofte dukker opp mer enn én gang. DHLinks engelske linjer, i den rekkefølgen en pakke møter dem:

| DHLink skriver | Hva det betyr for deg |
| --- | --- |
| `Data Received; Data transferred and received by carrier` | På en linje til Sørøst-Europa: Pactic, plattformen i Ungarn som den linjen går gjennom, har pakkens data. Før DHLinks lagerskanning viser Peek "Label created"; når raden kommer igjen etter flyturen, forblir pakken "In transit". |
| `Arrive at the collection warehouse` | DHLinks oppsamlingslager i Kina har pakken. |
| `The collection warehouse has completed sorting` | Sortert der. |
| `Package received & scanned at warehouse` | Registrert inn på DHLinks lager. |
| `Parcel handed over to carrier,waiting for transportation` | Innsamlingspunktet har den, og den venter på transport. |
| `Package departed from the warehouse`, `The package has sent from the warehouse` | Den har forlatt lageret. |
| `Warehouse scanning bagging`, `Master airway bill number received`, `Leave the warehouse` | På enkelte linjer: linjens lager i Kina har pakket forsendelsen i sekk for flyet, flyets hovedluftfraktbrev er mottatt, og pakken har forlatt lageret. Peek viser "In transit". |
| `Customs export clearance completed`, `Package handed over to airline` | Kinesisk eksporttoll har frigitt den, og et flyselskap overtok den deretter. |
| `Flight departure`, `The flight has taken off` | Fløyet ut av Kina. |
| `Shipment Departed From Original Country at …` | Det samme: den har forlatt Kina. |
| `Departed from airport of origin` | Flyet har lettet fra Kina ("In transit" i Peek). |
| `The flight has arrived and the board is being dismantled` | Landet; pallene brytes opp. |
| `Dismantling the board` | Det samme: pallen brytes opp på ankomstflyplassen ("In transit" i Peek). |
| `Shipment has arriveved at destination Country at …` | I ditt land. Stavingen er DHLinks egen. |
| `Arrived to … hub`, `Linehaul transit; Parcel has entered the linehaul network.` | Etter flyturen: en partners knutepunkt i Europa, angitt med en kode der prikkene står, og deretter linjetransport videre. På linjen til Sørøst-Europa er dette Pactics rader fra knutepunktet i Ungarn (kode HU). Peek viser "In transit". |
| `Customs clearance completed`, `Customs import clearance completed, awaiting pickup by local carrier` | Importtollen har frigitt den; transportøren i ditt land henter den neste gang. |
| `UPS/USPS/Fedex` | En overlevering til den amerikanske transportøren er på vei. |
| `货物已交UPS/USPS/Fedex,The goods have been delivered to UPS/USPS/Fedex` | Overlevert til den amerikanske transportøren. Ikke levert til deg ennå. |
| `Package picked up by last-mile carrier` | Transportøren i ditt land har overtatt den. |
| `Scheduled for Delivery` | Ute til levering. |
| `Shipment has delivered at …` | Levert. |

USA-linjer legger til knutepunktskanninger som `Arrived at Origin Hub` og `Departed Destination Hub`; på én er `Shipping Order Created.` linjens ordre, én time før lageret har pakken ("Label created" i Peek). En lokal transportørs anlegg kan skrive `Parcel received by ABC01.`, med sin egen kode der ABC01 står, etter å ha hentet pakken på DHLinks lager, og minutter senere `Parcel departed from Facility.`, begge "In transit". Etter overleveringen følger den leverende transportørens rader, som GOFOs `Arrived at GOFO Delivery Station` eller USPS' `No Access to Delivery Location` (et mislykket leveringsforsøk). UniUnis er `Parcel received at UniUni sorting hub.`, `Parcel processed.` (på det knutepunktet, minutter før leveringsrunden) og `Parcel scanned and in transit.`, noen ganger etter knutepunktets by og delstat, alle "In transit" i Peek. På China Post-linjer dukker også China Posts rader opp, noen bare på kinesisk: `中国邮政 已收取邮件` (China Post tok imot sendingen), `送交出口海关` (lagt frem for eksporttoll), `已妥投` etter en landkode (levert). For et ukjent nummer svarer siden `There is no track for this waybill number`.

## Hvem leverer DHLink-pakker

DHLinks nettsted er skrevet for selgere og navngir ingen lokal leveringspartner per land. Sporeren viser hvem som leverer. På USA-linjer sier én skanning at pakken gikk til "UPS/USPS/Fedex" uten å si hvilken; radene som følger er den transportørens, for eksempel [USPS](carrier:usps) sine. Enkelte USA-linjer ender hos [GOFO Express](carrier:gofo) eller SwiftX, hvis nummer vises som "Last Leg No." (GOFOs egen sporer finner også de DHgate-pakkene den leverer på DHLink-nummeret deres, FC og 13 sifre); i Sveits kan det nummeret være [DPD Switzerland](carrier:dpd-ch) sitt. Pakker som sendes med China Post beholder CN-nummeret sitt hele veien: se [China Post](carrier:china-post) for hvem som leverer dem i ditt land.

Når du kjenner transportøren og dens nummer, har dens egen sporing de lokale detaljene, og dens regler gjelder for leveringsforsøk, hentelapper og henting: se [mislykket levering](guide:missed-delivery) (på engelsk). Siden 1. november 2026 krever EU et håndteringsgebyr på 2 € per varetype på nettbestillinger fra land utenfor EU; selgeren eller leveringsselskapet betaler det og kan belaste deg for det i kassen eller før levering. For toll og andre avgifter på varer fra Kina, se [pakker holdt i tollen](guide:customs) (på engelsk).

## DHLink-pakke oppdateres ikke, borte eller levert men mangler

**Ikke funnet.** Sjekk nummeret på DHgate-bestillingen din, og prøv sporingsnummeret fremfor DHLink-nummeret. Et siste-etappe-nummer finner ingenting på DHLinks side: sjekk det hos den transportøren. Hvis du har valgt DHLink med "Change" for et nummer DHLink ikke kjenner, viser Peek "Waiting for DHLink" og fortsetter å sjekke. Hvis DHLink to dager senere fremdeles ikke kjenner nummeret og ingenting annet har svart på det, viser Peek "After two days, the carrier still doesn’t know this number. Check it, choose another carrier or delete the parcel."

**Sitter fast etter flyturen eller tollen.** Se etter et "Last Leg No." og sjekk det hos den transportøren, hvis skanninger kan være nyere enn DHLinks. Mer i [hvorfor sporing slutter å oppdatere](guide:tracking-not-updating) og [sporing av en pakke fra Kina](guide:tracking-from-china) (på engelsk).

**Levert men ikke fremme, tapt eller forsinket.** DHLinks regler for refusjon og erstatning ved problempakker er skrevet for selgere: selgeren krever erstatning fra DHLink, og du forholder deg til selgeren eller DHgate. De utelukker det kjøperen forårsaker: en forsinket levering fordi pakken ikke ble hentet eller signert for, eller mislykket fortolling fordi kjøperen ikke hjalp i tide. Hent den derfor, svar raskt på tollhenvendelser og si fra til selgeren tidlig: selgeren kan bare fremme krav innen 90 dager fra bestilling av forsendelsen. Se [levert men ikke mottatt](guide:delivered-not-received) og [tapte pakker](guide:lost-parcel) (på engelsk).

## DHLink kontakt og kundeservice

På DHLinks kontaktside, lest 4. november 2026, står én kundeserviceadresse, dhlinkservice@dhlink.com, samt et henvendelsesskjema på plattformen. Nettstedet har også en chatassistent, "AI 小飞". Kontaktsiden er på kinesisk og oppgir ikke noe telefonnummer. For bestillingen din bør du først kontakte selgeren via DHgate; for et leveringsproblem i ditt land kontakter du transportøren som leverer.

## Spor en DHLink-pakke med Peek

[Peek](/) kjenner igjen et `YLUS`-nummer med 10 sifre direkte som DHLink. `FC`-, `XZG`-, `QH`-, `EYT`- og `CYS`-numrene er bare svake treff ut fra formen alene: lim det inn likevel, Peek følger mer enn 4 500 transportører. Det viser "Checking tracking services…" og deretter "DHLink has this parcel." når DHLink kjenner nummeret; ellers velger du DHLink med "Change". Et `JXC`-nummer (`JXCYI`, `JXCZO`, `JXCUS` eller `JXCZI` med 10 sifre og `YQ`) deler form med Juxi Express: Peek spør DHLink om disse `JXC`-numrene, og hvis selgeren sendte med Juxi, velger du Juxi Express med "Change". Et China Post-nummer (to bokstaver, 9 sifre, CN) går til China Post.

Peek leser DHLinks sporer. Det viser statusen, historikken med stedet for hver skanning og destinasjonslandet, og viser hver skanning én gang. Skanninger i Kina leses etter Beijing-tid, USPS-skanninger etter tiden i den amerikanske delstaten og Pactics rader på en linje gjennom Ungarn etter Budapest-tid; andre beholder tiden DHLink oppgir. DHLink oppgir ingen leveringstid, vekt eller tjenestenavn, og Peek utelater postnumre og ordrereferanser. Dets eneste leveringsestimat er en videresendt USPS-skanning "Out for Delivery, Expected Delivery …" lest etter den amerikanske delstatens tid: mens den skanningen er nyest, viser Peek dagens tidsrom når skanningen gir et; en skanning med kun seneste tidspunkt legger ikke til noe estimat, siden "Out for delivery" allerede betyr i dag. Når "Last Leg No." er et GOFO-nummer (GFUS og 14 sifre), et SwiftX-nummer (SWX og 18 sifre) eller et DPD Switzerland-nummer (14 sifre som starter på 060 eller 061), viser Peek den transportøren og dens nummer, spør den og bytter over så snart den viser nyere fremgang. Andre siste-etappe-numre blir stående på DHLinks historikk.

Mens DHLinks sporer svarer, sjekker Peek opptil hvert 10. minutt med pakken på skjermen, eller med varsler på hvis levering forventes i dag. Fra kl. 06:00 på transportørens klokke den dagen pakken går ut til levering, kan sjekker kjøre hvert 2. minutt med pakken på skjermen eller varsler på. Hvis transportøren gir et leveringsvindu, starter de raskere sjekkene tidligst en halvtime før det åpner. Av skjermen og uten varsler er sjekker vanligvis hver time. Når bare en sporingstjeneste svarer, sjekker Peek hver time, hver 3. time når siste skanning er en dag gammel, og opptil hvert 15. minutt dagen den går ut til levering mens du har pakken åpen eller varsler på. Så snart Peek følger pakken hos transportøren som leverer den, sjekker Peek i den transportørens eget tempo. Det kan varsle deg ved hver skanning, ved viktige trinn eller bare på leveringsdagen. For sjekker hos selve transportøren bytter Peek til hver time etter to dager uten nytt (12 timer mens pakken fremdeles viser "Label created" eller "Received by carrier"). Peek kan ikke kontakte DHLink, DHgate eller selgeren for deg.

## Spørsmål om DHLink-sporing

### Hva er DHLink?

DHgates grenseoverskridende logistikkplattform, drevet av 驼飞侠科技（北京）有限公司 i Beijing. Den ble grunnlagt i 2014 og sender bestillinger fra Kina og fra utenlandske lagre via egne linjer og partnertransportører.

### Hvem leverer DHLink-pakken min?

En transportør i ditt land. På USA-linjer overleverer DHLink pakker til UPS, USPS eller FedEx, eller til GOFO, SwiftX eller UniUni; i Sveits noen ganger til DPD. Se etter "Last Leg No." på DHLinks resultat.

### Hva betyr "The goods have been delivered to UPS/USPS/Fedex"?

DHLink har overlevert pakken til den amerikanske transportøren. Den er ikke levert til deg ennå: den transportørens skanninger følger deretter.

:::sources
- [DHLink: About us (关于我们)](https://www.dhlink.com/about/introduce) – grunnlagt 2014, DHgates grenseoverskridende logistikkplattform, for DHgate og andre kunder
- [DHLink: Home page (首页)](https://www.dhlink.com/) – direkte sending fra Kina, utenlandske lagre, lenke til DHgates ordresporing
- [DHLink: Tracking (轨迹查询)](https://www.dhlink.com/trackQuery?lang=en) – 40 numre, godtatte numre, resultatfelter, DHLink No., Tracking No., Last Leg No., fremdriftslinje, "There is no track for this waybill number"
- [DHLink: Contact us (联系我们)](https://www.dhlink.com/about/connect) – kundeservice-e-post, henvendelsesskjema, chat
- [DHLink: Service agreement (服务协议)](https://www.dhlink.com/agreementPage/service) – operatør 驼飞侠科技（北京）有限公司
- [DHgate Seller Help: DHLink物流异常件赔付规则 (DHLink erstatningsregler)](https://seller.dhgate.com/help/c7502/516002.html) – erstatning til selgere, logistikktyper, unntak for kjøperforårsaket forsinkelse og mislykket fortolling, krav innen 90 dager fra logistikkordren
- [Revenue: Union Handling Fee press release](https://www.revenue.ie/en/corporate/press-office/press-releases/2026/pr-092926-handling-fee.aspx) – 2 € per varetype fra 1. november 2026, for varer kjøpt utenfor EU, betalt av selgeren eller leveringsselskapet, som kan belaste det i kassen eller før levering
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – nummerformer (YLUS gjenkjent direkte; CYS på en UniUni USA-linje; JXC delt med Juxi Express), skanningstekster (Pactics data-, knutepunkt- og linjetransportrader; en linjes pakking, luftfraktbrev og flyrader; UniUnis knutepunkt- og transitskanninger; en USA-linjes ordre; et lokalt anleggs mottak og avgang), siste-etappe-partnere, GOFOs sporer som tar DHLinks FC-numre, hva Peek leser og viser
:::
