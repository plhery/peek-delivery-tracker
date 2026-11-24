---
title: Śledzenie DTDC: formaty AWB, statusy RTO i pomoc
description: Śledź paczkę DTDC w Indiach i na świecie: numery AWB N, 7D i 7X, skany sortowni, zwroty RTO, czasy WorldWyde oraz obsługa klienta.
slug: sledzenie-dtdc
published: 2026-11-24
updated: 2026-11-24
---

DTDC (*DTDC Express Limited*), założony 26 lipca 1990 roku z siedzibą w Bengaluru, to jedna z największych sieci logistyki ekspresowej w Indiach. Dzięki ponad 16 500 partnerom docierającym do 96% ludności Indii, usłudze Express Parcel obejmującej ponad 15 300 kodów pocztowych, lokalnej obecności w 7 krajach oraz zasięgowi międzynarodowemu do ponad 220 kierunków DTDC obsługuje krajowe przesyłki ekspresowe, doręczenia następnego dnia *Express Premium*, *AirMax*, *Raftaar*, 3PL, fracht LTL i wysyłki transgraniczne *DTDC WorldWyde*. Przesyłkę sprawdzisz na [stronie śledzenia DTDC](https://www.dtdc.com/track-your-shipment/) lub w [MyDTDC](https://web.mydtdc.in/), albo wklejając numer AWB powyżej.

## Numery śledzenia DTDC (formaty AWB)

DTDC wysyła numer Air Waybill (AWB) e-mailem i SMS-em po potwierdzeniu zlecenia. Krajowe i międzynarodowe numery AWB DTDC mają następujące typowe formaty alfanumeryczne:

| Format (zmyślony) | Co to jest |
| --- | --- |
| `N48291736` | Standardowy 9-znakowy numer przesyłki DTDC (`N` + 8 cyfr). |
| `7D482917364` | 11-znakowy numer przesyłki DTDC (`7D` + 9 cyfr). |
| `7X482917364` | 11-znakowy numer przesyłki DTDC (`7X` + 9 cyfr). |

DTDC wydaje też 9- i 11-znakowe kody zaczynające się od innych pojedynczych liter (takich jak `B`, `D`, `H`, `P`, `T`, `U`, `V` lub `X` z 8 albo 10 cyframi), które pokrywają się z innymi przewoźnikami regionalnymi i mogą wymagać ręcznego wyboru DTDC, gdy automatyczne rozpoznawanie pokaże kilku kandydatów. Na [dtdc.com/track-your-shipment/](https://www.dtdc.com/track-your-shipment/) możesz przełączać między **Shipment Number** (AWB) a **Reference Number**. Inne wzorce w Indiach i na świecie znajdziesz w [formatach numerów przesyłek](guide:tracking-number-formats).

## Co oznaczają statusy śledzenia DTDC

DTDC publikuje etapy i ruchy między węzłami (np. `Arrived at DELHI`, `Dispatched from MATHURA` czy `Received at ... Processing Centre`) w indyjskim czasie standardowym (`Asia/Kolkata`, UTC+5:30).

| DTDC pokazuje | Co to oznacza dla ciebie | Peek pokazuje |
| --- | --- | --- |
| `Booked`, `Booked at Origin Branch`, `Pickup Awaited`, `Pickup Scheduled`, `Pickup requested`, `Not picked` | List przewozowy zarejestrowany elektronicznie lub w punkcie; oczekiwanie na odbiór fizyczny lub przyjęcie w hubie. | „Zarejestrowana” |
| `Picked Up`, `Pickup Completed` | DTDC odebrał paczkę od nadawcy. | „Przyjęta przez przewoźnika” |
| `In Transit`, `On The Way`, `At Processing Centre`, `At Destination`, `Reattempt Initiated`, `Arrived at ...`, `Dispatched from ...` | W drodze między sortowniami DTDC, dotarła do centrum docelowego lub została zaplanowana do ponownej próby. | „W transporcie” |
| `Out For Delivery` | Załadowana u lokalnego kuriera do doręczenia pod drzwi dziś. | „W doręczeniu” |
| `Undelivered` | Próba doręczenia nie powiodła się (np. nieobecność odbiorcy lub problem z adresem). | „Próba doręczenia” |
| `RTO Booked`, `Return Approval awaited`, `Return Initiated` / `Return - Initiated` | Uruchomiono zwrot do nadawcy (RTO). | „Zgłoszono problem” |
| `Delivered` (etap docelowy) | Doręczona odbiorcy. | „Dostarczona” |
| `Delivered` (na etapie `RTO`) | Zwrócona i oddana pierwotnemu nadawcy. | „Zwrócona nadawcy” |

**Uważaj na zwroty `RTO`.** Gdy przesyłki nie można doręczyć, DTDC uruchamia odcinek Return To Origin (`RTO`), który otrzymuje własny skan `Delivered` po powrocie paczki do nadawcy. Sprawdź, czy najnowszy odcinek jest docelowy czy `RTO`, zanim uznasz, że paczka trafiła do ciebie.

## Krajowe i międzynarodowe usługi DTDC

Według [DTDC Express Parcel](https://www.dtdc.com/express-parcel/), [DTDC International Shipping](https://www.dtdc.com/international-shipping/), [About Us](https://www.dtdc.com/about-us/) oraz [FAQ DTDC](https://www.dtdc.com/faq/):

- **Usługi krajowe w Indiach:** *Express Parcel* (codzienne doręczenia z określoną datą w ponad 15 300 kodach pocztowych z pobraniem, uwierzytelnianiem doręczenia i logistyką zwrotów), *Express Premium* (pilne następnego dnia), *AirMax*, *Raftaar*, *Personal Courier* oraz B2B *3PL* / *LTL*.
- **Niedziele i święta:** [FAQ DTDC](https://www.dtdc.com/faq/) zaznacza, że część lokalnych placówek DTDC działa w niedziele i święta zgodnie ze swoimi godzinami otwarcia.
- **Czasy międzynarodowe (*DTDC WorldWyde*):** ponad 220 kierunków z własną odprawą celną i cyfrowym KYC/CSB-V — *WorldWyde Express Premium* (**4–5 dni**), *WorldWyde Express Standard* (**7–8 dni**), *WorldWyde Imports Standard* (**10–12 dni**) i *WorldWyde B2C Standard* (**15–20 dni**). Rzeczywisty czas zależy także od cła w kraju docelowym; zobacz [przesyłki zatrzymane w urzędzie celnym](guide:customs).

Strony usług DTDC nie podają stałej liczby prób doręczenia ani okresu przechowywania. Nieudana próba ma status `Undelivered`, a kolejna `Reattempt Initiated`.

## Paczka DTDC opóźniona, niedoręczona lub zagubiona

Przy zakupach online skontaktuj się najpierw ze sprzedawcą, aby mógł zatwierdzić poprawkę adresu, nowy termin lub zwrot — szczególnie gdy śledzenie pokazuje `Return Approval awaited` lub `RTO Booked`.

- **Zmiana adresu, terminu lub odbiór własny:** na [Track Your Shipment](https://www.dtdc.com/track-your-shipment/) możesz zgłosić spór, uruchomić czat lub poprosić o oddzwonienie, a formularz [Customer Care](https://www.dtdc.com/customer-care/) przyjmuje wnioski o zmianę adresu lub telefonu, nową datę doręczenia lub odbiór własny.
- **Status `Delivered`, ale brak paczki:** najpierw sprawdź, czy nie jest to zwrot `RTO` do nadawcy; jeśli to doręczenie do ciebie, zapytaj domowników, sąsiadów i ochronę budynku, a następnie od razu zgłoś spór w DTDC Customer Care i u sprzedawcy.
- Zobacz [dlaczego śledzenie przestaje się aktualizować](guide:tracking-not-updating), [dostarczona, ale nieodebrana](guide:delivered-not-received), [uszkodzona paczka](guide:damaged-parcel), [zagubiona paczka](guide:lost-parcel) i [zwrócona nadawcy](guide:returned-to-sender).

## Obsługa klienta i oficjalne kontakty DTDC

Kontakty opublikowane na [DTDC Customer Care](https://www.dtdc.com/customer-care/) oraz [dtdc.com](https://www.dtdc.com/in/):

- **Telefon obsługi klienta:** `+91-9606 911 811`.
- **E-mail wsparcia:** `customersupport@dtdc.com`.
- **Wsparcie online:** formularz zgłoszeń i sporów na [dtdc.com/customer-care/](https://www.dtdc.com/customer-care/), wirtualny asystent **DIVA** na stronie oraz aplikacja mobilna **MyDTDC**.

> DTDC wyraźnie ostrzega na [dtdc.com](https://www.dtdc.com/in/), że **`www.dtdc.com` jest jedyną oficjalną stroną DTDC**, i zrzeka się odpowiedzialności za oferty, numery lub płatności dokonywane przez fałszywe serwisy. Nigdy nie płać ani nie podawaj kodu OTP przez nieznany link. Zobacz [fałszywe SMS-y o przesyłce](guide:delivery-scam-texts).

## Śledzenie paczki DTDC w Peek

[Peek](/) automatycznie rozpoznaje `N` + 8 cyfr (`N48291736`, zmyślony) oraz `7D` / `7X` + 9 cyfr (`7D482917364`, `7X482917364`) jako DTDC z wysoką pewnością, a także wklejone linki `dtdc.com` z parametrami `trackingId=` lub `awb=`. Dla 9- lub 11-znakowych kodów z innymi początkowymi literami stuknij „Zmień” podczas dodawania lub „Zmień przewoźnika” na ekranie paczki i wybierz DTDC.

Peek odpytuje DTDC w dwóch bezpośrednich krokach — najpierw anonimowy strumień MyDTDC, a w razie potrzeby stronę śledzenia `dtdc.com`. Wyświetla etap („Zarejestrowana”, „Przyjęta przez przewoźnika”, „W transporcie”, „W doręczeniu”, „Próba doręczenia”, „Zgłoszono problem”, „Zwrócona nadawcy”, „Dostarczona”), opisy skanów DTDC, lokalizację hubu lub oddziału oraz czasy w strefie Indii (`Asia/Kolkata`) przeliczone na czas twojego urządzenia. Co ważne, gdy DTDC oznaczy odcinek `RTO` jako `Delivered`, Peek mapuje go na „Zwrócona nadawcy” zamiast „Dostarczona”. Peek odrzuca adresy uliczne, numery telefonów/kontakty, metadane nadania i nieopisane wartości wagi; tracker DTDC nie podaje przewidywanej daty ani danych punktu odbioru.

Gdy masz paczkę otwartą w Peek albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza status nawet co 10 minut; w dniu, gdy DTDC pokaże `Out For Delivery`, co 2 minuty od 6:00 czasu indyjskiego, dopóki tracker DTDC odpowiada. Poza tym sprawdza co godzinę. Po 12 godzinach wyłącznie na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika”, albo po dwóch dniach bez zmian, sprawdza co godzinę niezależnie od sposobu śledzenia, a po siedmiu dniach na etapie „Zarejestrowana” — co 6 godzin. Gdy tracker DTDC nie odpowiada i Peek korzysta wyłącznie z zewnętrznych usług śledzenia, sprawdza co godzinę, co 3 godziny po dniu ciszy i do co 15 minut w dniu doręczenia, gdy masz paczkę otwartą lub masz włączone powiadomienia.

Gdy najnowszy skan ma cztery dni, a przesyłka nadal widnieje jako „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie”, Peek oznacza ją dniem tego skanu, np. „Brak aktualizacji od pon., 12 paź”. Na stronie paczki pojawia się wtedy napis: „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika DTDC.”, wraz z linkiem „Poproś przewoźnika DTDC o pomoc” do strony Customer Care DTDC. Po trzech tygodniach bez nowego skanu widnieje komunikat: „Od ponad trzech tygodni nic nowego. Jeśli przesyłka wciąż nie dotarła, zapytaj sprzedawcę. Peek dalej pyta przewoźnika DTDC.”

Możesz wybrać powiadomienia: „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek tylko monitoruje śledzenie — nie zmieni terminu doręczenia DTDC, nie poprawi adresu ani nie zgłosi sporu za ciebie.

## Pytania o śledzenie DTDC

### Jak wygląda numer śledzenia (AWB) DTDC?

Typowe numery przesyłek DTDC mają 9 znaków zaczynających się od `N` (`N48291736`, zmyślony) lub 11 znaków zaczynających się od `7D` albo `7X` (`7D482917364`, `7X482917364`), choć DTDC stosuje też inne jednoliterowe prefiksy o długości 9 i 11 znaków.

### Co oznaczają `RTO Booked` lub `Return Initiated` w śledzeniu DTDC?

`RTO` oznacza Return To Origin (zwrot do nadawcy). Oznacza to, że paczki nie udało się doręczyć (lub poproszono o akceptację zwrotu) i wraca do nadawcy. Od razu skontaktuj się ze sprzedawcą, jeśli nadal chcesz otrzymać zamówienie.

### Ile trwa przesyłka międzynarodowa DTDC?

Na [dtdc.com/international-shipping/](https://www.dtdc.com/international-shipping/) DTDC podaje 4–5 dni dla *WorldWyde Express Premium*, 7–8 dni dla *Express Standard*, 10–12 dni dla *Imports Standard* oraz 15–20 dni dla *B2C Standard*, z zastrzeżeniem odprawy celnej.

### Jak skontaktować się z obsługą klienta DTDC?

Zadzwoń pod `+91-9606 911 811`, napisz na `customersupport@dtdc.com` albo skorzystaj z formularza i asystenta DIVA na [dtdc.com/customer-care/](https://www.dtdc.com/customer-care/).

:::sources
- [DTDC: Home](https://www.dtdc.com/in/) – komunikat o jedynej oficjalnej stronie (`www.dtdc.com`), wsparcie DIVA i przegląd usług
- [DTDC: About Us](https://www.dtdc.com/about-us/) – założony 26 lipca 1990 r., centrala w Bengaluru, ponad 16 500 partnerów, zasięg 96% ludności Indii, 7 krajów, ponad 220 kierunków i ponad 5,6 mln użytkowników MyDTDC
- [DTDC: Track Your Shipment](https://www.dtdc.com/track-your-shipment/) – wyszukiwanie AWB / referencji, powiadomienia AWB przez SMS/e-mail, spory, czat i oddzwonienie
- [DTDC: Express Parcel](https://www.dtdc.com/express-parcel/) – ponad 15 300 kodów pocztowych, pobranie, uwierzytelnianie doręczenia i logistyka zwrotna
- [DTDC: International Shipping (WorldWyde)](https://www.dtdc.com/international-shipping/) – ponad 220 kierunków, czasy WorldWyde (4–5, 7–8, 10–12, 15–20 dni) oraz odprawa CSB-V/KYC
- [DTDC: FAQ](https://www.dtdc.com/faq/) – praca oddziałów w niedziele/święta i pytania o śledzenie
- [DTDC: Customer Care](https://www.dtdc.com/customer-care/) – `+91-9606 911 811`, `customersupport@dtdc.com`, formularz online do zmiany adresu, daty i odbioru własnego (adres pomocy linkowany przez Peek)
- [Portal MyDTDC](https://web.mydtdc.in/) – portal śledzenia MyDTDC
- [Universal Parcel Scraper: dtdc](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dtdc) – rozpoznawanie wysokiej pewności `N`/`7D`/`7X`, reguły linków `dtdc.com`, dwuetapowe odpytywanie, obsługa zwrotów RTO, strefa `Asia/Kolkata`, odrzucane pola i adres pomocy
:::
