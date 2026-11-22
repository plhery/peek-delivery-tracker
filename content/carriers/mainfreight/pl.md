---
title: Śledzenie Mainfreight: numery listów i statusy
description: Śledź przesyłkę Mainfreight w Nowej Zelandii, Australii, USA lub Europie: formaty numerów, statusy po polsku, cło, reklamacje i Peek.
slug: sledzenie-mainfreight
published: 2026-11-22
updated: 2026-11-22
---

Mainfreight przewozi ładunki drogą lądową, lotniczą i morską. Katalog Peek przypisuje go do Nowej Zelandii (jego kraju macierzystego), Australii i Stanów Zjednoczonych; jego własny system śledzi też transport drogowy w Europie oraz ruch kontenerów. Aby śledzić przesyłkę lub duży towar wysłany przez Mainfreight, wklej numer listu od zlecającego w pole powyżej. Własna wyszukiwarka Mainfreight działa na www.mainfreight.com.

## Numer śledzenia Mainfreight: jak wygląda

Z wymyślonymi cyframi:

| Numer (wymyślony) | Co to jest |
| --- | --- |
| `48291736542` | 11 cyfr: przesyłka drogowa w Nowej Zelandii, Australii lub USA — jedyny format, jaki katalog Peek przypisuje do Mainfreight. |
| Litery i cyfry | Numer zlecenia (`S` i 8 cyfr dla lotu/morza, `CJ` i 9 cyfr dla kontenerów), house bill albo numer referencyjny nadawcy. Po wybraniu Mainfreight Peek odpytuje kody o długości od 4 do 40 znaków. |
| Master bill | 11 cyfr lotniczego listu przewozowego lub głównego konosamentu morskiego: Peek odnajdzie zlecenie lotnicze lub morskie, jeśli odpowiada mu tylko jedno zlecenie. |

Katalog Peek rozpoznaje Mainfreight również pod nazwami Mainfreight Group, Mainfreight Transport i Mainfreight Air & Ocean.

**Gdzie go znaleźć.** W wiadomości o wysyłce lub na stronie zamówienia; przy transporcie między firmami zapytaj nadawcę. Zobacz [jak znaleźć numer przesyłki](guide:find-tracking-number).

**Inni przewoźnicy z 11 cyframi.** Jedenaście cyfr pasuje też do [GLS Germany](carrier:gls-de), [GLS France](carrier:gls-fr), [DHL eCommerce Poland](carrier:dhl-ecommerce-pl), PostLogistics i innych. Jeśli Twój e-mail wymienia któregoś z nich, śledź go tam. Więcej w [formaty numerów przesyłek](guide:tracking-number-formats).

## Co znaczą statusy śledzenia Mainfreight

**Transport drogowy w Nowej Zelandii i Australii:**

| Mainfreight podaje | Co to oznacza dla Ciebie | Peek pokazuje |
| --- | --- | --- |
| `Information Received` | Mainfreight ma dane, ale jeszcze nie towar. | „Zarejestrowana” |
| `Pickup Allocated to Driver` | Kierowca ma przydzielony odbiór; towar wciąż jest u nadawcy. | „Zarejestrowana” |
| `Picked Up` | Odebrano od nadawcy. | „Przyjęta przez przewoźnika” |
| `In Transit` | W drodze. | „W transporcie” |
| `At Delivery Depot` | W oddziale odpowiedzialnym za doręczenie. | „W transporcie” |
| `On Delivery Vehicle` | Na ciężarówce do doręczenia. | „W doręczeniu” |
| `Delivered` | Doręczono. | „Dostarczona” |

**Transport drogowy w Stanach Zjednoczonych.** Amerykańskie wpisy dopisują miejscowość za treścią; Peek wyświetla ją obok etapu.

| Mainfreight podaje | Co to oznacza dla Ciebie | Peek pokazuje |
| --- | --- | --- |
| `Booked` | Transport zarezerwowany. | „Zarejestrowana” |
| `Pickup assigned to driver` | Kierowca przydzielony do odbioru. | „Zarejestrowana” |
| `Goods picked up` | Odebrano od nadawcy. | „Przyjęta przez przewoźnika” |
| `Departed from Terminal`, `Arrived at Terminal` | Wyjechała z terminalu lub dotarła do terminalu. | „W transporcie” |
| `On drivers vehicle en-route` | Na samochodzie w drodze do Ciebie. | „W doręczeniu” |
| `Goods delivered` | Doręczono. | „Dostarczona” |
| `Collected by Customer` | Odebrana przez klienta w terminalu. | „Dostarczona” |

