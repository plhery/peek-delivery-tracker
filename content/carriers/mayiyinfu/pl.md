---
title: Śledzenie Mayi Yinfu: numery MY, loty i doręczenie DHL
description: Śledź paczkę Mayi Yinfu (蚂蚁银服) z Chin kontynentalnych: numery MY…US i MYYF…CN, statusy lotnicze, wpisy z wyprzedzeniem oraz przejęcie przez DHL w Europie.
slug: sledzenie-mayi-yinfu
published: 2026-11-21
updated: 2026-11-21
---

Mayi Yinfu (`蚂蚁银服`) to skrócona nazwa firmy `广东蚂蚁银服国际物流有限公司` (*Mayi Yinfu International Logistics*), spedytora z prowincji Guangdong w Chinach kontynentalnych wysyłającego za granicę drobne paczki i przesyłki ekspresowe. Jej numery śledzenia składają się z `MY`, dziesięciu cyfr i `US` (albo `MYYF`, ośmiu cyfr i `CN`): wklej swój numer w polu u góry strony lub wpisz go w okienku `快递查询` na `mayiyinfu.com.cn`. Mayi Yinfu transportuje przesyłki samolotem do twojego regionu i przekazuje je lokalnemu doręczycielowi — do Europy jest to Deutsche Post [DHL](carrier:dhl).

## Numer śledzenia Mayi Yinfu: jak wygląda

Litery `MY` pochodzą od `蚂蚁` (*mǎyǐ*, mrówka). Oto układy rozpoznawane przez Peek (z przykładowymi cyframi):

| Numer (zmyślony) | Co oznacza |
| --- | --- |
| `MY4829105736US` | `MY`, 10 cyfr i `US` (14 znaków): własny numer Mayi Yinfu, rozpoznawany od razu. |
| `MYYF48291057CN` | `MYYF` (inicjały `蚂蚁银服`), 8 cyfr i `CN` (14 znaków): drugi format obsługiwany przez wyszukiwarkę Mayi Yinfu. |
| `MY482910573US` | Tylko 9 cyfr zamiast 10: Peek wyświetli: „Zaczyna się jak numer śledzenia przewoźnika Mayi Yinfu, ale brakuje jednego znaku: może jest w nim literówka. Porównaj go z wiadomością o wysyłce.” |
| `RR482910576CN` | Dwie litery, 9 cyfr i `CN`: przesyłka pocztowa [China Post](carrier:china-post), a nie Mayi Yinfu. |

Wielkość liter i spacje nie mają w Peek znaczenia. Ponieważ Mayi Yinfu obsługuje firmy nadające, numer otrzymasz od sklepu internetowego ([jak znaleźć numer śledzenia](guide:find-tracking-number)); wyszukiwarka Mayi Yinfu potrafi też znaleźć przesyłkę po dokładnej referencji sprzedawcy (razem z myślnikami).

## Co oznaczają statusy śledzenia Mayi Yinfu

Mayi Yinfu nie publikuje słownika statusów. Jej własne wpisy są po chińsku (zwykle z dopisanym angielskim tłumaczeniem), a po przekazaniu paczki tracker kopiuje po angielsku skany firmy doręczającej:

| Status w Mayi Yinfu | Co oznacza dla twojej paczki | Peek pokazuje |
| --- | --- | --- |
| `货物电子信息已经收到` | Dane elektroniczne odebrane na platformie Hualei (`华磊`, z której korzysta także BYHD): zlecenie utworzone, brak fizycznego skanu. | „Zarejestrowana” |
| `The instruction data for this shipment have been provided by the sender to DHL electronically` | Skopiowane z DHL: dane etykiety przesłano elektronicznie do DHL, paczki jeszcze nie przekazano. | „Zarejestrowana” |
| `已入库,到达收货点` | Przyjęcie w magazynie zbiórki: Mayi Yinfu ma już twoją paczkę. | „Przyjęta przez przewoźnika” |
| `进仓时间：…；Warehouse entry time:…`, `已出库,货物离开操作中心`, `国内清关完成` | Godzina przyjęcia do magazynu, wyjście z centrum operacyjnego oraz zakończenie odprawy eksportowej w Chinach kontynentalnych. | „W transporcie” |
| `货物已交机场` | Przekazano na lotnisko wylotu (**nie** pod twoje drzwi, mimo angielskiego *delivered to the airport*), po czym pojawia się `货物已交机场，等待安排航班中The goods have been delivered to the airport and are awaiting the arrangement of flights.` lub planowany dzień lotu. | „W transporcie” |
| `航班延误` | Opóźnienie lotu wraz z dniem kolejnego rejsu. | „W transporcie” |
| `航班已起飞`, `航班落地`, `头程起飞`, `头程落地`, `二程起飞` | Start i lądowanie samolotu (`航班已起飞`, `航班落地`), wylot i lądowanie pierwszego odcinka (`头程起飞`, `头程落地`) oraz start lotu przesiadkowego (`二程起飞`, błędnie tłumaczony w trackerze jako *Flight 202*). | „W transporcie” |
| `航班落地，拆板清关The flight landed, and the board was dismantled and cleared` | Lądowanie w regionie docelowym, rozformowanie palet i odprawa celna importowa. | „W urzędzie celnym” |
| `清关完成，待转运Clearance has been completed and it is awaiting transfer.`, `已转运transferred` | Odprawa celna zakończona i przekazanie paczki przewoźnikowi końcowemu, którego numer widnieje już w karcie przesyłki. | „W transporcie” |
| `The international shipment has been processed in the parcel center of origin`, `The international shipment has been processed in the export parcel center` | Skopiowane z DHL: opracowanie w centrach paczkowych DHL w Niemczech. | „W transporcie” |
| `The shipment has arrived in the destination country/destination area.` | Dotarcie do twojego kraju (czasem z linkiem w nawiasie do lokalnego operatora). | „W transporcie” |
| `The shipment has been loaded onto the delivery vehicle` | Załadunek do samochodu kuriera. | „W doręczeniu” |
| `The shipment has been successfully delivered` | Paczka doręczona. | „Dostarczona” |

