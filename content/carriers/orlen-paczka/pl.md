---
title: ORLEN Paczka śledzenie: numer, statusy i odbiór paczki
description: Śledzenie przesyłki ORLEN Paczka: gdzie jest numer paczki, co znaczą statusy, ile czasu masz na odbiór z automatu, reklamacja i infolinia.
slug: orlen-paczka-sledzenie
published: 2026-10-11
updated: 2026-10-27
---

ORLEN Paczka, dawniej Paczka w RUCHu, to polska sieć, która dowozi paczki do automatów paczkowych, na stacje ORLEN i do punktów partnerskich, a nie pod drzwi. Paczkę śledzisz na stronie „Śledź paczkę” na orlenpaczka.pl albo wklejasz numer w pole powyżej. Numer paczki ma 13 cyfr i przychodzi w e-mailu oraz w powiadomieniu w aplikacji ORLEN Paczki, gdy nadawca nada paczkę.

## Gdzie jest numer paczki ORLEN Paczka

Numer paczki to 13 cyfr spod kodu kreskowego na etykiecie; formularz reklamacji nazywa go „Numer paczki (EAN)”. Wpisuj go bez spacji. Pole śledzenia na orlenpaczka.pl przyjmuje numer paczki albo kod nadania.

| Kształt (wymyślony) | Co to jest |
| --- | --- |
| `2101234567890` | 13 cyfr od 21: paczka utworzona przez API ORLEN Paczki. |
| `2301234567890` | 13 cyfr od 23: paczka nadana z 10-cyfrowym kodem nadania; od 26: paczka utworzona w systemie WebTrucker. |
| `1234567890` | Kod nadania z 10 cyfr: nadawca używa go przy nadaniu paczki. |

Gdzie go szukać:

- **E-mail i powiadomienie w aplikacji ORLEN Paczki**, gdy nadawca nada paczkę, i drugi raz, gdy dotrze do automatu.
- **Przycisk „Kopiuj link do śledzenia”** na stronie śledzenia, który kopiuje link z numerem.

Kod odbioru (5 albo 6 cyfr) otwiera skrytkę, ale nie służy do śledzenia. Po numerze telefonu nie wyśledzisz paczki. Nie masz żadnej wiadomości? Zajrzyj do poradnika [gdzie znaleźć numer przesyłki](guide:find-tracking-number).

Paczki z Allegro Delivery mają numer zaczynający się od `AD`: ORLEN Paczka pisze, że śledzisz je tylko w aplikacji Allegro albo na allegro.pl. Numer międzynarodowy zakończony na PL, na przykład `RR000000005PL`, należy do [Poczty Polskiej](carrier:poczta-polska).

## Statusy ORLEN Paczka: co znaczą

Strona śledzenia pokazuje najnowszy komunikat, a pod nim „Szczegółowa trasa wysyłki” z datą i godziną każdego kroku. Statusy, w kolejności, w jakiej spotyka je paczka:

| ORLEN Paczka pisze | Co to znaczy dla Ciebie |
| --- | --- |
| `Twoja paczka jest w trakcie przygotowania przez nadawcę` | Nadawca utworzył paczkę. ORLEN Paczka jeszcze jej nie ma. |
| `Paczka nadana w punkcie` | Nadawca zostawił paczkę w punkcie albo automacie. |
| `Twoja paczka została odebrana przez kuriera i ruszyła w drogę` | Kurier zabrał paczkę od nadawcy. |
| `Twoja paczka została przyjęta w sortowni` | Paczka jest w pierwszej sortowni; po komunikacie pojawia się miasto. |
| `W sortowni centralnej` | Paczka jest w sortowni centralnej. |
| `Twoja paczka znajduje się w sortowni` | Paczka jest w sortowni, z której pojedzie do Twojego punktu odbioru. |
| `Twoja paczka jest już w drodze do punktu odbioru` | Paczka jedzie do Twojego punktu odbioru. |
| `Twoja paczka czeka na odbiór w punkcie` | Paczka czeka na Ciebie. Kod odbioru dostajesz SMS-em, e-mailem i w aplikacji. |
| `Paczka przekazana do przewoźnika w innym kraju. Dalszą drogę możesz śledzić w aplikacji Vinted` | Paczka z Vinted trafiła do przewoźnika za granicą. Dalej śledzisz ją w aplikacji Vinted. |
| `Cieszymy się, że Twoja paczka została już odebrana z punktu odbioru` | Paczka została odebrana z punktu. |