**Przesyłki lotnicze i morskie:**

| Mainfreight podaje | Co to oznacza dla Ciebie | Peek pokazuje |
| --- | --- | --- |
| `Booking Confirmation Sent` | Rezerwacja potwierdzona. | „Zarejestrowana” |
| `Pickup Cartage Advised` | Zlecono samochód po odbiór towaru. | „Zarejestrowana” |
| `Pick up Cartage Complete` | Towar odebrano do eksportu. | „Przyjęta przez przewoźnika” |
| `Origin Customs Cleared` | Odprawa eksportowa zakończona przed wyjazdem. | „W transporcie” |
| `Departure from First Load Port`, `Arrival at Final Discharge Port` | Wyjście z pierwszego portu/lotniska lub przybycie do ostatniego. | „W transporcie” |
| `Customs Clearance Commenced` | Rozpoczęto odprawę celną w imporcie. | „W urzędzie celnym” |
| `Customs Clearance Finalised` | Odprawiono i ponownie w drodze. | „W transporcie” |
| `Cargo Available` | Zwolniony ładunek w miejscu przeznaczenia przed dostawą. | „W transporcie” |
| `Cargo Delivered`, `Full Container Delivered` | Doręczono. | „Dostarczona” |

Planowane wyjścia i przybycia oznaczone jako szacowane (`(Estimated)`) jeszcze nie nastąpiły i Peek je pomija.

**Inne etapy.** Przemieszczenia kontenera między obiektami, jak `Picked Up (Site Movement)`, pokazują „W transporcie”, a `Final Delivery (Empty to Customer)` — „Dostarczona”. W europejskim transporcie drogowym `Order received` daje „Zarejestrowana”, `Shipment loaded into line haul truck` „W transporcie”, a `Out for delivery` „W doręczeniu”. Gdy brak pojedynczych wpisów, Peek korzysta ze statusu ogólnego, np. `Created` lub `Final Delivery`.

Kod Peek nie ma własnych komunikatów Mainfreight dla nieudanej dostawy, problemu czy zwrotu. Inne treści podlegają ogólnym regułom Peek lub pokazują się w oryginale bez zmiany etapu. Więcej w [co znaczą statusy przesyłek](guide:tracking-statuses).

## Jak doręcza Mainfreight

Peek nie zdołał odczytać strony internetowej Mainfreight, więc nie podaje tu czasów dostawy, umawiania wizyt, liczby prób ani zasad odbioru w magazynie. Zapytaj zlecającego transport, jak i kiedy ładunek trafi pod Twoje drzwi.

Kod Peek nie przekazuje przesyłek Mainfreight żadnemu innemu przewoźnikowi. System może podać przewidywany dzień dostawy dla zleceń lotniczych, morskich i europejskiego transportu drogowego — Peek pokazuje go aż do wydania do doręczenia. Nie przekazuje Peek dowodu doręczenia ani nazwiska osoby odbierającej.

**Cło.** Dla towarów wwożonych do Nowej Zelandii NZ Customs podaje:

- Towary kupione na zagranicznych stronach za 1000 NZ$ lub mniej nie podlegają cłu, a zagraniczny sprzedawca pobiera podatek GST, chyba że są częścią większej przesyłki przekraczającej 1000 NZ$. Tytoń i alkohol podlegają cłu i GST niezależnie od wartości.
- GST wynosi 15% wartości powiększonej o ewentualne cło, fracht i ubezpieczenie. Na import niezwolniony nakładana jest opłata „Customs and MPI Goods Levy”; opłaca ją agent celny lub spedytor i może obciążyć nią odbiorcę.
- Przesyłki o wartości 1000 NZ$ i wyższej wymagają numeru Customs Number, a urząd celny nie wyda towaru przed uiszczeniem opłat.

W innych krajach zobacz [paczka zatrzymana na cle](guide:customs) i ustal ze zlecającym, kto pokrywa należności.

## Przesyłka Mainfreight opóźniona, zatrzymana, uszkodzona lub zaginiona

**Jeszcze brak danych.** Przesyłka zapisana w Mainfreight, której system jeszcze nie pokazuje, wyświetla „Oczekiwanie na przewoźnika Mainfreight”, a Peek sprawdza dalej. Wklejone 11 cyfr bez odpowiedzi żadnego przewoźnika pokazują „Brak historii śledzenia”. Sprawdź cyfry i zapytaj nadawcę, czy Mainfreight już odebrał ładunek.

