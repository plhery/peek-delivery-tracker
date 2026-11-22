---
title: Blue Dart śledzenie: numery listów przewozowych i statusy
description: Śledź przesyłkę Blue Dart: jak wygląda 11-cyfrowy numer waybill, co oznacza Shipment Further Connected oraz jak śledzi ją Peek.
slug: sledzenie-blue-dart
published: 2026-11-22
updated: 2026-11-22
---

Blue Dart to indyjska firma kurierska: katalog Peek przypisuje ją do Indii i odczytuje skany według czasu indyjskiego. Przesyłka Blue Dart ma 11-cyfrowy numer listu przewozowego (*waybill*), który dostajesz od nadawcy. Przy zamówieniu online znajdziesz go na przykład w mailu o wysyłce ze sklepu albo na stronie zamówienia. Wklej go w pole powyżej albo wpisz na stronie śledzenia bluedart.com. Ponieważ Peek nie mógł sprawdzić strony internetowej Blue Dart na potrzeby tej strony, poniższe informacje pochodzą z tego, co kod Peek wie o śledzeniu Blue Dart.

## Numer waybill Blue Dart: jak wygląda

Peek zna jeden format dla Blue Dart: 11 cyfr, z których ostatnia jest cyfrą kontrolną wyliczaną z pierwszych dziesięciu. Z wymyślonymi cyframi:

| Numer (wymyślony) | Co to jest |
| --- | --- |
| `48291057360` | 11 cyfr z poprawną cyfrą kontrolną: kształt numeru waybill Blue Dart. Sieci GLS, PostLogistics, Japan Post, Mainfreight i inni też używają 11 cyfr, więc sam format nie wskazuje jednoznacznie na Blue Dart. |
| `48291057361` | Ten sam format z błędną cyfrą kontrolną. Peek nie podpowiada wtedy Blue Dart: sprawdź, czy nie ma literówki. |
| `4829105736` | 10 cyfr: to nie jest numer Blue Dart. Jeśli wybierzesz dla niego Blue Dart, Peek wyświetli, że Blue Dart „nie wydaje takich numerów”. To samo dotyczy 12 cyfr lub numerów z literami. |

11-cyfrowe listy przewozowe Aramex i przesyłki rejestrowane Japan Post mają tę samą cyfrę kontrolną, więc nawet poprawny numer może należeć do nich: sprawdź, którego przewoźnika podał sklep. Spacje i myślniki nie mają znaczenia po wklejeniu do Peek. Wyszukiwarka Blue Dart przyjmuje też inne numery referencyjne, ale Peek potrzebuje numeru *waybill*. Nie możesz go znaleźć? Zobacz [gdzie znaleźć numer przesyłki](guide:find-tracking-number).

## Statusy śledzenia Blue Dart: co oznaczają

Śledzenie Blue Dart pokazuje aktualny status przesyłki, a pod nim skany od najnowszego, każdy z miejscowością, datą i godziną. Oto komunikaty znane Peek ze śledzenia Blue Dart, mniej więcej w kolejności drogi przesyłki:

| Blue Dart pisze | Co to oznacza dla ciebie | Peek pokazuje |
| --- | --- | --- |
| `Pickup Employee Is Out To P/U Shipment` | Kurier Blue Dart jedzie odebrać (*P/U*) przesyłkę od nadawcy. Blue Dart jeszcze jej nie ma. | „Zarejestrowana” |
| `Shipment Picked Up` | Blue Dart odebrał przesyłkę od nadawcy. | „Przyjęta przez przewoźnika” |
| `Shipment Arrived`, `Shipment Arrived At Hub` | Dotarła do oddziału lub sortowni (hubu) Blue Dart wskazanej jako lokalizacja skanu. | „W transporcie” |
| `Paper Work Inscan` | Zeskanowana w sortowni między przybyciem a kolejnym połączeniem. | „W transporcie” |
| `Clubbed Canvas Bag Scan` | Zeskanowana do worka transportowego razem z innymi przesyłkami. | „W transporcie” |
| `Shipment Further Connected` | Wysłana dalej z tej lokalizacji do kolejnego punktu na trasie. | „W transporcie” |
| `Delivery Scheduled For Next Working Day` | W oddziale doręczającym, ale po godzinie granicznej: wyjedzie w trasę w następnym dniu roboczym. | „W transporcie” |
| `Shipment Out For Delivery` | W trasie do doręczenia. | „W doręczeniu” |
| `Cnee Refused Id/Otp Not Shared-Incorrect` | Niedoręczona z powodu odmowy odbiorcy (*Cnee*, *consignee*) albo niepodania lub podania błędnego dokumentu tożsamości lub jednorazowego kodu (*OTP*). | „Próba doręczenia” |
| `Delivery Delayed`, `Delay Caused Beyond Control` | Doręczenie jest opóźnione; drugi komunikat wskazuje na przyczyny niezależne od Blue Dart. | „Zgłoszono problem” |
| `Shipment Delivered` | Doręczona. | „Dostarczona” |
| `Shipment Returned Back To Shipper` | Przesyłka wróciła do nadawcy. Blue Dart podaje to jako status przesyłki po zakończeniu zwrotu. | „Zwrócona nadawcy” |

