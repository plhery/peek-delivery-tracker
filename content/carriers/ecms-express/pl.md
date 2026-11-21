---
title: Śledzenie ECMS Express: 16-znakowe numery, cło i statusy
description: Śledź paczkę ECMS Express (易客满): jak wyglądają 16-znakowe numery, co oznacza każdy skan po angielsku i chińsku, kto doręcza w Polsce i jak działa Peek.
slug: sledzenie-ecms-express
published: 2026-11-21
updated: 2026-11-21
---

ECMS Express (`易客满`, także `ECMS Global` lub `ECMSGlobal`, chińska nazwa spółki `天津易客满国际物流有限公司`) to założona w 2013 roku międzynarodowa firma kurierska i logistyczna obsługująca sklepy internetowe w ponad 200 krajach i regionach. Jeśli sprzedawca nadał Twoje zamówienie przez ECMS, numer śledzenia ma 16 znaków i zaczyna się od `EC`, `ES`, `AP` lub `AX` — wklej go w polu powyżej. ECMS odpowiada za przelot międzynarodowy i odprawę celną, natomiast doręczenie na ostatniej mili realizuje lokalny przewoźnik. W katalogu Peek znajdziesz tę firmę również pod hasłami `ECMS`, `ECMS Global`, `ECMSGlobal`, `ECMS International Logistics Co.`, `易客满`, `易客满国际物流有限公司` oraz `易客满国际快递`.

## Numer śledzenia ECMS Express: format i przykłady

Numery ECMS liczą zawsze dokładnie 16 znaków: `EC`, `ES`, `AP` lub `AX`, po nich od trzech do pięciu liter, a na końcu cyfry. Przykłady ze zmyślonymi znakami:

| Numer (przykładowy) | Co oznacza |
| --- | --- |
| `ECLAX48291057364` | `EC`, trzy litery i jedenaście cyfr: numer ECMS Express. |
| `ECSZXA4829105736` | `EC`, cztery litery i dziesięć cyfr: również numer ECMS. |
| `ECSZXAB482910573` | `EC`, pięć liter i dziewięć cyfr: również numer ECMS. |
| `ESLAX48291057364`, `APLAX48291057364`, `AXLAX48291057364` | Te same 16-znakowe układy zaczynające się od `ES`, `AP` lub `AX`. |
| `ECLAX4829105736` | Tylko 15 znaków: to nie jest układ ECMS i Peek nie dopasuje żadnego przewoźnika (jeśli ręcznie wskażesz ECMS Express, Peek wyświetli informację, że przewoźnik „nie wydaje takich numerów”). Sprawdź, czy nie brakuje jednej cyfry. |

Żaden inny przewoźnik w katalogu Peek nie korzysta z tych układów. Spacje i wielkość liter nie mają znaczenia w Peek.

**Gdzie znaleźć numer.** ECMS drukuje numer listu przewozowego (*waybill*) na etykiecie paczki, a sklep przesyła go w wiadomości e-mail o wysyłce lub pokazuje na karcie zamówienia. Gdy nie możesz go znaleźć, sprawdź [jak znaleźć numer śledzenia](guide:find-tracking-number) oraz [formaty numerów śledzenia](guide:tracking-number-formats).

**Link do śledzenia.** Link do wyszukiwarki `ecmsglobal.com` zakończony parametrem `?number=` wpisuje ten numer w formularz i otwiera weryfikację obrazkową (*Security verification*). Inne linki ECMS przekazują numer po `orderNumber=`, `trackingno=` (np. w domenie `consignee.ecmsglobal.com`) albo `order_number=`. Wklej dowolny z nich w Peek — aplikacja sama wyodrębni z linku numer ECMS.

## Statusy śledzenia ECMS Express — co oznaczają

