---
title: FedEx śledzenie: numer przesyłki, statusy i cło
description: Śledzenie przesyłki FedEx: jak wygląda 12-cyfrowy numer, co znaczą „Delivery exception” i inne skany, cło za paczki spoza UE i Twoje prawa, gdy paczka zaginie.
slug: fedex-sledzenie
published: 2026-10-13
updated: 2026-10-28
---

Żeby śledzić paczkę, wklej numer przesyłki FedEx albo link do śledzenia FedEx w pole powyżej. Własne śledzenie FedEx działa na fedex.com i w aplikacji FedEx Mobile.

## FedEx śledzenie przesyłki: gdzie jest numer i ile ma cyfr

Szukaj w e-mailu sklepu o wysyłce albo na stronie zamówienia. Numer zawiera też link do śledzenia z fedex.com, po `trknbr=` albo `tracknumbers=`. Nadal nic? Więcej w poradniku [gdzie znaleźć numer przesyłki](guide:find-tracking-number).

| Numer (wymyślony) | Co to jest |
| --- | --- |
| `123456789012` | 12 cyfr: numer przesyłki FedEx. Ostatnia cyfra to cyfra kontrolna wyliczana z 11 poprzednich. |
| `6123456789016` | 13 cyfr, pierwsza to `6`: krajowe przesyłki [FedEx w Polsce](carrier:fedex-pl). |
| `123456789012343` | 15 cyfr: kształt, który Peek też przypisuje FedEx. |
| `61290000000000000014` | 20 cyfr zaczynających się od `6129` lub `7489`: FedEx Ground Economy (dawniej SmartPost) w USA. |

Dwanaście cyfr mają też numery DPD France, Mondial Relay, BRT i kilku azjatyckich firm, a piętnaście DPD France, BRT i Yunda. Numer zaczynający się od `1Z` należy do [UPS](carrier:ups), a 10 cyfr to zwykle list przewozowy [DHL Express](carrier:dhl-express). Jeśli fedex.com nie zna numeru, sprawdź w e-mailu od sklepu, jakiego przewoźnika podaje.

## Statusy FedEx: co znaczy „Delivery exception”

Peek odczytuje skany FedEx po angielsku, tak jak zapisuje je FedEx, a nad nimi pokazuje etap po polsku. Oto najważniejsze, mniej więcej w kolejności, w jakiej spotyka je paczka:

| FedEx pisze | Peek pokazuje | Co to znaczy dla Ciebie |
| --- | --- | --- |
| `Shipment information sent to FedEx` | Zarejestrowana | Nadawca przekazał FedEx dane przesyłki. |
| `Picked up`, `Tendered at FedEx location` | Przyjęta przez przewoźnika | FedEx ma paczkę: odebrał ją kurier albo nadawca oddał ją w placówce FedEx. |
| `Arrived at FedEx location`, `Departed FedEx location`, `At local FedEx facility`, `On the way` | W transporcie | Jedzie przez sieć FedEx. |
| `Clearance in progress`, `Clearance delay` | W urzędzie celnym | Trwa odprawa celna (więcej niżej). |
| `International shipment release - Import` | W transporcie | Urząd celny zwolnił paczkę w kraju docelowym. |
| `On FedEx vehicle for delivery` | W doręczeniu | Jest w samochodzie kuriera. |
| `Delivery exception`, `Customer not available or business closed` | Próba doręczenia | Doręczenie się nie udało. |
| `Ready for pickup` | Do odbioru | Czeka na Ciebie w punkcie odbioru. |
| `Delivered` | Dostarczona | Paczka została doręczona. |
| `Shipment exception` | Zgłoszono problem | FedEx zgłasza problem z przesyłką. |
| `Returning to shipper` | Zwrócona nadawcy | Wraca do nadawcy. |

Gdy FedEx oznaczy paczkę jako opóźnioną, Peek pokazuje „Zgłoszono problem”, chyba że ostatni skan to `Package received after FedEx cutoff`: FedEx przyjął paczkę po godzinie granicznej danego dnia, więc wyruszy w następnym dniu roboczym.

## Kurier FedEx Cię nie zastał: nieudane doręczenie i punkty odbioru

