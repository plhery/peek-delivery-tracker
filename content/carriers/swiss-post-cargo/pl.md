---
title: Śledzenie Swiss Post Cargo: statusy, dostawa i kontakt
description: Śledź przesyłkę Swiss Post Cargo: jaki numer działa, co znaczą Data entry POST i Loaded for Delivery, dostawa towaru paletowego oraz kontakt.
slug: sledzenie-swiss-post-cargo
published: 2026-11-22
updated: 2026-11-22
---

Swiss Post Cargo to marka transportu towarowego Swiss Post: palety i inne ładunki przewożone ciężarówkami, od pojedynczych sztuk po całopojazdowe ładunki, a nie zwykłe paczki pocztowe. Przesyłkę możesz śledzić na jej stronie Track & Trace (tt.swisspost-cargo.com) za pomocą kodu kreskowego lub numeru referencyjnego od nadawcy, albo wklejając go w pole powyżej. Jeśli masz zwykły numer paczki Swiss Post, należy on na [stronę Swiss Post](carrier:swiss-post).

## Numer śledzenia Swiss Post Cargo

System śledzenia Swiss Post Cargo przyjmuje kod kreskowy pojedynczej sztuki albo numer referencyjny nadawcy. Numer referencyjny wyświetla wszystkie kody kreskowe danej przesyłki. Wzorce, które Peek wiąże ze Swiss Post Cargo (cyfry wymyślone):

| Numer | Co to jest |
| --- | --- |
| `00482917365428193648` | 20 cyfr zaczynających się od `00`: kod kreskowy palety lub sztuki (SSCC). Ostatnia cyfra to cyfra kontrolna. |
| `PL-48291736` | `PL-` i 8 cyfr: numer referencyjny klienta. |

Sprawdź wiadomości e-mail ze sklepu, a następnie zapytaj sklep. Więcej wskazówek zawiera poradnik [jak znaleźć numer przesyłki](guide:find-tracking-number). Jeśli wyszukiwarka nic nie znajduje, sprawdź cyfry i skontaktuj się z nadawcą.

Dwa rodzaje numerów, które nie są śledzone jako Swiss Post Cargo:

- **Paczki Swiss Post:** 18 cyfr zaczynających się od `99` (zapisywane jako `99.00.482917.36542819`) lub dwie litery, dziewięć cyfr i `CH` (`RR482917367CH`).
- **Referencje PostLogistics** jak `48291736-542`: PostLogistics działa obecnie pod marką Swiss Post Cargo, a jej stary serwis tracking.postlogistics.ch już się nie otwiera. Peek śledzi te referencje jako PostLogistics.

## Statusy śledzenia Swiss Post Cargo

Swiss Post Cargo zapisuje skany po angielsku, niemiecku, francusku lub włosku. Po angielsku doręczona przesyłka wygląda tak:

| Swiss Post Cargo podaje | Co to oznacza dla Ciebie |
| --- | --- |
| `Data entry POST` | Dotarły dane od nadawcy. Towar nie trafił jeszcze do Swiss Post Cargo. |
| `Received warehouse POST` | Towar dotarł do centrum lub magazynu Swiss Post Cargo, wskazanego obok skanu. |
| `Loaded for Delivery` | Załadowano na ciężarówkę doręczającą: w doręczeniu. |
| `IMAGE` | Zdjęcie z doręczenia, wyświetlane tuż przed skanem doręczenia. |
| `DELIVERED SCANNED` | Doręczono w pokazanej miejscowości. |
| `SIGNATURE` | Podpis złożony przy odbiorze, z tą samą godziną co skan doręczenia. |

`Data entry POST` oznacza wyłącznie dane — „POST” jest częścią komunikatu i towar jeszcze nie ruszył. `IMAGE` oraz `SIGNATURE` to dowody towarzyszące doręczeniu, nie osobne ruchy przesyłki.

## Dostawa Swiss Post Cargo: kiedy, gdzie i nieudane doręczenia

**Kiedy.** Broszura transportu towarowego Swiss Post podaje, że ładunek dociera zazwyczaj następnego dnia roboczego. W Szwajcarii i Liechtensteinie miejsca dostępne dla ciężarówek otrzymują dostawę do 17:00 (czasu szwajcarskiego) następnego dnia roboczego. Trudniej dostępne doliny alpejskie i tereny górskie (Valais, Ticino i Gryzonia) otrzymują ją do 17:00 drugiego dnia roboczego.

