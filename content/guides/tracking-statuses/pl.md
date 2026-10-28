---
title: „Przesyłka utworzona” – co to znaczy? Statusy przesyłek
description: „Przesyłka utworzona” (np. w InPost) znaczy, że przewoźnik nie ma jeszcze paczki. A co znaczą „w tranzycie”, „w doręczeniu”, awizo, cło i zwrot?
slug: statusy-przesylek-co-oznaczaja
picture: Kręta trasa z odhaczonymi przystankami (etykieta z kodem kreskowym, magazyn, samolot), furgonetka na obecnym przystanku, dom jeszcze przed nią i pędzący Pip.
published: 2026-10-09
updated: 2026-10-27
---

Status przesyłki to ostatni skan przewoźnika w kilku słowach i zwykle niczego od Ciebie nie wymaga. „Przesyłka utworzona” albo „przygotowana przez nadawcę” znaczy, że przewoźnik nie ma jeszcze Twojej paczki, „w trasie” albo „w tranzycie”, że jest gdzieś między dwoma skanami, a „wydana do doręczenia”, że jedzie już do Ciebie. Działać musisz tylko w kilku sytuacjach: po nieudanej próbie doręczenia, gdy paczka czeka w automacie albo punkcie, gdy trzeba zapłacić należności celne i gdy status brzmi „doręczona”, a paczki nie ma.

## Przez jakie etapy przechodzi paczka?

Każda paczka mija te same przystanki, a paczka z zagranicy jeszcze trzy.

:::journey
- label | Przygotowana przez nadawcę | Przewoźnik ma dane paczki, ale nie samą paczkę.
- warehouse | W trasie | Odebrana, potem z oddziału do sortowni i dalej, a między skanami cisza.
- plane | Opuściła kraj nadania | Tylko z zagranicy. W samolocie nikt nie skanuje.
- customs | Odprawa celna | Tylko z zagranicy. Czasem trzeba coś dopłacić.
- handover | Przewoźnik w Polsce | Tylko z zagranicy. Jego skany bywają tylko na jego stronie.
- truck | Wydana do doręczenia | Jest już w aucie kuriera, zwykle dotrze dziś.
- locker | Gotowa do odbioru | Czeka w automacie paczkowym, punkcie albo na poczcie.
- home | Doręczona | Trafiła do Twoich rąk albo tam, gdzie przewoźnik uznaje ją za doręczoną.
:::

## Co oznaczają statusy InPost i FedEx?

Te same etapy, inne słowa. Tabela pokazuje statusy [InPost](carrier:inpost) i globalnego śledzenia [FedEx](carrier:fedex) tak, jak Peek odczytuje je po angielsku:

| Etap | InPost (tak czyta je Peek) | FedEx (tak czyta je Peek) |
| --- | --- | --- |
| Etykieta gotowa | `Shipment Created` | `Shipment information sent to FedEx` |
| Nadana | `Sent through Parcel Locker/Parcel Point` albo `Collected by the Courier` (po polsku InPost pisze `Paczka nadana w automacie Paczkomat.`, `Nadana w PaczkoPunkcie.` albo `Odebrana od Nadawcy.`) | `Picked up` albo `Tendered at FedEx location` |
| W drodze | `Accepted at the Logistics Centre`, `On the way`, `Dispatched from the Logistics Centre` | `On the way`, `At local FedEx facility` |
| Wydana do doręczenia | `Handed over for delivery` (pod drzwi albo do automatu Paczkomat) | `On FedEx vehicle for delivery` |
| Gotowa do odbioru | `Ready for collection` | `Ready for pickup` |
| Doręczona | `Delivered` | `Delivered` |

Krajowe przesyłki [FedEx Polska](carrier:fedex-pl) (dawniej Opek) mają 13-cyfrowe numery i osobne śledzenie, w którym statusy są po polsku, na przykład `Przesyłka w oddziale FedEx.`

## „Przesyłka utworzona” albo „przygotowana przez nadawcę”: co to znaczy?

