---
title: Śledzenie CNE Express: numery 3A5V, statusy i doręczenie
description: Śledź paczkę CNE Express (递一物流) z Chin kontynentalnych: kody 3A5V, znaczenie angielskich statusów, doręczyciel w Polsce, cło w UE i śledzenie w Peek.
slug: sledzenie-cne-express
published: 2026-11-21
updated: 2026-11-21
---

CNE Express (`递一物流`, `CNE EXPRESS CO LTD` / `递一国际物流有限公司`) to firma logistyki transgranicznej założona w 2003 roku w Szanghaju w Chinach kontynentalnych, obsługująca ponad 100 krajów i regionów. Wysyła drogą lotniczą z Chin kontynentalnych zamówienia od sprzedawców na Amazon, eBay, Walmart, Wish, DHgate, TikTok, Temu, Shopify czy SHEIN i przekazuje je partnerowi w kraju docelowym. Sprawdź numer w [angielskiej wyszukiwarce CNE](https://www.cne.com/en/track) albo wklej go w polu powyżej; w katalogu Peek znajdziesz przewoźnika także pod hasłami `CNE` i `递一物流`.

## Numer śledzenia CNE Express: jak wygląda

CNE nie publikuje specyfikacji swoich numerów. Oto układ, który Peek od razu rozpoznaje jako CNE Express (ze zmyślonymi cyframi):

| Numer (przykładowy) | Co oznacza |
| --- | --- |
| `3A5V482910573` | `3A5V` i 9 cyfr: numer przesyłki CNE Express. |
| `3A5V48291057` | Tylko 8 cyfr: to nie format `3A5V` z CNE. Peek nie wskazuje CNE, lecz sprawdza numer w Colis Privé (którego 12-znakowy wzorzec pasuje) oraz w serwisach śledzenia. Sprawdź, czy nie brakuje cyfry. |
| `LX482910576CN` | Dwie litery, 9 cyfr i `CN`: przesyłka pocztowa [China Post](carrier:china-post), której usługi (*International E-packet*, *China Post Packet*) również sprzedaje CNE. |

Małe litery, spacje i myślniki nie mają w Peek znaczenia. Wyszukiwarka CNE przyjmuje kilka numerów naraz, rozdzielonych spacjami lub przecinkami.

**Gdzie znaleźć numer.** W wiadomości e-mail ze sklepu o wysyłce albo na stronie zamówienia — zobacz [jak znaleźć numer śledzenia](guide:find-tracking-number), [zamówienia z Temu](guide:temu-orders) i [zamówienia z SHEIN](guide:shein-orders). W sekcji `Shipment Information` wyszukiwarka CNE może pokazać także `Order number` sprzedawcy oraz `Last mile number` firmy doręczającej wraz z jej stroną i telefonem.

## Statusy śledzenia CNE Express — co oznaczają

Nad wierszami historii wyszukiwarka CNE wyświetla ogólny status przesyłki po angielsku:

| Status CNE | Co to oznacza dla Ciebie | Peek pokazuje |
| --- | --- | --- |
| `Shipping` | W drodze. | status najnowszego wiersza |
| `Customs inspection` | Zatrzymana przez urząd celny (`扣关` na chińskiej stronie CNE). | „W urzędzie celnym” |
| `Delivered` | Doręczona. | „Dostarczona” |
| `Invalid address`, `Lost`, `Exception`, `Destroyed` | Błędny adres, paczka zagubiona lub zniszczona albo inna przeszkoda. | „Zgłoszono problem” |
| `Returned` | Odesłana do sprzedawcy (`退件` po chińsku). | „Zwrócona nadawcy” |
| `Not delivered` | Na chińskiej stronie widnieje `未发送` („niewysłana”): paczka jeszcze nie wyruszyła. | status najnowszego wiersza |

Mogą też wystąpić `Time out` i `Unknown` — wówczas Peek kieruje się samymi wierszami historii. Każdy wiersz ma godzinę, opis i miejsce:

| CNE podaje | Co to oznacza dla Twojej paczki | Peek pokazuje |
| --- | --- | --- |
| `Parcel Infomation Received` | Sprzedawca przekazał dane paczki (oryginalna pisownia CNE); CNE jeszcze jej nie odebrał. | „Zarejestrowana” |
| `Picked Up by Courier` | Kurier CNE odebrał przesyłkę od sprzedawcy w Chinach kontynentalnych. | „Przyjęta przez przewoźnika” |
| `Arrived at CNE Facility`, `Inbound scan`, `Shipment Processed`, `Shipment Loaded` | Przyjęcie, sortowanie i załadunek w obiekcie CNE. | „W transporcie” |
| `Departed CNE Facility`, `Departure Scan`, `Depart From Local Facility` | Wyjazd z centrum logistycznego. | „W transporcie” |
| `Arrived Export Airport`, `Released From Export Customs` | Na lotnisku wylotu w Chinach kontynentalnych, a potem zwolnienie przez chiński urząd celny eksportowy. | „W transporcie” |
| `Shipment Departed From Original Country` | Samolot wyleciał z Chin kontynentalnych. | „W transporcie” |
| `Arrived At Transit Airport`, `Shipment Depart From Transit Airport` | Przystanek na lotnisku tranzytowym. | „W transporcie” |
| `Arrived at Import Airport`, `Arrived at destination` | Dotarcie do kraju docelowego: przed paczką jeszcze sortowanie i odprawa celna importowa (to nie jest jeszcze Twój lokalny oddział doręczeń). | „W transporcie” |
| `The shipment is picked up by customs broker` | Przesyłka odebrana przez agencję celną do odprawy importowej. | „W urzędzie celnym” |
| `Released From Import Customs`, `Pickup from airport` | Zwolniona z odprawy celnej importowej i odebrana z lotniska. | „W transporcie” |
| `Handled by local carrier` | Przekazana przez CNE do lokalnej sieci doręczeń; jej własny skan przyjęcia pojawi się jako kolejny. | „W transporcie” |
| `Arrived At The Delivery Company`, `Your shipment has arrived at sorting center`, `Your shipment is in process at sorting center`, `Parcel Unloaded From Container` | W firmie doręczającej albo w lokalnej sortowni. | „W transporcie” |
| `Your shipment has been processed at sorting center and ready for shipping`, `Your shipment has departed from the transit origin`, `Your shipment has arrived at the transit destination` | Posortowana w kraju docelowym i wysłana do lokalnej stacji przed wyjazdem kuriera. | „W transporcie” |
| `The shipment has been processed in the parcel center of origin.` | Skan sortowni DHL w Niemczech przekazany przez CNE po oddaniu paczki. | „W transporcie” |
| `Pendiente de recepción en CTT Express`, `Enviado`, `En reparto`, `Entregado` | Hiszpańskie statusy [CTT Express](carrier:ctt-express) przekazywane z opisem po przecinku: zapowiedź („Zarejestrowana”, bez cofania statusu w trasie), nadanie („W transporcie”), wydanie kurierowi („W doręczeniu”) i doręczenie („Dostarczona”). | zależnie od kroku |
| `On Board with Courier`, `Your shipment is out for delivery` | W samochodzie kuriera — wydana do doręczenia na dziś. | „W doręczeniu” |
| `Your shipment delivery attempt failed [Recipient not at home]` | Kurier nie zastał nikogo w domu; doręczenie zostanie ponowione. | „Próba doręczenia”, albo „Zgłoszono problem”, gdy podsumowanie wskazuje `Exception` |
| `Sorting failed []` | Błąd sortowania (puste nawiasy kwadratowe pochodzą z CNE). | „Zgłoszono problem” |
| `It was returned to customer's address by CNE` | Zwrócona przez CNE na adres sprzedawcy. | „Zwrócona nadawcy” |

Po przekazaniu partnerowi lokalnemu jego wpisy często mają postać „etykieta；wyjaśnienie” (lub z przecinkiem w CTT Express): Peek czyta samą etykietę przed separatorem, pozostawia „W transporcie” przy przyjęciu partnera oznaczonym kodem `Shipping`, a dla nieznanych opisów bierze kod statusu CNE. Końcowy lub blokujący status podsumowania (`Delivered`, `Returned`, `Customs inspection`, `Invalid address`, `Lost`, `Exception`, `Destroyed`) ma pierwszeństwo przed ostatnim wierszem.

## Kto doręcza paczki CNE Express w Polsce i innych krajach

CNE prowadzi własne linie m.in. do Polski, Niemiec, Francji, Wielkiej Brytanii, USA, Kanady, Australii i Brazylii, dobierając lokalnych partnerów na ostatnią milę: strona partnerów wymienia m.in. [DHL](carrier:dhl), [GLS](carrier:gls-de), [DPD](carrier:dpd-pl), [La Poste](carrier:la-poste), [Royal Mail](carrier:royal-mail), [Evri](carrier:evri), [Correos](carrier:correos-spain), [Poste Italiane](carrier:poste-italiane), Austrian Post, POST Luxembourg oraz [USPS](carrier:usps).

Peek bezpośrednio przypisuje trzy kody podwykonawców CNE, jeśli towarzyszy im numer transferowy: [DHL](carrier:dhl) (`DE DHL`), [Royal Mail](carrier:royal-mail) i [USPS](carrier:usps), pokazując na karcie „Doręcza: …” (lub „Zgłoszona przewoźnikowi …, jeszcze nieprzyjęta”, dopóki ten przewoźnik nie ma własnego skanu). Przy innym kodzie podwykonawcy z numerem transferowym (np. `US SPX`, nieprzypisanym do jednej nazwy), dla poprawnego międzynarodowego numeru pocztowego Peek odpytuje operatora pocztowego kraju docelowego ([Poczta Polska](carrier:poczta-polska) w Polsce) albo jedynego przewoźnika wynikającego z układu tego numeru; bez nazwanego podwykonawcy drugi kod może być numerem zamówienia sklepu i nie jest śledzony. Próby doręczenia i odbiór w punkcie podlegają zasadom firmy końcowej ([nieudane doręczenie](guide:missed-delivery)).

**Cło i opłaty w Polsce oraz Unii Europejskiej.** W **Unii Europejskiej** towary kupione online spoza UE w paczkach o wartości rzeczywistej do 150 € objęte są od 1 lipca 2026 r. unijnym cłem 3 € za pozycję towarową (*per item*), a od 1 listopada 2026 r. dochodzi unijna opłata manipulacyjna (*Union handling fee*) w wysokości 2 € za pozycję bez względu na wartość (wspólny kod taryfowy, opis i — gdy wymagane — pochodzenie; powyżej 150 € zwykłe stawki celne). W komunikacie z 22 października 2026 r. CNE poinformowało sprzedawców, że od 26 października 2026 r. o `00:00` (czasu pekińskiego) na trasach do 27 państw UE pobiera od sprzedawców z góry te 2 € za każdą paczkę i kod HS obok 3 € pobieranych w ten sam sposób od lipca 2026 r., odprawiając przesyłki na podstawie deklaracji nadawcy. Komunikat nie wspomina o podatku VAT przy imporcie do **Polski** (należnym, o ile nie pobrano go już przy zakupie przez IOSS) ani o ewentualnych opłatach lokalnego doręczyciela ([paczka zatrzymana przez urząd celny](guide:customs)). Komunikaty o utrudnieniach na trasach CNE publikuje po chińsku w dziale `新闻公告`.

## Paczka CNE Express stoi w miejscu, zaginęła lub wraca

Klientami CNE są sprzedawcy zlecający wysyłkę: w sprawie opóźnienia, zaginięcia, uszkodzenia lub zwrotu pisz najpierw do sklepu lub platformy.

**„Oczekiwanie na przewoźnika CNE Express”.** Dopóki CNE zwraca informację, że zamówienie nie istnieje (`订单不存在`), Peek pokazuje „Oczekiwanie na przewoźnika CNE Express”; jeśli po dwóch dniach CNE nadal nie zna numeru i żaden serwis śledzenia nie odpowiada, Peek wyświetla: „Po dwóch dniach przewoźnik nadal nie zna tego numeru. Sprawdź go, wybierz innego przewoźnika lub usuń przesyłkę.” Przy `Not delivered` (`未发送`) albo `Parcel Infomation Received` dopytaj sprzedawcę, gdy przez kilka dni nic nie rusza.

**Cztery dni bez aktualizacji.** Gdy przesyłka w drodze nie ma nowego wpisu przez cztery dni, Peek pokazuje „Brak aktualizacji od czterech dni” z uwagą o długich lotach, cle i weekendach oraz przyciskiem „Poproś przewoźnika CNE Express o pomoc” prowadzącym do strony kontaktowej CNE. Zobacz [dlaczego śledzenie się nie aktualizuje](guide:tracking-not-updating) oraz [śledzenie paczek z Chin](guide:tracking-from-china).

**W urzędzie celnym, z problemem, zwrócona lub oznaczona jako dostarczona.** Przy `Customs inspection` (`扣关`), `The shipment is picked up by customs broker`, `Lost`, `Destroyed`, `Invalid address` lub `Returned` zgłoś się do sprzedawcy ([zagubiona paczka](guide:lost-parcel), [zwrot do nadawcy](guide:returned-to-sender)). W wyszukiwarce CNE wiersz z przyciskiem `View POD` otwiera potwierdzenie doręczenia po wpisaniu nazwiska albo kodu pocztowego odbiorcy ([dostarczona, ale nieotrzymana](guide:delivered-not-received)).

## Obsługa klienta i kontakt z CNE Express

Na stronie [Contact us przewoźnika CNE](https://www.cne.com/en/about/contact):

- **Ogólnokrajowa infolinia**: `400-021-5600` (*National Service Hotline*, numer serwisowy w Chinach kontynentalnych).
- **Centrala w Szanghaju**: `021-5109 6677`, `15/F, Block G, Lane 1777, Hualong Road, QingPu District, Shanghai` (Chiny kontynentalne).
- **Godziny pracy**: `08:30-18:00 Mon-Sun` bez podanej strefy czasowej (sortownie w Jiaxing i Dongguan w Chinach kontynentalnych pracują `7 x 24 Hours`, co widnieje również w stopce strony).
- **Formularz online**: *Consulting form* na tej samej stronie — wybierz typ `Complaint` i podaj telefon, e-mail, imię, numer śledzenia oraz wiadomość (CNE nie podaje adresu e-mail obsługi klienta).

**Uwaga na fałszywe SMS-y.** Oficjalna domena CNE Express to `www.cne.com` (`cne.com`). Zanim klikniesz link z wiadomości SMS lub e-mail, sprawdź [fałszywe SMS-y o paczkach](guide:delivery-scam-texts).

## Śledzenie paczki CNE Express w Peek

[Peek](/) natychmiast rozpoznaje CNE Express po numerze `3A5V` („Wykryty przewoźnik”), a każdy link `cne.com` z parametrem `?no=` przypisuje paczkę do CNE Express (biorąc pierwszy numer, gdy jest ich więcej). Dla innego alfanumerycznego numeru CNE od 6 do 40 znaków, gdy Peek wyświetla „Sprawdzanie usług śledzenia…” albo nie wskaże CNE, stuknij „Zmień” i wybierz CNE Express na liście „Wszyscy przewoźnicy” (albo użyj później „Zmień przewoźnika”); poza zakresem 6–40 liter i cyfr Peek pokaże, że przewoźnik „nie wydaje takich numerów”.

Peek odczytuje status, każdy skan z lokalizacją, kraj przeznaczenia oraz przewoźnika końcowego z numerem transferowym. Wpisy `Arrived at destination` i `Handled by local carrier` odczytuje według czasu pekińskiego (`UTC+8`), a przekazywane hiszpańskie statusy [CTT Express](carrier:ctt-express) w `UTC+02:00` (lub `UTC+01:00` dla kroków doręczenia [CTT](carrier:ctt) do Portugalii) i przelicza na strefę Twojego urządzenia („Godziny w Twojej strefie czasowej: …”), natomiast pozostałe czasy bez strefy zachowuje w postaci podanej przez CNE; pomija numer zamówienia sprzedawcy, pozbawioną strefy datę doręczenia z podsumowania oraz `View POD` (CNE nie udostępnia szacowanej daty ani wagi). Przycisk „Otwórz stronę przewoźnika CNE Express” otwiera Twój numer na `cne.com`. Gdy odpytywany przewoźnik końcowy pokaże skan co najmniej tak świeży jak ostatni wpis CNE, Peek przełącza się na niego, zachowując historię z CNE.

Dopóki masz otwartą przesyłkę lub w dniu dostawy włączone powiadomienia, Peek sprawdza zmiany nawet co 10 minut — a od 6:00 czasu lokalnego w dniu ze statusem „W doręczeniu” nawet co 2 minuty, dopóki odpowiada wyszukiwarka CNE (po przejęciu przez firmę końcową przejmuje jej tempo; przez same usługi śledzenia: co godzinę, co 3 godziny po dobie bez zmian i do co 15 minut w dniu dostawy przy otwartej paczce lub włączonych powiadomieniach); w pozostałych przypadkach oraz po dwóch dniach bez nowości (12 godzin na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika”) sprawdza co godzinę. Alerty: „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek nie kontaktuje się z CNE ani ze sprzedawcą za Ciebie.

## Najczęstsze pytania o śledzenie CNE Express

### Czy CNE Express to wiarygodna firma?

Tak. `CNE EXPRESS CO LTD` (`递一国际物流有限公司`) powstała w 2003 roku w Szanghaju w Chinach kontynentalnych, ma centrum operacyjne w Shenzhen oraz sortownie w Jiaxing i Dongguan o wydajności ponad 2 milionów przesyłek dziennie od 2020 roku i obsługuje sprzedawców z Amazon, eBay, Walmart, TikTok, Temu i SHEIN.

### Kto doręcza paczki CNE Express?

Poczta lub firma kurierska wybrana przez CNE w kraju docelowym (na liście partnerów są m.in. DHL, GLS, DPD, La Poste i Royal Mail), a w wyszukiwarce CNE może widnieć jej `Last mile number`.

### Co oznacza `Arrived at destination` w CNE Express?

Paczka dotarła do kraju docelowego, ale przed nią mogą być jeszcze sortowanie i odprawa celna importowa — nie leży jeszcze w Twojej lokalnej stacji doręczeń. CNE zapisuje ten wpis w czasie pekińskim (`UTC+8`, zimą 7 godzin przed Warszawą), podczas gdy sąsiednie wpisy zagraniczne mają czas lokalny bez strefy i mogą wyglądać na niechronologiczne.

### Ile trwa dostawa przez CNE Express?

CNE podaje sprzedawcom orientacyjne czasy zależne od trasy: `3-8 business days` dla *CNE Priority Express* (do USA, Wielkiej Brytanii, Francji i Niemiec) oraz `5-20 business days` dla *CNE Standard Express*. Kieruj się terminem podanym przez sklep.

### Jak skontaktować się z CNE Express?

Zadzwoń pod `400-021-5600` (infolinia w Chinach kontynentalnych) lub `021-5109 6677` w Szanghaju albo wypełnij *Consulting form* na `cne.com/en/about/contact`, wybierając `Complaint`. W sprawie samego zamówienia skontaktuj się najpierw ze sprzedawcą.

:::sources
- [CNE: Track your parcel](https://www.cne.com/en/track) – angielska wyszukiwarka, etykiety statusów, Shipment Information i View POD
- [CNE: 运单追踪 (śledzenie po chińsku)](https://www.cne.com/track) – chińskie odpowiedniki 未发送 i 扣关
- [CNE: Home](https://www.cne.com/en) – założenie w 2003 r. w Szanghaju, kierunki, platformy i infolinia 400
- [CNE: 首页 (strona główna po chińsku)](https://www.cne.com/) – 递一物流 i 递一国际物流有限公司
- [CNE: About us](https://www.cne.com/en/about/intro) – sortownie w Dongguan i Jiaxing, ponad 2 miliony przesyłek dziennie od 2020 r.
- [CNE: Partner](https://www.cne.com/en/about/partner) – międzynarodowi partnerzy logistyczni
- [CNE: Contact us](https://www.cne.com/en/about/contact) – infolinia 400, biuro w Szanghaju, godziny 08:30-18:00 i Consulting form (Complaint)
- [CNE: Postal Parcel](https://www.cne.com/en/solutions/economyParcel) – usługi China Post w ofercie CNE
- [CNE: CNE Priority Express](https://www.cne.com/en/solutions/product/36024) – orientacyjny czas 3-8 business days i trasy US/GB/FR/DE
- [CNE: CNE Standard Express](https://www.cne.com/en/solutions/product/36025) – orientacyjny czas 5-20 business days
- [CNE: 新闻公告 (komunikaty)](https://www.cne.com/news/notice) – powiadomienia o opóźnieniach według krajów
- [CNE: 关于欧盟国家2026年11月税改通知 (komunikat o reformie UE)](https://www.cne.com/news/article/rDe4ANa6KYOW) – pobieranie od sprzedawców z góry 2 € od 26 października 2026 r. obok 3 € za kod HS oraz odprawa na podstawie deklaracji nadawcy
- [Komisja Europejska: Reforma celna UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – cło 3 € od 1 lipca 2026 r. i opłata manipulacyjna 2 € od 1 listopada 2026 r.
- [Komisja Europejska: Wytyczne dotyczące cła 3 € i opłaty manipulacyjnej Unii (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – opłata 2 € niezależnie od wartości, definicja pozycji i zwolnienie IOSS
- [Universal Parcel Scraper: CNE Express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/cne) – format 3A5V, linki ?no=, statusy i kody, podwykonawcy, zegary pekiński / CTT Express / lokalne i przekazanie
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – rozpoznawanie Colis Privé i China Post oraz przełączenie na przewoźnika końcowego
:::