Na stronie `ecmsglobal.com` tracker najpierw prosi o przejście zabezpieczenia obrazkowego (*Security verification*: *Enter the characters shown below to continue tracking.*), następnie podsumowuje przesyłkę jednym z ośmiu haseł (od *Shipment Information Received* po *Delivered* lub *Exception*) i wypisuje skany od najnowszego z datą, godziną i miejscem. Peek wyświetla każdy skan w brzmieniu zapisanym w rekordzie ECMS. W kolejności przebiegu trasy:

| Zapis w ECMS | Co to oznacza dla Ciebie | Peek pokazuje |
| --- | --- | --- |
| `Virtual Manifest has been received by carrier`, `Virtual Manifest has been received`, `Shipment info confirmed` | Sklep przesłał elektroniczne dane paczki do ECMS, ale fizyczna przesyłka jeszcze nie dotarła do magazynu. | „Zarejestrowana” |
| `Documents and information required for clearance have been received by the carrier.`, `ID information has been reviewed and verified` | ECMS otrzymał dokumenty potrzebne do odprawy celnej albo zweryfikował dane dokumentu tożsamości odbiorcy; to wciąż nie jest skan paczki. | „Zarejestrowana” |
| `Shipment arrived at carrier facility.`, `Shipment arrived at origin facility.` | Pierwszy fizyczny skan w magazynie początkowym ECMS. | „Przyjęta przez przewoźnika” |
| `Parcel has arrived at export distribution warehouse` | Przyjęcie w magazynie eksportowym ECMS w kraju nadania. | „W transporcie” |
| `Shipment departed from carrier facility.`, `Shipment departed from origin facility.` | Paczka opuściła magazyn nadawczy. | „W transporcie” |
| `Flight departure`, `Flight arrival` | Wylot i lądowanie samolotu cargo. | „W transporcie” |
| `Arriving notification received from terminal` | Terminal cargo na lotnisku docelowym potwierdził przybycie ładunku z lotu. | „W transporcie” |
| `Shipment has arrived at ECMS transit warehouse`, `Shipment has departed from ECMS transit warehouse` | Magazyn tranzytowy ECMS przy lotnisku przylotu — przed odprawą celną importową oraz po jej zakończeniu. | „W transporcie” |
| `Shipment started customs clearance process.` | Urząd celny w kraju przeznaczenia prowadzi odprawę przesyłki. | „W urzędzie celnym” |
| `Shipment completed customs clearance process.` | Odprawa celna importowa zakończona; paczka rusza dalej. | „W transporcie” |
| `Shipment arrived at destination facility`, `Parcel at destination facility, ready for next step`, `Parcel Weight` | Paczka jest w sortowni docelowej, została zważona i czeka na wydanie lokalnemu kurierowi. | „W transporcie” |
| `Shipment is tendering to third party carrier for final delivery.` | ECMS przekazuje paczkę lokalnemu przewoźnikowi do doręczenia końcowego; od tego momentu może być widoczny jego własny numer. | „W transporcie” |
| `Return to origin` | Zgłoszenie problemu w sortowni docelowej, które nie zawsze oznacza faktyczny zwrot: w historii zapisanej w kodzie Peek lokalny przewoźnik przejął paczkę następnego dnia i normalnie ją doręczył. | „Zgłoszono problem” |
| `Local carrier has accepted package for final delivery` | Lokalny przewoźnik przyjął paczkę do doręczenia. | „W transporcie” |
| `Shipment is in Transit within the carrier‘s network`, `Parcel in transit during last mile delivery` | Przemieszczanie w sieci lokalnego przewoźnika przekazywane przez ECMS (drugi komunikat może powtarzać się w kolejnych dniach). | „W transporcie” |
| `Shipment is out for delivery.` | Kurier wyjechał z paczką do doręczenia. | „W doręczeniu” |
| `Available for pickup` | Lokalny przewoźnik przechowuje paczkę do odbioru w punkcie. | „Do odbioru” |
| `Shipment delivered to customer` | Przesyłka doręczona. | „Dostarczona” |

