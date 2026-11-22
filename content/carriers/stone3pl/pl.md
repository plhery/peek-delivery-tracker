---
title: Śledzenie Stone3PL: numery, statusy i kontakt
description: Śledź przesyłkę Stone3PL z Chin: jak wygląda numer z literą S, co oznacza każdy wpis śledzenia, kto doręcza paczkę w twoim kraju i jak się skontaktować.
slug: sledzenie-stone3pl
published: 2026-11-22
updated: 2026-11-22
---

Stone3PL to firma logistyczna z siedzibą w Shenzhen w Chinach, należąca do Stone Group. Wysyła towary dla sprzedawców internetowych i firm z Chin na cały świat we współpracy z innymi operatorami logistycznymi. Jeśli sklep nadał zamówienie przez Stone3PL, sprawdź numer na [stronie śledzenia Stone3PL](https://stone3pl.com/?route=services/track) (po angielsku) albo wklej go w polu powyżej. Numer otrzymujesz od sprzedawcy w danych wysyłkowych zamówienia.

## Numer śledzenia Stone3PL: jak wygląda

Stone3PL nie publikuje oficjalnego formatu numerów. Peek wiąże ze Stone3PL jeden układ: literę `S` i 18 cyfr. Oba poniższe przykłady są zmyślone:

| Numer (zmyślony) | Co to jest |
| --- | --- |
| `S482917365428193642` | `S` i 18 cyfr. Przy numerach tego kształtu Peek pyta Stone3PL. Sam kształt nie przesądza jednak, że paczka należy do Stone3PL. |
| `S48291736542819364` | Tylko 17 cyfr: nie jest to format powiązany przez Peek ze Stone3PL. Sprawdź, czy nie brakuje cyfry, albo wklej go mimo to. |

W Peek małe litery, spacje i myślniki nie mają znaczenia. Wyszukiwarka Stone3PL przyjmuje do 40 numerów naraz, po jednym w linii; zachowuje wyłącznie litery, cyfry i myślniki, zamieniając małe litery na wielkie.

**Gdzie go znaleźć.** W e-mailu wysyłkowym od sprzedawcy lub na stronie zamówienia. Jeśli go tam nie ma, zobacz [jak znaleźć numer śledzenia](guide:find-tracking-number).

**Inne numery.** Strona śledzenia Stone3PL wymienia firmy obsługiwane przez swój tracker, takie jak DHL Express, UPS, USPS, China Post i China EMS (ePacket). Wynik może też wskazywać przewoźnika doręczającego w twoim kraju wraz z jego własnym numerem, który możesz śledzić bezpośrednio na jego stronie.

## Statusy śledzenia Stone3PL — wyjaśnienie

Wpisy śledzenia Stone3PL są po angielsku, z datą i godziną, lecz bez lokalizacji. Strona wyników wyświetla najpierw wpisy z kraju docelowego, a dopiero potem z początku trasy. Oto linie rozpoznawane przez Peek:

| Stone3PL pisze | Co to znaczy dla ciebie | Peek pokazuje |
| --- | --- | --- |
| `Order Submited.` | Sprzedawca przesłał zlecenie do Stone3PL. Stone3PL może jeszcze nie mieć paczki. Pisownia pochodzi ze Stone3PL. | „Zarejestrowana” |
| `Shipment information received` | Stone3PL otrzymał dane przesyłki. | „Zarejestrowana” |
| `Processing information input` | Stone3PL wprowadza dane przesyłki. | „Zarejestrowana”, a po `Pickup Scan` „W transporcie” |
| `Pickup Scan` | Przesyłka została odebrana i zeskanowana. Jest już w drodze. | „Przyjęta przez przewoźnika” |
| `facility - Outbound` | Paczka opuściła magazyn lub sortownię. | „W transporcie” |
| `Arrived at domestic terminal station` | Dotarła do terminalu. Wpis nie podaje, w którym kraju. | „W transporcie” |
| `Port of departure` | W porcie lub na lotnisku wyjścia, przed odlotem lub wypłynięciem. | „W transporcie” |
| `International shipment release - Export` | Zwolniona po odprawie eksportowej, jedzie dalej. | „W transporcie” |
| `Port of departure - Departure` | Opuściła port lub lotnisko wyjścia. | „W transporcie” |
| `Port of destination - Arrival` | Dotarła do portu lub na lotnisko w kraju docelowym. | „W transporcie” |
| `International shipment release - Import` | Zwolniona po odprawie importowej, rusza dalej. | „W transporcie” |
| `Pick up by local carrier at destination port` | Przewoźnik doręczający w twoim kraju odebrał paczkę w porcie. Ty nie musisz niczego odbierać. | „W transporcie” |
| `Batch delivery to carrier` | Stone3PL przekazał paczkę w partii z innymi przesyłkami przewoźnikowi doręczającemu. | „W transporcie” |

Wpisy z kraju docelowego mogą używać innych sformułowań. Peek rozpoznaje w nich typowe słowa, np. o doręczeniu lub odprawie celnej. Linia, której Peek nie rozpozna, zostaje w historii, a status pozostaje na ostatnim zrozumianym etapie.

**Status ogólny.** Tracker Stone3PL podaje też dla każdej paczki status zbiorczy. Gdy oznacza on gotowość do odbioru, doręczenie lub problem, Peek pokazuje „Do odbioru”, „Dostarczona” albo „Zgłoszono problem”, nawet zanim wspomni o tym pojedyncza linia.

**Godziny.** Stone3PL podaje godziny bez strefy czasowej. Peek datuje wpisy z początku trasy według czasu chińskiego (UTC+8), a wpisy z kraju docelowego oraz `International shipment release - Import` według czasu tego kraju (przy kilku strefach według jednej z nich), i wyświetla godziny w strefie czasowej twojego urządzenia („Godziny w twojej strefie czasowej:”).

## Kto doręcza paczki Stone3PL

Stone3PL deklaruje współpracę z uznanymi globalnymi operatorami logistycznymi i prowadzi magazyny w regionach NORAM, APAC i EMEA (Ameryka Północna, Azja-Pacyfik oraz Europa, Bliski Wschód i Afryka).

Jego strony nie wskazują, która firma doręcza w danym kraju: sprawdź przewoźnika końcowego i jego numer na stronie wyników Stone3PL. Próby doręczenia, awiza i punkty odbioru podlegają zasadom tej firmy. Zobacz [nieudane doręczenia](guide:missed-delivery).

**Cło i podatki.** Stone3PL oferuje sprzedawcom wysyłkę DDP (Delivered Duty Paid) z odprawą celną DDP. Tylko sprzedawca potwierdzi, czy twoje zamówienie nadano w ten sposób.

**W UE (np. w Irlandii).** Towary kupione online spoza UE podlegają od 1 lipca 2026 r. cłu w wysokości 3 € za pozycję w paczkach o wartości do 150 €. Od 1 listopada 2026 r. obowiązuje też unijna opłata manipulacyjna 2 € za pozycję, niezależnie od wartości. Pozycja to towary o tej samej klasyfikacji taryfowej, opisie oraz — gdy wymagane — pochodzeniu. Strony Stone3PL nie wyjaśniają sposobu uiszczania tych opłat. W Wielkiej Brytanii, USA, Kanadzie czy Australii opłaty zależą od lokalnych przepisów: zobacz [przesyłki zatrzymane na cle](guide:customs).

## Przesyłka Stone3PL nie aktualizuje się, zaginęła lub wróciła

Stone3PL obsługuje firmy nadające paczki i nie prowadzi stron pomocy dla odbiorców, więc w razie opóźnienia, zaginięcia, uszkodzenia lub zwrotu skontaktuj się najpierw ze sprzedawcą.

**Jeszcze nic nie ma.** Peek sprawdza dalej. Pokazuje „Oczekiwanie na przewoźnika Stone3PL”, jeśli wybrałeś Stone3PL, albo „Brak historii śledzenia”, gdy przy dodawaniu nie wybrano przewoźnika. Po dwóch dniach bez wieści Peek oznacza przesyłkę dodaną bez przewoźnika komunikatem „Oczekiwanie na pierwszą aktualizację od przewoźnika”: sprawdź numer u sprzedawcy. Jeśli wybrałeś Stone3PL, a po dwóch dniach nadal nie zna tego numeru i nie odpowiada żadne inne źródło, Peek wyświetla „Po dwóch dniach przewoźnik nadal nie zna tego numeru. Sprawdź go, wybierz innego przewoźnika lub usuń przesyłkę.” i sprawdza ją raz dziennie, a po tygodniu raz w tygodniu.

**Tylko `Order Submited.` lub `Shipment information received`.** Stone3PL ma dane zamówienia, ale być może jeszcze nie samą paczkę. Jeśli przez kilka dni nic się nie zmienia, zapytaj sprzedawcę, czy nadał paczkę.

**Brak aktualizacji od kilku dni.** Gdy przesyłka w statusie „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie” nie ma nowego wpisu przez cztery dni, Peek pokazuje datę ostatniego skanu („Brak aktualizacji od …”) i komunikat „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika Stone3PL.” z linkiem „Poproś przewoźnika Stone3PL o pomoc” do strony kontaktu. Zobacz [dlaczego śledzenie przestaje się aktualizować](guide:tracking-not-updating) oraz [śledzenie paczek z Chin](guide:tracking-from-china).

**„Zgłoszono problem”.** Stone3PL oznaczył usterkę. Przeczytaj najnowszy wpis i zapytaj sprzedawcę, co dalej.

**Dostarczona, ale jej nie ma.** Rozejrzyj się wokół domu i zapytaj sąsiadów, po czym poinformuj sprzedawcę. Zobacz [dostarczona, ale nieotrzymana](guide:delivered-not-received).

**Zaginiona, uszkodzona lub zwrócona.** Strona Stone3PL nie informuje odbiorców o reklamacjach ani zwrotach; jej strona główna oferuje sprzedawcom usługę zwrotu i ponownej wysyłki. Poproś sprzedawcę o zwrot pieniędzy lub nową paczkę. Zobacz [zaginione przesyłki](guide:lost-parcel), [uszkodzone przesyłki](guide:damaged-parcel) i [zwrócona nadawcy](guide:returned-to-sender).

**Wiadomość z prośbą o dopłatę.** Zanim zapłacisz opłatę wskazaną w SMS-ie lub e-mailu, przeczytaj [fałszywe SMS-y o dostawie](guide:delivery-scam-texts).

## Kontakt ze Stone3PL

Na [stronie kontaktu Stone3PL](https://stone3pl.com/index.php?route=services/contact_us), dla centrali w Shenzhen („STONE3PL Marketing Dpt”):

- **E-mail:** support@stone3pl.com. Polityka prywatności Stone3PL podaje ten sam adres.
- **Telefon:** 86-0755-81466633 (numer w Shenzhen w Chinach). Stone3PL nie podaje godzin pracy ani obsługiwanych języków.
- **Adres:** Ground Floor, Building C5, Xin'an Industry Co., No.2 Industrial Zone, Gushu 2nd Rd., Baoan, Shenzhen, Guangdong Province, China.

W sprawie zamówienia zapytaj najpierw sprzedawcę — to on wynajął Stone3PL, nie ty.

## Śledzenie przesyłki Stone3PL w Peek

[Peek](/) śledzi paczki Stone3PL, ale nie rozpozna numeru Stone3PL po samym układzie znaków. Gdy wkleisz numer złożony z `S` i 18 cyfr, Peek pokazuje „Sprawdzanie usług śledzenia…”, pytając Stone3PL. Jeśli Stone3PL ma paczkę, wyświetla się „Stone3PL ma tę przesyłkę.” i Peek śledzi ją tam. Link do trackera Stone3PL z jednym numerem po `nums=` od razu wskazuje Stone3PL („Wykryty przewoźnik”).

Każdy inny numer również wklej: Peek obsługuje ponad 4500 przewoźników. Gdy układ słabo pasuje do kilku firm, Peek wyświetla „Sprawdzanie usług śledzenia…” podczas zapytań; numer niepasujący do żadnego wzorca jest wyszukiwany po dodaniu. Jeśli Peek nie wskaże przewoźnika, stuknij „Zmień” i wybierz Stone3PL w sekcji „Wszyscy przewoźnicy”, albo użyj „Zmień przewoźnika” w już dodanej paczce.

Peek odczytuje status ogólny, każdy wpis z godziną, kraje nadania i docelowy, usługę wysyłki oraz przewoźnika końcowego i jego numer, jeśli zostały podane. Usuwa numery telefonów. Stone3PL nie podaje przewidywanej daty dostawy, wagi, lokalizacji skanów ani potwierdzenia odbioru.

Gdy Stone3PL wskaże firmę doręczającą i jej numer, Peek odpytuje tego przewoźnika. Przy numerze pocztowym (dwie litery, dziewięć cyfr, dwie litery) bez podanej nazwy firmy Peek pyta pocztę kraju docelowego — m.in. [USPS](carrier:usps) w USA, [Royal Mail](carrier:royal-mail) w Wielkiej Brytanii, An Post w Irlandii, [Canada Post](carrier:canada-post) czy [Australia Post](carrier:australia-post). Przy innym numerze pyta jedynego przewoźnika, do którego pasuje taki format, jeśli istnieje. Gdy tylko ten przewoźnik pokaże skan co najmniej tak świeży jak najnowszy ze Stone3PL, Peek śledzi przesyłkę tam, zachowując historię ze Stone3PL. Nazwa wskazanego przewoźnika widnieje już wcześniej (np. „Doręcza: USPS”); dopóki ma jedynie zapowiedź bez własnego skanu, widać „Zgłoszona przewoźnikowi …, jeszcze nieprzyjęta”. Bez takiego przewoźnika Peek pozostaje przy śledzeniu Stone3PL.

Dopóki masz przesyłkę otwartą w Peek — lub w dniu doręczenia z włączonymi powiadomieniami — Peek odpytuje bezpośrednio Stone3PL nawet co 10 minut, a od 6:00 czasu kraju docelowego w dniu przejścia w doręczenie nawet co 2 minuty, dopóki tracker Stone3PL odpowiada; poza tym, oraz po dwóch dniach bez wieści (12 godzin, dopóki status to „Zarejestrowana” lub „Przyjęta przez przewoźnika”), sprawdza co godzinę; po siedmiu dniach bez wieści w statusie „Zarejestrowana” co 6 godzin. Gdy doręczenie przejmie inny przewoźnik, Peek stosuje rytm tego przewoźnika. Może wysyłać powiadomienia dla opcji „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek nie skontaktuje się za ciebie ze Stone3PL ani ze sprzedawcą.

## Pytania o śledzenie Stone3PL

### Czy Stone3PL jest wiarygodne?

Stone3PL podaje, że należy do Stone Group z siedzibą w Shenzhen, ma ponad 10 lat doświadczenia i ponad 8000 klientów biznesowych. Publikuje adres, telefon i e-mail. Ponieważ pracuje dla sprzedawców, pytania o zamówienie kieruj do sklepu.

### Gdzie mogę śledzić paczkę Stone3PL?

Na [stronie śledzenia Stone3PL](https://stone3pl.com/?route=services/track) albo w Peek. Jeśli wynik Stone3PL wskazuje firmę doręczającą w twoim kraju, jej numer możesz śledzić także na jej własnej stronie.

### Co oznacza „Pick up by local carrier at destination port”?

Firma doręczająca w twoim kraju odebrała przesyłkę w porcie lub na lotnisku docelowym. Nie oznacza to, że masz ją odebrać osobiście.

### Ile czasu Stone3PL potrzebuje na dostawę?

Stone3PL nie publikuje czasów dostawy. Kieruj się terminem podanym przez sprzedawcę i zapytaj go, jeśli paczka się spóźnia.

### Jak skontaktować się ze Stone3PL?

E-mailem na support@stone3pl.com lub telefonicznie pod numerem 86-0755-81466633 (numer w Shenzhen w Chinach). W sprawie zamówienia zapytaj najpierw sprzedawcę.

:::sources
- [Stone3PL: Home](https://stone3pl.com/) – logistyka e-commerce z Chin na świat dla sprzedawców, odprawa DDP i Delivered Duty Paid, usługa zwrotu i ponownej wysyłki, magazyny w NORAM, APAC i EMEA
- [Stone3PL: Logistics tracking](https://stone3pl.com/?route=services/track) – tracker po angielsku, do 40 numerów po jednym w linii, litery, cyfry i myślniki wielkimi literami, obsługiwani przewoźnicy
- [Stone3PL: About us](https://stone3pl.com/index.php?route=services/about_us) – Stone Group, siedziba w Shenzhen, ponad 10 lat doświadczenia, ponad 8000 klientów kontraktowych, globalni partnerzy logistyczni
- [Stone3PL: Contact us](https://stone3pl.com/index.php?route=services/contact_us) – siedziba w Shenzhen, e-mail, telefon, adres
- [Stone3PL: Privacy Policy](https://stone3pl.com/index.php?route=services/privacy) – e-mail wsparcia
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – cło 3 € za pozycję do 150 € od 1 lipca 2026 r., unijna opłata manipulacyjna 2 € od 1 listopada 2026 r.
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € za pozycję niezależnie od wartości, definicja pozycji
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – układ numeru, opisy i etapy, status ogólny, czas chiński, firma doręczająca i przekazanie
:::
