---
title: Śledzenie Colissimo i La Poste: statusy, doręczenie i cło
description: Śledź paczkę Colissimo lub La Poste z Francji: formaty numerów, znaczenie francuskich statusów po polsku, doręczenie w Polsce, reklamacje i kontakt.
slug: sledzenie-colissimo-la-poste
published: 2026-11-18
updated: 2026-11-20
---

Colissimo to usługa paczkowa La Poste, narodowego operatora pocztowego Francji. Jeśli czekasz w Polsce na paczkę lub przesyłkę poleconą nadaną we Francji, wklej numer śledzenia – albo link z `laposte.fr` – w pole powyżej. Własna wyszukiwarka La Poste działa na `laposte.fr`, a numer dostajesz od nadawcy, najczęściej w wiadomości e-mail ze sklepu potwierdzającej wysyłkę.

## Numer śledzenia Colissimo i La Poste: jak wygląda

Na całkowicie zmyślonych przykładach główne formaty La Poste wyglądają tak:

| Format (wymyślony) | Co oznacza |
| --- | --- |
| `6A48291057365` | 1 cyfra, 1 litera i 11 cyfr: paczka Colissimo albo list rejestrowany lub śledzony. Ostatnia cyfra jest cyfrą kontrolną. |
| `LA482910576FR` | 2 litery, 9 cyfr i `FR`: międzynarodowy numer pocztowy wydany we Francji dla paczek Colissimo oraz listów śledzonych i poleconych. Cyfra przed `FR` to cyfra kontrolna. |
| `87048291057364R` | 15 znaków zaczynających się od `863`–`866`, `870`, `875`, `880` lub `885`: numer Smart Data używany przez La Poste dla listów śledzonych i poleconych. Ostatni znak jest znakiem kontrolnym, przy czym 14 cyfr bez niego również pozwala odnaleźć przesyłkę. |

Numery pocztowe zaczynające się od `XU`, `XR`, `XT`, `XW`, `XY` lub `PZ` i kończące na `FR` należą do [Chronopost](carrier:chronopost), kuriera ekspresowego z tej samej grupy. Dwunastoznakowe kody wskazują zazwyczaj na Colis Privé, a sam ciąg 14 cyfr stosują również [DPD Polska](carrier:dpd-pl), [DPD Germany](carrier:dpd-de) i inni przewoźnicy – sprawdź nazwę firmy w wiadomości od sprzedawcy albo zajrzyj do poradnika [formaty numerów śledzenia](guide:tracking-number-formats). Nie możesz znaleźć numeru? Zobacz, [gdzie szukać numeru przesyłki](guide:find-tracking-number).

## Statusy śledzenia Colissimo po polsku

Peek wyświetla komunikaty La Poste dokładnie tak, jak publikuje je francuski przewoźnik, i przypisuje przesyłce czytelny etap po polsku na podstawie najnowszego zdarzenia. Oto typowe francuskie wpisy w kolejności drogi paczki (komunikaty doręczeniowe dotyczą sieci we Francji):

