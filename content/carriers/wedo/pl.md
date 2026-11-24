---
title: Śledzenie One by Allegro CZ (WE|DO): kody i odbiór
description: Śledź paczki One by Allegro CZ (WE|DO, IN TIME, Uloženka) w Czechach: 11-znakowe kody, One Box 48 godz., One Point 5 dni oraz kontakt.
slug: sledzenie-one-by-allegro-cz
published: 2026-11-24
updated: 2026-11-24
---

One by Allegro CZ to czeska firma doręczeniowa grupy Allegro (jej strona [onedelivery.cz](https://onedelivery.cz/) należy do Allegro Retail a.s.). Doręcza zamówienia z allegro.cz i innych sklepów internetowych w trzech usługach: **One Box** (ponad 5244 automatów paczkowych, w tym partnerskie AlzaBox i OX Box), **One Point** (1960 partnerskich punktów odbioru) oraz **One Courier** (doręczenie pod drzwi w całych Czechach za pośrednictwem ponad 350 aut i kurierów). Wyszukiwarka przewoźników w Peek znajduje ją też pod nazwami WE|DO, IN TIME lub Uloženka.

Czeską paczkę sprawdzisz na [onedelivery.cz/sledovani-zasilky](https://onedelivery.cz/sledovani-zasilky) albo wklejając swój 11-znakowy numer śledzenia w pole powyżej.

## Format numeru śledzenia One by Allegro CZ (WE|DO)

Czeskie przesyłki One by Allegro / WE|DO korzystają ze zwięzłego **11-znakowego kodu alfanumerycznego**:

| Format (wymyślony) | Co oznacza |
| --- | --- |
| `2BM48291736` | 1 cyfra + 1 litera + 1 litera/cyfra + 8 cyfr (łącznie 11 znaków) — standardowy numer One by Allegro CZ (WeDo). |
| `48P48291736` | 2 cyfry + 1 litera + 8 cyfr (łącznie 11 znaków) — alternatywny czeski format One by Allegro (WeDo). |

Prefiksy zaczynające się od `7D` + cyfra lub `7X` + cyfra są wyłączone z tej reguły; wszystkie pozostałe pasujące 11-znakowe kody jednoznacznie wskazują na **One by Allegro CZ** z wysoką pewnością. Peek odczytuje także bezpośrednie linki z `onedelivery.cz/sledovani-zasilky?numer=...`, `onedelivery.cz/?numer=...`, `trace.wedo.cz/index.php?orderNumber=...` i `trace.intime.cz/index.php?orderNumber=...`. Polskie przesyłki Allegro One korzystają z osobnego trackera — zobacz [Allegro One](carrier:allegro-one) i poradnik o [formatach numerów śledzenia](guide:tracking-number-formats).

## Co oznaczają statusy śledzenia One by Allegro CZ

Publiczne API śledzenia One by Allegro CZ zwraca angielskie komunikaty, każdy z godziną i przesunięciem względem UTC. Oto najczęstsze z nich:

| Komunikat One by Allegro CZ | Co oznacza |
| --- | --- |
| `Parcel has been prepared by the sender` | Sklep utworzył etykietę nadawczą; kurier nie odebrał jeszcze paczki. |
| `Parcel has been picked up by the courier` | One by Allegro CZ odebrało paczkę od nadawcy. |
| `Parcel has been dispatched to its destination point` | Opuściła centrum nadawcze w kierunku regionu docelowego lub sieci odbioru. |
| `Parcel has been accepted at the sorting center` | Przetworzona w sortowni One by Allegro. |
| `Parcel has been accepted at the branch` | Dotarła do lokalnego oddziału obsługującego Twój adres lub punkt odbioru. |
| `Parcel has been released for delivery` | Załadowana do samochodu dostawczego do dzisiejszego doręczenia kurierem lub umieszczenia w wybranym One Box / One Point. |
| `Parcel is awaiting pick-up` | Gotowa do odbioru w automacie One Box lub punkcie One Point (sprawdź PIN w SMS-ie, e-mailu lub aplikacji). |
| `Parcel has been delivered` | Przekazana odbiorcy albo odebrana z automatu lub punktu odbioru. |

Jeśli tracker zwróci nietypowy komunikat mogący zawierać prywatny tekst dotyczący odbiorcy, Peek zastępuje ten wiersz wpisem `Tracking update`, zachowując jego datę i godzinę w historii.

## Zasady doręczenia One Box, One Point i One Courier

One by Allegro CZ publikuje jasne terminy odbioru i zasady doręczeń na [onedelivery.cz](https://onedelivery.cz/):

- **Automaty One Box ([onedelivery.cz/one-box](https://onedelivery.cz/one-box)):** czynne **24/7** w ponad 5244 automatach (One Box, AlzaBox i OX Box). Odbierz w ciągu **48 godzin** od umieszczenia w skrytce, wpisując numer telefonu odbiorcy i kod PIN przysłany SMS-em, e-mailem, w aplikacji One by Allegro lub w aplikacji Allegro (przy ekranie automatu aplikacja nie jest potrzebna). Pobranie (*dobírka*) opłacisz przez link z powiadomienia albo kartą w terminalu One Box.
- **Punkty One Point ([onedelivery.cz/one-point](https://onedelivery.cz/one-point)):** w 1960 sklepach partnerskich w godzinach ich otwarcia paczki czekają standardowo **5 dni roboczych** (można bezpłatnie przedłużyć nawet o **3 dodatkowe dni robocze** przez obsługę klienta; w szczycie przedświątecznym czas przechowywania w One Point skraca się z 5 do **3 dni**, podczas gdy One Box zachowuje 48 godzin). Limity dla One Point to **20 kg**, najdłuższy bok **1,2 m** i objętość **0,2 m³**. Pobranie opłacisz gotówką lub kartą, jeśli punkt ma terminal.
- **Kurier One Courier ([onedelivery.cz/one-courier](https://onedelivery.cz/one-courier)):** standardowe doręczenie w **następny dzień roboczy (`D+1`)** po odbiorze, w **dni robocze między 08:00 a 18:00**, do pierwszych zamykanych drzwi (kurierzy nie mają obowiązku wnoszenia paczek po schodach, a skrytki pocztowe nie są obsługiwane). W dniu doręczenia otrzymasz powiadomienie SMS-em, e-mailem lub w aplikacji. Pobranie zapłacisz gotówką (zalecana odliczona kwota) albo kartą bez dopłat. Termin doręczenia lub przekierowanie kwalifikującej się paczki do punktu odbioru zmienisz w aplikacji One by Allegro albo przez obsługę klienta; zmiana adresu wymaga kontaktu z obsługą.

Jeśli przegapisz termin odbioru lub szukasz paczki, zobacz poradniki o [automatach paczkowych](guide:parcel-lockers), [nieodebranej paczce](guide:uncollected-parcel) oraz [braku aktualizacji śledzenia](guide:tracking-not-updating). Inne czeskie sieci porównasz na stronach [Packeta](carrier:packeta), [PPL](carrier:ppl) i [Česká pošta](carrier:ceska-posta).

## Śledzenie One by Allegro CZ w Peek

Wklej swój 11-znakowy kod One by Allegro CZ albo link `onedelivery.cz` lub `trace.wedo.cz` w Peek, a Peek rozpozna **One by Allegro CZ** natychmiast. Jeśli trzeba zmienić przewoźnika, użyj „Zmień” podczas dodawania paczki lub „Zmień przewoźnika” na ekranie śledzenia.

Peek odpytuje bezpośrednio publiczny endpoint One by Allegro CZ (`edge.onedelivery.cz`) przez otwarty [adapter One by Allegro CZ w universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/wedo), z automatycznym ponowieniem, gdy sieć nie odpowie na czas. Zapisuje dokładne znaczniki czasu z przesunięciem względem UTC oraz moment doręczenia (`delivered_at`), odrzucając dowolne teksty mogące ujawniać dane osobowe. Publiczne API nie podaje szacowanej daty doręczenia, miasta oddziału ani wagi.

Peek przekłada komunikaty na etapy: „Zarejestrowana” (`Parcel has been prepared by the sender`), „Przyjęta przez przewoźnika” (`Parcel has been picked up by the courier`), „W transporcie” (nadanie w drogę, sortownia i oddział), „W doręczeniu” (`Parcel has been released for delivery`), „Do odbioru” (`Parcel is awaiting pick-up`) oraz „Dostarczona” (`Parcel has been delivered`). Odczytuje też inne komunikaty Allegro One, np. `Parcel has been returned to the sender`.

Gdy masz przesyłkę otwartą w Peek albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza status nawet co 10 minut; w dniu, gdy One by Allegro CZ pokazuje `Parcel has been released for delivery`, co 2 minuty od godz. 6:00 czasu czeskiego, dopóki tracker odpowiada. W pozostałym czasie Peek sprawdza co godzinę. Po 12 godzinach wyłącznie na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika” albo po dwóch dniach bez zmian sprawdza co godzinę niezależnie od sposobu śledzenia, a po siedmiu dniach na „Zarejestrowana” — co 6 godzin. Gdy One by Allegro CZ nie odpowiada i Peek śledzi przesyłkę wyłącznie przez usługi śledzące, sprawdza co godzinę, co 3 godziny po spokojnym dniu i nawet co 15 minut w dniu, w którym przesyłka jest w doręczeniu, dopóki masz ją otwartą lub włączone powiadomienia.

Jeśli najnowszy skan ma cztery dni, a przesyłka nadal widnieje jako „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie”, Peek oznacza ją dniem tego skanu, np. „Brak aktualizacji od pon 12 paź”. Na stronie przesyłki pojawia się wtedy komunikat „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika One by Allegro CZ.” wraz z linkiem „Poproś przewoźnika One by Allegro CZ o pomoc” do strony kontaktowej. Po trzech tygodniach bez nowego skanu pojawia się informacja „Od ponad trzech tygodni nic nowego. Jeśli przesyłka wciąż nie dotarła, zapytaj sprzedawcę. Peek dalej pyta przewoźnika One by Allegro CZ.”

W ustawieniach alertów Peek wybierz „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”, aby otrzymywać wybrane powiadomienia.

## Kontakt z One by Allegro CZ

- **Formularz zapytań i wsparcie:** [onedelivery.cz/kontakt](https://onedelivery.cz/kontakt) (kliknij *Dotaz na zásilku* dla pytań kupujących i sprzedawców, zgubionego kodu PIN, darmowego 3-dniowego przedłużenia w One Point lub zmiany adresu)
- **Oficjalna strona śledzenia:** [onedelivery.cz/sledovani-zasilky](https://onedelivery.cz/sledovani-zasilky)
- **Informacje o usługach:** [One Box](https://onedelivery.cz/one-box), [One Point](https://onedelivery.cz/one-point) oraz [One Courier](https://onedelivery.cz/one-courier)
- **Strona główna:** [onedelivery.cz](https://onedelivery.cz/)

## Często zadawane pytania

### Czy WE|DO, IN TIME i One by Allegro CZ to ten sam przewoźnik?

Peek traktuje je jako jednego przewoźnika: wyszukiwarka znajduje One by Allegro CZ pod nazwami WE|DO, IN TIME lub Uloženka, a linki `trace.wedo.cz` i `trace.intime.cz` oraz 11-znakowe numery śledzą się pod One by Allegro CZ. Strona `onedelivery.cz` nie opisuje historii marek.

### Jak długo paczka czeka w One Box lub One Point?

W **One Box** (w tym AlzaBox i OX Box) paczki czekają **48 godzin** od włożenia do skrytki. W punktach **One Point** standardowy czas to **5 dni roboczych** (możliwe darmowe przedłużenie do 3 dni roboczych przez obsługę klienta), skracany do **3 dni** w szczycie przedświątecznym.

### Czy mogę zapłacić za pobranie (dobírka) kartą?

Tak. Kurierzy One Courier przyjmują gotówkę (zalecana odliczona kwota) lub kartę bez opłat dla odbiorcy; One Box obsługuje płatność kartą przez link w powiadomieniu lub w terminalu automatu; a One Point przyjmuje gotówkę lub kartę, gdy punkt posiada terminal.

### Co zrobić, jeśli zgubisz kod odbioru One Box lub One Point?

Kody odbioru przychodzą SMS-em, e-mailem oraz w aplikacjach One by Allegro i Allegro. Jeśli nigdzie nie możesz go znaleźć, napisz do obsługi klienta przez formularz na [onedelivery.cz/kontakt](https://onedelivery.cz/kontakt).

:::sources
- [One by Allegro CZ (onedelivery.cz)](https://onedelivery.cz/)
- [One by Allegro CZ — Sledování zásilky](https://onedelivery.cz/sledovani-zasilky)
- [One by Allegro CZ — One Box](https://onedelivery.cz/one-box)
- [One by Allegro CZ — One Point](https://onedelivery.cz/one-point)
- [One by Allegro CZ — One Courier](https://onedelivery.cz/one-courier)
- [One by Allegro CZ — Kontakt](https://onedelivery.cz/kontakt)
- [universal-parcel-scraper One by Allegro CZ adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/wedo)
:::
