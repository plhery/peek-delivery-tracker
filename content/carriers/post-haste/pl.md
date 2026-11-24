---
title: Śledzenie Post Haste: kody NZ, statusy i pomoc
description: Śledź przesyłkę Post Haste Couriers w Nowej Zelandii: formaty biletów i etykiet, statusy Freightways, ponowne doręczenie i oddziały.
slug: sledzenie-post-haste
published: 2026-11-24
updated: 2026-11-24
---

**Post Haste** (*Post Haste Couriers*) to nowozelandzka sieć kurierska, która w 1977 roku dołączyła do **grupy Freightways** i współpracuje z Castle Parcels ([posthaste.co.nz](https://posthaste.co.nz/)). Obsługując dostawy firmowe i prywatne na Wyspie Północnej oraz Wyspie Południowej przez ponad 20 oddziałów regionalnych i partnerów Delivery Link, Post Haste realizuje przesyłki lokalne, regionalne na następny dzień, międzywyspowe na następny dzień oraz dwudniowe ekonomiczne, a także doręczenia w strefach wiejskich. Paczkę sprawdzisz na [posthaste.co.nz/tools/track/](https://posthaste.co.nz/tools/track/) albo wklejając link śledzenia lub numer przesyłki w pole powyżej.

## Numery przesyłek Post Haste (bilety, etykiety i linki)

Post Haste korzysta z kilku formatów etykiet Freightways, zależnie od tego, czy nadawca użył przedpłaconego biletu kurierskiego / koperty, 8-cyfrowej etykiety rozliczeniowej, 20-cyfrowego kodu kreskowego SSCC czy etykiety drukowanej przez klienta (np. Despatch IT):

| Format (wymyślony) | Co oznacza |
| --- | --- |
| `PH48291736` | Bilet kurierski lub koperta Express Pack: 2–4-literowy kod usługi (np. `PE` lub `PH`) + 5–8-cyfrowy numer biletu (wpisuj bez spacji). |
| `73918462` | 8-cyfrowy numer etykiety rozliczeniowej Post Haste (*charge label*). |
| `KLM4829173` | Numer listu drukowany przez klienta (litery + 6–8 cyfr); część takich etykiet wymaga także numeru konta nadawcy zapisanego w linku z e-maila o wysyłce (`receiver.posthaste.co.nz/?consignmentid=CU..…`). |

Jeśli sprzedawca wysłał Ci e-mailem lub SMS-em link `receiver.posthaste.co.nz` albo `posthaste.co.nz`, **wklej ten pełny link bezpośrednio w Peek** — automatycznie przekaże zarówno numer przesyłki, jak i wymagany kod konta nadawcy. Zobacz poradnik o [formatach numerów śledzenia](guide:tracking-number-formats).

## Co oznaczają statusy śledzenia Post Haste

Post Haste działa na wspólnym systemie śledzenia Freightways (używanym przez Post Haste, Castle Parcels, New Zealand Couriers i Now Couriers) i podaje typy oraz podtypy skanów wraz z dzielnicą lub regionem:

| Kod lub skan Post Haste | Co oznacza dla Ciebie | Peek pokazuje |
| --- | --- | --- |
| `Label created` | Elektroniczna etykieta utworzona w systemie nadawcy przed odbiorem przez kuriera. | „Zarejestrowana” |
| `P` (`Picked Up`) | Odebrana od nadawcy przez kuriera Post Haste. | „Przyjęta przez przewoźnika” |
| `STAT/RZDEL` | Informacja o strefie wiejskiej lub pozamiejskiej; przekazanie partnerowi wiejskiemu wydłuża drogę zwykle o 2–3 dni robocze. | „W transporcie” |
| `STAT/REDEL` | Ponownie zaplanowana i wysłana po wcześniejszej nieudanej próbie doręczenia. | „W transporcie” |
| `D` (`Delivery Handed Over` / `Delivered by courier or branch` do partnera wiejskiego) | Przekazana podwykonawcy doręczeń wiejskich; nadal w drodze do Ciebie, jeszcze nie pod drzwiami. | „W transporcie” |
| `ONBD` | Załadowana do samochodu lokalnego kuriera do doręczenia dzisiaj. | „W doręczeniu” |
| `NOND` / `NOND/*` | Zdarzenie niedoręczenia (np. nikogo w domu i zostawione awizo, zamknięta firma lub brak dostępu). | „Próba doręczenia” |
| `D`, `3P/D` | Doręczona pod adres odbiorcy (w tym `3P/D` zrealizowane przez partnera wiejskiego). | „Dostarczona” |

`STAT/HLDFR8` oznacza, że przesyłka czeka w magazynie frachtowym oddziału po nieudanej dostawie. Nie przesądza, czy czeka na odbiór własny, czy na ponowną próbę: umów ponowne doręczenie albo skontaktuj się z oddziałem.

## Czasy doręczeń, strefy wiejskie i ponowne doręczenie w Nowej Zelandii

Według [Post Haste Sending & Receiving](https://posthaste.co.nz/services/sending-and-receiving/), [Redelivery](https://posthaste.co.nz/tools/redelivery/) oraz [Receiving Parcels Help](https://help.posthaste.co.nz/category/receiving-parcels/):

- **Deklarowane czasy dostaw:** **Lokalna** dla przesyłek firmowych w obrębie miasta; **Regionalna na następny dzień** (zwykle do pory lunchu w kolejny dzień roboczy); **Krajowa na następny dzień** w obrębie oraz pomiędzy Wyspą Północną i Południową (zwykle do południa następnego dnia roboczego pod adresy firmowe, do końca dnia pod adresy prywatne); **Dwudniowa ekonomiczna między wyspami**; dodatkowo **doręczenie w soboty** w dużych aglomeracjach.
- **Adresy pozamiejskie i wiejskie:** Post Haste dociera pod większość wiejskich adresów w Nowej Zelandii za pośrednictwem partnerów wiejskich (`STAT/RZDEL`), co dodaje dwa do trzech dni roboczych do czasów miejskich.
- **Nieobecność i ponowne doręczenie:** standardowa zasada Post Haste to **niezostawianie** paczek pod pustym adresem, chyba że wcześniej udzielono zgody (*authority to leave*). Jeśli przegapisz doręczenie, zamów ponowną dostawę na [posthaste.co.nz/tools/redelivery/](https://posthaste.co.nz/tools/redelivery/) z 16-cyfrowym numerem referencyjnym albo skontaktuj się z lokalnym oddziałem, jeśli go nie masz. Przy odbiorze w oddziale potrzebny jest dokument tożsamości.

## Paczka Post Haste opóźniona, wstrzymana lub zaginiona

- **Przekazanie na trasę wiejską wygląda jak „dostarczona” na części stron:** jeśli skan brzmi `Delivered by courier or branch` lub `Delivery Handed Over`, przesyłka trafiła zwykle do podwykonawcy wiejskiego (`3P`), a nie pod Twoją bramę — odczekaj 2–3 dni robocze na kurs wiejski (`3P/D`). Peek utrzymuje takie przekazania na etapie „W transporcie”, zamiast przedwcześnie oznaczać je jako dostarczone.
- **Brak skanów po etykiecie lub postój w oddziale:** jeśli numer z etykiety drukowanej przez klienta nie znajduje się samodzielnie, wklej pełny link `receiver.posthaste.co.nz` z e-maila wysyłkowego, aby Peek przekazał kod konta nadawcy. Gdy paczka utknie w oddziale, skontaktuj się z lokalnym oddziałem Post Haste lub sprzedawcą.
- **Reklamacje zaginięcia lub uszkodzenia:** sprawdź zasady zgłaszania strat i uszkodzeń na [help.posthaste.co.nz/category/receiving-parcels/](https://help.posthaste.co.nz/category/receiving-parcels/) i najpierw skontaktuj się ze sklepem, który zamówił kuriera. Zobacz też [dostarczona, ale nieodebrana](guide:delivered-not-received), [zaginiona paczka](guide:lost-parcel), [uszkodzona paczka](guide:damaged-parcel), [zwrócona do nadawcy](guide:returned-to-sender) oraz [brak aktualizacji śledzenia](guide:tracking-not-updating).

## Obsługa klienta i oddziały Post Haste w Nowej Zelandii

Oficjalne dane kontaktowe i spisy oddziałów na [posthaste.co.nz/about-us/contact/](https://posthaste.co.nz/about-us/contact/) oraz [help.posthaste.co.nz/category/receiving-parcels/](https://help.posthaste.co.nz/category/receiving-parcels/):

- **Spis lokalnych oddziałów (Wyspa Północna i Południowa):** [posthaste.co.nz/about-us/contact/](https://posthaste.co.nz/about-us/contact/) zawiera numery telefonów i adresy oddziałów na Wyspie Północnej (Whangārei, North Harbour, Auckland, Hamilton, Tauranga, Rotorua, New Plymouth, Taupō, Gisborne, Napier, Hastings, Whanganui, Palmerston North, Wellington) oraz Wyspie Południowej (Nelson, Blenheim, West Coast, Christchurch, Timaru, Dunedin, Queenstown, Invercargill), w tym wspólne punkty z Castle Parcels.
- **Formularz ponownego doręczenia online:** [posthaste.co.nz/tools/redelivery/](https://posthaste.co.nz/tools/redelivery/).
- **Centrum pomocy odbiorcy i zasady:** [help.posthaste.co.nz/category/receiving-parcels/](https://help.posthaste.co.nz/category/receiving-parcels/).

> Uważaj na fałszywe SMS-y żądające drobnej opłaty kartą za zmianę terminu doręczenia nowozelandzkiej przesyłki. Korzystaj wyłącznie ze strony [posthaste.co.nz/tools/redelivery/](https://posthaste.co.nz/tools/redelivery/) albo zweryfikuj sprawę bezpośrednio w swoim oddziale Post Haste. Zobacz poradnik o [fałszywych SMS-ach o paczkach](guide:delivery-scam-texts).

## Śledzenie Post Haste w Peek

Najprościej dodać przesyłkę Post Haste w [Peek](/), wklejając pełny link śledzenia z e-maila wysyłkowego (`receiver.posthaste.co.nz/?consignmentid=CU..…` lub `posthaste.co.nz/track?query=`) — Peek od razu rozpoznaje go jako Post Haste i odczytuje konto nadawcy, gdy jest potrzebne. Jeśli wpiszesz sam goły numer biletu, etykiety rozliczeniowej lub przesyłki (`PH48291736`, `73918462` albo `KLM4829173`, wymyślone), Peek nie rozpoznaje Post Haste po samym numerze (pyta TNT Włochy o bilet w stylu `PH48291736` i Australia Post o przesyłkę jak `KLM4829173`), więc kliknij „Zmień” przy dodawaniu albo „Zmień przewoźnika” na ekranie paczki i wybierz **Post Haste** (wyszukanie „Post Haste Couriers” lub „Posthaste” też działa). Peek udostępnia wtedy opcjonalne pole „Pełny link śledzenia” na link z e-maila wysyłkowego, zawierający konto nadawcy wymagane przy części przesyłek.

Peek czyta zapytanie Freightways pod nazwą Post Haste i pokazuje etap („Zarejestrowana”, „Przyjęta przez przewoźnika”, „W transporcie”, „W doręczeniu”, „Próba doręczenia”, „Dostarczona”), treść skanu, dzielnicę i region, szacowany dzień doręczenia (jeśli Post Haste go podaje) oraz godzinę doręczenia. Typy i podtypy skanów służą do wyznaczenia etapu i nie są wyświetlane w aplikacjach Peek. Godziny skanów przychodzą jako znaczniki UTC i wyświetlają się w czasie Twojego urządzenia. Gdy najnowszy skan pochodzi z innej sieci Freightways, np. Castle Parcels, na ekranie paczki widać „Doręcza:” i nazwę tej sieci. Peek nie zapisuje podpisu ani nazwiska podpisującego, zdjęć doręczenia, zgody na pozostawienie bez podpisu, kodów pocztowych ani danych kierowcy.

Gdy masz przesyłkę otwartą w Peek albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza status nawet co 10 minut; w dniu, gdy Post Haste pokazuje `ONBD`, co 2 minuty od godz. 6:00 czasu nowozelandzkiego, dopóki tracker Post Haste odpowiada. W pozostałym czasie Peek sprawdza co godzinę. Po 12 godzinach wyłącznie na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika” albo po dwóch dniach bez zmian sprawdza co godzinę niezależnie od sposobu śledzenia, a po siedmiu dniach na „Zarejestrowana” — co 6 godzin. Gdy tracker Post Haste nie odpowiada i Peek śledzi przesyłkę wyłącznie przez usługi śledzące, sprawdza co godzinę, co 3 godziny po spokojnym dniu i nawet co 15 minut w dniu, w którym przesyłka jest w doręczeniu, dopóki masz ją otwartą lub włączone powiadomienia.

Jeśli najnowszy skan ma cztery dni, a przesyłka nadal widnieje jako „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie”, Peek oznacza ją dniem tego skanu, np. „Brak aktualizacji od pon 12 paź”. Na stronie przesyłki pojawia się wtedy komunikat „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika Post Haste.” wraz z linkiem „Poproś przewoźnika Post Haste o pomoc” do pomocy dla odbiorców Post Haste. Po trzech tygodniach bez nowego skanu pojawia się informacja „Od ponad trzech tygodni nic nowego. Jeśli przesyłka wciąż nie dotarła, zapytaj sprzedawcę. Peek dalej pyta przewoźnika Post Haste.”

Możesz wybrać powiadomienia „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek monitoruje wyłącznie aktualizacje śledzenia i nie może zamówić ponownego doręczenia, udzielić zgody na pozostawienie paczki ani skontaktować się z lokalnym oddziałem Post Haste w Twoim imieniu.

## Pytania o śledzenie Post Haste

### Jakie numery śledzenia działają w Post Haste?

Post Haste śledzi bilety kurierskie i koperty Express Pack (2–4 litery + 5–8 cyfr, np. `PH48291736`, wymyślony), 8-cyfrowe etykiety rozliczeniowe (`73918462`, wymyślony), 20-cyfrowe kody SSCC i numery listów drukowane przez klienta (`KLM4829173`, wymyślony) — albo wklej pełny link `receiver.posthaste.co.nz` z e-maila o wysyłce.

### Dlaczego mój numer przesyłki wydrukowany przez nadawcę nie pojawia się w wynikach?

Część etykiet drukowanych przez klientów (np. przesyłki Despatch IT) jest powiązana z numerem konta nadawcy. Wklej pełny link `receiver.posthaste.co.nz` z e-maila powiadamiającego o wysyłce, aby zapytanie obejmowało kod konta.

### Co zrobić, jeśli przegapiłem doręczenie Post Haste?

Post Haste zwykle wymaga obecności odbiorcy, chyba że wcześniej udzielono zgody na pozostawienie paczki. Skorzystaj z formularza na [posthaste.co.nz/tools/redelivery/](https://posthaste.co.nz/tools/redelivery/) z 16-cyfrowym numerem referencyjnym albo zadzwoń do swojego oddziału z listy na [posthaste.co.nz/about-us/contact/](https://posthaste.co.nz/about-us/contact/).

### Dlaczego śledzenie Post Haste pokazuje `Delivery Handed Over` lub `STAT/RZDEL`?

`STAT/RZDEL` oznacza adres w strefie wiejskiej lub pozamiejskiej, a skan przekazania informuje, że paczka trafiła do podwykonawcy wiejskiego — zwykle dodaje to 2–3 dni robocze przed końcowym doręczeniem pod drzwi (`3P/D`).

:::sources
- [Post Haste: Official Website](https://posthaste.co.nz/) – nowozelandzka sieć kurierska (Freightways Group)
- [Post Haste: Our Story](https://posthaste.co.nz/about-us/our-story/) – przejęcie przez Freightways (1977) i partnerstwo z Castle Parcels
- [Post Haste: Sending & Receiving Services](https://posthaste.co.nz/services/sending-and-receiving/) – doręczenia lokalne, regionalne następnego dnia, krajowe następnego dnia, dwudniowe ekonomiczne między wyspami, sobotnie i wiejskie
- [Post Haste: Advanced Track & Trace](https://posthaste.co.nz/tools/track/) – etykiety drukowane przez klientów (z kontem firmowym), bilety/Express Pack, kody kreskowe i worki kontraktowe
- [Post Haste: Redelivery](https://posthaste.co.nz/tools/redelivery/) – wnioski online o ponowne doręczenie i wymóg dokumentu przy odbiorze w oddziale
- [Post Haste: Contact & Branches](https://posthaste.co.nz/about-us/contact/) – spis oddziałów na Wyspie Północnej i Południowej
- [Post Haste: Receiving Parcels Help](https://help.posthaste.co.nz/category/receiving-parcels/) – zasady przy nieobecności, powiadomienia oraz wytyczne dotyczące zaginięcia/uszkodzenia (strona pomocy linkowana w Peek)
- [Universal Parcel Scraper: post-haste](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/post-haste) – zapytania Freightways dla biletów/etykiet/SSCC/listów, reguły linków `receiver.posthaste.co.nz`, mapa kodów (`P`, `ONBD`, `NOND`, `STAT/RZDEL`, `D`, `3P/D`) i odczyt ETA
:::