Komunikat, którego Peek jeszcze nie zna, wyświetla się w brzmieniu z Blue Dart i liczy jako „W transporcie”, chyba że paczka jest już w doręczeniu albo na dalszym etapie — wtedy status pozostaje bez zmian. Więcej w [co oznaczają statusy śledzenia](guide:tracking-statuses).

## Doręczenie Blue Dart w Indiach: dokument i kod OTP

Ponieważ Peek nie mógł sprawdzić zasad doręczeń Blue Dart — takich jak liczba prób czy czas przechowywania — ta strona ich nie podaje.

**Dokument lub kod OTP.** Wpis `Cnee Refused Id/Otp Not Shared-Incorrect` pokazuje, że doręczenie Blue Dart może wymagać dokumentu tożsamości lub jednorazowego kodu (*OTP*). Jeśli otrzymałeś kod dla tej przesyłki, miej go pod ręką w dniu doręczenia. Jeśli ten komunikat pojawił się mimo braku odmowy z twojej strony, poproś sklep o ponowne doręczenie. Zobacz [nieudane doręczenia](guide:missed-delivery) oraz [nieodebrane przesyłki](guide:uncollected-parcel).

Wiadomość o nieudanej dostawie każe ci zapłacić lub podać kod przez link? Przeczytaj [fałszywe SMS-y o paczkach](guide:delivery-scam-texts).

## Przesyłki Blue Dart: doręczenie i cło

**Numer waybill Blue Dart.** Peek nie zna żadnego przewoźnika, któremu Blue Dart przekazywałby przesyłki w Indiach lub za granicą. Jeśli sklep podał też numer innego przewoźnika, dodaj również tamten.

**Wysłane przez ICC Worldwide.** Śledzenie ICC Worldwide może wskazać Blue Dart obok numeru Blue Dart. Peek przechodzi na śledzenie w Blue Dart tylko wtedy, gdy ten numer ma 11 cyfr: pyta wtedy Blue Dart i przenosi tam przesyłkę, gdy tylko pojawi się skan co najmniej tak świeży jak ostatni z ICC Worldwide, zachowując wcześniejszą historię ICC Worldwide. Przy każdym innym numerze Peek pozostaje przy ICC Worldwide. Dodaj przesyłkę numerem ICC Worldwide, kliknij „Zmień” i wyszukaj ICC Worldwide — Peek nie podpowiada go z samego numeru.

**Cło.** Kod Peek zna Blue Dart wyłącznie w Indiach i nie mógł sprawdzić jego strony pod kątem przesyłek zagranicznych. Przy przesyłce zatrzymanej na cle zobacz [przesyłki zatrzymane przez urząd celny](guide:customs).

## Śledzenie Blue Dart stoi, jest opóźnione lub wróciło

W sprawie zwrotu pieniędzy lub nowej wysyłki skontaktuj się ze sklepem: to on zlecił przewóz Blue Dart.

**Brak skanów.** Gdy numeru nie ma w systemie, śledzenie Blue Dart odpowiada `There is no information on the Waybill/Reference Number/Order Number currently.` Peek sprawdza dalej. Porównaj 11 cyfr z wiadomością ze sklepu; jeśli po kilku dniach nic nie ma, zapytaj sklep, czy przekazał przesyłkę.

**Stoi na `Pickup Employee Is Out To P/U Shipment`.** Blue Dart nie odebrał jeszcze przesyłki od nadawcy. Zapytaj sklep, kiedy ją przekaże.

**`Delivery Delayed` lub `Delay Caused Beyond Control`.** Peek wyświetla „Zgłoszono problem”. Jeśli potem nic się nie zmienia, skontaktuj się ze sklepem.

**Dni bez aktualizacji.** Gdy przesyłka w drodze nie ma nowego skanu od czterech dni, Peek oznacza ją komunikatem z dniem tego skanu: „Brak aktualizacji od pon 12 paź”. Zobacz [dlaczego śledzenie się nie aktualizuje](guide:tracking-not-updating).

**Doręczona, ale jej nie ma.** Zapytaj domowników lub recepcję budynku, a potem powiadom sklep. Zobacz [paczka doręczona, ale nieodebrana](guide:delivered-not-received).

**Zaginiona lub zwrócona.** Poproś sklep o zwrot pieniędzy lub ponowną wysyłkę: zobacz [zaginione paczki](guide:lost-parcel). `Shipment Returned Back To Shipper` oznacza powrót do nadawcy: zobacz [zwrot do nadawcy](guide:returned-to-sender).

## Obsługa klienta i kontakt z Blue Dart

Ponieważ Peek nie mógł sprawdzić strony Blue Dart, ta strona nie podaje numeru telefonu ani adresu e-mail: zamieszczamy tylko dane odczytane ze stron samego przewoźnika. Gdy przesyłka utknie, Peek pokazuje link do strony pomocy Blue Dart jako „Poproś przewoźnika Blue Dart o pomoc”.

W przypadku zamówienia online najpierw napisz do sklepu, który zamówił Blue Dart. Peek nie może skontaktować się za ciebie z Blue Dart ani ze sklepem.

