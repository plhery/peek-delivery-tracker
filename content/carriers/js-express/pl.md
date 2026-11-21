---
title: JS Express śledzenie przesyłki: numer JSE, statusy
description: Śledź przesyłkę JS Express (急速国际) z Chin: jak wygląda numer JSE…YQ, co oznaczają angielskie statusy, kto doręcza paczkę w Polsce i jak działa odprawa celna.
slug: js-express-sledzenie
published: 2026-11-17
updated: 2026-11-20
---

JS Express (急速国际, także JS EXP) to chińska firma logistyczna obsługująca zagraniczne sklepy internetowe, prowadzona w Suzhou przez spółkę 急创速供应链管理（苏州）有限公司. Jeśli sprzedawca wysłał Twoje zamówienie przez JS Express, numer śledzenia zaczyna się od JSE i kończy na YQ: wklej go w pole powyżej. JS Express wyprowadza paczkę z Chin do kraju docelowego, a doręczenie pod drzwi przejmuje lokalny przewoźnik.

## Numer śledzenia JS Express: jak wygląda

Listy przewozowe JS Express składają się z liter JSE, dwóch kolejnych liter, dziesięciu cyfr i końcówki YQ – łącznie 17 znaków. Z wymyślonymi cyframi:

| Numer | Co oznacza |
| --- | --- |
| `JSEAA9999999980YQ` | JSE, dwie litery, dziesięć cyfr i YQ: numer przesyłki JS Express. |
| `JSEAA999999980YQ` | Tylko dziewięć cyfr: to nie jest układ JS Express. Sprawdź, czy nie brakuje jednej cyfry. |
| `JXCAA9999999980YQ` | Też kończy się na YQ, ale zaczyna od JXC: to numer Juxi Express (który Peek sprawdza najpierw w [DHLink](carrier:dhlink), gdy po JXC stoją litery YI, ZO, US lub ZI), a nie JS Express. |

Inni chińscy spedytorzy używają tej samej budowy z własnym trzyliterowym początkiem: FEI w 17FEIA, WNB w WanbExpress, SFC w SFC, CJP w CJ Packet. JSE oznacza JS Express. Spacje i małe litery nie przeszkadzają w Peek: zero wpisane zamiast drugiej z dwóch liter jest odczytywane jako O, a litera O wśród dziesięciu cyfr jako zero. Wyszukiwarka JS Express wymaga pełnego numeru – jego fragment nic nie znajdzie.

**Gdzie go znaleźć.** Sklep wysyła numer w wiadomości e-mail o nadaniu albo pokazuje go w szczegółach zamówienia. JS Express podaje, że ściśle współpracuje z platformami Amazon, Walmart, Shopify, eBay, AliExpress, Wish oraz Shopee. Jeśli nie możesz go odszukać, sprawdź poradnik [jak znaleźć numer śledzenia przesyłki](guide:find-tracking-number).

**Brak linku do udostępnienia.** Strony śledzenia JS Express przyjmują numer wyłącznie w formularzu, więc żaden bezpośredni adres URL nie otwiera konkretnej paczki – skopiuj sam numer. Angielska strona „Express Query” odpowiada od razu, natomiast chińska wymaga najpierw przepisania kodu z obrazka (验证码).

## Statusy śledzenia JS Express: co oznaczają

Strona JS Express wypisuje każdy skan z datą i godziną, miejscem w nawiasach 【】 oraz komunikatem, od najnowszego do najstarszego, z oznaczeniem „Latest” na górze. Komunikaty są przeważnie po angielsku. W kolejności, w jakiej pojawiają się na trasie paczki:

| JS Express pisze | Co to oznacza dla Ciebie | Peek pokazuje |
| --- | --- | --- |
| `Electronic notification of order creation received.` | Sklep przesłał dane zamówienia do JS Express, ale magazyn nie ma jeszcze fizycznej paczki. | „Zarejestrowana” |
| `PRE-ADVICE` | Partner logistyczny otrzymał dane elektroniczne, zanim paczka trafiła do magazynu JS Express. | „Zarejestrowana” |
| `Item has been collected` | Magazyn JS Express w Chinach przyjął paczkę: to pierwszy fizyczny skan. | „Przyjęta przez przewoźnika” |
| `Item inbound in sorting center.`, `Item outbound in sorting center.` | Sortowanie w obiekcie JS Express w Chinach. | „W transporcie” |
| `Domestic allocation has begun`, `Under domestic allocation` | Paczka nadal jest w magazynie JS Express w Chinach, po sortowaniu i przed wyjazdem. | „W transporcie” |
| `Waybill Generated for` | Przewoźnik w kraju docelowym wygenerował własną etykietę, gdy paczka jest jeszcze w Chinach. | „Zarejestrowana” albo wcześniejszy etap, zwykle „W transporcie” |
| `Arrive at transit center` | W centrum tranzytowym JS Express w Chinach, między sortowaniem a wylotem. | „W transporcie” |
| `Item departed from origin.`, `Arrive at international airport to abroad` | W drodze z magazynu, a następnie na lotnisku międzynarodowym w Chinach. | „W transporcie” |
| `Arrived at export center`, `Exports Center Sorting and Packing`, `Arrived at Origin Port` | Na części linii centrum eksportowe w Chinach sortuje i pakuje przesyłkę przed dotarciem do portu wyjścia z Chin. | „W transporcie” |
| `Shipment arrived at facility and measured.`, `Depart from facility to service provider.` | Punkt w Chinach waży i mierzy paczkę, po czym przekazuje ją na linię obsługującą przelot. | „W transporcie” |
| `Start on-site transportation and shipment` | Na lotnisku w Chinach ruszyła obsługa naziemna jeszcze przed chińską odprawą eksportową. | „W transporcie” |
| `Released From Export Customs` | Chiński urząd celny zwolnił przesyłkę do eksportu. To jeszcze nie polska odprawa celna. | „W transporcie” |
| `DEPART ORIGIN AIRPORT`, `Departure from the original airport`, `Item Flight departure`, `Initial landing`, `Connecting flights flight takeoff` | Samolot wystartował, czasem z międzylądowaniem i lotem przesiadkowym. | „W transporcie” |
| `ARRIVED AT DESTINATION AIRPORT`, `Arrival to the destination airport`, `Item Flight landing`, `NOA-Notification of arrival from GHA` | Lądowanie na lotnisku docelowym. NOA to powiadomienie o przylocie od agenta obsługi naziemnej lotniska. | „W transporcie” |
| `The customs clearance agent is queuing up to pick up the goods` | Agent celny czeka w terminalu cargo na odbiór ładunku: nie ma go jeszcze ani on, ani urząd celny. | „W transporcie” |
| `The customs clearance agent has picked up the goods` | Agent celny odebrał ładunek z terminalu cargo do odprawy importowej. | „W urzędzie celnym” |
| `The goods have been picked up abroad` | Partner w kraju docelowym odebrał przesyłkę z lotniska. To nie jest ponowny odbiór od sprzedawcy. | „W transporcie” |
| `Customs clearance agent received the shipment in destnation country.` | Agent celny w kraju docelowym przejął przesyłkę po locie do odprawy importowej („destnation” to oryginalna pisownia JS Express). | „W urzędzie celnym” |
| `Inbound to customs temporary warehouse` | W magazynie czasowego składowania, w oczekiwaniu na odprawę celną. | „W urzędzie celnym” |
| `Released From Import Customs`, `Released from customs: customs cleared.` | Urząd celny zwolnił paczkę po odprawie importowej. | „W transporcie” |
| `Depart from the destination distribution center.` | Centrum dystrybucyjne w kraju docelowym przekazuje paczkę dalej po odprawie. | „W transporcie” |
| `In transit, it's progressing through post network` | Operator pocztowy przemieszcza przesyłkę w swojej sieci przed wydaniem kurierowi lub listonoszowi. | „W transporcie” |
| `collection by overseas warehouse` | Zagraniczny magazyn JS Express przyjął paczkę po odprawie celnej. | „W transporcie” |
| `Load cargo overseas`, `Vehicles depart from` | Ten magazyn ładuje przesyłkę dla lokalnego przewoźnika, a ciężarówka rusza z miejsca podanego przy skanie. | „W transporcie” |
| `Delivery to local courier`, `Last Mile Delivery` | Przekazanie przewoźnikowi doręczającemu w Twoim kraju, przed jego pierwszym własnym skanem. Paczka nie została jeszcze doręczona. | „W transporcie” |
| `Delivery in progress` | Na części tras: lokalny przewoźnik ma już paczkę w swoim oddziale przed wyjazdem na rejon. Mimo brzmienia to jeszcze nie wydanie do doręczenia. | „W transporcie” |
| `到达待取` | Własny status JS Express po chińsku: przesyłka dotarła i czeka na odbiór. Jako miejsce widnieje tylko nazwa kraju. | „Do odbioru” |
| `The delivery address is incorrect. Please ask the recipient to contact their local area?` | Komunikat JS Express po nieudanej próbie doręczenia z powodu niepełnego lub błędnego adresu; prosi o kontakt z lokalnym oddziałem (Peek usuwa numery telefonów). | „Próba doręczenia” |

