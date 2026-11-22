---
title: Dachser śledzenie Hiszpania i Portugalia: link i statusy
description: Śledź dostawę Dachser w Hiszpanii lub Portugalii: dlaczego liczy się pełny link, znaczenie statusów, umawianie dostawy i kontakt.
slug: sledzenie-dachser
published: 2026-11-22
updated: 2026-11-22
---

Dachser to niemiecka grupa logistyczna, której spółki w Hiszpanii i Portugalii przewożą ładunki i palety głównie między firmami, a także doręczają gabarytowe zamówienia do domów, gdy sklep wykupi usługę targo on-site. Aby śledzić dostawę w Hiszpanii lub Portugalii, otwórz link śledzenia z maila lub wiadomości o dostawie — prowadzi on do strony Customer Iberia firmy Dachser. Wklejony w całości w pole powyżej działa tak samo.

## Numer i link śledzenia Dachser

Dachser nie publikuje stałego formatu swoich numerów. Strona Customer Iberia pokazuje numer przesyłki („N.º Expedición”) oraz „N.º Único” (po angielsku „Unique Number”), czyli numer widniejący w linku śledzenia. Strony Dachser informują, że dla każdej przesyłki można utworzyć link śledzący i udostępnić go klientowi, ale nie precyzują, jaką drogą trafia do odbiorcy. Brak wiadomości? Zapytaj sklep i zobacz [gdzie znaleźć numer przesyłki](guide:find-tracking-number).

Najważniejszy jest pełny link. Wygląda tak (numer i klucz wymyślone):

`https://customeriberia.dachser.com/customerarea/utilidades/seguimiento-publico/detalle?numeroUnico=48291057362&hash=K7M2P9Q4R8`

Oprócz numeru zawiera klucz dostępu: `hash=`, albo `clave=` wraz z ośmiocyfrowym parametrem `fecha=`. Bez tego klucza strona nic nie wyświetla. Skopiuj cały link dokładnie w takiej postaci, w jakiej przyszedł.

Wpisany osobno numer Dachser przypomina numery wielu innych przewoźników (cyfry wymyślone):

| Numer | Z kim kojarzy go wyszukiwarka |
| --- | --- |
| `4829105736` | 10 cyfr: [DHL](carrier:dhl-express), [Mondial Relay](carrier:mondial-relay) i inni. |
| `48291057362` | 11 cyfr: GLS i inni. |
| `482910573621` | 12 cyfr: [FedEx](carrier:fedex), [MRW](carrier:mrw), BRT i inni. |
| `4829105736214` | 13 cyfr: DPD France, Delhivery i inni. |
| `48291057362148` | 14 cyfr: [SEUR](carrier:seur), DPD i inni. |

Jeśli wiadomość wymienia Dachser, zaufaj temu zamiast długości samego numeru.

## Znaczenie statusów śledzenia Dachser

Strona Customer Iberia ma etykiety po hiszpańsku, portugalsku, angielsku i francusku, ale treść każdego zdarzenia pochodzi z systemu Dachser dla konkretnej przesyłki. Tabela historii nazywa się po angielsku „Delivery Exceptions Historical” (po hiszpańsku „Historial de Incidencias”), lecz mimo nazwy obejmuje wszystkie zdarzenia, nie tylko problemy.

Przycisk „Track & Trace” na dachser.es i dachser.pt otwiera inne narzędzie — grupowy shipmentpointer. Polska pomoc Dachser objaśnia jego statusy w następującym zapisie (bez części polskich znaków); strona z twojego linku może używać innych sformułowań:

| Słowa Dachser | Co to oznacza dla ciebie |
| --- | --- |
| `Przesylka nie ma jeszcze zadnego statusu.` | [Zarejestrowana](guide:tracking-statuses): Dachser ma dane, ale jeszcze nie zapisał statusu towaru. |
| `Przesylka znajduje sie w przeladunku.` | Jest w magazynie Dachser podczas sortowania i przeładunku. |
| `Przesylka znajduje sie w transporcie glównym.` | Jedzie na dalekobieżnym odcinku między magazynami. |
| `Przesylka znajduje sie w transporcie lokalnym w trakcie dostawy.` | Wyjechała w lokalnym pojeździe doręczającym. |
| `Przesylka nie wchodzi jeszcze do procesu dostarczania albo jej dostarczenie nie powiodlo sie.` | Jest wstrzymana albo próba dostawy się nie powiodła. |
| `Przesylka zostala dostarczona.` | Dostarczona. |
| `Przesylka zostala "wyksiegowana" (np. anulowania).` | Anulowana. |

