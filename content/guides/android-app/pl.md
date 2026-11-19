---
title: Peek na Androida: wersja beta i widżet Przesyłki
description: Peek na Androida jest w wersji beta: dołącz do testów w Google Play albo zainstaluj go z F-Droid lub GitHuba. Bez konta. Dodaj widżet i udostępniaj mu linki.
slug: aplikacja-sledzenie-przesylek-android
picture: Telefon z Androidem z widżetem Przesyłki na ekranie głównym i Pip, paczka z buzią, zerkający znad telefonu.
published: 2026-11-19
updated: 2026-11-19
---

Peek na Androida to wersja beta, niedostępna publicznie w Google Play: pobierzesz go ze strony [peektracker.com/android](https://peektracker.com/android), dołączając do testów w Google Play albo instalując go z własnego repozytorium F-Droid Peeka lub z GitHuba. Wymaga Androida 12 lub nowszego i nie wymaga konta, a do ekranu głównego dodaje widżet „Przesyłki”, do menu udostępniania zaś „Dodaj do Peeka”.

Śledzi przesyłki ponad 3500 przewoźników: wklej numer przesyłki, link od przewoźnika albo cały e-mail o wysyłce, a [Peek](/) rozpozna przewoźnika i pokaże trasę na mapie. Jest darmowy, bez zakupów w aplikacji i bez reklam. Wersję na iPhone’a, iPada i Maca opisuje poradnik [Peek na iPhone’a, iPada i Maca](guide:iphone-app).

## Czy Peek jest w Google Play?

Tylko dla testerów. Peek jest w zamkniętych testach, a jego strona w Google Play nie otwiera się nikomu spoza nich. Google udostępni go wszystkim, gdy co najmniej 12 testerów będzie go mieć przez 14 dni z rzędu, a do testów może dołączyć każdy. Na telefonie z Androidem otwórz [peektracker.com/android](https://peektracker.com/android), stronę „Wypróbuj Peek na Androida”:

:::steps
- „Dołącz do grupy testerów” | Stuknij „Dołącz do grupy”, dołącz do grupy Google na stronie, która się otworzy, a potem wróć.
- „Zostań testerem w Google Play” | Stuknij „Otwórz Google Play”, a potem **Zostań testerem** na stronie, która się otworzy.
- „Zainstaluj i zostaw na dwa tygodnie” | Stuknij „Zainstaluj Peek” i nie usuwaj aplikacji przez 14 dni.
:::

Dopóki nie otworzysz linku do grupy w tej przeglądarce, dwa kolejne przyciski mają napis „Najpierw dołącz do grupy”. Z testów możesz zrezygnować w każdej chwili na tej samej stronie Google Play.

### Jak zainstalować Peek bez Google Play?

Dodaj ręcznie własne repozytorium Peeka w aplikacji F-Droid albo pobierz aplikację z GitHuba. Peeka nie ma w głównym katalogu F-Droid.

:::steps
- Skopiuj adres | Na stronie [peektracker.com/android](https://peektracker.com/android), w części „Bez Google Play”, stuknij „Kopiuj adres”.
- Dodaj repozytorium | W aplikacji F-Droid otwórz **Ustawienia** › **Repozytoria** › **+** i wklej cały adres.
- Zainstaluj Peek | Stuknij nowe repozytorium, żeby zobaczyć jego aplikacje, i zainstaluj „Peek”. F-Droid nie zainstaluje niczego, dopóki sam tego nie wybierzesz.
:::

Możesz też stuknąć „Otwórz GitHub” na tej samej stronie: w najnowszym wydaniu na stronie [wydań Peeka na GitHubie](https://github.com/plhery/peek-delivery-tracker/releases) stuknij plik .apk, a potem pozwól przeglądarce go zainstalować. Wydania Peeka mają tam oznaczenie „Pre-release”.

Tę wersję podpisuje Peek, a nie Google, więc nie może zaktualizować kopii z Google Play ani odwrotnie: jeśli masz Peeka z Google Play albo wczesną kopię z repozytorium lub z GitHuba, najpierw ją odinstaluj. Aktualizacje przychodzą z repozytorium F-Droid Peeka. Żadna z tych dwóch dróg nie czyni Cię testerem w Google Play.

### Czym różni się wersja z Google Play od wersji z F-Droid?

| Co | Wersja z Google Play | Wersja z F-Droid i GitHuba |
| --- | --- | --- |
| Usługi Google | Firebase, ML Kit, Play Integrity | Brak |
| Skaner kodów kreskowych | Tak | Nie, i nie prosi o dostęp do aparatu |
| Powiadomienia | Przez usługi Google Play | Przez aplikację UnifiedPush, taką jak ntfy, albo Peek sprawdza nowości mniej więcej co 15 minut |
| Podpisuje i aktualizuje | Google, przez Google Play | Peek, przez swoje repozytorium F-Droid |

Powiadomienia w każdej z wersji wyjaśnia poradnik [powiadomienia o dostawie przesyłek](guide:parcel-notifications).

## Jakiej wersji Androida wymaga Peek?

Androida 12 lub nowszego, w obu wersjach. Urządzenie bez tylnego aparatu też może go zainstalować, tylko bez skanera.

Peek jest dostępny w dziesięciu językach: angielskim, niemieckim, francuskim, włoskim, hiszpańskim, portugalskim, brazylijskim portugalskim, polskim, rosyjskim i chińskim uproszczonym. Wybierz jeden w „Ustawienia” › „Preferencje” › „Język” (ustawienia otwiera przycisk Konto u góry ekranu Dostawy) albo w androidowym ustawieniu języka dla poszczególnych aplikacji. Jeśli wybierzesz go w ustawieniach Peeka, „Dodaj do Peeka”, nazwa widżetu w selektorze i skrót „Dodaj przesyłkę” zostaną w języku telefonu; napisy w samym widżecie będą w języku Peeka.

## Czy mogę używać Peeka na Androidzie bez konta?

Tak. Wklej numer przesyłki na pierwszym ekranie, a Peek zacznie ją śledzić i pokaże tylko na tym telefonie; widżet i skrót do najbliższej przesyłki też działają. Ekran Dostawy pokazuje „Wszystkie przesyłki w jednym miejscu” z napisem „Na razie tylko na tym urządzeniu.” i przyciskiem „Zaloguj się”.

Przesyłka znika 30 dni po doręczeniu, a „Ustawienia” › „Na tym urządzeniu” › „Zapomnij o wszystkich” usuwa wszystkie naraz. Kopie zapasowe samego telefonu obejmują śledzone na nim przesyłki i preferencje Peeka.

Konto daje tę samą listę na każdym urządzeniu i na peektracker.com, paszport i znajomych, ustawienia powiadomień oraz „Dodawaj przesyłki e-mailem” ([dodawanie przesyłek e-mailem](guide:add-parcels-by-email)). Zaloguj się przez „Kontynuuj z Google” albo „Zaloguj się przez e-mail”; na Androidzie nie ma logowania przez Apple. „Wypróbuj demo” na ekranie logowania też nie wymaga konta. Poradnik [korzystanie z Peeka bez konta](guide:account-or-guest) porównuje obie możliwości.

## Jak dodać widżet Peeka do ekranu głównego?

Dodaj „Przesyłki”, jedyny widżet Peeka, z selektora widżetów, gdzie ma opis „Zobacz od razu przesyłki w drodze, najbliższą na początku.” Kroki Google:

:::steps
- Otwórz widżety | Na ekranie głównym naciśnij i przytrzymaj puste miejsce, a potem stuknij **Widżety**.
- Znajdź Peeka | Stuknij Peeka, żeby zobaczyć jego widżet, „Przesyłki”.
- Umieść go | Naciśnij i przytrzymaj widżet, przeciągnij go w wybrane miejsce i podnieś palec.
:::

Google uprzedza: „niektóre z opisanych tu czynności można wykonać tylko na urządzeniach z Androidem 14 lub nowszym”.

Widżet „Przesyłki” ma na start rozmiar cztery na dwie komórki i można go zmniejszyć do dwóch na dwie. Pokazuje przesyłki w drodze, a potem te doręczone w ciągu ostatniej doby, w kolejności z Peeka. Każda przesyłka otwiera swoją stronę; reszta widżetu otwiera Twoją listę. Widżet odrysowuje się co najmniej co 20 minut, a dni zmieniają się w nim o północy, nawet przy zamkniętej aplikacji.

| Widżet pokazuje | Co to znaczy |
| --- | --- |
| „Widżet wyłączony” | Włącz „Ustawienia” › „Dostawy” › „Widżety ekranu głównego” |
| „Brak przesyłek w drodze” | Żadna przesyłka nie jest w drodze: „Dodaj numer lub link śledzenia, aby zacząć.” |

Widżet „Przesyłki” działa tylko na ekranie głównym: nie ma widżetu na ekran blokady ani niczego w rodzaju Aktywności na żywo.

### Czy Peek ma skróty na ikonie aplikacji?

Tak. Naciśnij i przytrzymaj ikonę Peeka, a zobaczysz skrót „Dodaj przesyłkę”, który otwiera ekran dodawania, oraz skrót nazwany tak jak Twoja najbliższa przesyłka w drodze, który ją otwiera. Żeby przypiąć skrót do ekranu głównego, naciśnij go, przytrzymaj i przeciągnij na miejsce. Na Androidzie Peek nie ma fraz głosowych: te skróty zastępują czynności Siri z iPhone’a.

## Jak udostępnić link do śledzenia Peekowi na Androidzie?

Udostępnij go i wybierz „Dodaj do Peeka”, które przyjmuje każdy udostępniony tekst lub link.

:::steps
- Udostępnij | Udostępnij numer przesyłki, link albo stronę z dowolnej aplikacji i wybierz „Dodaj do Peeka”.
- Zapisz | Na karcie, która się otworzy, stuknij „Zapisz dane śledzenia”. Pojawi się „Zapisano. Otwórz aplikację Peek w ciągu 10 minut, aby sprawdzić i dodać przesyłkę.”
- Otwórz Peeka | W ciągu tych 10 minut otworzy się ekran dodawania z wypełnionymi danymi.
:::

Linki do przesyłek z Peeka (peektracker.com/p/…) otwierają się w aplikacji, gdy jest zainstalowana. Jak wysłać przesyłkę komuś innemu, wyjaśnia poradnik [udostępnianie śledzenia przesyłki](guide:share-parcel).

### Czy Peek zeskanuje kod kreskowy na Androidzie?

Tylko w wersji z Google Play. Stuknij ikonę kodu kreskowego na końcu pola śledzenia (odczytywaną na głos jako „Skanuj kod kreskowy”), na pierwszym ekranie albo na ekranie dodawania; przy pustej liście jest też przycisk „Skanuj kod kreskowy”. „Skanuj kod kreskowy przesyłki” otwiera się z tylnym aparatem i śledzi kod kreskowy najbliżej środka. Jeśli odmówisz dostępu do aparatu, skaner zniknie, dopóki nie zezwolisz na to w ustawieniach Androida. Nie wiesz, który numer jest numerem przesyłki? Zajrzyj do poradnika [gdzie znaleźć numer przesyłki](guide:find-tracking-number).

:::sources
- [Peek: Wypróbuj Peek na Androida](https://peektracker.com/android) – trzy kroki testów, Android 12, repozytorium F-Droid, GitHub, podpis
- [Peek: notatki o Google Play](https://github.com/plhery/peek-delivery-tracker/blob/main/docs/google-play/README.md) – zamknięte testy, 12 testerów przez 14 dni, grupa testerów
- [Peek: aplikacja na Androida](https://github.com/plhery/peek-delivery-tracker/blob/main/android/README.md) – dwie wersje, zawartość i odświeżanie widżetu, skróty, klucze podpisu, wydania wstępne
- [Peek: wpis w F-Droid](https://github.com/plhery/peek-delivery-tracker/blob/main/android/fdroid/com.plhery.peekdeliverytracker.yml) – beta, nazwa, które wcześniejsze kopie odinstalować
- [F-Droid: Jak dodać repozytorium do F-Droid](https://f-droid.org/pl/tutorials/add-repo/) – Ustawienia › Repozytoria › +, a potem wybór aplikacji do zainstalowania
- [Peek: wydania na GitHubie](https://github.com/plhery/peek-delivery-tracker/releases) – pliki .apk z oznaczeniem Pre-release
- [Peek: teksty po polsku](https://github.com/plhery/peek-delivery-tracker/blob/main/shared/locales/pl.json) – każda cytowana tu etykieta Peeka
- [Peek: manifest Androida](https://github.com/plhery/peek-delivery-tracker/blob/main/android/app/src/main/AndroidManifest.xml) – Dodaj do Peeka, linki do przesyłek, aparat, kopie zapasowe
- [Peek: widżet Przesyłki](https://github.com/plhery/peek-delivery-tracker/blob/main/android/app/src/main/res/xml/parcels_widget.xml) – tylko ekran główny, jego rozmiary
- [Peek: Ustawienia](https://github.com/plhery/peek-delivery-tracker/blob/main/android/app/src/main/kotlin/com/plhery/peekdeliverytracker/ui/settings/SettingsSheet.kt) – Widżety ekranu głównego, Zapomnij o wszystkich, Język, brak Aktywności na żywo
- [Peek: skaner w wersji z Google Play](https://github.com/plhery/peek-delivery-tracker/blob/main/android/app/src/play/kotlin/com/plhery/peekdeliverytracker/platform/CameraLabelScanner.kt) – kody kreskowe, dostęp do aparatu
- [Peek: Support](https://peektracker.com/support.html) – bez konta, Forget all
- [Android – Pomoc: Dodawanie aplikacji, skrótów i widżetów do ekranów głównych](https://support.google.com/android/answer/9450271?hl=pl) – dodawanie widżetu (Widżety), przypinanie skrótu, Android 14
:::
