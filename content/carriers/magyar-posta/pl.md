---
title: Śledzenie Magyar Posta: statusy węgierskie i PostaPont
description: Śledź paczkę Magyar Posta (poczta węgierska, MPL): formaty numerów, węgierskie statusy po polsku, PostaPont, automaty, cło i kontakt.
slug: sledzenie-magyar-posta
published: 2026-11-23
updated: 2026-11-23
---

Magyar Posta (Hungarian Post) to narodowy operator pocztowy Węgier, a MPL to nazwa widniejąca na jej paczkach, punktach odbioru PostaPont i automatach paczkowych. Śledź paczkę bezpłatnie na stronie „Nyomkövetés” w serwisie posta.hu, w aplikacji Magyar Posta albo wklej numer w polu powyżej. Numer pochodzi z e-maila o wysyłce ze sklepu albo z e-maila lub SMS-a od Magyar Posta po dotarciu paczki.

## Numer przesyłki Magyar Posta

Formaty wymieniane przez wyszukiwarkę posta.hu (cyfry zmyślone):

| Numer | Co to jest |
| --- | --- |
| `PB48271936404` | Krajowa paczka MPL: `P`, litera i 11 kolejnych znaków, łącznie 13. Z dłuższego kodu kreskowego wpisz pierwsze 13 znaków. |
| `CP482917367HU` | Paczka międzynarodowa (Europe+ lub EMS): dwie litery, 9 cyfr, `HU`. |
| `RR619402838HU` | Międzynarodowy list polecony. Krajowe listy polecone zaczynają się od `RL` i mają 16 znaków. |
| `JJH30KLMNPQ748291635` | MPL Europe Standard: 20 znaków zaczynających się od `JJH`, identyfikator w stylu DHL. |

W e-mailu od Magyar Posta podany jest identyfikator; kliknięcie go otwiera śledzenie w aplikacji.

Numer podany przez zagraniczny sklep często nie jest w ogóle numerem pocztowym: pochodzi z własnego systemu nadawcy i nigdy nie pojawi się na posta.hu. Przesyłki z zagranicy często pojawiają się też dopiero po dotarciu na Węgry, więc komunikat „Invalid item identifier” może oznaczać, że przesyłki jeszcze nie nadano, jeszcze nie dotarła albo wkradła się literówka. Poproś sklep o numer pocztowy i zobacz [formaty numerów przesyłek](guide:tracking-number-formats).

Numer zaczynający się od `P`, litery i 11 cyfr pasuje też do formatu sprawdzanego przez Peek u [La Poste](carrier:la-poste) (dwa znaki, potem 11 cyfr), więc francuska paczka może wyglądać jak węgierska.

## Statusy śledzenia Magyar Posta po polsku

Wyszukiwarka na posta.hu otwiera się po węgiersku; flagi w prawym górnym rogu przełączają ją na angielski lub niemiecki. Peek pokazuje węgierskie komunikaty, podane tu w zwykłej kolejności:

| Status Magyar Posta | Co to oznacza dla ciebie |
| --- | --- |
| `A küldeményt a feladó előrejelezte, az átadást követően megkezdjük a feldolgozást` | Zarejestrowana: nadawca zgłosił paczkę; opracowanie zacznie się po jej przekazaniu. |
| `A küldeményt a feladótól átvettük` | Przyjęta od nadawcy. |
| `A küldemény feldolgozás alatt` | W trakcie opracowywania. |
| `A bejövő küldemény átvétele a kicserélőben` | Paczka z zagranicy dotarła do węgierskiego urzędu wymiany. |
| `Online fizetés elérhető` | Opłaty celne można opłacić online; paczka czeka na płatność. |
| `Vámkezelés vége` | Odprawa celna zakończona. |
| `A küldemény továbbítása kézbesítésre` | Przekazana do twojego urzędu doręczającego. |
| `A küldemény a kézbesítőnél van` | W doręczeniu: paczkę ma kurier. |
| `Sikertelen kézbesítés` | Nieudana próba doręczenia. |
| `A küldemény postán átvehető (értesítőn szereplő dátumtól)` | Do odbioru na poczcie od daty podanej na awizie. |
| `Sikeresen kézbesítve` | Doręczona. |
| `A küldemény nem kézbesíthető (nem kereste)` | Nieodebrana w terminie: wraca do nadawcy. |

