---
title: Śledzenie SPX Express Singapur: paczki Shopee i statusy
description: Śledź paczkę SPX Express (Shopee Xpress) w Singapurze: formaty SPXSG i SG, znaczenie statusów, podatek GST w imporcie i pomoc.
slug: sledzenie-spx-express-singapore
published: 2026-11-22
updated: 2026-11-22
---

SPX Express Singapore, zwane także Shopee Xpress lub Shopee Express, doręcza przesyłki w Singapurze. Obsługuje zamówienia z Shopee, w tym paczki od sprzedawców z Chin przechodzące przez sortownię Shopee w Hongkongu. Własna wyszukiwarka SPX działa pod adresem spx.sg. Aby śledzić paczkę tutaj, wklej numer powyżej. W przypadku zamówienia Shopee otwórz je w Shopee i skopiuj numer śledzenia z danych wysyłki.

## Numer przesyłki SPX Express Singapur: jak wygląda

Peek zna dwa formaty dla SPX Express Singapore (cyfry wymyślone):

| Numer (wymyślony) | Co to jest |
| --- | --- |
| `SPXSG48291736482K` | SPXSG, 11 cyfr oraz jeszcze jedna cyfra lub litera (17 znaków): własny numer SPX. Peek od razu rozpoznaje SPX Express Singapore. |
| `SG482917364829K` | SG, 12 cyfr i końcowa cyfra lub litera (15 znaków): powiązany numer Shopee w systemie SPX. Z samego tego formatu Peek nie wskaże pewnie SPX (zobacz niżej). |
| `SPXSG4829173648` | SPXSG i tylko 10 cyfr: Peek go nie rozpoznaje. Sprawdź, czy nie brakuje cyfry. |
| `RA482917367SG` | Dwie litery, dziewięć cyfr i SG na końcu: numer Singapore Post, nie SPX. |

Spacje i małe litery nie mają znaczenia przy wklejaniu numeru do Peek.

**Numer zamówienia to nie numer śledzenia.** Jeśli wklejony kod ma format numeru zamówienia Shopee, Peek wyświetli „To wygląda na numer zamówienia Shopee”. Skopiuj zamiast niego numer śledzenia z zamówienia. Nie możesz go znaleźć? Zobacz [jak znaleźć numer przesyłki](guide:find-tracking-number).

## Co oznaczają statusy śledzenia SPX Express Singapur

System SPX odczytywany przez Peek podaje wszystkie wpisy po angielsku. Oto komunikaty rozpoznawane przez kod Peek, w przybliżonej kolejności dla przesyłki z zagranicy:

| Komunikat SPX | Co oznacza dla ciebie | Peek wyświetla |
| --- | --- | --- |
| `[China]Sender is preparing to ship your parcel` | Sprzedawca w Chinach przygotowuje paczkę. SPX jeszcze jej nie ma. | „Zarejestrowana” |
| `[China] Parcel has arrived at Hong Kong sorting centre` | Dotarła do sortowni Shopee w Hongkongu. | „W transporcie” |
| `[China] Parcel has departed from Hong Kong sorting centre` | Opuściła tę sortownię. | „W transporcie” |
| `[China] Parcel has been handed over to delivery service provider` | Przekazana przewoźnikowi lotniczemu do dalszego transportu. | „W transporcie” |
| `[China] Parcel has cleared export customs` | Przeszła odprawę eksportową — jeszcze nie singapurskie cło. | „W transporcie” |
| `Parcel has departed from the origin port: Hongkong` | Wyleciała z Hongkongu. | „W transporcie” |
| `Parcel has arrived at the destination port Singapore` | Dotarła do Singapuru: to przylot, nie zatrzymanie celne. | „W transporcie” |
| `Parcel has cleared import customs` | Singapurska służba celna zwolniła przesyłkę. | „W transporcie” |
| `Parcel has been picked up by our local logistics partner for further sorting. You will be notified when the parcel is ready for delivery.` | Paczka jest już w lokalnej sieci w Singapurze do dalszego sortowania przed doręczeniem. | „W transporcie” |
| `[Singapore] Parcel has arrived at sorting facility` | W sortowni w Singapurze. | „W transporcie” |
| `[SPX Express] Parcel is out for delivery` | Dziś w doręczeniu. | „W doręczeniu” |
| `Parcel has been delivered` | Doręczona. | „Dostarczona” |

