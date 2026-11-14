---
title: Praćenje GZYL pošiljke iz Kine: broj YL…YQ i statusi
description: Prati paket iz Kine poslan preko GZYL-a (广州云链): kako izgleda broj YL…YQ, što znače statusi i zašto praćenje može pokazati Delivered iako paket nije stigao.
slug: gzyl-pracenje-posiljke
published: 2026-11-03
updated: 2026-11-14
---

GZYL (广州云链, Guangzhou Yunlian) je 广州云链货运代理有限公司, špeditersko poduzeće iz Guangzhoua (Kantona) u Kini. Ako ti je kineska web-trgovina narudžbu poslala preko GZYL-a, broj za praćenje ima oblik YL, deset znamenki i YQ: zalijepi ga u polje iznad. GZYL-ova web-stranica, yunlianhouyun.com, na kineskom je, a njezina tražilica pošiljaka (小包查询) otvara GZYL-ov sustav za praćenje, koji radi na IP adresi, bez imena domene. Podatke u njemu uzmi s rezervom: GZYL velik dio puta paketa upisuje unaprijed, pa korak koji ondje vidiš nije uvijek stvarno skeniranje, čak ni kad piše `Delivered`.

## GZYL broj za praćenje: kako izgleda

GZYL-ovi brojevi pošiljaka imaju oblik YL, deset znamenki i YQ, ukupno 14 znakova. Primjeri s izmišljenim znamenkama:

| Broj (izmišljen) | Što je to |
| --- | --- |
| `YL0000000000YQ` | YL, deset znamenki i YQ: oblik GZYL-ovih brojeva. Peek ga provjerava u GZYL-ovu sustavu za praćenje. |
| `YL7380000000YQ` | YL738, sedam znamenki i YQ: po tom obliku Peek odmah prepoznaje GZYL. |
| `YL000000000YQ` | Samo devet znamenki: to nije GZYL-ov oblik. Provjeri nedostaje li znamenka. |
| `GZXH0000000000YQ` | GZXH, deset znamenki i YQ: Xinghe Freight (星和货运), drugo špeditersko poduzeće. Njegove pošiljke prikazuje GZYL-ov sustav za praćenje, s GZYL-ovim tekstovima koraka. |
| `JXCAA0000000000YQ` | I ovaj broj završava s YQ, kao brojevi mnogih kineskih špeditera, ali počinje s JXC: to je broj Juxi Expressa. |

Razmaci i mala slova nisu važni kad broj zalijepiš u Peek.

**Gdje ga naći.** Prodavač kod GZYL-a naruči prijevoz i pošalje ti broj. Ako ga nemaš, zatraži ga od prodavača.

## Što znače statusi GZYL pošiljke

GZYL svoje korake piše na engleskom, i to s tipfelerima. Peek ih prikazuje onako kako su napisani, a pošiljci uz to dodjeljuje vlastiti status. Aplikacija Peek nije na hrvatskom, pa i taj status vidiš na engleskom: "Label created" (izrađena je naljepnica), "Received by carrier" (prijevoznik je preuzeo paket), "In transit" (u prijevozu), "At customs" (na carini) ili "Delivered" (dostavljeno). GZYL-ovi koraci, redom kojim se obično pojavljuju:

| GZYL piše | Što to znači za tebe | U Peeku |
| --- | --- | --- |
| `Order information received` | Prodavač je GZYL-u poslao podatke o narudžbi. GZYL paket još nema. | "Label created" |
| `Arrive at the cross border receiving point` | GZYL je preuzeo paket: to je prvo skeniranje kod GZYL-a. | "Received by carrier" |
| `Goods leave the operation center` | Paket je napustio GZYL-ov operativni centar. | "In transit" |
| `Arrive at the international airport` | Paket je u zračnoj luci i čeka let. | "In transit" |
| `Departing from the airport` | Avion je poletio. | "In transit" |
| `Item arrives at destination airport` | Paket je stigao u zračnu luku u tvojoj zemlji. | "In transit" |
| `local carrier Agent Import customs clearance` | Carinski zastupnik u tvojoj zemlji provodi uvozno carinjenje paketa. | "At customs" |
| `The import customs keep it for inspection` | Carina u tvojoj zemlji pregledava paket. | "At customs" |
| `Customs clearance completed` | Paket je ocarinjen i nastavlja put. | "In transit" |
| `Internationaliy Package received at processing center` | Centar za obradu u tvojoj zemlji preuzeo je ocarinjeni paket. To nije novo preuzimanje: paket je preuzet mnogo ranije. | "In transit" |
| `Leave the email processing center` | Paket je napustio taj centar za obradu. | "In transit" |
| `Arrival sorting, distribution center` | Paket je u distribucijskom centru u tvojoj zemlji; GZYL iza teksta dopisuje ime grada. | "In transit" |
| `Leaving the sorting center` | Paket napušta sortirni centar; i ovdje iza teksta stoji grad. | "In transit" |
| `Package is on the way` | Paket putuje dalje po tvojoj zemlji, a iza teksta stoji grad. To ne znači da je paket već u dostavi. | "In transit" |
| `Delivered` | Korak dostave; iza teksta stoji grad. To može biti samo plan, a ne skeniranje (vidi niže). | "Delivered", s napomenom o unaprijed zadanom rasporedu, ako ga Peek uopće prikaže (vidi niže) |

Grad koji GZYL dopiše iza tih tekstova Peek prikazuje kao mjesto koraka.

**Preko Sjedinjenih Država.** U praćenju se može pojaviti i `Los Angeles Processing Center has received it`, s gradom u uglatim zagradama iza teksta: centar za obradu u SAD-u preuzeo je paket na putu prema odredištu. Peek prikazuje "In transit".

**Redak na kineskom.** Za narudžbu koja još nema nijedno skeniranje, platforma na kojoj radi GZYL-ov sustav za praćenje sama upiše redak `货物电子信息已经收到`, što znači da su elektronički podaci o robi zaprimljeni. Prodavač je izradio narudžbu, ali paket još nitko nije skenirao. Peek taj redak prikazuje na kineskom, sa statusom "Label created".

**Preko kineske pošte.** Može se pojaviti i `广州处理中心已收寄`, s gradom u zagradama iza teksta: kineska pošta China Post preuzela je paket u svom centru za obradu u Guangzhouu, a GZYL taj redak prenosi na kineskom. Kod brojeva Xinghe Freighta `香港处理中心已收寄` znači isto preuzimanje, u centru za obradu u Hong Kongu. I Peek oba retka prikazuje na kineskom, sa statusom "Received by carrier".

**Vrijeme.** Sva su vremena u GZYL-ovu sustavu za praćenje po pekinškom vremenu, i ona za korake u tvojoj zemlji, a vremenska zona uz njih nije navedena. Korak u 20:00 po pekinškom vremenu zimi je u 13:00 po hrvatskom vremenu, a ljeti u 14:00.

**Koraci upisani unaprijed.** GZYL može cijeli put paketa, od preuzimanja do posljednjeg koraka u tvojoj zemlji, upisati unaprijed, dugo prije vremena navedenih uz korake. Sustav za praćenje zatim svaki korak prikaže tek kad dođe njegovo vrijeme. Tako upisan korak je plan, a ne skeniranje tvog paketa. Isto radi i Meiteshun, špediter koji koristi istu platformu za praćenje. GZYL-ov plan završava korakom `Delivered`, koji platforma dodaje skupno, navečer onoga dana koji je uz njega naveden. Taj korak nosi datum dana nakon posljednjeg koraka plana i vrijeme unutar pekinškog radnog vremena, bez obzira na to u kojoj zemlji živiš. Servisi za praćenje koji preuzimaju GZYL-ove podatke mogu prikazivati i te korake. Zato `Delivered` u GZYL-ovu sustavu ili na takvom servisu ne dokazuje da je paket stigao.

## Tko dostavlja GZYL pošiljke u Hrvatskoj

Peek ne zna tko GZYL-ove pošiljke dostavlja u Hrvatskoj: GZYL-ovi koraci u tvojoj zemlji ne navode nijednu tvrtku, a Peek praćenje ne može prebaciti ni na poštu ni na kurira u Hrvatskoj. Jedini dostavljač na kojega Peek može prebaciti praćenje GZYL-ove pošiljke je Swiss Post (švicarska pošta), i to u Švicarskoj i Lihtenštajnu.

