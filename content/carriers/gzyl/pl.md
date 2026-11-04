---
title: GZYL śledzenie przesyłki: numer YL…YQ, statusy i kontakt
description: Paczka GZYL (广州云链) z Chin: jak wygląda numer YL…YQ, co znaczą statusy i dlaczego status Delivered może się pojawić, zanim paczka dotrze.
slug: gzyl-sledzenie
published: 2026-11-03
updated: 2026-11-03
---

GZYL (广州云链, Guangzhou Yunlian) to 广州云链货运代理有限公司, firma spedycyjna z Kantonu (Guangzhou) w Chinach. Jeśli chiński sklep wysłał Twoje zamówienie przez GZYL, dostajesz numer, który zaczyna się od YL i kończy na YQ: wklej go w pole powyżej. Strona internetowa GZYL, yunlianhouyun.com, jest po chińsku, a jej wyszukiwarka paczek (小包查询) prowadzi do strony śledzenia, która działa pod samym adresem IP serwera. Czytaj ją z rezerwą: GZYL zapisuje dużą część drogi paczki z góry, więc nie każdy etap na tej stronie, nawet `Delivered`, jest skanem.

## Numer przesyłki GZYL: jak wygląda

Numery listów przewozowych GZYL to litery YL, dziesięć cyfr i litery YQ, razem 14 znaków. Przykłady z wymyślonymi cyframi:

| Numer | Co to jest |
| --- | --- |
| `YL0000000000YQ` | YL, dziesięć cyfr i YQ: numer listu przewozowego GZYL. Peek sprawdza go na stronie śledzenia GZYL. |
| `YL000000000YQ` | Tylko dziewięć cyfr: tak numer GZYL nie wygląda. Sprawdź, czy nie brakuje cyfry. |
| `GZXH0000000000YQ` | GZXH, dziesięć cyfr i YQ: Xinghe Freight (星和货运), inna firma spedycyjna. Jej przesyłki śledzi ta sama strona co przesyłki GZYL, a etapy brzmią tak samo. |
| `JXCAA0000000000YQ` | Też kończy się na YQ, jak numery wielu chińskich spedytorów, ale zaczyna się od JXC: to numer Juxi Express. |

W Peek spacje i wielkość liter nie mają znaczenia.

**Gdzie go znaleźć.** Przesyłkę u GZYL zamawia sprzedawca i to on przekazuje Ci numer. Nie możesz go znaleźć? Zobacz, [gdzie szukać numeru przesyłki](guide:find-tracking-number).

## Statusy GZYL: co znaczą

GZYL zapisuje własne etapy po angielsku, z literówkami włącznie. W kolejności, w jakiej spotyka je paczka:

| GZYL pisze | Co to znaczy dla Ciebie | W Peek |
| --- | --- | --- |
| `Order information received` | Sprzedawca przesłał GZYL dane zamówienia. GZYL nie ma jeszcze paczki. | „Zarejestrowana” |
| `Arrive at the cross border receiving point` | Paczka trafiła do GZYL: to jej pierwszy skan u tej firmy. | „Przyjęta przez przewoźnika” |
| `Goods leave the operation center` | Paczka wyjechała z centrum operacyjnego GZYL. | „W transporcie” |
| `Arrive at the international airport` | Paczka jest na lotnisku i czeka na lot. | „W transporcie” |
| `Departing from the airport` | Samolot wystartował. | „W transporcie” |
| `Item arrives at destination airport` | Paczka wylądowała w kraju docelowym. | „W transporcie” |
| `local carrier Agent Import customs clearance` | Agent celny w kraju docelowym zgłasza paczkę do odprawy przywozowej. | „W urzędzie celnym” |
| `The import customs keep it for inspection` | Celnicy w kraju docelowym zatrzymali paczkę do kontroli. | „W urzędzie celnym” |
| `Customs clearance completed` | Odprawa zakończona, paczka jedzie dalej. | „W transporcie” |
| `Internationaliy Package received at processing center` | Centrum logistyczne w kraju docelowym przejęło odprawioną paczkę. To nie jest nowe nadanie: GZYL przyjął ją dużo wcześniej. | „W transporcie” |
| `Leave the email processing center` | Paczka wyjechała z tego centrum logistycznego. | „W transporcie” |
| `Arrival sorting, distribution center` | Paczka jest w centrum dystrybucyjnym w kraju docelowym; po tych słowach GZYL podaje miejscowość. | „W transporcie” |
| `Leaving the sorting center` | Paczka wyjeżdża z sortowni; GZYL dopisuje miejscowość. | „W transporcie” |
| `Package is on the way` | Paczka jedzie dalej po kraju docelowym; GZYL dopisuje miejscowość. To nie znaczy, że kurier już ją do Ciebie wiezie. | „W transporcie” |
| `Delivered` | Doręczenie; GZYL dopisuje miejscowość. Może to być plan, a nie skan: zobacz niżej. | „Dostarczona”, z dopiskiem o stałym harmonogramie, chyba że Peek pominie ten etap (zobacz niżej) |

