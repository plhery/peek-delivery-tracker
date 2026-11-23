---
title: Śledzenie Purolator: PIN, statusy i odbiór paczek
description: Śledź paczkę Purolator w Kanadzie: format numeru PIN, znaczenie statusów, odbiór po nieudanej próbie doręczenia, cło z USA i numer kontaktu.
slug: sledzenie-purolator
published: 2026-11-23
updated: 2026-11-23
---

Purolator to kanadyjska firma kurierska i spedycyjna należąca większościowo do Canada Post. Paczkę sprawdzisz po numerze PIN na stronie [purolator.com](https://www.purolator.com/en/shipping/tracker) albo wklejając go w pole powyżej. PIN przekazuje nadawca — przy zakupach online znajdziesz go w potwierdzeniu wysyłki ze sklepu.

## Numer śledzenia Purolator: czym jest PIN i jak wygląda

Purolator nazywa swój numer śledzenia PIN (Package Identification Number). Każda przesyłka otrzymuje własny PIN, który Purolator traktuje jako numer referencyjny przy każdym kontakcie. PIN i numer śledzenia to zatem dokładnie to samo.

| Format (wymyślony) | Co to jest |
| --- | --- |
| `482917364051` | 12 cyfr; Peek sprawdza ostatnią jako cyfrę kontrolną. |
| `BKM482917364` | 3 litery i 9 cyfr; śledzenie Purolator także opisuje ten kształt jako PIN. |

Wyszukiwarka Purolator przyjmuje również numer referencyjny nadawcy do 15 znaków, jeśli został dodany (np. numer zamówienia lub kod) — działa tak samo jak PIN.

Dwanaście cyfr to popularna długość: korzystają z niej także DPD France, Mondial Relay, Colis Privé, BRT, FedEx i kilku przewoźników chińskich, więc sprawdź przewoźnika w e-mailu ze sklepu. Numer 16-cyfrowy nigdy nie należy do Purolator (często jest to [Canada Post](carrier:canada-post)), a 13 znaków kończących się na `CA` (`RR482917367CA`) należy do Canada Post. Zobacz [formaty numerów śledzenia](guide:tracking-number-formats).

W trakcie transportu przesyłka może otrzymać nowy PIN — historia pokazuje wtedy skan z nowym numerem.

## Co oznaczają statusy śledzenia Purolator

Strona Purolator wyświetla nagłówek (`Shipment Created`, potem `Delivered`) nad listą skanów. Poniżej angielskie opisy (wersja francuska pokazuje opisy po francusku):

| Status Purolator | Co oznacza dla ciebie |
| --- | --- |
| `Shipper created a label` | Nadawca utworzył etykietę. Purolator jeszcze nie ma paczki. |
| `Label information electronically submitted` | To samo: dotarły dane etykiety, ale jeszcze nie sama paczka. |
| `Picked up by Purolator` | Odebrana przez Purolator. |
| `Arrived at sort facility` | Dotarła do terminala Purolator; lokalizacja to miasto i prowincja terminala. |
| `Departed sort facility` | Opuściła terminal w drodze do kolejnego albo do lokalnego oddziału. |
| `Delayed in transit due to missed connection` | Opóźnienie w sieci Purolator; przesyłka nadal jedzie. |
| `Cleared customs` | Z zagranicy: zwolniona przez cło i z powrotem w drodze. |
| `On vehicle for delivery` | Dziś w samochodzie w doręczeniu. |
| `Attempted delivery - receiver unavailable` | Kurier był na miejscu, lecz nikogo nie zastał. |
| `Address correction required - missing entry code` | Kurier nie mógł wejść do budynku bez kodu dostępu. |
| `Available for pickup for 5 business days from arrival date at the counter` | Czeka na odbiór w punkcie. |
| `Shipment delivered` | Doręczona tobie lub odebrana w punkcie. |

`Delayed in transit due to rail delay` działa jak utracone połączenie: to opóźnienie sieci Purolator, z samą paczką nie dzieje się nic złego. `Address correction required - resolution in progress` oznacza poprawę adresu; zwykle po tym pojawia się `Shipment scheduled for delivery next business day`.

`Shipment unavailable - En route to Purolator pickup location` oznacza transport do punktu odbioru — paczkę odbierzesz dopiero po skanie `Available for pickup…`.

## Próba doręczenia, odbiór i podpisy w Purolator

Gdy nikogo nie ma w domu, śledzenie zwykle pokazuje `Attempted delivery - receiver unavailable`, następnie `Available for pickup for 5 business days from arrival date at the counter` w tym samym mieście oraz `Shipment delivered` po odbiorze. Termin 5 dni roboczych widnieje w samym opisie statusu, więc nie zwlekaj z odbiorem.

Punkty przechowujące paczki to Purolator Shipping Centres, autoryzowani agenci i punkty PostNet. Przewodnik usług Purolator wymienia ponad 130 Shipping Centres i ponad 550 autoryzowanych agentów w Kanadzie — znajdziesz je przez [wyszukiwarkę placówek](https://www.purolator.com/fr/expedition/trouver-un-centre-dexpedition).

Wymóg podpisu zależy od opcji nadawcy:

- **Bez podpisu** to standard dla adresów prywatnych. „Signature required” nadawca wybiera opcjonalnie.
- **Adult signature required:** przy drzwiach potrzebny jest urzędowy dokument ze zdjęciem potwierdzający wiek. Bez niego kurier nie wyda paczki i trafi ona do Shipping Centre lub agenta, gdzie wymagany jest dokument ze zdjęciem i aktualnym adresem. Takich paczek nie zostawia się pod drzwiami.

Nadawca może też wybrać „Hold for pickup”, kierując paczkę bezpośrednio do oddziału Purolator zamiast pod twój adres.

## Śledzenie Purolator nie działa, paczka opóźniona lub zaginiona

Brak wyników oznacza zazwyczaj, że Purolator jeszcze nie zeskanował przesyłki: pomoc Purolator wskazuje, że dane pojawiają się dopiero po fizycznym przyjęciu i skanie. Sprawdź też cyfry — jedna pomyłka daje brak wyniku.

Opóźnienia mają własne skany (utracone połączenie, opóźnienie kolejowe); starsza pomoc wspomina też o opóźnieniach pogodowych i mechanicznych. Paczka kontynuuje drogę. Zobacz [śledzenie nie aktualizuje się](guide:tracking-not-updating).

Zaginięcie, uszkodzenie albo status doręczenia bez paczki? Najpierw skontaktuj się ze sklepem: to nadawca ma umowę z Purolator i wskaże tryb reklamacji.

Towary przesyłane kurierem z USA lub Meksyku mogą korzystać ze zwolnienia z cła do wartości 150 CAD oraz z podatków od sprzedaży i akcyzy do 40 CAD według zasad Canada Border Services Agency (CBSA). Niedozwolone jest dzielenie zamówienia na kilka paczek, aby zmieścić się w limitach. Należności płaci importer, a CBSA nie reguluje opłat maklerskich (brokerage fees) — ustala je sam kurier. Zobacz [paczki zatrzymane na cle](guide:customs).

## Numer obsługi klienta Purolator

Numer Purolator w Kanadzie według przewodnika usług obowiązującego od 30 czerwca 2026 roku to **1 888 SHIP-123 (1 888 744-7123)**. Przygotuj swój PIN — Purolator pyta o niego przy każdym zgłoszeniu.

Dostałeś SMS o próbie doręczenia z linkiem? Nie klikaj — wpisz PIN samodzielnie na purolator.com. Rząd Kanady zaleca przesyłanie spamowych SMS-ów na numer 7726 (SPAM).

## Śledzenie paczek Purolator w Peek

[Peek](/) sprawdza 3 litery i 9 cyfr (poza kilkoma prefiksami innych firm) jednocześnie w Colis Privé, TNT Australia i Purolator, nie przesądzając przewoźnika po samym wzorze; gdy Purolator potwierdzi paczkę, zobaczysz „Purolator ma tę przesyłkę.”. Dwanaście cyfr także się powtarza: przy poprawnej cyfrze kontrolnej Peek pyta sześciu przewoźników jednocześnie — Purolator (na czele dla odbiorców z Kanady, USA i Wielkiej Brytanii), BRT, Colis Privé, Sending, Paquetexpress i Metropolitan Warehouse — i wybiera tego, który zna przesyłkę. Jeśli odpowie kilka różnych marek lub żadna, wybierz Purolator przez „Zmień”. Działają też linki z purolator.com z parametrem `pin=`, `pins=` lub `searchValue=`.

Peek pokazuje status, każdy skan z miastem, prowincją i krajem na mapie, przewidywany dzień doręczenia, kraj docelowy oraz wagę przy przesyłkach jednopaczkowych. Statusy zachowują angielskie brzmienie Purolator. Pomijane jest nazwisko odbiorcy oraz nazwa konkretnego punktu (widać tylko komunikat `Available for pickup…`), a zmiany doręczenia w Peek nie są możliwe.

Godziny wyświetlają się w strefie czasowej twojego urządzenia. Przy przesyłkach transgranicznych Purolator przesyła je bez strefy czasowej: Peek odczytuje je wtedy według czasu miasta skanu i zachowuje historię Purolator. Gdy strona Purolator odpowie kontrolą bezpieczeństwa zamiast danych, wspomagają ją usługi śledzenia. Dopóki masz paczkę otwartą w Peek albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza status do co 10 minut — a od 6:00 czasu lokalnego w dniu, gdy Purolator zeskanuje paczkę w doręczeniu, co 2 minuty, dopóki odpowiada własne śledzenie Purolator (przez usługi śledzenia w tych samych warunkach do co 15 minut). Po dwóch dniach bez zmian (12 godzinach, gdy przesyłka nadal ma status „Zarejestrowana” lub „Przyjęta przez przewoźnika”) Peek sprawdza co godzinę, a po siedmiu dniach na etapie „Zarejestrowana” — co 6 godzin. Jeśli paczka na etapie „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie” nie ma nowego skanu przez cztery dni, Peek pokazuje „Brak aktualizacji od czterech dni” (lub „Brak aktualizacji od…” z datą), komunikat „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika Purolator.” oraz link „Poproś przewoźnika Purolator o pomoc”. Peek wysyła powiadomienia w trybie „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”.

## Pytania o śledzenie Purolator

### Czy PIN Purolator to to samo co numer śledzenia?

Tak. PIN to nazwa, jaką Purolator określa numer śledzenia — zazwyczaj 12 cyfr albo 3 litery i 9 cyfr.

### Dlaczego mój numer śledzenia Purolator nie działa?

Najczęściej Purolator nie zeskanował jeszcze paczki: śledzenie rusza po fizycznym przyjęciu. W przeciwnym razie sprawdź literówki, potwierdź przewoźnika w sklepie albo poszukaj nowego PIN-u w historii.

### Jak długo Purolator przechowuje paczkę do odbioru?

5 dni roboczych od dnia dotarcia do punktu, zgodnie ze statusem `Available for pickup for 5 business days from arrival date at the counter`. Zabierz dokument ze zdjęciem, jeśli wymagany jest podpis osoby dorosłej.

### Czy można śledzić Purolator bez numeru śledzenia?

Wyłącznie po numerze referencyjnym nadawcy, o ile go ustawił — wyszukiwarka Purolator traktuje go jak PIN. Inaczej poproś sklep o PIN.

:::sources
- [Purolator: Tracker](https://www.purolator.com/en/shipping/tracker) – formaty PIN, brzmienia statusów, nagłówki, 5 dni roboczych przechowania
- [Purolator: Trouver un centre d'expédition](https://www.purolator.com/fr/expedition/trouver-un-centre-dexpedition) – wyszukiwarka placówek
- [Purolator: Guide des services de Purolator](https://web.purolator.com/app/guides/pdf/fr/Purolator_Service_Guide_French.pdf) – telefon, Shipping Centres i agenci, Hold for pickup, podpisy, podpis dorosłego
- [Purolator: Tracking Details](https://eshiponline.purolator.com/ShipOnline/Help/Default/trackingdetails_E.htm) – brak skanów przed odbiorem, nowy PIN, opóźnienia pogodowe i mechaniczne
- [Purolator: Tracking - By PIN / By Reference](https://eshiponline.purolator.com/ShipOnline/Help/Default/trackingRef_E.htm) – śledzenie po PIN i 15-znakowej referencji
- [Purolator: Preparing a Shipment - Shipment Confirmation](https://eshiponline.purolator.com/ShipOnline/Help/Default/shipstep3_E.htm) – definicja PIN
- [CBSA: Memorandum D8-2-16](https://www.cbsa-asfc.gc.ca/publications/dm-md/d8/d8-2-16-eng.html) – progi 150 CAD i 40 CAD, zakaz dzielenia zamówień
- [CBSA: Step 4, import guide](https://www.cbsa-asfc.gc.ca/import/guide-4-eng.html) – opłaty po stronie importera, nieregulowane opłaty maklerskie
- [Government of Canada: Mobile spam](https://ised-isde.canada.ca/site/canada-anti-spam-legislation/en/hung-mobile-spam) – zgłaszanie spamu SMS na 7726
- [Wikipedia: Purolator Inc.](https://en.wikipedia.org/wiki/Purolator_Inc) – większościowy udział Canada Post
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – dane czytane przez Peek, cyfry kontrolne, kandydaci i usługi śledzenia
:::
