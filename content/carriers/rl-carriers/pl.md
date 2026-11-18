---
title: Śledzenie R+L Carriers: numer PRO, statusy i reklamacje
description: Śledź przesyłkę towarową R+L Carriers w USA: jak wygląda numer PRO, co oznacza każdy status śledzenia i jakie zasady obowiązują przy opóźnieniu lub szkodzie.
slug: sledzenie-rl-carriers
published: 2026-11-18
updated: 2026-11-18
---

R+L Carriers to drogowy przewoźnik ładunków towarowych w **Stanach Zjednoczonych**. W kodzie Peek odbiera towar od nadawcy, przewozi go między terminalami przeładunkowymi i doręcza z terminalu docelowego na terenie USA. Jeśli duże zamówienie albo inna przesyłka towarowa jedzie do Ciebie w Stanach Zjednoczonych za pośrednictwem R+L, sprawdzasz ją po numerze PRO otrzymanym ze sklepu lub od firmy zlecającej fracht: wklej go w polu powyżej. Własna wyszukiwarka R+L to strona *Shipment Tracing* w serwisie rlcarriers.com: przyjmuje numer PRO albo identyfikator zlecenia odbioru (*Pickup Request ID*), do 25 numerów naraz, a po zalogowaniu na konto MyRLC również inne numery referencyjne.

## Numer śledzenia R+L Carriers: numer PRO

Wyszukiwarka R+L odnajduje ładunki na podstawie numeru PRO. Peek rozpoznaje następujące formaty, pokazane poniżej z przykładowymi cyframi:

| Numer (przykładowy) | Co oznacza |
| --- | --- |
| `48291057-3` | Dziewięć cyfr, z których ostatnia jest cyfrą kontrolną zapisaną tutaj po myślniku. |
| `482910573` | Ten sam numer PRO zapisany bez myślnika. |
| `RL482910-5` | Dwie litery i siedem cyfr, gdzie ostatnia to cyfra kontrolna. Występuje także bez myślnika (`RL4829105`). |
| `I482910573` | Numer PRO w zapisie wyszukiwarki R+L z literą `I` na początku. Litera `I` nie jest częścią samego numeru: Peek przyjmuje PRO z nią lub bez niej. |

Wyszukiwarka R+L weryfikuje ostatnią cyfrę, dzięki czemu wyłapuje literówki i czeskie błędy (szczegóły niżej). Przed odpytaniem R+L Peek usuwa spacje i myślniki. W katalogu Peek przewoźnik R+L Carriers figuruje też pod nazwami R&L Carriers, R and L Carriers oraz RL Carriers.

**Gdzie go znaleźć.** W wiadomości e-mail ze sklepu potwierdzającej nadanie lub w szczegółach zamówienia; przy frachcie między firmami zapytaj przedsiębiorstwo wysyłające towar. Zobacz [jak znaleźć numer śledzenia](guide:find-tracking-number).

**Inni przewoźnicy o tym samym układzie.** Same dziewięć cyfr pasuje również do [TNT](carrier:tnt), TForce Freight oraz XPO, a litera `I` przed dziewięcioma cyframi pokrywa się z formatem Australia Post. Jeśli w powiadomieniu o wysyłce wskazano którąś z tych firm, śledź przesyłkę u niej. Więcej w poradniku [formaty numerów śledzenia](guide:tracking-number-formats).

## Co oznaczają statusy śledzenia R+L Carriers

System R+L wyświetla nad historią krótkie podsumowanie przesyłki, a poniżej wymienia kolejne wiersze z datą i godziną. Amerykańskie terminale oznaczane są nazwą miasta i dwuliterowym skrótem stanu. Wiersze nie mają kodów numerycznych: Peek odczytuje ich angielską treść. W typowej kolejności na trasie w **USA** (wielokropek zastępuje miasto i stan albo terminal):