Miejscowość, którą GZYL dopisuje po słowach etapu, Peek pokazuje jako miejsce tego etapu. Treść etapów zostawia tak, jak napisał ją GZYL, z jednym wyjątkiem: `Delivered` widzisz w historii przesyłki jako „Dostarczona”.

**Przez Stany Zjednoczone.** W historii może się też pojawić etap `Los Angeles Processing Center has received it`, a po nim nazwa miasta w nawiasie kwadratowym: paczkę przejęło centrum logistyczne w Stanach Zjednoczonych, przez które jedzie do kraju docelowego. Peek pokazuje „W transporcie”.

**Linia po chińsku.** Gdy zamówienie nie ma jeszcze skanów, platforma, na której działa strona śledzenia GZYL, sama zapisuje `货物电子信息已经收到`, czyli: otrzymano elektroniczne dane towaru. Sprzedawca założył zamówienie, ale nikt jeszcze nie zeskanował paczki. Peek pokazuje tę linię po chińsku, ze statusem „Zarejestrowana”.

**Przez China Post.** W historii może się też pojawić `广州处理中心已收寄`, a po nim nazwa miasta w nawiasie: to przyjęcie paczki przez China Post w jej centrum przetwarzania w Kantonie, które GZYL przekazuje po chińsku. Peek pokazuje tę linię po chińsku, ze statusem „Przyjęta przez przewoźnika”.

**Godziny.** Wszystkie godziny na stronie śledzenia GZYL są w czasie pekińskim (UTC+8), także przy etapach w kraju docelowym, choć strona nie wskazuje strefy czasowej. Godzina 20:00 w Pekinie to w Polsce 13:00 zimą i 14:00 w czasie letnim, który trwa od ostatniej niedzieli marca do ostatniej niedzieli października.

**Etapy zapisane z góry.** GZYL potrafi zapisać całą drogę paczki z wyprzedzeniem, od przyjęcia aż po ostatni etap w Twoim kraju, na długo przed godzinami podanymi przy etapach, a strona śledzenia pokazuje każdy etap, gdy nadejdzie jego godzina. Tak zapisany etap to plan, a nie skan Twojej paczki. Meiteshun, który korzysta z tej samej platformy śledzenia, robi tak samo. Plan GZYL kończy się potem etapem `Delivered`, który platforma dopisuje zbiorczo wieczorem dnia, który ten etap pokazuje: z datą dnia po ostatnim etapie planu, o godzinie, gdy w Pekinie pracują biura, bez względu na kraj docelowy. Serwisy śledzenia, które kopiują rekord GZYL, też mogą pokazywać takie etapy, więc `Delivered` na stronie GZYL czy w serwisie śledzenia nie dowodzi, że paczka dotarła.

## Kto doręcza paczki GZYL w Polsce

