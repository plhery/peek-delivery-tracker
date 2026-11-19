---
title: GOFO Express śledzenie: numer GFUS, statusy i dostawa w USA
description: Śledź paczkę GOFO Express w Stanach Zjednoczonych: formaty numerów GFUS, GF, CR i MGP, znaczenie angielskich skanów, różnice stref czasowych i pomoc.
slug: gofo-express-sledzenie
published: 2026-11-18
updated: 2026-11-20
---

GOFO Express to firma kurierska doręczająca przesyłki w Stanach Zjednoczonych: odbiera paczki z magazynu nadawcy albo przez partnera spedycyjnego, przekazuje je przez sortownie i regionalne huby do lokalnej stacji doręczeń i dowozi własnymi kierowcami pod drzwi odbiorcy. Niezależnie od tego, czy zamawiasz towar na adres w USA, czy czekasz tam na przesyłkę na miejscu, wklej numer śledzenia w pole powyżej — GOFO prowadzi też własną wyszukiwarkę na swojej stronie internetowej. Numer dostaniesz od sklepu internetowego, w którym złożono zamówienie; jeśli nie możesz go znaleźć, sprawdź [gdzie znaleźć numer przesyłki](guide:find-tracking-number).

## Numer przesyłki GOFO Express: formaty w USA

W Stanach Zjednoczonych GOFO Express korzysta z czterech własnych serii numeracyjnych. Poniżej znajdziesz je z wymyślonymi cyframi — razem z dwoma formatami partnerskimi spotykanymi na tych samych paczkach:

| Numer (wymyślony) | Co oznacza |
| --- | --- |
| `GFUS48291057364918` | `GFUS` i 14 cyfr: podstawowy amerykański numer listu przewozowego (*waybill*) GOFO. Jeśli zaraz po `GFUS` omyłkowo wpiszesz literę `O` zamiast zera, Peek sam zamieni ją na `0`. |
| `GF4829105736491` | `GF` i 13 cyfr: starsza amerykańska seria numerów, nadal obsługiwana przez wyszukiwarkę GOFO. |
| `CR482910573649` | `CR` i 12 cyfr: kolejna amerykańska seria GOFO, niekiedy nadawana równolegle z numerem `GFUS`. |
| `MGP4829105736491` | `MGP` i 13 cyfr: dodatkowy numer zapisywany przez GOFO przy części listów `GFUS`. |
| `FC4829105736491` | `FC` i 13 cyfr: własny numer [DHLink](carrier:dhlink) dla zamówień z DHgate, które w USA doręcza GOFO. |
| `YT2604821273049581` | `YT` i 16 cyfr: numer [YunExpress](carrier:yunexpress), również wtedy, gdy ostatnią milę w USA przejmuje GOFO. |

**Dwa numery dla tej samej paczki.** Część przesyłek ma jednocześnie list przewozowy `GFUS` oraz dodatkowy numer `CR` (12 cyfr) lub `MGP` (13 cyfr): oba prowadzą do tej samej przesyłki zarówno w Peek, jak i w amerykańskim trackerze GOFO. Przy zamówieniach z DHgate doręczanych przez GOFO na zlecenie [DHLink](carrier:dhlink) numerem śledzenia jest kod DHLink (`FC` i 13 cyfr) — Peek odpytuje pod nim DHLink, a amerykański tracker GOFO także znajduje tę paczkę po numerze `FC`.

**Numery `YT` z YunExpress.** Gdy GOFO doręcza w USA paczkę nadaną przez [YunExpress](carrier:yunexpress), śledzenie YunExpress pokazuje również skany GOFO, a wyszukiwarka GOFO w USA przyjmuje ten sam numer `YT`.

