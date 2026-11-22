---
title: Śledzenie FAR International: numery, statusy i kontakt
description: Śledź paczkę FAR International (泛远国际) z Chin: format numeru FAR…YQ, znaczenie statusów, kto doręcza przesyłkę, cło w UE i dane kontaktowe.
slug: sledzenie-far-international
published: 2026-11-22
updated: 2026-11-22
---

FAR International (泛远国际, FAR International Logistics) to firma logistyki transgranicznej z Hangzhou w Chinach, założona w 2004 roku. Sklepy internetowe i platformy zlecają jej wysyłki za granicę: FAR odbiera i sprawdza paczki w Chinach, a jej partnerzy odpowiadają za transport międzynarodowy, odprawę celną oraz przekazanie przesyłki lokalnemu przewoźnikowi w twoim kraju. Numer sprawdzisz na [angielskiej stronie Track and Trace FAR](https://www.far800.com/en/logistics) lub wklejając go w pole powyżej. Numer podaje sklep w szczegółach wysyłki zamówienia.

## Numer śledzenia FAR International: jak wygląda

FAR nie publikuje oficjalnej specyfikacji numerów. Oto wzorzec rozpoznawany przez Peek jako FAR International (fikcyjne cyfry):

| Numer | Co oznacza |
| --- | --- |
| `FARAA4829173654YQ` | `FAR`, dwie litery, 10 cyfr i `YQ`: numer FAR International. |
| `FARAA482917365YQ` | Tylko 9 cyfr: nie pasuje do formatu FAR. Peek nie wskaże FAR International; sprawdź, czy nie brakuje cyfry. |

W Peek małe litery, spacje i myślniki nie mają znaczenia. Wyszukiwarka FAR wymaga pełnego numeru i przyjmuje do 50 numerów naraz, po jednym w linii.

**Gdzie go znaleźć.** W e-mailu wysyłkowym lub na stronie zamówienia. Zobacz też [gdzie znaleźć numer śledzenia](guide:find-tracking-number) oraz poradniki [zamówienia Temu](guide:temu-orders) i [zamówienia SHEIN](guide:shein-orders).

**Drugi numer.** Strona wyników FAR może również pokazywać numer ostatniej mili (尾程单号) — to numer nadany przez przewoźnika doręczającego w twoim kraju, który można śledzić na jego stronie.

## Statusy śledzenia FAR International

Wpisy magazynowe i lotnicze FAR często łączą opis chiński i angielski, np. `仓库收到包裹 Arrived at Warehouse`: Peek zachowuje wersję angielską. Dalsze etapy bywają tylko po angielsku albo tylko po chińsku. Każdy wpis ma czas i miejsce, a wyszukiwarka FAR ma kolumnę ze strefą czasową. Typowe komunikaty w kolejności:

| Komunikat FAR | Co to oznacza dla ciebie | Status w Peek |
| --- | --- | --- |
| `Arrived at Warehouse` | Magazyn FAR w Chinach ma już paczkę. | „Przyjęta przez przewoźnika” |
| `Inspection by Warehouse` | Magazyn kontroluje towar (FAR otwiera paczki do kontroli przy odbiorze). | „Przyjęta przez przewoźnika” |
| `Await Release from Sender` | Magazyn wstrzymuje paczkę do potwierdzenia przez sprzedawcę. | „Przyjęta przez przewoźnika” |
| `Departed Warehouse`, `Pickup Scan` | Wyjście z magazynu FAR i odbiór na kolejny odcinek trasy. | „W transporcie” |
| `In Transit to Export Port` | W drodze na lotnisko lub do portu w Chinach. | „W transporcie” |
| `Export Custom Clearance Processing and Flight Booking` | Chińska odprawa eksportowa i rezerwacja lotu — rutynowy krok przed wylotem, a nie cło w twoim kraju. | „W transporcie” |
| `Scheduled to Take off`, `Export Scan` | Oczekiwanie na start samolotu i skan eksportowy. | „W transporcie” |
| `Arrived at departure transport hub`, `Leaving from departure country`, `Flight has departed` | W hubie wylotowym, a następnie wylot z kraju nadania. | „W transporcie” |
| `Arrived at Facility`, `Departed from Facility` | Przybycie do centrum przeładunkowego i wyjazd dalej. | „W transporcie” |
| `Last Mile Label Create` | Utworzono etykietę lokalnego kuriera (często pojawia się po dotarciu do hubu wylotowego — nie jest cofnięciem). | poprzedni status |
| `At destination country` | Paczka dotarła do kraju docelowego; kolej na cło i sortowanie. | „W transporcie” |
| `Picked up by Customs clearance company`, `Clearance started`, `开始清关` | Agencja celna przejęła paczkę i rozpoczęła odprawę importową. | „W urzędzie celnym” |
| `Customs Released`, `Import Scan`, `进口清关完成` | Zwolniona przez urząd celny. | „W transporcie” |
| `Handover To Last Mile`, `Received by Last Mile Provider`, `Received by local delivery company` | FAR przekazała paczkę przewoźnikowi doręczającemu w twoim kraju. | „W transporcie” |
| `包裹开始派送` | Przewoźnik końcowy przejął przesyłkę; jego własne skany pojawią się dalej (nie oznacza to jeszcze kuriera w trasie). | „W transporcie” |
| `Transferred to Next Facility`, `In transit`, `In Transit to Destination` | Przemieszczanie się między sortowniami. | „W transporcie” |
| `Out For Delivery` | Wydana kurierowi do doręczenia. | „W doręczeniu” |
| `DELIVERED` | Doręczona. | „Dostarczona” |

Po przekazaniu historia FAR może zawierać komunikaty lokalnego przewoźnika, np. FedEx (`Left FedEx origin facility`, `At local FedEx facility`, `On FedEx vehicle for delivery` jako „W doręczeniu” i `Package delivered` jako „Dostarczona”). Przy innych komunikatach Peek rozpoznaje popularne słowa jak delivered lub customs; nierozpoznany wpis zostaje w historii bez zmiany poprzedniego etapu.

FAQ FAR wyjaśnia, że `RETURN` w śledzeniu oznacza nieprawidłowość w doręczeniu. Wpis zawierający samo `RETURN` nie zmienia etapu w Peek — przeczytaj treść i skontaktuj się ze sprzedawcą.

## Kto doręcza paczki FAR International

FAR określa się jako operator „light asset”: jego punkty w Chinach odbierają, kontrolują, pakują, etykietują i sortują paczki, a dostawcy zewnętrzni (operatorzy lotnisk i portów, linie lotnicze, armatorzy, agencje celne i zewnętrzni przewoźnicy) realizują cło, transport dalekodystansowy i ostatnią milę. Strony FAR nie wymieniają lokalnych przewoźników według krajów — poszukaj numeru ostatniej mili na stronie wyników FAR.

FAR nie publikuje zasad dotyczących prób doręczenia, awiz ani punktów odbioru — obowiązują zasady lokalnego kuriera. Śledź numer ostatniej mili na jego stronie i zobacz [nieudane doręczenie](guide:missed-delivery).

**Cło w UE.** Zakupy online spoza UE podlegają od 1 lipca 2026 roku tymczasowemu cłu 3 € za pozycję w paczkach do 150 €, a od 1 listopada 2026 roku unijnej opłacie manipulacyjnej 2 € za pozycję niezależnie od wartości. Za jedną pozycję uznaje się towary o tej samej klasyfikacji taryfowej, opisie i — gdy to wymagane — pochodzeniu. Strony FAR nie podają, jak pobierane są te opłaty. Poza UE obowiązują przepisy lokalne: zobacz [paczka zatrzymana na cle](guide:customs).

**Gdy urząd celny potrzebuje danych.** FAQ FAR wskazuje, że problem z odprawą w kraju docelowym wymaga współdziałania odbiorcy z lokalnymi organami. Jeśli odprawa nie powiedzie się w terminie, paczka zostaje porzucona lub zwrócona według lokalnych przepisów, a koszty pokrywa nadawca.

## Paczka FAR International stoi, zaginęła lub wraca

FAR obsługuje sklepy i platformy, a jej FAQ kieruje je do opiekuna klienta w FAR. W razie opóźnienia, zaginięcia, uszkodzenia lub zwrotu skontaktuj się najpierw ze sprzedawcą.

**Brak danych.** Jeśli wyszukiwarka FAR nie zwraca jeszcze żadnych zdarzeń, Peek pokazuje „Oczekiwanie na przewoźnika FAR International” i sprawdza dalej. Gdy po kilku dniach nic się nie pojawi, potwierdź numer u sprzedawcy.

**Brak zmian od kilku dni.** FAQ FAR ostrzega, że aktualizacje lotnicze mogą pojawiać się z opóźnieniem. Po czterech dniach bez nowego skanu w trasie Peek pokazuje na liście dzień ostatniego skanu („Brak aktualizacji od …”), a na karcie paczki „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika FAR International.”. Zobacz [śledzenie nie aktualizuje się](guide:tracking-not-updating) oraz [śledzenie paczek z Chin](guide:tracking-from-china).

**Doręczona, ale jej nie ma.** FAQ FAR radzi sprawdzić otoczenie domu (zakamarki, wejście, skrytkę pocztową, schowek lub inne możliwe miejsca) i zapytać domowników oraz sąsiadów. Jeśli nadal jej brak, natychmiast powiadom sprzedawcę: postępowanie reklamacyjne trzeba wszcząć w ciągu **14 dni** od daty podpisania odbioru, a zgłasza je sprzedawca w FAR. Zobacz [doręczono, ale nie ma paczki](guide:delivered-not-received).

**Uszkodzona.** Zachowaj zewnętrzne pudełko oraz uszkodzony towar zgodnie z FAQ FAR i skontaktuj się ze sprzedawcą. Zobacz [uszkodzone paczki](guide:damaged-parcel).

**Zwrócona lub zaginiona.** Poproś sklep o ponowną wysyłkę albo zwrot pieniędzy. Zobacz [zwrot do nadawcy](guide:returned-to-sender) i [zaginione paczki](guide:lost-parcel).

## Obsługa klienta i kontakt FAR International

Dane podane na [angielskiej stronie FAR](https://www.far800.com/en):

- **Globalna infolinia:** 400-128-5656 (chiński numer serwisowy; strona usług wspomina o całodobowej infolinii 24/7 dla klientów, nie podając, czy działa z zagranicy ani w jakich językach).
- **E-mail:** csc@far800.com.
- **Adres:** Room 201, No. 22 Great Wall Street, Gongshu District, Hangzhou, Zhejiang Province, Chiny, kod pocztowy 310022.

W sprawie zamówienia napisz najpierw do sprzedawcy, który zlecił wysyłkę FAR.

## Śledzenie paczki FAR International w Peek

[Peek](/) rozpoznaje FAR International natychmiast po numerze `FAR…YQ` („Wykryty przewoźnik”). Działa też wklejenie linku wyników z far800.com zawierającego `key=` i numer FAR. Każdy inny numer, w tym ostatniej mili, również możesz wkleić: Peek śledzi ponad 4500 przewoźników i wskazuje właściwego, gdy format należy tylko do niego. Gdy format pasuje do kilku firm, wyświetla „Sprawdzanie usług śledzenia…”; gdy do żadnej, szuka go po dodaniu. W razie potrzeby kliknij „Zmień” i wybierz pod „Wszyscy przewoźnicy” albo użyj „Zmień przewoźnika” na już dodanej paczce. FAR International odpowiada wyłącznie na numery `FAR…YQ`.

Peek czyta bezpośrednio śledzenie FAR: status, każdy wpis z miejscem i godziną, czas doręczenia oraz numer ostatniej mili. Tam, gdzie FAR podaje strefę czasową, Peek przelicza godzinę na twoją strefę (na dole historii widnieje „Godziny w twojej strefie czasowej:” wraz z jej nazwą). FAR nie udostępnia przewidywanej daty doręczenia ani wagi.

Gdy FAR zwróci numer ostatniej mili, Peek odpytuje odpowiedniego przewoźnika: pocztę kraju, z którego sprawdzasz, dla numeru pocztowego (dwie litery, dziewięć cyfr, dwie litery), np. [Correos](carrier:correos-spain) w Hiszpanii, lub jedynego przewoźnika wynikającego z formatu. Gdy ten przewoźnik pokaże skan co najmniej tak nowy jak ostatni wpis FAR, Peek przejmuje śledzenie tam z zachowaniem historii FAR i pokazuje doręczyciela (np. „Doręcza: Correos”). Bez takiego przewoźnika zostaje przy FAR.

Gdy masz aplikację Peek otwartą na ekranie — albo w dniu doręczenia z włączonymi powiadomieniami — Peek odpytuje FAR bezpośrednio nawet co 10 minut, a od 6:00 czasu lokalnego w dniu wyjścia do doręczenia co 2 minuty; w pozostałym czasie oraz po dwóch dniach bez zmian (12 godzin, gdy paczka ma status „Zarejestrowana” albo „Przyjęta przez przewoźnika”) sprawdza co godzinę. Przez same serwisy śledzące sprawdza co godzinę, co 3 godziny po dniu bez skanu i do 15 minut w dniu doręczenia, gdy paczka jest otwarta na ekranie lub włączono powiadomienia. Powiadomienia ustawisz na „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek nie kontaktuje się w twoim imieniu z FAR ani ze sprzedawcą.

## Pytania o śledzenie FAR International

### Czy FAR International to rzetelna firma?

Tak. FAR International Logistics powstała w Hangzhou w 2004 roku. Spółka matka, FAR International Holdings Group, została założona w listopadzie 2022 roku i według aktualności FAR zadebiutowała na giełdzie w Hongkongu w grudniu 2023 roku. FAR podaje, że w 2005 roku została podwykonawcą UPS China, w 2023 uruchomiła transgraniczny projekt z Temu, a w 2024 nawiązała współpracę strategiczną z TikTok i SHEIN.

### Ile czasu idzie przesyłka FAR International?

Dla swoich klientów podaje orientacyjnie: Express 3–5 dni roboczych, Standard 6–9 oraz Economy 10–30 dni roboczych, zależnie od kodu pocztowego odbiorcy. Kieruj się szacunkowym terminem podanym przez sklep.

### Co oznacza „Await Release from Sender”?

Magazyn FAR w Chinach ma już paczkę i czeka na potwierdzenie sprzedawcy przed dalszą wysyłką. Jeśli status się nie zmienia, zapytaj sklep.

### Jak skontaktować się z FAR International?

Przez globalną infolinię 400-128-5656 lub e-mailem na csc@far800.com. W sprawie zamówienia zapytaj najpierw sprzedawcę.

:::sources
- [FAR International: Home](https://www.far800.com/en) – firma, 泛远国际, lata 2004 i 2022, debiut w Hongkongu (22 grudnia 2023), infolinia globalna, e-mail, adres
- [FAR International: Track and Trace](https://www.far800.com/en/logistics) – pełny numer i do 50 numerów po jednym w linii
- [FAR International: About FAR](https://www.far800.com/en/about) – 2004, 2022, UPS China 2005, Temu 2023, TikTok i SHEIN 2024
- [FAR International: Cross-border End-to-end Delivery](https://www.far800.com/en/service/info) – model light asset, partnerzy od cła i ostatniej mili, kontrola przy odbiorze, czasy Express/Standard/Economy, infolinia 24/7
- [FAR International: FAQs](https://www.far800.com/en/service/qa) – opóźnienia danych lotniczych, RETURN, doręczone ale nieotrzymane (14 dni), uszkodzenia, cło w kraju docelowym
- [FAR International: Privacy Policy](https://www.far800.com/en/support/privacy) – pełna nazwa firmy, infolinia, e-mail, adres
- [Komisja Europejska: reforma celna UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – cło 3 € od 1 lipca 2026 i opłata manipulacyjna 2 € od 1 listopada 2026
- [Komisja Europejska: wytyczne dotyczące cła 3 € i opłaty manipulacyjnej (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € za pozycję niezależnie od wartości oraz definicja pozycji
- [Universal Parcel Scraper: adapter FAR International](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/far-international) – format FAR…YQ, linki, opisy, numer ostatniej mili, strefy czasowe i przejęcie śledzenia
:::