Te, które mogą mylić:

- „W przeładunku” oznacza magazyn Dachser, nie inną firmę.
- „Transport główny” to przewóz liniowy między magazynem nadawczym a doręczającym.
- „Nie wchodzi jeszcze do procesu dostarczania albo jej dostarczenie nie powiodło się” obejmuje kilka sytuacji. Pomoc Dachser podaje trzy przykłady: odbiorca wymaga awizacji przed dostarczeniem, nastąpiła odmowa przyjęcia lub adres odbiorcy był błędny. Sprawdź, czy sklep ma twój poprawny numer telefonu i adres.
- „Wyksięgowana” oznacza anulowanie, a nie potwierdzenie nadania.

## Dostawa Dachser: umawianie terminu i nieobecność

**Umawianie terminu.** Hiszpańskie warunki Dachser wskazują, że dostawy targo on-site nie mają sztywnego terminu i zazwyczaj są uzgadniane telefonicznie z odbiorcą — spodziewaj się więc telefonu. Dachser opisuje targo on-site jako dostawę po wcześniejszym umówieniu, do krawężnika, rano lub po południu. W wariancie targo on-site plus towar wnoszony jest do miejsca użytkowania, a w części krajów możliwa jest dostawa wieczorem lub w sobotę. W targo on-site fix ustalasz stałą datę dostawy ze sklepem już przy zamówieniu.

**Wybór daty na stronie.** Strony niektórych przesyłek mogą wyświetlać przycisk „Arrange delivery date.” (po hiszpańsku „Concertar Fecha”), pozwalający wybrać datę dostawy i przedział godzinowy oraz je potwierdzić. Jeśli go nie widzisz, poczekaj na telefon z Dachser albo zapytaj sklep.

**Dni i terminy.** Dachser nie ma obowiązku doręczać w niedziele ani święta. Dostawa w sobotę możliwa jest wyłącznie w usługach targospeed 10 i targofix 10, po uzgodnieniu z oddziałem Dachser. Podawane czasy dostawy mają charakter orientacyjny: liczy się je od dnia roboczego po odbiorze, bez weekendów i świąt.

**Brak punktów odbioru i automatów.** Hiszpańskie i portugalskie strony Dachser nie wspominają o punktach odbioru ani automatach paczkowych dla odbiorców, ani o awizie, liczbie prób czy czasie przechowywania.

**Nieudana dostawa.** Zgodnie z warunkami Dachser w Hiszpanii i Portugalii dostawa nieudana z powodu nieobecności, błędnego lub niepełnego adresu bądź odmowy przyjęcia obciąża nadawcę, który pokrywa koszty. Drugie podejście jest płatną usługą dodatkową dla nadawcy. Jeśli nie było cię w domu, szybko powiadom sklep.

## Dostawa Dachser opóźniona, uszkodzona lub zaginiona

Zakupu dokonałeś w sklepie, a umowę z Dachser ma sklep lub nadawca, więc w sprawie opóźnień, zaginięć i zwrotów najpierw kontaktuj się z nimi.

**Brak aktualizacji.** Sprawdź, czy nie masz nieodebranego połączenia od Dachser, a potem zobacz [dlaczego śledzenie się nie aktualizuje](guide:tracking-not-updating).

**Uszkodzenia.** Warunki Dachser wymagają opisania utraty lub szkody na piśmie w chwili wydania towaru: obejrzyj przesyłkę zanim kierowca odjedzie i zapisz ewentualne uszkodzenia na dokumentach dostawy. W umowie między Dachser a jego klientem w Hiszpanii i Portugalii obowiązują takie terminy:

- **Szkody ukryte:** w ciągu 7 dni kalendarzowych od dostawy w transporcie krajowym; w międzynarodowym 7 dni bez niedziel i świąt.
- **Opóźnienie:** pisemne zastrzeżenia w ciągu 21 dni od dnia następującego po dostawie (transport krajowy).

