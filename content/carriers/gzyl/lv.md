---
title: GZYL sūtījuma izsekošana: YL…YQ numurs un statusi
description: Izseko GZYL (广州云链, Guangzhou Yunlian) sūtījumu: YL…YQ formāts, statusu nozīmes, kāpēc Delivered var būt ieplānots iepriekš, Swiss Post un kontakti.
slug: gzyl-sutijuma-sekosana
published: 2026-11-27
updated: 2026-11-27
---

GZYL (广州云链, Guangzhou Yunlian, 广州云链货运代理有限公司) ir kravu ekspeditors no Guandžou, kura pavadzīmes atbilst formātam `YL`, desmit cipari un `YQ`. Ielīmē savu `YL…YQ` numuru augstāk esošajā laukā, lai izsekotu sūtījumu un atdalītu iepriekš ieplānotās prognozes no reāliem skenējumiem: GZYL izsekošanas sistēma (sasniedzama no yunlianhouyun.com) var ierakstīt sūtījuma ceļu jau iepriekš, tāpēc tajā redzamais `Delivered` ne vienmēr ir īsts piegādes skenējums.

## GZYL izsekošanas numurs: kā tas izskatās

GZYL pavadzīmes veido `YL`, desmit cipari un `YQ` — kopā 14 rakstzīmes. Ar izdomātiem cipariem:

| Numurs | Kas tas ir |
| --- | --- |
| `YL4829105736YQ` | `YL`, desmit cipari un `YQ`: standarta GZYL pavadzīmes formāts. Peek to pārbauda GZYL izsekošanas sistēmā. |
| `YL7384829105YQ` | `YL738`, septiņi cipari un `YQ`: pēc šī formāta Peek uzreiz atpazīst GZYL. |
| `YL482910573YQ` | Tikai deviņi cipari: tas neatbilst GZYL formātam. Pārbaudi, vai nav izlaists cipars. |
| `GZXH4829105736YQ` | `GZXH`, desmit cipari un `YQ`: Xinghe Freight (星和货运), cits ekspeditors. Tā izsekošana darbojas GZYL sistēmā ar GZYL formulējumiem. |
| `JXCAA4829105736YQ` | Arī beidzas ar `YQ`, kā daudzu Ķīnas ekspeditoru numuri, bet sākas ar `JXC`: tas ir [Juxi Express](carrier:juxi-express) numurs. |

Ielīmējot numuru Peek, atstarpēm un mazajiem burtiem nav nozīmes.

**Kur atrast numuru.** Pārdevējs noformē sūtījumu caur GZYL un nodod numuru pircējam. Ja nevari to atrast, skati [kā atrast sūtījuma izsekošanas numuru](guide:find-tracking-number) (angļu valodā).

## GZYL izsekošanas statusu skaidrojums

GZYL savus soļus raksta angļu valodā, ieskaitot sastopamās drukas kļūdas. Secībā, kādā sūtījums tos parasti saņem:

| Ko raksta GZYL | Ko tas nozīmē tev | Ko rāda Peek |
| --- | --- | --- |
| `Order information received` | Pārdevējs nosūtīja GZYL pasūtījuma datus. GZYL vēl nav saņēmis pašu sūtījumu. | "Label created" |
| `Arrive at the cross border receiving point` | GZYL ir saņēmis sūtījumu: pirmais fiziskais skenējums tā rokās. | "Received by carrier" |
| `Goods leave the operation center` | Sūtījums izbraucis no GZYL operāciju centra. | "In transit" |
| `Arrive at the international airport` | Starptautiskajā lidostā, gaida savu reisu. | "In transit" |
| `Departing from the airport` | Reiss ir izlidojis. | "In transit" |
| `Item arrives at destination airport` | Ielidojis galamērķa valstī. | "In transit" |
| `local carrier Agent Import customs clearance` | Vietējais aģents tavā valstī veic importa muitošanu. | "At customs" |
| `The import customs keep it for inspection` | Tavas valsts muita pārbauda sūtījumu. | "At customs" |
| `Customs clearance completed` | Muitošana pabeigta, sūtījums turpina ceļu. | "In transit" |
| `Internationaliy Package received at processing center` | Apstrādes centrs tavā valstī ir pieņēmis atmuitoto sūtījumu. Tā nav jauna nodošana. | "In transit" |
| `Leave the email processing center` | Sūtījums pametis šo apstrādes centru. | "In transit" |
| `Arrival sorting, distribution center` | Šķirošanas jeb sadales centrā tavā valstī, aiz teksta norādot pilsētu. | "In transit" |
| `Leaving the sorting center` | Izbrauc no šķirošanas centra, norādot pilsētu. | "In transit" |
| `Package is on the way` | Pārvietojas tavā valstī, ar pilsētu. Tas nenozīmē, ka kurjers jau ved piegādei. | "In transit" |
| `Delivered` | Piegādes ieraksts ar pilsētu. Tas var būt plāns, nevis skenējums (sk. zemāk). | "Delivered" ar piezīmi par fiksētu grafiku, ja vien Peek to neaiztur (sk. zemāk) |