Przy niektórych paczkach te same kroki mają starsze brzmienie: `Paczka przygotowywana przez nadawcę`, `Paczka przyjęta w sortowni regionalnej` (bez miasta) i `Odebrana przez Klienta`, gdzie „Klient” to Ty.

Mylący bywa pierwszy status. „W trakcie przygotowania” znaczy, że sklep wygenerował etykietę, ale paczka jeszcze nie ruszyła; więcej o takich krokach w poradniku [statusy przesyłek](guide:tracking-statuses). Z kolei `Twoja paczka została odebrana przez kuriera i ruszyła w drogę` nie znaczy, że paczka jest u Ciebie: odebrał ją kurier od nadawcy.

Jeśli strona odpowie `Ups… przesyłki o tym numerze nie można śledzić.`, ORLEN Paczka podaje trzy powody: błędny numer, paczka już doręczona i odebrana z punktu albo paczka z Allegro Delivery. `Błędny numer paczki` oznacza, że wpisany ciąg nie jest ani 13-znakowym numerem paczki zaczynającym się od 2 lub 3, ani kodem nadania z 7 albo 10 cyfr.

## ORLEN Paczka: punkty odbioru, automaty i czas na odbiór

ORLEN Paczka dowozi paczkę tylko do punktu wybranego przy zamówieniu: do automatu paczkowego, na stację ORLEN (większość działa całą dobę) albo do punktu partnerskiego, na przykład osiedlowego sklepiku. Na jej stronach nie ma mowy o dostawie do domu, a paczki nie odbierzesz w centrum logistycznym.

**Kod odbioru.** Gdy paczka dotrze na miejsce, dostajesz SMS na numer telefonu podany przy zamówieniu, a kod jest też w e-mailu i w aplikacji ORLEN Paczki. W automacie wybierz „Odbierz paczkę” i wpisz kod z 5 albo 6 cyfr, zeskanuj kod QR albo otwórz skrytkę zdalnie w aplikacji. Paczkę może odebrać ktoś inny, jeśli dasz mu kod.

**Ile czasu na odbiór.** Według regulaminów ORLEN Paczki masz 3 dni robocze w punkcie z obsługą i 72 godziny w automacie, licząc od powiadomienia. W aplikacji przedłużysz czas w automacie bezpłatnie o jeden dzień, ale nie przy paczkach z Allegro Delivery. Nieodebrana paczka wraca do sklepu albo, przy nadawcach prywatnych jak sprzedający na Vinted, do punktu nadania.

**W niedzielę.** Paczkę odbierzesz w automacie i na wybranych stacjach ORLEN.

**Pełny albo zamknięty punkt.** Paczka może wtedy czekać do 2 dni roboczych w punkcie zastępczym, najwyżej 10 km dalej.

**Brak kodu.** Sprawdź spam, zablokowane wiadomości i blokady SMS u operatora; kod jest też w e-mailu i w aplikacji. Błędny numer telefonu poprawi tylko nadawca. Gdy automat się nie otwiera, dzwoń pod 22 33 33 999.

**Allegro Delivery.** Kod odbioru przysyła Allegro, a paczkę śledzisz i odbierasz w aplikacji Allegro w sekcji „Moje przesyłki”. Jak pisze ORLEN Paczka, Allegro odpowiada za każdy etap tej dostawy.

## Ile trwa dostawa ORLEN Paczka i co, gdy paczka nie dotarła

ORLEN Paczka podaje, że zamówienia ze sklepów docierają w 1 dzień roboczy (98% paczek), a paczki od osób prywatnych i z Vinted w 1–3 dni robocze. Regulaminy gwarantują dostawę do punktu najpóźniej w czwartym dniu roboczym po nadaniu. Na stronach ORLEN Paczki nie ma rady na śledzenie, które stoi w miejscu: zapytaj sklep, bo to on ma umowę z przewoźnikiem, i zajrzyj do poradnika [status przesyłki się nie zmienia](guide:tracking-not-updating).

