---
title: Śledzenie Mondial Relay: statusy, InPost, Vinted i odbiór
description: Śledź paczkę Mondial Relay w Polsce: 8-cyfrowy numer nadania, znaczenie francuskich statusów po polsku, powiązanie z Grupą InPost, odbiór w punkcie i kontakt.
slug: sledzenie-mondial-relay
published: 2026-11-17
updated: 2026-11-17
---

Mondial Relay to francuski przewoźnik paczkowy należący od 2021 roku do wywodzącej się z Polski **Grupy InPost**, specjalizujący się w dostawach do punktów odbioru (*Points Relais* w lokalnych sklepach), automatów paczkowych (*Lockers*) oraz pod adres domowy. Aby sprawdzić status przesyłki, wpisz 8-cyfrowy numer nadania wraz z kodem pocztowym odbiorcy na stronie `mondialrelay.fr` (lub `.be` / `.nl`) bądź w aplikacji Mondial Relay — albo wklej sam numer w polu powyżej.

Skąd numer Mondial Relay u kupującego w Polsce? Choć na polskim [Vinted](guide:vinted-orders) zintegrowana wysyłka obejmuje kraje Europy Środkowej i Północnej (Szwecję, Litwę, Słowację, Węgry, Rumunię, Czechy, Danię, Chorwację, Finlandię, Słowenię, Łotwę i Estonię — z udziałem m.in. [InPost](carrier:inpost), [DPD Polska](carrier:dpd-pl), [ORLEN Paczki](carrier:orlen-paczka) i [Poczty Polskiej](carrier:poczta-polska)), z numerem Mondial Relay spotkasz się przy zamówieniach bezpośrednio we francuskich lub belgijskich sklepach internetowych, przy międzynarodowych paczkach między krajami sieci Grupy InPost (w Polsce doręczanych do Paczkomatów lub punktów InPost) oraz gdy zamawiasz na adres we Francji czy krajach Beneluksu, gdzie Mondial Relay (obok [Vinted Go](carrier:vinted-go)) jest jednym ze zintegrowanych przewoźników.

## Numer śledzenia Mondial Relay: formaty i gdzie go znaleźć

Mondial Relay nazywa go *numéro d'expédition*: to 8 cyfr przesyłanych w e-mailu lub SMS-ie po nadaniu paczki. Jeśli nie masz numeru, poproś o niego nadawcę (który również widzi śledzenie) albo przeczytaj, [gdzie znaleźć numer śledzenia przesyłki](guide:find-tracking-number). Na etykietach i w systemach przewoźnika występują też dłuższe warianty:

| Format (przykład zmyślony) | Co oznacza |
| --- | --- |
| `48291057` | Podstawowy 8-cyfrowy numer nadania (taką samą długość stosują m.in. Colisweb i Heppner). |
| `0012345678` | 10 cyfr: dwucyfrowy kod nadawcy/sklepu oraz numer nadania. Tę samą długość mają [DHL Express](carrier:dhl-express), Relais Colis i inni. |
| `001234567802` | 12 cyfr: powyższy układ plus numer kolejny paczki (podobnie jak DPD France, Colis Privé czy BRT). |
| `00123456780101` | 14 cyfr: początek kodu kreskowego z kodem sklepu, numerem nadania, numerem paczki i liczbą paczek. Spotykany też w DPD i [Chronopost](carrier:chronopost). |
| `00123456780101700000000000` | 26 cyfr: pełny kod kreskowy z etykiety — wyłącznie w Mondial Relay, z 8-cyfrowym numerem nadania na pozycjach od 3 do 10. |
| `FV48291057` | `FV` i 8 cyfr: numer przesyłki InPost Hiszpania (w Hiszpanii i Portugalii dawne Mondial Relay działa od 2021 r. pod marką InPost). |

Na części francuskich etykiet numer nadania bywa sklejony bez spacji z 5-cyfrowym francuskim kodem pocztowym (łącznie 13, 15 lub 17 cyfr). Jeżeli natomiast sklep lub aplikacja InPost Mobile pokaże ci osobny, 24-cyfrowy krajowy numer [InPost](carrier:inpost) na polski odcinek dostawy, to właśnie nim prześledzisz ostatni etap w Polsce; zobacz też [jak rozpoznać przewoźnika po formacie numeru](guide:tracking-number-formats).

