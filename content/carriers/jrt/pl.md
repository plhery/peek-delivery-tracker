---
title: JRT śledzenie przesyłki: numer JRT…MM, statusy i DHL
description: Paczka JRT (今日通) z Xiamen w Chinach: jak wygląda numer JRT…MM, co znaczą chińskie i angielskie statusy lotu i odprawy celnej oraz kto ją doręcza.
slug: jrt-sledzenie
published: 2026-10-31
updated: 2026-10-31
---

JRT (今日通, Jinritong) to 厦门今日通商贸有限公司, firma spedycyjna z Xiamen w Chinach. Jeśli chiński sklep wysłał Twoje zamówienie przez JRT, dostajesz numer, który zaczyna się od JRT i kończy na MM: wklej go w pole powyżej. Śledzenie JRT pokazuje drogę paczki z Chin: lot, odprawę celną i przekazanie firmie, która ją doręcza. Przy wysyłkach do Europy w historii JRT pojawia się dalej DHL: JRT przepisuje jego skany.

## Numer przesyłki JRT: jak wygląda

Numery listów przewozowych JRT to litery JRT, dziesięć cyfr i MM, razem 15 znaków; JRT to inicjały chińskiej nazwy 今日通. Przykłady z wymyślonymi cyframi:

| Numer | Co to jest |
| --- | --- |
| `JRT0000000000MM` | JRT, dziesięć cyfr i MM: list przewozowy JRT. |
| `JRT000000000MM` | Tylko dziewięć cyfr: tak numer JRT nie wygląda. Sprawdź, czy nie brakuje cyfry. |
| `UP0000000000000000` | UP i szesnaście cyfr: numer referencyjny nadawcy, pod którym JRT może znaleźć zamówienie. Nic nie wskazuje, że takich numerów używają tylko klienci JRT. |
| `RR000000005CN` | Dwie litery, dziewięć cyfr i CN: numer [China Post](carrier:china-post), nie JRT. |

W Peek spacje i wielkość liter nie mają znaczenia. Nie możesz znaleźć numeru? Zobacz, [gdzie szukać numeru przesyłki](guide:find-tracking-number). Strona śledzenia JRT znajdzie zamówienie także po numerze referencyjnym sprzedawcy, jeśli wpiszesz go dokładnie tak, jak zapisał go sprzedawca, razem z myślnikami.

**Link z cyframi zamiast nazwy.** Strona śledzenia JRT działa pod samym adresem IP serwera i po zwykłym HTTP, nie pod domeną, a Peek nie zna żadnej innej strony JRT. Link do niej ma więc w miejscu nazwy strony same cyfry i zawiera `trackIndex.htm`, `documentCode=` oraz numer albo numer referencyjny: możesz go wkleić do Peek tak, jak jest. Sam Peek do tej strony nie linkuje, bo link po zwykłym HTTP przesyłałby Twój numer bez szyfrowania.

## Statusy JRT: co znaczą

Wiele linii JRT to tekst po chińsku, a za nim angielskie tłumaczenie samego JRT. Peek pokazuje je w całości. W kolejności, w jakiej spotyka je paczka:

| JRT pisze | Co to znaczy dla Ciebie | W Peek |
| --- | --- | --- |
| `货物电子信息已经收到` | Otrzymano elektroniczne dane przesyłki, czyli sprzedawca założył zamówienie. Tę linię zapisuje platforma Hualei (华磊), na której działa śledzenie JRT; to nie jest skan. | „Zarejestrowana” |
| `Shipment information received` | Dane przesyłki dotarły do JRT, ale nikt jeszcze nie zeskanował paczki. | „Zarejestrowana” |
| `到达收货点` | Paczka dotarła do punktu przyjęć JRT: to pierwszy skan, gdy JRT ma ją już u siebie. | „Przyjęta przez przewoźnika” |
| `货物离开操作中心` | Paczka wyjechała z centrum operacyjnego JRT. | „W transporcie” |
| `货物已交机场,等待航班安排中 Cargo delivered to airport, pending flight arrangement` | Przekazana na lotnisko, czeka na lot. „Delivered” znaczy tu: dostarczona na lotnisko, nie do Ciebie. | „W transporcie” |
| `报关已放行等待…号航班起飞 Customs released,Awaiting departure on flight of the …` | Odprawa wywozowa w Chinach zakończona, paczka czeka na lot w dniu podanym w miejscu kropek. To jeszcze nie odprawa przywozowa. | „W transporcie” |
| `航班起飞，以实际抵达为准 Flight departure subject to actual arrival time` | Samolot wystartował. | „W transporcie” |
| `航班落地，拆板中 Flight landing, dismantling board in progress` | Samolot wylądował. Ładunek jest zdejmowany z palet lotniczych przed odprawą. | „W transporcie” |
| `机场内等待提货，等待清关 Waiting for cargo pickup and customs clearance at the airport.` | Paczka jest na lotnisku docelowym i czeka na odbiór z lotniska i odprawę przywozową. | „W urzędzie celnym” |
| `已提货，清关中 The goods have been picked up and are currently undergoing customs clearance` | Trwa odprawa celna przywozowa. | „W urzędzie celnym” |
| `清关完成，卡车发运 Customs clearance completed; truck dispatched.` | Po odprawie, w drodze ciężarówką. | „W transporcie” |
| `已交服务商 Delivered to service provider` | Przekazana firmie, która doręcza ostatni odcinek. Jeszcze nie Tobie. | „W transporcie” |

