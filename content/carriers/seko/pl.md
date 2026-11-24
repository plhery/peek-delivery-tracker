---
title: Śledzenie SEKO Logistics: numery, OmniParcel i doręczenie
description: Śledź przesyłki SEKO Logistics i SEKO OmniParcel: formaty numerów, kody GX, znaczenie statusów, infolinie USA i UK oraz szacowane terminy.
slug: sledzenie-seko-logistics
published: 2026-11-24
updated: 2026-11-24
---

SEKO Logistics powstało w 1976 roku z jednym biurem w Chicago, a dziś ma ponad 150 oddziałów w przeszło 60 krajach oraz 15 głównych hubów. Firma oferuje fracht lotniczy, morski i drogowy, obsługę e-commerce, *White Glove & Specialty Delivery* oraz *Home Delivery & Last Mile*. Przesyłki można śledzić na [sekologistics.com/en/track](https://www.sekologistics.com/en/track/), a transgraniczne paczki e-commerce w [trackerze OmniParcel](https://track.omniparcel.com/) należącym do SEKO.

Ponieważ standardowy portal SEKO i OmniParcel działają jako dwa osobne systemy z innymi formatami numerów i stylem komunikatów, rozpoznanie posiadanego formatu ułatwia właściwe czytanie historii przesyłki.

## Formaty numerów SEKO Logistics i OmniParcel

Przesyłki SEKO Logistics nie mają jednego wyłącznego prefiksu — dzielą się na dwie grupy obsługiwane przez różne portale:

- **Numery przesyłek SEKO Logistics:** Peek przyjmuje od 8 do 20 liter i cyfr z ciągiem co najmniej 7 cyfr. Spotykane układy to m.in. 3 litery i 7 cyfr (np. `WFY4829173`), 5 liter i 7 cyfr albo same 9 cyfr (np. `482917360`). Peek nigdy nie rozpoznaje SEKO po samym numerze: dla 3 liter + 7 cyfr pyta [Australia Post](carrier:australia-post), dla 5 liter + 7 cyfr żadnego, a dla 9 cyfr [DPD Ireland](carrier:dpd-ie) (poza Irlandią wyłącznie w ostateczności). Wybierz SEKO Logistics ręcznie lub wklej link śledzenia.
- **Numery SEKO OmniParcel (`GX` + 17 lub 18 cyfr):** transgraniczne przesyłki e-commerce na `track.omniparcel.com` mają prefiks `GX` i 17 lub 18 cyfr (np. `GX482917360514829173`). Główna strona śledzenia SEKO nie rozpoznaje numerów `GX` — odpowiada za nie wyłącznie tracker OmniParcel.

Jeśli wkleisz link `sekologistics.com/en/track/?trackingNumbers=...` albo `track.omniparcel.com/...`, Peek odczyta numer i automatycznie skieruje go do SEKO Logistics. Więcej o różnicach między kodami frachtowymi i kurierskimi znajdziesz w poradniku o [formatach numerów śledzenia](guide:tracking-number-formats).

## Statusy śledzenia SEKO Logistics i OmniParcel

Główna strona śledzenia SEKO często zapisuje etapy jako `Status Changed to [...]` (czasem z końcowym myślnikiem zamiast nawiasów), podczas gdy OmniParcel podaje opisy międzynarodowych hubów, lotów, odprawy celnej i lokalnego doręczyciela.

| Komunikat SEKO / OmniParcel | Co oznacza |
| --- | --- |
| `Shipment Data Received` / `Shipment Created` / `Tracking number allocated & order ready` | Dane przesyłki dotarły do SEKO lub OmniParcel przed fizycznym odbiorem albo przyjęciem w magazynie. |
| `Arrived at PU location` | Kierowca odbiorczy dotarł do nadawcy; ładunek nie jest jeszcze zapisany jako odebrany. |
| `Picked Up From Customer` / `At Pickup Terminal` / `Received at Origin Warehouse` | SEKO odebrało przesyłkę lub przyjęło ją w terminalu albo magazynie nadawczym. |
| `In Transit` / `In Transit to Delivery Terminal` / `At Transfer Terminal` / `Processed through Export Hub` / `Flight - Freight departed to destination` | W drodze krajowym transportem liniowym lub międzynarodowym frachtem lotniczym do kraju bądź stacji doręczenia. |
| `At Delivery Terminal` / `At Delivery Terminal Delivery Appt Set` / `On Hand Destination Station` / `Delivered To Warehouse` | Dotarła do docelowego terminalu SEKO lub lokalnego magazynu przed końcowym doręczeniem; `Delivery Appt Set` oznacza umówiony termin dostawy do domu. |
| `Received by Destination Customs` / `Customs held for inspection and clearance` | Trwa obsługa lub kontrola celna w kraju docelowym dla przesyłki transgranicznej OmniParcel. |
| `Customs cleared` / `Received by Last Mile Provider` / `Received by carrier` | Odprawa celna zakończona; przesyłka przekazana lokalnemu hubowi docelowemu lub kurierowi ostatniej mili. |
| `Shipment is Out for Delivery` / `Driver on site Delivery Location` / `The order is on the way to the recipient.` | Dzisiaj w doręczeniu z kurierem albo kierowca jest już na miejscu. |
| `Awaiting collection` / `Customer to collect from carrier` / `Delivered to locker/collection point` | Oczekuje na odbiór przez odbiorcę w oddziale, punkcie odbioru lub automacie paczkowym. |
| `Attempted delivery` / `Attempted Delivery - Refused by customer` | Próba doręczenia nie powiodła się. |
| `Temporary Origin Hold` / `Temporary Destination Hold` | Tymczasowe wstrzymanie przesyłki w terminalu nadawczym lub docelowym. |
| `Shipment Delivered` / `Delivered - Authority to Leave / Safe Drop` | Doręczona pod adres odbiorcy (lub pozostawiona za zgodą na bezpieczne odłożenie). |
| `Return to sender` | Wraca do nadawcy. |

Na dwie frazy w głównym portalu SEKO warto zwrócić szczególną uwagę:

- **`Delivered To Warehouse`** oznacza, że ładunek trafił do docelowego magazynu SEKO, a nie pod Twój adres — doręczenie jeszcze nie nastąpiło.
- **`POD Image Attached` oraz `POD IMAGED`** odnotowują dołączenie skanu potwierdzenia doręczenia (czasem kilka dni po dostawie), a **`Return Data Received`** zapisuje zgłoszenie zwrotu bez potwierdzenia, że przesyłka fizycznie wyruszyła z powrotem. Żaden z tych wpisów nie zmienia fizycznego etapu doręczenia.

## Dostawa do domu, cło transgraniczne i opóźnienia

Strona SEKO wymienia usługi *White Glove & Specialty Delivery* oraz *Home Delivery & Last Mile*, a także zabezpieczoną logistykę elektroniki i sprzętu komputerowego obok transgranicznych zamówień OmniParcel.

- **Umawianie dostawy do domu:** `At Delivery Terminal Delivery Appt Set` i `On Hand DEST Delv Appt Set` informują o umówionym terminie doręczenia, gdy przesyłka czeka w terminalu docelowym. W trakcie transportu strona SEKO często podaje datę `Expected Arrival` lub przedział `Estimated delivery between`.
- **Przekazania transgraniczne OmniParcel:** przy zamówieniach międzynarodowych (`GX...`) skany OmniParcel obejmują magazyn nadawczy, eksport, lot, urząd celny w kraju docelowym i lokalnego kuriera ostatniej mili (`Received by Last Mile Provider`). Podczas lotu i odprawy celnej skany mogą na pewien czas ustać — zajrzyj do [poradnika celnego](guide:customs) i artykułu o [braku aktualizacji śledzenia](guide:tracking-not-updating).
- **Godziny i duplikaty:** strona SEKO podaje godziny co do minuty bez strefy czasowej i czasem wypisuje ten sam status dwa razy w tej samej minucie inną wielkością liter; Peek zachowuje jeden wpis i datuje kroki SEKO (które nie podają miejsca) według czasu Chicago. OmniParcel pokazuje godziny w UTC+10:00, o ile nie zmienisz listy stref czasowych; Peek odpytuje OmniParcel w UTC, więc każdy skan zachowuje dokładny moment. Peek wyświetla wszystkie godziny w czasie Twojego urządzenia.

Jeśli inny odcinek międzynarodowego zamówienia obsługuje duży kurier ekspresowy, porównaj śledzenie dla [DHL Express](carrier:dhl-express) lub [FedEx](carrier:fedex).

## Śledzenie SEKO Logistics w Peek

Ponieważ numery SEKO pokrywają się z innymi przewoźnikami i nie mają jednego wspólnego prefiksu, najlepiej wkleić pełny link `sekologistics.com/en/track/?trackingNumbers=...` lub `track.omniparcel.com/...`. Jeśli wpisujesz goły numer — `WFY4829173`, `482917360` albo kod `GX...` z OmniParcel — wybierz SEKO Logistics ręcznie przez „Zmień” przy dodawaniu przesyłki lub „Zmień przewoźnika” na ekranie śledzenia.

Peek odpytuje oba portale bezpośrednio przez otwarty [adapter SEKO z universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/seko): kody `GX` + 17–18 cyfr trafiają automatycznie do OmniParcel (pobierane w UTC wraz z miejscem skanu, nazwą usługi, wagą w kilogramach i krajem docelowym), a numery przesyłek SEKO na stronę śledzenia SEKO (przewidywana data lub przedział doręczenia, nazwa usługi, kraj docelowy i zdeduplikowane etapy, z pominięciem wierszy administracyjnych `POD Image Attached` / `POD IMAGED` oraz imion podpisujących i kodów pocztowych).

Peek przekłada kroki SEKO i OmniParcel na standardowe etapy: „Zarejestrowana” dla otrzymanych danych, utworzenia przesyłki i dojazdu kierowcy na odbiór; „Przyjęta przez przewoźnika” dla odbioru i przyjęcia w magazynie nadawczym; „W transporcie” dla skanów w hubach, lotów, przeładunków, terminalu docelowego, przyjęcia do magazynu, zakończonej odprawy celnej i przekazania na ostatnią milę; „W urzędzie celnym” dla kontroli celnej w kraju docelowym; „W doręczeniu” dla wyjścia do doręczenia i obecności kierowcy na miejscu; „Do odbioru” dla oczekiwania w punkcie lub automacie; „Próba doręczenia” dla nieudanych prób; „Zgłoszono problem” dla wstrzymań w terminalu nadawczym lub docelowym; „Dostarczona” dla doręczenia i pozostawienia za zgodą; oraz „Zwrócona nadawcy” dla zwrotów.

Gdy masz przesyłkę otwartą w Peek albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza status nawet co 10 minut; w dniu, gdy strona SEKO pokazuje `Shipment is Out for Delivery`, `Out for Delivery` lub `Driver on site Delivery Location`, albo OmniParcel pokazuje `Out for delivery` lub `The order is on the way to the recipient.`, co 2 minuty od godz. 6:00 według zegara najnowszego skanu, dopóki tracker SEKO odpowiada. W pozostałym czasie Peek sprawdza co godzinę. Po 12 godzinach wyłącznie na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika” albo po dwóch dniach bez zmian sprawdza co godzinę niezależnie od sposobu śledzenia, a po siedmiu dniach na „Zarejestrowana” — co 6 godzin. Gdy SEKO nie odpowiada i Peek śledzi przesyłkę wyłącznie przez usługi śledzące, sprawdza co godzinę, co 3 godziny po spokojnym dniu i nawet co 15 minut w dniu, w którym przesyłka jest w doręczeniu, dopóki masz ją otwartą lub włączone powiadomienia.

Jeśli najnowszy skan ma cztery dni, a przesyłka nadal widnieje jako „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie”, Peek oznacza ją dniem tego skanu, np. „Brak aktualizacji od pon 12 paź”. Na stronie przesyłki pojawia się wtedy komunikat „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika SEKO Logistics.” Po trzech tygodniach bez nowego skanu pojawia się informacja „Od ponad trzech tygodni nic nowego. Jeśli przesyłka wciąż nie dotarła, zapytaj sprzedawcę. Peek dalej pyta przewoźnika SEKO Logistics.”

W ustawieniach alertów Peek wybierz „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”, aby dopasować powiadomienia.

## Kontakt z SEKO Logistics

SEKO publikuje regionalne infolinie obsługi klienta bezpośrednio na swojej [stronie śledzenia](https://www.sekologistics.com/en/track/) oraz stronie [Contact Us](https://www.sekologistics.com/en/contact-us/):

- **Infolinia US Home Delivery:** (866) 268-5263
- **Obsługa klienta w Wielkiej Brytanii:** +44 1784 417120
- **Oficjalne śledzenie online:** [SEKO Track My Shipment](https://www.sekologistics.com/en/track/) (obsługuje do 30 numerów naraz) oraz [OmniParcel Tracker](https://track.omniparcel.com/)
- **Zapytanie online o przesyłkę:** [Kontakt z SEKO Logistics](https://www.sekologistics.com/en/contact-us/) (wybierz formularz „Track My Shipment”)
- **O firmie:** [O SEKO Logistics](https://www.sekologistics.com/en/about/) oraz [sekologistics.com](https://www.sekologistics.com/en/)

Przygotuj numer śledzenia SEKO przed telefonem lub wypełnieniem formularza Track My Shipment.

## Często zadawane pytania

### Dlaczego mój numer `GX` nie działa na `sekologistics.com/en/track/`?

Numery zaczynające się od `GX` i zawierające 17 lub 18 cyfr należą do platformy OmniParcel firmy SEKO i działają wyłącznie na [track.omniparcel.com](https://track.omniparcel.com/). W Peek wybór SEKO Logistics automatycznie kieruje numery `GX` do OmniParcel, a zwykłe numery SEKO do głównego trackera SEKO.

### Czy „Delivered To Warehouse” oznacza, że zamówienie doręczono pod zły adres?

Nie. W śledzeniu SEKO Logistics status `Delivered To Warehouse` oznacza, że przesyłka dotarła do docelowego magazynu SEKO przed końcowym doręczeniem. Poczekaj na `Out for delivery` i `Shipment Delivered`, które potwierdzają dostawę pod Twoje drzwi.

### Dlaczego Peek poprosił o wybór przewoźnika dla mojego numeru SEKO?

Numery SEKO (np. 3 litery + 7 cyfr lub 9 cyfr) pasują także do innych przewoźników, a Peek nigdy nie rozpoznaje SEKO po samym numerze — dotyczy to również numerów `GX`. Wskazanie SEKO Logistics przez „Zmień” albo wklejenie oficjalnego linku SEKO lub OmniParcel dokładnie mówi Peek, gdzie szukać.

### Jak umówić termin dostawy do domu w SEKO Logistics?

Zadzwoń na infolinię US Home Delivery pod numer `(866) 268-5263` lub do obsługi klienta w Wielkiej Brytanii pod `+44 1784 417120`, albo skorzystaj z formularza Track My Shipment na [sekologistics.com/en/contact-us](https://www.sekologistics.com/en/contact-us/).

:::sources
- [SEKO Logistics](https://www.sekologistics.com/en/)
- [SEKO Logistics — Track My Shipment](https://www.sekologistics.com/en/track/)
- [SEKO Logistics — About](https://www.sekologistics.com/en/about/)
- [SEKO Logistics — Contact Us](https://www.sekologistics.com/en/contact-us/)
- [SEKO OmniParcel Tracker](https://track.omniparcel.com/)
- [universal-parcel-scraper SEKO adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/seko)
:::
