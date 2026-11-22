---
title: Śledzenie Japan Post: numery EMS i znaczenie statusów
description: Śledzenie paczki lub EMS Japan Post z Japonii: format numeru JP, znaczenie statusów, kto doręcza w twoim kraju, cło i kontakt.
slug: sledzenie-japan-post
published: 2026-11-22
updated: 2026-11-22
---

Japan Post (日本郵便, *Japan Post Co., Ltd.*) to japoński operator pocztowy należący do Japan Post Holdings, posiadający 24 115 urzędów pocztowych według stanu na 31 marca 2026 r. Wysyła za granicę EMS, *International Air Packets* i paczki międzynarodowe, a w Japonii paczki Yu-Pack. Śledź przesyłkę w serwisie [Track & Trace Service](https://trackings.post.japanpost.jp/services/srv/search/input?locale=en) Japan Post (do dziesięciu numerów naraz) lub wklej numer powyżej. Kupiłeś w japońskim sklepie? Zapytaj sprzedawcę o numer.

## Numer śledzenia Japan Post: jak wygląda

Przesyłka międzynarodowa z Japonii ma 13 znaków: dwie litery, dziewięć cyfr i `JP`. Ostatnia z dziewięciu cyfr jest cyfrą kontrolną. Z wymyślonymi cyframi:

| Format (wymyślony) | Co oznacza |
| --- | --- |
| `EN482917367JP` | EMS, *International Air Packet*, paczka międzynarodowa lub list polecony z Japonii. |
| `482917364823` | 12 cyfr: przesyłka krajowa w Japonii, np. paczka Yu-Pack. |
| `48291736485` | 11 cyfr: również przesyłka krajowa — długość podawana przez tracker Japan Post dla listów i paczek. |

Dla przesyłek zagranicznych tracker Japan Post przyjmuje tylko taki format, nie numery jak `CNG` czy `LP` z 14 cyframi. Numerem śledzenia nie jest też kod kreskowy zaczynający się od `U` na małej paczce lub formularzu celnym (CN22): taka przesyłka idzie bez śledzenia. Numer zakończony innymi literami, np. `US`, pochodzi z poczty innego kraju. Brak numeru? Zobacz [gdzie znaleźć numer śledzenia](guide:find-tracking-number).

## Znaczenie statusów śledzenia Japan Post

Angielski tracker Japan Post pokazuje najstarszy skan na górze wraz z datą, godziną, urzędem i prefekturą lub krajem. Opisy znane Peekowi, w przybliżonej kolejności:

| Japan Post wyświetla | Co to oznacza dla ciebie |
| --- | --- |
| `Posting/Collection` | Nadawca nadał przesyłkę lub Japan Post ją odebrał. |
| `En route` | W drodze między placówkami Japan Post. |
| `Arrival at outward office of exchange` | Dotarła do urzędu wymiany wysyłającego pocztę za granicę. |
| `Dispatch from outward office of exchange` | Opuściła Japonię (lub kraj nadania w przypadku przesyłek do Japonii). |
| `Arrival at inward office of exchange` | Dotarła do urzędu wymiany w kraju docelowym. |
| `Item presented to import Customs`, `In Customs` | W urzędzie celnym kraju docelowego. |
| `Item held at inward Office of Exchange` | Zatrzymana w urzędzie wymiany w kraju docelowym. |
| `Item returned from import Customs` | Urząd celny oddał ją poczcie — rusza dalej. |
| `Departure from inward office of exchange` | W drodze do doręczenia. |
| `Item out of sorting centre` | Opuściła sortownię w kraju docelowym. |
| `Arrival`, `Processing at delivery Post Office` | W urzędzie pocztowym odpowiedzialnym za doręczenie. |
| `Item held at delivery depot/delivery office` | W urzędzie doręczeniowym; wpis nie mówi jeszcze, czy można ją odebrać. |
| `Item out for physical delivery` | W doręczeniu. |
| `Final delivery` | Doręczona. |
| `Final delivery - Collected at counter` | Odebrana w okienku urzędu pocztowego w Japonii. |
| `Retention` | Przechowywana w urzędzie pocztowym w Japonii po nieudanej próbie — do odbioru lub do wybranego dnia; wpis tego nie rozróżnia. |
| `Returned to sender` | Wraca do nadawcy. |

Przesyłki przychodzące do Japonii mają trzy dodatkowe wpisy: `Transported in bond` oznacza przewóz pod nadzorem celnym do właściwego urzędu wymiany; `Arrived in bond` — dotarcie na miejsce. Odprawa trwa aż do pojawienia się `Dispatched from Exchange Office`. Więcej w [znaczenie statusów śledzenia](guide:tracking-statuses).

## Paczki Japan Post za granicą: kto doręcza i cło

Po wpisie `Dispatch from outward office of exchange` przesyłkę przejmuje poczta twojego kraju. Dla EMS Japan Post odsyła na stronę tej poczty, gdzie śledzenie działa szybciej — np. [USPS](carrier:usps), Parcelforce w Wielkiej Brytanii, An Post, [Canada Post](carrier:canada-post), [Australia Post](carrier:australia-post), [Chronopost](carrier:chronopost) we Francji i NZ Post. Użyj tego samego numeru `JP`. Próby doręczenia i odbiór podlegają wtedy zasadom twojej poczty: zobacz [nieudane doręczenie](guide:missed-delivery).

**Oznaczona jako doręczona.** *International Air Packet* trafia do skrzynki bez podpisu (o ile nie ma opłat) i widnieje jako doręczona. Japan Post ostrzega też, że nieodebrany EMS do Wielkiej Brytanii lub Francji może wyglądać na doręczony, czekając w najbliższym urzędzie pocztowym. EMS do Niemiec może pokazać zwrot lub doręczenie, będąc u agenta celnego albo innego przewoźnika. Ponieważ doręczała twoja poczta, najpierw zapytaj ją: zobacz [doręczona, ale nieotrzymana](guide:delivered-not-received).

**Stany Zjednoczone.** Od 15 października 2026 r. Japan Post ponownie przyjmuje przesyłki do USA niemal we wszystkich urzędach. Towary o wartości do 2500 USD muszą być najpierw zarejestrowane w Zonos lub SafePackage (zalecanych przez Japan Post) z opłaconymi z góry cłami i podatkami; etykieta ma wtedy oznaczenie DDP i 13-cyfrowy numer deklaracji. Do 19 stycznia 2027 r. prezenty między osobami prywatnymi do 100 USD są zwolnione. Powyżej 2500 USD płaci importer w USA.

**Cło w UE.** Japonia leży poza UE, więc paczka przechodzi odprawę celną w twoim kraju. Od 1 lipca 2026 r. towary kupione online spoza UE w zamówieniach do 150 € podlegają unijnemu cłu 3 € za pozycję. Od 1 listopada 2026 r. obowiązuje też unijna opłata manipulacyjna 2 € za pozycję, niezależnie od wartości. Pozycja oznacza towary w paczce o tej samej klasyfikacji taryfowej, opisie oraz — gdy wymagane — pochodzeniu. Poza UE obowiązują przepisy krajowe: zobacz [przesyłki zatrzymane na cle](guide:customs).

## Odbiór paczki Japan Post w Japonii

**Cło.** Przesyłki z zagranicy przechodzą odprawę w jednym z sześciu urzędów wymiany w Japonii. Należne cło płacisz przy odbiorze lub na poczcie na podstawie zawiadomienia doręczonego przez Japan Post. Towary powyżej 200 000 ¥ wymagają zgłoszenia przywozowego.

**Nieudane doręczenie.** Paczka z zagranicy czeka 15 dni w urzędzie doręczeniowym lub miesiąc, jeśli należne jest cło: odbierz ją tam albo zamów ponowne doręczenie. Angielski formularz Japan Post przyjmuje 6-cyfrowy lub 11–13-cyfrowy numer z awiza i doręcza tylko do domu; dla innego miejsca zadzwoń pod `0570-046-111`. Zobacz [nieodebrane paczki](guide:uncollected-parcel).

## Śledzenie Japan Post bez zmian, zaginięcie lub zwrot

**Cisza po opuszczeniu Japonii.** Kolejne skany pochodzą z twojej poczty krajowej. Japan Post ostrzega, że dane z zagranicy bywają niepełne lub opóźnione i mogą wyświetlać się nie po kolei z powodu różnicy stref. Sprawdź śledzenie swojej poczty tym samym numerem: zobacz [dlaczego śledzenie przestaje się aktualizować](guide:tracking-not-updating).

**Brak jakiegokolwiek śledzenia.** *Small Packets* go nie mają, a od 31 grudnia 2025 r. Japan Post nie przyjmuje już małych paczek z towarami jako poleconych. Mała paczka ze śledzeniem idzie jako *International Air Packet*. Poczta naziemna/morska jest śledzona wyłącznie jako paczka międzynarodowa.

**Zaginiona lub uszkodzona.** Nadawca składa wniosek o poszukiwanie na formularzu w urzędzie nadania (nie telefonicznie): do 6 miesięcy dla poleconych, ubezpieczonych i paczek międzynarodowych; przy EMS termin zależy od kraju docelowego. Odszkodowanie obejmuje listy polecone i ubezpieczone, paczki międzynarodowe oraz EMS (bezpłatnie do 20 000 ¥, ubezpieczenie do 2 mln ¥); *International Air Packet* nie ma ochrony. Przy zakupie online skontaktuj się ze sklepem: zobacz [zaginione paczki](guide:lost-parcel). Jeśli paczka przyszła uszkodzona lub niekompletna, zachowaj wszystko i zgłoś w lokalnym urzędzie pocztowym: zobacz [uszkodzone paczki](guide:damaged-parcel).

**Zwrócona.** Japan Post uprzedza, że błędny wpis zwrotu za granicą może zostawić `Returned to sender` po doręczeniu; w takim przypadku wpis wskazuje kraj dostawy zamiast prefektury. Japan Post prosi też nadawców, by nie wysyłali zamówień IOSS poniżej 150 € do Austrii, Belgii, Danii, Finlandii, Francji, Luksemburga, Niemiec ani Portugalii, gdyż tamtejsze urzędy celne lub poczty mogą je odesłać. Zobacz [zwrócona do nadawcy](guide:returned-to-sender).

## Obsługa klienta i telefon Japan Post

Numery Japan Post (niedostępne z zagranicy):

- **Customer Service Center:** `0120-5931-55` (bezpłatny) lub `0570-046-666` z telefonu komórkowego (płatny).
- **Po angielsku:** `0570-046-111` (płatny), także do zamawiania ponownego doręczenia.
- **Godziny:** codziennie 8:00–21:00 czasu japońskiego.

Spoza Japonii skontaktuj się z nadawcą lub sklepem, który może złożyć wniosek o poszukiwanie w urzędzie nadania. Japan Post zaznacza, że nie udziela informacji o śledzeniu wykraczających poza to, co widać na stronie.

## Śledzenie paczki Japan Post w Peek

[Peek](/) obsługuje ponad 4500 przewoźników i od razu rozpoznaje dwie litery, dziewięć cyfr oraz `JP` jako Japan Post, gdy cyfra kontrolna się zgadza („Wykryty przewoźnik”); „Zmień” pozwala wybrać innego. Przy błędnej cyfrze kontrolnej wyświetla „Cyfra kontrolna tego numeru się nie zgadza.”: sprawdź wiadomość ze sklepu lub wybierz „Śledź tak, jak wpisano”. Linki do trackera Japan Post również działają. Numer zaczynający się od `U` nie jest przypisywany Japan Post ani tam sprawdzany, bo Japan Post go nie śledzi.

12-cyfrowy numer krajowy nie należy wyłącznie do Japan Post. Wklej go mimo to: Peek pyta pasujących przewoźników (Yamato i Japan Post w pierwszej kolejności przy wyszukiwaniu z Japonii) i wyświetla „Japan Post ma tę przesyłkę.”, gdy Japan Post go zna. Przy 11 cyfrach Peek nie pyta Japan Post — wybierz go przez „Zmień”. Przypisana do Japan Post przesyłka pokazuje „Oczekiwanie na przewoźnika Japan Post” do pierwszego skanu (lub „Sprawdź numer śledzenia”, jeśli po dwóch dniach Japan Post nadal nie zna numeru).

Peek pokazuje każdy skan po angielsku z urzędem i miejscem (poza `Returned to sender`, wyświetlanym jako „Zwrócona nadawcy”), przypisując zdarzenia do swoich etapów („W transporcie”, „W urzędzie celnym” itd.). Pomija kolumnę Details Japan Post, w której czasem figuruje powód taki jak nieznany adres. Czasy skanów odczytywane są według zegara wskazanego miejsca (lub sąsiednich skanów) i wyświetlane w twojej strefie („Godziny w twojej strefie czasowej:” i jej nazwa). Dla krajowej przesyłki z dniem dostawy wybranym przez nadawcę Peek pokazuje ten dzień i okno godzinowe jako przewidywaną dostawę do czasu doręczenia lub upływu dnia.

Z wielu krajów Peek pyta tym samym numerem `JP` także twoją pocztę krajową: USPS w USA, [Royal Mail](carrier:royal-mail) w Wielkiej Brytanii (Parcelforce dla numerów od `E`, jak podaje Japan Post dla EMS), An Post w Irlandii, Canada Post, Australia Post, NZ Post, [DHL](carrier:dhl) w Niemczech, [La Poste](carrier:la-poste) we Francji oraz pocztę każdego innego kraju UE poza Holandią. Przełącza się na tę pocztę po pojawieniu się świeżych skanów, zachowując historię Japan Post. Z Korei, Chin, Hongkongu i Singapuru nie pyta innej poczty. W drugą stronę, gdy inna poczta wskaże Japonię jako cel (lub żaden, gdy śledzisz z Japonii), Peek pyta Japan Post tym numerem, chyba że zaczyna się od `U`.

Gdy masz przesyłkę otwartą w Peek — albo w dniu dostawy przy włączonych powiadomieniach — Peek pyta bezpośrednio Japan Post nawet co 10 minut, a w dniu wyjścia do doręczenia nawet co 2 minuty od 6:00 czasu ostatniego skanu (czasu japońskiego w Japonii, lub od 30 minut przed wybranym krajowym oknem czasowym), dopóki tracker Japan Post odpowiada (po przejęciu przez inną pocztę obowiązuje jej rytm sprawdzania); w pozostałym czasie oraz po dwóch dniach bez zmian (12 godzinach, gdy przesyłka nadal ma status „Zarejestrowana” lub „Przyjęta przez przewoźnika”) sprawdza co godzinę. Dla powiadomień wybierz „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Po czterech dniach bez nowego skanu w drodze, poza odprawą celną i zgłoszonym problemem, Peek pokazuje datę ostatniego skanu („Brak aktualizacji od …”) i komunikat „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika Japan Post.” z linkiem „Poproś przewoźnika Japan Post o pomoc”. Peek nie może kontaktować się z Japan Post, twoją pocztą ani ze sklepem w twoim imieniu.

## Pytania o śledzenie Japan Post

### Czym jest EMS w Japan Post?

To najszybsza międzynarodowa usługa Japan Post dla dokumentów i towarów do 30 kg do ponad 120 krajów i terytoriów, ze śledzeniem. Jej numery kończą się na `JP`.

### Dlaczego moje śledzenie Japan Post się nie aktualizuje?

Jeśli ostatni wpis to `Dispatch from outward office of exchange`, paczka opuściła Japonię: sprawdź śledzenie własnej poczty tym samym numerem i daj czas na lot oraz odprawę celną.

### Czy mogę śledzić małą paczkę Japan Post?

Tylko jeśli nadano ją jako *International Air Packet*. *Small Packet* nie ma śledzenia, a kod kreskowy na `U` nie jest numerem śledzenia. Zapytaj sprzedawcę, jaką usługę wybrał.

### Jak długo trwa dostawa Japan Post?

Około 5–21 dni dla *International Air Packet*, według Japan Post. EMS jest najszybsze; kalkulator opłat i terminów na `post.japanpost.jp` podaje czas dla każdego kraju docelowego.

:::sources
- [Japan Post: Track & Trace Service](https://trackings.post.japanpost.jp/services/srv/search/input?locale=en) – śledzenie, przykłady numerów
- [Japan Post: Item numbers on labels](https://trackings.post.japanpost.jp/slipEn.html) – 12-cyfrowy numer Yu-Pack
- [Japan Post: Notices Concerning EMS Track & Trace Service](https://www.post.japanpost.jp/service/send/oversea/information/ems_search_en.html) – numery, kolejność skanów, skany za granicą, kolumna Details, zwroty, wpisy celne in-bond
- [Japan Post: Link to Overseas Post Offices](https://www.post.japanpost.jp/service/send/oversea/list/delivery/ems/link_en.html) – poczty zagraniczne dla EMS
- [Japan Post: Products and services comparative table](https://www.post.japanpost.jp/service/send/oversea/list/hikaku_en.html) – śledzenie według usługi
- [Japan Post: International Air Packet](https://www.post.japanpost.jp/service/send/oversea/list/delivery/airpacket_en.html) – doręczenie do skrzynki, czas dostawy
- [Japan Post: EMS](https://www.post.japanpost.jp/service/send/oversea/list/delivery/ems/index_en.html) – usługa, narzędzie terminów
- [Japan Post: EMS Insurance Program](https://www.post.japanpost.jp/service/send/oversea/list/delivery/ems/damage_en.html) – ubezpieczenie
- [Japan Post: Mail destined for the United States (23 October 2026)](https://www.post.japanpost.jp/service/send/oversea/information/2026/1023_01_en.html) – zasady USA
- [Japan Post: EU duty-free exemption removal (1 July 2026)](https://www.post.japanpost.jp/service/send/oversea/information/2026/0701_01_en.html) – zamówienia IOSS
- [Japan Post: Searches for undelivered items](https://www.post.japanpost.jp/question/oversea/18_en.html) – poszukiwania
- [Japan Post: Searches for EMS](https://www.post.japanpost.jp/question/oversea/138_en.html) – poszukiwania EMS
- [Japan Post: Compensation](https://www.post.japanpost.jp/question/oversea/21_en.html) – przesyłki objęte ochroną
- [Japan Post: Compensation claims](https://www.post.japanpost.jp/question/oversea/17_en.html) – uszkodzenia
- [Japan Post: Holding period for parcels from overseas](https://www.post.japanpost.jp/question/oversea/23_en.html) – okres przechowywania
- [Japan Post: Delivery Flow](https://www.post.japanpost.jp/service/send/oversea/use/flow/receive_en.html) – cło w Japonii
- [Japan Post: Application for redelivery](https://www.post.japanpost.jp/receive/redelivery_form/) – ponowne doręczenie
- [Japan Post: Yu-Pack](https://www.post.japanpost.jp/service/you_pack/index_en.html) – wybrany dzień dostawy
- [Japan Post: Inquiry by Phone](https://www.post.japanpost.jp/question/oversea/tel_en.html) – numery, godziny
- [Japan Post: Corporate Information](https://www.post.japanpost.jp/about/profile_en.html) – firma, urzędy pocztowe
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – cło 3 €, opłata manipulacyjna 2 €
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – za pozycję, definicja pozycji
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – treść skanów, pola odczytywane przez Peek, kolejna poczta
:::
