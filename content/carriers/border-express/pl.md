---
title: Śledzenie Border Express (BEX): numery przesyłek i statusy
description: Śledź przesyłkę Border Express (BEX) w Australii: wygląd numerów przesyłek i kodów kreskowych z etykiet, znaczenie statusów oraz śledzenie w Peek.
slug: sledzenie-border-express
published: 2026-11-22
updated: 2026-11-22
---

Border Express, w skrócie BEX, to przewoźnik, którego katalog Peek wymienia wyłącznie w Australii. Jeśli sklep internetowy wysłał zamówienie przez Border Express, numerem do śledzenia jest numer przesyłki (*consignment number*): wklej go w polu powyżej. Sklep podaje go w e-mailu wysyłkowym lub na stronie zamówienia. Własnym narzędziem śledzenia Border Express jest Quick Track w BEX Online (`bexonline.borderexpress.com.au`). Ponieważ Peek nie mógł sprawdzić strony internetowej Border Express na potrzeby tego opisu, poniższe informacje pochodzą z tego, co kod Peek wie o tym trackerze.

## Numer śledzenia Border Express: jak wygląda

Border Express śledzi dostawę po numerze przesyłki (*consignment number*). Kod Peek zna trzy układy. Ze zmyślonymi znakami:

| Numer (zmyślony) | Co to jest |
| --- | --- |
| `48291736` | 8 cyfr: numer przesyłki. |
| `KLMN482917` | 4 litery (prefiks nadawcy), po których następują cyfry: również numer przesyłki. Peek przyjmuje od 5 do 8 cyfr po literach. |
| `KLMNBX00000482917001` | 20 znaków: kod kreskowy na etykiecie pojedynczej paczki. 4 litery nadawcy, `BX`, cyfry przesyłki uzupełnione zerami z przodu do 11 pozycji oraz 3-cyfrowy numer sztuki. |

Przesyłka może obejmować kilka paczek, każdą z własnym kodem kreskowym etykiety. Wszystkie etykiety zaczynają się od tych samych liter i cyfr, a spina je numer przesyłki — to jego szuka tracker Border Express, który nie akceptuje kodu kreskowego etykiety. Peek go akceptuje i wylicza z niego numer przesyłki (niżej).

Peek nie rozpozna numeru Border Express po samym układzie: jego katalog nie ma reguły dla numerów przesyłek Border Express, a reguła dla kodu kreskowego z etykiety to jedynie słabe dopasowanie. Z tych samych formatów korzystają inni przewoźnicy: przy 8 cyfrach Peek podpowiada [Mondial Relay](carrier:mondial-relay), Colisweb i [Heppner](carrier:heppner) w Europie, oraz TForce Freight (fracht w Ameryce Północnej), gdy ostatnia cyfra przechodzi kontrolę. Cztery litery i 8 cyfr pasują także do 12-znakowych numerów Colis Privé. Jeśli sklep wskazał Border Express, wybierz go ręcznie (niżej). Spacje i małe litery nie mają znaczenia przy wklejaniu. Brak numeru? Zobacz [jak znaleźć numer śledzenia](guide:find-tracking-number) oraz [formaty numerów śledzenia](guide:tracking-number-formats).

## Statusy śledzenia Border Express — wyjaśnienie

Tracker Border Express wyświetla pasek postępu i listę etapów od najnowszego do najstarszego, każdy z dzielnicą/miejscowością i stanem. Etapy znane kodowi Peek w typowej kolejności:

| Border Express pisze | Co to znaczy dla ciebie | Peek pokazuje |
| --- | --- | --- |
| `Shipment Data Received` | Nadawca przekazał Border Express dane przesyłki. Border Express nie ma jeszcze paczki. Pasek postępu pokazuje `Data Received`. | „Zarejestrowana” |
| `Picked Up` | Border Express odebrało paczkę od nadawcy. Tracker zwykle podaje ten etap bez godziny, a Peek pomija etapy bez godziny. | „Przyjęta przez przewoźnika”, tylko gdy etap ma godzinę |
| `In Transit` | W drodze w sieci Border Express. | „W transporcie” |
| `On Board For Delivery` | W pojeździe doręczającym. Pasek postępu pokazuje `On Board`. | „W doręczeniu” |
| `Partially Delivered` | Część sztuk z przesyłki została dostarczona, reszta jest jeszcze w drodze. | „W transporcie” |
| `Partially Delivered - Final` | Doręczenie zakończone: przesyłka zamyka się sztukami, które dotarły. Jeśli czegoś brakuje, zapytaj nadawcę. | „Dostarczona” |
| `Delivered` | Doręczona. | „Dostarczona” |

Peek zachowuje angielskie nazwy Border Express z własnym statusem powyżej. Etap spoza tej tabeli wyświetla się tak, jak go zapisano, a Peek odczytuje status ze słów, gdy to możliwe.

## Dostawa Border Express w Australii

