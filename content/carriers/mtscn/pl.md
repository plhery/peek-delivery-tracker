---
title: Śledzenie Meiteshun (MTSCN): numery AMTS i statusy
description: Śledź paczkę Meiteshun Logistics (美特顺) z Shenzhen: jak wygląda numer AMTS…FS, co znaczą etapy i dlaczego status Delivered bywa wpisany z wyprzedzeniem.
slug: meiteshun-sledzenie
published: 2026-11-18
updated: 2026-11-20
---

Meiteshun Logistics (美特顺物流, 深圳市美特顺物流有限公司, w katalogach występujący też pod skrótem MTSCN) to spedytor z Shenzhen w Chinach. Jego numery zaczynają się od `AMTS` i kończą na `FS`: jeśli sklep, w którym złożyłeś zamówienie, przekazał Ci taki kod, wklej go w pole powyżej. Historię Meiteshun czytaj jednak z rezerwą – system tej firmy potrafi zapisać całą trasę przesyłki, łącznie z doręczeniem (`Delivered`), już w chwili utworzenia zamówienia, więc sam wpis o doręczeniu nie stanowi dowodu, że paczka faktycznie dotarła.

## Numer śledzenia Meiteshun: jak wygląda

Listy przewozowe Meiteshun składają się z liter `AMTS`, dziewięciu cyfr oraz końcówki `FS` (łącznie 15 znaków). Przykłady z wymyślonymi cyframi:

| Numer | Co oznacza |
| --- | --- |
| `AMTS482910517FS` | `AMTS`, dziewięć cyfr i `FS`: list przewozowy Meiteshun. |
| `AMTS48291051FS` | Tylko osiem cyfr: to nie jest układ Meiteshun. Sprawdź, czy nie brakuje jednej cyfry. |
| `RR482910576CN` | Dwie litery, dziewięć cyfr i `CN`: międzynarodowy numer pocztowy [China Post](carrier:china-post), a nie Meiteshun. |

Wielkość liter oraz spacje nie mają znaczenia przy wklejaniu numeru do [Peek](/).

**Gdzie go znaleźć.** Sprzedawca nadaje paczkę za pośrednictwem Meiteshun i przekazuje numer w potwierdzeniu wysyłki albo na karcie zamówienia. Gdy nie możesz go odszukać, zajrzyj do poradnika [jak znaleźć numer śledzenia przesyłki](guide:find-tracking-number).

**Numer referencyjny sklepu.** Wyszukiwarka Meiteshun pozwala odnaleźć zlecenie również po wewnętrznym numerze referencyjnym sprzedawcy – wpisanym dokładnie tak, jak podał go sklep, łącznie z myślnikami – i wyświetla wówczas przypisany do niego numer `AMTS…FS`.

**Link z adresem liczbowym zamiast domeny.** Własna strona śledzenia Meiteshun działa bezpośrednio pod liczbowym adresem serwera przez nieszyfrowane połączenie HTTP, a nie pod zwykłą nazwą domeny. Link do niej ma ten adres liczbowy w miejscu nazwy witryny oraz zawiera ścieżkę `trackIndex.htm`, parametr `documentCode=` i Twój numer lub referencję – taki adres możesz wkleić bezpośrednio do Peek.

## Statusy śledzenia Meiteshun: co oznaczają

Każde zlecenie Meiteshun przechodzi przez ten sam scenariusz, a większość jego kroków powstaje z wyprzedzeniem (patrz niżej). Komunikaty w kolejności, w jakiej biegnie scenariusz, wraz z etapem pokazywanym w Peek, gdy dany wiersz jest najnowszy:

| Meiteshun pokazuje | Co to oznacza dla Ciebie | Peek pokazuje |
| --- | --- | --- |
| `The package has been collected and is ready to be sent to the distribution center` | Pierwszy krok: przyjęcie paczki przez Meiteshun w Shenzhen. | „Przyjęta przez przewoźnika” |
| `item inbound in sorting center` | Przyjęcie w sortowni, z miejscowością `ShenZhen`. | „W transporcie” |
| `item outbound in sorting center` | Wyjazd z tej sortowni. | „W transporcie” |
| `Arriving at the terminal` | Na terminalu lotniczym, z miejscem `Hong Kong`. | „W transporcie” |
| `Leaving the terminal, the airline will take off` | Wylot samolotu z Hongkongu. | „W transporcie” |
| `Arriving at the destination country` | Lądowanie w kraju docelowym, oznaczonym dwuliterowym kodem (np. `PL` dla Polski). | „W transporcie” |
| `Customs clearanceisin progress` | Odprawa celna przywozowa w kraju docelowym (brak spacji w `clearanceisin` pochodzi z systemu Meiteshun). | „W urzędzie celnym” |
| `Customs clearance completed` | Odprawa celna zakończona; paczka wyrusza w dalszą drogę. | „W transporcie” |
| `Arriving at the mail processing center` | Przybycie do centrum opracowywania przesyłek w kraju docelowym. | „W transporcie” |
| `Arrive at the sorting and distribution center` | W centrum sortowania i dystrybucji w Twoim regionie, z nazwą miejscowości lub województwa. | „W transporcie” |
| `package is on the way` | Krok poprzedzający doręczenie. Nie oznacza wydania paczki kurierowi na dzisiejszą trasę doręczeń. | „W transporcie” |
| `Delivered` | Ostatni krok scenariusza (patrz niżej). | „Dostarczona”, o ile Peek nie wstrzyma tego kroku (patrz niżej) |

Przy zleceniu, które nie ma jeszcze żadnych kroków, platforma dopisuje od siebie `货物电子信息已经收到`, czyli „otrzymano elektroniczne informacje o towarze”. Oznacza to, że sprzedawca utworzył zamówienie w systemie, lecz nikt jeszcze nie zeskanował paczki; Peek wyświetla ten wiersz po chińsku pod statusem „Zarejestrowana”.

Miejsce zdarzenia pojawia się przed przecinkiem na początku wiersza, a kroki w kraju docelowym dopisują na końcu Twoją miejscowość lub region i kod pocztowy. Każda godzina jest jednak zapisana według zegara pekińskiego (`UTC+8`), również dla etapów w Polsce: wpis z godziny `21:00` w systemie Meiteshun odpowiada `14:00` w Warszawie zimą (od ostatniej niedzieli października do ostatniej niedzieli marca) oraz `15:00` latem (od ostatniej niedzieli marca do ostatniej niedzieli października).

**Etapy zapisane z wyprzedzeniem.** Meiteshun prowadzi swoje śledzenie na platformie spedycyjnej Hualei (华磊), z której korzystają też inni chińscy spedytorzy, m.in. Pengyou. W konfiguracji Meiteshun wszystkie etapy zlecenia bywają zapisywane od razu przy tworzeniu zamówienia, każdy z zaplanowaną z góry datą i godziną – od odbioru w Shenzhen aż po `Delivered`. Są to prognozy określające, gdzie przesyłka miała się znaleźć danego dnia, a nie fizyczne skany Twojej paczki. Zewnętrzne serwisy śledzenia kopiujące rekord Meiteshun powielają dokładnie te same wpisy.

## Kto doręcza paczki Meiteshun w Polsce

Kroki Meiteshun w kraju docelowym nie wymieniają nazwy firmy doręczającej na ostatnim odcinku. Jako nazwa usługi w rekordach widnieje `MTS全球小包` (globalna mała paczka MTS). Na własnej stronie śledzenia Meiteshun numer lokalnego przewoźnika może pojawić się w kolumnie `渠道转单号` (numer przekierowania kanału) albo pod `跟踪号码` (numer śledzenia), o ile nie powtarza się tam Twój kod `AMTS…FS` – jeśli znajdziesz tam inny numer, dodaj go w Peek jako osobną przesyłkę.

