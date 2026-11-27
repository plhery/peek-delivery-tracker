---
title: Отслеживание JRT: трек-номер JRT…MM, статусы и доставка
description: Отслеживание посылки JRT (今日通, Jinritong) из Сямыня (Китай): формат JRT…MM, китайские и английские авиастатусы, передача DHL/UPS/FedEx и контакты.
slug: jrt-otslezhivanie
published: 2026-11-27
updated: 2026-11-27
---

JRT (今日通, Jinritong, 厦门今日通商贸有限公司) — трансграничный экспедитор из Сямыня (Китай), трек-номера которого состоят из `JRT`, десяти цифр и `MM`. Вставьте номер `JRT…MM` в поле выше, чтобы отследить посылку из Китая через вылет, импортную таможню и передачу местному перевозчику: трекер JRT транслирует сканирования DHL в Европе (CTT Express в Испании, InPost в Польше и FedEx на линии в Ирландию), UPS в США и J&T Express в Мексике.

## Трек-номер JRT: как он выглядит

Собственные накладные JRT состоят из `JRT`, десяти цифр и `MM`, всего 15 знаков; `JRT` — инициалы 今日通. Примеры с выдуманными цифрами:

| Номер | Что это |
| --- | --- |
| `JRT4829105736MM` | `JRT`, десять цифр и `MM`: стандартная накладная JRT. |
| `JRT482910573MM` | Только девять цифр: не формат JRT. Проверьте, не пропущена ли цифра. |
| `JRTO4829105736MM` | Буква `O` после `JRT`: Peek читает его как `JRT4829105736MM` — единственную форму, которую знает трекер JRT. |
| `UP4829105736182940` | `UP` и шестнадцать цифр: референс отправителя, по которому может находиться заказ JRT, но нет подтверждения, что его используют только клиенты JRT. |
| `RR482910576CN` | Две буквы, девять цифр и `CN`: номер [Почты Китая](carrier:china-post), а не JRT. |

Пробелы и строчные буквы в Peek значения не имеют. Трекер JRT также находит заказ по собственному референсу продавца, если ввести его точно так, как указал продавец, вместе с дефисами.

**Ссылка с цифрами вместо домена.** Трекер JRT работает на голом адресе сервера по открытому HTTP без доменного имени, и другого сайта JRT Peek не знает. Ссылка на него содержит числовой адрес вместо имени сайта, `trackIndex.htm`, `documentCode=` и номер или референс: её можно вставить в Peek целиком. Сам Peek ссылку на трекер не публикует, так как открытый HTTP передавал бы ваш номер без шифрования.

## Статусы JRT: что значат отметки

Многие строки JRT записаны на китайском, а следом идёт собственный перевод JRT на английский — Peek показывает их полностью. В порядке прохождения посылки:

| JRT пишет | Что это значит для вас | Статус в Peek |
| --- | --- | --- |
| `货物电子信息已经收到` | «Получены электронные данные о грузе»: продавец создал заказ. Записано платформой Hualei (华磊), а не сканированием. | «Этикетка создана» |
| `Shipment information received` | Данные об отправке поступили в JRT; посылку ещё никто не сканировал. | «Этикетка создана» |
| `到达收货点` | Прибыла в пункт приёма JRT: первое физическое сканирование в руках JRT. | «Принята службой доставки» |
| `货物离开操作中心` | Посылка покинула операционный центр JRT. | «В пути» |
| `深圳集散中心 已收货` | Хаб JRT в Шэньчжэне принял посылку после операционного центра и перед рейсом. Peek переводит на английский как «Received at the Shenzhen distribution center». | «В пути» |
| `货物打包完成，已发往航司` | Упаковано и отправлено авиакомпании перед вылетом. Peek показывает «Goods packed and sent to the airline». | «В пути» |
| `抵达航司已起飞` | У авиакомпании, рейс вылетел из указанного аэропорта. Peek показывает «Arrived at the airline, flight departed». | «В пути» |
| `货物已交机场,等待航班安排中 Cargo delivered to airport, pending flight arrangement` | Передано в аэропорт в ожидании рейса. «Delivered» здесь означает доставку в аэропорт, а не вам. | «В пути» |
| `报关已放行等待…号航班起飞 Customs released,Awaiting departure on flight of the …` | Выпущено экспортной таможней в Китае, ожидает рейса на указанную дату. | «В пути» |
| `航班起飞，以实际抵达为准 Flight departure subject to actual arrival time` | Самолёт вылетел. | «В пути» |
| `航班落地，拆板中 Flight landing, dismantling board in progress` | Самолёт приземлился. Авиапалеты разбирают перед таможней. | «В пути» |
| `机场内等待提货，等待清关 Waiting for cargo pickup and customs clearance at the airport.` | В аэропорту назначения, ожидает вывоза и импортной таможни. | «На таможне» |
| `理货较慢，导致提货缓慢，已催促尽快处理 Slow cargo sorting has resulted in delayed pickup. We have followed up for prompt handling.` | Медленная сортировка в аэропорту задерживает вывоз, JRT запросила ускорение. Это задержка, а не утеря. | Статус не меняется: остаётся прежний |
| `已提货，清关中 The goods have been picked up and are currently undergoing customs clearance` | Идёт импортное таможенное оформление. | «На таможне» |
| `清关完成，卡车发运 Customs clearance completed; truck dispatched.` | Таможня пройдена, груз отправлен грузовиком. | «В пути» |
| `已交服务商 Delivered to service provider` | Передано компании, которая везёт последнюю милю. Это не доставка вам. | «В пути» |

