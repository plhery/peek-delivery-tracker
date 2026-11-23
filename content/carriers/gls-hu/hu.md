---
title: GLS Hungary csomagkövetés: számok, automaták, segítség
description: GLS Magyarország csomagkövetés: 10–11 jegyű csomagszámok, FlexDeliveryService, 5 napos automata és CsomagPont őrzés, 40 kg határ és +36 29 886-700 ügyfélszolgálat.
slug: gls-magyarorszag-csomagkovetes
published: 2026-11-23
updated: 2026-11-23
---

A GLS Hungary (*GLS General Logistics Systems Hungary Csomag-Logisztikai Kft.*, székhely: Alsónémedi) az európai GLS csomaglogisztikai hálózat magyarországi vállalata. Belföldön jellemzően másnapi (24 órán belüli) kiszállítást kínál, közel 1000 GLS CsomagPonttal, GLS Csomagautomatákkal és nemzetközi európai fuvarozással webáruházak, vállalatok és eseti feladók (*eCsomag*, CsomagPont) számára. A részletes csomagkövetést és átirányítási lehetőségeket a [GLS Hungary csomagkövetés oldalán](https://gls-group.com/HU/hu/csomagkovetes/) érheted el, vagy beillesztheted a csomagszámot a fenti mezőbe.

## GLS Hungary csomagszámok: 10 és 11 jegyű formátumok

A csomagszámot a webáruház feladási értesítőjében, a FlexDeliveryService e-mailben, a Viber/SMS-üzenetben vagy a futár által hagyott értesítőn találod. A GLS Hungary csomagszámai 10 vagy 11 számjegyből állnak:

| Formátum (kitalált) | Jelentés |
| --- | --- |
| `3482917364` | 10 jegyű belföldi GLS Hungary csomagszám. |
| `03482917364` | 11 jegyű belföldi GLS Hungary csomagszám kezdő nullával. |
| `94829173640` | 11 jegyű nemzetközi GLS csomagszám Magyarországra vagy külföldre. |

A 10 és 11 jegyű számokat más GLS-vállalatok (például [GLS Austria](carrier:gls-at), [GLS Germany](carrier:gls-de), [GLS Poland](carrier:gls-pl) vagy [GLS Italy](carrier:gls-it)) és más futárszolgálatok is használják, ezért a GLS Hungary-t magadnak kell kiválasztanod, vagy illeszd be a `gls-group.com/HU/...` linket. További formátumok: [csomagszám-formátumok](guide:tracking-number-formats).

## Mit jelentenek a GLS Hungary csomagkövetési státuszok

A [gls-group.com/HU/hu/csomagkovetes/](https://gls-group.com/HU/hu/csomagkovetes/) oldalon önmagában a csomagszám az áttekintő állapotot mutatja; a részletes depószkennelésekhez, helyszínekhez, várható kézbesítési naphoz és átadópont-címhez a kézbesítési irányítószám megadása szükséges:

| GLS Hungary állapotkód | Mit jelent számodra | Mit mutat a Peek |
| --- | --- | --- |
| `PREADVICE` | A feladó elektronikusan rögzítette a csomagadatokat, a GLS még nem vette át a csomagot. | „Label created” |
| `INPICKUP` | A GLS átvette a csomagot a feladótól vagy a CsomagPonton. | „Received by carrier” |
| `INTRANSIT` | A csomag úton van a GLS Hungary depói és elosztóközpontjai között (vagy nemzetközi tranzitban). | „In transit” |
| `DELIVERED` | A csomagot kézbesítették a címzettnek, szomszédnak, CsomagPontra vagy Csomagautomatába. | „Delivered” |
| `CANCELED` | A csomagfelvételt törölték. | „Returned to sender” |

## Szállítási idők, súlyhatárok, GLS CsomagPontok és automaták

A [GLS Hungary főoldal](https://gls-group.com/HU/hu/home/), a [GYIK](https://gls-group.com/HU/hu/gyik/) és az [angol GYIK](https://gls-group.com/HU/en/faq/) tájékoztatása alapján:

- **Átfutási idők:** belföldön a felvételt követő **következő munkanapon** (24 órán belül) kézbesít a GLS hétfőtől péntekig munkaidőben (Budapesten szombati kézbesítés is elérhető, emellett feláras *Guaranteed24Service* választható). A szomszédos országokba **24–48 óra**, a távolabbi európai országokba **48–120 óra** (jellemzően 72–120 óra) a sztenderd szállítási idő.
- **Súly- és mérethatárok:** legfeljebb **40 kg** (belföldön és exportnál egyaránt); maximális méret **200 cm hossz × 80 cm szélesség × 60 cm magasság**, és legfeljebb **300 cm körméret + hossz** (`2 × magasság + 2 × szélesség + 1 × leghosszabb oldal`).
- **Sikertelen kézbesítés és szomszédnál történő átadás:** ha nem vagy otthon az első kézbesítéskor, a futár először megpróbálhatja aláírás ellenében egy szomszédnak átadni a csomagot; ha ez nem lehetséges, értesítőt hagy és visszaviszi a csomagot a depóba. Következik a **második kiszállítási kísérlet**, és a címzett kérésére az **5 munkanapos tárolási időn** belül **harmadik kísérlet** is kérhető, mielőtt a csomag visszakerül a feladóhoz.
- **FlexDeliveryService, CsomagPont és Automata:** ha a feladó bekapcsolta a *FlexDeliveryService* szolgáltatást, online választhatsz új kézbesítési napot, más címet, depóbeli átvételt (**5 munkanap**), közel 1000 **GLS CsomagPont** egyikét (**5 munkanap** őrzés) vagy **GLS Csomagautomatát** (**5 nap** őrzés; a nyitókód e-mailben és Viberen, Viber hiányában pedig e-mailben és SMS-ben érkezik).
- **Utánvét kártyával:** a feladó által aktivált *BankCardService* és *CashService* esetén az utánvét bankkártyával is kifizethető (valamint a támogatott CsomagPontokon).
- **EU-n kívüli rendelések:** az EU-n kívülről érkező csomagokra az [EU vámreform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) alapján a 150 euróig érvényes 3 eurós vám és tételenként 2 eurós uniós kezelési díj vonatkozik, ha a webshop nem rendezte a vásárláskor; lásd [vámkezelés alatt álló csomagok](guide:customs).

## Késő, sérült vagy elveszett GLS Hungary csomag

A [GLS Hungary GYIK](https://gls-group.com/HU/hu/gyik/) alapján átvételkor érdemes ellenőrizni a csomagolást:

- **Külső sérülés átvételkor:** ha a doboz láthatóan sérült (például kiszakadt), **megtagadhatod az átvételt**, és a GLS visszaküldi a csomagot a feladónak. Sérült csomagolásnál átvételkor a tartalmat is ellenőrizheted; a futár kárjegyzőkönyvet vesz fel. Ha a csomag kívülről ép volt, és csak kibontás után veszed észre a sérülést, a feladóhoz fordulj.
- **Elmaradt vagy hiányzó csomag:** ellenőrizd a futár értesítőjét (hátha szomszéd vette át) és a FlexDeliveryService e-mailt vagy Viber-üzenetet, majd hívd a címzetti ügyfélszolgálatot (`+36 29 886-700`) a csomagszámmal, és értesítsd a feladót. Lásd még: [miért nem frissül a követés](guide:tracking-not-updating), [kézbesített, de nem érkezett meg](guide:delivered-not-received), [sérült csomag](guide:damaged-parcel), [elveszett csomag](guide:lost-parcel) és [visszaküldve a feladónak](guide:returned-to-sender).

## GLS Hungary ügyfélszolgálat és elérhetőségek

Hivatalos adatok a [Kapcsolat](https://gls-group.com/HU/hu/kapcsolat/), a [Cégadatok](https://gls-group.com/HU/hu/cegadatok/), az [Általános Üzleti Feltételek](https://gls-group.com/HU/hu/altalanos-uzleti-feltetelek/) és a [GYIK](https://gls-group.com/HU/hu/gyik/) oldalairól:

- **Címzetti ügyfélszolgálat:** `+36 29 886-700` (vezetékes tarifával hívható).
- **Feladói ügyfélszolgálat:** `+36 29 88 66 70`.
- **E-mail:** `info@gls-hungary.com`.
- **Online kapcsolatfelvételi űrlap:** [gls-group.com/HU/hu/kapcsolat/](https://gls-group.com/HU/hu/kapcsolat/).
- **Cégadatok:** GLS General Logistics Systems Hungary Csomag-Logisztikai Kft., 2351 Alsónémedi, GLS Európa u. 2.

> **Hivatalos csalási figyelmeztetés:** a [gls-group.com/HU/hu/home/](https://gls-group.com/HU/hu/home/) főoldalon a GLS Hungary figyelmeztet a nevükkel visszaélő adathalász üzenetekre és a `.pdf.img` kiterjesztésű hamis „elektronikus számla” mellékletekre. Soha ne nyiss meg gyanús csatolmányt, és ne fizess ismeretlen linken keresztül — a csomagot közvetlenül a [gls-group.com/HU/hu/csomagkovetes/](https://gls-group.com/HU/hu/csomagkovetes/) oldalon ellenőrizd. Lásd [csaló kézbesítési SMS-ek](guide:delivery-scam-texts).

## GLS Hungary csomag követése a Peek alkalmazásban

A [Peek](/) a beillesztett `gls-group.com/HU/...` és `gls-group.eu/HU/...` linkekből (`match=`, `parcelNumber=` vagy `matchParcelNumber=` paraméterrel) felismeri, hogy GLS Hungary-csomagról van szó. A puszta számot (például `3482917364`, `03482917364` vagy `94829173640`, kitalált számok) a Peek sosem rendeli a GLS Hungary-hez: hozzáadáskor koppints a „Change” gombra, vagy a csomag képernyőjén a „Change carrier” lehetőségre, és válaszd a GLS Hungary-t (a „GLS Magyarország” keresésre is megjelenik).

A Peek a GLS Hungary áttekintő adatait kéri le, ugyanazt, amit a csomagkövető oldal irányítószám nélkül mutat, és ennek állapotát jeleníti meg („Label created”, „Received by carrier”, „In transit”, „Returned to sender”, „Delivered”) a GLS szövegével. Az áttekintésben nincsenek szkennelések, helyszínek, várható kézbesítési nap, sem CsomagPont- vagy Automata-adatok: ezeket a GLS csak a kézbesítési irányítószámmal mutatja, ezért a részletekhez vagy a FlexDeliveryService átirányításhoz nyisd meg a [gls-group.com/HU/hu/csomagkovetes/](https://gls-group.com/HU/hu/csomagkovetes/) oldalt az irányítószámmal.

A Peek egy GLS Hungary-csomagot legfeljebb óránként egyszer ellenőriz, a kézi frissítést is beleértve, sikertelen ellenőrzés után pedig 4 órát vár. Hét nap „Label created” állapot után 6 óránként ellenőriz.

Ha a GLS Hungary állapota négy napja nem változott, és a csomag még „Label created”, „Received by carrier” vagy „In transit” állapotban van, a Peek azzal a nappal jelzi, amikor utoljára változást látott, például: „No tracking update since Mon 12 Oct”. A csomag oldalán ekkor ez áll: „Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking GLS Hungary.”, és egy „Get help from GLS Hungary” hivatkozás a GLS Hungary kapcsolati oldalára visz. Három hét új hír nélkül: „Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking GLS Hungary.”

Választhatsz értesítési beállítást: „Every scan”, „Important steps” vagy „Delivery only”. A Peek csak követi a csomag állapotát, és nem tudja átirányítani a küldeményt vagy felvenni a kapcsolatot a GLS Hungary-val helyetted.

## Gyakori kérdések a GLS Hungary csomagkövetésről

### Mennyi ideig tárolja a GLS Hungary a csomagot CsomagPonton vagy automatában?

GLS CsomagPonton és depóban **5 munkanapig**, GLS Csomagautomatában pedig **5 napig** vehető át a csomag, mielőtt visszaküldik a feladónak.

### Hány kézbesítési kísérletet tesz a GLS Hungary?

Ha az első kísérletnél senki nem veszi át a csomagot (és szomszéd sem tudja átvenni), a GLS értesítőt hagy és második kísérletet tesz; az 5 munkanapos tárolási időn belül kérésre **harmadik kézbesítési kísérlet** is kérhető.

### Mi a GLS Hungary maximális súly- és mérethatára?

A maximális súly **40 kg**, a maximális méret **200 cm (hossz) × 80 cm (szélesség) × 60 cm (magasság)**, legfeljebb **300 cm** körméret + hossz értékkel.

### Mi a GLS Hungary ügyfélszolgálat telefonszáma?

Címzetteknek **`+36 29 886-700`** (feladóknak `+36 29 88 66 70`), e-mail cím: `info@gls-hungary.com`, online űrlap: [gls-group.com/HU/hu/kapcsolat/](https://gls-group.com/HU/hu/kapcsolat/).

:::sources
- [GLS Hungary: Főoldal](https://gls-group.com/HU/hu/home/) – belföldi 24 órás szállítás, nemzetközi idők, közel 1000 CsomagPont, 5 munkanapos CsomagPont / 5 napos automata őrzés és csalási figyelmeztetés
- [GLS Hungary: Csomagkövetés](https://gls-group.com/HU/hu/csomagkovetes/) – nyilvános csomagkövető oldal
- [GLS Hungary: GYIK](https://gls-group.com/HU/hu/gyik/) – szállítási idők, 40 kg / `200×80×60 cm` / `300 cm` határok, FlexDeliveryService, szomszédos átvétel, 2–3 kísérlet, Viber/SMS értesítés és elérhetőségek
- [GLS Hungary: FAQ (angol)](https://gls-group.com/HU/en/faq/) – angol nyelvű GYIK és `+36 29 886-700` / `+36 29 88 66 70` telefonszámok
- [GLS Hungary: Kapcsolat](https://gls-group.com/HU/hu/kapcsolat/) – `info@gls-hungary.com` és online üzenetküldő (a Peek súgólinkje)
- [GLS Hungary: Cégadatok](https://gls-group.com/HU/hu/cegadatok/) – *GLS General Logistics Systems Hungary Csomag-Logisztikai Kft.*, `2351 Alsónémedi, GLS Európa u. 2.`
- [GLS Hungary: Általános Üzleti Feltételek](https://gls-group.com/HU/hu/altalanos-uzleti-feltetelek/) – ÁÜF és panaszkezelési tájékoztató
- [Európai Bizottság: EU vámreform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 3 eurós vám 150 euróig és 2 eurós uniós kezelési díj
- [Universal Parcel Scraper: gls-hu](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/gls-hu) – 10–11 jegyű csomagszámok, `/HU/` linkek, összefoglaló állapotkódok (`PREADVICE`, `INPICKUP`, `INTRANSIT`, `DELIVERED`, `CANCELED`), 60/240 perces frissítés, `Europe/Budapest` időzóna és súgólink
:::
