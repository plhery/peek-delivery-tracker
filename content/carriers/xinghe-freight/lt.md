---
title: Xinghe Freight sekimas: GZXH…YQ numeris ir būsenos
description: Sek Xinghe Freight (星和货运) GZXH…YQ siuntą iš Guangdžou: kodėl naudoja GZYL sistemą, iš anksto suplanuotas Delivered, ES mokesčiai ir kontaktai.
slug: xinghe-freight-siuntos-sekimas
published: 2026-11-27
updated: 2026-11-27
---

Sek Xinghe Freight (星和货运, 广州星和货运代理有限公司, įkurta 2014 m. Guangdžou) siuntą įklijuodamas 16 simbolių `GZXH` + 10 skaitmenų + `YQ` numerį į laukelį aukščiau. Xinghe Freight svetainė sekimo užklausas nukreipia į GZYL nešifruotą HTTP sekimo sistemą, kur etapai — įskaitant `Delivered` — gali būti suplanuoti iš anksto, o ne realiai nuskenuoti; Peek nuskaito šią sistemą už tave, atskiria prognozes nuo tikrų skenavimų ir prireikus pasitelkia kitus sekimo servisus.

## Xinghe Freight sekimo numeris: kaip jis atrodo

Peek atpažįstami Xinghe Freight numeriai sudaryti iš `GZXH`, dešimties skaitmenų ir `YQ` — iš viso 16 simbolių. Su išgalvotais skaitmenimis:

| Numeris | Kas tai yra |
| --- | --- |
| `GZXH4829105736YQ` | `GZXH`, dešimt skaitmenų ir `YQ`: Xinghe Freight formatas. Peek iš karto priskiria Xinghe Freight. |
| `GZXH482910573YQ` | Tik devyni skaitmenys: neatitinka Xinghe formato. Patikrink, ar nepraleistas skaitmuo. |
| `YL4829105736YQ` | `YL`, dešimt skaitmenų ir `YQ`: [GZYL](carrier:gzyl) važtaraščių formatas. Ta pati sekimo sistema, kitas ekspeditorius. |
| `JXCAA4829105736YQ` | Taip pat baigiasi `YQ`, kaip daugelio Kinijos ekspeditorių numeriai, bet prasideda `JXC`: tai [Juxi Express](carrier:juxi-express) numeris. |

Tarpai, brūkšneliai ir mažosios raidės įklijuojant numerį į Peek neturi reikšmės.

**Kur rasti numerį.** Pardavėjas užregistruoja siuntą per Xinghe ir perduoda numerį tau. Jei numerio nerandi, žr. [kaip rasti sekimo numerį](guide:find-tracking-number) (anglų kalba).

## Xinghe Freight sekimo būsenų paaiškinimas

Xinghe sekimas veikia GZYL sistemoje, todėl visi etapai pateikiami GZYL formuluotėmis anglų kalba, kartu su esamomis rašybos klaidomis. Peek juos skaito pagal GZYL etapų sąrašą. Įprasta eilės tvarka:

| Ką rašo sistema | Ką tai reiškia tau | Ką rodo Peek |
| --- | --- | --- |
| `Order information received` | Pardavėjas perdavė užsakymo duomenis. Siuntos dar niekas neturi. | „Label created“ |
| `Arrive at the cross border receiving point` | Ekspeditorius priėmė siuntą: pirmasis fizinis skenavimas jo rankose. | „Received by carrier“ |
| `Goods leave the operation center` | Siunta išvyko iš operacijų centro. | „In transit“ |
| `Arrive at the international airport` | Tarptautiniame oro uoste, laukia skrydžio. | „In transit“ |
| `Departing from the airport` | Lėktuvas išskrido. | „In transit“ |
| `Item arrives at destination airport` | Nusileido paskirties šalyje. | „In transit“ |
| `local carrier Agent Import customs clearance` | Vietinis tarpininkas tavo šalyje vykdo importo muitinės procedūras. | „At customs“ |
| `The import customs keep it for inspection` | Tavo šalies muitinė tikrina siuntą. | „At customs“ |
| `Customs clearance completed` | Muitinė praeita, siunta tęsia kelionę. | „In transit“ |
| `Internationaliy Package received at processing center` | Paskirties šalies apdorojimo centras priėmė išmuitintą siuntą. Tai nėra naujas išsiuntimas. | „In transit“ |
| `Leave the email processing center` | Siunta išvyko iš to apdorojimo centro. | „In transit“ |
| `Arrival sorting, distribution center` | Paskirties šalies skirstymo centre, po teksto nurodant miestą. | „In transit“ |
| `Leaving the sorting center` | Išvyksta iš skirstymo centro, nurodant miestą. | „In transit“ |
| `Package is on the way` | Juda tavo šalyje, nurodant miestą. Tai nereiškia, kad siunta jau perduota kurjeriui pristatymui. | „In transit“ |
| `Delivered` | Pristatymo įrašas su miestu. Tai gali būti planas, o ne skenavimas (žr. žemiau). | „Delivered“ su pastaba apie fiksuotą grafiką, nebent Peek jį sulaiko (žr. žemiau) |