**Sieci GOFO w innych krajach.** GOFO prowadzi odrębne sieci krajowe z własnymi prefiksami: numery z `GFCA` należą do GOFO Canada, z `GFFR` lub `CIFR` (oraz wybrane 20-znakowe numery `PK`) do [GOFO France](carrier:gofo-fr), z `GFIT` lub `CIIT` do GOFO Italy, z `GFES` lub `CIES` do GOFO Spain, a z `GFNL` lub `CINL` do GOFO Netherlands. Peek od razu rozpoznaje te prefiksy i odpytuje właściwą sieć krajową.

## Statusy GOFO Express: angielskie komunikaty po polsku

Komunikaty śledzenia GOFO Express w USA są publikowane po angielsku. Przesyłka przechodzi przez kolejne punkty mniej więcej w tej kolejności (nie każda paczka ma wszystkie wpisy pośrednie):

| Komunikat GOFO | Co to oznacza dla Ciebie |
| --- | --- |
| `Shipping Label Created` | Nadawca utworzył etykietę wysyłkową; GOFO nie odebrało jeszcze paczki. |
| `Pick Up Start` | GOFO rozpoczęło zlecenie odbioru u nadawcy, ale nie ma jeszcze paczki fizycznie. |
| `Pickup Exception, GOFO has not Received the Package from the Warehouse yet.` | GOFO nadal nie otrzymało przesyłki z magazynu sprzedawcy. |
| `Picked up by Shipping Partner, GOFO Awaiting Item` | Partner spedycyjny odebrał paczkę dla GOFO; GOFO czeka na jej wejście do swojego centrum. |
| `Pick Up Success` | GOFO odebrało paczkę od nadawcy. |
| `Arrived at GOFO Regional Origin Hub` | Pierwszy fizyczny skan w regionalnym hubie początkowym GOFO. |
| `Arrived at Sorting Center` | Przyjęcie w sortowni przed hubem regionalnym. |
| `Arrived at GOFO Regional Hub` | Przybycie do regionalnego centrum przeładunkowego GOFO. |
| `Processing at GOFO Facility` | Przesyłka jest sortowana w obiekcie GOFO. |
| `Departed GOFO Regional Hub` | Paczka opuściła regionalne centrum przeładunkowe. |
| `Loaded on Vehicle` | Załadowana do pojazdu w drodze do kolejnego obiektu. |
| `In Transit to Next Facility` | W transporcie do następnej stacji GOFO. |
| `Arrived at GOFO Delivery Station` | Dotarła do lokalnej stacji doręczeń GOFO obsługującej Twój adres. |
| `Departed GOFO Delivery Station` | Wyruszyła ze stacji doręczeń. |
| `Out for Delivery` | Kurier wyjechał dziś z paczką w trasę doręczeniową. |
| `Delivered` | Doręczona. |
| `Delivery Exception, Business Closed` | Nieudana próba doręczenia z powodu zamkniętej firmy (powód po przecinku zależy od sytuacji). |
| `Package Returned to GOFO Facility` | Dzień po nieudanej próbie doręczenia paczka wróciła do lokalnej stacji GOFO przed kolejnym podejściem — to nie jest zwrot do sprzedawcy. |
| `Incorrect/Insufficient Address, Additional Details Required for Delivery.` | Nieprawidłowy lub niepełny adres; przesyłka czeka na brakujące dane adresowe. |
| `Address Corrected.` | Adres został uzupełniony i paczka wraca na trasę. |
| `Label Cancelled` | Etykieta została anulowana po wygenerowaniu: pod tym numerem nic już nie przyjdzie. |

Przy wpisie `Delivery Exception, …` Peek ustawia etap „Próba doręczenia” i pokazuje pełne angielskie zdanie z powodem. Gdy następnego dnia pojawi się `Package Returned to GOFO Facility`, przesyłka wraca na etap „W transporcie” w lokalnej stacji przed kolejną próbą doręczenia (a nie na „Zwrócona nadawcy”). Z kolei `Incorrect/Insufficient Address…` oraz `Label Cancelled` przełączają paczkę na „Zgłoszono problem”: w pierwszym przypadku podaj sprzedawcy numer mieszkania, kod domofonu lub godziny otwarcia, a w drugim poproś sklep o nowy ważny numer śledzenia. Do niektórych wpisów GOFO dokleja na końcu stopkę kontaktową po angielsku lub hiszpańsku — Peek ją usuwa. Zobacz też [co oznaczają statusy przesyłek](guide:tracking-statuses).

