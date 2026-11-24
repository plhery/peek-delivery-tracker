---
title: Śledzenie TNT Australia: numery, statusy i pomoc
description: Śledź krajowe przesyłki TNT Australia: format 3 litery + 9 cyfr, statusy z oddziałów, ponowne doręczenie pn–pt oraz infolinia 13 11 50.
slug: sledzenie-tnt-australia
published: 2026-11-24
updated: 2026-11-24
---

TNT Australia (*TNT Express Australia*, w trakcie integracji z FedEx Express) przewozi paczki, dokumenty i palety na terenie całej Australii przez sieć swoich oddziałów ([tnt.com/express/en_au](https://www.tnt.com/express/en_au/site/home.html)). Przesyłki międzynarodowe mają osobny tracker (zobacz [TNT](carrier:tnt)), natomiast krajowe przesyłki australijskie obsługuje dedykowana wyszukiwarka pod adresem [tntexpress.com.au](https://www.tntexpress.com.au/interaction/Trackntrace.aspx).

Krajową przesyłkę w Australii sprawdzisz na stronie [TNT Australia Track & Trace](https://www.tntexpress.com.au/interaction/Trackntrace.aspx) albo wklejając swój 12-znakowy numer listu przewozowego w pole powyżej.

## Formaty numerów przesyłek TNT Australia

Peek śledzi krajowe przesyłki TNT Australia na podstawie numeru o długości 12 znaków:

| Format (wymyślony) | Co oznacza |
| --- | --- |
| `SYD482917360` | **3 wielkie litery + 9 cyfr** (łącznie 12 znaków) — standardowy krajowy numer przesyłki TNT Australia śledzony w `tntexpress.com.au`. |

Ponieważ 3 litery i 9 cyfr pasują także do numerów [Purolator](carrier:purolator), Peek pyta obu przewoźników po kolei: w Kanadzie najpierw Purolator, a w pozostałych krajach najpierw TNT Australia. Gdy TNT Australia odpowie, wyświetla się komunikat „TNT Australia ma tę przesyłkę.” Część prefiksów, np. `RWM` używany przez Kuehne+Nagel, jest wyłączona ze wzorca TNT Australia. Bezpośredni link `tntexpress.com.au/interaction/ASPs/trackref_detail.asp?con=...` lub `trackref.aspx?con=...` od razu kieruje do TNT Australia.

[Centrum pomocy TNT Australia](https://www.tnt.com/express/en_au/site/support.html) nazywa numerem przesyłki (*shipment / consignment number*) **9-cyfrowy** numer umieszczony po lewej stronie faktury, około 3 cm poniżej numeru klienta. Tracker TNT Australia w Peek przyjmuje jednak wyłącznie 3 litery i 9 cyfr. Więcej wzorców znajdziesz w poradniku o [formatach numerów śledzenia](guide:tracking-number-formats).

## Co oznaczają statusy śledzenia TNT Australia

Krajowa strona szczegółów przesyłki TNT Australia pokazuje cztery kolumny — `Status`, `Date`, `Time` oraz `Depot` — a na górze datę `Estimated Delivery by:`, gdy jest dostępna.

| Komunikat TNT Australia | Co oznacza |
| --- | --- |
| `Your shipment data is lodged` | Nadawca przekazał dane przesyłki elektronicznie; TNT Australia jeszcze jej nie odebrało. |
| `We've collected your shipment` | TNT Australia odebrało przesyłkę od nadawcy, przed pierwszym skanem w oddziale. |
| `We're processing your shipment` | Skan w oddziale nadawczym, tranzytowym lub doręczającym TNT Australia. |
| `Your shipment's with a driver` | Przesyłka jest dziś w samochodzie kuriera w drodze pod adres domowy lub firmowy. |
| `New delivery address required` | Podczas doręczenia nie udało się dostarczyć paczki pod wskazany adres; czeka w lokalnym oddziale na nowy adres lub odbiór własny. |
| `We've delivered your shipment` | Doręczona pod adres odbiorcy. |
| `Your shipment was collected` | Odbiorca odebrał przesyłkę osobiście w oddziale TNT (co innego niż `We've collected your shipment`, które dotyczy odbioru od nadawcy). |
| `Book-In Delivery Requested` / `Book-In Delivery Arranged` | Odbiorca poprosił o umówione doręczenie, a TNT je potwierdziło. To powiadomienia, nie skany: nie zmieniają etapu paczki, a potwierdzenie potrafi pojawić się już po doręczeniu. |

## Ponowne doręczenie, odbiór w oddziale i opóźnienia

[Centrum pomocy TNT Australia](https://www.tnt.com/express/en_au/site/support.html) wyjaśnia, co zrobić przy nieudanym doręczeniu lub zmianie terminu:

- **Jeśli kurier zostawił awizo:** skorzystaj z usługi TNT Re-delivery Service, aby wybrać nowy dzień doręczenia.
- **Jeśli nie ma awiza (lub nie było jeszcze próby doręczenia):** skontaktuj się z TNT Australia, podając numer przesyłki, pełny adres dostawy i wybrany termin ponownego doręczenia.
- **Ponowne doręczenia tylko od poniedziałku do piątku:** TNT Australia informuje, że ponowne dostawy realizowane są wyłącznie między poniedziałkiem a piątkiem.
- **`New delivery address required`:** przesyłka czeka w oddziale z kolumny `Depot`, dopóki nie podasz innego adresu lub nie odbierzesz jej osobiście; skontaktuj się z TNT Australia. Zobacz także poradniki o [nieudanym doręczeniu](guide:missed-delivery) i [braku aktualizacji śledzenia](guide:tracking-not-updating).

Jeśli Twoje zamówienie w Australii obsługuje inny krajowy przewoźnik, porównaj śledzenie dla [Australia Post](carrier:australia-post), [Aramex Australia](carrier:aramex-au), [CouriersPlease](carrier:couriersplease) lub [Border Express](carrier:border-express).

## Śledzenie TNT Australia w Peek

Wklej w Peek swój 12-znakowy krajowy numer (3 litery + 9 cyfr) albo link śledzenia z `tntexpress.com.au`. Jeśli Peek przypisze paczkę innemu przewoźnikowi, wybierz „Zmień” podczas dodawania lub „Zmień przewoźnika” na ekranie przesyłki.

Peek pobiera krajową stronę szczegółów TNT Australia bezpośrednio przez otwarty [adapter TNT Australia z universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/tnt-au), z automatyczną ponowną próbą przy przekroczeniu czasu odpowiedzi sieci. Odczytuje treść każdego skanu, oddział, godzinę oraz datę `Estimated Delivery by:` aż do doręczenia. TNT Australia podaje godziny oddziałów bez przesunięcia względem UTC: Peek przypisuje każdemu skanowi strefę czasową jego oddziału (czas Sydney dla Sydney, czas Perth dla Perth itd.), skanowi bez oddziału strefę sąsiedniego skanu, a w przeciwnym razie czas Sydney, po czym wyświetla wszystkie godziny w czasie Twojego urządzenia. Krajowa tabela szczegółów nie udostępnia wagi, nazwy usługi ani kodów statusu.

Peek przekłada komunikaty TNT Australia na przejrzyste etapy: „Zarejestrowana” (`Your shipment data is lodged`), „Przyjęta przez przewoźnika” (`We've collected your shipment`), „W transporcie” (`We're processing your shipment`), „W doręczeniu” (`Your shipment's with a driver`), „Zgłoszono problem” (`New delivery address required`) oraz „Dostarczona” (`We've delivered your shipment` lub `Your shipment was collected`). Powiadomienia o rezerwacji terminu (`Book-In Delivery Requested` i `Book-In Delivery Arranged`) pozostają na osi czasu, nie cofając zakończonego doręczenia ani nie zmieniając bieżącego etapu.

Gdy masz przesyłkę otwartą w Peek albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza status nawet co 10 minut; w dniu, gdy TNT Australia pokazuje `Your shipment's with a driver`, co 2 minuty od godz. 6:00 według zegara najnowszego skanu, dopóki tracker TNT Australia odpowiada. W pozostałym czasie Peek sprawdza co godzinę. Po 12 godzinach wyłącznie na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika” albo po dwóch dniach bez zmian sprawdza co godzinę niezależnie od sposobu śledzenia, a po siedmiu dniach na „Zarejestrowana” — co 6 godzin. Gdy TNT Australia nie odpowiada i Peek śledzi przesyłkę wyłącznie przez usługi śledzące, sprawdza co godzinę, co 3 godziny po spokojnym dniu i nawet co 15 minut w dniu, w którym przesyłka jest w doręczeniu, dopóki masz ją otwartą lub włączone powiadomienia.

Jeśli najnowszy skan ma cztery dni, a przesyłka nadal widnieje jako „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie”, Peek oznacza ją dniem tego skanu, np. „Brak aktualizacji od pon 12 paź”. Na stronie przesyłki pojawia się wtedy komunikat „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika TNT Australia.” wraz z linkiem „Poproś przewoźnika TNT Australia o pomoc” do Centrum pomocy TNT Australia. Po trzech tygodniach bez nowego skanu pojawia się informacja „Od ponad trzech tygodni nic nowego. Jeśli przesyłka wciąż nie dotarła, zapytaj sprzedawcę. Peek dalej pyta przewoźnika TNT Australia.”

W ustawieniach powiadomień Peek wybierz „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”, aby zdecydować, kiedy dostawać alerty.

## Kontakt z TNT Australia

- **Telefon (w Australii):** `13 11 50` (publikowany na całej stronie TNT Australia)
- **Centrum pomocy i ponowne doręczenie:** [Wsparcie TNT Australia](https://www.tnt.com/express/en_au/site/support.html)
- **Krajowy portal śledzenia:** [tntexpress.com.au Track & Trace](https://www.tntexpress.com.au/interaction/Trackntrace.aspx) (śledzi jednocześnie do 10 przesyłek krajowych, Sameday Local Jobs lub 1 referencję elektroniczną)
- **Strona TNT Australia i link do śledzenia międzynarodowego:** [tnt.com/express/en_au](https://www.tnt.com/express/en_au/site/home.html) oraz [przegląd Track & Trace](https://www.tnt.com/express/en_au/site/shipping-tools/tracking.html)

Przygotuj swój 12-znakowy krajowy numer przesyłki i adres dostawy przed telefonem pod `13 11 50` lub umawianiem ponownego doręczenia w dni robocze.

## Często zadawane pytania

### Jaka jest różnica między `We've collected your shipment` a `Your shipment was collected`?

`We've collected your shipment` pojawia się na początku drogi, gdy TNT Australia odbiera paczkę od nadawcy. `Your shipment was collected` pojawia się na końcu, gdy odbiorca osobiście odbiera zatrzymaną paczkę z oddziału TNT — dlatego Peek oznacza ją jako „Dostarczona”.

### Dlaczego `Book-In Delivery Arranged` pojawiło się już po doręczeniu przesyłki?

`Book-In Delivery Arranged` to potwierdzenie umówionego doręczenia przez TNT Australia — powiadomienie, a nie skan, które potrafi wpłynąć już po samym doręczeniu. Peek utrzymuje status „Dostarczona” zamiast cofać przesyłkę na wcześniejszy etap.

### Czy TNT Australia doręcza lub ponawia doręczenia w weekendy?

[Centrum pomocy TNT Australia](https://www.tnt.com/express/en_au/site/support.html) podaje, że przełożone dostawy mogą być ponownie doręczane wyłącznie od **poniedziałku do piątku**.

### Mój numer TNT ma 9 cyfr bez liter — dlaczego nie śledzi się jako TNT Australia w Peek?

Tracker TNT Australia w Peek obsługuje numery złożone z 3 liter i 9 cyfr, czyli krajowe przesyłki odczytywane w `tntexpress.com.au`. Centrum pomocy TNT nazywa numerem przesyłki 9-cyfrowy kod z faktury — sprawdź go bezpośrednio na stronie [TNT Australia Track & Trace](https://www.tnt.com/express/en_au/site/shipping-tools/tracking.html) lub zadzwoń pod `13 11 50`.

:::sources
- [TNT Australia](https://www.tnt.com/express/en_au/site/home.html)
- [TNT Australia Help Center](https://www.tnt.com/express/en_au/site/support.html)
- [TNT Australia Track & Trace](https://www.tnt.com/express/en_au/site/shipping-tools/tracking.html)
- [TNT Express Australia Domestic Tracker](https://www.tntexpress.com.au/interaction/Trackntrace.aspx)
- [universal-parcel-scraper TNT Australia adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/tnt-au)
:::