Ten sam komunikat adresowy bywa zapisany również po chińsku (`收件人地址不详或有误，派送失败，逾期退回或销毁的具体费用以账单为准，麻烦让收件人联系下当地`), z numerem telefonu na końcu – Peek wyświetla go po angielsku ze statusem „Próba doręczenia”.

**Skany europejskiego partnera pocztowego.** Na części europejskich linii pocztowych w historii pojawiają się `Package scanned at carrier facility` i `Departure to country of destination` w europejskim hubie, a następnie `Arrival from abroad`, `Departure to distribution network`, `Your shipment has arrived at the postal operator of the country of destination and will be delivered in the coming days.` oraz `Arrival distribution office` w kraju docelowym – wszystkie jako „W transporcie”. Gdy nikogo nie ma pod adresem, `Not Delivered - Addressee not present- Message left` oznacza pozostawione awizo („Próba doręczenia”), a `Item arrival at collection point for pick-up` informuje, że paczka czeka w punkcie odbioru („Do odbioru”).

**Przewoźnik bez nazwy pisany wielkimi literami.** Na innych liniach JS Express przekazuje skany firmy doręczającej wersalikami, nie podając jej nazwy. `SHIPMENT RECEIVED`, `PROCESSED AT HUB` i `SHIPMENT PROCESSED` oznaczają przyjęcie w sortowni, a `DEPARTED FROM DEPOT` wyjazd do lokalnej bazy („W transporcie”). `RECEIVED AT LOCAL DEPOT` to ta baza przed wyjazdem kuriera albo po nieudanej próbie: to magazyn przewoźnika, a nie punkt odbioru dla adresata, więc Peek zachowuje status wcześniejszego wiersza. `DELIVERY ATTEMPTED: 1ST DELIVERY ATTEMPT`, `DELIVERY ATTEMPTED: 2ND DELIVERY ATTEMPT` i `DELIVERY ATTEMPTED: 3RD DELIVERY ATTEMPT` to nieudane próby doręczenia („Próba doręczenia”); po trzeciej przewoźnik prosi o umówienie dostawy, a `SHIPMENT REDIRECTED: ON CONSIGNEE'S REQUEST` potwierdza przekierowanie na Twoją prośbę („W transporcie”). `DELIVERY EXCEPTION` oraz `DELIVERY EXCEPTION, WAIT FOR INFORMATION CONFIRMATION` po `ARRIVE AT THE WAREHOUSE` („W transporcie”) sygnalizują wstrzymanie paczki do czasu potwierdzenia danych („Zgłoszono problem”).

**Wiersze, które nie są fizycznymi skanami.** `SHIPPING INFORMATION RECEIVED`, `SHIPMENT CREATED`, `Data Received`, `货物电子信息已经收到`, `Your package is being prepared by the sender and will be handed over to us soon` oraz w europejskim hubie `Shipment Data Uploaded` z następującym po nim `Processed` to jedynie zapisy elektroniczne partnera, często sprzed fizycznego przyjęcia paczki. Również `Consignee Contacted`, dopisywane w tej samej sekundzie co nieudane doręczenie, odnotowuje tylko próbę kontaktu telefonicznego lub SMS i nie zmienia statusu.

