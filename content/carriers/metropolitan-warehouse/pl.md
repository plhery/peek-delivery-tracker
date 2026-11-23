---
title: Śledzenie Metropolitan Warehouse (MWD): dostawy mebli
description: Śledź zamówienie Metropolitan Warehouse & Delivery (MWD): 12-cyfrowy numer, statusy, umawianie dostawy, 72-godzinny termin na szkody i kontakt.
slug: sledzenie-metropolitan-warehouse
published: 2026-11-23
updated: 2026-11-23
---

Metropolitan Warehouse & Delivery (MWD) to rodzinna amerykańska firma specjalizująca się w dostawach mebli i logistyce 3PL, założona w 1989 roku w Perth Amboy w stanie New Jersey. Poprzez 47 hubów i terminali przeładunkowych doręcza meble do domów dla sklepów internetowych, marek meblarskich i projektantów w 48 przyległych stanach USA. Wpisz 12-cyfrowy numer na [stronie śledzenia MWD](https://www.gomwd.com/track-a-shipment) albo wklej go w pole powyżej.

## Numer śledzenia MWD: 12 cyfr

[Wyszukiwarka MWD](https://main.gomwd.com/client/wixtrackorder) ma jedno pole, *Tracking #*, i przyjmuje dokładnie 12 cyfr bez innych znaków (cyfry wymyślone):

| Numer | Co to jest |
| --- | --- |
| `584927163048` | Numer śledzenia MWD. Na stronie zamówienia widnieje w grupach po cztery: `5849 2716 3048`. |
| `POMS #`, `Client REF# 1`, `Client REF# 2`, `Carrier PRO #` | Inne numery referencyjne na stronie zamówienia (MWD, sklepu lub przewoźnika partnerskiego). Nie służą do śledzenia. |

System nie wymaga kodu pocztowego ani numeru telefonu, ale korzysta z reCAPTCHA i może poprosić o przepisanie tekstu z obrazka captcha. Wpis inny niż 12 cyfr zwraca `Please enter a valid tracking number of 12 digits.`, a nieznany numer — `Tracking id does not exist.`. Sprawdź kod w e-mailu ze sklepu lub zapytaj sprzedawcę ([jak znaleźć numer przesyłki](guide:find-tracking-number)). MWD udostępnia sklepom strony śledzenia pod ich własną marką, więc sklep może przysłać Ci link w swojej szacie graficznej.

## Co oznaczają statusy Metropolitan Warehouse

Historia MWD jest zapisywana po angielsku, od najnowszego zdarzenia, z datą, opisem oraz miastem i stanem. Oto statusy zmapowane w [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/metropolitan-warehouse), w typowej kolejności:

| Komunikat MWD | W Peek | Co oznacza dla Ciebie |
| --- | --- | --- |
| `Order registered` | Zarejestrowana | Zamówienie zostało zarejestrowane w MWD. |
| `Pickup document attached` | bez zmian | Dołączono dokument odbioru; towar jeszcze się nie ruszył. |
| `Pickup scheduled for 11/02/2026` | Zarejestrowana | Zaplanowano odbiór od dostawcy; MWD nie ma jeszcze mebla. |
| `Pickup completed, No exceptions` | Przyjęta przez przewoźnika | MWD odebrało zamówienie bez zgłoszonych zastrzeżeń. |
| `Order enroute to Metro-Hub` | W transporcie | W drodze do hubu MWD. |
| `Furniture Received by Metro-Hub` | W transporcie | Zeskanowana w hubie MWD (z podaniem miasta). |
| `Reached Delivery Hub` | W transporcie | Dotarła do hubu realizującego doręczenie. |
| `Delivery Scheduled for WEEK OF 11/09/2026` | bez zmian | Dostawa zaplanowana na ten tydzień; konkretny dzień określa umówiony termin. |
| `Delivery Rescheduled for WEEK OF 11/16/2026` | bez zmian | Dostawę przesunięto na inny tydzień. |
| `Delivery Appointment Canceled` | bez zmian | Termin anulowano; zwykle następuje nowy, w przeciwnym razie zapytaj sklep lub MWD. |
| `Arrived at Delivery Location` | W doręczeniu | Ekipa doręczeniowa dotarła pod Twój adres. |
| `Delivery Completed, No Exceptions` | Dostarczona | Doręczona bez zastrzeżeń (mimo to obejrzyj każdy element). |

Strona MWD zapisuje czasem nazwę hubu jako `Metro-<Warehouse>-Hub` z pustym miejscem; Peek skraca to do `Metro-Hub`. W historii nie ma wcześniejszego wpisu o wyjeździe w trasę: pierwszym wpisem dnia dostawy jest dopiero `Arrived at Delivery Location`, gdy ekipa stoi już pod drzwiami.

## Termin, zakres usługi i nieobecność

**Termin i śledzenie na żywo.** Na stronach [White Glove Delivery](https://www.gomwd.com/services/white-glove-delivery) i [Services](https://www.gomwd.com/services) MWD podaje, że klienci rezerwują okno czasowe samodzielnie, otrzymują automatyczne przypomnienia, a w dniu dostawy link na żywo z lokalizacją ekipy i przewidywaną godziną przyjazdu. Strona śledzenia pokazuje także okno dostawy i potwierdzenie doręczenia.

**Co robi ekipa.** Zakres zależy od poziomu wykupionego przez sklep (widocznego jako *Service Type* na stronie MWD):

| Poziom usługi | Zakres prac ekipy |
| --- | --- |
| Full White Glove | Wniesienie do wybranego pokoju, lekki montaż i zabranie wszystkich opakowań. |
| White Glove (No Assembly) | To samo dla mebli zmontowanych i dywanów, bez montażu. |
| Room of Choice | Wniesienie do środka do wskazanego pomieszczenia, bez montażu. |
| Threshold Delivery | Postawienie w pierwszym suchym miejscu w domu (przedpokój lub garaż), bez rozpakowywania i montażu. |
| Doorstep Delivery | Zostawienie przed drzwiami zewnętrznymi bez podpisu, dla lżejszych przesyłek. |
| Curbside Delivery | Zostawienie przy krawężniku lub na podjeździe; dalsze wniesienie należy do odbiorcy. |

Jeśli w Twoim wariancie brakuje montażu lub zabrania opakowań, wyjaśnij to ze sklepem sprzedającym usługę.

**Gdy nikogo nie ma w domu.** Ogólne strony MWD nie opisują zasad przekładania nieudanej wizyty, więc skontaktuj się z MWD lub sklepem przed dniem dostawy. [Warunki przewozu](https://sso.gomwd.com/Account/TermsandConditions) stanowią, że w razie odmowy przyjęcia lub niemożności doręczenia z winy nadawcy lub odbiorcy odpowiedzialność MWD ogranicza się do roli magazyniera: po próbie kontaktu telefonicznego lub elektronicznego od następnego dnia roboczego mogą biec opłaty za składowanie; przy braku dyspozycji w 48 godzin wysyłane jest drugie, ostateczne wezwanie, a po kolejnych 10 dniach MWD ma prawo wystawić towar na sprzedaż publiczną. Zobacz też [nieudane doręczenia](guide:missed-delivery).

## Uszkodzona, opóźniona lub zaginiona dostawa MWD

**Uszkodzenia.** Sprawdź każdy element, zanim ekipa odjedzie. [Warunki przewozu](https://sso.gomwd.com/Account/TermsandConditions) na portalu klienta wyznaczają **72 godziny** od doręczenia na pisemną reklamację zaginięcia lub szkody i pozwalają MWD naprawić lub wymienić przedmiot. Bez odmiennej umowy lub opcji *Enhanced Valuation* domyślny limit odpowiedzialności wynosi 500 USD na przesyłkę, a ukryte uszkodzenia towarów spakowanych przez nadawcę są wyłączone, jeśli opakowanie nie nosi widocznych śladów uszkodzeń. Ponieważ klientem MWD jest sklep, powiadom go natychmiast. Więcej w poradniku [uszkodzona paczka](guide:damaged-parcel).

**Opóźnienie.** Dopóki historia kończy się na `Pickup scheduled for`, MWD nie odebrało jeszcze mebla. Zgodnie z [przewodnikiem FTC dotyczącym zasady sprzedaży wysyłkowej, internetowej i telefonicznej](https://www.ftc.gov/business-guidance/resources/business-guide-ftcs-mail-internet-or-telephone-order-merchandise-rule) zamówienie uznaje się za wysłane dopiero po przekazaniu przewoźnikowi: jeśli sprzedawca nie może wysłać w zadeklarowanym terminie — albo w ciągu **30 dni**, gdy go nie podał — musi poprosić o zgodę na opóźnienie i poinformować o prawie do anulowania z pełnym, szybkim zwrotem pieniędzy. Jeśli brak zmian przez kilka dni po odbiorze, sprawdź [śledzenie nie aktualizuje się](guide:tracking-not-updating).

**Brak doręczenia lub zaginięcie.** Te same warunki dają **30 dni**, po upływie rozsądnego czasu na dostawę, na pisemną reklamację niedoręczenia (oraz 6 miesięcy na pozew po pisemnej odmowie). Skontaktuj się ze sklepem i MWD oraz zobacz [doręczona, ale nieodebrana](guide:delivered-not-received) i [zaginiona paczka](guide:lost-parcel).

## Kontakt z Metropolitan Warehouse & Delivery

Według [strony kontaktowej MWD](https://www.gomwd.com/contact-us):

- **Infolinia wsparcia:** (800) 300-7417 — zespół dostępny od poniedziałku do piątku (bez podanych godzin).
- **E-mail ogólny:** `comments@gomwd.com`.
- **Biura:** główna siedziba w Perth Amboy, New Jersey (podana na [About Us](https://www.gomwd.com/about-metropolitan) i [Services](https://www.gomwd.com/services)), oraz biuro *Corporate Office* na stronie kontaktowej: 310 Ramco Street, Suite 150, West Sacramento, CA 95691.

Ścieżka *Info About My Order* jest skierowana do klientów biznesowych (sklepów nadających z MWD); sklep może zgłosić problem do zespołu *Customer Success*.

## Śledzenie zamówienia MWD w Peek

[Peek](/) nie rozpozna MWD po samym układzie cyfr, bo dwanaście cyfr pasuje do wielu przewoźników ([dlaczego formaty się nakładają](guide:tracking-number-formats)). Mimo to wklej numer: Peek śledzi ponad 4500 przewoźników. **W Stanach Zjednoczonych** odpytuje MWD wśród pierwszych przewoźników 12-cyfrowych — obok [BRT](carrier:brt), [Colis Privé](carrier:colis-prive) i [Purolator](carrier:purolator) (dla numerów zaczynających się od 0–6 z poprawną cyfrą kontrolną) — i wyświetla „Metropolitan Warehouse & Delivery ma tę przesyłkę.”, gdy MWD prowadzi zamówienie. **Poza USA** Peek traktuje MWD jako ostateczność: najpierw pyta innych możliwych przewoźników, a do MWD sięga dopiero wtedy, gdy żaden z nich numeru nie zna. Numer wpisany w pole na tej stronie trafia najpierw do MWD, gdziekolwiek jesteś. Możesz też wskazać Metropolitan Warehouse & Delivery przez „Zmień” przy dodawaniu lub „Zmień przewoźnika” na już dodanej przesyłce (wyszukanie „MWD” także go znajdzie). Spacje są akceptowane; wklej same cyfry, bo Peek nie ma reguły dla linków MWD.

Peek odczytuje bezpośrednio tracker MWD: status, historię w angielskim brzmieniu MWD, miasto i stan każdego wpisu na mapie oraz wagę, w kilogramach lub funtach zależnie od ustawień. Każdą godzinę pokazuje w strefie czasowej Twojego urządzenia i podaje tę strefę pod historią. Pomija miasto i kod docelowy, numery referencyjne zamówienia i klienta, numer PRO partnera, liczbę sztuk, objętość i pasek postępu. Nie pokazuje przewidywanej daty dostawy, potwierdzenia doręczenia ani mapy na żywo z ekipą i nie śledzi przewoźników partnerskich.

Peek sprawdza bezpośrednio w MWD nawet co 10 minut, gdy masz otwarte zamówienie lub w dniu dostawy z włączonymi powiadomieniami, oraz co 2 minuty od 6:00 czasu lokalnego w dniu pojawienia się statusu `Arrived at Delivery Location`; w przeciwnym razie co godzinę. Po 12 godzinach w stanie „Zarejestrowana” lub „Przyjęta przez przewoźnika”, albo po dwóch dniach bez nowych wpisów, sprawdza co godzinę nawet przy otwartym ekranie, a po siedmiu dniach nadal w stanie „Zarejestrowana” — co 6 godzin. Przez zewnętrzne usługi śledzenia odpytuje co godzinę, co 3 godziny po dniu ciszy i do co 15 minut w dniu dostawy.

Gdy zamówienie na etapie „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie” przez cztery dni nie ma nowego skanu, Peek oznacza je dniem ostatniego skanu (na przykład „Brak aktualizacji od pon 12 paź”), pokazuje „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika Metropolitan Warehouse & Delivery.” i udostępnia link „Poproś przewoźnika Metropolitan Warehouse & Delivery o pomoc” do strony kontaktowej MWD. Po ponad trzech tygodniach ciszy wyświetla: „Od ponad trzech tygodni nic nowego. Jeśli przesyłka wciąż nie dotarła, zapytaj sprzedawcę. Peek dalej pyta przewoźnika Metropolitan Warehouse & Delivery.”

Możesz włączyć alerty dla opcji „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie” — w MWD ta ostatnia zaczyna się od `Arrived at Delivery Location`. Peek nie umawia ani nie przekłada terminów, nie składa reklamacji i nie kontaktuje się za Ciebie z MWD ani ze sklepem.

## Pytania o śledzenie Metropolitan Warehouse

### Czym jest Metropolitan Warehouse & Delivery?

To rodzinna firma przewozowo-magazynowa z siedzibą w Perth Amboy (New Jersey) działająca od 1989 roku, dysponująca 47 hubami w 48 przyległych stanach. Zlecają ją sklepy, marki meblowe i projektanci — klienci indywidualni nie zamawiają jej bezpośrednio.

### Co oznacza „Delivery Scheduled for WEEK OF”?

MWD zaplanowało dostawę w tygodniu wskazanym tą datą. Dokładny dzień i przedział godzinowy wynikają z umówionego terminu. `Delivery Rescheduled for WEEK OF` oznacza przesunięcie na inny tydzień.

### Czy Metropolitan Warehouse montuje meble?

Tylko w wariancie *Full White Glove*, który obejmuje lekki montaż i zabranie opakowań. Pozostałe poziomy — w tym *White Glove (No Assembly)* — nie obejmują montażu. Swój wariant sprawdzisz w polu *Service Type* na stronie MWD.

### Jak przełożyć dostawę w Metropolitan Warehouse?

Strony MWD opisują samodzielną rezerwację pierwszego terminu, ale nie jego zmianę. Zadzwoń na infolinię (800) 300-7417 od poniedziałku do piątku albo poproś sklep.

### Ile mam czasu na zgłoszenie uszkodzenia do MWD?

Warunki przewozu na portalu klienta wyznaczają 72 godziny po doręczeniu na pisemną reklamację zaginięcia lub uszkodzenia. Ponieważ stroną umowy jest sklep, od razu powiadom również sprzedawcę.

:::sources
- [Metropolitan Warehouse & Delivery: Strona główna](https://www.gomwd.com/) – od 1989 r., infolinia
- [MWD: About Us](https://www.gomwd.com/about-metropolitan) – firma rodzinna, siedziba w Perth Amboy, 48 przyległych stanów
- [MWD: Track a Shipment](https://www.gomwd.com/track-a-shipment) – śledzenie, okno dostawy i potwierdzenie doręczenia
- [MWD: Track Your Order](https://main.gomwd.com/client/wixtrackorder) – pojedyncze 12-cyfrowe pole, komunikat błędu, reCAPTCHA / tekstowe captcha
- [MWD: White Glove Delivery](https://www.gomwd.com/services/white-glove-delivery) – poziomy usług, samodzielne umawianie, przypomnienia i śledzenie na żywo
- [MWD: Services](https://www.gomwd.com/services) – Doorstep Delivery, 47 hubów i terminali przeładunkowych, 5 pełnych hubów 3PL, markowe strony śledzenia
- [MWD: Contact Us](https://www.gomwd.com/contact-us) – infolinia (800) 300-7417 pon.–pt., comments@gomwd.com, Corporate Office West Sacramento, ścieżka dla klientów
- [MWD: Terms & Conditions](https://sso.gomwd.com/Account/TermsandConditions) – reklamacje 72 godz. (szkody) i 30 dni (niedoręczenie), limit 500 USD, opłaty magazynowe
- [FTC: Business Guide to the Mail, Internet, or Telephone Order Merchandise Rule](https://www.ftc.gov/business-guidance/resources/business-guide-ftcs-mail-internet-or-telephone-order-merchandise-rule) – wydanie przewoźnikowi, zasada 30 dni, zgoda na opóźnienie i zwrot pieniędzy
- [Universal Parcel Scraper: Metropolitan Warehouse & Delivery](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/metropolitan-warehouse) – 12 cyfr, komunikat o nieznanym numerze, statusy i dane odczytywane przez Peek
:::
