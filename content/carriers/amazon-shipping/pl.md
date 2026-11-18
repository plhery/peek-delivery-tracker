---
title: Amazon Shipping śledzenie: numer przesyłki, statusy i pomoc
description: Śledź przesyłkę Amazon Shipping bez konta Amazon: jak wygląda numer PL lub TBA, która wyszukiwarka go zna oraz do kogo zgłosić opóźnienie lub zagubienie.
slug: amazon-shipping-sledzenie
published: 2026-11-18
updated: 2026-11-18
---

Paczkę nadaną przez Amazon Shipping sprawdzisz bez logowania na konto Amazon po numerze śledzenia w publicznej wyszukiwarce Amazon Shipping (na przykład `track.amazon.fr`) albo wklejając numer w pole powyżej. Amazon Shipping to usługa dostaw ostatniej mili, za pomocą której Amazon doręcza przesyłki wysyłane przez inne firmy do ich klientów — jak wyjaśnia publiczny tracker Amazon Shipping, zlecenie nadaje sprzedawca niezależnie od tego, czy kupiono towar na Amazonie, czy w innym sklepie internetowym. Zwykłe zamówienia realizowane bezpośrednio przez Amazon śledzi się na koncie w sekcji „Moje zamówienia”.

## Numer śledzenia Amazon Shipping: jak wygląda

Polska wersja publicznej wyszukiwarki Amazon Shipping nie istnieje: europejski serwis obejmuje jedynie strony dla Wielkiej Brytanii (`track.amazon.co.uk`), Francji (`track.amazon.fr`), Włoch (`track.amazon.it`), Hiszpanii (`track.amazon.es`) oraz Indii. Dla numerów z polskim prefiksem `PL` Peek odpytuje serwis francuski (`track.amazon.fr`), a dla numerów `TBA`, `TBC` lub `TBM` amerykański (`track.amazon.com`).

Peek rozpoznaje te formaty (wszystkie przykłady są wymyślone):

| Format | Co oznacza |
| --- | --- |
| `PL4829105736` | Europejski dwuliterowy kod kraju i 10 cyfr, np. `PL`, `DE`, `FR`, `IT`, `ES` lub `UK`. |
| `TBA482910573649` | `TBA`, `TBC` lub `TBM` i 12 cyfr (także `TBR` i 9 cyfr albo `TBAU` i 9 cyfr). |

Haczyk polega na tym, że [Amazon Logistics](carrier:amazon-logistics), który rozwozi własne zamówienia sklepu Amazon, korzysta z identycznych formatów numerów. Odróżnia je wyłącznie publiczny tracker Amazon Shipping: zna on tylko przesyłki nadane w usłudze Amazon Shipping, a dla zwykłych zamówień własnych Amazona zwraca brak wyników. Jeśli numer nie pojawia się w publicznym trackerze, a zakup pochodzi z `Amazon.pl`, zobacz [śledzenie Amazon Logistics](carrier:amazon-logistics). Numer zamówienia w układzie trzech, siedmiu i siedmiu cyfr z myślnikami nie jest numerem przesyłki, a dwóch innych liter z 10 cyframi (jak `US` i 10 cyfr) Peek nie uznaje za numer Amazona. Brakuje Ci numeru? Poproś o niego sklep: [gdzie znaleźć numer przesyłki](guide:find-tracking-number).

## Przesyłka Amazon Shipping nie dotarła, zaginęła lub jest uszkodzona

Twoim punktem kontaktu jest sklep internetowy, w którym złożono zamówienie. W Unii Europejskiej zgodnie z serwisem [Your Europe](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_pl.htm) sprzedawca odpowiada za dostawę oraz za wszelkie uszkodzenia towaru od chwili wysyłki aż do fizycznego odbioru przez Ciebie.

**Oznaczona jako doręczona, ale jej nie ma.** Według [FAQ Your Europe](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/faq/index_pl.htm) to sprzedawca musi skontaktować się z firmą kurierską i udowodnić doręczenie; jeśli nie potrafi przedstawić dowodu, ma obowiązek wysłać identyczny produkt albo zwrócić pieniądze. Więcej w poradniku [przesyłka doręczona, a jej nie ma](guide:delivered-not-received).

**Uszkodzona.** Ryzyko transportu ponosi sprzedawca: przysługuje Ci naprawa, wymiana albo — gdy nie są możliwe — obniżenie ceny lub zwrot zapłaconej kwoty. Zobacz też [uszkodzona paczka](guide:damaged-parcel).

**Opóźniona lub zaginiona.** O ile nie ustalono innej daty, zamówienie powinno dotrzeć w ciągu 30 dni. Gdy nie przychodzi, wyznacz sprzedawcy dodatkowy rozsądny termin, a po jego bezskutecznym upływie możesz odstąpić od umowy i żądać niezwłocznego zwrotu pieniędzy. Jeśli zatrzymał się sam status, sprawdź [status przesyłki się nie zmienia](guide:tracking-not-updating).

## Kontakt w sprawie Amazon Shipping

W sprawach opóźnionej, niedoręczonej lub uszkodzonej przesyłki skontaktuj się bezpośrednio ze sprzedawcą, u którego kupiono towar.

