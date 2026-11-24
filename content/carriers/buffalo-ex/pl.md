---
title: Śledzenie BUFFALO: kody BUFZA, cło w RPA i kontakt
description: Śledź przesyłkę BUFFALO International Logistics z Chin do RPA: kody BUFZA...YQ, skany VAT i cła SARS, godziny doręczeń i kontakt do obsługi.
slug: sledzenie-buffalo-ex
published: 2026-11-24
updated: 2026-11-24
---

**BUFFALO** (*BUFFALO International Logistics*, często znana jako *Buffaloex* lub *Buffalo Express*) to firma transgranicznej logistyki e-commerce na trasie Chiny–Afryka działająca od kwietnia 2017 roku ([buffaloex.com](https://www.buffaloex.com/)). Ma siedzibę w Szanghaju oraz afrykański oddział w Johannesburgu. Jej zintegrowane magazyny zajmują 35 000 metrów kwadratowych (w tym magazyn celny o powierzchni 6 500 m²), a firma obsługuje 300 własnych pojazdów. BUFFALO podaje, że jej systemy są zintegrowane z systemami celnymi i bankowymi RPA oraz że ma największy udział w rynku małych paczek e-commerce z Chin do Republiki Południowej Afryki. Przesyłkę sprawdzisz na [buffaloex.com/tracks.html](https://www.buffaloex.com/tracks.html) albo wklejając numer `BUFZA` w pole powyżej.

## Numery śledzenia BUFFALO (format `BUFZA...YQ`)

Transgraniczne przesyłki e-commerce z Chin do RPA obsługiwane przez BUFFALO noszą 17-znakowy kod kreskowy zaczynający się od `BUFZA` i kończący na `YQ`:

| Format (wymyślony) | Co to jest |
| --- | --- |
| `BUFZA6482917305YQ` | Standardowy 17-znakowy numer BUFFALO Chiny–RPA (`BUFZA` + 10 cyfr + `YQ`). |
| `BUFZA4SK73918BUYQ` | Alternatywny 17-znakowy kod przesyłki transgranicznej `BUFZA...BU...YQ`. |

Strona kontaktowa BUFFALO kieruje numery zamówień zaczynające się od `BUFEX-` do ZA-BUFFALO. Jeśli zgubiłeś numer śledzenia, FAQ BUFFALO zaleca poprosić o niego sprzedawcę lub zalogować się na swoje konto BUFFALO. Zobacz [formaty numerów śledzenia](guide:tracking-number-formats).

## Co oznaczają statusy śledzenia BUFFALO

Według [pomocy BUFFALO](https://www.buffaloex.com/support.html) i strumienia zdarzeń BUFFALO przesyłki przechodzą przez magazyn w Chinach, lot eksportowy, opłacenie podatków celnych w RPA, magazyny w Johannesburgu/regionalne i doręczenie lokalne:

| Komunikat BUFFALO | Co oznacza dla Ciebie | Peek pokazuje |
| --- | --- | --- |
| `Order Confirmed` | Zamówienie utworzone, gdy paczka jest jeszcze u sprzedawcy w drodze do magazynu BUFFALO w Chinach. | „Zarejestrowana” |
| `Enter Warehouse` | Paczka dotarła do magazynu BUFFALO w Chinach. | „Przyjęta przez przewoźnika” |
| `Out of Warehouse`, `Air Shipping`, `International Shipping` | Opuściła magazyn BUFFALO w Chinach i została wysłana do RPA frachtem lotniczym lub morskim. | „W transporcie” |
| `Clearance`, `At Destination`, `Arrival at South Africa customs`, `Customs VAT and duty can be paid now, please pay in time`, `Tax Paid`, `Clearance finished` | Odprawa eksportowa w Chinach przed wylotem lub odprawa importowa w RPA; VAT importowy i cło muszą zostać opłacone przed zwolnieniem paczki. | „W urzędzie celnym” |
| `Inbound`, `AIR DC Received the parcel`, `BUFFALO Airport Warehouse received`, `Transit the parcel from AIRDC to JHBDC`, `JHB DC Received the parcel`, `Dispatching`, `WAYBILL IMPORTED`, `Transit to thirdparty Express`, `RETURNS` | Odprawiona i w drodze między magazynem lotniskowym (`AIR DC`) a oddziałem w Johannesburgu (`JHB DC`), przekazana zewnętrznemu kurierowi w RPA albo zwrócona do magazynu między trasami. | „W transporcie” |
| `Driver Pick the Order`, `SCAN VEHICLE DEPARTURE` | Wydana kierowcy BUFFALO lub lokalnego kuriera do dzisiejszego doręczenia. | „W doręczeniu” |
| `Returned to BUFFALO warehouse[...]` | Wróciła do magazynu BUFFALO wskazanego w nawiasach po nieudanej próbie doręczenia przez lokalnego kuriera; postępuj zgodnie z SMS-em lub e-mailem od BUFFALO albo skontaktuj się z obsługą klienta. | „Zgłoszono problem” |
| `Client Received` | Doręczona pod Twój adres lub odebrana w punkcie. | „Dostarczona” |

## VAT i cło w RPA, godziny doręczeń i zmiany adresu

Według [pomocy BUFFALO](https://www.buffaloex.com/support.html) oraz [O BUFFALO](https://www.buffaloex.com/about.html):

- **VAT importowy i cło:** gdy śledzenie pokazuje `Customs VAT and duty can be paid now, please pay in time`, trzeba opłacić VAT i cło, zanim służba celna RPA zwolni paczkę. Filmy BUFFALO o VAT i cle wyjaśniają płatność online lub przelewem bankowym oraz przesłanie potwierdzenia, a formularz kontaktowy obsługuje pytania o podatki oraz dokumenty tożsamości lub kody importera. Zobacz [przesyłki zatrzymane na cle](guide:customs).
- **Godziny i czas dostawy:** BUFFALO doręcza w całej RPA **od poniedziałku do soboty w godz. 08:00–17:00** (bez doręczeń w niedziele i święta). Gdy paczka widnieje w transporcie, BUFFALO zakłada doręczenie w ciągu **1–5 dni**, zależnie od adresu odbiorcy.
- **Zmiana adresu, telefonu lub wartości celnej:** według FAQ BUFFALO zadeklarowanej wartości, liczby sztuk i kodu HS **nie można** zmienić po przyjęciu paczki do magazynu tranzytowego w Chinach, a adresu dostawy ani numeru telefonu **nie można** zmienić po przybyciu paczki do RPA.

## Przesyłka BUFFALO opóźniona, zwrócona do magazynu lub zaginiona

- **Brak aktualizacji śledzenia:** FAQ BUFFALO wyjaśnia, że przesyłka jest w drodze i nie dotarła jeszcze do kolejnego punktu kontrolnego; obsługa klienta może podać szczegóły. Zobacz [dlaczego śledzenie przestaje się aktualizować](guide:tracking-not-updating).
- **Wieczorny skan `RETURNS`:** w skanach lokalnego kuriera przekazywanych przez usługi śledzenia `RETURNS` pojawia się czasem wieczorem po `SCAN VEHICLE DEPARTURE`: paczka wróciła do magazynu po trasie i wyruszyła ponownie kolejnego ranka. Natomiast `Returned to BUFFALO warehouse[...]` oznacza nieudane doręczenie i wymaga kontaktu.
- **Status `Client Received`, ale brak paczki:** BUFFALO zaleca sprawdzić najpierw u domowników, sąsiadów i ochrony osiedla lub budynku, a potem napisać do obsługi klienta w RPA (`support@buffaloex.com`). Zobacz [doręczona, ale nieotrzymana](guide:delivered-not-received), [zaginiona przesyłka](guide:lost-parcel), [uszkodzona przesyłka](guide:damaged-parcel) i [zwrócona nadawcy](guide:returned-to-sender).

## Obsługa klienta i kontakt BUFFALO

Oficjalne dane kontaktowe ze strony [Kontakt BUFFALO](https://www.buffaloex.com/contact.html):

- **Obsługa klienta w RPA:** `support@buffaloex.com`, telefon `+27-10-510-9307`, WhatsApp `+27-67-758-6614`.
- **Obsługa klienta w Chinach:** `service@bufflogistics.com`, infolinia `+86-400-921-9066`.
- **Formularz zapytań i FAQ statusów:** [buffaloex.com/contact.html](https://www.buffaloex.com/contact.html) i [buffaloex.com/support.html](https://www.buffaloex.com/support.html).
- **Biura:** siedziba w Szanghaju (Room 301, Building A, Lane 1156, Shenbin South Road, Minhang District, Szanghaj), biuro w Kantonie (Room 401, Building 3, Site 5, Greenland Airport International Center, Huadu District, Kanton) oraz oddział w Johannesburgu w RPA.

> Uważaj na fałszywe SMS-y podszywające się pod służbę celną RPA lub BUFFALO z podobnymi linkami. Zawsze sprawdzaj należność VAT/cła względem swojego statusu na [buffaloex.com/tracks.html](https://www.buffaloex.com/tracks.html) przed zapłatą. Zobacz [fałszywe SMS-y o przesyłkach](guide:delivery-scam-texts).

## Śledzenie przesyłki BUFFALO w Peek

[Peek](/) wykrywa BUFFALO automatycznie jako jedynego kandydata po wpisaniu 17-znakowego kodu `BUFZA...YQ` (`BUFZA6482917305YQ` lub `BUFZA4SK73918BUYQ`, wymyślone), a także rozpoznaje wklejone linki `buffaloex.com/tracks.html?order=`, `buffaloex.com/track.html?order=` i `buffaloex.com/express/record?order=`. Jeśli musisz wybrać BUFFALO ręcznie, stuknij „Zmień” podczas dodawania lub „Zmień przewoźnika” na ekranie przesyłki (wyszukanie „Buffaloex”, „Buffalo Express” albo „BUFFALO International” także go znajduje).

Peek nie ma bezpośredniego połączenia z BUFFALO i śledzi paczki BUFFALO przez zewnętrzne usługi śledzenia. Stosuje jednak własne reguły słownictwa BUFFALO, więc wieczorne `RETURNS` pozostaje „W transporcie”, `SCAN VEHICLE DEPARTURE` pokazuje „W doręczeniu”, a `Returned to BUFFALO warehouse[...]` pokazuje „Zgłoszono problem”; każdy skan wyświetla się wraz z etapem („Zarejestrowana”, „Przyjęta przez przewoźnika”, „W transporcie”, „W urzędzie celnym”, „W doręczeniu”, „Zgłoszono problem”, „Dostarczona”).

Gdy Peek śledzi przesyłkę przez zewnętrzne usługi śledzenia, sprawdza status co godzinę, co 3 godziny po dniu bez nowego skanu i do co 15 minut w dniu, gdy paczka jest w doręczeniu, dopóki masz przesyłkę otwartą lub włączone powiadomienia.

Kiedy najnowszy skan ma cztery dni, a przesyłka nadal stoi na „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie”, Peek oznacza ją dniem tego skanu, np. „Brak aktualizacji od pon., 12 paź”. Na stronie przesyłki wyświetla się wtedy „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika BUFFALO.” oraz link „Poproś przewoźnika BUFFALO o pomoc” do strony kontaktowej BUFFALO. Po trzech tygodniach bez nowego skanu pojawia się komunikat „Od ponad trzech tygodni nic nowego. Jeśli przesyłka wciąż nie dotarła, zapytaj sprzedawcę. Peek dalej pyta przewoźnika BUFFALO.”

Możesz wybrać powiadomienia dla opcji „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek jedynie monitoruje aktualizacje śledzenia i nie może opłacić cła w SARS, przesłać kodów importera ani skontaktować się z BUFFALO za Ciebie.

## Pytania o śledzenie BUFFALO

### Jak wygląda numer śledzenia BUFFALO?

Numery śledzenia BUFFALO z Chin do RPA mają 17 znaków, zaczynają się od `BUFZA` i kończą na `YQ` — na przykład `BUFZA6482917305YQ` lub `BUFZA4SK73918BUYQ` (wymyślone).

### Kiedy BUFFALO doręcza przesyłki w RPA?

Według [buffaloex.com/support.html](https://www.buffaloex.com/support.html) BUFFALO doręcza od poniedziałku do soboty w godzinach **08:00–17:00** (z wyjątkiem niedziel i świąt), przewidując dostawę w **1–5 dni** od pojawienia się paczki w transporcie.

### Dlaczego moja paczka BUFFALO utknęła w urzędzie celnym w RPA?

Jeśli śledzenie pokazuje `Customs VAT and duty can be paid now, please pay in time`, należy opłacić VAT importowy i cło przed zwolnieniem przesyłki przez urząd celny RPA. Formularz BUFFALO przyjmuje pytania dotyczące płatności podatków oraz dokumentów tożsamości lub kodów importera.

### Jak skontaktować się z obsługą klienta BUFFALO w RPA?

Napisz na `support@buffaloex.com` z numerem `BUFZA...YQ` albo wyślij zapytanie przez [buffaloex.com/contact.html](https://www.buffaloex.com/contact.html) (infolinia w Chinach `+86-400-921-9066`).

:::sources
- [BUFFALO International Logistics: oficjalna strona](https://www.buffaloex.com/) – transgraniczna sieć logistyczna Chiny–Afryka
- [BUFFALO: o nas](https://www.buffaloex.com/about.html) – działa od kwietnia 2017 r., siedziba Szanghaj, oddział Johannesburg, 35 000 m² magazynów (6 500 m² magazyn celny), 300 pojazdów, integracja celna i bankowa
- [BUFFALO: wsparcie i FAQ śledzenia](https://www.buffaloex.com/support.html) – oficjalne znaczenia statusów (`Order Confirmed` do `Client Received`), godziny pon.–sob. 08:00–17:00, czas 1–5 dni i ograniczenia zmian adresu
- [BUFFALO: kontakt](https://www.buffaloex.com/contact.html) – `support@buffaloex.com`, `service@bufflogistics.com`, `+86-400-921-9066` oraz adresy biur/magazynów (URL pomocy linkowany przez Peek)
- [BUFFALO: śledzenie paczek](https://www.buffaloex.com/tracks.html) – oficjalna strona śledzenia
- [Universal Parcel Scraper: buffalo-ex](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/buffalo-ex) – reguła wykrywania `BUFZA...YQ`, reguły linków `buffaloex.com`, katalog statusów (`RETURNS`, `SCAN VEHICLE DEPARTURE`, `Returned to BUFFALO warehouse`), uniwersalne trasowanie i URL pomocy
:::