**Godziny nie po kolei.** Czasy skanów nie mają podanej strefy i mieszają różne zegary: czas pekiński w Chinach oraz zazwyczaj czas lokalny na lotniskach i u europejskich przewoźników. Kieruj się kolejnością wierszy, a nie samą godziną.

## Kto doręcza przesyłki JS Express w Polsce

W historii JS Express znanej Peek nie pada nazwa firmy doręczającej w Polsce (a strona europejskiej linii JS Express wymienia Wielką Brytanię, Francję, Niemcy, Włochy, Hiszpanię, Szwajcarię i Danię). Rekord systemowy JS Express może zawierać numer nadawczy lokalnego przewoźnika bez jego nazwy, a strona JS Express nie wyświetla żadnej z tych informacji.

Gdy kształt tego drugiego numeru jednoznacznie wskazuje na konkretnego przewoźnika działającego w Polsce, Peek wyświetla „Doręcza: …” wraz z tym numerem i odpytuje także jego system. Jeśli jest to prawidłowy [międzynarodowy numer pocztowy](guide:tracking-number-formats) (dwie litery, dziewięć cyfr i dwie litery), Peek sprawdza go w [Poczcie Polskiej](carrier:poczta-polska). Jeżeli Peek nie pokazuje żadnego przewoźnika doręczającego, zapytaj sprzedawcę, kto doręcza zamówienie w Polsce i pod jakim numerem.

Próby doręczenia, awiza oraz terminy odbioru w punkcie lub placówce zależą od regulaminu lokalnego przewoźnika – JS Express nie publikuje własnych zasad. Ponieważ przy wierszu `到达待取` widnieje jedynie kraj, sprawdź śledzenie firmy lokalnej lub dopytaj sklep o dokładny adres odbioru. Zobacz też [nieudana próba doręczenia i awizo](guide:missed-delivery) oraz [nieodebrana paczka](guide:uncollected-parcel).

## Przesyłka JS Express stoi w miejscu, utknęła na cle albo nie dotarła

**`未获取到任何数据,请提供有效单号!`** Angielska strona JS Express odpowiada tym zdaniem po chińsku, gdy nie zna numeru („nie pobrano żadnych danych, podaj prawidłowy numer listu przewozowego”). `No track data for now!` oznacza brak skanów. W obu sytuacjach Peek pokazuje „Oczekiwanie na przewoźnika JS Express” i sprawdza dalej. Jeśli po dwóch dniach JS Express nadal nie rozpoznaje numeru i żaden inny serwis go nie potwierdzi, pojawia się komunikat „Po dwóch dniach przewoźnik nadal nie zna tego numeru. Sprawdź go, wybierz innego przewoźnika lub usuń przesyłkę.”, a Peek przechodzi na sprawdzanie raz dziennie i – po tygodniu od dodania – raz w tygodniu. Porównaj numer znak po znaku z wiadomością ze sklepu; jeśli po kilku dniach nic się nie pojawi, zapytaj sprzedawcę, czy na pewno nadał paczkę.

**Zatrzymana na `Electronic notification of order creation received.`** JS Express nie odebrał jeszcze paczki od nadawcy. Zapytaj sklep, kiedy przekazał ją do magazynu.

