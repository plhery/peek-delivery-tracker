---
title: Śledzenie SpeedX: numery SPX, statusy i obsługa klienta
description: Śledź paczkę SpeedX w USA: jak wygląda numer SPX, co oznaczają angielskie statusy, zdjęcie doręczenia po kodzie ZIP, nieudane próby dostawy i kontakt.
slug: sledzenie-speedx
published: 2026-11-21
updated: 2026-11-21
---

SpeedX (Speed Xpress Inc. z siedzibą w New Hyde Park w stanie Nowy Jork) to firma kurierska ostatniej mili doręczająca zamówienia internetowe pod drzwi w **Stanach Zjednoczonych**. Przesyłkę sprawdzisz na stronie [tracking.speedx.io](https://tracking.speedx.io/) albo wklejając jej numer w pole powyżej. Kod pochodzi ze sklepu, w którym kupiłeś towar: SpeedX generuje etykietę dla sprzedawcy, dzięki czemu sklep zna numer jeszcze zanim paczka fizycznie trafi do SpeedX.

## Numer przesyłki SpeedX: format i gdzie go znaleźć

SpeedX podaje, że wszystkie jego numery zaczynają się od `SPX`. W praktyce składają się z prefiksu `SPX`, trzech liter oraz 12 albo 18 cyfr. Rozpoznawane układy (ze zmyślonymi znakami):

| Zmyślony układ | Co oznacza |
| --- | --- |
| `SPXLGA482910573649` | `SPX`, 3 litery i 12 cyfr (18 znaków). |
| `SPXLGA482910573649182736` | `SPX`, 3 litery i 18 cyfr (24 znaki). |
| `SPXPH482910573649` | `SPXPH` i cyfry: to nie SpeedX w USA, lecz SPX Express na Filipinach — inna firma. |

W Peek wielkość liter i spacje nie mają znaczenia.

**Gdzie jest numer.** Poszukaj go w wiadomości e-mail ze sklepu o wysyłce albo na stronie zamówienia. Jeśli go tam nie ma, SpeedX zaleca kontakt ze sprzedawcą; inne miejsca podpowiada poradnik [jak znaleźć numer przesyłki](guide:find-tracking-number). W USA na stronie SpeedX można też zapisać się na automatyczne powiadomienia SMS o wydaniu paczki do doręczenia.

**Podobne numery.** Samo `SPX` z samymi cyframi nie pasuje do żadnego z obu formatów SpeedX. Gdy w kodzie `SPX…` brakuje cyfry lub jest o jedną za dużo, porównaj go z wiadomością od sprzedawcy. Numery SpeedX nie pokrywają się również z kodami UniUni, [GOFO](carrier:gofo) ani [USPS](carrier:usps) w USA. Jeśli kod nadal nie działa, SpeedX radzi upewnić się u sprzedawcy, czy paczka nie jedzie z inną firmą ostatniej mili. Więcej w [formaty numerów przesyłek](guide:tracking-number-formats).

## Co oznaczają statusy śledzenia SpeedX

Strona śledzenia SpeedX działa wyłącznie po angielsku. Oto typowe wpisy w kolejności pojawiania się na trasie:

| Status SpeedX | Co oznacza dla paczki | Peek pokazuje |
| --- | --- | --- |
| `Shipping Label Created` | Sklep utworzył etykietę; SpeedX nie ma jeszcze paczki. | „Zarejestrowana” |
| `Pickup Requested` | Sprzedawca zlecił odbiór w SpeedX, ale przewoźnik jeszcze nie odebrał przesyłki. | „Zarejestrowana” |
| `Parcel Picked Up` | Kierowca SpeedX odebrał paczkę od nadawcy. | „Przyjęta przez przewoźnika” |
| `Arrived at Origin Facility` / `Processing at Origin Facility` | Przyjęcie i sortowanie w pierwszym magazynie SpeedX. | „W transporcie” |
| `In transit to destination facility` | W drodze między obiektami SpeedX w USA. | „W transporcie” |
| `Processing at Transit Facility` | Sortowanie w obiekcie pośrednim na trasie. | „W transporcie” |
| `Arrival at Destination Facility` | Dotarła do oddziału obsługującego Twoją okolicę w USA. | „W transporcie” |
| `Processing at Designated Facility` | Posortowana na miejscu do trasy kuriera. | „W transporcie” |
| `Out for Delivery` | Kurier zabrał paczkę do dzisiejszego doręczenia. | „W doręczeniu” |
| `Attempted Delivery: No access` | Kurier nie mógł dostać się pod drzwi: zamknięta brama, brak wejścia do budynku, roboty drogowe lub zła pogoda. | „Próba doręczenia” |
| `Delivered` | Doręczono. Strona SpeedX dopisuje miejsce pozostawienia paczki (np. pomieszczenie pocztowe) oraz zdjęcie. | „Dostarczona” |
| `Delivered to PUDO` / `Waiting for collection from Parcelshop` | Po trasie kurierskiej paczka została zostawiona w punkcie partnerskim (*parcelshop*), gdzie czeka na odbiór. | „Do odbioru” |
| `Self picked up from Parcelshop` | Przesyłka została odebrana z punktu *parcelshop*. | „Dostarczona” |
| `Parcel Void` | SpeedX oznaczył problem z przesyłką. Skontaktuj się ze sklepem. | „Zgłoszono problem” |
| `Exception- Parcel not found at the sort facility` | Nie odnaleziono paczki w sortowni. Napisz do sprzedawcy. | „Zgłoszono problem” |
| `Parcel Disposal` | SpeedX odnotował problem bez podania przyczyny. Zapytaj sklep o dalsze kroki. | „Zgłoszono problem” |

Dopóki w historii widnieje wyłącznie `Shipping Label Created` (albo nieudany odbiór u nadawcy, np. `Pickup Failure:Closed Business Hours`), paczka wciąż znajduje się u sprzedawcy lub w jego magazynie.

Nieudana próba doręczenia może mieć też inne powody, np. `Attempted Delivery:Building Closed/Weekend/Holiday` przy zamkniętej firmie („Próba doręczenia”). Pod wpisem SpeedX dodaje notatkę o kolejnym kroku: `The package is undeliverable and schedule to redelivery` zapowiada ponowne wydanie kurierowi, a `The package is undeliverable and return to facility` — powrót do oddziału. Peek wyświetla tę adnotację zaraz po treści statusu.

Gdy ostatnie zdarzenie oznacza utrudnienie (`Parcel Void`, `Parcel Disposal` albo brak paczki w sortowni), SpeedX przypisuje je do kategorii takiej jak `Last Mile Intercept` lub `Last Mile Undelivered`. SpeedX nie objaśnia tych kategorii publicznie — wyjaśnij sprawę ze sklepem.

## Doręczenie SpeedX w USA, próby dostawy i odbiór

Wszystkie poniższe zasady dotyczą sieci SpeedX na terenie **Stanów Zjednoczonych**:

**Przesyłki z zagranicy.** SpeedX doręcza w USA także zamówienia wysłane spoza kraju: usługa transgraniczna odbiera je w głównych amerykańskich portach lotniczych i odprawia przed amerykańskim urzędem celnym przez własną agencję celną, zanim trafią do sieci kurierskiej. O cłach przeczytasz w poradniku [paczka zatrzymana przez urząd celny](guide:customs).

**Weekendy.** W USA SpeedX doręcza w soboty od 8:00 do 20:00 czasu lokalnego we wszystkich obsługiwanych rejonach oraz w niedziele w tych samych godzinach na wybranych obszarach. Firma nie oferuje umawiania przedziałów godzinowych ani powiadomień o zbliżającym się kurierze.

**Podpis i zdjęcie doręczenia.** Większość przesyłek SpeedX w USA nie wymaga podpisu; towary z ograniczeniem wiekowym, jak alkohol, mogą wymagać podpisu osoby dorosłej mającej ukończone 21 lat. Pod nieobecność domowników kurier zostawia paczkę i robi zdjęcie — zobaczysz je po wpisaniu na `speedx.io` amerykańskiego kodu pocztowego (*ZIP code*) miejsca dostawy.

**Wskazówki dla kuriera i zmiana adresu.** Wskazówki doręczenia (kody do bramy, bezpieczne miejsce zostawienia paczki, godziny otwarcia firmy czy numer telefonu) przekażesz przez czatbota Miles, podając numer śledzenia; trafiają one w czasie rzeczywistym również do kierowcy, z którym nie można rozmawiać bezpośrednio. Jeśli zgłosisz zmianę odpowiednio wcześnie, SpeedX może poprawić w USA ulicę lub numer mieszkania; zmiana miasta, stanu lub kodu ZIP wymaga kontaktu ze sprzedawcą.

**Nieudana próba doręczenia.** Jeśli kwestionujesz odnotowaną próbę doręczenia, sprawdź najpierw adres na stronie sklepu, a potem prześlij poprawkę lub instrukcje dostępu przez czat Miles — według SpeedX ponowna próba powinna nastąpić w ciągu 1–2 dni roboczych. Przewoźnik nie publikuje łącznej liczby prób ani czasu przechowywania paczki. Zobacz też [nieudana próba doręczenia](guide:missed-delivery).

**Odbiór osobisty.** SpeedX zaznacza, że w USA nie wydaje paczek odbiorcom we własnych magazynach — przesyłka musi zostać doręczona. Część paczek trafia jednak po trasie do partnerskiego punktu *parcelshop* ze statusem `Waiting for collection from Parcelshop`, skąd można je odebrać.

## Paczka SpeedX stoi w miejscu, nie dotarła albo zaginęła

Dla przesyłek obsługiwanych przez SpeedX w **USA**:

**Brak ruchu.** Gdy status nie zmienia się przez trzy dni robocze, SpeedX prosi o kontakt ze sklepem. Więcej w poradniku [dlaczego śledzenie się nie aktualizuje](guide:tracking-not-updating).

**Oznaczona jako doręczona, ale jej nie ma.** Sama strona pomocy SpeedX przyznaje, że paczka bywa oznaczana jako doręczona, zanim faktycznie dotrze, albo zostawiana w nieoczekiwanym miejscu na posesji. W USA sprawdź najpierw zdjęcie doręczenia, a potem ganek z przodu i z tyłu domu, skrzynkę lub pokój pocztowy (*mailroom*), garaż, boki budynku i zadaszone zakamarki oraz zapytaj domowników lub sąsiadów. Daj znać SpeedX przez czatbota Miles, a w sprawie dalszych kroków skontaktuj się ze sprzedawcą. Przeczytaj też [dostarczona, ale nieodebrana](guide:delivered-not-received).

**Zaginięcie, uszkodzenie lub błędny adres.** SpeedX nie przyjmuje reklamacji od odbiorców: o reklamację, zwrot pieniędzy lub ponowną wysyłkę wystąp do sprzedawcy, podając numer zamówienia, numer śledzenia i historię korespondencji (przy uszkodzeniu sklep może poprosić o zdjęcia). SpeedX nie publikuje terminu granicznego. Zobacz [zagubiona paczka](guide:lost-parcel).

**Zwroty.** Po doręczeniu zwrot towaru załatwia się ze sklepem, w którym kupiono produkt; SpeedX nie odbiera zwrotów spod drzwi w USA. Jeśli paczka nie została jeszcze doręczona, możesz poprosić Milesa o wstrzymanie dostawy i odesłanie jej do sprzedawcy. Zobacz [zwrócona do nadawcy](guide:returned-to-sender).

## Obsługa klienta SpeedX: czat, telefon i e-mail

Kanały kontaktu dla odbiorców w **USA** wymienione na [stronie wsparcia SpeedX](https://speedx.io/customer-service-and-support/):

- **Czat:** czatbot Miles, codziennie w godz. 8:00–20:00 ET (czasu wschodnioamerykańskiego) w portalu śledzenia po wpisaniu numeru paczki.
- **Telefon w USA:** `1-949-667-6769`, od poniedziałku do piątku w godz. 9:00–21:00 EST oraz w weekendy w godz. 11:00–20:00 EST. W innej odpowiedzi na tej samej stronie widnieje jednak informacja, że SpeedX nie prowadzi infolinii telefonicznej i odsyła do Milesa — licz się więc z przekierowaniem na czat.
- **E-mail:** `speedxops@speedx.io`.

## Śledzenie przesyłek SpeedX w Peek

[Peek](/) od razu rozpoznaje SpeedX po kodzie `SPX` w obu wariantach (`SPX` + 3 litery + 12 lub 18 cyfr) albo po linku z `tracking.speedx.io` zawierającym numer, wyświetlając „Wykryty przewoźnik” oraz przycisk „Zmień”.

Peek odczytuje bezpośrednio stronę śledzenia SpeedX i pokazuje aktualny status, każdy skan z miejscowością na mapie o godzinie zapisanej przez SpeedX (przeliczonej na strefę Twojego urządzenia, z dopiskiem „Godziny w twojej strefie czasowej: …” pod historią na `peektracker.com` oraz w aplikacjach na iPhone'a i Androida), a także dokładną godzinę dostarczenia i, jeśli strona SpeedX go podaje, przewidywany dzień doręczenia. Peek pomija Twój adres oraz zdjęcie z doręczenia — aby obejrzeć zdjęcie, wejdź na `speedx.io` i podaj swój amerykański kod ZIP. Jeśli SpeedX doda notatkę o miejscu pozostawienia paczki, skan doręczenia zachowuje ją bez zmian, po angielsku; pod udostępnionym linkiem wyświetla wyłącznie „Dostarczona”, bez tej notatki. Przekazanie przesyłki kolejnemu przewoźnikowi nie występuje.

Dopóki SpeedX nie zna jeszcze numeru, Peek wyświetla „Oczekiwanie na przewoźnika SpeedX”; jeśli po dwóch dniach nie rozpozna go ani SpeedX, ani żadne inne źródło, pokazuje „Po dwóch dniach przewoźnik nadal nie zna tego numeru. Sprawdź go, wybierz innego przewoźnika lub usuń przesyłkę.” Gdy przez cztery dni nie pojawi się nowy skan na etapie „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie”, Peek udostępnia link „Poproś przewoźnika SpeedX o pomoc” do strony wsparcia przewoźnika.

Gdy masz otwartą kartę przesyłki lub w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza aktualizacje nawet co 10 minut — a od godz. 6:00 czasu lokalnego w dniu zeskanowania paczki jako wydanej do doręczenia (`Out for Delivery`) nawet co 2 minuty, dopóki odpowiada własny system SpeedX (przez zewnętrzne serwisy śledzenia same: co godzinę, co 3 godziny po spokojnym dniu i do co 15 minut w dniu doręczenia przy otwartej paczce lub włączonych alertach); w pozostałym czasie, a także po dwóch dniach bez zmian (lub 12 godzinach na etapie „Zarejestrowana” bądź „Przyjęta przez przewoźnika”), sprawdza co godzinę. Powiadomienia ustawisz na „Każdy skan”, „Ważne etapy” albo „Tylko doręczenie”. Gdy strona SpeedX nie odpowiada, inne źródła zewnętrzne rzadko mają dane o przesyłkach SpeedX, więc nowe wpisy mogą pojawić się dopiero po przywróceniu dostępu.

## Najczęstsze pytania o śledzenie SpeedX

### Czy SpeedX to wiarygodna firma kurierska?

Tak — SpeedX to marka spółki Speed Xpress Inc., założonej w 2022 r. w stanie Nowy Jork firmy ostatniej mili doręczającej zamówienia ze sklepów internetowych na terenie USA.

### Czy mogę odebrać paczkę SpeedX osobiście z magazynu?

Nie z oddziałów SpeedX w USA — firma nie zezwala na odbiór osobisty w swoich obiektach i wymaga próby doręczenia. Jeśli jednak w historii pojawi się `Waiting for collection from Parcelshop`, po trasie paczka trafiła do punktu partnerskiego (*parcelshop*), w którym możesz ją odebrać.

### Ile trwa dostawa przez SpeedX?

SpeedX nie publikuje gwarantowanych terminów dla odbiorców. Nadawcom oferuje w USA dostawy na następny dzień (*next-day*) oraz w dwa dni (*2nd-day*), ale każda szacowana data pochodzi ze sklepu i według SpeedX może różnić się od rzeczywistego dnia doręczenia.

### Dlaczego mój numer śledzenia SpeedX nie działa?

Gdy `tracking.speedx.io` nie pokazuje jeszcze danych (`No tracking information for … is available at this time`), nadawca mógł jeszcze nie przesłać informacji o paczce do SpeedX albo numer jest błędny. SpeedX radzi potwierdzić kod u sprzedawcy, ponieważ zamówienie mogło zostać wysłane inną firmą kurierską.

:::sources
- [SpeedX: Customer Service and Support](https://speedx.io/customer-service-and-support/) – prefiks SPX, słownik statusów, SMS, dostawy w soboty i niedziele, podpis od 21 lat, zdjęcia po kodzie ZIP, instrukcje i zmiany adresu przez Milesa, brak odbioru w magazynie, 3 dni robocze bez zmian, reklamacje u sprzedawcy, zwroty, telefon, e-mail i godziny pracy
- [SpeedX: Tracking](https://tracking.speedx.io/) – anglojęzyczny portal śledzenia przesyłek SpeedX
- [SpeedX: Tech-Enabled Last Mile Delivery](https://speedx.io/) – doręczenia ostatniej mili w USA, etykiety tworzone przez SpeedX, usługi next-day i 2nd-day oraz zdjęcia doręczenia
- [SpeedX: Cross-Border Shipping](https://speedx.io/cross-border-shipping/) – przyjęcie przesyłek zagranicznych na lotniskach w USA, własna agencja celna i przekazanie do sieci SpeedX
- [SpeedX: About Us](https://speedx.io/about-us/) – powstanie w 2022 r. w Nowym Jorku i zasięg w miastach USA
- [SpeedX: Privacy Policy](https://speedx.io/privacy-policy/) – nazwa spółki Speed Xpress Inc. i adres w New Hyde Park
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – rozpoznawane formaty SPX, odczyt statusów, dopiski po nieudanej próbie, komunikat o braku danych i prywatność na udostępnionych linkach
:::
