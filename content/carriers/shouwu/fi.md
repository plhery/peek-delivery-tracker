---
title: Shouwu-seuranta: SWECN…YQ-numerot, tilat ja toimitus
description: Seuraa Shouwu Internationalin (守务国际) pakettia Kiinasta: SWECN…YQ-numeromuoto, tilojen merkitykset ja toimittajat Isossa-Britanniassa ja Yhdysvalloissa.
slug: shouwu-tracking
published: 2026-11-28
updated: 2026-11-28
---

Shouwu International (守务国际, myös SWE) on shanghailainen logistiikkayritys, joka kuljettaa kiinalaisten verkkokauppiaiden paketteja Eurooppaan ja Yhdysvaltoihin, missä paikallinen posti tai kuriiri toimittaa ne perille. Jos kauppa lähetti tilauksesi Shouwulla, sen seurantanumero alkaa merkeillä SWECN ja päättyy kirjaimiin YQ: liitä se yllä olevaan kenttään tai Shouwun omaan seurantaan osoitteessa [admin.shshouwu.com](https://admin.shshouwu.com/track.html). Kauppa antaa numeron tilauksesi toimitustiedoissa.

## Shouwu-seurantanumero: miltä se näyttää

Shouwun rahtikirjanumerot muodostuvat merkeistä SWECN, kahdestatoista numerosta ja päätteestä YQ, yhteensä 19 merkistä. Keksityillä numeroilla:

| Numero | Mikä se on |
| --- | --- |
| `SWECN482910573649YQ` | SWECN, kaksitoista numeroa ja YQ: Shouwun rahtikirjanumero. |
| `SWECN48291057364YQ` | Vain yksitoista numeroa: ei Shouwun muotoa. Tarkista, puuttuuko numero. |
| `JSEAA4829105736YQ` | Päättyy myös kirjaimiin YQ, mutta alkaa JSE: [JS Expressin](carrier:js-express) numero. |

Myös muut kiinalaiset huolitsijat päättävät rahtikirjanumeronsa kirjaimiin YQ, omilla alkukirjaimillaan ja Peekin luettelossa enimmäkseen kymmenellä numerolla. SWECN ja kaksitoista numeroa erottavat Shouwun. Välilyönneillä, väliviivoilla ja kirjainkoolla ei ole Peekissä merkitystä.

**Mistä sen löytää.** Jos numeroa ei ole kaupan lähetyssähköpostissa tai tilaussivullasi, katso [kuinka löytää seurantanumero (englanniksi)](guide:find-tracking-number).

**Voit saada sen sijaan kuriirin numeron.** Shouwu antaa myyjän valita, kumman numeron se välittää eteenpäin: Shouwun oman numeron, joka seuraa pakettia alusta loppuun, vai sen kuriirin numeron, joka toimittaa paketin maassasi. Shouwun paketti voi siis saapua sinulle Isossa-Britanniassa [Royal Mailin](carrier:royal-mail), Yodelin tai [Evrin](carrier:evri) numerolla, tai Yhdysvalloissa [USPS:n](carrier:usps) numerolla. Liitä se silti kenttään: Peek seuraa yli 4 500 kuljetusyhtiötä.

**Seurantalinkki.** Shouwun "Copy Link" -painike antaa linkin sen seurantaan, jossa on `?q=` ja numero. Linkki avaa kyseisen paketin, ja voit liittää koko linkin Peekiin. Shouwun seuranta ottaa kerralla vastaan enintään 40 numeroa, yhden riviä kohden.

## Shouwun seurantatilat selitettynä

Shouwun seuranta on englanniksi kiinankielisen otsikon alla. Se näyttää jokaiselle paketille tilan ja kohdemaan sekä kunkin skannauksen ajan ja tekstin, mutta ei paikkaa.

| Shouwun tila | Mitä se tarkoittaa sinulle | Peek näyttää |
| --- | --- | --- |
| `Not Found` | Shouwulla ei vielä ole mitään tällä numerolla: "We seem can't identify the number." | "Waiting for Shouwu International" |
| `Preparing` | Shouwu selittää tämän paketiksi, joka on luovutettu sen varastoon Kiinassa tai jota punnitaan ja suljetaan siellä. Se voi näkyä jo ennen ensimmäistä skannausta. | "Label created", sitten "Received by carrier" kumppanin noudon tai varastoskannauksen jälkeen |
| `In Transit` | Kaikki kuljetusyhtiölle luovuttamisesta tulliin ja maasi sisäiseen kuljetukseen. | "In transit" tai uusimman skannauksen vaihe, kuten "At customs"; "Label created" niin kauan kuin on vain tilaustiedot ja `Carrier received the shipment` |
| `Undelivered` | Toimitusyritys epäonnistui. | "Delivery attempted", tai "Ready for pickup" tai "Returned to sender", jos uusin skannaus niin kertoo |
| `Delivered` | Toimitettu vastaanottajalle. | "Delivered" |

Shouwu kutsuu tilojaan järjestelmän automaattisesti arvioimiksi ("auto-judged by the system") ja vain tiedoksi tarkoitetuiksi ("only for information reference use"). Skannaukset kertovat enemmän:

| Shouwu sanoo | Mitä se tarkoittaa sinulle | Peek näyttää |
| --- | --- | --- |
| `Shipment information received`, `Order information received. We're expecting your parcel to arrive with us.` | Myyjä on varannut lähetyksen. Shouwulla ei vielä ole pakettia. | "Label created" |
| `Carrier received the shipment` | Shouwu kirjaa tämän kaksi tuntia tilaustietojen jälkeen, päiviä ennen ensimmäistä varastoskannausta: kenelläkään ei vielä ole pakettia. | "Label created" |
| `Picked up by Shipping Partner` | Shouwun kuljetuskumppani on noutanut paketin myyjältä: ensimmäinen skannaus tilaustietojen jälkeen. | "Received by carrier" |
| `Arrival processing center` | Shouwun varasto Kiinassa on ottanut paketin vastaan. | "Received by carrier" |
| `Package processing completed` | Varasto on saanut käsittelyn valmiiksi ennen lentoa. | "Received by carrier" |
| `Shipment started the customs clearance process at origin` | Vientitullaus Kiinassa on alkanut, tavanomainen vaihe ennen lentoa. Tulli ei pidättele pakettia. | "In transit" |
| `The package is shipped from the transfer warehouse to the airport Export customs clearance in progress` | Lähetetty siirtovarastosta lentokentälle, missä Kiinan vientitullaus on käynnissä: ei maasi tulli. | "In transit" |
| `Export customs declaration completed`, `Export customs clearance completed`, `Export Released` | Kiina on selvittänyt sen vientiin; mitään ei ole pysäytetty. | "In transit" |
| `The parcel has arrived at the airport and is currently being loaded onto a flight` | Lähtölentokentällä vientitullauksen jälkeen, lastattavana lennolle. | "In transit" |
| `Arrived at Shanghai Pudong Airport`, `Departed from export airport` | Shanghain lentokentällä, minkä jälkeen lennetty ulos Kiinasta. | "In transit" |
| `The ship has already set sail` | Merilinjalla: laiva on lähtenyt Kiinasta vientitullauksen jälkeen ennen saapumistaan satamaan ulkomailla. | "In transit" |
| `Arrived at airport of Destination`, `Shipment arrived in the destination country` | Laskeutunut maahasi. | "In transit" |
| `NOA received` | Edustaja maassasi on saanut lentoyhtiön saapumisilmoituksen; seuraavaksi on vuorossa tulli. | "In transit" |
| `Shipment started the customs clearance process at destination`, `Customs clearance in progress`, `Currently undergoing customs clearance in the destination country`, `Currently processing import customs clearance`, `Customs clearance for imports` | Maasi tulli tullaa pakettia. | "At customs" |
| `Custom clearance completed`, `Shipment completed the customs clearance process at destination`, `Customs clearance has been completed`, `Customs clearance in the destination country has been completed` | Maasi tulli on päästänyt sen läpi. | "In transit" |
| `Scheduled For Delivery` | Shouwun rivi tuontitullauksen jälkeen, päiviä ennen kuriirin varikkoa ja jakelukierrosta: toimitus on suunniteltu, ei vielä käynnissä. | "In transit" |
| `Delivery to local courier`, `The goods have been deliverey to the final delivery service provider`, `The goods have been delivered to the carrier` | Luovutettu kuriirille, joka toimittaa maassasi, ei toimitettu sinulle ("deliverey" on Shouwun kirjoitusasu). | "In transit" |
| `Your consignment has been picked up` | Kuriiri on noutanut sen Shouwun ulkomaiselta varikolta tullin jälkeen: ei nouto myyjältä (se on `Picked up by Shipping Partner` alkupäässä) eikä sinun noutosi. | "In transit" |
| `Picked up` | Yhdysvalloissa FedEx on noutanut paketin Shouwun varikolta tuontitullauksen jälkeen (rivissä mainitaan kaupunki ja postinumero). Ei nouto myyjältä (`Picked up by Shipping Partner`) eikä sinun noutosi. | "In transit" |
| `Arrived at FedEx location`, `Departed FedEx location` | FedExin omat skannaukset sen toimipaikoissa Yhdysvalloissa sen jälkeen, kun se nouti paketin Shouwun varikolta. | "In transit" |
| `The shipment has been processed in the destination parcel center` | DHL on lajitellut sen osoitettasi palvelevassa pakettikeskuksessa ennen jakelukierrosta. | "In transit" |
| `Mis routed` | Saksassa toimittava kuriiri lähetti paketin väärään toimipaikkaan ja lähettää sen eteenpäin: viivästys, ei pysähdys. Jakelukierros voi silti tulla samana päivänä. | "In transit" |
| `Out for delivery` | Paikallisen kuriirin jakelukierroksella. | "Out for delivery" |
| `Abnormal delivery` | Kierroksella ei saatu toimitettua. Kuriirin syy seuraa omana skannauksenaan kulmasulkeissa, kuten `<Incorrect address.>`. | "Delivery attempted" |
| `The recipient is unknown` | DHL:n jakelu Saksassa ei löytänyt kyseistä vastaanottajaa osoitteesta; paketti lähtee takaisin. | "Delivery attempted" |
| `Shipment will be returned to origin-Bad address` | DHL lähettää paketin takaisin, koska ei löytänyt osoitetta: matkalla takaisin, ei vielä myyjällä. Kysy myyjältä. | "Needs attention" |
| `Delivered by post man`, `This parcel has been delivered` | Paikallinen posti tai kuriiri on toimittanut sen. | "Delivered", kun Shouwun tila sanoo `Delivered` |
| `Shipment sent directly from parcel center to business customer` | DHL on toimittanut sen pakettikeskuksestaan suoraan yritysosoitteeseen. Jos DHL oli lähettänyt paketin takaisin, sama rivi tarkoittaa, että palautus saapui lähettäjälle. | "Delivered", tai "Returned to sender" palautuksen jälkeen |

Kun iMile toimittaa Italiassa, Shouwun historia välittää iMilen rivit keskuksen nimi 【】-sulkeissa (omallasi voi lukea muu keskus kuin Milan center tai ROM 10):

| Shouwu sanoo | Mitä se tarkoittaa sinulle | Peek näyttää |
| --- | --- | --- |
| `【Milan center】Received.` | iMilen keskus on vastaanottanut paketin. | "In transit" |
| `【Milan center】Parcel shipped to【ROM 10】.`, `【ROM 10】Arrived.` | Lähetetty eteenpäin iMilen jakeluasemalle ja saapunut sinne. | "In transit" |
| `【ROM 10】Shipment out for delivery.` | iMilen jakelukierroksella. | "Out for delivery" |
| `<User not at home>`, `【ROM 10】 Back To DS.` | Kuljettaja ei tavoittanut sinua, ja paketti on palannut jakeluasemalle. | "Delivery attempted" |
| `【ROM 10】Parcel shipped to【Milan Transit Center】.` | Kahden epäonnistuneen kierroksen jälkeen asema lähettää sen takaisin iMilen keskukseen. | "In transit" |
| `Back to Sender for reason: wrong recipient address or address related problem` | Epäonnistuneiden kierrosten jälkeen iMile lähettää paketin takaisin, tässä osoitteen vuoksi (kaksoispisteen jälkeen voi lukea muu syy): matkalla takaisin, ei vielä lähettäjällä. Kysy myyjältä. | "Needs attention" |
| `【Milan Transit Center】Order Closed.` | iMile sulkee toimitustilauksen ilman toimitusta; kysy myyjältä. | "Needs attention" |

**Kun DPD toimittaa.** Linjalla, jolla [DPD](carrier:dpd-de) toimittaa, Shouwun historia jatkuu DPD:n pisteeseen päättyvillä sanamuodoilla: `In transit.`, sitten `At parcel delivery centre.`, eli DPD:n jakeluvarikko ennen kierrosta ja uudelleen epäonnistuneen yrityksen jälkeen ("In transit"), sekä `Out for delivery.` ("Out for delivery"). `Unfortunately we have not been able to deliver your parcel. Consignee address not correct.` tarkoittaa virheellisen osoitteen vuoksi epäonnistunutta kierrosta: paketti palaa DPD:n varikolle, ei lähettäjälle ("Delivery attempted").

**Kiinankieliset rivit.** Jotkin paketit saavat myös Shouwun kiinaksi kirjoittamia rivejä, jotka Peek näyttää sellaisinaan. `货物电子信息已经收到` (tavaroiden sähköiset tiedot vastaanotettu) tulee tilaustietojen mukana: "Label created". Sitten kaikki "In transit": `申报过港中` eräkoodin jälkeen (erä ilmoitettu kuljetettavaksi Hongkongiin), `已到港，安排航班中` (lähtöpaikassa odottamassa lentoa), `预计23号的航班` (varattu lento, tässä 23. päivälle; `二程` edessä tarkoittaa toista osuutta), `航班已飞` ja `二程航班已飞` (lento tai toisen osuuden lento on lähtenyt) sekä `已落地` (laskeutunut). `已落地，清关提货中` (laskeutunut, tullattavana ja noudettavana lentoyhtiöltä) on "At customs"; `清关完成` (tullattu) ja `已交仓扫描` (skannattu toimittavan kuriirin varikolla) ovat taas "In transit".

**Kellonajat tuntikausia pielessä.** Shouwun järjestelmä kirjaa jokaisen skannauksen paikallisen ajan ikään kuin se olisi Pekingin aikaa, myös skannaukset ulkomailla, ja sen seuranta näyttää sen sinun aikavyöhykkeessäsi. Siksi skannaus Isossa-Britanniassa tai Yhdysvalloissa näkyy tunteja liian aikaisena ja lento näyttää tunteja lyhyemmältä. Luota rivien järjestykseen. Peek päättelee kunkin skannauksen aikavyöhykkeen sen paikan tai paketin kulkuosuuden perusteella ja näyttää ajat laitteesi aikavyöhykkeessä; peektracker.com-sivustolla sekä iPhone- ja Android-sovelluksissa historia päättyy tekstiin "Times are in your time zone:" ja aikavyöhykkeen nimeen.

## Kuka toimittaa Shouwu-paketit

Shouwu kertoo tekevänsä suoraa yhteistyötä USPS:n, Royal Mailin, La Posten, DHL:n, DPD:n ja Hermesin kanssa. Sen kiinankieliset linjasivut nimeävät kuriirit maittain:

- **Iso-Britannia.** Britannian linjat kattavat koko Ison-Britannian. Viimeisen osuuden kuriireina toimivat Royal Mail, Yodel ja Evri (kirjoitettu siellä ERVI); akkuja sisältävien tuotteiden linja kulkee pääasiassa Royal Maililla.
- **Yhdysvallat.** Yhdysvaltain linjat kattavat koko maan, toimittajana pääasiassa USPS; Peekin lukemissa historioissa siellä toimittaa myös [FedEx](carrier:fedex).
- **Euroopan unioni.** Shouwulla on linjoja myös Saksaan, missä toimittavat linjasta riippuen pääasiassa [DHL](carrier:dhl), [Hermes](carrier:hermes-de) tai [DPD](carrier:dpd-de), Ranskaan (pääasiassa [Colissimo](carrier:la-poste)), Alankomaihin (pääasiassa DHL), Ruotsiin (pääasiassa [Early Bird](carrier:early-bird)) ja Italiaan (economy-linja, 10–12 arkipäivää, toimittajina UniUni ja [Poste Italiane](carrier:poste-italiane); Peekin lukemissa historioissa siellä toimittaa myös [iMile](carrier:imile) yllä olevin rivein). Täälläkin myyjä voi antaa kuriirin oman numeron.
- **Irlanti, Kanada ja Australia.** Shouwu ei luettele niille linjaa eikä nimeä kuriiria. Kysy myyjältä, kuka toimittaa.

Shouwun seuranta ei anna kuriirin nimeä eikä numeroa sen lisäksi, mitä yksittäisen skannauksen tekstissä saattaa lukea.

**Epäonnistuneet toimitukset.** Yrityskerrat, saapumisilmoitukset ja noutopisteet noudattavat kuriirin sääntöjä. Tilassa `Undelivered` Shouwun seuranta kehottaa ottamaan heti yhteyttä kuljetusyhtiöön uutta toimitusta tai paketin noutoa varten ennen yhtiön määräaikaa, tai se "might be returned to the sender". Shouwun kiinankielisissä Ison-Britannian ja Yhdysvaltain linjaehdoissa sanotaan, ettei toimittamatonta pakettia palauteta Kiinaan vaan se hävitetään oletuksena. Toimi joka tapauksessa ilmoituksen pohjalta nopeasti. Katso [epäonnistunut toimitus (englanniksi)](guide:missed-delivery) ja [noutamatta jääneet paketit (englanniksi)](guide:uncollected-parcel).

## Shouwu-paketti ei saavu tai seuranta jumissa

**`Not Found`.** Shouwu antaa neljä syytä: se ei ole vielä vastaanottanut pakettia, ei ole vielä skannannut sitä, numero on väärä, tai paketti lähetettiin niin kauan sitten, että sen seuranta on poistunut. Käsittely kestää sen mukaan yleensä muutaman päivän. Peek näyttää "Waiting for Shouwu International" ja jatkaa tarkistamista. Jos Shouwu ei vieläkään tunne numeroa kahden päivän kuluttua eikä mikään muu palvelu ole vastannut siihen, Peek näyttää "After two days, the carrier still doesn’t know this number. Check it, choose another carrier or delete the parcel." Tarkista numero merkki merkiltä; jos muutaman päivän jälkeen ei näy mitään, kysy myyjältä.

**Jumissa tilassa `Preparing`, `Shipment information received` tai `Carrier received the shipment`.** Shouwulla ei vielä ole pakettia, tai se ei ole lähtenyt varastosta. Kysy myyjältä, milloin paketti luovutettiin.

**Jumissa tullissa.** Rivien `Shipment started the customs clearance process at destination`, `Customs clearance in progress`, `Currently undergoing customs clearance in the destination country`, `Currently processing import customs clearance`, `Customs clearance for imports` tai `已落地，清关提货中` jälkeen paketti odottaa maasi tullia. EU-linjoilla EU:n ulkopuolelta verkosta ostetuista tavaroista maksetaan nyt €3 tulli tuotetyyppiä kohden enintään €150 arvoisissa paketeissa (1. heinäkuuta 2026 alkaen) sekä EU:n €2 käsittelymaksu tuotetyyppiä kohden arvosta riippumatta (1. marraskuuta 2026 alkaen); Shouwun linjasivut eivät kerro, kuka ne maksaa. Jos sinulta pyydetään tuontimaksuja, katso [tulliin jääneet paketit (englanniksi)](guide:customs).

**Ei mitään rivin `Delivery to local courier` jälkeen.** Kuriirilla on paketti, mutta Shouwun seuranta ei anna kuriirin numeroa. Pyydä kuriirin numero myyjältä ja liitä se Peekiin. Lisää kohdissa [miksi seuranta lakkaa päivittymästä (englanniksi)](guide:tracking-not-updating) ja [paketin seuranta Kiinasta (englanniksi)](guide:tracking-from-china).

**Toimitettu, mutta mitään ei tullut.** Shouwun linjaehtojen mukaan silloin, kun kuriirin sivu näyttää toimituksen jota ei koskaan saatu, Shouwu auttaa asian selvittämisessä mutta ei maksa korvausta. Kysy siis ensin kuriirilta ja sitten myyjältä. Yhdysvalloissa USPS ottaa vastaan Missing Mail -etsintäpyynnön lähettäjältä tai vastaanottajalta 7 päivän kuluttua postittamisesta. Katso [toimitettu mutta ei vastaanotettu (englanniksi)](guide:delivered-not-received).

**Kadonnut tai vahingoittunut.** Myyjä on Shouwun asiakas, ja Shouwun linjaehtojen mukaan sen on tehtävä reklamaatio 30 päivän kuluessa siitä, kun Shouwu lähetti paketin. Paketista, jolla ei ole toimitusskannausta Shouwun varastoon saapumisen jälkeen, Shouwu korvaa ilmoitetun arvon sekä toimituskulun, enintään 200 juania lähetystä kohden, eikä mitään kuljetusvahingoista. Pyydä siis kaupalta tai markkinapaikalta hyvitystä tai uutta pakettia äläkä jää odottamaan. Katso [kadonneet paketit (englanniksi)](guide:lost-parcel).

## Shouwun yhteystiedot ja asiakaspalvelu

Shouwun verkkosivusto www.sedel.com, sellaisena kuin se luettiin 30. lokakuuta 2026, on kiinankielinen ja tarkoitettu lähetyksiä varaaville myyjille. Se listaa matkapuhelinnumerot Shanghain pääkonttoriin ja viiteen sivukonttoriin, QQ-chatlinkin sekä WeChat-koodit. Sähköpostiosoitetta, yhteydenottolomaketta, englanninkielistä yhteydenottoa tai mitään vastaanottajille suunnattua ei ole. Seuranta ei sisällä lainkaan yhteystietoja.

Kysy tilauksestasi myyjältä: se varasi lähetyksen Shouwulta ja tekee reklamaatiot. Itse toimituksesta kysy maassasi toimivalta kuriirilta.

## Seuraa Shouwu-pakettia Peekissä

[Peek](/) tunnistaa Shouwun numeron sen muodosta: SWECN, kaksitoista numeroa ja YQ, jota mikään muu sen luettelon kuljetusyhtiö ei käytä, ja kysyy sen avulla Shouwun seurannasta. Tiliä, postinumeroa tai puhelinnumeroa ei tarvita.

Peek lukee paketin vaiheen Shouwun tilasta yllä olevien taulukoiden mukaisesti ja näyttää kohdemaan sekä jokaisen skannauksen paikkakuntineen, minkä Shouwun oma sivu jättää pois. Numeron sisältävät paikkatiedot, kuten postinumeron, Peek jättää pois. Shouwu ei anna toimitusarviota, joten Peek ei näytä arvioitua toimituspäivää.

Koska kuriiria ei nimetä, Peek ei näytä "Delivery with" -riviä eikä siirrä pakettia kuriirille, vaikka historia välittäisikin iMilen rivejä. Jos sinulla on kuriirin numero, lisää se Peekiin omana pakettinaan.

Kun pidät pakettia auki tai sen toimituspäivänä ilmoitusten ollessa päällä, Peek tarkistaa enintään 10 minuutin välein ja toimituspäivänä kello 6 paikallista aikaa alkaen enintään 2 minuutin välein, kun paketti on jakelussa ja Shouwun oma seuranta vastaa; muuten tunnin välein. Seurantapalvelujen kautta tarkistusväli on tunti, hiljaisen päivän jälkeen 3 tuntia ja toimituspäivänä enintään 15 minuuttia samoin ehdoin. Kahden uutisettoman päivän jälkeen (12 tunnin jälkeen, kun paketti näyttää yhä tilaa "Label created" tai "Received by carrier") Peek tarkistaa tunnin välein. Se voi ilmoittaa sinulle jokaisesta skannauksesta, vain tärkeistä vaiheista (toimitus, nouto, tulli, epäonnistuneet yritykset ja palautukset) tai "Delivery only". Se ei voi ottaa yhteyttä Shouwuun tai myyjään puolestasi.

## Kysymyksiä Shouwu-seurannasta

### Mikä on Shouwu International (守务国际)?

Shanghailainen logistiikkayritys, 上海守务国际物流有限公司, joka kuljettaa paketteja rajat ylittäville verkkokauppiaille. Se kertoo aloittaneensa vuonna 2018 lentorahtihuolitsijana ja siirtyneensä verkkokauppalinjoihin Eurooppaan ja Yhdysvaltoihin vuonna 2019.

### Missä voin seurata Shouwu-pakettia?

Yllä olevassa kentässä tai Shouwun englanninkielisessä seurannassa osoitteessa admin.shshouwu.com. Käytä koko SWECN…YQ-numeroa.

### Kuka toimittaa Shouwu-paketit Isossa-Britanniassa ja Yhdysvalloissa?

Paikallinen posti tai kuriiri: Shouwun linjasivut nimeävät Isossa-Britanniassa Royal Mailin, Yodelin ja Evrin ja Yhdysvalloissa pääasiassa USPS:n. Irlannille, Kanadalle ja Australialle se ei nimeä ketään, joten kysy myyjältä.

### Kuinka kauan Shouwun toimitus kestää?

Se riippuu myyjän valitsemasta linjasta. Shouwun viiteajat ovat 5–8 arkipäivää Britannian standard-linjalla, 10–12 Britannian economy-linjalla, 6–8 Yhdysvaltain standard-linjalla ja 8–12 Yhdysvaltain economy-linjalla. EU:ssa ne ovat 4–7 arkipäivää Saksaan ja Ranskaan, 4–8 Alankomaihin ja Ruotsiin sekä 10–12 Italiaan.

### Kehen otan yhteyttä kadonneesta Shouwu-paketista?

Myyjään, jolta tilasit: se varasi lähetyksen Shouwulta ja voi tehdä reklamaation 30 päivän kuluessa lähetyksestä. Shouwu ei julkaise yhteystietoja vastaanottajille.

:::sources
- [Shouwu International: 守务国际包裹追踪 (parcel tracking)](https://admin.shshouwu.com/track.html) – englanninkielinen seuranta, 40 numeroa, tilat ja niiden selitykset, skannaukset ilman paikkoja, "Copy Link", "Not Found"
- [Shouwu International: 首页 (home page)](http://www.sedel.com/) – yhteystietoluettelo, QQ-chat, yrityksen nimi
- [Shouwu International: 关于我们 (about us)](http://www.sedel.com/about-us.html) – vuodet 2018 ja 2019, suorat kumppanit
- [Shouwu International: 主营线路 (main lines)](http://www.sedel.com/main-route.html) – linjat maittain
- [Shouwu International: 英国标快 (UK standard express)](http://www.sedel.com/main-route-secondary.html?groupId=1&groupItemId=2) – Royal Mail, Yodel, ERVI, numeron valinta, 5–8 arkipäivää, ei palautusta Kiinaan, reklamaatiot
- [Shouwu International: 英国经济 (UK economy)](http://www.sedel.com/main-route-secondary.html?groupId=2&groupItemId=3) – 10–12 arkipäivää
- [Shouwu International: 英国带电 (UK, batteries)](http://www.sedel.com/main-route-secondary.html?groupId=3&groupItemId=4) – pääasiassa Royal Mail
- [Shouwu International: 美国标快 (US standard express)](http://www.sedel.com/main-route-secondary.html?groupId=1&groupItemId=1) – pääasiassa USPS, 6–8 arkipäivää, ei palautusta Kiinaan, reklamaatiot
- [Shouwu International: 美国经济 (US economy)](http://www.sedel.com/main-route-secondary.html?groupId=2&groupItemId=21) – 8–12 arkipäivää
- [Shouwu International: 德国标快 (Germany standard express)](http://www.sedel.com/main-route-secondary.html?groupId=1&groupItemId=19) – pääasiassa DHL, numeron valinta, 4–7 arkipäivää, reklamaatiot
- [Shouwu International: 德国经济 (Germany economy)](http://www.sedel.com/main-route-secondary.html?groupId=2&groupItemId=22) – pääasiassa Hermes, 4–7 arkipäivää
- [Shouwu International: 德国带电 (Germany, batteries)](http://www.sedel.com/main-route-secondary.html?groupId=3&groupItemId=25) – pääasiassa DPD, 4–7 arkipäivää
- [Shouwu International: 法国标快 (France standard express)](http://www.sedel.com/main-route-secondary.html?groupId=1&groupItemId=20) – pääasiassa Colissimo, 4–7 arkipäivää
- [Shouwu International: 荷兰标快 (Netherlands standard express)](http://www.sedel.com/main-route-secondary.html?groupId=4&groupItemId=28) – pääasiassa DHL, 4–8 arkipäivää
- [Shouwu International: 瑞典经济 (Sweden economy)](http://www.sedel.com/main-route-secondary.html?groupId=4&groupItemId=29) – pääasiassa EarlyBird, 4–8 arkipäivää
- [Shouwu International: 意大利经济 (Italy economy)](http://www.sedel.com/main-route-secondary.html?groupId=4&groupItemId=27) – UniUni ja Poste Italiane, numeron valinta, 10–12 arkipäivää
- [European Commission: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – €3 tulli 1. heinäkuuta 2026 alkaen, €2 käsittelymaksu 1. marraskuuta 2026 alkaen
- [USPS: Missing Mail and Lost Packages](https://www.usps.com/help/missing-mail.htm) – lähettäjät tai vastaanottajat, 7 päivän kuluttua
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – numeromuoto, seuranta ja linkki, tilat, skannausrivit ja vaiheet (vienti- ja kohdetullauksen, lentokenttälastauksen ja luovutusrivit luettu 9. marraskuuta alkaen, kumppanin nouto myyjältä, FedExin nouto Yhdysvaltain varikolta ja laivan lähtö Kiinasta 14. marraskuuta alkaen, FedExin omat skannaukset ja DHL:n toimitus yritykselle 15. marraskuuta alkaen), Italiassa välitetyt iMilen rivit (myös palautus lähettäjälle), DPD:n rivit, kellonajat, paikat, mitä Peek näyttää
:::
