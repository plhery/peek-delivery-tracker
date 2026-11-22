---
title: Śledzenie TForce Freight: numery PRO, statusy i szkody
description: Śledź ładunek TForce Freight w USA lub Kanadzie: numer PRO, znaczenie komunikatów, dostawy pod adres prywatny i reklamacje frachtu.
slug: sledzenie-tforce-freight
published: 2026-11-22
updated: 2026-11-22
---

TForce Freight to przewoźnik drobnicy ciężkiej (LTL): przewozi palety i ciężkie ładunki między centrami serwisowymi w całych Stanach Zjednoczonych i Kanadzie oraz do ponad 600 punktów w Meksyku. Jeśli duże zamówienie lub inny ładunek jedzie do ciebie przez TForce, śledź je po numerze PRO otrzymanym od sklepu lub firmy zlecającej transport — wklej go powyżej. Własna wyszukiwarka TForce znajduje się na stronie Tracking pod adresem www.tforcefreight.com.

## Numer śledzenia TForce Freight: numer PRO

Strona śledzenia TForce wyszukuje ładunek po numerze PRO albo po numerze listu przewozowego (BOL) lub zamówienia (PO) wraz z zakresem dat wysyłki. Peek śledzi po numerze PRO (cyfry wymyślone):

| Numer (wymyślony) | Co to jest |
| --- | --- |
| `482917363` | Numer PRO: dziewięć cyfr. Ostatnia to cyfra kontrolna — reszta z dzielenia pierwszych ośmiu cyfr przez 7, więc zawsze od 0 do 6. |
| `48291736` | Numer PRO bez zera na początku. Strona TForce dopisuje 0, a Peek robi to samo po wybraniu TForce Freight. |
| `2205482917363` | Numer PARS stosowany przez agentów celnych dla ładunków do Kanady: 2205 i numer PRO. Wklej całość — Peek zapyta TForce po zawartym w nim PRO. |

Dziewięciocyfrowy PRO zakończony cyfrą 7, 8 lub 9 ma literówkę. Katalog Peek zna TForce Freight także jako UPS Freight i TForce.

**Gdzie go znaleźć.** W e-mailu wysyłkowym ze sklepu lub na stronie zamówienia; przy frachcie między firmami zapytaj nadawcę. Zobacz [jak znaleźć numer przesyłki](guide:find-tracking-number).

**Ten sam kształt, inni przewoźnicy.** Dziewięć cyfr z tą samą cyfrą kontrolną pasuje też do [TNT](carrier:tnt) i XPO. TForce Logistics (TForce Final Mile), którego numery zaczynają się od `TF`, to w Peek osobny przewoźnik. Jeśli e-mail podaje jednego z nich, wybierz właśnie jego. Więcej w [formatach numerów przesyłek](guide:tracking-number-formats).

## Co oznaczają statusy TForce Freight

Strona TForce wyświetla status obok numeru PRO, np. `In Transit` lub `Delivered`, a poniżej historię od najnowszego wpisu, każdy z miastem i stanem centrum serwisowego, datą i godziną. Wpisy nie mają kodów — Peek odczytuje ich treść. W typowej kolejności:

| Komunikat TForce Freight | Co oznacza dla ciebie | Peek wyświetla |
| --- | --- | --- |
| `SHIPMENT HAS BEEN PICKED-UP.` | Kierowca odebrał ładunek od nadawcy. | „Przyjęta przez przewoźnika” |
| `SHIPMENT IS AT A TFORCE FREIGHT LOCATION` | Ładunek jest w centrum serwisowym po stronie nadawcy. | „W transporcie” |
| `DEPARTURE` | Opuścił centrum serwisowe. | „W transporcie” |
| `SHIPMENT HAS ARRIVED AT A SERVICE CENTER.` | Dotarł do centrum po drodze lub do oddziału doręczającego. | „W transporcie” |
| `SENT EMAIL. TFORCE FREIGHT IS ATTEMPTING TO OBTAIN APPOINTMENT INFORMATION` | TForce wysłało e-mail do odbiorcy w celu umówienia terminu dostawy. Odpowiedz na niego. | „W transporcie” |
| `A DELIVERY APPOINTMENT HAS BEEN SCHEDULED WITH THE CONSIGNEE.` | Uzgodniono dzień dostawy. Wpis ten pojawia się czasem dopiero po doręczeniu. | „W transporcie” |
| `OUT FOR DELIVERY` | Na ciężarówce w trasie doręczenia. | „W doręczeniu” |
| `Shipment not delivered. Delivery attempted after normal business hours. Tforce will attempt delivery on next business day we service this area.` | Niedoręczony w tym kursie. Każdy wpis zaczynający się od `Shipment not delivered.` oznacza nieudaną próbę z podaniem przyczyny. | „Próba doręczenia” |
| `SHIPMENT HAS BEEN DELIVERED TO THE CONSIGNEE.` | Doręczony. Strona TForce pokazuje, kto podpisał odbiór. | „Dostarczona” |

