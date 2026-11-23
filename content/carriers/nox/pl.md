---
title: Śledzenie nox NachtExpress: statusy, miejsce dostawy
description: Śledź przesyłkę nox NachtExpress: jak wygląda kod kreskowy, co oznaczają niemieckie statusy, dostawa nocna do ustalonego miejsca, terminy i kontakt.
slug: sledzenie-nox
published: 2026-11-23
updated: 2026-11-23
---

nox (nox NachtExpress) to niemiecka firma nocnego ekspresu, nox Germany GmbH z siedzibą w Langenfeld. Wozi części zamienne i inne pilne towary dla firm. Odbiera je po południu i dostarcza w nocy, zazwyczaj przed 8:00 (czasu niemieckiego), w miejsce ustalone z odbiorcą: do skrzyni, garażu, warsztatu lub bagażnika samochodu serwisowego. Przesyłkę sprawdzisz po kodzie kreskowym w [systemie śledzenia nox](https://trackandtrace.app.nox.express/scannungen) albo po wklejeniu numeru w pole powyżej. System działa po niemiecku — poniższe tabele go wyjaśniają.

## Kod kreskowy nox: jak wygląda i gdzie go znaleźć

nox nazywa swój numer śledzenia kodem kreskowym (barcode). Każda paczka musi mieć etykietę nox, którą nadawca tworzy przy zlecaniu transportu, więc nadawca również ma kod kreskowy. Brak numeru? Zapytaj nadawcę lub sprawdź [jak znaleźć numer śledzenia](guide:find-tracking-number). Formaty znane w kodzie Peek:

| Kod kreskowy (zmyślony) | Co to jest |
| --- | --- |
| `220004829173640715482917` | 24 cyfry zaczynające się od 22000: kod klienta, 000, numer klienta nadawcy, data wydruku etykiety i numer seryjny. Katalog Peek rozpoznaje ten format. 24 cyfr używają też [Ciblex](carrier:ciblex), [InPost](carrier:inpost), [bpost](carrier:bpost) i [Poczta Austriacka](carrier:austrian-post). |
| `SWR482917364058291736405` | SWR i 21 cyfr: starszy kod kreskowy nox. |
| `RA1482917364058482917364` | RA, potem 1 lub 2 (rodzaj odbioru), 12 liter lub cyfr dla klientów i 9-cyfrowy numer seryjny: kod kreskowy zwrotu. |
| `C48291736405829173640582917364` | C i 29 cyfr: własny kod kreskowy nadawcy, na który system nox również odpowiada. |
| `trackandtrace.app.nox.express/scannungen/SWR482917364058291736405` | Link do przesyłki w systemie nox. Wklej cały link do Peek. |

Inne kody kreskowe nox mają 24 cyfry z innym dwucyfrowym kodem klienta przed 000. W Peek spacje nie mają znaczenia, a małe litery działają bez problemu.

## Statusy śledzenia nox wyjaśnione

System nox pokazuje dla każdego skanu pola `Status`, `Scanort` (miejsce), `Bemerkung` (uwaga), `Datum` (data) i `Uhrzeit` (godzina). W słownictwie nox słowo Collo oznacza jedną paczkę w przesyłce, a Niederlassung oddział nox.

| nox podaje | Co to oznacza dla ciebie | Status w Peek |
| --- | --- | --- |
| `Collo durch Versender bereitgestellt zur Abholung` | Nadawca przygotował paczkę do odbioru przez nox. nox jeszcze jej nie odebrało. | „Zarejestrowana” |
| `Collo in der nox Niederlassung eingetroffen` | Paczka dotarła do oddziału nox. | „W transporcie” |
| `Collo von der nox Niederlassung weiterverladen` | Oddział załadował ją w dalszą trasę do kolejnego oddziału. | „W transporcie” |
| `Collo steht am Lager in nox Niederlassung, Zustellung nicht im geplanten Zeitfenster` | Stoi w magazynie oddziału i nie została doręczona w planowanym oknie. Wyjedzie kolejnej nocy. | „W transporcie” |
| `Collo in nox Zustellfahrzeug geladen` | Załadowana do pojazdu doręczającego. | „W doręczeniu” |
| `Collo von nox zugestellt` | Doręczona: zwykle zostawiona w ustalonym miejscu dostawy, bez podpisu. | „Dostarczona” |

Jeśli system wyświetla `Der von Ihnen angegebene Barcode ist uns nicht bekannt.`, nox nie zna tego kodu kreskowego: sprawdź go u nadawcy. Aby zobaczyć więcej szczegółów lub złożyć reklamację, system prosi o potwierdzenie kodu pocztowego odbiorcy przez `PLZ validieren/Reklamation erfassen`.

## Jak doręcza nox: nocą, do ustalonego miejsca

**Kiedy.** nox odbiera zwykle od nadawcy między 14:00 a 18:00 (czasu niemieckiego). Zgodnie z regulaminem NachtExpress doręcza następnego dnia, od wtorku do soboty łącznie ze świętami, z reguły do 8:00 (czasu niemieckiego). Nadawca może zamówić dostawę przed 7:00 lub przed 6:00 (czasu niemieckiego). nox oferuje też dostawy w weekendy i święta jako osobną usługę (WochenendService). Dla paczek powyżej 100 kg lub dłuższych niż 3,20 m, szerszych niż 1,20 m albo wyższych niż 1,50 m godzina dostawy może się różnić. Przesyłki na wyspy Morza Północnego i Bałtyku nie mają gwarantowanej godziny.

**Gdzie.** Ponieważ nox doręcza, gdy wielu klientów jeszcze śpi, zostawia przesyłkę w miejscu uzgodnionym z wyprzedzeniem, zazwyczaj bez podpisu. Przykłady podawane przez nox:

- osobna skrzynia na terenie firmy;
- warsztat, myjnia, pomieszczenie socjalne lub garaż;
- bagażnik pojazdu technika serwisowego;
- portier, który odbiera przesyłkę osobiście od kierowcy;
- jedna ze stacji serwisowych nox z dostępem całodobowym, na przykład czynna całą dobę stacja paliw.

**Ustalenie miejsca dostawy.** Zarejestruj je lub zmień w dowolnym momencie, dodając zdjęcia i opis w narzędziu online [ready2nox](https://ready2nox.de/). Według regulaminu nox to jedyny sposób jego wyznaczenia, a miejsce obowiązuje aż do jego zmiany w tym narzędziu. Z chwilą pozostawienia paczki w tym miejscu uznaje się ją za doręczoną.

**Brak ustalonego miejsca.** Zgodnie z regulaminem nox może wtedy zostawić przesyłkę pod twoim adresem. Jeśli ryzyko utraty jest wyraźnie zbyt wysokie, nox może jej nie doręczyć i odesłać nadawcy.

**Odbiór w oddziale.** Jako usługę dodatkową możesz wybrać odbiór przesyłek w oddziale nox, gdzie firma przechowuje je dla ciebie.

**Dowód.** Gdy nikogo nie ma, nox fotografuje paczkę oraz miejsce jej pozostawienia i przechowuje zdjęcia jako dowód. nox może udostępnić zdjęcie w konkretnym przypadku osobie wykazującej uzasadniony interes, lecz nie istnieje ustawowe prawo do jego otrzymania.

**Poza Niemcami.** nox działa w 20 innych krajach Europy poprzez spółki siostrzane i partnerów. Wysyłka za granicę wymaga wcześniejszej umowy nadawcy z nox, a zasady reklamacji mogą różnić się w zależności od kraju.

## Przesyłka nox opóźniona, uszkodzona lub zaginiona

W sprawie ponownej wysyłki skontaktuj się z firmą, która nadała towar.

**Brak ruchu.** Jeśli `Collo durch Versender bereitgestellt zur Abholung` pozostaje jedynym skanem, nox jeszcze nie odebrało paczki — zapytaj nadawcę. Jeśli czeka w oddziale, wyruszy kolejnej nocy (zobacz tabelę wyżej). W razie opóźnienia użyj systemu śledzenia nox: potwierdź kod pocztowy i wybierz `Laufzeitverzug` (opóźnienie) lub `Sonstige Anliegen` (inne sprawy). Zobacz też [dlaczego śledzenie przestaje się aktualizować](guide:tracking-not-updating).

**Doręczona, ale jej nie ma.** Najpierw sprawdź ustalone miejsce dostawy, a potem inne punkty używane w firmie. Zapytaj portiera lub technika, do którego samochodu mogła trafić. Następnie szybko powiadom nox przed upływem poniższego terminu. Więcej w artykule [doręczona, ale nieotrzymana](guide:delivered-not-received).

**Uszkodzona.** W NachtExpress każda reklamacja i zgłoszenie szkody musi trafić do nox do 12:00 (czasu niemieckiego) w dniu doręczenia, na piśmie z opisem. Jeśli ten dzień wypada w sobotę, niedzielę lub święto, termin mija o 12:00 (czasu niemieckiego) następnego dnia roboczego. Ogólne zastrzeżenie nie wystarczy, a po terminie regulamin wyłącza odpowiedzialność nox. Zachowaj towar w oryginalnym opakowaniu do oględzin i odbioru przez nox — inaczej nox nie odpowiada. Zgłoś to w systemie śledzenia po weryfikacji kodu pocztowego (`Reklamation einer Beschädigung`), przez formularz kontaktowy albo w portalu klienta. Zobacz [uszkodzone paczki](guide:damaged-parcel).

**Cło.** Strony nox wspominają o odprawie celnej jedynie jako o usłudze dla klientów w imporcie i eksporcie. Zapytaj nadawcę i zobacz [paczki zatrzymane na cle](guide:customs).

## Kontakt i obsługa klienta nox

Peek nie może skontaktować się w twoim imieniu z nox ani z nadawcą. Dane ze stron nox:

- **Obsługa klienta:** +49 (0) 4224 920 000, od poniedziałku do piątku 7:00–17:00 (czasu niemieckiego), lub customerservice@nox-nachtexpress.de, podane w systemie śledzenia nox.
- **Formularz kontaktowy:** [formularz kontaktowy nox](https://www.nox.eu/de/de/kontakt/kontaktformular/), do pytań i reklamacji.
- **Reklamacje:** w systemie śledzenia po sprawdzeniu kodu pocztowego. Formularze (opóźnienie, uszkodzenie, inne) pytają, czy jesteś odbiorcą (Empfänger), nadawcą (Versender) czy zleceniodawcą trzecim.
- **Firma:** nox Germany GmbH, Katzbergstr. 3, 40764 Langenfeld, Niemcy; welcome@nox.eu.

Aby śledzić wszystkie swoje przesyłki, firma potrzebuje konta w portalu klienta nox — poproś o nie przez formularz kontaktowy.

## Śledzenie przesyłki nox w Peek

Peek nie rozpozna nox po samym numerze: kody kreskowe `22000` mają tę samą długość co Ciblex, InPost, bpost i Poczta Austriacka, a pozostałych formatów Peek nie przypisuje do nox po kształcie. Wklej numer mimo to: Peek obsługuje ponad 4500 przewoźników. Podczas dodawania kodu `22000` Peek pyta przewoźników używających tego formatu, najpierw z twojego kraju („Sprawdzanie usług śledzenia…”), i pokazuje „nox ma tę przesyłkę.”, gdy system nox ją zna. Jeśli zna ją kilku, wybierasz sam.

Jeśli żaden jeszcze nie odpowiada, przesyłka wyświetla „Brak historii śledzenia”, a Peek sprawdza dalej, lub „Oczekiwanie na przewoźnika nox”, gdy wybierzesz nox. Aby wybrać ręcznie, kliknij „Zmień” i wybierz nox: przy kodzie `22000` znajduje się w sekcji „Pasują do numeru”; przy kodzie SWR, RA lub C wyszukaj go (znajdą go także NOX Nachtexpress, Innight Express i TNT Innight). W już dodanej paczce użyj „Zmień przewoźnika”. Gdy wybierzesz nox dla numeru innego kształtu, Peek informuje, że „nie wydaje takich numerów”, i nie pyta nox: po dodaniu, o ile żadna usługa śledzenia nie ma przesyłki, pojawia się „Sprawdź numer śledzenia”. Link z systemu śledzenia nox w domenie `trackandtrace.app.nox.express` lub `tcs.app.nox.express` od razu przypisuje przesyłkę do nox.

Peek odczytuje status, niemiecką treść każdego skanu wraz z godziną (w czasie niemieckim) oraz czas doręczenia. Pomija miejsce skanu i uwagę, nie pyta o kod pocztowy i dlatego nie wyświetla danych dostępnych dopiero po sprawdzeniu kodu pocztowego. Peek nie pokazuje przewidywanej daty dostawy, wagi, miejsca dostawy, zdjęcia z doręczenia ani podpisu odbiorcy.

W dzień i w nocy, dopóki masz przesyłkę otwartą w Peek albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza ją nawet co 10 minut, a od 6:00 czasu niemieckiego w dniu zeskanowania jej przez nox do doręczenia nawet co 2 minuty, dopóki własny system nox odpowiada. Po dwóch dniach bez wieści (po 12 godzinach, gdy przesyłka nadal ma status „Zarejestrowana” lub „Przyjęta przez przewoźnika”) Peek sprawdza co godzinę, a jeśli przez siedem dni pozostaje na etapie „Zarejestrowana” — co 6 godzin.

Gdy przesyłka nadal na etapie „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie” nie ma nowego skanu od czterech dni, Peek oznacza ją komunikatem „Brak aktualizacji od czterech dni” (lub „Brak aktualizacji od …”), wyjaśnia: „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika nox.” nox nie ma w Peek strony pomocy, więc komunikat nie pokazuje linku do pomocy. Peek może wysyłać powiadomienia dla opcji „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Nie ustawi miejsca dostawy ani nie złoży reklamacji za ciebie.

## Pytania o śledzenie nox

### Czym jest nox NachtExpress?

Nocną usługą ekspresową spółki nox Germany GmbH z Langenfeld. Towary odebrane wczesnym wieczorem trafiają do odbiorcy przed rozpoczęciem pracy kolejnego dnia roboczego. Główne branże to motoryzacja, pojazdy użytkowe, maszyny budowlane i rolnictwo, a także rozwiązania dla sektora medycznego.

### Kiedy doręcza nox?

W nocy, z reguły do 8:00 (czasu niemieckiego), od wtorku do soboty włącznie ze świętami. Nadawca może zamówić dostawę przed 7:00 lub 6:00 (czasu niemieckiego).

### Gdzie nox zostawia przesyłkę?

W miejscu dostawy ustalonym dla twojego adresu w narzędziu ready2nox. Bez ustalonego miejsca nox może zostawić ją pod twoim adresem.

### Czy nox wymaga podpisu?

Zwykle nie: nox doręcza bez pokwitowania, często gdy nikogo nie ma, a następnie fotografuje paczkę w miejscu pozostawienia. Portierowi kierowca przekazuje przesyłkę osobiście.

### Jak zgłosić uszkodzoną dostawę nox?

Dla NachtExpress do 12:00 (czasu niemieckiego) w dniu doręczenia, na piśmie przez system śledzenia lub formularz kontaktowy nox. Zachowaj towar i oryginalne opakowanie. Jeśli doręczenie przypada w weekend lub święto, masz czas do 12:00 (czasu niemieckiego) następnego dnia roboczego.

:::sources
- [nox: Sendungsverfolgung/Reklamation erfassen](https://www.nox.eu/de/de/kundenbereich/sendungsverfolgung/) – formularz kodu kreskowego otwierający system nox, portal klienta dla wszystkich przesyłek
- [nox: Track & Trace](https://trackandtrace.app.nox.express/scannungen) – kolumny systemu śledzenia, nieznany kod, weryfikacja kodu pocztowego, formularze reklamacji, telefon, godziny i e-mail obsługi klienta, ready2nox
- [nox: Hinweise zum Nachtversand](https://www.nox.eu/de/de/kundenbereich/hinweise-zum-nachtversand/) – odbiór 14:00–18:00, dostawa przed 8:00, miejsca dostawy, duże paczki, 20 innych krajów, reklamacje przez formularz kontaktowy, termin szkody
- [nox: NachtExpress](https://www.nox.eu/de/de/leistungen/nachtexpress/) – przed 8:00, 7:00 lub 6:00, dostawa bez pokwitowania, przykłady miejsc dostawy, odbiór w oddziale, usługa celna
- [nox: Abstellplatzmanagement](https://www.nox.eu/de/de/leistungen/abstellplatzmanagement/) – przykłady miejsc dostawy, ready2nox ze zdjęciami i opisami
- [nox: Über uns](https://www.nox.eu/de/de/ueber-uns/) – dostawa przed początkiem pracy, śpiący klienci, główne branże
- [nox: Allgemeine Geschäftsbedingungen, from 1 September 2026 (PDF)](https://cms.nox.eu/de/wp-content/uploads/sites/4/2026/09/Allgemeine-Geschaftsbedingungen-fur-Geschaftskunden-nox-DE-Sept-2026-Final.pdf) – etykiety nox, dni dostaw, miejsce dostawy w ready2nox, dostawa bez miejsca, zdjęcia, wyspy, wysyłki międzynarodowe, termin reklamacji, zasady krajowe
- [nox: Allgemeine Geschäftsbedingungen](https://www.nox.eu/de/de/agb/) – obowiązujące warunki ogólne
- [nox: Kontakt](https://www.nox.eu/de/de/kontakt/kontaktformular/) – formularz kontaktowy
- [nox: Impressum](https://www.nox.eu/de/de/impressum) – nox Germany GmbH, Langenfeld, telefon, e-mail
- [Universal Parcel Scraper: nox](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/nox) – formaty kodów kreskowych, linki do śledzenia, treść i etapy skanów, czas niemiecki, co odczytuje Peek
:::