**Wpisy po chińsku.** Część rekordów podaje te kroki po chińsku; Peek wyświetla chiński tekst i przypisuje mu ten sam etap co angielskiemu odpowiednikowi: `发货预报已收到，正在等待货物到达我们的始发站仓库` („Zarejestrowana”), `干线航班起飞` i `干线航班已到达` („W transporcie”), `货物已进入清关流程` („W urzędzie celnym”), `货物已清关放行`, `货物已到达目的站仓库` oraz `货物出库前往配送中心安排最终派送` („W transporcie”).

**Godziny.** ECMS zapisuje własne skany z czasem lokalnym i przesunięciem UTC stacji, która je wykonała, a Peek zachowuje to przesunięcie. Przekazywane skany lokalnego przewoźnika mają przesunięcie równe zero: Peek odczytuje je w UTC, chyba że skan z takiego źródła wypadłby wtedy ponad dziesięć minut po dacie zapisania go w systemie ECMS — wówczas Peek traktuje godziny z tego źródła jako czas lokalny ustalany na podstawie miejsca. Wiersz lotu (`Flight departure`, `Flight arrival`) z godziną dokładnie o północy podaje wyłącznie dzień lotu i wyświetla się w Peek bez godziny. Zobacz też [śledzenie paczki z Chin](guide:tracking-from-china).

## Kto doręcza paczki ECMS Express w Polsce

Na ostatnim odcinku (*Final Mile Delivery*) ECMS deklaruje na stronie *Parcel Delivery Service* współpracę ze sprawdzonymi lokalnymi przewoźnikami (*trusted local carriers*) w ponad 200 krajach i regionach, nie publikując stałej listy firm kurierskich dla poszczególnych krajów Europy. W wyszukiwarce na `ecmsglobal.com` ramka *Last Mile Delivery* może wskazać lokalnego operatora w polu `LM Provider`, jego numer w polu `LM Tracking No.` oraz przycisk `Track with …` (w amerykańskich historiach w kodzie Peek jest to np. SpeedX).

**Ile trwa dostawa.** Według FAQ ECMS czas przewozu zależy od kraju nadania, kraju docelowego i wybranej usługi. Przy wysyłce ze Stanów Zjednoczonych (*U.S. Origin* na stronie *Parcel Delivery Service*) ECMS podaje `4–8 business days` do **Unii Europejskiej** (a także `3–6 business days` do Wielkiej Brytanii, `4–8 business days` do Szwajcarii i `3–11 business days` do Kanady). Zasady ponownych prób doręczenia i odbioru z punktu ustala lokalny kurier — zobacz [nieudane doręczenie](guide:missed-delivery) oraz [nieodebrana paczka](guide:uncollected-parcel).

**Cło i opłaty w Polsce oraz Unii Europejskiej.** FAQ ECMS wyjaśnia, że przy przesyłkach `DDU/DAP` odbiorca może zostać poproszony o opłacenie cła i podatków przed doręczeniem, natomiast w formule `DDP` opłaty te zazwyczaj pokrywa z góry nadawca (niektóre kraje wymagają też kopii dokumentu tożsamości do odprawy). W **Unii Europejskiej** od 1 lipca 2026 r. towary kupione online spoza UE w przesyłkach o wartości rzeczywistej do 150 € podlegają unijnemu cłu w wysokości 3 € za pozycję towarową (*per item*), a od 1 listopada 2026 r. dochodzi unijna opłata manipulacyjna (*Union handling fee*) w wysokości 2 € za pozycję bez względu na wartość przesyłki (jedna pozycja obejmuje towary w tej samej paczce o wspólnej klasyfikacji taryfowej, opisie oraz — gdy jest wymagane — pochodzeniu; powyżej 150 € obowiązują standardowe stawki celne), obok podatku VAT przy imporcie do **Polski**, o ile nie został już pobrany przy zakupie w ramach IOSS. Szczegóły w poradniku [paczka zatrzymana przez urząd celny](guide:customs).

