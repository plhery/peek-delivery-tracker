---
title: Śledzenie AnserX: kody BTYCN...YQ, przekazanie i pomoc
description: Śledź przesyłkę transgraniczną AnserX (斑头雁) z Chin: numery BTYCN...YQ, kody BTY, przekazanie lokalnemu kurierowi, cło i wsparcie w Shenzhen.
slug: sledzenie-anserx
published: 2026-11-24
updated: 2026-11-24
---

AnserX (*Shenzhen AnserX International Logistics Technology Co., Ltd.*, po chińsku *斑头雁国际物流*), założona w 2018 roku z siedzibą w Shenzhen, to chińska firma logistyki transgranicznej e-commerce obsługująca dedykowane linie małych paczek z Chin do USA, Europy (w tym Wielkiej Brytanii, Francji i Niemiec), Japonii, Kanady, Australii, Azji Południowo-Wschodniej, Meksyku, Nigerii i Arabii Saudyjskiej, a także fracht lotniczy/morski FBA (0–30 kg, maks. bok 1,2 m) i magazyny zagraniczne. Przesyłkę sprawdzisz w [portalu śledzenia OMS AnserX](https://oms.anserx.com/tracking) albo wklejając numer listu przewozowego w pole powyżej.

## Numery śledzenia AnserX: format BTYCN...YQ

Znajdź numer śledzenia w szczegółach zamówienia na platformie lub w e-mailu z potwierdzeniem wysyłki. Listy przewozowe AnserX mają 17 znaków: `BTYCN`, 10 cyfr i końcówkę `YQ`:

| Format (wymyślony) | Co to jest |
| --- | --- |
| `BTYCN4829173640YQ` | Standardowy transgraniczny numer przesyłki AnserX: `BTYCN` + 10 cyfr + `YQ`. |

Gdy przesyłka dotrze do kraju docelowego i przejdzie odprawę celną, AnserX przekazuje ją lokalnemu operatorowi pocztowemu lub kurierskiemu (np. [USPS](carrier:usps) w USA lub lokalnemu partnerowi ostatniej mili w Europie albo Meksyku) i dołącza lokalny numer śledzenia tego partnera, nadal przekazując jego skany pod Twoim kodem `BTYCN...YQ`. Inne chińskie prefiksy przesyłek międzynarodowych opisuje poradnik [formaty numerów śledzenia](guide:tracking-number-formats).

## Co oznaczają statusy i kody śledzenia AnserX

AnserX łączy kody etapów `BTY_` z opisami po angielsku i chińsku od sortowania eksportowego i transportu lotniczego po odprawę celną i doręczenie w kraju docelowym:

| Kod i opis AnserX | Co oznacza dla Ciebie | Peek pokazuje |
| --- | --- | --- |
| `BTY_ORDER_CREATED` (`Shipment information received`) | Sprzedawca przesłał elektroniczne dane zamówienia do AnserX, zanim fizyczna paczka dotarła do centrum. | „Zarejestrowana” |
| `BTY_WAREHOUSE_INBOUND` (`Shipment inbound in sorting center`) | Przyjęta i zeskanowana w sortowni AnserX w Chinach. | „Przyjęta przez przewoźnika” |
| `BTY_WAREHOUSE_OUTBOUND`, `BTY_LINE_HANDOVER` (`Hand over to airline` / `Departed from origin port`), `BTY_LINE_ARRIVED` (`Arrived at destination port`) | Opuściła sortownię w Chinach, została przekazana linii lotniczej i wylądowała na lotnisku docelowym. | „W transporcie” |
| `BTY_EXCC_COMPLETED` (`Relased from export customs` / `目的地进口清关完成`) | Odprawa celna zakończona (uwaga: angielski opis AnserX mówi o „export”, natomiast tekst chiński potwierdza zakończenie odprawy importowej w kraju docelowym); paczka przechodzi do transportu lokalnego. | „W transporcie” |
| `BTY_LASTMILE_TRANSIT` (`Delivery to local courier company` / `arrival at transportation partner facilities`) | Przekazana lokalnemu kurierowi w kraju docelowym i w drodze do lokalnego oddziału doręczającego. | „W transporcie” |
| `BTY_LASTMILE_DELIVERING` (`Parcel currently being delivered` / `Out for Delivery`) | Dziś w drodze z lokalnym kurierem. | „W doręczeniu” |
| `BTY_LASTMILE_DELIVERED` (`Parcel has been delivered` / `Delivered`) | Doręczona odbiorcy przez partnera w kraju docelowym. | „Dostarczona” |

## Jak działa transgraniczna dostawa AnserX

Na stronach [anserx.com](https://www.anserx.com/) i [O AnserX](https://www.anserx.com/about.html) AnserX opisuje swoją inteligentną platformę pojemności i trasowania łączącą odbiór krajowy w Chinach, huby eksportowe, transport lotniczy, odprawę celną oraz lokalnych przewoźników ostatniej mili (w tym dedykowane kanały małych paczek obsługujące elektronikę z bateriami).

**Doręczenie lokalne i cło.** AnserX jest konsolidatorem transportu międzynarodowego, a nie siecią kurierów doręczających pod drzwi poza Chinami. Po zakończeniu odprawy celnej w kraju docelowym (`BTY_EXCC_COMPLETED`) lokalna poczta lub firma kurierska decyduje o liczbie prób doręczenia, bezpiecznym pozostawieniu, punktach odbioru i zwrotach. VAT importowy, cło i ewentualne opłaty manipulacyjne wynikają z lokalnych przepisów celnych, o ile sprzedawca nie pobrał ich z góry; zobacz [przesyłki zatrzymane na cle](guide:customs).

## Przesyłka AnserX utknęła, nie aktualizuje się lub nie dotarła

Klientami AnserX są wysyłający sprzedawcy, dlatego przy opóźnieniach, błędnym adresie, zwrotach pieniędzy lub reklamacjach zagubienia zawsze skontaktuj się najpierw ze sprzedawcą.

- **Cisza podczas lotu lub odprawy celnej:** śledzenie często zatrzymuje się na kilka dni między `BTY_LINE_HANDOVER` (wylot z portu nadania), `BTY_LINE_ARRIVED` a `BTY_EXCC_COMPLETED`, gdy lądują samoloty, a urząd celny odprawia zbiorcze partie. Zobacz [dlaczego śledzenie przestaje się aktualizować](guide:tracking-not-updating).
- **Sprawdź lokalny numer po przekazaniu:** gdy AnserX dołączy numer śledzenia lokalnego przewoźnika, sprawdzenie go na stronie tego przewoźnika często daje dokładniejsze informacje o oknie dostawy lub odbiorze.
- **Dostarczona, uszkodzona lub zaginiona:** sprawdź najpierw u domowników, sąsiadów, w recepcji budynku i u lokalnego kuriera, a potem od razu skontaktuj się ze sprzedawcą. Zobacz [doręczona, ale nieotrzymana](guide:delivered-not-received), [uszkodzona przesyłka](guide:damaged-parcel), [zaginiona przesyłka](guide:lost-parcel) i [zwrócona nadawcy](guide:returned-to-sender).

## Obsługa klienta i kontakt AnserX

Oficjalne dane kontaktowe ze stron [Kontakt AnserX](https://www.anserx.com/contact-us.html) i [anserx.com](https://www.anserx.com/):

- **Całodobowe infolinie (Chiny):** `0755-2891 8474` oraz `0755-2868-5256` (obsługa online `09:00–24:00` czasu chińskiego).
- **E-mail obsługi klienta:** `bantouyankf@anserx.com`.
- **Siedziba główna:** Shenzhen AnserX International Logistics Technology Co., Ltd., Room 804, Block C, Building 3, Tian'an Cloud Park, Xuegang North Road, Bantian Street, Longgang District, Shenzhen, Chiny.

> Uważaj na niezamówione SMS-y lub wiadomości WhatsApp żądające płatności kartą online za „odprawę celną” lub „poprawienie adresu” paczki z Chin. Każde prawdziwe opłaty importowe sprawdzaj najpierw na stronie zamówienia lub na oficjalnej stronie lokalnej poczty. Zobacz [fałszywe SMS-y o przesyłkach](guide:delivery-scam-texts).

## Śledzenie przesyłki AnserX w Peek

[Peek](/) automatycznie i z wysoką pewnością rozpoznaje `BTYCN` + 10 cyfr + `YQ` (`BTYCN4829173640YQ`, cyfry wymyślone) jako AnserX, a także wklejone linki `oms.anserx.com/tracking?nos=`. Jeśli kiedykolwiek musisz wybrać AnserX ręcznie, stuknij „Zmień” podczas dodawania lub „Zmień przewoźnika” na ekranie przesyłki (wyszukanie „斑头雁” albo „AnserX International Logistics” także go znajduje).

Peek odczytuje bezpośrednio śledzenie OMS AnserX i pokazuje etap („Zarejestrowana”, „Przyjęta przez przewoźnika”, „W transporcie”, „W doręczeniu”, „Dostarczona”), angielski opis skanu (chiński, gdy brak angielskiego), miasto lub hub bez kodu pocztowego oraz kraj docelowy. Kody `BTY_` określają etap; żadna aplikacja Peek ich nie wyświetla. AnserX zapisuje własne skany według czasu pekińskiego; przekazywane skany lokalnego przewoźnika mają strefę czasową swojego miejsca (czas Los Angeles dla skanu w Los Angeles, miasta Meksyk dla Puebli), a Peek pokazuje wszystkie według zegara Twojego urządzenia. Gdy AnserX podaje numer przewoźnika doręczającego i Peek rozpoznaje tego przewoźnika, sprawdza, czy ma on przesyłkę: paczka wyświetla wtedy „Doręcza:” wraz z nazwą przewoźnika i pokazuje oba numery. Z kodu kreskowego USPS usuwany jest prefiks kodu pocztowego. Peek pomija kod konta klienta i numer referencyjny zamówienia; śledzenie AnserX nie podaje przewidywanej daty doręczenia, wagi, nazwy usługi, osobnej godziny doręczenia ani zdjęcia potwierdzenia dostawy.

Dopóki masz przesyłkę otwartą w Peek albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza status do co 10 minut; w dniu, gdy AnserX pokazuje `BTY_LASTMILE_DELIVERING`, co 2 minuty od 6:00 według zegara miejsca ostatniego skanu, dopóki odpowiada śledzenie AnserX. W pozostałych sytuacjach sprawdza co godzinę. Po 12 godzinach z samym statusem „Zarejestrowana” lub „Przyjęta przez przewoźnika” albo po dwóch dniach bez wiadomości przechodzi na sprawdzanie co godzinę niezależnie od sposobu śledzenia, a po siedmiu dniach na „Zarejestrowana” co 6 godzin. Gdy śledzenie AnserX nie odpowiada i Peek korzysta wyłącznie z zewnętrznych usług śledzenia, sprawdza co godzinę, po spokojnym dniu co 3 godziny i do co 15 minut w dniu, w którym paczka jest w doręczeniu, dopóki masz przesyłkę otwartą lub włączone powiadomienia.

Kiedy najnowszy skan ma cztery dni, a przesyłka nadal stoi na „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie”, Peek oznacza ją dniem tego skanu, np. „Brak aktualizacji od pon 12 paź”. Na stronie przesyłki wyświetla się wtedy „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika AnserX.” oraz link „Poproś przewoźnika AnserX o pomoc” do strony kontaktowej AnserX. Po trzech tygodniach bez nowego skanu pojawia się komunikat „Od ponad trzech tygodni nic nowego. Jeśli przesyłka wciąż nie dotarła, zapytaj sprzedawcę. Peek dalej pyta przewoźnika AnserX.”

Możesz wybrać powiadomienia dla opcji „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek jedynie monitoruje aktualizacje śledzenia i nie może odprawić przesyłki celnie ani skontaktować się z AnserX lub Twoim lokalnym kurierem za Ciebie.

## Pytania o śledzenie AnserX

### Czym jest AnserX (*斑头雁*)?

AnserX (*Shenzhen AnserX International Logistics Technology Co., Ltd.*, założona w 2018 roku w Shenzhen) to chiński operator logistyki transgranicznej e-commerce oferujący dedykowane lotnicze linie małych paczek, wysyłkę FBA oraz magazyny zagraniczne dla USA, Europy, Kanady, Australii, Japonii, Meksyku i innych kierunków.

### Co oznacza numer śledzenia `BTYCN...YQ`?

`BTYCN` + 10 cyfr + `YQ` (na przykład `BTYCN4829173640YQ`, cyfry wymyślone) to 17-znakowy transgraniczny list przewozowy AnserX.

### Dlaczego AnserX pokazuje `Relased from export customs` po przybyciu do mojego kraju?

To osobliwość tłumaczenia w angielskim komunikacie AnserX dla kodu `BTY_EXCC_COMPLETED` — odpowiadający mu chiński tekst (`目的地进口清关完成`) potwierdza zakończenie odprawy importowej w kraju docelowym i przekazanie paczki lokalnemu kurierowi.

### Jak skontaktować się z obsługą klienta AnserX?

W sprawach zamówień i zwrotów pieniędzy napisz najpierw do sklepu lub platformy. Dla zapytań do przewoźnika AnserX podaje adres `bantouyankf@anserx.com` oraz całodobowe infolinie w Shenzhen `0755-2891 8474` i `0755-2868-5256` na [anserx.com/contact-us.html](https://www.anserx.com/contact-us.html).

:::sources
- [AnserX: oficjalna strona](https://www.anserx.com/) – dedykowane linie małych paczek, limity FBA 0–30 kg / 1,2 m, magazyny zagraniczne i siedziba w Shenzhen
- [AnserX: o nas](https://www.anserx.com/about.html) – założona w 2018 r., trasy globalne (USA, Europa, Japonia, Azja Południowo-Wschodnia, Rosja, Australia) i inteligentna platforma trasowania
- [AnserX: kontakt](https://www.anserx.com/contact-us.html) – `bantouyankf@anserx.com`, `0755-2891 8474`, `0755-2868-5256`, obsługa online `09:00–24:00` i adres w Shenzhen (URL pomocy linkowany przez Peek)
- [AnserX: portal śledzenia OMS](https://oms.anserx.com/tracking) – publiczne sprawdzanie przesyłek
- [Universal Parcel Scraper: anserx](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/anserx) – wykrywanie `BTYCN...YQ`, reguła linku `oms.anserx.com/tracking?nos=`, kody `BTY_*`, czas pekiński dla własnych skanów i lokalna strefa czasowa dla przekazywanych, odczyt lokalnego `deliveryNo` i URL pomocy
:::
