---
title: Śledzenie ePost Global: numery EPG, statusy i doręczenie
description: Śledź paczkę ePost Global: jak wygląda numer EPG, co oznaczają angielskie statusy, kto doręcza przesyłki w Polsce i jak sprawdzać je w Peek.
slug: sledzenie-epost-global
published: 2026-11-21
updated: 2026-11-21
---

ePost Global (`epostglobalshipping.com`) to amerykański konsolidator paczek międzynarodowych i przesyłek pocztowych z siedzibą w Kalifornii (`11137 Warland Drive, Cypress, CA 90630, USA`). Firma wywodzi się z działu *Express Postal Options (XPO)* koncernu R.R. Donnelley i od ponad 25 lat obsługuje logistykę transgraniczną: przyjmuje zamówienia ze sklepów internetowych w swoich centrach operacyjnych w USA, organizuje fracht lotniczy oraz odprawę celną, a ostatnią milę przekazuje ponad 100 operatorom pocztowym i kurierskim w przeszło 200 krajach i terytoriach (prowadzi też krajową sieć `eDGE` w USA i Kanadzie). Jej numery zaczynają się od `EPG` — wklej swój kod w polu powyżej. Własne wyszukiwarki przewoźnika działają pod adresami `epgtrack.com` (*EPG Track*) oraz `ppxtrack.com` (*PPX Track*); katalog Peek rozpoznaje ePost Global także pod nazwami `ePostGlobal`, `EPG`, `PPX Track`, `PPXTrack`, `RR Donnelley`, `RRD Donnelley` i `RRD International Logistics`.

## Numer śledzenia ePost Global: format i przykłady

Peek rozpoznaje jeden format własny dla ePost Global: litery `EPG` oraz od 10 do 15 cyfr (obecne etykiety mają 15 cyfr). Przykłady ze zmyślonymi cyframi:

| Numer (przykładowy) | Co oznacza |
| --- | --- |
| `EPG482910573649182` | `EPG` i 15 cyfr: standardowy współczesny numer ePost Global. |
| `EPG4829105736` | `EPG` i 10 cyfr: również numer ePost Global, najkrótszy przyjmowany przez Peek. |
| `EPG482910573` | Tylko 9 cyfr: to nie jest format ePost Global. Trzy litery i dziewięć cyfr pasują do Colis Privé, Purolator oraz TNT Australia, które Peek wtedy odpytuje. Jeśli ręcznie wybierzesz ePost Global, zobaczysz komunikat, że przewoźnik „nie wydaje takich numerów”. Sprawdź, czy nie brakuje jednej cyfry. |
| `EPG4829105736491827` | 16 cyfr, o jedną za dużo: Peek nie dopasuje żadnego przewoźnika. Sprawdź, czy nie wpisałeś nadmiarowej cyfry. |
| `LZ482910576US` | Dwie litery, dziewięć cyfr i `US`: amerykański numer pocztowy. Dla większości kodów zaczynających się na `C`, `E`, `L` lub `R` Peek wskazuje [USPS](carrier:usps) (według FAQ ePost Global śledzenie odbywa się w portalu PPX lub USPS, zależnie od produktu). Aby w Peek widzieć również wcześniejsze wpisy z centrum ePost Global, dodaj numer `EPG`. |

Spacje, myślniki i małe litery nie mają znaczenia przy wklejaniu numeru w Peek. Na stronie `epgtrack.com` możesz wpisać jednocześnie do 20 numerów, a na `ppxtrack.com` do 10.

**Gdzie znaleźć numer.** W wiadomości e-mail ze sklepu potwierdzającej wysyłkę albo w szczegółach zamówienia. Jeśli nie możesz go odszukać, zajrzyj do poradników [jak znaleźć numer śledzenia](guide:find-tracking-number) i [formaty numerów śledzenia](guide:tracking-number-formats).

## Statusy śledzenia ePost Global — co oznaczają

Tracker ePost Global wyświetla każdy wpis po angielsku wraz z datą, godziną, opisem i zazwyczaj miejscem. Najpierw pojawiają się własne etapy ePost Global:

| Komunikat ePost Global | Co to oznacza dla Ciebie | Peek pokazuje |
| --- | --- | --- |
| `Data Received` | Sklep przesłał dane elektroniczne przesyłki do ePost Global, ale fizyczna paczka jeszcze tam nie dotarła. | „Zarejestrowana” |
| `Shipment in Transit to the ePost Global Processing Center` | Dopisywane automatycznie minutę po `Data Received` przy każdej przesyłce: to część rejestracji etykiety, a nie fizyczny skan w trasie. | „Zarejestrowana” |
| `Parcel Received and Processing` | Amerykańskie centrum operacyjne ePost Global zeskanowało paczkę (z podaniem miasta i stanu USA). ePost Global deklaruje opracowanie i wysłanie paczek w ciągu 24 godzin lub szybciej. | „Przyjęta przez przewoźnika” |
| `Parcel Shipped` | Paczka opuściła centrum operacyjne w USA. | „W transporcie” |
| `In transit to … tracking #: …` | Przekazanie partnerowi na dalszą trasę minutę po wysyłce, z nazwą usługi i numerem śledzenia partnera (spotykane też jako `In transit via …` lub `tracking#:`). | „W transporcie” |
| `In transit` | Takie samo przekazanie partnerowi, bez nazwy usługi i bez drugiego numeru. | „W transporcie” |

**Wpisy przekazywane od partnerów.** Po wydaniu przesyłki `epgtrack.com` przekazuje komunikaty przewoźnika partnerskiego. Wpisy rozpoznawane bezpośrednio w kodzie Peek:

| Po przekazaniu | Co to oznacza dla Ciebie | Peek pokazuje |
| --- | --- | --- |
| `Shipping label created - OSM awaiting item` | Amerykański partner OSM Worldwide otrzymał dane przesyłki. | bez cofnięcia etapu |
| `Arrived at OSM facility`, `Departed OSM facility`, `Package accepted at courier partner`, `Package processed by courier partner` | Przejście przez sortownię OSM Worldwide i wydanie kolejnemu kurierowi współpracującemu. | „W transporcie” |
| `Electronic manifest received` | Elektroniczny manifest odnotowany w ECMS Express po przyjęciu przez ePost Global: wpis informacyjny bez lokalizacji, który nie zmienia bieżącego etapu. | bez zmiany |
| `Parcel has arrived at export distribution warehouse` | Przyjęcie w magazynie eksportowym ECMS Express w kraju nadania. | „W transporcie” |
| `Customer Information Manifested` | DAI Post, partner obsługujący kierunek nowozelandzki, zapisał dane przesyłki. | bez cofnięcia etapu |
| `Checked in at origin hub`, `Airline Delay`, `Collected from the airline for processing` | Hub DAI Post w USA, opóźnienie lotu (`Airline Delay` oznacza opóźniony samolot, a nie problem z paczką) oraz odbiór z lotniska w kraju docelowym. | „W transporcie” |
| `Consignment received at the PostNL Acceptance Centre`, `The item has been manifested and ready to transport`, `The item is tied to a MAWB and ready for shipment`, `The item is received by PostNL`, `The item is on transport to the country of destination` | [PostNL](carrier:postnl) przez Spring przejął paczkę i przygotował ją do transportu lotniczego do kraju docelowego. | „W transporcie” |
| `The consignment has arrived in the country of destination`, `The item has arrived in the country of destination`, `The item is at the PostNL sorting center`, `The item is processed at the PostNL sorting center`, `The item has been processed in the country of destination` | Dotarcie do kraju przeznaczenia i opracowanie w sortowni PostNL. | „W transporcie” |
| `The item is at customs`, `The item is being processed by customs` | Odprawa celna importowa przesyłki obsługiwanej przez PostNL. | „W urzędzie celnym” |
| `Item released by customs` | Zwolnienie z odprawy celnej w sieci PostNL; paczka jedzie dalej. | „W transporcie” |
| `The item is out for delivery` | Wydanie kurierowi do doręczenia według wpisu PostNL. | „W doręczeniu” |
| `The item will be scheduled for a new delivery attempt` | Nieudana próba doręczenia zgłoszona przez PostNL; zostanie wyznaczony kolejny termin. | „Próba doręczenia” |
| `Your item has been dropped off by the sender with our postal partner in the country of origin.`, `Your item is ready to be forwarded from its foreign territory. It will be given to the carrier for forwarding.` | Komunikaty [La Poste](carrier:la-poste) dla paczek kierowanych do Francji przez PostNL, jeszcze przed dotarciem do Francji. | „W transporcie” |
| `The parcel has left the parcel center.`, `The parcel has reached the parcel center.` | Europejska sieć GLS: wyjazd z hubu i przyjęcie w centrum paczkowym. | „W transporcie” |
| `The parcel is expected to be delivered during the day.` | Europejska sieć GLS: paczka zaplanowana do doręczenia w ciągu dnia. | „W doręczeniu” |
| `The parcel has been delivered.` | Europejska sieć GLS: doręczono. | „Dostarczona” |
| `Departed Hub`, `Shipment Received in Depot`, `Shipment Departed Depot` | Brytyjski partner [Royal Mail](carrier:royal-mail): w drodze do Wielkiej Brytanii lub przejście przez magazyn. | „W transporcie” |
| `Arrived in customs` | Ten sam partner brytyjski: wejście do odprawy celnej w Wielkiej Brytanii. | „W urzędzie celnym” |
| `Ready for Collection` | Ten sam partner brytyjski: paczka czeka w punkcie odbioru. | „Do odbioru” |
| `Duty &/or tax fees requested` | Przewoźnik doręczający prosi odbiorcę o opłacenie cła lub podatku przed wydaniem paczki. | „W urzędzie celnym” |
| `Received payment` | Należności zostały opłacone i paczka rusza dalej. | „W transporcie” |
| `The item is available at the pickuppoint` | Przesyłka czeka na odbiór w punkcie przewoźnika lokalnego. | „Do odbioru” |

