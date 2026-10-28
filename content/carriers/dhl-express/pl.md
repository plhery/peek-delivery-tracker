---
title: DHL Express śledzenie przesyłki: numer, statusy i kontakt
description: DHL Express śledzenie przesyłki: gdzie jest 10-cyfrowy numer listu przewozowego, co znaczą statusy, cło, nieudane doręczenie, reklamacja i infolinia w Polsce.
slug: dhl-express-sledzenie-przesylki
published: 2026-10-13
updated: 2026-10-27
---

Wklej w pole powyżej numer listu przewozowego DHL Express albo link do śledzenia z MyDHL+ lub z dhlexpress.pl, a Peek będzie śledzić paczkę. DHL ma też własne śledzenie na swojej stronie i w aplikacji DHL Express. Według dhlexpress.pl odbiorcy indywidualni dostają też od DHL Express e-mail lub SMS z planowanym czasem doręczenia.

## Numer listu przewozowego DHL Express: gdzie go znaleźć

Numer przesyłki DHL Express to numer listu przewozowego (po angielsku waybill number). Szukaj go w e-mailu o wysyłce ze sklepu albo na stronie zamówienia; link do śledzenia z MyDHL+ też go zawiera, po `id=`. Więcej podpowiedzi w poradniku [gdzie znaleźć numer przesyłki](guide:find-tracking-number).

| Numer (wymyślony) | Co to jest |
| --- | --- |
| `1234567891` | List przewozowy: 10 cyfr, numer całej przesyłki. Tym śledzisz. Ostatnia cyfra jest cyfrą kontrolną. |
| `JD014600001234567890` | Identyfikator paczki: `JD01` i 16 cyfr, wydrukowany jako `(J)JD…` pod dolnym kodem kreskowym etykiety. Każda paczka w przesyłce ma własny, a prowadzi on do listu przewozowego. |

Dziesięć cyfr mają też numery Mondial Relay, Relais Colis, TIPSA, Estafety, [Packety](carrier:packeta), DHL eCommerce Iberia i [DHL Paket](carrier:dhl), więc sprawdź, jakiego przewoźnika wymienia e-mail ze sklepu. Według dhlexpress.pl numery DHL Parcel / eCommerce mogą mieć 11 znaków lub więcej i często zaczynają się od „C”, „3S”, „JVGL” albo „HL”; w Peek to osobny przewoźnik, [DHL eCommerce Poland](carrier:dhl-ecommerce-pl), z własnym śledzeniem.

DHL używa numerów listów przewozowych ponownie: pod numerem mogą pojawić się skany wcześniejszej przesyłki, które Peek pomija.

## DHL Express śledzenie przesyłki: co znaczą statusy

To skany, które Peek odczytuje z DHL Express, w angielskim brzmieniu DHL, mniej więcej w kolejności, w jakiej spotyka je paczka, ze statusem, który Peek pokazuje przy każdym. Wiele z nich kończy się nazwą placówki, na przykład `Processed at`, a po nim miasto i kraj.

| Skan DHL Express | Peek pokazuje | Co to znaczy |
| --- | --- | --- |
| `Shipment information received`, `Supplementary information has been received and merged with applicable shipment data` | Zarejestrowana | Nadawca przekazał DHL dane przesyłki. |
| `Shipment picked up`, `Shipment accepted` | Przyjęta przez przewoźnika | DHL Express ma paczkę. |
| `Processed at`, `Arrived at DHL Sort Facility`, `Shipment has departed from a DHL facility`, `Shipment is in transit to destination` | W transporcie | Paczka jedzie przez sieć DHL. |
| `Scheduled to depart on the next planned movement` | W transporcie | Czeka w placówce na najbliższy transport lub lot. |
| `Customs clearance status updated. Note - The Customs clearance process` …, `Processed for clearance at`, `Clearance event` | W urzędzie celnym | Trwa odprawa celna (więcej niżej). |
| `Payment is received and recorded for shipment related fees` | W urzędzie celnym | Cło lub opłaty są zapłacone; paczka czeka w urzędzie celnym do końca odprawy. |
| `Clearance processing complete at` | W transporcie | Odprawa się skończyła i paczka jedzie dalej. |
| `Arrived at DHL Delivery Facility` | W transporcie | Paczka dotarła do placówki DHL, która ją doręczy. |
| `Shipment is out with courier for delivery` | W doręczeniu | Kurier wiezie ją do Ciebie. |
| `Delivery attempted but no response at Consignee address`, `Delivery attempt could not be completed` | Próba doręczenia | Doręczenie się nie udało. „Consignee” to Ty, odbiorca. |
| `Shipment is scheduled for delivery` | W transporcie | Doręczenie zaplanowano na późniejszy kurs, na przykład po nieudanej próbie. |
| `Awaiting collection by the consignee` | Do odbioru | Paczka czeka w placówce DHL, aż ją odbierzesz. |
| `Delivered` | Dostarczona | Paczka została doręczona. |
| `Shipment is on hold` | Zgłoszono problem | DHL na razie wstrzymał przesyłkę. |
| `Shipment arrived at incorrect facility. It will be sent to the correct destination` | Zgłoszono problem | Paczka trafiła do złej placówki i z opóźnieniem jedzie do właściwej. |
| `Returned to shipper` | Zwrócona nadawcy | Paczka wraca do nadawcy. |

