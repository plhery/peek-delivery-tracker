---
title: Śledzenie SXW Express: numery SXW, statusy i dostawa
description: Śledź przesyłkę SXW Express (Shengxingwang) z Shenzhen: jak wygląda numer SXW, oficjalny tracker, znaczenie statusów, doręczenie i odprawa celna.
slug: sledzenie-sxw-express
published: 2026-11-21
updated: 2026-11-21
---

SXW Express to nazwa używana w Peek dla firmy `Shenzhen Shengxingwang Supply Chain Management Co., Ltd.` (`深圳胜兴旺供应链管理有限公司`) — operatora logistyki transgranicznej z siedzibą w **Shenzhen w Chinach**, który wysyła towary za granicę dla eksporterów i sprzedawców internetowych. Jeśli chiński sklep nadał Twoje zamówienie przez SXW, poszukaj numeru złożonego z liter `SXW` i dwunastu cyfr, a następnie wklej go w polu powyżej. Możesz też sprawdzić go w oficjalnym trackerze otwieranym z zakładki *Express Tracking* na stronie `sxwexpress.cn` — dokładnie z tego samego źródła korzysta Peek.

## Numer śledzenia SXW Express: jak wygląda

Strona SXW Express nie podaje opisu formatu numerów. Układ, który Peek rozpoznaje jako własny numer SXW Express, liczy 15 znaków: prefiks `SXW` i dwanaście cyfr. Przykłady z wymyślonymi cyframi:

| Numer (wymyślony) | Co oznacza |
| --- | --- |
| `SXW482910573649` | `SXW` i dwanaście cyfr: format SXW Express. Peek od razu przypisuje SXW Express. |
| `SXW48291057364` | Tylko jedenaście cyfr: nie pasuje do formatu SXW. Sprawdź w wiadomości od sprzedawcy, czy nie brakuje jednej cyfry. |
| `GFUS48291057364918` | `GFUS` i czternaście cyfr: numer [GOFO Express](carrier:gofo), który rekord SXW może zawierać dla przesyłek doręczanych w **Stanach Zjednoczonych**. |

Spacje, myślniki i małe litery nie mają znaczenia przy wklejaniu numeru do Peek. Przesyłkę zleca w SXW sprzedawca i to on przekazuje Ci numer; jeśli nie możesz go znaleźć, zobacz [jak znaleźć numer śledzenia](guide:find-tracking-number).

**Sprawdzanie na stronie SXW.** Na stronie głównej `sxwexpress.cn` w zakładce *Express Tracking* znajduje się pole *Enter your tracking number here!* oraz przycisk *Track*, który otwiera w nowej karcie tracker SXW po chińsku (dostępna jest też angielska strona wyszukiwarki `sxwexpress.cn:8082/en/trackIndex.htm`). Obok przycisku widnieje logo 17TRACK prowadzące do `17track.net` — to zewnętrzny serwis śledzenia, niezależny od trackera SXW.

## Statusy śledzenia SXW Express i ich znaczenie

W trackerze SXW trzy wpisy pochodzą po chińsku z wewnętrznego systemu SXW — Peek tłumaczy je na język angielski — natomiast większość angielskich komunikatów pochodzi z linii transportowej. Oto najważniejsze z nich w kolejności trasy:

| Tracker SXW / widok w Peek | Co oznacza dla Twojej paczki | Peek pokazuje |
| --- | --- | --- |
| `Parcel Data Received`, `Electronic notification of order creation received.` | Sprzedawca przesłał dane elektroniczne przesyłki; nikt jeszcze fizycznie nie odebrał paczki. | „Zarejestrowana” |
| `货物电子信息已经收到` → `The electronic cargo information has been received` | To samo zgłoszenie elektroniczne zapisane przez system SXW (w Peek wyświetlane po angielsku). | „Zarejestrowana” |
| `到达收货点` → `Arrived at the receiving point` | Pierwszy fizyczny skan w punkcie przyjęć SXW (w Peek po angielsku). | „Przyjęta przez przewoźnika” |
| `Parcel picked up`, `Item has been collected` | Paczka odebrana przez linię spedycyjną realizującą przewóz. | „Przyjęta przez przewoźnika” |
| `货物离开操作中心` → `Goods leave the operation center` | Przesyłka opuściła centrum operacyjne SXW (w Peek po angielsku). | „W transporcie” |
| `Parcel received at Shenzhen Sorting Centre.`, `Parcel processed at Shenzhen Sorting Centre.`, `Parcel departure in Shenzhen Sorting Centre.` | Przyjęcie, sortowanie i wyjazd z centrum sortowniczego w Shenzhen. | „W transporcie” |
| `Parcel departure in Transit Facility, Parcel Departure` | Wyjazd z obiektu tranzytowego na trasie. | „W transporcie” |
| `Customs declaration completed`, `Clearence processing completed - Export`, `Released From Export Customs` | Zakończona odprawa celna eksportowa w **Chinach** przed wylotem (czasem z pisownią `Clearence`). Paczka nie jest zatrzymana przez urząd celny w Twoim kraju. | „W transporcie” |
| `Parcel is leaving Airport, Parcel Leaving Port`, `International flight has departed` | Wylot samolotu z lotniska w Chinach. | „W transporcie” |
| `Parcel arrives at Airport, Parcel Arrival`, `International flight has arrived` | Przylot samolotu na lotnisko w kraju docelowym. | „W transporcie” |
| `Start customs clearence` | Rozpoczęcie odprawy celnej importowej w kraju przeznaczenia (pisownia `clearence`). | „W urzędzie celnym” |
| `Clearance processing completed - Import`, `Released From Import Customs` | Odprawa importowa zakończona; przesyłka jedzie dalej. | „W transporcie” |
| `Returned parcel has been recieved by shipper` | Zwrócona paczka odebrana z powrotem przez nadawcę w Chinach (pisownia `recieved`). | „Zwrócona nadawcy” |

Ponieważ wpisów dokonuje zarówno system SXW, jak i linia przewozowa, dwa komunikaty mogą mówić niemal to samo (`货物电子信息已经收到` obok `Parcel Data Received` albo `到达收货点` obok `Parcel picked up`). Na części tras SXW kopiuje także skany przewoźnika ostatniej mili (np. `Arrived at GOFO Regional Hub` w USA, `Outward MC - Handheld Acceptance` z Royal Mail w Wielkiej Brytanii albo `Onboard for delivery` i `Delivered - Left in a safe place` z Australia Post). Więcej w poradniku [co oznaczają statusy śledzenia](guide:tracking-statuses).

**Godziny i strefy czasowe.** Etapy z Chin Peek odczytuje według czasu pekińskiego (`Asia/Shanghai`, UTC+8) i przelicza na strefę czasową Twojego urządzenia; począwszy od najwcześniejszego wpisu, którego lokalizacja kończy się kodem innego kraju (np. `…, US`, `…, GB` lub `AU`), Peek zachowuje czas lokalny kraju docelowego. Na peektracker.com oraz w aplikacjach na iPhone'a i Androida pod historią widnieje „Godziny w Twojej strefie czasowej: …”.

## Kto doręcza paczki SXW Express i jakie opłaty celne obowiązują

Strona SXW Express wymienia dostawy od drzwi do drzwi `DDU/DDP` oraz lokalne doręczenia ostatniej mili (*local last-mile delivery*) w Europie, Ameryce, Azji Południowo-Wschodniej, na Bliskim Wschodzie i w Afryce. W katalogu produktów widnieją linie [UPS](carrier:ups) z Hongkongu (*Hong Kong UPS Direct Line Double Clearing With Tax Included - F*, *Hong Kong UPS-5000 (H)*), *ARAMEX Special Line*, *Middle East and Europe small package* oraz *US Express Mail Small Packet - F*, jednak bez wskazania, jaka firma doręcza paczki w poszczególnych krajach.