Status `Delivered` obok PRO zawsze wyświetla się jako „Dostarczona”; w pozostałych przypadkach decyduje najnowszy wpis rozpoznany przez Peek, a pozostałe zachowują oryginalny tekst TForce. Strona TForce może też wysyłać powiadomienia e-mailem. Więcej w [znaczeniu statusów](guide:tracking-statuses).

## Jak doręcza TForce Freight

TForce przewozi ładunek do centrum obsługującego twój rejon, a następnie dostarcza go ciężarówką. Wyszukiwarka podaje szacowany dzień dostawy, który Peek pokazuje aż do doręczenia, zwrotu lub upływu terminu. Kod Peek nie przekazuje przesyłki żadnemu innemu przewoźnikowi.

Taryfa TForce (LTL Rules Tariff UPGF 102-T, obowiązująca od 17 sierpnia 2026 roku) określa zasady dostawy:

- **Adresy prywatne.** Tam, gdzie ciężarówki mogą legalnie wjechać, TForce musi najpierw uzgodnić datę i przybliżoną godzinę z tobą lub nadawcą. Firma prowadzona w domu traktowana jest jako adres prywatny. Dostawa obejmuje wejście na parterze, wjazd do garażu lub wiatę albo odbiór własny w terminalu. Dostawa prywatna to usługa dodatkowa opłacana przez zlecającego lub płatnika frachtu.
- **Przy ciężarówce.** Standardowa dostawa oznacza rozładunek obok pojazdu. Wniesienie do środka i winda są dodatkowo płatne — zapytaj zlecającego, czy zostały wykupione.
- **Awizo przybycia.** Gdy dokumenty przewozowe tego wymagają, TForce powiadamia odbiorcę najpóźniej w następnym dniu roboczym po przybyciu do oddziału doręczającego, zwykle telefonicznie lub e-mailem.
- **Niedoręczenie.** W razie odmowy przyjęcia lub braku kontaktu z odbiorcą TForce informuje nadawcę o magazynowaniu, co może wiązać się z opłatami za przechowanie. Kolejna próba jest płatna, chyba że odbierzesz ładunek w terminalu — pod adresem prywatnym tylko wtedy, gdy przegapiłeś umówiony termin.

**Do Kanady.** Jak wyjaśnia kanadyjska strona TForce, ładunki z USA przechodzą odprawę na granicy przez agencję celną. Nieodprawione ładunki czekają w magazynie celnym u celu za opłatą za składowanie. Opłaty agenta, cła i podatki pokrywa importer.

## Przesyłka TForce Freight nieznaleziona, opóźniona, uszkodzona lub zagubiona

**Nie znaleziono.** Dla nieznanego PRO strona TForce wyświetla `Not Available` i `Please check ProNumber` z numerem. Peek pokazuje wtedy „Oczekiwanie na przewoźnika TForce Freight” (jeśli go wybrałeś) albo „Brak historii śledzenia” i sprawdza później ponownie. Zweryfikuj cyfry i zapytaj nadawcę, czy TForce odebrało już towar.

**Błędna cyfra.** Numer PRO z błędną cyfrą kontrolną nie należy do TForce i Peek nigdy o niego nie pyta. Jeśli mimo to wybierzesz TForce Freight, Peek poda „nie wydaje takich numerów”, a przesyłka pokaże „Sprawdź numer śledzenia”.

