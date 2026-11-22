---
title: Śledzenie FIRST LINE: numery 1ST, statusy i doręczenie
description: Śledź paczkę FIRST LINE (壹号专线) z Chin: jak wygląda numer 1ST, znaczenie statusów, kto doręcza w USA i do kogo zgłosić problem z przesyłką.
slug: sledzenie-first-line
published: 2026-11-22
updated: 2026-11-22
---

FIRST LINE (壹号专线, Shenzhen First Line Supply Chain Co., Ltd.) to spedytor z Shenzhen wysyłający paczki z Chin dla sprzedawców internetowych — głównie do Stanów Zjednoczonych — i przekazujący je tam lokalnemu przewoźnikowi do doręczenia. Należy do grupy 1ST (一代国际集团), której spółki wydają listy przewozowe zaczynające się od `1ST`. Paczkę sprawdzisz w polu wyszukiwania na stronie głównej firstline56.com (otwiera stronę 追踪单号 — śledzenie numeru) albo w polu powyżej. Numer podaje sklep w danych wysyłkowych zamówienia.

## Numer przesyłki FIRST LINE: jak wygląda

FIRST LINE nie publikuje oficjalnego formatu. Jego listy przewozowe, podobnie jak w całej grupie 1ST, to `1ST` i 11 cyfr (przykłady wymyślone):

| Numer | Co oznacza |
| --- | --- |
| `1ST48291736548` | `1ST` i 11 cyfr: list przewozowy grupy 1ST śledzony na stronie FIRST LINE. |
| `9400 1847 2936 5482 9173 64` | 22 cyfry zaczynające się od `94`: etykieta [USPS](carrier:usps) do doręczenia w USA. |

**Ten sam prefiks, inni spedytorzy.** Cztery firmy grupy — 1st Agent International (一代国际), FIRST LINE, Ferry-Boat (摆渡一下) i Universal Best Union (万国优联) — stosują identyczne numery `1ST`. Numer `1ST`, którego tracker FIRST LINE nie zna, może należeć do jednej z pozostałych trzech firm.

**Gdzie go znaleźć.** W szczegółach wysyłki ze sklepu, gdzie jako przewoźnik może widnieć USPS, China Post lub FIRST LINE — FIRST LINE pozwala sprzedawcom podać w serwisie sprzedażowym dowolną z tych nazw. Grupa informuje sprzedawców, że numer ostatniej mili (np. USPS) pojawia się 6 do 9 dni po przyjęciu paczki w magazynie, więc sklep może od razu przekazać ten numer. Więcej w poradniku [gdzie znaleźć numer śledzenia](guide:find-tracking-number).

## Znaczenie statusów śledzenia FIRST LINE

Tracker FIRST LINE jest po chińsku, ale same skany są po angielsku. Wpisy FIRST LINE w typowej kolejności:

| FIRST LINE pisze | Co to oznacza dla Ciebie |
| --- | --- |
| `Forecast has been received` | Sprzedawca wysłał dane zamówienia. FIRST LINE jeszcze nie ma paczki. `Parcel information received` znaczy to samo. |
| `Order received` | Przewoźnik doręczający, np. UniUni, otrzymał dane zamówienia, ale jeszcze nie paczkę. |
| `Barcode scanning` | Pierwszy skan w magazynie FIRST LINE w Shenzhen. |
| `Shipment arrived` | Również pierwszy skan w Shenzhen, nie przylot do Twojego kraju. |
| `Packaged` | Spakowana do wysyłki zagranicznej. |
| `Leaving Scan` | Opuściła magazyn. |
| `Export customs declaration completed` | Chińska odprawa eksportowa w Shenzhen przed lotem. Paczka nie jest zatrzymana na cle. |
| `Depart from facility to service provider` | W drodze z FIRST LINE do firmy przejmującej dalszy etap. |
| `Hand over to airline` | Przekazana linii lotniczej na lotnisku w Hongkongu po odprawie eksportowej. |
| `Next stop destination airport` | W locie na lotnisko docelowe. Jako miejsce nadal widnieje lotnisko w Hongkongu. |
| `Arrived at destination airport` | Samolot wylądował. Następuje odprawa importowa. |
| `The package is being submitted for customs clearance` | Odprawa importowa po przylocie. Peek pokazuje „W urzędzie celnym”. |
| `The package has been cleared through customs` | Zwolniona z cła. Peek znów pokazuje „W transporcie”. |
| `Customs broker picked it up` | Agent celny odebrał ją po zwolnieniu, by przekazać do doręczenia. Nie jest już w urzędzie celnym. |
| `The package has been transferred to the service provider` | Przekazana przewoźnikowi doręczającemu po agencie celnym. |

