---
title: Praćenje pošiljki M-ONE (Milšped ONE) — statusi i kontakt
description: Prati M-ONE (Milšped ONE) pakete u Srbiji: RS brojevi pošiljki, srpski statusi, paketomati, 5 radnih dana čuvanja, reklamacije i kontakt 011 222 8 222.
slug: pracenje-posiljki-m-one
published: 2026-11-23
updated: 2026-11-23
---

**M-ONE** (*Milšped ONE*, deo Milšped grupe, osnovan 2020. godine; pravna lica MILŠPED ONE doo i M-ONE e-comm doo u Beogradu) je kurirska služba u Srbiji koja isporučuje pakete za internet prodavnice i firme na svaku adresu u zemlji, preko sopstvene mreže distributivnih centara, paketomata i paket šopova. Pošiljku možeš pratiti u sekciji *TRACK* na `m-one.rs` ili tako što ćeš broj pošiljke nalepiti u polje iznad.

## Broj pošiljke (barkod) kod M-ONE

M-ONE ovaj broj naziva **broj barkoda** (a polje na sajtu nosi naziv *Order number*). Peek podržava oba formata koja M-ONE koristi (izmišljeni brojevi):

| Broj (izmišljen) | Šta predstavlja |
| --- | --- |
| `AA482917367RS` | Dva slova, devet cifara (deveta je kontrolna cifra) i `RS` — međunarodni poštanski format sa oznakom Srbije. |
| `RS482917360514` | `RS` i 12 cifara — drugi format koji prihvata M-ONE sistem za praćenje. |

Isti poštanski format sa završetkom `RS` koristi i Pošta Srbije, pa proveri ime kurira u poruci prodavnice. Nemaš broj? Pogledaj [how to find a tracking number](guide:find-tracking-number) i [tracking number formats](guide:tracking-number-formats) (na engleskom). Ako M-ONE prikaže „No order with this number“, proveri broj ili pitaj pošiljaoca da li je pošiljka već predata.

## Značenje statusa praćenja M-ONE

M-ONE sistem vraća statuse na srpskom jeziku (čak i na engleskoj verziji sajta), a Peek zadržava originalni tekst i dodaje jasnu fazu isporuke:

| M-ONE status | Faza u Peek-u | Značenje za primaoca |
| --- | --- | --- |
| `Kreiran nalog za preuzimanje` | Label created | Pošiljalac je zakazao preuzimanje; M-ONE još nije fizički preuzeo paket. |
| `Preuzeta od pošiljaoca` | Received by carrier | Kurir M-ONE je preuzeo pošiljku od pošiljaoca. |
| `U lokalnom tranzitu` | In transit | Pošiljka putuje između M-ONE centara. |
| `U distributivnom centru dostave` | In transit | U distributivnom centru nadležnom za tvoju adresu (ovde se vraća i posle neuspešne dostave). |
| `Preusmerena` / `Preusmerena na zahtev primaoca` | In transit | Pošiljka je preusmerena u dostavnom centru ili po tvom zahtevu. |
| `Na dostavnom vozilu` | Out for delivery | U dostavnom vozilu kod kurira — očekuj isporuku danas. |
| `Zaduzena za paketomat` | Out for delivery | Kurir nosi pošiljku na ubacivanje u izabrani paketomat. |
| `Čeka u paketomatu` | Ready for pickup | Spremna za preuzimanje u paketomatu pomoću koda iz Viber ili SMS poruke. |
| `Pokušaj uručenja (primaocu ostavljeno obaveštenje)` | Delivery attempted | Dostava nije uspela i ostavljeno je obaveštenje. |
| `Primalac ce doci po paket` | Ready for pickup | Paket te čeka u M-ONE centru za lično preuzimanje (ponavlja se dok ga ne preuzmeš). |
| `Uručena pošiljka` | Delivered | Uručena primaocu ili preuzeta iz paketomata. |

Reči `Zaduzena` i `ce` namerno su bez kvačica, tačno onako kako ih M-ONE šalje.

## Isporuka u Srbiji, paketomati i propuštena dostava

- **Rok isporuke („Danas za sutra“):** Standardna ekspres usluga M-ONE isporučuje pakete do **19:00 sledećeg radnog dana** na svaku adresu u Srbiji (ugovorom pošiljaoca rok može biti do 3 radna dana). U Beogradu postoji i dostava istog dana za pakete do 30 kg. M-ONE radi **od ponedeljka do petka**; subote, nedelje i državni praznici ne ulaze u rokove isporuke.
- **Ko može da preuzme paket i bezbedno mesto:** Primalac, ovlašćeno lice, punoletni član domaćinstva, osoba zaposlena u domaćinstvu ili kolega na radnom mestu. Možeš i pisanim putem (imejl, SMS ili Viber) odrediti bezbedno mesto gde će paket biti ostavljen na tvoj rizik.
- **Zatvorena isporuka i otkupnina:** M-ONE isporučuje isključivo zatvorene pošiljke — paket ne možeš otvoriti pre preuzimanja. Kod pošiljki sa otkupninom plaćaš navedeni iznos prilikom uručenja, a poštarinu plaća pošiljalac ili primalac po dogovoru sa prodavnicom.
- **Propuštena dostava (5 radnih dana):** Ako nisi na adresi, M-ONE šalje obaveštenje imejlom, SMS-om ili Viberom sa vremenom pokušaja i uputstvom gde i kada možeš preuzeti paket. Pošiljka se čuva **5 radnih dana**, računajući od prvog narednog radnog dana nakon poruke ([missed delivery](guide:missed-delivery)).
- **Paketomati i paket šopovi:** Isporuka u paketomat dostupna je za prodavnice koje sarađuju sa M-ONE. Kada paket stigne, M-ONE šalje Viber poruku (ili SMS ako nemaš Viber / nisi online) sa adresom paketomata, jedinstvenim kodom za otvaranje i rokom za preuzimanje. Ako se ne preuzme na vreme, paket se vraća pošiljaocu uz pisano obaveštenje ([parcel lockers](guide:parcel-lockers)).

