---
title: Chronopost śledzenie paczki: z Francji do Polski i statusy
description: Śledzenie paczki Chronopost z Francji do Polski: jak wygląda numer przesyłki, co oznaczają francuskie statusy, doręczenie przez DPD Polska i kontakt.
slug: chronopost-sledzenie-paczki
published: 2026-11-16
updated: 2026-11-16
---

Chronopost to francuski kurier ekspresowy należący do grupy La Poste i będący częścią Geopost – tej samej sieci paczkowej, do której należy w Polsce [DPD Polska](carrier:dpd-pl). Paczkę nadesłaną z francuskiego sklepu, z Vinted czy z leboncoin sprawdzisz na stronie chronopost.fr albo tutaj: wklej numer przesyłki w pole powyżej.

## Numer przesyłki Chronopost: jak wygląda i gdzie go szukać

Zazwyczaj numer Chronopost ma 13 znaków (dwie litery, dziewięć cyfr, dwie litery), ale spotkasz też numery 14- i 15-znakowe. Jeśli nie widzisz go w wiadomości ze sklepu lub z platformy sprzedażowej, zajrzyj do poradnika [gdzie znaleźć numer przesyłki](guide:find-tracking-number). Wszystkie przykłady w tabeli są wymyślone:

| Numer (wymyślony) | Co oznacza |
| --- | --- |
| `XY000000005FR` | Dwie litery (`PZ`, `XR`, `XT`, `XU`, `XW` lub `XY`), dziewięć cyfr i dwie litery: typowy numer Chronopost. |
| `AB000000000JB` | Ten sam 13-znakowy układ zakończony na `JB`, `JF`, `TS`, `RV` lub `VF`: oznaczenie usług własnych Chronopost. |
| `XN000000000000` | `XN` i 12 cyfr: format również obsługiwany przez śledzenie Chronopost. |
| `05000000000000E` | 15 znaków – 14 cyfr i znak kontrolny (cyfra lub litera): format paczek sieci DPD, który przyjmuje także Chronopost. |
| `20000000000000` | 14 cyfr niezaczynających się od `10`: może należeć do Chronopost, ale tego samego kształtu używają też sieci DPD, SEUR czy BRT. |

Trzynastoznakowy zapis to zarazem międzynarodowy standard pocztowy. Numer zakończony na `FR` o innych literach początkowych należy zazwyczaj do Colissimo (La Poste), natomiast numer z końcówką `PL` zaczynający się od `R`, `E`, `L` lub `C` (poza `CV`) to przesyłka [Poczty Polskiej](carrier:poczta-polska). Z kolei numery 14- i 15-znakowe zaczynające się od `13` wskazują w pierwszej kolejności na [DPD Polska](carrier:dpd-pl). Więcej o odróżnianiu przewoźników znajdziesz w poradniku [formaty numerów śledzenia](guide:tracking-number-formats).

## Statusy Chronopost: co oznaczają komunikaty po francusku

Peek pobiera historię Chronopost po francusku i pokazuje komunikaty tak, jak zapisuje je przewoźnik; obok każdego zdarzenia przypisuje czytelny etap po polsku. Najczęstsze wpisy w kolejności, w jakiej zwykle przechodzi je paczka:

| Chronopost pisze | Co to oznacza dla Ciebie | Etap w Peek |
| --- | --- | --- |
| `Colis en cours de préparation chez l'expéditeur` | Nadawca wygenerował etykietę, ale nie przekazał jeszcze paczki kurierowi. | „Zarejestrowana” |
| `Colis déposé par l'expéditeur` | Nadawca nadał paczkę w Chronopost. | „Przyjęta przez przewoźnika” |
| `Tri effectué dans l'agence de départ` | Posortowana w oddziale nadawczym. | „W transporcie” |
| `Colis en cours d'acheminement` | Jedzie w sieci Chronopost między sortowniami. | „W transporcie” |
| `Colis entré dans le pays de destination` | Dotarła do kraju docelowego. | „W transporcie” |
| `Tri effectué dans l'agence de distribution` | Posortowana w oddziale, z którego wyjedzie do doręczenia. | „W transporcie” |
| `Livraison reportée de 24h` | Oddział wstrzymał paczkę o dobę bez próby doręczenia; doręczenie wznawia się samoczynnie. | „W transporcie” |
| `Livraison reportée` | Partner doręczający w kraju docelowym przełożył dostawę bez podania przyczyny i bez próby doręczenia. | „W transporcie” |
| `Colis en cours de livraison` | Wydana kurierowi do doręczenia na dziś. | „W doręczeniu” |
| `Echec de livraison suite à l'absence du destinataire.` | Kurier nie zastał adresata. | „Próba doręczenia” |
| `Colis non livré : client absent` | Partner zagraniczny nie zastał Cię pod adresem. | „Próba doręczenia” |
| `Colis mis à disposition au point de retrait` | Czeka na Ciebie w punkcie odbioru lub automacie. | „Do odbioru” |
| `Colis remis au destinataire au point de retrait` | Odebrana w punkcie odbioru. | „Dostarczona” |
| `Colis en retour expéditeur` | Wraca do nadawcy. | „Zgłoszono problem” |
| `Livraison effectuée` | Paczka doręczona. | „Dostarczona” |

