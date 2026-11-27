---
title: Xinghe Freight csomagkövetés: GZXH…YQ szám és státuszok
description: Xinghe Freight (星和货运) csomag követése Kínából: GZXH…YQ formátum, miért a GZYL rendszerét használja, előre megírt Delivered státusz, EU-s díjak és kapcsolat.
slug: xinghe-freight-csomagkovetes
published: 2026-11-27
updated: 2026-11-27
---

A Xinghe Freight (星和货运, 广州星和货运代理有限公司) egy 2014-ben alapított kuangcsoui (Guangzhou) szállítmányozó cég Kínában, amely tengeri, légi és szárazföldi fuvarozással, hajóügynöki szolgáltatással és vámkezeléssel foglalkozik. A csomagod követéséhez illeszd be a fenti mezőbe a 16 karakteres, `GZXH`, tíz számjegy és `YQ` alakú követési számot. A Xinghe Freight saját weboldala a lekérdezéseket a [GZYL](carrier:gzyl) titkosítatlan HTTP-s követőfelületére küldi tovább, ahol a lépések — akár a `Delivered` bejegyzés is — előre beütemezhetők valódi szkennelések helyett. A Peek beolvassa helyetted ezt a rendszert, szétválasztja az előre jelzett lépéseket a valódi szkennelésektől, és szükség esetén külső csomagkövető szolgáltatásokat is megkérdez.

## Xinghe Freight követési szám: így néz ki

A Peek által a Xinghe Freighthez sorolt számok `GZXH` előtagból, tíz számjegyből és `YQ` végződésből állnak, összesen 16 karakterből. Ha véletlenül lemarad a végéről az utolsó `Q` betű (`GZXH`, tíz számjegy és `Y`, 15 karakter), a Peek akkor is felismeri a számot, és a lekérdezés előtt visszaírja a `Q` betűt. Példák kitalált számjegyekkel:

| Szám | Mit jelent |
| --- | --- |
| `GZXH4829105736YQ` | `GZXH`, tíz számjegy és `YQ`: a Xinghe Freight szabványos formátuma. A Peek azonnal felismeri. |
| `GZXH4829105736Y` | `GZXH`, tíz számjegy és `Y`: hiányzik a végéről a `Q`, de a Peek automatikusan kipótolja. |
| `GZXH482910573YQ` | Csak kilenc számjegy: nem Xinghe Freight formátum. A Peek rögtön elutasítja, hogy ellenőrizhesd, nem maradt-e ki egy számjegy. |
| `YL4829105736YQ` | `YL`, tíz számjegy és `YQ`: a [GZYL](carrier:gzyl) fuvarlevél-formátuma. Ugyanaz a követőrendszer, de másik szállítmányozó. |
| `JXCAA4829105736YQ` | Szintén `YQ` a vége, mint sok kínai szállítmányozónál, de `JXC`-vel kezdődik: ez a [Juxi Express](carrier:juxi-express) száma. |

A szóközök, kötőjelek és kisbetűk nem számítanak, amikor a számot beilleszted a Peekbe.

**Hol találod meg.** Az eladó rendeli meg a szállítást a Xinghe Freightnál, és ő adja meg a követési számot a feladási e-mailben vagy a rendelés oldalán. Ha nem találod, kérdezd meg az eladót.

## Mit jelentenek a Xinghe Freight státuszai

A Xinghe Freight nyomkövetése a GZYL rendszerén fut, így a lépések a GZYL angol megfogalmazásaival jelennek meg, elírásokkal együtt. Mivel a Peek alkalmazás felülete nem magyar nyelvű, a Peek fő szakaszai is angolul látszanak: «Label created» (címke létrehozva), «Received by carrier» (futár átvette), «In transit» (úton), «At customs» (vámon) és «Delivered» (kézbesítve). A lépések a csomag szokásos sorrendjében:

