---
title: Śledzenie YWE (YanwenExpress): numer, statusy i pomoc
description: Śledź przesyłkę YWE (YanwenExpress) w USA: jak wygląda 17-znakowy numer YWE, co oznacza każdy skan od sortowni aż pod drzwi i z kim się skontaktować.
slug: sledzenie-ywe
published: 2026-11-18
updated: 2026-11-20
---

YWE (YanwenExpress, prowadzony przez spółkę Yanwen Express LLC) to firma kurierska ostatniej mili działająca w Stanach Zjednoczonych: doręcza paczki ze swoich amerykańskich sortowni pod drzwi odbiorców dla sklepów internetowych, sieci handlowych i platform zakupowych. Jej numery składają się z liter YWE oraz 14 cyfr: wklej swój w polu powyżej albo sprawdź go bezpośrednio w wyszukiwarce YWE na stronie [yanwenexpress.com](https://www.yanwenexpress.com/tracking.html).

## Numer śledzenia YWE: jak wygląda

Numer śledzenia YWE liczy dokładnie 17 znaków: prefiks YWE i 14 cyfr. Przykłady ze zmyślonymi cyframi:

| Numer | Co oznacza |
| --- | --- |
| `YWE48291057364911` | YWE i 14 cyfr: prawidłowy numer przesyłki YWE. |
| `YWE4829105736491` | Tylko 13 cyfr: to nie jest układ numeru YWE. Sprawdź, czy nie brakuje jednej cyfry; jeśli żaden przewoźnik go nie rozpozna, jego strona na peektracker.com pokaże komunikat „Zaczyna się jak numer śledzenia przewoźnika YWE, ale brakuje jednego znaku: może jest w nim literówka. Porównaj go z wiadomością o wysyłce.” |
| `YWLAX482910573649` | YW, trzy litery i 12 cyfr: numer przewoźnika [Yanwen](carrier:yanwen), a nie YWE. |

Spacje ani małe litery nie mają znaczenia podczas wklejania numeru do Peek.

**Gdzie go znaleźć.** W wiadomości e-mail ze sklepu potwierdzającej wysyłkę albo na karcie zamówienia. Jeśli nie możesz go odszukać, zajrzyj do poradnika [jak znaleźć numer śledzenia](guide:find-tracking-number).

**Link do śledzenia.** Link prowadzący do wyszukiwarki YWE zakończony parametrem `?label=` i 14-cyfrowym numerem YWE otwiera tę przesyłkę bezpośrednio pod YWE po wklejeniu całego adresu do Peek. Wyszukiwarka na stronie YWE przyjmuje naraz do 30 numerów (jeśli wpiszesz więcej, zachowuje pierwsze 30).

## Statusy śledzenia YWE i ich znaczenie

Wyszukiwarka YWE wyświetla każdy skan z datą i godziną, przesunięciem względem czasu GMT oraz miastem i stanem w USA, od najnowszego na górze. Komunikaty są po angielsku. W kolejności, w jakiej przesyłka przechodzi przez sieć w Stanach Zjednoczonych:

| Komunikat YWE | Co to oznacza dla ciebie | Peek pokazuje |
| --- | --- | --- |
| `Shipping Order Created.` | Nadawca zgłosił zlecenie doręczenia w YWE, ale fizyczna paczka nie trafiła jeszcze do YWE. | „Zarejestrowana” |
| `Container picked up by YWE,LAX01 Awaiting Item.` | YWE odebrało w Stanach Zjednoczonych kontener lotniczy, w którym przyleciała paczka; pojedyncza przesyłka nie została jeszcze zeskanowana. | „W transporcie” |
| `Container received` | Amerykański hub YWE przyjął kontener na magazyn. | „W transporcie” |
| `Parcel received by in LAX01.` | Pierwszy indywidualny skan twojej paczki w amerykańskiej sortowni YWE. `Parcel received by NJC01.`, bez słowa „in”, oznacza to samo przyjęcie w innym hubie. | „W transporcie” |
| `Parcel departed from Facility.` | Przesyłka opuściła sortownię. | „W transporcie” |
| `Parcel received by transit facility.`, `In processing at transit facility.`, `Parcel departed from Transit Facility.`, `In transit - departure`, `In transit to next facility.` | Przejście przez pośredni punkt tranzytowy w USA przed stacją doręczeń obsługującą dany rejon. | „W transporcie” |
| `Parcel received by local  Facility.` | Przyjęcie w lokalnej stacji doręczeń w USA, z której kurierzy rozwożą paczki w twojej okolicy. | „W transporcie” |
| `Parcel is out for delivery.` | Wydana kurierowi do doręczenia w Stanach Zjednoczonych. | „W doręczeniu” |
| `Parcel has been delivered.(Front door)` | Doręczona w USA. W nawiasie okrągłym podano miejsce pozostawienia paczki. | „Dostarczona” |
| `Parcel delivery abnormal [Restricted Address].Parcel will be returned to the warehouse.` | Próba doręczenia w USA nie powiodła się z powodu podanego w nawiasie kwadratowym; paczka wraca do amerykańskiego magazynu YWE, a nie do nadawcy. | „Próba doręczenia” |
| `Parcel delivery failed [Wrong Address]. Parcel will be returned to the warehouse.` | Taki sam powrót do magazynu YWE w USA opisany jako nieudane doręczenie, skąd przesyłka może ponownie wyjechać na trasę. | „Próba doręczenia” |
| `Return to sender` | Rozpoczął się zwrot do nadawcy, ale przesyłka jeszcze do niego nie dotarła. | „Zgłoszono problem” |
| `Returned to sender, shipment has been received in sender warehouse.` | Zwrócona i przyjęta w magazynie nadawcy. | „Zwrócona nadawcy” |

**Kody sortowni, powody i miejsca doręczenia.** `LAX01` i `NJC01` to oznaczenia amerykańskich hubów YWE: skany z innych obiektów mają w tym miejscu własny kod. Podobnie `[Restricted Address]` lub `[Wrong Address]` to przykładowe przyczyny nieudanego doręczenia, a `(Front door)` to przykład miejsca zostawienia paczki.

**Zakładka Alert nie zawsze oznacza problem.** Strona śledzenia YWE dzieli przesyłki na zakładki `Awaiting`, `Transit`, `Delivered`, `Alert` i `Returned` według ostatniego wpisu. Skan `Parcel received by local  Facility.` (z podwójną spacją przed `Facility.`), czyli przyjęcie w lokalnej stacji doręczeń, trafia na stronie YWE do zakładki `Alert`, mimo że jest zwykłym etapem trasy. Peek oznacza go jako „W transporcie”.

**Inne brzmienia.** Skan spoza powyższej tabeli wyświetla się w Peek dokładnie tak, jak zapisało go YWE, a Peek w miarę możliwości rozpoznaje etap na podstawie użytych słów.

**Godziny.** Każdy wpis zawiera przesunięcie względem czasu GMT danego centrum YWE w USA. Peek odczytuje w ten sposób dokładny moment zdarzenia i przelicza wszystkie godziny na strefę czasową twojego urządzenia; na peektracker.com i w aplikacjach na iPhone’a i Androida oś czasu kończy się informacją „Godziny w twojej strefie czasowej:” wraz z jej nazwą.

## Kto doręcza przesyłki YWE

**Stany Zjednoczone.** W USA YWE samodzielnie obsługuje ostatnią milę poprzez sieć opisaną na stronie jako „Delivery Service Partner networks nationwide”: lokalne zespoły kurierskie w Stanach Zjednoczonych współpracujące z YWE przy doręczeniach i odbiorach. W kodzie Peek historia przesyłki YWE biegnie od amerykańskiego hubu YWE pod drzwi odbiorcy i nie wskazuje żadnego innego przewoźnika.

Na podstronie o firmie YWE podaje, że wystartowało w Los Angeles w 2024 roku i planuje objąć zasięgiem większość obszaru Stanów Zjednoczonych do końca 2026 roku.

**Próby doręczenia i odbiór w USA.** YWE nie publikuje zasad dotyczących liczby prób doręczenia w Stanach Zjednoczonych, awizowania ani punktów odbioru. Po nieudanej próbie w USA skan informuje, że paczka wraca do magazynu YWE (`Parcel will be returned to the warehouse.`). Aby poprosić o ponowne doręczenie lub przekazać wskazówki dojazdu, napisz do YWE e-mailem (szczegóły niżej), podając numer śledzenia, pełny adres w USA oraz instrukcje dla kuriera. Zobacz też [nieudana próba doręczenia](guide:missed-delivery).

**Polska i inne kraje poza Stanami Zjednoczonymi.** YWE nie wymienia żadnych usług poza terytorium Stanów Zjednoczonych. Jeśli sprzedawca podał ci numer YWE dla zamówienia adresowanego do Polski albo innego kraju poza USA, zapytaj sklep, który przewoźnik faktycznie doręcza paczkę na miejscu.

**YWE a Yanwen.** [Yanwen](carrier:yanwen) funkcjonuje w Peek jako osobny przewoźnik z własnymi formatami numerów i osobną wyszukiwarką, a strona YWE nie wyjaśnia, czy obie firmy są ze sobą powiązane. Gdy śledzenie Yanwen wskazuje `YanwenExpress` jako dystrybutora przesyłki w USA i podaje numer YWE, Peek wyświetla „Doręcza: YWE” oraz odpytuje również system YWE. Dopóki w YWE widnieje sama zapowiedź bez własnego skanu, wyświetla się „Zgłoszona przewoźnikowi YWE, jeszcze nieprzyjęta”. Gdy tylko YWE opublikuje skan co najmniej tak świeży jak ostatni wpis Yanwen, Peek przełącza dalsze śledzenie na YWE, zachowując wcześniejsze zdarzenia Yanwen.

## Paczka YWE nie dociera lub śledzenie utknęło

**`NOT_FOUND`.** Wyszukiwarka YWE pokazuje ten komunikat, gdy nie ma żadnych skanów dla wpisanego numeru, i wylicza możliwe powody: przesyłka nie została jeszcze przyjęta lub zeskanowana, numer zawiera błąd albo nadanie odbyło się tak dawno, że dane wygasły. Peek wyświetla „Oczekiwanie na przewoźnika YWE” i sprawdza dalej. Porównaj numer z wiadomością od sprzedawcy: YWE i 14 cyfr. Jeśli po kilku dniach nic się nie pojawi, zapytaj sklep, czy paczka w ogóle wyjechała.

**Zatrzymana na `Shipping Order Created.`** YWE ma wyłącznie dane elektroniczne zlecenia, a nie samą paczkę. Ponieważ pierwszy fizyczny skan YWE wykonuje dopiero w swoim hubie w USA, dopytaj sprzedawcę, gdzie przesyłka znajduje się obecnie i kiedy została wysłana. Jeśli jedzie z Chin do Stanów Zjednoczonych, sprawdź poradnik [śledzenie paczki z Chin](guide:tracking-from-china).

**Zatrzymana na `Container picked up by YWE,LAX01 Awaiting Item.`** YWE odebrało już w USA zbiorczy kontener lotniczy, ale nie zeskanowało jeszcze twojej pojedynczej paczki podczas rozładunku.

**`Parcel delivery abnormal` lub `Parcel delivery failed`.** Doręczenie w USA nie udało się z przyczyny podanej w nawiasie kwadratowym, a przesyłka wraca do magazynu YWE. Wyślij wiadomość e-mail do YWE z numerem śledzenia, pełnym adresem w USA oraz wskazówkami doręczenia i powiadom sprzedawcę.

**`Return to sender`.** Przesyłka jest odsyłana do nadawcy. Gdy pojawi się wpis `Returned to sender, shipment has been received in sender warehouse.`, magazyn nadawcy odebrał zwrot — poproś wtedy sklep o zwrot pieniędzy lub ponowną wysyłkę. Więcej w artykule [zwrot do nadawcy](guide:returned-to-sender).

**Oznaczona jako doręczona w USA, ale paczki brak.** Nawias okrągły przy skanie doręczenia, np. `(Front door)`, informuje, gdzie zostawiono przesyłkę. Przy paczkach doręczonych w USA strona YWE udostępnia link `view receipt`: wpisz amerykański kod ZIP odbiorcy w polu `Recipient's postal code`, aby obejrzeć zdjęcia z doręczenia, o ile zostały dodane. Następnie napisz do YWE i zgłoś sprawę sprzedawcy. Zobacz [doręczona, ale nieodebrana](guide:delivered-not-received).

**Zagubiona przesyłka i reklamacje.** Zgodnie z polityką prywatności portalu klienta YWE rejestracja kont jest dostępna wyłącznie dla klientów firmowych (`corporate/business clients`), więc zleceniodawcą YWE jest sklep lub platforma zakupowa zamawiająca doręczenie w USA, a nie odbiorca. Strona YWE nie udostępnia formularza reklamacyjnego dla odbiorców indywidualnych. O zwrot środków albo ponowną wysyłkę zwróć się do sprzedawcy lub platformy, na której kupiłeś towar. Zobacz [zagubiona paczka](guide:lost-parcel) oraz [dlaczego śledzenie się nie aktualizuje](guide:tracking-not-updating).

## Kontakt z YWE i obsługa klienta

Anglojęzyczna strona YWE podaje następujące dane:

- **E-mail**: adres widnieje w stopce każdej podstrony `yanwenexpress.com`. Po kliknięciu otwiera się okienko z listą informacji, które należy podać: pytanie, numer śledzenia paczki, a w treści wiadomości pełny adres odbiorcy, ewentualne wskazówki dla kuriera, dane kontaktowe odbiorcy (numer telefonu i adres e-mail) oraz czego dokładnie dotyczy zgłoszenie.
- **Telefon**: brak opublikowanego numeru. Strona `Customer support` zawiera zdanie „Calling us for further enquiries”, ale nie podaje ani numeru telefonu, ani godzin pracy.
- **FAQ**: na stronie `Customer support` znajdują się pytania i odpowiedzi skierowane do partnerów kurierskich (`Delivery Service Partner`), a nie do odbiorców czekających na paczkę.

W sprawach zamówienia i zwrotu pieniędzy pisz do sprzedawcy; w sprawie samego doręczenia na terenie USA skontaktuj się z YWE przez adres e-mail ze stopki strony.

## Śledzenie przesyłki YWE w Peek

[Peek](/) od razu rozpoznaje numer YWE po układzie znaków — YWE i 14 cyfr — którego nie używa żaden inny przewoźnik w katalogu, i odpytuje bezpośrednio wyszukiwarkę YWE. Zadziała również wklejenie linku `yanwenexpress.com/tracking.html?label=` z numerem YWE. Nie trzeba zakładać konta ani podawać kodu pocztowego czy numeru telefonu.

Peek wyświetla bieżący status, każdy skan z miastem, stanem i krajem oraz dokładny czas doręczenia. Powtarzające się wpisy są pomijane, podobnie jak imię lub nazwisko osoby kwitującej odbiór, jeśli pojawia się w skanie doręczenia.

Własna strona YWE potrafi też pokazywać dwugodzinne okno doręczenia w trakcie transportu w USA, mapę z pozycją kierowcy oraz zdjęcia z doręczenia chronione kodem ZIP. Peek pomija te elementy oraz dokładne linie ulicy przy skanach i nie wyświetla przewidywanej daty doręczenia.

Ponieważ wyszukiwarka YWE nie wymienia innego przewoźnika, przy przesyłce dodanej bezpośrednio numerem YWE Peek nie pokazuje wiersza „Doręcza:” i pozostaje przy systemie YWE aż do doręczenia. Jeśli serwer YWE chwilowo nie odpowiada, w międzyczasie Peek sprawdza usługi śledzenia.

Dopóki masz otwartą kartę przesyłki lub w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza aktualizacje nawet co 10 minut — a od godziny 6:00 czasu lokalnego w dniu wydania paczki do doręczenia nawet co 2 minuty — o ile odpowiada własna wyszukiwarka YWE (przez same usługi śledzenia: co godzinę, co 3 godziny po dobie bez nowego skanu i nawet co 15 minut w dniu doręczenia przy otwartej przesyłce lub aktywnych powiadomieniach); w pozostałym czasie oraz po dwóch dniach bez zmian (po 12 godzinach, gdy paczka wciąż ma status „Zarejestrowana” lub „Przyjęta przez przewoźnika”) sprawdza co godzinę. Powiadomienia możesz ustawić na „Każdy skan”, „Ważne etapy” albo „Tylko doręczenie”. Peek nie kontaktuje się w twoim imieniu ani z YWE, ani ze sprzedawcą.

## Najczęstsze pytania o śledzenie YWE

### Czym jest YWE (YanwenExpress)?

To amerykańska firma kurierska ostatniej mili prowadzona przez Yanwen Express LLC. Doręcza paczki dla sklepów internetowych, detalistów i platform zakupowych przez sieć lokalnych zespołów partnerskich w USA; podaje, że rozpoczęła działalność w Los Angeles w 2024 roku.

### Czy YWE to to samo co Yanwen?

Peek traktuje je jako dwóch odrębnych przewoźników: Yanwen ma własne numery i osobną wyszukiwarkę, a witryna YWE nie informuje, czy spółki są powiązane. Kiedy śledzenie Yanwen wskazuje `YanwenExpress` jako dystrybutora przesyłki w USA wraz z numerem YWE, Peek śledzi dalej ten numer również bezpośrednio w YWE.

### Gdzie mogę śledzić paczkę YWE?

W polu wyszukiwania na górze tej strony lub na oficjalnej stronie YWE pod adresem `yanwenexpress.com`, która przyjmuje jednorazowo do 30 numerów. Wpisz pełny kod: litery YWE i wszystkie 14 cyfr.

### Co oznacza „Container picked up by YWE”?

Oznacza to, że YWE odebrało w Stanach Zjednoczonych kontener lotniczy, w którym przyleciała twoja przesyłka. Kod po przecinku, na przykład `LAX01`, wskazuje amerykańską sortownię YWE. Pierwszy skan samej paczki (`Parcel received by in LAX01.`) pojawia się później, po rozformowaniu kontenera. Dla obu zdarzeń Peek pokazuje „W transporcie”.

### Z kim się skontaktować w sprawie zaginionej paczki YWE?

Najpierw ze sprzedawcą lub platformą zakupową, bo tylko klienci biznesowi mogą zlecać wysyłki w YWE. W sprawach dotyczących samego doręczenia w USA możesz napisać do YWE na adres e-mail podany w stopce strony wraz z numerem śledzenia; firma nie podaje numeru telefonu.

:::sources
- [YanwenExpress: strona główna](https://www.yanwenexpress.com/) – doręczenia ostatniej mili w USA przez sieci Delivery Service Partner, do 30 numerów naraz, adres e-mail w stopce i wymagane informacje
- [YanwenExpress: About us](https://www.yanwenexpress.com/about.html) – start w Los Angeles w 2024 r. i plan objęcia większości USA do końca 2026 r.
- [YanwenExpress: Tracking](https://www.yanwenexpress.com/tracking.html) – skany ze strefą GMT, miastem i stanem, zakładki Awaiting, Transit, Delivered, Alert i Returned, komunikat NOT_FOUND i możliwe przyczyny, okno doręczenia, mapa kierowcy, „view receipt” z kodem pocztowym odbiorcy oraz limit 30 numerów
- [YanwenExpress: Customer support](https://www.yanwenexpress.com/contactus.html) – wzmianka o kontakcie telefonicznym bez podanego numeru i godzin oraz FAQ dla Delivery Service Partnerów
- [YanwenExpress: Join us](https://www.yanwenexpress.com/joinus.html) – dołączanie lokalnych zespołów kurierskich w USA do sieci doręczeń i odbiorów
- [YanwenExpress: Privacy Policy](https://customer-portal.yanwenexpress.com/agreement/Yanwen_privacy_policy) – Yanwen Express LLC i rejestracja kont wyłącznie dla klientów biznesowych
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – nazwy, format YWE z 14 cyframi, wyszukiwarka i link `?label=`, treści skanów i mapowanie etapów, kody hubów, kategoria Alert, przesunięcia GMT, pola zachowywane i pomijane przez Peek oraz przekazanie z Yanwen
:::
