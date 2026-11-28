---
title: HuaHan Logistics sledování zásilek: HHW…YQ a dopravci
description: Sledování zásilky HuaHan Logistics (华翰物流) z Číny: tvar čísla HHW…YQ, význam anglických a čínských stavů, kdo doručuje v ČR, celní poplatky a kontakt.
slug: huahan-logistics-sledovani-zasilek
published: 2026-11-28
updated: 2026-11-28
---

HuaHan Logistics (华翰物流, také HuaHan Express nebo 华翰速递) je čínský dopravce, který letecky posílá balíky z Číny a v cílové zemi je předává místnímu dopravci, jenž zásilku doručí. Pokud obchod poslal tvoji objednávku přes HuaHan, hledej číslo začínající na `HHW` a končící na `YQ` a vlož ho do pole výše. Vlastní sledování HuaHan funguje na webu hh-exp.cn a dokáže uvést místního dopravce i jeho vlastní číslo pro tvůj balík.

## Sledovací číslo HuaHan: jak vypadá

Peek zná přepravní čísla HuaHan jako `HHW`, dvě písmena, deset číslic a `YQ`, celkem 17 znaků. S vymyšlenými číslicemi:

| Číslo | Co to je |
| --- | --- |
| `HHWCN4829105736YQ` | `HHW`, dvě písmena, deset číslic a `YQ`: přepravní číslo HuaHan. |
| `HHWCN482910573YQ` | Jen devět číslic: neodpovídá tvaru HuaHan. Zkontroluj, zda nechybí číslice. |
| `WNBAA4829105736YQ` | Také končí na `YQ`, ale začíná na `WNB`: číslo [WanbExpress](carrier:wanbexpress). |
| `JXCAA4829105736YQ` | `JXC` na začátku: číslo [Juxi Express](carrier:juxi-express). |

Mnoho čínských speditérů končí svá čísla na `YQ` a každý má na začátku vlastní písmena: `HHW` označuje HuaHan a žádný jiný dopravce v katalogu Peeku je nepoužívá. Mezery, pomlčky a malá písmena při vkládání do Peeku nehrají roli.

**Kde číslo najdeš.** Obchod ho uvádí v e-mailu o odeslání nebo na stránce objednávky. Pokud ho nemůžeš najít, podívej se na [kde najít sledovací číslo](guide:find-tracking-number) (v angličtině).

**Druhé číslo.** Sledování HuaHan může zobrazit i vlastní číslo místního dopravce. HuaHan za něj někdy přidá příponu za podtržítkem, kterou číslo místního dopravce nemá: při vyhledávání na webu místního dopravce ji vynech.

## Stavy sledování HuaHan: co znamenají

Sledování HuaHan vypisuje každý sken s časem, textem a místem, nejnovější nahoře. Vlastní skeny HuaHan jsou v angličtině, některé s čínštinou na začátku. V pořadí, jak je uvádí kód Peeku:

| HuaHan uvádí | Co to pro tebe znamená | Peek zobrazí |
| --- | --- | --- |
| `快件电子信息已经收到 Shipment information received`, `Shipment information received` | Obchod poslal HuaHan údaje o zásilce. HuaHan balík ještě nemá. | „Label created“ |
| `快件到达作业中心 Shipment arrived at facility`, `Has entered the warehouse` | Středisko HuaHan v Číně balík přijalo: v záznamech v kódu Peeku v Šen-čenu. | „Received by carrier“ |
| `快件操作完成 Shipment departed from facility` | Zpracováno a na cestě z tohoto střediska. | „In transit“ |
| `Transfer to Origin airport` | Na cestě na letiště. | „In transit“ |
| `Export customs clearance completed` | Vývozní celní řízení na cestě ven je hotové. Nejde o celnici ve tvé zemi. | „In transit“ |
| `Departed from airport of Origin` | Letadlo odletělo. | „In transit“ |
| `Import Customs clearance started` | Zásilku má celnice v cílové zemi. | „At customs“ |
| `Arrived at destination airport` | Letadlo přistálo v cílové zemi. | „In transit“ |
| `Import Customs clearance completed` | Dovozní celnice zásilku propustila. | „In transit“ |
| `Has been given to local carrier` | HuaHan balík předal dopravci, který ho doručí. Dál následují řádky tohoto dopravce. | „In transit“ |

