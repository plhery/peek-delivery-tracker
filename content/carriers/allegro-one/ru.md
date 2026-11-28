---
title: Отслеживание Allegro One: One Box, One Punkt и Kurier
description: Отследите посылку Allegro One (One Box, One Punkt, One Kurier): номера A0 и AD0, значения статусов, срок хранения 2 дня, задержки и рекламации.
slug: allegro-one-otslezhivanie
published: 2026-11-27
updated: 2026-11-27
---

Allegro One — собственная сеть доставки Allegro в Польше: постаматы Allegro One Box, пункты выдачи Allegro One Punkt и курьерская доставка до двери Allegro One Kurier. Она работает в рамках программы Allegro Delivery вместе с DPD, DHL и ORLEN Paczka. Отследить посылку с номером `A0`, `AD0` или `AD` можно в разделе «Moje przesyłki» на allegro.pl, в приложении Allegro или вставив номер в поле выше; ниже приведены значения статусов, сроки хранения 2 дня и правила подачи рекламаций.

## Где найти трек-номер Allegro One

Allegro показывает службу доставки, номер посылки и её статус в трёх местах:

- **«Moje przesyłki»** на allegro.pl или в приложении — с момента, когда посылка ждёт отправки, до вручения.
- **«Moje zakupy»** (Мои покупки) — в карточке конкретного заказа.
- **В письме на электронную почту**, которое приходит после того, как продавец добавит номер.

Peek распознаёт следующие форматы номеров для Allegro One (символы вымышленные):

| Формат | Что это за номер |
| --- | --- |
| `A0K4829105` | Allegro One: `A0` и 8 букв или цифр, всего 10 символов. |
| `AD0K4829105736491` | Allegro Delivery: `AD0` и 14 цифр или согласных букв, всего 17 символов; также отображается как Allegro One. |
| `ADK4829105` | Прежний формат Allegro Delivery, всё ещё встречающийся на части возвратов: `AD` и 8 букв или цифр. |
| `ALE482910573649` | Старый формат Allegro One: `ALE` и 12 цифр. |

Номера ещё нет? Продавец добавляет его к заказу вручную, если только Allegro не получает номер автоматически от интегрированной службы доставки. Подробнее см. [как найти трек-номер](guide:find-tracking-number) (на английском).

В программу Allegro Delivery входят также ORLEN Paczka, DHL и DPD. Их отправления по Allegro Delivery получают собственный номер Allegro и отслеживаются в системе Allegro — отметки партнёра переводятся в те же формулировки статусов. Если продавец отправил посылку вне Allegro Delivery по своему прямому договору со службой доставки, ей присваивается номер этой службы, и отслеживать её нужно у неё, например в ORLEN Paczka или DPD Poland.

## Значения статусов отслеживания Allegro One

Сервис отслеживания Allegro отдаёт формулировки этапов на польском или английском языке. Если вы пользуетесь Peek не на польском, показываются английские формулировки в том порядке, в котором их обычно проходит посылка:

| Статус Allegro One | Что это означает |
| --- | --- |
| `Parcel has been prepared by the sender` | Этикетка создана. Посылка ещё не передана в Allegro One. |
| `Parcel has been accepted at the sorting center` | Принята в сортировочном центре. |
| `Parcel has been dispatched to its destination point` | Отправлена к целевому постамату, пункту выдачи или отделению. |
| `Parcel has been released for delivery` | Выдана на доставку — в том числе когда направляется в постамат One Box. |
| `No space in the parcel locker` | В постамате нет свободных ячеек: неудачная попытка, курьер увёз посылку обратно. |
| `Parcel is awaiting pick-up` | Готова к выдаче в One Box или One Punkt. |
| `Parcel is awaiting pick-up — pick-up deadline is about to expire` | Напоминание: срок хранения скоро истечёт. |
| `Time for parcel pick-up has expired` | Посылку не забрали вовремя — она возвращается продавцу. |
| `Parcel has been returned to the sender` | Едет обратно к отправителю. Несмотря на формулировку, продавец её ещё не получил. |
| `Parcel has been delivered` | Доставлена курьером или получена вами в постамате / пункте (см. ниже). |

Статус `Parcel has been delivered` появляется и после того, как вы сами забрали посылку из постамата One Box — отдельной отметки о получении из ячейки нет.

Некоторые сообщения выглядят тревожнее, чем есть на самом деле. `Parcel may be delivered with delay` и `Parcel delivery time has been arranged with the recipient` — это уведомления, посылка при этом продолжает движение. `Parcel has been redirected to a different point` появляется после того, как вы сами выбрали другой пункт выдачи. Даже после `No parcel` или `Parcel has been lost` могут появиться новые сканы, когда отправление снова найдут.

## Доставка One Box, One Punkt и One Kurier

