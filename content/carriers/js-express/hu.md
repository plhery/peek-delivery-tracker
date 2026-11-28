---
title: JS Express csomagkövetés: JSE…YQ számok, státuszok
description: Kínából érkező JS Express (急速国际) csomag követése: a JSE…YQ szám formátuma, a státuszok jelentése és hogy ki kézbesít az USA-ban, Kanadában és máshol.
slug: js-express-csomagkovetes
published: 2026-11-28
updated: 2026-11-28
---

A JS Express (急速国际, más néven JS EXP) határon átnyúló webáruházak kínai logisztikai cége, amelyet a szucsoui 急创速供应链管理（苏州）有限公司 üzemeltet. Ha a webáruház JS Express-szel küldte el a rendelésedet, a csomagszám JSE-vel kezdődik és YQ-val végződik: illeszd be a fenti mezőbe. A JS Express kijuttatja a csomagot Kínából, majd az országodban egy helyi futárszolgálat kézbesíti: előzményei az Egyesült Államokban USPS, GOFO vagy SpeedX, Kanadában Straightship, az Egyesült Királyságban Yodel, Németországban DHL, iMile vagy J&T Express, Franciaországban Colissimo, Spanyolországban CTT Express, egyes vonalakon pedig FedEx szkenneléseit mutatják.

## JS Express csomagszám: hogyan néz ki

A JS Express fuvarlevelei JSE előtagból, két betűből, tíz számjegyből és YQ végződésből állnak, összesen 17 karakterből. Kitalált számjegyekkel:

| Szám | Mit jelent |
| --- | --- |
| `JSEAA4829105736YQ` | JSE, két betű, tíz számjegy és YQ: egy JS Express fuvarlevél. |
| `JSEAA482910573YQ` | Csak kilenc számjegy: nem a JS Express formátuma. Ellenőrizd, nem maradt-e ki egy számjegy. |
| `JXCAA4829105736YQ` | Szintén YQ-val végződik, de JXC-vel kezdődik: ez [Juxi Express](carrier:juxi-express) szám. |

Más kínai szállítmányozók ugyanezt az elrendezést használják saját kezdőbetűikkel: FEI a 17FEIA, WNB a WanbExpress, SFC az SFC, CJP a CJ Packet esetén. A JSE jelöli a JS Expresst. A szóközök és a kisbetűk nem számítanak a Peekben. A két betű közül a második helyére írt nullát O betűként, a tíz számjegy közé írt O betűt pedig nullaként olvassa be. A JS Express nyomkövetőjének a teljes számra van szüksége: egy részletére nem talál semmit.

**Hol találod meg.** A webáruház a feladási e-mailben vagy a rendelési oldaladon adja meg a számot. A JS Express közlése szerint szorosan együttműködik az Amazon, a Walmart, a Shopify, az eBay, az AliExpress, a Wish és a Shopee platformokkal. Ha nem találod, lásd: [hogyan találd meg a csomagszámot](guide:find-tracking-number) (angolul).

**Nincs megosztható link.** A JS Express követési oldalai űrlapban fogadják a számot, így nincs egyetlen csomagot megnyitó link: másold ki a számot. Az angol „Express Query” oldal azonnal válaszol; a kínai oldal előbb képes ellenőrző kódot (验证码) kér.

## A JS Express követési státuszainak magyarázata

A JS Express oldala minden szkennelést időponttal, 【szögletes zárójelben】 megadott hellyel és szöveggel sorol fel, a legújabbal felül („Latest”). A szövegek többnyire angol nyelvűek. A csomag által bejárt sorrendben:

| A JS Express ezt írja | Mit jelent ez neked | A Peek ezt mutatja |
| --- | --- | --- |
| `Electronic notification of order creation received.` | A webáruház elküldte a rendelés adatait a JS Expressnek. A csomag még nincs a JS Expressnél. | „Label created” |
| `PRE-ADVICE` | Egy partnernél megvannak a csomag adatai, még mielőtt a csomag a JS Express raktárába érne. | A csomag korábbi fázisa |
| `Item has been collected` | A csomag megérkezett a JS Express kínai raktárába: az első valódi szkennelés. | „Received by carrier” |
| `Item inbound in sorting center.`, `Item outbound in sorting center.` | Szortírozva a JS Express kínai központjában. | „In transit” |
| `Domestic allocation has begun`, `Under domestic allocation` | Még a JS Express kínai raktárában van, a szortírozás után és indulás előtt. | „In transit” |
| `Waybill Generated for` | Az országodban kézbesítő futár létrehozta a fuvarlevelét, miközben a csomag még a JS Express kínai raktárában van. | „Label created”, vagy a korábbi fázis, általában „In transit” |
| `Arrive at transit center` | A JS Express kínai tranzitközpontjában, szortírozás és indulás között. | „In transit” |
| `Item departed from origin.`, `Arrive at international airport to abroad` | Úton kifelé, majd a kínai repülőtéren. | „In transit” |
| `Arrived at export center`, `Exports Center Sorting and Packing`, `Arrived at Origin Port` | Egyes vonalakon az előző kettő között: egy kínai exportközpont szortírozza és csomagolja a küldeményt, majd megérkezik a Kínát elhagyó kikötőbe vagy repülőtérre. | „In transit” |
| `Shipment arrived at facility and measured.`, `Depart from facility to service provider.` | Egy kínai létesítmény leméri a csomag súlyát és méreteit, majd továbbítja a légi szállító vonalnak. | „In transit” |
| `Start on-site transportation and shipment` | A kínai repülőtéren, megérkezés után: elkezdődött a helyszíni kezelés, még a kínai kiviteli vámkezelés lezárása előtt. | „In transit” |
| `Released From Export Customs` | A kínai kiviteli vámkezelés lezárult. Nem a te országod vámja. | „In transit” |
| `DEPART ORIGIN AIRPORT`, `Departure from the original airport`, `Item Flight departure`, `Initial landing`, `Connecting flights flight takeoff` | A járat elindult, néha átszállással és csatlakozó járattal. | „In transit” |
| `ARRIVED AT DESTINATION AIRPORT`, `Arrival to the destination airport`, `Item Flight landing`, `NOA-Notification of arrival from GHA` | Megérkezett az országodba. A NOA a repülőtéri földi kiszolgáló érkezési értesítése. | „In transit” |
| `The customs clearance agent is queuing up to pick up the goods` | Az országodban dolgozó vámügynök a repülőtér árutermináljánál várakozik a küldemény átvételére: sem az ügynöknél, sem a vámnál nincs még. | „In transit” |
| `The customs clearance agent has picked up the goods` | Az ügynök felvette a küldeményt az áruterminálról import vámkezelésre. | „At customs” |
| `The goods have been picked up abroad` | Az ügynök átvette a küldeményt a repülőtérről az országodban. Nem új felvétel az eladótól. | „In transit” |
| `Customs clearance agent received the shipment in destnation country.` | A vámügynök átvette a küldeményt import vámkezelésre a repülőút után. A „destnation” a JS Express saját írásmódja. | „At customs” |
| `Inbound to customs temporary warehouse` | Vámraktárban van, import vámkezelésre vár. | „At customs” |
| `Released From Import Customs`, `Released from customs: customs cleared.` | Az országod vámhatósága továbbengedte. | „In transit” |
| `Depart from the destination distribution center.` | Az országod egyik elosztóközpontja továbbküldi a csomagot a vámkezelés után. | „In transit” |
| `In transit, it's progressing through post network` | Az országod postája továbbítja a csomagot a hálózatán, még kiszállítás előtt. | „In transit” |
| `collection by overseas warehouse` | A JS Express helyi raktára átvette a csomagot a vám után. | „In transit” |
| `Load cargo overseas`, `Vehicles depart from` | Az a raktár felrakodja a csomagot a futárnak, majd a jármű elindul a szkennelésnél megadott helyről. | „In transit” |
| `Delivery to local courier`, `Last Mile Delivery` | Átadva az országodban kézbesítő futárnak, annak első szkennelése előtt. Még nincs neked kézbesítve. | „In transit” |
| `Delivery in progress` | Egyes útvonalakon: a helyi futárnál van a csomag, miután megérkezett annak állomására, még kiszállítási kör előtt. A szavak ellenére még nincs kiszállítás alatt. | „In transit” |
| `到达待取` | A JS Express saját kínai státusza: megérkezett, átvételre vár. Helyként csak az ország szerepel. | „Ready for pickup” |
| `The delivery address is incorrect. Please ask the recipient to contact their local area?` | A JS Express saját értesítése sikertelen kézbesítés után: a cím pontatlan vagy hibás. Arra kér, hogy vedd fel a kapcsolatot a helyi irodával, amelynek telefonszámát a JS Express oldala megadja; a Peek kihagyja a telefonszámokat. | „Needs attention” |

