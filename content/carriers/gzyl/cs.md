---
title: Sledování GZYL: číslo YL…YQ, stavy, clo a kontakt
description: Sleduj balík GZYL (广州云链, Guangzhou Yunlian) z Číny: formát YL…YQ, význam stavů, proč může Delivered předbíhat doručení, celní poplatky EU a kontakt.
slug: gzyl-sledovani-zasilek
published: 2026-11-27
updated: 2026-11-27
---

GZYL (广州云链, Guangzhou Yunlian, 广州云链货运代理有限公司) je spediční společnost v čínském Kuang-čou (Guangzhou), jejíž přepravní čísla mají tvar `YL`, deset číslic a `YQ`. Vlož své číslo `YL…YQ` do pole výše, aby Peek vyfiltroval předem zapsané předpovědi: vlastní tracker GZYL (otevíraný přes `小包查询` na čínském webu `yunlianhouyun.com`, na holé IP adrese bez domény) umí předem zapsat celou trasu zásilky, takže řádek `Delivered` tam nemusí být skutečný sken doručení.

## Sledovací číslo GZYL: jak vypadá

Přepravní čísla GZYL tvoří `YL`, deset číslic a `YQ` — celkem 14 znaků. Příklady s vymyšlenými číslicemi:

| Číslo | Co znamená |
| --- | --- |
| `YL4829105736YQ` | `YL`, deset číslic a `YQ`: formát přepravních listů GZYL. Peek ho ověří v trackeru GZYL. |
| `YL7384829105YQ` | `YL738`, sedm číslic a `YQ`: podle tohoto tvaru Peek pozná GZYL okamžitě. |
| `YL482910573YQ` | Pouze devět číslic: není to formát GZYL. Zkontroluj, zda nechybí číslice. |
| `GZXH4829105736YQ` | `GZXH`, deset číslic a `YQ`: [Xinghe Freight](carrier:xinghe-freight) (星和货运), jiný spediter využívající stejný tracker GZYL. |
| `JXCAA4829105736YQ` | Také končí na `YQ`, jako mnoho čínských speditérů, ale začíná na `JXC`: číslo [Juxi Express](carrier:juxi-express). |

Mezery ani malá písmena při vkládání do Peeku nevadí.

**Kde číslo najdeš.** Zásilku u GZYL objednává prodejce, který ti číslo předá v potvrzení o odeslání nebo na stránce objednávky. Pokud ho nemůžeš najít, zeptej se prodejce.

## Význam sledovacích stavů GZYL

GZYL píše své kroky v angličtině včetně překlepů. Protože rozhraní Peeku není v češtině, hlavní etapy Peeku se zobrazují také anglicky: «Label created» (štítek vytvořen), «Received by carrier» (přijato dopravcem), «In transit» (na cestě), «At customs» (na celnici) a «Delivered» (doručeno). V obvyklém pořadí:

| GZYL píše | Co to znamená pro tebe | Peek ukazuje |
| --- | --- | --- |
| `Order information received` | Prodejce poslal GZYL údaje k objednávce. GZYL balík fyzicky ještě nemá. | «Label created» |
| `Arrive at the cross border receiving point` | GZYL převzala balík: první sken v jejích rukou. | «Received by carrier» |
| `Goods leave the operation center` | Balík opustil operační centrum GZYL. | «In transit» |
| `Arrive at the international airport` | Na letišti, čeká na svůj let. | «In transit» |
| `Departing from the airport` | Let odletěl. | «In transit» |
| `Item arrives at destination airport` | Přistálo v cílové zemi. | «In transit» |
| `local carrier Agent Import customs clearance` | Zástupce v tvé zemi provádí dovozní celní odbavení. | «At customs» |
| `The import customs keep it for inspection` | Celní úřad v tvé zemi balík kontroluje. | «At customs» |
| `Customs clearance completed` | Procleno a jede dál. | «In transit» |
| `Internationaliy Package received at processing center` | Zpracovatelské centrum v tvé zemi převzalo proclený balík. Nejde o nové vyzvednutí u odesílatele. | «In transit» |
| `Leave the email processing center` | Balík opustil toto zpracovatelské centrum. | «In transit» |
| `Arrival sorting, distribution center` | V distribučním centru v tvé zemi, s městem za textem. | «In transit» |
| `Leaving the sorting center` | Opouští třídicí centrum, s městem za textem. | «In transit» |
| `Package is on the way` | Pokračuje po cílové zemi, s městem. Neznamená to rozvoz kurýrem. | «In transit» |
| `Delivered` | Doručení s městem. Může jít o plán, nikoli sken: viz níže. | «Delivered» s poznámkou o pevném rozvrhu, pokud Peek krok nezadrží (viz níže) |

