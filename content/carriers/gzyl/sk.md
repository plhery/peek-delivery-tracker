---
title: Sledovanie zásielky GZYL z Číny: číslo YL…YQ a stavy
description: Ako sledovať balík z Číny poslaný cez GZYL (广州云链): ako vyzerá číslo YL…YQ, čo znamenajú stavy a prečo sledovanie môže ukázať Delivered, hoci balík neprišiel.
slug: gzyl-sledovanie-zasielky
published: 2026-11-03
updated: 2026-11-03
---

GZYL (广州云链, Guangzhou Yunlian) je 广州云链货运代理有限公司, špedičná firma z mesta Guangzhou (Kanton) v Číne. Ak vám čínsky e-shop poslal objednávku cez GZYL, číslo zásielky má tvar YL, desať číslic a YQ: vložte ho do poľa vyššie. Webová stránka GZYL, yunlianhouyun.com, je po čínsky a jej vyhľadávanie zásielok (小包查询) otvára sledovanie, ktoré beží na IP adrese bez doménového mena. Údaje v ňom berte s rezervou: GZYL zapisuje veľkú časť cesty balíka vopred, takže krok, ktorý tam uvidíte, nemusí byť skutočný sken, a to ani `Delivered`.

## Číslo zásielky GZYL: ako vyzerá

Čísla zásielok GZYL majú tvar YL, desať číslic a YQ, spolu 14 znakov. Príklady s vymyslenými číslicami:

| Číslo (vymyslené) | Čo to je |
| --- | --- |
| `YL0000000000YQ` | YL, desať číslic a YQ: tvar čísel GZYL. Peek ho overí v sledovaní GZYL. |
| `YL000000000YQ` | Len deväť číslic: to nie je tvar GZYL. Skontrolujte, či nechýba číslica. |
| `GZXH0000000000YQ` | GZXH, desať číslic a YQ: Xinghe Freight (星和货运), iná špedičná firma. Jej zásielky ukazuje sledovanie GZYL, s rovnakým znením krokov. |
| `JXCAA0000000000YQ` | Aj toto číslo končí na YQ, ako čísla mnohých čínskych špeditérov, ale začína sa na JXC: je to číslo Juxi Express. |

Medzery ani malé písmená pri vkladaní čísla do Peeku nevadia.

**Kde ho nájdete.** Predajca si u GZYL objedná prepravu a číslo vám pošle. Ak ho nemáte, vypýtajte si ho od predajcu.

## Čo znamenajú stavy zásielky GZYL

GZYL píše svoje kroky po anglicky, aj s preklepmi. Peek ich ukáže v pôvodnom znení a zásielke navyše priradí vlastný stav. Aplikácia Peek nie je v slovenčine, takže aj tento stav uvidíte po anglicky: „Label created“ (štítok vytvorený), „Received by carrier“ (prevzatá prepravcom), „In transit“ (na ceste), „At customs“ (na colnici) alebo „Delivered“ (doručená). Kroky GZYL v poradí, v akom sa zvyčajne objavujú:

| GZYL píše | Čo to znamená pre vás | Peek ukáže |
| --- | --- | --- |
| `Order information received` | Predajca poslal GZYL údaje o objednávke. GZYL balík ešte nemá. | „Label created“ |
| `Arrive at the cross border receiving point` | GZYL už balík má: je to prvý sken u GZYL. | „Received by carrier“ |
| `Goods leave the operation center` | Balík opustil prevádzkové centrum GZYL. | „In transit“ |
| `Arrive at the international airport` | Balík je na letisku a čaká na let. | „In transit“ |
| `Departing from the airport` | Lietadlo odletelo. | „In transit“ |
| `Item arrives at destination airport` | Balík priletel do vašej krajiny. | „In transit“ |
| `local carrier Agent Import customs clearance` | Colný zástupca vo vašej krajine vybavuje dovozné colné konanie. | „At customs“ |
| `The import customs keep it for inspection` | Colníci vo vašej krajine balík kontrolujú. | „At customs“ |
| `Customs clearance completed` | Balík je preclený a pokračuje ďalej. | „In transit“ |
| `Internationaliy Package received at processing center` | Spracovateľské centrum vo vašej krajine prevzalo preclený balík. Nejde o nové prevzatie: GZYL mal balík už oveľa skôr. | „In transit“ |
| `Leave the email processing center` | Balík odišiel z tohto spracovateľského centra. | „In transit“ |
| `Arrival sorting, distribution center` | Balík je v distribučnom centre vo vašej krajine; GZYL za text dopíše názov mesta. | „In transit“ |
| `Leaving the sorting center` | Balík odchádza z triediaceho centra; aj tu je za textom mesto. | „In transit“ |
| `Package is on the way` | Balík putuje ďalej po vašej krajine, za textom je mesto. Neznamená to, že ho kuriér už vezie k vám. | „In transit“ |
| `Delivered` | Doručenie, za textom je mesto. Môže to byť len plán, nie sken (pozrite nižšie). | „Delivered“ s poznámkou o pevnom harmonograme, ak tento krok Peek nezadrží (pozrite nižšie) |

