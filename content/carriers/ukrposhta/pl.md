---
title: Śledzenie Ukrposhta: numery, statusy i odbiór w placówce
description: Śledź paczkę Ukrposhta (Poczta Ukraińska): numery 13-cyfrowe i UA, znaczenie angielskich statusów, odbiór w oddziale i przesyłki zagraniczne.
slug: sledzenie-ukrposhta
published: 2026-11-22
updated: 2026-11-22
---

Ukrposhta (Укрпошта) to narodowy operator pocztowy Ukrainy. Paczkę sprawdzisz w jej wyszukiwarce track.ukrposhta.ua, w aplikacji Ukrposhta albo wklejając numer w pole powyżej. Numer podaje nadawca: 13 cyfr w obrocie krajowym na Ukrainie albo dwie litery, dziewięć cyfr i dwie litery w obrocie międzynarodowym.

## Numer śledzenia Ukrposhta

Numery Ukrposhta występują w dwóch formatach, a 13-cyfrowy numer krajowy kończy się cyfrą kontrolną (modulo 11).

| Format (fikcyjny) | Co oznacza |
| --- | --- |
| `0548291736543` | Przesyłka krajowa na Ukrainie: 13 cyfr. |
| `RR482917367UA` | Przesyłka międzynarodowa nadana na Ukrainie: dwie litery, dziewięć cyfr, `UA`. |
| `RR482917367US` | Przesyłka przychodząca na Ukrainę zachowuje numer poczty nadawczej i kod jej kraju. |

Brak numeru? Zapytaj nadawcę albo zobacz [gdzie znaleźć numer śledzenia](guide:find-tracking-number).

[Nie myl z innymi](guide:tracking-number-formats): krajowe listy przewozowe [Nova Poshta](carrier:nova-poshta) na Ukrainie mają 14 cyfr, a wiele numerów [Meest](carrier:meest) kończy się literą `G`.

## Co oznaczają statusy śledzenia Ukrposhta

Peek pobiera od Ukrposhta angielskie opisy. Najczęstsze z nich w przybliżonej kolejności:

| Status Ukrposhta | Co to oznacza dla ciebie |
| --- | --- |
| `Created online. Waiting acceptance` | Nadawca [utworzył etykietę online](guide:tracking-statuses); Ukrposhta nie ma jeszcze przesyłki. |
| `Registered` | Przyjęta przez Ukrposhta. |
| `Departed from the Branch` | Opuściła oddział nadania. |
| `Arrived to the Logistics Center` | Sortowana w drodze przez Ukrainę. |
| `Arrival at inward office of exchange` | Przesyłka z zagranicy dotarła na Ukrainę. |
| `Held at inward office of exchange` | Zatrzymana po przybyciu do odprawy celnej. |
| `Item presented to customs` | Przedstawiona urzędowi celnemu do kontroli. |
| `Released from customs for further delivery` | Zwolniona przez urząd celny. |
| `Departure from inward office of exchange` | Po odprawie celnej, w drodze do twojego regionu. |
| `Item out for physical delivery` | Wydana do doręczenia. |
| `Arrived to the Branch` | W twojej placówce, gotowa do odbioru. |
| `Final delivery` | Doręczona: wydana odbiorcy. |
| `Returned to Sender (Due to Recipient's Refusal)` | Odmowa odbioru przez adresata, wraca do nadawcy. |
| `Return: Delivered to Sender` | Wróciła do nadawcy: zwrócona, a nie doręczona tobie. |

Etapy zwrotu zaczynają się od `Return:`. Gdy pojawi się wpis o zwrocie, późniejsze doręczenie Peek wyświetla jako „Zwrócona nadawcy”, a nie „Dostarczona”.

## Odbiór w placówce i doręczenie Ukrposhta

Wpisy informują, czy przesyłka idzie do ciebie (`Item out for physical delivery`), czy czeka w oddziale (`Arrived to the Branch`, co Peek pokazuje jako „Do odbioru”). Aplikacja Ukrposhta pokazuje oddziały na mapie z kodami pocztowymi, adresami i godzinami otwarcia. Zapytaj w oddziale o okres przechowywania i wymagany dokument tożsamości.

## Paczka Ukrposhta opóźniona, zaginiona lub uszkodzona

