---
title: Pošta Rusije (Russian Post) praćenje: RU i 80 brojevi
description: Pratite paket Pošte Rusije (Почта России / Russian Post): RU i 14-znamenkasti 80 brojevi, prijevodi ruskih statusa, carina i predaja stranim poštama.
slug: russian-post-pracenje-posiljke
published: 2026-11-27
updated: 2026-11-27
---

Pošta Rusije (Почта России, Pochta Rossii, pochta.ru, na engleskom Russian Post) nacionalna je poštanska služba Rusije koja dostavlja pakete unutar Rusije te u međunarodnom prometu prema inozemstvu i iz inozemstva. Zalijepite broj koji vam je dao pošiljatelj ili trgovina u polje iznad: `RU` broj ili 14 znamenki koje počinju s `80` za paket poslan Poštom Rusije, odnosno vlastiti broj prijevoznika pošiljatelja za paket poslan u Rusiju. Peek čita praćenje Pošte Rusije na engleskom i može pratiti paket dalje do pošte u vašoj zemlji.

## Broj za praćenje Pošte Rusije: kako izgleda

S izmišljenim znamenkama:

| Broj (izmišljeni) | Što predstavlja |
| --- | --- |
| `RR482910576RU` | Dva slova, devet znamenki i `RU`: međunarodni format koji završava kodom za Rusiju. Posljednja od devet znamenki je kontrolna znamenka. Uz ispravnu kontrolnu znamenku Peek odmah prepoznaje Russian Post ako broj počinje s `C`, `E`, `L` ili `R`, a inače prikazuje „Unknown postal carrier“ (vidi dolje). |
| `80482910573649` | 14 znamenki koje počinju s `80`, pri čemu je zadnja kontrolna znamenka. Peek ga povezuje s Russian Post samo kao slabo podudaranje: DPD, SEUR, BRT, Delhivery i drugi prijevoznici također koriste takav oblik. |
| `80482910573642` | Isti oblik s pogrešnom kontrolnom znamenkom. Peek tada ne predlaže Russian Post nego samo druge prijevoznike: ako je riječ o broju Pošte Rusije, provjerite tipfeler. |
| `RR482910576CN` | Broj koji je izdala druga pošta, ovdje [China Post](carrier:china-post). Peek ga prati kod te pošte, i kada putuje u Rusiju. |

Razmaci i mala slova nisu važni kad lijepite broj u Peek. Ako ga ne možete pronaći, pogledajte [kako pronaći broj za praćenje](guide:find-tracking-number) (na engleskom).

## Značenje statusa praćenja Pošte Rusije

Pošta Rusije piše svoje korake na ruskom jeziku. Peek ih u obliku iz donje tablice čita na sustavu praćenja posrednika Qwintry, koji dio paketa predaje Pošti Rusije — za paket dodan s Qwintryjevim vlastitim brojem ili dodan s `RU` brojem i vođen pod Qwintryjem (vidi „Paketi iz Qwintryja“ dolje). Ondje svaki korak Pošte Rusije ima operaciju poput `обработка` (obrada), zatim redak s pojedinostima i poštanski ured na kraju. Gdje nema retka s pojedinostima, sama operacija predstavlja korak. Otprilike redoslijedom kojim se pojavljuju:

| Što piše Pošta Rusije | Što to znači za vas | Što prikazuje Peek |
| --- | --- | --- |
| `Партионный` | Pošta Rusije preuzela je paket u skupnoj pošiljci (operacija `приём`, prijem). | „In transit“ |
| `экспорт международной почты` | Izvoz međunarodne pošte: paket se otprema iz zemlje u kojoj se nalazi. | „In transit“ |
| `Прибыло на территорию РФ` | Paket je stigao na teritorij Rusije. | „In transit“ |
| `импорт международной почты` | Uvoz međunarodne pošte: paket ulazi u poštanski sustav Rusije. | „In transit“ |
| `приём на таможню` | Predano carini. | „At customs“ |
| `Выпущено таможней` | Pušteno s carine: ponovno na putu. | „In transit“ |
| `Покинуло место международного обмена` | Napustilo ured za međunarodnu razmjenu pošte. | „In transit“ |
| `Сортировка` | Sortiranje. | „In transit“ |
| `Прибыло в сортировочный центр` | Stiglo u sortirni centar. | „In transit“ |
| `Покинуло сортировочный центр` | Napustilo sortirni centar. | „In transit“ |
| `Прибыло в место вручения` | Stiglo u poštanski ured koji će ga uručiti ili izdati. | „In transit“ |
| `Передано почтальону` | Predano poštaru za dostavu. | „Out for delivery“ |
| `Временное отсутствие адресата` | Nije uručeno: niste bili na adresi. | „Delivery attempted“ |
| `Адресат заберет отправление сам` | Nije uručeno jer ćete paket preuzeti sami. | „Delivery attempted“ |
| `Вручение адресату` | Uručeno vama, primatelju. | „Delivered“ |

