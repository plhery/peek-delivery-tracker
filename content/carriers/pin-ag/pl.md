---
title: Śledzenie PIN AG: listy polecone, paczki i odbiór w bazie
description: Śledź list lub paczkę PIN AG w Niemczech: które przesyłki mają śledzenie, co oznaczają niemieckie statusy, odbiór z oddziału i kontakt z PIN.
slug: sledzenie-pin-ag
published: 2026-11-23
updated: 2026-11-23
---

PIN AG to firma pocztowa z Berlina, niezależna od Deutsche Post, zatrudniająca około 1300 osób. Doręcza listy, listy polecone (Einschreiben), paczki oraz zamówienia z Amazon, medimops i rebuy. Tylko część tych przesyłek ma śledzenie. Przesyłkę sprawdzisz na [stronie śledzenia PIN AG](https://sendungsverfolgung.pin-ag.de/) (Sendungsverfolgung) lub po wklejeniu numeru w pole powyżej. Numer pochodzi od nadawcy: sklep podaje go w e-mailu o wysyłce, a w przypadku listu poleconego zapytaj nadawcę.

## Które przesyłki PIN AG można śledzić?

PIN śledzi wyłącznie przesyłki wymagające potwierdzenia doręczenia, od przyjęcia przez PIN aż do doręczenia. Decyduje o tym nadawca przy wyborze usługi. Strony pomocy PIN wymieniają:

| Śledzone | Nieśledzone |
| --- | --- |
| Listy polecone: Einschreiben-Einwurf, -Übergabe, -Persönlich, -Rückschein i -Persönlich-Rückschein | Listy zwykłe (Standardsendungen) |
| Doręczenia urzędowe (Postzustellaufträge): pisma oficjalne, np. z sądu lub urzędu | Przesyłki książkowe i towarowe (Bücher- und Warensendungen) |
| Paczki (Pakete) | Zamówienia z medimops i rebuy |
| Przesyłki Amazon Prime w całych Niemczech | Zwykłe przesyłki Amazon poza Berlinem |
| Przesyłki Amazon w Berlinie | Poczta reklamowa (Werbepost) |

FAQ na stronie śledzenia PIN różni się w jednym punkcie: podaje, że zwykłe przesyłki Amazon (bez Prime) nie są śledzone i nie robi wyjątku dla Berlina. Zwykłe zamówienie z Amazon może więc w ogóle nie pokazać statusu.

## Numer śledzenia PIN AG

Formaty, które zna Peek (cyfry wymyślone):

| Numer (zmyślony) | Co to jest |
| --- | --- |
| `0100482917364058` | 16 cyfr zaczynających się od `0100`: numer paczki PIN, tak jak rozpoznaje go kod Peek. 16 cyfr pasuje też do [DHL eCommerce](carrier:dhl-ecommerce) i [TNT](carrier:tnt). |
| `4829173640582` | 13 cyfr: list polecony lub doręczenie urzędowe. Wielu innych przewoźników również używa 13 cyfr. |
| `482917364058(2)` | Te same 13 cyfr w zapisie spotykanym na etykietach, z ostatnią cyfrą w nawiasie. Peek odczytuje je jako `4829173640582`. |

Jeśli e-mail ze sklepu wymienia [DHL](carrier:dhl) lub innego przewoźnika, numer należy do niego. Nie masz numeru? Zobacz [jak znaleźć numer śledzenia](guide:find-tracking-number).

## Statusy śledzenia PIN AG wyjaśnione

Strona śledzenia PIN wyświetla pasek postępu z czterema krokami (`Sortierzentrum`, `Zustelldepot`, `In Zustellung`, potem `Zugestellt` albo `Unzustellbar` przy braku możliwości doręczenia) oraz niemiecki komunikat statusu. Co oznaczają:

| PIN podaje | Co to oznacza dla ciebie |
| --- | --- |
| `Sortierzentrum` | Przesyłka jest w sortowni PIN. |
| `Zustelldepot` | Trafiła do oddziału doręczeń dla twojego rejonu. |
| `In Zustellung` | W doręczeniu. PIN zwykle doręcza tego samego dnia: doręczyciele pracują do popołudnia. W razie nieudanej próby ponawiają ją następnego dnia. |
| `Zugestellt`, `Die Sendung wurde zugestellt.` | Doręczono. |
| `Unzustellbar` | Nie udało się doręczyć. Przesyłki niedoręczalne PIN odsyła do nadawcy. |
| `Die Sendung wurde in unserem Sortierzentrum erfasst und wird ohne weitere Aktualisierung der Sendungsverfolgung in den kommenden Tagen zugestellt. Hinweis: Bitte beachten Sie, dass es sich um eine Sendung handelt, bei dem der Zustellverlauf nicht dokumentiert wird. Für zusätzliche Informationen schauen Sie gerne in unsere FAQ.` | PIN zarejestrowało przesyłkę w sortowni i doręczy ją w najbliższych dniach bez dalszych aktualizacji śledzenia — dla tej kategorii PIN nie rejestruje przebiegu doręczenia. |

Ten ostatni status pojawia się przy kroku `In Zustellung`, ale przesyłka nie wyjechała jeszcze na trasę.

## Doręczenie PIN AG: skrzynka, zastępca i oddział

**Kto doręcza.** W Berlinie PIN doręcza list zazwyczaj dzień po opracowaniu w sortowni. Gdy poza Berlinem PIN przekazuje przesyłkę partnerowi doręczającemu (Zustellpartner), dostawa może potrwać nieco dłużej, a status może przestać się zmieniać, nawet przy liście poleconym ([dlaczego śledzenie przestaje się aktualizować](guide:tracking-not-updating)). Zwykła przesyłka Amazon poza Berlinem idzie jako list zwykły, więc jej status nie zmienia się wcale.

**Jak doręcza.** PIN wrzuca przesyłkę do skrzynki albo wręcza ją tobie. Gdy to się nie uda, może przekazać ją osobie zastępczej: członkowi rodziny, domownikowi, pracownikowi w firmie albo właścicielowi lub wynajmującemu lokal. Nie robi tego przy doręczeniu do rąk własnych ani gdy ty lub nadawca temu zapobiegliście. Listy polecone doręcza się według zasad danej opcji. PIN nie doręcza do automatów Packstation.

**Odbiór w oddziale.** Gdy PIN nie może doręczyć, przechowuje przesyłkę w oddziale PIN (Zustelldepot) lub u partnera i zostawia awizo (Benachrichtigungsschein).
- Zgłoś się od następnego dnia roboczego w godzinach otwarcia. Adres i godziny oddziału są na awizo oraz na stronie śledzenia PIN po wpisaniu numeru.
- Zabierz urzędowy dokument ze zdjęciem (obowiązkowo) i awizo. Nie masz awiza? Potrzebujesz numeru śledzenia i daty próby doręczenia.
- Inna osoba może odebrać przesyłkę z pełnomocnictwem na awizo (Vollmacht) i własnym dokumentem ze zdjęciem.
- PIN przechowuje przesyłkę przez 7 dni roboczych (poniedziałek–sobota), licząc od dnia po próbie doręczenia. Doręczenie urzędowe czeka 3 miesiące i uznaje się je za doręczone w dniu złożenia w oddziale.
- Po upływie terminu przesyłka automatycznie wraca do nadawcy.

## Przesyłka PIN AG opóźniona, zaginiona lub zwrócona

W sprawie ponownej wysyłki lub zwrotu pieniędzy skontaktuj się z nadawcą — PIN zaznacza, że to najszybsza droga.

- **Nie dotarła po 8 dniach roboczych.** Licz od poniedziałku do soboty od dnia nadania. Poproś nadawcę o nowy egzemplarz lub zwrot pieniędzy. Możesz też złożyć w PIN zlecenie poszukiwania (Nachforschungsauftrag) przez formularz online. Poszukiwania mogą potrwać do 21 dni i nie zawsze dają efekt. Zgodnie z regulaminem PIN przesyłkę uznaje się za zaginioną, jeśli nie została doręczona w ciągu 20 dni od nadania i PIN nie może jej odnaleźć. Zobacz [zagubione paczki](guide:lost-parcel).
- **`In Zustellung` ponad trzy dni.** PIN prosi o kontakt przez formularz podlinkowany na stronie śledzenia.
- **Doręczona, ale jej nie ma.** Zapytaj domowników: PIN może wydać przesyłkę osobie zastępczej. W przypadku przesyłek Amazon strona śledzenia PIN pokazuje po wpisaniu numeru i kodu pocztowego, gdzie ją oddano (np. sąsiadowi). Błąd techniczny może też pokazać doręczenie za wcześnie: poczekaj dzień, bo zwykle dociera nazajutrz. Więcej w artykule [doręczona, ale nieotrzymana](guide:delivered-not-received).
- **Uszkodzona.** Zgłoś to PIN formularzem „Hinweis zur Briefzustellung”, który służy też do opóźnionej, błędnie doręczonej lub nieprawidłowej poczty. PIN odpowiada tylko w wybranych przypadkach. Jeśli uszkodzona jest zawartość, skontaktuj się z nadawcą.
- **Zwrócona.** PIN odsyła przesyłki, gdy brak opisanej skrzynki lub wrzutki, nikt nie odbierze ich w terminie albo są zbyt zniszczone do doręczenia. Przesyłki Amazon wracają też do Amazon, jeśli adres jest błędny lub niepełny, na skrzynce brak twojego nazwiska, przesyłka nie spełnia formatu listu ustalonego z Amazon lub jest zaadresowana na Packstation. PIN nie może zmienić adresu w trakcie doręczenia — poproś Amazon o nową dostawę. Zobacz [zwrócona do nadawcy](guide:returned-to-sender).

## Kontakt i obsługa klienta PIN AG

Peek nie może skontaktować się w twoim imieniu z PIN AG ani z nadawcą. Dane publikowane przez PIN AG:

- **ServiceCenter:** 030 577978-800, od poniedziałku do piątku 8:00–18:00 (czasu niemieckiego).
- **Formularze online:** zlecenie poszukiwania (Nachforschungsauftrag) i zgłoszenie doręczenia (Hinweis zur Briefzustellung) ze stron pomocy PIN oraz formularze kontaktowe ze strony śledzenia.
- **Siedziba:** PIN AG, Alt-Moabit 91, 10559 Berlin. Telefon 030 577978-0, e-mail info@pin-ag.de.

## Śledzenie przesyłki PIN AG w Peek

Wklej numer do [Peek](/), z nawiasami lub bez: Peek automatycznie łączy ostatnią cyfrę w nawiasie z resztą numeru.

- **16 cyfr zaczynających się od 0100.** Ponieważ inni przewoźnicy też używają 16 cyfr, Peek odpytuje najbardziej prawdopodobnych według twojego kraju (regionu telefonu albo lokalizacji w przeglądarce). W Niemczech pyta najpierw PIN AG. W trakcie sprawdzania widać „Sprawdzanie usług śledzenia…”. Gdy PIN zna przesyłkę, pojawia się „PIN AG ma tę przesyłkę.” i Peek zapisuje ją pod PIN AG. Poza Niemcami Peek nie pyta PIN AG: kliknij „Zmień” i wybierz PIN AG.
- **Jeszcze niewidoczna.** Dodaj ją mimo to. W Niemczech, gdy Peek zapytał PIN, a numeru jeszcze nie ma, widać „Oczekiwanie na pierwszy skan przewoźnika PIN AG” i „Prawdopodobnie PIN AG”, a Peek dalej pyta PIN. Po tygodniu Peek poprosi o sprawdzenie numeru u nadawcy lub wybór innego przewoźnika.
- **13 cyfr.** Po samej długości Peek nie rozpozna PIN. Wklej numer mimo wszystko: Peek obsługuje ponad 4500 przewoźników. Kliknij „Zmień” i wybierz PIN AG (wpisz PIN). W już dodanej paczce użyj „Zmień przewoźnika”.
- **Link do strony śledzenia PIN** z twoim numerem od razu wskazuje PIN AG („Wykryty przewoźnik”).

**Co pokazuje Peek.** Status PIN i każdy etap z godziną w niemieckim brzmieniu PIN oraz w czasie niemieckim. `Sortierzentrum`, `Zustelldepot` oraz komunikat bez dalszych aktualizacji widnieją jako „W transporcie”, `In Zustellung` jako „W doręczeniu”, `Zugestellt` jako „Dostarczona”, a `Unzustellbar` jako „Zgłoszono problem”. Przesyłka czekająca w oddziale (strona PIN podaje wtedy adres i godziny otwarcia oddziału) pojawia się jako „Do odbioru” niezależnie od etapu wskazywanego przez PIN. PIN nie podaje miejsc skanów, przewidywanej daty dostawy, wagi ani podpisu odbiorcy. Peek pomija twój adres oraz adres oddziału: aby zobaczyć oddział, kliknij „Otwórz stronę przewoźnika PIN AG”, co otworzy numer bezpośrednio na stronie PIN. Zwykły list nie ma śledzenia w PIN, więc Peek również go nie wyśledzi.

Dopóki masz przesyłkę otwartą w Peek albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza ją nawet co 10 minut, a od 6:00 czasu niemieckiego w dniu zeskanowania jej przez PIN do doręczenia nawet co 2 minuty, dopóki własny system PIN odpowiada. Po dwóch dniach bez wieści (po 12 godzinach, gdy przesyłka nadal ma status „Zarejestrowana” lub „Przyjęta przez przewoźnika”) Peek sprawdza co godzinę, a jeśli przez siedem dni pozostaje na etapie „Zarejestrowana” — co 6 godzin.

Gdy przesyłka nadal na etapie „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie” nie ma nowego skanu od czterech dni, Peek oznacza ją komunikatem „Brak aktualizacji od czterech dni” (lub „Brak aktualizacji od …”), wyjaśnia: „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika PIN AG.” i wyświetla „Poproś przewoźnika PIN AG o pomoc”. Możesz wybrać powiadomienia „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”.

## Pytania o śledzenie PIN AG

### Dlaczego nie mogę śledzić listu PIN AG?

PIN śledzi listy polecone, doręczenia urzędowe, paczki i przesyłki Amazon Prime, a jej strony pomocy dodają przesyłki Amazon w Berlinie. Pozostałe listy, w tym zwykłe, nie mają śledzenia — zdecydował o tym nadawca. PIN przewiduje, że zwykły list dotrze w najbliższych dniach roboczych.

### Ile trwa doręczenie przez PIN AG?

W Berlinie zazwyczaj jeden dzień od opracowania przesyłki w sortowni PIN. Gdy poza Berlinem przejmuje ją partner doręczający, może to potrwać trochę dłużej. Jeśli po 8 dniach roboczych (poniedziałek–sobota) od nadania nic nie dotarło, skontaktuj się z nadawcą.

### Jak długo PIN AG przetrzymuje list w oddziale?

7 dni roboczych (poniedziałek–sobota), licząc od dnia następującego po próbie doręczenia. Doręczenia urzędowe czekają 3 miesiące. Potem przesyłka wraca do nadawcy.

### Dlaczego śledzenie PIN AG nie znajduje statusu dla mojego numeru?

FAQ PIN podaje komunikat `Für die Sendungsnummer konnte kein Zustellstatus gefunden werden.` Przesyłka może należeć do typu bez śledzenia (jak list zwykły), numer może zawierać literówkę lub nawiasy, albo przesyłka jest starsza niż sześć miesięcy — PIN usuwa dane śledzenia po sześciu miesiącach ze względu na ochronę danych.

### Czy PIN AG doręcza do Packstation?

Nie, PIN AG nie doręcza do Packstation. Amazon zazwyczaj wysyła takie zamówienia przez Deutsche Post; jeśli mimo to któreś trafi do PIN, jest odsyłane do Amazon.

:::sources
- [PIN AG: Sendungsverfolgung](https://sendungsverfolgung.pin-ag.de/) – etapy postępu; FAQ: śledzone przesyłki, brak statusu, doręczenie tego samego dnia, ponowna próba następnego dnia, formularze kontaktowe
- [PIN AG: Häufige Fragen zum Empfang von Briefsendungen](https://pin-ag.de/servicecenter/post-empfangen/sonstige-sendungen) – śledzone przesyłki, czasy doręczeń, 8 dni roboczych, zlecenie poszukiwania, odbiór w oddziale, zwroty, formularze
- [PIN AG: Häufige Fragen zum Empfang von Amazon-, medimops- und rebuy-Sendungen](https://pin-ag.de/servicecenter/post-empfangen/amazon-sendungen) – przesyłki Amazon, doręczona ale brak, zwroty, Packstation
- [PIN AG: ServiceCenter](https://pin-ag.de/servicecenter) – numer telefonu i godziny
- [PIN AG: Allgemeine Geschäftsbedingungen](https://pin-ag.de/agb) – skrzynka, osoby zastępcze, oddział, przesyłki niedoręczalne, zaginięcie po 20 dniach
- [PIN AG: Das Unternehmen](https://pin-ag.de/unternehmen) – doręczanie listów, ok. 1300 pracowników, Berlin
- [PIN AG: Impressum](https://pin-ag.de/impressum) – adres siedziby, telefon i e-mail
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – co odczytuje Peek, etapy i komunikaty PIN, formaty numerów
:::
