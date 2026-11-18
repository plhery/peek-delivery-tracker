---
title: Śledzenie SF Express: numer listu, statusy, cło i dostawa
description: Śledź paczkę SF Express z Chin do Polski: numer 12-cyfrowy lub z prefiksem SF, znaczenie angielskich statusów, odprawa celna w UE, czasy dostawy i kontakt.
slug: sledzenie-sf-express
published: 2026-11-18
updated: 2026-11-18
---

SF Express (顺丰, w skrócie SF) to chińska grupa logistyczna założona w 1993 roku z siedzibą w Shenzhen, a jej oddział transgraniczny SF International obsługuje przesyłki międzynarodowe dla firm, sklepów internetowych i osób prywatnych. Każdy serwis regionalny SF ma własną wyszukiwarkę: `www.sf-express.com` dla Chin kontynentalnych, `htm.sf-express.com` dla Tajwanu oraz `www.sf-international.com` dla sieci międzynarodowej. Numer swojej paczki możesz też wkleić bezpośrednio w pole powyżej.

## Numer śledzenia SF Express: jak wygląda i gdzie go znaleźć

SF nazywa numer przesyłki numerem listu przewozowego (*waybill number*). Wyszukiwarka SF prosi o „12-digit waybill No. or 15-digit waybill No. started with SF”, czyli o 12 cyfr albo 15 znaków zaczynających się od `SF`. Z wymyślonymi cyframi:

| Numer (wymyślony przykład) | Co oznacza |
| --- | --- |
| `SF1428593716200` | `SF` i 13 cyfr: jednoznaczny list przewozowy SF Express. |
| `755128493006` | 12 samych cyfr: również list przewozowy SF, choć ten sam układ stosuje wielu innych przewoźników. |

Wielkość liter i spacje nie mają znaczenia w Peek.

**Gdzie znaleźć numer.** Widnieje na etykiecie nadawczej naklejonej na paczkę, a przy zakupach internetowych w e-mailu z potwierdzeniem wysyłki lub w szczegółach zamówienia w sklepie. Jeśli nie możesz go odszukać, zajrzyj do poradnika [jak znaleźć numer śledzenia](guide:find-tracking-number).

**Jak działają strony śledzenia SF.** Tajwańska strona SF wymaga najpierw ułożenia obrazkowej układanki CAPTCHA oraz podania numeru telefonu nadawcy lub odbiorcy, zanim otworzy szczegóły trasy; przy przesyłkach oznaczonych „under privacy protection” (ochrona prywatności) pokazuje listę zdarzeń, ale ukrywa mapę. Z kolei dla listów przewozowych wystawionych w Chinach kontynentalnych serwis SF International odsyła do głównej strony SF Express i wymaga zalogowania, aby podejrzeć pełną historię.

## Statusy SF Express: co oznaczają angielskie komunikaty

Anglojęzyczne śledzenie SF wyświetla nad osią czasu ogólny znacznik stanu przesyłki:

| SF pokazuje | Co to oznacza dla Ciebie |
| --- | --- |
| `In transit` | W drodze. Wiele poszczególnych wpisów na osi czasu nosi ten sam nagłówek. |
| `Accepted` | Doręczona: to angielskie tłumaczenie chińskiego 已签收 („odebrano za podpisem”), a nie przyjęcie paczki do nadania. |
| `Redirected` | 已转寄: przekierowana pod inny adres; w historii może pojawić się wpis `The shipment is being forwarded`. |
| `Returned` | 已退回: w drodze powrotnej do nadawcy (`The shipment is being returned`). |
| `Detained` | 已扣件: przesyłka zatrzymana przez SF; skontaktuj się z SF, podając numer listu przewozowego, by poznać przyczynę. |
| `Lost` | 已遗失: SF odnotowało zaginięcie paczki; zgłoś się do sprzedawcy. |
| `Abandoned` | 已作废: list przewozowy został anulowany; zapytaj sklep, czy wysłał zamówienie pod nowym numerem. |