## Francuskie statusy Mondial Relay po polsku

Śledzenie Mondial Relay wyświetla komunikaty po francusku (w Belgii i Holandii również po niderlandzku). Oto najczęstsze wpisy w kolejności, w jakiej pojawiają się na trasie:

| Komunikat Mondial Relay | Co to oznacza dla ciebie |
| --- | --- |
| `Colis en préparation chez l'expéditeur` | Etykieta została utworzona, ale Mondial Relay nie odebrał jeszcze paczki od nadawcy. |
| `Prise en charge` / `Colis pris en charge en Locker` | Nadawca nadał przesyłkę w punkcie Point Relais lub w automacie Locker. |
| `Prise en charge de votre colis sur notre site logistique` / `Colis expédié depuis le site` | Paczka dotarła do wskazanej sortowni logistycznej albo z niej wyjechała. |
| `Colis en route vers le point de livraison` | W drodze do docelowego punktu odbioru, automatu albo sieci doręczeń. |
| `Sollicitation Client pour replace colis` | Wybrany punkt Point Relais nie może przyjąć paczki — przewoźnik prosi o wskazanie punktu zastępczego. |
| `Relais de substitution choisi` | Wyznaczono zastępczy punkt odbioru — sprawdź jego dokładny adres. |
| `En cours de livraison` | Wydana dziś kurierowi do doręczenia pod adres domowy. |
| `Disponible dans votre Point Relais` / `Disponible dans votre locker` | Gotowa do odbioru w punkcie lub w automacie paczkowym. |
| `jours restants pour retirer le colis` | Liczba dni pozostałych na odbiór przed zwrotem do nadawcy. |
| `Retiré par le destinataire` / `Colis livré au destinataire` | Odebrana przez adresata lub doręczona pod wskazany adres. |
| `Retour à l'expéditeur` | Przesyłka wraca do nadawcy. |

Przy dostawie do punktu lub automatu poczekaj na status `Disponible…` (albo powiadomienie z kodem odbioru), zanim ruszysz z domu. `Échec de livraison` oznacza nieudaną próbę doręczenia pod adres, a komunikat `Votre colis a été livré à l'enseigne.` informuje, że paczka dotarła do sklepu: jeśli wcześniej w historii widniał zwrot (`Retour à l'expéditeur`), Peek oznacza ją jako „Zwrócona nadawcy”; jeśli sam odesłałeś zwrot z osobnym numerem, Peek pokaże „Dostarczona”. Dla hiszpańskich numerów `FV` Peek odczytuje komunikaty po hiszpańsku z `inpost.es` (m.in. `En preparación`, `Recogido por el mensajero`, `Listo para recoger en Punto Pack`, `Entregado`).

## Odbiór paczki, terminy przechowywania i wymiary przesyłek do Polski

- **Jak odebrać przesyłkę:** po dotarciu paczki do punktu odbioru lub [automatu paczkowego](guide:parcel-lockers) otrzymasz e-mail i SMS z 6-cyfrowym kodem PIN lub kodem QR (automaty są dostępne całą dobę 7 dni w tygodniu; w punktach stacjonarnych zabierz również dokument tożsamości).
- **Czas na odbiór:** dokładny termin odbioru podaje wiadomość o gotowości przesyłki zależnie od kraju docelowego (w sieci InPost w Hiszpanii dla przesyłek krajowych to 5 dni kalendarzowych, a portugalska strona InPost podaje 5 dni lub 8 dni w dziale FAQ). Przy zamówieniach z Vinted nieodebrana w terminie paczka wraca automatycznie do sprzedającego, transakcja zostaje anulowana, a kupujący dostaje zwrot pieniędzy. W aplikacji Mondial Relay paczki odebrane lub doręczone ponad **7 dni** wcześniej są automatycznie archiwizowane (i usuwane co 160 dni).
- **Wymiary paczek wysyłanych do Polski:** według InPost Hiszpania i Portugalia przesyłki kierowane do **Polski** muszą mieć co najmniej **191 mm** wysokości wraz z opakowaniem, maksymalne wymiary **39 × 38 × 64 cm** oraz masę do **25 kg**.

## Paczka utknęła, zaginęła lub przyszła uszkodzona

