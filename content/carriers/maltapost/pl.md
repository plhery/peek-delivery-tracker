---
title: Śledzenie MaltaPost: numery, statusy i odbiór
description: Śledź przesyłkę MaltaPost na Malcie: jak wygląda numer, co znaczą statusy, odbiór na poczcie lub w automacie Easipik, cło i numer kontaktowy.
slug: sledzenie-maltapost
published: 2026-11-22
updated: 2026-11-22
---

MaltaPost to narodowy operator pocztowy Malty doręczający pod każdy adres na Malcie i Gozo sześć dni w tygodniu. Przesyłkę sprawdzisz na [stronie śledzenia MaltaPost](https://www.maltapost.com/tracking) albo wklejając numer w pole powyżej. Numer pochodzi od nadawcy: z potwierdzenia nadania, etykiety pod kodem kreskowym albo z awiza.

## Numer przesyłki MaltaPost: jak wygląda

Własne przesyłki rejestrowane MaltaPost mają 13 znaków: [dwie litery, dziewięć cyfr](guide:tracking-number-formats) i `MT`. Formularz ponownego doręczenia wymienia lokalne prefiksy RH, RR, RT i RI.

:::anatomy RR 482917367 MT
- RR | Rodzaj przesyłki: RR, RH, RT lub RI dla lokalnych poleconych
- 482917367 | Dziewięć cyfr, ostatnia to cyfra kontrolna
- MT | Nadano na Malcie
:::

Przesyłka z zagranicy zachowuje numer nadany przez pocztę zagraniczną (`RR482917367GB`, `CP482917367CN` lub inny format), a śledzenie MaltaPost go przyjmuje. Strona może wtedy pokazywać zarówno „Foreign Barcode”, jak i „Local Barcode”. Paczki SendOn można śledzić pod dowolnym z nich.

FAQ MaltaPost prosi o wpisywanie numeru wielkimi literami. Jeśli strona odpowiada „Tracking number not recognised.”, sprawdź kod u nadawcy. Niektóre numery, np. zaczynające się od U, nie podlegają śledzeniu na Malcie albo doręcza je lokalnie inna firma. Przesyłki nadanej bez numeru śledzenia nie można śledzić ani odnaleźć. Zgubiłeś numer? Zobacz [gdzie znaleźć numer śledzenia](guide:find-tracking-number).

## Znaczenie statusów śledzenia MaltaPost

Każdy skan podaje datę, godzinę, zdarzenie i lokalizację, czasem z wierszem „Reason:” i „Action:”. Dla przesyłki idącej do Ciebie:

| MaltaPost pisze | Co to oznacza dla Ciebie |
| --- | --- |
| `Item created` | Nadana. Dla przesyłki z zagranicy to przyjęcie przez pocztę zagraniczną. |
| `Item arrived at inbound mail sorting centre` | Dotarła na Maltę. |
| `Item to be submitted for clearance` | Trafia do odprawy celnej. |
| `Item undergoing border agency clearance` | Kontrola celna lub innej służby granicznej. |
| `Item held at inbound mail sorting centre` | Zatrzymana. Przy „Awaiting payment of charges” nic się nie ruszy do czasu zapłaty. |
| `Item returned from local Customs office` | Zwolniona przez urząd celny. |
| `Item to be forwarded to the delivery centre` | W drodze do placówki doręczającej. |
| `Item scheduled for physical delivery` | W doręczeniu. |
| `Item could not be processed further. Unsuccessful attempt-Recipient notified` | Nikt nie mógł odebrać — awizo jest w skrzynce. |
| `Item arrived at collection point` | Gotowa do odbioru w placówce wymienionej w skanie. |
| `Item delivered/collected by recipient` | Doręczona albo odebrana przez Ciebie. |
| `Item could not be processed further. Item not collected. Item returned to sender` | Nieodebrana na czas — wraca do nadawcy. |

Pasek postępu streszcza drogę w etapach takich jak `Arrived in Country`, `Ready for Collection` i `Attempted Delivery`.

Trzy wiersze nie są ruchem paczki: `Declaration Submitted`, `Payment Submitted` i `Online payment complete` zapisują wyłącznie Twoje działania online. `Forwarded to the next processing centre` to przekazanie między centrami MaltaPost. MaltaPost zastrzega, że data i godzina odnoszą się do skanu, nie zawsze do samego zdarzenia.

## Awizo, odbiór i automaty Easipik MaltaPost

MaltaPost próbuje doręczyć przesyłki rejestrowane pod drzwi. Gdy nikogo nie ma, zostawia awizo w skrzynce, a przesyłkę odbierasz we wskazanej placówce. Awiza mają różne kolory i każde wyjaśnia zasady odbioru lub ponownego doręczenia.

**Odbiór:** zabierz awizo i oryginalny dokument tożsamości (dowód osobisty, paszport, prawo jazdy, kartę pobytu lub jednolite pozwolenie na pracę). Osoba odbierająca w Twoim imieniu potrzebuje awiza, Twojego oryginalnego dokumentu i własnego albo pełnomocnictwa. Placówki są zwykle czynne od poniedziałku do soboty 7:30–13:00; w części godziny są inne.

**Nieudane doręczenie?** [Formularz Missed a Delivery](https://www.maltapost.com/delivery/missed-delivery) prosi o kolor awiza, numer śledzenia i zdjęcie awiza. Możesz zamówić ponowne doręczenie pod adres, do innej placówki pocztowej albo do automatu Easipik bądź wydłużyć przechowanie ponad standardowe 12 dni roboczych. Drugie doręczenie kurierem pod adres kosztuje 5,50 € za jedną przesyłkę śledzoną. Po okresie ponownego doręczenia MaltaPost może przechowywać przesyłkę do 12 tygodni. Nieodebrane przesyłki wracają do nadawcy.

**Automaty Easipik:** całodobowe automaty na Malcie i Gozo dla paczek do 40 × 40 × 60 cm i 30 kg. MaltaPost wysyła kod SMS-em i e-mailem; paczka czeka 72 godziny od wysłania kodu, a potem trafia na okienko lokalnej poczty. Dla dostaw MaltaPost Easipik jest darmowy; paczki innych kurierów kosztują 3,50 €.

## Opłaty celne, opóźnienia i zagubione przesyłki MaltaPost

**Zatrzymana do opłacenia.** Towary spoza UE podlegają cłu i VAT, a prawo zabrania MaltaPost ich wydania przed uregulowaniem opłat. Na stronie śledzenia przycisk „Declare Items” służy do przesłania dokumentów, a „Pay Charges” do zapłaty; opłacona przesyłka zostaje zwolniona i doręczona w następny dzień roboczy. Więcej w poradniku [przesyłki w odprawie celnej](guide:customs).

**Brak aktualizacji lub brak paczki.** Przy przesyłce z zagranicy FAQ MaltaPost wskazuje, że reklamację składa się przez pocztę nadania — poproś więc sklep lub nadawcę o jej rozpoczęcie. Za opóźnienie MaltaPost uznaje cztery lub więcej dni roboczych od nadania; zapytania i reklamacje można składać do sześciu miesięcy od nadania, z celem rozpatrzenia międzynarodowych w 90 dni. Zobacz [dlaczego śledzenie przestaje się aktualizować](guide:tracking-not-updating).

**Uszkodzona.** Sprawdź przesyłkę rejestrowaną lub paczkę przy pracowniku MaltaPost podczas odbioru — inaczej MaltaPost nie przyjmuje odpowiedzialności. Odszkodowanie przysługuje nadawcy, który może zrzec się go na Twoją rzecz.

## Obsługa klienta i telefon MaltaPost

Kanały podane na stronie obsługi klienta MaltaPost:

- **Telefon:** (+356) 2122 4421 albo bezpłatny z linii stacjonarnej 800 722 44. Poniedziałek–piątek 8:30–14:30, sobota 7:30–12:30, bez dni świątecznych.
- **E-mail:** info@maltapost.com, również do samodzielnej odprawy celnej lub przez własnego agenta.
- **Formularz kontaktowy** pod FAQ albo wiadomości na Facebooku.
- **Siedziba główna:** 305, Triq Ħal Qormi, Marsa MTP 1001, poniedziałek–piątek 8:00–16:00.
- **Wsparcie Easipik** (automaty i kody PIN): 2124 3324.

> MaltaPost ostrzega przed fałszywymi SMS-ami i e-mailami każącymi kliknąć link i dopłacić przed doręczeniem. Sprawdź, czy domena to maltapost.com, a nie podróbka typu malta-post.com, i zgłaszaj oszustwa do Malta Cyber Crime Unit.

## Śledzenie przesyłek MaltaPost w Peek

Dla numeru zakończonego na `MT` z poprawną cyfrą kontrolną [Peek](/) natychmiast odpytuje MaltaPost i [Chronopost](carrier:chronopost) i wyświetla „MaltaPost ma tę przesyłkę.”, gdy MaltaPost ją zna, albo „Jeszcze nie znaleziono u: … Peek będzie sprawdzać dalej po dodaniu.”. Kod z 5 liter, 9 cyfr i `MT` Peek sprawdza tylko w MaltaPost. Przy błędnej cyfrze kontrolnej MaltaPost nie jest pytana i ręczny wybór również nie pomaga (Peek informuje „nie wydaje takich numerów”) — sprawdź numer u nadawcy. Link śledzenia `maltapost.com` z parametrem `barcode=` zawsze wskazuje MaltaPost dla dowolnego numeru.

Numer z zagranicy Peek śledzi najpierw w poczcie nadania. Gdy tamta poczta poda Maltę jako cel (albo nie wskaże kraju, a Ty śledzisz z Malty), Peek pyta też MaltaPost tym samym numerem i przełącza się na nią po pojawieniu się świeżego skanu, zachowując wcześniejszą historię. Wyszukiwanie MaltaPost przyjmuje takie numery, łącznie z kodami SendOn: aby śledzić od razu w MaltaPost, wybierz „MaltaPost” przez „Zmień” (Peek śledzi ponad 4500 przewoźników) lub wklej link maltapost.com.

Godziny MaltaPost nie mają strefy czasowej: Peek odczytuje je według zegara miejsca skanu (czas maltański na Malcie) i wyświetla w strefie Twojego urządzenia; na peektracker.com oraz w aplikacjach na iPhone'a i Androida historia kończy się wierszem „Godziny w twojej strefie czasowej:” z jej nazwą. Statusy zachowują angielskie brzmienie MaltaPost. Peek potrafi pokazać placówkę, w której przesyłka czeka lub została odebrana; brak planowanej daty i wagi, a przesyłki wyłączonej ze śledzenia przez MaltaPost nie da się śledzić. Gdy przesyłka przestaje się przemieszczać, notatka na jej ekranie pokazuje przycisk „Poproś przewoźnika MaltaPost o pomoc”, który prowadzi do obsługi klienta.

Gdy masz paczkę otwartą na ekranie albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza status do co 10 minut, dopóki odpowiada tracker MaltaPost; od 6:00 czasu maltańskiego w dniu, gdy MaltaPost zeskanuje `Item scheduled for physical delivery`, może sprawdzać co 2 minuty. Gdy odpowiada tylko usługa śledzenia, sprawdza co godzinę, co 3 godziny, gdy ostatni skan ma dobę, a w dniu doręczenia do co 15 minut, gdy masz paczkę otwartą lub włączone powiadomienia. W tle bez powiadomień oraz po dwóch dniach bez nowych zdarzeń (po 12 godzinach, gdy przesyłka ma jeszcze status „Zarejestrowana” lub „Przyjęta przez przewoźnika”) sprawdza raz na godzinę. Gdy doręczenie przejmie inny przewoźnik, Peek przejmuje jego tempo. Przesyłka bez nowego skanu przez cztery dni w statusie „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie” pokazuje na liście dzień ostatniego skanu („Brak aktualizacji od …”). Powiadomienia ustawisz na „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”.

## Pytania o śledzenie MaltaPost

### Czy MaltaPost jest otwarta w soboty?

Tak. Placówki pocztowe są zwykle otwarte w soboty od 7:30 do 13:00, a infolinia działa od 7:30 do 12:30, z wyjątkiem świąt.

### Ile trwa doręczenie MaltaPost?

MaltaPost nie podaje ogólnego czasu dla przesyłek z zagranicy. Poczta międzynarodowa docierająca w dzień roboczy przed 16:30 (w sobotę przed 13:30) jest opracowywana tego samego dnia do doręczenia następnego dnia roboczego; paczki SendOn idą około 7–12 dni z Wielkiej Brytanii, 10–12 z Niemiec lub Włoch i około 15 z USA.

### Czy MaltaPost ma całodobową infolinię?

Nie. Obsługa klienta pracuje w dni robocze 8:30–14:30 i w soboty rano; całodobowo działają jedynie automaty Easipik.

### Jak wygląda międzynarodowy numer śledzenia MaltaPost?

Przesyłka wysłana z Malty za granicę ma dwie litery, dziewięć cyfr i `MT`, np. `RR482917367MT`. Przesyłka przychodząca na Maltę zachowuje zagraniczny numer, akceptowany przez śledzenie MaltaPost.

:::sources
- [MaltaPost: Tracking](https://www.maltapost.com/tracking) – pole śledzenia, kolumny, etapy, kody zagraniczne i lokalne, przyciski celne
- [MaltaPost: Frequently Asked Questions](https://www.maltapost.com/faqs?l=1) – wielkie litery, awiza, godziny placówek, Easipik 72 godziny i wsparcie, opłaty celne, opracowanie tego samego dnia, czasy SendOn
- [MaltaPost: Missed a Delivery?](https://www.maltapost.com/delivery/missed-delivery) – opcje ponownego doręczenia, 12 dni roboczych, 12 tygodni, opłata 5,50 €, lokalne prefiksy
- [MaltaPost: Packet Collection](https://www.maltapost.com/packets) – dokument i awizo przy odbiorze, odbiór przez inną osobę
- [MaltaPost: courier it](https://www.maltapost.com/courier) – próba doręczenia i awizo
- [MaltaPost: Easipik](https://www.maltapost.com/easipik) – automaty, kod SMS i e-mail, wymiary, opłata 3,50 € dla innych kurierów
- [MaltaPost: Parcel Lockers](https://www.maltapost.com/lockers) – lokalizacje na Malcie i Gozo
- [MaltaPost: Post Offices Opening Hours](https://www.maltapost.com/postoffices) – godziny poszczególnych placówek
- [MaltaPost: Customs](https://www.maltapost.com/customs) – zgłoszenie, płatność, odprawa przez info@maltapost.com
- [MaltaPost: How to lodge an Enquiry](https://www.maltapost.com/how-to-lodge-an-enquiry?l=1) – opóźnienie od 4 dni roboczych, termin 6 miesięcy, 90 dni
- [MaltaPost: Committed to Deliver](https://www.maltapost.com/committed-to-deliver) – odszkodowanie dla nadawcy, sprawdzenie przy odbiorze
- [MaltaPost: Customer Service](https://www.maltapost.com/customers-service?l=1) – telefon, bezpłatny numer, godziny, e-mail, siedziba
- [MaltaPost: Scam Protection](https://www.maltapost.com/scamprotection?l=1) – ostrzeżenie o fałszywych SMS-ach i e-mailach
- [MaltaPost: SendOn](https://www.maltapost.com/sendon) – usługa przekierowania, doręczenie pod drzwi lub Easipik
- [MaltaPost: About us](https://www.maltapost.com/about-us) – narodowy operator pocztowy, sześć dni w tygodniu
- [MCA: How can I track and trace registered articles?](https://www.mca.org.mt/content/post-how-can-i-track-and-trace-registered-articles) – numer pod kodem kreskowym
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/maltapost) – formaty, linki i dane odczytywane przez Peek
:::
