---
title: Xinghe Freight praćenje pošiljke: GZXH…YQ broj i statusi
description: Prati pošiljku Xinghe Freight (星和货运) iz Kine: format GZXH…YQ, zašto koristi GZYL sustav, unaprijed upisan status Delivered, EU carina i kontakt.
slug: xinghe-freight-pracenje-posiljke
published: 2026-11-27
updated: 2026-11-27
---

Xinghe Freight (星和货运, 广州星和货运代理有限公司) špediterska je tvrtka iz Guangzhoua u Kini osnovana 2014. godine, koja obavlja pomorski, zračni i kopneni prijevoz, brodsku agenturu i carinjenje. Da bi pratio pošiljku, zalijepi u polje iznad njezin 16-znakovni broj formata `GZXH`, deset znamenki i `YQ`. Web-stranica Xinghe Freighta šalje upite za praćenje na nekriptirani HTTP sustav tvrtke [GZYL](carrier:gzyl), gdje koraci — uključujući i `Delivered` — mogu biti unaprijed upisani umjesto stvarnih skeniranja. Peek čita taj sustav za tebe, odvaja unaprijed predviđene korake od stvarnih skeniranja i po potrebi provjerava servise za praćenje.

## Broj za praćenje Xinghe Freight: kako izgleda

Brojevi koje Peek prepoznaje kao Xinghe Freight sastoje se od `GZXH`, deset znamenki i `YQ`, ukupno 16 znakova. Ako slučajno izostaviš zadnje slovo `Q` (`GZXH`, deset znamenki i `Y`, 15 znakova), Peek svejedno prepoznaje broj i automatski vraća `Q` prije provjere. Primjeri s izmišljenim znamenkama:

| Broj | Što predstavlja |
| --- | --- |
| `GZXH4829105736YQ` | `GZXH`, deset znamenki i `YQ`: standardni format Xinghe Freighta. Peek ga odmah prepoznaje. |
| `GZXH4829105736Y` | `GZXH`, deset znamenki i `Y`: nedostaje zadnje `Q`, ali ga Peek automatski dodaje. |
| `GZXH482910573YQ` | Samo devet znamenki: nije format Xinghe Freighta. Peek odmah odbija broj da možeš provjeriti nedostaje li znamenka. |
| `YL4829105736YQ` | `YL`, deset znamenki i `YQ`: format tovarnog lista [GZYL-a](carrier:gzyl). Isti sustav praćenja, drugi špediter. |
| `JXCAA4829105736YQ` | Također završava na `YQ` kao mnogi kineski špediteri, ali počinje s `JXC`: broj tvrtke [Juxi Express](carrier:juxi-express). |

Razmaci, crtice i mala slova nisu važni kad broj lijepiš u Peek.

**Gdje ga pronaći.** Prodavatelj ugovara slanje sa Xinghe Freightom i šalje ti broj u poruci o otpremi ili na stranici narudžbe. Ako ga ne možeš pronaći, pitaj prodavatelja.

## Što znače statusi praćenja Xinghe Freight

Praćenje Xinghe Freighta radi na platformi GZYL-a, pa koraci dolaze na engleskom jeziku u GZYL-ovim formulacijama, zajedno s tipfelerima. Budući da sučelje aplikacije Peek nije prevedeno na hrvatski, i Peekove glavne faze prikazuju se na engleskom: «Label created» (naljepnica kreirana), «Received by carrier» (zaprimljeno kod prijevoznika), «In transit» (u tranzitu), «At customs» (na carini) i «Delivered» (dostavljeno). Koraci redoslijedom kojim pošiljka obično prolazi:

| Sustav piše | Što to znači za tebe | Peek prikazuje |
| --- | --- | --- |
| `Order information received` | Prodavatelj je poslao podatke o narudžbi. Nitko još fizički nema paket. | «Label created» |
| `Arrive at the cross border receiving point` | Špediter je zaprimio paket: prvo stvarno skeniranje u njegovim rukama. | «Received by carrier» |
| `Goods leave the operation center` | Paket je napustio operativni centar u Kini. | «In transit» |
| `Arrive at the international airport` | Na međunarodnoj je zračnoj luci i čeka let. | «In transit» |
| `Departing from the airport` | Zrakoplov je poletio. | «In transit» |
| `Item arrives at destination airport` | Sletio je u odredišnu zemlju. | «In transit» |
| `local carrier Agent Import customs clearance` | Agent u tvojoj zemlji obavlja uvozno carinjenje. | «At customs» |
| `The import customs keep it for inspection` | Carina u tvojoj zemlji zadržala je paket radi pregleda. | «At customs» |
| `Customs clearance completed` | Carinjenje je završeno i paket ide dalje. | «In transit» |
| `Internationaliy Package received at processing center` | Centar za obradu u tvojoj zemlji preuzeo je ocarinjeni paket. To nije novo preuzimanje od pošiljatelja. | «In transit» |
| `Leave the email processing center` | Paket je napustio taj centar za obradu. | «In transit» |
| `Arrival sorting, distribution center` | Stigao u sortirni ili distribucijski centar u tvojoj zemlji, s imenom grada nakon teksta. | «In transit» |
| `Leaving the sorting center` | Napušta sortirni centar, s gradom nakon teksta. | «In transit» |
| `Package is on the way` | Kreće se dalje u odredišnoj zemlji, uz ime grada. To ne znači da je paket kod dostavljača za današnju dostavu. | «In transit» |
| `Delivered` | Zabilježena dostava uz grad. Može biti unaprijed upisan plan, a ne skeniranje: vidi ispod. | «Delivered» uz napomenu o fiksnom rasporedu, osim ako Peek ne zadrži korak (vidi ispod) |

