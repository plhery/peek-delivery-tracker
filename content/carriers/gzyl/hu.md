---
title: GZYL csomagkövetés Kínából: YL…YQ szám, státuszok
description: Így követheti a GZYL-lel (广州云链) érkező kínai csomagot: milyen az YL…YQ szám, mit jelentenek a státuszok, és miért jelezhet kézbesítést a követés, ha nem jött semmi.
slug: gzyl-csomagkovetes
published: 2026-11-03
updated: 2026-11-21
---

A GZYL (广州云链, Guangzhou Yunlian) teljes neve 广州云链货运代理有限公司: szállítmányozó cég a kínai Kantonban (Guangzhou). Ha egy kínai webáruház a GZYL-lel küldte el a rendelését, a nyomkövetési szám YL-lel kezdődik, ezt 10 számjegy követi, a végén pedig YQ áll: illessze be a fenti mezőbe. A GZYL weboldala, a yunlianhouyun.com, kínai nyelvű, és csomagkeresője (小包查询) a GZYL nyomkövetőjét nyitja meg, amely csak egy IP-címen érhető el, domainnév nélkül. Amit ott lát, azt fenntartással kezelje: a GZYL a csomag útjának nagy részét előre rögzíti, így egy ott látható lépés, akár a `Delivered` is, nem mindig valódi beolvasás.

## A GZYL nyomkövetési száma

A GZYL fuvarlevélszámai 14 karakteresek: elöl YL, utána 10 számjegy, a végén YQ. Példák kitalált számjegyekkel:

| Szám (kitalált példa) | Mit jelent |
| --- | --- |
| `YL0000000000YQ` | YL, 10 számjegy és YQ: a GZYL fuvarlevélszámainak formája. A Peek a GZYL nyomkövetőjénél ellenőrzi. |
| `YL7380000000YQ` | YL738, 7 számjegy és YQ: erről a formáról a Peek azonnal felismeri a GZYL-t. |
| `YL000000000YQ` | Csak 9 számjegy: ez nem a GZYL formája. Nézze meg, nem maradt-e ki egy számjegy. |
| `GZXH0000000000YQ` | GZXH, 10 számjegy és YQ: a Xinghe Freight (星和货运), egy másik szállítmányozó száma. Ezeket a csomagokat is a GZYL nyomkövetője mutatja, ugyanazokkal a szövegekkel. |
| `JXCAA0000000000YQ` | Ez is YQ-ra végződik, mint sok kínai szállítmányozó száma, de JXC-vel kezdődik: ez a Juxi Express száma. |

Nem baj, ha a számot szóközökkel vagy kisbetűkkel illeszti be a Peekbe.

**Hol találja a számot?** A szállítást az eladó rendeli meg a GZYL-nél, és ő küldi el Önnek a számot. Ha nincs meg, kérje el az eladótól.

## Mit jelentenek a GZYL státuszai?

A GZYL angolul írja a lépéseit, elírásokkal együtt. A Peek az eredeti szövegükkel mutatja őket, és mindegyik alapján saját állapotot is ad a csomagnak. A Peek felülete angol nyelvű, így ezt az állapotot is angolul látja: „Label created” (címke létrehozva), „Received by carrier” (a szállító átvette), „In transit” (úton), „At customs” (vámkezelés alatt) vagy „Delivered” (kézbesítve). A GZYL lépései abban a sorrendben, ahogy általában megjelennek:

| A GZYL szövege | Mit jelent Önnek? | A Peek ezt mutatja |
| --- | --- | --- |
| `Order information received` | Az eladó elküldte a GZYL-nek a rendelés adatait. A csomag még nincs a GZYL-nél. | „Label created” |
| `Arrive at the cross border receiving point` | Ez az első beolvasás a GZYL-nél: a csomag már náluk van. | „Received by carrier” |
| `Goods leave the operation center` | A csomag elhagyta a GZYL logisztikai központját. | „In transit” |
| `Arrive at the international airport` | A csomag a repülőtéren várja a járatát. | „In transit” |
| `Departing from the airport` | A gép felszállt. | „In transit” |
| `Item arrives at destination airport` | A csomag megérkezett az Ön országába. | „In transit” |
| `local carrier Agent Import customs clearance` | Egy helyi vámügynök intézi a csomag behozatali vámkezelését az Ön országában. | „At customs” |
| `The import customs keep it for inspection` | Az Ön országának vámhatósága ellenőrzi a csomagot. | „At customs” |
| `Customs clearance completed` | Lezárult a vámkezelés, a csomag továbbhalad. | „In transit” |
| `Internationaliy Package received at processing center` | Egy feldolgozóközpont az Ön országában átvette a vámkezelt csomagot. Ez nem új átvétel: a csomag már jóval korábban a GZYL-hez került. | „In transit” |
| `Leave the email processing center` | A csomag elhagyta ezt a feldolgozóközpontot. | „In transit” |
| `Arrival sorting, distribution center` | A csomag egy elosztóközpontban van az Ön országában; a szöveg után a település neve áll. | „In transit” |
| `Leaving the sorting center` | A csomag továbbindul egy válogatóközpontból; a szöveg után itt is a település neve áll. | „In transit” |
| `Package is on the way` | A csomag továbbhalad az Ön országában, a szöveg után egy település neve áll. Nem azt jelenti, hogy a futár már viszi Önhöz. | „In transit” |
| `Delivered` | Kézbesítve; a szöveg után a település neve áll. Lehet, hogy csak terv, nem beolvasás (lásd lent). | „Delivered”, a rögzített menetrendről szóló megjegyzéssel, hacsak a Peek vissza nem tartja (lásd lent) |

A táblázat utolsó négy lépésénél a szöveg után álló települést a Peek a lépés helyeként mutatja.

**Az Egyesült Államokon át.** Megjelenhet a `Los Angeles Processing Center has received it` sor is, utána szögletes zárójelben egy városnévvel: egy amerikai feldolgozóközpont vette át a csomagot a célország felé tartó útján. A Peek ilyenkor „In transit” állapotot mutat.

**Kínai nyelvű sor.** Ha egy rendelésnek még nincs beolvasása, a platform, amelyen a GZYL nyomkövetője fut, magától beírja a `货物电子信息已经收到` sort, ami annyit tesz: az áru elektronikus adatai megérkeztek. Az eladó létrehozta a rendelést, de a csomagot még senki sem olvasta be. A Peek ezt a sort kínaiul mutatja, „Label created” állapottal.

**A China Poston át.** Megjelenhet a `广州处理中心已收寄` is, utána zárójelben a várossal: a China Post (a kínai posta) átvette a csomagot kantoni feldolgozóközpontjában, és a GZYL ezt kínaiul adja tovább. A Xinghe Freight számainál a `香港处理中心已收寄` ugyanez az átvétel, a hongkongi feldolgozóközpontban. A Peek mindkét sort kínaiul mutatja, „Received by carrier” állapottal.

**Időpontok.** A GZYL nyomkövetőjében minden időpont pekingi idő szerint szerepel, az Ön országában történt lépéseké is, időzóna jelölése nélkül. A pekingi 20:00 télen magyar idő szerint 13:00, nyáron 14:00.

**Előre rögzített lépések.** A GZYL a csomag teljes útját előre beírhatja, az átvételtől az Ön országában történő utolsó lépésig, jóval a lépéseknél szereplő időpontok előtt, és a nyomkövető mindegyiket akkor mutatja meg, amikor eljön az ideje. Az így beírt lépés terv, nem az Ön csomagjának beolvasása. Ugyanígy tesz a Meiteshun is, amely ugyanazt a nyomkövető platformot használja. A GZYL terve egy `Delivered` lépéssel zárul, amelyet a platform este, tömegesen ír be, azon a napon, amelyet a lépés mutat: a dátuma a terv utolsó lépését követő nap, az időpontja pekingi munkaidőre esik, bármelyik országba tart is a csomag. A GZYL adatait átvevő nyomkövető szolgáltatások is mutathatják ezeket a lépéseket, így a `Delivered` sem a GZYL nyomkövetőjében, sem egy ilyen szolgáltatásban nem bizonyítja, hogy a csomag megérkezett.

