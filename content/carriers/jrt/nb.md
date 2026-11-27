---
title: JRT-sporing: JRT…MM-nummer, statuser og lokal levering
description: Spor en JRT-pakke (今日通, Jinritong) fra Xiamen i Kina: JRT…MM-format, kinesiske og engelske flyskanninger, videreføring til DHL/UPS/FedEx og hvem du spør.
slug: jrt-sporing
published: 2026-11-27
updated: 2026-11-27
---

JRT (今日通, Jinritong, 厦门今日通商贸有限公司) er en grensekryssende speditør i Xiamen i Kina med sporingsnumre som består av `JRT`, ti siffer og `MM`. Lim inn `JRT…MM`-nummeret ditt i feltet over for å følge pakken fra Kina gjennom flyavgang, importfortolling og overlevering til lokal transportør: JRTs sporing videresender skanninger fra DHL i Europa (samt CTT Express i Spania, InPost i Polen og FedEx på Irland-linjen), UPS i USA og J&T Express i Mexico.

## JRT-sporingsnummer: slik ser det ut

JRTs egne fraktbrev består av `JRT`, ti siffer og `MM`, totalt 15 tegn; `JRT` er initialene til 今日通. Eksempler med oppdiktede siffer:

| Nummer | Hva det er |
| --- | --- |
| `JRT4829105736MM` | `JRT`, ti siffer og `MM`: et standard JRT-fraktbrev. |
| `JRT482910573MM` | Bare ni siffer: ikke JRTs format. Sjekk om det mangler et siffer. |
| `JRTO4829105736MM` | Bokstaven `O` etter `JRT`: Peek leser det som `JRT4829105736MM`, den eneste formen JRTs sporing kjenner. |
| `UP4829105736182940` | `UP` og seksten siffer: en avsenderreferanse som kan finne en JRT-ordre, men ingenting tyder på at bare JRT-avsendere bruker den. |
| `RR482910576CN` | To bokstaver, ni siffer og `CN`: et [China Post](carrier:china-post)-nummer, ikke JRT. |

Mellomrom og små bokstaver spiller ingen rolle i Peek. JRTs egen sporing finner også en ordre på selgers referansenummer hvis det skrives nøyaktig slik selgeren oppga det, med bindestreker.

**En lenke med tall i stedet for domenenavn.** JRTs sporing kjører på en ren serveradresse over ukryptert HTTP uten domenenavn, og Peek kjenner ingen annen nettside for JRT. En lenke dit har en talladresse i stedet for nettstednavn og inneholder `trackIndex.htm`, `documentCode=` og et nummer eller en referanse: du kan lime lenken rett inn i Peek. Peek lenker ikke selv til sporingen fordi en ukryptert HTTP-lenke ville sendt nummeret ditt i klartekst.

## Hva statusene i JRT-sporingen betyr

Mange av JRTs linjer står på kinesisk etterfulgt av JRTs egen engelske oversettelse, og Peek gjengir dem samlet. Fordi Peek-appen ikke har norsk grensesnitt, vises Peeks egne hovedtrinn på engelsk: «Label created» (etikett opprettet), «Received by carrier» (mottatt av transportør), «In transit» (underveis), «At customs» (i tollen), «Out for delivery» (ute til levering), «Delivery attempted» (leveringsforsøk), «Ready for pickup» (klar til henting), «Delivered» (levert) og «Returned to sender» (returnert til avsender). I rekkefølgen en pakke møter dem:

| JRT skriver | Hva det betyr for deg | Peek viser |
| --- | --- | --- |
| `货物电子信息已经收到` | «Elektronisk fraktinformasjon mottatt»: selgeren har opprettet ordren. Skrevet automatisk av plattformen Hualei (华磊), ikke en fysisk skanning. | «Label created» |
| `Shipment information received` | Sendingsdataene har nådd JRT; ingen har skannet pakken ennå. | «Label created» |
| `到达收货点` | Ankommet JRTs mottakssted: første fysiske skanning i JRTs hender. | «Received by carrier» |
| `货物离开操作中心` | Pakken har forlatt JRTs driftssenter. | «In transit» |
| `深圳集散中心 已收货` | JRTs knutepunkt i Shenzhen har mottatt pakken etter driftssenteret og før flyet. Peek viser den på engelsk: «Received at the Shenzhen distribution center». | «In transit» |
| `货物打包完成，已发往航司` | Pakket og sendt til flyselskapet før flyvningen. Peek viser «Goods packed and sent to the airline». | «In transit» |
| `抵达航司已起飞` | Hos flyselskapet, og flyet har lettet fra flyplassen som nevnes. Peek viser «Arrived at the airline, flight departed». | «In transit» |
| `货物已交机场,等待航班安排中 Cargo delivered to airport, pending flight arrangement` | Levert på flyplassen i påvente av fly. «Delivered» betyr levert til flyplassen, ikke til deg. | «In transit» |
| `报关已放行等待…号航班起飞 Customs released,Awaiting departure on flight of the …` | Eksportklarert i Kina, venter på flyet den datoen som står i teksten. | «In transit» |
| `航班起飞，以实际抵达为准 Flight departure subject to actual arrival time` | Flyet har lettet. | «In transit» |
| `航班落地，拆板中 Flight landing, dismantling board in progress` | Flyet har landet. Flypallene pakkes opp før fortolling. | «In transit» |
| `机场内等待提货，等待清关 Waiting for cargo pickup and customs clearance at the airport.` | På ankomstflyplassen, venter på henting og importfortolling. | «At customs» |
| `理货较慢，导致提货缓慢，已催促尽快处理 Slow cargo sorting has resulted in delayed pickup. We have followed up for prompt handling.` | Treg sortering på flyplassen forsinker hentingen, og JRT har purret. En forsinkelse, ikke en tapt pakke. | Ingen ny status: forrige status blir stående |
| `已提货，清关中 The goods have been picked up and are currently undergoing customs clearance` | Importfortolling pågår. | «At customs» |
| `清关完成，卡车发运 Customs clearance completed; truck dispatched.` | Ferdig fortollet og sendt videre med lastebil. | «In transit» |
| `已交服务商 Delivered to service provider` | Overlevert til selskapet som leverer siste etappe. Ikke levert til deg. | «In transit» |

Enkelte linjer som JRT bare skriver på kinesisk, viser Peek på engelsk slik andre speditører skriver dem: `货物电子信息已经收到` som «The electronic cargo information has been received», `到达收货点` som «Arrived at the receiving point», `货物离开操作中心` som «Goods leave the operation center» og `快件操作完成` som «Shipment departed from facility». Flyplasslinjen `货物已交机场,预计…号的航班 Goods delivered to airport, scheduled on flight of the …` viser Peek som «Goods delivered to airport» siden flyet ennå ikke har gått. Tre andre rent kinesiske linjer før flyet vises også som «In transit»: `分拣包裹已出库` («Sorted parcel has left the warehouse»), `货物到达口岸，预计…号航班起飞` («Arrived at the port, booked on a flight») og `预计…号航班飞` («Booked on a flight»).

På ruten til Europa kommer tre senere linjer kun på kinesisk, og Peek viser dem på engelsk under «In transit»: `航班已抵达` («Flight has arrived»), `清关完成，中转德国中` («Customs cleared, in transit to Germany») og `抵达德国，已交服务商` («Arrived in Germany, handed to the delivering carrier», overlevert til leveringsselskapet som DHL — ikke levert til deg).

**Med bil gjennom Kasakhstan.** På JRTs landeveisløsning til Europa forlater pakken Kina over land i stedet for med fly, med fem rent kinesiske linjer som Peek viser på engelsk under «In transit»: `已装柜发车，待抵达新疆` («Loaded into a container and on its way to Xinjiang»), `已抵达新疆，换装/报关，待出境` («Arrived in Xinjiang for transloading and export declaration, waiting to leave China»), `因哈萨克斯坦拥堵，外车入境新疆拥堵缓慢，在等待外车倒装后报关出境。` («Congestion in Kazakhstan is slowing foreign trucks into Xinjiang; the parcel will be declared for export once it is reloaded onto one» — kø på grensen, ikke en mistet pakke), `已出境，哈萨克斯坦阿拉木图` («Left China, Almaty, Kazakhstan») og `哈俄边境，待办理手续进入俄罗斯` («At the Kazakhstan–Russia border, awaiting the formalities to enter Russia», en transittgrense og ikke ditt eget lands toll).

**Linjer kun på engelsk.** Andre JRT-linjer kommer bare på engelsk, alle vist som «In transit» med mindre annet er oppgitt:

- `Shipment arrived at facility`, `Sending to the airport`, `The goods have been packed and sent to the airline`, `Package has been packed and delivered to airport`, `Cargo handed over to the airline`: på vei til flyet. `Domestic customs clearance completed` er eksportfortolling i Kina, ikke tollen i ditt land.
- `Arriving at the airport - arranging flights`, `Arriving at the airline, in the process of booking a flight`: fly bookes.
- `Flight is expected to take off on …`, `The flight is expected to depart on … and land on …`, `Estimated departure is …` eller `Estimated arrival date is …`: flyet er booket (datoformat måned.dag, f.eks. `11.12` for 12. november).
- `Flight has flown`, `The flight has departed and is expected to … land`, `The flight has taken off`: i luften.
- `Sorry, the second flight has been delayed and is expected to … land`: tilslutningsflyet er forsinket, med ny ankomstdag.
- `During transportation to …`: under transport til landet som nevnes.
- `Flight has arrived`, `The flight has arrived and is awaiting dismantling`, `Arrived at Destinated Airport`, `The NOA has been received` (ankomstvarsel fra flyselskapet), `Dismantling and picking up goods in progress`: landet, før toll.
- `Customs clearance of the cargo is in progress` og `Arrived at customs clearance warehouse`: «At customs». Deretter `Customs clearance completed` eller `Customs clearance in destination country completed`.
- `In transit to final service provider`, `Delivery to final service provider`, `The goods have been handed over to the carrier`, `The cargo has been picked up and arrived at the warehouse，awaiting outbound shipment`, `It has been handed over to the service provider.` og `It has been transferred to the provider`: videre til eller overlevert til leveringsselskapet, ikke levert til deg.
- `returned shipment has been received by processing center`: den returnerte pakken er tilbake hos JRT («Returned to sender»).

**Linjer med skråstrek eller komma.** Noen sendinger kombinerer kinesisk og engelsk med skråstrek eller komma, alle «In transit»: `预计…航班/Flight scheduled for …`, `航班已启运/Package in transit to destination country`, `航班已落地，拆板中/Package arrived at destination country`, `货物已清关完成/Import customs clearance complete`, `国内清关完成` (kinesisk eksporttoll), `预计配载航班…月…日,Expected flight on …` (planlagt flydato, ikke leveringsdato) og `航班已起飞,The flight has taken off from the origin`.

**Kopierte linjer fra DHL, FedEx, UPS og J&T Express Mexico.** Til Europa videresender sporingen DHLs linjer, blant annet `the instruction data for this shipment have been provided by the sender to dhl electronically` («Label created»), `shipment in transit to dhl`, `The shipment has been posted by the sender at the retail outlet`, `pick-up was successful.`, `the shipment has been processed in the parcel center of origin`, `loaded to movement / tour vehicle`, `The shipment has arrived in the destination country/destination area.`, `The shipment has left the import parcel center in the destination country/destination area.` og `The shipment is being prepared for delivery in the delivery depot` («In transit»); `The shipment has been loaded onto the delivery vehicle` («Out for delivery»); `not delivered-consignee could not be reached`, `The shipment could not be delivered. Please find more information here.`, `The recipient was not present. Please find more information here.` og `not delivered-bad address` («Delivery attempted»); `held for consignee collection` og `The shipment is ready for collection from a ParcelShop/retail outlet. Please find more information here.` («Ready for pickup»); `shipment will be returned to origin-not ordered` og `shipment will be returned to origin-bad address` («Returned to sender»); samt `the shipment has been successfully delivered` («Delivered», eller «Returned to sender» etter en returlinje). På Irland-linjen med FedEx starter noen pakker med `Shipment information sent to FedEx` («Label created») etterfulgt av FedEx-linjer som `Arrived at FedEx hub`, `Departed FedEx hub`, `On the way` og `Delivery option requested, hold request received - check back later for shipment status` («In transit»). Til USA vises kinesiske flylinjer (`…号航班`, `航班已飞`, `航班抵达`, `提货中`, `清关完成，已提货，待交仓`, `已交仓`) og deretter UPS-skanninger på kinesisk som Peek viser på UPS' engelsk (`Shipper created a label, UPS has not received the package yet.`, `Arrived at Facility`, `Departed from Facility`, `Processing at UPS Facility`, `Picked Up`, `Loaded on Delivery Vehicle`, `Out For Delivery Today`, `Delivered`). Til Mexico kopieres J&T Express Mexicos spanske linjer med by og avdeling i 【】-klammer frem til `…está en camino a tu domicilio.` («Out for delivery»).

**Klokkeslett.** JRTs egne linjer bruker Peking-tid (`UTC+8`): om vinteren er 17:00 i Peking lik 10:00 norsk tid (`UTC+1`). Kopierte DHL-linjer leses på lokal tid for byen som nevnes (eller tysk tid / mottakerlandets tid når by mangler), UPS-linjer på amerikansk lokaltid og J&T Express Mexicos linjer på Mexico by-tid.

## Hvem leverer JRT-pakker i Europa og Norge

