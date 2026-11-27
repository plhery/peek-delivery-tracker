---
title: Отслеживание Omniva: коды JJEE, статусы и пакоматы
description: Отследите посылку Omniva (Почта Эстонии) в Эстонии, Латвии и Литве: коды JJEE и EE, значения статусов, хранение 4 дня в пакоматах, продление и телефоны.
slug: omniva-otslezhivanie
published: 2026-11-27
updated: 2026-11-27
---

Omniva (Почта Эстонии / Eesti Post) обеспечивает почтовую доставку и сеть пакоматов в Эстонии, Латвии и Литве. Отследить отправление Omniva можно на minu.omniva.ee, в приложении Omniva или вставив код отслеживания формата `JJEE` или `EE` в поле выше. Код приходит в письме магазина об отправке либо в SMS или письме от Omniva.

## Трек-номер Omniva: как он выглядит

Omniva называет его кодом отслеживания отправления («shipment tracking code»). В англоязычной справке по возвратам приведён образец `CEXXXXXXXXEE`. Ниже — основные форматы (все цифры вымышленные):

| Код | Что это за номер |
| --- | --- |
| `JJEE48291736405829173` | Посылка Omniva: `JJEE` и 17 цифр, всего 21 символ. |
| `JJEEMKP48291736405` | Ещё один формат посылки Omniva: `JJEE`, три буквы и 11 цифр. |
| `CE482917367EE` | Две буквы, девять цифр, `EE`: отправление, переданное в Omniva в международном почтовом формате. Посылки в пакоматы Латвии и Литвы тоже могут иметь окончание `EE`. |
| `RR739402812CN` | Посылка из-за границы сохраняет номер отправителя; этот номер заканчивается на `CN` (отправлен из Китая). |

Шестизначный код дверцы из SMS-уведомления открывает ячейку пакомата и не является трек-номером; шестизначный код возврата тоже не подходит для отслеживания.

Код с окончанием `LV` принадлежит [Latvijas Pasts](carrier:latvijas-pasts), с окончанием `LT` — [Lietuvos paštas](carrier:lietuvos-pastas), а коды `JJFI` — Posti в Финляндии. Подробнее о форматах: [форматы трек-номеров](guide:tracking-number-formats) (на английском).

## Значения статусов отслеживания Omniva

Страницы отслеживания Omniva работают на английском, эстонском, латышском, литовском и русском языках, а приложение Omniva отдаёт этапы на английском. Раздел «Track & trace details» открывает полную хронологию. Обычные статусы примерно по порядку:

| Статус Omniva | Что это означает |
| --- | --- |
| `Shipment registered. Shipment has not been handed over to Omniva` | Отправитель зарегистрировал посылку, но ещё не передал её в Omniva. |
| `Sender has placed the shipment in the parcel machine` | Отправитель поместил посылку в пакомат. |
| `The courier has picked up the shipment` | Курьер Omniva забрал посылку у отправителя. |
| `Shipment has been posted by the sender or collected by the postal service` | Посылка из-за рубежа принята почтовой службой в стране отправления. |
| `Shipment held in customs` | Ожидает таможенного оформления. |
| `Shipment has arrived at a postal facility for further processing` | В сети Omniva — на терминале или сортировочной линии, указанной рядом. |
| `The shipment has been handed over to the courier` | Передана курьеру для доставки. |
| `Shipment is ready for collection from the parcel machine` | Ждёт вас в указанном пакомате. Используйте шестизначный код дверцы. |
| `A notification of shipment arrival has been sent` | Omniva отправила вам SMS, письмо или уведомление в приложении. Посылка не перемещалась. |
| `Delivery attempt made` | Курьер пытался вручить посылку, но попытка не удалась. |
| `Shipment delivered` | Доставлена или получена; указывается пакомат или почтовое отделение. |
| `Shipment is being returned to the sender` | Возвращается отправителю. |

Два статуса часто вводят в заблуждение: `Delivery attempt made` означает неудачную попытку доставки, а `Shipment delivered` после возврата означает, что посылка вернулась к отправителю.

## Пакоматы Omniva, курьеры и пропущенные доставки

**Уведомления.** Omniva оповещает по SMS, электронной почте или в приложении. В приложении Omniva SMS-уведомления по умолчанию отключены; их можно включить в разделе Profile → Notification preferences.

**В пакомате.** Введите шестизначный код дверцы в пакомате, указанном в сообщении. Получить посылку по коду может любой человек; удостоверение личности не требуется, если только отправитель не заказал проверку личности.