**Caur ASV.** Sūtījums var parādīt arī `Los Angeles Processing Center has received it` ar pilsētu iekavās: apstrādes centrs ASV ir pieņēmis to ceļā uz galamērķi. Peek rāda "In transit".

**Rinda ķīniešu valodā.** Pasūtījumam bez skenējumiem platforma, uz kuras darbojas GZYL izsekošana, parāda vienu rindu `货物电子信息已经收到` („preču elektroniskā informācija saņemta“). Pārdevējs ir izveidojis pasūtījumu, taču sūtījumu vēl neviens nav noskenējis. Peek parāda šo rindu ķīniešu valodā ar statusu "Label created".

**Caur China Post.** Var parādīties arī `广州处理中心已收寄` ar pilsētu iekavās: tas ir China Post pieņemšanas ieraksts Guandžou apstrādes centrā, ko GZYL pārsūta ķīniešu valodā. Xinghe Freight numuriem `香港处理中心已收寄` ir tāda pati pieņemšana Honkongas apstrādes centrā. Peek abas rindas parāda ķīniešu valodā ar statusu "Received by carrier".

**Laiki.** Visi laiki GZYL izsekošanas sistēmā ir pēc Pekinas pulksteņa, ieskaitot soļus tavā valstī, nenorādot laika joslu. Ziemā 20:00 Pekinā atbilst pusdienlaikam Londonā un 7:00 Ņujorkā.

**Iepriekš ierakstīti soļi.** GZYL var ierakstīt visu sūtījuma gaitu jau iepriekš — no pieņemšanas līdz pēdējam solim tavā valstī — krietni pirms soļos norādītā laika, un izsekošanas lapa katru no tiem parāda brīdī, kad pienāk tā laiks. Šādi ierakstīts solis ir plāns, nevis tava sūtījuma skenējums. [Meiteshun](carrier:mtscn), kas izmanto to pašu izsekošanas platformu, rīkojas tāpat. Pēc tam GZYL plāns noslēdzas ar `Delivered`, ko platforma pievieno vakara paketē tajā pašā dienā: datētu nākamajā dienā pēc plāna pēdējā soļa, Pekinas darba laikā, neatkarīgi no tavas valsts. Arī citi izsekošanas servisi, kas kopē GZYL datus, var rādīt šīs rindas, tāpēc `Delivered` GZYL lapā vai citā servisā nepierāda, ka sūtījums tiešām piegādāts.

## Kas piegādā GZYL sūtījumus

Peek nezina, kura vietējā kompānija piegādā GZYL sūtījumus vairumā valstu (tostarp ASV, Apvienotajā Karalistē, Īrijā, Kanādā, Austrālijā vai Latvijā): GZYL soļi tavā valstī nenosauc nevienu uzņēmumu. Vienīgais piegādātājs, kuram Peek var nodot GZYL sūtījumu tālāk, ir [Swiss Post](carrier:swiss-post) Šveicē un Lihtenšteinā.

