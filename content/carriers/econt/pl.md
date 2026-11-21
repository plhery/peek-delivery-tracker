---
title: Śledzenie przesyłek Econt: numer, statusy i oddziały
description: Śledź paczkę Econt w Bułgarii: gdzie znaleźć 13-cyfrowy numer listu przewozowego, co oznaczają statusy, oddziały, automaty Econtomat, reklamacje i kontakt.
slug: sledzenie-econt
published: 2026-11-21
updated: 2026-11-21
---

Econt Express (*Econt Express AD*) to założona w 1993 r. bułgarska firma kurierska doręczająca pod drzwi, do oddziałów Econt oraz do całodobowych automatów Econtomat. Przesyłkę sprawdzisz w zakładce `Track a shipment` na [econt.com](https://www.econt.com/en/services/track-shipment), przez link z e-maila lub SMS-a od Econt albo wklejając numer przesyłki w polu powyżej.

## Numer śledzenia Econt: format i gdzie go znaleźć

Econt nazywa go numerem przesyłki (*shipment number*) albo numerem listu przewozowego (*waybill number*). Powiadomienia e-mail (bezpłatne) i SMS (płatna opcja za `0,11 €`, którą aktywuje nadawca, więc nie każda paczka ją ma) zawierają bezpośredni link do śledzenia wraz z miejscem, terminem i ewentualną kwotą do zapłaty. Nie masz wiadomości od Econt? Zajrzyj do maila ze sklepu lub zobacz, [gdzie jeszcze szukać numeru śledzenia](guide:find-tracking-number).

Econt nie publikuje oficjalnego wzorca dla odbiorców, ale przykładowe numery listów przewozowych w angielskiej instrukcji integracji XML mają **13 cyfr**, a system śledzenia Econt odczytuje je jako dodatnie 13-cyfrowe liczby całkowite niezaczynające się od `0`, jak wymyślony numer `1048291057364`.

Trzynaście cyfr stosują również inni przewoźnicy — m.in. [DPD Polska](carrier:dpd-pl), DPD France, ukraińska Ukrposhta, rumuński FAN Courier czy indyjskie Delhivery — warto więc upewnić się, którą firmę podał sprzedawca. Jeśli wybierzesz Econt ręcznie dla numeru z zerem na początku albo o innej długości, Peek poinformuje, że przewoźnik „nie wydaje takich numerów”.

## Co oznaczają statusy śledzenia Econt

Strona `econt.com` otwiera się po bułgarsku (`Проследи пратка`) z możliwością przełączenia na angielski (`Track a shipment`). Poniżej znajdziesz angielskie opisy skanów pokazywane w Peek, w typowej kolejności:

| Status Econt | Co to oznacza dla paczki | Peek pokazuje |
| --- | --- | --- |
| `Awaiting delivery to Econt` | Nadawca [utworzył etykietę](guide:tracking-statuses), ale nie przekazał jeszcze paczki firmie Econt. | „Zarejestrowana” |
| `Submitted to courier` | Na samym początku historii: kurier odebrał paczkę od nadawcy („Przyjęta przez przewoźnika”). Po skanie w oddziale lub na trasie: paczka wyjechała do Ciebie („W doręczeniu”). | „Przyjęta przez przewoźnika” / „W doręczeniu” |
| `Arrival at an office` | Dotarcie do wskazanego obok oddziału Econt lub centrum logistycznego (w tym punktu odbioru). | „W transporcie” (lub „Do odbioru”) |
| `Received by line-haul` | Przewóz na linii międzymiastowej między centrami; dopisek podaje trasę z miasta do miasta. | „W transporcie” |
| `Change delivery terms` | Zmieniono dyspozycję doręczenia (np. oddział lub dzień); temat widnieje po `Change delivery terms:`, a paczka nie zmieniła miejsca. | *(notatka bez etapu)* |
| `Shipment forwarding` | Przekierowanie do innej miejscowości lub pod nowy adres. | „W transporcie” |
| `Failed delivery` | Nieudana próba doręczenia (powód w nawiasie) i zwrot do wskazanego oddziału. | „Próba doręczenia” |
| `Delivered` | Doręczono lub odebrano (z nazwiskiem osoby odbierającej po `Delivered:`, jeśli Econt je podaje). | „Dostarczona” |
| `Delivery return` | Przesyłka wraca do nadawcy. | „Zwrócona nadawcy” |
| `Shipment destruction` | Przesyłka skierowana do utylizacji; od razu skontaktuj się ze sklepem lub z Econt. | „Zgłoszono problem” |
| `Information` | Komunikat informacyjny bez zmiany etapu. | *(notatka bez etapu)* |
| `Return receipt: Delivered` | Skan podpisanego zwrotnego potwierdzenia odbioru wracającego do nadawcy — nie dotyczy już Twojej paczki. | *(potwierdzenie, bez etapu)* |

Zwróć uwagę na `Submitted to courier`: przed jakimkolwiek oddziałem lub trasą liniową oznacza odbiór od nadawcy, a później — ostatnią milę do odbiorcy.

Wyszukiwarka Econt zwraca też **bułgarski status zbiorczy**, z którego Peek ustala bieżący etap, gdy jest on jednoznaczny: `Очаква предаване към Еконт` i `Подготвена в eEcont` („Zarejestrowana”), `Приета в Еконт` i `Приета в офис` („Przyjęta przez przewoźnika”), `Пътува по линия` („W transporcie”), `В офис` i `Пристигнала в офис` („Do odbioru”, gdy tylko Econt wskaże oddział przechowujący przesyłkę), `Доставена` („Dostarczona”), `Върната` i `Върната и доставена към подател` („Zwrócona nadawcy”), a także `Връща се към подател`, `Анулирана след изпращане` i `Анулирана преди изпращане` („Zgłoszono problem”). Przy statusie `В куриер` (u kuriera) rozstrzyga najnowszy skan.

## Doręczenie pod adres, oddziały Econt i automaty Econtomat

**Pod drzwi.** W miejscowościach z oddziałem Econt standardowe paczki (do `50 kg` i `90 x 90 x 90 cm`, dokumenty A4 do `0,5 kg`, przesyłki niepaletyzowane od `50` do `1000 kg`) docierają w następnym dniu roboczym — w obrębie miasta do `18:00` — a w miejscowościach bez oddziału w ciągu **3 dni roboczych** według harmonogramu. Nadawca może dobrać bezpłatne przedziały godzinowe (`Delivery Time Slots`), płatną stałą godzinę (`1,88 €`–`6,50 €`) lub doręczenie w sobotę (`1,88 €`).

**W oddziale Econt.** Paczka nadana na następny dzień roboczy czeka wcześnie rano, zaraz po otwarciu placówki. Po **7. dniu** oczekiwania Econt nalicza opłatę magazynową **`3,78 €` dziennie za przesyłkę**.

**W automacie Econtomat (czynnym 24/7).** Automaty mieszczą paczki do **`61 x 44 x 37 cm`** i **`30,5 kg`** (trzy wysokości skrytek: `8 cm`, `18 cm`, `37 cm`), a czas dostawy z Econtomatu wynosi **1–2 dni robocze** zależnie od pory nadania. Aby odebrać przesyłkę: opłać z góry ewentualną należność w aplikacji Econt kartą bankową lub portfelem cyfrowym `ecoints`, wybierz `Receive a shipment`, zeskanuj kod QR albo wpisz `PIN 1` i `PIN 2` z aplikacji bądź SMS-a, wyjmij paczkę i zamknij drzwiczki.

**Zmiana doręczenia i sprawdzenie zawartości.** Odbiorcę, miejsce (inny adres, inny oddział, pozostawienie u znajomego lub sąsiada, dosłanie do innego miasta) oraz termin zmienisz w każdej chwili telefonicznie, przez link w powiadomieniu e-mail (`instruction.econt.com`) lub w profilu `e-Econt` ([zmiana adresu dostawy](guide:change-delivery-address)). W cenniku Econt są też trzy bezpłatne opcje udostępniane przez sprzedawcę: `Review` (oględziny), `Review and Test` (oględziny i test) oraz `Review, Test and Select` (oględziny, test i wybór).

## Opóźniona, uszkodzona lub zagubiona paczka Econt

W Bułgarii Econt deklaruje następny dzień roboczy tam, gdzie działa oddział Econt, do 3 dni roboczych poza siecią oddziałów i 1–2 dni robocze z Econtomatu. Jeśli status stoi w miejscu od kilku dni, sprawdź poradnik o [braku aktualizacji śledzenia](guide:tracking-not-updating), a potem zapytaj sklep lub Econt.

W razie uszkodzenia, braków lub zaginięcia ([uszkodzona paczka](guide:damaged-parcel), [zagubiona paczka](guide:lost-parcel)) złóż reklamację przez `I want to file a claim` w centrum obsługi Econt i powiadom sprzedawcę.

## Kontakt i obsługa klienta Econt

Na [stronie centrum obsługi klienta](https://www.econt.com/en/client-center) Econt podaje:

- **Z Bułgarii i Grecji:** `0 7001 7300` (w cenie połączenia lokalnego lub zgodnie z taryfą Twojego operatora).
- **Z pozostałych krajów:** `+359 88 565 43 21` (zgodnie z cennikiem Twojego operatora).
- **Online:** wirtualna asystentka `Ask Ena`, wyszukiwarka placówek i godzin pracy (`I want to find offices and working time`) oraz formularz reklamacyjny (`I want to file a claim`).

Gdy podejrzany SMS żąda dopłaty poza oficjalną aplikacją lub domeną `econt.com`, porównaj go z poradnikiem o [fałszywych SMS-ach o dostawie](guide:delivery-scam-texts).

## Śledzenie przesyłek Econt w Peek

Ponieważ 13 cyfr pasuje do wielu firm, po samym numerze [Peek](/) odpytuje Econt automatycznie tylko wtedy, gdy dodajesz go na stronie z Bułgarii lub w aplikacji na telefonie z regionem Bułgaria. Poza Bułgarią wybierz Econt przyciskiem „Zmień” — albo wklej link `econt.com` zawierający `/services/track-shipment/`, `/en/services/track-shipment/`, `/tracking/` lub `/tracking/?num=`, który od razu wskazuje Econt (dopóki Econt nie ma jeszcze wpisu lub gdy stara historia wygasła, Peek wyświetla „Oczekiwanie na przewoźnika Econt”).

Peek pobiera dane z usługi śledzenia Econt bez konta i kodu pocztowego, pokazuje bieżący status, zaznacza oddziały na mapie, przy statusie „Do odbioru” podaje oddział przechowujący paczkę i zachowuje do 100 najnowszych skanów. Dla osoby, która dodała przesyłkę, widoczne pozostają nazwa odbiorcy i osoba kwitująca po `Delivered:` (ukryte pod udostępnionym linkiem), natomiast liczba paczek, nazwisko kuriera, nadawca i numery zleceń są pomijane; Econt nie udostępnia prognozy dnia doręczenia, wagi ani zdjęcia doręczenia.

Godziny są odczytywane w strefie Sofii (`Europe/Sofia`, zimą `UTC+2`, latem `UTC+3`) i przeliczane na strefę Twojego urządzenia („Godziny w Twojej strefie czasowej: …”), z wyjątkiem godziny pominiętej lub powtórzonej przy zmianie czasu, zachowywanej jako czas lokalny. Przy otwartej paczce lub w dniu dostawy z włączonymi powiadomieniami Peek sprawdza status nawet co 10 minut, a od `06:00` czasu sofijskiego w dniu skanu „W doręczeniu” nawet co 2 minuty, dopóki odpowiada wyszukiwarka Econt (przez serwisy śledzące: co godzinę, co 3 godz. po spokojnej dobie i do co 15 minut w dniu dostawy przy otwartej paczce lub włączonych alertach); poza tym i po 12 godzinach na etapie „Zarejestrowana” / „Przyjęta przez przewoźnika” lub dwóch dniach bez skanu — co godzinę („Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”). Peek nie zmieni dyspozycji w Econt ani nie złoży reklamacji w Twoim imieniu.

## Najczęstsze pytania o śledzenie Econt

### Czy mogę śledzić paczkę Econt bez znajomości bułgarskiego?

Tak: Peek wyświetla skany Econt z angielskimi opisami z powyższej tabeli, a główny status paczki tłumaczy na polski. Strona `econt.com` ma też wersję angielską pod `Track a shipment`.

### Ile trwa dostawa kurierem Econt w Bułgarii?

W miejscowościach z oddziałem Econt — następnego dnia roboczego (pod adres w mieście do `18:00`, w oddziale rano od otwarcia), w miejscowościach bez oddziału — do 3 dni roboczych według grafiku, a z automatu Econtomat — od 1 do 2 dni roboczych.

### Jak śledzić paczkę Econt spoza Bułgarii po 13-cyfrowym numerze?

Wklej 13 cyfr w polu powyżej i wybierz Econt przyciskiem „Zmień” (albo wklej pełny link `econt.com/en/services/track-shipment/…`), ponieważ sam 13-cyfrowy ciąg jest automatycznie sprawdzany w Econt wyłącznie z Bułgarii.

:::sources
- [Econt: Track a shipment](https://www.econt.com/en/services/track-shipment) – angielska wyszukiwarka na econt.com
- [Econt: Проследяване на пратка](https://www.econt.com/services/track-shipment) – bułgarska strona śledzenia
- [Econt: About Econt Express](https://www.econt.com/en/econt-express) – Econt Express AD, założona w 1993 r. w Bułgarii
- [Econt: Customer service center](https://www.econt.com/en/client-center) – 0 7001 7300 (Bułgaria i Grecja), +359 88 565 43 21, Ask Ena, wyszukiwarka oddziałów i reklamacje
- [Econt: Courier services](https://www.econt.com/en/services/courier-services) – paczki do 50 kg i 90 x 90 x 90 cm, terminy, przedziały godzinowe, sobota, składowanie 3,78 €/dzień po 7. dniu, SMS 0,11 € i bezpłatne opcje Review / Test / Select
- [Econt: Econtomat](https://www.econt.com/en/services/econtomat) – automaty 24/7 do 61 x 44 x 37 cm i 30,5 kg, przedpłata, kod QR oraz PIN 1 i PIN 2
- [Econt: Имейл или SMS известяване](https://www.econt.com/developers/29-sms-izvestyavane.html) – powiadomienia e-mail (bezpłatne) i SMS z linkiem śledzenia
- [Econt: Instructions form](https://instruction.econt.com/5cZyLrYsBZH) – formularz zmiany adresu, oddziału, terminu lub odbioru u sąsiada
- [Econt: Integration of e-shops with the information system of Econt Express](https://www.econt.com/e-econt/doc_templates/XML_manual_engl.pdf) – 13-cyfrowe przykładowe numery listów przewozowych
- [Universal Parcel Scraper: Econt](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/econt) – weryfikacja 13-cyfrowych liczb całkowitych, rozpoznawanie z Bułgarii, statusy angielskie i bułgarskie podsumowania, strefa Europe/Sofia, odbiorca i pomijane pola
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – inni przewoźnicy 13-cyfrowi i zapasowe serwisy śledzące
:::
