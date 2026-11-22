---
title: Śledzenie Matkahuolto: statusy, punkty odbioru i kontakt
description: Śledź paczkę Matkahuolto w Finlandii: numer przesyłki, angielskie statusy, kody odbioru, czas przechowywania, automaty i kontakt.
slug: sledzenie-matkahuolto
published: 2026-11-22
updated: 2026-11-22
---

Matkahuolto to fiński przewoźnik paczkowy z punktami odbioru i automatami w całej Finlandii, w tym w części sklepów K-Market i kiosków R-kioski. Przesyłkę sprawdzisz na matkahuolto.fi/tracking (po angielsku) lub w aplikacji Paketit za pomocą identyfikatora przesyłki z e-maila ze sklepu albo SMS-a od Matkahuolto, lub wklejając go powyżej.

## Numer śledzenia Matkahuolto i gdzie go znaleźć

Matkahuolto nazywa go identyfikatorem przesyłki (*lähetystunnus* po fińsku, *shipment ID* lub *consignment ID* po angielsku). Znajdziesz go w e-mailu z wysyłką ze sklepu i w każdym SMS-ie lub e-mailu od Matkahuolto. Kod odbioru z powiadomienia o nadejściu to co innego: otwiera skrytkę albo okazuje się go przy kasie punktu odbioru.

Matkahuolto nie publikuje swoich formatów. Katalog Peek zna takie kształty (z fikcyjnymi cyframi):

| Numer | Co oznacza |
| --- | --- |
| `MH482910372FI` | `MH`, 9 cyfr i `FI`. |
| `MA7394028156342019482` | `MA` i 19 cyfr. |
| `00373482910375618296` | 20-cyfrowy kod kreskowy paczki (SSCC). Śledzenie Matkahuolto znajduje go też jako 18 cyfr, bez `00`. |

Numery zaczynające się od `JJFI` oraz [numery pocztowe](guide:tracking-number-formats) zakończone na `FI` z innymi literami niż `MH` (`RR482910372FI`) należą do [Posti](carrier:posti), poczty Finlandii. Jeśli Matkahuolto nie znajduje numeru, sprawdź przewoźnika w e-mailu ze sklepu.

Przeniesienie paczki do innego punktu odbioru tworzy nowy identyfikator przesyłki. Paczka GLS przekazana Matkahuolto w Finlandii pokazuje swój numer Matkahuolto w śledzeniu GLS jako „Finland parcel number”, zaczynający się od `00`.

Brak numeru? Jeśli masz konto w Matkahuolto lub korzystasz z aplikacji Paketit, paczki wysłane na twój telefon lub e-mail pojawią się tam automatycznie; w przeciwnym razie nadawca może dopisać twoje dane. Zobacz [jak znaleźć numer śledzenia](guide:find-tracking-number).

## Co oznaczają statusy Matkahuolto

Śledzenie Matkahuolto działa po fińsku (domyślnie), szwedzku i angielsku, a każdy skan zawiera datę, godzinę i miejscowość. Angielskie opisy po kolei:

| Status Matkahuolto | Co oznacza dla ciebie |
| --- | --- |
| `Matkahuolto is waiting for the consignment from the sender` | Nadawca [utworzył przesyłkę](guide:tracking-statuses); Matkahuolto nie ma jeszcze paczki. |
| `Consignment is being processed` | Zeskanowana w terminalu. |
| `The consignment is on its way` | W drodze między terminalami. |
| `Your parcel has been sorted and will be taken next to the pickup point.` | Posortowana w ostatnim terminalu; dalej jedzie do punktu odbioru. |
| `We will deliver the consignment to the pickup point during the day` | Dziś w drodze do twojego punktu odbioru. |
| `Your pick up point has changed` | Trafia do innego punktu wskazanego w tym wierszu. |
| `The shipment is available for pickup with the pickup code at the pick up point` | Gotowa do odbioru, z nazwą punktu i ostatnim dniem odbioru. |
| `We have sent the recipient a message about the arrival of the consignment (sms).` | Wysłano powiadomienie o nadejściu. Nie oznacza przemieszczenia. |
| `The consignment has been delivered. Thank you for choosing Matkahuolto!` | Odebrana z punktu lub automatu. |

**W ciągu dnia.** Pierwszy punkt na trasie kuriera nie musi być twoim, zaznacza Matkahuolto, ale paczka powinna być gotowa do odbioru tego samego dnia; otrzymasz powiadomienie, gdy dotrze.

## Punkty odbioru, automaty i przesyłki do domu