## Doręczenie DHL Express: nieudana próba i zmiana dostawy

Po nieudanej próbie śledzenie pokazuje `Delivery attempted but no response at Consignee address` albo `Delivery attempt could not be completed`. Jeśli potem pojawi się `Shipment is scheduled for delivery`, zaplanowano nową próbę; `Awaiting collection by the consignee` znaczy, że paczka czeka na Ciebie w placówce DHL.

W Polsce termin, adres albo sposób doręczenia zmienisz według dhlexpress.pl na infolinii DHL Express (temat 3, „Opcje dostawy”, z numerem przesyłki) albo w serwisie On-Demand Delivery. Paczkę możesz też przekierować do punktu DHL lub automatu paczkowego. W Peek doręczenia nie zmienisz. Więcej w poradnikach [nieudane doręczenie](guide:missed-delivery) i [nieodebrana paczka](guide:uncollected-parcel).

## Cło za paczkę DHL Express spoza UE

Za paczkę spoza UE mogą należeć się opłaty przywozowe. Dopóki trwa odprawa, śledzenie pokazuje `Customs clearance status updated…`, `Processed for clearance at` albo `Clearance event`, po zapłacie `Payment is received and recorded for shipment related fees`, a gdy urząd celny zwolni paczkę, `Clearance processing complete at`.

Według Krajowej Administracji Skarbowej od 1 lipca 2026 r. zakupy internetowe spoza UE nie korzystają już ze zwolnienia z cła dla przesyłek do 150 EUR. Zamiast niego obowiązuje ryczałtowe cło 3 EUR za każdą pozycję towarową w zgłoszeniu celnym. Tego cła nie płaci konsument, tylko sprzedawca, platforma internetowa lub ich przedstawiciel, ale sprzedawca może je doliczyć do ceny lub kosztów dostawy. Unijny portal Your Europe dodaje, że cło dochodzi do VAT i opłat za dostawę, a pobiera je sprzedawca albo przewoźnik.

DHL Express w Polsce pisze na dhlexpress.pl, że opłaty w imporcie to najczęściej cło i VAT, liczone od wartości celnej, czyli wartości towaru razem z kosztami dostarczenia. Jeśli do odprawy brakuje informacji, DHL Express się z Tobą skontaktuje: może poprosić o upoważnienie albo o opis zawartości po polsku (tłumaczenie faktury). Przy przesyłkach do osób prywatnych, co do zasady o wartości do 150 EUR, może działać na podstawie upoważnienia dorozumianego. Należności celne zapłacisz online przez On-Demand Delivery. Więcej w poradniku [paczka zatrzymana przez urząd celny](guide:customs).

## Przesyłka DHL Express opóźniona, wstrzymana, zaginiona lub uszkodzona

Jeśli status stoi w miejscu albo pokazuje `Shipment is on hold`, zajrzyj do poradnika [status przesyłki się nie zmienia](guide:tracking-not-updating). Uszkodzoną przesyłkę według dhlexpress.pl reklamujesz w ciągu 30 dni od doręczenia; potrzebne są zdjęcia uszkodzenia, rachunek i dokumentacja.

