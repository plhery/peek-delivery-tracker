---
title: Śledzenie Shiprocket: numery AWB, statusy i RTO
description: Śledź paczkę Shiprocket lub ShiprocketX: format AWB, znaczenie statusów, nieudane doręczenia (NDR), RTO i kontakt.
slug: sledzenie-shiprocket
published: 2026-11-22
updated: 2026-11-22
---

Shiprocket z siedzibą w Gurugram to indyjska platforma wysyłkowa, a nie firma kurierska: sprzedawcy internetowi nadają przez nią paczki u kurierów takich jak Delhivery, Blue Dart czy Xpressbees, a Shiprocket udostępnia kupującemu stronę śledzenia. ShiprocketX to jej usługa wysyłek z Indii za granicę. Przesyłkę sprawdzisz na [stronie śledzenia Shiprocket](https://www.shiprocket.in/shipment-tracking/) po numerze AWB, ID zamówienia lub numerze telefonu, przez link `shiprocket.co` od sprzedawcy albo w polu powyżej. Shiprocket podaje, że wysyła AWB e-mailem i SMS-em po potwierdzeniu zamówienia.

## Numer śledzenia Shiprocket

Shiprocket nazywa numer śledzenia kodem AWB (*air waybill number*). W Indiach jest to własny numer kuriera. Przesyłka ShiprocketX podróżuje pod własnym AWB Shiprocket: 13 cyfr zaczynających się od `832`. Shiprocket nie publikuje oficjalnego formatu. Z fikcyjnymi cyframi:

| Numer (fikcyjny) | Co oznacza |
| --- | --- |
| `8324829103756` | `832` i 10 kolejnych cyfr: AWB ShiprocketX. |
| `7394028156342` | 13 lub 14 cyfr: AWB krajowego kuriera w Indiach, np. Delhivery lub Xpressbees. |
| `74829103753` | 11 cyfr: kształt numeru AWB Blue Dart. |
| `FMPC4829103756` | Trzy litery, C, P lub R i 10 cyfr: AWB Ekart. |

**Gdzie go znaleźć.** Sprawdź e-mail i SMS wysłane przy potwierdzeniu zamówienia. Strona Shiprocket przyjmuje też ID zamówienia lub numer telefonu komórkowego, weryfikuje kod jednorazowy (OTP) i otwiera portal myShiprocket dla kupujących. Więcej miejsc w poradniku [jak znaleźć numer śledzenia](guide:find-tracking-number).

**Podobne numery.** Ponieważ listy przewozowe Delhivery również mogą mieć 13 cyfr, numer zaczynający się od `832` prawdopodobnie, ale nie na pewno, należy do Shiprocket. Strona Shiprocket odpowiada dla każdego kuriera zamówionego przez platformę, więc link `shiprocket.co/tracking/` od sprzedawcy działa niezależnie od kształtu numeru.

## Statusy śledzenia Shiprocket

Strona śledzenia pokazuje status, kuriera lub usługę obok AWB oraz skany od najnowszego — każdy z czynnością, często miejscowością, dniem, miesiącem i godziną, ale bez roku. Większość wpisów to oryginalne opisy kuriera: krajowego w Indiach lub przewoźnika docelowego za granicą. Częste wpisy po kolei:

| Strona Shiprocket podaje | Co oznacza dla ciebie |
| --- | --- |
| `Order Received` | Sprzedawca utworzył zamówienie. Żaden kurier nie ma jeszcze paczki. |
| `Manifested - Out for Pickup` | Kurier jedzie odebrać paczkę od sprzedawcy. |
| `In Transit - Shipment picked up` | Kurier odebrał paczkę. |
| `In Transit - Shipment Recieved at Origin Center` | W pierwszym hubie kuriera (pisownia oryginalna ze strony). |
| `Dispatched - Delivering At Shiprocket Warehouse` | ShiprocketX: kurier wiezie paczkę do hubu eksportowego Shiprocket, nie do ciebie. |
| `Received At DC` | Dotarła do hubu eksportowego Shiprocket. |
| `AWB Handed over to Flight` | Przekazana linii lotniczej. |
| `CUSTOM CLEARED OVERSEAS` | Zwolniona po odprawie celnej. |
| `Received by carrier` | Przejął ją przewoźnik w twoim kraju. |
| `RTO DELIVERED` | Zwrócona sprzedawcy. |

`Out For Pickup` i `PICKUP EMPLOYEE IS OUT TO P/U SHIPMENT` również oznaczają, że paczka nie została jeszcze odebrana. `Reached At Warehouse` i `Connection Aligned` to etapy w hubie eksportowym w Delhi. `Customer has made payment or Provided ID/Tax information` to krok celny za granicą (opłacone należności lub podane dane podatkowe; zobacz [przesyłki zatrzymane na cle](guide:customs)). Każdy wpis zaczynający się od `RTO` (*return to origin*) oznacza zwrot do sprzedawcy.

**Starsza paczka.** Po pewnym czasie strona pokazuje tylko widok archiwalny: ostatni status (`DELIVERED`, `FULFILLED` dla doręczonego i zamkniętego zamówienia lub `RTO DELIVERED`), dzień, usługę i AWB z ukrytymi ostatnimi znakami. Pełna historia jest widoczna tylko dla kupującego po kodzie wysłanym na telefon. Po około roku strona odpowiada `AWB Not Found`.

## Kto doręcza paczki Shiprocket

**W Indiach** kurier wybrany przez sprzedawcę. Shiprocket podaje współpracę z 42 kurierami, w tym Delhivery, Blue Dart, Xpressbees, Ekart, DTDC, Shadowfax, India Post i Amazon Shipping, oraz zaznacza, że czas dostawy zależy od kuriera, kodu PIN i usługi. Strona pokazuje nazwę usługi, np. `Delhivery Surface`.

**Za granicą** krajowy kurier dostarcza paczkę ShiprocketX do hubu eksportowego Shiprocket w Delhi; stamtąd leci samolotem, przechodzi odprawę celną i trafia do przewoźnika w kraju docelowym, nadal pod AWB Shiprocket. W odczytanych historiach w USA doręczał USPS. Strona podaje usługę, np. `SRX Economy` lub `SRX Premium`. Shiprocket podaje sprzedawcom 12–15 dni roboczych w opcji ekonomicznej do USA, Wielkiej Brytanii, Kanady i innych krajów oraz 10–12 dni z opłaconym cłem do USA — to dane ofertowe, nie gwarancja dla twojego zamówienia. W menu ShiprocketX bywa też nazywane „ShipX”.

## Nieudane doręczenie (NDR) i zwrot (RTO)

NDR (*non-delivery report*) oznacza nieudaną próbę doręczenia. Sprzedawca widzi go w Shiprocket po kilku minutach i decyduje, czy ponowić próbę, czy wycofać paczkę.

- **Wiadomości.** Jeśli sprzedawca korzysta z procedury NDR dla kupujących, status „customer requested future delivery” lub „customer refused the delivery” wysyła ci SMS — a w części planów połączenie automatyczne — z prośbą o potwierdzenie doręczenia. Sprzedawca może też wysłać przez Shiprocket link SMS-em, aby poprosić o kolejną próbę i poprawić adres lub numer telefonu.
- **Strona śledzenia.** Shiprocket podaje, że kupujący mogą tam poprosić o ponowną próbę i sprawdzić podany powód każdej próby.
- **Próba, której nie było.** Powiadom sprzedawcę — za twoim potwierdzeniem może zgłosić Shiprocket „fake attempt”.
- **Trzy próby.** Kurierzy podejmują do trzech prób. Potem zamówienie dostaje status RTO i wraca do magazynu lub adresu nadania sprzedawcy.

Gdy paczka wraca, poproś sprzedawcę o nową wysyłkę lub zwrot pieniędzy. Zobacz [nieudane doręczenie](guide:missed-delivery) i [zwrócona do nadawcy](guide:returned-to-sender).

## Paczka Shiprocket nie dociera lub śledzenie stoi

**Brak aktualizacji.** Shiprocket wyjaśnia, że kurierzy aktualizują status po zeskanowaniu paczki, co może potrwać kilka godzin; po ponad dobie bez zmian skontaktuj się ze sprzedawcą. Dopóki widnieje tylko `Order Received` lub wpis odbioru, kurier nie przejął jeszcze paczki. Więcej w [dlaczego śledzenie przestaje się aktualizować](guide:tracking-not-updating).

**Kto działa.** Klientem Shiprocket jest sprzedawca. Na pytanie „Whom shall I contact if I have concerns?” Shiprocket odpowiada, by skontaktować się ze sprzedawcą, u którego kupiłeś. Przy zaginięciu, uszkodzeniu lub niedoręczeniu także napisz do sprzedawcy: ochrona ShiprocketX do 5000 INR dotyczy sprzedawców. Zobacz [zagubiona paczka](guide:lost-parcel) i [dostarczona, ale nieotrzymana](guide:delivered-not-received).

## Infolinia i kontakt Shiprocket

Dane publikowane przez Shiprocket:

- **Telefon:** 9643887070, 9711451060 i 8510042020 (numery indyjskie, +91 z zagranicy). Strona śledzenia podaje je dla problemów ze śledzeniem wraz z czatem na żywo jako wsparcie 24/7; strona kontaktu podaje te same numery w godzinach 9:00–21:00 (czasu indyjskiego).
- **E-mail:** support@shiprocket.in.
- **Eskalacja:** wsparcie odpowiada w ciągu 5 dni roboczych. Jeśli sprawa pozostaje nierozwiązana, napisz na escalation@shiprocket.com, a potem na grievance@shiprocket.com — oba zespoły odpowiadają w ciągu 3 dni roboczych.

Większość tej pomocy kierowana jest do sprzedawców. Strona śledzenia ostrzega także: jeśli wiadomość z niezweryfikowanego źródła żąda opłaty, nie odpowiadaj ani nie podawaj danych płatniczych; zablokuj i zgłoś nadawcę. Zobacz [fałszywe SMS-y o dostawie](guide:delivery-scam-texts).

## Śledzenie paczek Shiprocket w Peek

Dla 13-cyfrowego numeru zaczynającego się od `832` [Peek](/) od początku odpytuje Shiprocket (razem z Delhivery, jeśli twoim krajem są Indie), a usługi śledzenia i innych przewoźników tylko wtedy, gdy Shiprocket nie zna numeru: przy dodawaniu pojawia się „Sprawdzanie usług śledzenia…”, a następnie „Shiprocket ma tę przesyłkę.” Ponieważ listy przewozowe Delhivery mają taką samą długość, Peek nie przypisuje Shiprocket po samym kształcie; bez odpowiedzi pokazuje „Oczekiwanie na pierwszy skan przewoźnika Shiprocket” i dalej go odpytuje, a ty możesz wybrać Shiprocket przez „Zmień” (lub „Zmień przewoźnika”). Link `shiprocket.co/tracking/` wybiera Shiprocket niezależnie od formatu numeru.

Peek odczytuje stronę śledzenia Shiprocket i pokazuje status, każdy skan z lokalizacją (gdy jest podana), szacowany termin doręczenia Shiprocket w trakcie transportu oraz godzinę doręczenia dla przesyłek doręczanych w Indiach. Skany zagraniczne nie mają strefy czasowej: Peek czyta je według zegara miejsca (wpis bez miejsca według zegara sąsiedniego wpisu, czasem czasu indyjskiego) i przelicza na twoją strefę czasową („Godziny w twojej strefie czasowej:”). Wpisy zachowują treść strony poza dokładnymi dopasowaniami jak `Delivered`. Peek pomija sprzedawcę, numer referencyjny zamówienia i adres odbiorcy, nie dostaje wagi ani potwierdzenia doręczenia i nie przełącza się na przewoźnika za granicą.

Dla starszej paczki pokazuje ostatni zarchiwizowany status z datą i szuka pełniejszej historii w usługach śledzenia. Peek sprawdza status do co 10 minut, a w dniu przejścia paczki do etapu „W doręczeniu” co 2 minuty od 6:00 według zegara ostatniego skanu (czasu indyjskiego w Indiach), gdy paczka jest otwarta w Peek albo w dniu dostawy z włączonymi powiadomieniami, o ile strona Shiprocket odpowiada. Po dwóch dniach bez zmian (po 12 godzinach na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika”) sprawdza co godzinę, a po siedmiu dniach bez zmian przy statusie „Zarejestrowana” co 6 godzin. W powiadomieniach możesz wybrać „Każdy skan”, „Ważne etapy” albo „Tylko doręczenie”. Po czterech dniach bez skanu przy statusie „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie” Peek pokazuje „Brak aktualizacji od …” i przypomina „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika Shiprocket.” Peek śledzi ponad 4500 przewoźników — w tym [Delhivery](carrier:delhivery), [Blue Dart](carrier:blue-dart), Xpressbees, [Ekart](carrier:ekart), DTDC, Shadowfax i [India Post](carrier:india-post) pod ich własnymi numerami — ale nie zweryfikuje cię jako kupującego, nie zleci ponownej próby ani nie skontaktuje się z Shiprocket czy sprzedawcą.

## Pytania o śledzenie Shiprocket

### Czy Shiprocket to firma kurierska?

Nie. Shiprocket to platforma wysyłkowa: sprzedawcy zamawiają przez nią kurierów takich jak Delhivery, Blue Dart czy Xpressbees, którzy przewożą paczkę. Przesyłki ShiprocketX podróżują pod własnym AWB Shiprocket i doręcza je przewoźnik w kraju docelowym.

### Co oznacza RTO w śledzeniu Shiprocket?

RTO to *return to origin* (zwrot do nadawcy): paczki nie udało się doręczyć po maksymalnie trzech próbach — lub wcześniej na wniosek sprzedawcy — i wraca ona do sprzedawcy. `RTO DELIVERED` oznacza, że sprzedawca otrzymał ją z powrotem.

### Jaki jest numer obsługi klienta Shiprocket?

Strona śledzenia Shiprocket podaje numery 9643887070, 9711451060 i 8510042020 w sprawach śledzenia. Przy problemach z zamówieniem Shiprocket kieruje kupujących do sprzedawcy.

### Dlaczego śledzenie Shiprocket wyświetla AWB Not Found?

Shiprocket nie rozpoznaje wpisanego numeru: sprawdź literówki lub brakujące cyfry i odczekaj kilka minut przy świeżo złożonym zamówieniu, jak podpowiada strona. Przesyłki starsze niż około rok również nie są już znajdowane.

:::sources
- [Shiprocket: Track your orders easily](https://www.shiprocket.in/shipment-tracking/) – śledzenie po AWB, ID zamówienia lub telefonie, OTP i myShiprocket, AWB e-mailem i SMS-em, kontakt ze sprzedawcą, aktualizacje po skanach, ShipX
- [Shiprocket: AWB Not Found](https://shiprocket.co/tracking) – sprawdzanie przy braku numeru, ostrzeżenie o płatnościach, telefony, czat na żywo, wsparcie 24/7
- [Shiprocket: Contact us](https://www.shiprocket.in/contact/) – biuro w Gurugram, godziny infolinii, e-mail, eskalacja i czasy odpowiedzi
- [Shiprocket: FAQs](https://www.shiprocket.in/faq/) – platforma dla sprzedawców, szacowane terminy, RTO, aktualizacje po skanach
- [Shiprocket: Native courier integrations](https://www.shiprocket.in/carrier-integrations/) – 42 kurierów partnerskich i ich nazwy
- [Shiprocket: ShiprocketX](https://www.shiprocket.in/x/) – usługi międzynarodowe, czasy dostawy, usługi z opłaconym cłem, ochrona do 5000 INR
- [Shiprocket: NDR management](https://www.shiprocket.in/ndr-management/) – NDR i RTO, ścieżka kupującego NDR, SMS-y i połączenia automatyczne
- [Shiprocket Support: How to Process an NDR?](https://support.shiprocket.in/support/solutions/articles/43000664210-how-to-process-an-ndr-) – ponowne próby, link SMS, fałszywe próby, trzy próby przed RTO
- [Shiprocket: Product highlights from March 2024](https://www.shiprocket.in/blog/product-highlights-from-march-2024/) – zlecanie ponownej próby i powody na stronie śledzenia
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formaty AWB, brzmienia statusów, hub eksportowy w Delhi, USPS w USA, widok archiwalny, co pokazuje Peek
:::