**Řádky místního dopravce.** Po předání přebírá sledování HuaHan skeny místního dopravce. Peek je čte podle kódu kroku od HuaHan, kde existuje, nebo podle jejich znění. Formulace, s nimiž se kód Peeku setkal a které připisuje místnímu dopravci:

| Po předání | Co to pro tebe znamená | Peek zobrazí |
| --- | --- | --- |
| `Ready to Ship, Parcel received and processed` | Místní dopravce balík má. | „In transit“ |
| `The parcel is in the central warehouse waiting to be processed.`, `In Transit, Shipment arrived to sorting centre / Shipment in the sorting centre`, `In Transit, Shipment remained at sorting hub` | V centrálním skladu nebo třídicím středisku místního dopravce, případně tam stále čeká. | „In transit“ |
| `In Transit, Arrived to HU HUB`, `In Transit, Parcel has entered the Linehaul network.` | V uzlu místního dopravce v Maďarsku, poté v přepravě mezi jeho uzly. | „In transit“ |
| `In Transit, Picked Up` (následováno německým `Einrollung` od DPD), `In Transit, Scanned By DPD At parcel delivery centre.`, `In Transit, At parcel delivery centre.` | V doručovacím depu DPD: příjmový sken v depu (nejde o vyzvednutí od tebe), pak jeden sken za každý den, kdy tam balík čeká. | „In transit“ |
| `Out for Delivery, Parcel is at courier, delivery expected soon.` | Na rozvozové trase u kurýra. | „Out for delivery“ |
| `The courier will deliver the parcel soon. Additional details are available in the SAMEDAY app, email or SMS.` | Na rozvozové trase, slovy kurýra [Sameday](carrier:sameday) působícího v Rumunsku a Bulharsku. | „Out for delivery“ |
| `Delivery Attempted`, `Delivery Attempted, Unfortunately we have not been able to deliver your parcel. Pickup parcelshop delivery after first delivery attempt.` | DPD se pokusilo doručit, ale neúspěšně. U delšího řádku míří balík po prvním pokusu na výdejní místo. | „Delivery attempted“ |
| `Awaiting Pickup, Parcel was deposited in Pickup parcel station` | Po pokusu o doručení uložilo DPD balík na výdejním místě k vyzvednutí. | „Ready for pickup“ |
| `The parcel has been successfully delivered.` | Doručeno. | „Delivered“ |

HuaHan řadí skeny místního dopravce pro rozvoz, výdejní místo a doručení pod vlastní kódy a Peek je ukazuje jako „Out for delivery“, „Ready for pickup“ nebo „Delivered“, pokud slova nepojmenovávají jiný krok: HuaHan pod svůj kód pro rozvoz řadí i odjezd USPS z distribučního centra.

**Dva řádky, které matou.** `We will pick up your parcel from the sender soon.` se může objevit hned na začátku, ještě než HuaHan balík v Číně vůbec převzal: jde o oznámení místního dopravce, že o zásilce ví, nikoli o vyzvednutí od tebe. Peek ukáže „Label created“. `The parcel was redirected. As soon as it is in the couriers possession, it will be processed as requested.` znamená, že dopravce zaznamenal požadované přesměrování — také se zapisuje, když je balík ještě v Číně. Balíkem to nepohne a Peek tomuto řádku vlastní stav nepřiřazuje.

**Data, nikoli skeny.** `The instruction data for this shipment have been provided by the sender to DHL electronically.` (někdy bez tečky na konci) je záznam DHL o datech zásilky pro poslední úsek, předaný předtím, než HuaHan balík v Číně přijal, nebo až po odletu. Čínský řádek `货物电子信息已经收到` (elektronická data o zboží přijata) je záznam dat objednávky a může se objevit i poté, co už HuaHan balík má. Ani jeden není pohyb: Peek ukáže „Label created“, nebo ponechá dosavadní stav.

