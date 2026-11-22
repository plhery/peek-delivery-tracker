---
title: Śledzenie Raben: numer przesyłki, statusy i dostawa
description: Śledź paletę lub przesyłkę drobnicową Raben: wygląd numeru, znaczenie statusów w myRaben, rozładunek, reklamacje szkód i kontakt.
slug: sledzenie-raben
published: 2026-11-22
updated: 2026-11-22
---

Grupa Raben to rodzinna europejska grupa logistyczna założona w 1931 roku w Holandii, dziś z siedzibą koło Poznania. Jej sieć drogowa przewozi palety i przesyłki drobnicowe przez oddziały w 17 krajach Europy, realizując też dostawy dla sklepów internetowych — większe zamówienie może więc przyjechać ciężarówką Raben. Śledź je po numerze przesyłki w polu „Shipment tracking” na raben-group.com albo wklej numer powyżej.

## Numer przesyłki Raben: wygląd i gdzie go znaleźć

Raben nadaje każdej przesyłce własny numer. Ma go firma zlecająca transport, zwykle sklep lub producent: sprawdź e-mail z potwierdzeniem wysyłki lub stronę zamówienia. Polskie spółki Raben podkreślają, że dane odbiorcy otrzymują od zleceniodawcy transportu, więc bez numeru zapytaj nadawcę. Więcej w [jak znaleźć numer przesyłki](guide:find-tracking-number).

| Numer (wymyślony) | Co to jest |
| --- | --- |
| `274829173648291` | 27 oraz 13 kolejnych cyfr: format numeru przesyłki Raben w katalogu Peek. |
| `27 4829 1736 4829 1` | Ten sam numer ze spacjami. Spacje i myślniki w Peek nie mają znaczenia. |
| `oftc.myraben.com/link/ShipmentInformation?ShipmentNumber=274829173648291` | Link do konkretnej przesyłki w śledzeniu Raben, otwierany przez wyszukiwarkę na raben-group.com. Wklej cały link do Peek. |

15-cyfrowe numery stosują także [BRT](carrier:brt) we Włoszech i Yunda w Chinach, więc taki format nie zawsze oznacza Raben. Wyszukiwarka Raben znajduje przesyłkę również po referencji lub etykiecie paczki, ale Peek śledzi wyłącznie właściwy numer przesyłki.

## Co oznaczają statusy śledzenia Raben

System śledzenia Raben (myRaben) pokazuje status, każdy etap z miejscowością i godziną, przewidywaną datę dostawy, wagę, liczbę paczek i objętość, pasek postępu, uwagi oraz dane kontaktowe oddziału Raben obsługującego przesyłkę. Kolejne etapy w typowej kolejności:

| Komunikat Raben | Co oznacza dla ciebie | Status w Peek |
| --- | --- | --- |
| `registered in the system` | Nadawca zlecił transport. Raben ma zlecenie, ale jeszcze nie towar. | „Zarejestrowana” |
| `arrived to the pick-up place` | Samochód dotarł do nadawcy po odbiór przesyłki. | „Zarejestrowana” |
| `collected` | Raben odebrało przesyłkę od nadawcy. | „Przyjęta przez przewoźnika” |
| `loaded` | Załadowana na ciężarówkę: do kolejnego oddziału lub w trasę doręczeń. | „W transporcie” |
| `unloaded` | Rozładowana w kolejnym oddziale. | „W transporcie” |
| `Inventory in a warehouse` | Przyjęta na magazyn oddziału. | „W transporcie” |
| `shipment in depo` | W oddziale. | „W transporcie” |
| `in progress - advice required` | W drodze. Dostawa wymaga wcześniejszej awizacji odbiorcy. | „W transporcie” |
| `in progress - fix delivery` | W drodze, z dostawą na ustalony dzień. | „W transporcie” |
| `in delivery` | W trasie doręczenia. | „W doręczeniu” |
| `arrived to the delivery place` | Kierowca dotarł pod adres dostawy. | „W doręczeniu” |
| `delivered` | Doręczona. | „Dostarczona” |
| `delivered D&D` | Doręczona i rozładowana przez kierowcę. | „Dostarczona” |
| `not delivered - self collection` | Niedoręczona: oznaczona do odbioru własnego w oddziale, choć dostawa może jeszcze nastąpić. Skontaktuj się z oddziałem podanym w śledzeniu, zanim pojedziesz. | „Do odbioru” |
| `not delivered - consignee not available` | Kierowca nie zastał cię pod adresem. | „Próba doręczenia” |