`Küldemény előkészítése nemzetközi szállításra` (przygotowanie do transportu międzynarodowego) oznacza paczkę wychodzącą szykowaną do wyjazdu z Węgier. `A vámkezeléshez szükséges információk bekérése (ügyfélre vár)` oznacza, że urząd celny potrzebuje od ciebie informacji i czeka. `A küldemény PostaPonton 14:00 után átvehető` zapowiada, od kiedy PostaPont będzie mógł wydać paczkę: jeszcze jej tam nie ma. `Feladónak visszakézbesítve` oznacza zwrot doręczony nadawcy.

Inne końcówki przy `A küldemény nem kézbesíthető (…)` podają powód, np. `átvételt megtagadta` (odmowa przyjęcia) lub `ismeretlen címzett` (nieznany adresat).

## Doręczenie, PostaPont i automaty paczkowe

**Powiadomienia.** Magyar Posta wysyła e-mail po nadaniu paczki do ciebie, a potem e-mail lub SMS po jej przybyciu: gotowa na poczcie, w PostaPont lub automacie, albo wydana kurierowi wraz z przewidywaną porą. Przypomnienie przychodzi, gdy zostają 2 dni na odbiór.

**Zmiana doręczenia.** Przycisk MÓDOSÍTÁS (zmień) w e-mailu albo „Kiszállítás módosítása” w aplikacji pozwala zmienić termin lub adres; część zmian jest płatna. Po wysłaniu wiadomości przed drugą próbą niczego już nie zmienisz.

**Nieudane doręczenie.** Paczka MPL Business Parcel ma dwie próby doręczenia do domu. Po pierwszej kurier dzwoni, by ustalić drugą, albo możesz poprosić o odbiór osobisty. Jeśli nikogo nie ma, Magyar Posta zostawia awizo (értesítő) lub wysyła SMS albo e-mail i przechowuje paczkę. Do odbioru zabierz dokument ze zdjęciem.

**Czas przechowywania.** Na poczcie lub w PostaPont paczki krajowe czekają 5 lub 10 dni roboczych (wybiera nadawca), a zagraniczne 15.

**Automaty paczkowe.** Automaty MPL Csomagautomata są zwykle czynne całą dobę. Otrzymujesz 6-cyfrowy kod e-mailem i SMS-em: wybierz „CSOMAGOM ÉRKEZETT” na ekranie i wpisz go. Masz 2 dni robocze; potem paczka trafia na pobliską pocztę, gdzie czeka 0, 5 lub 10 dni roboczych zgodnie z wyborem nadawcy.

**PostaPont.** Blisko 3 000 punktów odbioru: placówki pocztowe, automaty MPL, stacje paliw MOL i sklepy COOP. Wyszukiwarka Postakereső na posta.hu i w aplikacji pokazuje najbliższy punkt.

## Paczka z zagranicy, cło i problemy

**Cło.** Magyar Posta odprawia przesyłki spoza UE. Do 150 € dla osób prywatnych odbywa się to zwykle automatycznie: 27% VAT, o ile nie opłacono go przy zakupie przez IOSS, 3 € cła za kategorię towarów oraz opłata własna Magyar Posta. Powyżej 150 € składasz upoważnienie do odprawy. Opłać online w ciągu 48 godzin od SMS-a lub e-maila albo przy odbiorze. Zobacz [cło](guide:customs) i opłaty na posta.hu/vam.

**Brak aktualizacji.** Obsługa klienta może po numerze sprawdzić, czy przesyłka dotarła do Magyar Posta. Zobacz [dlaczego śledzenie przestaje się aktualizować](guide:tracking-not-updating).