Przyjęcie paczki przez partnera po etapie `Parcel Shipped` liczy się jako „W transporcie”, a nie powrót na początek. Pozostałe komunikaty partnerów Peek klasyfikuje wspólnymi regułami słownymi; nieznany opis wyświetla w oryginale i nie cofa statusu paczki wydanej już do doręczenia ani dostarczonej.

## Kto doręcza paczki ePost Global w Polsce

ePost Global współpracuje z ponad 100 operatorami pocztowymi i kurierskimi na sześciu kontynentach. W historiach zapisanych w kodzie Peek pojawiają się m.in. europejska sieć GLS, [PostNL](carrier:postnl) przez Spring (z przejęciem przez [La Poste](carrier:la-poste) we Francji), OSM Worldwide, ECMS Express, DAI Post oraz [Royal Mail](carrier:royal-mail). Gdy ePost Global nie podaje nazwy partnera, ale przekazuje prawidłowy międzynarodowy numer pocztowy (dwie litery, dziewięć cyfr, dwie litery), Peek odpytuje operatora pocztowego kraju docelowego — w **Polsce** jest to [Poczta Polska](carrier:poczta-polska).

**Szacunkowy czas dostawy i zakres śledzenia.** Przy wysyłce z USA strona *Cross-Border Parcel Shipping* ePost Global podaje orientacyjnie `1-5 days` od drzwi do drzwi na większości tras w usłudze *Expedited Parcel with Delivery Confirmation* oraz `4 to 8 days` do Kanady i `5 to 10 days` do Europy Zachodniej i reszty świata w *Priority Parcel with Delivery Confirmation* (`4 to 8 days` do Kanady i Europy Zachodniej w *Priority Packet Tracked*, ze śledzeniem od drzwi do drzwi do 48 głównych krajów). Pięć z siedmiu usług paczkowych obejmuje pełne śledzenie do doręczenia, natomiast dwie usługi ekonomiczne zapewniają śledzenie krajowe po dotarciu przesyłki do kraju docelowego. Liczba prób doręczenia i czas odbioru w punkcie zależą od lokalnego przewoźnika — zobacz [nieudane doręczenie](guide:missed-delivery) oraz [nieodebrana paczka](guide:uncollected-parcel).

