---
title: Śledzenie Nova Global: kody NPG i NGUS, cło i pomoc
description: Śledź paczkę Nova Global (Nova Poshta Global): listy NPG i NGUS, kody statusów, odprawa celna, lokalne przekazania oraz kontakt.
slug: sledzenie-nova-global
published: 2026-11-24
updated: 2026-11-24
---

Nova Global (formalnie *LLC „Nova Poshta Global”* / *LLC NP GLOBAL*, część grupy Nova Poshta) to międzynarodowy operator logistyki ekspresowej i transgranicznego e-commerce z biurami w Kijowie i New Jersey, działający w ponad 160 krajach i terytoriach oraz połączony z 506 690 punktami odbioru na świecie. Poprzez działy *Global E-Commerce*, *Global Forwarding* (wirtualne adresy dostawy w 16 krajach, w tym USA, Wielkiej Brytanii i Włoszech), *Global Sellers* oraz *Global Freight* obsługuje przesyłki międzynarodowe do i z Ukrainy, Stanów Zjednoczonych, Kanady, Unii Europejskiej i Azji. List przewozowy sprawdzisz na [stronie śledzenia Nova Global](https://nova.global/track/) albo wklejając numer w pole powyżej.

## Numery śledzenia Nova Global: formaty NPG i NGUS

Międzynarodowy numer listu przewozowego znajdziesz w e-mailu wysyłkowym ze sklepu, na koncie pośrednika (forwarding) albo w aplikacji Nova Post. Listy Nova Global mają trzy główne formaty:

| Format (zmyślony) | Co to jest |
| --- | --- |
| `NP482917364058NPG` | Standardowy list Nova Global: 2 litery (np. `NP`, `WB`, `OG` lub `YW`) + 9–16 cyfr + `NPG`. |
| `ABCDF4829173640NPG` | List zamówienia sklepowego: 5 liter + 10 cyfr + `NPG`. |
| `TE4829173640582917NGUS` | List Nova Global dla paczki z Chin: `TE` + 16 cyfr + `NGUS`. |

W kraju docelowym Nova Global może przekazać przesyłkę lokalnemu przewoźnikowi, np. Nova Poshta w Ukrainie, i wyświetlić własny numer tego przewoźnika obok listu `NPG` lub `NGUS`. Zobacz [formaty numerów przesyłek](guide:tracking-number-formats), aby rozpoznać inne formaty międzynarodowe.

## Co oznaczają statusy i kody Nova Global

Tracker Nova Global stosuje spójne kody numeryczne z opisami po angielsku lub ukraińsku, poprzedzone krajem wykonania skanu:

| Kod i opis Nova Global | Co to oznacza dla ciebie | Peek pokazuje |
| --- | --- | --- |
| `1-0` (`Order is processed for delivery to the country of destination`) | Nadawca lub konto forwardingowe utworzyło międzynarodowe zlecenie przed przyjęciem w hubie. | „Zarejestrowana” |
| `1-2` (`Processing an unregistered shipment`), `14` (`Accepted for delivery`) | Przyjęta i zeskanowana w początkowym hubie lub magazynie Nova Global. | „Przyjęta przez przewoźnika” |
| `247`, `76`, `78`, `23`–`26`, `54`, `55`, `30`, `301` | Przygotowana do lotu, przewieziona między lotniskami, przeszła przez węzły tranzytowe (np. Warszawę lub Wiedeń) i ruszyła do terminalu w mieście docelowym. | „W transporcie” |
| `20` (`Delivered to the customs terminal`), `400`, `402` (`Parcel require customs clearance`), `407` / `407-1` (`documents submitted`), `410` (`Accompanying documents received`) | Przechodzi kontrolę celną lub czeka na dokumenty celne i wyliczenie należności. | „W urzędzie celnym” |
| `401` (`Customs declaration finished`), `22` (`Left the customs terminal`) | Odprawiona celnie i zwolniona do dalszego doręczenia. | „W transporcie” |
| `37` (`On courier delivery`) | W drodze z lokalnym kurierem pod wskazany adres w kraju docelowym. | „W doręczeniu” |
| `303` (`Delivered to the locker`), `38` (`At the post office`) | Czeka na odbiór w paczkomacie (*поштомат*) lub placówce Nova Poshta. | „Do odbioru” |
| `553` (`Shipment lost`) | Zarejestrowano wyjątek dla przesyłki; natychmiast skontaktuj się z obsługą klienta i sprzedawcą. | „Zgłoszono problem” |
| `3-1` (`Delivered`) | Odebrana przez adresata lub doręczona. | „Dostarczona” |

## Dostawa transgraniczna, forwarding i cło

Na stronie [nova.global](https://nova.global/) Nova Global wyróżnia cztery działy: *Global E-Commerce* (doręczenia detaliczne w regionach NALA, EMEA i APAC), *Global Forwarding* (wirtualne adresy zakupowe w 16 krajach z przekierowaniem pod drzwi), *Global Sellers* (eksport ze sklepów i platform Etsy, Amazon, eBay, Vinted, StockX, Depop i TikTok Shop do ponad 160 krajów) oraz *Global Freight* (fracht lotniczy, drogowy i morski).

**Odprawa celna i dokumenty.** Przesyłki międzynarodowe wchodzące do Ukrainy, Unii Europejskiej, USA lub Kanady podlegają odprawie celnej (`20`, `400`, `402`). [Polityka prywatności Nova Global](https://nova.global/public-contract/) wskazuje, że operator może poprosić o skany dokumentów dotyczących przesyłki, np. potwierdzających jej wartość, oraz innych dokumentów wymaganych przez urzędy celne kraju nadania lub odbioru. Ogólne zasady (w tym unijny VAT importowy, cło 3 € do 150 € i unijną opłatę 2 € dla przesyłek spoza UE) opisuje poradnik [przesyłki zatrzymane w urzędzie celnym](guide:customs).

**Lokalne zasady doręczeń.** Gdy Nova Global przekazuje paczkę do Nova Poshta lub innego lokalnego kuriera, zasady tego partnera określają próby doręczenia, dni przechowywania i godziny otwarcia placówek.

## Paczka Nova Global na cle, milcząca lub zagubiona

- **Przerwy między krajami tranzytu:** transport lotniczy i drogowe przewozy transgraniczne (np. przez Polskę do Ukrainy) często zatrzymują aktualizacje na kilka dni między `78` (odlot z lotniska) lub `25` (wyjazd z kraju tranzytowego) a `20` (terminal celny). Zobacz [dlaczego śledzenie przestaje się aktualizować](guide:tracking-not-updating).
- **Sprawdź numer lokalnego partnera:** gdy Nova Global przypisze lokalnego przewoźnika i numer po odprawie celnej, sprawdzaj go bezpośrednio u tego partnera pod kątem godzin doręczenia czy punktu odbioru.
- **Zagubiona (`553`), uszkodzona lub doręczona bez odbioru:** najpierw skontaktuj się ze sklepem (lub serwisem forwardingowym) w sprawie reklamacji, a potem napisz do wsparcia Nova Global z listem `NPG`/`NGUS` i fakturą handlową. Zobacz [dostarczona, ale nieodebrana](guide:delivered-not-received), [uszkodzona paczka](guide:damaged-parcel), [zagubiona paczka](guide:lost-parcel) i [zwrócona nadawcy](guide:returned-to-sender).

## Obsługa klienta i kontakty Nova Global

Oficjalne dane opublikowane na [nova.global/contact/](https://www.nova.global/contact/), [nova.global/track/](https://nova.global/track/) i [nova.global/public-contract/](https://nova.global/public-contract/):

- **E-mail:** `info@nova.global`.
- **Opcje pomocy na stronie śledzenia:** widżety *Call us*, *Send e-mail* i *Chat with us* na [nova.global/track/](https://nova.global/track/), plus śledzenie i wsparcie w aplikacji mobilnej **Nova Post**.
- **Spółka:** LLC „Nova Poshta Global” (*LLC NP GLOBAL*, numer identyfikacyjny `38130410`, NIP `381304126502`), 103 Stolychne Hwy, Kijów, 03026, Ukraina. Nova Global ma również biuro w New Jersey.

> Ignoruj niechciane SMS-y i wiadomości z linkiem do pilnej płatności kartą za „odprawę” paczki Nova Global. Wszelkie prawdziwe wezwania celne sprawdzaj w oficjalnej aplikacji Nova Post albo przez [nova.global/track/](https://nova.global/track/) i `info@nova.global`. Zobacz [fałszywe SMS-y o przesyłce](guide:delivery-scam-texts).

## Śledzenie paczki Nova Global w Peek

[Peek](/) automatycznie rozpoznaje listy `NPG` (`NP482917364058NPG` lub `ABCDF4829173640NPG`, cyfry zmyślone) oraz `NGUS` (`TE4829173640582917NGUS`) jako Nova Global z wysoką pewnością, a także wklejone linki `nova.global/track/?Tracking_ID=` lub `novaposhtaglobal.ua/track/?Tracking_ID=`. Jeśli trzeba wybrać Nova Global ręcznie, stuknij „Zmień” podczas dodawania lub „Zmień przewoźnika” na ekranie paczki (wyszukanie „Nova Poshta Global”, „NP Global” lub „Нова Пошта Глобал” też go znajdzie).

Peek odczytuje bezpośrednio tracker Nova Global po angielsku i wyświetla etap („Zarejestrowana”, „Przyjęta przez przewoźnika”, „W transporcie”, „W urzędzie celnym”, „W doręczeniu”, „Do odbioru”, „Zgłoszono problem”, „Dostarczona”), treść każdego skanu wraz z krajem, kraj docelowy oraz godzinę doręczenia, gdy Nova Global ją podaje. Kody Nova Global takie jak `402` czy `553` wyznaczają etap; Peek ich nie pokazuje. Nova Global oznacza skany w Ukrainie czasem kijowskim, a zagraniczne strefą czasową miejsca zdarzenia; Peek wyświetla je na zegarze twojego urządzenia. Gdy Nova Global poda lokalnego przewoźnika z jego numerem, a Peek go rozpozna, sprawdza, czy ma tę paczkę: na ekranie pojawia się wtedy „Doręcza:” z nazwą przewoźnika oraz oba numery. Peek pomija kraj pochodzenia i pozostałe numery paczki. Tracker Nova Global nie podaje przewidywanej daty dostawy, wagi, nazwy usługi, adresu punktu odbioru ani zdjęcia doręczenia.

Gdy masz paczkę otwartą w Peek albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza status nawet co 10 minut; w dniu, gdy Nova Global pokaże `37` (`On courier delivery`), co 2 minuty od 6:00 według zegara miejsca najnowszego skanu, dopóki tracker Nova Global odpowiada. Poza tym sprawdza co godzinę. Po 12 godzinach wyłącznie na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika”, albo po dwóch dniach bez zmian, sprawdza co godzinę niezależnie od sposobu śledzenia, a po siedmiu dniach na etapie „Zarejestrowana” — co 6 godzin. Gdy tracker Nova Global nie odpowiada i Peek korzysta wyłącznie z zewnętrznych usług śledzenia, sprawdza co godzinę, co 3 godziny po dniu ciszy i do co 15 minut w dniu doręczenia, gdy masz paczkę otwartą lub masz włączone powiadomienia.

Gdy najnowszy skan ma cztery dni, a przesyłka nadal widnieje jako „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie”, Peek oznacza ją dniem tego skanu, np. „Brak aktualizacji od pon., 12 paź”. Na stronie paczki pojawia się wtedy napis: „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika Nova Global.”, wraz z linkiem „Poproś przewoźnika Nova Global o pomoc” do strony kontaktowej Nova Global. Po trzech tygodniach bez nowego skanu widnieje komunikat: „Od ponad trzech tygodni nic nowego. Jeśli przesyłka wciąż nie dotarła, zapytaj sprzedawcę. Peek dalej pyta przewoźnika Nova Global.”

Możesz wybrać powiadomienia: „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek tylko monitoruje aktualizacje śledzenia — nie prześle za ciebie dokumentów celnych ani nie skontaktuje się z Nova Global.

## Pytania o śledzenie Nova Global

### Co oznaczają `NPG` i `NGUS` na końcu numeru śledzenia?

`NPG` oznacza międzynarodowy list przewozowy Nova Global (*Nova Poshta Global*, np. `NP482917364058NPG` lub `ABCDF4829173640NPG`, cyfry zmyślone), natomiast `TE...NGUS` (`TE4829173640582917NGUS`) oznacza list Nova Global dla paczki wysłanej z Chin.

### Co oznacza `Parcel require customs clearance` (`402`)?

Kod `402` oznacza, że paczka wymaga odprawy celnej. Nova Global może poprosić cię o skany dokumentów dotyczących przesyłki, np. potwierdzających jej wartość, gdy wymaga tego urząd celny; po zakończeniu pojawia się `401` (`Customs declaration finished`).

### Gdzie odebrać paczkę Nova Global w Ukrainie?

Gdy śledzenie pokaże `303` (`Delivered to the locker`) lub `38` (`At the post office`), paczka czeka na odbiór w wybranym paczkomacie (*поштомат*) lub placówce Nova Poshta.

### Jak skontaktować się z obsługą klienta Nova Global?

Napisz na `info@nova.global`, wejdź na [nova.global/contact/](https://www.nova.global/contact/) albo skorzystaj z opcji telefonu, e-maila i czatu na [nova.global/track/](https://nova.global/track/).

:::sources
- [Nova Global: Oficjalna strona](https://nova.global/) – ponad 160 krajów, biura w Kijowie i New Jersey, Global E-Commerce, Global Forwarding (16 krajów z wirtualnymi adresami), Global Sellers i Global Freight
- [Nova Global: Śledzenie przesyłek](https://nova.global/track/) – publiczny portal śledzenia, 506 690 punktów odbioru i kanały obsługi
- [Nova Global: Kontakt](https://www.nova.global/contact/) – `info@nova.global`, LLC NP GLOBAL (NIP `381304126502`, 103 Stolychne Hwy, Kijów) (adres pomocy linkowany przez Peek)
- [Nova Global: Umowa publiczna i prywatność](https://nova.global/public-contract/) – *LLC „Nova Poshta Global”* (ID `38130410`), adres pocztowy w Kijowie oraz informacje celne i o danych
- [Universal Parcel Scraper: nova-global](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/nova-global) – wykrywanie `NPG`/`NGUS`, reguły linków `nova.global`/`novaposhtaglobal.ua`, kody statusów (`1-0`–`553`), czas kijowski w Ukrainie i lokalny za granicą, odczyt lokalnego partnera i adres pomocy
:::
