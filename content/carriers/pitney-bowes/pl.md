---
title: Śledzenie Pitney Bowes: numery UPAA, UPBE i PBX
description: Śledź paczkę Pitney Bowes (np. międzynarodowe zamówienie z eBay): formaty UPAA, UPBE i PBX, znaczenie statusów i kto doręcza przesyłkę.
slug: sledzenie-pitney-bowes
published: 2026-11-23
updated: 2026-11-23
---

Pitney Bowes wysyła paczki za granicę: w kodzie Peek jej numery należą do przesyłek eBay ze Stanów Zjednoczonych i Wielkiej Brytanii oraz do pozostałych przesyłek zagranicznych Pitney Bowes. W historiach zapisanych w kodzie Peek paczka przechodzi przez centrum wysyłkowe Pitney Bowes, a potem trafia do kraju kupującego, gdzie doręcza ją partner, na przykład lokalna poczta. Numer zaczyna się od UPAA, UPBE lub PBX i pochodzi od sprzedawcy albo platformy sprzedażowej: wklej go w polu powyżej. Własna wyszukiwarka Pitney Bowes działa pod adresem trackpb.shipment.co.

## Numer przesyłki Pitney Bowes: jak wygląda

Peek rozpoznaje trzy formaty Pitney Bowes, każdy o długości od 14 do 20 znaków. Z zmyślonymi cyframi:

| Numer (zmyślony) | Co to jest |
| --- | --- |
| `UPAAK482917364058291` | UPAA, jedna litera i 15 cyfr: w kodzie Peek paczka z eBay z USA lub Wielkiej Brytanii. |
| `UPBE48291736A4F02819` | UPBE i 16 znaków (cyfry lub litery od A do F): paczka z eBay z Wielkiej Brytanii. |
| `PBXHK482917365` | PBX, dwie litery i 9 cyfr: inna paczka Pitney Bowes wysyłana za granicę. |
| `PBXHK48291736A4F0281` | PBX, dwie litery i 15 znaków (cyfry lub litery od A do F): ten sam typ w dłuższej postaci. |
| `UPAA482917364058291` | UPAA bez litery: nie pasuje do formatu Pitney Bowes i Peek nie rozpoznaje przewoźnika. Gdy mimo to wybierzesz Pitney Bowes, Peek pokaże „nie wydaje takich numerów”. Sprawdź, czy nie brakuje znaku. |

Spacje, myślniki i małe litery nie mają w Peek znaczenia. Katalog zna Pitney Bowes także jako Pitney Bowes Global Ecommerce, Pitney Bowes Expedited Parcel Service, eBay Global Shipping Program oraz PBI.

**Gdzie go znaleźć.** W e-mailu z potwierdzeniem wysyłki od sprzedawcy lub na stronie zamówienia. Dla eBay zobacz [zamówienia z eBay](guide:ebay-orders); w pozostałych przypadkach [jak znaleźć numer śledzenia](guide:find-tracking-number).

## Wyjaśnienie statusów śledzenia Pitney Bowes

Wyszukiwarka Pitney Bowes wyświetla każdy wiersz z datą, godziną i zazwyczaj miejscem, od najnowszych. Wiersze nie mają kodów: Peek odczytuje ich treść. W zwykłej kolejności:

**W centrum wysyłkowym.** Własne wpisy Pitney Bowes:

| Komunikat Pitney Bowes | Co to oznacza dla ciebie | Co pokazuje Peek |
| --- | --- | --- |
| `Tracking details uploaded`, `Label Created` | Sprzedawca przesłał Pitney Bowes dane paczki. Pitney Bowes jeszcze jej nie ma. | „Zarejestrowana” |
| `Package not received by Hub or First Mile Carrier yet` | Etykieta istnieje, ale ani centrum wysyłkowe, ani przewoźnik pierwszego odcinka jeszcze jej nie zeskanował. | „Zarejestrowana” |
| `Processing at Shipping Center` | Centrum wysyłkowe Pitney Bowes ma paczkę i ją opracowuje. | „W transporcie” |
| `First Dimension Event`, `Second Dimension Event` | Centrum mierzy paczkę. | „W transporcie” |
| `Parcel Documentation and Labeling`, `Customs Documentation and Labeling` | Centrum dodaje dokumenty eksportowe i etykiety. To jeszcze nie samo cło. | „W transporcie” |
| `Container Assignment`, `Container Closed` | Umieszczona w kontenerze do transportu, po czym kontener zostaje zamknięty. | „W transporcie” |
| `Shipped from Global Shipping Center to Destination`, `Departed for Destination` | Opuściła centrum w drodze do kraju docelowego. | „W transporcie” |
| `International Carrier` | Przekazana przewoźnikowi, który wiezie ją za granicę. | „W transporcie” |
| `In Transit` | W drodze. Może pojawić się bez miejsca. | „W transporcie” |

