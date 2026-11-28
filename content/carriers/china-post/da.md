---
title: China Post-sporing: nummer, statusser og hvem der leverer
description: Spor en China Post- eller EMS-pakke der ender på CN: UPU S10-nummertyper (L, R, C, E, U), oversatte kinesiske scanninger, overdragelse til lokal post og 11183/11185.
slug: china-post-tracking
published: 2026-11-28
updated: 2026-11-28
---

Spor en China Post- eller EMS-pakke, der ender på `CN` (to bogstaver, ni cifre og `CN`, det internationale UPU S10-format) på ems.com.cn eller i feltet ovenfor. Fordi UPU kun tillader ét S10-nummer pr. forsendelse, leverer dit eget lands nationale postvæsen — som USPS i USA, Royal Mail eller Parcelforce i Storbritannien, An Post i Irland, Canada Post i Canada eller Australia Post i Australien — med præcis det samme nummer, når pakken har forladt Kina; ved formelle efterlysninger kræver China Post, at afsenderen henvender sig til sit lokale posthus (China Posts linjer: 8610 11183 for EMS, 8610 11185 for Postal Business).

## China Post-sporingsnummer: sådan ser det ud

Et China Post-nummer består af to bogstaver, ni cifre og `CN`: det internationale forsendelsesnummer fastlagt af Verdenspostforeningen (UPU). Det første bogstav viser, hvilken type forsendelse der er tale om. Med opdigtede cifre:

| Nummer | Hvad det er |
| --- | --- |
| `LT482917367CN` | L: en sporet småpakke (brevpost). |
| `RR482917367CN` | R: rekommanderet brevpost. |
| `CP482917367CN` | C: en pakke. |
| `EA482917367CN` | E: EMS, China Posts eksprestjeneste. |
| `UA482917367CN` | U: en småpakke med varer "uden kundeorienteret sporing" ("without customer-oriented tracking"), med UPU's egne ord. Regn ikke med scanninger i dit land. |
| `ZS48291736701` | ZS, ZC eller ZA efterfulgt af 11 cifre: besvares også af China Posts sporing. |
| `LP48291057364829` | Ikke China Post: en logistikreference fra [Cainiao](carrier:cainiao) (AliExpress). |
| `YT4829105736482910` | Ikke China Post, men [YunExpress](carrier:yunexpress). |

Det sidste af de ni cifre er et kontrolciffer, så et forkert indtastet ciffer gør næsten altid nummeret ugyldigt. UPU tillader kun ét sådant internationalt forsendelsesnummer pr. forsendelse, og derfor virker det samme nummer på dit eget postvæsens side, så snart pakken når dit land.

For nogle forsendelser beder China Posts sporing om de sidste fire cifre i et kontakttelefonnummer, der er knyttet til forsendelsen.

## China Posts sporingsstatusser forklaret

China Post skriver sine scanninger på kinesisk. Stednavne står i kantede parenteser, her vist som `【…】`: et kinesisk sorteringscenter eller, i udlandet, landets navn på kinesisk, såsom `【英国】` for Storbritannien eller `【美国】` for USA. I den rækkefølge en pakke møder dem:

| China Post siger | Hvad det betyder for dig |
| --- | --- |
| `已揽收` | Modtaget: China Post har pakken. |
| `邮件到达【…】` | Ankommet til et China Post-kontor eller sorteringscenter. |
| `邮件离开【…】，正在发往下一站` | Forlod det kontor på vej til næste stop. |
| `【…】退回，备注:安检退回` | Et sorteringscenter sendte den tilbage ved sikkerhedskontrollen og behandler den, før den sendes videre; den er ikke på vej tilbage til afsenderen. Peek viser "Needs attention". |
| `送交出口海关` | Indleveret til kinesisk eksporttold. |
| `出口海关/留存待验` | Tilbageholdt af eksporttolden til kontrol. |
| `出口海关/放行` | Frigivet af eksporttolden. |
| `【…】已出口直封` | Lukket i en direkte eksportforsendelse på et af Kinas internationale udvekslingskontorer. |
| `已交承运商运输` | Overdraget til en transportør. |
| `航空公司接收`, `航空公司启运` | Et flyselskab modtog den og fløj derefter af sted med den. |
| `飞机进港` | Flyet er landet. |
| `到达寄达地处理中心` | Ankommet til behandlingscenteret i dit land. |
| `送交境外进口海关` | Indleveret til tolden i dit land. |
| `境外进口海关留存待验` | Tolden tilbageholder den til kontrol. |
| `寄达地海关允许进口` | Tolden har frigivet den til indførsel. |
| `离开【…】处理中心` | Forlod det center. |
| `已到达【…】投递局` | Ankommet til dit lokale omdelingskontor. |
| `【…】安排投递`, `邮件正在派送中` | Ude til levering. |
| `已试投/投递失败` | Dit postvæsen forsøgte at levere den, men det lykkedes ikke. |
| `【…】已妥投`, `已签收` | Leveret. |
| `已退回` | Returneret til afsenderen. |

