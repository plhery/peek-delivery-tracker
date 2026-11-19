---
title: Śledzenie Xinghe Freight: numery GZXH, statusy i dostawa
description: Śledź paczkę Xinghe Freight (星和货运) z Chin: jak wygląda numer GZXH…YQ, co oznaczają statusy, dlaczego część etapów powstaje z wyprzedzeniem i kto doręcza.
slug: xinghe-freight-sledzenie
published: 2026-11-18
updated: 2026-11-20
---

Xinghe Freight (星和货运, 广州星和货运代理有限公司, Guangzhou Xinghe Freight Forwarding) to spedytor z Kantonu (Guangzhou) w Chinach, którego śledzenie działa na systemie przewoźnika [GZYL](carrier:gzyl). Jeśli sklep internetowy podał Ci numer zaczynający się od `GZXH` i kończący na `YQ`, wklej go w pole powyżej albo wpisz w wyszukiwarkę przesyłek na chińskiej stronie Xinghe, xinghe-express.cn. Do historii podchodź jednak ostrożnie: na tej platformie cały harmonogram trasy – łącznie z `Delivered` – bywa wpisany z góry, zanim ktokolwiek fizycznie zeskanuje paczkę.

## Numer śledzenia Xinghe Freight: jak wygląda

Numery, które [Peek](/) rozpoznaje jako przesyłki Xinghe Freight, składają się z liter `GZXH`, dziesięciu cyfr oraz `YQ` – łącznie mają 16 znaków. Przykłady z wymyślonymi cyframi:

| Numer | Co oznacza |
| --- | --- |
| `GZXH4829105736YQ` | `GZXH`, dziesięć cyfr i `YQ`: układ Xinghe Freight. Peek od razu rozpoznaje przewoźnika. |
| `GZXH482910573YQ` | Tylko dziewięć cyfr: to nie jest format Xinghe. Sprawdź, czy przy kopiowaniu nie uciekła jedna cyfra. |
| `YL4829105736YQ` | `YL`, dziesięć cyfr i `YQ`: format listów przewozowych [GZYL](carrier:gzyl). Ten sam system śledzenia, ale inny spedytor. |
| `JXCAA4829105736YQ` | Również kończy się na `YQ`, jak wiele numerów chińskich spedytorów, lecz zaczyna od `JXC`: to numer Juxi Express. |

Spacje, myślniki i wielkość liter nie mają znaczenia, gdy wklejasz numer do Peek.

**Gdzie go znaleźć.** Sprzedawca nadaje przesyłkę u Xinghe i przekazuje numer w wiadomości o wysyłce lub w szczegółach zamówienia. Jeśli nie możesz go odszukać, zajrzyj do poradnika [jak znaleźć numer śledzenia przesyłki](guide:find-tracking-number).

## Statusy śledzenia Xinghe Freight: co oznaczają

Ponieważ śledzenie Xinghe działa na systemie GZYL, poszczególne kroki wyświetlają się z angielskimi komunikatami GZYL, łącznie z literówkami. Peek odczytuje je według listy etapów GZYL. W kolejności, w jakiej zazwyczaj pojawiają się na trasie paczki:

| System pokazuje | Co to oznacza dla Ciebie | Peek pokazuje |
| --- | --- | --- |
| `Order information received` | Sprzedawca przesłał dane zamówienia. Nikt nie ma jeszcze fizycznie paczki. | „Zarejestrowana” |
| `Arrive at the cross border receiving point` | Spedytor przyjął paczkę: to pierwszy fizyczny skan w jego rękach. | „Przyjęta przez przewoźnika” |
| `Goods leave the operation center` | Przesyłka opuściła centrum operacyjne. | „W transporcie” |
| `Arrive at the international airport` | Na lotnisku wylotu, w oczekiwaniu na samolot. | „W transporcie” |
| `Departing from the airport` | Samolot wystartował. | „W transporcie” |
| `Item arrives at destination airport` | Lądowanie w kraju docelowym. | „W transporcie” |
| `local carrier Agent Import customs clearance` | Agencja w kraju docelowym prowadzi odprawę celną przywozową. | „W urzędzie celnym” |
| `The import customs keep it for inspection` | Urząd celny kraju docelowego zatrzymał przesyłkę do kontroli. | „W urzędzie celnym” |
| `Customs clearance completed` | Odprawa celna zakończona; paczka rusza dalej. | „W transporcie” |
| `Internationaliy Package received at processing center` | Centrum opracowywania w kraju docelowym przyjęło ocloną paczkę (`Internationaliy` to literówka platformy). To nie jest ponowne nadanie. | „W transporcie” |
| `Leave the email processing center` | Paczka opuściła to centrum opracowywania. | „W transporcie” |
| `Arrival sorting, distribution center` | Przyjęcie w centrum dystrybucyjnym w Twoim kraju, z nazwą miejscowości po komunikacie. | „W transporcie” |
| `Leaving the sorting center` | Wyjazd z sortowni, wraz z nazwą miejscowości. | „W transporcie” |
| `Package is on the way` | Przesyłka przemieszcza się w kraju docelowym, z podaną miejscowością. Nie oznacza to jeszcze wydania kurierowi do doręczenia dziś. | „W transporcie” |
| `Delivered` | Wpis o doręczeniu z miejscowością. Może być częścią z góry zapisanego planu, a nie prawdziwym skanem (patrz niżej). | „Dostarczona”, z uwagą o stałym harmonogramie, chyba że Peek wstrzyma ten etap (patrz niżej) |

**Przez Hongkong.** Przesyłka może też otrzymać wiersz `香港处理中心已收寄【Hong Kong】`, oznaczający przyjęcie pocztowe w centrum opracowywania w Hongkongu, przekazywany po chińsku z nazwą miasta w nawiasie. Peek wyświetla go w oryginalnym brzmieniu pod statusem „Przyjęta przez przewoźnika”.

**Wiersz po chińsku.** Przy zamówieniu, które nie ma jeszcze żadnych skanów, strona systemu potrafi pokazać `货物电子信息已经收到`, czyli „otrzymano elektroniczne informacje o towarze”. Platforma dopisuje ten wiersz automatycznie po utworzeniu zlecenia przez sprzedawcę, zanim ktokolwiek zeskanuje paczkę. Peek pokazuje go po chińsku pod etapem „Zarejestrowana”.

**Godziny i strefa czasowa.** Peek odczytuje każdą godzinę z systemu jako czas pekiński (`UTC+8`) – także przy etapach w Europie – i przelicza ją na strefę czasową Twojego urządzenia. W czasie zimowym (od ostatniej niedzieli października do ostatniej niedzieli marca) zdarzenie z `20:00` w Pekinie to `13:00` w Warszawie, a w czasie letnim (od ostatniej niedzieli marca do ostatniej niedzieli października) to `14:00`.

**Etapy zapisane z wyprzedzeniem.** Na tej platformie spedytorzy mogą wpisać z góry całą oś czasu przesyłki, od przyjęcia aż po ostatni krok w kraju docelowym, a system odsłania kolejne wpisy wraz z nadejściem ich zaplanowanej godziny. Taki wpis jest planem, a nie fizycznym skanem. W systemie GZYL harmonogram kończy się wpisem `Delivered` dodawanym zbiorczo wieczorem wskazanego dnia: nosi datę dnia następującego po ostatnim zaplanowanym etapie, w godzinach pracy biur w Pekinie, niezależnie od kraju odbiorcy. Peek wstrzymuje takie wpisy (patrz niżej), ale zewnętrzne serwisy śledzenia kopiujące rekord mogą je mimo to pokazać – widoczne tam `Delivered` nie dowodzi więc, że paczka dotarła pod Twoje drzwi.

## Kto doręcza paczki Xinghe Freight w Polsce

