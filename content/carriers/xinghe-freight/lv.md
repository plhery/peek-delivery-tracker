---
title: Xinghe Freight izsekošana: GZXH…YQ numurs un statusi
description: Seko Xinghe Freight (星和货运) sūtījumam no Ķīnas: GZXH…YQ formāts, kāpēc izmanto GZYL sistēmu, iepriekš ierakstīts Delivered, ES muitas maksas un kontakti.
slug: xinghe-freight-sutijuma-sekosana
published: 2026-11-27
updated: 2026-11-27
---

Xinghe Freight (星和货运, 广州星和货运代理有限公司) ir 2014. gadā Guandžou (Ķīnā) dibināts kravu ekspeditors, kas nodarbojas ar jūras, gaisa un sauszemes pārvadājumiem, kuģu aģentēšanu un muitošanu. Lai izsekotu sūtījumu, ielīmē augstāk esošajā laukā 16 rakstzīmju numuru formātā `GZXH`, desmit cipari un `YQ`. Xinghe Freight tīmekļa vietne izsekošanas pieprasījumus nosūta [GZYL](carrier:gzyl) nešifrētajai HTTP izsekošanas sistēmai, kur soļi — tostarp `Delivered` — var būt ieplānoti iepriekš, nevis faktiski skenēti. Peek izlasa šo sistēmu tavā vietā, nošķir prognozes no reāliem skenējumiem un vajadzības gadījumā pārbauda citus izsekošanas servisus.

## Xinghe Freight izsekošanas numurs: kā tas izskatās

Numuri, ko Peek atpazīst kā Xinghe Freight, sastāv no `GZXH`, desmit cipariem un `YQ` — kopā 16 rakstzīmēm. Ja nejauši izlaid pēdējo burtu `Q` (`GZXH`, desmit cipari un `Y`, 15 rakstzīmes), Peek numuru tik un tā atpazīst un pirms pārbaudes automātiski pievieno `Q` atpakaļ. Piemēri ar izdomātiem cipariem:

| Numurs | Kas tas ir |
| --- | --- |
| `GZXH4829105736YQ` | `GZXH`, desmit cipari un `YQ`: standarta Xinghe Freight formāts. Peek uzreiz izvēlas Xinghe Freight. |
| `GZXH4829105736Y` | `GZXH`, desmit cipari un `Y`: trūkst pēdējā `Q`, bet Peek to atjauno automātiski. |
| `GZXH482910573YQ` | Tikai deviņi cipari: neatbilst Xinghe Freight formātam. Peek uzreiz noraida numuru, lai vari pārbaudīt, vai netrūkst cipara. |
| `YL4829105736YQ` | `YL`, desmit cipari un `YQ`: [GZYL](carrier:gzyl) pavadzīmes formāts. Tā pati sistēma, bet cits ekspeditors. |
| `JXCAA4829105736YQ` | Arī beidzas ar `YQ`, kā daudziem Ķīnas ekspeditoriem, taču sākas ar `JXC`: [Juxi Express](carrier:juxi-express) numurs. |

Atstarpes, domuzīmes un mazie burti, ievadot numuru Peek, nav būtiski.

**Kur atrast numuru.** Piegādi ar Xinghe Freight piesaka pārdevējs, un numuru atradīsi nosūtīšanas e-pastā vai pasūtījuma lapā. Ja numuru neatrodi, jautā pārdevējam.

## Ko nozīmē Xinghe Freight izsekošanas statusi

Xinghe Freight izsekošana darbojas uz GZYL platformas, tāpēc ieraksti parādās GZYL angļu valodas formulējumos ar visām drukas kļūdām. Tā kā Peek lietotnes saskarne nav tulkota latviski, arī Peek galvenie posmi redzami angliski: „Label created“ (izveidota etiķete), „Received by carrier“ (pieņemts pie pārvadātāja), „In transit“ (ceļā), „At customs“ (muitā) un „Delivered“ (piegādāts). Soļi parastajā secībā:

| Sistēma raksta | Ko tas nozīmē tev | Peek rāda |
| --- | --- | --- |
| `Order information received` | Pārdevējs nosūtījis pasūtījuma datus. Fiziski sūtījums vēl nav nodots. | „Label created“ |
| `Arrive at the cross border receiving point` | Ekspeditors saņēmis sūtījumu: pirmais reālais skenējums. | „Received by carrier“ |
| `Goods leave the operation center` | Sūtījums pametis apstrādes centru. | „In transit“ |
| `Arrive at the international airport` | Starptautiskajā lidostā, gaida lidojumu. | „In transit“ |
| `Departing from the airport` | Lidmašīna izlidojusi. | „In transit“ |
| `Item arrives at destination airport` | Ielidojis galamērķa valstī. | „In transit“ |
| `local carrier Agent Import customs clearance` | Vietējais aģents tavā valstī kārto importa muitošanu. | „At customs“ |
| `The import customs keep it for inspection` | Galamērķa valsts muita aizturējusi sūtījumu pārbaudei. | „At customs“ |
| `Customs clearance completed` | Muitošana pabeigta, sūtījums turpina ceļu. | „In transit“ |
| `Internationaliy Package received at processing center` | Apstrādes centrs tavā valstī saņēmis atmuitoto sūtījumu. Tā nav jauna paņemšana no nosūtītāja. | „In transit“ |
| `Leave the email processing center` | Sūtījums pametis šo apstrādes centru. | „In transit“ |
| `Arrival sorting, distribution center` | Ieradies šķirošanas vai sadales centrā tavā valstī, aiz teksta norādīta pilsēta. | „In transit“ |
| `Leaving the sorting center` | Pamet šķirošanas centru, aiz teksta norādīta pilsēta. | „In transit“ |
| `Package is on the way` | Turpina ceļu galamērķa valstī ar norādītu pilsētu. Tas nenozīmē, ka kurjers šodien jau veic piegādi. | „In transit“ |
| `Delivered` | Piegādes atzīme ar pilsētu. Var būt plāns, nevis skenējums: skati zemāk. | „Delivered“ ar piezīmi par fiksētu grafiku, ja Peek šo soli neaiztur (skati zemāk) |

**Caur Honkongu.** Var parādīties arī rinda `香港处理中心已收寄【Hong Kong】`: pasta pieņemšana Honkongas apstrādes centrā, ko sistēma nodod ķīniešu valodā ar pilsētu iekavās. Peek to parāda kā rakstītu, posmā „Received by carrier“.

**Rinda ķīniešu valodā.** Pasūtījumam bez skenējumiem platforma pati ieraksta `货物电子信息已经收到` (saņemta elektroniskā kravas informācija). Pārdevējs izveidojis pasūtījumu, bet paku neviens vēl nav noskenējis. Peek to rāda ķīniešu valodā zem „Label created“.

**Laiki.** Peek visus laikus sistēmā nolasa pēc Pekinas laika (`UTC+8`), ieskaitot soļus Latvijā, un pārveido tavas ierīces laika joslā. Ziemā 20:00 Pekinā atbilst 14:00 Latvijas laikam (`UTC+2`), bet vasarā — 15:00 (`UTC+3`).

**Iepriekš ierakstīti soļi.** Šajā platformā ekspeditori var iepriekš sastādīt visu sūtījuma grafiku no pieņemšanas līdz pēdējam solim tavā valstī, un sistēma katru ierakstu parāda, kad pienāk tā laiks. Šāds ieraksts ir plāns, nevis īsts skenējums. GZYL sistēmā plānu noslēdz `Delivered`, ko platforma pievieno vakara apstrādē norādītajā dienā: datētu nākamajā dienā pēc plāna pēdējā soļa, Pekinas darba laikā neatkarīgi no tavas valsts. Peek šādus soļus aiztur (skati zemāk), taču citu izsekošanas vietņu kopijas tos joprojām var rādīt, tāpēc `Delivered` tur nepierāda, ka paka ir saņemta.

## Kas piegādā Xinghe Freight sūtījumus Latvijā

