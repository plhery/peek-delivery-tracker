---
title: Śledzenie DPD Austria: statusy, punkty Pickup i kontakt
description: Śledź paczkę DPD w Austrii: 14-cyfrowy numer i kod PIN, statusy myDPD, terminy odbioru w Pickup, cło i kontakt z DPD Austria.
slug: sledzenie-dpd-austria
published: 2026-11-23
updated: 2026-11-23
---

DPD Austria to prywatna firma kurierska z siedzibą w Leopoldsdorf. Na terenie Austrii doręcza paczki zazwyczaj w jeden dzień roboczy od przekazania przesyłki przez sklep. Paczkę sprawdzisz w [myDPD](https://www.mydpd.at/) – na stronie i w aplikacji DPD Austria – wpisując 14-cyfrowy numer z potwierdzenia wysyłki ze sklepu albo wklejając go w pole powyżej.

## Numer paczki DPD Austria i kod PIN

Numer paczki DPD składa się z 14 cyfr. Przesyła go sklep lub DPD e-mailem, jeśli sklep przekazał twój adres; na koncie myDPD znajdziesz go w zakładce „My parcels”. PIN (*Parcel Information Number*) widnieje na czerwono-białym awizie zostawionym pod twoją nieobecność. myDPD przyjmuje oba kody, natomiast Peek wymaga numeru paczki. Formaty rozpoznawane przez Peek:

| Numer (wymyślony) | Co oznacza |
| --- | --- |
| `06204819273645` | 14 cyfr zaczynających się od 0620–0630, 0632–0635, 0643–0645 lub 0649: numer paczki z jednego z oddziałów DPD Austria. |
| `01904819273645` | 14 cyfr zaczynających się od 0101–0199: Peek traktuje go najpierw jako numer [DPD Germany](carrier:dpd-de). DPD Austria może go mimo to doręczyć (zobacz niżej). |

Jeśli na etykiecie po 14 cyfrach widnieje dodatkowa litera lub cyfra, pomiń ją. Gdy w e-mailu ze sklepu wymieniono [Austrian Post](carrier:austrian-post), GLS Austria lub innego przewoźnika, numer należy do tamtej firmy. Brak numeru? Zobacz [jak znaleźć numer śledzenia](guide:find-tracking-number).

## Znaczenie statusów śledzenia DPD Austria

myDPD pokazuje każdy skan z nazwą oddziału po niemiecku lub angielsku. W kolejności, w jakiej zwykle pojawiają się dla paczki (*Paketstation* to automat paczkowy):

| myDPD pokazuje | Co to oznacza dla ciebie |
| --- | --- |
| `Auftragsdaten übermittelt` | Nadawca przesłał do DPD dane przesyłki, ale nie samą paczkę. Angielskie FAQ DPD nazywa to „Order data transmitted to DPD”. |
| `Paket unterwegs` | DPD ma paczkę: odebrana w pierwszym oddziale lub zeskanowana w sortowni po drodze. |
| `Im Paketzustellzentrum` | W oddziale doręczającym. Następny krok to doręczenie. |
| `Unfortunately, parcel was loaded incorrectly and will be delivered soon.` | Załadowano do niewłaściwego pojazdu. Kolejny oddział zeskanuje ją przed doręczeniem. |
| `In Zustellung` | U kuriera, w drodze do ciebie. |
| `Empfänger leider nicht angetroffen.`, `Unfortunately, consignee not present. Notification was left.` | Nikogo nie zastano. Kurier zostawił awizo. |
| `Unfortunately, parcel was not delivered.` | Nie doręczono w tej turze. Wraca do oddziału przed kolejną próbą. |
| `In Zustellung an Pickup Paketstation`, `In delivery to Pickup parcel shop` | Po nieudanej próbie kurier wiezie paczkę do automatu lub punktu Pickup. |
| `Paket wurde in der Pickup Paketstation hinterlegt`, `Parcel was handed over to Pickup parcel shop`, `Takeover by Pickup parcel shop` | Czeka w automacie lub punkcie Pickup. DPD wysyła kod odbioru e-mailem lub SMS-em (dla automatu także adres). |
| `Paket durch Empfänger abgeholt` | Odebrana z punktu Pickup. |
| `Paket wurde erfolgreich abgestellt` | Zostawiona w miejscu wskazanym zgodą na pozostawienie (*deposit okay*), np. w garażu. |
| `Parcel was not picked up` | Nie odebrano jej w terminie z punktu Pickup. Zwrot do nadawcy zwykle rusza w tej samej minucie. |
| `Parcel will be returned to the sender.` | Paczka rozpoczyna drogę powrotną do nadawcy. |
| `Parcel was picked up by driver from Pickup parcel shop` | Kurier zabrał ją z punktu Pickup: na początku to nadanie przez sprzedawcę, a po okresie oczekiwania – zwrot do nadawcy. |

## Doręczenia DPD w Austrii: Predict, zmiany i punkty Pickup

**Predict.** Jeśli sklep przekazał DPD twój e-mail lub numer komórkowy, DPD zapowiada dostawę z wyprzedzeniem. W dniu doręczenia Live-Tracking wyświetla okno czasowe i mapę odświeżaną co 15 minut. Przedział nie jest gwarantowany – mogą go zmienić ruch lub pogoda. Dla paczek z zagranicy kieruj się terminem sklepu.

**Zmiana doręczenia.** Zarejestruj się w myDPD, by zmienić doręczenie jeszcze tuż przed przyjazdem kuriera, używając numeru paczki lub PIN-u: inny dzień lub adres, wskazany sąsiad, punkt albo automat Pickup lub zgoda na pozostawienie w wybranym miejscu (np. na tarasie). Z kontem ustawisz też ulubiony punkt Pickup lub stałą zgodę na pozostawianie wszystkich paczek.

**Gdy nikogo nie ma.** DPD może przekazać paczkę każdej osobie pod adresem domowym lub firmowym, która ją odbierze, bez sprawdzania dowodu. Po pierwszej nieudanej próbie kurier może zostawić przesyłkę u sąsiada albo w najbliższym punkcie lub automacie Pickup i podać jego nazwę oraz adres. Informację znajdziesz na awizie i w myDPD. Zobacz [nieudane doręczenie](guide:missed-delivery).

**Odbiór.**
- Punkt Pickup: 7 dni kalendarzowych, z przypomnieniami po 3 i 5 dniach. Pokaż kod odbioru z e-maila lub SMS-a od DPD albo dowód tożsamości i złóż podpis. DPD nie może ponownie wysłać kodu, a myDPD go nie pokazuje. Sklepy mają własne godziny otwarcia.
- Automat Pickup (*Paketstation*): 5 dni kalendarzowych, z przypomnieniem po 3 dniach. Wymagany jest kod z e-maila lub SMS-a. Automaty działają całą dobę.

Po tym terminie paczka wraca do nadawcy – poproś sklep o ponowną wysyłkę. Zobacz [nieodebrane paczki](guide:uncollected-parcel) oraz [zwrot do nadawcy](guide:returned-to-sender).

## Paczka DPD opóźniona, zaginiona, uszkodzona lub na cle

O zwrot pieniędzy lub nową wysyłkę zwróć się do sklepu, bo to on ma umowę z DPD.

**Brak aktualizacji.** Jeśli `Auftragsdaten übermittelt` utrzymuje się przez dni, sklep jeszcze nie przekazał paczki – DPD radzi zapytać nadawcę. Gdy Live-Tracking nie zmienia się przez kilka godzin w dniu doręczenia, skontaktuj się z DPD, podając numer paczki. Zobacz [dlaczego śledzenie się nie aktualizuje](guide:tracking-not-updating).

**Doręczono, ale brak paczki.** Zgodnie z zaleceniem DPD sprawdź najpierw wyznaczone miejsce pozostawienia i sąsiada wpisanego na awizie. Nadal nic? Użyj formularza DPD dla odbiorców („Where was my parcel delivered/handed over.”) i powiadom sklep. Więcej w [doręczono, ale nie odebrano](guide:delivered-not-received).

**Zaginiona.** Jeśli paczki nie udaje się odnaleźć, skontaktuj się z obsługą klienta DPD i ze sklepem. Zobacz [zaginione paczki](guide:lost-parcel).

**Uszkodzona.** DPD prowadzi postępowanie szkodowe wyłącznie na wniosek nadawcy, więc zgłoś sprawę sklepowi – sklep reklamuje w DPD i zgodnie ze swoimi warunkami zwraca pieniądze lub wysyła nowy towar. Warunki DPD wymagają pisemnego zgłoszenia uszkodzeń ukrytych najpóźniej w ciągu sześciu dni kalendarzowych, a widocznych niezwłocznie po doręczeniu. Zobacz [uszkodzone paczki](guide:damaged-parcel).

**Cło.** Towary kupione online spoza UE podlegają od 1 lipca 2026 r. unijnemu cłu 3 € za pozycję w przesyłkach do 150 €, a od 1 listopada 2026 r. unijnej opłacie manipulacyjnej 2 € za pozycję niezależnie od wartości. Jedna pozycja to towary o tej samej klasyfikacji taryfowej, opisie i – gdy wymagane – pochodzeniu. Strony DPD Austria nie precyzują, czy i jak te opłaty są przenoszone na odbiorcę: zapytaj sklep, czy były wliczone w cenę. Zobacz [paczki zatrzymane na cle](guide:customs).

## Kontakt i obsługa klienta DPD Austria

Peek nie skontaktuje się z DPD ani ze sklepem w twoim imieniu. Na stronach DPD Austria:

- **Telefon:** 05 03 73 420 (+43 5 03 73 420 z zagranicy), infolinia dla klientów indywidualnych, od poniedziałku do piątku 7:00–18:00 (czasu austriackiego).
- **Online:** [formularz kontaktowy DPD Austria dla odbiorców](https://www.dpd.com/at/en/support/contact-form-consignee/) z numerem paczki lub PIN-em, imieniem, nazwiskiem i e-mailem. DPD prosi o korzystanie z jednego kanału naraz, bo równoległe zgłoszenia spowalniają obsługę.
- **Poczta:** DPD Direct Parcel Distribution Austria GmbH, Arbeitergasse 46, 2333 Leopoldsdorf.

> W obiegu są fałszywe e-maile i SMS-y podszywające się pod DPD. DPD zaleca: nie płać, nie otwieraj załączników, nie klikaj linków logowania i usuń wiadomość. W razie straty pieniędzy DPD radzi zgłosić sprawę policji. Zobacz [fałszywe SMS-y o przesyłkach](guide:delivery-scam-texts).

## Śledzenie paczki DPD Austria w Peek

Wklej 14-cyfrowy numer paczki w [Peek](/). Kod pocztowy nie jest potrzebny.

Jeśli numer mieści się w zakresach oddziałów DPD Austria, Peek pyta DPD Austria jako jednego z pierwszych przewoźników niezależnie od twojej lokalizacji i wyświetla „DPD Austria ma tę przesyłkę.”, gdy myDPD ją rozpozna z ostatnim skanem w Austrii. Jeśli DPD jeszcze jej nie ma, zobaczysz „jeszcze nie znaleziono · Peek sprawdza dalej”. Dodaj ją mimo to: karta pokaże „Prawdopodobnie DPD Austria” i „Oczekiwanie na pierwszy skan przewoźnika DPD Austria”, a Peek będzie sprawdzał dalej.

Inne numery 14-cyfrowe nie wskazują od razu na DPD Austria, bo pasują do wielu spółek DPD i innych przewoźników. Jeśli twoją paczkę ma DPD Austria, naciśnij „Zmień” i wybierz DPD Austria (znajdziesz też jako „DPD Österreich” lub „DPD AT”) albo użyj „Zmień przewoźnika” na dodanej paczce. Link myDPD (`?p=` lub `/meine-pakete?ipn=`) bądź dpdgroup.com/at/ z `parcelNumber=` od razu przypisuje DPD Austria.

**Od innej spółki DPD.** Dla paczki wysłanej przez [DPD Netherlands](carrier:dpd-nl), DPD Belgium lub DPD Hungary, gdy DPD zarejestruje ją w austriackim oddziale doręczającym lub dalej, Peek pyta DPD Austria pod tym samym numerem i przełącza śledzenie, gdy myDPD pokaże co najmniej równie świeży skan. Do tego czasu karta już wskazuje DPD Austria jako doręczyciela. Paczka z [DPD Germany](carrier:dpd-de) pozostaje pod DPD Germany: po wjeździe do Austrii Peek czyta też myDPD i pokazuje jego skany, jeśli są pełniejsze.

**Co widzisz.** Status, historię w słowach myDPD wraz z miastem i krajem każdego oddziału oraz godzinę doręczenia w czasie austriackim (skan z kraju w innej strefie zachowuje swój czas lokalny). Paczka w punkcie Pickup ma status „Do odbioru”. Peek pomija imiona, adresy, okno czasowe, nazwę punktu Pickup, dane podpisującego oraz opcje doręczenia i nie podaje planowanej daty; myDPD nie udostępnia wagi. Punkt Pickup sprawdzisz w myDPD lub na awizie, a kod odbioru w e-mailu lub SMS-ie DPD.

Peek sprawdza co 10 minut, a od 6:00 czasu lokalnego w dniu skanu wydania do doręczenia nawet co 2 minuty, dopóki odpowiada system DPD (przez usługi śledzenia co godzinę, a w dniu doręczenia do co 15 minut przy otwartej paczce lub włączonych alertach). Powiadomienia ustawisz według preferencji: „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Po dwóch dniach bez nowości (po 12 godzinach przy „Zarejestrowana” lub „Przyjęta przez przewoźnika”) sprawdza co godzinę. Aby zmienić doręczenie, użyj myDPD.

## Pytania o śledzenie DPD w Austrii

### Ile trwa doręczenie DPD w Austrii?

Zazwyczaj jeden dzień roboczy na terenie Austrii od momentu przekazania paczki przez sklep. Wcześniej myDPD może pokazywać `Auftragsdaten übermittelt`.

### Jak długo DPD przechowuje paczkę w punkcie lub automacie Pickup?

7 dni kalendarzowych w punkcie Pickup i 5 dni w automacie Pickup (*Paketstation*). Potem wraca do nadawcy.

### Czym jest kod PIN DPD?

To *Parcel Information Number* z awiza zostawianego przez DPD pod twoją nieobecność. myDPD go przyjmuje, a w Peek użyj 14-cyfrowego numeru paczki.

### Co oznacza Auftragsdaten übermittelt?

Sklep przesłał do DPD dane przesyłki, ale DPD nie ma jeszcze paczki. Jeśli status utrzymuje się przez dni, zapytaj sklep.

### Jaki jest numer telefonu do DPD Austria?

05 03 73 420 dla klientów indywidualnych, od poniedziałku do piątku w godzinach 7:00–18:00 (czasu austriackiego).

:::sources
- [DPD Austria: Receiving parcels](https://www.dpd.com/at/en/receiving-parcels/) – Predict, Live-Tracking, zmiany, zgoda na pozostawienie, automaty
- [DPD Austria: myDPD](https://www.mydpd.at/) – strona i aplikacja śledzenia
- [DPD Austria: FAQ receiving parcels](https://www.dpd.com/at/en/support/faq/faq-receiving-parcels/) – numer paczki, PIN, jeden dzień roboczy, zmiany
- [DPD Austria: FAQ Pickup](https://www.dpd.com/at/en/support/faq/pickup/) – terminy odbioru, kod, dowód tożsamości
- [DPD Austria: General terms and conditions](https://www.dpd.com/at/en/gtc/) – przechowywanie, przypomnienia, zwrot
- [DPD Austria: General terms and conditions, national (PDF)](https://www.dpd.com/wp-content/uploads/sites/224/2022/01/AGB0447_AGBs_DPDA_national_en.pdf) – sąsiad lub Pickup po pierwszej próbie, Predict, okno, zgłoszenie szkody
- [DPD Austria: What does the status "Order data transmitted to DPD" mean?](https://www.dpd.com/at/en/faq/was-bedeuter-der-status-auftragsdaten-an-dpd-uebermittelt/) – kontakt z nadawcą
- [DPD Austria: Status not changing in live tracking](https://www.dpd.com/at/en/faq/der-paketstatus-meines-pakets-hat-sich-schon-lange-nicht-mehr-veraendert-woran-kann-das-liegen/) – brak zmian przez kilka godzin
- [DPD Austria: Parcel shown as delivered but not delivered](https://www.dpd.com/at/en/faq/ein-paket-das-mir-als-zugestellt-angezeigt-wird-ist-noch-nicht-geliefert-worden-woran-liegt-das/) – co sprawdzić najpierw
- [DPD Austria: My parcel can't be located](https://www.dpd.com/at/en/faq/mein-paket-ist-unauffindbar-was-soll-ich-tun/) – obsługa klienta
- [DPD Austria: Parcel arrives damaged](https://www.dpd.com/at/en/faq/was-ist-zu-tun-wenn-ein-paket-beschadigt-beim-empfanger-ankommt/) – najpierw nadawca
- [DPD Austria: Contact](https://www.dpd.com/at/en/support/contact/) – telefon, godziny
- [DPD Austria: Contact form for consignees](https://www.dpd.com/at/en/support/contact-form-consignee/) – jeden kanał naraz
- [DPD Austria: Security](https://www.dpd.com/at/en/security/) – fałszywe e-maile i SMS-y
- [DPD Austria: Legal notice](https://www.dpd.com/at/en/legal-notice/) – firma, adres, numer z zagranicy
- [Komisja Europejska: reforma celna UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – cło 3 € od 1 lipca 2026 r., opłata 2 € od 1 listopada 2026 r.
- [Komisja Europejska: wytyczne dotyczące cła 3 € i unijnej opłaty manipulacyjnej (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – definicja pozycji
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – co czyta Peek, treści myDPD, zakresy oddziałów, przekazania
:::
