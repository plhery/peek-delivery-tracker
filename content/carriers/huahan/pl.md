---
title: Śledzenie HuaHan Logistics: numer HHW…YQ i doręczenie
description: Śledź paczkę HuaHan Logistics (华翰物流) z Chin: format numeru HHW…YQ, znaczenie statusów, lokalny przewoźnik doręczający, odprawa celna i śledzenie w Peek.
slug: sledzenie-przesylek-huahan-logistics
published: 2026-11-18
updated: 2026-11-20
---

HuaHan Logistics (华翰物流, działająca również pod nazwą HuaHan Express lub 华翰速递) to chiński operator logistyczny obsługujący transgraniczny handel internetowy, który wysyła paczki z Chin drogą lotniczą lub pocztową, a w kraju docelowym przekazuje je lokalnemu przewoźnikowi do doręczenia na ostatnim odcinku. Jeśli sklep internetowy nadał Twoje zamówienie przez HuaHan, poszukaj numeru zaczynającego się od `HHW` i zakończonego literami `YQ`, a następnie wklej go w polu powyżej. Publiczna wyszukiwarka HuaHan pod adresem [hh-exp.cn/WebTrack/CargoTrack.html](https://www.hh-exp.cn/WebTrack/CargoTrack.html) potrafi też wskazać lokalnego przewoźnika w Twoim kraju oraz nadany przez niego numer lokalny przesyłki.

## Numer śledzenia HuaHan: jak wygląda i jak go rozpoznać

Peek rozpoznaje listy przewozowe HuaHan po układzie złożonym z liter `HHW`, dwóch kolejnych liter, dziesięciu cyfr i końcówki `YQ` – łącznie dokładnie 17 znaków. Przykłady z zmyślonymi cyframi:

| Numer | Co oznacza |
| --- | --- |
| `HHWCN4829105736YQ` | `HHW`, dwie litery, dziesięć cyfr oraz `YQ`: prawidłowy numer listu przewozowego HuaHan. |
| `HHWCN482910573YQ` | Tylko dziewięć cyfr: to nie jest format HuaHan. Sprawdź, czy podczas kopiowania nie zginęła jedna cyfra. |
| `WNBAA4829105736YQ` | Również kończy się na `YQ`, ale zaczyna od `WNB`: to numer przewoźnika WanbExpress. |
| `JXCAA4829105736YQ` | Zaczyna się od `JXC`: to numer przewoźnika Juxi Express. |

Wielu chińskich spedytorów kończy swoje numery literami `YQ`, różniąc się prefiksem na początku: `HHW` wyróżnia wyłącznie HuaHan i żaden inny przewoźnik w katalogu Peek nie korzysta z tego oznaczenia. Spacje, myślniki oraz małe i wielkie litery nie mają znaczenia przy wklejaniu numeru w Peek.

**Gdzie znaleźć numer.** Sprzedawca podaje go w wiadomości e-mail z potwierdzeniem wysyłki albo w szczegółach zamówienia na koncie w sklepie. Jeśli nie możesz go odszukać, zajrzyj do poradnika [jak znaleźć numer śledzenia przesyłki](guide:find-tracking-number). Publiczna strona śledzenia HuaHan ([hh-exp.cn/WebTrack/CargoTrack.html](https://www.hh-exp.cn/WebTrack/CargoTrack.html), zatytułowana `运单追踪`) jest prowadzona po chińsku i przyjmuje jednocześnie do 20 numerów rozdzielonych przecinkami (`多个单号请用英文逗号隔开（最多支持 20 个单号同时查询）`) po ewentualnym rozwiązaniu prostego działania matematycznego; z kolei strona główna [hh-exp.cn](https://www.hh-exp.cn/) to panel logowania dla nadawców (`华翰物流订单管理系统`), zawierający odnośnik do publicznego śledzenia.

**Drugi numer śledzenia.** W karcie przesyłki HuaHan może pojawić się także własny numer lokalnego przewoźnika doręczającego. Zdarza się, że HuaHan dopisuje po znaku podkreślenia wewnętrzny przyrostek, który nie należy do właściwego numeru kuriera docelowego: pomiń znak `_` i wszystko po nim, gdy wpisujesz kod bezpośrednio na stronie lokalnego przewoźnika.

## Statusy śledzenia HuaHan i ich znaczenie

Wyszukiwarka HuaHan wyświetla każdy skan z datą i godziną, opisem oraz miejscem – od najnowszego na górze do najstarszego na dole. Własne komunikaty HuaHan są podawane po angielsku, czasem z chińskim odpowiednikiem na początku albo wyłącznie po chińsku przy wybranych operacjach magazynowych i lotniskowych. Oto typowe etapy w kolejności podróży paczki:

| Komunikat w HuaHan | Co oznacza dla Twojej przesyłki | Status w Peek |
| --- | --- | --- |
| `快件电子信息已经收到 Shipment information received`, `Shipment information received` | Sklep przesłał dane przesyłki elektronicznie do HuaHan, ale fizycznie nie przekazał jeszcze paczki (sam napis `Shipment information received` Peek tłumaczy jako „Otrzymano dane przesyłki”). | „Zarejestrowana” |
| `快件到达作业中心 Shipment arrived at facility`, `Arrived at origin facility`, `Has entered the warehouse` | Centrum operacyjne HuaHan w Chinach (w historiach zapisanych w kodzie Peek – w Shenzhen) przyjęło paczkę. | „Przyjęta przez przewoźnika” |
| `快件操作完成 Shipment departed from facility`, `Departed from origin facility`, `货物离开操作中心`, `Shipment received`, `Shipments were sent` | Przesyłka została opracowana i opuściła sortownię początkową lub przeszła przez magazyn przeładunkowy HuaHan przed eksportem. | „W transporcie” |
| `Transfer to Origin airport`, `快件从转运仓发出至机场，出口报关中（…）` | W drodze z magazynu przeładunkowego na lotnisko wylotu w trakcie zgłoszenia do odprawy eksportowej. | „W transporcie” |
| `Export customs clearance completed`, `快件抵达机场，预计…号航班飞`, `航班延误，重新配载至…号航班飞` | Chińska odprawa celna eksportowa zakończona (to nie jest urząd celny w Twoim kraju) oraz zaplanowany lub przełożony rejs lotniczy. | „W transporcie” |
| `Departed from airport of Origin` | Samolot wystartował z lotniska początkowego. | „W transporcie” |
| `Arrived at destination airport`, `拆板中` | Lot wylądował w kraju docelowym lub tranzytowym; trwa rozładunek palety lotniczej (`拆板中`). | „W transporcie” |
| `Import Customs clearance started` | Urząd celny w kraju docelowym rozpoczął odprawę importową przesyłki. | „W urzędzie celnym” |
| `Import Customs clearance completed`, `目的国清关完成` | Odprawa celna importowa została zakończona i paczka uzyskała zwolnienie. | „W transporcie” |
| `Has been given to local carrier`, `转单：…` | HuaHan przekazał przesyłkę lokalnemu przewoźnikowi (lub podaje po `转单：` numer przewozowy partnera). Kolejne wpisy pochodzą już od lokalnego operatora. | „W transporcie” |

**Wpisy lokalnego przewoźnika.** Po przekazaniu przesyłki system HuaHan przekazuje dalej skany operatora doręczającego w kraju docelowym. Peek rozpoznaje je na podstawie wewnętrznego kodu etapu HuaHan albo treści komunikatu:

| Po przekazaniu lokalnemu przewoźnikowi | Co oznacza dla Twojej przesyłki | Status w Peek |
| --- | --- | --- |
| `Ready to Ship, Parcel received and processed` | Lokalny przewoźnik odebrał przesyłkę i opracował ją w sortowni. | „W transporcie” |
| `The parcel is in the central warehouse waiting to be processed.`, `In Transit, Shipment arrived to sorting centre / Shipment in the sorting centre`, `In Transit, Shipment remained at sorting hub` | Paczka znajduje się w magazynie centralnym lub centrum sortowania lokalnego przewoźnika. | „W transporcie” |
| `In Transit, Arrived to HU HUB`, `In Transit, Parcel has entered the Linehaul network.` | Przyjęcie w europejskim hubie przeładunkowym (tutaj na Węgrzech) i dalszy przewóz między oddziałami. | „W transporcie” |
| `In Transit, Picked Up` (z niemieckim dopiskiem DPD `Einrollung`), `In Transit, Scanned By DPD At parcel delivery centre.`, `In Transit, At parcel delivery centre.` | Przyjęcie w oddziale doręczającym DPD (nie oznacza odbioru paczki od Ciebie) oraz codzienny skan magazynowy podczas oczekiwania w oddziale. | „W transporcie” |
| `Out for Delivery, Parcel is at courier, delivery expected soon.` | Przesyłka jest u kuriera w trasie doręczeniowej. | „W doręczeniu” |
| `The courier will deliver the parcel soon. Additional details are available in the SAMEDAY app, email or SMS.` | W doręczeniu z kurierem Sameday, działającym w Rumunii i Bułgarii. | „W doręczeniu” |
| `Delivery Attempted`, `Delivery Attempted, Unfortunately we have not been able to deliver your parcel. Pickup parcelshop delivery after first delivery attempt.` | Nieudana próba doręczenia przez DPD; dłuższy komunikat zapowiada skierowanie paczki do punktu Pickup. | „Próba doręczenia” |
| `Awaiting Pickup, Parcel was deposited in Pickup parcel station` | DPD złożyło przesyłkę do odbioru w punkcie lub automacie Pickup. | „Do odbioru” |
| `The parcel has been successfully delivered.`, `Delivered` | Przesyłka doręczona (samodzielny wpis `Delivered` Peek wyświetla jako „Dostarczona”). | „Dostarczona” |

HuaHan zapisuje wydanie kurierowi, złożenie w punkcie odbioru oraz doręczenie pod własnymi kodami (`CB`, `PB`, `CC`), ale Peek najpierw sprawdza sam tekst zdarzenia: przykładowo HuaHan oznacza kodem doręczenia `CB` również wyjazd z sortowni [USPS](carrier:usps) (`Departed USPS Facility`), który Peek pozostawia w statusie „W transporcie”.

**Dwa komunikaty, które potrafią zmylić.** Wpis `We will pick up your parcel from the sender soon.` pojawia się nieraz na samym początku, zanim HuaHan fizycznie odbierze paczkę w Chinach: to elektroniczna zapowiedź zarejestrowana u przewoźnika docelowego, a nie odbiór przesyłki spod Twojego adresu – Peek pokazuje „Zarejestrowana”. Podobnie `The parcel was redirected. As soon as it is in the couriers possession, it will be processed as requested.` odnotowuje dyspozycję przekierowania złożoną, gdy paczka jest jeszcze w Chinach: nie oznacza fizycznego ruchu i Peek nie przypisuje mu osobnego statusu.

**Dane elektroniczne zamiast fizycznych skanów.** `The instruction data for this shipment have been provided by the sender to DHL electronically.` (z kropką na końcu lub bez niej) to elektroniczne zgłoszenie przesyłki w systemie [DHL](carrier:dhl); `Pendiente de recepción en CTT Express` to zapowiedź w [CTT Express](carrier:ctt-express), `Pre-Shipment Info Sent to USPS` w USPS, a chiński komunikat `货物电子信息已经收到` potwierdza otrzymanie danych zamówienia. Żaden z tych wpisów nie przesuwa paczki w trasie: Peek wyświetla „Zarejestrowana” albo zachowuje dotychczasowy status.

**Jeden skan zapisany w dwóch wierszach.** Wyszukiwarka HuaHan potrafi pokazać to samo zdarzenie lokalnego przewoźnika dwukrotnie z tą samą minutą: raz własnymi słowami przewoźnika wraz z miejscowością, a drugi raz bez miejscowości w ogólnym sformułowaniu HuaHan, takim jak `Delivered, Parcel was delivered successfully`. Peek zachowuje wyłącznie wiersz zawierający nazwę miejsca.

**Wysyłka drogą pocztową.** HuaHan nadaje część przesyłek za pośrednictwem [China Post](carrier:china-post). Wówczas w historii pojawiają się po chińsku skany China Post z nazwą urzędu w nawiasach `【】`, a później skany operatora pocztowego kraju docelowego skopiowane przez China Post:

| Skan pocztowy przekazywany przez HuaHan | Co oznacza dla Twojej przesyłki | Status w Peek |
| --- | --- | --- |
| `中国邮政已收取邮件` | Poczta China Post przejęła przesyłkę od HuaHan. | „W transporcie” |
| `邮件已在【…】完成分拣，准备发出`, `邮件到达【…】`, `邮件离开【…】，正在发往【…】`, `邮件离开【…】，正在发往下一站` | Posortowano, przyjęto lub wysłano dalej w chińskim urzędzie pocztowym wskazanym w `【】`. | „W transporcie” |
| `送交出口海关` | Przekazano do chińskiego urzędu celnego eksportowego (nie jest to urząd celny w Twoim kraju). | „W urzędzie celnym” |
| `出口海关/放行`, `已交承运商运输` | Zwolniono z eksportowej kontroli celnej i przekazano linii lotniczej. | „W transporcie” |
| `到达寄达地`, `到达寄达地处理中心`, `到达境外经转局` | Dotarła do kraju docelowego, jego rozdzielni pocztowej lub zagranicznego urzędu tranzytowego. | „W transporcie” |
| `境外进口海关留存待验(等待提交给边境机构/安全部门)`, `境外进口互换局留存`, `送交境外进口海关` | Zatrzymana w urzędzie wymiany lub przekazana do kontroli celnej importowej w Twoim kraju. | „W urzędzie celnym” |
| `寄达地海关允许进口（无需缴税）` | Urząd celny kraju docelowego dopuścił przesyłkę do importu bez naliczania opłat. | „W transporcie” |
| `离开【…】处理中心`, `已到达【…】投递局` | Opuściła lokalną sortownię i dotarła do urzędu doręczeniowego. | „W transporcie” |

**Przez Landmark Global.** W przypadku innych przesyłek HuaHan przekazuje po angielsku wpisy operatora [Landmark Global](carrier:landmark-global). Dwa pierwsze pojawiają się jeszcze przed przyjęciem paczki w Chinach, natomiast kolejne następują po przekazaniu do sieci [bpost](carrier:bpost):

| Wpis Landmark Global w HuaHan | Co oznacza dla Twojej przesyłki | Status w Peek |
| --- | --- | --- |
| `Shipment Data Uploaded` | Dane nadawcy przesłane do Landmark Global. | „Zarejestrowana” |
| `Processed` | Przetworzenie w tej samej minucie, odczytywane przez Peek jako tranzyt. | „W transporcie” |
| `Incoming scan at facility` | Przyjęcie w magazynie po przekazaniu do sieci bpost. | „W transporcie” |
| `Outgoing scan at facility` | Wyjazd z tego magazynu (zapisywany przez HuaHan pod kodem wydania do doręczenia, ale pokazywany w Peek jako „W transporcie”, gdyż właściwe doręczenie może nastąpić kilka dni później). | „W transporcie” |
| `Out for delivery (On the way)` | W trasie doręczeniowej do odbiorcy. | „W doręczeniu” |
| `Delivery attempt (Delivery not possible)` | Nieudana próba doręczenia przesyłki. | „Próba doręczenia” |

## Kto doręcza paczki HuaHan i ile trwa transport

Na swojej oficjalnej witrynie firmowej ([hh-exp.com](https://www.hh-exp.com/) oraz [hh-exp.com/gyhh](https://www.hh-exp.com/gyhh)) HuaHan (`深圳市华翰鸿运国际物流技术有限公司`, założony w 2008 roku z siedzibą w Shenzhen) informuje o obsłudze ponad 150 krajów i regionów w Europie, Ameryce Północnej, Ameryce Południowej i Australii, specjalizując się w transgranicznych liniach drobnicowych dla towarów wrażliwych (`特货小包专线`, m.in. kosmetyków, baterii czy e-papierosów) oraz kanałach pocztowych (`特货邮路`). Na stronie głównej podaje orientacyjny łączny czas przewozu od 6 do 12 dni roboczych (`整体时效6-12个工作日`), nie publikuje jednak szczegółowych tabel dla poszczególnych państw ani pełnej listy lokalnych partnerów: wiążący dla Twojego zakupu pozostaje termin podany przez sklep.

Po komunikacie `Has been given to local carrier` paczkę doręcza przewoźnik w kraju przeznaczenia. W przebiegach analizowanych przez kod Peek pojawiają się m.in. [DHL](carrier:dhl), DPD, [CTT Express](carrier:ctt-express), Sameday, [USPS](carrier:usps), [China Post](carrier:china-post) (przekazująca przesyłki pocztowe operatorowi kraju przeznaczenia) oraz [Landmark Global](carrier:landmark-global) współpracujący z siecią [bpost](carrier:bpost). Peek zawsze kieruje się przewoźnikiem i numerem wskazanym w rekordzie konkretnej przesyłki.

**Próby doręczenia i odbiór w punkcie.** Liczba prób doręczenia, awizo oraz termin odbioru w placówce, punkcie partnerskim lub automacie paczkowym podlegają regulaminowi lokalnego przewoźnika: skontaktuj się z nim, podając jego własny numer lokalny. Zobacz również [nieudane doręczenie](guide:missed-delivery) oraz [nieodebrana przesyłka](guide:uncollected-parcel).

## Odprawa celna, opłaty importowe i fałszywe SMS-y

HuaHan świadczy usługi dla sprzedawców internetowych, a jego strony nie rozstrzygają, czy podatek VAT i należności celne od Twojego zamówienia zostały opłacone z góry przy zakupie: sprawdź to w podsumowaniu zamówienia w sklepie. Więcej wyjaśnień znajdziesz w poradnikach [paczka zatrzymana w urzędzie celnym](guide:customs) oraz [jak rozpoznać fałszywe SMS-y o paczce](guide:delivery-scam-texts).

- **Unia Europejska.** Od 1 lipca 2026 roku w **Unii Europejskiej** towary kupowane na odległość spoza obszaru celnego UE w przesyłkach o wartości rzeczywistej do 150 € objęte są cłem w wysokości 3 € za każdą kategorię towarową, a od 1 listopada 2026 roku doliczana jest unijna opłata manipulacyjna w wysokości 2 € za każdą kategorię towarową niezależnie od wartości przesyłki (jedna kategoria obejmuje towary o tej samej klasyfikacji taryfowej, tym samym opisie oraz – gdy jest wymagane – tym samym pochodzeniu, bez względu na liczbę sztuk).
- **Polska.** W **Polsce** w przypadku przesyłek spoza UE odprawianych przez [Pocztę Polską](https://www.poczta-polska.pl/news/zmiany-w-oplatach-dla-przesylek-spoza-unii-europejskiej-od-1-lipca-2026-r/) opłaty – cło, podatek VAT (jeżeli nie został opłacony w momencie zakupu) oraz opłata za dokonanie zgłoszenia celnego przez Pocztę Polską – są pobierane przy doręczeniu przesyłki lub przed jej odbiorem – w zależności od zastosowanej procedury celnej; jednocześnie Poczta Polska ostrzega w [komunikacie o fałszywych wiadomościach Pocztex](https://www.poczta-polska.pl/news/uwaga-na-falszywe-wiadomosci-pocztex-grozna-kampania-phishingowa/), że jej usługa kurierska Pocztex nie wysyła w wiadomościach SMS linków prowadzących do płatności ani nie prosi przez SMS o dane kart płatniczych.

## Paczka HuaHan nie dociera albo śledzenie utknęło

**Nie znaleziono numeru (`没有找到记录`).** Gdy HuaHan nie ma jeszcze numeru w bazie, jego strona zwraca komunikat `没有找到记录` („nie znaleziono wpisu”); Peek wyświetla „Oczekiwanie na przewoźnika HuaHan Logistics” i ponawia zapytania. Jeśli po dwóch dniach ani HuaHan, ani inne źródło nadal nie rozpoznaje numeru, pojawia się komunikat „Po dwóch dniach przewoźnik nadal nie zna tego numeru. Sprawdź go, wybierz innego przewoźnika lub usuń przesyłkę.”, a kolejne sprawdzenia odbywają się raz dziennie i – po upływie tygodnia – raz w tygodniu. Porównaj dokładnie każdy znak (`HHW`, dwie litery, dziesięć cyfr, `YQ`) z wiadomością od sprzedawcy.

**Zatrzymana na `Shipment information received`.** Sklep utworzył zlecenie wysyłki, lecz HuaHan nie zeskanował jeszcze paczki w swoim magazynie. Zapytaj sprzedawcę, kiedy fizycznie przekazał przesyłkę.

**Zatrzymana na `Import Customs clearance started`.** Urząd celny w Twoim kraju prowadzi kontrolę przesyłki. Jeśli status utrzymuje się przez wiele dni bez powiadomienia od lokalnego doręczyciela, zgłoś to sprzedawcy.

**Brak wpisów po `Has been given to local carrier`.** Lokalny przewoźnik przejął już paczkę, a jego własne śledzenie bywa dokładniejsze niż przekazywane przez HuaHan. Odczytaj nazwę i numer lokalny na stronie HuaHan lub na karcie przesyłki w Peek.

**Brak aktualizacji od kilku dni.** Gdy przesyłka w drodze nie otrzyma żadnego nowego skanu przez cztery dni, Peek oznacza ją komunikatem „Brak aktualizacji od czterech dni” i przypomina, że przerwy w skanowaniu często zdarzają się podczas długich lotów, odprawy celnej lub w weekend. Zobacz [dlaczego śledzenie się nie aktualizuje](guide:tracking-not-updating) oraz [śledzenie paczek z Chin](guide:tracking-from-china).

**Oznaczona jako dostarczona, ale jej nie ma.** Sprawdź skrzynkę, recepcję budynku, sąsiadów oraz pobliskie punkty odbioru, a następnie skontaktuj się z lokalnym przewoźnikiem i sprzedawcą. Zobacz [dostarczona, ale nieotrzymana](guide:delivered-not-received).

**Zagubiona lub zwrócona.** Umowę przewozu z HuaHan zawarł sprzedawca, dlatego o zwrot pieniędzy lub ponowną wysyłkę należy zwrócić się do sklepu lub platformy zakupowej. Zobacz [zagubiona paczka](guide:lost-parcel) i [zwrot do nadawcy](guide:returned-to-sender).

## Numer telefonu i obsługa klienta HuaHan

Na oficjalnej podstronie kontaktowej ([hh-exp.com/lxwm](https://www.hh-exp.com/lxwm)) HuaHan podaje dla **Chin**:

- **Oficjalna infolinia (`官方热线`):** `400-015-6080` (chiński numer krajowy 400, bez opublikowanych godzin pracy; numer komórkowy `13794450408` służy do zapytań handlowych dla nadawców `业务咨询`).
- **Siedziba główna:** 10. piętro, budynek C, Bairuida Building, 4001 Xuegang Avenue, Bantian, dzielnica Longgang, Shenzhen (`深圳市龙岗区坂田雪岗大道4001号百瑞达大厦C座10楼`), z oddziałami w Yiwu, Zhengzhou i Hangzhou.

HuaHan nie podaje adresu e-mail obsługi dla zagranicznych odbiorców indywidualnych. W sprawie zamówienia lub zwrotu środków pisz do sprzedawcy; w sprawie samego doręczenia kontaktuj się z lokalnym przewoźnikiem.

## Śledzenie przesyłki HuaHan w aplikacji Peek

[Peek](/) natychmiast rozpoznaje HuaHan Logistics po pełnym numerze `HHW…YQ` („Wykryty przewoźnik”) i odpytuje system HuaHan wyłącznie na podstawie tego numeru – bez zakładania konta, kodu pocztowego ani numeru telefonu. Jeśli witryna HuaHan jest niedostępna, Peek korzysta z serwisów śledzących.

Peek pokazuje aktualny status, każdy skan wraz z miejscowością oraz zapisany kraj przeznaczenia. Ponieważ wyszukiwarka HuaHan podaje godziny bez strefy czasowej, Peek pozostawia wydrukowany czas lokalny przy własnych skanach HuaHan sprzed przekazania partnerowi, natomiast skany po `Has been given to local carrier` osadza w strefie czasowej kraju docelowego, o ile kraj ten ma jedną strefę czasową, przeliczając ustalone godziny na strefę Twojego urządzenia („Godziny w Twojej strefie czasowej: …” na peektracker.com i w aplikacjach na iPhone’a i Androida). HuaHan nie udostępnia przewidywanego dnia dostawy ani wagi paczki, więc Peek ich nie wyświetla.

Gdy HuaHan wskaże znanego w Peek lokalnego przewoźnika wraz z pasującym numerem, karta przesyłki informuje o tym („Doręcza: DHL” albo „Zgłoszona przewoźnikowi DHL, jeszcze nieprzyjęta”, dopóki widnieje tam jedynie zapowiedź elektroniczna) i odpytuje także tego przewoźnika. Jeśli HuaHan poda międzynarodowy numer pocztowy (dwie litery, dziewięć cyfr, dwie litery) bez nazwy znanego kuriera, Peek odpytuje pocztę kraju docelowego. Z chwilą gdy lokalny przewoźnik pokaże skan co najmniej tak świeży jak ostatni wpis HuaHan, Peek przełącza śledzenie doręczenia na niego, zachowując dotychczasową historię HuaHan.

Dopóki masz otwartą kartę przesyłki albo w dniu jej doręczenia przy włączonych powiadomieniach, Peek sprawdza status nawet co 10 minut – a w dniu wydania kurierowi do doręczenia od godziny 6:00 czasu lokalnego nawet co 2 minuty, gdy wyszukiwarka HuaHan odpowiada bezpośrednio, lub co 5 minut, gdy sprawdzenie otwiera przeglądarkę; korzystając wyłącznie z serwisów śledzących, sprawdza co godzinę, co 3 godziny po dobie bez skanu oraz nawet co 15 minut w dniu doręczenia przy otwartej przesyłce lub aktywnych powiadomieniach. W pozostałym czasie, a także po dwóch dniach bez nowych zdarzeń (12 godzin na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika”), sprawdza co godzinę, a po przejęciu paczki przez lokalnego przewoźnika dopasowuje częstotliwość do jego rytmu. Powiadomienia możesz ustawić na „Każdy skan”, „Ważne etapy” (obejmuje `Import Customs clearance started`, pocztowe skany celne takie jak `送交出口海关` i `送交境外进口海关`, wydanie do doręczenia, punkt odbioru i doręczenie, z pominięciem rutynowej odprawy eksportowej `快件从转运仓发出至机场，出口报关中`) lub „Tylko doręczenie”. Peek nie może skontaktować się w Twoim imieniu ani z HuaHan, ani ze sprzedawcą.

## Najczęstsze pytania o śledzenie HuaHan

### Czym jest HuaHan Logistics (华翰物流)?

To chińska firma logistyczna specjalizująca się w transgranicznym e-commerce (`深圳市华翰鸿运国际物流技术有限公司`), założona w 2008 roku w Shenzhen i znana również jako HuaHan Express (`华翰速递`). Wysyła paczki z Chin do ponad 150 krajów i przekazuje je lokalnym przewoźnikom doręczającym.

### Gdzie mogę śledzić paczkę HuaHan?

W polu wyszukiwania na górze tej strony albo w publicznej wyszukiwarce HuaHan pod adresem `hh-exp.cn/WebTrack/CargoTrack.html` (do 20 numerów oddzielonych przecinkami po ewentualnej weryfikacji rachunkowej). Zawsze wpisuj pełny 17-znakowy numer `HHW…YQ`.

### Co oznacza status „Has been given to local carrier” w HuaHan?

Odprawa celna importowa dobiegła końca i HuaHan przekazał przesyłkę firmie, która doręcza ją w Twoim kraju. Następne wiersze w historii pochodzą już od tego lokalnego przewoźnika.

### Kto doręcza paczki HuaHan w moim kraju?

Zależy to od kanału wybranego przez nadawcę: w kodzie Peek występują m.in. DHL, DPD, CTT Express, Sameday, USPS, China Post (przekazująca przesyłki pocztowe operatorowi kraju przeznaczenia) oraz Landmark Global współpracujący z siecią bpost. Sprawdź kartę przesyłki w Peek lub wyszukiwarkę HuaHan, aby zobaczyć przewoźnika przypisanego do Twojej paczki.

### Z kim się skontaktować, jeśli paczka HuaHan nie dotarła?

Ze sklepem lub platformą zakupową, w której złożono zamówienie – to sprzedawca zlecił wysyłkę w HuaHan. Jeżeli przesyłka została już przekazana lokalnemu przewoźnikowi w Twoim kraju, skontaktuj się z nim, podając jego lokalny numer śledzenia.

:::sources
- [HuaHan Express: 运单追踪 (publiczne śledzenie przesyłek, po chińsku)](https://www.hh-exp.cn/WebTrack/CargoTrack.html) – publiczna wyszukiwarka do 20 numerów rozdzielonych przecinkami, kolumny statusu i szczegóły skanów
- [HuaHan Logistics: 华翰物流订单管理系统 (panel nadawców, po chińsku)](https://www.hh-exp.cn/) – portal logowania dla sprzedawców i odnośnik do publicznego śledzenia
- [HuaHan Express: Oficjalna strona główna (po chińsku)](https://www.hh-exp.com/) – założenie firmy w 2008 r., linie e-commerce i towarów wrażliwych do ponad 150 krajów i regionów, orientacyjny czas przewozu 6–12 dni roboczych oraz infolinia 400-015-6080
- [HuaHan Express: 关于华翰 (o firmie, po chińsku)](https://www.hh-exp.com/gyhh) – historia od 2008 r., pełna nazwa spółki, kanały specjalne i pocztowe oraz oddziały
- [HuaHan Express: 联系我们 (kontakt, po chińsku)](https://www.hh-exp.com/lxwm) – centrala w Shenzhen, oddziały w Yiwu, Zhengzhou i Hangzhou, oficjalna infolinia 400-015-6080 oraz telefon handlowy
- [Komisja Europejska: EU Customs Reform (po angielsku)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – cło 3 € za kategorię towaru do 150 € od 1 lipca 2026 r. oraz unijna opłata manipulacyjna 2 € od 1 listopada 2026 r.
- [Komisja Europejska: Guidance on the EUR 3 customs duty and the Union handling fee (PDF, po angielsku)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – opłata 2 € niezależnie od wartości przesyłki oraz definicja kategorii towarowej
- [Poczta Polska: Zmiany w opłatach dla przesyłek spoza Unii Europejskiej od 1 lipca 2026 r.](https://www.poczta-polska.pl/news/zmiany-w-oplatach-dla-przesylek-spoza-unii-europejskiej-od-1-lipca-2026-r/) – pobór opłat celno-podatkowych w Polsce przy doręczeniu przesyłki lub przed jej odbiorem w zależności od procedury celnej
- [Poczta Polska: Uwaga na fałszywe wiadomości Pocztex](https://www.poczta-polska.pl/news/uwaga-na-falszywe-wiadomosci-pocztex-grozna-kampania-phishingowa/) – ostrzeżenie w Polsce, że Pocztex nie wysyła w SMS-ach linków do płatności ani nie prosi o dane kart
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – format `HHW…YQ`, weryfikacja rachunkowa, kody i opisy skanów HuaHan, China Post, Landmark Global/bpost, DPD, Sameday, DHL, CTT Express i USPS, strefy czasowe i przekazanie lokalnemu przewoźnikowi
:::