Peek nezina, kura vietējā pasta vai kurjerpasta kompānija piegādā Xinghe Freight sūtījumus Latvijā. Galamērķa valsts soļos netiek nosaukts neviens uzņēmums, un Xinghe Freight vietnē nav ziņu par tiešsaistes pircēju pakām. Vienīgais vietējais piegādātājs, kuram Peek var automātiski nodot Xinghe sūtījumu, ir [Swiss Post](carrier:swiss-post) Šveicē un Lihtenšteinā, ja tas norādīts ierakstā.

Xinghe Freight vietne nepublicē piegādes termiņus, piegādes mēģinājumu skaitu vai saņemšanas punktus: šos noteikumus nosaka vietējais piegādātājs. Jautā pārdevējam, kura kompānija piegādā pasūtījumu Latvijā, un palūdz tās vietējo izsekošanas numuru.

**Muitas maksas ES.** Kopš 2026. gada 1. jūlija tiešsaistes pirkumiem no valstīm ārpus ES sūtījumos ar vērtību līdz 150 € piemēro fiksētu 3 € muitas nodokli par preces veidu. Kopš 2026. gada 1. novembra papildus piemēro ES apstrādes maksu (Union handling fee) 2 € par preces veidu neatkarīgi no vērtības. Viens veids aptver preces vienā sūtījumā ar vienādu tarifa klasifikāciju, aprakstu un (ja nepieciešams) izcelsmi neatkarīgi no vienību skaita. Plašāk — ceļvedī par [muitā aizturētiem sūtījumiem](guide:customs) (angļu valodā).

## Xinghe Freight paka nepienāk vai izsekošana apstājusies

**Tikai `Order information received` vai `货物电子信息已经收到`.** Pārdevējs izveidojis pasūtījumu, bet sūtījums vēl nav noskenēts. Ja pēc dažām dienām nekas nemainās, pajautā pārdevējam, vai tas tiešām izsūtīts.

**„Waiting for Xinghe Freight“.** Peek vēl nav apstiprinātu skenējumu tavam numuram: GZYL sistēma to nezina vai satur tikai iepriekš ierakstītu plānu (kas var beigties ar `Delivered`), un arī citiem servisiem nav datu. Peek turpina pārbaudīt. Nepazīstamam numuram GZYL sistēma atgriež vienu tukšu rindu ar numuru kā atsauci; Peek to uzskata par neatrastu, rāda „Waiting for Xinghe Freight“ un pēc divām dienām „Check the tracking number“. Savukārt iepriekš ierakstīts plāns apliecina, ka Xinghe pasūtījums ir, tāpēc „Waiting for Xinghe Freight“ paliek bez lūguma pārbaudīt numuru. Tomēr salīdzini numuru rakstzīmi pa rakstzīmei ar pārdevēja ziņu.

**Iestrēdzis muitā.** Ja tev pieprasa importa maksājumus, skati ceļvedi par [sūtījumiem muitā](guide:customs) (angļu valodā). Ja sūtījums stāv dienām ilgi, informē pārdevēju.

**Sistēma vai cita vietne rāda vairāk soļu nekā Peek.** Papildu soļi, tostarp `Delivered`, var būt iepriekš ierakstīts plāns, ko Peek aiztur. Vadies pēc veikala norādītā piegādes termiņa un sazinies ar pārdevēju, kad tas pagājis. Vairāk — ceļvedī par [sūtījumu izsekošanu no Ķīnas](guide:tracking-from-china) (angļu valodā).

**`Delivered`, bet nekas nav saņemts.** Ieraksts var būt daļa no fiksēta grafika. Ja piegādi neveic Swiss Post, Peek zem „Delivered“ rāda piezīmi: „Xinghe Freight updates on a fixed schedule, with no signature. If nothing came, ask the seller.“ Paziņo pārdevējam, ka paka nav saņemta, un pajautā vietējā piegādātāja nosaukumu un numuru. Vairāk — ceļvedī par [piegādātu, bet nesaņemtu sūtījumu](guide:delivered-not-received) (angļu valodā).

**Pazudis vai atgriezts.** Piegādi pie Xinghe Freight pasūtīja pārdevējs, tāpēc par atmaksu vai atkārtotu nosūtīšanu vērsies pie pārdevēja vai tirdzniecības platformas.

## Xinghe Freight kontakti un klientu atbalsts