**Jeden sken, dva řádky.** Sledování HuaHan dokáže vypsat jeden sken místního dopravce dvakrát ve stejný čas: slovy toho dopravce i s místem a bez místa v obecných slovech HuaHan, jako `Delivered, Parcel was delivered successfully`. Peek ponechá řádek s místem.

**Poštou.** HuaHan může balík poslat také přes China Post. Jeho sledování pak přenáší skeny China Post v čínštině, s poštou v závorkách 【】, a později skeny cílové pošty tak, jak je China Post přebírá. Peek je zobrazuje v čínštině tak, jak je HuaHan předává:

| HuaHan přenáší | Co to pro tebe znamená | Peek zobrazí |
| --- | --- | --- |
| `中国邮政已收取邮件` | China Post převzala balík od HuaHan: odtud putuje poštou. | „In transit“ |
| `邮件已在【…】完成分拣，准备发出`, `邮件到达【…】`, `邮件离开【…】，正在发往【…】`, `邮件离开【…】，正在发往下一站` | Vytříděno, dorazilo nebo opustilo provozovnu China Post v Číně uvedenou v 【】. | „In transit“ |
| `送交出口海关` | Předáno vývozní celnici v Číně. Nejde o celnici ve tvé zemi. | „At customs“ |
| `出口海关/放行` | Vývozní celnice zásilku propustila. | „In transit“ |
| `已交承运商运输` | Předáno aerolince, která balík přepraví do zahraničí. | „In transit“ |
| `到达寄达地`, `到达寄达地处理中心`, `到达境外经转局` | Dorazilo do cílové země, do zpracovatelského centra její pošty nebo do tranzitní pošty po cestě. | „In transit“ |
| `境外进口海关留存待验(等待提交给边境机构/安全部门)` | Zadrženo pro dovozní celnici v cílové zemi; v závorce stojí, že čeká na předložení pohraničním či bezpečnostním orgánům. | „At customs“ |
| `境外进口互换局留存`, `送交境外进口海关` | Uloženo na vyměňovací poště cílové pošty, poté předáno dovozní celnici. | „At customs“ |
| `寄达地海关允许进口（无需缴税）` | Dovozní celnice povolila dovoz bez platby cla. | „In transit“ |
| `离开【…】处理中心`, `已到达【…】投递局` | Opustilo zpracovatelské centrum v zemi uvedené v 【】, poté dorazilo na dodací poštu. | „In transit“ |

**Přes Landmark Global.** U některých zásilek přenáší sledování HuaHan anglické řádky [Landmark Global](carrier:landmark-global). První dva se objeví ještě předtím, než HuaHan balík v Číně převezme; ostatní následují po předání do sítě [bpost](carrier:bpost), která doručuje:

| HuaHan přenáší | Co to pro tebe znamená | Peek zobrazí |
| --- | --- | --- |
| `Shipment Data Uploaded` | Záznam Landmark Global o datech odesílatele. Nejde o pohyb. | „Label created“ |
| `Processed` | Zpracování u Landmark Global ve stejnou minutu jako předchozí záznam. Neznamená převzetí balíku, ale oznámení; Peek ponechá předchozí stav. | Předchozí stav |
| `Incoming scan at facility` | Depo balík přijalo po předání do sítě bpost. | „In transit“ |
| `Outgoing scan at facility` | Balík toto depo opustil. Do rozvozu mohou zbývat ještě dny. | „In transit“ |
| `Out for delivery (On the way)` | Na rozvozové trase. | „Out for delivery“ |
| `Delivery attempt (Delivery not possible)` | Pokus o doručení, který se nezdařil. | „Delivery attempted“ |

HuaHan řadí `Outgoing scan at facility` pod svůj kód pro rozvoz, ale text popisuje odjezd z depa, takže Peek zobrazuje „In transit“.

## Kdo doručuje zásilky HuaHan

