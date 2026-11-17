---
title: Śledzenie przesyłek USPS: numer, statusy i paczki z USA
description: Śledź paczkę USPS ze Stanów Zjednoczonych: jak wygląda numer śledzenia, co oznaczają statusy po angielsku, cło, reklamacje i kontakt z obsługą USPS.
slug: sledzenie-usps
published: 2026-11-17
updated: 2026-11-17
---

USPS, czyli United States Postal Service, to publiczna poczta Stanów Zjednoczonych: dociera pod każdy adres w kraju, a śledzenie przesyłek (USPS Tracking) jest wliczone bez dopłaty w większość krajowych usług paczkowych oraz w wiele – choć nie wszystkie – przesyłek międzynarodowych. Wpisz numer w USPS Tracking na usps.com albo wklej go w polu powyżej; na terenie USA można go też wysłać SMS-em na numer `28777`. Numer przekazuje nadawca, najczęściej w wiadomości e-mail ze sklepu o nadaniu paczki.

## Numer śledzenia USPS: gdzie go znaleźć i jak wygląda

Na etykiecie jest to ciąg znaków pod kodem kreskowym. Jako odbiorca poproś o niego nadawcę: ani placówki pocztowe (Post Offices), ani infolinia USPS nie są w stanie go dla Ciebie wyszukać. Osoby korzystające w USA z usługi Informed Delivery widzą numery nadchodzących paczek w swoim panelu. Więcej wskazówek w poradniku [jak znaleźć numer przesyłki](guide:find-tracking-number).

Numery USPS liczą od 13 do 34 znaków. Najczęstsze formaty, na fikcyjnych przykładach:

| Numer (przykład fikcyjny) | Co oznacza |
| --- | --- |
| `9400 1000 4829 1057 3649 24` | 22 cyfry zaczynające się od `94`, `95`, `93`, `92` lub `91`: standardowa etykieta paczkowa w USA. |
| `9205 5900 4829 1057 3649 2817 33` | 26 cyfr zaczynających się od `92`. |
| `420 10001 9400 1000 4829 1057 3649 24` | Pełny zapis kodu kreskowego ze skanera: `420`, amerykański kod pocztowy (ZIP Code), a po nim numer paczki. Początek z kodem ZIP można pominąć. |
| `CP482910576US` | Międzynarodowy numer pocztowy: 2 litery, 9 cyfr i końcówka `US`. Przy reklamacjach zagranicznych USPS wymienia zakresy od `EA` do `EZ`, `CA` do `CZ`, `HC` do `HZ` oraz `RA` do `RZ`. |

W USPS Tracking działa także numer spod kodu kreskowego na awizie PS Form 3849 zostawianym przez listonosza w USA.

Niektóre formaty występują również u innych operatorów: 22-cyfrowe numery zaczynające się od `9261` lub `9361` mogą należeć do DHL eCommerce, Austrian Post czy Estafeta, między innymi, a numery 20-cyfrowe stosują też m.in. NZ Post. Jeśli USPS nie rozpoznaje numeru, sprawdź, jakiego przewoźnika podał sklep w mailu. Zobacz też [formaty numerów śledzenia](guide:tracking-number-formats).

## Statusy śledzenia USPS: co oznaczają

USPS prowadzi historię śledzenia po angielsku. Prawie wszystkie poniższe komunikaty wyjaśnia oficjalna pomoc USPS – pokazują one kolejno typową drogę przesyłki i najczęstsze utrudnienia:

| USPS wyświetla | Co to oznacza dla Ciebie | W Peek |
| --- | --- | --- |
| `Merchant Order Receipt Notification` | Elektroniczne powiadomienie ze sklepu o przyjęciu zamówienia; przesyłka nie została jeszcze zeskanowana przez USPS. | „Zarejestrowana” |
| `Shipping Label Created, USPS Awaiting Item` | Nadawca wygenerował etykietę, ale USPS nie ma jeszcze paczki. W razie opóźnienia pytaj sklep. | „Zarejestrowana” |
| `Picked Up by Shipping Partner, USPS Awaiting Item` | Partner logistyczny odebrał paczkę i przekaże ją do USPS. Przewidywany termin dostawy pojawi się po przejęciu jej przez USPS. | „Zarejestrowana” |
| `USPS in Possession of Item` | USPS przyjął lub odebrał przesyłkę. Nie musisz nic robić. | „Przyjęta przez przewoźnika” |
| `Arrived at USPS Facility` | Przesyłka jest sortowana lub jedzie do urzędu doręczającego. Ten wpis może powtarzać się w tej samej sortowni lub w kolejne dni. | „W transporcie” |
| `Arrived at Post Office` | Paczka dotarła do urzędu pocztowego obsługującego Twój adres: według USPS powinna zostać doręczona dziś albo w następny pocztowy dzień roboczy. | „W transporcie” |
| `Out for Delivery` | Doręczyciel wiezie ją dzisiaj – niekoniecznie ten sam, który przynosi listy, i nieraz później w ciągu dnia. | „W doręczeniu” |
| `Delivered` | Doręczono, z zapisaną datą, godziną i miejscowością. | „Dostarczona” |
| `Delivery Status Not Updated`, `Awaiting Delivery` | Do 21:00 czasu lokalnego w dniu `Out for Delivery` nie pojawił się skan doręczenia; USPS zakłada dostawę w następny dzień roboczy. | `Awaiting Delivery`: „W transporcie”; `Delivery Status Not Updated`: brak osobnego etapu, Peek zostaje przy poprzednim, zwykle „W doręczeniu” |
| `No Access to Delivery Location` | Doręczyciel nie mógł dostać się do skrzynki lub drzwi. USPS automatycznie ponawia próbę w następny dzień roboczy. | „Próba doręczenia” |
| `Notice Left (No Authorized Recipient Available)` | Wymagany był podpis, a nikogo nie było na miejscu. Zostawiono awizo PS Form 3849; drugiej próby z urzędu nie ma. | „Próba doręczenia” |
| `Available for Pickup` | Brak możliwości doręczenia pod adres: paczkę odbierzesz w urzędzie pocztowym od następnego dnia roboczego. | „Do odbioru” |
| `Alert` | Opóźnienie spowodowane np. pogodą, klęską żywiołową lub innym zdarzeniem. | „Zgłoszono problem” |

Przy statusie `Available for Pickup` według USPS nie da się zamówić ponownego doręczenia – przesyłkę odbiera się w placówce z dokumentem tożsamości ze zdjęciem. `Notice Left (No Secure Location Available)` oznacza, że listonosz nie znalazł bezpiecznego miejsca do pozostawienia paczki i zabrał ją do urzędu, również bez automatycznej powtórnej próby. `Insufficient Address` oraz `No Such Number` informują według USPS, że przesyłka wraca do nadawcy, który musi nadać ją ponownie z nową opłatą; w Peek oba liczą się jako „Próba doręczenia”. Przy przesyłkach wysyłanych poza USA `Attempted Delivery Abroad` oznacza nieudaną próbę doręczenia przez pocztę w kraju docelowym („Próba doręczenia” w Peek), a dalsze losy pokazują jej kolejne skany. `Held at Delivery Depot/Delivery Office` informuje, że tamtejsza poczta przechowuje paczkę do odbioru w swojej placówce – wpis pojawia się kilka minut po przyjęciu jej na miejscu („Do odbioru” w Peek). Z kolei `Payment of charges - Item being held, addressee being notified` oznacza wstrzymanie wydania do czasu uregulowania należności celno-podatkowych przez adresata, który otrzymuje stosowne powiadomienie („W urzędzie celnym” w Peek).

USPS podaje godzinę każdego skanu w czasie lokalnym miejsca, w którym wykonano operację.

## Paczka z USA do Polski: doręczenie, cło i odbiór w USA

**Kto doręcza w Polsce.** Jeśli numer paczki to międzynarodowy numer pocztowy zakończony na `US` z poprawną cyfrą kontrolną, Peek sprawdza tym samym numerem również pocztę Twojego kraju – w Polsce [Pocztę Polską](carrier:poczta-polska). Jako Twój kraj liczy się region ustawiony w aplikacji na iPhonie albo kraj połączenia w przeglądarce. Gdy Poczta Polska pokaże skan co najmniej tak świeży jak ostatni wpis USPS, Peek przełącza dalsze śledzenie na nią i zachowuje dotychczasową historię z USPS. Dla numerów złożonych wyłącznie z cyfr Peek nie odpytuje innej poczty.

