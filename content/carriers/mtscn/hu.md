---
title: Meiteshun (MTSCN) csomagkövetés: AMTS szám és státuszok
description: Kövesd a Meiteshun Logistics (MTSCN / 美特顺) csomagodat Sencsenből: AMTS…FS formátum, miért lehet előre ütemezett a Delivered, helyi kézbesítés és kapcsolat.
slug: meiteshun-csomagkovetes
published: 2026-11-27
updated: 2026-11-27
---

A Meiteshun Logistics (MTSCN, 深圳市美特顺物流有限公司) egy sencseni (Kína) nemzetközi szállítmányozó, amelynek fuvarlevelei az `AMTS`, kilenc számjegy és `FS` formátumot követik. Illeszd be az `AMTS…FS` számot a fenti mezőbe a rendelésed nyomon követéséhez és az előre megírt lépések kiszűréséhez: a Meiteshun nyomkövetője a rendelés létrehozásakor előre felírja a csomag teljes idővonalát — a `Delivered` bejegyzést is beleértve —, így az oldalán látható `Delivered` sor önmagában még nem bizonyítja, hogy a csomag megérkezett.

## Meiteshun nyomkövetési szám: hogyan néz ki

A Meiteshun fuvarlevelei az `AMTS`, kilenc számjegy és `FS` felépítést követik. Kitalált számjegyekkel:

| Szám | Mi ez |
| --- | --- |
| `AMTS482910517FS` | `AMTS`, kilenc számjegy és `FS`: egy Meiteshun fuvarlevél. |
| `AMTS48291051FS` | Csak nyolc számjegy: nem a Meiteshun formátuma. Ellenőrizd, nem hiányzik-e egy számjegy. |
| `RR482910576CN` | Két betű, kilenc számjegy és `CN`: egy [China Post](carrier:china-post) szám, nem a Meiteshuné. |

A kisbetűk és a szóközök nem számítanak, amikor a számot beilleszted a Peekbe.

**Hol találod meg.** Az eladó rendeli meg a szállítást a Meiteshuntól, és ő továbbítja a számot. Ha nem találod, nézd meg, [hol keresd a nyomkövetési számot](guide:find-tracking-number) (angolul).

**Bolti referenciaszám.** A Meiteshun nyomkövetője az eladó saját referenciaszáma alapján is megtalálja a rendelést — pontosan úgy beírva, ahogy kaptad, a kötőjelekkel együtt —, majd megmutatja az `AMTS` számot.

**Hivatkozás számokkal, domainnév nélkül.** A Meiteshun nyomkövetője sima HTTP fölött, puszta szervercímen fut, nem domainnéven. A hozzá tartozó hivatkozásban ez a számjegyes cím szerepel a weboldal neve helyett, továbbá a `trackIndex.htm`, a `documentCode=` és a számod vagy a referenciád: ezt változtatás nélkül beillesztheted a Peekbe.

## A Meiteshun követési státuszok jelentése

Minden Meiteshun rendelés ugyanazt a forgatókönyvet követi, és a lépések többsége előre meg van írva (lásd alább). A szövegek a forgatókönyv sorrendjében, és hogy mit mutat a Peek, amikor az adott sor a legfrissebb:

| Mit ír a Meiteshun | Mit jelent ez neked | Mit mutat a Peek |
| --- | --- | --- |
| `The package has been collected and is ready to be sent to the distribution center` | Az első lépés: a Meiteshun felvette a csomagot Sencsenben. | "Received by carrier" |
| `item inbound in sorting center` | Egy elosztóközpontban, `ShenZhen` hellyel. | "In transit" |
| `item outbound in sorting center` | Elhagyja ezt az elosztóközpontot. | "In transit" |
| `Arriving at the terminal` | Repülőtéri terminálon, `Hong Kong` hellyel. | "In transit" |
| `Leaving the terminal, the airline will take off` | A járat felszáll Hongkongból. | "In transit" |
| `Arriving at the destination country` | Megérkezett a célországba, kétbetűs országkóddal (`US` az Egyesült Államoknál). | "In transit" |
| `Customs clearanceisin progress` | Importvámkezelés az országodban. A hiányzó szóközök a Meiteshuntól erednek. | "At customs" |
| `Customs clearance completed` | A vámkezelés kész, a csomag továbbhalad. | "In transit" |
| `Arriving at the mail processing center` | Feldolgozóközpontban az országodban. | "In transit" |
| `Arrive at the sorting and distribution center` | Hozzád közeli elosztóközpontban, a városod vagy régiód nevével. | "In transit" |
| `package is on the way` | A kézbesítés előtti lépés. Nem jelenti, hogy a csomag már a futárnál van. | "In transit" |
| `Delivered` | A forgatókönyv utolsó lépése (lásd alább). | "Delivered", hacsak a Peek vissza nem tartja (lásd alább) |