**Уведомление о прибытии.** Когда посылка попадает в постамат или пункт выдачи, Allegro либо партнёр сообщает по электронной почте, по SMS или уведомлением в приложении Allegro, где и до какого срока её нужно забрать.

**В постамате One Box** введите код получения (сам по себе или вместе с номером телефона), отсканируйте QR-код из SMS или письма либо откройте ячейку прямо из приложения Allegro. **В пункте One Punkt** назовите номер посылки и код получения.

**Срок хранения.** Allegro даёт 2 календарных дня (48 часов) в постамате One Box и 2 рабочих дня в пункте One Punkt. В отдельных постаматах и пунктах Allegro может позволить продлить хранение на отдельных условиях — тогда в отслеживании появляется статус `Time for pick-up has been extended`. Если не забрать посылку вовремя, она уходит обратно к продавцу.

**Постамат переполнен или не работает?** Allegro может поместить посылку в другой постамат в радиусе 3 км (до 6 км в сельской местности) и уведомит вас, где и до какого времени её забрать.

**One Kurier** доставляет посылки по указанному адресу в отдельных городах. Allegro может позволить один раз перенаправить курьерскую посылку (если позволяют её габариты) в постамат или пункт выдачи, принимающий переадресации в зоне обслуживания того же отделения. Запрос оформляется в приложении Allegro или на allegro.pl с момента отправки до передачи в доставку; переадресация добавляет один рабочий день, а отправления с наложенным платежом перенаправить нельзя. Если никого не оказалось дома, курьер оставляет извещение (`Notice`): нужно забрать посылку в течение 48 часов по указанному адресу или заказать платную повторную доставку.

## Посылка Allegro One задерживается, потеряна или повреждена

**Долго ждёте.** Проверьте раздел «Moje przesyłki». Если посылки там нет совсем, запросите трек-номер у продавца. См. также [что делать, если отслеживание не обновляется](guide:tracking-not-updating) (на английском).

**Продавец не помогает.** Сообщите о проблеме с покупкой («problem z zakupem») — это можно сделать уже через час после оплаты. У продавца есть 24 часа на ответ на ваше первое сообщение без учёта суббот, воскресений и праздничных дней; чтобы подключить Allegro, нажмите кнопку «Pomoc Allegro». На решение проблемы при задержке, утере посылки или отсутствии товара в посылке продавцу даётся 7 дней. Если после этого Allegro не видит, что спор решён, система спросит вас, и при подтверждении предложит подать заявку по программе защиты покупателей Allegro Ochrona Kupujących. На Allegro Lokalnie и у продавцов, пользующихся складом Magazyn Allegro, по-прежнему открывается «Dyskusja» (Дискуссия).

**Статус «доставлена», но посылки нет.** Уточните, не получил ли её кто-то из домашних, соседей или на ресепшене в офисе. Дополнительные шаги — в руководстве [статус «доставлена», но посылка не получена](guide:delivered-not-received) (на английском).

**Утеря, повреждение или задержка: рекламации.** Как оператор Allegro Delivery, рекламации по доставке принимает само Allegro: в электронном виде после входа в аккаунт Allegro или с подтверждением данных посылки, либо иным способом, предусмотренным почтовым законом. Подать рекламацию можете вы, если посылка была вручена вам, либо если она не дошла, а продавец передал вам право требования. Скрытую недостачу или повреждение нужно заявить в течение 7 дней с момента получения посылки; в остальных случаях срок составляет до 12 месяцев с даты отправки. Allegro отвечает не позднее чем через 30 дней.

## Контакты и служба поддержки Allegro One

Поддержку по отправлениям Allegro Delivery, включая Allegro One, ведёт Allegro:

- **Рекламации:** подаются в Allegro, как описано выше.
- **Ячейка One Box не открывается:** звоните на техническую горячую линию, номер которой указан прямо на постамате.
- **Вопросы к продавцу:** при задержке откройте обращение о проблеме с покупкой («problem z zakupem»), как описано выше.

## Отслеживание Allegro One в Peek

[Peek](/) сразу определяет Allegro One по номерам, начинающимся с `A0` или `AD` с 8 буквами или цифрами, а также по 17-символьным номерам `AD0`. Для старого формата `ALE` и 12 цифр выберите Allegro One вручную через «Изменить».

Peek читает данные отслеживания Allegro на английском языке (или на польском, если интерфейс Peek у вас на польском) и показывает исходные формулировки вместе с историей этапов и временем доставки. Allegro не передаёт географические названия, поэтому на карте точки не отмечаются, а также не отображаются название пункта выдачи, ориентировочная дата доставки, вес и отправитель.