**Til Europa** avsluttes de fleste JRT-linjer med [DHL](carrier:dhl), bortsett fra FedEx-linjen til Irland ([FedEx](carrier:fedex)), Spania-linjen som går til CTT Express og Polen-linjen som går til InPost. På DHL-linjene leverer JRT pakken inn hos Deutsche Post, og sporingen kopierer DHLs skanninger. Når JRT har levert pakken inn, inneholder JRTs datapost DHLs nummer i stedet for JRTs eget. Når Peek kjenner igjen nummeret som DHL, viser det «Delivery with DHL» med nummeret (eller «Announced to DHL, not received yet» mens DHL bare har forhåndsmeldingen) og bytter til DHLs egen sporing så snart DHL har en skanning. For sendinger til Norge eller andre land der et annet lokalt selskap tar siste del av leveringen — eller når Peek ikke viser noe lokalt nummer — bør du spørre selgeren hvilket selskap som leverer og be om det lokale sporingsnummeret. Leveringsforsøk, hentesteder og hentefrister følger det lokale selskapets regler.

**Toll og avgifter.** Pakker fra Kina til Norge følger norske import- og mva.-regler, mens nettbestillinger sendt fra utenfor EU inn til et EU-land siden 1. november 2026 også ilegges EUs håndteringsgebyr (Union handling fee) på 2 € per varetype i tillegg til eventuell toll og mva. Mer om toll og gebyrer finner du i guiden om [pakker i tollen](guide:customs) (på engelsk).

## JRT-pakke som ikke kommer frem eller sporing som står stille

**Bare de første linjene.** `货物电子信息已经收到`, `Shipment information received` eller bare en DHL-/FedEx-etikettlinje betyr at selgeren har opprettet sendingen, men at ingen har skannet pakken ennå. Spør selgeren hvis ingenting skjer etter noen dager.

**Venter på fly.** Flylinjen oppgir planlagt avreisedag, og `航班延误，预计…号飞出 Flight delayed, estimated departure on …` gir en ny dag. Hvis den dagen passerer uten avgang, gi beskjed til selgeren.

**Ingenting etter `已交服务商`, `Delivery to final service provider` eller `已交仓`.** JRTs del er ferdig. Hvis ikke sporingen kopierer skanningene fra den lokale transportøren eller Peek viser et lokalt nummer, ber du selgeren om nummeret til leveringsselskapet. Mer i guiden om [pakker fra Kina](guide:tracking-from-china) (på engelsk).

**Ingenting funnet.** For et ukjent nummer gir JRTs sporing én tom rad med nummeret som referanse. Peek tolker det som ikke funnet: først «Waiting for JRT», og etter to dager «Check the tracking number». Sammenlign nummeret tegn for tegn med meldingen fra selgeren.

**Adressefeil.** Etter `not delivered-bad address` må du kontrollere adressen på bestillingen og gi selgeren beskjed straks. Kommer `shipment will be returned to origin-bad address`, går pakken i retur: be selgeren om refusjon eller ny sending.

**Levert, men ingenting kom.** Leveringsskanningen kommer fra den lokale transportøren, ikke JRT: kontakt det selskapet og gi deretter beskjed til selgeren. Mer i guiden om [levert, men ikke mottatt](guide:delivered-not-received) (på engelsk).

**Mistet eller returnert.** Det er selgeren som bestilte frakten hos JRT, så be selgeren eller markedsplassen om refusjon eller ny pakke.

## Kontakt og kundeservice hos JRT

Peek fant ingen kontaktopplysninger for JRT: verken egen nettside utover sporingen, telefonnummer, e-post eller adresse for mottakere. Det er selgeren som bestilte frakten hos JRT, så spør selgeren først, og markedsplassen hvis selgeren ikke svarer.

## Spor en JRT-pakke med Peek

[Peek](/) gjenkjenner et `JRT…MM`-nummer med en gang ut fra formen (`JRT`, ti siffer og `MM`), som ingen annen transportør i katalogen bruker, samt lenker til JRTs sporing. For en 16-sifret `UP`-referanse spør Peek JRTs sporing om den kjenner nummeret og velger JRT ved treff. Andre referanser kan du lime inn likevel — Peek følger over 4 500 transportører — og velge JRT via «Change» (søk på JRT, 今日通 eller Jinritong) eller «Change carrier» på en allerede lagt til pakke. Ved referansesøk viser Peek JRT-nummeret under «JRT tracks this as» frem til overlevering; merk at Peek fjerner bindestreker, så be selgeren om `JRT…MM`-nummeret hvis referansen inneholder bindestrek.

