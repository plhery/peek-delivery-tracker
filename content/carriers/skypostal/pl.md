---
title: Śledzenie SkyPostal: kody SKAMZUS i SP, statusy i pomoc
description: Śledź paczkę SkyPostal z USA do Ameryki Łacińskiej: kody SKAMZUS i SP...BR, hub w Miami, cło (CEN, HTI, DEL) oraz e-maile wsparcia dla krajów.
slug: sledzenie-skypostal
published: 2026-11-24
updated: 2026-11-24
---

**SkyPostal** to amerykańska sieć przesyłek krajowych i transgranicznych do Ameryki Łacińskiej z siedzibą w Miami, założona przez Alberta P. i A.J. Hernandeza ([skypostal.com](https://www.skypostal.com/)). Działając z głównego amerykańskiego hubu eksportowego w Miami na Florydzie, SkyPostal dostarcza paczki e-commerce dla dużych sklepów i platform — w tym Amazon, Temu i eBay — do Brazylii, Meksyku, Kolumbii, Peru, Ekwadoru, Chile i Kostaryki, przeprowadzając odprawę eksportową i importową oraz przekazując końcowe doręczenie pod drzwi lokalnym partnerom w Ameryce Łacińskiej. Przesyłkę sprawdzisz na [tracking.skypostal.com](https://tracking.skypostal.com/) albo wklejając numer lub link SkyPostal w pole powyżej.

## Numery przesyłek SkyPostal (formaty `SKAMZUS` i `SP...BR`)

SkyPostal stosuje kilka formatów transgranicznych w zależności od sprzedawcy i kraju docelowego — najczęściej 17-znakowe kody `SKAMZUS` (powszechne w eksporcie Amazon USA do Ameryki Łacińskiej) oraz 15-znakowe kody `SP...BR` dla Brazylii:

| Format (wymyślony) | Co oznacza |
| --- | --- |
| `SKAMZUS0482917365` | 17-znakowy kod eksportowy SkyPostal dla Amazon USA (`SKAMZUS` + 10 cyfr). |
| `SP04829173605BR` | 15-znakowy kod transgraniczny SkyPostal dla Brazylii (`SP` + 11 cyfr + `BR`). |

Po ręcznym wybraniu przewoźnika tracker SkyPostal wyszukuje również inne alfanumeryczne listy przewozowe sklepów i referencje partnerów o długości od 8 do 40 znaków z co najmniej jedną cyfrą. Zobacz poradnik o [formatach numerów śledzenia](guide:tracking-number-formats).

## Co oznaczają statusy śledzenia SkyPostal

SkyPostal przypisuje 3-literowy kod zdarzenia każdemu etapowi — od elektronicznej wstępnej odprawy i przyjęcia w Miami po loty, odprawę celną w kraju docelowym i skany przekazywane od lokalnego przewoźnika:

| Kod i komunikat SkyPostal | Co oznacza dla Ciebie | Peek pokazuje |
| --- | --- | --- |
| `DRC` (`DATA RECEIVED`), `HCV` (`HS_CODE VALIDATION`), `TIC` (`TAX ID COLLECTED`), `DCO` (`DOCUMENTATION COMPLETE`) | Dane zamówienia, walidacja kodu taryfowego HS i identyfikator podatkowy kupującego otrzymane przed dotarciem paczki do hubu SkyPostal. | „Zarejestrowana” |
| `RUT` (`RECEPTACLE RECEIVED IN US TRANSPORT FACILITY`), `REC` (`RECEIVED`) | Fizyczna paczka przyjęta w ośrodku eksportowym SkyPostal w Miami. | „Przyjęta przez przewoźnika” |
| `XRS` (`X-RAY SCAN`), `PLP` (`PROVIDER LABEL PRINTED`), `INC` (`NESTED TO RECEPTACLE`), `PRO`, `MNF`, `FDP` (`FLIGHT DEPARTED`), `ATT`, `ATC` (`ARRIVED IN COUNTRY`), `DTP` (`DELIVERED FROM AIRLINE TO OPERATOR`) | Obsłużona w Miami, wysłana samolotem i przekazana przez linię lotniczą naziemnemu operatorowi w kraju docelowym (nie doręczenie pod drzwi). | „W transporcie” |
| `CEN` (`CUSTOMS ENTRY`), `CHE` (`CUSTOMS HOLD AT CONTROL ENTITY`) | Skierowana do odprawy celnej w kraju docelowym lub oczekuje na zwolnienie z lotniskowego magazynu celnego. | „W urzędzie celnym” |
| `CCH` (`CUSTOMS CLEARED`), `TTC` (`TENDERED TO LOCAL CARRIER`), `TDP`, `PES` (`PROVIDER ENTRY SCAN`), `INT`, `PRH`, `PDH`, `SLH` (`HOLD RESOLVED`) | Odprawiona celnie, przekazana lokalnemu przewoźnikowi w Ameryce Łacińskiej i w trasie między lokalnymi sortowniami. | „W transporcie” |
| `OFD` (`OUT FOR DELIVERY`) | Dzisiaj w doręczeniu u lokalnego partnera w kraju docelowym. | „W doręczeniu” |
| `PIK` (`AVAILABLE FOR PICKUP`) | Gotowa do odbioru przez odbiorcę w lokalnym punkcie lub na poczcie. | „Do odbioru” |
| `CNA` (`CONSIGNEE NOT AVAILABLE`) | Próba doręczenia nieudana, ponieważ nikogo nie było do odbioru paczki. | „Próba doręczenia” |
| `HTI` (`HOLD FOR PROBLEMATIC TAX ID`), `ICA` (`INCORRECT / INCOMPLETE ADDRESS`), `DRF` (`DELIVERY REFUSED`), `PLR` (`PROVIDER LABEL REJECTED`), `INH` (`ON HOLD`) | Wstrzymana z powodu problemu z numerem podatkowym, błędnego lub niepełnego adresu, odrzuconej etykiety albo odmowy odbioru. | „Zgłoszono problem” |
| `DEL` (`DELIVERED`) | Doręczona odbiorcy przez partnera docelowego. | „Dostarczona” |

Zwróć uwagę, że `DTP` (*Delivered from airline to operator*) oznacza wyłącznie przekazanie worka pocztowego przez linię lotniczą naziemnemu agentowi na lotnisku — Peek utrzymuje `DTP` na etapie „W transporcie”, zamiast oznaczać paczkę jako dostarczoną. Wewnętrzne zdarzenia rozliczeniowe (`CUT`, `PAY`, `FSC`) nie zmieniają etapu.

## Cło w Ameryce Łacińskiej, identyfikatory podatkowe (`HTI`) i doręczenia lokalne

Przesyłki transgraniczne z USA do Ameryki Łacińskiej przez SkyPostal wymagają ścisłej zgodności danych celnych i podatkowych:

- **Numer podatkowy kupującego (`TIC` / `HTI`):** skany SkyPostal odnotowują zebranie identyfikatora podatkowego (`TAX ID COLLECTED`) oraz wstrzymanie paczki z jego powodu (`HOLD FOR PROBLEMATIC TAX ID`). Jeśli pojawia się `HTI`, zapytaj sprzedawcę lub napisz na adres wsparcia SkyPostal dla swojego kraju z pełnym numerem przesyłki. Zobacz poradnik o [paczkach w urzędzie celnym](guide:customs).
- **Lokalne doręczenie ostatniej mili:** po pojawieniu się `CCH` (*Customs cleared*) i `TTC` (*Tendered to local carrier*) dostawę realizuje lokalny przewoźnik, a tracker SkyPostal pokazuje jego skany z miejscowością partnera.

## Paczka SkyPostal opóźniona, wstrzymana lub zaginiona

- **Przerwy między Miami a cłem w kraju docelowym:** śledzenie często milknie między `FDP` (`FLIGHT DEPARTED`), `ATC` (`ARRIVED IN COUNTRY`) i `CCH` (`CUSTOMS CLEARED`) w czasie kontroli celnej ładunków zbiorczych. Zobacz poradnik o [braku aktualizacji śledzenia](guide:tracking-not-updating).
- **Błędy adresu (`ICA`) lub odmowa odbioru (`DRF`):** najpierw skontaktuj się ze swoją platformą zakupową (Amazon, Temu, eBay itp.) w sprawie ponownej wysyłki lub zwrotu pieniędzy, a jeśli paczka wciąż czeka lokalnie, napisz na dedykowany adres SkyPostal dla swojego kraju z pełnym numerem śledzenia. Zobacz też [dostarczona, ale nieodebrana](guide:delivered-not-received), [zaginiona paczka](guide:lost-parcel), [uszkodzona paczka](guide:damaged-parcel) i [zwrócona do nadawcy](guide:returned-to-sender).

## Obsługa klienta SkyPostal i adresy e-mail według kraju

Na stronie [SkyPostal Customer Service](https://www.skypostal.com/customer-service) SkyPostal prosi odbiorców końcowych o kontakt **e-mailowy**, zawsze z pełnym numerem śledzenia i opisem sprawy (deklarowany czas odpowiedzi: **3–5 dni roboczych**):

- **Brazylia:** `Brazilcs@skypostal.com`
- **Meksyk:** `Mexicocs@skypostal.com`
- **Kolumbia:** `Colombiacs@skypostal.com`
- **Peru:** `Perucs@skypostal.com`
- **Ekwador:** `Ecuadorcs@skypostal.com`
- **Wszystkie pozostałe kraje:** `customerservice@skypostal.com`
- **Siedziba w USA:** *SkyPostal*, 2990 NW 75th Ave, Miami, FL 33122, Stany Zjednoczone ([skypostal.com/about-us](https://www.skypostal.com/about-us)).

> Uważaj na fałszywe wiadomości WhatsApp lub SMS żądające pilnej zapłaty podatku na nieznanej stronie w celu zwolnienia paczki do Ameryki Łacińskiej. Prawdziwe opłaty celne sprawdzaj wyłącznie na stronie swojego zamówienia albo w oficjalnym portalu celnym lub pocztowym. Zobacz poradnik o [fałszywych SMS-ach o paczkach](guide:delivery-scam-texts).

## Śledzenie SkyPostal w Peek

[Peek](/) automatycznie rozpoznaje SkyPostal jako jedynego kandydata, gdy wpiszesz numer `SKAMZUS` (`SKAMZUS0482917365`, wymyślony) lub `SP...BR` (`SP04829173605BR`, wymyślony), i pokazuje „SkyPostal ma tę przesyłkę.” po otrzymaniu historii z trackera SkyPostal; Peek rozpoznaje też bezpośrednio wklejone linki `tracking.skypostal.com/?trck_number=` oraz `tracking.skypostal.com/single-tracking.aspx?trck_number=`. Przy innych referencjach sklepowych kliknij „Zmień” przy dodawaniu albo „Zmień przewoźnika” na ekranie paczki i wybierz **SkyPostal** (wyszukanie „Sky Postal” również działa).

Peek odczytuje tracker SkyPostal i pokazuje etap („Zarejestrowana”, „Przyjęta przez przewoźnika”, „W transporcie”, „W urzędzie celnym”, „W doręczeniu”, „Do odbioru”, „Próba doręczenia”, „Zgłoszono problem”, „Dostarczona”), angielską treść skanu oraz miejscowość partnera albo kod lotniska SkyPostal. Kody 3-literowe wyznaczają etap i żadna aplikacja Peek ich nie wyświetla. SkyPostal podaje godziny bez przesunięcia względem UTC: Peek datuje każdy skan według strefy miejsca (czas Nowego Jorku dla `MIA`, São Paulo dla `GRU`, Bogoty lub Limy dla tamtejszych skanów), skan bez miejsca według sąsiedniego skanu, a wszystko wyświetla w czasie Twojego urządzenia. Skany partnera mogą spłynąć dwukrotnie z dwóch źródeł w odstępie kilku godzin, dlatego wcześniejszy etap dopisany po doręczeniu nie cofa dostawy. Peek nie zachowuje potwierdzenia doręczenia, komentarzy ani własnego numeru partnera, a żadna aplikacja Peek nie pokazuje nazwiska podpisującego. Tracker SkyPostal nie podaje szacowanego dnia doręczenia, wagi ani osobnej godziny doręczenia.

Gdy masz przesyłkę otwartą w Peek albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza status nawet co 10 minut; w dniu, gdy SkyPostal pokazuje `OFD` (`OUT FOR DELIVERY`), co 2 minuty od godz. 6:00 według zegara najnowszego skanu, dopóki tracker SkyPostal odpowiada. W pozostałym czasie Peek sprawdza co godzinę. Po 12 godzinach wyłącznie na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika” albo po dwóch dniach bez zmian sprawdza co godzinę niezależnie od sposobu śledzenia, a po siedmiu dniach na „Zarejestrowana” — co 6 godzin. Gdy tracker SkyPostal nie odpowiada i Peek śledzi przesyłkę wyłącznie przez usługi śledzące, sprawdza co godzinę, co 3 godziny po spokojnym dniu i nawet co 15 minut w dniu, w którym przesyłka jest w doręczeniu, dopóki masz ją otwartą lub włączone powiadomienia.

Jeśli najnowszy skan ma cztery dni, a przesyłka nadal widnieje jako „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie”, Peek oznacza ją dniem tego skanu, np. „Brak aktualizacji od pon 12 paź”. Na stronie przesyłki pojawia się wtedy komunikat „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika SkyPostal.” wraz z linkiem „Poproś przewoźnika SkyPostal o pomoc” do strony obsługi klienta. Po trzech tygodniach bez nowego skanu pojawia się informacja „Od ponad trzech tygodni nic nowego. Jeśli przesyłka wciąż nie dotarła, zapytaj sprzedawcę. Peek dalej pyta przewoźnika SkyPostal.”

Możesz wybrać powiadomienia „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek monitoruje wyłącznie aktualizacje śledzenia i nie może dosłać numeru podatkowego, odprawić paczki w Ameryce Łacińskiej ani skontaktować się ze SkyPostal w Twoim imieniu.

## Pytania o śledzenie SkyPostal

### Jak wygląda numer śledzenia SkyPostal?

Najczęstsze kody SkyPostal to 17-znakowe numery `SKAMZUS` (`SKAMZUS0482917365`, wymyślony) dla eksportu Amazon USA oraz 15-znakowe numery `SP...BR` (`SP04829173605BR`, wymyślony) dla Brazylii, choć SkyPostal obsługuje również inne 8–40-znakowe referencje sprzedawców.

### Co oznacza `DTP` (`DELIVERED FROM AIRLINE TO OPERATOR`) w SkyPostal?

Nie oznacza doręczenia paczki pod Twój adres. `DTP` oznacza, że linia lotnicza przekazała zbiorczą przesyłkę naziemnemu operatorowi na lotnisku docelowym przed odprawą celną (`CEN`) i przekazaniem lokalnemu kurierowi (`TTC`).

### Co oznacza `HTI` (`HOLD FOR PROBLEMATIC TAX ID`)?

Urząd celny kraju docelowego wykrył brakujący lub niezgodny identyfikator podatkowy odbiorcy (np. CPF w Brazylii albo RFC w Meksyku). Skontaktuj się ze sprzedawcą i napisz na właściwy dla kraju adres SkyPostal ze strony [skypostal.com/customer-service](https://www.skypostal.com/customer-service).

### Jak skontaktować się z obsługą klienta SkyPostal?

Napisz na adres właściwego zespołu krajowego (`Brazilcs@`, `Mexicocs@`, `Colombiacs@`, `Perucs@`, `Ecuadorcs@` lub `customerservice@skypostal.com`) z numerem śledzenia; według SkyPostal odpowiedź zajmuje 3–5 dni roboczych.

:::sources
- [SkyPostal: Official Website](https://www.skypostal.com/) – przesyłki krajowe USA i transgraniczne do Ameryki Łacińskiej, siedziba Miami FL
- [SkyPostal: About Us](https://www.skypostal.com/about-us) – historia firmy i prywatna sieć doręczeń w Ameryce Łacińskiej
- [SkyPostal: Customer Service](https://www.skypostal.com/customer-service) – adresy e-mail wsparcia według kraju (Brazylia, Meksyk, Kolumbia, Peru, Ekwador, ogólny), informacja o Amazon/Temu/eBay i czas odpowiedzi 3–5 dni roboczych (strona pomocy linkowana w Peek)
- [SkyPostal: Tracking Portal](https://tracking.skypostal.com/) – publiczny tracker pojedynczych przesyłek
- [Universal Parcel Scraper: skypostal](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/skypostal) – reguły rozpoznawania `SKAMZUS` i `SP...BR`, reguły linków `tracking.skypostal.com`, mapa 3-literowych kodów (`DRC`–`DEL`, `DTP`, `HTI`) i strefy czasowe skanów
:::