W USA następują skany lokalnego przewoźnika w jego brzmieniu aż do doręczenia: w obserwowanych historiach były to USPS, [GOFO](carrier:gofo) lub UniUni. Wyjazd GOFO do doręczenia brzmi `Out for Delivery` (w Peek „W doręczeniu”), a doręczenie `Delivered,` wraz z miejscem pozostawienia (np. `Door/Yard`) oraz `For delivery issues & tracking support, contact GOFO at` i kontaktem — Peek pokazuje to jako „Dostarczona”. Tracker FIRST LINE nigdy nie wymienia firmy doręczającej z nazwy, więc wskazówką jest sam tekst.

Każda godzina to czas lokalny miejsca skanowania (Shenzhen lub Hongkong, a potem USA) bez podanej strefy czasowej, więc amerykański skan może mieć wcześniejszą godzinę niż ostatni skan w Chinach. Dla nieznanego numeru pojawia się `未找到此单号信息` („nie znaleziono informacji dla tego numeru”).

## Kto doręcza paczki FIRST LINE

FIRST LINE wywozi paczkę z Chin, zleca odprawę celną po przylocie i przekazuje ją lokalnemu przewoźnikowi:

- **USA, drogą lotniczą:** doręcza USPS lub lokalny przewoźnik komercyjny; strona FIRST LINE wymienia USPS i DHL eCommerce. FIRST LINE podaje 7 do 16 dni od drzwi do drzwi.
- **USA, drogą morską:** szybkimi statkami Matson, następnie [FedEx](carrier:fedex) lub USPS, w 19 do 31 dni od drzwi do drzwi.
- **Kanada:** lotem do Vancouver lub Toronto, a potem Canada Post — według FIRST LINE 2 do 7 dni po odprawie celnej.
- **Europa, drogą lotniczą:** lot do Pragi i odprawa w Czechach, następnie doręczenie przez [UPS](carrier:ups) do domów, firm i magazynów Amazon w ponad 20 krajach, m.in. Niemczech, Francji, Włoszech, Hiszpanii i Wielkiej Brytanii. Każda sztuka rozliczana jest jako minimum 12 kg, a podatki zawiera opłata sprzedawcy.

Linie amerykańskie obsługują wyłącznie 48 kontynentalnych stanów USA — bez Alaski, Hawajów, Portoryko, Wysp Dziewiczych USA, Guam, skrytek pocztowych, adresów wojskowych i adresów Amazon. FIRST LINE sprzedaje linie do USA i Kanady w formule DDP: opłaty importowe pokrywa sprzedawca. Więcej o [przesyłkach w odprawie celnej](guide:customs).

FIRST LINE nie publikuje zasad prób doręczenia, awiz ani punktów odbioru — obowiązują zasady lokalnego przewoźnika. Tracker nie pokazuje numeru ostatniej mili, więc aby zmienić doręczenie, poproś sprzedawcę o numer lokalnego przewoźnika.

## Paczka FIRST LINE nie dociera lub śledzenie stanęło

**Tylko `Forecast has been received`.** FIRST LINE jeszcze jej nie zeskanowało. Grupa podaje sprzedawcom, że paczki przekazane przed 17:00 pojawiają się online tego samego dnia. Jeśli przez dni nic się nie pojawia, zapytaj sprzedawcę, czy nadał paczkę.

