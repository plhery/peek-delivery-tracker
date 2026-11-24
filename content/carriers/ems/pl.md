---
title: Śledzenie EMS: kody S10, statusy i pomoc pocztowa
description: Śledź międzynarodową przesyłkę EMS (Express Mail Service): 13-znakowe kody S10 na E, statusy celne i doręczenia UPU, opóźnienia oraz pomoc pocztową.
slug: sledzenie-ems
published: 2026-11-24
updated: 2026-11-24
---

**EMS** (*Express Mail Service*) to międzynarodowa ekspresowa usługa pocztowa dla dokumentów i towarów. **Światowy Związek Pocztowy (UPU)** utworzył **Spółdzielnię EMS** ([ems.post](https://www.ems.post/en)) w celu jej rozwijania; w czerwcu 2026 r. Spółdzielnia liczyła 179 członków. Zamiast utrzymywać własne samoloty czy samochody dostawcze, EMS łączy wyznaczonych narodowych operatorów pocztowych na całym świecie — takich jak [Japan Post](carrier:japan-post), [China Post](carrier:china-post), [Korea Post](carrier:korea-post), [USPS](carrier:usps), [Royal Mail](carrier:royal-mail), [La Poste](carrier:la-poste), [Poste Italiane](carrier:poste-italiane) oraz [Australia Post](carrier:australia-post) — dzięki czemu przesyłki ekspresowe poruszają się pod jednym ustandaryzowanym 13-znakowym numerem. Przesyłkę sprawdzisz w globalnej wyszukiwarce EMS na [items.ems.post](https://items.ems.post/) lub wklejając numer EMS w pole powyżej.

## Numery śledzenia EMS (13-znakowy format UPU S10)

Każda przesyłka EMS ma 13-znakowy kod kreskowy UPU S10 zaczynający się od litery **`E`** (od Express), po której następuje druga litera usługi, 8 cyfr seryjnych, 1 cyfra kontrolna S10 oraz 2-literowy kod ISO kraju nadania:

| Format (wymyślony) | Co to jest |
| --- | --- |
| `EA482917367JP` | 13-znakowa przesyłka EMS nadana w Japonii (prefiks `EA` + 9 cyfr z poprawną cyfrą kontrolną S10 + `JP`). |
| `EE739184627CN` | 13-znakowa przesyłka EMS nadana w Chinach (prefiks `EE` + 9 cyfr z poprawną cyfrą kontrolną S10 + `CN`). |

Ostatnie dwie litery zawsze wskazują **operatora pocztowego kraju nadania**, a nie kraj docelowy. Paczka wysłana przez EMS z Tokio do Paryża kończy się na `JP` przez całą podróż, nawet po przejęciu doręczenia we Francji przez [La Poste](carrier:la-poste). Kody pocztowe nie zaczynające się od `E` (np. listy polecone `RR...` albo zwykłe paczki `CP...`) są odrzucane przez tracker EMS jako niebędące przesyłkami EMS. Zobacz [formaty numerów przesyłek](guide:tracking-number-formats).

## Co oznaczają statusy śledzenia EMS

Publiczna tabela śledzenia Spółdzielni EMS na [items.ems.post](https://items.ems.post/) tłumaczy zdarzenia EDI od uczestniczących operatorów pocztowych na ujednolicone angielskie opisy:

| EMS podaje | Co to oznacza dla Ciebie | Peek pokazuje |
| --- | --- | --- |
| `Posted` | Nadana w placówce lub odebrana przez operatora pocztowego w kraju nadania. | „Przyjęta przez przewoźnika” |
| `Arrived at export office`, `Departed from export office` | Opracowana w międzynarodowym urzędzie wymiany kraju nadania i wysłana za granicę. | „W transporcie” |
| `Presented to export customs and security`, `Held for export customs inspection` | Przechodzi kontrolę celną eksportową lub bezpieczeństwa lotniczego w kraju nadania. | „W urzędzie celnym” |
| `Released from export customs and security` | Zwolniona po kontroli eksportowej i gotowa do wysyłki międzynarodowej. | „W transporcie” |
| `Export cancelled` | Wysyłka eksportowa została anulowana lub cofnięta przed opuszczeniem kraju nadania; skontaktuj się z nadawcą. | „Zgłoszono problem” |
| `Arrived at destination import office`, `Departed from destination import office` | Dotarła do międzynarodowego urzędu wymiany kraju docelowego lub opuściła go w stronę krajowej sortowni. | „W transporcie” |
| `Held at destination import office`, `Presented to import customs` | Zatrzymana w granicznym urzędzie wymiany lub zgłoszona do odprawy celnej importowej w kraju docelowym. | „W urzędzie celnym” |
| `Released from import customs` | Zwolniona z odprawy celnej w kraju docelowym i przekazana do krajowej sieci pocztowej. | „W transporcie” |
| `Arrived at sorting center`, `Arrived at post office` | Przemieszcza się między krajowymi sortowniami poczty docelowej albo dotarła do lokalnej placówki doręczającej. | „W transporcie” |
| `Out for delivery` | Wydana lokalnemu listonoszowi lub kurierowi EMS do doręczenia. | „W doręczeniu” |
| `Delivery attempted` | Próba doręczenia nie powiodła się; sprawdź awizo lub powiadomienie z lokalnej placówki pocztowej. | „Próba doręczenia” |
| `Returned to sender` | Zwracana do kraju nadania po nieudanym doręczeniu, odmowie odbioru lub odrzuceniu celnym. | „Zwrócona nadawcy” |
| `Delivered` | Doręczona odbiorcy lub w uprawnionym punkcie odbioru przez operatora docelowego. | „Dostarczona” |

## Jak działają przekazania międzynarodowe i odprawa celna w EMS

Ponieważ EMS jest siecią partnerską poczt narodowych wymienionych w [katalogu operatorów EMS](https://www.ems.post/en/global-network/ems-operators), każdą przesyłkę transgraniczną obsługują dwie różne instytucje pocztowe:

- **Poczta nadania a poczta docelowa:** operator kraju nadania odpowiada za przyjęcie, odprawę eksportową i wysłanie za granicę; od momentu pojawienia się `Arrived at destination import office` za odprawę importową, próby doręczenia, ponowne doręczenie i czas awizowania w placówce odpowiada Twój narodowy operator pocztowy (lub jego oddział ekspresowy, np. Parcelforce w Wielkiej Brytanii).
- **Sprawdzaj i EMS, i swoją lokalną pocztę:** globalny feed EMS przekazuje ustandaryzowane kamienie milowe EDI, podczas gdy strona śledzenia poczty docelowej często dodaje pełniejsze informacje krajowe — linki do opłat celnych, adres placówki odbioru, przedziały doręczenia czy zamówienie ponownego doręczenia.
- **Cło i importowy VAT:** transgraniczne przesyłki EMS podlegają przepisom celnym kraju docelowego, podatkowi VAT od importu, pocztowym opłatom za odprawę oraz cłom (w tym unijnym opłatom za import spoza UE). Gdy śledzenie zatrzyma się na `Presented to import customs` lub `Held at destination import office`, wypatruj oficjalnego listu, e-maila lub powiadomienia w portalu lokalnej poczty. Zobacz [przesyłki zatrzymane w urzędzie celnym](guide:customs).

## Przesyłka EMS opóźniona, zatrzymana lub zaginiona

- **Cisza między eksportem a importem:** międzynarodowe przesyłki lotnicze często nie mają nowych skanów przez kilka dni między `Departed from export office` a `Arrived at destination import office`, zwłaszcza w sezonach szczytowych lub w kolejce do przyjęcia celnego. Zobacz [dlaczego śledzenie przestaje się aktualizować](guide:tracking-not-updating).
- **Pomaga lokalna poczta, nie UPU:** jak podaje [EMS Tracking](https://www.ems.post/en/global-network/tracking), Jednostka EMS ani UPU nie udzielają informacji o śledzeniu telefonicznie i nie ingerują w spory między operatorami pocztowymi a klientami. W razie zaginięcia przesyłki EMS [strona kontaktowa EMS](https://www.ems.post/en/about-us/contacts-0) kieruje do krajowego biura obsługi klienta lub infolinii EMS, które odpowiadają na ogólne pytania o EMS, pomagają odnaleźć numer śledzenia i zlokalizować przesyłkę. Zobacz [zaginiona przesyłka](guide:lost-parcel), [uszkodzona przesyłka](guide:damaged-parcel), [dostarczona, ale nieodebrana](guide:delivered-not-received) oraz [zwrócona nadawcy](guide:returned-to-sender).

## Obsługa klienta EMS i oficjalny katalog operatorów

Ponieważ usługę EMS w każdym kraju prowadzi jego wyznaczona poczta narodowa, strona [EMS Contact Us](https://www.ems.post/en/about-us/contacts-0) publikuje wyszukiwalny katalog A–Z krajowych stron obsługi klienta i infolinii EMS z filtrowaniem według regionu (Afryka, Kraje Arabskie, Azja i Pacyfik, Karaiby, Europa, Ameryka Łacińska i Ameryka Północna):

- **Znajdź telefon i pomoc lokalnego operatora EMS:** [ems.post/en/about-us/contacts-0](https://www.ems.post/en/about-us/contacts-0) oraz [ems.post/en/global-network/ems-operators](https://www.ems.post/en/global-network/ems-operators).
- **Globalna wyszukiwarka publiczna:** [items.ems.post](https://items.ems.post/) (instrukcje na [ems.post/en/global-network/tracking](https://www.ems.post/en/global-network/tracking)).
- **Brak centralnej infolinii UPU:** ani Jednostka EMS, ani Światowy Związek Pocztowy nie rozpatrują telefonicznie spraw pojedynczych przesyłek.

> Uważaj na fałszywe SMS-y twierdzące, że „paczka EMS” jest zablokowana do czasu zapłaty drobnej opłaty pod nieznanym linkiem. Wszelkie należności celne sprawdzaj zawsze bezpośrednio na oficjalnej stronie swojego narodowego operatora pocztowego, podając 13-znakowy kod EMS. Zobacz [fałszywe SMS-y o doręczeniu](guide:delivery-scam-texts).

## Śledzenie przesyłki EMS w Peek

Gdy wpiszesz 13-znakowy kod S10 zaczynający się od `E` (`EA482917367JP` lub `EE739184627CN`, wymyślone) do [Peek](/), Peek zwykle kieruje go najpierw do operatora pocztowego kraju nadania wskazanego przez dwie ostatnie litery (np. [Japan Post](carrier:japan-post) lub [China Post](carrier:china-post)), ponieważ EMS nie ma osobnej reguły automatycznego wykrywania. Aby zamiast tego odpytać globalny tracker Spółdzielni **EMS**, stuknij „Zmień” podczas dodawania lub „Zmień przewoźnika” na ekranie przesyłki i wybierz **EMS** (wyszukanie „Express Mail Service” albo „EMS Post” też go znajduje). Peek rozpoznaje także bezpośrednio jako EMS wklejone linki `items.ems.post/api/publicTracking/track?itemId=`.

Peek pobiera dane bezpośrednio z `items.ems.post` po sprawdzeniu, że numer to kod S10 z prefiksem `E` i poprawną cyfrą kontrolną, i wyświetla etap („Przyjęta przez przewoźnika”, „W transporcie”, „W urzędzie celnym”, „W doręczeniu”, „Próba doręczenia”, „Zgłoszono problem”, „Zwrócona nadawcy”, „Dostarczona”), angielski opis EMS i placówkę. EMS podaje godziny skanów bez strefy czasowej: Peek zapisuje każdy skan w strefie czasowej jego placówki (Tokio dla `TOKYO INTERNATIONAL`, Paryż dla `ROISSY CDG`), a skan bez podanego miejsca w strefie sąsiedniego skanu, po czym wyświetla wszystko według zegara Twojego urządzenia. Tabela EMS nie zawiera szacowanej daty doręczenia, wagi, nazwiska osoby podpisującej ani oddzielnej godziny doręczenia.

Gdy masz przesyłkę otwartą w Peek albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza status nawet co 10 minut; w dniu, gdy EMS pokazuje `Out for delivery`, co 2 minuty od godz. 6:00 czasu miejsca najnowszego skanu, dopóki tracker EMS odpowiada. W pozostałym czasie Peek sprawdza co godzinę. Po 12 godzinach wyłącznie na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika” albo po dwóch dniach bez zmian sprawdza co godzinę niezależnie od sposobu śledzenia, a po siedmiu dniach na „Zarejestrowana” — co 6 godzin. Gdy tracker EMS nie odpowiada i Peek śledzi przesyłkę wyłącznie przez usługi śledzące, sprawdza co godzinę, co 3 godziny po spokojnym dniu i nawet co 15 minut w dniu doręczenia, dopóki masz ją otwartą lub włączone powiadomienia.

Jeśli najnowszy skan ma cztery dni, a przesyłka nadal widnieje jako „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie”, Peek oznacza ją dniem tego skanu, np. „Brak aktualizacji od pon., 12 paź”. Na stronie przesyłki pojawia się wtedy komunikat „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika EMS.” wraz z linkiem „Poproś przewoźnika EMS o pomoc” do katalogu kontaktów EMS. Po trzech tygodniach bez nowego skanu pojawia się informacja „Od ponad trzech tygodni nic nowego. Jeśli przesyłka wciąż nie dotarła, zapytaj sprzedawcę. Peek dalej pyta przewoźnika EMS.”

Możesz wybrać powiadomienia dla opcji „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek śledzi wyłącznie aktualizacje statusu i nie może odprawić przesyłki celnie, opłacić należności importowych ani skontaktować się za Ciebie z lokalną pocztą.

## Pytania o śledzenie EMS

### Jak wygląda poprawny numer śledzenia EMS?

Numer śledzenia EMS to 13-znakowy kod UPU S10 zaczynający się od `E`, drugiej litery, 9 cyfr (z cyfrą kontrolną na końcu) i 2-literowego kodu ISO kraju nadania — na przykład `EA482917367JP` lub `EE739184627CN` (wymyślone).

### Kto doręcza moją przesyłkę EMS w moim kraju?

Przesyłki EMS doręcza wyznaczony narodowy operator pocztowy Twojego kraju (lub jego oddział ekspresowy), taki jak [USPS](carrier:usps) w Stanach Zjednoczonych, [Royal Mail](carrier:royal-mail) / Parcelforce w Wielkiej Brytanii, [La Poste](carrier:la-poste) we Francji, [Poste Italiane](carrier:poste-italiane) we Włoszech czy [Australia Post](carrier:australia-post) w Australii.

### Dlaczego moja przesyłka EMS przestała się aktualizować po `Departed from export office`?

Po statusie `Departed from export office` przesyłka jest w międzynarodowym transporcie lotniczym lub czeka w kolejce w międzynarodowym urzędzie wymiany kraju docelowego. Śledzenie wznowi się, gdy poczta docelowa zeskanuje `Arrived at destination import office`.

### Z kim się skontaktować, jeśli przesyłka EMS utknęła lub zaginęła?

UPU i Jednostka EMS nie prowadzą śledzenia telefonicznego ani nie ingerują w spory między pocztami a klientami. Skorzystaj z [ems.post/en/about-us/contacts-0](https://www.ems.post/en/about-us/contacts-0), aby znaleźć krajowe biuro obsługi klienta lub infolinię EMS, które pomogą zlokalizować przesyłkę.

:::sources
- [Spółdzielnia EMS: oficjalna strona](https://www.ems.post/en) – opis Spółdzielni Express Mail Service w ramach UPU i globalnej sieci pocztowej
- [Spółdzielnia EMS: przewodnik po śledzeniu](https://www.ems.post/en/global-network/tracking) – 13-znakowy format S10, brak interwencji UPU/Jednostki EMS i brak śledzenia telefonicznego
- [Spółdzielnia EMS: globalne kontakty operatorów](https://www.ems.post/en/about-us/contacts-0) – katalog A–Z krajowych biur obsługi klienta EMS według regionu (adres pomocy linkowany przez Peek)
- [Spółdzielnia EMS: operatorzy A–Z](https://www.ems.post/en/global-network/ems-operators) – sieć wyznaczonych narodowych operatorów pocztowych
- [Publiczny portal śledzenia EMS](https://items.ems.post/) – globalna publiczna wyszukiwarka przesyłek EMS
- [Universal Parcel Scraper: ems](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ems) – weryfikacja cyfry kontrolnej S10 z prefiksem `E`, reguła linków `items.ems.post`, mapowanie 18 statusów Spółdzielni EMS oraz przypisywanie strefy czasowej placówki każdemu skanowi
:::
