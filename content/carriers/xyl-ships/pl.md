---
title: Śledzenie XYL-Ships: numery NUDE i XLWL oraz statusy
description: Śledź paczkę XYL-Ships (FXYL) z Chin: formaty NUDE i XLWL, znaczenie statusów, przerwy w skanach, cło w UE i do kogo się zwrócić.
slug: sledzenie-xyl-ships
published: 2026-11-24
updated: 2026-11-24
---

XYL-Ships (XYL-ships International Logistics, spotykane też jako FXYL) to chiński operator logistyki transgranicznej z uprawnieniami agenta sprzedaży frachtu lotniczego uznawanymi przez chiński urząd lotnictwa cywilnego (CAAC) oraz IATA. Oprócz komercyjnego frachtu lotniczego, morskiego, kolejowego i drogowego przewozi pojedyncze małe paczki e-commerce z Chin do krajów docelowych i przekazuje je lokalnej poczcie lub kurierowi do końcowego doręczenia. Przesyłkę możesz sprawdzić przez przycisk „Track Order” na [xylships.com](https://xylships.com/) albo wkleić numer w pole powyżej.

## Numery śledzenia XYL-Ships: formaty NUDE i XLWL

Numer przesyłki znajdziesz w e-mailu z potwierdzeniem nadania ze sklepu albo w historii zamówień. Małe paczki XYL-Ships mają zwykle 16- lub 17-znakowy kod alfanumeryczny zaczynający się od `NUDE` lub `XLWL` i kończący na `EX` lub `EXP`:

| Format (zmyślony) | Co to jest |
| --- | --- |
| `NUDE4829173640EX` | `NUDE`, 10 cyfr i `EX`. |
| `XLWL4829173640EX` | `XLWL`, 10 cyfr i `EX`. |
| `XLWL4829173640EXP` | `XLWL`, 10 cyfr i `EXP`. |

Ponieważ te kody są charakterystyczne dla XYL-Ships, nie kolidują z kodami poczt narodowych. Jeśli sklep podał zamiast tego międzynarodowy kod pocztowy S10 (dwie litery, dziewięć cyfr i dwuliterowy kod kraju, np. `CN`) lub numer innego kuriera, sprawdź [formaty numerów przesyłek](guide:tracking-number-formats), aby ustalić przewoźnika.

## Co oznaczają statusy śledzenia XYL-Ships

XYL-Ships publikuje komunikaty po angielsku, opisując drogę paczki od sortowni w Chinach przez transport międzynarodowy aż do lokalnego partnera w kraju docelowym:

| XYL-Ships pokazuje | Co to oznacza dla ciebie | Peek pokazuje |
| --- | --- | --- |
| `Shipment inbound in sorting center` | XYL-Ships odebrał i zeskanował paczkę w chińskiej sortowni początkowej. | „W transporcie” |
| `Departed from sorting center`, `Arrived at sorting warehouse`, `Leaving warehouse` | Paczka przemieszcza się między magazynami sortującymi w Chinach przed przekazaniem do linii głównej. | „W transporcie” |
| `Hand over to transportation company`, `Departed from origin transportation center`, `Arrived at destination transportation center` | Przekazana przewoźnikowi międzynarodowemu, opuściła Chiny i dotarła do centrum odbioru w kraju docelowym. | „W transporcie” |
| `Released and the package is being transferred`, `Package is being transferred locally` | Zwolniona do obrotu w kraju docelowym i przekazana do przewozu krajowego. | „W transporcie” |
| `Transfer again, send to the transportation partner department`, `Arrival at transportation partner department, waiting for processing`, `Leaving transportation partner department, sent to the transit hub` | Trafiła do działu obsługi lokalnego partnera doręczeniowego i została wysłana do jego centrum tranzytowego. | „W transporcie” |
| `Arriving at the transit hub, waiting for distribution`, `Leave the transit hub, transport to the destination city`, `Arriving at the destination city, about to distribute to the post office` | Posortowana w regionalnym węźle i przewieziona do miasta docelowego w celu przekazania lokalnego. | „W transporcie” |
| `The courier has received this parcel for delivery and will set a delivery time shortly` | Lokalny kurier przejął przesyłkę, ale trasa doręczeń jeszcze się nie zaczęła. | „W transporcie” |
| `Parcel currently being delivered` | Paczka jedzie dziś z lokalnym kurierem do doręczenia. | „W doręczeniu” |
| `Delivered` | Zarejestrowana jako doręczona pod adresem docelowym. | „Dostarczona” |

Zwróć uwagę na dwa mylące wpisy pod koniec trasy. `Arriving at the destination city, about to distribute to the post office` oznacza przekazanie do lokalnej sieci pocztowej lub partnerskiej, a nie to, że przesyłka czeka już na odbiór na poczcie. Podobnie `The courier has received this parcel for delivery and will set a delivery time shortly` nadal pozostaje na etapie „W transporcie”, bo kurier nie ruszył jeszcze w trasę — dopiero `Parcel currently being delivered` zapowiada doręczenie tego dnia.

## Jak działa przesyłka transgraniczna XYL-Ships

Na stronie [xylships.com](https://xylships.com/) XYL-Ships przedstawia komercyjny fracht lotniczy (w tym krajowy ekspres lotniczy 6- i 12-godzinny oraz konsolidację FBA door-to-door), konsolidację morską, transport kolejowy i drogowy, a także obsługę pojedynczych małych paczek e-commerce — przywołując przykład klienta ze skutecznością 99% dostaw w ciągu 20 dni.

**Lokalne doręczenie ostatniej mili.** Jak pokazują same statusy XYL-Ships, po dotarciu do kraju docelowego paczka trafia do lokalnego partnera transportowego lub poczty, które odpowiadają za końcowe doręczenie.

**Niepublikowane zasady doręczeń.** `xylships.com` nie podaje liczby prób doręczenia, zasad automatów paczkowych, terminów przechowywania, gwarancji czasu dostawy ani terminów reklamacji. Po wejściu przesyłki do twojego kraju konkretne zasady (zostawienie u sąsiada, w bezpiecznym miejscu albo liczba dni na odbiór po awizo) zależą od lokalnej poczty lub firmy kurierskiej.

**Cło i opłaty importowe.** Ponieważ paczki XYL-Ships lecą z Chin, przesyłki wchodzące do UE, Wielkiej Brytanii, USA czy innych regionów podlegają krajowemu podatkowi VAT od importu i przepisom celnym. Przy paczkach wchodzących do Unii Europejskiej obowiązuje ryczałtowe cło 3 € za rodzaj towaru dla przesyłek do 150 € oraz unijna opłata manipulacyjna 2 € za rodzaj towaru niezależnie od wartości. Zobacz [przesyłki zatrzymane w urzędzie celnym](guide:customs), aby poznać zasady odprawy.

## Paczka XYL-Ships utknęła, milczy albo nie dotarła

Jeśli kupujesz w sklepie internetowym wysyłającym przez XYL-Ships, stroną umowy pozostaje sprzedawca — to do niego zwracaj się w sprawie opóźnień, zwrotu pieniędzy lub ponownej wysyłki.

**Długa cisza w transporcie.** Kilkudniowe przerwy między `Departed from origin transportation center` a `Arrived at destination transportation center` są częste podczas oczekiwania na lot, odprawę celną i pierwszy skan w kraju docelowym. Zobacz [dlaczego śledzenie przestaje się aktualizować](guide:tracking-not-updating).

**Brak lokalnego numeru w Peek.** Peek śledzi przesyłkę także u lokalnego partnera pod numerem `NUDE` lub `XLWL` i nie wyświetla oddzielnego numeru krajowego. Jeśli doręczenie nie powiedzie się i potrzebujesz nazwy lub numeru lokalnego kuriera, zapytaj sprzedawcę.

**Status `Delivered`, ale paczki nie ma.** Najpierw sprawdź skrzynkę, ganek, recepcję budynku i sąsiadów. Jeśli nadal jej nie ma, natychmiast zgłoś to sprzedawcy i poproś o potwierdzenie doręczenia. Zobacz [dostarczona, ale nieodebrana](guide:delivered-not-received).

**Uszkodzona, zagubiona lub zwrócona.** Zachowaj zdjęcia uszkodzonego opakowania i od razu skontaktuj się ze sklepem albo otwórz spór w programie ochrony kupujących na platformie lub u operatora płatności przed upływem terminu. Zobacz [uszkodzona paczka](guide:damaged-parcel), [zagubiona paczka](guide:lost-parcel) i [zwrócona nadawcy](guide:returned-to-sender).

## Kontakt z XYL-Ships (i dlaczego najpierw pytać sprzedawcę)

`xylships.com` to strona spedytora B2B z minimalnymi danymi kontaktowymi dla odbiorców indywidualnych:

- **E-mail:** `fzxyl9527@163.com` (jedyny kontakt podany w stopce [xylships.com](https://xylships.com/)).
- **Telefon, adres biura i formularz reklamacji:** brak na `xylships.com`.

Ponieważ XYL-Ships współpracuje z nadawcami biznesowymi zamiast prywatnych odbiorców, w razie braku paczki, błędu w adresie lub zwrotu środków skontaktuj się najpierw ze sklepem albo platformą — sprzedawca ma bezpośredni dostęp do konta przewozowego i procedur ochrony kupujących.

> Traktuj podejrzliwie nieoczekiwane SMS-y lub e-maile z linkiem do płatności za „odblokowanie” albo „zmianę terminu” paczki XYL-Ships. Każdą prawdziwą opłatę celną sprawdzaj bezpośrednio u krajowej poczty lub administracji celnej, nigdy przez niezweryfikowany link. Zobacz [fałszywe SMS-y o przesyłce](guide:delivery-scam-texts).

## Śledzenie paczki XYL-Ships w Peek

[Peek](/) rozpoznaje numery `NUDE` (`NUDE` + 10 cyfr + `EX`) oraz `XLWL` (`XLWL` + 10 cyfr + `EX` lub `EXP`) jako XYL-Ships automatycznie, z wysoką pewnością. Ponieważ XYL-Ships nie ma w Peek dedykowanych linków śledzenia, wklej sam numer zamiast adresu URL; gdyby trzeba było wybrać przewoźnika ręcznie, stuknij „Zmień” podczas dodawania lub „Zmień przewoźnika” na ekranie paczki i wybierz XYL-Ships (wyszukanie „FXYL” też go znajdzie).

Peek odczytuje bezpośrednio strumień JSON XYL-Ships i wyświetla etap („W transporcie”, „W doręczeniu” lub „Dostarczona”), angielskie opisy skanów XYL-Ships, lokalizację skanu gdy jest podana oraz znaczniki czasu z jawną strefą czasową (przeliczone na czas twojego urządzenia). Peek odrzuca adresy nadawcy i odbiorcy oraz wewnętrzne referencje klienta. Nie pokazuje przewidywanej daty, wagi, numeru lokalnego przewoźnika ani zdjęcia doręczenia.

Gdy masz paczkę otwartą w Peek albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza status nawet co 10 minut; w dniu, gdy XYL-Ships pokaże `Parcel currently being delivered`, co 2 minuty od 6:00 według zegara najnowszego skanu XYL-Ships, dopóki system XYL-Ships odpowiada. Poza tym sprawdza co godzinę. Po 12 godzinach wyłącznie na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika”, albo po dwóch dniach bez zmian, sprawdza co godzinę niezależnie od sposobu śledzenia. Gdy system XYL-Ships nie odpowiada i Peek korzysta wyłącznie z zewnętrznych usług śledzenia, sprawdza co godzinę, co 3 godziny po dniu ciszy i do co 15 minut w dniu, w którym paczka jest w doręczeniu, gdy masz paczkę otwartą lub masz włączone powiadomienia.

Gdy najnowszy skan ma cztery dni, a przesyłka nadal widnieje jako „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie”, Peek oznacza ją dniem tego skanu, np. „Brak aktualizacji od pon 12 paź”. Na stronie paczki pojawia się wtedy napis: „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika XYL-Ships.” Po trzech tygodniach bez nowego skanu widnieje komunikat: „Od ponad trzech tygodni nic nowego. Jeśli przesyłka wciąż nie dotarła, zapytaj sprzedawcę. Peek dalej pyta przewoźnika XYL-Ships.”

Możesz wybrać powiadomienia: „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek tylko monitoruje śledzenie — nie wskaże lokalnego przewoźnika ani nie skontaktuje się za ciebie z XYL-Ships.

## Pytania o śledzenie XYL-Ships

### Czym jest XYL-Ships (FXYL)?

XYL-Ships International Logistics to chiński spedytor i operator transgranicznego e-commerce z uprawnieniami lotniczymi CAAC i IATA. Sklepy korzystają z niego do wywozu małych paczek z Chin i przekazania ich lokalnym przewoźnikom w twoim kraju.

### Co oznaczają numery śledzenia `NUDE...EX` i `XLWL...EX`?

Oba prefiksy to formaty XYL-Ships: `NUDE` lub `XLWL`, następnie 10 cyfr i końcówka `EX` (lub `EXP` w części przesyłek `XLWL`). Wklej pełny kod w Peek albo w pole Track Order na [xylships.com](https://xylships.com/).

### Dlaczego śledzenie XYL-Ships pokazuje `about to distribute to the post office`?

Oznacza to, że paczka dotarła do miasta docelowego i jest przekazywana lokalnej poczcie lub partnerowi doręczeniowemu na ostatnią milę. Zaczekaj na `Parcel currently being delivered`, zanim spodziewasz się doręczenia tego dnia.

### Kto doręcza paczki XYL-Ships w moim kraju?

Po przybyciu do kraju docelowego XYL-Ships przekazuje paczki lokalnemu partnerowi lub poczcie i nie podaje ich nazwy w skanach; Peek nie wyświetla drugiego numeru. Gdy doręczenie się nie uda, zapytaj sprzedawcę o dane lokalnego przewoźnika.

:::sources
- [XYL-Ships International Logistics](https://xylships.com/) – kwalifikacje lotnicze CAAC i IATA, fracht lotniczy/morski/kolejowy/drogowy i małe paczki transgraniczne e-commerce, opcja Track Order oraz e-mail `fzxyl9527@163.com`
- [Universal Parcel Scraper: xyl-ships](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/xyl-ships) – rozpoznawanie formatów `NUDE` i `XLWL`, alias FXYL, integracja JSON, mapowanie statusów angielskich, odrzucane pola i częstotliwość odświeżania
:::
