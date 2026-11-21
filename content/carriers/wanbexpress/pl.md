---
title: Śledzenie WanbExpress: numery WNB…YQ, statusy i doręczenie
description: Śledź przesyłkę WanbExpress (Wanb Express) z Chin: jak wygląda numer WNB…YQ, co oznaczają angielskie statusy, kto doręcza paczkę i co zrobić, gdy utknie.
slug: sledzenie-wanbexpress
published: 2026-11-21
updated: 2026-11-21
---

WanbExpress (także Wanb Express, WanBang Express lub WANB) to chiński przewoźnik wysyłający paczki za granicę, gdzie doręcza je lokalny przewoźnik. Jeśli sklep nadał zamówienie przez WanbExpress, numer zaczyna się od `WNB` i kończy na `YQ`: wklej go w pole powyżej. Własna strona śledzenia WanbExpress, tracking.wanbexpress.com, może również podać przewoźnika doręczającego w twoim kraju oraz jego własny numer przesyłki.

## Numer śledzenia WanbExpress: jak wygląda

Listy przewozowe WanbExpress składają się z `WNB`, dwóch liter, dziesięciu cyfr i `YQ`, razem 17 znaków. Przykłady ze zmyślonymi cyframi:

| Numer (zmyślony) | Co to jest |
| --- | --- |
| `WNBKG4829105736YQ` | `WNB`, dwie litery, dziesięć cyfr i `YQ`: numer WanbExpress. |
| `WNBKG482910573YQ` | Tylko dziewięć cyfr: nie pasuje do formatu WanbExpress. Sprawdź, czy nie brakuje cyfry. |
| `JSEKG4829105736YQ` | Również kończy się na `YQ`, ale zaczyna od `JSE`: to numer [JS Express](carrier:js-express). |
| `JXCKG4829105736YQ` | Zaczyna się od `JXC`: to numer [Juxi Express](carrier:juxi-express). |

Wielu chińskich spedytorów kończy swoje numery na `YQ`, a każdy ma własny początek: `WNB` oznacza WanbExpress. Spacje, myślniki i małe litery nie mają znaczenia po wklejeniu numeru w Peek.

**Gdzie go znaleźć.** Sklep podaje numer w e-mailu o wysyłce lub na stronie zamówienia. Jeśli go nie widzisz, zajrzyj do poradnika [jak znaleźć numer przesyłki](guide:find-tracking-number).

**Link do śledzenia.** Link do strony śledzenia WanbExpress zakończony `?trackingNumbers=` i numerem otwiera tam tę konkretną przesyłkę. Numer `WNB` to część po znaku równości: skopiuj ją stamtąd do Peek.

**Drugi numer.** Śledzenie WanbExpress może pokazywać też numer nadany przez przewoźnika doręczającego. Ten numer należy do drugiej firmy: sprawdź go na jej stronie albo dodaj w Peek jako osobną przesyłkę.

## Statusy śledzenia WanbExpress wyjaśnione

Skany WanbExpress są po angielsku, każdy z godziną i czasem z miejscem. Oto wpisy znane kodowi Peek, w typowej kolejności:

| Co podaje WanbExpress | Co to oznacza dla ciebie | Peek pokazuje |
| --- | --- | --- |
| `Shipping Order Created.`, `Parcel Data Received` | Sklep zgłosił przesyłkę i przekazał dane. WanbExpress nie ma jeszcze paczki. | „Zarejestrowana” |
| `Parcel received at Shenzhen Sorting Centre.` | Sortownia w Shenzhen w Chinach przyjęła paczkę: to pierwszy fizyczny skan. | „Przyjęta przez przewoźnika” |
| `Parcel processed at Shenzhen Sorting Centre.`, `Parcel departure in Shenzhen Sorting Centre.` | Posortowana, a następnie wysłana z tej sortowni. | „W transporcie” |
| `A-Scan: shipment in international transit` | Skan przyjęcia przekazany między przetworzeniem w sortowni a jej opuszczeniem. | „W transporcie” |
| `Customs declaration completed` | Złożono zgłoszenie celne. Treść nie wskazuje kraju i nie oznacza odprawy przywozowej – ta pojawia się jako `Custom clearance completed`. | „W transporcie” |
| `Customs inspection has not yet released` | Kontrola eksportowa w kraju nadania, po zgłoszeniu celnym a przed lotem. | „W transporcie” |
| `Parcel departure in Transit Facility, Parcel Departure` | Paczka opuściła obiekt tranzytowy. | „W transporcie” |
| `Parcel is leaving Airport, Parcel Leaving Port` | Odlot samolotem w kierunku kraju docelowego. | „W transporcie” |
| `Parcel arrives at Airport, Parcel Arrival` | Wylądowała w twoim kraju lub w jego pobliżu. Dalej następuje odprawa celna. | „W transporcie” |
| `Custom clearance completed` | Urząd celny w kraju docelowym zwolnił paczkę. | „W transporcie” |
| `Parcel received by in JFK01.` | WanbExpress zapisuje to jako odbiór na ostatni odcinek, bez nazwy firmy odbierającej. | „W transporcie” |
| `Your package is being prepared by the sender and will be handed over to us soon`, `Your parcel will soon be handed over to us! It is being prepared by the sender.`, `Order information received. We're expecting your parcel to arrive with us.` | Komunikat partnera przekazany bez kodu WanbExpress: paczka nie trafiła jeszcze do niego. | „Zarejestrowana” albo późniejszy etap, jeśli wcześniejszy skan już ją przesunął |
| `In transit along Poste Italiane’s logistic network` | W drodze w sieci przewoźnika doręczającego, tutaj [Poste Italiane](carrier:poste-italiane). | „W transporcie” |
| `Parcel is out for delivery.` | W trasie do doręczenia. | „W doręczeniu” |
| `Consignee absent, Consignee could not be found.` | Kurier partnera nikogo nie zastał. | „Próba doręczenia” |
| `Placed in Parcelshop/Parcel locker` | Po nieudanej próbie partner zostawił paczkę w punkcie odbioru lub automacie paczkowym. | „Do odbioru” |
| `Parcel has been delivered.(Front door)` | Doręczona. Dopisek `(Front door)` określa miejsce zostawienia paczki. | „Dostarczona” |

**Skany przewoźnika doręczającego.** Po przylocie śledzenie WanbExpress przekazuje komunikaty lokalnego przewoźnika w jego własnym brzmieniu, zwykle bez kodu WanbExpress. W przesyłce do Niemiec historia pokazuje doręczenie przez [iMile](carrier:imile), z nazwą centrum lub stacji w nawiasach `【】`:

| Co podaje iMile | Co to oznacza dla ciebie | Peek pokazuje |
| --- | --- | --- |
| `Received.` | Centrum iMile w kraju docelowym przyjęło paczkę. | „W transporcie” |
| `Arrived.` | Dotarła do stacji doręczeń obsługującej twoją okolicę. | „W transporcie” |
| `Dispatch task assigned to DA.` | Kierowca (DA, delivery associate) został przydzielony do trasy, często na kilka godzin przed wyjazdem. | „W transporcie” |
| `Shipment out for delivery.` | W trasie do doręczenia. | „W doręczeniu” |
| `<Route not completed>`, `Back To DS.` | Trasa skończyła się bez doręczenia i paczka wróciła do stacji doręczeń (DS). | „Próba doręczenia” (samodzielnie po trasie; po nieudanej próbie `Back To DS.` zostaje komunikatem bez zmiany etapu, a po „W transporcie” pozostaje „W transporcie”) |
| `Scheduled to <2026-11-02> .` | Dzień zaplanowany na kolejną próbę po powrocie na stację. | Nadal „Próba doręczenia” |

