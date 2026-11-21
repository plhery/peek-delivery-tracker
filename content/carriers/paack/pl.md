---
title: Paack śledzenie przesyłek: numer zamówienia, kod i statusy
description: Śledź paczkę Paack: numer zamówienia i kod pocztowy doręczenia, znaczenie statusów, przedziały czasowe, punkty PaackGo Point, czat i połączenie z Colis Privé.
slug: paack-sledzenie
published: 2026-11-17
updated: 2026-11-20
---

Paack doręcza zamówienia ze sklepów internetowych na tzw. ostatniej mili — pod drzwi albo do punktu odbioru PaackGo Point. Firma działa lokalnie w ponad 50 miastach w Hiszpanii, Francji, Włoszech i Portugalii oraz doręcza brytyjskie przesyłki z sieci [Evri](carrier:evri) International; w Polsce nie prowadzi krajowych doręczeń, więc z Paack spotkasz się przy zamówieniach wysyłanych na adres w Hiszpanii, we Włoszech, we Francji lub w Portugalii. Aby sprawdzić paczkę na [stronie śledzenia Paack](https://paack.co/shipments/tracking?lng=en), wpisz **numer zamówienia** oraz **kod pocztowy doręczenia** — albo wklej numer w polu powyżej, wybierz Paack i dodaj kod pocztowy. Paack sprawdza oba pola łącznie: jeśli którekolwiek się nie zgadza, nie pokaże żadnych danych.

## Numer śledzenia Paack: gdzie go znaleźć i jak wygląda

Strona śledzenia Paack wymaga numeru zamówienia nadanego przez sklep albo numeru kodu kreskowego z etykiety paczki, zawsze w parze z kodem pocztowym adresu dostawy. Ponieważ większość numerów pochodzi bezpośrednio z systemów sklepów, nie mają jednego stałego wzoru: szukaj ich w wiadomościach od sprzedawcy, w e-mailu z linkiem od Paack albo na etykiecie. Nie widzisz numeru? Zapytaj sklep lub sprawdź [jak znaleźć numer śledzenia](guide:find-tracking-number).

Część etykiet ma jednak rozpoznawalny układ, natomiast numery zamówień ze sklepów potrafią wyglądać jak numery innych kurierów (przykłady zmyślone):

| Numer zamówienia lub etykiety | Co oznacza i do czego jest podobny |
| --- | --- |
| `PK123456789012345678` | `PK` i 18 cyfr: przesyłka [Evri](carrier:evri) International doręczana przez Paack; w Peek działa bez kodu pocztowego. |
| `534012345678901234` | `5340` i 14 cyfr (łącznie 18 cyfr): numer etykiety spotykany na paczkach Paack. |
| `C26111512345678` | `C` lub `E`, 6-cyfrowa data (`RRMMDD`) i 8 cyfr: numer paczki z [zamówień SHEIN](guide:shein-orders), w Hiszpanii obsługiwany też przez [Ecoscooting](carrier:ecoscooting). |
| `123456789AB` | Litery i cyfry bez stałego wzoru: nie wskazują na konkretnego przewoźnika (12 znaków alfanumerycznych pasuje też do Colis Privé). |
| `12345678` | 8 cyfr: format używany również przez Mondial Relay, Colisweb i Heppner. |
| `123456789012` | 12 cyfr: format używany również przez [FedEx](carrier:fedex), [Poste Italiane](carrier:poste-italiane), BRT czy Colis Privé. |

Jeżeli sklep informuje, że paczkę wiezie Paack, numer należy sprawdzać w Paack niezależnie od jego długości — więcej w poradniku [formaty numerów śledzenia](guide:tracking-number-formats). Błędny numer lub kod pocztowy zwraca komunikat `Order not found` / `Incorrect order number or postal code`. Dopóki nie dostaniesz potwierdzenia wysyłki lub przyjęcia paczki, Paack zaleca kontakt ze sprzedawcą.

## Co oznaczają statusy śledzenia Paack

Strona Paack podsumowuje drogę paczki w czterech głównych krokach (otrzymano dane zamówienia, w centrum dystrybucyjnym Paack, w doręczeniu, doręczono), a szczegółowa historia dopisuje etapy pośrednie. Peek wyświetla je własnymi polskimi nazwami, pozostawiając trzy komunikaty techniczne po angielsku:

| Status w Peek | Co oznacza dla ciebie |
| --- | --- |
| `Otrzymano dane przesyłki` | Sklep przesłał dane twojego zamówienia do Paack, ale przewoźnik nie ma jeszcze paczki. |
| `Przesyłka przyjęta przez przewoźnika` | Pierwszy skan fizycznej paczki w centrum dystrybucyjnym Paack. |
| `W transporcie` | Paczka jest w sieci Paack, ale nie wyjechała jeszcze do doręczenia. |
| `Courier assigned` | Do doręczenia przypisano kuriera. Wpis pojawia się ponownie przed każdą kolejną próbą. |
| `W doręczeniu` | Kurier ma paczkę w aucie i doręczy ją w podanym przedziale godzinowym. |
| `Delivery attempt failed`, `Problem z doręczeniem` | Tym razem nie udało się doręczyć: kurier nie zastał nikogo pod adresem albo punkt PaackGo Point był zamknięty, przepełniony lub odmówił przyjęcia paczki. |
| `New delivery attempt scheduled` | Paack zaplanował kolejną próbę w nowym oknie czasowym. Gdy przewoźnik rejestruje ją dokładnie w chwili nieudanej próby, stanowi ona część tamtego zdarzenia i Peek nie wyświetla osobnego wiersza. |
| `Do odbioru` | Paczka czeka w punkcie PaackGo Point; strona Paack podaje ostatni dzień na odbiór. |
| `Dostarczona` | Doręczona pod adres lub odebrana w punkcie PaackGo Point. |
| `W drodze do nadawcy`, `Zwrócona nadawcy` | Paczka wraca do sklepu albo już tam dotarła — np. po kilku nieudanych próbach, nieodebraniu w terminie, odmowie przyjęcia lub braku opłaty w PaackGo Point. |
| `Problem z przesyłką` | Usterka dotycząca samej paczki, np. uszkodzenie lub zagubienie w punkcie PaackGo Point: skontaktuj się ze sklepem. |

Więcej o komunikatach kurierskich przeczytasz w przewodniku [statusy śledzenia przesyłek](guide:tracking-statuses).

## Przedziały godzinowe doręczenia, nieobecność i punkty PaackGo Point

**Okno doręczenia.** Strona Paack pokazuje przewidywany dzień i przedział godzinowy dostawy według czasu lokalnego adresu odbiorcy (albo kilkudniowy zakres dat). Po nieudanej próbie widoczne okno dotyczy kolejnego podjazdu kuriera. Zależnie od usługi wykupionej przez sklep możesz wybrać konkretne godziny dostawy; w przeciwnym razie przez czat Paack przekażesz wskazówki dla kuriera, choć przewoźnik nie gwarantuje spełnienia prośby o konkretną godzinę.

**Gdy nie ma cię w domu.** Każde zamówienie w sieci Paack ma zaplanowane **co najmniej dwie próby doręczenia** (dokładna liczba zależy od umowy sprzedawcy z Paack), przy czym druga odbywa się następnego dnia lub w najbliższym dniu roboczym; zobacz też [nieudana próba doręczenia](guide:missed-delivery). Jeśli kurier nie może doręczyć przesyłki bezpośrednio pod twój adres, Paack przewiduje dwie opcje zastępcze — o ile wcześniej ich nie wykluczysz: pozostawienie paczki u **najbliższego dostępnego sąsiada** (`Delivery to a neighbor`) albo u **portiera / w recepcji budynku** (`Condo concierge`). W razie błędnego adresu przy pierwszej próbie Paack wysyła e-mail z opcją poprawy danych; adres można też uzupełnić lub zmienić na czacie, pod warunkiem że sprzedawca na to pozwala i paczka nie jest jeszcze w doręczeniu. Po kilku nieudanych próbach przesyłka wraca do nadawcy (dlatego w śledzeniu może zmienić się widoczny adres dostawy). Paack nie zostawia papierowych potwierdzeń odbioru — wszystkie pokwitowania zapisuje elektronicznie i udostępnia sprzedawcy.

**Punkty PaackGo Point.** To punkty odbioru współpracujące z Paack. Gdy paczka trafi do punktu, na stronie Paack zobaczysz nazwę i adres PaackGo Point, kod odbioru, kod QR oraz ostateczny termin odbioru. Nieodebrana w terminie paczka wraca do sklepu; sprawdź też [nieodebrana przesyłka](guide:uncollected-parcel).

## Paczka Paack opóźniona, niedostarczona lub uszkodzona

Umowę zakupu zawierasz ze sklepem internetowym: w Unii Europejskiej sprzedawca odpowiada za uszkodzenie towaru „od momentu wysyłki do chwili odbioru” i — o ile nie uzgodniono inaczej — ma 30 dni na dostarczenie zamówienia. Zwroty pieniędzy, ponowna wysyłka i anulowanie zamówienia odbywają się przez sklep:

- **Brak nowych skanów lub opóźnienie:** zajrzyj do poradnika [śledzenie nie aktualizuje się](guide:tracking-not-updating); gdy mija termin dostawy, skontaktuj się ze sprzedawcą i wyznacz mu rozsądny dodatkowy czas przed odstąpieniem od umowy.
- **Status doręczono, ale paczki nie ma:** zapytaj domowników, sąsiadów oraz portiera/administrację budynku, a następnie zgłoś sprawę sprzedawcy, który ma wgląd w elektroniczne potwierdzenie doręczenia Paack. Więcej w artykule [dostarczona, ale nieotrzymana](guide:delivered-not-received).
- **Uszkodzona, zagubiona lub zwrócona do sklepu:** przesyłka odesłana do sprzedawcy nie znajduje się już w rękach Paack — skontaktuj się bezpośrednio ze sklepem i zobacz poradniki [uszkodzona paczka](guide:damaged-parcel) oraz [zagubiona paczka](guide:lost-parcel).

## Kontakt z Paack i obsługa klienta

Według anglojęzycznego centrum pomocy Paack (stan na listopad 2026 r.):

- **Czat online:** widoczny w prawym dolnym rogu [centrum pomocy Paack](https://paackcustomerservice.freshdesk.com/en/support/home) oraz na [stronie śledzenia Paack](https://paack.co/shipments/tracking?lng=en). Służy do dopisywania wskazówek dla kuriera oraz korekty adresu (jeśli sklep na nią pozwala).
- **Wiadomości e-mail od Paack:** przewoźnik wysyła e-mail z linkiem po nadaniu zamówienia oraz powiadomienia w razie problemów z adresem lub opóźnień.
- **Telefon i e-mail dla odbiorców:** w międzynarodowym centrum pomocy Paack nie publikuje numeru telefonu ani adresu e-mail dla odbiorców paczek (formularz kontaktowy na stronie głównej jest przeznaczony dla sklepów i marek e-commerce).

## Śledzenie paczek Paack w aplikacji Peek

[Peek](/) od razu rozpoznaje jako Paack numery złożone z liter `PK` i 18 cyfr — nadawane przesyłkom, które Paack doręcza dla [Evri](carrier:evri) International — i śledzi je bez kodu pocztowego. Dla 18-cyfrowych numerów etykiet zaczynających się od `5340` przycisk „Zmień” ustawia Paack na pierwszym miejscu w sekcji „Pasują do numeru”, przed innymi przewoźnikami używającymi 18 cyfr (w Belgii po bpost). Również przy numerze paczki SHEIN (`C` lub `E`, sześciocyfrowa data rok-miesiąc-dzień i 8 cyfr) Paack pojawia się w tej sekcji, podczas gdy Peek pyta tylko [Ecoscooting](carrier:ecoscooting). Pozostałe numery Paack nie mają własnego wzoru, a bez kodu pocztowego Peek nie może odpytać Paack automatycznie: ciąg zawierający litery zostaje zwykle bez przypisanego kuriera („Peek poszuka po dodaniu.”), natomiast same cyfry mogą zostać przypisane lub sprawdzone u innych przewoźników o tej samej długości numeru (np. [FedEx](carrier:fedex), [DHL](carrier:dhl), [Packeta](carrier:packeta) czy [Poste Italiane](carrier:poste-italiane)). Naciśnij wtedy „Zmień” i wskaż Paack albo wklej link zawierający `tracking_number=` z domeny `mydeliveries.paack.app` lub `paack.co/shipments/tracking`.

Następnie uzupełnij pole „Kod pocztowy odbiorcy”. Jest oznaczone jako opcjonalne, ponieważ numer `PK` go nie wymaga, ale wszystkie inne zamówienia Paack pokazuje wyłącznie z kodem pocztowym miejsca dostawy, a kod zaszyty w samym linku nie jest odczytywany. Jeśli podawałeś już wcześniej kod pocztowy, Peek podpowie go pod polem („Użyj …”), zarówno przy dodawaniu paczki, jak i w opcji „Zmień przewoźnika”. Kod trafia do Paack oraz — w razie potrzeby — do serwisów śledzenia; gdy numer i kod pocztowy do siebie nie pasują, zamówienie wygląda na nieznane. Peek zamienia litery na wielkie i usuwa spacje, kropki oraz myślniki: jeśli nic się nie pojawia, sprawdź też numer dokładnie w oryginalnym zapisie bezpośrednio na stronie Paack.

W Peek zobaczysz bieżący status, historię zdarzeń oraz przewidywany dzień i przedział godzinowy według czasu lokalnego adresu doręczenia (przy oknie kilkudniowym pierwszy i ostatni dzień; dla hiszpańskich kodów pocztowych zaczynających się od `35` lub `38` czas Wysp Kanaryjskich). Godziny skanów wyświetlają się w twojej strefie czasowej — na peektracker.com i w aplikacjach na iPhone’a i Androida informuje o tym dopisek pod historią („Godziny w twojej strefie czasowej: …”). Gdy paczka czeka w PaackGo Point, Peek pokazuje status „Do odbioru” wraz z nazwą i adresem punktu (obsługa przygotowana na podstawie kodu strony Paack, jeszcze niezaobserwowana na żywej paczce), nigdy jednak nie wyświetla kodu odbioru, kodu QR ani daty granicznej — po te dane otwórz stronę Paack. Odebranie przesyłki z punktu widnieje jako „Dostarczona”. Strona Paack nie podaje miejscowości skanów na mapie ani kolejnego przewoźnika. Z kolei dla numeru `PK` bez kodu pocztowego historia pochodzi z systemu Evri International — po angielsku, z miejscowościami i wagą, lecz bez okna godzinowego; jeśli do numeru `PK` dodasz także kod pocztowy, Peek najpierw odpyta stronę Paack o dokładne czasy i przedział dostawy, a dopiero gdy strona Paack nie rozpozna pary, skorzysta z Evri International. Nazwa sklepu oraz twoje imię, nazwisko i adres są pomijane. Peek sprawdza status nawet co 10 minut, a w dniu wydania paczki do doręczenia nawet co 2 minuty od 6:00 czasu lokalnego lub na pół godziny przed otwarciem okna dostawy, dopóki odpowiada bezpośredni system Paack lub Evri (przez zewnętrzne serwisy śledzenia co 15 minut), oferując powiadomienia „Wszystkie aktualizacje”, „Tylko ważne” lub „Tylko w dniu dostawy”. Po dwóch dniach bez zmian albo po 12 godzinach na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika” sprawdzanie przechodzi w tryb godzinowy.

## Najczęstsze pytania o śledzenie Paack

### Czym jest Paack i co łączy go z Colis Privé?

Paack to firma kurierska ostatniej mili specjalizująca się w dostawach w wybranym oknie czasowym dla sklepów internetowych, obecna lokalnie w Hiszpanii, Francji, Włoszech i Portugalii. 16 października 2026 r. firma Colis Privé ogłosiła, że po sfinalizowaniu partnerstwa latem 2026 r. Colis Privé i Paack łączą siły pod jedną wspólną marką **Paack**, wdrażaną stopniowo od stycznia 2027 r. (ze wspólną siecią we Francji, Belgii, Luksemburgu, Hiszpanii i Portugalii). Do tego czasu doręczenia i śledzenie działają bez zmian u przewoźnika wskazanego przez sklep.

### Dlaczego Paack prosi o kod pocztowy i wyświetla „Order not found”?

Paack udostępnia informacje o zamówieniu tylko osobie podającej zarówno numer zamówienia (lub etykiety), jak i kod pocztowy adresu doręczenia — ta para działa jak hasło. Wpisanie polskiego kodu z adresu rozliczeniowego zamiast np. hiszpańskiego lub włoskiego kodu dostawy sprawi, że strona zwróci `Order not found`, jakby paczka nie istniała.

### Co oznacza przełożenie doręczenia przez Paack?

Przewoźnik podejmie kolejną próbę, standardowo następnego dnia lub w pierwszym dostępnym dniu roboczym. Po nieudanym podjeździe Peek pokazuje `Delivery attempt failed`, a strona Paack aktualizuje datę i przedział godzinowy kolejnej wizyty: jeśli Paack zaplanuje ją w tej samej sekundzie co nieudaną próbę, Peek nie tworzy osobnego wpisu; jeśli zrobi to później, zobaczysz `New delivery attempt scheduled`. Gdy problem dotyczy doręczenia do zamkniętego lub pełnego punktu PaackGo Point, w Peek pojawi się `Problem z doręczeniem`.

### Ile trwa dostawa kurierem Paack?

Paack realizuje dla sklepów dostawy tego samego dnia, następnego dnia roboczego oraz w zaplanowanych przedziałach godzinowych — konkretny dzień i okno czasowe pojawiają się na stronie Paack oraz w Peek po przyjęciu paczki do sieci. Przed nadaniem wiążący jest termin podany przez sprzedawcę (w UE maksymalnie 30 dni, o ile nie ustalono inaczej).

:::sources
- [Paack: Track your order (angielski)](https://paack.co/shipments/tracking?lng=en) – numer zamówienia i kod pocztowy doręczenia
- [Paack Support: Home (angielski)](https://paackcustomerservice.freshdesk.com/en/support/home) – druga próba doręczenia, sąsiad i portier, zmiana adresu oraz wskazówki na czacie
- [Paack Support: Status of my order (angielski)](https://paackcustomerservice.freshdesk.com/en/support/solutions/folders/44001208238) – zmiana adresu przy zwrocie do nadawcy, elektroniczne potwierdzenia doręczenia
- [Paack Support: Other questions (angielski)](https://paackcustomerservice.freshdesk.com/en/support/solutions/folders/44001208240) – minimum dwie próby doręczenia, wiadomości e-mail o błędnym adresie
- [Paack Support: About Paack (angielski)](https://paackcustomerservice.freshdesk.com/en/support/solutions/folders/44001208241) – lokalna obecność w ponad 50 miastach w Hiszpanii, Francji, Włoszech i Portugalii, dostawy tego samego lub następnego dnia
- [Colis Privé: Colis Privé and Paack unite under one brand: Paack (angielski)](https://colisprive.fr/en/blog/colis-prive-and-paack-unite-under-one-brand-paack/) – komunikat z 16 października 2026 r.: partnerstwo z lata 2026 r., wspólna marka Paack od stycznia 2027 r., ciągłość śledzenia
- [Your Europe: Wysyłka i dostawa](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_pl.htm) – odpowiedzialność sprzedawcy do chwili odbioru i 30-dniowy termin dostawy w UE
- [Universal Parcel Scraper: Paack (angielski)](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/paack) – formaty `PK`, `5340` i SHEIN, weryfikacja kodu pocztowego, statusy w Peek, przedział dostawy i PaackGo Points
:::
