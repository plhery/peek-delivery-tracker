---
title: Śledzenie Canada Post: numery, statusy, awizo i cło
description: Jak śledzić paczkę Canada Post: kody z końcówką CA i 16-cyfrowe, znaczenie statusów po angielsku, awizo, doręczenie przez Pocztę Polską i cło.
slug: canada-post-tracking
published: 2026-11-21
updated: 2026-11-21
---

Canada Post (Canada Post Corporation / Postes Canada) to federalna korporacja Korony prowadząca publiczną pocztę Kanady. Przesyłkę sprawdzisz na [stronie Track Canada Post](https://www.canadapost-postescanada.ca/track-reperage/en), w bezpłatnej aplikacji mobilnej albo wklejając numer w pole powyżej. Kod udostępnia wyłącznie nadawca – najczęściej w wiadomości e-mail ze sklepu – ponieważ Canada Post nie wyszukuje paczek po nazwisku ani adresie. Jeśli numer kończy się na `CA` i śledzisz przesyłkę spoza Kanady, Peek może odpytać pod tym samym numerem również pocztę Twojego kraju.

## Numer śledzenia Canada Post: jak wygląda

Peek dopasowuje do Canada Post dwa główne formaty numerów (poniżej ze zmyślonymi cyframi):

| Przykład (fikcyjny) | Co oznacza |
| --- | --- |
| `4018 2930 5746 1826` | 16 cyfr; ostatnia to cyfra kontrolna. Szesnastocyfrowe numery stosują też DHL eCommerce, [Correos Express](carrier:correos-express), [TNT](carrier:tnt) oraz [Evri](carrier:evri). |
| `RR482910576CA` | Międzynarodowy numer pocztowy (13 znaków): 2 litery (np. `RR` lub `EE482910576CA`), 9 cyfr i `CA`; ostatnia cyfra przed `CA` jest cyfrą kontrolną. |

Na terenie Kanady Canada Post potrafi też odnaleźć przesyłkę po 15-cyfrowym numerze z karty awiza (*Delivery Notice Card*, np. `482910573649182`) albo po referencji złożonej wyłącznie z cyfr – w Peek (referencja musi mieć co najmniej 11 cyfr) wystarczy wtedy ręcznie wskazać Canada Post, jak opisujemy niżej.

Inny popularny kanadyjski przewoźnik, Purolator, używa własnych kodów 12-cyfrowych albo złożonych z 3 liter i 9 cyfr; dla takich numerów Peek sam nie pyta Canada Post. Gdy Canada Post nie rozpoznaje Twojego numeru, sprawdź przewoźnika w e-mailu ze sklepu i zobacz [jak znaleźć numer śledzenia](guide:find-tracking-number) oraz [formaty numerów śledzenia](guide:tracking-number-formats).

## Statusy śledzenia Canada Post po angielsku

Peek pobiera zdarzenia Canada Post po angielsku. Poniżej najczęstsze komunikaty w oryginalnym brzmieniu wraz z etapem w Peek:

**W Kanadzie oraz w eksporcie za granicę**

| Canada Post wyświetla | Co to oznacza dla paczki | W Peek |
| --- | --- | --- |
| `Electronic information submitted by shipper` | Nadawca wygenerował etykietę, ale Canada Post nie ma jeszcze paczki. | „Zarejestrowana” |
| `Item accepted at the Post Office` | Przesyłka nadana w kanadyjskiej placówce pocztowej. | „Przyjęta przez przewoźnika” |
| `Item picked up by Canada Post` | Odebrana od nadawcy przez kuriera Canada Post. | „Przyjęta przez przewoźnika” |
| `Item processed` | Opracowywana w sortowni Canada Post (wiersz może pojawić się kilkukrotnie). | „W transporcie” |
| `International item being forwarded to destination country` | Wysłana z Kanady do kraju przeznaczenia (`Item has arrived in foreign country` po przylocie). | „W transporcie” |
| `Item presented to customs` | Przekazana do odprawy celnej w kraju docelowym. | „W urzędzie celnym” |
| `Item out for delivery` | Wydana dziś do doręczenia w Kanadzie. | „W doręczeniu” |
| `Delivered to your community mailbox, parcel locker or apt./condo mailbox` | Doręczona do osiedlowej skrzynki pocztowej, skrytki paczkowej lub skrzynki w budynku. | „Dostarczona” |
| `Delivered to recipient's safe drop location` | Pozostawiona w bezpiecznym miejscu pod adresem (podobnie jak `Delivered to mailroom` lub `Delivered to your concierge or building manager`). | „Dostarczona” |
| `Notice card left indicating where and when to pick up item` | Nieudana próba doręczenia; zostawiono awizo z adresem placówki i terminem odbioru. | „Próba doręczenia” |
| `Item available for pickup at Post Office` | Gotowa do odbioru we wskazanej placówce pocztowej. | „Do odbioru” |
| `Final Notice; Item will be returned to sender if not collected within 10 days` | Ostateczne wezwanie: odbierz w ciągu 10 dni, inaczej wróci do nadawcy. | „Do odbioru” |
| `Recipient not located at address provided. Item being returned to sender.` | Nie odnaleziono adresata pod wskazanym adresem; rozpoczęto zwrot. | „Zgłoszono problem” |
| `Item has been returned and is enroute to the Sender` | W drodze powrotnej do nadawcy. | „W transporcie” |

`Item re-routed due to processing error; Possible delay` oraz `Delivery may be delayed due to transportation delay` ostrzegają o opóźnieniu („Zgłoszono problem”), natomiast `Expected delivery date updated` aktualizuje przewidywaną datę bez ruchu paczki. Dla przesyłek **z zagranicy do Kanady** `International item mailed in originating country` ma etap „Przyjęta przez przewoźnika”, `International item has left originating country and is en route to Canada` – „W transporcie”, `Item has arrived in Canada and will be presented for review` – „W urzędzie celnym”, a `Item was released by Customs and is now with Canada Post for processing` wraca do „W transporcie”. W historii doręczenia wyświetlane są jako „Dostarczona”, a pozostałe wiersze zachowują angielski tekst Canada Post.

## Paczka z Kanady do Polski: kto doręcza i jakie opłaty celne obowiązują

**Kto doręcza.** Gdy międzynarodowy numer pocztowy kończy się na `CA` i ma poprawną cyfrę kontrolną, Peek odpytuje pod tym samym numerem pocztę kraju, z którego śledzisz przesyłkę – z Polski [Pocztę Polską](carrier:poczta-polska), z Niemiec [DHL](carrier:dhl), z USA [USPS](carrier:usps), a z Wielkiej Brytanii [Royal Mail](carrier:royal-mail) (lub Parcelforce dla kodów zaczynających się na `E`). Canada Post nie przekazuje kraju docelowego, więc decyduje region ustawiony w telefonie albo kraj dodania paczki w przeglądarce. Gdy Poczta Polska pokaże skan co najmniej tak świeży jak ostatni wpis z Kanady, Peek przełącza śledzenie na nią z zachowaniem historii Canada Post i jeszcze przez dobę odpytuje dodatkowo Canada Post najwyżej co 55 minut. Numery 16-cyfrowe pozostają wyłącznie w Canada Post.

**Cło i VAT w Polsce.** Kanada leży poza UE, więc przesyłki z towarami przechodzą odprawę celną. Nalicza się polski VAT od importu (chyba że pobrano go już przy zamówieniu w IOSS); od 1 lipca 2026 r. zamówienia internetowe spoza UE do 150 € objęte są zryczałtowanym cłem UE w wysokości 3 € za pozycję towarową, a od 1 listopada 2026 r. dochodzi unijna opłata manipulacyjna 2 € za pozycję towarową niezależnie od wartości zamówienia. Więcej w poradniku [paczka zatrzymana przez urząd celny](guide:customs).

## Nieudane doręczenie, skrytki i odprawa celna w Kanadzie

Dla paczek odbieranych **na terenie Kanady** obowiązują zasady Canada Post i kanadyjskiej służby granicznej (CBSA):

- **Próba pod drzwiami i awizo**: jeśli wymagany jest podpis lub paczka nie mieści się w skrzynce, listonosz puka do drzwi (w budynkach z domofonem zadzwonienie domofonem liczy się jako próba doręczenia). W razie braku odpowiedzi zostawia *Delivery Notice Card* (po `Delivery Attempt Made / Rescheduled for delivery next business day` ponawia próbę w następnym dniu roboczym). Po 5 dniach kalendarzowych przychodzi powtórne zawiadomienie, a po łącznie **15 dniach kalendarzowych** w placówce paczka wraca do nadawcy. Do odbioru potrzebny jest urzędowy dokument ze zdjęciem i adresem (upoważniona osoba okazuje własny dokument i podpisane przez Ciebie awizo). Ze skrytki paczkowej (*parcel locker*, klucz w skrzynce) przesyłkę trzeba wyjąć w ciągu **5 dni**, zanim trafi na pocztę. Zobacz [nieudane doręczenie](guide:missed-delivery) i [nieodebrana paczka](guide:uncollected-parcel).
- **Cło w Kanadzie (CBSA)**: przesyłki pocztowe o wartości do **20 CAN$** są zwolnione z cła i podatku (prezenty od rodziny lub znajomych do **60 CAN$** z GST). Powyżej naliczany jest 5 % GST (lub HST/PST zależnie od prowincji) oraz ewentualne cło na formularzu **E14**, powiększone o **9,95 CAN$** opłaty manipulacyjnej Canada Post: zapłacisz online przyciskiem *Pay* w Track lub aplikacji (kartą kredytową; w aplikacji także Apple Pay lub Google Pay) przed wydaniem do doręczenia, kartą u kuriera lub na poczcie; kwotę można zakwestionować, odmawiając odbioru do ponownej wyceny albo składając po zapłacie wniosek **B2G** z odwrotu formularza E14.

## Paczka Canada Post opóźniona, bez aktualizacji, zagubiona lub nieodebrana

**Cztery dni bez skanów.** Po czterech dniach bez nowego zdarzenia na etapie „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie” Peek wyświetla na liście dzień ostatniego skanu („Brak aktualizacji od …”), a na karcie przesyłki wyjaśnia: „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika Canada Post.” oraz dodaje link „Poproś przewoźnika Canada Post o pomoc”. Jak zaznacza CBSA, poczta w trakcie kontroli celnej w Kanadzie nie ma szczegółowego śledzenia; zobacz [dlaczego śledzenie się nie aktualizuje](guide:tracking-not-updating).

**Opóźniona lub zaginiona.** Według Canada Post większość opóźnionych paczek dociera w ciągu **5 dni roboczych** po przewidywanej dacie. Po tym czasie zgłoś poszukiwanie przez [stronę zagubionych przesyłek](https://www.canadapost-postescanada.ca/cpc/en/support/kb/claims/missing-packages.page) w ciągu **90 dni** od nadania (lub **6 miesięcy** przy wysyłce do USA bądź za granicę). Odszkodowanie przysługuje wyłącznie nadawcy, dlatego przy zakupach internetowych zgłoś sprawę także sprzedawcy; zobacz [zagubiona paczka](guide:lost-parcel).

**Status doręczono, ale paczki brak.** Zgodnie z ustawą *Canada Post Corporation Act* przesyłka pozostawiona w zwyczajowy sposób pod adresem, w skrzynce lub u osoby uprawnionej uznawana jest za doręczoną. Sprawdź w Track dokładne miejsce (`Delivered to mailroom`, portiernia lub *safe drop*) i podpis; jeśli w Polsce doręczała [Poczta Polska](carrier:poczta-polska), zweryfikuj jej śledzenie i przeczytaj [dostarczona, ale nieodebrana](guide:delivered-not-received). Po zwrocie do nadawcy Peek pokazuje „Zwrócona nadawcy” ([zwrot do nadawcy](guide:returned-to-sender)).

## Obsługa klienta i kontakt z Canada Post

- **Telefon**: `1-866-607-6301` w Kanadzie, `+1-416-979-3033` z zagranicy, TTY `1-800-267-2797`. Od poniedziałku do piątku 7:00–23:00 ET; w soboty i niedziele 9:00–21:00 ET.
- **Online**: czat oraz zgłoszenia serwisowe na [stronie wsparcia Canada Post](https://www.canadapost-postescanada.ca/cpc/en/support.page).

> SMS-y żądające dopłaty za wstrzymaną paczkę z linkiem do płatności to oszustwo. Prawdziwe wiadomości Canada Post przychodzą z numerów `272727` lub `55555` (po zapisaniu się) albo `613-734-1002` i `613-734-1004` i prowadzą do `canadapost-postescanada.ca`. W Kanadzie fałszywe SMS-y można przekazać pod `7726`.

## Śledzenie przesyłek Canada Post w Peek

[Peek](/) od razu rozpoznaje Canada Post po międzynarodowych numerach z końcówką `CA` i poprawną cyfrą kontrolną. Przy 16-cyfrowym numerze z prawidłową cyfrą kontrolną pokazuje „Sprawdzanie usług śledzenia…”, odpytuje jednocześnie Canada Post, DHL eCommerce, TNT, Correos Express oraz Evri i wybiera przewoźnika, który zna numer. Dla 15-cyfrowego numeru z awiza lub referencji kliknij „Zmień” i wskaż Canada Post na liście „Wszyscy przewoźnicy”. Działają też linki z `searchFor=` (linki z `#/search` działają tylko dla kodów `CA`). W drugą stronę, dla przesyłek pocztowych z Polski lub innego kraju do Kanady, Peek pyta również Canada Post, gdy poczta nadania wskaże Kanadę jako cel – z wyjątkiem kodów `L…CH` śledzonych w Swiss Post, które nigdy nie przechodzą do Canada Post.

Peek odpytuje bezpośrednio Canada Post i pokazuje status, historię z miejscowością (w Kanadzie miasto i prowincję), przewidywany dzień doręczenia, przedział godzinowy po wydaniu do doręczenia w Kanadzie (jeśli Canada Post go udostępnia), godzinę doręczenia oraz pełną nazwę nadawcy, pomijając opcje dostawy, adresy, podpisy i zdjęcia. Godziny widoczne są w Twojej strefie („Godziny w twojej strefie czasowej: …”).

Gdy masz otwartą kartę paczki lub w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza do 10 minut, a gdy Canada Post zgłosi wydanie paczki „W doręczeniu” tego dnia – nawet co 2 minuty od godz. 6:00 czasu lokalnego albo od 30 minut przed początkiem zapowiedzianego okna czasowego; w pozostałych porach oraz po 12 godzinach ciszy na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika” (48 godzinach na innych etapach) sprawdza co godzinę. Przez serwisy śledzenia w tym dniu statusu „W doręczeniu” na tych samych warunkach odpytuje do 15 minut, poza tym co godzinę albo co 3 godziny po dobie bez zmian. Po przejęciu paczki przez pocztę docelową stosuje rytm tego operatora. Powiadomienia możesz ustawić na „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”.

## Najczęstsze pytania o śledzenie Canada Post

### Dlaczego Peek pokazuje „Oczekiwanie na przewoźnika Canada Post”?

Canada Post zwraca identyczną odpowiedź dla numeru jeszcze nieskanowanego, błędnego i wygasłego (starszego niż rok). Jeśli po dwóch dniach żadne źródło nie odpowie, Peek wyświetla „Po dwóch dniach przewoźnik nadal nie zna tego numeru. Sprawdź go, wybierz innego przewoźnika lub usuń przesyłkę.” i przechodzi na sprawdzanie raz dziennie, a po tygodniu od dodania – raz w tygodniu. Sprawdź ostatnią cyfrę kontrolną (lub cyfrę przed `CA`).

### Kto w Polsce doręcza paczkę wysłaną przez Canada Post?

Jeśli numer kończy się na `CA`, doręcza ją [Poczta Polska](carrier:poczta-polska), a Peek automatycznie odpytuje ją tym samym numerem. Numer 16-cyfrowy pozostaje w śledzeniu Canada Post.

### Czy mogę śledzić przesyłkę po 15-cyfrowym numerze z awiza?

Tak – wklej 15 cyfr z karty awiza, kliknij „Zmień” i wybierz Canada Post na liście „Wszyscy przewoźnicy”.

:::sources
- [Canada Post: Track a package by tracking number](https://www.canadapost-postescanada.ca/track-reperage/en) – bezpłatna aplikacja, awizo, dokument do odbioru i przyczyny braku wyników
- [Canada Post: How to use Track](https://www.canadapost-postescanada.ca/cpc/en/support/kb/tracking/how-to-use-track.page) – numer dostępny tylko od nadawcy, brak wyszukiwania po nazwisku/adresie i karty 15-cyfrowe
- [Canada Post: Tracking statuses](https://www.canadapost-postescanada.ca/cpc/en/support/kb/tracking/tracking-statuses.page) – statusy awiza, odbiór przez pełnomocnika, ponowna próba i odprawa celna
- [Canada Post: Delivery options](https://www.canadapost-postescanada.ca/cpc/en/support/articles/parcel-services-shipping-in-canada/delivery-options.page) – domofon jako próba, bezpieczne miejsce, powtórne awizo po 5 dniach i 15 dni przechowywania
- [Canada Post: Parcel lockers FAQ](https://www.canadapost-postescanada.ca/cpc/en/support/kb/receiving/parcel-lockers/why-wasnt-my-parcel-delivered.page) – klucz w skrzynce i 5 dni na odbiór ze skrytki
- [Canada Post: How to pay duty and taxes](https://www.canadapost-postescanada.ca/cpc/en/support/kb/receiving/delivery-faq/how-to-pay-duty-and-taxes-online-canada-post.page) – opłata 9,95 CAN$, płatność online oraz Apple Pay i Google Pay w aplikacji
- [Canada Post: Missing packages](https://www.canadapost-postescanada.ca/cpc/en/support/kb/claims/missing-packages.page) – 5 dni roboczych oczekiwania oraz terminy 90 dni i 6 miesięcy
- [Canada Post: Support and contact](https://www.canadapost-postescanada.ca/cpc/en/support.page) – numery infolinii w Kanadzie i z zagranicy, godziny pracy oraz czat
- [Canada Post: Recognizing spam and fraudulent messages](https://www.canadapost-postescanada.ca/cpc/en/support/kb/company-policies/privacy-security/what-do-to-if-you-get-suspicious-email.page) – prawdziwe numery SMS i zgłaszanie pod 7726
- [Canada Post: Responsible business practices](https://www.canadapost-postescanada.ca/cpc/en/our-company/corporate-sustainability/responsible-business-practices.page) – federalna korporacja Korony
- [CBSA: Importing by mail](https://www.cbsa-asfc.gc.ca/import/personal-personnel/postal-postale-eng.html) – odprawa pocztowa i formularz E14
- [CBSA: Paying duty and taxes](https://www.cbsa-asfc.gc.ca/import/postal-postale/dtytx-drttx-eng.html) – zwolnienia 20 CAN$ i 60 CAN$, GST/HST/PST
- [CBSA: Disputing duty and taxes](https://www.cbsa-asfc.gc.ca/import/postal-postale/dispute-contestation-eng.html) – ponowna wycena i formularz B2G
- [Justice Laws Website: Canada Post Corporation Act](https://laws-lois.justice.gc.ca/eng/acts/C-10/page-1.html) – ustawowa definicja doręczenia
- [Your Europe: Wysyłka i dostawa](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_pl.htm) – cło 3 € za pozycję towarową do 150 €
- [Komisja Europejska: Customs Guidance on EUR 3 customs duty](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – obowiązywanie od 1 lipca 2026 r.
- [Komisja Europejska: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – unijna opłata manipulacyjna 2 € za pozycję od 1 listopada 2026 r.
- [Universal Parcel Scraper: Canada Post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/canada-post) – formaty numerów, mapowanie statusów i przekazywanie między pocztami
:::
