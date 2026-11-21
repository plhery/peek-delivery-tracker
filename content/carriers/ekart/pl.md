---
title: Śledzenie Ekart: numery ID, statusy InscannedAtDH i RTO
description: Śledź paczkę Ekart w Indiach: 14-znakowy format numeru ID, znaczenie kodów InscannedAtDH oraz RTO, próby doręczenia i śledzenie w Peek.
slug: sledzenie-ekart
published: 2026-11-21
updated: 2026-11-21
---

Ekart (znany także jako *eKart Logistics* oraz *Ekart Express*), prowadzony w Indiach od 2009 roku przez spółkę *Instakart Services Private Limited* z siedzibą w Bengaluru (stan Karnataka), to jeden z największych indyjskich przewoźników obsługujących handel internetowy. Odbiera zamówienia od sprzedawców, przesyła je przez sieć sortowni i doręcza w ponad 15 000 kodów PIN w Indiach. Wklej identyfikator śledzenia z wiadomości ze sklepu w polu powyżej albo wpisz go na [stronie Track Order w serwisie ekartlogistics.in](https://www.ekartlogistics.in/track-order).

## Identyfikator śledzenia Ekart: jak wygląda

Peek rozpoznaje identyfikatory e-commerce Ekart jako trzy litery, następnie `C`, `P` lub `R` i dziesięć cyfr — łącznie 14 znaków (przykłady zmyślone):

| Identyfikator (przykładowy) | Co oznacza |
| --- | --- |
| `FMPP4829105736` | Jedna z 17 trzyliterowych grup przypisanych do Ekart (poniżej), po niej `C`, `P` lub `R` i dziesięć cyfr: Peek rozpoznaje Ekart po samym kształcie. |
| `ABCP4829105736` | Inne trzy litery na początku tego samego układu: numer może należeć do Ekart, dlatego Peek najpierw odpytuje wyszukiwarkę Ekart (szczegóły niżej). |

17 trzyliterowych prefiksów przypisanych bezpośrednio do Ekart to `AMI`, `BAL`, `BGS`, `BSI`, `DEL`, `FLO`, `FMP`, `GLA`, `LUA`, `MYE`, `MYN`, `MYS`, `SDL`, `SHO`, `SPO`, `SRT` oraz `TEL`. Spacje i małe litery nie mają w Peek znaczenia. Dwa wyjątki: numer zaczynający się od `PB` w tym układzie należy do brytyjskiego przewoźnika Parcelforce, natomiast początek `HRBC` (`HRBC4829105736`) odpowiada wzorcowi [Cainiao](carrier:cainiao) / AliExpress i nie jest podpowiadany jako Ekart — jeśli nadał go indyjski sklep przez Ekart, stuknij „Zmień” i wybierz Ekart.

Wpisuj identyfikator śledzenia, a nie numer zamówienia ze sklepu ([jak znaleźć numer śledzenia](guide:find-tracking-number)); dla ładunków B2B/FTL/PTL z numerem LR strona `ekartlogistics.in/track-order` udostępnia osobny odnośnik.

## Statusy śledzenia Ekart — co oznaczają

Wyszukiwarka Ekart podaje przy każdym skanie datę, godzinę i miasto — czasem zwykłym zdaniem po angielsku (`Shipment Created`), a czasem kodem technicznym z myślnikiem i nazwą oddziału (`InscannedAtDH` plus oddział). W typowej kolejności:

| Ekart podaje | Co to oznacza dla Ciebie | Peek pokazuje |
| --- | --- | --- |
| `Pickup Requested`, `Shipment Created` | Sprzedawca zlecił wysyłkę w Ekart; przewoźnik nie ma jeszcze paczki. | „Zarejestrowana” |
| `OutForPickupEvent` | Kurier Ekart jedzie po odbiór przesyłki do sprzedawcy. | „Zarejestrowana” |
| `NotPickedEvent` | Podjęcie od sprzedawcy nie powiodło się; paczka nadal jest u nadawcy. | „Zarejestrowana” |
| `Pickup From Seller`, `PickupReceived` | Ekart odebrał paczkę od sprzedawcy. | „Przyjęta przez przewoźnika” |
| `ShipmentGrouped`, `ShipmentInTransit`, `dispatched_to_tc`, `dispatched`, `Dispatched to` + oddział | W drodze między sortowniami sieci Ekart. | „W transporcie” |
| `Expected` | Kolejny oddział otrzymał zapowiedź nadchodzącej paczki. | „W transporcie” |
| `Received`, `Received at` + oddział | Przyjęta w sortowni lub węźle Ekart. | „W transporcie” |
| `InscannedAtFacility` | Skan wejściowy w obiekcie Ekart. | „W transporcie” |
| `InscannedAtDH`, `PrimaryDHInscan` | W oddziale doręczeniowym (*Delivery Hub*, DH) obsługującym Twój rejon: kolejnym krokiem będzie wydanie kurierowi. | „W transporcie” |
| `Out For Delivery` | Dziś w doręczeniu u kuriera. | „W doręczeniu” |
| `Delivered` | Doręczona Tobie (albo zwrócona sprzedawcy po wpisie RTO, zobacz niżej). | „Dostarczona” |
| `undelivered_attempted`, `ShipmentUndeliveredAttempted`, `undelivered_update` | Nieudana próba doręczenia (`undelivered_update` potrafi pojawić się w tej samej sekundzie przy oddziale). | „Próba doręczenia” |
| `undelivered_unattempted` | Trasa kurierska zakończyła się bez podjęcia próby doręczenia. | „Próba doręczenia” |
| `Unsuccessful Unable to attempt delivery due to operational issues`, `Unsuccessful Undelivered. (Rescheduled to: …)` | Opisowe niedoręczenie po trasie; data po `Rescheduled to:` wskazuje dzień kolejnej próby (`Unsuccessful` dotyczące odbioru od nadawcy nie jest tak liczone). | „Próba doręczenia” |
| `PickupCancel` | Odbiór od sprzedawcy anulowano zanim paczka trafiła do sieci Ekart. | „Zgłoszono problem” |
| `ShipmentRtoConfirmed` | Potwierdzono zwrot do nadawcy (*Return to Origin*, RTO): paczka wraca do sprzedawcy. | „Zgłoszono problem” |
| `Marked RTO: ORDER_REJECT` | Po nieudanych próbach przesyłkę oznaczono do zwrotu sprzedawcy wraz z powodem po dwukropku. | „Zgłoszono problem” |
| `RtoExpected`, `RtoReceived`, `ReturnInscanned` | W drodze powrotnej do sprzedawcy. | „W transporcie” |

**Co oznacza RTO.** Peek odczytuje `ShipmentRtoConfirmed`, każdy wpis `Marked RTO` oraz każdy kod zaczynający się od `Rto` lub `Return` jako początek zwrotu do sprzedawcy: ten i wszystkie późniejsze skany należą do trasy powrotnej. Wówczas `InscannedAtDH` oznacza oddział sprzedawcy, a późniejsze `Delivered` potwierdza oddanie paczki sklepowi — Peek pokazuje wtedy „Zwrócona nadawcy” zamiast „Dostarczona” ([co oznaczają statusy śledzenia](guide:tracking-statuses)).

## Jak Ekart doręcza w Indiach

Ekart działa wyłącznie na terenie Indii, a Peek nie przekazuje paczek Ekart żadnemu innemu przewoźnikowi. Na stronie `ekartlogistics.in` sekcja B2C Express deklaruje zasięg ponad 15 000 kodów PIN w doręczeniach (FAQ wymienia 5 000 kodów PIN przy odbiorach i 15 000 przy doręczeniach, a podstrona *About Us* — 14 000 kodów PIN, ponad 80 sortowni i 3 500 oddziałów ostatniej mili), oferując doręczenia tego samego lub następnego dnia na krótkich dystansach oraz w 48–96 godzin na dłuższych trasach zależnie od kodu PIN.

Według oficjalnego FAQ Ekart wykonuje **pięć prób** w pierwszej mili (odbiór od sprzedawcy) oraz **trzy próby doręczenia** w ostatniej mili do odbiorcy, a termin dostawy można przełożyć na prośbę klienta, dopóki przesyłka nie została oznaczona jako `RTO` ([nieudane doręczenie](guide:missed-delivery)).

## Śledzenie Ekart stoi w miejscu, ma status RTO lub brak paczki

**Zatrzymana na `Shipment Created`, `Pickup Requested` lub `NotPickedEvent`.** Sprzedawca nie przekazał jeszcze paczki (albo podjęcie się nie udało); `PickupCancel` oznacza anulowanie odbioru. Zapytaj sklep, kiedy zamówienie wyjdzie.

**Brak nowego skanu przez kilka dni.** Gdy zarejestrowana, przyjęta lub jadąca paczka nie ma nowego skanu przez cztery dni, Peek wyświetla „Brak aktualizacji od czterech dni”. Skontaktuj się ze sklepem ([dlaczego śledzenie się nie aktualizuje](guide:tracking-not-updating)).

**Nieudana próba lub RTO.** Po `undelivered_attempted`, `undelivered_unattempted` lub `Unsuccessful` sprawdź datę po `Rescheduled to:` i potwierdź w sklepie adres oraz numer telefonu. Przy `ShipmentRtoConfirmed` lub `Marked RTO: ORDER_REJECT` przesyłka wraca do nadawcy — poproś sklep o zwrot pieniędzy lub ponowną wysyłkę ([zwrot do nadawcy](guide:returned-to-sender)).

**Oznaczona jako doręczona, zagubiona lub uszkodzona.** Sprawdź u domowników, sąsiadów oraz w recepcji lub ochronie budynku, a następnie zgłoś sprawę sklepowi lub platformie zakupowej, która zleciła wysyłkę w Ekart ([dostarczona, ale nieotrzymana](guide:delivered-not-received), [zagubiona paczka](guide:lost-parcel), [uszkodzona paczka](guide:damaged-parcel)).

## Obsługa klienta i kontakt z Ekart

W sekcji FAQ na `ekartlogistics.in` Ekart kieruje sprzedawców do zgłaszania spraw przez panel nadawcy (odpowiedź w ciągu 24 godzin), a w zakładce *Corporate Information* podaje adres siedziby *Instakart Services Private Limited* w Bengaluru (`Embassy Tech Village, Outer Ring Road, Devarabeesanahalli Village, Bengaluru KA 560103`), nie publikując infolinii ani adresu e-mail dla odbiorców paczek. W sprawie zamówienia, ponownej wysyłki lub zwrotu środków skontaktuj się ze sklepem albo platformą zakupową.

**Uwaga na fałszywe SMS-y.** Oficjalne domeny Ekart to `www.ekartlogistics.in` (`ekartlogistics.in`) oraz `ekartlogistics.com`. Zanim opłacisz cokolwiek z linku w wiadomości, przeczytaj [fałszywe SMS-y o paczkach](guide:delivery-scam-texts).

## Śledzenie paczki Ekart w Peek

Wklej identyfikator w [Peek](/): przy jednej z 17 powyższych grup liter z `C`, `P` lub `R` i dziesięcioma cyframi Peek od razu wskazuje Ekart („Wykryty przewoźnik”). Przy innych literach na początku (`ABCP4829105736`) Peek pokazuje „Sprawdzanie usług śledzenia…” i odpytuje wyszukiwarkę Ekart: gdy znajdzie skan, wyświetli „Ekart ma tę przesyłkę.” (gdy skanu jeszcze nie ma, stuknij „Zmień” i wybierz Ekart pod „Pasują do numeru”). Link z `ekartlogistics.com` zawierający `shipmenttrack/` również od razu przypisuje Ekart; natomiast link z `shiprocket.co` jest śledzony w Shiprocket. Ręczny wybór Ekart dla ciągu innego niż cztery litery i dziesięć cyfr pokaże, że przewoźnik „nie wydaje takich numerów”.

Przypisana do Ekart przesyłka przed pierwszym skanem pokazuje „Oczekiwanie na przewoźnika Ekart” (a bez przewoźnika — „Brak historii śledzenia”). Peek pobiera dane z wyszukiwarki Ekart bez konta i telefonu: każdy wiersz z kodem i miastem, dokładną godziną przeliczoną na strefę Twojego urządzenia („Godziny w Twojej strefie czasowej: …”) bez powtórzeń oraz przewidywaną datę doręczenia, dopóki paczka zmierza do Ciebie (znika po doręczeniu, anulowaniu lub RTO).

Gdy odpowiada wyszukiwarka Ekart, Peek sprawdza status nawet co 10 minut przy otwartej paczce lub w dniu dostawy z włączonymi powiadomieniami oraz co 2 minuty od 6:00 (czasu indyjskiego) w dniu skanu `Out For Delivery` (przez serwisy śledzenia do co 15 minut w tym dniu przy otwartej karcie lub włączonych powiadomieniach); poza tym i po 12 godzinach na etapie „Zarejestrowana” / „Przyjęta przez przewoźnika” lub dwóch dniach bez zmian — co godzinę („Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”). Peek nie skontaktuje się za Ciebie z Ekart ani ze sklepem.

## Najczęstsze pytania o śledzenie Ekart

### Jak śledzić paczkę Ekart?

Wklej 14-znakowy identyfikator z wiadomości od sprzedawcy w polu u góry strony Peek albo wpisz go w polu *Ecommerce Parcel Tracking* na `ekartlogistics.in/track-order`.

### Jak wygląda identyfikator śledzenia Ekart?

Trzy litery, po nich `C`, `P` lub `R` oraz dziesięć cyfr, np. `FMPP4829105736` (przykład zmyślony).

### Co oznacza skrót `RTO` w śledzeniu Ekart?

*Return to Origin*, czyli zwrot paczki do sprzedawcy. `ShipmentRtoConfirmed` lub `Marked RTO: ORDER_REJECT` rozpoczyna zwrot, a późniejsze `Delivered` oznacza wydanie przesyłki z powrotem sprzedawcy („Zwrócona nadawcy” w Peek).

### Co oznacza status `InscannedAtDH` w Ekart?

Paczka została zeskanowana w oddziale doręczeniowym (*Delivery Hub*): w drodze do Ciebie w bazie obsługującej Twój adres, a po statusie RTO — w oddziale sprzedawcy.

### Ile prób doręczenia podejmuje Ekart?

Oficjalne FAQ Ekart podaje trzy próby doręczenia do odbiorcy w ostatniej mili (oraz pięć prób odbioru od sprzedawcy). W sprawach zamówienia kontaktuj się najpierw ze sklepem.

:::sources
- [Ekart Logistics: Strona główna](https://www.ekartlogistics.in/) – indyjski operator logistyczny e-commerce i zasięg ponad 15 000 kodów PIN
- [Ekart Logistics: Track Order](https://www.ekartlogistics.in/track-order) – śledzenie przesyłek e-commerce oraz osobny link B2B/LR
- [Ekart Logistics: FAQ](https://www.ekartlogistics.in/faq) – trzy próby doręczenia, pięć prób odbioru, zmiana terminu przed RTO, 5 000 / 15 000 kodów PIN i zgłoszenia dla sprzedawców
- [Ekart Logistics: About Us](https://www.ekartlogistics.in/about-us) – działalność od 2009 r., 3 500 oddziałów doręczeniowych, ponad 80 sortowni i 14 000 kodów PIN
- [Ekart Logistics: B2C Express](https://www.ekartlogistics.in/b2c-express) – doręczenia tego samego / następnego dnia oraz w 48–96 godzin w Indiach
- [Ekart Logistics: Corporate Information](https://www.ekartlogistics.in/corporate-info) – Instakart Services Private Limited z siedzibą w Bengaluru
- [Ekart Logistics: Terms & Conditions](https://www.ekartlogistics.in/terms-and-conditions) – prowadzenie domeny ekartlogistics.com przez Instakart Services Private Limited
- [Universal Parcel Scraper: Ekart](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ekart) – 17 grup liter, linki shipmenttrack, 31 kodów i statusów, obsługa RTO, czasy i przewidywana data dostawy
- [Universal Parcel Scraper: Shiprocket](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/shiprocket) – linki śledzenia w domenie shiprocket.co
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – wyjątki PB (Parcelforce) i HRBC (Cainiao / AliExpress) oraz brak przekazań do innych firm
:::