| Komunikat R+L | Co oznacza dla Ciebie | Peek pokazuje |
| --- | --- | --- |
| `Picked Up in …` | R+L odebrało ładunek od nadawcy w jego miejscowości. Peek zachowuje pełne brzmienie komunikatu, nie traktując miasta nadawcy jako lokalizacji skanu. | „Przyjęta przez przewoźnika” |
| `Arrived at origin terminal` | Przesyłka dotarła do początkowego terminalu R+L po stronie nadawcy. | „W transporcie” |
| `Departed … in route to …` | Wyjechała z jednego terminalu w kierunku następnego albo terminalu docelowego. | „W transporcie” |
| `Arrived at …` | Dotarła do pośredniego terminalu na trasie, nazwanego miastem i stanem USA. | „W transporcie” |
| `Arrived at destination terminal` | Znajduje się w terminalu docelowym w Twoim rejonie — to ostatni przystanek przed doręczeniem. | „W transporcie” |
| `Shipment Delayed` | Coś wstrzymało przewóz. Podsumowanie R+L oznacza wtedy przesyłkę jako opóźnioną (*Delayed*), zachowując przewidywany dzień dostawy. | „W transporcie” |
| `Delivered` | Doręczona. | „Dostarczona” |

**Podsumowanie nad historią.** Ponad listą zdarzeń wyszukiwarka R+L określa przesyłkę jako *In Transit*, *Delayed* lub *Delivered*. Gdy podsumowanie wskazuje dalszy etap niż wiersze historii, Peek kieruje się podsumowaniem: ładunek podsumowany jako doręczony otrzymuje status „Dostarczona”, nawet jeśli najnowszy wiersz jeszcze tego nie mówi. Jeżeli wyszukiwarka zwraca samo podsumowanie bez żadnych wierszy historii, Peek pokazuje wyłącznie status.

Kod Peek nie zawiera osobnych sformułowań R+L dla wydania do doręczenia, nieudanej próby doręczenia, problemu czy zwrotu. Inne komunikaty przechodzą przez wspólne reguły stosowane przez Peek dla wszystkich przewoźników. Wiersz nierozpoznany przez żadną regułę pozostaje widoczny w oryginalnym brzmieniu i przed doręczeniem liczy się jako sygnał ruchu („W transporcie”); po doręczeniu nic już nie zmienia. Więcej w artykule [statusy śledzenia wyjaśnione](guide:tracking-statuses).

## Jak R+L Carriers doręcza w Stanach Zjednoczonych

Gdy ładunek przemieszcza się po **Stanach Zjednoczonych**, wyszukiwarka R+L podaje przewidywany dzień dostawy, który Peek wyświetla aż do chwili doręczenia. System R+L nie wskazuje żadnego innego przewoźnika na ostatniej mili, a kod Peek nie przekazuje przesyłki żadnemu partnerowi. Informacje na tej stronie dotyczą wyłącznie przewozów towarowych na terenie USA.

Na tej stronie nie podajemy przedziałów godzinowych dostaw, zasad umawiania awizacji, liczby prób doręczenia ani warunków odbioru własnego w amerykańskim terminalu. O sposób i termin dostarczenia ładunku zapytaj sklep lub firmę, która zamówiła przewóz — to ona jest stroną zlecenia w R+L.

## Przesyłka R+L Carriers nieznaleziona, opóźniona, uszkodzona lub zagubiona

**Brak wyników.** Dla nieznanego numeru PRO wyszukiwarka R+L zwraca komunikat `No records found for Number:` oraz numer PRO poprzedzony literą `I`. Na peektracker.com Peek wyświetla wówczas „Oczekiwanie na przewoźnika R+L Carriers” dla numeru przypisanego do R+L Carriers i ponawia zapytanie później. Porównaj każdą cyfrę z wiadomością ze sklepu; jeśli numer się zgadza, zapytaj nadawcę, czy R+L odebrało już towar.

**Błędna cyfra.** Jeżeli cyfra kontrolna numeru PRO się nie zgadza, wyszukiwarka R+L odpowiada numerem PRO z dopiskiem `Pro number failed check digit validation.` Peek pokazuje wtedy „Sprawdź numer śledzenia”. Zweryfikuj numer znak po znaku z potwierdzeniem wysyłki lub dokumentami przewozowymi.