- **Brak skanów po nadaniu:** na Vinted pierwsze informacje o śledzeniu mogą pojawić się dopiero po **48 godzinach** od wysyłki. Przeczytaj też, [dlaczego śledzenie przesyłki się nie aktualizuje](guide:tracking-not-updating).
- **Opóźnienie lub zaginięcie:** przy zakupach na Vinted przesyłki docierają zwykle w **5 dni roboczych** (**7 dni roboczych** przy wysyłce międzynarodowej), ale przed zgłoszeniem zaginięcia zintegrowanej przesyłki należy odczekać do **25 dni roboczych** od nadania — po tym czasie (lub gdy czat sam wskaże zagubienie) kliknij w rozmowie **„Mam problem”**, aby anulować zamówienie i otrzymać zwrot środków. W sklepach internetowych w UE sprzedawca ma ustawowo **30 dni** na dostarczenie towaru (chyba że uzgodniliście inny termin) i odpowiada za przesyłkę aż do jej wydania kupującemu. Zobacz [zagubiona przesyłka](guide:lost-parcel).
- **Oznaczona jako doręczona lub uszkodzona:** na Vinted masz **2 dni** od zmiany statusu na „Doręczono”, by nacisnąć „Mam problem” i wstrzymać zamówienie. Jeżeli w punkcie odbioru widzisz rozerwany lub zgnieciony karton, najlepiej odmów przyjęcia paczki na miejscu i od razu powiadom sprzedawcę. Więcej w poradniku [uszkodzona paczka](guide:damaged-parcel).

## Kontakt z Mondial Relay i InPost

- **Polska (InPost sp. z o.o.):** siedziba przy `ul. Pana Tadeusza 4, 30-727 Kraków`; infolinia pod numerami **`746 600 000`** oraz **`722 444 000`**, czat w aplikacji InPost Mobile oraz formularz reklamacyjny online (w sprawach usług powszechnych i pocztowych w Polsce prawa reklamacyjne opisuje też Centrum Informacji Konsumenckiej UKE).
- **Francja i Beneluks (Mondial Relay SASU):** siedziba przy `1 avenue de l'Horizon, 59650 Villeneuve d'Ascq` we Francji (rejestr handlowy Lille Métropole `385 218 631`), część luksemburskiej spółki InPost S.A. dysponującej ok. 95 000 automatów i punktów odbioru w 9 krajach Europy (1,4 mld przesyłek w 2025 r.). Kontakt z obsługą klienta i formularz zgłoszeń są dostępne na `mondialrelay.fr` (w Holandii Mondial Relay przechodzi stopniowo pod szyld InPost na `inpost.nl`, zachowując obsługę na `mondialrelay.nl`).
- **Hiszpania i Portugalia (InPost):** Hiszpania pod numerem `900 900 137` (bezpłatny w Hiszpanii) lub `+34 938 16 62 34` (dostępny z każdego kraju, pon.–pt. 8:30–20:00, sob. 9:00–13:00); Portugalia pod numerem `211 245 624` (pon.–pt. 9:00–18:00).

> Otrzymałeś SMS lub e-mail z prośbą o drobną dopłatę do paczki Mondial Relay? Nie otwieraj linku. Francuski rządowy serwis *Cybermalveillance.gouv.fr* przypomina, że Mondial Relay nigdy nie żąda dopłacania dodatkowych opłat przez SMS ani e-mail. Fałszywe wiadomości podszywające się pod Mondial Relay zgłosisz na adres **`phishing@inpost-group.com`**, a próby wyłudzeń na Vinted przez przycisk „Napisz do nas” w Centrum Pomocy Vinted. Więcej wskazówek w poradniku [fałszywe SMS-y o dopłacie do paczki](guide:delivery-scam-texts).

## Śledzenie przesyłki Mondial Relay w Peek

[Peek](/) natychmiast rozpoznaje 26-cyfrowy kod kreskowy z etykiety i wyświetla „Wykryty przewoźnik”. Przy numerach o długości 8, 10, 12 lub 14 cyfr nie wskaże w Polsce Mondial Relay samoczynnie: te długości dzielą również inni przewoźnicy, a podczas automatycznego rozpoznawania Peek odpytuje Mondial Relay wyłącznie dla hiszpańskich numerów `FV` (`FV` plus 8 cyfr), sprawdzając wtedy jednocześnie Mondial Relay (na stronie `inpost.es`) oraz TNT Italy. Automatyczną podpowiedź Mondial Relay dla nieznanego 8-cyfrowego numeru Peek wyświetla tylko użytkownikom we Francji, Belgii, Luksemburgu, Hiszpanii, Portugalii i Holandii — w Polsce kliknij więc „Zmień” i wybierz Mondial Relay z listy albo wklej link śledzenia z domeny `mondialrelay.fr`, `.be`, `.nl` lub `.com` (linki z `.es`, `.pt` oraz `inpost.es` nie są odczytywane).