**Dalszy transport, cło i przekazanie.** W historiach z kodu Peek paczki opuszczające brytyjskie centrum Pitney Bowes jadą dalej z Interpost, partnerem składającym zgłoszenie importowe i przekazującym je poczcie kraju docelowego. Wiersze tego etapu (dwa pierwsze bez miejsca, bo powstają podczas opracowania w brytyjskim centrum):

| Komunikat Pitney Bowes | Co to oznacza dla ciebie | Co pokazuje Peek |
| --- | --- | --- |
| `Item processed at Interpost` | Brytyjskie centrum opracowuje paczkę dla Interpost. | „W transporcie” |
| `Interpost handed item over to postal company` | Przekazanie przez Interpost poczcie kraju docelowego, która doręczy paczkę. Pojawia się w tej samej chwili co wiersz wyżej, gdy paczka może być jeszcze w centrum. | „W transporcie” |
| `Customs Clearance` | Zgłoszenie importowe złożone w kraju docelowym. Formalność papierowa: może pojawić się kilka godzin po wyjściu z Wielkiej Brytanii, zanim paczka dotrze na miejsce. | „W urzędzie celnym” |
| `B2C ClearanceStatus4` | Zwolniona przez urząd celny importowy. Peek zapisuje to jako `Customs import cleared`, tak jak śledzenie Interpost opisuje ten kod. | „W transporcie” |
| `B2C ClearanceStatus` z inną cyfrą | Etap odprawy, którego znaczenia kod Peek nie zna. Peek zapisuje go jako `Customs clearance update`. | „W urzędzie celnym” |
| `Cleared customs` | Po odprawie celnej. | „W transporcie” |
| `Package is created in the system` | Kolejny przewoźnik zakłada wpis dla paczki. Paczka się nie porusza. | bez zmiany |

**Wpisy poczty.** Po przekazaniu wyszukiwarka przekazuje dalej skany doręczającej poczty:

| Komunikat Pitney Bowes | Co to oznacza dla ciebie | Co pokazuje Peek |
| --- | --- | --- |
| `Item in transit by postal company` | Poczta transportuje paczkę do ciebie. | „W transporcie” |
| `Parcel is out for delivery` | Wydana do doręczenia. | „W doręczeniu” |
| `Item is waiting for addressee at local post office` | Czeka na ciebie w lokalnej placówce pocztowej: odbierz ją. | „Do odbioru” |
| `Delivered by Consignee Pickup` | Ty jako adresat odebrałeś ją osobiście. | „Dostarczona” |
| `Item has been delivered` | Doręczona. | „Dostarczona” |

`Departed carrier facility` i `Processed by Carrier` też wyświetlają się jako „W transporcie”. Pozostałe wiersze podlegają ogólnym zasadom Peek; nieznany wiersz wyświetla się w oryginalnym brzmieniu jako „W transporcie”, chyba że paczka jest już w doręczeniu lub dalej — wtedy status pozostaje bez zmian.

**Status ogólny.** Wyszukiwarka podaje również status ogólny paczki. Gdy wskazuje doręczenie, Peek pokazuje „Dostarczona”, nawet jeśli najnowszy wiersz tego nie mówi. Inne statusy ogólne liczą się tylko dopóki żaden wiersz nie ma własnego. Status problemu, który potrafił pojawić się nawet po zwykłym odbiorze paczki, nigdy nie nadpisuje wiersza.

## Kto doręcza paczki Pitney Bowes

Peek nie zdołał odczytać strony internetowej Pitney Bowes, więc ta strona nie podaje listy jej partnerów ani krajów. Kod Peek pokazuje Interpost przejmujący paczki z brytyjskiego centrum i przekazujący je poczcie kraju docelowego, a jedna z historii pokazuje [Poste Italiane](carrier:poste-italiane) doręczającą we Włoszech. Wyszukiwarka Pitney Bowes podaje nazwę przewoźnika przejmującego przesyłkę i może linkować wiersz do jego strony śledzenia wraz z numerem.

