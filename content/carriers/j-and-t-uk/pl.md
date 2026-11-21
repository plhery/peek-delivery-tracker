---
title: Śledzenie J&T Express UK: numery JTUK i statusy
description: Śledź paczkę J&T Express UK: jak wygląda numer JTUK, co oznaczają kolejne statusy, cło w Wielkiej Brytanii i co zrobić w razie opóźnienia lub zaginięcia.
slug: sledzenie-jt-express-uk
published: 2026-11-21
updated: 2026-11-21
---

J&T Express UK doręcza przesyłki w Wielkiej Brytanii. Jeśli sklep wysłał zamówienie przez J&T, numer śledzenia zaczyna się od `JTUK`: wklej go w pole powyżej albo sprawdź w śledzeniu J&T na www.jtexpress.uk/tracking. Numer podaje sklep w e-mailu o wysyłce lub na stronie zamówienia. Ponieważ Peek nie mógł odczytać strony internetowej J&T Express UK, ten poradnik opiera się na tym, co kod Peek wie o śledzeniu J&T, oraz na oficjalnych źródłach brytyjskich.

## Numer śledzenia J&T Express UK: jak wygląda

List przewozowy J&T Express UK składa się z `JTUK` i 12 cyfr, łącznie 16 znaków. Peek nie zna innego kształtu dla tego przewoźnika. Przykłady ze zmyślonymi cyframi:

| Numer (zmyślony) | Co to jest |
| --- | --- |
| `JTUK482910573628` | `JTUK` i 12 cyfr: paczka J&T Express UK. Peek od razu rozpoznaje J&T Express UK. |
| `JTUK48291057362` | Brakuje jednej cyfry: nie pasuje do formatu J&T Express UK i Peek nie rozpoznaje przewoźnika. Sprawdź, czy nie brakuje cyfry. |
| `JTDE482910573628` | `JTDE` i 12 cyfr: [J&T Express Germany](carrier:j-and-t-de), a nie sieć brytyjska. |
| `JTAU482910573628` | `JTAU` i 12 cyfr: [J&T Express Australia](carrier:j-and-t-au). |
| `JTE482910573628` | `JTE` i 12 cyfr: [J&T Express Middle East](carrier:j-and-t-middle-east). |
| `JT4829105736284` | `JT` i 13 cyfr: sieci J&T w Azji Południowo-Wschodniej i Chinach ([J&T Express China](carrier:j-and-t-cn)). |

Na peektracker.com numer `JTUK` krótszy lub dłuższy o jeden znak otrzymuje komunikat taki jak: „Zaczyna się jak numer śledzenia przewoźnika J&T Express UK, ale brakuje jednego znaku: może jest w nim literówka. Porównaj go z wiadomością o wysyłce.” Spacje, myślniki i małe litery nie mają znaczenia w Peek: `jtuk 4829 1057 3628` działa jak pierwsza linia. Katalog Peek zna tego przewoźnika także jako J&T Express (UK), J&T Express UK Ltd i J&T UK.

**Gdzie go znaleźć.** W e-mailu o wysyłce ze sklepu, na stronie zamówienia w sklepie lub na platformie sprzedażowej albo w wiadomości o doręczeniu. Jeśli go nie widzisz, zobacz [jak znaleźć numer przesyłki](guide:find-tracking-number) lub zapytaj sklep.

## Statusy śledzenia J&T Express UK wyjaśnione

Śledzenie J&T pokazuje przy każdym skanie godzinę i jedno zdanie, bez miejscowości. Oto zdania zarejestrowane w kodzie Peek na prawdziwych przesyłkach J&T Express UK, w typowej kolejności:

| Co podaje J&T | Co to oznacza dla ciebie | Peek pokazuje |
| --- | --- | --- |
| `The parcel has been successfully picked up and is being prepared for transportation` | J&T odebrał paczkę od nadawcy i przygotowuje ją do transportu. | „Przyjęta przez przewoźnika” |
| `The parcel arrives at the distribution center and is prepared for transport to the next destination` | Paczka dotarła do centrum dystrybucyjnego J&T. | „W transporcie” |
| `The parcel is dispatched from the transit center to the next destination` | Opuściła centrum w drodze do kolejnego etapu. | „W transporcie” |
| `The parcel has been collected and is on its way` | Kurier zabrał paczkę na trasę doręczeń. Mimo brzmienia nie jest to pierwszy odbiór od nadawcy: J&T klasyfikuje ten wpis jako skan doręczenia. | „W doręczeniu” |
| `The parcel has been successfully delivered to the recipient` | Doręczona. | „Dostarczona” |
| `The delivery address is incomplete or incorrect.` | Próba doręczenia nie powiodła się z powodu niepełnego lub błędnego adresu. W historii w kodzie Peek wpis ten pojawia się kilka godzin po wyjeździe do doręczenia, tego samego dnia. | „Próba doręczenia” |
| `Delivery was attempted, but no one was available to receive the package at the address; the package was taken to a pickup location for the customer to collect.` | Nikogo nie było pod adresem, więc paczkę przewieziono do punktu odbioru do odebrania przez odbiorcę. | „Próba doręczenia” |

