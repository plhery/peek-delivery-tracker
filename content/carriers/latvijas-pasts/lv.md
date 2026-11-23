---
title: Latvijas Pasts sūtījumu izsekošana — numuri, pakomāti
description: Izseko Latvijas Pasts sūtījumus: 13 zīmju LV kodi, statusu skaidrojumi, pakomātu 7 dienu glabāšana, pāradresācija, muita un kontakti +371 27008001.
slug: latvijas-pasts-sutijumu-izsekosana
published: 2026-11-23
updated: 2026-11-23
---

**Latvijas Pasts** ir Latvijas valsts nacionālais pasta operators ar lielāko pakomātu tīklu valstī, kas nodrošina pasta, loģistikas, kurjera un finanšu pakalpojumus Latvijā un ārvalstīs. Sūtījumu vari izsekot portālā `mans.pasts.lv/track` (atveras latviešu valodā ar pārslēgu uz angļu valodu) vai ievadot sūtījuma numuru laukā augstāk. Sūtījuma numuru saņemsi internetveikala vai sūtītāja nosūtīšanas ziņojumā, vai arī kurjera SMS īsziņā pirms piegādes.

## Latvijas Pasts sūtījuma numura formāts

Reģistrētiem un izsekojamiem Latvijas Pasta sūtījumiem tiek piešķirts **13 zīmju** starptautiskais pasta numurs (UPU S10 standarts): divi pakalpojuma burti, deviņi cipari (devītais ir kontrolcipars) un valsts kods `LV`. Piemēri ar izdomātiem cipariem:

| Numurs (izdomāts) | Sūtījuma veids |
| --- | --- |
| `RR482917367LV` | Ierakstīts sūtījums — izsniedz pret parakstu. |
| `LA482917367LV` | Izsekojams sūtījums — reģistrēts katrā posmā, ievieto pastkastītē bez paraksta. |
| `CA482917367LV` | Paka — par saņemšanu saņem aicinājumu pasta nodaļā vai klientu centrā. |
| `EE482917367LV` | Eksprespasta (EMS) sūtījums. |

Vienkāršie (*Simple*) un ekonomiskie (*Economy*) sūtījumi netiek reģistrēti nevienā posmā, tāpēc tos nevar izsekot un bojājuma vai nozaudēšanas gadījumā kompensācija netiek izmaksāta. Ja tev nav izsekošanas numura, iespējams, sūtījums nosūtīts kā vienkāršais sūtījums — skati [how to find a tracking number](guide:find-tracking-number) (angļu valodā). Kurjera SMS ietver sūtījuma numuru, plānoto ierašanās laiku un saiti pāradresācijai, savukārt pakomāta SMS norāda pakomāta adresi, izņemšanas termiņu un durtiņu atvēršanas kodu.

Citi valstu kodi numura beigās apzīmē cita valsts pasta operatoru: `...LT` pieder [Lietuvos paštas](carrier:lietuvos-pastas), bet `...EE` — [Omniva](carrier:omniva). Sūtījums no ārvalstīm saglabā savu ārvalstu numuru, un `mans.pasts.lv/track` parāda arī šos sūtījumus tiklīdz Latvijas Pasts saņem datus vai pašu sūtījumu; ārzemju skenos tad redzama valsts, nevis Latvijas pasta nodaļa. Plašāk par pasta kodiem skati [tracking number formats](guide:tracking-number-formats).

## Ko nozīmē Latvijas Pasts izsekošanas statusi

Izsekošanas sistēma parāda katru posmu ar datumu, laiku un Latvijas pasta nodaļu vai ārvalstu kodu. Virs saraksta redzama piecu soļu josla no `Shipment created` līdz `Delivered to recipient`. Biežākie statusi (angļu valodas formulējumos, kā tos nolasa arī Peek) parastajā secībā:

| Latvijas Pasts statuss | Ko tas nozīmē latviski |
| --- | --- |
| `Electronic data about shipment received` | Sūtītāja elektroniskie dati sasnieguši Latvijas Pastu; fiziskais sūtījums vēl nav nodots. |
| `Item information received` | Sūtītājs reģistrējis sūtījumu sistēmā; Latvijas Pasts to vēl nav saņēmis. |
| `Item received from sender` | Sūtījums pieņemts no sūtītāja Latvijas Pastā. |
| `Item posted / received from customer` | Nodots pasta nodaļā; tāds pats teksts parādās arī ārvalstu pasta pieņemšanas skenā ienākošajiem sūtījumiem. |
| `Item sent to the processing facility` | Izvests no pieņemšanas pasta nodaļas ceļā uz šķirošanu. |
| `Item arrived at office of exchange` | Nonācis starptautiskajā apmaiņas vietā Rīgā vai ārvalstīs. |
| `Item sent to the Customs` | Iesniegts muitai apmaiņas vietā, sūtījumam pametot nosūtītājvalsti. |
| `Item returned from Customs` | Muita pabeigusi pārbaudi un atbrīvojusi sūtījumu tālākai piegādei — tā nav atgriešana sūtītājam! |
| `Item sent abroad` | Pametis apmaiņas vietu ceļā uz galamērķa valsti. |
| `Item sent abroad (EDI-received)` | Ārvalstu pasts nosūtījis sūtījumu uz Latviju; Latvijas Pasts par to uzzinājis no elektroniskajiem datiem. |
| `Item accepted for temporary storage` | Sūtījums no ārvalstīm ievietots pagaidu glabāšanā muitas uzraudzībā līdz atmuitošanai (tas nav pieņemšanas skens no sūtītāja). |
| `Electonic notification (SMS) of customs clearance sent to recipient` | Tev nosūtīta SMS par atmuitošanu (pareizrakstība saglabāta pēc Latvijas Pasta sistēmas); sūtījums vēl atrodas pagaidu glabāšanā. |
| `Customs clearance completed, item to be delivered to recipient` | Atmuitošana pabeigta; sūtījums dodas tālāk uz piegādes nodaļu. |
| `Item arrived at processing facility`, `Item arrived at sorting centre` | Nonācis šķirošanas centrā. |
| `Item sent to next processing facility` | Ceļā uz nākamo šķirošanas centru. |
| `Item sent to delivery facility` | Ceļā uz piegādes nodaļu, kas veiks piegādi vai izsniegšanu. |
| `Item received at delivery office` | Saņemts piegādes nodaļā; no turienes tas vēl var tikt nodots kurjeram vai pastniekam. |
| `Item redirected` | Pāradresēts uz citu saņemšanas vietu vai adresi. |
| `Item address re-labelled` | Pārmarķēts ar jauno piegādes adresi vai saņemšanas punktu. |
| `Item handed over for delivery` | Nodots kurjeram vai pastniekam piegādei šodien. |
| `Unsuccessful delivery attempt` | Neizdevies piegādes mēģinājums. Ja tajā pašā sekundē redzams arī `Item address re-labelled`, tā ir pāradresācijas atzīme, tāpēc Peek rāda **In transit**, nevis **Delivery attempted**. |
| `Notification - pick-up invitation printed` | Gatavs izņemšanai: pasta nodaļa sagatavojusi aicinājumu saņemšanai. |
| `Item arrived at pick-up point` | Ārvalstu pasta skens: sūtījums gaida saņemšanas punktā ārzemēs (norāda valsti, nevis konkrēto punktu). |
| `Item delivered` | Sūtījums piegādāts vai izņemts (arī tad, kad ārzemju pasts piegādā no Latvijas izsūtītu sūtījumu). |

Visbiežāk saņēmējus mulsina `Item returned from Customs` — tas nozīmē tikai to, ka muita atgrieza sūtījumu pasta apstrādē pēc pārbaudes un tas turpina ceļu pie tevis. `Item awaiting next planned cycle` nozīmē, ka sūtījums apmaiņas vietā gaida nākamo plānoto reisu. Par muitas nodokļiem un ES noteikumiem sūtījumiem no trešajām valstīm skati [parcels held at customs](guide:customs).

Ja portāls rāda `No information received on the tracking of the postal item. Please, try again!`, pārbaudi numuru vai pagaidi — sūtījums var vēl nebūt noskenēts, un sūtījumi no ārvalstīm parādās pēc datu saņemšanas no ārzemju pasta. Ziņojums `Postal item number entered incorrectly. Please check it and enter it again.` liecina par pārrakstīšanās kļūdu. Ja pareizs numurs vairākas dienas neparādās, jautā sūtītājam, vai sūtījums tiešām nosūtīts ar izsekošanu.

## Pakomāti, kurjers, pasta nodaļas un pāradresācija