Lépések nélküli rendelésnél a platform önmagában a `货物电子信息已经收到` („az áru elektronikus adatai megérkeztek”) sort írja ki. Az eladó létrehozta a rendelést, de a csomagot még senki sem olvasta be; a Peek ezt kínaiul jeleníti meg "Label created" alatt.

A lépés helye a sor elején, vessző előtt áll, a célországbeli lépések pedig a szöveg után hozzáteszik a városodat vagy régiódat és az irányítószámot. Minden időpont pekingi óra szerint szerepel, még az országodbeli lépéseknél is: a Meiteshun nyomkövetőjén látható 21:00 Londonban délután, New Yorkban pedig reggel.

**Előre megírt lépések.** A Meiteshun saját nyomkövetőjét a Hualei (华磊) szállítmányozói platformon futtatja, amelyet más kínai szállítmányozók — például a [Pengyou](carrier:pengyou) — is használnak. A Meiteshunnál a rendelés létrehozásakor minden lépés felkerül, mindegyik a tervezett időpontjával, a felvételtől egészen a `Delivered` bejegyzésig. Ezek előrejelzések, nem a csomagod tényleges beolvasásai: azt mutatják, hol tervezték a csomagot adott napon. A Meiteshun rekordját átvevő más követőoldalak ugyanezeket a lépéseket jelenítik meg.

## Ki kézbesíti a Meiteshun csomagokat

A Meiteshun célországbeli lépései nem nevezik meg a helyi kézbesítő céget. A rekordokban a szolgáltatás neve `MTS全球小包` (MTS globális kiscsomag). A Meiteshun követési oldalán a `渠道转单号` (átadási szám) oszlopban, illetve a `跟踪号码` (követési szám) oszlopban — ha ott nem a saját `AMTS` számod áll — szerepelhet a helyi kézbesítő cég száma: add hozzá a Peekhez külön csomagként.

A Peek nem talált a Meiteshuntól közzétett kézbesítési határidőket, sem szabályokat a kézbesítési kísérletekre, az átvételi pontokra vagy az őrzési időre. Kérdezd meg az eladót, melyik cég kézbesíti a rendelésedet, és kérd el a számát: annak a cégnek a követése és szabályai irányadók. Ha importterheket kérnek — amelyek az EU-ban 2026. november 1. óta terméktípusonként 2 eurós uniós kezelési díjat is magukban foglalnak a csomag értékétől függetlenül —, lásd: [vámon visszatartott csomagok](guide:customs) (angolul).

## Nem érkezik meg a Meiteshun csomag, vagy megállt a követés

**„Delivered”, de semmi sem érkezett.** A lépés a forgatókönyv előrejelzése lehet, nem tényleges beolvasás. Jelezd az eladónak, hogy a csomag nem érkezett meg, és kérd a helyi kézbesítő számát. További lépések: [kézbesítettként jelölve, de nem érkezett meg](guide:delivered-not-received) (angolul).

**Minden lépés pontos, még sincs csomag.** A lépéseket a rendelés létrehozásakor írták meg, így a tervet mutatják, nem a csomag valós helyét. A bolt által megadott kézbesítési becsléshez igazodj, és annak lejárta után fordulj az eladóhoz.

**Sencsenben elakadva a Peekben.** Ha a Peek csak az első sencseni lépéseket mutatja, miközben az eladó linkjén több sor látszik, a hiányzó lépéseket előre írták meg, vagy a legutóbbi ellenőrzés utáni dátumúak (lásd alább). Egy előre megírt lépés soha nem számít beolvasásnak: nem módosít státuszt vagy várható dátumot, és nem küld értesítést. Amíg a csomagot nem kézbesítik vagy nem küldik vissza, a Peek az előzményt a "Other sites may show more steps, but they’re only predictions." sorral nyitja, és a visszatartott lépéseket szürkén listázza a "Show them" alatt, mindegyiket "Predicted for" és a napja megjelöléssel. Nézd meg a Meiteshun oldalán a helyi kézbesítő nevét és számát; ha nincs, kérdezd az eladót. Bővebben: [miért nem frissül a csomagkövetés](guide:tracking-not-updating) (angolul).