## Paczka ECMS Express stoi w miejscu, nie ma jej w systemie lub wraca

**„We could not find a shipment for this tracking number.”** Wyszukiwarka ECMS nie zna jeszcze tego numeru (literówka albo sklep nie przesłał jeszcze danych): Peek pokazuje „Oczekiwanie na przewoźnika ECMS Express” i sprawdza dalej. Jeśli po dwóch dniach ECMS nadal nie zna numeru i żaden serwis śledzenia go nie odnajduje, Peek wyświetla: „Po dwóch dniach przewoźnik nadal nie zna tego numeru. Sprawdź go, wybierz innego przewoźnika lub usuń przesyłkę.”

**Zatrzymana na `Virtual Manifest has been received`.** ECMS ma tylko elektroniczną zapowiedź, a nie fizyczną paczkę. Zapytaj sklep, kiedy przekazał przesyłkę kurierowi.

**Cisza po `Shipment is tendering to third party carrier for final delivery.`** Paczkę przejmuje lokalny kurier, którego własne śledzenie bywa dokładniejsze. Sprawdź pola `LM Provider` i `LM Tracking No.` w ramce *Last Mile Delivery* i — jeśli Peek nie śledzi jeszcze przesyłki u tego przewoźnika — dodaj ten numer osobno w Peek. Po czterech dniach bez nowego skanu Peek pokazuje dzień ostatniego skanu („Brak aktualizacji od pon 12 paź”) oraz przycisk „Poproś przewoźnika ECMS Express o pomoc” (lub link do lokalnego przewoźnika, gdy Peek przełączył już śledzenie i zna jego stronę pomocy). Więcej w poradniku [dlaczego śledzenie się nie aktualizuje](guide:tracking-not-updating).

**Oznaczona jako dostarczona bez paczki, zagubiona, uszkodzona lub zwrócona.** Najpierw zapytaj lokalnego kuriera, gdzie zostawił przesyłkę, a potem zgłoś sprawę sprzedawcy: FAQ ECMS odsyła kupujących bezpośrednio do sklepu po wymianę lub zwrot pieniędzy, ponieważ reklamację w ECMS może złożyć wyłącznie nadawca na piśmie w ciągu 30 dni od przyjęcia paczki (tylko jedno zgłoszenie na przesyłkę), a ECMS nie może anulować ani zmienić zamówienia. Wśród przyczyn zwrotu ECMS wymienia towary zabronione lub ograniczone, niepełne bądź błędne dane adresowe, problemy celne, nieopłacone cło i podatki albo niedopełnienie formalności celnych przez odbiorcę; pojedynczy wpis `Return to origin` nie przesądza jednak jeszcze o ostatecznym zwrocie. Zobacz [dostarczona, ale nieotrzymana](guide:delivered-not-received), [zagubiona paczka](guide:lost-parcel) oraz [zwrot do nadawcy](guide:returned-to-sender).

## Kontakt z obsługą klienta ECMS Express

Według strony *Contact Us* w serwisie `www.ecmsglobal.com` (bez podanej strefy czasowej dla godzin pracy; Polska nie ma osobnego numeru na liście):

- **Formularz online**: na `www.ecmsglobal.com/en-us/contact-us.html` wybierz `Shipment Support` w polu *Inquiry Type* i wpisz numer paczki w *Tracking Number (Optional)*.
- **Europa (Wielka Brytania)**: `(+44) 01753528888`, `cs.uk@ecmsglobal.com`, od poniedziałku do piątku `08:30`–`17:00`.
- **Chiny kontynentalne**: `(+86) 400 086 1756`, `service@ecmsglobal.com`, od poniedziałku do piątku `08:30`–`17:30`.
- **Stany Zjednoczone**: `(+1) 626 956 5388`, `customerserviceus@ecmsglobal.com`, od poniedziałku do piątku `09:00`–`18:00`.

