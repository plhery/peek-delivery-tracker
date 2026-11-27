---
title: Відстеження JRT: номер JRT…MM, статуси та місцева доставка
description: Відстежуй посилку JRT (今日通, Jinritong) із Сяменя (Китай): формат JRT…MM, китайські й англійські авіаскани, передача в DHL/UPS/FedEx і до кого звертатися.
slug: jrt-vidstezhennia
published: 2026-11-27
updated: 2026-11-27
---

JRT (今日通, Jinritong, 厦门今日通商贸有限公司) — міжнародний експедитор із Сяменя в Китаї, чиї трек-номери складаються з `JRT`, десяти цифр і `MM`. Встав свій номер `JRT…MM` у поле вище, щоб відстежувати посилку з Китаю через виліт, імпортне розмитнення та передачу місцевому перевізнику: трекер JRT передає сканування DHL у Європі (CTT Express в Іспанії, InPost у Польщі та FedEx на маршруті до Ірландії), UPS у США та J&T Express у Мексиці.

## Трек-номер JRT: як він виглядає

Власні накладні JRT складаються з `JRT`, десяти цифр і `MM`, усього 15 символів; `JRT` — ініціали 今日通. Приклади з вигаданими цифрами:

| Номер | Що це |
| --- | --- |
| `JRT4829105736MM` | `JRT`, десять цифр і `MM`: стандартна накладна JRT. |
| `JRT482910573MM` | Лише дев'ять цифр: не формат JRT. Перевір, чи не пропущено цифру. |
| `JRTO4829105736MM` | Літера `O` після `JRT`: Peek читає його як `JRT4829105736MM` — єдиний формат, який знає трекер JRT. |
| `UP4829105736182940` | `UP` і шістнадцять цифр: референс відправника, за яким може знайтися замовлення JRT, хоча немає підтвердження, що його використовують лише відправники JRT. |
| `RR482910576CN` | Дві літери, дев'ять цифр і `CN`: номер [China Post](carrier:china-post), а не JRT. |

Пробіли й малі літери у Peek не мають значення. Трекер JRT також знаходить замовлення за власним референсом продавця, якщо ввести його точно так, як надав продавець, разом із дефісами.

**Посилання з цифрами замість домену.** Трекер JRT працює на голій адресі сервера через незашифрований HTTP без вебдомену, і Peek не знає жодного іншого сайту JRT. Посилання на нього має числову адресу замість назви сайту та містить `trackIndex.htm`, `documentCode=` і номер або референс: його можна вставити в Peek як є. Сам Peek не публікує посилання на трекер, оскільки HTTP-посилання передавало б твій номер у відкритому вигляді.

## Значення статусів відстеження JRT