**A futár szkennelései.** A `Delivery to local courier` után az előzmények a helyi futár szavaival folytatódnak. Az USA-ban a USPS sorai: `Accepted at USPS Origin Facility` vagy `Accepted at USPS Facility` (az első szkennelése, nem új feladás), `Out for Delivery`, `Delivered, Front Door/Porch`. A SpeedX sorai a neve nélkül jelennek meg: `Processing at Transit Facility`, `In transit to destination facility`, `Arrival at Destination Facility` és `Processing at Designated Facility`, mind „In transit”. Egy másik amerikai partner szkennelései is név nélkül érkeznek: `Arrived at Origin Hub` (az első központja, a célrepülőtér mellett), `Departed Destination Hub` és `Arrived At Warehouse` (a helyi raktára a kiszállítás előtt), mind „In transit”. Kanadában a Straightship sorai: `Package is received at a Straightship station`, majd `Package will be delivered before 9:00pm`, a sofőr aznapi köre („Out for delivery”: kézbesítés vagy kísérlet követi), és `Delivered` a város nevével zárójelben. Németországban a DHL angol sorai jelennek meg, néha ponttal a végén: `The instruction data for this shipment have been provided by the sender to DHL electronically`, az adatbejegyzése („Label created”, vagy „In transit”, ha előtte már volt szkennelés), majd `The shipment is in transit to DHL` („In transit”); kis csomagnál egy DHL levélközpontból németül `Die Sendung wurde im Briefzentrum bearbeitet.` is megjelenhet („In transit”). Az iMile futárja a `Route not completed` sorral zárhat egy kört („Delivery attempted”). A J&T Express Germany sorai németül jönnek, a helyekkel a 【】 zárójelekben: a `【】【】Ihr Sendung wurde abgeholt.` az első szkennelése, amikor a repülőút és a vám után átveszi a csomagot; ezt követi a `【】Die Sendung hat【】verlassen. Weiterversand nach【】` (elhagyott egy telephelyet a következő felé) és a `【】Die Sendung ist bei【】 angekommen.` (egy csomópontban vagy a kiszállító depóban, a kör előtt), mind „In transit”. Ahol a FedEx kézbesít, a `Shipment information sent to FedEx` a címkéje, még mielőtt a csomag a JS Express raktárába ér („Label created”); ezt `Arrived at FedEx location`, `Left FedEx origin facility` és `On the way` követi („In transit”). Magyarországon a futár `Arrived to HU HUB`, `Linehaul Transit` (a központból a szortírozóba tartó szállítás) és `Shipment arrived to sorting centre / Shipment in the sorting centre` sorai következnek, mind „In transit”. Spanyolországban a CTT Express spanyol `Enviado` sora azt jelenti, hogy átvétel után, a kiszállítási kör előtt továbbította a csomagot („In transit”).

