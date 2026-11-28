---
title: Śledzenie Whistl: kto doręcza paczkę i co zrobić
description: Śledzenie Whistl (Parcelhub) z Wielkiej Brytanii: numer UPI lub L+17 cyfr, znaczenie statusów, przejęcie przez Royal Mail/Evri/DPD i do kogo zgłosić zaginięcie.
slug: sledzenie-whistl
published: 2026-11-22
updated: 2026-11-27
---

Whistl (dawniej TNT Post UK) to brytyjska firma logistyczna obsługująca wysyłki ze sklepów w Wielkiej Brytanii i przekazująca paczki na ostatni etap przewoźnikom takim jak [Royal Mail](carrier:royal-mail), [Evri](carrier:evri) czy [DPD UK](carrier:dpd-uk). Śledź przesyłkę w systemie Parcelhub firmy Whistl numerem z e-maila sklepu (np. `L` i 17 cyfr) albo wklej link śledzenia w polu powyżej. Poniżej wyjaśniamy angielskie statusy, co dzieje się po `Handed over to …` i do kogo zwrócić się, gdy paczka nie dociera.

## Numer śledzenia Whistl: gdzie go znaleźć

Whistl nie publikuje jednego formatu numerów. W poradnikach podaje, że wyszukiwarka szuka „po UPI (zwykle kodzie kreskowym paczki) lub numerze przesyłki (*Consignment Number*)”: UPI oznacza jedną paczkę, a numer przesyłki obejmuje wszystko nadane razem. Referencje różnią się w zależności od sklepu i przewoźnika — od litery z szeregiem cyfr (np. `L48291736482917364`) po kod kreskowy przewoźnika końcowego.

Znajdziesz go w e-mailu z potwierdzeniem wysyłki ze sklepu, często jako link do śledzenia Whistl. Przy paczkach w pełni śledzonych (*fully tracked*) przewoźnik doręczający wysyła też e-mail i SMS w dniu dostawy pod własną marką i z własnym numerem.

Wyszukiwarka prosi o „Tracking Reference” i opcjonalny kod pocztowy, a następnie wyświetla dla każdej paczki „Tracking Number:”, skany oraz pola „Carrier:” i „Service:”.

Numer może przypominać format innego przewoźnika, np. kod kreskowy firmy doręczającej (znaki wymyślone):

| Format (wymyślony) | Do kogo pasuje |
| --- | --- |
| `L48291736482917364` | `L` i 17 cyfr: numer Whistl, który Peek sprawdza bezpośrednio |
| `RR482917367GB` | Royal Mail (dwie litery, dziewięć cyfr, `GB`) |
| `H00KLM0048291708` | 16-znakowy kod Evri, zobacz [śledzenie Evri](carrier:evri) |
| `48291736482917` | 14 cyfr: m.in. DPD UK, zobacz [DPD UK](carrier:dpd-uk) |
| `ZDBKLM482917` | XDP |

Więcej o rozpoznawaniu numerów w [formaty numerów śledzenia](guide:tracking-number-formats).

## Znaczenie statusów śledzenia Whistl

Śledzenie Whistl działa wyłącznie po angielsku i przekłada skany różnych przewoźników na ujednolicone „zdarzenia znormalizowane”; przesyłki w pełni śledzone pokazują też komentarze przewoźnika. W typowej kolejności:

| Whistl wyświetla | Co to oznacza dla ciebie |
| --- | --- |
| `Shipment Created` | Sklep zarejestrował wysyłkę. Paczka jeszcze nie ruszyła. |
| `Item receipted and processed by Whistl` | Whistl przyjął i przetworzył ją w swoim magazynie. |
| `Handed over to Delivery Carrier` | Przewoźnik wskazany na stronie przejął doręczenie. |
| `Handed over to Royal Mail` | Royal Mail przejęło ją na ostatni etap. |
| `Arrived At Depot` | Dotarła do magazynu na trasie. |
| `Shipment is Out for Delivery` | Dziś w doręczeniu. |
| `DELIVERED - Left as authorised` | Zostawiona w uzgodnionym miejscu, bez podpisu. |
| `Left with Neighbour` | Doręczona sąsiadowi. |
| `Delivered col. point` | Czeka na ciebie w punkcie odbioru. |
| `No-one to receive` | Nikogo nie zastano: szukaj awiza lub wiadomości od przewoźnika. |
| `Held by customs` | Zatrzymana na [cle](guide:customs). |
| `Return - Delivered` | Wróciła i została oddana nadawcy. |