Некоторые чисто китайские строки JRT Peek показывает по-английски, как у других экспедиторов: `货物电子信息已经收到` как «The electronic cargo information has been received», `到达收货点` как «Arrived at the receiving point», `货物离开操作中心` как «Goods leave the operation center» и `快件操作完成` как «Shipment departed from facility». Строку бронирования рейса `货物已交机场,预计…号的航班 Goods delivered to airport, scheduled on flight of the …` Peek выводит как «Goods delivered to airport», поскольку рейс ещё впереди. Ещё три китайские строки до вылета тоже относятся к «В пути»: `分拣包裹已出库` («Sorted parcel has left the warehouse»), `货物到达口岸，预计…号航班起飞` («Arrived at the port, booked on a flight») и `预计…号航班飞` («Booked on a flight»).

На европейском маршруте три более поздние строки также приходят только по-китайски и выводятся по-английски со статусом «В пути»: `航班已抵达` («Flight has arrived»), `清关完成，中转德国中` («Customs cleared, in transit to Germany») и `抵达德国，已交服务商` («Arrived in Germany, handed to the delivering carrier» — передано службе доставки вроде DHL, а не вручено вам).

**Автотранспортом через Казахстан.** На наземной линии JRT в Европу посылка едет по суше вместо авиаперелёта, с пятью китайскими строками, которые Peek показывает по-английски под статусом «В пути»: `已装柜发车，待抵达新疆` («Loaded into a container and on its way to Xinjiang»), `已抵达新疆，换装/报关，待出境` («Arrived in Xinjiang for transloading and export declaration, waiting to leave China»), `因哈萨克斯坦拥堵，外车入境新疆拥堵缓慢，在等待外车倒装后报关出境。` («Congestion in Kazakhstan is slowing foreign trucks into Xinjiang; the parcel will be declared for export once it is reloaded onto one» — очередь на границе, не утерянная посылка), `已出境，哈萨克斯坦阿拉木图` («Left China, Almaty, Kazakhstan») и `哈俄边境，待办理手续进入俄罗斯` («At the Kazakhstan–Russia border, awaiting the formalities to enter Russia» — транзитная граница по пути, а не таможня страны назначения).

**Строки только на английском.** Другие отметки JRT приходят только по-английски, все со статусом «В пути», если не указано иное:

- `Shipment arrived at facility`, `Sending to the airport`, `The goods have been packed and sent to the airline`, `Package has been packed and delivered to airport`, `Cargo handed over to the airline`: на пути к рейсу. `Domestic customs clearance completed` — китайская экспортная таможня, а не ваша.
- `Arriving at the airport - arranging flights`, `Arriving at the airline, in the process of booking a flight`: бронирование рейса.
- `Flight is expected to take off on …`, `The flight is expected to depart on … and land on …`, `Estimated departure is …`, `Estimated arrival date is …`: плановые даты рейса в формате месяц.день (`11.12` — 12 ноября).
- `Flight has flown`, `The flight has departed and is expected to … land`, `The flight has taken off`: в полёте.
- `Sorry, the second flight has been delayed and is expected to … land`: стыковочный рейс задержан, указана новая дата прилёта.
- `During transportation to …`: перевозка в указанную страну назначения.
- `Flight has arrived`, `The flight has arrived and is awaiting dismantling`, `Arrived at Destinated Airport`, `The NOA has been received` (уведомление авиакомпании о прибытии), `Dismantling and picking up goods in progress`: приземление до таможни.
- `Customs clearance of the cargo is in progress` и `Arrived at customs clearance warehouse`: «На таможне». Затем `Customs clearance completed` или `Customs clearance in destination country completed`.
- `In transit to final service provider`, `Delivery to final service provider`, `The goods have been handed over to the carrier`, `The cargo has been picked up and arrived at the warehouse，awaiting outbound shipment`, `It has been handed over to the service provider.` и `It has been transferred to the provider`: передача местному перевозчику, не доставка вам.
- `returned shipment has been received by processing center`: возвращённая посылка снова в центре JRT («Возвращена отправителю»).

