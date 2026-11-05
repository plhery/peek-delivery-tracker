---
title: Praćenje J&T pošiljke u Njemačkoj: JTDE broj i statusi
description: Prati paket J&T Expressa u Njemačkoj: kako izgleda JTDE broj za praćenje, što znači svaki status i što učiniti kad paket kasni, izgubi se ili ga nema.
slug: jt-express-njemacka-pracenje-posiljke
published: 2026-11-03
updated: 2026-11-06
---

J&T Express Germany (J&T Express Germany GmbH, Frankfurt na Majni) je njemačka mreža J&T Expressa: dostavlja pakete u Njemačkoj. Ako ti je web-trgovina narudžbu poslala preko J&T-a, broj za praćenje počinje s `JTDE`: zalijepi ga u polje iznad ili ga potraži na [J&T-ovoj stranici za praćenje na jtexpress.de](https://www.jtexpress.de/tracking). Broj ima trgovina koja ti je poslala narudžbu: naći ćeš ga u e-poruci o slanju ili na stranici narudžbe.

## J&T broj za praćenje u Njemačkoj: kako izgleda

Prema J&T-ovim čestim pitanjima, broj pošiljke počinje s JTDE. Peek poznaje samo jedan oblik: `JTDE` i 12 znamenki, ukupno 16 znakova. Primjeri s izmišljenim znamenkama:

| Broj (izmišljen) | Što je to |
| --- | --- |
| `JTDE000000000000` | `JTDE` i 12 znamenki: paket J&T Express Germany. Peek odmah prepoznaje J&T Express Germany. |
| `JTDE00000000000` | Jedna znamenka manje: to nije oblik J&T Express Germany i Peek ne prepoznaje nijednog prijevoznika. Provjeri nedostaje li znamenka. |

**Gdje ga naći.** Prema J&T-u, broj ti daje pošiljatelj, a nalazi se na stranici narudžbe u web-trgovini, u e-poruci s potvrdom narudžbe, u e-poruci ili SMS-u o slanju, u e-porukama koje najavljuju ili potvrđuju dostavu i na naljepnici paketa. Ako ga ne nađeš, J&T te upućuje na pošiljatelja ili na službu za korisnike platforme na kojoj si kupio.

J&T-ova stranica za praćenje prima do 10 brojeva u jednoj pretrazi, odvojenih razmakom ili zarezom.

**Slični brojevi.** J&T-ove mreže u drugim zemljama imaju vlastite sustave za praćenje i brojeve: `JTUK` i 12 znamenki je [J&T Express UK](carrier:j-and-t-uk), `JTAU` i 12 znamenki [J&T Express Australia](carrier:j-and-t-au), `JTE` i 12 znamenki [J&T Express Middle East](carrier:j-and-t-middle-east), a `JT` s 13 znamenki J&T koristi u jugoistočnoj Aziji i u Kini ([J&T Express China](carrier:j-and-t-cn)). Samo `JTDE` pripada njemačkoj mreži. Ako J&T-ova stranica za praćenje javi `Keine Abfrageergebnisse` (na engleskoj verziji `No tracking records yet`) ili Peek i dalje pokazuje "Waiting for the carrier" (čeka se prijevoznik), usporedi broj s onim u e-poruci trgovine ili pitaj trgovinu.

## Što znače J&T statusi u praćenju pošiljke

Svako skeniranje u J&T-ovu sustavu za praćenje sastoji se od vremena i jedne rečenice, bez mjesta. Stranica se otvara na njemačkom i nema hrvatsku verziju; prebaciš li je u izborniku jezika na engleski ili kineski, skeniranja se ponovno učitaju na tom jeziku. Peek od J&T-a traži rečenice na engleskom, pa su ovo one koje vidiš u Peeku, redom kojim se pojavljuju dok paket putuje:

| J&T-ov status | Što to znači za tebe | U Peeku |
| --- | --- | --- |
| `The shipment has been successfully picked up and is being prepared for transportation` | J&T je preuzeo paket od pošiljatelja. | "Received by carrier" (prijevoznik je preuzeo paket) |
| `The shipment arrives at the distribution center and is prepared for transport to the next destination.` | Paket je stigao u J&T-ov sortirni centar. | "In transit" (u prijevozu) |
| `The shipment is dispatched from the transit center to the next destination` | Paket je krenuo iz tog centra prema sljedećem odredištu. | "In transit" |
| `Your package has been loaded and is on its way to you` | Paket je u dostavnom vozilu: u dostavi je. | "Out for delivery" (u dostavi) |
| `Delivery failed because the address could not be found` | Dostavljač nije mogao pronaći tvoju adresu. | "Delivery attempted" (pokušaj dostave) |
| `Delivery failed, customer refused to accept` | Primatelj je odbio preuzeti paket. | "Delivery attempted" |
| `Your package has been successfully delivered.` | Dostavljeno. J&T dodaje i način dostave (vidi niže). | "Delivered" (dostavljeno) |
| `Returns have begun` | Nakon neuspjele dostave paket se vraća pošiljatelju. | "Returned to sender" (vraćeno pošiljatelju) |
| `The shipment has been returned` | Povrat je završen. | "Returned to sender" |

Rečenicu koje nema u tablici Peek tumači po pravilima koja primjenjuje na sve prijevoznike. Onu koju ne prepozna prikazuje onako kako ju je J&T napisao i vodi je kao "In transit" (u prijevozu), osim ako je paket već u dostavi ili dalje od toga: tada status ostaje kakav je bio.

Redak o utovaru može se pojaviti više puta: J&T ga upisuje za svaku dostavnu turu. Redak o dostavi nastavlja se načinom dostave u zagradama 【】 i završava rečenicom o službi za korisnike. Peek tu rečenicu izostavlja, a način dostave zadržava, bez zagrada, samo ako je jedan od ovih šest:

- `Left with neighbor`: kod susjeda;
- `Front Door Porch`: pred ulaznim vratima;
- `Front Desk Reception`: na recepciji;
- `Garden Yard`: u vrtu ili dvorištu;
- `Mailbox`: u poštanskom sandučiću;
- `Garage`: u garaži.

Za paket ostavljen kod susjeda Peek zato prikazuje `Your package has been successfully delivered. The delivery type is Left with neighbor.`

**Kod kojeg susjeda?** Na J&T-ovoj stranici za praćenje ispod tog skeniranja stoji `Zustelldetails anzeigen` (na engleskoj verziji `Click to view delivery details`). Klikni na to i upiši poštanski broj adrese dostave: vidjet ćeš ime susjeda i njegov kućni broj. Nakon pet pogrešnih poštanskih brojeva moraš pričekati prije novog pokušaja. Peek J&T-u šalje samo broj pošiljke i pokazuje jedino da je paket ostavljen kod susjeda: bez imena i bez adrese. Izostavlja i svako ime ili broj telefona koji se nađe u J&T-ovu retku.

## J&T dostava u Njemačkoj: što ti govori praćenje

U povijestima praćenja koje sadrži Peekov kod, J&T-ov dostavljač utovari paket za dostavnu turu, a zatim ga ili dostavi ili javi zašto dostava nije uspjela. Peekov kod ne pokazuje predaju drugom prijevozniku: J&T-ov sustav prati paket sve do tvojih vrata.

**U dostavi.** `Your package has been loaded and is on its way to you` znači da je paket na dostavnoj turi. U tim povijestima taj redak dolazi nekoliko sati prije dostave, istog jutra. J&T-ov sustav ne daje vremenski okvir ni očekivani dan dostave, pa ih ni Peek ne može pokazati.

**Željeni način dostave.** U e-poruci kojom te J&T obavještava o dostavi nalazi se poveznica na J&T-ovu [stranicu za odabir načina dostave](https://www.jtexpress.de/delivery-preference). Potvrdi poštanski broj i odaberi jedan od načina koje ti J&T ponudi: uz potpis, tebi osobno, susjedu (upišeš njegovo ime i stanuje li do tebe ili na istom katu) ili na recepciji; bez potpisa, pred ulaznim vratima, u vrtu ili dvorištu, u poštanskom sandučiću ili u garaži. Prema J&T-u, dostavljač se drži tvog izbora. Odabereš li mjesto bez potpisa, stranica upozorava da rizik gubitka snosiš ti čim paket ondje bude ostavljen. Ako stanje pošiljke više ne dopušta izbor, stranica će ti to reći.

**Fotografija dostave.** Prema J&T-ovoj obavijesti o zaštiti podataka za fotografski dokaz dostave, dostavljač fotografira paket kad ti ga preda ili ga ostavi na dogovorenom mjestu, i to samo paket i onoliko mjesta koliko treba; tko slučajno uđe u kadar, automatski postaje neprepoznatljiv. J&T fotografiju u pravilu briše šest mjeseci nakon dostave, ali je može čuvati do kraja postupka po reklamaciji podnesenoj u tom roku, a pošiljatelju je može pokazati kako bi se reklamacija riješila.

**Odbijeno ili adresa nije pronađena.** Nakon `Delivery failed, customer refused to accept` dostavljač je, u povijestima iz Peekova koda, paket sljedećeg jutra utovario za novu dostavnu turu. Nakon `Delivery failed because the address could not be found` provjeri adresu u narudžbi. Ako nešto nedostaje, primjerice broj stana ili ime na zvonu, odmah javi trgovini: ona je dostavu naručila s adresom koju si joj dao. I J&T-ova česta pitanja savjetuju da se odmah javiš pošiljatelju ili platformi: može li se adresa nakon slanja još promijeniti, ovisi o tome gdje je paket i o pravilima pošiljatelja. Nakon neuspjele dostave može uslijediti `Returns have begun`.

**Pokušaji dostave i preuzimanje.** J&T-ova web-stranica ne navodi koliko puta J&T pokušava dostaviti, koliko dugo čuva paket ni u koje sate dostavlja, a ne navodi ni mjesta za preuzimanje, paketomate ni skladišta gdje bi mogao preuzeti paket. Među retcima J&T Express Germany koje Peekov kod poznaje nijedan ne šalje paket na mjesto za preuzimanje ili u paketomat. Pitaj trgovinu ili pogledaj poruke o dostavi.

## J&T paket kasni, nestao je ili je vraćen

Prijevoznika J&T odabrala je trgovina, pa ti je kod narudžbe preko interneta upravo trgovina prvi kontakt. Prema njemačkom pravu, kad kao potrošač kupuješ od tvrtke, rizik gubitka ili oštećenja na putu snosi prodavatelj, osim ako si prijevoznika sam angažirao, a prodavatelj ti ga prije toga nije naveo (§ 475 BGB).

**Paket stoji.** J&T ne objavljuje rokove dostave za primatelje. Ako paket pokazuje "Received by carrier" ili "In transit", a četiri dana nema novog retka, Peek uz njega ispiše "No tracking update for four days" (četiri dana bez novosti u praćenju) i ponudi poveznicu "Get help from J&T Express Germany" (zatraži pomoć od J&T Express Germany). Ako se ni nakon toga ništa ne pomakne, pitaj trgovinu ili nazovi J&T-ovu službu za korisnike i navedi broj za praćenje. Više u vodiču o [praćenju koje se ne ažurira](guide:tracking-not-updating) (na engleskom).

**Još nema praćenja.** Za broj koji J&T-ov sustav još ne poznaje Peek pokazuje "Waiting for the carrier". Trgovine broj često izrade prije nego što paket predaju prijevozniku. Ako tako ostane nekoliko dana, provjeri broj kod trgovine i pitaj je li paket stvarno poslan.

**Dostavljeno, a paketa nema.** Pročitaj redak o dostavi u Peeku: ako navodi mjesto, kao `Mailbox` ili `Garage`, pogledaj ondje; ako piše `Left with neighbor`, pitaj susjede ili na J&T-ovoj stranici za praćenje provjeri kod kojeg je (vidi gore). J&T-ova česta pitanja savjetuju i da provjeriš je li paket preuzeo netko od ukućana, susjeda ili osoblja zgrade. I dalje ga nema? Prijavi to telefonom J&T-ovoj službi za korisnike i od trgovine zatraži povrat novca ili novi paket, uz broj za praćenje. Pogledaj i vodič o [paketu koji je dostavljen, a nije stigao](guide:delivered-not-received) (na engleskom).

**Izgubljen, oštećen ili vraćen.** Od trgovine zatraži povrat novca ili zamjenu, uz broj za praćenje. J&T-ova česta pitanja ne navode obrazac za reklamaciju, rokove ni odštetu za primatelje: rokove određuje njemački zakon. Prema § 438 njemačkog Trgovačkog zakonika (HGB), vidljivo oštećenje prijavljuješ najkasnije pri dostavi (dovoljno je reći dostavljaču), a skriveno u roku od sedam dana od dostave, u pisanom obliku, primjerice e-porukom J&T-u. Ako se s J&T-om ne dogovoriš oko izgubljenog ili oštećenog paketa, možeš se obratiti tijelu za mirenje u poštanskim sporovima pri Bundesnetzagenturu (Schlichtungsstelle Post), koje je besplatno i prima zahtjeve primatelja jednako kao i pošiljatelja. Paketi velikih web-trgovina ipak obično putuju pod posebnim uvjetima koji isključuju mirenje; tada se možeš obratiti Bundesnetzagenturovoj službi za potrošače u poštanskim pitanjima (Verbraucherservice Post).

## J&T Express Germany: služba za korisnike i kontakt

Kanali J&T Express Germany kako ih je 6. studenoga 2026. navodila njegova [stranica za kontakt](https://www.jtexpress.de/contact):

- **Telefon:** +49 6931090650, od ponedjeljka do petka od 8 do 20 sati, blagdanima ne radi.
- **E-pošta:** de-jtservice@jtexpress.de.
- **Chat:** "Chat starten" ("Start Chat" na engleskoj verziji), na stranici za kontakt i na stranici s čestim pitanjima.
- **Fotografije dostave i osobni podaci:** de-dpo@jtexpress.de (iz obavijesti o fotografiji dostave).
- **Ured:** J&T Express Germany GmbH, Am Giener 2, 55268 Nieder-Olm. U impresumu stoji adresa Wiesenhüttenstraße 11, 60329 Frankfurt am Main.

J&T ne navodi na kojim jezicima odgovara služba za korisnike; stranica je na njemačkom, engleskom i kineskom. Kod narudžbe preko interneta ipak počni od trgovine: ona je dostavu naručila kod J&T-a. Ako si naručio preko neke platforme, koristi pomoć na stranici narudžbe. Kod paketa koji je zapeo, Peekova poveznica "Get help from J&T Express Germany" otvara J&T-ovu stranicu za kontakt.

## Praćenje J&T Express Germany pošiljaka s Peekom

[Peek](/) je na engleskom, ali iz `JTDE` broja odmah prepoznaje J&T Express Germany ("Detected carrier", tj. prepoznati prijevoznik), bez obzira na razmake i na velika ili mala slova. Za upit J&T-ovu sustavu za praćenje Peeku je dovoljan sam broj: ne treba ni poštanski broj ni korisnički račun. Možeš zalijepiti i poveznicu www.jtexpress.de/tracking s `?q=` i brojem; ako je u njoj broj drugog oblika, Peek ga čita kao da si zalijepio samo taj broj. Ako tvoj broj nije `JTDE` broj, svejedno ga zalijepi: Peek prati više od 3.500 prijevoznika. Ni ručni odabir ne pomaže: odabereš li za broj drugog oblika J&T Express Germany pod "Change" (promijeni), Peek javlja da J&T Express Germany "doesn’t issue numbers like this one", tj. ne izdaje ovakve brojeve.

Peek pokazuje status, svako skeniranje s točnim vremenom i vrijeme dostave. J&T za svako skeniranje daje točan trenutak, pa ga Peek pokazuje u vremenskoj zoni tvog uređaja. Skeniranja stižu na engleskom, J&T-ovim riječima, čak i ako ih na jtexpress.de čitaš na njemačkom. J&T ne daje mjesto, očekivani dan dostave ni težinu, pa Peek ne pokazuje kartu. Kod vrlo duge povijesti zadržava 100 najnovijih skeniranja. "Open the J&T Express Germany website" (otvori web-stranicu J&T Express Germany) na stranici paketa otvara tvoj broj na J&T-ovoj stranici za praćenje.

Dok J&T ne skenira paket, Peek pokazuje "Waiting for the carrier" i nastavlja provjeravati. Kad J&T-ov sustav nije dostupan ili ne poznaje broj, Peek pita servise za praćenje i pokazuje njihovu povijest, njihovim riječima.

Peek provjerava paket svakih 10 minuta, a svake 2 minute kad je u dostavi. Ako dva dana nema novog skeniranja (12 sati dok paket pokazuje "Received by carrier"), provjerava ga jednom na sat. Obavijesti ti može slati za "Every scan" (svako skeniranje), "Important steps" (važni koraci) ili "Delivery only" (samo dostava). Peek ne može umjesto tebe kontaktirati J&T ili trgovinu, promijeniti adresu ni dogovoriti novu dostavu.

## Pitanja o praćenju J&T pošiljaka u Njemačkoj

### Je li J&T Express Germany prava dostavna služba?

Da. J&T Express Germany GmbH, sa sjedištem u Frankfurtu na Majni, njemačko je društvo J&T Expressa, grupe za dostavu paketa osnovane 2015. u Indoneziji, čije dionice kotiraju na burzi u Hong Kongu. Njemačko regulatorno tijelo za poštu, Bundesnetzagentur, od 1. rujna 2026. vodi ga u svom registru pružatelja poštanskih usluga. J&T diljem Njemačke traži franšizne partnere za dostavu i preuzimanje paketa na posljednjoj milji. Svaka rečenica iz gornje tablice statusa zabilježena je na stvarnim paketima J&T Express Germany.

### Kod kojeg je susjeda moj J&T paket?

To pokazuje J&T-ova stranica za praćenje: ispod skeniranja o dostavi klikni `Zustelldetails anzeigen` (na engleskom `Click to view delivery details`) i upiši poštanski broj adrese dostave, pa ćeš vidjeti ime susjeda i kućni broj. Peek pokazuje samo `Left with neighbor`, bez imena i adrese.

### Ima li J&T Express u Njemačkoj mjesta za preuzimanje ili paketomate?

J&T-ova njemačka web-stranica ne navodi ni jedno ni drugo, a nijedan redak J&T Express Germany koji Peek poznaje ne šalje paket na mjesto za preuzimanje ili u paketomat. Na stranici za odabir načina dostave možeš zatražiti dostavu sebi, susjedu, na recepciju ili na neko mjesto na svojoj adresi.

### Zašto J&T ne pronalazi moj broj za praćenje?

Provjeri počinje li s `JTDE` i ima li iza toga 12 znamenki: brojevi drugih J&T mreža, primjerice `JTUK` u Ujedinjenom Kraljevstvu, nisu njemački. Ako je broj točan, J&T možda još nema paket: njegova stranica za praćenje tada javlja `Keine Abfrageergebnisse` (`No tracking records yet`), a Peek do prvog skeniranja pokazuje "Waiting for the carrier". Ako to potraje nekoliko dana, pitaj trgovinu.

### Kako kontaktirati J&T Express u Njemačkoj?

Nazovi +49 6931090650 (od ponedjeljka do petka od 8 do 20 sati), piši u chat na jtexpress.de ili na de-jtservice@jtexpress.de. Kod narudžbe preko interneta ipak najprije pitaj trgovinu: ona je naručila dostavu.

:::sources
- [J&T Express Germany: Track & Trace](https://www.jtexpress.de/tracking) – stranica za praćenje, do 10 brojeva, jezici stranice, ponovno učitana skeniranja, odgovor bez rezultata, podaci o susjedu uz poštanski broj, pet pokušaja
- [J&T Express Germany: Frequently Asked Questions](https://www.jtexpress.de/faq) – JTDE brojevi i gdje ih naći, dostavljeno a nije primljeno, pogrešna adresa, chat; bez rokova dostave, pokušaja i pravila za reklamacije
- [J&T Express Germany: Delivery Preferences](https://www.jtexpress.de/delivery-preference) – poveznica u e-poruci, provjera poštanskog broja, načini dostave uz potpis i bez njega, rizik na odabranom mjestu
- [J&T Express Germany: Contact Us](https://www.jtexpress.de/contact) – telefon, radno vrijeme, chat, e-pošta, adresa ureda
- [J&T Express Germany: Legal notice](https://www.jtexpress.de/legal-statement) – J&T Express Germany GmbH, Frankfurt na Majni
- [J&T Express Germany: POD Privacy Policy](https://www.jtexpress.de/pod-policy) – fotografija dostave, šest mjeseci, reklamacije, kontakt za zaštitu podataka
- [J&T Express Germany: Become Our Partner](https://www.jtexpress.de/career) – franšizni partneri za dostavu i preuzimanje na posljednjoj milji
- [J&T Express Germany: About Us](https://www.jtexpress.de/about) – osnovan 2015. u Indoneziji, kotira u Hong Kongu
- [Universal Parcel Scraper: J&T Express Germany](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/j-and-t-de) – nazivi, oblik JTDE, poveznice za praćenje, rečenice sustava za praćenje pročitane na stvarnim paketima i njihove faze, ture dostave, načini dostave, što Peek izostavlja, stranica za pomoć
- [Universal Parcel Scraper: zajednički upit J&T-ovih mreža](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/j-and-t-au) – rečenice na engleskom, točna vremena skeniranja, bez mjesta, očekivanog dana i težine, izostavljena imena i brojevi telefona, 100 najnovijih skeniranja
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – oblici brojeva drugih J&T mreža, bez predaje drugom prijevozniku, servisi za praćenje kao rezerva
- [Gesetze im Internet: § 475 BGB](https://www.gesetze-im-internet.de/bgb/__475.html) – rizik prijevoza kad potrošač kupuje od tvrtke
- [Gesetze im Internet: § 438 HGB](https://www.gesetze-im-internet.de/hgb/__438.html) – oštećenje prijavljeno pri dostavi, skriveno oštećenje u roku od sedam dana, u pisanom obliku
- [Bundesnetzagentur: Anbieterverzeichnis Post](https://www.bundesnetzagentur.de/DE/Fachthemen/Post/Anbieterverzeichnis/PostAnbieterverzeichnis.html) – registar pružatelja poštanskih usluga: J&T Express Germany GmbH, upisan 1. rujna 2026.
- [Bundesnetzagentur: Häufig gestellte Fragen zur Schlichtung Post](https://www.bundesnetzagentur.de/DE/Vportal/Schlichtung/Schlichtung_Post/_FAQ_Schlichtung/faqSchlichtung.html) – zahtjev mogu podnijeti i primatelji, besplatno, posebni uvjeti za pakete web-trgovina, Verbraucherservice Post
:::
