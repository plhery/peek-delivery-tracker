---
title: Śledzenie GLS Polska: statusy, punkty GLS, automaty i pomoc
description: Śledź paczkę GLS Polska: numer paczki lub Track-ID, kod pocztowy, statusy, punkty GLS i automaty ORLEN Paczka, FlexDeliveryService, reklamacje i kontakt.
slug: sledzenie-gls-polska
published: 2026-11-23
updated: 2026-11-23
---

GLS Polska (*General Logistics Systems Poland Sp. z o.o.* z siedzibą w Głuchowie koło Poznania i centralną sortownią w Strykowie pod Łodzią) prowadzi około 50 filii oraz ponad 26 000 punktów odbioru i nadania w całym kraju — w tym ponad 18 000 punktów GLS (wśród nich wszystkie 11 000 sklepów Żabka) oraz około 8 000 współdzielonych całodobowych automatów paczkowych [ORLEN Paczka](carrier:orlen-paczka). Paczkę sprawdzisz na [polskiej stronie śledzenia paczek GLS](https://gls-group.com/PL/pl/sledzenie-paczek/) po numerze paczki albo kodzie Track-ID, lub wklejając numer w polu powyżej.

## Numer paczki GLS Polska i Track-ID: formaty i gdzie je znaleźć

Numer przesyłki znajdziesz w wiadomości ze sklepu o wysyłce, w powiadomieniu e-mail lub SMS od GLS albo na awizie (które zawiera też krótszy kod Track-ID działający w wyszukiwarce GLS).

| Format (wymyślony) | Co oznacza |
| --- | --- |
| `48291736405` | 11-cyfrowy numer paczki GLS. Taki sam układ stosują [GLS Niemcy](carrier:gls-de), [GLS Francja](carrier:gls-fr), [GLS Szwajcaria](carrier:gls-ch), [GLS Austria](carrier:gls-at) i [PPL](carrier:ppl). |
| `482917364052` | 12-cyfrowy numer paczki z cyfrą kontrolną na końcu. Wspólny m.in. z [BRT](carrier:brt) i [Colis Privé](carrier:colis-prive). |

Na [stronie śledzenia GLS Polska](https://gls-group.com/PL/pl/sledzenie-paczek/) podanie kodu pocztowego odbiorcy odblokowuje pełną historię skanów i dostęp do opcji przekierowania. Jeśli Twój numer ma inny kształt, sprawdź [formaty numerów śledzenia](guide:tracking-number-formats).

## Co oznaczają statusy śledzenia GLS Polska

Polska strona GLS wyświetla komunikaty po polsku, natomiast międzynarodowe API grupy GLS (z którego korzysta Peek) zwraca ich angielskie odpowiedniki.

| Komunikat GLS Polska / grupy GLS | Co to oznacza dla Ciebie | Peek pokazuje |
| --- | --- | --- |
| `Nadawca nadał numer paczce - paczka nie została jeszcze przekazana kurierowi GLS`, `Preadvice` | Nadawca wygenerował etykietę elektronicznie, ale GLS nie ma jeszcze fizycznie paczki. | "Zarejestrowana" |
| `Paczka została przekazana GLS` | GLS odebrał lub przyjął paczkę do swojej sieci. | "Przyjęta przez przewoźnika" |
| `Paczka zarejestrowana w filii GLS`, `Paczka wyjechała z filii GLS`, `In transit`, `Final parcel center` | Paczka przemieszcza się przez sortownie i filie GLS w stronę oddziału doręczającego. | "W transporcie" |
| `Paczka w doręczeniu`, `In delivery` | Kurier zabrał paczkę na trasę i planuje doręczenie dziś. | "W doręczeniu" |
| `Handing over the parcel to the recipient at the GLS ParcelShop.` (oraz komunikaty przyjęcia w punkcie lub automacie) | Przesyłka czeka na Twój odbiór w punkcie GLS albo w automacie paczkowym. | "Do odbioru" |
| `Paczka została doręczona`, `The parcel has been delivered.` | Doręczona Tobie, osobie upoważnionej albo sąsiadowi. | "Dostarczona" |
| `Paczka niedoreczona - odmowa przyjecia przez Odbiorce`, `Paczka pozostawiona w filii doreczajacej - Odbiorca odmowil przyjecia` | Odbiorca odmówił przyjęcia i przesyłka wróciła do filii doręczającej. | "Próba doręczenia" / "Zwrócona nadawcy" |

Usługa [Śledzenie na żywo](https://gls-group.com/PL/pl/sledzenie-na-zywo/) pozwala też obserwować trasę paczki na mapie z aktualizacją co 15 minut w dniu doręczenia. Zwróć uwagę, że system GLS potrafi oznaczyć przekazanie do punktu GLS jako doręczone, choć paczka nadal czeka tam na Ciebie — Peek wyświetla ten etap jako "Do odbioru".

## Doręczenia GLS Polska, punkty GLS, automaty ORLEN i FlexDeliveryService

**Dni i czas przewozu.** GLS Polska doręcza od poniedziałku do piątku (doręczenie w sobotę tylko wtedy, gdy nadawca zamówił taką usługę). Standardowe paczki krajowe docierają zazwyczaj w następnym dniu roboczym (termin orientacyjny; nadawcy biznesowi mogą wykupić Express 10:00 lub 12:00). Przesyłki międzynarodowe w UE idą zwykle 48–96 godzin (2 do 7 dni roboczych zależnie od kraju według tabeli na stronie [Punkty GLS](https://gls-group.com/PL/pl/punkty-nadan-i-odbioru/punkty-gls/)).

**Waga, wymiary i pobranie (CashService).** Paczki krajowe pod adres mogą ważyć do 31,5 kg (40 kg w ruchu międzynarodowym pod adres; 20 kg do automatów paczkowych, w myGLS i wybranych punktach), przy maksymalnych wymiarach 200 × 80 × 60 cm i sumie długości oraz obwodu do 300 cm. Przesyłki pobraniowe (`CashService`, do 6 000 zł dziennie) opłacisz u kuriera gotówką, kartą lub BLIKIEM.

**Gdy nie ma Cię w domu.** Jak opisuje strona [Odbieranie paczek](https://gls-group.com/PL/pl/odbieranie-paczek/), za Twoją zgodą kurier może zostawić przesyłkę sąsiadowi albo przekierować paczkę bez pobrania do pobliskiego punktu GLS. W pozostałych przypadkach paczka wraca do filii na drugą próbę doręczenia kolejnego dnia roboczego; jeśli i ta się nie uda, czeka w filii przed zwrotem do nadawcy (5 dni roboczych przy odbiorze własnym z FlexDeliveryService lub zwrot po 6 dniach roboczych według poradnika odbioru).

**Czas odbioru w punkcie GLS.** Zgodnie z obowiązującym [Regulaminem GLS Polska](https://gls-group.com/PL/pl/regulamin/) (od 9 listopada 2026 r.) oraz [FAQ](https://gls-group.com/PL/pl/faq/), punkt GLS przechowuje paczkę przez **7 dni kalendarzowych**, po czym wraca ona do nadawcy (uwaga: starszy wpis na stronie [Odbieranie paczek](https://gls-group.com/PL/pl/odbieranie-paczek/) wspomina jeszcze o „7 dniach roboczych”). Odbierzesz ją kodem PIN z SMS-a/e-maila albo z dokumentem tożsamości.

**Całodobowe automaty ORLEN Paczka.** W [automatach paczkowych współdzielonych z ORLEN Paczka](https://gls-group.com/PL/pl/punkty-nadan-i-odbioru/automaty-paczkowe/) przesyłka czeka **72 godziny**, po czym automatycznie wraca do nadawcy. Skrytkę otworzysz kodem PIN z SMS-a/e-maila, kodem QR albo zdalnie w aplikacji myGLS — bez okazywania dowodu, więc paczkę może odebrać każda osoba z kodem. Całodobowa pomoc techniczna dla automatów działa pod numerami `801 167 536` i `502 167 536`.

**Przekierowanie FlexDeliveryService.** Przez link w e-mailu od GLS (bez zakładania konta) na stronie [Przekierowanie paczki](https://gls-group.com/PL/pl/przekierowanie-paczki/) wybierzesz punkt GLS, automat ORLEN, inny adres, inny dzień doręczenia, odbiór w filii, zgodę na pozostawienie w bezpiecznym miejscu albo odmowę przyjęcia.

## Cło spoza UE, uszkodzenie, reklamacje i zaginiona paczka GLS

Przy zakupie w sklepie internetowym umowę przewozu z GLS ma sprzedawca, więc zwrot pieniędzy lub ponowna wysyłka należą do sklepu.

**Przesyłki spoza UE.** Paczki przemieszczające się wewnątrz UE nie podlegają odprawie celnej. Przy zakupach online spoza Unii Europejskiej, zgodnie z zasadami [reformy celnej Komisji Europejskiej](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en?prefLang=pl), od 1 lipca 2026 r. obowiązuje ryczałtowe cło 3 € za kategorię towaru dla przesyłek o wartości do 150 €, a od 1 listopada 2026 r. także unijna opłata manipulacyjna 2 € — niezależnie od polskiego podatku VAT od importu. Więcej w poradniku [paczka zatrzymana na cle](guide:customs).

**Uszkodzenia i reklamacje.** Paczkę z widocznym uszkodzeniem opakowania możesz odrzucić przy odbiorze (kurier sporządza notatkę i odsyła ją do nadawcy). Według [Regulaminu GLS Polska](https://gls-group.com/PL/pl/regulamin/) szkodę niewidoczną z zewnątrz trzeba zgłosić w ciągu **7 dni** od odbioru (pisemnie do centrali lub przez [formularz reklamacji konsumenckich](https://gls-group.com/PL/pl/reklamacje-konsumenckie/)). Ogólny termin na reklamację konsumencką to 30 dni od przyjęcia przesyłki przez GLS (45 dni w razie zaginięcia, nie później niż 12 miesięcy od nadania), a GLS rozpatruje reklamację w ciągu 30 dni. Zobacz poradnik [uszkodzona paczka](guide:damaged-parcel) oraz [zaginiona paczka](guide:lost-parcel).

**Brak skanów albo status doręczonej bez paczki.** Jeśli status nie zmienia się od kilku dni, zajrzyj do [dlaczego śledzenie się nie aktualizuje](guide:tracking-not-updating). Gdy przesyłka ma status doręczonej, a nie masz jej w ręku, sprawdź czy nie czeka w punkcie GLS lub u sąsiada i powiadom sklep; zobacz [przesyłka dostarczona, ale nieodebrana](guide:delivered-not-received) oraz [zwrócona nadawcy](guide:returned-to-sender).

## Obsługa klienta GLS Polska i ostrzeżenie przed oszustwami

W sprawach zwrotu płatności zacznij od sprzedawcy. Bezpośrednie kanały GLS Polska (ze stron [Kontakt GLS](https://gls-group.com/PL/pl/gls-kontakt/) i [Informacje o firmie](https://gls-group.com/PL/pl/informacje-o-firmie/)):

- **Infolinia:** `+48 46 814 82 20` (poniedziałek–piątek 7:00–19:00, sobota 7:00–15:00).
- **E-mail:** `gls@gls-poland.com` (biuro spółki: `office@gls-poland.com`).
- **Formularze online:** [formularz kontaktowy GLS Polska](https://gls-group.com/PL/pl/gls-kontakt/) oraz [reklamacje konsumenckie](https://gls-group.com/PL/pl/reklamacje-konsumenckie/).
- **Całodobowa infolinia techniczna automatów:** `801 167 536` / `502 167 536`.
- **Siedziba:** General Logistics Systems Poland Sp. z o.o., ul. Tęczowa 10, Głuchowo, 62-052 Komorniki.

> **Ostrzeżenie ze strony [Ostrzeżenia GLS Polska](https://gls-group.com/PL/pl/ostrzezenia/):** GLS nigdy nie wysyła e-mailem linków do opłacenia towaru kartą ani przelewem (pobranie CashService i ExWorksService opłaca się kurierowi gotówką, kartą albo BLIKIEM przy odbiorze) i nie korzysta z darmowych skrzynek typu Gmail czy Outlook. Podejrzane wiadomości zgłaszaj na `security@gls-poland.com` (lub `dataprotection@gls-poland.com`), a próby phishingu także do CERT Polska NASK (`incydent.cert.pl`, `cert@cert.pl` lub SMS pod numer `8080`). Zobacz [fałszywe wiadomości o dostawie](guide:delivery-scam-texts).

## Śledzenie przesyłki GLS Polska w Peek

Ponieważ numery 11- i 12-cyfrowe występują u kilku europejskich przewoźników, [Peek](/) kieruje się Twoim regionem. W Polsce Peek pyta najpierw GLS Poland (przed [GLS Francja](carrier:gls-fr), [GLS Niemcy](carrier:gls-de), [GLS Szwajcaria](carrier:gls-ch) i [PPL](carrier:ppl) przy 11 cyfrach oraz przed [BRT](carrier:brt) i [Colis Privé](carrier:colis-prive) przy 12 cyfrach) i wyświetla "GLS Poland ma tę przesyłkę.", gdy GLS ją rozpozna. Poza Polską GLS Poland działa jako wybór awaryjny — wklejenie linku `gls-group.com/PL/` lub `gls-group.eu/PL/` (z parametrem `match=`, `parcelNumber=` lub `matchParcelNumber=`) od razu wybiera GLS Poland; możesz też przełączyć przewoźnika przyciskiem "Zmień" podczas dodawania albo "Zmień przewoźnika" na ekranie przesyłki.

Peek udostępnia opcjonalne pole „Kod pocztowy dostawy”. Choć jest opcjonalne, GLS zwraca pełną historię skanów dopiero po podaniu kodu pocztowego odbiorcy (działa dowolny format 3–12 znaków z cyfrą; kod nigdy nie jest widoczny w udostępnianych linkach). Bez kodu pocztowego Peek korzysta z serwisów śledzenia i po 1 dniu bez odpowiedzi przechodzi na sprawdzanie raz dziennie; dopisanie lub zmiana kodu uruchamia natychmiastowe sprawdzenie. Gdy GLS odpowie, Peek pokazuje bieżący etap ("Zarejestrowana", "Przyjęta przez przewoźnika", "W transporcie", "W doręczeniu", "Do odbioru", "Dostarczona", "Próba doręczenia", "Zwrócona nadawcy"), miejscowość i kraj na mapie, przewidywany dzień doręczenia, wagę, godzinę wydania, nazwę i adres punktu GLS oraz czasy w strefie polskiej (`Europe/Warsaw`, wyświetlane w strefie Twojego urządzenia). Imię i nazwisko odbiorcy, ulica, dane kontaktowe, podpis oraz numer referencyjny nadawcy są pomijane.

Peek odpytuje GLS Poland mniej więcej raz na godzinę, również w dniu doręczenia — ręczne odświeżenie nie przyspiesza kolejnego sprawdzenia — i czeka 4 godziny po nieudanej próbie. Po 7 dniach nadal w statusie "Zarejestrowana" przechodzi na sprawdzanie co 6 godzin. Gdy bez kodu pocztowego działają same serwisy śledzenia, sprawdzają co godzinę, co 3 godziny po dniu bez zmian i do co 15 minut w dniu doręczenia. Po 4 dniach w transporcie bez nowego skanu Peek sygnalizuje zastój (np. "Brak aktualizacji od pon., 12 paź"), wyświetla "Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika GLS Poland." i pokazuje przycisk "Poproś przewoźnika GLS Poland o pomoc", prowadzący do strony kontaktowej GLS Polska. Po ponad trzech tygodniach bez nowości pojawia się komunikat "Od ponad trzech tygodni nic nowego. Jeśli przesyłka wciąż nie dotarła, zapytaj sprzedawcę. Peek dalej pyta przewoźnika GLS Poland."

Możesz wybrać powiadomienia: "Każdy skan", "Ważne etapy" lub "Tylko doręczenie". Peek wyłącznie monitoruje statusy — nie przekieruje paczki ani nie złoży reklamacji w GLS Polska w Twoim imieniu.

## Najczęstsze pytania o śledzenie GLS Polska

### Ile dni paczka czeka w punkcie GLS albo w automacie ORLEN Paczka?

Zgodnie z aktualnym [Regulaminem GLS Polska](https://gls-group.com/PL/pl/regulamin/) i [FAQ](https://gls-group.com/PL/pl/faq/) punkt GLS przechowuje przesyłkę 7 dni kalendarzowych (starsza strona odbioru podaje jeszcze 7 dni roboczych), natomiast całodobowy automat ORLEN Paczka trzyma ją 72 godziny przed automatycznym zwrotem do nadawcy.

### Dlaczego GLS Polska wymaga kodu pocztowego do pełnego śledzenia?

GLS chroni szczegóły przesyłki i udostępnia pełną historię skanów, opcje przekierowania oraz adres punktu odbioru dopiero po dopasowaniu numeru paczki do kodu pocztowego odbiorcy. Wpisz kod pocztowy w Peek, aby pobierać dane bezpośrednio z GLS.

### Czy za przesyłkę pobraniową GLS (CashService) zapłacę kartą lub BLIKIEM?

Tak. GLS Polska potwierdza, że pobranie CashService (do 6 000 zł dziennie) opłacisz u kuriera przy odbiorze gotówką, kartą płatniczą lub BLIKIEM — nigdy przez niespodziewany link z e-maila.

### Jak przekierować paczkę GLS Polska do sklepu Żabka lub automatu?

Kliknij link usługi FlexDeliveryService w powiadomieniu e-mail od GLS (bez rejestracji) albo wejdź na stronę [Przekierowanie paczki](https://gls-group.com/PL/pl/przekierowanie-paczki/), aby skierować przesyłkę do punktu GLS (w tym sklepów Żabka), automatu ORLEN Paczka, pod inny adres lub na inny termin.

:::sources
- [GLS Polska: Śledzenie paczek](https://gls-group.com/PL/pl/sledzenie-paczek/) – śledzenie po numerze paczki, Track-ID i kodzie pocztowym
- [GLS Polska: O firmie](https://gls-group.com/PL/pl/o-firmie/) – centralna sortownia w Strykowie, filie i sieć krajowa
- [GLS Polska: Informacje o firmie](https://gls-group.com/PL/pl/informacje-o-firmie/) – pełne dane spółki, adres w Głuchowie i `office@gls-poland.com`
- [GLS Polska: Odbieranie paczek](https://gls-group.com/PL/pl/odbieranie-paczek/) – doręczenia w dni robocze, sąsiad, druga próba, odbiór w filii i CashService
- [GLS Polska: Przekierowanie paczki](https://gls-group.com/PL/pl/przekierowanie-paczki/) – opcje FlexDeliveryService bez zakładania konta
- [GLS Polska: Punkty GLS](https://gls-group.com/PL/pl/punkty-nadan-i-odbioru/punkty-gls/) – ponad 18 000 punktów GLS (11 000 Żabek), ponad 26 000 punktów OOH, limity wagi/wymiarów i czasy w UE
- [GLS Polska: Automaty paczkowe](https://gls-group.com/PL/pl/punkty-nadan-i-odbioru/automaty-paczkowe/) – ok. 8 000 automatów ORLEN Paczka, 72 godziny na odbiór, PIN/QR/myGLS, limit 20 kg i infolinia techniczna
- [GLS Polska: Śledzenie na żywo](https://gls-group.com/PL/pl/sledzenie-na-zywo/) – mapa z aktualizacją co 15 minut
- [GLS Polska: FAQ](https://gls-group.com/PL/pl/faq/) – 7 dni kalendarzowych w punkcie GLS, płatność CashService gotówką/kartą/BLIKIEM
- [GLS Polska: Regulamin](https://gls-group.com/PL/pl/regulamin/) – regulamin od 9 listopada 2026 r.: 7 dni kalendarzowych na odbiór, 7 dni na szkodę ukrytą, 30/45 dni na reklamację i 30 dni na odpowiedź
- [GLS Polska: Kontakt](https://gls-group.com/PL/pl/gls-kontakt/) – infolinia `+48 46 814 82 20` (pn.–pt. 7:00–19:00, sob. 7:00–15:00), `gls@gls-poland.com` i formularz kontaktowy
- [GLS Polska: Reklamacje konsumenckie](https://gls-group.com/PL/pl/reklamacje-konsumenckie/) – formularz reklamacji dla konsumentów
- [GLS Polska: Ostrzeżenia](https://gls-group.com/PL/pl/ostrzezenia/) – ostrzeżenia przed phishingiem, `security@gls-poland.com` i zgłaszanie do CERT NASK
- [Komisja Europejska: Reforma celna UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en?prefLang=pl) – cło 3 € do 150 € (od 1 lipca 2026 r.) i unijna opłata manipulacyjna 2 € (od 1 listopada 2026 r.)
- [Universal Parcel Scraper: gls-pl](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/gls-pl) – detekcja 11/12 cyfr, priorytet w Polsce, opcjonalny kod pocztowy, mapowanie statusów, odświeżanie co godzinę / 4 h po błędzie i link pomocy
:::
