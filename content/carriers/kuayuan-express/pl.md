---
title: Śledzenie Kuayuan Express: kody, przekazania i pomoc
description: Śledź przesyłkę Kuayuan Express (跨源速递) z Chin do USA lub Europy: 14-znakowy numer, kody skanów (AF, ND, CC), przekazania lokalne i kontakty Shenzhen.
slug: sledzenie-kuayuan-express
published: 2026-11-24
updated: 2026-11-24
---

Kuayuan Express (*Shenzhen Kuayuan Express Supply Chain Co., Ltd.*, po chińsku *跨源速递* / *深圳市跨源速递供应链有限公司*) to operator logistyki e-commerce transgranicznego z Shenzhen, skupiony na liniach paczkowych door-to-door z Chin do Europy i Stanów Zjednoczonych. Na [kuayuanexpress.com.cn](https://www.kuayuanexpress.com.cn/) Kuayuan obsługuje odbiór pierwszej mili i konsolidację w Chinach, transport lotniczy i morski, zautomatyzowaną odprawę celną według kodów HS (kanały DDP z opłaconymi podatkami) oraz doręczenie ostatniej mili przez lokalnych partnerów lub własną flotę. Na [kuayuanexpress.com.cn](https://www.kuayuanexpress.com.cn/) sprawdzisz do 20 listów przewozowych naraz albo wkleisz swój 14-znakowy kod Kuayuan w pole powyżej.

## Numery śledzenia Kuayuan Express (14-znakowy format)

Transgraniczne kody paczek Kuayuan Express mają 14 znaków: jedną cyfrę (zwykle `3`), trzy wielkie litery i 10 cyfr (zapisujących jednocyfrowy rok, miesiąc, dzień oraz 5-cyfrowy numer seryjny):

| Format (wymyślony) | Co to jest |
| --- | --- |
| `3KAA6101548291` | 14-znakowy list przewozowy Kuayuan Express (`3` + 3 litery + data `YMMDD` + 5-cyfrowa seria). |
| `3KBC6102873914` | Inny 14-znakowy transgraniczny kod paczki Kuayuan Express. |

Kuayuan może podawać obok własnego 14-znakowego numeru również numer linii, której przekazuje paczkę (`server_hawbcode`), np. kod kreskowy [DPD Niemcy](carrier:dpd-de). Zobacz [formaty numerów przesyłek](guide:tracking-number-formats).

## Co oznaczają statusy śledzenia Kuayuan Express

System śledzenia Kuayuan łączy krótkie wielkoliterowe kody skanów z angielskimi opisami obejmującymi magazyny w Chinach, eksportową odprawę celną, przekazanie linii lotniczej oraz przesyłane dalej zdarzenia przewoźnika docelowego:

| Kod Kuayuan | Co to oznacza dla Ciebie | Peek pokazuje |
| --- | --- | --- |
| `AF` | Dotarła do centrum operacyjnego lub sortowni Kuayuan. | „W transporcie” |
| `DF` | Zakończono sortowanie i obsługę wychodzącą w centrum operacyjnym. | „W transporcie” |
| `ECKSBG` | Złożono zgłoszenie celne eksportowe w Chinach. | „W transporcie” |
| `ECYDG` | Dotarła do portu lub lotniskowego hubu wylotowego. | „W transporcie” |
| `JH` | Przekazana międzynarodowej linii lotniczej lub morskiej. | „W transporcie” |
| `ND` | W transporcie na przekazanej linii międzynarodowej lub u lokalnego doręczyciela ostatniej mili. | „W transporcie” |
| `CF` | Próba doręczenia pod adresem odbiorcy nie powiodła się. | „Próba doręczenia” |
| `UE` | Paczka zwrócona nadawcy po nieudanym doręczeniu lub odmowie odbioru. | „Zwrócona nadawcy” |
| `CC` | Pomyślnie doręczona i pokwitowana w miejscu docelowym. | „Dostarczona” |

Skany `ND` pochodzą bezpośrednio od linii przejmującej w jej języku: po angielsku dla DPD Niemcy, po chińsku dla China Post (`中国邮政已收取邮件`). Skan z innym kodem albo bez kodu bierze swój etap z treści opisu.

## Czasy dostawy, cło i przekazania lokalnym partnerom

Według [kuayuanexpress.com.cn](https://www.kuayuanexpress.com.cn/):

- **Deklarowane czasy tranzytu:** Kuayuan podaje **7–10 dni** door-to-door na swojej linii lotniczej towarów ogólnych do USA oraz **7–10 dni** do głównych krajów europejskich ze stałym rozkładem lotów i lokalną obsługą.
- **Kanały ładunków specjalnych:** oprócz towarów ogólnych Kuayuan przewozi elektronikę z bateriami, kosmetyki/płyny i suplementy dedykowanymi zgodnymi kanałami.
- **Odprawa celna i DDP:** Kuayuan oferuje kanały podwójnej odprawy z opłaconymi podatkami (DDP) do Europy i USA z automatycznym doborem kodów HS. Dla przesyłek nienadanych w formule DDP nadal mogą obowiązywać importowy VAT i cło (w tym unijne opłaty za zamówienia spoza UE). Zobacz [przesyłki zatrzymane w urzędzie celnym](guide:customs).
- **Lokalne doręczenie:** po odprawie importowej zagraniczny zespół Kuayuan przekazuje paczkę lokalnemu partnerowi, np. [DPD Niemcy](carrier:dpd-de), albo doręcza ją własnymi pojazdami. Lokalny przewoźnik ustala próby doręczenia, punkty odbioru i ponowne doręczenie.

## Przesyłka Kuayuan Express zatrzymana, bez skanów lub zaginiona

Ponieważ konto wysyłkowe w Kuayuan Express należy do sklepów i sprzedawców transgranicznych, **zawsze najpierw kontaktuj się ze sprzedawcą lub platformą** w sprawie zmiany adresu, zwrotu pieniędzy czy ponownej wysyłki.

- **Cisza między Chinami a krajem docelowym:** śledzenie często zatrzymuje się na kilka dni między `JH` (przekazanie linii lotniczej) a pierwszym skanem `ND` w kraju docelowym podczas przelotu i zbiorczej odprawy celnej. Zobacz [dlaczego śledzenie przestaje się aktualizować](guide:tracking-not-updating).
- **Śledź kod przekazanego przewoźnika:** gdy Kuayuan powiąże numer lokalnego partnera, sprawdzenie go bezpośrednio często pokazuje przedziały doręczenia, kody odbioru w punkcie lub opcje zmiany terminu.
- **Nieudane doręczenie, brak lub uszkodzenie:** jeśli śledzenie pokazuje `CF` (nieudana próba), `UE` (zwrot) lub `CC` (dostarczona) bez fizycznej paczki, sprawdź najpierw u sąsiadów i u lokalnego przewoźnika, a potem otwórz spór ze sprzedawcą przed końcem ochrony kupujących. Zobacz [dostarczona, ale nieodebrana](guide:delivered-not-received), [zaginiona przesyłka](guide:lost-parcel), [uszkodzona przesyłka](guide:damaged-parcel) oraz [zwrócona nadawcy](guide:returned-to-sender).

## Obsługa klienta i kontakty Kuayuan Express

Oficjalne dane kontaktowe opublikowane na [kuayuanexpress.com.cn](https://www.kuayuanexpress.com.cn/):

- **Nazwa firmy:** *Shenzhen Kuayuan Express Supply Chain Co., Ltd.* (*深圳市跨源速递供应链有限公司*, Guangdong ICP `粤ICP备2025475526号`).
- **Telefon:** `+86 15013702902`.
- **E-mail:** `fanjinyun@kuayuanexpress.com`.
- **Publiczna wyszukiwarka:** [kuayuanexpress.com.cn](https://www.kuayuanexpress.com.cn/) (obsługuje do 20 numerów na raz).

> Uważaj na fałszywe SMS-y żądające drobnej opłaty celnej lub za ponowne doręczenie pod nieznanym linkiem. Wszelkie prawdziwe opłaty importowe sprawdzaj na stronie zamówienia w sklepie lub na oficjalnej stronie lokalnego przewoźnika. Zobacz [fałszywe SMS-y o doręczeniu](guide:delivery-scam-texts).

## Śledzenie przesyłki Kuayuan Express w Peek

[Peek](/) automatycznie sprawdza Kuayuan Express jako jedynego kandydata po wpisaniu 14-znakowego listu przewozowego Kuayuan zaczynającego się od `3` (`3KAA6101548291` lub `3KBC6102873914`, wymyślone) i pokazuje „Kuayuan Express ma tę przesyłkę.”, gdy Kuayuan go potwierdzi. Jeśli Twój kod Kuayuan zaczyna się od innej cyfry albo musisz wybrać przewoźnika ręcznie, stuknij „Zmień” podczas dodawania lub „Zmień przewoźnika” na ekranie przesyłki (wyszukanie „跨源速递” lub „Kuayuan” też go znajduje).

Peek odczytuje tracker Kuayuan po angielsku i wyświetla etap („W transporcie”, „Próba doręczenia”, „Zwrócona nadawcy”, „Dostarczona” albo inny etap, gdy skan bez żadnego z dziewięciu kodów tak wskazuje), opis skanu, miejscowość i kraj docelowy. Krótkie kody skanów nie pojawiają się w żadnej aplikacji Peek. Na Twojej własnej stronie przesyłki Peek pokazuje imię i nazwisko odbiorcy w polu „Odbiorca” i podczas podróży paczki może zakończyć trasę na mapie w miejscowości odbiorcy; udostępniane linki nie pokazują żadnej z tych informacji. Kuayuan podaje godziny skanów bez strefy: Peek przypisuje każdy skan do strefy czasowej jego miejsca (Los Angeles dla skanu w Los Angeles, Pekin dla Shenzhen), skan z nieznanym miejscem do strefy sąsiedniego skanu, a paczkę bez żadnego miejsca do czasu pekińskiego, wyświetlając wszystko według zegara Twojego urządzenia. Gdy Kuayuan poda numer przejmującego przewoźnika, a Peek go rozpozna, sprawdza, czy ma tę przesyłkę: karta pokazuje wtedy „Doręcza:” z nazwą przewoźnika i oba numery. Tracker Kuayuan nie podaje szacowanej daty doręczenia, wagi ani własnej godziny doręczenia.

Gdy masz przesyłkę otwartą w Peek albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza status nawet co 10 minut; w dniu, gdy Kuayuan pokazuje skan wyjścia do doręczenia, co 2 minuty od godz. 6:00 czasu miejsca najnowszego skanu, dopóki własny tracker Kuayuan odpowiada. W pozostałym czasie Peek sprawdza co godzinę. Po 12 godzinach wyłącznie na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika” albo po dwóch dniach bez zmian sprawdza co godzinę niezależnie od sposobu śledzenia, a po siedmiu dniach na „Zarejestrowana” — co 6 godzin. Gdy tracker Kuayuan nie odpowiada i Peek śledzi przesyłkę wyłącznie przez usługi śledzące, sprawdza co godzinę, co 3 godziny po spokojnym dniu i nawet co 15 minut w dniu doręczenia, dopóki masz ją otwartą lub włączone powiadomienia.

Jeśli najnowszy skan ma cztery dni, a przesyłka nadal widnieje jako „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie”, Peek oznacza ją dniem tego skanu, np. „Brak aktualizacji od pon., 12 paź”. Na stronie przesyłki pojawia się wtedy komunikat „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika Kuayuan Express.” Po trzech tygodniach bez nowego skanu pojawia się informacja „Od ponad trzech tygodni nic nowego. Jeśli przesyłka wciąż nie dotarła, zapytaj sprzedawcę. Peek dalej pyta przewoźnika Kuayuan Express.”

Możesz wybrać powiadomienia dla opcji „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek śledzi wyłącznie aktualizacje statusu i nie może odprawić przesyłki celnie, zmienić adresu doręczenia ani skontaktować się za Ciebie z Kuayuan Express lub lokalnym przewoźnikiem.

## Pytania o śledzenie Kuayuan Express

### Czym jest Kuayuan Express (*跨源速递*)?

Kuayuan Express (*Shenzhen Kuayuan Express Supply Chain Co., Ltd.*) to transgraniczny operator logistyczny e-commerce z Shenzhen, prowadzący dedykowane linie lotnicze i morskie z Chin do Europy i Stanów Zjednoczonych.

### Jak wygląda numer śledzenia Kuayuan Express?

Listy przewozowe Kuayuan Express mają 14 znaków: jedną cyfrę (zwykle `3`), trzy wielkie litery i 10 cyfr — na przykład `3KAA6101548291` (wymyślony).

### Co oznaczają kody `AF`, `JH`, `ND` i `CC` w Kuayuan Express?

`AF` oznacza przybycie do centrum operacyjnego Kuayuan, `JH` przekazanie linii lotniczej, `ND` transport na linii docelowej, a `CC` doręczenie przesyłki.

### Jak skontaktować się z Kuayuan Express?

W sprawach zamówienia skontaktuj się najpierw ze sprzedawcą lub platformą. Kuayuan Express podaje `+86 15013702902` i `fanjinyun@kuayuanexpress.com` na [kuayuanexpress.com.cn](https://www.kuayuanexpress.com.cn/).

:::sources
- [Kuayuan Express: oficjalna strona i tracker](https://www.kuayuanexpress.com.cn/) – *Shenzhen Kuayuan Express Supply Chain Co., Ltd.*, wyszukiwarka 20 numerów, trasy USA/Europa 7–10 dni, cło DDP, telefon `+86 15013702902` i e-mail `fanjinyun@kuayuanexpress.com`
- [Universal Parcel Scraper: kuayuan-express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/kuayuan-express) – 14-znakowy format listu przewozowego, API `getTrackInfoOutside`, 9 kodów skanów (`AF`–`UE`), przekazanie partnerowi `server_hawbcode` i przypisywanie strefy czasowej miejsca każdemu skanowi
:::