## Ki kézbesíti a GZYL-csomagokat Magyarországon?

A Peek nem tudja, ki kézbesíti a GZYL-csomagokat Magyarországon: a GZYL lépései az Ön országában nem neveznek meg céget, és a Peek a csomag követését egyetlen magyarországi postának vagy futárszolgálatnak sem tudja átadni. Az egyetlen kézbesítő cég, amelynek a Peek át tudja adni egy GZYL-csomag követését, a Swiss Post (a svájci posta), Svájcban és Liechtensteinben.

A GZYL-től a Peek nem talált sem szállítási időket, sem a kézbesítési kísérletekre, az átvevőhelyekre vagy az őrzési időre vonatkozó szabályokat: ezek a kézbesítő cégtől függenek. Kérdezze meg az eladót, melyik cég kézbesíti a rendelését, és milyen számon követheti.

## Nem érkezik a GZYL-csomag, vagy nem frissül a nyomkövetés

**Csak `Order information received` vagy `货物电子信息已经收到`.** Az eladó létrehozta a rendelést, de a GZYL még nem olvasta be a csomagot. Ha egy ilyen lépés után egy nap elteltével sincs újabb, a Peek a „Label created” alá ezt írja: „The sender hasn’t handed it to GZYL yet. Tracking starts when they do.” Vagyis a feladó még nem adta át a csomagot a GZYL-nek, és a követés csak akkor indul, amikor átadja. Ha néhány nap múlva sem történik semmi, kérdezze meg az eladót, valóban elküldte-e a csomagot.

**Elakadt a vámnál.** Ha behozatali költségeket kérnek Öntől, olvassa el a [vámnál feltartott csomagokról szóló útmutatót](guide:customs) (angolul). 2026. november 1-jétől a vámon felül uniós kezelési díjat is fizetni kell: tételsoronként 2 eurót, ugyanúgy számolva, mint a 3 eurós átalányvámot, de a küldemény értékétől függetlenül. Ha a webáruház nem számolta fel a vásárláskor, a vámkezelést intéző posta vagy futárszolgálat kérheti el Öntől. Ha a csomag napokig ott marad, szóljon az eladónak.

**A GZYL nyomkövetője vagy egy nyomkövető szolgáltatás többet mutat, mint a Peek.** A Peek háromféle lépést nem tekint beolvasásnak: azt, amelyet előre, a rajta szereplő időpont előtt írtak be, a GZYL tervét lezáró `Delivered` lépést, és azt, amelynek időpontja későbbi, mint a Peek legutóbbi ellenőrzése (lásd lent). A többletlépések tehát lehetnek a GZYL tervének részei, nem beolvasások. Az előre beírt lépés sosem számít beolvasásnak: nem változtat sem az állapoton, sem a várható dátumon, és nem küld értesítést. Ha a Peek visszatart olyan lépéseket, amelyeket a GZYL előre beírt, a nyomkövetési előzmények a Peekben egy sorral kezdődnek, amíg a csomagot nem kézbesítik vagy vissza nem küldik: „Other sites may show more steps, but they’re only predictions.” Vagyis más oldalak talán több lépést mutatnak, de azok csak előrejelzések. Az utána álló „Show them” (mutasd őket) gombbal szürkén látja ezeket a lépéseket, a legújabbal kezdve, mindegyiket azzal a nappal, amelyet a GZYL adott neki: „Predicted for Mon 12 Oct” (előrejelzés: okt. 12., hétfő). Igazodjon a webáruház által megadott szállítási határidőhöz, és ha lejárt, írjon az eladónak. Bővebben: [csomagkövetés Kínából](guide:tracking-from-china) (angolul).

