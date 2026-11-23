---
title: Śledzenie Planzer: znaczenie statusów i kontakt
description: Śledź paczkę Planzer w Szwajcarii i Liechtensteinie: gdzie znaleźć numer, dlaczego Shipped oznacza doręczoną paczkę, punkty Parcel shop i kontakt.
slug: sledzenie-planzer
published: 2026-11-23
updated: 2026-11-23
---

Planzer Paket (na angielskich stronach Planzer Parcel) to usługa paczkowa szwajcarskiej grupy Planzer, doręczająca przesyłki do 30 kg w Szwajcarii i Liechtensteinie. Numer śledzenia znajdziesz w e-mailu lub SMS-ie zatytułowanym „A delivery is on its way to you” od sklepu albo Planzer. Zawarty w nim link otwiera portal odbiorcy Planzer; możesz też wkleić numer w pole powyżej.

## Gdzie znaleźć numer śledzenia Planzer

Po niemiecku wiadomość nosi tytuł „Eine Sendung ist unterwegs für Sie”, po francusku „Un colis est en chemin pour vous”, a po włosku „A breve riceverà una spedizione”. Nic nie przyszło? Sprawdź folder spam i zapytaj nadawcę — część sklepów nie wysyła powiadomień albo wysyła je bez numeru. Zobacz [jak znaleźć numer śledzenia](guide:find-tracking-number).

Dwa inne kody nie służą do śledzenia: „Reference number” na stronie Planzer to własny numer nadawcy, a kod doręczenia z awiza otwiera portal. Samo wpisanie numeru pokazuje jedynie status — do zmiany doręczenia użyj linku z wiadomości.

Planzer nie publikuje oficjalnego schematu numeracji. Peek rozpoznaje takie wzory (cyfry wymyślone):

| Numer | Do kogo należy |
| --- | --- |
| `9134 6097 4829 1736 4058` | Planzer: 20 cyfr zaczynających się od `91346097`. |
| `999.90.48291736` | Współdzielona przesyłka Planzer: 13 cyfr zaczynających się od `99990`. |
| `448291736405829173` | [Quickpac](carrier:quickpac), część Planzer Paket: 18 cyfr zaczynających się od `44`. |
| `99.60.482917.36405829` | [Swiss Post](carrier:swiss-post): 18 cyfr, dłuższy niż kropkowany zapis Planzer. |

Skopiuj numer zamiast wpisywać go ręcznie: jedna cyfra za dużo sprawia, że Peek pyta innego przewoźnika, np. [SEUR](carrier:seur), zamiast Planzer ([dlaczego długości się nakładają](guide:tracking-number-formats)).

## Statusy śledzenia Planzer wyjaśnione

Portal odbiorcy Planzer (tracking.app.planzer.ch) używa języka przeglądarki spośród angielskiego, niemieckiego, francuskiego i włoskiego, domyślnie wracając do angielskiego. Angielskie statusy:

| Status | Co oznacza dla ciebie |
| --- | --- |
| `Recorded` | [Zarejestrowana](guide:tracking-statuses): Planzer otrzymał dane przesyłki. |
| `Delivery day unknown`, `Expected` | Brak jeszcze wyznaczonego dnia albo przewidywany dzień doręczenia. |
| `Transferred`, `Shipment on the way` | W drodze przez sieć Planzer. |
| `In delivery`, `Shipment out for delivery` | W doręczeniu z kierowcą. |
| `Not delivered – A delivery card has been deposited`, `Not delivered` | Kierowca cię nie zastał — sprawdź awizo w skrzynce. |
| `New delivery released` | Zlecono nowe doręczenie po nieobecności. |
| `Collection at`, `Collected at` | Czeka do odbioru we wskazanym Planzer Parcel shop albo już tam odebrana. |
| `Shipped` | Doręczona — nie „wysłana ze sklepu”. |
| `Shipment delivered`, `Delivered`, `Parcel delivered` | Doręczona. |

`Shipped` często wprowadza w błąd: w angielskim śledzeniu Planzer oznacza doręczenie. Jeśli widnieje mimo braku paczki, sprawdź kroki poniżej. Po niemiecku, francusku i włosku doręczone przesyłki mają opis `Ausgeliefert`, `Livré` lub `Consegnato`.

W katalogu Peek numery na `99990` to przesyłki współdzielone czytane na trackandtrace.planzergroup.com (po niemiecku), gdzie pojawia się pięć etapów: `Erfasst` (etykieta utworzona), `Abholung` (odebrana od nadawcy, nie przez ciebie), `Umschlaglager` (w magazynie przeładunkowym), `In Auslieferung` (w doręczeniu) i `Ausgeliefert` (doręczona).

## Doręczenie Planzer, punkty Parcel shop i nieobecność

**Kiedy przyjeżdża.** Zwykle następnego dnia roboczego od poniedziałku do piątku, choć regulamin Planzer nie daje gwarancji terminu. Dzień doręczenia widnieje w powiadomieniu i portalu.