Po stavu `Has been given to local carrier` doručuje zásilku místní dopravce v cílové zemi a sledování HuaHan dokáže uvést jeho název i číslo.

**Co ukazuje kód Peeku.** Jeden záznam HuaHan ukazuje doručení přes Sameday: jeho řádek při rozvozu odkazuje na aplikaci SAMEDAY. Kód Peeku počítá od HuaHan také s čísly [USPS](carrier:usps): když HuaHan předá dlouhý čárový kód štítku USPS, Peek z něj vytáhne vlastní sledovací číslo USPS. Pro poslední úsek některých zásilek se objednává [DHL](carrier:dhl): výše citovaný datový řádek je jeho záznamem údajů odesílatele. Jiné doručuje DPD: jeho skeny z depa, jako `In Transit, Scanned By DPD At parcel delivery centre.`, najdeš v tabulce výše. U dalších je pro poslední úsek ohlášen [CTT Express](carrier:ctt-express): sledování HuaHan přenáší jeho oznámení `Pendiente de recepción en CTT Express` („čeká na přijetí u CTT Express“) pár minut po příjmovém skenu HuaHan v Číně, v madridském čase, a Peek zobrazí „Label created“. Další balíky jdou přes [Landmark Global](carrier:landmark-global): sledování HuaHan přenáší jeho řádky od záznamu dat až po rozvoz a po předání doručuje síť bpost (viz „Přes Landmark Global“ výše). Mimo tyto případy kód neváže žádného dopravce k určité zemi: Peek se řídí tím, kterého HuaHan uvede pro tvoji zásilku.

**Pokusy o doručení a vyzvednutí.** Pokusy, oznámení a to, kde a jak dlouho nevyzvednutý balík čeká, se řídí pravidly místního dopravce: o změnu doručení žádej přímo jeho s jeho sledovacím číslem. Viz [missed delivery](guide:missed-delivery) a [uncollected parcels](guide:uncollected-parcel) (oboje v angličtině).

**Clo v EU a ČR.** Zboží koupené online mimo EU podléhá od 1. července 2026 clu 3 € za položku u zásilek v hodnotě do 150 € a od 1. listopadu 2026 unijnímu manipulačnímu poplatku 2 € za položku bez ohledu na hodnotu. Položkou se rozumí zboží se stejným sazebním zařazením, popisem a tam, kde se vyžaduje, původem. Peek nemohl ověřit, zda je HuaHan účtuje prodejci předem: zeptej se obchodu, zda byly v ceně. Mimo EU závisí poplatky na dané zemi. Viz [parcels held at customs](guide:customs) (v angličtině).

## Zásilka HuaHan nedorazila nebo se sledování nehýbe

**Číslo nenalezeno.** Pokud HuaHan o čísle žádný záznam nemá, jeho sledování odpoví `没有找到记录` („záznam nenalezen“) a Peek zobrazí „Waiting for HuaHan Logistics“ a kontroluje dál. Pokud HuaHan číslo nezná ani po dvou dnech a nic jiného na něj neodpovědělo, Peek zobrazí „After two days, the carrier still doesn’t know this number. Check it, choose another carrier or delete the parcel.“ Zkontroluj číslo znak po znaku podle zprávy od prodejce: `HHW`, dvě písmena, deset číslic, `YQ`. Pokud se ani po pár dnech nic neobjeví, zeptej se prodejce, zda balík skutečně odeslal.

**Zaseknuto na `Shipment information received`.** Obchod zásilku zaregistroval, ale HuaHan ji ještě nenaskenoval. Zeptej se obchodu, kdy balík předal.

**Zaseknuto na `Import Customs clearance started`.** Zásilku má celnice v cílové zemi. Pokud po tobě chce uhradit dovozní poplatky, viz [parcels held at customs](guide:customs) (v angličtině); zůstává-li tam dlouho, napiš prodejci.

