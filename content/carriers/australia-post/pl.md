---
title: Śledzenie Australia Post: numery, statusy i automaty
description: Śledź paczkę Australia Post: gdzie znaleźć article ID, co oznaczają angielskie statusy, odbiór na poczcie lub w Parcel Locker, odprawa celna i kontakt.
slug: sledzenie-australia-post
published: 2026-11-21
updated: 2026-11-21
---

Australia Post to narodowy operator pocztowy **Australii**. Przesyłkę sprawdzisz bez logowania na stronie [auspost.com.au/mypost/track](https://auspost.com.au/mypost/track), w aplikacji AusPost albo wklejając numer w pole powyżej. Kod pochodzi od nadawcy: z potwierdzenia zamówienia w sklepie internetowym, z wiadomości e-mail od Australia Post lub z paragonu nadania w australijskiej placówce pocztowej.

## Numer przesyłki Australia Post: gdzie go znaleźć i jak wygląda

Australia Post używa określenia numer śledzenia lub identyfikator przesyłki (*tracking number, or article ID*). Znajdziesz go w jednym z tych miejsc:

- **Zakup przez internet:** w potwierdzeniu zamówienia lub na fakturze ze sklepu; jeśli nadawca przekazał Australia Post Twój adres e-mail, operator sam wysyła numer i powiadomienia o drodze paczki.
- **Nadanie na poczcie w Australii:** często na potwierdzeniu lub paragonie z okienka.
- **Opłacona koperta kurierska (*prepaid satchel*):** przy kodzie kreskowym oraz na odklejanym pasku.

Australia Post nie wyszukuje paczek po nazwisku ani adresie — jeśli nie masz numeru, poproś o niego nadawcę. Przesyłki powiązane z adresem e-mail lub numerem telefonu konta MyPost pojawiają się na koncie i w aplikacji automatycznie. Na stronie `auspost.com.au/mypost/track` można wpisać jednocześnie do 10 numerów.

Australia Post nie publikuje oficjalnego wykazu formatów; oto układy rozpoznawane przez Peek (ze zmyślonymi znakami):

| Układ (przykład) | Co oznacza |
| --- | --- |
| `33X482910573000482913` | Identyfikator artykułu (21 znaków): 3 litery lub cyfry, 9 cyfr, `000` oraz 6 cyfr. |
| `2933X482910573000482914` | Ten sam identyfikator poprzedzony 2 cyframi (23 znaki). |
| `94004829105736000482916` | 23 cyfry zaczynające się od `9400` z `000` przed ostatnimi 6 cyframi: również Australia Post. |
| `29ABC4829105` | Numer listu przewozowego (*consignment number*), 12 znaków. |
| `ABC4829105` | Numer listu przewozowego, 10 znaków. |
| `ABCZ48291057` | Numer przesyłki StarTrack (12 znaków): 3 litery lub cyfry, `Z` i 8 cyfr. |
| `0048291057364918273643` | Przedpłacony kod kreskowy z 22 cyfr zaczynający się od `00`–`03` (z cyfrą kontrolną GS1). |
| `R482910573649182736491827` | Litera `R` i 24 cyfry (25 znaków). |
| `EE482910576AU` | Międzynarodowa przesyłka EMS, paczka lub list śledzony: `C`, `E` lub `L`, litera, 9 cyfr i `AU`. |
| `RR482910576AU` | Inny międzynarodowy numer pocztowy zakończony na `AU` (np. przesyłka polecona). |

Podobne kody innych firm: 2 litery i 10 cyfr (`AB4829105736`) należą do Aramex Australia lub Aramex New Zealand, natomiast 22 cyfry od `9400` (`9400482910573649182736`) to numer [USPS](carrier:usps). Gdy wyszukiwarka nie rozpoznaje numeru, mógł wkraść się błąd albo paczka nie została jeszcze zeskanowana — Australia Post radzi spróbować ponownie po 24 godzinach. Zobacz też [formaty numerów przesyłek](guide:tracking-number-formats) i [jak znaleźć numer przesyłki](guide:find-tracking-number).

## Co oznaczają statusy śledzenia Australia Post

Nad listą szczegółowych skanów Australia Post wyświetla skrócony etap po angielsku:

| Status Australia Post | Co oznacza dla przesyłki | Peek pokazuje |
| --- | --- | --- |
| `Label created by sender` | Nadawca wygenerował etykietę; Australia Post nie ma jeszcze paczki. | „Zarejestrowana” |
| `Pending` | Przesyłka nie została jeszcze zeskanowana w sieci Australia Post; aktualizacja pojawia się zwykle w 24–48 godzin. | „Oczekiwanie na przewoźnika Australia Post” (przed pierwszym skanem) |
| `We've got it` | Australia Post przyjęła paczkę. | „Przyjęta przez przewoźnika” |
| `It's on its way` | Nadana, opracowana i w drodze między sortowniami. | „W transporcie” |
| `International arrival - awaiting clearance` | Przesyłka z zagranicy: oczekuje na australijską odprawę celną. | „W urzędzie celnym” |
| `Possible delay` | Pogoda, sytuacja nadzwyczajna, błąd nadawcy lub cło spowalniają przewóz, ale paczka jedzie dalej. | Zależnie od kodu skanu, np. „W transporcie” |
| `It's coming today` / `Onboard for delivery` | Załadowana do pojazdu kurierskiego na dzisiejsze doręczenie. | „W doręczeniu” |
| `Attempted delivery` | Nikogo nie zastano lub doręczyciel nie miał dostępu pod adres. | „Próba doręczenia” |
| `Awaiting collection` | Czeka na odbiór w urzędzie pocztowym lub automacie *Parcel Locker* wskazanym w skanie. | „Do odbioru” |
| `Delivered` / `Delivered - Left in a safe place` | Doręczona pod adres lub pozostawiona w bezpiecznym miejscu (*Safe Drop*). | „Dostarczona” |
| `Returning to sender` | Nieodebrana w terminie, adresat nieznany, odmowa przyjęcia lub uszkodzenie: wraca do nadawcy. | Zależnie od kodu skanu |

Jeśli `Pending` albo `Ready for processing` utrzymuje się dłużej niż tydzień, zapytaj nadawcę, czy paczka faktycznie została wysłana. W Australii przesyłki międzystanowe przechodzą przez główne sortownie w Redbank (QLD), Chullora (NSW) lub Sunshine West (VIC), więc skan daleko od nadawcy i odbiorcy jest normalny. Dla przesyłki z zagranicy do Australii `It's on its way` oznacza dotarcie do Australii i widnieje aż do wydania paczki kurierowi.

W Australii listonosze i kurierzy doręczają zazwyczaj od poniedziałku do piątku w godz. 7:00–17:00; paczka niedoręczona do 17:00 otrzymuje zwykle priorytet następnego dnia roboczego. Przy statusie `It's coming today` w części rejonów przychodzi SMS z 2-godzinnym oknem doręczenia.

`Contact sender` oznacza zwrot z powodu błędnych lub niepełnych danych adresowych albo nieodebrania paczki przez 10 dni roboczych; `Returned` potwierdza zwrot do nadawcy, z którym trzeba się skontaktować w sprawie ponownej wysyłki.

## Nieudane doręczenie, odbiór na poczcie i Parcel Lockers w Australii

Poniższe zasady dotyczą doręczeń na terenie **Australii**:

Gdy nikogo nie ma w domu i nie można zostawić paczki w bezpiecznym miejscu (*Safe Drop*), przesyłka trafia do pobliskiej placówki pocztowej albo — jeśli podano Twój numer komórkowy — do automatu *Parcel Locker* (otrzymasz kod odbioru). Użytkownicy MyPost dostają powiadomienie e-mailem, w aplikacji lub SMS-em; pozostali mogą nadal otrzymać papierowe awizo *Sorry We Missed You*. Zobacz także [nieudana próba doręczenia](guide:missed-delivery).

Australijski urząd pocztowy przechowuje paczkę przez **10 dni roboczych**, po czym odsyła ją nadawcy. Australia Post nie oferuje ponownego doręczenia pod drzwi z placówki pocztowej; przeniesienie paczki do innego urzędu pocztowego kosztuje **7,55 AU$** płatne przy odbiorze (sam formularz przeniesienia pokazuje jeszcze kwotę 7,20 AU$), a jeśli nie wiesz, dlaczego paczka trafiła akurat tam, możesz zgłosić problem z doręczeniem i poprosić o zwolnienie z opłaty. Zabierz dokument ze zdjęciem albo dwa dokumenty bez zdjęcia, z których jeden potwierdza adres. Osoba upoważniona odbierze przesyłkę na podstawie upoważnienia z aplikacji, formularza lub awiza oraz własnego dokumentu tożsamości.

Automaty *Parcel Lockers* są bezpłatne i w większości lokalizacji czynne 24/7. Na odbiór kodem lub kodem QR z aplikacji masz **2 dni robocze** (48 godzin bez weekendów i dni ustawowo wolnych); po tym czasie (po przypomnieniu e-mail wysłanym 24 godziny wcześniej) przesyłka jest przenoszona do pobliskiego urzędu pocztowego na 10 dni roboczych.

Z bezpłatnym kontem MyPost możesz przekierować przypisaną paczkę pod inny adres, dopóki nie wyjedzie do doręczenia (dolicz jeden dzień roboczy), oraz włączyć *Safe Drop* maksymalnie na godzinę przed dostawą.

## Brak nowych skanów, opóźnienie albo status doręczenia bez paczki

Kilka dni ciszy w śledzeniu nie oznacza zatrzymania przesyłki: Australia Post podkreśla, że paczka nadal jest w drodze, a większość opóźnionych przesyłek dociera w ciągu **5 dni roboczych** po planowanej dacie. Więcej w poradniku [dlaczego śledzenie się nie aktualizuje](guide:tracking-not-updating).

Australia Post kieruje odbiorców najpierw do nadawcy w celu potwierdzenia numeru i adresu oraz zgłoszenia reklamacji. Jeśli to nie pomoże, po upływie **10 dni roboczych** od przewidywanej daty wypełnij formularz opóźnionej lub zaginionej przesyłki (wymaga darmowego konta MyPost). W Australii paczki z zagranicy mogą spędzić do **15 dni** w australijskim urzędzie celnym — dłużej, gdy naliczane są należności — a w razie dłuższego zatrzymania służby celne lub kwarantannowe wysyłają pismo do adresata; reklamację za długie opóźnienie międzynarodowe składa nadawca u operatora pocztowego kraju nadania. Zobacz też [paczka zatrzymana przez urząd celny](guide:customs).

**Oznaczona jako doręczona, ale jej nie ma.** Sprawdź osłonięte miejsca na posesji, zapytaj domowników lub administrację budynku, obejrzyj zdjęcie z doręczenia w aplikacji lub formularzu i wyślij zgłoszenie; jeśli zdjęcie potwierdza zostawienie paczki pod Twoim adresem, a przesyłka zniknęła, zgłoś kradzież na policję. Uszkodzoną paczkę w Australii zanieś wraz z opakowaniem do placówki pocztowej wykonującej ocenę szkód. Większość usług obejmuje do **100 AU$** odszkodowania za utratę lub uszkodzenie (przy zaginięciu krajowym wypłacanego zazwyczaj nadawcy, a przy uszkodzeniu odbiorcy), z terminem zgłoszenia **6 miesięcy** od nadania; przy zakupach internetowych skontaktuj się najpierw ze sprzedawcą. Więcej w [dostarczona, ale nieodebrana](guide:delivered-not-received).

## Kontakt z Australia Post i pomoc

Kanały kontaktu podawane przez Australia Post:

- **Telefon:** `13 76 78` (`13POST`) na terenie Australii lub `+61 3 8340 7239` z zagranicy, od poniedziałku do piątku w godz. 8:00–18:00 z wyjątkiem australijskich świąt państwowych.
- **Online:** formularze zaginionych/opóźnionych przesyłek i problemów z doręczeniem oraz czat z botem lub konsultantem na [auspost.com.au/help](https://auspost.com.au/help).
- **Osobiście w Australii:** reklamację pomoże złożyć dowolna z ponad 4100 placówek pocztowych.

StarTrack, oddział kurierski i frachtowy Australia Post, ma własną infolinię (`13 23 45`) i formularze, lecz jego przesyłki śledzi się w tej samej sieci oraz w aplikacji AusPost.

> Australia Post zastrzega, że nigdy nie dzwoni, nie wysyła SMS-ów ani e-maili z prośbą o nieautoryzowane dane osobowe bądź finansowe ani o niespodziewaną dopłatę (np. za ponowne doręczenie czy weryfikację kodu pocztowego), nawet gdy nadawcą wiadomości wydaje się *AusPost*. Podejrzane wiadomości przekaż na adres `scams@auspost.com.au` i przeczytaj [fałszywe SMS-y o paczce](guide:delivery-scam-texts).

## Śledzenie przesyłek Australia Post w Peek

[Peek](/) od razu rozpoznaje Australia Post dla 21- i 23-znakowych identyfikatorów artykułu oraz międzynarodowych numerów EMS, paczek i listów śledzonych zakończonych na `AU` (`C…AU`, `E…AU`, `L…AU`) z poprawną cyfrą kontrolną, pokazując „Wykryty przewoźnik” z przyciskiem „Zmień”. Numery listów przewozowych (w tym StarTrack), 22-cyfrowe kody przedpłacone i 25-znakowe kody `R` nie rozstrzygają przewoźnika same: podczas dodawania Peek odpytuje pasujące firmy („Sprawdzanie usług śledzenia…”) i wyświetla „Australia Post ma tę przesyłkę.”, gdy system potwierdzi numer, albo pozwala wybrać Australia Post przyciskiem „Zmień” w sekcji „Pasują do numeru”. Pozostałe numery pocztowe z końcówką `AU` otwierają się jako przesyłka pocztowa, podczas gdy Peek pyta Australia Post. Linki z `auspost.com.au` zawierające `id=` (w tym `#/search?id=`) lub `/mypost/track/details/` wskazują Australia Post niezależnie od kształtu numeru.

Gdy bramka Australia Post odrzuca bezpośrednie zapytanie Peek, przeglądarka na serwerze Peek otwiera `auspost.com.au`; ponieważ zewnętrzne serwisy śledzenia przechowują bardzo niewiele zdarzeń Australia Post, w razie zablokowania także przeglądarki historia może być skąpa.

Peek prezentuje aktualny status, każdy skan z miejscowością na mapie, godzinę doręczenia, kraj docelowy oraz nazwę urzędu pocztowego lub automatu *Parcel Locker*, w którym paczka czeka na odbiór, pomijając nazwisko odbiorcy, ulicę i zdjęcia doręczenia (doręczenie wyświetla się wyłącznie jako „Dostarczona”). Własne skany Australia Post mają australijskie przesunięcie czasowe i są przeliczane na strefę Twojego urządzenia („Godziny w twojej strefie czasowej: …” pod historią na `peektracker.com` oraz w aplikacjach na iPhone'a i Androida). Dopóki paczka jest w drodze, Peek wyświetla przewidywaną datę lub przedział dni z Australia Post do jego upływu — a w dniu dostawy okno godzinowe, dopóki nie minie — i ukrywa prognozę po nieudanej próbie doręczenia lub problemie, a także gdy przesyłka zostanie doręczona, zwrócona albo trafi do odbioru. Jeśli Australia Post nie podaje terminu (np. dla przesyłki wrzuconej do skrzynki ulicznej lub zaadresowanej odręcznie), narzędzie na stronie Australia Post potrafi nadal oszacować go po kodzie pocztowym. Peek nie przekieruje paczki ani nie zmieni automatu.

Dopóki numer nie jest jeszcze znany, Peek wyświetla „Oczekiwanie na przewoźnika Australia Post”; jeśli po dwóch dniach nie rozpozna go ani Australia Post, ani inne źródło, pokazuje „Po dwóch dniach przewoźnik nadal nie zna tego numeru. Sprawdź go, wybierz innego przewoźnika lub usuń przesyłkę.”, a po czterech dniach bez nowego skanu w drodze udostępnia link „Poproś przewoźnika Australia Post o pomoc”.

Gdy masz otwartą kartę przesyłki lub w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza aktualizacje nawet co 10 minut — a od godz. 6:00 czasu lokalnego (lub pół godziny przed otwarciem zapowiedzianego okna dostawy) w dniu wydania paczki do doręczenia nawet co 2 minuty bądź co 5 minut przy odczycie przez przeglądarkę, dopóki odpowiada śledzenie Australia Post (przez zewnętrzne serwisy śledzenia same: co godzinę, co 3 godziny po spokojnym dniu i do co 15 minut w dniu doręczenia przy otwartej paczce lub włączonych alertach; po przejęciu przesyłki przez pocztę kraju docelowego Peek przejmuje jej rytm); w pozostałym czasie, a także po dwóch dniach bez zmian (lub 12 godzinach na etapie „Zarejestrowana” bądź „Przyjęta przez przewoźnika”), sprawdza co godzinę. Powiadomienia ustawisz na „Każdy skan”, „Ważne etapy” albo „Tylko doręczenie”. Przesyłka pocztowa nadana za granicą do Australii przełącza się na Australia Post, gdy tylko operator pokaże jej postęp.

## Najczęstsze pytania o śledzenie Australia Post

### Co oznacza status Pending w Australia Post?

Przesyłka nie została jeszcze zeskanowana w systemie Australia Post — mogła nie zostać jeszcze nadana albo wrzucono ją do ulicznej skrzynki pocztowej w Australii. Zwykle aktualizuje się w ciągu 24–48 godzin. Status `Pending` widnieje również przy starych numerach bez aktywności od ponad 3 miesięcy.

### Jak długo Australia Post trzyma paczkę w Parcel Locker?

**2 dni robocze** (48 godzin bez weekendów i świąt). Następnie paczka trafia do najbliższego urzędu pocztowego wydającego przesyłki, gdzie czeka **10 dni roboczych** przed zwrotem do nadawcy.

### Czy każda przesyłka Australia Post ma numer śledzenia?

Nie: usługi *International Post Registered* oraz *International Economy* (lotnicza i morska) standardowo nie obejmują śledzenia. W przypadku paczek wysyłanych z zagranicy do Australii dostępność śledzenia zależy od usługi wybranej u poczty lub kuriera w kraju nadania.

:::sources
- [Australia Post: Śledzenie przesyłek](https://auspost.com.au/mypost/track) – sprawdzanie do 10 numerów przesyłek naraz
- [Australia Post: Lost tracking number](https://auspost.com.au/help/parcel-tracking/tracking-support/lost-tracking-number) – gdzie znaleźć numer, brak wyszukiwania po nazwisku i ponowna próba po 24 godzinach
- [Australia Post: Help with tracking](https://auspost.com.au/help/parcel-tracking/tracking-support/help-with-tracking) – identyfikator article ID, centra sortowania i ostatnie zdarzenia
- [Australia Post: What your status means](https://auspost.com.au/help/parcel-tracking/tracking-support/what-your-status-means) – Pending (24–48 godz. i powyżej 3 miesięcy), próba doręczenia, awizo Sorry We Missed You, It's on its way z zagranicy i SMS z oknem 2 godz.
- [Australia Post: Tracking service](https://auspost.com.au/personal/receiving/tracking-service) – śledzenie bez konta oraz sortownie Redbank, Chullora i Sunshine West
- [Australia Post: Taking longer than expected](https://auspost.com.au/help/parcel-tracking/taking-longer-than-expected) – Pending i Ready for processing powyżej tygodnia oraz 5 dni roboczych
- [Australia Post: Delays and disruptions](https://auspost.com.au/help/parcel-tracking/delays-and-disruptions) – znaczenie statusu Possible delay
- [Australia Post: Return to sender](https://auspost.com.au/help/delivered-parcel/return-to-sender) – Returning to sender, Returned i Contact sender po 10 dniach roboczych
- [Australia Post: Missed parcel deliveries](https://auspost.com.au/personal/receiving/parcel-deliveries/missed-parcel-deliveries) – przekazanie na pocztę lub do automatu, dokumenty, upoważnienie i przeniesienie za 7,55 AU$
- [Australia Post: Requested delivery but parcel at Post Office](https://auspost.com.au/help/delivered-parcel/parcel-at-post-office) – 10 dni roboczych na odbiór, brak ponownego doręczenia z poczty i wniosek o zniesienie opłaty
- [Australia Post: Transfer to another post office](https://helpandsupport.auspost.com.au/s/products-and-services?transfertoPO) – formularz przeniesienia nadal wskazujący 7,20 AU$
- [Australia Post: 24/7 Parcel Lockers](https://auspost.com.au/help/collection-delivery/parcel-lockers) – dostępność 24/7 w większości lokalizacji, 48 godz. bez weekendów i świąt, kod odbioru i przypomnienie 24 godz. wcześniej
- [Australia Post: Redirect parcel or change address](https://auspost.com.au/help/parcel-tracking/redirect-parcel-change-address) – przekierowanie przez MyPost przed wydaniem kurierowi i dodatkowy dzień roboczy
- [Australia Post: Leave in a safe place (Safe Drop)](https://auspost.com.au/help/collection-delivery/safe-drop) – włączenie do 1 godz. przed dostawą oraz doręczenia 7:00–17:00 w dni robocze
- [Australia Post: AusPost app](https://auspost.com.au/personal/receiving/australia-post-app) – powiadomienia cyfrowe, papierowe awiza i okno 2-godzinne
- [Australia Post: No recent update](https://auspost.com.au/help/parcel-tracking/no-recent-update) – przerwy w skanach, 5 dni roboczych, zgłoszenie po 10 dniach roboczych i godziny infolinii
- [Australia Post: Lost or late items](https://auspost.com.au/personal/receiving/parcel-deliveries/lost-late-items) – formularz reklamacyjny i konto MyPost
- [Australia Post: Customs](https://auspost.com.au/help/parcel-tracking/international-parcel/customs) – do 15 dni w australijskim urzędzie celnym, pisma od służb i reklamacja u poczty nadania
- [Australia Post: International tracking](https://auspost.com.au/help/parcel-tracking/international-parcel/tracking) – usługi bez standardowego śledzenia (International Post Registered, International Economy) i śledzenie przychodzące
- [Australia Post: Delivered status but parcel not received](https://auspost.com.au/help/delivered-parcel/parcel-not-received) – weryfikacja na miejscu, zdjęcie doręczenia i zgłoszenie na policję
- [Australia Post: Damaged parcel](https://auspost.com.au/help/delivered-parcel/damaged-parcel) – oględziny uszkodzonej paczki na poczcie
- [Australia Post: Compensation](https://auspost.com.au/personal/receiving/parcel-deliveries/compensation) – odszkodowanie do 100 AU$, uprawnieni, 6 miesięcy na zgłoszenie i kontakt ze sprzedawcą
- [Australia Post: Complaints and feedback](https://auspost.com.au/about-us/corporate-information/complaints-and-feedback) – telefony 13 76 78, +61 3 8340 7239, StarTrack 13 23 45 i pomoc w placówkach
- [Australia Post: Help & support](https://auspost.com.au/help) – formularze online i czat
- [Australia Post: Expected delivery date](https://auspost.com.au/help/parcel-tracking/tracking-support/expected-delivery-date) – przewidywana data lub zakres dni, brak terminu i szacowanie po kodzie pocztowym
- [Australia Post: StarTrack tracking](https://auspost.com.au/help/startrack/tracking) – śledzenie StarTrack w tym samym narzędziu i w aplikacji AusPost
- [Australia Post: Scam alerts](https://auspost.com.au/about-us/about-our-site/online-security-scams-fraud/scam-alerts) – fałszywe wiadomości AusPost i zgłaszanie na scams@auspost.com.au
- [Australia Post: About us](https://auspost.com.au/about-us) – sieć ponad 4100 placówek pocztowych w Australii
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – rozpoznawane numery i linki, bramka i odczyt przez przeglądarkę, prognoza daty i okna dostawy, strefy czasowe oraz prywatność
:::