**Cło i opłaty.** Przesyłka z USA przechodzi odprawę celną – szczegóły znajdziesz w poradniku [paczka zatrzymana na cle](guide:customs). W Polsce, podobnie jak w całej Unii Europejskiej, dla zakupów internetowych spoza UE o wartości do 150 € od 1 lipca 2026 r. obowiązuje zryczałtowane cło 3 € za każdą kategorię towarową. Od 1 listopada 2026 r. dochodzi unijna opłata manipulacyjna (*Union handling fee*) w wysokości 2 € za każdą pozycję towarową w przesyłce, niezależnie od jej wartości – jak wyjaśnia Poczta Polska, opłata ta jest przekazywana Krajowej Administracji Skarbowej. Gdy paczka trafia w Polsce do sieci Pocztex PUNKT (placówki pocztowe oraz sklepy partnerskie, m.in. Żabka, ABC, Lewiatan czy Groszek) albo Pocztex AUTOMAT, odbiór następuje po powiadomieniu SMS lub e-mail.

**Nieudane doręczenie na terenie USA.** Po [nieudanej próbie doręczenia](guide:missed-delivery) w Stanach Zjednoczonych listonosz zostawia brzoskwiniową kartę PS Form 3849. Ponowne doręczenie (*Redelivery*) jest bezpłatne, ale nie pod każdym adresem dostępne: można je zlecić raz, wyłącznie pod pierwotny adres i bez wyboru godziny (online do 2:00 CST od poniedziałku do soboty z szansą na doręczenie tego samego dnia, kodem QR z awiza, telefonicznie albo zostawiając podpisane awizo w skrzynce). Paczkę można też odebrać w urzędzie z dokumentem ze zdjęciem i awizem lub upoważnić inną osobę podpisem na odwrocie karty. Dla większości rejestrowanych paczek drugie i ostateczne zawiadomienie przychodzi po 5 dniach, a zwrot po 15 dniach (Priority Mail Express wraca po 5 dniach od pierwszej próby; większość przesyłek z zagranicy czeka 30 dni; w automacie USPS Smart Locker paczka czeka 5 dni, a potem jeszcze 10 dni w okienku).

## Paczka USPS opóźniona, zagubiona lub zwrócona

**Z USA do Polski.** Według USPS poszukiwanie międzynarodowej przesyłki może rozpocząć online wyłącznie nadawca w Stanach Zjednoczonych, a USPS wyjaśnia sprawę z pocztą kraju docelowego. Przy zakupach internetowych skontaktuj się więc ze sklepem. Zaginięcie nadawca może zgłosić od 3 do 90 dni po nadaniu dla Priority Mail Express International oraz od 7 dni do 6 miesięcy dla Priority Mail International i Registered Mail; dla zwykłego First-Class Package International Service bez polecenia zgłoszenie online nie przysługuje. Więcej w poradnikach [zagubiona paczka](guide:lost-parcel) i [śledzenie nie aktualizuje się](guide:tracking-not-updating).

**Z Polski do USA.** Jeżeli wysłana przez Ciebie paczka zaginie lub dotrze uszkodzona w USA, odszkodowanie według USPS przysługuje Tobie jako nadawcy zagranicznemu, chyba że pisemnie zrzekniesz się go na rzecz odbiorcy w USA. USPS radzi odbiorcy poprosić Cię o złożenie reklamacji na poczcie nadania, natomiast uszkodzoną przesyłkę odbiorca powinien niezwłocznie okazać w amerykańskiej placówce pocztowej.

**W obrębie USA.** Orientacyjne czasy przewozu USPS: Priority Mail 2–3 dni robocze, Ground Advantage 2–5, Parcel Select 2–8; gwarantowany termin ma tylko Priority Mail Express. Doręczenia odbywają się od poniedziałku do soboty, według USPS zazwyczaj do 17:00 czasu lokalnego. Jeżeli status `Out for Delivery`, `Arrived at Post Office` lub `Delivery Status Not Updated` nie zmienia się przez pełny pocztowy dzień roboczy, złóż w USPS zgłoszenie serwisowe (*service request*) z numerem śledzenia; zrób to samo, gdy widnieje `Delivered`, a po upływie ponad jednego dnia roboczego paczki nie ma na ganku, w skrzynce, w garażu, u domowników ani w administracji budynku – zobacz [dostarczona, ale nieodebrana](guide:delivered-not-received). Od 7 dni po nadaniu nadawca lub odbiorca mogą uruchomić na usps.com poszukiwanie Missing Mail, dołączając adresy, opis i zdjęcia.

**Odszkodowania krajowe w USA.** Dotyczą wyłącznie przesyłek ubezpieczonych i wymagają oryginału dowodu nadania: uszkodzenie zgłasza się w ciągu 60 dni od nadania, a zaginięcie między 15 a 60 dniem (od 7 do 60 dni dla Priority Mail Express). Zachowaj karton i zawartość do zakończenia sprawy. O zwrotach przeczytasz w poradniku [zwrot do nadawcy](guide:returned-to-sender).

