---
title: Śledzenie Spee-Dee Delivery: kody SP, stany i pomoc
description: Śledź paczki Spee-Dee Delivery Service na Środkowym Zachodzie USA: 18- i 20-znakowe kody SP, ponowne użycie kodów, zasięg i numer 800-862-5578.
slug: sledzenie-spee-dee
published: 2026-11-24
updated: 2026-11-24
---

Spee-Dee Delivery (*Spee-Dee Delivery Service, Inc.*) to firma kurierska prowadzona przez drugie pokolenie rodziny, założona w 1978 roku przez Dona Weeresa jako usługa doręczeń na telefon. Określa się jako niekwestionowany lider ostatniej mili na Środkowym Zachodzie USA z siecią dostaw następnego dnia, a także oferuje fracht drobnicowy LTL, magazynowanie, punkty nadania oraz EZR Returns ([speedeedelivery.com](https://speedeedelivery.com/)).

Przesyłkę sprawdzisz na stronie [Track a Shipment Spee-Dee](https://speedeedelivery.com/track-a-shipment/) albo wklejając swój kod kreskowy `SP` w pole powyżej.

## Formaty numerów śledzenia Spee-Dee

Peek rozpoznaje kody kreskowe Spee-Dee jako `SP` z 16 lub 18 cyframi:

| Format (wymyślony) | Co oznacza |
| --- | --- |
| `SP4829173605148291` | `SP` + 16 cyfr (łącznie 18 znaków) — standardowy kod kreskowy Spee-Dee; pamiętaj, że Spee-Dee z czasem ponownie wykorzystuje 16-cyfrowe numery. |
| `SP482917360514829173` | `SP` + 18 cyfr (łącznie 20 znaków) — dłuższy format kodu kreskowego Spee-Dee. |

Oba formaty `SP` jednoznacznie wskazują na Spee-Dee i nie nakładają się na ogólnokrajowych przewoźników w USA, takich jak [UPS](carrier:ups), [FedEx](carrier:fedex) czy [USPS](carrier:usps). Jeśli masz link `speedeedelivery.com/...?tracking=...`, Peek bezpośrednio wyodrębnia numer `SP`. Inne amerykańskie wzorce znajdziesz w poradniku o [formatach numerów śledzenia](guide:tracking-number-formats).

## Obszar działania Spee-Dee na Środkowym Zachodzie

Według [map zasięgu Spee-Dee](https://speedeedelivery.com/services/service-area-maps/) firma realizuje regularne doręczenia drogowe na terenie **całych** sześciu stanów Środkowego Zachodu oraz wybranych miast i kodów pocztowych (*ZIP Codes*) w czterech sąsiednich stanach:

- **Pełne pokrycie stanu:** Illinois, Iowa, Minnesota, Dakota Północna, Dakota Południowa i Wisconsin.
- **Wybrane miasta i ZIP Codes:** Kansas (w tym rozszerzone kody `660` i `666`), Michigan, Missouri (w tym rozszerzone kody `634–635`, `644–646` i `650–653`) oraz Nebraska.

Aby sprawdzić czasy transportu, strona *Service Area Maps* udostępnia mapę *Days in Transit* dla każdego nadawczego kodu ZIP, a plik *Zip Transit File* podaje czasy dla każdego obsługiwanego kodu.

## Co oznaczają statusy śledzenia Spee-Dee

Tabela postępu Spee-Dee zawiera trzy kolumny — `Date / Time`, `Location` i `Activity` — od najnowszego zdarzenia, a nad nią podsumowanie z polami `Status:`, `Delivered on:`, `Signed by:` oraz `Delivered to:` po zakończeniu dostawy.

| Zdarzenie Spee-Dee | Co oznacza |
| --- | --- |
| `ARRIVAL SCAN` | Zeskanowana w terminalu lub sortowni Spee-Dee w drogowej sieci Środkowego Zachodu. |
| `OUT FOR DELIVERY` *(często z numerem trasy w nawiasie)* | Załadowana do lokalnego samochodu dostawczego Spee-Dee do doręczenia dzisiaj. |
| `Delivered` | Doręczona; podsumowanie na stronie Spee-Dee zapisuje też godzinę doręczenia, adres oraz podpis odbiorcy, jeśli został zebrany. |
| `No packages were found matching the barcode supplied.` | Kod kreskowy nie został jeszcze zeskanowany w systemie Spee-Dee lub zawiera literówkę. |

### Ponownie używane 16-cyfrowe kody kreskowe

Spee-Dee po pewnym czasie wykorzystuje starsze 16-cyfrowe kody `SP` dla nowych paczek. Na oryginalnej stronie Spee-Dee pod skanami Twojej bieżącej przesyłki mogą wciąż widnieć wpisy z poprzednio dostarczonej paczki. Sprawdzając ponownie użyty kod na stronie Spee-Dee, patrz na daty u góry tabeli i przestań czytać, gdy trafisz na starszy wpis `Delivered` z wcześniejszej przesyłki.

## Opóźnienia, brak skanów i reklamacje

Kilka kwestii pomaga wyjaśnić nietypowy przebieg śledzenia:

- **Brak skanów:** jeśli strona Spee-Dee wyświetla `No packages were found matching the barcode supplied.`, przesyłka nie była jeszcze skanowana albo w kodzie jest błąd. Sprawdź kod, zapytaj sprzedawcę, czy przekazał paczkę do Spee-Dee, lub zobacz [brak aktualizacji śledzenia](guide:tracking-not-updating).
- **Limity przy częstym odświeżaniu:** serwer Spee-Dee ogranicza liczbę wyszukiwań na minutę i przy szybkich ponowieniach zwraca chwilowo błąd HTTP 503. Odczekanie minuty lub pozostawienie sprawdzania w Peek pozwala uniknąć limitu.
- **Reklamacje niedoręczenia lub uszkodzenia:** [formularz obsługi klienta Spee-Dee](https://speedeedelivery.com/customer-service/) ma osobne kategorie **Claims – Non-Delivery** i **Claims – Damage** oraz prosi o podanie **pełnego adresu dostawy** przy pytaniach o śledzenie. Zobacz też poradnik [dostarczona, ale nieodebrana](guide:delivered-not-received).

## Śledzenie Spee-Dee w Peek

Wklej dowolny 18-znakowy (`SP` + 16 cyfr) lub 20-znakowy (`SP` + 18 cyfr) kod kreskowy Spee-Dee albo link `speedeedelivery.com` w Peek, a Peek rozpozna Spee-Dee natychmiast. Jeśli kiedykolwiek trzeba zmienić przewoźnika, użyj „Zmień” przy dodawaniu lub „Zmień przewoźnika” na ekranie śledzenia.

Peek pobiera stronę postępu Spee-Dee bezpośrednio przez otwarty [adapter Spee-Dee w universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/spee-dee), a gdy serwer Spee-Dee odrzuci połączenie bezpośrednie — przez sieć zdalną. Przy **ponownie użytym kodzie** zachowuje wyłącznie wiersze od poprzedniego doręczenia co najmniej dzień wcześniej. Usuwa komentarze debugujące HTML i numer trasy po `OUT FOR DELIVERY`, zapisując miasto lub terminal oraz godzinę każdego skanu. Spee-Dee podaje godziny lokalne bez przesunięcia UTC: Peek datuje każdy skan według strefy jego miejsca (czas Chicago dla St. Cloud, Fargo, Milwaukee lub Sioux Falls, czas Detroit dla Detroit), skan bez miejsca według sąsiedniego skanu, w pozostałych przypadkach według czasu Nowego Jorku, i wyświetla wszystko na zegarze Twojego urządzenia. Tracker Spee-Dee nie udostępnia szacowanej daty doręczenia ani wagi.

Peek przypisuje `ARRIVAL SCAN` do etapu „W transporcie”, `OUT FOR DELIVERY` do „W doręczeniu”, a `Delivered` do „Dostarczona”, zaś pozostałe zdarzenia klasyfikuje według wspólnych reguł statusów.

Gdy masz przesyłkę otwartą w Peek albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza status nawet co 10 minut; w dniu, gdy Spee-Dee pokazuje `OUT FOR DELIVERY`, co 2 minuty od godz. 6:00 według zegara najnowszego skanu — albo co 5 minut, jeśli ostatnie sprawdzenie musiało otworzyć przeglądarkę — dopóki tracker Spee-Dee odpowiada. W pozostałym czasie Peek sprawdza co godzinę. Po 12 godzinach wyłącznie na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika” albo po dwóch dniach bez zmian sprawdza co godzinę niezależnie od sposobu śledzenia, a po siedmiu dniach na „Zarejestrowana” — co 6 godzin. Gdy Spee-Dee nie odpowiada i Peek śledzi przesyłkę wyłącznie przez usługi śledzące, sprawdza co godzinę, co 3 godziny po spokojnym dniu i nawet co 15 minut w dniu, w którym przesyłka jest w doręczeniu, dopóki masz ją otwartą lub włączone powiadomienia.

Jeśli najnowszy skan ma cztery dni, a przesyłka nadal widnieje jako „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie”, Peek oznacza ją dniem tego skanu, np. „Brak aktualizacji od pon 12 paź”. Na stronie przesyłki pojawia się wtedy komunikat „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika Spee-Dee.” wraz z linkiem „Poproś przewoźnika Spee-Dee o pomoc” do strony obsługi klienta. Po trzech tygodniach bez nowego skanu pojawia się informacja „Od ponad trzech tygodni nic nowego. Jeśli przesyłka wciąż nie dotarła, zapytaj sprzedawcę. Peek dalej pyta przewoźnika Spee-Dee.”

W preferencjach powiadomień Peek wybierz „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”, aby ustawić swoje alerty.

## Kontakt z Spee-Dee Delivery

Spee-Dee podkreśla na swojej [stronie Customer Service](https://speedeedelivery.com/customer-service/), że dzwoniący rozmawiają bezpośrednio z człowiekiem, a nie z automatyczną centralą:

- **Bezpłatna infolinia obsługi klienta:** `800-862-5578`
- **Telefon lokalny:** `320-251-6697`
- **Faks:** `320-251-1846` (faks LTL: `320-240-7995`)
- **Formularz kontaktowy i reklamacyjny online:** [Spee-Dee Customer Service](https://speedeedelivery.com/customer-service/) (do pytań o śledzenie dołącz kod `SP` i pełny adres dostawy)
- **Adres siedziby:** Spee-Dee Delivery Service Inc., 4101 Clearwater Road, St. Cloud, MN 56301
- **Adres korespondencyjny:** P.O. Box 1417, St. Cloud, MN 56302
- **O firmie i śledzenie:** [O Spee-Dee](https://speedeedelivery.com/about-us/) oraz [Track a Shipment](https://speedeedelivery.com/track-a-shipment/)

## Często zadawane pytania

### Dlaczego moja strona Spee-Dee pokazuje doręczenie sprzed miesięcy?

Spee-Dee z czasem ponownie wykorzystuje 16-cyfrowe kody `SP`, więc jej surowa strona potrafi wyświetlać starszą doręczoną przesyłkę pod skanami bieżącej paczki. Peek automatycznie wykrywa tę granicę i ukrywa wiersze z wcześniejszych doręczeń.

### Jakie stany USA obsługuje Spee-Dee Delivery?

Spee-Dee obejmuje całe stany Illinois, Iowa, Minnesota, Dakota Północna, Dakota Południowa i Wisconsin oraz wybrane miasta i kody ZIP w stanach Kansas, Michigan, Missouri i Nebraska.

### Czy Spee-Dee Delivery podaje szacowaną datę doręczenia na stronie śledzenia?

Nie. Strona śledzenia Spee-Dee pokazuje skany przybycia do terminali, `OUT FOR DELIVERY` oraz szczegóły końcowego doręczenia, bez szacowanej daty dostawy. Typowe czasy transportu według kodu ZIP sprawdzisz na [mapach zasięgu Spee-Dee](https://speedeedelivery.com/services/service-area-maps/).

### Co podać przy kontakcie z Spee-Dee w sprawie paczki?

Przygotuj pełny 18- lub 20-znakowy numer `SP` oraz pełny adres dostawy — formularz obsługi klienta Spee-Dee wyraźnie wymaga pełnego adresu dostawy przy pytaniach o śledzenie.

:::sources
- [Spee-Dee Delivery](https://speedeedelivery.com/)
- [Spee-Dee — About Us](https://speedeedelivery.com/about-us/)
- [Spee-Dee — Track a Shipment](https://speedeedelivery.com/track-a-shipment/)
- [Spee-Dee — Service Area Maps](https://speedeedelivery.com/services/service-area-maps/)
- [Spee-Dee — Customer Service](https://speedeedelivery.com/customer-service/)
- [universal-parcel-scraper Spee-Dee adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/spee-dee)
:::