**Brak aktualizacji.** Po czterech dniach bez skanu w transporcie (poza odprawą celną i zgłoszonym problemem) Peek wyświetla „Brak aktualizacji od…”, przypomina, że „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika TForce Freight.” i podsuwa „Poproś przewoźnika TForce Freight o pomoc”. Zapytaj sklep lub nadawcę. Zobacz [gdy śledzenie się nie aktualizuje](guide:tracking-not-updating).

**Nieudana dostawa.** Po wpisie `Shipment not delivered.` Peek pokazuje „Sprawdź wskazówki przewoźnika”. Zadzwoń do TForce albo poproś zlecającego o ustalenie nowego terminu. Zobacz [nieudane doręczenia](guide:missed-delivery).

**Braki lub szkody przy odbiorze.** Strona Cargo Claims Information zaleca sprawdzenie ładunku za każdym razem i natychmiastowe zgłoszenie kierowcy:

- Policz sztuki względem kwitu odbioru i otwórz uszkodzone kartony przy kierowcy.
- Zapisz dokładnie braki lub uszkodzenia na swojej kopii oraz kopii TForce.
- Nie odmawiaj przyjęcia przesyłki, chyba że szkoda uczyniła towar bezwartościowym.
- Poproś osobę od Over, Short & Damage (OS&D) w lokalnym centrum serwisowym o odszukanie brakujących sztuk.

**Szkoda odkryta później.** Zgłoś ją w ciągu 5 dni roboczych od doręczenia i poproś o oględziny, pozostawiając opakowanie w zastanym stanie. Protokół oględzin nie zastępuje reklamacji.

**Składanie reklamacji.** Reklamacja to pisemne roszczenie właściciela ładunku. Przy zakupie w sklepie internetowym zacznij od sklepu, który ma umowę z przewoźnikiem. Zasady TForce:

- Złóż ją w ciągu dziewięciu miesięcy od doręczenia albo od daty wysyłki, jeśli nic nie dotarło.
- Użyj formularza Standard Claim Form lub pisma z danymi przesyłki i kwotą, załączając fakturę zakupu, fakturę frachtową, list przewozowy i ewentualne faktury za naprawę. Wyślij pocztą lub faksem (poniżej), nie obiema drogami.
- TForce potwierdza przyjęcie w ciągu 30 dni. Odpowiedzialność jest ograniczona do 25,00 $ za funt i sztukę (mniej dla części towarów), o ile nadawca nie wykupił dodatkowego pokrycia na liście przewozowym.

Jeśli śledzenie pokazuje doręczenie, ale nic nie dotarło, sprawdź [doręczona, ale nie otrzymana](guide:delivered-not-received). Więcej w [uszkodzona przesyłka](guide:damaged-parcel) i [zagubiona paczka](guide:lost-parcel).

## Obsługa klienta i kontakt TForce Freight

Dane ze strony www.tforcefreight.com (sekcje Tracking, Fact Sheet, Rules Tariffs z LTL Rules Tariff UPGF 102-T, Canada, Cargo Claims Information, Contact Us z formularzem Email TForce Freight Customer Support oraz Service Information):

- **Obsługa klienta:** (800) 333-7400, numer bezpłatny z USA, Kanady, Guam, Portoryko i Wysp Dziewiczych USA — w sprawie śledzenia, potwierdzenia doręczenia i problemów. Spoza tego obszaru: (804) 231-8555.
- **E-mail:** formularz Email TForce Freight Customer Support na stronie Contact Us z polem na numer PRO.
- **Dział reklamacji:** (888) 653-9720 w sprawie statusu reklamacji. Dokumenty kieruje się na Claims Processing, TForce Freight, PO Box 1216, Richmond, VA 23218 lub faksem na (866) 580-1944.
- **Lokalny terminal:** wyszukiwarka Service Center Directory w sekcji Service Information na www.tforcefreight.com.

Sklep lub nadawca załatwi sprawę często szybciej, bo ma rezerwację w TForce.

## Śledzenie ładunku TForce Freight w Peek