Linie, które JRT pisze tylko po chińsku, Peek pokazuje w angielskim brzmieniu, jakie nadają im inni spedytorzy: `货物电子信息已经收到` jako „The electronic cargo information has been received”, `到达收货点` jako „Arrived at the receiving point”, `货物离开操作中心` jako „Goods leave the operation center”, a `快件操作完成` jako „Shipment departed from facility”. Linia `货物已交机场,预计…号的航班 Goods delivered to airport, scheduled on flight of the …` podaje dzień zarezerwowanego lotu w obu częściach; Peek pokazuje ją jako „Goods delivered to airport”, bo lot jeszcze się nie odbył.

**Linie tylko po angielsku.** Inne linie JRT są wyłącznie po angielsku i w Peek mają status „W transporcie”, chyba że zaznaczono inaczej:

- `Shipment arrived at facility`, `Sending to the airport`, `The goods have been packed and sent to the airline`: paczka jest w drodze na lotnisko.
- `Arriving at the airport - arranging flights`, `Arriving at the airline, in the process of booking a flight`: trwa rezerwacja lotu.
- `Flight is expected to take off on …` albo `The flight is expected to depart on … and land on …`, z datami w miejscu kropek: lot jest zarezerwowany.
- `Flight has flown`, `The flight has departed and is expected to … land`: samolot jest w powietrzu.
- `Flight has arrived`, `The flight has arrived and is awaiting dismantling`, `The NOA has been received` (zawiadomienie linii lotniczej o przylocie), `Dismantling and picking up goods in progress`: po wylądowaniu, przed odprawą.
- `Customs clearance of the cargo is in progress` („W urzędzie celnym”), `Customs clearance completed`, a potem `The goods have been handed over to the carrier`, czyli przekazanie firmie, która doręcza.

W liniach zaczynających się od `The flight` daty mają kolejność miesiąc.dzień: 11.02 to 2 listopada, nie 11 lutego.

**Linie DHL i FedEx.** Przy wysyłkach do Europy historia w śledzeniu JRT ciągnie się dalej skanami DHL, przepisanymi zwykle małymi literami. `the instruction data for this shipment have been provided by the sender to dhl electronically` znaczy, że DHL ma dane z etykiety („Zarejestrowana”). `The shipment has been posted by the sender at the retail outlet` to nadanie paczki przez JRT w placówce Deutsche Post, a `pick-up was successful.` znaczy, że DHL odebrał nadaną paczkę (to nie Twój odbiór). Potem pojawiają się `the shipment has been processed in the parcel center of origin`, `loaded to movement / tour vehicle` i `the shipment has been processed in the destination parcel center` (sortownia DHL bliżej Ciebie), wszystkie „W transporcie”. Na końcu możesz zobaczyć `not delivered-consignee could not be reached` (kurier Cię nie zastał: „Próba doręczenia”), `held for consignee collection` (paczka czeka na Ciebie w punkcie odbioru: „Do odbioru”) i `the shipment has been successfully delivered` („Dostarczona”). Historia części paczek zaczyna się zamiast tego od `Shipment information sent to FedEx`: dane z etykiety trafiły do FedEx, który doręcza ostatni odcinek („Zarejestrowana”).

Wszystkie godziny w śledzeniu JRT są podane w czasie pekińskim, także w przepisanych skanach DHL: 17:00 w Pekinie to 10:00 w Polsce zimą i 11:00 latem.

## Kto doręcza paczki JRT w Polsce

**Przy wysyłkach do Europy** historia JRT kończy się skanami DHL: JRT nadaje paczkę w placówce Deutsche Post, a jego śledzenie przepisuje skany DHL. Jeśli widzisz je w historii przesyłki, ostatni odcinek ma DHL. Rekord JRT nie zawiera ani numeru DHL, ani nazwy kanału wysyłki, więc Peek nie może śledzić paczki w systemie DHL. Poproś sprzedawcę o numer DHL: śledzenie DHL prowadzi paczkę aż do doręczenia (numery i statusy DHL opisuje strona [DHL Paket](carrier:dhl)).