**Gdzie.** Standardowo ładunek trafia pod pierwsze drzwi na parterze, na rampę albo pod krawężnik, w godzinach pracy, bez wnoszenia na piętra. Kierowca oczekuje dostępu wózkiem paletowym, a rozładunek należy do odbiorcy: jeśli kierowca rozładowuje, robi to w Twoim imieniu. Kierowca prosi o podpis potwierdzający poprawną dostawę.

**Usługi dodatkowe zamawiane przez nadawcę.** Powiadomienie telefoniczne, e-mailowe lub SMS-em, dostawa terminowa, wniesienie na piętro oraz druga próba doręczenia to usługi dodatkowe — przysługują tylko wtedy, gdy zamówił je nadawca.

**Nieudane doręczenie i odbiór.** Broszura towarowa Swiss Post nie wspomina o punktach odbioru ani automatach, a druga dostawa należy do powyższych usług dodatkowych. Nie określa też, jak długo towar czeka w magazynie ani jak przełożyć termin. Skontaktuj się ze sklepem, który zlecił transport.

## Przesyłka Swiss Post Cargo opóźniona, zaginiona lub uszkodzona

Klientem Swiss Post Cargo jest sklep (usługi zamawiają firmy), więc opóźnienie, uszkodzenie lub brakujący towar zgłoś najpierw w sklepie. Uszkodzenia zgłoś sklepowi od razu, ze zdjęciami.

**Brak aktualizacji przez dni.** Samo `Data entry POST` oznacza, że towar może wciąż czekać u nadawcy — zapytaj sklep, kiedy go przekaże. Poradnik [śledzenie nie aktualizuje się](guide:tracking-not-updating) wyjaśnia najczęstsze powody.

**Doręczona, ale jej nie ma.** Sprawdź wpisy `SIGNATURE` i `IMAGE` w wyszukiwarce, a następnie skontaktuj się ze sklepem. Zobacz [oznaczona jako doręczona, ale nie dotarła](guide:delivered-not-received).

**Podejrzane wiadomości.** Strona bezpieczeństwa Swiss Post ostrzega, że próby oszustwa pod jej nazwą docierają przez SMS, e-mail, WhatsApp, fałszywe strony i aplikacje, telefony oraz media społecznościowe — z linkami, załącznikami lub żądaniem opłaty czy danych.

## Kontakt Swiss Post Cargo

Swiss Post Cargo obsługuje zlecenie sklepu, więc zacznij od sklepu. Broszura towarowa Swiss Post podaje kontakt dla klientów biznesowych bez numeru telefonu: Customer Service Transport CH, Lagerstrasse 12, 5606 Dintikon, sales.ls4@post.ch.

Dla zwykłych listów i paczek Swiss Post (nie Cargo) infolinia Swiss Post działa pod numerem +41 848 888 888 (0,08 CHF za minutę ze szwajcarskiego numeru stacjonarnego), od poniedziałku do piątku 07:30–18:00 oraz w soboty 08:00–12:00 (czasu szwajcarskiego).

## Śledzenie przesyłki Swiss Post Cargo w Peek

20-cyfrowy kod kreskowy zaczynający się od `00` należy do nadawcy, a nie jednego przewoźnika, więc [Peek](/) pokazuje „Sprawdzanie usług śledzenia…” i wskazuje Swiss Post Cargo dopiero wtedy, gdy Swiss Post Cargo zna numer (jeśli zna go kilku przewoźników, Peek pyta, którego wybrać). O referencję `PL-48291736` Peek pyta Swiss Post Cargo oraz TNT Italy, której numery mogą mieć ten sam układ. Dla każdej innej referencji od 6 do 40 liter i cyfr zmień przewoźnika i wybierz Swiss Post Cargo, albo wklej link Track & Trace z tt.swisspost-cargo.com lub apv.swisspost-cargo.com (`/public/trackandtrace/` z numerem). Zwykły numer paczki Swiss Post w takim linku jest śledzony jako Swiss Post.

Peek pokazuje status i historię z miejscowością każdego skanu na mapie oraz godzinę doręczenia. Godziny są odczytywane według czasu zurychskiego i przeliczane na Twoją strefę czasową (historia kończy się dopiskiem „Godziny w twojej strefie czasowej:” z jej nazwą). Treści pozostają po angielsku; `Loaded for Delivery` liczy się jako w doręczeniu. Wpisy podpisu i zdjęcia łączą się ze skanem doręczenia, bez samego zdjęcia. Peek pomija Twoje nazwisko i adres oraz nie podaje przewidywanej daty, wagi ani punktu odbioru.

