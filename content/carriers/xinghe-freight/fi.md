---
title: Xinghe Freight seuranta: GZXH…YQ-numero ja tilat
description: Seuraa Xinghe Freight (星和货运) -pakettia Kiinasta: GZXH…YQ-numero, miksi seuranta käyttää GZYL-palvelua, ennalta kirjoitetut Delivered-vaiheet, EU-maksut ja tuki.
slug: xinghe-freight-seuranta
published: 2026-11-27
updated: 2026-11-27
---

Xinghe Freight (星和货运, 广州星和货运代理有限公司) on vuonna 2014 perustettu kiinalainen huolintayritys Guangzhoussa, joka hoitaa meri-, lento- ja maakuljetuksia, laivanselvitystä sekä tullausta. Jos kiinalainen verkkokauppa lähetti tilauksesi Xinghe Freightin kautta, lähetyksen seurantanumero on 16 merkkiä pitkä ja muodostuu kirjaimista `GZXH`, kymmenestä numerosta ja lopusta `YQ`: liitä se yllä olevaan kenttään. Xinghe Freightin oma verkkosivusto lähettää seurantakyselyt [GZYL:n](carrier:gzyl) salaamattomaan HTTP-seurantaan, jossa vaiheet — myös `Delivered` — voivat olla aikataulutettuja etukäteen todellisten skannausten sijaan. Peek lukee kyseisen seurannan puolestasi, erottelee ennakkoon kirjoitetut ennusteet oikeista skannauksista ja kysyy tarvittaessa tietoja ulkoisilta seurantapalveluilta.

## Xinghe Freight -seurantanumero: miltä se näyttää

Seurantanumerot, jotka Peek tunnistaa Xinghe Freightin lähetyksiksi, sisältävät etuliitteen `GZXH`, kymmenen numeroa ja lopun `YQ`, yhteensä 16 merkkiä. Jos lopusta jää pois viimeinen kirjain `Q` (`GZXH`, kymmenen numeroa ja `Y`, yhteensä 15 merkkiä), Peek tunnistaa numeron silti Xinghe Freightiksi ja lisää `Q`-kirjaimen takaisin ennen seurantakyselyä. Esimerkkejä keksityillä numeroilla:

| Numero | Mikä se on |
| --- | --- |
| `GZXH4829105736YQ` | `GZXH`, kymmenen numeroa ja `YQ`: Xinghe Freightin vakiomuoto. Peek tunnistaa Xinghe Freightin heti. |
| `GZXH4829105736Y` | `GZXH`, kymmenen numeroa ja `Y`: viimeinen `Q` puuttuu, mutta Peek palauttaa sen automaattisesti. |
| `GZXH482910573YQ` | Vain yhdeksän numeroa: ei ole Xinghe Freightin muoto. Peek hylkää numeron heti, jotta voit tarkistaa puuttuvan numeron. |
| `YL4829105736YQ` | `YL`, kymmenen numeroa ja `YQ`: [GZYL:n](carrier:gzyl) rahtikirjamuoto. Sama seuranta-alusta, mutta toinen huolitsija. |
| `JXCAA4829105736YQ` | Päättyy myös kirjaimiin `YQ` monien kiinalaisten huolitsijoiden tavoin, mutta alkaa `JXC`: [Juxi Expressin](carrier:juxi-express) numero. |

Välilyönneillä, väliviivoilla tai pienillä kirjaimilla ei ole väliä, kun liität numeron Peekiin.

**Mistä numeron löytää.** Myyjä varaa kuljetuksen Xinghe Freightilta ja toimittaa seurantanumeron lähetysvahvistuksessa tai verkkokaupan tilaussivulla. Jos et löydä numeroa, kysy sitä suoraan myyjältä.

## Mitä Xinghe Freightin seurantatilat tarkoittavat

Xinghe Freightin seuranta toimii GZYL:n seurantajärjestelmässä, joten vaiheet näkyvät GZYL:n englanninkielisinä teksteinä kirjoitusvirheineen päivineen. Koska Peek-sovelluksen käyttöliittymää ei ole suomennettu, myös Peekin omat päätilat näkyvät englanniksi: «Label created» (lähetyskortti luotu), «Received by carrier» (kuljetusyhtiö vastaanottanut), «In transit» (matkalla), «At customs» (tullissa) ja «Delivered» (toimitettu). Vaiheet siinä järjestyksessä, jossa paketti tavallisesti kulkee:

| Seuranta näyttää | Mitä se tarkoittaa sinulle | Peek näyttää |
| --- | --- | --- |
| `Order information received` | Myyjä on lähettänyt tilauksen sähköiset tiedot. Kukaan ei ole vielä ottanut pakettia vastaan fyysisesti. | «Label created» |
| `Arrive at the cross border receiving point` | Huolitsija on vastaanottanut paketin: ensimmäinen fyysinen skannaus sen hallussa. | «Received by carrier» |
| `Goods leave the operation center` | Paketti on lähtenyt käsittelykeskuksesta Kiinassa. | «In transit» |
| `Arrive at the international airport` | Kansainvälisellä lentoasemalla odottamassa lentoa. | «In transit» |
| `Departing from the airport` | Lento on lähtenyt matkaan. | «In transit» |
| `Item arrives at destination airport` | Paketti on laskeutunut kohdemaahan. | «In transit» |
| `local carrier Agent Import customs clearance` | Paikallinen edustaja maassasi hoitaa tuontitullauksen. | «At customs» |
| `The import customs keep it for inspection` | Kohdemaan tulli on ottanut paketin tarkastukseen. | «At customs» |
| `Customs clearance completed` | Tullaus on valmis ja paketti jatkaa matkaa. | «In transit» |
| `Internationaliy Package received at processing center` | Kohdemaan käsittelykeskus on vastaanottanut tullatun paketin. Tämä ei tarkoita uutta noutoa lähettäjältä. | «In transit» |
| `Leave the email processing center` | Paketti on lähtenyt kyseisestä käsittelykeskuksesta. | «In transit» |
| `Arrival sorting, distribution center` | Saapunut lajittelu- tai jakelukeskukseen maassasi; kaupunki näkyy tekstin perässä. | «In transit» |
| `Leaving the sorting center` | Lähtee lajittelukeskuksesta; paikkakunta näkyy tekstin perässä. | «In transit» |
| `Package is on the way` | Matkalla eteenpäin kohdemaassa paikkakunnan kera. Tämä ei tarkoita, että kuljettaja olisi jo jakamassa pakettia kotiovelle. | «In transit» |
| `Delivered` | Toimitusmerkintä paikkakunnan kanssa. Kyseessä voi olla ennakkoon kirjoitettu aikataulu eikä aito skannaus: lue alta lisää. | «Delivered» ja huomautus kiinteästä aikataulusta, ellei Peek pidätä vaihetta (ks. alla) |

**Hongkongin kautta.** Lähetyksessä voi näkyä myös rivi `香港处理中心已收寄【Hong Kong】`: postin vastaanottomerkintä Hongkongin käsittelykeskuksessa, jonka seuranta välittää kiinaksi kaupunki hakasuluissa. Peek näyttää rivin sellaisenaan tilan «Received by carrier» alla.

**Kiinankielinen rivi.** Jos tilauksella ei ole vielä yhtään fyysistä skannausta, seuranta-alusta voi kirjoittaa automaattisesti `货物电子信息已经收到`, mikä tarkoittaa, että lähetyksen sähköiset tiedot on vastaanotettu. Myyjä on tehnyt tilauksen, mutta kukaan ei ole vielä skannannut pakettia. Peek näyttää rivin kiinaksi tilassa «Label created».

**Kellonajat.** Peek tulkitsee jokaisen seurannan kellonajan Pekingin ajaksi (`UTC+8`), myös Suomessa näkyvien vaiheiden osalta, ja muuntaa ajan laitteesi aikavyöhykkeeseen. Talviaikaan kello 20.00 Pekingissä vastaa kello 14.00 Suomen aikaa (`UTC+2`), ja kesäaikaan kello 15.00 (`UTC+3`).

**Etukäteen kirjoitetut vaiheet.** Tällä seuranta-alustalla huolitsijat voivat syöttää paketin koko aikajanan ennakkoon noudosta aina kohdemaan viimeiseen vaiheeseen asti, ja seuranta tuo kunkin rivin näkyviin vasta silloin, kun sen kellonaika koittaa. Tällainen merkintä on suunnitelma, ei oikea skannaus paketistasi. GZYL:n seurannassa suunnitelma päättyy `Delivered`-merkintään, jonka alusta lisää iltaeränä samana päivänä, jolle se on merkitty: päivättynä suunnitelman viimeistä vaihetta seuraavalle päivälle Pekingin toimistoaikoina, asuinmaastasi riippumatta. Peek pidättää tällaiset rivit (ks. alla), mutta ulkoisten seurantapalvelujen kopiot saattavat näyttää ne silti, joten pelkkä `Delivered` muualla ei todista paketin saapuneen perille.

## Kuka toimittaa Xinghe Freight -paketit Suomessa?

