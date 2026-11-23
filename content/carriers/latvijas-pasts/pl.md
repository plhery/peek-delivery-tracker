---
title: Śledzenie Latvijas Pasts: numery, statusy i automaty
description: Śledź przesyłkę Latvijas Pasts (poczty łotewskiej): format numeru, znaczenie statusów, automaty paczkowe, nieudane doręczenia i kontakt.
slug: sledzenie-latvijas-pasts
published: 2026-11-23
updated: 2026-11-23
---

Latvijas Pasts to państwowy operator pocztowy Łotwy z największą w kraju siecią automatów paczkowych. Przesyłkę sprawdzisz na [mans.pasts.lv/track](https://mans.pasts.lv/track?locale=en-GB) — strona otwiera się po łotewsku, ale ma przełącznik języka, m.in. na angielski — albo wklejając numer w pole powyżej. Numer znajdziesz w wiadomości od sklepu lub nadawcy albo w SMS-ie od kuriera przed doręczeniem.

## Numer śledzenia Latvijas Pasts

Przesyłka nadana w Latvijas Pasts ma 13-znakowy [międzynarodowy numer pocztowy](guide:tracking-number-formats): dwie litery, dziewięć cyfr i końcówkę `LV`. Pierwsze dwie litery wskazują rodzaj usługi:

| Prefiks (przykłady fikcyjne) | Rodzaj przesyłki |
| --- | --- |
| `RR482917367LV` | Przesyłka polecona (Standard +): wydawana za podpisem. |
| `LA482917367LV` | Przesyłka rejestrowana (Standard): śledzona na każdym etapie, wrzucana do skrzynki bez podpisu. |
| `CA482917367LV` | Paczka: otrzymujesz awizo do odbioru w placówce pocztowej. |
| `EE482917367LV` | Express Mail Service (EMS / Premium). |

Przesyłki Simple i Economy nie są rejestrowane na żadnym etapie, więc nie można ich śledzić, a w razie uszkodzenia nie przysługuje odszkodowanie. Jeśli nie masz numeru, przesyłka mogła zostać nadana właśnie tak; zobacz [jak znaleźć numer śledzenia](guide:find-tracking-number).

Gdzie szukać: SMS od kuriera podaje numer przesyłki, orientacyjną godzinę doręczenia i link do przekierowania. SMS z automatu paczkowego zawiera adres automatu, termin odbioru oraz kod otwarcia skrytki.

Inne kody kraju oznaczają innych operatorów: `…LT` to [Lietuvos paštas](carrier:lietuvos-pastas), a `…EE` to [Omniva](carrier:omniva). Przesyłka z zagranicy zachowuje swój numer z kraju nadania; mans.pasts.lv/track również go obsługuje, gdy Latvijas Pasts otrzyma przesyłkę lub jej dane — zagraniczne skany pokazują wtedy nazwę kraju zamiast łotewskiej placówki.

## Co oznaczają statusy Latvijas Pasts

Oficjalne śledzenie podaje datę, godzinę i nazwę łotewskiej placówki przy każdym etapie. W angielskiej wersji komunikatów, z której korzysta Peek, typowe statusy pojawiają się w takiej kolejności:

| Status Latvijas Pasts | Co to oznacza dla ciebie |
| --- | --- |
| `Electronic data about shipment received` | [Dane od nadawcy](guide:tracking-statuses) dotarły do Latvijas Pasts, ale operator nie ma jeszcze paczki. |
| `Item information received` | Nadawca zarejestrował przesyłkę; Latvijas Pasts jeszcze jej nie otrzymało. |
| `Item received from sender` | Latvijas Pasts odebrało przesyłkę od nadawcy. |
| `Item posted / received from customer` | Nadano w placówce pocztowej; taki sam opis ma skan nadania z poczty zagranicznej. |
| `Item sent to the processing facility` | Przesyłka opuściła placówkę nadawczą w drodze do sortowni. |
| `Item arrived at office of exchange` | Dotarła do międzynarodowego urzędu wymiany w Rydze lub za granicą. |
| `Item sent to the Customs` | Przedstawiona do kontroli celnej w urzędzie wymiany przy wywozie. |
| `Item returned from Customs` | Zwolniona przez urząd celny — to nie jest zwrot do nadawcy. |
| `Item sent abroad` | Wysłana z urzędu wymiany do kraju docelowego. |
| `Item sent abroad (EDI-received)` | Poczta zagraniczna wysłała przesyłkę na Łotwę; Latvijas Pasts wie o tym z danych elektronicznych. |
| `Item accepted for temporary storage` | Przesyłka z zagranicy umieszczona w czasowym składowaniu pod dozorem celnym przed odprawą. |
| `Electonic notification (SMS) of customs clearance sent to recipient` | Wysłano do ciebie SMS w sprawie odprawy celnej (literówka pochodzi od Latvijas Pasts). Paczka nadal czeka w magazynie czasowym. |
| `Customs clearance completed, item to be delivered to recipient` | Odprawa celna zakończona; przesyłka jedzie do placówki doręczającej. |
| `Item arrived at processing facility`, `Item arrived at sorting centre` | Dotarła do sortowni. |
| `Item sent to next processing facility` | W drodze do kolejnej sortowni. |
| `Item sent to delivery facility` | W drodze do placówki doręczającej lub wydającej. |
| `Item received at delivery office` | W placówce doręczającej; wciąż może stamtąd wyjechać do doręczenia. |
| `Item redirected` | Przekierowana w inne miejsce, np. do innego punktu odbioru (zobacz przekierowanie poniżej). |
| `Item address re-labelled` | Otrzymała nową etykietę adresową i rusza pod nowy adres. |
| `Item handed over for delivery` | Wydana do doręczenia. |
| `Unsuccessful delivery attempt` | Nieudana próba doręczenia (zobacz poniżej). Gdy pojawia się w tej samej sekundzie co `Item address re-labelled`, oznacza przekierowanie: paczka jedzie dalej, a Peek pokazuje „W transporcie”, nie „Próba doręczenia”. |
| `Notification - pick-up invitation printed`, `Electronic notification (SMS) - pick-up invitation sent to recipient` | Do odbioru: placówka wydrukowała awizo lub wysłała zaproszenie po odbiór SMS-em. |
| `Item arrived at pick-up point` | Skan poczty zagranicznej: przesyłka czeka w punkcie odbioru za granicą (pokazuje kraj, nie konkretny punkt). |
| `Item delivered` | Dostarczona — również gdy poczta kraju docelowego doręczy przesyłkę wysłaną z Łotwy. |

Status `Item returned from Customs` często budzi niepokój, ale oznacza wyłącznie powrót przesyłki z kontroli celnej do dalszego transportu, a nie zwrot do nadawcy. `Item awaiting next planned cycle` oznacza oczekiwanie w urzędzie wymiany na kolejną wysyłkę. Przesyłki ze sklepów kierowane przez węzeł tranzytowy mają też własne komunikaty (`Parcel notify received`, `Parcel data received`, `Parcel has arrived at transit location`, `Parcel sent to destination country`, `Parcel has arrived in last mile`, `Parcel has gone thought last mile customs`). O cłach i opłatach UE dla zakupów spoza UE przeczytasz w poradniku [paczki zatrzymane przez urząd celny](guide:customs).

Jeśli wyszukiwarka pokazuje `No information received on the tracking of the postal item. Please, try again!`, sprawdź numer lub poczekaj: przesyłka mogła jeszcze nie zostać zeskanowana, a paczka z zagranicy pojawia się dopiero po przesłaniu danych przez pocztę nadania. `Postal item number entered incorrectly. Please check it and enter it again.` oznacza literówkę. Gdy poprawny numer przez kilka dni pozostaje nieznany, zapytaj nadawcę, czy wybrał przesyłkę rejestrowaną: Simple i Economy nigdy się nie pojawiają.

## Automaty paczkowe, kurier i nieudane doręczenia

**Automaty paczkowe (pakomāti).** Po umieszczeniu paczki w automacie otrzymujesz SMS z adresem, terminem odbioru i sześciocyfrowym kodem (oraz e-mail, jeśli nadawca podał adres). Paczka czeka **7 dni kalendarzowych**; jeśli w trzecim dniu nie została odebrana, kod wysyłany jest ponownie, a przed upływem terminu przychodzi przypomnienie. Po siedmiu dniach przesyłka uznawana jest za niedoręczoną i wraca do nadawcy — chyba że obowiązuje dłuższy łączny okres przechowywania, wtedy trafia do najbliższego punktu obsługi klienta na **30 dni kalendarzowych**. Jeśli kod nie dotarł, poproś nadawcę o kontakt z Latvijas Pasts: numer telefonu poprawiany jest wyłącznie na wniosek nadawcy. Maksymalne wymiary to 30 kg i 38 × 38 × 58 cm (w automatach zewnętrznych do 60 cm długości).

**Automaty Unisend.** Paczka wysłana z Łotwy do automatu Unisend na Litwie lub w Estonii również czeka 7 dni kalendarzowych; sześciocyfrowy kod przychodzi SMS-em i e-mailem (na łotewskie, litewskie i estońskie numery) i jest ponawiany po 48 godzinach. Nieodebrana paczka wraca od razu do nadawcy.

**Kurier pod adres.** Latvijas Pasts podaje, że podejmuje do trzech prób doręczenia. Jeśli nie ma cię w domu i nie przekierowałeś paczki, kurier zostawia zawiadomienie i ponawia próbę; po kolejnych nieudanych próbach przesyłka trafia na przechowanie, skąd można ją odebrać w Poczcie Głównej lub ustalić inną opcję z obsługą klienta (okres przechowania ustala nadawca). Po nieudanym doręczeniu Express Mail (EMS) w skrzynce zostaje zawiadomienie „You have received a shipment” — zadzwoń pod wskazany na nim numer z numerem przesyłki pod ręką.

**Placówka pocztowa lub Customer Centre.** Otrzymasz SMS lub pisemne awizo ze wskazaną placówką. Zabierz ważny dokument tożsamości; prawo jazdy wystarcza dla przesyłek poleconych i ubezpieczonych o wartości do 150 €. Paczkę odbierzesz także bez awiza, jeśli znasz jej numer, albo możesz zadzwonić pod numer z awiza i zamówić doręczenie pod adres z opłatą przy odbiorze. Inna osoba może odebrać przesyłkę z twoim upoważnieniem oraz paszportem lub dowodem osobistym. Dla przesyłek poleconych i ubezpieczonych po 5 dniach przychodzi drugie awizo, a po 30 dniach od pierwszego przesyłka wraca do nadawcy. Jeśli na etykiecie z zagranicznego sklepu widnieje tylko część twojego imienia lub nazwiska, zabierz dokument tożsamości i dowód zamówienia.

**Przekierowanie.** Jeśli nadawca podał twój numer telefonu albo zapisałeś się na powiadomienia SMS, wiadomość zawiera link do przekierowania przesyłki do automatu paczkowego (0,00 € z VAT), punktu obsługi klienta (1,89 € z VAT), pod inny adres kurierem (4,99 € z VAT) lub bezpłatnej usługi „Postman at home”, jeśli jest dostępna pod twoim adresem. Nie przekierujesz przesyłki już włożonej do automatu ani objętej kontrolą celną, poleconej lub ubezpieczonej z potwierdzeniem odbioru do automatu, pobraniowej powyżej 38 × 38 × 58 cm do automatu ani przesyłki bez numeru telefonu. Zapisując się na SMS-y, możesz też wskazać ulubiony automat paczkowy.

## Przesyłka Latvijas Pasts zagubiona, uszkodzona lub opóźniona

Reklamację zagubionej lub uszkodzonej paczki z automatu składa wyłącznie nadawca (w terminie 6 miesięcy zgodnie z regulaminem automatów), więc przy zakupach online napisz do sklepu. Wniosek zawiera opis zawartości, wartość z dowodem zakupu (rachunek, faktura lub potwierdzenie przelewu) oraz zdjęcia uszkodzeń; Latvijas Pasts rozpatruje zgłoszenie w ciągu miesiąca, a nadawca może zrzec się odszkodowania na rzecz odbiorcy.

Przy przesyłce poleconej lub ubezpieczonej doręczanej przez kuriera sprawdź zawartość w jego obecności, aby sporządzić i podpisać protokół. Jeśli otworzysz paczkę samodzielnie i wykryjesz uszkodzenie, zrób zdjęcia i zgłoś się do nadawcy po wymianę lub zwrot pieniędzy — Latvijas Pasts wskazuje, że odszkodowanie nie należy wówczas do jego odpowiedzialności.

Gdy przesyłka wysłana za granicę nie dotarła, nadawca może zlecić międzynarodowe poszukiwanie: przed upływem miesiąca jest płatne, a po 30 dniach od nadania bezpłatne.

Śledzenie stoi w miejscu lub paczka utknęła na cle? Zobacz [śledzenie, które się nie aktualizuje](guide:tracking-not-updating) oraz [paczki zatrzymane przez urząd celny](guide:customs).

## Kontakt i obsługa klienta Latvijas Pasts

Kanały kontaktu podane na pasts.lv:

- **Infolinia:** +371 27008001 (w regulaminie automatów również +371 67008001).
- **E-mail:** info@pasts.lv.
- **Godziny pracy:** poniedziałek–piątek 8:00–19:00, sobota 9:00–17:00, niedziela nieczynne.
- **Zgłoszenie problemu:** formularz na [mans.pasts.lv/complaint](https://mans.pasts.lv/complaint), rozpatrywany w godzinach pracy obsługi klienta.

> Latvijas Pasts ostrzega przed fałszywymi SMS-ami i e-mailami z prośbą o potwierdzenie adresu lub opłacenie cła na podrobionych stronach oraz przed rzekomą wyprzedażą „nieodebranych paczek”. Prawdziwe e-maile zawsze kończą się na @pasts.lv, oficjalna strona to pasts.lv, a poczta nigdy nie prosi w SMS-ach ani e-mailach o dane bankowe, dane karty ani potwierdzenie przez Smart-ID.

## Śledzenie Latvijas Pasts w Peek

[Peek](/) od razu rozpoznaje jako Latvijas Pasts numer pocztowy zaczynający się od C, E, L lub R i kończący na `LV`, jeśli jego cyfra kontrolna jest prawidłowa. Dla pozostałych poprawnych numerów z końcówką `LV`, takich jak `VV482917367LV`, odpytuje jednocześnie Latvijas Pasts i [Chronopost](carrier:chronopost), pokazując „Latvijas Pasts ma tę przesyłkę.”, gdy numer istnieje, lub „Jeszcze nie znaleziono u: … Peek będzie sprawdzać dalej po dodaniu.”. Przy błędnej cyfrze kontrolnej Latvijas Pasts nie jest odpytywane, a ręczny wybór nic nie daje (Peek informuje, że „nie wydaje takich numerów”): potwierdź numer u nadawcy. Link do mans.pasts.lv/track z numerem zawsze wybiera Latvijas Pasts, a wpisanie „Latvian Post” w wyszukiwarce przewoźników też go znajduje. Dopóki Latvijas Pasts nie zna numeru, przesyłka ma status „Oczekiwanie na przewoźnika Latvijas Pasts”; jeśli po dwóch dniach wciąż brak odpowiedzi, Peek wyświetla „Po dwóch dniach przewoźnik nadal nie zna tego numeru. Sprawdź go, wybierz innego przewoźnika lub usuń przesyłkę.” i przechodzi na sprawdzanie raz dziennie, a po tygodniu — raz w tygodniu.

Przesyłkę z zagranicy Peek śledzi najpierw u poczty nadania. Gdy ta wskaże Łotwę jako kraj docelowy (albo nie poda kraju, a ty jesteś na Łotwie), Peek odpytuje również Latvijas Pasts tym samym numerem i przełącza się na nie przy pierwszym nowym skanie, zachowując wcześniejszą historię. Aby od razu śledzić ją w Latvijas Pasts, wybierz „Latvijas Pasts” przyciskiem „Zmień” podczas dodawania lub „Zmień przewoźnika” na już dodanej przesyłce.

Czasy Latvijas Pasts nie mają strefy czasowej: Peek odczytuje je według zegara miejsca skanu (czas łotewski dla łotewskich placówek) i prezentuje oryginalne angielskie opisy Latvijas Pasts, w tym `Item delivered`. Może pokazać placówkę, w której przesyłka czeka na odbiór, nie podaje jednak przewidywanego dnia doręczenia ani wagi.

Peek sprawdza status nawet co 10 minut, gdy przesyłka jest otwarta w aplikacji lub w dniu doręczenia przy włączonych powiadomieniach; w dniu, gdy Latvijas Pasts przekazuje paczkę do doręczenia, od 6:00 czasu lokalnego (czasu łotewskiego dla łotewskich placówek) sprawdza co 2 minuty, dopóki odpowiada oficjalny tracker Latvijas Pasts. W przeciwnym razie sprawdza co godzinę. Po 12 godzinach w statusie „Zarejestrowana” lub „Przyjęta przez przewoźnika” albo po dwóch dniach bez nowości sprawdza co godzinę nawet wtedy, gdy przesyłka jest otwarta, a po siedmiu dniach w statusie „Zarejestrowana” — co 6 godzin. Jeśli tracker Latvijas Pasts nie odpowiada i Peek musi korzystać z zewnętrznych usług śledzenia, sprawdza co godzinę, co 3 godziny po dniu bez nowego skanu, a w dniu doręczenia nawet co 15 minut, gdy przesyłka jest otwarta lub ma włączone powiadomienia. Gdy doręczenie przejmuje inny przewoźnik, Peek przejmuje jego tempo.

Jeśli przez cztery dni nie ma nowego skanu w statusie „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie”, Peek oznacza przesyłkę datą ostatniego skanu, na przykład „Brak aktualizacji od pon., 12 paź”. Wyświetla wtedy informację „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika Latvijas Pasts.” z linkiem „Poproś przewoźnika Latvijas Pasts o pomoc”. Po ponad trzech tygodniach bez nowości Peek pokazuje: „Od ponad trzech tygodni nic nowego. Jeśli przesyłka wciąż nie dotarła, zapytaj sprzedawcę. Peek dalej pyta przewoźnika Latvijas Pasts.”

W powiadomieniach możesz wybrać „Każdy skan”, „Ważne etapy” albo „Tylko doręczenie”.

## Pytania o śledzenie Latvijas Pasts

### Czym jest Latvijas Pasts?

To państwowy narodowy operator pocztowy Łotwy, świadczący usługi pocztowe, logistyczne i finansowe w kraju i za granicą pod adresem pasts.lv.

### Czy Unisend to Latvijas Pasts?

Nie. Unisend to marka paczkowa litewskiej poczty Lietuvos paštas. Oba podmioty prowadzą wspólną sieć automatów paczkowych, dzięki czemu klienci Latvijas Pasts mogą wysyłać paczki do automatów Unisend na Litwie i w Estonii.

### Czy mogę śledzić międzynarodową przesyłkę Latvijas Pasts?

Tak, jeśli została nadana jako rejestrowana. mans.pasts.lv/track wyświetla także skany poczty zagranicznej z nazwą kraju zamiast placówki, a przycisk „Pārrobežu izsekošana” prowadzi do zewnętrznego narzędzia śledzenia. Za granicą przesyłkę doręcza poczta kraju docelowego.

### Czy zwykła poczta z Łotwy ma numer śledzenia?

Nie zawsze. Przesyłki Simple i Economy nie są rejestrowane i nie mają śledzenia; polecone, rejestrowane Standard, paczki oraz EMS można śledzić.

:::sources
- [Latvijas Pasts: Customer support](https://pasts.lv/en/client-support) – prefiksy usług, brak odszkodowania Simple, poszukiwanie międzynarodowe, godziny pracy, infolinia
- [Latvijas Pasts: Contacts](https://pasts.lv/en/about-us/contacts) – infolinia, godziny pracy
- [Latvijas Pasts: Receive a parcel](https://pasts.lv/en/private-person/receive-parcel) – sposoby odbioru, upoważnienie
- [Latvijas Pasts: Receive by courier](https://pasts.lv/en/private-person/receive-parcel/receive-by-courier) – SMS kuriera, trzy próby, przechowanie, Poczta Główna, Postman at home
- [Latvijas Pasts: Receive item in a parcel locker](https://pasts.lv/en/useful/receiving-items-private/receive-in-parcel-locker) – SMS z automatu, 7 dni, przypomnienie, 30 kg i 38 × 38 × 58 cm, ulubiony automat
- [Latvijas Pasts: Delivery to address](https://pasts.lv/en/useful/receiving-items-private/delivery-to-address) – drugie awizo po 5 dniach, zwrot po 30 dniach, awizo EMS, niepełne imię i nazwisko
- [Latvijas Pasts: Receive at a Customer Centre](https://pasts.lv/en/private-person/receive-parcel/receive-at-customer-centre) – awizo, dokument tożsamości, prawo jazdy do 150 €, odbiór bez awiza, odpłatne doręczenie pod adres
- [Latvijas Pasts: Shipment redirection](https://pasts.lv/en/services/private-person/receiving/redirection-of-shipments) – link do przekierowania, opcje, cennik z VAT, wyłączenia
- [Latvijas Pasts: Lost or damaged parcel locker shipments](https://pasts.lv/en/services/private-person/shipment-management/lost-or-damaged-parcel-locker-shipments) – reklamacja nadawcy, dokumenty, rozpatrzenie w ciągu miesiąca
- [Latvijas Pasts: Damaged shipment](https://pasts.lv/en/useful/faulty-shipment) – sprawdzenie przy kurierze, roszczenia wobec nadawcy
- [Latvijas Pasts: Pakomātu pakalpojumu noteikumi](https://pasts.lv/media/789/download/Pakomatu_noteikumi.pdf) – sześciocyfrowy kod, e-mail, ponowny kod 3. dnia, zwrot po 7 dniach lub 30 dni w punkcie obsługi, 6 miesięcy na reklamację, automaty Unisend, info@pasts.lv i +371 67008001
- [Latvijas Pasts: Pasargā sevi no krāpniekiem](https://pasts.lv/pasarga-sevi-no-krapniekiem) – ostrzeżenia przed oszustwami SMS i e-mail
- [Latvijas Pasts: Mans pasts, track shipment](https://mans.pasts.lv/track?locale=en-GB) – śledzenie online, wybór języka, śledzenie międzynarodowe
- [Latvijas Pasts: Complaint form](https://mans.pasts.lv/complaint) – formularz zgłoszeniowy
- [Latvijas Pasts: About us](https://pasts.lv/en/about-us/par-pastu) – państwowy operator pocztowy, usługi
- [Latvijas Pasts: Joint parcel locker network with UNISEND](https://pasts.lv/en/news/latvijas-pasts-and-unisend-launch-their-joint-parcel-locker-network) – Unisend jako marka Lietuvos paštas, wspólna sieć automatów
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – dane odczytywane przez Peek, statusy i etapy, odrzucanie błędnych numerów, przejęcie z poczty zagranicznej
:::