**Per Honkongą.** Siunta taip pat gali rodyti `香港处理中心已收寄【Hong Kong】`: tai pašto priėmimas Honkongo apdorojimo centre, kurį sistema perduoda kinų kalba su miestu skliaustuose. Peek rodo eilutę taip, kaip parašyta, su būsena „Received by carrier“.

**Eilutė kinų kalba.** Užsakymui be skenavimų sistemos puslapyje gali pasirodyti `货物电子信息已经收到`, reiškianti, kad gauta elektroninė informacija apie prekes. Šią eilutę platforma įrašo pati: pardavėjas sukūrė užsakymą, bet siuntos dar niekas neskenavo. Peek rodo ją kinų kalba po „Label created“.

**Laikas.** Peek visus šios sistemos laikus skaito Pekino laiku, įskaitant etapus tavo šalyje, ir rodo tavo įrenginio laiko juostoje. Žiemą 20:00 Pekine yra 13:00 Paryžiuje ar Berlyne, 12:00 Londone ir 7:00 Niujorke.

**Iš anksto įrašyti etapai.** Šioje sekimo platformoje ekspeditoriai gali iš anksto sukurti visą siuntos eigą nuo priėmimo iki paskutinio etapo tavo šalyje, o sistema kiekvieną jų parodo atėjus jo laikui. Toks įrašas yra planas, o ne skenavimas. GZYL sistemoje planas baigiasi įrašu `Delivered`, kurį platforma prideda vakarinio paketo metu rodomą dieną: su kitos dienos po paskutinio suplanuoto etapo data, Pekino darbo valandomis, nepriklausomai nuo tavo šalies. Peek šiuos etapus sulaiko (žr. žemiau), bet kitų sekimo servisų kopijos vis tiek gali juos rodyti, tad ten esantis `Delivered` neįrodo, kad siunta atvyko.

## Kas pristato Xinghe Freight siuntas

Peek nežino, kuri vietos įmonė pristato Xinghe siuntas tavo šalyje. Sistemos įrašai šalyje nenurodo jokios įmonės, o Xinghe svetainė taip pat jos nemini. Vienintelis vežėjas, kuriam Peek gali perduoti Xinghe siuntą, yra [Swiss Post](carrier:swiss-post) Šveicarijoje ir Lichtenšteine, kai Xinghe įrašas ją nurodo.

Pačioje Xinghe svetainėje nėra informacijos apie siuntas pirkėjams internete: nei pristatymo terminų, nei bandymų skaičiaus, nei atsiėmimo punktų. Galioja galutinio vežėjo taisyklės. Paklausk pardavėjo, kuri įmonė pristato tavo užsakymą, ir paprašyk jos sekimo numerio.

**Muitinės mokesčiai ES.** Nuo 2026 m. liepos 1 d. internetu iš ne ES šalių pirktoms prekėms siuntose iki 150 € taikomas 3 € muitas už kiekvieną prekių rūšį. Nuo 2026 m. lapkričio 1 d. taip pat taikomas 2 € Sąjungos tvarkymo mokestis už kiekvieną prekių rūšį, nepriklausomai nuo siuntos vertės. Prekių rūšis reiškia tas pačias tarifų klasifikacijos, aprašymo ir, kur privaloma, kilmės prekes siuntoje, nesvarbu, kiek vienetų. Daugiau informacijos: [muitinėje sulaikytos siuntos](guide:customs) (anglų kalba).

## Xinghe Freight siunta neatvyksta arba sekimas sustojo

**Tik `Order information received` arba `货物电子信息已经收到`.** Pardavėjas sukūrė užsakymą, bet siuntos dar niekas neskenavo. Jei po kelių dienų niekas nepasikeičia, pasiteirauk pardavėjo, ar ji tikrai išsiųsta.

**„Waiting for Xinghe Freight“.** Peek dar neturi nė vieno tikro etapo: GZYL sistema nežino numerio arba laiko tik iš anksto įrašytą planą (galintį baigtis `Delivered`), o kiti sekimo servisai taip pat nieko neranda. Peek toliau tikrina. Nežinomam numeriui GZYL sistema pateikia vieną tuščią eilutę su numeriu nuorodos laukelyje; Peek tai laiko nerastu, rodo „Waiting for Xinghe Freight“, o po dviejų dienų — „Check the tracking number“. Iš anksto įrašytas planas patvirtina, kad Xinghe turi užsakymą, todėl Peek toliau rodo „Waiting for Xinghe Freight“ be raginimo tikrinti numerį. Palygink numerį su pardavėjo žinute.

