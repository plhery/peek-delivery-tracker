---
title: PostNord-seuranta: lähetystunnus, tilat ja tulli
description: Seuraa PostNordin pakettia Suomessa: miltä lähetystunnus näyttää, mitä seurannan tilat tarkoittavat, miten noudat paketin automaatista ja mitä tullista maksat.
slug: postnord-seuranta
published: 2026-10-23
updated: 2026-10-27
---

PostNord on posti- ja logistiikkakonserni, joka syntyi Tanskan ja Ruotsin postien yhdistyessä. Se kuljettaa lähetyksiä Pohjoismaissa sekä Pohjoismaihin ja niistä muualle. Suomessa PostNord tuo verkkokauppojen paketteja kotiin, palvelupisteisiin ja pakettiautomaatteihin. PostNord ei ole sama yhtiö kuin [Posti](carrier:posti), jolla on oma seurantansa. Pakettia seuraat [PostNordin seurannassa](https://tracking.postnord.com/fi/) tai PostNord-sovelluksessa lähetystunnuksella, tai voit liittää tunnuksen yllä olevaan kenttään. Tunnuksen saat lähettäjältä, yleensä verkkokaupalta.

## PostNordin lähetystunnus

PostNordin seuranta puhuu lähetystunnuksesta. Siinä on 8–35 merkkiä, ja se voi sisältää vain kirjaimia ja numeroita. PostNordin tunnuksia ovat esimerkiksi nämä:

| Tunnus (keksitty) | Mikä se on |
| --- | --- |
| `00064000000000000002` | 20 numeroa, alussa `00`: paketin SSCC-viivakoodi. `64` kolmen ensimmäisen numeron jälkeen viittaa Suomeen. Samanmuotoisia koodeja käyttävät muutkin, myös Posti. |
| `12345678901SE` | 11 numeroa ja `SE`: PostNordin paketti Ruotsista. |
| `RR000000005SE` | Kaksi kirjainta, yhdeksän numeroa ja `SE`: Ruotsissa jätetty seurattava postilähetys. |
| `RR000000005DK` | Sama muoto, lopussa `DK`: lähetys on jätetty Tanskassa. |

**Ei tuloksia?** Jos seuranta näyttää `Toimituksia ei löytynyt`, lähetys ei PostNordin mukaan ole seurattavissa tai tunnus on väärä. Tarkista tunnus verkkokaupasta.

**Ei PostNordin tunnus?** `JJFI`-alkuinen tunnus on Postin, samoin `FI`-loppuinen postitunnus (kaksi kirjainta, yhdeksän numeroa ja `FI`). `MA`-alkuinen 21 merkin tunnus voi olla [Matkahuollon](carrier:matkahuolto).

## Mitä PostNordin seurannan tilat tarkoittavat

Seuranta näyttää ylimpänä paketin tilan lyhyesti, ja kohdassa `Toimitusreitti` näet tapahtumat aikoineen ja paikkoineen. Jos PostNordilla on arvio, se näkyy kohdassa `Arvioitu toimitusaika`, ja saman päivän toimituksesta kertoo `toimitus tänään`. Suomenkielisen seurannan tilat:

| PostNord kertoo | Mitä se tarkoittaa sinulle |
| --- | --- |
| `Odotamme pakettiasi` | Lähettäjä on ilmoittanut paketista, mutta PostNord ei ole vielä saanut sitä. |
| `Matkalla` | Paketti on PostNordilla ja matkalla. |
| `Pysäytetty` | Paketti on pysähtynyt, esimerkiksi tullaukseen, maksamattoman arvonlisäveron vuoksi tai odottamaan sovittua toimituspäivää. Tapahtumista näet syyn. |
| `Saattaa viivästyä` | Paketti voi myöhästyä, esimerkiksi koska osoite on puutteellinen. Tapahtumista näet syyn. |
| `Viivästynyt` | Paketti tulee arvioitua myöhemmin. |
| `Ei voitu toimittaa` | Toimitus ei onnistunut. Tapahtumista näet jatkon, usein sen, että saat ilmoituksen paikasta, josta voit noutaa paketin. |
| `Noutopisteessä` | Paketti odottaa sinua palvelupisteessä tai pakettiautomaatissa. |
| `Toimitettu` | Paketti on toimitettu sinulle tai noudettu. |
| `Toimituksesta kieltäydytty` | Vastaanottaja kieltäytyi ottamasta pakettia vastaan. |
| `Palautettu` | Paketti palautetaan lähettäjälle. |

**Vain postinumero ja paikkakunta.** Kohdassa `Vastaanottajan osoite` PostNord näyttää turvallisuussyistä vain postinumeron ja paikkakunnan, ei koko nimeä ja osoitetta.

**Useampi paketti?** `Toimitus useilla paketeilla` tarkoittaa, että lähetys on jaettu useaksi paketiksi, jotka toimitetaan erikseen.

**Tullia maksettavana?** Jos paketista on maksettava tullia tai arvonlisäveroa, seurannassa näkyy painike `Tulli ja ALV`.

**Seuranta ei etene?** Lue ohje [seurannasta, joka ei päivity](guide:tracking-not-updating) (englanniksi).

## Pakettiautomaatti, palvelupiste ja kotiinkuljetus

Suomessa PostNordin yleisin toimitustapa on pakettiautomaatti. PostNordin mukaan automaatteihin menee lähes puolet sen verkkokauppatoimituksista Suomessa, selvästi enemmän kuin kotiin tai palvelupisteisiin.

**Pakettiautomaatti.** Jos seurannassa lukee `Nouda pakettisi pakettiautomaatista`, tarvitset noutoon PostNord-sovelluksen.

**Palvelupiste.** Kun paketti odottaa palvelupisteessä, seuranta kertoo, mihin asti piste on tänään auki (`Aukiolo päättyy`), ja `Näytä kartalla` näyttää sen sijainnin. Jos aukioloaikoja ei näy, PostNord neuvoo kysymään niitä suoraan palvelupisteestä.

**Viimeinen noutopäivä.** Kohdassa `Viimeinen noutopäivä on` näet päivän, jonka jälkeen paketti palautetaan lähettäjälle.

**Toimituksen muuttaminen.** Jos seurannassa näkyy `Mahdolliset toimitusvaihtoehdot`, painikkeesta `Valitse toimitusvaihtoehto` näet, miten muuten voit saada paketin.

**PostNord-sovellus.** Kun rekisteröit sovellukseen puhelinnumerosi tai sähköpostiosoitteesi, sinulle tulevat lähetykset näkyvät siellä automaattisesti. Jakelussa olevan paketin kulkua voit seurata sovelluksessa livenä, kun kirjaudut sisään.

## PostNordin paketti myöhässä, kadonnut tai rikki

**Myyjä vastaa.** Paketin lähettäjä, esimerkiksi verkkokauppa, vastaa siitä, että tavara tulee perille ehjänä ja sopimuksen mukaan. Ota siksi ongelmissa yhteys ensin myyjään.

**Myöhässä.** Kun toimitus viivästyy sinusta riippumattomasta syystä, kyse on myyjän sopimusrikkomuksesta. Ilmoita viivästyksestä myyjälle ja anna kohtuullinen lisäaika. Jos et ole vielä maksanut, voit lykätä maksua. Jos tavara ei tule lisäajassakaan, voit purkaa kaupan, ja viivästyksen aiheuttamista taloudellisista menetyksistä voit vaatia korvausta.

**Rikkoutunut tavara.** Ota kuvat sekä paketista että rikkoutuneesta tavarasta ja ilmoita virheestä myyjälle.

**Maksoit luottokortilla?** Jos tavaraa ei tule, voit vaatia rahojasi takaisin myös luottokorttiyhtiöltä.

**Jos ette pääse sopuun.** Kysy ohjeita kuluttajaneuvonnasta. Jos myyjä on EU- tai ETA-maassa, voit kääntyä Euroopan kuluttajakeskuksen puoleen. EU:n ulkopuolisten yritysten kohdalla viranomaisten keinot ovat rajalliset. Postilain käyttäjän oikeuksista ei ole apua: paketeissa ne koskevat vain tiettyjä Posti Oy:n palveluita, eivät PostNordin paketteja.

**Palautatko tavaran?** Pyydä palautuksesta kuitti, seurantakoodi tai muu todiste siitä, että paketti lähti matkaan. Kuljetusyhtiön vastuu kadonneesta tai rikkoutuneesta paketista on laissa rajattu 20 euroon kilolta. Kadonneesta kotimaan paketista voit tehdä huomautuksen, jos sitä ei ole luovutettu 14 päivän kuluessa sovitun luovutusajan päättymisestä. Jos aikaa ei sovittu, paketti katsotaan kadonneeksi, kun kuljetettavaksi ottamisesta on kulunut 28 päivää.

## Tulli EU:n ulkopuolelta tilatuissa paketeissa

**3 euroa tuotetyypiltä.** Euroopan komission mukaan EU:n ulkopuolelta tilatuista enintään 150 euron lähetyksistä peritään heinäkuun 2026 alusta lähtien väliaikainen 3 euron tulli jokaisesta tuotetyypistä. Aiemmin näin edulliset lähetykset olivat tullittomia. PostNordin esimerkissä paketissa on puuvillasukat ja kaksi villapaitaa: tuotetyyppejä on kaksi, joten tullia on 6 euroa. Yli 150 euron lähetyksissä tullit määräytyvät kuten ennenkin.

**Maksu jo kassalla.** PostNordin mukaan verkkokaupan pitää periä sekä tulli että arvonlisävero jo tilauksen yhteydessä. Jos maksoit kassalla arvonlisäveron mutta verkkokauppa ei perinyt tullia, paketti palautetaan lähettäjälle. Jos et maksanut kumpaakaan, saat PostNordilta laskun molemmista ja PostNordin hallinnollisesta maksusta, ja paketti toimitetaan vasta, kun lasku on maksettu.

**Lahjat ja palautukset.** PostNordin mukaan alle 45 euron henkilökohtaisista lahjoista ei peritä tullia eikä arvonlisäveroa, kun lähetys on ilmoitettu oikein lahjaksi. Jos palautat tuotteen, tullia ei yleensä makseta takaisin.

**1.11.2026 alkaen 2 euron käsittelymaksu.** Euroopan komission mukaan EU:n tulliviranomaiset alkavat 1.11.2026 periä EU:n ulkopuolelta tilatuista verkko-ostoksista 2 euron käsittelymaksun tuotetyyppiä kohden. Maksu koskee myös yli 150 euron lähetyksiä, eikä sitä palauteta.

Huomaa, että suomalainenkin verkkokauppa voi toimittaa tavaran EU:n ulkopuolisesta varastosta. Lue lisää [tulliin jääneistä paketeista](guide:customs) (englanniksi).

## PostNordin asiakaspalvelu

PostNordin sivuilla 23.10.2026:

- **Asiakaspalvelu Suomessa:** postnord.fi tai PostNord-sovellus. Seurantasivulta pääset sinne linkistä `Ota yhteyttä`.
- **PostNord Oy:** Osumakuja 1–3, 01530 Vantaa.

> Suhtaudu varauksella PostNordin nimissä tuleviin tekstiviesteihin ja sähköposteihin. PostNord lähettää harvoin viestejä, joissa on maksulinkki, paitsi EU:n ulkopuolelta tulevista tavaroista. Jos et odota pakettia ulkomailta ja saat epäilyttävän viestin, se on todennäköisesti huijaus. Huijausviesti voi tulla samaan viestiketjuun kuin aidot ilmoitukset: älä avaa linkkiä, vaan tarkista paketti PostNord-sovelluksesta.

## PostNordin paketin seuranta Peekillä

[Peek](/) tunnistaa PostNordin tunnuksen pelkästä muodosta vain, jos siinä on kaksi kirjainta, yhdeksän numeroa ja `SE` tai `DK`, tai 11 numeroa ja `SE`. PostNordin suomalaisia tunnuksia se ei tunnista muodon perusteella. 20-numeroisia `00`-alkuisia viivakoodeja PostNord jakaa muiden kanssa: jos kolmen ensimmäisen numeron jälkeen tulee `64`, `70`, `73` tai `57` ja tarkistusnumero täsmää, Peek kysyy PostNordilta ja muilta yhtiöiltä, joiden muotoon koodi sopii, Suomen `64`-koodeista myös Postilta ja `64`- ja `73`-koodeista Matkahuollolta. Jos vain PostNord tuntee paketin, näet tekstin "PostNord has this parcel." Jos useampi yhtiö tuntee koodin, Peek valitsee sen, jota koodin muoto suosii (`64`-koodeissa Postin), tai pyytää sinua valitsemaan. Jos kukaan ei vielä tunne pakettia, näet tekstin "Not found at … yet. Peek will keep checking after you add it."

Jos tunnuksessa on muuten 10–20 numeroa, liitä se silti: Peek seuraa yli 3 500 kuljetusyhtiötä. Valitse PostNord painikkeella "Change", jolla vaihdat myös väärin valitun yhtiön. Linkki tracking.postnord.com- tai postnord.fi-sivulta vie tunnuksen suoraan PostNordille.

Peek lukee PostNordin seurannan englanniksi, joten näet PostNordin englanninkieliset tekstit. `The shipment item has been delivered to a service point.` tarkoittaa, että paketti odottaa sinua palvelupisteessä, ei sitä, että olisit saanut sen, ja `The shipment item is being customs cleared by us.` sitä, että paketti on tullauksessa. Peek näyttää jokaisen skannauksen paikan, lähettäjän, palvelun, painon, mitat ja kohdemaan sekä noutoa odottavan paketin palvelupisteen nimen ja osoitteen. Tekstiviesti- ja sähköposti-ilmoitukset jäävät pois, ja `The shipment item has been stored.` merkitään ongelmaksi. Nimeäsi Peek ei tallenna, eikä se näytä arvioitua toimitusaikaa, vaikka PostNordin seuranta näyttäisi. Päiväsaikaan Peek tarkistaa paketin 10 minuutin välein, jakelussa olevan 2 minuutin välein, ja öisin tunnin tai kahden välein. Skannaukset näkyvät kartalla, ja Peek voi ilmoittaa muutoksista. PostNordiin tai verkkokauppaan Peek ei voi ottaa yhteyttä puolestasi.

## Kysymyksiä PostNordin seurannasta

### Miksi PostNordin seuranta ei löydä pakettiani?

Jos näet tekstin `Toimituksia ei löytynyt`, lähetys ei ole seurattavissa tai tunnus on väärä. Tarkista tunnus verkkokaupasta. Jos tila on `Odotamme pakettiasi`, PostNord ei ole vielä saanut pakettia.

### Kauanko PostNord säilyttää pakettia?

Viimeinen noutopäivä näkyy seurannassa kohdassa `Viimeinen noutopäivä on`. Sen jälkeen paketti palautetaan lähettäjälle.

### Kauanko PostNordin paketti on matkalla?

PostNordin mukaan kotimaan toimitukset kestävät tyypillisesti 1–2 arkipäivää. Muista maista Suomeen paketti tulee yleensä 2–6 arkipäivässä, syrjäseuduille pidempään.

### Onko PostNord sama kuin Posti?

Ei. PostNord on ruotsalais-tanskalainen konserni, ja Posti on eri yhtiö, jolla on oma seuranta ja asiakaspalvelu.

### Pitääkö PostNordin paketista maksaa tullia?

Vain jos paketti tulee EU:n ulkopuolelta. Enintään 150 euron lähetyksestä tulli on 3 euroa tuotetyyppiä kohden, ja PostNordin mukaan verkkokaupan pitää periä se jo kassalla. Jos tullia ja arvonlisäveroa ei ole maksettu, PostNord laskuttaa ne hallinnollisen maksunsa kera ennen toimitusta. 1.11.2026 alkaen tulee lisäksi 2 euron käsittelymaksu tuotetyyppiä kohden.

:::sources
- [PostNord: Seuraa lähetystäsi](https://tracking.postnord.com/fi/) – tilat, Lähetystunnus, 8–35 merkkiä, Toimituksia ei löytynyt, Toimitusreitti, Arvioitu toimitusaika, Viimeinen noutopäivä on, Vastaanottajan osoite, Toimitus useilla paketeilla, Tulli ja ALV, pakettiautomaatti ja sovellus, toimitusvaihtoehdot, live-seuranta, palvelupisteen aukiolo, Ota yhteyttä
- [PostNord: About us](https://group.postnord.com/aboutus/) – posti- ja logistiikkapalvelut Pohjoismaissa sekä niihin ja niistä
- [PostNord: The brand](https://group.postnord.com/aboutus/strategy/the-brand/) – Post Danmarkin ja Posten AB:n yhdistyminen
- [PostNord: Annual and Sustainability Report 2025](https://group.postnord.com/siteassets/documents/annual-and-sustainability-reports/postnord_2025_annualreport_en-1.pdf) – PostNord Finland, verkkokaupan paketit, toimitus kotiin, palvelupisteeseen ja automaattiin
- [PostNord: Shipping to Finland](https://www.postnord.com/services/logistics/shipping-to-the-nordics/shipping-to-finland/) – kotiinkuljetus, palvelupiste ja automaatti, toimitusajat, automaattien osuus
- [PostNord: New EU-regulations July, 1st 2026](https://www.postnord.com/incidents/new-eu-regulations-july-1st-2026/) – tulli tuotetyypeittäin, esimerkki, palautus lähettäjälle, lasku ja hallinnollinen maksu, lahjat, tulli palautuksissa
- [PostNord: Contact PostNord](https://group.postnord.com/contact/) – asiakaspalvelu Suomessa postnord.fi:ssä ja sovelluksessa, PostNord Oy:n osoite
- [PostNord: Phishing tips](https://group.postnord.com/cybersecurity/phishing-tips/) – huijausviestit, maksulinkit, sovellus
- [European Commission: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – väliaikainen 3 euron tulli 1.7.2026 alkaen, 2 euron käsittelymaksu 1.11.2026 alkaen
- [European Commission: Guidance on the 3 EUR customs duty and Union handling fee](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – käsittelymaksu 2 euroa tuotetyyppiä kohden, myös yli 150 euron lähetyksistä, ei palauteta
- [KKV: Posti- ja pakettijakelu](https://www.kkv.fi/kuluttaja-asiat/puhelin-ja-laajakaistapalvelut/posti-ja-pakettijakelu/) – myyjän vastuu, kuvat vaurioista, kuljetusyhtiön vastuu 20 €/kg, kadonnut paketti 14 ja 28 päivää, Posti nimettynä operaattorina, kotimainen verkkokauppa ja EU:n ulkopuolinen varasto, Euroopan kuluttajakeskus
- [KKV: Tavaran toimituksen viivästys](https://www.kkv.fi/kuluttaja-asiat/tavarat/tavaran-toimituksen-viivastys/) – viivästys sopimusrikkomuksena, lisäaika, maksun lykkääminen, purku, vahingonkorvaus, luottokortti, kuluttajaneuvonta
- [KKV: Usein kysyttyä verkko-ostamisesta](https://www.kkv.fi/kuluttaja-asiat/verkkokauppa/usein-kysyttya-verkko-ostamisesta/) – luottokortti, rikkoutunut paketti, todiste palautuksesta
- [KKV: Tietojenkalastelu](https://www.kkv.fi/kuluttaja-asiat/huijaukset/tietojenkalastelu/) – väärennetyt seuranta- ja saapumisviestit
- [Traficom: Postipalveluiden asiakkaan oikeudet](https://www.traficom.fi/fi/postitoiminta-ja-jakelu/postipalveluiden-asiakkaan-oikeudet) – postilain oikeudet paketeissa vain Posti Oy:n tietyissä palveluissa
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – mitä Peek lukee PostNordilta, tilojen englanninkieliset tapahtumat
:::