Mesto, ktoré GZYL dopíše za tieto texty, ukáže Peek ako miesto kroku.

**Cez Spojené štáty.** V sledovaní sa môže objaviť aj `Los Angeles Processing Center has received it` s mestom v hranatých zátvorkách za textom: spracovateľské centrum v USA prevzalo balík na ceste do cieľovej krajiny. Peek ukáže „In transit“.

**Riadok po čínsky.** Pri objednávke, ktorá ešte nemá žiadny sken, zapíše platforma, na ktorej beží sledovanie GZYL, sama riadok `货物电子信息已经收到`, teda že elektronické údaje o tovare boli prijaté. Predajca objednávku vytvoril, ale balík ešte nikto nenaskenoval. Peek tento riadok ukáže po čínsky, so stavom „Label created“.

**Cez China Post.** Objaviť sa môže aj `广州处理中心已收寄` s mestom v zátvorkách za textom: China Post, čínska pošta, prevzala balík vo svojom spracovateľskom centre v Guangzhou a GZYL tento riadok preberá po čínsky. Aj Peek ho ukáže po čínsky, so stavom „Received by carrier“.

**Časy.** Všetky časy v sledovaní GZYL sú v pekinskom čase, aj pri krokoch vo vašej krajine, a časové pásmo sa pri nich neuvádza. Krok o 20:00 pekinského času je v zime 13:00 slovenského času, v lete 14:00.

**Kroky zapísané vopred.** GZYL vie zapísať celú cestu balíka vopred, od prevzatia až po posledný krok vo vašej krajine, dávno predtým, než nastane čas uvedený pri krokoch. Sledovanie potom ukáže každý krok, až keď jeho čas príde. Takto zapísaný krok je plán, nie sken vášho balíka. Rovnako to robí aj Meiteshun, špeditér, ktorý používa tú istú platformu sledovania. Plán GZYL sa potom končí krokom `Delivered`, ktorý platforma pridáva hromadne večer toho dňa, ktorý je pri ňom uvedený: s dátumom dňa po poslednom kroku plánu a s časom v pracovnej dobe v Pekingu, bez ohľadu na to, v ktorej krajine bývate. Služby sledovania zásielok, ktoré údaje GZYL preberajú, môžu tieto kroky ukazovať tiež. `Delivered` v sledovaní GZYL alebo v takej službe preto nedokazuje, že balík dorazil.

## Kto doručuje zásielky GZYL na Slovensku

Peek nevie, kto zásielky GZYL na Slovensku doručuje: kroky GZYL vo vašej krajine neuvádzajú žiadnu firmu a Peek nemôže sledovanie zásielky GZYL odovzdať pošte ani kuriérovi na Slovensku. Jediný doručovateľ, ktorému Peek dokáže zásielku GZYL odovzdať, je Swiss Post (švajčiarska pošta), a to vo Švajčiarsku a v Lichtenštajnsku.

Peek u GZYL nenašiel lehoty doručenia ani pravidlá pre pokusy o doručenie, výdajné miesta či úložnú dobu: to všetko určuje firma, ktorá zásielku doručuje. Opýtajte sa predajcu, ktorá firma vám objednávku doručí a pod akým číslom ju môžete sledovať.

## Zásielka GZYL neprichádza alebo sa sledovanie nehýbe

**Len `Order information received` alebo `货物电子信息已经收到`.** Predajca objednávku vytvoril, ale GZYL balík ešte nenaskenoval. Ak ani deň po takom kroku nepribudne nič ďalšie, Peek pod „Label created“ doplní: „The sender has created the label but hasn’t handed the parcel to GZYL yet. Tracking starts when they do.“ Teda: odosielateľ vytvoril štítok, ale balík GZYL ešte neodovzdal, a sledovanie sa začne, až keď to urobí. Ak ani po niekoľkých dňoch nič nepribudne, opýtajte sa predajcu, či balík naozaj odoslal.

**Zásielka uviazla na colnici.** Ak od vás žiadajú dovozné poplatky, pozrite si návod [o zásielkach na colnici](guide:customs) (v angličtine). Ak tam zásielka leží niekoľko dní, dajte vedieť predajcovi.