Zdarzenia innych partnerów pojawiają się tak samo. W przesyłkach do USA `Parcel received by Transit Facility.` oznacza przyjęcie w obiekcie tranzytowym lokalnego partnera, `In Processing at Transit Facility.` sortowanie, `In Transit to Next Facility.` przewóz do kolejnego obiektu, a `Parcel received by local Facility.` przyjęcie w placówce doręczającej przed wyjazdem. `It is currently in transit to the next facility` i `The package is being transferred to the next facility` opisują przewóz między obiektami po przylocie, a `Shipment arrived to sorting centre / Shipment in the sorting centre` przyjęcie w sortowni partnera. Wszystkie siedem Peek pokazuje jako „W transporcie”. W przesyłce do Wielkiej Brytanii wpisy [InPost](carrier:inpost) mogą pojawić się jeszcze przed skanem sortowni WanbExpress: `Your parcel is on its way to InPost. Please check back for updates` („Zarejestrowana”), a następnie `We have your parcel. Please check back for updates` („Przyjęta przez przewoźnika”). `We'll apply your My Account preferences to this parcel delivery` to tylko informacja InPost o ustawieniach konta, nie skan: Peek zachowuje ją w historii bez zmiany etapu. Tak samo traktuje `Consignee Contacted`, czyli telefon od kuriera zapisany w tej samej minucie co nieudana próba.

**Godziny.** Każdy skan ma podane przesunięcie względem UTC. Peek odczytuje ten czas i wyświetla wszystkie godziny w strefie czasowej twojego urządzenia, od najnowszej do najstarszej. O typowych przerwach i przekazaniach paczek z Chin przeczytasz w poradniku [śledzenie przesyłek z Chin](guide:tracking-from-china).

## Kto doręcza przesyłki WanbExpress

Skany WanbExpress śledzą paczkę z Chin, przez odprawę celną, aż do lokalnego przewoźnika, który ją doręcza. Strona tracking.wanbexpress.com może podać jego nazwę, własny numer oraz zdjęcie z doręczenia.

**Co wynika z kodu Peek.** W przesyłce do Niemiec historia WanbExpress pokazuje doręczenie przez iMile, a jeden ze skanów wskazuje sieć Poste Italiane. W paczce do Wielkiej Brytanii pojawiają się wpisy InPost. Kod uwzględnia też przepakowywanie na numery Cainiao Express we Francji (`DOFR…HD` lub `CNFR…HD`) oraz skany przekazywane od J&T Express Australia. Dla USA, Irlandii czy Kanady kod Peek nie wskazuje konkretnego przewoźnika doręczającego.

**Próby doręczenia i odbiór.** Obowiązują zasady lokalnego przewoźnika: liczba prób, awizo, miejsce odbioru i czas przechowywania. Peek nie znalazł własnych zasad WanbExpress w tych sprawach. Gdy poznasz lokalnego przewoźnika i jego numer, sprawdź jego śledzenie i z nim ustal ponowne doręczenie. Zobacz [nieudane doręczenie](guide:missed-delivery).

## Paczka WanbExpress utknęła lub nie dociera

**Nie znaleziono numeru.** Jeśli śledzenie WanbExpress nie zna numeru, Peek pokazuje „Oczekiwanie na przewoźnika WanbExpress” i nadal pyta. Gdy po dwóch dniach WanbExpress wciąż go nie rozpoznaje, a żadne inne źródło nie odpowiedziało, Peek wyświetla „Po dwóch dniach przewoźnik nadal nie zna tego numeru. Sprawdź go, wybierz innego przewoźnika lub usuń przesyłkę.” i sprawdza go raz dziennie, a po tygodniu od dodania raz w tygodniu. Porównaj numer znak po znaku z wiadomością od sprzedawcy: `WNB`, dwie litery, dziesięć cyfr, `YQ`. Jeśli po kilku dniach nadal nic nie ma, zapytaj sprzedawcę, czy paczka naprawdę została wysłana.

**Stoi na `Shipping Order Created.` lub `Parcel Data Received`.** Sklep zgłosił wysyłkę, ale WanbExpress nie zeskanował jeszcze paczki. Zapytaj sklep, kiedy przekazał ją przewoźnikowi.

