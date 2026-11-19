---
title: Śledzenie przesyłek Speedaf Express: numery i statusy
description: Śledź paczkę Speedaf Express z Chin do Afryki, na Bliski Wschód czy do wybranych krajów Europy: formaty numerów, znaczenie statusów, cło i kontakt.
slug: sledzenie-speedaf
published: 2026-11-18
updated: 2026-11-20
---

Speedaf Express (速达非) to założona w 2019 roku sieć kurierska, która przyjmuje paczki we własnych magazynach w **Chinach**, wysyła je drogą lotniczą do krajów Afryki, Bliskiego Wschodu, Pakistanu oraz wybranych państw Europy i doręcza na miejscu własnymi kurierami. **Polska** nie znajduje się na liście krajów obsługiwanych przez Speedaf. Aby sprawdzić losy przesyłki, wpisz jej numer w zakładce „Track” na stronie [speedaf.com](https://www.speedaf.com/cn-en/send-parcel), w aplikacji klienckiej Speedaf albo w polu wyszukiwania powyżej. Numer znajdziesz w szczegółach wysyłki swojego zamówienia.

## Numer śledzenia Speedaf: format i przykłady

Numer listu przewozowego Speedaf zaczyna się od dwuliterowego kodu kraju docelowego — np. `NG` dla Nigerii, `KE` dla Kenii czy `PK` dla Pakistanu (dla Szwajcarii, Albanii, Czarnogóry i Serbii Peek nie ma przypisanego osobnego wzoru numeracji). Spotykane są dwa układy znaków, tutaj z wymyślonymi cyframi:

| Przykładowy numer | Co oznacza |
| --- | --- |
| `NG482910573R` | Kod kraju, 9 cyfr i litera na końcu — zazwyczaj `R`, czasem `E`. |
| `KE482910573649` | Kod kraju i 12 cyfr. |
| `PK482910573649T` | Ten sam układ 12-cyfrowy zakończony literą `T`. |
| `LP48291057364918` | To nie numer Speedaf, lecz referencja [Cainiao](carrier:cainiao) z zamówienia na AliExpress. |

Wyszukiwarka na stronie speedaf.com przyjmuje jednocześnie do 20 numerów („Up to 20 numbers”), po jednym w wierszu. Wpisuj tam wielkie litery — małe litery portal odrzuca jako nieznany numer. Jeśli nie możesz znaleźć kodu przesyłki, zajrzyj do poradników [jak znaleźć numer śledzenia](guide:find-tracking-number) oraz [śledzenie zamówień z AliExpress](guide:aliexpress-orders).

## Statusy śledzenia Speedaf i ich znaczenie

Speedaf zapisuje skany po angielsku, w przeciwnym razie po chińsku, a niekiedy także w języku kraju docelowego. Nazwy placówek pojawiają się w nawiasach kwadratowych typu 【tutaj】 w środku komunikatu. Poniżej typowa kolejność etapów paczki lecącej z Chin:

| Komunikat Speedaf | Peek pokazuje | Co to oznacza dla Twojej paczki |
| --- | --- | --- |
| `processing, order has been inbounded` | Przyjęta przez przewoźnika | Wymieniony wcześniej magazyn Speedaf w Chinach przyjął przesyłkę na stan. |
| `processing assembling operation`, `processing, order has been outbounded` | W transporcie | Spakowana do worka zbiorczego i wydana z magazynu. |
| `clearance in progress` | W urzędzie celnym | Odprawa eksportowa w Chinach. |
| `Delivery to the airport`, `Cargo flight departured`, `Cargo flight arrived` | W transporcie | Przekazana na lotnisko, wylot samolotu cargo i lądowanie w kraju docelowym. |
| `Clearance handover`, `Customs clearance in progress` | W urzędzie celnym | Przejęta na lotnisku i objęta odprawą importową w kraju przeznaczenia. |
| `pending Tax` | W urzędzie celnym | Przed zwolnieniem paczki z odprawy trzeba opłacić należności celno-podatkowe (zobacz niżej). |
| `Customs clearance tax paid` | W urzędzie celnym | Należności zostały opłacone, odprawa celna trwa dalej. |
| `Customs Clearance Completed` | W transporcie | Urząd celny zwolnił przesyłkę. |
| `Arrived at DC Warehouse`, `Arrival at Speedaf DC` | W transporcie | Paczka dotarła do magazynu centralnego lub centrum dystrybucyjnego Speedaf w kraju docelowym. |
| `Received in` | W transporcie | Przyjęta w pierwszym oddziale Speedaf w kraju przeznaczenia — to nie nowe nadanie. |
| `Loaded at`, `Departed for`, `Arrive at Distribution`, `Arrived at Site` | W transporcie | Przewóz między sortowniami aż do lokalnej stacji doręczeń. |
| `scanned for delivery` | W doręczeniu | Kurier zeskanował paczkę i zabrał ją w trasę. |
| `Issue Parcel Reason【Rescheduled delivery】` | Próba doręczenia | Doręczenie przełożono na dzień podany w nawiasie na końcu wpisu. |
| `Issue Parcel Reason【Receiver Rejected Parcel】` | Zgłoszono problem | Odbiorca odmówił przyjęcia przesyłki. |
| `Issue Parcel Reason【Delayed payment of tax by customer】` | W urzędzie celnym | Kurier zabrał paczkę z powrotem, ponieważ opłaty celno-podatkowe nie zostały jeszcze uregulowane. |
| `Please come to collect with the Pick-Up Code` | Do odbioru | Przesyłka czeka na odbiór z kodem odbioru we wskazanej placówce Speedaf. |
| `Parcel delivered 【Collected】`, `【Parcel delivered】` | Dostarczona | Doręczona pod adres lub odebrana w punkcie; komunikat może wymieniać placówkę odbioru. |
| `Transported to the warehouse，【Return Overdue】` | Zgłoszono problem | Przesyłka wraca do magazynu Speedaf po przekroczeniu czasu oczekiwania. |
| `Warehouse received` | Zwrócona nadawcy | Zwrot dotarł z powrotem do magazynu Speedaf. |

Sam napis `In Transit` może kryć chwilowe wstrzymanie: pod wewnętrznym kodem tego zdarzenia kryje się paczka zatrzymana do inwentaryzacji. Komunikaty `Products not for personal use` oraz `Clearance Exception` oznaczają zastrzeżenie lub pytanie ze strony służb celnych, natomiast `Long Time Tax No Paid,Email To Sender` informuje, że należności pozostają nieopłacone od dłuższego czasu i Speedaf wysyła wiadomość do nadawcy. W wersji chińskiej najważniejsze etapy to `进行处理，订单已操作入库` („Przyjęta przez przewoźnika”), `清关中` („W urzędzie celnym”), `已做派件扫描` („W doręczeniu”) oraz `包裹【已签收】` („Dostarczona”).

## Doręczenie w Speedaf: pod drzwi, punkt odbioru i próby

Na ostatniej mili Speedaf korzysta z własnych kurierów, oferując doręczenia pod drzwi oraz pobranie przy odbiorze (COD). Mapa placówek jest dostępna na stronie przewoźnika w sekcji „Look for Service Site”.

Zasady doręczeń wynikają z regulaminów poszczególnych krajów pisanych z myślą o nadawcy — regulaminy dla Nigerii i Zjednoczonych Emiratów Arabskich dotyczą przy tym tamtejszych przesyłek krajowych:

- **Zjednoczone Emiraty Arabskie:** każda przesyłka obejmuje 3 bezpłatne próby doręczenia („Every shipment will have 3 free delivery attempts”); jeśli doręczenie się nie powiedzie, paczka wraca do nadawcy.
- **Kenia, Uganda i Pakistan:** przesyłka zostaje zwrócona do nadawcy, gdy trzecia próba doręczenia zakończy się niepowodzeniem („cannot be delivered successfully upon a third attempt”) albo gdy odmówisz przyjęcia paczki lub zapłaty należnych opłat. Wszystkie trzy regulaminy zastrzegają, że Speedaf doręcza pod wskazany adres, ale niekoniecznie do rąk własnych wymienionego z nazwiska odbiorcy („but not necessarily to the named receiver in person”).
- **Nigeria i Zjednoczone Emiraty Arabskie:** paczka jest uznawana za niedoręczalną, gdy odmówisz jej przyjęcia lub zapłaty kwoty pobrania, adres jest błędny bądź leży poza strefą doręczeń albo wybrany przez Ciebie nowy termin doręczenia przekracza czas przechowywania w Speedaf („exceeds Speedaf holding time”). Żaden z tych dwóch dokumentów nie podaje, ile dni wynosi ten okres.

Speedaf nie publikuje jednolitego terminu odbioru paczek ze swoich punktów na całej sieci. W razie nieobecności podczas wizyty kuriera zobacz poradnik [nieudana próba doręczenia](guide:missed-delivery).

## Cło i podatki w Speedaf: co oznacza pending Tax

Jeżeli przed wydaniem przesyłki trzeba uregulować należności importowe, historia śledzenia zatrzymuje się na statusie `pending Tax`. Dla paczek adresowanych do **Republiki Południowej Afryki** strona śledzenia Speedaf wyświetla obok numeru listu przewozowego przycisk „I want to pay taxes”, a po zaksięgowaniu wpłaty pokazuje „Taxes paid”.

Gdy dostaniesz SMS lub e-mail z prośbą o dopłatę, najpierw sprawdź status paczki bezpośrednio na speedaf.com: więcej wskazówek znajdziesz w poradnikach [fałszywe SMS-y o dopłacie do paczki](guide:delivery-scam-texts) oraz [paczka zatrzymana w urzędzie celnym](guide:customs).

## Paczka Speedaf stoi w miejscu, zaginęła lub ma status dostarczonej

**Brak nowych skanów.** Jeśli historia śledzenia nie zmienia się od kilku dni, zajrzyj do poradników [śledzenie paczek z Chin](guide:tracking-from-china) oraz [dlaczego śledzenie się nie aktualizuje](guide:tracking-not-updating).

**Kto może złożyć reklamację.** Według regulaminów dla **Kenii, Ugandy i Pakistanu** roszczenie przysługuje wyłącznie w relacji między nadawcą a Speedaf („The claim is only between the shipper and SPEEDAF”), natomiast w **Nigerii i Zjednoczonych Emiratach Arabskich** reklamacje są rozliczane wyłącznie z nadawcą („Claims will only be settled with the Shipper.”). Przy zakupach internetowych nadawcą jest sklep — w sprawie zwrotu pieniędzy lub ponownej wysyłki skontaktuj się ze sprzedawcą albo platformą zakupową.

**Nie zwlekaj ze zgłoszeniem do sklepu.** Krajowe regulaminy Speedaf wyznaczają krótkie terminy:

- **Uszkodzenie:** zgłoszenie na piśmie w ciągu 7 dni kalendarzowych od doręczenia w **Kenii i Ugandzie**, w ciągu 72 godzin w **Nigerii i Zjednoczonych Emiratach Arabskich** oraz w ciągu 48 godzin w **Arabii Saudyjskiej**.
- **Status dostarczonej bez otrzymania paczki:** zakwestionowanie potwierdzenia odbioru w ciągu 5 dni kalendarzowych w **Nigerii i Zjednoczonych Emiratach Arabskich** albo 5 dni roboczych w **Pakistanie**.
- **Zaginięcie:** w **Nigerii i Zjednoczonych Emiratach Arabskich** reklamacja o zaginięcie w ciągu 14 dni kalendarzowych od odbioru przesyłki od nadawcy. Jeżeli paczka w tych krajach nie ma nowego skanu przez ponad 24 godziny, Speedaf prowadzi poszukiwania przez 7 dni roboczych, a gdy nie uda się jej odnaleźć, uznaje ją za zagubioną.

Wyszukiwarka Speedaf podaje w skanie doręczenia imię lub nazwisko osoby, która pokwitowała odbiór — sprawdź tę informację w pierwszej kolejności. Dalsze kroki opisują poradniki [przesyłka dostarczona, ale nieodebrana](guide:delivered-not-received) oraz [zagubiona paczka](guide:lost-parcel).

## Kontakt ze Speedaf: telefony i obsługa klienta

Każda strona krajowa Speedaf podaje dane kontaktowe w stopce albo na ekranie wyboru regionu (dla Szwajcarii, Irlandii i Zambii wybierz tam dany kraj, a potem „English”; dla Szwajcarii dostępne są wersje po angielsku, chińsku i francusku). Godziny pracy podano w czasie lokalnym danego kraju:

| Kraj | Telefon | E-mail | Godziny |
| --- | --- | --- | --- |
| Szwajcaria | +41 798559737, WhatsApp +41 786030610 | ch.info@speedaf.com | pon.–pt. 8:00–18:30 |
| Nigeria | +234 800 070 7070, +234 708 063 6400, +234 9073064940 (także WhatsApp) | ng.info@speedaf.com, nigeria.info@speedaf.com | pon.–pt. 8:00–17:00, sob. 8:00–14:00 |
| Ghana | +233 30 824 9100 | gh.info@speedaf.com | pon.–sob. 8:30–17:30 |
| Kenia | +254 741 000 666, +254 741 000 888, WhatsApp +254 741000666 | kenya.info@speedaf.com | pon.–pt. 8:00–18:00, sob. 8:00–14:00 |
| Uganda | +256 709 198 795 | ug.info@speedaf.com | pon.–pt. 9:00–17:00, sob. 9:00–14:00 |
| Zambia | +260 971269390 | zm.info@speedaf.com | pon.–pt. 7:45–17:15, sob. 8:00–13:00 |
| Republika Południowej Afryki | +27 108247184, +27 726393849, WhatsApp +27 664562613 | cs.southafrica@speedaf.com | nie podano |
| Pakistan | +92 311 118 7654, WhatsApp +92 3450010817 | pk.info@speedaf.com | pon.–niedz. 9:00–18:00 |
| Zjednoczone Emiraty Arabskie | +971 800 178 | ae.info@speedaf.com | pon.–sob. 9:00–18:00 |
| Arabia Saudyjska | brak numeru telefonu | sa.info@speedaf.com | nie podano |
| Irlandia | +35312633068, WhatsApp +353 834281088 | info_customerserviceie@speedaf.com | pon.–pt. 8:30–17:30 |
| Egipt | +20 15818, +20 1050383370 | eg.info@speedaf.com | sob.–czw. 9:00–18:00 |
| Maroko | +212 522 100 888 | ma.info@speedaf.com | pon.–pt. 9:00–18:00 |
| Chiny | +86 400 888 0975 | info@speedaf.com | pon.–pt. 9:30–18:30 |

Centrum pomocy Speedaf wymienia też infolinie dla pozostałych krajów z listy przewoźnika: Kazachstan `+7 778 371 9358`, Mauritius `4687316`, Malediwy `+960 7678788`, Serbia `+41 798559737` (ten sam numer co dla Szwajcarii), Czarnogóra `+382 60021040` oraz Albania `+355 45620111`. Na każdej stronie krajowej działa ponadto formularz zgłoszeniowy w zakładce „Customer Support”.

## Śledzenie przesyłek Speedaf w Peek

[Peek](/) rozpoznaje numer Speedaf od razu po samym formacie, gdy zaczyna się on od `NG`, `GH`, `KE`, `UG`, `RW`, `ZM`, `ZA`, `EG`, `MA`, `SA`, `AE`, `PK`, `KZ`, `MU`, `RE`, `MV` albo `TR`, po których następuje 9 cyfr oraz litera `R` lub `E`. Jeżeli po jednym z tych kodów krajów albo po `IE` (Irlandia) stoi 12 cyfr (opcjonalnie z literą `T` na końcu), sam kształt numeru nie rozstrzyga przewoźnika: Peek odpytuje Speedaf w tle i pokazuje „Sprawdzanie usług śledzenia…”, a gdy Speedaf potwierdzi paczkę — dopisek „ma tę przesyłkę” przy nazwie Speedaf. Dla Szwajcarii, Albanii, Czarnogóry i Serbii Peek nie ma zdefiniowanego wzoru numeracji — dla tych krajów oraz nietypowych numerów wybierz przewoźnika ręcznie przyciskiem „Zmień”. Link śledzenia ze strony speedaf.com zawierający parametr `tradeNo=` Peek przypisuje do Speedaf niezależnie od układu znaków, a małe litery zamienia automatycznie na wielkie.

Peek pobiera dane bezpośrednio z wyszukiwarki Speedaf: aktualny etap, historię skanów, kraj przeznaczenia oraz czas doręczenia. Każdy wpis jest zapisany w czasie lokalnym danej placówki (UTC+8 w chińskich magazynach, a następnie strefa czasowa kraju docelowego) i przeliczany na strefę czasową Twojego urządzenia; na peektracker.com oraz w aplikacji na iPhone'a wiersz pod osią czasu wskazuje tę strefę („Godziny w Twojej strefie czasowej: …”). Ponieważ Speedaf wpisuje nazwy oddziałów wyłącznie wewnątrz tekstu skanu, widok mapy nie jest dostępny, a przewoźnik nie podaje przewidywanego dnia dostawy. Gdy Speedaf zna już numer, lecz nie opublikował jeszcze żadnego skanu, Peek sprawdza dalej i pokazuje „Oczekiwanie na przewoźnika Speedaf” — albo „Oczekiwanie na pierwszy skan przewoźnika Speedaf” dla numeru 12-cyfrowego bez ręcznego wyboru przewoźnika. Jeżeli przesyłka zapisana pod Speedaf po dwóch dniach nadal nie ma żadnego skanu i nie odpowiedziało dla niej żadne inne źródło, Peek wyświetla komunikat „Po dwóch dniach przewoźnik nadal nie zna tego numeru. Sprawdź go, wybierz innego przewoźnika lub usuń przesyłkę.”, po czym sprawdza numer raz dziennie, a po tygodniu od dodania — raz w tygodniu.

Treść skanów wyświetla się w oryginalnym brzmieniu po angielsku (w braku wersji angielskiej — po chińsku), po usunięciu imion i nazwisk kurierów oraz pracowników sortowni, a także numerów telefonów. Imię lub nazwisko osoby kwitującej odbiór pozostaje widoczne w skanie doręczenia dla osoby, która dodała przesyłkę; na udostępnionym linku Peek ukrywa te dane i pokazuje przy doręczeniu samo `Delivered`. Pomijane są również zdjęcie z doręczenia, link do opłacenia podatków oraz liczba paczek — Peek śledzi wyłącznie wpisany numer listu przewozowego, bez pozostałych części przesyłki wielopaczkowej.

Gdy masz otwartą kartę przesyłki albo w dniu doręczenia masz włączone powiadomienia, Peek sprawdza status maksymalnie co 10 minut — a w dniu przejścia paczki na etap „W doręczeniu” od godziny 6:00 czasu lokalnego maksymalnie co 2 minuty, dopóki odpowiada własna wyszukiwarka Speedaf (przez zewnętrzne serwisy śledzenia co godzinę, po dobie bez nowego skanu co 3 godziny, a w dniu doręczenia przy otwartej paczce lub włączonych powiadomieniach maksymalnie co 15 minut). Poza tymi warunkami oraz po dwóch dniach bez ruchu — lub po 12 godzinach na etapie „Zarejestrowana” bądź „Przyjęta przez przewoźnika” — Peek sprawdza przesyłkę co godzinę. Do wyboru są powiadomienia: „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek nie może w Twoim imieniu opłacić należności celnych, zmienić terminu doręczenia ani skontaktować się ze Speedaf czy ze sklepem.

## Najczęstsze pytania o śledzenie Speedaf

### Czy Speedaf Express to prawdziwa firma kurierska?

Tak. Speedaf powstało w 2019 roku z udziałem chińskiego operatora ZTO wśród pierwotnych udziałowców, prowadzi własną wyszukiwarkę przesyłek pod adresem speedaf.com i zatrudnia własnych kurierów ostatniej mili na obsługiwanych przez siebie rynkach wschodzących.

### Do jakich krajów doręcza Speedaf?

Oprócz Chin lista krajów na stronie Speedaf obejmuje Egipt, Republikę Południowej Afryki, Maroko, Zjednoczone Emiraty Arabskie, Ghanę, Nigerię, Kenię, Ugandę, Arabię Saudyjską, Pakistan, Kazachstan, Mauritius, Malediwy, Szwajcarię, Czarnogórę, Albanię, Irlandię, Serbię oraz Zambię. Polski na niej nie ma; Peek rozpoznaje dodatkowo numery z prefiksami `RE` (Reunion), `RW` (Rwanda) i `TR` (Turcja).

### Ile trwa dostawa przez Speedaf?

Na karcie usługi eParcel dla przesyłek z Chin do Nigerii, Ghany, Kenii, Ugandy, Maroka, Egiptu, Arabii Saudyjskiej, Zjednoczonych Emiratów Arabskich oraz Pakistanu Speedaf podaje orientacyjny czas przewozu („Full Reference Transit Time”) od 5 do 10 dni roboczych. Centrum pomocy zaznacza, że czas zależy od kraju docelowego, a regulaminy dla Kenii, Ugandy i Pakistanu zastrzegają, że terminy doręczenia nie są gwarantowane.

### Co oznacza status pending Tax w Speedaf?

Przed zwolnieniem przesyłki z odprawy celnej w kraju docelowym trzeba opłacić cło lub podatek importowy. Po zaksięgowaniu płatności w historii pojawiają się kolejno wpisy `Customs clearance tax paid` oraz `Customs Clearance Completed`.

:::sources
- [Speedaf: Enterprise Introduction](https://www.speedaf.com/ng-en/business/about-us) – założenie w 2019 r., ZTO wśród udziałowców
- [Speedaf: Select your location and language](https://www.speedaf.com/cn-en/user-opt/locale-select) – lista krajów oraz kontakty dla Szwajcarii, Irlandii, Zambii i Chin
- [Speedaf: Speedaf eParcel](https://www.speedaf.com/ke-en/product/intl-straight-small) – paczki z Chin, orientacyjny czas 5–10 dni roboczych
- [Speedaf: Warehouse & Fulfillment](https://www.speedaf.com/ke-en/product/warehouse-promise) – właśni kurierzy ostatniej mili, pobranie (COD)
- [Speedaf: Last-mile delivery](https://www.speedaf.com/ke-en/product/warehouse-delivery) – doręczenie pod drzwi i pobranie
- [Speedaf: Help Center](https://www.speedaf.com/ng-en/help) – czas przewozu zależny od kraju, śledzenie na stronie i w aplikacji, infolinie krajowe
- [Speedaf: Track](https://www.speedaf.com/ng-en/send-parcel) – do 20 numerów naraz, przycisk opłacenia podatku dla RPA
- [Speedaf: Look for Service Site](https://www.speedaf.com/ke-en/location-map) – mapa placówek
- [Speedaf: Customer Support](https://www.speedaf.com/ng-en/service/support) – formularz kontaktowy
- [Speedaf Nigeria: Home](https://www.speedaf.com/ng-en) – numery telefonów, e-maile, godziny pracy i WhatsApp
- [Speedaf Ghana: Home](https://www.speedaf.com/gh-en) – telefon, e-mail i godziny pracy
- [Speedaf Kenya: Home](https://www.speedaf.com/ke-en) – numery telefonów, e-mail, godziny pracy i WhatsApp
- [Speedaf Uganda: Home](https://www.speedaf.com/ug-en) – telefon, e-mail i godziny pracy
- [Speedaf South Africa: Home](https://www.speedaf.com/za-en) – numery telefonów, e-mail i WhatsApp
- [Speedaf Pakistan: Home](https://www.speedaf.com/pk-en) – telefon, e-mail, godziny pracy i WhatsApp
- [Speedaf UAE: Home](https://www.speedaf.com/ae-en) – telefon, e-mail i godziny pracy
- [Speedaf Saudi Arabia: Home](https://www.speedaf.com/sa-en) – adres e-mail
- [Speedaf Egypt: Home](https://www.speedaf.com/eg-en) – numery telefonów, e-mail i godziny pracy
- [Speedaf Maroc: Accueil](https://www.speedaf.com/ma-fr) – telefon, e-mail i godziny pracy
- [Speedaf Kenya: Terms and Conditions](https://www.speedaf.com/ke-en/service-rule) – próby doręczenia, odmowa przyjęcia, odbiorca, reklamacje i terminy
- [Speedaf Uganda: Terms and Conditions](https://www.speedaf.com/ug-en/service-rule) – analogiczne warunki dla Ugandy
- [Speedaf Pakistan: Terms and Conditions](https://www.speedaf.com/pk-en/service-rule) – próby doręczenia, odbiorca, reklamacje i kwestionowanie potwierdzenia odbioru
- [Speedaf UAE: Terms & Conditions](https://www.speedaf.com/ae-en/service-rule) – przesyłki krajowe: próby doręczenia, paczki niedoręczalne, terminy reklamacji
- [Speedaf Nigeria: Service Agreement](https://www.speedaf.com/ng-en/service-rule) – przesyłki krajowe: paczki niedoręczalne, terminy reklamacji i poszukiwania zagubionych paczek
- [Speedaf Saudi Arabia: Express Shipping Terms](https://www.speedaf.com/sa-en/service-rule) – zgłoszenie szkody w ciągu 48 godzin
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – wzory numeracji, mapowanie statusów, czyszczenie skanów i działanie w Peek
:::