| A követőrendszer szövege | Mit jelent számodra | A Peek ezt mutatja |
| --- | --- | --- |
| `Order information received` | Az eladó beküldte a rendelés adatait. A csomag fizikailag még nincs a szállítmányozónál. | «Label created» |
| `Arrive at the cross border receiving point` | A szállítmányozó átvette a csomagot: az első valódi szkennelés. | «Received by carrier» |
| `Goods leave the operation center` | A csomag elhagyta a kínai feldolgozóközpontot. | «In transit» |
| `Arrive at the international airport` | A nemzetközi repülőtéren várja a járatot. | «In transit» |
| `Departing from the airport` | A repülőgép elindult. | «In transit» |
| `Item arrives at destination airport` | Megérkezett a célország repülőterére. | «In transit» |
| `local carrier Agent Import customs clearance` | Egy helyi ügynök végzi az import vámkezelést a célországban. | «At customs» |
| `The import customs keep it for inspection` | A célország vámhatósága vizsgálatra visszatartja a csomagot. | «At customs» |
| `Customs clearance completed` | A vámkezelés befejeződött, a csomag továbbhalad. | «In transit» |
| `Internationaliy Package received at processing center` | Egy célországbeli feldolgozóközpont átvette a vámkezelt csomagot. Nem új feladásról van szó. | «In transit» |
| `Leave the email processing center` | A csomag elhagyta ezt a feldolgozóközpontot. | «In transit» |
| `Arrival sorting, distribution center` | Megérkezett egy helyi szortírozó- vagy elosztóközpontba, a szöveg végén a várossal. | «In transit» |
| `Leaving the sorting center` | Elhagyta a szortírozóközpontot, a végén a várossal. | «In transit» |
| `Package is on the way` | Úton van tovább az országon belül egy városnévvel. Nem jelenti azt, hogy a futár ma kézbesíti. | «In transit» |
| `Delivered` | Kézbesítési bejegyzés a várossal. Előfordulhat, hogy csak előre ütemezett terv, nem valódi szkennelés: lásd alább. | «Delivered», a fix ütemtervre utaló megjegyzéssel, hacsak a Peek vissza nem tartja (lásd alább) |

**Hongkongon keresztül.** Előfordulhat a `香港处理中心已收寄【Hong Kong】` sor is: ez postai átvétel a hongkongi feldolgozóközpontban, amelyet a rendszer kínaiul, a várost szögletes zárójelben megadva továbbít. A Peek változatlanul jeleníti meg a «Received by carrier» szakasz alatt.

**Kínai nyelvű sor.** Ha a rendeléshez még nincs szkennelés, a platform magától kiírja a `货物电子信息已经收到` szöveget (az elektronikus áruadatok megérkeztek). Az eladó létrehozta a rendelést, de a csomagot még senki sem szkennelte be. A Peek kínaiul jeleníti meg a «Label created» szakaszban.

**Időpontok.** A Peek minden időpontot pekingi időként (`UTC+8`) olvas be — a magyarországi városokat említő soroknál is —, és átszámítja az eszközöd időzónájára. Télen a pekingi 20:00 magyar idő szerint 13:00 (`UTC+1`), nyáron pedig 14:00 (`UTC+2`).

**Előre megírt lépések.** Ezen a követőplatformon a szállítmányozók előre betölthetik a csomag teljes idővonalát a felvételtől egészen a célország utolsó lépéséig, a rendszer pedig akkor mutatja meg az egyes sorokat, amikor eljön az idejük. Az ilyen bejegyzés terv, nem valódi szkennelés. A GZYL követőjében a tervet egy `Delivered` zárja, amelyet a rendszer esti kötegben ad hozzá a jelzett napon: a terv utolsó lépését követő napra dátumozva, pekingi irodai órákban, függetlenül attól, melyik országban élsz. A Peek visszatartja ezeket a sorokat (lásd alább), de más csomagkövető oldalak másolatai ettől még megjeleníthetik őket, így egy ottani `Delivered` nem bizonyítja, hogy a csomag megérkezett.

## Ki kézbesíti a Xinghe Freight csomagokat Magyarországon?

