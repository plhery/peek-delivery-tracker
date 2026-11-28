---
title: Відстеження JS Express: номери JSE…YQ, статуси, доставка
description: Відстеження посилки JS Express (急速国际) з Китаю: формат JSE…YQ, значення статусів, хто доставляє у США, Канаді та Європі, митниця DDP і контакти.
slug: js-express-vidstezhennia
published: 2026-11-28
updated: 2026-11-28
---

JS Express (急速国际, також JS EXP) — китайська логістична компанія для транскордонних інтернет-магазинів, якою керує 急创速供应链管理（苏州）有限公司 у Сучжоу. Якщо магазин відправив ваше замовлення через JS Express, трек-номер починається з `JSE` і закінчується на `YQ`: вставте його в поле вище. JS Express вивозить посилку з Китаю, а доставляє її кур'єр у вашій країні: в історії трапляються USPS, GOFO або SpeedX у США, Straightship у Канаді, Yodel у Великій Британії, DHL, iMile або J&T Express у Німеччині, Colissimo у Франції та CTT Express в Іспанії, а на деяких лініях — FedEx.

## Трек-номер JS Express: як він виглядає

Накладні JS Express складаються з `JSE`, двох літер, десяти цифр і `YQ` — загалом 17 символів. Із вигаданими цифрами:

| Номер | Що це |
| --- | --- |
| `JSEAA4829105736YQ` | `JSE`, дві літери, десять цифр і `YQ`: накладна JS Express. |
| `JSEAA482910573YQ` | Лише дев'ять цифр: не формат JS Express. Перевірте, чи не пропущено цифру. |
| `JXCAA4829105736YQ` | Теж закінчується на `YQ`, але починається з `JXC`: номер [Juxi Express](carrier:juxi-express). |

Інші китайські експедитори використовують таку саму схему зі своїми літерами на початку: `FEI` для 17FEIA, `WNB` для WanbExpress, `SFC` для SFC, `CJP` для CJ Packet. `JSE` позначає JS Express. Пробіли й малі літери в Peek не мають значення. Якщо замість другої з двох літер введено нуль, Peek читає його як `O`, а літеру `O` серед десяти цифр — як нуль. Власному трекеру JS Express потрібен увесь номер: за частиною номера він нічого не знаходить.

**Де знайти номер.** Магазин передає номер у листі про відправлення або на сторінці замовлення. JS Express зазначає, що тісно співпрацює з Amazon, Walmart, Shopify, eBay, AliExpress, Wish і Shopee. Якщо ви не можете його знайти, див. [how to find your tracking number](guide:find-tracking-number) (англійською).

**Немає посилання для пересилання.** Сторінки відстеження JS Express приймають номер у формі, тому жодне посилання не відкриває одну посилку: скопіюйте номер. Англійська сторінка «Express Query» відповідає одразу; китайська спочатку просить ввести код із картинки (`验证码`).

## Пояснення статусів відстеження JS Express

Сторінка JS Express показує кожен скан із часом, місцем у дужках 【】 і текстом, найновіші зверху, з позначкою «Latest». Формулювання переважно англійські. У порядку проходження посилки:

| JS Express пише | Що це означає для вас | Peek показує |
| --- | --- | --- |
| `Electronic notification of order creation received.` | Магазин надіслав JS Express дані замовлення. Самої посилки в JS Express ще немає. | "Label created" |
| `PRE-ADVICE` | Партнер отримав дані про посилку ще до того, як вона надійшла на склад JS Express. | Статус, який посилка вже мала |
| `Item has been collected` | Склад JS Express у Китаї отримав посилку: перший реальний скан. | "Received by carrier" |
| `Item inbound in sorting center.`, `Item outbound in sorting center.` | Сортування в центрі JS Express у Китаї. | "In transit" |
| `Domestic allocation has begun`, `Under domestic allocation` | Досі на складі JS Express у Китаї, після сортування і перед відправленням. | "In transit" |
| `Waybill Generated for` | Кур'єр у вашій країні створив свою накладну, поки посилка ще на складі JS Express у Китаї. | "Label created" або попередній статус, зазвичай "In transit" |
| `Arrive at transit center` | У транзитному центрі JS Express у Китаї між сортуванням і вильотом. | "In transit" |
| `Item departed from origin.`, `Arrive at international airport to abroad` | На шляху до вильоту, потім в аеропорту в Китаї. | "In transit" |
| `Arrived at export center`, `Exports Center Sorting and Packing`, `Arrived at Origin Port` | На деяких лініях між двома попередніми кроками: експортний центр у Китаї сортує та пакує посилку, потім вона прибуває до порту вивезення. | "In transit" |
| `Shipment arrived at facility and measured.`, `Depart from facility to service provider.` | Центр у Китаї зважує й вимірює посилку, потім передає її на авіалінію. | "In transit" |
| `Start on-site transportation and shipment` | В аеропорту в Китаї після прибуття туди: розпочалося оброблення на місці до випуску китайською експортною митницею. | "In transit" |
| `Released From Export Customs` | Експортне митне оформлення в Китаї завершено. Це не митниця вашої країни. | "In transit" |
| `DEPART ORIGIN AIRPORT`, `Departure from the original airport`, `Item Flight departure`, `Initial landing`, `Connecting flights flight takeoff` | Рейс вилетів, іноді з проміжною посадкою та стикувальним рейсом. | "In transit" |
| `ARRIVED AT DESTINATION AIRPORT`, `Arrival to the destination airport`, `Item Flight landing`, `NOA-Notification of arrival from GHA` | Приземлилася в країні призначення. NOA — повідомлення про прибуття від наземного оператора аеропорту. | "In transit" |
| `The customs clearance agent is queuing up to pick up the goods` | Митний агент у вашій країні чекає в черзі у вантажному терміналі аеропорту, щоб забрати вантаж: посилки ще немає ні в агента, ні на митниці. | "In transit" |
| `The customs clearance agent has picked up the goods` | Агент забрав вантаж із вантажного терміналу для імпортного оформлення. | "At customs" |
| `The goods have been picked up abroad` | Агент у вашій країні забрав відправлення з аеропорту. Це не новий забір у продавця. | "In transit" |
| `Customs clearance agent received the shipment in destnation country.` | Митний агент у вашій країні прийняв відправлення на імпортне оформлення після перельоту. «destnation» — власне написання JS Express. | "At customs" |
| `Inbound to customs temporary warehouse` | На митному складі тимчасового зберігання в очікуванні імпортного оформлення. | "At customs" |
| `Released From Import Customs`, `Released from customs: customs cleared.` | Митниця вашої країни пропустила посилку. | "In transit" |
| `Depart from the destination distribution center.` | Розподільчий центр у вашій країні відправляє посилку далі після митниці. | "In transit" |
| `In transit, it's progressing through post network` | Пошта вашої країни перевозить посилку своєю мережею до виходу на доставку. | "In transit" |
| `collection by overseas warehouse` | Закордонний склад JS Express у вашій країні прийняв посилку після митниці. | "In transit" |
| `Load cargo overseas`, `Vehicles depart from` | Цей склад завантажує посилку для кур'єра, потім машина вирушає з місця, зазначеного у скані. | "In transit" |
| `Delivery to local courier`, `Last Mile Delivery` | Передано місцевому кур'єру у вашій країні до його першого скану. Ще не доставлено вам. | "In transit" |
| `Delivery in progress` | На деяких маршрутах: місцевий перевізник отримав посилку після прибуття на свою станцію і до виїзду на доставку. Попри формулювання, ще не в доставці кур'єром. | "In transit" |
| `到达待取` | Власний статус JS Express китайською: прибула, очікує отримання. Як місце зазначена лише країна. | "Ready for pickup" |
| `The delivery address is incorrect. Please ask the recipient to contact their local area?` | Власне повідомлення JS Express після невдалої спроби доставки: адреса нечітка або неправильна. Просить зв'язатися з місцевим відділенням, телефон якого подано на сторінці JS Express; Peek прибирає номери телефонів. | "Needs attention" |