**Inne etapy.** Kod Peek rozpoznaje też inne komunikaty SPX: odbiór od sprzedawcy lub nadanie w punkcie („Przyjęta przez przewoźnika”), nieudaną próbę doręczenia („Próba doręczenia”) oraz zwrot do sprzedawcy („Zwrócona nadawcy” albo „Zgłoszono problem”, gdy zwrot utknie). Więcej w [znaczeniu statusów śledzenia](guide:tracking-statuses).

## Doręczenie SPX Express w Singapurze

Po `[SPX Express] Parcel is out for delivery` kolejnym wpisem powinno być `Parcel has been delivered`. Jeśli SPX wiezie przesyłkę do punktu odbioru zamiast pod drzwi, Peek wyświetla „W doręczeniu” podczas transportu do tego punktu.

Ponieważ Peek nie mógł odczytać witryny SPX Express Singapore, ta strona nie podaje godzin doręczeń, liczby prób ani zasad odbioru. Zapytaj sprzedawcę lub Shopee, jeśli tam zamawiałeś. Zobacz [nieudane doręczenia](guide:missed-delivery) oraz [nieodebrane przesyłki](guide:uncollected-parcel).

## Przesyłki z zagranicy: GST i cło w Singapurze

Zasady Singapore Customs dla zakupów zagranicznych docierających drogą lotniczą lub pocztą:

- **GST przy zakupie.** Jeśli każdy przedmiot kosztuje nie więcej niż 400 S$, a zagraniczny sprzedawca lub platforma jest zarejestrowana jako płatnik GST, podatek jest już w cenie. Sprzedawca przekazuje dane płatności kurierowi i GST nie jest naliczany ponownie przy imporcie. Limit dotyczy wyłącznie wartości towaru, bez wysyłki i ubezpieczenia.
- **GST przy imporcie.** Jeśli sprzedawca nie jest zarejestrowany do GST, a łączna wartość CIF przesyłki (towar, ubezpieczenie i fracht) przekracza 400 S$, GST pobierany jest przy wwozie do Singapuru. Do 400 S$ nie pobiera się GST przy imporcie, z wyjątkiem alkoholu i tytoniu.
- **Pobrany dwukrotnie?** Jeśli zapłaciłeś GST przy zakupie i ponownie przy imporcie, opłać importowy GST, aby odebrać towar, a następnie poproś sprzedawcę o zwrot na podstawie potwierdzenia.

**Zatrzymanie celne.** Immigration & Checkpoints Authority (ICA) może zatrzymać przesyłkę kurierską z towarami objętymi cłem, kontrolowanymi lub zakazanymi albo wartą powyżej 400 S$ CIF bez faktury przy przybyciu. ICA wydaje kurierowi pokwitowanie depozytowe; aby się o nią dowiedzieć, poproś kuriera o dane przesyłki i to pokwitowanie i przekaż je urzędowi wskazanemu w pokwitowaniu.

**Oszustwa.** Singapore Customs podkreśla, że nie prosi o zapłatę GST ani o dane osobowe przez SMS, e-mail lub komunikatory. Zobacz [fałszywe SMS-y o przesyłkach](guide:delivery-scam-texts) i [przesyłki zatrzymane na cle](guide:customs).

## Paczka SPX bez aktualizacji, zagubiona lub zwrócona

**Numer jeszcze nieznaleziony.** Gdy SPX jeszcze nie zna numeru SPXSG, Peek wyświetla „Oczekiwanie na przewoźnika SPX Express Singapore” i sprawdza dalej. Sprzedawca mógł jeszcze nie nadać paczki; sprawdź również poprawność numeru.

