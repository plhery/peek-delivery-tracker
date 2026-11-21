---
title: Śledzenie Techlink: numery ZHPHD…ML, statusy i dostawa
description: Śledź paczkę Techlink (智禾) z Chin: jak wygląda numer ZHPHD…ML, co znaczą skany po angielsku i chińsku, kto doręcza przesyłkę i co zrobić, gdy utknie.
slug: sledzenie-techlink
published: 2026-11-21
updated: 2026-11-21
---

Techlink (智禾国际物流, Zhihe International Logistics) to firma logistyczna z Shenzhen w Chinach kontynentalnych, wysyłająca paczki sprzedawców internetowych do Europy i Ameryki. Jej numery zaczynają się od `ZHPHD` i kończą na `ML`: wpisz swój kod na stronie śledzenia Techlink (`www.techlinksz.cn/trajectory`) albo wklej go w pole powyżej. Numer przekazuje sklep w szczegółach wysyłki zamówienia.

## Numer przesyłki Techlink: jak wygląda

Techlink nie publikuje opisu formatu swoich numerów. Jego listy przewozowe liczą dokładnie 19 znaków: `ZHPHD`, sześć cyfr tworzących prawidłową datę (rok, miesiąc, dzień), sześć kolejnych cyfr oraz `ML`. Ze zmyślonymi cyframi:

| Numer | Co oznacza |
| --- | --- |
| `ZHPHD260315482910ML` | `ZHPHD`, sześciocyfrowa data, sześć cyfr i `ML`: list przewozowy Techlink. |
| `ZHPHD261315482910ML` | Miesiąc `13` nie istnieje: to nie jest poprawny numer Techlink. Sprawdź, czy nie ma literówki. |
| `RR482910576CN` | Dwie litery, dziewięć cyfr i `CN`: przesyłka pocztowa [China Post](carrier:china-post), a nie Techlink. |

W Peek wielkość liter i spacje nie mają znaczenia. Inne układy kodów omawia poradnik [formaty numerów przesyłek](guide:tracking-number-formats).

**Gdzie go znaleźć.** Klientami Techlink są sprzedawcy. Strona główna podaje, że linie małych paczek do Europy i Ameryki obsługują sprzedawców na Amazonie, Walmarcie i `TK` oraz niezależne sklepy internetowe; nie wymienia AliExpress, Temu ani Shein. Sprzedawca nadaje przesyłkę i udostępnia numer w wiadomości e-mail o wysyłce albo na karcie zamówienia. Jeśli nie możesz go znaleźć, zajrzyj do poradnika [jak znaleźć numer przesyłki](guide:find-tracking-number).

## Statusy śledzenia Techlink: co oznaczają

Wyszukiwarka Techlink przyjmuje do 40 numerów naraz, po jednym w wierszu lub rozdzielonych przecinkami, i pozostaje po chińsku nawet po przełączeniu menu na angielski. Przy każdym kodzie wyświetla ostatni wpis, łączną liczbę skanów (`条轨迹`) oraz kraje nadania i przeznaczenia po chińsku. Kliknij wiersz albo przycisk `展开全部` (rozwiń wszystko), aby zobaczyć każdy krok z godziną i krajem.

Kolorowy symbol przed numerem podsumowuje ostatni skan: `▤` w magazynie Techlink, `➤` w drodze lub w doręczeniu, zielony znaczek `✓` doręczono, czerwony wykrzyknik `!` problem (np. nieudana próba doręczenia) oraz `?` brak wyników.

Techlink nie publikuje słownika statusów, ale zapisuje każdy skan po angielsku i chińsku wraz z godziną i zazwyczaj krajem:

| Komunikat Techlink | Co oznacza dla paczki | Peek pokazuje |
| --- | --- | --- |
| `Parcel electronic information received` (`收到电子信息`) | Sprzedawca przesłał Techlink dane elektroniczne zamówienia; paczka nie dotarła jeszcze do przewoźnika. | „Zarejestrowana” |
| `Parcel has been received and processed at original facility` (`仓库入库`) | Paczka została przyjęta w magazynie Techlink w Chinach kontynentalnych. | „Przyjęta przez przewoźnika” |
| `Parcel has been packed and is ready for outbound shipping` (`包裹装箱`) | Spakowana w magazynie do wysyłki zagranicznej. | „W transporcie” |
| `Departed from the original facility` | Opuściła magazyn początkowy Techlink w Chinach kontynentalnych. | „W transporcie” |
| `Export customs declaration completed` | Zgłoszenie celne wywozowe w Chinach kontynentalnych zakończone po wyjściu z magazynu; paczka nie utknęła na cle. | „W transporcie” |
| `Parcel is in transit to origin airport` | Transport drogowy w Chinach kontynentalnych na lotnisko wylotu. | „W transporcie” |
| `Depart from the original airport` | Samolot wystartował z lotniska nadania. | „W transporcie” |
| `Arrive at the destination airport` | Samolot wylądował w kraju docelowym, przed odprawą celną przywozową. | „W transporcie” |
| `Shipment Received at Facility` | Przyjęta w hubie w kraju docelowym po locie. | „W transporcie” |
| `Your shipment has arrived at sorting center` | Dotarła do sortowni w kraju docelowym. | „W transporcie” |
| `Your shipment has been processed at sorting center and ready for shipping` | Posortowana w tej sortowni i gotowa do dalszej drogi. | „W transporcie” |
| `Your shipment has departed from the transit origin` | Wyjechała z sortowni do kolejnego punktu. | „W transporcie” |
| `In transit, it is currently in transit to the next facility` | W trasie między dwoma obiektami w kraju docelowym. | „W transporcie” |
| `The parcel has been successfully picked up and is being prepared for transportation.` | Dla paczek do Australii: przejęcie po locie przez J&T Express Australia, po którym pojawiają się skany tranzytowe i dystrybucyjne J&T. | „W transporcie” |
| `Parcel is out for delivery.` | Przekazany skan przewoźnika lokalnego: paczka została wydana kurierowi do doręczenia. | „W doręczeniu” |
| `Parcel delivery failed [Wrong Address]. Parcel will be returned to the warehouse.` | Nieudana próba doręczenia u lokalnego kuriera (tutaj błędny adres; w nawiasach kwadratowych może widnieć inny powód) z powrotem do lokalnego magazynu. | „Próba doręczenia” |

Dla nieznanego numeru strona Techlink wyświetla `查询不到轨迹` (nie znaleziono śledzenia), `未知 → 未知` (nieznane kraje) oraz `暂无最新轨迹` (brak najnowszego skanu).

## Kto doręcza paczki Techlink w kraju docelowym

Techlink nie podaje nazw partnerów ostatniej mili. Strona główna opisuje linie od drzwi do drzwi: fracht lotniczy z Chin kontynentalnych, odprawę celną za granicą prowadzoną przez własną zagraniczną spółkę założoną w 2023 r. oraz doręczenie przez własne konta u przewoźników w kraju docelowym — bez wymieniania żadnego z nich na stronie ani w śledzeniu. W historiach odczytanych przez Peek we Włoszech paczki trafiały do [iMile](carrier:imile), którego wpisy zaczynają się nazwą centrum w nawiasach, np. `【Milan center】Parcel shipped to【VEN 07】.` W Australii po locie przesyłki przejmuje [J&T Express Australia](carrier:j-and-t-au) od wpisu `The parcel has been successfully picked up and is being prepared for transportation.` W Stanach Zjednoczonych Techlink przekazuje w części przesyłek skany [YWE](carrier:ywe), m.in. `Parcel departed from Facility.` i `Parcel received by local Facility.`, aż po `Parcel is out for delivery.` lub nieudaną próbę doręczenia.

Dane w systemie Techlink mogą zawierać numer nadany przez przewoźnika końcowego bez jego nazwy, a strona internetowa Techlink nie ma pola, w którym by go wyświetlała. Gdy ustalisz, kto doręcza paczkę na miejscu, to regulamin tej firmy określa próby doręczenia, awiza, punkty odbioru i czas przechowywania — Techlink własnych zasad nie publikuje.

Usługa ekspresowa od drzwi do drzwi Techlink występuje w wariancie DDP (cło opłacone) lub DDU (cło nieopłacone). Od opcji wybranej przez sprzedawcę zależy, czy przy odbiorze mogą pojawić się należności przywozowe: zobacz [paczka zatrzymana przez urząd celny](guide:customs).

## Paczka Techlink utknęła albo nie dochodzi

**Widnieje tylko `Parcel electronic information received`.** Sprzedawca zgłosił przesyłkę elektronicznie, ale paczka nie dotarła jeszcze do magazynu Techlink w Chinach kontynentalnych. Jeśli przez kilka dni nic się nie zmienia, zapytaj sprzedawcę, czy faktycznie ją wysłał.

