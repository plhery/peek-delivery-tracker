---
title: Śledzenie DayOne: numery DOT, statusy i kto doręcza
description: Śledź przesyłkę DayOne z Chin: jak wygląda numer DOT, co oznaczają statusy na DayOneTrack, kto ją doręcza i do kogo się zgłosić przy opóźnieniu.
slug: sledzenie-dayone
published: 2026-11-23
updated: 2026-11-23
---

DayOne to magazyn logistyczny realizujący wysyłkę zamówień ze sklepów internetowych z Chin. Nie jest kurierem — określa siebie jako „the order fulfillment warehouse, not a courier delivery service provider”. Paczkę przewożą inne firmy, a serwis śledzenia DayOne, [DayOneTrack](https://www.dayonetrack.com/), dostępny po angielsku i chińsku, wyświetla ich skany. Sprawdź tam swój numer DOT albo wklej go w pole powyżej. Numer podaje sklep w szczegółach wysyłki zamówienia.

## Numer śledzenia DayOne: jak wygląda

DayOne nie publikuje oficjalnego formatu. Taki kształt Peek rozpoznaje jako DayOne (z wymyślonymi cyframi):

| Numer | Co to jest |
| --- | --- |
| `DOT0482917364058291` | DOT i 16 cyfr: numer DayOne. |
| `DOT1482917364058291` | DOT1 i 15 kolejnych cyfr: również DayOne. Serwis DayOne sprawdza je w innym systemie. |
| `DOT048291736405829` | Tylko 15 cyfr: nie pasuje do DayOne i Peek nie wskazuje DayOne. Sprawdź, czy nie brakuje cyfry. |

W Peek wielkość liter, spacje i myślniki nie mają znaczenia, gdy wklejasz sam numer. W całej wiadomości zapisuj DOT łącznie z cyframi: spacja zaraz po DOT może sprawić, że Peek odczyta same cyfry i wskaże innego przewoźnika.

**Gdzie go znaleźć.** W e-mailu o wysyłce ze sklepu lub na stronie zamówienia. Jeśli nie możesz go znaleźć, zobacz [jak znaleźć numer śledzenia](guide:find-tracking-number).

**W serwisie DayOne.** Możesz sprawdzić do 10 numerów naraz, po jednym w wierszu. Serwis wyszukuje wyłącznie numery z DOT0 lub DOT1: każdy inny kod, na przykład poczty krajowej, zwraca `Not found`. Obok numeru śledzenia wynik może pokazać `Reference Number` (wewnętrzny numer zamówienia sklepu) oraz kraj docelowy paczki.

## Statusy śledzenia DayOne wyjaśnione

Serwis DayOne przypisuje każdej paczce jeden ogólny status po angielsku i chińsku, a niżej wypisuje skany pod `Package Track`, od najnowszego:

| Status DayOne | Co to oznacza dla ciebie | Peek pokazuje |
| --- | --- | --- |
| `Not found` (查询不到) | DayOne nie ma jeszcze nic pod tym numerem. Dopisuje: „The order information has not been updated yet”. | „Oczekiwanie na przewoźnika DayOne” |
| `Info received` (收到信息) | Dane wysyłkowe dotarły. Paczka mogła jeszcze nie opuścić magazynu. | „Zarejestrowana” |
| `In transit` (运输途中) | W drodze. | „W transporcie” |
| `Pick up` (到达待取) | Dotarła i czeka na odbiór. | „Do odbioru” |
| `Out for Delivery` (派送途中) | W doręczeniu. | „W doręczeniu” |
| `Undelivered` (投递失败) | Próba doręczenia nie powiodła się. | „Próba doręczenia” |
| `Delivered` (成功签收) | Doręczona. | „Dostarczona” |
| `Alert` (可能异常) | Może występować problem. | „Zgłoszono problem” |
| `Expired` (运输过久) | Po chińsku „zbyt długo w transporcie”: paczka jedzie już bardzo długo. | status najnowszego skanu |

Peek kieruje się najnowszym skanem, który rozumie. Ogólnego statusu DayOne używa dopiero wtedy, gdy nie rozpoznaje żadnego ze skanów.

Każdy skan ma godzinę, miejsce oraz treść firmy przewożącej paczkę: Yanwen w Chinach, a potem poczty w twoim kraju. Typowe skany w zwykłej kolejności:

| DayOne podaje | Co to oznacza dla ciebie | Peek pokazuje |
| --- | --- | --- |
| `Order processed by shipper` | Yanwen ma dane zamówienia. Nikt jeszcze nie zeskanował paczki. | „Zarejestrowana” |
| `Yanwen Pickup Scan`, `Processing information input` | Yanwen ma paczkę w Chinach i ją przetwarza. | „Przyjęta przez przewoźnika” |
| `Yanwen facility - Outbound`, `Arrived at domestic terminal station` | Opuściła sortownię Yanwen i dotarła do terminalu w Chinach przed odprawą eksportową. | „W transporcie” |
| `Port of departure - Received by carrier`, `International shipment release - Export`, `Port of departure - Departure` | W porcie wylotu, po chińskiej odprawie eksportowej (nie twojej), a następnie wylot. | „W transporcie” |
| `Port of destination - Arrival`, `ARRIVED AT DESTINATION AIRPORT` | Wylądowała w twoim kraju lub w jego pobliżu. Odprawa importowa jest dopiero przed nią. | „W transporcie” |
| `CLEARED BY CUSTOMS`, `COLLECTED FROM AIRPORT TERMINAL`, `ARRIVED AT FACILITY IN DESTINATION COUNTRY` | Twój urząd celny ją zwolnił i przewieziono ją z lotniska do sortowni. | „W transporcie” |
| `RECEIVED BY AUSTRALIA POST FOR TRANSPORTATION TO PROCESSING FACILITY` | W Australii: przekazana do Australia Post. | „W transporcie” |
| `ITEM PROCESSED AT POST FACILITY`, `PROCESSED THROUGH AUSTRALIA POST FACILITY`, `TRANSFERRED` | Posortowana przez pocztę i przekazana dalej. | „W transporcie” |
| `WITH AUSTRALIA POST FOR DELIVERY TODAY` | Dziś w doręczeniu. | „W doręczeniu” |
| `DELIVERED WITH SAFE DROP` | Doręczona i zostawiona w bezpiecznym miejscu pod adresem. | „Dostarczona” |

`SHIPPING INFORMATION RECEIVED`, `SHIPPING INFORMATION RECEIVED BY AUSTRALIA POST` oraz `SHIPPING INFORMATION APPROVED BY AUSTRALIA POST` oznaczają, że poczta ma dane przesyłki. Mogą pojawić się po późniejszych skanach — to nie cofnięcie etapu, a Peek zachowuje dalszy postęp.

Jeśli jedyny skan to `Shipment has begun. In transit to destination city`, DayOne przekazało paczkę do [YunExpress](carrier:yunexpress), a reszta historii znajduje się w śledzeniu YunExpress pod numerem YunExpress (`YT` i 16 cyfr). `Clearence processing completed - Export` to odprawa eksportowa YunExpress z jego własną pisownią.

## Kto doręcza paczki DayOne

DayOne nie podaje, kto doręcza w twoim kraju. Widać to ze skanów: Yanwen w Chinach, a następnie poczta kraju docelowego, na przykład [Australia Post](carrier:australia-post) dla paczek do Australii.

DayOne nie publikuje zasad prób doręczenia, awiz ani punktów odbioru — ustala je doręczający przewoźnik. Po `Pick up` lub `Undelivered` szukaj awiza lub wiadomości od tej firmy. Zobacz [nieudane doręczenia](guide:missed-delivery) i [nieodebrane paczki](guide:uncollected-parcel).

**Cło w UE.** Towary kupione online spoza UE podlegają od 1 lipca 2026 roku unijnemu cłu 3 € za pozycję dla paczek o wartości do 150 €, a od 1 listopada 2026 roku także unijnej opłacie manipulacyjnej 2 € za pozycję niezależnie od wartości. Jedną pozycję stanowią towary o tej samej klasyfikacji taryfowej, opisie oraz — gdy wymagane — pochodzeniu. Strona DayOne nic nie mówi o cle, opłatach ani o tym, kto je pobiera. Poza UE obowiązują przepisy lokalne — zobacz [paczki zatrzymane na cle](guide:customs).

## Paczka DayOne bez aktualizacji, zaginiona lub zwrócona

DayOne odsyła do sklepu. Na stronie śledzenia widnieje komunikat, że w sprawach logistyki, zagubionych paczek, zwrotów i ponownej wysyłki należy kontaktować się z obsługą klienta sklepu, w którym złożono zamówienie.

**`Not found`.** DayOne nie ma jeszcze danych pod tym numerem. Sprawdź zapis: DOT i 16 cyfr. Jeśli się zgadza, zamówienie mogło jeszcze nie zostać wysłane. Peek wyświetla „Oczekiwanie na przewoźnika DayOne” i sprawdza dalej. Jeśli po dwóch dniach DayOne nadal nie zna numeru i żaden inny system nie odpowiedział, Peek pokazuje „Po dwóch dniach przewoźnik nadal nie zna tego numeru. Sprawdź go, wybierz innego przewoźnika lub usuń przesyłkę.” i od tego czasu sprawdza raz dziennie, a po tygodniu od dodania raz w tygodniu. Jeśli po kilku dniach nic się nie pojawi, zapytaj sklep.

**Tylko `Info received` lub `Order processed by shipper`.** Nikt jeszcze nie zeskanował paczki. Jeśli utrzymuje się to przez kilka dni, zapytaj sklep, kiedy ją nadał.

**Brak nowego skanu od kilku dni.** Przesyłki często milkną podczas długich lotów i na odprawie celnej. Gdy przesyłka nadal na etapie „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie” nie ma nowego skanu od czterech dni, Peek oznacza ją komunikatem „Brak aktualizacji od czterech dni” (lub „Brak aktualizacji od …”), wyjaśnia: „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika DayOne.” i wyświetla „Poproś przewoźnika DayOne o pomoc”. Po trzech tygodniach bez nowego skanu Peek pokazuje „Od ponad trzech tygodni nic nowego. Jeśli przesyłka wciąż nie dotarła, zapytaj sprzedawcę. Peek dalej pyta przewoźnika DayOne.” Zobacz [dlaczego śledzenie przestaje się aktualizować](guide:tracking-not-updating) oraz [śledzenie paczek z Chin](guide:tracking-from-china).

**`Alert` lub `Expired`.** Przeczytaj najnowsze skany, a potem poproś sklep o pomoc, podając numer DOT.

**Zaginiona, uszkodzona lub zwrócona.** DayOne nie publikuje procedury reklamacji dla odbiorców. Poproś sklep o zwrot pieniędzy lub ponowną wysyłkę. Zobacz [zagubione paczki](guide:lost-parcel) i [zwrócona do nadawcy](guide:returned-to-sender).

**Doręczona, ale jej nie ma.** `DELIVERED WITH SAFE DROP` oznacza, że paczkę zostawiono w bezpiecznym miejscu pod adresem. Rozejrzyj się wokół domu, zapytaj sąsiadów, a potem powiadom sklep. Zobacz [doręczona, ale nieotrzymana](guide:delivered-not-received).

## Kontakt z DayOne

Według [serwisu śledzenia DayOne](https://www.dayonetrack.com/):

- **W sprawie paczki:** sklep, w którym złożono zamówienie — zgodnie ze wskazaniem DayOne.
- **E-mail biznesowy:** info@dayone-shipping.com, podawany przez DayOne do współpracy i zapytań biznesowych niezwiązanych z pytaniami o paczki.

Serwis śledzenia DayOne nie podaje numeru telefonu, czatu, formularza kontaktowego ani adresu. Deklaruje, że przechowuje jedynie numery listów przewozowych i nie trzyma szczegółów zamówień.

## Śledzenie paczki DayOne w Peek

[Peek](/) od razu rozpoznaje DayOne po numerze DOT z 16 cyframi („Wykryty przewoźnik”). Działa też link `dayonetrack.com` z parametrem `trackNo=`. Każdy inny numer również wklej śmiało: Peek obsługuje ponad 4500 przewoźników. Gdy kształt pasuje do kilku firm, podczas sprawdzania widać „Sprawdzanie usług śledzenia…”. Jeśli Peek nie wskaże DayOne dla twojego numeru, kliknij „Zmień” i wybierz DayOne w sekcji „Wszyscy przewoźnicy” (wyszukanie „Day One” też go znajdzie), albo użyj „Zmień przewoźnika” na już dodanej przesyłce.

Dla numeru `DOT0` Peek odczytuje bezpośrednio własny system DayOne: status, każdy skan z miejscem, kraj docelowy i czas doręczenia. Dla numeru `DOT1` odpytuje zamiast tego usługi śledzenia.

Gdy DayOne podaje strefę czasową skanu, Peek ją wykorzystuje. Dla skanu bez strefy wylicza czas z miejsca albo sąsiednich skanów (strefę twojego kraju po skanie przylotu). Wszystkie godziny wyświetla w strefie czasowej twojego urządzenia. Pomija kody pocztowe, nazwisko osoby odbierającej lub podpisującej doręczoną paczkę oraz sklepowy `Reference Number`. DayOne nie podaje przewidywanej daty doręczenia ani wagi.

Strona DayOne nie wyświetla numeru doręczającego przewoźnika, ale odpowiedź techniczna czasem go zawiera. Peek odpytuje wtedy także tę firmę: pocztę w twoim kraju dla numeru pocztowego (dwie litery, dziewięć cyfr, dwie litery) jak Royal Mail, USPS, Canada Post, An Post czy Australia Post, albo jednego przewoźnika wynikającego z formatu, np. YunExpress dla numeru `YT`. Gdy ten przewoźnik pokaże skan co najmniej tak świeży jak najnowszy w DayOne, Peek przełącza śledzenie na niego, wyświetla jego numer obok DOT i zachowuje historię DayOne.

Dla numeru `DOT0`, dopóki masz paczkę otwartą w Peek albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza ją nawet co 10 minut, a od 6:00 czasu lokalnego w dniu zeskanowania jej przez DayOne do doręczenia nawet co 2 minuty, dopóki własny system DayOne odpowiada; po dwóch dniach bez wieści (po 12 godzinach przy statusie „Zarejestrowana” lub „Przyjęta przez przewoźnika”) sprawdza co godzinę, a po siedmiu dniach na etapie „Zarejestrowana” — co 6 godzin. Dla numeru `DOT1` (przez usługi śledzenia) Peek sprawdza co godzinę, co 3 godziny po dniu bez nowego skanu, co 6 godzin po siedmiu dniach na etapie „Zarejestrowana” oraz nawet co 15 minut w dniu, w którym paczka jest w doręczeniu, dopóki masz paczkę otwartą w Peek lub włączone powiadomienia. Może wysyłać powiadomienia „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek nie kontaktuje się w twoim imieniu z DayOne ani ze sklepem.

## Pytania o śledzenie DayOne

### Czy DayOne jest kurierem?

Nie. DayOne opisuje siebie jako „the order fulfillment warehouse, not a courier delivery service provider”. Przesyłkę przewożą inne firmy: ze skanów DayOne wynika, że Yanwen w Chinach, a następnie poczta w twoim kraju.

### Gdzie mogę śledzić numer DOT?

Na stronie śledzenia DayOne, [dayonetrack.com](https://www.dayonetrack.com/), po angielsku lub chińsku, do 10 numerów naraz. Albo wklejając go do Peek.

### Dlaczego serwis DayOne pokazuje Not found?

DayOne nie ma jeszcze żadnych danych pod tym numerem. Sprawdź, czy wpisano DOT i wszystkie 16 cyfr. Jeśli numer jest poprawny, poczekaj dzień lub dwa, a potem zapytaj sklep, czy paczka wyszła.

### Dlaczego godziny w serwisie DayOne są nie po kolei?

Każdy skan pokazuje czas lokalny miejsca, w którym go wykonano, bez strefy czasowej, a serwis sortuje wpisy po tych godzinach. Zimą na półkuli północnej Pekin jest 8 godzin przed Londynem i 13 przed Nowym Jorkiem, więc skan w twoim kraju może wyglądać na starszy niż ostatni w Chinach. Sydney jest 3 godziny przed Pekinem australijskim latem, przez co tamtejsze skany wydają się nowsze. Peek przelicza każdą godzinę na strefę czasową twojego urządzenia.

:::sources
- [DayOne: Dayone Tracking](https://www.dayonetrack.com/) – magazyn, nie kurier; obsługa posprzedażna w sklepie; informacja o prywatności; e-mail biznesowy; angielski i chiński; 10 numerów naraz; zapytania DOT0 i DOT1; statusy; pola wyniku; godziny
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – cło 3 € od 1 lipca 2026, unijna opłata manipulacyjna 2 € od 1 listopada 2026
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € za pozycję niezależnie od wartości, czym jest pozycja
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – format numeru, linki i zapytania, treść i kody skanów, strefy czasowe, numer doręczającego przewoźnika, przekazanie, co odczytuje Peek
:::