**Utknęła na `[China]Sender is preparing to ship your parcel`.** Sprzedawca jeszcze jej nie wysłał — zapytaj, kiedy nada paczkę.

**Brak aktualizacji od dni.** Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Po czterech dniach bez nowego skanu przy statusie „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie” Peek oznacza paczkę komunikatem „Brak aktualizacji od…” i dalej pyta SPX. Zobacz [gdy śledzenie się nie aktualizuje](guide:tracking-not-updating) oraz [śledzenie paczek z Chin](guide:tracking-from-china).

**Doręczona, ale jej nie ma.** Sprawdź okolice drzwi, zapytaj domowników i sąsiadów, a potem skontaktuj się ze sprzedawcą lub Shopee. Zobacz [doręczona, ale nie otrzymana](guide:delivered-not-received).

**Zagubiona lub zwrócona.** Transport zlecił sprzedawca, więc zgłoś się do niego lub przez Shopee po zwrot pieniędzy albo nową przesyłkę. Zobacz [zagubiona paczka](guide:lost-parcel) i [zwrócona do nadawcy](guide:returned-to-sender).

## Obsługa klienta i kontakt SPX Express Singapur

Ponieważ Peek nie mógł odczytać strony SPX Express Singapore, nie podajemy tu numeru telefonu ani adresu e-mail — Peek publikuje wyłącznie kontakty odczytane bezpośrednio ze stron przewoźnika.

W sprawach zamówień Shopee kontaktuj się przez Shopee. W innych sklepach zapytaj najpierw sklep zlecający dostawę. Singapore Customs nie pomoże, bo nie ma dostępu do śledzenia firm kurierskich i odsyła do przewoźnika.

## Śledzenie paczki SPX Express Singapur w Peek

[Peek](/) śledzi przesyłkę SPX wyłącznie po numerze — bez konta, numeru telefonu ani kodu pocztowego.

- **Numer SPXSG.** Peek od razu wskazuje SPX Express Singapore („Wykryty przewoźnik”) i odpytuje bezpośrednio śledzenie SPX. Przycisk „Zmień” pozwala wybrać innego przewoźnika.
- **Link.** Wklej link do wyszukiwarki spx.sg z numerem SPXSG (`/track?`, `/m/track?` lub `/detail/`), a Peek sam wyciągnie numer.
- **Numer SG.** Z samego tego kształtu Peek nie ma pewności co do przewoźnika. Wklej go mimo to: Peek obsługuje ponad 4500 przewoźników. Podczas sprawdzania zobaczysz „Sprawdzanie usług śledzenia…”. Aby wybrać SPX ręcznie, kliknij „Zmień” — SPX Express Singapore znajdziesz w sekcji „Pasują do numeru”. Lepiej jednak użyć numeru SPXSG, bo system SPX często odnajduje paczki Shopee tylko po nim.

Przesyłka dodana bez przewoźnika pokazuje „Brak historii śledzenia”, gdy Peek pyta usługi śledzenia. Jeśli jedna z nich wskaże wyłącznie SPX Express Singapore z datowanymi wpisami, Peek przypisze ją do SPX.

**Co pokazuje Peek.** Status, każdy wpis słowami SPX oraz lokalizację przy skanach w magazynach, portach i sortowniach. SPX podaje dokładny czas skanu, więc Peek wyświetla go w twojej strefie czasowej („Godziny w twojej strefie czasowej:”). Peek zachowuje też kilka etapów ukrywanych na stronie SPX, jak odprawę importową czy zwrot w drodze. Pomija lokalizację sprzedawcy, adresy biur i nazwisko osoby odbierającej. SPX nie podaje planowanej daty dostawy, wagi ani punktu odbioru, więc Peek ich nie wyświetla.