**Przekazania rozpoznawane przez Peek.** Przy przesyłkach do **Stanów Zjednoczonych** rekord SXW może zawierać numer [GOFO Express](carrier:gofo) (`GFUS` + 14 cyfr): Peek wyświetla wtedy „Doręcza: GOFO Express” (chyba że kanał wysyłki w rekordzie wskazuje innego przewoźnika, np. UPS), przed pierwszym skanem pokazuje „Zgłoszona przewoźnikowi GOFO Express, jeszcze nieprzyjęta”, a po pojawieniu się w GOFO skanu równie świeżego lub nowszego niż ostatni wpis SXW przełącza śledzenie na GOFO. Analogicznie, gdy kanał wskazuje [Pocztę Szwajcarską](carrier:swiss-post) z poprawnym szwajcarskim numerem do **Szwajcarii** lub **Liechtensteinu**, Peek przekazuje śledzenie do Poczty Szwajcarskiej po jej pierwszym skanie. Próby doręczenia i odbiór w punkcie podlegają zasadom lokalnego kuriera: zapytaj sprzedawcę o nazwę przewoźnika końcowego oraz jego numer, i zobacz [nieudane doręczenie](guide:missed-delivery).

**Cło i opłaty w Polsce oraz Unii Europejskiej.** W **Unii Europejskiej** od 1 lipca 2026 r. towary kupione online spoza UE w przesyłkach o wartości rzeczywistej do 150 € podlegają unijnemu cłu w wysokości 3 € za pozycję towarową (*per item*), a od 1 listopada 2026 r. dochodzi unijna opłata manipulacyjna (*Union handling fee*) w wysokości 2 € za pozycję bez względu na wartość przesyłki (jedna pozycja obejmuje towary w tej samej paczce o wspólnej klasyfikacji taryfowej, opisie oraz — gdy jest wymagane — pochodzeniu; powyżej 150 € obowiązują standardowe stawki celne), obok podatku VAT przy imporcie do **Polski**. Upewnij się u sprzedawcy, czy wysłał paczkę w formule `DDP` (opłaty pokryte z góry), czy `DDU`, i przeczytaj [paczka zatrzymana przez urząd celny](guide:customs).

## Przesyłka SXW Express stoi w miejscu lub nie dociera

**Widnieje tylko `Parcel Data Received` albo `The electronic cargo information has been received`.** Sprzedawca utworzył zlecenie, ale paczka nie została jeszcze zeskanowana. Jeśli przez kilka dni nic się nie zmienia, zapytaj sprzedawcę, czy faktycznie przekazał towar.

**„Oczekiwanie na przewoźnika SXW Express”.** Dopóki SXW wykazuje zamówienie bez realnych skanów (albo wyłącznie z etapami zapisanymi z wyprzedzeniem) i usługi śledzenia również nic nie zwracają, Peek utrzymuje status „Oczekiwanie na przewoźnika SXW Express”: w aplikacji na iPhone'a lub Androida albo na koncie Peek odpytuje SXW co 6 godzin po pierwszych godzinach; bez konta przez pierwsze 6 godzin, następnie przy otwarciu linku (najczęściej co 6 godzin, a po dwóch dniach najwyżej raz dziennie) oraz raz dziennie przez 30 dni przy włączonych powiadomieniach. Jeżeli natomiast strona SXW odpowiada, że w ogóle nie zna takiego numeru, i żaden serwis śledzenia go nie odnajduje, po dwóch dniach Peek wyświetla: „Po dwóch dniach przewoźnik nadal nie zna tego numeru. Sprawdź go, wybierz innego przewoźnika lub usuń przesyłkę.”

**`Customs declaration completed`.** To odprawa eksportowa w Chinach przed wylotem samolotu („W transporcie”), a nie zatrzymanie na granicy w Twoim kraju.

**Brak ruchu, oznaczona jako doręczona bez paczki, zagubienie lub zwrot.** Jeśli Peek pokazuje numer GOFO Express lub Poczty Szwajcarskiej, sprawdź tamto śledzenie; w przeciwnym razie kieruj się terminem dostawy podanym przez sklep i po jego upływie napisz do sprzedawcy. Pomoc znajdziesz w poradnikach [dlaczego śledzenie się nie aktualizuje](guide:tracking-not-updating), [śledzenie paczki z Chin](guide:tracking-from-china), [dostarczona, ale nieotrzymana](guide:delivered-not-received), [zagubiona paczka](guide:lost-parcel) oraz [zwrot do nadawcy](guide:returned-to-sender).

