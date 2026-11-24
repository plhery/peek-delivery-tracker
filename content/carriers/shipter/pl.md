---
title: Śledzenie Shipter: koreańskie numery H3 i statusy
description: Śledź międzynarodowe paczki lotnicze Shipter (H3 Networks) z Korei Południowej: numery H3...KR, przekazania lokalne, statusy i linki śledzenia.
slug: sledzenie-shipter
published: 2026-11-24
updated: 2026-11-24
---

Shipter, prowadzony przez **H3 NETWORKS Co., Ltd.** w Korei Południowej, wysyła paczki B2B i B2C z Korei do ponad **100 krajów** przez ponad 40 kanałów logistycznych w sieci lotniczej łączącej co tydzień przeszło 25 lotnisk ([shipter.kr](https://www.shipter.kr/)). Usługa B2B obejmuje transport, odprawę celną i magazynowanie, a B2C — doręczenia zagraniczne, odprawę celną i ostatnią milę. Amazon Korea wybrał firmę jako oficjalnego partnera logistycznego w swojej sieci SPN (*Service Provider Network*); Shipter jest zarejestrowany jako przewoźnik w eBay, Kogan i Allegro, jego śledzenie współpracuje z AfterShip i 17TRACK, a dodatkowo obsługuje wysyłki dla twórców i influencerów.

Przesyłkę sprawdzisz na [shipter.kr/tracking](https://www.shipter.kr/tracking) lub wklejając numer `H3...KR` w pole powyżej.

## Format numeru śledzenia Shipter

Numery śledzenia Shipter zaczynają się od `H3` i kończą na `KR`, mając w środku dwie litery oraz pięć lub sześć cyfr:

| Format (wymyślony) | Co oznacza |
| --- | --- |
| `H3AU482917KR` | 12 znaków: `H3`, dwie litery, sześć cyfr i `KR`. Peek pyta wyłącznie Shipter. |
| `H3FR48291KR` | 11 znaków: `H3`, dwie litery, pięć cyfr i `KR`. Peek pyta wyłącznie Shipter. |

Link `shipter.kr/tracking/<numer>` lub `shiptertracking.com/trackingResult/<numer>` od razu przypisuje przesyłkę do Shipter. Dla paczek wysyłanych z Korei Południowej przez Korea Post zobacz [Korea Post](carrier:korea-post); inne formaty znajdziesz w poradniku o [formatach numerów śledzenia](guide:tracking-number-formats).

## Co oznaczają statusy śledzenia Shipter

Historia śledzenia Shipter łączy własne etapy eksportu w Korei Południowej i międzynarodowego frachtu lotniczego ze skanami przekazywanymi przez lokalnego kuriera ostatniej mili w kraju docelowym, w języku angielskim.

| Komunikat Shipter / partnera | Co oznacza |
| --- | --- |
| `The shipment information has been received by shipter, but the package has not been picked up yet.` | Otrzymano dane od koreańskiego sprzedawcy; Shipter nie zeskanował jeszcze fizycznej paczki. |
| `The parcel has been scanned by shipter and ready to be shipped.` | Shipter przyjął i zeskanował paczkę w swoim koreańskim centrum. |
| `The parcel has been shipped.` / `The flight has been departed.` / `Depart origin airport` | Wysłana z Korei Południowej międzynarodowym lotem cargo. |
| `Arrived at destination airport` / `Collected from airport terminal` | Wylądowała w kraju docelowym i została odebrana z lotniskowego terminalu cargo. |
| `Import customs cleared` | Urząd celny kraju docelowego zwolnił paczkę do dalszego doręczenia lokalnego. |
| `Arrived at destination` / `In transit` | Trafiła do docelowego obiegu sortowania przed skanami lokalnego kuriera. |
| `Parcel received in processing centre (...)` / `Parcel depart from ... depot` / `The item is ready for shipment...` | Przetworzona i wysłana dalej w sieci lokalnego przewoźnika w kraju docelowym. |
| `Your parcel has been received at ... depot..` / `Your parcel is currently in transit to ...` | Przekazane od lokalnego partnera w kraju docelowym wraz z nazwą oddziału i miasta. |
| `Out for delivery` / `Your parcel is with your local courier and ready for delivery..` | Dzisiaj w drodze do odbiorcy z lokalnym kurierem. |
| `Your parcel was delivered` / `Delivered.` | Doręczona pod adres odbiorcy. |

Dwa komunikaty przekazywane od partnera ostatniej mili, `Your parcel will be entrusted to last mile .it is being prepared by your sender` oraz `Shipping information received`, pojawiają się już po skanie przyjęcia przez Shipter. Nie oznaczają, że przesyłka cofnęła się w drodze.

## Międzynarodowy transport lotniczy, cło i lokalne przekazanie

Shipter wysyła paczki drogą lotniczą z Korei Południowej, po czym przekazuje je lokalnemu kurierowi w kraju docelowym:

- **Lot i lotnisko:** po `Depart origin airport` Shipter pokazuje `Arrived at destination airport` po lądowaniu, `Collected from airport terminal` po opuszczeniu terminalu cargo oraz `Import customs cleared` po zakończeniu odprawy importowej. Zobacz [poradnik celny](guide:customs) i [brak aktualizacji śledzenia](guide:tracking-not-updating).
- **Lokalne doręczenie ostatniej mili:** końcową dostawę realizuje przewoźnik w kraju docelowym, a Shipter wyświetla jego skany oddziałowe, `Out for delivery` i doręczenie na własnej osi czasu.
- **Brak szacowanej daty doręczenia:** publiczne API śledzenia Shipter nie publikuje przewidywanej daty dostawy; postęp najlepiej śledzić po statusach `Import customs cleared`, przyjęciu w lokalnym oddziale oraz `Out for delivery`.

## Śledzenie Shipter w Peek

Wklej w Peek swój numer `H3...KR` albo link `shipter.kr/tracking/...` lub `shiptertracking.com/trackingResult/...`. Dla numeru 11- lub 12-znakowego Peek pyta wyłącznie Shipter. Gdy Shipter odpowie, pojawia się „Shipter ma tę przesyłkę.” Jeśli Peek wskaże innego przewoźnika, kliknij „Zmień” przy dodawaniu paczki lub „Zmień przewoźnika” na jej ekranie i wybierz Shipter.

Peek pobiera dane Shipter przez otwarty [adapter Shipter z universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/shipter) i ponawia próbę po błędzie sieciowym. Wyświetla angielski tekst każdego skanu z podanym oddziałem lub miejscem oraz godzinę doręczenia. Shipter podaje czasy z przesunięciem GMT, a Peek wyświetla je w czasie Twojego urządzenia; czas bez przesunięcia wyświetlany jest tak, jak podaje go Shipter.

Peek przekłada skany Shipter na przejrzyste etapy: „Zarejestrowana” dla danych przed odbiorem, „Przyjęta przez przewoźnika” po skanie Shipter w Korei, „W transporcie” dla wylotu, przylotu, zwolnienia celnego i przeładunków u lokalnego kuriera, „W doręczeniu” po wyjściu lokalnego kuriera na trasę oraz „Dostarczona” po doręczeniu. Późne komunikaty wstępne od partnera ostatniej mili (`Shipping information received` i `Your parcel will be entrusted to last mile...`) zostają na osi czasu, nie cofając przesyłki.

Jeśli najnowszy skan ma cztery dni, a przesyłka nadal widnieje jako „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie”, Peek oznacza ją dniem tego skanu, np. „Brak aktualizacji od pon 12 paź”. Na stronie przesyłki pojawia się wtedy komunikat „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika Shipter.” Po trzech tygodniach bez nowego skanu pojawia się informacja „Od ponad trzech tygodni nic nowego. Jeśli przesyłka wciąż nie dotarła, zapytaj sprzedawcę. Peek dalej pyta przewoźnika Shipter.”

Gdy masz przesyłkę otwartą w Peek albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza status nawet co 10 minut; w dniu, gdy Shipter pokazuje `Out for delivery` lub `Your parcel is with your local courier and ready for delivery..`, co 2 minuty od godz. 6:00 według zegara najnowszego skanu, dopóki tracker Shipter odpowiada. W pozostałym czasie Peek sprawdza co godzinę. Po 12 godzinach wyłącznie na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika” albo po dwóch dniach bez zmian sprawdza co godzinę niezależnie od sposobu śledzenia, a po siedmiu dniach na „Zarejestrowana” — co 6 godzin. Gdy tracker Shipter nie odpowiada i Peek śledzi przesyłkę wyłącznie przez usługi śledzące, sprawdza co godzinę, co 3 godziny po spokojnym dniu i nawet co 15 minut w dniu, w którym przesyłka jest w doręczeniu, dopóki masz ją otwartą lub włączone powiadomienia.

Peek może wysyłać powiadomienia dla opcji „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”.

## Kontakt z Shipter

Shipter obsługuje firmy nadające przez niego przesyłki:

- **Oficjalna strona i zapytania o usługę:** [shipter.kr](https://www.shipter.kr/) (H3 NETWORKS Co., Ltd.; *이용문의* dla zapytań o usługę)
- **Oficjalny tracker online:** [shipter.kr/tracking](https://www.shipter.kr/tracking)
- **Najpierw skontaktuj się ze sprzedawcą:** każda firma kliencka ma w Shipter dedykowanego opiekuna obsługi klienta, który rozwiązuje problemy, zwroty i reklamacje ze swoimi klientami. Jeśli paczka jest opóźniona, uszkodzona lub zaginęła, napisz do sklepu, który ją wysłał, podając numer `H3...KR`.

## Często zadawane pytania

### Jak wygląda numer śledzenia Shipter?

Zaczyna się od `H3` i kończy na `KR`, mając pomiędzy dwie litery oraz pięć lub sześć cyfr: 11 albo 12 znaków, np. `H3AU482917KR`. Strona Shipter nie wyjaśnia znaczenia tych dwóch liter.

### Dlaczego „Shipping information received” pojawiło się kilka dni po wysłaniu paczki?

To komunikat od lokalnego partnera ostatniej mili w kraju docelowym, przekazywany przez Shipter już po jego własnym skanie przyjęcia. Nie cofa przesyłki — Peek zachowuje etap z poprzedniego skanu.

### Dlaczego moja przesyłka Shipter pokazuje zdarzenia innego przewoźnika?

Shipter odpowiada za eksport z Korei Południowej i międzynarodowy fracht lotniczy, następnie przekazuje paczkę lokalnemu kurierowi w kraju docelowym i prezentuje skany tego partnera z oddziałów i doręczenia wewnątrz Twojej historii Shipter.

### Czy Shipter podaje szacowaną datę doręczenia?

Nie. Publiczny kanał śledzenia Shipter pokazuje zdarzenia z eksportu, lotu, odprawy celnej i lokalnego kuriera wraz z przesunięciem GMT, bez przewidywanej daty doręczenia.

:::sources
- [Shipter (H3 NETWORKS Co., Ltd.)](https://www.shipter.kr/)
- [Shipter Tracking](https://www.shipter.kr/tracking)
- [universal-parcel-scraper Shipter adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/shipter)
:::