Latvijas Pasta piegādes un saņemšanas nosacījumi (pēc [Klientu atbalsta](https://pasts.lv/en/client-support), [Saņemšanas pakomātā](https://pasts.lv/en/useful/receiving-items-private/receive-in-parcel-locker) un [Pāradresācijas](https://pasts.lv/en/services/private-person/receiving/redirection-of-shipments) lapām):

- **Pakomāti (7 dienas):** Kad sūtījums tiek ievietots pakomātā, tu saņem SMS (un e-pastu, ja sūtītājs norādījis adresi) ar pakomāta adresi, termiņu un **sešciparu kodu**. Sūtījums glabājas līdz **7 dienām**; ja tas vēl ir pakomātā trešajā dienā, kods tiek nosūtīts atkārtoti, un pirms termiņa beigām pienāk atgādinājums. Ja sūtījumu neizņem septiņās dienās, tas uzskatāms par nepiegādātu un atgriežas pie sūtītāja; sūtījums ar ilgāku glabāšanas termiņu tā vietā nonāk tuvākajā Klientu apkalpošanas vietā, kur to glabā **30 dienas**. Ja kods nav saņemts, lūdz sūtītājam sazināties ar Latvijas Pastu — tālruņa numuru labo tikai pēc sūtītāja pieprasījuma. Pakomāta maksimālais svars ir **30 kg** un izmēri **38 × 38 × 58 cm**.
- **Unisend pakomāti Lietuvā un Igaunijā:** No Latvijas nosūtīts sūtījums uz Unisend pakomātu Lietuvā vai Igaunijā arī glabājas **7 dienas** (kods pienāk SMS un e-pastā uz Latvijas, Lietuvas vai Igaunijas numuru un atkārtoti pēc 48 stundām). Neizņemts sūtījums uzreiz atgriežas pie sūtītāja.
- **Kurjera piegāde uz adresi:** Latvijas Pasts veic līdz **trim piegādes mēģinājumiem**. Ja piegāde neizdodas un sūtījums nav pāradresēts, kurjers atstāj paziņojumu un mēģina vēlreiz; pēc neveiksmīgiem mēģinājumiem sūtījums nonāk glabāšanā (to var izņemt galvenajā pasta nodaļā vai sazināties ar atbalstu par citu variantu; glabāšanas termiņu nosaka sūtītājs). Ja EMS sūtījumu neizdodas piegādāt, pastkastītē tiek atstāts paziņojums par sūtījumu: zvani uz tajā norādīto tālruni, lai pieteiktu atkārtotu piegādi, un turi pa rokai sūtījuma numuru.
- **Pasta nodaļa vai Klientu centrs:** Paziņojumu saņem SMS vai papīra formā ar konkrētās nodaļas nosaukumu. Līdzi jāņem personu apliecinošs dokuments (sūtījumiem līdz € 150 der arī autovadītāja apliecība). Vari saņemt arī bez papīra aicinājuma, nosaucot sūtījuma numuru, vai zvanīt uz paziņojumā norādīto numuru, lai pasūtītu piegādi uz adresi par maksu. Cita persona var izņemt ar tavu pilnvarojumu un savu pasi vai ID karti. Ierakstītiem un apdrošinātiem sūtījumiem pēc 5 dienām pienāk otrais aicinājums, bet pēc **30 dienām** no pirmā aicinājuma sūtījums atgriežas sūtītājam. Ja ārzemju internetveikala sūtījumam uz uzlīmes norādīta tikai daļa vārda, līdzi ņem pasūtījuma apliecinājumu un personu apliecinošu dokumentu.
- **Sūtījuma pāradresācija:** Ja sūtītājs norādījis tavu tālruni vai esi pieteicies SMS paziņojumiem, īsziņā esošā saite ļauj pāradresēt sūtījumu uz pakomātu (**bez maksas**), Klientu apkalpošanas vietu (€ 1,89 ar PVN), kurjeru uz citu adresi (€ 4,99 ar PVN) vai bezmaksas pakalpojumu „Pastnieks mājās“, kur tas pieejams. Nevar pāradresēt jau pakomātā ievietotus sūtījumus, muitas uzraudzībā esošus sūtījumus, kā arī uz pakomātu nosūtīt ierakstītus/apdrošinātus sūtījumus ar izsniegšanas apstiprinājumu vai pēcmaksas pakas, kas pārsniedz 38 × 38 × 58 cm. Piesakoties SMS paziņojumiem, vari norādīt arī savu iecienītāko pakomātu.

## Nozaudēts, bojāts sūtījums un brīdinājums par krāpniekiem

Pretenziju par nozaudētu vai bojātu pakomāta sūtījumu iesniedz **sūtītājs**, tāpēc pirkumiem internetā vispirms raksti veikalam. Pretenzijā norāda saturu un vērtību, pievienojot čeku, rēķinu vai maksājuma apstiprinājumu un bojājumu fotogrāfijas. Latvijas Pasts pretenzijas izskata **viena mēneša** laikā.

Saņemot ierakstītu vai apdrošinātu sūtījumu no kurjera, pārbaudi saturu kurjera klātbūtnē, lai parakstītu aktu par bojājumiem. Ja atver sūtījumu viens un konstatē bojājumu, nofotografē to un vērsies pie sūtītāja pēc nomaiņas vai atmaksas — Latvijas Pasts norāda, ka šādā gadījumā kompensācija nav pasta atbildība. Ārvalstīs nosūtītam sūtījumam sūtītājs var pieteikt bezmaksas starptautisko meklēšanu, kad pagājušas 30 dienas kopš nosūtīšanas. Ja izsekošana ilgstoši neatjaunojas, skati [tracking that isn't updating](guide:tracking-not-updating) un [parcels held at customs](guide:customs).

> Latvijas Pasts brīdina par krāpnieciskām SMS un e-pastiem, kuros lūdz apstiprināt sūtījuma datus, adresi vai samaksāt muitu viltotās vietnēs. Oficiālie e-pasti vienmēr beidzas ar `@pasts.lv`, oficiālā vietne ir `www.pasts.lv`, un Latvijas Pasts nekad neprasa bankas vai kartes datus SMS vai e-pastā, kā arī neprasa Smart-ID apstiprinājumu ([delivery scam texts](guide:delivery-scam-texts)).

## Latvijas Pasts izsekošana lietotnē Peek

Ievieto `...LV` pasta numuru ar derīgu kontrolciparu (sākas ar `C`, `E`, `L` vai `R`) vai saiti `mans.pasts.lv/track` lietotnē Peek, un Peek nekavējoties atpazīs **Latvijas Pasts**. Citiem derīgiem `...LV` numuriem (piemēram, `VV...LV`) Peek vienlaikus pārbauda Latvijas Pastu un [Chronopost](carrier:chronopost). Ja kontrolcipars ir kļūdains, Latvijas Pasts netiek vaicāts — salīdzini numuru ar sūtītāja ziņu. Lai izvēlētos pārvadātāju manuāli (arī meklējot pēc „Latvian Post“), izmanto **Change** pie pievienošanas vai **Change carrier** sūtījuma kartītē (Peek lietotnes saskarne ir angļu valodā).

Ienākošiem ārzemju sūtījumiem Peek vispirms seko sūtītājvalsts pastā. Kad tas norāda galamērķi Latvijā (vai, ja tas galamērķi nenorāda, tu atrodies Latvijā), Peek ar to pašu numuru vaicā arī Latvijas Pastam un automātiski pārslēdzas, tiklīdz parādās jaunāks Latvijas Pasta skens, saglabājot visu iepriekšējo vēsturi.

Peek saņem vēsturi caur atvērtā pirmkoda [universal-parcel-scraper Latvijas Pasts adapteri](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/latvijas-pasts). Latvijas Pasta laikiem nav laika joslas: Peek tos nolasa pēc skenēšanas vietas pulksteņa (Latvijas laiks Latvijas nodaļām) un rāda tavas ierīces laikā. Statusi saglabā Latvijas Pasta angļu formulējumus, un Peek var parādīt pasta nodaļu, kurā sūtījums gaida izņemšanu; paredzamo piegādes dienu un svaru tas nerāda.

Peek sakārto statusus pārskatāmos posmos: **Label created** (elektroniskie dati saņemti), **Received by carrier** (pieņemts no sūtītāja), **In transit** (šķirošana, apmaiņas vieta, muitas atbrīvošana un pāradresācija), **At customs** (muitas kontrole un pagaidu glabāšana), **Out for delivery** (nodots piegādei), **Delivery attempted** (neizdevies mēģinājums), **Ready for pickup** (gaida nodaļā vai saņemšanas punktā) un **Delivered** (piegādāts).

Ja sūtījumam statusā „Label created“, „Received by carrier“ vai „In transit“ četras dienas nav jauna skena, Peek to atzīmē ar pēdējā skena dienu, piemēram, „No tracking update since Mon 12 Oct“, pievieno piezīmi „Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking Latvijas Pasts.“ un saiti „Get help from Latvijas Pasts“ uz Latvijas Pasta klientu atbalsta lapu. Pēc trim nedēļām bez jauna skena piezīme ir „Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking Latvijas Pasts.“

Kamēr sūtījums ir atvērts Peek vai piegādes dienā, ja paziņojumi ir ieslēgti, Peek to pārbauda ne biežāk kā reizi 10 minūtēs, bet tajā dienā, kad Latvijas Pasts rāda `Item handed over for delivery`, no plkst. 6.00 pēc šī skena vietas pulksteņa (Latvijā — pēc Latvijas laika) ne biežāk kā reizi 2 minūtēs, kamēr atbild paša Latvijas Pasta izsekošana; citādi reizi stundā. Ja divas dienas nav jaunumu (vai 12 stundas, kamēr sūtījums ir tikai reģistrēts vai pārvadātāja pieņemts), Peek to pārbauda reizi stundā, bet ja sūtījums septiņas dienas bez jaunumiem ir statusā „Label created“, reizi 6 stundās. Ja Latvijas Pasta izsekošana neatbild un Peek seko sūtījumam tikai caur izsekošanas pakalpojumiem, tas pārbauda reizi stundā, pēc dienas bez jaunumiem reizi 3 stundās, bet dienā, kad sūtījums nodots piegādei, ne biežāk kā reizi 15 minūtēs. Ja piegādi pārņem cits pārvadātājs, Peek seko tā pārvadātāja ritmam, kas sūtījumu piegādā. Paziņojumiem var izvēlēties „Every scan“, „Important steps“ vai „Delivery only“.

## Latvijas Pasts klientu atbalsts un kontakti

Oficiālie saziņas kanāli ([Kontakti](https://pasts.lv/en/about-us/contacts) un [Klientu atbalsts](https://pasts.lv/en/client-support)):

- **Tālrunis:** `+371 27008001`
- **E-pasts:** `info@pasts.lv`
- **Darba laiks:** pirmdiena–piektdiena `8:00–19:00`, sestdiena `9:00–17:00`, svētdiena — slēgts
- **Pieteikt problēmu vai pretenziju:** tiešsaistes forma `mans.pasts.lv/complaint` (apstrādā klientu atbalsta darba laikā)

## Biežāk uzdotie jautājumi

### Cik ilgi sūtījums glabājas Latvijas Pasta pakomātā?

Pakomātā sūtījums gaida līdz **7 dienām**; ja tas vēl ir tur trešajā dienā, sešciparu kods tiek nosūtīts vēlreiz, un pirms termiņa beigām pienāk atgādinājums. Ja to neizņem, tas atgriežas pie sūtītāja; sūtījums ar ilgāku glabāšanas termiņu nonāk tuvākajā Klientu apkalpošanas vietā, kur to glabā 30 dienas.

### Vai „Item returned from Customs“ nozīmē, ka sūtījums tiek atgriezts sūtītājam?

Nē. Tas nozīmē, ka muita pabeigusi pārbaudi un nodevusi sūtījumu atpakaļ Latvijas Pastam tālākai piegādei tev.

### Vai Unisend ir Latvijas Pasts?

Nē. Unisend ir Lietuvas pasta ([Lietuvos paštas](carrier:lietuvos-pastas)) paku zīmols. Abi uzņēmumi uztur kopīgu pakomātu tīklu, tāpēc Latvijas Pasta klienti var sūtīt uz Unisend pakomātiem Lietuvā un Igaunijā.

### Vai vienkāršajiem sūtījumiem no Latvijas ir izsekošanas numurs?

Ne vienmēr. Vienkāršie (*Simple*) un ekonomiskie (*Economy*) sūtījumi netiek reģistrēti un nav izsekojami; izsekošana ir pieejama ierakstītiem (`R`), izsekojamiem (`L`), paku (`C`) un EMS (`E`) sūtījumiem.

:::sources
- [Latvijas Pasts — Klientu atbalsts](https://pasts.lv/en/client-support)
- [Latvijas Pasts — Kontakti](https://pasts.lv/en/about-us/contacts)
- [Latvijas Pasts — Saņemšana pakomātā](https://pasts.lv/en/useful/receiving-items-private/receive-in-parcel-locker)
- [Latvijas Pasts — Pakomātu pakalpojumu noteikumi](https://pasts.lv/media/789/download/Pakomatu_noteikumi.pdf)
- [Latvijas Pasts — Sūtījumu pāradresācija](https://pasts.lv/en/services/private-person/receiving/redirection-of-shipments)
- [Latvijas Pasts adapteris universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/latvijas-pasts)
:::
