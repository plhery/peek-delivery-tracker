---
title: Śledzenie przesyłek Straightship: numery STRTD i doręczenia
description: Śledź paczkę Straightship w Kanadzie: formaty STRTD, STRCA i STSH, znaczenie statusów, odprawa celna CBSA, zamówienia SHEIN i kontakt z obsługą.
slug: sledzenie-przesylek-straightship
published: 2026-11-18
updated: 2026-11-20
---

Straightship (Straightship Solutions Limited) to firma logistyczna z siedzibą w Burnaby w Kolumbii Brytyjskiej (**Kanada**), która przewozi towary z Azji do Ameryki Północnej i doręcza paczki w całej Kanadzie własną flotą kierowców. Na swojej stronie wymienia SHEIN wśród partnerów. Jeśli zamówienie internetowe ma numer zaczynający się od `STRTD` lub `STRCA`, obsługuje je Straightship; numer z przedrostkiem `STSH` również może należeć do tego przewoźnika. Przesyłkę sprawdzisz na [straightship.com](https://www.straightship.com/new/) w polu „Track Your Shipment” albo wklejając numer w wyszukiwarce powyżej. Kod udostępnia sklep w wiadomości o wysyłce lub w szczegółach zamówienia.

## Numer śledzenia Straightship: jak wygląda

Straightship nie publikuje zestawienia formatów swoich numerów. Kody rozpoznawane przez Peek mają następujące układy (poniżej z przykładowymi cyframi):

| Numer | Co oznacza format |
| --- | --- |
| `STRTD482910573` | `STRTD` i 9 cyfr. |
| `STRTD4829105736491827` | `STRTD` i 16 cyfr (występują też numery z 17 cyframi). |
| `STRCA4829105736491` | `STRCA` i 13 cyfr. |
| `STSHYW48291057364` | `STSH`, dwie litery i 11 cyfr. |

Małe litery i spacje nie mają znaczenia w Peek. Gdy wyszukiwarka Straightship nie ma numeru w bazie, zwraca komunikat `No results found!`: porównaj wtedy kod z potwierdzeniem wysyłki ze sklepu. Nie możesz nigdzie znaleźć numeru? Zobacz [jak znaleźć numer śledzenia](guide:find-tracking-number), a w przypadku zakupów na SHEIN sprawdź [śledzenie zamówień SHEIN](guide:shein-orders).

## Statusy śledzenia Straightship i ich znaczenie

Wyszukiwarka Straightship działa po angielsku. U góry pokazuje podsumowanie z polem „Package Status”, a poniżej historię z godziną, treścią i miejscowością każdego skanu. Oto typowe komunikaty w kolejności, w jakiej przechodzi przez nie paczka w **Kanadzie**:

| Komunikat Straightship | Co oznacza dla Twojej paczki |
| --- | --- |
| `Package information is created in the system` | Sklep wygenerował etykietę. Straightship nie ma jeszcze fizycznie paczki: jest nadal u sprzedawcy albo w transporcie z Azji. |
| `Package Received with Origin Scan warehouse` | Pierwszy skan Straightship w Kanadzie, z podaniem miasta i prowincji. |
| `Package is received at a Straightship station` | Przyjęcie w jednym z oddziałów Straightship (w starszych zapisach pojawia się `Arrived at Facility`). |
| `Package arrived at transit station` | W stacji tranzytowej w drodze do Twojego lokalnego oddziału. |
| `Package is in transit to a Straightship station` | W trasie między oddziałami przed dotarciem do stacji doręczającej. |
| `Loading` | Załadunek w oddziale przed wyjazdem na rejon; bywa zapisany dwukrotnie w odstępie kilku sekund. |
| `Package will be delivered before 9:00pm` | W doręczeniu dzisiaj: paczka zostanie doręczona lub kurier podejmie próbę jeszcze tego samego dnia. |
| `Attempted Delivery` | Kierowca podjechał pod adres, ale nie mógł doręczyć przesyłki. |
| `Shipment delayed` | Opóźnienie bez szczegółowego powodu w systemie śledzenia. |
| `Delivered` | Doręczona, z miastem i prowincją (w Peek pokazywane jako „Dostarczona”). |
| `Package is being returned to shipper` | W drodze powrotnej do nadawcy. |

Dwa komunikaty po nieudanej próbie mówią od razu, co zrobić: `An attempt was made to deliver your package-consignee closed (please e-mail SS@straightship.com to make arrangement)` oznacza, że miejsce doręczenia było zamknięte; to samo zdanie z frazą `unable to leave package at safe location` zamiast `consignee closed` informuje, że kurier nie znalazł bezpiecznego miejsca na pozostawienie paczki. Oba proszą o kontakt mailowy na adres `SS@straightship.com` w celu ustalenia ponownego doręczenia.

Trzy szczegóły często wprowadzają w błąd:

- **Podsumowanie spóźnia się względem historii.** Pole „Package Status” potrafi nadal wyświetlać `ReadyForShipping` (lub `Ready For Shipping`), mimo że paczka przemieszcza się już między magazynami. Zawsze patrz na najnowszy wiersz w tabeli na dole; kolejne wartości podsumowania to `Shipped`, `Delivered` i `ReturnedToSender`.
- **„In progress” nie oznacza wyjazdu kuriera.** `Delivery is in progress` oraz `Your order is in Progress` pojawiają się po niedoszłym doręczeniu, czasem kilka dni później i przed zwrotem do nadawcy. Napisz do Straightship, jeśli je zobaczysz.
- **Godziny są podawane w UTC.** Pod tabelą historii wyszukiwarka zaznacza, że wszystkie czasy są zapisane w uniwersalnym czasie koordynowanym (UTC): wieczorny skan w Kanadzie ma już datę następnego dnia kalendarzowego.

Przesyłka skierowana do zwrotu potrafi jeszcze zostać ponownie zeskanowana w oddziale i wyjechać na kolejną próbę doręczenia: `Package is being returned to shipper` nie zawsze kończy trasę paczki.

## Doręczenia Straightship w Kanadzie: kto dostarcza paczkę

Wyszukiwarka Straightship obsługuje **Kanadę** (jej nagłówek brzmi „Canada B2C Shipment Tracking”). Straightship podaje, że jego własna sieć obejmuje ponad 95% populacji Kanady oraz 90% kanadyjskich kodów pocztowych dzięki ponad 85 centrom dystrybucyjnym, a komunikat o wydaniu do doręczenia zapowiada wizytę przed 21:00 (`before 9:00pm`). Firma doręcza też w **Kanadzie** przesyłki dla chińskiego operatora [JS Express](carrier:js-express): śledzenie JS Express powtarza kanadyjskie skany Straightship, a gdy rekord JS Express zawiera numer `STRTD` lub `STRCA`, Peek odpytuje również bezpośrednio Straightship.

Dla paczek sprowadzanych do **Kanady** ze Stanów Zjednoczonych Straightship wymienia na angielskiej podstronie usług kilka opcji ostatniej mili — własną sieć, Canada Post, Purolator lub [UPS](carrier:ups) — natomiast wersja chińska wylicza własną sieć, Canada Post, [FedEx](carrier:fedex) lub UPS. Przewoźnik deklaruje w tej usłudze doręczenie w 6–10 dni oraz odprawę celną w 24 godziny. Jego wyszukiwarka pokazuje wyłącznie oddziały Straightship i nie podaje nazwy innego doręczyciela ani drugiego numeru przesyłki.

W **Kanadzie** Straightship nie publikuje liczby prób doręczenia, informacji o papierowym awizie ani czasu przechowywania nieodebranej paczki w magazynie:

- **Po nieudanej próbie doręczenia** wyślij wiadomość na `SS@straightship.com` ze swoim numerem śledzenia, zgodnie z instrukcją ze skanu.
- **Aby zmienić adres lub numer telefonu**, skorzystaj z formularza kontaktowego i wybierz temat „Address and/or contact details update”.
- **Potwierdzenie doręczenia:** strona główna informuje, że wyszukiwarka pokazuje aktualny status oraz zdjęcie z doręczenia, o ile jest dostępne („POD image (Where available)”).

## Odprawa celna w Kanadzie dla paczki Straightship z Azji

W **Kanadzie** Straightship przeprowadza odprawę celną na rzecz firm nadających, a jego chińska strona z usługami podaje, że w Kanadzie może pobierać podatki (`DDU`). Jeśli ktoś prosi Cię o dopłatę za paczkę Straightship, zapytaj najpierw sprzedawcę: centrum pomocy SHEIN Canada potwierdza, że wszystkie zamówienia wysyłane do **Kanady** realizowane są w formule opłaconych należności (`DDP`, *delivered duty paid*), bez dodatkowych kosztów celnych przy odbiorze. Zewnętrzne serwisy śledzenia mogą pokazać `Customs Released` przed pierwszym skanem w Kanadzie (czego sama witryna Straightship nie wyświetla), a także `Customs Held For Examination` podczas rewizji lub `Customs Rejected`, które nie zawsze oznacza ostateczną odmowę: w jednej z przesyłek obserwowanych przez Peek urząd celny skontrolował paczkę i zwolnił ją dwa dni później. Peek oznacza oba te komunikaty jako „W urzędzie celnym”; jeśli któryś utrzymuje się przez kilka dni, powiadom sprzedawcę.

Wysokość należności w **Kanadzie** zależy od wartości towaru i kraju wysyłki zgodnie z progami kurierskimi Kanadyjskiej Agencji Służb Granicznych (CBSA): w przesyłkach kurierskich o wartości do 20 CAD z Chin lub dowolnego kraju spoza USA i Meksyku nie płaci się cła ani podatków, a powyżej 20 CAD naliczane są oba obciążenia. Z USA lub Meksyku zwolnione z opłat są paczki do 40 CAD, między 40,01 CAD a 150 CAD obowiązują same podatki, a powyżej 150 CAD zarówno cło, jak i podatki (z wyjątkiem towarów objętych odrębnymi przepisami, takich jak alkohol czy tytoń).

W przypadku przesyłek kurierskich do **Kanady** CBSA upoważnia kwalifikowanych kurierów do rozliczania ceł i podatków w imieniu odbiorcy: jak wyjaśnia CBSA, firma kurierska kontaktuje się z Tobą przed doręczeniem w celu uregulowania należności i wstrzymuje wydanie paczki do czasu zapłaty, przy czym opłaty agencji lub kuriera za obsługę celną są niezależne od ceł i podatków i nie podlegają kontroli CBSA. Jednocześnie CBSA nigdy nie dzwoni ani nie wysyła SMS-ów czy e-maili z prośbą o numer ubezpieczenia społecznego (`SIN`) ani numer karty kredytowej: każda taka wiadomość jest próbą oszustwa. Więcej w poradniku [paczka zatrzymana w urzędzie celnym](guide:customs).

## Paczka Straightship nie dociera, zaginęła lub wraca do nadawcy

**Kto składa reklamację.** Klientami Straightship są sklepy i platformy sprzedażowe, a przewoźnik nie udostępnia procedury odszkodowawczej dla odbiorców indywidualnych. O zwrot pieniędzy lub ponowną wysyłkę zwróć się do sklepu (dla zamówień z SHEIN zobacz [śledzenie zamówień SHEIN](guide:shein-orders)).

**Zatrzymana przed Kanadą.** Sam wpis `Package information is created in the system` bez kolejnych skanów oznacza, że paczka nie trafiła jeszcze do magazynu Straightship w Kanadzie i może nadal być w transporcie z Azji. Zapytaj sklep o datę nadania i zajrzyj do poradnika [śledzenie paczek z Chin](guide:tracking-from-china).

**Brak ruchu w Kanadzie.** Jeśli od ostatniego skanu w oddziale minęło kilka dni bez zmian albo widnieje `Shipment delayed`, wyślij formularz kontaktowy Straightship z tematem „Delivery Status Enquiry”. Zobacz też [dlaczego śledzenie się nie aktualizuje](guide:tracking-not-updating).

**Oznaczona jako dostarczona, ale jej nie ma.** Sprawdź najpierw w wyszukiwarce Straightship, czy dołączono zdjęcie z doręczenia (nie zawsze jest dostępne), oraz zapytaj sąsiadów, recepcję budynku i obejrzyj osłonięte miejsca przy drzwiach. W **Kanadzie** SHEIN zaznacza ponadto, że w rzadkich przypadkach paczka może otrzymać status doręczonej do 48 godzin przed faktycznym dostarczeniem; po tym czasie skorzystaj z opcji „Delivered Order Not Received” w formularzu Straightship, a przy zakupie z SHEIN kliknij przycisk „Parcel Delivered, Not Received” w zakładce „My orders”, aby rozpocząć wniosek o zwrot środków. Zobacz [dostarczona, ale nieotrzymana](guide:delivered-not-received).

**Zwrócona do nadawcy.** `Package is being returned to shipper` informuje, że przesyłka wraca do sprzedawcy: poproś sklep o zwrot pieniędzy lub ponowną wysyłkę i przeczytaj [zwrot do nadawcy](guide:returned-to-sender).

## Numer telefonu i obsługa klienta Straightship

Na oficjalnej stronie Straightship podaje następujące dane kontaktowe:

- **Telefon w Kanadzie:** `+1 (800) 452-3304`, od poniedziałku do piątku w godz. 08:00–21:00 oraz w soboty w godz. 08:00–16:00 czasu wschodniego (`EST`); w stopce strony głównej widnieje również numer `+31 800 0200244` dla Holandii (`NL`).
- **E-mail:** `SS@straightship.com`, podawany w powiadomieniach po nieudanej próbie doręczenia.
- **Formularz kontaktowy:** na [stronie kontaktowej Straightship](https://www.straightship.com/new/contact.html), gdzie wpisanie numeru śledzenia jest obowiązkowe. Straightship prosi o uwzględnienie 2 dni roboczych na odpowiedź.

W sprawach samego zamówienia, zwrotów towaru lub refundacji kontaktuj się bezpośrednio ze sprzedawcą.

## Śledzenie przesyłki Straightship w Peek

[Peek](/) rozpoznaje Straightship od razu po numerach `STRTD` i `STRCA` w powyższych układach, których nie używa żaden inny przewoźnik z jego katalogu. Przy numerze `STSH` sam kształt nie wystarcza, ponieważ pokrywa się z niektórymi numerami Colis Privé: Peek pyta wtedy Straightship, czy zna przesyłkę, i wybiera go po twierdzącej odpowiedzi. Jeśli nie został wybrany, stuknij „Zmień” i wskaż go na liście „Pasują do numeru”. Możesz także wkleić link śledzenia z domeny `straightship.com` (`/track-your-shipment/tracking-id/` wraz z kodem). Peek odpytuje w Straightship wyłącznie te znane formaty: gdy wybierzesz Straightship przez „Zmień” dla innego układu znaków, Peek wyświetli komunikat „Ten przewoźnik nie wydaje takich numerów śledzenia. Sprawdź numer lub wybierz innego przewoźnika.” — skorzystaj wtedy z wyszukiwarki na stronie Straightship.

Peek odczytuje oficjalną wyszukiwarkę Straightship i pokazuje aktualny status, historię z miastem i prowincją każdego skanu oraz godzinę doręczenia. Traktuje godziny jako zapisane w UTC, zgodnie z uwagą na stronie przewoźnika, i przelicza je na strefę czasową Twojego urządzenia („Godziny w twojej strefie czasowej: …” na peektracker.com i w aplikacjach na iPhone’a i Androida). `Package will be delivered before 9:00pm` wyświetla się jako „W doręczeniu”, komunikaty po nieudanej wizycie jako „Próba doręczenia”, a `Delivery is in progress` jako „W transporcie”. Peek nie podaje przewidywanej daty dostawy oraz pomija numer referencyjny klienta i zdjęcie z doręczenia widoczne na stronie Straightship. Ponieważ Straightship nie wskazuje innego przewoźnika, Peek śledzi numer Straightship aż do doręczenia.

Gdy masz otwartą kartę przesyłki albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza status nawet co 10 minut — a od 6:00 czasu pacyficznego w dniu, w którym Straightship zeskanuje paczkę jako wydaną do doręczenia, nawet co 2 minuty, z uwzględnieniem wieczornych dostaw — o ile odpowiada wyszukiwarka Straightship; wyłącznie przez serwisy śledzenia sprawdza co godzinę, co 3 godziny po dobie bez nowego skanu oraz nawet co 15 minut w dniu doręczenia przy otwartej karcie lub włączonych alertach. W pozostałym czasie oraz po dwóch dniach bez ruchu (12 godzinach na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika”) sprawdza co godzinę. Możesz włączyć powiadomienia o opcji „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek nie może skontaktować się w Twoim imieniu ze Straightship ani ze sprzedawcą.

## Najczęstsze pytania o śledzenie Straightship

### Czy Straightship to wiarygodny przewoźnik?

Tak. Straightship Solutions Limited to firma logistyczna z siedzibą w Burnaby w Kolumbii Brytyjskiej (Kanada). Na swojej witrynie wymienia wśród partnerów SHEIN, Canada Post, FedEx, COSCO oraz [YTO](carrier:yto), wskazuje sieci Walmart i Costco wśród obsługiwanych detalistów oraz deklaruje ponad 85 centrów dystrybucyjnych w Kanadzie i obsługę ponad 500 000 paczek Amazon dziennie.

### Co oznacza komunikat „Package will be delivered before 9:00pm”?

Oznacza, że paczka jest dziś w doręczeniu w Kanadzie. Kierowca dostarczy ją lub odnotuje próbę doręczenia jeszcze tego samego dnia; jeśli nie uda się jej zostawić, pojawi się wpis z prośbą o wiadomość na `SS@straightship.com`.

### Dlaczego Straightship wciąż pokazuje „ReadyForShipping”?

Ponieważ pole podsumowania „Package Status” potrafi zatrzymać się na wartości `ReadyForShipping`, podczas gdy paczka przemieszcza się już między oddziałami Straightship. Kieruj się najnowszym wierszem w szczegółowej historii poniżej.

### Ile trwa dostawa przez Straightship?

Straightship nie publikuje czasów przewozu dla przesyłek nadawanych w Azji; dla usługi ze Stanów Zjednoczonych do Kanady podaje od 6 do 10 dni. Sprawdzaj szacowany termin dostawy z zamówienia w sklepie; gdy w śledzeniu pojawi się `Package will be delivered before 9:00pm`, kurier przyjedzie tego samego dnia.

### Kto doręcza paczki Straightship w Kanadzie?

Właśni kierowcy Straightship z jego kanadyjskich oddziałów, co widać w wyszukiwarce przewoźnika, która nie wymienia żadnej innej firmy kurierskiej. W usłudze z USA do Kanady podstrony ofertowe wymieniają dodatkowo Canada Post i UPS, a także Purolator na stronie angielskiej oraz FedEx na stronie chińskiej.

:::sources
- [Straightship: Home (po angielsku)](https://www.straightship.com/new/) – transport z Azji do Ameryki Północnej, własna flota, pole śledzenia, zdjęcie POD, partnerzy, telefon, e-mail i godziny pracy EST
- [Straightship: About (po angielsku)](https://www.straightship.com/new/about.html) – logistyka od drzwi do drzwi z Azji do Ameryki Północnej
- [Straightship: Services (po angielsku)](https://www.straightship.com/new/services.html) – własna sieć obejmująca ponad 95% populacji i 90% kodów pocztowych Kanady, usługa USA–Kanada w 6–10 dni, odprawa w 24 godziny oraz opcje Canada Post, Purolator i UPS
- [Straightship: 服务 (usługi, po chińsku)](https://www.straightship.com/new/cn/services.html) – ta sama usługa USA–Kanada z własną siecią, Canada Post, FedEx lub UPS na ostatniej mili oraz pobieranie podatków w Kanadzie (DDU)
- [Straightship: Resources (po angielsku)](https://www.straightship.com/new/resources.html) – ponad 85 centrów dystrybucyjnych w Kanadzie, ponad 500 000 paczek Amazon dziennie, Walmart i Costco
- [Straightship: Contact (po angielsku)](https://www.straightship.com/new/contact.html) – tematy zgłoszeń, obowiązkowy numer śledzenia i odpowiedź w 2 dni robocze
- [Straightship: Privacy Policy (po angielsku)](https://www.straightship.com/new/privacy.html) – nazwa Straightship Solutions Limited i lokalizacje w Burnaby (Kolumbia Brytyjska)
- [CBSA: Importing casual goods by courier (po angielsku)](https://www.cbsa-asfc.gc.ca/import/courier/lvs-efv/prsn-eng.html) – rozliczanie ceł i podatków przez kurierów w Kanadzie oraz opłaty za obsługę celną poza kontrolą CBSA
- [CBSA: Protect yourself against fraud (po angielsku)](https://www.cbsa-asfc.gc.ca/security-securite/pyaf-pvcf-eng.html) – płatność uzgadniana przed doręczeniem przez firmę kurierską oraz brak próśb CBSA o numer SIN lub kartę kredytową
- [CBSA: Low-value shipment thresholds (po angielsku)](https://www.cbsa-asfc.gc.ca/services/cusma-aceum/lvs-efv-eng.html) – progi zwolnień kurierskich w Kanadzie (20 CAD z pozostałych krajów, 40 CAD i 150 CAD z USA i Meksyku)
- [SHEIN Canada: Are there any additional fee for customs? (po angielsku)](https://ca.shein.com/faq/detail?id=2307) – zamówienia SHEIN do Kanady wysyłane w formule opłaconych należności (DDP)
- [SHEIN Canada: My order is marked as delivered, but I haven't received it (po angielsku)](https://ca.shein.com/faq/detail?id=2362) – status doręczenia do 48 godzin przed faktycznym dostarczeniem i przycisk Parcel Delivered, Not Received
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formaty `STRTD`, `STRCA` i `STSH`, brzmienie skanów, uwaga o UTC, wartości podsumowania, przekazywane `Customs Released` i zatrzymania celne, powiązanie z JS Express oraz pola wyświetlane w Peek
:::
