---
title: Śledzenie Aras Kargo: numery i statusy po polsku
description: Śledź paczkę Aras Kargo: jak wygląda 13-cyfrowy numer, co znaczą tureckie statusy jak TESLİMAT ŞUBESİNDE po polsku i jak śledzi ją Peek.
slug: sledzenie-aras-kargo
published: 2026-11-22
updated: 2026-11-22
---

Aras Kargo (zapisywane też jako Aras Cargo) to firma kurierska w Turcji. Jej system śledzenia, w postaci odczytywanej przez Peek, zapisuje każdy etap po turecku. Numer przesyłki otrzymujesz od sklepu lub nadawcy — wklej go w pole powyżej. Własna wyszukiwarka Aras Kargo działa pod adresem kargotakip.araskargo.com.tr. Tabele poniżej tłumaczą tureckie statusy na język polski.

## Numer śledzenia Aras Kargo: jak wygląda

Kod Peek rozpoznaje numery Aras Kargo jako 13 cyfr. Te, które automatycznie łączy z Aras, zaczynają się od 60 lub 65. Przykłady wymyślone:

| Numer (wymyślony) | Co to jest |
| --- | --- |
| `6048291736542` | 13 cyfr zaczynających się od 60: format, który Peek łączy z Aras Kargo. 13-cyfrowych numerów używają też inni przewoźnicy, m.in. [Delhivery](carrier:delhivery), Econt, Ozon Logistics, Paquetexpress, Correos Chile, Yunda i Korea Post. |
| `6548291736542` | 13 cyfr zaczynających się od 65: tak samo. |
| `1482917365482` | 13 cyfr z innym początkiem: Peek nie przypisuje ich automatycznie do Aras, choć Peek odpytuje system Aras o dowolny numer z 13 cyfr. Jeśli sklep wskazuje Aras Kargo, wybierz Aras Kargo ręcznie (zobacz niżej). |
| `604829173654` | Tylko 12 cyfr. Gdy wybierzesz dla niego Aras Kargo, Peek wyświetli, że „nie wydaje takich numerów”. Sprawdź, czy nie brakuje cyfry. |

Spacje nie przeszkadzają przy wklejaniu numeru do Peek. Działa również link do śledzenia Aras zawierający numer (kargotakip.araskargo.com.tr/mainpage.aspx z parametrem `code=` i numerem).

**Gdzie go znaleźć.** W wiadomości o wysyłce ze sklepu lub na stronie zamówienia. Jeśli go nie widzisz, zobacz [jak znaleźć numer przesyłki](guide:find-tracking-number) oraz [formaty numerów przesyłek](guide:tracking-number-formats).

## Statusy śledzenia Aras Kargo po polsku

System Aras pokazuje bieżący status (`Son Durum`), planowany dzień doręczenia oraz listę ruchów pojazdów (`Araç Hareketleri`). Każdy ruch ma datę i godzinę (`İşlem Tarihi`), prowincję (`İl`), jednostkę (`Birim` — oddział lub centrum przeładunkowe) oraz opis (`İşlem Açıklaması`). Gdy to nastąpi, strona podaje też dni bez doręczenia oraz przekierowania do innego oddziału.

**Bieżący status.** Jeden z poniższych u góry strony:

| Aras podaje | Co to znaczy dla Ciebie | Peek pokazuje |
| --- | --- | --- |
| `ÇIKIŞ ŞUBESİNDE` | W oddziale nadawczym, który przyjął przesyłkę. | „Przyjęta przez przewoźnika” |
| `YOLDA` | W drodze między centrami przeładunkowymi Aras. | „W transporcie” |
| `TESLİMAT ŞUBESİNDE` | W oddziale doręczającym, który dostarczy paczkę do Ciebie. Pozostaje taki sam również po nieudanej próbie doręczenia. | „W transporcie” |
| `TESLİM EDİLDİ` | Doręczono. Aras podaje dzień i godzinę (gdy brak godziny, status pozostaje „Dostarczona” bez osobnego zdarzenia doręczenia). | „Dostarczona” |

**Ruchy pojazdów.** Każdy skan przy załadunku, rozładunku lub pakowaniu do worka:

| Aras podaje | Co to znaczy dla Ciebie | Peek pokazuje |
| --- | --- | --- |
| `ŞUBEDE ARAÇ YÜKLEME` | Załadunek na pojazd w oddziale. | „W transporcie” |
| `ŞUBEDE ARAÇ İNDİRME` | Rozładunek z pojazdu w oddziale. | „W transporcie” |
| `AKTARMADA HAT ARACI YÜKLEME` | Załadunek na pojazd dalekobieżny w centrum przeładunkowym. | „W transporcie” |
| `AKTARMADA HAT ARACI İNDİRME` | Rozładunek z pojazdu dalekobieżnego w centrum przeładunkowym. | „W transporcie” |
| `AKTARMADA SUBE ARACI YUKLEME` | Załadunek na pojazd oddziałowy w centrum przeładunkowym. | „W transporcie” |
| `AKTARMADA SUBE ARACI INDIRME` | Rozładunek z pojazdu oddziałowego w centrum przeładunkowym. | „W transporcie” |
| `AKTARMADA TORBA DOLDURMA` | Umieszczenie w worku z innymi paczkami w centrum przeładunkowym. | „W transporcie” |

System zapisuje oba wiersze z `SUBE` bez tureckich znaków diakrytycznych, dokładnie jak powyżej.

**Brak doręczenia** (`Teslim Edilememe Nedeni`, powód). Jeden wiersz na każdy taki dzień:

| Aras podaje | Co to znaczy dla Ciebie | Peek pokazuje |
| --- | --- | --- |
| `TESLİMAT GÜN İÇERİSİNDE YAPILAMADI` | Paczki nie udało się doręczyć tego dnia. | „Próba doręczenia” |
| `TATİL DOLAYISIYLA TESLİMAT YAPILAMADI` | Brak doręczenia z powodu święta. | „Próba doręczenia” |
| `UĞRAMA NOTU BIRAKILDI (1-2)` | Kurier był na miejscu i zostawił awizo. | „Próba doręczenia” |
| `ALICI ADRESİ DAĞITIM ALANI DIŞINDA` | Twój adres leży poza obszarem doręczeń tego oddziału. | „Próba doręczenia” |

**Przekierowanie.** `YÖNLENDİRİLDİ` oznacza wysłanie paczki do innego oddziału. Aras podaje powód, a Peek dopisuje go w nawiasie za tym słowem. Peek pokazuje „W transporcie”.

Oba ostatnie rodzaje wpisów mają tylko datę, bez godziny. System Aras może też wyświetlać wolną notatkę kuriera przy nieudanej próbie — Peek ją pomija. Więcej o etapach znajdziesz w poradniku [co znaczą statusy przesyłek](guide:tracking-statuses).

## Doręczenia Aras Kargo w Turcji

Przesyłka trafia z oddziału nadawczego Aras (`ÇIKIŞ ŞUBESİNDE`), przez centra przeładunkowe (wiersze z `AKTARMADA`), do oddziału doręczającego (`TESLİMAT ŞUBESİNDE`), który dostarcza ją pod Twój adres.

**Planowany dzień doręczenia.** System Aras go podaje. Peek pokazuje go jako przewidywaną datę dostawy, dopóki paczka jest w drodze, a ten dzień nie wypada wcześniej niż ostatni skan. Ukrywa datę po nieudanej próbie doręczenia i po jej upływie.

**Poza obszarem doręczeń.** `ALICI ADRESİ DAĞITIM ALANI DIŞINDA` znaczy, że oddział nie obsługuje Twojego adresu: zapytaj sklep, co dalej.

**Czego ta strona nie podaje.** Peek nie mógł odczytać stron informacyjnych Aras Kargo, więc nie podaje tu czasów doręczenia, liczby prób, czasu przechowywania ani zasad odbioru. System Aras w odczycie Peek nie wskazuje punktu odbioru, wagi ani nazwy usługi. W razie nieobecności zobacz [nieudana próba doręczenia](guide:missed-delivery) i [nieodebrana paczka](guide:uncollected-parcel).

## Śledzenie Aras Kargo stoi w miejscu, paczka zaginęła lub wraca