A Peek nem tudja, melyik helyi futárszolgálat vagy posta kézbesíti a Xinghe Freight csomagokat Magyarországon. A célországbeli lépések nem neveznek meg egyetlen céget sem, és a Xinghe Freight weboldala sem közöl semmit online vásárlók csomagjairól. Az egyetlen helyi cég, amelynek a Peek át tudja adni egy Xinghe-csomag követését, a [Swiss Post](carrier:swiss-post) Svájcban és Liechtensteinben, ha a Xinghe adataiban szerepel.

A Xinghe Freight oldalán nincsenek szállítási idők, kézbesítési kísérletekre vonatkozó szabályok vagy átvételi pontok: ezeket a kézbesítő helyi cég határozza meg. Kérdezd meg az eladót, melyik futárszolgálat szállítja a rendelésedet, és kérd el a helyi követési számot.

**Vám- és EU-s díjak.** 2026. július 1. óta az EU-n kívülről érkező, legfeljebb 150 euró értékű online rendelésekre termékkategóriánként 3 euró átalányvám vonatkozik. 2026. november 1. óta emellett az EU-s kezelési díj (Union handling fee) is fizetendő: 2 euró termékkategóriánként, a csomag értékétől függetlenül. Egy kategória az azonos vámtarifaszámú, leírású és szükség esetén azonos származású árukat foglalja magában a darabszámtól függetlenül. További részletek a [vámon rekedt csomagokról szóló útmutatóban](guide:customs) (angolul).

## Ha a Xinghe Freight csomag nem érkezik meg vagy elakadt

**Csak `Order information received` vagy `货物电子信息已经收到`.** Az eladó rögzítette a rendelést, de a csomagot még senki sem szkennelte be. Ha néhány nap múlva sincs változás, kérdezd meg az eladótól, valóban feladta-e.

**«Waiting for Xinghe Freight».** A Peeknek még nincs igazolt szkennelése a számodhoz: a GZYL rendszere vagy nem ismeri, vagy csak előre megírt tervet tárol hozzá (amely akár `Delivered`-del is végződhet), és a külső követőszolgáltatásoknál sincs semmi. A Peek tovább figyeli. Ismeretlen szám esetén a GZYL rendszere egyetlen üres sort ad vissza a számoddal referenciaként; a Peek ezt nem találhatóként kezeli, megjeleníti a «Waiting for Xinghe Freight» üzenetet, majd két nap múlva a «Check the tracking number» felszólítást. Az előre megírt terv viszont azt jelzi, hogy a rendelés megvan a Xinghénél, ezért marad a «Waiting for Xinghe Freight» a szám ellenőrzésének kérése nélkül. Ennek ellenére vesd össze a számot karakterről karakterre az eladó üzenetével.

**Elakadt a vámon.** Ha importdíj befizetését kérik, olvasd el a [vámon lévő csomagok útmutatóját](guide:customs) (angolul). Ha napokig nem mozdul, szólj az eladónak.

**A követő vagy más oldal több lépést mutat, mint a Peek.** A többlet sorok — köztük a `Delivered` — lehetnek előre beírt tervek, amelyeket a Peek visszatart. A webshop által megadott becsült szállítási időhöz igazodj, és írj az eladónak, ha az lejárt. Bővebben a [Kínából érkező csomagok követéséről](guide:tracking-from-china) (angolul).

**`Delivered`, mégsem érkezett semmi.** A bejegyzés része lehet az előre beírt ütemtervnek. Ha nem a Swiss Post kézbesít, a Peek a «Delivered» alatt ezt jelzi: «Xinghe Freight updates on a fixed schedule, with no signature. If nothing came, ask the seller.» Jelezd az eladónak, hogy a csomag nem érkezett meg, és kérd el a helyi kézbesítő nevét és követési számát. Bővebben a [kézbesítettnek jelzett, de meg nem kapott csomagokról](guide:delivered-not-received) (angolul).

**Elveszett vagy visszaküldték.** A szállítást az eladó rendelte meg a Xinghe Freightnál, ezért visszatérítést vagy újraküldést az eladótól vagy a piactértől kérj.

## Xinghe Freight kapcsolat és ügyfélszolgálat

A Xinghe Freight kínai weboldalán (`xinghe-express.cn`) az alábbi elérhetőségek szerepelnek:

- **Ügyfélszolgálati vonal (24/7):** `19924483103` (külföldről `+86 199 2448 3103`), a hét minden napján 0–24 órában megadva.
- **E-mail:** `3975605292@qq.com` üzleti megkeresésekhez és árajánlatokhoz.
- **Cím:** Guangzhou, Guangdong tartomány, utcanév nélkül (a pontos címhez előzetes kapcsolatfelvételt kérnek).

Az oldal kizárólag kínai nyelvű, és nem kínál külön ügyfélszolgálatot külföldi címzetteknek: a kapcsolatfelvételi űrlap egyszerűen e-mailt nyit a fenti címre. A Xinghe ügyfele az eladó, ezért először mindig az eladónál érdeklődj, válasz hiányában pedig a piactéren.

## Xinghe Freight csomag követése a Peekkel

A [Peek](/) azonnal felismeri a `GZXH…YQ` számot (és a végéről lemaradt `Q` nélküli `GZXH…Y` alakot is) Xinghe Freightként a «Detected carrier» jelzéssel. Ha az eladó eltérő formátumú számot adott, akkor is illeszd be: a Peek több mint 4 500 fuvarozót követ. Kézi kiválasztáshoz kattints a «Change» gombra és keress a Xinghe Freight (vagy `星和货运`) névre, vagy már felvett csomagnál válaszd a «Change carrier» opciót.

A Peek közvetlenül a GZYL követőrendszerét kérdezi le, ugyanúgy, mint a Xinghe oldalának keresője. Megmutatja a státuszt, minden lépést a helyszínével, valamint a célországot, ha szerepel az adatlapban. Az időpontokat pekingi idő szerint olvassa és az eszközöd időzónájában jeleníti meg («Times are in your time zone:» az előzmények végén). A Peek kihagyja a nevek, cím, irányítószámok, súly, fizetési adatok, aláírás és kézbesítési igazolás mezőit, és becsült kézbesítési napot sem mutat, mert a rendszer nem ad meg ilyet.

Ha a rendszer nem ismeri a számot, a Peek külső követőszolgáltatásokat kérdez meg, és az ő másolatukat jeleníti meg. Amíg sehol nincs lépés, a «Waiting for Xinghe Freight», két nap után pedig a «Check the tracking number» látszik.

A Peek visszatart minden olyan lépést, amelyet a megjelenített időpontja előtt több mint 12 órával írtak be, minden olyan lépést, amely ugyanabban a másodpercben került be, mint egy több mint fél órával előre írt sor (a rendelés regisztrációját kivéve), és minden olyan sort, amely a lekérdezés pillanatában pekingi idő szerint a jövőbe mutat. Az előre beírt lépéseket tartalmazó adatlapokon a `Delivered` sort is visszatartja, még akkor is, ha aznap írták be. A státuszt a megtartott lépések alapján határozza meg, nem az összesítő státuszból, és a külső követőszolgáltatásokból származó másolatokból is kiszűri ezeket a tervezett lépéseket (`Delivered`-del együtt). Ha azonban a rendszer nem érhető el vagy nem ismeri a számot, a Peek nem tudja megkülönböztetni egy külső szolgáltatás tervezett sorát a valódi szkenneléstől. Ha a Peek előrejelzéseket tart vissza, az előzmények tetején kézbesítésig vagy visszaküldésig megjelenik az «Other sites may show more steps, but they’re only predictions.» sor, a «Show them» pedig szürkén, dátummal («Predicted for Mon 12 Oct») listázza őket. Ezek nem változtatnak státuszt és nem küldenek értesítést.

A Swiss Post kivételével a Peek nem adja tovább a követést a helyi futárnak: ha az eladó megadja a helyi számot, add hozzá külön csomagként. Amíg a csomag nyitva van a Peekben, illetve kézbesítés napján bekapcsolt értesítésekkel a Peek akár 10 percenként ellenőriz; egyébként és csendes időszakban óránként. Értesítést kérhetsz az «Every scan», az «Important steps» (a két vámügyi lépés és a `Delivered`, repülés és válogatás nélkül) vagy a «Delivery only» beállítással. A Peek nem tud kapcsolatba lépni helyetted a Xinghe Freighttal, a GZYL-lel vagy az eladóval.

