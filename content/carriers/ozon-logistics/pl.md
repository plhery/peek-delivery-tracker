---
title: Śledzenie Ozon: numery zamówień i statusy po polsku
description: Śledź zamówienie Ozon (Ozon Rocket, Ozon Доставка): jak wyglądają numery z myślnikami, co oznaczają rosyjskie statusy po polsku i jak śledzi je Peek.
slug: sledzenie-ozon
published: 2026-11-21
updated: 2026-11-21
---

Usługa dostawy Ozon przewozi zamówienia przekazane przez sprzedawców pod drzwi odbiorcy lub do punktu odbioru. Katalog Peek przypisuje ją do Rosji, Białorusi i Kazachstanu, również pod nazwami Ozon Rocket, OZON Rocket Доставка, Ozon Доставка i Ozon Dostavka. Wklej swój numer w pole powyżej razem z myślnikami. Własna strona śledzenia Ozon to tracking.ozon.ru, a Peek odczytuje ją za ciebie w sposób opisany poniżej.

## Numer śledzenia Ozon: jak wygląda

Peek zna dwa kształty numerów Ozon i oba zapisuje się z myślnikami. Myślniki są istotne: bez nich cyfry wyglądają jak numery innych przewoźników. Przykłady ze zmyślonymi cyframi:

| Numer (zmyślony) | Co to jest |
| --- | --- |
| `48291057-3628-1` | 8 cyfr, 4 cyfry i 1 cyfra połączone myślnikami. Peek od razu rozpoznaje Ozon. |
| `4829105736-2841-2` | Ten sam schemat z 10 cyframi na początku. Peek również od razu rozpoznaje Ozon. |
| `4829105736281` | Pierwszy numer bez myślników albo ze spacjami zamiast nich. DPD France, Ukrposhta i inni przewoźnicy używają numerów 13-cyfrowych, więc same cyfry nie wskazują Ozon. Gdy dodasz go z Rosji, Białorusi lub Kazachstanu, Peek pyta Ozon wraz z innymi przewoźnikami; z innych krajów nie pyta Ozon automatycznie. Wpisz z powrotem myślniki lub wybierz Ozon ręcznie (niżej). To samo dotyczy 15 cyfr, z których korzysta m.in. FedEx. |
| `PL-482910-57362` | `PL`, 6 cyfr, a potem kolejne litery lub cyfry z myślnikami. Peek rozpoznaje Ozon. |
| `PL-482910-5736` | `PL` z dokładnie 10 cyframi pasuje też do formatu numerów [Amazon](carrier:amazon-logistics). Peek traktuje go jako numer Amazon i nie pozwala wybrać dla niego Ozon. |

Małe litery nie mają znaczenia: `pl-` działa tak samo jak `PL-`. Nie możesz znaleźć numeru? Zobacz [jak znaleźć numer przesyłki](guide:find-tracking-number) i [formaty numerów przesyłek](guide:tracking-number-formats).

## Statusy śledzenia Ozon po polsku

Kod Peek zawiera listę kroków pokazywanych przez śledzenie Ozon po rosyjsku. Peek odczytuje je i wyświetla w oryginalnym rosyjskim brzmieniu, z wyjątkiem `Заказ в работе`, `Заказ на пути к границе страны назначения` oraz `Заказ прибывает на приграничный пункт`, które pomija (zobacz „Śledzenie zamówienia Ozon w Peek”).

**W twoim kraju.** Mniej więcej w kolejności pojawiania się:

| Co podaje Ozon | Co to oznacza dla ciebie |
| --- | --- |
| `Передается в доставку` | Sprzedawca spakował zamówienie i przekazuje je do wysyłki. Ozon może jeszcze go nie mieć. |
| `Заказ принят перевозчиком.` | Przewoźnik przyjął zamówienie: jest w drodze. |
| `Заказ отправили на сортировочный терминал`, `Заказ отправлен на сортировочный терминал.` | Wysłane do sortowni. |
| `Заказ покинул сортировочный терминал.` | Opuściło sortownię. |
| `Заказ ожидает отправки в город получателя.` | Czeka na wysyłkę do twojego miasta. |
| `Заказ везут в город получателя` | W drodze do twojego miasta. |
| `Заказ везут` | W drodze. |
| `Заказ передали в курьерскую доставку` | Przekazane do dostawy kurierskiej; kurier zadzwoni, aby ustalić godzinę. |
| `Заказ в пункте выдачи` | Czeka w punkcie odbioru: możesz po nie pójść. |
| `Заказ получен в пункте выдачи.` | Odebrane w punkcie odbioru lub na poczcie: dostarczone. |
| `Заказ успешно доставлен получателю.` | Dostarczone. Taki opis pojawia się, gdy zamówienie nie szło do punktu odbioru ani na pocztę, np. przy dostawie kurierem. |
| `Заказ отменён` | Zamówienie zostało anulowane. |
| `Заказ в работе` | Zamówienie w trakcie realizacji: komunikat zastępczy na stronie Ozon, a nie etap podróży. |

**Z innego kraju.** Zamówienie wysłane z zagranicy przechodzi również etapy celne, mniej więcej w tej kolejności:

| Co podaje Ozon | Co to oznacza dla ciebie |
| --- | --- |
| `Заказ везут на таможню в стране отправления` | W drodze do urzędu celnego w kraju nadania. |
| `Заказ привезли на таможню для экспортного таможенного оформления` | Dotarło do odprawy eksportowej w tamtym kraju. |
| `Заказ покинул зону экспортного таможенного оформления` | Opuściło strefę odprawy eksportowej. |
| `Заказ на пути к границе страны назначения` | W drodze do granicy twojego kraju. |
| `Заказ прибывает на приграничный пункт` | Dociera na przejście graniczne. |
| `Заказ привезли в страну назначения` | Dotarło do twojego kraju. |
| `Заказ передан на импортное таможенное оформление.` | Przekazane do odprawy celnej przywozowej w twoim kraju. |
| `Заказ проходит импортное таможенное оформление` | Trwa odprawa celna przywozowa. |
| `Заказ выпущен импортной таможней.` | Zwolnione przez urząd celny: jedzie dalej. |
| `Импортная таможня задержала заказ` | Urząd celny zatrzymał zamówienie do dodatkowej kontroli. |

## Jak doręcza Ozon: kurier czy punkt odbioru

Kolejne wpisy pokazują sposób dostawy twojego zamówienia.

**Pod drzwi.** Po wpisie `Заказ передали в курьерскую доставку` (oznaczonym w Peek jako „W transporcie”) kurier przywozi paczkę i wcześniej dzwoni, by umówić godzinę. Ostatni krok to `Заказ успешно доставлен получателю.`

**Do punktu odbioru.** Poczekaj na `Заказ в пункте выдачи` („Do odbioru”), a potem odbierz paczkę. Po odbiorze strona Ozon pokazuje `Заказ получен в пункте выдачи.` (ten sam opis dotyczy odbioru na poczcie).

Ponieważ Peek nie mógł odczytać strony internetowej Ozon, nie podaje tu czasów dostawy, godzin otwarcia, terminów przechowywania ani zasad prób doręczenia: zapytaj o nie Ozon. Zobacz także [nieodebrana przesyłka](guide:uncollected-parcel) i [nieudane doręczenie](guide:missed-delivery).

**Z zagranicy.** Powyższe etapy celne pokazują postęp przesyłki z innego kraju. Strona nie podaje zasad ani opłat celnych, bo Peek nie mógł ich sprawdzić na oficjalnym portalu. Zobacz [przesyłki zatrzymane na cle](guide:customs).

## Zamówienie Ozon nie dociera lub śledzenie utknęło

**Jeszcze nic albo tylko `Передается в доставку`.** Sprzedawca mógł jeszcze nie przekazać zamówienia albo śledzenie Ozon nie ma jeszcze tego numeru. Peek nadal pyta. Jeśli dodałeś numer bez myślników, wybierz Ozon przez „Zmień przewoźnika”.

**Peek jest opóźniony względem tracking.ozon.ru.** Gdy śledzenie Ozon nie odpowiada Peek, wchodzi usługa śledzenia, która może mieć opóźnienie. Porównaj z tracking.ozon.ru, zanim się zaniepokoisz.