**Név nélküli futár.** Egyes vonalakon a JS Express csupa nagybetűs sorokat továbbít egy kézbesítő futártól anélkül, hogy megnevezné a futárt vagy az országát. A `SHIPMENT RECEIVED`, `PROCESSED AT HUB` és `SHIPMENT PROCESSED` a futár központjában történő átvételt, a `DEPARTED FROM DEPOT` pedig a kiszállító depó felé való elindulást jelzi, mind „In transit”. A `RECEIVED AT LOCAL DEPOT` ez a depó minden kör előtt és sikertelen kísérlet után újra: a futár saját depója, nem csomagpont, ezért a Peek megtartja az előtte álló sor státuszát. A `DELIVERY ATTEMPTED: 1ST DELIVERY ATTEMPT`, `DELIVERY ATTEMPTED: 2ND DELIVERY ATTEMPT` és `DELIVERY ATTEMPTED: 3RD DELIVERY ATTEMPT` sikertelen kézbesítések („Delivery attempted”). A harmadiknál a futár arra kér, hogy foglalj kézbesítést; a `SHIPMENT REDIRECTED: ON CONSIGNEE'S REQUEST` ezután azt jelenti, hogy a csomag az általad egyeztetett kézbesítés felé tart („In transit”). A `DELIVERY EXCEPTION` probléma a futár központjában még kiszállítás előtt, a `DELIVERY EXCEPTION, WAIT FOR INFORMATION CONFIRMATION` az `ARRIVE AT THE WAREHOUSE` („In transit”) után pedig azt jelenti, hogy a futár adategyeztetésig visszatartja a csomagot: a Peek mindkettőt „Needs attention” státusszal mutatja. Ha a Peek nem mutat futárszámot, kérdezd meg az eladót, ki kézbesít és mire van szüksége a futárnak.

**Sorok, amelyek nem szkennelések.** A `SHIPPING INFORMATION RECEIVED`, `SHIPMENT CREATED`, `Data Received`, a USPS `Shipping Label Created, USPS Awaiting Item`, a `Package information is created in the system` és egy európai központban a `Shipment Data Uploaded`, majd `Processed` csak egy partner adatbejegyzései, néha napokkal azelőtt, hogy a csomag nála lenne. A `Consignee Contacted`, amelyet akkor rögzítenek, amikor a kézbesítő futár sikertelen kísérletet ír be, a hozzád érkező hívás vagy üzenet: ez sem változtat a státuszon.

**Összekeveredett időpontok.** Az időpontok mellett nincs időzóna, és keverik az órákat: Kínában pekingi idő, a repülőtereken és a legtöbb futárnál helyi idő, a Straightshipnél pedig UTC szerepel, ami órákkal a kanadai órák előtt jár. A sorok sorrendjére hagyatkozz, ne az órákra. A Peekben a J&T Express Germany német sorai a J&T Express Germany saját nyomkövetőjének időpontjait mutatják.

## Ki kézbesíti a JS Express csomagokat

**Egyesült Államok.** A JS Express előzményei szerint az [USPS](carrier:usps) kézbesít a USPS szkenneléseivel egészen a kézbesítésig, vagy a [GOFO](carrier:gofo) a szortírozójától a kiszállításig, illetve a [SpeedX](carrier:speedx), amelynek sorai nem említik a nevét. Amint a csomag a USPS-nél van, a USPS a feladástól számított 7 nap után elfogad Missing Mail keresési kérelmet a feladótól vagy a címzettől.

**Kanada.** Egyes JS Express csomagokat a kanadai [Straightship](carrier:straightship) kézbesít; szkennelései a JS Express előzményeiben is megjelennek.

