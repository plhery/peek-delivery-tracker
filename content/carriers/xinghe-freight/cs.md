---
title: Sledování Xinghe Freight: číslo GZXH…YQ, stavy a kontakt
description: Sleduj zásilku Xinghe Freight (星和货运) z Číny: formát GZXH…YQ, proč využívá tracker GZYL, předem zapsaný stav Delivered, celní poplatky EU a kontakt.
slug: xinghe-freight-sledovani-zasilek
published: 2026-11-27
updated: 2026-11-27
---

Xinghe Freight (星和货运, 广州星和货运代理有限公司) je spediční společnost z čínského Kuang-čou (Guangzhou) založená v roce 2014, která zajišťuje námořní, leteckou i pozemní přepravu, lodní agenturu a celní odbavení. Zásilku sleduješ vložením 16znakového čísla ve tvaru `GZXH`, deset číslic a `YQ` do pole výše. Web Xinghe Freight předává dotazy na sledování nešifrovanému HTTP trackeru [GZYL](carrier:gzyl), kde kroky — včetně `Delivered` — mohou být zapsány předem místo skutečných skenů. Peek tento tracker čte za tebe, odděluje předpovědi od skutečných skenů a v případě potřeby se ptá externích sledovacích služeb.

## Sledovací číslo Xinghe Freight: jak vypadá

Čísla, která Peek rozpozná jako Xinghe Freight, se skládají z `GZXH`, deseti číslic a `YQ` — celkem 16 znaků. Pokud omylem vynecháš poslední písmeno `Q` (`GZXH`, deset číslic a `Y`, 15 znaků), Peek číslo stejně pozná a `Q` před dotazem doplní zpět. Příklady s vymyšlenými číslicemi:

| Číslo | Co znamená |
| --- | --- |
| `GZXH4829105736YQ` | `GZXH`, deset číslic a `YQ`: standardní formát Xinghe Freight. Peek vybere Xinghe Freight okamžitě. |
| `GZXH4829105736Y` | `GZXH`, deset číslic a `Y`: chybí poslední `Q`, ale Peek ho doplní automaticky. |
| `GZXH482910573YQ` | Pouze devět číslic: není to formát Xinghe Freight. Peek číslo ihned odmítne, abys zkontroloval chybějící číslici. |
| `YL4829105736YQ` | `YL`, deset číslic a `YQ`: formát přepravního listu [GZYL](carrier:gzyl). Stejný tracker, jiný spediter. |
| `JXCAA4829105736YQ` | Také končí na `YQ`, jako mnoho čínských speditérů, ale začíná na `JXC`: číslo [Juxi Express](carrier:juxi-express). |

Mezery, pomlčky ani malá písmena při vkládání do Peeku nevadí.

**Kde číslo najdeš.** Přepravu u Xinghe Freight objednává prodejce a číslo ti pošle v e-mailu o odeslání nebo na stránce objednávky. Pokud ho nemůžeš najít, napiš prodejci.

## Co znamenají stavy sledování Xinghe Freight

Sledování Xinghe Freight běží na platformě GZYL, takže jednotlivé kroky přicházejí v anglických formulacích GZYL včetně překlepů. Protože rozhraní aplikace Peek nemá českou verzi, hlavní fáze Peeku jsou také v angličtině: «Label created» (štítek vytvořen), «Received by carrier» (přijato dopravcem), «In transit» (na cestě), «At customs» (na celnici) a «Delivered» (doručeno). Kroky v obvyklém pořadí:

| Tracker píše | Co to znamená pro tebe | Peek ukazuje |
| --- | --- | --- |
| `Order information received` | Prodejce odeslal údaje k objednávce. Balík ještě nikdo fyzicky nepřevzal. | «Label created» |
| `Arrive at the cross border receiving point` | Spediter balík převzal: první skutečný sken v jeho rukou. | «Received by carrier» |
| `Goods leave the operation center` | Balík opustil operační centrum v Číně. | «In transit» |
| `Arrive at the international airport` | Na mezinárodním letišti, čeká na let. | «In transit» |
| `Departing from the airport` | Letadlo odletělo. | «In transit» |
| `Item arrives at destination airport` | Přistálo v cílové zemi. | «In transit» |
| `local carrier Agent Import customs clearance` | Místní zástupce vyřizuje dovozní celní odbavení. | «At customs» |
| `The import customs keep it for inspection` | Celní úřad v tvé zemi zadržel balík ke kontrole. | «At customs» |
| `Customs clearance completed` | Celní odbavení skončilo a balík pokračuje dál. | «In transit» |
| `Internationaliy Package received at processing center` | Zpracovatelské centrum v cílové zemi převzalo proclený balík. Nejde o nové vyzvednutí u odesílatele. | «In transit» |
| `Leave the email processing center` | Balík toto centrum opustil. | «In transit» |
| `Arrival sorting, distribution center` | Dorazil do třídicího nebo distribučního centra v tvé zemi, s městem za textem. | «In transit» |
| `Leaving the sorting center` | Opouští třídicí centrum, s městem za textem. | «In transit» |
| `Package is on the way` | Pokračuje dál po cílové zemi s uvedením města. Neznamená to, že balík dnes rozváží kurýr. | «In transit» |
| `Delivered` | Záznam o doručení s městem. Může jít o plán, ne o sken: viz níže. | «Delivered» s poznámkou o pevném rozvrhu, pokud Peek krok nezadrží (viz níže) |

**Přes Hongkong.** Může se objevit i řádek `香港处理中心已收寄【Hong Kong】`: poštovní převzetí ve zpracovatelském centru v Hongkongu, přenášené v čínštině s městem v závorkách. Peek jej ukazuje beze změny pod «Received by carrier».

**Řádek v čínštině.** U objednávky bez skenů zapisuje platforma sama `货物电子信息已经收到` (přijaty elektronické údaje o zásilce). Prodejce vytvořil objednávku, ale balík ještě nikdo neoskenoval. Peek řádek zobrazuje čínsky pod «Label created».

**Časy.** Peek čte všechny časy v trackeru jako pekingský čas (`UTC+8`), včetně kroků v Česku, a převádí je do časového pásma tvého zařízení. V zimě je 20:00 v Pekingu 13:00 českého času (`UTC+1`), v létě 14:00 (`UTC+2`).

**Kroky zapsané předem.** Na této platformě mohou speditéři předem zapsat celý harmonogram zásilky od převzetí až po poslední krok v cílové zemi a tracker pak každý řádek zobrazí, až nastane jeho čas. Takový krok je plán, nikoli sken tvého balíku. Na trackeru GZYL plán končí záznamem `Delivered`, který systém přidává večerní dávkou v uvedený den: datovaný den po posledním kroku plánu v pekingské pracovní době bez ohledu na tvou zemi. Peek tyto kroky zadržuje (viz níže), ale kopie u jiných sledovacích služeb je mohou zobrazovat, takže `Delivered` tam nedokazuje, že balík dorazil.

## Kdo doručuje zásilky Xinghe Freight v Česku

Peek neví, který místní dopravce doručuje balíky Xinghe Freight v Česku. Kroky v cílové zemi nejmenují žádnou firmu a web Xinghe Freight neuvádí nic o balících pro online nakupující. Jediný místní dopravce, kterému Peek dokáže zásilku Xinghe automaticky předat, je [Swiss Post](carrier:swiss-post) ve Švýcarsku a Lichtenštejnsku, když je uvedena v záznamu.

Web Xinghe Freight neuvádí dodací lhůty, počet pokusů o doručení ani výdejní místa: ta se řídí pravidly místního dopravce. Zeptej se prodejce, která firma zásilku v Česku doručuje, a vyžádej si její vlastní sledovací číslo.

**Celní poplatky v EU.** Od 1. července 2026 platí pro online nákupy mimo EU v zásilkách do 150 € paušální clo 3 € za druh zboží. Od 1. listopadu 2026 přibyl unijní manipulační poplatek (Union handling fee) 2 € za druh zboží bez ohledu na hodnotu zásilky. Jedním druhem se rozumí zboží ve stejném balíku se stejným sazebním zařazením, popisem a případně původem bez ohledu na počet kusů. Více najdeš v průvodci [balíky na celnici](guide:customs) (anglicky).

## Zásilka Xinghe Freight nedorazila nebo sledování stojí

**Pouze `Order information received` nebo `货物电子信息已经收到`.** Prodejce vytvořil objednávku, ale balík ještě nikdo neoskenoval. Pokud se několik dní nic nezmění, zeptej se prodejce, zda byl skutečně odeslán.

