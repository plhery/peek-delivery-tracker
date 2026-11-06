---
title: Śledzenie Etsy: gdzie jest zamówienie i kto je dostarcza
description: Dlaczego zamówienie z Etsy wciąż ma status „Niewysłane”, który przewoźnik je dostarcza, jak wygląda numer przesyłki i kiedy złożyć reklamację.
slug: sledzenie-zamowienia-etsy
picture: Paczka obwiązana sznurkiem i oklejona znaczkami, pocztowy samochód dostawczy i pędzący Pip.
published: 2026-10-17
updated: 2026-10-17
---

Etsy niczego samo nie wysyła: każdy sklep sam wytwarza lub pakuje swoje zamówienia, nadaje je u wybranego przewoźnika i dodaje numer do śledzenia, jeśli usługa go ma. Strona Zakupy pokazuje, czy zamówienie opuściło sklep i kiedy powinno dotrzeć; numer przesyłki pokazuje, gdzie jest teraz. Spóźnia się albo zaginęło? Zapytaj najpierw sprzedawcę, potem Etsy.

## Dlaczego moje zamówienie z Etsy wciąż ma status „Niewysłane”?

Bo sprzedawca jeszcze go nie wykonał, nie spakował albo nie oznaczył jako wysłane. Etsy liczy szacowaną datę dostawy tak: „Czas przetwarzania + czas wysyłki u danego przewoźnika = szacowana data dostawy”. Czas przetwarzania to czas, którego sprzedawca potrzebuje, żeby wytworzyć produkt i przygotować go do wysyłki. Przykład Etsy: 3–5 dni przetwarzania i 2–4 dni wysyłki dają dostawę za 5–9 dni. Na potwierdzeniu zakupu widzisz też datę wysyłki, do której zobowiązał się sprzedawca.

W **Zakupy i oceny** (aplikacja: **Ty** → **Zakupy**) każde zamówienie ma jeden z tych statusów:

| Status | Co oznacza |
| --- | --- |
| `Niewysłane` | Sprzedawca jeszcze nie ukończył zamówienia albo nie zaktualizował go na Etsy |
| `Wysłane` | Sprzedawca ukończył zamówienie na Etsy |
| `Przygotowane do nadania` | Jest numer do śledzenia, ale przewoźnik jeszcze nie zeskanował paczki |
| `W drodze` | Przewoźnik ma paczkę, jest w drodze do ciebie |
| `Dostarczone` | Dotarła i możesz wystawić ocenę |

`Wysłane` nie zawsze znaczy, że paczka jest już u przewoźnika. Sprzedawca może ustawić datę wysyłki najpóźniej za 3 dni, a Etsy powiadamia cię tego dnia. Tylko przy etykietach Asendia, Evri, FedEx, USPS (bez listów i przesyłek płaskich) lub UPS kupionych na Etsy e-mail czeka na pierwszy skan przewoźnika. Jeśli szacowana data minęła, a zamówienie dalej jest `Niewysłane`, według Etsy sprzedawca mógł zapomnieć oznaczyć je jako ukończone albo jeszcze go nie wysłał: zapytaj przez **Pomoc w zakresie zamówienia**.

Gdzie znaleźć numer na Etsy i w e-mailu o wysyłce: [jak znaleźć numer przesyłki](guide:find-tracking-number).

## Który przewoźnik dostarcza zamówienie z Etsy?

Ten, którego wybrał sprzedawca. Etsy sprzedaje etykiety wysyłkowe tylko sprzedawcom w Stanach Zjednoczonych, Kanadzie i Wielkiej Brytanii; pozostali sami kupują opłatę na poczcie lub u kuriera. Przy wysyłce za granicę Etsy wymienia przewoźników często wybieranych w każdym kraju:

| Sprzedawca w | Etykiety sprzedawane na Etsy | Przewoźnicy wymieniani przez Etsy przy wysyłce za granicę |
| --- | --- | --- |
| Stany Zjednoczone | USPS, [FedEx](carrier:fedex), [UPS](carrier:ups) (tylko w USA), Global Postal Shipping | [DHL Express](carrier:dhl-express), FedEx, UPS, USPS |
| Wielka Brytania | Royal Mail, Evri | DHL, Evri, Royal Mail, UPS |
| Kanada | Canada Post, Chit Chats | Canada Post, Chit Chats, DHL, FedEx, UPS |
| Polska | Brak | [Poczta Polska](carrier:poczta-polska) |
| Chiny, Hongkong | Brak | [Yanwen](carrier:yanwen), [YunExpress](carrier:yunexpress), [4PX](carrier:4px), a z Chin także SF Express |

Jeśli sprzedawca skorzystał z przewoźnika, który współpracuje z Etsy, obok zamówienia jest przycisk **Śledź paczkę**. W innym razie numer znajdziesz tylko pod **Wyświetl potwierdzenie** i śledzisz go na stronie przewoźnika.

Od 1 lipca 2026 r. Etsy tymczasowo nie sprzedaje etykiet USPS z wysyłką do Polski, jak podaje, „ze względu na nowe opłaty manipulacyjne w UE wpływające na niektórych dostawców”. Sprzedawcy z USA korzystają wtedy z Global Postal Shipping albo kupują etykietę gdzie indziej. Paczki z Chin mają swoją drogę: zobacz [śledzenie paczki z Chin](guide:tracking-from-china).

## Jak wygląda numer przesyłki z Etsy?

To numer przewoźnika, nie Etsy. Poczty używają międzynarodowego formatu pocztowego: 13 znaków, które nie zmieniają się na granicy.

:::anatomy RR 00000000 5 US
- RR | Dwie litery: rodzaj przesyłki i usługa
- 00000000 | Numer seryjny, osiem cyfr
- 5 | Cyfra kontrolna, wyliczona z numeru seryjnego
- US | Kraj poczty, która go nadała: USA
:::

Formy, które Peek zna dla przewoźników z tabeli, z wymyślonymi cyframi:

| Numer wygląda tak | Zwykle |
| --- | --- |
| Dwie litery, dziewięć cyfr, potem `PL`, `US` lub `GB` | Poczta Polska, USPS lub Royal Mail |
| `PX` + 10 cyfr | Poczta Polska |
| 20 cyfr zaczynających się od `00` | Poczta Polska |
| 22 cyfry zaczynające się od `91` do `95`, np. `9400111111111111111114` | USPS |
| `1Z` + 16 liter i cyfr | UPS |
| 12 lub 15 cyfr | FedEx |

Nie wiesz, który masz? Wpisz go tutaj:

:::checker
:::

Wszystkie inne formaty, przewoźnik po przewoźniku: [formaty numerów przesyłek](guide:tracking-number-formats).

## Dlaczego śledzenie Etsy się zatrzymuje albo zmienia?

Zwykle z jednego z tych powodów:

- **Paczka przekroczyła granicę.** Etsy uprzedza, że „w przypadku niektórych przesyłek międzynarodowych dane śledzenia przestają być dostępne, gdy przesyłka opuści kraj nadawcy”. Status powinien się zaktualizować, gdy paczka dotrze do Polski.
- **Przejęła ją Poczta Polska.** Przy numerze pocztowym poczta sprzedawcy wysyła paczkę za granicę, a Poczta Polska ją doręcza. Światowy Związek Pocztowy dopuszcza „tylko jeden identyfikator S10” na przesyłkę, więc ten sam numer zwykle działa też na stronie Poczty Polskiej.
- **Dwie etykiety, jedna droga.** Przy Global Postal Shipping od Etsy sprzedawca z USA wysyła paczkę z etykietą USPS do Asendii, która nakleja etykietę międzynarodową. Etsy łączy oba etapy w jedną historię w Zakupy i oceny, a w e-mailach oznacza ją jako „Tracked on Etsy”.
- **Sprzedawca dodał lub poprawił numer później.** Sprzedawca może zmienić numer przez 3 dni od ukończenia zamówienia; potem wysyła ci właściwy w wiadomości. Może też później dodać paczkę, a jej numer pojawi się obok pierwszego.
- **Kilka zamówień, jedno pudełko.** Jeśli sprzedawca spakuje razem kilka twoich zamówień, wszystkie mają ten sam numer.

