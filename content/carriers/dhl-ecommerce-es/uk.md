---
title: Відстеження DHL eCommerce Iberia: статуси та ServicePoint
description: Відстежуйте посилку DHL eCommerce Iberia в Іспанії та Португалії: 10-значні й JJD номери, значення статусів, пункти ServicePoint, CTT Express і контакти.
slug: dhl-ecommerce-iberia-vidstezhennia
published: 2026-11-27
updated: 2026-11-27
---

DHL eCommerce Iberia (колишня DHL Parcel Iberia) — мережа доставки посилок DHL в Іспанії та Португалії, яка співпрацює з CTT Express для доставки приватним клієнтам в Іспанії та з CTT Expresso у Португалії в межах спільного підприємства від травня 2026 року. Вставте отриманий від магазину 10-значний номер, код із двох літер + 7 цифр + `0` або номер `JJD` у поле вище (і за потреби відкривайте трекер DHL прямо зі сторінки посилки). Нижче — значення англійських та іспанських статусів, видача в пунктах ServicePoint і дії у разі затримки або пошкодження.

## Трек-номер DHL eCommerce: де його знайти та як він виглядає

Немає номера? Запитайте в магазину або перегляньте [як знайти трек-номер](guide:find-tracking-number) (англійською).

Формати, відомі каталогу Peek (приклади вигадані):

| Формат | Приклад | Кому може належати |
| --- | --- | --- |
| 10 цифр | `4829173649` | DHL eCommerce Iberia, але також [DHL Express](carrier:dhl-express), Mondial Relay, Relais Colis та GLS Spain |
| Дві літери, 7 цифр, потім `0` | `KL48291730` | DHL eCommerce Iberia |
| `JJD000061`, `JJD000268`, `JJD000269` або `JJD000368`, потім 14 цифр | `JJD00006148291736482917` | DHL eCommerce Iberia |

Якщо в листі магазину для 10-значного номера вказано [Mondial Relay](carrier:mondial-relay) або [GLS Spain](carrier:gls-es), відстежуйте його там. Дві літери, дев’ять цифр і `ES` — це номер [Correos](carrier:correos-spain).

## Значення статусів відстеження DHL eCommerce

Peek запитує у DHL англомовні формулювання, хоча окремі скани все ж приходять іспанською, як-от `Llegada a`, `Tránsito en`, `Salida de` та `Entregado`. Основні статуси в порядку проходження:

| Що пише DHL | Що це означає для вас |
| --- | --- |
| `Shipment data received` | Відправник зареєстрував посилку. Самого відправлення у DHL ще немає. |
| `Picked up` | DHL забрала посилку у відправника; далі вказано місто. |
| `Shipment received at DHL Depot` | Посилка прибула до депо DHL. |
| `Arrived at` | Рухається між депо; далі вказано місто. Так само працюють `In transit in` та `Departed from`. |
| `Out for delivery` | Сьогодні в авто кур’єра на доставці. |
| `The delivery could not be made due to consignee absent` | Нікого не було вдома. Дивіться наступний розділ. |
| `Delivered to DHL ServicePoint, ready for collection by the recipient` | Готова до отримання в пункті ServicePoint. |
| `Parcel picked up by recipient` | Отримано в пункті ServicePoint. |
| `Delivered` | Доставлено за адресою. |
| `Shipment being returned to sender` | Триває повернення відправнику. |

Деякі формулювання потребують пояснення:

- `Delivered, POD pending`: позначено як доставлену, але підтвердження доставки ще не завантажено. Це може з’явитися завчасно: пізніше ще можлива інша спроба або невдала доставка.
- `Shipment with incidence If you are the sender, please contact DHL Parcel, if you are the recipient please contact the sender`: із посилкою виникла проблема. Як зазначено в самому статусі, одержувач має зв’язатися з відправником (магазином).
- `The expected delivery date might be postponed one working day` і `Not delivered today: delivery expected for the next working day` — короткі затримки, не втрата посилки.

## Пропущена доставка та пункти ServicePoint

**Нікого немає вдома.** Невдала спроба відображається як `The delivery could not be made due to consignee absent`, або `Receiver closed: delivery not possible`, якщо за адресою було зачинено. Наступні записи на кшталт `New delivery date requested` чи `Delivery agreed with recipient` означають, що нову дату доставки запрошено або узгоджено. Якщо натомість з’являється `Incorrect address details: delivery not possible`, кур’єр не зміг доставити через помилку в адресі: Peek показує "Needs attention", і вам або відправнику потрібно уточнити дані, перш ніж посилка рушить далі.

**Пункти ServicePoint.** Статуси `Parcel will be delivered to a DHL ServicePoint` та `Parcel is on it's way to our DHL ServicePoint` означають, що посилка прямує до пункту видачі DHL ServicePoint. Щойно вона готова там до видачі, Peek показує картку "Pickup point" із назвою та адресою магазину, маршрутом і кнопкою копіювання адреси. Години роботи та термін зберігання Peek не отримує — перевірте їх у DHL.

## Посилка затримується, загублена або пошкоджена

**Немає оновлень.** Перегляньте [чому відстеження припиняє оновлюватися](guide:tracking-not-updating) (англійською).

**Загублена або пошкоджена.** Почніть із магазину: власний статус DHL `Shipment with incidence` прямо радить одержувачам звертатися до відправника. В ЄС портал Your Europe зазначає, що магазин відповідає за пошкодження до моменту отримання вами товару. Якщо іншу дату не погоджено, продавець зобов’язаний доставити замовлення протягом 30 днів; якщо цього не сталося, нагадайте йому й дайте розумний додатковий час, після чого ви можете скасувати покупку та повернути гроші. Див. [загублені посилки](guide:lost-parcel) та [пошкоджені посилки](guide:damaged-parcel) (англійською).

