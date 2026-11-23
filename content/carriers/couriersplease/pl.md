---
title: Śledzenie CouriersPlease: numery, statusy i pomoc
description: Śledź paczkę CouriersPlease w Australii: format numeru przesyłki, statusy, punkty HUBBED, ponowne doręczenie, Delivery Choices i reklamacje.
slug: sledzenie-couriersplease
published: 2026-11-23
updated: 2026-11-23
---

CouriersPlease (Couriers Please Pty Limited, część grupy FMH) to australijska firma kurierska doręczająca przesyłki w dużych miastach i ośrodkach regionalnych Australii poprzez lokalnych franczyzobiorców (*Franchise Partners*). Aby sprawdzić przesyłkę, wpisz jej numer na [stronie śledzenia CouriersPlease](https://www.couriersplease.com.au/tools-track) — obsługującej do 10 numerów oddzielonych przecinkami — albo wklej go w pole powyżej. Numer przesyła sklep lub nadawca, który zamówił kuriera.

## Numer przesyłki CouriersPlease: jak wygląda

CouriersPlease nazywa go *consignment number* (numerem przesyłki lub śledzenia). Strona ponownego doręczenia podaje, że może zaczynać się od `CP` i mieć do 20 znaków. Sama wyszukiwarka przyjmuje od 8 do 30 liter i cyfr (z co najmniej jedną cyfrą), co obejmuje listy przewozowe, etykiety paczek, kupony przedpłacone i kody SSCC drukowane przez część nadawców. Nie każdy numer CouriersPlease zaczyna się więc od `CP`.

| Numer (wymyślony) | Co to jest |
| --- | --- |
| `CPABC4829173640` | Typowy numer przesyłki CouriersPlease: `CP`, litery, a następnie cyfry. |
| `CPA48291736405` | Inny, nieco krótszy format CouriersPlease zaczynający się od `CP`. |
| `CP482917367AU` | To nie CouriersPlease: dwie litery, dziewięć cyfr i `AU` to międzynarodowy format pocztowy używany m.in. przez [Australia Post](carrier:australia-post). |

**Gdzie go znaleźć.** Sprawdź najpierw wiadomość z potwierdzeniem zamówienia ze sklepu. Oficjalne e-maile od CouriersPlease przychodzą z adresu `noreply@couriersplease.com.au` (tylko jeśli nadawca podał Twój e-mail), a SMS-y wyświetlają nadawcę `Courierspls`. Papierowe awizo zawiera numer karty ponownego doręczenia (*redelivery card number*), również przyjmowany na stronie ponownego doręczenia. Więcej wskazówek znajdziesz w poradniku [jak znaleźć numer przesyłki](guide:find-tracking-number).

**Numer nieprawidłowy lub brak danych?** Sprawdź literówki i potwierdź w sklepie, czy przesyłkę nadano przez CouriersPlease. Dopóki w systemie nie ma żadnych skanów dla danego numeru, strona śledzenia zgłasza go jako nieprawidłowy lub nieznany.

## Statusy śledzenia CouriersPlease

W [poradniku odbiorcy](https://www.couriersplease.com.au/faqs) CouriersPlease wymienia pięć głównych etapów powiadomień: zgłoszenie paczki do odbioru, odbiór od nadawcy, przybycie do oddziału, wyjazd do doręczenia oraz doręczenie lub przekazanie do punktu odbioru. W systemie śledzenia komunikaty są po angielsku; poniżej najważniejsze statusy obsługiwane przez CouriersPlease i mapowane w [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper), w typowej kolejności:

| Status CouriersPlease | Co oznacza dla Ciebie |
| --- | --- |
| `Shipment information received` | Nadawca zarejestrował przesyłkę. CouriersPlease może jeszcze nie mieć fizycznie paczki. |
| `Your parcel has been picked up` | CouriersPlease odebrało paczkę od nadawcy. |
| `Pickup attempt was unsuccessful. Awaiting rebook confirmation by sender` | Próba odbioru od nadawcy nie powiodła się; sprzedawca musi ponowić zlecenie. |
| `Arrived at facility for processing` | Paczka dotarła do centrum przetwarzania CouriersPlease. |
| `Parcel requires further processing` | Przesyłka wymaga dodatkowego opracowania w sortowni; nadal znajduje się w sieci. |
| `Processed at delivery centre` | Opracowana w centrum doręczeń. |
| `In transit to delivery centre` | W drodze do centrum doręczeń (często z nazwami obu miast w nawiasach). |
| `Arrived at delivery centre` | Dotarła do centrum doręczeń; nie musisz nic robić. |
| `In transit to regional delivery centre` | Przekazana regionalnemu partnerowi lub w trasie do centrum regionalnego. |
| `Onboard for delivery` | W samochodzie kuriera lub franczyzobiorcy w drodze na doręczenie. |
| `Delivery rescheduled for next business day` | Wróciła do oddziału niedoręczona i została przełożona na kolejny dzień roboczy. |
| `Delivery attempted – Unsafe to leave – Sent to a collection point` | Nikogo nie było i brakowało bezpiecznego miejsca; paczka trafia do punktu odbioru. |
| `Delivery attempted – Signature required – Redirected to a collection point` | Wymagano podpisu, a nikogo nie zastano; przekierowanie do punktu odbioru. |
| `Unable to deliver` | Nie udało się doręczyć; sprawa wymaga weryfikacji przez obsługę klienta. |
| `Processing for collection at a nearby collection point` | Paczka jest w punkcie odbioru, ale nie jest jeszcze gotowa do wydania. |
| `Pickup at Collection Point – Ready for collection` | Gotowa do odbioru w punkcie z użyciem kodu PIN. |
| `Delivered with Authority to Leave (ATL)` | Pozostawiona w bezpiecznym miejscu pod adresem bez podpisu. |
| `Delivered` | Doręczona pod wskazany adres. |
| `Parcel has been collected from` | Odebrana z punktu odbioru podanego dalej w opisie. |
| `Collection period expired – Return to sender in progress` | Minął termin odbioru; trwa zwrot do nadawcy. |

Najwięcej wątpliwości budzi `Shipment information received`: dane wysyłki są już w systemie, lecz paczka często nadal czeka w sklepie. Jeśli status długo się nie zmienia, zapytaj sprzedawcę, czy przekazał przesyłkę kurierowi.

Komunikaty takie jak `Delivery rescheduled` oraz wpisy `Delivery instructions –` (zmiana daty, punkt odbioru, sąsiad, zgoda na pozostawienie lub nowy adres) oznaczają wyłącznie zapisanie dyspozycji lub nowej daty i same w sobie nie przemieszczają jeszcze paczki.

## Doręczenie, punkty HUBBED, ponowne doręczenie i Delivery Choices

**Szacowany czas dostawy.** [Poradnik odbiorcy](https://www.couriersplease.com.au/faqs) podaje 1–3 dni robocze między dużymi miastami, kilka dni więcej na trasach przez cały kraj (np. wschód–zachód), dłużej dla obszarów regionalnych i odległych oraz dodatkowy tydzień w szczytach takich jak Boże Narodzenie. Wszystkie terminy są orientacyjne i obejmują realizację zamówienia, pracę oddziału oraz ewentualne ponowne doręczenie lub odbiór; pogoda, ruch drogowy i nieprzewidziane zdarzenia mogą je wydłużyć.

**Gdy nikogo nie ma w domu (Authority to Leave).** Podczas doręczenia kurier puka lub dzwoni do drzwi, chyba że nadawca zamówił doręczenie bezkontaktowe. Zgodnie z punktem 5.3 [Warunków przewozu](https://www.couriersplease.com.au/media/Terms_and_Conditions_of_Carriage_12_October_2023_ed4c2f9d36.pdf) oraz stroną [Delivery Choices](https://www.couriersplease.com.au/delivery-choices) przesyłki są domyślnie realizowane w trybie *Authority to Leave* (ATL): jeśli ktoś jest na miejscu, kurier wpisuje na skanerze `SIG` i imię odbiorcy; w razie nieobecności szuka bezpiecznego miejsca. Gdy brak bezpiecznego miejsca (np. mieszkanie w bloku lub wejście przy ruchliwej ulicy) albo wymagany jest podpis, paczka trafia do pobliskiego punktu odbioru — z wyjątkiem przesyłek powyżej 10 kg lub 100 cm objętościowo (rozmiar zbliżony do bagażu podręcznego), dla których trzeba zamówić ponowne doręczenie. W rejonach regionalnych CouriersPlease zawsze pozostawia przesyłki w bezpiecznym miejscu (ATL).

**Punkty odbioru HUBBED.** CouriersPlease współpracuje z siecią HUBBED, zapewniając ponad 3 000 punktów odbioru w całej Australii z wydłużonymi godzinami otwarcia. Najbliższy punkt wyszukasz po dzielnicy lub kodzie pocztowym w [wyszukiwarce punktów odbioru](https://www.couriersplease.com.au/resources/find-a-collection-point) (z filtrami *Drop Off*, *Redirections* i *Failed deliveries*). Jeśli nadawca podał Twój e-mail, CouriersPlease wyśle wiadomość w ciągu 24 godzin; w przeciwnym razie zostawi papierowe awizo. Zgodnie z punktem 5.4 Warunków przewozu doręczenie następuje w momencie wysłania kodu PIN: masz **7 dni** od powiadomienia o PIN-ie na odbiór, po czym paczka jest przechowywana w najbliższym oddziale przez kolejne **5 dni**, a następnie odsyłana na koszt klienta. Jeśli nie dostałeś kodu PIN, zamów go na [stronie Get PIN w HUBBED](https://hubbed.com/getpin/) (dla siebie, firmy lub innej osoby).

**Ponowne doręczenie.** Zlecisz je na [stronie ponownego doręczenia](https://www.couriersplease.com.au/book/redelivery), wpisując numer przesyłki lub numer z karty awizo. Poradnik odbiorcy dodaje, że dla paczki przekazanej do punktu odbioru możesz poprosić o ponowne doręczenie w ciągu 6 dni.

**Delivery Choices.** Jeśli nadawca podał przy rezerwacji zarówno Twój e-mail, jak i numer telefonu komórkowego, otrzymasz bezpieczny link do pięciu opcji na stronie [Delivery Choices](https://www.couriersplease.com.au/delivery-choices):

- wymaganie podpisu (*Signature required* — w razie nieobecności paczka trafia do punktu odbioru lub do oddziału);
- odbiór w pobliskim punkcie (*Collect nearby*);
- zostawienie u sąsiada w promieniu trzech domów po obu stronach (*Leave with a neighbour*);
- zmiana adresu doręczenia (*Change the address*);
- wybór innego dnia w ciągu trzech dni od pierwotnego terminu (*Choose another day*).

## Opóźniona, zaginiona lub uszkodzona paczka CouriersPlease

**Opóźnienie lub brak nowych skanów.** CouriersPlease podaje, że większość opóźnionych przesyłek dociera w ciągu 24–48 godzin roboczych od pierwotnej prognozy. Sprawdź komunikaty sieciowe na stronie głównej couriersplease.com.au, a jeśli sytuacja budzi niepokój, otwórz czat *Virtual Assistant* (ikona w prawym dolnym rogu), wybierz *Assistance with a Delivery* i wyślij zgłoszenie tylko raz, bo powielanie zapytań spowalnia obsługę. Więcej podpowiedzi w poradniku [śledzenie nie aktualizuje się](guide:tracking-not-updating).

**Oznaczona jako doręczona, ale jej nie ma.** Sprawdź osłonięte miejsca wokół wejścia, zapytaj domowników, sąsiadów lub recepcję, a następnie skontaktuj się ze sklepem oraz przez *Virtual Assistant*. Zobacz też [doręczona, ale nieodebrana](guide:delivered-not-received) i [zaginiona paczka](guide:lost-parcel).

**Uszkodzenie, zaginięcie i ochrona Parcel Protect.** Dla krajowych przesyłek w Australii [ochrona Parcel Protect Domestic Cover](https://www.couriersplease.com.au/parcel-protect-domestic-cover) zapewnia automatyczne bezpłatne pokrycie do 1 000 AUD, ale nie obejmuje pierwszych 100 AUD szkody, przesyłek doręczonych z *Authority to Leave* (ATL), niewłaściwego opakowania ani wybranych towarów (m.in. przedmiotów wysokiej wartości, gotówki, biżuterii, kart podarunkowych, obrazów ze szkłem, luster, kwiatów, roślin, towarów łatwo psujących się, rzeczy osobistych i elektroniki oraz towarów niebezpiecznych). Pisemne zgłoszenie zaginięcia lub uszkodzenia musi wpłynąć w ciągu **14 dni roboczych** od doręczenia lub planowanej daty doręczenia (zależnie od tego, co nastąpi wcześniej). Jeśli transport zarezerwowano przez zewnętrznego pośrednika, reklamację składa się u tego pośrednika. Jako odbiorca najpierw skontaktuj się ze sklepem, który jest stroną umowy. Poza Parcel Protect punkt 7.3 Warunków przewozu ogranicza standardową odpowiedzialność do ponownego wykonania usługi, jej kosztu albo 100 AUD (z zachowaniem praw konsumenta wynikających z Australian Consumer Law). Więcej w poradniku [uszkodzona paczka](guide:damaged-parcel).

## Kontakt z CouriersPlease i ochrona przed oszustwami

- **Virtual Assistant:** kliknij ikonę czatu w prawym dolnym rogu na [couriersplease.com.au](https://www.couriersplease.com.au/faqs) i wybierz *Assistance with a Delivery* (lub wpisz `feedback`). Każde zapytanie składaj tylko raz.
- **Help Centre:** dostępne z poziomu menu *Help centre* w serwisie couriersplease.com.au.
- **Adres rejestrowy:** Couriers Please Pty Limited (ABN 76 006 144 734), Rosehill Industrial Estate Building C South, Level 1, 5–7 Shirley Street, Rosehill, NSW 2142, Australia.

> Według strony [ochrony przed oszustwami](https://www.couriersplease.com.au/scam-protection) prawdziwe e-maile CouriersPlease przychodzą z adresu `noreply@couriersplease.com.au`, a SMS-y z nazwy nadawcy `Courierspls`. CouriersPlease nigdy nie prosi o zmianę danych bankowych, opłatę za zwolnienie paczki, podanie danych karty lub haseł ani o odpowiedź `Y` w celu aktywacji linku (uważaj na fałszywe domeny w rodzaju `couriersplease.addliik.com` czy `no-reply@wufoo.com`). W razie wątpliwości usuń wiadomość, sprawdź paczkę bezpośrednio na couriersplease.com.au i zgłoś próbę oszustwa do CouriersPlease oraz Scamwatch.

## Śledzenie przesyłek CouriersPlease w Peek

[Peek](/) od razu rozpoznaje CouriersPlease po numerach w formacie `CP` (takich jak `CPABC4829173640` czy `CPA48291736405`) oraz po linkach ze strony couriersplease.com.au/tools-track z parametrem `no=` lub `trackingCode=`. Nie wymaga konta, kodu pocztowego ani telefonu. Jeśli Twój numer CouriersPlease nie zaczyna się od `CP`, wklej link ze śledzenia albo wybierz CouriersPlease przez „Zmień” lub „Zmień przewoźnika”.

Peek odczytuje oficjalny tracker CouriersPlease i pokazuje każdy skan w angielskim brzmieniu przewoźnika, wraz z lokalnym czasem skanu (Australia/Sydney), australijskim stanem (gdy jest podany) oraz prognozowaną datą doręczenia (*Delivery ETA*), gdy przesyłka wyjdzie poza sam wpis zgłoszenia (aż do doręczenia lub zwrotu). Usuwa znaczniki HTML i doklejone linki do wsparcia; nie wyświetla adresu punktu odbioru, kodu PIN HUBBED ani zdjęcia potwierdzającego doręczenie.

Peek sprawdza bezpośrednio u CouriersPlease nawet co 10 minut, gdy masz otwartą przesyłkę lub w dniu doręczenia przy włączonych alertach, oraz co 2 minuty od 6:00 czasu lokalnego w dniu, w którym CouriersPlease zeskanuje paczkę jako wydaną do doręczenia (`Onboard for delivery`); w przeciwnym razie co godzinę. Po 12 godzinach na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika”, albo po dwóch dniach bez nowych skanów, przechodzi na sprawdzanie co godzinę nawet przy otwartym ekranie, a po siedmiu dniach wciąż w stanie „Zarejestrowana” — co 6 godzin. Korzystając z zewnętrznych usług śledzenia, odpytuje co godzinę, co 3 godziny po dniu bez zmian i do co 15 minut w dniu doręczenia.

Po czterech dniach bez nowego skanu na etapie „W transporcie” Peek oznacza opóźnienie (na przykład „Brak aktualizacji od pon., 12 paź”), wyświetla „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika CouriersPlease.” i udostępnia przycisk „Poproś przewoźnika CouriersPlease o pomoc”. Po ponad trzech tygodniach ciszy pojawia się komunikat: „Od ponad trzech tygodni nic nowego. Jeśli przesyłka wciąż nie dotarła, zapytaj sprzedawcę. Peek dalej pyta przewoźnika CouriersPlease.”

Możesz ustawić powiadomienia na „Każdy skan”, „Ważne etapy” albo „Tylko doręczenie”. Peek nie zamówi ponownego doręczenia, nie wygeneruje kodu PIN HUBBED i nie złoży reklamacji Parcel Protect — w tych sprawach skontaktuj się z CouriersPlease lub ze sklepem.

## Pytania o śledzenie CouriersPlease

### Ile trwa doręczenie paczki przez CouriersPlease?

Według poradnika odbiorcy dostawa między dużymi miastami trwa zwykle 1–3 dni robocze, na trasach przez cały kraj kilka dni dłużej, jeszcze dłużej do miejscowości regionalnych lub odległych, a w okresach szczytowych takich jak święta warto doliczyć dodatkowy tydzień.

### Dlaczego moje śledzenie CouriersPlease się nie aktualizuje?

Na długich trasach międzystanowych, przy przekazaniu do partnera regionalnego lub podczas sortowania między skanami może upłynąć kilka dni. CouriersPlease podaje, że większość opóźnionych paczek dociera w ciągu 24–48 godzin roboczych po pierwotnej prognozie; jeśli minęło więcej czasu, skontaktuj się przez *Virtual Assistant* na couriersplease.com.au.

### Jak działają punkty odbioru HUBBED i kod PIN?

Jeśli paczki do 10 kg i 100 cm objętościowo nie da się bezpiecznie zostawić albo wymaga podpisu, trafia ona do jednego z ponad 3 000 punktów HUBBED. Według Warunków przewozu masz 7 dni od wysłania PIN-u na odbiór, potem paczka czeka jeszcze 5 dni w najbliższym oddziale przed zwrotem. Brakujący PIN zamówisz na hubbed.com/getpin/.

### Co zrobić, gdy przesyłka jest uszkodzona lub zostawiona w trybie Authority to Leave?

Od razu powiadom sklep, który wysłał zamówienie. Pisemną reklamację w ramach Parcel Protect trzeba zgłosić w ciągu 14 dni roboczych (pokrycie do 1 000 AUD z wyłączeniem pierwszych 100 AUD), ale ochrona Parcel Protect nie obejmuje przesyłek doręczonych z *Authority to Leave* (ATL).

:::sources
- [CouriersPlease: Track A Parcel Delivery](https://www.couriersplease.com.au/tools-track) – śledzenie do 10 numerów oddzielonych przecinkami
- [CouriersPlease: Guide to receiving](https://www.couriersplease.com.au/faqs) – pięć etapów śledzenia, szacowane czasy dostawy, limity 10 kg / 100 cm, 6 dni na ponowne doręczenie, 24–48 godzin roboczych przy opóźnieniach, Virtual Assistant
- [CouriersPlease: Book A Parcel Redelivery](https://www.couriersplease.com.au/book/redelivery) – numer przesyłki lub karty awizo, prefiks CP, do 20 znaków
- [CouriersPlease: Find a Collection Point](https://www.couriersplease.com.au/resources/find-a-collection-point) – wyszukiwanie po dzielnicy lub kodzie pocztowym i filtry punktów
- [CouriersPlease: Delivery Choices](https://www.couriersplease.com.au/delivery-choices) – domyślne ATL, pięć opcji doręczenia, zasada regionalnego ATL
- [CouriersPlease: Parcel Protect Domestic Cover](https://www.couriersplease.com.au/parcel-protect-domestic-cover) – limit 1 000 AUD, wyłączenie pierwszych 100 AUD i ATL, termin 14 dni roboczych, reklamacje przez pośredników
- [CouriersPlease: Terms and Conditions of Carriage](https://www.couriersplease.com.au/media/Terms_and_Conditions_of_Carriage_12_October_2023_ed4c2f9d36.pdf) – punkty 5.3 (ATL / SIG), 5.4 (7 dni po PIN + 5 dni w oddziale), 7.3 (limit odpowiedzialności) i adres rejestrowy
- [CouriersPlease: Scam protection](https://www.couriersplease.com.au/scam-protection) – oficjalni nadawcy noreply@couriersplease.com.au i Courierspls, oznaki phishingu, Scamwatch
- [CouriersPlease: Our Story](https://www.couriersplease.com.au/our-story) – FMH Group, Franchise Partners, ponad 3 000 punktów HUBBED
- [HUBBED: Get PIN](https://hubbed.com/getpin/) – zamówienie kodu PIN do odbioru w punkcie
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – wykrywanie numerów i linków CouriersPlease, mapa statusów i pola odczytywane przez Peek
:::