Kolejne wiersze historii informują o wejściu do sortowni i wyjściu z niej, załadunku na rejs lotniczy czy odprawie celnej. Komunikat o utrudnionym dostępie pod adresem odbiorcy oznacza nieudaną próbę doręczenia, a wzmianka o złożeniu paczki w urzędzie pocztowym lub samoobsługowym punkcie odbioru informuje, że przesyłka czeka na odbiór.

W Peek zdarzenia mówiące o trwającej odprawie celnej wpadają do etapu „W urzędzie celnym”, a wpis potwierdzający zwolnienie z odprawy przywraca etap „W transporcie”. Komunikat `The shipment is queuing up for flight resources，please wait patiently` oznacza, że przesyłka czeka w kolejce na miejsce w samolocie (nadal „W transporcie”). Z kolei `The shipment has arrived at the warehouse and is waiting for inspection and acceptance. Please wait patiently.` pojawia się przy doręczeniu do magazynu, który sprawdza towar przed potwierdzeniem odbioru — Peek pokazuje wtedy „W doręczeniu” aż do końcowego wpisu o wydaniu.

System SF zapisuje wszystkie znaczniki czasu według czasu chińskiego (`UTC+8`), również dla skanów wykonanych już w Europie: wieczorny skan w Polsce może więc nosić na stronach SF datę kolejnego poranka. Zobacz więcej w [co oznaczają statusy śledzenia](guide:tracking-statuses).

## Dostawa SF Express do Polski

Warunki przewozu SF dla przesyłek nadawanych z Chin kontynentalnych za granicę (*Terms and Conditions of Waybill – Chinese Mainland to Overseas Countries*) określają następujące zasady doręczenia:

- Paczka trafia pod adres wskazany przez nadawcę, niekoniecznie do Twoich rąk własnych: może ją przyjąć domownik lub inna osoba pod tym adresem, a w budynkach z centralnym punktem odbioru (recepcja, kancelaria, portiernia) pozostawienie przesyłki w tym miejscu uznaje się za skuteczne doręczenie.
- SF nie doręcza na adresy składające się wyłącznie ze skrytki pocztowej (*PO Box*) ani samego kodu pocztowego.
- Lokalny kurier nie ma obowiązku dzwonić przed przyjazdem.
- Dowodem doręczenia może być podpis lub pieczęć, wiadomość SMS, zdjęcie wykonane pod Twoim adresem, w punkcie SF albo przy automacie paczkowym, bądź zapis odbioru w systemie SF.

**E-Parcel i E-Commerce Express.** Sklepy internetowe często wysyłają lekkie zamówienia z Chin usługami E-Parcel (do 2 kg) albo E-Commerce Express (`EXD` / `EX-CD`). W kraju docelowym SF przekazuje ostatnią milę lokalnemu partnerowi określanemu na stronach SF ogólnie jako „service partner” lub „agent”, bez wskazywania konkretnego przewoźnika w Polsce. Dla wariantu `EX-CD` karta produktu SF zastrzega wprost: „Return, change of address, and re-delivery will not be accepted for EX-CD” — zwrot, zmiana adresu ani ponowne doręczenie nie są w nim obsługiwane.

**Korekta adresu.** Przy droższych przesyłkach Standard Express, Global Express+ oraz Economy Express nadanych z Chin kontynentalnych nadawca lub odbiorca może zlecić na infolinii SF usługę korekty adresu (*Address Correction Service*), dostępną m.in. dla krajów Unii Europejskiej (w tym Polski), Wielkiej Brytanii i Kanady. Koszt wynosi 70 RMB za przesyłkę i każdą zmianę (plus ewentualna dopłata transportowa); paczki E-Parcel i E-Commerce Express nie kwalifikują się do tej usługi.

## Cło i VAT przy paczkach SF Express z Chin do Polski