**Brak ruchu.** Po czterech dniach bez nowego wpisu przy statusie „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie” Peek pokazuje na liście dzień ostatniego skanu („Brak aktualizacji od …”), a na karcie paczki „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika Mainfreight.” z linkiem „Poproś przewoźnika Mainfreight o pomoc” do formularza kontaktu wyłącznie w tej informacji. Zapytaj sklep lub nadawcę. Zobacz [śledzenie nie aktualizuje się](guide:tracking-not-updating).

**Uszkodzona, niekompletna lub zaginiona.** Najpierw poinformuj sklep lub nadawcę, który zamówił transport. W Nowej Zelandii i Australii obowiązują warunki Mainfreight i lokalne prawo. W międzystanowym transporcie drogowym w USA prawo federalne stanowi:

- Przewoźnik odpowiada za rzeczywistą stratę lub szkodę; odpowiedzialność może być ograniczona do wartości zadeklarowanej przez nadawcę lub uzgodnionej pisemnie. Nie może dać mniej niż 9 miesięcy na złożenie reklamacji ani mniej niż 2 lata na pozew od dnia pisemnego odrzucenia jakiejkolwiek jej części.
- Reklamacja to pisemne żądanie wskazujące przesyłkę, odpowiedzialność przewoźnika oraz określoną lub dającą się ustalić kwotę. Sama adnotacja o szkodzie lub braku na potwierdzeniu doręczenia nie stanowi reklamacji. Przewoźnik może zażądać listu przewozowego, dowodu kosztów frachtu i faktury.
- Przewoźnik potwierdza otrzymanie reklamacji pisemnie w ciągu 30 dni, a w ciągu 120 dni płaci, odmawia albo składa wiążącą ofertę; w przeciwnym razie informuje pisemnie o statusie po 120 dniach i co kolejne 60 dni.

Jeśli widać `Delivered`, a nic nie dotarło, zobacz [oznaczona jako doręczona, ale nie dotarła](guide:delivered-not-received). Więcej w [uszkodzona paczka](guide:damaged-parcel) i [zagubiona paczka](guide:lost-parcel).

## Obsługa klienta i kontakt Mainfreight

Peek nie mógł odczytać strony internetowej Mainfreight, więc ta strona nie podaje numeru telefonu, adresu e-mail ani oddziału — Peek podaje wyłącznie dane kontaktowe odczytane wprost ze stron samego przewoźnika.

Skontaktuj się najpierw ze zlecającym przewóz: ze sklepem lub dostawcą przy zakupie albo z firmą nadającą. To ona dysponuje rezerwacją i może wyjaśnić sprawę w Mainfreight.

## Śledzenie przesyłki Mainfreight w Peek

Z Nowej Zelandii, Australii lub USA Peek przy 11 cyfrach pyta najpierw Mainfreight już w trakcie dodawania („Sprawdzanie usług śledzenia…”) i wskazuje Mainfreight, gdy znajdzie przesyłkę. Z innych krajów nie pyta Mainfreight automatycznie przy dodawaniu — wybierz go ręcznie.

Żaden inny format nie wskazuje Peek automatycznie na Mainfreight. Mimo to wklej numer: Peek obsługuje ponad 4500+ przewoźników. Zmień przewoźnika i wybierz Mainfreight (przy 11 cyfrach pojawi się wśród pasujących, w pozostałych przypadkach wyszukaj po nazwie). Link do śledzenia Mainfreight z numerem od razu rozpoznaje Mainfreight („Wykryty przewoźnik”).

Peek nie wymaga konta ani kodu pocztowego. Jeśli wyszukiwanie Mainfreight nie znajdzie 11 cyfr, Peek próbuje jednocześnie jego serwisów w Nowej Zelandii, Australii i USA; jeśli kilka z nich zna ten numer, Peek nie potrafi ustalić, który ładunek jest Twój. Dla house bill, master bill lub referencji nadawcy Peek wyświetla główny numer z dopiskiem „Mainfreight śledzi przesyłkę pod numerem”.