**Stoi po `Parcel arrives at Airport, Parcel Arrival`.** Paczka najpewniej czeka na odprawę celną przywozową. W Unii Europejskiej zakupy internetowe spoza UE podlegają od 1 lipca 2026 r. tymczasowemu cłu w wysokości 3 € za pozycję (rodzaj towaru) dla przesyłek o wartości do 150 € oraz, od 1 listopada 2026 r., unijnej opłacie manipulacyjnej 2 € za pozycję, niezależnie od wartości. Jeśli otrzymasz wezwanie do zapłaty należności przywozowych, zobacz [przesyłki zatrzymane na cle](guide:customs); jeśli przestój się przedłuża, poinformuj sprzedawcę. Więcej w [dlaczego śledzenie nie aktualizuje się](guide:tracking-not-updating).

**Nic nowego po `Custom clearance completed`.** Lokalny przewoźnik ma już paczkę albo zaraz ją odbierze, a jego śledzenie może wyprzedzać WanbExpress. Poszukaj jego nazwy i numeru na tracking.wanbexpress.com albo zapytaj sprzedawcę.

**Próba doręczenia.** Po wpisie takim jak `<Route not completed>` to przewoźnik doręczający decyduje o kolejnej próbie i miejscu odbioru. Kontaktuj się z nim, nie z WanbExpress.

**Oznaczona jako dostarczona, ale nic nie dotarło.** Sprawdź, czy na tracking.wanbexpress.com jest zdjęcie z doręczenia, a potem skontaktuj się z lokalnym przewoźnikiem i sprzedawcą. Zobacz [dostarczona, ale nieotrzymana](guide:delivered-not-received).

**Zaginiona lub zwrócona.** Umowę z WanbExpress ma sprzedawca, więc o zwrot pieniędzy lub ponowną wysyłkę zwróć się do niego albo do platformy sprzedażowej. Zobacz [zaginiona przesyłka](guide:lost-parcel) i [zwrot do nadawcy](guide:returned-to-sender).

## Obsługa klienta i kontakt z WanbExpress

Peek nie mógł sprawdzić strony internetowej WanbExpress, więc nie podaje tutaj danych kontaktowych tej firmy. W sprawie zamówienia skontaktuj się ze sprzedawcą, który nadał przesyłkę, albo z platformą, jeśli sprzedawca nie odpowiada. W sprawie samego doręczenia zwróć się do lokalnego przewoźnika.

## Śledzenie przesyłki WanbExpress w Peek

[Peek](/) rozpoznaje numer WanbExpress po jego kształcie (`WNB`, dwie litery, dziesięć cyfr i `YQ`, nieużywanym przez żadnego innego przewoźnika w katalogu) i pyta śledzenie WanbExpress wyłącznie na podstawie tego numeru, bez konta, kodu pocztowego ani numeru telefonu.

Ponieważ strona śledzenia WanbExpress wymaga weryfikacji przed udzieleniem odpowiedzi, Peek sprawdza ją przez przeglądarkę. Gdy śledzenie WanbExpress nie odpowiada, Peek sięga po usługi śledzenia i pokazuje zgromadzoną przez nie historię, w ich własnym brzmieniu; może ona mieć opóźnienie względem WanbExpress.

Ze śledzenia WanbExpress Peek pobiera status, do 100 najnowszych skanów wraz z miejscem oraz kraje nadania i przeznaczenia z rekordu. Gdy rekord oznacza przesyłkę jako będącą w transporcie, w doręczeniu lub dostarczoną, Peek trzyma się tego statusu, nawet jeśli treść ostatniego skanu mówi mniej. Jeśli WanbExpress zwraca numer lokalnego przewoźnika, a jego panel ostatniej mili wskazuje przewoźnika z katalogu, do którego pasuje ten numer, Peek może pokazać „Doręcza: …” (lub „Zgłoszona przewoźnikowi …, jeszcze nieprzyjęta”) i przełączyć śledzenie na niego. Peek nie podaje przewidywanej daty doręczenia i pomija zdjęcia z doręczenia oraz adres odbiorcy – znajdziesz je na tracking.wanbexpress.com, a numer lokalnego przewoźnika możesz też dodać osobno w Peek. Peek śledzi ponad 4500 przewoźników.

