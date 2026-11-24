---
title: Śledzenie PigeonShip: 18-znakowe kody, statusy i pomoc
description: Śledź dostawy tego samego dnia i ostatniej mili PigeonShip w USA i Kanadzie: 18-znakowe kody, statusy na żywo, godziny dostawy i kontakty.
slug: sledzenie-pigeonship
published: 2026-11-24
updated: 2026-11-24
---

PigeonShip to przewoźnik ostatniej mili działający w Stanach Zjednoczonych i Kanadzie, z biurami w Provo (Utah) oraz Oakville (Ontario). Sklepy, nadawcy e-commerce i lokalne firmy korzystają z PigeonShip do doręczeń tego samego dnia, następnego dnia oraz regionalnych poprzez usługi takie jak **Pigeon Flex** (dostawa krajowa 2–5 dni), **Pigeon Express** (dostawa krajowa w 2 dni), **Pigeon Connect** (dostawa tego samego dnia na żądanie) oraz **Pigeon Flash** (planowana dostawa tego samego dnia).

PigeonShip współpracuje z niezależnymi kierowcami i prowadzi dedykowane programy doręczeń dla firm. Odbiorcy otrzymują link śledzenia w domenie `admin.pigeonship.com`, który wyświetla status zamówienia i docelową godzinę dostawy, a po dostarczeniu przechodzi na stronę zakończenia.

## Format numeru śledzenia PigeonShip

Peek przyjmuje numery PigeonShip złożone z 8 do 30 liter i cyfr, usuwa spacje i zamienia litery na wielkie. Peek rozpoznaje jeden 18-znakowy wzorzec naprzemiennych cyfr i wielkich liter:

- **18 znaków alfanumerycznych** o schemacie `cyfra-litera-cyfra-litera-3 cyfry-litera-3 cyfry-litera-2 cyfry-litera-cyfra-2 litery` (na przykład `7P4K829H631N58R2TM`).

Linki śledzenia PigeonShip mają postać `admin.pigeonship.com/shipping/tracking/package/<trackingNumber>/`. Po wklejeniu takiego linku do Peek aplikacja wyciąga kod paczki i kieruje go prosto do PigeonShip. Dla samego 18-znakowego kodu w tym kształcie Peek odpytuje wyłącznie PigeonShip i po odpowiedzi wyświetla „PigeonShip ma tę przesyłkę.” Porównanie z innymi formatami z USA i Kanady znajdziesz w [przewodniku po formatach numerów przesyłek](guide:tracking-number-formats).

## Jak działa śledzenie na żywo PigeonShip

Strona śledzenia PigeonShip dla odbiorcy to widok podsumowujący, a nie historia skanów. Gdy zamówienie jest aktywne, pokazuje „In transit” oraz kierowcę na mapie z docelową godziną dostawy (`dueDate`), jeśli ją ustawiono; pod spodem zamówienie ma kod i etykietę statusu, np. `16` i `Assigned`. Strona wyświetla „Delivered” wyłącznie dla kodu `64`. Po ukończeniu doręczenia PigeonShip zastępuje stronę pod tym samym adresem URL ekranem zakończenia `Your delivery has been completed successfully.` oraz linkiem do zdjęć z doręczenia.

| Status / strona PigeonShip | Co oznacza |
| --- | --- |
| Status `16` — `Assigned` | Zamówienie znajduje się na trasie kierowcy; strona pokazuje „In transit”. |
| Godzina docelowa / `dueDate` | Planowany czas ukończenia doręczenia widoczny dla aktywnych zamówień przed zostawieniem paczki. |
| Status `64` / `Delivered` lub `Your delivery has been completed successfully.` | Kierowca zakończył doręczenie; otwarcie własnego linku PigeonShip pozwala obejrzeć zdjęcie potwierdzające dostawę. |
| `Page not found` (404) | Numer śledzenia nie jest aktywny w systemie PigeonShip na żywo albo zawiera literówkę. |

Ponieważ PigeonShip nie publikuje dziennika skanów z godzinami na stronie odbiorcy, nie zobaczysz pośrednich skanów z magazynów czy sortowni jak w [UPS](carrier:ups), [USPS](carrier:usps), [Canada Post](carrier:canada-post) czy [Intelcom](carrier:intelcom). Zamiast tego strona na bieżąco odświeża stan dostawy, a po zostawieniu przesyłki przełącza się na ekran końcowy.

## Dostawy tego samego dnia i co sprawdzić przy opóźnieniu

PigeonShip oferuje doręczenia tego samego i następnego dnia w całych Stanach Zjednoczonych i Kanadzie. Opinie na stronie przewoźnika wspominają o SMS-ie przed przyjazdem kierowcy i zdjęciu po zostawieniu paczki.

Jeśli zamówienie nadal ma status `Assigned` po przewidywanym czasie albo link pokazuje `Page not found`:

- **Sprawdź literówki:** potwierdź pełny kod lub cały link `admin.pigeonship.com` z e-maila lub SMS-a od sklepu; strona PigeonShip zwraca `Page not found` dla numerów bez aktywnej strony.
- **Skontaktuj się z PigeonShip:** użyj [formularza kontaktowego](https://www.pigeonship.com/contact-us), jeśli zamówienie pozostaje na „In transit” po terminie.
- **Sprawdź wskazówki doręczenia:** opinie na stronie PigeonShip wspominają o kierowcach stosujących się do uwag na etykiecie i zdjęciu z zostawienia paczki; otwórz oryginalny link na `admin.pigeonship.com`, aby obejrzeć zdjęcia po dostawie. Więcej wskazówek znajdziesz w [gdy śledzenie przestaje się aktualizować](guide:tracking-not-updating) oraz w [poradniku o nieudanej dostawie](guide:missed-delivery).

## Śledzenie PigeonShip w Peek

Wklej numer PigeonShip albo pełny link `admin.pigeonship.com/shipping/tracking/package/...` do Peek. Jeśli Peek nie podpowie PigeonShip przy nietypowym formacie kodu, wybierz **Zmień** podczas dodawania przesyłki lub **Zmień przewoźnika** na ekranie przesyłki.

Peek pobiera bezpośrednio stronę śledzenia PigeonShip generowaną po stronie serwera przez otwartoźródłowy [adapter PigeonShip w universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pigeonship). Odczytuje kod i etykietę statusu zamówienia, informację o doręczeniu oraz docelową godzinę (`dueDate`), jeśli jest podana, wyświetlając ją jako przewidywaną dostawę. Peek pomija nazwisko odbiorcy, współrzędne kierowcy na mapie, pasek postępu i zdjęcia z doręczenia — otwórz własny link PigeonShip, aby zobaczyć zdjęcie.

Ponieważ PigeonShip pokazuje tylko aktualny stan, Peek dopisuje wiersz z datą zauważenia każdej zmiany etapu: aktywne zamówienie `Assigned` widnieje jako **W transporcie** z przewidywaną godziną, a ukończone doręczenie jako **Dostarczona**.

Gdy masz przesyłkę otwartą w Peek albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza status nawet co 10 minut, dopóki strona śledzenia PigeonShip odpowiada. W pozostałym czasie Peek sprawdza co godzinę. Ponieważ strona PigeonShip nie posiada etapu wyjścia do doręczenia, Peek nigdy nie przełącza się na sprawdzanie co 2 minuty w dniu dostawy. Po 12 godzinach wyłącznie na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika” albo po dwóch dniach bez zmian sprawdza co godzinę niezależnie od sposobu śledzenia, a po siedmiu dniach na „Zarejestrowana” — co 6 godzin. Gdy strona PigeonShip nie odpowiada i Peek śledzi przesyłkę wyłącznie przez usługi śledzące, sprawdza co godzinę, co 3 godziny po spokojnym dniu i nawet co 15 minut w dniu doręczenia, dopóki masz ją otwartą lub włączone powiadomienia.

Jeśli najnowszy skan ma cztery dni, a przesyłka nadal widnieje jako „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie”, Peek oznacza ją dniem tego skanu, np. „Brak aktualizacji od pon., 12 paź”. Na stronie przesyłki pojawia się wtedy komunikat „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika PigeonShip.” wraz z linkiem „Poproś przewoźnika PigeonShip o pomoc” do strony kontaktowej PigeonShip. Po trzech tygodniach bez nowego skanu pojawia się informacja „Od ponad trzech tygodni nic nowego. Jeśli przesyłka wciąż nie dotarła, zapytaj sprzedawcę. Peek dalej pyta przewoźnika PigeonShip.”

W ustawieniach powiadomień Peek wybierz **Każdy skan**, **Ważne etapy** lub **Tylko doręczenie**, aby zdecydować, które aktualizacje mają wysyłać alerty.

## Kontakt z PigeonShip

- **Oficjalny formularz kontaktowy:** [PigeonShip Contact Us](https://www.pigeonship.com/contact-us) (wybierz United States, Canada lub Other i opisz sprawę doręczenia)
- **Oficjalna strona:** [pigeonship.com](https://www.pigeonship.com/)
- **Biuro w USA:** PigeonShip USA, 1675 N Freedom Blvd Ste 9B, Provo, Utah 84604-6929
- **Biuro w Kanadzie:** PigeonShip Canada, 405 Dundas St W, Oakville, ON L6M 5P9

## Najczęstsze pytania

### Dlaczego śledzenie PigeonShip nie pokazuje listy wcześniejszych skanów?

Strona PigeonShip dla odbiorcy jest wyłącznie podsumowaniem: pokazuje aktualny status zamówienia („In transit” lub „Delivered”) oraz czas docelowy zamiast wieloetapowej historii skanów magazynowych.

### Gdzie mogę obejrzeć zdjęcie z doręczenia PigeonShip?

Peek nie zapisuje ani nie wyświetla zdjęć z doręczenia od PigeonShip. Gdy paczka otrzyma status dostarczonej, otwórz oryginalny link `admin.pigeonship.com/shipping/tracking/package/...` z e-maila lub SMS-a, aby zobaczyć stronę końcową i zdjęcia PigeonShip.

### Co oznacza status `Assigned` w PigeonShip?

`Assigned` (kod statusu `16`) to wewnętrzny status zamówienia na stronie PigeonShip podczas realizacji trasy przez kierowcę; sama strona pokazuje „In transit”. Peek wyświetla go jako **W transporcie**.

### Czy PigeonShip doręcza zarówno w USA, jak i w Kanadzie?

Tak. PigeonShip realizuje dostawy tego samego dnia, następnego dnia oraz krajowe doręczenia ostatniej mili w Stanach Zjednoczonych i Kanadzie, z biurami w Provo (Utah) oraz Oakville (Ontario).

:::sources
- [PigeonShip](https://www.pigeonship.com/)
- [PigeonShip Contact Us](https://www.pigeonship.com/contact-us)
- [adapter PigeonShip w universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pigeonship)
:::