**Zatrzymana na `Inbound to customs temporary warehouse`.** Przesyłka czeka na odprawę importową (na części tras widnieją wpisy `Start Custom clearance Process`, a potem `customs clearance completed`; wcześniejsze `Under customs clearance, please wait patiently.` przed wylotem oznacza chińską odprawę eksportową, czyli „W transporcie”). Zgodnie z komunikatami Komisji Europejskiej i Poczty Polskiej od 1 lipca 2026 r. przesyłki spoza Unii Europejskiej o wartości do 150 euro objęte są cłem w wysokości 3 euro za każdy rodzaj produktu, a od 1 listopada 2026 r. przy zakupach na odległość spoza UE obowiązuje dodatkowo unijna opłata manipulacyjna 2 euro za każdą pozycję towarową (niezależnie od wartości przesyłki), przekazywana Krajowej Administracji Skarbowej. Poczta Polska przypomina, że nawet jeśli podatek VAT został pobrany przy zakupie, przy imporcie mogą zostać naliczone cło, unijna opłata manipulacyjna oraz opłata operatora za obsługę zgłoszenia celnego, pobierane przy doręczeniu lub przed odbiorem. Więcej w poradnikach [paczka zatrzymana w urzędzie celnym](guide:customs), [dlaczego śledzenie się nie aktualizuje](guide:tracking-not-updating) oraz [śledzenie paczek z Chin](guide:tracking-from-china).

**Cisza po `Delivery to local courier`.** Paczkę przejął już lokalny przewoźnik. Jeśli Peek pokazuje jego numer, wpisz go na stronie tego przewoźnika; w przeciwnym razie poproś sklep o numer lokalny.

**Status doręczono, ale paczki nie ma.** Najpierw skontaktuj się z lokalnym przewoźnikiem, który doręczał paczkę w Polsce, a zaraz potem zgłoś sprawę sprzedawcy. Sprawdź poradnik [przesyłka dostarczona, ale nieotrzymana](guide:delivered-not-received).

**Zaginiona, uszkodzona albo zwrócona.** Klientem JS Express jest sprzedawca: według warunków JS Express wszelkie roszczenia odszkodowawcze klient musi zgłosić pisemnie w ciągu 30 dni od przyjęcia przesyłki przez JS Express, a paczki ubezpieczone rozlicza ze swojego konta klienta. Dlatego od razu wystąp o zwrot pieniędzy lub ponowną wysyłkę do sklepu albo platformy zakupowej. Jeżeli przesyłki nie da się doręczyć (brak odbiorcy, odmowa przyjęcia, błędny adres lub zatrzymanie celne), JS Express może zorganizować płatny zwrot na życzenie nadawcy; jeśli nadawca z niego zrezygnuje, paczka jest traktowana jako porzucona i niszczona przez urząd celny w kraju docelowym. Na niektórych trasach `Refused` i następujące po nim `Returned` oznaczają, że odmówiona paczka wróciła do sortowni kuriera, a nie do sprzedawcy („Zgłoszono problem”). Zobacz [zagubiona paczka](guide:lost-parcel) i [zwrot przesyłki do nadawcy](guide:returned-to-sender).

## Kontakt z JS Express i obsługa klienta

Na dzień 16 listopada 2026 r. chińska strona kontaktowa JS Express podaje następujące dane (angielska podstrona jest starsza i zawiera dawne numery oraz adresy):

- **Infolinia:** 400-0353-518 – chiński numer serwisowy podawany również dla zapytań międzynarodowych. JS Express nie podaje godzin pracy ani informacji, czy numer działa przy połączeniach z zagranicy.
- **QQ i WeChat:** firmowe konto QQ 4000353518 oraz oficjalny profil WeChat `js-express`.
- **Skargi:** formularz na angielskiej podstronie „Complaint” oraz ta sama infolinia 4000353518.
- **Oddziały:** główna siedziba i magazyn w Suzhou, kolejne magazyny w Chinach oraz biura sieci w Hongkongu i w Walnut w Kalifornii.

Ponieważ reklamacje w JS Express składa nadawca, w sprawie zamówienia pisz do sklepu, a w sprawie samego doręczenia w Polsce – do lokalnego przewoźnika.

## Śledzenie przesyłki JS Express w Peek

[Peek](/) rozpoznaje numer JS Express po samym układzie znaków (JSE, dwie litery, dziesięć cyfr i YQ), którego nie używa żaden inny przewoźnik w katalogu, i odpytuje bezpośrednio śledzenie JS Express. Jeśli masz wyłącznie numer lokalnego kuriera, wklej go zamiast tego: Peek obsługuje ponad 4500 przewoźników, a gdy sam układ nie wskaże firmy, wybierzesz ją przyciskiem „Zmień”.