Багато рядків JRT записані китайською з власною англійською версією JRT поруч, і Peek наводить їх повністю. Оскільки інтерфейс застосунку Peek не має української локалізації, власні етапи Peek показуються англійською: «Label created» (етикетка створена), «Received by carrier» (прийнято перевізником), «In transit» (у дорозі), «At customs» (на митниці), «Out for delivery» (передано кур'єру), «Delivery attempted» (спроба доставки), «Ready for pickup» (готово до видачі), «Delivered» (доставлено) та «Returned to sender» (повернено відправнику). У порядку проходження посилки:

| JRT пише | Що це означає для тебе | Peek показує |
| --- | --- | --- |
| `货物电子信息已经收到` | «Отримано електронні дані про вантаж»: продавець створив замовлення. Автоматичний запис платформи Hualei (华磊), а не фізичне сканування. | «Label created» |
| `Shipment information received` | Дані відправлення надійшли до JRT; посилку ще ніхто не сканував. | «Label created» |
| `到达收货点` | Прибула до пункту приймання JRT: перше фізичне сканування в руках JRT. | «Received by carrier» |
| `货物离开操作中心` | Посилка залишила операційний центр JRT. | «In transit» |
| `深圳集散中心 已收货` | Хаб JRT у Шеньчжені прийняв посилку після операційного центру та перед рейсом. Peek показує англійською: «Received at the Shenzhen distribution center». | «In transit» |
| `货物打包完成，已发往航司` | Запаковано й відправлено авіакомпанії перед перельотом. Peek показує «Goods packed and sent to the airline». | «In transit» |
| `抵达航司已起飞` | У авіакомпанії, рейс вилетів із зазначеного аеропорту. Peek показує «Arrived at the airline, flight departed». | «In transit» |
| `货物已交机场,等待航班安排中 Cargo delivered to airport, pending flight arrangement` | Передано в аеропорт в очікуванні рейсу. «Delivered» означає доставку в аеропорт, а не до тебе. | «In transit» |
| `报关已放行等待…号航班起飞 Customs released,Awaiting departure on flight of the …` | Пройдено експортне оформлення в Китаї, очікується виліт зазначеної дати. | «In transit» |
| `航班起飞，以实际抵达为准 Flight departure subject to actual arrival time` | Літак вилетів. | «In transit» |
| `航班落地，拆板中 Flight landing, dismantling board in progress` | Літак приземлився. Авіапалети розбирають перед митницею. | «In transit» |
| `机场内等待提货，等待清关 Waiting for cargo pickup and customs clearance at the airport.` | В аеропорту призначення, очікує забирання й імпортного розмитнення. | «At customs» |
| `理货较慢，导致提货缓慢，已催促尽快处理 Slow cargo sorting has resulted in delayed pickup. We have followed up for prompt handling.` | Повільне сортування в аеропорту затримує забір вантажу, JRT попросила пришвидшити обробку. Це затримка, а не втрата. | Без зміни статусу: лишається попередній |
| `已提货，清关中 The goods have been picked up and are currently undergoing customs clearance` | Триває імпортне митне оформлення. | «At customs» |
| `清关完成，卡车发运 Customs clearance completed; truck dispatched.` | Митницю пройдено, вантаж вирушив вантажівкою. | «In transit» |
| `已交服务商 Delivered to service provider` | Передано компанії, що виконує доставку на останній милі. Не доставлено тобі. | «In transit» |

Окремі рядки, які JRT пише лише китайською, Peek показує англійською: `货物电子信息已经收到` як «The electronic cargo information has been received», `到达收货点` як «Arrived at the receiving point», `货物离开操作中心` як «Goods leave the operation center» та `快件操作完成` як «Shipment departed from facility». Рядок бронювання рейсу `货物已交机场,预计…号的航班 Goods delivered to airport, scheduled on flight of the …` Peek відображає як «Goods delivered to airport», адже рейс ще попереду. Ще три китайські рядки до вильоту теж мають етап «In transit»: `分拣包裹已出库` («Sorted parcel has left the warehouse»), `货物到达口岸，预计…号航班起飞` («Arrived at the port, booked on a flight») і `预计…号航班飞` («Booked on a flight»).

На європейському маршруті три пізніші рядки також надходять лише китайською, і Peek показує їх англійською як «In transit»: `航班已抵达` («Flight has arrived»), `清关完成，中转德国中` («Customs cleared, in transit to Germany») та `抵达德国，已交服务商` («Arrived in Germany, handed to the delivering carrier» — передано службі доставки, як-от DHL, а не вручено тобі).

**Автошляхом через Казахстан.** На наземній лінії JRT до Європи посилка прямує суходолом замість авіа, із п'ятьма китайськими рядками, які Peek показує англійською під «In transit»: `已装柜发车，待抵达新疆` («Loaded into a container and on its way to Xinjiang»), `已抵达新疆，换装/报关，待出境` («Arrived in Xinjiang for transloading and export declaration, waiting to leave China»), `因哈萨克斯坦拥堵，外车入境新疆拥堵缓慢，在等待外车倒装后报关出境。` («Congestion in Kazakhstan is slowing foreign trucks into Xinjiang; the parcel will be declared for export once it is reloaded onto one» — затор на кордоні, не втрачена посилка), `已出境，哈萨克斯坦阿拉木图` («Left China, Almaty, Kazakhstan») і `哈俄边境，待办理手续进入俄罗斯` («At the Kazakhstan–Russia border, awaiting the formalities to enter Russia» — транзитний кордон дорогою, а не твоя митниця).

**Рядки лише англійською.** Інші події JRT приходять тільки англійською, усі під «In transit», якщо не вказано інше:

- `Shipment arrived at facility`, `Sending to the airport`, `The goods have been packed and sent to the airline`, `Package has been packed and delivered to airport`, `Cargo handed over to the airline`: на шляху до рейсу. `Domestic customs clearance completed` — китайська експортна митниця, а не митниця твоєї країни.
- `Arriving at the airport - arranging flights`, `Arriving at the airline, in the process of booking a flight`: бронювання рейсу.
- `Flight is expected to take off on …`, `The flight is expected to depart on … and land on …`, `Estimated departure is …`, `Estimated arrival date is …`: заплановані дати рейсу у форматі місяць.день (`11.12` — 12 листопада).
- `Flight has flown`, `The flight has departed and is expected to … land`, `The flight has taken off`: у польоті.
- `Sorry, the second flight has been delayed and is expected to … land`: затримка стикувального рейсу з новою датою прильоту.
- `During transportation to …`: перевезення до вказаної країни призначення.
- `Flight has arrived`, `The flight has arrived and is awaiting dismantling`, `Arrived at Destinated Airport`, `The NOA has been received` (повідомлення авіакомпанії про прибуття), `Dismantling and picking up goods in progress`: приземлилася перед митницею.
- `Customs clearance of the cargo is in progress` та `Arrived at customs clearance warehouse`: «At customs». Далі `Customs clearance completed` або `Customs clearance in destination country completed`.
- `In transit to final service provider`, `Delivery to final service provider`, `The goods have been handed over to the carrier`, `The cargo has been picked up and arrived at the warehouse，awaiting outbound shipment`, `It has been handed over to the service provider.` і `It has been transferred to the provider`: на шляху до місцевого перевізника або передано йому, не доставка тобі.
- `returned shipment has been received by processing center`: повернене відправлення повернулося до центру JRT («Returned to sender»).

**Рядки через скісну риску або кому.** Деякі посилки поєднують китайську й англійську через `/` або кому, усі «In transit»: `预计…航班/Flight scheduled for …`, `航班已启运/Package in transit to destination country`, `航班已落地，拆板中/Package arrived at destination country`, `货物已清关完成/Import customs clearance complete`, `国内清关完成` (китайська експортна митниця), `预计配载航班…月…日,Expected flight on …` (дата рейсу, не дата доставки) і `航班已起飞,The flight has taken off from the origin`.

**Скопійовані рядки DHL, FedEx, UPS і J&T Express Mexico.** До Європи трекер додає скани DHL переважно малими літерами: `the instruction data for this shipment have been provided by the sender to dhl electronically` («Label created»), `shipment in transit to dhl`, `A preferred location was designated as a reception option for this shipment.` (обране місце доставки, статус не змінює), `The shipment has been posted by the sender at the retail outlet` (JRT здала посилку у відділенні Deutsche Post), `pick-up was successful.` (DHL забрала посилку після передачі), `the shipment has been processed in the parcel center of origin`, `loaded to movement / tour vehicle`, `The shipment has arrived in the destination country/destination area.`, `The shipment has left the import parcel center in the destination country/destination area.`, `The shipment is being prepared for delivery in the delivery depot` («In transit»); `The shipment has been loaded onto the delivery vehicle` («Out for delivery»); `not delivered-consignee could not be reached`, `The shipment could not be delivered. Please find more information here.`, `The recipient was not present. Please find more information here.` і `not delivered-bad address` («Delivery attempted»); `held for consignee collection` і `The shipment is ready for collection from a ParcelShop/retail outlet. Please find more information here.` («Ready for pickup»); `shipment will be returned to origin-not ordered` та `shipment will be returned to origin-bad address` («Returned to sender»); і `the shipment has been successfully delivered` («Delivered», або «Returned to sender» після рядка повернення). На ірландському маршруті FedEx трапляються `Shipment information sent to FedEx` («Label created»), `Arrived at FedEx hub`, `Departed FedEx hub`, `On the way` і `Delivery option requested, hold request received - check back later for shipment status` («In transit»). На лінії до США спершу йдуть китайські рядки рейсу (`…号航班`, `航班已飞`, `航班抵达`, `提货中`, `清关完成，已提货，待交仓`, `已交仓`), а далі китайські скани UPS, які Peek показує англійськими фразами UPS (`Shipper created a label, UPS has not received the package yet.`, `Awaiting delivery to a UPS Access Point location.`, `Arrived at Facility`, `Departed from Facility`, `Processing at UPS Facility`, `Picked Up`, `Loaded on Delivery Vehicle`, `Out For Delivery Today`, `Delivered`). На лінії до Мексики копіюються іспанські рядки J&T Express Mexico з містом і відділенням у 【】-дужках аж до `…está en camino a tu domicilio.` («Out for delivery»).

**Час.** Власні рядки JRT йдуть за пекінським часом (`UTC+8`): взимку 17:00 у Пекіні — це 11:00 за київським часом (`UTC+2`) і 10:00 у Берліні (`UTC+1`). Скопійовані рядки DHL Peek читає за місцевим часом зазначеного міста (або німецьким часом / часом країни призначення, якщо міста немає), скани UPS — за місцевим часом у США, а J&T Express Mexico — за часом Мехіко.

## Хто доставляє посилки JRT у Європі та за її межами

**У Європі** більшість ліній JRT завершуються доставкою [DHL](carrier:dhl), за винятком лінії [FedEx](carrier:fedex) до Ірландії, лінії до Іспанії (CTT Express) та лінії до Польщі (InPost). На маршрутах DHL компанія JRT передає посилку у відділенні Deutsche Post, і її запис отримує номер DHL замість номера JRT. Коли Peek розпізнає цей номер як DHL, він показує «Delivery with DHL» із номером (або «Announced to DHL, not received yet», поки DHL має лише попереднє повідомлення) та переходить на власний трекінг DHL після першого сканування. Для інших країн або коли локальний номер не відображається запитай продавця, яка місцева компанія доставляє замовлення і який її трек-номер: спроби доставки, пункти видачі та терміни зберігання визначає саме місцевий перевізник.

**Імпортні збори в ЄС.** На авіалініях JRT посилка проходить імпортне розмитнення в аеропорту прильоту (рядки «At customs» вище). У країнах Європейського Союзу з 1 листопада 2026 року стягується збір ЄС за обробку (Union handling fee) 2 € за категорію товару на онлайн-покупки з-за меж ЄС додатково до мита чи ПДВ; детальніше — у посібнику про [посилки на митниці](guide:customs) (англійською).

**У США та Мексиці.** У США лінії JRT передаються UPS (`已交仓`), а в Мексиці — J&T Express Mexico.

## Посилка JRT не приходить або відстеження зависло

**Лише початкові рядки.** `货物电子信息已经收到`, `Shipment information received` або самий рядок етикетки DHL/FedEx означають, що продавець оформив відправлення, але посилку ще не сканували. Якщо за кілька днів нічого не зміниться, запитай продавця, чи її справді відправили.

**Очікування рейсу.** Рядок рейсу вказує запланований день вильоту, а `航班延误，预计…号飞出 Flight delayed, estimated departure on …` — нову дату. Якщо ця дата минула без вильоту, повідом продавця.

**Тиша після `已交服务商`, `Delivery to final service provider` або `已交仓`.** Свою частину JRT виконала. Якщо трекер не копіює скани DHL, UPS, FedEx чи J&T і Peek не показує місцевого номера, подальші події є на сайті місцевого перевізника: попроси цей номер у продавця. Більше — у посібнику про [відстеження посилок із Китаю](guide:tracking-from-china) (англійською).

**Нічого не знайдено.** На невідомий номер трекер JRT повертає один порожній рядок із номером як референсом. Peek показує «Waiting for JRT», а за два дні — «Check the tracking number». Звір номер із повідомленням продавця символ за символом.

**Помилка адреси.** Після `not delivered-bad address` перевір адресу в замовленні й негайно напиши продавцю. Якщо з'явиться `shipment will be returned to origin-bad address`, посилка повертається: проси у продавця повернення коштів або нову відправку.

**Позначено як доставлену, але посилки немає.** Скан доставки належить місцевій службі, а не JRT: звернися до неї, а потім до продавця. Більше — у посібнику про [посилку зі статусом «Доставлено», якої немає](guide:delivered-not-received) (англійською).

**Загублена або повернута.** Відправлення через JRT замовляв продавець, тому щодо компенсації чи повторної відправки звертайся до продавця або майданчика.

## Контакти та підтримка JRT

Peek не знайшов контактних даних JRT: жодного іншого сайту крім трекера, а також жодного телефону, електронної пошти чи адреси для отримувачів. Щодо замовлення звертайся до продавця, а якщо він не відповідає — до підтримки майданчика.

## Відстеження посилки JRT у Peek

[Peek](/) одразу розпізнає номер `JRT…MM` за його унікальною формою (`JRT`, десять цифр і `MM`) і за посиланням на трекер JRT. Для 16-значного референсу `UP` Peek запитує трекер JRT і вибирає JRT у разі збігу. Будь-який інший референс теж можна вставити — Peek підтримує понад 4 500 перевізників — і вибрати JRT вручну через «Change» (пошук за 今日通 або Jinritong також працює) чи «Change carrier» на вже доданій посилці. Тоді Peek показує номер JRT під «JRT tracks this as» до моменту передачі; оскільки Peek прибирає дефіси, для референсу з дефісами попроси у продавця номер `JRT…MM`.

Peek показує статус, кожен рядок із місцем та країну призначення, орієнтуючись на самі події, а не на загальний підсумок. Час переводиться у часовий пояс твого пристрою. Peek приховує рядки, записані більш ніж за 12 годин до зазначеного в них часу (або більш ніж за 6 годин у ту саму секунду з таким рядком), і рядки в майбутньому. Тоді історія до доставки чи повернення відкривається рядком «Other sites may show more steps, but they’re only predictions.», а «Show them» показує їх сірим («Predicted for …») без зміни статусу та сповіщень. Імена, адресу, індекси, вагу, збори, підпис і підтвердження вручення Peek приховує, а очікуваної дати доставки немає, бо JRT її не надає.

Коли в записі JRT з'являється номер DHL, FedEx, [Swiss Post](carrier:swiss-post), міжнародний поштовий номер (дві літери, дев'ять цифр, дві літери) або номер, що однозначно відповідає іншому перевізнику, Peek запитує і його та перемикається на нього після появи не старішого скану, зберігаючи історію JRT. Будь-який інший локальний номер від продавця додай окремою посилкою.