Zdanie spoza tej tabeli podlega ogólnym regułom Peek: nierozpoznane wyświetla się w brzmieniu J&T i liczy jako „W transporcie”, chyba że paczka jest już w doręczeniu lub dalej – wtedy status nie ulega zmianie.

Jeśli zdanie kończy się numerem obsługi klienta lub zachętą do telefonu, Peek tę część pomija. Usuwa też tekst w nawiasach, numery telefonów oraz nazwiska osób podpisujących, odbierających, kurierów, kierowców, odbiorców i nadawców, zostawiając resztę.

## Doręczenie J&T Express UK: co mówi śledzenie

W historiach w kodzie Peek kurier J&T wyjeżdża z paczką i ją doręcza, zgłasza problem z adresem albo odwozi ją do punktu odbioru po nieudanej próbie. Kod Peek nie pokazuje przekazania innemu przewoźnikowi: śledzenie J&T prowadzi paczkę do drzwi.

**W doręczeniu.** `The parcel has been collected and is on its way` oznacza, że przesyłka jest na dzisiejszej trasie. Śledzenie J&T nie podaje przedziału godzinowego ani przewidywanego dnia, więc Peek również ich nie wyświetla.

**Problem z adresem.** Po wpisie `The delivery address is incomplete or incorrect.` sprawdź adres w zamówieniu. Jeśli czegoś brakuje (np. numeru mieszkania lub kodu pocztowego), natychmiast napisz do sklepu, który wykupił dostawę. Zobacz [jak zmienić adres dostawy](guide:change-delivery-address) i [nieudane doręczenie](guide:missed-delivery).

**Próby, bezpieczne miejsca i punkty odbioru.** Ponieważ Peek nie mógł odczytać strony J&T Express UK, nie podaje tutaj liczby prób doręczenia, zasad zostawiania paczek u sąsiadów ani czasu przechowywania nieodebranej przesyłki. Zapytaj sklep albo sprawdź otrzymane wiadomości o dostawie.

**Zamówienia spoza Wielkiej Brytanii.** Według GOV.UK przy przesyłkach z zagranicy firma doręczająca kontaktuje się z odbiorcą, jeśli należny jest podatek VAT, cło lub opłata za doręczenie, wysyłając rachunek z dokładną kwotą. Zwykle przechowuje paczkę przez około 3 tygodnie, a potem odsyła ją nadawcy, jeśli rachunek nie zostanie opłacony. Przy samodzielnych zakupach o wartości do 135 £ (z wyjątkiem towarów akcyzowych, takich jak alkohol, tytoń czy e-papierosy) sprzedawca wliczył już VAT w cenę. Powyżej 135 £ płacisz VAT przewoźnikowi przed doręczeniem lub przy odbiorze oraz ewentualne cło zależne od towaru i kraju pochodzenia. Irlandia Północna ma odrębne zasady. Peek nie mógł sprawdzić, jak J&T nalicza opłaty importowe. Więcej w [przesyłki zatrzymane na cle](guide:customs).

## Paczka J&T Express UK opóźniona, zaginiona lub zwrócona

To sklep wybrał J&T, więc w zakupach online sklep jest pierwszym kontaktem. Zgodnie z sekcją 29 brytyjskiej ustawy Consumer Rights Act 2015 towary kupione od przedsiębiorcy pozostają na ryzyku sprzedawcy, dopóki nie trafią fizycznie w ręce konsumenta lub wskazanej przez niego osoby, chyba że sam konsument zlecił przewóz firmie nieoferowanej przez sprzedawcę.

**Brak ruchu.** Przesyłka ze statusem „Przyjęta przez przewoźnika” lub „W transporcie” bez nowego wpisu przez cztery dni dostaje w Peek oznaczenie („Brak aktualizacji od czterech dni”) z linkiem do strony kontaktowej J&T Express UK („Poproś przewoźnika J&T Express UK o pomoc”). Jeśli nic się nie zmienia, zapytaj sklep. Zobacz [dlaczego śledzenie nie aktualizuje się](guide:tracking-not-updating).