**Uszkodzenie.** Widoczne uszkodzenia odnotuj od razu na dokumencie doręczenia (przy automacie zadzwoń pod numer na obudowie). Ukryte uszkodzenia zgłoś w ciągu 3 dni roboczych na poczcie z całym opakowaniem.

**Zaginięcie.** Reklamację składa nadawca, zwykle sklep, chyba że paczka została ci doręczona albo przelano na ciebie prawo do reklamacji. Reklamacja krajowa otwiera się 15. dnia po nadaniu; termin mija po sześciu miesiącach. Magyar Posta odpowiada w 30 dni. Jeśli odpowiedź na skargę cię nie zadowala, możesz w ciągu 30 dni zwrócić się do NMHH (węgierskiego urzędu ds. komunikacji).

## Kontakt z Magyar Posta: telefon i obsługa klienta

Kanały podane na posta.hu:

- **Telefon:** 06-1-767-8282; z zagranicy +36 1 767 8282. Od poniedziałku do środy i w piątek 8:00–17:00, w czwartek 8:00–20:00 czasu węgierskiego. Osobnej infolinii po angielsku nie podano.
- **E-mail:** ugyfelszolgalat@posta.hu.
- **Osobiście:** 1062 Budapest, Teréz körút 51-53; poniedziałek 7:00–19:00, wtorek–piątek 8:00–16:00 czasu węgierskiego.
- **Sprawy celne:** formularze kontaktowe na posta.hu/vam.

> Magyar Posta nigdy nie wysyła SMS-ów z zagranicznych numerów ani e-maili z adresów „.com”. Jej nadawca SMS to 06-30-344-4987, nigdy nie pyta o adres telefonicznie ani e-mailem, a jedyne prośby o płatność dotyczą cła, zawsze przez www.posta.hu/szolgaltatasok/vam.

## Śledzenie paczek Magyar Posta w Peek

[Peek](/) pyta Magyar Posta, czy zna krajowy numer `P…`, pocztowy `…HU`, list polecony `RL…` lub MPL Europe Standard `JJH…`, w przeglądarce na serwerze Peek, więc odpowiedź może potrwać do około 20 sekund; przy `P`, literze i 11 cyfrach pytana jest także La Poste. Możesz też wybrać Magyar Posta przyciskiem „Zmień” albo wkleić link z posta.hu zawierający `searchvalue=`, co od razu wskazuje przewoźnika.

Pierwsze sprawdzenie otwiera posta.hu w przeglądarce na serwerze Peek, aby przejść captcha, nie jest więc natychmiastowe; kolejne sprawdzenia odczytują numer z listy w aplikacji Magyar Posta. Gdy captcha zostanie odrzucone, Peek czeka 10 minut. Peek pokazuje status, historię z każdą lokalizacją oraz punkt odbioru (pocztę, PostaPont lub automat) po węgiersku, w brzmieniu Magyar Posta. Nie pokazuje przewidywanego terminu doręczenia, terminu odbioru ani tego, kto pokwitował przesyłkę. Dopóki przesyłka jest otwarta w Peek albo w dniu doręczenia przy włączonych powiadomieniach, Peek odpytuje Magyar Posta bezpośrednio nawet co 10 minut, a od 6:00 czasu węgierskiego w dniu, gdy Magyar Posta zeskanuje ją w doręczeniu, nawet co 2 minuty — lub co 5 minut, gdy sprawdzenie otwiera posta.hu w przeglądarce — dopóki działa wyszukiwarka Magyar Posta. Po dwóch dniach bez nowych wpisów (po 12 godzinach, gdy przesyłka nadal ma status „Zarejestrowana” lub „Przyjęta przez przewoźnika”) Peek sprawdza ją co godzinę; po siedmiu dniach bez nowych wpisów w statusie „Zarejestrowana” — co 6 godzin. Po czterech dniach bez skanu dla paczki w statusie „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie” Peek wyświetla „Brak aktualizacji od …”, informację „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika Magyar Posta.” oraz link „Poproś przewoźnika Magyar Posta o pomoc”. Powiadomienia mogą dotyczyć opcji „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Przesyłka nadana za granicą zachowuje numer poczty nadania. Gdy ta poczta wskazuje Węgry jako kraj docelowy — albo nie wskazuje kraju, a śledzisz ją z Węgier — Peek pyta także Magyar Posta i przechodzi na nią, gdy tylko Magyar Posta pokaże skan co najmniej tak świeży jak najnowszy skan poczty zagranicznej. Peek nie może skontaktować się w twoim imieniu z Magyar Posta ani ze sprzedawcą.