Peek pobiera historię z angielskiej strony JS Express i pokazuje każdy skan wraz z miejscem oraz krajem docelowym, którego sama strona JS Express nie wyświetla. `到达待取` pojawia się po angielsku jako `Arrived, awaiting collection`, podobnie jak chiński komunikat o błędnym adresie. Nazwiska osób odbierających paczkę, numery telefonów, zdjęcia z doręczenia oraz wewnętrzny numer zamówienia sklepu są pomijane. Jako przewidywaną datę dostawy Peek pokazuje dzień szacowany przez JS Express i ukrywa go, gdy paczka przechodzi w stan „W doręczeniu”, bo doręczenie wypada już tego dnia. Wiersze informacyjne nie zmieniają statusu.

Skany z Chin są przeliczane według czasu pekińskiego, a pozostałe według strefy wynikającej z miejsca skanu lub kraju docelowego – wszystkie wyświetlają się w strefie czasowej Twojego urządzenia, od najnowszego do najstarszego.

Gdy drugi numer jednoznacznie pasuje do przewoźnika działającego w Polsce, Peek pokazuje „Doręcza: …” wraz z tym numerem i sprawdza go równolegle; prawidłowy międzynarodowy numer pocztowy jest sprawdzany w Poczcie Polskiej. Dopóki u tego przewoźnika widnieje tylko elektroniczna zapowiedź bez fizycznego skanu, zobaczysz „Zgłoszona przewoźnikowi …, jeszcze nieprzyjęta”. Gdy pojawi się tam skan co najmniej tak świeży jak ostatni wpis JS Express, Peek przełącza śledzenie na tego przewoźnika, pokazując oba numery i zachowując wcześniejsze skany JS Express.

Peek sprawdza paczkę nawet co 10 minut, a nawet co 2 minuty (co 5, gdy musi otworzyć stronę JS Express w przeglądarce) od 6:00 czasu lokalnego w dniu, w którym przesyłka ma status „W doręczeniu”, dopóki odpowiada własne śledzenie JS Express (przez serwisy śledzenia przesyłek co 15 minut). Gdy doręczenie przejmuje inny przewoźnik, Peek sprawdza paczkę w tempie tego, który ją doręcza: Royal Mail i Poste Italiane nie częściej niż co godzinę, PostNL i USPS nie częściej niż co 30 minut, także gdy paczka jest w doręczeniu. Po dwóch dniach bez nowych wpisów (albo po 12 godzinach na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika”) sprawdza raz na godzinę. Możesz włączyć powiadomienia: „Każdy skan”, „Ważne etapy” (urząd celny, problemy, próby doręczenia, „Do odbioru”, wydanie do doręczenia, doręczenie i zwrot) albo „Tylko doręczenie”. Peek nie skontaktuje się za Ciebie z JS Express ani ze sprzedawcą.

## Najczęstsze pytania o śledzenie JS Express

### Czym jest JS Express (急速国际)?

To chińska firma logistyczna obsługująca transgraniczny handel internetowy, prowadzona przez 急创速供应链管理（苏州）有限公司. Podaje, że została zarejestrowana w Hongkongu w 2010 r., a w 2011 r. otworzyła centrum operacyjne w Suzhou.

### Gdzie mogę śledzić paczkę JS Express?

W polu na górze tej strony albo na angielskiej podstronie „Express Query” w serwisie js-exp.com. Chińska wersja wyszukiwarki prosi najpierw o przepisanie kodu z obrazka.

### Co oznacza komunikat 未获取到任何数据 w JS Express?

Oznacza „nie pobrano żadnych danych”: system JS Express nie zna tego numeru – przez literówkę albo dlatego, że przesyłka nie trafiła jeszcze do JS Express. Zweryfikuj numer u sprzedawcy.

### Kto doręcza paczki JS Express w Polsce?

Lokalny przewoźnik w Polsce, choć historia JS Express znana Peek nie wymienia jego nazwy. Jeśli w danych przesyłki zapisany jest prawidłowy międzynarodowy numer pocztowy, Peek odpytuje Pocztę Polską; w pozostałych przypadkach zapytaj sprzedawcę, komu przekazywana jest paczka w Polsce.