Peek nie ma informacji, jaka firma doręcza przesyłki Xinghe w Polsce: nazwy lokalnego przewoźnika nie podają ani wpisy w systemie śledzenia, ani strona Xinghe. Jedynym operatorem doręczającym, któremu Peek potrafi automatycznie przekazać paczkę Xinghe, jest [Poczta Szwajcarska](carrier:swiss-post) (*Swiss Post*) w Szwajcarii i Liechtensteinie, gdy rekord Xinghe ją wskazuje.

Strona internetowa Xinghe nie zawiera żadnych informacji dla kupujących w sieci: nie podaje czasów dostawy, liczby prób doręczenia, zasad awizowania ani czasu odbioru w punkcie. Decyduje o tym przewoźnik obsługujący ostatni odcinek w Polsce – zapytaj sprzedawcę, jakiej firmie powierzył doręczenie i jaki jest jej lokalny numer śledzenia.

**Cło, opłata manipulacyjna i fałszywe SMS-y (UE i Polska).** W **Unii Europejskiej** (w tym w **Polsce**) od 1 lipca 2026 r. towary kupione na odległość spoza UE w przesyłkach o wartości do 150 € objęte są ryczałtowym cłem w wysokości 3 € za kategorię towaru, a od 1 listopada 2026 r. dochodzi unijna opłata manipulacyjna (*Union Handling Fee*) w wysokości 2 € za kategorię towaru niezależnie od wartości przesyłki. Za jedną kategorię towaru uznaje się przedmioty w tej samej paczce o wspólnej klasyfikacji taryfowej, opisie oraz – gdy wymagane – pochodzeniu, bez względu na liczbę sztuk. Jeżeli w **Polsce** ostatni odcinek przesyłki spoza UE realizuje [Poczta Polska](carrier:poczta-polska) i do uregulowania pozostają należności celno-podatkowe, Poczta Polska pobiera je przy doręczeniu przesyłki lub przed jej odbiorem – i ostrzega, że Pocztex nie wysyła SMS-ów z linkami do płatności, a fałszywe SMS-y podszywające się pod Pocztex prowadzą do stron wyłudzających dane kart płatniczych. Więcej informacji znajdziesz w poradnikach [paczka zatrzymana przez urząd celny](guide:customs) oraz [fałszywe SMS-y o dopłacie do paczki](guide:delivery-scam-texts).

## Paczka Xinghe Freight nie dochodzi albo śledzenie stoi w miejscu

**Zatrzymana na cle.** Jeśli otrzymasz wezwanie do zapłaty należności przywozowych, zweryfikuj je bezpośrednio u przewoźnika doręczającego w Polsce i zajrzyj do poradnika [paczka zatrzymana przez urząd celny](guide:customs). Gdy status celny nie zmienia się przez wiele dni, powiadom sprzedawcę.

**System przewoźnika albo inny serwis pokazuje więcej etapów niż Peek.** Dodatkowe wiersze, w tym `Delivered`, mogą być z góry rozpisanym planem, który Peek wstrzymuje jako niepotwierdzony. Kieruj się przewidywanym terminem dostawy podanym przez sklep przy zakupie i skontaktuj się ze sprzedawcą, gdy ten termin minie. Więcej w poradnikach [dlaczego status przesyłki się nie zmienia](guide:tracking-not-updating) oraz [śledzenie paczki z Chin](guide:tracking-from-china).

**Widnieje `Delivered`, ale paczki nie ma.** Wpis może pochodzić z harmonogramu utworzonego z wyprzedzeniem. O ile doręczenia nie przejęła Poczta Szwajcarska, pod statusem „Dostarczona” Peek wyświetla ostrzeżenie: „Statusy przewoźnika Xinghe Freight pojawiają się według stałego harmonogramu, bez podpisu. Jeśli nic nie dotarło, zapytaj sprzedawcę.” Zgłoś sprzedawcy brak przesyłki i poproś o nazwę przewoźnika ostatniej mili oraz jego numer śledzenia. Zobacz też [paczka ma status dostarczonej, ale jej nie otrzymałem](guide:delivered-not-received).