**Przy FedEx** pierwsza linia historii wymienia FedEx, ale Peek nie śledzi paczki dalej w systemie FedEx. Poproś sprzedawcę o numer FedEx i śledź go na stronie [FedEx](carrier:fedex).

**W pozostałych przypadkach** Peek nie wie, kto doręcza paczki JRT. Zapytaj sprzedawcę, która firma doręcza Twoje zamówienie i pod jakim numerem: próby doręczenia, punkty odbioru i czas przechowania paczki to zasady tej firmy. Musisz zapłacić cło albo VAT? Zobacz poradnik o [paczce zatrzymanej przez urząd celny](guide:customs).

## Paczka JRT nie dochodzi albo śledzenie stoi

**Tylko pierwsze linie.** Gdy widzisz tylko `货物电子信息已经收到`, `Shipment information received` albo samą linię etykiety DHL lub FedEx, sprzedawca zarejestrował paczkę, ale nikt jej jeszcze nie zeskanował. Jeśli przez kilka dni nic nie przybywa, zapytaj sprzedawcę, czy naprawdę ją wysłał.

**Czeka na lot.** Linia o locie podaje dzień, na który go zarezerwowano. Jeśli ten dzień minie bez linii o starcie samolotu, daj znać sprzedawcy.

**Nic po `已交服务商`.** JRT skończył swoją część. Jeśli jego śledzenie nie przepisuje skanów DHL, dalsze kroki są w śledzeniu firmy, która doręcza: poproś sprzedawcę o jej numer. Więcej w poradnikach [status przesyłki się nie zmienia](guide:tracking-not-updating) i [śledzenie paczki z Chin](guide:tracking-from-china).

**Nic nie znaleziono.** Gdy JRT nie zna numeru, jego strona pokazuje jeden wynik z tym numerem jako numerem referencyjnym i wszystkimi innymi kolumnami pustymi. Peek pokazuje wtedy „Oczekiwanie na przewoźnika” (a gdy sam dobiera przewoźnika do numeru z UP: „Oczekiwanie na pierwszy skan przewoźnika JRT”) i sprawdza dalej. Porównaj numer z wiadomością od sprzedawcy, znak po znaku.

**Doręczona, a nic nie przyszło.** Skan doręczenia pochodzi od firmy, która doręcza, nie od JRT, nawet gdy JRT go przepisuje: zapytaj tę firmę, potem napisz do sprzedawcy. Więcej w poradniku [paczka doręczona, a jej nie ma](guide:delivered-not-received).

**Linie o zwrocie.** W przepisanych liniach DHL `shipment will be returned to origin-not ordered` znaczy, że odbiorca odmówił przyjęcia paczki, bo jej nie zamawiał, a linie z dopiskiem `(return to sender)`, że paczka wraca do nadawcy. `returned shipment has been received by processing center` to paczka z powrotem w centrum JRT. Po takiej linii `the shipment has been successfully delivered` oznacza doręczenie z powrotem do nadawcy, więc Peek pokazuje „Zwrócona nadawcy”, nie „Dostarczona”.

**Zgubiona albo zwrócona.** Przesyłkę u JRT zamówił sprzedawca, więc to od niego, albo od platformy, na której kupowałeś, żądaj zwrotu pieniędzy lub nowej paczki. Zobacz poradniki o [zgubionej paczce](guide:lost-parcel) i [zwrocie do nadawcy](guide:returned-to-sender).

## JRT kontakt: telefon i obsługa klienta

Stan na 29 października 2026 Peek nie znalazł danych kontaktowych JRT: poza stroną śledzenia nie zna żadnej strony firmy ani numeru telefonu, adresu e-mail czy adresu pocztowego dla odbiorców. Paczkę u JRT zamówił sprzedawca i to jego pytaj o zamówienie, a gdy nie odpowiada, platformę, na której kupowałeś.

## Śledzenie paczki JRT w Peek