**Скани місцевого кур'єра.** Після `Delivery to local courier` історія триває словами кур'єра. У США це USPS: `Accepted at USPS Origin Facility` або `Accepted at USPS Facility` (його перший скан, не нове відправлення), `Out for Delivery`, `Delivered, Front Door/Porch`. Скани SpeedX приходять без його назви: `Processing at Transit Facility`, `In transit to destination facility`, `Arrival at Destination Facility` і `Processing at Designated Facility` — усі "In transit". Скани іншого американського партнера теж без назви: `Arrived at Origin Hub` (перший хаб біля аеропорту прильоту), `Departed Destination Hub` і `Arrived At Warehouse` (склад останньої милі перед маршрутом) — усі "In transit". У Канаді — Straightship: `Package is received at a Straightship station`, потім `Package will be delivered before 9:00pm`, маршрут водія того дня ("Out for delivery": далі доставка або спроба), і `Delivered` з містом у дужках. У Німеччині — DHL англійською, іноді з крапкою в кінці: `The instruction data for this shipment have been provided by the sender to DHL electronically`, його запис даних посилки ("Label created", або "In transit", якщо раніше вже був скан), далі `The shipment is in transit to DHL` ("In transit"); дрібний пакет також може отримати німецьке `Die Sendung wurde im Briefzentrum bearbeitet.` із поштового центру DHL ("In transit"). Водій iMile може завершити маршрут записом `Route not completed` ("Delivery attempted"). Рядки J&T Express Germany надходять німецькою з місцями у дужках 【】: `【】【】Ihr Sendung wurde abgeholt.` — перший скан при прийманні посилки після перельоту й митниці; далі `【】Die Sendung hat【】verlassen. Weiterversand nach【】` (залишила один пункт на шляху до наступного) і `【】Die Sendung ist bei【】 angekommen.` (у хабі або депо доставки перед маршрутом) — усі "In transit". Де доставляє FedEx, `Shipment information sent to FedEx` — це його етикетка, створена ще до надходження посилки на склад JS Express ("Label created"); далі `Arrived at FedEx location`, `Left FedEx origin facility` і `On the way` ("In transit"). В Угорщині далі йдуть `Arrived to HU HUB`, `Linehaul Transit` (перевезення з хабу до сортувального центру) і `Shipment arrived to sorting centre / Shipment in the sorting centre` — усі "In transit". В Іспанії `Enviado` від CTT Express іспанською означає, що воно відправило посилку далі після приймання й до маршруту доставки ("In transit").

**Кур'єр без назви.** На частині ліній JS Express передає скани місцевого кур'єра великими літерами без назви кур'єра та країни. `SHIPMENT RECEIVED`, `PROCESSED AT HUB` і `SHIPMENT PROCESSED` означають прийняття посилки в хабі кур'єра, а `DEPARTED FROM DEPOT` — вихід звідти до депо доставки; усі "In transit". `RECEIVED AT LOCAL DEPOT` — це те депо перед кожним маршрутом і знову після невдалої спроби: власне депо кур'єра, не пункт видачі, тому Peek зберігає статус попереднього рядка. `DELIVERY ATTEMPTED: 1ST DELIVERY ATTEMPT`, `DELIVERY ATTEMPTED: 2ND DELIVERY ATTEMPT` і `DELIVERY ATTEMPTED: 3RD DELIVERY ATTEMPT` — невдалі спроби доставки ("Delivery attempted"). Після третьої кур'єр просить призначити доставку; `SHIPMENT REDIRECTED: ON CONSIGNEE'S REQUEST` означає, що посилка прямує за узгодженою вами доставкою ("In transit"). `DELIVERY EXCEPTION` — проблема в хабі кур'єра до виїзду на доставку, а `DELIVERY EXCEPTION, WAIT FOR INFORMATION CONFIRMATION` після `ARRIVE AT THE WAREHOUSE` ("In transit") означає, що кур'єр утримує посилку до підтвердження даних: обидва рядки Peek показує як "Needs attention". Якщо Peek не показує номер кур'єра, запитайте продавця, хто доставляє і що потрібно кур'єру.