Przy zakupach w sklepie to sprzedawca odpowiada za to, żeby paczka do Ciebie dotarła, nawet gdy wiezie ją DHL Express. Według Your Europe sprzedawca odpowiada za uszkodzenia towaru od wysyłki aż do chwili, gdy go otrzymasz. Jeśli nie umówiliście się inaczej, musi dostarczyć towar w ciągu 30 dni. Gdy towaru nie ma, przypomnij sprzedawcy o zamówieniu i wyznacz mu dodatkowy, rozsądny termin. Jeśli i tego nie dotrzyma, możesz rozwiązać umowę i odzyskać pieniądze.

**Peek pokazuje „Dostarczona”, a paczki nie ma?** Strona śledzenia DHL może pokazywać podpis, którego Peek nie zapisuje. Sprawdź to u DHL, a potem skontaktuj się ze sprzedawcą. Więcej w poradnikach [paczka doręczona, a jej nie ma](guide:delivered-not-received) i [zaginiona paczka](guide:lost-parcel).

## DHL Express kontakt: infolinia w Polsce

Tak podaje kanały kontaktu polska strona DHL Express, dhlexpress.pl:

- **Infolinia:** +48 426 345 100; konsultanci od poniedziałku do piątku 7:30–17:00, wirtualny asystent codziennie przez całą dobę. Po połączeniu wybierz temat: 2 to status przesyłki, 3 to opcje dostawy. Przygotuj 10-cyfrowy numer przesyłki.
- **Czat online:** konsultanci od poniedziałku do piątku 8:00–17:00, wirtualny asystent całą dobę.
- **WhatsApp:** +48 732 141 010, konsultanci od poniedziałku do piątku 8:00–17:00, wirtualny asystent całą dobę.

DHL Parcel / eCommerce to inny operator, z własną obsługą klienta.

## Śledzenie DHL Express w Peek

[Peek](/) rozpoznaje 10-cyfrowy numer listu przewozowego, który przechodzi kontrolę cyfry kontrolnej DHL Express. Stawia wtedy DHL Express na pierwszym miejscu, pyta naraz DHL Express i innych przewoźników z 10-cyfrowymi numerami, np. ACS Courier, Geniki Taxydromiki i DHL eCommerce Iberia, i podaje tego, kto zna paczkę. ACS i Geniki używają tej samej cyfry kontrolnej: jeśli któryś z nich też zna numer, wybór należy do ciebie. Numeru, który kontroli nie przechodzi, nie proponuje DHL Express: wybierz go wtedy przez „Zmień przewoźnika”. Numery paczek `JD01` i etykiety `JJD0146` (`JJD014612345678901234`) przypisuje do DHL Express od razu i odnajduje ich list przewozowy. Działają też linki z mydhl.express.dhl. Link z dhl.com trafia pod „DHL”, a nie pod DHL Express, więc wklej sam numer listu przewozowego. Paczkę, która już trafiła pod „DHL”, przestaw na „DHL Express” przez „Zmień przewoźnika”.

Peek odczytuje DHL Express przez usługę, z której korzysta aplikacja mobilna DHL, a gdy to się nie uda, serwer Peek otwiera stronę śledzenia dhl.com w przeglądarce. To trwa dłużej i DHL może odmówić; wtedy historię uzupełniają serwisy śledzenia przesyłek. Peek sprawdza paczkę w dzień co 10 minut, a gdy kurier ma ją w doręczeniu, co 2–5 minut; w nocy co godzinę lub dwie. Może Cię powiadamiać o każdym skanie, tylko o ważnych krokach albo tylko w dniu dostawy.

Widzisz status, każdy skan z placówką na mapie i kraj docelowy. Godzina skanu jest ustawiana według miasta placówki, a gdy to nie wystarcza, według sąsiednich skanów. Skany są po angielsku, jak pisze je DHL, z wyjątkiem `Delivered`, `Shipment picked up` i `Shipment information received`, pokazywanych własnymi słowami Peek. Pomija podpis i stare skany z ponownie użytego numeru, a nie pokazuje planowanej daty doręczenia, wagi ani punktu odbioru. Nie zapłaci cła ani nie zmieni doręczenia.

## Pytania o DHL Express śledzenie

### Jak skontaktować się z konsultantem DHL Express?