Trzy rzeczy bywają mylące:

- **Samo `Shipment Created`** oznacza tylko rezerwację. Jeśli przez dni nic nie przybywa, sklep mógł jeszcze nie nadać paczki.
- **`Handed over to …`** to często ostatni wpis w Whistl. Dla przesyłek częściowo śledzonych (*part-tracked*) Whistl zaznacza, że po przekazaniu dalsze śledzenie nie jest możliwe — śledź paczkę numerem przewoźnika końcowego.
- **Problemy** takie jak `Delayed`, `Bad Address`, `Refused`, `Lost` lub `Item damaged` oznaczają usterkę; `Shipment Deleted` oznacza anulowanie przez nadawcę. Wpisy `Item Weight Changed` czy `Label reprinted` to jedynie notatki bez przemieszczenia paczki.

## Doręczenia Whistl i nieudane próby

Paczkę dostarcza przewoźnik końcowy, nie Whistl, i obowiązują jego zasady. Po nieudanej próbie doręczenia zostawia kartkę lub powiadomienie elektroniczne z informacją, kto był i kiedy spróbuje ponownie.

Przewodnik Parcelhub (sierpień 2025) podaje dla poszczególnych przewoźników:

| Przewoźnik | Próby i kolejne kroki |
| --- | --- |
| Royal Mail | 1 próba i awizo „something for you”; odbiór w placówce doręczeniowej lub na poczcie, inaczej zwrot po 18 dniach. Od maja 2023 r., gdy paczka nie mieści się w skrzynce lub wymaga podpisu pod nieobecność, trafia w bezpieczne miejsce albo do sąsiada. |
| Evri | Do trzech prób; ustawienia w aplikacji Evri; 2-godzinne okno e-mailem lub SMS-em. |
| DPD | Awizo oraz SMS lub e-mail z wyborem sąsiada, bezpiecznego miejsca, nowej daty lub punktu odbioru; godzinne okno. |
| Yodel | Do trzech prób; adresy na wyspach mogą mieć tylko jedną przed odbiorem na poczcie. |
| [Amazon Shipping](carrier:amazon-shipping) | Do trzech prób w 3 kolejne dni. |
| DHL Parcel UK | Do 2 prób. |
| Parcelforce | 1 próba, z opcjami bezpiecznego pozostawienia i przekierowania. |
| [UPS](carrier:ups) | 1 próba, a następnie powiadomienie cyfrowe z instrukcją ponownego doręczenia. |

Przesyłki zagraniczne z częściowym śledzeniem trafiają do partnerów międzynarodowych i podlegają zasadom lokalnej poczty. Whistl nie ma własnych punktów odbioru: `Delivered col. point` oznacza sklep lub automat przewoźnika końcowego. Odmówiona paczka wraca do nadawcy bez ponownego doręczenia.

Status wskazuje doręczenie, ale paczki nie ma? Poradniki Whistl zalecają sprawdzić awizo, bezpieczne miejsca i sąsiadów. Potem zobacz [doręczona, ale nieotrzymana](guide:delivered-not-received).

## Paczka Whistl niedoręczona, opóźniona lub zaginiona

Strona kontaktowa Whistl prosi konsumentów o kontakt ze sklepem, w którym dokonano zakupu. To sklep ma umowę, zgłasza zapytanie „Where Is My Item” do Whistl i składa ewentualne reklamacje.

Według poradników Whistl:

- Paczka w Wielkiej Brytanii, której nie da się doręczyć, wraca do nadawcy w ciągu 5 dni roboczych od wejścia do sieci Whistl.
- Przy przesyłkach częściowo śledzonych nie przysługuje odszkodowanie za opóźnienia, a po przekazaniu partnerowi Whistl nie może pomóc w razie niedoręczenia.
- Terminy reklamacji nadawcy zależą od przewoźnika: np. do 28 dni od wysyłki w Evri, a w Royal Mail minimum 10 dni kalendarzowych oczekiwania przed złożeniem reklamacji i termin do 80 dni. Skontaktuj się ze sklepem wcześnie.

