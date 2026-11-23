---
title: Śledzenie Andreani: numery i statusy w Argentynie
description: Śledź paczkę Andreani w Argentynie: jak wyglądają 15-cyfrowe numery, co oznaczają hiszpańskie statusy i co zrobić, gdy przesyłka stoi.
slug: sledzenie-andreani
published: 2026-11-23
updated: 2026-11-23
---

Andreani to firma kurierska w Argentynie. Katalog Peek wymienia ją w Argentynie i Brazylii, także jako Correo Andreani i Grupo Logístico Andreani. Jeśli kupiłeś coś w internecie, sklep może wysłać paczkę przez Andreani: numer śledzenia znajdziesz w e-mailu wysyłkowym sklepu lub na stronie zamówienia. Wklej numer albo link śledzenia z andreani.com w pole powyżej. Własna wyszukiwarka Andreani działa na andreani.com; Peek odczytuje ten serwis, a ta strona wyjaśnia każdy etap po polsku.

## Numer śledzenia Andreani: jak wygląda

Numery Andreani, które rozpoznaje Peek, mają 15 cyfr i zaczynają się od `36000`, `31000` lub `30000`. Na wymyślonych cyfrach:

| Numer (wymyślony) | Co to jest |
| --- | --- |
| `360004829103756` | `36000` i 10 kolejnych cyfr: zakres Andreani, ale tylko słabe dopasowanie. [BRT](carrier:brt) i Yunda również używają 15 cyfr, więc Peek pyta zarówno Andreani, jak i BRT. |
| `310004829103756` | `31000` i 10 kolejnych cyfr: również zakres Andreani. |
| `300004829103756` | `30000` i 10 kolejnych cyfr: również zakres Andreani. |
| `739402815634281` | 15 cyfr z innym początkiem. Peek nie wiąże go z Andreani: [FedEx](carrier:fedex), DPD Francja, [Hermes](carrier:hermes-de), BRT i Yunda też stosują 15 cyfr. |

Spacje nie przeszkadzają przy wklejaniu numeru do Peek.

**Inne referencje.** Śledzenie Andreani przyjmuje także inne referencje: numery Andreani z literami oraz własne numery nadawców. Po samym kształcie Peek nie wie, że należą do Andreani, więc wybierz Andreani ręcznie (zobacz „Śledzenie paczki Andreani w Peek” niżej).

**Link śledzenia.** Link z andreani.com zawierający `/envio/` i numer również działa: Peek sam wyciąga numer z adresu.

Nie możesz znaleźć numeru? Zobacz [jak znaleźć numer śledzenia](guide:find-tracking-number).

## Wyjaśnienie statusów śledzenia Andreani

Śledzenie Andreani grupuje wpisy pod nagłówkami po hiszpańsku. Cztery nagłówki znane Peek to `Pendiente de ingreso` (oczekuje na przyjęcie), `Ingresado` (przyjęta), `En camino` (w drodze) i `Entregado` (doręczona). Każdy wpis to krótkie zdanie skierowane do odbiorcy, z nazwą oddziału (`sucursal`) i miejscowością, jeśli zostały podane. Wpisy znane Peek, w typowej kolejności:

| Andreani podaje | Co to oznacza dla ciebie | Status w Peek |
| --- | --- | --- |
| `Tu envío se encuentra pendiente de ingreso.` | Nadawca zgłosił przesyłkę do Andreani, ale Andreani jeszcze jej nie ma. | „Zarejestrowana” |
| `¡Ya tenemos tu envío! El circuito comienza en C.a.b.a., Buenos Aires. Pronto lo enviaremos a la sucursal encargada de la entrega.` | Andreani ma już twoją paczkę. Trasa zaczyna się we wskazanym mieście i prowincji, a następnie paczka ruszy do oddziału odpowiedzialnego za doręczenie. | „Przyjęta przez przewoźnika” |
| `Tu envío salió de la sucursal Monserrat (av Belgrano), en la localidad C.a.b.a. de la provincia Buenos Aires.` | Opuściła wskazany oddział w tym mieście i prowincji. | „W transporcie” |
| `Tu envío se encuentra en camino a la sucursal La Plata (calle 2).` | Jest w drodze do wskazanego oddziału, który ją doręczy. | „W transporcie” |
| `La sucursal La Plata (calle 2) ya está procesando tu envío. ¡Pronto tendrás novedades!` | Oddział doręczający ma już paczkę i ją opracowuje. Wkrótce pojawią się nowe informacje. | „W transporcie” |
| `Hoy te visitaremos para entregarte tu envío.` | Andreani przyjedzie dziś doręczyć paczkę. | „W doręczeniu” |
| `Ya entregamos tu envío. ¡Esperamos volver a verte pronto!` | Doręczona. | „Dostarczona” |