**Строки через косую черту или запятую.** Часть отправлений объединяет китайский и английский через `/` или запятую, все со статусом «В пути»: `预计…航班/Flight scheduled for …`, `航班已启运/Package in transit to destination country`, `航班已落地，拆板中/Package arrived at destination country`, `货物已清关完成/Import customs clearance complete`, `国内清关完成` (экспортная таможня Китая), `预计配载航班…月…日,Expected flight on …` (дата рейса, а не доставки) и `航班已起飞,The flight has taken off from the origin`.

**Скопированные строки DHL, FedEx, UPS и J&T Express Mexico.** В Европу трекер копирует сканы DHL, часто строчными буквами: `the instruction data for this shipment have been provided by the sender to dhl electronically` («Этикетка создана»), `shipment in transit to dhl`, `A preferred location was designated as a reception option for this shipment.` (выбранное место доставки, статус не меняет), `The shipment has been posted by the sender at the retail outlet` (JRT сдала посылку в отделение Deutsche Post), `pick-up was successful.` (DHL забрала посылку после передачи), `the shipment has been processed in the parcel center of origin`, `loaded to movement / tour vehicle`, `The shipment has arrived in the destination country/destination area.`, `The shipment has left the import parcel center in the destination country/destination area.`, `The shipment is being prepared for delivery in the delivery depot` («В пути»); `The shipment has been loaded onto the delivery vehicle` («Передана курьеру»); `not delivered-consignee could not be reached`, `The shipment could not be delivered. Please find more information here.`, `The recipient was not present. Please find more information here.` и `not delivered-bad address` («Попытка вручения»); `held for consignee collection` и `The shipment is ready for collection from a ParcelShop/retail outlet. Please find more information here.` («Готова к выдаче»); `shipment will be returned to origin-not ordered` и `shipment will be returned to origin-bad address` («Возвращена отправителю»); и `the shipment has been successfully delivered` («Доставлена», либо «Возвращена отправителю» после строки возврата). На ирландской линии FedEx встречаются `Shipment information sent to FedEx` («Этикетка создана»), `Arrived at FedEx hub`, `Departed FedEx hub`, `On the way` и `Delivery option requested, hold request received - check back later for shipment status` («В пути»). В США сначала идут китайские строки рейса (`…号航班`, `航班已飞`, `航班抵达`, `提货中`, `清关完成，已提货，待交仓`, `已交仓`), затем китайские отметки UPS, которые Peek выводит английскими формулировками UPS (`Shipper created a label, UPS has not received the package yet.`, `Awaiting delivery to a UPS Access Point location.`, `Arrived at Facility`, `Departed from Facility`, `Processing at UPS Facility`, `Picked Up`, `Loaded on Delivery Vehicle`, `Out For Delivery Today`, `Delivered`). В Мексику копируются испанские строки J&T Express Mexico с городом и отделением в скобках 【】 вплоть до `…está en camino a tu domicilio.` («Передана курьеру»).

**Время.** Собственные строки JRT идут по пекинскому времени (`UTC+8`): зимой 17:00 в Пекине — это 10:00 в Берлине (`UTC+1`), 11:00 в Риге и Таллине (`UTC+2`) и 12:00 в Москве (`UTC+3`). Скопированные строки DHL Peek читает по местному времени указанного города (или по немецкому времени / времени страны назначения без города), строки UPS — по местному времени в США, а J&T Express Mexico — по времени Мехико.

## Кто доставляет посылки JRT в Европе, США и Мексике