| La Poste pisze | Peek pokazuje | Co to dla ciebie znaczy |
| --- | --- | --- |
| `Votre Colissimo va bientôt nous être confié ! Il est en cours de préparation chez votre expéditeur.` | Zarejestrowana | Nadawca przygotowuje paczkę; La Poste jeszcze jej nie odebrała. |
| `Votre colis a été déposé dans un point postal.` | Przyjęta przez przewoźnika | Nadawca nadał paczkę w placówce lub punkcie pocztowym. |
| `Votre colis est en transit sur nos plateformes logistiques.` | W transporcie | Przesyłka przemieszcza się między sortowniami logistycznymi. |
| `Les formalités import/export sont en cours sur votre colis.` | W urzędzie celnym | Trwają formalności celne importowe lub eksportowe. |
| `Votre Colissimo est en cours de traitement sur le site de tri local.` | W transporcie | Przesyłka jest opracowywana w lokalnej sortowni. |
| `Votre colis est sur son site de distribution. Nous le préparons pour le mettre en livraison.` | W doręczeniu | Paczka dotarła do bazy doręczeniowej i jest szykowana do dzisiejszego wyjazdu kuriera. |
| `Votre colis n'a pas pu vous être remis. Il sera mis en livraison demain (hors dimanche et jours fériés).` | Próba doręczenia | Nie udało się doręczyć przesyłki; kolejna próba nastąpi następnego dnia roboczego (z wyłączeniem niedziel i świąt). |
| `Nous sommes passés mais nous n'avons pu vous remettre votre colis. Il va être acheminé vers votre point de retrait.` | Próba doręczenia | Nikogo nie zastano; paczka jedzie do punktu odbioru. |
| `Votre colis est disponible dans votre point de retrait pendant un délai de 15 jours calendaires.` | Do odbioru | Czeka w punkcie odbioru przez czas podany w komunikacie (np. 15 dni kalendarzowych lub `Le délai de retrait est de 5 jours.`). |
| `Votre colis est livré dans votre boite aux lettres.` | Dostarczona | Doręczona do skrzynki pocztowej. |
| `Votre colis vous a attendu dans votre point de retrait jusqu'à la date limite. Nous sommes contraints de le renvoyer à l'expéditeur.` | Zgłoszono problem | Nieodebrana w terminie; wraca do nadawcy. |
| `Votre colis est livré à son expéditeur à la suite d'un retour.` | Zwrócona nadawcy | Zwrot dotarł z powrotem do nadawcy. |

Najbardziej mylący bywa wpis `Votre colis est sur son site de distribution…`: brzmi tak, jakby paczka utknęła w magazynie, ale w La Poste oznacza poranne sortowanie na trasę doręczeniową tego samego dnia. Więcej wskazówek znajdziesz w poradnikach [nieudana próba doręczenia](guide:missed-delivery) oraz [nieodebrana paczka](guide:uncollected-parcel).

## Kto doręcza Colissimo w Polsce i co z cłem?

Gdy paczka opuszcza Francję, śledzenie La Poste może podać zagranicznego partnera doręczającego oraz jego numer śledzenia – Peek pokazuje oba te dane na karcie przesyłki. Z kolei przy międzynarodowych numerach pocztowych zakończonych na `FR`, takich jak `LA482910576FR`, z krajem przeznaczenia Polska Peek automatycznie sprawdza przesyłkę także w [Poczcie Polskiej](carrier:poczta-polska) i przełącza widok na jej skany, gdy pojawią się tam nowsze zdarzenia. Jeśli dostaniesz powiadomienie SMS, e-mail lub awizo od [Poczty Polskiej](carrier:poczta-polska), [DPD Polska](carrier:dpd-pl) czy [InPost](carrier:inpost) z krajowym numerem, ostatni odcinek i zasady odbioru w punkcie lub automacie podlegają regulaminowi tego przewoźnika.

**Brak cła z Francji.** Przesyłki wysyłane z europejskiej części Francji do Polski nie przechodzą odprawy celnej, bo oba kraje należą do unijnego obszaru celnego i VAT. Francuskie departamenty zamorskie (Gwadelupa, Gujana Francuska, Martynika, Reunion, Majotta) leżą jednak poza unijnym obszarem VAT, więc od paczki stamtąd może być należny VAT z importu. Cło i VAT dotyczą natomiast zakupów spoza UE: zgodnie z przepisami Komisji Europejskiej i informacjami [Poczty Polskiej](carrier:poczta-polska) od 1 lipca 2026 r. przesyłki spoza UE o wartości do 150 € objęte są zryczałtowanym cłem 3 € za każdą pozycję taryfową (kategorię towaru), a od 1 listopada 2026 r. dochodzi unijna opłata manipulacyjna 2 € za kategorię towaru niezależnie od wartości zamówienia. Szczegóły opisuje poradnik [paczka w urzędzie celnym](guide:customs).

