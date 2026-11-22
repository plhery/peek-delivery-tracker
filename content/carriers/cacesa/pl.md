---
title: Śledzenie CACESA: cło, statusy i kto doręcza paczkę
description: Śledź paczkę CACESA do Hiszpanii lub Portugalii: jaki numer wpisać, co oznaczają statusy, opłaty celne, kto doręcza przesyłkę i kontakt z CACESA.
slug: sledzenie-cacesa
published: 2026-11-22
updated: 2026-11-22
---

CACESA (Compañía Auxiliar al Cargo Express, S.A.) to madrycki agent celny obsługujący zakupy online z Chin do Hiszpanii i Portugalii: odbiera paczki na kilku lotniskach, przeprowadza odprawę celną i przekazuje je firmie odpowiedzialnej za końcowe doręczenie. Sama CACESA nie doręcza przesyłek. Paczkę możesz sprawdzić na [stronie śledzenia CACESA](https://www.cacesa.com/en/tracking), wpisując numer nadany przez sklep, albo wklejając go w pole powyżej.

## Numer śledzenia CACESA: który wpisać

CACESA nie publikuje własnego formatu numeru. Jej wyszukiwarka wymaga podania „Shipment number”, czyli numeru przekazanego wraz z paczką przez sprzedawcę albo chińskiego konsolidatora łączącego zamówienia. Znajdziesz go w e-mailu wysyłkowym lub na stronie zamówienia — CACESA nie ma bezpośredniego kontaktu ze sklepem, w którym kupujesz.

Formaty często spotykane w wyszukiwarce CACESA (cyfry fikcyjne):

| Format | Co oznacza |
| --- | --- |
| `CNES48291736548` | `CNES` i 11 cyfr. |
| `CNESP48291736548291736548` | `CNESP` i 20 cyfr, paczka do Hiszpanii. |
| `CNPRT48291736548291736548` | `CNPRT` i 20 cyfr, paczka do Portugalii. |

Katalog Peek nie przypisuje żadnego z tych formatów bezpośrednio do CACESA. Numery `CNESP` i `CNPRT` należą też do [Ecoscooting](carrier:ecoscooting), firmy doręczającej Cainiao w Hiszpanii i Portugalii, a numer `LP` z 14 cyframi to referencja logistyczna AliExpress śledzona w [Cainiao](carrier:cainiao). Jeśli śledzenie CACESA nie rozpoznaje numeru, sprawdź przewoźnika wymienionego w wiadomości ze sklepu. Więcej przykładów znajdziesz w poradniku [formaty numerów śledzenia](guide:tracking-number-formats).

Na stronie CACESA możesz wpisać kilka numerów naraz. Gdy wyszukiwarka niczego nie znajduje, CACESA zaleca sprawdzić poprawność kodu i zwraca uwagę, że świeże zamówienie może nie być jeszcze w systemie. Starsze przesyłki również znikają ze śledzenia.

Pismo z hiszpańskiego urzędu skarbowego (AEAT) w sprawie zatrzymanej paczki zawiera numer referencyjny „HB”. Służy on do kontaktu z CACESA, a nie do śledzenia przesyłki.

## Statusy śledzenia CACESA

Wyszukiwarka pokazuje komunikaty po hiszpańsku na [stronie hiszpańskiej](https://www.cacesa.com/es/seguimiento) oraz poniższe opisy po angielsku na stronie angielskiej. Każdy wpis ma datę i godzinę pod jednym z nagłówków: Data registered, Warehouse albo Custom Clearance.

| Status CACESA | Co to oznacza dla ciebie |
| --- | --- |
| `Electronic notification of shipment received` | CACESA otrzymała dane przesyłki, ale sama paczka jeszcze nie dotarła. |
| `Arrival Notification` | Zgłoszono przybycie paczki do CACESA. |
| `Inbound warehouse` | Paczka znajduje się w magazynie CACESA. |
| `Start Custom clearance Process` | Rozpoczęła się odprawa celna. |
| `Customs cleared-Green channel` | Urząd celny zwolnił paczkę: może ruszyć w dalszą drogę. |
| `Parcel is going out from Cacesa facilities - More delivery information check with transport company.` | CACESA przekazała paczkę firmie doręczającej. |
| `Shipment not found` | CACESA nie zna tego numeru albo nie wyświetla go już w systemie. |

Zielony kanał (green channel) oznacza zwykłe zwolnienie. [FAQ CACESA](https://www.cacesa.com/en/faqs) wymienia też dwa pozostałe kanały celne: pomarańczowy (kontrola dokumentów) i czerwony (fizyczna kontrola towaru oraz dokumentów). Jeśli urząd celny potrzebuje wyjaśnień lub opłaty, CACESA pisze do odbiorcy e-mailem albo SMS-em.

Wpis o wyjściu z obiektów CACESA jest zwykle ostatnim na jej stronie. Od tego momentu paczką zajmuje się lokalny przewoźnik, do którego CACESA odsyła po szczegóły doręczenia.

## Doręczenie CACESA, nieudane próby i zwroty

CACESA stawia sprawę jasno: to nie ona dostarcza paczkę, lecz firma transportowa wybrana przez sprzedawcę. Ponieważ CACESA nie podaje nazwy tego przewoźnika, sprawdź e-mail wysyłkowy lub stronę zamówienia w sklepie.

Dzień doręczenia, punkty odbioru, automaty paczkowe i ewentualne doręczenia w sobotę zależą wyłącznie od tego przewoźnika — CACESA nie podaje żadnych zasad w tej sprawie. Po wyczerpaniu ustalonej liczby prób doręczenia firma kurierska odsyła paczkę do sprzedawcy (CACESA nie określa liczby prób).

Jeśli paczka była zatrzymana na cle i CACESA prosiła o dokumenty, przekazanie jej dystrybutorowi trwa według CACESA 3 do 4 dni od zwolnienia, plus czas potrzebny na dostawę do twojego kodu pocztowego.

CACESA nie obsługuje zwrotów od osób prywatnych. Zwrot trzeba ustalić ze sklepem albo z firmą, która doręczyła przesyłkę.

## Cło w CACESA: płatności, dokumenty i problemy

**E-mail lub SMS od CACESA.** Wiadomość o paczce oczekującej na odprawę oznacza, że urząd celny wymaga dodatkowych informacji albo zapłaty należności. CACESA prosi o przesłanie na adres atc@cacesa.com następujących danych:

- faktury lub dowodu zakupu (np. zrzutu ekranu strony zamówienia z numerem paczki, artykułem i ceną oraz zdjęcia towaru);
- potwierdzenia płatności (zrzutu ekranu obciążenia karty lub potwierdzenia przelewu);
- informacji, jeśli prowadzisz jednoosobową działalność (autónomo), aby naliczyć hiszpańską dopłatę wyrównawczą VAT (0,5 % przy stawce 4 %, 1,4 % przy 10 %, 5,2 % przy 21 %).

Możesz też przeprowadzić odprawę samodzielnie w serwisie hiszpańskiego urzędu skarbowego (AEAT) — CACESA prosi wtedy o powiadomienie na atc@cacesa.com.

**Płatność opłat celnych.** Na cacesa.com wybierz „Tax payment”, podaj numer przesyłki i kwotę zakupu w euro, aby zobaczyć naliczone cło, podatek i sumę do zapłaty. Zapłacisz kartą (Redsys), przez PayPal lub Bizum. Ponieważ CACESA reguluje należność wobec AEAT w twoim imieniu, nie przysługuje prawo odstąpienia; nadpłaty zwraca obsługa klienta. Nieopłacone paczki wracają do nadawcy.

**Nie chcesz płacić?** Wyślij wypełniony formularz zrzeczenia się towaru (Authorisation of abandonment of goods) na atc@cacesa.com, aby CACESA przeprowadziła z AEAT zwrot paczki do kraju pochodzenia.

**Pismo z urzędu skarbowego (AEAT).** Jeśli dostaniesz zawiadomienie „Decomiso - Depósito temporal” (z numerami HB i AEAT) albo „Validación de datos”, zeskanuj je i wyślij na atc@cacesa.com. CACESA poinformuje, czy paczkę można jeszcze odzyskać, czy została już zniszczona.

Ogólne zasady opisuje poradnik [paczka zatrzymana na cle](guide:customs). Od 1 lipca 2026 roku Unia Europejska pobiera tymczasowe zryczałtowane cło 3 € za każdą zadeklarowaną pozycję towarową (kod HS 6-cyfrowy) w zakupach online do 150 € spoza UE: FAQ CACESA wyjaśnia, że identyczne produkty o tym samym kodzie HS na jednej linii liczone są raz, a jeśli VAT i cło zapłacono już przy zakupie, przy doręczeniu nie ma dalszych opłat. Od 1 listopada 2026 roku obowiązuje też unijna opłata manipulacyjna 2 € za artykuł; strony CACESA nie określają jeszcze, jak jest przenoszona na odbiorcę.

**Opóźnienie, zaginięcie lub uszkodzenie.** W sprawach innych niż sama płatność należności celnych CACESA odsyła do sprzedawcy, z którym zawarto umowę dostawy. Jeśli przesyłka została zwolniona bez zatrzymania i bez kontaktu ze strony CACESA, a nie dotarła, napisz do sklepu. Więcej wskazówek znajdziesz w [śledzenie nie aktualizuje się](guide:tracking-not-updating).

> CACESA ostrzega, że ani ona, ani żadna osoba uprawniona do doręczenia paczki nie zażąda ponownej zapłaty VAT, cła ani innych opłat uiszczonych już przy zakupie. Nie płać i sprawdź sprawę u sprzedawcy lub w CACESA.

## Kontakt z CACESA: telefon i e-mail

CACESA nie ma portugalskiej wersji serwisu ani biura w Portugalii; dla Hiszpanii i Portugalii obowiązują te same kanały kontaktu:

- **E-mail:** atc@cacesa.com w sprawach zatrzymanych paczek, dokumentów, płatności i zrzeczenia się towaru (zaplecze obsługi: postalcustomers@cacesa.com).
- **Telefon:** +34 91 746 1860 (centrala, Calle Golf 23, 28042 Madryt) lub +34 91 333 54 50 (biuro cargo lotniska Madrid-Barajas i hub w Madrycie). CACESA nie podaje godzin pracy ani osobnej infolinii dla odbiorców.
- **Reklamacje** dotyczące usług CACESA: przez [formularz reklamacyjny](https://www.cacesa.com/en/legal-complaints), z numerem przesyłki i powodem zgłoszenia.
- **Gdzie jest paczka i kiedy dotrze:** u sprzedawcy albo u przewoźnika końcowego.

## Śledzenie paczek CACESA w Peek

[Peek](/) śledzi ponad 4500 przewoźników, ale żaden wzorzec numeru nie należy wyłącznie do CACESA, więc Peek nigdy nie wybierze jej automatycznie po samym numerze. Dodaj numer i wybierz CACESA przyciskiem „Zmień” albo wklej link śledzenia z cacesa.com (`/en/tracking` lub `/es/seguimiento` z parametrem `busqueda=`), który od razu przypisuje CACESA do dowolnego numeru. Numery `CNESP` i `CNPRT` automatycznie wskazują [Ecoscooting](carrier:ecoscooting).

Peek wyświetla angielskie komunikaty CACESA w oryginalnym brzmieniu, odczytuje godziny według czasu madryckiego i przelicza je na twoją strefę czasową (na dole historii widnieje napis „Godziny w twojej strefie czasowej:” wraz z jej nazwą). Wpis o doręczeniu pojawia się jako doręczony, `Start Custom clearance Process` jako etap celny, a zwolnienie na zielonym kanale jako dalszy transport; Peek nie pokazuje jednak kwot należności celnych i nie umożliwia ich opłacenia. Ponieważ CACESA nie przekazuje lokalizacji, brakuje punktów na mapie, a także przewidywanej daty doręczenia, wagi, nadawcy i odbiorcy.

CACESA nie podaje nazwy firmy doręczającej, więc paczka jedzie dalej pod tym samym numerem. Peek może przejąć dalsze śledzenie tylko wtedy, gdy numer jednoznacznie pasuje do innego przewoźnika: [Ecoscooting](carrier:ecoscooting) dla `CNESP` i `CNPRT`, [Correos](carrier:correos-spain) dla numeru pocztowego kończącego się na `ES`, [CTT](carrier:ctt) dla końcówki `PT` lub [CTT Express](carrier:ctt-express) dla własnych numerów tej firmy. Numery `CNES` i `LP` nie są przekazywane dalej.

Gdy masz aplikację Peek otwartą na ekranie — albo w dniu doręczenia z włączonymi powiadomieniami — Peek odpytuje CACESA bezpośrednio nawet co 10 minut (co 15 minut przez zewnętrzne serwisy śledzące). W pozostałym czasie oraz po dwóch dniach bez zmian (lub po 12 godzinach, gdy przesyłka ma jeszcze status „Zarejestrowana” albo „Przyjęta przez przewoźnika”) sprawdza co godzinę. Możesz wybrać powiadomienia dla opcji „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Po czterech dniach bez nowego skanu Peek pokazuje „Brak aktualizacji od czterech dni” oraz „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika CACESA.” z linkiem „Poproś przewoźnika CACESA o pomoc” prowadzącym do FAQ.

## Pytania o śledzenie CACESA

### Czym jest CACESA Postal?

CACESA, nazywana też Cacesa Postal lub CACESA Logistics, to madrycki agent celny odprawiający zamówienia online z Chin do Hiszpanii i Portugalii. Firmę założyła Iberia w 1987 roku, a w 2025 roku przejęła ją portugalska poczta CTT.

### Kto doręcza paczkę z CACESA?

Firma transportowa wybrana przez sprzedawcę, a nie CACESA. Jej nazwę znajdziesz w e-mailu ze sklepu lub na stronie zamówienia — CACESA jej nie podaje.

### Czy CACESA obsługuje cło dla AliExpress?

Strony CACESA nie wymieniają konkretnych sklepów. Firma wyjaśnia, że otrzymuje paczki od podmiotów konsolidujących zamówienia w Chinach i odprawia je w imieniu odbiorców, bez bezpośredniego kontaktu ze sklepami internetowymi.

### Ile idzie paczka CACESA po odprawie celnej?

W przypadku zatrzymanej paczki CACESA potrzebuje 3 do 4 dni od zwolnienia do przekazania jej kurierowi, do czego dochodzi czas doręczenia na twój kod pocztowy. Dla paczek niezatrzymanych CACESA nie podaje terminu.

:::sources
- [CACESA: Shipment tracking](https://www.cacesa.com/en/tracking) – wyszukiwarka, numer przesyłki, samodzielna odprawa, odesłanie do przewoźnika
- [CACESA: Seguimiento de envíos](https://www.cacesa.com/es/seguimiento) – wyszukiwarka po hiszpańsku
- [CACESA: Faqs](https://www.cacesa.com/en/faqs) – rola agenta, brak własnych doręczeń, kanały celne, dokumenty, 3–4 dni, zwroty, pisma AEAT, zrzeczenie się, atc@cacesa.com
- [CACESA: Facilities](https://www.cacesa.com/en/offices) – numery telefonu, oddziały, brak biura w Portugalii
- [CACESA: Legal Warning](https://www.cacesa.com/en/legal-warning) – płatności (Redsys, PayPal, Bizum), brak prawa odstąpienia, zwrot bez opłaty, reklamacje do sprzedawcy
- [CACESA: Complaints](https://www.cacesa.com/en/legal-complaints) – formularz reklamacyjny
- [CACESA: Quiénes somos](https://www.cacesa.com/es/quienes-somos) – pełna nazwa, założenie w 1987 roku przez Iberię
- [CACESA: teksty aplikacji webowej](https://www.cacesa.com/main.40b62b43302d800a.js) – komunikaty śledzenia, kroki płatności, FAQ o unijnej opłacie 3 €, ostrzeżenie przed oszustwami
- [ECO: wyniki CTT do września 2025](https://eco.sapo.pt/2025/10/30/lucro-dos-ctt-sobe-184-para-328-milhoes-ate-setembro-com-integracao-da-espanhola-cacesa/) – przejęcie CACESA przez CTT w 2025 roku
- [Komisja Europejska: reforma celna UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – tymczasowe cło 3 € (od 1 lipca 2026) i opłata manipulacyjna 2 € (od 1 listopada 2026)
- [Universal Parcel Scraper: adapter CACESA](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/cacesa) – zakres danych odczytywanych przez Peek i reguły wykrywania
:::