**Рядки, що не є сканами.** `SHIPPING INFORMATION RECEIVED`, `SHIPMENT CREATED`, `Data Received`, рядок USPS `Shipping Label Created, USPS Awaiting Item`, `Package information is created in the system` і, у європейському хабі, `Shipment Data Uploaded`, а потім `Processed` — це запис даних посилки в партнера, інколи за кілька днів до отримання самої посилки. `Consignee Contacted`, що записується в момент фіксації невдалої доставки, означає дзвінок чи повідомлення вам і теж не змінює статус.

**Час не за порядком.** Позначки часу не мають часового поясу й змішують різні годинники: пекінський час у Китаї, місцевий час в аеропортах і більшості кур'єрів та UTC для Straightship, що випереджає канадський час на кілька годин. Орієнтуйтеся на порядок рядків, а не на час. У Peek німецькі рядки J&T Express Germany показують час із власного трекера J&T Express Germany.

## Хто доставляє посилки JS Express

**Сполучені Штати.** В історії JS Express доставку виконують [USPS](carrier:usps) зі сканами до вручення, [GOFO](carrier:gofo) зі сканами від сортувального центру до маршруту або [SpeedX](carrier:speedx), чиї скани не називають компанію. Коли посилка вже в USPS, USPS приймає запит Missing Mail від відправника або отримувача починаючи із 7 днів після відправлення.

**Канада.** Частину посилок JS Express доставляє канадський кур'єр [Straightship](carrier:straightship); його скани відображаються в історії JS Express.

**Німеччина, Франція та Іспанія.** Європейська лінія JS Express охоплює такі країни, як Велика Британія, Франція, Німеччина, Італія, Іспанія, Швейцарія та Данія, крім деяких віддалених індексів. Для Німеччини JS Express називає DPD та інших кур'єрів останньої милі й зазначає, що понад 98 % посилок доставляються протягом 8 робочих днів; в історії там траплялися скани [DHL Paket](carrier:dhl), [iMile](carrier:imile) з німецького центру та [J&T Express Germany](carrier:j-and-t-de) німецькою. У Франції траплялися скани [Colissimo](carrier:la-poste) французькою, а в Іспанії — [CTT Express](carrier:ctt-express).