Peek ei tiedä, mikä paikallinen kuljetusyhtiö toimittaa Xinghe Freightin paketit Suomessa. Kohdemaan seurantavaiheet eivät mainitse yhdenkään jakeluyhtiön nimeä, eikä Xinghe Freightin verkkosivuilla kerrota mitään verkkokauppa-asiakkaiden paketeista. Ainoa paikallinen kuljetusyhtiö, jolle Peek pystyy siirtämään Xinghe-paketin seurannan, on [Swiss Post](carrier:swiss-post) Sveitsissä ja Liechtensteinissa, kun Xinghen tietue nimeää sen.

Xinghe Freightin sivuilla ei ole tietoa toimitusajoista, jakeluyritysten määrästä, noutopisteistä tai säilytysajoista — kaikki nämä määräytyvät lopullisen jakeluyhtiön sääntöjen mukaan. Kysy myyjältä, mikä yhtiö toimittaa tilauksesi Suomessa, ja pyydä kyseisen yhtiön oma seurantanumero.

**EU:n tullimaksut ja käsittelymaksu.** 1. heinäkuuta 2026 alkaen EU:n ulkopuolelta verkosta ostetuista tavaroista enintään 150 euron arvoisissa paketeissa peritään kiinteä 3 euron tulli tavaraluokkaa kohden. 1. marraskuuta 2026 alkaen lisäksi peritään EU:n 2 euron käsittelymaksu (Union handling fee) tavaraluokkaa kohden lähetyksen arvosta riippumatta. Yksi tavaraluokka tarkoittaa samassa paketissa olevia tavaroita, joilla on sama tullinimike, kuvaus ja tarvittaessa sama alkuperä kappalemäärästä riippumatta. Lisätietoja löydät oppaasta [tulliin jääneistä paketeista](guide:customs) (englanniksi).

## Jos Xinghe Freight -paketti ei saavu tai seuranta jumittaa

**Vain `Order information received` tai `货物电子信息已经收到`.** Myyjä on luonut kuljetustilauksen, mutta pakettia ei ole vielä skannattu kertaakaan. Jos uusia tapahtumia ei tule muutaman päivän kuluessa, kysy myyjältä, onko paketti todella lähetetty.

**«Waiting for Xinghe Freight».** Peekillä ei ole vielä vahvistettua skannausta numerollesi: joko GZYL:n seuranta ei tunne numeroa tai se sisältää vain etukäteen kirjoitetun suunnitelman (joka voi päättyä `Delivered`-merkintään), eivätkä ulkoiset seurantapalvelutkaan löydä mitään. Peek jatkaa tarkistamista. Tuntemattomalle numerolle GZYL:n seuranta palauttaa yhden rivin, jossa numerosi näkyy viitteenä ja kaikki muut kentät ovat tyhjiä; Peek tulkitsee tämän puuttuvaksi tiedoksi, näyttää «Waiting for Xinghe Freight» ja kahden päivän kuluttua «Check the tracking number». Ennakkoon kirjoitettu suunnitelma sen sijaan kertoo, että tilaus on Xinghellä, joten «Waiting for Xinghe Freight» pysyy näkyvissä ilman kehotusta tarkistaa numero. Vertaa numeroa silti merkki merkiltä myyjän viestiin.

**Pysähtynyt tulliin.** Jos sinulta pyydetään tuontimaksuja, lue opas [tullissa olevista paketeista](guide:customs) (englanniksi). Jos paketti seisoo tullissa päiväkausia, ilmoita myyjälle.

**Seuranta tai toinen sivusto näyttää enemmän vaiheita kuin Peek.** Ylimääräiset rivit — mukaan lukien `Delivered` — voivat olla ennakkoon kirjoitettu aikataulu, jonka Peek jättää laskematta oikeiksi skannauksiksi. Seuraa verkkokaupan antamaa toimitusaika-arviota ja ota yhteyttä myyjään, kun se on umpeutunut. Lisää aiheesta oppaassa [paketin seuranta Kiinasta](guide:tracking-from-china) (englanniksi).

**`Delivered`, mutta pakettia ei ole tullut.** Merkintä saattaa kuulua etukäteen syötettyyn aikatauluun. Ellei Swiss Post toimita pakettia, Peek näyttää «Delivered»-tilan alla huomautuksen: «Xinghe Freight updates on a fixed schedule, with no signature. If nothing came, ask the seller.» Kerro myyjälle, ettei paketti ole saapunut, ja pyydä paikallisen jakeluyhtiön nimi ja seurantanumero. Lisätietoja oppaassa [toimitettu mutta ei vastaanotettu](guide:delivered-not-received) (englanniksi).

