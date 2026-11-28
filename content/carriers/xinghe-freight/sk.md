---
title: Xinghe Freight sledovanie: číslo GZXH…YQ a statusy
description: Sledujte zásielku Xinghe Freight (星和货运) s číslom GZXH…YQ z Kantonu: prečo používa systém GZYL, dopredu naplánované Delivered, colné poplatky EÚ a kontakt.
slug: xinghe-freight-sledovanie-zasielky
published: 2026-11-27
updated: 2026-11-27
---

Sledujte zásielku Xinghe Freight (星和货运, 广州星和货运代理有限公司, založená v roku 2014 v Kantone) vložením jej 16-znakového čísla v tvare `GZXH` + 10 číslic + `YQ` do poľa vyššie. Webová stránka Xinghe Freight presmerúva sledovacie požiadavky na nešifrovaný HTTP systém GZYL, kde kroky — vrátane `Delivered` — môžu byť naplánované vopred namiesto reálneho skenovania; Peek tento systém prečíta za vás, oddelí predpovede od skutočných skenov a v prípade potreby osloví aj ďalšie sledovacie služby.

## Sledovacie číslo Xinghe Freight: ako vyzerá

Čísla, ktoré Peek pozná ako čísla Xinghe Freight, pozostávajú z `GZXH`, desiatich číslic a `YQ`, spolu 16 znakov. S vymyslenými číslicami:

| Číslo | Čo je to |
| --- | --- |
| `GZXH4829105736YQ` | `GZXH`, desať číslic a `YQ`: formát Xinghe Freight. Peek hneď rozpozná Xinghe Freight. |
| `GZXH482910573YQ` | Len deväť číslic: nezodpovedá formátu Xinghe. Skontrolujte chýbajúcu číslicu. |
| `YL4829105736YQ` | `YL`, desať číslic a `YQ`: formát nákladných listov [GZYL](carrier:gzyl). Rovnaký systém, iný zasielateľ. |
| `JXCAA4829105736YQ` | Tiež končí na `YQ`, ako mnohé čísla čínskych zasielateľov, ale začína na `JXC`: ide o číslo [Juxi Express](carrier:juxi-express). |

Medzery, pomlčky a malé písmená pri vkladaní do Peeku nerozhodujú.

**Kde ho nájsť.** Zásielku objednáva u Xinghe predajca a číslo vám odovzdá. Ak ho neviete nájsť, pozrite si [ako nájsť sledovacie číslo](guide:find-tracking-number) (v angličtine).

## Vysvetlenie sledovacích statusov Xinghe Freight

Sledovanie Xinghe beží na systéme GZYL, takže jeho kroky prichádzajú v anglických formuláciách GZYL vrátane preklepov. Peek ich číta podľa zoznamu krokov GZYL. V bežnom poradí:

| Čo píše systém | Čo to znamená pre vás | Čo zobrazuje Peek |
| --- | --- | --- |
| `Order information received` | Predajca odoslal údaje objednávky. Zásielku ešte nikto nemá. | „Label created“ |
| `Arrive at the cross border receiving point` | Zasielateľ prevzal zásielku: prvý fyzický sken v jeho rukách. | „Received by carrier“ |
| `Goods leave the operation center` | Zásielka opustila prevádzkové centrum. | „In transit“ |
| `Arrive at the international airport` | Na letisku, čaká na svoj let. | „In transit“ |
| `Departing from the airport` | Let odletel. | „In transit“ |
| `Item arrives at destination airport` | Pristála v cieľovej krajine. | „In transit“ |
| `local carrier Agent Import customs clearance` | Miestny zástupca vo vašej krajine vybavuje dovozné colné konanie. | „At customs“ |
| `The import customs keep it for inspection` | Colný úrad vašej krajiny kontroluje zásielku. | „At customs“ |
| `Customs clearance completed` | Colnica prebehla, zásielka pokračuje ďalej. | „In transit“ |
| `Internationaliy Package received at processing center` | Spracovateľské centrum vo vašej krajine prevzalo preclenú zásielku. Nie je to nové podanie. | „In transit“ |
| `Leave the email processing center` | Zásielka opustila toto spracovateľské centrum. | „In transit“ |
| `Arrival sorting, distribution center` | V triediacom centre vo vašej krajine s mestom za textom. | „In transit“ |
| `Leaving the sorting center` | Opúšťa triediace centrum s uvedeným mestom. | „In transit“ |
| `Package is on the way` | Pohybuje sa vo vašej krajine s mestom. Neznamená to, že ju už vezie kuriér na doručenie. | „In transit“ |
| `Delivered` | Doručenie s mestom. Môže ísť o plán, nie sken (pozri nižšie). | „Delivered“ s poznámkou o pevnom rozvrhu, ak ho Peek nezadrží (pozri nižšie) |