**Przesyłka zagubiona albo zwrócona.** Umowę z Xinghe zawarł sprzedawca, dlatego o zwrot pieniędzy lub ponowną wysyłkę zwróć się do sklepu albo do platformy zakupowej. Sprawdź poradniki [zagubiona paczka](guide:lost-parcel) i [zwrot przesyłki do nadawcy](guide:returned-to-sender).

## Kontakt z Xinghe Freight i obsługa klienta

Zgodnie z informacjami na stronie xinghe-express.cn, dostępnej wyłącznie po chińsku:

- **Infolinia**: `19924483103`, opisana jako czynna przez całą dobę, siedem dni w tygodniu (`+86 199 2448 3103` z zagranicy).
- **E-mail**: `3975605292@qq.com`, przeznaczony do zapytań biznesowych i wycen frachtu.
- **Adres**: Kanton (Guangzhou), prowincja Guangdong, bez podanej ulicy; przed wizytą strona prosi o wcześniejszy kontakt.

Witryna nie udostępnia obsługi dla odbiorców indywidualnych za granicą, a formularz kontaktowy jedynie otwiera wiadomość na powyższy adres e-mail. Klientem Xinghe jest sprzedawca, więc w sprawach zamówienia pisz najpierw do sklepu, a w razie braku odpowiedzi – do serwisu zakupowego.

## Śledzenie paczki Xinghe Freight w Peek

[Peek](/) natychmiast rozpoznaje Xinghe Freight po numerze w formacie `GZXH…YQ`. Jeśli sprzedawca przekazał Ci numer o innym układzie, również go wklej: Peek obsługuje ponad 3500 przewoźników. Aby przypisać przesyłkę do Xinghe ręcznie, naciśnij zmianę przewoźnika podczas dodawania i wyszukaj Xinghe Freight (możesz też wpisać `星和货运`) albo wybierz „Zmień przewoźnika” na karcie dodanej już paczki.

