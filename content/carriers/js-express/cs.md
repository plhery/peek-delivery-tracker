---
title: JS Express sledování zásilek: čísla JSE…YQ a stavy
description: Sledujte zásilku JS Express (急速国际) z Číny: jak vypadá číslo JSE…YQ, co znamenají jeho stavy a kdo doručuje v USA, Kanadě i dalších zemích.
slug: js-express-sledovani-zasilek
published: 2026-11-28
updated: 2026-11-28
---

JS Express (急速国际, také JS EXP) je čínská logistická společnost pro přeshraniční e-shopy, kterou provozuje 急创速供应链管理（苏州）有限公司 v Su-čou. Pokud obchod poslal vaši objednávku přes JS Express, číslo začíná na JSE a končí na YQ: vložte jej do pole výše. JS Express zajistí přepravu z Číny a ve vaší zemi zásilku doručí místní kurýr: v její historii se objevuje USPS, GOFO nebo SpeedX ve Spojených státech, Straightship v Kanadě, Yodel ve Spojeném království, DHL, iMile nebo J&T Express v Německu, Colissimo ve Francii, CTT Express ve Španělsku a na některých linkách FedEx.

## Sledovací číslo JS Express: jak vypadá

Nákladní listy JS Express tvoří JSE, dvě písmena, deset číslic a YQ, celkem 17 znaků. S vymyšlenými číslicemi:

| Číslo | Co to je |
| --- | --- |
| `JSEAA4829105736YQ` | JSE, dvě písmena, deset číslic a YQ: nákladní list JS Express. |
| `JSEAA482910573YQ` | Jen devět číslic: neodpovídá tvaru JS Express. Zkontrolujte, zda nechybí číslice. |
| `JXCAA4829105736YQ` | Také končí na YQ, ale začíná na JXC: číslo [Juxi Express](carrier:juxi-express). |

Stejnou strukturu s vlastními počátečními písmeny používají i další čínští speditéři: FEI pro 17FEIA, WNB pro WanbExpress, SFC pro SFC, CJP pro CJ Packet. JS Express označuje předpona JSE. Mezery a malá písmena v Peeku nehrají roli. Nulu zadanou místo druhého ze dvou písmen přečte jako O a písmeno O mezi deseti číslicemi jako nulu. Sledovač JS Express potřebuje celé číslo: jeho část nenajde nic.

**Kde jej najít.** Obchod číslo předá v e-mailu o odeslání nebo na stránce objednávky. JS Express uvádí, že úzce spolupracuje s platformami Amazon, Walmart, Shopify, eBay, AliExpress, Wish a Shopee. Pokud jej nemůžete najít, viz [jak najít sledovací číslo](guide:find-tracking-number) (v angličtině).

**Žádný odkaz ke sdílení.** Sledovací stránky JS Express přijímají číslo ve formuláři, takže žádný odkaz neotevře přímo konkrétní balík: zkopírujte číslo. Anglická stránka „Express Query“ odpovídá okamžitě; čínská nejprve vyžaduje opsání obrázkového kódu (验证码).

## Vysvětlení stavů sledování JS Express

Stránka JS Express vypisuje každý záznam s časem, místem v 【hranatých závorkách】 a textem, od nejnovějšího nahoře s označením „Latest“. Texty jsou převážně anglicky. V pořadí, v jakém jimi zásilka prochází:

| JS Express uvádí | Co to znamená pro vás | Peek zobrazuje |
| --- | --- | --- |
| `Electronic notification of order creation received.` | Obchod poslal JS Express údaje o objednávce. JS Express zásilku ještě nemá. | „Label created“ |
| `PRE-ADVICE` | Partner má údaje o zásilce ještě předtím, než ji má sklad JS Express. | Fáze, kterou zásilka už měla |
| `Item has been collected` | Sklad JS Express v Číně zásilku převzal: její první skutečný záznam. | „Received by carrier“ |
| `Item inbound in sorting center.`, `Item outbound in sorting center.` | Zpracováno v třídicím centru JS Express v Číně. | „In transit“ |
| `Domestic allocation has begun`, `Under domestic allocation` | Stále ve skladu JS Express v Číně, po třídění a před odjezdem. | „In transit“ |
| `Waybill Generated for` | Kurýr ve vaší zemi vytvořil vlastní nákladní list, zatímco sklad JS Express v Číně zásilku stále má. | „Label created“, nebo fáze, kterou zásilka už měla, obvykle „In transit“ |
| `Arrive at transit center` | V tranzitním centru JS Express v Číně, mezi tříděním a odletem. | „In transit“ |
| `Item departed from origin.`, `Arrive at international airport to abroad` | Na cestě ven a poté na letišti v Číně. | „In transit“ |
| `Arrived at export center`, `Exports Center Sorting and Packing`, `Arrived at Origin Port` | Na některých linkách mezi předchozími dvěma: exportní centrum v Číně zásilku roztřídí a zabalí, pak dorazí do výstupního přístavu nebo na letiště v Číně. | „In transit“ |
| `Shipment arrived at facility and measured.`, `Depart from facility to service provider.` | Zařízení v Číně balík zváží a změří a odešle jej dál leteckému přepravci. | „In transit“ |
| `Start on-site transportation and shipment` | Na letišti v Číně po příjezdu: začala manipulace na místě před uvolněním čínskou vývozní celnicí. | „In transit“ |
| `Released From Export Customs` | Čínské vývozní celní řízení je dokončeno. Nejde o celnici ve vaší zemi. | „In transit“ |
| `DEPART ORIGIN AIRPORT`, `Departure from the original airport`, `Item Flight departure`, `Initial landing`, `Connecting flights flight takeoff` | Letadlo odletělo, někdy s mezipřistáním a navazujícím letem. | „In transit“ |
| `ARRIVED AT DESTINATION AIRPORT`, `Arrival to the destination airport`, `Item Flight landing`, `NOA-Notification of arrival from GHA` | Přistálo ve vaší zemi. NOA je hlášení letištního pozemního operátora o příletu. | „In transit“ |
| `The customs clearance agent is queuing up to pick up the goods` | Celní deklarant ve vaší zemi čeká u nákladového terminálu letiště na vyzvednutí zásilky: ještě ji nemá ani deklarant, ani celnice. | „In transit“ |
| `The customs clearance agent has picked up the goods` | Deklarant vyzvedl zásilku z nákladového terminálu k dovoznímu celnímu řízení. | „At customs“ |
| `The goods have been picked up abroad` | Deklarant ve vaší zemi převzal zásilku z letiště. Nejde o nový svoz u prodejce. | „In transit“ |
| `Customs clearance agent received the shipment in destnation country.` | Celní deklarant ve vaší zemi převzal zásilku po letu k dovoznímu celnímu řízení. „destnation“ je původní pravopis JS Express. | „At customs“ |
| `Inbound to customs temporary warehouse` | V celním skladu, čeká na dovozní celní řízení. | „At customs“ |
| `Released From Import Customs`, `Released from customs: customs cleared.` | Celnice ve vaší zemi ji propustila. | „In transit“ |
| `Depart from the destination distribution center.` | Distribuční centrum ve vaší zemi zásilku po proclení posílá dál. | „In transit“ |
| `In transit, it's progressing through post network` | Pošta ve vaší zemi zásilku přepravuje svou sítí před samotným rozvozem. | „In transit“ |
| `collection by overseas warehouse` | Zahraniční sklad JS Express ve vaší zemi zásilku po celním řízení převzal. | „In transit“ |
| `Load cargo overseas`, `Vehicles depart from` | Tento sklad zásilku naloží pro kurýra a vůz odjede z místa uvedeného u záznamu. | „In transit“ |
| `Delivery to local courier`, `Last Mile Delivery` | Předáno kurýrovi, který doručuje ve vaší zemi, ještě před jeho prvním skenem. Ještě nebylo doručeno vám. | „In transit“ |
| `Delivery in progress` | Na některých trasách: dopravce ve vaší zemi má zásilku po příchodu na jeho stanici, ale ještě před rozvozem. Navzdory slovům zatím není na rozvozu. | „In transit“ |
| `到达待取` | Vlastní stav JS Express v čínštině: dorazilo, čeká na vyzvednutí. Jako místo je uvedena jen země. | „Ready for pickup“ |
| `The delivery address is incorrect. Please ask the recipient to contact their local area?` | Vlastní upozornění JS Express po neúspěšném doručení kurýrem: adresa je nejasná nebo chybná. Vyzývá vás ke kontaktu s místní pobočkou, jejíž telefonní číslo stránka JS Express uvádí; Peek telefonní čísla vynechává. | „Needs attention“ |

