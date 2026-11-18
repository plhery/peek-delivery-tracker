---
title: SpeedPAK śledzenie paczki: statusy, eBay i kto doręcza
description: Śledź przesyłkę eBay SpeedPAK w Polsce: jak wygląda 28-znakowy numer ES/EE/EX, co oznaczają angielskie statusy, kto doręcza paczkę, cło i z kim pisać.
slug: speedpak-sledzenie
published: 2026-11-18
updated: 2026-11-18
---

SpeedPAK to transgraniczna usługa wysyłkowa dla zamówień z eBay, prowadzona wspólnie z firmą logistyczną Orange Connex głównie dla paczek nadawanych z Chin. Numer przesyłki ma 28 znaków i zaczyna się od `ES`, `EE` lub `EX`: możesz wpisać go na stronie śledzenia Orange Connex ([orangeconnex.com/tracking](https://www.orangeconnex.com/tracking), gdzie naraz sprawdzisz do 30 numerów, albo [globalshipping.orangeconnex.de/tracking](https://globalshipping.orangeconnex.de/tracking)) bądź wkleić od razu w pole powyżej. Przy zakupie na eBay numer znajdziesz w historii zakupów obok zamówionego przedmiotu; szczegóły znajdziesz też w naszym poradniku, [jak śledzić zamówienie z eBay](guide:ebay-orders).

## Numer śledzenia SpeedPAK: jak wygląda

Każdy numer obsługiwany przez śledzenie SpeedPAK składa się z 28 wielkich liter i cyfr oraz zaczyna od `ES`, `EE` albo `EX`. Ze zmyślonymi znakami wygląda tak:

| Przykładowy numer | Co oznacza |
| --- | --- |
| `ES1000482910573649281047290N` | `ES` oraz 26 liter i cyfr: przesyłka SpeedPAK. |
| `EE1000482910573649281047290X` | `EE` oraz 26 liter i cyfr: również przesyłka SpeedPAK. |
| `EE482910576CN` | Dwie litery, dziewięć cyfr z cyfrą kontrolną S10 i dwie litery: międzynarodowy numer pocztowy (tu [China Post](carrier:china-post)), a nie SpeedPAK. |

Peek rozpoznaje jako SpeedPAK także 28-znakowe numery z prefiksem `EX` – w przesyłkach odczytanych dotąd pod tym formatem całą trasę od nadawcy do odbiorcy obsługiwał [DHL Express](carrier:dhl-express). Jeśli w zamówieniu brakuje numeru, Orange Connex zaleca kupującym skontaktować się bezpośrednio ze sprzedawcą na eBay lub platformie zakupu, aby potwierdzić wysyłkę. Pomocne wskazówki znajdziesz również w poradniku [gdzie znaleźć numer śledzenia](guide:find-tracking-number).

**Drugi numer na ostatnim odcinku.** Po dotarciu paczki do Europy historia SpeedPAK często ujawnia przewoźnika ostatniej mili i jego własny numer lokalny, którym sprawdzisz doręczenie na stronie tego operatora.

## Statusy śledzenia SpeedPAK: co oznaczają po polsku

Wyszukiwarka na `orangeconnex.com` działa po angielsku i po chińsku (na niemieckiej podstronie Orange Connex interfejs dostępny jest po niemiecku i angielsku). Jeśli strona otworzy się po chińsku, przełącz język w górnym rogu. Najważniejsze komunikaty w typowej kolejności podróży paczki:

| SpeedPAK wyświetla | Etap w Peek | Co to dla Ciebie znaczy |
| --- | --- | --- |
| `Shipment Data Received` | Zarejestrowana | Sprzedawca utworzył zlecenie; SpeedPAK ma dane elektroniczne, ale jeszcze nie odebrał paczki. |
| `Package Received` | Przyjęta przez przewoźnika | Przesyłka została odebrana od nadawcy (podobnie jak pierwszy fizyczny skan `大包收货确认(A-SCAN)`). |
| `Arrived at Regional Distribution Center` | W transporcie | Paczka dotarła do regionalnego centrum dystrybucyjnego, zwykle jeszcze w Chinach. |
| `Departed from Regional Distribution Center` | W transporcie | Opuściła regionalną sortownię. |
| `Export Customs Declaration Completed` | W transporcie | Zakończono eksportową odprawę celną w kraju nadania. |
| `Departed from Origin Country` | W transporcie | Samolot lub transport główny wyruszył z kraju nadania. |
| `Arrived at Destination Hub` | W transporcie | Przesyłka dotarła do hubu docelowego przed odprawą importową. |
| `Import Customs Clearance Completed` | W transporcie | Odprawa celna importowa została zakończona. |
| `Out for delivery` | W doręczeniu | Wydana kurierowi do doręczenia dzisiaj. |
| `Delivered` / `Delivered. (…)` | Dostarczona | Doręczona (w nawiasie bywa podane miejsce pozostawienia paczki). |

Kluczowym wpisem w historii jest wiersz przekazania w formie `LM Carrier: [przewoźnik] , LM tracking No.: [numer]` (lub jego chiński odpowiednik z `尾程供应商`): skrót *LM* oznacza *last mile*, czyli przewoźnika doręczającego w Twoim kraju wraz z jego własnym numerem. Po tym wpisie SpeedPAK przekazuje komunikaty partnera w jego własnym brzmieniu – na przykład `The item is received by PostNL`, `Item is cleared by customs` i `Arrival at Delivery Post Office` (wszystkie trzy oznaczane w Peek jako „W transporcie”), po nieudanej próbie doręczenia `NOT DELIVERED - TO KEEP DURING THE PRESCRIBED TERM BECAUSE THE ADDRESSEE IS NOT AT HOME LEFT MESSAGE IN THE LETTERBOX` lub `Attempted Delivery: No access` („Próba doręczenia”), a gdy paczka czeka w placówce lub punkcie: `The item is being held, addressee being notified` albo `ITEM ARRIVED AT THE COLLECTION POINT WAITING FOR YOUR COLLECT` („Do odbioru”). Jeśli SpeedPAK prześle chiński opis `货物运输中`, Peek zamienia go na angielskie `In transit` („W transporcie”).

Postój przez kilka dni na `Arrived at Destination Hub` albo na wpisie celnym nie jest niczym niezwykłym: Orange Connex uprzedza, że zależnie od miejsca przeznaczenia przewóz do najbliższego lokalnego centrum w regionie może zająć kilka dni.

## Kto doręcza paczki SpeedPAK w Polsce i jak wygląda odprawa celna?

SpeedPAK nie ma własnych kurierów doręczających pod drzwi w Polsce – ostatni odcinek przejmuje operator lokalny lub międzynarodowa sieć pocztowo-kurierska. Żadna publiczna strona Orange Connex nie przypisuje Polsce jednego stałego doręczyciela, dlatego nie warto zgadywać z góry: zaczekaj na wiersz `LM Carrier` w historii (w dotychczasowych przebiegach pojawiały się m.in. [PostNL](carrier:postnl), [DHL Express](carrier:dhl-express), a w USA [USPS](carrier:usps) i UniUni) albo zapytaj sprzedawcę. Orange Connex nie publikuje też własnych reguł dotyczących liczby prób doręczenia, awiza, punktów odbioru czy automatów paczkowych – od chwili przejęcia przesyłki decydują zasady przewoźnika widocznego w `LM Carrier` (w Polsce np. [Poczty Polskiej](carrier:poczta-polska), [InPost](carrier:inpost) lub [DPD Polska](carrier:dpd-pl), jeśli to któryś z nich otrzyma paczkę).

**Cło i opłaty przy zamówieniach spoza UE.** Paczki nadawane z Chin do Polski przechodzą odprawę celno-podatkową. Zgodnie z komunikatami Komisji Europejskiej i [Poczty Polskiej](carrier:poczta-polska) od 1 lipca 2026 r. przesyłki spoza UE o wartości do 150 € podlegają zryczałtowanemu cłu 3 € za każdą pozycję taryfową (kategorię towaru), a od 1 listopada 2026 r. obowiązuje dodatkowo unijna opłata manipulacyjna 2 € za kategorię towaru niezależnie od wartości przesyłki (obok należnego podatku VAT, o ile nie rozliczono go już przy zakupie). Więcej przeczytasz w poradniku [paczka w urzędzie celnym](guide:customs).

## Śledzenie SpeedPAK stoi w miejscu albo paczka nie dotarła

- **Brak aktualizacji od ponad 5 dni:** Orange Connex wskazuje wprost, że jeśli kupujący nie widzi żadnego statusu lub przesyłka nie poruszyła się od **ponad pięciu dni**, powinien skontaktować się ze sprzedawcą przez oficjalną stronę eBay albo platformę, na której złożył zamówienie. Zobacz też [dlaczego śledzenie się nie aktualizuje](guide:tracking-not-updating).
- **Zagubiona, opóźniona lub oznaczona jako doręczona:** dział reklamacji Orange Connex (*Claims*) w portalu pomocy jest przeznaczony wyłącznie dla sprzedawców, a nie dla odbiorców. Zgłoszenie niedoręczonego lub uszkodzonego przedmiotu, zwrot towaru i wniosek o zwrot pieniędzy załatwiasz ze sprzedawcą na eBay (szczegóły procedury znajdziesz w poradniku [zamówienia z eBay](guide:ebay-orders); w Unii Europejskiej przy zakupie konsumenckim sprzedawca odpowiada też za dostawę w ciągu 30 dni oraz za uszkodzenia w transporcie według zasad opisanych na *Your Europe*). Zajrzyj również do poradników [zagubiona paczka](guide:lost-parcel) oraz [dostarczona, ale nieodebrana](guide:delivered-not-received).

## SpeedPAK i Orange Connex: kontakt i bezpieczeństwo

Orange Connex nie prowadzi polskiej infolinii dla odbiorców, a jego niemieckie centrum pomocy (*Hilfecenter für Verkäufer*) odsyła kupujących bezpośrednio do sprzedawcy. Na stronie śledzenia Orange Connex widnieją jedynie azjatyckie dane kontaktowe usługi SpeedPAK:

- **Telefon:** `400 126 0008` (Chiny kontynentalne) oraz `852 30183458` (Hongkong SAR).
- **Godziny pracy:** od poniedziałku do soboty `9:00–18:00` (w niedziele i święta państwowe nieczynne).
- **E-mail:** `CS@orangeconnex.com`.

Gdy w śledzeniu pojawi się już krajowy przewoźnik doręczający wraz ze swoim numerem, w sprawach awiza, przekierowania czy odbioru paczki kontaktuj się bezpośrednio z tym przewoźnikiem.

> **Uważaj na fałszywe SMS-y o dopłacie do paczki:** Orange Connex nie wysyła polskim odbiorcom linków SMS do dopłat za doręczenie. Jeżeli ostatni odcinek przesyłki spoza UE realizuje w Polsce [Poczta Polska](carrier:poczta-polska) i do zapłaty pozostają należności celno-podatkowe, Poczta Polska pobiera je od odbiorcy przy doręczeniu u listonosza lub kuriera albo przy odbiorze w placówce pocztowej – i ostrzega, że SMS-y oraz e-maile z linkami podszywające się pod Pocztex prowadzą do fałszywych stron wyłudzających dane płatnicze. Sprawdzaj numer wyłącznie na `orangeconnex.com`, na oficjalnej stronie wskazanego przewoźnika albo w Peek. Więcej w poradniku [fałszywe SMS-y o paczkach](guide:delivery-scam-texts).

## Śledzenie paczki SpeedPAK w Peek

[Peek](/) natychmiast rozpoznaje 28-znakowy numer zaczynający się od `ES`, `EE` lub `EX`, ponieważ żaden inny przewoźnik nie korzysta z tego formatu. Możesz też wkleić link z `orangeconnex.com` lub `orangeconnex.cn` zawierający `trackingNumber=` albo `trackingNumbers=` z prawidłowym numerem SpeedPAK. Peek wyświetla aktualny etap po polsku, historię zdarzeń z miastem i krajem każdego skanu na mapie, kraj docelowy, godzinę doręczenia oraz – gdy SpeedPAK go ujawni – przewoźnika doręczającego wraz z jego numerem (w samym wierszu `LM Carrier: […]` Peek ukrywa powtórzony numer referencyjny, bo pokazuje go osobno w sekcji doręczenia). Kod pocztowy odbiorcy jest odrzucany ze względów prywatności, a ponieważ SpeedPAK nie udostępnia przewidywanej daty dostawy ani adresu punktu odbioru, dane te nie pojawiają się do czasu przejęcia przez partnera. Godziny skanów wyświetlane są w strefie czasowej Twojego urządzenia z informacją „Godziny w Twojej strefie czasowej: …” na `peektracker.com` i w aplikacji na iPhone'a.

Gdy SpeedPAK wskaże znanego w katalogu przewoźnika ostatniej mili, którego format numeru się zgadza, na karcie paczki zobaczysz „Doręcza: …” wraz z numerem partnera i Peek przełączy się na jego śledzenie, gdy tylko pojawią się tam nowsze skany. Dopóki u partnera widnieje sama elektroniczna zapowiedź bez własnego skanu, karta pokazuje „Zgłoszona przewoźnikowi …, jeszcze nieprzyjęta”.

Kiedy masz paczkę otwartą na ekranie albo w dniu doręczenia z włączonymi powiadomieniami, Peek sprawdza aktualizacje do co 10 minut – a w dniu wydania paczki do doręczenia od 6:00 czasu lokalnego do co 2 minut, dopóki odpowiada bezpośrednio serwer SpeedPAK (przez serwisy śledzące: co godzinę, co 3 godziny po dniu bez skanu oraz do co 15 minut w dniu doręczenia przy otwartej paczce lub aktywnych powiadomieniach). W pozostałym czasie oraz po dwóch dniach bez nowych zdarzeń – lub po 12 godzinach na etapie „Zarejestrowana” albo „Przyjęta przez przewoźnika” – Peek odpytuje co godzinę. Po przejęciu przesyłki przez przewoźnika końcowego Peek dostosowuje rytm do niego: na przykład co godzinę dla Royal Mail i Poste Italiane oraz do co 30 minut dla [PostNL](carrier:postnl) i [USPS](carrier:usps), również w dniu doręczenia. Powiadomienia ustawisz na „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek nie kontaktuje się w Twoim imieniu ze sprzedawcą ani z Orange Connex.

## Najczęstsze pytania o śledzenie SpeedPAK

### Czym jest eBay SpeedPAK?

To usługa wysyłki międzynarodowej dla zamówień z eBay realizowana przez operatora logistycznego Orange Connex, wykorzystywana przede wszystkim przy paczkach wysyłanych z Chin do Europy i Ameryki Północnej.

### Kto dostarcza paczki SpeedPAK w Polsce?

Orange Connex nie publikuje nazwy jednego stałego przewoźnika dla Polski. Nazwę firmy doręczającej oraz jej lokalny numer śledzenia poznasz z wiersza `LM Carrier: […] , LM tracking No.: […]` w historii przesyłki, gdy paczka dotrze do etapu przekazania ostatniej mili.

### Dlaczego strona śledzenia SpeedPAK otwiera się po chińsku?

Wyszukiwarka na `orangeconnex.com` obsługuje tylko język angielski i chiński – przy polskiej konfiguracji przeglądarki potrafi domyślnie wyświetlić wersję chińską. Wystarczy przełączyć język w górnym menu strony albo wkleić numer w Peek, który pobiera opisy angielskie (i zamienia chiński status `货物运输中` na `In transit`).

### Co zrobić, gdy śledzenie SpeedPAK nie zmienia się od tygodnia?

Jeśli od ponad pięciu dni nie pojawił się żaden nowy ruch, Orange Connex zaleca kupującym skontaktować się bezpośrednio ze sprzedawcą na eBay, ponieważ to sprzedawca ma dostęp do portalu reklamacyjnego Orange Connex i odpowiada za doręczenie zamówienia.

:::sources
- [Orange Connex: Tracking For SpeedPAK](https://www.orangeconnex.com/tracking) – wyszukiwarka SpeedPAK, języki strony, numery telefonów w Chinach i Hongkongu, godziny pracy oraz adres CS@orangeconnex.com
- [Orange Connex Logistics DE: Tracking](https://globalshipping.orangeconnex.de/tracking) – jednoczesne sprawdzanie do 30 numerów, kontakt ze sprzedawcą na eBay po ponad 5 dniach bez ruchu oraz czas transportu do lokalnego centrum
- [Orange Connex Logistics DE: Hilfecenter](https://globalshipping.orangeconnex.de/helpPage) – centrum pomocy dla sprzedawców i odesłanie kupujących do sprzedawcy
- [Orange Connex: Claims](https://help.orangeconnex.com/helpCentre/claims.html) – procedura reklamacyjna przeznaczona dla sprzedawców
- [Your Europe: Wysyłka i dostawa](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_pl.htm) – 30-dniowy termin dostawy w UE i odpowiedzialność sprzedawcy za przesyłkę
- [Komisja Europejska: reforma celna UE (po angielsku)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – cło ryczałtowe 3 € do 150 € od 1 lipca 2026 r. oraz unijna opłata manipulacyjna 2 € od 1 listopada 2026 r.
- [Komisja Europejska: wytyczne dotyczące cła 3 € i opłaty manipulacyjnej (PDF, po angielsku)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – naliczanie 3 € i 2 € za kategorię towaru przy imporcie spoza UE
- [Poczta Polska: Zmiany w opłatach dla przesyłek spoza UE od 1 lipca 2026 r.](https://www.poczta-polska.pl/news/zmiany-w-oplatach-dla-przesylek-spoza-unii-europejskiej-od-1-lipca-2026-r/) – pobór opłat celno-podatkowych od odbiorcy przy doręczeniu lub w placówce pocztowej
- [Poczta Polska: Nowe przepisy UE – opłata za przesyłki spoza UE](https://media.poczta-polska.pl/releases/nowe-przepisy-unii-europejskiej-tak-dziala-nowa-oplata-za-przesylki-spoza-ue) – opłata manipulacyjna 2 € od 1 listopada 2026 r.
- [Poczta Polska: Uwaga na fałszywe wiadomości Pocztex](https://www.poczta-polska.pl/news/uwaga-na-falszywe-wiadomosci-pocztex-grozna-kampania-phishingowa/) – ostrzeżenie przed fałszywymi linkami do dopłat w SMS-ach i e-mailach
- [Universal Parcel Scraper: SpeedPAK](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/speedpak) – rozpoznawanie 28-znakowych numerów ES/EE/EX, obsługa linków orangeconnex, przekazywanie LM Carrier, odrzucanie kodu pocztowego i brak ETA
- [Universal Parcel Scraper: statusy SpeedPAK](https://github.com/plhery/universal-parcel-scraper/blob/main/carriers/speedpak/statuses.json) – potwierdzone statusy angielskie i chińskie, numery EX z DHL Express oraz tłumaczenie 货物运输中
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – rozpoznawanie międzynarodowego numeru pocztowego EE…CN oraz rytm odświeżania przewoźników doręczających
:::