**Inne nieudane doręczenia.** `not delivered - delivery refused`, `not delivered - wrong address`, `not delivered - truck with lift required` (wymagany samochód z windą) i `not delivered - wrong/missing docs` podają przyczynę. Peek wyświetla „Próba doręczenia”.

**Wstrzymania.** `not delivered - breakdown lorry` i `not delivered - strike` to opóźnienia na trasie; `not delivered - at sender disposal` oznacza wstrzymanie przesyłki do dyspozycji nadawcy; `cancelled` oznacza anulowanie transportu. Peek wyświetla „Zgłoszono problem”.

**Doręczona z adnotacją.** `delivered - damages`, `delivered - discrepancies`, `delivered - partly returned` oraz `delivered with delay` oznaczają przekazanie przesyłki z opisem zastrzeżenia. Peek wyświetla „Dostarczona”.

**Zmiany planu.** `new delivery date agreed with consignee` i `shipment parameters updated` aktualizują termin lub dane — towar się nie przemieścił.

**Odprawa celna.** Przesyłki celne otrzymują wpisy takie jak `customs: clearance in progress` i `customs clearance completed`; `customs: lack of invoices / incomplete invoices` oznacza brak faktur dla urzędu celnego — zapytaj sklep. Zobacz [przesyłki zatrzymane na cle](guide:customs) oraz [znaczenie statusów](guide:tracking-statuses).

## Jak doręcza Raben: palety, rozładunek i terminy

FAQ dotyczące transportu paletowego Raben Polska opisuje zasady dostawy. Usługi dodatkowe wykupuje klient Raben, czyli firma zlecająca transport (zwykle sklep):

- **Rozładunek.** Rozładunek leży po stronie odbiorcy: Raben nie odpowiada za niego ani za zapewnienie wózka widłowego czy rampy. Zleceniodawca może dokupić ręczny rozładunek przez kierowcę (HUN) lub rozładunek w miejscu dostawy (UNLO). Przy ciężkim towarze zapytaj sklep, co wykupił.
- **Terminy.** W Polsce paleta jedzie standardowo jeden dzień roboczy lub dwa, zależnie od wykupionej usługi; za granicą czas zależy od kraju docelowego. Zleceniodawca może też zamówić dostawę na konkretny dzień (FIX), do godz. 8:00, 10:00, 12:00 lub 16:00 (ND 08–ND 16) albo na umówiony dzień i godzinę (WXACT).
- **Nieobecność.** Jeśli dostawa nie dojdzie do skutku z winy odbiorcy, Raben może obciążyć zleceniodawcę opłatą.
- **Zdjęcia z dostawy.** Przy bezpłatnej usłudze Picture Confirming Delivery (PCD), wybieranej przez zleceniodawcę, nie musisz być obecny. Kierowca zostawia towar w uzgodnionym miejscu i fotografuje etykietę, przesyłkę oraz oba razem — zdjęcia zastępują dokument dostawy.

## Przesyłka Raben opóźniona, uszkodzona lub niedoręczona

**Brak postępu.** Jeśli przez kilka dni nie ma nowych wpisów, zapytaj sklep albo oddział Raben wskazany w śledzeniu. Zobacz [gdy śledzenie się nie aktualizuje](guide:tracking-not-updating).

**Opóźnienie.** W UE sprzedawca musi dostarczyć zamówienie w ciągu 30 dni, o ile nie uzgodniono innego terminu — przypomina unijny portal Your Europe. Po tym czasie możesz wyznaczyć dodatkowy termin, a następnie odstąpić i odzyskać pieniądze.

**Uszkodzenie lub zagubienie.** Sprzedawca odpowiada za szkody od nadania do odbioru, więc według Your Europe najpierw skontaktuj się ze sprzedawcą. Zobacz [uszkodzona przesyłka](guide:damaged-parcel) i [zagubiona przesyłka](guide:lost-parcel). Zasady Raben:

- **Polska.** Raben Logistics Polska przyjmuje pisemne reklamacje od nadawcy lub odbiorcy: przez myClaim (formularz online bez konta myRaben) albo pocztą, faksem lub e-mailem. Widoczne szkody zgłoś przy odbiorze, a szkody ukryte w ciągu 7 dni od doręczenia (w transporcie międzynarodowym niedziele i święta nie wliczają się). Raben odpowiada w ciągu 30 dni od otrzymania kompletu dokumentów. Przesyłka krajowa uznawana jest za zaginioną od 31. dnia po planowanej dacie dostawy.
- **Niemcy.** Raben Niemcy prosi o sprawdzenie opakowania i kompletności przy odbiorze oraz szczegółowe wpisanie widocznych szkód na dokumencie dostawy z kontrasygnatą kierowcy. Zgłoś szkodę pisemnie do właściwego oddziału Raben; szkody ukryte niezwłocznie po wykryciu, najpóźniej 7 dni po dostawie.

## Kontakt i obsługa klienta Raben

Dane ze stron Raben:

- **Kontakt dla twojej przesyłki.** Raben prosi o kontakt z dedykowanym opiekunem pokazywanym na stronie statusu przesyłki, którego Peek nie wyświetla.
- **Godziny pracy.** Obsługa klienta Raben Polska pracuje od poniedziałku do piątku 8:00–16:00 (czasu polskiego), dąży do odpowiedzi w ciągu 3 godzin i standardowo obsługuje po polsku.
- **Oddziały.** [Strona kontaktowa](https://www.raben-group.com/contact) Raben wyszukuje oddział po kodzie pocztowym i podaje listę oddziałów według krajów; każda strona krajowa, np. polska.raben-group.com, publikuje własne dane kontaktowe.

## Śledzenie przesyłki Raben w Peek

15-cyfrowy format dzieli Raben z [BRT](carrier:brt) i innymi przewoźnikami, dlatego [Peek](/) nie rozpoznaje Raben z samego numeru. Wklej go mimo to: Peek obsługuje ponad 4500 przewoźników. Podczas dodawania Peek pyta pasujących przewoźników („Sprawdzanie usług śledzenia…”) i wyświetla „Raben ma tę przesyłkę.”, gdy system Raben rozpozna numer; jeśli rozpozna go kilka firm, wybierasz właściwą. Gdy jeszcze nikt go nie zna, widać „Brak historii śledzenia”, a Peek sprawdza dalej — albo „Oczekiwanie na przewoźnika Raben”, gdy sam wskażesz Raben. Aby wybrać przewoźnika ręcznie, kliknij „Zmień” i wybierz „Raben” w sekcji „Pasują do numeru” lub wyszukaj nazwę („Raben Group” też pasuje). Przy już dodanej przesyłce użyj „Zmień przewoźnika”.

Link z myraben.com lub oftc.myraben.com od razu przypisuje przesyłkę do Raben. Jeśli twój numer Raben ma inny format, wybierz Raben ręcznie: Peek odpytuje śledzenie Raben dla każdego ciągu od 4 do 40 liter i cyfr z co najmniej jedną cyfrą.

Peek odczytuje śledzenie Raben po angielsku: status, każdy etap w brzmieniu Raben z miastem i krajem, planowany dzień dostawy (data bez godziny) lub — gdy trasa kierowcy go wyznaczy — przedział godzinowy, wagę oraz czas doręczenia. Godziny wyświetlane są w twojej strefie czasowej („Godziny w twojej strefie czasowej:”). Etapy zmieniające wyłącznie datę lub parametry zachowują dotychczasowy status. Peek pomija liczbę paczek, objętość, pasek postępu, uwagi i kontakt do oddziału. Publiczne śledzenie nie udostępnia danych nadawcy ani odbiorcy, adresu, punktu odbioru, nazwy usługi ani potwierdzenia doręczenia.

Peek odpytuje Raben bezpośrednio nawet co 10 minut, a w dniu, gdy Raben zeskanuje przesyłkę w doręczeniu — nawet co 2 minuty od 6:00 czasu polskiego lub pół godziny przed otwarciem zaplanowanego okna czasowego, gdy przesyłka jest otwarta w Peek albo w dniu dostawy z włączonymi powiadomieniami. Po dwóch dniach bez zmian (po 12 godzinach, gdy przesyłka ma jeszcze status „Zarejestrowana” lub „Przyjęta przez przewoźnika”) Peek sprawdza co godzinę. Dla powiadomień możesz wybrać „Wszystkie aktualizacje”, „Tylko ważne” lub „Tylko w dniu dostawy”. Po czterech dniach bez skanu w transporcie, poza odprawą celną i zgłoszonym problemem, Peek wyświetla „Brak aktualizacji od…” i przypomina, że „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika Raben.”. Peek nie skontaktuje się za ciebie z Raben ani ze sklepem, nie zamówi rozładunku i nie złoży reklamacji.

## Najczęstsze pytania o śledzenie Raben

### Czym jest Grupa Raben?

Rodzinną europejską grupą logistyczną założoną w 1931 roku w Winterswijk w Holandii, z siedzibą w Robakowie pod Poznaniem. Przewozi palety i drobnicę drogą lądową w 17 krajach, zwykle na zlecenie nadawcy.

### Jak śledzić przesyłkę Raben?

Wpisz numer przesyłki w polu „Shipment tracking” na raben-group.com i kliknij „Search”: śledzenie Raben otworzy się w nowej karcie. Możesz też wkleić numer lub link w polu powyżej.

### Czy kierowca Raben rozładuje moje zamówienie?

Według zasad Raben Polska wyłącznie wtedy, gdy firma zlecająca transport wykupiła taką usługę: rozładunek należy do odbiorcy, a rozładunek przez kierowcę jest usługą dodatkową (HUN lub UNLO). Status `delivered D&D` oznacza, że kierowca dostarczył i rozładował przesyłkę.

### Ile czasu trwa dostawa Raben?

W Polsce jeden lub dwa dni robocze w zależności od usługi wykupionej przez nadawcę; za granicą zależy od kraju docelowego. Śledzenie Raben pokazuje przewidywaną datę dostawy.

### Z kim się skontaktować w sprawie uszkodzonej przesyłki Raben?

Najpierw ze sklepem: w UE sprzedawca odpowiada za towar do chwili jego odbioru. W Polsce możesz też sam złożyć pisemną reklamację w Raben Logistics Polska — szkody ukryte zgłoś w ciągu 7 dni od doręczenia.

:::sources
- [Raben Group: home page](https://www.raben-group.com/) – pole śledzenia, „Search”, nowa karta, 17 krajów
- [Raben Group: Customer Zone](https://www.raben-group.com/customer-zone) – jeden numer na przesyłkę, planowana data, kontakt na stronie statusu
- [Raben Group: Contact](https://www.raben-group.com/contact) – wyszukiwarka oddziałów według kodu pocztowego, oddziały w krajach
- [Raben Group: Road transport and international transport](https://www.raben-group.com/services/road-network) – przesyłki drobnicowe
- [Raben Group: E-commerce](https://www.raben-group.com/e-commerce) – dostawy dla sklepów internetowych
- [Raben Group: Picture Confirming Delivery](https://www.raben-group.com/it-solutions/pcd) – bezpłatna usługa, zdjęcia, potwierdzenie dostawy
- [Raben Group: Raben Group in Europe](https://www.raben-group.com/about-us/raben-group-in-europe) – 1931, Winterswijk, firma rodzinna, 17 krajów
- [Raben Group: Imprint](https://www.raben-group.com/imprint) – Robakowo pod Poznaniem
- [Raben Poland: Pytania i odpowiedzi - Transport paletowy](https://polska.raben-group.com/strefa-klienta/pytania-i-odpowiedzi-transport-paletowy) – rozładunek, HUN, UNLO, terminy, FIX, ND, WXACT, opłata za niedoręczenie, PCD, godziny obsługi, język polski
- [Raben Poland: Strefa uczestnika przewozu](https://polska.raben-group.com/strefa-uczestnika-przewozu) – dane odbiorcy przekazywane przez zleceniodawcę
- [Raben Logistics Polska: Claims](https://polska.raben-group.com/en/customer-zone/raben-logistics-polska/claims-raben-logistics-polska) – kto reklamuje, tryb zgłoszenia, szkody widoczne i ukryte, zagubienie, odpowiedź w 30 dni
- [Raben Germany: Transportschäden – Wir helfen Ihnen](https://deutschland.raben-group.com/kundenbereich/reklamationen) – sprawdzenie przy odbiorze, zgłoszenie pisemne, 7 dni
- [Your Europe: Your rights when shopping in the EU](https://europa.eu/youreurope/citizens/consumers/shopping/shopping-consumer-rights/index_en.htm) – 30 dni, odpowiedzialność sprzedawcy, kontakt najpierw ze sprzedawcą
- [Universal Parcel Scraper: Raben](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/raben) – format numeru, linki, referencje, co pokazuje strona i odczytuje Peek, statusy
:::