**Brak wyników.** Gdy Aras nie zna numeru, odpowiada `Girdiğiniz bilgiler için bir sonuç bulunamamıştır.` (brak wyników dla podanych danych). Sklep mógł jeszcze nie przekazać przesyłki. Peek sprawdza dalej: paczka zapisana w Aras Kargo pokazuje „Oczekiwanie na przewoźnika Aras Kargo”. Sprawdź 13 cyfr ze sklepem; jeśli po kilku dniach nic się nie pojawi, zapytaj, czy paczka naprawdę trafiła do Aras.

**Utknęła na `TESLİMAT ŞUBESİNDE`.** Paczka jest w oddziale doręczającym i ten status pozostaje taki sam po nieudanej próbie. Jeśli dla danego dnia widać powód (tabela wyżej), doręczenie się nie odbyło i powód wyjaśnia dlaczego. W przeciwnym razie czeka tam na wydanie. Ponieważ system Aras nie ma etapu wydania do doręczenia, Peek pokazuje „W transporcie” aż do doręczenia albo nieudanej próby.

**Brak aktualizacji przez kilka dni.** Po czterech dniach bez nowego skanu przesyłki w drodze Peek pokazuje na liście dzień ostatniego skanu („Brak aktualizacji od …”), a na karcie paczki „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika Aras Kargo.” z linkiem „Poproś przewoźnika Aras Kargo o pomoc” do strony kontaktu wyłącznie w tej informacji. W weekend paczki często nie mają nowych skanów. Zobacz [dlaczego śledzenie nie aktualizuje się](guide:tracking-not-updating).

**Doręczona, ale nic nie dotarło.** Zapytaj sąsiadów i domowników. Strona Aras podaje w zamaskowanej formie, kto odebrał paczkę (`Teslim Alan`): Peek nie pokazuje tego nazwiska, więc sprawdź je na stronie Aras. Następnie skontaktuj się ze sklepem i z Aras. Więcej w [oznaczona jako doręczona, ale nie dotarła](guide:delivered-not-received).

**Zaginiona lub zwrócona.** Peek nie ma jeszcze zmapowanego osobnego komunikatu zwrotu z Aras. Ponieważ to sklep zlecił wysyłkę w Aras, najpierw poproś sklep o zwrot pieniędzy lub ponowną wysyłkę. Zobacz [zagubiona paczka](guide:lost-parcel) i [zwrot do nadawcy](guide:returned-to-sender).

## Obsługa klienta i kontakt Aras Kargo

Peek nie zdołał odczytać strony internetowej Aras Kargo, więc ta strona nie podaje numeru telefonu ani adresu e-mail — Peek podaje wyłącznie dane kontaktowe odczytane wprost z oficjalnych stron przewoźnika.

Przy zamówieniu ze sklepu zapytaj najpierw sklep, który zamówił przewóz. W Peek lokalizacja każdego ruchu podaje oddział lub centrum przeładunkowe, a lokalizacja doręczenia wskazuje oddział doręczający — podaj tę nazwę w zgłoszeniu.

## Śledzenie paczki Aras Kargo w Peek

[Peek](/) nie rozpozna Aras Kargo na pewno z samej formy numeru, bo 13 cyfr używają też inni przewoźnicy. Mimo to wklej numer: Peek obsługuje ponad 4500+ przewoźników.

- **13 cyfr od 60 lub 65.** Peek pyta jednocześnie Aras Kargo oraz innych przewoźników, których może zapytać o ten numer (Ukrposhta przy `6048291736542`, FedEx Poland przy `6548291736542`). W trakcie sprawdzania widzisz „Sprawdzanie usług śledzenia…”. Jeśli przesyłkę potwierdzi tylko Aras Kargo, Peek wyświetli „Aras Kargo ma tę przesyłkę.” i zapisze ją w Aras; jeśli rozpozna ją kilku przewoźników, Peek zapyta, którego wybrać.
- **Inne 13 cyfr albo brak wyników.** Zmień przewoźnika i wybierz Aras Kargo (wpisz Aras), by Peek odpytał bezpośrednio stronę Aras. W już dodanej paczce również możesz zmienić przewoźnika na jej karcie.
- **Link do śledzenia Aras.** Peek wyciąga numer z adresu i od razu rozpoznaje Aras Kargo („Wykryty przewoźnik”).