**Preko Hong Konga.** Može se pojaviti i `香港处理中心已收寄【Hong Kong】`: poštansko zaprimanje u centru za obradu u Hong Kongu, koje sustav prenosi na kineskom s gradom u zagradama. Peek ga prikazuje kako je napisan, pod fazom «Received by carrier».

**Redak na kineskom.** Za narudžbu bez ijednog skeniranja platforma sama upisuje `货物电子信息已经收到` (elektronički podaci o pošiljci su zaprimljeni). Prodavatelj je kreirao narudžbu, ali paket još nije skeniran. Peek taj redak prikazuje na kineskom pod «Label created».

**Vremena.** Peek svako vrijeme u sustavu čita po pekinškom vremenu (`UTC+8`), uključujući korake u Hrvatskoj, i pretvara ga u vremensku zonu tvog uređaja. Zimi je 20:00 u Pekingu jednako 13:00 po hrvatskom vremenu (`UTC+1`), a ljeti 14:00 (`UTC+2`).

**Koraci upisani unaprijed.** Na ovoj platformi za praćenje špediteri mogu unaprijed upisati cijeli vremenski tijek paketa, od preuzimanja do zadnjeg koraka u tvojoj zemlji, a sustav prikazuje svaki korak kad dođe njegovo vrijeme. Takav korak je plan, a ne skeniranje tvog paketa. Na GZYL-ovu sustavu plan završava statusom `Delivered` koji platforma dodaje u večernjoj seriji na dan koji prikazuje: datiran dan nakon zadnjeg koraka plana, tijekom radnog vremena u Pekingu, bez obzira na to u kojoj zemlji živiš. Peek zadržava takve korake (vidi ispod), no kopije na drugim servisima za praćenje mogu ih ipak prikazivati, pa `Delivered` tamo ne dokazuje da je paket stigao.

## Tko dostavlja pakete Xinghe Freight u Hrvatskoj

Peek ne zna koja lokalna dostavna služba ili pošta dostavlja pakete Xinghe Freighta u Hrvatskoj. Koraci u odredišnoj zemlji ne spominju nijednu tvrtku, a na stranici Xinghe Freighta nema nikakvih informacija o paketima za internetske kupce. Jedini lokalni dostavljač kojem Peek može automatski proslijediti praćenje Xinghe paketa je [Swiss Post](carrier:swiss-post), u Švicarskoj i Lihtenštajnu, kada ga Xingheov zapis navede.

Web-stranica Xinghe Freighta ne objavljuje rokove dostave, broj pokušaja dostave ni paketomate ili poštanske urede: to određuje lokalni dostavljač. Pitaj prodavatelja koja tvrtka dostavlja tvoju narudžbu u Hrvatskoj i zatraži njezin lokalni broj za praćenje.

**Carinske pristojbe u EU.** Od 1. srpnja 2026. za robu kupljenu putem interneta izvan Europske unije u pošiljkama vrijednosti do 150 € naplaćuje se paušalna carina od 3 € po vrsti proizvoda. Od 1. studenoga 2026. dodatno se naplaćuje naknada EU-a za obradu (Union handling fee) od 2 € po vrsti proizvoda, bez obzira na vrijednost. Jedna vrsta obuhvaća robu u istom paketu s istom tarifnom oznakom, opisom i (gdje se zahtijeva) podrijetlom, neovisno o broju komada. Više pročitaj u vodiču o [paketima na carini](guide:customs) (na engleskom).

## Paket Xinghe Freight ne stiže ili praćenje stoji

**Samo `Order information received` ili `货物电子信息已经收到`.** Prodavatelj je kreirao narudžbu, ali paket još nitko nije skenirao. Ako se za nekoliko dana ništa ne promijeni, pitaj prodavatelja je li ga stvarno poslao.