**Велика Британія, Ірландія, Австралія та інші країни.** На деяких лініях в історії з'являються скани [FedEx](carrier:fedex). У Великій Британії трапляються скани Yodel; два з них — `DESPATCHED AND ON ROUTE TO YODEL` і `PARCEL PICKED UP` — з'являються, поки посилка ще на складі JS Express у Китаї. Для Австралії JS Express називає Australia Post і Fastway (тепер [Aramex Australia](carrier:aramex-au)) до 4 кг та DPEX понад 4 кг. Запис JS Express може містити номер місцевого кур'єра, але не його назву, а сторінка JS Express не показує жодного з них. Peek визначає кур'єра, коли цей номер відповідає одному перевізнику у вашій країні, і запитує пошту вашої країни за [поштовим номером](guide:tracking-number-formats) (англійською; дві літери, дев'ять цифр, дві літери); докладніше нижче. Якщо Peek не називає кур'єра, запитайте продавця, хто доставляє.

Спроби доставки, повідомлення та пункти видачі підпорядковуються правилам місцевого кур'єра: JS Express власних не публікує. Рядок `到达待取` називає лише країну, тому перевірте трекінг кур'єра або запитайте продавця, де чекає посилка.

## Посилка JS Express не прибуває або відстеження зависло

**«未获取到任何数据,请提供有效单号!»** Англійська сторінка JS Express відповідає так китайською на невідомий номер: «даних не знайдено, вкажіть дійсний номер накладної». `No track data for now!` означає, що сканів поки немає. В обох випадках Peek показує "Waiting for JS Express" і продовжує перевіряти. Якщо JS Express усе ще не знає номер через два дні й ніщо інше за ним не відповіло, Peek показує "After two days, the carrier still doesn’t know this number. Check it, choose another carrier or delete the parcel." Перевірте номер символ за символом; якщо за кілька днів нічого не з'явиться, запитайте продавця, чи була посилка відправлена.

**Зависло на `Electronic notification of order creation received.`** JS Express ще не забрав посилку. Запитайте магазин, коли її передали.

**Зависло на `Inbound to customs temporary warehouse`.** Посилка чекає на митницю вашої країни. На частині маршрутів ідуть рядки `Start Custom clearance Process`, потім `customs clearance completed`; раніший `Under customs clearance, please wait patiently.` — це експортна митниця Китаю в аеропорту вильоту, тому Peek показує для нього "In transit". JS Express продає свою європейську лінію та стандартну лінію до США зі сплаченими митами (DDP): компанія наперед збирає імпортні податки з магазину, тож на цих лініях із вас не повинні вимагати доплати. Якщо кур'єр просить сплатити, уточніть у продавця перед оплатою. В ЄС із 1 листопада 2026 року діє збір за оброблення 2 € за вид товару; його сплачує продавець або компанія-доставник і може перекласти на отримувача. Див. [parcels held at customs](guide:customs) (англійською); якщо посилка затримується на дні, повідомте продавця. Більше в [why tracking stops updating](guide:tracking-not-updating) і [tracking a parcel from China](guide:tracking-from-china) (англійською).

**Нічого після `Delivery to local courier`.** Посилка в місцевого кур'єра. Якщо Peek показує номер цього кур'єра, перевірте його на сайті кур'єра; якщо ні, запитайте продавця, хто доставляє.

**Доставлено, але нічого не прийшло.** Спершу запитайте місцевого кур'єра (у США — USPS), потім продавця. На лінії до США JS Express допомагає перевірити доставку, яку показує кур'єр, але ви не отримали, проте компенсації не виплачує, тому вирішує політика продавця. Див. [delivered but not received](guide:delivered-not-received) (англійською).

**Загублено, пошкоджено або повернуто.** Клієнтом JS Express є продавець. За умовами JS Express клієнт подає письмову претензію протягом 30 днів із моменту отримання посилки JS Express, а щодо застрахованих посилок — через власний акаунт. Тож просіть у магазину або маркетплейсу повернення коштів чи нову посилку і не зволікайте. Посилку, яку не вдалося доставити (нікого немає вдома, відмова, неправильна адреса, митниця), повертають за плату на запит відправника або знищують; на лінії до США JS Express ні повертає таку посилку до Китаю, ні відправляє повторно. На частині маршрутів `Refused`, потім `Returned` означають, що від посилки відмовилися під час доставки й вона повернулася до сортувального центру кур'єра, але ще не в продавця: Peek показує "Needs attention". Див. [lost parcels](guide:lost-parcel) та [returned to sender](guide:returned-to-sender) (англійською).

## Контакти та підтримка клієнтів JS Express

Станом на 30 жовтня 2026 року на китайській сторінці контактів JS Express зазначено таке. Англійська сторінка контактів старіша й досі наводить інші номери та адреси.

- **Гаряча лінія**: 400-0353-518 — китайський сервісний номер, зазначений також для міжнародних звернень. Годин роботи JS Express не публікує і не уточнює, чи працює номер із-за кордону.
- **QQ і WeChat**: корпоративний QQ 4000353518 та офіційний акаунт WeChat js-express.
- **Скарги**: форма на англійській сторінці скарг і та сама гаряча лінія.
- **Офіси**: головний офіс і склад у Сучжоу, інші склади в Китаї та офіси мережі в Гонконзі й Волнат, Каліфорнія. Єдиний телефон американського офісу — та сама китайська гаряча лінія.

Претензії JS Express приймає через продавця, який оформив відправлення. Щодо замовлення звертайтеся до продавця; щодо доставки — до місцевого кур'єра.

## Відстежуйте посилку JS Express за допомогою Peek

[Peek](/) розпізнає номер JS Express за його формою: `JSE`, дві літери, десять цифр і `YQ`, яку не використовує жоден інший перевізник у каталозі, й запитує за ним трекер JS Express. Якщо у вас є лише номер місцевого кур'єра, вставте його натомість: Peek відстежує понад 4 500 перевізників.

Peek показує історію з англійської сторінки JS Express, кожен скан із його місцем і країну призначення, яку та сторінка пропускає. `到达待取` відображається як "Arrived, awaiting collection". Номери телефонів вилучаються; ім'я того, хто розписався, зберігається. Фото доставки й номер замовлення магазину, які можуть бути в записі JS Express, Peek не показує. Як очікувану дату доставки він показує день з оцінки JS Express; щойно посилка виходить на доставку, зокрема з рядком Straightship `Package will be delivered before 9:00pm`, день більше не показується, бо "Out for delivery" уже означає сьогодні. Рядки, що не є сканами, не змінюють статус.

Peek читає рядки в Китаї за пекінським часом, рядки Straightship за UTC, а німецькі рядки J&T Express Germany за зміщенням, указаним у кожному рядку, визначає годинники інших за місцем або країною призначення й показує всі часи в часовому поясі вашого пристрою, найновіші зверху.

Коли номер кур'єра підходить одному перевізнику, що працює у вашій країні — як-от штрихкод USPS у США чи номер Straightship у Канаді — Peek показує "Delivery with" цього перевізника з його номером і також опитує його; міжнародний поштовий номер іде на пошту вашої країни. Поки той перевізник має лише повідомлення про посилку без власного скану, натомість показується "Announced to …, not received yet". Щойно цей перевізник показує скан не старіший за останній скан JS Express, Peek переходить на нього, показуючи обидва номери й зберігаючи скани JS Express. Доти він залишається на JS Express, чия історія часто містить і скани кур'єра.

Поки посилка відкрита у вас на екрані або в день доставки з увімкненими сповіщеннями, Peek перевіряє до кожних 10 хвилин і до кожних 2 хвилин (кожні 5, коли доводиться відкривати трекер JS Express у браузері) з 6:00 за місцевим часом у день виходу на доставку, поки відповідає власний трекер JS Express; інакше — щогодини. Через сервіси відстеження — щогодини, кожні 3 години після доби тиші та до кожних 15 хвилин у день доставки за тієї самої умови. Коли доставку перебирає інший перевізник, Peek дотримується його темпу. За номером SpeedX для посилки до США Peek може відстежувати її на [SpeedX](carrier:speedx). Після двох днів без новин (12 годин, поки посилка ще показує "Label created" або "Received by carrier") Peek перевіряє щогодини. Він може сповіщати про кожен скан, лише важливі кроки (митниця, проблеми, "Ready for pickup", вихід на доставку, доставка та повернення) або "Delivery only". Зв'язатися з JS Express чи продавцем замість вас Peek не може.

## Питання про відстеження JS Express

### Що таке JS Express (急速国际)?

Китайська логістична компанія для транскордонних онлайн-продавців під управлінням 急创速供应链管理（苏州）有限公司. Вона зазначає, що розпочала роботу в Гонконзі 2010 року й відкрила базу в Сучжоу 2011 року.

### Де можна відстежити посилку JS Express?

У полі вище або на англійській сторінці «Express Query» на js-exp.com. Китайська сторінка спочатку вимагає код із картинки.

### Що означає 未获取到任何数据 у відстеженні JS Express?

«Даних не знайдено»: трекер JS Express не знає номера через помилку під час введення або тому, що JS Express ще не отримав посилку. Перевірте номер у продавця.

### Хто доставляє посилки JS Express у моїй країні?

Кур'єр у вашій країні. В історії JS Express доставку виконують USPS, GOFO або SpeedX у США, Straightship у Канаді, Yodel у Великій Британії, DHL, iMile або J&T Express у Німеччині, Colissimo у Франції та CTT Express в Іспанії, а на частині ліній — FedEx. В інших країнах запитайте продавця, яка компанія доставляє.

### До кого звертатися щодо зниклої посилки JS Express?

До продавця, у якого ви замовляли: саме він оформив відправлення в JS Express і подає претензії. Гаряча лінія JS Express, 400-0353-518, — це китайський номер.

:::sources
- [JS Express: 急速国际 (головна сторінка)](https://www.js-exp.com/) – компанія-оператор
- [JS Express: Introduction](https://www.js-exp.com/EN/index.php?page=6) – Гонконг 2010, Сучжоу 2011, транскордонна електронна комерція
- [JS Express: 企业介绍 (про компанію)](https://www.js-exp.com/index.php?page=6) – Сучжоу 2011, філії, платформи
- [JS Express: Express Query](https://www.js-exp.com/EN/index.php?page=19) – англійський трекер, що показує, його відповіді
- [JS Express: 快件查询 (відстеження)](https://www.js-exp.com/index.php?page=19) – код із картинки
- [JS Express: Insurance Claims](https://www.js-exp.com/EN/index.php?page=29) – претензії клієнтів, 30 днів, недоставлені посилки
- [JS Express: 投保理赔 (страхові претензії)](https://www.js-exp.com/index.php?page=29) – 30 днів від отримання посилки JS Express, претензії з кабінету клієнта
- [JS Express: Complaint](https://www.js-exp.com/EN/index.php?page=32) – форма, гаряча лінія
- [JS Express: Contact Us](https://www.js-exp.com/EN/index.php?page=35) – гаряча лінія, QQ, WeChat, старіші номери та адреси
- [JS Express: 联系我们 (контакти)](https://www.js-exp.com/index.php?page=35) – гаряча лінія для міжнародних звернень, QQ, WeChat, офіси
- [JS Express: 国际专线 (міжнародні лінії)](https://www.js-exp.com/index.php?catid=13) – лінія в Німеччину: DPD та інші, понад 98 % за 8 робочих днів; лінія в Австралію: Australia Post, Fastway, DPEX
- [JS Express: 急速美国专线 (лінія в США)](https://www.js-exp.com/index.php?aid=27) – сплачені мита (DDP), повернення не везуть назад до Китаю й не відправляють повторно, доставлено але не отримано
- [JS Express: 急速欧洲专线 (європейська лінія)](https://www.js-exp.com/index.php?aid=35) – країни, сплачені мита (DDP), віддалені індекси
- [Aramex Australia: About Aramex Australia](https://www.aramex.com.au/why-aramex/about-us/) – Fastway стала Aramex Australia
- [Revenue: Union Handling Fee press release](https://www.revenue.ie/en/corporate/press-office/press-releases/2026/pr-092926-handling-fee.aspx) – 2 € за вид товару з 1 листопада 2026 року, сплачує продавець або компанія-доставник, може перекладатися
- [USPS: Missing Mail and Lost Packages](https://www.usps.com/help/missing-mail.htm) – відправники або отримувачі, від 7 днів
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – форми номерів, тексти сканів і етапи, часові пояси (рядки J&T Express Germany із зазначеним зміщенням від 9 листопада), скани USPS, GOFO, SpeedX, Straightship, Yodel, DHL, iMile, J&T Express Germany, FedEx, Colissimo та CTT Express, скани неназваного кур'єра (хаб, депо, спроби, переадресація, винятки), скани хабу неназваного партнера в США, німецькі центри iMile, резерв браузера, митниця, відмови й повернення, номер кур'єра, що показує Peek і як передає посилку
:::