Peek nie wie, kto doręcza paczki GZYL w Polsce: etapy GZYL w kraju docelowym nie wymieniają żadnej firmy, a Peek nie przenosi śledzenia paczek GZYL do żadnego polskiego przewoźnika. Jedyna firma, u której Peek może dalej śledzić paczkę GZYL, to Poczta Szwajcarska (Swiss Post), i to tylko w Szwajcarii i Liechtensteinie.

Peek nie znalazł podawanych przez GZYL czasów dostawy ani zasad dotyczących prób doręczenia, punktów odbioru czy czasu przechowania paczki: to zasady firmy doręczającej. Zapytaj sprzedawcę, która firma doręcza Twoje zamówienie i pod jakim numerem.

## Paczka GZYL nie dochodzi albo śledzenie stoi

**Tylko `Order information received` albo `货物电子信息已经收到`.** Sprzedawca założył zamówienie, ale GZYL nie zeskanował jeszcze paczki. Jeśli przez kilka dni nic nie przybywa, zapytaj sprzedawcę, czy naprawdę ją wysłał.

**Utknęła w urzędzie celnym.** Od 1 lipca 2026 przy przesyłkach spoza UE o wartości do 150 euro nalicza się 3 euro cła za każdą pozycję, czyli rodzaj towaru w paczce. Od 1 listopada 2026 dochodzi do tego unijna opłata manipulacyjna: 2 euro za każdą pozycję, bez względu na wartość przesyłki. Kto pobiera te kwoty razem z VAT-em i ile w sumie zapłacisz, wyjaśnia poradnik o [paczce zatrzymanej przez urząd celny](guide:customs). Jeśli paczka stoi w urzędzie celnym kilka dni, daj znać sprzedawcy.

**Strona GZYL albo serwis śledzenia pokazuje więcej niż Peek.** Peek nie pokazuje etapów zapisanych z wyprzedzeniem, etapu `Delivered`, który kończy plan GZYL, ani etapów, których godzina jeszcze nie nadeszła (więcej niżej), więc dodatkowe etapy mogą być planem GZYL, a nie skanami. Kieruj się terminem dostawy podanym przez sklep, a gdy minie, napisz do sprzedawcy. Więcej w poradnikach [status przesyłki się nie zmienia](guide:tracking-not-updating) i [śledzenie paczki z Chin](guide:tracking-from-china).

**`Delivered`, a paczki nie ma.** Ten etap może być częścią planu GZYL, a nie skanem. Jeśli paczki nie doręcza Poczta Szwajcarska, Peek dopisuje pod statusem „Dostarczona”: „Statusy przewoźnika GZYL pojawiają się według stałego harmonogramu, bez podpisu. Jeśli nic nie dotarło, zapytaj sprzedawcę.” Napisz sprzedawcy, że paczka nie dotarła, i zapytaj, która firma ją doręcza i pod jakim numerem. Więcej w poradniku [paczka doręczona, a jej nie ma](guide:delivered-not-received).

**Nic nie znaleziono.** Gdy strona śledzenia GZYL nie zna numeru, pokazuje jeden wynik z Twoim numerem w kolumnie 参考号 (numer referencyjny) i wszystkimi innymi polami pustymi. Jeśli dodałeś numer YL…YQ bez wybierania przewoźnika, Peek pokazuje wtedy „Oczekiwanie na pierwszy skan przewoźnika GZYL” i dalej pyta GZYL, a po tygodniu poprosi Cię o sprawdzenie numeru. Porównaj go z wiadomością od sprzedawcy, znak po znaku.

**Zgubiona albo zwrócona.** Przesyłkę u GZYL zamówił sprzedawca, więc to od niego, albo od platformy, na której kupowałeś, żądaj zwrotu pieniędzy lub nowej paczki. Zobacz poradniki o [zgubionej paczce](guide:lost-parcel) i [zwrocie do nadawcy](guide:returned-to-sender).

