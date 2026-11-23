---
title: Śledzenie GOFO Canada: numery GFCA, statusy i opóźnienia
description: Śledź paczkę GOFO w Kanadzie: format numeru GFCA, znaczenie skanów w Peek oraz co zrobić przy opóźnieniu, nieudanej próbie doręczenia lub zaginięciu.
slug: sledzenie-gofo-ca
published: 2026-11-23
updated: 2026-11-23
---

GOFO Canada to kanadyjska sieć kurierska GOFO, firmy działającej w kilku krajach i korzystającej z tego samego systemu śledzenia co GOFO w Stanach Zjednoczonych. GOFO informuje, że jej kanadyjska sieć działa w Toronto i Vancouver, a kolejne obszary są w przygotowaniu. Numery zaczynają się od `GFCA`: sprawdź przesyłkę na kanadyjskiej stronie śledzenia gofo.com albo wklej numer w pole powyżej. Nie masz jeszcze numeru? Zobacz [jak znaleźć numer śledzenia](guide:find-tracking-number).

## Numer śledzenia GOFO Canada: jak wygląda

Peek przypisuje numer do GOFO Canada, gdy ma taki kształt — wielkość liter i spacje nie mają znaczenia:

| Format (wymyślony) | Co to jest |
| --- | --- |
| `GFCA48291736405829` | `GFCA` i 14 cyfr: list przewozowy GOFO Canada. Literę `O` wpisaną tuż po `GFCA` Peek odczytuje jako zero. |

GOFO nadaje też każdej paczce numer śledzenia, który powtarza list przewozowy `GFCA` albo stanowi numer referencyjny sklepu. Peek obsługuje wyłącznie list przewozowy `GFCA`, więc właśnie ten numer wklej.

**Podobne numery.** Pozostałe sieci GOFO używają innych prefiksów: `GFUS` i 14 cyfr, `GF` i 13, `CR` i 12 albo `MGP` i 13 należą do [GOFO Express](carrier:gofo) w USA; `GFFR` i `CIFR` do [GOFO France](carrier:gofo-fr); `GFIT` i `CIIT` do [GOFO Italy](carrier:gofo-it); `GFNL` i `GFES` do sieci holenderskiej i hiszpańskiej. Żaden z nich nie jest numerem GOFO Canada. Więcej wskazówek: [formaty numerów śledzenia](guide:tracking-number-formats).

## Statusy śledzenia GOFO Canada

GOFO Canada używa tych samych kodów skanów co GOFO US, a Peek przypisuje je w identyczny sposób. Paczka w Kanadzie przechodzi zwykle przez takie etapy:

| Skan GOFO | Etap w Peek | Co oznacza dla ciebie |
| --- | --- | --- |
| `Shipping Label Created` | Zarejestrowana | Sklep utworzył etykietę. GOFO jeszcze nie ma paczki. |
| `Arrived at GOFO Regional Hub` | W transporcie | Dotarła do regionalnego centrum GOFO. |
| `Loaded on Vehicle` | W transporcie | Załadowana na transport między obiektami GOFO w drodze do stacji doręczeń. |
| `Arrived at GOFO Delivery Station` | W transporcie | W stacji GOFO obsługującej doręczenie. GOFO podaje zwykle 1–2 dni kalendarzowe do doręczenia. |
| `Out for Delivery` | W doręczeniu | U kuriera w doręczeniu. |
| `Delivered` | Dostarczona | Doręczona. |

Ponieważ kody są wspólne, komunikaty znane z przesyłek w USA wyświetlają się tak samo w Kanadzie. `Delivery Exception, Business Closed` oznacza nieudaną próbę i pojawia się jako „Próba doręczenia” (powód po przecinku bywa różny). `Label Cancelled` oznacza anulowaną etykietę — pod tym numerem żadna paczka nie przyjdzie; Peek wyświetla „Zgłoszono problem”.

GOFO dodaje do części skanów telefon i e-mail działu wsparcia; Peek usuwa tę linię, tak jak robi to strona GOFO. Skan doręczenia wyświetla się po prostu jako `Delivered`, bez dopisków po przecinku.