Oddziały i miasta w tabeli to tylko przykłady: w twojej historii zobaczysz punkty z własnej trasy. `C.a.b.a.` oznacza miasto Buenos Aires (*Ciudad Autónoma de Buenos Aires*).

Peek pokazuje każdy wpis w oryginalnych hiszpańskich słowach Andreani, pod polskim etapem Peek. Wpis, którego Peek jeszcze nie zna, przejmuje etap nagłówka: każdy wpis pod `En camino` wyświetla się jako „W transporcie”. Peek usuwa z treści wpisów numery telefonów i dokumentów tożsamości. Więcej w poradniku [co oznaczają statusy przesyłek](guide:tracking-statuses).

## Jak Andreani doręcza w Argentynie

Powyższe wpisy oddają typowy przebieg:

1. **Przyjęcie.** Andreani odbiera paczkę w miejscu rozpoczęcia trasy.
2. **Do oddziału doręczającego.** Przesyła ją do oddziału odpowiedzialnego za dostawę (`sucursal encargada de la entrega`), wskazanego w wierszu od `Tu envío se encuentra en camino a la sucursal`.
3. **W oddziale doręczającym.** Informacja, że ten oddział `ya está procesando tu envío`, oznacza, że paczka już tam dotarła.
4. **Dzień dostawy.** `Hoy te visitaremos para entregarte tu envío.` oznacza dzisiejszą próbę doręczenia pod adres.
5. **Doręczenie.** `Ya entregamos tu envío.` kończy podróż.

Peek nie mógł odczytać strony internetowej Andreani, więc ta strona nie podaje czasów dostawy, zasad przy nieobecności ani reguł i czasu odbioru w oddziale. Zapytaj sklep lub Andreani. Zobacz też [nieudane doręczenia](guide:missed-delivery) oraz [nieodebrane przesyłki](guide:uncollected-parcel).

## Śledzenie Andreani nie aktualizuje się, paczka zaginęła lub wróciła

**Tylko `Pendiente de ingreso`.** Nadawca utworzył przesyłkę, lecz nie przekazał jeszcze paczki do Andreani. Peek pokazuje „Zarejestrowana”. Zapytaj sklep, czy zamówienie zostało już wysłane.

**Brak wyników.** Wyszukiwarka Andreani może jeszcze nie znać numeru. Zapisana pod Andreani paczka pokazuje „Oczekiwanie na przewoźnika Andreani”, a Peek pyta dalej. Jeśli po kilku dniach nadal nic nie ma, potwierdź numer w sklepie. Zobacz [dlaczego śledzenie się nie aktualizuje](guide:tracking-not-updating).

**Cisza od kilku dni.** Gdy najnowszy wpis ma co najmniej cztery dni, a paczka nadal ma status „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie”, Peek oznacza ją: „Brak aktualizacji od czterech dni”. Przez weekend często nie ma nowych skanów. Jeśli nic się nie zmienia, zapytaj sklep.

**Doręczona, ale nic nie dotarło.** Ostatni wpis to `Ya entregamos tu envío. ¡Esperamos volver a verte pronto!`, lecz nie masz paczki. Zapytaj domowników, sąsiadów i obsługę budynku, a potem sklep. Zobacz [doręczona, ale nieotrzymana](guide:delivered-not-received).

