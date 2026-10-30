---
title: GEODIS Polska (Pekaes): śledzenie przesyłki i statusy
description: Śledzenie przesyłki GEODIS (dawniej Pekaes) po numerze z PL i 16 cyframi: co znaczą statusy, jak wygląda dostawa i odbiór z terminala, reklamacja i telefon.
slug: geodis-polska-sledzenie
published: 2026-10-30
updated: 2026-10-30
---

GEODIS Polska to polska sieć przewozu palet i paczek francuskiej grupy GEODIS, której właścicielem są koleje SNCF. GEODIS przejął PEKAES w 2021 roku, a dziś sieć prowadzi spółka GEODIS Road Network z siedzibą w Błoniu. Przesyłkę śledzisz w [Strefie Klienta](https://strefaklienta.pekaes.geodis.pl/pl/) po numerze złożonym z liter PL i 16 cyfr albo wklejasz ten numer w pole powyżej. Numer podaje sklep lub nadawca, najczęściej w e-mailu z potwierdzeniem zamówienia albo w powiadomieniu o wysyłce.

## Numer przesyłki GEODIS: jak wygląda i gdzie go szukać

GEODIS pracuje wyłącznie dla firm, więc numer ma ten, kto wysłał Ci coś przez GEODIS, zwykle sklep albo producent: jeśli go nie masz, zapytaj nadawcę. Nadal nic? Zobacz, [gdzie jeszcze szukać numeru przesyłki](guide:find-tracking-number).

| Numer (wymyślony) | Co to jest |
| --- | --- |
| `PL0000000000000000` | PL i 16 cyfr: numer przesyłki GEODIS Polska – ten, który wpisujesz w Strefie Klienta. |
| `1G0000000000` | 1G i 10 wielkich liter lub cyfr: numer GEODIS we Francji. Śledzi się go na francuskiej stronie GEODIS, nie w Strefie Klienta. |

Wpisz numer w pole „Wpisz numer przesyłki” i kliknij „Szukaj”. Gdy strona odpowie `Brak przesyłki o podanym numerze.`, Strefa Klienta nie zna tego numeru: skontaktuj się ze sklepem lub nadawcą, bo tak radzi GEODIS, gdy numer nie działa. Komunikat `Proszę wprowadzić właściwy numer przesyłki.` znaczy, że strona nie przyjęła numeru w tej postaci: poszukaj literówki.

## Statusy GEODIS: co znaczą

Strefa Klienta działa po polsku i po angielsku. Po wyszukaniu widzisz najpierw podsumowanie: status, kraj dostawy, terminale, daty, listę towarów oraz kod pocztowy i miejscowość dostawy. Link przy podsumowaniu otwiera historię skanów, każdy z godziną i lokalizacją terminala. Peek czyta wersję angielską, dlatego w tabeli są oba brzmienia, w kolejności, w jakiej zwykle spotyka je przesyłka:

| Strona GEODIS po polsku | W Peek (po angielsku) | Co to znaczy dla Ciebie |
| --- | --- | --- |
| `Odebrana od nadawcy` | `Received from the sender` | GEODIS odebrał przesyłkę od nadawcy. |
| `wejście do terminala od kierowcy` | `Inbound Scan From Driver to Terminal` | Kierowca przywiózł ją do terminala nadania. |
| `wyjście z terminala (odbioru) na linię` | `Outbound Scan from (Collection) Terminal to Line-haul` | Wyjechała z terminala nadania w trasę do terminala, który obsługuje Twój adres. |
| `wejscie z linii do terminala (dostawy)` | `Inbound Scan from Line-haul to (Delivery) Terminal` | Dotarła do terminala, który ją doręczy. |
| `oczekiwanie na dostawę` | `Awaiting Delivery` | Czeka w tym terminalu na kurs z dostawą. |
| `wydanie do dostawy` | `Outbound for Delivery` | Jedzie do Ciebie samochodem dostawczym. |
| `odbiorca nieobecny` | `Consignee Absent` | Kierowca Cię nie zastał. |
| `Niedostarczona (SMS)` | `Failed delivery (SMS)` | Nieudane doręczenie, potwierdzone SMS-em. |
| `oczekiwanie - zmiana terminu dostawy` | `Awaiting designation of new delivery date` | GEODIS czeka na nowy termin dostawy. |
| `oczekiwanie na decyzję` | `Awaiting Decision` | GEODIS czeka na decyzję nadawcy. |
| `odmowa przyjęcia` | `Delivery Refusal` | Odbiorca odmówił przyjęcia przesyłki. |
| `dyspozycja zwrotu` | `Request for Return` | Jest polecenie zwrotu: przesyłka wraca do nadawcy, ale jeszcze tam nie dotarła. |
| `doręczone` | `Delivered` | Doręczona. `Dostarczona (SMS)` znaczy to samo, z potwierdzeniem SMS-em. |
| `przyjęta reklamacja` | `Claim Received` | Złożono reklamację. To tylko wpis: przesyłka się nie przemieszcza. |

Na przestój wskazują `zatrzymana w sortowni` i `zatrzymana w magazynie terminala nadania` (przesyłkę wstrzymano w sortowni albo w pierwszym terminalu), `nie wyjechała` (nie pojechała zaplanowanym transportem lub kursem, ale wciąż jest w sieci) oraz `awaria`. Wiersz zaczynający się od `pseudo skan` kasuje wcześniejsze skany doręczenia, zwrotu i reklamacji. Ogólne etapy opisuje poradnik o [statusach przesyłek](guide:tracking-statuses).

## Dostawa GEODIS: adres prywatny, awizacja, odbiór z terminala

Zasady dostawy ustala Ogólny Regulamin Świadczenia Usług GEODIS, obowiązujący od 4 listopada 2024 roku. Wiąże on zleceniodawcę, czyli firmę, która zamówiła przewóz (zwykle sklep), a większość z nich opisuje dla przesyłek paletowych, czyli drobnicowych:

- **Nikogo nie ma w domu.** Kierowca zostawia informację o próbie doręczenia. Po drugiej nieudanej próbie przesyłka wraca do nadawcy; wraca też, gdy odmówisz jej przyjęcia albo nadawca podał błędny adres. Więcej w poradnikach o [nieudanym doręczeniu](guide:missed-delivery) i [zwrocie do nadawcy](guide:returned-to-sender).
- **Palety na adres prywatny.** Taką dostawę GEODIS realizuje tylko w dni robocze, od poniedziałku do piątku, w godzinach 8:00–16:00. Zleceniodawca musi podać Twój numer komórki: GEODIS wysyła na niego informacje o dostawie i wcześniej ją awizuje telefonicznie, SMS-em lub e-mailem, a dzwoni najpóźniej w dniu możliwej dostawy. Jeśli przez dwa dni robocze nie zdoła się z Tobą skontaktować, po kontakcie ze zleceniodawcą odsyła przesyłkę do nadawcy.
- **Rozładunek.** Przesyłkę paletową na adres prywatny kierowca rozładowuje do poziomu podłoża w miejscu dostawy, a gdy samochód nie może tam bezpiecznie podjechać – w najbliższym bezpiecznym, utwardzonym miejscu. Wniesienie, dostawa na wskazaną godzinę i dostawa do 10:00 lub 12:00 (tylko pod wybrane kody pocztowe) to osobne usługi, które może zamówić zleceniodawca.
- **Odbiór z terminala.** Gdy zleceniodawca zleci odbiór własny z terminala GEODIS, przesyłka czeka 48 godzin, liczonych w dniach roboczych od tej dyspozycji. Odbierzesz ją od poniedziałku do piątku w godzinach 11:00–16:00; potem wraca do nadawcy.
- **Pobranie.** Pobranie do 10 000 zł zapłacisz gotówką, kartą, BLIK-iem albo inną metodą bezgotówkową, którą dopuszcza GEODIS.
- **Podpis.** Odbiór potwierdzasz podpisem na urządzeniu kierowcy albo na dokumentach przewozowych. Według regulaminu nie możesz sprawdzić zawartości przed potwierdzeniem odbioru, więc obejrzyj opakowanie, a zastrzeżenia wpisz do dokumentów od razu przy odbiorze.

## Przesyłka GEODIS się spóźnia, jest uszkodzona albo nie dotarła

**Status stoi w miejscu.** Zajrzyj do poradnika, [co robić, gdy status się nie zmienia](guide:tracking-not-updating), i napisz do sklepu.

**Opóźnienie.** Według UOKiK sprzedawca musi wydać towar niezwłocznie, najpóźniej w ciągu 30 dni od zawarcia umowy, chyba że umówiliście się inaczej. Po tym terminie możesz wyznaczyć mu dodatkowy czas, a gdy towar dalej nie dotrze, odstąpić od umowy.

**Uszkodzenie lub braki.** Widoczne uszkodzenie albo brak części towaru opisz w protokole szkody, spisanym przy kierowcy na wzorze GEODIS i podpisanym przez Ciebie i kierowcę. Ukryte uszkodzenia i braki zgłoś do GEODIS przed upływem 7 dni od odbioru; to reklamujący musi udowodnić, że powstały przed doręczeniem. Zostaw towar do oględzin i zrób zdjęcia z datą i godziną. Formularz reklamacyjny GEODIS przypomina, że sam protokół szkody to jeszcze nie reklamacja. Więcej w poradniku o [uszkodzonej paczce](guide:damaged-parcel).

**Kto składa reklamację.** Według regulaminu: zleceniodawca albo osoba, którą upoważni na piśmie. Reklamację składa się na piśmie do Działu Reklamacji w Błoniu, niezwłocznie: przy uszkodzeniu lub ubytku nie później niż 14 dni od odbioru, a przy opóźnieniu lub utracie nie później niż 21 dni od dnia, w którym przesyłka miała dotrzeć. Jako kupujący załatwiasz sprawę ze sklepem: UOKiK przypomina, że sprzedawca odpowiada za przesyłkę, dopóki jej nie otrzymasz, nawet gdy uszkodził ją przewoźnik. Reklamację składasz więc sprzedawcy, a on dochodzi roszczeń od przewoźnika.

## GEODIS kontakt: telefon i reklamacje

Według stron GEODIS (stan na 30 października 2026):

- **Dział Obsługi Klienta:** +48 22 460 26 26, numer podany w Strefie Klienta. Godzin pracy GEODIS nie podaje.
- **Reklamacje listownie:** Dział Reklamacji, ul. Spedycyjna 1, 05-870 Błonie. Formularz zgłoszenia reklamacji i wzór protokołu szkody pobierzesz ze Strefy Klienta.
- **Lokalizacje:** [polska strona kontaktowa GEODIS](https://geodis.com/pl-pl/skontaktuj-sie-z-nami) wymienia 42 placówki w Polsce z adresami, w tym oddziały GEODIS Road Network.
- **Formularz na geodis.com:** służy do zapytań ofertowych i wymaga nazwy firmy, więc jest przeznaczony dla firm.

## Śledzenie przesyłki GEODIS w Peek

PL i 16 cyfr to nie znak rozpoznawczy GEODIS (PL oznacza Polskę), więc Peek nie rozpozna GEODIS Poland po samym numerze. I tak wklej numer: Peek śledzi ponad 3500 przewoźników. Zapyta GEODIS Poland, czy zna ten numer, a gdy zna, pokaże „GEODIS Poland ma tę przesyłkę.” Jeśli GEODIS jeszcze go nie zna, zobaczysz „Prawdopodobnie GEODIS Poland” i „Oczekiwanie na pierwszy skan przewoźnika GEODIS Poland”, a Peek będzie pytać dalej. Przewoźnika wybierzesz też sam: kliknij „Zmień” w wierszu przewoźnika i wskaż „GEODIS Poland” w sekcji „Pasują do numeru” albo wyszukaj go – znajdziesz go także, wpisując Pekaes. Samo „GEODIS” to GEODIS we Francji. Przy dodanej już przesyłce użyj „Zmień przewoźnika”.

Peek czyta angielską wersję Strefy Klienta i pokazuje status, każdy skan w angielskim brzmieniu GEODIS z lokalizacją terminala, godzinę doręczenia i kraj dostawy. Skany z polskich terminali odczytuje w czasie warszawskim, a skan z terminala za granicą ma godzinę lokalną tego terminala. Gdy wiersz `Pseudoscan` skasuje wcześniejsze skany, Peek też je pomija, a `Claim Received` nie zmienia statusu. Strona GEODIS nie podaje przewidywanej daty dostawy, wagi, nazwy usługi, nazwiska odbiorcy ani dowodu doręczenia. Peek pomija kod pocztowy i adres dostawy, liczbę sztuk, kody terminali i uwagi, w których może paść nazwisko osoby podpisującej odbiór.

Peek sprawdza przesyłkę w dzień co 10 minut, gdy jedzie już do Ciebie – co 2 minuty, a w nocy co godzinę lub dwie. Może Cię powiadamiać o każdym skanie, tylko o ważnych etapach albo tylko w dniu dostawy. Nie zmieni terminu dostawy, nie umówi odbioru z terminala i nie złoży reklamacji: zadzwoń do GEODIS albo napisz do sklepu.

## Pytania o śledzenie przesyłek GEODIS

### Czy GEODIS to dawny Pekaes?

Tak. GEODIS przejął PEKAES w 2021 roku, a sieć prowadzi dziś spółka GEODIS Road Network. Strefa Klienta wciąż ma adres strefaklienta.pekaes.geodis.pl.

### Czy GEODIS dowozi w sobotę?

Regulamin GEODIS nie mówi o dostawach w soboty. Przesyłki paletowe na adres prywatny GEODIS doręcza tylko od poniedziałku do piątku, w godzinach 8:00–16:00, a czas dostawy paczek liczy bez sobót, niedziel i świąt.

### Czy kierowca GEODIS wniesie przesyłkę do mieszkania?

Tylko przesyłkę paletową i tylko wtedy, gdy zleceniodawca zamówił usługę wniesienia: do 300 kg, nie dalej niż 150 m od miejsca rozładunku, a bez windy do drugiej kondygnacji. Bez tej usługi kierowca rozładowuje przesyłkę do poziomu podłoża pod Twoim adresem.

### Ile trwa dostawa GEODIS?

W Polsce GEODIS podaje 24–48 godzin dla dostawy standardowej i następny dzień przed południem dla ekspresowej. Według regulaminu paczki do 30 kg docierają „zasadniczo” w ciągu 48 godzin od odbioru od nadawcy, nie licząc sobót, niedziel i świąt, a przesyłki paletowe w ciągu 2 dni roboczych.

:::sources
- [GEODIS Polska: Strefa Klienta, Śledzenie przesyłek](https://strefaklienta.pekaes.geodis.pl/pl/) – pole i przycisk wyszukiwania, odpowiedź na nieznany numer, Dział Obsługi Klienta, formularze
- [GEODIS Polska: Strefa Klienta po angielsku](https://strefaklienta.pekaes.geodis.pl/en/) – wersja, którą czyta Peek
- [GEODIS Road Network: Ogólny Regulamin Świadczenia Usług od 04.11.2024](https://strefaklienta.pekaes.geodis.pl/pl/?file=files/pekaes/Dla%20Klienta%20-%20nowe%20www/GEODIS_SK/GEODIS%20RN_Ogolny%20Regulamin%20Swiadczenia%20Uslug_od%2004.11.2024.pdf) – zleceniodawca, próby doręczenia, dostawa palet na adres prywatny, awizacja, rozładunek, wniesienie, odbiór z terminala, pobranie, podpis, czas dostawy, reklamacje
- [GEODIS Road Network: Formularz zgłoszenia reklamacji](https://strefaklienta.pekaes.geodis.pl/pl/?file=files/pekaes/Dla%20Klienta%20-%20nowe%20www/GEODIS_SK/GRN%20Formularz%20zgloszenia%20reklamacji.pdf) – adres Działu Reklamacji, protokół szkody to nie reklamacja
- [GEODIS: Śledź swoją przesyłkę](https://geodis.com/pl-pl/sledz-swoja-przesylke) – gdzie jest numer, co zrobić, gdy nie działa
- [GEODIS: Twój obszar roboczy](https://geodis.com/pl-pl/twoj-obszar-roboczy) – Strefa Klienta jako śledzenie GEODIS
- [GEODIS: Transport palet i paczek](https://geodis.com/pl-pl/uslugi-transportowe/transport-palet-i-paczek) – tylko dla firm, czasy dostawy
- [GEODIS: Skontaktuj się z nami](https://geodis.com/pl-pl/skontaktuj-sie-z-nami) – formularz, 42 lokalizacje w Polsce
- [GEODIS: Historia](https://geodis.com/pl-pl/historia) – przejęcie PEKAES w 2021 roku, SNCF
- [UOKiK: Odpowiedzialność za przesyłkę](https://prawakonsumenta.uokik.gov.pl/prawa-ogolne/odpowiedzialnosc-sprzedawcy-za-przesylke/) – sprzedawca odpowiada do wydania, reklamacja u sprzedawcy
- [UOKiK: Wydanie towaru](https://prawakonsumenta.uokik.gov.pl/prawa-ogolne/wydanie-towaru/) – 30 dni, dodatkowy termin, odstąpienie
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – kształt numeru, statusy po polsku i angielsku, co Peek odczytuje
:::