## Paczka GOFO Canada opóźniona, nieodebrana lub zaginiona

**Brak ruchu.** Gdy widać tylko `Shipping Label Created`, GOFO nie ma jeszcze przesyłki — sklep musi ją przekazać. Peek dalej pyta GOFO i wyświetla ostrzeżenie, jeśli przed doręczeniem miną cztery dni bez nowego skanu. Więcej w poradniku [śledzenie nie aktualizuje się](guide:tracking-not-updating).

**Nieudana próba doręczenia.** Pojawia się jako „Próba doręczenia” z podanym przez GOFO powodem. GOFO wskazuje, że paczka będąca już w doręczeniu ma zwykle kolejną próbę w ciągu 24 godzin; przy problemie z adresem (np. brak numeru mieszkania) może wrócić do lokalnego magazynu do weryfikacji. Dalszy krok zależy od GOFO i sklepu — zobacz [nieudane doręczenie](guide:missed-delivery). Jeśli dostaniesz SMS z prośbą o opłatę za ponowne doręczenie GOFO, przeczytaj najpierw [fałszywe SMS-y o paczkach](guide:delivery-scam-texts).

**Doręczona, ale jej nie ma.** Peek pokazuje godzinę skanu doręczenia, lecz nie zdjęcie ani dopiski GOFO po przecinku. Oficjalna strona gofo.com/ca/track udostępnia dowód doręczenia — najczęściej zdjęcie miejsca pozostawienia paczki — po podaniu nazwiska odbiorcy z zamówienia. Sprawdź najpierw okolicę wejścia, skrzynkę pocztową lub wspólny punkt skrzynek, zapytaj sąsiadów, domowników, portiera lub ochronę budynku. Jeśli paczki nadal nie ma, zgłoś to GOFO (kontakty niżej), najlepiej ze zdjęciem budynku z zewnątrz, oraz napisz do sklepu z numerem zamówienia i kodem `GFCA`. Zobacz [doręczona, ale nie otrzymana](guide:delivered-not-received).

**Opóźniona lub zagubiona.** Napisz do sklepu, który ją wysłał: dysponuje danymi zamówienia i wysyłki, a GOFO podaje, że nadawca może złożyć reklamację i zapytanie w twoim imieniu. Zobacz [zaginiona paczka](guide:lost-parcel). Jeśli przesyłka wraca do nadawcy, sprawdź [zwrot do nadawcy](guide:returned-to-sender): GOFO informuje, że paczki w zwrocie nie da się przechwycić, więc poproś sklep o wymianę lub ponowną wysyłkę.

Zamówienie z Temu lub Shein? Zobacz [zamówienia Temu](guide:temu-orders) i [zamówienia Shein](guide:shein-orders). Informacje o opłatach celnych znajdziesz w poradniku [cło](guide:customs).

## Obsługa klienta GOFO Canada