**Záznamy místního kurýra.** Po `Delivery to local courier` historie pokračuje slovy doručujícího kurýra. V USA jsou to záznamy USPS: `Accepted at USPS Origin Facility` nebo `Accepted at USPS Facility` (jeho první sken, nikoli nová zásilka), `Out for Delivery`, `Delivered, Front Door/Porch`. Záznamy SpeedX přicházejí bez názvu společnosti: `Processing at Transit Facility`, `In transit to destination facility`, `Arrival at Destination Facility` a `Processing at Designated Facility`, vše „In transit“. Bez názvu přicházejí i záznamy jiného amerického partnera: `Arrived at Origin Hub` (jeho první uzel u letiště příletu), `Departed Destination Hub` a `Arrived At Warehouse` (sklad pro poslední míli před rozvozem), vše „In transit“. V Kanadě se u Straightship objevuje `Package is received at a Straightship station`, poté `Package will be delivered before 9:00pm`, tedy denní trasa řidiče („Out for delivery“: následuje doručení nebo pokus o něj) a `Delivered` s městem v závorce. V Německu chodí záznamy DHL v angličtině, někdy s tečkou na konci: `The instruction data for this shipment have been provided by the sender to DHL electronically`, jeho datový záznam („Label created“, případně „In transit“, pokud před ním už byl sken), poté `The shipment is in transit to DHL` („In transit“); malá zásilka může dostat také německé `Die Sendung wurde im Briefzentrum bearbeitet.` z poštovního centra DHL („In transit“). Řidič iMile může trasu ukončit hláškou `Route not completed` („Delivery attempted“). Záznamy J&T Express Germany přicházejí v němčině s místy uvnitř závorek 【】: `【】【】Ihr Sendung wurde abgeholt.` je jeho první sken po letu a celním řízení; následují `【】Die Sendung hat【】verlassen. Weiterversand nach【】` (opustila jedno pracoviště směrem k dalšímu) a `【】Die Sendung ist bei【】 angekommen.` (v uzlu nebo v doručovacím depu před rozvozem), vše „In transit“. Kde doručuje FedEx, je `Shipment information sent to FedEx` jeho štítek zapsaný ještě předtím, než má zásilku sklad JS Express („Label created“); následují `Arrived at FedEx location`, `Left FedEx origin facility` a `On the way` („In transit“). V Maďarsku následují záznamy kurýra `Arrived to HU HUB`, `Linehaul Transit` (přesun z jeho uzlu do třídicího centra) a `Shipment arrived to sorting centre / Shipment in the sorting centre`, vše „In transit“. Ve Španělsku španělský záznam CTT Express `Enviado` znamená, že balík po převzetí poslal dál před samotným rozvozem („In transit“).

**Kurýr bez názvu.** Na některých linkách JS Express přebírá záznamy doručujícího kurýra velkými písmeny bez uvedení jeho názvu nebo země. `SHIPMENT RECEIVED`, `PROCESSED AT HUB` a `SHIPMENT PROCESSED` znamenají příjem zásilky v uzlu kurýra a `DEPARTED FROM DEPOT` odjezd směrem k doručovacímu depu, vše „In transit“. `RECEIVED AT LOCAL DEPOT` označuje toto depo před každým rozvozem a znovu po neúspěšném pokusu: jde o vlastní depo kurýra, nikoli o výdejní místo, takže Peek zachovává stav předchozího řádku. `DELIVERY ATTEMPTED: 1ST DELIVERY ATTEMPT`, `DELIVERY ATTEMPTED: 2ND DELIVERY ATTEMPT` a `DELIVERY ATTEMPTED: 3RD DELIVERY ATTEMPT` jsou neúspěšné pokusy o doručení („Delivery attempted“). U třetího kurýr žádá o domluvení doručení; `SHIPMENT REDIRECTED: ON CONSIGNEE'S REQUEST` pak znamená, že zásilka směřuje na vámi domluvené doručení („In transit“). `DELIVERY EXCEPTION` je problém v uzlu kurýra před rozvozem a `DELIVERY EXCEPTION, WAIT FOR INFORMATION CONFIRMATION` po `ARRIVE AT THE WAREHOUSE` („In transit“) znamená, že kurýr zásilku drží do potvrzení údajů: Peek oba zobrazuje jako „Needs attention“. Pokud Peek neukazuje číslo kurýra, zeptejte se prodejce, kdo doručuje a co kurýr potřebuje.