Dopóki masz przesyłkę otwartą na ekranie albo w dniu doręczenia przy włączonych powiadomieniach, a strona WanbExpress odpowiada, Peek sprawdza ją nawet co 10 minut, a od godz. 6:00 czasu przewoźnika w dniu trafienia do doręczenia co 5 minut, gdy sprawdzenie otwiera przeglądarkę; w pozostałym czasie co godzinę. Przez usługi śledzenia sprawdza co godzinę, a w dniu doręczenia nawet co 15 minut, dopóki masz ją otwartą lub włączone powiadomienia. Po dwóch dniach bez nowych wpisów (po 12 godzinach na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika”) Peek sprawdza przesyłkę co godzinę. Możesz włączyć powiadomienia „Każdy skan”, „Ważne etapy” albo „Tylko doręczenie”. „Ważne etapy” (dostawa, odbiór, odprawa celna, nieudane doręczenia i zwroty) obejmują wyjazd do doręczenia, nieudaną próbę i dostawę, lecz nie obejmują wpisów celnych WanbExpress, które Peek zalicza do etapu „W transporcie”. Peek nie skontaktuje się za ciebie z WanbExpress ani ze sprzedawcą.

## Pytania o śledzenie WanbExpress

### Czym jest WanbExpress?

Chińskim przewoźnikiem, występującym też jako Wanb Express, WanBang Express lub WANB. W kodzie Peek jego skany zaczynają się w sortowni w Shenzhen, obejmują lot i odprawę celną, a kończą wpisami przewoźnika doręczającego w kraju docelowym.

### Gdzie mogę śledzić przesyłkę WanbExpress?

W polu powyżej albo na własnej stronie śledzenia WanbExpress pod adresem tracking.wanbexpress.com. Strona wymaga weryfikacji przeciw botom i może podać również lokalnego przewoźnika. Wpisz cały numer `WNB…YQ`.

### Co oznacza „Customs declaration completed” w śledzeniu WanbExpress?

Że dla paczki zakończono zgłoszenie celne. Wpis nie wskazuje kraju ani nie oznacza zwolnienia przez urząd celny w kraju docelowym – ten etap pojawia się jako `Custom clearance completed`. Peek pokazuje oba jako „W transporcie”.

### Kto doręcza paczki WanbExpress w moim kraju?

Lokalny przewoźnik, którego nazwę i numer może podać strona tracking.wanbexpress.com. Kod Peek pokazuje iMile przy paczce do Niemiec, wpisy InPost do Wielkiej Brytanii i skan wspominający sieć Poste Italiane, a także przekazywanie do J&T Express Australia lub na numery `DOFR…HD`/`CNFR…HD` we Francji; w pozostałych krajach sprawdź tracking.wanbexpress.com albo zapytaj sprzedawcę.

### Z kim się skontaktować, jeśli paczka WanbExpress zaginęła?

Ze sprzedawcą, u którego złożono zamówienie: to on wykupił przesyłkę w WanbExpress. Peek nie mógł sprawdzić strony WanbExpress pod kątem danych kontaktowych.

:::sources
- [Universal Parcel Scraper: WanbExpress](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/wanbexpress) – nazwy, format `WNB…YQ`, sprawdzanie przez przeglądarkę, treści skanów i ich etapy (w tym `Customs inspection has not yet released`, `A-Scan: shipment in international transit`, wpisy iMile, InPost, Poste Italiane i partnerów w USA), godziny z przesunięciem UTC, rozpoznawanie przewoźnika doręczającego (`TrackLastMileCarrier`), pominięte zdjęcia i adres, 100 najnowszych skanów
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – inne formaty zakończone na `YQ` (`JS Express`, `Juxi Express`), zapasowe usługi śledzenia i przejęcie przez przewoźnika lokalnego
- [Komisja Europejska: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – tymczasowe cło 3 € za pozycję do 150 € od 1 lipca 2026 r., unijna opłata manipulacyjna 2 € od 1 listopada 2026 r.
- [Komisja Europejska: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – opłata manipulacyjna 2 € za pozycję niezależnie od wartości
:::
