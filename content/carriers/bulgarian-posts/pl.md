---
title: Śledzenie Bulgarian Posts: kody PS/BG, cło i pomoc
description: Śledź paczkę Bulgarian Posts: kody PS i S10 BG, statusy angielskie i bułgarskie, odbiór w 20 dni, opłaty celne UE, reklamacje i kontakt.
slug: sledzenie-bulgarian-posts
published: 2026-11-24
updated: 2026-11-24
---

Bulgarian Posts (*Български пощи ЕАД*) to narodowy operator pocztowy Bułgarii, obsługujący krajowe listy, e-przesyłki i paczki, a także międzynarodową pocztę Światowego Związku Pocztowego (UPU) oraz przesyłki kurierskie EMS / Bulpost. Przesyłkę sprawdzisz na [stronie śledzenia Bulgarian Posts](https://www.bgpost.bg/en/postal-services/track-package) albo wklejając numer w pole powyżej.

## Numery śledzenia Bulgarian Posts: formaty PS i S10

13-znakowy kod kreskowy znajdziesz na potwierdzeniu nadania, w e-mailu ze sklepu internetowego lub na papierowym awizo (*служебно известие*). Na stronie [bgpost.bg/en/postal-services/track-package](https://www.bgpost.bg/en/postal-services/track-package) Bulgarian Posts wymienia dwa śledzone formaty:

| Format (zmyślony) | Co to jest |
| --- | --- |
| `PS48291736405` | Przesyłka krajowa lub e-paczka: `PS` i 11 liter oraz cyfr (łącznie 13 znaków). |
| `RR482917367BG` | List polecony lub mała paczka międzynarodowa (`R`, `C`, `E`, `L` lub `V` + druga litera + 9 cyfr + `BG`). |
| `CP482917367BG` | Międzynarodowa paczka pocztowa nadana w Bułgarii (`CP` + 9 cyfr + `BG`). |

Paczki przychodzące z zagranicy zachowują kod UPU S10 poczty nadawczej (zakończony dwuliterowym kodem jej kraju, np. `DE`, `CN` lub `US`). Bulgarian Posts podaje, że przesyłki wychodzące za granicę można śledzić na jej stronie do opuszczenia Bułgarii, a potem na stronie poczty docelowej, natomiast przychodzące pojawiają się po dotarciu do Bułgarii. Dla innych firm kurierskich w Bułgarii sprawdź [Sameday](carrier:sameday), [BOX NOW](carrier:box-now) lub [formaty numerów przesyłek](guide:tracking-number-formats).

## Co oznaczają statusy śledzenia Bulgarian Posts

Śledzenie Bulgarian Posts pokazuje angielskie zdarzenia UPU IPS na odcinkach międzynarodowych — oznaczone `(Otb)` dla wyjścia lub `(Inb)` dla wejścia — obok bułgarskich opisów skanów krajowych (nawet na angielskiej wersji strony).

| Bulgarian Posts pokazuje | Co to oznacza dla ciebie | Peek pokazuje |
| --- | --- | --- |
| `Receive item from customer (Otb)`, `Получаване пратка от клиент (А10)`, pierwsze `Приемане` | Przyjęta od nadawcy w placówce pocztowej. | „Przyjęta przez przewoźnika” |
| `Insert item into bag (Otb) and Send item abroad`, `Send item abroad (EDI-received)`, `Receive item at office of exchange (Otb)` / `(Inb)` | Spakowana na eksport, wysłana za granicę lub przyjęta w bułgarskim międzynarodowym urzędzie wymiany. | „W transporcie” |
| `Receive item at sorting centre (Inb)`, `Insert item into domestic bag`, `Send item to domestic location (Inb)`, `Receive item at location (Inb)`, kolejne `Приемане` | W drodze przez bułgarskie sortownie do twojej placówki pocztowej. | „W transporcie” |
| `Send item to customs (Inb)`, `Record item customs information (Inb)` | Przedstawiona bułgarskiemu urzędowi celnemu lub oczekująca na dokumenty/wycenę celną (często z dopiskiem o oczekiwaniu na przedstawienie funkcjonariuszowi celnemu). | „W urzędzie celnym” |
| `Return item from customs (Inb)` | Zwolniona przez urząd celny i przekazana z powrotem do sieci Bulgarian Posts (nie jest to zwrot do nadawcy). | „W transporcie” |
| `Handover item to delivery agent` | Wydana doręczycielowi; doręczenie pod adres dziś. | „W doręczeniu” |
| `Unsuccessful item delivery attempt (Inb)`, `Първо посещение` | Pierwsza próba doręczenia nieudana; zostawiono pisemne awizo, a przesyłka trafia na pocztę. | „Próba doręczenia” |
| `Receive item at collection point for pick-up (Inb)`, `За получаване в ПС`, `Второ посещение` | Czeka na odbiór w lokalnej placówce (`Второ посещение` oznacza drugie pisemne awizo wysłane po 10 dniach kalendarzowych). | „Do odbioru” |
| `Deliver item (Inb)`, `Доставена`, `Доставена пратка` | Wydana za pokwitowaniem. | „Dostarczona” |

Zwróć uwagę, że `Създаване` (założenie krajowego rekordu przez Bulgarian Posts zaraz po skanie w urzędzie wymiany) to wpis administracyjny bez fizycznego ruchu, dlatego Peek zachowuje etap poprzedniego skanu. Podobnie `Второ посещение` nie jest kolejną nieudaną próbą pod drzwiami — odnotowuje drugie zawiadomienie o odbiorze, podczas gdy paczka nadal czeka na poczcie.

## Odbiór w placówce, czas przechowywania i opłaty składowe

Według [Ogólnych Warunków Świadczenia Usług Bulgarian Posts (ОУ 2023)](https://www.bgpost.bg/cms/static/%D0%9E%D0%A3_2023.pdf) (punkty 43, 45 i 46, dostępne przez [General Terms](https://www.bgpost.bg/en/about_us/conditions)):

- **Przesyłki polecone i paczki:** wydawane osobiście adresatowi lub osobie upoważnionej za podpisem i dokumentem tożsamości ze zdjęciem. Paczki pocztowe (*пощенски колети*) odbiera się zwykle w placówce doręczającej po dostarczeniu pisemnego awiza (*писмено служебно известие*), chyba że osobno opłacono doręczenie pod adres.
- **Standardowy czas przechowywania — 20 dni kalendarzowych:** zarówno przesyłki polecone po nieudanej próbie, jak i paczki w placówce czekają **20 dni kalendarzowych** od przybycia do placówki doręczającej, zanim wrócą do nadawcy.
- **Drugie awizo po 10 dniach:** jeśli nie odbierzesz przesyłki w ciągu **10 dni kalendarzowych**, Bulgarian Posts wysyła drugie pisemne zawiadomienie (`Второ посещение`).
- **Opłata za składowanie po 9 dniach:** za paczki pocztowe nieodebrane w ciągu **9 dni kalendarzowych** od zawiadomienia Bulgarian Posts nalicza opłatę magazynową (*магазинаж*) według aktualnego cennika.
- **Przesyłki po odprawie celnej — 45 dni kalendarzowych:** według [zasad celnych Bulgarian Posts](https://www.bgpost.bg/en/customs) **wszystkie przesyłki po odprawie celnej odbiera się wyłącznie we wskazanej placówce pocztowej** (nigdy pod drzwiami), a termin przechowywania wynosi **45 dni kalendarzowych** przed zwrotem do nadawcy.

## Paczki spoza UE, opłaty celne oraz cło 3 € i opłata UE 2 €

Przesyłki przemieszczające się wewnątrz UE nie przechodzą odprawy celnej. Dla zakupów online i prezentów trafiających do Bułgarii spoza UE [bgpost.bg/en/customs](https://www.bgpost.bg/en/customs) oraz [Komisja Europejska](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) podają obowiązujące stawki:

- **Stałe cło i unijna opłata manipulacyjna:** dla zakupów online B2C o wartości do 150 € obowiązuje ryczałtowe **cło 3 € za artykuł** (od 1 lipca 2026 r.), a dla zakupów online spoza UE niezależnie od wartości obowiązuje **unijna opłata manipulacyjna (UHF) 2 € za każdy artykuł** (od 1 listopada 2026 r.; wliczana do podstawy VAT; zwolnione są prywatne prezenty C2C). Ani cło 3 € (do 150 €), ani opłata UHF 2 € **nie podlegają zwrotowi**, nawet przy zwrocie wadliwego towaru.
- **Opłaty za reprezentację celną Bulgarian Posts (z VAT, obowiązujące od 2 listopada 2026 r.):**
  - B2C do 150 € z IOSS (zgłoszenie H7): **2,70 €** (plus cło, jeśli sklep nie pobrał go przy płatności).
  - B2C do 150 € bez IOSS (procedura szczególna, H7): **3,80 €** (plus VAT i cło).
  - B2C od 150 € do 1 000 € (H6): **12,00 €** (plus VAT i cło).
  - B2C powyżej 1 000 € (H1): **20,00 €** za pierwszy rodzaj towaru plus **5,00 €** za każdy kolejny rodzaj.
  - Prywatne prezenty C2C: **2,70 €** do 45 € (bez VAT i cła), **3,80 €** od 45 € do 1 000 €, lub **20,00 €** (+ 5,00 € za każdy dodatkowy rodzaj, wymagany numer EORI) powyżej 1 000 €.
- **Wymagane dokumenty:** oświadczenie o zgodzie, deklaracja wartości oraz — przy zakupach — zamówienie/faktura zgodne z numerem przesyłki i dowód zapłaty (wyciąg z banku, karty lub PayPal).

Zobacz [przesyłki zatrzymane w urzędzie celnym](guide:customs), aby poznać ogólne zasady odprawy w UE.

## Zagubione, opóźnione lub uszkodzone paczki i prawa konsumenta

Przy zakupach w sklepie internetowym za doręczenie i zwroty odpowiada sprzedawca. Według bułgarskiej Komisji Ochrony Konsumentów ([zasady zakupów online KZP](https://kzp.bg/bg/potrebiteli/good-to-know/online-shopping)) zamówienia internetowe muszą zostać dostarczone w ciągu 30 dni, o ile nie uzgodniono inaczej, a konsument ma 14-dniowe prawo odstąpienia od umowy od dnia odbioru.

**Reklamacja w Bulgarian Posts.** Zgodnie z [bgpost.bg/en/complaints](https://www.bgpost.bg/en/complaints) i punktami 56–59 Ogólnych Warunków reklamację przed doręczeniem składa **nadawca** — adresat może ją złożyć przed odbiorem wyłącznie za pisemną zgodą nadawcy. Reklamację składa się w ciągu **6 miesięcy** od nadania (w dowolnej placówce pocztowej lub online w portalu RQMS) z kopią potwierdzenia nadania. Bulgarian Posts odpowiada w ciągu **1 miesiąca** dla przesyłek krajowych i **3 miesięcy** dla międzynarodowych, wypłacając uznane odszkodowanie w ciągu **1 miesiąca** od zawiadomienia.

**Skarga do regulatora.** Nierozwiązane spory pocztowe można zgłosić do bułgarskiej Komisji Regulacji Komunikacji ([skargi CRC / КРС](https://crc.bg/bg/rubriki/271/jalbi)), która odpowiada w ciągu 30 dni (do 2 miesięcy przy konieczności uzupełnienia dowodów). W razie opóźnień lub problemów zobacz [dlaczego śledzenie przestaje się aktualizować](guide:tracking-not-updating), [dostarczona, ale nieodebrana](guide:delivered-not-received), [uszkodzona paczka](guide:damaged-parcel), [zagubiona paczka](guide:lost-parcel) i [zwrócona nadawcy](guide:returned-to-sender).

## Obsługa klienta i kontakty Bulgarian Posts

Dane kontaktowe opublikowane na [bgpost.bg/en/contacts](https://www.bgpost.bg/en/contacts), [bgpost.bg/en/complaints](https://www.bgpost.bg/en/complaints) i [bgpost.bg/en/customs](https://www.bgpost.bg/en/customs):

- **Ogólna obsługa klienta:** `*7678` lub `+359 2 962 50 50` (dni robocze 08:00–17:30), e-mail `info@bgpost.bg`.
- **Kurier EMS / Bulpost:** `*6666` lub `0700 19 666` (dni robocze 07:00–19:00).
- **Obsługa celna (1900 Sofia BESC):** `customerservice@bgpost.bg` (pon.–pt. 08:00–16:00); przesyłki EMS na literę `E`: `emscustomerservice@bgpost.bg` (pon.–pt. 08:30–16:00); urząd celny Warna `psm.varna@bgpost.bg` / `052 604 143`.
- **Infolinie reklamacyjne (dni robocze 09:00–17:30):** krajowe `+359 2 949 3326`, międzynarodowe przychodzące `+359 2 949 3121`, międzynarodowe wychodzące `+359 2 949 3343`, pomoc portalu RQMS `02 949 31 90`.
- **Siedziba:** 1, Akademik Stefan Mladenov Str., Bl. 31, 1700 Sofia, Bułgaria.

> Uważaj na niechciane SMS-y i e-maile proszące o zapłatę kartą online za „zaplanowanie doręczenia” przesyłki Bulgarian Posts: przesyłki po odprawie celnej opłaca się i odbiera we wskazanej placówce pocztowej. Sprawdzaj kod bezpośrednio na [bgpost.bg](https://www.bgpost.bg/en/postal-services/track-package). Zobacz [fałszywe SMS-y o przesyłce](guide:delivery-scam-texts).

## Śledzenie paczki Bulgarian Posts w Peek

[Peek](/) rozpoznaje 13-znakowe kody `PS` (`PS48291736405`, cyfry zmyślone) bezpośrednio jako Bulgarian Posts z wysoką pewnością oraz obsługuje wklejone linki `bgpost.bg/postal-services/track-package?itemid=` (w tym `/en/`) i starsze `IPSWeb_item_events.asp?itemid=`. Dla międzynarodowych kodów UPU S10 kończących się na `BG` (np. `RR482917367BG` lub `CP482917367BG`) Peek automatycznie wybiera Bulgarian Posts jako preferowaną pocztę nadania; dla kodów S10 z zagranicy też możesz wskazać Bulgarian Posts, stukając „Zmień” podczas dodawania lub „Zmień przewoźnika” na ekranie paczki (wyszukanie „Български пощи”, „Bulgaria Post” lub „BG Post” również go znajdzie). Jeśli numer należy do Bulpost (`EMS/Bulpost`), Bulgarian Posts przekierowuje na osobną stronę Bulpost.

Peek odpytuje bezpośrednio publiczne API Bulgarian Posts i pokazuje etap („Przyjęta przez przewoźnika”, „W transporcie”, „W urzędzie celnym”, „W doręczeniu”, „Próba doręczenia”, „Do odbioru”, „Dostarczona”), angielskie wpisy IPS (wraz z bułgarskimi wpisami krajowymi i uwagami celnymi), miejscowości/kraje placówek (bez kodów pocztowych) oraz godziny doręczenia w bułgarskich urzędach. Skany w bułgarskich placówkach mają czas sofijski, a skany poczt zagranicznych — strefę czasową tamtego miejsca (np. niemiecką dla Frankfurtu); Peek przelicza je na zegar twojego urządzenia. System Bulgarian Posts nie udostępnia przewidywanej daty dostawy, wagi, nazwy usługi, danych nadawcy/odbiorcy ani zdjęcia potwierdzającego doręczenie.

Gdy masz paczkę otwartą w Peek albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza status nawet co 10 minut; w dniu, gdy Bulgarian Posts pokaże `Handover item to delivery agent`, co 2 minuty od 6:00 czasu bułgarskiego, dopóki system Bulgarian Posts odpowiada. Poza tym sprawdza co godzinę. Po 12 godzinach wyłącznie na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika”, albo po dwóch dniach bez zmian, sprawdza co godzinę niezależnie od sposobu śledzenia, a po siedmiu dniach na etapie „Zarejestrowana” — co 6 godzin. Gdy system Bulgarian Posts nie odpowiada i Peek korzysta wyłącznie z zewnętrznych usług śledzenia, sprawdza co godzinę, co 3 godziny po dniu ciszy i do co 15 minut w dniu, w którym paczka jest w doręczeniu, gdy masz paczkę otwartą lub masz włączone powiadomienia.

Gdy najnowszy skan ma cztery dni, a przesyłka nadal widnieje jako „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie”, Peek oznacza ją dniem tego skanu, np. „Brak aktualizacji od pon 12 paź”. Na stronie paczki pojawia się wtedy napis: „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika Bulgarian Posts.”, wraz z linkiem „Poproś przewoźnika Bulgarian Posts o pomoc” do strony kontaktowej Bulgarian Posts. Po trzech tygodniach bez nowego skanu widnieje komunikat: „Od ponad trzech tygodni nic nowego. Jeśli przesyłka wciąż nie dotarła, zapytaj sprzedawcę. Peek dalej pyta przewoźnika Bulgarian Posts.”

Możesz wybrać powiadomienia: „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek tylko monitoruje śledzenie — nie złoży za ciebie zgłoszenia celnego ani reklamacji w Bulgarian Posts.

## Pytania o śledzenie Bulgarian Posts

### Jak długo Bulgarian Posts przechowuje paczkę na poczcie?

Standardowe paczki i przesyłki polecone czekają **20 dni kalendarzowych** od dotarcia do placówki doręczającej (z drugim awizo po 10 dniach i opłatą składową dla paczek po 9 dniach). Przesyłki po odprawie celnej są przechowywane **45 dni kalendarzowych** we wskazanej placówce pocztowej.

### Co oznacza `Второ посещение` w śledzeniu Bulgarian Posts?

Oznacza to, że Bulgarian Posts wystawił drugie pisemne awizo po 10 dniach kalendarzowych bez odbioru. Twoja paczka nadal czeka na poczcie — Peek pozostawia ją w statusie „Do odbioru”.

### Co oznacza `Return item from customs (Inb)`?

Oznacza to, że bułgarski urząd celny zwolnił przesyłkę i przekazał ją z powrotem do sieci Bulgarian Posts, aby dotarła do twojej placówki. Nie oznacza to zwrotu do nadawcy.

### Ile kosztuje odprawa celna w Bulgarian Posts?

Według cennika obowiązującego od 2 listopada 2026 r. opłata za reprezentację celną Bulgarian Posts (z VAT) wynosi 2,70 € dla zakupów IOSS do 150 € (lub prezentów C2C do 45 €), 3,80 € dla zakupów bez IOSS do 150 € (lub prezentów 45 €–1 000 €), 12,00 € dla zakupów 150 €–1 000 € oraz 20,00 € (+ 5,00 € za każdy kolejny rodzaj towaru) powyżej 1 000 €, obok należnego cła UE (3 € do 150 €), unijnej opłaty 2 € oraz VAT.

:::sources
- [Bulgarian Posts: Home](https://www.bgpost.bg/en) – przegląd działalności narodowego operatora pocztowego
- [Bulgarian Posts: Item Tracking](https://www.bgpost.bg/en/postal-services/track-package) – zasady 13-znakowych numerów S10 i `PS`, zakres śledzenia przychodzącego/wychodzącego
- [Bulgarian Posts: Customs](https://www.bgpost.bg/en/customs) – cło 3 €, opłata UHF 2 €, cennik reprezentacji celnej od 2 listopada 2026 r. (2,70 € / 3,80 € / 12 € / 20 €), odbiór wyłącznie w placówce i 45 dni kalendarzowych przechowywania
- [Bulgarian Posts: Ogólne Warunki (ОУ 2023 PDF)](https://www.bgpost.bg/cms/static/%D0%9E%D0%A3_2023.pdf) – punkty 43, 45, 46 (dowód/podpis, 20 dni kalendarzowych przechowywania, drugie awizo po 10 dniach, składowe po 9 dniach) oraz punkty 56–59 (reklamacje)
- [Bulgarian Posts: General Terms Overview](https://www.bgpost.bg/en/about_us/conditions) – usługi powszechne oraz warunki EMS/Bulpost
- [Bulgarian Posts: Claims and Inquiries](https://www.bgpost.bg/en/complaints) – zasada reklamacji przez nadawcę, termin 6 miesięcy, odpowiedź w 1/3 miesiące, portal RQMS i infolinie reklamacyjne
- [Bulgarian Posts: Contacts](https://www.bgpost.bg/en/contacts) – `*7678`, `+359 2 962 50 50`, `info@bgpost.bg`, EMS `*6666` / `0700 19 666`, siedziba w Sofii
- [Bulgarian Posts: FAQ](https://www.bgpost.bg/en/faq) – najczęstsze pytania odbiorców
- [Komisja Europejska: Reforma Celna UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – cło 3 € i unijna opłata manipulacyjna 2 €
- [KZP: Zakupy Online](https://kzp.bg/bg/potrebiteli/good-to-know/online-shopping) – termin dostawy 30 dni i 14 dni na odstąpienie od umowy
- [CRC (КРС): Skargi](https://crc.bg/bg/rubriki/271/jalbi) – skargi do regulatora pocztowego i termin odpowiedzi 30 dni
- [Universal Parcel Scraper: bulgarian-posts](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/bulgarian-posts) – wykrywanie `PS` i S10 `BG`, reguły URL `bgpost.bg`, mapowanie statusów IPS angielsko-bułgarskich, przekierowanie Bulpost, strefa Sofii i adres pomocy
:::