**«Waiting for Xinghe Freight».** Peek još nema potvrđeno skeniranje za tvoj broj: GZYL-ov sustav ga ne poznaje ili sadrži samo unaprijed upisan plan (koji može završiti s `Delivered`), a ni servisi za praćenje nemaju ništa. Peek nastavlja provjeravati. Za nepoznat broj GZYL-ov sustav vraća jedan prazan redak s brojem kao referencom; Peek to tumači kao nepostojeći broj, prikazuje «Waiting for Xinghe Freight» i nakon dva dana «Check the tracking number». Unaprijed upisan plan računa se kao potvrda da Xinghe ima narudžbu, pa «Waiting for Xinghe Freight» ostaje bez zahtjeva za provjerom broja. Ipak usporedi broj znak po znak s porukom prodavatelja.

**Zapeo na carini.** Ako te traže plaćanje uvoznih davanja, pogledaj vodič o [paketima na carini](guide:customs) (na engleskom). Ako ondje stoji danima, javi prodavatelju.

**Sustav ili druga stranica prikazuje više koraka nego Peek.** Dodatni koraci, uključujući `Delivered`, mogu biti unaprijed upisani plan koji Peek zadržava. Ravnaj se prema procijenjenom roku dostave trgovine i javi se prodavatelju kad prođe. Više u vodiču o [praćenju paketa iz Kine](guide:tracking-from-china) (na engleskom).

**`Delivered`, ali ništa nije stiglo.** Korak može biti dio unaprijed zadanog rasporeda. Osim ako dostavlja Swiss Post, Peek ispod «Delivered» dodaje napomenu: «Xinghe Freight updates on a fixed schedule, with no signature. If nothing came, ask the seller.» Javi prodavatelju da paket nije stigao i traži naziv te broj lokalnog dostavljača. Više u vodiču o [paketu označenom kao dostavljenom koji nije stigao](guide:delivered-not-received) (na engleskom).

**Izgubljen ili vraćen.** Prodavatelj je ugovorio slanje sa Xinghe Freightom, pa povrat novca ili novo slanje zatraži od prodavatelja ili platforme.

## Kontakt i korisnička podrška Xinghe Freighta

Na kineskoj web-stranici Xinghe Freighta (`xinghe-express.cn`) navedeno je:

- **Telefonska linija (24/7):** `19924483103` (`+86 199 2448 3103` iz inozemstva), navedena kao otvorena 24 sata dnevno, svaki dan.
- **E-mail:** `3975605292@qq.com` za poslovne upite i ponude.
- **Adresa:** Guangzhou, pokrajina Guangdong, bez naziva ulice (za punu adresu mole da ih se prvo kontaktira).

Stranica je samo na kineskom i nema posebnu podršku za inozemne primatelje: kontaktni obrazac samo otvara e-mail prema gornjoj adresi. Klijent Xinghea je prodavatelj, stoga se prvo obrati prodavatelju, a zatim platformi ako ne odgovara.

## Praćenje paketa Xinghe Freight pomoću Peeka

[Peek](/) odmah prepoznaje broj `GZXH…YQ` (kao i skraćeni `GZXH…Y`) kao Xinghe Freight s oznakom «Detected carrier». Ako ti je prodavatelj dao broj drugog formata, zalijepi ga svejedno: Peek prati više od 4 500 prijevoznika. Za ručni odabir pritisni «Change» i potraži Xinghe Freight (ili `星和货运`), odnosno «Change carrier» na već dodanom paketu.

Peek izravno pita GZYL-ov sustav, baš kao i polje na stranici Xinghea. Prikazuje status, svaki korak s mjestom i odredišnu zemlju kada je navedena. Vremena se čitaju u pekinškom vremenu i pretvaraju u vremensku zonu tvog uređaja («Times are in your time zone:» na kraju povijesti). Peek izostavlja imena, adresu, poštanske brojeve, masu, podatke o plaćanju, potpis i potvrdu dostave te ne prikazuje procijenjeni datum dostave jer ga sustav ne daje.

Kad sustav ne poznaje broj, Peek pita servise za praćenje i prikazuje njihovu kopiju. Dok nigdje nema koraka, stoji «Waiting for Xinghe Freight», a nakon dva dana «Check the tracking number».

Peek zadržava svaki korak upisan više od 12 sati prije vremena koje prikazuje, svaki korak upisan u istoj sekundi s korakom upisanim više od pola sata prije svog vremena (osim registracije narudžbe) i svaki korak s datumom nakon trenutka provjere po pekinškom vremenu. Na zapisu s unaprijed upisanim koracima zadržava i `Delivered`, čak i ako je upisan istog dana. Status određuje prema zadržanim koracima, a ne prema sažetku zapisa, i uklanja kopije tih planiranih koraka i iz servisa za praćenje (`Delivered` uključeno). No ako sustav ne poznaje broj ili je nedostupan, Peek ne može razlikovati planirani korak kod vanjskog servisa od pravog skeniranja. Kada Peek zadrži predviđanja, povijest do dostave ili povrata otvara retkom «Other sites may show more steps, but they’re only predictions.», a «Show them» ih prikazuje sivo s danom iz sustava («Predicted for Mon 12 Oct»). Takvi koraci ne mijenjaju status i ne šalju obavijesti.

