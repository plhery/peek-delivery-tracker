---
title: Śledzenie iMile: numery, statusy i dostawa w Polsce
description: Śledź przesyłkę iMile w Polsce: jak wygląda numer, co oznaczają angielskie statusy, kto doręcza paczki i jak skontaktować się z iMile, gdy paczka utknie.
slug: imile-sledzenie
published: 2026-11-16
updated: 2026-11-17
---

iMile to firma kurierska obsługująca handel internetowy, założona w Dubaju w 2017 roku. Przewozi paczki między krajami, między innymi z Chin, zajmuje się odprawą celną, a w Polsce ma własną spółkę z siedzibą w Bielanach Wrocławskich. Wpisz swój numer na [stronie śledzenia iMile](https://www.imile.com/en/track) albo wklej go w polu powyżej. Numer dostaniesz od sklepu w szczegółach wysyłki zamówienia.

## Numer śledzenia iMile: jak wygląda

iMile nie podaje na stronie przykładowego numeru. Takie formaty noszą przesyłki iMile (cyfry są przykładowe):

| Numer | Czyj to numer |
| --- | --- |
| `IM06010000000000` | `IM`, `0601` i dziesięć cyfr: iMile. |
| `IM99999999999980` | `IM` i 14 cyfr: również iMile. |
| `6010125000000` | 13 cyfr zaczynających się od `6`, po których sześć kolejnych ma układ daty (miesiąc, dzień, rok): własny numer listu przewozowego iMile. |
| `49000000000000` | 14 cyfr zaczynających się od `49`: format spotykany na przesyłkach iMile we Włoszech, współdzielony przy 14 cyfrach m.in. z DPD, Chronopost czy BRT. Sam układ znaków nie wystarcza, ale Peek odpytuje iMile wśród pierwszych przewoźników. |
| `3000000000000` | 13 cyfr zaczynających się od `3`: kolejna seria obsługiwana przez iMile, używana też przez innych przewoźników z 13-cyfrowymi numerami. Sam układ znaków nie wystarcza: Peek odpytuje iMile razem z nimi. |
| `2000000000000` | Inne 13-cyfrowe numery: to nie jest format iMile. Takich numerów używają m.in. Delhivery, Yunda i Korea Post. |
| `LP99999999999980` | To nie iMile, lecz [Cainiao](carrier:cainiao) – numer logistyczny z AliExpress. |

Na stronie śledzenia iMile wpisz numer pod „TYPE YOUR ORDER NUMBER”; historia pojawi się w sekcji „ORDER STATUS”. Jeśli iMile nie zna numeru, to pole zostaje puste, bez komunikatu o błędzie. Gdy nie możesz znaleźć numeru nadania, zajrzyj do poradnika [gdzie znaleźć numer śledzenia](guide:find-tracking-number), a potem zapytaj sklep.

## Statusy śledzenia iMile: co oznacza każdy z nich

Skany iMile są po angielsku – dokładnie tak, jak odczytuje je Peek. Nazwy w nawiasach `【】` to centra iMile lub kody lotnisk, więc przy Twojej paczce zobaczysz inne miejsca. Najczęstsze wpisy w kolejności drogi przesyłki:

| iMile pisze | Co to dla Ciebie znaczy |
| --- | --- |
| `Order submitted, pending to receive.` | Sprzedawca utworzył zlecenie wysyłki. iMile nie ma jeszcze paczki. |
| `Your order has been picked up.` | iMile odebrało przesyłkę od nadawcy. |
| `【IST】The flight has taken off.` | Samolot wystartował; kod oznacza lotnisko wylotu. |
| `【RUH】Flight Arrived - Waiting for customs clearance.` | Samolot wylądował, a paczka czeka na odprawę celną. |
| `Clearance Processing` | Urząd celny sprawdza przesyłkę. |
| `【Milan center】Your order has been received.` | Paczka dotarła do pierwszego centrum iMile w kraju docelowym. |
| `Your order has been shipped from 【Milan center】.` | Wyruszyła do kolejnego centrum lub oddziału doręczającego. |
| `Parcel is currently being processed.` | Jest w oddziale obsługującym Twoją okolicę. |
| `Our delivery associate is out for delivery.` | Wydana dziś kurierowi do doręczenia. |
| `User not at home` | Próba doręczenia się nie powiodła: to powód wpisany przez kuriera. |
| `Delivery attempt finished,return to Local logistics company warehouse.` | Po nieudanej próbie paczka wraca do lokalnego magazynu. |
| `The delivery method has been confirmed with the recipient as Self-Pickup.` | Po nieudanej próbie ustalono odbiór osobisty. Paczka jeszcze nie zmieniła miejsca. |
| `The parcel has been delivered to …. Please collect your parcel promptly at … using your pickup code.` | Czeka w punkcie odbioru wskazanym w treści wpisu, pod podanym adresem – odbierz ją z kodem odbioru. „Delivered” oznacza tu dostarczenie do punktu, a nie do Ciebie; Peek pokazuje „Do odbioru”. |
| `【Contact Error】need to return.` | Kurier nie zdołał się z Tobą skontaktować i rusza zwrot. |
| `Your order has been delivered successfully.` | Doręczona. |
| `Order delivery failed, returning to sender.` | Przesyłka wraca do nadawcy. |
| `【Milan Transit Center】Shipment returned to origin.` | Zwrócona nadawcy. |

Przy przesyłkach europejskich pierwszy wpis ma postać `Order Submited.` – literówka pochodzi od iMile, podobnie jak w `Your order has assigned delivery drvier.`, które informuje o przypisaniu kuriera przed wyjazdem na trasę. `Shipment is scheduled to` z datą w nawiasach kwadratowych oznacza wyznaczony dzień doręczenia. Z kolei `Scheduled Delivery`, pojawiające się między wyjazdem na rejon a końcem próby lub kolejnym kursem, planuje ponowne doręczenie bez podania daty: paczka się nie przemieszcza, więc Peek utrzymuje dotychczasowy status.

Inne powody nieudanej próby wpisywane przez kurierów obok `User not at home`: `wrong address` (albo `Wrong Address(Insufficient address details)`, gdy w adresie brakuje szczegółów), `switch off`, `Customer Wants to Change Location`, `Route not completed`, `Traffic, Strike, Bad weather`, komunikat zaczynający się od `failed to schedule due to` z powodem w nawiasach kwadratowych, np. `noanswer` (kurier nie zdołał umówić z Tobą doręczenia), a także `The driver failed to deliver due to shop(building) closure, we will try to deliver again.` Podczas kursu może pojawić się też wpis `Lost order` – sam w sobie nie przesądza jeszcze o zaginięciu paczki, więc obserwuj kolejne skany. Gdy przesyłka utknie na cle, pomoże poradnik [paczka zatrzymana w urzędzie celnym](guide:customs).

## Dostawa iMile w Polsce

**Kto doręcza.** iMile ma w Polsce spółkę Imile logistics and transportation sp. z o.o. z siedzibą w Bielanach Wrocławskich (`ul. Magazynowa 8, DC5, 55-040 Bielany Wrocławskie`), ale na swoich stronach nie opisuje przebiegu doręczeń w kraju. Polska polityka prywatności iMile podaje, że dane z listu przewozowego i adresy doręczenia trafiają do „podwykonawców realizujących dostawy na ostatnim odcinku trasy (last mile), zewnętrznych dostawców oraz zawodowych kierowców”, nie wymieniając żadnej firmy z nazwy. Strony usług iMile również nie obejmują Polski: krajowa dostawa ekspresowa dotyczy ZEA, Arabii Saudyjskiej, Meksyku, Brazylii oraz Malezji, a międzynarodowy dropshipping z Chin z własnym zespołem ostatniej mili nie wymienia żadnego kraju europejskiego.

**Próby doręczenia, punkty odbioru i opcje dostawy.** iMile nie publikuje dla Polski regulaminu określającego liczbę prób doręczenia, zasady awizowania ani czas oczekiwania paczki, nie podaje też listy punktów odbioru (choć na polskiej stronie głównej wymienia punkty PUDO wśród swoich usług). To, co się wydarzyło, widać w samych skanach: powód wpisany przez kuriera, powrót do oddziału, kolejna próba, `【Contact Error】need to return.` albo – po uzgodnieniu odbioru własnego – wpis z nazwą i adresem punktu odbioru. Polska polityka prywatności wspomina o przesyłaniu aktualizacji śledzenia i udostępnianiu opcji takich jak „wybór konkretnego przedziału czasowego”, nie wyjaśnia jednak, jak z nich skorzystać.

**Zmiana adresu i dowód doręczenia.** Ogólne FAQ iMile, pisane głównie pod kątem Emiratów, radzi skontaktować się z obsługą klienta zanim przesyłka trafi do końcowego etapu doręczenia albo skorzystać z *iMile Online Bot* (zobacz też [zmiana adresu dostawy](guide:change-delivery-address) i [nieudane doręczenie](guide:missed-delivery)). Według polskiej polityki prywatności jako dowód doręczenia iMile zbiera „podpisy, kody PIN lub zdjęcia miejsca doręczenia” i przekazuje je nadawcy lub platformie sprzedażowej. Na stronie śledzenia dostępny jest podgląd dowodu doręczenia wymagający potwierdzenia, że jesteś osobą uprawnioną. W niektórych krajach wpis o wydaniu do doręczenia na stronie iMile zawiera numer telefonu kuriera lub infolinii iMile.

## Paczka iMile nie aktualizuje się, zaginęła lub wraca

**Jeszcze bez historii.** Dopóki widnieje tylko `Order submitted, pending to receive.`, sprzedawca wygenerował zlecenie, ale mógł jeszcze nie przekazać paczki. Zapytaj sklep, kiedy ją nadał. Jeśli dla numeru nieznanego jeszcze w iMile wybierzesz iMile przyciskiem „Zmień”, Peek pokaże na peektracker.com „Oczekiwanie na przewoźnika iMile”; gdy po dwóch dniach iMile nadal nie zna numeru i żadne inne źródło dla niego nie odpowiada, Peek wyświetli „Po dwóch dniach przewoźnik nadal nie zna tego numeru. Sprawdź go, wybierz innego przewoźnika lub usuń przesyłkę.” i będzie sprawdzał paczkę raz dziennie, a po tygodniu od dodania – raz w tygodniu.

**Brak zmian przez kilka dni.** Przesyłki z Chin często milkną między wylotem, odprawą celną a pierwszym centrum w Polsce. Wyjaśniamy to w poradnikach [śledzenie paczki z Chin](guide:tracking-from-china) oraz [śledzenie się nie aktualizuje](guide:tracking-not-updating).

**Cło i opłaty.** W całej Unii Europejskiej przy zakupach online wysyłanych spoza UE (np. z Chin) od 1 lipca 2026 roku zniesiono zwolnienie z cła do 150 € i wprowadzono tymczasowe cło 3 € za pozycję towarową dla przesyłek o wartości do 150 €, a od 1 listopada 2026 roku dochodzi unijna opłata manipulacyjna 2 € za pozycję towarową niezależnie od wartości zamówienia, należna z chwilą przyjęcia zgłoszenia celnego. iMile nie informuje na stronie, w jaki sposób rozlicza cło, VAT i opłatę – to sklep wie, czy były już wliczone w cenę zakupu. Szczegóły znajdziesz w poradniku [paczka zatrzymana w urzędzie celnym](guide:customs).

**Link do śledzenia nie działa.** Według FAQ iMile odśwież stronę albo otwórz ją w innej przeglądarce, wyczyść pliki cookie i pamięć podręczną, a jeśli problem nie ustąpi, napisz do obsługi klienta z numerem zamówienia po nowy link.

**Status doręczonej, ale paczki nie ma.** Sprawdź zdjęcie z dowodu doręczenia na stronie iMile, rozejrzyj się przy drzwiach i zapytaj sąsiadów, a następnie skontaktuj się z iMile oraz ze sklepem. Zobacz [doręczona, ale nie dotarła](guide:delivered-not-received).

**Zaginiona, uszkodzona lub zwrócona.** Polska polityka prywatności podaje, że iMile rozpatruje roszczenia takie jak „zapytania dotyczące zagubionych lub uszkodzonych przesyłek”, ale nie publikuje terminów ani stawek odszkodowań. Umowę z iMile ma sklep, więc o zwrot pieniędzy lub ponowną wysyłkę zwróć się do sprzedawcy. Zobacz [paczka zaginęła](guide:lost-parcel), a po statusie `Order delivery failed, returning to sender.` – [zwrot do nadawcy](guide:returned-to-sender).

## Kontakt z iMile w Polsce i obsługa klienta

Według stanu na 16 listopada 2026 roku iMile udostępnia dla Polski następujące kanały:

- **E-mail obsługi klienta**: `customerservice@imile.me` (podany przy polskim biurze na stronie lokalizacji biur).
- **Kontakt w sprawach danych osobowych**: `dpo@imile.me` (w polityce prywatności zapisany jako `dpo[AT]imile.me`).
- **Telefon**: iMile nie publikuje numeru telefonu dla Polski.
- **Formularz online**: dostępny na stronie wsparcia iMile (`PL-pl/CustomerService/CustomerSupport`), wymaga jednak wypełnienia pól „Nazwa Firmy” oraz „Liczba Zamówień Dziennych”.
- **Czat iMile**: przycisk w prawym dolnym rogu imile.com otwiera wirtualną asystentkę Alison obsługującą język angielski, niemiecki, hiszpański, włoski, portugalski, węgierski i arabski (bez języka polskiego).

W sprawie samego zamówienia szybciej pomoże sklep: to on zlecił przewóz w iMile i może zgłosić reklamację po swojej stronie.

## Śledzenie przesyłki iMile w Peek

[Peek](/) od razu rozpoznaje numery `IM0601…` jako iMile. Przy pozostałych numerach `IM` z 14 cyframi, przy 14-cyfrowych numerach zaczynających się od `49` oraz przy 13-cyfrowych numerach zaczynających się od `6` lub `3`, z których korzystają też inni przewoźnicy, sam układ znaków nie wystarcza – wklej numer mimo to, bo Peek obsługuje ponad 3500 przewoźników. Po dodaniu przesyłki Peek wyświetla „Sprawdzanie usług śledzenia…”, odpytuje pasujących przewoźników (stawiając iMile wśród pierwszych dla numerów z `49` na początku oraz dla numerów z `6` i datą) i pokazuje „iMile ma tę przesyłkę.”, gdy iMile ją potwierdzi. Jeśli żaden przewoźnik jeszcze nie odpowiada lub wybierze się inna firma, wskaż iMile przyciskiem „Zmień”. Działają też linki ze strony śledzenia imile.com (`/track?waybillNo=`).

Peek odpytuje iMile po angielsku. Pokazuje aktualny status, historię z centrum lub miastem każdego skanu na mapie oraz kraj przeznaczenia, odczytując godziny według zegara tego kraju i wyświetlając je w strefie czasowej Twojego urządzenia (na peektracker.com i w aplikacji na iPhonie przypomina o tym dopisek pod historią: „Godziny w Twojej strefie czasowej: …”). Jeżeli paczkę wywozi z Chin dla iMile [Cainiao](carrier:cainiao), Peek dokłada na początku wcześniejsze skany z Cainiao: odbiór, sortowanie, eksport, lot i import.

Jako przewidywaną datę doręczenia Peek pokazuje dzień wskazany w najnowszym wpisie `Shipment is scheduled to`, dopóki nie nastąpi nieudana próba, problem, przyjęcie w punkcie odbioru, doręczenie, zwrot lub skan z datą późniejszą niż ten dzień. Pomija zaplanowany lot, dowód doręczenia, dane osoby kwitującej odbiór, kontakty i szczegóły płatności, a z treści skanów usuwa numer telefonu kuriera i infolinii iMile. Nazwy etapów takie jak „W doręczeniu” czy „Próba doręczenia” pochodzą z Peek; same wiersze historii pozostają w oryginalnym zapisie iMile. Dopóki odpowiada bezpośrednio śledzenie iMile, Peek sprawdza przesyłkę nawet co 10 minut, a w dniu wydania do doręczenia nawet co 2 minuty (od 6:00 czasu lokalnego); przez serwisy śledzące co godzinę, a w dniu doręczenia nawet co 15 minut. Po dwóch dniach bez nowych zdarzeń (lub po 12 godzinach, dopóki paczka ma status „Zarejestrowana” albo „Przyjęta przez przewoźnika”) przechodzi na sprawdzanie raz na godzinę. Powiadomienia ustawisz na „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek nie skontaktuje się za Ciebie z iMile ani ze sklepem.

## Pytania o śledzenie iMile

### Gdzie jest moja paczka z iMile?

Mówi o tym ostatni skan, a nazwa w nawiasach `【】` wskazuje centrum lub lotnisko. Wpis `Our delivery associate is out for delivery.` oznacza, że przesyłka wyjechała dziś do doręczenia; jeśli po nim pojawi się powód wpisany przez kuriera, próba nie doszła do skutku.

### Kto doręcza paczki iMile w Polsce?

iMile nie podaje tego wprost na swoich stronach. Polska polityka prywatności spółki z Bielan Wrocławskich mówi o podwykonawcach ostatniej mili, zewnętrznych dostawcach i zawodowych kierowcach, nie wymieniając nazw firm. W razie potrzeby zapytaj sprzedawcę albo napisz na `customerservice@imile.me`.

### Co oznacza status „Order Submited” w iMile?

Sprzedawca utworzył zlecenie nadania, ale iMile nie odebrało jeszcze przesyłki. Literówka w angielskim zapisie pochodzi od iMile; po odbiorze paczki pojawia się `Your order has been picked up.`

### Czy iMile to prawdziwa firma kurierska?

Tak. To firma kurierska założona w Dubaju w 2017 roku, mająca własną stronę śledzenia oraz polską spółkę wpisaną do KRS pod numerem 0001008750, z siedzibą w Bielanach Wrocławskich. Gdy przesyłka utknie, skontaktuj się ze sklepem i obsługą klienta iMile.

:::sources
- [iMile: Strona główna (Polska)](https://www.imile.com/PL-pl) – założona w 2017 r. w Dubaju, dostawy transgraniczne, odprawa celna i punkty PUDO
- [iMile: Informacja prawna (Polska)](https://www.imile.com/PL-pl/LegalNotice) – Imile logistics and transportation sp. z o.o., Bielany Wrocławskie, KRS 0001008750, NIP i REGON
- [iMile: Polityka prywatności (Polska)](https://www.imile.com/PL-pl/PrivacyPolicy) – podwykonawcy ostatniej mili, dowód doręczenia (podpisy, kody PIN, zdjęcia), przedziały czasowe, roszczenia za zagubione lub uszkodzone przesyłki, kontakt dpo@imile.me
- [iMile: Lokalizacje biur (Polska)](https://www.imile.com/PL-pl/CustomerService/OfficeLocations) – adres w Bielanach Wrocławskich, e-mail customerservice@imile.me, brak numeru telefonu
- [iMile: Wsparcie klienta (Polska)](https://www.imile.com/PL-pl/CustomerService/CustomerSupport) – formularz kontaktowy i wymagane pola (nazwa firmy, liczba zamówień dziennych)
- [iMile: Krajowa dostawa ekspresowa (Polska)](https://www.imile.com/PL-pl/logistics-solutions/domestic-express-delivery) – kraje objęte dostawą krajową
- [iMile: Międzynarodowa dostawa dropshippingowa (Polska)](https://www.imile.com/PL-pl/logistics-solutions/international-dropship-delivery) – własny zespół ostatniej mili i trasy z Chin
- [iMile: Track Your Shipment (po angielsku)](https://www.imile.com/en/track) – strona śledzenia, pola TYPE YOUR ORDER NUMBER i ORDER STATUS, puste pole przy nieznanym numerze, podgląd dowodu doręczenia
- [iMile: FAQs (po angielsku)](https://www.imile.com/en/CustomerService/FAQ) – zmiana adresu dostawy, Online Bot, niedziałający link do śledzenia
- [iMile: iMile Chat](https://help.imile.com/) – wirtualna asystentka Alison i obsługiwane języki
- [Komisja Europejska: EU Customs Reform (po angielsku)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – zniesienie zwolnienia do 150 € i tymczasowe cło 3 € za pozycję od 1 lipca 2026 r., opłata manipulacyjna UE 2 € od 1 listopada 2026 r.
- [Komisja Europejska: Customs Guidance on EUR 3 customs duty, PDF (po angielsku)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – opłata 2 € liczona za pozycję towarową tak jak cło 3 €, niezależnie od wartości, należna przy przyjęciu zgłoszenia celnego
- [Universal Parcel Scraper: iMile (po angielsku)](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/imile) – formaty numerów (w tym 49 + 12 cyfr i 3 + 12 cyfr), treści statusów, skany Cainiao i działanie w Peek
:::