W sprawie opóźnionego, brakującego lub uszkodzonego zamówienia zacznij od sklepu, mając pod ręką numer zamówienia i numer `GFCA`. W kwestiach doręczenia lub śledzenia GOFO podaje te kanały na [kanadyjskiej stronie obsługi klienta](https://www.gofo.com/ca/customer-service):

- **E-mail:** cs@mail.gofo.com
- **Telefon (infolinia dla odbiorców):** +1 (949) 688-6032, poniedziałek–piątek 9:00–21:00, sobota–niedziela 11:00–19:00 (czas wschodni, Eastern Time).
- **WhatsApp:** +1 (787) 469-5811, poniedziałek–piątek 9:00–21:00, sobota–niedziela 11:00–20:00 (czas wschodni, Eastern Time).
- **Online:** formularz zgłoszeniowy (wymaga numeru listu przewozowego) oraz czat na tej samej stronie.

## Śledzenie paczki GOFO Canada w Peek

[Peek](/) rozpoznaje numer GOFO Canada od razu po formacie: `GFCA` i 14 cyfr, wielkimi lub małymi literami, ze spacjami lub bez. Działa też link śledzenia GOFO Canada z gofo.com zawierający `searchID=` i numer. Peek śledzi wyłącznie list przewozowy `GFCA`, nie numer referencyjny sklepu.

Peek odczytuje własne śledzenie GOFO Canada: status, każdy skan z godziną oraz miastem i prowincją podanymi przez GOFO, te miejsca na mapie, godzinę doręczenia oraz kraj docelowy. Opisy zachowują angielskie brzmienie GOFO bez dopisku z kontaktem. Peek pokazuje przewidywaną datę doręczenia GOFO, dopóki paczka nie jest w doręczeniu, doręczona, zwrócona ani w punkcie odbioru, i pomija zdjęcia z doręczenia, współrzędne oraz wagę.

Dopóki masz paczkę otwartą w Peek albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza status do co 10 minut — a od 6:00 czasu lokalnego w dniu, gdy GOFO zeskanuje paczkę w doręczeniu, co 2 minuty, dopóki odpowiada własne śledzenie GOFO Canada. W pozostałych przypadkach sprawdza co godzinę. Po dwóch dniach bez zmian (12 godzinach, gdy przesyłka nadal ma status „Zarejestrowana” lub „Przyjęta przez przewoźnika”) Peek sprawdza co godzinę, nawet gdy masz ją otwartą, a po siedmiu dniach na etapie „Zarejestrowana” — co 6 godzin. Jeśli paczka na etapie „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie” nie ma nowego skanu przez cztery dni, Peek pokazuje „Brak aktualizacji od czterech dni” (lub „Brak aktualizacji od…” z datą), komunikat „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika GOFO Canada.” oraz link „Poproś przewoźnika GOFO Canada o pomoc”. Peek wysyła powiadomienia w trybie „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Nie może zamówić ponownego doręczenia ani skontaktować się z GOFO lub sklepem w twoim imieniu.

## Pytania o śledzenie GOFO Canada

### Czy GOFO Canada jest wiarygodne?

Rejestr Corporations Canada wykazuje GOFO EXPRESS CANADA INC. jako aktywną spółkę federalną nr 1807362-7 z siedzibą w Mississauga w prowincji Ontario, przeniesioną pod Canada Business Corporations Act w lipcu 2026 roku. GOFO prowadzi też sieci w USA, Francji, Włoszech, Holandii i Hiszpanii, które Peek śledzi osobno.

### Co oznacza „Loaded on Vehicle”?

GOFO załadowało paczkę na transport między dwoma swoimi obiektami, zanim dotrze ona do stacji doręczeń. Zwykle następny skan to `Arrived at GOFO Delivery Station`, a później `Out for Delivery`.

### Dlaczego mój numer śledzenia GOFO nie działa?

Sprawdź najpierw prefiks: numer GOFO Canada to `GFCA` i 14 cyfr. Kody `GFUS`, `GFFR` lub `GFIT` należą do sieci w USA, Francji albo Włoszech, które Peek śledzi pod ich własnymi nazwami. Jeśli numer `GFCA` wciąż nie zwraca wyników, porównaj go z e-mailem wysyłkowym i zapytaj sklep.

:::sources
- [Corporations Canada: GOFO EXPRESS CANADA INC.](https://ised-isde.canada.ca/cc/lgcy/fdrlCrpDtls.html?corpId=18073627) – aktywna spółka federalna 1807362-7, przeniesiona z Ontario 8 lipca 2026, siedziba w Mississauga
- [Universal Parcel Scraper: GOFO Canada](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/gofo-ca) – kształt numeru, linki, opisy i etapy wspólne z GOFO US, linia kontaktu, dane pokazywane i pomijane, link pomocy GOFO Canada
- [GOFO Canada: Customer Service](https://www.gofo.com/ca/customer-service) – e-mail, infolinia odbiorców i godziny, WhatsApp, formularz i czat; dowód doręczenia, gdzie szukać, ponowna próba w 24 godziny, 1–2 dni ze stacji, reklamacje przez nadawcę, brak możliwości przechwycenia zwrotu
- [GOFO Canada](https://www.gofo.com/ca) – sieć aktywna w Toronto i Vancouver, dalsza rozbudowa w toku
:::
