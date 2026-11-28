---
title: DHLink sporing: numre, statusser og hvem der leverer
description: Spor en DHLink-pakke fra en DHgate-ordre: hvilke numre DHLinks sporing tager, hvad de engelske skanninger betyder, hvem der leverer og hvem du spørger.
slug: dhlink-spor-pakke
published: 2026-11-28
updated: 2026-11-28
---

DHLink (驼飞侠) er den grænseoverskridende logistikplatform under DHgate (敦煌网). Virksomheden blev grundlagt i 2014, sender ordrer for DHgate-sælgere og andre kunder fra Kina eller fra sine lagre i udlandet og overdrager dem til et fragtfirma i dit land på sidste strækning. Spor nummeret på DHLinks [sporingsside](https://www.dhlink.com/trackQuery?lang=en) eller i feltet ovenfor. Find nummeret på din DHgate-ordre, eller bed sælgeren om det.

## DHLink sporingsnummer: hvordan det ser ud

DHLink offentliggør intet fast nummerformat. Dets sporing tager op til 40 numre ad gangen og accepterer det, den kalder Global Tracking No., Logistics Delivery No. og DHLink pickup number. Former set på DHLink-pakker, med opdigtede cifre:

| Nummer | Hvad det er |
| --- | --- |
| `FC4829105736482` | FC og 13 cifre: DHLinks eget nummer, "DHLink No." På nogle linjer er det også sporingsnummeret. |
| `XZG482910573WL` | XZG, 9 cifre, WL: nummer fra en linjepartner, som DHLink finder som sporingsnummer. |
| `YLUS4829105736` | YLUS og 10 cifre: en USA-linje, som GOFO leverer. |
| `QH482910573648FS` | QH, 12 cifre, FS: endnu en USA-linje, som GOFO leverer. |
| `EYT4829105736HZ` | EYT, 10 cifre, HZ: en USA-linje, som SwiftX leverer. |
| `CYS48291057364` | CYS og 11 cifre: en USA-linje, som [UniUni](carrier:uniuni) leverer. |
| `JXCYI4829105736YQ` | JXC, YI, ZO, US eller ZI, 10 cifre, YQ: en form fra [Juxi Express](carrier:juxi-express)' fragtbreve, som DHLinks sporing også svarer på for visse pakker på en USA-linje. Peek spørger DHLink om JXC-numre; har sælgeren sendt med Juxi, vælger du Juxi Express med "Change". |
| `RR482917367CN` | To bogstaver, 9 cifre og CN: et [China Post](carrier:china-post)-nummer. Nogle DHLink-pakker rejser med China Post under sådan et nummer. |
| `GFUS48291057364829` | Ikke DHLinks: et [GOFO Express](carrier:gofo)-nummer, sidste strækning på visse USA-linjer. |

På et resultat viser DHLink "DHLink No." og "Tracking No.", og når et andet fragtfirma leverer, et "Last Leg No." for det firma. Hvis sporingsnummeret adskiller sig fra DHLink-nummeret, skal du bruge sporingsnummeret: slår du kun DHLink-nummeret op, vises sådan en pakke måske ikke. Uden `lang=en` i adressen åbner siden på kinesisk.

## DHLink sporingsstatusser forklaret

Et resultat viser statussen (`In transit`, `Delivered`), landekoder for afsender- og modtagerland, linjens kode, en forløbslinje med `Original Leaving`, `Destination Arrived`, `Pending delivery` og `Delivered` samt hver skanning med tidspunkt. En leveret pakke viser også "Signed for - Signed for time". Rækkerne kommer fra flere kilder, så samme skanning optræder ofte mere end én gang. DHLinks engelske linjer, i den rækkefølge en pakke møder dem:

| DHLink skriver | Hvad det betyder for dig |
| --- | --- |
| `Data Received; Data transferred and received by carrier` | På en linje til Sydøsteuropa: Pactic, platformen i Ungarn som linjen går igennem, har pakkens data. Før DHLinks lagerskanning viser Peek "Label created"; når linjen kommer igen efter flyveturen, forbliver pakken "In transit". |
| `Arrive at the collection warehouse` | DHLinks opsamlingslager i Kina har pakken. |
| `The collection warehouse has completed sorting` | Sorteret dér. |
| `Package received & scanned at warehouse` | Registreret ind på DHLinks lager. |
| `Parcel handed over to carrier,waiting for transportation` | Opsamlingspunktet har pakken, og den venter på transport. |
| `Package departed from the warehouse`, `The package has sent from the warehouse` | Den har forladt lageret. |
| `Warehouse scanning bagging`, `Master airway bill number received`, `Leave the warehouse` | På visse linjer: linjens lager i Kina har pakket forsendelsen i sæk til flyet, flyets hovedluftfragtbrev er modtaget, og pakken har forladt lageret. Peek viser "In transit". |
| `Customs export clearance completed`, `Package handed over to airline` | Kinesisk eksporttold har frigivet den, hvorefter et flyselskab overtog den. |
| `Flight departure`, `The flight has taken off` | Fløjet ud af Kina. |
| `Shipment Departed From Original Country at …` | Det samme: den har forladt Kina. |
| `Departed from airport of origin` | Flyet er lettet fra Kina ("In transit" i Peek). |
| `The flight has arrived and the board is being dismantled` | Landet; pallerne bliver brudt op. |
| `Dismantling the board` | Det samme: pallen brydes op i ankomstlufthavnen ("In transit" i Peek). |
| `Shipment has arriveved at destination Country at …` | I dit land. Stavningen er DHLinks egen. |
| `Arrived to … hub`, `Linehaul transit; Parcel has entered the linehaul network.` | Efter flyveturen: en partners hub i Europa, angivet med en kode hvor prikkerne står, og derefter hovedtransport videre. På linjen til Sydøsteuropa er dette Pactics linjer fra dens hub i Ungarn (kode HU). Peek viser "In transit". |
| `Customs clearance completed`, `Customs import clearance completed, awaiting pickup by local carrier` | Importtold har frigivet pakken; fragtfirmaet i dit land afhenter den næste gang. |
| `UPS/USPS/Fedex` | En overdragelse til det amerikanske fragtfirma er på vej. |
| `货物已交UPS/USPS/Fedex,The goods have been delivered to UPS/USPS/Fedex` | Overdraget til det amerikanske fragtfirma. Ikke leveret til dig endnu. |
| `Package picked up by last-mile carrier` | Fragtfirmaet i dit land har overtaget den. |
| `Scheduled for Delivery` | Ude til levering. |
| `Shipment has delivered at …` | Leveret. |

USA-linjer tilføjer hub-skanninger som `Arrived at Origin Hub` og `Departed Destination Hub`; på én linje er `Shipping Order Created.` linjens ordre, en time før lageret har pakken ("Label created" i Peek). Et lokalt fragtfirmas anlæg kan skrive `Parcel received by ABC01.`, med sin egen kode i stedet for ABC01, efter at have hentet pakken fra DHLinks lager, og minutter senere `Parcel departed from Facility.`, begge "In transit". Efter overdragelsen følger det leverende firmas linjer, såsom GOFOs `Arrived at GOFO Delivery Station` eller USPS' `No Access to Delivery Location` (et mislykket leveringsforsøg). UniUnis er `Parcel received at UniUni sorting hub.`, `Parcel processed.` (på den hub, minutter før udbringningen) og `Parcel scanned and in transit.`, sommetider efter hubbens by og delstat, alle "In transit" i Peek. På China Post-linjer vises China Posts linjer også, nogle kun på kinesisk: `中国邮政 已收取邮件` (China Post modtog forsendelsen), `送交出口海关` (frembudt til eksporttold), `已妥投` efter en landekode (leveret). For et ukendt nummer svarer siden `There is no track for this waybill number`.

## Hvem leverer DHLink-pakker

DHLinks hjemmeside er skrevet til sælgere og nævner ingen lokal leveringspartner per land. Dets sporing viser, hvem der leverer. På USA-linjer siger én skanning, at pakken gik til "UPS/USPS/Fedex" uden at angive hvilket; de efterfølgende linjer er det pågældende firmas, for eksempel [USPS](carrier:usps)'. Nogle USA-linjer slutter med [GOFO Express](carrier:gofo) eller SwiftX, hvis nummer vises som "Last Leg No." (GOFOs egen sporing finder også de DHgate-pakker, det leverer, på deres DHLink-nummer, FC og 13 cifre); i Schweiz kan det nummer tilhøre [DPD Switzerland](carrier:dpd-ch). Pakker sendt med China Post beholder deres CN-nummer hele vejen: se [China Post](carrier:china-post) for, hvem der leverer dem i dit land.

Når du kender fragtfirmaet og dets nummer, har dets egen sporing de lokale detaljer, og dets regler gælder for leveringsforsøg, beskedkort og afhentning: se [mislykket levering](guide:missed-delivery) (på engelsk). Siden 1. november 2026 opkræver EU et håndteringsgebyr på 2 € pr. varetype på onlineordrer uden for EU; sælgeren eller det leverende selskab betaler det og kan vælte det over på dig ved kassen eller før levering. For told og andre gebyrer på varer fra Kina, se [pakker tilbageholdt i tolden](guide:customs) (på engelsk).

## DHLink-pakke opdateres ikke, forsvundet eller leveret men mangler

**Ikke fundet.** Tjek nummeret på din DHgate-ordre, og prøv sporingsnummeret frem for DHLink-nummeret. Et lokalt leveringsnummer finder intet på DHLinks side: tjek det hos det pågældende fragtfirma. Hvis du har valgt DHLink med "Change" for et nummer, DHLink ikke kender, viser Peek "Waiting for DHLink" og fortsætter med at tjekke. Hvis DHLink to dage senere stadig ikke kender nummeret, og intet andet har svaret på det, viser Peek "After two days, the carrier still doesn’t know this number. Check it, choose another carrier or delete the parcel."

**Sidder fast efter flyveturen eller tolden.** Led efter et "Last Leg No." og tjek det hos det fragtfirma, hvis skanninger kan være nyere end DHLinks. Mere i [hvorfor sporing stopper med at opdatere](guide:tracking-not-updating) og [sporing af en pakke fra Kina](guide:tracking-from-china) (på engelsk).

**Leveret men ikke fremme, bortkommet eller forsinket.** DHLinks regler for refusion og erstatning ved problempakker er skrevet til sælgere: sælgeren rejser krav over for DHLink, og du tager det med sælgeren eller DHgate. Reglerne undtager det, køberen selv forårsager: en forsinket levering fordi pakken ikke blev afhentet eller kvitteret for, eller manglende toldbehandling fordi køberen ikke hjalp i tide. Hent den derfor i tide, besvar toldhenvendelser hurtigt og giv sælgeren besked tidligt: sælgeren kan kun rejse krav inden for 90 dage fra bestillingen af forsendelsen. Se [leveret men ikke modtaget](guide:delivered-not-received) og [bortkomne pakker](guide:lost-parcel) (på engelsk).

## DHLink kontakt og kundeservice

Ifølge DHLinks kontaktside, læst den 4. november 2026, er der én kundeservice-e-mail, dhlinkservice@dhlink.com, samt en formular til platformhenvendelser. Siden har også en chatassistent, "AI 小飞". Kontaktsiden er på kinesisk og angiver intet telefonnummer. For din ordre bør du først kontakte sælgeren gennem DHgate; for et leveringsproblem i dit land kontakter du det leverende fragtfirma.

## Spor en DHLink-pakke med Peek

[Peek](/) genkender et `YLUS`-nummer med 10 cifre direkte som DHLink. Dets `FC`-, `XZG`-, `QH`-, `EYT`- og `CYS`-numre er kun svage træf ud fra formen alene: indsæt nummeret alligevel, Peek følger mere end 4.500 fragtfirmaer. Det viser "Checking tracking services…" og derefter "DHLink has this parcel.", når DHLink kender nummeret; vælg ellers DHLink med "Change". Et `JXC`-nummer (`JXCYI`, `JXCZO`, `JXCUS` eller `JXCZI` med 10 cifre og `YQ`) deler form med Juxi Express: Peek spørger DHLink om disse `JXC`-numre, og hvis sælgeren sendte med Juxi, vælger du Juxi Express med "Change". Et China Post-nummer (to bogstaver, 9 cifre, CN) går til China Post.

Peek læser DHLinks sporing. Det viser statussen, historikken med hvert steds skanning og modtagerlandet og viser hver skanning én gang. Skanninger i Kina læses efter Beijing-tid, USPS-skanninger efter tiden i den amerikanske delstat og Pactics linjer på en rute gennem Ungarn efter Budapest-tid; øvrige beholder den tid, DHLink oplyser. DHLink angiver intet leveringstidspunkt, vægt eller tjenestenavn, og Peek fjerner postnumre og ordrereferencer. Dets eneste leveringsestimat er en videresendt USPS "Out for Delivery, Expected Delivery …"-skanning læst efter den amerikanske delstats tid: så længe den skanning er nyest, viser Peek dagens tidsrum, når skanningen giver et; en skanning med kun seneste tidspunkt tilføjer intet estimat, da "Out for delivery" allerede betyder i dag. Når "Last Leg No." er et GOFO-nummer (GFUS og 14 cifre), et SwiftX-nummer (SWX og 18 cifre) eller et DPD Switzerland-nummer (14 cifre der begynder med 060 eller 061), viser Peek det fragtfirma og dets nummer, spørger det og skifter over til det, så snart det viser nyere fremgang. Andre sidste-strækning-numre forbliver på DHLinks historik.

Mens DHLinks sporing svarer, tjekker Peek op til hvert 10. minut med pakken åben på skærmen, eller med notifikationer slået til hvis levering forventes i dag. Fra kl. 06:00 på fragtfirmaets ur den dag, pakken kommer ud til levering, kan tjek køre hvert 2. minut med pakken på skærmen eller notifikationer slået til. Hvis fragtfirmaet giver et leveringsvindue, starter de hurtigere tjek tidligst en halv time før det åbner. Lukket uden notifikationer er tjek normalt hver time. Når kun en sporingstjeneste svarer, tjekker Peek hver time, hver 3. time når den seneste skanning er en dag gammel, og op til hvert 15. minut den dag den kommer ud til levering, mens du har pakken åben eller notifikationer slået til. Så snart Peek følger pakken hos det firma, der leverer den, tjekker Peek i det firmas eget tempo. Det kan give dig besked ved hver skanning, ved vigtige trin eller kun på leveringsdagen. For tjek hos fragtfirmaet selv skifter Peek til hver time efter to dage uden nyt (12 timer mens pakken stadig viser "Label created" eller "Received by carrier"). Peek kan ikke kontakte DHLink, DHgate eller sælgeren for dig.

## Spørgsmål om DHLink sporing

### Hvad er DHLink?

DHgates grænseoverskridende logistikplatform, drevet af 驼飞侠科技（北京）有限公司 i Beijing. Den blev grundlagt i 2014 og sender ordrer fra Kina og fra oversøiske lagre via egne linjer og partnerfirmaer.

### Hvem leverer min DHLink-pakke?

Et fragtfirma i dit land. På USA-linjer overdrager DHLink pakker til UPS, USPS eller FedEx, eller til GOFO, SwiftX eller UniUni; i Schweiz somme tider til DPD. Kig efter "Last Leg No." på DHLinks resultat.

### Hvad betyder "The goods have been delivered to UPS/USPS/Fedex"?

DHLink har overdraget pakken til det amerikanske fragtfirma. Den er ikke leveret til dig endnu: derefter følger det pågældende firmas egne skanninger.

:::sources
- [DHLink: About us (关于我们)](https://www.dhlink.com/about/introduce) – grundlagt 2014, DHgates grænseoverskridende logistikplatform, for DHgate og andre kunder
- [DHLink: Home page (首页)](https://www.dhlink.com/) – direkte forsendelse fra Kina, oversøiske lagre, link til DHgates ordresporing
- [DHLink: Tracking (轨迹查询)](https://www.dhlink.com/trackQuery?lang=en) – 40 numre, accepterede numre, resultatfelter, DHLink No., Tracking No., Last Leg No., forløbslinje, "There is no track for this waybill number"
- [DHLink: Contact us (联系我们)](https://www.dhlink.com/about/connect) – kundeservice-e-mail, forespørgselsformular, chat
- [DHLink: Service agreement (服务协议)](https://www.dhlink.com/agreementPage/service) – operatør 驼飞侠科技（北京）有限公司
- [DHgate Seller Help: DHLink物流异常件赔付规则 (DHLink erstatningsregler)](https://seller.dhgate.com/help/c7502/516002.html) – erstatning til sælgere, logistiktyper, undtagelser ved køberforårsaget forsinkelse og mislykket toldbehandling, krav inden for 90 dage fra logistikordren
- [Revenue: Union Handling Fee press release](https://www.revenue.ie/en/corporate/press-office/press-releases/2026/pr-092926-handling-fee.aspx) – 2 € pr. varetype fra 1. november 2026 for varer købt uden for EU, betalt af sælgeren eller leveringsselskabet, som kan vælte det over ved kassen eller før levering
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – nummerformer (YLUS genkendt direkte; CYS på en UniUni USA-linje; JXC delt med Juxi Express), skanningstekster (Pactics data-, hub- og linehaul-linjer; en linjes pakning, luftfragtbrev og flylinjer; UniUnis hub- og transit-skanninger; en USA-linjes ordre; et lokalt anlægs modtagelse og afgang), sidste-strækning-partnere, GOFOs sporing der tager DHLinks FC-numre, hvad Peek læser og viser
:::
