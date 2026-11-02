---
title: Praćenje J&T pošiljke u Njemačkoj: JTDE broj i statusi
description: Prati paket J&T Expressa u Njemačkoj: kako izgleda JTDE broj za praćenje, što znači svaki status i što učiniti kad paket kasni, izgubi se ili ga nema.
slug: jt-express-njemacka-pracenje-posiljke
published: 2026-11-11
updated: 2026-11-11
---

J&T Express Germany je njemačka mreža J&T Expressa: dostavlja pakete u Njemačkoj. Ako ti je web-trgovina narudžbu poslala preko J&T-a, broj za praćenje počinje s `JTDE`: zalijepi ga u polje iznad ili ga potraži na J&T-ovoj stranici za praćenje, www.jtexpress.de/tracking. Broj ima trgovina koja ti je poslala narudžbu: naći ćeš ga u e-poruci o slanju ili na stranici narudžbe. Ova se stranica ne oslanja na web-stranicu J&T Express Germany, nego na ono što Peekov kod zna o J&T-ovu sustavu za praćenje i na njemačko pravo.

## J&T broj za praćenje u Njemačkoj: kako izgleda

Broj pošiljke J&T Express Germany sastoji se od `JTDE` i 12 znamenki, ukupno 16 znakova. Nijedan drugi oblik Peek ne poznaje. Primjeri s izmišljenim znamenkama:

| Broj (izmišljen) | Što je to |
| --- | --- |
| `JTDE000000000000` | `JTDE` i 12 znamenki: paket J&T Express Germany. Peek odmah prepoznaje J&T Express Germany. |
| `JTDE00000000000` | Jedna znamenka manje: to nije oblik J&T Express Germany i Peek ne prepoznaje nijednog prijevoznika. Provjeri nedostaje li znamenka. |

**Gdje ga naći.** U e-poruci trgovine o slanju, na stranici narudžbe (u trgovini ili na platformi na kojoj si kupio) ili u bilo kojoj poruci o dostavi. Ako ga ne nađeš, pitaj trgovinu.

**Slični brojevi.** J&T-ove mreže u drugim zemljama imaju vlastite sustave za praćenje i brojeve: `JTUK` i 12 znamenki je [J&T Express UK](carrier:j-and-t-uk), `JTAU` i 12 znamenki [J&T Express Australia](carrier:j-and-t-au), `JTE` i 12 znamenki [J&T Express Middle East](carrier:j-and-t-middle-east), a `JT` s 13 znamenki J&T koristi u jugoistočnoj Aziji i u Kini ([J&T Express China](carrier:j-and-t-cn)). Samo `JTDE` pripada njemačkoj mreži. Ako Peek i dalje pokazuje "Waiting for the carrier" (čeka se prijevoznik), usporedi broj s onim u e-poruci trgovine ili pitaj trgovinu.

## Što znače J&T statusi u praćenju pošiljke

Svako skeniranje u J&T-ovu sustavu za praćenje sastoji se od vremena i jedne rečenice, bez mjesta. Peek od J&T-a traži rečenice na engleskom, pa su ovo one koje vidiš u Peeku, redom kojim se pojavljuju dok paket putuje:

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

**Kod kojeg susjeda?** Peek pokazuje samo da je paket ostavljen kod susjeda: bez imena i bez adrese. Izostavlja i svako ime ili broj telefona koji se nađe u J&T-ovu retku.

## J&T dostava u Njemačkoj: što ti govori praćenje

U povijestima praćenja koje sadrži Peekov kod, J&T-ov dostavljač utovari paket za dostavnu turu, a zatim ga ili dostavi ili javi zašto dostava nije uspjela. Peekov kod ne pokazuje predaju drugom prijevozniku: J&T-ov sustav prati paket sve do tvojih vrata.

**U dostavi.** `Your package has been loaded and is on its way to you` znači da je paket na dostavnoj turi. U tim povijestima taj redak dolazi nekoliko sati prije dostave, istog jutra. J&T-ov sustav ne daje vremenski okvir ni očekivani dan dostave, pa ih ni Peek ne može pokazati.

**Odbijeno ili adresa nije pronađena.** Nakon `Delivery failed, customer refused to accept` dostavljač je, u povijestima iz Peekova koda, paket sljedećeg jutra utovario za novu dostavnu turu. Nakon `Delivery failed because the address could not be found` provjeri adresu u narudžbi. Ako nešto nedostaje, primjerice broj stana ili ime na zvonu, odmah javi trgovini: ona je dostavu naručila s adresom koju si joj dao. Nakon neuspjele dostave može uslijediti `Returns have begun`.