**Cez Hongkong.** Zásielka môže zobraziť aj `香港处理中心已收寄【Hong Kong】`: poštové prevzatie v spracovateľskom centre v Hongkongu, ktoré systém prenáša v čínštine s mestom v zátvorkách. Peek riadok zobrazuje doslova pod „Received by carrier“.

**Riadok v čínštine.** Pri objednávke bez skenov môže stránka zobraziť `货物电子信息已经收到` („elektronické informácie o tovare boli prijaté“). Tento riadok píše sama platforma: predajca vytvoril objednávku, ale balík ešte nikto neskenoval. Peek ho zobrazuje v čínštine pod „Label created“.

**Časy.** Peek číta každý čas v systéme ako pekinský čas vrátane krokov vo vašej krajine a zobrazuje ho v časovom pásme vášho zariadenia. V zime je 20:00 v Pekingu 13:00 v Bratislave, Paríži či Berlíne, 12:00 v Londýne a 7:00 v New Yorku.

**Kroky zapísané vopred.** Na tejto sledovacej platforme môžu zasielatelia zapísať celú časovú os balíka dopredu — od prijatia až po posledný krok vo vašej krajine — a systém každý zobrazí až vo chvíli jeho času. Taký krok je plán, nie sken. V systéme GZYL sa plán končí riadkom `Delivered`, ktorý platforma pridá vo večernej dávke v deň, ktorý zobrazuje: s dátumom nasledujúceho dňa po poslednom kroku plánu, v pekinských pracovných hodinách, bez ohľadu na vašu krajinu. Peek tieto kroky zadržiava (nižšie), ale kópie na iných sledovacích weboch ich môžu stále ukazovať, takže `Delivered` tam nedokazuje doručenie.

## Kto doručuje zásielky Xinghe Freight

Peek nevie, ktorá miestna spoločnosť doručuje zásielky Xinghe vo vašej krajine. Kroky v systéme žiadnu firmu neuvádzajú a ani web Xinghe. Jediný doručovateľ, ktorému vie Peek zásielku Xinghe odovzdať, je [Swiss Post](carrier:swiss-post) vo Švajčiarsku a Lichtenštajnsku, keď ju záznam Xinghe uvádza.

Samotný web Xinghe neuvádza nič o balíkoch pre nakupujúcich online: žiadne dodacie lehoty, pokusy o doručenie ani výdajné miesta. Platia pravidlá miestneho doručovateľa. Spýtajte sa predajcu, ktorá spoločnosť objednávku doručuje, a vypýtajte si jej sledovacie číslo.

**Colné poplatky v EÚ.** Od 1. júla 2026 podlieha tovar kúpený online mimo EÚ v zásielkach do 150 € clu 3 € za druh tovaru. Od 1. novembra 2026 sa k tomu účtuje aj manipulačný poplatok Únie 2 € za druh tovaru bez ohľadu na hodnotu. Druhom tovaru sú položky v balíku s rovnakým colným zaradením, opisom a prípadným pôvodom bez ohľadu na počet kusov. Viac v sprievodcovi [zásielky na colnici](guide:customs) (v angličtine).

## Zásielka Xinghe Freight neprichádza alebo sledovanie stojí

**Len `Order information received` alebo `货物电子信息已经收到`.** Predajca vytvoril objednávku, ale balík ešte nikto neskenoval. Ak sa po pár dňoch nič nezmení, spýtajte sa predajcu, či ju skutočne odoslal.

**„Waiting for Xinghe Freight“.** Peek zatiaľ nemá žiaden reálny krok: systém GZYL číslo nepozná alebo drží len vopred zapísaný plán (ktorý môže končiť na `Delivered`) a nič nemajú ani ostatné sledovacie služby. Peek pokračuje v kontrole. Pre neznáme číslo vráti GZYL jeden prázdny výsledok s číslom v poli referencie; Peek to berie ako nenájdené, zobrazuje „Waiting for Xinghe Freight“ a po dvoch dňoch „Check the tracking number“. Vopred zapísaný plán potvrdzuje, že Xinghe objednávku eviduje, preto Peek naďalej zobrazuje „Waiting for Xinghe Freight“ bez výzvy na kontrolu čísla. Porovnajte číslo so správou predajcu.