**Próby doręczenia i odbiór.** Próby, awiza i czas przechowywania zależą od zasad poczty doręczającej: zapytaj ją lub zajrzyj na jej stronę w Peek, jeśli istnieje. Zobacz też [nieudane doręczenie](guide:missed-delivery) i [nieodebrane paczki](guide:uncollected-parcel).

**Cło.** To, czy zapłacisz opłaty przy odbiorze, zależy od twojego kraju oraz od tego, co sprzedawca lub platforma pobrała przy zakupie: zapytaj ich najpierw. Peek nie mógł sprawdzić, jak Pitney Bowes rozlicza opłaty importowe. Zobacz [paczki zatrzymane na cle](guide:customs).

## Paczka Pitney Bowes bez aktualizacji, zaginiona lub zwrócona

**Na razie nic.** Dla nieznanego numeru lub wygasłej historii wyszukiwarka Pitney Bowes daje tę samą odpowiedź, co przy błędzie odczytu. Peek nie potrafi odróżnić tego od awarii: pyta usługi śledzenia, a gdy żadna nie zna paczki, pokazuje „Oczekiwanie na przewoźnika Pitney Bowes”, a od drugiego sprawdzenia komunikat „Nie udało się jeszcze połączyć z przewoźnikiem Pitney Bowes. Peek próbuje dalej.”, sprawdzając dalej. Porównaj numer z tabelą wyżej. Jeśli po kilku dniach nic się nie pojawi, zapytaj sprzedawcę, czy paczka naprawdę została wysłana.

**Utknęło na `Package not received by Hub or First Mile Carrier yet`.** Sprzedawca utworzył etykietę, ale ani centrum wysyłkowe Pitney Bowes, ani pierwszy przewoźnik jeszcze jej nie zeskanował. Zapytaj sprzedawcę, kiedy ją nadał.

**Utknęło na `Customs Clearance`.** Zgłoszenie zostało złożone, a paczki może jeszcze nie być w kraju. Zaczekaj na wpis o zwolnieniu, pokazywany w Peek jako `Customs import cleared`. Jeśli trwa to długo, powiadom sprzedawcę.

**Brak nowości po przejęciu przez pocztę.** Po pierwszych wierszach poczty jej własne śledzenie może wyprzedzać to, co przekazuje Pitney Bowes, a Peek sam się na nią nie przełącza. Otwórz stronę poczty z wiersza w wyszukiwarce Pitney Bowes albo dodaj numer pocztowy w Peek jako osobną przesyłkę.

**Brak aktualizacji od dni.** Po czterech dniach bez skanu dla paczki w statusie „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie” Peek pokazuje flagę („Brak aktualizacji od czterech dni” lub „Brak aktualizacji od …”) i informację „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika Pitney Bowes.” Zobacz [dlaczego śledzenie przestaje się aktualizować](guide:tracking-not-updating).

**Doręczona, ale nic nie przyszło.** Sprawdź miejsce zostawienia i zapytaj sąsiadów, potem doręczającą pocztę i sprzedawcę. Zobacz [doręczona, ale nie otrzymana](guide:delivered-not-received).

**Zaginiona lub zwrócona.** Poproś sprzedawcę albo platformę o zwrot pieniędzy lub ponowną wysyłkę: to oni zlecili transport. Zobacz [zaginione paczki](guide:lost-parcel) i [zwrot do nadawcy](guide:returned-to-sender).

## Obsługa klienta i kontakt z Pitney Bowes

Peek nie zdołał odczytać strony internetowej Pitney Bowes, więc ta strona nie podaje numeru telefonu ani adresu e-mail: Peek publikuje wyłącznie kontakty odczytane bezpośrednio ze stron przewoźnika.

Pitney Bowes wysyła paczkę dla sprzedawcy lub platformy, więc zwróć się najpierw do nich — przy zamówieniu z eBay bezpośrednio przez eBay. Gdy przesyłkę ma już lokalna poczta, o samo doręczenie pytaj pocztę.

## Śledzenie paczki Pitney Bowes w Peek

[Peek](/) od razu rozpoznaje Pitney Bowes po numerze UPAA, UPBE lub PBX („Wykryty przewoźnik”) i sprawdza jej wyszukiwarkę po samym numerze: bez kodu pocztowego i bez konta. Działa też link śledzenia z trackpb.shipment.co lub parceltracking.pb.com. Jeśli masz tylko numer pierwszego odcinka albo numer poczty, wklej go mimo to: Peek śledzi ponad 4 500 przewoźników. Wybranie Pitney Bowes przez „Zmień” dla innego formatu nie pomoże: Peek pokaże „nie wydaje takich numerów”.

