---
title: Śledzenie Packlink: numer referencyjny, statusy i kontakt
description: Śledź paczkę Packlink: format ES…COM, znaczenie statusów, nieudane doręczenia, reklamacje za uszkodzenia i jak skontaktować się z Packlink.
slug: sledzenie-packlink
published: 2026-11-23
updated: 2026-11-23
---

Packlink nie jest firmą kurierską. To platforma rezerwacyjna z siedzibą w Madrycie, która określa się jako porównywarka wysyłek: osoba prywatna lub sklep porównuje na niej przewoźników takich jak SEUR, Correos, Correos Express czy UPS, opłaca przesyłkę, drukuje etykietę, a wybrany przewoźnik ją odbiera i doręcza. Sklepy korzystają z Packlink PRO, wersji biznesowej zintegrowanej ze sklepami internetowymi i platformami takimi jak eBay czy Amazon. Aby śledzić paczkę, wpisz numer referencyjny Packlink lub numer przewoźnika na [stronie śledzenia Packlink](https://www.packlink.es/seguimiento-envios/) albo w polu powyżej.

## Numer przesyłki Packlink: jak wygląda

Packlink nadaje każdej wysyłce własny numer referencyjny: dwie litery rynku, rok, `COM` lub `PRO`, a potem dziesięć cyfr. Hiszpańska strona Packlink podaje, że jej numery zaczynają się od ES. Przewoźnik nadaje przesyłce również własny numer, a wyszukiwarka Packlink przyjmuje oba. Z zmyślonymi cyframi:

| Numer (zmyślony) | Co to jest |
| --- | --- |
| `ES2026COM4829173650` | ES, rok, COM i 10 cyfr: numer referencyjny Packlink. |
| `ES2026PRO6194028375` | To samo z PRO: również numer referencyjny Packlink. |
| `48291736405829` | 14 cyfr: jeden z formatów SEUR, współdzielony przez DPD i innych przewoźników. |
| `1Z9X482W0348291737` | 1Z i 16 liter lub cyfr: numer UPS. |

**Gdzie go znaleźć.** Packlink wysyła numer referencyjny w e-mailu potwierdzającym zamówienie wysyłki. Jeśli ktoś sprzedał ci przedmiot i wysłał go przez Packlink, ma ten e-mail: poproś go o numer referencyjny lub numer przewoźnika. Więcej wskazówek: [jak znaleźć numer śledzenia](guide:find-tracking-number) oraz [zakupy z drugiej ręki](guide:second-hand-orders).

## Wyjaśnienie statusów śledzenia Packlink

Packlink odczytuje każdy skan od przewoźnika i przypisuje go do własnego kodu statusu z angielskim opisem na stronie śledzenia. Wiele wpisów zachowuje też słowa przewoźnika, na przykład hiszpańskie wersaliki z SEUR lub Correos Express. Częste wiersze, w kolejności:

| Wiersz śledzenia | Co to oznacza dla ciebie |
| --- | --- |
| `Waiting for carrier to collect the order` | Nadawca zarezerwował wysyłkę i ma etykietę. Przewoźnik jeszcze nie ma paczki. |
| `SIN RECEPCION` | To samo w Correos Express: jest zgłoszenie, nie ma jeszcze paczki. |
| `RECIBIDO EN ORIGEN` | Pierwszy oddział SEUR ma paczkę. Packlink nadal jednak klasyfikuje ją jako oczekującą na odbiór. |
| `Shipment accepted by carrier (scanned)` | Przewoźnik zeskanował paczkę. Jest już w drodze. |
| `EN ARRASTRE` | Przewóz między oddziałami przewoźnika. |
| `DELEGACION DESTINO` | W oddziale doręczającym w twoim rejonie. |
| `TU ENVÍO ESTÁ PENDIENTE DE LLEGAR A DESTINO` | SEUR: jeszcze nie dotarła do lokalnego oddziału. Packlink oznacza to jako problem z doręczeniem, ale paczka po prostu jedzie dalej. |
| `EN REPARTO` | W doręczeniu. |
| `REPARTO FALLIDO` | Nieudana próba doręczenia. |
| `Package at pick-up point and ready for collection` | Czeka na ciebie w punkcie odbioru. Packlink zalicza to do kodów doręczenia, ale wciąż musisz ją odebrać. |
| `ENTREGADO` | Doręczona. |
| `Package returned to sender` | Paczka wróciła do nadawcy. |

Inne komunikaty podano wprost po angielsku: `Recipient not present at indicated address` i `Unsuccessful delivery and final attempt` to nieudane doręczenia, a `Packet delivered to customs for clearance` to odprawa celna dla paczek przekraczających granicę celną.

Dwa komunikaty nie mówią nic o samej paczce. `Pending, there is no information available on the carrier’s page or the tracking number has not yet been activated` oznacza, że Packlink nie znalazł jeszcze danych u przewoźnika. `Expired, there is no tracking information for the last 30 days` oznacza brak nowych skanów od przewoźnika przez miesiąc, po którym Packlink przestaje ją śledzić.

## Kto doręcza paczkę Packlink

Przewoźnik wybrany przez nadawcę. Strona śledzenia Packlink wymienia między innymi [SEUR](carrier:seur), [Correos](carrier:correos-spain), [Correos Express](carrier:correos-express), [UPS](carrier:ups), GLS, DHL, [TNT](carrier:tnt), [InPost](carrier:inpost), [Mondial Relay](carrier:mondial-relay), [NACEX](carrier:nacex) i Zeleris. Regulamin Packlink przewiduje:

- **Adres lub punkt odbioru.** Nadawca wybiera podczas rezerwacji, czy paczka trafi pod adres czy do punktu odbioru.
- **Nieudane doręczenie.** Przewoźnik może zostawić awizo pozwalające umówić nową dostawę i zabiera paczkę z powrotem do oddziału. Nie każdy przewoźnik zostawia awizo, więc Packlink prosi nadawcę o pilnowanie śledzenia i poinformowanie odbiorcy.
- **Próby doręczenia.** Zwykle w cenie jest pierwsza próba i jedna kolejna bezpłatna. Każda dodatkowa kosztuje nadawcę od 2 € do 15 € plus VAT zależnie od przewoźnika i pojazdu. W części krajów przewoźnik może zostawić paczkę u sąsiada lub w punkcie odbioru.
- **Odbiór.** Paczkę w oddziale przewoźnika trzeba odebrać w jego terminie. Potem nadawca może ponieść koszty magazynowania, zwrotu lub zniszczenia.
- **Odmowa lub brak możliwości doręczenia.** Packlink powiadamia nadawcę w śledzeniu lub e-mailem. Nadawca ma 2 dni robocze na decyzję o zwrocie albo wysyłce pod inny adres.

Zobacz też [nieudane doręczenie](guide:missed-delivery) i [nieodebrane paczki](guide:uncollected-parcel).

**Cło.** Jeśli paczka pochodzi spoza UE i zawiera towary kupione online, od 1 lipca 2026 roku zamówienia do 150 € objęte są unijnym cłem 3 € za rodzaj produktu. Od 1 listopada 2026 roku dochodzi unijna opłata manipulacyjna 2 € za rodzaj produktu niezależnie od wartości. Regulamin Packlink stanowi, że cła i podatki importowe pokrywa odbiorca, ale nie określa, jak te dwie opłaty są pobierane. Zobacz [paczki zatrzymane na cle](guide:customs).

## Paczka Packlink nie dociera, jest uszkodzona lub zwrócona

**Kto może działać.** Regulamin Packlink zastrzega, że reklamacje i zapytania może składać wyłącznie osoba, która zarezerwowała i opłaciła przesyłkę, z adresu e-mail użytego przy rezerwacji. Osoba trzecia uzyskuje dostęp do reklamacji tylko na podstawie pisemnego oświadczenia nadawcy. Jako kupujący musisz więc poprosić sprzedawcę o kontakt z Packlink.

**Brak aktualizacji.** Dopóki widać `Waiting for carrier to collect the order`, przewoźnik nie odebrał paczki: zapytaj nadawcę, czy ją przekazał. W razie opóźnienia lub braku nowych wpisów z Packlink kontaktuje się nadawca. Więcej w [dlaczego śledzenie przestaje się aktualizować](guide:tracking-not-updating).

**Zaginiona.** Packlink rozpatruje reklamację z tytułu zaginięcia dopiero wtedy, gdy przewoźnik przeszuka swoje oddziały i uzna paczkę za zaginioną. Poszukiwania trwają zwykle do 30 dni od planowanego terminu doręczenia. Zobacz [zaginione paczki](guide:lost-parcel).

**Uszkodzona.** Jeśli przy odbiorze widać uszkodzenia, wpisz *Con Daños* (uszkodzona) na potwierdzeniu odbioru lub urządzeniu kuriera, jak wymaga Packlink. Bez tej adnotacji Packlink wskazuje, że nie może należycie rozpatrzyć reklamacji. Zachowaj przedmiot i całe opakowanie do inspekcji: Packlink odrzuca reklamację, jeśli przedmiot naruszono lub naprawiono albo wyrzucono opakowanie. Przy zaginięciu lub ukrytych szkodach nadawca musi zgłosić reklamację w ciągu 7 dni w transporcie drogowym (w Hiszpanii i za granicą), 10 dni w transporcie lotniczym krajowym i 14 dni w lotniczym międzynarodowym, licząc od dnia po doręczeniu. Zobacz [uszkodzone paczki](guide:damaged-parcel).

**Zwrócona.** `Package returned to sender` oznacza powrót paczki do nadawcy. Poproś go o ponowną wysyłkę lub zwrot pieniędzy. Zobacz [zwrot do nadawcy](guide:returned-to-sender).

**Doręczona, ale jej nie ma.** Zapytaj sąsiadów i sprawdź w punkcie odbioru, a potem natychmiast poinformuj nadawcę. Zobacz [doręczona, ale nie otrzymana](guide:delivered-not-received).

## Telefon i kontakt z Packlink

Kanały publikowane przez Packlink:

- **Telefon:** brak. Packlink informuje, że działa wyłącznie online i nie prowadzi infolinii. Ostrzega, że numery telefonów przypisywane Packlink w innych serwisach nie są prawdziwe ani zweryfikowane przez Packlink — nie dzwoń pod nie.
- **Formularz kontaktowy:** w centrum pomocy Packlink, dostępnym ze strony poświęconej telefonowi. Wybierz właściwą kategorię, a dla istniejącej wysyłki przygotuj numer referencyjny z e-maila potwierdzającego. Packlink udostępnia również czat na żywo z obsługą klienta.
- **Reklamacje:** przez to samo centrum pomocy, wyłącznie przez osobę, która zamówiła wysyłkę.

## Śledzenie paczki Packlink w Peek

Wklej numer referencyjny Packlink do [Peek](/). Ten format należy wyłącznie do Packlink, więc ekran dodawania od razu pokazuje Packlink jako „Wykryty przewoźnik”. Możesz też wkleić własny numer przewoźnika: Peek śledzi ponad 4 500 przewoźników. Jeśli format pasuje do kilku przewoźników — jak 14 cyfr SEUR — Peek wyświetla „Sprawdzanie usług śledzenia…”, pytając ich po kolei; kliknij „Zmień”, aby wskazać przewoźnika ręcznie.

Dopóki masz paczkę otwartą w Peek albo w dniu doręczenia przy włączonych powiadomieniach, Peek odpytuje bezpośrednio Packlink nawet co 10 minut, a w dniu wydania do doręczenia od 6:00 czasu madryckiego nawet co 2 minuty, dopóki działa śledzenie Packlink. Po dwóch dniach bez nowych wpisów (po 12 godzinach, gdy paczka nadal ma status „Zarejestrowana” lub „Przyjęta przez przewoźnika”) sprawdza co godzinę; po siedmiu dniach bez nowych wpisów w statusie „Zarejestrowana” — co 6 godzin. Po czterech dniach bez skanu dla paczki w statusie „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie” Peek wyświetla „Brak aktualizacji od …”, informację „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika Packlink.” oraz link „Poproś przewoźnika Packlink o pomoc”. Pokazuje każdy skan w brzmieniu przewoźnika, gdy Packlink je podaje, albo w angielskim opisie Packlink, z czasem odczytanym w czasie madryckim i przeliczonym na strefę czasową twojego urządzenia. Przy wpisie o punkcie odbioru status w Peek to „Do odbioru”; przy `RECIBIDO EN ORIGEN` — „Przyjęta przez przewoźnika”. Przewidywany dzień doręczenia z Packlink pojawia się w trakcie transportu, a wysyłka wielopaczkowa wyświetla się jako jedna historia. Peek pomija miejsca — nie ma więc mapy — oraz liczbę paczek. Śledzenie Packlink nie podaje wagi, nadawcy ani odbiorcy.

Dane z Packlink zawierają też własny numer przewoźnika. Gdy jego format jednoznacznie wskazuje przewoźnika — jak numer UPS zaczynający się od 1Z — Peek pyta również tego przewoźnika. Kiedy pokaże skan co najmniej tak świeży jak najnowszy w Packlink, Peek śledzi paczkę bezpośrednio tam, zachowując historię z Packlink: wyświetla wtedy „Doręcza: UPS” i oba numery w szczegółach. Żaden format SEUR ani Correos Express nie jest tak jednoznaczny, więc ich paczki pozostają w śledzeniu Packlink.

Peek może powiadamiać o opcjach „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”, ale nie złoży reklamacji, nie zmieni doręczenia ani nie skontaktuje się z nikim za ciebie.

## Pytania o śledzenie Packlink

### Czy Packlink to firma kurierska?

Nie. Packlink to platforma, na której osoby prywatne i sklepy porównują i zamawiają przewoźników. Wybrany przewoźnik — na przykład SEUR, Correos Express lub UPS — odbiera i doręcza paczkę.

### Jaki jest numer telefonu do Packlink?

Brak: Packlink działa wyłącznie online i ostrzega, że numery podawane na innych stronach nie są przez niego zweryfikowane. Skorzystaj z formularza w centrum pomocy albo czatu na żywo.

### Gdzie znajdę mój numer śledzenia Packlink?

W e-mailu potwierdzającym wysłanym przez Packlink po zarezerwowaniu wysyłki: dwie litery takie jak ES, rok, COM lub PRO i dziesięć cyfr. Jeśli jesteś kupującym, poproś o niego nadawcę.

### Dlaczego moje śledzenie Packlink pokazuje Expired?

`Expired, there is no tracking information for the last 30 days` oznacza, że przewoźnik przez miesiąc nie przekazał żadnych informacji i Packlink przestał śledzić przesyłkę. Nie oznacza to, że paczka zaginęła. Poproś nadawcę o kontakt z Packlink.

:::sources
- [Packlink: Seguimiento envios](https://www.packlink.es/seguimiento-envios/) – śledzenie po numerze referencyjnym Packlink lub numerze przewoźnika, numery zaczynające się od ES, wymienieni przewoźnicy, Packlink PRO
- [Packlink: ¿Y cómo funciona esto de Packlink?](https://www.packlink.es/como-funciona/) – porównywanie i zamawianie przewoźników, etykiety, integracje Packlink PRO z eBay i Amazonem
- [Packlink: Teléfono Packlink](https://www.packlink.es/telefono-packlink/) – porównywarka, brak obsługi telefonicznej, niezweryfikowane numery na innych stronach, formularz kontaktowy, czat na żywo, numer referencyjny w potwierdzeniu e-mail
- [Packlink: Términos y Condiciones](https://www.packlink.es/terminos-condiciones/) – siedziba w Madrycie, reklamację składa tylko zamawiający, doręczenie pod adres lub do punktu, awiza, próby i koszt, termin odbioru, odmowa przyjęcia, opłaty importowe, terminy reklamacji, zastrzeżenia przy szkodach, 30-dniowe poszukiwania
- [Komisja Europejska: reforma celna UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – cło 3 € za rodzaj produktu do 150 € od 1 lipca 2026, unijna opłata manipulacyjna 2 € od 1 listopada 2026
- [Komisja Europejska: wytyczne w sprawie cła 3 € i unijnej opłaty manipulacyjnej (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € za rodzaj produktu niezależnie od wartości dla sprzedaży na odległość
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – format numeru referencyjnego, formaty przewoźników, treści i kody statusów, czas madrycki, co Peek odczytuje z Packlink, przekazanie przewoźnikowi
:::
