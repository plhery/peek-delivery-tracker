---
title: Śledzenie Parcelforce Worldwide: statusy, cło i kontakt
description: Śledź przesyłkę Parcelforce Worldwide: gdzie znaleźć numer, co oznaczają statusy, opcje doręczenia, opłaty celne i kontakt z obsługą Royal Mail.
slug: sledzenie-parcelforce-worldwide
published: 2026-11-22
updated: 2026-11-22
---

Parcelforce Worldwide należy do grupy [Royal Mail](carrier:royal-mail) Group, dlatego przesyłkę Parcelforce sprawdzisz na stronie Track your item w serwisie Royal Mail albo wklejając numer w polu powyżej. Numer pochodzi ze sklepu lub od nadawcy — najczęściej z e-maila potwierdzającego zamówienie.

## Numer śledzenia Parcelforce: gdzie go znaleźć

Sprawdź e-mail potwierdzający ze sklepu lub zapytaj sprzedawcę. Zgubiłeś go? Zobacz [jak znaleźć numer śledzenia](guide:find-tracking-number). Oto trzy możliwe układy:

| Numer (zmyślony) | Co to jest |
| --- | --- |
| `EA482917367GB` | Dwie litery (`E` i litera albo `CP` lub `GI`), dziewięć cyfr i `GB`, z cyfrą kontrolną przed `GB`. |
| `PBTR4829173654` | 14-znakowy numer paczki: `PB`, numer przesyłki zbiorczej i pozycja paczki w jej ramach. |
| `TR4829173` | Sam numer przesyłki zbiorczej (*consignment number*): tracker Royal Mail wymaga zamiast niego własnego numeru każdej paczki. |

Format 13-znakowy należy także do Royal Mail (`RR482917367GB` to Royal Mail, nie Parcelforce), a część przesyłek express48 ma numer Royal Mail. Ponieważ oba śledzi się na tej samej stronie, w praktyce niewiele to zmienia.

## Statusy śledzenia Parcelforce — wyjaśnienie

Tracker Royal Mail wyświetla poniższe wpisy po angielsku. W typowej kolejności:

| Śledzenie pisze | Co to znaczy dla ciebie |
| --- | --- |
| `Sender has despatched item` | Nadawca zgłosił paczkę. Parcelforce jeszcze jej nie ma. |
| `Item Collected` | Odebrana od nadawcy. |
| `Accepted at Parcelshop` | Nadawca nadał paczkę na poczcie lub w punkcie paczkowym. |
| `Item received` | Paczka weszła do sieci. |
| `Customs charges raised` | Przesyłka zza granicy spoza Wielkiej Brytanii wymaga opłacenia VAT, cła lub opłaty przed doręczeniem. |
| `Despatched to delivery office` | W drodze do lokalnego oddziału lub urzędu doręczeń. |
| `Received at Delivery Depot` | W lokalnym oddziale. Nie jest jeszcze gotowa do odbioru. |
| `Out for delivery` | Dziś u kierowcy. |
| `Delivery attempted` | Kierowca nie zdołał doręczyć. |
| `Delivered by` | Doręczona. Wpis może podawać placówkę, która ją doręczyła. |
| `Delivered to Neighbour/Reception by` | Zostawiona u sąsiada lub na recepcji, z podanym nazwiskiem. |
| `Returned to sender` | W drodze powrotnej do nadawcy. |

Dla przesyłek wysyłanych za granicę historia biegnie dalej wpisami takimi jak `Item Leaving the UK` oraz `Due to be delivered today` partnera doręczającego.

## Opcje doręczenia Parcelforce i nieudane doręczenia

**Przed doręczeniem.** W aplikacji Royal Mail lub przez Royal Mail Customer Services możesz wybrać inny dzień dostawy również dla paczki Parcelforce, poprosić o doręczenie do sąsiada albo o głośniejsze pukanie lub dłuższy czas na dojście do drzwi. Bezpieczne miejsce (*safe place*) również ustawiasz w Royal Mail dla przesyłek Parcelforce.