**Uwaga na fałszywe SMS-y.** Oficjalne domeny śledzenia ECMS to `www.ecmsglobal.com` (`ecmsglobal.com`) oraz starszy portal `consignee.ecmsglobal.com`. Peek odrzuca wszelkie linki płatnicze z rekordu; jeśli dostaniesz SMS z żądaniem dopłaty do cła lub doręczenia, przeczytaj [fałszywe SMS-y o paczkach](guide:delivery-scam-texts).

## Śledzenie przesyłki ECMS Express w Peek

[Peek](/) od razu rozpoznaje 16-znakowy numer ECMS („Wykryty przewoźnik”) i odpytuje tracker ECMS bez konta, kodu pocztowego ani numeru telefonu.

Zabezpieczenie obrazkowe ECMS to proste jednocyfrowe działanie rachunkowe: Peek odczytuje je automatycznie, a w razie niepowodzenia ponawia próbę z nowym obrazkiem. Gdy tracker ECMS nadal nie odpowiada, Peek odpytuje usługi śledzenia i pokazuje zapisaną u nich historię w ich własnym brzmieniu.

Peek wyświetla status, każdy skan z miastem, regionem i krajem oraz kraj przeznaczenia — od najnowszego do najstarszego — w strefie czasowej Twojego urządzenia; na `peektracker.com` oraz w aplikacjach na iPhone'a i Androida historia kończy się wierszem „Godziny w twojej strefie czasowej: …”. Pomija referencję sklepu, lokalizację przy wpisach elektronicznego manifestu oraz linki płatnicze, a także nie pokazuje wagi ani przewidywanej daty doręczenia. Przycisk „Otwórz stronę przewoźnika ECMS Express” otwiera Twój numer na `www.ecmsglobal.com`.

Gdy rekord ECMS wymienia lokalnego przewoźnika znanego w Peek, na karcie widać „Doręcza: …” (wraz z jego numerem, jeśli w przekazywanych skanach partnera widnieje pojedyncza referencja) albo „Zgłoszona przewoźnikowi …, jeszcze nieprzyjęta”, dopóki ten przewoźnik ma tylko zapowiedź paczki bez własnego skanu. Jeżeli Peek obsługuje bezpośredni tracker tego przewoźnika bez dodatkowych danych, odpytuje również jego i przełącza tam śledzenie, gdy tylko pojawi się skan co najmniej tak świeży jak ostatni wpis ECMS, zachowując wcześniejsze skany ECMS; w przeciwnym razie dodaj `LM Tracking No.` jako osobną paczkę — Peek śledzi ponad 4 000 przewoźników.

Dopóki masz otwartą przesyłkę lub w dniu dostawy włączone powiadomienia, Peek sprawdza aktualizacje nawet co 10 minut — a od 6:00 czasu lokalnego w dniu ze statusem „W doręczeniu” nawet co 2 minuty, dopóki odpowiada własny tracker ECMS (po przekazaniu do lokalnego przewoźnika Peek przejmuje jego tempo; przez same usługi śledzenia: co godzinę, co 3 godziny po dobie ciszy i do co 15 minut w dniu dostawy przy otwartej paczce lub włączonych powiadomieniach); w pozostałych sytuacjach oraz po dwóch dniach bez nowości (12 godzin na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika”) sprawdza co godzinę. Możesz wybrać powiadomienia „Każdy skan”, „Ważne etapy” (rozpoczęcie odprawy importowej, `Return to origin`, wydanie do doręczenia, `Available for pickup` i doręczenie, bez lotów ani skanów magazynowych) albo „Tylko doręczenie”. Peek nie kontaktuje się w Twoim imieniu z ECMS ani ze sprzedawcą.

## Najczęstsze pytania o śledzenie ECMS Express

### Czym jest ECMS Express (易客满)?

To międzynarodowa firma kurierska i logistyczna założona w 2013 roku (`天津易客满国际物流有限公司`), posiadająca własne oddziały w Chinach kontynentalnych, Hongkongu, Singapurze, USA, Wielkiej Brytanii, Europie, Korei Południowej i Japonii.