**`Delivered`, de nem jött semmi.** Lehet, hogy ez a lépés csak a GZYL tervének része, nem valódi beolvasás. Hacsak nem a Swiss Post kézbesíti a csomagot, a Peek a „Delivered” alá ezt írja: „GZYL updates on a fixed schedule, with no signature. If nothing came, ask the seller.” Vagyis a GZYL nyomkövetése rögzített menetrendet követ, és nem mutat aláírást; ha nem érkezett semmi, kérdezze meg az eladót. Írja meg neki, hogy a csomag nem jött meg, és tudja meg tőle, melyik cég kézbesíti, és milyen számon követheti. Bővebben: [ha a követés kézbesítést mutat, de a csomag nem érkezett meg](guide:delivered-not-received) (angolul).

**Nincs találat.** Ha a GZYL nem ismeri a számot, a nyomkövetője csak az Ön számát mutatja a 参考号 (hivatkozási szám) rovatban, minden más mező üres. A Peek ezt a választ úgy érti, hogy a GZYL már nyilvántartja a küldeményt, de még nem olvasta be: tovább ellenőrzi, ugyanolyan gyakran, mint az előre beírt lépéseknél (lásd alább), és a peektracker.com oldalon a „Waiting for GZYL” (várakozás a GZYL-re) állapotot mutatja, anélkül hogy a szám ellenőrzését kérné. Ezért Ön vesse össze a számot karakterről karakterre az eladó üzenetével.

**Elveszett vagy visszaküldött csomag.** A szállítást az eladó rendelte meg a GZYL-nél, ezért visszatérítést vagy új csomagot tőle kérjen, illetve attól a piactértől, ahol vásárolt. Bővebben az [elveszett csomagokról szóló útmutatóban](guide:lost-parcel) (angolul).

## A GZYL elérhetőségei

A 2026. november 4-i állapot szerint a GZYL kínai nyelvű weboldalának kapcsolati oldala (联系我们) egy éjjel-nappal hívható forródrótot, egy WeChat-fiókot és egy értékesítés utáni QQ-fiókot ad meg, de a címzetteknek semmit: ezek a csatornák a GZYL ügyfeleinek, vagyis az eladóknak szólnak. A szállítást az eladó rendelte meg, ezért a rendeléssel kapcsolatban őt kérdezze, ha pedig nem válaszol, azt a piacteret, ahol vásárolt.

## GZYL-csomagok követése a Peekkel

A [Peek](/) felülete angol nyelvű. Ha a szám YL738-cal kezdődik, és 7 számjegy, majd YQ követi, a Peek a formájáról azonnal felismeri a GZYL-t, és ezt mutatja: „Detected carrier” (felismert szállító). A többi, YL-lel kezdődő, 10 számjegyből és YQ-ból álló számnál a forma csak gyenge támpont. Illessze be így is: a Peek több mint 4500 szállítót követ. Hozzáadás közben a Peek megkérdezi a GZYL nyomkövetőjét („Checking tracking services…”, vagyis a nyomkövető szolgáltatások ellenőrzése), és ha a nyomkövető ad találatot a számra, akár azt az üreset is, amelyet egy általa nem ismert számra ad, ezt látja: „GZYL has this parcel.” (a GZYL ismeri ezt a csomagot). Ha nem, nyomja meg a „Change” (módosítás) gombot, és válassza a GZYL-t a „Fits this number” (illik erre a számra) listából; a 广州云链 beírásával is megtalálja. Már hozzáadott csomagnál a „Change carrier” (szállító módosítása) gombot használja. Az eladó hivatkozási száma is működik, de csak akkor, ha a GZYL-t Ön választja ki: nyomja meg a „Change” (vagy a „Change carrier”) gombot, és keressen rá a GZYL-re. A Peek ezután azt a számot is mutatja, amelyen a GZYL a rendelést nyilvántartja, „GZYL tracks this as” (a GZYL ezen a számon követi) felirattal. A kötőjeleket viszont a Peek elhagyja, így a kötőjeles hivatkozási számra nincs találat: ilyenkor kérje el az eladótól az YL…YQ számot.