**Zaginiona lub uszkodzona.** Najpierw napisz do sklepu: to on zlecił dostawę w Andreani. Zobacz [zaginione paczki](guide:lost-parcel) i [uszkodzone paczki](guide:damaged-parcel).

**Zwrócona nadawcy.** Tabela wpisów Andreani w Peek nie ma jeszcze osobnego wiersza zwrotu. Jeśli paczka wraca do nadawcy, wpis i tak pojawi się w słowach Andreani. Poproś sklep o zwrot pieniędzy lub ponowną wysyłkę. Zobacz [zwrócona do nadawcy](guide:returned-to-sender).

**Sklep nie pomaga.** Jeśli firma, w której kupiłeś, nie wywiązuje się ze zobowiązań, możesz złożyć skargę w *Defensa del Consumidor*, argentyńskim urzędzie ochrony konsumentów. Jest to bezpłatne i online przez *Ventanilla Federal Única de Defensa del Consumidor*. Potrzebujesz DNI lub paszportu, numeru telefonu albo adresu e-mail do odpowiedzi, nazwy i miasta firmy oraz podstawowych danych zakupu. *Defensa del Consumidor* przekazuje sprawę do właściwego biura i wysyła numer skargi e-mailem.

## Obsługa klienta i kontakt z Andreani

Ponieważ Peek nie mógł odczytać strony internetowej Andreani, nie podajemy tu numeru telefonu, e-maila ani godzin pracy: Peek publikuje wyłącznie kontakty odczytane z oficjalnych stron. Adres strony Andreani to andreani.com.

Przy zakupach online zapytaj najpierw sklep: to on zamówił dostawę i ma szczegóły przesyłki.

## Śledzenie paczki Andreani w Peek

Po samych 15 cyfrach [Peek](/) nie rozpoznaje jednoznacznie Andreani. Wklej numer mimo to: Peek obsługuje ponad 4 500 przewoźników.

**Numer zaczynający się od `36000`, `31000` lub `30000`.** Peek pyta równocześnie Andreani i BRT, wyświetlając „Sprawdzanie usług śledzenia…”. Jeśli Andreani ma paczkę, wiersz pod numerem wskazuje Andreani z dopiskiem „ma tę przesyłkę” i Peek przypisuje ją do Andreani. Jeśli nie, pojawia się „jeszcze nie znaleziono · Peek sprawdza dalej”. Dodaj przesyłkę mimo to: wyświetli „Prawdopodobnie Andreani” i „Oczekiwanie na pierwszy skan przewoźnika Andreani”, a Peek pyta Andreani co 6 godzin po pierwszych kilku godzinach w aplikacji na iPhone’a lub Androida albo na koncie. Przy wyszukiwaniu bez konta Peek sprawdza przez pierwsze 6 godzin, potem przy otwarciu linku (najwyżej co 6 godzin, a po dwóch dniach najwyżej raz dziennie) oraz przy włączonych powiadomieniach raz dziennie do 30 dni od wyszukiwania. Po tygodniu bez wyniku sugeruje sprawdzenie numeru u nadawcy lub wybór innego przewoźnika.

**Link andreani.com.** Wklej link śledzenia zawierający `/envio/`. Peek od razu wskazuje Andreani („Wykryty przewoźnik”) i śledzi numer z linku, także referencje z literami.

**Każdy inny numer.** Wybierz Andreani ręcznie: kliknij „Zmień”, a następnie znajdź Andreani w sekcji „Wszyscy przewoźnicy” lub przez wyszukiwarkę. Dla numerów zaczynających się od `36000`, `31000` lub `30000` pojawia się również w „Pasują do numeru”. Na dodanej już paczce użyj „Zmień przewoźnika”. Dla Andreani Peek przyjmuje od 6 do 30 liter i cyfr z co najmniej jedną cyfrą; dla innych ciągów informuje, że Andreani „nie wydaje takich numerów”.