**Brak wpisów po eksporcie.** Przed paczką lot i odprawa importowa, więc historia może stać przez kilka dni. Gdy czas wyraźnie przekracza powyższe widełki, skontaktuj się ze sprzedawcą. Więcej w [dlaczego śledzenie przestaje się aktualizować](guide:tracking-not-updating).

**Doręczona, ale jej nie ma.** Gdy lokalny przewoźnik pokazuje doręczenie, którego nie otrzymałeś, warunki FIRST LINE przewidują pomoc w wyjaśnieniu, ale bez odszkodowania. Zacznij od przewoźnika lokalnego i sprzedawcy — zobacz [doręczona, ale nieodebrana](guide:delivered-not-received).

**Zagubiona lub uszkodzona.** Reklamacje składa sprzedawca w ciągu 60 dni od nadania, a FIRST LINE nie wypłaca odszkodowań za uszkodzenia w transporcie ani opóźnienia. Poproś sprzedawcę lub platformę o zwrot pieniędzy albo nową przesyłkę. Przy uszkodzeniu FAQ grupy prosi odbiorcę o zgłoszenie go najpierw lokalnie i zachowanie numeru zgłoszenia oraz zdjęć opakowania. Zobacz [zagubiona paczka](guide:lost-parcel).

**Zwrócona.** Na liniach amerykańskich niedoręczalna paczka nie wraca do Chin: trafia do zagranicznego magazynu FIRST LINE i jeśli sprzedawca w ciągu 14 dni nie opłaci ponownego doręczenia, zostaje zniszczona. Gdy śledzenie pokazuje zwrot, natychmiast powiadom sprzedawcę. Więcej w [zwrot do nadawcy](guide:returned-to-sender).

## Kontakt i obsługa klienta FIRST LINE

Na chińskiej stronie kontaktu FIRST LINE podaje centrum obsługi klienta (客维中心) i adres biura: 3rd floor, Building 36, Zone 5, Cuigang Industrial Park, Huaide, Fuyong, Bao'an District, Shenzhen, Chiny. Nie podaje godzin ani języków. Strona grupy 1ST udostępnia formularz skarg (投诉建议) z polami na telefon, e-mail i wiadomość.

Kanały te służą sprzedawcom będącym klientami FIRST LINE; nie ma publicznego wsparcia dla odbiorców. W sprawie zamówienia zwróć się do sprzedawcy, a w sprawie doręczenia do lokalnego przewoźnika.

## Śledzenie paczki FIRST LINE w Peek

[Peek](/) od razu rozpoznaje numer `1ST` z 11 cyframi jako FIRST LINE, niezależnie od wielkości liter i spacji. Peek nie odczytuje linków FIRST LINE — wklej sam numer.

Peek odczytuje tracker FIRST LINE i wyświetla status, historię (do 100 skanów), miejsce każdego skanu bez kodu pocztowego oraz czas odczytany według lokalnego zegara miejsca i przeliczony na strefę Twojego urządzenia; na peektracker.com oraz w aplikacjach na iPhone'a i Androida historia kończy się wierszem „Godziny w twojej strefie czasowej:” z jej nazwą. Opisy pozostają dokładnie takie, jak podaje FIRST LINE — łącznie z przekazywanymi skanami USPS, GOFO czy UniUni — poza dokładnym `Delivered`, które Peek wyświetla własnymi słowami. Ponieważ tracker nie podaje kraju docelowego, firmy doręczającej, numeru ostatniej mili ani dowodu doręczenia, Peek ich nie pokazuje. Jedyną prognozą jest przekazany skan USPS „Out for Delivery, Expected Delivery …”: dopóki jest najnowszy, Peek pokazuje podane okno czasowe na ten dzień. Peek nie przełącza się automatycznie na USPS, GOFO ani UniUni — jeśli sprzedawca poda Ci lokalny numer, dodaj go osobno (Peek śledzi ponad 4500 przewoźników).