## Kontakt z USPS: infolinia, obsługa klienta i fałszywe SMS-y

Według stanu na 17 listopada 2026 r. strona „Contact Us” w serwisie USPS podaje:

- **1-800-ASK-USPS** (`1-800-275-8777`), od poniedziałku do piątku 8:00–20:30 oraz w soboty 8:00–18:00 czasu wschodniego (ET, zazwyczaj 6 godzin za czasem polskim – 8:00 ET to 14:00 w Warszawie); automatyczna informacja o śledzeniu działa całą dobę.
- **1-800-222-1811** w sprawach śledzenia krajowego i międzynarodowego, w tych samych godzinach. USPS zaznacza, że konsultanci widzą te same dane co Ty w USPS Tracking.
- Formularz „Email Customer Service” oraz czat do problemów technicznych ze stroną usps.com.
- W USA połączenia przez `7-1-1` dla osób niesłyszących, słabosłyszących i z trudnościami w mówieniu.

USPS nie podaje, czy numery `1-800` działają spoza Ameryki Północnej; przy paczce z USA w pierwszej kolejności najlepiej pisać do nadawcy. Jak podkreślają U.S. Postal Inspection Service (USPIS) oraz strona ostrzeżeń USPS, poczta nigdy nie wysyła SMS-ów ani e-maili o paczce bez wcześniejszego zamówienia powiadomień dla konkretnego numeru śledzenia, prawdziwe wiadomości nie zawierają linków, a ponowne doręczenie (*Redelivery*) jest zawsze darmowe. W USA podejrzane SMS-y przekazuje się na numer `7726` lub zgłasza na adres `spam@uspis.gov`; zobacz też [fałszywe SMS-y o paczce](guide:delivery-scam-texts).

Strony USPS przywołane w tym tekście, odczytane 17 listopada 2026 r.: w serwisie about.usps.com „Who we are”; w serwisie faq.usps.com „Where is my package? Tracking Status Help”, „USPS Tracking - The Basics”, „How to find your tracking number”, „PS Form 3849 Redelivery Notice”, „Redelivery - The Basics”, „What are the Second and Final Notice and Return Dates for Redelivery?”, „Picking Up Mail that is Being Held at Your Post Office”, „USPS Smart Locker”, „Delayed mail and packages?” oraz „Scams & Scheme Alerts”; w serwisie www.usps.com „Text Tracking FAQs”, „Receive Mail & Packages”, „Informed Delivery”, „Missing Mail and Lost Packages”, „File a Claim”, „File a USPS Claim: International” oraz „Contact Us”.

## Śledzenie przesyłek USPS z Peek

[Peek](/) od razu rozpoznaje USPS po większości 22-cyfrowych etykiet ze zgodną cyfrą kontrolną, większości 26-cyfrowych numerów zaczynających się od `92` oraz zeskanowanych kodach `420`, z których usuwa prefiks z kodem ZIP. Międzynarodowe numery zakończone na `US` rozpoznaje natychmiast, gdy zaczynają się od `C` (poza `CE` i `CV`), `E`, `L` lub `R` i przechodzą kontrolę. Dla formatów współdzielonych (numerów zaczynających się od `9261` lub `9361` oraz numerów 20-cyfrowych) Peek wyświetla „Sprawdzanie usług śledzenia…”, najpierw pyta pozostałych możliwych przewoźników (DHL eCommerce, Austrian Post, Estafeta albo NZ Post) oraz serwisy śledzące, a stronę USPS otwiera na końcu – może to chwilę potrwać. Jeśli USPS się nie pojawi, stuknij „Zmień” i wybierz go w sekcji „Pasują do numeru” albo użyj później opcji „Zmień przewoźnika”. Działa też link śledzenia z usps.com zawierający numer.

Peek otwiera stronę śledzenia USPS w przeglądarce na swoim serwerze. Jeżeli USPS przekieruje zapytanie na stronę awarii lub je odrzuci, dane uzupełniają serwisy śledzące, choć nie zawsze mają pełną historię. Peek pokazuje status, oś zdarzeń z miejscem każdego skanu i przewidywany dzień doręczenia (oraz 2-godzinne okno, jeśli USPS je podaje). Godzinę każdego skanu odczytuje w strefie czasowej amerykańskiego stanu, w którym go wykonano, i przelicza na strefę Twojego urządzenia („Godziny w Twojej strefie czasowej: …”). Nazwisko osoby kwitującej odbiór i wskazówki dla doręczyciela są pomijane. Wpisy pozostają w oryginalnym angielskim brzmieniu USPS – jedynie `Delivered` wyświetla się po polsku jako „Dostarczona” (natomiast doręczenie zwracanej paczki z powrotem do nadawcy widnieje po angielsku jako „Returned to the sender”).

