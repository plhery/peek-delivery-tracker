---
title: Отслеживание Speedaf Express: номера NG/KE/PK и контакты
description: Отследите посылку Speedaf Express (速达非) из Китая в Нигерию, Кению, Гану, Пакистан или ОАЭ: номера NG/KE/PK, статус pending Tax, 3 попытки и горячие линии.
slug: speedaf-otslezhivanie
published: 2026-11-27
updated: 2026-11-27
---

Speedaf Express (速达非, основана в 2019 году с участием ZTO среди первоначальных акционеров) доставляет авиапосылки интернет-торговли из Китая в Африку, на Ближний Восток, в Пакистан и ряд стран Европы силами собственных курьеров (ориентировочный срок для eParcel — 5–10 рабочих дней). Отследить накладную с кодом страны (`NG…`, `KE…`, `PK…`, `AE…`) заглавными буквами можно на [speedaf.com](https://www.speedaf.com/cn-en/send-parcel), в приложении Speedaf или в поле выше.

## Трек-номер Speedaf: как он выглядит

Номер Speedaf начинается с кода страны назначения: `NG` для Нигерии, `KE` для Кении и так далее. Два основных формата (цифры вымышленные):

| Номер | Что это за код |
| --- | --- |
| `NG482910573R` | Код страны, 9 цифр и буква — обычно `R`, иногда `E`. |
| `KE482910573649` | Код страны и 12 цифр. |
| `PK482910573649T` | То же самое с буквой `T` на конце. |
| `LP48291057364918` | Это не Speedaf, а референс Cainiao с AliExpress. |

Трекер Speedaf принимает до 20 номеров за раз, по одному в строке. Вводите их заглавными буквами: строчные буквы сайт воспринимает как неизвестный номер. Нет номера под рукой? См. [как найти трек-номер](guide:find-tracking-number) (на английском).

## Значения статусов отслеживания Speedaf

Speedaf пишет отметки на английском, иначе на китайском, а иногда на языке вашей страны. Пункты и склады указываются в скобках вроде `【…】` внутри строки. В обычном порядке следования из Китая:

| Что пишет Speedaf | Что показывает Peek | Что это означает |
| --- | --- | --- |
| `processing, order has been inbounded` | «Принята службой доставки» | Склад Speedaf в Китае (указанный перед текстом) принял посылку. |
| `processing assembling operation`, `processing, order has been outbounded` | «В пути» | Упакована в партию, затем отправлена со склада. |
| `clearance in progress` | «На таможне» | Экспортное декларирование в Китае. |
| `Delivery to the airport`, `Cargo flight departured`, `Cargo flight arrived` | «В пути» | Доставлена в аэропорт, рейс вылетел, затем приземлился в вашей стране. |
| `Clearance handover`, `Customs clearance in progress` | «На таможне» | Передана из аэропорта на таможню в вашей стране. |
| `pending Tax` | «На таможне» | Требуется оплата пошлины до выпуска с таможни (см. ниже). |
| `Customs clearance tax paid` | «На таможне» | Пошлина оплачена; оформление продолжается. |
| `Customs Clearance Completed` | «В пути» | Таможня выпустила посылку. |
| `Arrived at DC Warehouse`, `Arrival at Speedaf DC` | «В пути» | На складе или в распределительном центре Speedaf в вашей стране. |
| `Received in` | «В пути» | Первый пункт Speedaf в вашей стране принял её. Это не новый забор у отправителя. |
| `Loaded at`, `Departed for`, `Arrive at Distribution`, `Arrived at Site` | «В пути» | Движение между центрами Speedaf к пункту доставки. |
| `scanned for delivery` | «Передана курьеру» | У курьера на доставке. |
| `Issue Parcel Reason【Rescheduled delivery】` | «Попытка вручения» | Доставка перенесена на другой день (указан в скобках далее). |
| `Issue Parcel Reason【Receiver Rejected Parcel】` | «Требует внимания» | Получатель отказался от посылки. |
| `Issue Parcel Reason【Delayed payment of tax by customer】` | «На таможне» | Курьер вернул посылку из-за неуплаты пошлины. |
| `Please come to collect with the Pick-Up Code` | «Готова к выдаче» | Ждёт вас в пункте Speedaf, указанном перед текстом. Заберите по коду получения. |
| `Parcel delivered 【Collected】`, `【Parcel delivered】` | «Доставлена» | Вручена или получена. В строке может быть указан пункт выдачи. |
| `Transported to the warehouse，【Return Overdue】` | «Требует внимания» | Возвращается на склад Speedaf. |
| `Warehouse received` | «Возвращена отправителю» | Возвращённая посылка вернулась на склад Speedaf. |

Обычная строка `In Transit` иногда скрывает задержку: внутренний код Speedaf за ней означает удержание для инвентаризации. `Products not for personal use` и `Clearance Exception` означают вопрос или проблему на таможне; `Long Time Tax No Paid,Email To Sender` — пошлина долго не оплачивалась, и Speedaf связался с отправителем. На китайском ориентируйтесь на `进行处理，订单已操作入库` («Принята службой доставки»), `清关中` («На таможне»), `已做派件扫描` («Передана курьеру») и `包裹【已签收】` («Доставлена»).

## Доставка Speedaf: курьер, пункты выдачи и попытки

Speedaf использует собственных курьеров последней мили с доставкой до двери и оплатой при получении (COD). Страница «Look for Service Site» показывает пункты на карте.

Правила доставки зависят от условий конкретной страны (написанных для отправителей). Условия Нигерии и ОАЭ описывают внутреннюю доставку там:

- **ОАЭ:** «Every shipment will have 3 free delivery attempts» (три бесплатные попытки доставки); невручённая посылка возвращается отправителю.
- **Кения, Уганда и Пакистан:** посылка возвращается отправителю после третьей неудачной попытки, при отказе от посылки или при отказе оплатить сборы. Во всех трёх странах указано, что Speedaf доставляет по адресу, «но не обязательно лично в руки указанному получателю».
- **Нигерия и ОАЭ:** посылка признаётся невручённой при отказе от неё или от оплаты COD, при неверном адресе или адресе вне зоны Speedaf, либо если запрошенный день доставки превышает срок хранения Speedaf (сам срок в условиях не указан).

Предельный срок хранения в пунктах выдачи Speedaf не публикует.

## Таможенные пошлины Speedaf: статус pending Tax

Если требуется уплата пошлины, отслеживание останавливается на `pending Tax`. Для посылок в ЮАР на странице отслеживания Speedaf рядом с номером появляется ссылка «I want to pay taxes», а после оплаты — отметка «Taxes paid».

Если SMS или письмо требует оплату, сначала сверьте статус на speedaf.com: см. [посылки на таможне](guide:customs) (на английском).

## Посылка Speedaf задерживается, пропала или не доставлена

**Застряла.** Если статус не меняется несколько дней, см. [почему отслеживание не обновляется](guide:tracking-not-updating) (на английском).

**Кто подаёт претензию.** В условиях Кении, Уганды и Пакистана указано, что претензии решаются только между отправителем и SPEEDAF, а в условиях Нигерии и ОАЭ — что выплаты производятся только отправителю. Для интернет-заказа отправителем выступает магазин, поэтому за возвратом денег или повторной отправкой обращайтесь в магазин или на маркетплейс.

**Сообщайте магазину быстро.** Условия Speedaf устанавливают короткие сроки:

- **Повреждение:** письменно в течение 7 календарных дней с доставки в Кении и Уганде, 72 часов в Нигерии и ОАЭ и 48 часов в Саудовской Аравии.
- **Отмечена доставленной, но не получена:** оспаривание подтверждения вручения в течение 5 календарных дней в Нигерии и ОАЭ и 5 рабочих дней в Пакистане.
- **Утеря:** в Нигерии и ОАЭ жалоба на отслеживание в течение 14 календарных дней с приёма. При отсутствии сканов более 24 часов Speedaf ведёт поиск 7 рабочих дней, после чего, если посылка не найдена, признаёт её утраченной.

На странице отслеживания Speedaf указывается, кто расписался за посылку — проверьте это сначала. Подробнее в [статус «доставлена», но посылка не получена](guide:delivered-not-received) (на английском).

## Контакты и горячие линии Speedaf по странам

По состоянию на 28 октября 2026 года (Египет и Марокко — на 8 ноября, Нигерия и Китай — на 14 ноября 2026 года) региональные сайты Speedaf публикуют внизу страниц следующие контакты (Ирландия и Замбия открываются через выбор страны и английского языка):

| Страна | Телефон | Эл. почта | Часы работы |
| --- | --- | --- | --- |
| Нигерия | +234 800 070 7070, +234 708 063 6400, +234 9073064940 (также WhatsApp) | ng.info@speedaf.com, nigeria.info@speedaf.com | Пн–Пт 8:00–17:00, Сб 8:00–14:00 |
| Гана | +233 30 824 9100 | gh.info@speedaf.com | Пн–Сб 8:30–17:30 |
| Кения | +254 741 000 666, +254 741 000 888; WhatsApp +254 741000666 | kenya.info@speedaf.com | Пн–Пт 8:00–18:00, Сб 8:00–14:00 |
| Уганда | +256 709 198 795 | ug.info@speedaf.com | Пн–Пт 9:00–17:00, Сб 9:00–14:00 |
| Замбия | +260 971269390 | zm.info@speedaf.com | Пн–Пт 7:45–17:15, Сб 8:00–13:00 |
| ЮАР | +27 108247184, +27 726393849; WhatsApp +27 664562613 | cs.southafrica@speedaf.com | не указаны |
| Пакистан | +92 311 118 7654; WhatsApp +92 3450010817 | pk.info@speedaf.com | Пн–Вс 9:00–18:00 |
| ОАЭ | +971 800 178 | ae.info@speedaf.com | Пн–Сб 9:00–18:00 |
| Саудовская Аравия | не указан | sa.info@speedaf.com | не указаны |
| Ирландия | +35312633068; WhatsApp +353 834281088 | info_customerserviceie@speedaf.com | Пн–Пт 8:30–17:30 |
| Египет | +20 15818, +20 1050383370 | eg.info@speedaf.com | Сб–Чт 9:00–18:00 |
| Марокко | +212 522 100 888 | ma.info@speedaf.com | Пн–Пт 9:00–18:00 |
| Китай | +86 400 888 0975 | info@speedaf.com | Пн–Пт 9:30–18:30 |

Справочный центр Speedaf добавляет горячие линии для остальных стран: Казахстан +7 778 371 9358, Маврикий 4687316, Мальдивы +960 7678788, Швейцария и Сербия +41 798559737, Черногория +382 60021040, Албания +355 45620111. На каждом сайте также есть форма «Customer Support».

## Отслеживание посылки Speedaf в Peek

[Peek](/) сразу распознаёт номер Speedaf по формату, когда он начинается с `NG`, `GH`, `KE`, `UG`, `RW`, `ZM`, `ZA`, `EG`, `MA`, `SA`, `AE`, `PK`, `KZ`, `MU`, `RE`, `MV` или `TR`, за которыми следуют 9 цифр и `R` или `E`. Если вместо этого после одного из этих кодов или `IE` идут 12 цифр, одной формы недостаточно: Peek опрашивает Speedaf — появляется «Проверяем сервисы отслеживания…», а затем «Эта посылка есть в Speedaf.», если служба знает номер. Для Швейцарии, Албании, Черногории и Сербии у Peek нет шаблона номера: для них и для прочих номеров выберите Speedaf через «Изменить». Ссылка отслеживания speedaf.com с параметром `tradeNo=` распознаётся как Speedaf при любом номере. Peek сам переводит номер в заглавные буквы, поэтому строчные подходят.

Peek читает собственный трекер Speedaf: статус, историю, страну назначения и время доставки. Каждый скан идёт по времени своего пункта (сначала китайскому на складах, затем местному вашей страны), а Peek отображает его в часовом поясе вашего устройства; на peektracker.com и в приложениях для iPhone и Android история заканчивается строкой «Время указано в вашем часовом поясе:» и его названием. Speedaf указывает названия пунктов только внутри текста, поэтому карты нет, а ожидаемая дата доставки не передаётся. Если Speedaf знает номер, но сканов нет (как у части старых посылок), Peek показывает «Ждём Speedaf» (или «Ждём первой отметки от Speedaf» для 12-значного номера без выбранной службы) и продолжает проверки. Если спустя два дня у посылки под Speedaf так и не появилось сканов и другие сервисы не ответили, Peek предлагает проверить номер, сменить службу или удалить посылку и переходит на ежедневные проверки (а через неделю после добавления посылки — на еженедельные).

Строки Speedaf выводятся в исходном виде (на английском или китайском), без имён курьера и сотрудника и без телефона курьера. Имя расписавшегося за вручение видно вам в скане доставки, но при отправке публичной ссылки скрывается и заменяется на `Delivered`. Peek также опускает подтверждение вручения, ссылку на оплату пошлины и число коробок, и следит только за добавленным номером. Поскольку Speedaf доставляет своими силами, второго перевозчика нет.

Пока карточка посылки открыта у вас, или в день доставки при включённых уведомлениях, Peek проверяет обновления до раза в 10 минут, а с 6:00 по местному времени в день передачи курьеру — каждые 2 минуты, пока отвечает собственный трекер Speedaf (через агрегаторы отслеживания — до раза в 15 минут); в остальное время — раз в час. После двух дней без новостей (12 часов в статусе «Этикетка создана» или «Принята службой доставки») проверка идёт раз в час. Доступны уведомления «Каждая отметка», «Важные этапы» или «Только доставка». Peek не может оплатить пошлину, перенести доставку или связаться со Speedaf либо магазином за вас.

## Вопросы об отслеживании Speedaf

### Является ли Speedaf Express надёжной службой?

Да. Speedaf — логистическая компания, основанная в 2019 году с участием ZTO среди первоначальных акционеров; она ведёт собственный трекинг на speedaf.com и курьерскую доставку последней мили в Африке, на Ближнем Востоке, в Пакистане и ряде стран Европы.

### В какие страны доставляет Speedaf и сколько идёт посылка?

Помимо Китая Speedaf указывает Египет, ЮАР, Марокко, ОАЭ, Гану, Нигерию, Кению, Уганду, Саудовскую Аравию, Пакистан, Казахстан, Маврикий, Мальдивы, Швейцарию, Черногорию, Албанию, Ирландию, Сербию и Замбию. Услуга eParcel заявляет ориентировочный срок 5–10 рабочих дней из Китая (без гарантии).

### Что означает «pending Tax» в трекинге Speedaf?

Необходимо оплатить таможенную пошлину или налог до выпуска посылки таможней вашей страны (для ЮАР на speedaf.com появляется ссылка «I want to pay taxes»). После оплаты следуют статусы `Customs clearance tax paid` и `Customs Clearance Completed`.

### Сколько попыток доставки делает Speedaf?

В ОАЭ условия Speedaf предусматривают 3 бесплатные попытки доставки, а в Кении, Уганде и Пакистане посылка возвращается отправителю после третьей неудачной попытки либо при отказе от посылки или уплаты сборов.

### Как связаться со службой поддержки Speedaf в Нигерии, Кении, Гане, Пакистане или ОАЭ?

Воспользуйтесь локальной горячей линией или почтой из таблицы выше — например, Нигерия `+234 800 070 7070`, Кения `+254 741 000 666`, Гана `+233 30 824 9100`, Пакистан `+92 311 118 7654` или ОАЭ `+971 800 178`. Поскольку претензии решаются только с отправителем, также оперативно свяжитесь с магазином.

:::sources
- [Speedaf: Enterprise Introduction](https://www.speedaf.com/ng-en/business/about-us) – founded 2019, ZTO
- [Speedaf: Select your location and language](https://www.speedaf.com/cn-en/user-opt/locale-select) – countries; Ireland's and Zambia's contacts (pick the country, then English); China's contacts (read 14 November 2026)
- [Speedaf: Speedaf eParcel](https://www.speedaf.com/ke-en/product/intl-straight-small) – parcels from China for online sellers, 5-10 working days
- [Speedaf: Warehouse & Fulfillment](https://www.speedaf.com/ke-en/product/warehouse-promise) – own last-mile couriers, COD
- [Speedaf: Last-mile delivery](https://www.speedaf.com/ke-en/product/warehouse-delivery) – COD, door-to-door
- [Speedaf: Help Center](https://www.speedaf.com/ng-en/help) – transit time; tracking on the website or the customer app, hotlines by country (read 14 November 2026)
- [Speedaf: Track](https://www.speedaf.com/ng-en/send-parcel) – up to 20 numbers; duty link for South Africa
- [Speedaf: Look for Service Site](https://www.speedaf.com/ke-en/location-map) – map of sites
- [Speedaf: Customer Support](https://www.speedaf.com/ng-en/service/support) – contact form
- [Speedaf Nigeria: Home](https://www.speedaf.com/ng-en) – phones, emails, hours, WhatsApp (read 14 November 2026)
- [Speedaf Ghana: Home](https://www.speedaf.com/gh-en) – phone, email, hours
- [Speedaf Kenya: Home](https://www.speedaf.com/ke-en) – phones, email, hours, WhatsApp
- [Speedaf Uganda: Home](https://www.speedaf.com/ug-en) – phone, email, hours
- [Speedaf South Africa: Home](https://www.speedaf.com/za-en) – phones, email, WhatsApp
- [Speedaf Pakistan: Home](https://www.speedaf.com/pk-en) – phone, email, hours, WhatsApp
- [Speedaf UAE: Home](https://www.speedaf.com/ae-en) – phone, email, hours
- [Speedaf Saudi Arabia: Home](https://www.speedaf.com/sa-en) – email
- [Speedaf Egypt: Home](https://www.speedaf.com/eg-en) – phones, email, hours (read 8 November 2026)
- [Speedaf Morocco: Home](https://www.speedaf.com/ma-en) – phone, email, hours (read 8 November 2026)
- [Speedaf Kenya: Terms and Conditions](https://www.speedaf.com/ke-en/service-rule) – attempts, refusal, receiver, claims, delivery times
- [Speedaf Uganda: Terms and Conditions](https://www.speedaf.com/ug-en/service-rule) – the same for Uganda
- [Speedaf Pakistan: Terms and Conditions](https://www.speedaf.com/pk-en/service-rule) – attempts, receiver, claims, disputed delivery
- [Speedaf UAE: Terms & Conditions](https://www.speedaf.com/ae-en/service-rule) – domestic delivery: attempts, undeliverable parcels, claim windows, claims with the shipper
- [Speedaf Nigeria: Service Agreement](https://www.speedaf.com/ng-en/service-rule) – domestic delivery: undeliverable parcels, claim windows, claims with the shipper
- [Speedaf Saudi Arabia: Express Shipping Terms](https://www.speedaf.com/sa-en/service-rule) – damage within 48 hours
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – network, number shapes (RE, RW and TR checked 8 November 2026), scan wordings and meanings, what Speedaf's page and Peek show
:::