**Kadonnut tai palautettu.** Myyjä teki kuljetussopimuksen Xinghe Freightin kanssa, joten pyydä rahojen palautusta tai uutta lähetystä myyjältä tai kauppapaikalta.

## Xinghe Freightin yhteystiedot ja asiakaspalvelu

Xinghe Freightin kiinankielisellä verkkosivustolla (`xinghe-express.cn`) on julkaistu seuraavat tiedot:

- **Puhelinpalvelu (24/7):** `19924483103` (`+86 199 2448 3103` ulkomailta), ilmoitettu palvelevan ympäri vuorokauden joka päivä.
- **Sähköposti:** `3975605292@qq.com` yrityskyselyihin ja hintatarjouksiin.
- **Osoite:** Guangzhou, Guangdongin maakunta, ilman katuosoitetta (vierailijoita pyydetään ottamaan yhteyttä etukäteen täyttä osoitetta varten).

Sivusto on vain kiinaksi eikä tarjoa tukikanavaa ulkomaisille vastaanottajille: yhteydenottolomake avaa vain sähköpostiviestin yllä olevaan osoitteeseen. Xinghen asiakas on myyjä, joten ota aina ensin yhteyttä myyjään ja vastaamattomuustilanteessa kauppapaikkaan.

## Xinghe Freight -paketin seuranta Peekissä

[Peek](/) tunnistaa `GZXH…YQ`-numeron (ja myös lopun `Q`-kirjaimesta lyhentyneen `GZXH…Y`-numeron) heti Xinghe Freightiksi tekstillä «Detected carrier». Jos myyjä antoi toisenmuotoisen koodin, liitä se silti: Peek seuraa yli 4 500 kuljetusyhtiötä. Voit myös valita Xinghe Freightin käsin painamalla «Change» ja hakemalla nimellä Xinghe Freight (tai `星和货运`), tai käyttämällä «Change carrier» -toimintoa jo lisätylle paketille.

Peek kysyy tietoja suoraan GZYL:n seurannasta, aivan kuten Xinghen oman sivun hakukenttä. Se näyttää nykyisen tilan, jokaisen vaiheen paikkakuntineen sekä kohdemaan, kun se sisältyy tietueeseen. Kellonajat luetaan Pekingin ajassa ja näytetään oman laitteesi aikavyöhykkeessä («Times are in your time zone:» historian lopussa). Peek jättää pois tietueessa mahdollisesti olevat nimet, osoitteen, postinumerot, painon, maksutiedot, allekirjoituksen ja toimitustodistuksen, eikä se näytä arvioitua toimituspäivää, koska seuranta ei anna sellaista.

Jos seuranta ei tunne numeroasi, Peek kysyy tietoja seurantapalveluilta ja näyttää niiden kopion vaiheista. Niin kauan kuin kummallakaan ei ole vaiheita, ruudulla näkyy «Waiting for Xinghe Freight» ja kahden päivän kuluttua «Check the tracking number».

Peek pidättää jokaisen vaiheen, joka on kirjoitettu yli 12 tuntia ennen näyttämäänsä kellonaikaa, jokaisen vaiheen, joka on kirjoitettu samalla sekunnilla kuin yli puoli tuntia etuajassa kirjoitettu rivi (tilauksen rekisteröintiä lukuun ottamatta), sekä jokaisen Pekingin ajassa tarkistushetkeä myöhemmäksi päivätyn rivin. Jos tietueessa on etukäteen kirjoitettuja vaiheita, Peek pidättää myös `Delivered`-merkinnän, vaikka se olisi kirjoitettu samana päivänä. Peek määrittää tilan säilyttämiensä vaiheiden mukaan eikä tietueen yhteenvetotilan perusteella, ja suodattaa pois myös seurantapalvelujen kopiot näistä ennakkoon kirjoitetuista vaiheista (`Delivered` mukaan lukien). Jos seuranta on tavoittamattomissa tai ei tunne numeroa, Peek ei kuitenkaan pysty erottamaan seurantapalvelun ennakkoriviä aidosta skannauksesta. Kun Peek pidättää ennusteita, historian alussa näkyy toimitukseen tai palautukseen saakka teksti «Other sites may show more steps, but they’re only predictions.», ja «Show them» näyttää rivit harmaana päivineen («Predicted for Mon 12 Oct»). Tällainen rivi ei muuta tilaa eikä lähetä ilmoitusta.