**Хранение.** Пакоматы хранят посылку 4 дня во всех трёх странах, а перед истечением срока Omniva отправляет напоминание. В э-обслуживании срок можно продлить за плату — до 4 дней за один раз, максимум на 12 дополнительных дней. Хранение в почтовом отделении Эстонии можно продлить один раз на срок до месяца. Невостребованная вовремя посылка возвращается отправителю за его счёт.

**Курьер.** Вы получите SMS с датой доставки, а курьер позвонит перед приездом (в Латвии и Литве — чтобы согласовать день и время). Курьеры доставляют до двери: до 30 кг в Эстонии и Латвии и до 25 кг в Литве. Если время не подходит, можно попросить курьера перенести доставку на другой день или направить посылку в ближайший пакомат.

**Пропущенная курьерская доставка.** Посылки размером до L переносятся в ближайший пакомат на 4 дня. В Эстонии посылка до размера XL может ждать 7 дней в почтовом отделении, а XXL — 7 дней в центре доставки. В Латвии крупные отправления ждут 4 дня в центре доставки; в Литве они возвращаются отправителю.

**Переадресация.** Бесплатна до поступления посылки в сортировочный центр — по ссылке из письма Omniva или через э-обслуживание; позже или при смене на курьерскую доставку взимается плата. Посылку также могут перенаправить, если выбранный пакомат оказался переполнен.

## Посылка Omniva задерживается, повреждена или на таможне

**Не доставлена или нет обновлений.** Сначала проверьте, истёк ли расчётный срок доставки Omniva (см. вопросы ниже). Если срок прошёл, Omniva просит получателя обратиться к отправителю, который подаёт форму запроса статуса доставки («Request parcel delivery status»). См. [что делать, если отслеживание не обновляется](guide:tracking-not-updating) (на английском).

**Пустая ячейка или чужое место выдачи.** Свяжитесь с поддержкой частных клиентов Omniva в вашей стране.

**Повреждение.** В Эстонии получатель может подать форму о повреждении или утере в течение 6 месяцев со дня вручения, хотя компенсацию обычно выплачивают отправителю. В Латвии и Литве справочные страницы направляют получателя к отправителю (в Литве — с фотографиями посылки и упаковки), чтобы претензию подал отправитель, но латвийские условия Omniva также позволяют получателю подать письменную жалобу в течение 6 месяцев после вручения.

**Таможня (Эстония).** С 1 июля 2021 года любая посылка из-за пределов ЕС подлежит декларированию независимо от стоимости в течение 30 дней. Вы можете уполномочить Omniva либо подать декларацию самостоятельно в Налогово-таможенный департамент. См. [посылки на таможне](guide:customs) (на английском).

> Мошеннические сообщения от имени Omniva требуют оплатить небольшой сбор за доставку или таможню по поддельной ссылке. Omniva никогда не шлёт SMS с иностранных номеров и пишет только с доменов omniva.ee, omniva.lv, omniva.lt или omniva.eu. Проверяйте статус на minu.omniva.ee.

## Контакты и служба поддержки Omniva

На страницах контактов для частных клиентов Omniva указаны следующие данные (по местному времени каждой страны):

| Страна | Телефон | Эл. почта | Часы работы |
| --- | --- | --- | --- |
| Эстония | +372 661 6616 | info@omniva.ee | Пн–Пт 8:00–19:00, Сб 9:00–15:00 |
| Латвия | +371 27042424 | info@omniva.lv | Пн–Пт 8:00–18:00, Сб 9:00–15:00 |
| Литва | +370 700 55077 | info@omniva.lt | Пн–Пт 8:00–18:00, Сб 9:00–15:00 |

На каждом сайте также есть формы запроса статуса доставки, сообщения о повреждении или утере и переадресации. В Литве стоимость звонка зависит от тарифа Telia. Онлайн-чата у Omniva нет.

## Отслеживание Omniva в Peek

[Peek](/) сразу определяет коды `JJEE` обоих форматов выше как Omniva. Ссылки с minu.omniva.ee, mana.omniva.lv и mano.omniva.lt (`/track/` с кодом), а также ссылки отслеживания omniva.ee или omniva.lt с параметром `barcode=` работают для любого кода. Почтовый индекс не требуется.