**Přes USA.** Může se objevit i `Los Angeles Processing Center has received it` s městem v závorkách: zpracovatelské centrum v USA přijalo zásilku cestou do cíle («In transit»).

**Řádek v čínštině.** U objednávky bez skenů zapisuje platforma sama `货物电子信息已经收到` (přijaty elektronické údaje o zboží). Prodejce vytvořil objednávku, ale balík ještě nikdo neoskenoval («Label created»).

**Přes China Post.** Zásilka může ukázat i `广州处理中心已收寄` s městem v závorkách: převzetí China Post v Kuang-čou (u čísel Xinghe Freight pak `香港处理中心已收寄` v Hongkongu). Peek ukazuje oba řádky čínsky pod «Received by carrier».

**Časy.** Každý čas v trackeru GZYL je v pekingském čase (`UTC+8`), včetně kroků v Česku, bez vypsané časové zóny. V zimě odpovídá 20:00 v Pekingu 13:00 českého času (`UTC+1`), v létě 14:00 (`UTC+2`).

**Kroky zapsané předem.** GZYL může předem zapsat celou trasu zásilky od převzetí po poslední krok v tvé zemi dávno před časy, které kroky ukazují, a tracker každý z nich zobrazí, až nastane jeho chvíle. Takový krok je plán, nikoli sken. Stejně funguje [Meiteshun](carrier:mtscn) na téže platformě. Plán GZYL končí záznamem `Delivered`, který systém přidává večerní dávkou v uvedený den: datovaný den po posledním kroku plánu v pekingské pracovní době bez ohledu na tvou zemi. Sledovací služby kopírující GZYL mohou tyto kroky ukazovat také, takže `Delivered` tam nedokazuje, že balík přišel.

## Kdo doručuje balíky GZYL v Česku

Peek neví, která firma doručuje balíky GZYL v Česku: kroky GZYL v cílové zemi nejmenují žádného dopravce. Jediná doručovací společnost, které Peek dokáže balík GZYL předat, je [Swiss Post](carrier:swiss-post) ve Švýcarsku a Lichtenštejnsku.

Peek nenašel od GZYL žádné dodací lhůty ani pravidla pro pokusy o doručení, výdejní místa či úložní doby: ty určuje místní doručovatel. Na sledovací stránce GZYL se podívej do sloupce `渠道转单号` (překládkové číslo) a do `跟踪号码` (sledovací číslo), pokud obsahuje něco jiného než tvé `YL…YQ`: může jít o číslo místního dopravce, které si do Peeku přidáš jako samostatný balík. Není-li tam nic, zeptej se prodejce.

**Celní poplatky v EU.** Od 1. července 2026 platí pro online nákupy mimo EU v zásilkách do 150 € paušální clo 3 € za druh zboží. Od 1. listopadu 2026 se navíc účtuje unijní manipulační poplatek (Union handling fee) 2 € za druh zboží bez ohledu na hodnotu. Více v průvodci [balíky na celnici](guide:customs) (anglicky).

## Balík GZYL nedorazil nebo sledování stojí

**Pouze `Order information received` nebo `货物电子信息已经收到`.** Prodejce vytvořil objednávku, ale GZYL balík ještě neoskenovala. Pokud po několika dnech nic nepřibude, zeptej se prodejce, zda ho opravdu odeslal.

