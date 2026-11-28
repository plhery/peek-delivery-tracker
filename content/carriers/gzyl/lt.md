---
title: GZYL siuntos sekimas: YL…YQ numeris, būsenos ir pagalba
description: Sek GZYL (广州云链, Guangzhou Yunlian) siuntą: YL…YQ formatas, būsenų reikšmės, kodėl Delivered gali būti suplanuotas iš anksto, Swiss Post ir kontaktai.
slug: gzyl-siuntos-sekimas
published: 2026-11-27
updated: 2026-11-27
---

GZYL (广州云链, Guangzhou Yunlian, 广州云链货运代理有限公司) yra krovinių ekspeditorius iš Guangdžou, kurio važtaraščiai turi formatą `YL`, dešimt skaitmenų ir `YQ`. Įklijuok savo `YL…YQ` numerį aukščiau esančiame laukelyje, kad galėtum sekti siuntą ir atskirti iš anksto suplanuotas prognozes nuo tikrų skenavimų: GZYL sekimo sistema (pasiekiama iš yunlianhouyun.com) gali iš anksto įrašyti visą siuntos kelionės grafiką, todėl joje rodomas `Delivered` ne visada reiškia realų pristatymo skenavimą.

## GZYL sekimo numeris: kaip jis atrodo

GZYL važtaraščius sudaro `YL`, dešimt skaitmenų ir `YQ` — iš viso 14 simbolių. Su išgalvotais skaitmenimis:

| Numeris | Kas tai yra |
| --- | --- |
| `YL4829105736YQ` | `YL`, dešimt skaitmenų ir `YQ`: standartinis GZYL važtaraščio formatas. Peek patikrina jį GZYL sekimo sistemoje. |
| `YL7384829105YQ` | `YL738`, septyni skaitmenys ir `YQ`: pagal šį formatą Peek iš karto atpažįsta GZYL. |
| `YL482910573YQ` | Tik devyni skaitmenys: tai nėra GZYL formatas. Patikrink, ar nepraleistas skaitmuo. |
| `GZXH4829105736YQ` | `GZXH`, dešimt skaitmenų ir `YQ`: Xinghe Freight (星和货运), kitas ekspeditorius. Jo sekimas veikia per GZYL sistemą ir naudoja tas pačias formuluotes. |
| `JXCAA4829105736YQ` | Taip pat baigiasi `YQ`, kaip daugelio Kinijos ekspeditorių numeriai, bet prasideda `JXC`: tai [Juxi Express](carrier:juxi-express) numeris. |

Įklijuojant numerį į Peek, tarpai ir mažosios raidės neturi reikšmės.

**Kur rasti numerį.** Pardavėjas užregistruoja siuntą per GZYL ir perduoda numerį pirkėjui. Jei numerio nerandi, žr. [kur ieškoti siuntos sekimo numerio](guide:find-tracking-number) (anglų kalba).

## GZYL sekimo būsenų paaiškinimas

GZYL savo etapus rašo angliškai, kartu su pasitaikančiomis rašybos klaidomis. Eilės tvarka, kuria siunta paprastai gauna įrašus:

| Ką rašo GZYL | Ką tai reiškia tau | Ką rodo Peek |
| --- | --- | --- |
| `Order information received` | Pardavėjas perdavė GZYL užsakymo duomenis. GZYL dar neturi pačios siuntos. | „Label created“ |
| `Arrive at the cross border receiving point` | GZYL priėmė siuntą: pirmasis fizinis skenavimas jų rankose. | „Received by carrier“ |
| `Goods leave the operation center` | Siunta išvyko iš GZYL operacijų centro. | „In transit“ |
| `Arrive at the international airport` | Tarptautiniame oro uoste, laukia skrydžio. | „In transit“ |
| `Departing from the airport` | Lėktuvas išskrido. | „In transit“ |
| `Item arrives at destination airport` | Nusileido paskirties šalyje. | „In transit“ |
| `local carrier Agent Import customs clearance` | Vietinis tarpininkas tavo šalyje vykdo importo muitinės formalumus. | „At customs“ |
| `The import customs keep it for inspection` | Tavo šalies muitinė tikrina siuntą. | „At customs“ |
| `Customs clearance completed` | Muitinė praeita, siunta tęsia kelionę. | „In transit“ |
| `Internationaliy Package received at processing center` | Paskirties šalies apdorojimo centras priėmė išmuitintą siuntą. Tai nėra naujas išsiuntimas. | „In transit“ |
| `Leave the email processing center` | Siunta išvyko iš to apdorojimo centro. | „In transit“ |
| `Arrival sorting, distribution center` | Paskirties šalies skirstymo centre, po teksto nurodant miestą. | „In transit“ |
| `Leaving the sorting center` | Išvyksta iš skirstymo centro, nurodant miestą. | „In transit“ |
| `Package is on the way` | Juda tavo šalyje, nurodant miestą. Tai nereiškia, kad kurjeris jau veža pristatyti. | „In transit“ |
| `Delivered` | Pristatymo įrašas su miestu. Tai gali būti planas, o ne skenavimas (žr. žemiau). | „Delivered“ su pastaba apie fiksuotą grafiką, nebent Peek jį sulaiko (žr. žemiau) |

**Per Jungtines Valstijas.** Siunta taip pat gali rodyti `Los Angeles Processing Center has received it` su miesto pavadinimu skliaustuose: apdorojimo centras JAV priėmė ją pakeliui į paskirties vietą. Peek rodo „In transit“.

**Eilutė kinų kalba.** Užsakymui be fizinių skenavimų platforma, kurioje veikia GZYL sekimas, rodo vieną eilutę `货物电子信息已经收到` („elektroninė informacija apie prekes gauta“). Pardavėjas sukūrė užsakymą, tačiau siuntos dar niekas neskenavo. Peek rodo šią eilutę kinų kalba su būsena „Label created“.

**Per China Post.** Taip pat gali pasirodyti `广州处理中心已收寄` su miestu skliaustuose: tai China Post priėmimas Guangdžou apdorojimo centre, kurį GZYL persiunčia kinų kalba. Xinghe Freight numeriuose `香港处理中心已收寄` reiškia tokį patį priėmimą Honkongo apdorojimo centre. Peek abi eilutes rodo kinų kalba su būsena „Received by carrier“.

**Laikas.** Visi laikai GZYL sekimo puslapyje pateikiami Pekino laiku — įskaitant etapus tavo šalyje — nenurodant laiko juostos. Žiemą 20:00 Pekino laiku atitinka 12:00 Londone ir 7:00 Niujorke.

**Iš anksto įrašyti etapai.** GZYL gali surašyti visą siuntos istoriją iš anksto — nuo priėmimo iki paskutinio etapo tavo šalyje — gerokai anksčiau, nei ateina įrašuose nurodytas laikas, o sekimo puslapis parodo kiekvieną eilutę atėjus jos laikui. Tokiu būdu įrašytas etapas yra planas, o ne tavo siuntos skenavimas. [Meiteshun](carrier:mtscn), naudojantis tą pačią sekimo platformą, elgiasi lygiai taip pat. Tada GZYL planas baigiasi būsena `Delivered`, kurią platforma prideda vakarinio paketo metu tą dieną, kurią ji rodoma: su data kitą dieną po paskutinio suplanuoto etapo, Pekino darbo valandomis, nepriklausomai nuo tavo šalies. Kitos sekimo svetainės, kopijuojančios GZYL įrašus, taip pat gali parodyti šias eilutes, todėl `Delivered` GZYL puslapyje ar sekimo agregatoriuje neįrodo, kad siunta išties pristatyta.

## Kas pristato GZYL siuntas

Peek nežino, kuris vietinis vežėjas pristato GZYL siuntas daugumoje šalių (įskaitant JAV, Jungtinę Karalystę, Airiją, Kanadą, Australiją ar Lietuvą): GZYL įrašai gavėjo šalyje nenurodo jokios įmonės pavadinimo. Vienintelis pristatantis vežėjas, kuriam Peek gali perduoti GZYL siuntą, yra [Swiss Post](carrier:swiss-post) Šveicarijoje ir Lichtenšteine.

