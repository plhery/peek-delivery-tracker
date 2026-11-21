---
title: Śledzenie GlobalPost: numery GP, statusy i doręczenie
description: Śledź paczkę GlobalPost: jak wyglądają numery GP i SDC, co oznaczają angielskie statusy, czym różnią się usługi, cło w Polsce i śledzenie w Peek.
slug: sledzenie-globalpost
published: 2026-11-21
updated: 2026-11-21
---

GlobalPost, część grupy Auctane, to międzynarodowa usługa wysyłkowa kupowana przez sprzedawców internetowych za pośrednictwem platform takich jak ShipStation (w USA i Wielkiej Brytanii) oraz Stamps.com (obsługuje ponad 220 krajów i podaje ponad 60 milionów doręczonych przesyłek). Paczka trafia najpierw do centrum wysyłkowego GlobalPost, gdzie otrzymuje międzynarodową etykietę i dokumentację celną, a następnie partnerzy pocztowi i komercyjni przewożą ją za granicę do lokalnej poczty lub firmy kurierskiej w kraju docelowym. Sprzedawca przekazuje Ci numer zaczynający się zazwyczaj od `GP` — wklej go w polu powyżej. Oficjalny tracker działa pod adresem `goglobalpost.com/tracking`; w katalogu Peek znajdziesz przewoźnika także pod nazwami `Go GlobalPost`, `GoGlobalPost`, `Global Post`, `Go Global Post` i `GlobalPost UK`.

## Numer śledzenia GlobalPost: jak wygląda

Peek rozpoznaje aktualne numery GlobalPost po ich budowie: `GP`, cztery litery oznaczające usługę, dziesięć cyfr oraz `US` lub `GB` wskazujące kraj nadania. Ze zmyślonymi literami i cyframi:

| Numer (przykładowy) | Co oznacza |
| --- | --- |
| `GPECON4829105736US` | `GP`, cztery litery, dziesięć cyfr i `US`: paczka GlobalPost nadana ze Stanów Zjednoczonych. |
| `GPSTND4829105736GB` | Ten sam układ zakończony `GB`: paczka nadana z Wielkiej Brytanii. |
| `SDC482910573649182` | `SDC` i 15 cyfr: starsza etykieta GlobalPost. Po samym kształcie numeru Peek nie przypisuje przewoźnika — wybierz GlobalPost przyciskiem „Zmień”. |
| `GPECON482910573US` | Tylko dziewięć cyfr: Peek nie rozpoznaje żadnego przewoźnika. Sprawdź, czy nie brakuje cyfry. |
| `LZ482910576US` | Amerykański numer pocztowy, a nie format GlobalPost: Peek wskazuje [USPS](carrier:usps). Jeśli ręcznie wybierzesz GlobalPost dla takiego kodu, Peek wyświetli, że przewoźnik „nie wydaje takich numerów”. |

Spacje, myślniki i małe litery są przez Peek ignorowane.

**Gdzie znaleźć numer.** W wiadomości e-mail ze sklepu o wysyłce albo na stronie zamówienia. Jeśli go nie widzisz, zobacz [jak znaleźć numer śledzenia](guide:find-tracking-number) i [formaty numerów śledzenia](guide:tracking-number-formats).

**Drugi numer śledzenia.** W sekcji `Final Mile Tracking By:` wyszukiwarka GlobalPost może podać własny numer firmy doręczającej w kraju docelowym i otwiera przesyłkę również po tym numerze (natomiast Peek odpytuje GlobalPost wyłącznie o kody `GP` lub `SDC`).

## Statusy śledzenia GlobalPost — co oznaczają

Tracker GlobalPost wyświetla skany od najnowszego pod nagłówkami kolejnych dni — każdy z godziną, angielskim opisem i przeważnie miejscem — opisując skany partnerów własnymi słowami. W typowej kolejności:

| GlobalPost podaje | Co to oznacza dla Ciebie | Peek pokazuje |
| --- | --- | --- |
| `Label Created` | Sprzedawca utworzył etykietę; paczka jeszcze nie wyruszyła. | „Zarejestrowana” |
| `Data Received` | GlobalPost otrzymał dane przesyłki od sprzedawcy, ale jeszcze nie samą paczkę. | „Zarejestrowana” |
| `Arrived at Partner Facility`, `Departed Partner Facility` | Partner zeskanował paczkę: np. USPS w drodze do GlobalPost lub operator pocztowy w kraju docelowym. | „W transporcie” |
| `Arrived at GlobalPost Facility` | Przesyłka dotarła do centrum wysyłkowego GlobalPost (skany USPS w drodze do centrum mogą pojawić się wcześniej). | „Przyjęta przez przewoźnika” |
| `Departed GlobalPost Facility` | Paczka opuściła centrum GlobalPost. | „W transporcie” |
| `Departed from Origin Country` | Wyleciała ze Stanów Zjednoczonych lub z Wielkiej Brytanii. | „W transporcie” |
| `In Transit`, `In-Transit` | W drodze. | „W transporcie” |
| `Arrived in Destination Country` | Dotarła do kraju docelowego. | „W transporcie” |
| `In Customs` | W trakcie odprawy celnej importowej. | „W urzędzie celnym” |
| `Cleared Customs` | Zwolniona przez urząd celny; jedzie dalej. | „W transporcie” |
| `Out for Delivery` | Wydana kurierowi do doręczenia w danym dniu. | „W doręczeniu” |
| `Attempted Delivery` | Nieudana próba doręczenia. | „Próba doręczenia” |
| `Exception, No Action Required` | Utrudnienie przy doręczeniu w kraju docelowym, które nie wymaga żadnego działania z Twojej strony. | „Zgłoszono problem” |
| `Delayed, No Action Required` | Opóźnienie w trasie niewymagające reakcji odbiorcy. | „Zgłoszono problem” |
| `Delivered` | Doręczona (albo zwrócona do nadawcy — patrz niżej). | „Dostarczona” |

Wpis może kończyć się uwagą GlobalPost, że aktualizacje mogą zająć trochę czasu: Peek odczytuje status przed tą uwagą, zachowując pełny opis, a wpis rejestracyjny dodany już po ruszeniu paczki nie cofa statusu. Pozostałe komunikaty przechodzą przez ogólne reguły Peek.

**Zwroty.** Wyszukiwarka GlobalPost kończy zwrot do nadawcy również statusem `Delivered`, pokazując w nagłówku miejscowość odbiorcy, podczas gdy sam skan ma wpisaną miejscowość nadawcy. Gdy miejscowość skanu zgadza się z miastem nadawcy w innym kraju i innym mieście niż odbiorcy, Peek pokazuje „Zwrócona nadawcy”.

## Kto doręcza paczki GlobalPost w Polsce

GlobalPost przekazuje przesyłki *Economy* i *Standard* operatorowi pocztowemu (np. USPS w USA) na odcinek międzynarodowy. Na swoich stronach nie publikuje listy doręczycieli według krajów, ale tracker potrafi podać nazwę firmy końcowej wraz z jej numerem.

**Zakres śledzenia zależy od usługi** wybranej przez sprzedawcę:

| Usługa | Śledzenie | Cło i podatki | Czas podawany sprzedawcy |
| --- | --- | --- | --- |
| `Economy` | do dotarcia do kraju i odprawy celnej | płacisz Ty (`DDU`) | `10 – 14 business days` |
| `Standard` | pod Twoje drzwi | płacisz Ty (`DDU`) | `6 – 10 business days` |
| `Plus`, `Plus – Duties & Taxes Included` | pod Twoje drzwi | płaci sprzedawca (`DDP`) | `5 – 8 business days` |
| `Flat Rate (formerly Go)` | pod Twoje drzwi | wliczone w cenę sprzedawcy | `6 – 10 business days` |

Przy wysyłce z USA usługa *GlobalPost Plus – Duties & Taxes Included* obejmuje m.in. **Polskę** (a także Kanadę i inne kraje europejskie), natomiast *Flat Rate (formerly Go)* działa do Kanady, Australii, Nowej Zelandii, Hongkongu i Meksyku. Zasady prób doręczenia i odbioru w placówce ustala przewoźnik końcowy — zobacz [Poczta Polska](carrier:poczta-polska), [nieudane doręczenie](guide:missed-delivery) oraz [nieodebrana paczka](guide:uncollected-parcel).