Peek viser status, hver linje med sted og mottakerland når det oppgis, basert på selve hendelsene og ikke sammendraget. Klokkeslett vises i din egen tidssone. Peek holder tilbake linjer som er skrevet mer enn tolv timer før sitt oppgitte tidspunkt (eller mer enn seks timer før i samme sekund som en slik linje) og linjer datert etter kontrolltidspunktet. Da starter historikken med «Other sites may show more steps, but they’re only predictions.» og «Show them» viser linjene i grått («Predicted for …») uten at de endrer status eller sender varsler. Peek utelater navn, adresse, postnummer, vekt, gebyrer, signatur og leveringsbevis, og viser ingen forventet leveringsdato siden JRT ikke oppgir noen.

Når JRTs datapost inneholder et DHL-, FedEx-, [Swiss Post](carrier:swiss-post)- eller internasjonalt postnummer (to bokstaver, ni siffer, to bokstaver) eller et nummer som entydig tilhører én annen transportør, spør Peek også den transportøren og flytter sporingen dit så snart den viser en minst like fersk skanning, samtidig som JRT-historikken beholdes. Har du et annet lokalt nummer fra selgeren, legg det til som en egen pakke.

Mens pakken er åpen i Peek, eller på leveringsdagen med varsler på, sjekker Peek opptil hvert 10. minutt; ellers og når JRT er stille (12 timer på de første linjene eller to dager uten nytt) én gang i timen. Når pakken er «Out for delivery», sjekker Peek opptil hvert 2. minutt fra kl. 06.00 lokal tid på utleveringsdagen så lenge JRT eller den overtakende transportøren svarer direkte (hvert 5. minutt når oppslaget krever nettleser, og via sporingstjenester én gang i timen, hver 3. time etter en rolig dag og hvert 15. minutt på leveringsdagen). Varsler velges mellom «Every scan», «Important steps» (toll, retur, samt DHLs/UPS'/J&Ts utleverings-, forsøks-, hentesteds-, leverings- og returlinjer — ikke fly eller `已交服务商`) og «Delivery only». Peek kan ikke kontakte JRT eller selgeren for deg.

## Spørsmål om JRT-sporing

### Hva er JRT (今日通 / Jinritong), og hvordan ser et JRT…MM-nummer ut?

JRT er 厦门今日通商贸有限公司 (Xiamen Jinritong), en speditør i Xiamen i Kina. Fraktbrevene består av 15 tegn (`JRT`, ti siffer og `MM`; ni siffer betyr et manglende siffer), og sporingen kjører på plattformen Hualei (华磊).

### Hvorfor står det «delivered» hos JRT når pakken ikke har kommet?

På grunn av JRTs engelske formuleringer: `Cargo delivered to airport` betyr at JRT leverte pakken på flyplassen før flyvningen, og `Delivered to service provider` eller `Delivery to final service provider` betyr overlevering til lokal transportør. Ingen av dem betyr levert til deg, og Peek beholder dem som «In transit».

### Hvem leverer JRT-pakker i Europa, USA og Mexico?

Til Europa ender de fleste JRT-linjer hos DHL (med CTT Express til Spania, InPost til Polen og FedEx på Irland-linjen). Til USA leverer UPS, og til Mexico leverer J&T Express Mexico. Andre steder — eller hvis ingen lokal transportør vises — må du spørre selgeren om hvilket selskap som leverer og om det lokale sporingsnummeret.

### Hvordan kontakter jeg JRT om en forsinket eller manglende pakke?

Kontakt selgeren eller markedsplassen der du bestilte, siden det var selgeren som bestilte frakten hos JRT. Utover den ukrypterte HTTP-sporingen fant Peek verken nettside, telefonnummer eller e-postadresse for mottakere hos JRT.

:::sources
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – selskapsnavn og initialer, Hualei-plattformen, sporing på ren serveradresse over HTTP, lenker uten publisert lenke, `JRT…MM`- og `UP`-formater, `O` etter `JRT` lest som `JRT`, søk på nummer eller referanse, linjetekster og stadier, engelsk for kinesiske linjer, landeveisløsning gjennom Xinjiang og Kasakhstan, kopierte DHL-, FedEx-, UPS- og J&T Express Mexico-linjer, overføring til DHL, FedEx og Swiss Post, Peking-klokke og lokale klokker, svar ved ukjent nummer samt hva Peek viser, holder tilbake og utelater
- [Europakommisjonen: EU Customs Reform (på engelsk)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – EUs håndteringsgebyr på 2 € fra 1. november 2026
:::