**«Waiting for Xinghe Freight».** Peek zatím nemá potvrzený sken: tracker GZYL číslo buď nezná, nebo obsahuje jen předem napsaný plán (klidně až po `Delivered`), a externí služby také nic nemají. Peek kontroluje dál. Pro neznámé číslo vrátí tracker GZYL jeden prázdný řádek s číslem jako referencí; Peek to bere jako nenalezené, zobrazí «Waiting for Xinghe Freight» a po dvou dnech «Check the tracking number». Předem zapsaný plán naopak dokládá, že Xinghe objednávku má, takže «Waiting for Xinghe Freight» zůstává bez výzvy ke kontrole čísla. Přesto číslo porovnej znak po znaku se zprávou od prodejce.

**Zaseknuto na celnici.** Pokud po tobě chtějí dovozní poplatky, projdi si průvodce [balíky na celnici](guide:customs) (anglicky). Stojí-li balík několik dní, napiš prodejci.

**Tracker nebo jiný web ukazuje víc kroků než Peek.** Kroky navíc — včetně `Delivered` — mohou být předem zapsaný plán, který Peek zadržuje. Řiď se odhadem doručení z obchodu a po jeho uplynutí kontaktuj prodejce. Více v průvodci [sledování balíků z Číny](guide:tracking-from-china) (anglicky).

**`Delivered`, ale nic nepřišlo.** Krok může být součástí pevného rozvrhu. Pokud nedoručuje Swiss Post, Peek pod «Delivered» vypíše: «Xinghe Freight updates on a fixed schedule, with no signature. If nothing came, ask the seller.» Napiš prodejci, že balík nedorazil, a vyžádej si název a číslo místního dopravce. Více v průvodci [doručeno, ale nedorazilo](guide:delivered-not-received) (anglicky).

**Ztraceno nebo vráceno.** Přepravu u Xinghe Freight objednal prodejce, proto vrácení peněz nebo nové zaslání řeš s prodejcem či tržištěm.

## Kontakt a zákaznická podpora Xinghe Freight

Čínský web Xinghe Freight (`xinghe-express.cn`) uvádí:

- **Nepřetržitá linka (24/7):** `19924483103` (`+86 199 2448 3103` ze zahraničí), uvedena jako otevřená 24 hodin denně každý den.
- **E-mail:** `3975605292@qq.com` pro obchodní dotazy a cenové nabídky.
- **Adresa:** Kuang-čou (Guangzhou), provincie Kuang-tung, bez ulice (pro přesnou adresu prosí nejprve o kontakt).

Web je pouze v čínštině a nenabízí podporu pro zahraniční příjemce: kontaktní formulář jen otevře e-mail na uvedenou adresu. Zákazníkem Xinghe je prodejce, takže kontaktuj nejprve prodejce a při neodpovídání tržiště.

## Sledování zásilky Xinghe Freight v Peeku

[Peek](/) okamžitě rozpozná číslo `GZXH…YQ` (i zkrácené `GZXH…Y`) jako Xinghe Freight se štítkem «Detected carrier». Dal-li ti prodejce číslo jiného tvaru, vlož ho stejně: Peek sleduje přes 4 500 dopravců. Ručně zvolíš Xinghe Freight přes «Change» a vyhledání Xinghe Freight (nebo `星和货运`), případně přes «Change carrier» u již přidané zásilky.

Peek se ptá přímo trackeru GZYL stejně jako pole na webu Xinghe. Ukazuje stav, každý krok s místem a cílovou zemi, pokud je v záznamu. Časy čte v pekingském čase a zobrazuje ve tvém časovém pásmu («Times are in your time zone:» na konci historie). Peek vynechává jména, adresu, PSČ, hmotnost, platební údaje, podpis i doklad o doručení a nezobrazuje odhadované datum doručení, protože ho tracker neposkytuje.

Když tracker číslo nezná, Peek osloví sledovací služby a ukáže jejich kopii. Dokud nikde není žádný krok, zobrazuje «Waiting for Xinghe Freight» a po dvou dnech «Check the tracking number».

Peek zadržuje každý krok zapsaný více než 12 hodin před časem, který uvádí, každý krok zapsaný ve stejné sekundě s krokem napsaným více než půl hodiny dopředu (kromě registrace objednávky) a každý krok datovaný po okamžiku kontroly v pekingském čase. U záznamu s předem zapsanými kroky zadrží i `Delivered`, i když byl zapsán tentýž den. Řídí se ponechanými kroky, ne souhrnným stavem, a vyřazuje i kopie těchto plánovaných kroků ze sledovacích služeb (`Delivered` včetně). Pokud však tracker nemá záznam nebo neodpovídá, Peek nedokáže odlišit plánovaný krok z externí služby od pravého skenu. Když zadrží předpovědi, historie do doručení či vrácení začíná větou «Other sites may show more steps, but they’re only predictions.» a «Show them» je zobrazí šedě s datem («Predicted for Mon 12 Oct») beze změny stavu a bez upozornění.