Tylko tyle, że przewoźnik ma dane Twojej paczki, ale nie samą paczkę. U InPost `Przesyłka utworzona.` znaczy, że przesyłka „nie jest gotowa do nadania”, a `Przygotowana przez Nadawcę.`, że „wkrótce zostanie przekazana w nasze ręce”. W globalnym śledzeniu FedEx ten etap to `Shipment information sent to FedEx`, a gdy paczka trafi już do FedEx, pojawia się `Picked up` albo `Tendered at FedEx location`.

Jeśli pierwszy status długo się nie zmienia, napisz do sprzedawcy, nie do przewoźnika: paczka wciąż jest u sprzedawcy. Tak samo radzi [Cainiao](carrier:cainiao) przy zamówieniach z Chin bez danych śledzenia: zamówienie „jest przetwarzane”, a po szczegóły trzeba się zwrócić do sprzedawcy.

Paczka nadana w automacie też nie od razu rusza: po `Sent through Parcel Locker/Parcel Point` przychodzi `Collected by the Courier`, gdy kurier odbierze ją z automatu (ten sam status pojawia się, gdy odbiera ją od nadawcy).

## Przesyłka w trasie albo w tranzycie: co to znaczy?

Przewoźnik ma Twoją paczkę gdzieś w swojej sieci, ale nie znaczy to, że akurat jedzie. U InPost na tym etapie zobaczysz w Peek `Accepted at the Logistics Centre`, `Dispatched from the Logistics Centre` i `On the way`.

Między skanami cisza jest normalna: [Poczta Polska](carrier:poczta-polska) przy listach poleconych pokazuje tylko dzień nadania i dzień doręczenia, bez drogi przesyłki.

### Ile paczka jest w drodze?

[DPD](carrier:dpd-pl) podaje, że krajową paczkę standardowo doręcza następnego dnia roboczego, a gwarantuje 3 dni robocze; wiele zależy od usługi wybranej przez nadawcę. Poczta Polska, operator wyznaczony do 2035 roku, ma w rozporządzeniu cele dla zwykłych paczek pocztowych: 93% paczek najszybszej kategorii ma dotrzeć w terminie D+3, a 98% pozostałych w terminie D+5, gdzie D to dzień nadania.

Cisza dłuższa niż czas podany przez przewoźnika? Zobacz, [dlaczego status przesyłki się nie zmienia](guide:tracking-not-updating).

## Paczka z zagranicy: „opuściła kraj nadania”, cło i przewoźnik w Polsce

- **Opuściła kraj nadania.** Paczka jest w drodze samolotem, statkiem albo pociągiem. Po drodze nikt jej nie skanuje, więc do następnego skanu, zwykle już w kraju docelowym, bywa cicho.
- **Odprawa celna.** Każdą przesyłkę pocztową z towarem spoza Unii Europejskiej trzeba zgłosić do urzędu celnego, bez względu na wartość, ale paczkę do 150 euro Poczta Polska zgłasza za Ciebie. Gdy brakuje danych albo opis towaru jest niejasny, paczka może na jakiś czas utknąć w magazynie pocztowo-celnym. Po odprawie jedzie do doręczenia, a należności Poczta Polska pobiera przy doręczeniu albo przed odbiorem. Działaj tylko wtedy, gdy poczta albo urząd celny o coś poprosi. Ile zapłacisz, łącznie z cłem 3 euro za każdy rodzaj produktu, wyjaśnia poradnik [paczka zatrzymana przez urząd celny](guide:customs).
- **Przewoźnik w Polsce.** Ostatni odcinek często przejmuje inna firma, czasem [pod nowym numerem](guide:tracking-number-formats). Przy ekonomicznej wysyłce Cainiao pisze, że jego śledzenie kończy się na przekazaniu „przewoźnikowi w kraju/regionie docelowym”, a o dalsze statusy trzeba pytać tego przewoźnika. U [YunExpress](carrier:yunexpress) ten moment opisuje `Delivered to local carrier`: mimo słowa „Delivered” paczka trafiła do przewoźnika, nie do Ciebie. Paczek od Temu, AliExpress i Shein DPD nie przekierowuje; gdy już je ma, możesz je najwyżej skierować do punktu DPD Pickup na trasie kuriera. Zamawiasz w Chinach? Zobacz, jak [śledzić paczkę z Chin](guide:tracking-from-china).