**Wiersze ze skryptu (`CO_C_CR`).** Samodzielne wpisy `国内清关中` (odprawa eksportowa), `已排航班` (lot zarezerwowany), `起运地航班起飞` (wylot), `已到达目的国` (w kraju docelowym) oraz `清关完成` (po odprawie) generuje automatyczny skrypt potrafiący pokazać paczkę za granicą, zanim dotrze ona na lotnisko — Peek nie liczy ich jako skanów ([co oznaczają statusy śledzenia](guide:tracking-statuses)).

## Kto doręcza paczki Mayi Yinfu?

Strona Mayi Yinfu nie wymienia partnerów, lecz po `已转运transferred` w rekordzie pojawia się numer przewoźnika końcowego. **Do Europy** jest to numer Deutsche Post [DHL](carrier:dhl), pod którym DHL śledzi przesyłkę aż do doręczenia w twoim kraju (wiersz przybycia DHL bywa zakończony linkiem do firmy doręczającej pod drzwi). **W innych krajach** rozpoznasz przewoźnika dzięki poradnikowi [formaty numerów śledzenia](guide:tracking-number-formats) albo dopytasz sprzedawcę. Próby doręczenia, punkty odbioru i czas przechowywania podlegają zasadom firmy końcowej; przy naliczeniu opłat importowych zajrzyj do [paczka zatrzymana przez urząd celny](guide:customs), a podejrzane wiadomości zweryfikuj z [fałszywe SMS-y o paczkach](guide:delivery-scam-texts).

## Paczka Mayi Yinfu utknęła, nie istnieje lub się spóźnia

- **Widoczne tylko pierwsze wiersze:** `货物电子信息已经收到` (również z elektronicznym wpisem DHL) oznacza, że sprzedawca utworzył zlecenie, ale nie oddał jeszcze paczki do magazynu. Po kilku dniach bez przyjęcia skontaktuj się ze sklepem.
- **Brak zmian po `已转运transferred`:** etap Mayi Yinfu dobiegł końca — śledź dalej numer firmy doręczającej ([śledzenie paczki z Chin](guide:tracking-from-china), [dlaczego śledzenie się nie aktualizuje](guide:tracking-not-updating)).
- **Nieznany numer:** dla obcego numeru wyszukiwarka Mayi Yinfu i tak zwraca pusty rekord z tym ciągiem w polu `参考号` (referencja). Peek traktuje to jako nieznaleziony numer: wyświetla „Oczekiwanie na przewoźnika Mayi Yinfu”, a jeśli numer po dwóch dniach nadal jest nieznany, prosi o jego sprawdzenie — porównaj więc znak po znaku numer z wiadomością od sklepu.
- **Zaginięcie, uszkodzenie lub zwrot:** Mayi Yinfu nie udostępnia procedury reklamacyjnej; o zwrot pieniędzy lub ponowną wysyłkę zwróć się do sprzedawcy albo na platformę zakupową ([zagubiona paczka](guide:lost-parcel), [zwrot do nadawcy](guide:returned-to-sender), [dostarczona, ale nieotrzymana](guide:delivered-not-received)).

## Kontakt i obsługa klienta Mayi Yinfu

Chińskojęzyczna strona `mayiyinfu.com.cn` podaje wyłącznie jeden numer komórkowy w Chinach kontynentalnych: **`+86 13705996996`** (`13705996996`), widoczny w nagłówku jako ogólnokrajowa infolinia (`全国服务热线`) oraz w ramce `在线咨询`. Podstrona kontaktowa (`contact.html`), opis firmy, nota prawna i polityka prywatności są puste (brak adresu, e-maila czy formularza). W każdej sprawie pisz najpierw do sprzedawcy.

## Śledzenie przesyłek Mayi Yinfu w Peek

Wklej numer `MY…US` lub link `mayiyinfu.com.cn/trackIndex.htm` z `documentCode=` w [Peek](/), a od razu zostanie przypisany do Mayi Yinfu („Wykryty przewoźnik”). Dla numeru `MYYF…CN` Peek pokaże „Sprawdzanie usług śledzenia…”, a po potwierdzeniu — „Mayi Yinfu ma tę przesyłkę.”. Dla referencji zamówienia bez myślników wybierz Mayi Yinfu przyciskiem „Zmień” (wyszukasz go także po `蚂蚁银服` lub `MYYF`; ponieważ Peek usuwa myślniki, referencje z myślnikami wpisuj bezpośrednio na `mayiyinfu.com.cn`).

