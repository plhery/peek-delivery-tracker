---
title: 4PX śledzenie przesyłki: numery, statusy i kontakt
description: Paczka z Chin wysłana przez 4PX Express: jak wygląda numer 4PX, co znaczą angielskie statusy, kto doręcza ją w Polsce i gdzie pytać, gdy śledzenie stoi.
slug: 4px-sledzenie-przesylki
published: 2026-10-11
updated: 2026-10-27
---

4PX Express (递四方速递, 4PX Express Co., Ltd.) to chińska firma logistyczna z Shenzhen: wysyła za granicę paczki sprzedawców internetowych i w kraju odbiorcy przekazuje je lokalnemu przewoźnikowi. Paczkę śledzisz na stronie 4PX, [track.4px.com](https://track.4px.com/), albo w polu nad tym tekstem. Numer dostajesz od sklepu lub platformy, w której kupujesz, w szczegółach wysyłki zamówienia.

## Numer przesyłki 4PX: jak wygląda

4PX nie publikuje przykładowego numeru. Jego strona przyjmuje od 5 do 50 liter i cyfr, do 100 numerów naraz, każdy w osobnej linii. Najczęstsze kształty, z wymyślonymi cyframi:

| Numer | Czyj to numer |
| --- | --- |
| `4PX1234567890123CN` | 4PX, 13 cyfr i CN: własny numer 4PX. |
| `LP1234567890123CN` | LP, 13 cyfr i CN: prawdopodobnie 4PX. |
| `LP12345678901234` | LP i 14 cyfr, bez CN: [Cainiao](carrier:cainiao), numer logistyczny AliExpress. |
| `RR000000005CN` | [Numer pocztowy](guide:tracking-number-formats) z kodem kraju na końcu, tu China Post. |
| `YT1234567890123456` | Nie 4PX, tylko [YunExpress](carrier:yunexpress). |

Na stronie 4PX wpisuj sam ciąg liter i cyfr, bez spacji. Na stronie wyniku 4PX może pokazać dwa numery: „4PX Order No.” i „Tracking No.”, czyli numer przewoźnika, który doręcza paczkę w Twoim kraju. Sklep może podać Ci właśnie ten drugi numer zamiast numeru 4PX; strona 4PX odpowiada też na część takich numerów.

## Statusy 4PX: co znaczą angielskie komunikaty

Strona 4PX nie ma wersji polskiej: przeglądarka ustawiona na polski dostaje interfejs po angielsku. Linie skanów są zwykle po angielsku, a przycisk „translate:” tłumaczy je tylko na angielski albo chiński uproszczony, albo pokazuje oryginał. Linie, które widać najczęściej, w kolejności, w jakiej spotyka je paczka:

| 4PX pisze | Co to znaczy dla Ciebie |
| --- | --- |
| `Parcel information received` | Sprzedawca utworzył przesyłkę. 4PX jeszcze nie ma paczki. |
| `4px received shipment.` | Paczka jest już w magazynie 4PX. |
| `Shipment arrived at facility and measured.` | Paczkę zmierzono w sortowni 4PX. |
| `Hand over to airline.` | Przekazana liniom lotniczym przed lotem za granicę. |
| `Arrival to the destination airport` | Wylądowała w kraju docelowym. |
| `Customs clearance agent received the shipment in destnation country.` | Odprawa celna w kraju docelowym (literówka pochodzi od 4PX). |
| `Released from customs: customs cleared.` | Odprawa zakończona, paczka jedzie dalej. |
| `In transit, it's progressing through post network` | Paczkę ma już lokalny przewoźnik. |
| `Out for delivery` | Paczka jest w doręczeniu. |
| `Delivery attempt unsuccessful` | Próba doręczenia się nie udała. |
| `Delivered, in/at mailbox` | Doręczona do skrzynki pocztowej. |

`Shipping information received`, `Waybill generated`, `Shipping label created` i `Advance notice - package information received.` znaczą to samo co pierwszy wiersz: są dane, nie ma jeszcze paczki. Końcówka „usps awaiting item” znaczy, że istnieje etykieta amerykańskiej poczty USPS, której USPS jeszcze nie zeskanował.

Oprócz skanów 4PX przypisuje każdą paczkę do jednej zakładki, też po angielsku. `receive forecast` to zapowiedź przesyłki od sprzedawcy: 4PX zna zamówienie, ale nie ma paczki. `In Transit` obejmuje wszystko od magazynu po ostatni odcinek, także cło. `Arrived for Pickup` znaczy, że paczka czeka w punkcie odbioru. `Exception` zbiera kłopoty: brak kontaktu z odbiorcą, zły adres, odmowę przyjęcia, uszkodzenie, zaginięcie, nieodebranie w terminie, opóźnienie, nieudaną pierwszą próbę doręczenia albo problem z odprawą. `Suspected return` znaczy, że przewoźnik może odesłać paczkę. `Trajectory stalled` to 15 dni bez żadnej aktualizacji, a `No Data Found` znaczy, że 4PX nic nie znalazł: numer może być błędny albo nie ma jeszcze nowych danych.

## Kurier 4PX w Polsce: doręczenie i odbiór

4PX sam nie doręcza paczek do drzwi. Jego strona pisze, że paczka „is being delivered to the delivery company of the destination country”, czyli trafia do firmy doręczającej w kraju docelowym. Którego przewoźnika używa w Polsce, 4PX nie podaje. Sprawdzisz to przy swojej paczce: na stronie wyniku pole „Terminal Service Provider” pokazuje nazwę tej firmy, jej stronę i dane kontaktowe, a „Tracking No.” to numer, pod którym ją śledzisz.

Liczba prób doręczenia, awizo, zmiana terminu i czas przechowania w punkcie to zasady lokalnego przewoźnika, nie 4PX: 4PX żadnych nie publikuje. Przy `Arrived for Pickup` prosi tylko, by odebrać paczkę jak najszybciej, bo inaczej przewoźnik może ją odesłać. Po `Delivery attempt unsuccessful` umów nowe doręczenie albo odbiór u przewoźnika z pola „Terminal Service Provider”, jego własnymi kanałami.

## Paczka 4PX stoi, zaginęła albo wraca

**Brak śledzenia.** Strona 4PX pisze wtedy, że nie ma jeszcze śladu paczki, i prosi, by cierpliwie poczekać albo zapytać sprzedawcę o stan wysyłki. `No Data Found` może też znaczyć błędnie wpisany numer.

**Nic się nie dzieje od wielu dni.** Po 15 dniach bez skanu 4PX przenosi paczkę do `Trajectory stalled` i pisze, że może być zgubiona albo opóźniona albo że przewoźnik nie wprowadził skanów. Najpierw sprawdź śledzenie lokalnego przewoźnika pod numerem „Tracking No.”. Więcej w poradniku [status przesyłki się nie zmienia](guide:tracking-not-updating).

**Doręczona, a jej nie ma.** Przy `Successful delivery` 4PX prosi o kontakt z obsługą klienta w celu potwierdzenia. Zacznij od przewoźnika, który doręczał, i przeczytaj poradnik [paczka doręczona, a jej nie ma](guide:delivered-not-received).

**Zwrot albo zaginięcie.** 4PX nie publikuje dla odbiorców procedury reklamacji ani terminów; zwroty i reklamacje opisuje jako usługi dla sprzedawców. Umowę z 4PX ma sklep lub platforma, więc to ich proś o zwrot pieniędzy albo ponowną wysyłkę, a przewoźnika pytaj o wszystko, co stało się przy doręczeniu.

## 4PX kontakt: telefon i e-mail

Stan na 11 października 2026: strona śledzenia 4PX podaje w zakładce Help, Contact Us jeden kontakt dla wszystkich krajów:

- **Telefon:** 0755-23508000, numer w Shenzhen w Chinach; z Polski +86 755 2350 8000. 4PX nie podaje godzin ani języków obsługi.
- **E-mail:** track@4px.com.
- **Adres:** 4PX Express Co., Ltd., No.12 Building, FuYong e-cool, BaoAn District, ShenZhen, GuangDong, China.

Polskiego numeru, adresu ani biura dla odbiorców 4PX nie podaje. W sprawie zamówienia szybciej odpowie sklep, a w sprawie samego doręczenia przewoźnik z pola „Terminal Service Provider”.

## Śledzenie paczki 4PX w Peek

[Peek](/) rozpoznaje numer `4PX…CN` od razu. Przy numerze z LP, 13 cyframi i CN pokazuje „Peek pyta: 4PX…”, a potem „4PX ma tę przesyłkę.”, gdy 4PX zna numer; jeśli nie, wybierz przewoźnika przyciskiem „Zmień”. Link z track.4px.com z `#/result/0/` i numerem jest czytany jako 4PX bez względu na numer w środku, więc działa też z numerem „Tracking No.”. Zamówienia z kilkoma paczkami sprawdza w uniwersalnych serwisach śledzenia.

Peek czyta 4PX po angielsku i pokazuje status, historię z miejscem każdego skanu na mapie, godzinę doręczenia, kraj docelowy oraz lokalnego przewoźnika z jego numerem. Gdy ten przewoźnik jest na liście Peek (na przykład USPS), śledzenie przechodzi do niego, gdy tam pojawią się nowsze skany. `Out for delivery` i `Delivery attempt unsuccessful` widzisz w słowach Peek, pozostałe linie tak, jak pisze je 4PX. Przewidywanej daty dostawy, danych punktu odbioru ani wagi przy 4PX tu nie zobaczysz.

Godziny skanów Peek odczytuje w strefach czasowych 4PX (skan bez strefy czasowej według czasu miejsca skanu) i pokazuje w strefie czasowej Twojego urządzenia. Peek sprawdza paczkę w dzień co 10 minut, a co 2 minuty, gdy paczka jest w doręczeniu, w nocy zaś co godzinę lub dwie, i powiadomi Cię o każdym skanie, tylko o ważnych etapach albo „Tylko w dniu dostawy”. Nie skontaktuje się za Ciebie z 4PX ani ze sklepem.

## Pytania o śledzenie 4PX

### 4PX Express: co to za firma?

To chińska firma logistyczna z siedzibą w Shenzhen, działająca od 2004 roku, która wysyła za granicę paczki sprzedawców internetowych. W Dongguan prowadzi z Cainiao wspólny park logistyczny, ale żadna strona 4PX nie mówi, że należy do Cainiao.

### Jaki kurier doręcza paczki 4PX w Polsce?

4PX nie podaje jednego przewoźnika dla Polski. Firmę, która ma Twoją paczkę, pokazuje pole „Terminal Service Provider” na stronie wyniku, razem z jej numerem w „Tracking No.”.

### Jaki jest czas dostawy 4PX Express?

4PX nie publikuje czasów dostawy dla odbiorców, więc kieruj się terminem, który sklep lub platforma pokazuje przy zamówieniu. Po 15 dniach bez aktualizacji 4PX sam uznaje śledzenie za wstrzymane.

### Czy 4PX Express ma kontakt w Polsce?

Na stronie śledzenia nie: 4PX podaje tylko chiński telefon 0755-23508000 i e-mail track@4px.com, wspólne dla wszystkich krajów. W sprawie zamówienia pisz do sklepu.

:::sources
- [4PX: Global Order Tracking](https://track.4px.com/) – zasady numeru, zakładki statusów, tłumaczenie, angielski dla polskiej przeglądarki, pole Terminal Service Provider
- [4PX: plik językowy strony śledzenia](https://track.4px.com/js/index.6aecf0a2c58f0d7c56a3.js) – opisy zakładek, przekazanie lokalnemu przewoźnikowi, komunikat bez skanów, 100 numerów
- [4PX: Contact Us](https://track.4px.com/#/help/contact) – telefon, e-mail, adres
- [4PX: Privacy Policy](https://track.4px.com/#/help/privacy) – 4PX Group, reklamacje
- [4PX: For Perfect X-border](https://www.4px.com/view/index.html) – firma i usługi dla sprzedawców
- [4PX: 退换货 (zwroty)](https://www.4px.com/view/return.html) – zwroty jako usługa dla sprzedawców
- [4PX: 递四方成都至伦敦全货机航线成功首航](https://www.4px.com/view/newsCenterContentfive.html) – założona w 2004 roku
- [4PX: 菜鸟&递四方国际智慧物流园盛大开园](https://www.4px.com/view/newsCenterContentsix.html) – park logistyczny Cainiao i 4PX
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – co Peek odczytuje i pokazuje
:::
