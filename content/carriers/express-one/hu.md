---
title: Express One csomagkövetés: státuszok, Exopoint, elérhetőség
description: Így követheti az Express One-csomagot: mit jelentenek a státuszok, hogyan irányíthatja Exopointba, hogyan fizeti ki az utánvétet, és hol reklamálhat.
slug: express-one-csomagkovetes
published: 2026-10-26
updated: 2026-10-27
---

Az Express One Hungary Kft. az [Osztrák Posta](carrier:austrian-post) tulajdonában álló magyar futárszolgálat. Házhoz szállít, és Exopoint néven több mint 2300 csomagpontra és csomagautomatába is kézbesít. A csomagot a [tracking.expressone.hu](https://tracking.expressone.hu/) oldalon követheti, vagy beírhatja a számát a fenti mezőbe.

## Az Express One csomagazonosítója

Amikor a csomag megérkezik az Express One-hoz, Ön és a feladó is kap egy e-mailes előértesítőt, benne egy 4 órás várható időablakkal. SMS-t csak akkor kap, ha a feladó megrendelte ezt a feláras szolgáltatást. Ha nem jött értesítő, és a webáruház e-mailjében sincs csomagszám, kérdezze meg a webáruházat.

A Csomagkövetés háromféle azonosítót fogad el: a csomagazonosítót (a vonalkód számát), a feladói referenciaszámot és a bizonylatszámot. Az utóbbi kettőhöz a feladó 5 jegyű cégkódja is kell, ezért a csomagazonosítót érdemes használni. Az Express One nem teszi közzé, hogyan épül fel a szám; a Peek a 13 és a 24 jegyű csomagazonosítót követi:

| Szám (kitalált példa) | Mit jelent |
| --- | --- |
| `0000000000000` | 13 számjegy: lehet Express One-csomagazonosító, de ilyen hosszú a [La Poste](carrier:la-poste) és más szállítók száma is. |
| `000000000000000000000000` | 24 számjegy: lehet Express One-csomagazonosító, de ilyen hosszú az Osztrák Posta, az [InPost](carrier:inpost) és a [Ciblex](carrier:ciblex) száma is. |
| `00000000000000` | 14 számjegy: többnyire DPD-csomagszám; az Express One-nál a Peek nem követi. |
| `RR000000005HU` | Két betű, 9 számjegy és `HU`: a [Magyar Posta](carrier:magyar-posta) nemzetközi azonosítója. |

## Mit jelentenek az Express One státuszai?

A tracking.expressone.hu minden lépésnél dátumot, időt, egy rövid kódot és egy mondatot mutat, a depós beolvasásoknál zárójelben a helyet is, például „(HU - Budapest)”. Az oldal magyarul és angolul is olvasható. A Peek az angol mondatokat mutatja, ezért a táblázat is ezeket adja meg; a kód alapján a magyar nézetben is megtalálja őket.

| Kód | Angol mondat (a Peekben is ez látszik) | Mit jelent Önnek? |
| --- | --- | --- |
| `PRE` | `Shipping details received, shipment not yet handed over to EXOHU` | A feladó rögzítette az adatokat, de az Express One Hungary (EXOHU) még nem kapta meg a csomagot. |
| `DLS` | `Shipment (pan) data uploaded` | A csomag adatai bekerültek az Express One rendszerébe, többnyire a központi depóba érkezés után. Ehhez tartozik az e-mailes előértesítő. |
| `INB` | `Arrived at central hub` | Megérkezett a központi depóba. |
| `OUB` | `Departed central hub` | Elindult a központi depóból. |
| `DEP` | `Arrived at depot` | Megérkezett abba a depóba, ahonnan kiszállítják. |
| `OFD` | `Out for delivery` | Ma kiszállítják; ilyenkor jön a 2 órás időablakot megadó értesítő. |
| `E12` | `Addressee not present at delivery address` | A futár nem találta otthon. |
| `V14` | `Addressee unavailable` | A második kísérlet is sikertelen volt. |
| `E05` | `Obstacles of nature` | Időjárás vagy más természeti akadály miatt elmaradt a kézbesítés; ez is kísérletnek számít, a csomagot újra kiviszik. |
| `D00` | `Successful delivery` | Kézbesítve. |

Az átütemezést jelző lépések, például `Delivery schedule updated` vagy `Processing in progress`, nem jelentenek mozgást. Ha a csomag visszaindul, külön visszaküldési előzmény jelenik meg; az ott álló `Successful delivery` azt jelenti, hogy visszaért a feladóhoz.

## Kézbesítés házhoz és Exopointba

**Mikor jön a futár?** Az ÁSZF szerint a belföldi csomag a feladást követő munkanapon, 7 és 18 óra között érkezik, november 1. és december 31. között pedig 24–72 óra alatt. A kiszállítás reggelén kapott értesítő megadja a 2 órás időablakot, a feladó nevét, a futár elérhetőségét és az esetleges utánvét összegét. A futár többnyire ebben az időablakban érkezik, de ez nem garantált. Hétvégén csak akkor kézbesítenek, ha a feladó felárért szombati kézbesítést rendelt.

**Átvétel és utánvét.** Az átvételt a teljes nevével, egy személyazonosító okmánya számával és az aláírásával igazolja; Ön helyett meghatalmazott is átveheti. Kibontani csak az aláírás és az utánvét kifizetése után lehet. A futárnál legfeljebb 1 000 000 Ft-ot fizethet: 300 000 Ft-ig készpénzzel is, e fölött csak bankkártyával. Előre, online is fizethet, az értesítőben kapott linken. A futárnak nem kötelező visszajárót adnia.

**Ha nincs otthon.** Legkésőbb a következő munkanapon e-mailben vagy SMS-ben értesítik, és egyszer ingyen újra megpróbálják. Az első sikertelen kísérlet után legfeljebb az 5. munkanapig tartják a csomagot; ezalatt új időpontot kérhet, vagy kérheti, hogy csomagpontra vagy automatába vigyék. Az ÁSZF szerint a második sikertelen kísérlet után a csomag visszamegy a feladóhoz; egy 2024-es Express One-blogbejegyzés szerint viszont ilyenkor még 5 munkanapig átvehető a budapesti központi depóban. Erről az ügyfélszolgálaton érdeklődhet.

**Módosítás (rugalmas kézbesítés).** A reggeli SMS vagy e-mail „Módosítás” gombjával az első kézbesítési napon, egyszer módosíthat: a csomagot csomagautomatába vagy csomagpontra (akár 1 órával a futár érkezése előtt is), másik címre, a szomszédhoz vagy a legközelebbi Express One-depóba irányíthatja, legfeljebb 5 munkanappal későbbi napot kérhet, és online kifizetheti az utánvétet. Az értesítő csak azt kínálja fel, amit a feladó engedélyez és a csomag mérete enged: nagy csomag nem kerülhet automatába. Az ÁSZF szerint a módosításért az Express One külön díjat számíthat fel, és a módosítás egy kézbesítési kísérletnek számít; egy 2024-es blogbejegyzése szerint az átirányítás ingyenes.

**Exopoint.** Ide tartoznak az Express One EXOBOX automatái, az Alzaboxok, a Packeta-pontok és az OMV-kutak; sok webáruházban már a rendeléskor választhatja őket. Az átvételi kódot SMS-ben vagy e-mailben kapja. A Csomagpont oldal 0–24 órás átvételt ígér, egy 2025-ös blogbejegyzés szerint viszont csak az EXOBOX-ok és az Alzaboxok érhetők el éjjel-nappal, a benzinkutak és a Packeta-pontok pedig hosszú nyitvatartással várják az átvevőket. Az utánvétet online előre kifizetheti; a helytől függően kártyával vagy készpénzzel (legfeljebb 250 000 Ft-ot) is fizethet. Külföldről érkező csomagot az Express One kérés nélkül is Exopointba irányíthat. Visszaküldeni bizonyos webáruházaknak az OMV-kutak kivételével bármelyik Exopointban lehet; hogy hogyan, azt a webáruháztól tudhatja meg.

**Meddig vár a csomag?** Erről az Express One oldalai mást-mást írnak: a Csomagpont oldal szerint „akár egy hétig”, a blogbejegyzései szerint EXOBOX-ban 3, Alzaboxban 2, Packeta-ponton akár 7 napig (2025), automatában 72 óráig (2026), csomagponton 5 napig (2024). A határidő közeledtével emlékeztetőt küldenek. Ha nem veszi át időben, a 2026-os bejegyzés szerint a csomag visszakerül a rendszerbe, és újra egyeztetni kell, a 2024-es szerint visszamegy a feladóhoz.

## Késik, elveszett vagy sérült a csomag

**A Csomagkövetés nem találja a csomagot.** Lehet, hogy elírta a számot, hogy a 180 napnál régebbi számot már törölték, vagy hogy a feladó még nem rögzítette a csomagot: próbálja később. Az ÁSZF szerint ha az adatok rögzítése után 5 munkanapon belül nem érkezik csomag, az Express One törölheti az adatokat. Bővebben: [mit tegyen, ha nem frissül a nyomkövetés](guide:tracking-not-updating) (angolul).

**Sérült csomag vagy rossz termék.** A látható sérülést átvételkor azonnal jelezze a kézbesítési dokumentumon, és kérjen jegyzőkönyvet, különben elveszíti a kártérítéshez való jogát. A kibontás után észrevett sérülést az ÁSZF szerint 3 munkanapon belül írásban kell bejelentenie az Express One-nak. A GYIK szerint a sérült tartalomról és a rossz termékről a webáruháznak is szóljon: a kárügyintézést az Express One az ő jelzésükre indítja, a tartalomért pedig nem felel.

**Elveszett csomag.** Belföldön elveszettnek számít a csomag, ha a feladástól számított 15 napon belül nem kézbesítették, és meg sem kísérelték. Kártérítést általában a feladó kérhet; Ön akkor, ha a feladó írásban Önre engedményezte az igényt, vagy ha online rendelésnél a visszaigazolással igazolja, hogy a teljes árat kifizette. Az igényt a feladást követő 15. naptól számított 6 hónapon belül, írásban kell bejelenteni, a fuvarlevélszámmal, a tartalmat és az értéket igazoló iratokkal és a kártérítési formanyomtatvánnyal. Az Express One 30 napon belül dönt.

**Panasz.** Panaszt a feladástól számított 6 hónapon belül tehet, telefonon, írásban vagy szóban. Az Express One legkésőbb 30 naptári napon belül válaszol, ezt egyszer 30 nappal meghosszabbíthatja. Ha a választ nem fogadja el, vagy nem kap választ, 30 napon belül a Nemzeti Média- és Hírközlési Hatósághoz (NMHH) fordulhat felügyeleti eljárásért; ennek díja 6000 forint. Fogyasztóként a lakóhelye szerinti békéltető testülethez is fordulhat, ha előbb az Express One-nal próbálta rendezni a vitát.

**Csomag az EU-n kívülről.** Az Express One az AliExpress, a Shein, a Trendyol és a Temu csomagjait is kézbesíti. Távol-keleti rendelésnél az adatok már az ottani logisztikai központból megérkeznek, de maga a csomag legalább 1–1,5 hét múlva ér Magyarországra, és csak az Express One központi depójától követhető. A vámkezelés jellemzően legfeljebb 2 munkanap. 2026. július 1. óta a távértékesítésben érkező, 150 eurónál kisebb értékű küldeményekre tételenként 3 eurós átalányvámot kell fizetni, 2026. november 1-jétől pedig a távértékesítésben érkező árukra tételenként 2 eurós uniós kezelési díjat is fizetni kell majd. Részletek a [vámról szóló útmutatóban](guide:customs) (angolul).

## Az Express One elérhetőségei

Az elérhetőségek így szerepeltek az expressone.hu Kapcsolat oldalán 2026. október 26-án. Az ügyfélszolgálat munkanapokon 8 és 17 óra között érhető el (a GYIK szerint 8 és 18 óra között):

- **Belföldi ügyfélszolgálat és reklamáció:** +36 1 8 777 400, ugyfelszolgalat@expressone.hu; akkor is ide forduljon, ha a futár nem veszi fel a telefont. Ha a telefonos ügyfélszolgálat túlterhelt, az Express One az e-mailt javasolja.
- **Nemzetközi ügyfélszolgálat:** +36 1 8 777 410, intcs@expressone.hu; reklamáció: intcsreklamacio@expressone.hu.
- **Káresemény-kezelés:** +36 1 8 777 470, karugy@expressone.hu.
- **Űrlap:** a [Kapcsolat oldalon](https://expressone.hu/kapcsolat), például „Csomagot várok” vagy „Szállítással kapcsolatos panasz” tárggyal; ezekhez a fuvarlevélszám is kell.
- **Személyesen:** a logisztikai központban (1239 Budapest, Európa u. 12.) csak előzetes egyeztetés után veheti át a csomagot.

A Csomagkövetés oldal a +36 1 8 777 444-es számot és egy Messenger-chatbotot is felsorol.

> A valódi Express One-értesítő e-mailben érkezik (ha a feladó megrendelte, SMS-ben is), és megnevezi a feladót, a futár elérhetőségét, az időablakot és az esetleges utánvét összegét. Ha egy üzenet olyan csomagért kér pénzt vagy adatot, amelyről nem tud, ne kattintson: ellenőrizze a csomagazonosítót a tracking.expressone.hu oldalon, vagy hívja az ügyfélszolgálatot.

## Express One-csomagok követése a Peekkel

A [Peek](/) felülete angol nyelvű. A Peek a számból nem ismeri fel, hogy Express One-csomagról van szó: a 13 jegyű számmal más szállítókat, például a La Poste-ot kérdezi meg, a 24 jegyűvel az Osztrák Postát, az InPostot és a Ciblexet. Illessze be így is: a Peek több mint 3500 szállítót követ. Ha egyik sem ismeri, ezt látja: „not found yet · Peek keeps checking”; ilyenkor a „Change” gombbal válassza ki az Express One-t. Ha a tracking.expressone.hu `plc_number=` részt tartalmazó linkjét illeszti be, a Peek abból tudja, hogy Express One-csomagról van szó.

A lépéseket angolul mutatja, a depós beolvasásoknál a településsel együtt; az időpontokat magyar idő szerint értelmezi, és az eszköze időzónájában jeleníti meg. Az ismeretlen kódú lépéseket állapot nélkül jeleníti meg. Várható kézbesítési időt (az Express One időablakát), súlyt, feladót és aláírást nem mutat.

Ha valaki várja a csomagot (értesítést kért rá, vagy az elmúlt órában megnézte), a Peek napközben, 8 és 22 óra között 10 percenként ellenőrzi, `Out for delivery` állapotban 2 percenként; egyébként óránként, éjjel egy-két óránként. Változáskor értesítést küldhet. Az Express One-nal vagy a webáruházzal Ön helyett nem veszi fel a kapcsolatot.

## Gyakori kérdések az Express One-csomagkövetésről

### Mit jelent a „not yet handed over to EXOHU” státusz?

A feladó rögzítette a csomag adatait, de az Express One még nem kapta meg a csomagot. Ha napokig így marad, kérdezze meg a webáruházat.

### Mikor jön az Express One futárja?

A kiszállítás reggelén kapott értesítőben 2 órás időablakot és a futár telefonszámát találja, a Csomagkövetésben pedig azt, hány címzett van még Ön előtt. Ha a futár nem veszi fel a telefont, hívja a +36 1 8 777 400-as számot.

### Átirányíthatom a csomagot csomagautomatába?

Igen, a reggeli értesítő „Módosítás” gombjával, akár 1 órával a futár érkezése előtt is, de csak az első kézbesítési napon és egyszer. Nagy csomagot nem lehet automatába irányítani.

### Meddig vár a csomag az Exopointban?

Az Express One oldalai mást-mást írnak: a Csomagpont oldal szerint akár egy hétig, a blogbejegyzései szerint a helytől függően 2–7 napig. A határidő közeledtével emlékeztetőt küldenek.

### Megbízható az Express One?

Igen: az Osztrák Posta leányvállalata, és az NMHH nyilvántartásában futárposta- és expressz szolgáltatóként szerepel. Ha kétes üzenetet kap, a csomagot a tracking.expressone.hu oldalon ellenőrizze, ne az üzenetben kapott linken.

:::sources
- [Express One: Csomagkövetés](https://expressone.hu/csomagkovetes) – futár érkezése, telefonszámok, chatbot
- [Express One: Csomagkövetés (tracking.expressone.hu)](https://tracking.expressone.hu/) – azonosítók, cégkód, nem található csomag, nyelvek
- [Express One: GYIK](https://expressone.hu/gyik) – módosítás, időablak, szombat, utánvét, kibontás, sérülés, nyitvatartás
- [Express One: Kapcsolat](https://expressone.hu/kapcsolat) – elérhetőségek, nyitvatartás, űrlap
- [Express One: Csomagpont](https://expressone.hu/csomagpont) – Exopoint, egy hét, 0–24, utánvét, visszaküldés
- [Express One: Kiegészítő szolgáltatások](https://expressone.hu/kiegeszito-szolgaltatasok) – értesítők, rugalmas kézbesítés
- [Express One: Főoldal](https://expressone.hu/) – átirányítás 1 órával az érkezés előtt
- [Express One: Rólunk](https://expressone.hu/rolunk) – az Osztrák Posta leányvállalata
- [Express One: eBOX GYIK](https://expressone.hu/ebox/faq) – 250 000 Ft készpénz Exopointban
- [Express One: Általános Szerződési Feltételek](https://expressone.hu/downloads/ASZF_2026_08_15.pdf) – kézbesítés, kísérletek, FlexDel, import, átvétel, panasz, kárigény
- [Express One: Letölthető dokumentumok](https://expressone.hu/letoltheto-dokumentumok) – kártérítési formanyomtatvány
- [Express One: Merre jár a csomagom?](https://expressone.hu/blog/merre-jar-a-csomagom-csomagkovetes-es-ertesitesi-rendszerek-alkalmazasa) – értesítők, időablakok, emlékeztetők
- [Express One: Csomag átirányítás és a kézbesítés átütemezése](https://expressone.hu/blog/csomag-atiranyitas-es-a-kezbesites-atutemezese) – ingyenes átirányítás, 5 nap
- [Express One: Minden, amit az EXOPOINT megoldásokról tudni érdemes](https://expressone.hu/blog/minden-amit-az-exopoint-megoldasokrol-tudni-erdemes) – hálózat, nyitvatartás, 3, 2 és 7 nap
- [Express One: Csomagküldés csomagautomatába](https://expressone.hu/blog/csomagkuldes-csomagautomataba-miert-valasztjak-egyre-tobben) – 72 óra
- [Express One: Kínai küldemények kezelése](https://expressone.hu/blog/kinai-kuldemenyek-kezelese) – 1–1,5 hét, vámkezelés
- [Express One: A Temu, Shein és Trendyol térnyerése](https://expressone.hu/blog/a-temu-shein-es-trendyol-ternyerese-az-e-kereskedelemben) – partnerek
- [NMHH: Postafelügyelet](https://nmhh.hu/posta/felugyelet) – felügyeleti eljárás, 6000 Ft
- [NMHH: Szolgáltatók listája](https://nmhh.hu/csomageslevel/szolgaltatok-listaja) – nyilvántartott szolgáltató
- [Békéltető testületek: Tájékoztatás az eljárás menetéről](https://bekeltetes.hu/tartalom/21/menu/8) – illetékesség, előzetes egyeztetés
- [NAV: E-kereskedelem](https://nav.gov.hu/vam/e-kereskedelem) – átalányvám, kezelési díj
- [NAV: A 3 euró átalányvám és az uniós kezelési díj alkalmazása](https://nav.gov.hu/pfile/file?path=/vam/e-kereskedelem/a-3-euro-atalanyvam-alkalmazasa) – 3 és 2 euró
- [Európai Bizottság: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – július 1., november 1.
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – mit olvas a Peek
:::