A Peek közvetlenül a GZYL nyomkövetőjét olvassa, és mutatja az állapotot, minden lépést a helyével együtt, valamint a célországot, ha a GZYL adatai tartalmazzák. Az időpontokat pekingi idő szerint értelmezi, és az eszköze időzónájában jeleníti meg; a peektracker.com oldalon és az iPhone- és Android-alkalmazásban a nyomkövetési előzmények a „Times are in your time zone:” (az időpontok az Ön időzónájában vannak) sorral és az időzóna nevével zárulnak. A GZYL adataiban esetleg szereplő neveket, címet, irányítószámot, súlyt, díjakat, aláírást és kézbesítési igazolást a Peek nem veszi át. Várható kézbesítési napot nem mutat: a GZYL nem ad meg ilyet.

A Peek visszatart minden lépést, amelyet több mint 12 órával a rajta szereplő időpont előtt írtak be, vagy egy ilyen lépéssel azonos másodpercben több mint 6 órával előtte, és minden olyat, amelynek időpontja pekingi idő szerint későbbi, mint az ellenőrzés pillanata. Ha egy rendelésben vannak előre rögzített lépések, a `Delivered` lépést is visszatartja, akkor is, ha a saját napján írták be. Ha a nyomkövető adatfelülete egy számhoz egyetlen lépést sem ad, a Peek helyette a nyomkövető weboldalát olvassa; az oldal nem mutatja, mikor írták be a lépéseket, ezért onnan minden `Delivered` lépést visszatart. A Peek a megtartott lépésekhez igazodik, nem a GZYL adataiban szereplő összesített állapothoz. Ha egy rendelés minden lépését visszatartja, a rendelésnek a Peekben még nincs nyomkövetési előzménye. Ilyenkor a peektracker.com oldalon a „Waiting for GZYL” (várakozás a GZYL-re) állapot látszik, anélkül hogy a Peek a szám ellenőrzését kérné: az előre beírt lépésekből a Peek azt érti, hogy a rendelés már a GZYL-nél van, ugyanúgy, mint egy olyan küldeménynél, amelyet a GZYL nyilvántart, de még nem olvasott be. A Peek tovább kérdezi a GZYL nyomkövetőjét, az iPhone- vagy Android-alkalmazásban, illetve az Ön fiókjában lévő csomagnál az első néhány óra után hatóránként. Fiók nélküli keresésnél az első 6 órában kérdezi, utána akkor, amikor Ön megnyitja a csomag linkjét (legfeljebb hatóránként, az első két nap után legfeljebb naponta egyszer), és amíg az értesítések be vannak kapcsolva, naponta is, a kereséstől számított 30 napig. Ha egy nyomkövető szolgáltatás olyan lépések másolatát mutatja, amelyeket a Peek a GZYL terveként visszatart (a `Delivered` lépést is beleértve), a Peek ezeket a másolatokat is figyelmen kívül hagyja, akkor is, ha a rendelés más lépéseit megtartja. Ha viszont a GZYL nyomkövetője nem érhető el, a Peek nyomkövető szolgáltatásokat kérdez meg helyette, és ezek másolatai a GZYL által előre beírt lépéseket is tartalmazhatják.

A Swiss Post kivételével a Peek nem adja át a követést más cégnek: a GZYL-nél marad. Ha az eladó megadja a kézbesítő cég számát, adja hozzá a Peekhez külön csomagként. A Peek akár 10 percenként ellenőrzi a csomagot, „Out for delivery” (kiszállítás alatt) állapotban pedig akár 2 percenként (aznap, reggel 6-tól helyi idő szerint, vagy ha a cég idősávot ad meg, az idősáv kezdete előtti fél órától, amíg a GZYL vagy a Swiss Post saját nyomkövetője válaszol; nyomkövető szolgáltatásokon keresztül legfeljebb 15 percenként, amíg a csomag nyitva van vagy be vannak kapcsolva az értesítések); a GZYL Peek által ismert lépései közül azonban egyik sem jár ilyen állapottal. Ha két napig nincs új beolvasás (vagy 12 óráig, amíg a csomag „Label created” vagy „Received by carrier” állapotú), a Peek már csak óránként ellenőrzi. Értesítést kérhet minden beolvasásról („Every scan”), csak a fontos lépésekről („Important steps”) vagy csak a kézbesítésről („Delivery only”). Az „Important steps” a GZYL két vámlépéséről és a `Delivered` lépésről értesít, a repülőjáratokról és a válogatásról nem. A Peek nem tud Ön helyett kapcsolatba lépni sem a GZYL-lel, sem az eladóval.

