---
title: Śledzenie GLS US (GSO): numery, statusy i doręczenia
description: Śledź paczkę GLS US (znaną też jako GSO): numer SH, znaczenie skanów, dni doręczeń, podpisy, nieudane próby i kontakt.
slug: sledzenie-gls-us
published: 2026-11-23
updated: 2026-11-23
---

GLS US (General Logistics Systems US, Inc.) to amerykańska spółka paczkowa GLS Group, doręczająca na zachodzie Stanów Zjednoczonych. Katalog Peek zna ją również jako GLS USA, GLS United States, General Logistics Systems US, GSO oraz Golden State Overnight – firmę przejętą przez GLS w 2016 roku. Aby sprawdzić paczkę GLS US, wklej numer powyżej lub wpisz go na [amerykańskiej stronie śledzenia GLS](https://gls-group.com/US/en/send-and-receive/track-a-parcel/).

## Numer śledzenia GLS US: jak wygląda

Katalog Peek rozpoznaje jeden format numerów GLS US (cyfry wymyślone):

| Co wklejasz (wymyślone) | Co robi Peek |
| --- | --- |
| `SH605182704936815247` | SH i 18 cyfr, razem 20 znaków. Tylko GLS US stosuje ten format w katalogu Peek, ale dopasowanie jest słabe, więc Peek pyta GLS US przed przypisaniem. |
| `sh 6051 8270 4936 8152 47` | Ten sam numer. Małe litery, spacje i myślniki nie mają znaczenia. |
| Numer GLS US o innym formacie | Peek nie rozpozna go sam. Wybierz GLS US ręcznie (zobacz „Śledzenie paczki GLS US w Peek” niżej). |
| Link śledzenia z gls-group.com zawierający `/US/` albo z gls-us.com lub gso.com | Peek odczytuje numer z linku i od razu wskazuje GLS US („Wykryty przewoźnik”). |

**Gdzie go znaleźć.** W e-mailu z wysyłką lub na stronie zamówienia ze sklepu, albo w e-mailach i SMS-ach od GLS, jeśli sklep cię zapisał. Każde pudełko ma własną etykietę, więc kilka paczek oznacza kilka numerów. Wyszukiwanie bez numeru w GLS wymaga numeru konta i referencji nadawcy – zapytaj sklep. Zobacz [jak znaleźć numer śledzenia](guide:find-tracking-number). Numer zamówienia nie zadziała.

**Inna sieć niż europejskie GLS.** GLS US należy do GLS Group, ale Peek śledzi jej przesyłki w osobnym systemie GLS US. Link gls-group.com z `/EU/` lub `/DE/` prowadzi Peek do [GLS Germany](carrier:gls-de), a nie GLS US. Jeśli e-mail sklepu wskazuje europejską spółkę GLS lub innego przewoźnika, śledź ją tam. [Formaty numerów śledzenia](guide:tracking-number-formats) pokazuje inne formaty.

## Znaczenie statusów śledzenia GLS US

Każdy skan w systemie GLS US ma datę i godzinę, a większość podaje lokalizację: miasto, stan i kraj. Komunikaty znane kodowi Peek są po angielsku i wielkimi literami, w typowej kolejności:

| GLS US pokazuje | Co to oznacza dla ciebie | Peek pokazuje |
| --- | --- | --- |
| `LABEL CREATED` | Nadawca utworzył etykietę. GLS US może jeszcze nie mieć paczki. Ten skan nie podaje miejsca. | „Zarejestrowana” |
| `ARRIVAL SCAN - DELIVERY SCHED FOR 07/15/2026` | GLS US zeskanowało paczkę po przybyciu. Data na końcu (miesiąc najpierw) to zaplanowany dzień doręczenia. | „W transporcie” |
| `SCANNED AT DELIVERY DEPOT` | Zeskanowana w oddziale doręczającym. | „W transporcie” |
| `ON ROUTE FOR DELIVERY` | Dziś w trasie do doręczenia. | „W doręczeniu” |
| `SHIPMENT DELIVERED` | Doręczona. | „Dostarczona” |

**Status ogólny przesyłki.** Nad skanami system podsumowuje przesyłkę kilkoma słowami: `label created`, `in transit`, `out for delivery`, `delivered` lub `returned`. Gdy podsumowanie jest dalej niż skany, Peek kieruje się nim: `delivered` pokazuje „Dostarczona” nawet bez takiego ostatniego skanu, a `returned` pokazuje „Zwrócona nadawcy”.

**Inne wpisy.** Kod Peek nie ma dedykowanych tekstów GLS US dla nieudanej próby, problemu, zatrzymania czy punktu odbioru. Nieznane wpisy wyświetlają się w brzmieniu GLS US według ogólnych reguł Peek. Więcej w [znaczenie statusów śledzenia](guide:tracking-statuses).

Jeśli po `LABEL CREATED` przez dobę lub dłużej nic nie przybywa, Peek pokazuje „Nadawca nie przekazał jej jeszcze przewoźnikowi GLS US. Śledzenie zacznie się po przekazaniu.”. Zapytaj sklep.

**E-maile i SMS-y od GLS.** Jeśli sklep zapisze cię przy tworzeniu etykiety, GLS informuje o przyjęciu w oddziale (z przewidywaną datą), o opóźnieniu lub nieudanej próbie, o wyjeździe do doręczenia (z przybliżoną godziną) oraz o doręczeniu.

## Czas doręczenia, podpisy i nieobecność w GLS US

**Adres.** GLS nie doręcza na skrytki pocztowe (*P.O. boxes*) i sortuje po kodzie ZIP, więc błąd może opóźnić przesyłkę. Przekierowanie wymaga zgody nadawcy – skontaktuj się ze sklepem. Zobacz [zmiana adresu doręczenia](guide:change-delivery-address).

**Dni.** Od poniedziałku do piątku; soboty tylko w wybranych rejonach na zlecenie nadawcy; nigdy w niedziele. GLS nie pracuje w sześć głównych świąt w USA (Nowy Rok, Memorial Day, Dzień Niepodległości, Labor Day, Święto Dziękczynienia i Boże Narodzenie). Paczka przypadająca na święto dociera kolejnego dnia roboczego.

**Godziny.** Zależą od usługi wybranej przez nadawcę. Priority dociera do 10:30 następnego dnia roboczego w większości obszarów, Ground do 17:00 (18:00 na obszarach wiejskich). Na dłuższych trasach Priority przychodzi do 10:30 zaplanowanego dnia, a Ground do końca dnia. Doręczenia sobotnie realizowane są zazwyczaj do 10:30.

**Podpis.** Opcję wybiera nadawca. Domyślnie kurier pobiera podpis w firmach. Pod domem paczka może zostać zostawiona, jeśli nikt nie otwiera, a miejsce jest bezpieczne i suche; w apartamentowcu pod drzwiami lub w biurze administracji/wynajmu, gdy kurier nie ma dostępu do drzwi. Przy *Signature Required* podpis jest konieczny; przy *Adult Signature Required* (wymagane dla alkoholu i tytoniu) podpisujący musi mieć co najmniej 21 lat.

**Nieudane doręczenie.** Nie było cię w domu lub masz zawieszkę na drzwiach? Przełóż dostawę linkiem z e-maili lub SMS-ów GLS albo zadzwoń na infolinię dla odbiorców (niżej). Strony GLS nie podają liczby prób ani czasu przechowywania, a GLS Points opisują wyłącznie jako punkty nadania dla wysyłających. Zobacz [nieudane doręczenie](guide:missed-delivery) oraz [nieodebrane paczki](guide:uncollected-parcel).

## Paczka GLS US nieznaleziona, bez aktualizacji, zaginiona lub zwrócona

**Nieznaleziona.** System GLS US informuje, gdy nie zna numeru. Peek pokazuje wtedy „Oczekiwanie na pierwszy skan przewoźnika GLS US” dla wykrytego numeru SH lub „Oczekiwanie na przewoźnika GLS US” przy ręcznym wyborze, i sprawdza dalej. Nowa etykieta często pojawia się z opóźnieniem. Jeśli po dwóch dniach od dodania numeru SH nadal nic nie ma, Peek oznacza go komunikatem „Oczekiwanie na pierwszą aktualizację od przewoźnika”. Gdy wybrałeś GLS US ręcznie i po dwóch dniach nadal nie zna numeru (bez innych źródeł), Peek wyświetla „Po dwóch dniach przewoźnik nadal nie zna tego numeru. Sprawdź go, wybierz innego przewoźnika lub usuń przesyłkę.”, sprawdzając potem raz dziennie, a po tygodniu raz w tygodniu. Porównaj numer z e-mailem ze sklepu i zapytaj, czy paczkę wysłano.

**Brak wiadomości od dni.** Po czterech dniach bez nowego skanu przesyłka ze statusem „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie” otrzymuje flagę „Brak aktualizacji od …” z dniem tego skanu. Peek przypomina, że w weekendy często brak skanów, pyta GLS US dalej i pokazuje link „Poproś przewoźnika GLS US o pomoc”. Zobacz [dlaczego śledzenie się nie aktualizuje](guide:tracking-not-updating).

**Doręczona, ale nic nie dotarło.** `SHIPMENT DELIVERED` bez paczki pod drzwiami? Sprawdź w śledzeniu GLS, kto podpisał odbiór, obejrzyj okolice drzwi, pocztownię, biuro najmu i zapytaj sąsiadów, po czym zadzwoń na infolinię odbiorców i powiadom sklep. Zobacz [doręczono, ale nie odebrano](guide:delivered-not-received).

**Zaginiona lub uszkodzona.** Najpierw napisz do sklepu, który zamówił transport i może wyjaśnić sprawę z GLS US albo wysłać nowy towar. Reklamować może wyłącznie nadawca w ciągu 90 dni od wysyłki; GLS dąży do rozpatrzenia w 45 dni. Przy szkodach GLS wymaga od nadawcy zdjęć przedmiotu, opakowania wewnętrznego i kartonu – zrób je, zanim cokolwiek wyrzucisz. Zobacz [zaginione paczki](guide:lost-parcel) i [uszkodzone paczki](guide:damaged-parcel).

**Zwrócona.** „Zwrócona nadawcy” oznacza powrót do sklepu. Poproś o zwrot pieniędzy lub ponowną wysyłkę. Zobacz [zwrot do nadawcy](guide:returned-to-sender).

## Obsługa klienta i kontakt GLS US

Na [stronie obsługi klienta GLS US](https://gls-group.com/US/en/support/customer-support/):

- **Telefon:** 1-800-322-5555, opcja 2 (wsparcie odbiorców: śledzenie, problemy i pomoc w doręczeniu).
- **Godziny:** poniedziałek–piątek 7:00–19:00 oraz sobota 7:00–16:00 czasu pacyficznego.
- **Czat na żywo**, dostępny w FAQ GLS. GLS nie podaje adresu e-mail dla odbiorców.

> **Oszustwa.** GLS nigdy nie prosi odbiorców e-mailem o opłatę za towar kartą, płatnością online ani przelewem, choć przy paczkach z zagranicy GLS lub jego agent mogą pobrać cła i podatki. Prawdziwe są strony gls-group.com i z niego linkowane. Fałszywe wiadomości zgłaszaj na security@gls-us.com i przeczytaj [fałszywe SMS-y o przesyłkach](guide:delivery-scam-texts).

## Śledzenie paczki GLS US w Peek

**Numer SH.** Wklej go w pole powyżej. Podczas sprawdzania widnieje „Sprawdzanie usług śledzenia…”. Gdy GLS US zna numer, pojawia się GLS US z komunikatem „ma tę przesyłkę” i paczka trafia pod tego przewoźnika. Gdy jeszcze go nie zna, zobaczysz „jeszcze nie znaleziono · Peek sprawdza dalej”. Dodaj ją mimo to – do pierwszego skanu będzie widnieć jako „Prawdopodobnie GLS US”.

**Inny numer.** Wklej go mimo to: Peek śledzi ponad 4500 przewoźników. Następnie wybierz GLS US ręcznie:

1. Naciśnij „Zmień” obok linii przewoźnika i wyszukaj GLS US (znajdziesz go też wpisując „GSO”, „Golden State Overnight”, „General Logistics Systems US” lub „United States”).
2. Wybierz GLS US – pojawi się „Wybrano ręcznie”. Rozpocznij śledzenie.

Dla dodanej już przesyłki użyj „Zmień przewoźnika”. Peek pyta GLS US tylko o numery mające 5–20 liter i cyfr.

**Co widzisz.** Peek pyta system GLS US wyłącznie po numerze – bez konta i bez kodu ZIP. Pokazuje status, każdy skan z miastem i stanem na mapie oraz, gdy GLS US go podaje, przewidywany dzień doręczenia aż do doręczenia lub zwrotu. Na twojej własnej stronie paczki pokazuje nazwę odbiorcy podaną przez GLS US (jako „Odbiorca”) i kończy trasę na twojej mapie w mieście doręczenia; udostępnione linki nie pokazują ani jednego, ani drugiego. Nie pokazuje podpisującego, podpisu, potwierdzenia doręczenia ani numeru referencyjnego nadawcy. Peek nie przekazuje paczek GLS US innemu przewoźnikowi. Jeśli pod jednym numerem w GLS US figuruje kilka przesyłek, Peek śledzi tę z najnowszym skanem. Przycisk „Otwórz stronę przewoźnika GLS US” na stronie paczki otwiera numer bezpośrednio w amerykańskim systemie GLS.

**Godziny.** GLS US podaje godziny skanów bez strefy czasowej. Peek odczytuje je w czasie miasta skanu lub – gdy go nie zna – stanu (głównej strefy przy wielostrefowych stanach). Przy braku miasta i stanu przyjmuje strefę najbliższego wcześniejszego, potem późniejszego skanu, a ostatecznie czas pacyficzny. Wszystkie godziny Peek wyświetla w strefie czasowej twojego urządzenia.

**Gdy GLS US nie odpowiada.** Jeśli jego system jest niedostępny, Peek pyta [usługi śledzenia](guide:universal-tracker) i pokazuje ich historię w ich własnych słowach.

**Częstotliwość.** Gdy paczka jest otwarta w Peek albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza ją nawet co 10 minut, a co 2 minuty od 6:00 czasu lokalnego w dniu, w którym GLS US wyśle ją na trasę doręczenia; w przeciwnym razie co godzinę. Po dwóch dniach bez zmian (po 12 godzinach przy „Zarejestrowana” lub „Przyjęta przez przewoźnika”) sprawdza co godzinę, a po siedmiu dniach bez nowości przy „Zarejestrowana” co 6 godzin. Powiadomienia obejmują „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”.

Peek nie może skontaktować się z GLS US ani sklepem, zmienić doręczenia ani złożyć reklamacji.

## Pytania o śledzenie GLS US

### Czy GSO to ta sama firma co GLS US?

GLS kupiło Golden State Overnight (GSO) w 2016 roku, a gso.com przekierowuje obecnie na stronę GLS US. Założona w 1995 roku firma GSO doręczała w Kalifornii, Arizonie, Nevadzie i Nowym Meksyku. Peek traktuje je jako jedną firmę: w katalogu GSO, Golden State Overnight, GLS USA, GLS United States i General Logistics Systems US są aliasami GLS US, a linki z gso.com i gls-us.com są rozpoznawane jako GLS US.

### Jak wygląda numer śledzenia GLS US?

Format rozpoznawany przez Peek to SH i 18 cyfr, np. `SH605182704936815247` (wymyślony). Strony GLS nie podają formatu i mogą występować inne: wklej je mimo to i wybierz GLS US przez „Zmień”.

### Co oznacza arrival scan w GLS US?

`ARRIVAL SCAN - DELIVERY SCHED FOR 07/15/2026` oznacza, że GLS US zeskanowało paczkę przy przyjęciu i wyznaczyło doręczenie na datę na końcu (najpierw miesiąc). Peek pokazuje „W transporcie”.

### Dlaczego Peek pokazuje „Oczekiwanie na pierwszy skan przewoźnika GLS US”?

Numer pasuje do formatu GLS US i Peek zapytało GLS US, ale firma jeszcze go nie wykazuje. Przy świeżej etykiecie to normalne i Peek sprawdza dalej. Jeśli po tygodniu nadal nie ma wpisów, Peek informuje o tym i sugeruje sprawdzenie numeru u nadawcy lub wybór innego przewoźnika.

### Czy GLS US to to samo co GLS w Europie?

Należy do tej samej grupy GLS Group, ale Peek traktuje GLS US jako osobnego przewoźnika z własnym systemem śledzenia. Europejskie spółki GLS, takie jak [GLS Germany](carrier:gls-de), mają własne śledzenie – korzystaj ze spółki GLS wskazanej w e-mailu sklepu.

:::sources
- [GLS US: Track a parcel](https://gls-group.com/US/en/send-and-receive/track-a-parcel/) – amerykański tracker, szukanie bez numeru, kto podpisał
- [GLS US: About GLS US](https://gls-group.com/US/en/about-gls-us/) – spółka USA grupy GLS, zachód USA, historia GSO
- [GSO: gso.com](https://www.gso.com/) – przekierowanie na stronę GLS US
- [GLS US: Domestic shipping services](https://gls-group.com/US/en/Shipping/shipping-services/domestic/) – czasy doręczeń, soboty w wybranych rejonach
- [GLS US: Holiday schedule](https://gls-group.com/US/en/resources/holiday-schedule/) – dni doręczeń, święta
- [GLS US: Parcel Tariff](https://gls-group.com/US/en/parcel-tariff/) – bez niedziel, święta, podpisy, kody ZIP, jedna etykieta na karton, reklamacje do 90 dni
- [GLS US: FAQ](https://gls-group.com/US/en/support/faq/) – skrytki pocztowe, przekierowania, zmiana terminu, zawieszki, powiadomienia, czat na żywo, reklamacje
- [GLS US: Claims](https://gls-group.com/US/en/support/claims/) – zgłasza nadawca, 45 dni, zdjęcia
- [GLS US: Customer support](https://gls-group.com/US/en/support/customer-support/) – linia dla odbiorców, godziny
- [GLS US: Find a GLS Point](https://gls-group.com/US/en/Shipping/shipping-tools/find-drop-point/) – wyłącznie punkty nadania
- [GLS US: Security advice](https://gls-group.com/US/en/security-advice/) – prośby o zapłatę, cła i podatki, prawdziwe strony, zgłaszanie
- [Universal Parcel Scraper: GLS US](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/gls-us) – nazwy, format SH i słabe dopasowanie, linki, skany i statusy, odpowiedź brak danych, przewidywany dzień, kilka przesyłek pod jednym numerem, zegary, dane pomijane
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – europejskie sieci GLS i linki, usługi śledzenia jako zapasowe, czas według miasta
:::