Peek nerado GZYL skelbiamų pristatymo terminų, pristatymo bandymų taisyklių, atsiėmimo punktų ar saugojimo trukmės — juos nustato galutinę dalį pristatanti įmonė. GZYL sekimo puslapyje peržiūrėk stulpelį `渠道转单号` (perdavimo numeris) ir stulpelį `跟踪号码` (sekimo numeris), kai jame nurodyta kas nors kita nei tavo `YL…YQ` numeris: bet kuris jų gali būti vietinio vežėjo numeris, kurį gali pridėti į Peek kaip atskirą siuntą. Jei tokio numerio nėra, paklausk pardavėjo, kuri įmonė pristato tavo užsakymą, ir paprašyk jos sekimo numerio.

## GZYL siunta neatvyksta arba sekimas sustojo

**Tik `Order information received` arba `货物电子信息已经收到`.** Pardavėjas sukūrė užsakymą, bet GZYL dar neskenavo pačios siuntos. Jei per kelias dienas neatsiranda naujų įrašų, pasiteirauk pardavėjo, ar siunta iš tikrųjų išsiųsta.

**Užstrigo muitinėje.** Jei prašoma sumokėti importo mokesčius, žr. [siuntos, sulaikytos muitinėje](guide:customs) (anglų kalba). Jei siunta muitinėje išbūna kelias dienas be pokyčių, pranešk pardavėjui.

**GZYL svetainė arba kitas sekimo servisas rodo daugiau nei Peek.** Peek sulaiko etapus, įrašytus anksčiau už jų rodomą laiką, GZYL planą užbaigiantį `Delivered` bei įrašus su ateities data pagal paskutinį patikrinimą (žr. žemiau), todėl papildomi įrašai gali būti GZYL planas, o ne tikri skenavimai. Iš anksto GZYL įrašytas etapas niekada nelaikomas skenavimu: jis nekeičia būsenos ar prognozuojamos datos ir nesiunčia pranešimo. Kai Peek sulaiko tokius etapus, sekimo istorija prasideda eilute (iki pristatymo arba grąžinimo): „Other sites may show more steps, but they’re only predictions.“ Šalia esantis mygtukas „Show them“ pilkai parodo sulaikytus etapus nuo naujausio, su GZYL nurodyta diena („Predicted for Mon 12 Oct“). Vadovaukis parduotuvės nurodytu pristatymo terminu ir kreipkis į pardavėją jam pasibaigus. Daugiau informacijos — [kodėl sekimas nebeatsinaujina](guide:tracking-not-updating) (anglų kalba).

**`Delivered`, bet nieko negavai.** Šis etapas gali būti GZYL plano dalis, o ne tikras skenavimas. Jei siuntos nepristato Swiss Post, Peek po „Delivered“ parodo įspėjimą: „GZYL updates on a fixed schedule, with no signature. If nothing came, ask the seller.“ Pranešk pardavėjui, kad siunta neatvyko, ir paklausk, kokia įmonė ją pristato bei koks jos numeris. Daugiau žingsnių — [pažymėta kaip pristatyta, bet negauta](guide:delivered-not-received) (anglų kalba).

**Nieko nerasta.** Nežinomam numeriui GZYL sekimo sistema grąžina vieną eilutę su tavo numeriu skiltyje `参考号` (nuoroda), o visi kiti laukai lieka tušti. Peek tokį atsakymą laiko nerastu: rodo „Waiting for GZYL“, o po dviejų dienų — „Check the tracking number“. Palygink numerį su pardavėjo žinute.

**Pamesta arba grąžinta.** Siuntą per GZYL užsakė pardavėjas, todėl dėl pinigų grąžinimo ar pakartotinio siuntimo kreipkis į pardavėją arba prekyvietę.

## GZYL kontaktai ir klientų aptarnavimas

2026 m. lapkričio 10 d. duomenimis, GZYL svetainės kontaktų puslapyje (`联系我们`, kinų kalba) nurodyta visą parą veikianti karštoji linija (`全国24小时服务热线`), WeChat paskyra ir po pardavimo skirtas QQ kontaktas (`售后QQ`), tačiau gavėjams skirtos pagalbos nėra: šie kanalai aptarnauja GZYL klientus — pardavėjus. Kadangi siuntimą užsakė pardavėjas, dėl užsakymo kreipkis į jį, o jei jis neatsako — į prekyvietę.