**Nincs találat.** Ismeretlen szám esetén a Meiteshun nyomkövetője referenciaként mutatja a számot, az összes többi mezőt üresen hagyva. A Peek ezt nem találhatóként kezeli: "Waiting for Meiteshun Logistics" feliratot, két nappal később pedig "Check the tracking number" üzenetet mutat. Egyeztesd a számot az eladóval.

**Elveszett, sérült vagy visszaküldött.** A csomagot az eladó rendelte meg a Meiteshuntól, ezért visszatérítésért vagy új küldeményért az eladóhoz vagy a piactérhez fordulj.

## Meiteshun elérhetőség és ügyfélszolgálat

A rendeléseddel kapcsolatos minden kérdéssel először az eladóhoz fordulj, mert ő kötött szerződést a Meiteshunnal. Ha az eladó nem válaszol, keresd a piacteret. Ha egyik sem segít, a 17TRACK Meiteshun-oldala megadja az `szmeiteshun@163.com` e-mail-címet, az `mtswl.com.cn` weboldalt és egy sencseni (Longgang kerület) irodát, telefonszám nélkül (2026. november 10-i állapot szerint): levélben add meg az `AMTS` számodat és az eladó nevét.

## Meiteshun csomag követése a Peekkel

A [Peek](/) alakjáról felismeri a Meiteshun számát: `AMTS`, kilenc számjegy és `FS`, amelyet a katalógusában más fuvarozó nem használ. A Meiteshun nyomkövetőjének `documentCode=` részt és a számot vagy eladói referenciát tartalmazó hivatkozása is beilleszthető. Az eladói referenciát a Peek alak alapján nem ismeri fel: illeszd be mégis, mert a Peek több mint 4 500 fuvarozót követ, majd nyomd meg a "Change" gombot és válaszd a Meiteshun Logistics lehetőséget (a `美特顺` vagy `MTSCN` keresés is megtalálja), vagy használd a "Change carrier" gombot egy már hozzáadott csomagnál. Ekkor a Peek a rendelés `AMTS` számát is megmutatja. A Peek azonban eltávolítja a kötőjeleket mind gépeléskor, mind linkből, ezért a kötőjeles referenciát közvetlenül a Meiteshun oldalán ellenőrizd.

A Peek a Meiteshun saját nyomkövetőjét olvassa, és megjeleníti a státuszt, minden beolvasást a helyével, valamint a célországot. A sorok a Meiteshun megfogalmazásában jelennek meg: a `Customs clearanceisin progress` vámként, a `package is on the way` pedig úton lévőként (nem kiszállítás alattként) számít. A Peek megtartja a városodat a célországbeli lépésekből, az irányítószámot elhagyja. Az időpontokat pekingi idő szerint olvassa, és az eszközöd időzónájában mutatja. Kihagyja a Meiteshun rekordjában esetleg szereplő neveket, címet, súlyt, díjakat, aláírást és kézbesítési igazolást, és nem mutat várható kézbesítési dátumot, mert a Meiteshun nem ad meg ilyet.

A Peek visszatart minden olyan lépést, amelyet több mint tizenkét órával a rajta szereplő időpont előtt írtak meg, továbbá minden pekingi idő szerint az ellenőrzés pillanata utánra dátumozott lépést. A megtartott lépésekre támaszkodik, nem a rekord összefoglaló státuszára. Ha egy rendelés teljes forgatókönyve a létrehozáskor íródott, a Peek csak az addig vagy legfeljebb hat órával későbbre dátumozott lépéseket tartja meg, így az akkor felírt `Delivered` visszatartásra kerül. Amikor a Peeknek a `trackIndex.htm` oldalt kell olvasnia, mert az adatlekérdezés nem listáz lépéseket, minden ottani `Delivered` sort visszatart, mert az oldal nem mutatja a létrehozás idejét. Az a rendelés, amelynek minden lépését visszatartották, még nem rendelkezik előzménnyel a Peekben, és "Waiting for Meiteshun Logistics" feliratot mutat a szám ellenőrzésére irányuló kérés nélkül: a Peek az előre megírt lépéseket annak jeleként veszi, hogy a rendelés a Meiteshunnál van (ahogy a beolvasás nélküli küldeményt is), és tovább kérdezi a nyomkövetőt. Akár részben, akár teljesen tartja vissza a lépéseket, a Peek a más követőszolgáltatások által lemásolt példányaikat is félreteszi.