Peek kod GZYL-a nije našao rokove dostave ni pravila o pokušajima dostave, mjestima za preuzimanje ili roku čuvanja paketa: sve to određuje tvrtka koja dostavlja. Pitaj prodavača koja će ti tvrtka dostaviti narudžbu i pod kojim je brojem možeš pratiti.

## GZYL pošiljka ne stiže ili praćenje stoji

**Samo `Order information received` ili `货物电子信息已经收到`.** Prodavač je izradio narudžbu, ali GZYL paket još nije skenirao. Ako se ni dan nakon takvog koraka ne pojavi ništa novo, Peek ispod "Label created" dopiše: "The sender hasn’t handed it to GZYL yet. Tracking starts when they do." To znači da pošiljatelj paket još nije predao GZYL-u, a praćenje počinje kad to učini. Ako se ni nakon nekoliko dana ništa ne pomakne, pitaj prodavača je li paket stvarno poslan.

**Paket je zapeo na carini.** Ako od tebe traže uvozna davanja, pogledaj vodič o [pošiljkama na carini](guide:customs) (na engleskom). Od 1. studenoga 2026. za internetske narudžbe iz zemalja izvan EU-a osim carine plaća se i naknada za rukovanje: 2 € za svaku stavku deklaracije, kao kod fiksne carine od 3 €, ali i za narudžbe iznad 150 €. Ako ih trgovina nije naplatila pri kupnji, plaća ih onaj tko prijavljuje pošiljku carini, obično prijevoznik, koji ih može tražiti od tebe prije uručenja. Ako paket ondje stoji danima, javi prodavaču.

**GZYL-ov sustav za praćenje ili neki servis za praćenje pokazuje više od Peeka.** Peek ne prikazuje korake upisane unaprijed, prije vremena koje navode, `Delivered` kojim završava GZYL-ov plan ni korake s vremenom kasnijim od trenutka svoje posljednje provjere (vidi niže). Koraci kojih u Peeku nema zato mogu biti GZYL-ov plan, a ne skeniranja. Ravnaj se po roku dostave koji ti je dala trgovina, a kad prođe, javi se prodavaču. Više u vodiču o [praćenju paketa iz Kine](guide:tracking-from-china) (na engleskom).

**`Delivered`, a ništa nije stiglo.** Taj korak može biti dio GZYL-ova plana, a ne skeniranje. Osim ako pošiljku dostavlja Swiss Post, Peek ispod "Delivered" piše: "GZYL updates on a fixed schedule, with no signature. If nothing came, ask the seller." To znači da GZYL-ovo praćenje slijedi unaprijed zadan raspored i ne pokazuje potpis te da se, ako ništa nije stiglo, trebaš obratiti prodavaču. Javi mu da paket nije stigao i pitaj koja ga tvrtka dostavlja i pod kojim brojem. Više u vodiču o [paketu koji je dostavljen, a nije stigao](guide:delivered-not-received) (na engleskom).

**Ništa nije pronađeno.** Kad GZYL ne poznaje broj, njegov sustav za praćenje pokazuje jedan rezultat: tvoj broj pod 参考号 (referentni broj), a sva su ostala polja prazna. Ako si broj YL…YQ dodao bez odabira prijevoznika, Peek umjesto imena prijevoznika pokazuje "Probably GZYL" (vjerojatno GZYL) i status "Waiting for GZYL’s first scan" (čeka se prvo GZYL-ovo skeniranje) te nastavlja pitati GZYL. Ako GZYL ni nakon tjedan dana nema taj broj, Peek pokazuje "GZYL still doesn’t list it after a week. Check the number with the sender, or choose another carrier." (ni nakon tjedan dana GZYL ga nema u sustavu; provjeri broj kod pošiljatelja ili odaberi drugog prijevoznika) i gumb "Choose another carrier" (odaberi drugog prijevoznika). U međuvremenu usporedi broj, znak po znak, s porukom prodavača.

**Izgubljen ili vraćen paket.** Prijevoz kod GZYL-a naručio je prodavač, pa povrat novca ili novi paket traži od njega ili od platforme na kojoj si kupio. Više u vodiču o [izgubljenim paketima](guide:lost-parcel) (na engleskom).

## GZYL kontakt i služba za korisnike