Peek pokazuje status, każdy etap w brzmieniu Mainfreight z miejscem oraz wagę w Twoich jednostkach, jeśli jest dostępna. Pomija kontakty oddziałów, kody pocztowe, referencję nadawcy i dokumenty. „Otwórz stronę przewoźnika Mainfreight” otwiera numer w systemie Mainfreight. Gdy system jest niedostępny lub nieczytelny, Peek korzysta z [usług śledzenia](guide:universal-tracker) i pokazuje ich historię w ich brzmieniu.

Etapy drogowe z oznaczeniem strefy Nowej Zelandii, Australii lub USA (`NZDT`, `AEST`, `PST` itp.) podają dokładną godzinę. Pozostałe — transport europejski, etapy lotnicze, morskie i kontenery — nie mają strefy: Peek odczytuje je w czasie miejsca, sąsiednich kroków lub kraju docelowego, a inaczej Nowej Zelandii, i przelicza na Twoją strefę (historia kończy się dopiskiem „Godziny w twojej strefie czasowej:” z jej nazwą), więc sporadycznie godzina może lekko odbiegać.

Gdy masz przesyłkę otwartą na ekranie — albo w dniu doręczenia z włączonymi powiadomieniami — Peek odpytuje bezpośrednio Mainfreight nawet co 10 minut, a od 6:00 czasu lokalnego co 2 minuty w dniu, gdy Mainfreight oznaczy ją jako wydaną do doręczenia, o ile jego własny system odpowiada. W pozostałym czasie oraz po dwóch dniach bez nowego skanu (12 godzin przy statusach „Zarejestrowana” lub „Przyjęta przez przewoźnika”) sprawdza co godzinę. Przez same serwisy śledzące sprawdza co godzinę, co 3 godziny po dniu bez skanu i do 15 minut w dniu doręczenia, gdy przesyłka jest otwarta na ekranie lub włączono powiadomienia. Wybierz „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek nie kontaktuje się z Mainfreight, nie umawia dostaw ani nie składa reklamacji w Twoim imieniu.

## Pytania o śledzenie Mainfreight

### Jak wygląda numer śledzenia Mainfreight?

W transporcie drogowym w Nowej Zelandii, Australii i USA ma 11 cyfr (np. `48291736542`). Numery zleceń, house bille i referencje łączą litery z cyframi — wybierz dla nich Mainfreight ręcznie w Peek.

### Co oznacza „At Delivery Depot” w śledzeniu Mainfreight?

Ładunek dotarł do oddziału doręczającego, a Peek pokazuje „W transporcie”. Kolejny etap to `On Delivery Vehicle`, gdy wyjedzie ciężarówką do Ciebie. Jeśli stoi tam od kilku dni, zapytaj nadawcę o planowany termin.

### Co oznacza „Pickup Allocated to Driver”?

Kierowca ma przydzielony odbiór, ale towar nadal znajduje się u nadawcy, więc Peek pokazuje „Zarejestrowana”. Następnym krokiem jest `Picked Up`.

:::sources
- [Universal Parcel Scraper: Mainfreight](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/mainfreight) – nazwy i kraje, 11-cyfrowy format, inne referencje i master bille, linki, wyszukiwanie i serwisy, komunikaty i etapy, wpisy szacowane, godziny, waga, przewidywany dzień, pola pomijane przez Peek
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – przewoźnicy z 11 cyframi, odpytywanie Mainfreight tylko z Nowej Zelandii, Australii i USA, brak przekazania innemu przewoźnikowi, usługi śledzenia jako zapasowe źródło
- [NZ Customs: Duty and GST](https://www.customs.govt.nz/sending-and-receiving/duty-and-gst) – 1000 NZ$, GST pobierane przez zagranicznych sprzedawców, przesyłki zbiorcze, tytoń i alkohol, obliczanie GST, Goods Levy, Customs Number, zwolnienie po opłaceniu
- [GovInfo: 49 U.S.C. 14706, Liability of carriers under receipts and bills of lading](https://www.govinfo.gov/content/pkg/USCODE-2024-title49/html/USCODE-2024-title49-subtitleIV-partB-chap147-sec14706.htm) – odpowiedzialność za rzeczywistą stratę lub szkodę, limit wartości, 9 miesięcy na reklamację i 2 lata na pozew
- [GovInfo: 49 CFR Part 370, Investigation and voluntary disposition of loss and damage claims and processing salvage](https://www.govinfo.gov/content/pkg/CFR-2025-title49-vol5/xml/CFR-2025-title49-vol5-part370.xml) – zawartość reklamacji, adnotacje na pokwitowaniu doręczenia, dokumenty, 30, 120 i 60 dni
:::