Xinghe Freight ķīniešu valodas tīmekļa vietnē (`xinghe-express.cn`) norādīts:

- **Diennakts tālrunis (24/7):** 19924483103 (+86 199 2448 3103 no ārzemēm), norādīts kā atvērts 24 stundas diennaktī katru dienu.
- **E-pasts:** 3975605292@qq.com biznesa jautājumiem un cenu piedāvājumiem.
- **Adrese:** Guandžou, Guandunas province, bez ielas (pirms apmeklējuma lūdz sazināties).

Vietne ir tikai ķīniešu valodā un nepiedāvā atbalstu ārvalstu saņēmējiem: kontaktu forma vienkārši atver e-pastu uz šo adresi. Xinghe klients ir pārdevējs, tāpēc vispirms raksti pārdevējam, bet atbildes trūkuma gadījumā — platformai.

## Xinghe Freight sūtījuma izsekošana ar Peek

[Peek](/) uzreiz atpazīst numuru `GZXH…YQ` (arī saīsināto `GZXH…Y`) kā Xinghe Freight ar birku „Detected carrier“. Ja pārdevējs iedeva cita formāta numuru, ielīmē to tik un tā: Peek seko vairāk nekā 4 500 pārvadātājiem. Lai izvēlētos Xinghe Freight manuāli, spied „Change“ un meklē Xinghe Freight (vai `星和货运`), vai izmanto „Change carrier“ jau pievienotai pakai.

Peek tieši jautā GZYL sistēmai, tāpat kā meklēšanas lauks Xinghe vietnē. Tas parāda statusu, katru soli ar vietu un galamērķa valsti, ja tā norādīta. Laiki tiek lasīti Pekinas laikā un rādīti tavas ierīces laika joslā („Times are in your time zone:“ vēstures beigās). Peek neiekļauj vārdus, adresi, pasta indeksus, svaru, maksājumu datus, parakstu un piegādes apliecinājumu, kā arī nerāda paredzamo piegādes datumu, jo sistēma tādu nedod.

Kad sistēma numuru nepazīst, Peek jautā izsekošanas servisiem un rāda to kopiju. Kamēr nevienam nav soļu, redzams „Waiting for Xinghe Freight“, bet pēc divām dienām — „Check the tracking number“.

Peek aiztur jebkuru soli, kas ierakstīts vairāk nekā 12 stundas pirms norādītā laika, jebkuru soli, kas ierakstīts tajā pašā sekundē ar soli, kas ierakstīts vairāk nekā pusstundu pirms sava laika (izņemot pasūtījuma reģistrāciju), un jebkuru soli ar datumu nākotnē pēc Pekinas laika pārbaudes brīdī. Ierakstā ar iepriekš sarakstītiem soļiem tiek aizturēts arī `Delivered`, pat ja ierakstīts tajā pašā dienā. Peek balstās uz paturētajiem soļiem, nevis kopsavilkuma statusu, un izfiltrē šo plānoto soļu kopijas arī izsekošanas servisos (`Delivered` ieskaitot). Ja sistēmai nav ieraksta vai tā neatbild, Peek gan nespēj atšķirt ārēja servisa plānotu soli no īsta skenējuma. Kad prognozes tiek aizturētas, vēsture līdz piegādei vai atgriešanai sākas ar „Other sites may show more steps, but they’re only predictions.“, un „Show them“ tās parāda pelēkas ar dienu („Predicted for Mon 12 Oct“) bez statusa maiņas vai paziņojumiem.

Izņemot Swiss Post, Peek automātiski nenodod izsekošanu vietējam piegādātājam: ja pārdevējs iedod vietējo numuru, pievieno to kā atsevišķu sūtījumu. Kamēr paka ir atvērta vai piegādes dienā ar ieslēgtiem paziņojumiem, Peek pārbauda līdz reizei 10 minūtēs; citādi un pēc klusuma perioda — reizi stundā. Paziņojumus var iestatīt uz „Every scan“, „Important steps“ (divi muitas soļi un `Delivered`, bez lidojumiem un šķirošanas) vai „Delivery only“. Peek nevar tavā vietā sazināties ar Xinghe Freight, GZYL vai pārdevēju.

