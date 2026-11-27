---
title: CNE Express praćenje pošiljke: 3A5V broj, statusi i pomoć
description: Pratite CNE Express (递一物流) paket iz Kine: 3A5V format broja, značenje statusa Arrived at destination i carine, lokalni dostavljači, rokovi i kontakt.
slug: cne-express-pracenje-posiljke
published: 2026-11-27
updated: 2026-11-27
---

Pratite paket CNE Expressa (递一物流, CNE EXPRESS CO LTD, osnovan 2003. u Šangaju) iz Kine tako da zalijepite broj formata `3A5V` + 9 znamenki u polje iznad ili na [englesku stranicu za praćenje CNE-a](https://www.cne.com/en/track). CNE prevozi međunarodne narudžbe za prodavače na eBayu, Walmartu, TikToku, Temuu i SHEIN-u te završnu dostavu predaje lokalnim partnerima poput USPS-a, Royal Maila, DHL-a, La Postea, Evrija, GLS-a ili DPD-a — provjerite `Last mile number` pod `Shipment Information` na CNE-ovoj stranici ili prepustite Peeku da automatski nastavi praćenje prepoznatih predaja.

## CNE Express broj za praćenje: kako izgleda

CNE ne objavljuje službeni format broja. Oblik koji Peek prepoznaje kao CNE-ov (s izmišljenim znamenkama):

| Broj | Što predstavlja |
| --- | --- |
| `3A5V482910573` | `3A5V` i 9 znamenki: broj za praćenje CNE Expressa. |
| `3A5V48291057` | Samo 8 znamenki: nije format CNE-a. Peek ne navodi CNE, već ga provjerava kod Colis Privéa (čijim 12-znakovnim brojevima odgovara) i na servisima za praćenje. Provjerite nedostaje li znamenka. |

Mala slova, razmaci i crtice nisu važni u Peeku. CNE-ov sustav prihvaća više brojeva odjednom, odvojenih razmacima ili zarezima.

**Gdje ga pronaći.** U e-poruci o otpremi od prodavača ili na stranici narudžbe. Ako ga ne nalazite, pogledajte [kako pronaći broj za praćenje](guide:find-tracking-number) (na engleskom), ili vodiče za [Temu narudžbe](guide:temu-orders) i [SHEIN narudžbe](guide:shein-orders) (na engleskom).

**Ostali brojevi.** Pod `Shipment Information` CNE-ova stranica može prikazati i prodavačev `Order number` te `Last mile number`: broj lokalnog dostavljača za vaš paket zajedno s njegovom web-stranicom i telefonom.

**Poštanski paketi CNE-a.** CNE također prodaje vlastite usluge China Posta kao što su International E-packet i China Post Packet. Za broj u formatu China Posta pogledajte [China Post](carrier:china-post).

## Značenje statusa praćenja CNE Expressa

CNE-ova stranica iznad pojedinačnih redaka prikazuje sažeti status. Na engleskom, kako ih CNE označava:

| CNE status | Što to znači za vas | Što prikazuje Peek |
| --- | --- | --- |
| `Shipping` | Na putu. | status najnovijeg retka |
| `Customs inspection` | Zadržano na carini (`扣关` na kineskoj stranici CNE-a). | "At customs" |
| `Delivered` | Isporučeno. | "Delivered" |
| `Invalid address`, `Lost`, `Exception`, `Destroyed` | Pogrešna adresa, izgubljen ili uništen paket, ili drugi problem. | "Needs attention" |
| `Returned` | Poslano natrag prodavaču. | "Returned to sender" |
| `Not delivered` | Kineska stranica CNE-a piše `未发送` («nije poslano»): paket još nije krenuo. | status najnovijeg retka |

Mogu se pojaviti i `Time out` te `Unknown`, za koje se Peek vodi pojedinačnim recima. Svaki redak daje vrijeme, CNE-ovu formulaciju i mjesto. Najčešći reci redoslijedom kojim se pojavljuju:

| Što CNE piše | Što to znači za vas | Što prikazuje Peek |
| --- | --- | --- |
| `Parcel Infomation Received` | Prodavač je CNE-u poslao podatke o paketu (tipfeler je CNE-ov). CNE još nema paket. | "Label created" |
| `Picked Up by Courier` | CNE je preuzeo paket od prodavača u Kini. | "Received by carrier" |
| `Arrived at CNE Facility`, `Inbound scan`, `Shipment Processed`, `Shipment Loaded` | U CNE-ovom objektu: skenirano, sortirano, utovareno. | "In transit" |
| `Departed CNE Facility`, `Departure Scan`, `Depart From Local Facility` | Napustilo objekt. | "In transit" |
| `Arrived Export Airport`, `Released From Export Customs` | Na zračnoj luci u Kini pa kroz kinesku izvoznu carinu. To nije carina vaše zemlje. | "In transit" |
| `Shipment Departed From Original Country` | Let je poletio iz Kine. | "In transit" |
| `Arrived At Transit Airport`, `Shipment Depart From Transit Airport` | Usputno presjedanje. | "In transit" |
| `Arrived at Import Airport`, `Arrived at destination` | Sletjelo u vašu zemlju. Sortiranje i carina tek slijede: paket još nije u lokalnom dostavnom centru. | "In transit" |
| `The shipment is picked up by customs broker` | Carinski posrednik preuzeo ga je za uvozno carinjenje. | "At customs" |
| `Released From Import Customs`, `Pickup from airport` | Prošlo uvoznu carinu i preuzeto iz zračne luke. | "In transit" |
| `Handled by local carrier` | CNE ga je predao prijevozniku koji dostavlja u vašoj zemlji. Slijedi prijem tog prijevoznika. | "In transit" |
| `Arrived At The Delivery Company`, `Your shipment has arrived at sorting center`, `Your shipment is in process at sorting center`, `Parcel Unloaded From Container` | Kod lokalnog dostavljača ili u sortirnom centru prije njega. | "In transit" |
| `Your shipment has been processed at sorting center and ready for shipping`, `Your shipment has departed from the transit origin`, `Your shipment has arrived at the transit destination` | Sortirano u vašoj zemlji i poslano prema lokalnoj postaji dostavljača prije izlaska na dostavu. «Transit» ovdje označava taj zadnji dio, ne zaustavljanje u inozemstvu. | "In transit" |
| `On Board with Courier` | U vozilu kurira: na dostavi. | "Out for delivery" |
| `Your shipment delivery attempt failed [Recipient not at home]` | Kurir nije zatekao nikoga kod kuće. Dostava se ponavlja. | "Delivery attempted", ili "Needs attention" dok je status `Exception` |
| `Sorting failed []` | Sortiranje nije uspjelo. Prazne zagrade potječu od CNE-a. | "Needs attention" |
| `It was returned to customer's address by CNE` | CNE ga je vratio prodavaču, svom klijentu. | "Returned to sender" |

Nakon predaje u CNE-ovoj povijesti pojavljuju se i reci lokalnog dostavljača, često kao kratka oznaka s objašnjenjem. Peek čita oznaku, a za nepoznatu formulaciju uzima status koji je CNE pridružio tom retku. CNE-ov sustav ispisuje vremena bez vremenske zone i ne koriste sva istu zonu (vidi pitanja dolje).

## Tko dostavlja CNE Express pakete

CNE vodi vlastite linije prema Europi, SAD-u, Kanadi, Australiji, Brazilu i drugim zemljama te bira lokalne partnere za dostavu. Stranica s partnerima navodi USPS, Royal Mail, Austrian Post, Correos, Poste Italiane, GLS, La Poste, DHL, Evri, DPD i POST Luxembourg među svojim globalnim logističkim partnerima.

Peek čita tri koda koje CNE dodjeljuje lokalnim dostavljačima: DHL u Njemačkoj ([DHL Paket](carrier:dhl)), [Royal Mail](carrier:royal-mail) u Ujedinjenom Kraljevstvu i [USPS](carrier:usps) u SAD-u. Kad CNE navede jednoga od njih s njegovim vlastitim brojem, kartica u Peeku prikazuje to (npr. "Delivery with USPS") i taj broj. Dok lokalni prijevoznik ima samo najavu bez vlastitog skeniranja, stoji "Announced to …, not received yet". Bez tog broja Peek ne navodi lokalnog dostavljača jer on prati paket po svom broju, ne CNE-ovom. Ostali kodovi, poput US SPX, u Peeku ne imenuju dostavljača.

CNE ne objavljuje pravila o pokušajima dostave, obavijestima ni preuzimnim mjestima: vrijede pravila lokalnog dostavljača, kao što su [Evri](carrier:evri), [La Poste / Colissimo](carrier:la-poste), [Correos](carrier:correos-spain), [Poste Italiane](carrier:poste-italiane), [Austrian Post](carrier:austrian-post) ili [POST Luxembourg](carrier:post-luxembourg).

**Carina u EU-u.** Na robu kupljenu online izvan EU-a od 1. srpnja 2026. primjenjuje se carina od 3 € po stavci na pakete vrijednosti do 150 €, a od 1. studenoga 2026. i naknada Unije za obradu od 2 € po stavci bez obzira na vrijednost. Jednu stavku čine proizvodi s istom tarifnom oznakom, opisom i, gdje je potrebno, podrijetlom. U PDV zoni EU-a naplaćuje se i uvozni PDV ako nije plaćen pri kupnji putem IOSS-a. CNE je 22. listopada 2026. obavijestio prodavače da na EU linijama od 26. listopada unaprijed naplaćuje naknadu od 2 € od prodavača po tarifnoj oznaci u svakom paketu (povrh 3 € carine koju jednako naplaćuje od srpnja) te carini prema deklaraciji prodavača. Obavijest ne spominje PDV ni eventualne naknade lokalnog dostavljača. Više u [paketi na carini](guide:customs) (na engleskom).

**Kašnjenja.** CNE objavljuje obavijesti o zastojima na linijama (štrajkovi, loše vrijeme u inozemstvu) na svojoj kineskoj stranici s novostima (`新闻公告`).

## CNE Express paket se ne ažurira, izgubljen je ili vraćen

Klijenti CNE-a su prodavači koji su ugovorili prijevoz: ako paket kasni, izgubi se, ošteti ili vrati, najprije kontaktirajte prodavača ili platformu.

**Još nema rezultata.** Ako CNE-ov sustav ništa ne nađe, još nema zapis o broju: Peek prikazuje "Waiting for CNE Express" i nastavlja provjeravati. Ako ga CNE ne prepozna ni nakon dva dana i nijedan servis ga ne nađe, Peek poručuje: "After two days, the carrier still doesn’t know this number. Check it, choose another carrier or delete the parcel." `Not delivered` znači da paket još nije otpremljen (`未发送`); Peek tada prikazuje status najnovijeg retka, npr. "Label created" za `Parcel Infomation Received`. Ako se ništa ne promijeni nekoliko dana, provjerite broj kod prodavača.

**Danima bez novosti.** Kad paket na putu nema novi redak četiri dana, Peek ga označava datumom zadnjeg retka ("No tracking update since Mon 12 Oct") i napominje da paketi često miruju na dugim letovima, na carini ili vikendom; nakon tri tjedna bez novosti savjetuje da pitate prodavača. Dok prati paket na CNE-u, napomena dodaje poveznicu "Get help from CNE Express" na CNE-ovu stranicu za kontakt. Pogledajte [zašto se praćenje ne ažurira](guide:tracking-not-updating) (na engleskom).

**Na carini.** `Customs inspection` (`扣关`) ili `The shipment is picked up by customs broker` znači da je paket kod carine. CNE ga carini po podacima prodavača, pa ako se zadrži, pitajte prvo prodavača.

**Izgubljen, uništen, pogrešna adresa ili vraćen.** Kod statusa `Lost`, `Destroyed` ili `Invalid address` odmah obavijestite prodavača i zatražite povrat novca ili novi paket. `Returned` ili `It was returned to customer's address by CNE` znači da se paket vratio prodavaču: zatražite ponovno slanje ili povrat.

**Označeno kao isporučeno, ali paketa nema.** Na CNE-ovom sustavu redak s `View POD` sadrži dokaz dostave (npr. fotografiju), za čiji prikaz CNE traži ime primatelja ili poštanski broj. Provjerite okolinu i susjede, pa se javite prodavaču. Više u [isporučeno, ali nije primljeno](guide:delivered-not-received) (na engleskom).

## CNE Express korisnička služba i kontakt

Na CNE-ovoj [stranici za kontakt](https://www.cne.com/en/about/contact):

- **Dežurna linija:** `400-021-5600` (kineski servisni broj, National Service Hotline).
- **Sjedište u Šangaju:** `021-5109 6677`, 15/F, Block G, Lane 1777, Hualong Road, Qingpu District, Shanghai.
- **Radno vrijeme:** 08:30 do 18:00, od ponedjeljka do nedjelje. CNE ne navodi vremensku zonu; sortirni centri rade neprekidno.
- **Online obrazac:** obrazac za upite na istoj stranici. Odaberite `Complaint` kao vrstu te unesite telefon, e-poštu, ime, broj za praćenje i poruku.

CNE ne navodi adresu e-pošte za podršku; ostali uredi diljem Kine služe prodavačima. Za svoju narudžbu uvijek prvo pitajte prodavača.

## Praćenje CNE Express paketa u Peeku

[Peek](/) odmah prepoznaje CNE Express iz broja `3A5V` ("Detected carrier"). Poveznica za praćenje na cne.com s `?no=` svrstava paket pod CNE Express bez obzira na oblik broja; ako poveznica sadrži više brojeva, Peek uzima prvi. Za bilo koji drugi CNE broj zalijepite ga svejedno (Peek prati više od 4 500 prijevoznika). Kad oblik odgovara nekolicini prijevoznika, Peek prikazuje "Checking tracking services…" dok provjerava; kad ne odgovara nijednom, traži ga nakon dodavanja. Ako Peek ne odabere CNE, dodirnite "Change" i odaberite CNE Express pod "All carriers" (pretraga `CNE` ili `递一物流` također ga pronalazi) ili upotrijebite "Change carrier" na već dodanom paketu.

Peek čita vlastito praćenje CNE-a: status, svaki redak s mjestom, zemlju odredišta te lokalnog dostavljača kad CNE navede jednoga od triju gore navedenih s brojem. Redak `Arrived at destination` i CNE-ov redak predaje lokalnom prijevozniku čita po pekinškom vremenu, za ostale redke određuje sat prema mjestu ili dionici, i sva vremena prikazuje u vremenskoj zoni vašeg uređaja. Izostavlja prodavačev broj narudžbe, web i telefon lokalnog dostavljača te CNE-ov dokaz dostave. CNE ne daje očekivani datum dostave ni težinu.

Kad CNE navede lokalnog dostavljača i njegov broj, Peek pita i tog dostavljača: jednoga od triju gore navedenih, poštu vaše zemlje za međunarodni poštanski broj (dva slova, devet znamenki, dva slova), ili jedinog prijevoznika na kojeg taj oblik upućuje. Čim taj prijevoznik prikaže skeniranje barem jednako novo kao zadnje kod CNE-a, Peek nastavlja pratiti paket ondje uz zadržavanje CNE-ove povijesti.

Dok je paket otvoren, ili na dan dostave s uključenim obavijestima, Peek provjerava do svakih 10 minuta, a od 6:00 po lokalnom vremenu na dan kad CNE skenira paket na dostavu do svake 2 minute, dok CNE-ov sustav odgovara (putem servisa za praćenje do svakih 15 minuta); inače svakih sat vremena. Kad drugi prijevoznik preuzme dostavu, Peek slijedi njegov ritam. Nakon dva dana bez novosti (12 sati u "Label created" ili "Received by carrier") provjerava svakih sat vremena. Može vas obavještavati za "Every scan", "Important steps" ili "Delivery only", ali ne može kontaktirati CNE ili prodavača umjesto vas.

## Pitanja o CNE Express praćenju

### Je li CNE Express (递一物流) legitimna tvrtka?

Da. CNE EXPRESS CO LTD (递一国际物流有限公司), osnovan 2003. sa sjedištem u Šangaju, vodi sortirne centre u Jiaxingu i Dongguanu s kapacitetom od preko 2 milijuna pošiljaka dnevno od 2020. i otprema za prodavače na Amazonu, eBayu, Walmartu, TikToku, Temuu i SHEIN-u.

### Tko dostavlja CNE Express pakete i gdje je lokalni broj za praćenje?

CNE predaje završnu dostavu lokalnom poštanskom ili kurirskom partneru — partnerska stranica navodi USPS, Royal Mail, DHL, La Poste, Correos, Poste Italiane, Austrian Post, GLS, DPD, Evri i POST Luxembourg. Potražite `Last mile number` pod `Shipment Information` na CNE-ovom praćenju (Peek automatski nastavlja praćenje za DHL Njemačka, Royal Mail, USPS i valjane odredišne pošte).

### Što znači «Arrived at destination» na CNE Express praćenju?

Vaš je paket sletio u vašu zemlju, a ne u lokalni dostavni ured — sortiranje i uvozno carinjenje tek prethode predaji lokalnom dostavljaču. CNE taj zapis bilježi po pekinškom vremenu dok lokalni dostavljač može koristiti lokalno vrijeme, pa satnice mogu izgledati izvan redoslijeda.

### Koliko dugo traje dostava CNE Expressom?

CNE-ove stranice usluga navode referentne rokove od 3 do 8 radnih dana za CNE Priority Express (u SAD, UK, Francusku i Njemačku) i 5 do 20 radnih dana za CNE Standard Express; vodite se procjenom dostave koju daje trgovina.

### Kako kontaktirati CNE Express zbog zaglavljenog ili izgubljenog paketa?

Prvo pitajte prodavača jer prodavač ima ugovor s CNE-om. Za izravni kontakt nazovite kinesku liniju `400-021-5600` (ili ured u Šangaju `021-5109 6677`, pon–ned 08:30–18:00) ili pošaljite obrazac na [cne.com/en/about/contact](https://www.cne.com/en/about/contact) pod `Complaint`.

:::sources
- [CNE: Track your parcel](https://www.cne.com/en/track) – tracker, status labels, lines, Shipment Information, View POD
- [CNE: 运单追踪 (tracking, Chinese)](https://www.cne.com/track) – 未发送 and 扣关 behind the English labels
- [CNE: Home](https://www.cne.com/en) – 2003, Shanghai, local partners abroad, destinations, platforms, hotline
- [CNE: 首页 (home, Chinese)](https://www.cne.com/) – 递一物流, 递一国际物流有限公司
- [CNE: About us](https://www.cne.com/en/about/intro) – sorting centres, capacity of over 2 million shipments a day since 2020, platforms
- [CNE: Partner](https://www.cne.com/en/about/partner) – global logistics partners
- [CNE: Contact us](https://www.cne.com/en/about/contact) – hotline, Shanghai office, hours, consulting form
- [CNE: Postal Parcel](https://www.cne.com/en/solutions/economyParcel) – China Post's services sold by CNE
- [CNE: CNE Priority Express](https://www.cne.com/en/solutions/product/36024) – 3 to 8 business days, routes
- [CNE: CNE Standard Express](https://www.cne.com/en/solutions/product/36025) – 5 to 20 business days
- [CNE: 新闻公告 (news and notices)](https://www.cne.com/news/notice) – delay notices by destination
- [CNE: 关于欧盟国家2026年11月税改通知 (EU tax change notice)](https://www.cne.com/news/article/rDe4ANa6KYOW) – €2 collected from sellers in advance from 26 October, on top of the €3, per parcel and tariff code; customs cleared on the seller's declaration
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – €3 duty since 1 July 2026, €2 Union handling fee since 1 November 2026
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – €2 per item whatever the value, what an item is
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – number shape and links, wordings and codes (last-mile sorting, station and missed-delivery lines since 9 November 2026), last-mile suppliers, clocks, hand-over, what Peek reads
:::