De sidste scanninger kommer fra dit lands postvæsen og videresendes af China Post, nogle gange først efter leveringen. Dit eget postvæsens sporing med det samme nummer har det sidste stykke fra første hånd.

## Hvem leverer China Post-pakker i dit land

China Post flyver pakken ud af Kina; i dit land overtager det nationale postvæsen, gennem tolden og frem til din dør:

- **USA**: [USPS](carrier:usps).
- **Storbritannien**: [Royal Mail](carrier:royal-mail). Det fører pakken gennem britisk told og giver dig besked, hvis der skal betales moms eller told. EMS-numre (der starter med E) går i stedet til [Parcelforce](carrier:parcelforce), Storbritanniens EMS-operatør.
- **Irland**: [An Post](carrier:an-post). Siden 1. november 2026 opkræver EU et håndteringsgebyr på €2 pr. produkttype; hvis butikken ikke opkrævede det ved kassen, beder An Post dig betale det sammen med eventuel told og moms før levering.
- **Canada**: [Canada Post](carrier:canada-post). Slå nummeret op i Track-værktøjet; hvis der intet vises, beder Canada Post dig få afsenderen til at oprette en efterlysning hos postvæsenet i afsenderlandet.
- **Australien**: [Australia Post](carrier:australia-post). Det oplyser, at sporing af forsendelser fra udlandet afhænger af den tjeneste, der er brugt i afsenderlandet, og at toldbehandling kan tage op til 15 dage.

Leveringsforsøg, afhentningskort og hvor længe en pakke ligger på posthuset følger dit eget postvæsens regler; se dets side og [manglende levering (på engelsk)](guide:missed-delivery). En pakke, der [ikke bliver hentet (på engelsk)](guide:uncollected-parcel), sendes tilbage til afsenderen. Told, afgifter og håndteringsgebyrer på pakker fra udlandet gennemgås i [pakker i tolden (på engelsk)](guide:customs).

## China Post-sporing opdaterer ikke, pakke bortkommet eller returneret

**Ingen scanninger endnu.** China Post viser intet, før det har scannet pakken; indtil da har butikken kun oprettet en label. Spørg butikken, hvornår den indleverede pakken. I mellemtiden viser Peek "Waiting for China Post". Hvis China Post stadig ikke kender nummeret to dage senere, og ingen anden tjeneste har svaret på det, viser Peek "After two days, the carrier still doesn’t know this number. Check it, choose another carrier or delete the parcel."

**Stille efter afgang fra Kina.** Mellem flyvningen og dit postvæsens første scanning kan der gå dage uden nyt. Tjek dit eget postvæsens sporing med det samme nummer. Et nummer, der begynder med U, får muligvis aldrig scanninger i dit land. Læs mere i [hvorfor sporing stopper med at opdatere (på engelsk)](guide:tracking-not-updating) og [sporing af pakker fra Kina (på engelsk)](guide:tracking-from-china).

**Leveret, men ikke modtaget.** Dit eget postvæsen stod for leveringen, så start med dets sporing og leveringsbevis og derefter butikken. Se [leveret men ikke modtaget (på engelsk)](guide:delivered-not-received).

