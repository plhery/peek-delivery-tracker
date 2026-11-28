---
title: UPS paki jälgimine: 1Z-numbrid, staatused ja Access Point
description: Jälgi UPS-i pakki: kuidas 1Z-jälgimisnumber välja näeb, mida Exception ja teised UPS-i staatused tähendavad, Access Pointid, tollimaksud ja kadunud pakk.
slug: ups-paki-jalgimine
published: 2026-11-27
updated: 2026-11-27
---

UPS-i (United Parcel Service) jälgimisnumbrid algavad tavaliselt tähtedega `1Z`. Paki jälgimiseks kleebi number või UPS-i jälgimislink ülalolevasse kasti; UPS-i enda jälgimisleht asub aadressil ups.com.

## UPS-i jälgimisnumber: kust selle leiab ja kuidas see välja näeb

Numbri annab paki saatnud pood, tavaliselt saatmiskinnituse e-kirjas või tellimuse lehel. UPS-i lehe ups.com jälgimislingis asub number samuti pärast `tracknum=`. Ikka ei leia? Vaata juhist [kust veel jälgimisnumbrit otsida](guide:find-tracking-number) (inglise keeles).

| Kuju (väljamõeldud) | Mis see on |
| --- | --- |
| `1Z4829173654829177` | UPS-i pakinumber: `1Z` ja 16 tähte või numbrit, kokku 18 märki. Viimane number on kontrollnumber, mis arvutatakse sellele eelneva 15 märgi põhjal. |

## Mida tähendavad UPS-i jälgimisstaatused, sealhulgas Exception

UPS-i jälgimislehel on edenemisriba (`Label Created`, `We Have Your Package`, `On the Way`, `Out for Delivery`, `Delivered`) ning selle all üksikud skaneeringud. Allpool on ingliskeelsed UPS-i skaneeringutekstid, mida Peek loeb, umbes paki liikumise järjekorras, koos staatusega, mida Peek igaühe puhul näitab.

| UPS-i skaneering | Peek näitab | Mida see tähendab |
| --- | --- | --- |
| `Shipper created a label, UPS has not received the package yet.` | Label created | Saatja on loonud sildi. UPS ei ole pakki veel kätte saanud. |
| `Drop-Off`, `Pickup Scan` | Received by carrier | Pakk on UPS-i käes: saatja viis selle teeninduspunkti või UPS võttis selle peale. Siia kuulub ka UPS-i lähteskaneering, mis võib samuti kanda teksti `Arrived at Facility`. |
| `Arrived at Facility`, `Departed from Facility`, `Processing at UPS Facility` | In transit | Pakk liigub läbi UPS-i võrgu. `Export Scan` ja `Import Scan` tähistavad piiriületust. |
| `Your package is prepared for clearance.` | At customs | Pakk läbib tollivormistust. |
| `Your package is pending release from a Government Agency. We'll notify the receiver or sender if information is needed.` | At customs | Riigiasutus hoiab pakki kinni ekspordi- ja impordiskaneeringu vahel. UPS võtab sinu või saatjaga ühendust, kui on vaja lisateavet. |
| `Your package has been released by a government agency.` | In transit | Riigiasutus on paki vabastanud. |
| `Duties or taxes are due on this package.` | At customs | Enne kättetoimetamist tuleb tasuda impordimaksud (vt allpool). |
| `Loaded on Delivery Vehicle`, `Out For Delivery Today` | Out for delivery | Pakk on kulleri autos. |
| `Delivery instructions have been provided by the receiver.` | Unchanged | Saaja on andnud UPS-ile tarnejuhised. See on teade, mitte liikumisskaneering: Peek jätab alles eelmise staatuse, näiteks „Out for delivery“. |
| `The receiver was not available at the time of the final delivery attempt.` | Delivery attempted | Viimane kättetoimetamiskatse ebaõnnestus. |
| `Delivered to UPS Access Point™`, `UPS Access Point™ possession` | Ready for pickup | Pakk ootab sind UPS Access Pointi punktis. |
| `DELIVERED` | Delivered | Kohale toimetatud või Access Pointist kätte saadud. |
| `Return to Sender` | Returned to sender | Pakk liigub tagasi saatjale. |