**Strefy czasowe.** GOFO zapisuje `Shipping Label Created` zawsze w amerykańskim czasie pacyficznym (`America/Los_Angeles`), a każdy kolejny skan w strefie czasowej danej stacji w USA. Peek przelicza wszystkie godziny na strefę czasową Twojego urządzenia („Godziny w Twojej strefie czasowej: …”): etykieta utworzona o `22:00` czasu pacyficznego wyświetli się w Polsce jako `07:00` następnego dnia (a przez kilkanaście dni w marcu i na przełomie października i listopada, gdy USA i Europa nie zmieniają czasu tego samego dnia, jako `06:00`; na wschodnim wybrzeżu USA jako `01:00`).

## Paczka GOFO Express stoi w miejscu, spóźnia się lub zginęła

**Tylko `Shipping Label Created`, `Pick Up Start` lub `Pickup Exception`.** Dopóki na górze widnieje jeden z tych trzech wpisów, GOFO nie odebrało jeszcze towaru z magazynu sprzedawcy. Jeśli nic nie zmienia się przez kilka dni, zapytaj sklep o termin przekazania paczki; więcej w poradniku [status przesyłki się nie zmienia](guide:tracking-not-updating).

**Brak skanów w trasie lub błąd adresu.** Jeśli przed wydaniem do doręczenia przez cztery dni nie pojawi się żaden nowy skan, skontaktuj się ze sklepem albo skorzystaj z linku pomocy, który wyświetli wtedy Peek. Przy `Delivery Exception, …` lub `Incorrect/Insufficient Address…` przekaż sprzedawcy brakujący numer lokalu, kod bramy albo godziny pracy, aż status zmieni się na `Address Corrected.`.

**Oznaczona jako `Delivered`, ale jej nie ma.** Peek nie pobiera zdjęć z doręczenia z GOFO. Sprawdź najpierw ganek (*porch*), skrzynkę lub pokój pocztowy (*mailroom*), recepcję budynku oraz bezpośrednio stronę śledzenia GOFO pod kątem zdjęcia dowodu dostawy, a następnie zgłoś sprawę sprzedawcy. Kolejne kroki znajdziesz w [przesyłka doręczona, a jej nie ma](guide:delivered-not-received).