:::journey
- shop | Twórca | Wykonuje i pakuje: do tego czasu `Niewysłane`
- label | Etykieta | `Przygotowane do nadania`: numer jest, skanu brak
- truck | Poczta nadawcy | Pierwsze skany w kraju sprzedawcy
- plane | Lot | Tu śledzenie często milknie
- customs | Cło | Kontrola, czasem z opłatami
- handover | Poczta Polska | Doręcza, zwykle pod tym samym numerem
- home | Ty | `Dostarczone`
:::

Jak długo może trwać cisza: [dlaczego śledzenie się nie aktualizuje](guide:tracking-not-updating).

### Dlaczego w ogóle nie ma numeru przesyłki?

Nie każda usługa go ma. Etsy pisze wprost: „Nie wszystkie metody wysyłki obejmują informacje o śledzeniu”. Przy listach i przesyłkach płaskich z USA etykiety Etsy dodają śledzenie przez Pitney Bowes, ale zbyt sztywna koperta może go nie mieć. Etsy wymaga śledzenia od sprzedawców z USA, ale każdy sprzedawca może oznaczyć zamówienie jako bez śledzenia i podać powód. Brak numeru? Napisz do sprzedawcy przez **Pomoc w zakresie zamówienia**: wie, jakiej usługi użył i kiedy nadał paczkę.

## Czy zapłacę cło za zamówienie z Etsy z zagranicy?

Etsy pisze, że cło, podatki i opłaty za odprawę zwykle płaci kupujący, najczęściej przy odbiorze paczki. W Polsce obowiązują zasady UE, a część z nich Etsy pobiera już przy kasie:

| Zamówienie | Co obowiązuje |
| --- | --- |
| Z kraju UE | VAT jest wliczony w cenę, nie dopłacasz nic |
| Spoza UE, poniżej 150 EUR | Etsy pobiera VAT przy kasie. Dochodzi opłata celna 3 EUR od produktu, przy kasie albo przy dostawie |
| Spoza UE, powyżej 150 EUR | VAT i cło mogą zostać naliczone przy dostawie, jeśli sprzedawca nie opłacił cła z góry |

Jeśli paczka spóźni się albo nie dotrze, bo odmówiłeś lub nie zapłaciłeś opłat przy dostawie, program ochrony procesu zakupu na Etsy tego nie obejmuje. Więcej o opłatach i czasie oczekiwania: [paczka zatrzymana w urzędzie celnym](guide:customs).

## Co zrobić, gdy zamówienie z Etsy się spóźnia albo nie dociera?

W tej kolejności:

:::steps
- Sprawdź daty | Szacowana data dostawy jest przy zamówieniu. Etsy przelicza ją, jeśli sprzedawca wyśle wcześniej.
- Śledź u przewoźnika | Przez Śledź paczkę albo z numerem spod Wyświetl potwierdzenie na stronie przewoźnika.
- Zapytaj sprzedawcę | Zakupy i oceny, potem Pomoc w zakresie zamówienia. Sprzedawca może sprawdzić status, zmienić adres albo zwrócić pieniądze.
- Złóż reklamację | 48 godzin po twojej prośbie, gdy szacowana data minie. Gdy zamówienie nie dotarło: po 7 dniach od najpóźniejszej szacowanej daty.
- Pilnuj terminu | Masz czas do 30 dni po szacowanej dacie dostawy.
:::