**В Европе** большинство линий JRT заканчиваются доставкой [DHL](carrier:dhl), кроме линии FedEx в Ирландию, линии в Испанию (CTT Express) и линии в Польшу (InPost). На линиях DHL компания JRT сдаёт посылку в отделение Deutsche Post, и в записи JRT вместо её номера появляется номер DHL. Когда Peek узнаёт номер DHL, он показывает «Доставляет DHL» с номером (или «В DHL посылку ждут, но ещё не получили», пока у DHL только электронное уведомление) и переходит на собственный трекинг DHL, как только там появится скан. Для других стран или если локального номера нет, уточните у продавца название местной службы и её трек-номер: число попыток, пункты выдачи и сроки хранения зависят от неё.

**Таможенные сборы в ЕС.** На авиамаршрутах JRT посылка проходит импортное оформление в аэропорту прилёта (строки «На таможне» выше). В странах Европейского союза с 1 ноября 2026 года на онлайн-покупки из-за пределов ЕС действует сбор ЕС за обработку (Union handling fee) 2 € за категорию товара вдобавок к пошлине или НДС; подробнее — в руководстве о [посылках на таможне](guide:customs) (на английском).

**В США и Мексике.** В США линии JRT передаются UPS (`已交仓`), а в Мексике — J&T Express Mexico.

## Посылка JRT не приходит или отслеживание стоит на месте

**Только первые строки.** `货物电子信息已经收到`, `Shipment information received` или одна строка этикетки DHL/FedEx означают, что продавец оформил заказ, но посылку ещё никто не сканировал. Если через несколько дней ничего не меняется, спросите продавца, действительно ли её отправили.

**Ожидание рейса.** Строка рейса называет плановый день вылета, а `航班延误，预计…号飞出 Flight delayed, estimated departure on …` — новую дату. Если этот день прошёл без вылета, напишите продавцу.

**Нет обновлений после `已交服务商`, `Delivery to final service provider` или `已交仓`.** Свою часть пути JRT завершила. Если трекер не копирует сканы DHL, UPS, FedEx или J&T, а Peek не показывает номер местной службы, следующие этапы нужно смотреть у местного перевозчика: попросите у продавца его номер. Подробнее — в руководстве об [отслеживании посылок из Китая](guide:tracking-from-china) (на английском).

**Ничего не найдено.** На неизвестный номер трекер JRT выдаёт одну пустую строку с вашим номером в поле референса. Peek показывает «Ждём JRT», а через два дня — «Проверьте трек-номер». Сверьте номер с сообщением продавца символ за символом.

**Ошибка в адресе.** После `not delivered-bad address` проверьте адрес в заказе и сразу напишите продавцу. Если появится `shipment will be returned to origin-bad address`, посылка едет обратно: просите у продавца возврат или повторную отправку.

**Отмечена как доставленная, но посылки нет.** Отметка о доставке принадлежит местной службе, а не JRT: обратитесь сначала к ней, а затем к продавцу. Подробнее — в руководстве о [посылках, отмеченных как доставленные, но не полученных](guide:delivered-not-received) (на английском).

**Потерялась или вернулась.** Отправку через JRT оформлял продавец, поэтому возврат денег или повторную отправку запрашивайте у продавца или площадки.

## Контакты и поддержка JRT

Peek не нашёл контактных данных JRT: у компании нет другого известного сайта кроме трекера, а также телефона, почты или адреса для получателей. По вопросам заказа обращайтесь к продавцу, а если он не отвечает — в поддержку торговой площадки.

## Отслеживание посылки JRT в Peek

[Peek](/) сразу распознаёт номер `JRT…MM` по его уникальной форме (`JRT`, десять цифр и `MM`) и по ссылке на трекер JRT. Для 16-значного референса `UP` Peek спрашивает трекер JRT и выбирает JRT при совпадении. Любой другой референс тоже можно вставить — Peek отслеживает более 4500 служб доставки — и выбрать JRT вручную через «Изменить» (поиск по 今日通 или Jinritong тоже работает) или «Сменить службу доставки» на уже добавленной посылке. В этом случае Peek показывает номер JRT с подписью «JRT отслеживает посылку под номером» до момента передачи; но Peek убирает дефисы, поэтому для референса с дефисами попросите у продавца номер `JRT…MM`.