Peek wyświetla status, każdy wiersz z miejscem oraz wagę w twoich jednostkach (wyszukiwarka podaje funty i uncje). Pomija link i numer kolejnego przewoźnika. Ponieważ wyszukiwarka nie daje przewidywanego terminu doręczenia ani potwierdzenia doręczenia, Peek nie pokazuje żadnego z nich. Przycisk „Otwórz stronę przewoźnika Pitney Bowes” na stronie paczki otwiera numer w jej wyszukiwarce.

Peek nie przekazuje paczki do poczty: skany pocztowe docierają przez wyszukiwarkę Pitney Bowes. Gdy ta nie odpowiada lub nie może odczytać paczki, Peek pyta usługi śledzenia i pokazuje ich historię w ich brzmieniu.

Wyszukiwarka drukuje każdą godzinę według zegara podmiotu, który zeskanował paczkę, bez strefy czasowej. Peek odczytuje każdy wiersz według zegara jego miejsca lub etapu podróży. Bez miejsca `In Transit` i `Item processed at Interpost` idą w UTC, `Interpost handed item over to postal company` w UTC+2, a `Customs Clearance` w czasie wskazanego kraju. Każda godzina wyświetla się w strefie czasowej twojego urządzenia.

Dopóki masz paczkę otwartą w Peek albo w dniu doręczenia przy włączonych powiadomieniach, Peek odpytuje bezpośrednio Pitney Bowes nawet co 10 minut, a w dniu wydania do doręczenia od 6:00 według zegara najnowszego skanu nawet co 2 minuty, dopóki odpowiada jej wyszukiwarka, a poza tym co godzinę. Po dwóch dniach bez nowych wpisów (po 12 godzinach, gdy paczka nadal ma status „Zarejestrowana” lub „Przyjęta przez przewoźnika”) sprawdza co godzinę; po siedmiu dniach bez nowych wpisów w statusie „Zarejestrowana” — co 6 godzin. Może powiadamiać o opcjach „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek nie może skontaktować się za ciebie z Pitney Bowes, ze sprzedawcą ani z pocztą.

## Pytania o śledzenie Pitney Bowes

### Czym jest numer śledzenia UPAA?

To numer Pitney Bowes: UPAA, jedna litera i 15 cyfr. Kod Peek rozpoznaje numery UPAA jako paczki z eBay z USA i Wielkiej Brytanii, UPBE jako paczki z eBay z Wielkiej Brytanii, a PBX jako inne paczki Pitney Bowes wysyłane za granicę. Katalog wymienia też eBay Global Shipping Program jako inną nazwę Pitney Bowes.

### Co oznacza „Interpost handed item over to postal company”?

To przekazanie paczki przez Interpost — firmę przejmującą paczki z brytyjskiego centrum Pitney Bowes — poczcie kraju docelowego, która ją doręczy. Pitney Bowes wpisuje to już podczas opracowania w brytyjskim centrum, więc wiersz może pojawić się przed wyjazdem paczki. Skany poczty pojawiają się później w wyszukiwarce Pitney Bowes.

### Dlaczego moje śledzenie Pitney Bowes stoi na „Customs Clearance”?

To zgłoszenie importowe, które może zostać złożone kilka godzin po wyjściu z Wielkiej Brytanii, zanim paczka dotrze. Zaczekaj na wpis o zwolnieniu, `Customs import cleared` w Peek. Jeśli stan długo się nie zmienia, zapytaj sprzedawcę.

### Jak skontaktować się z Pitney Bowes w sprawie paczki?

Zapytaj najpierw sprzedawcę albo platformę sprzedażową: to oni zlecili wysyłkę. Ponieważ Peek nie zdołał odczytać strony Pitney Bowes, ta strona nie podaje numeru telefonu ani adresu e-mail.

:::sources
- [Universal Parcel Scraper: Pitney Bowes](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pitney-bowes) – nazwy, trzy formaty numerów i ich zakres, linki śledzenia, wyszukiwarka, komunikaty i etapy, wiersze Interpost i kod odprawy, przekazywane wiersze poczty, Poste Italiane na paczce do Włoch, status ogólny, zegary, waga, co Peek pomija
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – brak przekazania do doręczającej poczty, usługi śledzenia jako zapasowe źródło
:::