W programie ochrony procesu zakupu na Etsy kwalifikujące się zamówienie dostaje pełny zwrot, jeśli produkt nie dotrze, dotrze uszkodzony, będzie niezgodny z opisem albo dotrze co najmniej 7 dni po najpóźniejszej szacowanej dacie dostawy podanej przy zakupie. Twoja prośba w **Pomoc w zakresie zamówienia** pokazuje, od kiedy i do kiedy możesz złożyć reklamację. Etsy zwraca pieniądze tylko w ciągu 180 dni od zakupu.

Niektóre sytuacje są wyłączone: opóźnienia przez strajk przewoźnika, ekstremalną pogodę, wojnę lub niepokoje społeczne, opłaty odrzucone przy drzwiach i zamówienia, które już zakwestionowałeś w banku. Kupowałeś jako gość? Najpierw powiąż zamówienie z kontem Etsy. Jeśli płatność przeszła przez PayPal poza Etsy Payments, otwórz zamiast tego spór w PayPal w ciągu 180 dni od zakupu.

### Status „Dostarczone”, ale paczki nie ma

Etsy radzi najpierw: zapytaj domowników lub współpracowników, poczekaj dzień, jeśli ci się nie spieszy, skontaktuj się z lokalną pocztą lub centrum wysyłkowym i zapytaj sąsiadów. Dalej nic? Wyślij sprzedawcy prośbę przez **Pomoc w zakresie zamówienia**: zwykle to on składa reklamację u przewoźnika w twoim imieniu. Jeśli paczka się nie znajdzie, złóż reklamację w Etsy. Więcej w [dostarczono, ale nie odebrano](guide:delivered-not-received) i [zaginiona paczka](guide:lost-parcel).

## Czy mogę śledzić zamówienie z Etsy poza Etsy?

Tak, numerem przewoźnika. Wklej go do [Peek](/): rozpoznaje numery poczt i przewoźników z tabeli. Przy przesyłce pocztowej wysłanej za granicę, gdy poczta nadawcy podaje kraj docelowy, ale nie wskazuje partnera, Peek może zaproponować sprawdzenie tego samego numeru w Poczcie Polskiej. Zachowuje historię poczty nadawcy i przechodzi na Pocztę Polską, gdy jej śledzenie pokaże nowe postępy. Kroki Etsy zostają na Etsy: Peek nie widzi zamówienia `Niewysłane` i nie złoży za ciebie reklamacji.