**Opóźnienie lub brak nowych skanów.** Po wpisie `Shipment Delayed` Peek utrzymuje status „W transporcie” wraz z dniem dostawy podanym przez R+L. Gdy przesyłka na etapie „Przyjęta przez przewoźnika” albo „W transporcie” nie ma nowego wiersza przez cztery dni, pojawia się oznaczenie „Brak aktualizacji od czterech dni”. Skontaktuj się ze sklepem lub nadawcą. Zobacz też [dlaczego śledzenie się nie aktualizuje](guide:tracking-not-updating).

**Towar uszkodzony, niekompletny lub zagubiony w USA.** Najpierw powiadom sklep albo firmę wysyłającą, która zleciła fracht. W przypadku ładunków przewożonych transportem drogowym **między stanami USA** prawo federalne **Stanów Zjednoczonych** (`49 U.S.C. 14706` oraz `49 CFR Part 370`) określa następujące zasady:

- Przewoźnik odpowiada za rzeczywistą utratę lub uszkodzenie ładunku wobec osoby uprawnionej na podstawie pokwitowania lub listu przewozowego (*bill of lading*). Odpowiedzialność może zostać ograniczona do wartości zadeklarowanej pisemnie bądź elektronicznie przez nadawcę albo uzgodnionej na piśmie między przewoźnikiem a nadawcą.
- Przewoźnik nie może wyznaczyć krótszego terminu niż 9 miesięcy na zgłoszenie reklamacji ani krótszego niż 2 lata na wniesienie pozwu sądowego, licząc od dnia pisemnego powiadomienia o odrzuceniu jakiejkolwiek części roszczenia. Dokładny termin określa list przewozowy lub umowa.
- Reklamacja to pisemne żądanie złożone u przewoźnika w tych terminach, które identyfikuje przesyłkę, przypisuje odpowiedzialność przewoźnikowi i wskazuje określoną lub możliwą do wyliczenia kwotę pieniężną. Sama adnotacja o uszkodzeniu lub braku na pokwitowaniu odbioru nie stanowi jeszcze formalnej reklamacji.
- Gdy wymaga tego postępowanie wyjaśniające, do reklamacji należy dołączyć list przewozowy, dowód opłacenia frachtu oraz fakturę. Przy zaginięciu całej sztuki ładunku lub całej przesyłki, którego nie da się potwierdzić w inny sposób, przewoźnik uzyskuje od odbiorcy (*consignee*) poświadczone pisemne oświadczenie, że towar nie dotarł z żadnego innego źródła.
- Przewoźnik pisemnie potwierdza wpływ reklamacji w ciągu 30 dni. W terminie 120 dni wypłaca odszkodowanie, oddala roszczenie albo składa na piśmie wiążącą propozycję ugody; jeśli sprawa nie może jeszcze zostać zakończona, informuje pisemnie o stanie postępowania po upływie 120 dni oraz co kolejne 60 dni.

Gdy śledzenie pokazuje `Delivered`, a towar nie dotarł, zajrzyj do poradnika [dostarczona, ale nieotrzymana](guide:delivered-not-received). Sprawdź także [uszkodzona paczka](guide:damaged-parcel) oraz [zagubiona paczka](guide:lost-parcel).

## Obsługa klienta i kontakt z R+L Carriers

Na amerykańskiej stronie internetowej R+L Carriers podane są następujące kanały kontaktu:

- **Telefon:** `800-543-5589`, numer widoczny w nagłówku stron R+L.
- **R+L Cares:** formularz na stronie R+L dotyczący nierozwiązanych spraw, pytań i opinii; R+L informuje, że zgłoszenie trafia bezpośrednio do kierownictwa wyższego szczebla (*R+L Executive Leadership*).
- **Reklamacje ładunków:** odnośnik *Cargo Claims* w menu witryny prowadzi do instrukcji R+L dotyczących zgłaszania szkód w transporcie.