Peek nie znalazł u Meiteshun żadnych opublikowanych terminów dostawy ani zasad dotyczących prób doręczenia, punktów odbioru czy czasu bezpłatnego przechowywania paczki. Zapytaj sprzedawcę, który przewoźnik doręcza Twoje zamówienie w Polsce i jaki ma numer przesyłki – o doręczeniu decyduje regulamin tej firmy.

**Cło, opłaty przywozowe i ostrzeżenie przed fałszywymi SMS-ami (UE i Polska).** W **Unii Europejskiej** (w tym w **Polsce**) od 1 lipca 2026 r. towary kupione przez internet spoza UE w przesyłkach o wartości do 150 € podlegają ryczałtowemu cłu w wysokości 3 € za kategorię towaru, a od 1 listopada 2026 r. do każdej przesyłki ze sprzedaży na odległość spoza UE – niezależnie od jej wartości – doliczana jest unijna opłata manipulacyjna (*Union Handling Fee*) w wysokości 2 € za kategorię towaru (liczona za pozycję towarową, a nie za każdą sztukę). Jeżeli w **Polsce** ostatni odcinek przesyłki spoza UE realizuje [Poczta Polska](carrier:poczta-polska) i do zapłaty pozostają należności celno-podatkowe, Poczta Polska pobiera je przy doręczeniu przesyłki lub przed jej odbiorem – i ostrzega, że Pocztex nie wysyła SMS-ów z linkami do płatności, a fałszywe SMS-y podszywające się pod Pocztex prowadzą do stron wyłudzających dane kart płatniczych. Szczegóły znajdziesz w poradnikach [paczka zatrzymana przez urząd celny](guide:customs) oraz [fałszywe SMS-y o dopłacie do paczki](guide:delivery-scam-texts).

## Paczka Meiteshun nie dochodzi albo śledzenie utknęło

**Widnieje `Delivered`, ale nic nie przyszło.** Wpis może być wyłącznie zaprogramowaną prognozą ze scenariusza, a nie potwierdzeniem doręczenia. Poinformuj sprzedawcę, że przesyłka nie dotarła, i poproś o nazwę oraz numer śledzenia lokalnego przewoźnika. Więcej w poradniku [paczka ma status dostarczonej, ale jej nie otrzymałem](guide:delivered-not-received).

**Każdy krok pojawia się punktualnie, a paczki brak.** Ponieważ etapy powstały w chwili utworzenia zlecenia, pokazują harmonogram, a nie rzeczywiste położenie przesyłki. Kieruj się terminem dostawy podanym przez sklep przy składaniu zamówienia i napisz do sprzedawcy, gdy ten termin upłynie.

**W Peek paczka stoi w Shenzhen, choć link od sprzedawcy pokazuje więcej.** Jeśli Peek wyświetla tylko początkowe kroki w Shenzhen, podczas gdy zewnętrzny link pokazuje dalszą trasę, pominięte wiersze zostały zapisane z wyprzedzeniem lub noszą datę późniejszą niż chwila sprawdzenia (patrz niżej). Wstrzymany krok nie liczy się jako skan: nie zmienia statusu ani oczekiwanej daty i nie wywołuje powiadomienia. Gdy Peek wstrzyma takie etapy, pod statusem przesyłki wyświetla aż do doręczenia lub zwrotu uwagę: „Na stronie przewoźnika Meiteshun Logistics widać etapy zapisane z wyprzedzeniem, nawet doręczenie. Peek nie może ich potwierdzić, więc ich nie pokazuje. Poszukaj tam firmy, która doręcza w Twoim kraju, i jej numeru śledzenia; jeśli jej nie ma, zapytaj sprzedawcę.” Więcej w poradnikach [dlaczego status przesyłki się nie zmienia](guide:tracking-not-updating) oraz [śledzenie paczki z Chin](guide:tracking-from-china).