Wpis `Livraison reportée de 24h` (np. po omyłkowym skierowaniu, podczas weryfikacji adresu albo gdy firma odbiorcy jest zamknięta) nie oznacza zagubienia przesyłki. Komunikaty `Destinataire informé par SMS ou mail` oraz `Instruction de livraison reçue` odnotowują jedynie wysłane powiadomienie lub Twoją dyspozycję – paczka nie zmieniła miejsca, więc etap w Peek pozostaje ten sam. Jeżeli po `Colis en retour expéditeur` pojawi się `Livraison effectuée`, przesyłkę odebrał z powrotem nadawca, a nie Ty: Peek oznacza wtedy paczkę jako „Zwrócona nadawcy”. Zobacz też [co oznaczają statusy przesyłek](guide:tracking-statuses).

## Paczka Chronopost z Francji do Polski: kto doręcza i czy jest cło

Według La Poste Groupe Chronopost doręcza przesyłki do 230 krajów i terytoriów na świecie, a zarówno Chronopost, jak i [DPD Polska](carrier:dpd-pl) należą do holdingu Geopost będącego własnością francuskiej poczty La Poste.

**Śledzenie i partner w Polsce.** Gdy Chronopost podaje przy przesyłce krajowej lub zagranicznej numer partnerski w jednoznacznym formacie DPD Polska – 13 cyfr zakończonych literą (np. `1300000000000A`) – Peek wyświetla ten numer, odpytuje [DPD Poland](carrier:dpd-pl) i przełącza na nie śledzenie, gdy tylko DPD pokaże datowany skan co najmniej tak świeży jak ostatni wpis Chronopost (przez dobę od przejęcia pyta jeszcze pomocniczo Chronopost co najwyżej raz na 55 minut). Jeżeli Chronopost poda 14-cyfrowy numer partnerski albo nie ujawni numeru partnera dla Polski, Peek dalej śledzi paczkę bezpośrednio w Chronopost; 14-cyfrowy numer DPD zaczynający się od `13` z e-maila lub SMS-a od kuriera możesz też wkleić osobno, aby śledzić go wprost w DPD Poland. Natomiast gdy przez sortownię Chronopost przechodzi cudza przesyłka pocztowa (np. spoza Francji, niebędąca EMS ani własnym kodem Chronopost) adresowana do Polski, Peek sprawdza pod tym samym numerem [Pocztę Polską](carrier:poczta-polska).

**Cło.** Francja i Polska należą do Unii Europejskiej, a w ramach unii celnej UE między państwami członkowskimi nie płaci się ceł. Gdyby w historii przesyłki spoza UE pojawił się wpis `Problème douanier résolu, colis en cours d'acheminement`, oznacza on zakończenie formalności celnych i powrót paczki w trasę; więcej w poradniku [paczka w urzędzie celnym](guide:customs).

## We Francji: punkty Pickup, automaty i nieudana dostawa

Jeśli odbierasz paczkę na terenie Francji (np. przy zakupach z odbiorem w punkcie Shop2Shop by Chronopost na leboncoin lub Vinted), przesyłka może trafić do jednego z 19 555 punktów sieci Chronopost we Francji: sklepów Pickup (*relais Pickup*), automatów paczkowych (*consignes Pickup*), urzędów pocztowych lub oddziałów Chronopost. Po złożeniu paczki w punkcie dostajesz e-mail lub SMS z godzinami otwarcia i datą końcową odbioru. Według sieci Pickup czas oczekiwania we Francji wynosi maksymalnie:

- **8 dni** w punkcie sklepowym Pickup (*relais*);
- **5 dni** w automacie paczkowym Pickup (*consigne*).

Po tym terminie paczka automatycznie wraca do nadawcy; zobacz [nieodebrana paczka](guide:uncollected-parcel) oraz [automaty paczkowe](guide:parcel-lockers). Do odbioru wystarczy Pickup Pass (kod QR) albo kody PIN z wiadomości e-mail/SMS, a w sklepie Pickup także oryginał dokumentu tożsamości. Ktoś inny może odebrać przesyłkę Twoim kodem lub Pickup Passem, a w punkcie sklepowym również z oryginałami swojego i Twojego dokumentu tożsamości – pisemnych upoważnień sieć Pickup nie honoruje.

## Paczka Chronopost stoi w miejscu, zaginęła lub dotarła uszkodzona

**Opóźnienie lub brak nowych skanów.** Zgłoś się w pierwszej kolejności do sklepu lub na platformę (np. Vinted), na której kupiłeś towar – to nadawca zawarł umowę z przewoźnikiem. Zgodnie z unijnymi zasadami opisanymi w serwisie Your Europe sprzedawca powinien dostarczyć zakup w ciągu 30 dni, jeśli nie uzgodniono inaczej; po bezskutecznym upływie wyznaczonego dodatkowego terminu możesz odstąpić od umowy i żądać zwrotu pieniędzy. Zobacz [śledzenie przesyłki się nie aktualizuje](guide:tracking-not-updating).

**Uszkodzenie lub zaginięcie.** W UE sprzedawca odpowiada wobec konsumenta za przypadkową utratę lub uszkodzenie towaru w transporcie aż do chwili, gdy fizycznie go odbierzesz. Francuska administracja (Service-Public) radzi przy doręczeniu wpisać zastrzeżenia na liście przewozowym i niezwłocznie powiadomić obsługę sklepu; przy odbiorze w francuskim punkcie Pickup zgłoś szkodę pracownikowi punktu (odnotuje ją w systemie), a paczkę możesz mimo to zabrać albo jej odmówić. Zobacz [zagubiona paczka](guide:lost-parcel), [uszkodzona paczka](guide:damaged-parcel) oraz [dostarczona, ale nieodebrana](guide:delivered-not-received).

## Kontakt i obsługa klienta Chronopost

Dane kontaktowe podawane przez La Poste Groupe, Geopost i sieć Pickup (stan na 16 listopada 2026 r.):

- **Wirtualny asystent Léonard** na stronie chronopost.fr – odpowiada na pytania o doręczenie, a złożone sprawy przekazuje konsultantowi.
- **Telefon we Francji:** `09 69 39 13 91` – francuski numer infolinii Chronopost wskazywany m.in. przez Pickup, gdy nie dotarły kody odbioru.
- **Przesyłki Shop2Shop:** serwis `chronoshop2shop.fr`.

Jeżeli złożyłeś do Chronopost pisemną, opatrzoną datą i podpisaną reklamację i przez dwa miesiące nie dostałeś odpowiedzi albo odpowiedź Cię nie satysfakcjonuje, w ciągu roku od złożenia reklamacji możesz bezpłatnie zwrócić się do rzecznika grupy pocztowej – *Médiateur du Groupe La Poste*.

> Francuska rządowa platforma Cybermalveillance.gouv.fr ostrzega przed fałszywymi SMS-ami i e-mailami podszywającymi się pod Chronopost. We Francji Chronopost nie żąda przy doręczeniu skanu dowodu osobistego ani dopłat za dostawę przez linki w wiadomościach; podejrzane e-maile możesz przesłać na `abuse@chronopost.fr`. Zobacz [jak rozpoznać fałszywe SMS-y o paczce](guide:delivery-scam-texts).

## Śledzenie paczki Chronopost w Peek