R+L przewozi ładunek na zlecenie podmiotu zamawiającego transport: w sprawie zakupów internetowych zwróć się najpierw do sklepu, a przy przesyłkach firmowych do przedsiębiorstwa nadającego towar.

## Śledzenie przesyłki R+L Carriers w Peek

Sam dziewięciocyfrowy kształt numeru PRO nie zdradza, że należy on do R+L, ponieważ inni przewoźnicy również używają dziewięciu cyfr. Mimo to wklej numer: Peek obsługuje ponad 3500 przewoźników. Przy dwóch literach i siedmiu cyfrach Peek od razu odpytuje R+L i wyświetla „R+L Carriers ma tę przesyłkę.”, gdy R+L zna dany PRO. Jeśli wpisujesz dziewięć cyfr z myślnikiem przed ostatnią cyfrą, Peek pokazuje R+L Carriers w sekcji „Pasują do numeru” i także sprawdza R+L; po dodaniu przesyłki (Peek usuwa myślnik przy zapisie) albo po wklejeniu dziewięciu cyfr bez myślnika Peek odpytuje natomiast TNT, TForce Freight i XPO, o ile numer przechodzi ich walidację, ale nie R+L: stuknij „Zmień” i wyszukaj R+L Carriers po nazwie. Dla zapisanej już przesyłki użyj opcji „Zmień przewoźnika”. Jeżeli później serwis śledzenia pokaże ruch ładunku i wskaże R+L Carriers, Peek przypisze przesyłkę do R+L i przy kolejnych sprawdzeniach będzie pytać bezpośrednio wyszukiwarkę R+L.

Z literą `I` przed dziewięcioma cyframi numer pasuje do R+L Carriers oraz Australia Post: Peek odpytuje obu przewoźników i zapisuje przesyłkę pod tym, który ją rozpozna. Działa również link do strony *Shipment Tracing* R+L zawierający parametr `pro=` — Peek od razu wskazuje wtedy R+L („Wykryty przewoźnik”).

Peek odpytuje wyszukiwarkę R+L wyłącznie po numerze PRO, bez konta i kodu pocztowego. Pokazuje aktualny status, każdy wiersz historii (z miastem i stanem terminalu, gdy wiersz je wymienia), przewidywany dzień dostawy, nazwę odbiorcy na Twojej własnej karcie przesyłki („Odbiorca”, ukrytą na udostępnionych linkach) oraz wagę w jednostkach Twojego urządzenia (w funtach na urządzeniach z amerykańskim systemem miar, w kilogramach w pozostałych krajach; R+L podaje wagę w funtach). Pomija adres odbiorcy, dane nadawcy i płatnika, liczbę sztuk, referencje, rodzaj usługi, numer listu przewozowego oraz dokumenty. Wyszukiwarka R+L nie udostępnia w Peek potwierdzenia doręczenia. Przycisk „Otwórz stronę przewoźnika R+L Carriers” na karcie przesyłki otwiera dany PRO bezpośrednio w wyszukiwarce R+L.

Gdy wyszukiwarka R+L jest niedostępna lub nie udaje się odczytać odpowiedzi, Peek korzysta z [serwisów śledzenia](guide:universal-tracker) i prezentuje zebraną przez nie historię w ich własnym brzmieniu.

Wiersze R+L zawierają datę i godzinę bez strefy czasowej. Peek odczytuje każdy wpis według zegara miasta terminalu, w drugiej kolejności według stanu terminalu (głównej strefy, gdy dany stan USA leży w więcej niż jednej), następnie według sąsiedniego wiersza historii, a w ostateczności według czasu wschodnioamerykańskiego (`America/New_York`), i przelicza wszystkie godziny na strefę Twojego urządzenia („Godziny w Twojej strefie czasowej: …”).

Peek sprawdza aktualizacje nawet co 10 minut, dopóki masz otwartą przesyłkę albo w dniu dostawy przy włączonych powiadomieniach; w pozostałym czasie oraz po dwóch dniach bez nowego wiersza (12 godzinach na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika”) sprawdza co godzinę. Możesz włączyć powiadomienia „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek nie kontaktuje się w Twoim imieniu z R+L, sklepem ani nadawcą, nie umawia terminu dostawy i nie składa reklamacji.