## Kontakt z obsługą klienta SXW Express

Witryna `sxwexpress.cn` nie podaje żadnego numeru telefonu, adresu e-mail ani adresu siedziby: jej strona *Contact US* zawiera wyłącznie nagłówek i pusty panel *Online Support*, a logowanie członkowskie pod `sxwexpress.cn:8082` służy firmom posiadającym konto nadawcze w SXW (nie jest potrzebne do śledzenia).

Klientem SXW Express jest sprzedawca, dlatego w sprawie zamówienia skontaktuj się najpierw ze sklepem, a w razie braku odpowiedzi otwórz spór na platformie zakupowej. W przypadku paczek doręczanych w USA przez GOFO Express w sprawach samego doręczenia pytaj bezpośrednio GOFO.

## Śledzenie przesyłki SXW Express w Peek

[Peek](/) od razu rozpoznaje numer złożony z `SXW` i dwunastu cyfr jako „Wykryty przewoźnik”, ponieważ żaden inny przewoźnik w katalogu nie używa takiego formatu (przy innym formacie Peek obsługuje ponad 3 500 przewoźników; ręcznie wybierzesz SXW przez „Zmień” lub „Zmień przewoźnika”, wpisując `SXW Express`, `Shengxingwang` lub `胜兴旺`).

Peek odpytuje oficjalny tracker SXW bez konta, kodu pocztowego i numeru telefonu. Pokazuje status, każdy potwierdzony skan (z trzema chińskimi komunikatami przetłumaczonymi na angielski), lokalizację, nazwę usługi i kraj docelowy, a pomija nazwiska, adres, kody pocztowe, liczbę sztuk, wagę, referencję klienta, dane płatności, osobę odbierającą, podpis i potwierdzenie doręczenia. Ponieważ tracker SXW nie podaje przewidywanej daty dostawy, Peek jej nie wyświetla.

**Etapy zapisane z wyprzedzeniem.** Peek wstrzymuje każdy wpis datowany w przyszłości według czasu pekińskiego, wpisy symulowanej trasy (`CO_C_CR`) oraz wiersze zapisane ponad 12 godzin przed widoczną w nich godziną (lub ponad 6 godzin przed nią w tej samej sekundzie co taki wpis) — to harmonogram, a nie prawdziwe skany. Zanim nadejdzie godzina wstrzymanego etapu, Peek go ukrywa i informuje: „Na stronie przewoźnika SXW Express widać etapy zapisane z wyprzedzeniem, nawet doręczenie. Peek nie może ich potwierdzić, więc ich nie pokazuje. Poszukaj tam firmy, która doręcza w Twoim kraju, i jej numeru śledzenia; jeśli jej nie ma, zapytaj sprzedawcę.” Gdy godzina takiego wpisu minie, Peek pokazuje go na szaro w sekcji „Zapisane z wyprzedzeniem przez przewoźnika SXW Express” z odpowiednią adnotacją („… więc pokazuje je na szaro …”) aż do faktycznego doręczenia lub zwrotu; takie wpisy nigdy nie zmieniają statusu ani nie wysyłają powiadomień. Gdy tracker SXW nie odpowiada lub nie zna numeru, Peek korzysta z usług śledzenia.

Dopóki masz otwartą przesyłkę lub w dniu dostawy włączone powiadomienia, Peek sprawdza aktualizacje nawet co 10 minut — a od 6:00 czasu lokalnego w dniu ze statusem „W doręczeniu” nawet co 2 minuty, dopóki odpowiada bezpośredni tracker SXW (po przekazaniu do GOFO Express lub Poczty Szwajcarskiej Peek przejmuje częstotliwość przewoźnika końcowego; przez same usługi śledzenia: co godzinę, co 3 godziny po dobie ciszy oraz do co 15 minut w dniu dostawy przy otwartej paczce lub włączonych powiadomieniach). W pozostałych sytuacjach oraz po dwóch dniach bez ruchu (lub 12 godzinach na etapie „Zarejestrowana” bądź „Przyjęta przez przewoźnika”) Peek sprawdza co godzinę. Możesz wybrać alerty „Każdy skan”, „Ważne etapy” (odprawa importowa jak `Start customs clearence`, problemy, wydanie do doręczenia, nieudane próby, odbiór, dostawa i zwroty — `Customs declaration completed` nie wywołuje powiadomienia) lub „Tylko doręczenie” / „Tylko w dniu dostawy”. Peek nie kontaktuje się w Twoim imieniu z SXW Express ani ze sprzedawcą.

