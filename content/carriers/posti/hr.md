---
title: Posti praćenje pošiljke: JJFI broj, statusi i paketomati
description: Pratite Posti (SmartPosti) pošiljku u Finskoj: JJFI format broja, značenje statusa, rok čuvanja 5 dana u paketomatu, OmaPosti, carina i telefon 0100 5577.
slug: posti-pracenje-posiljke
published: 2026-11-27
updated: 2026-11-27
---

Posti je finski nacionalni poštanski i paketni prijevoznik (a u Estoniji, Latviji i Litvi posluje pod imenom SmartPosti). Svoju `JJFI` pošiljku, preporučenu pošiljku s nastavkom `…FI` ili dolazni inozemni poštanski paket možete pratiti na engleskom na [posti.fi/en/tracking](https://www.posti.fi/en/tracking), u aplikaciji OmaPosti ili tako da zalijepite broj pošiljke u polje iznad. Broj pošiljke (item ID) dobivate od pošiljatelja ili u Postijevoj obavijesti o prispijeću putem OmaPosti aplikacije, SMS-a ili e-pošte.

## Posti broj za praćenje: JJFI item ID

Posti ovaj broj naziva „item ID“ (ponekad „shipment ID“). Za vlastite Posti pakete počinje s `JJFI`. Postijeva web-stranica ima i englesku verziju („EN“ na vrhu stranice).

| Broj (izmišljeni) | Što predstavlja |
| --- | --- |
| `JJFI63482917465283914` | Posti paket: `JJFI` i 17 znamenki, ukupno 21 znak. |
| `RR482917367FI` | Preporučena ili praćena pošiljka poslana iz Finske u međunarodnom formatu: dva slova, devet znamenki, `FI`. |
| `RR482917367CN` | Paket iz inozemstva zadržava broj pošiljatelja. Ovaj završava na `CN` (poslan putem China Posta); Posti ga prati pod istim brojem. |
| `00064382917465283912` | 20-znamenkasti SSCC barkod; `64` nakon prve tri znamenke je finski prefiks. |

Ako je pošiljatelj Postiju dostavio vaš broj telefona, pošiljka se sama pojavljuje u aplikaciji OmaPosti; u suprotnom je dodajte ručno. Postijevo praćenje odgovara isključivo na točan broj, nikad na ime ili adresu.

Kod za preuzimanje i kod za otvaranje paketomata (6 ili 9 znakova) nisu brojevi za praćenje. Broj koji počinje s `JJEE` pripada [Omnivi](carrier:omniva), estonskoj pošti, a broj koji završava na `SE` [PostNordu](carrier:postnord) u Švedskoj. Niste sigurni koji je prijevoznik u pitanju? Pogledajte [formate brojeva za praćenje](guide:tracking-number-formats) (na engleskom).

## Značenje Posti statusa praćenja

Postijevo praćenje prikazuje statuse na engleskom, finskom ili švedskom jeziku ovisno o jeziku stranice, često uz dodatni redak objašnjenja. Ovo su najčešći engleski statusi, otprilike redoslijedom kojim se pojavljuju:

| Što Posti piše | Što to znači za vas |
| --- | --- |
| `We have received information about an upcoming delivery from the sender` | Pošiljatelj je najavio pošiljku. Posti još nema sam paket. |
| `Item received for transport` | Posti je preuzeo pošiljku. |
| `Item is in transport in country of origin.` | Predano u inozemstvu. Još nema procjene datuma dostave. |
| `Item arrived in the destination country.` | Pošiljka je stigla u Finsku i nastavlja kao Posti dostava. |
| `The item is in sorting` | U sortirnom centru. Paketi se obično sortiraju jednom ili dvaput, a češće na duljim rutama. |
| `Item accepted from transport` | Još uvijek u tijeku dostave. Nije isporučeno unatoč formulaciji. |
| `Item is routed to an outlet because the locker was full.` | Odabrani paketomat bio je pun pa pošiljka ide u Posti poslovnicu ili preuzimno mjesto. |
| `Item is in delivery transportation.` | U dostavnom je vozilu na putu prema vama. Peek prikazuje „Out for delivery“. |
| `Item is ready for a pick up` | Čeka na preuzimanje. Obavijest o prispijeću sadrži lokaciju i kod. |
| `We sent the recipient a text message about the item` | Posti vam je poslao SMS. Sama pošiljka se nije pomaknula. |
| `The item has been delivered` | Isporučeno, uključujući i nakon što ste ga preuzeli. |
| `Item delivered to the recipient.` | Uručeno primatelju. |

Status `Item accepted from transport` zbunjuje mnoge korisnike. Posti objašnjava da je riječ o unutarnjoj transportnoj oznaci: dostava je još u tijeku i nitko još nije uručio paket. Skeniranja u inozemstvu u svim jezicima nose oznaku lokacije `ULKOMAILLA` (finsko za „u inozemstvu“).

## Posti paketomati, preuzimanje i propuštene dostave

**Obavijest o prispijeću.** Kad pošiljka stigne u paketomat (Parcel Locker) ili na preuzimno mjesto, Posti šalje podatke i kod za otvaranje u aplikaciji OmaPosti, SMS-om i e-poštom. Preuzmite je u roku od 5 dana. Neke pošiljke čekaju dulje, poput paketa iz inozemstva (obično 14 dana) ili onih čija obavijest dolazi pismom (9 dana), stoga se uvijek vodite zadnjim danom preuzimanja s vaše obavijesti.

**Produženje roka.** U aplikaciji OmaPosti opcija „Extend pickup time“ produžuje rok za do 14 dana od izvornog zadnjeg dana preuzimanja, po cijeni od 1,90 € prema Postijevoj engleskoj stranici odnosno 1,90 € po danu prema finskoj stranici.

**Na paketomatu.** Unesite kod za otvaranje (na paketomatima s tipkovnicom: C, kod, zatim OK). Svatko tko ima kod može preuzeti paket umjesto vas bez osobne iskaznice. Za preporučene i osigurane pošiljke potrebna je punomoć. Pošiljke bez koda za preuzimanje čekaju u Posti poslovnici: ponesite broj pošiljke (item ID) i osobni dokument.

**Zašto je otišao na drugu lokaciju.** Paket koji je prevelik za paketomat, zahtijeva osobno uručenje ili ima pogrešan broj telefona preusmjerava se u Posti poslovnicu.

**Kućna dostava.** Ako Posti ne može uručiti pošiljku na kućnoj adresi, odnosi je na najbliže preuzimno mjesto i obavještava vas u OmaPostiju, SMS-om ili e-poštom. Mali paket (Small parcel) ostavlja se ispred vrata ili u poštanskom sandučiću kad nikoga nema kod kuće. U OmaPostiju možete prebaciti pošiljku na kućnu dostavu samo dok je paket još kod pošiljatelja.

**Promjene.** Samo pošiljatelj može promijeniti adresu. Korisnička služba može uz naknadu premjestiti paket na drugo preuzimno mjesto. Nepreuzeti paket vraća se pošiljatelju.

## Posti paket kasni, izgubljen je, oštećen ili na carini

**Nema novosti.** Dok paket iz inozemstva ne stigne u Finsku, Posti ne može procijeniti kada će stići. Pogledajte [zašto se praćenje ne ažurira](guide:tracking-not-updating) (na engleskom).

**Izgubljen ili kasni.** Ako u praćenju nema traga pošiljci, kontaktirajte Postijevu korisničku službu za zahtjev za provjeru (clarification request); mogu ga zatražiti i pošiljatelj i primatelj. Ako se paket ne pronađe, naknadu traži pošiljatelj jer Posti isplaćuje onoga tko je platio prijevoz, što je gotovo uvijek pošiljatelj. Prema finskom zakonu internetska trgovina odgovara za prijevoz i obično šalje zamjenu. Finsko tijelo za zaštitu potrošača KKV smatra domaći paket izgubljenim 14 dana nakon dogovorenog roka dostave ili 28 dana nakon što ga je prijevoznik preuzeo od pošiljatelja ako rok nije dogovoren.

**Označeno kao isporučeno, ali nema paketa.** Provjerite poštanski sandučić za mali paket, zatim kontaktirajte korisničku službu. Pogledajte [isporučeno, ali nije primljeno](guide:delivered-not-received) (na engleskom).

**Oštećeno.** Recite dostavljaču prilikom uručenja, fotografirajte pošiljku, ispunite Postijev obrazac za oštećenu pošiljku i obavijestite pošiljatelja.

**Carina.** Paket izvan EU-a (ili s područja EU-a izvan porezne zone, poput Ålandskih ili Kanarskih otoka) može zahtijevati carinjenje prije dostave na stranici finske carine ili u OmaPostiju. U Postijevom skladištu čeka 20 dana, nakon čega se vraća. Kad se pri carinjenju plaćaju porezi, Posti naplaćuje naknadu za obradu od 3,10 € (3,30 € u poslovnici), koja se plaća na Postijevoj vlastitoj stranici. Od 1. studenoga 2026. finska carina također naplaćuje 2 € po stavci robe za internetske kupnje. Za kupnje ispod 150 € kod kojih je trgovina naplatila PDV na blagajni (IOSS), carinjenje obavlja pošiljatelj i pri dolasku ne plaćate ništa; ako nedostaju podaci o plaćanju carine i naknade, Posti vraća paket pošiljatelju. Trenutačne naknade navedene su na [Postijevoj stranici o carinjenju](https://www.posti.fi/en/receiving/customs-clearance).

> Posti nikada ne šalje poveznicu za plaćanje carine ili naknade za obradu. Lažna poruka može sadržavati čak i pravi broj za praćenje: izbrišite je bez otvaranja poveznice.

## Posti korisnička služba i kontakt broj

Kako je navedeno na posti.fi:

- **Telefon:** 0100 5577 ili +358 100 5577 iz inozemstva. Pozivi se naplaćuju po tarifi mobilne ili lokalne mreže. Radno vrijeme nalazi se na [Postijevoj stranici korisničke službe](https://www.posti.fi/en/customer-service).
- **Chat:** u aplikaciji OmaPosti.
- **Obrasci:** zahtjev za naknadu štete, prijava oštećene pošiljke i neisporučivi paket (kad ga Posti nije mogao uručiti ni vama ni vratiti pošiljatelju).

Posti ne objavljuje adresu e-pošte za korisničku podršku.

## Praćenje Posti pošiljaka u aplikaciji Peek

[Peek](/) odmah prepoznaje `JJFI` broj kao Postijev. Poveznica za praćenje s posti.fi (`/tracking/`, `/seuranta/` ili `/uppfoljning/` s brojem, uključujući nakon jezičnog prefiksa poput `/en/`) prepoznaje Posti za bilo koji broj. Poštanski broj nije potreban.

Za poštanski broj koji završava na `FI`, Peek prvo provjerava Posti zajedno s Chronopostom i navodi Posti čim potvrdi pošiljku. Ako je Posti još ne poznaje, prikazuje se „Not found at … yet. Peek will keep checking after you add it.“ Kad je dodana u Peek račun, provjerava se svakih sat vremena prvih 6 sati, a zatim sve rjeđe; bez računa provjere staju nakon 6 sati osim ako uključite „Notify me when it’s found“, što održava dnevne provjere do 30 dana od dodavanja. Za SSCC gornjeg formata Peek provjerava Posti, Matkahuolto, Swiss Post Cargo, PostNord i NZ Post. S pogrešnom kontrolnom znamenkom odaberite Posti putem „Change“. Strani broj prati se kod Postija nakon „Change“ ili putem posti.fi poveznice. Inače ga Peek prvo prati kod polazne pošte; kad ta pošta javi da pošiljka ide u Finsku (ili ne navede odredište a pratite iz Finske), Peek s istim brojem provjerava i Posti te, čim Posti prikaže skeniranje barem jednako novo kao polazna pošta, nastavlja praćenje tamo zadržavajući raniju povijest.

U Estoniji, Latviji i Litvi Posti posluje kao SmartPosti: Peekova pretraga prijevoznika pronalazi Posti i pod tim imenom („Also called SmartPosti“), a poveznice za praćenje sa smartposti.ee ili itella.ee (te njihovih .lv i .lt domena) dodjeljuju Posti za bilo koji kod. Peek zadržava Postijev odgovor samo kada točno jedna pošiljka nosi točan upisani kod, pa za SmartPosti kod ponekad neće naći ništa — tada ga provjerite na smartposti.ee.

Peek čita Posti na engleskom i prikazuje svaki status s Postijevim retkom objašnjenja, grad svakog skeniranja, težinu, dimenzije te naziv i adresu preuzimnog mjesta. Dok imate otvorenu pošiljku ili na dan dostave s uključenim obavijestima, provjerava do svakih 10 minuta (svake 2 minute od 6 sati po lokalnom vremenu na dan kada Posti skenira pošiljku na dostavu, dok Postijev vlastiti sustav odgovara), inače svakih sat vremena. Prikazuje svako skeniranje na karti i može vas obavijestiti o promjenama. Kad drugi prijevoznik preuzme dostavu, Peek slijedi tempo tog prijevoznika. Vremena se prikazuju u vremenskoj zoni vašeg uređaja; na peektracker.com te u aplikacijama za iPhone i Android povijest završava s „Times are in your time zone:“ i nazivom zone. Nakon dva dana bez novosti (12 sati dok je status „Label created“ ili „Received by carrier“), Peek provjerava svakih sat vremena. Izostavlja kod za preuzimanje i vašu adresu te ne prikazuje očekivani datum dostave ni pošiljatelja. Broj koji obuhvaća više paketa ne prati se, a stare pošiljke javljaju se kao nepronađene.

## Pitanja o Posti praćenju pošiljaka

### Kako pratiti Posti pošiljku na engleskom jeziku?

Otvorite posti.fi/en/tracking i upišite broj pošiljke (item ID); svi statusi tada se prikazuju na engleskom jeziku.

### Koliko dugo Posti čuva paket u paketomatu?

Posti traži da paket preuzmete u roku od 5 dana, a točan zadnji dan piše u obavijesti o prispijeću. U aplikaciji OmaPosti rok možete produžiti za do 14 dana uz naknadu od 1,90 € (finska stranica navodi 1,90 € po danu).

### Koliko dugo traje dostava Posti pošiljke?

Mali paket (Small parcel) stiže za 2 do 5 radnih dana nakon što ga Posti zaprimi. Za ostale pošiljke Postijevo praćenje prikazuje procijenjeno vrijeme dostave.

### Ima li Posti korisnička služba chat podršku?

Da, chat podrška dostupna je izravno u aplikaciji OmaPosti.

### Što znači „Item accepted from transport“ na Posti praćenju?

To je unutarnja transportna oznaka koja pokazuje da je vaš paket još uvijek na putu kroz Postijevu mrežu — ne znači da je pošiljka već uručena primatelju.

:::sources
- [Posti: Picking up an item](https://www.posti.fi/en/receiving/picking-up-an-item) – JJFI item ID, notices, storage (14 days from abroad, 9 days with a letter notice), extension, lockers, codes, power of attorney, returns, phone, chat
- [Posti: Lähetyksen noutaminen](https://www.posti.fi/vastaanottaminen/lahetyksen-noutaminen) – in Finnish: extension €1.90 a day
- [Posti: Track item](https://www.posti.fi/en/tracking) – status wordings, explanation lines, ULKOMAILLA, languages
- [Posti: Home delivery](https://www.posti.fi/en/receiving/home-delivery) – missed delivery, Small parcel, delivery times, delivered not received
- [Posti: Customs clearance](https://www.posti.fi/en/receiving/customs-clearance) – handling fee, Finnish Customs, €2 fee since 1 November 2026, IOSS clearance by the sender, returns when payment details are missing
- [Posti: Ordering from abroad and customs clearance](https://www.posti.fi/en/receiving/customs-clearance/ordering-from-abroard-and-custom-clearance) – 20 days in the warehouse, OmaPosti
- [Posti: What does Posti compensate for and to whom](https://www.posti.fi/en/customer-service/damage-situations/what-does-posti-compensate-for-and-to-whom) – clarification request, compensation, damage steps
- [Posti: Claim for compensation](https://www.posti.fi/en/customer-service/damage-situations/claim-for-compensation) – claim form
- [Posti: Report a damaged item](https://www.posti.fi/en/customer-service/damage-situations/report-damage) – damage form, notify the sender
- [Posti: Undeliverable parcel](https://www.posti.fi/en/customer-service/damage-situations/undeliverable-parcel) – undeliverable form
- [Posti: Security and phishing messages](https://www.posti.fi/en/customer-service/security-and-phishing-messages) – fake messages, no payment links
- [Posti: Privacy statement for Item Tracking](https://www.posti.fi/en/customer-service/terms-and-statements/data-protection/privacy-statements/privacy-statement-for-item-tracking) – tracking by exact code, call cost
- [Posti: Changes to parcel storage periods](https://www.posti.fi/en/latest-news-at-posti/changes-to-parcel-storage-periods-to-improve-availability-at-posti-parcel-lockers-11-8-2025) – five-day storage
- [Posti: Customer Service](https://www.posti.fi/en/customer-service) – customer service page
- [SmartPosti: Meist (About us)](https://www.smartposti.ee/ariklient/meist) – part of Posti Group, SmartPosti in Latvia and Lithuania
- [SmartPosti: Saadetise jälgimine](https://www.smartposti.ee/saadetise-jalgimine) – SmartPosti's tracking page
- [KKV: Postal and parcel services](https://www.kkv.fi/en/consumer-affairs/telephone-and-internet-subscriptions-postal-and-parcel-services/postal-and-parcel-services/) – when a parcel counts as lost
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – what Peek reads
:::