Na dan 4. studenoga 2026. stranica za kontakt (联系我们) GZYL-ove web-stranice na kineskom navodi telefonsku liniju dostupnu 0–24, račun na WeChatu i račun na QQ-u za postprodajnu podršku, ali ništa za primatelje: ti su kanali namijenjeni GZYL-ovim klijentima, prodavačima. Prijevoz je naručio prodavač, pa se s pitanjima o narudžbi obrati njemu, a ako ne odgovara, platformi na kojoj si kupio.

## Praćenje GZYL pošiljaka s Peekom

Broj koji počinje s YL738, a zatim ima sedam znamenki i YQ, [Peek](/) po obliku odmah pripiše GZYL-u i pokaže "Detected carrier" (prepoznat prijevoznik). Kod drugih brojeva s YL, deset znamenki i YQ oblik je tek slab znak. Svejedno ga zalijepi: Peek prati više od 3.500 prijevoznika. Dok broj dodaješ, Peek pita GZYL-ov sustav za praćenje i pokazuje "Checking tracking services…" (provjeravaju se servisi za praćenje). Ako GZYL poznaje broj, Peek javlja "GZYL has this parcel." (GZYL ima ovu pošiljku). Ako ne, pritisni "Change" (promijeni) i odaberi GZYL pod "Fits this number" (odgovara ovom broju); GZYL ćeš naći i ako upišeš 广州云链. Za pošiljku koju si već dodao koristi "Change carrier" (promijeni prijevoznika). Radi i prodavačev referentni broj, ali tek kad sam odabereš GZYL: pritisni "Change" (ili "Change carrier") i potraži GZYL. Peek tada pokazuje i broj pod kojim GZYL vodi narudžbu, s oznakom "GZYL tracks this as" (GZYL ovu pošiljku vodi pod brojem). No Peek iz broja briše crtice, pa se referentni broj s crticama ne može pronaći: tada od prodavača zatraži broj YL…YQ.

Peek čita GZYL-ov vlastiti sustav za praćenje i pokazuje status, svaki korak s mjestom te zemlju odredišta kad je GZYL-ov zapis navodi. Vremena čita po pekinškom vremenu i prikazuje ih u vremenskoj zoni tvog uređaja. Imena, adresu, poštanske brojeve, težinu, podatke o plaćanju, potpis i dokaz o dostavi koje GZYL-ov zapis može sadržavati Peek izostavlja. Očekivani datum dostave ne pokazuje jer ga GZYL ne daje.

Peek ne prikazuje nijedan korak upisan više od 12 sati prije vremena koje navodi ili više od 6 sati prije njega u istoj sekundi kao takav korak, ni korak s vremenom kasnijim od trenutka provjere po pekinškom vremenu. Ako narudžba ima korake upisane unaprijed, ne prikazuje ni `Delivered`, čak ni onaj upisan na sam dan koji navodi. Kad podatkovno sučelje sustava za praćenje nema nijednog koraka za neki broj, Peek umjesto njega čita GZYL-ovu stranicu za praćenje; ona ne pokazuje kad je korak upisan, pa ondje Peek ne prikazuje nijedan `Delivered`. Ravna se po koracima koje prikazuje, a ne po sažetom statusu u GZYL-ovu zapisu. Ako Peek ne prikaže nijedan korak narudžbe, za nju zasad nema povijesti praćenja. Kad servisi za praćenje pokazuju kopije koraka koje je Peek prepoznao kao GZYL-ov plan, uključujući `Delivered`, Peek ni te kopije ne prikazuje, čak ni kad prikazuje druge korake narudžbe. Ako je GZYL-ov sustav za praćenje nedostupan, Peek pita servise za praćenje, a njihove kopije mogu sadržavati i korake koje je GZYL upisao unaprijed.

Osim prebacivanja na Swiss Post, Peek ostaje kod GZYL-a: ako ti prodavač da broj tvrtke koja dostavlja, dodaj ga u Peek kao zasebnu pošiljku. Peek provjerava pošiljku svakih 10 minuta, a svake 2 minute kad je "Out for delivery" (u dostavi): samo toga dana, od 6 sati ujutro po lokalnom vremenu ili, ako prijevoznik navede termin dostave, od pola sata prije njegova početka, i dok odgovara vlastito praćenje GZYL-a ili Swiss Posta. Preko servisa za praćenje provjerava je svakih 15 minuta. No nijedan GZYL-ov korak koji Peek poznaje ne dobiva taj status. Ako dva dana nema novog skeniranja (12 sati dok pošiljka pokazuje "Label created" ili "Received by carrier"), provjerava je jednom na sat. Obavijesti ti može slati za "Every scan" (svako skeniranje), "Important steps" (važni koraci) ili "Delivery only" (samo dostava). "Important steps" obuhvaća GZYL-ova dva carinska koraka i `Delivered`, ali ne letove ni sortiranje. Peek ne može umjesto tebe kontaktirati GZYL ni prodavača.

