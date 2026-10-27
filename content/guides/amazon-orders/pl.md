---
title: Śledzenie zamówienia Amazon: Amazon, sprzedawcy, przewoźnicy
description: Kto wysyła Twoje zamówienie z Amazon.pl, który przewoźnik je doręcza, czemu numer śledzenia się zmienia lub go nie ma i jak działa Gwarancja od A do Z.
slug: sledzenie-zamowienia-amazon
picture: Telefon pokazuje postęp zamówienia, dwie furgonetki przekazują sobie paczkę, a Pip się przygląda.
published: 2026-10-16
updated: 2026-10-16
---

Zamówienie z Amazona śledzisz w sekcji Moje zamówienia: wybierz zamówienie i kliknij „Śledź przesyłkę”. Tam Amazon pokazuje przewoźnika, identyfikator przesyłki i każdą aktualizację. Kto pomoże, gdy coś pójdzie nie tak, zależy od tego, kto wysyła. Amazon zajmuje się własnymi zamówieniami i produktami z „Realizacją przez Amazon”, a sprzedawca, który wysyła sam, wybiera przewoźnika i odpowiada za dostawę.

## Kto wysyła moje zamówienie z Amazona: Amazon czy sprzedawca?

Sprawdź nazwę sprzedawcy na stronie produktu albo w sekcji Moje zamówienia i zwróć uwagę na odznakę Prime.

| Sytuacja | Co to znaczy | Kto pomaga przy dostawie |
| --- | --- | --- |
| Sprzedaje i wysyła Amazon | Towar Amazona | Amazon |
| Sprzedaje sprzedawca, wysyła Amazon | „Realizacja przez Amazon” (FBA): produkt sprzedawcy, pakowany i wysyłany z centrum logistycznego Amazon | Amazon |
| Sprzedaje i wysyła sprzedawca, bez Prime | Sprzedawca wysyła sam, przewoźnikiem, którego wybierze | Najpierw sprzedawca |

Amazon pisze wprost: „Jeśli produkty sprzedawcy zewnętrznego nie mają odznaki Amazon Prime, to sprzedawca ten będzie realizował zamówienie, zajmie się dostawą i ewentualnym zwrotem.” Przy produktach z „Realizacją przez Amazon” obsługę klienta i zwroty zapewnia Amazon.

## Który przewoźnik doręcza moją paczkę z Amazona?

Pokazuje go „Śledź przesyłkę”, razem z identyfikatorem przesyłki. Jeśli przewoźnikiem jest sam Amazon, to [Amazon Logistics](carrier:amazon-logistics), własna sieć dostaw Amazona. Na stronie z danymi kontaktowymi Amazon.pl wymienia tych przewoźników:

| Przewoźnik | Co mówi Amazon.pl |
| --- | --- |
| [DHL Polska](carrier:dhl-ecommerce-pl) | U niego zmienisz datę dostawy (tylko DHL) |
| [InPost](carrier:inpost) | U niego przedłużysz czas odbioru z automatu paczkowego (tylko InPost) |
| [Poczta Polska](carrier:poczta-polska) | Śledzenie w jej serwisie |
| [UPS](carrier:ups) Polska | Śledzenie w jego serwisie |
| [DPD Polska](carrier:dpd-pl) | Śledzenie w jego serwisie |

Przewoźnika przydziela system Amazona i nie da się wykluczyć konkretnej firmy. Amazon radzi: „Jeśli chcesz skorzystać z konkretnego przewoźnika, wybierz dostawę do punktu odbioru.” Sprzedawca, który wysyła sam, może użyć dowolnego przewoźnika. Gdzie dokładnie jest numer w każdym sklepie: [jak znaleźć numer przesyłki](guide:find-tracking-number).

## Jak wygląda numer śledzenia Amazon?

Są dwa rodzaje, a do tego numer zamówienia, który nie jest żadnym z nich.

Amazon nie publikuje formatu swoich numerów. Te kształty Peek rozpoznaje jako numery Amazona, tu z wymyślonymi cyframi:

:::anatomy TBA 123456789012
- TBA | Litery Amazona. `TBC` i `TBM` działają tak samo
- 123456789012 | Dwanaście cyfr
:::

| Kształt (wymyślony) | Co to jest |
| --- | --- |
| `TBA123456789012` | Amazon: `TBA`, `TBC` lub `TBM` i 12 cyfr |
| `PL1234567890` | Amazon w Europie: kod kraju (`PL` i inne) i 10 cyfr |
| `123-1234567-1234567` | Twój numer zamówienia Amazon: 3, 7 i 7 cyfr. To nie jest numer śledzenia |
| Cokolwiek innego | Numer przewoźnika, na przykład InPost albo Poczty Polskiej |

Numer przewoźnika działa na jego stronie, jak opisują [formaty numerów przesyłek](guide:tracking-number-formats). Nie wiesz, który masz? Wpisz go tutaj:

:::checker
:::

## Dlaczego zmienił się numer śledzenia Amazon albo mam dwa?

Zwykle z jednego z tych powodów:

- **Kilka produktów, kilka paczek.** Jeśli zamówisz kilka produktów, mogą mieć oddzielne daty dostawy i informacje o śledzeniu. Brakujący produkt mógł zostać wysłany osobno.
- **Paczka z zagranicy.** „W przypadku przesyłek wysyłanych z centrów logistycznych za granicą możliwość śledzenia będzie dostępna po dotarciu paczki do centrum dystrybucyjnego przewoźnika w Polsce.”
- **Kilku przewoźników.** „Niektóre paczki są przewożone przez wielu przewoźników.”
- **Sprzedawca ma własnego przewoźnika.** Wtedy numer należy do InPost, DPD czy innej firmy, a nie do Amazona.

:::journey
- warehouse | Amazon lub sprzedawca | Kilka produktów może wyjść w kilku paczkach, każda z własnym numerem
- label | Etykieta | Baza danych śledzenia bywa aktualizowana z opóźnieniem
- handover | Przewoźnik w Polsce | Paczka z zagranicy: śledzenie od jego centrum w Polsce
- truck | W trakcie dostawy | Niektórzy przewoźnicy doręczają do 22.00
- home | Ty | Może być oznaczona jako dostarczona do 48 godzin wcześniej
:::

Gdy pierwszy przewoźnik poda partnera, który przejmuje paczkę, [Peek](/) zachowuje pierwszą historię, przechodzi na partnera, gdy jego śledzenie pokaże nowe postępy, i pokazuje na karcie jego nazwę i numer.

## Dlaczego nie ma śledzenia mojego zamówienia z Amazona?

Amazon podaje te powody na stronach „Brak informacji o śledzeniu” i „Śledzenie paczki”:

- **Opóźnienie w bazie.** Aktualizacja bazy danych śledzenia może się spóźniać.
- **Przesyłka bez śledzenia.** „Niektórych przesyłek, takich jak standardowe przesyłki międzynarodowe, nie można śledzić.”
- **Sprzedawca nie przekazał danych.** „Sprzedawcy korzystający z platformy handlowej czasem nie udostępniają firmie Amazon informacji dotyczących śledzenia ich zamówień.” Przesyłkę od sprzedawcy śledzisz tylko wtedy, gdy przekaże on dane i gdy wybrano opcję wysyłki z możliwością śledzenia.
- **Paczka jeszcze za granicą.** Śledzenie pojawi się, gdy dotrze do centrum przewoźnika w Polsce.

Jeśli minęła szacowana data dostawy, a nic się nie zmienia, Amazon radzi: „poczekaj jeszcze dzień lub dwa”. Brak śledzenia przy paczce od sprzedawcy? Wejdź w Moje zamówienia, wybierz „Problem z zamówieniem” i napisz do sprzedawcy. Gdy śledzenie stoi od dni: [dlaczego śledzenie się nie aktualizuje](guide:tracking-not-updating).

## Co zrobić, gdy zamówienie z Amazona się spóźnia albo jest „dostarczone”?

Statusy Amazona w Moich zamówieniach to między innymi `Dostawa`, `W trakcie dostawy`, `Dostarczono`, `Odebrano` i `Dostawa niemożliwa`. Ten ostatni znaczy, że paczka wraca do Amazona, a ten po jej otrzymaniu automatycznie zwraca ci pieniądze. W pozostałych przypadkach działaj w tej kolejności:

:::steps
- Odczekaj 48 godzin | „Większość opóźnionych paczek dociera w ciągu 48 godzin od podanej daty dostawy.” Paczka może też zostać oznaczona jako dostarczona do 48 godzin przed doręczeniem.
- Rozejrzyj się | Sprawdź miejsce dostawy, skrzynkę pocztową, awizo i zapytaj sąsiadów. Niektórzy przewoźnicy doręczają do 22.00.
- Zapytaj przewoźnika | „Śledź przesyłkę” pokazuje, kto to jest. Strona „Informacje kontaktowe przewoźników” podaje telefony i linki.
- Napisz do sprzedawcy | Jeśli to on sprzedał i wysłał: Moje zamówienia, „Problem z zamówieniem”. „Sprzedawcy odpowiadają e-mailem w ciągu 48 godzin.”
- Zwróć się do Amazona | Zamówienia wysłane przez Amazon: obsługa klienta, w ciągu 30 dni od spodziewanej daty dostawy. Zamówienia od sprzedawców: Gwarancja od A do Z.
:::

Więcej o każdym przypadku: [paczka dostarczona, ale jej nie ma](guide:delivered-not-received) i [zaginiona paczka](guide:lost-parcel).

### Kiedy mogę skorzystać z Gwarancji od A do Z?