**Zaseknutá na colnici.** Ak od vás pýtajú dovozné poplatky, pozrite si [zásielky na colnici](guide:customs) (v angličtine). Ak balík stojí na colnici celé dni, informujte predajcu.

**Systém alebo iná služba ukazuje viac než Peek.** Kroky navyše vrátane `Delivered` môžu byť vopred napísaný plán, ktorý Peek zadržiava. Riaďte sa dodacou lehotou obchodu a po jej uplynutí napíšte predajcovi. Viac: [prečo sa sledovanie neaktualizuje](guide:tracking-not-updating) (v angličtine).

**`Delivered`, ale nič neprišlo.** Môže ísť o vopred naplánovaný krok. Pokiaľ nedoručuje Swiss Post, Peek pod „Delivered“ uvádza: „Xinghe Freight updates on a fixed schedule, with no signature. If nothing came, ask the seller.“ Oznámte predajcovi, že balík nedorazil, a vypýtajte si názov doručovateľa a jeho číslo. Pozrite aj [doručené, ale neprijaté](guide:delivered-not-received) (v angličtine).

**Stratená alebo vrátená.** Zásielku cez Xinghe objednal predajca, preto žiadajte vrátenie peňazí alebo nový balík od predajcu či trhoviska.

## Kontakt a zákaznícka podpora Xinghe Freight

Podľa stavu zo 6. novembra 2026 uvádza čínska webová stránka Xinghe (xinghe-express.cn):

- **Horúca linka:** 19924483103, údajne dostupná 24 hodín denne každý deň (+86 199 2448 3103 zo zahraničia).
- **E-mail:** 3975605292@qq.com pre obchodné dopyty a cenové ponuky.
- **Adresa:** Kanton, provincia Kuang-tung (bez ulice; na získanie celej adresy stránka žiada najprv kontaktovať firmu).

Stránka nemá podporu pre zahraničných príjemcov: kontaktný formulár iba otvorí e-mail na túto adresu. Zákazníkom Xinghe je predajca, preto sa obráťte najskôr na neho a ak neodpovedá, na trhovisko.

## Sledovanie zásielky Xinghe Freight cez Peek

[Peek](/) okamžite priradí Xinghe Freight pre číslo `GZXH…YQ` so značkou „Detected carrier“. Ak vám predajca dal číslo iného tvaru, vložte ho napriek tomu: Peek sleduje vyše 4 500 dopravcov. Ručne ho vyberiete cez „Change“ vyhľadaním Xinghe Freight (funguje aj `星和货运`) alebo cez „Change carrier“ pri už pridanom balíku.

Peek sa pýta systému GZYL rovnako ako vyhľadávacie pole samotného Xinghe. Zobrazuje stav, každý krok s miestom a cieľovú krajinu, ak ju záznam obsahuje. Časy číta v pekinskom čase a zobrazuje vo vašom časovom pásme. Vynecháva mená, adresu, PSČ, hmotnosť, platobné údaje, podpis a doklad o doručení a nezobrazuje očakávaný dátum doručenia, lebo ho systém neposkytuje.

Keď systém vaše číslo nepozná, Peek osloví sledovacie služby a zobrazí ich kópiu krokov, ktorá môže byť jediným záznamom. Kým žiadna nemá krok, zobrazuje sa „Waiting for Xinghe Freight“ a o dva dni neskôr „Check the tracking number“.

Peek zadržiava každý krok zapísaný viac než 12 hodín pred časom, ktorý ukazuje, každý krok zapísaný v tej istej sekunde ako krok vytvorený viac než pol hodiny pred svojím časom (okrem registrácie objednávky), a každý krok datovaný po okamihu kontroly v pekinskom čase. Pri zázname s vopred zapísanými krokmi zadrží aj `Delivered`, hoci zapísané v svoj deň. Riadi sa ponechanými krokmi, nie súhrnným stavom, a odkladá bokom aj kópie zadržaných krokov z iných sledovacích služieb vrátane `Delivered`, aj keď iné kroky ponechá. Keď však systém nemá záznam alebo nie je dostupný, Peek nevie odlíšiť plánovaný krok v kópii služby od skenu. Po zadržaní krokov začína história riadkom (až do doručenia či vrátenia): „Other sites may show more steps, but they’re only predictions.“ a tlačidlo „Show them“ ich vypíše sivo s dňom („Predicted for Mon 12 Oct“). Taký krok sa nikdy nepočíta ako sken, nemení stav a neposiela upozornenie.

