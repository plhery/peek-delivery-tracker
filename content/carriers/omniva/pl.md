---
title: Omniva śledzenie paczki: kody, statusy, automaty i kontakt
description: Śledź przesyłkę Omniva z Litwy, Łotwy i Estonii do Polski lub w krajach bałtyckich: kody JJEE i EE, statusy po angielsku, automaty paczkowe i kontakt.
slug: omniva-sledzenie
published: 2026-11-14
updated: 2026-11-14
---

Omniva (spółka *AS Eesti Post*) to narodowy operator pocztowy Estonii oraz sieć automatów paczkowych i kurierów działająca w Estonii, na Łotwie i na Litwie. W Polsce z numerem Omniva spotkasz się najczęściej przy zakupach w litewskich, łotewskich lub estońskich sklepach internetowych, na platformach sprzedażowych albo przy odbiorze paczki w jednym z krajów bałtyckich. Przesyłkę sprawdzisz po kodzie śledzenia w serwisie [minu.omniva.ee](https://minu.omniva.ee), w aplikacji Omniva lub wklejając numer w pole powyżej. Kod otrzymasz w e-mailu ze sklepu albo w wiadomości SMS lub e-mail od Omniva.

## Jak wygląda numer śledzenia Omniva

Omniva nazywa ten numer po angielsku *shipment tracking code* lub *shipment code (tracking number)*, a w instrukcji zwrotów podaje przykład `CEXXXXXXXXEE`. Najczęściej spotkasz jeden z poniższych formatów (wszystkie numery są wymyślone):

| Kod (wymyślony) | Co oznacza |
| --- | --- |
| `JJEE00000000000000000` | Paczka Omniva: prefiks `JJEE` i 17 cyfr (łącznie 21 znaków). |
| `JJEEABC00000000000` | Drugi format paczkowy Omniva: `JJEE`, trzy litery i 11 cyfr. |
| `CE000000005EE` | Dwie litery, dziewięć cyfr z cyfrą kontrolną i `EE`: międzynarodowy format pocztowy nadawany przez Omniva. Takie numery z końcówką `EE` noszą też paczki do automatów na Łotwie i Litwie oraz przesyłki pocztowe wysyłane do Polski. |
| `RR000000005CN` | Przesyłka z zagranicy zachowuje numer kraju nadania — tutaj z końcówką `CN` (nadana w Chinach). |

Sześciocyfrowy kod otwarcia skrytki z SMS-a o doręczeniu nie służy do śledzenia paczki — podobnie jak sześciocyfrowy kod zwrotu (jeśli wkleisz 6 cyfr w pole Peek, zobaczysz komunikat „To wygląda na kod odbioru”).

Numery pocztowe zakończone na `LV` należą do łotewskiej poczty Latvijas Pasts, na `LT` do litewskiej Lietuvos paštas, na `PL` do [Poczty Polskiej](carrier:poczta-polska), a kody `JJFI` do fińskiego Posti. Więcej przykładów znajdziesz w poradniku [formaty numerów śledzenia](guide:tracking-number-formats).

## Statusy śledzenia Omniva po angielsku: co oznaczają

Strony śledzenia Omniva (`minu.omniva.ee`, `mana.omniva.lv`, `mano.omniva.lt`) działają po angielsku, estońsku, łotewsku, litewsku i rosyjsku — nie mają polskiej wersji — a aplikacja mobilna wyświetla zdarzenia po angielsku. Kliknięcie „Track & trace details” rozwija pełną oś czasu. Oto typowe komunikaty w kolejności podróży paczki:

| Komunikat Omniva | Co to znaczy dla Ciebie |
| --- | --- |
| `Shipment registered. Shipment has not been handed over to Omniva` | Nadawca wygenerował etykietę, ale nie przekazał jeszcze paczki do Omniva. |
| `Sender has placed the shipment in the parcel machine` | Nadawca włożył paczkę do automatu paczkowego. |
| `The courier has picked up the shipment` | Kurier Omniva odebrał przesyłkę od nadawcy. |
| `Shipment has been posted by the sender or collected by the postal service` | Przesyłka zagraniczna została nadana na poczcie w kraju wysyłki. |
| `Shipment held in customs` | Paczka spoza UE czeka na odprawę celną. |
| `Shipment has arrived at a postal facility for further processing` | Przesyłka dotarła do wskazanego obok terminala lub sortowni Omniva. |
| `The shipment has been handed over to the courier` | Wydana kurierowi do doręczenia. |
| `Shipment is ready for collection from the parcel machine` | Czeka w podanym automacie paczkowym — odbierz ją sześciocyfrowym kodem. |
| `A notification of shipment arrival has been sent` | Omniva wysłała SMS, e-mail lub powiadomienie w aplikacji (paczka nie zmieniła miejsca). |
| `Delivery attempt made` | Kurier próbował doręczyć paczkę, ale doręczenie się nie udało. |
| `Shipment delivered` | Paczka została doręczona lub odebrana ze wskazanego automatu albo urzędu pocztowego. |
| `Shipment is being returned to the sender` | Przesyłka wraca do nadawcy. |

Dwa komunikaty łatwo źle odczytać: `Delivery attempt made` brzmi jak sukces, ale oznacza **nieudaną** próbę doręczenia, natomiast `Shipment delivered` po wcześniejszym zwrocie informuje, że przesyłkę odebrał z powrotem nadawca.

## Doręczenie do Polski oraz automaty i kurierzy w krajach bałtyckich

**Przesyłka z krajów bałtyckich do Polski.** Omniva nie ma własnych automatów paczkowych w Polsce. Międzynarodowe przesyłki pocztowe z końcówką `EE` adresowane do Polski po przekroczeniu sieci wymiany przejmuje [Poczta Polska](carrier:poczta-polska), a ich dalszą drogę w kraju zobaczysz też w eMonitoringu na poczta-polska.pl (lub bezpośrednio w Peek).

Jeśli odbierasz paczkę w Estonii, na Łotwie albo na Litwie, obowiązują zasady sieci Omniva:

- **Powiadomienia.** Omniva wysyła SMS, e-mail lub powiadomienie w aplikacji. Uwaga: w aplikacji Omniva powiadomienia SMS są domyślnie wyłączone — włączysz je w menu *Profile → Notification preferences*.
- **Odbiór z automatu paczkowego.** Na ekranie wskazanego automatu wpisujesz sześciocyfrowy kod z wiadomości. Paczkę może odebrać dowolna osoba znająca kod; dokument tożsamości jest potrzebny tylko wtedy, gdy nadawca wykupił weryfikację odbiorcy.
- **Czas przechowywania.** Automaty paczkowe we wszystkich trzech krajach trzymają przesyłkę przez **4 dni**, a przed końcem terminu Omniva wysyła przypomnienie. W e-serwisie możesz odpłatnie przedłużyć postój maksymalnie o 4 dni naraz, dodając łącznie najwyżej 12 dni. W estońskim urzędzie pocztowym termin można przedłużyć raz, maksymalnie o miesiąc. Nieodebrana paczka wraca na koszt nadawcy.
- **Kurier pod drzwi.** Otrzymasz SMS z datą dostawy, a kurier zadzwoni przed przyjazdem (na Łotwie i Litwie — aby ustalić dzień i porę). Kurierzy wnoszą paczki pod drzwi do 30 kg w Estonii i na Łotwie oraz do 25 kg na Litwie; podczas rozmowy możesz poprosić o inny dzień albo przekierowanie do pobliskiego automatu.
- **Nieudane doręczenie kurierskie.** Jeśli kurier nie zdoła umówić się z Tobą na doręczenie (na Litwie przez 2 dni), paczki do rozmiaru L trafiają na 4 dni do pobliskiego automatu paczkowego. Większe przesyłki w Estonii czekają 7 dni na poczcie (do rozmiaru XL) lub 7 dni w centrum doręczeń (większe niż XL, do 100 kg), na Łotwie 4 dni w centrum doręczeń, a na Litwie wracają do nadawcy.
- **Przekierowanie.** Zmiana miejsca odbioru przez link w e-mailu lub e-serwis jest bezpłatna, dopóki paczka nie dotrze do sortowni; później lub przy zmianie na kuriera obowiązuje opłata. Kurier może też zostawić paczkę w pobliskim automacie, jeśli wskazany był pełny albo miał awarię.

## Opóźniona lub uszkodzona paczka i odprawa celna

**Brak doręczenia lub zastój w śledzeniu.** Sprawdź najpierw, czy minął deklarowany czas przewozu (poniżej). Jeśli tak, Omniva prosi odbiorcę o kontakt ze sprzedawcą, który składa formularz *Request parcel delivery status*. Zajrzyj też do poradnika [dlaczego śledzenie się nie aktualizuje](guide:tracking-not-updating).

**Pusta skrytka lub błędny punkt.** Skontaktuj się bezpośrednio z działem obsługi klientów indywidualnych Omniva we właściwym kraju.

**Uszkodzona przesyłka.** W Estonii formularz reklamacyjny dotyczący uszkodzenia lub zaginięcia może złożyć również odbiorca w ciągu 6 miesięcy od nadania, choć odszkodowanie trafia zazwyczaj do nadawcy. Jeśli przesyłkę z Estonii doręczyła w Polsce Poczta Polska, od razu zgłoś uszkodzenie Poczcie Polskiej, a potem powiadom nadawcę, który zgłasza szkodę w Omniva. Strony pomocy na Łotwie i Litwie kierują odbiorcę do sprzedawcy (na Litwie dołącz zdjęcia paczki i opakowania), przy czym łotewski regulamin usług paczkowych pozwala odbiorcy złożyć pisemną reklamację także samodzielnie w ciągu 6 miesięcy od nadania.

**Odprawa celna.** Estonia, Łotwa, Litwa i Polska należą do Unii Europejskiej, więc paczki między krajami bałtyckimi a Polską **nie przechodzą odprawy celnej**. Cło dotyczy wyłącznie przesyłek spoza UE: w Estonii od 1 lipca 2021 r. każdą paczkę spoza Unii trzeba zgłosić w ciągu 30 dni (samodzielnie w estońskim urzędzie skarbowo-celnym lub upoważniając Omniva). W całej UE od 1 lipca 2026 r. zamówienia spoza Unii o wartości do 150 € podlegają zryczałtowanemu cłu 3 euro za każdy rodzaj produktu, a od 1 listopada 2026 r. dochodzi unijna opłata manipulacyjna 2 euro za każdy rodzaj produktu, bez względu na wartość przesyłki, należna od zgłaszającego. Więcej w poradniku [paczka zatrzymana w urzędzie celnym](guide:customs).

> Fałszywe wiadomości podszywające się pod Omniva proszą o drobną dopłatę do dostawy lub cła przez podstawiony link. Omniva nigdy nie wysyła SMS-ów z zagranicznych numerów, a e-maile wysyła wyłącznie z domen `omniva.ee`, `omniva.lv`, `omniva.lt` oraz `omniva.eu`. Status sprawdzaj bezpośrednio na `minu.omniva.ee` lub w Peek.

## Kontakt z obsługą klienta Omniva

Dane dla klientów indywidualnych zweryfikowane na stronach Omniva 14 listopada 2026 r.:

| Kraj | Telefon | E-mail | Godziny pracy |
| --- | --- | --- | --- |
| Estonia | +372 661 6616 | info@omniva.ee | pon.–pt. 8:00–19:00, sob. 9:00–15:00 |
| Łotwa | +371 27042424 | info@omniva.lv | pon.–pt. 8:00–18:00, sob. 9:00–15:00 |
| Litwa | +370 700 55077 | info@omniva.lt | pon.–pt. 8:00–18:00, sob. 9:00–15:00 |

Na każdej z trzech stron dostępne są formularze zapytania o status przesyłki, zgłoszenia szkody lub zaginięcia oraz przekierowania. Na Litwie opłata za połączenie zależy od taryfy operatora Telia. Na stronach kontaktowych Omniva nie ma czatu na żywo.

## Śledzenie paczek Omniva w aplikacji Peek

[Peek](/) od razu rozpoznaje oba formaty `JJEE` jako przesyłki Omniva, bez konieczności podawania kodu pocztowego. Działają też linki z `minu.omniva.ee`, `mana.omniva.lv` i `mano.omniva.lt` zawierające `/track/` oraz linki `omniva.ee` i `omniva.lt` z parametrem `barcode=`.

Numery pocztowe zakończone na `EE`, zaczynające się od `C`, `R`, `E` lub `L` (z wyjątkiem `CR`, `CV` i `RO`) i mające poprawną cyfrę kontrolną są przypisywane do Omniva natychmiast. Przy innych dwuliterowych prefiksach z końcówką `EE` (na przykład `CR000000005EE`) Peek rozpoznaje format poczty międzynarodowej, odpytuje Omniva razem z Chronopost i zapisuje przesyłkę pod przewoźnikiem, który potwierdzi numer; jeśli paczki jeszcze nie ma w systemie, Peek sprawdza ją dalej. Gdy pomylisz cyfrę (np. `CE000000004EE`), cyfra kontrolna przestaje się zgadzać — Peek nie wskaże żadnego przewoźnika, a Omniva odrzuci taki numer nawet po ręcznym wyborze przez „Zmień”.

Gdy przesyłka pocztowa z końcówką `EE` jedzie do Polski, Peek pyta też [Pocztę Polską](carrier:poczta-polska) o ten sam numer. Dopóki Poczta Polska ma tylko zapis bez własnego skanu, zobaczysz „Zgłoszona przewoźnikowi Poczta Polska, jeszcze nieprzyjęta”, a po jej pierwszym skanie „Doręcza: Poczta Polska”. Gdy ten skan jest co najmniej tak świeży jak najnowszy skan Omniva, Peek śledzi paczkę dalej w Poczcie Polskiej. Gdy doręczenie przejmuje inny przewoźnik, Peek sprawdza paczkę w tempie tego, który ją doręcza. W drugą stronę polską przesyłkę pocztową (`PL`) wysłaną do Estonii Peek potrafi śledzić dalej w Omniva (dla Łotwy i Litwy odpytuje odpowiednio Latvijas Pasts lub Lietuvos paštas).

Peek pokazuje angielskie opisy zdarzeń Omniva, polski status główny, lokalizację każdego skanu na mapie, kraj docelowy, godzinę doręczenia oraz nazwę automatu paczkowego lub urzędu pocztowego, w którym czeka paczka (pomija natomiast dokładne adresy, kody pocztowe i powody nieudanego doręczenia; Omniva nie podaje wagi ani planowanej daty dostawy). Dopóki odpowiada bezpośrednio serwer Omniva, Peek sprawdza status co 10 minut, a w dniu wydania paczki do doręczenia („W doręczeniu”) co 2 minuty od 6:00 czasu lokalnego (przez zewnętrzne usługi śledzenia co 15 minut; po 48 godzinach bez zmian lub 12 godzinach na etapie „Zarejestrowana” / „Przyjęta przez przewoźnika” — co godzinę). Peek pokazuje każdy skan w strefie czasowej Twojego urządzenia; na peektracker.com przypomina o tym linijka pod historią: „Godziny w Twojej strefie czasowej: …”. Powiadomienia możesz ustawić na „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”.

## Najczęstsze pytania o śledzenie Omniva

### Ile idzie paczka Omniva?

W obrębie jednego kraju bałtyckiego Omniva podaje 1–2 dni robocze od przyjęcia przesyłki, a między Estonią, Łotwą i Litwą 2–3 dni robocze (kurierzy doręczają zazwyczaj następnego dnia roboczego, najpóźniej w ciągu 3 dni). Czas drogi przesyłki pocztowej `EE` do Polski zależy od przekazania jej do sieci [Poczty Polskiej](carrier:poczta-polska).

### Czy można śledzić paczkę Omniva po numerze telefonu?

Strony Omniva opisują śledzenie tylko po kodzie przesyłki. Jeśli założysz konto w samoobsłudze na `minu.omniva.ee`, zobaczysz tam wszystkie swoje przyszłe paczki w jednym miejscu.

### Co oznacza status „Delivery attempt made” w Omniva?

Oznacza nieudaną próbę doręczenia przez kuriera, mimo że angielskie sformułowanie brzmi, jakby dostawa doszła do skutku. Sprawdź kolejny wpis lub SMS, aby zobaczyć, czy paczka trafiła do automatu lub placówki.

### Kto doręcza w Polsce paczkę wysłaną przez Omniva?

Przesyłki w międzynarodowym formacie pocztowym z końcówką `EE` przejmuje w Polsce [Poczta Polska](carrier:poczta-polska), używając tego samego numeru nadania.

:::sources
- [Omniva Estonia: Track and receive parcels](https://www.omniva.ee/en/track-and-receive-parcels/) – kod śledzenia, sześciocyfrowy kod skrytki, sposoby odbioru oraz SMS i telefon od kuriera
- [Omniva Latvia: Track and receive](https://www.omniva.lv/en/track-and-receive-parcels/) – odbiór na Łotwie i limit 30 kg dla kuriera
- [Omniva Lithuania: Track and receive](https://www.omniva.lt/en/track-and-receive-parcels/) – odbiór na Litwie i limit 25 kg dla kuriera
- [Omniva: How to track your parcel?](https://www.omniva.ee/help/how-to-track-your-parcel-ee) – powiadomienia z kodem, szczegóły śledzenia i paczki zagraniczne
- [Omniva: Parcel delivery timeframes](https://www.omniva.ee/help/omniva-parcel-delivery-timeframes-ee) – 1–2 dni w kraju, 2–3 dni między krajami bałtyckimi, kurier do 3 dni
- [Omniva Latvia: Omniva sūtījumu piegādes termiņi](https://www.omniva.lv/palidziba/omniva-sutijumu-piegades-termini) – doręczenie kurierskie w ciągu 3 dni na Łotwie
- [Omniva Lithuania: OMNIVA siuntų pristatymo terminai](https://www.omniva.lt/pagalba/omniva-siuntu-pristatymo-terminai-lt) – doręczenie kurierskie następnego dnia roboczego na Litwie
- [Omniva: How long items are stored by Omniva?](https://www.omniva.ee/help/how-long-items-are-stored-by-omniva-ee) – 4 dni w automatach paczkowych, przechowanie paczek kurierskich w Estonii i zwrot na koszt nadawcy
- [Omniva Latvia: Cik ilgi Omniva uzglabā sūtījumus?](https://www.omniva.lv/palidziba/cik-ilgi-omniva-uzglaba-sutijumus) – przypomnienie przed upływem terminu odbioru i przechowanie paczek kurierskich na Łotwie
- [Omniva Lithuania: How long items are stored by Omniva?](https://www.omniva.lt/help/how-long-items-are-stored-by-omniva-lt) – przechowanie paczek kurierskich na Litwie i zwrot paczek XL
- [Omniva: Extend parcel storage time](https://www.omniva.ee/help/extend-parcel-storage-time-ee) – płatne przedłużenie maksymalnie o 4 dni naraz i 12 dni łącznie, jednorazowo do miesiąca na poczcie w Estonii
- [Omniva: Will I get notified about a delivery?](https://www.omniva.ee/help/will-i-get-notified-about-a-delivery-ee) – powiadomienia i kontakt telefoniczny kuriera
- [Omniva: Issue with the delivery SMS or email notification](https://www.omniva.ee/help/i-have-an-issue-with-the-delivery-smsemail-notification) – domyślnie wyłączone SMS-y w aplikacji Omniva
- [Omniva: Will the courier deliver parcels to my door?](https://www.omniva.ee/help/will-the-courier-deliver-parcels-to-my-door-ee) – limity wagowe doręczeń pod drzwi
- [Omniva: Not at the address when the courier delivers](https://www.omniva.ee/help/what-happens-if-im-not-at-the-delivery-address-when-the-courier-delivers-the-parcel-ee) – telefon kuriera, inny dzień doręczenia albo pobliski automat lub urząd pocztowy
- [Omniva: Can someone else pick up my parcel?](https://www.omniva.ee/help/can-someone-else-pick-up-my-parcel-ee) – odbiór przez inną osobę z kodem skrytki
- [Omniva: Do I need to show ID?](https://www.omniva.ee/help/do-i-need-to-show-id-when-receiving-items-via-omniva-ee) – kiedy wymagany jest dokument tożsamości
- [Omniva: Redirect a parcel or letter](https://www.omniva.ee/help/redirect-a-parcel-or-letter-ee) – bezpłatne i płatne przekierowanie paczki
- [Omniva: My parcel hasn't been delivered](https://www.omniva.ee/help/my-parcel-hasn%E2%80%99t-been-delivered-ee) – zgłoszenie opóźnienia przez nadawcę
- [Omniva: My parcel is damaged](https://www.omniva.ee/help/my-parcel-is-damaged-ee) – 6 miesięcy na zgłoszenie szkody w Estonii, zgłoszenie u poczty doręczającej za granicą, zasady na Łotwie i Litwie
- [Omniva Latvia: Sūtījumu pakalpojumu standarta noteikumi privātajiem klientiem (PDF)](https://www.omniva.lv/wp-content/uploads/sites/6/2026/06/LV_Omniva-Standard-Terms-and-Conditions-of-Parcel-Services-for-Private-Customers_2026_07_22.pdf) – prawo nadawcy lub odbiorcy do pisemnej reklamacji w ciągu 6 miesięcy
- [Omniva: The parcel machine locker is empty](https://www.omniva.ee/help/the-parcel-machine-locker-is-empty-or-a-shipment-is-missing) – kontakt ze wsparciem w razie pustej skrytki
- [Omniva: How to return a parcel?](https://www.omniva.ee/help/manage-shipments-general/return-a-parcel-containing-items-purchased-in-estonia-latvia-or-lithuania-ee) – przykładowy format `CEXXXXXXXXEE` i sześciocyfrowy kod zwrotu
- [Omniva Estonia: Customs: receiving](https://www.omniva.ee/en/customs-receiving/) – 30 dni na zgłoszenie celne przesyłki spoza UE w Estonii
- [Omniva: Which shipments require a customs declaration](https://www.omniva.ee/help/which-shipments-require-a-customs-declaration) – obowiązek zgłoszenia każdej paczki spoza UE od 1 lipca 2021 r.
- [Komisja Europejska: reforma celna UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – cło 3 € do 150 € od 1 lipca 2026 r. oraz opłata manipulacyjna 2 € od 1 listopada 2026 r.
- [Komisja Europejska: wytyczne dotyczące cła 3 € i unijnej opłaty manipulacyjnej (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – zasady naliczania opłaty 2 € za kategorię towaru
- [Poczta Polska: Śledzenie przesyłek (eMonitoring)](https://www.poczta-polska.pl/sledzenie-przesylek/) – śledzenie przesyłek zagranicznych przejętych przez Pocztę Polską
- [Omniva Estonia: Contacts for private customers](https://www.omniva.ee/en/contacts-for-private-customers/) – telefon, e-mail, godziny pracy i nazwa spółki AS Eesti Post
- [Omniva Latvia: Contacts for private customers](https://www.omniva.lv/en/contacts-for-private-customers/) – dane kontaktowe na Łotwie
- [Omniva Lithuania: Contacts for private customers](https://www.omniva.lt/en/contacts-for-private-customers/) – dane kontaktowe na Litwie i informacja o taryfie Telia
- [Omniva: How to distinguish scams from genuine parcel notifications](https://www.omniva.ee/en/frauds/) – oficjalne domeny i ostrzeżenie przed fałszywymi SMS-ami
- [Omniva: Tracker interface strings](https://minu.omniva.ee/locales/common/common_en.json) – wersje językowe wyszukiwarki `minu.omniva.ee`
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – rozpoznawanie kodów Omniva, przekazanie do Poczty Polskiej i harmonogram odświeżania w Peek
:::