**Nic po `Has been given to local carrier`.** Balík má místní dopravce a jeho vlastní sledování může být napřed před HuaHan. Jeho název a číslo najdeš ve sledování HuaHan, nebo na kartě zásilky v Peeku, pokud Peek toho dopravce zná — jinak se zeptej prodejce, kdo doručuje.

**Bez aktualizace několik dní.** Když zásilka na cestě nemá nový sken čtyři dny, Peek ji označí datem posledního skenu („No tracking update since Mon 12 Oct“) a poznamená, že balíky na dlouhých letech, na celnici nebo o víkendu často mlčí; po třech týdnech bez novinek radí oslovit prodejce. Viz [why tracking stops updating](guide:tracking-not-updating) a [tracking parcels from China](guide:tracking-from-china) (v angličtině).

**Doručeno, ale nic nepřišlo.** Zkontroluj místo uložení a zeptej se sousedů, potom místního dopravce a prodejce. Viz [delivered but not received](guide:delivered-not-received) (v angličtině).

**Ztraceno nebo vráceno.** Přepravu u HuaHan objednal prodejce, takže vrácení peněz nebo nový balík řeš s prodejcem či tržištěm. Viz [lost parcels](guide:lost-parcel) a [returned to sender](guide:returned-to-sender) (v angličtině).

## Kontakt a zákaznický servis HuaHan