**Řádky, které nejsou skeny.** `SHIPPING INFORMATION RECEIVED`, `SHIPMENT CREATED`, `Data Received`, hláška USPS `Shipping Label Created, USPS Awaiting Item`, `Package information is created in the system` a v evropském uzlu `Shipment Data Uploaded` následované `Processed` jsou jen datové záznamy partnera, někdy celé dny předtím, než balík má. `Consignee Contacted`, zapsané ve chvíli, kdy doručující kurýr zaznamená neúspěšný pokus, označuje jeho hovor nebo zprávu vám: ani to nemění stav.

**Časy mimo pořadí.** Časy nenesou časové pásmo a míchají různé hodiny: pekingský čas v Číně, místní čas na letištích a u většiny kurýrů a UTC u Straightship, což je o několik hodin napřed oproti kanadskému času. Řiďte se pořadím řádků, ne časy. V Peeku zobrazují německé řádky J&T Express Germany časy, které uvádí vlastní sledovač J&T Express Germany.

## Kdo doručuje zásilky JS Express

**Spojené státy.** Historie JS Express ukazuje doručení přes [USPS](carrier:usps) se skeny USPS až do doručení, přes [GOFO](carrier:gofo) se skeny GOFO od třídicího centra po rozvoz, nebo přes [SpeedX](carrier:speedx), jehož skeny neuvádějí název. Jakmile má balík USPS, přijímá USPS žádost Missing Mail search od odesílatele nebo příjemce po 7 dnech od podání.

**Kanada.** Část zásilek JS Express doručuje kanadský kurýr [Straightship](carrier:straightship); jeho skeny se zobrazují v historii JS Express.

**Německo, Francie a Španělsko.** Evropská linka JS Express obsluhuje země jako Spojené království, Francii, Německo, Itálii, Španělsko, Švýcarsko a Dánsko, s výjimkou některých odlehlých PSČ. Pro Německo JS Express jmenuje DPD a další kurýry pro poslední míli a uvádí, že přes 98 % zásilek je doručeno do 8 pracovních dnů; tamní historie ukázala skeny [DHL Paket](carrier:dhl), [iMile](carrier:imile) z německého centra a [J&T Express Germany](carrier:j-and-t-de) v němčině. Ve Francii ukázala skeny [Colissimo](carrier:la-poste) ve francouzštině a ve Španělsku [CTT Express](carrier:ctt-express).

**Spojené království, Irsko, Austrálie a jinde.** Na některých linkách historie ukazuje skeny [FedEx](carrier:fedex). Ve Spojeném království historie JS Express obsahuje skeny Yodelu; dva z nich, `DESPATCHED AND ON ROUTE TO YODEL` a `PARCEL PICKED UP`, přicházejí ještě v době, kdy je zásilka ve skladu JS Express v Číně. Pro Austrálii JS Express jmenuje Australia Post a Fastway, nyní [Aramex Australia](carrier:aramex-au), pod 4 kg, a DPEX nad tuto hmotnost. Záznam JS Express může obsahovat číslo místního kurýra, ale ne jeho název, a stránka JS Express nezobrazuje ani jedno. Peek kurýra pojmenuje, když toto číslo odpovídá jednomu dopravci ve vaší zemi, a na [poštovní číslo](guide:tracking-number-formats) (dvě písmena, devět číslic, dvě písmena) se zeptá pošty vaší země; více níže. Pokud Peek žádného kurýra neuvede, zeptejte se prodejce, kdo doručuje.

Počet pokusů o doručení, oznámení ve schránce a výdejní místa se řídí pravidly místního kurýra: JS Express žádná nezveřejňuje. Řádek `到达待取` uvádí jen zemi, proto zkontrolujte sledování kurýra nebo se zeptejte prodejce, kde zásilka čeká.

## Zásilka JS Express nedorazila nebo sledování zamrzlo