**Opóźnienie.** Po przekroczeniu tego terminu możesz ubiegać się o zwrot; odszkodowanie to dwukrotność opłaty za usługę. **Zaginięcie.** Paczkę, która nie dotarła do punktu w ciągu 30 dni po terminie, uważa się za utraconą. **Uszkodzenie.** Widoczne zgłaszasz przy odbiorze, ukryte w ciągu 7 dni.

**Kto składa reklamację.** Nadawca, a Ty, gdy paczka została Ci doręczona albo odebrana, lub gdy nadawca przekaże Ci to prawo. Reklamację składasz tylko przez [formularz online](https://www.orlenpaczka.pl/reklamacje/), wybierając zagubienie, uszkodzenie albo opóźnienie, w ciągu 12 miesięcy od nadania. ORLEN Paczka odpowiada w 30 dni. Odpowiada za paczkę do 500 zł przy klientach indywidualnych i do 5000 zł przy klientach biznesowych, takich jak sklepy.

## ORLEN Paczka kontakt i infolinia

Kanały według strony kontaktowej ORLEN Paczki, stan na 11 października 2026 roku:

- **Infolinia:** 22 33 33 999, opłata według stawki operatora. Poniedziałek–piątek 7:00–22:00, sobota 9:00–19:00, niedziela 10:00–16:00, według [strony kontaktowej](https://www.orlenpaczka.pl/kontakt/). FAQ podaje w sobotę do 16:00.
- **Formularz kontaktowy** („Napisz do nas”) na [orlenpaczka.pl/formularz-kontaktowy](https://www.orlenpaczka.pl/formularz-kontaktowy/) oraz czat PaczBot na stronie.
- **Reklamacje:** formularz na orlenpaczka.pl/reklamacje.
- **Adres:** ORLEN Paczka, ul. Annopol 17A, 03-236 Warszawa.

> ORLEN Paczka nigdy nie wysyła SMS-ów z linkami do płatności ani do logowania i nie prosi o dopłatę przez link; ostrzega też, że nazwę nadawcy można sfałszować. Paczkę sprawdzaj, wpisując samodzielnie www.orlenpaczka.pl, a podejrzane wiadomości zgłaszaj do CERT Polska albo do ORLEN Paczki.

## Śledzenie paczki ORLEN Paczka w Peek

[Peek](/) rozpoznaje numery z 13 cyfr zaczynające się od 210 z poprawną cyfrą kontrolną. Taki sam kształt mają numery innych przewoźników, więc Peek pyta naraz ORLEN Paczkę, La Poste i Delhivery i pisze „ORLEN Paczka ma tę przesyłkę.”, gdy zna ją tylko ORLEN Paczka. Przy innych numerach z 13 cyfr od 2 albo 3 (na przykład od 23 albo 26) wybierz ORLEN Paczkę przez „Zmień przewoźnika” albo wklej link śledzenia z orlenpaczka.pl z 13-cyfrowym numerem. Kodów nadania z 7 i 10 cyfr Peek nie śledzi, a numery `AD` nie należą do ORLEN Paczki.

Statusy widzisz po polsku, tak jak pisze je ORLEN Paczka, z miastem sortowni na mapie, gdy komunikat je podaje, i z godziną odbioru. Godziny Peek odczytuje według czasu warszawskiego i pokazuje w strefie czasowej Twojego urządzenia. Gdy ORLEN Paczka oznaczy historię jako niepełną, widać tylko najnowszy skan. Peek nie pokazuje punktu odbioru, przewidywanej daty dostawy, wagi ani nadawcy, a numer paczki zwrotnej pomija. Paczka z Vinted przekazana przewoźnikowi w innym kraju nie jest śledzona dalej i zostaje w drodze. Odebrane paczki po kilku miesiącach znikają ze śledzenia ORLEN Paczki.

Peek sprawdza śledzenie w dzień co 10 minut, a w nocy co godzinę lub dwie, i może Cię powiadomić o każdej zmianie statusu, tylko o ważnych krokach albo tylko w dniu dostawy.

## Pytania o śledzenie ORLEN Paczka

### Czy ORLEN Paczka to dawniej Paczka w RUCHu?

Tak. Usługa ruszyła w 2021 roku, a stary adres paczkawruchu.pl prowadzi teraz na orlenpaczka.pl.

### Czy ORLEN Paczka dostarcza do domu?

Według stron ORLEN Paczki nie: paczka trafia do wybranego automatu, na stację ORLEN albo do punktu partnerskiego. Żadna z nich nie opisuje dostawy pod adres.

### Jak śledzić paczkę ORLEN Paczka za granicą?

ORLEN Paczka dostarcza w Polsce. Paczki z Vinted między Polską a krajami takimi jak Litwa, Czechy czy Szwecja przekazuje przewoźnikowi w drugim kraju, a dalszą drogę pokazuje aplikacja Vinted.

### Czy ORLEN Paczka ma przesyłki za pobraniem?

Nie. ORLEN Paczka nie realizuje dostaw z płatnością za pobraniem, więc za zamówienie płacisz sklepowi albo sprzedającemu wcześniej.

:::sources
- [ORLEN Paczka: Śledź Paczkę](https://www.orlenpaczka.pl/sledz-paczke/) – pole śledzenia, numery AD z Allegro, czas na odbiór, brak kodu SMS
- [ORLEN Paczka: skrypt strony śledzenia](https://www.orlenpaczka.pl/wp-content/themes/orlenpaczka/dist/track.dist.js) – przyjmowane numery, komunikaty o błędach, trasa wysyłki, link do śledzenia
- [ORLEN Paczka: serwis statusów](https://nadaj.orlenpaczka.pl/parcel/api-status) – aktualne brzmienie statusów
- [ORLEN Paczka: FAQ](https://www.orlenpaczka.pl/faq/) – powiadomienia, kody, odbiór, niedziela, zwroty, czas dostawy, dostawy zagraniczne, pobranie, odpowiedzialność, bezpieczeństwo
- [ORLEN Paczka: Kontakt](https://www.orlenpaczka.pl/kontakt/) – infolinia, godziny, formularze, PaczBot, adres
- [ORLEN Paczka: Reklamacje](https://www.orlenpaczka.pl/reklamacje/) – formularz reklamacji i powody
- [ORLEN Paczka: Regulamin dla klientów indywidualnych](https://www.orlenpaczka.pl/wp-content/uploads/2026/03/Regulamin-swiadczenia-klientom-indywidualnym-uslug-pocztowych-przez-ORLEN-Paczka-sp.zo_.o.-obowiazuje-od-16-marca-2026-r..pdf) – termin dostawy, czas na odbiór, punkt zastępczy, utrata, uszkodzenie, reklamacje
- [ORLEN Paczka: Regulamin dla przedsiębiorców](https://www.orlenpaczka.pl/wp-content/uploads/2026/03/Regulamin-swiadczenia-przedsiebiorcom-uslug-pocztowych-i-przewozowych-obowiazuje-od-1-maja-2026-r..pdf) – te same zasady dla paczek ze sklepów, limit odpowiedzialności
- [ORLEN Paczka: Aplikacja](https://www.orlenpaczka.pl/aplikacja/) – przedłużenie odbioru z automatu o jeden dzień
- [ORLEN Paczka: Jak odebrać przesyłki z Allegro Delivery](https://www.orlenpaczka.pl/jak-odebrac-przesylki-orlen-paczki-z-allegro-delivery/) – Allegro obsługuje kody, śledzenie i odbiór
- [ORLEN Paczka: O usłudze](https://www.orlenpaczka.pl/o-usludze-orlen-paczka/) – sieć punktów, dostawa ze sklepów w 1 dzień, Allegro
- [ORLEN Paczka: Punkty nadań i odbioru](https://www.orlenpaczka.pl/punkty-nadan-i-odbioru/) – automaty, stacje, punkty partnerskie
- [ORLEN Paczka: O firmie](https://www.orlenpaczka.pl/o-firmie/) – historia, kierunki Vinted
- [ORLEN Paczka: Specyfikacja API v1.26.005](https://www.orlenpaczka.pl/wp-content/uploads/2026/09/API_ORLENPaczka_v_1_26_005_PL.pdf) – prefiksy numerów, kod nadania
- [ORLEN Paczka: Cenniki i regulaminy](https://www.orlenpaczka.pl/cenniki-i-regulaminy/) – regulamin RUCH SA z 2021 roku
- [Paczka w RUCHu](https://www.paczkawruchu.pl/) – stary adres przekierowuje na orlenpaczka.pl
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – co Peek odczytuje
:::