Wklej numer na [Peek](/). Trzynastoznakowe kody zaczynające się od `PZ`, `XR`, `XT`, `XU`, `XW` lub `XY` z poprawną cyfrą kontrolną zakończone na `FR` albo `PL` (oraz inne kraje, których poczta nie zastrzega wszystkich prefiksów pocztowych), a także kody zakończone na `JB`, `JF` lub `TS`, Peek od razu przypisuje do Chronopost. Jeśli taki kod z poprawną cyfrą kontrolną kończy się na `CH`, `ES`, `PT` czy `GB`, Peek pyta równolegle Chronopost oraz pocztę danego kraju. Gdy przy tych sześciu prefiksach cyfra kontrolna nie jest pocztowa, gdy numer kończy się na `RV` lub `VF` albo zaczyna od `XN`, Peek odpytuje wyłącznie Chronopost i do czasu pierwszego skanu wyświetla „Oczekiwanie na pierwszy skan przewoźnika Chronopost”. Poprawne numery pocztowe na `PL` zaczynające się od `R`, `E`, `L` lub `C` (poza `CV`) trafiają prosto do [Poczty Polskiej](carrier:poczta-polska), a przy pozostałych (np. `VV…PL`) Peek pyta najpierw Pocztę Polską, a potem Chronopost.

Przy 15-znakowym numerze DPD zaczynającym się od `13` Peek pyta w pierwszej kolejności [DPD Poland](carrier:dpd-pl) razem z DPD Switzerland, DPD Germany, Chronopost i DPD UK (a gdy wszystkie 15 znaków to cyfry i pierwsza piątka nie zna numeru – sprawdza też FedEx); przy innych 15-znakowych numerach DPD pyta Chronopost wraz z czterema sieciami DPD. Przy 14 cyfrach niezaczynających się od `10` sprawdza w jednej lub dwóch piątkach przewoźników używających tego formatu (dla `13…` zaczyna od DPD Poland, a jeśli pierwsza piątka nie zna numeru, w drugiej pyta m.in. Chronopost), pokazując w tym czasie „Sprawdzanie usług śledzenia…”. Gdy Chronopost potwierdzi paczkę, zobaczysz „Chronopost ma tę przesyłkę.”; zawsze możesz też wybrać przewoźnika ręcznie przyciskiem „Zmień” lub „Zmień przewoźnika”. Działają również linki ze śledzenia `chronopost.fr`, `chronotrace.chronopost.fr` oraz `chronoshop2shop.fr` (z parametrami `listeNumerosLT`, `listeNumeros` lub `numeroLT`), bez kodu pocztowego.

Peek pokazuje aktualny etap, trasę ze skanami na mapie, kraj przeznaczenia, moment doręczenia oraz – dopóki przesyłka czeka na odbiór – nazwę i adres punktu lub automatu. Przewidywaną datę dostawy wyświetla wtedy, gdy Chronopost poda termin ponownego doręczenia, przedział godzinowy albo między piątkiem a niedzielą zapisze `Livraison prévue lundi prochain`. Twojego adresu domowego ani telefonu punktu Peek nie zachowuje. Jeśli skan nie ma zaszytej strefy czasowej, Peek odczytuje go według czasu lokalnego miejsca skanu i przelicza na strefę Twojego urządzenia (z dopiskiem „Godziny w Twojej strefie czasowej: …” pod historią na peektracker.com i w aplikacji na iPhonie).

Sprawdzanie odbywa się co 10 minut, a w dniu doręczenia od 6:00 czasu lokalnego co 2 minuty, dopóki odpowiada bezpośrednio Chronopost (po nieudanym zapytaniu lub przez zewnętrzne serwisy śledzenia – co 15 minut; jeśli podane jest okno czasowe, rytm 2-minutowy rusza 30 minut przed jego początkiem). Gdy paczkę przejmie partner doręczający, Peek przechodzi na jego własny rytm odpytywania. Po dwóch dniach bez nowego skanu (lub po 12 godzinach na etapie „Zarejestrowana” bądź „Przyjęta przez przewoźnika”) sprawdza historię co godzinę, a po dwóch dniach bez rozpoznania nieznanego numeru wyświetla: „Po dwóch dniach przewoźnik nadal nie zna tego numeru. Sprawdź go, wybierz innego przewoźnika lub usuń przesyłkę.” Powiadomienia ustawisz na „Każdy skan”, „Ważne etapy” albo „Tylko doręczenie”.

## Najczęstsze pytania o śledzenie Chronopost

### Czy Chronopost należy do La Poste i ma związek z DPD Polska?

Tak. Chronopost wchodzi w skład Geopost, a właścicielem Geopost jest francuska grupa pocztowa La Poste Groupe. Do tej samej grupy Geopost należy w Polsce [DPD Polska](carrier:dpd-pl), dlatego część przesyłek Chronopost nosi numery w formacie DPD.

