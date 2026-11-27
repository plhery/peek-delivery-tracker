---
title: Vinted Go praćenje pošiljke: statusi, paketomati i kontakt
description: Pratite Vinted Go pošiljku (VGS ili 16-znamenkasti broj): paketomati i trgovine (7 kalendarskih dana), engleski statusi, rok od 25 dana i podrška.
slug: vinted-go-pracenje-posiljke
published: 2026-11-27
updated: 2026-11-27
---

Pratite Vinted Go pošiljku dodirom na gumb za praćenje u razgovoru vaše Vinted narudžbe ili lijepljenjem svog `VGS` ili 16-znamenkastog broja na [vintedgo.com/en/tracking](https://vintedgo.com/en/tracking) ili u polje iznad. Vinted Go (vlastita dostavna mreža Vinteda u Francuskoj, Belgiji, Nizozemskoj, Španjolskoj, Portugalu i Finskoj) dostavlja između paketomata i partnerskih trgovina — čuvajući pošiljke 7 kalendarskih dana od dolaska — dok sve prigovore za nestale pakete (nakon 25 radnih dana) i povrate novca rješava podrška Vinteda.

## Gdje pronaći broj za praćenje Vinted Go pošiljke

U razgovoru o narudžbi na Vintedu možete otvoriti poveznicu na stranicu za praćenje prijevoznika ili kopirati broj za praćenje. Praćenje se na Vintedu može pojaviti i do 48 sati nakon što prodavatelj preda paket.

Vinted Go ne objavljuje službeni format svojih brojeva. Ovo su oblici koje poznaje Peekov popis prijevoznika:

| Broj (izmišljen) | Što predstavlja |
| --- | --- |
| `1748291736548291` | Oblik Vinted Go broja: 16 znamenki koje počinju sa 17 ili 18. [DHL eCommerce](carrier:dhl-ecommerce), TNT i [Evri](carrier:evri) također koriste 16-znamenkaste brojeve, pri čemu Evri samo kada zadnja znamenka prođe njegovu provjeru. |
| `1748291736548292` | 16 znamenki čija zadnja znamenka ne prolazi provjeru, pa ne odgovara obliku Vinted Goa. |
| `VGS4829173654829` | `VGS` i 13 ili 14 znamenki: broj na koji odgovara isključivo Vinted Go praćenje. |
| `KLM4UW8291736548` | 16 znakova: 3 slova, znamenka, `UW`, znamenka i 9 slova ili znamenki. Vinted Go praćenje odgovara i na ovakve kodove. |

Dvije stvari lako je pomiješati:

- **Poveznica za praćenje.** Gumb za praćenje vodi na stranicu oblika `vintedgo.com/en/tracking/1748291736548291`. Ta poveznica najpouzdaniji je znak da paket prevozi Vinted Go.
- **Kod za preuzimanje.** To nije broj za praćenje: stiže e-poštom (a moguće i SMS-om) kada je paket spreman i uvijek počinje velikim slovom.

Ne možete pronaći broj? Pogledajte [kako pronaći broj za praćenje](guide:find-tracking-number) (na engleskom). Poslano drugim prijevoznikom? Vodič za [Vinted praćenje](guide:vinted-orders) (na engleskom) obuhvaća svakog prijevoznika kojeg Vinted koristi.

## Objašnjenje statusa praćenja Vinted Go

Engleska stranica za praćenje Vinted Goa prikazuje najnoviju fazu kao naslov s vremenom iznad vremenske crte svih faza; „Show details“ otvara pojedinačna skeniranja ispod svake faze. Redoslijedom kojim pošiljka prolazi:

| Vinted Go prikazuje | Što to znači za vas |
| --- | --- |
| `Shipment created` | Prodavatelj je izradio naljepnicu. Vinted Go još nema paket. |
| `Parcel was dropped off at the locker` | Prodavatelj je ubacio paket u paketomat (ili `Parcel was dropped off at the parcel shop`). |
| `Shipped` | Naziv faze za tu predaju: paket je sada kod Vinted Goa. |
| `In transit` | Putuje kroz sortirne centre prema vašoj lokaciji. |
| `Parcel is on the way to <country>` | Napustio je mrežu Vinted Goa i nastavlja s drugom mrežom, primjerice Poste Italiane za Italiju. |
| `Ready to collect at the locker` | Čeka vas u paketomatu (ili `Ready to collect at the parcel shop`). Kod za preuzimanje je na putu. |
| `Ready to collect` | Naziv faze za taj korak. Počeo je rok od 7 dana za preuzimanje. |
| `Parcel was collected by the recipient` | Vi ili netko s vašim kodom preuzeo je paket. |
| `Delivered` | Preuzeto: završeno. |
| `Missing from locker` | Paketa nema u njegovom pretincu. Prijavite problem na Vintedu. |
| `Returning to sender` | Vraća se prodavatelju, primjerice zato što ga nitko nije preuzeo u roku od 7 dana. |

`Shipped` znači samo da je prodavatelj predao paket. `Delivered` dolazi tek nakon preuzimanja, ne kada paket stigne u paketomat: ako vaš kod ne radi, FAQ Vinted Goa savjetuje da provjerite piše li u praćenju već `Ready to collect at the locker`.

Stranica Vinted Goa sadrži i oznake poput `Out for delivery`, `Couldn’t be delivered` i `Parcel lost`, koje se prikazuju samo ako pošiljka dospije u tu fazu.

## Vinted Go paketomati i partnerske trgovine: preuzimanje paketa

**Kamo ide pošiljka.** Lokacije Vinted Goa su paketomati, partnerske trgovine (parcel shops) i kvartovska mjesta. Početna stranica navodi Francusku, Belgiju, Nizozemsku, Španjolsku, Portugal i Finsku. Vinted nudi ovu opciju samo kada postoji Vinted Go paketomat blizu i kupca i prodavatelja. Ako je odabrana lokacija zatvorena ili puna, Vinted Go može preusmjeriti paket na obližnju lokaciju i javiti vam gdje čeka čim bude spreman.

**Kada stigne.** Vinted Go šalje e-poštu kad je paket spreman; Vinted ažurira narudžbu, a možete dobiti i SMS. Uvjeti dodaju i podsjetnik 3 dana nakon dolaska paketa.

**Preuzimanje.** Na paketomatu skenirajte ili upišite kod za preuzimanje kako biste otvorili odgovarajući pretinac. U partnerskoj trgovini ili kvartovskoj lokaciji pokažite osoblju kod za preuzimanje ili osobnu iskaznicu. Radno vrijeme nalazi se na karti Vinted Goa.

**Koliko čeka.** Imate 7 kalendarskih dana. Vinted Go ne može produljiti rok niti zaustaviti paket kad krene natrag. Nepreuzeti paket vraća se na mjesto na kojem ga je prodavatelj predao; Vinted tada otkazuje narudžbu i vraća vam novac.

**Dostava na adresu.** U Francuskoj pojedine lokacije nude dostavu Uber kurirom u terminu koji rezervirate putem poveznice koju šalje Vinted. Gdje postoji kućna dostava, uvjeti Vinted Goa predviđaju jedan pokušaj uz zakazani termin i dva bez njega.

**Trajanje dostave.** Uvjeti Vinted Goa predviđaju 3 do 6 radnih dana od predaje do lokacije za preuzimanje.

## Vinted Go praćenje se ne ažurira, izgubljen ili oštećen paket

Rješavate s Vintedom, ne s Vinted Goom: korisnička podrška Vinteda obrađuje izgubljene i oštećene pakete te po potrebi traži istragu od prijevoznika.

**Bez promjene nekoliko dana.** Vinted Go navodi da je uobičajeno da vremenska crta miruje nekoliko dana dok je paket u tranzitu. Više u [zašto se praćenje ne ažurira](guide:tracking-not-updating) (na engleskom).

**Nije stigao.** Vinted Go moli da pričekate 25 radnih dana. Ako paket ni tada ne stigne ili razgovor o narudžbi navodi da je vjerojatno izgubljen, prijavite problem izravno iz tog razgovora.

**Oštećen, pogrešan artikl ili prazan pretinac.** Imate 2 dana od preuzimanja za prijavu na Vintedu; pomoć Vinteda navodi 14 dana za narudžbe od Pro prodavatelja. Ako je paket u paketomatu oštećen, izvadite ga i odmah fotografirajte: pretinac se više neće otvoriti.

**Označeno kao dostavljeno, ali nije primljeno.** Prijavite na Vintedu u roku od 2 dana od oznake dostave. Pogledajte i [dostavljeno, ali nije primljeno](guide:delivered-not-received) (na engleskom).

## Vinted Go kontakt telefon i korisnička podrška

Prema stranicama Vinted Goa, kupcima nije dostupan e-mail ni obrazac, a telefonska linija služi isključivo za pomoć kod paketomata.

- **Problemi s narudžbom** (kašnjenje, gubitak, oštećenje, pogrešan artikl): podrška Vinteda putem narudžbe na vašem Vinted računu.
- **Poteškoće kod paketomata** u Francuskoj, Belgiji, Nizozemskoj ili Španjolskoj: pritisnite gumb „?“ na početnom zaslonu paketomata za prikaz broja i radnog vremena podrške Vinted Goa. Za Nizozemsku i Belgiju FAQ Vinted Goa navodi i broj: +31 20 793 2587, na engleskom (te na francuskom za Belgiju).
- **Poteškoće kod paketomata u Portugalu:** pritisnite „Obter ajuda“ na paketomatu i mobitelom skenirajte QR kod. Otvara se chat uživo s podrškom Vinted Goa svakoga dana od 7:00 do 22:00 po lokalnom vremenu.

Vinted upozorava da se phishing pojavljuje i putem SMS-a; francuska stranica pomoći moli da lažne e-poruke proslijedite na phishing@vinted.com.

## Praćenje Vinted Go pošiljke uz Peek

[Peek](/) čita izravno praćenje Vinted Goa, bez računa ili poštanskog broja: svako skeniranje s vremenom u formulacijama Vinted Goa, poput `Ready to collect at the locker`, grad svakog sortirnog centra i vlastiti naslov („Ready for pickup“, „Delivered“). Prikazuje naziv paketomata ili partnerske trgovine gdje paket čeka te kada i gdje ste ga preuzeli, ali ne i kvartovska mjesta. Adresa, radno vrijeme i rok preuzimanja ostaju na stranici Vinted Goa, a predviđeni dan dostave nije naveden. Paket koji se vraća prodavatelju prikazuje „Returned to sender“.

Peek odmah prepoznaje Vinted Go za brojeve koji počinju s `VGS`. Upišete li samo 16-znamenkasti broj, Peek pita Vinted Go i DHL eCommerce (za neke brojeve i druge prijevoznike) poznaju li ga, imenuje Vinted Go kad potvrdi, a TNT pita tek ako nitko od njih ne odgovori; za kod s `UW` pita Vinted Go. Inače pritisnite „Change“ i odaberite „Vinted Go“ ili zalijepite poveznicu vintedgo.com, koja odmah odabire Vinted Go.

Peek provjerava do svakih 10 minuta, ili svaki sat nakon što prođe 48 sati bez novog skeniranja (12 sati dok paket još prikazuje „Label created“ ili „Received by carrier“), i može vam slati obavijesti o promjenama. Ako Vinted Go preda paket drugoj mreži pod novim kodom, Peek ga može nastaviti pratiti ako tu mrežu čita izravno. Kada zadnjih 13 znakova 16-znamenkastog `UW` koda odgovara kodu paketa [Poste Italiane](carrier:poste-italiane), Peek ga provjerava kod Poste Italiane i nastavlja praćenje ondje čim Poste ima najnovije skeniranje. Peek ne može kontaktirati Vinted ili Vinted Go niti prijaviti problem u vaše ime.

## Pitanja o praćenju Vinted Go pošiljke

### Koliko dugo Vinted Go paketomat ili trgovina čuva moj paket?

7 kalendarskih dana od dolaska (`Ready to collect`). Nepreuzeti paketi vraćaju se na prodavateljevu lokaciju predaje, a Vinted otkazuje narudžbu i vraća vam novac.

### Je li Vinted Go pouzdan?

Da. Vinted Go je vlastita dostavna mreža Vinteda kojom upravlja Vinted Go, UAB iz Vilniusa u Litvi, a posluje u Francuskoj, Belgiji, Nizozemskoj, Španjolskoj, Portugalu i Finskoj.

### Koliko dugo traje dostava s Vinted Goom?

Vinted Go moli kupce da pričekaju do 25 radnih dana prije prijave nestalog paketa na Vintedu; nakon što je paket označen kao dostavljen ili preuzet, imate 2 dana za prijavu problema u razgovoru narudžbe.

### Ima li Vinted Go telefonski broj korisničke podrške?

Samo za pomoć kod paketomata: +31 20 793 2587 u Nizozemskoj i Belgiji, gumb „?“ na zaslonu paketomata u Francuskoj, Belgiji, Nizozemskoj i Španjolskoj, ili „Obter ajuda“ QR chat uživo (7:00–22:00) u Portugalu. Sva pitanja oko narudžbi rješava podrška Vinteda.

### Zašto moje Vinted Go praćenje ne radi?

Praćenje se može pojaviti i do 48 sati nakon predaje; provjerite jeste li unijeli broj za praćenje umjesto koda za preuzimanje (koji počinje velikim slovom) i imajte na umu da Vinted Go prestaje prikazivati praćenje šest mjeseci nakon izrade naljepnice.

:::sources
- [Vinted Go: Frequently Asked Questions](https://vintedgo.com/en/faq?country=fr&region=europe) – kod za preuzimanje, 7 dana, druga obližnja lokacija, 25 radnih dana, prijava u 2 dana, pauze na vremenskoj crti, telefon paketomata
- [Vinted Go: Frequently Asked Questions (Netherlands)](https://vintedgo.com/en/faq?country=nl&region=europe) – telefonska linija paketomata za Nizozemsku i Belgiju
- [Vinted Go: Terms and Conditions for Delivery Services](https://vintedgo.com/en/terms-and-conditions?country=fr&region=europe) – tvrtka, vrste lokacija, pokušaji kućne dostave, 3 do 6 radnih dana, podsjetnik, oštećenje u paketomatu, prigovori preko Vinteda
- [Vinted Go: Shipping service, trusted by millions of Vinted users](https://vintedgo.com/en?country=fr&region=europe) – države, početak 2022.
- [Vinted Go: Track your parcel](https://vintedgo.com/en/tracking) – obrazac za praćenje, jezici stranice, statusi, poruka kad nije pronađeno
- [Vinted: Comment suivre un colis ?](https://www.vinted.fr/help/100) – broj u razgovoru, 48 sati, 25 radnih dana, dostavljeno ali nije primljeno
- [Vinted: Envoi avec Vinted Go](https://www.vinted.fr/help/1110) – rute, Uber Direct kućna dostava, dostupnost
- [Vinted: Récupérer un colis](https://www.vinted.fr/help/1601) – nepreuzeti paket, otkazivanje narudžbe i povrat novca
- [Vinted: Comment utiliser les consignes Vinted Go ?](https://www.vinted.fr/help/1109) – telefonska podrška paketomata, 2 i 14 dana
- [Vinted: Hulp met een Vinted Go locker](https://www.vinted.nl/help/1109) – gumb „?“ u Nizozemskoj
- [Vinted: Ayuda en las taquillas de Vinted Go](https://www.vinted.es/help/1109) – telefonska podrška paketomata u Španjolskoj
- [Vinted: Envio Vinted Go](https://www.vinted.pt/help/1110) – chat uživo paketomata u Portugalu, 7:00 do 22:00
- [Vinted: Le colis n'est pas arrivé](https://www.vinted.fr/help/657) – paketa nema na lokaciji, Vinted kontaktira prijevoznika
- [Vinted: Comment repérer et signaler les tentatives d'hameçonnage et d'escroquerie ?](https://www.vinted.fr/help/628) – phishing putem SMS-a, phishing@vinted.com
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – što Peek čita iz Vinted Goa, ograničenje od šest mjeseci
:::
