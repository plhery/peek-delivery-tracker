---
title: Śledzenie Shouwu: numer SWECN…YQ, statusy i dostawa
description: Śledź paczkę Shouwu International (守务国际) z Chin: jak wygląda numer SWECN…YQ, co oznaczają statusy i skany oraz kto doręcza przesyłki w Europie.
slug: shouwu-sledzenie
published: 2026-11-18
updated: 2026-11-20
---

Shouwu International (守务国际, w skrócie SWE) to szanghajska firma logistyczna, która wysyła paczki od sprzedawców internetowych w Chinach do Europy i Stanów Zjednoczonych, gdzie doręczeniem pod wskazany adres zajmuje się operator pocztowy albo lokalna firma kurierska. Jeśli sklep nadał twoje zamówienie przez Shouwu, numer przesyłki zaczyna się od `SWECN` i kończy na `YQ`: wklej go w polu powyżej albo w anglojęzycznej wyszukiwarce Shouwu pod adresem [admin.shshouwu.com](https://admin.shshouwu.com/track.html). Numer przekazuje sprzedawca wraz z informacją o wysyłce zamówienia — samo Shouwu nie publikuje żadnego kontaktu dla odbiorców paczek.

## Numer śledzenia Shouwu: jak wygląda

Listy przewozowe wystawiane przez Shouwu składają się z prefiksu `SWECN`, dwunastu cyfr oraz końcówki `YQ`, czyli łącznie z 19 znaków. Na przykładowych cyfrach wygląda to następująco:

| Numer | Co oznacza |
| --- | --- |
| `SWECN482910573649YQ` | `SWECN`, dwanaście cyfr i `YQ`: prawidłowy numer listu przewozowego Shouwu International. |
| `SWECN48291057364YQ` | Tylko jedenaście cyfr w środku: to nie jest układ Shouwu. Sprawdź, czy przy kopiowaniu nie uciekła jedna cyfra. |
| `JSEAA4829105736YQ` | Również kończy się na `YQ`, ale zaczyna od `JSE` i ma dziesięć cyfr: to numer [JS Express](carrier:js-express). |

Wielu innych chińskich spedytorów e-commerce także kończy swoje numery literami `YQ`, poprzedzając je własnym skrótem literowym i — w katalogu Peeka — zazwyczaj dziesięcioma cyframi. Połączenie `SWECN` oraz dwunastu cyfr wyróżnia wyłącznie przesyłki Shouwu. Spacje, myślniki ani małe litery nie przeszkadzają przy wklejaniu numeru do Peeka.

**Gdzie go znaleźć.** Jeśli numeru nie ma w wiadomości e-mail o nadaniu paczki ani na karcie zamówienia w sklepie lub na platformie zakupowej, zajrzyj do poradnika [jak znaleźć numer śledzenia przesyłki](guide:find-tracking-number).

**Sprzedawca mógł podać numer kuriera końcowego.** Na stronach swoich linii przewozowych Shouwu wyjaśnia, że nadawca sam wybiera, który numer przekazuje kupującemu: własny numer Shouwu, pozwalający śledzić paczkę na całej trasie z Chin, albo od razu numer przewoźnika doręczającego przesyłkę w kraju docelowym. Dlatego paczka obsłużona przez Shouwu może dotrzeć pod numerem europejskiej poczty lub firmy kurierskiej. Taki numer również możesz wkleić powyżej — Peek obsługuje ponad 4 500 przewoźników.

**Link do śledzenia.** Przycisk „Copy Link” na stronie śledzenia Shouwu kopiuje adres z parametrem `?q=` i numerem paczki na końcu. Taki link otwiera bezpośrednio daną przesyłkę, a w razie potrzeby możesz wkleić go w całości prosto do Peeka. Wyszukiwarka Shouwu przyjmuje jednocześnie do 40 numerów wpisywanych jeden pod drugim.

## Statusy śledzenia Shouwu i ich znaczenie

Strona śledzenia Shouwu działa po angielsku pod chińskim nagłówkiem `包裹追踪`. Dla każdej przesyłki podaje ogólny status oraz kraj przeznaczenia, a niżej czas i treść poszczególnych skanów — bez wyświetlania kolumny z nazwą miejscowości.

| Status w Shouwu | Co to oznacza dla ciebie | Co pokazuje Peek |
| --- | --- | --- |
| `Not Found` | Shouwu nie ma jeszcze żadnych danych pod tym numerem: „We seem can't identify the number.” | „Oczekiwanie na przewoźnika Shouwu International” |
| `Preparing` | Według opisu na stronie Shouwu paczka została przekazana do magazynu firmy w Chinach albo trwa tam jej ważenie i plombowanie. Status ten potrafi pojawić się jeszcze przed pierwszym skanem. | „Zarejestrowana”, a po odbiorze przez partnera lub skanie magazynowym „Przyjęta przez przewoźnika” |
| `In Transit` | Obejmuje całą drogę od przekazania przewoźnikowi przez odprawę celną aż po transport na terenie kraju docelowego. | „W transporcie” albo dokładniejszy etap wynikający z najnowszego skanu, np. „W urzędzie celnym”; pozostaje „Zarejestrowana”, dopóki widnieją wyłącznie dane zamówienia i `Carrier received the shipment` |
| `Undelivered` | Próba doręczenia zakończyła się niepowodzeniem. | „Próba doręczenia”, ewentualnie „Do odbioru” lub „Zwrócona nadawcy”, jeśli mówi o tym ostatni skan |
| `Delivered` | Przesyłka została doręczona adresatowi. | „Dostarczona” |

Samo Shouwu zaznacza na stronie, że te ogólne statusy są nadawane automatycznie przez system („auto-judged by the system”) i mają charakter wyłącznie orientacyjny („only for information reference use”). Dużo dokładniejszy obraz dają poszczególne wpisy w historii:

| Komunikat w Shouwu | Co to oznacza dla ciebie | Co pokazuje Peek |
| --- | --- | --- |
| `Shipment information received`, `Order information received. We're expecting your parcel to arrive with us.` | Sprzedawca zgłosił przesyłkę elektronicznie; Shouwu nie ma jeszcze fizycznej paczki w rękach. | „Zarejestrowana” |
| `Carrier received the shipment` | Wpis dopisywany przez Shouwu dwie godziny po danych zamówienia, na całe dni przed pierwszym skanem w magazynie — nikt jeszcze nie odebrał paczki od nadawcy. | „Zarejestrowana” |
| `Picked up by Shipping Partner` | Partner logistyczny Shouwu w Chinach odebrał paczkę od sprzedawcy; pierwszy fizyczny ruch po utworzeniu zlecenia. | „Przyjęta przez przewoźnika” |
| `Arrival processing center`, `Arrived at Transit logistics facility` | Magazyn Shouwu w Chinach przyjął przesyłkę na stan. | „Przyjęta przez przewoźnika” |
| `Package processing completed` | Obsługa magazynowa w Chinach zakończona przed przekazaniem na lot. | „Przyjęta przez przewoźnika” |
| `Item inbound in sorting center`, `Item outbound in sorting center`, `Item is in transit in Hong Kong` | Wejście i wyjście z sortowni oraz tranzyt przez Hongkong w drodze na lotnisko wylotu. | „W transporcie” |
| `Shipment started the customs clearance process at origin`, `Export Clearance` | Rozpoczęła się odprawa eksportowa w Chinach — rutynowa formalność przed wylotem; urząd celny nie zatrzymuje twojej paczki. | „W transporcie” |
| `The package is shipped from the transfer warehouse to the airport Export customs clearance in progress` | Paczka jedzie z magazynu przeładunkowego na lotnisko, podczas gdy w Chinach trwa odprawa wywozowa (to nie jest urząd celny w kraju odbiorcy). | „W transporcie” |
| `Export customs declaration completed`, `Export customs clearance completed`, `Export Released` | Chińska odprawa eksportowa zakończyła się zwolnieniem przesyłki do wywozu. | „W transporcie” |
| `The parcel has arrived at the airport and is currently being loaded onto a flight` | Przesyłka dotarła na lotnisko wylotu po odprawie eksportowej i jest ładowana do samolotu. | „W transporcie” |
| `Arrived at Shanghai Pudong Airport`, `Departed from export airport`, `The flight has departed` | Przybycie na lotnisko Szanghaj-Pudong i wylot samolotu z Chin. | „W transporcie” |
| `The ship has already set sail` | Na linii morskiej: statek wypłynął z Chin po odprawie eksportowej i płynie do portu docelowego. | „W transporcie” |
| `Arrived at airport of Destination`, `Shipment arrived in the destination country`, `Item is in transit in European` | Samolot wylądował w kraju docelowym albo paczka jest już w tranzycie na terenie Europy przed odprawą importową. | „W transporcie” |
| `NOA received` | Agent w kraju docelowym otrzymał od linii lotniczej awizo przylotu ładunku (*Notice of Arrival*); następnym krokiem jest odprawa celna. | „W transporcie” |
| `Shipment started the customs clearance process at destination`, `Customs clearance in progress`, `Currently undergoing customs clearance in the destination country`, `Currently processing import customs clearance`, `Customs clearance for imports`, `Under Clearance`, `Item has started customs clearance process` | Trwa odprawa celna importowa w kraju przeznaczenia. | „W urzędzie celnym” |
| `Custom clearance completed`, `Shipment completed the customs clearance process at destination`, `Customs clearance has been completed`, `Customs clearance in the destination country has been completed` | Urząd celny kraju docelowego zwolnił przesyłkę po odprawie. | „W transporcie” |
| `Scheduled For Delivery` | Wpis Shouwu zaraz po zwolnieniu celnym, na kilka dni przed przyjęciem w oddziale kuriera i wyjazdem w trasę: doręczenie jest dopiero zaplanowane, a nie w toku. | „W transporcie” |
| `Delivery to local courier`, `The goods have been deliverey to the final delivery service provider`, `The goods have been delivered to the carrier` | Przekazanie przesyłki lokalnemu przewoźnikowi odpowiedzialnemu za ostatnią milę — to jeszcze nie doręczenie do ciebie (pisownia „deliverey” pochodzi wprost z systemu Shouwu). | „W transporcie” |
| `Your consignment has been picked up` | Lokalny kurier odebrał paczkę z zagranicznego magazynu Shouwu po odprawie celnej; nie oznacza to odbioru od sprzedawcy (`Picked up by Shipping Partner`) ani odbioru osobistego przez adresata. | „W transporcie” |
| `Picked up`, `Arrived at FedEx location`, `Departed FedEx location` | W USA firma FedEx odebrała paczkę z magazynu Shouwu po odprawie importowej (z podaniem miasta i kodu pocztowego) i przesyła ją między swoimi oddziałami. | „W transporcie” |
| `Parcel on its way`, `arrival city`, `leave the city` | Krajowy przewóz u lokalnego partnera po zwolnieniu celnym — przyjazd do kolejnych miast i wyjazd z nich. | „W transporcie” |
| `The shipment has been processed in the destination parcel center` | DHL posortował paczkę w docelowym centrum paczkowym obsługującym twój rejon przed wydaniem kurierowi. | „W transporcie” |
| `Mis routed` | W Niemczech przewoźnik skierował paczkę do niewłaściwej sortowni i przekierowuje ją dalej — oznacza to opóźnienie, a nie blokadę, i doręczenie bywa jeszcze tego samego dnia. | „W transporcie” |
| `Out for delivery` | Przesyłka wyjechała z lokalnym kurierem do doręczenia. | „W doręczeniu” |
| `Abnormal delivery` | Kurier nie zdołał doręczyć przesyłki; powód pojawia się w osobnym wierszu w nawiasach ostrokątnych, np. `<Incorrect address.>`. | „Próba doręczenia” |
| `The recipient is unknown` | Podczas próby doręczenia przez DHL w Niemczech nie znaleziono takiego adresata pod wskazanym adresem; paczka jest kierowana do zwrotu. | „Próba doręczenia” |
| `Shipment will be returned to origin-Bad address` | DHL odsyła przesyłkę z powodu błędnego adresu: paczka jest w drodze powrotnej, ale nie dotarła jeszcze do nadawcy. Skontaktuj się ze sprzedawcą. | „Zgłoszono problem” |
| `Delivered by post man`, `This parcel has been delivered` | Lokalna poczta lub kurier doręczył przesyłkę. | „Dostarczona”, gdy ogólny status Shouwu również wskazuje `Delivered` |
| `Shipment sent directly from parcel center to business customer` | DHL doręczył paczkę bezpośrednio z centrum paczkowego do klienta firmowego; jeśli wcześniej trwał zwrot, ten sam komunikat oznacza oddanie przesyłki nadawcy. | „Dostarczona” albo „Zwrócona nadawcy” po zwrocie |

Gdy we Włoszech doręcza [iMile](carrier:imile), historia w Shouwu powtarza komunikaty iMile z nazwą centrum w nawiasach `【】` (w twojej przesyłce może pojawić się inne centrum niż `Milan center` czy `ROM 10`):

| Komunikat w Shouwu | Co to oznacza dla ciebie | Co pokazuje Peek |
| --- | --- | --- |
| `【Milan center】Received.` | Regionalne centrum iMile przyjęło przesyłkę. | „W transporcie” |
| `【Milan center】Parcel shipped to【ROM 10】.`, `【ROM 10】Arrived.` | Wysłano do stacji doręczeń iMile i przyjęto na miejscu. | „W transporcie” |
| `【ROM 10】Shipment out for delivery.` | Kurier iMile wyjechał z paczką w trasę. | „W doręczeniu” |
| `<User not at home>`, `【ROM 10】 Back To DS.` | Kurier nie zastał nikogo pod adresem i paczka wróciła do stacji doręczeń. | „Próba doręczenia” |
| `【ROM 10】Parcel shipped to【Milan Transit Center】.` | Po dwóch nieudanych próbach doręczenia stacja odsyła paczkę do centrum tranzytowego iMile. | „W transporcie” |
| `Back to Sender for reason: wrong recipient address or address related problem` | Po nieudanych próbach iMile kieruje przesyłkę do zwrotu (tutaj z powodu problemu z adresem, choć po dwukropku może widnieć inny powód) — paczka jedzie z powrotem, jeszcze nie jest u nadawcy. Napisz do sprzedawcy. | „Zgłoszono problem” |
| `【Milan Transit Center】Order Closed.` | iMile zamyka zlecenie bez doręczenia; skontaktuj się ze sklepem. | „Zgłoszono problem” |

**Gdy doręcza DPD.** Na liniach obsługiwanych przez [DPD](carrier:dpd-de) dalsza historia składa się z angielskich komunikatów DPD zakończonych kropką: `In transit.`, następnie `At parcel delivery centre.` — oddział doręczający DPD przed wyjazdem kuriera oraz ponownie po nieudanej próbie („W transporcie”) — i `Out for delivery.` („W doręczeniu”). Wpis `Unfortunately we have not been able to deliver your parcel. Consignee address not correct.` oznacza nieudaną próbę doręczenia z powodu nieprawidłowego adresu, po której paczka wraca do oddziału DPD, a nie od razu do nadawcy („Próba doręczenia”).

**Wpisy po chińsku.** W części przesyłek pojawiają się również komunikaty zapisane po chińsku, które Peek wyświetla w oryginalnym brzmieniu. `货物电子信息已经收到` (odebrano elektroniczne dane towaru) towarzyszy rejestracji zlecenia: „Zarejestrowana”. Następnie status „W transporcie” otrzymują kolejno: `申报过港中` po kodzie partii zbiorczej (zgłoszenie partii do przewozu drogowego do Hongkongu), `已到港，安排航班中` (dotarcie do portu wylotu, oczekiwanie na przydział lotu), `预计23号的航班` (zarezerwowany lot, tutaj na 23. dzień miesiąca; z dopiskiem `二程` na początku dla drugiego odcinka lotniczego), `航班已飞` oraz `二程航班已飞` (wylot samolotu lub drugiego odcinka) i `已落地` (lądowanie samolotu). `已落地，清关提货中` (po lądowaniu, trwa odprawa celna i odbiór ładunku od linii lotniczej) oznacza „W urzędzie celnym”, natomiast `清关完成` (odprawa celna zakończona) oraz `已交仓扫描` (skan przekazania do magazynu firmy doręczającej) ponownie „W transporcie”.

**Godziny przesunięte o kilka godzin.** Zaplecze systemowe Shouwu zapisuje lokalną godzinę niemal każdego skanu — także skanów linii lotniczych i kurierów za granicą — tak, jakby był to czas pekiński (`UTC+8`), a strona śledzenia przelicza ten zapis na strefę czasową twojej przeglądarki. W efekcie skan wykonany o 10:00 w Polsce pokazuje się na stronie Shouwu jako 03:00 zimą (`UTC+1`) oraz jako 04:00 w czasie letnim (`UTC+2`, od ostatniej niedzieli marca do ostatniej niedzieli października), czyli o siedem albo sześć godzin za wcześnie, przez co sam lot wygląda na krótszy o kilka godzin. Kieruj się kolejnością wierszy. Peek ustala właściwy zegar każdego zdarzenia na podstawie jego miejsca albo etapu podróży i przelicza godziny na strefę czasową twojego urządzenia; w serwisie peektracker.com oraz w aplikacjach na iPhone'a i Androida pod historią widnieje informacja „Godziny w twojej strefie czasowej: …”.

## Kto doręcza paczki Shouwu w Polsce i w Europie

Na swojej chińskojęzycznej stronie o firmie Shouwu podaje, że współpracuje bezpośrednio z operatorami USPS, Royal Mail, La Poste, DHL, DPD oraz Hermes. Karty poszczególnych tras, również po chińsku, wymieniają lokalnych doręczycieli w krajach, dla których przewoźnik publikuje własne linie:

- **Polska.** W serwisie `www.sedel.com` Shouwu **nie wymienia żadnej dedykowanej linii do Polski ani nie wskazuje polskiego przewoźnika ostatniej mili**, a sama wyszukiwarka śledzenia nie podaje nazwy firmy kurierskiej ani jej krajowego numeru poza tym, co może czasem wynikać z treści skanu. Aby ustalić, kto doręcza twoją paczkę w Polsce i pod jakim numerem krajowym, zapytaj bezpośrednio sprzedawcę.
- **Inne kraje Unii Europejskiej.** Shouwu publikuje własne linie do Niemiec — doręczane głównie przez [DHL](carrier:dhl), [Hermes](carrier:hermes-de) lub [DPD](carrier:dpd-de) zależnie od wariantu —, Francji (głównie [Colissimo](carrier:la-poste), a w części historii również Colis Privé), Holandii (głównie DHL), Szwecji (głównie Early Bird) oraz Włoch (linia ekonomiczna 10–12 dni roboczych doręczana przez UniUni i [Poste Italiane](carrier:poste-italiane), a w historiach odczytywanych przez Peeka także przez [iMile](carrier:imile)).
- **Wielka Brytania i Stany Zjednoczone.** W Wielkiej Brytanii paczki doręczają [Royal Mail](carrier:royal-mail), Yodel oraz [Evri](carrier:evri) (zapisane na stronie jako `ERVI`; linia dla towarów z bateriami idzie głównie przez Royal Mail), natomiast w USA głównie [USPS](carrier:usps), a w części przesyłek także [FedEx](carrier:fedex).

**Nieudane doręczenie.** Ponowne próby doręczenia, awiza i punkty odbioru podlegają regulaminowi lokalnego przewoźnika. Pod statusem `Undelivered` wyszukiwarka Shouwu radzi jak najszybciej skontaktować się z przewoźnikiem w celu umówienia ponownego doręczenia lub odebrać przesyłkę przed upływem terminu przechowywania, ostrzegając, że inaczej paczka „might be returned to the sender” (może zostać zwrócona do nadawcy). Jednocześnie chińskojęzyczne warunki linii do Wielkiej Brytanii i USA mówią, że niedoręczalne przesyłki nie wracają do Chin i są domyślnie niszczone. W obu przypadkach warto zareagować na awizo bez zwłoki — zobacz [nieudane doręczenie: co zrobić](guide:missed-delivery) oraz [nieodebrana paczka](guide:uncollected-parcel).

## Paczka Shouwu nie dociera lub śledzenie stoi w miejscu

**`Not Found`.** Shouwu wymienia cztery możliwe przyczyny: jeszcze nie przyjęło paczki, jeszcze jej nie zeskanowało, wpisany numer zawiera błąd albo przesyłka została nadana tak dawno, że jej historia wygasła; dodaje też, że początkowe opracowanie zajmuje zwykle kilka dni. W Peek zobaczysz wtedy komunikat „Oczekiwanie na przewoźnika Shouwu International”, a Peek będzie dalej sprawdzał numer. Jeżeli po dwóch dniach Shouwu nadal nie rozpoznaje numeru i żadne inne źródło dla niego nie odpowiada, pojawi się komunikat „Po dwóch dniach przewoźnik nadal nie zna tego numeru. Sprawdź go, wybierz innego przewoźnika lub usuń przesyłkę.”, a sprawdzanie przejdzie na tryb raz dziennie, a po tygodniu od dodania — raz w tygodniu. Sprawdź numer znak po znaku, a gdy po kilku dniach nic się nie zmieni, napisz do sprzedawcy.

**Zatrzymanie na `Preparing`, `Shipment information received` lub `Carrier received the shipment`.** Shouwu nie ma jeszcze fizycznej przesyłki albo nie wypuściło jej jeszcze ze swojego magazynu w Chinach. Zapytaj sklep, kiedy faktycznie przekazał paczkę przewoźnikowi.

**Postój w urzędzie celnym i opłaty w Polsce.** Po komunikatach `Shipment started the customs clearance process at destination`, `Customs clearance in progress`, `Currently undergoing customs clearance in the destination country`, `Currently processing import customs clearance`, `Customs clearance for imports` lub `已落地，清关提货中` przesyłka czeka na odprawę importową. Strony linii Shouwu nie precyzują, kto pokrywa należności przywozowe. W Unii Europejskiej towary kupowane przez internet spoza UE objęte są stałym cłem w wysokości 3 € za każdą kategorię towarową w przesyłkach o wartości do 150 € (od 1 lipca 2026 r.) oraz unijną opłatą manipulacyjną 2 € za kategorię towarową niezależnie od wartości paczki (od 1 listopada 2026 r.). W **Polsce**, jeśli doręczenie obsługuje [Poczta Polska](carrier:poczta-polska) i od przesyłki spoza UE należą się opłaty celno-podatkowe, Poczta Polska pobiera je przy doręczeniu przesyłki lub przed jej odbiorem. Jednocześnie w **Polsce** Poczta Polska ostrzega przed fałszywymi wiadomościami podszywającymi się pod Pocztex: Pocztex nie wysyła wiadomości SMS z linkami do płatności. Więcej wskazówek znajdziesz w poradniku [paczka zatrzymana w urzędzie celnym](guide:customs).

**Brak nowych wpisów po `Delivery to local courier`.** Krajowy kurier przejął już paczkę, ale strona Shouwu nie podaje jego numeru śledzenia. Poproś sprzedawcę o numer lokalnego przewoźnika i dodaj go do Peeka. Przeczytaj również [dlaczego śledzenie przestaje się aktualizować](guide:tracking-not-updating) oraz [śledzenie paczek z Chin](guide:tracking-from-china).

**Status doręczono, ale paczki nie ma.** Warunki linii Shouwu wskazują, że gdy strona przewoźnika końcowego pokazuje doręczenie, którego adresat nie otrzymał, Shouwu pomaga wyjaśnić sprawę, lecz nie wypłaca odszkodowania. Zgłoś więc reklamację najpierw lokalnemu przewoźnikowi, a zaraz potem w sklepie lub na platformie zakupowej. Zobacz [przesyłka oznaczona jako doręczona, ale nieodebrana](guide:delivered-not-received).

**Zagubiona lub uszkodzona przesyłka.** Zleceniodawcą Shouwu jest sprzedawca, który według warunków linii ma 30 dni od wysyłki przez Shouwu na złożenie reklamacji. Za paczkę przyjętą do magazynu Shouwu, która nie uzyskała skanu doręczenia, przewoźnik zwraca nadawcy zadeklarowaną wartość wraz z opłatą za wysyłkę maksymalnie do 200 juanów (RMB) za przesyłkę i nie wypłaca odszkodowania za uszkodzenia powstałe w transporcie. O zwrot pieniędzy albo ponowną wysyłkę wystąp bezpośrednio do sklepu lub marketplace'u, pilnując terminów ochrony kupującego. Zobacz [zagubiona paczka](guide:lost-parcel).

## Kontakt z Shouwu i obsługa klienta

Firmowa witryna Shouwu pod adresem `http://www.sedel.com/` działa wyłącznie po chińsku i jest przeznaczona dla sprzedawców zlecających wysyłki. Podaje chińskie numery telefonów komórkowych do centrali w Szanghaju i pięciu oddziałów, link do czatu QQ oraz kody QR komunikatora WeChat — nie zawiera natomiast adresu e-mail, formularza kontaktowego, obsługi po polsku ani po angielsku, ani żadnego kanału dla odbiorców paczek. Sama strona śledzenia `admin.shshouwu.com` nie podaje żadnych danych kontaktowych.

W sprawie zamówienia lub reklamacji kontaktuj się ze sprzedawcą, który nadał paczkę przez Shouwu; w sprawie samego doręczenia pod twój adres zwróć się do lokalnego przewoźnika, gdy znasz już jego numer przesyłki.

## Śledzenie paczki Shouwu w aplikacji Peek

[Peek](/) rozpoznaje numer Shouwu po samym układzie znaków — `SWECN`, dwanaście cyfr i `YQ`, którego nie używa żaden inny przewoźnik w katalogu — i odpytuje bezpośrednio wyszukiwarkę Shouwu bez zakładania konta ani podawania kodu pocztowego czy numeru telefonu.

Peek wyznacza bieżący etap przesyłki na podstawie statusu Shouwu i najnowszego skanu zgodnie z powyższymi tabelami, a oprócz kraju docelowego pokazuje też miejscowość przypisaną do poszczególnych zdarzeń, pomijaną w tabeli na stronie Shouwu. Jeżeli pole lokalizacji zawiera cyfrę (na przykład kod pocztowy), jest odrzucane. Shouwu nie udostępnia przewidywanego terminu doręczenia, dlatego Peek również nie wyświetla szacowanej daty dostawy.

Ponieważ Shouwu nie przekazuje osobno nazwy ani numeru kuriera końcowego, Peek nie pokazuje wiersza „Dostawa przez” i nie przełącza automatycznie śledzenia na lokalnego przewoźnika, nawet jeśli w treści skanów pojawiają się komunikaty iMile lub DPD. Jeśli otrzymasz od sprzedawcy numer krajowego kuriera, dodaj go w Peeku jako osobną przesyłkę.

Gdy masz otwartą kartę przesyłki lub w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza aktualizacje nawet co 10 minut, a w dniu wydania paczki do doręczenia od godziny 6:00 czasu lokalnego nawet co 2 minuty, dopóki odpowiada bezpośrednio wyszukiwarka Shouwu; w pozostałym czasie sprawdza co godzinę, podobnie jak po dwóch dniach bez nowych zdarzeń albo po 12 godzinach, gdy przesyłka nadal ma etap „Zarejestrowana” lub „Przyjęta przez przewoźnika”. Gdy odczyt odbywa się przez zewnętrzne serwisy śledzenia, sprawdzanie następuje co godzinę, co 3 godziny po dobie bez nowego skanu oraz w dniu, gdy paczka jest w doręczeniu, nawet co 15 minut przy otwartej przesyłce lub włączonych powiadomieniach. Do wyboru masz powiadomienia „Wszystkie aktualizacje”, „Tylko ważne” (dostawa, odbiór, odprawa celna, nieudane doręczenia i zwroty) albo „Tylko w dniu dostawy”. Peek nie może skontaktować się z Shouwu ani ze sprzedawcą w twoim imieniu.

## Najczęstsze pytania o śledzenie Shouwu

### Czym zajmuje się firma Shouwu International (守务国际)?

To firma logistyczna z Szanghaju (`上海守务国际物流有限公司`, używająca też skrótu SWE), obsługująca przesyłki transgranicznego handlu internetowego. Na swojej stronie podaje, że zaczęła działalność w 2018 roku w spedycji lotniczej, a w 2019 roku uruchomiła linie e-commerce do Europy i Stanów Zjednoczonych.

### Gdzie mogę sprawdzić status paczki Shouwu?

W polu wyszukiwania na górze tej strony albo bezpośrednio w anglojęzycznej wyszukiwarce Shouwu pod adresem `admin.shshouwu.com`. Wpisz pełny, 19-znakowy numer `SWECN…YQ`.

### Kto doręcza paczki Shouwu w Polsce?

Shouwu nie publikuje na swojej stronie osobnej linii do Polski i nie wskazuje polskiego doręczyciela (dla innych krajów UE wymienia m.in. Colissimo we Francji, DHL, Hermes i DPD w Niemczech oraz UniUni i Poste Italiane we Włoszech). Zapytaj sprzedawcę, któremu kurierowi przekazywana jest paczka w Polsce i poproś o numer lokalny.

### Ile idzie paczka wysłana przez Shouwu?

Czas transportu zależy od linii wybranej przez nadawcę. Dla Polski Shouwu nie podaje czasu orientacyjnego; na opisanych trasach europejskich deklaruje od 4 do 7 dni roboczych do Niemiec i Francji, od 4 do 8 dni roboczych do Holandii i Szwecji oraz od 10 do 12 dni roboczych na linii ekonomicznej do Włoch.

### Z kim się skontaktować, jeśli paczka Shouwu zaginęła?

Ze sklepem lub platformą, gdzie złożono zamówienie — to sprzedawca jest klientem Shouwu i ma 30 dni od wysyłki na zgłoszenie reklamacji u przewoźnika. Shouwu nie udostępnia kontaktu dla odbiorców końcowych.

:::sources
- [Shouwu International: 守务国际包裹追踪 (śledzenie przesyłek)](https://admin.shshouwu.com/track.html) – anglojęzyczna wyszukiwarka, do 40 numerów naraz, statusy i ich wyjaśnienia, skany bez miejscowości, przycisk „Copy Link” oraz `Not Found`
- [Shouwu International: 首页 (strona główna, po chińsku)](http://www.sedel.com/) – pełna nazwa spółki, chińskie numery komórkowe do Szanghaju i pięciu oddziałów, czat QQ i kody WeChat
- [Shouwu International: 关于我们 (o nas, po chińsku)](http://www.sedel.com/about-us.html) – start w 2018 r., przejście w 2019 r. na linie e-commerce do Europy i USA oraz partnerzy bezpośredni
- [Shouwu International: 主营线路 (główne linie, po chińsku)](http://www.sedel.com/main-route.html) – wykaz krajów z własnymi liniami (brak wymienionej trasy do Polski)
- [Shouwu International: 英国标快 (Wielka Brytania standard, po chińsku)](http://www.sedel.com/main-route-secondary.html?groupId=1&groupItemId=2) – Royal Mail, Yodel i ERVI, wybór numeru śledzenia, 5–8 dni roboczych, brak zwrotów do Chin i zasady reklamacji (30 dni, limit 200 RMB)
- [Shouwu International: 英国经济 (Wielka Brytania ekonomiczna, po chińsku)](http://www.sedel.com/main-route-secondary.html?groupId=2&groupItemId=3) – 10–12 dni roboczych
- [Shouwu International: 英国带电 (Wielka Brytania z bateriami, po chińsku)](http://www.sedel.com/main-route-secondary.html?groupId=3&groupItemId=4) – doręczenie głównie przez Royal Mail
- [Shouwu International: 美国标快 (USA standard, po chińsku)](http://www.sedel.com/main-route-secondary.html?groupId=1&groupItemId=1) – doręczenie głównie przez USPS, 6–8 dni roboczych, brak zwrotów do Chin i reklamacje
- [Shouwu International: 美国经济 (USA ekonomiczna, po chińsku)](http://www.sedel.com/main-route-secondary.html?groupId=2&groupItemId=21) – 8–12 dni roboczych
- [Shouwu International: 德国标快 (Niemcy standard, po chińsku)](http://www.sedel.com/main-route-secondary.html?groupId=1&groupItemId=19) – głównie DHL, wybór numeru, 4–7 dni roboczych i reklamacje
- [Shouwu International: 德国经济 (Niemcy ekonomiczna, po chińsku)](http://www.sedel.com/main-route-secondary.html?groupId=2&groupItemId=22) – głównie Hermes, 4–7 dni roboczych
- [Shouwu International: 德国带电 (Niemcy z bateriami, po chińsku)](http://www.sedel.com/main-route-secondary.html?groupId=3&groupItemId=25) – głównie DPD, 4–7 dni roboczych
- [Shouwu International: 法国标快 (Francja standard, po chińsku)](http://www.sedel.com/main-route-secondary.html?groupId=1&groupItemId=20) – głównie Colissimo, 4–7 dni roboczych
- [Shouwu International: 荷兰标快 (Holandia standard, po chińsku)](http://www.sedel.com/main-route-secondary.html?groupId=4&groupItemId=28) – głównie DHL, 4–8 dni roboczych
- [Shouwu International: 瑞典经济 (Szwecja ekonomiczna, po chińsku)](http://www.sedel.com/main-route-secondary.html?groupId=4&groupItemId=29) – głównie EarlyBird, 4–8 dni roboczych
- [Shouwu International: 意大利经济 (Włochy ekonomiczna, po chińsku)](http://www.sedel.com/main-route-secondary.html?groupId=4&groupItemId=27) – UniUni i Poste Italiane, wybór numeru i 10–12 dni roboczych
- [Komisja Europejska: EU Customs Reform (po angielsku)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – cło 3 € od 1 lipca 2026 r. oraz opłata manipulacyjna 2 € od 1 listopada 2026 r.
- [Komisja Europejska: Guidance on the 3 EUR customs duty and Union handling fee (PDF, po angielsku)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – naliczanie unijnej opłaty manipulacyjnej 2 € za kategorię towaru niezależnie od wartości przesyłki
- [Poczta Polska: Zmiany w opłatach dla przesyłek spoza Unii Europejskiej od 1 lipca 2026 r.](https://www.poczta-polska.pl/news/zmiany-w-oplatach-dla-przesylek-spoza-unii-europejskiej-od-1-lipca-2026-r/) – pobieranie należności celno-podatkowych w Polsce przy doręczeniu przesyłki lub przed jej odbiorem
- [Poczta Polska: Nowe przepisy Unii Europejskiej – tak działa nowa opłata za przesyłki spoza UE](https://media.poczta-polska.pl/releases/nowe-przepisy-unii-europejskiej-tak-dziala-nowa-oplata-za-przesylki-spoza-ue) – stosowanie nowej unijnej opłaty od przesyłek spoza UE od 1 listopada 2026 r.
- [Poczta Polska: Uwaga na fałszywe wiadomości Pocztex – groźna kampania phishingowa](https://www.poczta-polska.pl/news/uwaga-na-falszywe-wiadomosci-pocztex-grozna-kampania-phishingowa/) – ostrzeżenie w Polsce, że Pocztex nie wysyła wiadomości SMS z linkami do płatności
- [Komisja Europejska: Summertime (po angielsku)](https://transport.ec.europa.eu/transport-themes/summertime_en) – europejski czas letni od ostatniej niedzieli marca do ostatniej niedzieli października
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – wzorzec `SWECN…YQ`, wyszukiwarka i link, statusy, treści skanów i etapy (w tym odprawa eksportowa i importowa, załadunek na lotnisku, odbiór przez partnera, odbiór i sortownie FedEx, wypłynięcie statku, doręczenie firmowe DHL, komunikaty iMile oraz DPD i wpisy `arrival city` / `leave the city`), zegar pekiński, miejscowości i działanie Peeka
:::