## Kasni, vraćen, oštećen ili izgubljen paket

- **Bez ažuriranja ili vraćen paket:** Ako nekoliko radnih dana nema promena, proveri prvo sa prodavnicom, pa sa M-ONE ([tracking that isn't updating](guide:tracking-not-updating)). M-ONE vraća pošiljku ako je odbiješ, ne preuzmeš u roku, ne postojiš na adresi ili je adresa netačna ([returned to sender](guide:returned-to-sender)).
- **Oštećenje ili manjak sadržaja (rok naredni radni dan!):** Reklamaciju moraš podneti **najkasnije do kraja narednog radnog dana** nakon uručenja pozivom na `011 222 8 222` ili na `reklamacije@m-one.rs`. M-ONE sastavlja zapisnik i fotografiše unutrašnju i spoljašnju ambalažu i štetu — sačuvaj ambalažu netaknutom, jer se kasnije reklamacije ne nadoknađuju ([damaged parcel](guide:damaged-parcel)).
- **Gubitak, kašnjenje ili uručeno bez prijema:** Rok za ostale reklamacije je **60 dana** u unutrašnjem saobraćaju i **6 meseci** u međunarodnom, računajući od dana posle predaje. Reklamacija se podnosi pisano u poslovnici, imejlom ili telefonom uz broj pošiljke, podatke pošiljaoca i primaoca i dokaz o predaji/fakturu. Za domaće pošiljke M-ONE odgovara u roku od **8 dana** i donosi odluku u roku od **15 dana**; u slučaju odbijanja imaš 15 dana za prigovor regulatoru (ili 90 dana ako nema odgovora). Naknadu dobija onaj ko je platio poštarinu (pošiljalac može preneti pravo na primaoca).

## Praćenje M-ONE pošiljki pomoću Peek-a

[Peek](/) prepoznaje poštanski format, dva slova, devet cifara i `RS` sa ispravnom kontrolnom cifrom, ali sam format ne imenuje M-ONE. Peek pita Poštu Srbije, M-ONE i [Chronopost](carrier:chronopost): vidiš „Checking tracking services…“, pa „M-ONE has this parcel.“ kada M-ONE zna broj. Sa pogrešnom kontrolnom cifrom Peek ne pita nijednog kurira: uporedi broj sa porukom o slanju. Broj koji počinje sa `C`, `E`, `L` ili `R`, završava se sa `RS` i ima ispravnu kontrolnu cifru Peek po obliku pripisuje [Pošti Srbije](carrier:posta-srbije); ako je tvoj stigao od M-ONE, izaberi „M-ONE“ pomoću „Change“ prilikom dodavanja ili „Change carrier“ na ekranu paketa (interfejs Peek-a je na engleskom). Za `RS` i 12 cifara Peek ne imenuje nijednog kurira, pa i tu izaberi „M-ONE“ pomoću „Change“. Link ka M-ONE praćenju sa brojem bilo kog oblika posle `?` (`m-one.rs/en?…`) odmah imenuje M-ONE.

Peek čita M-ONE-ovo praćenje putem otvorenog [M-ONE adaptera u universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/m-one). Dobijaš status i istoriju M-ONE-ovim rečima na srpskom, distributivni centar kada ga sken navodi i težinu paketa. M-ONE daje vremena bez vremenske zone; Peek ih čita po srpskom vremenu i prikazuje u vremenskoj zoni tvog uređaja. M-ONE-ovo praćenje ne daje očekivani dan isporuke ni paketomat, pa ih Peek ne prikazuje. Ime pošiljaoca se izostavlja; ime primaoca, kada ga M-ONE da, vidi se pod „Recipient“ na tvojoj stranici paketa, nikad na deljenom linku. Paket upisan pod M-ONE prikazuje „Waiting for M-ONE“ dok M-ONE ne zna broj, a Peek nastavlja da proverava.

Peek svrstava statuse u pregledne etape: **Label created** (`Kreiran nalog za preuzimanje`), **Received by carrier** (`Preuzeta od pošiljaoca`), **In transit** (`U lokalnom tranzitu`, `U distributivnom centru dostave`, `Preusmerena`), **Out for delivery** (`Na dostavnom vozilu`, `Zaduzena za paketomat`), **Delivery attempted** (`Pokušaj uručenja`), **Ready for pickup** (`Čeka u paketomatu`, `Primalac ce doci po paket`) i **Delivered** (`Uručena pošiljka`).

Kada je najnoviji sken star četiri dana, a paket je još na „Label created“, „Received by carrier“ ili „In transit“, Peek ga označava danom tog skena, na primer „No tracking update since Mon 12 Oct“. Stranica paketa tada kaže „Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking M-ONE.“, sa linkom „Get help from M-ONE“ ka M-ONE-ovoj stranici za kontakt. Posle tri nedelje bez novog skena kaže „Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking M-ONE.“

Dok je paket otvoren u Peek-u, ili na dan isporuke sa uključenim obaveštenjima, Peek proverava do svakih 10 minuta, a do svaka 2 minuta od 6 ujutru po srpskom vremenu na dan kada paket krene na isporuku, dok M-ONE-ovo praćenje odgovara. Posle dva dana bez novosti (12 sati dok paket još pokazuje „Label created“ ili „Received by carrier“) Peek proverava svakog sata, a na svakih 6 sati kada paket sedam dana pokazuje „Label created“. Obaveštenja: „Every scan“, „Important steps“ ili „Delivery only“. Peek ne može da preusmeri paket, otvori reklamaciju niti da kontaktira M-ONE ili prodavnicu umesto tebe.

## Kontakt i korisnička podrška M-ONE

Zvanični kanali podrške objavljeni na `m-one.rs` (`m-one.rs/kontakt` i `m-one.rs/cesta-pitanja`):

- **Telefon (kontakt centar):** `011 222 8 222` (`+381 11 222 8 222` iz inostranstva), radnim danima **08:00–16:00** (pripremi broj pošiljke)
- **Reklamacije:** `reklamacije@m-one.rs` (u dodatnim uslovima naveden je i broj službe reklamacija `011/2203547`)
- **Korisnička podrška:** `podrskaMone@milsped.com`
- **Opšti upiti:** `office@m-one.rs` i `info@m-one.rs`
- **Adresa sedišta:** M-One, Arsenija Čarnojevića 59 V, 11070 Novi Beograd (obrazac *Zahtev za nadoknadu štete* i opšti uslovi nalaze se u dnu sajta `m-one.rs`)

## Česta pitanja

### Da li su M-ONE i Milšped ista kompanija?

M-ONE (osnovan 2020) je ćerka-firma **Milšped grupe** specijalizovana za kurirsku dostavu paketa u Srbiji (pravna lica MILŠPED ONE doo i M-ONE e-comm doo u Beogradu).

### Koliko traje dostava preko M-ONE?

Uslugom „Danas za sutra“ paketi se isporučuju do **19:00 narednog radnog dana** na svaku adresu u Srbiji (po ugovoru pošiljaoca najviše do 3 radna dana; vikendi i praznici se ne računaju).

### Koliko dugo M-ONE čuva paket nakon neuspele dostave?

Pošiljka se čuva **5 radnih dana**, računajući od prvog radnog dana nakon poruke obaveštenja putem imejla, SMS-a ili Vibera, nakon čega se vraća pošiljaocu.

### Šta znači status „Primalac ce doci po paket“?

Znači da pošiljka čeka u nadležnom M-ONE centru da je lično preuzmeš — u Peek-u se taj status prikazuje kao **Ready for pickup**.

:::sources
- [M-ONE: Početna](https://m-one.rs/en) – osnovan 2020, Milšped grupa, mreža u Srbiji, sekcija TRACK i njene poruke, broj barkoda, „Danas za sutra“, dostava istog dana u Beogradu, povraćaj, paketomati i paket šopovi, radno vreme, imejlovi, dokumenti
- [M-ONE: Česta pitanja](https://m-one.rs/en/cesta-pitanja) – ko može da preuzme, obaveštenje posle neuspele dostave, poštarina, reklamacije za oštećenje do kraja narednog radnog dana, 011 222 8 222
- [M-ONE: Kontakt](https://m-one.rs/en/kontakt) – adresa, telefon, imejlovi
- [M-ONE: Opšti uslovi obavljanja poštanskih usluga](https://m-one.rs/pdf/Op%C5%A1ti%20uslovi.docx) – M-ONE e-comm doo, radni dani i radno vreme kontakt centra, rokovi isporuke, Track&Trace, zatvorene pošiljke, otkupnina, ko može da preuzme, bezbedno mesto, obaveštenje i 5 radnih dana, povraćaj, paketomati, rokovi i sadržaj reklamacija, rokovi za odgovor, prigovor regulatoru, ko dobija naknadu
- [M-ONE: Ostali uslovi korišćenja](https://m-one.rs/pdf/OSTALI%20USLOVI%20KORI%C5%A0%C4%86ENJA%2029072024.pdf) – MILŠPED ONE doo, služba reklamacija 011/2203547 i reklamacije@m-one.rs
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/m-one) – oblici brojeva, statusi i njihova značenja, šta daje M-ONE-ovo praćenje i šta Peek čita
:::
