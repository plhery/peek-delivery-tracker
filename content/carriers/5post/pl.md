---
title: Śledzenie 5Post: numery, odbiór Pyaterochka i statusy
description: Śledź przesyłki 5Post (FivePost / X5) w Rosji: numery P z Avito i kody sklepów, rosyjskie statusy, odbiór w Pyaterochka i Perekrestok oraz pomoc.
slug: sledzenie-5post
published: 2026-11-24
updated: 2026-11-24
---

5Post (*5Пост* / *FivePost*, działająca w ramach X5 Group) to rosyjska sieć przesyłek e-commerce, wysyłek między osobami prywatnymi i fulfillmentu, która doręcza zamówienia internetowe i paczki Avito Delivery do ponad **25 700** automatów paczkowych (*постаматы*) oraz kasowych punktów odbioru w sklepach **Pyaterochka** («Пятёрочка») i **Perekrestok** («Перекрёсток») w **4 950** miastach i miejscowościach w Rosji, w oparciu o **51** sortowni ([fivepost.ru](https://fivepost.ru/)). 5Post współpracuje z ponad 1 000 sklepów internetowych i platform — w tym z Avito Delivery i międzynarodowymi zamówieniami AliExpress — a także oferuje dostawy kurierskie pod drzwi oraz fracht LTL/FTL.

Przesyłkę sprawdzisz na [fivepost.ru](https://fivepost.ru/) (w sekcji *Где мой заказ?*) albo wklejając numer zamówienia lub link `fivepost.ru/tracking?id=...` w pole powyżej.

## Formaty numerów śledzenia 5Post

5Post śledzi przesyłki według numeru zamówienia ze sklepu lub platformy — dowolnego pojedynczego ciągu od 3 do 40 liter i cyfr zawierającego co najmniej jedną cyfrę:

| Format (wymyślony) | Co to jest |
| --- | --- |
| `P48291736051` | `P` + 11 cyfr (12 znaków), format numerów Avito Delivery. Peek pyta [Colis Privé](carrier:colis-prive) i 5Post, w Rosji najpierw 5Post. |
| `99994829173605` | 14 cyfr, numer zamówienia sklepu internetowego. Peek nigdy nie wskazuje dla niego 5Post i pyta spółki DPD, Chronopost, SEUR, BRT oraz inne: wybierz 5Post ręcznie albo wklej link 5Post. |

Sklepy same nadają numery zamówień, więc dwa zamówienia mogą mieć identyczny kod. Strona 5Post prosi wtedy o cyfry numeru telefonu odbiorcy, a Peek nie jest w stanie rozróżnić takich przesyłek. Link `fivepost.ru/tracking?id=...` od razu przypisuje przesyłkę do 5Post. Inne wzorce opisuje [przewodnik po formatach numerów śledzenia](guide:tracking-number-formats).

## Co oznaczają statusy śledzenia 5Post

Śledzenie 5Post łączy techniczne kody statusów z opisami po rosyjsku i podaje regionalny magazyn sortowniczy (`РЦ ...`) przy skanach magazynowych i wysyłkowych.

| Kod 5Post i rosyjski komunikat | Co oznacza |
| --- | --- |
| `INFORMATION_RECEIVED` — `Ожидается передача заказа в 5post` | Dane zamówienia zostały zarejestrowane; 5Post czeka na przekazanie przesyłki przez sprzedawcę lub nadawcę. |
| `PROCESS_WITHDRAWN` — `Передан службе доставки` | Nadawca nadał lub przekazał paczkę do 5Post. |
| `WAREHOUSE_PROCESSING` — `Обрабатывается на складе ...` | Przetwarzana w regionalnym magazynie sortowniczym 5Post (`РЦ`). |
| `READY_FOR_SHIPMENT` — `Покинул склад ...` | Opuściła sortownię w drodze do kolejnego hubu lub sklepu odbioru. |
| `DELIVERED` — `Доставлен в пункт выдачи` | Dotarła do wybranej kasy Pyaterochka / Perekrestok lub automatu 5Post — **do odbioru**, jeszcze nieodebrana! |
| `DONE` — `Получен` | Odebrana przez odbiorcę (dostarczona). |
| `RECEIVED_BY_COURIER` — `Передан курьеру` | W doręczeniu pod drzwi dla zamówień z dostawą kurierską. |
| `RETURNING_BY_COURIER` — `Неуспешная попытка вручения` | Próba doręczenia przez kuriera nie powiodła się. |
| `CUSTOMS_CLEARANCE_STARTED` / `CUSTOMS_DATA_REQUIRED` / `CUSTOMS_AWAITING_PAYMENT` | Zamówienie z zagranicy (np. AliExpress) w trakcie odprawy celnej, oczekuje na Twoje dane dla przedstawiciela celnego albo na opłacenie należności. |
| `CUSTOMS_CLEARANCE_COMPLETED` — `Таможенное оформление завершено` | Urząd celny zwolnił paczkę do dalszego transportu przez 5Post. |
| `UNCLAIMED_...` / `RETURNED_TO_PARTNER` / `RETURNED_TO_CLIENT` | Nieodebrana w terminie przechowywania lub anulowana po przekazaniu, wraca do sklepu lub nadawcy (`UNCLAIMED_READY_FOR_REPICKUP` oznacza przygotowanie do ponownego dostarczenia do punktu odbioru). |
| `INCEPTION_PROBLEM` / `LOST` / `CANCELLED` / `REGISTRATION_ERROR_...` | Problem z zamówieniem, anulowanie, zagubienie albo brakujący numer telefonu / data dostawy / adres wymagający poprawy. |

Zwróć uwagę na ważną specyfikę kodów 5Post: `DELIVERED` (`Доставлен в пункт выдачи`) oznacza **do odbioru** w sklepie lub automacie, natomiast dopiero `DONE` (`Получен`) oznacza faktyczne odebranie przesyłki.

## Odbiór w Pyaterochka, Perekrestok i automatach paczkowych

Zamówienie do odbioru czeka w automacie 5Post (*постамат*) albo przy kasie (*касса*) sklepu Pyaterochka lub Perekrestok wybranego przy zakupie; FAQ 5Post podaje godziny otwarcia automatów i punktów odbioru:

- **Kod odbioru:** gdy paczka dotrze do punktu (`Доставлен в пункт выдачи`), 5Post wysyła kod odbioru SMS-em. Wpisz go na ekranie automatu lub podaj przy kasie. FAQ 5Post wyjaśnia, gdzie znaleźć kod i co zrobić, gdy obsługa nie może odszukać zamówienia.
- **Przedłużenie przechowywania i zmiana telefonu:** zgodnie z [FAQ 5Post](https://fivepost.ru/faq/) odbiorcy mogą sprawdzić czas przechowywania, przedłużyć go, zapłacić online na stronie lub w aplikacji mobilnej albo znaleźć instrukcję zmiany numeru telefonu, otwarcia zablokowanej skrytki czy zgłoszenia pustej lub niewłaściwej skrytki.
- **Zamówienia zagraniczne (AliExpress):** przesyłki z zagranicy przez 5Post obejmują transport lotniczy (`DEPARTED_BY_AIR`, `DELIVERED_BY_AIR`) i rosyjską odprawę celną; gdy śledzenie pokazuje `Требуется предоставить данные таможенному представителю` (`CUSTOMS_DATA_REQUIRED`) lub `Требуется оплата таможенных платежей` (`CUSTOMS_AWAITING_PAYMENT`), przedstawiciel celny potrzebuje Twoich danych albo należności celne nie są opłacone; FAQ 5Post opisuje, które zamówienia podlegają odprawie, po co są dane, jak skontaktować się z przedstawicielem i kiedy zapłacić. Zobacz [przewodnik celny](guide:customs) oraz [dlaczego śledzenie przestaje się aktualizować](guide:tracking-not-updating).

Dla przesyłek obsługiwanych w Rosji przez pocztę narodową lub innego kuriera porównaj z [Russian Post](carrier:russian-post) i [CDEK](carrier:cdek).

## Śledzenie 5Post w Peek

Wklej swój numer `P`, numer zamówienia ze sklepu lub link `fivepost.ru/tracking?id=...` do [Peek](/). Dla numeru `P` Peek pyta Colis Privé i 5Post (w Rosji najpierw 5Post) i pokazuje „5Post ma tę przesyłkę.”, gdy 5Post odpowie. Dla 14-cyfrowego numeru zamówienia nigdy nie wskazuje 5Post: stuknij „Zmień” podczas dodawania lub „Zmień przewoźnika” na stronie przesyłki i wybierz 5Post. Link 5Post od razu przypisuje przesyłkę do 5Post.

Peek odczytuje publiczne śledzenie 5Post przez otwarty [adapter 5Post w universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/5post) i ponawia próbę po błędzie sieci. Pokazuje rosyjski opis każdego skanu, sortownię lub hub wskazany w skanach magazynowych (`РЦ ...`, `ХАБ ...`), nazwę i adres punktu odbioru, gdy paczka czeka w sklepie lub automacie, oraz godzinę odbioru. 5Post oznacza każdy skan przesunięciem UTC, a Peek pokazuje go według zegara Twojego urządzenia. Termin przechowywania, dane płatności, godziny otwarcia punktu, nazwa nadawcy i liczba paczek pozostają na stronie i w aplikacji 5Post.

Peek dokładnie mapuje kody 5Post, aby `DELIVERED` nigdy nie wywołał fałszywego powiadomienia o doręczeniu do domu: **Zarejestrowana** (`INFORMATION_RECEIVED`), **Przyjęta przez przewoźnika** (`PROCESS_WITHDRAWN`), **W transporcie** (sortownia, wyjazd, odcinki lotnicze i przewóz po odprawie), **W urzędzie celnym** (prośba agenta o dane, odprawa i opłata należności), **W doręczeniu** (`RECEIVED_BY_COURIER`), **Próba doręczenia** (`RETURNING_BY_COURIER`), **Do odbioru** (`DELIVERED` — `Доставлен в пункт выдачи`), **Dostarczona** (`DONE` — `Получен`), **Zwrócona nadawcy** (`RETURNED_TO_...` oraz kody `UNCLAIMED_...` poza `UNCLAIMED_READY_FOR_REPICKUP`, który pozostaje W transporcie) oraz **Zgłoszono problem** (`LOST`, `CANCELLED`, `CUSTOMS_CLEARANCE_REJECTED` i błędy rejestracji).

Kiedy najnowszy skan ma cztery dni, a przesyłka nadal stoi na „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie”, Peek oznacza ją dniem tego skanu, np. „Brak aktualizacji od pon 12 paź”. Na stronie przesyłki wyświetla się wtedy „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika 5Post.” oraz link „Poproś przewoźnika 5Post o pomoc” do FAQ 5Post. Po trzech tygodniach bez nowego skanu pojawia się komunikat „Od ponad trzech tygodni nic nowego. Jeśli przesyłka wciąż nie dotarła, zapytaj sprzedawcę. Peek dalej pyta przewoźnika 5Post.”

Dopóki masz przesyłkę otwartą w Peek albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza status do co 10 minut; w dniu, gdy 5Post pokazuje `Передан курьеру`, co 2 minuty od 6:00 według zegara najnowszego skanu, dopóki odpowiada śledzenie 5Post. W pozostałych sytuacjach sprawdza co godzinę. Po 12 godzinach z samym statusem „Zarejestrowana” lub „Przyjęta przez przewoźnika” albo po dwóch dniach bez wiadomości przechodzi na sprawdzanie co godzinę niezależnie od sposobu śledzenia, a po siedmiu dniach na „Zarejestrowana” co 6 godzin. Gdy śledzenie 5Post nie odpowiada i Peek korzysta wyłącznie z zewnętrznych usług śledzenia, sprawdza co godzinę, po spokojnym dniu co 3 godziny i do co 15 minut w dniu, w którym paczka jest w doręczeniu, dopóki masz przesyłkę otwartą lub włączone powiadomienia.

Może powiadamiać Cię o opcjach „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”.

## Kontakt z 5Post

5Post udostępnia oficjalne numery telefonów i formularze kontaktowe (z kontem lub bez) na [fivepost.ru/contacts](https://fivepost.ru/contacts/) i [fivepost.ru/faq](https://fivepost.ru/faq/):

- **Telefon Moskwa i obwód moskiewski:** `8-495-935-88-00` (według taryfy operatora)
- **Regiony Rosji:** `8-800-100-05-05`
- **FAQ i formularz zgłoszeń online:** [FAQ 5Post](https://fivepost.ru/faq/) i [Kontakt 5Post](https://fivepost.ru/contacts/) (zgłoszenia odbiorców bez logowania lub po zalogowaniu z załącznikami do 40 MB)
- **Adres prawny:** 109029, Moskwa, ul. Srednyaya Kalitnikovskaya, 28 bldg. 4 (INN `7722461620`, OGRN `1187746567568`)

## Najczęstsze pytania

### Dlaczego wewnętrzny status 5Post pokazuje `DELIVERED`, zanim odbiorę przesyłkę?

W systemie 5Post `DELIVERED` (`Доставлен в пункт выдачи`) oznacza, że paczka została dostarczona do punktu odbioru lub automatu Pyaterochka/Perekrestok i czeka na Ciebie. Peek wyświetla wtedy **Do odbioru** i czeka na `DONE` (`Получен`), zanim oznaczy paczkę jako **Dostarczona**.

### Gdzie odbiorę zamówienie 5Post?

Odbierzesz je w automacie 5Post (*постамат*) albo przy kasie sklepu Pyaterochka («Пятёрочка») lub Perekrestok («Перекрёсток») wybranego podczas zamawiania, używając kodu z SMS-a od 5Post.

### Dlaczego 5Post poprosił o dane celne do mojego zamówienia z AliExpress?

Zamówienie z zagranicy odprawia przedstawiciel celny, który może potrzebować Twoich danych (`CUSTOMS_DATA_REQUIRED`). FAQ 5Post wyjaśnia, które zamówienia podlegają odprawie, po co potrzebne są dane i jak skontaktować się z przedstawicielem. Po zwolnieniu z cła 5Post pokazuje `Таможенное оформление завершено` (`CUSTOMS_CLEARANCE_COMPLETED`).

### Jak skontaktować się z 5Post, jeśli skrytka się nie otwiera lub brakuje paczki?

Zadzwoń pod `8-495-935-88-00` (Moskwa i region) lub `8-800-100-05-05` (regiony), albo wyślij zgłoszenie odbiorcy (ze zdjęciami do 40 MB) przez [fivepost.ru/contacts](https://fivepost.ru/contacts/) lub konto 5Post.

:::sources
- [5Post (fivepost.ru)](https://fivepost.ru/)
- [FAQ 5Post](https://fivepost.ru/faq/)
- [Kontakt 5Post](https://fivepost.ru/contacts/)
- [universal-parcel-scraper 5Post adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/5post)
:::