**Németország, Franciaország és Spanyolország.** A JS Express európai vonala olyan országokat szolgál ki, mint az Egyesült Királyság, Franciaország, Németország, Olaszország, Spanyolország, Svájc és Dánia, néhány távoli irányítószám kivételével. Németországnál a JS Express a DPD-t és más utolsó mérföldes futárokat említ, és azt írja, hogy a csomagok több mint 98%-a 8 munkanapon belül megérkezik; ottani előzményei [DHL Paket](carrier:dhl), egy német központból érkező [iMile](carrier:imile) és német nyelvű [J&T Express Germany](carrier:j-and-t-de) szkenneléseket mutattak. Franciaországban francia nyelvű [Colissimo](carrier:la-poste), Spanyolországban pedig [CTT Express](carrier:ctt-express) sorokat mutatott.

**Egyesült Királyság, Írország, Ausztrália és máshol.** Egyes vonalakon az előzmények [FedEx](carrier:fedex) szkenneléseket mutatnak. Az Egyesült Királyságban a JS Express előzményeiben a Yodel sorai jelennek meg; kettő közülük, a `DESPATCHED AND ON ROUTE TO YODEL` és a `PARCEL PICKED UP`, még akkor érkezik, amikor a csomag a JS Express kínai raktárában van. Ausztráliánál a JS Express 4 kg alatt az Australia Postot és a Fastwayt — ma [Aramex Australia](carrier:aramex-au) —, afelett a DPEX-et nevezi meg. A JS Express rekordja tartalmazhatja a helyi futár számát, de a nevét nem, és a JS Express oldala egyiket sem mutatja. A Peek megnevezi a futárt, ha az a szám egyetlen, az országodban működő szolgáltatóhoz illeszkedik, [postai szám](guide:tracking-number-formats) (két betű, kilenc számjegy, két betű) esetén pedig az országod postáját kérdezi meg; lásd alább. Ha a Peek nem nevez meg futárt, kérdezd meg az eladót, ki kézbesít.

A kézbesítési kísérletek, értesítők és átvételi pontok a helyi futár szabályait követik: a JS Express nem tesz közzé ilyeneket. A `到达待取` sor csak az országot nevezi meg, ezért nézd meg a futár saját követését, vagy kérdezd meg az eladót, hol vár a csomag.

## Nem érkezik meg a JS Express csomag, vagy elakadt a követés

**„未获取到任何数据,请提供有效单号!”** A JS Express angol oldala ezt válaszolja kínaiul egy ismeretlen számra: „nincs adat, adj meg érvényes fuvarlevélszámot”. A `No track data for now!` azt jelenti, hogy még nincs szkennelés. A Peek mindkettőre „Waiting for JS Express” állapotot mutat, és tovább ellenőriz. Ha a JS Express két nap múlva sem ismeri a számot és más forrás sem válaszolt rá, a Peek ezt írja: „After two days, the carrier still doesn’t know this number. Check it, choose another carrier or delete the parcel.” Ellenőrizd a számot karakterről karakterre; ha néhány nap után sincs semmi, kérdezd meg az eladót, valóban feladta-e a csomagot.

**Elakadt az `Electronic notification of order creation received.` státuszon.** A JS Express még nem vette át a csomagot. Kérdezd meg a webáruházat, mikor adta át.

**Elakadt az `Inbound to customs temporary warehouse` státuszon.** Az országod vámkezelésére vár. Egyes útvonalakon a sorok `Start Custom clearance Process`, majd `customs clearance completed` szövegűek; egy korábbi `Under customs clearance, please wait patiently.` Kína kiviteli vámja az indulási repülőtéren, így a Peek „In transit” státusszal mutatja. A JS Express az európai vonalát és a standard amerikai vonalát megfizetett vámokkal (DDP) értékesíti: előre beszedi az importadókat a webáruháztól, így azokon a vonalakon nem kellene külön fizetned. Ha egy futár mégis díjat kér, fizetés előtt egyeztess az eladóval. Az EU-ban 2026. november 1. óta terméktípusonként 2 € kezelési díj esedékes; az eladó vagy a kézbesítő cég fizeti meg, és átháríthatja. Lásd: [vámnál visszatartott csomagok](guide:customs) (angolul); ha napokig ott marad, szólj az eladónak. Továbbiak: [miért áll le a nyomkövetés](guide:tracking-not-updating) és [Kínából érkező csomag követése](guide:tracking-from-china) (angolul).

