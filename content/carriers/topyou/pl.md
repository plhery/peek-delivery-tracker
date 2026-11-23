---
title: TopYou śledzenie przesyłki: numer TYZR, statusy
description: Śledź przesyłkę TopYou (通邮) z Chin: jak wygląda numer TYZR…YQ, co oznaczają angielskie statusy, kto doręcza paczkę w Polsce oraz jak wyglądają opłaty celne.
slug: topyou-sledzenie
published: 2026-11-17
updated: 2026-11-20
---

TopYou (通邮, Tongyou, także 通邮集团 lub SZTY56) to spółka 深圳市通邮物流科技（集团）股份有限公司 – międzynarodowy operator logistyczny z Shenzhen w Chinach, wysyłający zamówienia internetowe na własnych liniach międzynarodowych i pocztowych. Jeśli chiński sklep nadał twoje zamówienie przez TopYou, numer zaczyna się od TYZ i kończy na YQ: wklej go w pole powyżej. Historia TopYou prowadzi od magazynu w Chinach przez lot i odprawę celną, po czym doręczenie przejmuje lokalny przewoźnik.

## Numer śledzenia TopYou: jak wygląda

Numery przesyłek TopYou składają się z liter TYZR, jednej litery, dziesięciu cyfr i końcówki YQ – razem 17 znaków. Część przesyłek ma inną literę w miejscu R. Z wymyślonymi cyframi:

| Numer | Co oznacza |
| --- | --- |
| `TYZRA0000000017YQ` | TYZR, jedna litera, dziesięć cyfr i YQ: numer przesyłki TopYou. |
| `TYZRA000000017YQ` | Tylko dziewięć cyfr: to nie jest układ TopYou. Sprawdź, czy nie brakuje jednej cyfry. |
| `JXCAB0000000017YQ` | Też kończy się na YQ, jak u innych chińskich spedytorów, ale zaczyna od JXC: to numer Juxi Express (który Peek sprawdza najpierw w [DHLink](carrier:dhlink), gdy po JXC stoją litery YI, ZO, US lub ZI), a nie TopYou. |