## Czy „wydana do doręczenia” znaczy, że paczka będzie dziś?

Zwykle tak, ale przewoźnicy ujmują to różnie:

- **InPost, paczka kurierska:** `W doręczeniu.` znaczy, że paczka jest „na ostatnim etapie podróży”, u kuriera.
- **DPD:** po wydaniu paczki do doręczenia w aplikacji DPD Mobile i na tt.dpd.com.pl pojawia się numer telefonu kuriera. Gdy przy zamówieniu był wybór przedziału godzin, paczka przyjedzie w wybranym. W sobotę DPD doręcza tylko paczki z usługą Sobota.

Uwaga: `W doręczeniu.` pojawia się u InPost także wtedy, gdy kurier Cię nie zastał: w opisie zobaczysz wtedy, że kolejna próba będzie w następnym dniu roboczym.

[Peek](/) sprawdza paczkę nawet co 2 minuty, gdy jest już na ostatnim odcinku, a powiadomienia możesz ograniczyć do samego doręczenia (ustawienie „Tylko doręczenie”).

## Awizo i nieudana próba doręczenia: co teraz?

Tu musisz coś zrobić: [nikt nie odebrał paczki albo kurier nie mógł jej doręczyć](guide:missed-delivery).

:::steps
- Przeczytaj powiadomienie | SMS, e-mail, aplikacja albo awizo w skrzynce: gdzie jest paczka i do kiedy czeka.
- Sprawdź termin | Od 2 dni roboczych w automacie paczkowym do 14 dni na poczcie, liczonych w godzinach, dniach albo dniach roboczych.
- Zmień dostawę | Tylko na stronie lub w aplikacji przewoźnika: inny dzień, adres, sąsiad, automat paczkowy albo punkt.
- Odbierz paczkę | Kodem odbioru albo w aplikacji. W DPD z Twoim kodem odbierze ją też ktoś inny, jeśli nie trzeba nic podpisywać.
:::

| Przewoźnik | Po nieudanej próbie | Jak długo czeka |
| --- | --- | --- |
| DPD | W DPD Mobile albo na mojapaczka.dpd.com.pl: inna data (do 3 dni roboczych), adres, sąsiad, safeplace albo DPD Pickup | Punkt i automat DPD Pickup: 2 pełne dni robocze, bez przedłużenia |
| Poczta Polska, kurier Pocztex | SMS, e-mail albo powiadomienie w aplikacji (bez tych danych awizo w skrzynce), przypomnienie drugiego dnia | 7 dni na poczcie, potem zwrot |
| Poczta Polska, paczka pocztowa | Awizo w skrzynce; w miastach powyżej 100 000 mieszkańców paczkę odbierzesz od następnego dnia | 14 dni, licząc od dnia po zostawieniu awizo |
| Pocztex PUNKT i AUTOMAT | – | Placówka pocztowa 7 dni, punkt w sklepie (np. Żabka) 3 dni, automat 72 godziny, bez przedłużenia |

Przy paczce pocztowej cięższej niż 2 kg albo przesyłce z należnościami celnymi rozporządzenie pozwala Poczcie Polskiej od razu zostawić awizo, bez próby doręczenia do drzwi.

> SMS o nieudanej próbie doręczenia z linkiem do „ustalenia nowego terminu” albo do dopłaty to znany schemat oszustwa. Pocztex zapewnia, że nie wysyła linków do płatności i nie prosi SMS-em o dane karty. Zmieniaj dostawę tylko na stronie lub w aplikacji przewoźnika, a podejrzany SMS prześlij na numer 8080 (CERT Polska).

## „Gotowa do odbioru”: gdzie jest paczka?