## Śledzenie przesyłki Blue Dart w Peek

Wklej numer waybill w [Peek](/). Ponieważ wielu przewoźników używa 11 cyfr, Peek nie wyświetla od razu „Wykryty przewoźnik” na podstawie samego numeru Blue Dart:

- **W Indiach** (region telefonu lub w przeglądarce kraj połączenia) Peek pokazuje „Sprawdzanie usług śledzenia…” i pyta pasujących przewoźników, zaczynając od Blue Dart. Gdy Blue Dart zna numer, zobaczysz „Blue Dart ma tę przesyłkę.”.
- **Poza Indiami** Peek nie pyta Blue Dart automatycznie. Kliknij „Zmień” i wybierz Blue Dart w sekcji „Pasują do numeru”.

Jeśli żaden przewoźnik nie zna jeszcze numeru, dodaj go mimo to lub wybierz Blue Dart ręcznie: Peek śledzi ponad 4500 przewoźników. Peek podpowiada Blue Dart tylko przy poprawnej cyfrze kontrolnej. Jeśli cyfra się nie zgadza, ale jesteś pewien numeru, wyszukaj Blue Dart na liście przewoźników — Peek i tak zapyta. Link śledzenia z bluedart.com zawierający numer (`trackNo=`) także działa: Peek wyciąga z niego numer i od razu wskazuje Blue Dart („Wykryty przewoźnik”).

Przesyłka przypisana do Blue Dart pokazuje „Oczekiwanie na przewoźnika Blue Dart” do pierwszego skanu (lub „Sprawdź numer śledzenia”, jeśli po dwóch dniach Blue Dart nadal nie zna numeru), a dodana bez przewoźnika — „Brak historii śledzenia”. Peek czyta publiczne śledzenie Blue Dart po samym numerze waybill, bez logowania: status przesyłki i każdy skan w brzmieniu z Blue Dart wraz z miejscowością, odczytany w czasie indyjskim. Pomija dane odbiorcy i nie podaje planowanej daty doręczenia. Link „Otwórz stronę przewoźnika Blue Dart” na stronie paczki otwiera numer w śledzeniu Blue Dart.

Gdy masz paczkę otwartą na ekranie albo w dniu doręczenia z włączonymi powiadomieniami, Peek sprawdza nawet co 10 minut — a od 6:00 czasu lokalnego w dniu wyjścia do doręczenia w Blue Dart nawet co 2 minuty — dopóki odpowiada własne śledzenie Blue Dart. Poza tym oraz po dwóch dniach bez nowych informacji (po 12 godzinach, gdy przesyłka ma wciąż status „Zarejestrowana” lub „Przyjęta przez przewoźnika”) sprawdza co godzinę. Gdy przesyłkę prowadzi usługa śledzenia, Peek sprawdza co godzinę, w dniu doręczenia nawet co 15 minut (dopóki masz ją otwartą lub włączone powiadomienia), a po dniu bez nowego skanu co 3 godziny. Powiadomienia możesz ustawić na „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”.

## Pytania o śledzenie Blue Dart

### Jak śledzić przesyłkę Blue Dart?

Wpisz 11-cyfrowy numer waybill w śledzeniu na bluedart.com albo wklej go do Peek. W Indiach Peek pyta Blue Dart jako pierwszego; poza Indiami kliknij „Zmień” i wybierz Blue Dart.

### Jak wygląda numer waybill Blue Dart?

Ma 11 cyfr, z których ostatnia jest cyfrą kontrolną. Aramex i inni też używają 11 cyfr, więc upewnij się, że wiadomość ze sklepu wymienia Blue Dart.

### Co oznacza „Shipment Further Connected” w Blue Dart?

Blue Dart przekazał przesyłkę z jednej ze swoich placówek dalej, do kolejnego punktu na trasie. Peek pokazuje „W transporcie”.

### Co oznacza „Cnee Refused Id/Otp Not Shared-Incorrect”?

Nieudaną próbę doręczenia z powodu odmowy odbiorcy lub niepodania albo błędnego podania dokumentu tożsamości czy jednorazowego kodu (OTP). Peek pokazuje „Próba doręczenia”. Jeśli nie odmawiałeś odbioru, poproś sklep o ponowne doręczenie.

### Z kim się skontaktować w sprawie zaginionej przesyłki Blue Dart?

Ze sklepem albo nadawcą, który zlecił przewóz Blue Dart. Ponieważ Peek nie mógł sprawdzić strony Blue Dart, ta strona nie podaje danych kontaktowych Blue Dart.

:::sources
- [Universal Parcel Scraper: Blue Dart](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/blue-dart) – Indie, 11-cyfrowy numer waybill z cyfrą kontrolną, śledzenie i komunikat o braku danych, opisy i etapy, czas indyjski, pomijane dane, linki śledzenia i strona pomocy
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – inni przewoźnicy z 11 cyframi, cyfra kontrolna Aramex i Japan Post, przewoźnicy odpytywani w Indiach i za granicą, przekazanie z ICC Worldwide do Blue Dart i reguła 11 cyfr, brak przekazywania z Blue Dart
:::