**Zaseknuto na celnici.** Pokud po tobě požadují dovozní poplatky, podívej se na průvodce [balíky na celnici](guide:customs) (anglicky). Stojí-li zásilka několik dní, napiš prodejci.

**Tracker GZYL nebo jiná služba ukazuje víc kroků než Peek.** Peek zadržuje kroky zapsané před časem, který ukazují, závěrečné `Delivered` plánu GZYL i kroky datované po poslední kontrole (viz níže). Předem zapsaný krok nemění stav ani neposílá upozornění. Když Peek takové kroky zadrží, historie do doručení či vrácení začíná větou «Other sites may show more steps, but they’re only predictions.» a «Show them» je ukáže šedě s datem («Predicted for Mon 12 Oct»). Řiď se odhadem obchodu a zeptej se prodejce, až uplyne. Více v průvodci [balíky z Číny](guide:tracking-from-china) (anglicky).

**`Delivered`, ale nic nepřišlo.** Krok může být součástí pevného plánu GZYL, ne skenem. Pokud nedoručuje Swiss Post, Peek pod «Delivered» vypíše: «GZYL updates on a fixed schedule, with no signature. If nothing came, ask the seller.» Napiš prodejci a vyžádej si název i číslo místního dopravce. Více v průvodci [doručeno, ale nedorazilo](guide:delivered-not-received) (anglicky).

**Nic nenalezeno.** Pro neznámé číslo ukáže tracker GZYL jeden výsledek s tvým číslem pod `参考号` (reference) a ostatními poli prázdnými. Peek zobrazí «Waiting for GZYL» a po dvou dnech «Check the tracking number». Porovnej číslo se zprávou prodejce.

**Ztraceno nebo vráceno.** Přepravu u GZYL objednal prodejce, proto žádej vrácení peněz nebo novou zásilku u prodejce či tržiště.

## Kontakt a zákaznická podpora GZYL

Kontaktní stránka (`联系我们`) čínského webu GZYL (`yunlianhouyun.com`) uvádí nepřetržitou linku (`全国24小时服务热线`), účet WeChat a poprodejní účet QQ (`售后QQ`), ale nic pro příjemce: tyto kanály slouží zákazníkům GZYL, tedy prodejcům. Ohledně objednávky kontaktuj prodejce, a pokud neodpovídá, tržiště.

## Sledování balíku GZYL v Peeku

[Peek](/) okamžitě pozná GZYL u čísla začínajícího na `YL738` se sedmi dalšími číslicemi a `YQ` («Detected carrier»). Ostatní čísla `YL`, deset číslic a `YQ` jsou slabá shoda. Přesto ho vlož: Peek sleduje přes 4 500 dopravců. Při přidávání se zeptá trackeru GZYL («Checking tracking services…») a při nalezení ukáže «GZYL has this parcel.» (i u prázdné odpovědi na neznámé číslo). Jinak stiskni «Change» a vyber GZYL pod «Fits this number» (najdeš ji i zadáním `广州云链`), nebo použij «Change carrier». Funguje i reference prodejce, pokud GZYL vybereš ručně — Peek pak ukáže číslo objednávky jako «GZYL tracks this as» (protože však odstraňuje pomlčky, referenci s pomlčkami nenajde, požádej o `YL…YQ`).

Peek čte vlastní tracker GZYL a zobrazuje stav, každý krok s místem a cílovou zemi. Časy čte v pekingském čase a zobrazuje ve tvém časovém pásmu («Times are in your time zone:» na konci historie). Vynechává jména, adresu, PSČ, hmotnost, poplatky, podpis i doklad o doručení a nezobrazuje odhadované datum doručení.

Peek zadržuje kroky zapsané více než 12 hodin před časem, který ukazují, kroky zapsané ve stejné sekundě s krokem zapsaným více než půl hodiny dopředu (kromě registrace) a kroky po okamžiku kontroly v pekingském čase. U objednávek s předem zapsanými kroky zadrží i `Delivered`. Když datové rozhraní nemá kroky, čte vyhledávací stránku GZYL a zadržuje tam každé `Delivered`. Jsou-li všechny kroky zadrženy, zobrazuje «Waiting for GZYL» bez výzvy ke kontrole čísla a vyřazuje i kopie plánovaných kroků z externích služeb. Pokud však tracker GZYL neodpovídá, kopie z externích služeb mohou obsahovat předem zapsané kroky.