**„未获取到任何数据,请提供有效单号!“** Anglická stránka JS Express tímto čínským hlášením odpovídá na neznámé číslo: „žádná data nenalezena, zadejte platné číslo nákladního listu“. `No track data for now!` znamená, že zatím nejsou žádné skeny. Peek u obou zobrazí „Waiting for JS Express“ a dál kontroluje. Pokud JS Express číslo ani po dvou dnech nezná a nic jiného na něj neodpovědělo, Peek zobrazí „After two days, the carrier still doesn’t know this number. Check it, choose another carrier or delete the parcel.“ Zkontrolujte číslo znak po znaku; pokud po několika dnech nic nepřijde, zeptejte se prodejce, zda zásilku odeslal.

**Zaseknuté na `Electronic notification of order creation received.`** JS Express balík ještě nevyzvedl. Zeptejte se obchodu, kdy jej předal.

**Zaseknuté na `Inbound to customs temporary warehouse`.** Čeká na celní řízení ve vaší zemi. Na některých trasách řádky znějí `Start Custom clearance Process` a poté `customs clearance completed`; dřívější `Under customs clearance, please wait patiently.` je čínské vývozní celní řízení na letišti odletu, takže jej Peek zobrazuje jako „In transit“. JS Express prodává svou evropskou linku i standardní americkou linku s předplacenými poplatky (DDP): dovozní daně vybírá od obchodu předem, takže na těchto linkách by po vás neměly být požadovány. Pokud kurýr přesto platbu požaduje, ověřte si to před zaplacením u prodejce. V EU se od 1. listopadu 2026 platí manipulační poplatek 2 € za druh zboží; platí jej prodejce nebo doručující společnost a může jej přenést dál. Viz [zásilky zadržené celnicí](guide:customs) (v angličtině); pokud tam balík leží celé dny, dejte vědět prodejci. Více v článcích [proč se sledování neaktualizuje](guide:tracking-not-updating) a [sledování zásilky z Číny](guide:tracking-from-china) (v angličtině).

**Nic po `Delivery to local courier`.** Zásilku má místní kurýr. Pokud Peek zobrazí číslo kurýra, zkontrolujte jej na jeho sledování; pokud ne, zeptejte se prodejce, kdo doručuje.

**Doručeno, ale nic nedorazilo.** Nejprve se obraťte na místního kurýra (v USA na USPS), poté na prodejce. Na americké lince JS Express pomáhá prověřit doručení, které kurýr uvádí, ale vy jste jej nedostali, nevyplácí však žádnou náhradu, takže rozhodují podmínky prodejce. Viz [doručeno, ale nedorazilo](guide:delivered-not-received) (v angličtině).

**Ztraceno, poškozeno nebo vráceno.** Zákazníkem JS Express je prodejce. Podle podmínek JS Express podává zákazník reklamaci písemně do 30 dnů od převzetí zásilky JS Expressem a pojistné nároky uplatňuje ze svého účtu. Požádejte proto včas obchod nebo tržiště o vrácení peněz či nový balík. Nedoručitelnou zásilku (nikdo doma, odmítnuto, chybná adresa, celnice) pošlou za poplatek zpět, pokud o to odesílatel požádá, nebo ji zlikvidují; na své americké lince JS Express vrácenou zásilku nevrací do Číny ani ji znovu neposílá. Na některých trasách znamenají `Refused` a poté `Returned`, že balík byl při rozvozu odmítnut a vrátil se do třídicího centra kurýra, ještě není zpět u prodejce: Peek zobrazí „Needs attention“. Viz [ztracené zásilky](guide:lost-parcel) a [vráceno odesílateli](guide:returned-to-sender) (v angličtině).

## Telefonní číslo a zákaznický servis JS Express

Podle čínské kontaktní stránky JS Express ze dne 30. října 2026 společnost uvádí tyto kontakty. Anglická kontaktní stránka je starší a stále uvádí jiná čísla a adresy.

- **Horká linka**: 400-0353-518, čínské servisní číslo uváděné i pro mezinárodní dotazy. JS Express nezveřejňuje provozní hodiny a neuvádí, zda funguje ze zahraničí.
- **QQ a WeChat**: firemní QQ 4000353518 a oficiální účet WeChat js-express.
- **Stížnosti**: formulář na anglické stránce pro stížnosti a stejná horká linka.
- **Pobočky**: centrála a sklad v Su-čou, další sklady po Číně a pobočky sítě v Hongkongu a v kalifornském Walnutu. Jediným telefonním číslem americké kanceláře je čínská horká linka.