**Brak wyników dla numeru.** Dla nieznanego numeru system Meiteshun pokazuje go wyłącznie jako numer referencyjny z pustymi pozostałymi polami. Peek traktuje taką odpowiedź jak zlecenie wpisane bez pierwszego skanu: sprawdza je dalej w rytmie przewidzianym dla etapów pisanych z wyprzedzeniem i wyświetla „Oczekiwanie na przewoźnika Meiteshun Logistics”, bez komunikatu proszącego o weryfikację numeru. Sprawdź więc numer bezpośrednio u sprzedawcy.

**Przesyłka zagubiona, uszkodzona lub zwrócona.** Umowę przewozu zawarł z Meiteshun sprzedawca, dlatego o zwrot pieniędzy lub ponowną wysyłkę zwróć się do sklepu albo do platformy zakupowej. Zobacz [zagubiona paczka](guide:lost-parcel) oraz [zwrot przesyłki do nadawcy](guide:returned-to-sender).

## Kontakt z Meiteshun i obsługa klienta

W każdej sprawie dotyczącej zamówienia napisz najpierw do sprzedawcy, który nadał paczkę u Meiteshun; jeśli nie odpowiada, skorzystaj z ochrony kupujących na platformie zakupowej. Gdy żadna z tych dróg nie pomoże, profil przewoźnika na stronie [17track.net/en/carriers/mtscn](https://www.17track.net/en/carriers/mtscn) podaje dla Meiteshun adres e-mail `szmeiteshun@163.com`, witrynę `mtswl.com.cn` oraz biuro w dzielnicy Longgang w Shenzhen, bez numeru telefonu (i zaznacza, że odnośniki ze strony przewoźnika do 17TRACK, 718track oraz własnej wyszukiwarki zamówień prowadzą do odrębnych ścieżek zapytań, które nie muszą korzystać z tej samej bazy danych). Pisząc na ten adres, podaj numer `AMTS…FS` oraz nazwę sklepu.

## Śledzenie paczki Meiteshun w Peek

[Peek](/) od razu rozpoznaje numer Meiteshun po układzie `AMTS`, dziewięciu cyfrach i `FS`, którego nie używa żaden inny przewoźnik w katalogu. Możesz też wkleić link do strony śledzenia Meiteshun z parametrem `documentCode=` zawierającym numer przesyłki albo referencję sprzedawcy. Samej referencji sklepu Peek nie rozpozna po kształcie: wklej ją mimo to (Peek śledzi ponad 3500 przewoźników), po czym naciśnij zmianę przewoźnika i wybierz Meiteshun Logistics (wyszukiwarka znajduje też `美特顺` oraz `MTSCN`) albo użyj opcji „Zmień przewoźnika” na karcie dodanej już paczki – Peek wyświetli wtedy również przypisany numer `AMTS`. Pamiętaj jednak, że Peek usuwa myślniki z wpisanego tekstu i z linku, dlatego referencję zawierającą myślniki sprawdź bezpośrednio na stronie śledzenia Meiteshun.

Peek odczytuje własny system Meiteshun i pokazuje aktualny etap, każdy krok z miejscowością oraz kraj docelowy. Zdarzenia wyświetlają się w brzmieniu Meiteshun: `Customs clearanceisin progress` zaliczane jest do odprawy celnej („W urzędzie celnym”), natomiast `package is on the way` do transportu („W transporcie”), a nie wydania do doręczenia. Z kroków w kraju docelowym Peek zachowuje nazwę miejscowości, odrzucając kod pocztowy. Godziny odczytywane są według czasu pekińskiego i pokazywane w strefie czasowej Twojego urządzenia („Godziny w Twojej strefie czasowej: …”). Peek pomija imiona i nazwiska, adres, wagę, opłaty, podpis i dowód doręczenia, które mogą znajdować się w rekordzie, oraz nie pokazuje przewidywanej daty dostawy, ponieważ Meiteshun jej nie podaje.

Peek wstrzymuje każdy krok zapisany ponad dwanaście godzin przed godziną, którą nosi, a także każdy krok datowany w przyszłości względem chwili sprawdzenia (według zegara w Pekinie). Wyznaczając status, kieruje się wyłącznie zachowanymi krokami, a nie zbiorczym statusem rekordu. Gdy cały scenariusz zamówienia został wpisany w momencie jego utworzenia, Peek zachowuje jedynie kroki datowane przed tą chwilą lub maksymalnie do sześciu godzin po niej, dzięki czemu wpisane wtedy `Delivered` zostaje wstrzymane. Jeżeli interfejs danych nie zwraca kroków i Peek musi odczytać stronę `trackIndex.htm`, wstrzymuje tam każde `Delivered`, ponieważ strona ta nie ujawnia, kiedy poszczególne wiersze zostały utworzone. Zamówienie, którego wszystkie kroki zostały wstrzymane, nie ma jeszcze historii w Peek i pokazuje „Oczekiwanie na przewoźnika Meiteshun Logistics” bez prośby o sprawdzenie numeru: Peek traktuje je tak samo jak przesyłkę wpisaną bez skanów i odpytuje system Meiteshun co 6 godzin po pierwszych godzinach dla paczki w aplikacji na iPhone'a lub Androida albo na koncie. Przy wyszukaniu bez konta pyta przez pierwsze 6 godzin, następnie przy otwarciu linku (najwyżej co 6 godzin w pierwszych dwóch dniach, później najwyżej raz dziennie) oraz – przy włączonych powiadomieniach – raz dziennie przez 30 dni od sprawdzenia. Niezależnie od tego, czy wstrzymano część kroków, czy wszystkie, Peek odrzuca też ich kopie z zewnętrznych serwisów śledzenia.

Jedyne automatyczne przekazanie ostatniej mili, jakie Peek przyjmuje z rekordu Meiteshun, prowadzi do [Poczty Szwajcarskiej](carrier:swiss-post) (*Swiss Post*), gdy rekord wskazuje linię i numer Swiss Post dla przesyłki do Szwajcarii lub Liechtensteinu. Każdy inny numer lokalnego kuriera otrzymany od sprzedawcy dodaj jako osobną przesyłkę.

Dopóki masz paczkę otwartą na ekranie albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza aktualizacje nawet co 10 minut (oraz co 2 minuty w dniu wydania do doręczenia od 6:00 czasu lokalnego lub pół godziny przed oknem dostawy, choć żaden znany krok samego Meiteshun nie daje statusu „W doręczeniu”), o ile odpowiada bezpośredni system Meiteshun lub Poczty Szwajcarskiej; przez same serwisy śledzenia sprawdza co godzinę, co 3 godziny po dniu bez nowego skanu oraz do 15 minut w dniu doręczenia przy otwartej karcie lub włączonych powiadomieniach. W pozostałym czasie, a także po dwóch dniach bez nowości (lub po 12 godzinach na etapie „Zarejestrowana” bądź „Przyjęta przez przewoźnika” – co zlecenie ze scenariuszem wpisanym z góry szybko osiąga), Peek sprawdza co godzinę. Po przejęciu paczki przez Pocztę Szwajcarską obowiązuje jej własny rytm. Powiadomienia możesz ustawić na „Wszystkie aktualizacje”, „Tylko ważne” albo „Tylko w dniu dostawy”. Peek nie może skontaktować się z Meiteshun ani ze sprzedawcą w Twoim imieniu.

## Najczęstsze pytania o śledzenie Meiteshun

### Czym jest Meiteshun Logistics (美特顺)?

To 深圳市美特顺物流有限公司 (Shenzhen Meiteshun Logistics), spedytor z siedzibą w Shenzhen w Chinach. Jego listy przewozowe mają format `AMTS`, dziewięć cyfr i `FS`, a w rekordach przesyłek widnieje usługa `MTS全球小包`.

### Czy numer AMTS…FS należy do Poczty Chińskiej (China Post)?

Nie. Układ `AMTS`, dziewięć cyfr i `FS` należy wyłącznie do Meiteshun. Międzynarodowe numery rejestrowane [China Post](carrier:china-post) składają się z dwóch liter, dziewięciu cyfr oraz końcówki `CN`.

### Dlaczego Meiteshun pokazuje, że paczka została doręczona, skoro nic nie przyszło?

Najprawdopodobniej dlatego, że system Meiteshun zapisuje wszystkie etapy zlecenia – łącznie z `Delivered` – już w chwili utworzenia zamówienia. Tak wpisane doręczenie jest prognozą, a nie fizycznym skanem kuriera. Zgłoś sprzedawcy brak paczki.

### Kto doręcza przesyłki Meiteshun w Polsce?

Wiersze historii Meiteshun nie podają nazwy przewoźnika doręczającego. Na stronie śledzenia Meiteshun numer lokalnej firmy może widnieć w polu `渠道转单号` (numer przekierowania); jeśli go tam nie ma, zapytaj sprzedawcę, komu powierzył doręczenie w Polsce i jaki jest numer przesyłki u tego przewoźnika.

### Z kim się skontaktować w sprawie zagubionej paczki Meiteshun?

Ze sprzedawcą, u którego kupiłeś towar – to on zlecił przewóz firmie Meiteshun. Jeśli ani sklep, ani platforma zakupowa nie pomogą, strona 17TRACK wymienia dla Meiteshun adres e-mail `szmeiteshun@163.com`.

:::sources
- [17TRACK: MTSCN tracking (po angielsku)](https://www.17track.net/en/carriers/mtscn) – adres e-mail, strona internetowa i biuro w Shenzhen (dzielnica Longgang) podane dla Meiteshun, brak telefonu oraz uwaga o odrębnych ścieżkach wyszukiwania
- [Komisja Europejska: EU Customs Reform (po angielsku)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – cło 3 € w paczkach do 150 € od 1 lipca 2026 r. oraz unijna opłata manipulacyjna 2 € od 1 listopada 2026 r.
- [Komisja Europejska: Guidance on the 3 EUR customs duty and Union handling fee (PDF, po angielsku)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – opłata 2 € za kategorię towaru sprzedawanego na odległość niezależnie od wartości, liczona od pozycji deklaracji tak jak cło 3 €
- [Poczta Polska: Zmiany w opłatach dla przesyłek spoza UE od 1 lipca 2026 r.](https://www.poczta-polska.pl/news/zmiany-w-oplatach-dla-przesylek-spoza-unii-europejskiej-od-1-lipca-2026-r/) – pobór należności celno-podatkowych w Polsce przy doręczeniu przesyłki lub przed jej odbiorem
- [Poczta Polska: Nowe przepisy UE – opłata za przesyłki spoza UE](https://media.poczta-polska.pl/releases/nowe-przepisy-unii-europejskiej-tak-dziala-nowa-oplata-za-przesylki-spoza-ue) – unijna opłata manipulacyjna 2 € od 1 listopada 2026 r.
- [Poczta Polska: Uwaga na fałszywe wiadomości Pocztex](https://www.poczta-polska.pl/news/uwaga-na-falszywe-wiadomosci-pocztex-grozna-kampania-phishingowa/) – ostrzeżenie w Polsce przed fałszywymi SMS-ami z linkami do płatności podszywającymi się pod Pocztex
- [Komisja Europejska: Summertime (po angielsku)](https://transport.ec.europa.eu/transport-themes/summertime_en) – czas letni w UE od ostatniej niedzieli marca do ostatniej niedzieli października
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – nazwa firmy, platforma Hualei, format AMTS…FS, system śledzenia i jego linki, wyszukiwanie po referencji z myślnikami, scenariusz i brzmienie kroków, miejscowości i czas pekiński, etapy zapisywane z wyprzedzeniem, odpowiedź dla nieznanego numeru, chiński wiersz platformy, nazwa usługi, brak nazwy doręczyciela w krokach, kolumny numeru przekierowania i śledzenia, przekazanie Poczcie Szwajcarskiej oraz co Peek pokazuje, wstrzymuje i pomija
:::