[Peek](/) rozpoznaje numer JRT po samym kształcie: JRT, dziesięć cyfr i MM, a takich numerów nie ma żaden inny przewoźnik z jego katalogu. Rozpoznaje też link do strony śledzenia JRT (opisany wyżej). Przy numerze referencyjnym z UP i szesnastoma cyframi Peek pyta stronę JRT, czy go zna, i wybiera JRT, gdy zna. Inny numer referencyjny nie ma kształtu, po którym dałoby się go rozpoznać: i tak go wklej, bo Peek śledzi ponad 3500 przewoźników, a potem kliknij „Zmień” i wybierz JRT (znajdziesz go też, wpisując 今日通 albo Jinritong). Przy dodanej już przesyłce użyj „Zmień przewoźnika”. Peek pokaże wtedy numer JRT przy „JRT śledzi przesyłkę pod numerem”. Peek usuwa jednak myślniki, więc numeru referencyjnego z myślnikami przez Peek nie znajdziesz: poproś sprzedawcę o numer JRT…MM.

Peek pokazuje status, każdą linię z jej miejscem, a także nazwę usługi i kraj docelowy, gdy rekord JRT je podaje. Status ustala na podstawie linii, nie podsumowania w rekordzie. Godziny odczytuje w czasie pekińskim i pokazuje w strefie czasowej Twojego urządzenia. Pomija linię zapisaną ponad dwanaście godzin przed godziną, którą nosi, i każdą linię z datą późniejszą niż jego sprawdzenie. Nie odczytuje nazwisk, adresu, kodów pocztowych, wagi, opłat, podpisu ani potwierdzenia doręczenia i nie pokazuje przewidywanej daty doręczenia: JRT jej nie podaje.

Paczki do Europy Peek śledzi tylko na stronie JRT, razem z przepisanymi skanami DHL. Jedyne przekazanie, które Peek bierze z rekordu JRT, to przekazanie Poczcie Szwajcarskiej, gdy rekord podaje jej kanał wysyłki i numer przy paczce do Szwajcarii lub Liechtensteinu. Gdy sprzedawca poda Ci numer innej firmy, która doręcza, dodaj go jako osobną przesyłkę.

Peek sprawdza paczkę w dzień co 10 minut, a w nocy co godzinę lub dwie. Co godzinę sprawdza ją też wtedy, gdy JRT milknie: po 12 godzinach przy pierwszych liniach albo po dwóch dniach bez nowej linii. Powiadomienia możesz dostawać o każdym skanie („Każdy skan”), tylko o ważnych etapach („Ważne etapy”) albo tylko o doręczeniu („Tylko doręczenie”). „Ważne etapy” (dostawa, odbiór, odprawa celna, nieudane doręczenia i zwroty) powiadomią Cię o liniach JRT ze statusem „W urzędzie celnym” i o przepisanych liniach DHL o nieudanym doręczeniu, paczce w punkcie odbioru, doręczeniu i zwrocie, ale nie o lotach ani o `已交服务商`. Peek nie skontaktuje się za Ciebie z JRT ani ze sprzedawcą.

## Pytania o śledzenie JRT

### Co to za firma JRT (今日通)?

To 厦门今日通商贸有限公司, Xiamen Jinritong, firma spedycyjna z Xiamen w Chinach. Jej listy przewozowe to JRT, dziesięć cyfr i MM, a jej śledzenie działa na platformie Hualei (华磊), z której korzystają też inni chińscy spedytorzy.

### Dlaczego JRT pisze „delivered”, choć paczka nie dotarła?

Przez tłumaczenia. `Cargo delivered to airport` znaczy, że JRT przekazał paczkę na lotnisko do lotu, a `Delivered to service provider`, że przekazał ją firmie, która doręcza. Żadna z tych linii nie oznacza doręczenia do Ciebie.

### Kto doręcza paczki JRT w Polsce?

Przy wysyłkach do Europy historia JRT pokazuje DHL, którego skany przepisuje śledzenie JRT; część paczek trafia do FedEx. Poza tym Peek nie wie: zapytaj sprzedawcę, która firma doręcza i pod jakim numerem.

### Do kogo pisać, gdy paczka JRT zaginęła?

Do sprzedawcy, u którego kupowałeś: to on zamówił przesyłkę u JRT. Peek nie zna żadnej strony ani danych kontaktowych JRT poza stroną śledzenia.

:::sources
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – nazwa firmy i inicjały, platforma Hualei, strona śledzenia pod adresem IP po zwykłym HTTP, jej linki i brak opublikowanego linku, kształty numerów JRT…MM i UP, wyszukiwanie po numerze lub numerze referencyjnym z myślnikami, brzmienie linii i ich etapy, angielskie wersje linii chińskich, przepisane linie DHL (także doręczenie, próba doręczenia, odbiór i zwrot) i placówka Deutsche Post, linia FedEx, brak numeru DHL w rekordzie, przekazanie Poczcie Szwajcarskiej, zegar pekiński, odpowiedź na nieznany numer, co Peek pokazuje, pomija i czego nie odczytuje
:::