Gdy śledzenie staje, zajrzyj do [dlaczego śledzenie przestaje się aktualizować](guide:tracking-not-updating).

## Numer i obsługa klienta Whistl

Dane ze strony kontaktowej Whistl:

- **W sprawie twojej paczki:** sklep, w którym kupiłeś towar.
- **Śledzenie:** przycisk „Whistl Tracking Portal” na stronie kontaktowej.
- **Zapytania ogólne:** `01628 702 965`, od 8:30 do 17:30 czasu brytyjskiego.
- **E-mail Customer Services:** `packagescustomerservices@whistl.co.uk`, który według Whistl „nie może pomagać osobom prywatnym w sprawach śledzenia przesyłek”.
- **Siedziba główna:** Network House, Third Avenue, Marlow, Buckinghamshire, SL7 1EY.

Zapytaj sklep, a po przekazaniu — przewoźnika lub pocztę lokalną wskazaną w śledzeniu.

## Śledzenie paczek Whistl w Peek

[Peek](/) obsługuje ponad 4500 przewoźników. Dla 18-znakowej referencji zaczynającej się od `L` i 17 cyfr Peek pyta bezpośrednio Whistl i wyświetla „Whistl ma tę przesyłkę.”, gdy Whistl ją zna. Inne referencje Whistl nie mają własnego formatu: wklej link Whistl (`web.parcelhub.net` lub `despatch.whistl.co.uk` z `/Tracking/reference/…`, albo `trackmyitem.whistl.co.uk` z `/tracking/…`), co od razu wskazuje Whistl („Wykryty przewoźnik”), albo dodaj numer i wybierz „Whistl” przez „Zmień”. Kod pocztowy nie jest wymagany. Referencja w formacie innego przewoźnika (np. kod Royal Mail z `…GB`) śledzi tamtego przewoźnika, nie Whistl.

Peek czyta tracker Parcelhub firmy Whistl. Czasy nie mają strefy: Peek odczytuje je według zegara miejsca skanu, gdy Whistl je podaje, a w innym razie według czasu brytyjskiego, i pokazuje w twojej strefie („Godziny w twojej strefie czasowej:” i jej nazwa). Wyświetla status, właściwy numer Whistl (jeśli różni się od referencji) oraz przewoźnika końcowego wraz z jego numerem. Brak przewidywanej daty dostawy, wagi i punktu odbioru. Opisy zachowują angielską treść Whistl, z wyjątkiem zwykłego `Delivered`, `In transit` i `Returned to sender`, tłumaczonych przez Peek.

Gdy Whistl wskazuje Royal Mail, Parcelforce Worldwide, DPD UK, DHL Parcel UK (DHL eCommerce UK), Amazon Shipping, [InPost](carrier:inpost) (także Yodel, który Peek śledzi jako InPost), UPS, [FedEx](carrier:fedex) lub Evri, Peek może śledzić paczkę dalej u tego przewoźnika. Jeśli wskazano jedynie DPD, przejęcie nie następuje automatycznie: dodaj własny numer tego przewoźnika. Referencja obejmująca kilka paczek nie jest śledzona — dodaj numer każdej paczki osobno.

Gdy masz przesyłkę otwartą w Peek — albo w dniu dostawy przy włączonych powiadomieniach — Peek pyta bezpośrednio Whistl nawet co 10 minut, a w dniu, gdy Whistl nada status „W doręczeniu”, nawet co 2 minuty (albo co 5 minut, gdy musi otworzyć stronę Whistl w przeglądarce) od 6:00 czasu ostatniego skanu (czasu brytyjskiego w Wielkiej Brytanii), dopóki śledzenie Whistl odpowiada; w pozostałym czasie oraz po dwóch dniach bez zmian (12 godzinach, gdy przesyłka nadal ma status „Zarejestrowana” lub „Przyjęta przez przewoźnika”) sprawdza co godzinę, a po siedmiu dniach bez wiadomości przy statusie „Zarejestrowana” co 6 godzin. Dla powiadomień wybierz „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Po czterech dniach bez nowego skanu przy statusie „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie” Peek pokazuje datę ostatniego skanu („Brak aktualizacji od …”) i komunikat „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika Whistl.” z linkiem „Poproś przewoźnika Whistl o pomoc” do strony kontaktowej. Peek nie może kontaktować się z Whistl ani ze sklepem w twoim imieniu.