W automacie paczkowym, punkcie albo na poczcie, do [terminu z tabeli wyżej](guide:uncollected-parcel). U InPost Peek pokazuje wtedy `Ready for collection`, a gdy paczka dalej czeka, `Reminder of a waiting parcel`. Pocztex przysyła SMS z numerem przesyłki, adresem punktu, terminem odbioru i jednorazowym kodem odbioru. Paczkę z DPD Pickup odbierzesz w aplikacji DPD Mobile albo kodem z e-maila.

Gdy w wybranym automacie Paczkomat nie ma miejsca, paczka czeka w pobliskim automacie tymczasowym i odbierzesz ją tym samym kodem (`Stored Parcel - Temporary Parcel Locker`). Jeśli nie odbierzesz jej tam na czas, InPost zawozi ją do wybranego automatu (`The storage period has expired`).

Nie idź po paczkę przed tym statusem albo powiadomieniem od przewoźnika: dopiero wtedy masz kod i znasz adres.

## Czy „doręczona” znaczy, że paczka jest u Ciebie?

Nie zawsze. Znaczy, że kurier zeskanował ją jako wydaną albo zostawioną:

- **Domownikowi.** Jeśli na liście przewozowym nie ma dopisku „do rąk własnych”, DPD uznaje za skuteczne doręczenie dorosłemu domownikowi albo innej uprawnionej osobie.
- **We wskazanym miejscu.** W DPD Mobile możesz sam wybrać sąsiada albo safeplace. Kurierowi Pocztex możesz przez telefon wskazać miejsce tuż przy adresie, jeśli nadawca podał Twój numer; za to, co stanie się z paczką potem, Poczta Polska już nie odpowiada.
- **W punkcie, nie u Ciebie.** Poczta Polska przyznaje, że przy paczce do punktu odbioru status „doręczono” bywa błędnym skanem: kurier dostarczył ją do punktu, a poprawny status to „dostarczono do punktu odbioru”. Zgłoś to infolinii Poczty Polskiej: 801 333 444 z telefonu stacjonarnego albo +48 438 420 600 z komórki.

Nie ma paczki? Sprawdź w pełnej historii śledzenia, gdzie i komu ją wydano, a potem przeczytaj poradnik [paczka doręczona, a jej nie ma](guide:delivered-not-received).

## Opóźnienie w doręczeniu: czy paczka zaginęła?

Nie. U InPost `Possible delivery delay` to komunikat, a nie ruch paczki, więc Peek zostawia ją na dotychczasowym etapie.

Inaczej jest, gdy chodzi o adres: jeśli jest zły, napisz od razu do sprzedawcy.

## Zwrot do nadawcy: dlaczego paczka wraca?

Przewoźnicy podają takie powody: paczka nieodebrana w terminie, odmowa przyjęcia, nieopłacone należności.

Odmowa działa od razu: w Pocztex odmówiona paczka wraca do nadawcy niezwłocznie, a odmowa zapłaty należności za przesyłkę liczy się jak odmowa przyjęcia. Przy paczce spoza Unii Poczta Polska też przypomina, że możesz odmówić jej przyjęcia. Przesyłka nieodebrana z poczty w terminie [wraca do nadawcy](guide:returned-to-sender). Gdy paczka już wraca, poproś sprzedawcę o ponowną wysyłkę albo zwrot pieniędzy.