Jeśli od kilku dni nic się nie zmienia, zobacz [śledzenie nie aktualizuje się](guide:tracking-not-updating); przy zatrzymaniu na cle zobacz [paczki zatrzymane na cle](guide:customs).

**Zaginięcie lub opóźnienie przesyłki międzynarodowej.** Zgodnie z przepisami Światowego Związku Pocztowego (UPU) każda poczta narodowa musi przyjąć reklamację paczki albo przesyłki poleconej lub ubezpieczonej — od nadawcy lub od odbiorcy — w ciągu sześciu miesięcy od dnia następującego po nadaniu. Takie zgłoszenia są co do zasady bezpłatne, lecz Ukraina zastrzega sobie prawo do pobierania opłaty. Odszkodowanie przysługuje nadawcy lub tobie, jeśli nadawca zrzeknie się go na piśmie. Przy niedoręczonym zakupie online zacznij od kontaktu ze sprzedawcą.

**Uszkodzenie przy doręczeniu.** Zgłoś szkodę przed odebraniem paczki. Na mocy tych samych przepisów UPU poczty odpowiadają za uszkodzenia stwierdzone przed doręczeniem lub przy odbiorze, a poczta doręczająca sporządza protokół do twojego podpisu i wręcza ci kopię.

## Obsługa klienta Ukrposhta

W razie problemów z aplikacją strona Ukrposhta w Google Play podaje adres mobile-app@ukrposhta.ua. W przypadku zaginionej lub opóźnionej przesyłki międzynarodowej złóż reklamację jak wyżej — zgodnie z przepisami UPU Ukrposhta musi przyjąć ją zarówno od ciebie, jak i od nadawcy.

## Śledzenie Ukrposhta w Peek

[Peek](/) śledzi ponad 4500 przewoźników. Dla 13 cyfr z poprawną cyfrą kontrolną Ukrposhta Peek odpytuje Ukrposhta — oraz [FAN Courier](carrier:fan-courier) lub iMile, gdy numer pasuje też do ich wzorca — i pokazuje Ukrposhta z dopiskiem „ma tę przesyłkę”, gdy zna ją tylko ona; jeśli zna ją też inny przewoźnik, wybierasz sam. Numer 13-cyfrowy z błędną cyfrą kontrolną nie jest podpowiadany dla Ukrposhta: wybierz ją przyciskiem „Zmień”. Poprawny kod S10 zaczynający się od `C`, `E`, `L` lub `R` i kończący na `UA` jest rozpoznawany od razu jako Ukrposhta; przy innej poprawnej literze początkowej Peek pyta Ukrposhta i [Chronopost](carrier:chronopost), a jeśli jeszcze żaden nie odpowiada, sprawdza dalej po dodaniu. Link track.ukrposhta.ua z numerem (`barcode=`) zawsze wskazuje Ukrposhta.

Zagraniczny numer pocztowy nie jest z góry kierowany do Ukrposhta. Gdy paczka zmierza na Ukrainę, Peek może przejąć jej śledzenie w Ukrposhta, albo możesz wybrać „Ukrposhta” przyciskiem „Zmień”.

Peek czyta usługę stojącą za aplikacją Ukrposhta; gdy ta zawiedzie albo nie zna przesyłki międzynarodowej, otwiera stronę śledzenia Ukrposhta w przeglądarce po stronie serwera. Pokazuje każdy krok na mapie z miejscowością i krajem, etapy celne oraz godzinę doręczenia. Ukrposhta podaje godziny bez strefy czasowej: wpisy z Ukrainy czytane są według czasu kijowskiego i przeliczane na twoją strefę czasową (na dole historii widnieje „Godziny w twojej strefie czasowej:” z jej nazwą); strefę skanu za granicą lub bez kraju (np. odcinka lotniczego) Peek ustala z jego miejsca lub sąsiednich etapów, więc ta godzina może być przesunięta. Peek nie wyświetla przewidywanej daty przybycia i pomija planowaną datę oraz kody pocztowe; jedynym nazwanym punktem odbioru jest placówka przy `Arrived to the Branch`. Statusy zachowują angielskie komunikaty Ukrposhta, w tym `Final delivery`.

