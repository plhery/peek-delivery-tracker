---
title: Śledzenie LinHan International: numery LHGJ i statusy
description: Śledź paczkę LinHan International z Shenzhen: format LHGJ, znaczenie skanów i dlaczego godziny podawane są według czasu pekińskiego.
slug: sledzenie-linhan
published: 2026-11-22
updated: 2026-11-22
---

LinHan International (深圳市林瀚国际物流有限公司, Shenzhen LinHan International Logistics Co., Ltd.) to spedytor z Shenzhen w Chinach, który wysyła drogą lotniczą paczki sprzedawców internetowych z Chin i przekazuje je agentowi w kraju odbiorcy do odprawy celnej i doręczenia. Numery zaczynają się od `LHGJ`: wpisz swój na stronie śledzenia LinHan, [linhan56.com/index/tracknumber/track](https://www.linhan56.com/index/tracknumber/track), lub w polu powyżej.

## Numer śledzenia LinHan

LinHan nie publikuje oficjalnego formatu. Jego listy przewozowe składają się z `LHGJ`, ośmiu cyfr i `CD` (z fikcyjnymi cyframi):

| Numer | Co oznacza |
| --- | --- |
| `LHGJ48291037CD` | `LHGJ`, osiem cyfr i `CD`: numer przesyłki LinHan International. |
| `LHGJ4829103CD` | Tylko siedem cyfr: nie pasuje do formatu LinHan. Sprawdź, czy nie brakuje cyfry. |

Małe litery i spacje nie mają znaczenia po wklejeniu numeru do Peek.

**Gdzie go znaleźć.** LinHan obsługuje sprzedawców: strona główna oferuje im cenniki na żywo i rezerwację tras, a odbiorcom jedynie wyszukiwarkę śledzenia. Numer pochodzi ze sklepu, w którym zamówiłeś; zobacz [jak znaleźć numer śledzenia](guide:find-tracking-number).

**W śledzeniu LinHan.** Narzędzie przyjmuje do 20 numerów naraz (oddzielonych przecinkami lub nowymi liniami) i pokazuje tylko paczki z ostatnich dwóch miesięcy.

## Statusy śledzenia LinHan

LinHan nie publikuje listy statusów. Strona jest po chińsku, ale same skany są po angielsku, od najnowszego. Nad nimi wiersz podsumowania podaje kraj docelowy i status po chińsku:

| LinHan podaje | Co oznacza dla ciebie |
| --- | --- |
| `运输途中` | „W transporcie”: przesyłka jest w drodze. |
| `成功签收` | „Pokwitowana”: dostarczona. |
| `查询不到` | „Nie znaleziono”: system nie ma paczki pod tym numerem i nie pojawiają się żadne skany. |

Skany własnych etapów LinHan w Chinach, w typowej kolejności:

| LinHan podaje | Co oznacza dla ciebie |
| --- | --- |
| `Order information received` | Sprzedawca przekazał LinHan dane zamówienia. LinHan nie ma jeszcze paczki. |
| `Arrive at the cross border receiving point` | LinHan przyjął paczkę w transgranicznym punkcie odbioru w Chinach. `Arrive at the receiving point` oznacza to samo. |
| `Goods leave the operation center` | Paczka opuściła centrum operacyjne LinHan. |
| `Arrival at domestic internationality airport` | Na lotnisku wylotowym w Chinach. |
| `Departure from the airport of origin domestic` | Lot opuścił Chiny. |

Następnie pojawiają się etapy w twoim kraju, gdzie przesyłkę przejmują agent LinHan i sieć doręczająca:

| LinHan podaje | Co oznacza dla ciebie |
| --- | --- |
| `Item arrives at destination airport` | Wylądowała w twoim kraju. `Arrive at the destination international airport` oznacza to samo. |
| `local carrier Agent Import customs clearance` | Agent LinHan odprawia paczkę w cle importowym; nie jest jeszcze zwolniona. `Import customs clearance`, po nazwie miasta portowego, to ten sam krok. |
| `transfer to local acting company International self-operation team` | Przekazana agentowi w twoim kraju. |
| `Internationaliy Package received at processing center` | Oryginalna pisownia LinHan. Pierwszy skan sieci doręczającej w centrum przetwarzania. To nie nowe nadanie: LinHan przyjął paczkę w Chinach. |
| `Leave the processing center and transport to … processing center` | W drodze między dwoma centrami przetwarzania, z nazwą kolejnego miasta w miejscu „…”. `Leave the … processing center` wskazuje opuszczone centrum. |
| `Arrive at the sorting and distribution center` | W sortowni, której miasto widnieje po „Arrive”. `Leaving the sorting center` oznacza wyjazd z sortowni. |
| `Arrive at the last mile sorting and distribution center` | W sortowni sieci doręczającej, po nazwie miasta, stanu i kodzie pocztowym. |
| `Arrived at logistics carrier Facility In Transit to Next Facility` | Skan sieci doręczającej, z jej nazwą zastąpioną przez „logistics carrier”. |
| `Accepted at logistics carrier Regional Destination Facility` | Skan tej sieci blisko ciebie, po nazwie miasta, stanu i kodzie pocztowym. „Accepted” nie oznacza nowego startu: przesyłka nadal jest w transporcie. |
| `package is on the way` | Rusza dalej z lokalnej sortowni, po nazwie miasta. Nie oznacza jeszcze doręczenia dzisiaj. |
| `Delivered` | Dostarczona. |

Wiele wierszy w twoim kraju zaczyna się od miejscowości (w USA od miasta, stanu i kodu ZIP), a wpis doręczenia zawiera też twój kod pocztowy: miasto, kod pocztowy, Delivered. Ten sam wiersz potrafi pojawić się dwa razy.

**Godziny według czasu pekińskiego.** System LinHan zapisuje każdą godzinę w czasie pekińskim (UTC+8), także dla skanów w twoim kraju. Ponieważ w Pekinie data zmienia się o 16:00 UTC, skan między tą godziną a twoją północą ma już datę kolejnego dnia: w Polsce i Niemczech od 17:00 zimą i 18:00 latem, a w Nowym Jorku od 11:00 zimą i w południe latem.

## Kto doręcza paczki LinHan

Strona główna LinHan wymienia łańcuch usług: odbiór, magazynowanie i sortowanie, odprawę celną w Chinach, fracht lotniczy, zgłoszenie celne za granicą, magazyny zagraniczne i doręczenie (揽货, 仓储分拣, 国内通关, 空运, 海外报关, 海外仓, 海外派送). Nie wymienia tras ani kierunków; paczki obserwowane w systemie szły do USA i Niemiec.

W twoim kraju paczkę przejmuje agent. System LinHan nazywa go „local carrier Agent” i „local acting company”, a sieć doręczającą „logistics carrier”, nie ujawniając nazwy firmy ani drugiego numeru śledzenia. Historia biegnie jednak aż do doręczenia, więc strona LinHan pozostaje właściwym miejscem śledzenia.

LinHan nie publikuje czasów dostawy ani zasad dotyczących prób doręczenia, punktów odbioru czy przechowywania. Jeśli awizo lub wiadomość lokalnego przewoźnika poda jego nazwę, obowiązują jego zasady: zobacz [nieudane doręczenie](guide:missed-delivery).

## Paczka LinHan nie dociera lub śledzenie stoi

**Tylko `Order information received`.** Sprzedawca zarejestrował wysyłkę, ale paczka nie dotarła jeszcze do LinHan. Jeśli przez kilka dni nic nie dochodzi, zapytaj sprzedawcę, czy ją wysłał.

**Brak wpisów po locie.** Po `Departure from the airport of origin domestic` kolejny wpis pojawia się po wylądowaniu lub w urzędzie celnym w twoim kraju. Więcej w [śledzenie z Chin](guide:tracking-from-china) i [dlaczego śledzenie przestaje się aktualizować](guide:tracking-not-updating).

**Na cle.** System LinHan nie informuje, czy masz coś do zapłaty, i nie podaje nazwy lokalnego przewoźnika, więc SMS z prośbą o dopłatę trudno zweryfikować: zajrzyj do [fałszywych SMS-ów o dostawie](guide:delivery-scam-texts) przed zapłatą oraz [przesyłki zatrzymane na cle](guide:customs).

**`查询不到`.** System nie zna tego numeru: potwierdź go u sprzedawcy. Nie pokazuje też paczek starszych niż dwa miesiące.

**Dostarczona, ale jej nie ma.** Jeśli ostatni wpis to `Delivered`, przeczytaj [dostarczona, ale nieotrzymana](guide:delivered-not-received) i powiadom sprzedawcę. LinHan nie pokazuje zdjęcia z doręczenia.

**Zaginiona, uszkodzona lub zwrócona.** LinHan nie publikuje procedury reklamacji, odszkodowań ani zwrotów. Ponieważ to sprzedawca zamówił przewóz, poproś go lub platformę o zwrot pieniędzy albo ponowną wysyłkę. Zobacz [zagubiona paczka](guide:lost-parcel).

## Kontakt z LinHan International

Strona główna LinHan, wyłącznie po chińsku, kończy się sekcją 联系我们 (Kontakt): adresem e-mail i adresem placówki w Pinghu, dzielnica Longgang, Shenzhen. Nie ma numeru telefonu, czatu, formularza ani wsparcia dla odbiorców — w sprawach zamówienia skontaktuj się ze sprzedawcą.

## Śledzenie paczki LinHan w Peek

[Peek](/) rozpoznaje numer LinHan po samym kształcie (`LHGJ`, osiem cyfr i `CD`), unikalnym wśród ponad 4500 przewoźników w katalogu, i od razu pokazuje „Wykryty przewoźnik”. Można też wkleić link śledzenia LinHan z parametrem `nos=` i numerem.

Peek odczytuje stronę LinHan i pokazuje status, każdy skan z miastem (gdy podane), kraj docelowy oraz odbiorcę, który pokwitował doręczenie, jeśli linia doręczenia go zawiera. Wydziela miasto z treści opisu jako lokalizację i pomija kody pocztowe, dzięki czemu doręczenie wygląda jako `Delivered` z miastem. Zduplikowane wiersze wyświetlają się raz, a skany zachowują angielską pisownię LinHan, włącznie z `Internationaliy`. Peek czyta każdą godzinę według czasu pekińskiego (UTC+8), tak jak zapisuje ją tracker, i przelicza na twoją strefę czasową („Godziny w twojej strefie czasowej:”).

Część wierszy klasyfikuje się inaczej, niż mogłaby sugerować treść: `Order information received` to „Zarejestrowana”; `Internationaliy Package received at processing center` i `Accepted at logistics carrier Regional Destination Facility` pozostają w „W transporcie”, bez nowego nadania; oba wpisy celne trafiają do „W urzędzie celnym”; `package is on the way` pozostaje w „W transporcie”, bo nie oznacza doręczenia dziś.

LinHan nie podaje przewidywanego dnia doręczenia, wagi, nazwy usługi, lokalnego przewoźnika ani drugiego numeru, więc Peek ich nie wyświetla i śledzi numer `LHGJ` do końca bez przełączania na innego przewoźnika.

Peek sprawdza status do co 10 minut, a w dniu przejścia paczki do etapu „W doręczeniu” co 2 minuty od 6:00 według zegara ostatniego skanu (czasu pekińskiego), gdy masz paczkę otwartą w Peek lub włączone powiadomienia i strona LinHan odpowiada. Po dwóch dniach bez zmian (po 12 godzinach na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika”) sprawdza co godzinę. W powiadomieniach możesz wybrać „Wszystkie aktualizacje”, „Tylko ważne” albo „Tylko w dniu dostawy”. Po czterech dniach bez skanu w transporcie, poza odprawą celną i zgłoszonym problemem, Peek pokazuje „Brak aktualizacji od …” i przypomina „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika LinHan International.” Peek nie skontaktuje się za ciebie z LinHan ani ze sprzedawcą.

## Pytania o śledzenie LinHan

### Czym jest LinHan International?

Shenzhen LinHan International Logistics (深圳市林瀚国际物流有限公司), spedytor z linhan56.com, który wysyła drogą lotniczą paczki sprzedawców z Chin do agenta zajmującego się odprawą celną i doręczeniem.

### Gdzie mogę śledzić numer LHGJ?

Na stronie śledzenia LinHan, [linhan56.com/index/tracknumber/track](https://www.linhan56.com/index/tracknumber/track), lub w Peek. Strona główna LinHan zawiera też link do 17TRACK.

### Kto doręcza paczki LinHan?

Agent w twoim kraju, opisywany w systemie jako „local carrier” lub „local acting company” bez podania nazwy. Jego skany pojawiają się w śledzeniu LinHan aż do doręczenia.

### Dlaczego godziny skanów LinHan wyglądają na dzień do przodu?

Ponieważ śledzenie LinHan podaje każdą godzinę w czasie pekińskim (UTC+8), także dla skanów w twoim kraju. Skan po 16:00 UTC, gdy w Pekinie jest już następny dzień, dostaje kolejną datę. Peek przelicza godziny na strefę czasową twojego urządzenia.

### Z kim się skontaktować w sprawie zaginionej paczki LinHan?

Ze sprzedawcą, u którego zamówiłeś — to on opłacił przewóz w LinHan. Strona główna LinHan podaje tylko adres e-mail i adres w Shenzhen, bez obsługi odbiorców.

:::sources
- [LinHan International: 深圳市林瀚国际物流有限公司 (strona główna)](https://www.linhan56.com/) – nazwa firmy, transgraniczny fracht lotniczy dla nadawców, łańcuch od odbioru po doręczenie za granicą, linki do śledzenia i 17TRACK, sekcja kontaktu
- [LinHan International: 快件跟踪 (śledzenie)](https://www.linhan56.com/index/tracknumber/track) – śledzenie po chińsku, paczki z ostatnich dwóch miesięcy, do 20 numerów na wyszukiwanie
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – format LHGJ, statusy podsumowania i treści skanów, agent docelowy i anonimowa sieć, zegary w czasie pekińskim, miasta i kody pocztowe, obserwowane kierunki, co Peek pokazuje i pomija
:::