Osim za Swiss Post, Peek ne prelazi automatski na lokalnog dostavljača: ako od prodavatelja dobiješ lokalni broj, dodaj ga kao zaseban paket. Dok držiš paket otvorenim ili na dan dostave s uključenim obavijestima, Peek provjerava do svakih 10 minuta; inače i kad nema novosti jednom na sat. Obavijesti možeš postaviti na «Every scan», «Important steps» (dva carinska koraka i `Delivered`, bez letova i sortiranja) ili «Delivery only». Peek ne može kontaktirati Xinghe Freight, GZYL ili prodavatelja umjesto tebe.

## Česta pitanja o Xinghe Freightu

### Što je Xinghe Freight (星和货运)?

广州星和货运代理有限公司 (Guangzhou Xinghe Freight Forwarding) špediterska je tvrtka iz Guangzhoua u Kini osnovana 2014. godine, koja obavlja pomorski, zračni i kopneni prijevoz, brodsku agenturu i carinsko deklariranje. Njezini brojevi paketa imaju 16 znakova: `GZXH`, deset znamenki i `YQ`.

### Zašto moj paket Xinghe Freight prikazuje praćenje GZYL-a?

Polje za praćenje na stranici Xinghe Freighta prosljeđuje upite izravno GZYL-ovu sustavu, pa su nazivi koraka jednaki GZYL-ovima. Peek vodi paket pod Xinghe Freightom i čita taj sustav za tebe.

### Tko dostavlja pakete Xinghe Freight u Hrvatskoj?

Ni stranica Xinghea ni sustav za praćenje ne navode lokalnog partnera za dostavu u Hrvatskoj (Peek automatski predaje samo Swiss Postu u Švicarskoj i Lihtenštajnu kad je naveden). Pitaj prodavatelja koja lokalna služba dostavlja tvoj paket i koji je njezin broj za praćenje.

### Zašto Peek prikazuje manje koraka ili nikakvo praćenje za moj broj GZXH?

Ili sustav još nema zapis za broj, ili sadrži unaprijed upisane korake — uključujući planirani `Delivered` — koje Peek skriva iza «Show them» umjesto da ih broji kao skeniranja. Usporedi broj s porukom prodavatelja i javi mu se ako nakon nekoliko dana nema promjena.

### Kako mogu kontaktirati Xinghe Freight?

Prvo se obrati prodavatelju ili platformi, jer je prodavatelj ugovorio prijevoz. Kineska stranica (`xinghe-express.cn`) navodi 24-satnu liniju `19924483103` (`+86 199 2448 3103` iz inozemstva) i poslovni e-mail `3975605292@qq.com`, bez posebne podrške za inozemne primatelje.

:::sources
- [Xinghe Freight: 广州星和货运代理有限公司 (početna stranica, na kineskom)](http://xinghe-express.cn/) – naziv tvrtke, špediterske usluge, polje za praćenje prema sustavu GZYL, telefon i e-mail
- [Xinghe Freight: 关于我们 (o nama, na kineskom)](http://xinghe-express.cn/about.html) – osnivanje 2014., pomorski, zračni i kopneni prijevoz, brodska agentura i carinjenje
- [Xinghe Freight: 产品服务 (usluge, na kineskom)](http://xinghe-express.cn/services.html) – ponuda usluga, bez podataka o paketima za internetske kupce
- [Xinghe Freight: 联系我们 (kontakt, na kineskom)](http://xinghe-express.cn/contact.html) – 7×24 linija, poslovni e-mail, adresa samo Guangzhou, obrazac koji otvara e-mail
- [Europska komisija: EU Customs Reform (na engleskom)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – paušalna carina 3 € po vrsti proizvoda do 150 € od 1. srpnja 2026., naknada EU-a za obradu 2 € od 1. studenoga 2026.
- [Europska komisija: Guidance on the EUR 3 customs duty and the Union handling fee (PDF, na engleskom)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – naknada 2 € bez obzira na vrijednost i definicija vrste proizvoda
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – nazivi, formati `GZXH…YQ` i skraćeni `GZXH…Y`, odbijanje pogrešne duljine, sustav GZYL, formulacije koraka, zaprimanje u Hong Kongu, kineski redak, pekinško vrijeme, unaprijed upisani koraci, rezervni servisi, predaja Swiss Postu te što Peek prikazuje, zadržava i izostavlja
:::