## Paczka stoi w miejscu, zaginęła lub dotarła uszkodzona

**Brak nowych skanów.** Sprawdź, czy przesyłka nie przeszła już pod numer polskiego operatora; jeśli od kilku dni nic się nie zmienia, skontaktuj się z nadawcą i zajrzyj do poradnika [dlaczego śledzenie się nie aktualizuje](guide:tracking-not-updating).

**Zagubiona lub uszkodzona.** Przy zakupach w unijnym sklepie internetowym pierwszym adresem jest sprzedawca: według portalu *Your Europe* odpowiada on za uszkodzenia towaru aż do chwili doręczenia i – o ile nie umówiono innego terminu – musi dostarczyć zamówienie w ciągu 30 dni. Po wyznaczeniu rozsądnego dodatkowego terminu masz prawo odstąpić od umowy i odzyskać pieniądze. Zobacz też [zagubiona paczka](guide:lost-parcel), [uszkodzona paczka](guide:damaged-parcel), [dostarczona, ale nieodebrana](guide:delivered-not-received) i [zwrot do nadawcy](guide:returned-to-sender).

**Reklamacja w La Poste.** Jak wyjaśnia francuski regulator pocztowy Arcep, złożenie reklamacji w La Poste jest bezpłatne i przysługuje zarówno nadawcy, jak i adresatowi (choć zakres ich uprawnień się różni). Jeśli odpowiedź działu obsługi nie rozwiąże sprawy, tymi samymi kanałami możesz poprosić o ponowne rozpatrzenie przez *service recours* w La Poste. Gdy i to nie pomoże albo przez dwa miesiące nie otrzymasz odpowiedzi, w ciągu roku od pierwszej pisemnej reklamacji możesz bezpłatnie zwrócić się do rzecznika *Médiateur du Groupe La Poste*.

## Kontakt z La Poste i Colissimo

Według Arcep oraz francuskiego rządowego katalogu *Service-Public.fr* z obsługą klienta La Poste skontaktujesz się następująco:

- **Z Polski i zza granicy:** `+33 810 821 821` (międzynarodowe biuro obsługi klienta), od poniedziałku do piątku w godz. 8:00–19:00 czasu francuskiego.
- **Z terenu Francji:** `3631`, od poniedziałku do piątku 8:30–19:00 oraz w soboty 8:30–13:00 (usługa bezpłatna, koszt połączenia jak na numer stacjonarny).
- **Online i listownie:** przez formularz na `laposte.fr` albo pocztą na adres `Service consommateurs, 99 999 LA POSTE`.

> **Uwaga na fałszywe SMS-y i e-maile o dopłacie:** Francuski rządowy serwis *Cybermalveillance.gouv.fr* przypomina, że **La Poste we Francji nigdy nie prosi SMS-em ani e-mailem o opłatę za odbiór paczki** (próby phishingu podszywające się pod La Poste można zgłaszać na `phishing@laposte.fr`, a we Francji SMS-y pod numer `33700`). Pamiętaj jednak o zasadach w Polsce przy przesyłkach spoza UE: jak podaje [Poczta Polska](carrier:poczta-polska), należności celno-podatkowe od paczek spoza Unii są pobierane przy doręczeniu przesyłki lub przed jej odbiorem, natomiast SMS-y z linkami do rzekomych dopłat podszywające się pod Pocztex są oszustwem. Zawsze sprawdzaj status bezpośrednio na `laposte.fr`, na oficjalnej stronie polskiego przewoźnika albo w Peek. Więcej w poradniku [fałszywe SMS-y o paczkach](guide:delivery-scam-texts).

## Śledzenie przesyłek Colissimo w Peek