## GZYL siuntos sekimas su Peek

[Peek](/) iškart priskiria GZYL numeriams, prasidedantiems `YL738` su septyniais skaitmenimis ir `YQ` („Detected carrier“). Kiti `YL`, dešimties skaitmenų ir `YQ` numeriai yra tik silpnas atitikmuo. Vis tiek įklijuok savo numerį: Peek seka daugiau nei 4 500 vežėjų. Pridedant siuntą Peek užklausia GZYL sekimo sistemą („Checking tracking services…“) ir parodo „GZYL has this parcel.“, kai sistema grąžina rezultatą tam numeriui — net ir tuščią eilutę, kurią rodo nežinomam numeriui. Priešingu atveju paspausk „Change“ ir pasirink GZYL skiltyje „Fits this number“ (įvedus `广州云链` jį taip pat ras), arba naudok „Change carrier“ jau pridėtai siuntai. Veikia ir pardavėjo nuorodos numeris, bet tik pasirinkus GZYL rankiniu būdu per „Change“ (arba „Change carrier“): tada Peek parodo patį užsakymo numerį su žyma „GZYL tracks this as“. Tačiau Peek pašalina brūkšnelius, todėl nuoroda su brūkšneliais nerandama — paprašyk pardavėjo `YL…YQ` numerio.

Peek skaito pačią GZYL sekimo sistemą ir rodo būseną, kiekvieną etapą su vietove bei paskirties šalį, kai GZYL įrašas ją pateikia. Laikai skaitomi Pekino laiku ir rodomi tavo įrenginio laiko juostoje; svetainėje peektracker.com ir iPhone bei Android programėlėse istorija baigiasi eilute „Times are in your time zone:“ ir juostos pavadinimu. Peek nerodo vardų, adreso, pašto kodų, svorio, mokesčių, parašo ar pristatymo patvirtinimo, kuriuos gali turėti GZYL įrašas, ir nerodo prognozuojamos pristatymo datos, nes GZYL jos neteikia.

Peek sulaiko kiekvieną etapą, įrašytą daugiau nei dvylika valandų anksčiau už jo rodomą laiką, bet kurį etapą, įrašytą tą pačią sekundę kaip etapas, sukurtas daugiau nei pusvalandį prieš savo laiką (išskyrus užsakymo registraciją), ir bet kurį etapą, datuotą vėliau nei tikrinimo momentas Pekino laiku. Užsakymui su iš anksto įrašytais etapais sulaikomas ir `Delivered`, net jei jis įrašytas tą pačią dieną. Kai duomenų sąsaja neturi etapų, Peek skaito GZYL sekimo tinklalapį; tas puslapis nerodo įrašymo laiko, todėl ten Peek sulaiko visus `Delivered`. Būsena nustatoma pagal paliktus etapus, o ne pagal suvestinę įrašo būseną. Užsakymas, kurio visi etapai sulaikyti, Peek sistemoje dar neturi istorijos: Peek rodo „Waiting for GZYL“ be raginimo tikrinti numerį, nes iš anksto įrašytus etapus laiko patvirtinimu, kad GZYL turi užsakymą (kaip ir užsakymą be skenavimų), ir toliau tikrina GZYL sistemą. Peek taip pat atideda kitų sekimo servisų kopijuojamus sulaikytus GZYL plano etapus, įskaitant `Delivered`, net jei kiti etapai paliekami. Tačiau kai pats GZYL sekimo puslapis nepasiekiamas, Peek klausia sekimo servisų, o jų kopijose gali likti iš anksto įrašyti GZYL etapai.