## GZYL kontakt: telefon i obsługa klienta

Stan na 4 listopada 2026: strona kontaktowa (联系我们) chińskiej witryny GZYL podaje całodobową infolinię, konto WeChat i konto QQ obsługi posprzedażowej, ale nic dla odbiorców. To kanały dla klientów GZYL, czyli sprzedawców. Paczkę u GZYL zamówił sprzedawca i to jego pytaj o zamówienie, a gdy nie odpowiada, platformę, na której kupowałeś.

## Śledzenie paczki GZYL w Peek

[Peek](/) nie rozpozna numeru GZYL z całą pewnością po samym kształcie: YL, dziesięć cyfr i YQ to tylko słaba wskazówka. I tak go wklej: Peek śledzi ponad 3500 przewoźników. Gdy dodajesz numer, Peek pyta stronę śledzenia GZYL („Sprawdzanie usług śledzenia…”) i pokazuje „GZYL ma tę przesyłkę.”, jeśli GZYL zna numer. Jeśli nie, kliknij „Zmień” i wybierz GZYL w sekcji „Pasują do numeru” (znajdziesz go też, wpisując 广州云链), a przy dodanej już przesyłce użyj „Zmień przewoźnika”. Po numerze referencyjnym sprzedawcy też śledzisz, ale tylko gdy sam wybierzesz GZYL: kliknij „Zmień” (albo „Zmień przewoźnika”) i wyszukaj GZYL. Peek pokaże wtedy numer, pod którym GZYL prowadzi zamówienie, przy napisie „GZYL śledzi przesyłkę pod numerem”. Peek usuwa jednak myślniki, więc numeru referencyjnego z myślnikami przez Peek nie znajdziesz: poproś sprzedawcę o numer YL…YQ.

Peek czyta stronę śledzenia GZYL i pokazuje status, każdy etap z jego miejscem i kraj docelowy, gdy rekord GZYL go podaje. Godziny odczytuje w czasie pekińskim i pokazuje w strefie czasowej Twojego urządzenia. Nie pokazuje nazwisk, adresu, kodów pocztowych, wagi, opłat, podpisu ani potwierdzenia doręczenia, nawet jeśli rekord GZYL je zawiera. Przewidywanej daty doręczenia też nie ma, bo GZYL jej nie podaje.

Peek pomija każdy etap zapisany ponad dwanaście godzin przed godziną, którą nosi, i każdy etap, którego godzina w czasie pekińskim jeszcze nie nadeszła. Jeśli zamówienie ma etapy zapisane z góry, Peek pomija też `Delivered`, nawet zapisany w dniu, który pokazuje. Status ustala na podstawie etapów, które zostawia, a nie ogólnego statusu w rekordzie. Gdy Peek pominie wszystkie etapy zamówienia, przesyłka nie ma jeszcze w Peek historii śledzenia. Peek pomija też kopie etapów, które uznaje za plan GZYL, łącznie z `Delivered`, gdy pokazują je serwisy śledzenia, nawet jeśli zostawia inne etapy zamówienia. Jeśli jednak strona GZYL nie odpowiada, Peek pyta serwisy śledzenia, a ich kopie mogą zawierać etapy, które GZYL zapisał z góry.

Poza przekazaniem paczki Poczcie Szwajcarskiej Peek zostaje przy GZYL: jeśli sprzedawca poda Ci numer firmy, która ją doręcza, dodaj go jako osobną przesyłkę. Peek sprawdza przesyłkę co 10 minut. Jeśli przez dwa dni nie ma nowych informacji (albo przez 12 godzin, gdy przesyłka wciąż ma status „Zarejestrowana” lub „Przyjęta przez przewoźnika”), Peek sprawdza ją już tylko co godzinę. Powiadomienia możesz dostawać o każdym skanie („Każdy skan”), tylko o ważnych etapach („Ważne etapy”) albo tylko o doręczeniu („Tylko doręczenie”). „Ważne etapy” obejmują dwa etapy celne GZYL i doręczenie, ale nie loty ani sortowanie. Peek nie skontaktuje się za Ciebie z GZYL ani ze sprzedawcą.