Gdy doręczenie się nie uda, skan może podać powód: `Customer not available or business closed`, gdy nikogo nie było, albo `Local delivery restriction - Delivery not attempted`, gdy lokalne ograniczenie nie pozwoliło na próbę doręczenia.

Jeśli poprosisz FedEx o zatrzymanie paczki w punkcie odbioru, w drodze skan może brzmieć `Delivery option requested`, a po dotarciu na miejsce `Ready for pickup`. Peek pokazuje wtedy nazwę i adres punktu, jeśli FedEx je podaje: gdy o zatrzymanie poproszono już po nadaniu, strona FedEx pokazuje niezalogowanym tylko miejscowość. Strona FedEx może nazwać ostatni odcinek `Heading to pickup location`, a Peek liczy go jako „W doręczeniu”. Takie zmiany ustalasz z FedEx, nie w Peek. Więcej w poradnikach [awizo i nieudana próba doręczenia](guide:missed-delivery) i [nieodebrana paczka](guide:uncollected-parcel).

## Cło za paczkę FedEx spoza UE

Za paczkę spoza UE mogą należeć się opłaty przywozowe. Dopóki trwa odprawa, śledzenie pokazuje `Clearance in progress` albo `Clearance delay`, a gdy urząd celny zwolni paczkę, `International shipment release - Import`.

Według Krajowej Administracji Skarbowej od 1 lipca 2026 r. zakupy internetowe spoza UE nie korzystają już ze zwolnienia z cła dla przesyłek do 150 EUR. Zamiast niego obowiązuje ryczałtowe cło 3 EUR za każdą pozycję towarową w zgłoszeniu celnym. Tego cła nie płaci konsument, tylko sprzedawca, platforma internetowa lub ich przedstawiciel, ale sprzedawca może je doliczyć do ceny lub kosztów dostawy. Unijny portal Your Europe dodaje, że cło dochodzi do VAT i opłat za dostawę, a pobiera je sprzedawca albo przewoźnik. Więcej w poradniku [paczka zatrzymana przez urząd celny](guide:customs).

## Przesyłka FedEx opóźniona, zaginiona lub doręczona, a jej nie ma

Jeśli status stoi w miejscu, zajrzyj do poradnika [status przesyłki się nie zmienia](guide:tracking-not-updating).

Przy zakupach w sklepie to sprzedawca odpowiada za to, żeby paczka do Ciebie dotarła, nawet gdy wiezie ją FedEx. Według Your Europe sprzedawca odpowiada za uszkodzenia towaru od wysyłki aż do chwili, gdy go otrzymasz. Jeśli nie umówiliście się inaczej, musi dostarczyć towar w ciągu 30 dni. Gdy towaru nie ma, przypomnij sprzedawcy o zamówieniu i wyznacz mu dodatkowy, rozsądny termin. Jeśli i tego nie dotrzyma, możesz rozwiązać umowę i odzyskać pieniądze.

**Peek pokazuje „Dostarczona”, a paczki nie ma?** Skan doręczenia FedEx podaje, kto pokwitował odbiór, ale Peek tego nie zapisuje. Sprawdź to na fedex.com, a potem skontaktuj się ze sprzedawcą. Więcej w poradnikach [paczka doręczona, a jej nie ma](guide:delivered-not-received) i [zaginiona paczka](guide:lost-parcel).

## Śledzenie przesyłki FedEx w Peek

[Peek](/) stawia FedEx na pierwszym miejscu przy 12- i 15-cyfrowych numerach, których cyfra kontrolna się zgadza, ale nie ma pewności: te same kształty mają inni przewoźnicy. Stronę FedEx otwiera w przeglądarce dopiero na końcu, więc przewoźnika często wskazuje serwis śledzenia przesyłek albo Peek podaje go po krótkiej chwili. Numeru, który nie przechodzi kontroli, Peek nie proponuje FedEx: wybierz wtedy przewoźnika przez „Zmień przewoźnika”. Ważne w Polsce: krajowe 13-cyfrowe numery FedEx Peek śledzi osobno, jako FedEx Poland. Możesz wkleić link z fedex.com z `trknbr=` albo `tracknumbers=`, także z polskiej strony śledzenia. Zeskanowany kod z etykiety jest odczytywany jako numer, który w sobie ma.