Peek neatrada GZYL publicētus piegādes termiņus, piegādes mēģinājumu noteikumus, saņemšanas punktus vai glabāšanas ilgumu — tos nosaka galīgā piegādātāja kompānija. GZYL izsekošanas lapā apskati kolonnu `渠道转单号` (nodošanas numurs) un kolonnu `跟踪号码` (izsekošanas numurs), ja tajā ir kas cits nekā tavs `YL…YQ` numurs: jebkurš no tiem var būt vietējā piegādātāja numurs, ko vari pievienot Peek kā atsevišķu sūtījumu. Ja tāda nav, pajautā pārdevējam, kura kompānija piegādā tavu pasūtījumu, un palūdz tās izsekošanas numuru.

## GZYL sūtījums nepienāk vai izsekošana iestrēgusi

**Tikai `Order information received` vai `货物电子信息已经收到`.** Pārdevējs ir izveidojis pasūtījumu, bet GZYL vēl nav noskenējis sūtījumu. Ja pēc dažām dienām nekas neseko, pajautā pārdevējam, vai sūtījums patiešām ir izsūtīts.

**Iestrēdzis muitā.** Ja tev pieprasa importa maksājumus, skati [muitā aizturēti sūtījumi](guide:customs) (angļu valodā). Ja sūtījums muitā paliek vairākas dienas bez izmaiņām, ziņo pārdevējam.

**GZYL izsekošanas lapa vai cits serviss rāda vairāk nekā Peek.** Peek aiztur soļus, kas ierakstīti pirms to parādītā laika, GZYL plānu noslēdzošo `Delivered` un soļus ar datumu pēc pēdējās pārbaudes (sk. zemāk), tāpēc papildu rindas var būt GZYL plāns, nevis skenējumi. Iepriekš GZYL ierakstīts solis nekad netiek skaitīts kā skenējums: tas nemaina statusu vai prognozēto datumu un nesūta paziņojumu. Kad Peek ir aizturējis iepriekš ierakstītus soļus, izsekošanas vēsture sākas ar rindu (līdz piegādei vai atgriešanai): "Other sites may show more steps, but they’re only predictions." Poga "Show them" aiz tās parāda aizturētos soļus pelēkā krāsā, sākot ar jaunāko un norādot GZYL doto dienu ("Predicted for Mon 12 Oct"). Vadies pēc veikala norādītā piegādes termiņa un sazinies ar pārdevēju, kad tas ir pagājis. Plašāk — [kāpēc izsekošana neatjaunojas](guide:tracking-not-updating) (angļu valodā).

**`Delivered`, bet nekas nav pienācis.** Šis solis var būt GZYL plāna daļa, nevis reāls skenējums. Ja vien sūtījumu nepiegādā Swiss Post, Peek zem "Delivered" parāda brīdinājumu: "GZYL updates on a fixed schedule, with no signature. If nothing came, ask the seller." Paziņo pārdevējam, ka sūtījums nav saņemts, un pajautā, kura kompānija to piegādā un kāds ir tās numurs. Plašāk — [atzīmēts kā piegādāts, bet nav saņemts](guide:delivered-not-received) (angļu valodā).

**Nekas netika atrasts.** Nezināmam numuram GZYL izsekošanas sistēma parāda vienu rezultātu ar tavu numuru laukā `参考号` (atsauce) un visiem pārējiem laukiem tukšiem. Peek šādu atbildi uztver kā neatrastu: rāda "Waiting for GZYL", bet pēc divām dienām — "Check the tracking number". Salīdzini numuru ar pārdevēja atsūtīto ziņu.

**Pazudis vai atgriezts.** Sūtījumu pie GZYL pasūtīja pārdevējs, tāpēc par naudas atmaksu vai jaunu sūtījumu vērsies pie pārdevēja vai tirdzniecības platformas.

## GZYL kontakti un klientu atbalsts

Pēc 2026. gada 10. novembra datiem GZYL mājaslapas kontaktu sadaļā (`联系我们`, ķīniešu valodā) ir norādīts diennakts tālrunis (`全国24小时服务热线`), WeChat konts un pēcpārdošanas QQ konts (`售后QQ`), taču nekas saņēmējiem: šie kanāli apkalpo GZYL klientus — pārdevējus. Sūtījumu pasūtīja pārdevējs, tāpēc par savu pasūtījumu jājautā pārdevējam vai tirdzniecības platformai, ja pārdevējs neatbild.

