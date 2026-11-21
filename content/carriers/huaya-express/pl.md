---
title: Śledzenie Huaya Express: numery HYYT…GZ, statusy i dostawa
description: Huaya Express (华亚运通) wysyła zamówienia z Kantonu. Sprawdź format HYYT…GZ, znaczenie skanów oraz kogo zapytać o lokalnego przewoźnika po przekazaniu.
slug: sledzenie-huaya-express
published: 2026-11-21
updated: 2026-11-21
---

Huaya Express (`华亚运通`) to marka z logo firmy `华亚运通国际货运代理有限公司` (*Huaya Yuntong International Freight Forwarding*) — spedytora z Kantonu (Guangzhou) w Chinach kontynentalnych, wysyłającego zagraniczne paczki dla sklepów internetowych. List przewozowy Huaya zaczyna się od `HYYT` i kończy na `GZ`. Wklej go w polu powyżej albo przejdź przez link `物流跟踪` (śledzenie logistyki) w górnym menu strony `hyytgz.com` do własnej wyszukiwarki Huaya. Historia w Huaya urywa się z chwilą przekazania paczki lokalnemu przewoźnikowi w Twoim kraju lub po kilku jego skanach (w USA czasem aż do doręczenia), nie podając nazwy tej firmy — z wyjątkiem linii przez [DHL](carrier:dhl) w Niemczech, gdzie kopiuje skany DHL, szwedzkiego wpisu o Early Bird oraz linii do Meksyku, gdzie kopiuje skany J&T Express.

## Numer śledzenia Huaya Express: jak wygląda

Huaya nie publikuje wzoru swoich numerów. Układ rozpoznawany przez Peek składa się z `HYYT`, dwunastu cyfr i `GZ` (zmyślone przykłady):

| Numer (przykładowy) | Co oznacza |
| --- | --- |
| `HYYT482910573617GZ` | `HYYT`, dwanaście cyfr i `GZ`: format listu przewozowego Huaya Express. |
| `HYYT48291057317GZ` | Jedenaście cyfr: przy kopiowaniu uciekła jedna cyfra. Skopiuj numer ponownie z wiadomości od sprzedawcy. |
| `RR482910576CN` | Numer pocztowy zakończony na `CN`: śledzi go [China Post](carrier:china-post), a nie Huaya. |

Spacje oraz wielkość liter nie mają w Peek znaczenia.

**Skąd wziąć numer.** Huaya obsługuje sprzedawców: strona główna wymienia sklepy Shopify, Vova, DHgate (`敦煌`) oraz sprzedawców z Facebooka. Numer przekazuje więc sklep, w którym kupiłeś towar; jeśli go nie widzisz, zajrzyj do poradnika [jak znaleźć numer śledzenia](guide:find-tracking-number). Wyszukiwarka Huaya przyjmuje też numer referencyjny zamówienia ze sklepu (`参考号`), wpisany dokładnie tak, jak podał go sprzedawca, łącznie z myślnikami.

**Link do śledzenia z adresem numerycznym.** Własna wyszukiwarka Huaya nie działa pod domeną `hyytgz.com`, lecz pod adresem numerycznym przez zwykłe HTTP (`trackIndex.htm` z parametrem `documentCode=` i numerem) — taki link możesz wkleić prosto do Peek. Natomiast link `物流跟踪` w stopce strony otwiera zewnętrzny serwis 17TRACK.

## Statusy śledzenia Huaya Express — co oznaczają

Wyszukiwarka Huaya ma wersję chińską i angielską. Znane w Peek wpisy są głównie po angielsku, poza przyjęciem w magazynie oraz wybranymi krokami dotyczącymi lotniska, lotu, odprawy celnej i wydania partnerowi po chińsku, a także jednym komunikatem po szwedzku w Szwecji. W typowej kolejności:

| Huaya podaje | Co to oznacza dla Ciebie | Peek pokazuje |
| --- | --- | --- |
| `Shipping Label Created` | Utworzono etykietę przewoźnika doręczającego w Twoim kraju: same dane zamówienia, Huaya nie ma jeszcze paczki. | „Zarejestrowana” |
| `Shipment not yet received or processed` | Wiersz przewoźnika końcowego przekazany zanim paczka dotarła do punktu przyjęć Huaya: również same dane. | „Zarejestrowana” |
| `Package received & scanned at warehouse`, `到达收货点` | Huaya przyjęła paczkę w magazynie w Kantonie w Chinach kontynentalnych: pierwszy skan w rękach Huaya (`到达收货点` oznacza dotarcie do punktu odbioru). | „Przyjęta przez przewoźnika” |
| `Package departed from the warehouse` | Przesyłka opuściła magazyn w Kantonie. | „W transporcie” |
| `货物发往机场途中`, `货物到达机场` | W drodze na lotnisko oraz przybycie na lotnisko w Chinach kontynentalnych, przed odprawą eksportową. | „W transporcie” |
| `Customs export clearance completed`, `国内清关完成` | Odprawa eksportowa w Chinach kontynentalnych zakończona; kontrola importowa w Twoim kraju nastąpi po przylocie. | „W transporcie” |
| `安排航班中`, `Package handed over to airline` | Rezerwacja lotu oraz przekazanie przesyłki linii lotniczej. | „W transporcie” |
| `Shipment Announced` | Huaya zaanonsowała paczkę przewoźnikowi w kraju docelowym, który jeszcze jej nie odebrał. | „W transporcie” |
| `Package in transit to destination country`, `航班起飞，预计航班落地时间(…)` | W locie do kraju docelowego (chiński wpis podaje w nawiasie planowany dzień lądowania). | „W transporcie” |
| `Package arrived at destination country` | Dotarła do kraju docelowego. | „W transporcie” |
| `航班落地，清关中` | Samolot wylądował, trwa odprawa celna importowa. | „W urzędzie celnym” |
| `Customs import clearance completed, awaiting pickup by local carrier`, `清关完成` | Urząd celny Twojego kraju zwolnił paczkę; lokalny przewoźnik musi ją jeszcze odebrać. | „W transporcie” |
| `It has been handed over to the service provider.`, `已交仓`, `Preparing for delivery` | Wydano lokalnemu przewoźnikowi (lub do jego magazynu) albo trwa przygotowanie do wydania kurierowi. Huaya nie podaje nazwy firmy ani nowego numeru; dalej pojawiają się skany amerykańskiego partnera (`Arrived at Origin Hub` koło Chicago O'Hare, `Departed Origin Hub`, `Arrived at Destination Hub`, `Departed Destination Hub`, `Arrived At Warehouse`, a potem `Out for Delivery` jako „W doręczeniu” i `Delivered` jako „Dostarczona”) albo kopiowane do Niemiec skany [DHL](carrier:dhl), albo kopiowane do Meksyku skany J&T Express. Peek zna też inne angielskie wpisy o doręczeniu, których kraju żadne źródło nie podaje (`Arrived at the regional hub`, `Departed from the regional hub`, `Arrived at the sorting center`, `Departed from the sorting center`, `Arrived in your region`, `Out for delivery with the courier` jako „W doręczeniu”). | „W transporcie” |
| `Din leverans ?r bokad med Early Bird.` | Po szwedzku „Twoja dostawa jest zarezerwowana w Early Bird” (`ä` w `är` wyświetla się jako `?`): w Szwecji doręcza Early Bird. | „W transporcie” |

Godziny w Huaya nie mają strefy czasowej: są podawane w czasie pekińskim (`UTC+8`), z wyjątkiem skopiowanych skanów DHL oraz amerykańskich skanów w miejscowościach USA, które zachowują czas lokalny miejsca skanowania, a także skopiowanych do Meksyku skanów J&T Express w czasie miasta Meksyk. Dla nieznanego numeru kolumna `参考号` (referencja) powtarza numer, a reszta wiersza pozostaje pusta.

## Kto doręcza paczki Huaya Express?

Poza kopiowanymi skanami [DHL](carrier:dhl) na trasie do Niemiec, wzmianką o Early Bird w Szwecji i kopiowanymi skanami J&T Express na trasie do Meksyku historia Huaya nie wymienia firmy doręczającej ani drugiego numeru śledzenia. Strona Huaya też tego nie rozstrzyga, bo opisy linii dla sprzedawców przeczą same sobie: karta linii do Wielkiej Brytanii (`ywfw.php?id=21`) kończy się amerykańskim opisem USPS, a na karcie linii do Australii (`ywfw.php?id=27`) paczka leci do USA dla USPS. Faktycznego doręczyciela zna sprzedawca — poproś go o nazwę firmy i jej numer śledzenia. O opłatach importowych przeczytasz w poradniku [paczka zatrzymana przez urząd celny](guide:customs).

## Paczka Huaya Express utknęła albo nie dociera

**Ostatni wpis `It has been handed over to the service provider.`.** Wyszukiwarka Huaya może już nic więcej nie dopisać, bo przesyłka jedzie dalej pod własnym numerem lokalnego kuriera. Zapytaj sprzedawcę o nazwę przewoźnika i ten numer ([śledzenie paczek z Chin](guide:tracking-from-china)).

**Cisza po odprawie importowej.** Odprawa celna jest zakończona, a paczka czeka na odbiór przez lokalnego przewoźnika. Jeśli przez kilka dni nie pojawia się przekazanie, powiadom sprzedawcę ([dlaczego śledzenie się nie aktualizuje](guide:tracking-not-updating)).

**Brak wyników.** Ponieważ dla nieznanego numeru Huaya zwraca pusty wiersz z powtórzoną referencją, Peek traktuje to jako zgłoszoną przesyłkę przed pierwszym skanem i wyświetla „Oczekiwanie na przewoźnika Huaya Express” bez prośby o sprawdzenie numeru (przy wyszukaniu bez konta sprawdza przez pierwsze 6 godzin, potem po otwarciu linku oraz do 30 dni przy włączonych powiadomieniach). Sam porównaj wpisany kod z wiadomością ze sklepu (`HYYT`, dwanaście cyfr, `GZ`) i upewnij się u sprzedawcy, że nadał paczkę przez Huaya.

**Opóźniona, zagubiona lub zwrócona.** Warunki reklamacyjne Huaya są pisane dla sklepów nadających, a nie dla odbiorców. Poproś sprzedawcę o ponowną wysyłkę lub zwrot pieniędzy, a gdy przestanie odpowiadać, otwórz spór na platformie zakupowej ([zagubiona paczka](guide:lost-parcel), [zwrot do nadawcy](guide:returned-to-sender), [dostarczona, ale nieotrzymana](guide:delivered-not-received)).

## Kontakt i obsługa klienta Huaya Express

Dane firmowe podane wyłącznie po chińsku na `hyytgz.com`:

- **Telefon**: `18039073131`, opisany jako firmowa infolinia serwisowa (`公司服务热线`). To chiński numer komórkowy w Chinach kontynentalnych (z zagranicy dodaj `+86`); brak informacji o godzinach i językach obsługi.
- **E-mail**: `2065332407@qq.com`, adres QQ Mail oznaczony jako poczta firmowa (`公司邮箱`).
- **Formularz**: podstrona `投诉建议` (`message.php`, skargi i sugestie) prosi o imię i nazwisko, telefon komórkowy, e-mail oraz treść wiadomości.
- **Siedziba**: dzielnica Baiyun w Kantonie (`广州市白云区`), Chiny kontynentalne.

Podstrony `关于我们` (`about.php`) i `服务网点` (`outlets.php`) zawierają jedynie nagłówek bez treści, a żadna strona nie jest przeznaczona dla odbiorców.

**Uwaga na fałszywe SMS-y.** Strona firmowa Huaya Express to `hyytgz.com`. Zanim klikniesz link z wiadomości SMS lub e-mail, przeczytaj [fałszywe SMS-y o paczkach](guide:delivery-scam-texts). W sprawach zamówienia pisz najpierw do sklepu.

## Śledzenie paczki Huaya Express w Peek

Wklej numer `HYYT…GZ` albo link do wyszukiwarki z `documentCode=`, a [Peek](/) od razu przypisze go do Huaya Express („Wykryty przewoźnik”) — żaden inny przewoźnik w katalogu nie używa takiego wzoru. Aby wyszukać po referencji zamówienia ze sklepu (wśród ponad 3 500 przewoźników), wpisz ją i wybierz Huaya Express przez „Zmień” (znajdziesz go też po `华亚运通` lub `GZHY`, a dla dodanej paczki przez „Zmień przewoźnika”): Peek pokaże wtedy również właściwy numer Huaya, ale usuwa myślniki — referencje z myślnikami sprawdzaj bezpośrednio w wyszukiwarce Huaya.

**Skany, strefy czasowe i etapy zapisane z wyprzedzeniem.** Peek odczytuje własny tracker Huaya, pokazuje każdy krok z miejscem i krajem docelowym, odrzuca dane osobowe (nazwiska, adresy, kody pocztowe, podpisy, zdjęcia, wagę) i nie wyświetla przewidywanej daty dostawy (Huaya jej nie podaje). Przelicza godziny z czasu pekińskiego (`UTC+8`) — a przy skopiowanych skanach DHL i skanach w miastach USA z tamtejszego czasu lokalnego, przy skanach J&T Express z czasu miasta Meksyk — na strefę Twojego urządzenia („Godziny w twojej strefie czasowej: …”). Wiersze z przyszłą godziną w Pekinie Peek trzyma do tego momentu poza skanami. Peek wstrzymuje też wpisy utworzone ponad 12 godzin przed widniejącą przy nich godziną lub ponad 6 godzin przed nią w tej samej sekundzie co taki wpis (prognozy zamiast skanów). Historia zaczyna się wtedy, aż do doręczenia lub zwrotu paczki, od zdania „Inne strony mogą pokazywać więcej etapów, ale to tylko prognozy.”, a „Pokaż” wyświetla te wpisy na szaro, każdy z dopiskiem „Prognoza:” i dniem. Takie wpisy nigdy nie liczą się jako skan, nie zmieniają statusu ani nie wysyłają powiadomień.

**Brak automatycznego przełączenia i powiadomienia.** Ponieważ rekord Huaya nie wskazuje obsługiwanego doręczyciela ani drugiego numeru, Peek pozostaje przy wyszukiwarce Huaya; dodaj numer lokalnego kuriera od sprzedawcy jako drugą przesyłkę. Peek sprawdza zmiany nawet co 10 minut przy otwartej paczce lub w dniu dostawy z włączonymi powiadomieniami oraz nawet co 2 minuty od 6:00 czasu lokalnego w dniu „W doręczeniu” (poza tym i po 12 godzinach na etapie „Zarejestrowana” / „Przyjęta przez przewoźnika” lub dwóch spokojnych dniach — co godzinę). Spośród własnych wierszy Huaya tylko `航班落地，清关中` trafia jako „W urzędzie celnym” do „Ważne etapy”, natomiast „Tylko doręczenie” zadziała wyłącznie przy kopiowanych skanach DHL, J&T Express lub partnera z USA — wybierz więc „Każdy skan” albo „Ważne etapy”. Peek nie skontaktuje się za Ciebie z Huaya ani ze sprzedawcą.

## Najczęstsze pytania o śledzenie Huaya Express

### Czym jest Huaya Express (`华亚运通`)?

To marka firmy `华亚运通国际货运代理有限公司` (*Huaya Yuntong International Freight Forwarding*), spedytora z Kantonu w Chinach kontynentalnych obsługującego sklepy internetowe głównie przez [China Post](carrier:china-post), własne linie z odprawą i opłaconym podatkiem do USA i Europy, linie dla towarów wrażliwych oraz cztery wielkie firmy kurierskie. Jej numery mają postać `HYYT`, dwanaście cyfr i `GZ`.

### Dlaczego śledzenie zatrzymało się na `It has been handed over to the service provider.`?

Ponieważ w tym miejscu kończy się odcinek Huaya: przewoźnik w Twoim kraju przejął paczkę pod własnym numerem, którego wpis Huaya nie ujawnia. Nazwę firmy i drugi numer uzyskasz od sprzedawcy.

### Co oznaczają szare wpisy w Peek?

To kroki wpisane do systemu ponad 12 godzin przed podaną godziną skanu (lub ponad 6 godzin wcześniej w tej samej sekundzie) — automatyczne prognozy, a nie potwierdzone skany, nawet gdy mówią o doręczeniu.

### Z kim się kontaktować, gdy paczka Huaya Express zaginie?

Ze sklepem, w którym złożono zamówienie: to on ma umowę z Huaya, a zasady reklamacji dotyczą nadawców. Huaya podaje kontakt wyłącznie po chińsku.

:::sources
- [Huaya Express: 华亚运通国际货运代理有限公司 (strona główna)](http://hyytgz.com/) – nazwa firmy i logo, obsługiwane platformy, główne usługi, linki 物流跟踪 (własny tracker i 17TRACK), infolinia 18039073131, e-mail 2065332407@qq.com i adres w Baiyun (Kanton)
- [Huaya Express: 欧洲双清包税小包 (linie do Europy i Wielkiej Brytanii)](http://hyytgz.com/ywfw.php?id=21) – karty linii brytyjskich z opisem USA kończącym się na USPS oraz zasady reklamacji dla nadawców
- [Huaya Express: 澳大利亚专线小包 (linia do Australii)](http://hyytgz.com/ywfw.php?id=27) – linia australijska z przelotem do USA dla USPS oraz warunki dla nadawców
- [Huaya Express: 投诉建议 (skargi i sugestie)](http://hyytgz.com/message.php) – pola formularza (imię, telefon, e-mail, wiadomość)
- [Huaya Express: 关于我们 (o nas)](http://hyytgz.com/about.php) – pusta podstrona
- [Huaya Express: 服务网点 (oddziały)](http://hyytgz.com/outlets.php) – pusta podstrona
- [Universal Parcel Scraper: Huaya Express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/huaya-express) – układ HYYT…GZ, aliasy, link trackIndex.htm, statusy angielskie/chińskie/szwedzkie, skany DHL i USA, strefy czasowe oraz wpisy zapisane z wyprzedzeniem
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – rozpoznawanie w katalogu, wyszukiwanie po referencji i prezentacja w Peek
:::
