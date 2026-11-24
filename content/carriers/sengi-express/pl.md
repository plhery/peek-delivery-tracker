---
title: Śledzenie Sengi Express: kody Chiny–Włochy i pomoc
description: Śledź przesyłkę Sengi Express (grupa Poste Italiane) z Chin do Włoch lub Europy: kody SDA/MT, cło, przekazania Crono, opóźnienia i kontakty.
slug: sledzenie-sengi-express
published: 2026-11-24
updated: 2026-11-24
---

**Sengi Express** (*Guangzhou Sengi Logistics Co., Ltd.* / *Seng Yi (Shenzhen) Global Supply Chain Management Co., Ltd.*, po chińsku *广州森吉物流有限公司*) to operator logistyki transgranicznej z większościowym udziałem **Poste Italiane** ([portal.sengiexpress.com/about/](https://portal.sengiexpress.com/about/)). Specjalizując się w bezpośrednim wprowadzaniu przesyłek e-commerce z Chin do Włoch i Europy, Sengi obsługuje konsolidację w Chinach, fracht lotniczy i morski, europejską odprawę celną B2C/B2B w bramach takich jak Mediolan-Malpensa (**MXP**), Amsterdam (**AMS**), Liège (**LGG**) i Paryż (**CDG**), ogólnoeuropejski transport drogowy przez Sennder (grupa Poste Italiane) oraz końcowe doręczenie pod drzwi lub do automatów we Włoszech w sieci Crono [Poste Italiane](carrier:poste-italiane). Przesyłkę sprawdzisz na [portal.sengiexpress.com](https://portal.sengiexpress.com/home) lub wklejając kod w pole powyżej.

## Numery śledzenia Sengi Express (formaty SDA `...C...F` i `MT`)

Sengi Express często wystawia już w Chinach 13-znakowy list przewozowy **Poste Italiane / SDA**, dzięki czemu ten sam kod działa od chińskiego magazynu Sengi aż po doręczenie we Włoszech, obok 16-znakowych kodów transgranicznych zakończonych na `MT`:

| Format (wymyślony) | Co to jest |
| --- | --- |
| `482C73918462F` | 13-znakowy list przewozowy SDA / Poste Italiane (`3 cyfry` + `C` + `8 cyfr` + `F`) śledzony przez Sengi Express, zanim Poste Italiane zeskanuje go we Włoszech. |
| `SENGI482917360MT` | 16-znakowy transgraniczny kod Sengi (`5 liter` + `9 cyfr` + `MT`). |

Sengi nadaje paczce do Włoch jej list przewozowy SDA, zanim SDA go zna, dlatego wyszukiwarka Poste Italiane może z początku nie znajdować numeru; tracker Sengi śledzi przesyłkę od magazynu w Chinach do odbiorcy we Włoszech. Po ręcznym wyborze tracker Sengi przyjmuje również inne alfanumeryczne referencje partnerów o długości od 8 do 40 znaków. Zobacz [formaty numerów przesyłek](guide:tracking-number-formats).

## Co oznaczają statusy śledzenia Sengi Express

Sengi Express publikuje angielskie (i chińskie) opisy skanów transgranicznych wraz z opcjonalnymi kodami zdarzeń oraz znacznikami czasu:

| Sengi Express podaje | Co to oznacza dla Ciebie | Peek pokazuje |
| --- | --- | --- |
| `Order information received. We're expecting your parcel to arrive with us.` (kod `10`) | Sprzedawca przekazał dane elektroniczne przed fizycznym przyjęciem w chińskim magazynie Sengi. | „Zarejestrowana” |
| `Arrived at Destinated Airport`, `Terminal Notification of Arrival` | Lot międzynarodowy wylądował na europejskim lotnisku docelowym (takim jak MXP, AMS, LGG lub CDG). | „W transporcie” |
| `Arrived at customs clearance warehouse` | Paczka trafiła do europejskiego magazynu odprawy celnej. | „W urzędzie celnym” |
| `Customs clearance inspection, Reason: supposed as undervaluation, waiting for customs check(VM)` | Wytypowana przez europejskie służby celne do weryfikacji zadeklarowanej wartości lub zawartości (`VM`). | „W urzędzie celnym” |
| `Ready for transit to last mile operator` | Odprawiona celnie i oczekuje na przekazanie [Poste Italiane](carrier:poste-italiane) lub lokalnemu kurierowi. | „W transporcie” |

Skan z innym opisem przejmuje etap z tego opisu. Sengi informuje, że odbiorcy mogą śledzić przesyłkę także na stronie [Poste Italiane](carrier:poste-italiane) pod tym samym kodem `...C...F` lub pod nowym numerem, gdy Sengi przemetkuje paczkę.

## Bezpośrednie wprowadzanie, europejskie bramy celne i doręczenie Poste Italiane

Według [O Sengi Express](https://portal.sengiexpress.com/about/) i [Produktów Sengi Express](https://portal.sengiexpress.com/product/):

- **Europejskie bramy lotnicze:** Sengi prowadzi bezpośrednią odprawę B2C i B2B w Mediolanie-Malpensa (`MXP`), Amsterdamie (`AMS`), Liège (`LGG`) i Paryżu Charles de Gaulle (`CDG`).
- **Usługi ostatniej mili Poste Italiane:** we Włoszech Sengi wprowadza paczki do usług **Crono** (standard), **Crono Express**, **Extralarge** (ponadgabaryt / ostatnia mila FBA), **Crono ADP** (automaty paczkowe) oraz **Crono Reverse** (zwroty).
- **Kontrole wartości celnej (`VM`) i opłaty UE:** przesyłki z Chin do UE podlegają unijnym przepisom celnym, importowemu VAT (IOSS lub przy odbiorze) oraz unijnym opłatom celnym i manipulacyjnym dla zamówień spoza UE. Gdy pojawia się `supposed as undervaluation, waiting for customs check(VM)`, służby celne weryfikują zadeklarowaną wartość z importerem lub agentem — poczekaj na zwolnienie lub zawiadom sprzedawcę. Zobacz [przesyłki zatrzymane w urzędzie celnym](guide:customs).

## Przesyłka Sengi Express zatrzymana, bez skanów lub zaginiona

Ponieważ umowę przewozową z Sengi Express mają sklepy i chińscy sprzedawcy, **zawsze najpierw kontaktuj się ze sprzedawcą lub platformą** w sprawie zwrotu pieniędzy, ponownej wysyłki lub dokumentów wartości celnej.

- **Cisza przed włoskimi skanami:** przesyłki często pauzują na kilka dni między `Order information received`, `Arrived at Destinated Airport` a `Ready for transit to last mile operator`, gdy partie lotnicze przechodzą europejską odprawę celną i jadą ciężarówkami do Włoch. Zobacz [dlaczego śledzenie przestaje się aktualizować](guide:tracking-not-updating).
- **Śledź w Poste Italiane po przekazaniu:** po pojawieniu się `Ready for transit to last mile operator` dla włoskiej przesyłki `PI` ten sam numer `...C...F` (albo nowy przemetkowany numer pokazany w Peek) w [Poste Italiane](carrier:poste-italiane) pokazuje włoskie sortownie, próby doręczenia oraz dane Punto Poste i automatów.
- **Zaginiona, uszkodzona lub oznaczona jako dostarczona:** sprawdź najpierw u sąsiadów i w lokalnej placówce Poste Italiane / Punto Poste, a potem otwórz spór na platformie przed końcem ochrony kupujących. Zobacz [dostarczona, ale nieodebrana](guide:delivered-not-received), [zaginiona przesyłka](guide:lost-parcel), [uszkodzona przesyłka](guide:damaged-parcel) oraz [zwrócona nadawcy](guide:returned-to-sender).

## Obsługa klienta i kontakty Sengi Express

Oficjalne kontakty opublikowane na [O Sengi Express](https://portal.sengiexpress.com/about/) i [Kontakt Sengi Express](https://portal.sengiexpress.com/contact/):

- **E-mail:** `service@sengiexpress.com`.
- **Formularz kontaktowy online:** [portal.sengiexpress.com/contact/](https://portal.sengiexpress.com/contact/).
- **Operator:** *Guangzhou Sengi Logistics Co., Ltd.* (*广州森吉物流有限公司*, ICP `粤ICP备2021101066号`), z większościowym udziałem Poste Italiane.

> Uważaj na fałszywe SMS-y twierdzące, że opłatę Poste Italiane lub celną trzeba uregulować pod nieznanym linkiem. Każdą rzeczywistą opłatę importową sprawdzaj na stronie zamówienia w sklepie albo na oficjalnej stronie Poste Italiane. Zobacz [fałszywe SMS-y o doręczeniu](guide:delivery-scam-texts).

## Śledzenie przesyłki Sengi Express w Peek

Gdy wpiszesz 13-znakowy kod `...C...F` (`482C73918462F`, wymyślony), [Peek](/) pyta najpierw [Poste Italiane](carrier:poste-italiane), potem AliExpress i Sengi Express (we Włoszech Sengi przed AliExpress; w Chinach najpierw AliExpress, potem Sengi) i wyświetla „Sengi Express ma tę przesyłkę.”, gdy obsługuje ją Sengi. Dla kodu `...MT` (`SENGI482917360MT`, wymyślony) Peek pyta [MaltaPost](carrier:maltapost), której numery również kończą się na `MT`: przy takim kodzie lub innym numerze partnera stuknij „Zmień” podczas dodawania albo „Zmień przewoźnika” na ekranie przesyłki i wybierz **Sengi Express** (wyszukanie „Sengi” też go znajduje).

Peek odczytuje tracker Sengi i wyświetla etap („Zarejestrowana”, „W transporcie”, „W urzędzie celnym” albo inny etap wynikający z treści skanu), angielski opis Sengi (lub chiński przy braku angielskiego) oraz miejscowość. Kody zdarzeń wyznaczają etap i nie pojawiają się w żadnej aplikacji Peek. Sengi podaje przesunięcie UTC dla skanów; skan bez przesunięcia otrzymuje strefę czasową swojego miejsca (Włochy dla `MXP`, Pekin dla Kantonu) lub sąsiedniego skanu, a Peek pokazuje je według zegara Twojego urządzenia. Na linii Poste Italiane firmy Sengi Peek sprawdza, czy Poste Italiane ma przesyłkę: ekran pokazuje wtedy „Doręcza:” z nazwą Poste Italiane i podaje nowy numer, gdy Sengi przemetkuje paczkę.

Gdy masz przesyłkę otwartą w Peek albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza status nawet co 10 minut; w dniu, gdy Sengi pokazałoby skan wyjścia do doręczenia (dotąd niezaobserwowany), co 2 minuty od godz. 6:00 czasu najnowszego skanu, dopóki tracker Sengi odpowiada. W pozostałym czasie Peek sprawdza co godzinę. Po 12 godzinach wyłącznie na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika” albo po dwóch dniach bez zmian sprawdza co godzinę niezależnie od sposobu śledzenia, a po siedmiu dniach na „Zarejestrowana” — co 6 godzin. Gdy tracker Sengi nie odpowiada i Peek śledzi przesyłkę wyłącznie przez usługi śledzące, sprawdza co godzinę, co 3 godziny po spokojnym dniu i nawet co 15 minut w dniu doręczenia, dopóki masz ją otwartą lub włączone powiadomienia.

Jeśli najnowszy skan ma cztery dni, a przesyłka nadal widnieje jako „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie”, Peek oznacza ją dniem tego skanu, np. „Brak aktualizacji od pon., 12 paź”. Na stronie przesyłki pojawia się wtedy komunikat „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika Sengi Express.” wraz z linkiem „Poproś przewoźnika Sengi Express o pomoc” do strony kontaktowej Sengi. Po trzech tygodniach bez nowego skanu pojawia się informacja „Od ponad trzech tygodni nic nowego. Jeśli przesyłka wciąż nie dotarła, zapytaj sprzedawcę. Peek dalej pyta przewoźnika Sengi Express.”

Możesz wybrać powiadomienia dla opcji „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek śledzi wyłącznie aktualizacje statusu i nie może odprawić przesyłki celnie, zmienić adresu doręczenia ani skontaktować się za Ciebie z Sengi Express lub Poste Italiane.

## Pytania o śledzenie Sengi Express

### Czym jest Sengi Express?

Sengi Express (*Guangzhou Sengi Logistics Co., Ltd.*) to kontrolowany przez **Poste Italiane** operator logistyki e-commerce Chiny–Włochy i Chiny–Europa, zajmujący się konsolidacją w Chinach, europejską odprawą celną (MXP, AMS, LGG, CDG) oraz wprowadzaniem paczek do sieci Crono Poste Italiane.

### Dlaczego mój kod Poste Italiane `...C...F` pojawia się najpierw w Sengi Express?

Sengi nadaje paczce do Włoch list przewozowy SDA / [Poste Italiane](carrier:poste-italiane) (`482C73918462F`, wymyślony), zanim SDA go otrzyma. Tracker Sengi pokazuje skany lotu i cła, podczas gdy strona Poste Italiane może z początku nie znajdować numeru.

### Co oznacza `supposed as undervaluation, waiting for customs check(VM)`?

Europejski urząd celny wytypował paczkę do kontroli zadeklarowanej wartości (`VM`). Dokumenty odprawy składa agent celny lub sprzedawca; skontaktuj się ze sprzedawcą, jeśli status się utrzymuje.

### Jak skontaktować się z obsługą klienta Sengi Express?

W sprawach zwrotu pieniędzy lub dokumentów celnych skontaktuj się najpierw ze sklepem lub platformą. Sengi Express podaje `service@sengiexpress.com` oraz formularz online na [portal.sengiexpress.com/contact/](https://portal.sengiexpress.com/contact/).

:::sources
- [Sengi Express: portal śledzenia](https://portal.sengiexpress.com/home) – publiczny tracker listów przewozowych i dane *Guangzhou Sengi Logistics Co., Ltd.*
- [Sengi Express: o nas](https://portal.sengiexpress.com/about/) – większościowy udział Poste Italiane, usługi transgraniczne i `service@sengiexpress.com`
- [Sengi Express: produkty i usługi](https://portal.sengiexpress.com/product/) – bramy celne MXP/AMS/LGG/CDG, Poste Italiane Crono / Crono Express / Extralarge / Crono ADP / Crono Reverse oraz transport drogowy Sennder w UE
- [Sengi Express: kontakt](https://portal.sengiexpress.com/contact/) – oficjalna strona kontaktowa dla odbiorców i klientów (adres pomocy linkowany przez Peek)
- [Universal Parcel Scraper: sengi-express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/sengi-express) – reguły wykrywania `...C...F` i `...MT`, parser `api-v2/tracking/web`, mapowanie statusów, obsługa przesunięcia UTC oraz przekazanie Poste Italiane `PI` / `relabelTrackingNumber`
:::