Standardowy 8-cyfrowy numer wymaga też pola „Kod pocztowy odbiorcy”: wpisz najpierw sam numer, a następnie kod pocztowy miejsca doręczenia lub wybranego punktu odbioru; portugalski kod pocztowy wymaga wszystkich siedmiu cyfr (`1234-567`), natomiast 13-, 15- i 17-cyfrowe numery z francuskich etykiet zakończone 5-cyfrowym kodem pocztowym Francji Peek rozdziela sam po wybraniu Mondial Relay. Z błędnym kodem pocztowym przesyłka nie zostanie odnaleziona. Kod trafia do Mondial Relay (i w razie potrzeby do serwisów śledzenia), ale nigdy nie pojawia się w udostępnianych linkach.

Peek pokazuje oryginalne francuskie statusy Mondial Relay bez zmian, zaznacza każdą sortownię na mapie i przelicza czasy z francuskiej strefy `Europe/Paris` z dopiskiem „Godziny w twojej strefie czasowej: …”. Gdy paczka ma etap „Do odbioru”, widzisz nazwę i adres punktu Point Relais lub automatu Locker, nigdy jednak twój kod odbioru ani nazwisko. Peek sprawdza przesyłkę nawet co 10 minut, a w dniu wydania do doręczenia od 6:00 czasu paryskiego nawet co 2 minuty (co 5 minut przez przeglądarkę, co 15 minut przez serwisy śledzenia), przechodząc na sprawdzanie co godzinę po dwóch dniach bez nowości albo po 12 godzinach na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika”. Powiadomienia ustawisz na „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Ponieważ Mondial Relay po pewnym czasie ponownie wykorzystuje stare numery, dawny numer może pokazać nowszą paczkę. Peek nie przekazuje śledzenia Mondial Relay do innych przewoźników — jeśli paczka dostanie osobny polski numer [InPost](carrier:inpost), dodaj go w Peek jako oddzielną przesyłkę.

## Najczęstsze pytania o śledzenie Mondial Relay

### Czy Mondial Relay i InPost to ta sama firma?

Należą do tej samej grupy kapitałowej: wywodzący się z Polski InPost przejął Mondial Relay w 2021 roku, tworząc europejską sieć około 95 000 Paczkomatów i punktów odbioru w 9 krajach. W Polsce sieć działa jako [InPost](carrier:inpost), w Hiszpanii i Portugalii przeszła już pod markę InPost, a w Holandii trwa stopniowa zmiana szyldu z Mondial Relay na InPost.

### Czy mogę śledzić paczkę Mondial Relay bez kodu pocztowego?

Tak, jeśli masz 26-cyfrowy kod kreskowy z etykiety albo wariant 10-, 12- bądź 14-cyfrowy. Przy najpopularniejszym 8-cyfrowym numerze nadania w Peek zawsze potrzebny jest „Kod pocztowy odbiorcy”.

### Kto doręcza w Polsce paczkę wysłaną z Francji przez Mondial Relay?

W obrębie międzynarodowej sieci Grupy InPost przesyłki kierowane do Polski trafiają do Paczkomatów lub punktów [InPost](carrier:inpost). Jeśli nadawca lub sklep przekaże ci osobny, krajowy numer śledzenia InPost na polski odcinek trasy, wklej go osobno w Peek lub sprawdź w aplikacji InPost Mobile.

### Co się stanie, jeśli nie odbiorę paczki Mondial Relay w terminie?

Po upływie terminu wskazanego w powiadomieniu SMS lub e-mail przesyłka wraca automatycznie do nadawcy (w przypadku zamówień z Vinted oznacza to automatyczne anulowanie zakupu i zwrot pieniędzy na twoje konto).