**Cło i opłaty w Polsce oraz Unii Europejskiej.** Paczka wysłana z USA lub z Wielkiej Brytanii przechodzi odprawę celną przy wjeździe do Unii Europejskiej (**Irlandia Północna** jest natomiast traktowana jako terytorium UE do celów celnych i VAT od towarów według Komisji Europejskiej). W usługach *Economy* i *Standard* (*Delivered Duties Unpaid*) cło i podatki pokrywa kupujący. W **Unii Europejskiej** od 1 lipca 2026 r. towary kupione online spoza UE w przesyłkach o wartości rzeczywistej do 150 € podlegają unijnemu cłu w wysokości 3 € za pozycję towarową (*per item*), a od 1 listopada 2026 r. dochodzi unijna opłata manipulacyjna (*Union handling fee*) w wysokości 2 € za pozycję bez względu na wartość przesyłki (jedna pozycja obejmuje towary w tej samej paczce o wspólnej klasyfikacji taryfowej, opisie oraz — gdy jest wymagane — pochodzeniu; powyżej 150 € obowiązują standardowe stawki celne), obok podatku VAT przy imporcie do **Polski**, o ile nie został już pobrany przy zakupie w ramach IOSS. Strony GlobalPost nie opisują sposobu pobierania opłaty 2 €: zapytaj sprzedawcę, czy była ujęta w cenie, i sprawdź [paczka zatrzymana przez urząd celny](guide:customs).

## Paczka GlobalPost stoi w miejscu, zaginęła lub wraca

**„Oczekiwanie na przewoźnika GlobalPost”.** Dopóki tracker przekierowuje z informacją, że nie znalazł szczegółów dla podanego numeru (`status=not_found`), Peek wyświetla „Oczekiwanie na przewoźnika GlobalPost” i sprawdza dalej. Jeśli po dwóch dniach GlobalPost nadal nie rozpoznaje numeru i żaden serwis śledzenia go nie znajduje, Peek pokazuje: „Po dwóch dniach przewoźnik nadal nie zna tego numeru. Sprawdź go, wybierz innego przewoźnika lub usuń przesyłkę.” Gdy przez kilka dni widnieje wyłącznie `Label Created`, zapytaj sprzedawcę, czy nadał paczkę.

**Brak aktualizacji od kilku dni.** GlobalPost zaznacza, że zagraniczne aktualizacje mogą pojawiać się z opóźnieniem (pogoda, święta, strajki, urząd celny), a w wariancie *Economy* śledzenie kończy się po odprawie celnej w kraju przeznaczenia. Po czterech dniach bez nowego skanu Peek wyświetla „Brak aktualizacji od czterech dni” wraz z uwagą o przelotach, cle i weekendach. Komunikat nie zawiera linku pomocy GlobalPost; gdy Peek przełączy śledzenie na firmę doręczającą i zna jej stronę pomocy, podaje tamten link. Zobacz [dlaczego śledzenie się nie aktualizuje](guide:tracking-not-updating).

**Brak paczki po 30 dniach.** Jeśli przesyłka nie dotarła w ciągu 30 dni od daty wysyłki, GlobalPost prosi o kontakt ze sprzedawcą: tylko on może złożyć reklamację w GlobalPost w ciągu `90 days` od nadania, a jej rozpatrzenie trwa do `3 weeks`. Zobacz [zagubiona paczka](guide:lost-parcel).

**Oznaczona jako dostarczona bez paczki albo niedoręczalna / zwrócona.** Gdy widnieje doręczenie, a przesyłki nie ma, skontaktuj się najpierw z firmą doręczającą wskazaną w śledzeniu, a potem ze sprzedawcą ([dostarczona, ale nieotrzymana](guide:delivered-not-received)). W razie błędnego lub niepełnego adresu, nieopłaconego cła lub podatku, pominiętych powiadomień celnych bądź towarów objętych ograniczeniami przesyłką zajmuje się poczta docelowa (zwykle odsyła ją do nadawcy albo przechowuje w placówce), nie przekazując GlobalPost daty ani nowego numeru zwrotnego; regulamin GlobalPost wyłącza przesyłki niedoręczalne (*undeliverable*) z odszkodowań, dlatego o zwrot środków poproś sklep ([zwrot do nadawcy](guide:returned-to-sender)).

## Obsługa klienta i kontakt z GlobalPost

Na stronie *Contact Support* GlobalPost udostępnia następujące kanały kontaktu:

- **E-mail**: `info@goglobalpost.com`.
- **Telefon**: `(888) 899-1255`, od poniedziałku do piątku w godzinach `6am`–`6pm` czasu pacyficznego (*Pacific Time*).
- **Formularz kontaktowy**: na `www.goglobalpost.com/contact-support/` wybierz `Recipient` i wpisz numer śledzenia (GlobalPost zaznacza, że dział wsparcia widzi te same dane co publiczna strona śledzenia).