**Cło i opłaty w Polsce oraz Unii Europejskiej.** W **Unii Europejskiej** od 1 lipca 2026 r. towary kupione online spoza UE w przesyłkach o wartości rzeczywistej do 150 € podlegają unijnemu cłu w wysokości 3 € za pozycję towarową (*per item*), a od 1 listopada 2026 r. dochodzi unijna opłata manipulacyjna (*Union handling fee*) w wysokości 2 € za pozycję bez względu na wartość przesyłki (jedna pozycja obejmuje towary w tej samej paczce o wspólnej klasyfikacji taryfowej, opisie oraz — gdy jest wymagane — pochodzeniu; powyżej 150 € obowiązują standardowe stawki celne), obok podatku VAT przy imporcie do **Polski**, o ile nie został już pobrany przy zakupie w ramach IOSS. Zgodnie z komunikatem celnym ePost Global opłata 2 € obejmuje zarówno przesyłki `DDP` (należności pobrane przy zamówieniu), jak i `DDU` (płatne przez odbiorcę przy doręczeniu — do czasu zapłaty paczka może zostać wstrzymana). Więcej w poradniku [paczka zatrzymana przez urząd celny](guide:customs).

## Paczka ePost Global nie aktualizuje się, zaginęła lub wraca

**„Oczekiwanie na przewoźnika ePost Global”.** Gdy `epgtrack.com` zwraca `We haven't received any tracking updates`, Peek wyświetla „Oczekiwanie na przewoźnika ePost Global” i sprawdza dalej. Upewnij się, że numer składa się z `EPG` i 10–15 cyfr; jeśli po dwóch dniach ePost Global nadal go nie rozpoznaje i żaden serwis śledzenia go nie znajduje, Peek pokazuje: „Po dwóch dniach przewoźnik nadal nie zna tego numeru. Sprawdź go, wybierz innego przewoźnika lub usuń przesyłkę.”

**Cisza po komunikacie `In transit to …`.** Przesyłkę przejął partner doręczający, którego własny system śledzenia bywa dokładniejszy. Jeśli Peek nie przełączył się jeszcze automatycznie, skopiuj numer widoczny po `tracking #:` i dodaj go w Peek osobno.

**Brak aktualizacji od kilku dni.** Po czterech dniach bez nowego skanu Peek oznacza przesyłkę komunikatem z dniem ostatniego skanu („Brak aktualizacji od pon 12 paź”), przypomina, że paczki często milkną podczas długich lotów, na cle lub w weekend (po trzech tygodniach bez nowości radzi zapytać sprzedawcę), i wyświetla przycisk „Poproś przewoźnika ePost Global o pomoc” (albo link do partnera, gdy Peek śledzi już paczkę u przewoźnika posiadającego w katalogu stronę pomocy; dla OSM Worldwide i DAI Post pozostaje link ePost Global). Komunikaty o zawieszonych kierunkach znajdziesz na `epostglobalshipping.com/service-updates`; zobacz też [dlaczego śledzenie się nie aktualizuje](guide:tracking-not-updating).

**Oznaczona jako dostarczona bez paczki, zagubiona lub zwrócona.** Najpierw zapytaj lokalnego doręczyciela, podając jego numer przesyłki, a następnie zgłoś sprawę sprzedawcy, który zlecił wysyłkę w ePost Global. Sprawdź [dostarczona, ale nieotrzymana](guide:delivered-not-received), [zagubiona paczka](guide:lost-parcel) oraz [zwrot do nadawcy](guide:returned-to-sender).

## Obsługa klienta i kontakt z ePost Global

Według `epostglobalshipping.com`:

- **Telefon (USA)**: `+1 866-784-8444` (bezpłatna infolinia w USA) oraz `+1 310-784-8485` (numer lokalny), bez podanych godzin pracy ani adresu e-mail wsparcia na stronie.
- **Asystent online ePost Assist**: pod adresem `epostassist.epgtrack.com` (dostępny z przycisków *Track a package* i *Chat with our online support*) z opcjami *Check package status*, *Create a support ticket* oraz *Get support ticket status*.
- **Formularz kontaktowy**: na `epostglobalshipping.com/contact-support` (*Looking for support beyond tracking a package?*), z opcjonalnym polem na numer śledzenia.
- **Siedziba główna**: `11137 Warland Drive, Cypress, CA 90630, USA`.

**Uwaga na fałszywe SMS-y.** Oficjalne domeny ePost Global służące do śledzenia i pomocy to `epgtrack.com`, `epostassist.epgtrack.com`, `ppxtrack.com` oraz `epostglobalshipping.com`. Przy przesyłce `DDU` prawdziwe wezwanie do opłaty celno-podatkowej pochodzi od Poczty Polskiej lub firmy kurierskiej doręczającej paczkę w Polsce — zanim klikniesz link, zajrzyj do poradnika [fałszywe SMS-y o paczkach](guide:delivery-scam-texts).