## GZYL sūtījuma izsekošana ar Peek

[Peek](/) uzreiz nosauc GZYL numuriem, kas sākas ar `YL738`, septiņiem papildu cipariem un `YQ` ("Detected carrier"). Citi `YL`, desmit ciparu un `YQ` numuri ir tikai vāja atbilstība. Ielīmē savējo tik un tā: Peek seko vairāk nekā 4 500 pārvadātājiem. Pievienojot numuru, Peek uzdod vaicājumu GZYL sistēmai ("Checking tracking services…") un parāda "GZYL has this parcel.", kad sistēma atgriež rezultātu šim numuram — pat tukšo ierakstu, ko tā rāda nezināmam numuram. Pretējā gadījumā nospied "Change" un izvēlies GZYL sadaļā "Fits this number" (arī ierakstot `广州云链` to var atrast) vai izmanto "Change carrier" jau pievienotam sūtījumam. Darbojas arī pārdevēja atsauces numurs, bet tikai pēc tam, kad pats izvēlies GZYL ar "Change" (vai "Change carrier"): tad Peek parāda paša pasūtījuma numuru ar norādi "GZYL tracks this as". Taču Peek noņem domuzīmes, tāpēc atsauce ar domuzīmēm netiek atrasta — palūdz pārdevējam `YL…YQ` numuru.

Peek nolasa pašu GZYL izsekošanas sistēmu un parāda statusu, katru soli ar tā vietu un galamērķa valsti, ja GZYL ieraksts to satur. Laiki tiek lasīti pēc Pekinas laika un rādīti tavas ierīces laika joslā; vietnē peektracker.com un iPhone un Android lietotnēs vēsture noslēdzas ar "Times are in your time zone:" un tās nosaukumu. Peek neiekļauj vārdus, adresi, pasta indeksus, svaru, maksas, parakstu un piegādes apliecinājumu, kas var būt GZYL ierakstā, un nerāda paredzamo piegādes datumu, jo GZYL tādu nesniedz.

Peek aiztur jebkuru soli, kas ierakstīts vairāk nekā divpadsmit stundas pirms tajā norādītā laika, jebkuru soli, kas ierakstīts tajā pašā sekundē kā solis, kas izveidots vairāk nekā pusstundu pirms sava laika (izņemot pasūtījuma reģistrāciju), un jebkuru soli, kas datēts vēlāk par pārbaudes brīdi pēc Pekinas laika. Pasūtījumam ar iepriekš ierakstītiem soļiem tiek aizturēts arī `Delivered`, pat ja tas ierakstīts savā dienā. Kad datu saskarnē numuram nav soļu, Peek tā vietā nolasa GZYL izsekošanas lapu; šī lapa nerāda soļa ierakstīšanas laiku, tāpēc tur Peek aiztur katru `Delivered`. Statuss tiek noteikts pēc paturētajiem soļiem, nevis pēc ieraksta kopsavilkuma statusa. Pasūtījumam, kura visi soļi ir aizturēti, Peek sistēmā vēl nav vēstures: Peek rāda "Waiting for GZYL" bez aicinājuma pārbaudīt numuru, jo iepriekš ierakstītus soļus uzskata par apliecinājumu, ka pasūtījums ir pie GZYL (tāpat kā sūtījumu bez skenējumiem), un turpina pārbaudīt GZYL sistēmu. Peek atliek malā arī citu izsekošanas servisu kopētos aizturētos GZYL plāna soļus, ieskaitot `Delivered`, pat tad, ja citi pasūtījuma soļi tiek saglabāti. Tomēr, kad GZYL sistēma nav sasniedzama, Peek jautā izsekošanas servisiem, un to kopijās var būt iepriekš ierakstītie GZYL soļi.