Sam kształt numeru PRO nie przesądza o TForce: dziewięć cyfr pasuje też do [TNT](carrier:tnt), XPO i innych przewoźników. Wklej go mimo to — Peek obsługuje ponad 4500 przewoźników. Podczas wyświetlania komunikatu „Sprawdzanie usług śledzenia…” Peek pyta pasujących przewoźników; gdy przesyłkę zna wyłącznie TForce, pojawia się jej nazwa z dopiskiem „ma tę przesyłkę”. Dla numeru PARS (`2205` i PRO) TForce jest pytane jako pierwsze; przy ośmiocyfrowym PRO bez zera na początku wybierz TForce Freight przyciskiem „Zmień” w sekcji „Pasują do numeru” (lub później przez „Zmień przewoźnika”). Link do wyszukiwarki TForce (`proNumbers=`) lub dawnego UPS Freight (`TrackProNumber=`) od razu wskazuje TForce („Wykryty przewoźnik”).

Peek odpytuje TForce po samym numerze PRO. Pokazuje status, każdy wpis z miastem i stanem, szacowany dzień dostawy, kraj docelowy na mapie, usługę, wagę w twoich jednostkach, nazwę i miejscowość odbiorcy oraz podpis po doręczeniu; pomija nadawcę, umówioną wizytę, numery BOL i PO, liczbę sztuk, naczepy, kody terminali i dokument potwierdzenia doręczenia. „Otwórz stronę przewoźnika TForce Freight” otwiera numer (lub PRO z numeru PARS) w systemie TForce. Gdy strona nie odpowiada lub wymaga CAPTCHA, Peek może zapytać [usługi śledzenia](guide:universal-tracker).

TForce podaje każdą godzinę w czasie lokalnym centrum serwisowego bez strefy czasowej. Peek odczytuje ją według zegara miejscowości — lub stanu/prowincji, sąsiedniego wpisu albo kraju docelowego, a w ostateczności czasu wschodniego USA (Eastern Time) — i wyświetla w twojej strefie czasowej („Godziny w twojej strefie czasowej:”).

Peek odpytuje TForce bezpośrednio nawet co 10 minut, a w dniu, gdy TForce zeskanuje ładunek w doręczeniu — nawet co 2 minuty od 6:00 według zegara ostatniego skanu (domyślnie czasu wschodniego), gdy przesyłka jest otwarta w Peek albo w dniu dostawy z włączonymi powiadomieniami. Po dwóch dniach bez zmian (po 12 godzinach przy statusie „Zarejestrowana” lub „Przyjęta przez przewoźnika”) Peek sprawdza co godzinę. Dla powiadomień możesz wybrać „Wszystkie aktualizacje”, „Tylko ważne” lub „Tylko w dniu dostawy”. Peek nie skontaktuje się za ciebie z TForce, sklepem ani nadawcą i nie złoży reklamacji.

## Najczęstsze pytania o TForce Freight

### Czym jest numer PRO w TForce Freight?

To numer śledzenia TForce: dziewięć cyfr, gdzie ostatnia jest cyfrą kontrolną od 0 do 6. Podaje go sklep lub firma zlecająca transport.

### Czy mogę śledzić TForce Freight bez numeru PRO?

Na stronie śledzenia TForce tak — po numerze listu przewozowego (BOL) lub zamówienia (PO) z zakresem dat wysyłki. Peek śledzi wyłącznie po numerze PRO.

### Czy TForce Freight dostarcza pod adresy prywatne?

Tak, tam gdzie ciężarówki mogą legalnie wjechać, za dodatkową opłatą. TForce najpierw umawia datę i orientacyjną godzinę z tobą lub nadawcą, a potem dostarcza pod wejście na parterze, garaż lub wiatę. Wniesienie i winda również są dodatkowo płatne.

### Ile czasu mam na złożenie reklamacji w TForce Freight?

Dziewięć miesięcy od doręczenia lub od daty nadania, jeśli nic nie dotarło. Szkody wykryte po odbiorze zgłoś w ciągu 5 dni roboczych.

### Jaki jest numer obsługi klienta TForce Freight?

(800) 333-7400, bezpłatny z USA i Kanady. Z pozostałych krajów: (804) 231-8555.

:::sources
- [Universal Parcel Scraper: TForce Freight](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/tforce-freight) – nazwy, formaty PRO, linki, komunikaty i etapy, odpowiedź brak danych, dane odczytywane przez Peek, strefy czasu
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – przewoźnicy o tym samym formacie, brak przekazania dalej, zapasowe usługi śledzenia
:::
