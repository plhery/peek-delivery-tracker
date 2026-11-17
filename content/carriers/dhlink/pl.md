---
title: Śledzenie DHLink: numer z DHgate, statusy i doręczenie
description: Śledź paczkę DHLink z zamówienia na DHgate: jakie numery przyjmuje wyszukiwarka, co znaczą angielskie statusy, kto doręcza przesyłkę i do kogo pisać.
slug: dhlink-sledzenie
published: 2026-11-16
updated: 2026-11-17
---

DHLink (驼飞侠) to transgraniczna platforma logistyczna serwisu DHgate (敦煌网). Założona w 2014 roku, wysyła zamówienia sprzedawców z DHgate i innych klientów z Chin lub ze swoich magazynów zagranicznych, a na ostatnim odcinku przekazuje je lokalnemu przewoźnikowi w kraju docelowym. Wpisz swój numer na [stronie śledzenia DHLink](https://www.dhlink.com/trackQuery?lang=en) albo wklej go w polu powyżej. Numer znajdziesz w szczegółach zamówienia na DHgate; jeśli go tam nie ma, poproś o niego sprzedawcę.

## Numer śledzenia DHLink: jak wygląda

DHLink nie publikuje stałego wzoru numeracji. Jego wyszukiwarka przyjmuje do 40 numerów naraz i akceptuje to, co opisuje jako *Global Tracking No.*, *Logistics Delivery No.* oraz *DHLink pickup No.* Oto formaty spotykane na przesyłkach DHLink, z wymyślonymi cyframi:

| Numer | Co oznacza |
| --- | --- |
| `FC0000000000000` | `FC` i 13 cyfr: własny numer DHLink („DHLink No.”). Na części linii pełni też funkcję numeru śledzenia. |
| `XZG000000000WL` | `XZG`, 9 cyfr i `WL`: numer partnera liniowego, który DHLink rozpoznaje jako numer śledzenia. |
| `YLUS0000000000` | `YLUS` i 10 cyfr: linia do USA doręczana przez GOFO. |
| `QH000000000000FS` | `QH`, 12 cyfr i `FS`: inna linia do USA doręczana przez GOFO. |
| `EYT0000000000HZ` | `EYT`, 10 cyfr i `HZ`: linia do USA doręczana przez SwiftX. |
| `CYS00000000000` | `CYS` i 11 cyfr: linia do USA doręczana przez UniUni. |
| `JXCYI0000000000YQ` | `JXC`, `YI`, `ZO`, `US` lub `ZI`, 10 cyfr i `YQ`: format listu przewozowego Juxi Express, na który przy części paczek do USA odpowiada też śledzenie DHLink. Peek pyta najpierw DHLink; pozostałe numery `JXC` przypisuje do Juxi Express. |
| `RR000000005CN` | Dwie litery, 9 cyfr i `CN`: numer [China Post](carrier:china-post), używany, gdy paczka idzie drogą pocztową. |
| `GFUS00000000000000` | To nie numer DHLink, lecz GOFO Express z ostatniego odcinka niektórych linii amerykańskich. |

W wyniku wyszukiwania DHLink pokazuje „DHLink No.”, „Tracking No.” oraz – gdy doręcza inna firma – „Last Leg No.” tego przewoźnika. Jeśli numer śledzenia różni się od numeru DHLink, szukaj po numerze śledzenia: przy wyszukiwaniu samym kodem `FC` przesyłka mająca odrębny numer śledzenia może się nie pojawić. Bez parametru `lang=en` w adresie strona otwiera się po chińsku.

## Statusy śledzenia DHLink: co oznaczają

Wynik grupuje przesyłki jako `In transit`, `Delivered` lub `Not found`, podaje kody krajów nadania i przeznaczenia oraz czas każdego skanu, a po doręczeniu dopisuje `Signed for`. Ponieważ wpisy spływają z kilku źródeł, to samo zdarzenie często widnieje na stronie DHLink podwójnie. Najczęstsze angielskie komunikaty w kolejności trasy:

| DHLink pisze | Co to dla Ciebie znaczy |
| --- | --- |
| `Data Received; Data transferred and received by carrier` | Na linii do Europy Południowo-Wschodniej: Pactic, węgierska platforma, przez którą biegnie ta trasa, otrzymała dane przesyłki. Przed skanem w magazynie DHLink Peek pokazuje „Zarejestrowana”; gdy wpis powtórzy się po locie, zostaje status „W transporcie”. |
| `Arrive at the collection warehouse` | Paczka dotarła do magazynu zbiorczego DHLink w Chinach. |
| `The collection warehouse has completed sorting` | Zakończono sortowanie w tym magazynie. |
| `Package received & scanned at warehouse` | Przyjęta i zeskanowana w magazynie DHLink. |
| `Parcel handed over to carrier,waiting for transportation` | Przekazana w punkcie odbioru, czeka na transport. |
| `Package departed from the warehouse`, `The package has sent from the warehouse` | Opuściła magazyn. |
| `Warehouse scanning bagging`, `Master airway bill number received`, `Leave the warehouse` | Na niektórych liniach: zapakowanie do worka zbiorczego w Chinach przed lotem, nadanie głównego lotniczego listu przewozowego (*master air waybill*) i wyjście z magazynu („W transporcie” w Peek). |
| `Customs export clearance completed`, `Package handed over to airline` | Chiński urząd celny zwolnił paczkę do eksportu, po czym przejęła ją linia lotnicza. |
| `Flight departure`, `The flight has taken off` | Samolot wyleciał z Chin. |
| `Shipment Departed From Original Country at …`, `Departed from airport of origin` | Ten sam etap: wylot z Chin („W transporcie” w Peek). |
| `The flight has arrived and the board is being dismantled`, `Dismantling the board` | Samolot wylądował, a na lotnisku docelowym trwa rozładunek palet lotniczych („W transporcie” w Peek). |
| `Shipment has arriveved at destination Country at …` | Przesyłka dotarła do kraju docelowego (literówka w *arriveved* pochodzi od DHLink). |
| `Arrived to … hub`, `Linehaul transit; Parcel has entered the linehaul network.` | Po locie: przyjęcie w europejskim hubie partnera (oznaczonym kodem w miejscu wielokropka, np. `HU` na Węgrzech dla Pactic) i dalszy przewóz między oddziałami („W transporcie” w Peek). |
| `Customs clearance completed`, `Customs import clearance completed, awaiting pickup by local carrier` | Odprawa celna importowa zakończona; paczka czeka na odbiór przez lokalnego przewoźnika. |
| `UPS/USPS/Fedex` | Zbliża się przekazanie amerykańskiemu przewoźnikowi. |
| `货物已交UPS/USPS/Fedex,The goods have been delivered to UPS/USPS/Fedex` | Przekazano do [UPS](carrier:ups), USPS lub [FedEx](carrier:fedex) w USA. To jeszcze nie jest doręczenie do Ciebie. |
| `Package picked up by last-mile carrier` | Przewoźnik ostatniej mili w Twoim kraju odebrał przesyłkę. |
| `Scheduled for Delivery` | Wydana do doręczenia. |
| `Shipment has delivered at …` | Doręczona. |

Na liniach do USA dochodzą skany takie jak `Arrived at Origin Hub`, `Departed Destination Hub` czy `Shipping Order Created.` („Zarejestrowana” w Peek, około godziny przed przyjęciem w magazynie). Oddział przewoźnika końcowego może zapisać `Parcel received by ABC01.` (z własnym kodem zamiast `ABC01`), a kilka minut później `Parcel departed from Facility.`, oba jako „W transporcie”. Dalej pojawiają się już wpisy firmy doręczającej, np. `Arrived at GOFO Delivery Station` w GOFO, `No Access to Delivery Location` w USPS (nieudana próba doręczenia) albo w UniUni: `Parcel received at UniUni sorting hub.`, `Parcel processed.` i `Parcel scanned and in transit.` Na liniach [China Post](carrier:china-post) część wpisów jest wyłącznie po chińsku: `中国邮政 已收取邮件` (przyjęto przez China Post), `送交出口海关` (przekazano do eksportowej odprawy celnej) oraz `已妥投` po kodzie kraju (doręczono). Gdy DHLink nie zna numeru, strona wyświetla `There is no track for this waybill number`.

## Kto doręcza paczki DHLink w Polsce i za granicą

Strona DHLink jest skierowana do sprzedawców i nie podaje listy przewoźników doręczających w poszczególnych krajach – to wynik śledzenia ujawnia, kto przejmuje paczkę. Na liniach do USA jeden ze skanów zapowiada czasem przekazanie do „UPS/USPS/Fedex” bez wskazania konkretnej firmy, zanim pojawią się jej własne zdarzenia; inne trasy kończą się w GOFO Express (którego wyszukiwarka przyjmuje też 13-cyfrowy numer `FC` dla doręczanych przez siebie paczek z DHgate) lub SwiftX, a w Szwajcarii pole „Last Leg No.” może zawierać 14-cyfrowy numer DPD zaczynający się od `060` lub `061`. Przesyłki nadane przez [China Post](carrier:china-post) zachowują swój numer zakończony na `CN` aż do doręczenia: na stronie China Post sprawdzisz, który operator pocztowy doręcza je w Polsce.

Gdy znasz już lokalnego przewoźnika i jego numer, w jego własnym śledzeniu znajdziesz szczegóły doręczenia, awiza lub punktu odbioru: zobacz też poradnik [nieudane doręczenie](guide:missed-delivery).

**Cło i opłaty w Polsce i UE.** W Unii Europejskiej 1 lipca 2026 roku zniesiono zwolnienie z cła dla przesyłek o wartości do 150 €: przy zakupach internetowych wysyłanych spoza UE o wartości do 150 € obowiązuje tymczasowe zryczałtowane cło w wysokości 3 € za pozycję towarową (podpozycję taryfową, a nie za każdą sztukę), obok podatku VAT. Od 1 listopada 2026 roku UE dolicza ponadto opłatę manipulacyjną w wysokości 2 € za pozycję towarową, liczoną w ten sam sposób niezależnie od wartości przesyłki i należną z chwilą przyjęcia zgłoszenia celnego. Więcej w poradniku [paczka zatrzymana w urzędzie celnym](guide:customs).

## Śledzenie DHLink nie działa, paczka stoi albo ma status doręczonej

**Nie znaleziono numeru.** Sprawdź numer w zamówieniu na DHgate i wypróbuj numer śledzenia (*Tracking No.*) zamiast numeru `FC`. Numer lokalnego kuriera nie da wyniku na stronie DHLink – wpisz go bezpośrednio u tego przewoźnika. Jeśli dla numeru nieznanego w DHLink wybierzesz DHLink przyciskiem „Zmień”, Peek pokaże na peektracker.com „Oczekiwanie na przewoźnika DHLink” i będzie dalej sprawdzał. Jeżeli po dwóch dniach DHLink nadal nie rozpozna numeru i żadne inne źródło dla niego nie odpowie, Peek wyświetli komunikat „Po dwóch dniach przewoźnik nadal nie zna tego numeru. Sprawdź go, wybierz innego przewoźnika lub usuń przesyłkę.” i od tej pory będzie sprawdzał paczkę raz dziennie, a po tygodniu od dodania – raz w tygodniu.

**Brak nowych skanów po locie lub odprawie.** Sprawdź, czy w wyniku pojawiło się pole „Last Leg No.”, i wyszukaj ten numer u przewoźnika końcowego – jego skany bywają świeższe niż w DHLink. Więcej w poradnikach [śledzenie się nie aktualizuje](guide:tracking-not-updating) oraz [śledzenie paczki z Chin](guide:tracking-from-china).

**Doręczona, ale jej nie masz, opóźniona lub zaginiona.** Zasady zwrotów i odszkodowań DHLink dotyczą sprzedawców: to sklep składa reklamację w DHLink, a Ty wyjaśniasz sprawę ze sprzedawcą lub przez DHgate. Regulaminy wyłączają problemy wynikłe z winy kupującego, takie jak opóźnienie z powodu nieodebrania przesyłki lub braku podpisu albo nieudana odprawa celna, gdy odbiorca nie pomógł na czas. Szybko reaguj na wezwania urzędu celnego lub kuriera i wcześnie powiadom sprzedawcę – ma on tylko 90 dni kalendarzowych od utworzenia zlecenia logistycznego na zgłoszenie roszczenia. Zobacz [doręczona, ale nie dotarła](guide:delivered-not-received) oraz [paczka zaginęła](guide:lost-parcel).

## Kontakt z DHLink i obsługa klienta

Według stanu na 16 listopada 2026 roku strona kontaktowa DHLink podaje jeden adres e-mail obsługi klienta (`dhlinkservice@dhlink.com`), formularz zapytań na platformie oraz asystenta czatu „AI 小飞”. Strona jest po chińsku i nie zawiera żadnego numeru telefonu. W sprawach zamówienia napisz najpierw do sprzedawcy na DHgate; przy problemach z doręczeniem w kraju skontaktuj się z lokalnym przewoźnikiem.

## Śledzenie paczki DHLink w Peek

[Peek](/) nie rozpoznaje DHLink na pewno po samym kształcie numeru: serie `FC`, `XZG`, `YLUS`, `QH`, `EYT`, `CYS`, `JXCYI`, `JXCZO`, `JXCUS` i `JXCZI` to jedynie słabe dopasowania. Wklej numer mimo to – Peek obsługuje ponad 3500 przewoźników, pokazuje „Sprawdzanie usług śledzenia…”, a gdy DHLink potwierdzi przesyłkę, wyświetla „DHLink ma tę przesyłkę.”; w przeciwnym razie wybierz DHLink przyciskiem „Zmień”. Numer `JXCYI`, `JXCZO`, `JXCUS` lub `JXCZI` może należeć również do Juxi Express: Peek odpytuje najpierw DHLink i dopóki żaden przewoźnik nie odpowie, oznacza paczkę jako „Prawdopodobnie DHLink”; jeśli sprzedawca nadał ją przez Juxi, przełącz na Juxi Express przez „Zmień”. Numer pocztowy (dwie litery, 9 cyfr i `CN`) trafia do [China Post](carrier:china-post).

Peek czyta bezpośrednio śledzenie DHLink, pokazuje status, kraj przeznaczenia i miejscowość każdego zdarzenia oraz wyświetla każdy skan tylko raz (tak jak zapisuje go DHLink, zazwyczaj po angielsku), odrzucając duplikaty między źródłami, kody pocztowe i numery referencyjne zamówienia. Skany z Chin odczytuje według czasu pekińskiego, skany USPS według czasu danego stanu USA, a wpisy Pactic z trasy przez Węgry według czasu w Budapeszcie, po czym przelicza je na strefę czasową Twojego urządzenia (na peektracker.com i w aplikacji na iPhonie przypomina o tym dopisek pod historią: „Godziny w Twojej strefie czasowej: …”); pozostałe wpisy zachowują czas podany przez DHLink. DHLink nie podaje wagi, nazwy usługi ani godziny doręczenia, a jedyną prognozą w Peek jest przekazany skan USPS typu `Out for Delivery, Expected Delivery …`, o ile pozostaje najnowszy i zawiera przedział godzinowy na ten sam dzień. Gdy „Last Leg No.” to numer GOFO (`GFUS` i 14 cyfr), SwiftX (`SWX` i 18 cyfr) lub szwajcarskiego DPD (14 cyfr zaczynających się od `060` lub `061`), Peek pokazuje tego przewoźnika i jego numer, odpytuje go i przełącza na niego, gdy tylko pojawi się tam nowszy postęp; inne numery ostatniej mili zostają na historii DHLink.

Dopóki odpowiada bezpośrednio śledzenie DHLink, Peek sprawdza paczkę nawet co 10 minut, a w dniu wydania do doręczenia nawet co 2 minuty (od 6:00 czasu lokalnego albo – jeśli podano okno doręczenia – od pół godziny przed jego początkiem); przez serwisy śledzące co godzinę, a w dniu doręczenia nawet co 15 minut. Po przełączeniu na GOFO sprawdza co 30 minut, także w doręczeniu. Po dwóch dniach bez nowych zdarzeń (lub po 12 godzinach, gdy paczka wciąż ma status „Zarejestrowana” albo „Przyjęta przez przewoźnika”) przechodzi na sprawdzanie co godzinę. Powiadomienia możesz ustawić na „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek nie skontaktuje się w Twoim imieniu z DHLink, DHgate ani sprzedawcą.

## Najczęstsze pytania o śledzenie DHLink

### Czym jest DHLink?

To transgraniczna platforma logistyczna grupy DHgate, prowadzona w Pekinie przez spółkę 驼飞侠科技（北京）有限公司. Działa od 2014 roku i realizuje wysyłki z Chin oraz z magazynów zagranicznych za pośrednictwem własnych linii i przewoźników partnerskich.

### Kto doręcza moją paczkę z DHLink?

Lokalny przewoźnik w Twoim kraju. Na liniach amerykańskich DHLink przekazuje przesyłki do UPS, USPS lub FedEx albo do GOFO, SwiftX czy UniUni; w Szwajcarii czasem do DPD; a przy przesyłkach pocztowych zakończonych na `CN` – operatorowi pocztowemu, który obsługuje pocztę zagraniczną z China Post. Sprawdź pole „Last Leg No.” w wyniku na stronie DHLink.

### Co oznacza status „The goods have been delivered to UPS/USPS/Fedex”?

DHLink przekazał paczkę amerykańskiemu przewoźnikowi odpowiedzialnemu za dalszy odcinek trasy. Przesyłka nie została jeszcze doręczona pod Twój adres – kolejne skany będą już pochodzić od tej firmy.

:::sources
- [DHLink: O nas (关于我们)](https://www.dhlink.com/about/introduce) – założona w 2014 r. platforma logistyczna DHgate, magazyny zagraniczne i doręczenie w kraju docelowym
- [DHLink: Strona główna (首页)](https://www.dhlink.com/) – wysyłka bezpośrednia z Chin i magazyny zagraniczne
- [DHLink: Śledzenie przesyłek (轨迹查询)](https://www.dhlink.com/trackQuery?lang=en) – wyszukiwanie do 40 numerów, obsługiwane typy numerów, pola DHLink No., Tracking No., Last Leg No., Signed for, komunikat o braku danych i domyślny język strony
- [DHLink: Kontakt (联系我们)](https://www.dhlink.com/about/connect) – adres dhlinkservice@dhlink.com, formularz na platformie, czat AI 小飞, brak numeru telefonu
- [DHLink: Regulamin usług (服务协议)](https://www.dhlink.com/agreementPage/service) – operator 驼飞侠科技（北京）有限公司
- [DHgate Seller Help: Zasady odszkodowań DHLink (DHLink物流异常件赔付规则)](https://seller.dhgate.com/help/c7502/516002.html) – odszkodowania dla sprzedawców, wyłączenia z winy kupującego, termin 90 dni kalendarzowych od zlecenia logistycznego
- [Komisja Europejska: EU Customs Reform (po angielsku)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – zniesienie zwolnienia z cła do 150 € i tymczasowe cło 3 € za pozycję od 1 lipca 2026 r., unijna opłata manipulacyjna 2 € od 1 listopada 2026 r.
- [Komisja Europejska: Customs Guidance on EUR 3 customs duty, PDF (po angielsku)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – opłata 2 € liczona za pozycję taryfową tak samo jak cło 3 €, niezależnie od wartości przesyłki, należna w chwili przyjęcia zgłoszenia celnego
- [Universal Parcel Scraper: DHLink (po angielsku)](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dhlink) – formaty numerów, opisy skanów (Pactic, UniUni, China Post, USPS), strefy czasowe, przekazywanie do GOFO, SwiftX i szwajcarskiego DPD oraz działanie w Peek
- [Universal Parcel Scraper: GOFO Express (po angielsku)](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/gofo) – obsługa numerów FC z DHLink w śledzeniu GOFO i odpytywanie co 30 minut
:::