Izņemot nodošanu Swiss Post, Peek paliek pie GZYL: ja pārdevējs iedod vietējā piegādātāja numuru, pievieno to kā atsevišķu sūtījumu. Kamēr sūtījums tev ir atvērts vai tā piegādes dienā ar ieslēgtiem paziņojumiem, Peek pārbauda līdz reizei 10 minūtēs un līdz reizei 2 minūtēs dienā, kad sūtījums rāda "Out for delivery" (no plkst. 6:00 vietējā laika vai pusstundu pirms pārvadātāja norādītā laika loga), kamēr atbild GZYL vai Swiss Post sistēma, lai gan neviens Peek zināms GZYL solis šo statusu nedod. Citādi pārbaudes notiek reizi stundā. Caur izsekošanas servisiem — reizi stundā, pēc klusas dienas ik pēc 3 stundām un piegādes dienā ar tādiem pašiem nosacījumiem līdz reizei 15 minūtēs. Kad sūtījumu pārņem Swiss Post, Peek seko tās ritmam. Pēc divām dienām bez jaunumiem (12 stundām, kamēr statuss ir "Label created" vai "Received by carrier") Peek pārbauda reizi stundā. Tas var paziņot par katru skenējumu, tikai svarīgiem soļiem vai tikai piegādi: "Important steps" ietver abus GZYL muitas soļus un `Delivered`, nevis lidojumus vai šķirošanu. Peek nevar sazināties ar GZYL vai pārdevēju tavā vietā.

## Jautājumi par GZYL izsekošanu

### Kas ir GZYL (广州云链)?

GZYL ir 广州云链货运代理有限公司 (Guangzhou Yunlian) — kravu ekspeditors Guandžou, Ķīnā, ar tīmekļa vietni ķīniešu valodā yunlianhouyun.com. Tā pavadzīmēm ir 14 rakstzīmju formāts `YL`, desmit cipari un `YQ`.

### Vai katrs YL…YQ izsekošanas numurs pieder GZYL?

Ne automātiski. `YL`, desmit cipari un `YQ` ir GZYL formāts — numuriem `YL738…YQ` Peek uzreiz nosauc GZYL, bet pārējos `YL…YQ` kodus pārbauda GZYL izsekošanas sistēmā. Deviņi vai vienpadsmit cipari neatbilst GZYL, savukārt `GZXH…YQ` pieder Xinghe Freight.

### Kāpēc GZYL rāda „Delivered“, ja nekas nav pienācis?

GZYL bieži ieraksta lielu daļu sūtījuma gaitas jau iepriekš un pievieno `Delivered` pēc fiksēta grafika, nevis no reāla vietējā piegādes skenējuma. Peek aiztur prognozētos soļus un brīdina par fiksēto GZYL grafiku — ziņo pārdevējam, ka sūtījums nav saņemts, un pajautā, kura kompānija to piegādā.

### Kas piegādā GZYL sūtījumus manā valstī?

GZYL skenējumos nekad nav minēts vietējais piegādātājs (tikai Šveicē un Lihtenšteinā Peek var nodot sūtījumu Swiss Post). Pārbaudi kolonnu `渠道转单号` (nodošanas numurs) GZYL lapā vai pajautā pārdevējam vietējo pārvadātāju un tā izsekošanas numuru.

### Kā sazināties ar GZYL par pazudušu vai aizkavētu sūtījumu?

Vispirms vērsies pie pārdevēja vai tirdzniecības platformas. Lai gan yunlianhouyun.com norāda diennakts tālruni ķīniešu valodā, WeChat un pēcpārdošanas QQ biznesa klientiem (pārdevējiem), GZYL nepublicē nevienu atbalsta kanālu sūtījumu saņēmējiem.

:::sources
- [广州云链货运代理有限公司 (GZYL): Home](http://yunlianhouyun.com/) – company name, Chinese site, parcel search (小包查询) on GZYL's tracker
- [广州云链货运代理有限公司 (GZYL): 联系我们 (contact)](http://yunlianhouyun.com/contact.html) – hotline, WeChat and after-sales QQ
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – company and other names, the tracker on a bare address, YL…YQ number shapes, YL738 named at once and the others a weak match, Xinghe Freight on GZYL's tracker, search by number or reference with its dashes, step wordings and their stages, the platform's Chinese line, towns after wordings, Beijing clock, timeline written in advance, the answer for an unknown number, the tracking page's transfer-number and tracking-number columns, Swiss Post as the only hand-over, what Peek shows, holds back and leaves out
:::