W sprawie niedostarczonej paczki GlobalPost odsyła najpierw do sprzedawcy, który zlecił wysyłkę. Wyjaśnia też, że paczka wysłana z Chin za pośrednictwem [China Post](carrier:china-post) nie jest przesyłką GlobalPost — skontaktuj się wtedy z nadawcą.

**Uwaga na fałszywe SMS-y.** Oficjalna domena GlobalPost to `www.goglobalpost.com` (`goglobalpost.com`). Zanim klikniesz podejrzany link z wiadomości, sprawdź poradnik [fałszywe SMS-y o paczkach](guide:delivery-scam-texts).

## Śledzenie przesyłki GlobalPost w Peek

[Peek](/) od razu rozpoznaje numer `GP` („Wykryty przewoźnik”) i odpytuje tracker GlobalPost bez konta ani kodu pocztowego (znalezioną kartę strona przechowuje przez kilka minut w pamięci podręcznej). Działa też link `goglobalpost.com/track-detail/?t=` lub `/track/?t=`; dla starszego numeru `SDC` wybierz GlobalPost przez „Zmień”. Jeśli masz tylko numer firmy doręczającej, wklej go tak samo — Peek obsługuje ponad 3 500 przewoźników.

Peek pokazuje status, każdy skan z lokalizacją (obcinając kody pocztowe), nazwę usługi, kraj docelowy oraz firmę doręczającą wraz z jej numerem, gdy podaje je GlobalPost. Pomija pasek postępu, miasta nadawcy i odbiorcy z nagłówka (odczytywane wyłącznie do rozpoznania zwrotu) oraz kody pocztowe; GlobalPost nie podaje przewidywanej daty dostawy, wagi ani potwierdzenia doręczenia. Przycisk „Otwórz stronę przewoźnika GlobalPost” otwiera Twój numer na `goglobalpost.com`.

Godziny w GlobalPost nie mają strefy czasowej: przy przesyłkach z USA skany w centrach GlobalPost obsługiwanych przez ePost Global (`Town ST`) oraz wyjściowe skany USPS w stanach z jedną strefą Peek odczytuje według zegara danego stanu i przelicza na strefę Twojego urządzenia, natomiast rejestracja, centra prowadzone przez Asendię (`Town, ST`), paczki nadane z Wielkiej Brytanii, skany za granicą i zwroty zachowują godzinę widoczną w GlobalPost (północ punktualnie wyświetla się jako sama data bez godziny); na `peektracker.com` oraz w aplikacjach na iPhone'a i Androida historia kończy się wierszem „Godziny w Twojej strefie czasowej: …”.

Gdy GlobalPost wymienia firmę doręczającą znaną w Peek pod tą nazwą, karta pokazuje „Doręcza: …” i wypisuje jej numer po kodzie `GP` — albo „Zgłoszona przewoźnikowi …, jeszcze nieprzyjęta”, dopóki firma ta ma tylko zapowiedź bez własnych skanów. Jeśli jest drugi numer bez rozpoznanej nazwy partnera, przy poprawnym numerze pocztowym Peek odpytuje pocztę kraju docelowego ([Poczta Polska](carrier:poczta-polska) w Polsce) lub jedynego przewoźnika wynikającego z formatu numeru. Gdy pojawi się tam skan co najmniej tak świeży jak ostatni wpis GlobalPost, Peek przełącza śledzenie na tego przewoźnika, zachowując historię z GlobalPost.

Dopóki masz otwartą przesyłkę lub w dniu dostawy włączone powiadomienia, Peek sprawdza zmiany nawet co 10 minut — a od 6:00 czasu lokalnego w dniu ze statusem „W doręczeniu” nawet co 2 minuty (co 5 minut, gdy musi otworzyć tracker GlobalPost w przeglądarce), dopóki odpowiada własny tracker GlobalPost (po przekazaniu do firmy doręczającej Peek przejmuje jej tempo; przez same usługi śledzenia: co godzinę, co 3 godziny po dobie ciszy i do co 15 minut w dniu dostawy przy otwartej paczce lub włączonych powiadomieniach); w pozostałych sytuacjach oraz po dwóch dniach bez nowości (12 godzin na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika”) sprawdza co godzinę. Możesz włączyć alerty „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek nie kontaktuje się z GlobalPost ani ze sprzedawcą w Twoim imieniu.