Tracker Border Express nie wskazuje żadnego innego przewoźnika dla przesyłki, więc Peek śledzi ją w Border Express od pierwszego etapu aż po doręczenie, bez przekazywania innemu przewoźnikowi.

Ta strona nie podaje zasad prób doręczenia, awiz, pozostawiania bez podpisu, odbioru w oddziale ani czasu przechowywania, bo Peek nie mógł ich sprawdzić na stronie Border Express. Zapytaj sklep nadający paczkę albo Border Express. Zobacz [nieudane doręczenie](guide:missed-delivery) oraz [nieodebrane przesyłki](guide:uncollected-parcel).

## Paczka Border Express nie aktualizuje się, zaginęła lub dostarczona, ale jej nie ma

**Nie znaleziono numeru.** Dopóki tracker Border Express nie zna numeru, przesyłka przypisana do Border Express wyświetla „Oczekiwanie na przewoźnika Border Express”, a Peek sprawdza dalej. Jeśli po dwóch dniach Border Express nadal nie zna numeru i nie odpowiedziało żadne inne źródło, Peek wyświetla „Po dwóch dniach przewoźnik nadal nie zna tego numeru. Sprawdź go, wybierz innego przewoźnika lub usuń przesyłkę.” i sprawdza ją raz dziennie, a po tygodniu raz w tygodniu. Sprawdź numer z e-mailem sklepu: 8 cyfr albo 4 litery i 5–8 cyfr. Jeśli po kilku dniach nadal nic się nie pojawia, zapytaj sklep, czy przesyłka została nadana.

**Stoi na „Zarejestrowana”.** Border Express ma dane przesyłki, ale jeszcze żaden skan nie ma daty. Jego tracker może pokazywać `Picked Up` bez godziny, co Peek pomija — sprawdź tam najpierw. Jeśli wciąż widnieje samo `Shipment Data Received`, zapytaj sklep o moment przekazania paczki.

**Brak aktualizacji od dni.** Gdy przesyłka w drodze nie ma nowego etapu przez cztery dni, Peek pokazuje datę ostatniego skanu („Brak aktualizacji od …”) i komunikat „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika Border Express.” z linkiem „Poproś przewoźnika Border Express o pomoc” do strony obsługi klienta. Zobacz [dlaczego śledzenie przestaje się aktualizować](guide:tracking-not-updating).

**Częściowo dostarczona.** Porównaj otrzymane rzeczy z zamówieniem. Reszta przesyłki jest jeszcze w drodze, a tracker Border Express pokazuje skany poszczególnych sztuk, których Peek nie wyświetla.

**Dostarczona, ale nic nie przyszło.** Sprawdź okolice adresu i zapytaj sąsiadów. Po doręczeniu Peek odczytuje również nazwisko podpisującego, jeśli widnieje w trackerze Border Express. Następnie skontaktuj się ze sklepem. Więcej w [dostarczona, ale nieotrzymana](guide:delivered-not-received).

**Zaginiona, uszkodzona lub zwrócona.** Przewóz przez Border Express zlecił sklep, więc to sklep prosisz o zwrot pieniędzy lub nową paczkę. Zobacz [zaginione przesyłki](guide:lost-parcel), [uszkodzone przesyłki](guide:damaged-parcel) i [zwrócona nadawcy](guide:returned-to-sender).

## Kontakt i obsługa klienta Border Express

Ponieważ Peek nie mógł sprawdzić strony Border Express, nie podajemy tu telefonu ani e-maila: Peek umieszcza wyłącznie kontakty odczytane ze stron samego przewoźnika. Dostawę zamówił sklep, więc zwróć się najpierw do niego.

Na ekranie przesyłki w Peek link „Otwórz stronę przewoźnika Border Express” otwiera twój numer w trackerze Border Express. Zanim zapłacisz cokolwiek na podstawie SMS-a o paczce, przeczytaj [fałszywe SMS-y o dostawie](guide:delivery-scam-texts).

## Śledzenie przesyłki Border Express w Peek

Wklej numer w [Peek](/), który obsługuje ponad 4500 przewoźników. Podczas sprawdzania może pojawić się „Sprawdzanie usług śledzenia…”. Następnie stuknij „Zmień” i wybierz Border Express, wpisując w wyszukiwarkę `Border Express` (`BEX` również go znajduje, ale najpierw pokazuje serbskiego przewoźnika BEX Express). Przy kodzie kreskowym etykiety Border Express widnieje w sekcji „Pasują do numeru”. W już dodanej paczce użyj „Zmień przewoźnika”. Konto, kod pocztowy ani telefon nie są potrzebne.

Jeśli dodasz numer bez wyboru przewoźnika, Peek zapyta wyłącznie usługi śledzenia, nigdy Border Express, i wyświetli „Brak historii śledzenia”, dopóki któraś z nich nie rozpozna paczki. Dopiero gdy usługa śledzenia wskaże Border Express, Peek zacznie odpytywać Border Express. Ręczny wybór Border Express od razu daje dostęp do jego własnych etapów.

