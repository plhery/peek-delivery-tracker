---
title: Śledzenie CVC (Си Ви Си): paczka w Bułgarii
description: Śledź paczkę CVC (Си Ви Си, e-CVC) w Bułgarii w Peek: jak dodać numer i wybrać CVC, co oznaczają bułgarskie statusy i co zrobić, gdy przesyłka stoi.
slug: sledzenie-cvc
published: 2026-11-23
updated: 2026-11-23
---

CVC to bułgarski przewoźnik. Katalog Peek zna go także pod nazwami Си Ви Си, Си Ви Си ЕООД oraz e-CVC. Aby śledzić paczkę CVC, wklej link do śledzenia CVC w pole powyżej albo wklej sam numer i wybierz CVC jako przewoźnika: Peek nie rozpozna numeru CVC po samym kształcie. Numer podaje sklep lub osoba, która nadała paczkę. Własne śledzenie CVC działa pod adresem my.e-cvc.bg/track po bułgarsku: prosi o numer listu przewozowego i ma jeden przycisk, „Провери” (Sprawdź).

Strony CVC niewiele więcej zawierają: my.e-cvc.bg udostępnia wyłącznie tę wyszukiwarkę i ekran logowania, bez publicznych stron z zasadami doręczeń, cennikiem ani kontaktami. Dlatego niniejsza strona opisuje wymagania śledzenia CVC oraz sposób działania Peek dla przesyłek CVC, bez podawania reguł dostaw, cen czy telefonów, których CVC nie publikuje.

## Numer śledzenia CVC: jak wygląda

Wyszukiwarka CVC prosi o numer listu przewozowego (*товарителница* po bułgarsku). Pole przyjmuje wyłącznie cyfry: podczas wpisywania usuwa litery oraz spacje i dopuszcza najwyżej 20 znaków. Numer CVC składa się więc z samych cyfr (do 20), ale katalog Peek nie przypisuje CVC żadnego wzoru: Peek nie rozpozna go automatycznie jako CVC, a przy dodawaniu CVC nigdy nie pojawia się w sekcji „Pasują do numeru”. Nie podajemy tu przykładowego numeru, ponieważ śledzenie CVC nie pokazuje wzoru pełnego numeru.

| Co wklejasz | Co robi Peek |
| --- | --- |
| Sam numer CVC | Nie wskazuje CVC. Może pokazać innych przewoźników pasujących do formatu numeru albo żadnego. Wybierz CVC ręcznie zgodnie z sekcją „Śledzenie paczki CVC w Peek” niżej. |
| Link do śledzenia CVC: `my.e-cvc.bg/track?wb=` i numer | Peek wyciąga numer z adresu i natychmiast rozpoznaje CVC („Wykryty przewoźnik”). |
| Numer przypisany przez sklep innemu przewoźnikowi | Śledź go u tego przewoźnika: e-mail ze sklepu to najlepsza wskazówka, kto doręcza. |

**Gdzie go znaleźć.** W e-mailu wysyłkowym ze sklepu lub na stronie zamówienia, czasem jako numer listu przewozowego (*waybill*). W przesyłce prywatnej spytaj nadawcę. Brak numeru? Zobacz [jak znaleźć numer śledzenia](guide:find-tracking-number) oraz [formaty numerów przesyłek](guide:tracking-number-formats) dla innych przewoźników.

## Co oznaczają statusy śledzenia CVC w Peek

Peek odczytuje bezpośrednio stronę śledzenia CVC pod adresem my.e-cvc.bg/track i wyświetla każdy wpis w bułgarskim brzmieniu CVC, przypisany do polskiego etapu w Peek. Główne komunikaty bułgarskie rozpoznawane przez Peek:

| CVC podaje | Peek pokazuje | Co to oznacza dla ciebie |
| --- | --- | --- |
| `Приета за събиране` | Zarejestrowana | Przesyłka została zgłoszona do odbioru; CVC jeszcze jej nie odebrało. |
| `Отложено събиране за …` | Zarejestrowana | Odbiór przesyłki został przełożony na podany termin. |
| `Взета от куриер …` | Przyjęta przez przewoźnika | Kurier CVC odebrał paczkę. |
| `Приета в Хъб …`, `Транспорт към Хъб …` | W transporcie | Dotarła do hubu CVC lub jedzie w kierunku hubu. |
| `Приета за разнос` | W doręczeniu | Paczka wyjechała dziś na trasę doręczeń. |
| `Доставена`, `Доставена на …` | Dostarczona | Paczka została doręczona. |