Почтовый код с окончанием `EE`, начинающийся с `C`, `R`, `E` или `L` (кроме `CR`, `CV` и `RO`) и имеющий верную контрольную цифру, принадлежит Omniva по формату — Peek называет Omniva сразу. Для кодов с другими первыми буквами Peek одновременно опрашивает Omniva и Chronopost и привязывает посылку к той службе, которая её знает. Если ни одна пока не нашла отправление, вы увидите «Omniva, Chronopost: пока не найдена. Peek продолжит проверять после добавления.», и Peek продолжит проверки, постепенно увеличивая интервал. Если код определён как Omniva, но ещё не появился в её базе, показывается «Ждём Omniva»; если спустя два дня Omniva по-прежнему не знает номер и другие сервисы не ответили, Peek предложит проверить номер, сменить службу или удалить посылку, а сам перейдёт на ежедневные проверки (и еженедельные спустя неделю после добавления). Номер с неверной контрольной цифрой не распознаётся, и Omniva отклоняет его даже при ручном выборе — проверьте опечатки. Иностранный почтовый номер можно отслеживать в Omniva через «Изменить» или ссылку Omniva. Когда почта отправления указывает назначением Эстонию (либо страна не указана, а вы в Эстонии), Peek дополнительно опрашивает Omniva и переводит посылку туда, как только у Omniva появляется скан не старше последнего скана почты отправления; для Латвии или Литвы аналогично опрашиваются Latvijas Pasts или Lietuvos paštas. Отправление с кодом `EE`, отправленное Omniva за границу, так же отслеживается дальше у почты страны назначения.

Peek показывает англоязычные статусы Omniva, точку каждого скана на карте, страну назначения, время доставки и пакомат или почтовое отделение выдачи. Адреса, индексы и причины неудачной доставки не выводятся, как и ожидаемая дата или вес. Пока посылка открыта в Peek или в день доставки при включённых уведомлениях, Peek опрашивает Omniva до раза в 10 минут, а с 6:00 по эстонскому времени в день передачи курьеру — каждые 2 минуты, пока отвечает трекер Omniva; в остальное время — раз в час. После двух дней без новостей (12 часов в статусе «Этикетка создана» или «Принята службой доставки») проверка идёт раз в час, а спустя семь дней без новостей в статусе «Этикетка создана» — каждые 6 часов. Через четыре дня без новых строк в статусах «Этикетка создана», «Принята службой доставки» или «В пути» Peek отмечает дату последнего скана («Нет обновлений с …»), показывает пояснение «В долгих перелётах, на таможне или в выходные у посылок часто нет новых отметок. Peek продолжает спрашивать Omniva.» и ссылку «Поддержка Omniva»; спустя три недели текст меняется на «Уже больше трёх недель ничего нового. Если посылка так и не пришла, спросите продавца. Peek продолжает спрашивать Omniva.» Доступны режимы уведомлений «Каждая отметка», «Важные этапы» или «Только доставка». У старых посылок может сохраняться только запись о вручении.

## Вопросы об отслеживании Omniva

### Как отследить посылку Omniva (Почта Эстонии)?

Введите код отслеживания (например, `JJEE…` или 13-символьный почтовый код с окончанием `EE`) на minu.omniva.ee, в приложении Omniva или в поле выше. Omniva ищет по коду отправления, а не по номеру телефона, но в личном кабинете на minu.omniva.ee входящие посылки отображаются списком.

### Сколько идёт доставка Omniva в Эстонии, Латвии и Литве?

Omniva указывает 1–2 рабочих дня внутри одной страны Балтии и 2–3 рабочих дня между ними с момента передачи. Курьеры в Эстонии и Литве доставляют на следующий рабочий день и не позднее 3 дней после передачи (в Латвии — в течение 3 дней).

### Сколько посылка хранится в пакомате Omniva и можно ли продлить срок?

Пакоматы Omniva хранят посылки 4 дня в Эстонии, Латвии и Литве. В э-обслуживании можно платно продлевать хранение до 4 дней за раз (в сумме максимум на 12 дополнительных дней), прежде чем посылка вернётся отправителю.

### Что означает «Delivery attempt made» в трекинге Omniva?

Это означает, что курьер пытался вручить посылку, но не смог — посылка не доставлена. Отправления размером до L после этого помещаются в ближайший пакомат на 4 дня.

### Как связаться со службой поддержки Omniva?

Позвоните по номеру `+372 661 6616` в Эстонии (`info@omniva.ee`), `+371 27042424` в Латвии (`info@omniva.lv`) или `+370 700 55077` в Литве (`info@omniva.lt`). Линии работают с понедельника по пятницу (8:00–19:00 в Эстонии, 8:00–18:00 в Латвии и Литве) и в субботу 9:00–15:00.