Link śledzenia Border Express też działa i natychmiast wskazuje Border Express („Wykryty przewoźnik”): `borderexpress.com.au/tracking` z parametrem `cn=` albo link `bexonline.borderexpress.com.au/bxo/track`. Jeśli wybierzesz Border Express dla numeru w innym formacie, Peek poinformuje „nie wydaje takich numerów”, a po dodaniu (jeśli żadna usługa śledzenia nie ma przesyłki) wyświetli „Sprawdź numer śledzenia” — poszukaj brakującego lub zbędnego znaku.

**Kody kreskowe z etykiet.** Ponieważ tracker Border Express ich nie przyjmuje, Peek wylicza z kodu numer przesyłki: cztery litery, następnie 11 cyfr po `BX` bez początkowych zer (zachowując co najmniej sześć cyfr) i z odrzuceniem ostatnich 3 cyfr numeru sztuki. Na ekranie przesyłki numer ten figuruje pod „Border Express śledzi przesyłkę pod numerem”, a „Otwórz stronę przewoźnika Border Express” otwiera Quick Track od razu pod tym numerem. Jeśli Border Express go tak nie znajdzie, użyj numeru przesyłki z e-maila sklepu.

**Co widzisz.** Peek pokazuje status, każdy datowany etap z miejscowością i stanem, przewidywany dzień doręczenia aż do dostawy, godzinę doręczenia, miejscowość odbiorcy (dzielnicę/miejscowość, stan i kod pocztowy) oraz osobę podpisującą po doręczeniu. Pomija pasek postępu, datę nadania, liczbę sztuk oraz skany pojedynczych etykiet, a także miejscowość nadawcy; nie pokazuje wagi ani potwierdzenia odbioru. Każdy etap zachowuje strefę czasową swojego stanu, dzięki czemu etapy ze stanów w różnych strefach układają się we właściwej kolejności i wyświetlają w twojej strefie („Godziny w twojej strefie czasowej:”).

**Jak często.** Dopóki masz przesyłkę otwartą w Peek — lub w dniu doręczenia z włączonymi powiadomieniami — Peek odpytuje bezpośrednio Border Express nawet co 10 minut, a od 6:00 czasu w Sydney w dniu zeskanowania w doręczenie nawet co 2 minuty, dopóki tracker Border Express odpowiada; poza tym, oraz po dwóch dniach bez wiadomości (12 godzin przy statusie „Zarejestrowana” lub „Przyjęta przez przewoźnika”), co godzinę. Jeśli tracker Border Express jest nieosiągalny, Peek pyta usługi śledzenia i pokazuje ich zapis ich własnymi słowami. Może wysyłać alerty „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek nie skontaktuje się za ciebie z Border Express ani ze sklepem.

## Pytania o śledzenie Border Express

### Czym jest numer przesyłki Border Express?

Numerem, pod którym Border Express śledzi dostawę: 8 cyfr albo 4 litery nadawcy i cyfry. Obejmuje wszystkie sztuki w danej dostawie. Sklep podaje go w e-mailu wysyłkowym lub na stronie zamówienia.

### Czy mogę śledzić Border Express za pomocą kodu kreskowego z etykiety?

W Peek tak: wklej 20-znakowy kod kreskowy (4 litery, `BX` i 14 cyfr) i wybierz Border Express. Peek wylicza z niego numer przesyłki. Własny tracker Border Express przyjmuje wyłącznie numer przesyłki.

### Co znaczy „On Board For Delivery” w Border Express?

Paczka znajduje się w pojeździe doręczającym. Peek pokazuje ją jako „W doręczeniu”.

### Dlaczego moje śledzenie Border Express pokazuje „Partially Delivered”?

Przesyłka składa się z kilku sztuk i doręczono dopiero część z nich; reszta jest jeszcze w drodze, więc Peek pokazuje „W transporcie”. `Partially Delivered - Final` znaczy co innego: Border Express zamknęło przesyłkę sztukami, które dotarły, a Peek pokazuje „Dostarczona”.

### Co znaczy „Shipment Data Received” w Border Express?

Nadawca przesłał Border Express dane paczki, ale Border Express jej jeszcze nie zeskanowało. Peek pokazuje „Zarejestrowana”.

:::sources
- [Universal Parcel Scraper: Border Express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/border-express) – nazwy, Australia, numery przesyłek i kody kreskowe z etykiet, linki śledzenia, Quick Track, opisy i etapy, etapy bez godziny, zegary stanowe, przewidywana data i godzina doręczenia, miejscowość odbiorcy i podpisujący, pola pomijane, brak innego przewoźnika
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – układy numerów innych przewoźników (Mondial Relay, Colisweb, Heppner, Colis Privé), nazwa BEX Express, brak przekazania do lub z Border Express
:::
