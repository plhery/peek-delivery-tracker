---
title: TopYou sporing: TYZR-numre, statusser og levering
description: Spor en TopYou-pakke (通邮 / SZTY56) fra Kina: TYZR…YQ-numre, betydning af statusser, lokale transportører (USPS, GOFO, iMile, DPD) og kontakt i Shenzhen.
slug: topyou-spor-pakke
published: 2026-11-28
updated: 2026-11-28
---

TopYou (通邮, Tongyou, 通邮集团 eller SZTY56 — 深圳市通邮物流科技（集团）股份有限公司, der oplyser at være grundlagt i Shenzhen i 2016) er en grænseoverskridende speditør med internationale linjer fra Kina. Indsæt dit 17-tegns `TYZR…YQ`-fragtbrevsnummer i feltet ovenfor (eller med store bogstaver på track.szty56.com) for at følge pakken fra Shenzhen gennem eksport, flyvning, told og overlevering til den lokale transportør — fx USPS i USA, GOFO i Frankrig og Italien, iMile i Tyskland eller DPD i Østrig.

## TopYou sporingsnummer: sådan ser det ud

TopYous fragtbrevsnumre består af `TYZR`, ét bogstav, ti cifre og `YQ` — i alt 17 tegn; enkelte har et andet bogstav i stedet for `R`. Med opdigtede cifre:

| Nummer | Hvad det er |
| --- | --- |
| `TYZRA4829105736YQ` | `TYZR`, et bogstav, ti cifre og `YQ`: et TopYou-fragtbrev. |
| `TYZRA482910573YQ` | Kun ni cifre: ikke TopYous format. Tjek om der mangler et ciffer. |
| `JXCAB4829105736YQ` | Slutter også på `YQ`, som andre kinesiske speditørers numre gør, men begynder med `JXC`: et nummer fra [Juxi Express](carrier:juxi-express). |