A Peek a Meiteshun nyomkövetőjén követi a csomagot. Mivel a Meiteshun rekordjai sosem nevezik meg a külföldi kézbesítőt, a Peek nem adja át automatikusan: az eladótól kapott helyi futárszámot külön csomagként add hozzá.

Amíg a csomag nyitva van nálad, vagy a kézbesítés napján bekapcsolt értesítésekkel, a Peek legfeljebb 10 percenként, a "Out for delivery" megjelenésének napján pedig legfeljebb 2 percenként ellenőriz (helyi idő szerint reggel 6 órától, illetve a fuvarozó időablakának nyitása előtt fél órával), amíg a Meiteshun saját nyomkövetője válaszol — bár egyetlen ismert Meiteshun-lépés sem adja ezt a státuszt. Egyébként óránként ellenőriz. Követőszolgáltatásokon keresztül óránként, egy csendes nap után 3 óránként, a kézbesítés napján pedig azonos feltétellel legfeljebb 15 percenként. Két hírek nélküli nap után (vagy 12 óra után "Label created" vagy "Received by carrier" állapotban) óránként ellenőriz, amit egy előre megírt forgatókönyvű rendelés hamar elér. Értesíthet minden beolvasásról, csak a fontos lépésekről vagy "Delivery only" módban, és nem tud helyetted kapcsolatba lépni a Meiteshunnal vagy az eladóval.

## Kérdések a Meiteshun csomagkövetésről

### Mi az a Meiteshun Logistics (MTSCN / 美特顺)?

A 深圳市美特顺物流有限公司 (Shenzhen Meiteshun Logistics) egy sencseni (Kína) szállítmányozó cég. Fuvarlevélszámai `AMTS`, kilenc számjegy és `FS` formátumúak, a rekordokban pedig az `MTS全球小包` szolgáltatásnév szerepel.

### Az AMTS…FS követési szám a China Posthoz tartozik?

Nem. Az `AMTS`, kilenc számjegy és `FS` a Meiteshun formátuma (nyolc számjegy hiányzó számjegyre utal). A China Post nemzetközi számai két betűből, kilenc számjegyből és `CN` végződésből állnak.

### Miért írja a Meiteshun, hogy „Delivered”, ha a csomagom nem érkezett meg?

A Meiteshun Hualei nyomkövetője a rendelés létrehozásakor előre felírja a teljes tervezett idővonalat — a kézbesítést is beleértve. A Peek visszatartja ezeket az előrejelzett lépéseket, hogy ne okozzanak téves riasztást; jelezd az eladónak, hogy nem érkezett meg, és kérd a helyi fuvarozó számát.

### Ki kézbesíti a Meiteshun (MTSCN) csomagokat az én országomban?

A Meiteshun lépései sosem nevezik meg a helyi kézbesítő céget. Nézd meg a Meiteshun oldalán a `渠道转单号` (átadási szám) vagy a `跟踪号码` oszlopot egy második számért, vagy kérdezd meg az eladót a helyi futárról, és add hozzá azt a számot a Peekhez.

### Hogyan léphetek kapcsolatba a Meiteshun Logistics-szal eltűnt csomag ügyében?

Kezdd az eladónál (vagy a piactéren), mivel ő adta fel a küldeményt. Ha egyik sem válaszol, a 17TRACK az `szmeiteshun@163.com` címet és az `mtswl.com.cn` weboldalt adja meg (sencseni Longgang iroda, telefonszám nélkül); írd meg az `AMTS` számodat és az eladó nevét.

:::sources
- [17TRACK: MTSCN tracking](https://www.17track.net/en/carriers/mtscn) – email address, website and Shenzhen office listed for Meiteshun, no phone
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – €2 Union handling fee from 1 November 2026
- [European Commission: Guidance on the 3 EUR customs duty and Union handling fee](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – €2 per kind of product sold at a distance, whatever its value, counted per declaration line (one per kind of product) like the €3 duty
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – company name, Hualei platform, AMTS number shape, the tracker and its links, search by reference, the script and its wordings, places and Beijing clock, steps written in advance, the answer for an unknown number, the platform's Chinese line, service name, no delivery company in the steps, the tracking page's transfer-number and tracking-number columns, what Peek shows, holds back and leaves out
:::