Peek показывает статус, каждую строку с местом и страну назначения, ориентируясь на сами строки, а не на сводку записи. Время переводится в часовой пояс вашего устройства. Peek скрывает строки, записанные более чем за 12 часов до указанного в них времени (или более чем за 6 часов в ту же секунду с такой строкой), а также строки в будущем. История до доставки или возврата тогда начинается со строки «На других сайтах может быть больше этапов, но это лишь прогнозы.», а «Показать» раскрывает их серым цветом («Прогноз: …») без смены статуса и без уведомлений. Имена, адрес, почтовые индексы, вес, сборы, подпись и подтверждение вручения Peek скрывает, а ожидаемой даты доставки нет, потому что JRT её не даёт.

Когда в записи JRT вместо её номера появляется номер DHL, FedEx, Swiss Post, международный почтовый номер (две буквы, девять цифр, две буквы) или номер, однозначно подходящий другой службе доставки, Peek опрашивает и её и переходит на её отслеживание, как только там появится скан не старее последнего у JRT, сохраняя историю JRT. Любой другой локальный номер от продавца добавляйте отдельной посылкой.

Пока посылка открыта в Peek или в день доставки с включёнными уведомлениями, проверки идут до раза в 10 минут; в остальное время и при затишье (12 часов на первых строках или два дня без новостей) — раз в час. При статусе «Передана курьеру» Peek проверяет до раза в 2 минуты с 06:00 по местному времени в день доставки, пока JRT или местный перевозчик отвечает напрямую (каждые 5 минут, если требуется браузер; через сервисы отслеживания — раз в час, каждые 3 часа после тихого дня и до раза в 15 минут в день доставки). Уведомления настраиваются на «Каждая отметка», «Важные этапы» (таможня и возврат у JRT, а также скопированные отметки о курьере, неудачной попытке, пункте выдачи, доставке и возврате от DHL, UPS и J&T — без рейсов и `已交服务商`) или «Только доставка». Связаться с JRT или продавцом за вас Peek не может.

## Вопросы об отслеживании JRT

### Что такое JRT (今日通 / Jinritong) и как выглядит номер JRT…MM?

JRT — это 厦门今日通商贸有限公司 (Xiamen Jinritong), экспедитор из Сямыня (Китай). Его накладные состоят из 15 знаков (`JRT`, десять цифр и `MM`; девять цифр означают пропущенную цифру), а трекер работает на платформе Hualei (华磊).

### Почему JRT пишет «delivered», хотя посылка не пришла?

Из-за английских формулировок JRT: `Cargo delivered to airport` означает, что JRT сдала посылку в аэропорт на рейс, а `Delivered to service provider` или `Delivery to final service provider` — передачу местному перевозчику. Ни одна из них не означает доставку вам; Peek оставляет статус «В пути».

### Кто доставляет посылки JRT в Европе, США и Мексике?

В Европе большинство маршрутов JRT передаются DHL (CTT Express в Испании, InPost в Польше и FedEx на ирландской линии). В США доставляет UPS, в Мексике — J&T Express Mexico. Для других стран уточните у продавца название местной службы и её трек-номер.

### Начисляются ли таможенные сборы ЕС на посылки JRT из Китая?

На онлайн-покупки из-за пределов ЕС с 1 ноября 2026 года действует сбор ЕС за обработку (Union handling fee) 2 € за категорию товара вдобавок к пошлинам и НДС; подробнее о том, как перевозчики его взимают, — в руководстве о [посылках на таможне](guide:customs) (на английском).

### Как связаться с JRT, если посылка задержалась или пропала?

Обращайтесь к продавцу или площадке, где вы оформляли заказ: именно продавец заказывал доставку у JRT. Помимо открытого HTTP-трекера, Peek не нашёл у JRT ни сайта, ни телефона, ни почты для получателей.

:::sources
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – название компании и инициалы, платформа Hualei, трекер на голом адресе HTTP, ссылки без публикации, форматы `JRT…MM` и `UP`, буква `O` после `JRT` как `JRT`, поиск по номеру или референсу, тексты строк и статусы, английский для китайских строк, наземный маршрут через Синьцзян и Казахстан, скопированные сканы DHL, FedEx, UPS и J&T Express Mexico, передача в DHL, FedEx и Swiss Post, пекинское и местное время, ответ на неизвестный номер и что Peek показывает, откладывает и скрывает
- [Европейская комиссия: EU Customs Reform (на английском)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – сбор ЕС за обработку 2 € с 1 ноября 2026 года
:::