## Najczęstsze pytania o śledzenie SXW Express

### Czym jest SXW Express?

To nazwa stosowana w Peek dla `Shenzhen Shengxingwang Supply Chain Management Co., Ltd.` (`深圳胜兴旺供应链管理有限公司`, `sxwexpress.cn`), firmy logistyki transgranicznej z Shenzhen obsługującej fracht morski i lotniczy, kolej Chiny–Europa, przesyłki ekspresowe oraz dostawy DDU/DDP.

### Gdzie mogę samodzielnie śledzić paczkę SXW Express?

W polu *Express Tracking* na stronie głównej `sxwexpress.cn`: wpisz numer i kliknij *Track*. Oficjalny tracker otwiera się po chińsku i ma też wersję angielską; umieszczone obok logo 17TRACK prowadzi do osobnego, zewnętrznego serwisu.

### Co oznacza status „Customs declaration completed” w SXW Express?

Że zakończyła się odprawa celna eksportowa w Chinach przed startem samolotu. Peek oznacza ten krok jako „W transporcie”: dotyczy on urzędu celnego w Chinach, a nie w Twoim kraju, i paczka nie jest zatrzymana.

### Kto doręcza przesyłki SXW Express w moim kraju?

Strona SXW nie publikuje listy lokalnych kurierów. W przesyłkach do USA rekord może podawać numer GOFO Express, który Peek śledzi po pierwszym skanie GOFO (a do Szwajcarii — Pocztę Szwajcarską, gdy wskazuje ją kanał); w pozostałych krajach zapytaj sprzedawcę o firmę doręczającą i jej numer śledzenia.

### Z kim się skontaktować w sprawie zaginionej paczki SXW Express?

Ze sprzedawcą, u którego złożyłeś zamówienie — to on nadał przesyłkę w SXW, a `sxwexpress.cn` nie podaje telefonu ani e-maila. Jeśli sprzedawca nie odpisuje, skorzystaj z ochrony kupujących na platformie handlowej.

:::sources
- [SXW Express: strona główna](http://www.sxwexpress.cn/) – pełna nazwa firmy, pole Express Tracking z przyciskiem Track, link do 17TRACK i oferta DDU/DDP
- [SXW Express: About US](http://www.sxwexpress.cn/about.html) – siedziba w Shenzhen, fracht morski, lotniczy, kolejowy i ekspresowy, DDU/DDP oraz doręczenia ostatniej mili
- [SXW Express: Products](http://www.sxwexpress.cn/products/default.html) – linie UPS z Hongkongu, ARAMEX Special Line, małe paczki Europa/Bliski Wschód i USA
- [SXW Express: Contact US](http://www.sxwexpress.cn/contact.html) – strona kontaktowa bez telefonu, e-maila ani adresu oraz pusty panel Online Support
- [SXW Express: 会员登录 (logowanie członkowskie)](http://www.sxwexpress.cn:8082/) – chińska nazwa spółki w systemie śledzenia
- [SXW Express: trace (angielski tracker)](http://www.sxwexpress.cn:8082/en/trackIndex.htm) – angielska wersja oficjalnej wyszukiwarki SXW
- [Komisja Europejska: Reforma celna UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – cło 3 € od 1 lipca 2026 r. oraz opłata manipulacyjna 2 € od 1 listopada 2026 r.
- [Komisja Europejska: Wytyczne dotyczące cła 3 € i opłaty manipulacyjnej (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – naliczanie od pozycji towarowej
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – format SXW, angielskie tłumaczenie chińskich wpisów, strefy czasowe, etapy zapisane z wyprzedzeniem oraz przekazanie do GOFO i Poczty Szwajcarskiej
:::