## Najczęstsze pytania o śledzenie R+L Carriers

### Czym jest numer PRO w R+L Carriers?

To numer śledzenia ładunku R+L Carriers w Stanach Zjednoczonych: dziewięć cyfr albo dwie litery i siedem cyfr, z ostatnią cyfrą pełniącą funkcję cyfry kontrolnej, czasem oddzieloną myślnikiem. Wyszukiwarka R+L zapisuje przed nim literę `I`. Numer przekazuje sklep lub firma, która zamówiła przewóz.

### Dlaczego R+L Carriers wyświetla „No records found for Number”?

Wyszukiwarka R+L nie ma tego numeru PRO w bazie. Porównaj każdą cyfrę z e-mailem o wysyłce, upewnij się, że przewoźnikiem jest rzeczywiście R+L Carriers, i zapytaj nadawcę, czy R+L odebrało już ładunek.

### Co oznacza „Shipment Delayed” w R+L Carriers?

Na trasie w USA wystąpiło utrudnienie opóźniające przewóz. Sam wiersz nie podaje przyczyny; podsumowanie R+L oznacza wówczas przesyłkę jako opóźnioną przy zachowaniu szacowanego dnia dostawy, a Peek pozostawia ją w statusie „W transporcie”. O powód opóźnienia zapytaj sprzedawcę lub nadawcę.

### Ile mam czasu na złożenie reklamacji towarowej w R+L Carriers?

Przy drogowym przewozie ładunków między stanami USA amerykańskie prawo federalne zapewnia co najmniej 9 miesięcy na złożenie pisemnej reklamacji u przewoźnika oraz co najmniej 2 lata na skierowanie sprawy do sądu od dnia pisemnego odrzucenia dowolnej części roszczenia. Szczegółowy termin określa list przewozowy lub umowa; ta strona nie opisuje wewnętrznej taryfy R+L. Najpierw skontaktuj się ze zleceniodawcą przewozu; wskazówki R+L znajdują się w sekcji *Cargo Claims* na stronie przewoźnika.

:::sources
- [Universal Parcel Scraper: R+L Carriers](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/rl-carriers) – nazwy, formaty PRO i prefiks `I`, linki śledzenia, komunikaty i statusy, podsumowanie, odpowiedzi przy braku numeru i błędzie cyfry kontrolnej, przewidywany dzień dostawy, odbiorca, waga, strefy czasowe i pomijane pola
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – przewoźnicy współdzielący format, brak przekazywania innemu doręczycielowi oraz zapasowe serwisy śledzenia
- [R+L Carriers: Shipment Tracing](https://www.rlcarriers.com/freight/shipping/shipment-tracing) – wyszukiwanie po numerze PRO lub Pickup Request ID do 25 przesyłek naraz oraz inne referencje z kontem MyRLC
- [R+L Carriers: R+L Cares](https://www.rlcarriers.com/contact/rl-cares) – formularz kierowany bezpośrednio do kierownictwa R+L, numer 800-543-5589 w nagłówku strony i link Cargo Claims
- [GovInfo: 49 U.S.C. 14706, Liability of carriers under receipts and bills of lading](https://www.govinfo.gov/content/pkg/USCODE-2024-title49/html/USCODE-2024-title49-subtitleIV-partB-chap147-sec14706.htm) – odpowiedzialność wobec uprawnionego z listu przewozowego, ograniczenie wartości oraz minimalne terminy 9 miesięcy i 2 lat w USA
- [GovInfo: 49 CFR Part 370, Investigation and voluntary disposition of loss and damage claims and processing salvage](https://www.govinfo.gov/content/pkg/CFR-2025-title49-vol5/xml/CFR-2025-title49-vol5-part370.xml) – wymogi pisemnej reklamacji, adnotacje na dowodzie dostawy, wymagane dokumenty oraz terminy 30, 120 i 60 dni
:::