Gdy masz paczkę otwartą na ekranie — albo w dniu doręczenia z włączonymi powiadomieniami — Peek sprawdza nawet co 10 minut, a po wydaniu do doręczenia od 6:00 według zegara ostatniego skanu (na Ukrainie czasu kijowskiego) nawet co 2 minuty (albo co 5, gdy ostatnie sprawdzenie wymagało przeglądarki), o ile usługa lub strona Ukrposhta odpowiada. W pozostałym czasie oraz po dwóch dniach bez nowego skanu (12 godzin przy statusach „Zarejestrowana” lub „Przyjęta przez przewoźnika”) sprawdza co godzinę. W powiadomieniach wybierz „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Po czterech dniach bez skanu, poza odprawą celną lub zgłoszonym problemem, Peek pokazuje na liście dzień ostatniego skanu („Brak aktualizacji od …”), a na karcie paczki „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika Ukrposhta.” z linkiem „Poproś przewoźnika Ukrposhta o pomoc” do centrum kontaktu wyłącznie w tej informacji.

## Pytania o śledzenie Ukrposhta

### Jak śledzić paczkę Ukrposhta w Wielkiej Brytanii?

Gdy dodasz przesyłkę Ukrposhta w Wielkiej Brytanii, Peek pyta pod tym samym numerem również [Royal Mail](carrier:royal-mail) albo Parcelforce dla numeru zaczynającego się od `E` (EMS). Gdy tamta poczta pokaże skan co najmniej tak nowy jak ostatni wpis Ukrposhta, Peek przejmuje śledzenie u niej z zachowaniem wcześniejszych etapów.

### Dlaczego mój numer śledzenia Ukrposhta nie działa?

Porównaj go znak po znaku z numerem od nadawcy. 13-cyfrowe numery kończą się cyfrą kontrolną i Peek nie podpowiada Ukrposhta przy błędnej cyfrze. Jeśli numer jest poprawny, Ukrposhta może go jeszcze nie widzieć: pod Ukrposhta zobaczysz „Oczekiwanie na przewoźnika Ukrposhta”, podczas gdy Peek sprawdza dalej. Jeśli po dwóch dniach nadal go nie zna i nikt inny nie odpowiedział, Peek pokazuje „Po dwóch dniach przewoźnik nadal nie zna tego numeru. Sprawdź go, wybierz innego przewoźnika lub usuń przesyłkę.”, sprawdzając następnie raz dziennie, a po tygodniu raz w tygodniu. Jeśli przez kilka dni nic się nie zmienia, zapytaj nadawcę.

### Jak śledzić paczkę wysłaną na Ukrainę z zagranicy?

Wklej numer od nadawcy. Peek śledzi przesyłkę w poczcie nadawczej; gdy ta wskaże Ukrainę jako cel (lub dodasz ją na Ukrainie), Peek pyta też Ukrposhta i przechodzi do niej po pojawieniu się co najmniej równie świeżego skanu, zachowując wcześniejsze kroki. Aby zacząć od Ukrposhta, wybierz ją przyciskiem „Zmień”. Paczka DHL z Niemiec z numerem na literę `C` jest śledzona w [Meest](carrier:meest).

:::sources
- [Universal Parcel Scraper: Ukrposhta](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ukrposhta) – formaty i cyfry kontrolne, linki track.ukrposhta.ua, usługa aplikacji i rezerwowa przeglądarka, opisy angielskie, zwroty, czas kijowski i skany zagraniczne, pomijane pola
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – przewoźnicy odpytywani po numerze, przekazania do Ukrposhta (oraz Meest dla `C` z DHL) i do Royal Mail lub Parcelforce, formaty Nova Poshta i Meest
- [Światowy Związek Pocztowy (UPU): Convention Manual](https://www.upu.int/UPU/media/upu/files/aboutUpu/acts/06-manualsInThreeVolumes/actInThreeVolumesConventionManual202609En.pdf) – reklamacje do sześciu miesięcy, zastrzeżenie opłat przez Ukrainę, odszkodowanie dla nadawcy (lub pisemne zrzeczenie), protokół uszkodzeń przy doręczeniu
- [Google Play: Ukrposhta](https://play.google.com/store/apps/details?id=ua.ukrposhta.android.app) – śledzenie w aplikacji, mapa oddziałów z kodami, adresami i godzinami otwarcia, e-mail wsparcia aplikacji
:::