**Od Janio do SPX.** Gdy śledzenie paczki Janio wymienia SPX z singapurskim numerem SPX, karta pokazuje „Doręcza: SPX Express Singapore”, a Peek pyta także SPX. Dopóki SPX ma wyłącznie zapowiedź bez własnego skanu, wyświetla się „Zgłoszona przewoźnikowi SPX Express Singapore, jeszcze nieprzyjęta”. Gdy tylko SPX doda skan nie starszy od ostatniego z Janio, Peek śledzi paczkę w SPX i zachowuje historię z Janio.

**Częstotliwość i powiadomienia.** Peek odpytuje SPX bezpośrednio nawet co 10 minut, a w dniu, gdy SPX zeskanuje paczkę w doręczeniu — nawet co 2 minuty od 6:00 czasu singapurskiego, gdy paczka jest otwarta w Peek lub w dniu dostawy z włączonymi powiadomieniami. Po dwóch dniach bez zmian (po 12 godzinach, gdy przesyłka ma jeszcze status „Zarejestrowana” lub „Przyjęta przez przewoźnika”) Peek sprawdza co godzinę; po siedmiu dniach bez zmian przy statusie „Zarejestrowana” co 6 godzin. Dla powiadomień możesz wybrać „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Po czterech dniach bez skanu przy statusie „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie” Peek wyświetla „Brak aktualizacji od…”, przypomina, że „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika SPX Express Singapore.” oraz podsuwa link „Poproś przewoźnika SPX Express Singapore o pomoc”. Peek nie skontaktuje się za ciebie z SPX, Shopee ani ze sprzedawcą.

## Najczęstsze pytania o SPX Express Singapur

### Czy SPX Express to to samo co Shopee Express?

Tak. Katalog Peek wymienia SPX Express Singapore również pod nazwami Shopee Xpress Singapore i Shopee Express Singapore, a firma doręcza zamówienia Shopee. W innych krajach SPX prowadzi osobne sieci, np. [SPX Express Brazylia](carrier:spx-br) — numer SPXSG należy do Singapuru.

### Czym różni się numer SPXSG od numeru SG?

Numer SPXSG to własny numer SPX, dzięki któremu Peek od razu go rozpoznaje. Numer SG to powiązany numer Shopee. Ponieważ wyszukiwarka SPX często znajduje paczkę Shopee tylko po numerze SPXSG, lepiej użyć właśnie jego.

### Co oznacza „Parcel has been picked up by our local logistics partner”?

Twoja przesyłka z zagranicy dotarła do Singapuru i trafiła do lokalnej sieci doręczeń do dalszego sortowania. SPX informuje, że dostaniesz powiadomienie, gdy będzie gotowa do doręczenia. Peek pokazuje „W transporcie”.

### Dlaczego moje śledzenie SPX się nie aktualizuje?

Jeśli ostatni wpis to `[China]Sender is preparing to ship your parcel`, sprzedawca jeszcze jej nie nadał. Później przesyłki często milkną podczas lotów, odpraw celnych lub w weekendy. Po czterech dniach bez nowości przy statusie „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie” Peek ją oznacza; jeśli nadal stoi, zapytaj sprzedawcę lub Shopee.

:::sources
- [Singapore Customs: GST on Low-Value Goods](https://www.customs.gov.sg/personal-shipment/buying-from-overseas/gst-on-low-value-goods/) – próg 400 S$, GST przy zakupie lub imporcie, podwójne pobranie, oszustwa
- [Singapore Customs: Courier / Postal Procedures](https://www.customs.gov.sg/personal-shipment/buying-from-overseas/courier-postal-procedures/) – przesyłki zatrzymane przez ICA, kontakt z kurierem w sprawie śledzenia
- [Universal Parcel Scraper: SPX Express Singapore](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/spx-sg) – nazwy, formaty numerów, linki do śledzenia, komunikaty i etapy
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – przypisanie każdego skanu do etapu, usługi śledzenia
:::