**Semmi a `Delivery to local courier` után.** A helyi futárnál van. Ha a Peek megmutatja a futár számát, keresd meg annak saját követőjében; ha nem, kérdezd meg az eladót, ki kézbesít.

**Kézbesítve, de semmi nem jött.** Először a helyi futárt kérdezd (az USA-ban a USPS-t), majd az eladót. Amerikai vonalán a JS Express segít ellenőrizni a futár által kézbesítettnek jelzett, de meg nem érkezett csomagot, kártérítést viszont nem fizet rá, így az eladó saját szabályzata dönt. Lásd: [kézbesítve, de nem érkezett meg](guide:delivered-not-received) (angolul).

**Elveszett, megsérült vagy visszaküldték.** A JS Express ügyfele az eladó. A JS Express feltételei szerint az ügyfél a csomag JS Express általi átvételétől számított 30 napon belül írásban reklamálhat, a biztosított csomagokra pedig saját fiókjából nyújt be igényt. Kérj tehát időben visszatérítést vagy új csomagot a webáruháztól vagy a piactértől. A kézbesíthetetlen csomagot (nincs otthon senki, megtagadva, rossz cím, vám) díj ellenében visszaküldik, ha a feladó kéri, vagy megsemmisítik; amerikai vonalán a JS Express sem vissza nem viszi Kínába, sem ki nem küldi újra a visszaküldött csomagot. Egyes útvonalakon a `Refused`, majd `Returned` azt jelenti, hogy a csomagot megtagadták a kiszállításkor, és visszakerült a futár szortírozójába, még nincs az eladónál: a Peek „Needs attention” státuszt mutat. Lásd: [elveszett csomagok](guide:lost-parcel) és [visszaküldve a feladónak](guide:returned-to-sender) (angolul).

## JS Express telefonszám és ügyfélszolgálat

A JS Express kínai kapcsolati oldalán 2026. október 30-án olvasottak szerint a következő elérhetőségek szerepelnek. Az angol kapcsolati oldal régebbi, és még más számokat és címeket ad meg.

- **Forródrót**: 400-0353-518, kínai ügyfélszolgálati szám, amelyet nemzetközi kérdésekhez is megadnak. A JS Express nem közöl nyitvatartást, és nem írja le, hogy külföldről hívható-e.
- **QQ és WeChat**: vállalati QQ 4000353518 és a js-express hivatalos WeChat-fiók.
- **Panaszok**: űrlap az angol panaszoldalon, valamint ugyanez a forródrót.
- **Irodák**: központ és raktár Szucsouban, további raktárak Kína-szerte, és hálózati irodák Hongkongban és a kaliforniai Walnutban. Az amerikai iroda egyetlen telefonszáma a kínai forródrót.

A JS Express reklamációit a csomagot megrendelő eladó intézi. A rendelésed ügyében az eladót keresd, a kézbesítés ügyében a helyi futárt.

## JS Express csomag követése a Peekkel

A [Peek](/) a formája alapján felismeri a JS Express számot: JSE, két betű, tíz számjegy és YQ, amelyet a katalógusában egyetlen más fuvarozó sem használ, és ez alapján kérdezi meg a JS Express nyomkövetőjét. Ha csak a helyi futár száma van meg, illeszd be azt: a Peek több mint 4500 fuvarozót követ.

A Peek a JS Express angol oldalának előzményeit mutatja, minden szkennelést a helyével együtt, valamint a célországot, amelyet az az oldal elhagy. A `到达待取` sor „Arrived, awaiting collection” felirattal jelenik meg. A telefonszámokat törli; az átvevő aláíró nevét megtartja. A Peek elhagyja a kézbesítési fotókat és a webáruház rendelési számát, amelyet a JS Express rekordja tartalmazhat. Várható kézbesítésként a JS Express becslésének napját mutatja; amint a csomag kiszállítás alatt áll — beleértve a Straightship `Package will be delivered before 9:00pm` sorát — nem mutat napot, mivel az „Out for delivery” már a mai napot jelenti. A nem szkennelés jellegű sorok nem változtatják meg a státuszt.