:::sources
- [Etsy: Jaki status ma moje zamówienie?](https://help.etsy.com/hc/pl/articles/115015521948-Jaki-status-ma-moje-zam%C3%B3wienie) – statusy, Śledź paczkę, Wyświetl potwierdzenie, śledzenie za granicą, brak śledzenia, rady, gdy paczka nie dotarła
- [Etsy: Co to jest szacowana data dostawy?](https://help.etsy.com/hc/pl/articles/360020601674-Co-to-jest-szacowana-data-dostawy) – przetwarzanie plus wysyłka, przykład, 7 dni przy niedostarczeniu
- [Etsy: Ustawianie czasów i wzorców przetwarzania oraz dat wysyłki](https://help.etsy.com/hc/pl/articles/115015588087-Ustawianie-czas%C3%B3w-i-wzorc%C3%B3w-przetwarzania-oraz-dat-wysy%C5%82ki) – czas przetwarzania, data wysyłki na potwierdzeniu
- [Etsy: Jak dodać do zamówienia dane śledzenia i oznaczyć je jako zrealizowane](https://help.etsy.com/hc/pl/articles/115015774228-Jak-doda%C4%87-do-zam%C3%B3wienia-dane-%C5%9Bledzenia-i-oznaczy%C4%87-je-jako-zrealizowane) – data wysyłki do 3 dni naprzód, e-mail po pierwszym skanie, śledzenie obowiązkowe w USA, 3 dni na zmianę, dodane paczki, połączone zamówienia
- [Etsy: Jak można kupić etykiety wysyłkowe na Etsy](https://help.etsy.com/hc/pl/articles/360001967188-Jak-mo%C5%BCna-kupi%C4%87-etykiety-wysy%C5%82kowe-na-Etsy) – przewoźnicy, sprzedawcy w Kanadzie, Wielkiej Brytanii i USA, wstrzymane kierunki w UE
- [Etsy: FedEx Shipping Labels on Etsy](https://help.etsy.com/hc/en-us/articles/360000337447-FedEx-Shipping-Labels-on-Etsy) – sprzedawcy w USA
- [Etsy: UPS Shipping Labels on Etsy](https://help.etsy.com/hc/en-us/articles/18309523715735-UPS-Shipping-Labels-on-Etsy) – sprzedawcy w USA, tylko wysyłka krajowa
- [Etsy: Royal Mail Postage Labels on Etsy](https://help.etsy.com/hc/en-gb/articles/30907634892311-Royal-Mail-Postage-Labels-on-Etsy) – sprzedawcy w Wielkiej Brytanii
- [Etsy: Evri Postage Labels on Etsy](https://help.etsy.com/hc/en-gb/articles/360002035507-Evri-Postage-Labels-on-Etsy) – sprzedawcy w Wielkiej Brytanii
- [Etsy: Chit Chats Shipping Labels on Etsy](https://help.etsy.com/hc/en-us/articles/44210045773719-Chit-Chats-Shipping-Labels-on-Etsy) – sprzedawcy w Kanadzie
- [Etsy: Global Postal Shipping Labels on Etsy](https://help.etsy.com/hc/en-us/articles/360037382894-Global-Postal-Shipping-Labels-on-Etsy) – Asendia, dwie etykiety, Tracked on Etsy
- [Etsy: How to Ship First-Class Flats and Letters](https://help.etsy.com/hc/en-us/articles/360039466434-How-to-Ship-First-Class-Flats-and-Letters-with-Etsy-Shipping-Labels) – śledzenie przez Pitney Bowes, sztywne koperty
- [Etsy: Informacje dotyczące zarządzania wysyłką międzynarodową](https://help.etsy.com/hc/pl/articles/360001987487-Informacje-dotycz%C4%85ce-zarz%C4%85dzania-wysy%C5%82k%C4%85-mi%C4%99dzynarodow%C4%85) – przewoźnicy często wybierani w każdym kraju
- [Etsy: Czy muszę zapłacić podatek lub cło od swojego zamówienia?](https://help.etsy.com/hc/pl/articles/115015691007-Czy-musz%C4%99-zap%C5%82aci%C4%87-podatek-lub-c%C5%82o-od-swojego-zam%C3%B3wienia) – zasady UE, 150 EUR, 3 EUR od produktu
- [Etsy: Program ochrony procesu zakupu na Etsy](https://help.etsy.com/hc/pl/articles/7471925990807-Program-ochrony-procesu-zakupu-na-Etsy) – co jest objęte, opłaty celne, strajki i pogoda, goście
- [Etsy: Jak można uzyskać pomoc w zakresie zamówienia](https://help.etsy.com/hc/pl/articles/4402660818583-Jak-mo%C5%BCna-uzyska%C4%87-pomoc-w-zakresie-zam%C3%B3wienia) – Pomoc w zakresie zamówienia, co może zrobić sprzedawca
- [Etsy: Jak złożyć reklamację](https://help.etsy.com/hc/pl/articles/5745586898199-Jak-z%C5%82o%C5%BCy%C4%87-reklamacj%C4%99) – 48 godzin, 30 dni, 180 dni, PayPal, obciążenia zwrotne
- [Etsy: What if My Buyer's Package Gets Lost?](https://help.etsy.com/hc/en-us/articles/360050349953-What-if-My-Buyer-s-Package-Gets-Lost) – reklamacja składana przez sprzedawcę
- [UPU: S10 standard](https://www.upu.int/UPU/media/upu/files/postalSolutions/programmesAndServices/standards/S10-12.pdf) – format 13 znaków, jeden numer na przesyłkę
- [Universal Parcel Scraper: carrier data](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers) – formy numerów, co sprawdza Peek
:::