`Exception` on UPS-i üldstaatus ja võib tähendada pelgalt viivitust. Loe selle all olevat skaneeringut: `We're sorry this package may experience a temporary delay.` tähistab ajutist viivitust, samas kui `The company or receiver name is incorrect. This will delay delivery.` tähendab, et tarne ootab sinu andmete täpsustamist (Peek näitab „Needs attention“). Peek lähtub kõige värskemast skaneeringust, mitte üldstaatusest.

## Ebaõnnestunud UPS-i tarne ja Access Pointid

Kui kuller ei saa pakki üle anda, näitab skaneering, et saaja polnud kättesaadav või ettevõte oli suletud, ning UPS proovib uuesti. Pärast viimast katset näitab järgmine skaneering edasist sammu: UPS hoiab pakki kinni, viib selle Access Pointi või saadab tagasi saatjale. Loe lähemalt [ebaõnnestunud tarnete](guide:missed-delivery) kohta (inglise keeles).

UPS võib viia paki ka UPS Access Pointi. Kui pakk on kohal, ilmuvad skaneeringud `Delivered to UPS Access Point™` ja seejärel `UPS Access Point™ possession`, ning Peek näitab Access Pointi nime ja aadressi. Ära jäta järele minemist liiga kauaks: kui välja võtmata pakk saadetakse tagasi, ilmub skaneering `The UPS Access Point™ location has prepared the package for return to UPS or pickup by UPS.` Kui mõni Access Point suletakse, korjab UPS pakid kokku ja skaneering annab teada, et ootel on tarne teise Access Pointi. Vaata [välja võtmata pakkide](guide:uncollected-parcel) juhendit (inglise keeles).

## Tollimaksud UPS-i pakkidel

Väljastpoolt Ühendkuningriiki saadetud paki või Iirimaa puhul väljastpoolt EL-i tulnud paki eest võib enne üleandmist olla vaja maksta impordimaksud; jälgimises ilmuvad siis read `Duties or taxes are due on this package.` ja `Your package is being held until duties and taxes are paid. Awaiting customer action.` Peek näitab staatust „At customs“ koos teatega „Import duties or taxes to pay“.

Kui toll vajab selle asemel sinu maksunumbrit, on UPS-i skaneering `Importer's consumer or business Identification number is required for clearance. Awaiting information from the customer.`: tollivormistus ootab importija eraisiku- või ettevõtte tunnust ning sina kui saaja pead selle esitama. Peek näitab staatust „At customs“ koos teatega „Customs needs your tax number“.

- **Ühendkuningriik:** GOV.UK andmetel võtab kuller sinuga ühendust arvega käibemaksu, tollimaksu või kättetoimetamistasude kohta ja hoiab pakki tavaliselt umbes 3 nädalat. Tasumata jätmisel saadetakse pakk tagasi saatjale.
- **Iirimaa:** alates 1. juulist 2026 kehtib enamikule väljastpoolt EL-i saabuvatele kuni 150-eurose väärtusega pakkidele 3-eurone tollimaks tooteliigi kohta. Kui pood ei kogunud seda kassas, küsib kuller selle maksuameti Revenue teatel enne üleandmist ning lisab oma haldustasu. Alates 1. novembrist 2026 lisandub väljastpoolt EL-i veebist tellitud kaupadele väärtusest sõltumata ka EL-i 2-eurone käitlemistasu (Union handling fee) tooteliigi kohta.

Lähemalt [tollimaksude juhendis](guide:customs) (inglise keeles).

## UPS-i pakk hilineb, on kadunud või märgitud kohaletoimetatuks

