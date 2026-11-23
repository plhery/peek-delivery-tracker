---
title: Śledzenie GLS Polska: statusy, punkty GLS, automaty i pomoc
description: Śledź paczkę GLS Polska: numer paczki lub Track-ID, kod pocztowy, statusy, punkty GLS i automaty ORLEN Paczka, FlexDeliveryService, reklamacje i kontakt.
slug: sledzenie-gls-polska
published: 2026-11-23
updated: 2026-11-23
---

GLS Polska (*General Logistics Systems Poland Sp. z o.o.* z siedzibą w Głuchowie koło Poznania i centralną sortownią w Strykowie pod Łodzią) prowadzi około 50 filii oraz ponad 26 000 punktów odbioru i nadania w całym kraju — w tym ponad 18 000 punktów GLS (wśród nich wszystkie 11 000 sklepów Żabka) oraz około 8 000 współdzielonych całodobowych automatów paczkowych [ORLEN Paczka](carrier:orlen-paczka). Paczkę sprawdzisz na [polskiej stronie śledzenia paczek GLS](https://gls-group.com/PL/pl/sledzenie-paczek/) po numerze paczki albo kodzie Track-ID, lub wklejając numer w polu powyżej.

## Numer paczki GLS Polska i Track-ID: formaty i gdzie je znaleźć

Numer przesyłki znajdziesz w wiadomości ze sklepu o wysyłce, w powiadomieniu e-mail lub SMS od GLS albo na awizie. Wyszukiwarka GLS przyjmuje też kod Track-ID z powiadomień GLS.

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
| Dotarcie do punktu GLS lub automatu, które GLS oznacza jako doręczenie | Przesyłka czeka na Twój odbiór w punkcie GLS albo w automacie paczkowym. | "Do odbioru" |
| `Paczka została doręczona`, `The parcel has been delivered.`, `Handing over the parcel to the recipient at the GLS ParcelShop.` | Doręczona Tobie, osobie upoważnionej albo sąsiadowi lub odebrana w punkcie GLS. | "Dostarczona" |
| `Paczka niedoreczona - odmowa przyjecia przez Odbiorce` | Odbiorca odmówił przyjęcia paczki. | "Próba doręczenia" |
| `Paczka pozostawiona w filii doreczajacej - Odbiorca odmowil przyjecia` | Po odmowie przyjęcia paczka czeka w filii doręczającej. | "Zgłoszono problem" |

W dniu doręczenia usługa [Śledzenie na żywo](https://gls-group.com/PL/pl/sledzenie-na-zywo/) wysyła e-mailem link z przewidywaną godziną doręczenia, podawaną z dokładnością do 15 minut, a pozycję kuriera pokazuje na mapie, gdy do Twojego adresu zostaje 20 przystanków lub mniej. Pasek postępu GLS oznacza paczkę zostawioną w punkcie GLS lub automacie jako doręczoną, choć czeka ona tam na Ciebie; Peek pokazuje ten etap jako „Do odbioru”, a „Dostarczona” dopiero po odbiorze.

## Doręczenia GLS Polska, punkty GLS, automaty ORLEN i FlexDeliveryService

**Dni i czas przewozu.** GLS Polska doręcza od poniedziałku do piątku (doręczenie w sobotę tylko wtedy, gdy nadawca zamówił taką usługę). Standardowe paczki krajowe docierają zazwyczaj w następnym dniu roboczym (termin orientacyjny; nadawcy biznesowi mogą wykupić Express 10:00 lub 12:00). Z Polski do innych krajów UE GLS podaje od 2 do 7 dni roboczych, zależnie od kraju (tabela na stronie [Punkty GLS](https://gls-group.com/PL/pl/punkty-nadan-i-odbioru/punkty-gls/)).

**Waga, wymiary i pobranie (CashService).** Paczki krajowe pod adres mogą ważyć do 31,5 kg (40 kg w ruchu międzynarodowym pod adres; 31,5 kg przez wybrane punkty GLS; 20 kg przez pozostałe punkty GLS, automaty paczkowe i myGLS), przy maksymalnych wymiarach 200 × 80 × 60 cm i sumie długości oraz obwodu do 300 cm. Przesyłki pobraniowe (`CashService`, do 6 000 zł dziennie) opłacisz u kuriera gotówką, kartą lub BLIKIEM.

**Gdy nie ma Cię w domu.** Jak opisuje strona [Odbieranie paczek](https://gls-group.com/PL/pl/odbieranie-paczek/), za Twoją zgodą kurier może zostawić przesyłkę sąsiadowi albo przekierować paczkę bez pobrania do pobliskiego punktu GLS. W pozostałych przypadkach paczka wraca do filii na drugą próbę doręczenia kolejnego dnia roboczego; jeśli i ta się nie uda, czeka w filii przed zwrotem do nadawcy (5 dni roboczych przy odbiorze własnym z FlexDeliveryService lub zwrot po 6 dniach roboczych według poradnika odbioru).

**Czas odbioru w punkcie GLS.** Zgodnie z [regulaminem GLS Polska](https://gls-group.com/PL/pl/regulamin/) dla punktów GLS i automatów paczkowych (obowiązującym od 9 listopada 2026 r.) oraz [FAQ](https://gls-group.com/PL/pl/faq/), punkt GLS przechowuje paczkę przez **7 dni kalendarzowych**, po czym wraca ona do nadawcy (strony [Odbieranie paczek](https://gls-group.com/PL/pl/odbieranie-paczek/) i Punkty GLS wciąż podają 7 dni roboczych). Odbierzesz ją kodem PIN z SMS-a lub e-maila; GLS może sprawdzić Twój dokument tożsamości.

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

Wklej numer w [Peek](/). Numery 11- i 12-cyfrowe stosuje kilku przewoźników, więc Peek pyta najbardziej prawdopodobnych według Twojego kraju (regionu telefonu lub kraju połączenia w sieci). W Polsce pyta najpierw GLS Poland, przed [GLS Francja](carrier:gls-fr), [GLS Niemcy](carrier:gls-de), [GLS Szwajcaria](carrier:gls-ch) i innymi, jak [PPL](carrier:ppl) przy 11 cyfrach albo [BRT](carrier:brt) i [Colis Privé](carrier:colis-prive) przy 12, i zazwyczaj zobaczysz „GLS Poland ma tę przesyłkę.”. Gdzie indziej Peek pyta GLS Poland dopiero wtedy, gdy inni prawdopodobni przewoźnicy nie znają numeru. Wklejony link gls-group.com lub gls-group.eu z `/PL/` w ścieżce i numerem w `match=`, `parcelNumber=` lub `matchParcelNumber=` zostaje przypisany do GLS Poland. W innym wypadku naciśnij „Zmień” przy dodawaniu albo „Zmień przewoźnika” przy dodanej już przesyłce i wybierz GLS Poland.

Pole „Kod pocztowy odbiorcy” ma etykietę „Opcjonalnie”, ale GLS pokazuje skany wyłącznie z nim, o czym informuje podpis: „GLS Poland pokazuje skany tylko po podaniu kodu pocztowego odbiorcy.” Bez niego Peek dostaje od GLS tylko status, bez skanów: w międzyczasie pyta o nie inne usługi śledzenia, a na stronie paczki oferuje „Dodaj kod pocztowy”. Na koncie Peek raz próbuje ostatnio wpisanego kodu dla paczki GLS Poland albo kodu z ostatniej przesyłki. Jeśli GLS nie odnajdzie paczki z tym kodem, Peek o tym informuje i proponuje „Zmień kod pocztowy”. Dopóki GLS czeka na kod lub nie znajduje z nim paczki, a nic innego nie odpowiada przez dobę, Peek sprawdza raz dziennie; dodanie lub zmiana kodu uruchamia kontrolę od razu. Kod pocztowy trafia do GLS i w razie potrzeby do usług śledzenia i nigdy nie pojawia się w udostępnianych linkach.

Z kodem pocztowym widzisz status, lokalizację każdego skanu na mapie, przewidywany dzień, wagę, godzinę doręczenia, a w punkcie GLS jego nazwę i adres. Imię, adres, dane kontaktowe, podpis i numer referencyjny nadawcy są pomijane. Peek odczytuje angielski serwis GLS zamiast strony polskiej: `W doręczeniu` widnieje tam jako `In delivery`, a `Paczka została doręczona` jako `The parcel has been delivered.`. Dotarcie do punktu GLS lub automatu pokazuje status „Do odbioru”. Godziny odczytywane są w czasie polskim.

Peek sprawdza GLS mniej więcej raz na godzinę, także w dniu doręczenia, a ręczne odświeżenie tego nie przyspiesza (po nieudanej próbie co 4 godziny); po siedmiu dniach bez nowości przy „Zarejestrowana” co 6 godzin. Gdy śledzi paczkę tylko przez usługi śledzenia, sprawdza co godzinę, a po dniu bez zmian co 3 godziny.

Gdy najnowszy skan ma cztery dni, a przesyłka wciąż ma status „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie”, Peek oznacza ją dniem tego skanu, np. „Brak aktualizacji od pon 12 paź”. Strona przesyłki pokazuje wtedy „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika GLS Poland.” z linkiem „Poproś przewoźnika GLS Poland o pomoc” do strony kontaktowej GLS Polska. Po trzech tygodniach bez nowego skanu pojawia się „Od ponad trzech tygodni nic nowego. Jeśli przesyłka wciąż nie dotarła, zapytaj sprzedawcę. Peek dalej pyta przewoźnika GLS Poland.”

Możesz wybrać powiadomienia: "Każdy skan", "Ważne etapy" lub "Tylko doręczenie". Peek wyłącznie monitoruje statusy — nie przekieruje paczki ani nie złoży reklamacji w GLS Polska w Twoim imieniu.

## Najczęstsze pytania o śledzenie GLS Polska

### Ile dni paczka czeka w punkcie GLS albo w automacie ORLEN Paczka?

Zgodnie z aktualnym [Regulaminem GLS Polska](https://gls-group.com/PL/pl/regulamin/) i [FAQ](https://gls-group.com/PL/pl/faq/) punkt GLS przechowuje przesyłkę 7 dni kalendarzowych (strony Odbieranie paczek i Punkty GLS wciąż podają 7 dni roboczych), natomiast całodobowy automat ORLEN Paczka trzyma ją 72 godziny przed automatycznym zwrotem do nadawcy.

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
- [GLS Polska: Śledzenie na żywo](https://gls-group.com/PL/pl/sledzenie-na-zywo/) – mapa i przewidywana godzina z dokładnością do 15 minut
- [GLS Polska: FAQ](https://gls-group.com/PL/pl/faq/) – 7 dni kalendarzowych w punkcie GLS, płatność CashService gotówką/kartą/BLIKIEM
- [GLS Polska: Regulamin](https://gls-group.com/PL/pl/regulamin/) – regulamin, dla punktów GLS i automatów od 9 listopada 2026 r.: 7 dni kalendarzowych na odbiór, 7 dni na szkodę ukrytą, 30/45 dni na reklamację i 30 dni na odpowiedź
- [GLS Polska: Kontakt](https://gls-group.com/PL/pl/gls-kontakt/) – infolinia `+48 46 814 82 20` (pn.–pt. 7:00–19:00, sob. 7:00–15:00), `gls@gls-poland.com` i formularz kontaktowy
- [GLS Polska: Reklamacje konsumenckie](https://gls-group.com/PL/pl/reklamacje-konsumenckie/) – formularz reklamacji dla konsumentów
- [GLS Polska: Ostrzeżenia](https://gls-group.com/PL/pl/ostrzezenia/) – ostrzeżenia przed phishingiem, `security@gls-poland.com` i zgłaszanie do CERT NASK
- [Komisja Europejska: Reforma celna UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en?prefLang=pl) – cło 3 € do 150 € (od 1 lipca 2026 r.) i unijna opłata manipulacyjna 2 € (od 1 listopada 2026 r.)
- [Universal Parcel Scraper: gls-pl](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/gls-pl) – detekcja 11/12 cyfr, priorytet w Polsce, opcjonalny kod pocztowy, mapowanie statusów, odświeżanie co godzinę / 4 h po błędzie i link pomocy
:::
