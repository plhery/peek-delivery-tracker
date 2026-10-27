---
title: Postin seuranta: lähetystunnus, tilat ja pakettiautomaatit
description: Seuraa Postin pakettia: miltä JJFI-alkuinen lähetystunnus näyttää, mitä seurannan tilat tarkoittavat, kauanko paketti odottaa automaatissa ja miten tavoitat Postin.
slug: posti-seuranta
published: 2026-10-22
updated: 2026-10-27
---

Posti on Suomen posti- ja pakettiyhtiö. Pakettia seuraat [Postin seurannassa](https://www.posti.fi/seuranta) tai OmaPosti-sovelluksessa lähetystunnuksella, tai voit liittää tunnuksen yllä olevaan kenttään. Tunnuksen saat lähettäjältä. Jos lähettäjä on merkinnyt lähetyksen tietoihin puhelinnumerosi, paketti näkyy OmaPostissa automaattisesti.

## Postin lähetystunnus: JJFI ja muut muodot

Posti puhuu lähetystunnuksesta, ja huijausviesteistä varoittaessaan myös seurantatunnuksesta. Postin omien pakettien tunnus alkaa kirjaimilla `JJFI`, ja juuri sitä Postin seuranta pyytää.

| Tunnus (keksitty) | Mikä se on |
| --- | --- |
| `JJFI12345678901234567` | Postin paketti: `JJFI` ja 17 numeroa, yhteensä 21 merkkiä. |
| `RR000000005FI` | Suomesta lähtenyt postilähetys kansainvälisessä muodossa: kaksi kirjainta, yhdeksän numeroa ja `FI`. |
| `RR000000005CN` | Ulkomailta tuleva lähetys säilyttää lähtömaan tunnuksen. Tämä päättyy kirjaimiin `CN`, eli lähetys on jätetty China Postin kuljetettavaksi. |
| `00064000000000000002` | 20-numeroinen SSCC-viivakoodi. `64` kolmen ensimmäisen numeron jälkeen viittaa Suomeen. |

Poista tunnuksesta välilyönnit ja hae yksi tunnus kerrallaan.

Automaatin avauskoodi ja palvelupisteen noutokoodi eivät ole lähetystunnuksia: niillä vain noudetaan paketti. `JJEE`-alkuinen tunnus kuuluu Viron postille [Omnivalle](carrier:omniva), `SE`-loppuinen Ruotsin [PostNordille](carrier:postnord). Jos et tiedä, kenen tunnus sinulla on, katso [seurantanumeroiden muodot](guide:tracking-number-formats) (englanniksi).

## Mitä Postin seurannan tilat tarkoittavat

Postin suomenkielinen seuranta näyttää ylimpänä paketin vaiheen ja lyhyen selityksen. "Näytä seurantatiedot" avaa yksittäiset tapahtumat. Tavallisimmat vaiheet suunnilleen siinä järjestyksessä kuin ne tulevat vastaan:

| Posti kertoo | Mitä se tarkoittaa sinulle |
| --- | --- |
| `Odotamme lähetystä` | Lähettäjä on ilmoittanut lähetyksestä, mutta paketti ei ole vielä Postilla. |
| `Valmistelemme paketin matkaa` | Posti on saanut paketin, ja se lähtee matkaan luoksesi. |
| `Paketti on ulkomailla` | Paketti on vielä matkalla Suomeen. Posti odottaa sen saapumista maahan. |
| `Saapunut maahan` | EU:n ulkopuolelta tullut paketti on Suomessa ja odottaa tullausta. |
| `Paketti on matkalla` | Paketti on kuljetuksessa. Jos se on menossa noutopisteeseen, alla lukee `Kuljetamme pakettia kohti noutopistettä.` |
| `Noutopiste vaihtuu.` | Esimerkiksi valitussa automaatissa ei ollut sopivan kokoista lokeroa tai paketti ei mahdu automaattiin. Seuranta kertoo uuden noutopisteen. |
| `Kotiinkuljetus ei onnistunut.` | Kuljettaja ei tavoittanut sinua, joten Posti vie paketin noutopisteeseen. |
| `Perillä noutopisteessä!` | Paketti odottaa sinua. Alla lukee viimeinen noutopäivä: `Paketti tulee noutaa viimeistään` ja päivämäärä. |
| `Paketti on toimitettu` | Paketti on perillä. Jos noudit sen noutopisteestä, alla lukee `Olet noutanut paketin noutopisteestä.` |
| `Kuljetamme pakettia takaisin lähettäjälle.` | Paketti palautuu, esimerkiksi siksi, ettei sitä noudettu ajoissa. |

Jos seuranta ilmoittaa `Lähetystä ei löytynyt`, syynä voi Postin mukaan olla kirjoitusvirhe, se, ettei lähetystä ole vielä rekisteröity tai luovutettu kuljetettavaksi, tai se, että lähetys on lähetetty yli 2,5 kuukautta sitten.

## Pakettiautomaatti, palvelupiste ja kotiinkuljetus

**Saapumisilmoitus.** Kun paketti on automaatissa, saat OmaPostiin, tekstiviestinä ja sähköpostina automaatin tiedot ja avauskoodin. Palvelupisteeseen tulevasta paketista saat ilmoituksen OmaPostiin, tekstiviestinä tai sähköpostina, ja tiskillä näytät noutokoodin puhelimestasi.

**Säilytysajat.** Postin taulukon mukaan:

- automaatti tai Postin palvelupiste: 5 päivää
- paketti, jonka kotiinkuljetus ei onnistunut: 5 päivää
- paketti, josta saapumisilmoitus tulee kirjeenä: 9 päivää
- ulkomailta saapunut tai palautunut paketti: 14 päivää (5 päivää, jos ulkomainen yritys on jättänyt sen Postille Postipakettina)
- paketti, jolle lähettäjä on ostanut lisää säilytysaikaa: 12 päivää

Tarkka viimeinen noutopäivä on saapumisilmoituksessa.

**Lisäaikaa.** OmaPosti-sovelluksessa noutoaikaa voi pidentää jopa 14 päivällä viimeisestä noutopäivästä. Kun pidennys on mahdollinen, paketin tiedoissa näkyy "Pidennä noutoaikaa". Hinta on 1,90 €/päivä.

**Automaatilla.** Näppäimistöllisessä automaatissa näppäile C, avauskoodi ja OK; kosketusnäytöllä valitse "Nouda paketti". Avauskoodissa on ulkoautomaateissa yhdeksän merkkiä, muissa näppäimistöllisissä automaateissa kuusi. Paketin voi noutaa puolestasi kuka tahansa, jolle annat koodin; kirjatun tai postivakuutetun lähetyksen noutamiseen tarvitaan valtakirja.

**Miksi paketti ei mennyt automaattiin.** Automaattiin mahtuu enintään 59 x 36 x 60 cm:n paketti. Paketti viedään Postin palvelupisteeseen myös, jos se pitää luovuttaa kasvotusten tai jos siinä on vaarallisia aineita (vaikkapa kynsilakkaa). Väärä puhelinnumero voi ohjata paketin muualle kuin valitsemaasi noutopisteeseen, ja jos lähin noutopiste on täynnä, paketti voidaan viedä toiseen.

**Kotiinkuljetus.** Pikkupaketti ovelle (enintään 38 x 38 x 28 cm ja 4 kg) jätetään oven taakse tai postilaatikkoon, jos et ole kotona. Kun muu kotiinkuljetus ei onnistu, Posti vie paketin lähimpään noutopaikkaan ja ilmoittaa siitä OmaPostiin, tekstiviestillä tai sähköpostilla. OmaPostin "Vaihda kotiinkuljetukseen" toimii paketille vain, jos se ei ole vielä siirtynyt lähettäjältä Postille.

**Muutokset.** Vain lähettäjä voi muuttaa osoitetta tai puhelinnumeroa. Asiakaspalvelu voi siirtää paketin toiseen noutopaikkaan kuljetusmaksua vastaan. Noutamaton paketti palautuu lähettäjälle.

## Postin paketti myöhässä, kadonnut, rikki tai tullissa

**Seuranta ei etene.** Posti muistuttaa, että kansainvälisessä seurannassa voi olla pitkiäkin taukoja. Jos jokin näyttää poikkeavalta, ota yhteyttä lähettäjään. Lisää: [miksi seuranta ei päivity](guide:tracking-not-updating) (englanniksi).

**Kadonnut.** Jos lähetystä ei kuulu, tee selvityspyyntö Postin asiakaspalveluun. Sen voi tehdä lähettäjä tai vastaanottaja. Jos pakettia ei löydy, korvausta hakee kuljetuksen maksaja, eli lähes aina lähettäjä. Verkkokaupassa myyjä vastaa kuluttajansuojalain mukaan kuljetuksesta, joten pyydä kaupalta uutta tuotetta tai kaupan perumista. Kilpailu- ja kuluttajaviraston mukaan voit tehdä huomautuksen kadonneesta paketista, jos kotimaan pakettia ei ole luovutettu 14 päivän kuluessa sovitun luovutusajan päättymisestä. Jos aikaa ei sovittu, paketti katsotaan kadonneeksi, kun kuljetukseen ottamisesta on kulunut 28 päivää.

**Toimitettu, mutta ei perillä.** Pikkupaketti on voitu jakaa postilaatikkoon, joten katso ensin sieltä. Muuten ota yhteys asiakaspalveluun.

**Rikki.** Ilmoita vauriosta heti paketin luovuttaneelle Postin työntekijälle. Kotiinkuljetuksessa tee vahingosta merkintä lähetysasiakirjoihin yhdessä kuljettajan kanssa. Ota kuvat pakkauksesta, tuotteen suojauksesta ja tuotteesta, tee vahinkoilmoitus Postin lomakkeella ja kerro lähettäjälle.

**Tulli.** EU:n ulkopuolelta tuleva tavara pitää tullata, ja tullauksen teet tulli.fi-palvelussa. Paketti odottaa Postin väliaikaisessa varastossa 20 vuorokautta Suomeen saapumisesta. Jos maksoit tullauksessa veroja, maksa vielä Postin käsittelymaksu 3,10 € Postin verkkosivuilla (paikan päällä Postissa 3,30 €). Alle 150 euron IOSS-ostokset Posti tullaa puolestasi. Tullimaksu koskee 1.7.2026 alkaen kaikkia EU:n ulkopuolisia ostoksia, ja enintään 150 euron ostoksessa se on 3 euroa lähetyksen jokaisesta tavaraerästä. 1.11.2026 alkaen verkko-ostoksista maksetaan lisäksi Tullin käsittelymaksu, 2 euroa tavaraerältä.

> Posti ei lähetä linkkiä tullauksen tai käsittelymaksun maksamista varten. Huijausviestissä voi olla aito Postin seurantatunnus: poista viesti avaamatta linkkiä.

## Postin asiakaspalvelu

Postin sivuilla 22.10.2026:

- **Puhelin:** 0100 5577. Puhelu maksaa matkapuhelin- tai paikallisverkkomaksun, myös jonotuksen ajalta. Aukioloajat ovat Postin asiakaspalvelusivulla.
- **Chat:** OmaPosti-sovelluksessa.
- **Lomakkeet:** vahinkoilmoitus sekä lomake, jolla kerrot toimitusosoitteen perillesaamattomalle paketille (sitä ei saatu toimitettua vastaanottajalle eikä palautettua lähettäjälle), kun Posti on ilmoittanut sinulle sellaisesta.

## Postin paketin seuranta Peekillä

[Peek](/) tunnistaa `JJFI`-tunnuksen Postin tunnukseksi heti. Postin linkki, jossa on `/seuranta/`, `/tracking/` tai `/uppfoljning/` (myös esimerkiksi `/en/tracking/`) ja tunnus, ohjaa Postille minkä tahansa tunnuksen. Postinumeroa ei tarvita.

`FI`-loppuisesta postitunnuksesta Peek kysyy ensin Postilta (ja Chronopostilta) ja nimeää Postin, kun Posti tuntee tunnuksen. Jos Posti ei vielä tunne sitä, näet tekstin "Not found at … yet. Peek will keep checking after you add it." Peek-tilille lisätyn paketin Peek tarkistaa ensimmäiset 6 tuntia tunnin välein (öisin tunnin tai kahden välein) ja sen jälkeen yhä harvemmin. Ilman tiliä tarkistukset loppuvat 6 tunnin kuluttua, ellet ota käyttöön ilmoitusta "Notify me when it’s found": silloin Peek tarkistaa paketin kerran päivässä, kunnes lisäyksestä on kulunut 30 päivää. Jos tarkistusnumero ei täsmää, Peek ei kysy Postilta: valitse Posti painikkeella "Change". SSCC-koodista Peek kysyy Postilta ja muilta yhtiöiltä, joiden muotoon koodi sopii; jos useampi niistä tuntee sen, valitse itse. Ulkomaisen tunnuksen saat Postille "Change"-painikkeella tai posti.fi:n seurantalinkillä, ja osan kansainvälisistä paketeista Peek seuraa itse Postille asti.

Peek lukee Postin seurannan englanniksi, joten tapahtumat näkyvät englanninkielisinä riveinä, kuten `Item is ready for a pick up` (paketti odottaa noutoa) tai `Item accepted from transport` (paketti on yhä matkalla, sanamuodosta huolimatta). Jos Posti selittää tapahtumaa, selitys on rivin perässä. Peek näyttää myös skannausten paikkakunnat kartalla, painon, mitat sekä noutopisteen nimen ja osoitteen. Ulkomaisten skannausten paikkamerkintä ULKOMAILLA jää pois. Päiväsaikaan Peek tarkistaa paketin 10 minuutin välein, jakelussa olevan 2 minuutin välein, öisin tunnin tai kahden välein, ja voi ilmoittaa muutoksista. Noutokoodi ja osoitteesi jäävät pois, eikä Peek näytä arvioitua toimitusaikaa tai lähettäjää. Usean paketin yhteistä tunnusta se ei seuraa, ja vanhat lähetykset näkyvät löytymättöminä.

## Kysymyksiä Postin seurannasta

### Mistä löydän Postin lähetystunnuksen?

Lähettäjältä, esimerkiksi verkkokaupan toimitusviestistä. Postin oma tunnus alkaa `JJFI`.

### Kauanko Posti säilyttää pakettia automaatissa?

Yleensä 5 päivää, ja tarkka viimeinen päivä on saapumisilmoituksessa. OmaPostissa voit pidentää noutoaikaa jopa 14 päivällä, hintaan 1,90 €/päivä.

### Kauanko Postin paketti on matkalla?

Pikkupaketti ovelle tulee 2–5 arkipäivässä siitä, kun se saapuu Postin käsittelyyn. Muuten näet arvioidun toimitusajan seurannasta; tullattavalle lähetykselle arvio tulee vasta tullauksen jälkeen.

### Voiko Postin pakettia seurata ilman lähetystunnusta?

Postin seurannassa ei: haku vaatii tarkan tunnuksen, eikä nimellä tai osoitteella voi hakea. OmaPostissa näkyvät kuitenkin automaattisesti lähetykset, joiden tiedoissa on puhelinnumerosi.

:::sources
- [Posti: Lähetysten seuranta](https://www.posti.fi/seuranta) – hakukenttä, vaiheiden tekstit, ilmoitukset, Lähetystä ei löytynyt -syyt, tauot kansainvälisessä seurannassa
- [Posti: Lähetyksen noutaminen](https://www.posti.fi/vastaanottaminen/lahetyksen-noutaminen) – JJFI, saapumisilmoitus, säilytysajat, pidennys ja hinta, avauskoodit, valtakirja, automaatin enimmäiskoko, siirrot, osoitteenmuutos, OmaPosti, toimitusarvio, puhelin, chat
- [Posti: Muutoksia pakettien säilytysaikoihin](https://www.posti.fi/ajankohtaista-postilla/muutoksia-pakettien-sailytysaikoihin-tavoitteena-enemman-tilaa-automaateissa-11-8-2025) – viiden päivän säilytys 1.10.2025 alkaen
- [Posti: Kotiinkuljetus](https://www.posti.fi/vastaanottaminen/kotiinkuljetus) – Pikkupaketti ovelle, toimitusaika, epäonnistunut kotiinkuljetus, toimitettu mutta ei perillä, Vaihda kotiinkuljetukseen
- [Posti: Tullaaminen](https://www.posti.fi/vastaanottaminen/tullaaminen) – tulli.fi, käsittelymaksu 3,10 € ja 3,30 €, IOSS-ostokset
- [Posti: Ulkomailta tilaaminen ja tullaus](https://www.posti.fi/vastaanottaminen/tullaaminen/ulkomailta-tilaaminen-ja-tullaus) – 20 vuorokautta varastossa, Tullin käsittelymaksu 1.11.2026 alkaen
- [Posti: Mitä Posti korvaa ja kenelle](https://www.posti.fi/asiakaspalvelu/vahinkotilanteet/mita-posti-korvaa-ja-kenelle) – selvityspyyntö, korvaus kuljetuksen maksajalle, myyjän vastuu, toimet vahingon sattuessa
- [Posti: Ilmoita vahingoittuneesta lähetyksestä](https://www.posti.fi/asiakaspalvelu/vahinkotilanteet/vahinkoilmoitus) – vahinkolomake, kuvat, ilmoitus lähettäjälle
- [Posti: Perillesaamaton paketti](https://www.posti.fi/asiakaspalvelu/vahinkotilanteet/perillesaamaton-paketti) – lomake ja määritelmä
- [Posti: Tietoturva ja huijausviestit](https://www.posti.fi/asiakaspalvelu/tietoturva-ja-huijausviestit) – seurantatunnus huijausviestissä, ei maksulinkkejä
- [Posti: Lähetysten seurannan tietosuojaseloste](https://www.posti.fi/asiakaspalvelu/ehdot-ja-selosteet/tietosuoja/tietosuojaselosteet/lahetysten-seurannan-tietosuojaseloste) – haku vain tarkalla tunnuksella, puhelun hinta
- [Posti: Postin asiakaspalvelu](https://www.posti.fi/asiakaspalvelu) – asiakaspalvelusivu
- [KKV: Posti- ja pakettijakelu](https://www.kkv.fi/kuluttaja-asiat/puhelin-ja-laajakaistapalvelut/posti-ja-pakettijakelu/) – milloin kotimaan paketti katsotaan kadonneeksi
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – mitä Peek lukee Postilta
:::
