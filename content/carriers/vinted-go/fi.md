---
title: Vinted Go -paketin seuranta: koodit, automaatit ja apu
description: Seuraa Vinted Go -pakettia Suomessa ja Euroopassa: seurantakoodit, tilojen merkitykset, 7 päivän noutoaika, noutokoodi ja miten toimit ongelmatilanteissa.
slug: vinted-go-seuranta
published: 2026-11-24
updated: 2026-11-24
---

Vinted Go on Vintedin oma toimituspalvelu, joka kuljettaa Vintedissä myytyjä paketteja pakettiautomaateista, pakettipisteistä ja naapuripisteistä toisiin noutopaikkoihin sekä tietyillä alueilla kotiovelle. Voit seurata pakettia suoraan Vintedin tilauskeskustelusta tai syöttämällä seurantakoodin [Vinted Gon seurantasivulle](https://vintedgo.com/en/tracking) tai yllä olevaan hakukenttään.

## Mistä Vinted Go -seurantakoodi löytyy ja miltä se näyttää

Seurantakoodi ja seurantalinkki löytyvät Vinted-tilauksesi keskustelunäkymästä. Kun myyjä on jättänyt paketin lähetettäväksi, seurannan päivittyminen Vintediin voi viedä jopa 48 tuntia.

Vinted Go ei julkaise erillistä numeromuotoluetteloa, mutta Peek tunnistaa seuraavat muodot (kaikki alla olevat esimerkit ovat keksittyjä):

| Seurantakoodi (keksitty) | Mikä se on |
| --- | --- |
| `1748291736405812` | 16 numeroa, jotka alkavat `17` tai `18` ja läpäisevät tarkistusnumeron: Vinted Go -muoto. Myös DHL eCommerce, TNT ja Evri käyttävät 16-numeroisia koodeja, joten Peek tarkistaa koodin Vinted Golta ensisijaisena. |
| `1748291736405813` | 16 numeroa, joiden viimeinen tarkistusnumero ei täsmää: ei sovi Vinted Go -muotoon. |
| `VGS48291736405812` | `VGS` sekä 13 tai 14 numeroa: vain Vinted Gon käyttämä koodi. |
| `ABC4UW8917364058` | 16 merkkiä: 3 kirjainta, numero, `UW`, numero ja 9 kirjainta tai numeroa. Vinted Gon seuranta tunnistaa myös nämä koodit. |

Kaksi asiaa sekoitetaan usein keskenään:

- **Seurantalinkki:** Vintedin seurantanappi avaa esimerkiksi osoitteen `vintedgo.com/en/tracking/1748291736405812`. Linkki on varmin merkki siitä, että kuljetuksesta vastaa Vinted Go.
- **Noutokoodi (PIN-koodi):** tämä ei ole seurantakoodi. Noutokoodi lähetetään sähköpostiin, tekstiviestillä tai Vinted-alustan kautta vasta, kun paketti on noudettavissa, ja se alkaa isolla kirjaimella.

Jos et löydä koodia ollenkaan, katso [kuinka löydät seurantakoodin](guide:find-tracking-number). Jos myyjä käytti muuta kuljetusyhtiötä, lue [Vinted-lähetysten seurantaopas](guide:vinted-orders).

## Vinted Go -seurannan tilojen merkitykset

Vinted Gon seurantasivu näyttää ylimpänä viimeisimmän vaiheen ja sen kellonajan sekä alempana vaiheittaisen aikajanan, jonka alta yksittäiset tapahtumat avautuvat. Yleisimmät englanninkieliset vaiheet kuljetusjärjestyksessä:

| Vinted Go näyttää | Mitä se tarkoittaa sinulle |
| --- | --- |
| `Shipment created` | Myyjä on luonut lähetystarran. Paketti ei ole vielä Vinted Golla. |
| `Parcel was dropped off at the locker` | Myyjä on jättänyt paketin automaattiin (tai pakettipisteeseen: `Parcel was dropped off at the parcel shop`). |
| `Shipped` | Paketti on luovutettu Vinted Gon kuljetettavaksi. |
| `In transit` | Paketti liikkuu lajittelukeskusten välillä kohti noutopaikkaasi. |
| `Parcel is on the way to <country>` | Paketti on siirtynyt Vinted Gon verkosta kumppaniverkkoon toiseen maahan (esimerkiksi Poste Italianelle Italiaan). |
| `Ready to collect at the locker` | Paketti odottaa noutoa automaatissa (tai pakettipisteessä: `Ready to collect at the parcel shop`) ja noutokoodi lähetetään sinulle. |
| `Ready to collect` | Noutovaihe ja 7 päivän säilytysaika ovat alkaneet. |
| `Parcel was collected by the recipient` | Sinä tai koodillasi asioinut henkilö nouti paketin. |
| `Delivered` | Paketti on noudettu ja toimitus päättynyt. |
| `Missing from locker` | Paketti puuttuu lokerosta. Ilmoita asiasta heti Vintedissä. |
| `Returning to sender` | Paketti palaa myyjälle esimerkiksi siksi, ettei sitä noudettu 7 päivän kuluessa. |

Huomaa kaksi yleistä väärinkäsitystä: `Shipped` tarkoittaa vasta myyjän jättöä, ei saapumista perille, ja `Delivered` ilmestyy vasta noudon jälkeen eikä silloin kun paketti saapuu automaattiin. Jos noutokoodi ei toimi, tarkista ensin seurannasta, että paketti on jo merkitty noudettavaksi.

## Paketin noutaminen Vinted Go -automaatista ja pakettipisteestä

**Toiminta-alue ja noutopaikat.** [Vinted Gon suomenkielisen etusivun](https://vintedgo.com/fi?country=fi&region=europe) mukaan verkosto toimii Suomessa, Ranskassa, Belgiassa, Alankomaissa, Espanjassa ja Portugalissa. Suomen etusivu neuvoo valitsemaan Vintedin kassalla toimituksen Unisendillä Vinted Go -pakettipisteeseen. Vinted Go -toimitus koostuu pakettiautomaateista (säilytyslokeroista), pakettipisteistä (Relay-pisteet) ja naapuripisteistä. Jos valitsemasi noutopaikka on täynnä tai tilapäisesti suljettu, Vinted Go voi toimittaa paketin toiseen lähistöllä sijaitsevaan pisteeseen ja kertoo uuden sijainnin saapumisilmoituksessa.

**Saapumisilmoitus ja noutaminen.** Kun paketti on noudettavissa, saat noutokoodin sähköpostitse, tekstiviestillä tai Vinted-sovelluksen kautta. Automaatilla syötät tai skannaat noutokoodin lokeron avaamiseksi; pakettipisteessä tai naapuripisteessä esität noutokoodin tai henkilöllisyystodistuksen henkilökunnalle. Aukioloajat näkyvät Vinted Gon kartalla.

**Säilytysaika on 7 päivää.** [Vinted Gon UKK-sivun](https://vintedgo.com/fi/faq?country=fi&region=europe) ja [käyttöehtojen](https://vintedgo.com/fi/terms-and-conditions?country=fi&region=europe) mukaan pakettia säilytetään noutopaikassa **7 kalenteripäivää**, eikä jo alkanutta palautusta voi pysäyttää. UKK:n mukaan määräaikaa ei voi pidentää, mutta käyttöehdot kuvaavat maksullisen noutoajan pidennyksen: noutoajan viimeisen vuorokauden aikana voit ostaa 24 tuntia lisää, tarvittaessa useamman kerran, automaatissa tai pakettikeskuksessa odottavalle paketille, jos Vinted Go näyttää sen olevan saatavilla. Vinted Go lähettää muistutuksen 3 päivän kuluttua saapumisesta. Noutamatta jäänyt paketti palautuu myyjän lähetyspisteeseen, jolloin [Vintedin ohjeen](https://www.vinted.fi/help/1601) mukaan tilaus peruuntuu automaattisesti ja saat hyvityksen.

**Arvioitu toimitusaika ja kotiinkuljetus.** Käyttöehtojen tavoitetoimitusaika jätöstä noutopaikkaan on 3–6 arkipäivää (ilman viikonloppuja ja pyhäpäiviä). Ranskassa osa pisteistä tarjoaa myös Uber-kuriirin kotiinkuljetuksen erillisestä linkistä; kotiinkuljetuksessa tehdään ehtojen mukaan yksi yritys varatulla aikaikkunalla tai kaksi yritystä ilman varausta.

## Kun Vinted Go -seuranta ei päivity tai paketti katoaa

Kaikki tilausta koskevat reklamaatiot, katoamiset ja vahingot hoidetaan Vintedin asiakaspalvelun kautta — ei suoraan Vinted Gon kautta.

**Ei päivityksiä muutamaan päivään.** Vinted Gon UKK toteaa, että matkan aikana on tavallista, ettei seuranta päivity pariin päivään. Lisätietoa löydät oppaasta [miksi seuranta ei päivity](guide:tracking-not-updating).

**Paketti ei ole saapunut.** [Vintedin ohjesivun](https://www.vinted.fi/help/657) ja Vinted Gon UKK:n mukaan sinun tulee odottaa **25 arkipäivää** siitä päivästä, kun tilaus on merkitty lähetetyksi. Jos paketti ei ole silloinkaan tullut perille, avaa keskustelu ja paina ”Minulla on ongelma” pyytääksesi hyvitystä.

**Vahingoittunut, väärä tuote tai tyhjä lokero.** Kun tilaus merkitään toimitetuksi, sinulla on **2 päivää** aikaa ilmoittaa ongelmasta Vintedissä (Pro-myyjien tilauksissa 14 päivää). Jos huomaat paketin vaurioituneeksi automaatilla, ota paketti ulos ja kuvaa se heti, sillä lokero ei avaudu uudelleen.

**Merkitty toimitetuksi, mutta et ole saanut pakettia.** Ilmoita tästä Vintedissä 2 päivän kuluessa toimitukseksi merkitsemisestä. Katso myös [merkitty toimitetuksi mutta puuttuu](guide:delivered-not-received).

## Vinted Gon asiakaspalvelu ja yhteystiedot

Vinted Go ei tarjoa ostajille sähköpostiosoitetta tai yhteydenottolomaketta yleisiin tilauskysymyksiin:

- **Tilausongelmat** (myöhästyminen, katoaminen, vaurio, väärä tuote): ota yhteyttä Vintedin asiakaspalveluun oman Vinted-tilisi tilauskeskustelun kautta.
- **Ongelma pakettiautomaatilla:** Ranskassa, Belgiassa, Alankomaissa ja Espanjassa automaatin aloitusnäytön ”?”-painike näyttää paikallisen tukipuhelinnumeron ja aukioloajat (Alankomaissa ja Belgiassa FAQ mainitsee myös numeron `+31 20 793 2587`). Portugalissa automaatin ”Obter ajuda” -painikkeen QR-koodi avaa chatin päivittäin klo 7.00–22.00 paikallista aikaa.

> [Vintedin ohje](https://www.vinted.fi/help/628) varoittaa, että tietojenkalasteluviestejä tulee puhelimeen, sähköpostiin ja Vintedin postilaatikkoon: älä avaa epäilyttäviä linkkejä äläkä anna maksutietojasi viestillä. Katso myös [huijaustekstiviestit toimituksista](guide:delivery-scam-texts).

## Vinted Go -paketin seuraaminen Peekissä

[Peek](/) tunnistaa `VGS`-alkuiset koodit suoraan Vinted Go -lähetyksiksi ("Detected carrier"), samoin `vintedgo.com`-seurantalinkit. Pelkän 16-numeroisen `17`- tai `18`-alkuisen koodin (kun tarkistusnumero täsmää) kohdalla Peek kysyy ensin Vinted Golta ja DHL eCommercelta (sekä tietyissä numeroissa muilta yhtiöiltä) ja nimeää Vinted Gon, kun se tunnistaa paketin; `UW`-koodin se tarkistaa Vinted Golta. Jos oikea kuljettaja ei löydy heti, napauta "Change" tai pakettinäkymässä "Change carrier" ja valitse Vinted Go — Peek seuraa yli 4 500 kuljetusyhtiötä.

Peek hakee tapahtumat suoraan Vinted Gon seurannasta ilman tiliä tai postinumeroa ja näyttää tilan ("Label created", "Received by carrier", "In transit", "Ready for pickup", "Delivered", "Returned to sender"), tapahtumatekstit Vinted Gon sanoin, lajittelukeskusten paikkakunnat sekä noutoautomaatin tai -pisteen nimen (ei kuitenkaan naapuripisteitä). Noutopaikan katuosoite, aukioloajat ja noutoajan päättymispäivä jäävät Vinted Gon omalle sivulle, eikä Vinted Go anna arvioitua toimituspäivää tai painoa. Jos Vinted Go luovuttaa paketin toiselle verkolle uudella koodilla (tai jos 16-merkkisen `UW`-koodin viimeiset 13 merkkiä vastaavat Poste Italianen koodia), Peek voi seurata pakettia myös siellä, jos se lukee kyseisen verkon seurantaa suoraan; Poste Italianen koodin se seuraa siellä heti, kun Postella on tuorein tapahtuma.

Kun paketti on avoinna Peekissä tai sen toimituspäivänä ilmoitusten ollessa päällä, Peek tarkistaa tilan jopa 10 minuutin välein; muulloin kerran tunnissa. Kahden päivän hiljaiselon jälkeen (tai 12 tunnin jälkeen tilassa "Label created" tai "Received by carrier") Peek tarkistaa kerran tunnissa, seurasitpa sitä miten tahansa, ja seitsemän päivän jälkeen tilassa "Label created" joka 6. tunti. Jos vain seurantapalvelut vastaavat, tarkistusväli on tunti ja yhden hiljaisen päivän jälkeen 3 tuntia. Kun uusin tapahtuma on neljä päivää vanha ja paketti on yhä tilassa "Label created", "Received by carrier" tai "In transit", Peek näyttää päiväyksen (esimerkiksi "No tracking update since Mon 12 Oct"), viestin "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking Vinted Go." sekä linkin "Get help from Vinted Go". Yli kolmen viikon hiljaiselon jälkeen näkyy "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking Vinted Go."

Voit valita ilmoituksille vaihtoehdon "Every scan", "Important steps" tai "Delivery only". Peek vain seuraa lähetystä eikä voi ottaa yhteyttä Vintediin tai Vinted Gohon puolestasi.

## Usein kysytyt kysymykset Vinted Go -seurannasta

### Onko Vinted Go luotettava palvelu?

Kyllä. Vinted Go on Vintedin oma logistiikkapalvelu, jota hallinnoi Vilnassa, Liettuassa rekisteröity yhtiö Vinted Go, UAB.

### Kuinka kauan Vinted Go -toimitus kestää?

Käyttöehtojen mukainen tavoiteaika jätöstä noutopaikkaan on 3–6 arkipäivää ilman viikonloppuja ja pyhäpäiviä. Jos paketti viivästyy, Vinted pyytää odottamaan 25 arkipäivää ennen katoamisilmoituksen tekemistä.

### Kuinka kauan pakettia säilytetään Vinted Go -noutopaikassa?

Pakettia säilytetään 7 kalenteripäivää. Automaatissa tai pakettikeskuksessa odottavalle paketille Vinted Go voi tarjota maksullisen 24 tunnin pidennyksen noutoajan viimeisen vuorokauden aikana. Noutamaton paketti palautuu myyjälle, jolloin Vinted peruuttaa tilauksen ja palauttaa maksun ostajalle.

### Miksi Vinted Go -seurantakoodi ei toimi?

Seurannan aktivoituminen voi kestää jopa 48 tuntia myyjän jätöstä. Tarkista myös mahdolliset näppäilyvirheet; lisäksi Vinted Go lakkaa näyttämästä paketin seurantaa kuuden kuukauden kuluttua lähetystarran luomisesta.

:::sources
- [Vinted Go Suomi: Etusivu](https://vintedgo.com/fi?country=fi&region=europe) – toiminta-alue (Suomi, Ranska, Belgia, Alankomaat, Espanja, Portugali), Unisend kassalla
- [Vinted Go Suomi: Usein kysyttyä](https://vintedgo.com/fi/faq?country=fi&region=europe) – noutokoodi, 7 päivän säilytysaika, lähin vapaa piste, 25 arkipäivää, 2 päivän ilmoitusaika, seurannan tauot
- [Vinted Go Suomi: Käyttöehdot](https://vintedgo.com/fi/terms-and-conditions?country=fi&region=europe) – Vinted Go UAB, pistetyypit, 3–6 arkipäivää, muistutus, maksullinen noutoajan pidennys, kotiinkuljetusyritykset, reklamaatiot Vintedin kautta
- [Vinted Go: Track your parcel](https://vintedgo.com/en/tracking) – seurantalomake ja tilojen nimet
- [Vinted Go: Frequently Asked Questions](https://vintedgo.com/en/faq?country=fr&region=europe) – noutokoodi alkaa isolla kirjaimella, automaattien tukipainike
- [Vinted: Envoi avec Vinted Go](https://www.vinted.fr/help/1110) – Uber-kotiinkuljetus Ranskassa
- [Vinted: Comment utiliser les consignes Vinted Go ?](https://www.vinted.fr/help/1109) – 2 ja 14 päivän ilmoitusaika
- [Vinted Suomi: Paketin seuranta](https://www.vinted.fi/help/100) – seurantakoodi keskustelussa ja 48 tunnin viive
- [Vinted Suomi: Paketin noutaminen](https://www.vinted.fi/help/1601) – noutokoodi, palautuminen ja hyvitys, 2 päivän vahvistusaika
- [Vinted Suomi: Paketti ei saapunut perille](https://www.vinted.fi/help/657) – 25 arkipäivän odotusaika ja ”Minulla on ongelma”
- [Vinted Suomi: Tietojenkalasteluviestien välttäminen](https://www.vinted.fi/help/628) – kalasteluviestien kanavat, epäilyttävät linkit, maksutiedot
- [Universal Parcel Scraper: Vinted Go](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/vinted-go) – koodimuodot, Luhn-tarkistus, tilakartoitus, 6 kuukauden rajoitus, Poste Italiane -siirto ja Peekin päivitysrytmi
:::