Peek sprawdza USPS do każdych 30 minut, gdy masz przesyłkę otwartą na ekranie albo w dniu doręczenia masz włączone powiadomienia (także wtedy, gdy paczka jest w doręczeniu), po nieudanym odpytaniu odczekuje godzinę, a w pozostałym czasie sprawdza co godzinę – również po dwóch dniach bez nowego skanu lub po 12 godzinach na etapie „Zarejestrowana” bądź „Przyjęta przez przewoźnika” (oraz co 3 godziny po dobie ciszy, gdy odpowiadają wyłącznie serwisy śledzące). W powiadomieniach wybierasz „Każdy skan”, „Ważne etapy” albo „Tylko doręczenie”.

Gdy [UPS](carrier:ups) lub [FedEx](carrier:fedex) przekazują paczkę do USPS, Peek może kontynuować jej śledzenie w USPS, jak tylko pojawi się tam postęp. Z kolei dla międzynarodowej przesyłki pocztowej z końcówką `PL` lecącej do USA Peek odpytuje tym samym numerem także USPS – gdy tylko [Poczta Polska](carrier:poczta-polska) wskaże Stany Zjednoczone jako kraj przeznaczenia albo gdy Twoim krajem dla Peek są USA. Ponowne doręczenie, Informed Delivery i reklamacje obsługuje bezpośrednio usps.com: Peek nie kontaktuje się w Twoim imieniu ani z USPS, ani ze sklepem.

## Najczęstsze pytania o śledzenie USPS

### Co oznacza komunikat „Tracking Not Available” w USPS?

USPS nie znajduje danych dla podanego numeru: jest nieznany, zawiera literówkę albo wygasł. Sprawdź każdy znak – USPS przechowuje historię w USPS Tracking przez 120 dni, a dla rejestrowanych przesyłek międzynarodowych do 2 lat.

### Czy każdą paczkę z USA da się śledzić?

Nie. Według USPS śledzenie jest wliczone w wiele, ale nie we wszystkie usługi międzynarodowe, a zakres skanów zależy też od kraju docelowego. Zapytaj sprzedawcę, czy przesyłka ma numer śledzenia.

### Czy USPS doręcza paczki w niedziele?

Tylko przesyłki Priority Mail Express oraz paczki Amazon – podaje USPS. Wszystkie pozostałe usługi doręczane są od poniedziałku do soboty.

### Czy USPS podaje dokładną godzinę doręczenia?

Nie. USPS może pokazać szacunkowe 2-godzinne okno dostawy (*Expected Delivery Window*), które nie jest gwarantowane, i wprost zaznacza, że nie wie, o której dokładnie listonosz dotrze pod dany adres. Peek wyświetla przewidywany dzień oraz to okno, jeśli USPS je udostępnia.

:::sources
- [U.S. Postal Inspection Service: Smishing – Package Tracking Text Scams (po angielsku)](https://www.uspis.gov/news/scam-article/smishing/) – SMS-y wyłącznie na życzenie po podaniu numeru śledzenia, brak linków w prawdziwych wiadomościach, zgłaszanie pod 7726 i spam@uspis.gov
- [Komisja Europejska: EU customs reform (po angielsku)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – cło 3 € do 150 € od 1 lipca 2026 r. oraz opłata manipulacyjna 2 € od 1 listopada 2026 r.
- [Komisja Europejska: Customs Guidance on EUR 3 customs duty (po angielsku)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – naliczanie za każdą kategorię towaru niezależnie od wartości przy opłacie 2 €
- [Poczta Polska: Nowe przepisy Unii Europejskiej – opłata za przesyłki spoza UE](https://media.poczta-polska.pl/releases/nowe-przepisy-unii-europejskiej-tak-dziala-nowa-oplata-za-przesylki-spoza-ue) – opłata 2 € za pozycję towarową od 1 listopada 2026 r. przekazywana KAS
- [Poczta Polska: Odbiór w Punkcie – FAQ](https://odbiorwpunkcie.poczta-polska.pl/en/faq/) – sieć Pocztex PUNKT i Pocztex AUTOMAT oraz powiadomienia SMS/e-mail
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formaty numerów, mapowanie statusów i etapów, strefy czasowe USA oraz przekazywanie śledzenia do poczt krajowych
:::
