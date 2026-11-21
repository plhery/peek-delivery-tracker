---
title: Śledzenie CTTYEX: numery CTTY…CN, statusy i czas pekiński
description: Śledź przesyłkę CTTYEX (诚泰通运) z Shenzhen: układ CTTY…CN, angielskie skany w chińskiej wyszukiwarce, czas pekiński i śledzenie w Peek.
slug: sledzenie-cttyex
published: 2026-11-21
updated: 2026-11-21
---

CTTYEX to *Shenzhen Chengtai Tongyun International Logistics* (`深圳诚泰通运国际物流`, w skrócie `诚泰通运`) — transgraniczny spedytor e-commerce z Shenzhen w Chinach kontynentalnych, wysyłający zagraniczne paczki dla sprzedawców internetowych. Na swojej stronie podaje powstanie w 2011 roku, zasięg ponad 200 krajów i regionów, przeszło sześć oddziałów w Chinach kontynentalnych oraz obsługę sprzedawców na platformach Amazon, Wish, Tophatter, VOVA i Shopify wraz z lotami czarterowymi do USA, Wielkiej Brytanii, Kanady, Niemiec i Australii. Jego numery mają postać `CTTY`, dziesięć cyfr i `CN` (`CTTY` od *Chengtai Tongyun*): sprawdź swój numer na [cttyvip888.com](https://www.cttyvip888.com/assets/html/search.html) albo wklej go w polu powyżej.

## Numer śledzenia CTTYEX: jak wygląda

Strona CTTYEX nie opisuje formatu numerów, jednak wyszukiwarka nadaje paczkom jeden stały układ (przykłady zmyślone):

| Numer (przykładowy) | Co oznacza |
| --- | --- |
| `CTTY4829105736CN` | `CTTY`, dziesięć cyfr i `CN`: numer przesyłki CTTYEX. |
| `CTTY482910573CN` | Tylko dziewięć cyfr: brakuje jednego znaku. Jeśli żaden przewoźnik nie odpowie, strona na `peektracker.com` pokaże komunikat: „Zaczyna się jak numer śledzenia przewoźnika CTTYEX, ale brakuje jednego znaku: może jest w nim literówka. Porównaj go z wiadomością o wysyłce.” |
| `LX482910576CN` | Dwie litery, dziewięć cyfr i `CN`: numer [China Post](carrier:china-post), a nie CTTYEX, mimo końcówki `CN`. |

Peek przyjmuje małe litery i spacje. Strona CTTYEX — nie: pole wyszukiwania dopuszcza wyłącznie litery i cyfry, a wyszukiwarka dopasowuje numer tylko wielkimi literami (`CTTY…CN`, bez spacji).

**Gdzie znaleźć numer.** Klientami CTTYEX są sklepy wysyłkowe, więc numer otrzymasz od sprzedawcy w wiadomości o wysyłce lub w szczegółach zamówienia ([jak znaleźć numer śledzenia](guide:find-tracking-number)).

**Na stronie CTTYEX.** Otwórz `物流查询` (`search.html`), wpisz po jednym numerze w wierszu wielkimi literami i kliknij `提交` (wyślij): otworzy się strona śledzenia `快件跟踪` (`index/tracknumber/track`), wyszukująca wyłącznie paczki z ostatnich dwóch miesięcy.

## Statusy śledzenia CTTYEX — co oznaczają

Wyszukiwarka jest po chińsku, ale poszczególne skany wyświetlają się po angielsku (od najnowszego) pod chińskim wierszem podsumowania z krajem docelowym (`目的地`) i ogólnym statusem (`状态`):

| Podsumowanie CTTYEX | Co to oznacza dla Ciebie | Peek pokazuje |
| --- | --- | --- |
| `运输途中` (ang. `In Transit`) | W drodze; potrafi widnieć już wtedy, gdy zapisano dopiero dane zamówienia — sprawdź szczegółowe skany niżej. | „W transporcie” |
| `查询不到` (ang. `Not Found`) | Nie znaleziono: wyszukiwarka nie zna przesyłki o tym numerze i nie zwraca żadnych skanów. | Nie znaleziono |

Najpierw idą kroki w Chinach kontynentalnych, bez podanej miejscowości:

| CTTYEX podaje | Co to oznacza dla Ciebie | Peek pokazuje |
| --- | --- | --- |
| `Order information received` | Dane zamówienia od sprzedawcy dotarły do CTTYEX; sama paczka jest jeszcze u nadawcy. | „Zarejestrowana” |
| `Arrive at the cross border receiving point`, `Arrive at the receiving point` | Przyjęta w punkcie odbioru przesyłek transgranicznych CTTYEX w Chinach kontynentalnych. | „Przyjęta przez przewoźnika” |
| `Goods leave the operation center` | Opuściła centrum operacyjne CTTYEX w Chinach kontynentalnych. | „W transporcie” |
| `Arrival at domestic internationality airport` | Na lotnisku wylotu w Chinach kontynentalnych przed rejsem (`internationality` to pisownia CTTYEX). | „W transporcie” |
| `Departure from the airport of origin domestic` | Wyleciała z Chin kontynentalnych. | „W transporcie” |

Po przylocie wpisy pochodzą od agenta CTTYEX w kraju docelowym oraz sieci realizującej doręczenie:

| CTTYEX podaje | Co to oznacza dla Ciebie | Peek pokazuje |
| --- | --- | --- |
| `Arrive at the destination international airport`, `Item arrives at destination airport` | Samolot wylądował w Twoim kraju. | „W transporcie” |
| `Import customs clearance` | Odprawa celna importowa (zapisana po nazwie miasta portu wejścia); paczka nie jest jeszcze zwolniona. | „W urzędzie celnym” |
| `transfer to local acting company International self-operation team` | Przekazana po odprawie lokalnemu agentowi CTTYEX w Twoim kraju. | „W transporcie” |
| `Leave the … processing center` | Opuściła centrum opracowania (w miejscu wielokropka widnieje miasto). | „W transporcie” |
| `Internationaliy Package received at processing center` | Pierwszy skan sieci doręczającej (`Internationaliy` to pisownia CTTYEX; nie oznacza ponownego nadania). | „W transporcie” |
| `Arrived at logistics carrier Facility In Transit to Next Facility` | W obiekcie sieci doręczającej (nazywanej jedynie `logistics carrier`), w drodze do następnego. | „W transporcie” |
| `Accepted at logistics carrier Regional Destination Facility` | Pierwszy skan sieci doręczającej w Twoim regionie. | „W transporcie” |
| `Arrive at the last mile sorting and distribution center` | W sortowni i centrum dystrybucji ostatniej mili blisko Ciebie. | „W transporcie” |
| `Leaving the sorting center` | Opuściła tę sortownię. | „W transporcie” |
| `Arrive at the sorting and distribution center` | W sortowni (nazwa miasta stoi po słowie `Arrive`). | „W transporcie” |
| `package is on the way` | Następuje po skanie sortowni w danym mieście; nie oznacza jeszcze wyjazdu kuriera na rejon. | „W transporcie” |
| `Delivered` | Doręczona przez lokalną sieć (wiersz zaczyna się od miasta i kodu pocztowego). | „Dostarczona” |

W USA trzy wiersze od `Accepted at logistics carrier Regional Destination Facility` do `Leaving the sorting center` zaczynają się od miasta, stanu i kodu ZIP. Inne komunikaty Peek wyświetla dokładnie tak, jak zapisuje je CTTYEX.

**Każda godzina to czas pekiński.** Wyszukiwarka podaje godziny bez strefy czasowej, lecz wszystkie są w czasie pekińskim (`UTC+8`) — **także przy skanach wykonanych w Twoim kraju**. Ponieważ w Pekinie doba zmienia się o `16:00 UTC`, skan wykonany w Polsce po `17:00` zimą (lub po `18:00` latem) ma w wyszukiwarce CTTYEX już jutrzejszą datę.

## Kto doręcza paczki CTTYEX?

CTTYEX przyjmuje przesyłkę w Chinach kontynentalnych i wysyła ją samolotem; po lądowaniu jego agent przeprowadza odprawę importową, a doręczeniem zajmuje się lokalna sieć. Ani strona, ani wyszukiwarka CTTYEX nie podają nazwy tej sieci (`logistics carrier`) ani drugiego numeru śledzenia — do samego końca śledzisz numer `CTTY…CN`. CTTYEX nie publikuje terminów doręczenia, zasad awizowania ani czasu przechowywania: kieruj się szacunkiem ze sklepu, a w razie awiza od lokalnego kuriera zobacz [nieudane doręczenie](guide:missed-delivery).

## Paczka CTTYEX stoi w miejscu, jest na cle lub nie dociera

**Tylko `Order information received`.** CTTYEX ma dane zamówienia, ale nie ma jeszcze samej paczki, nawet jeśli w nagłówku widnieje `运输途中`. Gdy przez kilka dni nic nie przybywa, poproś sprzedawcę o potwierdzenie nadania.

**Cisza po `Departure from the airport of origin domestic` lub przez cztery dni.** Między wylotem a lądowaniem lub odprawą w Twoim kraju nie pojawiają się pośrednie wpisy; po czterech dniach w drodze bez nowego skanu Peek pokazuje „Brak aktualizacji od czterech dni” ([dlaczego śledzenie się nie aktualizuje](guide:tracking-not-updating), [śledzenie paczek z Chin](guide:tracking-from-china)).

**Zatrzymana na `Import customs clearance`.** Wyszukiwarka nie podaje kwot ani nazwy lokalnego doręczyciela, więc nie potwierdzi SMS-a ani e-maila z żądaniem dopłaty za paczkę CTTYEX. Najpierw przeczytaj [fałszywe SMS-y o paczkach](guide:delivery-scam-texts), a o opłatach sprawdź [paczka zatrzymana przez urząd celny](guide:customs).

**`查询不到`, zagubiona lub zwrócona.** Zweryfikuj numer u sprzedawcy i wpisz go wielkimi literami (po dwóch miesiącach wyszukiwarka również usuwa przesyłki). Na stronie CTTYEX nie ma procedury reklamacyjnej dla odbiorców — o zwrot pieniędzy lub ponowną wysyłkę zwróć się do sprzedawcy albo platformy zakupowej ([zagubiona paczka](guide:lost-parcel), [zwrot do nadawcy](guide:returned-to-sender)).

## Kontakt z CTTYEX

Chińskojęzyczna [podstrona 联系我们 (kontakt) CTTYEX](https://www.cttyvip888.com/assets/html/contact.html) podaje adres w poddzielnicy Fuyong, dzielnicy Bao'an w Shenzhen w Chinach kontynentalnych (`深圳市宝安区福永街道怀德新村8巷12号一层`), oraz adres e-mail (`cttywlvip888@163.com`) — bez numeru telefonu, czatu, formularza i obsługi odbiorców.

**Uwaga na fałszywe SMS-y.** Oficjalne domeny CTTYEX to `www.cttyvip888.com` oraz `cttyvip888.com` ([fałszywe SMS-y o paczkach](guide:delivery-scam-texts)). W sprawie zamówienia pisz do sprzedawcy.

## Śledzenie przesyłki CTTYEX w Peek

[Peek](/) rozpoznaje CTTYEX od razu po samym numerze `CTTY…CN` („Wykryty przewoźnik”), nieużywanym przez żadnego innego przewoźnika w katalogu, albo po linku z `cttyvip888.com` zawierającym numer po `nos=` (przy ręcznym wyborze dla ciągu spoza 8–40 liter i cyfr zawierającego co najmniej jedną cyfrę informuje, że przewoźnik „nie wydaje takich numerów”).

Peek pobiera z wyszukiwarki CTTYEX status, skany oraz kraj docelowy: gdy zagraniczny wiersz otwiera nazwa miasta (lub miasta i stanu), przenosi ją do pola miejsca bez kodu pocztowego, usuwa duplikaty, numery telefonów i nazwisko odbiorcy oraz przelicza każdą godzinę z czasu pekińskiego (`UTC+8`) na strefę Twojego urządzenia („Godziny w Twojej strefie czasowej: …”), dopasowując daty do Twojego dnia. O statusie decyduje najnowszy rozpoznany skan (podsumowanie tylko wtedy, gdy żaden nie ma przypisanego etapu); wobec braku prognozy dostawy, wagi i drugiego przewoźnika Peek śledzi paczkę pod numerem CTTY do samego doręczenia.

Peek sprawdza status nawet co 10 minut przy otwartej paczce lub w dniu dostawy z włączonymi powiadomieniami; poza tym i po 12 godzinach na etapie „Zarejestrowana” / „Przyjęta przez przewoźnika” lub dwóch spokojnych dniach — co godzinę („Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”). Peek nie skontaktuje się za Ciebie z CTTYEX ani ze sprzedawcą.

## Najczęstsze pytania o śledzenie CTTYEX

### Czym jest CTTYEX?

To *Shenzhen Chengtai Tongyun International Logistics* (`深圳诚泰通运国际物流`), spedytor z Shenzhen w Chinach kontynentalnych obsługujący międzynarodowe paczki e-commerce, ze stroną `cttyvip888.com` i numerami zaczynającymi się od `CTTY` i kończącymi na `CN`.

### Gdzie śledzić numer CTTY…CN?

Przez zakładkę `物流查询` na [cttyvip888.com](https://www.cttyvip888.com/assets/html/search.html) (wpisując numer wielkimi literami bez spacji) albo bezpośrednio w aplikacji Peek.

### Czy godziny skanów CTTYEX są w mojej strefie czasowej?

Nie. Wszystkie godziny w wyszukiwarce CTTYEX to czas pekiński (`UTC+8`), również dla skanów wykonanych w Europie czy Ameryce — wieczorny skan lokalny potrafi więc nosić datę następnego dnia. Peek przelicza je na strefę czasową Twojego urządzenia.

### Dlaczego wyszukiwarka CTTYEX wyświetla `查询不到`?

Po chińsku oznacza to *nie znaleziono*: żaden wpis nie pasuje do podanego numeru. Sprawdź literówki, użyj wielkich liter i skontaktuj się ze sprzedawcą; paczki nadane ponad dwa miesiące temu także znikają z wyszukiwarki.

### Z kim kontaktować się w sprawie zaginionej paczki CTTYEX?

Najpierw ze sprzedawcą, który ma umowę z CTTYEX; chińska strona CTTYEX zawiera jedynie adres w Shenzhen i e-mail bez działu obsługi odbiorców.

:::sources
- [CTTYEX: 深圳诚泰通运国际物流 (strona główna)](https://www.cttyvip888.com/) – nazwa firmy, usługi i dane kontaktowe w stopce
- [CTTYEX: 关于我们 (o nas)](https://www.cttyvip888.com/assets/html/about_us.html) – założenie w 2011 r., ponad 200 krajów, przeszło 6 oddziałów w Chinach kontynentalnych, platformy i loty czarterowe
- [CTTYEX: 物流查询 (wyszukiwanie)](https://www.cttyvip888.com/assets/html/search.html) – jeden numer w wierszu (litery i cyfry) i przycisk 提交
- [CTTYEX: 快件跟踪 (śledzenie)](https://www.cttyvip888.com/index/tracknumber/track) – wyszukiwarka paczek z ostatnich 2 miesięcy, kolumny celu i statusu
- [CTTYEX: 联系我们 (kontakt)](https://www.cttyvip888.com/assets/html/contact.html) – adres w Fuyong (Bao'an, Shenzhen, Chiny kontynentalne) i e-mail cttywlvip888@163.com, bez telefonu i formularza
- [Universal Parcel Scraper: CTTYEX](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/cttyex) – układ CTTY…CN, dopasowanie wielkimi literami, 23 statusy i podsumowania, czas pekiński i odczyt miejscowości
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – odróżnienie od China Post i prezentacja w Peek
:::
