---
title: Śledzenie przesyłek Delhivery: numery AWB, statusy i pomoc
description: Śledź paczkę Delhivery w Indiach lub za granicę: jak wygląda numer AWB, co oznaczają statusy takie jak RTO, nieudane próby, odprawa celna w UE i wsparcie.
slug: sledzenie-delhivery
published: 2026-11-21
updated: 2026-11-21
---

Założona w 2011 r. firma Delhivery (*Delhivery Limited*) określa się jako największy w pełni zintegrowany operator logistyczny w Indiach: doręcza zamówienia ze sklepów i platform handlowych do ponad `18 850` kodów PIN oraz wysyła paczki z Indii do przeszło `220` krajów i terytoriów. Przesyłkę sprawdzisz na [stronie śledzenia Delhivery](https://www.delhivery.com/tracking) po numerze AWB, numerze zamówienia (`Order Id`), numerze `LRN` lub numerze telefonu komórkowego — albo wklejając sam numer AWB w polu u góry strony.

## Numer śledzenia Delhivery (AWB): jak wygląda

Delhivery nazywa swój numer śledzenia **AWB** (*airway bill number*, numer listu przewozowego). Oto formaty AWB rozpoznawane przez Peek (z przykładowymi, zmyślonymi cyframi):

| Numer (zmyślony) | Co oznacza |
| --- | --- |
| `1482910573642` | **13 cyfr**: numer AWB Delhivery. Trzynaście cyfr stosują również m.in. [Econt](carrier:econt), DPD France, FAN Courier czy Korea Post. |
| `14829105736492` | **14 cyfr**: także numer AWB Delhivery — z tej samej długości korzystają [DPD](carrier:dpd-de), [SEUR](carrier:seur), [BRT](carrier:brt) czy Xpressbees. |

Sama liczba cyfr nie przesądza więc o przewoźniku: sprawdź w SMS-ie lub e-mailu od sklepu, z kim nadano zamówienie. Na `delhivery.com/tracking` wyszukasz paczkę również po `Order Id`, `LRN` (fracht B2B) albo zalogujesz się numerem telefonu i kodem `OTP`, aby zobaczyć wszystkie swoje przesyłki; w Peek potrzebny jest 13- lub 14-cyfrowy **AWB** ([jak znaleźć numer śledzenia](guide:find-tracking-number)).

## Co oznaczają statusy śledzenia Delhivery

Wyszukiwarka Delhivery łączy pasek postępu ze spisem dotychczasowych skanów i miast. Oto komunikaty oraz kody odczytywane przez Peek:

| Status Delhivery | Co oznacza dla Twojej paczki | Peek pokazuje |
| --- | --- | --- |
| `WAITING_PICKUP` | Przesyłka zgłoszona, ale jeszcze nieodebrana od nadawcy (bez skanu widoczna jako `Waiting pickup`). | „Zarejestrowana” |
| `Picked Up` | Delhivery odebrało przesyłkę od nadawcy (również kamień milowy we frachcie bez pojedynczych skanów). | „Przyjęta przez przewoźnika” |
| `PROD_REPLACED` | Przy zwrotnym odbiorze wymienianego towaru kurier odebrał zastępowany produkt. | „Przyjęta przez przewoźnika” |
| `In Transit`, `IN_TRANSIT`, `On the Way` | W drodze przez sieć Delhivery (`IN_TRANSIT` jako kod, `On the Way` jako etap frachtowy). | „W transporcie” |
| `Dispatched`, `Out for Delivery` | Wydana do doręczenia (`Dispatched` w skanie paczki, `Out for Delivery` na pasku postępu). | „W doręczeniu” |
| `Delivered` | Doręczona (w obiegu zwrotnym oznacza oddanie przesyłki z powrotem sprzedawcy). | „Dostarczona” (lub „Zwrócona nadawcy”) |
| `Undelivered` | Niedoręczona i skierowana z powrotem do sprzedawcy; we frachcie Delhivery przemianowuje tak etap `On the Way` z jego pierwotną datą, więc data nie oznacza próby doręczenia. | „Zgłoszono problem” |
| `REACHED_SEL_CITY` | Zwracana przesyłka dotarła do miasta sprzedawcy. | „W transporcie” |
| `RTO`, `DELIVERED_SELLER` | *Return to Origin*: niedoręczona paczka wróciła do sprzedawcy. | „Zwrócona nadawcy” |
| `DTO` | Zwrot konsumencki nadany przez Ciebie dotarł do sprzedawcy. | „Zwrócona nadawcy” |
| `LOST` | Delhivery oznaczyło przesyłkę jako zagubioną. | „Zgłoszono problem” |
| `CANCELLED`, `Canceled` | Przesyłka lub zlecenie odbioru zwrotu zostało anulowane. | „Zgłoszono problem” |

Więcej o etapach podróży paczki znajdziesz w poradniku [co oznaczają statusy śledzenia](guide:tracking-statuses).

## Doręczenia w Indiach: telefony, nieobecność i adres

**Sieć.** Delhivery dysponuje `50` zautomatyzowanymi sortowniami, `123` węzłami przeładunkowymi i ponad `4 500` bezpośrednimi centrami doręczeń we wszystkich indyjskich stanach, obsługując `99,5 %` kodów PIN (w tym północno-wschodnie Indie oraz Dżammu i Kaszmir) przez całą dobę, 365 dni w roku.

**Telefony i WhatsApp.** Zgodnie z regulaminem kurierzy i partnerzy Delhivery mogą dzwonić, pisać SMS-y oraz kontaktować się przez WhatsApp (rozmowy mogą być nagrywane), by ustalić doręczenie. Dla sklepów dostępny jest system `Smart NDR` z połączeniami AI i potwierdzeniami na WhatsAppie, jednak Delhivery **nigdy** nie prosi o kod `OTP` ani hasła w celu potwierdzenia adresu.

**Nieudane próby i zasady Direct.** W usłudze dla osób prywatnych *Delhivery Direct* (do `50 kg` wagi rozliczeniowej, w trybie ekspresowym maks. `10 kg` i `50 cm` boku; brak skrytek pocztowych) po **3 nieudanych próbach doręczenia** zlecenie jest anulowane bez zwrotu opłaty (zwrot paczki możliwy na koszt nadawcy). Za zgodą Delhivery można wskazać sąsiada, skrzynkę/automat lub odbiór w zatwierdzonym punkcie, przy czym zmiana kodu PIN może spotkać się z odmową ([nieudane doręczenie](guide:missed-delivery)). Przy zakupach w sklepie obowiązują warunki sprzedawcy.

## Paczki z Indii za granicę i odprawa celna

**Czas dostawy i partnerzy.** W usłudze **DLV Saver** Delhivery szacuje `5–7` dni do Wielkiej Brytanii i `8–12` dni do USA (obejmuje USA, Wielką Brytanię, Kanadę i Australię), natomiast **DLV Premium** dociera do ponad `220` krajów i terytoriów w `3–6` dni (*Express*) lub `6–10` dni (*Deferred Express*), już od `50 g`. Wśród partnerów zagranicznych Delhivery wymienia [FedEx](carrier:fedex) i Aramex — jeśli sklep przekaże osobny numer dla ostatniego odcinka, dodaj go również w Peek.

**Odprawa celna w Polsce i Unii Europejskiej.** W **Polsce** (całe terytorium kraju należy do obszaru celnego i obszaru VAT UE) zakupy internetowe wysyłane spoza UE o wartości do `150 €` objęte są od 1 lipca 2026 r. tymczasowym cłem `3 €` za pozycję towarową (towary o tej samej klasyfikacji taryfowej, opisie i — gdy wymagane — pochodzeniu; powyżej `150 €` obowiązuje zwykła taryfa celna), a od 1 listopada 2026 r. także unijną opłatą manipulacyjną `2 €` za pozycję niezależnie od wartości, plus VAT importowy — chyba że VAT pobrano już przy zakupie w procedurze IOSS ([paczka zatrzymana przez urząd celny](guide:customs)).

## Brak aktualizacji, zagubienie, uszkodzenie lub zwrot paczki

W sprawie zwrotu pieniędzy lub ponownej wysyłki zamówienia ze sklepu internetowego kontaktuj się w pierwszej kolejności ze sprzedawcą.

- **Brak ruchu:** status „Zarejestrowana” (`WAITING_PICKUP`) oznacza, że sklep nie przekazał jeszcze paczki kurierowi. Jeśli przesyłka utknęła w trasie, zaloguj się numerem telefonu na `delhivery.com/support` lub w aplikacji i wyślij zgłoszenie ([dlaczego śledzenie się nie aktualizuje](guide:tracking-not-updating)).
- **Zagubiona, uszkodzona lub doręczona pod zły adres:** w *Delhivery Direct* reklamacje z tytułu utraty, uszkodzenia lub błędnego doręczenia trzeba zgłosić ticketem w aplikacji w ciągu **3 dni** (tego samego dnia kalendarzowego, jeśli uszkodzony dotarł karton kupiony od Delhivery), dołączając przy szkodzie **3 zdjęcia** opakowania zewnętrznego i zawartości z opisem (zalecane nagranie wideo z otwierania). Roszczenia są rozpatrywane w **30–45 dni roboczych**, a bez płatnej ochrony *Delhivery Protect* (obowiązkowej od `10 000 ₹` do limitu Direct `49 999 ₹`; w e-commerce do `30 000 ₹` na AWB) odpowiedzialność wynosi maksymalnie **`2 000 ₹`** z limitami kategorii (`500 ₹` książki/dokumenty/inne, `700 ₹` odzież, `800 ₹` dom, `1 000 ₹` elektronika lub wiele przedmiotów, `2 000 ₹` sport, `400 ₹` artykuły zużywalne), bez odszkodowań za opóźnienie ([zagubiona paczka](guide:lost-parcel), [uszkodzona paczka](guide:damaged-parcel), [dostarczona, ale nieotrzymana](guide:delivered-not-received)).
- **Zwrot (`RTO`, `DELIVERED_SELLER`):** przesyłka wróciła do sprzedawcy — poproś sklep o zwrot środków lub ponowne nadanie ([zwrot do nadawcy](guide:returned-to-sender)).

## Obsługa klienta Delhivery i ostrzeżenia przed oszustwami

- **Wsparcie wyłącznie przez stronę lub aplikację:** Delhivery informuje, że dział obsługi klienta jest dostępny **tylko** na [stronie pomocy](https://www.delhivery.com/support) (logowanie numerem telefonu i kodem `OTP` w `Raise a Query`; sekcje `Report Issues`, `Grievance Officer`, `Report on-field staff issues`, `Returns`) albo w aplikacji *Delhivery Direct*.
- **Brak publicznej infolinii:** numery telefonów znajdowane w innych miejscach w sieci mogą należeć do oszustów. Oficjalne wiadomości przychodzą wyłącznie z domeny `@delhivery.com`, a w mediach społecznościowych Delhivery pomaga tylko z oficjalnych profili na Facebooku, X i Instagramie.
- **Fałszywe SMS-y:** uważaj na wiadomości o rzekomo nieudanym doręczeniu prowadzące do podrobionych domen (`delhiveryu.top`, `delhiveryma.top`); Delhivery nigdy nie żąda kodu `PIN` ani `OTP` do płatności ani kodu `OTP` czy haseł do potwierdzenia adresu. W Indiach przy stracie finansowej zadzwoń pod `1930` lub zgłoś sprawę na `cybercrime.gov.in` (a podejrzane połączenia/SMS-y w ciągu 3 dni na `sancharsaathi.gov.in`); zobacz też [fałszywe SMS-y o paczkach](guide:delivery-scam-texts).

## Śledzenie przesyłek Delhivery w Peek

Wklej 13- lub 14-cyfrowy numer AWB w [Peek](/): ponieważ układ ten dzieli wielu przewoźników, Peek wyświetla „Sprawdzanie usług śledzenia…” i automatycznie odpytuje Delhivery, gdy łączysz się z Indii, a numer zaczyna się od `1`–`9` i kończy na `0`–`6` („Delhivery ma tę przesyłkę.”). Spoza Indii — albo przy numerze zaczynającym się od `0` bądź kończącym na `7`, `8` lub `9` — stuknij „Zmień” i wskaż Delhivery. Przy innej liczbie cyfr ręczny wybór pokaże, że przewoźnik „nie wydaje takich numerów”.

Przed pierwszym skanem widnieje „Oczekiwanie na przewoźnika Delhivery”. Peek odczytuje publiczny kanał śledzenia bez logowania: pokazuje wykonane skany z miastem (pomijając przyszłe kroki na pasku postępu i dane odbiorcy), ustala rok etapów frachtowych na podstawie dnia tygodnia, odczytuje czas indyjski (`Asia/Kolkata`, `UTC+5:30`) i przelicza go na strefę Twojego urządzenia („Godziny w Twojej strefie czasowej: …”), bez szacowanej daty dostawy. Paczka wydana z powrotem sprzedawcy ma status „Zwrócona nadawcy”, a nie „Dostarczona”.

Przy otwartej paczce lub w dniu dostawy z włączonymi powiadomieniami Peek sprawdza status nawet co 10 minut, a od `06:00` czasu indyjskiego w dniu skanu „W doręczeniu” nawet co 2 minuty, dopóki odpowiada wyszukiwarka Delhivery; poza tym i po 12 godzinach na etapie „Zarejestrowana” / „Przyjęta przez przewoźnika” lub dwóch dniach bez zmian — co godzinę („Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”). Peek nie skontaktuje się za Ciebie z Delhivery ani ze sklepem.

## Najczęstsze pytania o śledzenie Delhivery

### Jak śledzić paczkę Delhivery?

Wklej 13- lub 14-cyfrowy numer AWB u góry strony w Peek (spoza Indii wybierz Delhivery przyciskiem „Zmień”) albo wyszukaj przesyłkę na `delhivery.com/tracking` po AWB, `Order Id`, `LRN` lub numerze telefonu z kodem `OTP`.

### Czym jest numer AWB w Delhivery?

To *airway bill number* — 13- lub 14-cyfrowy numer listu przewozowego Delhivery podawany w SMS-ie lub e-mailu potwierdzającym wysyłkę ze sklepu.

### Co oznacza status `RTO` w Delhivery?

*Return to Origin*: paczka zawróciła i została wydana z powrotem sprzedawcy. Skontaktuj się ze sklepem po zwrot pieniędzy lub ponowną wysyłkę.

### Jaki jest numer telefonu do obsługi klienta Delhivery?

Delhivery nie udostępnia telefonicznej infolinii — kontakt ze wsparciem odbywa się wyłącznie po zalogowaniu numerem telefonu na `delhivery.com/support` lub w aplikacji Delhivery, a inne numery krążące w sieci mogą być fałszywe.

:::sources
- [Delhivery: Track your order](https://www.delhivery.com/tracking) – śledzenie po telefonie, AWB, Order Id lub LRN oraz ostrzeżenia przed phishingiem
- [Delhivery: Support](https://www.delhivery.com/support) – logowanie kodem OTP, zgłoszenia i sekcje w tym Grievance Officer
- [Delhivery: About Us](https://www.delhivery.com/about-us) – założenie w 2011 r., ponad 18 850 kodów PIN, 50 sortowni i ponad 4 500 centrów doręczeń 24/7
- [Delhivery: Strona główna](https://www.delhivery.com/) – ponad 220 krajów i terytoriów oraz partnerzy strategiczni FedEx i Aramex
- [Delhivery: Express Parcel](https://www.delhivery.com/services/express-parcel) – zasięg 99,5 % kodów PIN, Smart NDR przez WhatsApp/AI i ochrona przesyłek e-commerce
- [Delhivery: International](https://www.delhivery.com/services/international) – czasy dostawy DLV Saver i DLV Premium od 50 g oraz cła i podatki
- [Delhivery: Personal Courier](https://www.delhivery.com/solutions/personal-courier) – przesyłki prywatne Delhivery Direct
- [Delhivery: Terms and Conditions](https://www.delhivery.com/terms-and-conditions) – połączenia, SMS-y i wiadomości WhatsApp dotyczące doręczenia
- [Delhivery: TnC (Direct National Courier)](https://www.delhivery.com/tnc-direct) – limity wagi, 3 próby, reklamacje w 3 dni z 3 zdjęciami, rozpatrzenie w 30–45 dni roboczych i limity do 2 000 ₹ / Delhivery Protect
- [Delhivery: Fraud Disclaimer](https://www.delhivery.com/fraud-disclaimer) – oficjalne kanały @delhivery.com, fałszywe SMS-y, brak OTP do płatności, linia 1930 i cybercrime.gov.in
- [Komisja Europejska: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – cło 3 € do 150 € od lipca 2026 r. i opłata 2 € od listopada 2026 r.
- [Komisja Europejska: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – definicja pozycji, opłata 2 € niezależnie od wartości i procedura IOSS
- [Komisja Europejska: Terytorialny zakres stosowania VAT i ceł](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Polska w obszarze celnym i obszarze VAT UE
- [Universal Parcel Scraper: Delhivery](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/delhivery) – 13- i 14-cyfrowe numery AWB, 17 statusów i kodów, etapy frachtowe, zwroty i strefa Asia/Kolkata
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – inni przewoźnicy z numerami 13- i 14-cyfrowymi
:::