:::sources
- [Omniva Estonia: Track and receive parcels](https://www.omniva.ee/en/track-and-receive-parcels/) – shipment code, door code, receiving options, courier SMS and call
- [Omniva Latvia: Track and receive](https://www.omniva.lv/en/track-and-receive-parcels/) – receiving in Latvia, courier, 30 kg
- [Omniva Lithuania: Track and receive](https://www.omniva.lt/en/track-and-receive-parcels/) – receiving in Lithuania, courier, 25 kg
- [Omniva: How to track your parcel?](https://www.omniva.ee/help/how-to-track-your-parcel-ee) – tracking code by SMS or email, summary and details, parcels from abroad
- [Omniva: Parcel delivery timeframes](https://www.omniva.ee/help/omniva-parcel-delivery-timeframes-ee) – delivery times, courier next day
- [Omniva Latvia: Omniva sūtījumu piegādes termiņi](https://www.omniva.lv/palidziba/omniva-sutijumu-piegades-termini) – courier within 3 days (Latvian)
- [Omniva Lithuania: OMNIVA siuntų pristatymo terminai](https://www.omniva.lt/pagalba/omniva-siuntu-pristatymo-terminai-lt) – courier next working day (Lithuanian)
- [Omniva: How long items are stored by Omniva?](https://www.omniva.ee/help/how-long-items-are-stored-by-omniva-ee) – storage in each country, returns
- [Omniva Latvia: Cik ilgi Omniva uzglabā sūtījumus?](https://www.omniva.lv/palidziba/cik-ilgi-omniva-uzglaba-sutijumus) – reminder in Latvia (Latvian)
- [Omniva: Extend parcel storage time](https://www.omniva.ee/help/extend-parcel-storage-time-ee) – up to 4 days at a time, 12 extra days at most
- [Omniva: Will I get notified about a delivery?](https://www.omniva.ee/help/will-i-get-notified-about-a-delivery-ee) – notices, courier calls
- [Omniva: Issue with the delivery SMS or email notification](https://www.omniva.ee/help/i-have-an-issue-with-the-delivery-smsemail-notification) – app notification settings
- [Omniva: When courier parcels are delivered?](https://www.omniva.ee/help/when-courier-parcels-are-delivered-ee) – courier hours
- [Omniva: Will the courier deliver parcels to my door?](https://www.omniva.ee/help/will-the-courier-deliver-parcels-to-my-door-ee) – weight limits
- [Omniva: Not at the address when the courier delivers](https://www.omniva.ee/help/what-happens-if-im-not-at-the-delivery-address-when-the-courier-delivers-the-parcel-ee) – missed courier options
- [Omniva: Can someone else pick up my parcel?](https://www.omniva.ee/help/can-someone-else-pick-up-my-parcel-ee) – collection by another person
- [Omniva: Do I need to show ID?](https://www.omniva.ee/help/do-i-need-to-show-id-when-receiving-items-via-omniva-ee) – ID rules
- [Omniva: Redirect a parcel or letter](https://www.omniva.ee/help/redirect-a-parcel-or-letter-ee) – redirection rules and fees
- [Omniva: My parcel hasn't been delivered](https://www.omniva.ee/help/my-parcel-hasn%E2%80%99t-been-delivered-ee) – who acts when a parcel is late
- [Omniva: My parcel is damaged](https://www.omniva.ee/help/my-parcel-is-damaged-ee) – damage form, 6 months
- [Omniva Latvia: Sūtījumu pakalpojumu standarta noteikumi privātajiem klientiem](https://www.omniva.lv/wp-content/uploads/sites/6/2026/06/LV_Omniva-Standard-Terms-and-Conditions-of-Parcel-Services-for-Private-Customers_2026_07_22.pdf) – sender or recipient complains within 6 months (Latvian)
- [Omniva: The parcel machine locker is empty](https://www.omniva.ee/help/the-parcel-machine-locker-is-empty-or-a-shipment-is-missing) – contact support
- [Omniva: How to return a parcel?](https://www.omniva.ee/help/manage-shipments-general/return-a-parcel-containing-items-purchased-in-estonia-latvia-or-lithuania-ee) – example code CEXXXXXXXXEE, return code
- [Omniva Estonia: Customs: receiving](https://www.omniva.ee/en/customs-receiving/) – declaring within 30 days
- [Omniva: Which shipments require a customs declaration](https://www.omniva.ee/help/which-shipments-require-a-customs-declaration) – all non-EU parcels since 1 July 2021
- [Omniva Estonia: Contacts for private customers](https://www.omniva.ee/en/contacts-for-private-customers/) – phone, email, hours, forms, Eesti Post
- [Omniva Latvia: Contacts for private customers](https://www.omniva.lv/en/contacts-for-private-customers/) – phone, email, hours
- [Omniva Lithuania: Contacts for private customers](https://www.omniva.lt/en/contacts-for-private-customers/) – phone, email, hours, Telia tariff
- [Omniva: How to distinguish scams from genuine parcel notifications](https://www.omniva.ee/en/frauds/) – scam signs, phone numbers, email domains
- [Omniva Estonia: Home](https://www.omniva.ee/en/) – scam warning, e-service
- [Omniva: Tracker interface strings](https://minu.omniva.ee/locales/common/common_en.json) – tracker languages
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – what Peek reads
:::