Kui jälgimine ei liigu edasi, vaata [miks jälgimine ei uuene](guide:tracking-not-updating) (inglise keeles).

Kui elad Suurbritannias või Iirimaal ja ostsid kauba poest, vastutab paki sinuni jõudmise eest pood isegi siis, kui veo teeb UPS:

- **Inglismaa, Šotimaa ja Wales:** Citizens Advice märgib, et kulleriga peab suhtlema müüja, mitte sina. Kui kaup ei saabu 30 päeva jooksul pärast ostu, võid tellimuse tühistada ja raha tagasi küsida. Kui pakk jäeti kohta, millega sa ei nõustunud, ja see läks kaduma, peab olukorra lahendama müüja.
- **Iirimaa:** CCPC selgitab, et kokkulepitud kuupäeva puudumisel peab tellimus jõudma 30 päeva jooksul ostust. Kui see ei saabu, lepi ettevõttega kokku uus tarnekuupäev; kui ka see möödub, on sul õigus täielikule tagasimaksele. Teel kahjustada saanud või kadunud pakk tuleb asendada ja kaupleja vastutab seni, kuni pakk on sinu või sinu määratud isiku käes, isegi kui kuller väidab, et see on kohal.

Märgitud `DELIVERED`, aga pakki pole? UPS-i leht võib näidata, kuhu pakk jäeti (näiteks `Left at: Front Door`), ja kättetoimetamise tõendit, mida Peek ei talleta. Kontrolli ups.com lehte ja võta seejärel ühendust poega. Rohkem infot juhendites [märgitud kohaletoimetatuks, aga kätte saamata](guide:delivered-not-received) ja [kadunud pakid](guide:lost-parcel) (inglise keeles).

## UPS-i pakkide jälgimine Peekis

[Peek](/) tunneb UPS-i kohe ära õige kontrollnumbriga `1Z`-numbri järgi; vale kontrollnumbri korral lükkab UPS selle trükiveana tagasi. Töötavad ka `1Z`-numbrit sisaldavad lingid. UPS-ist otsib Peek ainult `1Z`-numbreid: muud kujud suunatakse jälgimisteenustesse.

Peek avab ups.com jälgimislehe oma serveri brauseris ja loeb vastust kuni iga 10 minuti järel ning alates kell 6.00 kohaliku aja järgi päeval, mil UPS skaneerib paki kullerile üleantuks, iga 5 minuti järel, kuni UPS-i enda jälgimine vastab (jälgimisteenuste kaudu iga 15 minuti järel). Pärast kahte uudisteta päeva (või 12 tunni möödudes, kui pakil on endiselt staatus „Label created“ või „Received by carrier“) kontrollib Peek kord tunnis. Kui UPS keeldub vastamast või loetav on ainult staatus, täidavad ajaloo jälgimisteenused. Peek näitab staatust, iga skaneeringut kaardil täpse kellaajaga, planeeritud tarnepäeva (mitte kunagi ajavahemikku), kohaletoimetamise aega ning paki ootel olles Access Pointi nime ja aadressi. Teavitused saab seada iga skaneeringu, oluliste sammude või ainult tarnepäeva kohta.