[Peek](/) od razu rozpoznaje La Poste / Colissimo po 13-znakowym numerze Colissimo z poprawną cyfrą kontrolną, 14-cyfrowym numerze zaczynającym się od `870` oraz po 15-znakowym numerze Smart Data i francuskim numerze pocztowym zakończonym na `FR` (poza prefiksami Chronopost) z prawidłowym znakiem kontrolnym. W pozostałych przypadkach – na przykład przy dwóch literach i 11 cyfrach, błędnej cyfrze kontrolnej albo 14 cyfrach używanych też przez [DPD Polska](carrier:dpd-pl) i innych – wklej numer tak samo: Peek obsługuje ponad 4500 przewoźników i odpytuje prawdopodobnych, a w razie potrzeby wybierzesz La Poste ręcznie przyciskiem „Zmień przewoźnika” (lub „Zmień”). Jeśli La Poste odrzuci numer już przy pierwszym sprawdzeniu, Peek wyświetli komunikat: „Ten przewoźnik nie wydaje takich numerów śledzenia. Sprawdź numer lub wybierz innego przewoźnika.” Działają również wklejone linki z `laposte.fr` zawierające `code=`, `idShip=` lub `idship=`.

Komunikaty z La Poste pozostają w oryginalnym języku francuskim. Peek nanosi każdy skan na mapę, pokazuje przewidywaną datę dostawy, nazwę nadawcy, godzinę doręczenia oraz – gdy przesyłka czeka na odbiór – nazwę i adres punktu odbioru, pomijając twoje imię, nazwisko i adres domowy. Godziny z francuskiej strefy `Europe/Paris` przeliczane są na strefę twojego urządzenia z dopiskiem „Godziny w twojej strefie czasowej: …” na `peektracker.com` i w aplikacjach na iPhone'a i Androida.

Gdy masz paczkę otwartą na ekranie albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza status do co 10 minut – a w dniu wydania do doręczenia od 6:00 czasu lokalnego do co 2 minut, dopóki bezpośrednio odpowiada śledzenie La Poste (przez serwisy śledzące: co godzinę, co 3 godziny po dobie bez skanu oraz do co 15 minut w dniu doręczenia przy otwartej paczce lub aktywnych powiadomieniach). W pozostałym czasie oraz po dwóch dniach bez zmian – lub po 12 godzinach na etapie „Zarejestrowana” albo „Przyjęta przez przewoźnika” – sprawdza co godzinę. Gdy doręczenie przejmuje inny operator, Peek pokazuje „Doręcza: …” (a przed jego pierwszym własnym skanem „Zgłoszona przewoźnikowi …, jeszcze nieprzyjęta”) i dopasowuje częstotliwość do przewoźnika doręczającego paczkę: co godzinę dla Royal Mail i Poste Italiane, do co 30 minut dla PostNL i USPS, także w doręczeniu. Powiadomienia ustawisz na „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek nie zmienia opcji dostawy ani nie składa reklamacji w twoim imieniu.

## Najczęstsze pytania o śledzenie Colissimo

### Kto dostarcza paczki Colissimo w Polsce?

Po wyjeździe paczki z Francji La Poste może wskazać w historii partnera doręczającego i jego numer lokalny, które zobaczysz w Peek. Dla międzynarodowych numerów pocztowych zakończonych na `FR` z adresem w Polsce Peek sprawdza również [Pocztę Polską](carrier:poczta-polska). O tym, kto doręcza ostatni odcinek, informuje także SMS lub e-mail od krajowego operatora.

### Czy za paczkę z Francji do Polski płaci się cło?

Z europejskiej części Francji nie: przesyłki przemieszczają się wewnątrz Unii bez odprawy celnej i opłat importowych. Od paczki z francuskich departamentów zamorskich może być należny VAT z importu. Cło (w tym stawka 3 € do 150 € od lipca 2026 r.) oraz opłata manipulacyjna 2 € od listopada 2026 r. dotyczą wyłącznie przesyłek napływających spoza Unii Europejskiej.

### Czy La Poste doręcza paczki w soboty?

We Francji tak – według La Poste Groupe listy i paczki są tam doręczane sześć dni w tygodniu (od poniedziałku do soboty), a w 2025 r. 41 % krajowych paczek Colissimo dotarło już następnego dnia po nadaniu. W Polsce dni doręczenia zależą od harmonogramu operatora, który przejął paczkę na ostatnim odcinku.

