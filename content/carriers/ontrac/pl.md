---
title: Śledzenie przesyłek OnTrac: numery, statusy i doręczenia
description: Śledź paczkę OnTrac lub LaserShip w USA: formaty numerów C, D, LX i 1LS, znaczenie statusów, nieudane próby doręczenia, opóźnienia i czat z asystentem Wink.
slug: sledzenie-ontrac
published: 2026-11-21
updated: 2026-11-21
---

OnTrac to amerykański przewoźnik ostatniej mili doręczający zamówienia internetowe dla sklepów i marek w `35` stanach USA oraz w Waszyngtonie, D.C. (`70 %` populacji USA), który od fuzji w 2021 r. obejmuje także sieć LaserShip. Paczkę sprawdzisz na [ontrac.com/tracking](https://www.ontrac.com/tracking/) po numerze śledzenia albo wklejając go bezpośrednio w polu u góry strony.

## Format numeru śledzenia OnTrac i gdzie go znaleźć

Numer znajdziesz na stronie zamówienia lub w e-mailu potwierdzającym wysyłkę ze sklepu (sam numer zamówienia nie zadziała). Według FAQ OnTrac numery zaczynają się od `C`, `D`, `1LS`, `LS`, `LX` albo `BN` ([jak znaleźć numer śledzenia](guide:find-tracking-number)). Poniższe układy rozpoznaje Peek (z przykładowymi cyframi):

| Układ (zmyślony) | Co oznacza |
| --- | --- |
| `C14829105736497` | `C1` i 13 cyfr zakończonych cyfrą kontrolną (15 znaków): od razu wykrywany jako OnTrac. |
| `D10482910573645` | `D10` i 12 cyfr z cyfrą kontrolną (15 znaków): od razu wykrywany jako OnTrac (`C24829105736495` i inne poprawne 14-cyfrowe numery `C`/`D` są sprawdzane w OnTrac). |
| `LX48291057` | Format LaserShip: `L`, następnie `A`, `E`, `H`, `I`, `N` lub `X` oraz 8 cyfr (10 znaków). |
| `1LS482910573649` | Format LaserShip: `1LS` oraz od 12 do 14 liter lub cyfr. |
| `AD482910573` | Referencja nadawcy (`AD` + 9 cyfr), którą po wybraniu OnTrac system zamienia na właściwy numer OnTrac. |

Dawna domena `lasership.com` przekierowuje na `ontrac.com`, gdzie sprawdzisz wszystkie paczki LaserShip. Numery z prefiksem `LS` lub `BN` (bądź o innym układzie) wpisz bezpośrednio na `ontrac.com/tracking/`, bo po ręcznym wyborze OnTrac w Peek pojawi się informacja, że przewoźnik „nie wydaje takich numerów”. Świeżo nadany numer może początkowo nie pokazywać skanów: OnTrac prosi o odczekanie **1–3 dni** od maila o wysyłce, gdyż niektórzy nadawcy przesyłają dane nawet do 24 godzin.

## Co oznaczają statusy śledzenia OnTrac

Strona OnTrac działa wyłącznie po angielsku. Oto zdarzenia i komunikaty opóźnień odczytywane przez Peek:

| Status OnTrac | Co oznacza dla Twojej paczki | Peek pokazuje |
| --- | --- | --- |
| `On its way to OnTrac` | OnTrac ma dane etykiety, ale jeszcze nie samą paczkę (`LABEL CREATED, TRACKING DETAILS WILL BE AVAILABLE ONCE THE PACKAGE ARRIVES AT THE ONTRAC FACILITY` lub `Waiting for package from shipper.`). | „Zarejestrowana” |
| `Origin Scan` | OnTrac przyjął przesyłkę i kieruje ją do lokalnego oddziału (kolejne sortownie mają wpis `Arrived at Facility`). | „W transporcie” |
| `Arrived at Final Servicing Facility` | Paczka dotarła do oddziału obsługującego Twój rejon. | „W transporcie” |
| `Loaded onto vehicle` | Załadowana do pojazdu liniowego lub kurierskiego. | „W transporcie” |
| `Out for Delivery` | Dziś u kierowcy w drodze pod Twój adres. | „W doręczeniu” |
| `Package Delivered` | Doręczona. | „Dostarczona” |
| `Delivery Pending` | Kurier nie mógł zostawić paczki (względy bezpieczeństwa lub wymagany podpis); OnTrac ponowi próbę. | „Próba doręczenia” |
| `Business closed. Please provide hours` | Firma zamknięta; do 3 prób doręczenia, OnTrac prosi o podanie godzin otwarcia. | „Próba doręczenia” |
| `Incomplete address. Please contact us` | Niepełny adres; uzupełnij dane, inaczej paczka może wrócić. | „Próba doręczenia” |
| `Details needed. Please contact us` | Paczka wstrzymana do czasu podania szczegółów adresu lub dostępu. | „Zgłoszono problem” |
| `Order has been transferred to another carrier` | Przekazana innemu przewoźnikowi, którego OnTrac nie podaje z nazwy (mimo nagłówka *Delivered* na `ontrac.com`). | „W transporcie” |
| `Delivery Delay`, `Delayed. Delivery date updated`, `Delayed by road closure`, `Delayed. Could not reach delivery area` | Opóźnienie w trasie, zamknięta droga lub brak dojazdu z nową datą dostawy. | „W transporcie” |
| `Weather delay. Delivery date updated`, `Weather delay. See service alerts` | Opóźnienie pogodowe w sieci OnTrac. | „W transporcie” |
| `Misrouted. Delivery date updated` | Błędne skierowanie skorygowane wraz z nową datą doręczenia. | „W transporcie” |

Szersze omówienie etapów znajdziesz w poradniku [co oznaczają statusy śledzenia](guide:tracking-statuses).

## Godziny doręczeń OnTrac i nieudane próby

**Godziny.** Do domów OnTrac doręcza zazwyczaj między **`08:00` a `21:00`** czasu lokalnego w zaplanowanym dniu, a w wybranych rejonach także w **soboty i niedziele** w tych samych godzinach (standardowe limity w regulaminie dodatkowym: waga rozliczeniowa do `50 lbs`, długość `48 in`, drugi bok `30 in`, objętość `8 640 cali sześciennych`).

**Nieudane doręczenie.** OnTrac podejmuje maksymalnie **3 próby doręczenia** (kolejna w ciągu **1–2 dni roboczych**, nigdy tego samego dnia), po czym zwraca przesyłkę lub postępuje zgodnie ze swoimi zasadami dla przesyłek niedoręczalnych ([nieudane doręczenie](guide:missed-delivery)).

**Wskazówki dla kuriera i zmiana adresu.** Jeśli odnotowano próbę podczas Twojej obecności, sprawdź adres na koncie sklepu i prześlij kod do bramy lub instrukcje czatbotowi **Wink** z numerem śledzenia (gdy paczka jest już w doręczeniu, wskazówki zadziałają przy kolejnej próbie). **Zmianę adresu** musi natomiast zatwierdzić sprzedawca ([zmiana adresu dostawy](guide:change-delivery-address)).

**Odbiór własny i alerty SMS.** Strona OnTrac nie wymienia własnych punktów odbioru ani paczkomatów, a OnTrac paczkę zatrzymuje w lokalnym oddziale wyłącznie na wcześniejszą prośbę (zabierz numer śledzenia i dokument ze zdjęciem). Przycisk `Get Text Alerts` (`NotifyMe`) na `ontrac.com/tracking/` włącza SMS-y o wydaniu do doręczenia, próbie i dostarczeniu (rezygnacja SMS-em `STOP` na `(469) 529-1133`), a na stronie widoczne bywa zdjęcie doręczenia (`VPOD`).

## Opóźniona, stojąca w miejscu lub zagubiona paczka OnTrac

- **Brak aktualizacji:** gdy status nie zmienia się przez **3 dni robocze**, OnTrac prosi o kontakt ze sklepem, a w razie potrzeby z asystentem **Wink** ([dlaczego śledzenie się nie aktualizuje](guide:tracking-not-updating)).
- **Status doręczono, ale paczki brak:** sprawdź adres i zdjęcie doręczenia na `ontrac.com/tracking/` (lub zapytaj administrację budynku o kod do skrytki), a potem wybierz `Tracking shows delivered; no package` w formularzu `Have a question?` albo zgłoś sprawę przez **Wink**. Otrzymasz e-mailem numer zgłoszenia, a wyjaśnianie trwa do **48 godzin**; jeśli po **2 dniach roboczych** nadal nie ma wieści, skontaktuj się ze sprzedawcą ([dostarczona, ale nieotrzymana](guide:delivered-not-received)).
- **Uszkodzenie lub zaginięcie:** zgodnie z warunkami OnTrac formalne reklamacje składa nadawca (`cargoclaims@ontrac.com`); o zwrot pieniędzy lub ponowną wysyłkę poproś sklep ([uszkodzona paczka](guide:damaged-parcel), [zagubiona paczka](guide:lost-parcel)).

## Obsługa klienta OnTrac: czat, SMS i godziny pracy

Na podstronach [Support](https://www.ontrac.com/support/) i [FAQ](https://www.ontrac.com/faq/) OnTrac nie podaje głosowej infolinii ani adresu e-mail dla odbiorców, udostępniając trzy kanały:

- **Czat z asystentem Wink:** czerwona ikona w prawym dolnym rogu `ontrac.com` (wymagany poprawny numer śledzenia).
- **Linia SMS:** wyślij `start` SMS-em na **`703-348-8441`** (linia wyłącznie tekstowa do śledzenia lub otwarcia zgłoszenia; opłaty wg taryfy operatora; `STOP` wyłącza wiadomości).
- **Formularz na stronie:** sekcja `Have a question?` pod wynikiem wyszukiwania na `ontrac.com/tracking/`.
- **Godziny pracy:** poniedziałek–piątek `08:00–21:00` czasu wschodniego USA (*Eastern Time*), sobota–niedziela `08:00–20:00` *Eastern Time*; nieczynne w Boże Narodzenie, 26 grudnia i Nowy Rok.

Podejrzane wiadomości sprawdzisz z poradnikiem [fałszywe SMS-y o paczkach](guide:delivery-scam-texts).

## Śledzenie przesyłek OnTrac w Peek

[Peek](/) rozpoznaje OnTrac natychmiast („Wykryty przewoźnik”) dla numerów `C1` i `D10` z poprawną cyfrą kontrolną, kodów LaserShip `LX` i `1LS` oraz linków `ontrac.com` z parametrem `number=`. Dla pozostałych poprawnych 14-cyfrowych numerów `C` i `D` pokazuje „Sprawdzanie usług śledzenia…”, a po odpowiedzi — „OnTrac ma tę przesyłkę.”. Dla 9-cyfrowej referencji `AD` wybierz OnTrac przyciskiem „Zmień”: OnTrac odnajdzie paczkę, a Peek pokaże też jej numer OnTrac („OnTrac śledzi przesyłkę pod numerem”) (przed pierwszym skanem widnieje „Oczekiwanie na przewoźnika OnTrac”).

Peek pobiera dane z usługi JSON OnTrac i wyświetla status, skany z miastem i stanem na mapie (do 100 najnowszych), przewidywaną datę doręczenia, czas dostarczenia, wagę oraz wymiary, pomijając dane odbiorcy, podpis i zdjęcie doręczenia. Skany ze strefą czasową są przeliczane na strefę Twojego urządzenia („Godziny w twojej strefie czasowej: …”), a skany bez przesunięcia UTC Peek odczytuje według czasu stanu USA, w którym je zarejestrowano, i przelicza tak samo. `Order has been transferred to another carrier` pozostaje w Peek „W transporcie” bez dalszego przełączenia, ponieważ OnTrac nie ujawnia nazwy kolejnego przewoźnika.

Przy otwartej paczce lub w dniu dostawy z włączonymi powiadomieniami Peek sprawdza status nawet co 10 minut, a od `06:00` czasu lokalnego w dniu skanu `Out for Delivery` nawet co 2 minuty, dopóki odpowiada usługa OnTrac; poza tym i po 12 godzinach na etapie „Zarejestrowana” / „Przyjęta przez przewoźnika” lub dwóch dniach bez zmian — co godzinę („Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”). Peek nie skontaktuje się za Ciebie z OnTrac ani ze sklepem.

## Najczęstsze pytania o śledzenie OnTrac

### Jak zaczynają się numery śledzenia OnTrac?

Od `C`, `D`, `1LS`, `LS`, `LX` lub `BN` według OnTrac; obsługiwane przez Peek numery `C` i `D` liczą 15 znaków (litera i 14 cyfr).

### Dlaczego śledzenie OnTrac się nie aktualizuje?

Odczekaj 1–3 dni od maila o wysyłce na pierwsze aktualizacje. Jeśli aktywna przesyłka nie zmienia statusu przez 3 dni robocze, skontaktuj się ze sklepem, a potem z asystentem Wink.

### W jakich godzinach kurier OnTrac doręcza paczki?

Między `08:00` a `21:00` Twojego czasu lokalnego w zaplanowanym dniu, a w części obsługiwanych stref także w soboty i niedziele.

### Czy OnTrac udostępnia śledzenie na żywo?

OnTrac pokazuje datę, godzinę i miasto każdego skanu (oraz zdjęcie doręczenia na stronie), a usługa `NotifyMe` wysyła SMS-y o wyjeździe kuriera, próbie i doręczeniu.

### Czy OnTrac to wiarygodny przewoźnik?

Tak: firma powstała w 1991 r. jako *California Overnight*, w 2021 r. połączyła się z LaserShip (założonym w 1986 r.), prowadzi ponad `102` oddziały i `18` sortowni w USA oraz jest preferowanym przewoźnikiem dla sprzedawców Amazon MFN.

:::sources
- [OnTrac: FAQ](https://www.ontrac.com/faq/) – prefiksy numerów, godziny 08:00–21:00 i weekendy, ponowne próby w 1–2 dni robocze, Wink, linia SMS 703-348-8441, 102+ lokalizacji i 18 sortowni
- [OnTrac: Support](https://www.ontrac.com/support/) – 1–3 dni na pierwsze skany, 48 godzin na wyjaśnienie sprawy, NotifyMe oraz godziny pracy i święta (w tym 26 grudnia)
- [OnTrac: Tracking](https://www.ontrac.com/tracking/) – wyszukiwarka przesyłek, link do zdjęcia doręczenia i formularz Have a question?
- [OnTrac: Skrypt strony śledzenia](https://www.ontrac.com/wp-content/themes/ontrac/assets/js/ontrac-package-status.js) – nagłówki statusów, kolumny historii i powody zgłoszeń
- [OnTrac: Kody statusów](https://www.ontrac.com/wp-content/themes/ontrac/assets/data/statusCodes.json) – znaczenie prób, wstrzymań, przekazań i opóźnień
- [OnTrac: Additional Terms & Conditions](https://www.ontrac.com/terms-conditions-additional/) – 3 próby doręczenia, limity 50 lbs / 48 in oraz reklamacje składane przez nadawcę
- [OnTrac: About OnTrac](https://www.ontrac.com/about/) – historia LaserShip (1986) i OnTrac (1991), fuzja w 2021 r. i sieć międzybrzeżna
- [OnTrac: Residential E-Commerce Delivery](https://www.ontrac.com/residential-e-commerce-delivery/) – 35 stanów i D.C., zasięg 70 % populacji USA oraz zdjęcia doręczenia VPOD
- [OnTrac: Amazon MFN Sellers](https://www.ontrac.com/amazon-mfn-sellers/) – preferowany przewoźnik dla sprzedawców Amazon MFN
- [OnTrac: NotifyMe Terms of Use](https://www.ontrac.com/notifyme-terms-of-use/) – powiadomienia SMS i rezygnacja komendą STOP pod (469) 529-1133
- [Universal Parcel Scraper: OnTrac](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ontrac) – wzorce C, D, LX, 1LS i referencja AD, 18 opisów statusów, strefy czasowe, waga/wymiary i przekazanie NFRP
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – rozpoznawanie przewoźników i serwisy śledzące
:::