Пока карточка посылки открыта у вас, или в день доставки при включённых уведомлениях, Peek проверяет обновления до одного раза в 10 минут, а с 6:00 по местному времени в день, когда Allegro One отмечает передачу посылки в доставку, — каждые 2 минуты, пока отвечает собственный трекер Allegro One (через агрегаторы отслеживания — до одного раза в 15 минут в этот день); в остальное время — раз в час. Вы можете выбрать уведомления: «Каждая отметка», «Важные этапы» или «Только доставка». После двух дней без обновлений (или 12 часов, пока посылка остаётся в статусе «Этикетка создана» либо «Принята службой доставки») Peek переходит на ежечасные проверки.

## Вопросы об отслеживании Allegro One

### Что такое Allegro One Box, One Punkt и One Kurier?

Это собственная сеть доставки Allegro в программе Allegro Delivery: постаматы One Box, пункты выдачи One Punkt и курьерская доставка до двери One Kurier в отдельных городах Польши. В рамках Allegro Delivery посылки также доставляют партнёры DPD, DHL и ORLEN Paczka.

### Сколько посылка хранится в Allegro One Box и One Punkt?

2 календарных дня (48 часов) в постамате One Box и 2 рабочих дня в пункте выдачи One Punkt. В отдельных постаматах и пунктах Allegro может позволить продлить срок хранения; если не забрать посылку вовремя, она возвращается продавцу.

### Как открыть постамат Allegro One Box, чтобы забрать посылку?

Введите код получения из письма, SMS или приложения Allegro (один или вместе с номером телефона), отсканируйте QR-код из SMS или письма либо откройте ячейку прямо из приложения Allegro.

### Сколько стоит доставка Allegro One?

Стоимость доставки указывает продавец в прайс-листе объявления. По действующим условиям подписки Allegro Smart! доставка в постаматы, пункты выдачи или курьером бесплатна при покупке у одного продавца на сумму от 49,90 злотых.

### Как подать жалобу при задержке, утере или повреждении посылки Allegro One?

Сообщите продавцу о проблеме с покупкой («problem z zakupem») начиная с 1 часа после оплаты (у продавца есть 24 часа на первый ответ без учёта выходных и праздников и 7 дней на решение проблем с доставкой). Если посылка была вручена вам или продавец передал вам право требования, можно также подать рекламацию по доставке в Allegro в течение 12 месяцев с отправки (в течение 7 дней с момента получения при скрытой недостаче или повреждении); Allegro отвечает в срок до 30 дней.

:::sources
- [Allegro: The Allegro Delivery program, information for sellers](https://help.allegro.com/en/sell/a/the-allegro-delivery-program-information-for-sellers-MR7exl1wYS4) – One Box, One Punkt, One Kurier in selected cities, partners, waiting times, locker helpline, customer service, price lists
- [Allegro: How to correctly dispatch parcels with orders](https://help.allegro.com/en/sell/a/how-to-correctly-dispatch-parcels-with-orders-a-guide-for-sellers-GRv326wwXF1) – where buyers see the parcel, numbers received automatically, tracking at the carrier
- [Allegro: Regulamin Allegro](https://policies.allegro.com/doc/f86a1a793dc4f5bc4b695b0ff0d54d974badab4169f5aaaddad89585c2c90690) – email with the carrier, the number and a tracking link
- [Allegro: Regulamin Allegro Delivery](https://policies.allegro.com/doc/e5cf7a904b3b08d908db62830a8bdac17b22cf354baf748e7853adff00fea84c) – pickup notice, code, QR and app pickup, substitute locker, extension, redirection, notice, return to the seller, complaints
- [Allegro: How to solve problems with purchases in the Messages tab in the Sales Center or in the Message Center](https://help.allegro.com/en/sell/a/how-to-solve-problems-with-purchases-in-the-messages-tab-in-the-sales-center-or-in-the-message-center-Bv0zrVqDVIX) – purchase problems instead of Discussions since 28 October 2026, from one hour after the purchase, 24 hours to answer the first message excluding weekends and holidays, 7 days for a delay, loss or no product in the parcel, buyer protection, Discussions still on Allegro Lokalnie and with Allegro Warehouse sellers
- [Allegro: Jak rozwiązywać problemy z zakupem w zakładce Wiadomości w Sales Center lub w Centrum Wiadomości](https://help.allegro.com/pl/sell/a/jak-rozwiazywac-problemy-z-zakupem-w-zakladce-wiadomosci-w-sales-center-lub-w-centrum-wiadomosci-x5ZVrvzXPuP) – an hour after paying, the "Pomoc Allegro" button: Allegro joins only when the buyer asks
- [Allegro: We changed the Allegro Smart! terms and conditions for buyers](https://help.allegro.com/en/sell/a/we-changed-the-allegro-smart-terms-conditions-for-buyers-wwkzRYxRdio) – free delivery from 49.90 zł from one seller
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – number shapes, status wordings and languages, partners' parcels under Allegro's number, what Peek reads
:::