Za sve ostale pakete Pošte Rusije Peek čita vlastiti sustav praćenja Pošte Rusije, koji korake vraća na engleskom (vidi „Praćenje paketa Pošte Rusije u Peeku“ dolje).

## Kako Pošta Rusije dostavlja

Gornji koraci pokazuju put unutar Rusije: sortirni centri, zatim poštanski ured koji isporučuje paket. Odande ga poštar može donijeti na adresu (`Передано почтальону`). Ako niste bili kod kuće ili paket trebate sami preuzeti, to stoji u koraku. Na Qwintryjevom praćenju Peek kao mjesto koraka prikazuje grad poštanskog ureda. Kada je Peek na ruskom jeziku ili kada ne može smjestiti ured, prikazuje naziv ureda kako ga piše Pošta Rusije, pa znate točno koji ured drži paket.

Peek nije pronašao rokove dostave, rokove čuvanja ni pravila pokušaja dostave koje bi mogao provjeriti na vlastitoj web-stranici Pošte Rusije, stoga ih ova stranica ne navodi. Za paket koji čeka u pošti pogledajte [nepreuzeti paketi](guide:uncollected-parcel) (na engleskom).

**Poslano iz Rusije u drugu zemlju.** Peek ne zna unaprijed koja tvrtka dostavlja paket Pošte Rusije u vašoj zemlji. Za `RU` broj provjerava poštu zemlje koju sustav Pošte Rusije navodi kao odredište i može tamo nastaviti praćenje (vidi dolje).

**Poslano u Rusiju iz druge zemlje.** Peek prati paket pod prijevoznikom koji je izdao broj, putem njegovog praćenja ili servisa za praćenje. Ako to praćenje navede Russian Post kao dostavljača, Peek provjerava i sustav Pošte Rusije te prebacuje paket tamo čim se pojavi skeniranje barem jednako novo kao zadnje kod polaznog prijevoznika. Paket upućen u Rusiju ne prebacuje se samo zbog odredišta.

## Paket Pošte Rusije ne stiže ili je praćenje stalo

**Još nema koraka.** Sustav Pošte Rusije ili servisi za praćenje možda još nemaju broj, ili pošiljatelj još nije predao paket. Peek nastavlja provjeravati. Ako nakon nekoliko dana nema promjene, provjerite broj s pošiljateljem. Više u [zašto se praćenje ne ažurira](guide:tracking-not-updating) (na engleskom).

**Zapeo na carini.** Ako Peek prikazuje „At customs“, paket je na carini. Ova stranica ne navodi carinska pravila ni naknade jer ih Peek nije mogao provjeriti na službenoj stranici. Pogledajte [paketi zadržani na carini](guide:customs) (na engleskom).

**Peek kasni za pochta.ru.** Kad vlastiti sustav Pošte Rusije ne odgovori Peeku, uskače servis za praćenje, koji može kasniti za službenim praćenjem. Usporedite s pochta.ru prije brige.

**Označeno kao isporučeno, ali ništa nije stiglo.** Ako Peek pokazuje „Delivered“, a nemate paket, provjerite u lokalnoj pošti i obavijestite pošiljatelja. Više u [isporučeno, ali nije primljeno](guide:delivered-not-received) (na engleskom).

**Izgubljeno ili vraćeno.** Prvo pitajte pošiljatelja ili trgovinu jer su oni ugovorili dostavu.

## Korisnička služba i kontakt Pošte Rusije

Peek nije mogao učitati web-stranicu Pošte Rusije, stoga ova stranica ne navodi telefon ni e-poštu: Peek objavljuje samo kontakte pročitane sa službenih stranica prijevoznika. Stranice pomoći Pošte Rusije nalaze se na info.pochta.ru. Za paket koji je prestao s kretanjem Peek povezuje stranicu pomoći za praćenje pod „Get help from Russian Post“.

Za narudžbu iz internetske trgovine prvo kontaktirajte trgovinu koja je ugovorila dostavu.

## Praćenje paketa Pošte Rusije u Peeku