Išskyrus perdavimą Swiss Post, Peek lieka prie GZYL: jei pardavėjas duoda vietinio vežėjo numerį, pridėk jį kaip atskirą siuntą. Kol turi atidaręs siuntą arba jos pristatymo dieną su įjungtais pranešimais, Peek tikrina iki kas 10 minučių, o dieną, kai siunta rodo „Out for delivery“, — iki kas 2 minutes (nuo 6 val. vietos laiku arba pusvalandį prieš vežėjo nurodytą langą), kol atsako GZYL arba Swiss Post sistema, nors joks Peek žinomas GZYL etapas šios būsenos nesuteikia. Kitu metu tikrinama kas valandą. Per sekimo servisus — kas valandą, po ramios dienos kas 3 valandas ir pristatymo dieną tomis pačiomis sąlygomis iki kas 15 minučių. Perėmus Swiss Post, Peek pereina prie jos ritmo. Po dviejų dienų be naujienų (arba 12 valandų esant „Label created“ ar „Received by carrier“) Peek tikrina kas valandą. Pranešimus galima pasirinkti kiekvienam skenavimui, tik svarbiems etapams arba tik pristatymui: „Important steps“ apima du GZYL muitinės etapus ir `Delivered`, bet ne skrydžius ar skirstymą. Peek negali susisiekti su GZYL ar pardavėju tavo vardu.

## Klausimai apie GZYL siuntų sekimą

### Kas yra GZYL (广州云链)?

GZYL yra 广州云链货运代理有限公司 (Guangzhou Yunlian) — krovinių ekspeditorius iš Guangdžou (Kinija), kurio kinų kalbos svetainė yra yunlianhouyun.com. Jo važtaraščiai naudoja 14 simbolių formatą `YL`, dešimt skaitmenų ir `YQ`.

### Ar kiekvienas YL…YQ numeris priklauso GZYL?

Ne automatiškai. `YL`, dešimt skaitmenų ir `YQ` yra GZYL formatas — numerius `YL738…YQ` Peek priskiria GZYL iš karto, o kitus `YL…YQ` kodus patikrina GZYL sistemoje. Devyni ar vienuolika skaitmenų neatitinka GZYL, o `GZXH…YQ` priklauso Xinghe Freight.

### Kodėl GZYL rodo „Delivered“, nors siunta neatvyko?

GZYL dažnai surašo didelę dalį siuntos eigos iš anksto ir prideda `Delivered` pagal fiksuotą grafiką, o ne pagal realų vietinio kurjerio skenavimą. Peek sulaiko suplanuotus etapus ir įspėja apie fiksuotą GZYL grafiką — pranešk pardavėjui, kad nieko negavai, ir paklausk, kuri įmonė pristato siuntą.

### Kas pristato GZYL siuntas mano šalyje?

GZYL įrašai niekada nenurodo vietinio pristatymo vežėjo (tik Šveicarijoje ir Lichtenšteine Peek gali perduoti siuntą Swiss Post). Patikrink stulpelį `渠道转单号` (perdavimo numeris) GZYL puslapyje arba paprašyk pardavėjo nurodyti vietinį vežėją ir jo sekimo numerį.

### Kaip susisiekti su GZYL dėl dingusios ar vėluojančios siuntos?

Pirmiausia kreipkis į pardavėją arba prekyvietę. Nors yunlianhouyun.com nurodo visą parą veikiančią kinų kalbos liniją, WeChat ir po pardavimo QQ verslo klientams (pardavėjams), GZYL neskelbia jokio klientų aptarnavimo kanalo siuntų gavėjams.

:::sources
- [广州云链货运代理有限公司 (GZYL): Home](http://yunlianhouyun.com/) – company name, Chinese site, parcel search (小包查询) on GZYL's tracker
- [广州云链货运代理有限公司 (GZYL): 联系我们 (contact)](http://yunlianhouyun.com/contact.html) – hotline, WeChat and after-sales QQ
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – company and other names, the tracker on a bare address, YL…YQ number shapes, YL738 named at once and the others a weak match, Xinghe Freight on GZYL's tracker, search by number or reference with its dashes, step wordings and their stages, the platform's Chinese line, towns after wordings, Beijing clock, timeline written in advance, the answer for an unknown number, the tracking page's transfer-number and tracking-number columns, Swiss Post as the only hand-over, what Peek shows, holds back and leaves out
:::