**Skany, zegary i etapy zapisane z wyprzedzeniem.** Gdy wiersz lotniczy podaje oczekiwany przyszły dzień, Peek wyświetla sam angielski opis kroku (np. `The first leg has departed`) bez nieosiągniętej daty, pomijając nazwiska, adresy, kody pocztowe, wagę, opłaty i zdjęcia (brak prognozowanej daty dostawy; sam skrót `SZ`, oznaczający w normie ISO Eswatini, jest czyszczony z pola miejsca). Skany oznaczone `CN` (Chiny kontynentalne) — oraz skopiowane angielskie wiersze DHL z kodem kraju po przypięciu DHL — są odczytywane w czasie pekińskim (`UTC+8`) i przeliczane na strefę twojego urządzenia („Godziny w twojej strefie czasowej: …”); pozostałe zachowują czas lokalny. Wpisy utworzone ponad 12 godzin przed podaną w nich godziną lub ponad 6 godzin przed nią w tej samej sekundzie co taki wpis traktowane są, jak wiersze `CO_C_CR`, jako prognozy: nie liczą się jako skany i nie zmieniają statusu. Historia zaczyna się wtedy od „Inne strony mogą pokazywać więcej etapów, ale to tylko prognozy.”, a te etapy są wyszarzone pod „Pokaż”. Wpis z godziną późniejszą niż sprawdzenie Peek pojawia się dopiero po tej godzinie.

**Automatyczne przejęcie przez DHL i częstotliwość.** Gdy numer transferowy należy jednoznacznie do [DHL](carrier:dhl), Peek pokazuje „Doręcza: DHL” wraz z numerem DHL (lub „Zgłoszona przewoźnikowi DHL, jeszcze nieprzyjęta”, dopóki DHL ma tylko elektroniczną zapowiedź) i przełącza się na bezpośrednie śledzenie DHL, gdy tylko pojawi się tam skan co najmniej tak świeży jak ostatni wpis Mayi Yinfu; każdy inny numer doręczenia dodaj osobno jako drugą paczkę. Przy otwartej paczce lub w dniu dostawy z włączonymi powiadomieniami Peek sprawdza zmiany nawet co 10 minut, a poza tym oraz po 12 godzinach na etapie „Zarejestrowana” / „Przyjęta przez przewoźnika” lub dwóch spokojnych dniach — co godzinę (jeśli po dwóch dniach nadal widnieją tylko pierwsze wpisy, odpytuje też serwisy śledzące). Powiadomienia: „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”.

## Najczęstsze pytania o śledzenie Mayi Yinfu

### Dlaczego mój numer Mayi Yinfu kończy się na `US`?

Każdy numer `MY` z Mayi Yinfu ma na końcu `US` (a numery `MYYF` końcówkę `CN`), niezależnie od kraju przeznaczenia — nie oznacza to wysyłki do USA ani poczty USPS.

### Czy `货物已交机场` (*delivered to the airport*) oznacza doręczenie paczki?

Nie: informuje jedynie, że Mayi Yinfu przekazało ładunek na lotnisku wylotu do załadunku na samolot. Właściwe doręczenie pod twój adres wykona później lokalny kurier.

### Ile trwa dostawa z Mayi Yinfu?

Podstrony ofertowe Mayi Yinfu zawierają wyłącznie tekst zastępczy bez czasów przelotu — kieruj się terminem podanym przez sklep przy zakupie.

### Kogo pytać o zaginioną paczkę Mayi Yinfu?

Sprzedawcę lub platformę handlową, gdzie złożono zamówienie: to nadawca zlecił wysyłkę w Mayi Yinfu, którego strona podaje jedynie komórkowy numer w Chinach kontynentalnych.

:::sources
- [Mayi Yinfu: 广东蚂蚁银服国际物流有限公司 (strona główna)](http://www.mayiyinfu.com.cn/Default.aspx) – nazwa spółki, wyszukiwarka 快递查询, panel dla firm i infolinia komórkowa +86 13705996996 (全国服务热线 / 在线咨询)
- [Mayi Yinfu: 联系我们 (kontakt)](http://www.mayiyinfu.com.cn/contact.html) – pusta strona kontaktowa poza numerem komórkowym z nagłówka
- [Mayi Yinfu: 国际小包 (międzynarodowe małe paczki)](http://www.mayiyinfu.com.cn/products/category-1.html) – tekst zastępczy bez czasów doręczenia i listy partnerów
- [Universal Parcel Scraper: Mayi Yinfu](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/mayiyinfu) – platforma Hualei, formaty MY…US i MYYF…CN, statusy lotnicze i wiersze CO_C_CR, czas pekiński, filtr SZ oraz przejęcie przez Deutsche Post DHL
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – wykrywanie China Post i DHL, etapy zapisane z wyprzedzeniem oraz serwisy śledzące
:::