### Z kim się skontaktować, jeśli paczka JS Express nie dociera?

Ze sklepem, w którym złożono zamówienie: to sprzedawca nadał przesyłkę w JS Express i może złożyć pisemną reklamację w terminie 30 dni. Infolinia JS Express 400-0353-518 to numer chiński.

:::sources
- [JS Express: 急速国际 (strona główna)](https://www.js-exp.com/) – spółka prowadząca serwis
- [JS Express: Introduction (po angielsku)](https://www.js-exp.com/EN/index.php?page=6) – Hongkong 2010, Suzhou 2011, transgraniczny e-commerce
- [JS Express: 企业介绍 (o firmie)](https://www.js-exp.com/index.php?page=6) – Suzhou 2011, oddziały i obsługiwane platformy
- [JS Express: Express Query (po angielsku)](https://www.js-exp.com/EN/index.php?page=19) – angielska wyszukiwarka formularzowa bez bezpośredniego linku i komunikaty odpowiedzi
- [JS Express: 快件查询 (śledzenie)](https://www.js-exp.com/index.php?page=19) – kod obrazkowy na stronie chińskiej
- [JS Express: Insurance Claims (po angielsku)](https://www.js-exp.com/EN/index.php?page=29) – reklamacje klienta w ciągu 30 dni oraz paczki niedoręczalne
- [JS Express: 投保理赔 (ubezpieczenie i odszkodowania)](https://www.js-exp.com/index.php?page=29) – pisemny wniosek klienta w ciągu 30 dni od przyjęcia przesyłki przez JS Express, konto klienta, płatny zwrot lub zniszczenie
- [JS Express: Complaint (po angielsku)](https://www.js-exp.com/EN/index.php?page=32) – formularz skarg i numer 4000353518
- [JS Express: Contact Us (po angielsku)](https://www.js-exp.com/EN/index.php?page=35) – starsze numery i adresy
- [JS Express: 联系我们 (kontakt)](https://www.js-exp.com/index.php?page=35) – infolinia 400-0353-518 również dla zapytań międzynarodowych, QQ, WeChat, magazyny w Chinach, Hongkongu i Kalifornii
- [JS Express: 急速欧洲专线 (linia europejska)](https://www.js-exp.com/index.php?aid=35) – kraje objęte linią europejską
- [Poczta Polska: Zmiany w opłatach dla przesyłek spoza Unii Europejskiej od 1 lipca 2026 r.](https://www.poczta-polska.pl/news/zmiany-w-oplatach-dla-przesylek-spoza-unii-europejskiej-od-1-lipca-2026-r/) – cło 3 euro za każdy rodzaj produktu w przesyłkach do 150 euro i pobór należności przy doręczeniu lub przed odbiorem
- [Poczta Polska: Nowe przepisy Unii Europejskiej. Tak działa nowa opłata za przesyłki spoza UE](https://media.poczta-polska.pl/releases/nowe-przepisy-unii-europejskiej-tak-dziala-nowa-oplata-za-przesylki-spoza-ue) – opłata manipulacyjna 2 euro za pozycję towarową od 1 listopada 2026 r. przekazywana KAS, odrębna od cła, VAT i opłaty za obsługę zgłoszenia
- [Komisja Europejska: EU Customs Reform (po angielsku)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – cło 3 euro od 1 lipca 2026 r. oraz opłata manipulacyjna 2 euro od 1 listopada 2026 r.
- [Komisja Europejska: Guidance on the 3 EUR customs duty and Union handling fee (PDF, po angielsku)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 euro za pozycję w sprzedaży na odległość niezależnie od wartości
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – format JSE…YQ, brzmienie i etapy skanów, skany europejskiego partnera pocztowego oraz przewoźnika bez nazwy pisanego wielkimi literami, wiersze informacyjne, strefy czasowe, otwieranie w przeglądarce, drugi numer oraz przekazanie do Poczty Polskiej lub lokalnego przewoźnika
:::