## Pytania o śledzenie Whistl

### Czy Whistl jest częścią Royal Mail?

Nie. Whistl UK Ltd to osobna firma (dawniej TNT Post UK), do której należy też Parcelhub. Przekazuje jednak wiele przesyłek do Royal Mail na ostatni etap doręczenia, stąd status `Handed over to Royal Mail`.

### Kto doręcza paczki Whistl?

Przewoźnik końcowy, nie Whistl: w Wielkiej Brytanii m.in. Royal Mail, Evri, DPD, Yodel, Amazon Shipping, DHL Parcel UK, Parcelforce lub UPS, a przy wysyłkach zagranicznych lokalni partnerzy pocztowi. Nazwę firmy widać w polu „Carrier:” na stronie śledzenia.

### Co oznacza „Handed over to Delivery Carrier” lub „Handed over to Royal Mail”?

Paczka została przekazana przewoźnikowi doręczającemu. To często ostatni wpis w Whistl, a dla przesyłek częściowo śledzonych (*part-tracked*) Whistl zaznacza, że po przekazaniu dalsze śledzenie nie jest możliwe — dalsze kroki sprawdzaj numerem przewoźnika końcowego.

### Dlaczego mój numer śledzenia Whistl nie działa?

System zwraca „Unable to find shipment”, gdy nie rozpoznaje podanej referencji UPI lub numeru przesyłki. Potwierdź numer w sklepie albo użyj numeru przewoźnika końcowego z jego SMS-a lub e-maila.

### Czy śledzenie Whistl jest wiarygodne i jak zgłosić problem z paczką?

Tak: oficjalne portale to `web.parcelhub.net`, `despatch.whistl.co.uk` oraz `trackmyitem.whistl.co.uk`. W razie zaginięcia lub uszkodzenia napisz do sklepu — Whistl wyraźnie zaznacza, że adres `packagescustomerservices@whistl.co.uk` nie obsługuje pytań prywatnych o śledzenie.

:::sources
- [Whistl: Contact Us](https://www.whistl.co.uk/contact) – konsumenci kierowani do sprzedawcy, telefon i godziny, e-mail Customer Services, portal śledzenia, siedziba
- [Whistl: home page](https://www.whistl.co.uk/) – Whistl UK Ltd, usługi biznesowe, Parcelhub częścią Whistl Group
- [Whistl: Domestic Fully Tracked Customer Guide (October 2025)](https://www.whistl.co.uk/1817/6062/1711/Whistl_Domestic_Fully_Tracked_Customer_Guide_-_V2.2_October_2025.pdf) – UPI i consignment, zdarzenia znormalizowane, SMS-y i e-maile przewoźników, awizo, odmowa, zaginięcie, 5 dni roboczych, reklamacje nadawcy
- [Whistl: Part Tracked Services Customer Guide v1.7](https://www.whistl.co.uk/download_file/force/3171/250) – przekazanie Royal Mail i partnerom, 18 dni, brak odszkodowania za opóźnienie, linki śledzenia, markowe e-maile
- [Parcelhub: Domestic Carrier Guide (August 2025)](https://www.whistlparcels.co.uk/hubfs/Parcels/Documents/Parcelhub_Carrier_Guide_Domestic_-_V1.1_Aug_2025.pdf) – wykorzystywani przewoźnicy, próby i opcje, terminy reklamacji nadawcy
- [Parcelhub: Part Tracked Services Customer Guide V1.0](https://www.whistlparcels.co.uk/hubfs/Parcels/Documents/Parcelhub_Part_Tracked_Customer_Guide_-_V1_September_2024.pdf) – portale śledzenia, wyszukiwanie po UPI lub consignment, brak wsparcia po przekazaniu
- [Parcelhub: Tracking](https://web.parcelhub.net/Tracking) – pola formularza, układ wyników, tylko angielski
- [Whistl Despatch: Tracking](https://despatch.whistl.co.uk/tracking) – ten sam tracker pod marką Whistl
- [Wikipedia: Whistl](https://en.wikipedia.org/wiki/Whistl) – zmiana nazwy z TNT Post UK, ostatni etap Royal Mail
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – pola odczytywane przez Peek, statusy, wykrywanie numerów i częstotliwość sprawdzania
:::
