---
title: Śledzenie BFE Express: numery CK i WYB, statusy i pomoc
description: Śledź paczki BFE Express do Argentyny, Peru, Urugwaju i USA: numery CK i WYB, odprawa celna, statusy doręczenia oraz regionalne adresy wsparcia.
slug: sledzenie-bfe-express
published: 2026-11-24
updated: 2026-11-24
---

BFE Express (*BFEexpress*, prowadzone przez Hongkong Bfe Info Tech Co Limited, założone w 2005 roku) to transgraniczna sieć logistyki e-commerce i doręczeń ostatniej mili łącząca zagranicznych sprzedawców — w tym sprzedawców Temu Argentyna — z odbiorcami w Argentynie, Peru, Urugwaju i Stanach Zjednoczonych. Firma obsługuje fracht międzynarodowy, odprawę celną, lokalne magazynowanie oraz dostawę ostatniej mili ze śledzeniem na [bfeexpress.com/tracking](https://www.bfeexpress.com/tracking).

Ponieważ BFE Express prowadzi przylot międzynarodowy i odprawę celną, zanim przekaże paczki regionalnym oddziałom ostatniej mili, jedna historia śledzenia zazwyczaj łączy angielskie skany lotniskowe i celne z hiszpańskimi skanami lokalnego doręczenia.

## Formaty numerów przesyłek BFE Express

Peek przyjmuje numery BFE Express złożone z 6 do 50 liter i cyfr. Formularz kontaktowy BFE Express akceptuje poniższe układy według kraju docelowego:

| Format (wymyślony) | Kraj docelowy i szczegóły |
| --- | --- |
| `CK54829173604518` | `CK54` + 12 cyfr (16 znaków) — Argentyna; żaden inny przewoźnik w Peek nie używa tego wzorca. |
| `CK548291736045182` | `CK54` + 13 cyfr (17 znaków) — alternatywny format dla Argentyny (taka sama długość jak w [Colis Privé](carrier:colis-prive)). |
| `CK512829173604518` | `CK512` + 12 cyfr (17 znaków) — format dla Peru (taka sama długość jak w [Colis Privé](carrier:colis-prive)). |
| `CK598829173604518` | `CK598` + 12 cyfr (17 znaków) — format dla Urugwaju (taka sama długość jak w [Colis Privé](carrier:colis-prive)). |
| `WYB482917360` | `WYB` + 9 cyfr (12 znaków) — format listu przewozowego dla Peru (pokrywa się z [Purolator](carrier:purolator) i [TNT Australia](carrier:tnt-au)). |

Cyfry po `CK` wskazują kierunek: `54` dla Argentyny, `512` dla Peru i `598` dla Urugwaju. Jeśli wkleisz bezpośredni link `bfeexpress.com/track?track_number=...`, Peek odczyta numer i od razu wybierze BFE Express. Inne wzorce międzynarodowe znajdziesz w poradniku o [formatach numerów śledzenia](guide:tracking-number-formats).

## Co oznaczają statusy śledzenia BFE Express

Tracker BFE Express wyświetla dwie grupy wpisów — międzynarodowy pierwszy odcinek i ostatnią milę — a Peek odczytuje obie.

| Komunikat BFE Express | Co oznacza |
| --- | --- |
| `Arrived at Airport of destination` | Lot z Twoją paczką wylądował w kraju docelowym. |
| `Transport to next facility` | Przejazd między terminalem lotniskowym a lokalnym oddziałem celnym lub sortownią. |
| `Customs declaration completed` | Zakończono odprawę celną w kraju docelowym, przed przekazaniem na ostatnią milę. |
| `Turned over to last mile facility` | Przekazano z etapu celnego BFE do lokalnego oddziału doręczającego. |
| `Se ha recibido el paquete.` / `Tu paquete fue recibido por en: ...` | Przyjęto w lokalnym oddziale ostatniej mili w Argentynie lub Urugwaju. |
| `En embarque` / `En transporte` / `Tu paquete está en viaje a ...` | Załadowano i przemieszcza się między krajowymi oddziałami w stronę Twojego miasta. |
| `Tu paquete se encuentra en ...` / `En espera para salir a reparto en ...` | Oczekuje w lokalnym oddziale doręczającym na wydanie kurierowi. |
| `Reparto en curso` | Dzisiaj w doręczeniu u lokalnego kuriera. |
| `Entregado` | Doręczona odbiorcy. Obok własny status przesyłki BFE pokazuje `Delivered`. |

Angielskie wiersze BFE Express podają najpierw miejsce przed przecinkiem (np. `<City>, Customs declaration completed`); hiszpańskie wiersze w Argentynie zaczynają się od kraju, natomiast urugwajska ostatnia mila podaje miejsce po `en` lub `a`. Gdy własny status paczki w BFE brzmi `Delivered`, Peek oznacza ją jako dostarczoną, nawet jeśli najnowszy wiersz mówi co innego.

## Odprawa celna, przekazanie na ostatnią milę i opóźnienia

Strona BFE Express oferuje wysyłkę paczek, fracht międzynarodowy, odprawę celną, lokalne magazynowanie oraz doręczenie ostatniej mili i kieruje ofertę także do sprzedawców Temu Argentyna. Kilka wskazówek pomaga czytać przebieg trasy:

- **Przed lotniskiem docelowym:** jeśli tracker BFE Express nic jeszcze nie pokazuje, sprawdź stronę zamówienia u sprzedawcy i zajrzyj do poradnika o [braku aktualizacji śledzenia](guide:tracking-not-updating).
- **Urząd celny i ostatnia mila:** `Customs declaration completed` oraz `Turned over to last mile facility` potrafią pojawić się w odstępie godziny w tym samym miejscu, a przyjęcie w lokalnym oddziale (`Se ha recibido el paquete.` lub `Tu paquete fue recibido por en: ...`) dzień później. Jeśli paczka zatrzyma się w urzędzie celnym w kraju docelowym, zobacz [poradnik celny](guide:customs).
- **Planowany dzień doręczenia:** BFE Express może podać planowany dzień doręczenia, gdy paczka jest w drodze, a Peek go wyświetla. Peek usuwa go, gdy przesyłka zostanie dostarczona, zwrócona lub gotowa do odbioru albo gdy skan wypadnie po tym dniu.
- **Oznaczona jako dostarczona lub uszkodzona:** strona [Obsługi klienta BFE Express](https://www.bfeexpress.com/contactUS) omawia obie sytuacje w FAQ i prosi o przygotowanie numeru przesyłki przed kontaktem z lokalnym zespołem. Zobacz także poradnik [dostarczona, ale nieodebrana](guide:delivered-not-received).

Dla krajowych paczek w Argentynie nadawanych bezpośrednio u lokalnych przewoźników porównaj [OCA](carrier:oca) i [Andreani](carrier:andreani).

## Śledzenie BFE Express w Peek

Wklej swój kod `CK...` lub `WYB...` w Peek, który śledzi ponad 4500 przewoźników. Sam kształt numeru nie mówi Peek, że należy on do BFE Express, więc podczas dodawania Peek pyta BFE Express („Sprawdzanie usług śledzenia…”) i pokazuje „BFE Express ma tę przesyłkę.”, gdy BFE Express zna numer. Dla 16-znakowego kodu `CK54` pyta tylko BFE Express. Dla 17-znakowego kodu `CK` pyta też [Colis Privé](carrier:colis-prive): we Francji najpierw, gdzie indziej dopiero wtedy, gdy BFE Express i usługi śledzenia nie znają numeru. Dla kodu `WYB` pyta BFE Express, [TNT Australia](carrier:tnt-au) i [Purolator](carrier:purolator): w Argentynie, Peru, Urugwaju i USA najpierw BFE Express, w Kanadzie najpierw Purolator, gdzie indziej najpierw TNT Australia. Aby od początku śledzić paczkę w BFE Express, kliknij „Zmień” i wybierz BFE Express w sekcji „Pasują do numeru” albo użyj „Zmień przewoźnika” na już dodanej paczce. Link `bfeexpress.com/track?track_number=...` od razu prowadzi do BFE Express.

Peek odpytuje bezpośrednio API śledzenia BFE Express przez otwarty [adapter BFE Express w universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/bfe-express). Czyta obie grupy wierszy w kolejności czasowej, jako miejsce bierze miasto, oddział lub kraj i zachowuje planowany dzień doręczenia oraz kraj docelowy. Godzina z przesunięciem GMT zachowuje dokładny moment; godzina bez przesunięcia otrzymuje strefę miejsca (czas Buenos Aires dla Ezeiza, czas Limy dla Limy, czas Montevideo dla Montevideo), skan bez miejsca — strefę sąsiedniego skanu, a w pozostałych przypadkach czas Buenos Aires, po czym każdy skan wyświetla się w czasie Twojego urządzenia. Adresy uliczne i zdjęcia potwierdzenia doręczenia z portalu BFE są pomijane.

Peek pokazuje wiersze BFE Express jako „W transporcie”, z wyjątkiem `Reparto en curso` („W doręczeniu”) oraz `Entregado` („Dostarczona”). Oznacza paczkę jako „Dostarczona” również wtedy, gdy własny status BFE mówi `Delivered`.

Gdy masz przesyłkę otwartą w Peek albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza status nawet co 10 minut; w dniu, gdy BFE Express pokazuje `Reparto en curso`, co 2 minuty od godz. 6:00 według zegara najnowszego skanu, dopóki tracker BFE Express odpowiada. W pozostałym czasie Peek sprawdza co godzinę. Po 12 godzinach wyłącznie na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika” albo po dwóch dniach bez zmian sprawdza co godzinę niezależnie od sposobu śledzenia, a po siedmiu dniach na „Zarejestrowana” — co 6 godzin. Gdy BFE Express nie odpowiada i Peek śledzi przesyłkę wyłącznie przez usługi śledzące, sprawdza co godzinę, co 3 godziny po spokojnym dniu i nawet co 15 minut w dniu, w którym przesyłka jest w doręczeniu, dopóki masz ją otwartą lub włączone powiadomienia.

Jeśli najnowszy skan ma cztery dni, a przesyłka nadal widnieje jako „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie”, Peek oznacza ją dniem tego skanu, np. „Brak aktualizacji od pon 12 paź”. Na stronie przesyłki pojawia się wtedy komunikat „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika BFE Express.” wraz z linkiem „Poproś przewoźnika BFE Express o pomoc” do strony Contact Us BFE Express. Po trzech tygodniach bez nowego skanu pojawia się informacja „Od ponad trzech tygodni nic nowego. Jeśli przesyłka wciąż nie dotarła, zapytaj sprzedawcę. Peek dalej pyta przewoźnika BFE Express.”

Użyj ustawień alertów Peek — „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie” — aby określić, które zdarzenia wysyłają powiadomienia.

## Kontakt z BFE Express

BFE Express podaje regionalne adresy e-mail obsługi klienta na stronie [Contact Us](https://www.bfeexpress.com/contactUS), wszystkie czynne od **poniedziałku do piątku w godz. 9:00 – 18:00** (z wyjątkiem świąt):

- **Argentyna:** `info@ar.bfeexpress.com`
- **Peru:** `info@pe.bfeexpress.com`
- **Urugwaj:** `info@uy.bfeexpress.com`
- **Stany Zjednoczone:** `info@us.bfeexpress.com`
- **Paragwaj:** `info_py@bfeexpress.com`
- **Pozostałe obsługiwane regiony (Kolumbia, Ekwador, Serbia, Dominika):** `info@other.bfeexpress.com`
- **Oficjalna strona i śledzenie:** [bfeexpress.com](https://www.bfeexpress.com/) oraz [bfeexpress.com/tracking](https://www.bfeexpress.com/tracking)

Pisząc do zespołu dla swojego kraju, przygotuj pełny numer śledzenia `CK...` lub `WYB...`.

## Często zadawane pytania

### Dlaczego śledzenie BFE Express zaczyna się dopiero na lotnisku docelowym bez wcześniejszych skanów?

Tracker BFE Express może zaczynać od `Arrived at Airport of destination`, czyli przylotu do kraju docelowego. Wcześniejsze nadanie sprawdź na stronie zamówienia u sprzedawcy.

### Dlaczego moje skany BFE Express są częściowo po angielsku, a częściowo po hiszpańsku?

BFE Express rejestruje międzynarodowy przylot na lotnisko, odprawę celną i przekazanie na ostatnią milę po angielsku, natomiast krajowy oddział kurierski obsługujący końcowe doręczenie wpisuje skany po hiszpańsku (aż do `Reparto en curso` i `Entregado`).

### Co oznaczają cyfry `54`, `512` i `598` w numerach `CK`?

Wskazują kraj docelowy: `CK54...` dotyczy Argentyny (16 lub 17 znaków), `CK512...` Peru (17 znaków), a `CK598...` Urugwaju (17 znaków). Przesyłki do Peru korzystają także z 12-znakowego formatu `WYB` + 9 cyfr.

### Co zrobić, jeśli BFE Express nie znajduje mojego numeru?

Sprawdź, czy w kodzie `CK` lub `WYB` nie brakuje znaków, i zapytaj sprzedawcę, czy przekazał już paczkę do BFE Express.

:::sources
- [BFE Express](https://www.bfeexpress.com/)
- [BFE Express Tracking](https://www.bfeexpress.com/tracking)
- [BFE Express Customer Support](https://www.bfeexpress.com/contactUS)
- [universal-parcel-scraper BFE Express adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/bfe-express)
:::