**Brak nowego kroku od kilku dni.** Jeśli najnowszy wpis ma co najmniej cztery dni, a Peek pokazuje „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie”, pojawia się oznaczenie „Brak aktualizacji od czterech dni” oraz link do pomocy Ozon („Poproś przewoźnika Ozon o pomoc”). Więcej w [dlaczego śledzenie nie aktualizuje się](guide:tracking-not-updating).

**`Импортная таможня задержала заказ`.** Urząd celny sprawdza zamówienie. Poczekaj na `Заказ выпущен импортной таможней.`; jeśli nic nie zmienia się przez dłuższy czas, zapytaj Ozon.

**`Заказ отменён`.** Zamówienie zostało anulowane i nie dotrze. Zapytaj Ozon albo sklep, w którym kupowałeś, o zwrot pieniędzy.

**Dostarczone, ale nic nie przyszło.** Sprawdź, czy ktoś nie odebrał go za ciebie, a potem skontaktuj się z Ozon. Zobacz [dostarczona, ale nieotrzymana](guide:delivered-not-received).

**Zaginione.** Skontaktuj się z Ozon lub ze sklepem, w którym złożyłeś zamówienie. Zobacz [zaginiona przesyłka](guide:lost-parcel).

## Obsługa klienta i kontakt z Ozon

Ponieważ Peek nie mógł odczytać strony internetowej Ozon, nie podaje tu numeru telefonu ani adresu e-mail: Peek publikuje wyłącznie kontakty sprawdzone na stronach samego przewoźnika. Własne śledzenie Ozon działa na tracking.ozon.ru.

W sprawie zamówienia kupionego w Ozon skontaktuj się z Ozon. Jeśli kupowałeś w innym sklepie, zapytaj najpierw ten sklep, bo to on zamówił dostawę.

## Śledzenie zamówienia Ozon w Peek

[Peek](/) śledzi numer Ozon wyłącznie na podstawie numeru – bez konta, telefonu ani dodatkowego kodu.

- **Z myślnikami.** Peek od razu rozpoznaje Ozon („Wykryty przewoźnik”) i przypisuje paczkę do Ozon.
- **Bez myślników.** Po samych cyfrach Peek nie rozpozna przewoźnika z pewnością. Wklej go mimo to: Peek śledzi ponad 4500 przewoźników. Podczas zapytań do pasujących firm i usług śledzenia wyświetla „Sprawdzanie usług śledzenia…”. Ozon znajduje się wśród pytanych przewoźników tylko wtedy, gdy dodajesz numer z Rosji, Białorusi lub Kazachstanu. Jeśli usługi śledzenia wskażą Ozon z datowanymi krokami, Peek sam przypisze tam przesyłkę; w przeciwnym razie kliknij „Zmień” i wyszukaj Ozon (lub użyj „Zmień przewoźnika” na już dodanej przesyłce).
- **Link.** Wklej link z tracking.ozon.ru z parametrem `track=` albo z rocket.ozon.ru z `SearchId=`, a Peek będzie śledzić ten numer w Ozon.

**Skąd biorą się kroki.** Peek odczytuje śledzenie Ozon i wyświetla każdy etap w rosyjskim brzmieniu Ozon (jak w tabelach wyżej), wraz z godziną oraz przewidywanymi datami doręczenia podanymi przez Ozon. Godziny z jawnym przesunięciem strefowym odczytuje dokładnie, a pozostałe według czasu moskiewskiego, pokazując wszystko w strefie czasowej twojego urządzenia; na peektracker.com oraz w aplikacjach na iPhone’a i Androida historia kończy się wierszem „Godziny w twojej strefie czasowej:” z jej nazwą. Ozon nie podaje miejsc przy skanach, więc nie ma mapy. Dopóki śledzenie Ozon nie zna numeru, Peek pokazuje „Oczekiwanie na przewoźnika Ozon” lub „Brak historii śledzenia” dla numeru dodanego bez przewoźnika. Gdy śledzenie Ozon nie odpowiada, Peek pyta kolejno ParcelsApp, Ship24 i 17TRACK i pokazuje historię pierwszego serwisu, który zna paczkę, w jego brzmieniu (co może być opóźnione względem tracking.ozon.ru). Bezpośrednie zapytanie do Ozon w Peek nie przyjmuje numerów `PL-`: Peek wyświetla „Sprawdź numer śledzenia”, dopóki paczki nie znajdzie usługa śledzenia.

