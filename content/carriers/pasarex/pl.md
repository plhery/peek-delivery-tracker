---
title: Śledzenie PasarEX: kody Amazon AMZPSR, cło i pomoc
description: Śledź import PasarEX (Pasar Express) w Kolumbii, Chile, Panamie i na Barbadosie: kody AMZPSR, statusy celne, zasady PQR oraz numery telefonów.
slug: sledzenie-pasarex
published: 2026-11-24
updated: 2026-11-24
---

PasarEX (marka handlowa kolumbijskiej spółki *PASAR EXPRESS SAS* założonej w 1993 roku) oferuje przesyłki ekspresowe, fracht lotniczy oraz doręczenia ostatniej mili dla platform e-commerce. Jej serwisy obejmują **Kolumbię**, **Chile**, **Panamę** i **Barbados**, a oferta obejmuje centrum logistyczne w Miami oraz przewozy multimodalne z Chin.

PasarEX dokonuje odprawy celnej przesyłek jako agent celny, dlatego jego śledzenie obejmuje drogę od manifestu po przybycie skonsolidowanego importu, odprawę celną, magazyny i lokalne doręczenie.

## Formaty numerów śledzenia PasarEX

Peek przyjmuje numery PasarEX złożone z 6 do 30 liter i cyfr (z co najmniej jedną cyfrą). Jeden wzorzec należy wyłącznie do PasarEX:

| Format (wymyślony) | Co to jest |
| --- | --- |
| `AMZPSR482917360` | `AMZPSR` + 9 cyfr (15 znaków) — numer Amazon wyszukiwany przez tracker PasarEX; żaden inny przewoźnik w Peek nie ma tego wzorca. |

Jeśli potwierdzenie zamówienia zawiera bezpośredni adres URL (`tracking.pasarex.com/?awb=...`), wklejenie go do Peek wyciąga list przewozowy i natychmiast wybiera PasarEX. Inne wzorce e-commerce opisuje [przewodnik po formatach numerów przesyłek](guide:tracking-number-formats) oraz [poradnik o zamówieniach Amazon](guide:amazon-orders).

## Co oznaczają statusy śledzenia PasarEX

PasarEX grupuje wiersze skanów pod ogólnymi nagłówkami (`COMMENTS`, `IN CUSTOMS PROCESS`, `RECEIVED FOR DELIVERY`, `IN TRANSIT`, `IN WAREHOUSE`, `OUT FOR DISTRIBUTION`, `DELIVERED`, `SHIPPING WITH DELIVERY ISSUE`), z pojedynczymi zdaniami skanów po hiszpańsku lub angielsku wraz z miastem i datą/godziną.

| Opis skanu PasarEX | Co oznacza |
| --- | --- |
| `Orden de pedido recibida - paquete no arribado` / `Information (Manifest) of the shipment received.` | Elektroniczne dane manifestu dotarły do PasarEX przed fizycznym przybyciem paczki do hubu importowego. |
| `Carga consolidada para importación recibida` / `Reception of consolidated cargo in import.` | Skonsolidowany ładunek lotniczy dotarł do bramy wjazdowej PasarEX (np. w Bogocie) przed odprawą celną. |
| `Entry to station for customs processing.` | Paczka trafiła na stanowisko celne do odprawy importowej. |
| `Cleared by customs by PasarEx.` / `Cleared by customs and transferred to OPS.` | PasarEX odprawił przesyłkę celnie i przekazał ją do operacji krajowych. |
| `Ingresó a bodega` / `Package received in warehouse.` / `Reception of consolidated cargo at a station.` | Przyjęta w magazynie PasarEX lub stacji docelowej między odcinkami trasy krajowej. |
| `Package in transit` / `Package in ground transit` | W transporcie drogowym lub krajowym między stacjami PasarEX. |
| `Applies to cargo that may experience delivery delays due to disturbances or protests` | Komunikat o możliwych opóźnieniach wskutek lokalnych zakłóceń; przesyłka rusza dalej po ich ustaniu. |
| `Shipment left to local route for final delivery` | Wyjechała dziś na lokalną trasę z kurierem do doręczenia końcowego. |
| `Shipment delivered in perfect condition` | Doręczona pod adres odbiorcy. |

## Odprawa celna, reklamacje PQR i oczekiwany przebieg dostawy

Ponieważ PasarEX odprawia import transgraniczny bezpośrednio jako agent celny przed krajową dystrybucją:

- **Zanim paczka przyleci:** dopóki śledzenie pokazuje wyłącznie `Information (Manifest) of the shipment received.` albo `Orden de pedido recibida - paquete no arribado`, przesyłka została zgłoszona, lecz fizycznie nie dotarła jeszcze do PasarEX. Spodziewaj się przerwy do pojawienia się `Reception of consolidated cargo in import.` lub `Carga consolidada para importación recibida`. Zobacz [przewodnik celny](guide:customs) oraz [gdy śledzenie przestaje się aktualizować](guide:tracking-not-updating).
- **Zasady PQR i odszkodowań w Kolumbii (uchwała CRC 5050 z 2016 r.):** na stronie [PasarEX Colombia Support](https://pasarex.com/co/soporte/) Pasar Express SAS podaje ustawowe terminy reklamacji z ustawy 1369/2009 i uchwały 5050/2016. Odbiorcy muszą zgłosić ubytek lub uszkodzenie w ciągu **5 dni roboczych** od odbioru paczki, natomiast nadawcy mają **10 dni kalendarzowych** (krajowe) lub **6 miesięcy** (międzynarodowe) na zgłoszenie zaginięcia, ubytku lub uszkodzenia (umowy firmowe mogą mieć własne warunki). Reklamacje wymagają kopii lotniczego listu przewozowego (*Guía Aérea*), faktury handlowej, faktury Pasar Express, spisu zawartości, opisu opakowania i zdjęć. PasarEX rozpatruje PQR zazwyczaj w ciągu **15 dni roboczych** (z możliwością przedłużenia przy postępowaniu wyjaśniającym), a użytkownicy mają **10 dni roboczych** od zawiadomienia na odwołanie (*recurso de reposición y en subsidio de apelación*).

## Śledzenie PasarEX w Peek

Wklej swój numer albo link `tracking.pasarex.com/?awb=...` do Peek, który obsługuje ponad 4 500 przewoźników. Link od razu kieruje do PasarEX. Przy gołym numerze Peek nigdy sam nie pyta PasarEX: może zapytać innych przewoźników o tym samym kształcie, potem odpytuje usługi śledzące („Sprawdzanie usług śledzenia…”) i przypisuje przesyłkę do PasarEX tylko wtedy, gdy usługa śledząca wskaże wyłącznie PasarEX z datowanymi krokami. Stuknij więc „Zmień” i wybierz PasarEX (w sekcji „Pasują do numeru” dla kodu `AMZPSR`) lub użyj „Zmień przewoźnika” na już dodanej przesyłce.

Tracker PasarEX wymaga zaznaczenia reCAPTCHA przy każdym zapytaniu, dlatego Peek odczytuje go przez otwartoźródłowy [adapter PasarEX w universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pasarex) z pomocą przeglądarki. Peek pobiera opis, miasto i godzinę każdego skanu i układa je od najnowszego zamiast według nagłówków PasarEX. PasarEX podaje czasy lokalne bez przesunięcia względem UTC: Peek przypisuje je do strefy czasowej miasta (czas nowojorski dla Miami, czas Bogoty dla Bogoty, Medellín i Cali oraz czas Santiago, Panamy albo Barbadosu dla tych miejsc), skan bez miasta do strefy sąsiedniego skanu, a w ostateczności do czasu Bogoty, i wyświetla wszystko w czasie Twojego urządzenia. Tracker PasarEX nie publikuje szacowanej daty doręczenia, wagi ani potwierdzenia doręczenia.

Peek przekłada zdania PasarEX na przejrzyste etapy: **Zarejestrowana** dla odbioru manifestu przed fizycznym przybyciem, **W transporcie** od przyjęcia skonsolidowanego importu przez zwolnienie celne, magazyn i transport drogowy (opisy PasarEX przechodzą bezpośrednio z manifestu do przylotu importowego bez osobnego skanu **Przyjęta przez przewoźnika**), **W urzędzie celnym** podczas odprawy celnej, **W doręczeniu** przy wyjeździe na trasę lokalną oraz **Dostarczona** po doręczeniu.

Peek sprawdza tracker PasarEX najwyżej raz na godzinę — najkrótszy odstęp dopuszczany przez PasarEX między dwoma sprawdzeniami, także po nieudanej próbie, niezależnie od tego, czy masz przesyłkę otwartą; w dniu, w którym przesyłka jest w doręczeniu, nie sprawdza co 2 minuty. Po siedmiu dniach na etapie „Zarejestrowana” sprawdza co 6 godzin. Gdy tracker PasarEX nie odpowiada i Peek śledzi przesyłkę wyłącznie przez usługi śledzące, sprawdza co godzinę i co 3 godziny po spokojnym dniu.

Jeśli najnowszy skan ma cztery dni, a przesyłka nadal widnieje jako „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie”, Peek oznacza ją dniem tego skanu, np. „Brak aktualizacji od pon 12 paź”. Na stronie przesyłki pojawia się wtedy komunikat „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika PasarEX.” Po trzech tygodniach bez nowego skanu pojawia się informacja „Od ponad trzech tygodni nic nowego. Jeśli przesyłka wciąż nie dotarła, zapytaj sprzedawcę. Peek dalej pyta przewoźnika PasarEX.”

Wybierz **Każdy skan**, **Ważne etapy** lub **Tylko doręczenie** w ustawieniach powiadomień Peek, aby zdecydować, które aktualizacje mają wysyłać alerty.

## Kontakt z PasarEX

PasarEX publikuje numery telefonów i adresy e-mail dla każdego kraju:

- **Kolumbia ([pasarex.com/co/soporte](https://pasarex.com/co/soporte/)):** `+57 601 384 7000`, `+57 601 390 6000` (również linia PQR w Bogocie), `+57 601 291 6500`, `+57 300 919 7521`, `+57 300 919 7532`; e-mail `sac@pasar.net` (wsparcie) i `pqr@pasar.net` (PQR/reklamacje)
- **Chile ([pasarex.com/cl](https://pasarex.com/cl/)):** `+56 2 2571 7500`; e-mail `clgestionpe@pasar.net`
- **Panama ([pasarex.com/pa](https://pasarex.com/pa/)):** `+507 836 0399`; e-mail `pagestionpe@pasar.net` i `pqr@pasar.net`
- **Barbados:** `+1 246 4370277`, `+1 305 7042435`
- **Informacje o firmie:** [PasarEX Colombia](https://pasarex.com/co/) oraz [Nosotros](https://pasarex.com/co/nosotros/)

## Najczęstsze pytania

### Czym jest numer śledzenia `AMZPSR`?

`AMZPSR` i 9 cyfr (łącznie 15 znaków, np. `AMZPSR482917360`) to numer Amazon obsługiwany przez tracker PasarEX. Żaden inny przewoźnik w Peek nie używa tego wzorca, lecz Peek sam nie pyta PasarEX: wybierz PasarEX w sekcji „Pasują do numeru”.

### Dlaczego moja paczka PasarEX pokazuje `Orden de pedido recibida - paquete no arribado`?

Oznacza to, że PasarEX otrzymał manifest przesyłki, ale fizyczna paczka nie dotarła jeszcze do docelowej stacji importowej PasarEX. Skany ruszają po przylocie skonsolidowanego lotu.

### Dlaczego Peek odświeża PasarEX rzadziej niż niektórych innych przewoźników?

PasarEX pozwala sprawdzać swój tracker co najwyżej raz na godzinę, również po błędzie, dlatego Peek odpytuje go raz na godzinę. Tracker wymaga ponadto zaznaczenia reCAPTCHA przy każdym zapytaniu.

### Ile mam czasu na zgłoszenie uszkodzenia przesyłki w PasarEX w Kolumbii?

Zgodnie z kolumbijską uchwałą CRC 5050/2016 podaną na [pasarex.com/co/soporte](https://pasarex.com/co/soporte/) odbiorcy mają **5 dni roboczych** od doręczenia na zgłoszenie uszkodzenia lub braków, natomiast nadawcy mają 10 dni kalendarzowych (krajowe) albo 6 miesięcy (międzynarodowe) na zgłoszenie zaginięcia lub szkody.

:::sources
- [PasarEX Colombia](https://pasarex.com/co/)
- [PasarEX Colombia — Soporte y PQR](https://pasarex.com/co/soporte/)
- [PasarEX Colombia — Nosotros](https://pasarex.com/co/nosotros/)
- [PasarEX Chile](https://pasarex.com/cl/)
- [PasarEX Panamá](https://pasarex.com/pa/)
- [adapter PasarEX w universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pasarex)
:::