Serwer Peek otwiera w dzień stronę śledzenia fedex.com w przeglądarce co 10 minut, a co 5 minut, gdy paczka jest w doręczeniu; w nocy co godzinę lub dwie. FedEx często odmawia takich wizyt. Gdy odmówi albo zażąda weryfikacji odbiorcy, historię uzupełniają serwisy śledzenia przesyłek.

Widzisz status, każdy skan z miejscem i dokładną godziną na mapie, przewidywany dzień doręczenia, godzinę doręczenia i, dopóki paczka czeka, nazwę i adres placówki FedEx, jeśli FedEx je podaje. Powiadomienia możesz dostawać o każdym skanie, tylko o ważnych etapach albo tylko w dniu dostawy. Nie ma tam Twojego imienia i adresu, nadawcy, podpisu, wagi ani wymiarów. Skany są po angielsku, tak jak zapisuje je FedEx (`On FedEx vehicle for delivery` to wydanie do doręczenia). Skan doręczenia, odbiór w placówce i wyjątek w przesyłce (`Delivered`, `Ready for pickup`, `Shipment exception`) Peek pokazuje własnymi słowami: „Dostarczona”, „Do odbioru” i „Problem z przesyłką”. Zmianę doręczenia, cło i reklamację załatwisz tylko w FedEx albo u nadawcy.

## Pytania o FedEx śledzenie

### Co znaczy „delivery exception” w FedEx?

Peek zalicza ten skan FedEx do nieudanych prób doręczenia i pokazuje „Próba doręczenia”. Powód może podać skan taki jak `Customer not available or business closed` (nikogo nie zastano albo firma była zamknięta).

### Dlaczego śledzenie FedEx pokazuje tylko „Shipment information sent to FedEx”?

Ten pierwszy skan znaczy tylko, że nadawca przekazał FedEx dane przesyłki. Śledzenie ruszy, gdy FedEx będzie miał paczkę: `Picked up` albo `Tendered at FedEx location`. Jeśli nadawca oddał ją po godzinie granicznej, skan brzmi `Package received after FedEx cutoff`, a paczka wyrusza w następnym dniu roboczym.

### Czy SMS od FedEx z prośbą o dopłatę jest prawdziwy?

Może to być oszustwo. CERT Polska (CSIRT NASK) ostrzegał przed SMS-ami, w których przestępcy podszywają się pod firmy kurierskie: piszą o problemie z doręczeniem, a link prowadzi do fałszywej strony, gdzie pod pretekstem dopłaty wyłudzają dane karty. Nie klikaj linku: numer przesyłki wpisz sam na fedex.com albo w Peek, a w razie wątpliwości skontaktuj się z firmą przez numer lub adres z jej oficjalnej strony, nie z SMS-a. Podejrzany SMS przekaż w całości, bez wycinania linku, na bezpłatny numer 8080. Jeśli dane karty trafiły już na fałszywą stronę, od razu zadzwoń do banku. Więcej w poradniku [oszustwo na paczkę](guide:delivery-scam-texts).

:::sources
- [Krajowa Administracja Skarbowa: Zmiany w e-commerce od 1 lipca 2026 r.](https://www.gov.pl/web/kas/zmiany-w-e-commerce-od-1-lipca-2026-r) – koniec zwolnienia do 150 EUR, 3 EUR za pozycję towarową, cła nie płaci konsument
- [Your Europe: Wysyłka i dostawa](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_pl.htm) – cło pobiera sprzedawca lub przewoźnik, 30 dni, dodatkowy termin, rozwiązanie umowy, odpowiedzialność sprzedawcy za uszkodzenia
- [CSIRT NASK: oszustwo na „przesyłkę”](https://www.gov.pl/web/baza-wiedzy/uwaga-csirt-nask-ostrzega-w-okresie-przedswiatecznej-goraczki-zakupowej-wraca-popularne-oszustwo-na-przesylke) – fałszywe SMS-y o przesyłce, dopłata, numer 8080, bank
- [Universal Parcel Scraper: FedEx](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/fedex) – kształty numerów, cyfra kontrolna, skany FedEx, co Peek odczytuje z FedEx
- [Universal Parcel Scraper: FedEx Poland](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/fedex-pl) – 13-cyfrowe numery krajowe zaczynające się od 6
:::