Reklamace JS Express vyřizuje přes prodejce, který zásilku objednal. Ohledně objednávky kontaktujte prodejce; ohledně doručení místního kurýra.

## Sledování zásilky JS Express v aplikaci Peek

[Peek](/) rozpozná číslo JS Express podle tvaru: JSE, dvě písmena, deset číslic a YQ, který v jeho katalogu nepoužívá žádný jiný dopravce, a dotazuje se podle něj sledovače JS Express. Pokud máte jen číslo místního kurýra, vložte místo toho je: Peek sleduje více než 4 500 dopravců.

Peek zobrazuje historii z anglické stránky JS Express, každý sken s jeho místem a cílovou zemi, kterou tato stránka vynechává. `到达待取` se zobrazí jako „Arrived, awaiting collection“. Telefonní čísla se odstraňují; jméno osoby, která zásilku podepsala, zůstává. Peek vynechává fotografie z doručení a číslo objednávky z obchodu, které může záznam JS Express obsahovat. Jako očekávané doručení zobrazuje den odhadu JS Express; jakmile je zásilka na rozvozu, včetně řádku Straightship `Package will be delivered before 9:00pm`, nezobrazuje žádný den, protože „Out for delivery“ už znamená dnes. Řádky, které nejsou skeny, nemění stav.

Peek čte řádky v Číně v pekingském čase, řádky Straightship v UTC a německé řádky J&T Express Germany s posunem uvedeným u každého řádku, u ostatních určí hodiny podle místa nebo cílové země a všechny je zobrazí v časovém pásmu vašeho zařízení, od nejnovějšího.

Když číslo kurýra odpovídá jednomu dopravci působícímu ve vaší zemi — například čárovému kódu USPS v USA nebo číslu Straightship v Kanadě — Peek zobrazí „Delivery with“ s tímto dopravcem a jeho číslem a zeptá se i jeho; mezinárodní poštovní číslo jde na poštu vaší země. Dokud má tento dopravce jen avízo zásilky bez vlastního skenu, zobrazí se místo toho „Announced to …, not received yet“. Jakmile tento dopravce ukáže sken alespoň tak čerstvý jako nejnovější od JS Express, sleduje Peek zásilku u něj, zobrazuje obě čísla a uchovává skeny JS Express. Do té doby zůstává u JS Express, jehož historie často přenáší i skeny kurýra.

Dokud máte zásilku otevřenou, nebo v den doručení se zapnutými oznámeními, kontroluje Peek až každých 10 minut a od 6:00 místního času v den, kdy balík vyjede na rozvoz, až každé 2 minuty (každých 5 minut, když musí sledovač JS Express otevřít v prohlížeči), dokud vlastní sledovač JS Express odpovídá; jinak každou hodinu. Přes sledovací služby je to každou hodinu, po klidném dni každé 3 hodiny a v den doručení za stejné podmínky až každých 15 minut. Když doručení převezme jiný dopravce, řídí se Peek tempem doručujícího dopravce. S číslem SpeedX pro zásilku do USA ji Peek dokáže sledovat u [SpeedX](carrier:speedx). Po dvou dnech bez zpráv (12 hodin, dokud zásilka stále zobrazuje „Label created“ nebo „Received by carrier“) kontroluje Peek každou hodinu. Může vás upozorňovat na každý sken, jen na důležité kroky (celnice, problémy, „Ready for pickup“, rozvoz, doručení a vrácení) nebo na „Delivery only“. Za vás ale nemůže kontaktovat JS Express ani prodejce.

## Otázky ke sledování JS Express

### Co je JS Express (急速国际)?

Čínská logistická společnost pro přeshraniční online prodejce, provozovaná firmou 急创速供应链管理（苏州）有限公司. Uvádí, že vznikla v roce 2010 v Hongkongu a v roce 2011 otevřela základnu v Su-čou.

### Kde mohu sledovat zásilku JS Express?

V poli výše, nebo na anglické stránce „Express Query“ společnosti JS Express na js-exp.com. Její čínská stránka nejprve požaduje opsání obrázkového kódu.