**Pokušaji dostave, gdje ostaviti paket i preuzimanje.** J&T-ova web-stranica ne može poslužiti kao izvor za ovu stranicu, pa ova stranica ne navodi koliko puta J&T pokušava dostaviti, možeš li odabrati gdje će dostavljač ostaviti paket ni koliko dugo J&T čuva paket koji nije uspio dostaviti. Među retcima J&T Express Germany koje Peekov kod poznaje nijedan ne šalje paket na mjesto za preuzimanje ili u paketomat. Pitaj trgovinu ili pogledaj poruke o dostavi.

## J&T paket kasni, nestao je ili je vraćen

Prijevoznika J&T odabrala je trgovina, pa ti je kod narudžbe preko interneta upravo trgovina prvi kontakt. Prema njemačkom pravu, kad kao potrošač kupuješ od tvrtke, rizik gubitka ili oštećenja na putu snosi prodavatelj, osim ako si prijevoznika sam angažirao, a prodavatelj ti ga prije toga nije naveo (§ 475 BGB).

**Paket stoji.** Ako paket pokazuje "Received by carrier" ili "In transit", a četiri dana nema novog retka, Peek uz njega ispiše "No tracking update for four days" (četiri dana bez novosti u praćenju) i ponudi poveznicu "Get help from J&T Express Germany" (zatraži pomoć od J&T Express Germany). Ako se ni nakon toga ništa ne pomakne, pitaj trgovinu. Više u vodiču o [praćenju koje se ne ažurira](guide:tracking-not-updating) (na engleskom).

**Još nema praćenja.** Za broj koji J&T-ov sustav još ne poznaje Peek pokazuje "Waiting for the carrier". Trgovine broj često izrade prije nego što paket predaju prijevozniku. Ako tako ostane nekoliko dana, provjeri broj kod trgovine i pitaj je li paket stvarno poslan.

**Dostavljeno, a paketa nema.** Pročitaj redak o dostavi u Peeku: ako navodi mjesto, kao `Mailbox` ili `Garage`, pogledaj ondje; ako piše `Left with neighbor`, pitaj susjede. I dalje ga nema? Javi trgovini, s brojem za praćenje, i zatraži povrat novca ili novi paket. Pogledaj i vodič o [paketu koji je dostavljen, a nije stigao](guide:delivered-not-received) (na engleskom).

**Izgubljen, oštećen ili vraćen.** Od trgovine zatraži povrat novca ili zamjenu, uz broj za praćenje. Ova stranica ne navodi J&T-ova pravila za reklamacije.

## J&T Express Germany: služba za korisnike i kontakt

Na dan 2. studenoga 2026. ova stranica ne navodi broj telefona, e-adresu ni chat za J&T Express Germany: Peek navodi samo kontakte pročitane na prijevoznikovim vlastitim stranicama, a J&T-ova web-stranica ne može poslužiti kao izvor za ovu stranicu.

Počni od trgovine: ona je dostavu naručila kod J&T-a. Ako si naručio preko neke platforme, koristi pomoć na stranici narudžbe. J&T-ova stranica za praćenje je www.jtexpress.de/tracking. Kod paketa koji je zapeo, Peekova poveznica "Get help from J&T Express Germany" otvara J&T-ovu stranicu za kontakt.

## Praćenje J&T Express Germany pošiljaka s Peekom

[Peek](/) je na engleskom, ali iz `JTDE` broja odmah prepoznaje J&T Express Germany ("Detected carrier", tj. prepoznati prijevoznik), bez obzira na razmake i na velika ili mala slova. Za upit J&T-ovu sustavu za praćenje Peeku je dovoljan sam broj: ne treba ni poštanski broj ni korisnički račun. Možeš zalijepiti i poveznicu www.jtexpress.de/tracking s `?q=` i brojem; ako je u njoj broj drugog oblika, Peek ga čita kao da si zalijepio samo taj broj. Ako tvoj broj nije `JTDE` broj, svejedno ga zalijepi: Peek prati više od 3.500 prijevoznika. Ni ručni odabir ne pomaže: odabereš li za broj drugog oblika J&T Express Germany pod "Change" (promijeni), Peek javlja da J&T Express Germany "doesn’t issue numbers like this one", tj. ne izdaje ovakve brojeve.

