---
title: Śledzenie Lietuvos paštas (Poczta Litewska) i LP Express
description: Śledź przesyłkę Lietuvos paštas lub LP Express: numer śledzenia, statusy po polsku, automaty Unisend, odprawa celna, reklamacje i kontakt.
slug: sledzenie-lietuvos-pastas
published: 2026-11-22
updated: 2026-11-22
---

Lietuvos paštas (Poczta Litewska) to narodowa poczta Litwy, a LP Express to jej marka dla automatów paczkowych, kurierów i samoobsługowego nadawania. Przesyłkę możesz śledzić na [lpexpress.lt/track](https://lpexpress.lt/track) (z wyborem języka angielskiego) albo wklejając numer w pole powyżej. Numer znajdziesz w SMS-ie lub e-mailu od Lietuvos paštas albo na potwierdzeniu nadania.

## Numer śledzenia Lietuvos paštas

Numer Poczty Litewskiej ma 13 znaków w [międzynarodowym formacie pocztowym](guide:tracking-number-formats): dwie litery, dziewięć cyfr i `LT`. Regulamin Lietuvos paštas wskazuje dwa prefiksy LP Express, `CC` i `CH` (LP Express używa też `HC`).

| Format (wymyślony) | Co to jest |
| --- | --- |
| `RR482917367LT` | Przesyłka pocztowa nadana na Litwie. |
| `CC482917367LT` | Paczka LP Express do automatu paczkowego. |
| `CH482917367LT` | Paczka LP Express dostarczana przez kuriera pod adres na Litwie lub za granicą. |

Gdy paczka trafi do automatu, Lietuvos paštas wysyła SMS i/lub e-mail z numerem, adresem automatu i kodem PIN.

Prawdziwe wiadomości przychodzą od nadawcy `LPASTAS` lub `LP_EXPRESS`, nigdy z numeru telefonu.

Inne kody krajów oznaczają inne poczty: `…LV` to Latvijas Pasts, a `…EE` to [Omniva](carrier:omniva). Przesyłka z zagranicy zachowuje numer swojej poczty nadania. Venipak, inny kurier na Litwie, stosuje inny wzór (`V48291E7365428`). Zgubiłeś go? Zobacz [jak znaleźć numer przesyłki](guide:find-tracking-number).

## Co znaczą statusy śledzenia Lietuvos paštas

lpexpress.lt/track pokazuje każdy krok po angielsku, litewsku, łotewsku lub estońsku; główny serwis post.lt działa tylko po litewsku. Każdy wpis ma krótki nagłówek i dłuższą linię zdarzenia. Poniżej angielskie linie zdarzeń po kolei:

| Status Lietuvos paštas | Co to oznacza dla Ciebie |
| --- | --- |
| `The label for the parcel has been created` | Nadawca wygenerował etykietę. Lietuvos paštas jeszcze nie ma paczki. |
| `Parcel received` | Lietuvos paštas przyjęła paczkę od nadawcy. |
| `The parcel is at the distribution center` | W centrum dystrybucyjnym; wpis podaje miasto. |
| `The parcel is transported to another distribution center` | W transporcie między centrami dystrybucyjnymi. |
| `The parcel has been sent abroad` | Opuściła Litwę. |
| `The parcel was received at the distribution center of the recipient's (or transit) country` | Dotarła do sortowni za granicą. |
| `The parcel has been forwarded for delivery` | W doręczeniu: jedzie do Ciebie lub do automatu. |
| `The parcel was delivered to the UNISEND parcel locker` | Gotowa do odbioru we wskazanym automacie. |
| `The parcel has been delivered to the recipient` | Doręczona lub odebrana z automatu. |
| `Sent to receiver` | Nagłówek, nie konkretny etap: w drodze albo czeka w automacie. |

Dwa elementy często mylą odbiorców. Nagłówek `Sent to receiver` zostaje od pierwszego skanu sortowania aż do odbioru, także przez cały czas postoju w automacie — dopiero wiersz zdarzenia mówi, że paczka jest gotowa. Ponadto komunikat o skrytce podaje nazwę UNISEND nawet dla automatów na Litwie oznaczonych LP EXPRESS: Unisend to ich marka na Łotwie i w Estonii.

Komunikat `Your parcel could not be found` wymaga sprawdzenia numeru, a `Without tracking` oznacza wysyłkę bez śledzenia. Na Litwie trasę pokazują wyłącznie przesyłki rejestrowane. Więcej w [co znaczą statusy przesyłek](guide:tracking-statuses).

## Automaty LP Express, placówki pocztowe i nieudane doręczenia

**Automaty paczkowe.** Na Litwie, Łotwie i w Estonii działa około 1500 automatów LP EXPRESS i Unisend czynnych całą dobę. Na ekranie wybierz „Atsiimti siuntą” (odbierz paczkę) i wpisz PIN z SMS-a lub e-maila. Masz 72 godziny od dostarczenia. Później kurier wyjmuje paczkę, a Ty dostajesz SMS z linkiem: w ciągu 4 dni możesz raz i bezpłatnie wysłać ją z powrotem do tego samego lub innego automatu, w przeciwnym razie wraca do nadawcy. Paczka za duża do skrytki czeka 15 dni w punkcie obsługi Lietuvos paštas.

**Przekierowanie.** Aby przekierować paczkę będącą jeszcze w drodze do innego automatu, wpisz numer na stronie śledzenia i wybierz nową lokalizację pod „To parcel locker” (jest też „To home address”). Gdy jest za późno, pojawi się `Redirection is not possible` albo komunikat o upływie terminu.

**Kurier pod drzwi.** Kurier nie musi czekać dłużej niż 10 minut; odbiór potwierdzasz podpisem na jego urządzeniu. Jeśli nikogo nie ma, otrzymujesz powiadomienie SMS-em lub e-mailem, a paczka czeka bezpłatnie w centrum logistycznym przez 7 dni kalendarzowych od pierwszego dnia wyjścia w doręczenie.

**Placówki pocztowe.** Przesyłki rejestrowane są doręczane pod adres raz; w razie nieobecności dostajesz awizo do skrzynki, e-mailem lub SMS-em. Weź je z dokumentem tożsamości. Według regulaminu TAI-16 czekają 30 dni od dnia następnego po awizie; FAQ na post.lt podaje jeden miesiąc kalendarzowy, o ile umowa nadawcy nie stanowi inaczej.

**Spoza UE.** Lietuvos paštas prosi o opłacenie należności importowych w samoobsłudze LP EXPRESS w ciągu 7 dni kalendarzowych od wezwania, inaczej paczka wraca do nadawcy. Po opłaceniu trafia na pocztę lub do automatu bez dodatkowych opłat na miejscu. Za zgłoszenie celne Lietuvos paštas pobiera 6,99 € do 150 € oraz 30 € powyżej tej kwoty lub za zgłoszenie standardowe, plus 21% podatku VAT od towarów i wysyłki. Gdy zgłaszasz samodzielnie albo sklep pobrał VAT przy zakupie (IOSS), opłata wynosi 2 €. Od 1 lipca 2026 r. zakupy internetowe spoza UE podlegają tymczasowemu cłu UE 3 € za pozycję w paczkach do 150 €; od 1 listopada 2026 r. obowiązuje też unijna opłata manipulacyjna 2 € za pozycję niezależnie od wartości. Pozycja to towary w przesyłce o tej samej klasyfikacji taryfowej, opisie i — gdy wymagane — pochodzeniu. Zobacz [paczka zatrzymana na cle](guide:customs).

## Paczka Lietuvos paštas opóźniona, zaginiona lub zwrócona

Zaginięcie lub opóźnienie przesyłki rejestrowanej albo ubezpieczonej Lietuvos paštas każe zgłaszać pod 1842. Reklamację składa nadawca w ciągu 6 miesięcy od nadania, więc przy zakupie w sklepie najpierw napisz do sklepu. Po podpisaniu odbioru odszkodowanie nie przysługuje.

W przypadku paczki nadanej za granicą Lietuvos paštas odsyła do nadawcy. Jeśli zauważysz uszkodzenie przy dostawie kurierskiej, dopilnuj zapisania go na urządzeniu kuriera lub w dokumencie przewozowym w jego obecności — inaczej Lietuvos paštas nie uznaje odpowiedzialności.

**Nadana z Litwy za granicę.** Po `The parcel has been sent abroad` doręcza operator w kraju docelowym, a w reklamacji Lietuvos paštas opiera się na jego potwierdzeniu doręczenia lub zaginięcia. Peek również odpytuje pocztę docelową (niżej). Nadawca nadal ma 6 miesięcy od nadania na reklamację, a Lietuvos paštas wypłaca odszkodowanie za przesyłkę międzynarodową w ciągu 90 dni kalendarzowych od zgłoszenia.

Gdy śledzenie stoi od wielu dni, zobacz [śledzenie nie aktualizuje się](guide:tracking-not-updating).

## Kontakt i obsługa klienta Lietuvos paštas

Oficjalne kanały podane na post.lt (strony kontaktu są po litewsku):

- **Telefon z Litwy:** 1842 lub +370 700 554 00, według stawki operatora plus opłata za połączenie.
- **Telefon z zagranicy:** +370 5 233 3060.
- **Godziny:** w dni robocze 8:00–19:00, w soboty 9:00–16:00 czasu litewskiego.
- **E-mail:** info@post.lt.
- **Formularz online:** „Rašykite mums” (napisz do nas) na post.lt z zakładkami do reklamacji i poszukiwań międzynarodowych; na stronie działa też czat.

> Lietuvos paštas ostrzega przed fałszywymi SMS-ami o błędnych adresach, zatrzymanych paczkach i zwrotach. Prawdziwe wiadomości pochodzą od `LPASTAS` lub `LP_EXPRESS`, w SMS-ach nigdy nie proszą o dane karty ani dane osobowe i nie zawierają bezpośrednich linków do płatności. Fałszerstwa zgłaszaj na prevencija@post.lt; jeśli podałeś dane karty, zadzwoń do banku.

## Śledzenie Lietuvos paštas w Peek

[Peek](/) od razu rozpoznaje numer kończący się na `LT` z poprawną cyfrą kontrolną, gdy zaczyna się od `C`, `E` lub `HC`, i wskazuje Lietuvos paštas jako „Wykryty przewoźnik”. Większość numerów `CC`, `CH` i `HC` nie spełnia jednak cyfry kontrolnej S10: Peek pokazuje wtedy „Cyfra kontrolna tego numeru się nie zgadza.”, a opcja „Śledź tak, jak wpisano” odpytuje wyłącznie Lietuvos paštas. Dla każdego innego numeru pocztowego na `LT` z poprawną cyfrą kontrolną Peek odpytuje jednocześnie Lietuvos paštas i [Chronopost](carrier:chronopost), pokazując „Lietuvos paštas ma tę przesyłkę.”, gdy ją potwierdzi (lub pytając, którego wybrać, jeśli rozpozna ją kilku przewoźników). Działają również linki śledzenia z lpexpress.lt i post.lt.

Przesyłkę z zagranicy w formacie międzynarodowym Peek śledzi najpierw w poczcie nadania. Gdy ta wskaże Litwę jako cel — albo nie poda kraju, a sprawdzasz z Litwy — Peek pyta także Lietuvos paštas i po pojawieniu się nowych etapów prowadzi paczkę dalej tam, zachowując wcześniejsze wpisy. W przeciwnym razie wybierz „Lietuvos paštas” ręcznie. Numer [DHL Paket](carrier:dhl) zaczynający się od `C` i kończący na `DE` nigdy nie przechodzi do Lietuvos paštas.

Analogicznie przy przesyłce nadanej na Litwie z poprawną cyfrą kontrolną: Peek pyta też pocztę kraju docelowego, gdy Lietuvos paštas wykaże sortownię w kraju odbiorcy lub tranzytu (albo wcześniej pocztę kraju, z którego sprawdzasz, jeśli to nie Litwa). Część krajów docelowych nie ma poczty narodowej, którą Peek może odpytać.

Peek odczytuje usługę stojącą za lpexpress.lt/track po angielsku. Gdy masz paczkę otwartą na ekranie — albo w dniu doręczenia z włączonymi powiadomieniami — Peek sprawdza nawet co 10 minut, a od 6:00 czasu litewskiego nawet co 2 minuty w dniu, gdy Lietuvos paštas wyda ją do doręczenia na Litwie, o ile jej własny system odpowiada. W pozostałym czasie oraz po dwóch dniach bez nowego skanu (12 godzin przy statusach „Zarejestrowana” lub „Przyjęta przez przewoźnika”) sprawdza co godzinę. Przez same serwisy śledzące sprawdza co godzinę, co 3 godziny po dniu bez skanu i do 15 minut w dniu doręczenia, gdy paczka jest otwarta na ekranie lub włączono powiadomienia. Gdy doręczenie przejmie inny przewoźnik, Peek przyjmuje jego rytm. Wybierz „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Po czterech dniach bez nowego skanu przesyłki w drodze Peek pokazuje na liście dzień ostatniego skanu („Brak aktualizacji od …”), a na karcie paczki „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika Lietuvos paštas.” z linkiem „Poproś przewoźnika Lietuvos paštas o pomoc” do strony kontaktu wyłącznie w tej informacji.

Każdy krok trafia na mapę z miastem, sortownią lub krajem, etapami celnymi, godziną doręczenia oraz nazwą i miejscowością automatu lub placówki (bez ulicy i kodu pocztowego). Godziny litewskie są odczytywane według czasu wileńskiego i przeliczane na Twoją strefę czasową (historia kończy się dopiskiem „Godziny w twojej strefie czasowej:” z jej nazwą); kroki zagraniczne są odczytywane według czasu ich kraju (w krajach wielostrefowych jednej z jego stref) i wyświetlane w Twojej strefie. Brak przewidywanej daty dostawy i wagi. Statusy zachowują angielskie brzmienie Lietuvos paštas; zwrot pokazuje „Zgłoszono problem”, a po powrocie „Zwrócona nadawcy”.

## Pytania o śledzenie Lietuvos paštas

### Czym jest LP Express?

To nazwa, pod którą Lietuvos paštas prowadzi na Litwie automaty paczkowe, kurierów i samoobsługowe nadawanie. Na Łotwie i w Estonii ta sama usługa nazywa się Unisend.

### Czy śledzenie Lietuvos paštas działa po angielsku?

Tak, na lpexpress.lt/track po przełączeniu języka na angielski. Główny serwis post.lt jest dostępny tylko po litewsku.

### Czy mogę śledzić paczkę międzynarodową w Poczcie Litewskiej?

Tak, jeśli została nadana ze śledzeniem — z Litwy oznacza to usługę „Su sekimu” (ze śledzeniem). W przypadku przesyłki z zagranicy zapytaj nadawcę.

### Ile idzie paczka LP Express?

Lietuvos paštas podaje, że paczka do automatu na Litwie trafia zwykle następnego dnia roboczego lub w sobotę, jeśli zostanie nadana przed godziną odbioru. Dla krajowych paczek pocztowych post.lt wskazuje cel jakościowy 97% doręczonych w ciągu 4 dni roboczych, a dla listów priorytetowych do innych krajów UE — 85% w 3 dni robocze i 97% w 5.

:::sources
- [Lietuvos paštas: Siuntų sekimas](https://www.post.lt/lt/siuntu-sekimas) – format numeru, potwierdzenie, widoczność trasy, 1842, reklamacje, przesyłki zagraniczne, 6 miesięcy, 90 dni, cele jakościowe
- [LP Express: Parcel tracking](https://lpexpress.lt/track) – angielska strona śledzenia, języki, nagłówki, komunikaty i przekierowanie
- [Lietuvos paštas: Dažniausiai užduodami klausimai](https://www.post.lt/duk) – FAQ automatów, odbiór na poczcie, przechowywanie
- [Lietuvos paštas: Siuntos](https://www.post.lt/lt/duk/siuntos) – terminy przechowywania, zasady odbioru
- [Lietuvos paštas: LP EXPRESS paštomatai](https://www.post.lt/lt/duk/lp-express-pastomatai) – 72 godziny, przekierowanie w 4 dni, 15 dni przy nadgabarycie, opłata z góry
- [Lietuvos paštas: Siuntų siuntimas paštomatu](https://www.post.lt/lt/lp-express-pastomatai) – nazwy LP EXPRESS i Unisend, ok. 1500 automatów, PIN, następny dzień roboczy
- [Lietuvos paštas: Pašto paslaugų teikimo nesutartiniams naudotojams taisyklės TAI-16](https://www.post.lt/sites/default/files/Dokumentai/PA%C5%A0TO%20PASLAUG%C5%B2%20TEIKIMO%20TAISYKL%C4%96S.pdf) – `CC` i `CH`, zasady kurierskie, 7 dni przechowania, 30 dni na poczcie, dowód, uszkodzenia, 7 dni na zapłatę
- [Lietuvos paštas: Siuntų deklaravimas](https://www.post.lt/lt/siuntu-deklaravimas) – opłaty za zgłoszenie, VAT, 7 dni na opłatę
- [Lietuvos paštas: Kontaktai](https://www.post.lt/lt/kontaktai) – telefony, godziny, koszty, e-mail, czat
- [Lietuvos paštas: Rašykite mums](https://www.post.lt/rasykite-mums) – formularz oraz zakładki reklamacji i poszukiwań
- [Lietuvos paštas: Būkite atsargūs, Lietuvos paštu apsimetantys sukčiai](https://www.post.lt/lt/apie-mus/naujienos/bukite-atsargus-lietuvos-pastu-apsimetantys-sukciai-gviesiasi-pinigu-ir-duomenu) – ostrzeżenie przed fałszywymi SMS-ami, nazwy nadawcy, prevencija@post.lt
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – opłata manipulacyjna 2 € od 1 listopada 2026 r.
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 3 € i 2 € za pozycję, definicja pozycji
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – co odczytuje i pokazuje Peek
:::