### Co znamená 未获取到任何数据 při sledování JS Express?

„Žádná data nenalezena“: sledovač JS Express číslo nezná kvůli překlepu nebo proto, že zásilku ještě neobdržel. Ověřte si jej u prodejce.

### Kdo doručuje zásilky JS Express v mé zemi?

Kurýr ve vaší zemi. Historie JS Express ukazuje doručení přes USPS, GOFO nebo SpeedX ve Spojených státech, Straightship v Kanadě, Yodel ve Spojeném království, DHL, iMile nebo J&T Express v Německu, Colissimo ve Francii, CTT Express ve Španělsku a na některých linkách FedEx. Jinde se zeptejte prodejce, která společnost doručuje.

### Koho kontaktovat kvůli ztracené zásilce JS Express?

Prodejce, u kterého jste objednali: on zásilku u JS Express objednal a podává reklamace. Horká linka JS Express, 400-0353-518, je čínské číslo.

:::sources
- [JS Express: 急速国际 (hlavní stránka)](https://www.js-exp.com/) – provozující společnost
- [JS Express: Introduction](https://www.js-exp.com/EN/index.php?page=6) – Hongkong 2010, Su-čou 2011, přeshraniční e-commerce
- [JS Express: 企业介绍 (představení)](https://www.js-exp.com/index.php?page=6) – Su-čou 2011, pobočky, platformy
- [JS Express: Express Query](https://www.js-exp.com/EN/index.php?page=19) – anglický sledovač, co zobrazuje, jeho odpovědi
- [JS Express: 快件查询 (sledování)](https://www.js-exp.com/index.php?page=19) – obrázkový kód
- [JS Express: Insurance Claims](https://www.js-exp.com/EN/index.php?page=29) – reklamace zákazníka, 30 dní, nedoručitelné zásilky
- [JS Express: 投保理赔 (pojistné nároky)](https://www.js-exp.com/index.php?page=29) – 30 dní od převzetí JS Expressem, nároky z účtu zákazníka
- [JS Express: Complaint](https://www.js-exp.com/EN/index.php?page=32) – formulář, horká linka
- [JS Express: Contact Us](https://www.js-exp.com/EN/index.php?page=35) – horká linka, QQ, WeChat, starší čísla a adresy
- [JS Express: 联系我们 (kontakt)](https://www.js-exp.com/index.php?page=35) – horká linka pro mezinárodní dotazy, QQ, WeChat, kanceláře
- [JS Express: 国际专线 (mezinárodní linky)](https://www.js-exp.com/index.php?catid=13) – německá linka: DPD a další, přes 98 % do 8 pracovních dnů; australská linka: Australia Post, Fastway, DPEX
- [JS Express: 急速美国专线 (linka USA)](https://www.js-exp.com/index.php?aid=27) – zaplacená cla (DDP), vrácené zásilky se neposílají zpět do Číny ani znovu nedoručují, doručeno ale nedorazilo
- [JS Express: 急速欧洲专线 (evropská linka)](https://www.js-exp.com/index.php?aid=35) – země, zaplacená cla (DDP), odlehlá PSČ
- [Aramex Australia: About Aramex Australia](https://www.aramex.com.au/why-aramex/about-us/) – Fastway se stal Aramex Australia
- [Revenue: Union Handling Fee press release](https://www.revenue.ie/en/corporate/press-office/press-releases/2026/pr-092926-handling-fee.aspx) – 2 € za druh zboží od 1. listopadu 2026, platí prodejce nebo doručující společnost, lze přenést dál
- [USPS: Missing Mail and Lost Packages](https://www.usps.com/help/missing-mail.htm) – odesílatelé nebo příjemci, od 7 dnů
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – tvary čísel, texty skenů a fáze, časy (řádky J&T Express Germany od 9. listopadu s uvedeným posunem), skeny USPS, GOFO, SpeedX, Straightship, Yodel, DHL, iMile, J&T Express Germany, FedEx, Colissimo a CTT Express, skeny nejmenovaného kurýra z uzlu, depa, neúspěšných pokusů, přesměrování a výjimek, skeny z uzlu nejmenovaného amerického partnera, německá centra iMile, záložní prohlížeč, celnice, odmítnutí a vratky, číslo kurýra, co Peek zobrazuje a jak zásilku předává
:::