**Cisza po `Arrive at the destination airport`.** Samolot wylądował w Twoim kraju; przed paczką odprawa celna przywozowa oraz przekazanie lokalnemu kurierowi, co potrafi zająć kilka dni bez nowego skanu. W wielu przesyłkach historia Techlink urywa się na lądowaniu lub kilka kroków dalej w lokalnym hubie bądź sortowni (`Shipment Received at Facility` do `In transit, it is currently in transit to the next facility`), choć dla części paczek przekazuje skany doręczyciela aż do `Parcel is out for delivery.` Gdy śledzenie stoi w miejscu, poproś sprzedawcę o nazwę przewoźnika w Twoim kraju i jego lokalny numer przesyłki. Więcej w poradnikach [dlaczego śledzenie się nie aktualizuje](guide:tracking-not-updating) oraz [śledzenie paczki z Chin](guide:tracking-from-china).

**Brak wyników.** Sprawdź kod u sprzedawcy: numer `ZHPHD…ML` ma dokładnie 19 znaków. Świeżo zarejestrowane zamówienie również może jeszcze nie zwracać wpisów.

**Zagubiona, uszkodzona albo oznaczona jako doręczona, lecz nieodebrana.** Techlink nie udostępnia procedury reklamacyjnej dla odbiorców. Umowę z przewoźnikiem ma sprzedawca, więc o zwrot pieniędzy lub ponowną wysyłkę zwróć się do sklepu albo platformy zakupowej. Zobacz [zagubiona paczka](guide:lost-parcel), [dostarczona, ale nieodebrana](guide:delivered-not-received), a przy zamówieniach z Amazona [śledzenie zamówień Amazon](guide:amazon-orders).

## Kontakt z Techlink i obsługa klienta

Strona internetowa Techlink (`www.techlinksz.cn`) nie podaje numeru telefonu, adresu e-mail ani adresu siedziby. Odnośnik `联系我们` (kontakt) prowadzi donikąd, a w serwisie nie ma czatu ani formularza kontaktowego.

W sprawach dotyczących zamówienia pisz bezpośrednio do sprzedawcy, natomiast w kwestii samego doręczenia w kraju odbioru — do lokalnego przewoźnika, gdy już znasz jego nazwę i numer.

## Śledzenie przesyłek Techlink w Peek

[Peek](/) natychmiast rozpoznaje każdy numer `ZHPHD…ML` jako przesyłkę Techlink — żaden inny przewoźnik w katalogu nie używa tego układu — i wyświetla „Wykryty przewoźnik” z przyciskiem „Zmień”. Nie ma linku Techlink do wklejenia, ponieważ `www.techlinksz.cn/trajectory` nie umieszcza numeru w adresie strony.

Peek odczytuje ten sam system śledzenia co strona Techlink i pokazuje aktualny status, każdy skan z krajem oraz kraj docelowy. Angielskie komunikaty wyświetlają się tak, jak zapisuje je Techlink (pod udostępnionym linkiem doręczenie pokazuje wyłącznie `Delivered`). Własne skany Techlink w Chinach kontynentalnych, początkowe zgłoszenie elektroniczne bez lokalizacji oraz skany zagraniczne zaczynające się nazwą centrum w nawiasach (`【…】`) są odczytywane według czasu pekińskiego; przekazywane skany J&T Express Australia dla paczek do Australii według czasu Sydney niezależnie od stanu magazynu; pozostałe zachowują lokalny zegar swojego kraju, a Peek przelicza godziny ze znaną strefą na strefę Twojego urządzenia — na `peektracker.com` oraz w aplikacjach na iPhone'a i Androida pod historią widnieje dopisek „Godziny w Twojej strefie czasowej: …”. Techlink nie podaje przewidywanej daty doręczenia, wagi ani nazwy usługi.

Dopóki Techlink nie zna numeru, Peek pokazuje „Oczekiwanie na przewoźnika Techlink”; jeśli odpowiedź oznacza brak zamówienia i po dwóch dniach żadne inne źródło nie odpowie, pojawia się komunikat „Po dwóch dniach przewoźnik nadal nie zna tego numeru. Sprawdź go, wybierz innego przewoźnika lub usuń przesyłkę.”