**Sledovanie GZYL alebo služba sledovania zásielok ukazuje viac ako Peek.** Peek zadrží tri druhy krokov: kroky zapísané viac ako 12 hodín pred časom, ktorý uvádzajú, `Delivered`, ktorým sa končí plán GZYL, a kroky s časom neskorším než posledná kontrola Peeku (pozrite nižšie). Kroky navyše teda môžu byť plán GZYL, nie skeny. Riaďte sa termínom doručenia, ktorý vám dal e-shop, a keď uplynie, napíšte predajcovi. Viac v návode [o sledovaní zásielok z Číny](guide:tracking-from-china) (v angličtine).

**`Delivered`, ale nič neprišlo.** Tento krok môže byť súčasťou plánu GZYL, nie sken. Ak zásielku nedoručuje Swiss Post, Peek pod „Delivered“ napíše: „GZYL’s tracking follows a fixed schedule and shows no signature. If nothing arrived, ask the seller.“ Teda: sledovanie GZYL ide podľa pevného harmonogramu a neukazuje podpis; ak nič neprišlo, opýtajte sa predajcu. Napíšte mu, že balík neprišiel, a zistite, ktorá firma ho doručuje a pod akým číslom. Viac v návode [keď sledovanie ukazuje doručenie, ale zásielku nemáte](guide:delivered-not-received) (v angličtine).

**Nič sa nenašlo.** Ak GZYL číslo nepozná, jej sledovanie ukáže jeden výsledok: vaše číslo pod 参考号 (referenčné číslo) a všetky ostatné polia prázdne. Ak ste číslo YL…YQ pridali bez toho, aby ste vybrali prepravcu, Peek namiesto názvu prepravcu ukáže „Probably GZYL“ (pravdepodobne GZYL) a stav „Waiting for GZYL’s first scan“ (čaká sa na prvý sken od GZYL). Peek sa bude GZYL pýtať ďalej. Ak GZYL číslo nepozná ani po týždni, Peek ukáže „GZYL still doesn’t list it after a week. Check the number with the sender, or choose another carrier.“ (GZYL číslo ani po týždni neeviduje; overte ho u odosielateľa alebo vyberte iného prepravcu) a tlačidlo „Choose another carrier“ (vybrať iného prepravcu). Medzitým číslo porovnajte znak po znaku so správou od predajcu.

**Stratená alebo vrátená zásielka.** Prepravu u GZYL objednal predajca, preto o vrátenie peňazí alebo nový balík žiadajte jeho, prípadne platformu, na ktorej ste nakupovali. Viac v návode [o stratených zásielkach](guide:lost-parcel) (v angličtine).

## Kontakt na GZYL

K 4. novembru 2026 uvádza kontaktná stránka (联系我们) webu GZYL, po čínsky, nonstop linku, účet WeChat a účet QQ popredajného servisu, no nič pre príjemcov: tieto kanály slúžia zákazníkom GZYL, teda predajcom. Prepravu objednal predajca, preto sa s otázkami o objednávke obráťte naňho, a ak neodpovedá, na platformu, kde ste nakupovali.

## Sledovanie zásielok GZYL v Peeku

[Peek](/) nevie číslo GZYL s istotou rozpoznať iba podľa tvaru: YL, desať číslic a YQ je len slabý náznak. Vložte ho aj tak: Peek sleduje viac ako 3 500 prepravcov. Kým číslo pridávate, Peek sa opýta sledovania GZYL a zobrazí „Checking tracking services…“ (overujú sa služby sledovania). Ak GZYL číslo pozná, Peek ukáže „GZYL has this parcel.“ (GZYL túto zásielku eviduje). Ak nie, kliknite na „Change“ (zmeniť) a vyberte GZYL v časti „Fits this number“ (zodpovedá tomuto číslu); GZYL nájdete aj vtedy, keď napíšete 广州云链. Pri zásielke, ktorú už máte pridanú, použite „Change carrier“ (zmeniť prepravcu). Funguje aj referenčné číslo od predajcu, ale len keď GZYL vyberiete sami: kliknite na „Change“ (alebo „Change carrier“) a vyhľadajte GZYL. Peek potom ukáže aj číslo, pod ktorým GZYL objednávku vedie, s označením „GZYL tracks this as“ (GZYL ju sleduje pod číslom). Peek však z čísla odstraňuje pomlčky, takže referenčné číslo s pomlčkami sa nenájde: vtedy si od predajcu vypýtajte číslo YL…YQ.

Peek číta priamo sledovanie GZYL a ukazuje stav, každý krok aj s miestom a cieľovú krajinu, ak ju záznam GZYL uvádza. Časy číta podľa pekinského času a ukazuje ich v časovom pásme vášho zariadenia. Mená, adresu, PSČ, hmotnosť, platobné údaje, podpis a doklad o doručení, ktoré záznam GZYL môže obsahovať, Peek vynechá. Predpokladaný dátum doručenia neukazuje: GZYL ho neuvádza.