**Bortkommet eller beskadiget.** China Post angiver, at ved formel efterlysning af alle internationale forsendelser skal afsenderen henvende sig til sit lokale posthus ("for formal inquiry of all international items, the sender shall go to local post office"). USPS, Canada Post og Australia Post henviser også modtagere til afsenderen, som kontakter postvæsenet i afsenderlandet. Kontakt derfor butikken eller markedspladsen og bed om refusion eller en ny pakke; se [bortkomne pakker (på engelsk)](guide:lost-parcel).

**Returneret.** `已退回` betyder, at pakken er på vej tilbage til afsenderen. Spørg butikken, hvad den gør derefter; se [returneret til afsender (på engelsk)](guide:returned-to-sender).

## China Post-kontaktnummer og kundeservice

Den 10. november 2026 viste China Posts engelske hjemmeside disse numre:

- **Logistics and EMS**: 8610 11183.
- **Postal Business**: 8610 11185.

China Post oplyser ikke, hvilke sprog der tales. En formel efterlysning skal komme fra afsenderen, så butikken er hurtigere. Ved levering, genlevering eller told skal du kontakte dit eget nationale postvæsen.

## Spor en China Post-pakke med Peek

[Peek](/) genkender to bogstaver, ni cifre og `CN` som China Post med det samme, når kontrolcifferet passer, medmindre nummeret starter med YY: det er YYposts numre. Med et forkert kontrolciffer tager Peek det ikke for China Post, og vælger du China Post, siger Peek, at China Post "doesn’t issue numbers like this one": tjek nummeret i butikkens e-mail. Et ZS-, ZC- eller ZA-nummer med 11 cifre genkendes også som China Post med det samme. Indsæt bare ethvert andet nummer alligevel: Peek følger over 4.500 fragtfirmaer. Vælger Peek det forkerte fragtfirma, kan du vælge China Post med "Change".

Peek læser China Posts egen sporing. Når China Post beder om telefoncifre, prøver Peek i stedet den offentlige forhåndsvisning på ems.com.cn; Peek beder dig aldrig om dem. Hvis forhåndsvisningen også afviser, og ingen sporingstjeneste har pakken, melder Peek, at der mangler oplysninger: slå nummeret op på China Posts hjemmeside med de fire cifre. China Post giver besøgende kun sine tre nyeste scanninger, så Peek tilføjer indleveringsdatoen, når China Post oplyser den, og henter en mere fuldstændig historik fra sporingstjenester, når en af dem har den.

Peek viser status, historikken med kontoret for hver scanning (et kontor skrevet på kinesisk navngivet efter sin by, eller som skrevet hvis Peek ikke kan placere det) og destinationslandet. Scanninger vises på engelsk, hvor Peek kender China Posts formulering, og ellers på kinesisk. China Post angiver sine egne scanninger i kinesisk tid, selv ved ankomst i dit land; de senere scanninger, som kopieres fra dit nationale postvæsen, beholder som regel postvæsenets lokale tid. Peek viser hver scanning i din enheds tidszone; på peektracker.com og i iPhone- og Android-appen slutter historikken med "Times are in your time zone:" og dens navn. China Post oplyser hverken forventet leveringsdato, vægt eller udleveringssted, og Peek fjerner tjenestenavn, kontakter, afsender- og modtageroplysninger, budenes navne og telefonnumre samt kortkoordinater.

For et nummer, der ender på `CN`, spørger Peek — så snart China Post nævner dit land — også dit nationale postvæsen med det samme nummer (USPS, Royal Mail, An Post, Canada Post eller Australia Post; Parcelforce for EMS-numre til Storbritannien) og skifter over til det, så snart det viser friske scanninger, mens China Posts historik bevares. Nævner China Post intet land, går Peek ud fra dit: i iPhone- og Android-appen den region, der er indstillet på telefonen; på peektracker.com det land, du tilføjede pakken fra.

Peek tjekker China Post hver time, også når pakken er ude til levering. Når dit postvæsen overtager leveringen, følger Peek tempoet for det fragtfirma, der leverer pakken. Peek kan give dig besked ved "Every scan", "Important steps" eller "Delivery only". Det kan ikke kontakte China Post, dit postvæsen eller butikken for dig.