## Śledzenie przesyłki ePost Global w Peek

[Peek](/) natychmiast rozpoznaje numer złożony z `EPG` i 10–15 cyfr („Wykryty przewoźnik”) i odpytuje `epgtrack.com` bez zakładania konta ani podawania kodu pocztowego. Działa również wklejenie linku z `epgtrack.com` lub `ppxtrack.com`: Peek wyciąga z niego numer (pierwszy z listy, jeśli parametr `?id=` zawiera ich kilka). Gdy masz wyłącznie numer lokalnego partnera, wklej go tak samo — Peek obsługuje ponad 4 500 przewoźników.

Peek pokazuje aktualny status, każdy skan z jego miejscem, kraj przeznaczenia oraz nazwę i numer partnera, gdy podaje je ePost Global. Pomija pasek postępu, kody pocztowe, numery magazynów lub ulic, referencję klienta oraz lotnicze listy przewozowe (`MAWB`); ponieważ `epgtrack.com` nie udostępnia przewidywanej daty dostawy, wagi ani potwierdzenia doręczenia, Peek ich nie wyświetla. Przycisk „Otwórz stronę przewoźnika ePost Global” otwiera Twoją paczkę bezpośrednio na `epgtrack.com`.

Tracker publikuje godziny bez strefy czasowej. Peek odczytuje `Parcel Received and Processing` oraz `Parcel Shipped` według czasu stanu USA danego centrum (o ile cały stan leży w jednej strefie), wpisy PostNL bez kraju według zegara PostNL, wpisy europejskiej sieci GLS w czasie środkowoeuropejskim, a wpisy brytyjskiego partnera Royal Mail w czasie brytyjskim; pozostałe zdarzenia datuje na podstawie miejsca lub odcinka trasy. Wszystkie godziny widzisz w strefie swojego urządzenia, a na `peektracker.com` oraz w aplikacjach na iPhone'a i Androida oś czasu kończy się informacją „Godziny w twojej strefie czasowej: …”.

Gdy ePost Global wskazuje znanego w Peek partnera, karta przesyłki pokazuje np. „Doręcza: PostNL” — albo „Zgłoszona przewoźnikowi PostNL, jeszcze nieprzyjęta”, dopóki partner ma tylko zapowiedź paczki bez własnych skanów — i wyświetla numer partnera obok numeru `EPG`. Peek odpytuje wtedy także tego przewoźnika (pod jego numerem lub pod kodem `EPG`, jeśli drugiego numeru brak); bez wskazanego partnera przy poprawnym numerze pocztowym pyta pocztę kraju docelowego lub jedynego przewoźnika wynikającego z formatu drugiego numeru (spośród tych, których własny tracker Peek odczytuje bez dodatkowych danych). Gdy u tego przewoźnika pojawi się skan co najmniej tak świeży jak najnowszy wpis ePost Global, Peek przełącza śledzenie na niego, zachowując wcześniejszą historię (dla europejskiej sieci GLS, ponieważ ePost Global nie podaje, która krajowa spółka GLS doręcza, Peek pozostaje przy wpisach przekazywanych przez ePost Global).

Dopóki masz otwartą przesyłkę lub w dniu dostawy włączone powiadomienia, Peek sprawdza zmiany nawet co 10 minut — a od 6:00 czasu lokalnego w dniu ze statusem „W doręczeniu” nawet co 2 minuty, dopóki odpowiada tracker ePost Global (po przejęciu przesyłki przez partnera końcowego Peek dostosowuje się do jego tempa; przez same usługi śledzenia: co godzinę, co 3 godziny po dobie ciszy i do co 15 minut w dniu dostawy przy otwartej paczce lub włączonych powiadomieniach); w pozostałych przypadkach oraz po dwóch dniach bez nowości (12 godzin na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika”) sprawdza co godzinę. Możesz włączyć powiadomienia „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek nie kontaktuje się w Twoim imieniu z ePost Global ani ze sklepem.

## Najczęstsze pytania o śledzenie ePost Global

### Czym jest PPX Track?