Peek pokazuje status, svako skeniranje s točnim vremenom i vrijeme dostave. J&T za svako skeniranje daje točan trenutak, pa ga Peek pokazuje u vremenskoj zoni tvog uređaja. Skeniranja stižu na engleskom, J&T-ovim riječima. J&T ne daje mjesto, očekivani dan dostave ni težinu, pa Peek ne pokazuje kartu. Kod vrlo duge povijesti zadržava 100 najnovijih skeniranja. "Open the J&T Express Germany website" (otvori web-stranicu J&T Express Germany) na stranici paketa otvara tvoj broj na J&T-ovoj stranici za praćenje.

Dok J&T ne skenira paket, Peek pokazuje "Waiting for the carrier" i nastavlja provjeravati. Kad J&T-ov sustav nije dostupan ili ne poznaje broj, Peek pita servise za praćenje i pokazuje njihovu povijest, njihovim riječima.

Peek provjerava paket svakih 10 minuta, a svake 2 minute kad je u dostavi. Ako dva dana nema novog skeniranja (12 sati dok paket pokazuje "Received by carrier"), provjerava ga jednom na sat. Obavijesti ti može slati za "Every scan" (svako skeniranje), "Important steps" (važni koraci) ili "Delivery only" (samo dostava). Peek ne može umjesto tebe kontaktirati J&T ili trgovinu, promijeniti adresu ni dogovoriti novu dostavu.

## Pitanja o praćenju J&T pošiljaka u Njemačkoj

### Je li J&T Express Germany prava dostavna služba?

Da, to je prava dostavna služba: njemačka mreža J&T Expressa, koju Peekov katalog vodi i pod nazivom J&T Express Germany GmbH. Njezin sustav za praćenje daje podatke za `JTDE` brojeve, a svaka rečenica iz gornje tablice statusa zabilježena je na stvarnim paketima J&T Express Germany. J&T-ova web-stranica ne može poslužiti kao izvor za ovu stranicu, pa ova stranica ne daje podatke o tvrtki.

### Kod kojeg je susjeda moj J&T paket?

Peek ti to ne može reći. Kad u retku o dostavi piše `Left with neighbor`, Peek to pokazuje, ali bez imena i adrese. Pitaj susjede; ako ga nitko nema, javi trgovini.

### Ima li J&T Express u Njemačkoj mjesta za preuzimanje ili paketomate?

Koliko zna Peekov kod, nema. U retku o dostavi Peek pokazuje samo ove načine dostave: kod susjeda, pred ulaznim vratima, na recepciji, u vrtu ili dvorištu, u poštanskom sandučiću ili u garaži. Nijedan redak J&T Express Germany koji Peek poznaje ne šalje paket na mjesto za preuzimanje ili u paketomat.

### Zašto J&T ne pronalazi moj broj za praćenje?

Provjeri počinje li s `JTDE` i ima li iza toga 12 znamenki: brojevi drugih J&T mreža, primjerice `JTUK` u Ujedinjenom Kraljevstvu, nisu njemački. Ako je broj točan, J&T možda još nema paket. Peek pokazuje "Waiting for the carrier" do prvog skeniranja; ako to potraje nekoliko dana, pitaj trgovinu.

### Kako kontaktirati J&T Express u Njemačkoj?

Najprije pitaj trgovinu: ona je naručila dostavu. Ova stranica ne navodi broj telefona ni e-adresu, ali kod paketa koji je zapeo, Peekova poveznica "Get help from J&T Express Germany" otvara J&T-ovu stranicu za kontakt.

:::sources
- [Universal Parcel Scraper: J&T Express Germany](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/j-and-t-de) – nazivi, oblik JTDE, poveznice za praćenje, rečenice sustava za praćenje pročitane na stvarnim paketima i njihove faze, ture dostave, načini dostave, što Peek izostavlja, stranica za pomoć
- [Universal Parcel Scraper: zajednički upit J&T-ovih mreža](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/j-and-t-au) – rečenice na engleskom, točna vremena skeniranja, bez mjesta, očekivanog dana i težine, izostavljena imena i brojevi telefona, 100 najnovijih skeniranja
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – oblici brojeva drugih J&T mreža, bez predaje drugom prijevozniku, servisi za praćenje kao rezerva
- [Gesetze im Internet: § 475 BGB](https://www.gesetze-im-internet.de/bgb/__475.html) – rizik prijevoza kad potrošač kupuje od tvrtke
:::