## Najczęstsze pytania o śledzenie GlobalPost

### Dlaczego śledzenie GlobalPost urwało się po odprawie celnej?

W usłudze *GlobalPost Economy* śledzenie kończy się z chwilą dotarcia przesyłki do kraju docelowego i przejścia odprawy celnej, a GlobalPost uprzedza, że część paczek ma na miejscu ograniczone śledzenie. Przesyłka nadal jedzie i może dotrzeć bez kolejnych wpisów; jeśli w `Final Mile Tracking By:` widnieje numer lokalny, Peek odpytuje tego przewoźnika i Ty również możesz go sprawdzić.

### Kto doręcza przesyłki GlobalPost?

Poczta lub firma kurierska w kraju przeznaczenia, którą tracker GlobalPost może wymienić wraz z jej numerem przesyłki. Gdy podany jest międzynarodowy numer pocztowy bez rozpoznanej nazwy partnera, Peek odpytuje operatora pocztowego kraju docelowego (w Polsce Pocztę Polską).

### Ile trwa dostawa przez GlobalPost?

FAQ wskazuje `5-6 days` do pobliskich krajów i do `20 days` na dalszych kierunkach; sprzedawcom GlobalPost podaje orientacyjnie `10 – 14 business days` dla *Economy*, `6 – 10 business days` dla *Standard* i *Flat Rate* oraz `5 – 8 business days` dla *Plus*, licząc od opracowania paczki w centrum wysyłkowym.

### Czy GlobalPost to wiarygodna firma?

Tak. GlobalPost należy do grupy Auctane, jest oferowany na platformach wysyłkowych takich jak ShipStation i Stamps.com, wysyła paczki do ponad 220 krajów i deklaruje ponad 60 milionów doręczonych przesyłek.

### Jak skontaktować się z GlobalPost?

Napisz na `info@goglobalpost.com`, zadzwoń pod `(888) 899-1255` w dni robocze w godzinach `6am`–`6pm` czasu pacyficznego lub skorzystaj z formularza na `www.goglobalpost.com/contact-support/`, wybierając `Recipient`. Jeśli od wysyłki minęło 30 dni bez doręczenia, GlobalPost prosi o zgłoszenie się najpierw do sprzedawcy.

:::sources
- [GlobalPost: FAQs](https://www.goglobalpost.com/faqs/) – Auctane, zasada działania, lista krajów Plus – Duties & Taxes Included oraz Flat Rate, śledzenie Economy do odprawy celnej, czasy dostawy, zwroty i reklamacje w 90 dni / 3 tygodnie
- [GlobalPost: Standard](https://www.goglobalpost.com/standard/) – porównanie usług Economy, Standard, Plus i Flat Rate: śledzenie, opłaty DDU/DDP i dni robocze
- [GlobalPost: Contact Support](https://www.goglobalpost.com/contact-support/) – e-mail, telefon, godziny czasu pacyficznego, opcja Recipient w formularzu i uwaga dotycząca China Post
- [GlobalPost: Home](https://www.goglobalpost.com/) – ponad 220 krajów i przeszło 60 milionów doręczonych przesyłek
- [GlobalPost: Track your package](https://www.goglobalpost.com/tracking/) – oficjalna wyszukiwarka i komunikat o braku danych
- [GlobalPost: Terms and Conditions](https://www.goglobalpost.com/conditions/) – centra wysyłkowe, szacunkowy charakter terminów i wyłączenie paczek niedoręczalnych z roszczeń
- [Komisja Europejska: Reforma celna UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – cło 3 € od 1 lipca 2026 r. oraz opłata manipulacyjna 2 € od 1 listopada 2026 r.
- [Komisja Europejska: Wytyczne dotyczące cła 3 € i opłaty manipulacyjnej Unii (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – opłata 2 € niezależnie od wartości, definicja pozycji towarowej i zwolnienie z VAT przy IOSS
- [Komisja Europejska: Zakres terytorialny VAT i ceł](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Irlandia Północna
- [Universal Parcel Scraper: GlobalPost](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/globalpost) – formaty GP i SDC, statusy, rozpoznawanie zwrotów, strefy czasowe, pamięć podręczna i przewoźnik końcowy
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – format USPS i przekazanie do firmy doręczającej lub Poczty Polskiej
:::