Поки посилка відкрита у Peek або в день доставки з увімкненими сповіщеннями, перевірки відбуваються до кожних 10 хвилин; інакше та після затишшя (12 годин на перших рядках чи два дні без оновлень) — щогодини. Після статусу «Out for delivery» Peek перевіряє до кожних 2 хвилин з 06:00 за місцевим часом у день розвозу, поки JRT або місцевий перевізник відповідає напряму (кожні 5 хвилин, якщо потрібен браузер, і щогодини / кожні 3 години після тихого дня / до кожних 15 хвилин у день доставки через сервіси відстеження). Сповіщення налаштовуються на «Every scan», «Important steps» (митні та поворотні рядки JRT і скопійовані події розвозу, невдалої спроби, пункту видачі, доставки та повернення від DHL, UPS і J&T — без рейсів і `已交服务商`) або «Delivery only». Peek не може зв'язатися з JRT чи продавцем замість тебе.

## Поширені запитання про JRT

### Що таке JRT (今日通 / Jinritong) і як виглядає номер JRT…MM?

JRT — це 厦门今日通商贸有限公司 (Xiamen Jinritong), експедитор із Сяменя в Китаї. Його накладні складаються з 15 символів (`JRT`, десять цифр і `MM`; дев'ять цифр означають пропущену цифру), а трекер працює на платформі Hualei (华磊).

### Чому JRT пише «delivered», якщо посилка не прийшла?

Через англійські формулювання JRT: `Cargo delivered to airport` означає, що JRT доставила посилку в аеропорт для перельоту, а `Delivered to service provider` або `Delivery to final service provider` — передачу місцевому перевізнику. Жоден із них не означає доставку тобі; Peek залишає статус «In transit».

### Хто доставляє посилки JRT у Європі, США та Мексиці?

У Європі більшість маршрутів JRT передаються DHL (CTT Express в Іспанії, InPost у Польщі та FedEx на лінії до Ірландії). У США доставляє UPS, у Мексиці — J&T Express Mexico. Для інших країн запитай у продавця назву місцевої компанії та її трек-номер.

### Чи діють митні збори ЄС на посилки JRT із Китаю?

Для онлайн-замовлень, надісланих з-за меж ЄС до країн Євросоюзу, з 1 листопада 2026 року діє збір ЄС за обробку 2 € за категорію товару разом із відповідним митом або ПДВ; подробиці — у посібнику про [посилки на митниці](guide:customs) (англійською).

### Як зв'язатися з JRT щодо затриманої чи зниклої посилки?

Звертайся до продавця або торговельного майданчика, де зроблено замовлення, адже доставку оформлював продавець. Крім незашифрованого HTTP-трекера, Peek не знайшов для JRT жодного сайту, телефону чи пошти для отримувачів.

:::sources
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – назва компанії та ініціали, платформа Hualei, трекер на голій адресі HTTP, посилання без публікації, формати `JRT…MM` і `UP`, літера `O` після `JRT` як `JRT`, пошук за номером або референсом, формулювання рядків і етапи, англійська для китайських рядків, сухопутний маршрут через Сіньцзян і Казахстан, скопійовані скани DHL, FedEx, UPS і J&T Express Mexico, передача в DHL, FedEx і Swiss Post, пекінський та місцевий час, відповідь для невідомого номера і що Peek показує, приховує та пропускає
- [Європейська комісія: EU Customs Reform (англійською)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – збір ЄС за обробку 2 € з 1 листопада 2026 року
:::