## Gyakori kérdések a GZYL-csomagkövetésről

### Mi az a GZYL (广州云链)?

A GZYL teljes neve 广州云链货运代理有限公司 (Guangzhou Yunlian): szállítmányozó cég a kínai Kantonban. Fuvarlevélszámai YL-lel kezdődnek, ezt 10 számjegy és YQ követi.

### Minden YL…YQ szám a GZYL-é?

Nem feltétlenül. Az YL, 10 számjegy és YQ a GZYL formája, és a Peek katalógusában más szállító nem használja. Az YL738-cal kezdődő számot a Peek már a formája alapján a GZYL-éként kezeli; minden más számnál előbb megkérdezi a GZYL nyomkövetőjét. Ha 9 vagy 11 számjegy van benne, az nem a GZYL formája.

### Miért mutatja a GZYL kézbesítettnek a csomagot, ha nem kaptam meg?

Lehet, hogy ez a GZYL tervének része. A GZYL a csomag útjának nagy részét előre rögzíti, és egy `Delivered` lépéssel zárja; az ilyen lépések tervek, nem beolvasások. A Peek a GZYL nyomkövetőjéből érkező ilyen lépéseket, a `Delivered` lépést is beleértve, nem tekinti beolvasásnak, és ha mégis „Delivered” állapotot mutat, alá is írja, hogy a GZYL nyomkövetése rögzített menetrendet követ. Írja meg az eladónak, hogy a csomag nem érkezett meg, és kérdezze meg, melyik cég kézbesíti.

### Ki kézbesíti a GZYL-csomagokat Magyarországon?

A Peek nem tudja: a GZYL lépései az Ön országában nem neveznek meg céget. Kérdezze meg az eladót, melyik cég kézbesíti a rendelését és milyen számon, majd adja hozzá ezt a számot a Peekhez külön csomagként. A Peek csak Svájcban és Liechtensteinben tudja egy GZYL-csomag követését maga átadni egy kézbesítő cégnek, a Swiss Postnak.

:::sources
- [广州云链货运代理有限公司 (GZYL): főoldal (kínaiul)](http://yunlianhouyun.com/) – a cég neve, kínai nyelvű weboldal, csomagkereső (小包查询) a GZYL nyomkövetőjéhez
- [广州云链货运代理有限公司 (GZYL): 联系我们 (kapcsolat, kínaiul)](http://yunlianhouyun.com/contact.html) – éjjel-nappal hívható forródrót, WeChat és értékesítés utáni QQ
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – a cég neve és más nevei, a domainnév nélküli nyomkövető, az YL…YQ számformák: az YL738 azonnal felismerve, a többi gyenge támpontként, a Xinghe Freight a GZYL nyomkövetőjén és hongkongi átvétele, keresés számra vagy hivatkozási számra és a kötőjelek, a lépések szövegei és állapotaik, a kínai nyelvű sorok, a települések a szövegek után, pekingi idő, az előre rögzített terv és az azt lezáró `Delivered`, a válasz ismeretlen számra, a weboldal az adatfelület helyett, a Swiss Post mint egyetlen átadás, mit mutat, mit tart vissza és mit nem vesz át a Peek
- [NAV: A 3 euró átalányvám és az uniós kezelési díj alkalmazása (PDF)](https://nav.gov.hu/pfile/file?path=/vam/e-kereskedelem/a-3-euro-atalanyvam-alkalmazasa) – uniós kezelési díj 2026. november 1-jétől, tételsoronként 2 euró, mint az átalányvám, értékhatártól függetlenül, a vámon felül; a nyilatkozattevő, például a postai szolgáltató szedi be
:::