Zachowaj towar w oryginalnym opakowaniu do wglądu Dachser i powiadom sklep tego samego dnia.

**Dostarczona, ale jej nie masz.** Poproś sklep o sprawdzenie dowodu doręczenia i zobacz [paczka doręczona, ale nieodebrana](guide:delivered-not-received).

## Telefon i obsługa klienta Dachser

Dachser nie podaje osobnej infolinii dla odbiorców. Na oficjalnych stronach widnieją następujące dane kontaktowe:

- **Hiszpania:** DACHSER Spain, S.A., Regional Head Office Madrid, Coslada: +34 916 60 65 78, dachser-spain.iberia@dachser.com. Nie podano godzin ani kosztu połączenia.
- **Portugalia:** DACHSER Portugal, Vila do Conde: +351 221 210 500 (połączenie na krajową sieć stacjonarną), ten sam adres e-mail. Godzin nie podano.
- **Formularze i oddziały:** zarówno dachser.es, jak i dachser.pt udostępniają formularz kontaktowy, a dachser.es ma wyszukiwarkę oddziałów.
- **Reklamacje w Portugalii:** dachser.pt linkuje do oficjalnej księgi skarg (*Livro de Reclamações*).
- **Niemcy:** DACHSER SE, Kempten: +49 831 5916 0, info@dachser.com.

## Śledzenie dostawy Dachser w Peek

[Peek](/) śledzi ponad 4500 przewoźników, ale nie potrafi rozpoznać numeru Dachser po samych cyfrach ani odczytać go bez klucza: wklej więc pełny link z wiadomości, który Peek rozpozna jako Dachser i zapisze. Jeśli wpisałeś sam numer, kliknij „Zmień” (lub „Zmień przewoźnika”), wybierz Dachser i wklej link w polu „Pełny link śledzenia”. Link z innym adresem, bez klucza, z nieznanym parametrem lub do innego numeru zostanie odrzucony komunikatem „Użyj pełnego linku śledzenia z wiadomości o wysyłce.”.

Peek odczytuje wyłącznie stronę Customer Iberia, obejmuje więc dostawy Dachser w Hiszpanii i Portugalii. Wyświetla status, historię oraz przewidywany dzień dostawy do czasu wyjścia do doręczenia. Każdy krok pojawia się we własnym brzmieniu Peek — na przykład „Otrzymano dane przesyłki”, „Przesyłka przyjęta przez przewoźnika”, „Przesyłka dotarła do placówki Dachser”, „W doręczeniu”, „Zaktualizowano termin doręczenia” albo „Dostarczona” — bez zachowywania oryginalnego tekstu Dachser. Godziny odczytywane są w strefie czasowej podanej przez Dachser, a gdy jej brak — według czasu madryckiego, i wyświetlane w strefie czasowej twojego urządzenia. Dachser nie przekazuje miejscowości, więc na mapie niewiele można zaznaczyć. Nadawca, dane kontaktowe i podpis są pomijane (a Peek nie wyświetla danych osobowych odbiorcy). Gdy przesyłka stanie, Peek pokazuje link „Poproś przewoźnika Dachser o pomoc”.

Dopóki śledzenie Dachser odpowiada, Peek sprawdza nawet co 10 minut, gdy masz przesyłkę otwartą na ekranie albo z włączonymi powiadomieniami w spodziewanym dniu dostawy. Od 6:00 na zegarze przewoźnika w dniu wyjścia do doręczenia sprawdzanie może przyspieszyć do co 2 minut przy otwartej przesyłce na ekranie lub włączonych alertach (jeśli podano przedział czasowy, szybsze sprawdzanie zaczyna się najwcześniej pół godziny przed jego początkiem). W tle bez powiadomień oraz po dwóch dniach bez nowych informacji (po 12 godzinach, gdy przesyłka ma wciąż status „Zarejestrowana” lub „Przyjęta przez przewoźnika”) Peek sprawdza co godzinę. Gdy odpowiada wyłącznie usługa śledzenia, Peek sprawdza co godzinę (co 3 godziny, gdy ostatni skan ma ponad dobę), albo nawet co 15 minut w dniu wyjścia do doręczenia przy otwartej przesyłce lub włączonych powiadomieniach. Powiadomienia możesz ustawić na „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Nieznana przesyłka albo uszkodzony link mogą pojawić się jako nieznalezione lub nieudane sprawdzenie.