### Kto doręcza paczkę Chronopost w Polsce?

Chronopost należy wraz z [DPD Polska](carrier:dpd-pl) do sieci Geopost. Gdy w danych przesyłki Chronopost pojawia się numer partnerski w formacie DPD Polska (13 cyfr i litera), Peek wyświetla go i przełącza śledzenie na DPD Poland, gdy tylko pojawi się tam aktualny skan; jeśli Chronopost podaje 14-cyfrowy numer lub nie podaje numeru partnera, Peek kontynuuje śledzenie w Chronopost (a 14-cyfrowy numer `13…` z powiadomienia od kuriera możesz też wkleić osobno).

### Co oznacza status „Livraison reportée de 24h”?

Oddział Chronopost wstrzymał paczkę o dobę bez próby doręczenia – na przykład po błędnym skierowaniu, w celu potwierdzenia adresu albo gdy odbiorca firmowy był zamknięty. Nie trzeba nic robić: paczka pozostaje „W transporcie”, a doręczenie wznawia się samoczynnie.

### Ile dni paczka Chronopost czeka w punkcie Pickup we Francji?

Według sieci Pickup we Francji przesyłka czeka maksymalnie 8 dni w sklepowym punkcie Pickup (*relais*) oraz maksymalnie 5 dni w automacie paczkowym Pickup (*consigne*), a dokładna data widnieje w SMS-ie lub e-mailu. Nieodebrana paczka wraca automatycznie do nadawcy.

:::sources
- [La Poste Groupe: Chronopost (po angielsku)](https://www.lapostegroupe.com/en/focus/la-poste-groupes-subsidiaries/chronopost) – ekspresowy kurier La Poste, 230 krajów, 19 555 punktów odbioru we Francji
- [La Poste Groupe: 2025 Integrated Report, PDF (po angielsku)](https://www.lapostegroupe.com/assets/media/le-groupe-laposte/ad4Q_p1ZCF7ETKqb_MEL_LAPO_RI_seul_2025_EN_14042026.pdf) – Geopost z sieciami DPD i Chronopost oraz chatbot Léonard (s. 18)
- [DPD Polska: O DPD Polska](https://www.dpd.com/pl/pl/o-dpd/o-dpd-polska/) – przynależność DPD Polska do holdingu Geopost należącego do francuskiej poczty La Poste
- [Pickup: FAQ Particulier (po francusku)](https://www.pickup.fr/faq-particulier/) – powiadomienia, 8 dni w punkcie i 5 dni w automacie, odbiór kodem/QR i dokumentem, infolinia 09 69 39 13 91, zgłoszenie szkody w punkcie
- [Your Europe: Wysyłka i dostawa](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_pl.htm) – dostawa w 30 dni, dodatkowy termin i odpowiedzialność sprzedawcy za transport
- [Unia Europejska: Unia celna UE w praktyce](https://european-union.europa.eu/priorities-and-actions/actions-topic/customs_pl) – brak ceł między krajami członkowskimi UE
- [Service-Public: Achat à distance – livraison du bien (po francusku)](https://www.service-public.gouv.fr/particuliers/vosdroits/F10037) – zastrzeżenia na liście przewozowym i szybki kontakt ze sklepem
- [Médiateur du Groupe La Poste: Conditions de saisine (po francusku)](https://mediateur.groupelaposte.com/pages/conditions-requises-a-la-saisine-du-mediateur-de-la-consommation-du-groupe-la-poste) – spór z Chronopost po 2 miesiącach i w ciągu roku od pisemnej reklamacji
- [Cybermalveillance.gouv.fr: L'hameçonnage à la livraison de colis (po francusku)](https://www.cybermalveillance.gouv.fr/tous-nos-contenus/actualites/escroqueries-livraison-colis) – brak próśb o dowód i dopłaty we Francji, adres abuse@chronopost.fr
- [Geopost: leboncoin is teaming up with Chronopost (po angielsku)](https://www.geopost.com/en/news/leboncoin-is-teaming-up-with-chronopost/) – Chronopost w Geopost i usługa Shop2Shop
- [Geopost: About us (po angielsku)](https://www.geopost.com/en/about-us/) – Geopost jako część La Poste Groupe
- [Universal Parcel Scraper: Chronopost (po angielsku)](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/chronopost) – rozpoznawane numery, francuskie statusy, przekazanie numeru DPD Polska i działanie w Peek
:::