Swiss Postia lukuun ottamatta Peek ei siirrä seurantaa automaattisesti paikalliselle jakeluyhtiölle: jos saat paikallisen numeron myyjältä, lisää se Peekiin omana pakettinaan. Kun pidät pakettia auki Peekissä tai toimituspäivänä ilmoitusten ollessa päällä, Peek tarkistaa tilanteen enintään 10 minuutin välein; muulloin ja paketin hiljennyttyä kerran tunnissa. Ilmoitusasetuksiksi voit valita «Every scan», «Important steps» (kaksi tullivaihetta ja `Delivered`, ei lentoja tai lajittelua) tai «Delivery only». Peek ei voi ottaa puolestasi yhteyttä Xinghe Freightiin, GZYL:ään tai myyjään.

## Usein kysytyt kysymykset Xinghe Freight -seurannasta

### Mikä on Xinghe Freight (星和货运)?

广州星和货运代理有限公司 (Guangzhou Xinghe Freight Forwarding) on vuonna 2014 perustettu guangzhoulainen huolintayritys, joka tarjoaa meri-, lento- ja maakuljetuksia, laivanselvitystä sekä tullausta. Sen seurantanumeroissa on 16 merkkiä: `GZXH`, kymmenen numeroa ja `YQ`.

### Miksi Xinghe Freight -pakettini näyttää GZYL:n seurantaa?

Xinghe Freightin verkkosivuston hakukenttä ohjaa seurantakyselyt suoraan GZYL:n seurantaan, minkä vuoksi vaiheiden sanamuodot ovat GZYL:n tekstejä. Peek pitää paketin Xinghe Freightin alla ja lukee kyseisen seurannan puolestasi.

### Kuka toimittaa Xinghe Freight -paketit Suomessa?

Xinghe Freightin sivusto ja seuranta eivät mainitse Suomessa toimivaa paikallista jakeluyhtiötä (Peek pystyy siirtämään seurannan automaattisesti vain Swiss Postille Sveitsissä ja Liechtensteinissa). Kysy myyjältä, mikä yhtiö jakaa pakettisi, ja pyydä sen oma seurantanumero.

### Miksi Peek näyttää vähemmän vaiheita tai ei lainkaan seurantaa GZXH-numerolleni?

Joko seurannassa ei ole vielä merkintää numerostasi tai tietue sisältää etukäteen kirjoitettuja vaiheita — myös aikataulutetun `Delivered`-merkinnän — jotka Peek siirtää «Show them» -painikkeen taakse oikeiden skannausten sijaan. Tarkista numero myyjän sähköpostista ja kysy myyjältä, jos mitään ei päivity muutamassa päivässä.

### Miten otan yhteyttä Xinghe Freightiin?

Ota ensin yhteyttä myyjään tai kauppapaikkaan, sillä myyjä varasi kuljetuksen. Xinghen kiinankielinen sivusto (`xinghe-express.cn`) listaa ympärivuorokautisen puhelinnumeron `19924483103` (`+86 199 2448 3103` ulkomailta) ja yrityssähköpostin `3975605292@qq.com`, mutta ei erillistä tukea ulkomaisille vastaanottajille.

:::sources
- [Xinghe Freight: 广州星和货运代理有限公司 (etusivu, kiinaksi)](http://xinghe-express.cn/) – yrityksen nimi, huolintapalvelut, GZYL-seurannan avaava hakukenttä, puhelinnumero ja sähköposti
- [Xinghe Freight: 关于我们 (tietoa meistä, kiinaksi)](http://xinghe-express.cn/about.html) – perustettu 2014, meri-, lento- ja maakuljetukset, laivanselvitys ja tullaus
- [Xinghe Freight: 产品服务 (palvelut, kiinaksi)](http://xinghe-express.cn/services.html) – tarjotut palvelut, ei tietoja verkkokauppapaketeista
- [Xinghe Freight: 联系我们 (yhteystiedot, kiinaksi)](http://xinghe-express.cn/contact.html) – 7×24-linja, yrityssähköposti, osoitteena vain Guangzhou, sähköpostin avaava lomake
- [Euroopan komissio: EU Customs Reform (englanniksi)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 3 € kiinteä tulli tavaraluokkaa kohden enintään 150 € paketeille 1.7.2026 alkaen, 2 € käsittelymaksu 1.11.2026 alkaen
- [Euroopan komissio: Guidance on the EUR 3 customs duty and the Union handling fee (PDF, englanniksi)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € käsittelymaksu arvosta riippumatta ja tavaraluokan määritelmä
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – nimet, muodot `GZXH…YQ` ja lyhennetty `GZXH…Y`, virheellisen pituuden hylkäys, GZYL-seuranta, vaiheiden sanamuodot, Hongkongin vastaanotto, kiinankielinen rivi, Pekingin aika, ennakkovaiheet, varapalvelut, Swiss Post -siirto sekä mitä Peek näyttää, pidättää ja jättää pois
:::
