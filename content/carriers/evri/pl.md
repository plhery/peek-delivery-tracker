---
title: Śledzenie przesyłek Evri: numer, statusy, cło i doręczenie
description: Śledź paczkę Evri w Polsce: 16-znakowy kod, statusy GlobalEco i w Wielkiej Brytanii, odprawa celna, punkty odbioru oraz oficjalny kontakt.
slug: sledzenie-evri
published: 2026-11-17
updated: 2026-11-20
---

Evri to brytyjska firma kurierska działająca do marca 2022 roku pod nazwą Hermes UK (odrębna od niemieckiego Hermesa). Gdy zamawiasz w brytyjskich sklepach internetowych — takich jak ASOS, Boohoo, Gymshark, Lookfantastic, eBay UK czy Vinted — Evri często odbiera przesyłkę w Wielkiej Brytanii i przekazuje ją partnerowi doręczającemu w Polsce. Przesyłki międzynarodowe sprawdzisz w portalu [GlobalEco dla Evri International](https://globaleco.app/track/), natomiast paczki krajowe na terenie Wielkiej Brytanii na stronie [evri.com](https://www.evri.com/track-a-parcel) po wpisaniu 16-znakowego numeru lub 8 cyfr z awiza (*calling card*). Możesz też wkleić 16-znakowy kod kreskowy albo link śledzenia z `evri.com` bezpośrednio w polu powyżej.

## Numer śledzenia Evri: formaty i gdzie go znaleźć

Evri zamiennie nazywa go 16-znakowym kodem (*16-character code*) lub 16-cyfrowym numerem (*16-digit number*), mimo że w jego własnych przykładach występują litery. Znajdziesz go w wiadomości ze sklepu potwierdzającej wysyłkę albo w SMS-ach (nadawca „Evri”) i e-mailach od Evri.

| Format (wymyślony przykład) | Co oznacza |
| --- | --- |
| `H00AAC0000000008` | 16-znakowy kod kreskowy Evri (`H` lub rzadziej `T`, cztery litery lub cyfry, `C` na 6. pozycji oraz 10 cyfr z cyfrą kontrolną), typowy dla Evri International (np. `H04G0C…` przy nadaniu w Post Office lub `H0595C…` przy zamówieniu na evri.com). |
| `H00AAA0000000006` | 16-znakowy kod kreskowy Evri z literą `A`, `D` lub innym znakiem na 6. pozycji (np. `H00AAB0000000007`), stosowany w Evri UK i współdzielony przez obie usługi. |
| `C00HHA0000000000` | `C00HHA` oraz 10 cyfr: krajowy numer Evri UK widoczny m.in. w części linków śledzenia na `evri.com`. |
| `1000000000000007` | 16 cyfr z cyfrą kontrolną GS1: używany przez Evri UK, ale też przez DHL eCommerce, Correos Express, Canada Post i TNT. |
| `12340000` | 8-cyfrowy numer brytyjskiego awiza (*calling card*); działa wyłącznie na stronie evri.com. |

Litera `H` i 15 samych cyfr (`H000000000000008`) pasuje jednocześnie do numeracji Hermesa w Niemczech — kieruj się przewoźnikiem wskazanym przez sprzedawcę. Na stronie [globaleco.app/track/](https://globaleco.app/track/), po przekazaniu paczki lokalnemu przewoźnikowi w kraju docelowym, pole `Carrier` podaje jego nazwę, a `Carrier Tracking` — krajowy numer przesyłki (na przykład przy doręczeniach realizowanych przez Paack).

**Zakup na eBay UK?** Jeśli sprzedawca korzysta z programu *Global Shipping Programme* (GSP) serwisu eBay, Evri dowozi paczkę wyłącznie na terenie Wielkiej Brytanii do brytyjskiego centrum wysyłkowego eBay pod krajowym numerem Evri UK, a dalszy transport za granicę obsługuje program eBay. Brakuje numeru? Zajrzyj do poradnika [jak znaleźć numer przesyłki](guide:find-tracking-number).

## Co oznaczają statusy śledzenia Evri

W przypadku paczek wysyłanych z Wielkiej Brytanii do Polski portal GlobalEco usługi Evri International wyświetla komunikaty po angielsku:

| GlobalEco wyświetla | Co to oznacza dla Twojej paczki |
| --- | --- |
| `Created` | Zgłoszono przesyłkę; Evri fizycznie jeszcze jej nie odebrało. |
| `Received at Evri`, `Accepted` | Evri przyjęło paczkę do swojej sieci w Wielkiej Brytanii. |
| `Consolidated`, `Cross Docked`, `Processed at Export Gateway` | Sortowanie i odprawa wywozowa w brytyjskim centrum eksportowym Evri. |
| `Departed Origin Country`, `In transit` | Przesyłka opuściła Wielką Brytanię lub jest w drodze do kraju docelowego. |
| `Arrived In Country` | Dotarła do kraju przeznaczenia. |
| `In customs`, `Customs cleared` | Trwa kontrola celna, a następnie paczka została zwolniona z odprawy (zobacz [paczka zatrzymana przez urząd celny](guide:customs)). |
| `Handed to Partner Carrier`, `Received by carrier`, `At the local depot` | Przekazana lokalnemu przewoźnikowi partnerskiemu i posortowana w jego oddziale; sprawdź pola `Carrier` i `Carrier Tracking` w GlobalEco, aby śledzić ostatni odcinek. |
| `Scheduled For Delivery` | Pojawia się przy przesyłkach doręczanych przez Paack po dotarciu do kraju, gdy zmieniono dzień lub przedział doręczenia, a paczka czeka w magazynie. |
| `Out for delivery` | Wydana dziś kurierowi do doręczenia. |
| `Delivery Attempted` | Podjęto próbę doręczenia, ale nie udało się przekazać paczki. |
| `Delivery awaiting collection` | Czeka na odbiór w punkcie odbioru lub placówce pocztowej. |
| `Delivered` | Doręczona lub odebrana. |
| `Delayed`, `Delivery Exception`, `Carrier label error` | Opóźnienie, przeszkoda w doręczeniu albo błąd etykiety — sprawdź lokalne śledzenie partnera lub skontaktuj się ze sklepem. |

Dla przesyłek krajowych w Wielkiej Brytanii sprawdzanych na `evri.com` przewoźnik stosuje własne opisy:

| Evri wyświetla (Wielka Brytania) | Co to oznacza |
| --- | --- |
| `We're expecting it` | Nadawca nie przekazał jeszcze paczki firmie Evri. |
| `on its way` (dostawa do ParcelShopu) | Paczka jedzie przez brytyjskie sortownie Evri do wybranego punktu ParcelShop; dostaniesz SMS lub e-mail, gdy będzie gotowa do odbioru. |
| `Evri gateway` | Przesyłka z zagranicy po wjeździe do Wielkiej Brytanii, zatrzymana w punkcie Evri na czas brytyjskiej odprawy celnej przed transportem do hubu. |
| `We're sorry we haven't been able to deliver today. We'll update your tracking when it's on the way on the next working day` | Opóźniona trasa bez próby doręczenia tego dnia; paczka wyjedzie ponownie w następnym dniu roboczym. |
| `On its way back to the retailer` | Ostatni wpis przy zwrocie: Evri przekazało paczkę z powrotem sprzedawcy i nie wysyła już kolejnych powiadomień. |

Jeśli śledzenie na `evri.com` prosi o kontakt z Evri, przewoźnik potrzebuje dodatkowych wskazówek do doręczenia — uruchom czat na `evri.com`.

## Dostawa do Polski, odprawa celna z Wielkiej Brytanii i odbiór w UK

**Czas dostawy do Polski.** Choć ogólne międzynarodowe FAQ Evri podaje 3–7 dni roboczych na świecie, na podstronie poświęconej wysyłkom z Wielkiej Brytanii do Polski Evri wskazuje średnio 8–11 dni (do których mogą dojść dni ustawowo wolne od pracy lub odprawa celna). Zgodnie z regulaminem Evri w usługach międzynarodowych wykonywana jest co najmniej jedna próba doręczenia; dalszy tryb — ponowny podjazd albo awizo do punktu odbioru — zależy od lokalnego przewoźnika partnerskiego w Polsce.

**Cło i podatek VAT przy zakupach z Wielkiej Brytanii.** Ponieważ Wielka Brytania pozostaje poza unią celną UE, każda przesyłka towarowa przechodzi odprawę celną:

- Na podstronie Evri dla Polski widnieje ostrzeżenie, że w międzynarodowej usłudze standardowej odbiorca może zostać poproszony o opłacenie podatków i należności celnych jeszcze przed wyjazdem paczki z Wielkiej Brytanii albo po jej dotarciu do kraju przeznaczenia; jeśli należności są wymagalne, Evri lub właściwe organy wysyłają odbiorcy link do płatności przed wydaniem przesyłki.
- Ponadto od 1 lipca 2026 r. w UE zniesiono zwolnienie z cła dla przesyłek o wartości do 150 €: stosuje się do nich (także do pocztowych, jak podaje Poczta Polska) cło w wysokości 3 € za każdy rodzaj produktu, niezależnie od procedury VAT (z IOSS lub bez), a od 1 listopada 2026 r. dochodzi unijna opłata manipulacyjna w wysokości 2 € za każdy rodzaj produktu, niezależnie od wartości. Evri liczy obie opłaty według grup celnych (towary o tych samych pierwszych 6 cyfrach kodu HS tworzą jedną grupę — np. 2 koszulki = 1 grupa, natomiast 1 książka + 1 koszulka = 2 grupy, a nie opłata za każdą pojedynczą sztukę). Szczegóły znajdziesz w poradniku o [opłatach celnych i zatrzymanych paczkach](guide:customs).

**Doręczenia na terenie Wielkiej Brytanii (Calling Cards, ParcelShops i Lockers).** Pod brytyjskimi adresami kurier ustala przedział godzinowy rano (1 godzina według strony głównej Evri, 2 godziny według centrum pomocy). Gdy nikogo nie zastanie, zostawia paczkę w bezpiecznym miejscu (*safe place*) lub wrzuca ją przez wrzut na listy, a jeśli to niemożliwe — zostawia 8-cyfrową kartę *calling card* i ponawia próbę maksymalnie do trzech razy łącznie (po trzeciej próbie poradnik *safe place* mówi o powrocie do oddziału, regulamin o zwrocie do nadawcy, a strona `evri.com/send` o przekazaniu do najbliższego ParcelShopu). Po podaniu brytyjskiego kodu pocztowego na `evri.com` można przekierować paczkę do sąsiada, bezpiecznego miejsca, ParcelShopu lub automatu Locker, o ile nadawca na to pozwala i przesyłka nie wymaga podpisu; nie da się zmienić dnia doręczenia, adres może skorygować wyłącznie nadawca, a paczki będącej już u kuriera nie można odebrać osobiście w oddziale. Brytyjskie punkty ParcelShop (sklepy lokalne i placówki Post Office) przechowują przesyłkę przez 10 dni za okazaniem dokumentu tożsamości lub kodu PIN od nadawcy, a całodobowe automaty Locker — przez 3 dni na podstawie kodu lub kodu kreskowego z e-maila (przycisk „help” na ekranie pomaga w razie usterki).

## Paczka Evri utknęła, spóźnia się, zaginęła lub dotarła uszkodzona

- **Brak ruchu w śledzeniu:** jeśli status nie wychodzi poza `We're expecting it` albo `Created`, nadawca nie przekazał jeszcze paczki do Evri — napisz do sklepu. Gdy inny brytyjski status nie zmienia się przez 24 godziny (lub przez 5 dni w usłudze Standard po stronie nadawcy), Evri prosi o rozpoczęcie czatu. Zobacz [dlaczego status przesyłki się nie zmienia](guide:tracking-not-updating).
- **Ponad 7 dni oczekiwania:** zgłoś sprawę sprzedawcy, który ma umowę z Evri. Przy zakupach w UE przedsiębiorca powinien dostarczyć towar w ciągu 30 dni (chyba że uzgodniliście inny termin) i odpowiada za wszelkie uszkodzenia towaru od chwili wysyłki aż do odebrania go przez Ciebie.
- **Status „doręczono”, ale paczki nie ma:** zapytaj domowników i bezpośrednich sąsiadów. W Wielkiej Brytanii czat uruchamia wyjaśnienie w lokalnym oddziale; w Polsce sprawdź również numer partnera krajowego i powiadom sklep. Więcej w poradniku [przesyłka oznaczona jako doręczona, ale nieodebrana](guide:delivered-not-received).
- **Zaginiona lub uszkodzona:** Evri nie wypłaca zwrotu bezpośrednio odbiorcy; [reklamację może złożyć wyłącznie nadawca](guide:damaged-parcel) — Evri zaleca zrobienie tego w ciągu 28 dni od zamówienia dla usług krajowych w UK oraz 45 dni dla usług międzynarodowych.

## Kontakt z Evri i jak rozpoznać fałszywe SMS-y

Evri udostępnia wyłącznie brytyjskie kanały obsługi po angielsku (sprawdzone 17 listopada 2026 r.), bez polskiej infolinii:

- **Czat 24/7** na stronie [evri.com/contact-us](https://www.evri.com/contact-us) przez wirtualnego asystenta Ezra, z odpowiedzią w ciągu maksymalnie 24 godzin. Przygotuj numer śledzenia, potwierdzenie nadania z ParcelShopu (`P` i 7 cyfr) lub 8 cyfr z *calling card*. W przypadku przesyłek międzynarodowych do krajów i terytoriów bez kodów pocztowych Evri zaleca wpisać `0`, gdy Ezra poprosi o kod pocztowy.
- **Telefon w Wielkiej Brytanii:** `0330 808 5456`, automat głosowy 24/7 oraz konsultanci od poniedziałku do piątku w godz. 8:00–16:00 i w soboty w godz. 8:00–14:00 (czasu brytyjskiego). Na klawiaturze telefonu trzeba wybrać ostatnie 8 cyfr numeru śledzenia, 7 cyfr po literze `P` z paragonu ParcelShop albo 8 cyfr z *calling card*. W Wielkiej Brytanii numery `03` kosztują tyle co zwykłe połączenia stacjonarne `01`/`02`; z Polski obowiązuje stawka Twojego operatora za połączenie międzynarodowe do Wielkiej Brytanii.
- **Tell us straight:** jeśli dział obsługi klienta próbował już pomóc, ale problem pozostał nierozwiązany, przycisk „Email us” na stronie kontaktowej otwiera wiadomość na adres `noreply.priority@evri.com` do zespołu wyższego szczebla (potwierdzenie wpływu w ciągu godziny w brytyjskich godzinach biurowych i codzienna informacja w trakcie wyjaśniania sprawy).

> Prawdziwe e-maile od Evri przychodzą zazwyczaj z domen `@evri.com`, `@hermes-europe.co.uk` lub `@myhermes.co.uk`, a pełna lista obejmuje także `@custhelp.com`, `@globalco.uk`, `@globaleco.app` oraz ich subdomeny (np. `@international.evri.com`). Brytyjskie SMS-y mają zawsze nadawcę „Evri” (nigdy zwykły numer komórkowy), nigdy nie żądają dopłaty za zmianę terminu doręczenia ani instalacji aplikacji i prowadzą wyłącznie do `evri.com` lub `evri.link` (pod które oszuści mimo wszystko potrafią się podszyć). W razie wątpliwości nie odpowiadaj i sprawdź numer samodzielnie w GlobalEco lub na `evri.com`; w Wielkiej Brytanii podejrzane SMS-y zgłasza się bezpłatnie pod numer `7726`. Więcej w poradniku o [fałszywych SMS-ach o paczce](guide:delivery-scam-texts).

## Śledzenie przesyłki Evri w aplikacji Peek

[Peek](/) odpytuje jednocześnie **Evri International** oraz **Evri UK** dla każdego wpisanego 16-znakowego kodu zaczynającego się od `H` lub `T`, w którym zgadza się cyfra kontrolna: ustawia wyżej Evri International, gdy 6. znakiem jest `C`, a Evri UK, gdy stoi tam `A` lub `D`, ale nie wybiera żadnego z góry, dopóki jeden z serwisów nie potwierdzi paczki (jeśli znają ją oba, Peek wybiera Evri International przy `C`, a w pozostałych przypadkach Evri UK). Przy `H` i 15 cyfrach ze zgodną cyfrą kontrolną Evri sprawdza również Hermesa w Niemczech; gdy cyfra Evri się nie zgadza, pasuje wyłącznie Hermes w Niemczech. Jeśli żaden serwis Evri jeszcze nie rozpoznaje kodu, stuknij „Zmień” i wybierz **Evri International** albo **Evri UK** na liście „Pasują do numeru” lub wklej link `evri.com/track/parcel/…/details`, który Peek od razu przypisuje do Evri UK. Przy 16 cyfrach Peek odpytuje Evri UK obok innych przewoźników tylko wtedy, gdy zgadza się cyfra kontrolna GS1, a w przeciwnym razie wyłącznie pozostałych; kod `C00HHA` z 10 cyframi trafia prosto do Evri UK. Kod pocztowy nie jest wymagany, natomiast Peek nie obsługuje 8-cyfrowych numerów *calling card*.

W **Evri International** Peek pokazuje historię z GlobalEco (większość komunikatów pozostaje po angielsku, a ogólne etykiety takie jak „W transporcie”, „W doręczeniu” czy „Dostarczona” wyświetlają się po polsku), miejsca skanów, wagę paczki oraz — gdy w komentarzu widnieje `Estimated delivery date is: …` — przewidywaną datę doręczenia aż do wydania lub zwrotu przesyłki; nazwa nadawcy i miejsce docelowe są pomijane. Ponieważ GlobalEco nie podaje strefy czasowej przy znacznikach czasu, Peek zachowuje te lokalne godziny bez przeliczania.

W **Evri UK** widzisz aktualny status i historię. Evri UK nie udostępnia lokalizacji skanów, dlatego mapa pozostaje pusta. Każde doręczenie kurierskie i każdy odbiór z ParcelShopu lub automatu Locker wyświetla się jako „Dostarczona” bez wskazania miejsca pozostawienia; paczka czekająca w ParcelShopie lub Lockerze ma status „Do odbioru”, a pozostałe zachowują angielskie zdanie Evri. Imię i nazwisko, adres, zdjęcie z doręczenia oraz kody odbioru są odrzucane. Na peektracker.com i w aplikacjach na iPhone'a i Androida historia ze strefą czasową kończy się dopiskiem „Godziny w Twojej strefie czasowej: …”, a powiadomienia ustawisz jako „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”.

## Najczęstsze pytania o śledzenie Evri

### Czy Evri i Hermes to ta sama firma?

W Wielkiej Brytanii tak: Evri to dawny Hermes UK po zmianie nazwy w marcu 2022 roku. Z kolei Hermes w Niemczech jest osobnym przewoźnikiem; jeśli Twój numer zaczyna się od `H` i 15 cyfr, sprawdź w wiadomości ze sklepu, który z nich realizuje wysyłkę.

### Czy Evri należy do DHL albo Royal Mail?

Do żadnego z nich. W ramach transakcji zatwierdzonej przez brytyjski urząd ochrony konkurencji we wrześniu 2025 r. i sfinalizowanej 1 października 2025 r. z utworzeniem Evri Group, firma Evri (należąca wówczas do funduszy zarządzanych przez Apollo) przejęła spółkę DHL eCommerce UK od DHL Group, a DHL Group objęło mniejszościowy udział w Evri. W 2026 r. sieć DHL eCommerce UK przyjęła nazwę **Evri Premium – a network of DHL**, pozostając osobną siecią dla większych i cenniejszych przesyłek; zwykłych paczek [DHL](carrier:dhl) nie sprawdzisz w standardowej wyszukiwarce Evri.

### Ile idzie paczka Evri z Wielkiej Brytanii do Polski?

Podczas gdy ogólne międzynarodowe FAQ Evri podaje 3–7 dni roboczych na świecie, na podstronie Evri dla wysyłek z Wielkiej Brytanii do Polski widnieje średni czas 8–11 dni, do którego mogą dojść dni świąteczne lub odprawa celna. Na terenie samej Wielkiej Brytanii centrum pomocy Evri podaje 1–2 dni dla usługi *Next Day* i 3–5 dni dla *Standard* (inne strony Evri wymieniają 1–3 dni).

### Czy wiadomość z adresu noreply.priority@evri.com jest prawdziwa?

Evri podaje ten adres jako skrzynkę do kontaktu: na stronie kontaktowej przycisk „Email us” w sekcji *Tell us straight* otwiera wiadomość na `noreply.priority@evri.com`, pozwalającą dotrzeć do zespołu wyższego szczebla po wcześniejszym kontakcie z obsługą klienta (z potwierdzeniem w ciągu godziny w brytyjskich godzinach biurowych). Ponieważ poradniki bezpieczeństwa Evri wymieniają domeny takie jak `@evri.com` bez wskazywania tego konkretnego adresu, a pole nadawcy łatwo podrobić lub minimalnie przekręcić, sprawdź zapis znak po znaku i w razie jakichkolwiek wątpliwości nie odpisuj — skorzystaj bezpośrednio z formularzy na `evri.com`.

### Czy mogę śledzić paczkę Evri wyłącznie po kodzie pocztowym?

Nie. Zarówno w GlobalEco, na `evri.com`, jak i w Peek najpierw potrzebujesz 16-znakowego numeru śledzenia (lub — wyłącznie na `evri.com` — 8-cyfrowego numeru brytyjskiego awiza *calling card*); kod pocztowy na `evri.com` służy jedynie jako dodatkowe potwierdzenie przy podglądzie szczegółów albo przekierowaniu dostawy w Wielkiej Brytanii.

:::sources
- [Evri: Track a parcel](https://www.evri.com/track-a-parcel) – 16-znakowy kod, 8-cyfrowa karta calling card i odnośnik do śledzenia międzynarodowego
- [GlobalEco: Evri International parcel tracking](https://globaleco.app/track/) – oficjalna wyszukiwarka przesyłek Evri International
- [Evri: International courier services](https://www.evri.com/our-services/international-courier-services) – dostawy do ponad 200 krajów i terytoriów
- [Evri: Postage to Europe](https://www.evri.com/our-services/international-courier-services/europe) – IOSS i powiadamianie odbiorcy o należnościach celnych
- [Evri: Sending a parcel to Poland](https://www.evri.com/our-services/international-courier-services/poland) – średni czas dostawy do Polski 8–11 dni oraz zasady naliczania podatków i ceł
- [Evri: EU Customs Update November 2026](https://www.evri.com/news/eu-customs-update-november-2026) – cło 3 € i opłata manipulacyjna 2 € liczone za grupę celną (6 cyfr kodu HS)
- [Evri: International deliveries FAQ](https://www.evri.com/faqs/sending-a-parcel/international-deliveries) – ogólny czas 3–7 dni roboczych oraz eBay Global Shipping Programme
- [Evri: How to contact customer services for international parcels](https://www.evri.com/faqs/sending-a-parcel/how-to-contact-customer-services-for-international-parcels) – prefiksy `H04G0C` i `H0595C`, asystent Ezra i wpisywanie `0` bez kodu pocztowego
- [Evri: What is an Evri gateway?](https://www.evri.com/faqs/receiving-a-parcel/what-is-an-evri-gateway) – odprawa celna po dotarciu paczki do Wielkiej Brytanii
- [Evri: How do I track my parcel?](https://www.evri.com/faqs/receiving-a-parcel/how-do-i-track-my-parcel) – status „on its way” do ParcelShopu, calling card i automaty Locker
- [Evri: Tracking status hasn't changed](https://www.evri.com/help-and-support/help-centre/receiving/awaiting-delivery/tracking-status) – „We're expecting it” oraz próg 24 godzin
- [Evri: Tracking status hasn't changed (senders)](https://www.evri.com/help-and-support/help-centre/sending/delivery-problem/status-has-not-changed) – 24 godziny w Next Day i 5 dni w Standard
- [Evri: Update on my delivery](https://www.evri.com/help-and-support/help-centre/receiving/awaiting-delivery/update-delivery) – 1–2 / 3–5 dni oraz 2-godzinne okno doręczenia
- [Evri: You asked me to contact you](https://www.evri.com/help-and-support/help-centre/receiving/awaiting-delivery/contact-you) – prośba o dodatkowe informacje na czacie
- [Evri: Change my delivery place or date](https://www.evri.com/help-and-support/help-centre/receiving/awaiting-delivery/change-delivery) – zmiana adresu przez nadawcę i opcje przekierowania
- [Evri: Can't find my parcel](https://www.evri.com/help-and-support/help-centre/receiving/not-find-parcel/all-correct) – status doręczenia przy braku paczki
- [Evri: Haven't received my parcel](https://www.evri.com/faqs/receiving-a-parcel/what-can-i-do-if-i-havent-received-my-parcel) – kontakt ze sprzedawcą po 7 dniach
- [Evri: Has the retailer received my return?](https://www.evri.com/faqs/returning-a-parcel/how-will-i-know-the-retailer-has-received-my-return) – końcowy status zwrotu „On its way back to the retailer”
- [Evri: Home](https://www.evri.com/) – 1-godzinne okno i do 3 prób doręczenia w UK
- [Evri: Safe place guide](https://www.evri.com/guides/safe-place) – bezpieczne miejsce i powrót do oddziału
- [Evri: Send a parcel](https://www.evri.com/send) – najbliższy ParcelShop po 3 próbach
- [Evri: Diversions](https://www.evri.com/diversions) – przekierowanie paczki na evri.com
- [Evri: Expected delivery time](https://www.evri.com/faqs/receiving-a-parcel/expected-delivery-time) – poranne wyznaczanie okna i brak przekierowania przy podpisie
- [Evri: Can I collect my parcel?](https://www.evri.com/faqs/receiving-a-parcel/can-i-collect-my-parcel) – brak odbioru osobistego, gdy paczkę ma kurier
- [Evri: ParcelShop delivery](https://www.evri.com/our-services/parcelshop-delivery) – 10 dni na odbiór, akceptowane dokumenty i Post Office
- [Evri: Lockers](https://www.evri.com/parcelshops/lockers) – dostęp 24/7, 3 dni na odbiór i przycisk „help”
- [Evri: Terms and conditions](https://www.evri.com/terms-and-conditions) – liczba prób w UK i za granicą oraz terminy reklamacji 28 i 45 dni
- [Evri: Claims](https://www.evri.com/faqs/account/how-do-i-submit-a-claim-for-a-lost-or-damaged-parcel) – reklamacja składana przez nadawcę
- [Evri: Contact us](https://www.evri.com/contact-us) – czat 24/7, numer 0330 808 5456 i adres Tell us straight
- [GOV.UK: Call charges](https://www.gov.uk/call-charges) – opłaty za brytyjskie numery 03
- [Evri: Cyber security](https://www.evri.com/cyber-security) – oficjalne domeny e-mail, nadawca SMS „Evri” i zgłoszenia na 7726
- [Evri: How will Evri contact you?](https://www.evri.com/faqs/receiving-a-parcel/how-will-evri-contact-me) – linki `evri.link`
- [Evri: Is this Evri text/email genuine?](https://www.evri.com/faqs/receiving-a-parcel/is-this-evri-text-email-genuine) – podrabiane adresy i zalecenie nieodpowiadania
- [Evri: About us](https://www.evri.com/about-us) – zmiana nazwy z Hermes UK w marcu 2022 r. i sklepy partnerskie
- [Evri: Introducing the Evri Group](https://www.evri.com/press/introducing-the-evri-group-the-uks-premier-parcel-delivery-company) – połączenie z 1 października 2025 r. i odrębna sieć Evri Premium
- [Evri: Evri Premium – a network of DHL](https://www.evri.com/our-services/evri-premium-dhl) – osobna sieć dla dużych i wartościowych paczek
- [GOV.UK: EVRi / DHL merger inquiry](https://www.gov.uk/cma-cases/evri-slash-dhl-merger-inquiry) – Apollo, mniejszościowy udział DHL Group i zgoda CMA
- [Komisja Europejska: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – zniesienie progu 150 €, cło 3 € i opłata unijna
- [Poczta Polska: Zmiany w opłatach dla przesyłek spoza UE od 1 lipca 2026 r.](https://www.poczta-polska.pl/news/zmiany-w-oplatach-dla-przesylek-spoza-unii-europejskiej-od-1-lipca-2026-r/) – naliczanie 3 € za rodzaj produktu przy przesyłkach pocztowych do 150 €
- [Your Europe: Wysyłka i dostawa](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_pl.htm) – 30 dni na dostawę i odpowiedzialność sprzedawcy do chwili odbioru
- [Universal Parcel Scraper: Evri International](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/evri) – `C` na 6. pozycji, pola GlobalEco i przekazywanie partnerom
- [Universal Parcel Scraper: Evri UK](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/evri-uk) – kody 16-znakowe, `C00HHA`, 16 cyfr i statusy w Wielkiej Brytanii
:::