Peek odpytuje system GZYL dokładnie tak jak pole wyszukiwania na stronie Xinghe, wyświetlając bieżący etap, każdy krok z miejscowością oraz kraj docelowy, o ile rekord go podaje. Godziny są odczytywane według czasu pekińskiego i przeliczane na strefę czasową Twojego urządzenia (z informacją „Godziny w Twojej strefie czasowej: …” na `peektracker.com` oraz w aplikacji na iPhone'a). Peek pomija imiona i nazwiska, adres, kody pocztowe, wagę, szczegóły płatności, podpis i potwierdzenie doręczenia, które mogą znajdować się w rekordzie, a także nie pokazuje przewidywanej daty dostawy, bo system jej nie podaje.

Gdy system GZYL nie zna Twojego numeru, Peek odpytuje zapasowo zewnętrzne serwisy śledzenia przesyłek i pokazuje zapisaną u nich kopię zdarzeń, która dla części numerów Xinghe bywa jedynym dostępnym źródłem. Dopóki ani system bezpośredni, ani serwisy śledzenia nie zwrócą żadnego kroku, paczka ma komunikat „Oczekiwanie na przewoźnika Xinghe Freight”, bez prośby o sprawdzenie numeru.

Peek wstrzymuje każdy krok zapisany ponad 12 godzin przed godziną widniejącą przy zdarzeniu albo ponad 6 godzin przed nią w tej samej sekundzie co taki krok, a także wszelkie wpisy z datą późniejszą niż chwila sprawdzenia (według zegara w Pekinie). W rekordzie zawierającym kroki zapisane z wyprzedzeniem wstrzymuje również `Delivered`, nawet wpisane tego samego dnia. Peek wyznacza status wyłącznie na podstawie zachowanych kroków, ignorując zbiorczy status rekordu, i odrzuca także kopie wstrzymanych kroków (w tym `Delivered`) pobrane z serwisów śledzenia, nawet gdy zachowuje inne zdarzenia. Jeśli jednak bezpośredni system nie ma rekordu dla danego numeru lub jest nieosiągalny, w kopii z serwisu śledzenia Peek nie jest w stanie odróżnić zaplanowanego kroku od prawdziwego skanu. Gdy wstrzymuje kroki zapisane z wyprzedzeniem, pod statusem paczki wyświetla aż do doręczenia lub zwrotu komunikat: „Na stronie przewoźnika Xinghe Freight widać etapy zapisane z wyprzedzeniem, nawet doręczenie. Peek nie może ich potwierdzić, więc ich nie pokazuje. Poszukaj tam firmy, która doręcza w Twoim kraju, i jej numeru śledzenia; jeśli jej nie ma, zapytaj sprzedawcę.” Taki krok nigdy nie liczy się jako skan: nie zmienia statusu i nie wysyła powiadomienia. Gdy wszystkie kroki są wstrzymane albo przesyłka widnieje w systemie bez skanów, w aplikacji na iPhone'a lub na koncie Peek ponawia zapytania co 6 godzin po pierwszych godzinach; przy sprawdzeniu bez konta pyta przez pierwsze 6 godzin, następnie po otwarciu linku (najwyżej co 6 godzin przez pierwsze dwa dni, potem najwyżej raz dziennie), a przy włączonych powiadomieniach – raz dziennie przez 30 dni od wyszukania.

Poza Pocztą Szwajcarską w Szwajcarii i Liechtensteinie Peek nie przełącza przesyłki Xinghe na firmę doręczającą: jeśli otrzymasz od sprzedawcy numer lokalnego kuriera, dodaj go jako osobną paczkę. Gdy masz przesyłkę otwartą na ekranie albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza ją nawet co 10 minut, dopóki odpowiada bezpośredni system; w pozostałym czasie oraz gdy paczka ucichnie – co godzinę (przez same serwisy śledzenia: co godzinę, co 3 godziny po dobie bez nowego skanu, a w dniu wydania do doręczenia nawet co 15 minut, dopóki masz paczkę otwartą lub włączone powiadomienia). Możesz wybrać powiadomienia „Wszystkie aktualizacje”, „Tylko ważne” (obejmujące tutaj dwa etapy celne oraz `Delivered`, bez lotów i sortowni) albo „Tylko w dniu dostawy”. Peek nie skontaktuje się w Twoim imieniu z Xinghe Freight, GZYL ani ze sprzedawcą.

## Najczęstsze pytania o śledzenie Xinghe Freight

### Czym jest Xinghe Freight (星和货运)?

To 广州星和货运代理有限公司 (Guangzhou Xinghe Freight Forwarding), spedytor z siedzibą w Kantonie (Guangzhou) w Chinach. Na swojej stronie podaje, że działa od 2014 roku i zajmuje się spedycją morską, lotniczą i lądową, agencją morską oraz obsługą celną. Numery przypisywane przez Peek do Xinghe Freight mają postać `GZXH`, dziesięciu cyfr i `YQ`.

### Dlaczego moja paczka Xinghe Freight pokazuje śledzenie GZYL?

Ponieważ śledzenie Xinghe działa na platformie [GZYL](carrier:gzyl): wyszukiwarka na własnej stronie Xinghe przekazuje Twój numer właśnie tam, dlatego zdarzenia wracają w brzmieniu GZYL. Peek zachowuje przesyłkę pod nazwą Xinghe Freight i bezpośrednio odczytuje ten system.

### Kto doręcza paczki Xinghe Freight w Polsce?

Peek tego nie wie: ani wpisy w systemie śledzenia, ani strona Xinghe nie wymieniają lokalnego doręczyciela. Tylko w Szwajcarii i Liechtensteinie Peek może automatycznie przekazać paczkę Poczcie Szwajcarskiej. W Polsce zapytaj sprzedawcę, który przewoźnik odpowiada za ostatni odcinek i jaki nadał numer przesyłki.

### Dlaczego Peek nie pokazuje jeszcze żadnych zdarzeń dla numeru GZXH?

System może jeszcze nie mieć tego zlecenia albo zawierać wyłącznie etapy wpisane z wyprzedzeniem (aż po `Delivered`), które Peek wstrzymuje jako niepotwierdzone. Peek odpytuje również serwisy śledzenia i wyświetla „Oczekiwanie na przewoźnika Xinghe Freight” bez prośby o weryfikację numeru – porównaj go więc samodzielnie z wiadomością od sklepu, a jeśli po kilku dniach nic się nie pojawi, zapytaj sprzedawcę, czy paczka faktycznie została nadana.

:::sources
- [Xinghe Freight: 广州星和货运代理有限公司 (strona główna, po chińsku)](http://xinghe-express.cn/) – nazwa firmy, usługi spedycyjne, wyszukiwarka korzystająca z systemu GZYL, infolinia i adres e-mail
- [Xinghe Freight: 关于我们 (o firmie, po chińsku)](http://xinghe-express.cn/about.html) – założenie w 2014 r., spedycja morska, lotnicza i lądowa, agencja morska oraz zgłoszenia celne
- [Xinghe Freight: 产品服务 (usługi, po chińsku)](http://xinghe-express.cn/services.html) – oferta spedycyjna bez wzmianek o paczkach e-commerce dla konsumentów
- [Xinghe Freight: 联系我们 (kontakt, po chińsku)](http://xinghe-express.cn/contact.html) – całodobowa infolinia, e-mail do zapytań handlowych i wycen, lokalizacja podana wyłącznie jako Guangzhou z prośbą o kontakt przed wizytą oraz formularz otwierający e-mail
- [Komisja Europejska: EU Customs Reform (po angielsku)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – cło 3 € za kategorię towaru w paczkach do 150 € od 1 lipca 2026 r. oraz unijna opłata manipulacyjna 2 € od 1 listopada 2026 r.
- [Komisja Europejska: Guidance on the 3 EUR customs duty and Union handling fee (PDF, po angielsku)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – naliczanie opłaty 2 € za pozycję towarową niezależnie od wartości przesyłki oraz definicja pozycji
- [Poczta Polska: Zmiany w opłatach dla przesyłek spoza UE od 1 lipca 2026 r.](https://www.poczta-polska.pl/news/zmiany-w-oplatach-dla-przesylek-spoza-unii-europejskiej-od-1-lipca-2026-r/) – pobór należności celno-podatkowych w Polsce przy doręczeniu przesyłki lub przed jej odbiorem
- [Poczta Polska: Nowe przepisy UE – opłata za przesyłki spoza UE](https://media.poczta-polska.pl/releases/nowe-przepisy-unii-europejskiej-tak-dziala-nowa-oplata-za-przesylki-spoza-ue) – unijna opłata manipulacyjna 2 € od 1 listopada 2026 r.
- [Poczta Polska: Uwaga na fałszywe wiadomości Pocztex](https://www.poczta-polska.pl/news/uwaga-na-falszywe-wiadomosci-pocztex-grozna-kampania-phishingowa/) – ostrzeżenie w Polsce przed fałszywymi SMS-ami z linkami do płatności podszywającymi się pod Pocztex
- [Komisja Europejska: Summertime (po angielsku)](https://transport.ec.europa.eu/transport-themes/summertime_en) – czas letni w UE od ostatniej niedzieli marca do ostatniej niedzieli października
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – nazwy, format GZXH…YQ, odczyt w systemie GZYL, treść i etapy zdarzeń, przyjęcie w Hongkongu, chiński wiersz platformy, zegar pekiński, osie czasu pisane z wyprzedzeniem, serwisy śledzenia jako zapas, odpowiedź dla nieznanego numeru, przekazanie Poczcie Szwajcarskiej oraz co Peek pokazuje, wstrzymuje i pomija
:::
