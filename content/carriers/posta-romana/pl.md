---
title: Śledzenie Poșta Română: numery RO, statusy i cło
description: Śledź przesyłkę Poșta Română: format RO, rumuńskie statusy po polsku, paczki z zagranicy, opłaty celne, reklamacje i kontakt.
slug: sledzenie-posta-romana
published: 2026-11-23
updated: 2026-11-23
---

Poșta Română (Compania Națională Poșta Română, CNPR) świadczy powszechne usługi pocztowe w Rumunii i doręcza przesyłki do każdego miasta i wsi w kraju. Śledź przesyłkę na jej stronie [Track & Trace](https://www.posta-romana.ro/en/track-trace.html) albo wklej numer w polu powyżej. Każda poczta podaje informacje tylko ze swojego odcinka trasy, więc śledzenie Poșta Română obejmuje wyłącznie Rumunię: paczka z zagranicznego sklepu najpierw pojawia się u poczty nadania, a w Poșta Română dopiero po dotarciu do Rumunii. Nie możesz znaleźć numeru? Zobacz [jak znaleźć numer śledzenia](guide:find-tracking-number).

## Numer przesyłki Poșta Română: jak wygląda

Kod śledzenia Poșta Română ma 13 znaków: dwie litery, dziewięć cyfr i dwie litery, bez spacji. Jej własne kody kończą się na `RO`. To [międzynarodowy format pocztowy](guide:tracking-number-formats), więc numer innej poczty wygląda tak samo, tylko z kodem tamtego kraju na końcu. Ostatnia z dziewięciu cyfr to cyfra kontrolna. Z zmyślonymi cyframi:

| Format (zmyślony) | Co to jest |
| --- | --- |
| `CP482917367RO` | Przesyłka nadana z numerem Poșta Română, w Rumunii lub za granicę. Dwie pierwsze litery zależą od usługi; liczy się `RO` na końcu. |
| `RR739402812CN` | Kończy się innymi literami, tu `CN`: numer nadała poczta innego kraju, tutaj [China Post](carrier:china-post). Paczka przychodząca do Rumunii z zagranicy ma zwykle taki numer. |

Poșta Română śledzi przesyłki polecone i ubezpieczone, paczki, paczki pobraniowe, EMS oraz druki polecone. Zwykłego listu nie można śledzić.

## Wyjaśnienie statusów śledzenia Poșta Română

Peek odczytuje każdy etap po rumuńsku, zwykle bez znaków diakrytycznych, wraz z placówką lub węzłem, który zeskanował przesyłkę. Komunikaty rozpoznawane przez Peek, mniej więcej w kolejności:

| Komunikat Poșta Română | Co to oznacza dla ciebie |
| --- | --- |
| `Primit de la expeditor` | Przyjęta od nadawcy: Poșta Română ma przesyłkę. |
| `In prelucrare` | W opracowaniu we wskazanym miejscu: na poczcie, w centrum regionalnym albo w międzynarodowym urzędzie wymiany w Bukareszcie. |
| `In transport` | Opuściła wskazane miejsce i jest w drodze. |
| `Sosit in Romania` | Dotarła do Rumunii z zagranicy, do międzynarodowego urzędu wymiany. |
| `Expediat din Romania` | Wysłana za granicę. Tu kończy się śledzenie Poșta Română. |
| `Programat pentru predare incepand cu aceasta data` | Gotowa do odbioru od tej daty we wskazanej placówce pocztowej. Odbierz ją tam. |
| `Predat la destinatar` | Wydana odbiorcy: doręczona. |
| `Returnat catre expeditor` | Wraca do nadawcy. |

Peek przypisuje te komunikaty do własnych etapów, takich jak „W transporcie” czy „Do odbioru”, i rozpoznaje także zapis ze znakami diakrytycznymi. Doręczenie po `Returnat catre expeditor` oznacza powrót do nadawcy, więc Peek pokazuje „Zwrócona nadawcy”. Każdy inny wiersz Peek interpretuje według ogólnych zasad, a nieznany wyświetla bez zmiany i bez etapu. Więcej w [co oznaczają statusy przesyłek](guide:tracking-statuses).

## Odbiór paczki Poșta Română w Rumunii

**Czas dostawy.** Ogólne warunki Poșta Română określają standard Z+5 dla doręczenia na adres w Rumunii paczki do 20 kg nadanej z zagranicy: pięć dni od wejścia do sieci Poșta Română. Przy paczkach spoza UE dochodzi czas odprawy celnej. Zobacz [czasy dostawy](guide:delivery-times).

**Odbiór.** Gdy paczka czeka na poczcie, śledzenie pokazuje `Programat pentru predare incepand cu aceasta data` wraz z placówką. Poșta Română przechowuje ją tam co najmniej 5 dni kalendarzowych od dnia powiadomienia. Jeśli leży w placówce z obsługą celną, skontaktuj się z nią przed wizytą. Zobacz [nieudane doręczenie](guide:missed-delivery) i [nieodebrane paczki](guide:uncollected-parcel).

**Cło dla paczek spoza UE.** Rumunia należy do UE, więc paczka z Chin, Wielkiej Brytanii czy USA przechodzi odprawę celną, a każda przesyłka z towarami wymaga elektronicznego zgłoszenia celnego, także prezenty. Poșta Română zgłasza za ciebie zakupy internetowe do 150 € jako przedstawiciel i pobiera podstawowy VAT 21%, chyba że sprzedawca pobrał go przez IOSS i potwierdzają to dane przesłane z paczką. Od 1 lipca 2026 roku towary kupione online spoza UE w zamówieniach do 150 € podlegają unijnemu cłu 3 € za pozycję, o czym informuje też strona celna Poșta Română. Od 1 listopada 2026 roku dochodzi unijna opłata manipulacyjna 2 € za pozycję niezależnie od wartości. Pozycja obejmuje towary w paczce o tej samej klasyfikacji taryfowej, opisie oraz — gdy wymagane — pochodzeniu.

Poșta Română pobiera opłatę za przedstawienie do odprawy 18,00 lei od takich przesyłek niskiej wartości, włącznie z prezentami i paczkami IOSS. Jeśli urząd celny wybierze paczkę do kontroli ryzyka, otrzymujesz wezwanie o dokumenty, a opłata za obsługę celną wynosi wówczas 35,00 lei za paczkę. Do formalności celnych potrzebujesz numeru EORI, nawet jako osoba prywatna odbierająca wymagający ich prezent. Podatki importowe płacisz gotówką albo kartą na pocztach z obsługą celną mających terminal. Więcej w [paczki zatrzymane na cle](guide:customs).

**Opóźnienia i zwroty na cle.** Poșta Română obsługuje te paczki w Bukareszcie na podstawie danych elektronicznych przesłanych z wyprzedzeniem przez pocztę nadania. Brakujące lub niepełne dane opóźniają paczkę; bez nich albo bez prawidłowych formularzy celnych CN22/CN23 paczka wraca do nadawcy. Zakup z [AliExpress](guide:aliexpress-orders), [Temu](guide:temu-orders) lub [Shein](guide:shein-orders)? Zobacz [śledzenie z Chin](guide:tracking-from-china).

## Przesyłki Poșta Română za granicę

Śledzenie Poșta Română obejmuje przesyłkę do opuszczenia Rumunii i nie pokazuje jej dalszej drogi w innych krajach. Po `Expediat din Romania` użyj śledzenia poczty kraju docelowego z tym samym numerem `RO`, np. [USPS](carrier:usps), [Royal Mail](carrier:royal-mail), An Post, [Canada Post](carrier:canada-post), [Australia Post](carrier:australia-post), [Poste Italiane](carrier:poste-italiane) lub [Correos](carrier:correos-spain).

## Śledzenie Poșta Română stoi w miejscu, zaginięcie lub zwrot

**Brak nowego kroku.** System obejmuje tylko Rumunię: przesyłka wychodząca zatrzymuje się na `Expediat din Romania`, a przychodząca nie pojawia się, dopóki nie dotrze do Rumunii. Paczka spoza UE może też czekać na cle. Zobacz [dlaczego śledzenie przestaje się aktualizować](guide:tracking-not-updating).

**Zaginięcie, opóźnienie lub uszkodzenie.** Nadawca lub odbiorca może złożyć reklamację w sposób opisany niżej w ciągu 6 miesięcy od nadania; przy przesyłkach międzynarodowych 6 miesięcy liczy się od dnia po nadaniu, a dla przesyłek ekspresowych jak EMS termin wynosi 1 do 4 miesięcy. Przygotuj numer przesyłki i datę nadania. Poșta Română odpowiada w ciągu 3 miesięcy, łącznie z odszkodowaniem. Odszkodowania za zaginioną lub uszkodzoną przesyłkę poleconą możesz też zażądać w dowolnej placówce pocztowej na podstawie dowodu nadania; przesyłki nierejestrowane nie są objęte ochroną. Dla przesyłek międzynarodowych odszkodowanie wynosi do 30 SDR za polecone dokumenty i do 40 SDR za paczkę plus 4,5 SDR za kg dla paczek standardowych; przesyłka ubezpieczona jest chroniona do zadeklarowanej wartości. Zakup online? Powiadom też sklep: zobacz [zaginione paczki](guide:lost-parcel) i [uszkodzone paczki](guide:damaged-parcel).

**Doręczona, ale nie dotarła.** Złóż reklamację jak wyżej: formularz międzynarodowy wymienia niedoręczoną przesyłkę i brak zawartości wśród powodów. Zobacz [doręczona, ale nie otrzymana](guide:delivered-not-received).

**Zwrócona.** `Returnat catre expeditor` oznacza, że przesyłka wraca do nadawcy. Peek pokazuje ją jako „Zwrócona nadawcy”: zobacz [zwrot do nadawcy](guide:returned-to-sender).

## Obsługa klienta i kontakt z Poșta Română

Kanały kontaktu Poșta Română:

- **Telefon:** 021 9393, od poniedziałku do piątku 8:00–20:00 czasu rumuńskiego. Opłata jak za zwykłe połączenie.
- **E-mail:** suportclienti@ro.post.
- **Reklamacje:** online przez formularz krajowy lub [formularz reklamacji międzynarodowej](https://www.posta-romana.ro/en/reclamatii-externe.html), w okienku pocztowym lub pocztą za potwierdzeniem odbioru. Poșta Română nie przyjmuje reklamacji telefonicznie ani przez ogólny formularz wsparcia.

Poșta Română nie prosi o dane bankowe, numery kart ani kody PIN ani o płatność online za opłaty doręczeniowe. Zobacz [fałszywe SMS-y o paczkach](guide:delivery-scam-texts).

## Śledzenie paczki Poșta Română w Peek

Ponieważ każda poczta używa tego samego 13-znakowego formatu, [Peek](/) nie rozpoznaje Poșta Română po samym numerze `RO`. Wklej go mimo to: Peek śledzi ponad 4 500 przewoźników. Pokazuje „Sprawdzanie usług śledzenia…”, pytając kompatybilnych przewoźników — Poșta Română jako pierwszą — i wyświetla „Poșta Română ma tę przesyłkę.”, jeśli ją zna. Jeśli jeszcze nie, zapisuje ją jako nieznanego przewoźnika pocztowego i pyta usługi śledzenia oraz pocztę kraju docelowego, gdy któraś go wskaże; przez pierwsze 30 dni ponownie pyta Poșta Română i przenosi tam paczkę, gdy ta pokaże ruch. Możesz też wybrać Poșta Română przyciskiem „Zmień”. Przy błędnej cyfrze kontrolnej Peek mówi „Cyfra kontrolna tego numeru się nie zgadza.”: sprawdź numer albo wybierz „Śledź tak, jak wpisano”. Działa również link do śledzenia Poșta Română: Peek wyciąga z niego numer. Zapisana pod Poșta Română paczka pokazuje „Oczekiwanie na przewoźnika Poșta Română” do pierwszego skanu.

Peek odczytuje własne śledzenie Poșta Română. Pokazuje każdy etap w oryginalnym rumuńskim brzmieniu z podanym miejscem oraz placówkę, w której paczka czeka, jako „Punkt odbioru”, a po odbiorze jako „Odebrano w”. Każdy skan odczytuje w czasie rumuńskim, bo śledzenie obejmuje wyłącznie Rumunię. Pomija kraj nadania, datę wysyłki i godziny otwarcia placówki. Wiersz `Programat pentru predare incepand cu aceasta data` z przyszłą datą, np. na kolejny dzień, pojawia się w Peek, gdy nadejdzie ten czas. Śledzenie nie podaje przewidywanego terminu doręczenia, wagi ani nazwisk, więc Peek ich nie pokazuje. Przycisk „Otwórz stronę przewoźnika Poșta Română” otwiera numer w jej wyszukiwarce. Jeśli wybierzesz Poșta Română dla numeru, którego nie może wyszukać, Peek pokazuje „nie wydaje takich numerów”.

Gdy śledzisz numer `RO` spoza Rumunii, Peek pyta pod tym samym numerem także pocztę twojego kraju: USPS w USA, Royal Mail w Wielkiej Brytanii ([Parcelforce](carrier:parcelforce) dla numerów na E), An Post w Irlandii, Canada Post, Australia Post, NZ Post, [DHL](carrier:dhl) w Niemczech, [La Poste](carrier:la-poste) we Francji, Israel Post w Izraelu oraz przewoźnika w pozostałych krajach UE poza Holandią, a także w Szwajcarii i Norwegii. Przełącza się na tę pocztę, gdy pokaże skan co najmniej tak świeży jak najnowszy w Poșta Română, zachowując historię Poșta Română. Z Rumunii Peek nie pyta innej poczty, bo Poșta Română nie wskazuje kraju docelowego; z Mołdawii, Turcji ani ZEA nie zna żadnej, którą mógłby zapytać. W drugą stronę, gdy inna poczta wskazuje Rumunię jako cel — albo nie wskazuje celu, a śledzisz z Rumunii — Peek pyta Poșta Română pod jej numerem.

Dopóki masz paczkę otwartą w Peek albo w dniu doręczenia przy włączonych powiadomieniach, Peek odpytuje Poșta Română bezpośrednio nawet co 10 minut, dopóki odpowiada jej wyszukiwarka. Po dwóch dniach bez nowych wpisów (po 12 godzinach, gdy przesyłka nadal ma status „Zarejestrowana” lub „Przyjęta przez przewoźnika”) sprawdza co godzinę; po siedmiu dniach bez nowych wpisów w statusie „Zarejestrowana” — co 6 godzin. Po czterech dniach bez skanu dla paczki w statusie „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie” Peek wyświetla „Brak aktualizacji od …”, informację „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika Poșta Română.” oraz link „Poproś przewoźnika Poșta Română o pomoc”. Powiadomienia: „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek nie może skontaktować się za ciebie z Poșta Română, twoją pocztą ani ze sklepem.

## Pytania o śledzenie Poșta Română

### Jak śledzić paczkę Poșta Română?

Wpisz 13-znakowy kod na stronie Track & Trace Poșta Română albo wklej go do Peek. Dla paczki z zagranicy użyj numeru od nadawcy lub sklepu: Poșta Română pokaże ją po dotarciu do Rumunii.

### Dlaczego moje śledzenie Poșta Română się nie aktualizuje?

Jeśli ostatni wpis to `Expediat din Romania`, przesyłka opuściła Rumunię, a system Poșta Română obejmuje tylko teren kraju: śledź ją na stronie poczty kraju docelowego pod tym samym numerem. Paczka spoza UE może też czekać na cle.

### Jak długo Poșta Română przechowuje paczkę na poczcie?

Co najmniej 5 dni kalendarzowych od dnia powiadomienia. Odbierz ją w placówce wskazanej w śledzeniu.

### Czy płacę cło od paczki spoza UE?

Przy zakupach online tak. Zamówienia do 150 € objęte są cłem 3 € za pozycję, a każdy zakup online unijną opłatą manipulacyjną 2 € za pozycję, niezależnie od wartości. Poșta Română pobiera też 21% VAT (chyba że sprzedawca pobrał go przez IOSS) oraz 18,00 lei za przedstawienie do odprawy.

### Jaki jest numer obsługi klienta Poșta Română?

021 9393, od poniedziałku do piątku 8:00–20:00 czasu rumuńskiego, albo e-mail suportclienti@ro.post. Poșta Română nie przyjmuje reklamacji telefonicznie.

:::sources
- [Poșta Română: Track & Trace](https://www.posta-romana.ro/en/track-trace.html) – 13-znakowy kod, śledzenie wyłącznie w Rumunii, śledzenie poczty docelowej, formularze reklamacyjne, kontakt, ostrzeżenie przed oszustwami
- [Poșta Română: Help](https://www.posta-romana.ro/en/help.html) – format kodu, usługi śledzone, listy zwykłe, odszkodowanie na każdej poczcie, przesyłki nierejestrowane, zakres poszczególnych poczt
- [Poșta Română: Ajutor](https://www.posta-romana.ro/ajutor.html) – zgłoszenia celne, cło 3 €, VAT 21%, opłaty 18,00 i 35,00 lei, EORI, urząd wymiany, zwroty bez danych, płatność, samodzielne zgłoszenie
- [Poșta Română: Modificări vamale începând cu 01.07.2026](https://www.posta-romana.ro/a2050/servicii-destinatari/informatii-utile/intrebari-frecvente/modificari-vamale-incepand-cu-01072026.html) – cło 3 € za pozycję od 1 lipca 2026, EORI dla prezentów
- [Poșta Română: Termeni și condiții](https://www.posta-romana.ro/a119/termeni-de-utilizare/termeni-si-conditii.html) – usługa powszechna, doręczenia w całej Rumunii, Z+5, 5 dni przechowywania, odpowiedzialność, zasady reklamacji
- [Poșta Română: International complaint](https://www.posta-romana.ro/en/reclamatii-externe.html) – kto może reklamować, powody reklamacji
- [Poșta Română: Contact info](https://www.posta-romana.ro/en/a847/about-us/contact-info.html) – telefon, e-mail, godziny
- [Komisja Europejska: reforma celna UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – cło 3 €, opłata manipulacyjna 2 €
- [Komisja Europejska: wytyczne w sprawie cła 3 € i unijnej opłaty manipulacyjnej (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – stawka za pozycję, definicja pozycji
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – treść skanów, co odczytuje Peek, którą pocztę pyta dalej
:::
