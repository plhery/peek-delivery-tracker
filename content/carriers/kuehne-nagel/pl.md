---
title: Śledzenie Kuehne+Nagel: numery, statusy i doręczenia
description: Śledź przesyłki drogowe, lotnicze i morskie Kuehne+Nagel po numerze referencyjnym lub linku myKN: formaty kodów, etapy, odprawa celna i kontakt.
slug: sledzenie-kuehne-nagel
published: 2026-11-24
updated: 2026-11-24
---

Kuehne+Nagel to jeden z czołowych światowych operatorów logistycznych, zatrudniający blisko 92 000 osób w prawie 1 300 lokalizacjach w niemal 100 krajach. Obsługuje fracht morski, lotniczy i drogowy, a jego [publiczna wyszukiwarka myKN](https://mykn.kuehne-nagel.com/public-tracking/) pozwala sprawdzać przesyłki każdego rodzaju.

Publiczne śledzenie w myKN obejmuje przesyłki rozpoczęte w ciągu ostatnich 90 dni. Ponieważ Kuehne+Nagel przewozi ładunki przez terminale drogowe, lotniska, magazyny konsolidacji kontenerów (CFS), terminale kolejowe i porty morskie, jedna przesyłka może wyświetlać zarówno fizyczne etapy trasy, jak i osobne powiadomienia o rezerwacjach, cle czy dokumentach.

## Formaty numerów śledzenia Kuehne+Nagel

Peek przyjmuje referencje Kuehne+Nagel złożone z 6 do 35 liter i cyfr (z co najmniej jedną cyfrą), usuwając spacje i myślniki. myKN wyszukuje własne numery śledzenia Kuehne+Nagel, listy przewozowe i rezerwacje w tym samym polu co numery referencyjne klienta. Peek rozpoznaje trzy wzorce:

- **`RWM` i 9 cyfr** (łącznie 12 znaków, np. `RWM482917360`) — numer przesyłki drogowej Kuehne+Nagel, który Peek rozpoznaje jako należący wyłącznie do Kuehne+Nagel.
- **`RAPI` i 8 cyfr** (łącznie 12 znaków, np. `RAPI48291736`) — numer śledzenia Kuehne+Nagel, również rozpoznawany jako wyłączny.
- **2 cyfry, `A` i 9 cyfr** (łącznie 12 znaków, np. `26A482917360`) — numer przesyłki drogowej. Ten schemat pasuje również do [Australia Post](carrier:australia-post) i [Colis Privé](carrier:colis-prive), dlatego Peek pyta kolejno Australia Post, Colis Privé i Kuehne+Nagel (w Szwajcarii i Niemczech Kuehne+Nagel jako drugi).

W transporcie lotniczym i morskim nadawcy mogą przekazywać także list przewozowy Kuehne+Nagel, konosament spedytorski (*house bill of lading*) lub numer referencyjny klienta poza tymi trzema wzorcami. Po wklejeniu linku `mykn.kuehne-nagel.com/public-tracking/shipments?query=...` Peek wyciąga referencję i kieruje ją bezpośrednio do Kuehne+Nagel. Szersze zestawienie numerów znajdziesz w [przewodniku po formatach numerów przesyłek](guide:tracking-number-formats).

## Jak działają etapy w Kuehne+Nagel

Publiczny tracker Kuehne+Nagel dzieli przesyłkę na dwa strumienie: fizyczne punkty na trasie (nadanie, terminal wyjściowy, hub tranzytowy, brama, terminal docelowy i adres doręczenia) oraz komunikaty transportowe (rezerwacje, zgłoszenia celne i dokumenty potwierdzenia doręczenia).

| Komunikat Kuehne+Nagel | Co oznacza |
| --- | --- |
| Booking submission / Booking confirmation | Zlecenie zostało zarejestrowane w systemie Kuehne+Nagel przed ruszeniem ładunku; późna zmiana rezerwacji nigdy nie cofa przesyłki będącej już w drodze. |
| Ready for pickup / Arrival (at origin) | Ładunek jest gotowy u nadawcy lub samochód odbiorczy przyjechał przed potwierdzeniem załadunku. |
| Pickup | Kuehne+Nagel odebrał ładunek od nadawcy. |
| Arrival / Departure / Load on vehicle / Load on vessel | Fizyczne skany w terminalu, magazynie przeładunkowym, na rampie kolejowej, lotnisku lub w porcie w trakcie głównego przewozu. |
| Customs entry start / Customs duties payment / Entry documents required | Trwa odprawa celna eksportowa lub importowa albo organy zażądały dokumentów, opłat celnych czy kontroli agencji (np. FDA lub PGA). |
| Customs entry release / Export customs entry release / Import customs entry release | Urząd celny lub właściwa służba graniczna zwolniła ładunek do dalszej drogi. |
| In delivery | Pojazd drogowy wyjechał na ostatni etap doręczenia lub dotarł pod adres dostawy. |
| Delivery / Freight collection / Proof of delivery available | Ładunek doręczono odbiorcy lub odebrano w terminalu; `Delivery with discrepancies` nadal oznacza doręczenie z adnotacją o braku lub uszkodzeniu. |
| Delivery attempt failed / Delivery not possible | Próba doręczenia nie powiodła się, np. z powodu odmowy odbiorcy. |
| Shipment on hold / Delivery instructions pending / Missing/incomplete document | Przesyłka jest wstrzymana w terminalu lub w urzędzie celnym do czasu dostarczenia brakujących dokumentów, płatności lub instrukcji doręczenia. |

Kilka komunikatów wymaga bliższego wyjaśnienia:

- **Available at CY / Available at CFS / Ready for collection / Available at rail terminal** oznaczają przybycie ładunku na plac kontenerowy, do magazynu CFS, na lotnisko lub terminal kolejowy i gotowość do przekazania lub podjęcia. Kuehne+Nagel albo partner nadal mogą wykonać końcowy przewóz drogowy, więc te skany pozostają w transporcie, a nie kończą doręczenia.
- **Freight handover** oznacza przekazanie ładunku agencji celnej lub kolejnemu przewoźnikowi, nie odbiorcy końcowemu.
- **Komunikaty dokumentowe i zgłoszeniowe**, takie jak `HAWB available`, `MAWB available`, `Transport document available`, `VGM closure`, `Delivery appointment submission` i `Customs billing invoice available`, dokumentują czynności administracyjne i nie zmieniają fizycznego etapu przesyłki.
- **Empty container gate-in i odjazd pojazdu w miejscu docelowym** następują już po doręczeniu i pozostają na etapie dostarczonej przesyłki.

## Doręczenie, cło i opóźnienia

`Delivery appointment submission` zapisuje uzgodniony późniejszy termin dostawy. Nie jest to skan wyjścia do doręczenia: przesyłka przechodzi na etap doręczenia dopiero przy `In delivery` albo przy zdarzeniu `Arrival` ciężarówki pod adresem doręczenia.

Jeśli śledzenie pokazuje `Awaiting consignee's feedback`, `Awaiting consignee's payment` lub `Entry documents required`, urząd celny czeka na odpowiedź, płatność albo dokumenty od odbiorcy. `Unable to file customs entry - missing details or documentation` oznacza, że zgłoszenia celnego nie da się złożyć do momentu ich otrzymania. Zobacz [przewodnik celny](guide:customs), aby sprawdzić zasady odprawy i opłat importowych.

Planowane daty widoczne w myKN to prognozy, a nie wykonane skany. Ogólne wskazówki przy zatrzymanym śledzeniu znajdziesz w [gdy śledzenie przestaje się aktualizować](guide:tracking-not-updating).

## Śledzenie Kuehne+Nagel w Peek

Gdy wkleisz numer `RWM` lub `RAPI` albo link `mykn.kuehne-nagel.com/public-tracking/shipments?query=...` do Peek, Peek od razu rozpoznaje Kuehne+Nagel. Dla numeru `26A...` Peek odpytuje kolejno powyższych przewoźników i po odpowiedzi wyświetla „Kuehne+Nagel ma tę przesyłkę.” Dla listu przewozowego lub referencji klienta spoza tych wzorców wybierz Kuehne+Nagel przez **Zmień** podczas dodawania przesyłki lub **Zmień przewoźnika** na ekranie śledzenia.

Peek odczytuje bezpośrednio publiczną wyszukiwarkę przesyłek, etapy trasy oraz komunikaty transportowe Kuehne+Nagel przez otwartoźródłowy [adapter Kuehne+Nagel w universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/kuehne-nagel). Zapisuje wyłącznie osiągnięte etapy (pomijając planowane prognozy) i pobiera łączną wagę w kilogramach oraz kraj docelowy, gdy są podane. Gdy wyszukiwarka znajdzie przesyłkę po referencji klienta, Peek podaje własny numer śledzenia Kuehne+Nagel obok Twojego jako „Kuehne+Nagel śledzi przesyłkę pod numerem”; jeśli referencja pasuje do kilku przesyłek, Peek nie rozpozna, która jest Twoja. Gdy system skanujący podaje przesunięcie UTC, Peek zachowuje ten dokładny moment; w przeciwnym razie przypisuje skan do strefy czasowej miejsca (czas berliński dla Hamburga, zuryski dla Bazylei), skan bez miejsca do strefy sąsiedniego skanu, a w ostateczności do czasu Zurychu, wyświetlając wszystko według zegara Twojego urządzenia.

Peek przekłada etapy Kuehne+Nagel na przejrzyste statusy: **Zarejestrowana** dla rezerwacji i gotowości do odbioru, **Przyjęta przez przewoźnika** dla odbioru, **W transporcie** dla hubów, portów, kolei i przekazań, **W urzędzie celnym** dla odpraw celnych i wstrzymań organów, **W doręczeniu** dla `In delivery` i przyjazdu ciężarówki do celu, **Próba doręczenia** dla nieudanych doręczeń, **Zgłoszono problem** dla wstrzymań, nieudanych odbiorów, anulowań i brakujących dokumentów oraz **Dostarczona** dla doręczenia, odbioru ładunku lub potwierdzenia dostawy. Wpisy administracyjne zostają w historii bez cofania bieżącego etapu, a zgłoszenie celne po dostawie nie cofa statusu doręczonej przesyłki.

Gdy masz przesyłkę otwartą w Peek albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza status nawet co 10 minut; w dniu, gdy Kuehne+Nagel pokazuje `In delivery` albo `Arrival` ciężarówki pod adresem doręczenia, co 2 minuty od godz. 6:00 czasu najnowszego skanu, dopóki tracker Kuehne+Nagel odpowiada. W pozostałym czasie Peek sprawdza co godzinę. Po 12 godzinach wyłącznie na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika” albo po dwóch dniach bez zmian sprawdza co godzinę niezależnie od sposobu śledzenia, a po siedmiu dniach na „Zarejestrowana” — co 6 godzin. Gdy Kuehne+Nagel nie odpowiada i Peek śledzi przesyłkę wyłącznie przez usługi śledzące, sprawdza co godzinę, co 3 godziny po spokojnym dniu i nawet co 15 minut w dniu doręczenia, dopóki masz ją otwartą lub włączone powiadomienia.

Jeśli najnowszy skan ma cztery dni, a przesyłka nadal widnieje jako „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie”, Peek oznacza ją dniem tego skanu, np. „Brak aktualizacji od pon., 12 paź”. Na stronie przesyłki pojawia się wtedy komunikat „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika Kuehne+Nagel.” Po trzech tygodniach bez nowego skanu pojawia się informacja „Od ponad trzech tygodni nic nowego. Jeśli przesyłka wciąż nie dotarła, zapytaj sprzedawcę. Peek dalej pyta przewoźnika Kuehne+Nagel.”

W ustawieniach przesyłki możesz wybrać powiadomienia dla opcji **Każdy skan**, **Ważne etapy** lub **Tylko doręczenie**.

## Kontakt z Kuehne+Nagel

Strona Kuehne+Nagel udostępnia wyszukiwarkę oddziałów oraz formularz kontaktowy:

- **Globalna strona i portal śledzenia:** [kuehne-nagel.com](https://www.kuehne-nagel.com/) oraz [myKN Public Tracking](https://mykn.kuehne-nagel.com/public-tracking/)
- **Katalog lokalnych biur:** [Kuehne+Nagel locations worldwide](https://www.kuehne-nagel.com/company/locations), aby znaleźć najbliższy oddział krajowy lub lokalny
- **Formularz zapytań online:** [Talk to an expert](https://www.kuehne-nagel.com/contact/talk-to-an-expert), aby otrzymać odpowiedź e-mailem
- **Inne sieci frachtowe:** jeśli Twoje zamówienie zostało rozdzielone między przewoźników, porównaj [DHL Freight](carrier:dhl-freight) oraz [DSV](carrier:dsv).

## Najczęstsze pytania

### Dlaczego mój numer śledzenia Kuehne+Nagel nie zwraca żadnych wyników?

Publiczny tracker Kuehne+Nagel obejmuje wyłącznie przesyłki rozpoczęte w ciągu ostatnich 90 dni. Sprawdź, czy nie brakuje żadnego znaku, a przy świeżej rezerwacji poczekaj na pierwszy faktyczny skan. Jeśli referencja pasuje do kilku przesyłek naraz, poproś nadawcę o własny numer `RWM`, `RAPI` lub inny identyfikator Kuehne+Nagel.

### Co oznaczają „Service complete” lub „Freight handover” w Kuehne+Nagel?

`Freight handover` oznacza, że Kuehne+Nagel przekazał ładunek agencji celnej lub kolejnemu przewoźnikowi, więc przesyłka nadal jest w drodze do Ciebie. `Service complete` zamyka zakontraktowany odcinek Kuehne+Nagel — który może kończyć się przekazaniem w porcie lub na lotnisku zamiast dostawy pod drzwi — dlatego sprawdź, czy nadano osobny numer ostatniej mili.

### Dlaczego części dat z myKN nie ma w historii skanów w Peek?

Publiczny widok trasy Kuehne+Nagel pokazuje planowane daty prognoz obok wykonanych skanów oraz przyszłe komunikaty bez daty. Peek zapisuje wyłącznie etapy oznaczone przez Kuehne+Nagel jako faktycznie osiągnięte, aby planowane terminy nie wyglądały na ukończone ruchy.

### Jak długo działa publiczne śledzenie Kuehne+Nagel?

Publiczne śledzenie na `mykn.kuehne-nagel.com` obejmuje przesyłki rozpoczęte w ciągu ostatnich 90 dni. Aby zobaczyć dłuższą historię, myKN oferuje bezpłatną rejestrację.

:::sources
- [Globalna strona Kuehne+Nagel](https://www.kuehne-nagel.com/)
- [myKN Public Tracking](https://mykn.kuehne-nagel.com/public-tracking/)
- [Lokalizacje Kuehne+Nagel na świecie](https://www.kuehne-nagel.com/company/locations)
- [Kuehne+Nagel Talk to an expert](https://www.kuehne-nagel.com/contact/talk-to-an-expert)
- [adapter Kuehne+Nagel w universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/kuehne-nagel)
:::