Mellemrum og små bogstaver betyder ikke noget, når du indsætter nummeret i Peek. TopYous egen sporing på [track.szty56.com](https://track.szty56.com/) skelner mellem store og små bogstaver, så skriv nummeret med store bogstaver der. Kan du ikke finde nummeret i din ordre, så se [how to find your tracking number](guide:find-tracking-number) (på engelsk).

**Et andet nummer.** TopYous registrering kan også indeholde den leverende transportørs eget nummer, som TopYous sporing viser.

**Kopiér nummeret, ikke et link.** Peek læser ikke links fra TopYous sporing: indsæt selve nummeret.

## TopYou sporingsstatusser forklaret

TopYou skriver sine scanninger på engelsk. I den rækkefølge en pakke normalt får dem:

| TopYou skriver | Hvad det betyder for dig | Peek viser |
| --- | --- | --- |
| `Parcel Data Received` | Butikken har sendt pakkens oplysninger til TopYou, men TopYou har ikke pakken endnu. | "Label created" |
| `Arrived at the first mile sorting center` | TopYous lager i Kina har pakken: første scanning i TopYous hænder. | "Received by carrier" |
| `Departed from the first mile sorting center` | Den har forladt det lager. | "In transit" |
| `Arrived at airport of the original country` | I afgangslufthavnen før flyvningen. | "In transit" |
| `Departed from Airport to destination country` | Flyet mod dit land er lettet. | "In transit" |
| `Arrived at destination airport` | Landet i modtagerlandet. | "In transit" |
| `Departed from customs of` | Modtagerlandets told har frigivet den. TopYou lader landet efter "of" stå tomt, og Peek fjerner "of". | "In transit" |
| `At U.S. Postal Service facility, Accepted by U.S. Postal Service` | I USA: USPS har overtaget pakken fra TopYou efter flyvningen. Det er ikke en ny forsendelse. | "In transit" |

**Nyere fragtbreve.** TopYou formulerer nogle af de samme trin anderledes på nyere fragtbreve:

| TopYou skriver | Hvad det betyder for dig | Peek viser |
| --- | --- | --- |
| `Carrier received the shipment` | TopYou har pakken i Kina: dens første scanning. | "Received by carrier" |
| `Shipment arrived at a carrier facility` | På vej videre fra TopYous første lager. Pakken indleveres ikke igen. | "In transit" |
| `Shipment started the customs clearance process at origin` | Kinas eksportfortoldning, et rutinetrin før flyvningen. Tolden tilbageholder den ikke, og det er ikke dit lands importtold. | "In transit" |
| `Shipment arrived in the destination country` | Pakken er nået frem til modtagerlandet. | "In transit" |
| `Shipment started the customs clearance process at destination` | Modtagerlandets importtold har pakken. | "At customs" |
| `Shipment completed the customs clearance process at destination` | Fortoldet og på vej til den leverende transportør. | "In transit" |
| `Shipment is out for delivery` | Ikke ude til levering endnu, uanset ordlyden: i Frankrig og Italien markerer linjen overdragelsen til GOFO på dets første sorteringscenter, dage før chaufføren kører ud. | "In transit" |

**Linjer leveret af postvæsenet.** På linjer, hvor landets postvæsen leverer, formulerer TopYou scanningerne på en tredje måde frem til importtolden:

| TopYou skriver | Hvad det betyder for dig | Peek viser |
| --- | --- | --- |
| `Accepted by carrier` | TopYou har pakken i Kina: første scanning. | "Received by carrier" |
| `Inbound in sorting center` | Registreret på TopYous første lager få minutter senere. Den er ikke på vej endnu. | "Received by carrier" |
| `Outbound in sorting center` | Har forladt det lager. TopYou tilføjer `, Lastmile Number:` og det nummer, posten leverer under; Peek fjerner den slutning fra linjen. | "In transit" |
| `Received by line-haul`, `Hand over to airline` | Overtaget til hovedtransport og derefter overdraget til flyselskabet. | "In transit" |
| `Airline arrived at transit country or district.`, `Airline departed from transit or district country.` | Mellemlanding på vej mod dit land. | "In transit" |
| `Airline arrived at destination country`, `Arrive at destination country` | Er nået frem til modtagerlandet. | "In transit" |
| `Export clearance success` | Kinas eksporttold har godkendt den. Den er ikke tilbageholdt. | "In transit" |
| `Import clearance success` | Dit lands told har frigivet den til posten. Posten kan stadig vise egne toldscanninger. | "In transit" |
| `Customs Clearance Completed Successfully` | Dit lands told har frigivet den, få minutter efter overdragelsen til den lokale transportør, der nu har pakken. | "In transit" |

**Den lokale transportørs scanninger.** Efter overdragelsen kan TopYous sporing fortsætte med den lokale transportørs egne scanninger: USPS i USA, eller GOFO i Frankrig og Italien som `Arrived at sorting center`, `The package left sorting center` og `Package departed from sorting center` (på eller ud fra et GOFO-sorteringscenter: "In transit"), `Arrived at GOFO Regional Destination Facility` (på GOFOs regionale depot: "In transit") og `The driver is out for delivery` (GOFOs chauffør er på vej med den: "Out for delivery").

I Tyskland kommer iMiles scanninger med iMiles navn for trinnet, et komma og iMiles sætning: `In Transit, Your order has been shipped from 【iMile Köln DC Center】.` (sendt videre fra et iMile-center: "In transit"), `Delivery, Your order has arrived at 【Dortmund01】` (på den iMile-station, der leverer den: "In transit"), `Delivery, Your order has assigned delivery drvier.` (en chauffør er tildelt, men ikke kørt ud endnu; stavefejlen er iMiles: "In transit") og `Delivery, Our delivery associate is out for delivery.` (iMiles chauffør er ude med den: "Out for delivery"). En linje, der starter med `Delivery`, betyder altså ikke, at chaufføren er kørt ud. En iMile-linje uden bevægelse, fx en lageroptælling, ændrer ikke status. En besked fra et afhentningssted, der starter `Arrived, The parcel has been delivered to 【】. Please collect your parcel promptly at 【】 using your pickup code.`, betyder, at pakken venter der til afhentning med din afhentningskode: "Ready for pickup".

I Østrig kommer DPDs scanninger på DPDs engelsk: `In parcel delivery center` (på DPDs leveringsdepot: "In transit"), `In delivery` (DPDs chauffør leverer den: "Out for delivery"), `Unfortunately, consignee not present. Notification was left.` (ingen hjemme, og DPD efterlod en besked: "Delivery attempted"), `Parcel was handed over to Pickup parcel shop` og `Takeover by Pickup parcel shop` (venter på dig i en DPD Pickup parcelshop: "Ready for pickup") samt `Parcel was not picked up` (ikke afhentet i tide, så DPD sender den retur til afsender: "Needs attention").

I Spanien kan den leverende partners scanning efter told lyde `Parcel in transit` (på vej til dig: "In transit").

## Hvem leverer TopYou-pakker

**USA.** USPS overtager pakken fra TopYous linje efter flyvningen, og TopYous sporing kan fortsætte med dens scanninger. Det andet nummer i TopYous registrering er da det, som [USPS](carrier:usps) sporer den med.

**Frankrig og Italien.** GOFO kan levere: [GOFO France](carrier:gofo-fr) eller [GOFO Italy](carrier:gofo-it). TopYous sporing viser overdragelsen som `Shipment is out for delivery` efterfulgt af GOFOs scanninger.

**Tyskland.** [iMile](carrier:imile) kan levere, hvorefter TopYous sporing fortsætter med iMiles scanninger.

**Østrig.** [DPD Austria](carrier:dpd-at) kan levere, hvorefter TopYous sporing fortsætter med DPDs scanninger.

**Danmark og øvrige lande.** TopYous registrering kan vise den lokale transportørs nummer, men ikke dens navn, og navnet på den TopYou-linje, pakken rejser med, afslører det heller ikke: en linje kan bære et andet fragtselskabs navn. På en postlinje afslutter postens nummer også linjen `Outbound in sorting center` efter `Lastmile Number:`. Spørg derfor sælgeren, hvilket firma der leverer din ordre.

## TopYou-pakke ankommer ikke eller sporing står stille

**Fast på `Shipment started the customs clearance process at destination`.** Dit lands told har pakken. Bliver du bedt om importafgifter — som i Danmark og resten af EU siden 1. november 2026 også omfatter et EU-håndteringsgebyr på 2 € pr. vareart uanset varens værdi — se [parcels held at customs](guide:customs) (på engelsk); bliver den liggende i dagevis, så kontakt sælgeren. Mere i [why tracking stops updating](guide:tracking-not-updating) (på engelsk).

**Intet efter `Departed from customs`, `Shipment completed the customs clearance process at destination`, `Customs Clearance Completed Successfully` eller `Import clearance success`.** Den lokale transportør bør have pakken. Se efter dens nummer i TopYous sporing, eller spørg sælgeren, hvem der leverer og under hvilket nummer. Baggrund i [tracking a parcel from China](guide:tracking-from-china) (på engelsk).

**Ikke fundet eller ingen scanninger endnu.** TopYous sporing svarer `查询不到` (ikke fundet) ved et ukendt nummer og kan svare `暂无轨迹信息` (ingen sporingsoplysninger endnu) før første scanning. Peek viser så "Waiting for TopYou" og fortsætter med at tjekke; kender TopYou stadig ikke nummeret to dage senere, og intet andet har svaret for det, viser Peek "After two days, the carrier still doesn’t know this number. Check it, choose another carrier or delete the parcel." Peek kan ud fra TopYous svar se, når TopYou kender forsendelsen, men ikke har scannet den endnu: så fortsætter Peek med at tjekke og viser "Waiting for TopYou" uden at bede dig tjekke nummeret. Har TopYou kun vist `Parcel Data Received` i mindst et døgn, viser Peek "The sender hasn’t handed it to TopYou yet. Tracking starts when they do." Spørg butikken, hvornår den sender den. Tjek nummeret tegn for tegn mod sælgerens besked; hvis intet dukker op efter et par dage, så spørg sælgeren, om pakken faktisk er sendt.

**Leveret, men intet modtaget.** Ved en USA-pakke leveret af USPS: spørg dit postkontor og kontakt derefter sælgeren. Mere i [delivered but not received](guide:delivered-not-received) (på engelsk).

**Bortkommet eller returneret.** Sælgeren bookede pakken hos TopYou, så det er sælgeren eller markedspladsen, du skal bede om refusion eller en ny pakke. Se [lost parcels](guide:lost-parcel) og [returned to sender](guide:returned-to-sender) (på engelsk).

## TopYou kontaktnummer og kundeservice

Pr. 16. november 2026 oplyser TopYous hjemmeside, [szty56.com](https://www.szty56.com/), et telefonnummer i Shenzhen, +86 755 8181 9898 (0755-81819898 i Kina), og åbningstiden 9:00 til 18:00 på hverdage uden angivet tidszone. Den nævner ingen kundeservice for modtagere: kunderne er de butikker, der sender med TopYou. Sælgeren bookede forsendelsen og er den rette at kontakte om din ordre — og markedspladsen, hvis sælgeren ikke svarer.

## Spor en TopYou-pakke med Peek

[Peek](/) genkender et TopYou-nummer på formen: `TYZR`, et bogstav, ti cifre og `YQ`, som ingen anden transportør i kataloget bruger. Med et andet bogstav i stedet for `R` er formen kun et svagt match: Peek viser "Checking tracking services…" og spørger TopYous sporing, om den kender nummeret. Peek spørger kun TopYous sporing om numre med disse to former. Har du kun den lokale transportørs nummer, så indsæt det i stedet: Peek følger over 4.500 transportører.

Peek læser TopYous egen sporing og viser status, hver scanning med TopYous eller den videresendte transportørs ordlyd, destinationslandet og vægten fra TopYous registrering. Et sted, der blot er et land, udelades fra TopYous egne scanninger, så lufthavnsscanningen i Kina ikke ser ud som om, den skete i dit land. Når TopYous registrering markerer pakken som leveret eller returneret, viser Peek "Delivered" eller "Returned to sender", selv om teksten i den sidste scanning ikke gør det. TopYous egne scanninger står i Beijing-tid. For den lokale transportørs videresendte scanninger angiver TopYous sporing ikke altid den rigtige tidszone, så Peek beregner deres tid og viser alt i din enheds tidszone. Der vises ingen forventet leveringsdato, fordi TopYou ikke opgiver nogen.

Når TopYous registrering indeholder den lokale transportørs nummer, prøver Peek det hos dit lands postvæsen, hvis det er et gyldigt internationalt postnummer (to bogstaver, ni cifre med gyldigt kontrolciffer, to bogstaver), eller ellers hos den ene transportør, formen med sikkerhed tilhører, så længe Peek læser den transportørs egen sporing. Så snart den transportør viser en scanning mindst lige så ny som TopYous seneste, følger Peek pakken der og viser begge numre, mens TopYous scanninger bevares i historikken. Et nummer, Peek ikke kan knytte entydigt til én transportør — som 20 cifre alene, der bruges af USPS og andre — prøves ikke: Peek bliver hos TopYous sporing, som også kan vise den lokale transportørs scanninger.

Peek tjekker op til hvert 10. minut, mens du har pakken åben, eller på leveringsdagen med notifikationer slået til (ellers hver time), og hver time når TopYou har været stille: efter 12 timer uden ny scanning, mens pakken stadig står på "Label created" eller "Received by carrier", eller efter to dage i ethvert andet trin. Når pakken er ude til levering, tjekker Peek op til hvert 2. minut (hvert 5., når tjekket åbner en browser): den pågældende dag fra kl. 06:00 lokal tid, så længe TopYous egen sporing svarer. Via sporingstjenester tjekker Peek hver time, hver 3. time efter en stille dag og op til hvert 15. minut på leveringsdagen, mens du har pakken åben eller har notifikationer slået til. Når en anden transportør overtager leveringen, følger Peek den leverende transportørs tempo. Peek kan give besked ved hver scanning, kun vigtige trin eller "Delivery only". "Important steps" (levering, afhentning, told, mislykkede forsøg og returneringer) advarer om modtagerlandets told (`Shipment started the customs clearance process at destination`), GOFOs `The driver is out for delivery`, iMiles `Delivery, Our delivery associate is out for delivery.` og selve leveringen — ikke eksportfortoldningen i Kina, flyvningen, toldfrigivelsen eller `Shipment is out for delivery`. Peek kan ikke kontakte TopYou eller sælgeren for dig.

## Spørgsmål om TopYou sporing

### Hvad er TopYou (通邮 / SZTY56)?

Det er 深圳市通邮物流科技（集团）股份有限公司, også kaldet 通邮集团, Tongyou eller SZTY56, en grænseoverskridende speditør i Shenzhen, Kina. Selskabet oplyser, at det blev grundlagt i 2016, har afdelinger og sorteringscentre i Shenzhen, Guangzhou, Shanghai og andre kinesiske byer samt håndterer over 800.000 ordrer om dagen.

### Hvordan ser et TopYou sporingsnummer ud, og hvorfor findes det ikke?

TopYou-fragtbreve har 17 tegn: `TYZR` (eller `TYZ` og et andet bogstav), ét bogstav, ti cifre og `YQ`. TopYous sporing på track.szty56.com skelner mellem store og små bogstaver og svarer `查询不到` ved ukendt nummer eller `暂无轨迹信息` før første scanning.

### Hvorfor viser TopYou mit land, før pakken forlader Kina?

Fordi TopYous sporing skriver destinationslandet ud for sine egne scanninger, selv i afgangslufthavnen. Gå efter teksten: `Arrived at airport of the original country` betyder, at pakken stadig venter på sit fly.

### Hvem leverer TopYou-pakker i USA, Europa og andre lande?

I USA overtager USPS efter flyvningen. I Frankrig og Italien kan det være GOFO, i Tyskland iMile og i Østrig DPD. Andre steder kan TopYous registrering vise et andet sporingsnummer uden at nævne transportøren: spørg sælgeren, hvilket firma der leverer.

### Hvordan kontakter jeg TopYou ved en forsinket eller bortkommet pakke?

Spørg sælgeren eller markedspladsen først, da butikken bookede forsendelsen hos TopYou. TopYous hjemmeside szty56.com oplyser et telefonnummer i Shenzhen (+86 755 8181 9898, hverdage 9:00–18:00), men ingen kundeservice for modtagere.

:::sources
- [TopYou: 通邮集团 (hjemmeside)](https://www.szty56.com/) – telefon, åbningstider
- [TopYou: 关于我们 (om os)](https://www.szty56.com/view?path=about/about) – grundlagt 2016, afdelinger, ordrer pr. dag, tjenester
- [TopYou: 轨迹查询 (sporing)](https://track.szty56.com/) – TopYous sporing
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 2 € EU-håndteringsgebyr fra 1. november 2026
- [European Commission: Guidance on the 3 EUR customs duty and Union handling fee](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € pr. vareart solgt ved fjernsalg, uanset værdi
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – firmanavn og andre navne, TYZR-format, store bogstaver hos TopYou, ingen understøttede links, scanningstekster og faser, destinationsland ved TopYous egne scanninger, Beijing-tid og videresendte tidszoner, overtagelse ved USPS, GOFO, iMile og DPD, videresendte scanninger og afhentningsbesked, andet nummer, Lastmile-nummer på postlinje og linjenavn, ukendte numre og manglende scanninger, data før modtagelse, hvad Peek viser og hvordan overdragelse fungerer
:::
