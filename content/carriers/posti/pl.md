---
title: Śledzenie Posti: kod JJFI, statusy, automaty i kontakt
description: Śledzenie przesyłek Posti (Finlandia) i SmartPosti: kody JJFI i FI, znaczenie statusów po angielsku, 5 dni w automacie OmaPosti, cło (3 € i 2 €) i kontakt.
slug: sledzenie-posti
published: 2026-11-22
updated: 2026-11-27
---

Posti to fiński operator pocztowy i paczkowy (działający w Estonii, na Łotwie i Litwie jako SmartPosti). Wklej identyfikator przesyłki (*item ID*, np. 21-znakowy kod `JJFI…` albo międzynarodowy numer `…FI`) w pole powyżej lub sprawdź go na [stronie śledzenia Posti](https://www.posti.fi/en/tracking) i w aplikacji OmaPosti, aby zobaczyć angielskie statusy, kod odbioru i termin przechowywania.

## Numer śledzenia Posti: identyfikator JJFI

Posti nazywa go *item ID* (czasem *shipment ID*). Dla własnych paczek Posti zaczyna się od `JJFI`. Strona Posti ma wersję angielską („EN” u góry).

| Numer (wymyślony) | Co oznacza |
| --- | --- |
| `JJFI63482917465283914` | Paczka Posti: `JJFI` i 17 cyfr, łącznie 21 znaków. |
| `RR482917367FI` | Przesyłka polecona lub śledzona nadana w Finlandii w formacie międzynarodowym: dwie litery, dziewięć cyfr, `FI`. |
| `RR482917367CN` | Paczka z zagranicy zachowuje numer nadawcy. Ten kończy się na `CN` (China Post); Posti śledzi ją pod tym samym numerem. |
| `00064382917465283912` | 20-cyfrowy kod kreskowy SSCC; `64` po pierwszych trzech cyfrach to prefiks Finlandii. |

Jeśli nadawca przekazał Posti Twój numer telefonu, paczka pojawi się w OmaPosti sama; w przeciwnym razie dodaj ją ręcznie. Śledzenie Posti odpowiada wyłącznie na dokładny kod, nigdy na nazwisko czy adres.

Kod odbioru i kod otwarcia skrytki (6 lub 9 znaków) nie są numerami śledzenia. Numer zaczynający się od `JJEE` należy do [Omniva](carrier:omniva) (poczty Estonii), a kończący się na `SE` do PostNord w Szwecji. Nie masz pewności, jaki to przewoźnik? Zobacz [formaty numerów przesyłek](guide:tracking-number-formats).

## Statusy śledzenia Posti po angielsku

Śledzenie Posti wyświetla opisy po angielsku, fińsku lub szwedzku zależnie od języka strony, często z dodatkowym wyjaśnieniem. Najczęstsze komunikaty w przybliżonej kolejności:

| Posti pisze | Co to oznacza dla Ciebie |
| --- | --- |
| `We have received information about an upcoming delivery from the sender` | Nadawca zapowiedział paczkę. Posti jeszcze jej nie ma. |
| `Item received for transport` | Posti przyjęło paczkę do transportu. |
| `Item is in transport in country of origin.` | Nadana za granicą. Brak jeszcze szacowanego terminu. |
| `Item arrived in the destination country.` | Dotarła do Finlandii i jedzie dalej z Posti. |
| `The item is in sorting` | W sortowni. Paczki są zwykle sortowane raz lub dwa razy, częściej na długich trasach. |
| `Item accepted from transport` | Nadal w drodze. Niezależnie od brzmienia nie została doręczona. |
| `Item is routed to an outlet because the locker was full.` | Wybrany automat był pełny, więc trafia do punktu Posti. |
| `Item is in delivery transportation.` | W samochodzie kuriera w drodze do Ciebie. Peek pokazuje „W doręczeniu”. |
| `Item is ready for a pick up` | Czeka na odbiór. Miejsce i kod podaje powiadomienie. |
| `We sent the recipient a text message about the item` | Posti wysłało SMS. Paczka się nie przemieściła. |
| `The item has been delivered` | Dostarczona, w tym po Twoim odbiorze. |
| `Item delivered to the recipient.` | Doręczona odbiorcy. |

`Item accepted from transport` często wprowadza w błąd. Posti wyjaśnia, że to wpis transportowy: doręczenie nadal trwa i nikt nie wydał jeszcze paczki. Skany zagraniczne mają w każdym języku lokalizację `ULKOMAILLA` (po fińsku „za granicą”).

## Automaty paczkowe Posti, odbiór i nieudane doręczenia

**Powiadomienie o nadejściu.** Gdy paczka trafi do automatu (*Parcel Locker*) lub punktu obsługi, Posti wysyła szczegóły i kod otwarcia w OmaPosti, SMS-em i e-mailem. Odbierz ją w ciągu 5 dni. Niektóre przesyłki czekają dłużej — np. paczki z zagranicy (zwykle 14 dni) albo te z awizem listownym (9 dni) — kieruj się więc ostatnim dniem odbioru z powiadomienia.

**Więcej czasu.** W OmaPosti funkcja „Extend pickup time” wydłuża termin do 14 dni od pierwotnej daty końcowej za 1,90 € według angielskiej strony Posti albo 1,90 € dziennie według fińskiej strony.

**Przy automacie.** Wpisz kod otwarcia (w automatach z klawiaturą: C, kod, potem OK). Paczkę może odebrać każda osoba z kodem, bez dokumentu. Przesyłki polecone i ubezpieczone wymagają pełnomocnictwa. Przesyłki bez kodu czekają w punkcie Posti — zabierz *item ID* i dokument tożsamości.

**Dlaczego trafiła gdzie indziej.** Paczka za duża do automatu, wymagająca odbioru osobistego albo wysłana z błędnym numerem telefonu trafia do punktu Posti.

**Doręczenie do domu.** Jeśli Posti nie może doręczyć pod drzwi, zawozi paczkę do najbliższego punktu odbioru i powiadamia w OmaPosti, SMS-em lub e-mailem. *Small parcel* zostawia pod drzwiami lub w skrzynce, gdy nikogo nie ma. W OmaPosti zmianę na doręczenie do domu można wybrać tylko dopóki paczka jest jeszcze u nadawcy.

**Zmiany.** Adres może zmienić wyłącznie nadawca. Obsługa klienta może przenieść paczkę do innego punktu odbioru za opłatą. Nieodebrana paczka wraca do nadawcy.

## Paczka Posti opóźniona, zagubiona, uszkodzona lub na cle

**Brak aktualizacji.** Dopóki paczka z zagranicy nie dotrze do Finlandii, Posti nie podaje przewidywanego czasu przybycia. Zobacz [dlaczego śledzenie nie aktualizuje się](guide:tracking-not-updating).

**Zagubiona lub opóźniona.** Jeśli śledzenie nie pokazuje śladu paczki, zgłoś prośbę o wyjaśnienie (*clarification request*) w obsłudze klienta Posti — może to zrobić nadawca lub odbiorca. Jeśli się nie znajdzie, o odszkodowanie występuje nadawca, bo Posti wypłaca je płatnikowi transportu, niemal zawsze sprzedawcy. Według fińskiego prawa za transport odpowiada sklep internetowy i zwykle wysyła zamiennik. Fiński urząd konsumencki KKV uznaje przesyłkę krajową za zaginioną 14 dni po uzgodnionym terminie dostawy albo 28 dni po nadaniu, gdy terminu nie uzgodniono.

**Doręczona, ale jej nie ma.** Przy *Small parcel* sprawdź skrzynkę, a potem skontaktuj się z obsługą klienta. Zobacz [doręczona, ale nieodebrana](guide:delivered-not-received).

**Uszkodzona.** Zgłoś kierowcy przy odbiorze, zrób zdjęcia, wypełnij formularz uszkodzonej przesyłki Posti i poinformuj nadawcę.

**Cło.** Paczka spoza UE (albo z terytoriów UE poza jej obszarem podatkowym, jak Wyspy Alandzkie czy Kanary) może wymagać odprawy celnej przed doręczeniem, na stronie fińskich służb celnych albo w OmaPosti. Czeka 20 dni w magazynie Posti, po czym wraca. Gdy przy odprawie należne są podatki, Posti dolicza opłatę manipulacyjną 3,10 € (3,30 € w placówce), opłacaną na własnej stronie Posti. Od 1 lipca 2026 r. na przesyłki do 150 € obowiązuje też ryczałtowe cło 3 € za pozycję towarową, a od 1 listopada 2026 r. fińskie służby celne pobierają również 2 € za każdą pozycję towarową z zakupów online. Przy zakupie poniżej 150 €, dla którego sklep pobrał VAT w koszyku (IOSS), odprawę robi nadawca i nie płacisz nic przy odbiorze; jeśli brakuje danych płatności cła i opłaty, Posti zwraca paczkę nadawcy. Aktualne stawki podaje [strona celna Posti](https://www.posti.fi/en/receiving/customs-clearance).

> Posti nigdy nie wysyła linków do opłacenia cła ani opłaty manipulacyjnej. Fałszywa wiadomość może zawierać nawet prawdziwy kod śledzenia — usuń ją bez klikania linku.

## Obsługa klienta i numer telefonu Posti

Kanały podane na posti.fi:

- **Telefon:** 0100 5577 albo +358 100 5577 z zagranicy. Koszt połączenia jak na sieć komórkową lub lokalną. Godziny otwarcia podaje [strona obsługi klienta Posti](https://www.posti.fi/en/customer-service).
- **Czat:** w aplikacji OmaPosti.
- **Formularze:** wniosek o odszkodowanie, zgłoszenie uszkodzenia oraz paczka niedoręczalna (gdy Posti nie mogło doręczyć jej ani Tobie, ani z powrotem nadawcy).

Posti nie podaje adresu e-mail dla klientów.

## Śledzenie paczek Posti w Peek

[Peek](/) od razu rozpoznaje numer zaczynający się od `JJFI` jako Posti. Link śledzenia posti.fi (`/tracking/`, `/seuranta/` lub `/uppfoljning/` z numerem, także po kodzie języka jak `/en/`, oraz `/itemtracking/posti/search_by_shipment_id` z `ShipmentId=`) przypisuje Posti do każdego numeru. Kod pocztowy nie jest potrzebny.

Przy numerze pocztowym kończącym się na `FI` Peek pyta najpierw Posti wraz z [Chronopost](carrier:chronopost) i pokazuje Posti po jego rozpoznaniu. Jeśli Posti jeszcze go nie ma, pojawia się „Jeszcze nie znaleziono u: … Peek będzie sprawdzać dalej po dodaniu.”. Na koncie Peek przesyłka jest sprawdzana co godzinę przez 6 godzin, a potem coraz rzadziej; bez konta sprawdzanie ustaje po 6 godzinach, chyba że włączysz „Powiadom mnie, gdy się znajdzie”, co utrzymuje codzienne sprawdzanie do 30 dni od dodania. Dla kodu SSCC jak powyżej Peek pyta Posti razem z Matkahuolto, Swiss Post Cargo, PostNord i NZ Post. Przy błędnej cyfrze kontrolnej wybierz Posti przez „Zmień” (Peek śledzi ponad 4500 przewoźników). Numer zagraniczny śledzisz w Posti po użyciu „Zmień” lub linku posti.fi; w przeciwnym razie Peek śledzi go najpierw w poczcie nadania i gdy ta wskaże Finlandię (lub nie poda kraju, a śledzisz z Finlandii), odpytuje też Posti i przełącza się na nią przy co najmniej tak świeżym skanie, zachowując wcześniejszą historię.

W Estonii, na Łotwie i Litwie Posti działa jako SmartPosti: wyszukiwarka Peek znajduje Posti również pod tą nazwą („Znany też jako SmartPosti”), a linki z smartposti.ee lub itella.ee (oraz .lv i .lt) przypisują Posti każdemu kodowi. Peek zachowuje odpowiedź Posti tylko wtedy, gdy dokładnie jedna przesyłka odpowiada wpisanemu kodowi; jeśli dla kodu SmartPosti nic nie znajdzie, sprawdź go na smartposti.ee.

Peek czyta Posti po angielsku i wyświetla każdy status z opisem wyjaśniającym, miejscowość każdego skanu na mapie, wagę, wymiary oraz nazwę i adres punktu odbioru. Godziny wyświetlają się w strefie Twojego urządzenia; na peektracker.com oraz w aplikacjach na iPhone'a i Androida historia kończy się wierszem „Godziny w twojej strefie czasowej:” z jej nazwą. Po czterech dniach bez nowego skanu notka Peek przy przesyłce pokazuje „Poproś przewoźnika Posti o pomoc”, co prowadzi do obsługi klienta Posti. Peek pomija kod odbioru i Twój adres, nie pokazuje przewidywanej daty doręczenia ani nadawcy. Numer obejmujący wiele paczek nie jest śledzony, a stare paczki widnieją jako nieznalezione.

Gdy masz paczkę otwartą na ekranie albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza status do co 10 minut, a w dniu zeskanowania w doręczeniu od 6:00 czasu lokalnego co 2 minuty, dopóki odpowiada własny system Posti. Poza tym oraz po dwóch dniach bez nowych zdarzeń (po 12 godzinach, gdy przesyłka ma jeszcze status „Zarejestrowana” lub „Przyjęta przez przewoźnika”) sprawdza raz na godzinę. Gdy doręczenie przejmie inny przewoźnik, Peek przejmuje jego tempo. Powiadomienia ustawisz na „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”.

## Pytania o śledzenie Posti

### Jak śledzić paczkę Posti lub SmartPosti po angielsku?

Wklej kod *item ID* (np. `JJFI` + 17 cyfr lub międzynarodowy kod kończący się na `FI`) w polu powyżej w Peek albo otwórz [posti.fi/en/tracking](https://www.posti.fi/en/tracking). Paczki SmartPosti z Estonii, Łotwy i Litwy sprawdzisz także na `smartposti.ee`.

### Jak długo Posti przechowuje paczkę w automacie paczkowym?

Standardowo 5 dni (paczki z zagranicy zwykle 14 dni, a z awizem listownym 9 dni) — dokładną datę podaje powiadomienie z OmaPosti, SMS lub e-mail. W OmaPosti funkcję „Extend pickup time” można wykupić na maksymalnie 14 dni dodatkowo za 1,90 € (1,90 € dziennie według fińskiej strony Posti).

### Co oznacza status `Item accepted from transport` w Posti?

Mimo mylącego brzmienia nie oznacza on doręczenia odbiorcy — Posti wyjaśnia, że to rutynowy wpis transportowy oznaczający, iż przesyłka nadal jest w drodze. Doręczenie potwierdzają dopiero komunikaty `The item has been delivered` lub `Item delivered to the recipient.`.

### Ile trwa doręczenie przesyłki Posti?

Usługa *Small parcel* dociera zazwyczaj w 2 do 5 dni roboczych od przyjęcia przez Posti; dla pozostałych przesyłek Posti podaje szacowany termin doręczenia bezpośrednio w śledzeniu (dla paczek spoza Finlandii dopiero po dotarciu do kraju).

### Jak skontaktować się z obsługą klienta Posti?

Posti nie udostępnia adresu e-mail dla klientów — skorzystaj z czatu w aplikacji OmaPosti albo zadzwoń pod numer 0100 5577 (+358 100 5577 z zagranicy). Pamiętaj też, że Posti nigdy nie wysyła linków do opłacenia cła (3 €/2 €) ani opłaty manipulacyjnej (3,10 €).

:::sources
- [Posti: Picking up an item](https://www.posti.fi/en/receiving/picking-up-an-item) – kod JJFI, powiadomienia, przechowywanie (14 dni zagranica, 9 dni list), przedłużenie, automaty, kody, pełnomocnictwo, zwroty, telefon, czat
- [Posti: Lähetyksen noutaminen](https://www.posti.fi/vastaanottaminen/lahetyksen-noutaminen) – po fińsku: przedłużenie 1,90 €/dzień
- [Posti: Track item](https://www.posti.fi/en/tracking) – komunikaty statusów, wyjaśnienia, ULKOMAILLA, języki
- [Posti: Home delivery](https://www.posti.fi/en/receiving/home-delivery) – nieudane doręczenie, Small parcel, terminy, doręczona nieodebrana
- [Posti: Customs clearance](https://www.posti.fi/en/receiving/customs-clearance) – opłata manipulacyjna, fińskie służby celne, opłata 2 € od 1 listopada 2026, odprawa IOSS przez nadawcę, zwrot przy braku danych płatności
- [Posti: Ordering from abroad and customs clearance](https://www.posti.fi/en/receiving/customs-clearance/ordering-from-abroard-and-custom-clearance) – 20 dni w magazynie, OmaPosti, opłata 3,10 €, cło 3 € i opłata 2 €
- [Posti: What does Posti compensate for and to whom](https://www.posti.fi/en/customer-service/damage-situations/what-does-posti-compensate-for-and-to-whom) – wniosek wyjaśniający, odszkodowanie, zgłoszenie uszkodzenia
- [Posti: Claim for compensation](https://www.posti.fi/en/customer-service/damage-situations/claim-for-compensation) – formularz odszkodowania
- [Posti: Report a damaged item](https://www.posti.fi/en/customer-service/damage-situations/report-damage) – formularz szkody, powiadomienie nadawcy
- [Posti: Undeliverable parcel](https://www.posti.fi/en/customer-service/damage-situations/undeliverable-parcel) – formularz przesyłki niedoręczalnej
- [Posti: Security and phishing messages](https://www.posti.fi/en/customer-service/security-and-phishing-messages) – fałszywe wiadomości, brak linków płatniczych
- [Posti: Privacy statement for Item Tracking](https://www.posti.fi/en/customer-service/terms-and-statements/data-protection/privacy-statements/privacy-statement-for-item-tracking) – śledzenie wyłącznie po dokładnym kodzie, koszt połączenia
- [Posti: Changes to parcel storage periods](https://www.posti.fi/en/latest-news-at-posti/changes-to-parcel-storage-periods-to-improve-availability-at-posti-parcel-lockers-11-8-2025) – 5-dniowe przechowywanie
- [Posti: Customer Service](https://www.posti.fi/en/customer-service) – strona obsługi klienta
- [SmartPosti: Meist](https://www.smartposti.ee/ariklient/meist) – część Posti Group, SmartPosti na Łotwie i Litwie
- [SmartPosti: Saadetise jälgimine](https://www.smartposti.ee/saadetise-jalgimine) – strona śledzenia SmartPosti
- [KKV: Postal and parcel services](https://www.kkv.fi/en/consumer-affairs/telephone-and-internet-subscriptions-postal-and-parcel-services/postal-and-parcel-services/) – kiedy paczka uznawana jest za zaginioną
- [European Commission: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – cło 3 € i unijna opłata manipulacyjna 2 €
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/posti) – formaty, linki SmartPosti/Posti i dane odczytywane przez Peek
:::