**Link na stronie przesyłki.** „Otwórz stronę przewoźnika Ozon” otwiera numer na tracking.ozon.ru. Jeśli dane pochodzą z usługi śledzenia, link otwiera ten serwis, np. „Otwórz stronę przewoźnika ParcelsApp”.

**Przesyłka innego przewoźnika.** Peek zapisuje numer Ozon bez myślników. Dopóki śledzenie Ozon nie pokazało paczki, zapytanie bez wyniku sprawdza też w pierwszych 30 dniach, przed usługami śledzenia, wybranych przewoźników o tej samej liczbie cyfr (np. Ukrposhta i Delhivery dla 13, Hermes Germany i BRT dla 15). Jeśli któryś pokaże ruch przesyłki, Peek przenosi ją tam i pokazuje „Automatycznie zmieniono przewoźnika (wcześniej: Ozon)”.

**Doręczenie przez innego przewoźnika.** Jeśli usługa śledzenia wskaże innego przewoźnika wraz z jego numerem, a Peek obsługuje jego bezpośrednie śledzenie, pyta też tego przewoźnika; gdy tylko pojawi się jego skan nie starszy niż ostatni w usłudze, Peek przechodzi na niego.

**Częstotliwość sprawdzeń i powiadomienia.** Dopóki masz przesyłkę otwartą na ekranie albo w dniu doręczenia przy włączonych powiadomieniach, a śledzenie Ozon odpowiada, Peek sprawdza ją nawet co 10 minut (a od godz. 6:00 czasu moskiewskiego w dniu doręczenia nawet co 5 minut, gdy sprawdzenie otwiera przeglądarkę); w pozostałym czasie co godzinę. Gdy przesyłkę prowadzi usługa śledzenia, Peek sprawdza co godzinę, w dniu doręczenia nawet co 15 minut (dopóki ją masz otwartą lub włączone powiadomienia), a po dniu bez nowego wpisu co 3 godziny. Możesz wybrać powiadomienia „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Dla punktu odbioru wybierz „Ważne etapy”: „Tylko doręczenie” nie informuje, że paczka czeka już do odbioru. Peek nie skontaktuje się za ciebie z Ozon ani ze sprzedawcą.

## Pytania o śledzenie Ozon

### Jak wygląda numer śledzenia Ozon?

Peek przypisuje do Ozon dwa kształty, oba z myślnikami: trzy grupy cyfr (8 lub 10 cyfr, potem 4 i 1, np. `48291057-3628-1`) oraz numery zaczynające się od `PL-`, np. `PL-482910-57362`. Wpisuj myślniki: bez nich Peek sam nie rozpozna, że to numer Ozon.

### Dlaczego Peek nie rozpoznaje mojego numeru Ozon?

Prawdopodobnie wpisano go bez myślników lub ze spacjami. Wpisz go ponownie jako `48291057-3628-1` ze swoimi cyframi albo kliknij „Zmień” i wybierz Ozon.

### Co oznacza „Заказ в пункте выдачи”?

Twoje zamówienie jest w punkcie odbioru i możesz je odebrać. Po odbiorze strona Ozon pokaże `Заказ получен в пункте выдачи.`

### Czy mogę śledzić zamówienie Ozon po polsku?

Tak. Peek pokazuje ogólny status po polsku (np. „W transporcie”) oraz historię w rosyjskim brzmieniu Ozon; powyższe tabele objaśniają każdy krok.

:::sources
- [Universal Parcel Scraper: Ozon](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ozon-logistics) – nazwy, obsługiwane kraje (`RU`, `BY`, `KZ`), formaty z myślnikami, linki tracking.ozon.ru i rocket.ozon.ru, strona pomocy `docs.ozon.ru/common/dostavka/`, odczytywane pola i pomijane komunikaty zastępcze (`FantomDelivery` i graniczne wpisy fantomowe), rosyjskie kroki i ich etapy, telefon kuriera, odbiór w punkcie lub na poczcie
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – przewoźnicy dzielący 13 i 15 cyfr, numery Amazon (`PL-` + 10 cyfr), kolejność usług śledzenia, przewoźnicy pytani w pierwszych 30 dniach, przekazania wskazywane przez usługi śledzenia
:::