[Peek](/) ne može biti siguran da broj pripada Pošti Rusije samo po obliku. Svejedno ga zalijepite: Peek prati više od 4 500 prijevoznika. Tijekom dodavanja prikazuje „Checking tracking services…“.

- **14 znamenki koje počinju s `80`, s valjanom kontrolnom znamenkom.** Peek pita sustav Pošte Rusije zajedno s DPD-om i drugim prijevoznicima čiji brojevi odgovaraju obliku. Ako ga ima samo Russian Post, Peek prikazuje „Russian Post has this parcel.“ i vodi ga ondje. Inače pritisnite „Change“: Russian Post nalazi se pod „Fits this number“ zajedno s DPD-om i ostalim odgovarajućim prijevoznicima, s onima iz vaše zemlje na vrhu. Na već dodanom paketu upotrijebite „Change carrier“.
- **Broj s nastavkom `RU`.** Ako počinje s `C`, `E`, `L` ili `R` i ima ispravnu kontrolnu znamenku, Peek odmah navodi Russian Post. Drugi `RU` broj s ispravnom kontrolnom znamenkom prikazuje se kao „Unknown postal carrier“: pritisnite „Change“ (ili kasnije „Change carrier“) i odaberite Russian Post (pretraga `Почта России` također ga pronalazi). U oba slučaja Peek može pratiti paket do pošte u vašoj zemlji (dolje).

Peek čita vlastiti sustav Pošte Rusije, koji mu odgovara na engleskom. Prikazuje svaki korak riječima Pošte Rusije — uglavnom na engleskom, poput `Sorting`, `Arrived at the local distribution center` ili `Forwarded to the postman` („Out for delivery“) — s vremenom, gradom i zemljom te zemljom odredišta. `Available for pickup at the Post Office` prikazuje se kao „Ready for pickup“, a `Handed over for temporary storage` kao „Needs attention“. Nakon `Returned to the sender` (također „Needs attention“) paket je na putu natrag, a njegova isporuka tada se prikazuje kao „Returned to sender“. Pošta Rusije ne daje očekivani datum dostave. Dok njezin sustav ne prepozna broj, Peek prikazuje „Waiting for Russian Post“, odnosno „Waiting for Russian Post’s first scan“ za `80` broj dodan bez prijevoznika. Kada sustav Pošte Rusije ne odgovori Peeku, Peek redom pita servise za praćenje — ParcelsApp, Ship24 i 17TRACK, a za `RU` broj naposljetku praćenje Svjetske poštanske unije — te prikazuje povijest prvoga koji pronađe paket, riječima tog servisa, što može kasniti za pochta.ru. Servis koji nije znao broj ponovno se pita idući dan.

Za `80` broj dodan bez prijevoznika ili `RU` broj označen kao „Unknown postal carrier“ Peek u prvih 30 dana i prije servisa za praćenje pita i vjerojatne prijevoznike: za `80` broj prvo Russian Post, zatim DPD i druge. Ako jedan prikaže kretanje, Peek prebacuje paket tamo. Ako ste dodali `80` broj bez prijevoznika i servis za praćenje navede Russian Post kao jedinog prijevoznika s datiranim koracima, Peek sam svrstava paket pod Russian Post. Za `RU` broj pod „Unknown postal carrier“ sam navod servisa nije dovoljan: potrebno je da ga prikaže sustav Pošte Rusije ili da ga sami odaberete.

Peek nema izravnu poveznicu na sustav praćenja Pošte Rusije. Ako je uskočio servis za praćenje, poveznica na stranici paketa otvara taj servis, primjerice „Open the ParcelsApp website“. Stranicu pochta.ru otvorite sami.

**Paketi iz Qwintryja.** Ako je Qwintry poslao vaš paket u Rusiju, dodajte njegov Qwintry broj: `QR` i osam znamenki. Peek odmah prepoznaje Qwintry i čita njegovo praćenje, koje nastavlja prikazivati korake Pošte Rusije kad pošta preuzme paket. Kad Qwintry navede Russian Post, paket prikazuje „Delivery with Russian Post“ i `RU` broj, a Peek provjerava i sustav Pošte Rusije: čim taj sustav prikaže skeniranje barem jednako novo kao Qwintryjevo zadnje, Peek nastavlja pratiti paket ondje uz zadržavanje ranijih Qwintryjevih koraka. Dok Pošta Rusije ima samo najavu bez vlastitog skeniranja, stoji „Announced to Russian Post, not received yet“. Qwintryjev sustav prihvaća i `RU` broj paketa koji je predao pošti: ako imate samo njega, a sustav Pošte Rusije ga još nema, pritisnite „Change“ i odaberite Qwintry.

