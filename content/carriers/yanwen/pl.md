---
title: Yanwen śledzenie przesyłki: statusy, kurier, kontakt
description: Paczka Yanwen z Chin: jak wygląda numer, co znaczą angielskie statusy, kto doręcza w Polsce i do kogo się zwrócić, gdy paczka utknie.
slug: yanwen-sledzenie
published: 2026-10-11
updated: 2026-10-27
---

Yanwen to chińska firma logistyczna założona w 1998 roku w Pekinie, która wysyła paczki za granicę dla sprzedawców internetowych, a ostatni odcinek oddaje lokalnemu przewoźnikowi. Paczkę śledzisz na stronie Yanwen, [track.yw56.com.cn](https://track.yw56.com.cn/en/index), albo wklejasz numer w pole powyżej. Numer dostajesz od sklepu lub platformy, na której kupowałeś: szukaj go w danych wysyłki przy zamówieniu.

## Yanwen numer przesyłki: jak wygląda

Yanwen nie publikuje przykładowego numeru ani stron pomocy dla odbiorców. Kształty numerów paczek Yanwen, z wymyślonymi cyframi:

| Numer | Czyj to numer |
| --- | --- |
| `BYS123456789` | BYS i 9 cyfr: Yanwen. |
| `UL000000005YP` | Dwie litery, 9 cyfr i YP na końcu: Yanwen. Często zaczyna się od UL albo UK. |
| `YWABC123456789012` | YW, trzy litery i 12 cyfr: własna usługa doręczeń Yanwen w USA. |
| `RR000000005CN` | Ten sam pocztowy kształt z CN na końcu: [China Post](carrier:china-post), nie Yanwen. |
| `1234567890123U` | 13 cyfr i litera: numer [DPD Polska](carrier:dpd-pl), który paczka może dostać na ostatnim odcinku. |
| `YT1234567890123456` | Nie Yanwen, tylko [YunExpress](carrier:yunexpress). |
| `LP12345678901234` | Nie Yanwen, tylko numer logistyczny AliExpress, zobacz [Cainiao](carrier:cainiao). |

Pocztowy numer Yanwen zawsze kończy się na YP; inna końcówka należy do innej poczty. Na stronie wyników Yanwen pokazuje dwa numery: „Tracking Number”, czyli swój, i „Last Mile Tracking”, czyli numer przewoźnika, który doręcza w Polsce. Jeśli sklep podał Ci ten drugi numer, strona Yanwen też go przyjmie. Nie masz żadnego numeru? Poproś o niego sklep.

Strona Yanwen przyjmuje do 100 numerów naraz, ale samodzielnie sprawdzisz tylko przesyłki z ostatniego roku. Produktów China Post Yanwen nie śledzi.

## Yanwen śledzenie: co znaczą statusy

Strona śledzenia Yanwen ma tylko wersję angielską i chińską, a linie skanów są po angielsku w obu. Polskiej wersji nie ma. Najczęstsze linie, w kolejności, w jakiej spotyka je paczka do Polski:

| Yanwen pisze | Co to znaczy dla Ciebie |
| --- | --- |
| `Order processed by shipper` | Sprzedawca zamówił wysyłkę. Yanwen jeszcze nie ma paczki. |
| `Yanwen Pickup Scan` | Yanwen ma paczkę w Chinach. |
| `Yanwen facility - Outbound` | Paczka wyjechała z sortowni Yanwen. |
| `International shipment release - Export` | Odprawa wywozowa w Chinach zakończona. |
| `Port of departure - Departure` | Paczka odleciała samolotem z Chin. |
| `Port of destination - Arrival` | Dotarła do kraju docelowego albo w jego pobliże. |
| `International shipment release - Import` | Odprawa przywozowa zakończona, paczka jedzie dalej. |
| `Batch delivery to carrier` | Przekazana lokalnemu przewoźnikowi. Dalej idą jego skany. |
| `Parcel received by DPD depot` | Linia od DPD: paczka jest w oddziale DPD. |
| `Parcel dispatched to be delivered` | Linia od DPD: kurier wiezie paczkę do Ciebie. |
| `Parcel delivered` | Linia od DPD: paczka doręczona. |

`Order Submited.` (pisownia Yanwen), `Waybill Generated` i `Shipping Order Created.` znaczą to samo co pierwszy wiersz: istnieje tylko etykieta, a paczka mogła jeszcze nie opuścić sprzedawcy. `Pick up by local carrier at destination port` znaczy to samo co `Batch delivery to carrier`. Mylić może `Registered parcel data, parcel not dispatched yet`: to linia od DPD Polska: DPD ma już dane paczki, ale jeszcze nie samą paczkę. Nie znaczy to, że utknęła w Polsce. Później przychodzą linie DPD, na przykład `SMS notification` albo `Mail notification`, gdy DPD wysyła Ci wiadomość.

Nad skanami Yanwen przypisuje paczkę do kategorii. `Info_Received` to etap etykiety. `In_Transportation` obejmuje wszystko od lotu przez cło po odcinek lokalny. `End_of_Tracking` znaczy, że Yanwen przestał śledzić paczkę: nic nie mówi o tym, gdzie ona jest, i nie oznacza ani doręczenia, ani zgubienia. `Not_Found` z linią `No information was found` znaczy, że Yanwen nie ma nic pod tym numerem. Przy doręczonej paczce wiersz pokazuje liczbę dni w drodze, na przykład „Delivered（7d）”. Ogólne etapy drogi paczki opisuje [poradnik o śledzeniu paczki z Chin](guide:tracking-from-china).

## Kto doręcza paczki Yanwen w Polsce

Yanwen sam nie doręcza w Europie: pisze, że współpracuje „with local carriers to fulfill the last mile package delivery”. Gdy podaje tego przewoźnika, na stronie wyników pojawia się ramka z polami „Distributor”, „Distributor Website” i „Contact Number”. Przy części paczek ramka jest pusta.

Kto doręcza, zależy od kraju i od linii, którą wybrał sprzedawca. Przy paczkach do Polski była to na przykład firma DPD Polska, a w ramce stał adres dpd.com/pl. Wtedy dalsze linie są od DPD i paczkę możesz śledzić także numerem DPD z pola „Last Mile Tracking”.

Liczby prób doręczenia, awizo, zmiany terminu ani czasu przechowania paczki Yanwen nie opisuje: to zasady lokalnego przewoźnika. Kategoria `Available_For_Pick_Up` znaczy, że paczka czeka w punkcie odbioru, a `Undelivered`, że doręczenie się nie udało. O nowy termin pytaj przewoźnika z ramki, podając jego numer.

Strona wyników ma też okno „Proof of Delivery Images”, które otwiera się po wpisaniu kodu pocztowego odbiorcy. Każdy skan pokazuje przesunięcie względem GMT, a przełącznik zmienia strefę czasową.

## Paczka Yanwen nie dochodzi: co zrobić

**Jeszcze brak śledzenia.** Dopóki paczka jest w `Info_Received`, sprzedawca tylko zrobił etykietę i mógł jej jeszcze nie nadać. Zapytaj sklep, kiedy wysłał paczkę.

**Od kilku dni bez zmian.** Gdy paczkę ma już przewoźnik w Polsce, sprawdź jego śledzenie numerem z „Last Mile Tracking”. Więcej w poradniku [status przesyłki się nie zmienia](guide:tracking-not-updating).

**Doręczona, a jej nie ma.** Doręczał lokalny przewoźnik, więc zacznij od niego, potem napisz do sklepu. Zobacz [paczka doręczona, a jej nie ma](guide:delivered-not-received).

**Zgubiona, uszkodzona albo zwrócona.** Yanwen nie publikuje dla odbiorców ani procedury reklamacji, ani terminów, ani odszkodowań. Jego strony są dla sprzedawców, a umowę z Yanwen ma sklep lub platforma, więc to od nich żądaj zwrotu pieniędzy albo nowej paczki. `Exception` i `Returned` to kategorie Yanwen dla takich przypadków.

**Starsze paczki.** Po roku przesyłka znika z samodzielnego śledzenia; według komunikatu Yanwen konsultant na infolinii nadal może ją sprawdzić.

## Yanwen kontakt: telefon i e-mail

Stan na 11 października 2026 Yanwen podaje takie kanały:

- **Infolinia:** 400-108-5656, od poniedziałku do soboty, 9:00–18:00, według chińskiej strony kontaktowej. Numer 400 to chiński numer usługowy; Yanwen nie pisze, czy działa z zagranicy ani w jakich językach odpowiada.
- **E-mail:** marketing@yw56.com.cn, jedyny adres na angielskiej stronie. Yanwen nie nazywa go adresem obsługi klienta.
- **Inne kanały:** czat w prawym dolnym rogu yw56.com.cn, portal klienta i konto WeChat, wszystkie po chińsku i dla sprzedawców.

Polskiego kontaktu Yanwen nie publikuje: nie ma ani biura, ani numeru dla Polski. W sprawie zamówienia szybciej odpowie sklep albo platforma, a w sprawie samego doręczenia przewoźnik i numer z ramki na stronie wyników.

## Śledzenie paczki Yanwen w Peek

[Peek](/) od razu rozpoznaje numery BYS, numery z YP i amerykańskie numery YW. Numeru z YP z błędną cyfrą kontrolną nie rozpoznaje jako Yanwen: wybierz wtedy Yanwen przyciskiem „Zmień”. Link z track.yw56.com.cn z `nums=` jest czytany jako Yanwen, jakikolwiek numer zawiera, więc działa też z numerem lokalnego przewoźnika.

Widzisz status, historię z miastem lub regionem każdego skanu na mapie, godzinę doręczenia, kraj docelowy i lokalnego przewoźnika z jego numerem („Doręcza: DPD”, na przykład). Gdy Yanwen podaje przewoźnika z listy Peek, a numer do niego pasuje, aplikacja pyta tego przewoźnika i przełącza się na niego, gdy ten pokaże nowsze postępy. Jeśli nie, a numer z „Last Mile Tracking” ma kształt rozpoznawany na pewno, aplikacja pyta jego przewoźnika; w ostatniej kolejności, przy numerze z YP, Pocztę Polską. Nie dzieje się tak przy każdej paczce. Yanwen nie podaje przewidywanej daty doręczenia, punktu odbioru też nie zobaczysz, a numery zamówienia, kontakty, kody pocztowe, kody PIN i numery drzwi są pomijane.

Godziny skanów Peek odczytuje z przesunięciami GMT podanymi przez Yanwen i pokazuje w strefie czasowej Twojego urządzenia, a powtórzone skany są usuwane. `Delivered` i `Out for delivery` pojawiają się słowami aplikacji, inne linie tak, jak je zapisano, także `Delivered.` z kropką. Śledzenie jest sprawdzane w dzień co 10 minut, a co 2 minuty, gdy paczka jest w doręczeniu; w nocy co godzinę lub dwie. Powiadomienia możesz dostawać o każdym skanie, o ważnych etapach albo tylko o doręczeniu („Tylko doręczenie”). Peek nie skontaktuje się za Ciebie z Yanwen ani ze sklepem.

## Pytania o śledzenie Yanwen

### Co to za firma Yanwen?

To chiński operator logistyczny dla handlu internetowego, założony w 1998 roku w Pekinie, z własną stroną śledzenia. Według własnych danych od 2015 roku jest polecanym dostawcą usług logistycznych dla AliExpress i eBay.

### Co znaczy Yanwen facility - Outbound?

Paczka wyjechała z sortowni Yanwen w Chinach. Potem zwykle przychodzą `Arrived at domestic terminal station`, odprawa wywozowa i lot.

### Gdzie jest moja paczka Yanwen?

Najnowszy skan pokazuje etap, a jeśli je podaje, także miasto lub region. Po `Batch delivery to carrier` paczkę ma przewoźnik w Polsce: jego nazwę i numer znajdziesz na stronie wyników Yanwen.

### Ile idzie paczka Yanwen do Polski?

Yanwen nie publikuje czasów doręczenia dla odbiorców, więc kieruj się terminem, który pokazuje sklep albo platforma przy zamówieniu. Na długie postoje po `Yanwen facility - Outbound` Yanwen też nie podaje normy.

:::sources
- [Yanwen: YW Tracking](https://track.yw56.com.cn/en/index) – strona śledzenia, języki, kolumny wyników, kategorie, ramka przewoźnika, potwierdzenie doręczenia, strefy czasowe, infolinia
- [Yanwen: 燕文追踪 (śledzenie, po chińsku)](https://track.yw56.com.cn/) – skany po angielsku, komunikat o China Post
- [Yanwen: YANWEN EXPRESS, strona główna](https://www.yw56.com.cn/en/) – firma, 1998, Pekin, ostatni odcinek przez lokalnych przewoźników, infolinia, e-mail
- [Yanwen: Company Profile](https://www.yw56.com.cn/webfile/Profile/) – AliExpress i eBay od 2015
- [Yanwen: Contact Us](https://www.yw56.com.cn/webfile/ContactUs/) – infolinia, e-mail
- [Yanwen: 联系我们 (kontakt, po chińsku)](https://www.yw56.com.cn/webfile/联系我们/) – godziny infolinii
- [Yanwen: 关于官方快件查询入口及服务的通告 (oficjalne kanały śledzenia)](https://www.yw56.com.cn/webfile/ANNOUNCEMENTS/2025/06-30/7345353199651393536.html) – 100 numerów, limit jednego roku, sprawdzanie przez infolinię, czat, portal, WeChat
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – co Peek odczytuje i pokazuje, linie DPD Polska w historiach Yanwen
:::