W Peek nie przeszkadzają spacje ani małe litery. Natomiast wyszukiwarka TopYou pod adresem [track.szty56.com](https://track.szty56.com/) rozróżnia wielkość liter (przyjmuje do 20 numerów naraz, po jednym w wierszu) – tam wpisuj numer wielkimi literami. Peek nie odczytuje linków skopiowanych z tamtej strony, więc wklej sam numer. Jeśli nie widzisz go przy zamówieniu, sprawdź poradnik [jak znaleźć numer śledzenia przesyłki](guide:find-tracking-number).

**Drugi numer.** Dane TopYou mogą zawierać także własny numer przewoźnika, który doręcza paczkę na miejscu – jest on widoczny również w wyszukiwarce TopYou.

## Statusy śledzenia TopYou: co oznacza każdy z nich

TopYou zapisuje swoje skany po angielsku, a Peek pokazuje je w oryginalnym brzmieniu. W kolejności, w jakiej otrzymuje je paczka:

| TopYou pisze | Co to oznacza dla ciebie | Peek pokazuje |
| --- | --- | --- |
| `Parcel Data Received` | Sklep przesłał dane elektroniczne, ale magazyn TopYou nie ma jeszcze fizycznej paczki. | „Zarejestrowana” |
| `Arrived at the first mile sorting center` | Pierwszy fizyczny skan: magazyn TopYou w Chinach przyjął przesyłkę. | „Przyjęta przez przewoźnika” |
| `Departed from the first mile sorting center` | Paczka wyjechała z pierwszego magazynu w Chinach. | „W transporcie” |
| `Arrived at airport of the original country` | Dotarła na lotnisko wylotu w Chinach przed rejsem. | „W transporcie” |
| `Departed from Airport to destination country` | Samolot wyleciał w stronę kraju docelowego. | „W transporcie” |
| `Arrived at destination airport` | Wylądowała na lotnisku w kraju docelowym. | „W transporcie” |
| `Departed from customs` | Urząd celny w kraju docelowym zwolnił przesyłkę. Na stronie TopYou wiersz kończy się urwanym słowem `of` bez nazwy kraju – Peek usuwa ten przyimek. | „W transporcie” |

**Nowsze numery.** W nowszych przesyłkach TopYou opisuje te etapy innymi zdaniami:

| TopYou pisze | Co to oznacza dla ciebie | Peek pokazuje |
| --- | --- | --- |
| `Carrier received the shipment` | TopYou przyjął paczkę w Chinach: to pierwszy fizyczny skan. | „Przyjęta przez przewoźnika” |
| `Shipment arrived at a carrier facility` | Paczka ruszyła dalej z pierwszego magazynu TopYou – nie oznacza to ponownego nadania. | „W transporcie” |
| `Shipment started the customs clearance process at origin` | Rutynowa odprawa eksportowa w Chinach przed lotem: urząd nie zatrzymuje paczki i to jeszcze nie polska odprawa celna. | „W transporcie” |
| `Shipment arrived in the destination country` | Przesyłka dotarła do kraju docelowego. | „W transporcie” |
| `Shipment started the customs clearance process at destination` | Rozpoczęła się odprawa importowa w kraju docelowym. | „W urzędzie celnym” |
| `Shipment completed the customs clearance process at destination` | Odprawa celna zakończona – paczka jedzie do firmy doręczającej. | „W transporcie” |
| `Shipment is out for delivery` | Mimo słów po angielsku paczka nie jest jeszcze u kuriera w drodze pod drzwi: to przekazanie partnerowi lokalnemu (np. GOFO we Francji i we Włoszech albo [iMile](carrier:imile) w Niemczech) na kilka dni lub godzin przed faktycznym wyjazdem kuriera. | „W transporcie” |

**Linie doręczane przez pocztę kraju docelowego.** Na liniach pocztowych TopYou używa jeszcze innego zestawu komunikatów aż do odprawy celnej:

| TopYou pisze | Co to oznacza dla ciebie | Peek pokazuje |
| --- | --- | --- |
| `Accepted by carrier` | TopYou ma paczkę w Chinach: pierwszy skan. | „Przyjęta przez przewoźnika” |
| `Inbound in sorting center` | Zarejestrowana w pierwszej sortowni TopYou kilka minut później; jeszcze stamtąd nie wyjechała. | „Przyjęta przez przewoźnika” |
| `Outbound in sorting center` | Wyjazd z pierwszej sortowni. Na stronie TopYou dopisywana jest końcówka `, Lastmile Number:` z numerem pocztowym doręczenia; Peek usuwa ten dopisek z treści wiersza. | „W transporcie” |
| `Received by line-haul`, `Hand over to airline` | Przejęta do transportu głównego, a następnie przekazana linii lotniczej. | „W transporcie” |
| `Airline arrived at transit country or district.`, `Airline departed from transit or district country.` | Międzylądowanie w kraju tranzytowym po drodze do celu. | „W transporcie” |
| `Airline arrived at destination country`, `Arrive at destination country` | Przesyłka dotarła do kraju docelowego. | „W transporcie” |
| `Export clearance success` | Chiński urząd celny wypuścił przesyłkę – nic jej nie wstrzymuje. | „W transporcie” |
| `Import clearance success` | Urząd celny w kraju docelowym zwolnił paczkę dla operatora pocztowego, który może jeszcze pokazać własne statusy celne. | „W transporcie” |
| `Customs Clearance Completed Successfully` | Odprawa importowa w kraju docelowym zakończona kilka minut po przekazaniu lokalnemu przewoźnikowi, który ma już paczkę. | „W transporcie” |
| `The Shipment Is In Transit` | Własny komunikat TopYou po odprawie celnej w kraju docelowym: przesyłka jest w dalszej drodze. | „W transporcie” |

**Dalsze skany.** Po przekazaniu strona TopYou może dalej wyświetlać wpisy przewoźnika lokalnego. Jeżeli punkt odbioru prześle powiadomienie zaczynające się od `Arrived, The parcel has been delivered to 【】. Please collect your parcel promptly at 【】 using your pickup code.`, paczka czeka tam na odbiór z kodem odbioru („Do odbioru”).

## Kto doręcza paczki TopYou w Polsce

Po odprawie celnej TopYou przekazuje przesyłkę firmie doręczającej w Polsce. Karta przesyłki w TopYou może podawać numer nadawczy tej firmy, ale nigdy nie podaje jej nazwy, a nazwa linii TopYou też jej nie zdradza – linia potrafi nosić handlową nazwę innego przewoźnika niż ten, który faktycznie doręcza paczkę. Na linii pocztowej numer poczty pojawia się także na końcu `Outbound in sorting center` na stronie TopYou, po `Lastmile Number:`. Dlatego zapytaj sprzedawcę, jaka firma doręcza twoje zamówienie w Polsce i pod jakim numerem – od niej zależą próby doręczenia, awiza i punkty odbioru.

Jeśli ten drugi numer jest prawidłowym [międzynarodowym numerem pocztowym](guide:tracking-number-formats) (dwie litery, dziewięć cyfr z poprawną cyfrą kontrolną i dwie litery), a krajem docelowym w danych TopYou jest Polska, Peek odpytuje [Pocztę Polską](carrier:poczta-polska). Samo zapytanie nie przesądza jeszcze, że to Poczta Polska doręcza przesyłkę: Peek przełącza na nią śledzenie dopiero wtedy, gdy pokaże ona świeże skany (więcej niżej). Zobacz też [nieudana próba doręczenia i awizo](guide:missed-delivery) oraz [nieodebrana paczka](guide:uncollected-parcel).

**Cło i opłaty w Polsce.** Według informacji Komisji Europejskiej i Poczty Polskiej od 1 lipca 2026 r. przesyłki spoza Unii Europejskiej o wartości do 150 euro objęte są cłem w wysokości 3 euro za każdy rodzaj produktu w paczce, a od 1 listopada 2026 r. dla towarów kupowanych na odległość spoza UE obowiązuje również unijna opłata manipulacyjna 2 euro za każdą pozycję towarową (niezależnie od wartości zamówienia), przekazywana Krajowej Administracji Skarbowej. Poczta Polska zaznacza, że opłacenie podatku VAT przy zakupie nie oznacza automatycznie uregulowania wszystkich należności importowych: przy odbiorze lub doręczeniu mogą dojść cło, unijna opłata manipulacyjna oraz opłata operatora za obsługę zgłoszenia celnego. Szczegóły znajdziesz w poradniku [paczka zatrzymana w urzędzie celnym](guide:customs).

## Przesyłka TopYou utknęła, nie ma skanów albo nie dociera

**Stoi na `Shipment started the customs clearance process at destination`.** Przesyłka przechodzi odprawę importową. Jeśli przewoźnik prosi o dokumenty lub dopłatę, zajrzyj do poradnika [paczka zatrzymana w urzędzie celnym](guide:customs); gdy wpis nie zmienia się przez wiele dni, powiadom sklep i sprawdź [dlaczego śledzenie się nie aktualizuje](guide:tracking-not-updating).

**Cisza po `Departed from customs`, `Shipment completed the customs clearance process at destination`, `Customs Clearance Completed Successfully` lub `Import clearance success`.** Paczkę powinien mieć już lokalny przewoźnik. Poszukaj drugiego numeru na stronie [track.szty56.com](https://track.szty56.com/) albo zapytaj sprzedawcę, kto doręcza zamówienie i pod jakim numerem. Więcej w poradniku [śledzenie paczek z Chin](guide:tracking-from-china).

**Nie znaleziono lub brak jeszcze skanów.** Wyszukiwarka TopYou odpowiada `查询不到` (nie znaleziono), gdy nie zna numeru, a przed pierwszym skanem może zwrócić `暂无轨迹信息` (brak jeszcze informacji o śledzeniu). Peek pokazuje wtedy „Oczekiwanie na przewoźnika TopYou” i sprawdza dalej; jeśli po dwóch dniach TopYou nadal nie zna numeru i żadne inne źródło go nie potwierdzi, pojawia się „Po dwóch dniach przewoźnik nadal nie zna tego numeru. Sprawdź go, wybierz innego przewoźnika lub usuń przesyłkę.” Na podstawie odpowiedzi TopYou Peek potrafi rozpoznać sytuację, w której przesyłka jest już wpisana z krajem docelowym, ale nie ma jeszcze żadnego skanu: wtedy TopYou zna numer, więc Peek nadal wyświetla „Oczekiwanie na przewoźnika TopYou” bez proszenia cię o poprawianie numeru. Jeśli od co najmniej doby widnieje wyłącznie `Parcel Data Received`, Peek informuje: „Nadawca nie przekazał jej jeszcze przewoźnikowi TopYou. Śledzenie zacznie się po przekazaniu.” – zapytaj wtedy sklep, kiedy wyśle paczkę. Zawsze porównaj numer znak po znaku z potwierdzeniem ze sklepu.

**Status doręczono, ale nic nie przyszło.** Doręczenie realizuje lokalna firma, a nie TopYou: skontaktuj się najpierw z tym przewoźnikiem, a następnie zgłoś sprawę sprzedawcy. Zobacz [przesyłka dostarczona, ale nieotrzymana](guide:delivered-not-received).

**Zaginiona albo zwrócona.** Umowę z TopYou zawarł sklep, więc to od sprzedawcy lub platformy zakupowej zażądaj zwrotu pieniędzy albo ponownego wysłania towaru. Sprawdź [zagubiona paczka](guide:lost-parcel) i [zwrot przesyłki do nadawcy](guide:returned-to-sender).

## Kontakt z TopYou: do kogo się zwrócić

Na dzień 16 listopada 2026 r. strona TopYou, [szty56.com](https://www.szty56.com/), podaje adres siedziby w Shenzhen, numer telefonu +86 755 8181 9898 (`0755-81819898` w Chinach), adres e-mail `2543370662@qq.com` oraz godziny pracy w dni robocze 9:00–18:00 dla nadawców biznesowych – bez obsługi adresatów za granicą. W sprawie zamówienia kontaktuj się ze sklepem (a gdy nie odpowiada – z platformą zakupową); jeśli paczkę przejęła już Poczta Polska, numery infolinii znajdziesz na stronie [Poczty Polskiej](carrier:poczta-polska).

## Śledzenie przesyłki TopYou w Peek

[Peek](/) rozpoznaje numer TopYou po układzie znaków (TYZR, jedna litera, dziesięć cyfr i YQ), którego nie używa żaden inny przewoźnik w katalogu. Jeśli w miejscu litery R stoi inna litera, kształt jest jedynie słabą wskazówką: Peek pokazuje „Sprawdzanie usług śledzenia…” i pyta TopYou, czy rozpoznaje ten numer. Peek odpytuje TopYou wyłącznie dla tych dwóch układów. Gdy masz tylko numer lokalnego przewoźnika, wklej go mimo to: Peek śledzi ponad 4500 przewoźników, a w razie potrzeby wybierzesz właściwego przyciskiem „Zmień”.

Peek pobiera dane ze strony TopYou i pokazuje aktualny status, każdy skan w brzmieniu TopYou lub firmy lokalnej, kraj docelowy oraz – w polu „Waga” – wagę zapisaną przez TopYou. Z własnych skanów TopYou Peek usuwa miejsce, gdy jest nim wyłącznie nazwa kraju docelowego, dzięki czemu skan z chińskiego lotniska nie wygląda jak wykonany w Polsce. Gdy podsumowanie karty TopYou oznacza paczkę jako doręczoną lub zwróconą, Peek pokazuje „Dostarczona” albo „Zwrócona nadawcy”, nawet jeśli ostatni wiersz brzmi inaczej. Własne skany TopYou mają czas pekiński (UTC+8); w skanach kopiowanych od lokalnego przewoźnika strona TopYou nie zawsze podaje właściwą strefę czasową, więc Peek sam wylicza poprawną godzinę i pokazuje wszystko w strefie twojego urządzenia. Przewidywanej daty doręczenia nie ma, bo TopYou jej nie udostępnia.

Gdy dane TopYou zawierają numer firmy doręczającej, Peek sprawdza go w Poczcie Polskiej (dla prawidłowego międzynarodowego numeru pocztowego z Polską jako krajem docelowym) albo u jedynego przewoźnika, do którego taki układ numeru należy na pewno, o ile Peek obsługuje jego śledzenie. Gdy tylko ten przewoźnik pokaże skan co najmniej tak świeży jak ostatni wpis TopYou, Peek śledzi paczkę u niego i wyświetla oba numery, zachowując wcześniejsze skany TopYou. Numer niejednoznaczny (np. same 20 cyfr używane przez kilka firm) nie jest przełączany automatycznie – Peek zostaje przy stronie TopYou.

Gdy masz przesyłkę otwartą albo w dniu doręczenia z włączonymi powiadomieniami, Peek sprawdza ją nawet co 10 minut; w pozostałych przypadkach co godzinę. Raz na godzinę sprawdza ją też, gdy przestają spływać nowości: po 12 godzinach na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika” albo po dwóch dniach na pozostałych etapach. Gdy paczka ma status „W doręczeniu”, na tych samych warunkach sprawdza nawet co 2 minuty (co 5, gdy sprawdzenie otwiera przeglądarkę) od 6:00 czasu lokalnego w tym dniu, dopóki odpowiada śledzenie TopYou; przez serwisy śledzenia przesyłek co godzinę, co 3 godziny po dniu bez nowości i nawet co 15 minut w tym dniu, dopóki masz przesyłkę otwartą lub włączone powiadomienia. Gdy doręczenie przejmuje inny przewoźnik, Peek sprawdza paczkę w tempie tego, który ją doręcza. Dostępne powiadomienia to „Każdy skan”, „Ważne etapy” oraz „Tylko doręczenie”: „Ważne etapy” powiadomią cię o odprawie importowej (`Shipment started the customs clearance process at destination`), przejściu w stan „W doręczeniu” i samym doręczeniu, ale pominą odprawę eksportową w Chinach, lot, wyjście z cła oraz `Shipment is out for delivery`. Peek nie skontaktuje się za ciebie z TopYou, sklepem ani Pocztą Polską.

## Najczęstsze pytania o śledzenie TopYou

### Czym jest TopYou (通邮)?

To spółka 深圳市通邮物流科技（集团）股份有限公司 (także 通邮集团, Tongyou lub SZTY56) – operator logistyczny z Shenzhen działający od 2016 r., posiadający oddziały w innych miastach Chin i obsługujący ponad 800 000 zamówień dziennie w kanałach pocztowych, na liniach własnych oraz w ekspresie międzynarodowym. Jego numery mają postać TYZR, jedna litera, dziesięć cyfr i YQ (czasem z inną literą zamiast R).

### Dlaczego TopYou pokazuje Polskę, skoro paczka jest jeszcze w Chinach?

Ponieważ strona TopYou dopisuje kraj docelowy jako miejsce przy własnych skanach, nawet na lotnisku wylotu w Chinach; Peek usuwa to mylące oznaczenie kraju. Patrz na treść komunikatu: `Arrived at airport of the original country` oznacza, że przesyłka wciąż czeka na samolot w Chinach.

### Kto doręcza paczki TopYou w Polsce?

Lokalny przewoźnik w Polsce. Dane TopYou mogą podawać jego numer, ale nie nazwę – dopytaj sprzedawcę, kto doręcza zamówienie. Jeśli jest to prawidłowy międzynarodowy numer pocztowy, Peek sprawdza go w Poczcie Polskiej, choć przełącza śledzenie dopiero po pojawieniu się jej własnych skanów.

### Czy za paczkę TopYou trzeba zapłacić cło i opłatę manipulacyjną?

Od 1 lipca 2026 r. zakupy internetowe spoza UE do 150 euro podlegają cłu 3 euro za każdy rodzaj produktu, a od 1 listopada 2026 r. dochodzi unijna opłata manipulacyjna 2 euro za pozycję towarową przy sprzedaży na odległość (także powyżej 150 euro), obok VAT i ewentualnej opłaty operatora za obsługę zgłoszenia celnego.

### Do kogo zgłosić reklamację, gdy paczka TopYou nie dochodzi?

Do sklepu, w którym zrobiono zakupy (lub do platformy zakupowej): to sprzedawca zlecił wysyłkę w TopYou. Jeśli przesyłkę przyjęła już Poczta Polska, w sprawie samego doręczenia skontaktuj się z Pocztą Polską.

:::sources
- [TopYou: 通邮集团 (strona główna, po chińsku)](https://www.szty56.com/) – założenie w 2016 r. w Shenzhen, telefon 0755-81819898, e-mail 2543370662@qq.com, godziny w dni robocze 9:00–18:00, obsługiwane platformy
- [TopYou: 关于我们 (o firmie, po chińsku)](https://www.szty56.com/view?path=about/about) – pełna nazwa spółki, oddziały w Chinach, ponad 800 000 zamówień dziennie i zakres usług
- [TopYou: 轨迹查询 (wyszukiwarka przesyłek)](https://track.szty56.com/) – wyszukiwarka do 20 numerów naraz
- [Poczta Polska: Zmiany w opłatach dla przesyłek spoza Unii Europejskiej od 1 lipca 2026 r.](https://www.poczta-polska.pl/news/zmiany-w-oplatach-dla-przesylek-spoza-unii-europejskiej-od-1-lipca-2026-r/) – cło 3 euro za rodzaj produktu w przesyłkach do 150 euro i pobór opłat przy doręczeniu lub przed odbiorem
- [Poczta Polska: Nowe przepisy Unii Europejskiej. Tak działa nowa opłata za przesyłki spoza UE](https://media.poczta-polska.pl/releases/nowe-przepisy-unii-europejskiej-tak-dziala-nowa-oplata-za-przesylki-spoza-ue) – opłata manipulacyjna 2 euro za pozycję towarową od 1 listopada 2026 r. przekazywana KAS, odrębna od cła, VAT i opłaty za zgłoszenie celne
- [Komisja Europejska: EU Customs Reform (po angielsku)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – cło 3 euro od 1 lipca 2026 r. oraz opłata manipulacyjna 2 euro od 1 listopada 2026 r.
- [Komisja Europejska: Guidance on the 3 EUR customs duty and Union handling fee (PDF, po angielsku)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 euro za pozycję w sprzedaży na odległość niezależnie od wartości
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formaty TYZR…YQ i TYZ…YQ, rozróżnianie wielkości liter w wyszukiwarce TopYou, teksty i etapy skanów (w tym `The Shipment Is In Transit` i powiadomienia z punktu odbioru), usuwanie `Lastmile Number:` oraz końcowego `of`, kraj docelowy przy skanach własnych, czas pekiński, przesyłki zapowiedziane bez skanów, drugi numer i przekazanie do Poczty Polskiej
:::