To drugi portal śledzenia należący do ePost Global (`ppxtrack.com`, obsługujący do 10 numerów naraz) oraz jeden z aliasów ePost Global w katalogu Peek. Peek odczytuje linki `ppxtrack.com` tak samo jak linki z `epgtrack.com`.

### Co oznacza status „In transit to” w ePost Global?

ePost Global przekazał paczkę partnerowi odpowiedzialnemu za dalszy przewóz. Wpis wymienia nazwę usługi, a po `tracking #:` podaje własny numer śledzenia tego partnera.

### Dlaczego śledzenie ePost Global zatrzymało się na „Data Received”?

Sklep wysłał do ePost Global dane elektroniczne przesyłki, ale żadne centrum operacyjne w USA nie zeskanowało jeszcze fizycznej paczki. Zapytaj sprzedawcę, kiedy faktycznie nadał zamówienie.

### Kto doręcza paczki ePost Global w Polsce?

Partner wskazany w wierszu przekazania: w danych znanych w kodzie Peek pojawiają się m.in. europejska sieć GLS i PostNL przez Spring. Gdy przesyłka ma międzynarodowy numer pocztowy bez wskazanego partnera, Peek odpytuje Pocztę Polską.

### Jak skontaktować się z ePost Global?

W sprawie zamówienia napisz najpierw do sklepu. Bezpośrednio z ePost Global skontaktujesz się przez asystenta *ePost Assist* na `epostassist.epgtrack.com`, formularz na `epostglobalshipping.com/contact-support` lub telefonicznie w USA pod numerami `+1 866-784-8444` i `+1 310-784-8485`.

:::sources
- [ePost Global: strona główna](https://epostglobalshipping.com/) – międzynarodowy konsolidator, 20M+ przesyłek rocznie, 100+ przewoźników, 200+ krajów, adres w Cypress i numery telefonów
- [ePost Global: About us](https://epostglobalshipping.com/about-us) – ponad 25 lat działalności, rodowód w R.R. Donnelley (XPO) i partnerzy na sześciu kontynentach
- [ePost Global: Cross-Border Parcel Shipping](https://epostglobalshipping.com/cross-border-parcel-shipping) – opracowanie w 24 godziny lub mniej, czasy dostawy do Kanady i Europy Zachodniej, śledzenie od drzwi do drzwi i w kraju docelowym, DDP i DDU, portale PPX i USPS
- [ePost Global: eDGE](https://epostglobalshipping.com/edge) – sieć krajowa w USA i Kanadzie obejmująca ponad 100 przewoźników
- [ePost Global: EU customs fee ecommerce](https://epostglobalshipping.com/eu-customs-fee-ecommerce) – opłata 2 € od pozycji w DDP i DDU, cło 3 € do 150 €, IOSS oraz wstrzymanie paczki do czasu zapłaty w DDU
- [ePost Global: Contact support](https://epostglobalshipping.com/contact-support) – formularz wsparcia, link do ePost Assist, telefony i adres siedziby
- [ePost Global: Contact us](https://epostglobalshipping.com/contact-us) – odnośnik do pomocy online w sprawie brakującej paczki
- [ePost Global: Service updates](https://epostglobalshipping.com/service-updates) – zawieszenia i komunikaty operacyjne według kraju
- [EPG Track](https://epgtrack.com/) – oficjalna wyszukiwarka do 20 numerów śledzenia
- [PPX Track](https://ppxtrack.com/) – portal PPX Track do 10 numerów śledzenia
- [ePost Assist](https://epostassist.epgtrack.com/) – sprawdzanie statusu paczki oraz zakładanie i podgląd zgłoszeń
- [Komisja Europejska: Reforma celna UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – cło 3 € od 1 lipca 2026 r. oraz opłata manipulacyjna 2 € od 1 listopada 2026 r.
- [Komisja Europejska: Wytyczne dotyczące cła 3 € i opłaty manipulacyjnej Unii (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – stała opłata 2 € niezależnie od wartości, definicja pozycji towarowej i zwolnienie z VAT przy IOSS
- [Universal Parcel Scraper: ePost Global](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/epost-global) – aliasy, format EPG, linki, statusy własne i partnerskie, strefy czasowe, pomijane pola i strona pomocy
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formaty Colis Privé, Purolator i USPS oraz przekazanie do partnera lub Poczty Polskiej
:::