Gdy masz paczkę otwartą na ekranie albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza status do co 10 minut, a w dniu wyjścia do doręczenia od 6:00 czasu lokalnego (lub od pół godziny przed początkiem okna czasowego, jeśli jest podane) co 2 minuty, dopóki odpowiada własny tracker FIRST LINE; przez usługi śledzenia co 15 minut. Poza tym oraz po dwóch dniach bez nowych zdarzeń (po 12 godzinach, gdy przesyłka ma jeszcze status „Zarejestrowana” lub „Przyjęta przez przewoźnika”) sprawdza raz na godzinę. Powiadomienia ustawisz na „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek nie kontaktuje się za Ciebie z FIRST LINE ani ze sprzedawcą.

## Pytania o śledzenie FIRST LINE

### Co to jest numer śledzenia 1ST?

To numer listu przewozowego spedytorów z grupy 1ST, m.in. FIRST LINE: `1ST` i 11 cyfr. Śledzisz go na firstline56.com lub w Peek.

### Kto doręcza paczki FIRST LINE w USA?

Amerykański przewoźnik: strony FIRST LINE wymieniają USPS, DHL eCommerce i FedEx, a obserwowane historie doręczały USPS, GOFO lub UniUni. Tracker FIRST LINE przekazuje ich skany bez podawania nazw.

### Dlaczego tracker FIRST LINE nie znajduje mojego numeru 1ST?

Numer może mieć literówkę, nie być jeszcze zarejestrowany lub należeć do innego spedytora z grupy 1ST. Sprawdź go i zapytaj sprzedawcę, która firma przewozi przesyłkę.

### Ile trwa doręczenie FIRST LINE do USA?

FIRST LINE podaje sprzedawcom 7 do 16 dni od drzwi do drzwi lotniczo i 19 do 31 dni drogą morską. To dane ofertowe, a nie termin dla Twojego zamówienia.

:::sources
- [FIRST LINE: 壹号专线 (strona główna)](http://www.firstline56.com/) – pole śledzenia, chińska strona, adres w stopce
- [FIRST LINE: 追踪单号 (śledzenie)](http://www.firstline56.com/track.php) – tracker otwierany ze strony głównej
- [FIRST LINE: 公司简介 (o firmie)](http://www.firstline56.com/aboutus.php) – nazwa firmy, spedytor z Shenzhen, linie do USA i Kanady
- [FIRST LINE: 联系我们 (kontakt)](http://www.firstline56.com/contact.php) – centrum obsługi, adres biura
- [FIRST LINE: 美国小包 (linia paczek USA)](http://www.firstline56.com/business.php?id=4) – trasa, przewoźnicy lokalni, nazwy na platformach, czasy i obszar, DDP, zwroty, reklamacje
- [FIRST LINE: 美国海运小包 (linia morska USA)](http://www.firstline56.com/business.php?id=5) – Matson, FedEx i USPS, 19–31 dni
- [FIRST LINE: 加拿大小包 (linia Kanada)](http://www.firstline56.com/business.php?id=6) – Vancouver lub Toronto, Canada Post, terminy, DDP
- [FIRST LINE: 欧洲空派 (linia lotnicza Europa)](http://www.firstline56.com/yhzx.php?id=4) – Praga, UPS, kraje, minimum 12 kg, podatki w cenie
- [1ST: Business](https://www.1st56.com/group/groupbusiness/en/) – cztery spółki grupy, angielska nazwa FIRST LINE
- [一代国际集团: 关于我们 (o grupie)](https://www.1st56.com/group/aboutus/) – spółki grupy, formularz skarg
- [一代国际集团: 常见问题 (FAQ)](https://www.1st56.com/group/commonproblems/) – rynek USA, skany tego samego dnia, numer lokalny po 6–9 dniach, reklamacje, protokoły szkody, obszar USA
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/first-line) – format numeru, wspólne listy grupy, statusy, przewoźnicy i dane odczytywane przez Peek
:::