Według [podatki.gov.pl](https://podatki.gov.pl/informacje-o-cle/pozostale-informacje/zwolnienia-celne) oraz komunikatów Komisji Europejskiej zwolnienie z cła dla importu do 150 euro zostało zniesione: od 1 lipca 2026 r. do 1 lipca 2028 r. towary kupione na odległość spoza UE w przesyłce o wartości wewnętrznej do 150 euro podlegają tymczasowemu ryczałtowemu cłu w wysokości 3 euro za każdą kategorię towarową (według wytycznych KE niezależnie od tego, czy VAT rozliczono w IOSS, procedurze szczególnej czy na zasadach ogólnych). Od 1 listopada 2026 r. dochodzi unijna opłata manipulacyjna (*Union handling fee*) w wysokości 2 euro za kategorię towaru bez względu na wartość paczki — jak przypomina m.in. [komunikat Poczty Polskiej](https://media.poczta-polska.pl/releases/nowe-przepisy-unii-europejskiej-tak-dziala-nowa-oplata-za-przesylki-spoza-ue). Jeśli sprzedawca nie pobrał polskiego podatku VAT przy zakupie (IOSS), przy odprawie doliczany jest także VAT importowy; opłatę za obsługę zgłoszenia celnego, pobieraną przez operatora, Poczta Polska wymienia jako możliwą także wtedy, gdy VAT pobrano przy zakupie. Bez cła pozostają okazjonalne nieodpłatne prezenty między osobami prywatnymi do 45 euro (z limitami ilościowymi dla alkoholu, tytoniu i perfum). Szczegóły znajdziesz w poradniku [paczka zatrzymana przez urząd celny](guide:customs).

Jeśli odmówisz przyjęcia paczki albo nie uregulujesz należności celno-podatkowych, warunki SF (pkt 11.4) pozwalają odesłać przesyłkę do nadawcy na jego wniosek i koszt. Gdy urząd celny zatrzyma paczkę, a nadawca nie wyjaśni sprawy w ciągu 30 dni kalendarzowych od pisemnego powiadomienia przez SF, przesyłka zostaje uznana za porzuconą i SF może nią rozporządzić, włącznie ze zniszczeniem.

## Paczka SF Express stoi w miejscu, zaginęła lub została uszkodzona

**Kto składa reklamację.** Zasady odszkodowań SF są skierowane do nadawcy: w usługach E-Parcel i `EX-CD` roszczenie składa sklep internetowy, dołączając zrzuty ekranu potwierdzające zwrot pieniędzy kupującemu na platformie. Przy zamówieniach online zgłoś się więc najpierw do sprzedawcy; przy zakupie od przedsiębiorcy w UE według [Your Europe](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_pl.htm) sprzedawca odpowiada za towar aż do chwili wydania go Tobie i ma domyślnie 30 dni na dostawę.

**Brak nowych skanów.** Sprzedawca może zlecić w SF poszukiwanie paczki po 10 dniach roboczych bez aktualizacji (licząc po upływie 15 dni roboczych od nadania) w usłudze E-Parcel albo po 7 dniach roboczych bez skanu w `EX-CD`. Termin na złożenie reklamacji wynosi 90 dni od wysyłki w E-Parcel (odszkodowanie do 200 RMB bez wykupionej ochrony wartości) oraz 60 dni w `EX-CD` (do 300 RMB). Zobacz też [śledzenie się nie aktualizuje](guide:tracking-not-updating) oraz [śledzenie paczek z Chin](guide:tracking-from-china).

**Uszkodzenie lub brak doręczenia mimo statusu `Accepted`.** Warunki SF wymagają pisemnego zgłoszenia uszkodzenia w ciągu 7 dni kalendarzowych od doręczenia; odbiór bez zastrzeżeń na pokwitowaniu oznacza domniemanie dostarczenia w dobrym stanie, dlatego zachowaj karton i wypełnienie do oględzin. Jeśli status pokazuje doręczenie, a paczki nie ma, sprawdź u sąsiadów, w recepcji i w pobliskich punktach odbioru oraz powiadom sprzedawcę (w E-Parcel i `EX-CD`, gdy oficjalna strona lokalnego partnera potwierdza doręczenie, SF pomaga w weryfikacji, lecz nie wypłaca odszkodowania). Więcej w [przesyłka doręczona, ale nieodebrana](guide:delivered-not-received).

## Kontakt z SF Express

Według europejskiej strony kontaktowej SF International przewoźnik nie prowadzi osobnej polskiej infolinii ani oddziału w Polsce. Dla całej Europy udostępnia numer WhatsApp `+31 6 43249528`, czynny od poniedziałku do piątku w godzinach `09:00–17:00` (nieczynny w weekendy i święta). Na globalnej liście infolinii SF International podaje m.in.:

- **Chiny kontynentalne:** `95338` (z zagranicy `+86 755 95338`), codziennie `08:00–21:00` czasu chińskiego (`UTC+8`, czyli o 7 godzin później niż w Polsce zimą i o 6 godzin latem).
- **Hongkong:** `273 00 273` (`+852 2730 0273`), pon.–pt. `08:00–20:30`, weekendy i święta `08:00–20:00`.
- **Tajwan:** `412 8830` (z telefonu komórkowego z prefiksem `02`, z zagranicy `+886 412 8830`), pon.–pt. `08:00–20:00`, sobota `08:00–17:30`.

Strony SF przywołane w tym poradniku: na `www.sf-express.com` zakładki „About SF” i „Track & Trace”; na `htm.sf-express.com` wyszukiwarka dla Tajwanu; na `www.sf-international.com` strony „Company Profile”, „Tracking”, „Contact Us” (świat i Europa), „E-Parcel”, „E-Commerce Express”, „Address Correction Service” oraz „Terms and Conditions of Waybill (Chinese Mainland to Overseas Countries)”.

## Śledzenie przesyłki SF Express w Peek

[Peek](/) od razu rozpoznaje SF Express po numerze z prefiksem `SF` i poprawną cyfrą kontrolną, a także po linkach `sf-express.com` z `waybill-detail/`, linkach tajwańskich z `#search/bill-number/` oraz linkach SF International z numerem po `No=`. Zwykły 12-cyfrowy numer pasuje do wielu firm kurierskich — w Polsce Peek sprawdza przy nim automatycznie m.in. [BRT](carrier:brt) czy Colis Privé, ale nie może odpytać SF w tle podczas samego rozpoznawania. Jeśli masz 12-cyfrowy list SF, stuknij „Zmień” (albo później „Zmień przewoźnika” na karcie paczki) i wybierz SF Express na liście dopasowań.

Peek pobiera angielską historię z tajwańskiego serwisu SF: zachowuje oryginalne komunikaty skanów, tłumaczy wpisy o wydaniu paczki na etap „Dostarczona” i przekłada globalne znaczniki SF na statusy anulowania, przekierowania, „Zwrócona nadawcy”, zatrzymania lub zagubienia. Wszystkie chińskie godziny (`UTC+8`) przelicza na strefę czasową Twojego telefonu lub komputera („Godziny w Twojej strefie czasowej: …”). SF nie zwraca tym kanałem przewidywanej daty doręczenia ani zdjęcia dowodu dostawy, a Peek śledzi numer SF do końca bez przełączania na numer lokalnego partnera.

Peek sprawdza status nawet co 10 minut, gdy masz paczkę otwartą na ekranie albo w dniu dostawy przy włączonych powiadomieniach, a w pozostałym czasie — oraz po dwóch dniach bez nowego skanu lub po 12 godzinach na etapie „Przyjęta przez przewoźnika” — co godzinę. Gdy przesyłka ma status „W doręczeniu” i odpowiada bezpośrednio serwis SF, w godzinach `06:00–24:00` czasu chińskiego (czyli do `17:00` zimą i `18:00` latem czasu polskiego) sprawdza nawet co 5 minut; przez zewnętrzne serwisy śledzenia pyta co godzinę, co 3 godziny po dniu bez skanu i nawet co 15 minut w dniu dostawy przy otwartej karcie lub aktywnych alertach („Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”). Peek nie może skontaktować się w Twoim imieniu z SF ani ze sprzedawcą.

## Najczęstsze pytania o śledzenie SF Express

### Czy SF Express to wiarygodny przewoźnik?

Tak. Założona w 1993 roku grupa SF jest notowana na giełdach w Shenzhen i Hongkongu i — powołując się na raport Frost & Sullivan — przedstawia się jako największy zintegrowany operator logistyczny w Chinach i Azji oraz czwarty na świecie pod względem przychodów za 2025 rok.

### Co znaczy status `Accepted` w śledzeniu SF Express?

Oznacza doręczenie przesyłki. Anglojęzyczny system SF tłumaczy w ten sposób chiński termin 已签收 („odebrano za podpisem”), czyli wydanie paczki pod adresem dostawy, a nie samo przyjęcie jej od nadawcy.

### Ile idzie paczka SF International z Chin do Polski?

SF podaje wyłącznie orientacyjne widełki i zastrzega brak gwarancji terminu („does not make any transit time commitment”). Dla usługi E-Parcel do Polski wskazuje 14–18 dni roboczych, a dla E-Commerce Express Standard (`EXD`) z południowych Chin do Polski 12–15 dni roboczych (Polska nie jest ujęta w osobnej tabeli wariantu `EX-CD`).

### Czy SF Express doręcza do skrytek pocztowych?

Nie. Warunki przewozu SF dla przesyłek z Chin kontynentalnych wykluczają adresy ograniczone do skrytki pocztowej (*PO Box*) lub samego kodu pocztowego — podaj pełny adres z ulicą i numerem.

:::sources
- [SF Express: About SF](https://www.sf-express.com/chn/en/about) – rok założenia 1993, siedziba w Shenzhen i notowania giełdowe
- [SF International: E-Parcel](https://www.sf-international.com/cn/en/product/parcel?isProduct=product) – orientacyjny czas dostawy 14–18 dni roboczych do Polski oraz terminy reklamacji E-Parcel
- [SF International: E-Commerce Express](https://www.sf-international.com/cn/en/product/delivery?isProduct=product) – 12–15 dni roboczych do Polski w EXD, zasady EX-CD i reklamacje sprzedawcy
- [SF International: Address Correction Service](https://www.sf-international.com/cn/en/product/addrchange?isProduct=increment) – korekta adresu za 70 RMB dla krajów UE przy wybranych usługach ekspresowych
- [SF International: Terms of Use](https://www.sf-international.com/cn/en/support/termsOfUse) – zasady doręczenia, wykluczenie skrytek PO Box, zwrot na wniosek nadawcy (pkt 11.4), 30 dni przy zatrzymaniu celnym i 7 dni na zgłoszenie szkody
- [SF International Europe: Contact Us](https://www.sf-international.com/eu/en/about/contact) – europejski numer WhatsApp +31 6 43249528 i godziny pracy
- [SF International: Global Contact Us](https://www.sf-international.com/us/en/about/contact) – numery infolinii w Chinach, Hongkongu i na Tajwanie
- [podatki.gov.pl: Zwolnienia celne](https://podatki.gov.pl/informacje-o-cle/pozostale-informacje/zwolnienia-celne) – 3 euro cła za pozycję do 150 euro od 1 lipca 2026 r. oraz limit 45 euro na prezenty
- [Komisja Europejska: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – cło 3 € od 1 lipca 2026 r. i unijna opłata manipulacyjna 2 € od 1 listopada 2026 r.
- [Komisja Europejska: Customs Guidance on EUR 3 customs duty](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – naliczanie 3 € za kategorię towaru niezależnie od procedury VAT
- [Poczta Polska: Nowa opłata manipulacyjna UE](https://media.poczta-polska.pl/releases/nowe-przepisy-unii-europejskiej-tak-dziala-nowa-oplata-za-przesylki-spoza-ue) – opłata manipulacyjna UE od 1 listopada 2026 r. w Polsce
- [Your Europe: Wysyłka i dostawa](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_pl.htm) – odpowiedzialność sprzedawcy za dostawę i domyślny termin 30 dni w UE
- [Your Europe: Pytania i odpowiedzi dotyczące dostawy](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/faq/index_pl.htm) – prawa kupującego przy opóźnionej lub niedostarczonej przesyłce
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – rozpoznawanie numerów SF i 12-cyfrowych, mapowanie statusów, przeliczanie czasu UTC+8 i częstotliwość odpytywania
:::