Peek zadrží každý krok zapísaný viac ako 12 hodín pred časom, ktorý uvádza, aj každý krok s časom neskorším, než je okamih kontroly podľa pekinského času. Ak má objednávka kroky zapísané vopred, zadrží aj `Delivered`, a to aj vtedy, keď bol zapísaný v deň, ktorý uvádza. Každý `Delivered` zadrží aj vtedy, keď údaje číta z webovej stránky sledovania GZYL, lebo tá neukazuje, kedy bol krok zapísaný. Riadi sa krokmi, ktoré ukáže, nie súhrnným stavom v zázname GZYL. Ak Peek zadrží všetky kroky objednávky, nemá k nej zatiaľ históriu sledovania. Keď služby sledovania zásielok ukazujú kópie krokov, ktoré Peek zadržal ako plán GZYL, `Delivered` vrátane, Peek ich tiež vynechá, aj keď iné kroky objednávky ukazuje. Ak je však sledovanie GZYL nedostupné, Peek sa opýta služieb sledovania zásielok a ich kópie môžu obsahovať aj kroky, ktoré GZYL zapísal vopred.

Peek zostáva pri GZYL; inej firme odovzdá zásielku jedine Swiss Post. Ak vám predajca dá číslo od firmy, ktorá zásielku doručuje, pridajte ho do Peeku ako samostatnú zásielku. Peek zásielku kontroluje každých 10 minút. Keď má zásielka stav „Out for delivery“ (doručovateľ ju vezie k vám), kontroluje ju každé 2 minúty. Žiadny krok GZYL, ktorý Peek pozná, však tento stav nemá. Ak dva dni nepribudne nový sken (alebo 12 hodín, kým má zásielka stav „Label created“ alebo „Received by carrier“), kontroluje ju už len raz za hodinu. Upozornenia si vyberiete sami: „Every scan“ (každý sken), „Important steps“ (dôležité kroky) alebo „Delivery only“ (len doručenie). Pri „Important steps“ vás Peek upozorní na dva colné kroky GZYL a na `Delivered`, nie na lety ani triedenie. Kontaktovať GZYL ani predajcu za vás Peek nemôže.

## Časté otázky o sledovaní zásielok GZYL

### Čo je GZYL (广州云链)?

GZYL je 广州云链货运代理有限公司 (Guangzhou Yunlian), špedičná firma z mesta Guangzhou v Číne. Jej čísla zásielok majú tvar YL, desať číslic a YQ.

### Je každé číslo YL…YQ od GZYL?

Nie s úplnou istotou. YL, desať číslic a YQ je tvar čísel GZYL a žiadny iný prepravca v katalógu Peeku ho nepoužíva. Peek sa však aj tak najprv opýta sledovania GZYL a až potom číslo priradí GZYL. S deviatimi alebo jedenástimi číslicami to nie je tvar GZYL.

### Prečo GZYL ukazuje zásielku ako doručenú, hoci neprišla?

Môže to byť súčasť plánu GZYL. GZYL zapisuje veľkú časť cesty balíka vopred a končí ju krokom `Delivered`, a takéto kroky sú plán, nie skeny. Peek ich zo sledovania GZYL zadrží, `Delivered` vrátane, a ak „Delivered“ predsa ukáže, pripíše pod neho, že sledovanie GZYL ide podľa pevného harmonogramu. Napíšte predajcovi, že balík neprišiel, a opýtajte sa, ktorá firma ho doručuje.

### Kto doručuje zásielky GZYL na Slovensku?

Peek to nevie: kroky GZYL vo vašej krajine neuvádzajú žiadnu firmu. Opýtajte sa predajcu, ktorá firma vám objednávku doručí a pod akým číslom, a toto číslo pridajte do Peeku ako samostatnú zásielku. Sám odovzdať zásielku GZYL inej firme vie Peek len vo Švajčiarsku a v Lichtenštajnsku, a to Swiss Post.

:::sources
- [广州云链货运代理有限公司 (GZYL): úvodná stránka (po čínsky)](http://yunlianhouyun.com/) – názov firmy, web po čínsky, vyhľadávanie zásielok (小包查询) v sledovaní GZYL
- [广州云链货运代理有限公司 (GZYL): 联系我们 (kontakt, po čínsky)](http://yunlianhouyun.com/contact.html) – nonstop linka, WeChat a QQ popredajného servisu
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – názov firmy a ďalšie mená, sledovanie na IP adrese, tvar YL…YQ ako slabý náznak, Xinghe Freight v sledovaní GZYL, vyhľadávanie podľa čísla alebo referenčného čísla s pomlčkami, texty krokov a ich stavy, riadky po čínsky, mestá za textami, pekinský čas, plán zapísaný vopred a `Delivered`, ktorým sa končí, odpoveď pri neznámom čísle, Swiss Post ako jediné odovzdanie, čo Peek ukazuje, zadrží a vynechá
:::
