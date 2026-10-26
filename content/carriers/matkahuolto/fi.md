---
title: Matkahuollon seuranta: lähetystunnus, nouto ja tilat
description: Seuraa Matkahuollon pakettia: mistä löydät lähetystunnuksen, mitä seurannan tilat tarkoittavat, kauanko paketti odottaa noutoa ja miten tavoitat Matkahuollon.
slug: matkahuolto-seuranta
published: 2026-10-26
updated: 2026-10-26
---

Matkahuolto kuljettaa paketteja pakettipisteisiin, pakettiautomaatteihin ja kotiovelle eri puolilla Suomea. Pakettia seuraat [Matkahuollon seurannassa](https://www.matkahuolto.fi/seuranta) tai Matkahuolto Paketit -sovelluksessa lähetystunnuksella, tai voit liittää tunnuksen yllä olevaan kenttään.

## Matkahuollon lähetystunnus

Lähetystunnus on jokaisessa Matkahuollon saapumisilmoituksessa. Ennen ilmoitusta saat tunnuksen lähettäjältä. Noutokoodi on eri asia: sillä vain noudetaan paketti.

Matkahuolto ei kerro sivuillaan tunnustensa muotoja. Peek tuntee nämä muodot:

| Tunnus (keksitty) | Mikä se on |
| --- | --- |
| `MH000000005FI` | Matkahuollon oma tunnus: `MH`, yhdeksän numeroa ja `FI`. |
| `MA0000000000100000000` | Matkahuollon oma tunnus: `MA` ja 19 numeroa. |
| `00373000000000000005` | Paketin 20-numeroinen SSCC-viivakoodi. Matkahuollon seuranta löytää sen myös ilman alun nollia, 18 numerona. |

`JJFI`-alkuinen tunnus kuuluu [Postille](carrier:posti), samoin `FI`-loppuinen postitunnus, jonka alussa on muut kirjaimet kuin `MH` (`RR000000005FI`). Jos Matkahuollon seuranta ei löydä pakettia, katso lähettäjän viestistä, mikä yhtiö sen kuljettaa.

## Mitä Matkahuollon seurannan tilat tarkoittavat

Matkahuollon seuranta toimii suomeksi, ruotsiksi ja englanniksi. Peek hakee tiedot englanniksi, joten tässä ovat Matkahuollon englanninkieliset tilat suunnilleen siinä järjestyksessä kuin ne tulevat vastaan:

| Matkahuolto kertoo | Mitä se tarkoittaa sinulle |
| --- | --- |
| `Matkahuolto is waiting for the consignment from the sender` | Lähettäjän tiedot paketista ovat tulleet, mutta itse paketti ei ole vielä Matkahuollolla. |
| `Consignment is being processed` | Paketti on käsittelyssä terminaalissa. |
| `The consignment is ready to continue its journey from Matkahuolto` | Paketti lähtee terminaalista eteenpäin. |
| `The consignment is on its way` | Paketti on matkalla. |
| `Your parcel has been sorted and will be taken next to the pickup point.` | Seuraavaksi paketti viedään noutopisteeseen. |
| `We will deliver the consignment to the pickup point during the day` | Paketti viedään noutopisteeseesi tänään. |
| `Your pick up point has changed` | Paketti menee toiseen noutopisteeseen. Syy ja uusi piste ovat samalla rivillä. |
| `The shipment is available for pickup with the pickup code at the pick up point` | Paketti odottaa noutoa. Rivillä on noutopiste ja viimeinen noutopäivä. |
| `We have sent the recipient a message about the arrival of the consignment (sms).` | Saapumisilmoitus on lähetetty (myös `(e-mail).` tai `(Paketit mobile app notification).`). Itse paketti ei liikkunut. |
| `The consignment has been delivered. Thank you for choosing Matkahuolto!` | Paketti on toimitettu. Noutopisteessä odottanut paketti on silloin noudettu. |

**Saman päivän aikana.** Kuljettajalla on kyydissään usean noutopisteen paketteja, joten sinun pisteesi ei välttämättä ole ensimmäinen. Matkahuollon mukaan paketin pitäisi silti olla noudettavissa saman päivän aikana.

Jos seuranta vastaa `Antamallasi lähetystunnuksella ei löydy vielä tietoja.`, tarkista tunnus tai kokeile myöhemmin uudelleen.

## Pakettipiste, pakettiautomaatti ja kotiinkuljetus

Pakettipisteinä toimivat Matkahuollon omat pisteet sekä muun muassa monet K-kaupat ja R-kioskit, ja pakettiautomaatteja on 1 450.

**Saapumisilmoitus.** Nopeimmin ilmoitus tulee Paketit-sovellukseen. Muuten saat sen WhatsAppiin tai tekstiviestinä ja lisäksi sähköpostiin, jos osoitteesi on lähetyksen tiedoissa. Jos lähettäjä ei antanut puhelinnumeroasi eikä sähköpostiosoitettasi, ilmoitus tulee paperisena. Jos ilmoitusta ei kuulu, katso roskapostikansiot ja pyydä lähettäjää korjaamaan yhteystietosi.

**Nouto.** Tarvitset vain noutokoodin, niin pakettipisteessä kuin automaatilla (näytöltä Nouda). Henkkareita tai valtakirjaa ei tarvita, ellei kyse ole henkilökohtaisesti luovutettavasta lähetyksestä, esimerkiksi alkoholista tai asiakirjoista: siitä kerrotaan saapumisilmoituksessa erikseen. Noutokoodin voit antaa vaikka puolisolle tai kaverille. Sovelluksella toimivasta ulkoautomaatista paketin saa kuitenkin vain vastaanottajan puhelimella; jos et voi käyttää sovellusta, asiakaspalvelu siirtää paketin maksutta toiseen noutopisteeseen. Jos ilmoituksessa ei mainita automaattia, paketti odottaa palvelevassa pakettipisteessä, esimerkiksi kassalla.

**Säilytysaika.** Paketti odottaa 5 päivää, tai 14 päivää, jos saapumisilmoitus tuli paperisena. Viimeinen noutopäivä on saapumisilmoituksessa ja Paketit-sovelluksessa, ja Matkahuolto muistuttaa, kun se lähestyy. Lisäaikaa (1–16 päivää) tai kotiinkuljetuksen voit ostaa sovelluksessa tai rekisteröityneenä verkkosivuilla, ellei lähettäjä ole estänyt sitä. Noutamaton paketti palautuu lähettäjälle.

**Noutopisteen vaihto.** Kun paketti näkyy sovelluksessa jo ennen kuin se on Matkahuollolla, voit vaihtaa noutopisteen kerran maksutta. Kesken kuljetuksen se ei onnistu. Saapumisilmoituksen jälkeen voit ostaa sovelluksessa siirron palvelevasta pakettipisteestä toiseen, pääkaupunkiseudulla myös automaatista (Matkahuolto siirtää paketin yhteistyössä Woltin kanssa). Siirretty paketti saa uuden lähetystunnuksen. Jos valittu piste on täynnä, Matkahuolto vie paketin lähimpään pisteeseen, jossa on tilaa.

**Kotiinkuljetus.** Kotijakelu toimitetaan seuraavana mahdollisena arkipäivänä klo 8–18. Saat ilmoituksen toimituspäivästä ja voit ilmoituspäivän aikana vaihtaa sen johonkin seuraavista viidestä arkipäivästä. Paketti jätetään ovesi taakse säältä suojaan, eikä sinun tarvitse olla kotona. Jakopaketti tuodaan arkena klo 8–16 ilman ennakkoilmoitusta, ja sovelluksessa voit antaa luvan jättää sen ovelle. Kontaktitoimituksessa, jonka voi ostaa myös sovelluksesta lisäpalveluna, paketti luovutetaan osoitteessa tavatulle henkilölle eikä jätetä oven taakse. Jos pakettia ei voi jättää eikä sinua tavoiteta, se viedään seuraavana arkipäivänä lähelläsi olevaan noutopisteeseen.

**FedEx.** Matkahuolto toimittaa myös [FedExin](carrier:fedex) paketteja, arkena klo 8–16 ilman ennakkoilmoitusta. Jos sinua ei tavoitettu, sovi toimituksesta FedExin kanssa.

**Jälkivaatimus.** Matkahuollon mukaan lähetykseen ei voi lisätä vastaanottajan maksettavaa jälkivaatimusta.

**Palautus.** Hae aktivointikoodi sovelluksen Palauta-painikkeella tai verkkosivuilta, kirjoita se, nimesi ja puhelinnumerosi paketin päälle ja vie paketti automaattiin tai pakettipisteeseen. Kun palautat verkkokaupan tilauksen automaatista, tarvitset kaupan palautusosoitekortin tai aktivointikoodin: lähetystunnus tai 9:llä alkava palautustunnus ei käy.

## Matkahuollon paketti myöhässä, kadonnut, rikki tai tullissa

**Matkalla.** Matkahuollon mukaan valtaosa paketeista on perillä lähettämistä seuraavana päivänä ja kaikki viimeistään kolmessa arkipäivässä siitä, kun Matkahuolto on saanut paketin. Sitä ennen paketti voi kulkea muiden kuljetuskumppanien kautta: kotimaan paketti tulee Matkahuollon käsittelyyn yleensä parissa päivässä, ulkomailta tulevassa voi mennä reilu viikko.

**Seuranta ei etene.** Seuranta voi pysähtyä hetkeksi, kun paketti odottaa kuljetusta tai sen pakkausta tarkistetaan. Jos mikään ei muutu, ota yhteyttä lähettäjään. Lue myös, [miksi seuranta ei päivity](guide:tracking-not-updating) (englanniksi).

**Rikki tai kadonnut.** Ilmoita vahingosta viipymättä myyjälle tai lähettäjälle kuvien kera, myös jos paketti on myöhässä tai kadonnut. Älä hävitä tavaraa tai pakkausta ennen kuin Matkahuolto on todennut vahingon. Ulkoisesti näkyvästä vahingosta huomautetaan heti pakettia vastaanotettaessa, muuten kirjallisesti 7 päivän kuluessa; kuluttajalla aikaa on 30 vuorokautta. Kilpailu- ja kuluttajaviraston mukaan kotimaan paketti katsotaan kadonneeksi, jos sitä ei ole luovutettu 28 päivän kuluessa siitä, kun se otettiin kuljetettavaksi, eikä luovutusajasta ole sovittu.

**Kuka hakee korvausta.** Kuluttajansuojalain mukaan myyjä vastaa lähetyksestä kuljetuksen aikana, joten yritykseltä tilatusta paketista korvausta Matkahuollolta hakee aina lähettänyt yritys. Kahden kuluttajan kaupassa korvausta hakee ensisijaisesti lähettäjä. Jos ostit käytetyn tavaran vertaiskauppa-alustalta, ilmoita vahingosta tai katoamisesta alustalle: saat sieltä jatko-ohjeet. Matkahuolto korvaa enintään 20 euroa painokilolta tai tavaran arvon, jos se on pienempi.

**Myyjän kanssa.** Kilpailu- ja kuluttajaviraston mukaan viivästys, joka ei johdu sinusta, on myyjän sopimusrikkomus. Anna myyjälle kohtuullinen lisäaika; jos tavara ei tule silloinkaan, voit purkaa kaupan. Luottokortilla maksetusta ostoksesta voit vaatia hyvitystä myös luottokorttiyhtiöltä. Jos ette pääse sopuun, ota yhteyttä Kilpailu- ja kuluttajaviraston kuluttajaneuvontaan. Sen jälkeen voit pyytää ratkaisua Kuluttajariitalautakunnalta.

**Tulli.** Kaikki EU:n ulkopuolelta tulevat ostokset on tullattava, ja suomalainenkin verkkokauppa voi lähettää tavaran EU:n ulkopuolelta. Euroopan komission mukaan enintään 150 euron lähetyksistä on 1.7.2026 alkaen peritty väliaikaista 3 euron tullia tavaraerältä. Matkahuollon mukaan sen perii ja maksaa verkkokauppa, joko erikseen tai hintaan sisällytettynä. EU:n tulliviranomaiset alkavat 1.11.2026 periä EU:n ulkopuolelta tilatuista verkko-ostoksista lisäksi 2 euron käsittelymaksun tavaraerältä, myös yli 150 euron lähetyksistä, eikä sitä palauteta. Lue lisää [tulliin jääneistä paketeista](guide:customs) (englanniksi).

## Matkahuollon asiakaspalvelu

Matkahuollon sivuilla 26.10.2026:

- **Pakettipalveluiden neuvonta:** 0600 11300, ma–pe 8–18, 2,49 €/min/vastattu puhelu + pvm. Jonotuksesta veloitetaan pvm/mpm.
- **Pakettipalveluiden tuki:** 020 33 6161, ma–pe 8–10 ja 14–16, pvm/mpm. Apua automaatteihin ja kotiinkuljetuksiin.
- **Chat:** Paketit-sovelluksessa maksutta arkisin ja lauantaisin. Ensin vastaa Mauno-chatbot, joka ohjaa tarvittaessa asiakaspalvelijalle.
- **Huijausviestit:** ilmoituslomake, jonka tiedot Matkahuolto käsittelee yhdessä Kyberturvallisuuskeskuksen kanssa.

> Matkahuolto varoittaa huijaussivustoista, kuten matkahuoltoo.fi, ja väärennetyistä lähettäjänimistä (Maktahuolto). Huijausviesti voi tulla samaan viestiketjuun kuin aidot ilmoitukset. Matkahuollon mukaan se ei koskaan välitä maksuja eikä toimi maksuyhdyskäytävänä. Älä avaa linkkiä, vaan syötä lähetystunnus itse seurantaan. Jos annoit jo kortti- tai verkkopankkitietosi, ota heti yhteys pankkiisi ja tee rikosilmoitus.

## Matkahuollon paketin seuranta Peekillä

[Peek](/) kysyy `MH…FI`-tunnuksesta Matkahuollolta, Postilta ja [Chronopostilta](carrier:chronopost) ja `MA`-tunnuksesta vain Matkahuollolta. Jos vain Matkahuolto tuntee tunnuksen, näet tekstin "Matkahuolto has this parcel." Jos myös Posti tuntee sen, valitset itse. Jos kukaan ei vielä tunne sitä, näet tekstin "Not found at … yet. Peek will keep checking after you add it."

SSCC-viivakoodista Peek kysyy Matkahuollolta, jos ensimmäisen numeron jälkeen (20-numeroisessa koodissa `00`:n ja yhden numeron jälkeen) tulee Suomen `64` tai Ruotsin `73`, ja samalla muilta yhtiöiltä, joiden muotoon koodi sopii. Jos vain Matkahuolto tuntee koodin, Peek nimeää Matkahuollon. Jos myös [Posti](carrier:posti), Bring tai [PostNord](carrier:postnord) tuntee sen, Peek nimeää tämän yhtiön: valitse silloin Matkahuolto painikkeella "Change". Muista viivakoodeista Peek ei kysy Matkahuollolta. Liitä koodi silti, sillä Peek seuraa yli 3 500 kuljetusyhtiötä, ja valitse Matkahuolto painikkeella "Change". Matkahuollon seurannan linkki, jossa on `parcelNumber=`, vie Matkahuollolle minkä tahansa tunnuksen.

Peek näyttää paketin tilan ja tapahtumat, palvelun nimen sekä noutopisteen nimen ja katuosoitteen ja voi ilmoittaa muutoksista. Arviota saapumisesta tai painoa Peek ei saa Matkahuollolta, eikä se poimi viimeistä noutopäivää erikseen: katso se saapumisilmoituksesta. Peek ei seuraa pakettia toiselta yhtiöltä Matkahuollolle eikä voi ottaa yhteyttä Matkahuoltoon tai verkkokauppaan puolestasi.

## Kysymyksiä Matkahuollon seurannasta

### Onko Matkahuollon viesti aito?

Aidossa saapumisilmoituksessa lähettäjänä on Matkahuolto ja viestissä on lähetystunnus. Syötä tunnus itse Matkahuollon seurantaan: siitä näet, onko ilmoitus lähetetty ja onko lähetyksellä lisämaksuja. Maksulinkin Matkahuolto lähettää paketin lähettäjälle, kun noutamaton paketti palautuu, ja senkin aitouden voi tarkistaa seurannasta.

### Voiko Matkahuollon pakettia seurata ilman lähetystunnusta?

Voi, jos lähetyksen tiedoissa on puhelinnumerosi tai sähköpostiosoitteesi: paketti näkyy silloin Paketit-sovelluksessa ja, jos olet rekisteröitynyt, verkkosivujen omissa saapuvissa paketeissa. Muuten pyydä tunnus lähettäjältä.

### Kauanko Matkahuollon paketti on matkalla?

Automaattiin tai pakettipisteeseen pääsääntöisesti 1–2 työpäivää, isoimmissa kaupungeissa myös lauantaisin. Matkahuollolle tultuaan paketti on perillä viimeistään kolmessa arkipäivässä.

### Kauanko Matkahuolto säilyttää pakettia?

5 päivää, tai 14 päivää, jos saapumisilmoitus tuli paperisena. Sovelluksessa voit ostaa 1–16 päivää lisää, ellei lähettäjä ole estänyt sitä.

:::sources
- [Matkahuolto: Seuraa pakettia](https://www.matkahuolto.fi/seuranta) – hakukenttä, ilmoitus tuntemattomasta tunnuksesta, kieliversiot
- [Matkahuolto: Usein kysyttyä](https://www.matkahuolto.fi/usein-kysyttya) – noutopisteen vaihto ja siirto, nouto, ulkoautomaatit, kotijakelu ja jakopaketti, FedEx, vertaiskauppa, jälkivaatimus, puhelinnumerot
- [Matkahuolto: Seuraa pakettisi matkaa](https://www.matkahuolto.fi/asiakaspalvelu/paketit/seuraa-paketin-matkaa) – ennen Matkahuoltoa, saman päivän aikana, kolme arkipäivää, tauot, palautuksen maksulinkki
- [Matkahuolto: Miten vastaanotan paketin](https://www.matkahuolto.fi/asiakaspalvelu/paketit/miten-vastaanotan-paketin) – saapumisilmoitukset, WhatsApp, noutokoodi, valtakirja, vahingot
- [Matkahuolto: Vastaanota paketti helposti](https://www.matkahuolto.fi/paketit/vastaanota-paketti) – 1–2 työpäivää, lauantait, kotijakelu arkisin 8–18, lisäsäilytysaika, saapuvat paketit verkossa
- [Matkahuolto: Pakettien säilytysaika muuttuu](https://www.matkahuolto.fi/news/pakettien-sailytysaika-muuttuu-tavoitteena-entistakin-sujuvampi) – 5 ja 14 päivää, viimeinen noutopäivä ja muistutus, pidennys 1–16 päivää
- [Matkahuolto: Näin käytät pakettiautomaattia](https://www.matkahuolto.fi/paketit/nain-kaytat-pakettiautomaattia) – Nouda, verkkokaupan palautus, 9:llä alkava tunnus
- [Matkahuolto: Palauta saapunut paketti helposti](https://www.matkahuolto.fi/paketit/palauta-paketti) – aktivointikoodi ja Palauta-painike
- [Matkahuolto: Matkahuollon pakettipisteet ja -automaatit](https://www.matkahuolto.fi/paketit/palvelupisteverkosto) – K-kaupat, R-kioskit, 1 450 automaattia
- [Matkahuolto: Lataa Paketit-äppi](https://www.matkahuolto.fi/paketit/paketit-sovellus) – chat, Mauno
- [Matkahuolto: Kuljetusvahinko-ohje kuluttajille](https://www.matkahuolto.fi/paketit/kuljetusvahingot) – myyjän vastuu, korvauksen hakija, 7 päivää ja 30 vuorokautta, 20 €/kg
- [Matkahuolto: Tietoa huijausviesteistä](https://www.matkahuolto.fi/paketit/tietoa-huijausviesteista) – huijaussivustot, lähettäjänimet, maksut, palautuskulut, pankki ja rikosilmoitus
- [Matkahuolto: Huijausviestit](https://www.matkahuolto.fi/huijausviestit) – ilmoituslomake, Kyberturvallisuuskeskus
- [Matkahuolto: Uusi tullimaksu astuu voimaan](https://www.matkahuolto.fi/news/uusi-tullimaksu-astuu-voimaan-kuluttajalta-ei-odoteta-toimenpiteita-vaikutus-kohdistuu-arvoltaan-halvimpiin-tilauksiin) – verkkokauppa perii tullimaksun
- [KKV: Posti- ja pakettijakelu](https://www.kkv.fi/kuluttaja-asiat/puhelin-ja-laajakaistapalvelut/posti-ja-pakettijakelu/) – kadonnut paketti 28 päivää, tullaus, EU:n ulkopuolinen varasto
- [KKV: Tavaran toimituksen viivästys](https://www.kkv.fi/kuluttaja-asiat/tavarat/tavaran-toimituksen-viivastys/) – sopimusrikkomus, lisäaika, purku, luottokortti
- [KKV: Tietojenkalastelu](https://www.kkv.fi/kuluttaja-asiat/huijaukset/tietojenkalastelu/) – huijausviesti aitojen ilmoitusten ketjussa
- [Kuluttajariitalautakunta: Etusivu](https://www.kuluttajariita.fi/) – kuluttajaneuvonta ennen ratkaisupyyntöä
- [European Commission: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 3 euron tulli 1.7.2026 alkaen, käsittelymaksu 1.11.2026 alkaen
- [European Commission: Guidance on the 3 EUR customs duty and Union handling fee](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 euroa tavaraerältä, myös yli 150 euron lähetyksistä, ei palauteta
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – tunnusten muodot ja mitä Peek lukee Matkahuollolta
:::