## Biežāk uzdotie jautājumi par Xinghe Freight

### Kas ir Xinghe Freight (星和货运)?

广州星和货运代理有限公司 (Guangzhou Xinghe Freight Forwarding) ir 2014. gadā Guandžou (Ķīnā) dibināts kravu ekspeditors, kas nodarbojas ar jūras, gaisa un sauszemes kravu pārvadājumiem, kuģu aģentēšanu un muitošanu. Tā sūtījumu numuriem ir 16 rakstzīmes: `GZXH`, desmit cipari un `YQ`.

### Kāpēc mans Xinghe Freight sūtījums rāda GZYL izsekošanu?

Xinghe Freight vietnes meklēšanas lauks pārsūta vaicājumus tieši GZYL sistēmai, tāpēc soļu formulējumi sakrīt ar GZYL. Peek patur sūtījumu zem Xinghe Freight un izlasa šo sistēmu tavā vietā.

### Kas piegādā Xinghe Freight sūtījumus Latvijā?

Ne Xinghe vietne, ne izsekošanas sistēma nenosauc vietējo piegādes partneri Latvijā (Peek automātiski nodod tikai Swiss Post Šveicē un Lihtenšteinā). Pajautā pārdevējam, kura vietējā kompānija piegādā sūtījumu, un palūdz tās izsekošanas numuru.

### Kāpēc Peek rāda mazāk soļu vai nerāda izsekošanu manam GZXH numuram?

Vai nu sistēmā vēl nav ieraksta, vai arī tajā ir iepriekš ierakstīti soļi — tostarp ieplānots `Delivered` — ko Peek paslēpj aiz „Show them“, nevis skaita kā skenējumus. Salīdzini numuru ar pārdevēja e-pastu un raksti pārdevējam, ja vairākas dienas nekas neatjaunojas.

### Kā sazināties ar Xinghe Freight?

Vispirms vērsies pie pārdevēja vai tirdzniecības platformas, jo piegādi pasūtīja pārdevējs. Ķīniešu vietnē (`xinghe-express.cn`) norādīts diennakts tālrunis 19924483103 (+86 199 2448 3103 no ārzemēm) un biznesa e-pasts 3975605292@qq.com, bez atsevišķa atbalsta ārvalstu saņēmējiem.

:::sources
- [Xinghe Freight: 广州星和货运代理有限公司 (sākumlapa, ķīniešu valodā)](http://xinghe-express.cn/) – uzņēmuma nosaukums, ekspedīcijas pakalpojumi, meklēšanas lauks uz GZYL sistēmu, tālrunis un e-pasts
- [Xinghe Freight: 关于我们 (par mums, ķīniešu valodā)](http://xinghe-express.cn/about.html) – dibināts 2014. gadā, jūras, gaisa un sauszemes pārvadājumi, kuģu aģentēšana un muitošana
- [Xinghe Freight: 产品服务 (pakalpojumi, ķīniešu valodā)](http://xinghe-express.cn/services.html) – pakalpojumi, nav ziņu par tiešsaistes pircēju pakām
- [Xinghe Freight: 联系我们 (kontakti, ķīniešu valodā)](http://xinghe-express.cn/contact.html) – 7×24 līnija, biznesa e-pasts, adrese tikai Guandžou, forma atver e-pastu
- [Eiropas Komisija: EU Customs Reform (angļu valodā)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 3 € muitas nodoklis par preces veidu līdz 150 € kopš 2026. gada 1. jūlija, 2 € ES apstrādes maksa kopš 2026. gada 1. novembra
- [Eiropas Komisija: Guidance on the EUR 3 customs duty and the Union handling fee (PDF, angļu valodā)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € maksa neatkarīgi no vērtības un preces veida definīcija
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – nosaukumi, `GZXH…YQ` un saīsinātais `GZXH…Y` formāts, nepareiza garuma noraidīšana, GZYL sistēma, soļu teksti, pieņemšana Honkongā, ķīniešu rinda, Pekinas laiks, iepriekš ierakstīti soļi, rezerves servisi, nodošana Swiss Post un ko Peek rāda, aiztur un izlaiž
:::