**Gdzie zostawia paczkę.** Standardowo kierowca zostawia przesyłkę bez podpisu i robi zdjęcie z lokalizacją GPS jako dowód. Jeśli nadawca na to pozwala, w sekcji „Manage the delivery” z linku w wiadomości możesz wskazać bezpieczne miejsce (skrzynka, klatka lub zadaszone wejście) albo inny dzień; wyszarzona opcja oznacza blokadę nadawcy. Planzer nie doręcza do skrytek pocztowych, My Post 24 ani PickPost.

**Nieudane doręczenie.** Planzer Paket podejmuje jedną próbę, zostawia kartę w skrzynce i wysyła e-mail lub SMS. Kod z karty pozwala zamówić ponowne doręczenie albo sprawdzić, który Planzer Parcel shop przechowuje paczkę: przesyłka, której nie da się wręczyć ani zostawić, trafia automatycznie do pobliskiego punktu. Regulamin przewiduje drugą próbę tylko na wniosek twój lub nadawcy w ciągu 7 dni kalendarzowych — inaczej paczka może wrócić do nadawcy po co najmniej 7 dniach roboczych.

**Parcel shops.** Bezpłatne punkty partnerskie lub automaty odbiorcze, część czynna całą dobę. Znajdź punkt po kodzie pocztowym na [stronie Parcel shop Planzer Paket](https://www.planzer-paket.ch/en/parcel-shop/) lub przekieruj paczkę przed pierwszą próbą w sekcji „Shipment information” w portalu. Punkt przechowuje paczkę zwykle 7 dni: zabierz kod odbioru z portalu i dowód tożsamości. Inna osoba, której pełne imię i nazwisko tam wpiszesz, odbierze ją z własnym dokumentem.

## Paczka Planzer opóźniona, zaginiona lub uszkodzona

Umowę z Planzer Paket ma nadawca, więc zaginięcia, szkody i zwroty załatwia się przez nadawcę.

**Brak aktualizacji od kilku dni.** Sprawdź dzień doręczenia w portalu, a potem skontaktuj się z nadawcą lub wyślij formularz Planzer Paket (obsługuje brakujące paczki oraz skargi za opóźnienia lub niedotrzymane płatne okna czasowe). Zobacz [śledzenie nie aktualizuje się](guide:tracking-not-updating).

**Doręczona, ale jej nie ma.** Upewnij się, że status to `Parcel delivered`, i sprawdź „Delivery confirmation” w portalu (dowód i ewentualne zdjęcie, jeśli nadawca je udostępnia). Nadal brak? Użyj formularza kontaktowego i zobacz [doręczona, ale nie otrzymana](guide:delivered-not-received). Przy udzielonej zgodzie na pozostawienie bez podpisu formularz zastrzega, że ryzyko utraty przechodzi na ciebie.

**Uszkodzona lub zagubiona.** Planzer odsyła do nadawcy, który poprosi o zdjęcia towaru, opakowania i etykiety. Działaj szybko: umowa nadawcy wymaga pisemnego zgłoszenia szkód ukrytych w ciągu 8 dni roboczych od doręczenia i ogranicza odpowiedzialność Planzer do 500 CHF za zdarzenie.

**Zwroty.** Zapytaj nadawcę. Jeśli w portalu widnieje „Return a shipment”, wydrukuj etykietę i nadaj paczkę bezpłatnie w Planzer Parcel shop.

## Obsługa klienta i numer telefonu Planzer

Kanały publikowane na stronach Planzer Paket i Quickpac:

- **Centrum Obsługi Klienta:** 0848 00 19 36, poniedziałek–piątek 8:00–18:00 (czas szwajcarski), 0,08 CHF za minutę ze szwajcarskich telefonów stacjonarnych. Przygotuj numer śledzenia.
- **Formularz kontaktowy:** na [stronie kontaktowej Planzer Paket](https://www.planzer-paket.ch/en/contact/) w sprawach zaginięcia, ponownego doręczenia, zmiany terminu, adresu, szkód lub skarg, z opcją oddzwonienia.
- **Inne linie:** strona główna Quickpac podaje numery 044 438 50 40, 021 821 02 02 i 091 611 54 84 (po jednym na język) dla Planzer Paket, a okno śledzenia na planzer-paket.ch podaje +41 44 438 50 40 w komunikacie błędu. Żadna strona nie wyjaśnia ich relacji z 0848.
- **Z zagranicy:** żadna strona nie potwierdza działania 0848 spoza Szwajcarii ani nie podaje formy z +41, dlatego skorzystaj z formularza.
- **E-mail:** brak adresu dla odbiorców; nota prawna podaje info@planzer-paket.ch.
- **Adres:** Planzer Paket, Lerzenstrasse 14, 8953 Dietikon.

> Planzer ostrzega przed fałszywymi e-mailami i SMS-ami z prośbą o potwierdzenie paczki przez link. Prześlij podejrzaną wiadomość jako załącznik na security@planzer.ch, a Planzer sprawdzi jej autentyczność.

## Śledzenie paczki Planzer w Peek

[Peek](/) od razu rozpoznaje 20-cyfrowy numer zaczynający się od `91346097` lub link tracking.app.planzer.ch z parametrem `deliveryNumber=`, wyświetlając status, historię, przewidywany termin, godzinę doręczenia, kraj docelowy, łączną wagę (gdy wszystkie paczki ją podają) oraz wymiary pojedynczej paczki. Jeśli Planzer podaje odbiorcę, Peek pokazuje ci też jego imię i nazwisko pod „Odbiorca” oraz, dopóki paczka jest w drodze, miejscowość docelową na mapie; osoba, której udostępnisz paczkę, nie widzi ani jednego, ani drugiego, a nazwisko osoby, która podpisała odbiór, pozostaje prywatne: żadna aplikacja go nie pokazuje. `Shipped` wyświetla się poprawnie jako dostarczona.

Dla 13-cyfrowego numeru na `99990` Peek prosi o pełny współdzielony link z trackandtrace.planzergroup.com (wraz z `accessKey`) i odczytuje dotychczasowe etapy z godzinami oraz przewidywaną datą.

Dopóki masz paczkę otwartą w Peek albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza status do co 10 minut — a od 6:00 (czasu szwajcarskiego) w dniu, gdy Planzer zeskanuje paczkę w doręczeniu, co 2 minuty, dopóki odpowiada własne śledzenie Planzer (przez usługi śledzenia tego dnia do co 15 minut, gdy masz ją otwartą lub włączone powiadomienia). W pozostałych przypadkach sprawdza co godzinę. Po dwóch dniach bez zmian (12 godzinach, gdy przesyłka nadal ma status „Zarejestrowana” lub „Przyjęta przez przewoźnika”) Peek sprawdza co godzinę, nawet gdy masz ją otwartą, a po siedmiu dniach na etapie „Zarejestrowana” — co 6 godzin. Jeśli paczka na etapie „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie” nie ma nowego skanu przez cztery dni, Peek pokazuje „Brak aktualizacji od czterech dni” (lub „Brak aktualizacji od…” z datą), komunikat „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika Planzer.” oraz link „Poproś przewoźnika Planzer o pomoc”. Peek wysyła powiadomienia w trybie „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Ponieważ Planzer nie podaje miejscowości przy skanach, na mapie niewiele się pojawia, a Peek nie skontaktuje się z Planzer za ciebie.

## Pytania o śledzenie Planzer

### Czym jest Planzer Paket?

To dział paczkowy Planzer Transport AG z Dietikon w szwajcarskiej grupie Planzer. Paczki jadą nocą pociągiem towarowym do regionu doręczenia, a od 2024 roku należy do niego także Quickpac.

### O której godzinie doręcza Planzer?

Planzer nie podaje stałych godzin doręczeń. Nadawcom przekazuje, że paczki bez wykupionego przedziału docierają najpóźniej wieczorem następnego dnia roboczego, bez gwarancji w regulaminie. Nadawca może dokupić doręczenie do 8:00, 10:00 lub 12:00 (czasu szwajcarskiego), zależnie od kodu pocztowego.

### Czy Planzer doręcza w soboty?

Z zasady nie: regulamin Planzer przewiduje doręczenia od poniedziałku do piątku. Doręczenie sobotnie jest wyłącznie usługą dodatkową zamawianą przez nadawcę — zapytaj sklep.

:::sources
- [Planzer Paket: Contact](https://www.planzer-paket.ch/en/contact/) – numer, awizo, portal, problemy, telefon
- [Planzer Paket: Parcel shop](https://www.planzer-paket.ch/en/parcel-shop/) – punkty, siedem dni, zwroty
- [Planzer Paket: Terms & Conditions](https://cms.planzer.ch/doc/agb-pp/?lang=en) – obszar, próby, dowód, odpowiedzialność
- [Planzer: Parcels](https://www.planzer.ch/en/transport-en/parcels/) – 30 kg, kolej, następny dzień roboczy, przedziały czasowe
- [Planzer: Security tips](https://www.planzer.ch/en/phishing-2/) – fałszywe wiadomości i security@planzer.ch
- [Planzer Paket: Receiving](https://www.planzer-paket.ch/en/receiving/) – pole śledzenia, +41 44 438 50 40
- [Planzer: Tracking](https://tracking.app.planzer.ch/) – języki, numery, etykiety
- [Planzer Paket: Kontakt](https://www.planzer-paket.ch/de/kontakt/) – tematy formularza, ryzyko pozostawienia, sobota
- [Planzer Paket: Impressum](https://www.planzer-paket.ch/de/impressum/) – operator, adres, e-mail prawny
- [Quickpac: Home](https://quickpac.ch/de) – w grupie Planzer Paket od 2024 roku, linie telefoniczne
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formaty, link współdzielony i odczyt w Peek
:::