Peek jätab kõrvale allkirjastaja, sinu aadressi, kättetoimetamistõendi ja koha, kuhu pakk jäeti, ega näita tarnefotot ega kaalu. Skaneeringud ilmuvad UPS-i inglise keeles; üleandmine kannab silti „DELIVERED“. Kui UPS annab Ground Saveri paki üle [US Postal Service'ile](carrier:usps) ja lisab selle numbri, jälgib Peek pakki edasi seal, kui USPS-il tekivad uued kanded („Delivery with USPS“).

## Küsimused UPS-i paki jälgimise kohta

### Miks minu UPS-i jälgimisnumber ei kehti?

Enamasti on numbris trükiviga: üks vale märk `1Z`-numbris rikub tavaliselt kontrollnumbri ja UPS lükkab selle tagasi. Teine võimalus on, et number kuulub teisele vedajale — kontrolli, millise vedaja poe e-kiri nimetab.

### Kui kaua võtab aega, kuni UPS-i jälgimine nähtavaks muutub?

Esimene skaneering `Shipper created a label, UPS has not received the package yet.` ilmub kohe pärast seda, kui saatja on sildi loonud. Jälgimine liigub edasi alles siis, kui saatja viib paki ära või UPS võtab selle peale (`Drop-Off`, `Pickup Scan`).

### Kas ma saan Peekis UPS-i tarnet muuta?

Ei: Peek ainult loeb UPS-i jälgimisandmeid. Muudatused, mille teed otse UPS-is, ilmuvad skaneeringutesse, näiteks `The package will be delivered to the UPS Access Point™ location requested by the receiver.`

### Kas UPS-i tarne-SMS võib olla pettus?

Jah. Citizens Advice hoiatab, et sõnum, mis palub paki kättesaamiseks või tarne ümberplaneerimiseks tasuda, on tavaliselt pettus, ning kullerfirmad ei küsi makset SMS-is või e-kirjas oleva lingi kaudu. Tõelised impordimaksud ilmuvad jälgimisse tolliskaneeringutena, seega kontrolli numbrit ise lehel ups.com või Peekis, mitte lingil klõpsates. Ühendkuningriigis saab kahtlase SMS-i tasuta edastada numbrile 7726. USA-s soovitab FTC võtta vedajaga ühendust veebisaidi või telefoninumbri kaudu, mille ehtsuses oled kindel. Loe lisaks [tarnepettuse sõnumitest](guide:delivery-scam-texts) (inglise keeles).

:::sources
- [Citizens Advice: If something you ordered hasn't arrived](https://www.citizensadvice.org.uk/consumer/somethings-gone-wrong-with-a-purchase/if-something-you-ordered-hasnt-been-delivered/) – müüja vastutus, 30 päeva, kokkulepitud koht, petusõnumid (Inglismaa, Šotimaa ja Wales)
- [GOV.UK: Tax and customs for goods sent from abroad](https://www.gov.uk/goods-sent-from-abroad/tax-and-duty) – kulleri arve, umbes 3 nädalat, tagastus saatjale
- [GOV.UK: Report internet scams and phishing](https://www.gov.uk/report-suspicious-emails-websites-phishing) – petusõnumite edastamine numbrile 7726
- [CCPC: Your consumer rights if your order is not delivered](https://www.ccpc.ie/consumer-advice/consumer-rights/buying-goods/non-delivery-of-goods) – 30 päeva, uus kuupäev, tagasimakse, vastutus kuni kättesaamiseni (Iirimaa)
- [Revenue: Buying goods from outside the EU](https://www.revenue.ie/en/customs/individuals/buying-online-personal/outside-eu.aspx) – 3 eurot tooteliigi kohta alates 1. juulist 2026, tasutakse kullerile enne tarnet
- [Revenue: Buying goods online for personal use](https://www.revenue.ie/en/customs/individuals/buying-online-personal/index.aspx) – kullerite haldustasu
- [European Commission: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 2-eurone EL-i käitlemistasu alates 1. novembrist 2026
- [European Commission: The temporary EUR 3 customs duty and the Union handling fee](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 eurot tooteliigi kohta kaugmüügil olenemata kauba väärtusest
- [FTC: Fake shipping notification emails and text messages](https://consumer.ftc.gov/consumer-alerts/2023/12/fake-shipping-notification-emails-text-messages-what-you-need-know-holiday-season) – vedajaga ühenduse võtmine veebisaidi või numbri kaudu, mille ehtsuses oled kindel
- [Universal Parcel Scraper: UPS](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ups) – numbrikujud, kontrollnumber, UPS-i skaneeringute sõnastused (riigiasutuse kinnipidamine ja vabastamine, saaja tarnejuhised, importija tunnuse nõue), mida Peek UPS-ist loeb
:::