**Nieudane doręczenie.** Śledzenie pokaże `Delivery attempted` albo `Delivered to Neighbour/Reception by` z imieniem osoby, która ją odebrała. Jeśli nadal nie wiesz, gdzie jest paczka, zadzwoń do Royal Mail Customer Services (niżej).

## Śledzenie Parcelforce nie aktualizuje się, paczka opóźniona lub zaginiona

**Brak skanów.** Nadawca mógł wygenerować numer przed wydaniem paczki: `Sender has despatched item` oznacza, że Parcelforce jeszcze jej nie ma. Sprawdź numer i spróbuj później. Więcej w [dlaczego śledzenie przestaje się aktualizować](guide:tracking-not-updating).

**Opóźniona, zaginiona lub uszkodzona.** Przy zakupie online skontaktuj się najpierw ze sprzedawcą: Citizens Advice wskazuje, że zazwyczaj daje to lepszy rezultat i możesz zażądać zwrotu pieniędzy lub ponownej wysyłki. Zasady ubiegania się o odszkodowanie sprawdzisz na stronie Parcelforce.

**Opłaty celne.** Parcelforce przeprowadza przesyłki spoza Wielkiej Brytanii przez odprawę celną i wysyła ci rachunek z dokładną kwotą do zapłaty, jeśli należny jest VAT, cło lub inne opłaty ([więcej o cle](guide:customs)). Zazwyczaj przetrzymuje paczkę przez około 3 tygodnie; nieopłacona wraca do nadawcy. Pytania dotyczące towaru również kieruj do Parcelforce.

> Nie masz pewności, czy e-mail lub SMS z prośbą o zapłatę za paczkę jest prawdziwy? Nie klikaj linków i nie podawaj danych bankowych. Prześlij podejrzane e-maile bezpłatnie na report@phishing.gov.uk, a SMS-y na 7726 (w Wielkiej Brytanii).

## Telefon i obsługa klienta Parcelforce

Citizens Advice kieruje klientów Parcelforce do Royal Mail w sprawach preferencji doręczenia i bezpiecznych miejsc:

- **Royal Mail Customer Services:** 03457 740 740, od poniedziałku do piątku 8:00–18:30, sobota 8:00–15:00 i niedziela 9:00–14:00 czasu brytyjskiego. **Relay UK:** 18001, potem 0345 266 0075.
- **Aplikacja Royal Mail:** data dostawy, sąsiad, bezpieczne miejsce i potrzeby doręczenia również dla paczek Parcelforce.

Reklamację lub skargę złóż bezpośrednio przez stronę Parcelforce.

## Śledzenie paczek Parcelforce w Peek

[Peek](/) rozpoznaje Parcelforce od razu przy numerach typu `EA482917367GB` z prawidłową cyfrą kontrolną oraz przy 14-znakowych numerach `PB`. Sam numer przesyłki zbiorczej nie jest śledzony. Działa link Parcelforce z parametrem `trackNumber=` oraz link Royal Mail zakończony `#/tracking-results/` i numerem Parcelforce.

Peek odczytuje Parcelforce ze strony Royal Mail, otwierając ją w prawdziwej przeglądarce na serwerze, więc sprawdzenie trwa dłużej i może się nie udać, gdy strona blokuje automatyczne wizyty. Peek próbuje wtedy uniwersalnych usług śledzenia, które jednak mają niewiele danych dla Parcelforce.

Peek pokazuje status, każdy skan z lokalizacją (bez kodów pocztowych), przewidywany dzień doręczenia, godzinę doręczenia oraz kraj docelowy. Statusy zachowują angielskie brzmienie Royal Mail, poza kilkoma, które Peek tłumaczy, np. `Out for delivery` („W doręczeniu”), `Delivered` („Dostarczona”) i `Returned to sender` („Zwrócona nadawcy”). Skan zagraniczny bez strefy czasowej nie otrzymuje daty; pozostałe przeliczane są na twoją strefę czasową („Godziny w twojej strefie czasowej:”). Przy eksporcie pod numerem typu `EA482917367GB` Peek może też zapytać pocztę narodową kraju docelowego podanego przez Parcelforce (a gdy go nie podaje, pocztę kraju, w którym cię umiejscawia, poza Wielką Brytanią) i przejść na jej śledzenie, gdy tylko pokaże skan nie starszy niż ostatni skan Parcelforce.