:::sources
- [La Poste Groupe: La branche Services-Courrier-Colis](https://www.lapostegroupe.com/fr/services-courrier-colis) – usługa paczkowa Colissimo, doręczenia we Francji 6 dni w tygodniu oraz 41 % doręczeń następnego dnia w 2025 r.
- [Arcep: Comment résoudre un litige avec un opérateur postal ?](https://www.arcep.fr/mes-demarches-et-services/consommateurs/fiches-pratiques/la-resolution-des-litiges-postaux.html) – bezpłatna reklamacja nadawcy lub odbiorcy, kanały kontaktu, service recours oraz Médiateur
- [Le Médiateur du Groupe La Poste: Foire aux questions](https://mediateur.groupelaposte.com/pages/foire-aux-questions) – bezpłatna mediacja po dwustopniowej reklamacji lub dwóch miesiącach bez odpowiedzi, roczny termin
- [Service-Public.fr: Service consommateur de La Poste](https://lannuaire.service-public.gouv.fr/centres-contact/R11647) – numery 3631 i +33 810 821 821, godziny pracy oraz adres pocztowy 99 999 LA POSTE
- [Cybermalveillance.gouv.fr: L'hameçonnage à la livraison de colis](https://www.cybermalveillance.gouv.fr/tous-nos-contenus/actualites/escroqueries-livraison-colis) – La Poste nigdy nie żąda płatności SMS-em ani e-mailem za odbiór paczki, zgłoszenia na phishing@laposte.fr i 33700
- [Your Europe: Wysyłka i dostawa](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_pl.htm) – dostawa w ciągu 30 dni, dodatkowy termin, zwrot pieniędzy i odpowiedzialność sprzedawcy za uszkodzenia
- [Komisja Europejska: reforma celna UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) oraz [Wytyczne ws. cła 3 € i opłaty 2 € (PDF, po angielsku)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – cło ryczałtowe 3 € za kategorię do 150 € od 1 lipca 2026 r. oraz unijna opłata manipulacyjna 2 € za kategorię od 1 listopada 2026 r.
- [Podatki.gov.pl: Zwolnienia celne](https://podatki.gov.pl/informacje-o-cle/pozostale-informacje/zwolnienia-celne) – zasady celne obowiązujące przy przywozie towarów spoza obszaru celnego UE
- [Poczta Polska: Zmiany w opłatach dla przesyłek spoza UE od 1 lipca 2026 r.](https://www.poczta-polska.pl/news/zmiany-w-oplatach-dla-przesylek-spoza-unii-europejskiej-od-1-lipca-2026-r/) – pobór należności celno-podatkowych przy doręczeniu przesyłki lub przed jej odbiorem
- [Poczta Polska: Nowe przepisy UE – opłata za przesyłki spoza UE](https://media.poczta-polska.pl/releases/nowe-przepisy-unii-europejskiej-tak-dziala-nowa-oplata-za-przesylki-spoza-ue) – opłata manipulacyjna 2 € od 1 listopada 2026 r.
- [Poczta Polska: Uwaga na fałszywe wiadomości Pocztex](https://www.poczta-polska.pl/news/uwaga-na-falszywe-wiadomosci-pocztex-grozna-kampania-phishingowa/) – ostrzeżenie przed fałszywymi linkami do dopłat w SMS-ach
- [Universal Parcel Scraper: La Poste](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/la-poste) – wzorce numerów i cyfr kontrolnych, etapy statusów, prywatność i interwały odświeżania w Peek
- [Universal Parcel Scraper: zweryfikowana historia śledzenia](https://github.com/plhery/universal-parcel-scraper/blob/main/testing/auditedTrackingHistory.json) – potwierdzone francuskie komunikaty statusów La Poste
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – przekazywanie międzynarodowych numerów FR z adresem w Polsce do Poczty Polskiej
:::