**Odbiór.** Wystarczy kod odbioru z powiadomienia. Dowód nie jest potrzebny, chyba że powiadomienie wskazuje doręczenie do rąk własnych (zwykle alkohol lub dokumenty). Paczkę może odebrać każdy z kodem, z wyjątkiem zewnętrznych automatów otwieranych aplikacją, które otwiera wyłącznie telefon odbiorcy w aplikacji Paketit. Jeśli powiadomienie nie wskazuje automatu, zapytaj przy kasie punktu odbioru.

**Powiadomienie o nadejściu.** Najpierw powiadomienie z aplikacji Paketit, potem SMS lub WhatsApp oraz e-mail, jeśli nadawca go podał.

**Czas przechowywania.** 5 dni, po czym paczka wraca do nadawcy; 14 dni, gdy awizo przychodzi papierowo z powodu braku telefonu i e-maila. Możesz dokupić 1 do 16 dodatkowych dni w aplikacji lub po zalogowaniu na stronie, chyba że nadawca to zablokował. Ponieważ partnerzy tacy jak GLS podają inne terminy (10 dni na fińskiej stronie GLS), kieruj się ostatnim dniem odbioru ze swojego powiadomienia.

**Zmiana punktu.** Raz bezpłatnie w aplikacji: przed przejęciem paczki przez Matkahuolto według angielskiego FAQ, albo przy pierwszym powiadomieniu według szwedzkiej strony; nie podczas transportu. Po nadejściu powiadomienia możesz wykupić przeniesienie.

**Dostawy pod drzwi.** *Home Parcels* doręczane są w dni robocze 8:00–18:00 (czasu fińskiego); *Business Parcels* i paczki [FedEx](carrier:fedex) w dni robocze 8:00–16:00 (czasu fińskiego), bez wcześniejszej zapowiedzi. Powiadomienie podaje planowany dzień, który możesz przesunąć na jeden z kolejnych pięciu dni roboczych w dniu otrzymania wiadomości. Paczka zostaje zostawiona w bezpiecznym, osłoniętym miejscu przy drzwiach; jeśli to niemożliwe i nie ma z tobą kontaktu, kolejnego dnia roboczego trafia do pobliskiego punktu odbioru.

**Wyspy Alandzkie.** Od 1 października 2026 r. przesyłki Matkahuolto na Wyspach Alandzkich doręcza Åland Post, a jej placówki, agencje, automaty i doręczenia domowe należą do sieci Matkahuolto.

**Zwroty.** Pobierz kod aktywacyjny przyciskiem `Palauta` w aplikacji lub na stronie, napisz go na paczce z imieniem, nazwiskiem i telefonem i nadaj w automacie lub punkcie odbioru.

## Opóźniona, zatrzymana lub uszkodzona paczka Matkahuolto

Większość paczek dociera dzień po nadaniu, podaje Matkahuolto, a wszystkie w ciągu trzech dni od przyjęcia przez przewoźnika (trzech dni roboczych w fińskim FAQ). Paczki z zagranicy mogą wcześniej iść przez innych przewoźników i dotrzeć do Matkahuolto po nieco ponad tygodniu.

**Brak aktualizacji.** Śledzenie może się zatrzymać np. w oczekiwaniu na wyjazd lub kontrolę opakowania. Jeśli nadal nic się nie dzieje, Matkahuolto prosi o kontakt z nadawcą. Zobacz [śledzenie nie aktualizuje się](guide:tracking-not-updating).

**Brak powiadomienia o nadejściu.** Zwykle literówka w danych kontaktowych: sprawdź spam i poproś nadawcę o poprawkę.

**Uszkodzona lub zagubiona.** Zgłoś niezwłocznie sklepowi lub nadawcy ze zdjęciami. Reklamację w Matkahuolto składa firma wysyłająca, a między osobami prywatnymi — nadawca. Widoczne uszkodzenia zgłoś przy odbiorze; w przeciwnym razie pisemnie w ciągu 7 dni, lub 30 dni jako konsument. Odpowiedzialność Matkahuolto wynosi 20 € za kg lub wartość towaru, jeśli jest niższa.

## Obsługa klienta i telefon Matkahuolto

Kontakty podane na stronach Matkahuolto:

- **Obsługa paczek:** 0600 11300, pon.–pt. 8:00–18:00 (czasu fińskiego), 2,49 €/min plus opłata lokalna.
- **Wsparcie techniczne paczek:** +358 20 33 6161, pon.–pt. 8:00–10:00 i 14:00–16:00 (czasu fińskiego), stawka lokalna — dla automatów, automatów z aplikacją i paczek pod drzwi.
- **Czat:** z chatbotem Mauno na [stronie obsługi klienta paczek](https://www.matkahuolto.fi/customer-service/parcels) lub w aplikacji Paketit, gdzie rozmowę może przejąć konsultant.
- **Formularz opinii:** po fińsku pod adresem matkahuolto.fi/palautelomake. Adres e-mail dla odbiorców nie jest publikowany.

> Matkahuolto ostrzega przed fałszywymi wiadomościami, podszywaniem się pod nadawcę i fałszywymi stronami jak matkahuoltoo.fi. Podkreśla, że nigdy nie pośredniczy w płatnościach ani nie działa jako bramka płatnicza; jedyny wysyłany link płatniczy trafia do nadawców za koszty zwrotu. Każdą wiadomość sprawdź, wpisując numer przesyłki w śledzeniu lub aplikacji Matkahuolto; jeśli zapłaciłeś, zadzwoń do banku.

## Śledzenie paczki Matkahuolto w Peek

Dla numeru `MH…FI` [Peek](/) odpytuje Matkahuolto, [Posti](carrier:posti) oraz [Chronopost](carrier:chronopost) i wyświetla „Matkahuolto ma tę przesyłkę.”, gdy zna ją tylko Matkahuolto; jeśli zna ją też Posti, wybierasz sam. Numer `MA` trafia wyłącznie do Matkahuolto. Przy 18- lub 20-cyfrowym kodzie kreskowym z fińskim `64` lub szwedzkim `73` po pierwszej cyfrze (albo po `00` i jednej cyfrze) Peek pyta też Matkahuolto wraz z innymi pasującymi przewoźnikami i wybiera Matkahuolto, gdy tylko ona ją zna. Jeśli [Posti](carrier:posti) zna również kod `64`, albo Bring kod `73`, Peek wybierze tamtego przewoźnika — przełącz na Matkahuolto przez „Zmień”. Jeśli zna ją też np. PostNord, decydujesz ty. Inne kody nie są kierowane do Matkahuolto, więc wybierz ją przez „Zmień” (lub „Zmień przewoźnika”). Link `matkahuolto.fi` z `parcelNumber=` przypisuje każdy numer do Matkahuolto, lecz jej wyszukiwanie przyjmuje wyłącznie kody `MH…FI`, `MA` oraz pocztowe i SSCC/GS1 z poprawną cyfrą kontrolną.

Skany Matkahuolto nie zawierają strefy czasowej: Peek czyta je według czasu fińskiego i przelicza na twoją strefę czasową („Godziny w twojej strefie czasowej:”). Statusy zachowują angielskie brzmienie Matkahuolto. Peek pokazuje punkt odbioru lub automat z miastem i ulicą oraz nazwę usługi (gdy jest podana i nie jest to pobranie), ale nie dostaje przewidywanego dnia doręczenia ani wagi, pomija usługi dodatkowe i kody pocztowe i nie wyodrębnia osobno ostatniego dnia odbioru (znajduje się w opisie statusu i powiadomieniu). Peek nie przełącza przesyłki na innego przewoźnika.

Peek sprawdza status do co 10 minut, a w dniu przejścia paczki do etapu „W doręczeniu” co 2 minuty od 6:00 według zegara ostatniego skanu (czasu fińskiego), gdy masz paczkę otwartą w Peek lub włączone powiadomienia i strona Matkahuolto odpowiada. Po dwóch dniach bez zmian (po 12 godzinach na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika”) sprawdza co godzinę. W powiadomieniach możesz wybrać „Wszystkie aktualizacje”, „Tylko ważne” albo „Tylko w dniu dostawy”. Po czterech dniach bez skanu w transporcie, poza odprawą celną i zgłoszonym problemem, Peek pokazuje „Brak aktualizacji od …”, uwagę „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika Matkahuolto.” i opcję „Poproś przewoźnika Matkahuolto o pomoc”. Peek śledzi ponad 4500 przewoźników, ale nie skontaktuje się za ciebie z Matkahuolto ani z nadawcą.

## Pytania o śledzenie Matkahuolto

### Czy Matkahuolto to prawdziwy przewoźnik?

Tak, Matkahuolto to działający fiński przewoźnik. Każdą wiadomość z żądaniem zapłaty w jego imieniu traktuj jako oszustwo: Matkahuolto podkreśla, że nigdy nie pośredniczy w płatnościach.

### Czy śledzenie Matkahuolto działa po angielsku?

Tak. Wersja angielska działa pod adresem matkahuolto.fi/tracking, a język aplikacji Paketit zmienisz w ustawieniach.

### Czy mogę śledzić paczkę Matkahuolto bez kodu?

Tak, jeśli masz konto lub aplikację Paketit, a nadawca podał twój numer telefonu lub e-mail — przesyłka pojawi się tam sama. W przeciwnym razie poproś sklep o identyfikator przesyłki.

### Ile idzie paczka Matkahuolto?

Zwykle 1–2 dni robocze do automatów i punktów odbioru i maksymalnie trzy dni od przyjęcia przez Matkahuolto (trzy dni robocze według fińskiego FAQ). Paczki z zagranicy potrafią dotrzeć do Matkahuolto po nieco ponad tygodniu.

:::sources
- [Matkahuolto: How to receive a parcel?](https://www.matkahuolto.fi/customer-service/parcels/how-to-receive-a-parcel) – kod odbioru, dowód, powiadomienia, zmiana punktu, dostawa do domu, szkody, telefony
- [Matkahuolto: Frequently asked questions](https://www.matkahuolto.fi/frequently-asked-questions) – dostawa do domu, nieudane próby, telefony
- [Matkahuolto: Track your parcel](https://www.matkahuolto.fi/customer-service/parcels/track-your-parcel) – widoki śledzenia, czasy dostawy, przerwy, kontakt z nadawcą
- [Matkahuolto: Receive your parcel easily](https://www.matkahuolto.fi/packages/receive-a-parcel) – 1–2 dni robocze, powiadomienia, 1–16 dodatkowych dni przechowywania
- [Matkahuolto: Pakettien säilytysaika muuttuu](https://www.matkahuolto.fi/news/pakettien-sailytysaika-muuttuu-tavoitteena-entistakin-sujuvampi) – 5 i 14 dni, przedłużenia
- [Matkahuolto: Domestic parcels](https://www.matkahuolto.fi/packages/domestic-parcels) – 5 dni przechowywania, zwrot do nadawcy
- [Matkahuolto: Parcel lockers and parcel points in Finland](https://www.matkahuolto.fi/packages/service-point-network) – sieć, automaty z aplikacją
- [Matkahuolto: Transport damage guide for consumers](https://www.matkahuolto.fi/packages/damage-in-transit) – kto reklamuje, terminy, zdjęcia
- [Matkahuolto: Kuljetusvahinko-ohje kuluttajille](https://www.matkahuolto.fi/paketit/kuljetusvahingot) – terminy, 20 € za kg
- [Matkahuolto: About scam messages](https://www.matkahuolto.fi/packages/about-scam-messages) – fałszywe wiadomości, prawdziwy nadawca, identyfikator przesyłki
- [Matkahuolto: Return the arrived parcel easily](https://www.matkahuolto.fi/packages/return-parcel) – zwroty z kodem aktywacyjnym
- [Matkahuolto: Paketit app](https://www.matkahuolto.fi/packages/paketit-app) – język aplikacji, czat
- [Matkahuolto: Customer service for parcel services](https://www.matkahuolto.fi/customer-service/parcels) – chatbot Mauno
- [Matkahuolto: Usein kysyttyä](https://www.matkahuolto.fi/usein-kysyttya) – cena infolinii, trzy dni robocze, Business Parcels i FedEx 8:00–16:00, formularz
- [Matkahuolto: Seuraa pakettisi matkaa](https://www.matkahuolto.fi/asiakaspalvelu/paketit/seuraa-paketin-matkaa) – nieco ponad tydzień z zagranicy, link płatniczy zwrotu dla nadawców
- [Matkahuolto: Track the parcel](https://www.matkahuolto.fi/tracking) – śledzenie po angielsku, fińsku i szwedzku, brzmienie statusów
- [Matkahuolto: Ta emot paket](https://www.matkahuolto.fi/paket/ta-emot-paket) – po szwedzku: darmowa zmiana punktu przy pierwszym powiadomieniu
- [Matkahuolto: Spåra ditt paket](https://www.matkahuolto.fi/kundservice/paket/spara-ditt-paket) – po szwedzku: wszystkie paczki w trzy dni
- [Matkahuolto: Åland Post blir Matkahuoltos nya transportpartner på Åland](https://www.matkahuolto.fi/news/%C3%85land%20Post%20blir%20Matkahuoltos%20nya%20transportpartner%20p%C3%A5%20%C3%85land) – po szwedzku: Åland Post na Wyspach Alandzkich od 1 października 2026 r.
- [GLS Finlandia: Lähetysseuranta](https://gls-group.com/FI/fi/laehetysseuranta.html) – punkty Matkahuolto, Finland parcel number, 10 dni
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – co czyta Peek
:::