Kromě předání Swiss Post zůstává Peek u GZYL: dostaneš-li číslo místního dopravce, přidej ho jako samostatný balík. Dokud balík sleduješ otevřený nebo v den doručení se zapnutými upozorněními, Peek kontroluje až každých 10 minut (a každé 2 minuty v den «Out for delivery» od 6:00 místního času, ačkoli žádný známý krok GZYL tento stav nedává); jinak a po dvou dnech klidu (12 hodin u «Label created» / «Received by carrier») každou hodinu (přes sledovací služby každou hodinu, každé 3 hodiny po klidném dni a každých 15 minut v den doručení). Upozornění lze nastavit na «Every scan», «Important steps» (dva celní kroky a `Delivered`, bez letů či třídění) nebo «Delivery only». Peek nemůže kontaktovat GZYL ani prodejce za tebe.

## Časté otázky o GZYL

### Co je GZYL (广州云链)?

GZYL je 广州云链货运代理有限公司 (Guangzhou Yunlian), spediční firma v čínském Kuang-čou s čínským webem `yunlianhouyun.com`. Její přepravní čísla mají 14 znaků: `YL`, deset číslic a `YQ`.

### Patří každé číslo YL…YQ společnosti GZYL?

Ne automaticky. `YL`, deset číslic a `YQ` je formát GZYL — Peek okamžitě určí GZYL pro `YL738…YQ` a u ostatních `YL…YQ` ověří tracker GZYL. Devět ani jedenáct číslic GZYL neodpovídá a `GZXH…YQ` patří Xinghe Freight.

### Proč GZYL hlásí «Delivered», když nic nedorazilo?

GZYL často zapisuje velkou část trasy předem a přidává `Delivered` podle pevného rozvrhu, nikoli na základě místního skenu doručení. Peek předem zapsané kroky zadržuje a upozorňuje na pevný rozvrh — napiš prodejci a zjisti místního dopravce.

### Kdo doručuje zásilky GZYL v Česku?

Skeny GZYL nikdy nejmenují místního dopravce v Česku (jen ve Švýcarsku a Lichtenštejnsku umí Peek předat zásilku Swiss Post). Podívej se na `渠道转单号` na stránce GZYL nebo se zeptej prodejce na místního dopravce a jeho číslo.

### Jak kontaktovat GZYL při zpožděném nebo ztraceném balíku?

Kontaktuj nejprve prodejce nebo tržiště. Ačkoli `yunlianhouyun.com` uvádí čínskou linku 24/7, WeChat a poprodejní QQ pro firemní klienty (prodejce), GZYL nemá žádný kanál podpory pro příjemce.

:::sources
- [广州云链货运代理有限公司 (GZYL): úvodní stránka (čínsky)](http://yunlianhouyun.com/) – název firmy, čínský web, vyhledávání zásilek (`小包查询`) na trackeru GZYL
- [广州云链货运代理有限公司 (GZYL): 联系我们 (kontakt, čínsky)](http://yunlianhouyun.com/contact.html) – 24hodinová linka, WeChat a poprodejní QQ
- [Evropská komise: EU Customs Reform (anglicky)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – clo 3 € za druh zboží do 150 € od 1. července 2026, manipulační poplatek EU 2 € od 1. listopadu 2026
- [Evropská komise: Guidance on the EUR 3 customs duty and the Union handling fee (PDF, anglicky)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – poplatek 2 € za druh zboží bez ohledu na hodnotu
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – názvy, tracker na holé IP adrese, formáty `YL…YQ` a `YL738…YQ`, Xinghe Freight na trackeru GZYL, vyhledávání podle reference, formulace kroků, čínský řádek, města, pekingský čas, předem zapsané kroky, odpověď na neznámé číslo, sloupce překládkového čísla, předání Swiss Post a co Peek zobrazuje, zadržuje a vynechává
:::
