---
title: Śledzenie Express One: statusy, punkty i kontakt
description: Śledź paczkę Express One na Węgrzech: skąd wziąć numer, znaczenie statusów, punkty odbioru, automaty, nieudane doręczenia i kontakt.
slug: sledzenie-express-one
published: 2026-11-22
updated: 2026-11-22
---

Express One Hungary to węgierska firma kurierska należąca do [Austrian Post](carrier:austrian-post), doręczająca przesyłki ze sklepów internetowych z udziałem około 700 kurierów i ponad 2300 punktów odbioru oraz automatów paczkowych. Śledź paczkę po identyfikatorze przesyłki na [tracking.expressone.hu](https://tracking.expressone.hu/) albo wklej go powyżej. Numer przychodzi w e-mailu od Express One po zarejestrowaniu i fizycznym przyjęciu paczki.

## Numer przesyłki Express One

Express One wysyła e-mail z zapowiedzią doręczenia, gdy nadawca zarejestruje przesyłkę i trafi ona fizycznie do Express One; SMS przychodzi tylko wtedy, gdy nadawca zapłaci za tę usługę. Na Węgrzech wiadomości zawierają przedział godzinowy, kontakt do kuriera, ewentualną kwotę pobrania i link do zmiany dostawy. Przesyłki zagraniczne nie dostają powiadomień. Nie masz wiadomości? Zapytaj sklep i zobacz [jak znaleźć numer przesyłki](guide:find-tracking-number).

Wyszukiwarka przyjmuje też numer referencyjny lub numer dokumentu nadawcy, lecz wymagają one 5-cyfrowego Company ID nadawcy — użyj więc identyfikatora paczki (numeru z kodu kreskowego).

Express One nie publikuje oficjalnego formatu. Spotykane dotąd identyfikatory mają dwie postacie (cyfry wymyślone):

| Numer | Do kogo należy |
| --- | --- |
| `4829173648291` | 13 cyfr: identyfikator paczki Express One, choć taką długość stosują też DPD France i inni przewoźnicy. |
| `148291736482917364821011` | 24 cyfry zakończone węgierskim kodem pocztowym: Express One. 24 cyfry stosują też Austrian Post, [InPost](carrier:inpost), [bpost](carrier:bpost) i [Ciblex](carrier:ciblex). |
| `48291736482917` | 14 cyfr: DPD, nigdy Express One. |
| `48291736482` | 11 lub 12 cyfr: GLS, nigdy Express One. |
| `RR482917367HU` | Dwie litery, 9 cyfr, `HU`: Magyar Posta, nigdy Express One. |

Kieruj się nazwą przewoźnika z e-maila od sklepu; więcej w [formatach numerów przesyłek](guide:tracking-number-formats).

## Co oznaczają statusy śledzenia Express One

tracking.expressone.hu wyświetla się po angielsku lub węgiersku zależnie od języka przeglądarki, z przełącznikiem EN/HU. Każdy skan ma kod, opis, datę i godzinę, a skany w hubie lub oddziale dodają miejscowość w nawiasie, np. „(HU - Budapest)”. Angielskie komunikaty w typowej kolejności:

| Status Express One | Co oznacza dla ciebie |
| --- | --- |
| `Shipping details received, shipment not yet handed over to EXOHU` | Nadawca [zarejestrował przesyłkę](guide:tracking-statuses); Express One jeszcze jej nie otrzymało. |
| `Shipment (pan) data uploaded` | Dane przesyłki trafiły do systemu Express One, zwykle tuż po skanie w hubie. |
| `Arrived at central hub` | W głównym hubie w Budapeszcie. |
| `Departed central hub` | Opuściła hub. |
| `Arrived at depot` | W oddziale doręczającym pod twój adres. |
| `Out for delivery` | Dziś w doręczeniu u kuriera. |
| `Delivery schedule updated` | Przeliczono plan doręczeń; paczka się nie przemieściła. |
| `Processing in progress` | Dostawę przełożono na późniejszy termin bez podania powodu. |
| `Addressee not present at delivery address` | Kurier był na miejscu, ale nikogo nie zastał. |
| `Addressee unavailable` | Druga próba doręczenia również się nie powiodła. |
| `Obstacles of nature` | Zła pogoda przerwała trasę; liczy się jako próba, a paczka wyjedzie ponownie. |
| `Successful delivery` | Doręczona. |

EXOHU to skrót od Express One Hungary: pierwszy status oznacza, że utworzono dopiero etykietę. Pasek nad historią ma własne etykiety, od `Shipment data uploaded` do `Successfully delivered`. Po węgiersku doręczenie to `Sikeres kézbesítés`.

## Doręczenie, punkty odbioru i nieudane dostawy

**Kiedy przyjedzie.** Zapowiedź podaje 4-godzinny przedział. Rano w dniu dostawy przychodzi wiadomość z 2-godzinnym oknem, które Express One określa wyłącznie jako orientacyjne: kurier może przyjechać wcześniej lub później. Tego dnia strona śledzenia pokazuje, ile przystanków zostało kurierowi przed tobą. W weekendy nie ma doręczeń, z wyjątkiem płatnej dostawy sobotniej dla nadawców firmowych.

**Zmiany.** Przycisk „Modification” w porannym SMS-ie lub e-mailu pozwala zmienić godzinę lub adres albo przekierować paczkę do punktu lub automatu nawet godzinę przed przyjazdem kuriera. Zmiany są możliwe tylko w pierwszym dniu dostawy i tylko raz. Część opcji zależy od nadawcy (duża paczka nie zmieści się w automacie). Regulamin Express One stanowi, że zmiana liczy się jako próba doręczenia i może wiązać się z opłatą; wpis na blogu firmy z 2024 roku podaje, że przekierowanie jest bezpłatne.

**Przy odbiorze.** Odbiorcy prywatni składają podpis i podają numer dokumentu tożsamości. Pobranie można opłacić gotówką (do 300 000 Ft), kartą (do 1 000 000 Ft) albo z góry przez link z zapowiedzi; w punkcie Exopoint limit gotówki wynosi 250 000 Ft. Paczki nie wolno otwierać przed podpisaniem i zapłatą.

**Nieudane doręczenie.** Według regulaminu Express One powiadamia e-mailem lub SMS-em najpóźniej następnego dnia roboczego, podejmuje jedną bezpłatną próbę ponowną i przechowuje paczkę maksymalnie do 5. dnia roboczego. W tym czasie możesz poprosić o nową datę lub punkt odbioru. Po drugiej nieudanej próbie paczka wraca do nadawcy; wpis na blogu z 2024 roku wskazuje, że wcześniej czeka 5 dni roboczych w centralnym oddziale w Budapeszcie.

**Punkty odbioru i automaty.** Wiele sklepów pozwala wybrać przy zamówieniu punkt lub automat Exopoint. Strona punktów odbioru Express One mówi o przechowaniu do tygodnia, 24/7. Wpisy na blogu podają różne terminy: 3 dni w EXOBOX, 2 w Alzabox, do 7 w punkcie Packeta (2025), 72 godziny w automacie (2026), 5 dni w punkcie (2024) oraz dostęp całodobowy wyłącznie dla automatów EXOBOX i Alzabox. Odbiór w centrum logistycznym w Budapeszcie (H-1239 Budapest, Európa street 12, building L1) wymaga umówienia się z obsługą klienta.

## Paczka Express One opóźniona, uszkodzona lub zagubiona

**Brak aktualizacji lub nie znaleziono.** Express One nie podaje czasu oczekiwania. Wyszukiwarka informuje, że numer może być błędny, starszy niż 180 dni albo jeszcze niezarejestrowany przez nadawcę, i radzi spróbować później. Regulamin pozwala usunąć dane, jeśli paczka nie dotrze w ciągu 5 dni roboczych, i traktuje czas dostawy jako orientacyjny. Zobacz [gdy śledzenie się nie aktualizuje](guide:tracking-not-updating).

**Uszkodzona lub zły towar.** Jak najszybciej zgłoś to sklepowi: Express One wszczyna reklamację po zgłoszeniu przez nadawcę i odpowiada za przewóz, nie za zawartość. Widoczne uszkodzenia zgłoś kurierowi przy odbiorze; ukryte szkody zgłoś pisemnie w ciągu 3 dni roboczych od doręczenia, inaczej prawo wygasa.

**Zagubiona.** Zwykle reklamuje nadawca, ale możesz to zrobić sam, jeśli przesyłka została ci doręczona albo nadawca pisemnie przelał na ciebie uprawnienie. Pisemną reklamację z tytułu całkowitej utraty składa się w ciągu 6 miesięcy od 15. dnia po nadaniu, z numerem listu przewozowego, dowodem zawartości i wartości oraz formularzem Express One. Express One odpowiada w ciągu 30 dni kalendarzowych; w razie niezadowolenia możesz zwrócić się w ciągu 30 dni do NMHH, węgierskiego organu ds. mediów i łączności.

## Telefon i obsługa klienta Express One

Dane z angielskiej strony kontaktowej Express One, czynnej w dni robocze 8:00–17:00 (FAQ podaje do 18:00) czasu węgierskiego:

- **Obsługa klienta:** +36 1 8 777 400, ugyfelszolgalat@expressone.hu — także gdy kurier nie odbiera. W okresach wzmożonego ruchu Express One zaleca kontakt pisemny.
- **Obsługa międzynarodowa:** +36 1 8 777 410, intcs@expressone.hu; reklamacje na intcsreklamacio@expressone.hu.
- **Szkody:** +36 1 8 777 470, karugy@expressone.hu.
- **Formularz kontaktowy:** na [stronie kontaktowej Express One](https://expressone.hu/en/kapcsolat), z tematami takimi jak „I am expecting a parcel” i „Delivery complaint”; oba wymagają numeru przesyłki.

Strona Parcel Tracking podaje również numer +36 1 8 777 444 oraz chatbota.

## Śledzenie paczek Express One w Peek

13-cyfrowy numer nie jest odpytywany w Express One automatycznie: [Peek](/) sprawdza najpierw innych przewoźników. Gdy żaden go nie zna, zobaczysz „jeszcze nie znaleziono · Peek sprawdza dalej” — kliknij „Zmień” i wybierz „Express One”. Przy numerze 24-cyfrowym Peek pyta Express One jako pierwszego, jeśli wyszukujesz z Węgier — przed [Austrian Post](carrier:austrian-post), [InPost](carrier:inpost) i [Ciblex](carrier:ciblex); poza Węgrami sprawdza pozostałych trzech przewoźników, więc wybierz „Express One” przyciskiem „Zmień”. Możesz też wkleić link tracking.expressone.hu z `?plc_number=`, który od razu wskazuje Express One niezależnie od formatu.

Skany Express One podają godzinę bez strefy czasowej: Peek odczytuje je według zegara miejsca skanu (czasu węgierskiego na Węgrzech) i wyświetla w twojej strefie czasowej („Godziny w twojej strefie czasowej:”). Odczytuje status oraz przy skanach w hubach miasto i kraj. Opisy pozostają oryginalne, poza `Out for delivery`; `Successful delivery` oznacza doręczenie, a trasa zwrotna zwrot do nadawcy. Nieznany skan pokazuje opis bez etapu. Brak przewidywanego terminu, wagi, punktu odbioru, paska postępu oraz podpisu.

Peek odpytuje Express One bezpośrednio nawet co 10 minut, a w dniu, gdy Express One zeskanuje paczkę w doręczeniu — co 2 minuty od 6:00 według zegara ostatniego skanu (czasu węgierskiego na Węgrzech), gdy paczka jest otwarta w Peek lub w dniu dostawy z włączonymi powiadomieniami. Po dwóch dniach bez zmian (po 12 godzinach, gdy przesyłka ma jeszcze status „Zarejestrowana” lub „Przyjęta przez przewoźnika”) Peek sprawdza co godzinę. Dla powiadomień możesz wybrać „Wszystkie aktualizacje”, „Tylko ważne” lub „Tylko w dniu dostawy”. Po czterech dniach bez skanu w transporcie, poza odprawą celną i zgłoszonym problemem, Peek wyświetla „Brak aktualizacji od…”, przypomina, że „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika Express One.” oraz podsuwa link „Poproś przewoźnika Express One o pomoc”.

## Najczęstsze pytania o Express One

### Co oznacza „shipment not yet handed over to EXOHU”?

Nadawca utworzył przesyłkę, ale Express One Hungary jeszcze jej nie otrzymało. Jeśli taki stan utrzymuje się przez kilka dni, zapytaj sklep.

### Jak długo paczka Express One czeka w punkcie odbioru?

Do tygodnia o każdej porze — podaje strona punktów odbioru Express One; wpisy na blogu wskazują od 2 do 7 dni zależnie od punktu oraz całodobowy dostęp wyłącznie w automatach EXOBOX i Alzabox.

### Czy Express One na Słowacji lub w Słowenii to ta sama firma?

Nie. Również należą do Austrian Post, ale są osobnymi spółkami; ta strona i „Express One” w Peek dotyczą wyłącznie spółki węgierskiej.

:::sources
- [Express One: Parcel Tracking](https://expressone.hu/en/csomagkovetes) – przystanki kuriera, infolinia, chatbot
- [Express One: Tracking](https://tracking.expressone.hu/) – typy ID, Company ID, komunikat o braku danych, 180 dni, statusy, języki
- [Express One: Frequently asked questions](https://expressone.hu/en/gyik) – modyfikacja, okno 2-godzinne, sobota, pobranie, uszkodzenia, zły towar
- [Express One: GYIK](https://expressone.hu/gyik) – limity pobrania
- [Express One: Contact](https://expressone.hu/en/kapcsolat) – telefony, e-maile, godziny, formularz, centrum logistyczne
- [Express One: Parcel point](https://expressone.hu/en/csomagpont) – 2300 punktów i automatów, tydzień, 24/7, wybór przy zamówieniu
- [Express One: Home](https://expressone.hu/en) – Austrian Post, 700 kurierów, przekierowanie do godziny przed przyjazdem
- [Express One: General Terms and Conditions](https://expressone.hu/downloads/General_Terms_and_Conditions_2026_08_15.pdf) – próby, przechowywanie, dokument tożsamości, opłata za zmianę, reklamacje, NMHH, usuwanie danych
- [Express One: Merre jár a csomagom?](https://expressone.hu/blog/merre-jar-a-csomagom-csomagkovetes-es-ertesitesi-rendszerek-alkalmazasa) – zapowiedź po rejestracji i przyjęciu, okna 4- i 2-godzinne, opcja SMS
- [Express One: Csomag átirányítás és a kézbesítés átütemezése](https://expressone.hu/blog/csomag-atiranyitas-es-a-kezbesites-atutemezese) – bezpłatne przekierowanie, 5 dni w oddziale w Budapeszcie lub punkcie odbioru
- [Express One: Minden, amit az EXOPOINT megoldásokról tudni érdemes](https://expressone.hu/blog/minden-amit-az-exopoint-megoldasokrol-tudni-erdemes) – godziny otwarcia, 3, 2 i 7 dni
- [Express One: Csomagküldés csomagautomatába](https://expressone.hu/blog/csomagkuldes-csomagautomataba-miert-valasztjak-egyre-tobben) – 72 godziny w automacie
- [Express One: eBOX GYIK](https://expressone.hu/ebox/faq) – treść powiadomień, brak przy eksporcie, 250 000 Ft gotówką w Exopoint
- [Austrian Post: Austrian Post Group acquires Hungarian parcel delivery provider](https://news.post.at/presse/en/post/id/2255390/) – spółka w 100% zależna
- [Austrian Post: International subsidiaries](https://post.at/en/j/c/careers-austrian-post-subsidiaries-international) – spółki Express One w innych krajach
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – dane odczytywane przez Peek
:::
