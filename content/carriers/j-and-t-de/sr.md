---
title: J&T Express Germany praćenje: JTDE brojevi i statusi
description: Pratite J&T Express paket u Nemačkoj: kako izgleda JTDE broj, šta znači svaki status i šta da uradite kada pošiljka kasni ili je izgubljena.
slug: jt-express-germany-pracenje-posiljke
published: 2026-11-28
updated: 2026-11-28
---

J&T Express Germany (J&T Express Germany GmbH, Frankfurt na Majni) je nemačka mreža kurirske grupe J&T Express: dostavlja pakete u Nemačkoj. Ako je prodavnica poslala vašu porudžbinu preko J&T-a, broj za praćenje počinje sa `JTDE`: nalepite ga u polje iznad ili ga proverite na [sopstvenom J&T tragaču na jtexpress.de](https://www.jtexpress.de/tracking). Broj ima prodavnica koja je poslala porudžbinu, u svojoj e-poruci o slanju ili na stranici vaše porudžbine.

## Broj za praćenje J&T Express Germany: kako izgleda

U rubrici čestih pitanja J&T navodi da njegovi brojevi za praćenje počinju sa JTDE. Peek poznaje samo jedan oblik za njih: `JTDE` i 12 cifara iza toga, ukupno 16 znakova. Sa izmišljenim ciframa:

| Broj (izmišljen) | Šta je to |
| --- | --- |
| `JTDE482910573649` | `JTDE` i 12 cifara: paket kompanije J&T Express Germany. Peek odmah prepoznaje J&T Express Germany. |
| `JTDE48291057364` | Jedna cifra nedostaje: nije oblik J&T Express Germany i Peek ne prepoznaje nijednog prevoznika. Proverite da li nedostaje cifra. |

Ako broj posle `JTDE` ima jednu cifru manje ili više i nijedan prevoznik ne odgovara za njega, stranica pošiljke na peektracker.com to navodi („This starts like a J&T Express Germany tracking number but is one character short, so it may be mistyped.“ ili „… but has one character too many, so it may be mistyped.“) i traži da ga uporedite sa porukom o slanju.

**Gde ga pronaći.** J&T kaže da vam ga daje pošiljalac i da se nalazi na stranici porudžbine u prodavnici, u e-poruci potvrde porudžbine, u e-poruci ili SMS poruci o slanju, u e-porukama koje najavljuju ili potvrđuju dostavu, kao i na etiketi paketa. Ako ne možete da ga nađete, J&T vas upućuje na pošiljaoca ili korisničku podršku prodajne platforme. Pogledajte i [kako pronaći broj za praćenje](guide:find-tracking-number) (na engleskom).

Tragač kompanije J&T prima do 10 brojeva u jednoj pretrazi, odvojenih razmacima ili zarezima.

**Slični brojevi.** Mreže J&T-a u drugim zemljama imaju sopstvene tragače i brojeve: `JTUK` i 12 cifara je [J&T Express UK](carrier:j-and-t-uk), `JTAU` i 12 cifara je [J&T Express Australia](carrier:j-and-t-au), `JTE` i 12 cifara je [J&T Express Middle East](carrier:j-and-t-middle-east), a `JT` sa 13 cifara koristi J&T u Jugoistočnoj Aziji i u Kini ([J&T Express China](carrier:j-and-t-cn)). Samo `JTDE` pripada nemačkoj mreži. Ako tragač J&T odgovori `Keine Abfrageergebnisse` (`No tracking records yet` na engleskom), ili Peek i dalje pokazuje „Waiting for J&T Express Germany“, uporedite broj sa e-porukom prodavnice ili pitajte prodavnicu.

## Objašnjenje statusa praćenja J&T Express Germany

Tragač J&T daje svako skeniranje kao vreme i jednu rečenicu, bez mesta. Otvara se na nemačkom; prebacite sajt na engleski ili kineski u meniju jezika i skeniranja se ponovo učitavaju na tom jeziku. Peek traži rečenice na engleskom, pa su ovo one koje Peek prikazuje, redosledom kojim paket prolazi kroz njih:

| Status J&T-a | Šta to znači za vas | Peek prikazuje |
| --- | --- | --- |
| `The shipment has been successfully picked up and is being prepared for transportation` | J&T je preuzeo paket od pošiljaoca. | „Received by carrier“ |
| `The shipment arrives at the distribution center and is prepared for transport to the next destination.` | Stigao je u sortirni centar J&T-a. | „In transit“ |
| `The shipment is dispatched from the transit center to the next destination` | Napustio je taj centar ka sledećoj stanici. | „In transit“ |
| `Your package has been loaded and is on its way to you` | U dostavnom vozilu je: na dostavi. | „Out for delivery“ |
| `Delivery failed because the address could not be found` | Vozač nije mogao da pronađe vašu adresu. | „Delivery attempted“ |
| `Delivery failed, customer refused to accept` | Primalac je odbio da primi paket. | „Delivery attempted“ |
| `Your parcel has been delivered to the pickup point. Please check your email for details` | Vozač je ostavio paket na mestu za preuzimanje: J&T vam e-poštom šalje podatke za preuzimanje. | „Ready for pickup“ |
| `Your package has been successfully delivered.` | Dostavljeno. J&T dodaje način (pogledajte ispod). | „Delivered“ |
| `Returns have begun` | Posle neuspele dostave, paket se vraća pošiljaocu. | „Returned to sender“ |
| `The shipment has been returned` | Povrat je završen. | „Returned to sender“ |

Rečenica koja nije u ovoj tabeli obrađuje se po pravilima koja Peek koristi za svakog prevoznika; onu koju Peek ne prepozna prikazuje onako kako ju je J&T napisao i računa kao „In transit“, osim ako je paket već na dostavi ili je prošao tu fazu: tada status ostaje kakav je bio.

Red o utovaru može da se pojavi više puta: J&T ga upisuje za svaku dostavnu turu. Red o dostavi se nastavlja vrstom dostave u zagradama 【】 i završava se redom korisničke podrške. Peek izostavlja taj red podrške, a vrstu dostave zadržava, bez zagrada, samo kada je jedna od ovih šest:

- `Left with neighbor`: kod komšije;
- `Front Door Porch`: kod ulaznih vrata;
- `Front Desk Reception`: na recepciji;
- `Garden Yard`: u bašti ili dvorištu;
- `Mailbox`: u poštanskom sandučetu;
- `Garage`: u garaži.

Tako paket ostavljen kod komšije u Peek-u glasi `Your package has been successfully delivered. The delivery type is Left with neighbor.`.

**Kod kog komšije?** Na J&T tragaču uz to skeniranje stoji `Zustelldetails anzeigen` (`Click to view delivery details` na engleskom sajtu). Kliknite na to i unesite poštanski broj adrese dostave da vidite ime komšije i kućni broj; posle pet pogrešnih poštanskih brojeva morate sačekati pre novog pokušaja. Peek traži podatke samo po broju i prikazuje jedino da je paket ostavljen kod komšije: bez imena i bez adrese. Izostavlja i svako ime ili broj telefona koji red J&T-a sadrži.

## Dostava J&T Express Germany: komšije, sigurna mesta i fotografije

U istorijama koje čuva kod Peek-a, kurir J&T-a utovaruje paket za dostavnu turu, a zatim ga dostavlja, ostavlja na mestu za preuzimanje ili prijavljuje zašto dostava nije uspela. Kod Peek-a ne pokazuje predaju drugom prevozniku: tragač J&T-a prati paket sve do dostave. Vozač na vašim vratima može raditi za jednog od franšiznih partnera J&T-a: sajt J&T-a navodi da traži partnere širom Nemačke čiji timovi dostavljaju pakete.

**Na dostavi.** `Your package has been loaded and is on its way to you` znači da je vaš paket na dostavnoj turi. U tim istorijama to dolazi satima pre dostave, istog jutra. Tragač J&T-a ne daje vremenski okvir ni očekivani dan, pa ni Peek ne može da ih prikaže.

**Podešavanja dostave.** E-poruka sa obaveštenjem o dostavi sadrži link ka [stranici za podešavanja dostave J&T-a](https://www.jtexpress.de/delivery-preference). Potvrdite poštanski broj, a zatim izaberite jedan od načina koje vam J&T nudi: uz potpis, vama lično, komšiji (unosite njegovo ime i da li živi pored ili na istom spratu) ili na recepciji, ili ostavljeno bez potpisa kod ulaznih vrata, u bašti, u poštanskom sandučetu ili u garaži. J&T navodi da vozač poštuje vaš izbor, čime se izbegavaju ponovljene i pogrešne dostave, i da se podaci koje unesete koriste samo za tu dostavu. Ako izaberete mesto bez potpisa, stranica upozorava da je rizik gubitka vaš čim paket bude ostavljen tamo. Ako status paketa više ne dozvoljava izbor, stranica to saopštava.

**Fotografija dostave.** Obaveštenje o privatnosti za dokaz o dostavi J&T-a navodi da vozač fotografiše paket kada vam ga predaje ili ga ostavlja na dogovorenom mestu, kadrirajući samo paket i potreban deo mesta; svako ko se slučajno nađe u kadru automatski se zamućuje. J&T po pravilu briše fotografiju šest meseci posle dostave, može je zadržati dok se ne zatvori prigovor podnet u tom roku i može je pokazati pošiljaocu radi rešavanja prigovora.

**Odbijeno ili nije pronađeno.** Posle `Delivery failed, customer refused to accept`, kurir je u istorijama koje čuva kod Peek-a utovario paket za novu turu sledećeg jutra. Posle `Delivery failed because the address could not be found`, proverite adresu u porudžbini. Ako nešto nedostaje, poput broja stana ili prezimena na interfonu, odmah obavestite prodavnicu: ona je zakazala dostavu sa adresom koju ste joj dali. FAQ J&T-a takođe kaže da se odmah obratite pošiljaocu ili prodajnoj platformi: kada je paket već poslat, da li adresa još može da se promeni zavisi od toga gde se nalazi i od pravila pošiljaoca. Posle neuspele dostave može uslediti `Returns have begun`. Pogledajte [kako promeniti adresu dostave](guide:change-delivery-address) i [propuštene dostave](guide:missed-delivery) (na engleskom).

**Pokušaji i preuzimanje.** Sajt J&T-a ne navodi broj pokušaja dostave, rok čuvanja niti radno vreme dostave, i ne navodi nikakve paket-šopove, paketomate niti depoe gde biste mogli preuzeti paket. Jedan red J&T Express Germany koji kod Peek-a poznaje ipak kaže da je paket dostavljen na mesto za preuzimanje, sa detaljima u e-poruci od J&T-a. Pitajte prodavnicu ili pogledajte poruke o dostavi.

## Paket J&T Express kasni, nedostaje ili se vraća

Prodavnica je izabrala J&T, pa je za online porudžbinu prodavnica vaš prvi kontakt. Po nemačkom zakonu, kada kao potrošač kupujete od firme, prodavac snosi rizik gubitka ili oštećenja u prevozu, osim ako ste sami angažovali prevoznika a prodavac vam tog prevoznika prethodno nije ponudio.

**Ne pomera se.** J&T ne objavljuje rokove dostave za primaoce. Paket koji pokazuje „Received by carrier“ ili „In transit“ bez novog reda četiri dana dobija oznaku u Peek-u sa danom poslednjeg reda („No tracking update since Mon 12 Oct“) i link „Get help from J&T Express Germany“. Ako se ni posle toga ništa ne pomera, pitajte prodavnicu ili pozovite dežurnu liniju J&T-a sa svojim brojem. Više o [praćenju koje se ne ažurira](guide:tracking-not-updating) (na engleskom).

**Još nema praćenja.** Broj koji tragač J&T-a još ne poznaje prikazuje se kao „Waiting for J&T Express Germany“. Prodavnice često naprave broj pre nego što predaju paket. Ako J&T ni dva dana kasnije još ne poznaje broj i ništa drugo nije odgovorilo za njega, Peek prikazuje „After two days, the carrier still doesn’t know this number. Check it, choose another carrier or delete the parcel.“ i Peek ga od tada proverava jednom dnevno, a nedeljno kada prođe sedam dana od dodavanja. Proverite broj sa prodavnicom i pitajte da li je paket zaista poslat.

**Dostavljeno, ali nije primljeno.** Pročitajte red o dostavi u Peek-u: ako navodi mesto, kao što je `Mailbox` ili `Garage`, pogledajte tamo; ako piše `Left with neighbor`, pitajte komšije ili proverite kod kog komšije je na J&T tragaču (pogledajte iznad). FAQ J&T-a takođe traži da proverite da li su članovi porodice, komšija ili osoblje zgrade preuzeli paket. I dalje ga nema? Prijavite to na dežurnoj liniji J&T-a i zatražite od prodavnice povraćaj novca ili novi paket, uz svoj broj za praćenje. Pogledajte i [dostavljeno, ali nije primljeno](guide:delivered-not-received) (na engleskom).

**Izgubljeno, oštećeno ili vraćeno.** Zatražite od prodavnice povraćaj novca ili zamenu, uz svoj broj za praćenje. FAQ J&T-a ne daje obrazac za reklamaciju, rok niti naknadu za primaoce; rokove određuje nemački zakon. Prema § 438 Trgovinskog zakonika (HGB), vidljivo oštećenje prijavite najkasnije pri dostavi (dovoljno je reći vozaču), a skriveno oštećenje u roku od sedam dana od dostave, u tekstualnom obliku, na primer e-porukom za J&T; blagovremeno slanje je dovoljno za poštovanje roka. Ako ne možete da se dogovorite sa J&T-om oko izgubljenog ili oštećenog paketa, možete se obratiti poštanskoj arbitraži agencije Bundesnetzagentur (Schlichtungsstelle Post), koja ne naplaćuje naknadu, saslušava i primaoce i pošiljaoce i traži prijavu preko svog online obrasca. Paketi iz velikih internet prodavnica, međutim, obično putuju pod posebnim uslovima koji isključuju arbitražu; tada se i dalje možete obratiti potrošačkoj službi Bundesnetzagentur-a za poštanska pitanja (Verbraucherservice Post). Pogledajte [izgubljeni paketi](guide:lost-parcel), [oštećeni paketi](guide:damaged-parcel) i [vraćeno pošiljaocu](guide:returned-to-sender) (na engleskom).

## Korisnička podrška i kontakt J&T Express Germany

Kanali kompanije J&T Express Germany, prema [njenoj stranici za kontakt](https://www.jtexpress.de/contact) pročitanoj 14. novembra 2026:

- **Telefon:** +49 6931090650 (069 31090650 unutar Nemačke), od ponedeljka do petka od 8:00 do 20:00, zatvoreno državnim praznicima.
- **E-pošta:** de-jtservice@jtexpress.de.
- **Čet uživo:** „Start Chat“ na engleskim stranicama za kontakt i FAQ, kako je pročitano 3. novembra 2026. (14. novembra nemačke stranice navode „Chat starten“).
- **Fotografije dostave i lični podaci:** de-dpo@jtexpress.de (iz obaveštenja o privatnosti za dokaz o dostavi).
- **Kancelarija:** J&T Express Germany GmbH, Am Giener 2, 55268 Nieder-Olm. Pravno obaveštenje navodi registrovanu adresu: Wiesenhüttenstraße 11, 60329 Frankfurt am Main.

Za online porudžbinu ipak počnite od prodavnice: ona je zakazala dostavu sa J&T-om. Za porudžbinu sa tržišta koristite pomoć na stranici porudžbine. Na paketu koji je prestao da se pomera, Peek-ov link „Get help from J&T Express Germany“ otvara J&T-ovu stranicu za kontakt.

## Praćenje paketa J&T Express Germany uz Peek

[Peek](/) odmah imenuje J&T Express Germany na osnovu `JTDE` broja („Detected carrier“), ukucanog sa razmacima ili bez njih, velikim ili malim slovima, i pita sopstveni J&T tragač samo pomoću broja: bez poštanskog broja i bez naloga. Radi i link www.jtexpress.de/tracking sa `?q=` i brojem; ako je u linku druga vrsta broja, Peek taj broj čita kao da ste ga nalepili samog. Ako to što imate nije `JTDE` broj, nalepite ga svejedno: Peek prati više od 4.500 prevoznika. Izbor J&T Express Germany preko „Change“ za drugačiji oblik ne pomaže: Peek navodi da prevoznik „doesn’t issue numbers like this one“.

Peek prikazuje status, svako skeniranje sa njegovim tačnim vremenom i vreme dostave. J&T daje svako skeniranje kao tačan trenutak, pa ga Peek prikazuje u vremenskoj zoni vašeg uređaja; na peektracker.com i u aplikacijama za iPhone i Android poslednji red ispod istorije je imenuje („Times are in your time zone: …“). Skeniranja dolaze na J&T-ovom engleskom, čak i ako ih na jtexpress.de čitate na nemačkom. J&T ne daje mesto, očekivani dan dostave ni težinu, pa Peek ne prikazuje mapu. Kod veoma duge istorije čuva 100 najnovijih skeniranja. „Open the J&T Express Germany website“, na stranici paketa, otvara vaš broj na J&T tragaču.

Dok J&T ne skenira paket, Peek nastavlja da pita i prikazuje „Waiting for J&T Express Germany“ (ili, ako J&T ni dva dana kasnije još ne poznaje broj, zahtev da ga proverite). Kada J&T tragač nije dostupan ili ne poznaje broj, Peek pita servise za praćenje i prikazuje njihovu istoriju, njihovim rečima. Ako kasnije i J&T tragač i servisi za praćenje svi odgovore bez skeniranja za paket koji ih već ima, Peek zadržava skeniranja koja je već pronašao i, ako to potraje, navodi „The carrier has no new scans for now. The ones already found are kept, and Peek keeps checking.“.

Peek proverava do svakih 10 minuta, a od 6:00 po lokalnom vremenu na dan kada J&T skenira paket kao na dostavi do svaka 2 minuta, dok sopstveni tragač J&T-a odgovara (preko servisa za praćenje na svakih 15 minuta). Posle dva dana bez novog skeniranja (12 sati dok pokazuje „Received by carrier“) proverava na sat vremena. Može da vas obaveštava za „Every scan“, „Important steps“ ili „Delivery only“. Peek ne može umesto vas da kontaktira J&T ili prodavnicu, da promeni adresu niti da zakaže novu dostavu.

## Pitanja o praćenju J&T Express Germany

### Da li je J&T Express Germany pouzdana kompanija?

Da. J&T Express Germany GmbH, registrovana u Frankfurtu na Majni, nemačka je kompanija grupe J&T Express, kurirske grupacije osnovane u Indoneziji 2015. godine i listirane na berzi u Hong Kongu. Nemački poštanski regulator Bundesnetzagentur uvrstio je ovo društvo u svoj registar pružalaca poštanskih usluga 1. septembra 2026. godine. J&T traži franšizne partnere širom Nemačke za dostavu na poslednjoj milji i preuzimanje pošiljki. Svaka rečenica u gornjoj tabeli statusa pročitana je na stvarnim paketima kompanije J&T Express Germany.

### Kod kog komšije je moj J&T paket?

To vam pokazuje tragač J&T-a: ispod skeniranja o dostavi kliknite `Zustelldetails anzeigen` (`Click to view delivery details` na engleskom) i unesite poštanski broj dostave da vidite ime komšije i kućni broj. Peek prikazuje samo `Left with neighbor`, bez imena i bez adrese.

### Da li J&T Express ima paket-šopove u Nemačkoj?

Nemački sajt J&T-a ne navodi nijedan, ali jedan red J&T Express Germany koji Peek poznaje kaže da je paket dostavljen na mesto za preuzimanje, sa detaljima u e-poruci od J&T-a. Na stranici za podešavanja dostave možete zatražiti dostavu vama, komšiji, na recepciju ili na sigurno mesto na vašoj adresi.

### Zašto moj J&T broj za praćenje nije pronađen?

Proverite da li počinje sa `JTDE` i ima 12 cifara iza toga: brojevi drugih mreža J&T-a, poput `JTUK` u Velikoj Britaniji, nisu nemački. Ako je broj tačan, J&T možda još nema paket: njegov tragač odgovara `Keine Abfrageergebnisse` (`No tracking records yet`), a Peek prikazuje „Waiting for J&T Express Germany“. Ako J&T ni dva dana kasnije još ne poznaje broj i ništa drugo nije odgovorilo za njega, Peek traži da ga proverite, izaberete drugog prevoznika ili obrišete paket. Ako J&T ni posle nekoliko dana nema evidenciju, pitajte prodavnicu.

### Kako da kontaktiram J&T Express u Nemačkoj?

Pozovite +49 6931090650 (od ponedeljka do petka, 8:00 do 20:00), koristite čet uživo na jtexpress.de ili pišite na de-jtservice@jtexpress.de. Za online porudžbinu prvo pitajte prodavnicu: ona je zakazala dostavu.

:::sources
- [J&T Express Germany: Track & Trace](https://www.jtexpress.de/tracking) – tragač, do 10 brojeva, jezici, ponovno učitavanje skeniranja, poruka bez zapisa, podaci o komšiji iza poštanskog broja, pet pokušaja
- [J&T Express Germany: Frequently Asked Questions](https://www.jtexpress.de/faq) – JTDE brojevi i gde ih pronaći, dostavljeno ali nije primljeno, pogrešna adresa, čet uživo; bez rokova dostave, broja pokušaja ili pravila reklamacije
- [J&T Express Germany: Delivery Preferences](https://www.jtexpress.de/delivery-preference) – link u e-poruci obaveštenja, provera poštanskog broja, načini sa potpisom i bez njega, rizik na izabranom mestu, manje ponovljenih i pogrešnih dostava, podaci korišćeni samo za tu dostavu
- [J&T Express Germany: Contact Us](https://www.jtexpress.de/contact) – telefon, radno vreme, čet, e-pošta, adresa kancelarije
- [J&T Express Germany: Legal notice](https://www.jtexpress.de/legal-statement) – J&T Express Germany GmbH, Frankfurt na Majni
- [J&T Express Germany: POD Privacy Policy](https://www.jtexpress.de/pod-policy) – fotografije dostave, šest meseci, prigovori, kontakt za zaštitu podataka
- [J&T Express Germany: Become Our Partner](https://www.jtexpress.de/career) – franšizni partneri širom Nemačke za dostavu na poslednjoj milji i preuzimanje
- [J&T Express Germany: About Us](https://www.jtexpress.de/about) – osnovano 2015. u Indoneziji, listiranje u Hong Kongu
- [Universal Parcel Scraper: J&T Express Germany](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/j-and-t-de) – nazivi, JTDE oblik, linkovi za praćenje, rečenice tragača pročitane na stvarnim paketima i njihove faze, dostavne ture, vrste dostave, šta je izostavljeno, stranica pomoći
- [Universal Parcel Scraper: J&T's shared tracker lookup](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/j-and-t-au) – engleske rečenice, tačna vremena skeniranja, bez mesta, očekivanog dana ili težine, izostavljena imena i brojevi telefona, 100 najnovijih skeniranja
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – oblici drugih J&T mreža, bez predaje drugom prevozniku, servisi za praćenje kao rezerva
- [Gesetze im Internet: § 475 BGB](https://www.gesetze-im-internet.de/bgb/__475.html) – rizik prodavca u prevozu
- [Gesetze im Internet: § 438 HGB](https://www.gesetze-im-internet.de/hgb/__438.html) – oštećenje prijavljeno pri dostavi, skriveno oštećenje u roku od sedam dana, tekstualni oblik, blagovremeno slanje je dovoljno
- [Bundesnetzagentur: Anbieterverzeichnis Post](https://www.bundesnetzagentur.de/DE/Fachthemen/Post/Anbieterverzeichnis/PostAnbieterverzeichnis.html) – registar pružalaca poštanskih usluga: J&T Express Germany GmbH, upisana 1. septembra 2026.
- [Bundesnetzagentur: Häufig gestellte Fragen zur Schlichtung Post](https://www.bundesnetzagentur.de/DE/Vportal/Schlichtung/Schlichtung_Post/_FAQ_Schlichtung/faqSchlichtung.html) – primaoci mogu podneti prijavu, online obrazac, bez naknade, posebni uslovi za pakete online prodavnica, Verbraucherservice Post
:::