Gdy system CVC nie zna numeru, odpowiada `Пратката не е открита!` (przesyłka nie została znaleziona). Jeśli bezpośrednie śledzenie CVC nie odpowiada, Peek może zapytać także usługi śledzenia takie jak ParcelsApp, Ship24 i 17TRACK i pokazać historię pierwszej z nich, która ma przesyłkę, przyporządkowaną do standardowych etapów Peek („Do odbioru”, „Próba doręczenia”, „Zwrócona nadawcy”, „Zgłoszono problem”).

Zanim pojawi się pierwszy wpis, Peek wyświetla „Sprawdzanie aktualizacji”, a potem „Oczekiwanie na przewoźnika CVC”. Jeśli dwa sprawdzenia z rzędu się nie powiodą, pozostaje „Oczekiwanie na przewoźnika CVC”, a na stronie przesyłki pojawia się „Nie udało się jeszcze połączyć z przewoźnikiem CVC. Peek próbuje dalej.” Więcej w [wyjaśnieniu statusów przesyłek](guide:tracking-statuses).

## Doręczenie CVC w Bułgarii

Strony CVC nie podają czasów dostaw, liczby prób doręczenia, adresów oddziałów, zasad pobrań ani terminów odbioru: my.e-cvc.bg zawiera wyłącznie śledzenie i logowanie. Zapytaj sklep lub nadawcę, jak i kiedy doręcza CVC – to oni zamówili dostawę. Gdy próba doręczenia się nie uda albo paczka czeka na odbiór, zobacz [nieudane doręczenie](guide:missed-delivery) i [nieodebrane przesyłki](guide:uncollected-parcel).

## Paczka CVC bez aktualizacji, zaginiona lub zwrócona

**Na razie nic.** Możliwe, że CVC jeszcze nie zarejestrowało numeru. Peek pokazuje wtedy „Oczekiwanie na przewoźnika CVC” i sprawdza dalej. Jeśli dwa dni po pierwszej takiej odpowiedzi CVC nadal nie zna numeru, Peek prosi o sprawdzenie numeru, wybór innego przewoźnika lub usunięcie paczki. Porównaj numer z e-mailem ze sklepu. Gdy po kilku dniach wciąż nic nie przychodzi, otwórz śledzenie CVC przyciskiem „Otwórz stronę przewoźnika CVC” na stronie paczki i zapytaj sklep, czy paczka faktycznie została wysłana.

**Brak wiadomości od kilku dni.** Po czterech dniach bez nowego wpisu przesyłka będąca wciąż na etapie „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie” dostaje oznaczenie „Brak aktualizacji od …” z dniem tej linii. Zobacz [dlaczego śledzenie się nie aktualizuje](guide:tracking-not-updating).

**Doręczona, ale nic nie dotarło.** Sprawdź okolice drzwi i zapytaj sąsiadów, a potem sklep. Zobacz [doręczona, ale nieotrzymana](guide:delivered-not-received).

**Zaginiona lub uszkodzona.** Najpierw napisz do sklepu: to on zlecił dostawę i może złożyć reklamację w CVC lub wysłać nową paczkę. Zobacz [zaginione paczki](guide:lost-parcel) i [uszkodzone paczki](guide:damaged-parcel).

**Zwrócona.** „Zwrócona nadawcy” oznacza, że paczka wróciła do sklepu lub nadawcy. Poproś ich o zwrot pieniędzy albo ponowną wysyłkę. Zobacz [zwrócona do nadawcy](guide:returned-to-sender).

## Kontakt i obsługa klienta CVC

Strony CVC nie podają telefonu, adresu e-mail ani biura: my.e-cvc.bg wyświetla wyłącznie śledzenie i ekran logowania. Peek publikuje jedynie kontakty odczytane z oficjalnych stron przewoźnika, więc ta strona żadnych nie podaje. Skontaktuj się najpierw ze sklepem lub nadawcą, mając pod ręką numer zamówienia i śledzenia: to oni zamówili usługę w CVC.

## Śledzenie paczki CVC w Peek

[Peek](/) obsługuje ponad 4 500 przewoźników, w tym CVC, lecz nie rozpozna paczki CVC po samym numerze. Link do śledzenia CVC wskazuje CVC od razu; z samym numerem wybierz przewoźnika ręcznie:

1. Wklej numer w pole powyżej.
2. Kliknij „Zmień” przy linii przewoźnika i wyszukaj CVC. Działają też hasła „e-CVC” i „Си Ви Си” (z podpisem „Znany też jako e-CVC” lub „Znany też jako Си Ви Си”) oraz „Bulgaria”.
3. Wybierz CVC: pojawi się podpis „Wybrano ręcznie”. Uruchom śledzenie.

Na już dodanej paczce użyj „Zmień przewoźnika”. Dla CVC Peek akceptuje wyłącznie od 1 do 20 cyfr; dla innego ciągu zgłasza, że CVC „nie wydaje takich numerów”. Jeśli Peek wymienia inne firmy w „Pasują do numeru”, pasują one tylko do formatu numeru. Przed wyborem może pojawić się „jeszcze nie znaleziono · Peek sprawdza dalej”, co jest normalne dla nowego numeru.