Kromě Swiss Post nepředává Peek zásilku místnímu dopravci: dostaneš-li od prodejce místní číslo, přidej ho jako samostatný balík. Dokud máš balík otevřený nebo v den doručení se zapnutými upozorněními, Peek kontroluje až každých 10 minut; jinak a v klidném období jednou za hodinu. Upozornění lze nastavit na «Every scan», «Important steps» (dva celní kroky a `Delivered`, nikoli lety či třídění) nebo «Delivery only». Peek nemůže za tebe kontaktovat Xinghe Freight, GZYL ani prodejce.

## Časté otázky k Xinghe Freight

### Co je Xinghe Freight (星和货运)?

广州星和货运代理有限公司 (Guangzhou Xinghe Freight Forwarding), spediční firma v čínském Kuang-čou založená v roce 2014 pro námořní, leteckou a pozemní přepravu, lodní agenturu a celní deklarace. Její čísla zásilek mají 16 znaků: `GZXH`, deset číslic a `YQ`.

### Proč moje zásilka Xinghe Freight ukazuje sledování GZYL?

Sledovací pole na webu Xinghe Freight posílá dotazy přímo do trackeru GZYL, takže texty kroků odpovídají GZYL. Peek vede balík pod Xinghe Freight a tento tracker čte za tebe.

### Kdo doručuje zásilky Xinghe Freight v Česku?

Ani web Xinghe, ani tracker nejmenují místního partnera v Česku (Peek automaticky předává pouze Swiss Post ve Švýcarsku a Lichtenštejnsku). Zeptej se prodejce, jaká místní služba zásilku doručuje, a vyžádej si její sledovací číslo.

### Proč Peek ukazuje méně kroků nebo žádné sledování pro mé číslo GZXH?

Tracker buď číslo ještě nezná, nebo obsahuje předem zapsané kroky — včetně plánovaného `Delivered` — které Peek schovává pod «Show them», místo aby je počítal jako skeny. Zkontroluj číslo podle e-mailu od prodejce a po několika dnech mu napiš.

### Jak mohu kontaktovat Xinghe Freight?

Nejprve kontaktuj prodejce nebo tržiště, protože přepravu objednal prodejce. Čínský web (`xinghe-express.cn`) uvádí nepřetržitou linku `19924483103` (`+86 199 2448 3103` ze zahraničí) a obchodní e-mail `3975605292@qq.com` bez zvláštní podpory pro zahraniční příjemce.

:::sources
- [Xinghe Freight: 广州星和货运代理有限公司 (úvodní stránka, čínsky)](http://xinghe-express.cn/) – název firmy, spediční služby, vyhledávací pole k trackeru GZYL, telefon a e-mail
- [Xinghe Freight: 关于我们 (o nás, čínsky)](http://xinghe-express.cn/about.html) – založení 2014, námořní, letecká a pozemní přeprava, lodní agentura a celní odbavení
- [Xinghe Freight: 产品服务 (služby, čínsky)](http://xinghe-express.cn/services.html) – přehled služeb, nic o balících pro online nakupující
- [Xinghe Freight: 联系我们 (kontakt, čínsky)](http://xinghe-express.cn/contact.html) – linka 7×24, obchodní e-mail, adresa jen Guangzhou, formulář otevírající e-mail
- [Evropská komise: EU Customs Reform (anglicky)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – paušální clo 3 € za druh zboží do 150 € od 1. července 2026, manipulační poplatek EU 2 € od 1. listopadu 2026
- [Evropská komise: Guidance on the EUR 3 customs duty and the Union handling fee (PDF, anglicky)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – poplatek 2 € bez ohledu na hodnotu a definice druhu zboží
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – názvy, formáty `GZXH…YQ` a zkrácený `GZXH…Y`, odmítnutí chybné délky, tracker GZYL, formulace kroků, převzetí v Hongkongu, čínský řádek, pekingský čas, předem zapsané kroky, záložní služby, předání Swiss Post a co Peek zobrazuje, zadržuje a vynechává
:::