Referencja współdzielona przez kilka przesyłek działa tylko wtedy, gdy tylko jedna z nich ma skany z ostatnich 60 dni; w przeciwnym razie Swiss Post Cargo jej nie znajduje (Peek pokazuje „Oczekiwanie na przewoźnika Swiss Post Cargo”, a po dwóch dniach prosi o sprawdzenie numeru), więc użyj kodu kreskowego. Gdy masz przesyłkę otwartą na ekranie — albo w dniu doręczenia z włączonymi powiadomieniami — Peek odpytuje bezpośrednio Swiss Post Cargo nawet co 10 minut, a od 6:00 czasu szwajcarskiego co 2 minuty w dniu, gdy Swiss Post Cargo oznaczy ją jako wydaną do doręczenia, o ile jej własny system odpowiada. W pozostałym czasie oraz po dwóch dniach bez nowego skanu (12 godzin przy statusach „Zarejestrowana” lub „Przyjęta przez przewoźnika”) sprawdza co godzinę. W powiadomieniach wybierz „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Po czterech dniach bez nowego skanu przesyłki w statusie „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie” Peek pokazuje na liście dzień ostatniego skanu („Brak aktualizacji od …”), a na karcie paczki „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika Swiss Post Cargo.” z linkiem „Poproś przewoźnika Swiss Post Cargo o pomoc” do strony pomocy wyłącznie w tej informacji.

## Pytania o śledzenie Swiss Post Cargo

### Czym jest Swiss Post Cargo?

To nazwa, pod którą od 1 marca 2025 r. działa logistyka towarowa Swiss Post: rozwiązania logistyczne dla firm w Szwajcarii i za granicą, w tym przewozy ładunków. Wśród spółek grupy w Szwajcarii znajduje się Swiss Post Cargo CH AG, należąca w 100% do Swiss Post. PostLogistics była jedną z pierwszych spółek grupy, które przyjęły tę nazwę.

### Czy PostLogistics to to samo co Swiss Post Cargo?

Tak, PostLogistics działa teraz pod marką Swiss Post Cargo. Jej dawny serwis tracking.postlogistics.ch już nie działa; system Swiss Post Cargo znajduje się pod tt.swisspost-cargo.com. Peek nadal śledzi referencje PostLogistics takie jak `48291736-542`.

### Czy Swiss Post Cargo ma adres e-mail?

Broszura towarowa Swiss Post podaje adres sales.ls4@post.ch dla klientów biznesowych. Jako odbiorca napisz najpierw do sklepu, który zamówił transport.

### Dlaczego Swiss Post Cargo wysyła mi SMS?

Często dlatego, że nadawca zamówił powiadomienie o dostawie — broszura wymienia powiadomienie telefoniczne, e-mailowe lub SMS-em jako usługę dodatkową. Krążą też fałszywe SMS-y podszywające się pod Swiss Post, więc zawsze sprawdź numer samodzielnie w systemie śledzenia.

### Czy mogę śledzić Swiss Post Cargo na żywo?

Nie na mapie na żywo. System podaje każdy skan wraz z miejscowością, a broszura towarowa mówi o regularnych aktualizacjach położenia przesyłki.

:::sources
- [Swiss Post: Transport freight brochure](https://www.post.ch/en/-/media/post/gk/dokumente/stueckgut.pdf) – od pojedynczych sztuk po całopojazdowe ładunki, zamówienia firm, następny dzień roboczy, rejony górskie, parter, rozładunek, podpis, dodatki nadawcy w tym druga dostawa, regularne aktualizacje, kontakt w Dintikon i e-mail
- [Swiss Post: Logistics market](https://site.post.ch/en/about-us/company/strategy-and-value/business-activities/logistics) – Swiss Post Cargo to logistyka towarowa Swiss Post dla firm w Szwajcarii i za granicą
- [Swiss Post: Group companies](https://site.post.ch/en/about-us/company/governance/organisation/subsidiaries) – Swiss Post Cargo CH AG, 100% Swiss Post
- [Swiss Post: Help and contact](https://www.post.ch/en/help-and-contact) – infolinia listów i paczek, cena, godziny
- [Swiss Post: Current fraud attempts](https://site.post.ch/en/about-us/security/phishing-and-fraud/current-fraud-attempts) – fałszywe wiadomości pod nazwą Swiss Post
- [VerkehrsRundschau: Schweizerische Post mit neuem Marktauftritt](https://www.verkehrsrundschau.de/nachrichten/transport-logistik/logistik-schweizerische-post-fuehrt-neue-marke-ein-3626449) – marka Swiss Post Cargo od 1 marca 2025 r., PostLogistics wśród pierwszych spółek pod nową nazwą
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – co odczytuje Peek: kody kreskowe i referencje, wzorce numerów, angielskie opisy skanów, adresy Track & Trace, PostLogistics pod marką Swiss Post Cargo
:::