**Užstrigo muitinėje.** Jei prašoma importo mokesčių, žr. [muitinėje sulaikytos siuntos](guide:customs) (anglų kalba). Jei siunta stovi muitinėje kelias dienas, pranešk pardavėjui.

**Sistema ar kitas servisas rodo daugiau nei Peek.** Papildomi etapai — įskaitant `Delivered` — gali būti iš anksto suplanuoti įrašai, kuriuos Peek sulaiko. Vadovaukis parduotuvės pristatymo terminu ir parašyk pardavėjui jam pasibaigus. Plačiau: [kodėl sekimas neatnaujinamas](guide:tracking-not-updating) (anglų kalba).

**`Delivered`, bet nieko negavai.** Šis etapas gali būti iš anksto įrašyto grafiko dalis. Jei siuntos nepristato Swiss Post, Peek po „Delivered“ rodo: „Xinghe Freight updates on a fixed schedule, with no signature. If nothing came, ask the seller.“ Pranešk pardavėjui, kad siunta neatvyko, ir paklausk, kokia įmonė ją pristato bei koks jos numeris. Žr. [pažymėta kaip pristatyta, bet negauta](guide:delivered-not-received) (anglų kalba).

**Pamesta arba grąžinta.** Siuntą per Xinghe užsakė pardavėjas, tad dėl pinigų grąžinimo ar naujos siuntos kreipkis į pardavėją arba prekyvietę.

## Xinghe Freight kontaktai ir klientų aptarnavimas

2026 m. lapkričio 6 d. duomenimis, tik kinų kalba veikianti Xinghe svetainė (xinghe-express.cn) nurodo:

- **Karštoji linija:** 19924483103, anot svetainės veikianti visą parą kasdien (iš užsienio +86 199 2448 3103).
- **El. paštas:** 3975605292@qq.com, skirtas verslo užklausoms ir kainų pasiūlymams.
- **Adresas:** Guangdžou, Guangdongo provincija, be gatvės; norint gauti visą adresą, prašoma pirmiausia susisiekti.

Svetainėje nėra pagalbos gavėjams užsienyje: kontaktų forma tik atidaro el. laišką minėtu adresu. Xinghe klientas yra pardavėjas, todėl pirmiausia kreipkis į pardavėją, o jei jis neatsako — į prekyvietę.

## Xinghe Freight siuntos sekimas su Peek

[Peek](/) iškart atpažįsta Xinghe Freight pagal `GZXH…YQ` numerį su žyma „Detected carrier“. Jei pardavėjas davė kitokio formato numerį, vis tiek įklijuok jį: Peek seka daugiau nei 4 500 vežėjų. Norėdamas priskirti rankiniu būdu, paspausk „Change“ ir ieškok Xinghe Freight (tinka ir `星和货运`) arba naudok „Change carrier“ jau pridėtai siuntai.

Peek užklausia GZYL sistemą lygiai taip pat, kaip pačios Xinghe sekimo langelis. Rodo būseną, kiekvieną etapą su vietove ir paskirties šalį, jei įrašas ją turi. Laikai skaitomi Pekino laiku ir rodomi tavo įrenginio laiko juostoje. Peek nerodo vardų, adreso, pašto kodų, svorio, mokėjimo duomenų, parašo ar pristatymo patvirtinimo, ir nerodo numatomos pristatymo datos, nes sistema jos neduoda.

Kai GZYL sistema nežino tavo numerio, Peek užklausia kitų sekimo servisų ir parodo jų turimą kopiją, kuri kartais būna vieninteliai duomenys Xinghe numeriui. Kol nė vienas neturi etapo, rodoma „Waiting for Xinghe Freight“, o po dviejų dienų — „Check the tracking number“.

Peek sulaiko kiekvieną etapą, įrašytą daugiau nei 12 valandų anksčiau už jo rodomą laiką, bet kurį etapą, įrašytą tą pačią sekundę kaip etapas, sukurtas daugiau nei pusvalandį prieš savo laiką (išskyrus užsakymo registraciją), ir bet kurį etapą su ateities data pagal Pekino laiką. Įrašui su iš anksto sukurtais etapais sulaikomas ir `Delivered`, net jei įrašytas tą pačią dieną. Peek vadovaujasi paliktais etapais, o ne suvestine būsena, ir atideda sekimo servisų kopijuojamus sulaikytus etapus (įskaitant `Delivered`), net kai kiti etapai paliekami. Tačiau jei pati sistema neturi įrašo arba nepasiekiama, Peek negali atskirti suplanuoto etapo sekimo serviso kopijoje nuo skenavimo. Sulaikius prognozes, istorija prasideda eilute (iki pristatymo ar grąžinimo): „Other sites may show more steps, but they’re only predictions.“, o mygtukas „Show them“ pilkai parodo sulaikytus etapus su diena („Predicted for Mon 12 Oct“). Toks etapas niekada nelaikomas skenavimu: nekeičia būsenos ir nesiunčia pranešimo.