Jeśli zostawisz wybór Peek, zapyta on usługi śledzenia: gdy pokażą paczkę w ruchu i wskażą CVC jako przewoźnika, Peek sam przypisze ją do CVC.

**Co widzisz.** Status oraz każdy wpis z historii wraz z datą i godziną według czasu lokalnego Bułgarii. CVC nie zwraca miejscowości skanu, przewidywanej daty dostawy, punktu odbioru ani wagi. „Otwórz stronę przewoźnika CVC” otwiera numer bezpośrednio na bułgarskiej stronie śledzenia CVC.

**Inny przewoźnik.** Jeśli śledzenie CVC nie zna numeru, w ciągu pierwszych 30 dni Peek sprawdza również przewoźników, których format pasuje do numeru. Jeśli któryś z nich wykaże paczkę w drodze pod tym numerem, Peek przenosi ją tam z adnotacją „Automatycznie zmieniono przewoźnika (wcześniej: CVC)”.

**Jak często.** Gdy paczka jest otwarta w Peek albo, przy włączonych powiadomieniach, w dniu, w którym CVC zeskanuje ją jako `Приета за разнос`, Peek sprawdza ją nawet co 10 minut, a tego dnia od 6:00 czasu bułgarskiego co 2 minuty. Po dwóch dniach bez nowości (12 godzinach przy „Zarejestrowana” lub „Przyjęta przez przewoźnika”) sprawdza co godzinę, a po siedmiu dniach bez nowości przy „Zarejestrowana” co 6 godzin. Powiadomienia mogą obejmować „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”.

Peek nie może skontaktować się za ciebie z CVC, sklepem ani nadawcą, zmienić dostawy ani złożyć reklamacji.

## Pytania o śledzenie CVC

### Jak śledzić paczkę CVC?

Wklej link do śledzenia CVC w pole powyżej, a Peek sam wybierze CVC. Z samym numerem kliknij „Zmień” i wybierz CVC, bo Peek nie rozpoznaje jego kształtu automatycznie. Na dodanej już paczce użyj „Zmień przewoźnika”. Oficjalne śledzenie CVC znajduje się na my.e-cvc.bg/track: wpisz numer listu przewozowego i kliknij „Провери”.

### Jak wygląda numer śledzenia CVC?

To numer listu przewozowego (*товарителница*) złożony wyłącznie z cyfr: strona CVC usuwa litery oraz spacje i przyjmuje najwyżej 20 cyfr. Katalog Peek nie przypisuje mu własnego wzoru, więc wybierz CVC ręcznie.

### Czy e-CVC to to samo co CVC?

Tak. Śledzenie CVC na my.e-cvc.bg nazywa się e-CVC.bg i ma logo CVC, a katalog Peek wymienia e-CVC, Си Ви Си oraz Си Ви Си ЕООД jako inne nazwy CVC. Wpisanie dowolnej z nich na liście przewoźników Peek znajduje CVC.

### Dlaczego moja paczka CVC pokazuje „Oczekiwanie na przewoźnika CVC”?

Na peektracker.com paczka przypisana do CVC pokazuje taki status, dopóki śledzenie CVC (lub usługa śledzenia) nie ma jeszcze żadnego wpisu dla tego numeru albo nie udało się z nim połączyć: strona informuje wówczas „Nie udało się jeszcze połączyć z przewoźnikiem CVC. Peek próbuje dalej.” Potwierdź numer w sklepie i sprawdź go przyciskiem „Otwórz stronę przewoźnika CVC”.

### Jaki jest numer telefonu do CVC?

Ta strona żadnego nie podaje, ponieważ strony CVC nie publikują numeru telefonu. Zapytaj sklep, który wysłał przesyłkę: to on zamówił dostawę w CVC.

:::sources
- [CVC: śledzenie e-CVC.bg](https://my.e-cvc.bg/track) – śledzenie po bułgarsku, „Потърсете товарителница”, same cyfry, maksymalnie 20 znaków, „Провери”, numer po `wb=`, nazwa e-CVC.bg, logo CVC
- [CVC: logowanie e-CVC.bg](https://my.e-cvc.bg/) – wyłącznie strona logowania, bez stron o dostawie, cenach i kontakcie
- [Universal Parcel Scraper: CVC](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/cvc) – nazwy i aliasy, Bułgaria, adres śledzenia, reguła linku, brak wzoru numeru, bezpośredni odczyt my.e-cvc.bg/track i bułgarskie statusy
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – zapasowe usługi śledzenia, dopasowanie nazwy przewoźnika do CVC, link do strony przewoźnika
:::