:::sources
- [InPost: statusy przesyłek (API ShipX)](https://api-shipx-pl.easypack24.net/v1/statuses?lang=pl_PL) – „Przesyłka utworzona”, „Przygotowana przez Nadawcę”, „Paczka nadana w automacie Paczkomat”, „Nadana w PaczkoPunkcie”, „Odebrana od Nadawcy”, „W doręczeniu”
- [Universal Parcel Scraper: InPost](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/inpost) – statusy InPost, które czyta Peek (po angielsku), automat tymczasowy, możliwe opóźnienie
- [DPD Polska: najczęściej zadawane pytania](https://www.dpd.com/pl/pl/najczesciej-zadawane-pytania-faq/) – czas doręczenia, numer kuriera, zmiana dostawy, DPD Pickup
- [DPD Polska: Regulamin świadczenia usług w obrocie krajowym](https://www.dpd.com/wp-content/uploads/sites/260/2025/01/RUK_01.01.2024PL.pdf) – doręczenie domownikowi
- [Poczta Polska: Regulamin usługi Pocztex w obrocie krajowym dla klienta biznesowego od 24 sierpnia 2026](https://www.poczta-polska.pl/wp-content/uploads/2026/08/Regulamin-swiadczenia-uslugi-Pocztex-w-obrocie-krajowym-dla-klienta-biznesowego-obowiazuje-od-24.08.2026-r.pdf) – awizo kuriera, PUNKT, AUTOMAT, odmowa, dyspozycja telefoniczna
- [Poczta Polska: Odbiór w punkcie, FAQ](https://odbiorwpunkcie.poczta-polska.pl/faq/) – „doręczono” zamiast „dostarczono do punktu odbioru”, infolinia, brak przedłużenia
- [Poczta Polska: e-monitoring](https://emonitoring.poczta-polska.pl/) – listy polecone
- [Poczta Polska: Regulamin obsługi przesyłek zagranicznych w zakresie przedstawienia do kontroli i zgłoszenia do procedury celnej](https://www.poczta-polska.pl/wp-content/uploads/2025/09/Regulamin-obslugi-przesylek-zagranicznych-w-zakresie-przedstawienia-do-kontroli-i-zgloszenia-do-procedury-celnej.pdf) – numer infolinii z telefonu stacjonarnego i z komórki
- [Poczta Polska: Uwaga na fałszywe wiadomości Pocztex](https://www.poczta-polska.pl/news/uwaga-na-falszywe-wiadomosci-pocztex-grozna-kampania-phishingowa/) – SMS o nieudanym doręczeniu, numer 8080
- [Poczta Polska: Zmiany w opłatach dla przesyłek spoza UE od 1 lipca 2026](https://www.poczta-polska.pl/news/zmiany-w-oplatach-dla-przesylek-spoza-unii-europejskiej-od-1-lipca-2026-r/) – cło 3 euro, magazyn pocztowo-celny, odmowa
- [Poczta Polska: operator wyznaczony do 2035 roku](https://www.poczta-polska.pl/news/prezes-uke-zdecydowal-poczta-polska-operatorem-wyznaczonym-do-2035-roku/) – usługi powszechne
- [Dziennik Ustaw: rozporządzenie w sprawie warunków wykonywania usług powszechnych przez operatora wyznaczonego, tekst jednolity Dz.U. 2020 poz. 1026](https://eli.gov.pl/eli/DU/2020/1026/ogl) – § 34 awizo i miasta powyżej 100 000 mieszkańców, § 36 paczki powyżej 2000 g, § 37 termin odbioru 14 dni
- [Dziennik Ustaw: rozporządzenie zmieniające, Dz.U. 2024 poz. 1745](https://eli.gov.pl/eli/DU/2024/1745/ogl) – początek terminu odbioru, wskaźniki D+3 i D+5 dla paczek
- [KAS: Informacja dla odbiorców przesyłek pocztowych spoza Unii Europejskiej](https://www.mazowieckie.kas.gov.pl/mazowiecki-urzad-celno-skarbowy-w-warszawie/wiadomosci/komunikaty/-/asset_publisher/i3dT/content/informacja-dla-odbiorcow-przesylek-pocztowych-spoza-unii-europejskiej) – zgłoszenie celne każdej przesyłki, próg 150 euro, należności przy doręczeniu
- [Universal Parcel Scraper: FedEx](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/fedex) – statusy FedEx, które czyta Peek (po angielsku)
- [Universal Parcel Scraper: FedEx Poland](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/fedex-pl) – krajowe przesyłki FedEx w Polsce (dawniej Opek): 13 cyfr, osobne śledzenie, statusy po polsku
- [Cainiao: teksty strony śledzenia po polsku](https://lang.alicdn.com/mcms/global-track/0.0.10/global-track.json) – wysyłka ekonomiczna, brak danych
:::
