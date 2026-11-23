---
title: Śledzenie euShipments: statusy i lokalny kurier
description: Śledź zamówienie euShipments: skąd wziąć numer, co oznaczają New, Loaded hub i On delivery, kto doręcza przesyłkę i jak się skontaktować.
slug: sledzenie-eushipments
published: 2026-11-22
updated: 2026-11-22
---

euShipments to firma logistyczna e-commerce z siedzibą w Ruse w Bułgarii. Magazynuje, pakuje i wysyła zamówienia sklepów internetowych do 28 krajów ze swojej listy „We deliver to” — 27 państw UE oraz Wielkiej Brytanii — a doręczenie na ostatniej mili przekazuje lokalnemu kurierowi. Przesyłkę sprawdzisz na stronie „Track my order”, [eushipments.com/tracking](https://eushipments.com/tracking), albo wklejając numer w pole powyżej. Numer podaje sklep.

## Numer śledzenia euShipments

euShipments nie publikuje stałego formatu numeru. Informuje, że po przetworzeniu zamówienia otrzymujesz „tracking code, AWB number or tracking ID” do wpisania w pole „Enter your tracking number to track packages.” System wyświetla zamówienie pod numerem wysłanym przez sklep, którym bywa też numer lokalnego kuriera (z fikcyjnymi cyframi):

| Numer | Co oznacza |
| --- | --- |
| `74829103` | Osiem cyfr: jedyny format widziany przez Peek na stronie euShipments, dla przesyłki doręczonej w Bułgarii pod tym samym numerem przez lokalnego kuriera. |
| Numer po „Tracking Number:” | Własny numer lokalnego kuriera widoczny na stronie wyników euShipments, gdy kurier przejmie paczkę. Działa bezpośrednio w śledzeniu tego kuriera. |

Osiem cyfr pasuje też do [Mondial Relay](carrier:mondial-relay), Colisweb i [Heppner](carrier:heppner), więc sam numer nie wskazuje jednoznacznie na euShipments: sprawdź w e-mailu sklepu, jakiego przewoźnika podał. Link do strony śledzenia euShipments nie otworzy zamówienia — numer trzeba wpisać w formularzu. Brak numeru? Zobacz [jak znaleźć numer śledzenia](guide:find-tracking-number).

## Statusy śledzenia euShipments

Wynik pokazuje „My order N” i twój numer, a poniżej tabelę z trzema kolumnami: „Date/Time”, „Tracking Status” i „Tracking Information”. Status jest zawsze w jednolitym angielskim brzmieniu euShipments, niezależnie od kuriera. W „Tracking Information” pojawiają się obok komunikaty lokalnego kuriera, gdy przejmie paczkę. Każdy wpis ma datę i godzinę co do minuty, np. „10 August, 2026 15:39”, bez strefy czasowej.

| euShipments podaje | Co oznacza dla ciebie |
| --- | --- |
| `New` | Sklep przekazał zamówienie do euShipments. Paczka może jeszcze nie być spakowana. |
| `Warehouse` | Zamówienie jest w wymienionym obok magazynie fulfillment euShipments. |
| `Loaded hub` | Załadowana w hubie euShipments w drogę do kolejnego. Po statusie pojawia się nazwa hubu z krajem w nawiasie, np. (HR). |
| `Unloaded hub` | Rozładowana w hubie, opisanym tak samo. |
| `Picked up` | Lokalny kurier odebrał paczkę. |
| `In transit` | W trasie w sieci kuriera. |
| `On delivery` | U kuriera w doręczeniu. |
| `Delivered` | Dostarczona. Ostatni status zamówienia. |

`Loaded hub` i `Unloaded hub` mogą pojawić się kilkukrotnie, dla każdego hubu po drodze. Po `Picked up` pod tabelą wyświetlają się trzy wiersze: „Courier Name:”, „Tracking Number:” i „Tracking Link:” — nazwa kuriera doręczającego, jego numer i link do jego śledzenia.

Peek nie spotkał osobnego statusu euShipments dla nieudanej próby doręczenia, punktu odbioru ani zwrotu. W takich sytuacjach sprawdź opis kuriera w „Tracking Information” lub otwórz jego link.

## Kto doręcza paczkę euShipments w twoim kraju

Ostatnią milę euShipments powierza lokalnym firmom kurierskim. Przewozi paczki transportem liniowym ze swoich centrów fulfillment i cross-docking (Sofia i Ruse w Bułgarii, Oradea w Rumunii, Zagrzeb w Chorwacji, Lublana w Słowenii, Senec na Słowacji, Budapeszt na Węgrzech) do oddziału lokalnego kuriera. W FAQ podaje współpracę z ponad 60 lokalnymi kurierami, co najmniej dwoma w każdym kraju europejskim, a lista „We deliver to” obejmuje 28 krajów: 27 państw UE i Wielką Brytanię.

Strona główna pokazuje logotypy kluczowych partnerów („Key eCommerce partnerships”), w tym Speedy, Sameday, [FAN Courier](carrier:fan-courier), Express One, GLS, DPD, BOX NOW, Pošta Slovenije, [Slovenská pošta](carrier:slovenska-posta), PPL, ACS i [Geniki Taxydromiki](carrier:geniki-taxydromiki), nie podając, który doręcza gdzie. Historia firmy wspomina też strategiczne partnerstwo z Austrian Post w 2026 r., bez szczegółów zakresu. To, który kurier przyniesie paczkę, zależy od kraju i wybranej metody dostawy w sklepie — pokazuje to wiersz „Courier Name:”.

**Dokąd może trafić paczka.** W Bułgarii euShipments doręcza pod adres domowy lub firmowy oraz poza dom: do punktu kuriera, punktu odbioru (PUDO) lub automatu paczkowego. Strony dla Chorwacji i Rumunii wymieniają podobne opcje. Sieć obejmuje ponad 150 000 punktów poza domem, a gdy paczka czeka na odbiór, otrzymujesz powiadomienie z instrukcją.

**Próby doręczenia, przechowywanie i ponowna dostawa.** Obowiązują zasady lokalnego kuriera — euShipments nie publikuje własnych. Użyj „Tracking Link:” i „Tracking Number:”, aby sprawdzić stronę kuriera i umówić kolejną próbę lub zmianę adresu.

**Płatność za pobraniem.** euShipments oferuje pobranie w wielu krajach europejskich: lokalny kurier pobiera kwotę, która trafia potem do sklepu.

## Paczka euShipments stoi, zaginęła lub wróciła

**Zatrzymana na `New`.** Sklep przekazał zamówienie do euShipments, ale nie pojawił się jeszcze skan z magazynu ani hubu. Zapytaj sklep, kiedy wyśle paczkę.

**Zatrzymana między hubami.** Po `Loaded hub` paczka jedzie do kolejnego hubu i następny wpis pojawia się zwykle przy rozładunku. Jeśli nic się nie dzieje dłużej niż szacował sklep, napisz do sklepu. Zobacz [dlaczego śledzenie przestaje się aktualizować](guide:tracking-not-updating).

**Zatrzymana po `Picked up`.** Paczkę ma lokalny kurier. Otwórz „Tracking Link:” i sprawdź jego stronę pod jego numerem.

**Dostarczona, ale jej nie ma.** Doręczenie wykonał lokalny kurier — zacznij od niego, a potem powiadom sklep. Zobacz [dostarczona, ale nieotrzymana](guide:delivered-not-received).

**Zwrócona do nadawcy.** Strona zwrotów euShipments klasyfikuje przesyłkę niedoręczoną z powodu błędnych danych odbiorcy lub braku kontaktu jako niedostarczoną: wraca do magazynu euShipments lub do sklepu, nie do ciebie. Poproś sklep o ponowną wysyłkę lub zwrot pieniędzy. Więcej w [zwrócona do nadawcy](guide:returned-to-sender).

**Samodzielny zwrot.** euShipments obsługuje zwroty dla sklepów na trzy sposoby: zgłoszenie zwrotu na stronie sklepu z wyborem dnia, godziny i miejsca odbioru przez kuriera; odbiór zamówiony przez sklep; albo samodzielne odesłanie dowolnym kurierem na lokalny adres zwrotów podany przez sklep (jeden z magazynów euShipments). Który sposób obowiązuje, określają zasady zwrotów sklepu. Zobacz [śledzenie zwrotu](guide:returns-tracking).

**Zaginiona lub uszkodzona.** euShipments nie publikuje procedury reklamacyjnej dla odbiorców: umowę ma sklep. W UE sprzedawca odpowiada za uszkodzenia od wysyłki do odbioru i powinien dostarczyć zamówienie w 30 dni, chyba że uzgodniono inaczej. Jeśli tego nie zrobi, przypomnij mu i wyznacz rozsądny dodatkowy termin; jeśli nadal nie dostarczy, możesz odstąpić i odzyskać pieniądze. Zobacz [zagubiona paczka](guide:lost-parcel) i [uszkodzona paczka](guide:damaged-parcel).

## Telefon i obsługa klienta euShipments

euShipments udostępnia następujące kontakty:

- **Telefon:** +359 700 70 755, bułgarski numer na stronach kontaktu, FAQ i śledzenia. Godzin pracy nie podano.
- **E-mail:** office@eushipments.com.
- **Formularz kontaktowy:** ankieta wyceny dla sklepów (miesięczny wolumen, rynki sprzedaży), nie dla odbiorców.

FAQ dla sklepów wspomina, że obsługa odbiorców przez telefon, e-mail i czat na żywo działa na życzenie sklepu („when requested”). Zacznij więc od sklepu; w sprawie samego doręczenia szybciej odpowie kurier z wiersza „Courier Name:”.

## Śledzenie paczki euShipments w Peek

[Peek](/) nie rozpoznaje numeru euShipments po samym kształcie: nie ma reguły formatu dla euShipments, a osiem cyfr słabo pasuje do innych przewoźników. Wklej go mimo to — Peek śledzi ponad 4500 przewoźników. Następnie kliknij „Zmień” i wybierz „euShipments” albo użyj „Zmień przewoźnika” na już dodanej paczce.

Peek odczytuje stronę śledzenia euShipments. Pokazuje status, historię oraz w wpisach magazynowych i hubowych lokalizację (z krajem przy hubach). Zachowuje angielskie statusy euShipments poza „In transit” i „Delivered”, pokazywanymi jako „W transporcie” i „Dostarczona”, i pomija opisy kuriera z „Tracking Information”. Gdy „Tracking Link:” prowadzi do kuriera obsługiwanego przez Peek — np. [Econt](carrier:econt), Sameday, [FAN Courier](carrier:fan-courier), [Packeta](carrier:packeta), [Slovenská pošta](carrier:slovenska-posta) lub [Geniki Taxydromiki](carrier:geniki-taxydromiki) — z jego numerem w linku, Peek wyświetla „Doręcza: …” i ten drugi numer, jeśli różni się od twojego. Dopóki ten kurier ma tylko zapowiedź bez własnego skanu, widać „Zgłoszona przewoźnikowi …, jeszcze nieprzyjęta”. Gdy kurier pokaże datowany postęp co najmniej tak świeży jak ostatni wpis euShipments, Peek przechodzi na niego, zachowując wcześniejsze wpisy; w przeciwnym razie zostaje przy historii euShipments.

euShipments nie podaje przewidywanego dnia doręczenia ani potwierdzenia doręczenia, więc Peek ich nie wyświetla. Godziny bez strefy czasowej są odczytywane we wpisach hubowych według zegara kraju huba (czasu bułgarskiego dla huba w Bułgarii), w pozostałych według zegara sąsiedniego wpisu hubowego, a bez takiego wpisu według czasu bułgarskiego, i przeliczane na twoją strefę czasową („Godziny w twojej strefie czasowej:”). Peek sprawdza status do co 10 minut, a w dniu przejścia paczki do etapu „W doręczeniu” co 2 minuty od 6:00 według zegara ostatniego skanu, gdy paczka jest otwarta w Peek albo w dniu dostawy z włączonymi powiadomieniami, o ile strona euShipments odpowiada. Po dwóch dniach bez zmian (po 12 godzinach na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika”) sprawdza co godzinę, a po siedmiu dniach bez zmian przy statusie „Zarejestrowana” co 6 godzin. W powiadomieniach możesz wybrać „Każdy skan”, „Ważne etapy” albo „Tylko doręczenie”. Po czterech dniach bez skanu przy statusie „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie” Peek pokazuje „Brak aktualizacji od …”, uwagę „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika euShipments.” i opcję „Poproś przewoźnika euShipments o pomoc”. Peek nie skontaktuje się za ciebie z euShipments, sklepem ani kurierem.

## Pytania o śledzenie euShipments

### Czy euShipments to prawdziwa firma?

Tak. To firma logistyczna z Ruse w Bułgarii, założona w 2012 r. jako InOut Trade i przemianowana na euShipments.com w 2019 r. Wysyła zamówienia dla sklepów internetowych, w tym sprzedawców na Temu i eMAG poprzez własne integracje.

### Który kurier doręczy moją paczkę euShipments?

Lokalny kurier w twoim kraju. Gdy przejmie przesyłkę, wynik na stronie euShipments pokaże „Courier Name:”, „Tracking Number:” i „Tracking Link:” pod tabelą.

### Ile trwa dostawa przez euShipments?

Zależy to od magazynu, z którego wysyłany jest towar. Z Sofii i Ruse euShipments podaje dostawę następnego dnia do Bułgarii i Rumunii oraz 2–4 dni do Chorwacji, Słowenii, Słowacji i Czech. Z Zagrzebia, Lublany i Senec podaje 1 dzień roboczy na terenie danego kraju. Kieruj się szacunkiem sklepu. Więcej w [czasach dostawy](guide:delivery-times).

### Co oznacza Loaded hub w euShipments?

Paczka została załadowana w jednym z hubów euShipments na kolejny odcinek trasy, podanym z krajem w nawiasie. `Unloaded hub` oznacza przyjazd do następnego hubu.

### Do jakich krajów dostarcza euShipments?

Lista „We deliver to” obejmuje 28 krajów: 27 państw UE oraz Wielką Brytanię. W każdym z nich doręczenie końcowe wykonuje lokalny kurier.

:::sources
- [euShipments: Track Your Order](https://eushipments.com/tracking) – formularz śledzenia i etykiety, telefon, e-mail
- [euShipments: Parcel Tracking For End Customers](https://eushipments.com/ecommerce-delivery/new-parcel-tracking/parcel-tracking-for-end-customers) – kod śledzenia, numer AWB lub ID, ponad 60 partnerów ostatniej mili
- [euShipments: We deliver to](https://eushipments.com/we-deliver-to) – 28 krajów: 27 państw UE i Wielka Brytania
- [euShipments: FAQ](https://eushipments.com/faq) – ponad 60 kurierów, co najmniej dwóch w każdym kraju, wsparcie odbiorców na życzenie, telefon, e-mail
- [euShipments: Contact Us](https://eushipments.com/contact) – telefon, e-mail, Ruse, formularz wyceny dla sklepów
- [euShipments: About us](https://eushipments.com/about-us) – InOut Trade, 2012, zmiana nazwy 2019, partnerstwo Austrian Post
- [euShipments: Home](https://eushipments.com/) – logotypy kluczowych partnerów e-commerce
- [euShipments: eCommerce Deliveries To Bulgaria](https://eushipments.com/countries/bulgaria) – Sofia i Ruse, przewóz do oddziału kuriera, dostawa pod adres i poza dom, czasy tranzytu, pobranie
- [euShipments: International eCommerce Delivery To Romania](https://eushipments.com/countries/romania) – Oradea, Budapeszt, opcje dostawy
- [euShipments: Domestic and International eCommerce Delivery To Croatia](https://eushipments.com/countries/croatia) – Zagrzeb i pozostałe centra, dostawa następnego dnia, automaty, punkty odbioru, placówki
- [euShipments: International Parcel Delivery To Slovenia](https://eushipments.com/countries/slovenia) – Lublana, 1 dzień roboczy
- [euShipments: International Delivery To Slovakia](https://eushipments.com/countries/slovakia) – Senec, 1 dzień roboczy
- [euShipments: Out-of-home Delivery](https://eushipments.com/ecommerce-delivery/out-of-home-delivery) – 150 000 punktów, powiadomienia o odbiorze
- [euShipments: Returns Management](https://eushipments.com/returns-management) – przesyłki niedoręczone, trzy sposoby zwrotu
- [euShipments: Temu Integration](https://eushipments.com/integration/ecommerce-platform-integration/temu) – sprzedawcy Temu
- [euShipments: eMAG Integration](https://eushipments.com/integration/ecommerce-platform-integration/emag-integration) – sprzedawcy eMAG
- [Your Europe: Shipping and delivery](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_en.htm) – 30 dni, odpowiedzialność sprzedawcy do odbioru, ponaglenie i zwrot pieniędzy
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – etykiety strony wyników, statusy, numery, co czyta i pokazuje Peek
:::
