---
title: Śledzenie Korea Post: kody EMS KR, 13 cyfr i pomoc
description: Śledź przesyłkę Korea Post (ePOST): numery S10 z końcówką KR, 13-cyfrowe kody krajowe, statusy EMS i koreańskie, odprawa celna, terminy i kontakt.
slug: sledzenie-korea-post
published: 2026-11-24
updated: 2026-11-24
---

Korea Post (*우정사업본부*, portal online *ePOST* / *인터넷우체국*) to narodowy operator pocztowy Korei Południowej podlegający Ministerstwu Nauki i ICT. Obsługuje krajowe paczki i przesyłki polecone na terenie Korei Południowej oraz międzynarodowe usługi **EMS** (Express Mail Service do ponad 100 kierunków oraz ponad 70 krajów przez EMS Premium), **K-Packet** i międzynarodowe lotnicze przesyłki polecone i paczki. Przesyłki międzynarodowe sprawdzisz w [anglojęzycznym śledzeniu EMS Korea Post](https://trace.epost.go.kr/xtts/tt/epost/ems/ems_eng.jsp), przesyłki krajowe w [krajowym śledzeniu ePOST](https://service.epost.go.kr/iservice/usr/trace/usrtrc001k01.jsp), albo po prostu wklejając numer w pole powyżej.

## Numery śledzenia Korea Post: format S10 z końcówką KR i 13 cyfr krajowych

Sprawdź potwierdzenie nadania z poczty, stronę zamówienia w koreańskim sklepie albo e-mail z informacją o wysyłce. Korea Post stosuje dwa główne formaty w zależności od tego, czy przesyłka leci za granicę, czy porusza się po Korei Południowej:

| Format (wymyślony) | Co to jest |
| --- | --- |
| `EM482917367KR` | Międzynarodowa przesyłka **EMS** z towarem (format UPU S10 zakończony `KR`; dokumenty zwykle zaczynają się od `EE`). |
| `RR482917367KR` | Międzynarodowa przesyłka polecona lub K-Packet (format UPU S10: 2 litery + 9 cyfr z cyfrą kontrolną + `KR`). |
| `6482917364058` | 13-cyfrowy krajowy numer południowokoreańskiej przesyłki poleconej lub paczki (zaczynający się od cyfr `1`–`6`). Taki sam 13-cyfrowy układ numeryczny stosują [Correos de Chile](carrier:correos-chile), [Delhivery](carrier:delhivery), [Econt](carrier:econt), [Paquetexpress](carrier:paquetexpress) i inni przewoźnicy. |

Na [stronie przeglądu EMS Korea Post](https://ems.epost.go.kr/front.Introduction01.postal) prefiks `EE` oznacza dokumenty EMS (niewymagające celnej kontroli towarów), a prefiks `EM` przesyłki EMS z towarami. Inne międzynarodowe kody pocztowe opisuje poradnik [formaty numerów śledzenia](guide:tracking-number-formats).

## Co oznaczają statusy śledzenia Korea Post

Przesyłki międzynarodowe `KR` wyświetlają angielskie wpisy UPU/EMS z zachowaniem czasu lokalnego zgłaszanego przez operatora pocztowego w danym kraju, natomiast 13-cyfrowe przesyłki krajowe pokazują opisy po koreańsku w czasie standardowym Korei (`Asia/Seoul`, UTC+9 przez cały rok):

| Komunikat Korea Post (międzynarodowy / krajowy) | Co oznacza dla Ciebie | Peek pokazuje |
| --- | --- | --- |
| `운송장출력` *(krajowa)* | Wydrukowano list przewozowy przed nadaniem w placówce pocztowej. | „Zarejestrowana” |
| `Posting/Collection` / `접수`, `집하완료`, `인수완료` | Nadana w okienku koreańskiej poczty lub odebrana przez kuriera Korea Post. | „Przyjęta przez przewoźnika” |
| `Arrival at outward office of exchange`, `Ready for dispatch`, `Handed over to a transport company`, `Received by Air carriers`, `Departure from the Airport`, `Arrival at Destination Airport`, `Delivered to Destination Post`, `Arrival at inward office of exchange`, `Departure from inward office of exchange` / `발송`, `도착` | Opracowana w koreańskim urzędzie wymiany (np. Incheon), wysłana samolotem, przekazana poczcie kraju docelowego lub przemieszczana między krajowymi sortowniami. | „W transporcie” |
| `Ready for customs clearance` | Przedstawiona do odprawy celnej w kraju docelowym (lub podczas kontroli celnej eksportu/importu). | „W urzędzie celnym” |
| `배달준비` *(krajowa)* | Przygotowana do doręczenia przez kuriera lokalnej placówki pocztowej w Korei. | „W doręczeniu” |
| `Unsuccessful delivery` | Próba doręczenia w kraju docelowym nie powiodła się. | „Próba doręczenia” |
| `Delivery complete` / `배달완료` | Doręczona; śledzenie Korea Post może wskazywać imię lub nazwę odbiorcy. | „Dostarczona” |

## Czas dostawy EMS Korea Post, cło i przekazanie lokalnej poczcie

Według [przeglądu usługi EMS Korea Post](https://ems.epost.go.kr/front.Introduction01.postal), [warunków wysyłki według kraju](https://ems.epost.go.kr/front.EmsApplyGoCondition.postal) oraz [kalkulatora czasu i ceny dostawy](https://ems.epost.go.kr/front.EmsDeliveryDelivery09.postal):

- **Czasy transportu EMS:** dla dokumentów (numery `EE`) nadanych w Seulu przed południem Korea Post podaje następny dzień lub 3–4 dni do miast z bezpośrednimi lotami, takich jak Tokio, Hongkong i Singapur, oraz 4–6 dni do pozostałych miejsc; strona przeglądu podaje 2–3 i 3–5 dni dla dokumentów nadanych w Seulu rano. Dolica się dzień dla przesyłek nadanych po południu lub poza Seulem albo adresowanych poza miasto docelowego urzędu wymiany; przy nadaniu w placówce górskiej lub wyspowej dochodzą dni na dotarcie do International Mail Logistics Center. Przesyłki z towarami (numery `EM`) czekają dodatkowo na odprawę celną w kraju docelowym. Dzień nadania i święta nie wliczają się do terminu.
- **Deklaracje celne:** dokumenty i druki korzystają z deklaracji CN22, natomiast towary i przedmioty podlegające opłatom wymagają deklaracji CN23 (oraz faktury handlowej i ewentualnych zezwoleń przy przesyłkach komercyjnych). Przedmioty zakazane i ograniczone wymienia [przewodnik ePOST o rzeczach zabronionych i ograniczonych](https://ems.epost.go.kr/front.Introduction04.postal).
- **Przekazanie lokalnej poczcie:** gdy przesyłka `KR` dotrze do Twojego kraju (`Delivered to Destination Post` / `Arrival at inward office of exchange`), krajowy operator pocztowy (na przykład [USPS](carrier:usps), [Royal Mail](carrier:royal-mail), [La Poste](carrier:la-poste), [DHL](carrier:dhl), [Japan Post](carrier:japan-post) lub [Australia Post](carrier:australia-post)) zajmuje się poborem opłat celnych, próbami doręczenia i awizowaniem według własnych zasad. Sprawdź ten sam numer `...KR` także na stronie lokalnej poczty, aby zobaczyć pełniejsze opcje dostawy. Zobacz [przesyłki zatrzymane na cle](guide:customs).

## Przesyłka Korea Post opóźniona, utknęła w drodze lub zaginęła

- **Cisza po `Departure from the Airport`:** międzynarodowe ładunki pocztowe EMS i K-Packet często nie dostają żadnego skanu od kilku dni do ponad tygodnia między wylotem z Korei Południowej a zarejestrowaniem w urzędzie wymiany kraju docelowego (`Arrival at inward office of exchange`). Zobacz [dlaczego śledzenie przestaje się aktualizować](guide:tracking-not-updating).
- **Postój na `Ready for customs clearance`:** sprawdź ten sam numer `...KR` w serwisie poczty swojego kraju, czy nie czeka nieopłacony VAT importowy, cło albo opłata manipulacyjna.
- **Przesyłka zagubiona, uszkodzona lub niekompletna:** przy zakupach online najpierw skontaktuj się z koreańskim sprzedawcą lub platformą; ePOST przyjmuje reklamacje i wnioski o poszukiwanie (*행방조사청구*) online lub pisemnie i publikuje zasady odszkodowań (*손해배상규정*). Zobacz [doręczona, ale nieotrzymana](guide:delivered-not-received), [uszkodzona przesyłka](guide:damaged-parcel), [zaginiona przesyłka](guide:lost-parcel) oraz [zwrócona nadawcy](guide:returned-to-sender).

## Obsługa klienta i kontakt Korea Post

Oficjalne dane kontaktowe z [Centrum Obsługi Klienta ePOST](https://www.epost.go.kr/bbs.RetrieveBbsMain.comm), [głównego portalu ePOST](https://www.epost.go.kr/main.retrieveMainPage.comm) oraz [przeglądu usługi EMS](https://ems.epost.go.kr/front.Introduction01.postal):

- **Centrum Obsługi Klienta Korea Post (w Korei Południowej):** `1588-1300` (dni robocze `09:00–18:00` KST, nieczynne w soboty i święta; połączenie płatne).
- **Z zagranicy:** `+82-42-609-4295`, w tych samych godzinach.
- **E-mail:** `help@posa.or.kr`.
- **FAQ i zapytania online:** [Centrum Obsługi Klienta ePOST](https://www.epost.go.kr/bbs.RetrieveBbsMain.comm) oraz opcja *e-mail EMS Inquiry* na [trace.epost.go.kr](https://trace.epost.go.kr/xtts/tt/epost/ems/ems_eng.jsp).
- **Adres w stopce ePOST:** 1 Jeongbohwa-gil (Bitgaram-dong), Naju, 58323, Korea Południowa.

> Ignoruj fałszywe SMS-y z informacją, że przesyłka Korea Post nie może zostać doręczona bez uiszczenia drobnej opłaty pod nieoficjalnym linkiem. Prawdziwe opłaty importowe za przychodzącą paczkę `KR` pobiera poczta lub portal celny Twojego kraju, nigdy przypadkowy link z SMS-a. Zobacz [fałszywe SMS-y o przesyłkach](guide:delivery-scam-texts).

## Śledzenie przesyłki Korea Post w Peek

[Peek](/) automatycznie i z wysoką pewnością rozpoznaje każdy numer UPU S10 kończący się na `KR` (`EM482917367KR` lub `RR482917367KR`, cyfry wymyślone) jako Korea Post, a także wklejone linki `trace.epost.go.kr` z parametrem `POST_CODE=` oraz `service.epost.go.kr` z `sid1=`. 13-cyfrowy numer krajowy (`6482917364058`) pasuje do kilku przewoźników: Peek wymienia wśród nich Korea Post i prosi o wybór. Możesz też wybrać go później przyciskiem „Zmień przewoźnika” na ekranie przesyłki (wyszukanie „South Korea Post” lub „ePOST” również go znajduje) albo wkleić link z ePOST.

Peek odczytuje anglojęzyczne śledzenie EMS Korea Post dla numerów `KR` oraz koreańskie śledzenie krajowe ePOST dla numerów 13-cyfrowych. Pokazuje etap („Zarejestrowana”, „Przyjęta przez przewoźnika”, „W transporcie”, „W urzędzie celnym”, „W doręczeniu” dla paczek krajowych, „Próba doręczenia”, „Dostarczona”), treść skanu wraz z placówką pocztową lub lotniskiem oraz godzinę doręczenia w przesyłkach krajowych. Skany krajowe mają datę według czasu koreańskiego, a międzynarodowe według strefy czasowej danego miejsca (np. Nowego Jorku dla skanu w Nowym Jorku); Peek przelicza je na zegar Twojego urządzenia. Nazwa odbiorcy podawana przez Korea Post pojawia się pod „Odbiorca” na Twojej stronie przesyłki, nigdy na udostępnionym linku; nazwisko osoby podpisującej nie trafia do żadnej aplikacji Peek, a Peek pomija nazwę usługi, nazwę nadawcy i numery telefonów placówek. Śledzenie Korea Post nie podaje szacowanej daty doręczenia, wagi ani punktu odbioru.

Dopóki masz przesyłkę otwartą w Peek albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza status do co 10 minut; w dniu, gdy paczka krajowa pokazuje `배달준비`, co 2 minuty od 6:00 czasu koreańskiego, dopóki odpowiada śledzenie Korea Post. W pozostałych sytuacjach sprawdza co godzinę. Po 12 godzinach z samym statusem „Zarejestrowana” lub „Przyjęta przez przewoźnika” albo po dwóch dniach bez wiadomości przechodzi na sprawdzanie co godzinę niezależnie od sposobu śledzenia, a po siedmiu dniach na „Zarejestrowana” co 6 godzin. Gdy śledzenie Korea Post nie odpowiada i Peek korzysta wyłącznie z zewnętrznych usług śledzenia, sprawdza co godzinę, po spokojnym dniu co 3 godziny i do co 15 minut w dniu doręczenia, dopóki masz przesyłkę otwartą lub włączone powiadomienia.

Kiedy najnowszy skan ma cztery dni, a przesyłka nadal stoi na „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie”, Peek oznacza ją dniem tego skanu, np. „Brak aktualizacji od pon., 12 paź”. Na stronie przesyłki wyświetla się wtedy „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika Korea Post.” oraz link „Poproś przewoźnika Korea Post o pomoc” do Centrum Obsługi Klienta ePOST. Po trzech tygodniach bez nowego skanu pojawia się komunikat „Od ponad trzech tygodni nic nowego. Jeśli przesyłka wciąż nie dotarła, zapytaj sprzedawcę. Peek dalej pyta przewoźnika Korea Post.”

Możesz wybrać powiadomienia dla opcji „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek jedynie monitoruje aktualizacje śledzenia i nie może odprawić przesyłki celnie, przekierować paczki ani złożyć reklamacji EMS w Korea Post w Twoim imieniu.

## Pytania o śledzenie Korea Post

### Co oznaczają `EE...KR`, `EM...KR` i `RR...KR`?

To 13-znakowe międzynarodowe numery pocztowe UPU S10 nadane w Korei Południowej: `EE` oznacza dokumenty EMS, `EM` przesyłki EMS z towarami, a `RR` (oraz inne prefiksy `R`/`C`/`L`) przesyłki polecone, K-Packet i paczki międzynarodowe.

### Dlaczego moja paczka EMS Korea Post przestała się aktualizować po opuszczeniu lotniska?

Po pojawieniu się `Departure from the Airport` w Korei Południowej śledzenie milczy aż do momentu, gdy operator pocztowy kraju docelowego zeskanuje ładunek w swoim urzędzie wymiany (`Arrival at inward office of exchange`). Sprawdź ten sam numer `...KR` także na stronie swojej poczty narodowej.

### Czy Korea Post sama doręcza przesyłki za granicą?

Korea Post odpowiada za odbiór, sortowanie eksportowe i wysyłkę lotniczą z Korei Południowej, a następnie przekazuje przesyłkę (`Delivered to Destination Post`) poczcie narodowej kraju docelowego do odprawy importowej i doręczenia pod adres.

### Jak skontaktować się z obsługą klienta Korea Post?

Zadzwoń pod `1588-1300` w Korei Południowej (pon.–pt. `09:00–18:00` KST, nieczynne w soboty i święta), napisz na `help@posa.or.kr` albo skorzystaj z [Centrum Obsługi Klienta ePOST](https://www.epost.go.kr/bbs.RetrieveBbsMain.comm).

:::sources
- [Korea Post: anglojęzyczne śledzenie EMS](https://trace.epost.go.kr/xtts/tt/epost/ems/ems_eng.jsp) – międzynarodowy portal śledzenia EMS i partnerskie poczty docelowe
- [Korea Post (ePOST): śledzenie krajowe](https://service.epost.go.kr/iservice/usr/trace/usrtrc001k01.jsp) – 13-cyfrowe śledzenie krajowych paczek i przesyłek poleconych
- [Korea Post (ePOST): przegląd usługi EMS](https://ems.epost.go.kr/front.Introduction01.postal) – prefiksy `EE` i `EM`, terminy EMS, zasady celne CN22/CN23, ponad 100 kierunków EMS / ponad 70 EMS Premium i `1588-1300`
- [Korea Post (ePOST): warunki wysyłki według kraju](https://ems.epost.go.kr/front.EmsApplyGoCondition.postal) – krajowe limity EMS i uwagi celne
- [Korea Post (ePOST): kalkulator czasu dostawy i cen](https://ems.epost.go.kr/front.EmsDeliveryDelivery09.postal) – kalkulator dla EMS, EMS Premium i paczek
- [Korea Post (ePOST): przedmioty zakazane i ograniczone](https://ems.epost.go.kr/front.Introduction04.postal) – międzynarodowe ograniczenia w wysyłce
- [Korea Post (ePOST): Centrum Obsługi Klienta](https://www.epost.go.kr/bbs.RetrieveBbsMain.comm) – `1588-1300`, `help@posa.or.kr`, godziny w dni robocze `09:00–18:00` KST i adres w Naju (URL pomocy linkowany przez Peek)
- [Korea Post (ePOST): portal główny](https://www.epost.go.kr/main.retrieveMainPage.comm) – przegląd paczek krajowych, poleconych, EMS i K-Packet, oraz `+82-42-609-4295` z zagranicy
- [Universal Parcel Scraper: korea-post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/korea-post) – rozpoznawanie `KR` S10 i 13 cyfr, reguły linków `trace.epost.go.kr` / `service.epost.go.kr`, mapy statusów EMS po angielsku i krajowych po koreańsku, strefy czasowe, pomijane pola i URL pomocy
:::