### Gdzie mogę śledzić paczkę ECMS Express?

W polu na górze tej strony (bez przepisywania kodu z obrazka) albo na stronie *Track Your Shipment* w serwisie `www.ecmsglobal.com` po rozwiązaniu tamtejszej weryfikacji obrazkowej.

### Kto doręcza paczki ECMS Express w Polsce?

Lokalny przewoźnik współpracujący z ECMS, którego nazwę i numer przesyłki tracker ECMS może pokazać w sekcji *Last Mile Delivery* pod `LM Provider` i `LM Tracking No.`. Jeśli jeszcze ich tam nie ma, zapytaj sprzedawcę.

### Co oznacza status „Shipment is tendering to third party carrier for final delivery”?

ECMS przekazuje Twoją paczkę lokalnej firmie kurierskiej realizującej doręczenie końcowe; jej własny numer śledzenia może pojawić się w polu `LM Tracking No.`. Peek pokazuje „W transporcie”.

### Z kim się skontaktować, gdy paczka ECMS Express zaginęła?

Najpierw ze sprzedawcą, u którego kupiłeś towar: FAQ ECMS kieruje kupujących do sklepu w sprawach zwrotu pieniędzy lub ponownej wysyłki, a reklamację w ECMS może złożyć wyłącznie nadawca pisemnie w ciągu 30 dni od przyjęcia przesyłki. O samo doręczenie pytaj lokalnego kuriera; do ECMS napiszesz przez formularz *Shipment Support* na `ecmsglobal.com`.

:::sources
- [ECMS Express: strona główna](https://www.ecmsglobal.com/en-us/home.html) – międzynarodowe przesyłki ekspresowe, spedycja i logistyka dla e-commerce
- [ECMS Express: About ECMS](https://www.ecmsglobal.com/en-us/about.html) – założenie firmy w 2013 r.
- [易客满: 关于我们 (o nas)](https://www.ecmsglobal.com/zh-cn/about.html) – rok założenia 2013, własne oddziały i nazwa spółki 天津易客满国际物流有限公司
- [ECMS Express: Parcel Delivery Service](https://www.ecmsglobal.com/en-us/express.html) – lokalni przewoźnicy ostatniej mili, ponad 200 krajów oraz czasy doręczenia z USA do UE, Szwajcarii, Wielkiej Brytanii i Kanady
- [ECMS Express: Track Your Shipment](https://www.ecmsglobal.com/en-us/tracking.html) – weryfikacja obrazkowa, osiem etykiet podsumowania, sekcja Last Mile Delivery (`LM Provider`, `LM Tracking No.`), komunikat o braku przesyłki i `?number=`
- [ECMS Express: FAQ](https://www.ecmsglobal.com/en-us/faq.html) – numer listu na etykiecie, DDU/DAP i DDP, przyczyny zwrotów, brak możliwości anulowania oraz 30 dni na reklamację przez nadawcę
- [ECMS Express: Contact Us](https://www.ecmsglobal.com/en-us/contact-us.html) – formularz Shipment Support, regionalne telefony, adresy e-mail i godziny pracy
- [Komisja Europejska: Reforma celna UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – cło 3 € od 1 lipca 2026 r. oraz opłata manipulacyjna 2 € od 1 listopada 2026 r.
- [Komisja Europejska: Wytyczne dotyczące cła 3 € i opłaty manipulacyjnej Unii (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – naliczanie od pozycji towarowej i zwolnienie z VAT przy IOSS
- [Universal Parcel Scraper: ECMS Express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ecms-express) – aliasy, 16-znakowy format, linki, zagadka rachunkowa i ponowienie, statusy angielskie i chińskie, strefy czasowe oraz odrzucane pola
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – przekazanie do lokalnego przewoźnika i awaryjne odpytywanie usług śledzenia
:::