**Brak śledzenia.** Numer nieznany jeszcze systemowi J&T wyświetla się jako „Oczekiwanie na przewoźnika J&T Express UK”. Sklepy często generują numer przed przekazaniem paczki. Jeśli dwa dni później J&T wciąż go nie zna, a żadne inne źródło nie odpowiedziało, Peek pokazuje „Po dwóch dniach przewoźnik nadal nie zna tego numeru. Sprawdź go, wybierz innego przewoźnika lub usuń przesyłkę.” i sprawdza potem raz dziennie, a po tygodniu od dodania raz w tygodniu. Sprawdź numer w sklepie i zapytaj, czy paczka naprawdę została nadana.

**Dostarczona, ale nic nie przyszło.** Sprawdź okolice drzwi, zapytaj domowników i sąsiadów, a potem zgłoś sprawę sklepowi: zgodnie z tą ustawą ryzyko spoczywa na nim do faktycznego odbioru, więc poproś o zwrot pieniędzy lub ponowną wysyłkę. Zobacz [dostarczona, ale nieotrzymana](guide:delivered-not-received).

**Zaginiona lub uszkodzona.** Poproś sklep o zwrot pieniędzy lub wymianę, podając numer śledzenia. Peek nie mógł odczytać zasad reklamacji J&T; zobacz [zaginiona przesyłka](guide:lost-parcel) i [uszkodzona przesyłka](guide:damaged-parcel).

**Zwrócona.** Jeśli paczka wraca do sklepu po nieudanym doręczeniu, sklep decyduje o zwrocie pieniędzy lub ponownej wysyłce. Zobacz [zwrot do nadawcy](guide:returned-to-sender).

## Obsługa klienta i kontakt z J&T Express UK

Ponieważ Peek nie mógł odczytać strony internetowej J&T Express UK, nie podaje tutaj numeru telefonu, e-maila ani czatu: Peek zamieszcza wyłącznie kontakty zweryfikowane na stronach samego przewoźnika. Rejestr Companies House podaje adres siedziby `C/O Tmf Group 13th Floor, One Angel Court, London, United Kingdom, EC2R 7HJ`: to adres rejestrowy u zewnętrznego podmiotu, a nie punkt obsługi przesyłek.

Zacznij od sklepu, który zamówił dostawę, albo skorzystaj z pomocy na stronie zamówienia na platformie sprzedażowej. Własne śledzenie J&T działa na www.jtexpress.uk/tracking.

## Śledzenie przesyłki J&T Express UK w Peek

[Peek](/) od razu rozpoznaje J&T Express UK po numerze `JTUK` („Wykryty przewoźnik”) i pyta śledzenie J&T samym numerem, bez kodu pocztowego ani konta. Działa też link www.jtexpress.uk/tracking z parametrem `?q=` i numerem; jeśli zawiera inny typ numeru, Peek odczytuje go jak wklejony osobno. Jeśli masz inny numer niż `JTUK`, wklej go mimo to: Peek śledzi ponad 4500 przewoźników. Dla 13-cyfrowego numeru `JT` wyświetla „Sprawdzanie usług śledzenia…” podczas sprawdzania. Ręczny wybór J&T Express UK przez „Zmień” dla innego kształtu nie pomoże: Peek poinformuje, że „nie wydaje takich numerów”.

Peek pokazuje status, każdy skan z dokładną godziną i godzinę doręczenia. J&T zwraca każdy skan jako dokładny moment, więc Peek wyświetla godziny w strefie czasowej twojego urządzenia latem i zimą; na peektracker.com oraz w aplikacjach na iPhone’a i Androida historia kończy się wierszem „Godziny w twojej strefie czasowej:” z jej nazwą. Skany pozostają po angielsku, w brzmieniu J&T. Ponieważ J&T nie podaje miejsca, przewidywanej daty ani wagi, Peek nie wyświetla mapy. Strona J&T może zawierać potwierdzenie doręczenia, które Peek pomija; w bardzo długich historiach zachowuje 100 najnowszych skanów. „Otwórz stronę przewoźnika J&T Express UK” na stronie paczki otwiera twój numer w śledzeniu J&T.

Dopóki J&T nie zeskanuje przesyłki, Peek dalej pyta z opisem „Oczekiwanie na przewoźnika J&T Express UK” (lub po dwóch dniach prosi o sprawdzenie numeru). Gdy śledzenie J&T jest niedostępne lub nie zna numeru, Peek pyta usługi śledzenia i pokazuje ich historię w ich brzmieniu.