## Gyakori kérdések a Xinghe Freight követéséről

### Mi az a Xinghe Freight (星和货运)?

A 广州星和货运代理有限公司 (Guangzhou Xinghe Freight Forwarding) egy 2014-ben alapított kuangcsoui szállítmányozó cég Kínában, amely tengeri, légi és szárazföldi fuvarozással, hajóügynöki ügyintézéssel és vámkezeléssel foglalkozik. Csomagszámai 16 karakteresek: `GZXH`, tíz számjegy és `YQ`.

### Miért a GZYL követőjét mutatja a Xinghe Freight csomagom?

A Xinghe Freight weboldalának keresőmezője közvetlenül a GZYL rendszerére továbbítja a lekérdezéseket, ezért a lépések szövege megegyezik a GZYL-ével. A Peek Xinghe Freight néven kezeli a csomagot, és ezt a rendszert olvassa be helyetted.

### Ki kézbesíti a Xinghe Freight csomagokat Magyarországon?

Sem a Xinghe weboldala, sem a követőrendszer nem nevez meg magyarországi helyi futárszolgálatot (a Peek kizárólag Svájcban és Liechtensteinben tudja automatikusan átadni a követést a Swiss Postnak). Kérdezd meg az eladótól, melyik helyi cég kézbesít, és mi a követési száma.

### Miért mutat a Peek kevesebb lépést vagy semmit a GZXH számomra?

Vagy még nincs adat a rendszerben, vagy a rekord előre megírt lépéseket — köztük ütemezett `Delivered` bejegyzést — tartalmaz, amelyeket a Peek a «Show them» mögé rejt valódi szkennelések helyett. Ellenőrizd a számot az eladó levelében, és írj neki, ha pár nap múlva sincs frissítés.

### Hogyan léphetek kapcsolatba a Xinghe Freighttal?

Először az eladót vagy a piacteret keresd, mivel az eladó rendelte meg a szállítást. A kínai weboldal (`xinghe-express.cn`) a 0–24 órás `19924483103` telefonszámot (`+86 199 2448 3103` külföldről) és a `3975605292@qq.com` üzleti e-mail-címet tünteti fel, külön külföldi címzetti ügyfélszolgálat nélkül.

:::sources
- [Xinghe Freight: 广州星和货运代理有限公司 (főoldal, kínaiul)](http://xinghe-express.cn/) – cégnév, szállítmányozási szolgáltatások, a GZYL követőt megnyitó keresőmező, telefonszám és e-mail
- [Xinghe Freight: 关于我们 (rólunk, kínaiul)](http://xinghe-express.cn/about.html) – 2014-es alapítás, tengeri, légi és szárazföldi fuvarozás, hajóügynöki tevékenység és vámkezelés
- [Xinghe Freight: 产品服务 (szolgáltatások, kínaiul)](http://xinghe-express.cn/services.html) – szolgáltatások köre, online vásárlói csomagokra vonatkozó adatok nélkül
- [Xinghe Freight: 联系我们 (kapcsolat, kínaiul)](http://xinghe-express.cn/contact.html) – 7×24-es vonal, üzleti e-mail, címként csak Guangzhou, e-mailt nyitó űrlap
- [Európai Bizottság: EU Customs Reform (angolul)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 3 € átalányvám termékkategóriánként 150 €-ig 2026. július 1-től, 2 € uniós kezelési díj 2026. november 1-től
- [Európai Bizottság: Guidance on the EUR 3 customs duty and the Union handling fee (PDF, angolul)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € díj értéktől függetlenül és a termékkategória meghatározása
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – nevek, `GZXH…YQ` és rövidített `GZXH…Y` formátumok, hibás hossz elutasítása, GZYL követő, lépések szövegei, hongkongi átvétel, kínai sor, pekingi idő, előre írt lépések, tartalék követők, Swiss Post átadás és amit a Peek megjelenít, visszatart vagy kihagy
:::