Dopóki masz przesyłkę otwartą w Peek — lub w dniu doręczenia z włączonymi powiadomieniami — Peek sprawdza nawet co 10 minut, a od 6:00 czasu brytyjskiego (lub na pół godziny przed otwarciem okna czasowego, gdy Parcelforce je podaje) w dniu skanu w doręczenie nawet co 5 minut, dopóki tracker Parcelforce odpowiada; poza tym, oraz po dwóch dniach bez wiadomości (12 godzin przy statusie „Zarejestrowana” lub „Przyjęta przez przewoźnika”), co godzinę; po siedmiu dniach bez wiadomości przy statusie „Zarejestrowana” co 6 godzin. Po czterech dniach bez nowego skanu dla przesyłki w statusie „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie” Peek pokazuje datę ostatniego skanu („Brak aktualizacji od …”) i „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika Parcelforce Worldwide.” z linkiem „Poproś przewoźnika Parcelforce Worldwide o pomoc” do strony kontaktowej Parcelforce. Umieszcza skany na mapie i wysyła wybrane alerty („Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”).

## Pytania o śledzenie Parcelforce

### Czy Parcelforce należy do Royal Mail?

Tak. GOV.UK zalicza Parcelforce do Royal Mail Group, a jego paczki śledzi się na stronie Track your item serwisu Royal Mail.

### Dlaczego mój numer Parcelforce nie jest rozpoznawany?

Paczka mogła jeszcze nie dotrzeć do Parcelforce albo w numerze jest literówka. Sam numer przesyłki zbiorczej nie jest śledzony: poproś nadawcę o 14-znakowy numer `PB` dla każdej paczki i spróbuj później.

### Jak długo Parcelforce przechowuje paczkę z opłatami celnymi?

Około 3 tygodni. Jeśli rachunek nie zostanie opłacony w tym czasie, paczka wraca do nadawcy.

### Czy mogę wybrać inny dzień dostawy Parcelforce?

Tak — w aplikacji Royal Mail albo przez Royal Mail Customer Services. Możesz tam też poprosić o zostawienie paczki u sąsiada.

:::sources
- [GOV.UK: Check when you can account for import VAT on your VAT Return](https://www.gov.uk/guidance/check-when-you-can-account-for-import-vat-on-your-vat-return) – Parcelforce częścią Royal Mail Group
- [Citizens Advice: Telling delivery companies about your delivery or collection needs](https://www.citizensadvice.org.uk/consumer/post-and-parcels/telling-delivery-companies-about-your-delivery-or-collection-needs/) – aplikacja Royal Mail i Customer Services dla Parcelforce, inny dzień, sąsiad, bezpieczne miejsce, pukanie, telefon i godziny
- [Citizens Advice: Claiming compensation from Royal Mail](https://www.citizensadvice.org.uk/consumer/post-and-parcels/claiming-compensation-from-royal-mail/) – numer Relay UK
- [Citizens Advice: Damaged, lost or delayed post or parcel](https://www.citizensadvice.org.uk/consumer/post-and-parcels/damaged-lost-delayed-post-or-parcel/) – najpierw sprzedawca, reklamacje na stronie przewoźnika
- [GOV.UK: Tax and customs for goods sent from abroad: Overview](https://www.gov.uk/goods-sent-from-abroad) – Parcelforce obsługuje odprawę celną, pytania o towar
- [GOV.UK: Tax and customs for goods sent from abroad: Tax and duty](https://www.gov.uk/goods-sent-from-abroad/tax-and-duty) – rachunek, około 3 tygodni, zwrot do nadawcy
- [GOV.UK: Avoid and report internet scams and phishing](https://www.gov.uk/report-suspicious-emails-websites-phishing) – 7726, report@phishing.gov.uk
- [UPU: S10 standard, Identification of postal items](https://www.upu.int/UPU/media/upu/files/postalSolutions/programmesAndServices/standards/S10-12.pdf) – cyfra kontrolna przed kodem kraju
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – co Peek odczytuje, statusy i formaty numerów
:::
