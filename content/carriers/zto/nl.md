---
title: ZTO Express pakket volgen (中通快递): nummers, statussen
description: Volg een ZTO Express-pakket (中通快递) in China: 12- en 14-cijferige nummers, betekenis van 已签收 en 已代收, ZTO International 771 en klantenservice 95311.
slug: zto-express-pakket-volgen
published: 2026-11-27
updated: 2026-11-27
---

Volg een ZTO Express-pakket (`中通快递`, Zhongtong) op het Chinese vasteland door het 12-cijferige of 14-cijferige (beginnend met `7`) vrachtbriefnummer (`运单号`) in het vak hierboven of op [de Chinese tracker van ZTO](https://www.zto.com/check) in te voeren. ZTO werd op 8 mei 2002 opgericht in Shanghai (38,52 miljard pakketten in 2025) en vervoert binnenlandse bestellingen — vaak richting het magazijn van een expediteur, waar `已签收` («Signed for») betekent dat de expediteur het pakket heeft ontvangen. Internationale zendingen lopen via [ZTO International](carrier:zto-international) met `771`-nummers op `ztoglobal.com`, en de Chinese hotline van ZTO is `95311`.

## ZTO Express-trackingnummer: hoe het eruitziet

ZTO noemt het nummer de vrachtbrief (`运单号`). Peek kent twee vormen voor ZTO Express (met verzonnen cijfers):

| Nummer | Wat het is |
| --- | --- |
| `784920573280` | 12 cijfers: een vrachtbrief van ZTO Express, maar ook [FedEx](carrier:fedex), [SF Express](carrier:sf-express) en anderen gebruiken 12 cijfers. |
| `74829105736428` | 14 cijfers beginnend met `7`: eveneens ZTO Express, een vorm die DPD, SEUR, BRT en anderen delen. |
| `77148291057364` | `771` en nog 11 cijfers: de vorm van ZTO International. Peek vraagt eerst ZTO International. |

Spaties maken in Peek niets uit. De eigen tracker van ZTO accepteert tot 10 nummers tegelijk, gescheiden door komma's of spaties.

**Waar je het vindt.** De afzender (meestal de winkel) bewaart zijn kopie van de vrachtbrief (`发件联`) en stuurt het nummer mee met de verzendgegevens.

**Op de site van ZTO.** Typ het nummer op `zto.com/check` en druk op `查询` (zoeken). Mogelijk moet je eerst een puzzel oplossen. Voor meldingen per update kun je de QR-code op die pagina scannen met WeChat.

## Betekenis van de statussen bij ZTO Express

Elke scan heeft een korte Chinese stapnaam. Omdat de app van Peek niet in het Nederlands is vertaald, toont Peek zowel de Engelse vertaling van de stap als de eigen fase in het Engels:

| ZTO zegt | Peek toont | Fase in Peek | Wat dit voor jou betekent |
| --- | --- | --- | --- |
| `已取件` | «Collected» | «Received by carrier» | Een koerier van ZTO heeft het pakket opgehaald bij de afzender. |
| `已收件` | «Received» | «Received by carrier» | Een vestiging van ZTO heeft het pakket ingenomen. |
| `运输中` | «In transit» | «In transit» | Onderweg tussen hubs en vestigingen van ZTO. |
| `派件中` | «Out for delivery» | «Out for delivery» | Een bezorger heeft het pakket mee op zijn rit. |
| `已代收` | «Held at a collection point for the recipient» | «Ready for pickup» | Een afhaalpunt, zoals een pakketstation (`驿站`), heeft het voor je aangenomen. |
| `已签收` | «Signed for» | «Delivered» | Iemand heeft getekend (`签收`): jijzelf, iemand namens jou of het magazijn waarnaar het verzonden is. |
| `异常通知` | «Exception notice» | «Needs attention» | ZTO meldt een uitzondering. Peek toont alleen de stapnaam; de volledige tekst staat op de tracker van ZTO. |

Overige stappen, zoals een retour of omleiding, blijven in Peek in het Chinees staan.

**Naar een expediteur gestuurd?** Dan betekent `已签收` dat het magazijn van de expediteur voor ontvangst heeft getekend en het ZTO-traject voorbij is: vanaf daar volg je het eigen nummer van de expediteur. ZTO heeft ook een eigen internationale bundeldienst (`中通国际集运`) die pakketten in een magazijn samenvoegt; hiervoor bel je `95311` op het Chinese vasteland en vraag je naar de internationale afdeling.

## Bezorging door ZTO Express in China: koeriers, stations en pakketkluizen

Op het Chinese vasteland bezorgt een ZTO-koerier het pakket aan de deur of wacht het bij een pakketstation (`驿站`), afhaalpunt of slimme pakketkluis (`智能快件箱`) in de buurt: de serviceovereenkomst van ZTO met verzenders staat dit toe. Volgens de Chinese regels voor de pakketmarkt (van kracht sinds 1 maart 2024) is voor levering in een kluis of pakketstation toestemming van de ontvanger nodig, en mag je het pakket bij ontvangst controleren tegen de vrachtbrief en weigeren bij zichtbare schade aan de verpakking of kwetsbare inhoud.

De overeenkomst van ZTO raadt bovendien aan om het ophalen en openen van een pakket bij de deur, kluis of pakketstation op video vast te leggen als bewijs. Een bezorging volgens afspraak geldt als bevestigd als niemand binnen drie dagen schriftelijk bezwaar maakt.

Uit de veelgestelde vragen van ZTO:

- **Geen afhaalcode voor een kluis?** Zoek het filiaal voor jouw adres via de [vestigingszoeker van ZTO](https://www.zto.com/service/station) (`服务网点查询`) of geef het nummer door aan `95311` voor het telefoonnummer van de bezorger of vestiging.
- **Gevraagd te betalen voor te lang liggen in een kluis?** ZTO zegt bij het ophalen op overslaan (`跳过`) te tikken zodat je niets betaalt.
- **Bezorgkosten gevraagd?** Het bezorgende filiaal mag geen bezorgkosten bij je in rekening brengen.
- **Buiten het bezorggebied?** Haal het pakket op bij de vestiging of vraag de afzender om doorsturen met een andere vervoerder.
- **Verkeerd adres?** Zodra het pakket in bezorging of afgeleverd is, kan het adres niet meer worden gewijzigd. Daarvoor kan alleen de afzender een verzoek doen bij het versturende filiaal.

## ZTO-pakket zonder updates, vermist of retour

**Nog niets te zien.** Vindt de tracker van ZTO niets op het nummer, controleer het dan bij de verkoper. Een pakket onder ZTO Express toont «Waiting for ZTO Express», en zonder gekozen vervoerder «No tracking history yet».

**Geen updates.** Als een pakket onderweg 4 dagen geen nieuwe scan krijgt, meldt Peek dat pauzes vaak voorkomen en plaatst het een link «Get help from ZTO Express» naar de contactpagina van ZTO. Zie ook de gids over [pakketten uit China volgen](guide:tracking-from-china) (in het Engels).

**Verloren of beschadigd (op het Chinese vasteland).** Vraag de verkoper: volgens de overeenkomst van ZTO dient de afzender binnen 30 dagen na ontdekking schriftelijk een claim in. Zonder verzekering (`保价`) vergoedt ZTO maximaal 500 yuan, en met verzekering tot de aangegeven waarde. Ontbrekende of beschadigde inhoud bij onbeschadigde buitenverpakking is uitgesloten.

**Retour.** Op het Chinese vasteland mag ZTO een pakket terugsturen bij een onduidelijk adres, weigering door de ontvanger of na twee mislukte bezorgpogingen buiten de schuld van ZTO.

**Bezorgd gemeld, maar niets ontvangen.** `已代收` betekent dat een afhaalpunt het bewaart en `已签收` dat iemand heeft getekend — niet altijd jijzelf. Controleer pakketstations, kluizen, receptie en eventueel de expediteur. Voor een klacht in China bel je `95311` of gebruik je het klachtenformulier (`投诉反馈`, inloggen vereist), met recht op beroep bij de Chinese postautoriteiten als ZTO niet tijdig of naar tevredenheid antwoordt. Meer in de gids over [bezorgd maar niet ontvangen](guide:delivered-not-received) (in het Engels).

## Telefoonnummer en klantenservice van ZTO Express

Door ZTO gepubliceerde kanalen:

- **`95311`:** nationale klantenservice op het Chinese vasteland (`全国统一客服热线`), volgens de FAQ bereikbaar van 08:30 tot 17:30 Chinese tijd (op de Engelse site «Customer Service Hotline»).
- **`95720`:** het uitgaande nummer waarmee ZTO-koeriers je bellen (`中通业务员专属外呼号码`).
- **Online klantenservice (`在线客服`):** op `zto.com`, plus de WeChat-miniprogramma's en de app van ZTO.

Voor je bestelling zelf neem je eerst contact op met de verkoper, want de verkoper boekte de zending bij ZTO.

## Een ZTO Express-pakket volgen met Peek

Plak het nummer in [Peek](/): het volgt meer dan 4.500 vervoerders. Aan het nummer alleen ziet Peek niet meteen dat het ZTO Express is. Het vraagt eerst andere vervoerders met dezelfde vorm (FedEx, DHL, BRT en Colis Privé bij 12 cijfers, nooit ZTO; DPD, SEUR en BRT bij 14 cijfers; ZTO International eerst bij `771`), daarna trackingdiensten («Checking tracking services…»). Als die het pakket vinden en ZTO Express noemen, zet Peek het onder ZTO Express en raadpleegt het ZTO bij de volgende controle. Voor 14 cijfers beginnend met `73` of `79` opent Peek daarna ook zelf de ZTO-tracker in een browser. Om ZTO meteen vanaf het begin te gebruiken tik je op «Change» en kies je ZTO Express (of zoek je op `ZTO` of `中通快递`; alleen `中通` toont ook ZTO International), of gebruik je «Change carrier» bij een bestaand pakket. Peek accepteert voor ZTO alleen 12 cijfers of 14 cijfers beginnend met `7`.

Peek leest de eigen tracker van ZTO en opent een browser als ZTO om een puzzelcontrole vraagt. Voor elke scan zie je de stap (tabel hierboven), de plaats van de ZTO-vestiging (of de Chinese naam als die niet te plaatsen is) en het tijdstip in de tijdzone van jouw apparaat. Peek laat de volledige regel van ZTO, telefoonnummers van bezorgers en naam en adres van de ontvanger weg en toont geen verwachte bezorgdatum. De openbare tracker van ZTO kan de vroegste scans weglaten, waardoor die ook in Peek ontbreken. Peek volgt alleen het ZTO-nummer: een nummer van een internationale expediteur voeg je apart toe.

Zolang je het pakket open hebt of op de bezorgdag met meldingen aan controleert Peek tot elke 10 minuten, en op de dag van «Out for delivery» vanaf 06:00 lokale tijd tot elke 2 minuten (elke 5 minuten als een browser nodig was) zolang ZTO's eigen tracker antwoordt (via trackingdiensten elke 15 minuten); anders en na twee dagen zonder nieuws (12 uur bij «Label created» of «Received by carrier») elk uur. Meldingen stel je in op «Every scan», «Important steps» of «Delivery only». Peek kan geen contact opnemen met ZTO, de expediteur of de verkoper.

## Veelgestelde vragen over ZTO Express

### Is ZTO Express (中通快递) betrouwbaar?

Ja. ZTO Express werd op 8 mei 2002 opgericht in Shanghai, is beursgenoteerd in New York (27 oktober 2016) en Hongkong (29 september 2020), verwerkte 38,52 miljard pakketten in 2025 en heeft meer dan 31.000 vestigingen die ruim 99% van de Chinese districten dekken.

### Wat is het verschil tussen ZTO Express en ZTO International?

Eén groep, twee diensten en twee trackers: ZTO Express bezorgt binnen het Chinese vasteland (niet Hongkong, Macau of Taiwan) met 12-cijferige of 14-cijferige (`7…`) nummers op `zto.com/check`, terwijl ZTO International grensoverschrijdende zendingen vervoert met 14-cijferige `771`-nummers op `ztoglobal.com`.

### Hoe lang duurt levering met ZTO Express?

Als richtlijn noemt de FAQ van ZTO de volgende dag binnen een provincie en tussen Jiangsu, Zhejiang, Shanghai en Anhui, 3 tot 5 dagen tussen andere provincies en 5 tot 7 dagen naar afgelegen gebieden. Gaat je bestelling eerst naar een expediteur, tel daar dan de internationale levertijd van de expediteur bij op.

### Wat betekenen 已签收 en 已代收 bij ZTO Express?

`已签收` («Signed for», bezorgd) betekent dat jij, iemand namens jou of het magazijn van een expediteur heeft getekend — volg het vervolgtraject dan met het nummer van de expediteur. `已代收` («Held at a collection point for the recipient», klaar om af te halen) betekent dat een pakketstation (`驿站`) of kluis het bewaart.

### Wat is het telefoonnummer van de klantenservice van ZTO Express?

Op het Chinese vasteland bel je `95311` (08:30 tot 17:30 Chinese tijd) of gebruik je de online klantenservice (`在线客服`) op `zto.com` en het WeChat-miniprogramma van ZTO. Oproepen van `95720` komen van ZTO-koeriers; neem voor terugbetalingen of vermiste bestellingen eerst contact op met de verkoper.

:::sources
- [ZTO Express: 运单查询 (vrachtbrief zoeken, in het Chinees)](https://www.zto.com/check) – Chinese tracker, tot 10 nummers, WeChat-updates
- [ZTO Express: 关于中通 (over ZTO, in het Chinees)](https://www.zto.com/introduce/about/home) – oprichting, beursnoteringen, 38,52 miljard pakketten in 2025 en netwerk
- [ZTO Express: 首页 (startpagina, in het Chinees)](https://www.zto.com/) – 95311, 95720, app en WeChat
- [ZTO Express: Home (in het Engels)](https://en.zto.com/) – «Customer Service Hotline: 95311»
- [ZTO Express: 联系我们 (contact, in het Chinees)](https://www.zto.com/introduce/about/contact) – online klantenservice
- [ZTO Express: 常见问题 (FAQ, in het Chinees)](https://www.zto.com/after/answers) – openingstijden, pakketkluizen, geen bezorgkosten, adreswijziging en levertijden
- [ZTO Express: 服务网点查询 (vestigingen zoeken, in het Chinees)](https://www.zto.com/service/station) – filiaal bij adres vinden
- [ZTO Express: 投诉反馈 (klachten, in het Chinees)](https://www.zto.com/after/complaint/problemTypes) – klachtenformulier na inloggen
- [ZTO Express: 快递服务协议 (serviceovereenkomst, in het Chinees)](https://mtoc.zto.com/agreement/service-protocol-pc.html) – dekking, bezorgwijzen, video bij openen, retouren, vergoeding en claimtermijn van 30 dagen
- [ZTO Express: 中通国际 (ZTO International, in het Chinees)](https://www.zto.com/introduce/service/international) – internationale tak sinds 2014
- [ZTO Express: 中通集运 (consolidatie, in het Chinees)](https://www.zto.com/introduce/service/concentrated) – internationale bundeling en contact
- [Ministerie van Transport van China: 快递市场管理办法 (in het Chinees)](https://xxgk.mot.gov.cn/gz/202401/t20240104_3980684.html) – toestemming voor kluis/station, controle bij levering en beroep
- [Universal Parcel Scraper: ZTO Express (in het Engels)](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/zto) – nummervormen, stapnamen, wat Peek toont en weglaat, puzzelcontrole en browserherkenning van 14-cijferige nummers beginnend met 73 of 79
:::
