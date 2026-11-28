---
title: Praćenje Shouwu: SWECN…YQ brojevi, statusi i dostava
description: Pratite Shouwu International (守务国际) paket iz Kine: kako izgleda broj SWECN…YQ, što znače njegovi statusi i tko ga dostavlja u Ujedinjenom Kraljevstvu i SAD-u.
slug: shouwu-tracking
published: 2026-11-28
updated: 2026-11-28
---

Shouwu International (守务国际, također SWE) logistička je tvrtka iz Šangaja koja otprema pakete internetskih prodavača u Kini u Europu i Sjedinjene Američke Države, gdje ih dostavlja lokalna pošta ili kurirska služba. Ako je trgovina poslala vašu narudžbu putem Shouwua, njezin broj počinje sa SWECN i završava s YQ: zalijepite ga u polje iznad ili u Shouwuov alat za praćenje na adresi [admin.shshouwu.com](https://admin.shshouwu.com/track.html). Trgovina vam taj broj šalje uz podatke o otpremi vaše narudžbe.

## Broj za praćenje Shouwu: kako izgleda

Shouwuovi brojevi tovarnih listova sastoje se od SWECN, dvanaest znamenki i YQ, ukupno 19 znakova. S izmišljenim znamenkama:

| Broj | Što je to |
| --- | --- |
| `SWECN482910573649YQ` | SWECN, dvanaest znamenki i YQ: Shouwuov tovarni list. |
| `SWECN48291057364YQ` | Samo jedanaest znamenki: nije Shouwuov format. Provjerite nedostaje li znamenka. |
| `JSEAA4829105736YQ` | Također završava na YQ, ali počinje s JSE: broj službe [JS Express](carrier:js-express). |

I drugi kineski špediteri završavaju svoje brojeve tovarnih listova s YQ, sa svojim slovima na početku i, u Peekovu katalogu, uglavnom s deset znamenki. SWECN i dvanaest znamenki označavaju Shouwu. Razmaci, crtice i mala slova u Peeku nisu važni.

**Gdje ga pronaći.** Ako ga nema u e-poruci trgovine o otpremi ili na stranici vaše narudžbe, pogledajte [kako pronaći broj za praćenje (na engleskom)](guide:find-tracking-number).

**Umjesto toga možete dobiti broj kurirske službe.** Shouwu dopušta prodavaču da odabere koji će broj proslijediti: Shouwuov vlastiti, koji prati paket od početka do kraja, ili broj kurirske službe koja dostavlja u vašoj zemlji. Tako vam Shouwuov paket može stići pod brojem službe [Royal Mail](carrier:royal-mail), Yodel ili [Evri](carrier:evri) u Ujedinjenom Kraljevstvu, odnosno [USPS](carrier:usps) u SAD-u. Svejedno ga zalijepite: Peek prati više od 4.500 prijevoznika.

**Poveznica za praćenje.** Shouwuov gumb „Copy Link“ daje poveznicu na njegov alat za praćenje s `?q=` i brojem. Ona otvara taj paket, a u Peek možete zalijepiti cijelu poveznicu. Shouwuov alat prima do 40 brojeva odjednom, po jedan u svakom retku.

## Objašnjenje statusa praćenja Shouwu

Shouwuov alat za praćenje na engleskom je jeziku pod kineskim naslovom. Za svaki paket daje status i odredišnu zemlju, a zatim vrijeme i tekst svakog skeniranja, ali bez mjesta.

| Shouwuov status | Što znači za vas | Peek prikazuje |
| --- | --- | --- |
| `Not Found` | Shouwu još nema ništa pod tim brojem: „We seem can't identify the number.“ | „Waiting for Shouwu International“ |
| `Preparing` | Shouwu to objašnjava kao paket predan njegovu skladištu u Kini ili paket koji se ondje važe i pečati. Može se pojaviti prije prvog skeniranja. | „Label created“, a zatim „Received by carrier“ nakon preuzimanja od strane partnera ili skeniranja u skladištu |
| `In Transit` | Sve od predaje prijevozniku do carine i putovanja unutar vaše zemlje. | „In transit“ ili fazu najnovijeg skeniranja, kao što je „At customs“; „Label created“ dok postoje samo podaci o narudžbi i `Carrier received the shipment` |
| `Undelivered` | Pokušaj dostave nije uspio. | „Delivery attempted“, odnosno „Ready for pickup“ ili „Returned to sender“ ako najnovije skeniranje tako navodi |
| `Delivered` | Dostavljeno primatelju. | „Delivered“ |

Shouwu naziva svoje statuse automatski procijenjenima od sustava („auto-judged by the system“) i namijenjenima samo za informativnu referencu („only for information reference use“). Skeniranja govore više:

| Shouwu navodi | Što znači za vas | Peek prikazuje |
| --- | --- | --- |
| `Shipment information received`, `Order information received. We're expecting your parcel to arrive with us.` | Prodavač je najavio pošiljku. Shouwu još nema paket. | „Label created“ |
| `Carrier received the shipment` | Shouwu to upisuje dva sata nakon podataka o narudžbi, danima prije prvog skeniranja u skladištu: nitko još nema paket. | „Label created“ |
| `Picked up by Shipping Partner` | Shouwuov transportni partner preuzeo je paket od prodavača: prvo skeniranje nakon podataka o narudžbi. | „Received by carrier“ |
| `Arrival processing center` | Shouwuovo skladište u Kini zaprimilo je paket. | „Received by carrier“ |
| `Package processing completed` | Skladište je završilo obradu prije leta. | „Received by carrier“ |
| `Shipment started the customs clearance process at origin` | Započelo je izvozno carinjenje u Kini, rutinski korak prije leta. Carina ne zadržava paket. | „In transit“ |
| `The package is shipped from the transfer warehouse to the airport Export customs clearance in progress` | Poslano iz tranzitnog skladišta prema zračnoj luci, gdje je u tijeku kinesko izvozno carinjenje: to nije carina vaše zemlje. | „In transit“ |
| `Export customs declaration completed`, `Export customs clearance completed`, `Export Released` | Kina je odobrila izvoz; ništa nije zadržano. | „In transit“ |
| `The parcel has arrived at the airport and is currently being loaded onto a flight` | U polaznoj zračnoj luci nakon izvoznog carinjenja, ukrcava se na let. | „In transit“ |
| `Arrived at Shanghai Pudong Airport`, `Departed from export airport` | U zračnoj luci u Šangaju, a zatim odletjelo iz Kine. | „In transit“ |
| `The ship has already set sail` | Na morskoj liniji: brod je isplovio iz Kine nakon izvoznog carinjenja, prije dolaska u luku u inozemstvu. | „In transit“ |
| `Arrived at airport of Destination`, `Shipment arrived in the destination country` | Sletjelo u vašu zemlju. | „In transit“ |
| `NOA received` | Agent u vašoj zemlji primio je obavijest zrakoplovne tvrtke o dolasku; sljedeća je carina. | „In transit“ |
| `Shipment started the customs clearance process at destination`, `Customs clearance in progress`, `Currently undergoing customs clearance in the destination country`, `Currently processing import customs clearance`, `Customs clearance for imports` | Carina vaše zemlje carini pošiljku. | „At customs“ |
| `Custom clearance completed`, `Shipment completed the customs clearance process at destination`, `Customs clearance has been completed`, `Customs clearance in the destination country has been completed` | Carina vaše zemlje pustila je pošiljku dalje. | „In transit“ |
| `Scheduled For Delivery` | Shouwuov redak nakon uvoznog carinjenja, danima prije kurirskog skladišta i dostavne rute: dostava je planirana, ne još u tijeku. | „In transit“ |
| `Delivery to local courier`, `The goods have been deliverey to the final delivery service provider`, `The goods have been delivered to the carrier` | Predano kurirskoj službi koja dostavlja u vašoj zemlji, nije dostavljeno vama („deliverey“ je Shouwuov pravopis). | „In transit“ |
| `Your consignment has been picked up` | Kurir je preuzeo pošiljku iz Shouwuova skladišta u inozemstvu nakon carine: nije preuzimanje od prodavača (to je `Picked up by Shipping Partner` na početku) niti vaše preuzimanje. | „In transit“ |
| `Picked up` | U SAD-u je FedEx preuzeo paket iz Shouwuova skladišta nakon uvoznog carinjenja (redak navodi grad i poštanski broj). Nije preuzimanje od prodavača (`Picked up by Shipping Partner`) niti vaše preuzimanje. | „In transit“ |
| `Arrived at FedEx location`, `Departed FedEx location` | FedExova vlastita skeniranja u njegovim pogonima u SAD-u nakon što je preuzeo paket iz Shouwuova skladišta. | „In transit“ |
| `The shipment has been processed in the destination parcel center` | DHL ga je razvrstao u paketnom centru koji pokriva vašu adresu, prije dostavne rute. | „In transit“ |
| `Mis routed` | Kurir koji dostavlja u Njemačkoj poslao je paket u krivi pogon i šalje ga dalje: kašnjenje, ne zaustavljanje. Dostavna ruta i dalje može doći istog dana. | „In transit“ |
| `Out for delivery` | Na dostavnoj ruti lokalnog kurira. | „Out for delivery“ |
| `Abnormal delivery` | Dostava u ruti nije uspjela. Kurirov razlog slijedi kao zasebno skeniranje u uglatim zagradama, poput `<Incorrect address.>`. | „Delivery attempted“ |
| `The recipient is unknown` | DHL-ova dostava u Njemačkoj nije pronašla tog primatelja na adresi; paket se vraća. | „Delivery attempted“ |
| `Shipment will be returned to origin-Bad address` | DHL šalje paket natrag jer nije mogao pronaći adresu: na putu je natrag, još nije kod pošiljatelja. Pitajte prodavača. | „Needs attention“ |
| `Delivered by post man`, `This parcel has been delivered` | Lokalna pošta ili kurir dostavio je paket. | „Delivered“, kada Shouwuov status navede `Delivered` |
| `Shipment sent directly from parcel center to business customer` | DHL ga je dostavio iz svojeg paketnog centra izravno na poslovnu adresu. Ako je DHL poslao paket natrag, isti redak znači da je povrat stigao pošiljatelju. | „Delivered“, ili „Returned to sender“ nakon povrata |

Kada u Italiji dostavlja iMile, Shouwuova povijest prenosi iMileove retke sa središtem u zagradama 【】 (na vašem paketu može stajati drugo središte umjesto Milan center ili ROM 10):

| Shouwu navodi | Što znači za vas | Peek prikazuje |
| --- | --- | --- |
| `【Milan center】Received.` | iMileovo središte zaprimilo je paket. | „In transit“ |
| `【Milan center】Parcel shipped to【ROM 10】.`, `【ROM 10】Arrived.` | Poslano na iMileovu dostavnu postaju, zatim stiglo ondje. | „In transit“ |
| `【ROM 10】Shipment out for delivery.` | Na iMileovoj dostavnoj ruti. | „Out for delivery“ |
| `<User not at home>`, `【ROM 10】 Back To DS.` | Vozač vas nije zatekao i paket se vratio u dostavnu postaju. | „Delivery attempted“ |
| `【ROM 10】Parcel shipped to【Milan Transit Center】.` | Nakon dva neuspjela pokušaja postaja ga vraća u iMileovo središte. | „In transit“ |
| `Back to Sender for reason: wrong recipient address or address related problem` | Nakon neuspjelih pokušaja iMile vraća paket, ovdje zbog adrese (iza dvotočke može stajati drugi razlog): na putu je natrag, još nije kod pošiljatelja. Pitajte prodavača. | „Needs attention“ |
| `【Milan Transit Center】Order Closed.` | iMile zatvara nalog za dostavu bez isporuke; pitajte prodavača. | „Needs attention“ |

**Kada dostavlja DPD.** Na liniji koju dostavlja [DPD](carrier:dpd-de), Shouwuova povijest nastavlja se DPD-ovim formulacijama, od kojih svaka završava točkom: `In transit.`, zatim `At parcel delivery centre.`, DPD-ovo dostavno skladište prije rute i ponovno nakon promašenog pokušaja („In transit“), te `Out for delivery.` („Out for delivery“). `Unfortunately we have not been able to deliver your parcel. Consignee address not correct.` znači neuspjelu rutu zbog netočne adrese: paket se vraća u DPD-ovo skladište, a ne pošiljatelju („Delivery attempted“).

**Retci na kineskom.** Neki paketi dobivaju i retke koje Shouwu piše na kineskom, a Peek ih prikazuje onako kako su napisani. `货物电子信息已经收到` (elektronički podaci o robi zaprimljeni) stiže s podacima o narudžbi: „Label created“. Zatim, sve „In transit“: `申报过港中` nakon šifre serije (serija je prijavljena za cestovni prijevoz prema Hong Kongu), `已到港，安排航班中` (u polaznoj luci, čeka let), `预计23号的航班` (rezervirani let, ovdje za 23. u mjesecu; s `二程` ispred za drugu dionicu), `航班已飞` i `二程航班已飞` (let, odnosno let druge dionice, poletio je) te `已落地` (sletjelo). `已落地，清关提货中` (sletjelo, carini se i preuzima od zrakoplovne tvrtke) jest „At customs“; `清关完成` (ocarinjeno) i `已交仓扫描` (skenirano na ulazu u skladište dostavnog kurira) ponovno su „In transit“.

**Vremena pomaknuta za više sati.** Shouwuov sustav bilježi lokalno vrijeme svakog skeniranja kao da je pekinško vrijeme, pa tako i skeniranja u inozemstvu, a njegov alat za praćenje to prikazuje u vašoj vremenskoj zoni. Stoga se skeniranje u Ujedinjenom Kraljevstvu ili SAD-u prikazuje satima prerano, a let izgleda satima kraći. Vodite se redoslijedom redaka. Peek određuje sat svakog skeniranja prema njegovu mjestu ili dionici na kojoj se paket nalazio i prikazuje ih u vremenskoj zoni vašeg uređaja; na peektracker.com te u aplikacijama za iPhone i Android povijest završava natpisom „Times are in your time zone:“ i njezinim nazivom.

## Tko dostavlja Shouwu pakete

Shouwu navodi da izravno surađuje sa službama USPS, Royal Mail, La Poste, DHL, DPD i Hermes. Njegove stranice linija na kineskom navode kurire po državama:

- **Ujedinjeno Kraljevstvo.** Britanske linije pokrivaju cijelo Ujedinjeno Kraljevstvo. Kuriri za završnu dostavu su Royal Mail, Yodel i Evri (ondje napisano ERVI); linija za robu s baterijama ide uglavnom putem Royal Maila.
- **Sjedinjene Američke Države.** Američke linije pokrivaju cijelu zemlju, a dostavlja uglavnom USPS; u povijestima koje je Peek pročitao ondje dostavlja i [FedEx](carrier:fedex).
- **Europska unija.** Shouwu ima linije i za Njemačku, gdje ovisno o liniji uglavnom dostavljaju [DHL](carrier:dhl), [Hermes](carrier:hermes-de) ili [DPD](carrier:dpd-de), Francusku (uglavnom [Colissimo](carrier:la-poste)), Nizozemsku (uglavnom DHL), Švedsku (uglavnom [Early Bird](carrier:early-bird)) i Italiju (ekonomska linija, 10 do 12 radnih dana, dostavljaju UniUni i [Poste Italiane](carrier:poste-italiane); u povijestima koje je Peek pročitao ondje dostavlja i [iMile](carrier:imile), s gornjim retcima). I ovdje prodavač može proslijediti kurirov broj.
- **Irska, Kanada i Australija.** Shouwu ne navodi liniju za njih i ne imenuje kurira. Pitajte prodavača tko dostavlja.

Shouwuov alat za praćenje ne daje ime ni broj kurira, osim onoga što može pisati u tekstu pojedinog skeniranja.

**Propuštene dostave.** Pokušaji, obavijesti i mjesta preuzimanja slijede pravila kurirske službe. Pod statusom `Undelivered` Shouwuov alat poručuje da odmah kontaktirate prijevoznika radi nove dostave ili preuzimanja paketa prije roka prijevoznika, inače bi mogao biti vraćen pošiljatelju („might be returned to the sender“). Shouwuovi uvjeti za britansku i američku liniju na kineskom navode da se paket koji nije moguće dostaviti ne vraća u Kinu, nego se prema zadanim pravilima uništava. U svakom slučaju, brzo reagirajte na obavijest. Pogledajte [propuštena dostava (na engleskom)](guide:missed-delivery) i [nepreuzeti paketi (na engleskom)](guide:uncollected-parcel).

## Shouwu paket ne stiže ili praćenje stoji

**`Not Found`.** Shouwu navodi četiri razloga: još nije zaprimio paket, još ga nije skenirao, broj je pogrešan ili je paket poslan toliko davno da je praćenje uklonjeno. Navodi da obrada obično traje nekoliko dana. Peek prikazuje „Waiting for Shouwu International“ i nastavlja provjeravati. Ako Shouwu ni nakon dva dana ne poznaje broj i ništa drugo nije odgovorilo za njega, Peek prikazuje „After two days, the carrier still doesn’t know this number. Check it, choose another carrier or delete the parcel.“ Provjerite broj znak po znak; ako ni nakon nekoliko dana nema ničega, pitajte prodavača.

**Zapelo na `Preparing`, `Shipment information received` ili `Carrier received the shipment`.** Shouwu još nema paket ili ga još nije otpremio iz svojeg skladišta. Pitajte prodavača kada je predan.

**Zapelo na carini.** Nakon `Shipment started the customs clearance process at destination`, `Customs clearance in progress`, `Currently undergoing customs clearance in the destination country`, `Currently processing import customs clearance`, `Customs clearance for imports` ili `已落地，清关提货中`, pošiljka čeka carinu vaše zemlje. Na EU linijama roba kupljena na internetu izvan EU-a sada podliježe carini od €3 po vrsti proizvoda za pakete vrijednosti do €150 (od 1. srpnja 2026.) i naknadi EU-a za obradu od €2 po vrsti proizvoda bez obzira na vrijednost (od 1. studenoga 2026.); Shouwuove stranice linija ne navode tko ih plaća. Ako od vas zatraže uvozne naknade, pogledajte [paketi zadržani na carini (na engleskom)](guide:customs).

**Ništa nakon `Delivery to local courier`.** Kurir ima paket, ali Shouwuov alat za praćenje ne navodi kurirov broj. Zatražite kurirov broj od prodavača i zalijepite ga u Peek. Više u [zašto praćenje prestaje ažurirati (na engleskom)](guide:tracking-not-updating) i [praćenje paketa iz Kine (na engleskom)](guide:tracking-from-china).

**Dostavljeno, ali ništa nije stiglo.** Shouwuovi uvjeti linija navode da kada kurirova stranica prikazuje dostavu koja nikad nije stigla, Shouwu pomaže istražiti slučaj, ali ništa ne isplaćuje. Stoga prvo pitajte kurira, a zatim prodavača. U SAD-u USPS prima zahtjev za potragu Missing Mail od pošiljatelja ili primatelja počevši 7 dana od slanja. Pogledajte [dostavljeno, ali nije primljeno (na engleskom)](guide:delivered-not-received).

**Izgubljeno ili oštećeno.** Prodavač je Shouwuov klijent i prema Shouwuovim uvjetima linija mora podnijeti reklamaciju u roku od 30 dana od dana kada je Shouwu otpremio paket. Za paket bez skeniranja dostave nakon što ga je zaprimilo Shouwuovo skladište, Shouwu plaća deklariranu vrijednost plus trošak dostave, najviše 200 juana po pošiljci, i ništa za štetu nastalu u prijevozu. Stoga zatražite povrat novca ili novi paket od trgovine ili internetske tržnice i nemojte čekati. Pogledajte [izgubljeni paketi (na engleskom)](guide:lost-parcel).

## Kontakt i korisnička podrška za Shouwu

Web-stranica tvrtke Shouwu, www.sedel.com, pregledana 30. listopada 2026., na kineskom je jeziku i namijenjena je prodavačima koji naručuju pošiljke. Navodi brojeve mobitela za sjedište u Šangaju i pet podružnica, poveznicu za QQ chat i WeChat kodove. Nema adrese e-pošte, kontaktnog obrasca, podrške na engleskom ni ičega za primatelje. Alat za praćenje ne sadrži nikakve podatke za kontakt.

Za svoju narudžbu pitajte prodavača: on je ugovorio pošiljku sa Shouwuom i podnosi reklamacije. Za samu dostavu obratite se kurirskoj službi u svojoj zemlji.

## Praćenje Shouwu paketa pomoću Peeka

[Peek](/) prepoznaje Shouwuov broj po njegovu obliku: SWECN, dvanaest znamenki i YQ, koji ne koristi nijedan drugi prijevoznik u njegovu katalogu, te po njemu upituje Shouwuov alat za praćenje. Nije potreban račun, poštanski broj ni broj telefona.

Peek preuzima fazu paketa iz Shouwuova statusa, kao u gornjim tablicama, i prikazuje odredišnu zemlju te svako skeniranje s njegovim mjestom, što Shouwuova stranica izostavlja. Mjesto koje sadrži znamenku, poput poštanskog broja, uklanja se. Shouwu ne daje procjenu dostave, pa Peek ne prikazuje očekivani datum dostave.

Budući da kurir nije imenovan, Peek ne prikazuje redak „Delivery with“ i ne prebacuje paket na kurira, čak ni kada povijest prenosi iMileove retke. Ako imate kurirov broj, dodajte ga u Peek kao zaseban paket.

Dok imate otvoren paket, ili na dan dostave uz uključene obavijesti, Peek provjerava najčešće svakih 10 minuta, a na dan kada paket krene u dostavu od 6 sati po lokalnom vremenu najčešće svake 2 minute, dok Shouwuov vlastiti alat odgovara; inače svaki sat. Putem servisa za praćenje provjerava svaki sat, svaka 3 sata nakon mirnog dana i najčešće svakih 15 minuta na dan dostave pod istim uvjetom. Nakon dva dana bez novosti (12 sati dok paket još prikazuje „Label created“ ili „Received by carrier“), Peek provjerava svaki sat. Može vas obavijestiti o svakom skeniranju, samo o važnim koracima (dostava, preuzimanje, carina, neuspjeli pokušaji i povrati) ili o opciji „Delivery only“. Ne može kontaktirati Shouwu ili prodavača umjesto vas.

## Pitanja o praćenju Shouwu pošiljaka

### Što je Shouwu International (守务国际)?

Logistička tvrtka iz Šangaja, 上海守务国际物流有限公司, koja šalje pakete za prekogranične internetske prodavače. Navodi da je započela 2018. godine kao špediter za zračni teret, a 2019. prešla na e-commerce linije prema Europi i SAD-u.

### Gdje mogu pratiti Shouwu paket?

U polju iznad ili na Shouwuovu engleskom alatu za praćenje na adresi admin.shshouwu.com. Unesite cijeli broj SWECN…YQ.

### Tko dostavlja Shouwu pakete u Ujedinjenom Kraljevstvu i SAD-u?

Lokalna pošta ili kurirska služba: Shouwuove stranice linija navode Royal Mail, Yodel i Evri u Ujedinjenom Kraljevstvu te uglavnom USPS u SAD-u. Za Irsku, Kanadu i Australiju ne navodi nikoga, pa pitajte prodavača.

### Koliko traje dostava putem Shouwua?

Ovisi o liniji koju je prodavač odabrao. Shouwuova referentna vremena iznose 5 do 8 radnih dana na standardnoj liniji za Ujedinjeno Kraljevstvo, 10 do 12 na ekonomskoj liniji za Ujedinjeno Kraljevstvo, 6 do 8 na standardnoj liniji za SAD i 8 do 12 na ekonomskoj liniji za SAD. U EU-u iznose 4 do 7 radnih dana za Njemačku i Francusku, 4 do 8 za Nizozemsku i Švedsku te 10 do 12 za Italiju.

### Kome da se obratim zbog nestalog Shouwu paketa?

Prodavaču od kojeg ste naručili: on je rezervirao pošiljku kod Shouwua i može podnijeti reklamaciju u roku od 30 dana od slanja. Shouwu ne objavljuje kontakt za primatelje.

:::sources
- [Shouwu International: 守务国际包裹追踪 (parcel tracking)](https://admin.shshouwu.com/track.html) – engleski alat za praćenje, 40 brojeva, statusi i njihova objašnjenja, skeniranja bez mjesta, „Copy Link“, „Not Found“
- [Shouwu International: 首页 (home page)](http://www.sedel.com/) – popis kontakata, QQ chat, naziv tvrtke
- [Shouwu International: 关于我们 (about us)](http://www.sedel.com/about-us.html) – 2018. i 2019., izravni partneri
- [Shouwu International: 主营线路 (main lines)](http://www.sedel.com/main-route.html) – linije po državama
- [Shouwu International: 英国标快 (UK standard express)](http://www.sedel.com/main-route-secondary.html?groupId=1&groupItemId=2) – Royal Mail, Yodel, ERVI, izbor broja, 5 do 8 radnih dana, bez povrata u Kinu, reklamacije
- [Shouwu International: 英国经济 (UK economy)](http://www.sedel.com/main-route-secondary.html?groupId=2&groupItemId=3) – 10 do 12 radnih dana
- [Shouwu International: 英国带电 (UK, batteries)](http://www.sedel.com/main-route-secondary.html?groupId=3&groupItemId=4) – uglavnom Royal Mail
- [Shouwu International: 美国标快 (US standard express)](http://www.sedel.com/main-route-secondary.html?groupId=1&groupItemId=1) – uglavnom USPS, 6 do 8 radnih dana, bez povrata u Kinu, reklamacije
- [Shouwu International: 美国经济 (US economy)](http://www.sedel.com/main-route-secondary.html?groupId=2&groupItemId=21) – 8 do 12 radnih dana
- [Shouwu International: 德国标快 (Germany standard express)](http://www.sedel.com/main-route-secondary.html?groupId=1&groupItemId=19) – uglavnom DHL, izbor broja, 4 do 7 radnih dana, reklamacije
- [Shouwu International: 德国经济 (Germany economy)](http://www.sedel.com/main-route-secondary.html?groupId=2&groupItemId=22) – uglavnom Hermes, 4 do 7 radnih dana
- [Shouwu International: 德国带电 (Germany, batteries)](http://www.sedel.com/main-route-secondary.html?groupId=3&groupItemId=25) – uglavnom DPD, 4 do 7 radnih dana
- [Shouwu International: 法国标快 (France standard express)](http://www.sedel.com/main-route-secondary.html?groupId=1&groupItemId=20) – uglavnom Colissimo, 4 do 7 radnih dana
- [Shouwu International: 荷兰标快 (Netherlands standard express)](http://www.sedel.com/main-route-secondary.html?groupId=4&groupItemId=28) – uglavnom DHL, 4 do 8 radnih dana
- [Shouwu International: 瑞典经济 (Sweden economy)](http://www.sedel.com/main-route-secondary.html?groupId=4&groupItemId=29) – uglavnom EarlyBird, 4 do 8 radnih dana
- [Shouwu International: 意大利经济 (Italy economy)](http://www.sedel.com/main-route-secondary.html?groupId=4&groupItemId=27) – UniUni i Poste Italiane, izbor broja, 10 do 12 radnih dana
- [European Commission: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – €3 carine od 1. srpnja 2026., €2 naknade za obradu od 1. studenoga 2026.
- [USPS: Missing Mail and Lost Packages](https://www.usps.com/help/missing-mail.htm) – pošiljatelji ili primatelji, od 7 dana
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – oblik broja, alat za praćenje i poveznica, statusi, tekstovi skeniranja i faze (retci izvoznog i odredišnog carinjenja, ukrcaja u zračnoj luci i primopredaje očitani od 9. studenoga, partnerovo preuzimanje od prodavača, FedExovo preuzimanje iz američkog skladišta i isplovljavanje broda iz Kine od 14. studenoga, FedExova vlastita skeniranja i DHL-ova dostava poslovnom korisniku od 15. studenoga), iMileovi retci preneseni u Italiji (uključujući povrat pošiljatelju), DPD-ovi retci, satovi, mjesta, što Peek prikazuje
:::