## Pytania o śledzenie Dachser

### Gdzie znajduje się siedziba Dachser?

W Kempten w Niemczech: DACHSER SE to rodzinna grupa założona przez Thomasa Dachsera w 1930 roku. Regionalna siedziba DACHSER Spain mieści się w Cosladzie pod Madrytem, a DACHSER Portugal w Vila do Conde.

### Czy Dachser doręcza do domów?

Tak, gdy sklep wykupi taką usługę: targo on-site doręcza do odbiorców prywatnych po wcześniejszym umówieniu, pod krawężnik, rano lub po południu. Wniesienie do miejsca użytkowania to targo on-site plus, które wybiera sklep.

### Czy mogę zmienić datę dostawy Dachser?

Czasem tak. Jeśli strona Customer Iberia pokazuje przycisk wyboru daty („Arrange delivery date.” / „Concertar Fecha”), wybierz tam dzień i przedział godzinowy; w przeciwnym razie odbierz telefon od Dachser lub skontaktuj się ze sklepem.

:::sources
- [DACHSER Spain: Condiciones generales European Logistics](https://www.dachser.es/downloads/Spain/Spain_Terms_Conditions.zip) – umawianie telefoniczne, niedziele, soboty, orientacyjne terminy, koszty nieudanej dostawy, usługi dodatkowe, reklamacje
- [DACHSER Portugal: Condições gerais de transporte](https://www.dachser.pt/downloads/Portugal/Portugal_Terms_Conditions.zip) – orientacyjne terminy, koszty nieudanej dostawy, usługi dodatkowe, reklamacje
- [DACHSER Spain: targo on-site fix permite fijar una fecha de entrega al realizar un pedido](https://www.dachser.es/es/mediaroom/DACHSER-amplia-su-cartera-de-servicios-para-entregas-B2C-24216) – targo on-site, krawężnik, plus, fix
- [DACHSER Portugal: DACHSER disponibiliza serviço entargo em Portugal e Espanha](https://www.dachser.pt/pt/mediaroom/DACHSER-disponibiliza-servico-entargo-em-Portugal-e-Espanha-16917) – dostawa po umówieniu rano i po południu
- [DACHSER Spain: eLogistics](https://www.dachser.es/es/elogistics-116) – link śledzenia udostępniany klientom
- [DACHSER Spain: Información legal](https://www.dachser.es/es/informacion-legal-273) – spółka, Coslada, telefon, e-mail, DACHSER SE
- [DACHSER Portugal: Aviso legal](https://www.dachser.pt/pt/aviso-legal-273) – spółka, Vila do Conde, telefon, e-mail
- [DACHSER Spain: Contacto](https://www.dachser.es/es/contacto-275) – formularz kontaktowy
- [DACHSER Portugal: Contacto](https://www.dachser.pt/pt/contacto-275) – formularz kontaktowy
- [DACHSER Spain: Home](https://www.dachser.es/es/) – Track & Trace otwiera shipmentpointer
- [DACHSER Portugal: Home](https://www.dachser.pt/pt/) – Track & Trace, Livro de Reclamações
- [Dachser Customer Iberia: publiczna strona przesyłki](https://customeriberia.dachser.com/customerarea/utilidades/seguimiento-publico/detalle) – strona otwierana przez link
- [Dachser Customer Iberia: angielskie teksty strony](https://customeriberia.dachser.com/customerarea/assets/i18n/common/en.json) – klucze wyszukiwania, tabela historii, Arrange delivery date
- [Dachser Customer Iberia: hiszpańskie teksty strony](https://customeriberia.dachser.com/customerarea/assets/i18n/common/es.json) – N.º Único, Historial de Incidencias
- [Dachser: pomoc shipmentpointer (polski)](https://elogistics.dachser.com/help/pl/shp.html) – polskie opisy statusów i przykłady
- [Dachser: Company](https://www.dachser.com/en/company-10) – Kempten, 1930, firma rodzinna
- [Universal Parcel Scraper: Dachser](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dachser) – wymagany pełny link, odczytywane i pomijane pola, czas madrycki, link pomocy
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – co odczytuje i pokazuje Peek
:::