Dopóki masz przesyłkę otwartą na ekranie albo w dniu doręczenia przy włączonych powiadomieniach, a śledzenie J&T odpowiada, Peek sprawdza ją nawet co 10 minut, a od godz. 6:00 czasu lokalnego w dniu skanu w doręczeniu nawet co 2 minuty; w pozostałym czasie co godzinę. Przez usługi śledzenia sprawdza co godzinę, a w dniu doręczenia nawet co 15 minut, dopóki masz ją otwartą lub włączone powiadomienia. Po dwóch dniach bez nowego skanu (po 12 godzinach na etapie „Przyjęta przez przewoźnika”) sprawdza co godzinę. Możesz wybrać powiadomienia „Każdy skan”, „Ważne etapy” albo „Tylko doręczenie”. Peek nie skontaktuje się za ciebie z J&T ani ze sklepem, nie zmieni adresu ani nie umówi ponownego doręczenia.

## Pytania o śledzenie J&T Express UK

### Czy J&T Express UK to legalna firma?

Tak. Rejestr Companies House wymienia `J&T EXPRESS UK LTD` jako aktywną spółkę private limited company, zarejestrowaną 18 grudnia 2025 r., z przedmiotem działalności obejmującym transport drogowy towarów, magazynowanie i działalność pocztową. Jej numery zaczynają się od `JTUK`, a Peek odczytuje bezpośrednio jej śledzenie.

### Co oznacza „The parcel has been collected and is on its way” w J&T?

Twoja paczka jest w doręczeniu. Kurier odebrał ją na trasę doręczeń; J&T zapisuje ten wpis jako skan doręczenia, a Peek pokazuje „W doręczeniu”. Nie oznacza to, że paczka dopiero wyjechała od nadawcy.

### Dlaczego J&T podaje, że mój adres dostawy jest niepełny lub nieprawidłowy?

Kurier nie mógł doręczyć przesyłki, bo brakowało danych albo nie pasowały. Sprawdź adres w zamówieniu i natychmiast napisz do sklepu, podając pełny adres i numer śledzenia.

### Dlaczego mój numer śledzenia J&T nie został znaleziony?

Sprawdź, czy zaczyna się od `JTUK` i ma potem 12 cyfr: numery innych sieci J&T, takich jak `JTDE` w Niemczech, nie należą do sieci brytyjskiej. Jeśli numer jest poprawny, J&T może jeszcze nie mieć paczki i Peek pokazuje „Oczekiwanie na przewoźnika J&T Express UK”. Jeśli po dwóch dniach nadal go nie zna, a żadne inne źródło nie odpowiedziało, Peek poprosi o sprawdzenie numeru, wybór innego przewoźnika lub usunięcie paczki. Gdy po kilku dniach nic się nie pojawi, zapytaj sklep.

### Jak skontaktować się z J&T Express UK?

Zapytaj najpierw sklep, który zlecił dostawę. Ponieważ Peek nie mógł odczytać strony internetowej J&T Express UK, na tej stronie nie ma numeru telefonu ani adresu e-mail.

:::sources
- [Universal Parcel Scraper: J&T Express UK](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/j-and-t-uk) – nazwy, format `JTUK`, linki śledzenia, strona pomocy `jtexpress.uk/contact`, zdania statusów i etapy (w tym odwiezienie do punktu odbioru po nieobecności), dokładne godziny, brak miejsca/daty/wagi, pominięte potwierdzenie doręczenia, 100 najnowszych skanów
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formaty innych sieci J&T, odfiltrowywanie linii obsługi klienta, nawiasów, nazwisk i telefonów, brak przekazania innemu przewoźnikowi, zapasowe usługi śledzenia
- [Companies House: J&T EXPRESS UK LTD](https://find-and-update.company-information.service.gov.uk/company/16920174) – aktywna spółka private limited company, zarejestrowana 18 grudnia 2025 r., przedmiot działalności, adres siedziby
- [GOV.UK: Tax and customs for goods sent from abroad](https://www.gov.uk/goods-sent-from-abroad/tax-and-duty) – rachunek od przewoźnika, przechowywanie przez ok. 3 tygodnie, VAT do 135 £ i powyżej, cło, Irlandia Północna
- [UK legislation: Consumer Rights Act 2015, section 29](https://www.legislation.gov.uk/ukpga/2015/15/section/29) – ryzyko po stronie sprzedawcy do chwili fizycznego objęcia towaru w posiadanie przez konsumenta
:::