A Peek a kínai sorokat pekingi idő szerint, a Straightship sorait UTC szerint, a J&T Express Germany német sorait pedig az egyes sorokban megadott időeltolódással olvassa be, a többiek óráját a helyből vagy a célországból határozza meg, és mindegyiket a készüléked időzónájában, a legújabbal elöl jeleníti meg.

Amikor a futár száma egyetlen, az országodban működő szolgáltatóhoz illeszkedik — például egy USPS vonalkód az USA-ban vagy egy Straightship szám Kanadában —, a Peek a „Delivery with” felirattal mutatja azt a futárt és a számát, és őt is megkérdezi; nemzetközi postai szám az országod postájához megy. Amíg annál a futárnál csak a csomag előzetes bejelentése van meg saját szkennelés nélkül, addig „Announced to …, not received yet” jelenik meg. Amint az a futár legalább olyan friss szkennelést mutat, mint a JS Express legutóbbija, a Peek ott követi tovább a csomagot, mindkét számot mutatva és megőrizve a JS Express szkenneléseit. Addig a JS Expressnél marad, amelynek előzményei gyakran a futár sorait is tartalmazzák.

Amíg nyitva tartod a csomagot, vagy a kézbesítés napján bekapcsolt értesítésekkel, a Peek akár 10 percenként ellenőriz, és a kiszállítás napján helyi idő szerint reggel 6:00-tól akár 2 percenként (5 percenként, ha böngészőben kell megnyitnia a JS Express nyomkövetőjét), amíg a JS Express saját nyomkövetője válaszol; egyébként óránként. Nyomkövető szolgáltatásokon keresztül óránként, egy csendes nap után 3 óránként, és a kézbesítés napján ugyanezzel a feltétellel akár 15 percenként. Ha egy másik fuvarozó átveszi a kézbesítést, a Peek a kézbesítő fuvarozó saját ütemét követi. Egy USA-ba tartó csomagnál a SpeedX számával a Peek a [SpeedX](carrier:speedx) rendszerében is tudja követni. Két nap hír nélküli időszak után (12 óra után, amíg a csomag még „Label created” vagy „Received by carrier” állapotban van) a Peek óránként ellenőriz. Értesíteni tud minden szkennelésről, csak a fontos lépésekről (vám, problémák, „Ready for pickup”, kiszállítás alatt, kézbesítés és visszaküldés) vagy „Delivery only” beállítással. Nem tud helyetted kapcsolatba lépni a JS Express-szel vagy az eladóval.

## Kérdések a JS Express csomagkövetésről

### Mi az a JS Express (急速国际)?

Határon átnyúló online eladóknak dolgozó kínai logisztikai cég, amelyet a 急创速供应链管理（苏州）有限公司 működtet. Közlése szerint 2010-ben Hongkongban indult, és 2011-ben nyitotta meg szucsoui központját.

### Hol tudok JS Express csomagot követni?

A fenti mezőben, vagy a JS Express angol „Express Query” oldalán a js-exp.com webhelyen. A kínai oldala előbb képes ellenőrző kódot kér.

### Mit jelent a 未获取到任何数据 a JS Express követésnél?

„Nem található adat”: a JS Express nyomkövetője nem ismeri a számot elírás miatt, vagy mert a JS Express még nem kapta meg a csomagot. Ellenőrizd az eladóval.

### Ki kézbesíti a JS Express csomagokat az én országomban?

Egy helyi futárszolgálat az országodban. A JS Express előzményei az Egyesült Államokban USPS, GOFO vagy SpeedX, Kanadában Straightship, az Egyesült Királyságban Yodel, Németországban DHL, iMile vagy J&T Express, Franciaországban Colissimo, Spanyolországban CTT Express, egyes vonalakon pedig FedEx kézbesítését mutatják. Máshol kérdezd meg az eladót, melyik cég kézbesít.

