---
title: Śledzenie przesyłek BRT: paczka z Włoch, statusy i kontakt
description: Jak śledzić paczkę BRT z Włoch: gdzie znaleźć BRTcode, co oznaczają angielskie statusy, przekazanie do DPD Polska, odbiór w punkcie i reklamacja.
slug: brt-sledzenie
published: 2026-11-17
updated: 2026-11-17
---

BRT to włoska firma kurierska należąca – podobnie jak [DPD Polska](carrier:dpd-pl) i [Chronopost](carrier:chronopost) – do grupy Geopost. Przesyłkę możesz śledzić z linku w SMS-ie lub e-mailu od BRT, w wyszukiwarce na stronie [brt.it](https://www.brt.it/en/) albo wklejając numer w pole powyżej. Numer ten – *BRTcode* – BRT wysyła od razu po przejęciu paczki, jeśli nadawca przekazał twoje dane kontaktowe.

## Numer przesyłki BRT: gdzie go szukać, formaty i prawdziwe wiadomości

*BRTcode* znajdziesz w SMS-ach i e-mailach od BRT oraz na awizie zostawionym przez kuriera po nieudanej próbie doręczenia. Nie dostałeś żadnej wiadomości? Sprawdź e-mail ze sklepu o wysyłce albo poradnik [gdzie znaleźć numer przesyłki](guide:find-tracking-number).

Gdy sprzedawca przekaże twój numer telefonu lub adres e-mail, BRT wysyła prawdziwe powiadomienia z kodem *BRTcode* po odebraniu przesyłki oraz komunikaty o nieudanej próbie doręczenia, złożeniu w magazynie (*giacenza*) albo dostarczeniu do punktu *BRT-fermopoint* lub automatu *BRT Locker*. Jednocześnie 16 kwietnia 2026 r. przewoźnik opublikował [ostrzeżenie przed phishingiem SMS i e-mail](https://www.brt.it/en/news/2026/04/16/alert-phishing-sms-email/), opisując podszywanie się pod BRT przy fałszywych powiadomieniach o niedoręczeniu i fałszywych fakturach ze złośliwym oprogramowaniem: zwróć uwagę na błędy językowe, podejrzany lub lekko zmieniony adres nadawcy oraz pilne wezwania do kliknięcia linku, a w razie wątpliwości nie klikaj linków, nie pobieraj załączników i wpisz *BRTcode* ręcznie na `brt.it` lub `vas.brt.it` (zobacz też [jak rozpoznać fałszywe SMS-y o paczce](guide:delivery-scam-texts)).

Oto formaty przyjmowane przez Peek dla BRT (przykłady są zmyślone):

| Numer (zmyślony) | Co oznacza |
| --- | --- |
| `182094736105` | 12 cyfr: numer przesyłki (*numero spedizione*) nadawany przez BRT. |
| `06482917350428` | 14 cyfr: *BRTcode*. |
| `918273645019283` | 15 cyfr: identyfikator paczki klienta (*ID collo cliente*) nadany przez nadawcę. |
| `EOB19283746501928374` | `EOB` i 17 cyfr: inny format identyfikatora paczki nadawcy. |

Zaawansowany formularz wyszukiwania BRT wymaga 19 cyfr w polu *BRTcode* – tak długich numerów Peek dla BRT nie przyjmuje. Trzycyfrowy kod bezpieczeństwa (*codice di sicurezza*), używany do zmiany doręczenia lub zwolnienia paczki z magazynu, nie jest numerem śledzenia.

Jeśli BRT nic nie znajduje, sprawdź, czy nie ma literówki, lub poczekaj, aż paczka fizycznie trafi do przewoźnika. [Wyszukiwarka zaawansowana BRT](https://vas.brt.it/vas/sped_numspe_par.htm?lang=en) (*Rintraccia spedizione*) sięga 12 miesięcy wstecz po *BRTcode* lub 12-cyfrowym numerze przesyłki, ale tylko 2 miesiące po *ID collo cliente*. Numery 14-cyfrowe stosują też sieci DPD (w tym [DPD Polska](carrier:dpd-pl)), a 12-cyfrowe m.in. [Mondial Relay](carrier:mondial-relay) czy Colis Privé – upewnij się w wiadomości ze sklepu, kto wiezie paczkę, albo sprawdź [formaty numerów przesyłek](guide:tracking-number-formats).

## Statusy śledzenia BRT: co oznaczają komunikaty po angielsku

Śledzenie na `vas.brt.it` otwiera się po włosku i pozwala przełączyć widok na angielski, francuski lub niemiecki (polskiej wersji nie ma). Peek odczytuje wersję angielską, w której nagłówki kolumn pozostają włoskie: *Data* (data), *Ora* (godzina), *Filiale* (oddział BRT) i *Evento* (zdarzenie). W historii w Peek zobaczysz dokładne angielskie opisy poniżej, z wyjątkiem każdego wydania paczki – także odbioru w *BRT-fermopoint* lub *BRT Locker* – które na liście zdarzeń widnieje jako `Delivered` (jeśli masz Peek ustawiony po włosku, zobaczysz włoskie komunikaty BRT oraz `Consegnata`, a zdarzenia znane tylko po angielsku, np. z automatu, pozostaną po angielsku). Po prawej stronie podajemy etap w Peek:

| Komunikat BRT | Co oznacza dla ciebie | Etap w Peek |
| --- | --- | --- |
| `SHIPPING INFO SENT TO BRT` | BRT otrzymało dane od nadawcy, ale nie ma jeszcze samej paczki. Zobacz [statusy przesyłek – wyjaśnienie](guide:tracking-statuses). | „Zarejestrowana” |
| `COLLECTED` | BRT odebrało przesyłkę od nadawcy. | „Przyjęta przez przewoźnika” |
| `DEPARTED` | Paczka opuściła oddział lub sortownię BRT. | „W transporcie” |
| `ARRIVED AT DEPOT` | Dotarła do oddziału wskazanego w kolumnie *Filiale*. | „W transporcie” |
| `FOR DELIVERY` | Kurier wyjechał z paczką na trasę doręczeń. | „W doręczeniu” |
| `NOTICE OF ATTEMPTED DELIVERY` | Kurier nikogo nie zastał i zostawił awizo. | „Próba doręczenia” |
| `UNKNOWN/INCOMPLETE CONSIGNEE` | Niedoręczona: nieznany odbiorca lub niepełny adres. | „Próba doręczenia” |
| `TURMOIL` | Demonstracja publiczna chwilowo wstrzymuje przewóz. | „Zgłoszono problem” |
| `PATRONAL HOLIDAY` | Święto patrona w miejscowości docelowej: paczka czeka przez dzień przed dotarciem do oddziału doręczającego, bez próby dostawy. | „W transporcie” |
| `ARRIVED AT BRT-fermopoint` | Gotowa do odbioru w punkcie partnerskim *BRT-fermopoint*. | „Do odbioru” |
| `COLLECTED AT BRT-fermopoint` | Odebrałeś paczkę w punkcie *BRT-fermopoint*. | „Dostarczona” |
| `ARRIVED AT BRT LOCKER` | Gotowa do odbioru w automacie paczkowym *BRT Locker*. | „Do odbioru” |
| `COLLECTED AT BRT LOCKER` | Odebrałeś paczkę z automatu *BRT Locker*. | „Dostarczona” |
| `TO BE DELIVERED` | Zagraniczny partner doręczający przejął ją do wydania; BRT zapisuje to w tej samej minucie co `FOR DELIVERY`. | „W transporcie” (razem z `FOR DELIVERY`: „W doręczeniu”) |
| `ABSENT AFTER ATTEMPT.DELIVERY` | Zagraniczny partner nikogo nie zastał podczas doręczenia; nastąpi kolejna próba albo zwrot. | „Próba doręczenia” |
| `To collect at the parcel shop` | Zagraniczny partner przekazał paczkę do swojego punktu odbioru. | „Do odbioru” |
| `DELIVERED` | Doręczona. | „Dostarczona” |
| `RETURNED TO SENDER` | Przesyłka wraca do nadawcy. | „Zwrócona nadawcy” |

Warto pamiętać o trzech szczegółach: `COLLECTED` to odbiór od sprzedawcy, a nie przez ciebie; jeśli paczkę przywiózł do Włoch z zagranicy [Chronopost](carrier:chronopost) lub DPD, `COLLECTED` oznacza przekazanie jej do BRT i w Peek wyświetla się jako „W transporcie”. Podobnie `FOR DELIVERY` w zagranicznym oddziale DPD oznacza przekazanie paczki tej sieci („W transporcie”). Z kolei gdy `DELIVERED` pojawia się po `RETURNED TO SENDER`, paczka wróciła do nadawcy: wiersz zdarzenia ma opis `Delivered`, ale ogólny etap w Peek pozostaje „Zwrócona nadawcy”.

## Paczka BRT z Włoch do Polski

**Kto doręcza w Polsce.** Przesyłki drogowe z Włoch do innych krajów Europy BRT przekazuje sieci DPD (a ładunki ciężkie lub ponadgabarytowe innemu partnerowi). Przy przesyłkach DPD strona [szczegółów przesyłki BRT](https://vas.brt.it/vas/aiuto/aiuto.hsm?urlaiuto=sped_det_show.htm&lang=en) wyświetla ramkę prowadzącą do śledzenia DPD. W Peek przekazanie następuje automatycznie, gdy przesyłka składa się z jednej paczki, BRT podaje jej 14-cyfrowy numer DPD (*Parcel number dpd*), a najnowszy skan pochodzi z oddziału DPD wskazującego Polskę: wtedy Peek pokazuje ten numer z dopiskiem „Doręcza: DPD Poland” i odpytuje [DPD Polska](carrier:dpd-pl) (dopóki paczka jest w DPD jedynie zapowiedziana bez własnego skanu, widnieje komunikat „Zgłoszona przewoźnikowi DPD Poland, jeszcze nieprzyjęta”). Gdy tylko w DPD pojawi się datowany skan co najmniej tak świeży jak ostatni wpis BRT, Peek kontynuuje śledzenie w DPD Polska z zachowaniem włoskiej historii i jeszcze przez dobę sprawdza równolegle BRT, najwyżej co 55 minut. Przy przesyłkach wielopaczkowych Peek pozostaje przy BRT.

**Cło.** Z Włoch do Polski paczka przemieszcza się wewnątrz Unii Europejskiej, więc odprawa celna nie występuje; o przesyłkach spoza UE przeczytasz w poradniku [paczka w urzędzie celnym](guide:customs).

## Niedoręczona paczka we Włoszech: kolejna próba, BRT-fermopoint i Locker

Jeśli odbierasz przesyłkę pod włoskim adresem i kurier cię nie zastanie, BRT zostawia powiadomienie e-mailem, SMS-em lub na kartce. Ponowna próba odbywa się zazwyczaj w następnym dniu roboczym, a gdy i ona się nie powiedzie, oddział BRT według karty usług (*Carta dei servizi*) dzwoni do odbiorcy.

Przez link w wiadomości od BRT lub w serwisie myBRT (z użyciem *BRTcode* i kodu pocztowego lub PIN-u) możesz – zależnie od usług dla danej paczki – wybrać inny dzień lub przedział godzinowy, przekierować paczkę do punktu *BRT-fermopoint* albo do odbioru w oddziale, zwolnić przesyłkę z magazynu bądź zmienić adres za zgodą nadawcy. Więcej w poradniku [nieudana próba doręczenia](guide:missed-delivery).

**Przechowywanie w magazynie (*in giacenza*).** Paczka trafia do magazynu m.in. z powodu nieobecnego lub nieznanego odbiorcy, błędnego bądź niepełnego adresu albo odmowy przyjęcia; powód widnieje w szczegółach przesyłki. Dyspozycję możesz wydać z poziomu wiadomości BRT lub śledzenia (czasem wymagany jest 3-cyfrowy kod bezpieczeństwa). BRT prosi też o instrukcje nadawcę: zgodnie z *Carta dei servizi* brak pisemnej odpowiedzi w ciągu 10 dni od powiadomienia skutkuje zwrotem paczki (ogólne warunki odsyłają do liczby dni uzgodnionej z nadawcą). Po odpowiedzi nadawcy szczegóły pokazują również, czy naliczane są koszty składowania i kto je pokrywa.

**Odbiór w oddziale (*fermo deposito*).** Paczkę odbierzesz osobiście z dokumentem tożsamości albo przez osobę upoważnioną z pisemnym upoważnieniem, jej własnym dokumentem tożsamości i kopią twojego. Jeśli nadawca podał twój kontakt, BRT prześle adres oddziału, godziny otwarcia i ewentualną kwotę pobrania.

**BRT-fermopoint.** To partnerskie punkty w sklepach (kioski, sklepy tytoniowe, papiernicze i inne), z których część działa także w weekendy. O dostarczeniu informuje e-mail lub SMS, a paczka czeka 10 dni.

**BRT Locker.** Automaty paczkowe przy sklepach, galeriach handlowych i stacjach paliw – zewnętrzne często czynne całą dobę – otwierane kodem QR lub kodem PIN z wiadomości od BRT. Przesyłki powyżej 20 kg nie trafiają ani do automatów *BRT Locker*, ani do *BRT-fermopoint*. W razie awarii ekranu lub pustej skrytki skorzystaj z formularza na dole e-maila o odbiorze. Zobacz też [automaty paczkowe](guide:parcel-lockers).

## Paczka BRT opóźniona, uszkodzona lub zagubiona

Czasy dostawy podawane przez BRT mają charakter orientacyjny. Gdy śledzenie stoi w miejscu, skontaktuj się z oddziałem z kolumny *Filiale* albo ze sklepem internetowym, który zawarł umowę z BRT, i sprawdź [śledzenie przesyłki się nie aktualizuje](guide:tracking-not-updating).

**Uszkodzenie lub zaginięcie.** Jeżeli to ty opłaciłeś transport, zgłoś szkodę przez formularz wsparcia BRT (przy uszkodzeniu dołącz zdjęcia); w przeciwnym razie powiadom sprzedawcę. Widoczne uszkodzenia opakowania trzeba wpisać przy odbiorze jako konkretne zastrzeżenie („*con riserva di controllo*” ze wskazaniem rodzaju szkody), bo ogólne zastrzeżenia są według BRT nieważne. Szkody ukryte zgłasza się pisemnie w ciągu 8 dni od doręczenia na terenie Włoch (art. 1698 włoskiego kodeksu cywilnego) albo w ciągu 7 dni bez niedziel i świąt w międzynarodowym transporcie drogowym zgodnie z konwencją CMR. Więcej w poradniku [uszkodzona paczka](guide:damaged-parcel).

**Reklamacja i koncyliacja.** Reklamację może złożyć odbiorca lub nadawca: pobierz plik PDF *Modulo di Reclamo* ze strony [Carta dei servizi](https://www.brt.it/it/carta-dei-servizi/), wypełnij go i dołącz do formularza na `services.brt.it/it/form-agcom`. BRT odpowiada w ciągu 45 dni. W razie braku odpowiedzi lub niezadowolenia możesz wystąpić o postępowanie polubowne pod adresem `conciliazioni@brt.it`, np. przez organizację *Casa del Consumatore* (składka roczna 50 €, a przy sporach do 50 € – 25 €, zwracana przez BRT po zawarciu ugody) lub inny podmiot, a następnie do włoskiego regulatora AGCOM. Paczka ma status doręczonej, ale jej nie masz? Sprawdź [przesyłka doręczona, ale nieodebrana](guide:delivered-not-received).

## Kontakt i obsługa klienta BRT

Jako odbiorca skontaktujesz się z BRT następująco:

- **Formularze zgłoszeniowe** na angielskiej stronie [Support](https://services.brt.it/en/support): wybierasz temat (opóźnienie, uszkodzenie, paczka oznaczona jako doręczona bez odbioru itp.) i przechodzisz do formularza. Numeru telefonu tam nie podano.
- **Włoska bezpłatna infolinia** (*numero verde*) `800 278 708`, od poniedziałku do piątku w godz. 8:30–18:30 według *Carta dei servizi*. BRT nie informuje, czy numer działa przy połączeniach z zagranicy.
- **E-mail** na adres `assistenzaclienti@brt.it` zgodnie z *Carta dei servizi* – wiadomość musi zawierać imię i nazwisko, kody pocztowe nadania i doręczenia oraz *BRTcode*, inaczej nie zostanie rozpatrzona.
- **Czat** na `brt.it` albo kontakt z oddziałem z [wyszukiwarki oddziałów](https://services.brt.it/en/customer-service/search-for-a-branch).

Boloński numer telefonu widniejący w *Carta dei servizi* należy do siedziby zarządu, a nie do biura obsługi klienta.

## Śledzenie paczek BRT w Peek

Wklej numer w [Peek](/). Dla kodów `EOB` i większości numerów 15-cyfrowych Peek odpytuje wyłącznie BRT (nawet przed [FedEx](carrier:fedex), gdy 15 cyfr przechodzi też jego sumę kontrolną); przy 12 cyfrach sprawdza BRT w pierwszej turze obok innych przewoźników. Przy 14 cyfrach pyta BRT od razu, jeśli twoim krajem są Włochy; w Polsce i innych krajach pyta najpierw pięciu głównych kandydatów (w Polsce sieci DPD oraz [Chronopost](carrier:chronopost)), a dopiero gdy żaden nie rozpozna numeru – BRT. Jeśli wybrano innego przewoźnika, stuknij „Zmień” i wskaż BRT w sekcji „Pasują do numeru” lub „Wszyscy przewoźnicy” (wyszukiwarka zna też nazwę „Bartolini”). Linki z `vas.brt.it` z parametrem `brtCode=` działają; linki myBRT i numery 19-cyfrowe nie.

Peek odpytuje BRT bezpośrednio bez przeglądarki: pokazuje status, lokalizację każdego skanu na mapie oraz wagę, pomijając dane nadawcy i odbiorcy, referencję sklepu, liczbę paczek, objętość i szczegóły punktu odbioru. Samo BRT nie podaje przewidywanej daty dostawy, ale przy 14 cyfrach Peek może pobrać termin z sieci DPD albo z [Chronopost](carrier:chronopost) (jeśli to on wiózł paczkę do Włoch); prognoza znika po upływie dnia albo wcześniej przy doręczeniu, zwrocie, złożeniu w punkcie, nieudanej próbie lub problemie. Godziny bez strefy czasowej Peek odczytuje jako czas włoski – taki sam jak w Polsce – a skany z samą datą pozostawia bez godziny.

**Z zagranicy do Włoch.** Gdy [Chronopost](carrier:chronopost) lub DPD wiezie paczkę do Włoch i przekazuje ją BRT, Peek uzupełnia 14-cyfrowy numer o wcześniejsze skany partnera, a zanim BRT ją zarejestruje – pokazuje jej drogę do Włoch.

Gdy masz przesyłkę otwartą na ekranie albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza status do każdych 10 minut, a w dniu doręczenia do każdych 2 minut od 6:00 czasu lokalnego (albo od pół godziny przed oknem czasowym DPD lub Chronopost), dopóki BRT lub DPD odpowiada bezpośrednio (co 5 minut, gdy sprawdzenie otwiera przeglądarkę). W pozostałym czasie sprawdza co godzinę – również po dwóch dniach bez nowego skanu lub po 12 godzinach ze statusem „Zarejestrowana” bądź „Przyjęta przez przewoźnika”. Gdy odpowiadają wyłącznie serwisy śledzące, sprawdza co godzinę, co 3 godziny po dobie bez skanu i do każdych 15 minut w dniu dostawy, o ile masz przesyłkę otwartą lub włączone powiadomienia. Po przekazaniu do DPD Polska Peek przejmuje harmonogram tego przewoźnika. Powiadomienia ustawisz na „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”.

## Najczęstsze pytania o śledzenie BRT

### Gdzie jest teraz moja paczka BRT?

Wskazuje to najnowszy wiersz historii: `ARRIVED AT DEPOT` podaje oddział w kolumnie *Filiale*, a `FOR DELIVERY` oznacza, że paczka jest u kuriera. Brak wpisów? Prawdopodobnie BRT jeszcze jej nie odebrało i Peek pokaże „Oczekiwanie na przewoźnika BRT” (albo „Oczekiwanie na pierwszy skan przewoźnika BRT” przy kodzie `EOB` przed wyborem przewoźnika).

### Czy BRT to to samo co DPD?

Obie firmy należą do grupy Geopost: BRT obsługuje sieć krajową we Włoszech, a paczki drogowe wysyłane z Włoch do reszty Europy przekazuje sieciom DPD.

### Ile idzie paczka wysłana przez BRT?

Orientacyjnie BRT podaje 24–48 godzin na terenie Włoch oraz 48–72 godziny w transporcie drogowym do większości krajów Europy. Z kolei *Carta dei servizi* (wydanie z lipca 2026 r., dla paczek do 31,5 kg) wyznacza cel jakościowy na poziomie 96% przesyłek doręczanych w 1–2 dni robocze we Włoszech oraz w 2–10 dni roboczych za granicę.

### Kto doręcza w Polsce paczkę nadaną przez BRT we Włoszech?

Zazwyczaj [DPD Polska](carrier:dpd-pl) pod własnym 14-cyfrowym numerem DPD. Przy przesyłce jednopaczkowej, dla której BRT udostępnia ten numer, Peek automatycznie przełącza śledzenie na DPD Polska, gdy tylko pojawi się tam aktualny datowany skan.

:::sources
- [BRT: Strona główna (EN)](https://www.brt.it/en/) – szybkie śledzenie po BRTcode
- [BRT: FAQ (EN)](https://www.brt.it/en/customer-service/faq/) – BRTcode, nieobecność, składowanie, orientacyjne czasy dostaw, szkody i limit 20 kg
- [BRT: Codes and Support (EN)](https://www.brt.it/en/customer-service/codes-and-support/) – gdzie znaleźć BRTcode
- [BRT: Ostrzeżenie o phishingu SMS i e-mail (EN)](https://www.brt.it/en/news/2026/04/16/alert-phishing-sms-email/) – komunikat z 16 kwietnia 2026 r. i cechy fałszywych wiadomości
- [BRT: Czym jest kod bezpieczeństwa? (IT)](https://www.brt.it/it/faq/cose-il-codice-di-sicurezza/) – 3-cyfrowy kod bezpieczeństwa odrębny od numeru śledzenia
- [BRT: Rintraccia spedizione (EN)](https://vas.brt.it/vas/sped_numspe_par.htm?lang=en) – długości numerów, okresy 12 i 2 miesięcy oraz wersje językowe
- [BRT: Pomoc do szczegółów przesyłki (EN)](https://vas.brt.it/vas/aiuto/aiuto.hsm?urlaiuto=sped_det_show.htm&lang=en) – oddziały, składowanie, opłaty i przekierowanie do DPD
- [BRT: BRT-fermopoint (EN)](https://www.brt.it/en/brt-fermopoint/) – punkty partnerskie, weekendy i 10 dni na odbiór
- [BRT: Locker (EN)](https://www.brt.it/en/network/locker/) – automaty paczkowe, dostęp 24/7, kod QR, PIN i limit 20 kg
- [BRT: Odbiór w oddziale BRT (EN)](https://www.brt.it/en/faq/can-i-pick-up-my-shipment-at-a-brt-branch/) – odbiór przez pełnomocnika i wymagane dokumenty
- [BRT: Support (EN)](https://services.brt.it/en/support) – formularze zgłoszeniowe według sprawy, brak telefonu
- [BRT: Wyszukiwarka oddziałów (EN)](https://services.brt.it/en/customer-service/search-for-a-branch) – adresy i dane oddziałów BRT
- [BRT: Carta dei servizi, PDF (IT)](https://www.brt.it/wp-content/uploads/sites/275/2026/11/261105-Carta-dei-Servizi.pdf) – lipiec 2026: paczki do 31,5 kg, Geopost, próby doręczenia, 10 dni składowania, terminy 8/7 dni, 96% w 1–2 i 2–10 dni roboczych, 800 278 708, assistenzaclienti@brt.it i 45 dni
- [BRT: Carta dei servizi (IT)](https://www.brt.it/it/carta-dei-servizi/) – reklamacja form-agcom, koncyliacja i AGCOM
- [BRT: Modulo di Reclamo, PDF (IT)](https://www.brt.it/wp-content/uploads/sites/275/2026/02/2.-Modulo-Reclamo.pdf) – prawo odbiorcy i nadawcy do reklamacji
- [BRT: Modulo di Conciliazione, PDF (IT)](https://www.brt.it/wp-content/uploads/sites/275/2026/02/3.-Modulo-di-Conciliazione-con-istruzioni.pdf) – conciliazioni@brt.it, składka Casa del Consumatore i jej zwrot
- [BRT: Condizioni generali, PDF (IT)](https://www.brt.it/wp-content/uploads/sites/275/2026/01/CONDIZIONI-GENERALI-CONTRATTO-TARIFFARIO_IT_CON-LOGO_REV-31.pdf) – czas składowania uzgodniony z nadawcą
- [DPD: Strona główna (EN)](https://www.dpd.com/en/) – marki grupy Geopost w Europie
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formaty numerów, statusy, strefy czasowe, przekazanie do DPD i częstotliwość sprawdzania w Peek
:::