**Zaginięcie, zwrot i prawa kupującego.** Umowę przewozu z GOFO zawiera zawsze sprzedawca — to u niego żądaj ponownej wysyłki albo zwrotu pieniędzy (zobacz [zaginiona paczka](guide:lost-parcel) i [przesyłka zwrócona do nadawcy](guide:returned-to-sender)). Dla dostaw w USA federalna zasada sprzedaży wysyłkowej FTC ([16 CFR § 435.2](https://www.law.cornell.edu/cfr/text/16/435.2)) zobowiązuje sprzedawcę do wysyłki w obiecanym terminie — a gdy go nie podano, w ciągu 30 dni — oraz do uzyskania Twojej zgody na opóźnienie albo niezwłocznego zwrotu pieniędzy. Jeśli za niedostarczony towar obciążono amerykańską kartę kredytową, ustawa Fair Credit Billing Act ([15 U.S.C. § 1666](https://www.law.cornell.edu/uscode/text/15/1666)) daje 60 dni od wysłania wyciągu na pisemne zakwestionowanie transakcji u wystawcy karty. Przy zamówieniu u przedsiębiorcy z siedzibą w UE obowiązuje również ustawowy domyślny termin dostawy 30 dni ([Your Europe](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_pl.htm)). Natomiast niezamówiony towar przysłany pocztą na adres w USA (*brushing*) można zgodnie z prawem federalnym ([39 U.S.C. § 3009](https://www.law.cornell.edu/uscode/text/39/3009)) zatrzymać jako prezent bez obowiązku zapłaty ani odsyłania (przepis mówi o przesyłkach pocztowych, nie wymieniając wprost kurierów prywatnych takich jak GOFO).

## Kontakt z obsługą klienta GOFO Express

W razie problemów z zamówieniem skontaktuj się w pierwszej kolejności ze sklepem internetowym, który wysłał paczkę. Gdy przesyłka przed wydaniem do doręczenia nie otrzyma żadnego nowego skanu przez cztery dni (również wtedy, gdy istnieje dopiero sama etykieta), Peek wyświetla przycisk pomocy prowadzący do amerykańskiej strony obsługi klienta GOFO (`gofo.com/us/customer-service`).

## Śledzenie przesyłek GOFO Express w Peek

Przy numerze `GFUS` z 14 cyframi [Peek](/) rozpoznaje GOFO Express natychmiast. Dla numerów z serii `GF` (13 cyfr), `CR` (12 cyfr) lub `MGP` (13 cyfr) odpytuje GOFO w tle i po potwierdzeniu pokazuje „GOFO Express ma tę przesyłkę.” (spacje w numerze są pomijane). Ponieważ [Cainiao](carrier:cainiao) prowadzi część zamówień z AliExpress do USA pod listem przewozowym `GFUS`, Peek sprawdza dodatkowo Cainiao pod kątem wcześniejszego odcinka trasy z Chin, gdy GOFO przez dwa dni pokazuje wyłącznie utworzenie etykiety; więcej w [śledzeniu zamówień z AliExpress](guide:aliexpress-orders).

Dopóki GOFO nie zna jeszcze numeru `GF`, `CR` lub `MGP`, Peek sprawdza dalej w oczekiwaniu na pierwszy skan (jeśli masz pewność, że to GOFO Express, możesz wskazać przewoźnika przyciskiem „Zmień”). Gdy list przewozowy figuruje już u GOFO bez pierwszego skanu albo zapiszesz numer `GFUS`, Peek czeka na skany GOFO bez zgłaszania błędu; dopiero jeśli GOFO przez dwa dni odrzuca numer zapisany pod GOFO Express jako nieznany i nie odpowiada żadne inne źródło, Peek informuje, że po dwóch dniach przewoźnik nadal nie zna tego numeru, po czym sprawdza raz dziennie, a po siedmiu dniach raz w tygodniu. Możesz też wkleić amerykański link śledzenia z `gofo.com` (`/us…` lub strony głównej) albo `gofoexpress.com` z parametrem `searchID=`.

Peek pokazuje aktualny etap po polsku („Zarejestrowana”, „Przyjęta przez przewoźnika”, „W transporcie”, „W doręczeniu”, „Dostarczona” — bez dopisków, które GOFO czasem umieszcza po przecinku przy doręczeniu), oryginalną angielską treść każdego skanu wraz z miastem i stanem USA (np. `Jamaica, NY`) na mapie, godzinę doręczenia oraz kraj docelowy. Szacowaną przez GOFO datę dostawy wyświetla do chwili przejścia paczki w stan „W doręczeniu”, „Dostarczona” lub „Zwrócona nadawcy”, natomiast zdjęcia z doręczenia, współrzędne GPS i wagę pomija.

Peek sprawdza GOFO nawet co 30 minut, gdy masz przesyłkę otwartą albo w dniu dostawy z włączonymi powiadomieniami (także na etapie „W doręczeniu”), po nieudanym zapytaniu odczekuje 60 minut, a w pozostałych przypadkach — oraz po dwóch dniach bez nowych wpisów lub po 12 godzinach na „Zarejestrowana” bądź „Przyjęta przez przewoźnika” — przechodzi na sprawdzanie co godzinę. Powiadomienia możesz ustawić na „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek nie kontaktuje się w Twoim imieniu z GOFO ani ze sklepem.

## Najczęstsze pytania o śledzenie GOFO Express

### Czym jest GOFO Express?

GOFO Express to amerykański operator kurierski obsługujący odbiór z magazynów, regionalne sortowanie i doręczenia ostatniej mili na terenie Stanów Zjednoczonych, a pod osobnymi prefiksami krajowymi grupa GOFO działa też w Kanadzie, Francji ([GOFO France](carrier:gofo-fr)), Włoszech, Hiszpanii i Holandii.

### Dlaczego mój numer GOFO nie działa?

Upewnij się najpierw, że to amerykański format GOFO: `GFUS` (14 cyfr), `GF` (13 cyfr), `CR` (12 cyfr) lub `MGP` (13 cyfr). Prefiksy `GFCA`, `GFFR`/`CIFR`, `GFIT`/`CIIT`, `GFES`/`CIES` i `GFNL`/`CINL` należą do innych sieci krajowych, numery `YT` do [YunExpress](carrier:yunexpress), a paczki DHgate doręczane przez GOFO wyszukasz też po numerze `FC` z [DHLink](carrier:dhlink). Świeżo wygenerowany numer często czeka dopiero na fizyczne odebranie z magazynu; jeśli widnieje `Label Cancelled`, etykietę anulowano i musisz poprosić sklep o nowy numer.

### Co oznacza `Package Returned to GOFO Facility`?

Wbrew dosłownemu brzmieniu paczka nie wraca wtedy do sprzedawcy: ten skan pojawia się dzień po nieudanej próbie doręczenia (`Delivery Exception`), gdy kierowca przywiózł przesyłkę z powrotem do lokalnej bazy GOFO przed kolejnym wyjazdem w trasę — dlatego Peek nadal oznacza ją jako „W transporcie”.

### Skąd numer GOFO przy zamówieniu z AliExpress lub DHgate do USA?

Przy wysyłkach do Stanów Zjednoczonych [Cainiao](carrier:cainiao) (operator logistyczny AliExpress) śledzi część paczek bezpośrednio pod amerykańskim numerem `GFUS`, gdy ostatnią milę realizuje GOFO Express, natomiast [DHLink](carrier:dhlink) przekazuje wybrane zamówienia z DHgate do GOFO pod własnym numerem `FC`. Przy numerze `GFUS` Peek odpytuje GOFO, a gdy przez dwa dni widnieje tylko etykieta, sprawdza również Cainiao.

:::sources
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formaty numerów USA (GFUS, GF, CR, MGP), powiązane numery DHLink (FC) i YunExpress (YT), sieci krajowe GOFO, mapowanie skanów, strefy czasowe, Cainiao i interwały odpytywania
- [16 CFR § 435.2 – Mail, Internet, or Telephone Order Merchandise Rule (Cornell LII)](https://www.law.cornell.edu/cfr/text/16/435.2) – obowiązek wysyłki w USA w zadeklarowanym terminie lub w ciągu 30 dni oraz prawo do zwrotu pieniędzy przy opóźnieniu
- [15 U.S.C. § 1666 – Fair Credit Billing Act (Cornell LII)](https://www.law.cornell.edu/uscode/text/15/1666) – pisemna reklamacja obciążenia karty kredytowej w ciągu 60 dni przy niedostarczonym towarze
- [39 U.S.C. § 3009 – Mailing of unordered merchandise (Cornell LII)](https://www.law.cornell.edu/uscode/text/39/3009) – niezamówione przesyłki pocztowe w USA traktowane jako prezent bez obowiązku zapłaty
- [Your Europe: Wysyłka i dostawa](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_pl.htm) – ustawowy 30-dniowy termin dostawy przy zakupie od przedsiębiorcy w UE
:::
