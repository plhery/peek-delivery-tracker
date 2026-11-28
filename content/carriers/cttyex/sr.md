---
title: CTTYEX praćenje: CTTY brojevi, skenovi i pekinško vreme
description: Pratite CTTYEX (诚泰通运) pošiljku iz Kine: format CTTY…CN od 16 znakova, pekinško vreme, greška „查询不到“, predaja lokalnoj dostavi i podrška.
slug: cttyex-tracking
published: 2026-11-28
updated: 2026-11-28
---

Pratite pošiljku iz Kine službe CTTYEX (Shenzhen Chengtai Tongyun International Logistics / 深圳诚泰通运国际物流, koja navodi da je osnovana 2011. godine) tako što ćete u polje iznad ili na stranici [cttyvip888.com](https://www.cttyvip888.com/assets/html/search.html) uneti njen 16-znakovni broj `CTTY` + deset cifara + `CN` (samo velika slova, pošiljke iz poslednja dva meseca). CTTYEX prevozi pakete avionom za prodavce na platformama kao što su Amazon, Wish, Tophatter, VOVA i Shopify i beleži svaki sken — čak i lokalne skenove u vašoj zemlji — po pekinškom vremenu (`UTC+8`), koje Peek pretvara u vremensku zonu vašeg uređaja.

## CTTYEX broj za praćenje: kako izgleda

Sajt CTTYEX-a ne navodi ništa o formatu svojih brojeva, ali brojevi koje njegov sistem za praćenje dodeljuje pošiljkama imaju jedan stalan oblik. Svi primeri u nastavku su izmišljeni:

| Broj | Šta je to |
| --- | --- |
| `CTTY6051827049CN` | CTTY, deset cifara i CN: CTTYEX broj. |
| `CTTY605182704CN` | Samo devet cifara: nije CTTYEX oblik. Nedostaje jedna cifra; ako nijedan prevoznik ne odgovori za njega, njegova stranica na peektracker.com prikazuje „This starts like a CTTYEX tracking number but is one character short, so it may be mistyped.“ |
| `LX482917367CN` | Dva slova, devet cifara i CN: broj službe [China Post](carrier:china-post), a ne CTTYEX-a, uprkos istom završetku CN. |

Peek prihvata broj napisan malim slovima ili sa razmacima. Sajt CTTYEX-a ne prihvata: njegovo polje za pretragu prima samo slova i cifre, a sistem za praćenje pronalazi broj samo ako je upisan velikim slovima, pa ga unesite kao CTTY…CN, bez razmaka.

**Gde da ga pronađete.** Klijenti CTTYEX-a su internet prodavci koji šalju preko njega. Prodavac zakazuje pošiljku i daje vam broj u mejlu o otpremi ili na stranici vaše porudžbine. Ako ne možete da ga pronađete, pogledajte [kako pronaći broj za praćenje (na engleskom)](guide:find-tracking-number).

**Na sajtu CTTYEX-a.** Otvorite 物流查询 (pretraga logistike), unesite po jedan broj u svaki red i pritisnite 提交 (pošalji). Time se otvara praćenje 快件跟踪 (praćenje pošiljki), koje pronalazi samo pošiljke iz poslednja dva meseca.

## Objašnjenje CTTYEX statusa praćenja

Sajt CTTYEX-a ne objašnjava nijedan svoj status. Stranica za praćenje je na kineskom, ali je svaki sken na engleskom, od najnovijeg ka najstarijem, ispod kineskog zbirnog reda sa zemljom odredišta (目的地) i statusom (状态):

| CTTYEX navodi | Šta to znači za vas |
| --- | --- |
| `运输途中` | „U tranzitu“. Može da se prikaže i dok je jedini sken podatak o porudžbini, zato čitajte skenove ispod njega. |
| `查询不到` | „Nije pronađeno“: sistem ne poznaje nijednu pošiljku pod ovim brojem i ne prikazuje nijedan sken. |

Zatim slede koraci CTTYEX-a u Kini, od najstarijeg ka novijem. Nijedan od njih ne navodi mesto:

| CTTYEX navodi | Šta to znači za vas |
| --- | --- |
| `Order information received` | Podaci o porudžbini stigli su CTTYEX-u od prodavca. Sama pošiljka je još kod prodavca. |
| `Arrive at the cross border receiving point` | Pošiljka je sada u rukama CTTYEX-a, na tački u Kini gde prima prekogranične pošiljke. `Arrive at the receiving point` je isti korak. |
| `Goods leave the operation center` | Napustila je operativni centar CTTYEX-a u Kini. |
| `Arrival at domestic internationality airport` | Na aerodromu u Kini, pre leta. `internationality` je način na koji CTTYEX piše ovu reč. |
| `Departure from the airport of origin domestic` | Odletela je iz Kine. |

Posle leta redovi dolaze od agenta CTTYEX-a u vašoj zemlji i mreže koja vrši dostavu:

| CTTYEX navodi | Šta to znači za vas |
| --- | --- |
| `Arrive at the destination international airport` | Pošiljka je sletela u vašu zemlju. `Item arrives at destination airport` je isti korak. |
| `Import customs clearance` | Uvozno carinjenje, napisano iza grada luke ili aerodroma. Pošiljka još nije puštena. |
| `transfer to local acting company International self-operation team` | Predato agentu CTTYEX-a u vašoj zemlji posle carine. |
| `Leave the … processing center` | Napustila je centar za obradu, čiji grad stoji umesto …. |
| `Internationaliy Package received at processing center` | Prvi sken mreže koja dostavlja, a ne novi prijem. `Internationaliy` je način na koji CTTYEX piše ovu reč. |
| `Arrived at logistics carrier Facility In Transit to Next Facility` | U objektu dostavne mreže, na putu ka sledećem objektu. Sistem za praćenje tu mrežu naziva samo „logistics carrier“. |
| `Accepted at logistics carrier Regional Destination Facility` | Prvi sken dostavne mreže u vašem regionu. „Accepted“ ne znači nov početak: CTTYEX ima pošiljku još od Kine. |
| `Arrive at the last mile sorting and distribution center` | U sortnom i distributivnom centru dostavne mreže za završnu isporuku, u vašoj blizini. |
| `Leaving the sorting center` | Napustila je taj sortni centar. |
| `Arrive at the sorting and distribution center` | U sortnom centru, čiji grad sistem navodi posle reči `Arrive`. |
| `package is on the way` | Napisano iza naziva grada; sledi posle skena sortnog centra u tom gradu. Ne znači da je počela ruta dostave. |
| `Delivered` | Dostavna mreža je isporučila pošiljku. Red počinje gradom i poštanskim brojem. |

U SAD tri reda od `Accepted at logistics carrier Regional Destination Facility` do `Leaving the sorting center` počinju gradom, saveznom državom i ZIP kodom. To su formulacije koje je Peek zabeležio iz sistema za praćenje CTTYEX-a; svaki drugi red prikazuje se onako kako ga CTTYEX napiše.

**Svako vreme je pekinško vreme.** Sistem za praćenje CTTYEX-a prikazuje vremena bez vremenske zone, ali svako od njih je po pekinškom vremenu (UTC+8), uključujući i skenove napravljene u vašoj zemlji. Datum u Pekingu menja se u 16:00 UTC. Sken napravljen u Ujedinjenom Kraljevstvu ili Irskoj posle 16:00 zimi, odnosno posle 17:00 leti, nosi datum narednog dana; u Njujorku posle 11:00 zimi ili u podne leti.

## Ko dostavlja CTTYEX pošiljke

CTTYEX preuzima pošiljku u Kini i šalje je avionom. Nakon sletanja njegov agent u vašoj zemlji obavlja uvozno carinjenje, a lokalna mreža vrši dostavu. Ni sajt CTTYEX-a ni njegov sistem za praćenje ne imenuju tu mrežu: sistem je naziva samo „logistics carrier“ i ne daje drugi broj za praćenje. Broj CTTY ostaje jedini koji pratite kroz carinu i lokalne sortne centre.

CTTYEX za primaoce ne objavljuje pravila o broju pokušaja dostave, obaveštenjima o prispeću, mestima preuzimanja niti o tome koliko se paket čuva, a sistem ne daje procenu roka dostave: vodite se procenom prodavnice. Kartica ili poruka lokalnog prevoznika znači da važe njegova pravila; [propuštene dostave (na engleskom)](guide:missed-delivery) objašnjavaju šta da uradite.

## CTTYEX pošiljka ne stiže ili praćenje stoji

**Samo `Order information received`.** CTTYEX ima podatke o porudžbini, ali još nema pošiljku, čak i kada u zbirnom statusu piše `运输途中`. Ako danima nema novog reda, pitajte prodavca da potvrdi da je pošiljka predata.

**Tišina posle `Departure from the airport of origin domestic`.** Ništa novo se ne pojavljuje do sletanja ili uvoznog carinjenja u vašoj zemlji. Pogledajte [zašto praćenje prestaje da se ažurira (na engleskom)](guide:tracking-not-updating) i [praćenje pošiljke iz Kine (na engleskom)](guide:tracking-from-china).

**Zadržano na `Import customs clearance`.** Sistem za praćenje ne navodi ništa o carini ili naknadama i ne daje naziv kompanije, pa ne može da potvrdi SMS ili mejl koji od vas traži uplatu za CTTYEX pošiljku. Prvo pročitajte [lažne poruke o dostavi (na engleskom)](guide:delivery-scam-texts); [pošiljke na carini (na engleskom)](guide:customs) objašnjavaju ostalo.

**`查询不到`.** Proverite broj kod prodavca i unesite ga velikim slovima. Sistem za praćenje takođe uklanja pošiljke starije od dva meseca.

**Izgubljena, oštećena ili vraćena.** Na sajtu CTTYEX-a nema ničega o reklamacijama, naknadi štete ili povraćajima. Ugovor CTTYEX-a je sa prodavcem, pa povraćaj novca ili zamenu obezbeđuje prodavac ili platforma na kojoj ste kupili: [izgubljene pošiljke (na engleskom)](guide:lost-parcel) i [vraćeno pošiljaocu (na engleskom)](guide:returned-to-sender) objašnjavaju postupak.

## CTTYEX kontakt

Stranica CTTYEX-a [联系我们 (kontaktirajte nas)](https://www.cttyvip888.com/assets/html/contact.html) navodi adresu u Fuyongu, okrug Bao'an, Šenžen, i imejl adresu. Ne navodi broj telefona, čet ni kontakt-formu, napisana je na kineskom i nema ništa namenjeno primaocima. Za svoju porudžbinu pišite prodavcu.

## Praćenje CTTYEX pošiljke u Peeku

[Peek](/) prepoznaje CTTYEX na osnovu samog broja: CTTY, deset cifara i CN oblik je koji ne koristi nijedan drugi prevoznik kog Peek prati. Možete da nalepite i link ka sistemu za praćenje na cttyvip888.com koji sadrži broj posle `nos=`.

Peek čita pošiljku sa sistema za praćenje CTTYEX-a: njen status, skenove i zemlju u koju putuje. Kada red počinje gradom, ili gradom i saveznom državom, Peek to postavlja kao mesto skena i uklanja ZIP kod; grad centra za obradu ostaje u tekstu. Peek takođe uklanja brojeve telefona. Dupli redovi prikazuju se jednom, a svaki sken ostaje na engleskom jeziku CTTYEX-a. Svako vreme čita se kao pekinško i prikazuje u vremenskoj zoni vašeg uređaja, tako da se datumi poklapaju sa vašim danom; na peektracker.com i u aplikacijama za iPhone i Android istorija se završava tekstom „Times are in your time zone:“ i nazivom vaše vremenske zone.

Status u Peeku prati najnoviji sken koji razume, a zbirni status samo kada ne razume nijedan. `Order information received` računa se kao „Label created“, dva reda prijemne tačke kao „Received by carrier“, `Import customs clearance` kao „At customs“, `Accepted at logistics carrier Regional Destination Facility` i `Internationaliy Package received at processing center` kao „In transit“, a `Delivered` kao „Delivered“. Red koji Peek ne poznaje tumači se po rečima, pa se red koji navodi da je pošiljka dostavljena računa kao dostavljena.

Sistem CTTYEX-a ne prikazuje procenu roka isporuke, težinu niti naziv usluge i ne imenuje ni kompaniju koja dostavlja ni drugi broj, pa ih nema ni Peek. On nastavlja da proverava sistem CTTYEX-a pod brojem CTTY i nikada ne prebacuje pošiljku na drugog prevoznika.

Dok imate otvorenu pošiljku, ili na dan dostave uz uključena obaveštenja, Peek proverava najčešće na svakih 10 minuta; inače svakih sat vremena. Može da vas obavesti o svakom skenu, samo o važnim koracima ili uz opciju „Delivery only“. Nakon dva dana bez novosti (12 sati dok pošiljka još prikazuje „Label created“ ili „Received by carrier“), Peek proverava svakih sat vremena. Ne može da kontaktira CTTYEX ni prodavca umesto vas.

## Pitanja o CTTYEX praćenju

### Šta je CTTYEX (诚泰通运)?

CTTYEX je Shenzhen Chengtai Tongyun International Logistics (深圳诚泰通运国际物流), prekogranični e-commerce špediter iz Šenžena koji navodi da je osnovan 2011. godine i obavlja čarter letove za SAD, Ujedinjeno Kraljevstvo, Kanadu, Nemačku i Australiju. Njegovi brojevi za praćenje imaju 16 znakova: `CTTY`, deset cifara i `CN`.

### Gde mogu da pratim CTTY…CN broj i zašto mora velikim slovima?

Nalepite ga u polje iznad u Peeku (koji prihvata mala slova i razmake), ili otvorite 物流查询 na [cttyvip888.com](https://www.cttyvip888.com/assets/html/search.html) i unesite ga velikim slovima bez razmaka — sopstveni sistem CTTYEX-a pronalazi samo brojeve `CTTY…CN` upisane velikim slovima.

### Zašto su datumi CTTYEX skenova unapred ili po pekinškom vremenu?

Svako vreme na sistemu CTTYEX-a ispisano je po pekinškom vremenu (UTC+8) bez oznake vremenske zone, čak i za skenove u Evropi ili Severnoj Americi, pa popodnevni sken po lokalnom vremenu može nositi sutrašnji datum. Peek čita svako vreme kao pekinško i pretvara ga u vremensku zonu vašeg uređaja.

### Zašto CTTYEX sistem za praćenje prikazuje 查询不到?

`查询不到` na kineskom znači „nije pronađeno“: proverite da nema greške u kucanju, unesite ceo 16-znakovni broj velikim slovima bez razmaka i imajte u vidu da sistem CTTYEX-a čuva samo pošiljke iz poslednja dva meseca. Ako i dalje ne radi, proverite broj sa prodavcem.

### Ko dostavlja CTTYEX pošiljke i kome da se obratim ako paket nestane?

Nakon uvoznog carinjenja lokalna dostavna mreža dovršava isporuku, iako je CTTYEX naziva samo „logistics carrier“ i ne objavljuje drugi broj. Prvo kontaktirajte prodavca ili platformu na kojoj ste kupili — kineska kontakt stranica CTTYEX-a navodi samo adresu u Šenženu i imejl za pošiljaoce, bez podrške ili stranice za reklamacije primalaca.

:::sources
- [CTTYEX: 深圳诚泰通运国际物流 (home)](https://www.cttyvip888.com/) – naziv kompanije
- [CTTYEX: 关于我们 (about us)](https://www.cttyvip888.com/assets/html/about_us.html) – osnovano 2011, logistika za prodavce na Amazonu, Wishu, Tophatteru, VOVA-i i Shopifyju, čarter letovi za SAD, Ujedinjeno Kraljevstvo, Kanadu, Nemačku i Australiju
- [CTTYEX: 物流查询 (search)](https://www.cttyvip888.com/assets/html/search.html) – jedan broj po redu, samo slova i cifre, dugme za slanje
- [CTTYEX: 快件跟踪 (tracking)](https://www.cttyvip888.com/index/tracknumber/track) – kinesko praćenje koje vodi 诚泰通运国际物流, kolone za odredište i status, pošiljke iz poslednja dva meseca
- [CTTYEX: 联系我们 (contact us)](https://www.cttyvip888.com/assets/html/contact.html) – adresa u Fuyongu, okrug Bao'an, Šenžen, i imejl adresa; bez telefona, četa ili forme
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – prekogranični e-commerce špediter, oblik broja CTTY i značenje CTTY, pretraga velikim slovima, zbirni statusi i engleski skenovi sistema za praćenje, agent u inostranstvu i neimenovana dostavna mreža, svako vreme po pekinškom vremenu, gradovi, savezne države i ZIP kodovi u redovima u inostranstvu, šta Peek prikazuje i izostavlja
:::