Gdy rekord Techlink zawiera numer przewoźnika doręczającego, Peek sprawdza jego kształt: międzynarodowy numer pocztowy (dwie litery, dziewięć cyfr, dwie litery) wskazuje operatora pocztowego kraju docelowego, a format właściwy tylko jednemu przewoźnikowi w katalogu Peek wskazuje tę firmę. Jeśli Peek obsługuje bezpośrednie śledzenie takiego przewoźnika, odpytuje go i przełącza się na niego, gdy tylko pokaże postęp równie świeży jak w Techlink, zachowując kod `ZHPHD…ML` obok drugiego numeru. W przeciwnym razie pozostaje przy śledzeniu Techlink, które często kończy się po przylocie na lotnisko docelowe lub w tamtejszej sortowni.

Gdy masz otwartą kartę przesyłki lub w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza aktualizacje nawet co 10 minut — a od godz. 6:00 czasu lokalnego w dniu wydania paczki do doręczenia nawet co 2 minuty, dopóki odpowiada własny system Techlink (przez zewnętrzne serwisy śledzenia same: co godzinę, co 3 godziny po spokojnym dniu i do co 15 minut w dniu doręczenia przy otwartej paczce lub włączonych alertach; po przekazaniu paczki Peek przejmuje rytm przewoźnika doręczającego) —; poza tym, a także po dwóch dniach bez zmian (lub 12 godzinach na etapie „Zarejestrowana” bądź „Przyjęta przez przewoźnika”), sprawdza co godzinę. Powiadomienia ustawisz na „Każdy skan”, „Ważne etapy” albo „Tylko doręczenie”. Peek nie skontaktuje się za Ciebie z Techlink ani ze sprzedawcą.

## Najczęstsze pytania o śledzenie Techlink

### Czym jest Techlink (智禾)?

Techlink to `智禾国际物流(深圳)有限公司` (Zhihe International Logistics, Shenzhen), firma logistyczna z Chin kontynentalnych obsługująca sklepy internetowe na liniach lotniczych małych paczek oraz ekspresowych od drzwi do drzwi do Europy i Ameryki. Jej witryna to `www.techlinksz.cn`.

### Co to jest numer śledzenia ZHPHD?

19-znakowy list przewozowy Techlink złożony z prefiksu `ZHPHD`, sześciu cyfr daty, sześciu kolejnych cyfr oraz końcówki `ML`. Sprawdzisz go na `www.techlinksz.cn/trajectory` albo bezpośrednio w Peek.

### Kto doręcza paczki Techlink w moim kraju?

Lokalny przewoźnik, którego Techlink nie wymienia ani na swojej stronie, ani w wyszukiwarce (w historiach odczytanych przez Peek pojawiały się m.in. iMile we Włoszech, J&T Express Australia w Australii oraz YWE w USA). Jeśli skany na to nie wskazują, a Peek nie przełączył się automatycznie po drugim numerze, zapytaj sprzedawcę o nazwę firmy kurierskiej i jej lokalny numer.

### Dlaczego śledzenie Techlink zatrzymało się po przylocie na lotnisko?

W wielu przesyłkach własny system Techlink kończy wpisy na `Arrive at the destination airport` lub kilka kroków później w hubie bądź sortowni w kraju docelowym, a dalszą drogę widać wyłącznie u lokalnego doręczyciela. Poproś sprzedawcę o lokalny numer śledzenia i zgłoś się do niego, gdy minie deklarowany termin dostawy.

:::sources
- [Techlink: 智禾官网 (strona główna)](http://www.techlinksz.cn/) – nazwa spółki w Shenzhen, linie małych paczek do Europy i Ameryki, sprzedawcy na Amazonie, Walmarcie, TK i w sklepach własnych, ekspres od drzwi do drzwi DDU i DDP, zagraniczna spółka celna z 2023 r., konta doręczeniowe w krajach docelowych oraz niedziałający link kontaktowy
- [Techlink: 轨迹跟踪 (śledzenie)](http://www.techlinksz.cn/trajectory) – wyszukiwanie do 40 numerów po chińsku, kraje nadania i przeznaczenia, ikony statusu oraz komunikat dla nieznanego numeru
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – budowa numeru ZHPHD…ML, komunikaty po angielsku i chińsku, przekazywane skany iMile we Włoszech, J&T Express Australia w Australii i YWE w USA, strefy czasowe Pekinu i Sydney, przełączenie po drugim numerze oraz prezentacja w Peek
:::
