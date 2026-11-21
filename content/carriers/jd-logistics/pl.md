---
title: Śledzenie przesyłek JD Logistics: numery, statusy i kontakt
description: Śledź paczkę JD Logistics (京东物流): formaty numerów JD i VG, znaczenie statusów po angielsku i chińsku, magazyny pod Warszawą, odprawa celna i kontakt.
slug: sledzenie-przesylek-jd-logistics
published: 2026-11-18
updated: 2026-11-21
---

JD Logistics (京东物流, Jingdong Logistics) to ramię logistyczne grupy JD.com, notowane na giełdzie w Hongkongu (`HKEX: 2618`). Za granicą działa pod markami JoyLogistics oraz JoyExpress: wysyła towary sprzedawców ze swoich magazynów w **Polsce**, **Niemczech**, **Francji**, **Holandii**, **Wielkiej Brytanii**, **Stanach Zjednoczonych** czy **Australii**, a także przewozi paczki e-commerce z **Chin**. Przesyłkę możesz sprawdzić po angielsku w [międzynarodowej wyszukiwarce JD Logistics](https://www.jingdonglogistics.com/Tracking) albo wkleić numer w polu powyżej. Kod otrzymujesz od sklepu, który nadał zamówienie.

## Numer śledzenia JD Logistics: formaty i przykłady

Oto układy znaków rozpoznawane przez Peek jako JD Logistics oraz jeden podobny format należący do innego przewoźnika (z przykładowymi cyframi):

| Numer | Co oznacza |
| --- | --- |
| `JD4829105736491` | `JD` i 13 cyfr: list przewozowy JD Logistics. |
| `JDA482910573649` | `JD`, jedna litera i 12 cyfr: również JD Logistics. |
| `JDAP48291057364` | `JD`, dwie litery i 11 cyfr: również JD Logistics. |
| `VG48291057364` | `VG` i 11 cyfr: używane przez JD Logistics, ale pokrywa się z formatem [La Poste](carrier:la-poste), dlatego Peek nie przypisuje od razu JD (szczegóły niżej). |
| `JD482910573649182736` | `JD` i 18 cyfr: to nie JD Logistics. Peek odpytuje [DHL Paket](carrier:dhl), który stosuje ten układ. |

Małe litery, spacje i myślniki nie mają znaczenia w Peek. Międzynarodowa wyszukiwarka JD przyjmuje do 100 numerów naraz oddzielonych spacjami: naciśnij Enter lub przycisk „Track”.

**Gdzie go znaleźć.** Sklep, który zlecił wysyłkę w JD, podaje numer w e-mailu o nadaniu lub w szczegółach zamówienia. Jeśli nie możesz go odszukać, zajrzyj do poradnika [jak znaleźć numer śledzenia](guide:find-tracking-number).

**Paczka na terenie Chin.** Przesyłkę przemieszczającą się wewnątrz **Chin** (na przykład do magazynu pośrednika) sprawdzisz na chińskiej stronie [jdl.com](https://www.jdl.com/orderSearch): wpisz numer listu przewozowego lub paczki pod `运单查询` i kliknij `立即查询` (wyszukiwarka przyjmuje kilka numerów rozdzielonych przecinkami, spacjami lub nowymi liniami).

## Statusy śledzenia JD Logistics i ich znaczenie

Międzynarodowe śledzenie JD wyświetla każdy etap po angielsku wraz z miejscowością skanu. Oto oryginalne komunikaty JD odczytywane przez Peek, w typowej kolejności dla zamówienia wysłanego z zagranicznego magazynu JD:

| Komunikat JD | Status w Peek | Co oznacza dla Twojej przesyłki |
| --- | --- | --- |
| `Picking Completed.` | „Przyjęta przez przewoźnika” | Zamówienie zostało skompletowane w magazynie JD, w którym sprzedawca trzymał już zapas towaru. |
| `Weighed at Warehouse.` | „Przyjęta przez przewoźnika” | Zważone w tym samym magazynie przed wyjazdem. |
| `Departed from Warehouse.` | „W transporcie” | Przesyłka opuściła magazyn JD Logistics. |
| `Handed over to Carrier` | „W transporcie” | JD przekazało paczkę firmie doręczającej na ostatnim odcinku. |
| `Shipping Label Created` | bez cofnięcia statusu | Etykieta lokalnego kuriera przekazana przez JD; paczka będąca już w drodze zachowuje swój dotychczasowy etap. |
| `Arrived at GOFO Regional Hub` | „W transporcie” | W **Stanach Zjednoczonych**: [GOFO Express](carrier:gofo), który w historii JD realizuje tam doręczenia, zeskanował paczkę w swoim regionalnym centrum. |

**Paczka wysłana z Chin** przechodzi przez inne etapy w następującym porządku: najpierw `Shipment Picked up` (odebrana od sprzedawcy), potem `Arrived at Warehouse`, `Weighed at Warehouse` oraz `Consolidation Completed` w chińskim magazynie JD przed wylotem — wszystkie jako „Przyjęta przez przewoźnika” (`Consolidation Completed` oznacza spakowanie w zbiorczy ładunek lotniczy z innymi paczkami, a nie doręczenie). Następnie pod statusem „W transporcie” pojawiają się: `Departed from Warehouse.`, `Arrive at airport`, `Customs released from origin port` (zakończona odprawa eksportowa), `Departure from origin port`, `Arrive at destination port`, `Cleanrance agency picked` (pisownia JD: agencja celna odebrała ocloną przesyłkę, nie odbiorca) oraz `Handover to local carrier`. W **Stanach Zjednoczonych** dalsze skany GOFO przychodzą pod kodami JD: `Arrived at GOFO Regional Hub`, `In Transit to Next Facility`, `Loaded on Vehicle` (załadunek w hubie GOFO) i `Arrived at GOFO Delivery Station` („W transporcie”), a na końcu `Out for Delivery` („W doręczeniu”).

**Numer lokalnego przewoźnika.** Przy paczkach z Chin wiersz `Departed from Warehouse.` w wyszukiwarce JD kończy się niekiedy dopiskiem `Tracking no` i numerem nadanym przez lokalnego doręczyciela (w **USA** numerem `GFUS` z 14 cyframi, np. `GFUS48291057364918` dla GOFO Express). Peek usuwa ten dopisek z treści wiersza, ale automatycznie odpytuje tego przewoźnika, gdy kształt numeru go wskazuje: gdy tylko lokalny przewoźnik pokaże skan co najmniej tak świeży jak ostatni wpis JD, Peek przełącza śledzenie na niego, zachowując wcześniejszą historię JD, i wyświetla „Doręcza: GOFO Express”. Jeśli numeru nie da się przypisać do znanego przewoźnika, sprawdź go bezpośrednio na stronie lokalnej firmy kurierskiej.

Pozostałe zdarzenia JD Peek rozpoznaje po ich wewnętrznym kodzie: utworzenie zlecenia („Zarejestrowana”); rozpoczęcie odprawy celnej („W urzędzie celnym”); zakończenie odprawy, rozładunek lub załadunek w sortowni, rozładunek w punkcie docelowym albo przekazanie do zagranicznej sortowni („W transporcie”); kurier JD w trasie („W doręczeniu”); pokwitowanie odbioru („Dostarczona”). Gdy komunikat o wydaniu do doręczenia zawiera imię i telefon kuriera w nawiasach `【…】`, Peek pomija oba te elementy.

**Wpisy z chińskiej wyszukiwarki (`jdl.com`).** Dla paczek wewnątrz **Chin** komunikaty są po chińsku, m.in. `京东快件已取件` (odebrano od nadawcy), `已到达【…】` (dotarła do sortowni lub stacji doręczeń podanej w nawiasach), `正在派送中` (w doręczeniu), `已与收件人约定新的派送时间` (uzgodniono z odbiorcą nowy termin doręczenia) oraz `已送达` (doręczono).

## Doręczenia JD Logistics w Polsce, Europie i na świecie

**Paczki z Chin.** Na podstronie „Cross-Border Parcels” JD opisuje usługę dla sprzedawców internetowych wysyłających z **Chin** do **USA**, **Wielkiej Brytanii**, **Australii**, **Kanady**, **Meksyku**, **Rosji** i kolejnych wdrażanych krajów, w wariantach standardowym, ekspresowym i ekonomicznym. JD przewozi ładunki drogą lotniczą, a ostatnią milę powierza lokalnym partnerom ze średnim deklarowanym czasem doręczenia lokalnego od 3 do 6 dni roboczych, nie wymieniając nazw tych firm (w **USA** historia JD wskazuje [GOFO Express](carrier:gofo)). W **Europie** grupa JD.com uruchomiła ponadto w kwietniu 2025 roku własną markę przesyłek ekspresowych JoyExpress.

**Paczki z magazynu JD w Polsce lub w Europie.** JD Logistics prowadzi własne magazyny poza Chinami — w tym dwa pod Warszawą w **Polsce** (`PL-JD-WAW1` w Urzucie przy al. Katowickiej 283 oraz `PL-JD-WAW2` w Brwinowie, Moszna Lot 29), a także w **Niemczech** pod Frankfurtem (`DE-JD-FRA1` w Buseck) i Dortmundem (`DE-JD-DTM1` w Unnie), we **Francji** pod Paryżem (`FR-JD-PAR1` w Dugny), w **Holandii** w Venray (`NL-JD-VEN1`) i Tilburgu (`NL-JD-TIL1`), w **Wielkiej Brytanii** (`GB-JD-BHM1` w Lutterworth), w **USA** (New Jersey, rejon Los Angeles i Georgia) oraz w **Australii** (Sydney). Gdy sprzedawca wysyła towar z takiego lokalnego zapasu, historia zaczyna się od `Picking Completed.` i `Weighed at Warehouse.` zamiast `Shipment Picked up`.

Strony JD nie określają liczby prób doręczenia ani miejsca oczekiwania awizowanej paczki: decyduje o tym regulamin przewoźnika doręczającego w Twoim kraju. Zobacz [nieudane doręczenie](guide:missed-delivery).

## Odprawa celna, opłaty importowe i fałszywe SMS-y

Zgodnie z regulaminem międzynarodowych przesyłek ekspresowych JD Logistics nadawanych z **Chin kontynentalnych** ([jdl.com/serviceDetails](https://www.jdl.com/serviceDetails), zakładka `国际`), podczas transgranicznej odprawy celnej mogą powstać należności celne i podatkowe, które obciążają nadawcę lub odbiorcę i nie są wliczone w opłatę przewozową JD. Upewnij się u sprzedawcy, skąd wyruszyła paczka i czy podatek VAT oraz cło były zawarte w cenie zakupu. Więcej w poradnikach [paczka zatrzymana w urzędzie celnym](guide:customs) oraz [jak rozpoznać fałszywe SMS-y o paczce](guide:delivery-scam-texts).

- **Unia Europejska.** Od 1 lipca 2026 roku w **Unii Europejskiej** towary kupowane na odległość spoza obszaru celnego UE w przesyłkach o wartości rzeczywistej do 150 € objęte są cłem w wysokości 3 € za każdą kategorię towarową, a od 1 listopada 2026 roku doliczana jest unijna opłata manipulacyjna w wysokości 2 € za każdą kategorię towarową niezależnie od wartości przesyłki (jedna kategoria obejmuje towary o tej samej klasyfikacji taryfowej, tym samym opisie oraz – gdy jest wymagane – tym samym pochodzeniu, bez względu na liczbę sztuk).
- **Polska.** W **Polsce** w przypadku przesyłek spoza UE odprawianych przez [Pocztę Polską](https://www.poczta-polska.pl/news/zmiany-w-oplatach-dla-przesylek-spoza-unii-europejskiej-od-1-lipca-2026-r/) oprócz wskazanej opłaty mogą zostać naliczone również inne należności wynikające z obowiązujących przepisów, w tym podatek VAT (jeżeli nie został opłacony w momencie zakupu) oraz opłata za dokonanie zgłoszenia celnego przez Pocztę Polską; opłaty te pobierane są przy doręczeniu przesyłki lub przed jej odbiorem – w zależności od zastosowanej procedury celnej, a odbiorca ma również możliwość odmowy przyjęcia przesyłki. Jednocześnie Poczta Polska ostrzega w [komunikacie o fałszywych wiadomościach Pocztex](https://www.poczta-polska.pl/news/uwaga-na-falszywe-wiadomosci-pocztex-grozna-kampania-phishingowa/), że Pocztex nie wysyła w wiadomościach SMS linków prowadzących do płatności ani nie prosi przez SMS o dane kart płatniczych.

## Paczka JD Logistics nie aktualizuje się, spóźnia się lub wraca

**Brak wpisów pod numerem.** Jeśli wyszukiwarka JD nie znajduje jeszcze przesyłki, potwierdź numer u sprzedawcy. Peek wyświetla „Oczekiwanie na przewoźnika JD Logistics” i sprawdza dalej; jeśli po dwóch dniach numer nadal nie jest znany, pojawia się komunikat „Po dwóch dniach przewoźnik nadal nie zna tego numeru. Sprawdź go, wybierz innego przewoźnika lub usuń przesyłkę.”.

**Brak aktualizacji od kilku dni.** Gdy przesyłka w drodze nie ma nowego skanu od czterech dni, Peek pokazuje dzień ostatniego skanu („Brak aktualizacji od pon 12 paź”), przypomina, że paczki często milkną podczas długich lotów, w urzędzie celnym lub w weekend (po trzech tygodniach bez skanu radzi zamiast tego zapytać sprzedawcę), i udostępnia link „Poproś przewoźnika JD Logistics o pomoc” do strony kontaktowej JD (albo do lokalnego przewoźnika, gdy doręczenie przejął np. GOFO). Zobacz [dlaczego śledzenie się nie aktualizuje](guide:tracking-not-updating) oraz [śledzenie paczek z Chin](guide:tracking-from-china).

**Opóźniona lub zagubiona.** Umowę z JD zawarł sprzedawca, dlatego najpierw zgłoś sprawę w sklepie. Dla ekspresu międzynarodowego z **Chin kontynentalnych** regulamin JD zastrzega, że szacowany czas dostawy widoczny na liście przewozowym ma charakter wyłącznie orientacyjny: JD nie zwraca kosztów transportu ani nie wypłaca odszkodowania za opóźnienie. Zobacz [zagubiona paczka](guide:lost-parcel).

**Zwrócona.** Regulamin międzynarodowego ekspresu JD z **Chin kontynentalnych** nie przewiduje usługi zwrotnej po opuszczeniu przez towar Chin kontynentalnych. Aby zwrócić zamówienie, zapytaj sklep o procedurę zwrotu. Zobacz [zwrot do nadawcy](guide:returned-to-sender).

**Oznaczona jako dostarczona, ale jej nie ma.** Skontaktuj się ze sprzedawcą, zapytaj sąsiadów lub recepcję budynku i sprawdź [dostarczona, ale nieotrzymana](guide:delivered-not-received).

## Obsługa klienta i kontakt z JD Logistics

Międzynarodowa [strona Contact Us JD Logistics](https://www.jingdonglogistics.com/contact) podaje adresy działu obsługi według krajów (w weekendy i święta państwowe nieczynne, o ile nie zaznaczono inaczej; bez wskazanej strefy czasowej):

- **Polska:** `org.cs.intl1@jd.com`, od poniedziałku do piątku w godz. 09:00–17:00.
- **Niemcy, Holandia i Wielka Brytania:** `org.cs.eu1@jd.com`, od poniedziałku do piątku w godz. 09:00–18:00.
- **Francja:** `org.cs.eu1@jd.com`, od poniedziałku do piątku w godz. 09:00–17:00.
- **Stany Zjednoczone:** `org.cs.us1@jd.com`, od poniedziałku do piątku w godz. 08:00–17:00.
- **Australia:** `+61 (02)87239755` oraz `org.cs.intl1@jd.com`, od poniedziałku do piątku w godz. 09:00–18:00 (w niedziele i święta nieczynne).

W **Chinach** główna infolinia JD Logistics ma numer `950616` (`0086 950616` na angielskiej podstronie International Express), a [strona Contact Us na jdl.com](https://www.jdl.com/en/contactUs) udostępnia przycisk „Online Consultation”. W sprawie samego zamówienia lub zwrotu pieniędzy skontaktuj się ze sklepem.

## Śledzenie przesyłki JD Logistics w Peek

[Peek](/) od razu rozpoznaje JD Logistics po formatach `JD…` z powyższej tabeli („Wykryty przewoźnik”). Numer `VG` z 11 cyframi pasuje także do La Poste, dlatego Peek sprawdza go najpierw w La Poste i innych serwisach śledzenia („Sprawdzanie usług śledzenia…”); jeśli go tam nie znajdą, stuknij „Zmień” i wybierz JD Logistics. Link z domeny `jingdonglogistics.com` zawierający `waybillCodes=` przypisuje paczkę do JD Logistics niezależnie od kształtu numeru; z linku `jdl.com` Peek pobiera numer, gdy odpowiada jednemu z układów `JD…`. Jeśli Twój numer JD wygląda inaczej, i tak go wklej: Peek obsługuje ponad 4500 przewoźników — stuknij „Zmień” i wskaż JD Logistics na liście „Wszyscy przewoźnicy” (wyszukiwarka znajduje też `Jingdong` oraz `京东物流`) albo użyj opcji „Zmień przewoźnika” na zapisanej przesyłce.

Peek pobiera dane z międzynarodowego śledzenia JD: status, każdy etap w oryginalnym brzmieniu JD wraz z miastem i krajem po angielsku (nawet gdy JD zapisuje kraj po chińsku) oraz godzinę doręczenia. Czasy są odczytywane według zegara miejsca skanu i pokazywane w strefie czasowej Twojego urządzenia („Godziny w twojej strefie czasowej: …” na peektracker.com i w aplikacjach na iPhone’a i Androida). Peek nie wyświetla przewidywanej daty dostawy dla paczek JD i pomija dane odbiorcy, ewentualne zdjęcia z doręczenia, imię i telefon kuriera oraz numer partnera lokalnego dopisany na końcu wiersza wyjazdu z magazynu.

Dla serii numerów przeznaczonych do **ruchu krajowego w Chinach** (`JD` i 13 cyfr zaczynających się od `0`; `JDJ`, `JDK`, `JDV` lub `JDX` i 12 cyfr; `JDVA`, `JDVB`, `JDVC` lub `JDAZ` i 11 cyfr) Peek otwiera chińską wyszukiwarkę na `jdl.com`. Dla `JDW` z 12 cyframi oraz `JDAP` z 11 odpytuje wyłącznie wyszukiwarkę międzynarodową; dla pozostałych numerów `JD` najpierw międzynarodową, a gdy ta nic nie zwróci — chińską. Chińskie wiersze wyświetlane są po chińsku bez imienia i telefonu kuriera oraz bez nazwiska osoby kwitującej odbiór. Ponieważ `jdl.com` często wymaga weryfikacji, której Peek nie może przejść, po odmowie Peek wstrzymuje otwieranie `jdl.com` dla tego numeru na 6 godzin lub dłużej, maksymalnie na dobę: jeśli paczka wewnątrz Chin nie pokazuje wpisów w Peek, sprawdź ją bezpośrednio na `jdl.com`.

Gdy masz otwartą kartę przesyłki albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza status nawet co 10 minut — a od 6:00 czasu lokalnego w dniu zeskanowania wydania do doręczenia przez JD nawet co 2 minuty, dopóki odpowiada międzynarodowy system JD (co 5 minut przy otwieraniu chińskiego `jdl.com`); wyłącznie przez serwisy śledzenia sprawdza co godzinę, co 3 godziny po dobie ciszy i nawet co 15 minut w dniu doręczenia przy otwartej karcie lub włączonych alertach. W pozostałym czasie oraz po dwóch dniach bez ruchu (12 godzinach na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika”) sprawdza co godzinę. Gdy doręczenie przejmuje lokalny przewoźnik, Peek przechodzi na jego rytm odpytywania (w **Stanach Zjednoczonych** GOFO Express: nawet co 30 minut, lub co godzinę po nieudanej próbie pobrania, także w dniu dostawy). Możesz włączyć alerty „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek nie pośredniczy w kontakcie z JD Logistics ani ze sklepem.

## Najczęstsze pytania o śledzenie JD Logistics

### Czy JD Logistics to wiarygodna firma?

Tak. JD.com rozpoczęło budowę własnej sieci logistycznej w 2007 roku, wydzieliło grupę JD Logistics w kwietniu 2017 roku i wprowadziło ją na giełdę w Hongkongu w maju 2021 roku (kod `2618`). Według podstrony o firmie sieć obejmuje ponad 1600 magazynów własnych i 2000 magazynów chmurowych prowadzonych przez podmioty zewnętrzne na 26 rynkach. Na świecie JD Logistics prowadzi ponad 200 magazynów celnych, bezpośredniej dystrybucji międzynarodowej i zagranicznych.

### Jak śledzić paczkę JD Logistics?

Na stronie `jingdonglogistics.com/Tracking` (do 100 numerów oddzielonych spacjami), na `jdl.com/orderSearch` w przypadku przesyłki na terenie Chin albo wklejając numer na górze tej strony.

### Kto doręcza paczki JD Logistics w Polsce?

JD nie publikuje listy partnerów ostatniej mili dla przesyłek z Chin, natomiast w Polsce prowadzi dwa magazyny pod Warszawą — w Urzucie (`PL-JD-WAW1`) i Brwinowie (`PL-JD-WAW2`) — a w Europie rozwija od kwietnia 2025 roku markę ekspresową JoyExpress. Jeżeli na końcu wiersza `Departed from Warehouse.` po `Tracking no` pojawi się numer lokalnego przewoźnika rozpoznawanego przez Peek, Peek automatycznie zacznie śledzić ten numer po pierwszym skanie u doręczyciela.

### Dlaczego śledzenie JD Logistics się nie aktualizuje?

Sprzedawca mógł jeszcze nie przekazać paczki do JD, przesyłka może być w transporcie lotniczym lub na odprawie celnej albo lokalny doręczyciel nie przesłał jeszcze skanów. Po czterech dniach bez nowego wpisu skontaktuj się ze sprzedawcą lub napisz do polskiej obsługi JD (`org.cs.intl1@jd.com`, pon.–pt. 09:00–17:00). Dla przesyłki wewnątrz Chin sprawdź `jdl.com`.

### Jak skontaktować się z JD Logistics w Polsce?

Przez adres e-mail `org.cs.intl1@jd.com` podany dla Polski na stronie `jingdonglogistics.com/contact` (czynny od poniedziałku do piątku w godz. 09:00–17:00, z wyłączeniem weekendów i świąt). W Chinach działa infolinia `950616` (`0086 950616` z zagranicy). W sprawach zamówienia i zwrotu środków pisz najpierw do sklepu.

:::sources
- [JD Logistics: Track your shipment (po angielsku)](https://www.jingdonglogistics.com/Tracking) – międzynarodowa wyszukiwarka po angielsku, do 100 numerów oddzielonych spacjami, Enter lub przycisk Track
- [JD Logistics: About JINGDONG Logistics (po angielsku)](https://www.jingdonglogistics.com/about) – spółka logistyczna JD.com (HKEX: 2618), marki JoyLogistics i JoyExpress, ponad 1600 magazynów własnych, 2000 chmurowych i ponad 200 międzynarodowych na 26 rynkach
- [JD Logistics: Contact Us (po angielsku)](https://www.jingdonglogistics.com/contact) – skrzynki obsługi klienta (w tym Polska: org.cs.intl1@jd.com, pon.–pt. 09:00–17:00), godziny pracy i infolinia 950616
- [JD Logistics: Cross-Border Parcels (po angielsku)](https://www.jingdonglogistics.com/ser/CrossExport) – obsługiwane kraje, warianty standard/express/economic, transport lotniczy i doręczenie lokalne średnio w 3–6 dni roboczych
- [JD Logistics: JINGDONG Logistics (Poland) (po angielsku)](https://www.jingdonglogistics.com/ser/GlobalWarehousePL) – polskie magazyny PL-JD-WAW1 w Urzucie i PL-JD-WAW2 w Brwinowie pod Warszawą
- [JD Logistics: JINGDONG Logistics (Germany) (po angielsku)](https://www.jingdonglogistics.com/ser/GlobalWarehouseDE) – magazyny DE-JD-FRA1 w Buseck i DE-JD-DTM1 w Unnie
- [JD Logistics: JINGDONG Logistics (France) (po angielsku)](https://www.jingdonglogistics.com/ser/GlobalWarehouseFR) – magazyn FR-JD-PAR1 w Dugny pod Paryżem
- [JD Logistics: JINGDONG Logistics (The Netherlands) (po angielsku)](https://www.jingdonglogistics.com/ser/GlobalWarehouseNL) – magazyny NL-JD-VEN1 w Venray i NL-JD-TIL1 w Tilburgu
- [JD Logistics: JINGDONG Logistics (United Kingdom) (po angielsku)](https://www.jingdonglogistics.com/ser/GlobalWarehouseUK) – magazyn GB-JD-BHM1 w Lutterworth (Anglia)
- [JD Logistics: JINGDONG Logistics (USA) (po angielsku)](https://www.jingdonglogistics.com/ser/GlobalWarehouseUS) – magazyny w USA (New Jersey, rejon Los Angeles i Georgia)
- [JD Logistics: JINGDONG Logistics (Australia) (po angielsku)](https://www.jingdonglogistics.com/ser/GlobalWarehouseAS) – magazyny w Sydney
- [JDL: 运单查询 (wyszukiwanie listów przewozowych, po chińsku)](https://www.jdl.com/orderSearch) – chińska wyszukiwarka po numerze listu lub paczki, obsługa wielu numerów
- [JDL: 服务细则 (regulamin usług, po chińsku)](https://www.jdl.com/serviceDetails) – zakładka międzynarodowa: cło i podatki poza frachtem, szacowany czas wyłącznie orientacyjny bez zwrotu opłat i odszkodowania oraz brak zwrotów spoza Chin kontynentalnych
- [JDL: International Express (po angielsku)](https://www.jdl.com/en/crossBorder/internationalC2C) – ekspres z Chin kontynentalnych za granicę i numer 0086 950616
- [JDL: Contact Us (po angielsku)](https://www.jdl.com/en/contactUs) – infolinia 950616 oraz przycisk Online Consultation
- [JDL: Company Profile (po angielsku)](https://www.jdl.com/en/profile) – powstanie grupy w kwietniu 2017, debiut w Hongkongu w maju 2021 i start marki JoyExpress w Europie w kwietniu 2025
- [Komisja Europejska: EU Customs Reform (po angielsku)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – cło 3 € za kategorię towaru do 150 € od 1 lipca 2026 r. oraz unijna opłata manipulacyjna 2 € od 1 listopada 2026 r.
- [Komisja Europejska: Guidance on the EUR 3 customs duty and the Union handling fee (PDF, po angielsku)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – opłata 2 € niezależnie od wartości przesyłki oraz definicja kategorii towarowej
- [Poczta Polska: zmiany w opłatach dla przesyłek spoza UE od 1 lipca 2026 r.](https://www.poczta-polska.pl/news/zmiany-w-oplatach-dla-przesylek-spoza-unii-europejskiej-od-1-lipca-2026-r/) – VAT, opłata za dokonanie zgłoszenia celnego, pobór przy doręczeniu lub przed odbiorem oraz możliwość odmowy przyjęcia w Polsce
- [Poczta Polska: uwaga na fałszywe wiadomości Pocztex](https://www.poczta-polska.pl/news/uwaga-na-falszywe-wiadomosci-pocztex-grozna-kampania-phishingowa/) – ostrzeżenie przed fałszywymi linkami do płatności SMS w Polsce
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formaty `JD` i `VG`, treść skanów i kody JD, przekazanie do GOFO Express, obsługa `jdl.com` i wyświetlanie w Peek
:::
