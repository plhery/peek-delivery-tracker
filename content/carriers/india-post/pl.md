---
title: Śledzenie India Post: numery Speed Post, statusy i cło
description: Śledź przesyłkę India Post lub Speed Post: format numeru zakończonego na IN, znaczenie statusów, doręczenie przez Pocztę Polską, odprawa celna i reklamacje.
slug: sledzenie-india-post
published: 2026-11-18
updated: 2026-11-20
---

India Post to Department of Posts, państwowy operator pocztowy **Indii**, który na swojej stronie podaje liczbę 164 999 placówek pocztowych — według własnej deklaracji największą sieć pocztową na świecie. W Indiach obsługuje przesyłki Speed Post, listy polecone oraz India Post Parcel, a w ruchu zagranicznym EMS (International Speed Post), rejestrowane pakiety dla e-commerce (International Tracked Packet) i paczki lotnicze. Przesyłkę sprawdzisz w wyszukiwarce na [indiapost.gov.in](https://www.indiapost.gov.in/), która wymaga przepisania kodu CAPTCHA, albo wklejając numer w polu powyżej. India Post nazywa ten kod *article barcode number* (numerem kodu kreskowego przesyłki); przy zakupie w sklepie internetowym poproś o niego sprzedawcę.

## Numer śledzenia India Post: jak wygląda

Numer nadawczy India Post liczy 13 znaków w [międzynarodowym formacie pocztowym](guide:tracking-number-formats): dwie litery, dziewięć cyfr oraz końcówkę `IN`. Ostatnia z dziewięciu cyfr pełni rolę cyfry kontrolnej. Przykłady z wymyślonymi cyframi:

| Przykładowy numer | Co oznacza |
| --- | --- |
| `EE482910576IN` | Przesyłka ponumerowana przez India Post — krajowa w Indiach albo wysyłana za granicę. Dwie początkowe litery zależą od rodzaju usługi; kluczowa jest końcówka `IN`. |
| `EE482910576PL` | Kończy się innym kodem kraju, tutaj `PL`: numer nadała poczta innego państwa ([Poczta Polska](carrier:poczta-polska)). Paczka wysłana do Indii z zagranicy zachowuje numer poczty nadania. |

Wyszukiwarka India Post obejmuje przesyłki Speed Post, przesyłki polecone i wartościowe, Business Parcel oraz Express Parcel, przekazy elektroniczne (*electronic money orders*) oraz międzynarodowy EMS. Nie możesz znaleźć kodu? Zobacz [jak znaleźć numer śledzenia](guide:find-tracking-number).

## Statusy śledzenia India Post i ich znaczenie

Sekcja FAQ India Post sprowadza drogę przesyłki do sześciu kroków: *booked*, *received*, *bagged*, *dispatched*, *out for delivery* oraz *delivered*. Oto komunikaty rozpoznawane przez Peek w typowej kolejności:

| Komunikat India Post | Co oznacza dla twojej przesyłki |
| --- | --- |
| `Item Booked` | Nadawca nadał przesyłkę w placówce lub zarejestrował ją online, a India Post przyjęła ją do przewozu. |
| `Bag Received for Forwarding` | Worek zbiorczy z przesyłką dotarł do urzędu pocztowego w celu dalszego przesłania. |
| `Assigned to load plan` | Przesyłka została przypisana do planu załadunku na kolejny środek transportu. |
| `Transport Leg Completed` | Jeden odcinek trasy transportowej dobiegł końca. |
| `Transfer to OOE (Otb)` | Przekazanie do indyjskiego urzędu wymiany poczty z zagranicą (*Office of Exchange*). |
| `Aircraft Departure` | Samolot wiozący przesyłkę wystartował. Czasem India Post zapisuje ten etap jako `UPLIFT`. |
| `Bag unloaded at destination Airport` | Worek z przesyłką rozładowano na lotnisku w kraju docelowym. |
| `Send item to domestic location (Inb)` | Urząd wymiany w kraju przeznaczenia skierował przesyłkę do swojej sieci krajowej. |
| `Item Delivered` | Doręczono. |

Dopisek `(Otb)` oznacza skan poczty nadawczej w Indiach, a `(Inb)` skan przekazany przez operatora pocztowego kraju docelowego. W źródle odczytywanym przez Peek India Post często zwraca dziś skrócone kody w rodzaju `BAG_DISPATCH`; znane sobie kody Peek rozwija do pełnych opisów, takich jak `Bag Dispatched` czy `Item Presented To Customs`, a pozostałe zamienia na czytelne słowa. Więcej o ogólnych etapach przeczytasz w poradniku [co oznaczają statusy śledzenia](guide:tracking-statuses).

## Odbiór przesyłki India Post na terenie Indii

Poniższe zasady dotyczą doręczeń realizowanych w **Indiach**:

**Czas doręczenia.** Opublikowane przez India Post normy dla krajowego Speed Post od nadania do doręczenia wynoszą: 1–2 dni w obrocie lokalnym, 1–3 dni między głównymi metropoliami, 1–4 dni między stolicami stanów lub w obrębie jednego stanu oraz 4–5 dni do pozostałej części kraju, plus jeden dzień przy doręczeniu przez placówkę pomocniczą (*branch office*). Usługa 24 Speed Post doręcza następnego dnia w sześciu indyjskich metropoliach (Nowe Delhi, Mumbaj, Bengaluru, Ćennaj, Kalkuta i Hajdarabad) przez siedem dni w tygodniu; w razie opóźnienia nadawca może w ciągu 7 dni wystąpić online o zwrot opłaty pocztowej.

**SMS i kod OTP.** Dla paczek Speed Post w Indiach na podany numer telefonu komórkowego przychodzi SMS po dotarciu przesyłki do urzędu doręczającego oraz drugi w chwili wydania. Część przesyłek Speed Post jest wydawana wyłącznie po podaniu jednorazowego kodu (OTP): w 24 Speed Post jest on wliczony w usługę, a w pozostałych wariantach Speed Post stanowi płatny dodatek.

**Awizo po nieudanej próbie.** Śledzenie India Post odnotowuje wystawienie zawiadomienia (*Delivery Intimation Notice*) informującego, że przesyłka jest gotowa do doręczenia. Zobacz też [nieudana próba doręczenia](guide:missed-delivery) oraz [nieodebrane paczki](guide:uncollected-parcel).

**Zmiana adresu.** W Indiach nadawca, który opłacił przesyłkę online ze swojego konta w India Post, może zlecić jej wycofanie lub przekierowanie, dopóki nie została doręczona ani wydana listonoszowi na rejon.

## Paczki India Post za granicę: kto doręcza w Polsce i jakie cło obowiązuje

Za granicę India Post wysyła przesyłki EMS (International Speed Post, do 35 kg), International Tracked Packet dla sprzedawców internetowych (do 5 kg) oraz International Air Parcel (do 20 kg) — wszystkie ze śledzeniem. Po przylocie skany oznaczone `(Inb)` pochodzą od operatora pocztowego w twoim kraju: pod tym samym numerem zakończonym na `IN` sprawdzisz przesyłkę w [Poczcie Polskiej](carrier:poczta-polska) w **Polsce**, [DHL](carrier:dhl) w **Niemczech**, [La Poste](carrier:la-poste) we **Francji**, [Royal Mail](carrier:royal-mail) (Parcelforce dla EMS) w **Wielkiej Brytanii**, [USPS](carrier:usps) w **USA**, Canada Post w **Kanadzie** lub Australia Post w **Australii**.

**Odprawa celna w Unii Europejskiej (Polska).** Indie leżą poza Unią Europejską, dlatego każda paczka przechodzi odprawę celną w kraju docelowym. W **Unii Europejskiej** od 1 lipca 2026 r. towary kupione przez internet spoza UE w przesyłkach o wartości do 150 € podlegają unijnemu cłu w wysokości 3 € za pozycję towarową (*per item*), a od 1 listopada 2026 r. doliczana jest również unijna opłata manipulacyjna w wysokości 2 € za pozycję, niezależnie od wartości, oprócz krajowego podatku VAT od importu oraz — powyżej 150 € — stawek taryfy celnej. Jedną pozycję stanowią towary w paczce o tej samej klasyfikacji taryfowej, tym samym opisie i — gdy jest wymagane — tym samym pochodzeniu. Wśród usług dodatkowych dla eksporterów India Post wymienia formułę Delivery Duty Paid (DDP): zapytaj sprzedawcę, czy z niej skorzystał. Poza UE obowiązują przepisy danego kraju: zobacz [paczka zatrzymana w urzędzie celnym](guide:customs).

**Wstrzymania ruchu.** Gdy India Post zawiesza lub wznawia przyjmowanie przesyłek do danego kraju — jak przy zawieszeniu dla Kanady w październiku 2025 r. albo wznowieniu dla Iraku, Kuwejtu i Libanu w sierpniu 2026 r. — publikuje komunikat w dziale [News and Updates](https://www.indiapost.gov.in/media/newsandupdates).

**Paczki wysyłane do Indii.** Przesyłka nadana do Indii z zagranicy nosi numer nadany przez pocztę kraju wysyłki. Śledź ją najpierw na stronie tamtego operatora; FAQ India Post uprzedza, że dane od zagranicznych administracji pocztowych mogą spływać z opóźnieniem.

## Śledzenie India Post stoi w miejscu, paczka zaginęła lub wróciła

**Brak nowych skanów.** India Post zaznacza, że brak skanu nie oznacza, iż przesyłka nie wyruszyła w drogę: wpisy mogą pojawiać się online z opóźnieniem, zwłaszcza z placówek wiejskich w Indiach albo z poczt zagranicznych. Jeżeli przesyłka zagraniczna ma jako ostatni wpis `Aircraft Departure`, opuściła już Indie — sprawdź ten sam numer w śledzeniu Poczty Polskiej lub poczty swojego kraju. Więcej w poradniku [dlaczego śledzenie się nie aktualizuje](guide:tracking-not-updating).

**Opóźnienie, zaginięcie lub uszkodzenie (zasady India Post).** Reklamację w India Post składa się po upływie normatywnego czasu przewozu i w ciągu 60 dni od nadania, podając numer przesyłki; standardowy czas rozpatrzenia skargi w India Post wynosi 90 dni. W krajowym Speed Post w Indiach za opóźnienie przysługuje zwrot opłaty za nadanie, a za utratę, ubytek lub uszkodzenie — dwukrotność opłaty albo 1 000 ₹, w zależności od tego, która kwota jest niższa. Dla towarów w międzynarodowym EMS odszkodowanie wynosi do wartości zawartości albo 130 SDR, zależnie od tego, która kwota jest niższa, plus opłata pocztowa, a opóźnienie przekraczające o ponad 5 dni opublikowaną normę daje zwrot 5 % opłaty; przesyłka International Tracked Packet jest objęta odpowiedzialnością do jej wartości lub 1 000 ₹ (kwota niższa). Przy zakupie w sklepie internetowym zgłoś sprawę także sprzedawcy: zobacz [zagubiona paczka](guide:lost-parcel) oraz [uszkodzona paczka](guide:damaged-parcel).

**Status doręczenia mimo braku paczki.** Złóż reklamację kanałami poniżej albo — w Indiach — w książce skarg dostępnej w każdym urzędzie pocztowym: zobacz [przesyłka dostarczona, ale nieodebrana](guide:delivered-not-received).

**Zwrot do nadawcy.** Rejestrując przesyłkę online w India Post, nadawca może podać inny adres zwrotny niż własny. Peek oznacza zwroty etapem „Zwrócona nadawcy”: zobacz [zwrot do nadawcy](guide:returned-to-sender).

## Infolinia India Post i składanie reklamacji

Oficjalne kanały kontaktu India Post:

- **Infolinia (bezpłatna na terenie Indii):** `1800 266 6868`. Konsultanci dyżurują od 8:00 do 20:00 czasu indyjskiego (IST); automatyczny system głosowy (IVRS) działa całą dobę, również w niedziele, z wyjątkiem świąt państwowych (*gazetted holidays*) w Indiach.
- **Reklamacje:** online przez formularz [File a Complaint](https://www.indiapost.gov.in/grievance-redressal/file-a-complaint) na stronie India Post (tam też sprawdzisz status zgłoszenia), w placówce pocztowej w Indiach (u naczelnika urzędu lub *Superintendent of Post Offices*) bądź telefonicznie. Konto nie jest wymagane.
- **Odwołanie w Indiach:** jeśli India Post nie załatwi sprawy w rozsądnym terminie, rządowe Directorate of Public Grievances (`dpg.gov.in`).

Na stronie głównej India Post ostrzega przed fałszywymi linkami podszywającymi się pod pocztę: korzystaj wyłącznie z `www.indiapost.gov.in` i nigdy nie podawaj kodów OTP ani danych bankowych, kartowych czy UPI przez nieautoryzowane linki („do not share OTP, banking, card, UPI or other personal/financial information through unauthorized links”). Zobacz [fałszywe SMS-y o dopłacie do paczki](guide:delivery-scam-texts).

## Śledzenie przesyłek India Post w Peek

[Peek](/) rozpoznaje układ dwóch liter, dziewięciu cyfr i końcówki `IN` jako India Post, gdy cyfra kontrolna się zgadza, i wyświetla „Wykryty przewoźnik”; przycisk „Zmień” pozwala wskazać innego operatora. Przy błędnej cyfrze kontrolnej (np. `EE482910577IN`) pojawia się ostrzeżenie „Cyfra kontrolna tego numeru się nie zgadza.”: porównaj numer z wiadomością od nadawcy albo wybierz „Śledź tak, jak wpisano”. Po zapisaniu pod India Post przesyłka ma status „Oczekiwanie na przewoźnika India Post” aż do pierwszego skanu.

Peek nie odpytuje bezpośrednio portalu India Post: pobiera skany przez MySpeedPost, zewnętrzny serwis śledzenia spoza domeny India Post czerpiący dane z India Post (albo przez track91).

Peek wyświetla każdy wpis w brzmieniu India Post wraz z nazwą urzędu i zaznacza urząd na mapie po zweryfikowaniu miejscowości. Start samolotu pokazuje się jako `Aircraft Departure`. Zdarzenia trafiają do etapów Peek: nieudane doręczenie jako „Próba doręczenia”, a odmowa przyjęcia, uszkodzenie, zaginięcie lub niepełny adres jako „Zgłoszono problem”. Przez track91 komunikaty są krótsze: `Dispatched` (wysłano za granicę z urzędu wymiany) oraz `Aircraft arrival in Transit country` (międzylądowanie w kraju tranzytowym) widnieją jako „W transporcie”, a `Delivered` jako „Dostarczona”. Peek pomija imię i nazwisko odbiorcy, adres oraz podpis i nie pokazuje przewidywanej daty doręczenia. Skany z Indii odczytuje w czasie indyjskim (UTC+5:30), przeliczając wszystkie godziny na strefę twojego urządzenia; na peektracker.com i w aplikacjach na iPhone'a i Androida wiersz pod historią wskazuje tę strefę („Godziny w twojej strefie czasowej: …”).

Gdy India Post wskaże kraj przeznaczenia — albo go nie poda, a sprawdzasz numer spoza Indii — Peek odpytuje tym samym numerem `IN` również znanego sobie operatora na miejscu, takiego jak [Poczta Polska](carrier:poczta-polska) w Polsce, [DHL](carrier:dhl) w Niemczech, [La Poste](carrier:la-poste) we Francji, USPS, Royal Mail (lub Parcelforce dla EMS), Canada Post czy Australia Post. Gdy tamten przewoźnik zwróci świeższe skany, Peek przełącza śledzenie na niego, zachowując dotychczasową historię z India Post. Peek zna operatora dla większości państw Europy i części innych krajów, w tym NZ Post w Nowej Zelandii i Qatar Post w Katarze, ale nie dla Zjednoczonych Emiratów Arabskich, Kuwejtu, Omanu, Singapuru, Malezji ani Sri Lanki. Nie pyta natomiast India Post o numery nadane przez inne poczty: ręczny wybór India Post przy takim numerze wyświetla „nie wydaje takich numerów”.

Dopóki masz otwartą przesyłkę na ekranie albo w dniu doręczenia włączone powiadomienia, Peek sprawdza aktualizacje maksymalnie co 10 minut — a od godziny 6:00 czasu lokalnego w dniu przejścia na etap „W doręczeniu” maksymalnie co 2 minuty, o ile odpowiada MySpeedPost lub bezpośredni system twojej poczty (po przekazaniu Peek sprawdza w tempie poczty docelowej; przez same zewnętrzne serwisy śledzenia co godzinę, po dobie bez ruchu co 3 godziny i maksymalnie co 15 minut w dniu doręczenia przy otwartej karcie lub aktywnych powiadomieniach). Poza tymi warunkami oraz po dwóch dniach ciszy (lub po 12 godzinach na etapie „Zarejestrowana” bądź „Przyjęta przez przewoźnika”) Peek sprawdza status co godzinę. Powiadomienia do wyboru: „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Gdy przesyłka utknie po stronie India Post, przycisk „Poproś przewoźnika India Post o pomoc” otwiera jej stronę Contact Us. Peek nie może skontaktować się w twoim imieniu z India Post, Pocztą Polską ani ze sklepem.

## Najczęstsze pytania o śledzenie India Post

### Jak śledzić przesyłkę Speed Post z India Post?

Wpisz 13-znakowy numer zakończony na `IN` na stronie indiapost.gov.in albo wklej go w Peek, który śledzi przesyłkę aż do doręczenia, także po przejęciu jej w Polsce przez Pocztę Polską.

### Dlaczego śledzenie India Post się nie aktualizuje?

Publikacja skanów bywa opóźniona, szczególnie z wiejskich urzędów w Indiach albo od zagranicznych operatorów pocztowych. Jeśli paczka zagraniczna ma jako ostatni status `Aircraft Departure`, wyleciała już z Indii: sprawdź ten sam numer w śledzeniu Poczty Polskiej lub poczty swojego kraju.

### Ile idzie przesyłka Speed Post?

W Indiach standard India Post wynosi od 1 do 3 dni między metropoliami oraz od 4 do 5 dni do reszty kraju (o dzień dłużej przy doręczeniu przez urząd pomocniczy), a 24 Speed Post dociera następnego dnia w sześciu indyjskich metropoliach. Czas dostawy za granicę zależy od wybranej usługi międzynarodowej oraz odprawy celnej.

### Jaki jest numer infolinii India Post?

Bezpłatny w Indiach numer `1800 266 6868`: konsultanci odpowiadają od 8:00 do 20:00 czasu indyjskiego, a automatyczna infolinia działa przez całą dobę poza indyjskimi świętami państwowymi. Z zagranicy reklamację złożysz i sprawdzisz online na indiapost.gov.in bez zakładania konta.

:::sources
- [India Post: Home](https://www.indiapost.gov.in/) – wyszukiwarka, infolinia i ostrzeżenie przed oszustwami
- [India Post: Contact Us](https://www.indiapost.gov.in/help&Support/contactus) – bezpłatny numer, godziny pracy i reklamacje w urzędzie
- [India Post: Help and FAQs](https://www.indiapost.gov.in/grievance-redressal/help-faq) – numer kodu kreskowego, śledzone usługi, statusy, opóźnienia, reklamacje, wycofanie i przekierowanie oraz adres zwrotny
- [India Post: About Us](https://www.indiapost.gov.in/ministry/aboutus) – Department of Posts i 164 999 placówek pocztowych
- [India Post: Speed Post](https://www.indiapost.gov.in/mail&Parcel/mailproducts/premiumservices/speedpost) – kody OTP, normy doręczeń, SMS-y Speed Post Parcel i odszkodowania
- [India Post: 24/48 Speed Post FAQs](https://www.indiapost.gov.in/speedpost/faqs) – doręczenie następnego dnia w sześciu metropoliach, OTP i zwrot opłaty
- [India Post: India Post Parcel](https://www.indiapost.gov.in/mail&Parcel/mailproducts/domesticservices/indiapostparcel) – India Post Parcel
- [India Post: International Mail](https://www.indiapost.gov.in/mail&Parcel/mailproducts/mailservices/international) – usługi międzynarodowe i opcja DDP dla eksporterów
- [India Post: International services](https://www.indiapost.gov.in/mail&Parcel/mailproducts/internationalservices) – EMS, International Tracked Packet, paczki lotnicze i limity odpowiedzialności
- [India Post: News and Updates](https://www.indiapost.gov.in/media/newsandupdates) – komunikaty o zawieszeniach i wznowieniach ruchu
- [India Post: File a Complaint](https://www.indiapost.gov.in/grievance-redressal/file-a-complaint) – formularz reklamacyjny online i odwołanie do DPG
- [India Post: Guidelines on Complaint Registration](https://www.indiapost.gov.in/grievance-redressal/guidelines) – terminy i zasady zgłaszania reklamacji
- [Komisja Europejska: Reforma celna UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – cło 3 € i opłata manipulacyjna 2 €
- [Komisja Europejska: Wytyczne dotyczące cła w wysokości 3 € i unijnej opłaty manipulacyjnej (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – naliczanie za pozycję i definicja pozycji towarowej
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – brzmienie skanów (w tym track91), strefy czasowe i przełączanie na poczty krajów docelowych
:::