**Митниця.** Для посилки з-за меж ЄС перегляньте [довідник із митниці](guide:customs) (англійською).

**CTT Express.** 12 травня 2026 року DHL eCommerce та CTT підписали угоду про спільне підприємство: у Португалії бізнес DHL eCommerce переходить до CTT Expresso, а в Іспанії CTT Express бере на себе доставку споживачам, тоді як DHL залишає доставку бізнесу. Частина посилок має номер CTT Express — їх відстежуйте через [CTT Express](carrier:ctt-express).

> Підозріле SMS чи лист про посилку DHL? Перегляньте [як розпізнати шахрайські повідомлення про доставку](guide:delivery-scam-texts) (англійською).

## Контакти DHL eCommerce

Шукайте контактні дані DHL eCommerce на сайті DHL для країни доставки — Іспанії або Португалії. Якщо посилка загублена чи пошкоджена, спершу звертайтеся до магазину.

## Відстеження посилок DHL eCommerce Iberia у Peek

[Peek](/) опитує DHL eCommerce Iberia та [TNT Italy](carrier:tnt-it), накладні яких мають той самий формат, для комбінації з двох літер, 7 цифр і `0`. Для 10 цифр він запитує DHL eCommerce Iberia, Packeta та BOX NOW, а також DHL Freight (із Португалії або без відомої країни — лише якщо попередні не знайшли номер), і спершу DHL Express, коли підходить його контрольна цифра (тоді також ACS Courier і Geniki Taxydromiki з тією ж перевіркою); якщо DHL Express теж знає номер, перемагає він. Якщо номер знають перевізники різних брендів або не знає жоден, виберіть службу через "Change carrier". Код `JJD` із таблиці вище одразу визначається як DHL eCommerce Iberia. Інші номери DHL (12 або 22 цифри, інші коди `JJD`, поштові номери) працюють лише після вибору "DHL eCommerce Iberia" вручну. Поштовий індекс не потрібен.

Можна вставляти й посилання зі сторінки відстеження DHL eCommerce Iberia іспанською, португальською або англійською (`/LiveTracking/ModificarEnvio/`, `ModificarMeuEnvio` чи `ModifyMyShipment`). Вони одразу визначають DHL eCommerce Iberia незалежно від формату номера. Поштовий індекс у кінці посилання відкидається, бо він не потрібен.

Скани DHL не містять часового поясу: Peek зчитує їх за часом місця сканування та виводить власну історію DHL. Коли у відповіді DHL є номер CTT Express, Peek також запитує [CTT Express](carrier:ctt-express) і переходить на нього, щойно CTT Express показує скан не старіший за найновіший від DHL. Статуси зберігають англійські формулювання DHL, окрім `Delivered` і `Out for delivery`, що виводяться словами Peek. Peek може сповіщати про зміну статусу. Очікуваної дати чи часу доставки для DHL eCommerce Iberia немає.

## Питання про відстеження DHL eCommerce Iberia

### Чи DHL eCommerce Iberia — це те саме, що DHL Express?

Ні. Накладні DHL Express мають власний трекер MyDHL+, і Peek вважає їх різними перевізниками. Оскільки 10-значні номери підходять обом, Peek спершу перевіряє DHL Express при валідній контрольній цифрі, і DHL Express має пріоритет, якщо знає відправлення.

### Чи доставляє CTT Express посилки DHL eCommerce в Іспанії та Португалії?

Для багатьох замовлень приватних покупців — так. За угодою про спільне підприємство від 12 травня 2026 року CTT Express виконує доставку споживачам в Іспанії, а бізнес DHL eCommerce у Португалії переходить до CTT Expresso. Якщо у трекінгу вказано номер CTT Express, стежте за ним через [CTT Express](carrier:ctt-express).

### Що означають «Delivered, POD pending» і «Shipment with incidence»?

`Delivered, POD pending` означає, що посилка позначена як доставлена до завантаження підтвердження доставки — це може з’явитися завчасно, і пізніше ще можлива нова доставка або невдала спроба. `Shipment with incidence` позначає проблему, і сам скан DHL радить одержувачам звернутися до відправника.

### Чому моя посилка DHL eCommerce Iberia не відстежується?

Уточніть і номер, і перевізника в магазині, оскільки 10-значні номери спільно використовують DHL Express, Mondial Relay, GLS Spain та інші. Старі відправлення також можуть зникати з трекера DHL.

### До кого звертатися, якщо посилка DHL eCommerce Iberia загублена чи пошкоджена?

Спершу зверніться до магазину: сам скан проблеми DHL спрямовує одержувачів до відправника, а за правилами ЄС на порталі Your Europe продавець відповідає за пошкодження до доставки та має доставити товар протягом 30 днів без іншої домовленості. Із питань місцевої доставки звертайтеся на сайт DHL eCommerce Іспанії або Португалії.

:::sources
- [CTT: CTT e DHL Ecommerce selam parceria estratégica](https://www.ctt.pt/grupo-ctt/media/noticias/ctt-e-dhl-ecommerce-selam-parceria-estrategica-para-criar-rede-de-distribuicao-de-encomendas-mais-completa-da-peninsula-iberica) – joint venture signed 12 May 2026, DHL eCommerce Portugal to CTT Expresso, CTT Express for consumers in Spain, DHL for business deliveries
- [Your Europe: Shipping and delivery](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_en.htm) – shop responsible for damage until delivery, 30 days, extra time, refund
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – what Peek reads, status wordings and their stages, ServicePoint shop, shipment links, former DHL Parcel Iberia name, CTT Express numbers
:::