**Co pokazuje Peek.** Peek odczytuje bezpośrednio śledzenie Andreani — to samo zapytanie co strona Andreani. Dla każdego zdarzenia pokazuje hiszpański opis Andreani, etap (tabela wyżej), oddział i godzinę. Czasy Andreani podawane są w czasie Argentyny, który ma jedną strefę czasową bez czasu letniego; Peek przelicza je na strefę twojego urządzenia. Strona Andreani pokazuje też nazwę nadawcy, imię kierowcy, uwagi, pozycję na mapie, pasek postępu i szacowany termin doręczenia: Peek pomija te pola i dlatego nie wyświetla przewidywanej daty dostawy.

**Gdy Andreani nie odpowiada.** Peek pyta wtedy po kolei usługi śledzenia — ParcelsApp, Ship24 i 17TRACK — i pokazuje ich wpisy w ich własnym brzmieniu. Peek nie zna przekazań z Andreani do innego przewoźnika, więc śledzi paczkę w Andreani do końca.

**Sprawdzanie i powiadomienia.** Peek sprawdza paczkę nawet co 10 minut, a co 2 minuty od 6:00 czasu lokalnego w dniu, gdy Andreani zeskanuje ją w doręczeniu. Przesyłka bez nowych zdarzeń przez dwa dni, albo przez 12 godzin ze statusem „Zarejestrowana” lub „Przyjęta przez przewoźnika”, sprawdzana jest co godzinę. Możesz ustawić powiadomienia: „Wszystkie aktualizacje”, „Tylko ważne” lub „Tylko w dniu dostawy”. Peek nie może skontaktować się za ciebie z Andreani ani ze sklepem.

## Pytania o śledzenie Andreani

### Jak wygląda numer śledzenia Andreani?

15 cyfr zaczynających się od `36000`, `31000` lub `30000`, na przykład `360004829103756`. Śledzenie Andreani przyjmuje także inne referencje. Wklej numer lub link z andreani.com w Peek.

### Co oznacza „Pendiente de ingreso” w śledzeniu Andreani?

Oczekuje na przyjęcie: nadawca zgłosił przesyłkę, ale Andreani jeszcze jej nie odebrał. Peek pokazuje „Zarejestrowana”. Jeśli trwa to kilka dni, zapytaj sklep, czy nadał paczkę.

### Co oznacza „Hoy te visitaremos para entregarte tu envío.”?

Andreani przyjedzie dziś doręczyć twoją przesyłkę. Peek pokazuje „W doręczeniu” i sprawdza częściej, aż pojawi się wpis o doręczeniu.

### Dlaczego moje śledzenie Andreani się nie aktualizuje?

Sklep mógł jeszcze nie przekazać paczki albo Andreani nie dodał nowego kroku, co często zdarza się w weekend. Peek dalej pyta Andreani i oznacza paczkę po czterech dniach bez zmian; wtedy zapytaj sklep.

### Jak śledzić paczkę Andreani po polsku?

Wklej numer w Peek. Opisy Andreani pozostają po hiszpańsku, ale Peek przypisuje każdy z nich do polskiego etapu, a tabela powyżej tłumaczy komunikaty.

:::sources
- [Universal Parcel Scraper: Andreani](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/andreani) – nazwy i kraje, 15-cyfrowy format, inne referencje, linki andreani.com, zapytanie do śledzenia, hiszpańskie opisy i nagłówki wraz z etapami, usuwanie numerów telefonów i dokumentów, czas Argentyny, co pokazuje śledzenie i co pomija Peek, brak daty dostawy
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – 15-cyfrowe formaty innych przewoźników, których przewoźników pyta Peek, usługi śledzenia, brak przekazania dla Andreani
- [Argentina.gob.ar: Iniciar un reclamo ante Defensa del Consumidor](https://www.argentina.gob.ar/servicio/iniciar-un-reclamo-ante-defensa-del-consumidor) – skargi na firmę niedotrzymującą warunków, Ventanilla Federal Única, bezpłatnie i online, wymagane dane, numer skargi e-mailem
:::