## Pytania o śledzenie GZYL

### Co to za firma GZYL (广州云链)?

To 广州云链货运代理有限公司 (Guangzhou Yunlian), firma spedycyjna z Kantonu (Guangzhou) w Chinach. Jej numery przesyłek to litery YL, dziesięć cyfr i litery YQ.

### Czy każdy numer YL…YQ to numer GZYL?

Niekoniecznie. YL, dziesięć cyfr i YQ to format numerów GZYL i żaden inny przewoźnik z katalogu Peek go nie używa, ale Peek i tak pyta stronę śledzenia GZYL, zanim uzna numer za numer GZYL. Numer z dziewięcioma albo jedenastoma cyframi nie ma formatu GZYL.

### Dlaczego GZYL pokazuje doręczenie, choć paczka nie dotarła?

Może to być część planu GZYL. GZYL zapisuje dużą część drogi paczki z wyprzedzeniem i kończy ją etapem `Delivered`, a takie etapy to plan, nie skany. Peek nie pokazuje takich etapów ze strony GZYL, łącznie z `Delivered`, a pod statusem „Dostarczona”, który pokazuje, dopisuje, że statusy GZYL pojawiają się według stałego harmonogramu. Napisz sprzedawcy, że paczka nie dotarła, i zapytaj, która firma ją doręcza.

### Kto doręcza paczki GZYL w Polsce?

Peek tego nie wie: etapy GZYL w kraju docelowym nie wymieniają żadnej firmy, a Peek nie przenosi śledzenia paczek GZYL do polskiego przewoźnika. Zapytaj sprzedawcę, która firma doręcza Twoje zamówienie i pod jakim numerem, i dodaj ten numer w Peek jako osobną przesyłkę.

:::sources
- [广州云链货运代理有限公司 (GZYL): strona główna (po chińsku)](http://yunlianhouyun.com/) – nazwa firmy, strona po chińsku, wyszukiwarka paczek (小包查询) prowadząca do strony śledzenia GZYL
- [广州云链货运代理有限公司 (GZYL): 联系我们 (kontakt, po chińsku)](http://yunlianhouyun.com/contact.html) – całodobowa infolinia, WeChat i QQ obsługi posprzedażowej
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – nazwa firmy i inne nazwy, strona śledzenia pod adresem IP, kształt numeru YL…YQ i jego słabe dopasowanie, Xinghe Freight na stronie śledzenia GZYL, wyszukiwanie po numerze lub numerze referencyjnym z myślnikami, brzmienie etapów i ich statusy, chińska linia platformy, miejscowości po etapach, zegar pekiński, etapy zapisane z góry, odpowiedź na nieznany numer, przekazanie śledzenia tylko Poczcie Szwajcarskiej, co Peek pokazuje i pomija
- [Komisja Europejska: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – cło 3 euro za pozycję przy przesyłkach do 150 euro od 1 lipca 2026, unijna opłata manipulacyjna 2 euro od 1 listopada 2026
- [Komisja Europejska: Guidance on the 3 EUR customs duty and Union handling fee](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – opłata 2 euro za każdą pozycję bez względu na wartość, czym jest pozycja
- [Komisja Europejska: Summertime](https://transport.ec.europa.eu/transport-themes/summertime_en) – czas letni od ostatniej niedzieli marca do ostatniej niedzieli października
- [Główny Urząd Miar: Zmiany czasu czy rezygnacja ze zmian czasu?](https://www.gum.gov.pl/pl/inne/portal-informacyjny-dot/4655,Zmiany-czasu-czy-rezygnacja-ze-zmian-czasu.html) – czas zimowy w Polsce UTC+1, letni UTC+2
:::