Na svém webu ([hh-exp.com/lxwm](https://www.hh-exp.com/lxwm)) uvádí HuaHan infolinku v **Číně**, 400-015-6080, bez zveřejněné provozní doby, a své sídlo v Šen-čenu; e-mail podpory pro příjemce nezveřejňuje. Zásilku u HuaHan objednal prodejce, takže kvůli objednávce piš jemu — a tržišti, pokud prodejce neodpovídá. Samotné doručení řeš s místním dopravcem.

## Sledování zásilky HuaHan v Peeku

[Peek](/) rozpozná HuaHan Logistics okamžitě z čísla `HHW…YQ` („Detected carrier“) a dotazuje se sledování HuaHan jen pomocí celého tohoto čísla: není potřeba účet, PSČ ani telefon. Pokud máš jen číslo objednávky, požádej prodejce o přepravní číslo `HHW…YQ`. Máš-li jen číslo místního dopravce, vlož místo něj to: Peek sleduje více než 4 500 dopravců.

Peek zobrazuje stav, každý sken s místem a cílovou zemi ze záznamu HuaHan. Sledování HuaHan uvádí časy bez časového pásma. Peek čte vlastní skeny HuaHan podle času jejich místa nebo úseku, na kterém balík byl: v Číně podle pekingského času. Skeny místního dopravce po `Has been given to local carrier` čte podle času tvé země, má-li jedno časové pásmo. Každý čas se zobrazuje v časovém pásmu tvého zařízení. HuaHan neuvádí předpokládané datum doručení ani hmotnost, takže Peek nezobrazuje ani jedno.

Když HuaHan uvede místního dopravce, kterého Peek zná, spolu s číslem v jeho tvaru, karta zásilky to napíše (například „Delivery with USPS“, nebo „Announced to USPS, not received yet“, dokud má dopravce jen avízo bez vlastního skenu) a uvede číslo toho dopravce za číslem HuaHan. Peek se pak dotazuje i tohoto dopravce. Pokud HuaHan uvede poštovní číslo (dvě písmena, devět číslic, dvě písmena) bez dopravce známého Peeku, zeptá se Peek pošty cílové země; u jiného čísla jednoho dopravce, na kterého ukazuje jeho tvar. Jakmile ten dopravce ukáže sken alespoň tak čerstvý jako nejnovější sken HuaHan, sleduje Peek zásilku tam a historii HuaHan ponechá. Pokud HuaHan číslo místního dopravce neuvede, zůstává Peek u sledování HuaHan, které skeny tohoto dopravce dál přenáší.

Když sledování HuaHan není dostupné, Peek se místo něj ptá sledovacích služeb a zobrazí historii, kterou mají, v jejich formulacích.

Dokud máš zásilku otevřenou, nebo v den doručení se zapnutými oznámeními, kontroluje Peek až každých 10 minut a od 6:00 místního času v den, kdy jde zásilka do rozvozu, až každé 2 minuty (každých 5, když kontrola otevírá prohlížeč), dokud odpovídá vlastní sledování HuaHan; jinak každou hodinu. Přes sledovací služby je to každou hodinu, po dni ticha každé 3 hodiny a v den doručení za stejné podmínky až každých 15 minut. Jakmile doručení převezme jiný dopravce, řídí se Peek jeho vlastním tempem. Po dvou dnech bez novinek (po 12 hodinách, dokud balík ukazuje „Label created“ nebo „Received by carrier“) kontroluje Peek každou hodinu. Oznámení si můžeš nastavit pro „Every scan“, „Important steps“ nebo „Delivery only“. „Important steps“ pokrývá `Import Customs clearance started`, čínské celní skeny China Post přenášené HuaHan u poštovní linky (např. `送交出口海关` a `送交境外进口海关`), rozvoz, výdejní místo a doručení — nikoli lety ani vlastní vývozní celní skeny HuaHan: vývozní proclení cestou na letiště, `快件从转运仓发出至机场，出口报关中（ The package is shipped from the transfer warehouse to the airport Export customs clearance in progress ）`, je běžný krok před odletem a Peek ho zobrazuje jako „In transit“. Peek za tebe nemůže kontaktovat HuaHan ani prodejce.

## Časté otázky ke sledování HuaHan

### Co je HuaHan Logistics (华翰物流)?

Čínský dopravce, označovaný také jako HuaHan Express nebo 华翰速递, který posílá balíky do zahraničí. V záznamech v kódu Peeku začínají jeho skeny v jeho středisku v Šen-čenu, pokračují letem a celnicí a končí skeny místního dopravce, který zásilku doručí v cílové zemi.

### Kde mohu sledovat balík HuaHan?

V poli výše nebo na vlastním sledování HuaHan na hh-exp.cn. Toto sledování vyžaduje nejprve splnit kontrolu: kliknutí v pořadí nebo jednoduchý součet. Použij celé číslo `HHW…YQ`.

### Co znamená „Has been given to local carrier“ u HuaHan?

Dovozní celnice zásilku propustila a HuaHan ji předal dopravci, který ji doručí ve tvé zemi. Skeny tohoto dopravce následují v historii HuaHan.

### Kdo doručuje zásilky HuaHan v mé zemi?

Místní dopravce, kterého dokáže sledování HuaHan pojmenovat spolu s jeho číslem zásilky. Kód Peeku ukazuje v jedné historii Sameday a skeny depa DPD, čte čísla USPS, zná data DHL a avízo CTT Express pro poslední úsek části zásilek a čte řádky Landmark Global u jiných, kde po předání doručuje síť bpost: zkontroluj sledování HuaHan nebo se zeptej prodejce.

### Koho kontaktovat ohledně nedoručeného balíku HuaHan?

Prodejce, u kterého jsi nakoupil: ten přepravu u HuaHan objednal. Vlastní infolinka HuaHan je v Číně (400-015-6080).

:::sources
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – clo 3 € za položku do 150 € od 1. července 2026, unijní manipulační poplatek 2 € od 1. listopadu 2026
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € za položku bez ohledu na hodnotu, co je položka
- [HuaHan Express: 联系我们 (kontakt, čínsky)](https://www.hh-exp.com/lxwm) – sídlo v Šen-čenu a oficiální linka 400-015-6080
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – názvy, tvar čísla HHW, sledování a kontrolní součet, texty skenů, kódy a jejich fáze, přenášené skeny místního dopravce a duplicitní řádky, text Sameday, skeny depa, pokusy a výdejní místo DPD, avízo CTT Express, čínské skeny China Post a cílové pošty na poštovní lince a jejich fáze, přenášené řádky Landmark Global, předání síti bpost a jejich fáze, název a číslo místního dopravce s příponou za podtržítkem a čárové kódy USPS, časová pásma, cílová země, bez data doručení či hmotnosti, předání
:::
