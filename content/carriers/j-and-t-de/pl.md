---
title: J&T Express Germany śledzenie: numer JTDE i statusy
description: Śledź paczkę J&T Express w Niemczech: jak wygląda numer JTDE, co oznaczają angielskie statusy i co zrobić, gdy przesyłka stoi, ginie lub nie dociera.
slug: jt-express-niemcy-sledzenie
published: 2026-11-16
updated: 2026-11-16
---

J&T Express Germany (J&T Express Germany GmbH, Frankfurt nad Menem) to niemiecka sieć grupy kurierskiej J&T Express: doręcza paczki na terenie Niemiec. Jeśli sklep wysłał Twoje zamówienie na niemiecki adres przez J&T, numer śledzenia zaczyna się od `JTDE`: wklej go w pole powyżej albo sprawdź bezpośrednio na [stronie śledzenia J&T pod adresem jtexpress.de](https://www.jtexpress.de/tracking). Numer przekazuje sklep, który wysłał zamówienie — znajdziesz go w wiadomości o wysyłce lub na stronie zamówienia.

## Numer śledzenia J&T Express Germany: jak wygląda

Według FAQ J&T numery przesyłek w tej sieci zaczynają się od JTDE. Peek zna dla nich tylko jeden format: `JTDE` i 12 cyfr, łącznie 16 znaków. Na zmyślonych cyfrach wygląda to tak:

| Numer (zmyślony) | Co oznacza |
| --- | --- |
| `JTDE000000000000` | `JTDE` i 12 cyfr: paczka J&T Express Germany. Peek od razu wskazuje J&T Express Germany. |
| `JTDE00000000000` | O jedną cyfrę za mało: to nie jest format J&T Express Germany i Peek nie rozpoznaje żadnego przewoźnika. Sprawdź, czy nie pominąłeś cyfry. |

Gdy po `JTDE` jest o jedną cyfrę za mało lub za dużo i żaden przewoźnik nie odpowiada dla takiego numeru, strona przesyłki na peektracker.com zwraca na to uwagę („Zaczyna się jak numer śledzenia przewoźnika J&T Express Germany, ale brakuje jednego znaku: może jest w nim literówka. Porównaj go z wiadomością o wysyłce.” albo „… ale ma o jeden znak za dużo: może jest w nim literówka. Porównaj go z wiadomością o wysyłce.”).

**Gdzie go znaleźć.** J&T podaje, że numer udostępnia nadawca: widnieje na stronie zamówienia w sklepie internetowym, w e-mailu potwierdzającym zamówienie, w e-mailu lub SMS-ie o wysyłce, w wiadomościach zapowiadających lub potwierdzających doręczenie oraz na etykiecie paczki. Jeśli nie możesz go znaleźć, J&T odsyła do nadawcy lub do obsługi klienta platformy zakupowej. Zobacz też, [jak znaleźć numer śledzenia](guide:find-tracking-number).

Wyszukiwarka na stronie J&T przyjmuje naraz do 10 numerów rozdzielonych spacjami lub przecinkami.

**Podobne numery innych sieci.** Sieci J&T w innych krajach mają własne strony śledzenia i własne oznaczenia: `JTUK` i 12 cyfr to J&T Express UK, `JTAU` i 12 cyfr to J&T Express Australia, `JTE` i 12 cyfr to J&T Express Middle East, natomiast `JT` z 13 cyframi stosuje J&T w Azji Południowo-Wschodniej oraz w Chinach (J&T Express China). Tylko `JTDE` należy do sieci niemieckiej. Jeśli strona J&T odpowiada `Keine Abfrageergebnisse` (`No tracking records yet` w wersji angielskiej) albo peektracker.com cały czas pokazuje „Oczekiwanie na przewoźnika J&T Express Germany”, porównaj numer z e-mailem ze sklepu lub poproś sprzedawcę o potwierdzenie.

## Statusy śledzenia J&T Express Germany: co oznaczają

W śledzeniu J&T każdy skan składa się wyłącznie z daty, godziny i jednego zdania — bez nazwy miejscowości. Strona jtexpress.de otwiera się po niemiecku i nie ma polskiej wersji; gdy w menu języka przełączysz ją na angielski lub chiński, wczytuje skany na nowo w wybranym języku. Peek pobiera komunikaty po angielsku i nie tłumaczy samych opisów skanów, dlatego zobaczysz poniższe zdania w kolejności, w jakiej pojawiają się na trasie paczki:

| Komunikat J&T | Co oznacza dla Ciebie | Peek pokazuje |
| --- | --- | --- |
| `The shipment has been successfully picked up and is being prepared for transportation` | J&T odebrał paczkę od nadawcy. | „Przyjęta przez przewoźnika” |
| `The shipment arrives at the distribution center and is prepared for transport to the next destination.` | Przesyłka dotarła do sortowni J&T. | „W transporcie” |
| `The shipment is dispatched from the transit center to the next destination` | Opuściła sortownię i jedzie do kolejnego punktu sieci. | „W transporcie” |
| `Your package has been loaded and is on its way to you` | Jest w samochodzie kuriera: wydana do doręczenia. | „W doręczeniu” |
| `Delivery failed because the address could not be found` | Kurier nie zdołał odnaleźć Twojego adresu. | „Próba doręczenia” |
| `Delivery failed, customer refused to accept` | Odbiorca odmówił przyjęcia paczki. | „Próba doręczenia” |
| `Your parcel has been delivered to the pickup point. Please check your email for details` | Kurier zostawił paczkę w punkcie odbioru; szczegóły odbioru J&T wysyła e-mailem. | „Do odbioru” |
| `Your package has been successfully delivered.` | Doręczona. J&T dopisuje sposób doręczenia (zobacz niżej). | „Dostarczona” |
| `Returns have begun` | Po nieudanym doręczeniu paczka wraca do nadawcy. | „Zwrócona nadawcy” |
| `The shipment has been returned` | Zwrot do nadawcy został zakończony. | „Zwrócona nadawcy” |

Komunikat spoza tej tabeli jest oceniany według wspólnych reguł, których Peek używa dla wszystkich przewoźników. Jeśli Peek nie rozpozna go także w ten sposób, wyświetla zdanie dokładnie tak, jak zapisał je J&T, i zalicza paczkę do etapu „W transporcie” — chyba że była już w doręczeniu lub dalej, bo wtedy dotychczasowy status zostaje zachowany.

Wiersz o załadunku (`Your package has been loaded and is on its way to you`) może pojawić się kilkakrotnie: J&T dopisuje go przy każdym wyjeździe kuriera na rejon. Wiersz potwierdzający doręczenie zawiera dalej sposób wydania paczki w nawiasach 【】 i kończy się formułką o obsłudze klienta. Peek usuwa tę końcową formułkę, a typ doręczenia — już bez nawiasów — zachowuje tylko wtedy, gdy jest jednym z sześciu wariantów oferowanych odbiorcom:

- `Left with neighbor`: zostawiona u sąsiada;
- `Front Door Porch`: pod drzwiami wejściowymi;
- `Front Desk Reception`: w recepcji;
- `Garden Yard`: w ogrodzie lub na podwórku;
- `Mailbox`: w skrzynce pocztowej;
- `Garage`: w garażu.

Paczka zostawiona u sąsiada ma więc w Peek opis `Your package has been successfully delivered. The delivery type is Left with neighbor.`

**U którego sąsiada?** Na stronie śledzenia J&T pod skanem doręczenia widnieje przycisk `Zustelldetails anzeigen` (`Click to view delivery details` w wersji angielskiej). Kliknij go i wpisz kod pocztowy adresu dostawy, aby zobaczyć nazwisko sąsiada i numer budynku; po pięciu błędnych próbach wpisania kodu pocztowego trzeba odczekać przed kolejną próbą. Peek odpytuje J&T wyłącznie po numerze przesyłki, dlatego pokazuje jedynie informację o pozostawieniu paczki u sąsiada — bez nazwiska i bez adresu. Usuwa również wszelkie imiona, nazwiska i numery telefonów z wierszy śledzenia J&T.

## Doręczenie J&T w Niemczech: sąsiedzi, miejsce zostawienia i zdjęcie

W historiach przesyłek znanych kodowi Peek kurier J&T ładuje paczkę na trasę doręczeniową, a następnie ją doręcza, zostawia w punkcie odbioru albo odnotowuje powód nieudanego doręczenia. Kod Peek nie wykazuje przekazywania przesyłek innemu przewoźnikowi: śledzenie J&T prowadzi paczkę aż do wydania. Kierowca, który dzwoni do drzwi, może pracować dla jednego z partnerów franczyzowych J&T: na stronie jtexpress.de firma poszukuje w całych Niemczech partnerów dysponujących zespołami do doręczeń na ostatniej mili.

**W doręczeniu.** `Your package has been loaded and is on its way to you` oznacza wyjazd paczki na rejon doręczeniowy. W zapisanych historiach ten wpis pojawia się rano, kilka godzin przed samym doręczeniem. Śledzenie J&T nie podaje ani przedziału godzinowego, ani przewidywanego dnia dostawy, więc Peek także nie może ich wyświetlić.

**Preferencje doręczenia.** Link w powiadomieniu o wysyłce prowadzi do [strony preferencji doręczenia J&T](https://www.jtexpress.de/delivery-preference) i zawiera zaszyfrowany numer przesyłki. Po potwierdzeniu kodu pocztowego możesz wybrać jedną z metod udostępnianych przez J&T: wydanie za podpisem Tobie, sąsiadowi (podajesz jego nazwisko oraz to, czy mieszka obok, czy na tym samym piętrze) albo w recepcji, bądź pozostawienie bez podpisu pod drzwiami wejściowymi, w ogrodzie, w skrzynce pocztowej lub w garażu. Według J&T kurier kieruje się Twoim wyborem, co pozwala uniknąć powtórnych wizyt i błędnych doręczeń, a podane dane służą wyłącznie do tej jednej dostawy. Przy wyborze miejsca bez podpisu strona ostrzega, że z chwilą pozostawienia tam paczki ryzyko jej utraty przechodzi na Ciebie. Jeśli aktualny etap przesyłki nie pozwala już na zmianę dyspozycji, strona wyświetla stosowną informację.

**Zdjęcie z doręczenia.** Zgodnie z polityką prywatności J&T dotyczącą potwierdzenia doręczenia (POD) kurier fotografuje paczkę przy przekazywaniu jej odbiorcy lub pozostawianiu w uzgodnionym miejscu, kadrując wyłącznie samą przesyłkę i niezbędny fragment otoczenia; osoby uchwycone przypadkowo są automatycznie i nieodwracalnie zamazywane. Co do zasady J&T usuwa zdjęcie po 6 miesiącach od doręczenia, ale jeśli w tym czasie wpłynie reklamacja lub zgłoszenie wyjaśniające, może przechowywać fotografię do zakończenia sprawy i udostępnić ją nadawcy w celu rozpatrzenia reklamacji.

**Odmowa przyjęcia lub nieznaleziony adres.** Po wpisie `Delivery failed, customer refused to accept` w historiach znanych kodowi Peek kurier następnego ranka ponownie ładował paczkę na kolejną próbę doręczenia. Po komunikacie `Delivery failed because the address could not be found` sprawdź adres w swoim zamówieniu. Jeśli brakuje w nim szczegółu, na przykład numeru mieszkania albo nazwiska na dzwonku lub domofonie, od razu powiadom sklep: zlecił wysyłkę dokładnie na adres podany przy zakupie. Także FAQ J&T zaleca niezwłoczny kontakt z nadawcą lub obsługą platformy zakupowej — po nadaniu paczki możliwość zmiany adresu zależy od jej bieżącego statusu w transporcie oraz zasad nadawcy. Po nieudanym doręczeniu może pojawić się również `Returns have begun`. Zobacz poradniki: [zmiana adresu dostawy](guide:change-delivery-address) oraz [nieudane doręczenie](guide:missed-delivery).

**Próby doręczenia i punkty odbioru.** Strona J&T nie podaje liczby prób doręczenia, czasu przechowywania przesyłek ani godzin pracy kurierów, nie publikuje też własnej mapy punktów odbioru, automatów paczkowych ani oddziałów. W śledzeniu J&T Express Germany może jednak pojawić się komunikat `Your parcel has been delivered to the pickup point. Please check your email for details` — po wyjeździe na rejon kurier zostawił wtedy paczkę w punkcie odbioru, a dane potrzebne do odbioru J&T przesyła w wiadomości e-mail.

## Paczka J&T opóźniona, zaginiona lub zwrócona

Przewoźnika wybrał sklep, dlatego przy zamówieniu internetowym pierwszym kontaktem zawsze jest sprzedawca. Zgodnie z prawem niemieckim (§ 475 niemieckiego kodeksu cywilnego, BGB), gdy kupujesz od przedsiębiorcy jako konsument, ryzyko przypadkowej utraty lub uszkodzenia rzeczy w transporcie ponosi sprzedawca — chyba że sam zleciłeś przewóz przewoźnikowi, którego sprzedawca wcześniej Ci nie wskazał.

**Brak ruchu w śledzeniu.** J&T nie publikuje czasów doręczenia dla odbiorców. Gdy paczka ma status „Przyjęta przez przewoźnika” lub „W transporcie” i przez cztery dni nie pojawia się żaden nowy wpis, Peek oznacza ją komunikatem „Brak aktualizacji od czterech dni” oraz pokazuje link „Poproś przewoźnika J&T Express Germany o pomoc”. Jeśli nadal nic się nie zmienia, skontaktuj się ze sklepem albo zadzwoń na infolinię J&T, podając numer `JTDE`. Więcej wskazówek znajdziesz w poradniku [śledzenie przesyłki się nie aktualizuje](guide:tracking-not-updating).

**Brak danych o numerze.** Numer, którego śledzenie J&T jeszcze nie zna, widnieje na peektracker.com jako „Oczekiwanie na przewoźnika J&T Express Germany”. Sklepy często generują numer listu przewozowego, zanim fizycznie przekażą paczkę kurierowi. Jeśli po dwóch dniach J&T wciąż nie rozpoznaje numeru i żadne inne źródło dla niego nie odpowiedziało, witryna wyświetla komunikat „Po dwóch dniach przewoźnik nadal nie zna tego numeru. Sprawdź go, wybierz innego przewoźnika lub usuń przesyłkę.”, a Peek sprawdza go odtąd raz dziennie, natomiast po tygodniu od dodania — raz w tygodniu. Potwierdź wtedy numer w sklepie i zapytaj, czy paczka faktycznie została wysłana.

**Dostarczona, ale nieodebrana.** Przeczytaj wiersz doręczenia w Peek: jeśli wymienia miejsce, takie jak `Mailbox` lub `Garage`, zajrzyj tam; jeśli widnieje `Left with neighbor`, popytaj sąsiadów albo sprawdź nazwisko sąsiada na stronie J&T po wpisaniu kodu pocztowego (zobacz wyżej). FAQ J&T radzi też upewnić się, czy przesyłki nie odebrali domownicy, sąsiedzi lub administracja budynku. Nadal jej nie ma? Zgłoś sprawę na infolinii J&T i poproś sklep o zwrot pieniędzy lub ponowną wysyłkę, podając numer śledzenia. Zobacz również [przesyłka dostarczona, ale nieodebrana](guide:delivered-not-received).

**Zaginiona, uszkodzona lub zwrócona.** Poproś sklep o zwrot środków albo wymianę towaru, podając numer śledzenia. FAQ J&T nie zawiera formularza reklamacyjnego, terminów ani stawek odszkodowań dla odbiorców — terminy zawiadomienia określa prawo niemieckie. Według § 438 niemieckiego kodeksu handlowego (HGB) widoczne uszkodzenie należy zgłosić najpóźniej przy odbiorze (wystarczy poinformować osobę doręczającą paczkę), a szkodę niewidoczną z zewnątrz — w ciągu siedmiu dni od doręczenia w formie tekstowej, na przykład e-mailem do J&T; dla zachowania terminu wystarczy wysłanie zgłoszenia na czas. Jeśli nie uda Ci się dojść do porozumienia z J&T w sprawie zagubionej lub uszkodzonej paczki, możesz złożyć bezpłatny wniosek online do komisji polubownej ds. pocztowych przy Federalnej Agencji Sieci (Schlichtungsstelle Post w Bundesnetzagentur), z której mogą korzystać zarówno odbiorcy, jak i nadawcy. Przesyłki z dużych sklepów internetowych są jednak zazwyczaj nadawane na warunkach specjalnych wyłączających postępowanie polubowne; w takiej sytuacji nadal możesz zwrócić się do serwisu konsumenckiego Bundesnetzagentur ds. usług pocztowych (Verbraucherservice Post). Zobacz też: [zagubiona paczka](guide:lost-parcel), [uszkodzona paczka](guide:damaged-parcel) oraz [zwrot paczki do nadawcy](guide:returned-to-sender).

## J&T Express Germany: obsługa klienta i kontakt

Kanały kontaktu J&T Express Germany odczytane na [stronie kontaktowej przewoźnika](https://www.jtexpress.de/contact) 16 listopada 2026 r.:

- **Telefon:** +49 6931090650 (069 31090650 z terenu Niemiec), od poniedziałku do piątku w godz. 8:00–20:00, nieczynne w dni świąteczne.
- **E-mail:** `de-jtservice@jtexpress.de`.
- **Czat na żywo:** przycisk „Chat starten” („Start Chat” na anglojęzycznej wersji strony), dostępny na stronie kontaktowej oraz na stronie FAQ.
- **Zdjęcia z doręczenia i ochrona danych:** `de-dpo@jtexpress.de` (adres wskazany w polityce prywatności POD).
- **Biuro:** J&T Express Germany GmbH, Am Giener 2, 55268 Nieder-Olm. Nota prawna (Impressum) podaje adres siedziby spółki: Wiesenhüttenstraße 11, 60329 Frankfurt am Main.

J&T nie informuje, w jakich językach odpowiada infolinia i czat; sama strona działa po niemiecku, angielsku i chińsku. W sprawie zamówienia ze sklepu internetowego zacznij jednak od sprzedawcy — to on zlecił wysyłkę w J&T. Przy zakupie na platformie marketplace skorzystaj z pomocy na stronie zamówienia. Gdy przesyłka utknie w drodze, link „Poproś przewoźnika J&T Express Germany o pomoc” w Peek otwiera stronę kontaktową J&T.

## Śledzenie paczki J&T Express Germany w Peek

[Peek](/) od razu rozpoznaje J&T Express Germany po numerze `JTDE` („Wykryty przewoźnik”), wpisanym ze spacjami lub bez, wielkimi albo małymi literami, i odpytuje bezpośrednio śledzenie J&T samym numerem: bez kodu pocztowego i bez zakładania konta. Działa również link `www.jtexpress.de/tracking` z parametrem `?q=` i numerem; jeśli w linku znajduje się numer o innym formacie, Peek odczytuje go tak, jakbyś wkleił sam numer. Gdy masz numer o innym kształcie niż `JTDE`, wklej go mimo to: Peek obsługuje ponad 3500 przewoźników. Ręczne wybranie J&T Express Germany przyciskiem „Zmień” dla innego formatu nie pomoże — Peek poinformuje, że J&T Express Germany „nie wydaje takich numerów”.

Peek pokazuje aktualny etap, każdy skan z dokładną godziną oraz moment doręczenia. J&T zapisuje każdy skan jako dokładny punkt w czasie, więc Peek wyświetla go w strefie czasowej Twojego urządzenia; na peektracker.com i w aplikacji na iPhonie przypomina o tym ostatnia linijka pod historią („Godziny w Twojej strefie czasowej: …”). Opisy skanów pozostają w angielskim brzmieniu J&T, nawet jeśli na jtexpress.de czytasz je po niemiecku. J&T nie udostępnia miejscowości, przewidywanego dnia doręczenia ani wagi paczki, dlatego Peek nie wyświetla mapy. Przy bardzo długiej historii zachowuje 100 najnowszych skanów. Przycisk „Otwórz stronę przewoźnika J&T Express Germany” na karcie przesyłki otwiera Twój numer bezpośrednio na stronie J&T.

Dopóki J&T nie zeskanuje paczki po raz pierwszy, Peek ponawia zapytania i wyświetla na peektracker.com „Oczekiwanie na przewoźnika J&T Express Germany” (albo po dwóch dniach bez rozpoznania numeru — prośbę o jego sprawdzenie). Gdy strona J&T jest nieosiągalna lub nie zna numeru, Peek odpytuje zewnętrzne serwisy śledzenia przesyłek i pokazuje ich historię w ich własnym brzmieniu. Jeżeli później zarówno śledzenie J&T, jak i serwisy śledzenia odpowiedzą pustą listą dla paczki mającej już zapisane skany, Peek zachowuje dotychczasowe wpisy, a gdy ten stan się utrzymuje, wyświetla komunikat: „Przewoźnik nie ma na razie nowych skanów. Te już znalezione zostają, a Peek sprawdza dalej.”

Peek sprawdza śledzenie co 10 minut, a gdy paczka jest w doręczeniu, co 2 minuty (w dniu wydania do doręczenia, od 6:00 czasu lokalnego i dopóki odpowiada własne śledzenie J&T; przez serwisy śledzenia przesyłek co 15 minut). Jeśli przez dwa dni nie ma nowych informacji (albo przez 12 godzin, gdy paczka wciąż ma status „Przyjęta przez przewoźnika”), Peek sprawdza ją już tylko co godzinę. Powiadomienia możesz ustawić na „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek nie może skontaktować się w Twoim imieniu z J&T ani ze sklepem, zmienić adresu dostawy ani zamówić ponownego doręczenia.

## Najczęstsze pytania o śledzenie J&T Express Germany

### Czy J&T Express Germany to prawdziwa firma kurierska?

Tak. J&T Express Germany GmbH z siedzibą we Frankfurcie nad Menem to niemiecka spółka grupy J&T Express — operatora kurierskiego założonego w 2015 roku w Indonezji i notowanego na giełdzie w Hongkongu. Od 1 września 2026 roku spółka widnieje w oficjalnym rejestrze operatorów pocztowych niemieckiego urzędu regulacyjnego Bundesnetzagentur (Anbieterverzeichnis Post). W całych Niemczech J&T rekrutuje partnerów franczyzowych do obsługi doręczeń i odbiorów ostatniej mili, a wszystkie komunikaty w powyższej tabeli statusów pochodzą z prawdziwych paczek J&T Express Germany.

### Który sąsiad odebrał moją paczkę J&T?

Sprawdzisz to na stronie śledzenia J&T: pod skanem doręczenia kliknij `Zustelldetails anzeigen` (`Click to view delivery details` po angielsku) i wpisz kod pocztowy adresu dostawy, aby zobaczyć nazwisko sąsiada oraz numer domu. Peek pokazuje wyłącznie wzmiankę `Left with neighbor`, bez nazwiska i adresu.

### Czy J&T Express ma punkty odbioru lub automaty paczkowe w Niemczech?

Niemiecka strona J&T nie wymienia żadnych punktów odbioru ani automatów, ale jeden ze znanych Peek komunikatów J&T Express Germany mówi, że kurier zostawił paczkę w punkcie odbioru, a szczegóły J&T wysyła e-mailem. Na stronie preferencji doręczenia możesz poprosić o wydanie paczki Tobie, sąsiadowi, w recepcji albo o zostawienie jej w wybranym miejscu pod Twoim adresem.

### Dlaczego śledzenie J&T nie znajduje mojego numeru?

Upewnij się, że numer zaczyna się od `JTDE` i ma po tym prefiksie dokładnie 12 cyfr: numery innych sieci J&T, na przykład brytyjskie `JTUK`, nie działają w sieci niemieckiej. Jeśli format się zgadza, J&T mógł jeszcze nie przejąć fizycznie paczki od nadawcy — jego strona zwraca wtedy `Keine Abfrageergebnisse` (`No tracking records yet`), a peektracker.com pokazuje „Oczekiwanie na przewoźnika J&T Express Germany”. Gdy po dwóch dniach ani J&T, ani inne źródło nadal nie rozpoznaje numeru, witryna prosi o jego weryfikację, zmianę przewoźnika lub usunięcie przesyłki. Jeśli po kilku dniach nadal brak wpisów, skontaktuj się ze sklepem.

### Jak skontaktować się z J&T Express w Niemczech?

Zadzwoń pod numer +49 6931090650 (od poniedziałku do piątku, 8:00–20:00), skorzystaj z czatu na żywo na jtexpress.de albo napisz na `de-jtservice@jtexpress.de`. W sprawie zakupów internetowych najpierw napisz jednak do sklepu, który nadał przesyłkę.

:::sources
- [J&T Express Germany: Track & Trace](https://www.jtexpress.de/tracking) – wyszukiwarka śledzenia, limit 10 numerów, wersje językowe strony i przeładowanie skanów, komunikat o braku wyników, dane sąsiada po weryfikacji kodu pocztowego i limit pięciu prób
- [J&T Express Germany: Frequently Asked Questions](https://www.jtexpress.de/faq) – prefiks JTDE i gdzie szukać numeru, paczka oznaczona jako doręczona lecz nieotrzymana, błędny adres, czat; brak publikowanych czasów dostawy, liczby prób i zasad reklamacji
- [J&T Express Germany: Delivery Preferences](https://www.jtexpress.de/delivery-preference) – link w wiadomości o dostawie, weryfikacja kodu pocztowego, opcje z podpisem i bez podpisu, przejście ryzyka przy zostawieniu bez podpisu, mniej powtórnych i błędnych doręczeń, użycie danych tylko do tej dostawy
- [J&T Express Germany: Contact Us](https://www.jtexpress.de/contact) – infolinia, godziny pracy, czat na żywo, e-mail i adres biura w Nieder-Olm
- [J&T Express Germany: Legal notice](https://www.jtexpress.de/legal-statement) – J&T Express Germany GmbH, siedziba we Frankfurcie nad Menem
- [J&T Express Germany: POD Privacy Policy](https://www.jtexpress.de/pod-policy) – zdjęcia potwierdzające doręczenie, automatyczne zamazywanie osób postronnych, usunięcie po 6 miesiącach, reklamacje i kontakt ds. ochrony danych
- [J&T Express Germany: Become Our Partner](https://www.jtexpress.de/career) – poszukiwanie partnerów franczyzowych w całych Niemczech do doręczeń i odbiorów ostatniej mili
- [J&T Express Germany: About Us](https://www.jtexpress.de/about) – założenie grupy w 2015 r. w Indonezji i notowanie na giełdzie w Hongkongu
- [Universal Parcel Scraper: J&T Express Germany](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/j-and-t-de) – nazwy, format JTDE, linki śledzenia, zdania ze śledzenia prawdziwych paczek i ich etapy, wyjazdy kuriera na rejon, typy doręczenia, pomijane elementy i strona pomocy
- [Universal Parcel Scraper: wspólne zapytanie sieci J&T](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/j-and-t-au) – angielskie komunikaty, dokładne czasy skanów, brak miejscowości, przewidywanego dnia dostawy i wagi, usuwanie nazwisk i numerów telefonów, limit 100 najnowszych skanów
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formaty numerów innych sieci J&T, brak przekazywania paczki innemu przewoźnikowi oraz zewnętrzne serwisy śledzenia jako zapasowe źródło
- [Gesetze im Internet: § 475 BGB](https://www.gesetze-im-internet.de/bgb/__475.html) – ryzyko utraty lub uszkodzenia w transporcie po stronie sprzedawcy przy sprzedaży konsumenckiej
- [Gesetze im Internet: § 438 HGB](https://www.gesetze-im-internet.de/hgb/__438.html) – zgłoszenie widocznej szkody przy odbiorze, szkody ukrytej w ciągu siedmiu dni w formie tekstowej oraz zachowanie terminu przez terminowe wysłanie
- [Bundesnetzagentur: Anbieterverzeichnis Post](https://www.bundesnetzagentur.de/DE/Fachthemen/Post/Anbieterverzeichnis/PostAnbieterverzeichnis.html) – rejestr operatorów pocztowych: wpis J&T Express Germany GmbH z 1 września 2026 r.
- [Bundesnetzagentur: Häufig gestellte Fragen zur Schlichtung Post](https://www.bundesnetzagentur.de/DE/Vportal/Schlichtung/Schlichtung_Post/_FAQ_Schlichtung/faqSchlichtung.html) – bezpłatny wniosek online dostępny również dla odbiorców, warunki specjalne przy paczkach z dużych sklepów internetowych oraz Verbraucherservice Post
:::