Okrem Swiss Post Peek neodovzdáva balík Xinghe miestnemu dopravcovi: ak vám predajca dá jeho číslo, pridajte ho ako samostatnú zásielku. Kým máte balík otvorený alebo v deň doručenia s upozorneniami, Peek kontroluje až každých 10 minút; inak a po utíchnutí každú hodinu. Upozornenia: každý sken, iba dôležité kroky alebo len doručenie („Important steps“ zahŕňa dva colné kroky a `Delivered`, nie lety ani triedenie). Peek nemôže kontaktovať Xinghe Freight, GZYL ani predajcu za vás.

## Otázky o sledovaní Xinghe Freight

### Čo je Xinghe Freight (星和货运)?

广州星和货运代理有限公司 (Guangzhou Xinghe Freight Forwarding) je logistický zasielateľ založený v roku 2014 v Kantone, ktorý zabezpečuje námornú, leteckú a pozemnú prepravu, lodnú agentúru a colné doklady. Jeho čísla zásielok majú 16 znakov: `GZXH`, desať číslic a `YQ`.

### Prečo moja zásielka Xinghe Freight zobrazuje sledovanie GZYL?

Vyhľadávacie pole Xinghe posiela dopyty priamo do systému GZYL, preto sa znenie krokov zhoduje s GZYL; Peek vedie zásielku pod Xinghe Freight a systém číta za vás.

### Kto doručuje zásielky Xinghe Freight v mojej krajine?

Ani stránka Xinghe, ani systém neuvádzajú miestneho doručovacieho partnera (Peek vie zásielku odovzdať iba Swiss Post vo Švajčiarsku a Lichtenštajnsku, keď ju uvádza záznam Xinghe). Spýtajte sa predajcu na miestneho dopravcu a jeho sledovacie číslo.

### Prečo Peek neukazuje žiadne alebo menej krokov pre moje číslo GZXH?

Systém buď zatiaľ nemá záznam, alebo obsahuje vopred napísané kroky — vrátane naplánovaného `Delivered` — ktoré Peek skrýva za „Show them“ namiesto toho, aby ich počítal ako skeny. Overte číslo v e-maile predajcu a po pár dňoch bez zmeny ho kontaktujte.

### Ako kontaktovať Xinghe Freight?

Obráťte sa najprv na predajcu alebo trhovisko, keďže dopravu objednal predajca. Čínsky web `xinghe-express.cn` uvádza nonstop linku 19924483103 (+86 199 2448 3103 zo zahraničia) a obchodný e-mail 3975605292@qq.com, bez vyhradeného kanála pre zahraničných príjemcov.

:::sources
- [Xinghe Freight: 广州星和货运代理有限公司 (home page)](http://xinghe-express.cn/) – company name, freight forwarding services, tracking box that opens GZYL's tracker, hotline and email
- [Xinghe Freight: 关于我们 (about)](http://xinghe-express.cn/about.html) – founded in 2014, sea, air and land freight, ship agency, customs declaration
- [Xinghe Freight: 产品服务 (services)](http://xinghe-express.cn/services.html) – services offered, nothing on parcels for online shoppers
- [Xinghe Freight: 联系我们 (contact)](http://xinghe-express.cn/contact.html) – 24-hour hotline, email for business enquiries and quotes, address given as Guangzhou only, get in touch before visiting, contact form opening an email
- [European Commission: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – €3 customs duty per kind of product on parcels up to €150 since 1 July 2026, €2 Union handling fee since 1 November 2026
- [European Commission: Guidance on the €3 customs duty and the Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – handling fee per item whatever the value, what an item is
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – names, GZXH…YQ number shape, Xinghe read on GZYL's tracker, step wordings and stages, Hong Kong acceptance, Chinese line, Beijing clock, timelines written ahead, tracking services as fallback, the tracker's answer for an unknown number, Swiss Post hand-over, what Peek shows, holds back and leaves out
:::
