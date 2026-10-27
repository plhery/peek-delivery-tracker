---
title: Sledenje pošiljk Pošte Slovenije: statusi, prevzem, kontakt
description: Sledite pošiljki Pošte Slovenije: oblika sprejemne številke, pomen statusov, koliko časa paket čaka na pošti ali v PS Paketomatu in kje dobite pomoč.
slug: posta-slovenije-sledenje-posiljk
published: 2026-10-25
updated: 2026-10-27
---

Pošta Slovenije je izvajalec univerzalne poštne storitve v Sloveniji: pisma in pakete dostavlja po vsej državi, tudi tiste, ki prispejo iz tujine. Pošiljki sledite v storitvi [Sledenje pošiljk](https://moja.posta.si/tracking) na moja.posta.si, v aplikaciji MojaPošta ali tako, da številko prilepite v polje zgoraj. Številko običajno najdete v sporočilu pošiljatelja, na primer v e-pošti spletne trgovine o odpremi.

## Sprejemna številka pošiljke

Pošta Slovenije številki za sledenje pravi sprejemna številka, v pomoči za MojoPošto tudi sledilna številka. Ima 13 znakov: dve črki za vrsto pošiljke, osem števk, kontrolno števko in oznako države. Pošiljke, oddane v Sloveniji, imajo na koncu oznako `SI`.

| Številka (izmišljena) | Kaj je |
| --- | --- |
| `CA000000005SI` | Paket: paketi imajo oznake od `CA` do `CZ`. |
| `RR000000005SI` | Priporočeno pismo: od `RA` do `RZ`. Vrednostna pisma imajo oznake od `VA` do `VZ`. |
| `EE000000005SI` | Hitra pošta: od `EA` do `EZ`. |
| `RR000000005CN` | Pošiljka iz tujine: ohrani številko tuje pošte z oznako države, iz katere prihaja (tu `CN`, Kitajska). |

Druga oznaka države pomeni drugo pošto: `…HR` je [hrvaška](carrier:hrvatska-posta), `…AT` [avstrijska](carrier:austrian-post), `…CN` [kitajska pošta](carrier:china-post). Ko tako pošiljko prevzame Pošta Slovenije, v Sledenju pošiljk pod isto številko vidite tudi korake v Sloveniji.

Če je pošiljatelj na pošiljki navedel vašo mobilno številko, se paket po prijavi v MojoPošto sam prikaže v razdelku Moji paketi, kjer so pošiljke zadnjih 60 dni.

Sledenje pošiljk pokaže pošiljke, sprejete v zadnjih dveh mesecih. Za neznano številko izpiše `Pošiljke s sprejemno številko nismo našli.` in `Preverite številko in poskusite znova.` Če piše `Nismo še prejeli vseh podatkov o paketu.`, preverite znova čez 24 ur.

## Kaj pomenijo statusi pri sledenju Pošte Slovenije

Sledenje pošiljk korake privzeto izpiše v slovenščini, s preklopom na EN pa v angleščini. Peek prikazuje angleško besedilo, zato so v tabeli angleški statusi, v vrstnem redu, v katerem jih paket običajno dobi. Mnogi imajo še nadaljevanje s podrobnostjo ali naslednjim korakom.

| Status (angleško) | Kaj pomeni za vas |
| --- | --- |
| `The item information has been received in the Pošta Slovenije system` | Pošiljatelj je poslal podatke o pošiljki, paketa pa Pošti Slovenije še ni izročil. |
| `The item has arrived at Pošta Slovenije` | Pošiljka je bila oddana na pošti ali v centru, ki ga navaja korak. Pošiljka iz tujine je s tem prispela v Slovenijo. |
| `Posting/collection` | Pošiljka je bila oddana na pošti v državi pošiljatelja. |
| `Arrival at outward OE` | Pošiljka je v izmenjalni pošti, od koder gre v tujino. |
| `Departure from outward OE` | Pošiljka je zapustila izmenjalno pošto in je na poti v namembno državo. |
| `The item is expected to be delivered today` | Pošiljka je pri pismonoši in bo predvidoma dostavljena danes. |
| `The item is on its way to the selected parcel locker` | Paket je na poti v PS Paketomat, ki ste ga izbrali. |
| `Unsuccessful delivery attempt` | Dostava ni uspela ali pošiljke niste prevzeli. Nadaljevanje pove, kaj sledi, na primer `The item will be redirected to the post office`. |
| `The item is ready for pickup at the post office` | Pošiljka vas čaka na pošti, ki jo navaja korak. |
| `Unsuccessful delivery` | Paketa niste pravočasno prevzeli iz PS Paketomata, zato gre na pošto. |
| `The item was delivered to the mailbox / P.O. box` | Dostavljeno v hišni predalčnik ali poštni predal. Če sledi `The item was delivered to the doorstep`, je bil paket izročen na vašem naslovu. |
| `Unsuccessful delivery attempt. Item is being returned to the sender` | Pošiljke niste prevzeli v roku, zato se vrača pošiljatelju. |

Korak o dostavi po vračilu pomeni, da je paket spet pri pošiljatelju. Če se stanje več dni ne spremeni, preberite, [kaj storiti, ko se sledenje ne posodablja](guide:tracking-not-updating) (v angleščini).

## Obvestilo o prispeli pošiljki, pošta in PS Paketomat

Če vas pismonoša ne najde doma, vam pusti obvestilo o prispeli pošiljki, na katerem piše, kje in do kdaj jo lahko prevzamete. Obvestilo lahko dobite tudi po elektronski poti. Koliko časa pošiljka čaka, je odvisno od storitve:

| Pošiljka | Koliko časa čaka |
| --- | --- |
| Pošiljka univerzalne storitve, npr. priporočeno pismo ali paket | 15 dni od dneva, ko ste bili obveščeni. Paket iz tujine lahko čaka manj, če je pošiljatelj ob oddaji označil krajši rok. |
| Paket MojPaket (pošiljatelj ga odda na podlagi pogodbe s Pošto Slovenije) | 8 dni na pošti. Rok lahko podaljšate, ob prevzemu pa plačate storitev Hramba na pošti po ceniku. |
| PS Paketomat | 60 ur od SMS-a ali e-sporočila, nato še 5 dni na izročilni pošti. |

Roki se štejejo v koledarskih dneh od dneva po obvestilu. Ko rok poteče, se pošiljka vrne pošiljatelju.

**PS Paketomat.** V Sloveniji je več kot 300 PS Paketomatov, ki delujejo 24 ur na dan. Predal odprete s QR-kodo ali PIN-kodo iz obvestila, ki ga dobite po SMS-u, e-pošti ali v aplikaciji MojaPošta. V paketomatu lahko prevzamete tudi pošiljko z odkupnino ali označeno vrednostjo do 500 EUR: odkupnino plačate prek povezave PayByLink v obvestilu, s kartico, Flikom ali Valu-jem, nato dobite kodo za prevzem.

**Moja dostava – moja izbira.** Paket lahko preusmerite tudi, ko je že na poti: v PS Paketomat, na pošto, na bencinski servis Petrol (z doplačilom po Petrolovem ceniku), v paketnik Direct4.me, na drug naslov v Sloveniji ali na dogovorjeno mesto (terasa, balkon, garaža, lopa, pred vhodom), lahko pa izberete enega od treh ponujenih datumov dostave. Povezavo dobite prek aplikacije MojaPošta, Vibra ali SMS-a, zato ob spletnem nakupu navedite svojo mobilno številko. V MojiPošti v razdelku Moji paketi izberite paket in nato Preusmeri paket. Preusmeritev ni mogoča, ko je paket že v končni fazi dostave, pri dodatnih storitvah ali posebnih zahtevah pošiljatelja, pri preveliki teži ali merah in pri odkupnini nad 500 EUR.

**Kdo lahko prevzame.** Pošiljko lahko prevzamete vi, oseba, ki ste jo pooblastili, ali odrasel član gospodinjstva. Kdor pismonoši ni znan, pokaže osebni dokument, na primer osebno izkaznico, potni list ali vozniško dovoljenje. Za pogoste prevzeme Pošta Slovenije priporoča overjeno pooblastilo (obrazec P-71, overitev na pošti, pri pismonoši ali na upravni enoti): z njim pooblastite do pet oseb, ki prevzem potrdijo le s podpisom. Neoverjeno pooblastilo sestavite sami, pooblaščenec pa ga vsakič pokaže skupaj z osebnim dokumentom. Za en sam prevzem izpolnite besedilo na hrbtni strani obvestila o prispeli pošiljki.

## Pošiljka Pošte Slovenije zamuja, se je izgubila ali je poškodovana

Večina pošiljk je po Sloveniji dostavljena naslednji dan po oddaji (D+1), najkasneje pa v treh dneh (D+3). Dan oddaje, sobote, nedelje in prazniki se ne štejejo. Pri mednarodnih pošiljkah je sledenje odvisno od tujih poštnih operaterjev, čas carinskega postopka pa se v rok ne šteje.

**Carina.** Za blagovne pošiljke iz držav zunaj EU je treba opraviti carinske formalnosti, sicer jih Pošta Slovenije ne more dostaviti. Po SMS-u, e-pošti ali pošti dobite obvestilo, naj podatke oddate prek Portala Uvoz. Obvestilo včasih pride že pred prispetjem pošiljke v Slovenijo: če podatke oddate takoj, je dostava najhitrejša. Pošta Slovenije za carinsko zastopanje pošiljke do 150 EUR zaračuna 6,50 EUR pri spletnem plačilu ali 9,00 EUR ob dostavi, za darila do 45 EUR 3,00 ali 5,50 EUR, nad 150 EUR pa 28,00 ali 30,50 EUR. Za pošiljke e-trgovine do 150 EUR od 1. 7. 2026 velja začasna carinska dajatev 3 EUR, praviloma na vsako vrsto izdelka, in DDV. Če ste naročili prek ureditve IOSS, ste DDV plačali že ob naročilu. Od 1. novembra 2026 bo treba za vsako postavko, ne glede na vrednost pošiljke, plačati še 2 EUR pristojbine za obdelavo.

**Reklamacija.** Preden jo vložite, še enkrat preverite status pošiljke. Reklamacijo vložite na pošti s poizvednico (obrazec P-105, za mednarodno pošiljko CN-08) ali na reklamacije.posta.si, v treh mesecih po oddaji pošiljke v notranjem prometu oziroma v šestih mesecih v mednarodnem. Poškodovano pošiljko ali manjkajočo vsebino reklamirajte takoj ob vročitvi oziroma najkasneje v 30 dneh od dostave, na pošti pa pokažite zunanjo in notranjo ovojnino ter poškodovano vsebino. Odškodnino lahko uveljavlja tudi naslovnik (v mednarodnem prometu le, kadar to dopuščajo pravila Svetovne poštne zveze). Pošta Slovenije mora odgovoriti v 15 dneh, pri mednarodni pošiljki v dveh mesecih. Če ne odgovori ali vam ne ugodi, lahko v 15 dneh brezplačno predlagate rešitev spora pri agenciji AKOS.

## Kontakt in pomoč Pošte Slovenije

Kot je posta.si navajala 25. oktobra 2026:

- **Kontaktni center:** 080 14 00 (brezplačna številka) ali +386 2 449 20 00, od ponedeljka do petka od 7.00 do 17.00; ob sobotah, nedeljah in praznikih je zaprt.
- **E-pošta:** info@posta.si; za sledenje sledenje.posiljk@posta.si; za MojoPošto PomocMojaPosta@posta.si; za uvoz info.carinska@posta.si.
- **Pio**, interaktivna poštna asistentka Pošte Slovenije, in obrazec »Imate vprašanje? Pišite nam.« na posta.si.
- **Osebno:** na pošti; najbližjo najdete z [iskalnikom poslovalnic](https://www.posta.si/iskalnik-poslovalnic).

> Pošta Slovenije SMS-e pošilja pod imenoma »Paketomat« in »POSTA«, e-pošto samo z domene @posta.si; vsa njena sporočila so v slovenščini, povezave v njih pa vodijo na domeno posta.si. Nikoli ne zahteva izključno plačila vnaprej: poštnino ali odkupnino lahko vedno plačate tudi ob prevzemu. Lažna sporočila prihajajo po SMS-u, e-pošti, WhatsAppu in Vibru. Ne klikajte povezave in ne plačujte; sprejemno številko raje preverite v Sledenju pošiljk. Če ste vnesli podatke kartice, takoj pokličite banko. Če je bil denar že odtegnjen, podajte prijavo na najbližji policijski postaji; goljufijo lahko prijavite tudi pri SI-CERT.

## Sledenje pošiljk Pošte Slovenije s Peekom

[Peek](/) številko, ki se začne s črko C, E, L ali R in ima na koncu `SI`, takoj pripiše Pošti Slovenije, če je kontrolna števka pravilna. Za druge številke s `SI` na koncu, na primer vrednostno pismo `VA…SI`, Peek takoj vpraša Pošto Slovenije in [Chronopost](carrier:chronopost), ali pošiljko poznata, nato pa izpiše »Pošta Slovenije has this parcel.« ali »Not found at … yet. Peek will keep checking after you add it.« Pri napačni kontrolni števki Pošte Slovenije ne vpraša: preverite, ali ste številko pravilno prepisali. Povezavo moja.posta.si/tracking s številko lahko prilepite tudi v celoti; Peek jo vedno pripiše Pošti Slovenije.

Pošiljko iz tujine Peek najprej spremlja pri tuji pošti. Če ta kot namembno državo navede Slovenijo ali je sploh ne navede, vi pa pošiljko dodate iz Slovenije, Peek isto številko preveri še pri Pošti Slovenije. Ko ta pokaže korak, ki ni starejši od zadnjega koraka tuje pošte, Peek pošiljko naprej spremlja pri njej, zgodovina tuje pošte pa ostane. Pošto Slovenije lahko pod »Change« izberete tudi sami, saj njeno iskanje sprejme vsako poštno številko te oblike.

Peek bere storitev, na kateri deluje aplikacija MojaPošta. Podnevi pošiljko preveri vsakih 10 minut, ko je v dostavi vsaki 2 minuti, ponoči vsako uro ali dve, in vas lahko obvesti, ko se status spremeni. Pri korakih vidite pošto, pri dostavljeni pošiljki čas dostave, pri pošiljki, ki čaka na pošti, pa to pošto (pri PS Paketomatu ne). Pričakovanega dneva dostave in teže ni. Korake v tujini Peek bere po tamkajšnjem času in jih prikaže v časovnem pasu vaše naprave.

## Vprašanja o sledenju pošiljk Pošte Slovenije

### Zakaj so statusi v Peeku v angleščini?

Peek prevzame angleško besedilo, ki ga Pošta Slovenije pripravi za vsak korak; enako ga vidite v Sledenju pošiljk, ko preklopite na EN. V slovenščini so koraki v Sledenju pošiljk na moja.posta.si.

### Ali lahko sledim paketu iz tujine?

Da, s številko, ki vam jo je dal pošiljatelj; konča se z oznako države, iz katere paket prihaja. Ko paket prispe v Slovenijo, Sledenje pošiljk pokaže tudi korake Pošte Slovenije. Prej so podatki odvisni od pošte v državi pošiljatelja.

### Koliko časa Pošta Slovenije hrani paket?

Paket univerzalne storitve 15 dni od obvestila, paket MojPaket 8 dni na pošti. V PS Paketomatu paket čaka 60 ur, nato še 5 dni na izročilni pošti. Potem se vrne pošiljatelju.

### Kaj storim, če piše, da je paket dostavljen, pa ga nimam?

`The item was delivered to the mailbox / P.O. box` pomeni, da je bil paket vložen v hišni predalčnik ali poštni predal ali izročen na vašem naslovu. Prevzame ga lahko tudi pooblaščena oseba ali odrasel član gospodinjstva, zato najprej vprašajte domače. Nato vložite reklamacijo na pošti ali na reklamacije.posta.si. Več o tem v vodiču [dostavljeno, a ne prejeto](guide:delivered-not-received) (v angleščini).

### Kaj pomeni »Pošiljke s sprejemno številko nismo našli«?

Sledenje pošiljk številke ne pozna. Preverite, ali ste jo vpisali pravilno, in poskusite znova; sledenje pokaže le pošiljke, sprejete v zadnjih dveh mesecih. Če težava ostane, pokličite 080 14 00 ali pišite na PomocMojaPosta@posta.si.

:::sources
- [Pošta Slovenije: Sledenje pošiljk (MojaPošta)](https://moja.posta.si/tracking) – 13-mestna sprejemna številka, dva meseca, preklop SI/EN, sporočila o napaki in nepopolnih podatkih, doplačilo za Petrol
- [Pošta Slovenije: MojaPošta – Center za pomoč](https://www.posta.si/zasebno/mojaposta/center-za-pomoc) – sledilna številka, Moji paketi za 60 dni, obvestilo s QR-kodo in PIN-om, Preusmeri paket in omejitve, Pio, PomocMojaPosta@posta.si
- [Pošta Slovenije: Priročnik za pravilno opremo paketov](https://www.posta.si/poslovno-site/Documents/Prirocnik-za-pravilno-opremo-malih-poslovnih-paketov-poslovnih-paketov-PPIN-PPVD-palet-in-tovora.pdf) – sestava sprejemne številke, oznake za pakete, hitro pošto, priporočena in vrednostna pisma
- [Pošta Slovenije: PS Paketomat](https://www.posta.si/zasebno/postne-storitve/ps-paketomat) – več kot 300, 24 ur na dan, koda v SMS-u, 60 ur, 5 dni na izročilni pošti, 500 EUR, PayByLink
- [Pošta Slovenije: Moja dostava – moja izbira](https://www.posta.si/zasebno/postne-storitve/prejemanje/moja-dostava-moja-izbira) – možnosti preusmeritve, povezava v MojiPošti, Vibru ali SMS-u, mobilna številka
- [Pošta Slovenije: Pooblastilo za prevzem pošiljk](https://www.posta.si/zasebno/postne-storitve/prejemanje/pooblastilo-za-prevzem-posiljk) – overjeno in neoverjeno pooblastilo, hrbtna stran obvestila o prispeli pošiljki
- [Pošta Slovenije: Splošni pogoji izvajanja univerzalne poštne storitve](https://www.posta.si/Documents/Zakoni%20in%20splo%c5%a1ni%20pogoji/Splo%c5%a1ni%20pogoji%20izvajanja%20univerzalne%20po%c5%a1tne%20storitve.pdf) – izvajalec univerzalne storitve, istovetnost, vročitev, obvestilo, P-71, 15 dni, reklamacija, odgovor, AKOS, naslovnikova odškodnina
- [Pošta Slovenije: Splošni pogoji poslovanja s paketi MojPaket](https://www.posta.si/Documents/Zakoni%20in%20splo%C5%A1ni%20pogoji/Splosni-pogoji-poslovanja-s-paketi-MojPaket.pdf) – pogodba, 8 dni, Hramba na pošti, vračilo pošiljatelju
- [Pošta Slovenije: Splošni pogoji PS Paketomat](https://www.posta.si/zasebno-site/Documents/Splosni%20pogoji%20PS%20Paketomat.pdf) – veljajo od 1. 4. 2026 za vse pošiljke v PS Paketomatu: 60 ur, QR- ali PIN-koda, 5 dni na pošti, vračilo
- [Pošta Slovenije: Roki prenosa](https://www.posta.si/roki-prenosa) – D+1, D+3, dnevi, ki se ne štejejo, carinski postopek
- [Pošta Slovenije: Sledenje pošiljk (poslovno)](https://www.posta.si/poslovno/logisticne-storitve/informacijske-resitve/sledenje-posiljk) – omejitve pri mednarodnih pošiljkah, sledenje.posiljk@posta.si, brezplačna številka
- [Pošta Slovenije: Uvoz pošiljk](https://www.posta.si/zasebno/postne-storitve/prejemanje-posiljk-iz-tujine) – carinske formalnosti, obvestilo, Portal Uvoz, info.carinska@posta.si
- [Pošta Slovenije: Cenik storitev carinskega zastopanja](https://www.posta.si/zasebno-site/Documents/Ceniki/Cenik-storitve-carinskega-posredovanja.pdf) – nadomestila od 1. 7. 2026
- [FURS: Nakup prek spleta iz tretjih držav](https://www.fu.gov.si/zivljenjski_dogodki_prebivalci/nakup_prek_spleta_iz_tretjih_drzav) – carinska dajatev 3 EUR na vrsto izdelka, DDV, IOSS
- [GOV.SI: Za spletne pošiljke iz držav zunaj EU dodatna pristojbina dveh evrov](https://www.gov.si/novice/2026-10-15-za-spletne-posiljke-iz-drzav-zunaj-evropske-unije-dodatna-pristojbina-dveh-evrov/) – 2 EUR na postavko od 1. novembra 2026, ne glede na vrednost
- [European Commission: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 3 EUR od 1. julija 2026, 2 EUR od 1. novembra 2026
- [Pošta Slovenije: Reklamacije in pritožbe](https://www.posta.si/o-nas/predstavitev/reklamacije) – roki, obrazci, ovojnina, reklamacije.posta.si, ure kontaktnega centra
- [AKOS: Spori med izvajalci poštnih storitev in končnimi uporabniki](https://www.akos-rs.si/uporabniki-storitev/raziscite/spori-med-operaterji-oz-izvajalci-postnih-storitev-in-koncnimi-uporabniki) – brezplačno reševanje sporov, najprej reklamacija
- [AKOS: Kako se lahko pritožim](https://www.akos-rs.si/uporabniki-storitev/raziscite/spori-med-operaterji-oz-izvajalci-postnih-storitev-in-koncnimi-uporabniki/kako-se-lahko-pritozim-kako-oddam-vlogo) – 15 dni, ko izvajalec na reklamacijo ne odgovori ali ji ne ugodi
- [Pošta Slovenije: Kontakt in pomoč](https://www.posta.si/o-nas/predstavitev/kontakt-in-pomoc) – 080 14 00, +386 2 449 20 00, ure, info@posta.si
- [Pošta Slovenije: Spletne prevare](https://www.posta.si/spletne-prevare) – imena pošiljatelja SMS-ov, @posta.si, slovenščina, brez plačila vnaprej, banka, policija, SI-CERT
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – kaj Peek prebere
:::