Obejmuje zamówienia u sprzedawcy z platformy Marketplace. Gdy paczka nie dotarła, muszą minąć trzy dni od ostatniej przewidywanej daty dostawy albo od momentu, gdy śledzenie pokazało doręczenie, zależnie od tego, co nastąpi wcześniej. Musisz też skontaktować się ze sprzedawcą ponad 48 godzin wcześniej, bez odpowiedzi ani rozwiązania.

| Termin | Amazon.pl |
| --- | --- |
| Odpowiedź sprzedawcy | 48 godzin |
| Czekanie po ostatniej przewidywanej dacie | Trzy dni |
| Wniosek możliwy | Najwyżej 90 dni od ostatniej możliwej daty dostawy |

Wniosek składasz w Moich zamówieniach, opcją „Problem z zamówieniem”. Przy produktach z Amazon Global Store i produktach od sprzedawców dostarczanych w ramach Prime kontaktujesz się od razu z Amazonem. Jeśli sprzedawca nie zwrócił ci dodatkowych kosztów, na przykład cła, też możesz je zgłosić.

## Czy mogę śledzić paczkę z Amazona poza Amazonem?

To zależy od numeru. Wklej go do [Peek](/):

- **Numer przewoźnika** (InPost, DPD, DHL, Poczta Polska…) śledzisz jak każdą inną paczkę.
- **Numer Amazona** albo link track.amazon: Peek najpierw pyta publiczny system śledzenia Amazona. Jeśli Amazon odpowie, paczka jedzie z Amazon Shipping, usługą dostaw Amazona dla innych sklepów i sprzedawców, albo w ramach Multi-Channel Fulfilment, gdzie Amazon wysyła zamówienia z innych stron, często w opakowaniach bez logo Amazona. Wtedy Peek śledzi ją sam.
- Jeśli nie, to Amazon Logistics dowozi zamówienie z Amazona, a Amazon pokazuje je tylko na twoim koncie. Peek mówi ci o tym i proponuje „Otwórz moje zamówienia Amazon”, które otwiera twoje zamówienia na stronie Amazona pasującej do numeru: amazon.pl dla `PL`. Peek nigdy nie prosi o login do Amazona.
- **Numer zamówienia** dostaje komunikat „To wygląda na numer zamówienia Amazon”. Użyj zamiast niego numeru ze „Śledź przesyłkę” albo z e-maila o wysyłce.

:::sources
- [Amazon.pl: Informacje kontaktowe przewoźników](https://www.amazon.pl/gp/help/customer/display.html?nodeId=GBPEXEXYULHB5WCH) – przewoźnicy, śledzenie po dotarciu do Polski, DHL i InPost
- [Amazon.pl: Zamawianie u sprzedawcy zewnętrznego](https://www.amazon.pl/gp/help/customer/display.html?nodeId=GEF528GN65XSJ7V8) – odznaka Prime, sprzedawca odpowiada za dostawę
- [Amazon.pl: Realizacja przez serwis Amazon](https://www.amazon.pl/gp/help/customer/display.html?nodeId=G239KSGYPUFUY8TQ) – FBA, obsługa klienta i zwroty
- [Amazon.pl: Śledzenie paczki](https://www.amazon.pl/gp/help/customer/display.html?nodeId=GENAFPTNLHV7ZACW) – oddzielne paczki, śledzenie przesyłek od sprzedawców i z zagranicy, statusy, 48 godzin, przydział przewoźników, punkt odbioru
- [Amazon.pl: Brak informacji o śledzeniu](https://www.amazon.pl/gp/help/customer/display.html?nodeId=G6ZFEB9ZDU7QHMER) – powody braku śledzenia, dzień lub dwa
- [Amazon.pl: Odnajdywanie zaginionej przesyłki, która została oznaczona jako dostarczona](https://www.amazon.pl/gp/help/customer/display.html?nodeId=GCU8BWGTQNJKQEBS) – 22.00, wielu przewoźników, 48 godzin, 30 dni
- [Amazon.pl: Skontaktuj się ze sprzedawcą zewnętrznym](https://www.amazon.pl/gp/help/customer/display.html?nodeId=GLC8ZMBWMBTR6QZZ) – 48 godzin
- [Amazon.pl: Gwarancja od A do Z](https://www.amazon.pl/gp/help/customer/display.html?nodeId=GQ37ZCNECJKTFYQV) – 90 dni, trzy dni, 48 godzin, Global Store, Prime, cło
- [Amazon Shipping UK](https://shipping.amazon.co.uk/) – dostawy dla sklepów i sprzedawców Amazon
- [Amazon UK: Multi-Channel Fulfilment](https://sell.amazon.co.uk/fulfilment-by-amazon/fba-multi-channel) – zamówienia z innych kanałów, opakowania bez logo
- [Universal Parcel Scraper: dane przewoźników](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers) – kształty numerów Amazona, co sprawdza Peek
:::