## Pitanja o praćenju GZYL pošiljaka

### Što je GZYL (广州云链)?

GZYL je 广州云链货运代理有限公司 (Guangzhou Yunlian), špeditersko poduzeće iz Guangzhoua u Kini. Njegovi brojevi pošiljaka imaju oblik YL, deset znamenki i YQ.

### Je li svaki broj YL…YQ GZYL-ov?

Ne nužno. YL, deset znamenki i YQ je GZYL-ov oblik i nijedan ga drugi prijevoznik u Peekovu katalogu ne koristi. Broj koji počinje s YL738 Peek pripiše GZYL-u već po obliku; za svaki drugi najprije pita GZYL-ov sustav za praćenje. S devet ili jedanaest znamenki to nije GZYL-ov oblik.

### Zašto GZYL piše da je paket dostavljen iako nije stigao?

Korak `Delivered` može biti dio GZYL-ova plana. GZYL velik dio puta paketa upisuje unaprijed i završava ga korakom `Delivered`, a takvi su koraci plan, a ne skeniranja. Peek takve korake iz GZYL-ova sustava za praćenje ne prikazuje, uključujući `Delivered`, a ako ipak prikaže "Delivered", ispod njega dopiše da GZYL-ovo praćenje slijedi unaprijed zadan raspored. Javi prodavaču da paket nije stigao i pitaj koja ga tvrtka dostavlja.

### Tko dostavlja GZYL pošiljke u Hrvatskoj?

Peek to ne zna: GZYL-ovi koraci u tvojoj zemlji ne navode nijednu tvrtku. Pitaj prodavača koja će ti tvrtka dostaviti narudžbu i pod kojim brojem, pa taj broj dodaj u Peek kao zasebnu pošiljku. Praćenje GZYL-ove pošiljke Peek sam može prebaciti na drugu tvrtku samo u Švicarskoj i Lihtenštajnu, i to na Swiss Post.

:::sources
- [广州云链货运代理有限公司 (GZYL): početna stranica (na kineskom)](http://yunlianhouyun.com/) – ime tvrtke, web-stranica na kineskom, tražilica pošiljaka (小包查询) prema GZYL-ovu sustavu za praćenje
- [广州云链货运代理有限公司 (GZYL): 联系我们 (kontakt, na kineskom)](http://yunlianhouyun.com/contact.html) – linija 0–24, WeChat i QQ za postprodajnu podršku
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – ime tvrtke i drugi nazivi, sustav za praćenje na IP adresi, oblici YL…YQ: YL738 prepoznat odmah, ostali kao slab znak, Xinghe Freight u GZYL-ovu sustavu za praćenje i preuzimanje u Hong Kongu, traženje po broju ili referentnom broju s crticama, tekstovi koraka i njihovi statusi, redci na kineskom, gradovi iza tekstova, pekinško vrijeme, plan upisan unaprijed i `Delivered` kojim završava, odgovor za nepoznat broj, stranica za praćenje umjesto podatkovnog sučelja, Swiss Post kao jedini prijevoznik na kojeg Peek prebacuje praćenje, što Peek prikazuje, ne prikazuje i izostavlja
- [Carinska uprava: Naplata naknade za rukovanje od 1. studenog 2026.](https://carina.gov.hr/vijesti/naplata-naknade-za-rukovanje-od-1-studenog-2026/20646) – naknada za rukovanje od 2 € za svaku stavku deklaracije uz fiksnu carinu od 3 €, i za internetske narudžbe iznad 150 €
- [Europska komisija: The temporary EUR 3 customs duty and the Union handling fee (PDF, na engleskom)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – naknadu plaća onaj tko podnosi deklaraciju: platforma, prodavač ili prijevoznik
:::
