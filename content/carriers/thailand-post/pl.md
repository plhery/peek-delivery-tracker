---
title: Śledzenie Thailand Post: kody TH S10, statusy EMS i 1545
description: Śledź przesyłkę Thailand Post (ไปรษณีย์ไทย): 13-znakowe kody TH S10, statusy krajowe i EMS, pobranie COD, cło oraz Contact Center 1545.
slug: sledzenie-thailand-post
published: 2026-11-24
updated: 2026-11-24
---

**Thailand Post** (*Thailand Post Co., Ltd.*, po tajsku *บริษัท ไปรษณีย์ไทย จำกัด*) to narodowy operator pocztowy Tajlandii ([thailandpost.co.th](https://www.thailandpost.co.th/index.php?page=index&language=en)). Obsługuje krajowe listy, przesyłki polecone, EMS, Same Day, ciężkie paczki LogisPost i przesyłki za pobraniem (COD) w Tajlandii, a także usługi międzynarodowe [EMS](carrier:ems) World, Registered, ePacket, Courier Post (do 200 kg) oraz Courier Post Plus na cały świat. Publiczne śledzenie działa pod adresem [track.thailandpost.co.th](https://track.thailandpost.co.th/), a komunikaty międzynarodowe na [international.thailandpost.com](https://sites.google.com/thailandpost.com/interthp/update). Możesz sprawdzić tam tajską przesyłkę pocztową lub wkleić 13-znakowy numer `TH` w pole powyżej.

## Numery przesyłek Thailand Post (13-znakowy format `TH` S10)

Rejestrowane krajowe i międzynarodowe przesyłki Thailand Post mają 13-znakowe kody Światowego Związku Pocztowego (UPU S10): dwie wielkie litery usługi, 8 cyfr seryjnych, 1 cyfrę kontrolną S10 oraz kod kraju `TH`:

| Format (wymyślony) | Co oznacza |
| --- | --- |
| `EE482917367TH` | 13-znakowa przesyłka ekspresowa [EMS](carrier:ems) Thailand Post (prefiks `EE` + 9 cyfr z poprawną cyfrą kontrolną S10 + `TH`). |
| `RR739184627TH` | 13-znakowy list polecony lub mały pakiet Thailand Post (prefiks `RR` + 9 cyfr z poprawną cyfrą kontrolną S10 + `TH`). |

Krajowe Express/EMS, Polecone (`R...TH`), ePacket (`L...TH`) oraz prefiksy paczek krajowych (`E`, `R`, `C`, `W` itp. zakończone na `TH`) stosują tę samą 13-znakową budowę z cyfrą kontrolną S10. Zwykłych listów bez 13-znakowego kodu kreskowego nie można śledzić online. Zobacz poradnik o [formatach numerów śledzenia](guide:tracking-number-formats).

## Co oznaczają statusy śledzenia Thailand Post

Tracker Thailand Post łączy numeryczne kody `statusID` zgodne z UPU z angielskimi opisami (oraz tajskimi nazwami placówek), obejmując zarówno skany krajowe w Tajlandii, jak i międzynarodowe aktualizacje EDI przesyłane przez poczty docelowe:

| Kod i komunikat Thailand Post | Co oznacza dla Ciebie | Peek pokazuje |
| --- | --- | --- |
| `3` `Posting/Collection` | Nadana w okienku Thailand Post lub odebrana od nadawcy. | „Przyjęta przez przewoźnika” |
| `9` `Arrival at outward office of exchange`, `15` `Departure From Outward OE`, `18` `Arrival at inward office of exchange` | Obsłużona w międzynarodowym urzędzie wymiany przy eksporcie lub imporcie. | „W transporcie” |
| `23`–`27` `In transit`, `Item into sorting center`, `Arrival at post office` | W drodze między sortowniami pocztowymi i przybycie do lokalnego urzędu doręczającego. | „W transporcie” |
| `31` `Item out for physical delivery` | Dzisiaj w drodze pod adres odbiorcy z listonoszem Thailand Post. | „W doręczeniu” |
| `57` `Contact recipient` | Doręczyciel próbował zadzwonić do odbiorcy (zdarzenie bez przemieszczenia; zachowuje bieżący etap). | Zachowuje poprzedni etap |
| `32` `Item arrival at collection point for pick-up (by recipient)` | Umieszczona w automacie paczkowym lub punkcie odbioru Thailand Post, gotowa do odebrania. | „Do odbioru” |
| `34` `Scheduled for further delivery attempt today` / `Item held, addressee notified` (grupa 4) | Próba doręczenia nieudana (np. nieobecność adresata lub należne opłaty celne/COD). | „Próba doręczenia” |
| `28` `Return to the origin post office` | Zgłoszono problem; przesyłka kierowana z powrotem do urzędu nadawczego. | „Zgłoszono problem” |
| `35/L` `The sender received the returned item` | Zwrócona przesyłka przekazana z powrotem pierwotnemu nadawcy. | „Zwrócona nadawcy” |
| `35/S` `Final delivery` | Doręczona odbiorcy (przy zamówieniach COD może potem pojawić się `36` `Successfully transfer money to seller`, co nie zmienia godziny doręczenia). | „Dostarczona” |

## Pobranie COD w Tajlandii, przekazania międzynarodowe i cło

- **Eksport międzynarodowy (`...TH` za granicę):** po `15` (`Departure From Outward OE`) narodowy operator pocztowy Twojego kraju — np. [USPS](carrier:usps), [Royal Mail](carrier:royal-mail), [La Poste](carrier:la-poste), [DHL](carrier:dhl) / Deutsche Post lub [Australia Post](carrier:australia-post) — obsługuje odprawę importową i lokalne doręczenie, odsyłając skany EDI do Thailand Post.
- **Cło i VAT importowy:** paczki wysyłane z Tajlandii lub do Tajlandii podlegają przepisom celnym, VAT i opłatom kraju docelowego (w tym opłatom UE dla zamówień spoza UE). Jeśli śledzenie pokazuje `34` (`Item held, addressee notified due to Payment of charges`), sprawdź skrzynkę lub lokalną pocztę w poszukiwaniu zawiadomienia o opłacie celnej. Zobacz poradnik o [paczkach w urzędzie celnym](guide:customs).
- **Płatność przy odbiorze (COD) w Tajlandii:** dla krajowych przesyłek COD Thailand Post zapisuje `35/S` (`Final delivery`) po odebraniu i opłaceniu paczki przez odbiorcę, a następnie `36` (`Successfully transfer money to seller`) po przekazaniu pieniędzy sprzedawcy.

## Przesyłka Thailand Post opóźniona, zatrzymana lub zaginiona

- **Cisza po opuszczeniu Tajlandii:** międzynarodowe przesyłki lotnicze (szczególnie polecone `R...TH` i paczki ekonomiczne) często przez kilka dni lub tygodni nie mają nowych wpisów między `Departure From Outward OE` a `Arrival at inward office of exchange`. Sprawdzenie tego samego 13-znakowego kodu `TH` na stronie poczty docelowej często pokazuje dodatkowe szczegóły celne lub doręczenia. Zobacz poradnik o [braku aktualizacji śledzenia](guide:tracking-not-updating).
- **Przesyłki zaginione, uszkodzone lub zwrócone:** najpierw skontaktuj się z nadawcą lub platformą sprzedażową i zachowaj 13-znakowy kod oraz potwierdzenie nadania. Zobacz też [zaginiona paczka](guide:lost-parcel), [uszkodzona paczka](guide:damaged-parcel), [dostarczona, ale nieodebrana](guide:delivered-not-received) oraz [zwrócona do nadawcy](guide:returned-to-sender).

## Obsługa klienta Thailand Post i Contact Center 1545

Oficjalne kanały opublikowane na [Thailand Post Contact Us](https://www.thailandpost.co.th/un/contact_thp/un/41) i [thailandpost.co.th](https://www.thailandpost.co.th/index.php?page=index&language=en):

- **THP Contact Center (Tajlandia):** **`1545`** (pytania ogólne, poszukiwanie przesyłek i reklamacje).
- **Oficjalny portal kontaktowy i zgłoszeniowy:** [thailandpost.co.th/un/contact_thp/un/41](https://www.thailandpost.co.th/un/contact_thp/un/41).
- **Tracker online i komunikaty międzynarodowe:** [track.thailandpost.co.th](https://track.thailandpost.co.th/) oraz [international.thailandpost.com](https://sites.google.com/thailandpost.com/interthp/update).

> Uważaj na fałszywe SMS-y lub wiadomości LINE żądające drobnej opłaty przez nieznany link za paczkę Thailand Post. Wszelkie informacje o śledzeniu lub opłatach celnych weryfikuj bezpośrednio na [track.thailandpost.co.th](https://track.thailandpost.co.th/) albo w THP Contact Center `1545`. Zobacz poradnik o [fałszywych SMS-ach o paczkach](guide:delivery-scam-texts).

## Śledzenie Thailand Post w Peek

[Peek](/) automatycznie rozpoznaje Thailand Post jako jedynego kandydata dla każdego poprawnego 13-znakowego kodu S10 `TH` (`EE482917367TH` lub `RR739184627TH`, wymyślone) i bezpośrednio rozpoznaje wklejone linki `track.thailandpost.co.th/?trackNumber=`. Jeśli chcesz ręcznie zmienić przewoźnika (np. aby porównać własne skany poczty docelowej po przylocie za granicę), kliknij „Zmień” podczas dodawania lub „Zmień przewoźnika” na ekranie przesyłki.

Peek odczytuje tracker Thailand Post — a w razie awarii pierwszego serwera jego drugi serwer — i pokazuje etap („Przyjęta przez przewoźnika”, „W transporcie”, „W doręczeniu”, „Do odbioru”, „Próba doręczenia”, „Zgłoszono problem”, „Zwrócona nadawcy”, „Dostarczona”), angielską treść skanu, urząd pocztowy oraz godzinę doręczenia. Kody statusu wyznaczają etap i żadna aplikacja Peek ich nie wyświetla; nazwisko adresata pojawia się w polu „Odbiorca” na Twojej własnej stronie paczki, nigdy w udostępnionym linku, a żadna aplikacja nie pokazuje nazwiska osoby odbierającej przesyłkę. Własne skany Thailand Post zawierają przesunięcie względem UTC właściwe dla Bangkoku. Skan EDI zagranicznej poczty niesie lokalny zegar tamtejszego urzędu, który Thailand Post zapisuje jak czas Bangkoku, więc Peek datuje go według strefy czasowej tego urzędu (czas Tokio dla `TOKYO INTERNATIONAL`), skan bez miejsca według strefy sąsiedniego skanu, i wyświetla wszystko w czasie Twojego urządzenia. Peek pomija imię kuriera, numery telefonów, podpis i zamaskowaną notatkę kontaktową; Thailand Post nie podaje szacowanego dnia doręczenia.

Gdy masz przesyłkę otwartą w Peek albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza status nawet co 10 minut; w dniu, gdy Thailand Post pokazuje `31` (`Item out for physical delivery`), co 2 minuty od godz. 6:00 według zegara najnowszego skanu, dopóki tracker Thailand Post odpowiada. W pozostałym czasie Peek sprawdza co godzinę. Po 12 godzinach wyłącznie na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika” albo po dwóch dniach bez zmian sprawdza co godzinę niezależnie od sposobu śledzenia, a po siedmiu dniach na „Zarejestrowana” — co 6 godzin. Gdy tracker Thailand Post nie odpowiada i Peek śledzi przesyłkę wyłącznie przez usługi śledzące, sprawdza co godzinę, co 3 godziny po spokojnym dniu i nawet co 15 minut w dniu, w którym przesyłka jest w doręczeniu, dopóki masz ją otwartą lub włączone powiadomienia.

Jeśli najnowszy skan ma cztery dni, a przesyłka nadal widnieje jako „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie”, Peek oznacza ją dniem tego skanu, np. „Brak aktualizacji od pon 12 paź”. Na stronie przesyłki pojawia się wtedy komunikat „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika Thailand Post.” wraz z linkiem „Poproś przewoźnika Thailand Post o pomoc” do strony kontaktowej Thailand Post. Po trzech tygodniach bez nowego skanu pojawia się informacja „Od ponad trzech tygodni nic nowego. Jeśli przesyłka wciąż nie dotarła, zapytaj sprzedawcę. Peek dalej pyta przewoźnika Thailand Post.”

Możesz wybrać powiadomienia „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek monitoruje wyłącznie aktualizacje śledzenia i nie może odprawić paczki celnie, opłacić pobrania COD ani skontaktować się z Thailand Post za Ciebie.

## Pytania o śledzenie Thailand Post

### Jak wygląda numer śledzenia Thailand Post?

Rejestrowane przesyłki Thailand Post używają 13-znakowych kodów UPU S10 złożonych z dwóch liter usługi, 9 cyfr (z cyfrą kontrolną na końcu) oraz `TH` — na przykład `EE482917367TH` dla EMS lub `RR739184627TH` dla listu poleconego (wymyślone).

### Co oznacza kod `36` (`Successfully transfer money to seller`)?

W krajowych przesyłkach za pobraniem (COD) w Tajlandii kod `36` potwierdza, że Thailand Post przekazało sprzedawcy pobraną kwotę po końcowym doręczeniu (`35/S`). To zdarzenie finansowe, a nie ruch paczki.

### Dlaczego moja przesyłka Thailand Post przestała się aktualizować po `Departure From Outward OE`?

Przesyłka opuściła wychodzący międzynarodowy urząd wymiany w Tajlandii i znajduje się w transporcie lotniczym lub oczekuje na przyjęcie importowe u operatora pocztowego w kraju docelowym.

### Jak skontaktować się z obsługą klienta Thailand Post?

Zadzwoń do THP Contact Center pod numer **`1545`** w Tajlandii albo skorzystaj z oficjalnej strony kontaktowej [thailandpost.co.th/un/contact_thp/un/41](https://www.thailandpost.co.th/un/contact_thp/un/41).

:::sources
- [Thailand Post: Official Website](https://www.thailandpost.co.th/index.php?page=index&language=en) – *Thailand Post Co., Ltd.*, krajowe/międzynarodowe EMS, Registered, Courier Post, LogisPost i COD
- [Thailand Post: Contact Us & THP Contact Center 1545](https://www.thailandpost.co.th/un/contact_thp/un/41) – oficjalne wsparcie klienta, portal reklamacji i infolinia `1545` (strona pomocy linkowana w Peek)
- [Thailand Post: Public Tracker](https://track.thailandpost.co.th/) – oficjalna wyszukiwarka śledzenia online
- [Thailand Post: International Service Advisories](https://sites.google.com/thailandpost.com/interthp/update) – komunikaty śledzenia i usług międzynarodowych
- [Universal Parcel Scraper: thailand-post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/thailand-post) – walidacja cyfry kontrolnej S10 `TH`, zapasowy drugi host, mapa `statusID` UPU (`3`–`36`, `57`), czas Bangkoku vs zagraniczne EDI i odczyt odbiorcy
:::