:::sources
- [Grupa InPost: Regulamin Konta InPost (od 01.10.2026 r.)](https://terms.inpost-group.com/pl/regulamin) – dane rejestrowe Mondial Relay SASU w Villeneuve-d'Ascq oraz InPost sp. z o.o. w Krakowie (ul. Pana Tadeusza 4, infolinia 746 600 000 i 722 444 000), ręczne śledzenie z kodem pocztowym i archiwizacja w aplikacji po 7 dniach
- [InPost S.A.: Who we are](https://inpost.eu/who-we-are) – spółka dominująca InPost S.A. w Luksemburgu i europejska sieć dostaw out-of-home
- [InPost Nederland: Mondial Relay Nederland maakt plaats voor InPost](https://inpost.nl/) – skala Grupy InPost (9 rynków, 95 000 automatów i punktów odbioru, 1,4 mld paczek w 2025 r.) i zmiana marki w Holandii
- [InPost España: Quiénes somos](https://www.inpost.es/quienes-somos) – przejęcie Mondial Relay przez InPost w 2021 r.
- [InPost España: Seguimiento del envío](https://www.inpost.es/seguimiento-del-envio) – 8-znakowy numer przesyłki, 5 dni kalendarzowych na odbiór w Hiszpanii i wydanie paczki za okazaniem dokumentu
- [InPost España: Contacta con nosotros](https://www.inpost.es/contacta-con-nosotros) – numery 900 900 137 i +34 938 16 62 34, godziny pracy oraz wymiary paczek do Polski (wysokość min. 191 mm, maks. 39 × 38 × 64 cm i 25 kg)
- [InPost Portugal: Quem somos](https://www.inpost.pt/quem-somos) – przejęcie sieci w Portugalii w 2021 r.
- [InPost Portugal: Como recolher](https://www.inpost.pt/recebe-a-tua-encomenda/como-recolher) – 6-cyfrowy PIN lub kod QR, całodobowe automaty i termin odbioru 5 lub 8 dni
- [InPost Portugal: Fala connosco](https://www.inpost.pt/fala-connosco) – portugalska infolinia 211 245 624 oraz limity wymiarów przesyłek do Polski
- [Vinted Polska: Wysyłka na Vinted](https://www.vinted.pl/help/234) – kraje połączone z polskim Vinted oraz lista zintegrowanych przewoźników w Polsce
- [Vinted France: Comment fonctionne l'envoi ?](https://www.vinted.fr/help/234) – Mondial Relay i Vinted Go jako zintegrowani przewoźnicy we Francji
- [Vinted Polska: Śledzenie przesyłki](https://www.vinted.pl/help/100) – do 48 godzin na pierwsze informacje śledzenia, limit 25 dni roboczych i 2 dni od doręczenia na zgłoszenie problemu
- [Vinted Polska: Odbiór przesyłki](https://www.vinted.pl/help/1601) – kod odbioru przez SMS/e-mail, automatyczny zwrot i refundacja po nieodebraniu oraz 2 dni na reklamację
- [Vinted Polska: Moja przesyłka nie dotarła](https://www.vinted.pl/help/657) – standardowy czas dostawy 5 dni roboczych (7 międzynarodowo) i procedura po 25 dniach roboczych
- [Vinted Polska: Jak unikać phishingu i oszustw oraz jak je zgłaszać](https://www.vinted.pl/help/628) – ochrona przed phishingiem i zgłaszanie oszustw w Centrum Pomocy Vinted
- [Cybermalveillance.gouv.fr: L'hameçonnage à la livraison de colis](https://www.cybermalveillance.gouv.fr/tous-nos-contenus/actualites/escroqueries-livraison-colis) – Mondial Relay nigdy nie żąda dodatkowych opłat przez SMS lub e-mail, adres zgłoszeń phishing@inpost-group.com
- [Your Europe: Wysyłka i dostawa](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_pl.htm) – 30 dni na dostawę w sklepach internetowych UE i odpowiedzialność sprzedawcy za uszkodzenia w transporcie
- [CIK UKE: Reklamacje usług pocztowych](https://cik.uke.gov.pl/uslugi-pocztowe/dochodzenie-roszczen/reklamacje/) – zasady składania reklamacji usług pocztowych w Polsce
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – rozpoznawane formaty w Peek, jednoczesne sprawdzanie numerów FV, kraje z podpowiedzią 8-cyfrową, częstotliwość odpytywania i brak automatycznego przekazywania do innych przewoźników
:::
