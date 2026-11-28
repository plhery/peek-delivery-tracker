---
title: Yanwen sledování zásilek: číslo UL…YP, stavy a doručení
description: Sledujte zásilku Yanwen (UL…YP, UK…YP, BYS nebo YW): význam stavů, místní dopravce pro doručení, doba dodání a kontakty na Yanwen.
slug: yanwen-sledovani-zasilek
published: 2026-11-28
updated: 2026-11-28
---

Sledovací číslo tvořené dvěma písmeny (často `UL` nebo `UK`), 9 číslicemi a koncovkou `YP` — nebo `BYS` a 9 číslicemi — patří čínské logistické společnosti Yanwen, založené v Pekingu v roce 1998, která přepravuje internetové objednávky do zahraničí a na poslední úsek je předává místnímu dopravci. Zásilku můžete sledovat na stránce Yanwenu [track.yw56.com.cn](https://track.yw56.com.cn/en/index) nebo v poli výše; číslo najdete v údajích o dopravě u obchodu nebo tržiště.

## Sledovací číslo Yanwen: jak vypadá

Yanwen nezveřejňuje vzorové číslo ani nápovědu pro příjemce. Formáty, které zásilky Yanwen mívají, s vymyšlenými číslicemi:

| Číslo | Komu patří |
| --- | --- |
| `BYS482910573` | BYS a 9 číslic: Yanwen. |
| `UL482917367YP` | Dvě písmena, 9 číslic a koncovka YP: Yanwen. Časté začátky jsou UL a UK. |
| `YWABC482910573649` | YW, tři písmena a 12 číslic: číslo pro poslední úsek zásilky doručované v USA. |
| `RR482917367CN` | Stejný poštovní tvar končící na CN: [China Post](carrier:china-post), nikoli Yanwen. |
| `YT4829105736482910` | Ne Yanwen, ale [YunExpress](carrier:yunexpress). |
| `LP48291057364829` | Ne Yanwen, ale [Cainiao](carrier:cainiao), logistické označení AliExpressu. |

Poštovní formát čísla Yanwen končí vždy na YP; jakákoli jiná koncovka patří jiné poště. Stránka výsledků Yanwenu zobrazuje dvě čísla: „Tracking Number“, tedy číslo Yanwenu, a „Last Mile Tracking“, které patří dopravci doručujícímu ve vaší zemi. Pokud vám obchod poslal toto místní číslo, stránka Yanwenu na něj odpoví také. Nemůžete-li žádné číslo najít, požádejte o něj obchod.

Stránka Yanwenu přijme až 100 čísel najednou, samoobslužné sledování ale zahrnuje jen nákladní listy z posledního roku. Produkty China Post na ní sledovat nelze.

## Vysvětlení stavů sledování Yanwen

Sledovací stránka Yanwenu funguje pouze v angličtině a čínštině a vlastní hlášky Yanwenu jsou v obou jazycích anglicky. Hlášky, se kterými se lidé setkávají, v pořadí, v jakém jimi zásilka prochází:

| Yanwen uvádí | Co to znamená pro vás |
| --- | --- |
| `Order processed by shipper` | Prodejce zásilku objednal. Yanwen ji ještě nemá. |
| `Yanwen Pickup Scan` | Yanwen převzal zásilku v Číně. |
| `Yanwen facility - Outbound` | Zásilka opustila třídicí centrum Yanwenu. |
| `Tendered for Delivery` | Předána k přepravě do zahraničí, jen několik minut po opuštění třídicího centra Yanwenu a ještě před vývozním clem. Navzdory slovům nejde o rozvoz k vám. |
| `International shipment release - Export` | Vývozní celní řízení v Číně je hotové. |
| `Port of departure - Departure` | Předána letecké společnosti a odletěla. |
| `Port of destination - Arrival` | Dorazila do vaší země nebo do její blízkosti. |
| `International shipment release - Import` | Dovozní celní řízení zásilku uvolnilo. |
| `Batch delivery to carrier` | Předána místnímu dopravci. Následují jeho záznamy. |
| `Parcel is out for delivery.` | Na cestě k vám (vlastní americká služba Yanwenu). |
| `<User not at home>` | Pokus o doručení nevyšel, převzato od místního dopravce. |
| `Delivered.` | Doručeno, slovy jednoho z místních dopravců. |

`Order Submited.` a `Waybill Generated` znamenají totéž co první řádek: existuje jen štítek a zásilka ještě nemusela opustit prodejce. `Shipping Order Created.` je objednávkové hlášení partnera: Peek ponechá fázi, kterou zásilka už měla. `Pick up by local carrier at destination port` znamená totéž co `Batch delivery to carrier`. Poté následují vlastní řádky místního dopravce, někdy s předponou země nebo názvem depa v závorkách: například `Out for Delivery` nebo `Delivered, In/At Mailbox` od USPS. Záznamy jednoho místního dopravce chodí velkými písmeny bez jeho názvu: `ARRIVE TRANSITHUB` (jeho tranzitní uzel), `TASK ASSIGNED` (úkol doručení) a `ORDER SORTED` (třídění), vše jako „In transit“ v Peeku. V Itálii je `First parcel scan along Poste Italiane’s logistic network` prvním záznamem Poste Italiane po předání od Yanwenu, nikoli novým svozem („In transit“). Hláška USPS `Shipping Label Created, USPS Awaiting Item` se může objevit, když je zásilka ještě v Číně: jde jen o záznam štítku v systému USPS, takže Peek ponechá dosavadní fázi a nezobrazí u ní žádné místo. Také zpráva DHL `The recipient has selected a Packstation as the new delivery address.` může dorazit ještě během pobytu zásilky v Číně: jen zaznamenává, že jste pro doručení vybrali DHL Packstation, a Peek s ní zachází stejně.

Některé zásilky přecházejí po `Yanwen facility - Outbound` ke China Post. Yanwen pak přebírá záznamy China Post v čínštině, vysvětlené na stránce [China Post](carrier:china-post); `中国邮政已收取邮件` označuje toto předání. `【…】退回，备注:安检退回` znamená, že třídicí centrum zásilku vrátilo při bezpečnostní kontrole a řeší ji před dalším odesláním: nejde o vrácení prodejci. Peek tento stav ukazuje jako „Needs attention“.

Nad záznamy zařazuje Yanwen každou zásilku do kategorie. `Info_Received` je fáze štítku. `In_Transportation` pokrývá vše od letu přes celní řízení po místní úsek. `End_of_Tracking` znamená, že Yanwen zásilku přestal sledovat: nic to neříká o tom, kde balík je, a neznamená to doručení ani ztrátu. `Not_Found` s řádkem `No information was found` znamená, že Yanwen pod daným číslem nic nemá. Řádek doručené zásilky ukazuje počet dnů cesty, například „Delivered（7d）“.

## Doručení, vyzvednutí a neúspěšné doručení Yanwen

Yanwen v Evropě sám nedoručuje: uvádí, že pro doručení na poslední míli spolupracuje s místními dopravci („with local carriers to fulfill the last mile package delivery“). Když tohoto dopravce uvede, zobrazí stránka výsledků pole s poznámkami obsahující „Distributor“, „Distributor Website“ a „Contact Number“. U některých zásilek je toto pole prázdné.

Kdo doručuje, záleží na zemi a službě zvolené obchodem. Produktový přehled Yanwenu například pro Spojené království jmenuje linky přes [Royal Mail](carrier:royal-mail), Yodel, Hermes a [Evri](carrier:evri). V USA doručují zásilky sledované na stránce Yanwenu společnosti [USPS](carrier:usps), [UniUni](carrier:uniuni) nebo YanwenExpress, americký doručovatel pro poslední míli, jehož web neuvádí, zda je propojen s Yanwenem (viz [YWE](carrier:ywe)), a v Kanadě Emile nebo UniUni. V Austrálii Yanwen předává zásilky v dávkách v přístavu příjezdu společnosti [J&T Express Australia](carrier:j-and-t-au); záznamy J&T se pak zobrazí v historii Yanwenu a Peek zobrazuje převzetí J&T jako „In transit“.

Počet pokusů o doručení, oznámení ve schránce, přeplánování i úložní doba se řídí pravidly místního dopravce: Yanwen žádná nezveřejňuje. `Available_For_Pick_Up` znamená, že zásilka čeká na výdejním místě; `Undelivered` znamená neúspěšný pokus o doručení. Použijte číslo z „Last Mile Tracking“ a kontakt z pole poznámek a domluvte si s daným dopravcem nové doručení.

Na stránce výsledků je u každého záznamu uveden jeho posun vůči GMT.

## Zásilka Yanwen se neaktualizuje, je ztracená nebo vrácená

**Zatím žádné sledování.** Dokud zásilka zůstává v kategorii `Info_Received`, prodejce vytvořil štítek, ale balík možná ještě nepředal. Zeptejte se obchodu, kdy byl odeslán.

**Zaseknutá několik dnů.** Jakmile je zásilka u místního dopravce, zkontrolujte jeho vlastní sledování pomocí čísla z „Last Mile Tracking“. Více v článku [proč se sledování přestává aktualizovat](guide:tracking-not-updating) (v angličtině).

**Doručená, ale chybí.** Doručení provedl místní dopravce, takže začněte u něj a poté se obraťte na obchod. Viz [označeno jako doručené, ale nedorazilo](guide:delivered-not-received) (v angličtině).

**Ztracená, poškozená nebo vrácená.** Yanwen nezveřejňuje pro příjemce žádný reklamační postup, lhůtu ani odškodnění. Jeho stránky slouží prodejcům a smlouvu s Yanwenem má obchod nebo tržiště, proto tam žádejte vrácení peněz nebo novou zásilku. `Exception` a `Returned` jsou kategorie Yanwenu pro tyto případy.

**Starší zásilky.** Nákladní listy po roce ze samoobslužného sledování zmizí; podle oznámení Yanwenu je však operátor na horké lince stále dokáže dohledat.

## Telefonní číslo a zákaznický servis Yanwen

Yanwen uvádí tyto kontakty:

- **Horká linka**: 400-108-5656, pondělí až sobota 9:00 až 18:00, podle čínské kontaktní stránky. Číslo začínající na 400 je čínská servisní linka; Yanwen neuvádí, zda funguje ze zahraničí ani jakými jazyky se na ní mluví.
- **E-mail**: marketing@yw56.com.cn, jediná adresa na anglickém webu. Yanwen ji neoznačuje jako adresu podpory.
- **Další kanály**: online chat vpravo dole na yw56.com.cn, zákaznický portál a účet WeChat, vše v čínštině a určené prodejcům.
- **Poslední míle v USA**: stránka zákaznické podpory YanwenExpress neuvádí žádné telefonní číslo ani e-mailovou adresu.

Yanwen nezveřejňuje kontakt pro žádnou evropskou zemi. Kvůli objednávce je rychlejší obchod nebo tržiště; kvůli samotnému doručení využijte dopravce a číslo z pole poznámek.

## Sledování zásilky Yanwen v aplikaci Peek

[Peek](/) okamžitě rozpozná čísla BYS, čísla s koncovkou YP i americká čísla YW. Číslo s koncovkou YP, které má chybnou kontrolní číslici, se jako Yanwen nerozpozná: vyberte Yanwen přes „Change“. Odkaz z track.yw56.com.cn obsahující `nums=` Peek čte jako Yanwen bez ohledu na to, jaké číslo obsahuje, takže funguje i pro místní sledovací číslo.

Peek zobrazuje stav, historii s městem nebo regionem každého záznamu na mapě, čas doručení, cílovou zemi a místního dopravce včetně jeho čísla („Delivery with“ s tímto dopravcem, případně „Announced to …, not received yet“, dokud má dopravce jen avízo zásilky bez vlastního záznamu). Pokud Yanwen uvede místního dopravce ze seznamu Peeku a číslo k němu pasuje, Peek se zeptá i jeho a přejde na něj, jakmile ukáže novější posun. Yanwen neuvádí očekávané datum doručení; Peek nezobrazuje výdejní místo a vynechává čísla objednávek, kontakty a PSČ.

Časy záznamů se načítají s posuny GMT od Yanwenu a zobrazují v časovém pásmu vašeho zařízení, přičemž duplicitní záznamy se odstraňují. Převzatý krok USPS `In Transit to Next Facility` datovaný na půlnoc ukazuje jen datum, protože USPS tyto kroky datuje pouze na dny. `Delivered` a `Out for delivery` se zobrazují vlastními slovy Peeku; ostatní řádky tak, jak byly zapsány, včetně `Delivered.`. Dokud máte zásilku otevřenou, nebo v den doručení se zapnutými oznámeními, Peek ji kontroluje až každých 10 minut a od 6:00 místního času v den, kdy zásilka vyjede na rozvoz, až každé 2 minuty, pokud odpovídá vlastní sledovač Yanwenu; jinak každou hodinu. Přes sledovací služby kontroluje každou hodinu, po klidném dni každé 3 hodiny a v den doručení za stejné podmínky až každých 15 minut. Když doručení převezme jiný dopravce, řídí se Peek jeho tempem. Může vás upozorňovat na každý záznam, jen na důležité kroky nebo na „Delivery only“. Po dvou dnech bez zpráv (po 12 hodinách, dokud zásilka stále zobrazuje „Label created“ nebo „Received by carrier“) kontroluje Peek jednou za hodinu. Za vás ale nemůže kontaktovat Yanwen ani obchod.

## Otázky ke sledování Yanwen

### Co znamená sledovací číslo končící na YP (například UL…YP)?

Dvě písmena (často `UL` nebo `UK`), 9 číslic a `YP` je poštovní formát sledovacího čísla Yanwenu — `YP` označuje Yanwen, nikoli poštu určité země. Yanwen zajistí přepravu z Číny a předá balík místnímu dopravci ve vaší zemi, který mu může přidělit druhé číslo pro doručení na poslední míli.

### Kde je moje zásilka Yanwen?

Nejnovější záznam ukazuje její fázi a — pokud je zapsáno — město nebo region. Jakmile se objeví `Batch delivery to carrier`, zásilku už má místní dopravce ve vaší zemi: na stránce výsledků Yanwenu nebo v Peeku vyhledejte jeho název a místní sledovací číslo.

### Co znamená Yanwen facility - Outbound?

Zásilka opustila třídicí centrum Yanwenu v Číně. Následuje `Arrived at domestic terminal station`, vývozní celní řízení (`International shipment release - Export`) a mezinárodní let.

### Je Yanwen důvěryhodná společnost?

Ano. Yanwen je logistická společnost založená v Pekingu v roce 1998, která se v roce 2015 stala doporučeným logistickým partnerem pro AliExpress a eBay a posílá přeshraniční zásilky, jejichž poslední úsek doručují místní dopravci.

### Jak dlouho trvá doručení s Yanwen?

Yanwen nezveřejňuje pro příjemce žádné dodací lhůty, řiďte se proto odhadem doručení uvedeným u vaší objednávky v obchodě nebo na tržišti. Samoobslužné sledování pokrývá nákladní listy z posledního roku; obecně k zásilkám z Číny viz [sledování zásilky z Číny](guide:tracking-from-china) (v angličtině).

:::sources
- [Yanwen: YW Tracking](https://track.yw56.com.cn/en/index) – sledovací stránka, jazyky, sloupce výsledků, kategorie, pole poznámek, horká linka
- [Yanwen: 燕文追踪 (sledování, čínsky)](https://track.yw56.com.cn/) – záznamy v angličtině, upozornění k China Post
- [Yanwen: YANWEN EXPRESS home page](https://www.yw56.com.cn/en/) – společnost, 1998 Peking, doručení místními dopravci, horká linka, e-mail
- [Yanwen: Company Profile](https://www.yw56.com.cn/webfile/Profile/) – AliExpress a eBay
- [Yanwen: Contact Us](https://www.yw56.com.cn/webfile/ContactUs/) – horká linka, e-mail
- [Yanwen: 联系我们 (kontakt)](https://www.yw56.com.cn/webfile/联系我们/) – provozní doba horké linky, linky přes Royal Mail, Yodel, Hermes, Evri
- [Yanwen: 关于官方快件查询入口及服务的通告 (oficiální kanály sledování)](https://www.yw56.com.cn/webfile/ANNOUNCEMENTS/2025/06-30/7345353199651393536.html) – 100 čísel, roční limit, dohledání po telefonu, chat, portál, WeChat
- [YanwenExpress: home page](https://www.yanwenexpress.com/) – doručení na poslední míli v USA
- [YanwenExpress: Customer support](https://www.yanwenexpress.com/contactus.html) – bez telefonního čísla i e-mailové adresy
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – co Peek zobrazuje, záznamy J&T Express Australia po dávkovém předání, hlášení DHL Packstation, záznamy velkými písmeny místního dopravce, první záznam Poste Italiane
:::