Paczka dodana bez przewoźnika pokazuje „Brak historii śledzenia”, podczas gdy Peek dalej odpytuje przewoźników i usługi śledzenia. Jeśli usługa śledzenia wskaże Aras Kargo jako jedynego przewoźnika z datowanymi etapami, Peek przypisze paczkę do Aras.

**Co pokazuje Peek.** Bieżący status, każdy ruch z godziną i miejscem (oddział lub centrum przeładunkowe z prowincją), dni bez doręczenia, przekierowania, planowany dzień doręczenia, czas i oddział doręczenia oraz zamaskowane dane odbiorcy wyświetlane w podsumowaniu, ale nie dane osoby odbierającej (`Teslim Alan`). Pomija niezasłonięte dane ukryte w stronie, nazwę nadawcy, kod odbioru, weryfikację tożsamości, dane płatności i faktury, objętość i liczbę paczek, pasek postępu, wolne notatki i zakładkę SMS. Peek odczytuje godziny Aras w czasie tureckim i przelicza na Twoją strefę czasową (historia kończy się dopiskiem „Godziny w twojej strefie czasowej:” z jej nazwą). Link „Otwórz stronę przewoźnika Aras Kargo” otwiera przesyłkę bezpośrednio w systemie Aras.

Gdy system Aras jest niedostępny, Peek korzysta z usług śledzenia i pokazuje otrzymaną od nich historię w ich brzmieniu.

Gdy masz paczkę otwartą na ekranie — albo w dniu doręczenia z włączonymi powiadomieniami — Peek odpytuje bezpośrednio Aras Kargo nawet co 10 minut (bez 2-minutowego kroku, bo Aras nie raportuje statusu wydania do doręczenia). W pozostałym czasie oraz po dwóch dniach bez nowego skanu (12 godzin przy statusach „Zarejestrowana” lub „Przyjęta przez przewoźnika”) sprawdza co godzinę. W powiadomieniach wybierz „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek nie kontaktuje się z Aras Kargo ani ze sklepem w Twoim imieniu.

## Pytania o śledzenie Aras Kargo

### Co oznacza TESLİMAT ŞUBESİNDE w śledzeniu Aras Kargo?

Oznacza „w oddziale doręczającym”: paczkę ma lokalny oddział Aras odpowiedzialny za dostawę do Ciebie. Status pozostaje taki sam po nieudanej próbie — szukaj wiersza z powodem, który Peek pokazuje jako „Próba doręczenia”.

### Co oznacza YOLDA w Aras Kargo?

Oznacza „w drodze”: paczka jedzie między centrami przeładunkowymi Aras w kierunku oddziału doręczającego. Peek pokazuje „W transporcie”.

### Jak wygląda numer śledzenia Aras Kargo?

W kodzie Peek to 13 cyfr. Numery od 60 lub 65, jak `6048291736542`, Peek kojarzy z Aras Kargo. Przy innych 13 cyfrach wybierz Aras Kargo ręcznie.

### Dlaczego Aras Kargo nie znajduje mojego numeru przesyłki?

`Girdiğiniz bilgiler için bir sonuç bulunamamıştır.` oznacza, że Aras nie ma jeszcze wyników dla tego numeru. Sklep mógł nie nadać jeszcze paczki. Sprawdź 13 cyfr, odczekaj dzień i zapytaj sklep.

### Jak skontaktować się z Aras Kargo?

Najpierw napisz do sklepu, który zamówił przesyłkę. Ponieważ Peek nie mógł odczytać strony Aras Kargo, nie ma tu numeru telefonu ani adresu e-mail.

:::sources
- [Universal Parcel Scraper: Aras Kargo](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/aras-kargo) – nazwy, 13-cyfrowy format i prefiksy 60 oraz 65, strona śledzenia i link, sekcje oraz tureckie komunikaty z etapami, komunikat o braku wyniku, planowany dzień doręczenia, czas turecki, pola odczytywane i pomijane przez Peek
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – inni przewoźnicy o tym samym formacie, automatyczne rozpoznawanie i wsparcie usług śledzenia
:::
