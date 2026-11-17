---
title: Śledzenie przesyłek GEODIS: numer 1G, statusy i kontakt
description: Jak śledzić przesyłkę GEODIS z numerem 1G (a kiedy wybrać GEODIS Polska): co oznaczają francuskie statusy, zmiana terminu dostawy, odbiór i kontakt.
slug: geodis-sledzenie
published: 2026-11-17
updated: 2026-11-17
---

GEODIS to francuska grupa transportowo-logistyczna, od 2008 roku należąca w 100% do państwowych kolei francuskich SNCF. Ten przewodnik dotyczy francuskiej i europejskiej sieci paczkowo-paletowej GEODIS (*Distribution & Express*), w której numery śledzenia zaczynają się od `1G`: taką przesyłkę sprawdzisz bez logowania w strefie odbiorcy (*Espace destinataire*) pod adresem [espace-client.geodis.com/services/destinataires/](https://espace-client.geodis.com/services/destinataires/) albo wklejając numer w pole powyżej. Jeśli natomiast Twój numer zaczyna się od `PL` i 16 cyfr (dawny Pekaes, Strefa Klienta), przejdź do przewodnika [GEODIS Polska](carrier:geodis-pl). Portal *Mon espace GEODIS* służy nadawcom posiadającym konto.

## Numer przesyłki GEODIS: format, gdzie go szukać i prawdziwe powiadomienia

Według GEODIS numer śledzenia przekazuje Ci nadawca albo sklep internetowy – zazwyczaj w e-mailu z potwierdzeniem zamówienia lub w powiadomieniu o wysyłce. Gdy kierowca nie zastanie Cię pod adresem, wpisuje numer `1G` również na awizie zostawionym w skrzynce. Nie możesz go znaleźć? Zapytaj sprzedawcę albo sprawdź poradnik [gdzie znaleźć numer przesyłki](guide:find-tracking-number).

Jeżeli nadawca włączył powiadomienia, GEODIS wysyła też SMS lub e-mail dzień albo dwa dni przed doręczeniem oraz drugą wiadomość rano w dniu dostawy. Prawdziwe wiadomości z sieci `1G` zawierają krótki link w postaci `edesti.com/1G…` (w FAQ GEODIS podano przykład `edesti.com/1G123GEODIS0`), prowadzący bezpośrednio do *Espace destinataire*. Jeśli masz wątpliwości co do SMS-a, wpisz numer `1G` samodzielnie na stronie `espace-client.geodis.com/services/destinataires/` i zobacz, [jak rozpoznać fałszywe SMS-y o paczce](guide:delivery-scam-texts).

W sieci GEODIS spotkasz te formaty numerów (przykłady są zmyślone):

| Format (zmyślony) | Co oznacza |
| --- | --- |
| `1GGDXF0B27F1` | **Numer śledzenia 1G** (*Numéro de suivi 1G*): 12 wielkich liter i cyfr zaczynających się od `1G`. Wystarcza sam w sobie zarówno w *Espace destinataire*, jak i w Peek. |
| `19847263510492` | **14-cyfrowy numer przesyłki lub listu** (*n° de récépissé* / *numéro d'envoi*): 14 cyfr. W *Espace destinataire* działa tylko razem z kodem pocztowym odbiorcy; Peek nie przypisuje go do GEODIS. |
| `PL9482716350492817` | **PL i 16 cyfr:** krajowa i drogowa sieć [GEODIS Polska](carrier:geodis-pl) (dawny Pekaes) – obsługiwana w polskiej Strefie Klienta oraz w Peek jako osobny przewoźnik. |

Ogólna wyszukiwarka [Śledź swoją przesyłkę na geodis.com](https://geodis.com/pl-pl/sledz-swoja-przesylke) obsługuje fracht lotniczy, morski i dostawy ostatniej mili, natomiast przy transporcie drogowym odsyła do osobnego narzędzia. Ośmiocyfrowy numer w Peek nie należy do GEODIS – może dotyczyć np. Heppner albo [Mondial Relay](carrier:mondial-relay), więc sprawdź nazwę przewoźnika w e-mailu ze sklepu lub porównaj [formaty numerów przesyłek](guide:tracking-number-formats). Zgodnie z komunikatem błędu w *Espace destinataire* przesyłki starsze niż 12 miesięcy nie są już wyszukiwane.

## Statusy przesyłek GEODIS: co oznaczają komunikaty po francusku

*Espace destinataire* otwiera się po francusku i pozwala przełączyć język na angielski, hiszpański lub włoski (polskiej wersji językowej nie ma). Pokazuje czteroetapową oś czasu z podświetlonym bieżącym krokiem oraz – gdy dostępne są datowane skany – historię transportu (*Historique d'acheminement*) wraz z oddziałem GEODIS każdego skanu. Niektóre przesyłki wyświetlają wyłącznie samą oś czasu.

Ponieważ Peek pobiera francuską wersję śledzenia GEODIS, w historii zobaczysz oryginalne opisy po francusku (pierwsza kolumna); obok podajemy angielskie nazwy czterech głównych kroków na stronie GEODIS:

| GEODIS (francuski) | Wersja angielska GEODIS | Co to dla Ciebie znaczy |
| --- | --- | --- |
| `En préparation chez l'expéditeur` | `In preparation at the shipper's` | Nadawca zarejestrował wysyłkę, ale GEODIS jeszcze jej nie odebrał. |
| `En attente de prise en charge` | – | Sklep nie przekazał jeszcze fizycznie towaru przewoźnikowi GEODIS. |
| `Envoi pris en charge` | – | GEODIS odebrał przesyłkę od nadawcy. |
| `En cours d’acheminement` | `In transit` | Przesyłka jest w drodze między oddziałami i sortowniami GEODIS. |
| `Mise en livraison` | `Delivery in progress` | Przygotowana do wydania kurierowi na trasę doręczeń. |
| `En cours de livraison` | – | W samochodzie kierowcy w trakcie doręczania. |
| `Disponible pour retrait en agence` | – | Gotowa do odbioru osobistego we wskazanym oddziale GEODIS. |
| `Expédition livrée` | `Delivered shipment` | Doręczona; na stronie data pojawia się po nagłówku *„Expédition livrée le :”*. |
| `Livraison effectuée` | – | Doręczenie wykonane (w Peek wyświetlane jako „Dostarczona”). |

Kilka wskazówek przy czytaniu statusów:

- `Mise en livraison` (`Delivery in progress`) oznacza, że paczka jest w doręczeniu u kierowcy, a nie że została już wydana.
- Jeśli status przez wiele dni stoi na `En préparation chez l'expéditeur` lub `En attente de prise en charge`, sprzedawca nie wydał jeszcze paczki GEODIS – skontaktuj się ze sklepem, nie z przewoźnikiem.
- W dniu doręczenia portal może pokazać mapę kierowcy na żywo z komunikatami takimi jak *„Le conducteur est en route”* (`The driver is on the way`), *„Vous êtes le prochain”* (`You are the next`) i *„Le conducteur arrive !”* (`The driver is coming !`).
- Komunikat *„Vous n'avez pas encore planifié votre RDV”* (`You have not yet scheduled your appointment`) informuje, że GEODIS czeka, aż wybierzesz termin dostawy w portalu.
- Gdy paczka czeka na odbiór, licznik pokazuje pozostałe dni (*„You have … day(s) left to pick up your shipment”*), a osobny komunikat uprzedza o zwrocie do nadawcy. Więcej o etapach znajdziesz w poradniku [statusy przesyłek – wyjaśnienie](guide:tracking-statuses).

## Dostawa GEODIS: zmiana terminu, odbiór w oddziale i inne opcje

Strefa odbiorcy wyświetla tylko te opcje, które są aktywne dla danej przesyłki. W usłudze standardowej datę lub sposób dostawy zmienisz dopiero po pierwszej nieudanej próbie doręczenia; jeśli nadawca wykupił opcję *On Demand*, zmiana jest dostępna od pierwszego SMS-a lub e-maila. Po zatwierdzeniu nowego wyboru nie da się już wrócić do pierwotnej dostawy zaplanowanej na ten sam dzień. W zależności od przesyłki GEODIS udostępnia:

- **Nowy termin dostawy do domu**, wraz z przedziałem godzinowym, o ile jest oferowany. Rano w dniu dostawy – przy włączonych powiadomieniach – GEODIS przesyła 2-godzinne okno w usłudze ekspresowej lub 3-godzinne w standardowej; według FAQ GEODIS przy dostawach prywatnych kierowcy zazwyczaj dzwonią trzy przystanki przed Twoim adresem.
- **Odbiór w oddziale** (*retrait en agence*) w jednym ze 110 lokalnych oddziałów francuskiej sieci, jeśli jest dostępny dla Twojej okolicy. Każdy oddział ma punkt odbioru, a strona podaje godziny jego otwarcia. Zabierz dokument tożsamości oraz SMS lub e-mail od GEODIS. Gdy paczkę odbiera pełnomocnik, musi mieć wypełniony formularz upoważnienia GEODIS, swój oryginalny dokument tożsamości, Twój oryginalny dokument oraz wiadomość od GEODIS; przy przesyłkach na firmę bez wskazanej osoby kontaktowej GEODIS wymaga wypisu z rejestru (*Kbis*) młodszego niż 3 miesiące i upoważnienia reprezentanta prawnego.
- **Sąsiad lub dozorca** (*voisin / gardien*), wskazany przez Ciebie z imienia, nazwiska i numeru telefonu, który pokwituje odbiór pod Twoją nieobecność.
- **Bezpieczne miejsce** (*lieu sûr*), np. podest na klatce, garaż lub ogród: kierowca zostawia tam paczkę i robi zdjęcie.
- **Punkt sąsiedzki Pickme** (*Voisin Pickme*): paczka trafia do zarejestrowanego sąsiada od następnego dnia roboczego, a punkt sam informuje Cię, gdy jest gotowa do odbioru. Przy starcie usługi w grudniu 2024 r. GEODIS podawał 14 dni na odbiór z tajnym kodem.

Węższe okno godzinowe lub wniesienie na piętro są możliwe wyłącznie wtedy, gdy nadawca zamówił taką usługę (dla wniesienia portal podaje limit poniżej 70 kg do obsługi przez jednego kierowcę, sumę wymiarów poniżej 2 m oraz obowiązkową windę od pierwszego piętra); palety domyślnie zostawiane są przy bramie lub wejściu do budynku. Po nieudanej próbie doręczenia francuski oddział przechowuje przesyłkę przez 8 dni kalendarzowych według FAQ GEODIS, a bez dyspozycji odsyła ją do nadawcy (zobacz [nieudana próba doręczenia](guide:missed-delivery)). Liczby prób doręczenia GEODIS nie publikuje. Jeśli adres lub telefon są błędne, prześlij poprawkę przez formularz kontaktowy na geodis.com lub poproś o to sklep.

## Przesyłka GEODIS opóźniona, uszkodzona lub niedoręczona

Umowę przewozu z GEODIS zawiera nadawca, dlatego w razie problemów pierwszym kontaktem pozostaje zwykle sklep internetowy.

**Opóźnienie lub brak ruchu w śledzeniu.** Dla swojej sieci paczek i palet GEODIS podaje orientacyjnie 24–48 godzin we Francji w usłudze standardowej i następny dzień przed południem w ekspresie, a w Europie 1–2 dni w ekspresie i 3–5 dni w standardzie. Ogólne warunki sprzedaży (*Conditions générales de vente*, maj 2025) zaznaczają jednak, że terminy dostaw nie są gwarantowane. Jeśli śledzenie nie zmienia się od kilku dni, przeczytaj [śledzenie przesyłki się nie aktualizuje](guide:tracking-not-updating) i napisz do sprzedawcy.

**Numer nie działa.** Upewnij się, że format jest poprawny (`1G` i 10 znaków), a w razie dalszych problemów skontaktuj się z nadawcą lub sklepem.

**Oczekiwanie na instrukcje.** Gdy doręczenie nie jest możliwe, warunki GEODIS stanowią, że przewoźnik zwraca się o dyspozycje do zleceniodawcy. Skontaktuj się ze sprzedawcą.

**Uszkodzenie lub brak części towaru.** Sprawdź stan przesyłki w obecności kierowcy i wpisz konkretne, uzasadnione zastrzeżenia (*réserves précises et motivées*) na papierowym lub elektronicznym liście przewozowym. Strefa odbiorcy przypomina, że prawo przewozowe pozwala zgłosić szkody lub braki na piśmie w ciągu trzech dni od dostawy; bez prawidłowych zastrzeżeń warunki GEODIS wykluczają roszczenia wobec przewoźnika. Szczegóły znajdziesz w poradniku [uszkodzona paczka](guide:damaged-parcel) – równolegle powiadom sprzedawcę.

**Reklamacja konsumencka.** Francuskie warunki GEODIS proszą konsumentów o kontakt najpierw ze sprzedawcą, następnie z działem obsługi klienta właściwej spółki GEODIS lub mailowo pod adresem `dpm@geodis.com`. W razie braku odpowiedzi w ciągu 2 miesięcy albo nierozwiązanego sporu GEODIS wskazuje francuski podmiot mediacji konsumenckiej *Médiation de la consommation AME Conso* (197 Boulevard Saint Germain, 75007 Paryż).

## Kontakt i obsługa klienta GEODIS

Dla odbiorców przesyłek `1G` GEODIS nie publikuje numeru telefonu (numery telefonów do polskiej sieci krajowej znajdziesz w przewodniku [GEODIS Polska](carrier:geodis-pl)). Dostępne kanały dla przesyłek `1G`:

- **Strefa odbiorcy:** [espace-client.geodis.com/services/destinataires/](https://espace-client.geodis.com/services/destinataires/) z wbudowanym menu pomocy (*Aide* / *help*).
- **Formularz kontaktowy dla odbiorców:** na [angielskiej stronie kontaktowej](https://geodis.com/contact-us) wybierz *Customer support* i zaznacz „a recipient” albo na [francuskiej stronie kontaktowej](https://geodis.com/fr/nous-contacter) wybierz *Assistance clientèle* i „un destinataire”, podając numer śledzenia (formularz na [geodis.com/pl-pl/skontaktuj-sie-z-nami](https://geodis.com/pl-pl/skontaktuj-sie-z-nami) służy wyłącznie zapytaniom biznesowym o wycenę i kontakt z ekspertem). Według francuskiego FAQ dla odbiorców najbliższy ze 110 działów obsługi klienta odpowiada w ciągu 12 godzin roboczych.
- **E-mail do reklamacji konsumenckich:** `dpm@geodis.com` zgodnie z ogólnymi warunkami sprzedaży.
- **Oddziały we Francji:** nazwy i adresy sprawdzisz na [liście agencji](https://geodis.com/fr/liste-des-agences?territory=FR), obejmującej również oddziały sieci France Express należącej do GEODIS.

## Śledzenie przesyłek GEODIS w Peek

Wklej numer `1G` w [Peek](/), a Peek od razu rozpozna GEODIS, bo żaden inny przewoźnik nie używa tego prefiksu. Peek przyjmuje także linki do `espace-client.geodis.com/services/destinataires/` (z `noSuivi=`, `trackingNumber=` lub `#/.../suivi/...`), linki do `espace-client.france-express.com/#/.../suivi/...` oraz link `edesti.com/1G…` lub pełną treść SMS-a od GEODIS zawierającą numer `1G`. 14-cyfrowego numeru przesyłki Peek nie śledzi jako GEODIS; użyj kodu `1G` (natomiast numery `PL` z 16 cyframi Peek kieruje do [GEODIS Polska](carrier:geodis-pl)).

Peek odczytuje francuskie śledzenie GEODIS, wyświetla oryginalne francuskie opisy (przy czym `Livraison effectuée` pokazuje jako „Dostarczona”) i zaznacza na mapie oddział GEODIS każdego skanu. Godziny odczytuje według czasu paryskiego i przelicza na strefę czasową Twojego urządzenia, pokazując też przewidywaną datę doręczenia, nadawcę, nazwę usługi i wagę. Jeśli najnowszy skan informuje o nieudanej próbie doręczenia, problemie, zwrocie lub gotowości do odbioru, ma on pierwszeństwo przed krokiem na osi czasu; gdy GEODIS zwraca same kroki bez datowanych skanów, Peek zapisuje czas pierwszego odczytu danego statusu. Pomija liczbę paczek, Twoje imię, nazwisko i adres, instrukcje dostawy oraz dokumenty doręczenia, a przesyłki starsze niż 12 miesięcy traktuje jako nieznalezione.

Peek sprawdza status do każdych 10 minut, dopóki ktoś czeka na przesyłkę, a w pozostałym czasie co godzinę – tak samo po dwóch dniach bez nowego skanu lub po 12 godzinach na etapie „Zarejestrowana” bądź „Przyjęta przez przewoźnika”. W dniu doręczenia sprawdza do każdych 2 minut od 6:00 czasu lokalnego, gdy paczka jest w doręczeniu i odpowiada własny system GEODIS; gdy odpowiadają wyłącznie zewnętrzne serwisy śledzące, sprawdza co godzinę, co 3 godziny po dobie bez nowego skanu i do każdych 15 minut w dniu dostawy. Powiadomienia możesz ustawić na „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek nie zmienia terminu dostawy – zrobisz to w *Espace destinataire*.

## Najczęstsze pytania o śledzenie GEODIS

### Czym różni się numer 1G od numeru GEODIS Polska z PL?

Numer z `1G` i 10 znakami pochodzi z francuskiej sieci paczkowo-paletowej GEODIS i śledzi się go w *Espace destinataire*. Numer z `PL` i 16 cyframi należy do sieci [GEODIS Polska](carrier:geodis-pl) (dawny Pekaes) i działa w polskiej Strefie Klienta; Peek obsługuje oba formaty osobno.

### Czym jest „Mon espace GEODIS”?

To portal klienta pod adresem [espace-client.geodis.com/index](https://espace-client.geodis.com/index/) dla klientów nadających przesyłki w GEODIS, którzy logują się loginem i hasłem, by zarządzać odbiorami i wysyłkami. Jeśli czekasz na dostawę jako odbiorca, strona odsyła Cię wprost do *Espace destinataire*, gdzie wystarczy sam numer `1G`.

### Co łączy GEODIS, Calberson i France Express?

Korzenie grupy sięgają firmy założonej przez Émile’a Calbersona w 1904 roku; pod koniec 1995 roku grupa przyjęła nazwę GEODIS, od 2008 roku należy w 100% do SNCF, a w 2015 roku połączyła swoje marki pod szyldem GEODIS. France Express to jedna z sieci ekspresowych GEODIS z własną [strefą odbiorcy](https://espace-client.france-express.com/services/destinataire).

### W jakich dniach i godzinach dostarcza GEODIS?

Według FAQ GEODIS standardowe doręczenia odbywają się zazwyczaj od poniedziałku do piątku między 8:00 a 18:00. Dostawa w sobotę między 8:00 a 13:00 jest realizowana, jeśli nadawca wybrał taką opcję; w niedziele i święta GEODIS nie doręcza.

:::sources
- [GEODIS: Espace destinataire](https://espace-client.geodis.com/services/destinataires/) – pola wyszukiwania, języki, oś czasu i komunikaty na żywo, opcje dostawy, pełnomocnictwo, wniesienie na piętro, zasada trzech dni i limit 12 miesięcy
- [GEODIS: Mon espace GEODIS](https://espace-client.geodis.com/index/) – portal dla nadawców i przekierowanie do strefy odbiorcy
- [GEODIS: Śledź swoją przesyłkę (PL)](https://geodis.com/pl-pl/sledz-swoja-przesylke) – gdzie znaleźć numer, śledzenie lotnicze/morskie/ostatniej mili i odesłanie przy transporcie drogowym
- [GEODIS: Skontaktuj się z nami (PL)](https://geodis.com/pl-pl/skontaktuj-sie-z-nami) – polski formularz B2B dla wycen i kontaktu z ekspertem
- [GEODIS: Contact us (EN)](https://geodis.com/contact-us) – wybór „a recipient” w formularzu wsparcia, punkty odbioru, palety i wniesienie
- [GEODIS: Nous contacter (FR)](https://geodis.com/fr/nous-contacter) – formularz odbiorcy, korekta adresu i wymóg dokumentu tożsamości
- [GEODIS: FAQ – Dostęp do Espace Client](https://geodis.com/fr/faq?question=comment-acceder-a-mon-espace-client) – logowanie i zarządzanie wysyłkami dla nadawców
- [GEODIS: FAQ – Godziny dostaw i weekendy](https://geodis.com/fr/faq?question=peut-on-etre-livre-dehors-heures-bureau-jour-ferie-dimanche) – pon.–pt. 8:00–18:00, sob. 8:00–13:00 jako opcja, brak dostaw w niedziele i święta
- [GEODIS: FAQ – Numer przesyłki dla odbiorcy](https://geodis.com/fr/faq?question=qu-est-ce-qu-un-numero-d-envoi-et-ou-le-trouver-lorsqu-on-est-destinataire) – kod 1G, awizo, link edesti.com i 14-cyfrowy numer listu
- [GEODIS: FAQ – En attente de prise en charge](https://geodis.com/fr/faq?question=que-signifie-en-attente-de-prise-en-charge-sur-le-suivi-de-ma-livraison-50000) – towar wciąż u sprzedawcy
- [GEODIS: FAQ – Przedział czasowy dostawy](https://geodis.com/fr/faq?question=quand-serai-je-informe-e-du-creneau-horaire-de-ma-livraison) – powiadomienia, okna 2 h i 3 h oraz telefon trzy przystanki wcześniej
- [GEODIS: FAQ – Zmiana daty dostawy](https://geodis.com/fr/faq?question=comment-modifier-la-date-de-livraison-prevue) – standard po pierwszej próbie, On Demand od razu i 110 agencji
- [GEODIS: FAQ – Przechowywanie w oddziale](https://geodis.com/fr/faq?question=pendant-combien-de-temps-mon-envoi-est-il-conserve-en-agence) – 8 dni kalendarzowych w oddziale
- [GEODIS: FAQ – Kontakt z obsługą odbiorców](https://geodis.com/fr/faq?question=comment-contacter-le-service-relation-client-destinataire) – bezpłatny formularz i odpowiedź w 12 godzin roboczych
- [GEODIS: Votre espace de travail (FR)](https://geodis.com/fr/votre-espace-de-travail) – Espace Client dla sieci Distribution & Express
- [GEODIS: Transport de colis et de palettes](https://geodis.com/fr/transport-de-marchandises/transport-de-colis-et-de-palettes) – orientacyjne czasy we Francji i w Europie oraz opcja sobotnia
- [GEODIS: Conditions générales de vente, maj 2025 (PDF)](https://geodis.com/sites/default/files/widen-migration/2glqzqlnw7/CGV_FR-052025.pdf) – brak gwarancji terminów, instrukcje, zastrzeżenia, dpm@geodis.com i mediacja AME
- [GEODIS: Współpraca z Pickme](https://geodis.com/fr/newsroom/geodis-sappuie-sur-pickme-pour-elargir-son-offre-de-livraison-en-point-de-retrait-dans) – punkty sąsiedzkie, 14 dni i tajny kod
- [GEODIS: Notre histoire](https://geodis.com/fr/notre-histoire) – Calberson 1904, nazwa GEODIS w 1995, SNCF w 2008 i wspólna marka w 2015
- [GEODIS: Liste des agences](https://geodis.com/fr/liste-des-agences?territory=FR) – oddziały GEODIS i France Express
- [France Express: Strona główna](https://france-express.com/) – dostęp do strefy odbiorcy France Express
- [France Express: Espace destinataire](https://espace-client.france-express.com/services/destinataire) – strefa śledzenia France Express dla odbiorców
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – co Peek odczytuje i pokazuje dla GEODIS
:::