> W poradniku bezpieczeństwa [About Amazon Polska](https://www.aboutamazon.pl/wiadomosci/zakupy-na-amazon/5-wskazowek-jak-rozpoznawac-oszustow-podszywajacych-sie-pod-amazon-i-unikac-ich) Amazon przypomina, że nigdy nie prosi przez telefon ani e-mail o płatność, przelew na zewnętrznej stronie, zakup karty podarunkowej ani instalację oprogramowania do zdalnego dostępu. Nie dzwoń pod numery z SMS-ów, e-maili czy wyszukiwarek — autentyczność wiadomości sprawdzisz bezpośrednio na `Amazon.pl` lub w aplikacji Amazon, a podejrzane próby zgłosisz przez narzędzie samoobsługowe Amazona albo na adres `reportascam@amazon.com`. Więcej w [oszustwo na paczkę przez SMS](guide:delivery-scam-texts).

## Śledzenie przesyłki Amazon Shipping w Peek

Wklej numer albo link z `track.amazon` zawierający `/tracking/` i numer w [Peek](/). Peek sprawdza w publicznym trackerze Amazona, czy numer jest tam dostępny. Gdy Amazon potwierdzi przesyłkę, zobaczysz komunikat: „Publiczne śledzenie jest dostępne w Amazon Shipping. Peek będzie aktualizować informacje o tej dostawie.” Jeśli tracker nie zna numeru, Peek traktuje go jako prywatną dostawę [Amazon Logistics](carrier:amazon-logistics) widoczną po zalogowaniu w zamówieniach. Gdy zapytanie chwilowo się nie powiedzie, użyj przycisku „Sprawdź ponownie”. Na liście pod „Zmień przewoźnika” nie ma osobnej pozycji Amazon Shipping — jedyną drogą jest ten automatyczny test, bez podawania kodu pocztowego.

Peek pokazuje aktualny status, oś czasu z miejscowością, regionem i krajem każdego skanu na mapie, przewidywany dzień dostawy (z wyjątkiem numerów `TBA`, `TBC` i `TBM`) oraz godzinę doręczenia w strefie Twojego urządzenia („Godziny w Twojej strefie czasowej: …”). Zamiast długich komunikatów Amazona wyświetla zwięzłe polskie etapy — takie jak „W doręczeniu” czy „Dostarczona” — pozostawiając po angielsku jedynie wpisy pośrednie `Arrived at sorting center`, `Arrived at delivery center` i `Departed facility`. Nazwa sklepu, Twoje imię i adres, podpis oraz zdjęcie z doręczenia nie są wyświetlane. Ponieważ publiczny tracker Amazona przechowuje historię przez 45 dni i jest jedynym źródłem danych, po wygaśnięciu udostępniania Peek informuje o tym i kończy odpytywanie (nie przełącza też paczki na innego kuriera).

Peek sprawdza aktualizacje nawet co 10 minut, gdy masz przesyłkę otwartą albo w dniu dostawy z włączonymi powiadomieniami, a w pozostałych przypadkach — oraz po dwóch dniach bez zmian lub po 12 godzinach na etapie „Zarejestrowana” bądź „Przyjęta przez przewoźnika” — co godzinę. W tych samych warunkach na etapie „W doręczeniu” od 6:00 czasu lokalnego sprawdza nawet co 2 minuty, z alertami „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”.

## Pytania o śledzenie Amazon Shipping

### Czy Amazon Shipping to to samo co Amazon Logistics?

Nie do końca: Amazon Shipping to usługa kurierska Amazona dla zewnętrznych firm i sprzedawców (zarówno z marketplace’u Amazona, jak i z innych sklepów online), z publicznym śledzeniem po numerze przesyłki, natomiast [Amazon Logistics](carrier:amazon-logistics) doręcza własne zamówienia sklepu Amazon śledzone po zalogowaniu w sekcji „Moje zamówienia”.

### Czy istnieje polska strona Amazon Shipping?

Nie ma osobnej polskiej witryny `track.amazon`: europejski tracker działa dla Wielkiej Brytanii, Francji, Włoch, Hiszpanii i Indii, dlatego dla numerów zaczynających się od `PL` Peek odpytuje francuski tracker `track.amazon.fr`.

### Ile trwa dostawa Amazon Shipping?

Jeśli tracker Amazona podaje przewidywany dzień doręczenia, Peek wyświetla go na karcie paczki (poza seriami `TBA`, `TBC` i `TBM`). Przy zakupach w UE, o ile nie umówiono się ze sklepem inaczej, towar powinien zostać dostarczony w ciągu maksymalnie 30 dni.

:::sources
- [About Amazon España: Carta anual a los accionistas 2023](https://www.aboutamazon.es/noticias/noticias-de-la-compania/carta-anual-a-los-accionistas-2023-de-nuestro-ceo-andy-jassy) – definicja Amazon Shipping jako sieci dostaw ostatniej mili Amazona dla przesyłek innych firm
- [About Amazon Polska: 5 wskazówek, jak rozpoznawać oszustów podszywających się pod Amazon](https://www.aboutamazon.pl/wiadomosci/zakupy-na-amazon/5-wskazowek-jak-rozpoznawac-oszustow-podszywajacych-sie-pod-amazon-i-unikac-ich) – brak próśb o płatność przez telefon lub e-mail, zakaz kart podarunkowych i zgłaszanie na reportascam@amazon.com
- [Your Europe: Wysyłka i dostawa](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_pl.htm) – odpowiedzialność sprzedawcy do chwili odbioru, termin 30 dni, dodatkowy termin i zwrot pieniędzy
- [Your Europe: Pytania i odpowiedzi dotyczące wysyłki i dostawy](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/faq/index_pl.htm) – ciężar dowodu doręczenia po stronie sprzedawcy oraz wymiana lub zwrot środków
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – rozpoznawane prefiksy PL i TBA/TBC/TBM, przypisanie do track.amazon.fr i track.amazon.com, 45-dniowe archiwum oraz interwały odpytywania
:::