### Kit keressek egy eltűnt JS Express csomag miatt?

Az eladót, akitől rendeltél: ő rendelte meg a szállítást a JS Expressnél, és ő nyújtja be a reklamációt. A JS Express 400-0353-518-as forródrótja kínai telefonszám.

:::sources
- [JS Express: 急速国际 (főoldal)](https://www.js-exp.com/) – üzemeltető vállalat
- [JS Express: Introduction](https://www.js-exp.com/EN/index.php?page=6) – Hongkong 2010, Szucsou 2011, határon átnyúló e-kereskedelem
- [JS Express: 企业介绍 (bemutatkozás)](https://www.js-exp.com/index.php?page=6) – Szucsou 2011, fiókok, platformok
- [JS Express: Express Query](https://www.js-exp.com/EN/index.php?page=19) – angol nyomkövető, mit mutat, válaszai
- [JS Express: 快件查询 (követés)](https://www.js-exp.com/index.php?page=19) – képes ellenőrző kód
- [JS Express: Insurance Claims](https://www.js-exp.com/EN/index.php?page=29) – ügyfél kárigényei, 30 nap, kézbesíthetetlen csomagok
- [JS Express: 投保理赔 (biztosítási kárigények)](https://www.js-exp.com/index.php?page=29) – 30 nap a JS Express átvételétől, kárigény az ügyfél fiókjából
- [JS Express: Complaint](https://www.js-exp.com/EN/index.php?page=32) – űrlap, forródrót
- [JS Express: Contact Us](https://www.js-exp.com/EN/index.php?page=35) – forródrót, QQ, WeChat, régebbi számok és címek
- [JS Express: 联系我们 (kapcsolat)](https://www.js-exp.com/index.php?page=35) – forródrót nemzetközi kérdésekhez, QQ, WeChat, irodák
- [JS Express: 国际专线 (nemzetközi vonalak)](https://www.js-exp.com/index.php?catid=13) – német vonal: DPD és mások, több mint 98% 8 munkanapon belül; ausztrál vonal: Australia Post, Fastway, DPEX
- [JS Express: 急速美国专线 (USA vonal)](https://www.js-exp.com/index.php?aid=27) – megfizetett vám (DDP), visszaküldött csomagot nem visznek vissza Kínába és nem küldenek ki újra, kézbesítve de nem érkezett meg
- [JS Express: 急速欧洲专线 (Európa vonal)](https://www.js-exp.com/index.php?aid=35) – országok, megfizetett vám (DDP), távoli irányítószámok
- [Aramex Australia: About Aramex Australia](https://www.aramex.com.au/why-aramex/about-us/) – a Fastwayből lett az Aramex Australia
- [Revenue: Union Handling Fee press release](https://www.revenue.ie/en/corporate/press-office/press-releases/2026/pr-092926-handling-fee.aspx) – 2 € terméktípusonként 2026. november 1-től, az eladó vagy a kézbesítő cég fizeti, áthárítható
- [USPS: Missing Mail and Lost Packages](https://www.usps.com/help/missing-mail.htm) – feladók vagy címzettek, 7 naptól
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – számformátumok, szkennelési szövegek és fázisok, órák (a J&T Express Germany sorai november 9. óta a saját időeltolódásukkal), USPS, GOFO, SpeedX, Straightship, Yodel, DHL, iMile, J&T Express Germany, FedEx, Colissimo és CTT Express szkennelések, név nélküli futár központi, depós, sikertelen kísérletes, átirányítási és hiba szkennelései, név nélküli amerikai partner központi szkennelései, iMile német központjai, böngészős tartalék, vám, megtagadások és visszaküldések, a futár száma, mit mutat a Peek és hogyan adja át a csomagot
:::