## Pytania o śledzenie Magyar Posta

### Z jakiego kraju pochodzi Magyar Posta?

Z Węgier. Magyar Posta Zrt. to wyznaczony operator powszechnych usług pocztowych na Węgrzech; jej angielska strona używa nazwy Hungarian Post.

### Czy mogę śledzić Magyar Posta po angielsku lub niemiecku?

Tak. Wyszukiwarka na posta.hu przełącza się na angielski lub niemiecki za pomocą flag w prawym górnym rogu.

### Czym jest MPL?

MPL to nazwa, której Magyar Posta używa dla swoich usług paczkowych i sieci: MPL Business Parcel, MPL PostaPont oraz automatów MPL Csomagautomata.

### Jak śledzić paczkę międzynarodową na Węgry?

Poproś sklep o pocztowy numer przesyłki i wpisz go na posta.hu. Często pojawia się tam dopiero po dotarciu przesyłki na Węgry; własny numer zamówienia ze sklepu nigdy się tam nie pojawi.

:::sources
- [Magyar Posta: Keresés - Magyar Posta Nyomkövetés](https://www.posta.hu/ikrnyomkovproxy/) – formaty numerów, 13 i 16 znaków, języki, 10 numerów, identyfikatory zagranicznych sklepów, dotarcie na Węgry, telefon i e-mail
- [Magyar Posta: Nyomkövetés](https://www.posta.hu/kuldemeny_erkezese/nyomonkovetes) – bezpłatne śledzenie, aplikacja, obsługa klienta
- [Magyar Posta: Belföldi csomagküldemények értesítései](https://www.posta.hu/kuldemeny_erkezese/elektronikus_ugyfelertesites_belfold) – wiadomości, MÓDOSÍTÁS, przypomnienie, granica zmian
- [Magyar Posta: Háznál történő csomagkézbesítés](https://www.posta.hu/kuldemeny_erkezese/haznal_torteno_csomagkezbesites) – dwie próby, telefon, osoby uprawnione, ukryte uszkodzenia
- [Magyar Posta: Postai küldemények átvétele](https://www.posta.hu/kuldemeny_erkezese/hasznos_tudnivalok/postai_kuldemenyek_atvetele) – okresy przechowywania, dokument tożsamości
- [Magyar Posta: MPL Csomagautomata](https://www.posta.hu/csomagautomata) – kod do automatu, 2 dni robocze, potem poczta
- [Magyar Posta: PostaPont](https://www.posta.hu/postapont) – sieć, MOL i COOP, godziny otwarcia
- [Magyar Posta: Postai vámügynöki szolgáltatás](https://www.posta.hu/vam) – odprawa celna, 150 €, VAT, cło, płatność w 48 godzin
- [Magyar Posta: Postai szolgáltatások ÁSZF](https://www.posta.hu/static/internet/download/PASZF_ASZF01_ASZF.pdf) – operator powszechny, awiza, reklamacje, skargi, NMHH, godziny i placówka obsługi
- [Magyar Posta: Magyar Posta app](https://www.posta.hu/postaapp) – śledzenie w aplikacji, zmiana doręczenia, Postakereső
- [Magyar Posta: WARNING! Phishing emails, SMS messages](https://www.posta.hu/news/phishing) – fałszywe wiadomości, oficjalny numer SMS
- [Magyar Posta: Hungarian Post](https://www.posta.hu/international_main) – angielska nazwa
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – co odczytuje Peek, węgierskie treści statusów
:::