## Spørgsmål om China Post-sporing

### Hvor er min China Post-pakke?

Se den nyeste scanning: et kinesisk sorteringscenter i kantede parenteser (`【…】`) betyder, at pakken stadig er i Kina; så snart `到达寄达地处理中心` vises, er den nået frem til dit land, og du kan derefter følge det samme `CN`-nummer på dit eget nationale postvæsens hjemmeside.

### Hvorfor opdaterer min China Post-sporing ikke?

Sporingen holder typisk pause under den internationale flyvning eller ved importtolden mellem China Posts eksportscanning og dit nationale postvæsens første scanning. Bemærk, at numre der starter med `U` (`UA…CN`) er UPU-småpakker uden kundeorienteret sporing og måske ikke får scanninger i udlandet.

### Hvor lang tid tager China Post om at levere?

Leveringstiden afhænger af den valgte tjeneste (EMS-ekspres i forhold til almindelig brevpost eller pakke) og destinationslandet, så følg estimatet fra din butik eller markedspladsordre.

### Hvad betyder 已妥投 på China Post-sporing?

Det betyder leveret (for eksempel `【英国】已妥投` = leveret i Storbritannien). Fordi dit lokale nationale postvæsen stod for den endelige levering, bør du først tjekke dets sporing og leveringsbevis, hvis der intet er ankommet: se [leveret men ikke modtaget (på engelsk)](guide:delivered-not-received).

### Hvem leverer China Post-pakker i USA, Storbritannien, Irland, Canada eller Australien?

Det nationale postvæsen under præcis det samme `CN`-nummer: USPS i USA, Royal Mail i Storbritannien (Parcelforce for `E…CN` EMS-forsendelser), An Post i Irland, Canada Post i Canada og Australia Post i Australien.

:::sources
- [China Post: Introduction to China Post Group](https://english.chinapost.com.cn/en/folder/1408/3969-1.htm) – statsejet nationalt postvæsen
- [China Post: EMS and Logistics](https://english.chinapost.com.cn/en/folder/1408/3923-1.htm) – EMS-mærke
- [China Post: Contact us](https://english.chinapost.com.cn/en/category/1408/3925-1.htm) – sporingssider, formel efterlysning gennem afsenderen
- [China Post: Home page (English)](https://english.chinapost.com.cn/) – numre til Logistics and EMS samt Postal Business
- [UPU: S10 standard, Identification of postal items](https://www.upu.int/UPU/media/upu/files/postalSolutions/programmesAndServices/standards/S10-12.pdf) – nummerformat, tjenestebogstaver, U-serien uden sporing, ét nummer pr. forsendelse
- [GOV.UK: Tax and customs for goods sent from abroad: Overview](https://www.gov.uk/goods-sent-from-abroad) – Royal Mail fører varer gennem britisk told og oplyser, om moms eller told skal betales
- [Revenue: Union Handling Fee press release](https://www.revenue.ie/en/corporate/press-office/press-releases/2026/pr-092926-handling-fee.aspx) – €2 pr. produkttype fra 1. november 2026, betales ved kassen eller til postvæsenet før levering
- [Canada Post: Tracking international packages](https://www.canadapost-postescanada.ca/cpc/en/support/kb/tracking/tracking-international-packages.page) – pakker til Canada, afsenderen efterlyser, tidsrammer
- [Australia Post: International tracking](https://auspost.com.au/help/parcel-tracking/international-parcel/tracking) – sporing afhænger af afsenderlandets tjeneste
- [Australia Post: Customs](https://auspost.com.au/help/parcel-tracking/international-parcel/customs) – op til 15 dage
- [USPS: International Inquiries – The Basics](https://faq.usps.com/s/article/International-Inquiries-The-Basics) – forsendelser fra udlandet: efterlysning via afsenderens postvæsen
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – China Posts scanningsformuleringer, kontrol af telefoncifre, offentlig forhåndsvisning i browser og hvad Peek viser
:::