Išskyrus Swiss Post, Peek neperkelia Xinghe siuntos pas galutinį vežėją: jei pardavėjas davė to vežėjo numerį, pridėk jį kaip atskirą siuntą. Kol siunta atidaryta arba jos pristatymo dieną su įjungtais pranešimais, Peek tikrina iki kas 10 minučių; kitu metu ir nurimus skenavimams — kas valandą. Pranešimai: kiekvienas skenavimas, tik svarbūs etapai arba tik pristatymas („Important steps“ apima du muitinės etapus ir `Delivered`, bet ne skrydžius ar skirstymą). Peek negali susisiekti su Xinghe Freight, GZYL ar pardavėju tavo vardu.

## Klausimai apie Xinghe Freight sekimą

### Kas yra Xinghe Freight (星和货运)?

广州星和货运代理有限公司 (Guangzhou Xinghe Freight Forwarding) — 2014 m. Guangdžou įkurta logistikos ekspedijavimo įmonė, vykdanti jūrų, oro ir sausumos gabenimą, laivų agentavimą ir muitinės formalumus. Jos siuntų numeriai turi 16 simbolių: `GZXH`, dešimt skaitmenų ir `YQ`.

### Kodėl mano Xinghe Freight siunta rodo GZYL sekimą?

Xinghe sekimo langelis užklausas siunčia tiesiai į GZYL sekimo sistemą, todėl etapų tekstai sutampa su GZYL; Peek išlaiko siuntą prie Xinghe Freight ir nuskaito šią sistemą už tave.

### Kas pristato Xinghe Freight siuntas mano šalyje?

Nei Xinghe svetainė, nei sekimo sistema nenurodo vietinio pristatymo partnerio (Peek gali siuntą perduoti tik Swiss Post Šveicarijoje ir Lichtenšteine, kai Swiss Post nurodyta Xinghe įraše). Paklausk pardavėjo, koks vietinis vežėjas pristato siuntą, ir paprašyk jo numerio.

### Kodėl Peek nerodo įrašų arba rodo mažiau etapų mano GZXH numeriui?

Arba sistema dar neturi įrašo, arba jame yra iš anksto suplanuoti etapai — įskaitant numatytą `Delivered` — kuriuos Peek perkelia po „Show them“ ir nelaiko skenavimais. Sutikrink numerį su pardavėjo laišku ir paklausk pardavėjo, jei po kelių dienų niekas neatsinaujina.

### Kaip susisiekti su Xinghe Freight?

Pirmiausia kreipkis į pardavėją arba prekyvietę, nes siuntimą užsakė pardavėjas. Kinų kalbos svetainė `xinghe-express.cn` nurodo visą parą veikiančią liniją 19924483103 (+86 199 2448 3103 iš užsienio) ir verslo el. paštą 3975605292@qq.com, tačiau atskiro kanalo gavėjams užsienyje nėra.

:::sources
- [Xinghe Freight: 广州星和货运代理有限公司 (home page)](http://xinghe-express.cn/) – company name, freight forwarding services, tracking box that opens GZYL's tracker, hotline and email
- [Xinghe Freight: 关于我们 (about)](http://xinghe-express.cn/about.html) – founded in 2014, sea, air and land freight, ship agency, customs declaration
- [Xinghe Freight: 产品服务 (services)](http://xinghe-express.cn/services.html) – services offered, nothing on parcels for online shoppers
- [Xinghe Freight: 联系我们 (contact)](http://xinghe-express.cn/contact.html) – 24-hour hotline, email for business enquiries and quotes, address given as Guangzhou only, get in touch before visiting, contact form opening an email
- [European Commission: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – €3 customs duty per kind of product on parcels up to €150 since 1 July 2026, €2 Union handling fee since 1 November 2026
- [European Commission: Guidance on the €3 customs duty and the Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – handling fee per item whatever the value, what an item is
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – names, GZXH…YQ number shape, Xinghe read on GZYL's tracker, step wordings and stages, Hong Kong acceptance, Chinese line, Beijing clock, timelines written ahead, tracking services as fallback, the tracker's answer for an unknown number, Swiss Post hand-over, what Peek shows, holds back and leaves out
:::