Zadzwoń na infolinię +48 426 345 100 i wybierz temat; konsultanci odbierają od poniedziałku do piątku od 7:30 do 17:00, a wirtualny asystent całą dobę. Na czacie i przez WhatsApp (+48 732 141 010) konsultanci piszą od poniedziałku do piątku od 8:00 do 17:00.

### Dlaczego numer listu przewozowego DHL nie działa?

Często to numer innego przewoźnika z grupy DHL: list przewozowy DHL Express ma dokładnie 10 cyfr, a ostatnia jest cyfrą kontrolną, więc literówka zwykle nie przechodzi kontroli. Nowy numer może pokazywać tylko `Shipment information received`, dopóki DHL nie odbierze paczki.

### Czy DHL i DHL Express to to samo?

W Peek nie: DHL Express to osobny przewoźnik, oddzielny od [DHL Paket](carrier:dhl) i od DHL eCommerce Poland, każdy z własnymi numerami i śledzeniem. Paczka bez 10-cyfrowego listu przewozowego ani numeru `JD01` należy zwykle do któregoś z nich.

### Czy da się śledzić DHL Express bez numeru listu przewozowego?

W Peek nie: potrzebny jest 10-cyfrowy list przewozowy albo numer `JD01` z etykiety. Poproś sklep lub nadawcę o numer listu przewozowego.

### Czy SMS od DHL z prośbą o dopłatę jest prawdziwy?

Może to być oszustwo. CSIRT NASK ostrzegał przed SMS-ami, w których przestępcy podszywają się pod firmy kurierskie: piszą o problemie z doręczeniem, a link prowadzi do fałszywej strony, gdzie pod pretekstem dopłaty wyłudzają dane karty. Nie klikaj linku: numer przesyłki wpisz sam na stronie DHL albo w Peek. Podejrzany SMS przekaż w całości, bez wycinania linku, na bezpłatny numer 8080. Jeśli dane karty trafiły już na fałszywą stronę, od razu zadzwoń do banku. Więcej w poradniku [oszustwo na paczkę](guide:delivery-scam-texts).

:::sources
- [DHL Express Polska: Kontakt](https://dhlexpress.pl/kontakt/) – 10-cyfrowy numer przesyłki, numery DHL Parcel / eCommerce, infolinia, tematy 2 i 3, godziny, czat, WhatsApp, reklamacja w ciągu 30 dni od doręczenia, powiadomienia SMS/e-mail
- [DHL Express Polska: Odbiór przesyłek](https://dhlexpress.pl/indywidualny/odbior-przesylek/) – powiadomienia e-mail/SMS z planowanym czasem doręczenia, przekierowanie, punkty i automaty, płatność należności celnych przez On-Demand Delivery
- [DHL Express Polska: Import – osoby fizyczne](https://dhlexpress.pl/obsluga-i-proces-celny/import-osoby-fizyczne/) – odprawa spoza UE, kontakt przy brakach, upoważnienie, tłumaczenie faktury, upoważnienie dorozumiane do 150 EUR, cło i VAT od wartości celnej
- [Krajowa Administracja Skarbowa: Zmiany w e-commerce od 1 lipca 2026 r.](https://www.gov.pl/web/kas/zmiany-w-e-commerce-od-1-lipca-2026-r) – koniec zwolnienia do 150 EUR, 3 EUR za pozycję towarową, cła nie płaci konsument
- [Your Europe: Wysyłka i dostawa](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_pl.htm) – cło pobiera sprzedawca lub przewoźnik, 30 dni, dodatkowy termin, rozwiązanie umowy, odpowiedzialność sprzedawcy za uszkodzenia
- [CSIRT NASK: oszustwo na „przesyłkę”](https://www.gov.pl/web/baza-wiedzy/uwaga-csirt-nask-ostrzega-w-okresie-przedswiatecznej-goraczki-zakupowej-wraca-popularne-oszustwo-na-przesylke) – fałszywe SMS-y o przesyłce, dopłata, numer 8080, bank
- [Universal Parcel Scraper: DHL Express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dhl-express) – kształty listu przewozowego i identyfikatora paczki, cyfra kontrolna, inne 10-cyfrowe numery, ponownie używane numery, skany DHL Express i status Peek dla każdego, podpis na stronie DHL, linki z dhlexpress.pl, co Peek odczytuje z DHL Express
:::