**Pošta u vašoj zemlji.** Za `RU` broj pod Russian Post sustav Pošte Rusije navodi zemlju odredišta, pa Peek s istim `RU` brojem pita i poštu te zemlje ako čita njezino praćenje — primjerice [USPS](carrier:usps), [Royal Mail](carrier:royal-mail), [An Post](carrier:an-post), [Canada Post](carrier:canada-post) i [Australia Post](carrier:australia-post). Za paket označen kao „Unknown postal carrier“ dovoljan je servis za praćenje koji navodi odredišnu poštu s njezinim brojem ili odredišnu zemlju za `RU` broj. Čim odredišna pošta prikaže skeniranje barem jednako novo kao zadnje kod Pošte Rusije ili servisa, Peek nastavlja pratiti paket ondje.

Dok imate otvoren paket ili na dan dostave s uključenim obavijestima i dok sustav Pošte Rusije odgovara, Peek provjerava do svakih 10 minuta, odnosno do svake 2 minute od 6 sati po lokalnom vremenu na dan kad Pošta Rusije skenira paket za dostavu; inače svakih sat vremena. Kad odgovaraju samo servisi za praćenje, provjerava svakih sat vremena, svaka 3 sata kad je zadnje skeniranje staro dan, te do svakih 15 minuta na dan kad je paket na dostavi, dok imate otvoren paket ili su obavijesti uključene. Može vas obavijestiti za „Every scan“, „Important steps“ ili „Delivery only“. Peek ne može kontaktirati Poštu Rusije ili pošiljatelja umjesto vas.

## Pitanja o praćenju Pošte Rusije

### Što je Почта России (Pochta Rossii)?

To je Pošta Rusije (na engleskom Russian Post, transkribirano Pochta Rossii), nacionalna poštanska služba Rusije s web-stranicom pochta.ru.

### Kako izgleda broj za praćenje Pošte Rusije?

Međunarodni broj sastoji se od dva slova, devet znamenki i `RU`, na primjer `RR482910576RU` (izmišljeni). Peek s Poštom Rusije povezuje i 14 znamenki koje počinju s `80` kad je kontrolna znamenka ispravna.

### Što znače ruski statusi poput Прибыло в место вручения ili Выпущено таможней?

Na Qwintryjevom praćenju, gdje Peek prikazuje korake Pošte Rusije na ruskom, `Выпущено таможней` znači pušteno s carine („In transit“), `Прибыло в место вручения` znači da je stiglo u odredišni poštanski ured, `Передано почтальону` znači predano poštaru na dostavu, a `Вручение адресату` znači uručeno primatelju.

### Zašto se koraci u Peeku ne podudaraju potpuno s pochta.ru?

Peek čita sustav Pošte Rusije na engleskom, pa za većinu koraka prikazuje engleske tekstove Pošte Rusije umjesto ruskih. Kad njezin sustav ne odgovori, uskače servis za praćenje vlastitim formulacijama koje mogu kasniti. Ruske korake iz gornje tablice Peek prikazuje za pakete koje prati putem Qwintryja.

### Može li Peek pratiti pakete poslane u Rusiju ili iz nje putem druge pošte?

Da. Za `RU` broj pod Russian Post Peek pita i poštu zemlje odredišta ako čita njezino vlastito praćenje, kao za USPS, Royal Mail, An Post, Canada Post i Australia Post, te nastavlja pratiti paket ondje čim ta pošta prikaže skeniranje barem jednako novo kao zadnje kod Pošte Rusije. Za paket poslan u Rusiju prati polaznog prijevoznika; ako to praćenje navede Poštu Rusije kao dostavljača, Peek provjerava i sustav Pošte Rusije te prebacuje paket ondje čim se pojavi skeniranje barem jednako novo.

:::sources
- [Universal Parcel Scraper: Russian Post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/russian-post) – names, the 80 and RU number shapes with their check digits and weak matches, the tracker Peek reads and its English steps, places, destination and stages, help page
- [Universal Parcel Scraper: Qwintry](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/qwintry) – Russian Post's steps as Qwintry's tracker shows them and their stages, the office as the place, the QR number and the RU numbers it takes, the pochta.ru link
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – RU numbers not starting with C, E, L or R as an unknown postal carrier, carriers asked first, order of tracking services, hand-overs to the post in your country and to Russian Post
:::
