---
title: Śledzenie Royal Mail: numer GB, statusy, cło i doręczenie
description: Śledź paczkę Royal Mail w Polsce: formaty numerów GB, statusy po angielsku, doręczenie przez Pocztę Polską, VAT, cło 3 € i unijna opłata 2 €.
slug: sledzenie-royal-mail
published: 2026-11-17
updated: 2026-11-17
---

Royal Mail to brytyjski narodowy operator pocztowy, a jego wyszukiwarka *Track your item* (na stronie `royalmail.com`) obsługuje również przesyłki kurierskie Parcelforce Worldwide. Aby sprawdzić, gdzie jest paczka, wpisz numer na `royalmail.com` albo wklej go w polu powyżej. Kod śledzenia przekazuje zawsze nadawca — znajdziesz go w potwierdzeniu zamówienia, wiadomości o wysyłce lub na rachunku z brytyjskiego sklepu.

## Numer śledzenia Royal Mail: formaty i gdzie go znaleźć

Przesyłki rejestrowane Royal Mail mają trzy główne formaty numerów. Jeśli w wiadomościach od sprzedawcy nie ma żadnego kodu, poproś go o niego bezpośrednio; zobacz też, [jak znaleźć numer śledzenia przesyłki](guide:find-tracking-number).

| Format (przykład zmyślony) | Co oznacza |
| --- | --- |
| `AB482910576GB` | Standardowy międzynarodowy numer pocztowy (norma UPU S10): 2 litery, 9 cyfr oraz `GB`. Dziewiąta cyfra to cyfra kontrolna. |
| `0A1234567000000000ABC` | 21-znakowy identyfikator z kodu 2D na krajowych etykietach w Wielkiej Brytanii. |
| `4829105738ABCDEF` | 16-znakowy identyfikator złożony z cyfr oraz liter od `A` do `F`. |

Podobne numery, których nie warto mylić:

- Prefiks zaczynający się od `E` (`EA`, `EB`…), `CP` albo `GI` przed 9 cyframi i końcówką `GB` należy do Parcelforce Worldwide (śledzenie Royal Mail przyjmuje również 14-znakowe numery Parcelforce zaczynające się od `PB` oraz dwóch liter i dziesięciu cyfr).
- Ten sam układ S10 zakończony kodem innego kraju (np. `RR482910576PL`) pochodzi od poczty nadania: zgodnie ze standardem UPU S10 przesyłka zachowuje ten sam identyfikator aż do doręczenia, a Royal Mail skanuje ją po dotarciu do Wielkiej Brytanii (z wyjątkiem przesyłek ekspresowych EMS zaczynających się od `E`, które doręcza tam Parcelforce).
- Ciąg 16 samych cyfr bez liter nie jest numerem Royal Mail — przy takim formacie Peek odpytuje jednocześnie kilku przewoźników, m.in. DHL eCommerce oraz [TNT](carrier:tnt).

## Co oznaczają statusy Royal Mail po polsku

Royal Mail publikuje historię śledzenia wyłącznie po angielsku. Poniżej znajdziesz najczęstsze komunikaty przy paczkach z Wielkiej Brytanii (lub wysyłanych tam z Polski), w typowej kolejności:

| Komunikat Royal Mail | Co to oznacza dla Ciebie |
| --- | --- |
| `Sender preparing item` / `Sender has despatched item` | Nadawca wygenerował etykietę lub zgłosił wysyłkę, ale Royal Mail nie ma jeszcze paczki. |
| `We're expecting it` | Elektroniczna zapowiedź nadania przed fizycznym przyjęciem przesyłki. |
| `Item Received` | Royal Mail przyjęła przesyłkę. Ten sam wpis pojawia się ponownie w brytyjskich sortowniach oraz po dotarciu do urzędu wymiany w kraju docelowym. |
| `In transit` | Przesyłka przemieszcza się między sortowniami w sieci. |
| `Item Leaving the UK` | Paczka opuściła Wielką Brytanię w drodze do kraju doręczenia. |
| `Your item is currently with Customs in the destination country.` | Przesyłka przechodzi kontrolę i odprawę celną w Twoim kraju. |
| `Released from Customs` | Odprawa celna zakończona — paczka wraca do krajowej sieci doręczeń. |
| `Customs charges raised` | Przed odbiorem trzeba opłacić naliczony VAT, cło lub opłatę pocztową (szczegóły niżej). |
| `Item has left the overseas International Processing Centre` | Paczka opuściła międzynarodowy węzeł pocztowy w kraju przeznaczenia. |
| `Despatched to delivery office` / `Item received at Sorting Office` | W drodze do placówki lub sortowni obsługującej Twój rejon. |
| `Ready for Delivery` | Przyjęta w lokalnej jednostce i przygotowana do wydania doręczycielowi. |
| `Out for delivery` / `Due to be delivered today` | Wydana dziś listonoszowi lub kurierowi do doręczenia. |
| `Delivery attempted` | Nieudana próba doręczenia — sprawdź skrzynkę na listy, SMS-y lub e-mail. |
| `Due for Redelivery the Next Working Day` | W Wielkiej Brytanii Royal Mail ponowi próbę doręczenia w następnym dniu roboczym. |
| `Available for Redelivery or Collection` | Przesyłka czeka na odbiór w punkcie albo na zamówienie ponownego doręczenia. |
| `Delivered` | Doręczona (w Wielkiej Brytanii `Delivered to Neighbour/Reception by` oznacza wydanie sąsiadowi lub w recepcji). |
| `Returned to sender` | Przesyłka wraca do nadawcy. |

Gdy w nagłówku podsumowania Royal Mail widnieje ogólne `Pending` (często przy zdarzeniach celnych), Peek przyjmuje etap wynikający z ostatniego konkretnego skanu w historii, np. „W urzędzie celnym”.

## Kto doręcza paczkę Royal Mail w Polsce

Przesyłka wysłana z Wielkiej Brytanii z numerem kończącym się na `…GB` trafia do międzynarodowego obiegu pocztowego i w Polsce przejmuje ją [Poczta Polska](carrier:poczta-polska), zachowując dokładnie ten sam numer:

- **Śledzenie i awizo:** krajowe skany sprawdzisz pod adresem `www.poczta-polska.pl/sledzenie-przesylek` lub w Peek. Jeśli listonosz bądź kurier nie zastanie Cię pod adresem, zostawi zawiadomienie (*awizo*). Gdy nie odbierzesz przesyłki w ciągu pierwszych 7 dni, następnego dnia wystawiane jest powtórne zawiadomienie, a po upływie **14 dni** liczonych od dnia następnego po pierwszym awizie nieodebrana przesyłka wraca do nadawcy (na stronie `poczta-polska.pl` można odpłatnie zlecić przechowywanie przesyłki rejestrowanej jeszcze przez maksymalnie 14 dni, o ile placówka ma na to miejsce).

## Cło, VAT i opłaty unijne przy paczkach z Wielkiej Brytanii

Po brexicie Wielka Brytania znajduje się poza Unią Europejską (jedynie Irlandia Północna pozostaje objęta unijnymi zasadami obrotu towarowego). Każda paczka nadana z Anglii, Szkocji lub Walii do Polski przechodzi odprawę celno-podatkową:

- **Podatek VAT od pierwszej złotówki:** zakupy internetowe spoza UE podlegają polskiemu podatkowi VAT (stawka podstawowa 23%). Jak przypomina Poczta Polska, samo opłacenie VAT przy kasie sklepu (np. w procedurze IOSS) nie oznacza jeszcze, że wszystkie należności importowe zostały uregulowane.
- **Zryczałtowane cło UE 3 € za rodzaj produktu (do 150 €):** od 1 lipca 2026 r. zniesiono dawne zwolnienie z cła dla przesyłek do 150 €, a do 1 lipca 2028 r. od każdej sprzedaży na odległość do 150 € naliczane jest cło w wysokości **3 € za każdy rodzaj produktu** (przeliczane na złote i przekazywane Krajowej Administracji Skarbowej), niezależnie od zastosowanej procedury VAT (zarówno przy IOSS, jak i poza IOSS). Dwie jednakowe koszulki z tym samym kodem towaru i krajem pochodzenia to jeden rodzaj produktu (3 €), ale koszulka i spodnie to już dwa rodzaje produktów (6 €). Towar o pochodzeniu preferencyjnym wynikającym z umowy handlowej UE (np. umowy UE–Wielka Brytania) może skorzystać ze stawki preferencyjnej wyłącznie w standardowym zgłoszeniu `H1`, pod warunkiem że przy sprzedaży nie użyto IOSS; wszystkie przesyłki rozliczane przez IOSS płacą 3 €. Powyżej 150 € stosuje się standardową taryfę celną.
- **Unijna opłata manipulacyjna 2 € za rodzaj produktu:** od 1 listopada 2026 r. do każdej przesyłki ze sprzedaży na odległość spoza UE doliczana jest stała, bezzwrotna opłata unijna w wysokości **2 € za rodzaj produktu** (pozycję towarową), bez względu na wartość zamówienia — także powyżej 150 € oraz przy pochodzeniu preferencyjnym (np. trzy identyczne koszulki = 2 €; koszulka i buty = 4 €).
- **Opłata pocztowa i płatność przy odbiorze:** do należności celno-podatkowych dochodzi opłata [Poczty Polskiej](carrier:poczta-polska) za dokonanie zgłoszenia celnego. Należności mogą być pobierane przed doręczeniem przesyłki lub przy jej odbiorze, zależnie od rodzaju odprawy celnej (możesz też odmówić przyjęcia paczki, co skutkuje jej zwrotem do nadawcy). Okazjonalne przesyłki prezentowe między osobami prywatnymi bez charakteru handlowego o wartości do **45 €** pozostają zwolnione z cła i VAT. Więcej w poradniku [paczka zatrzymana przez urząd celny](guide:customs).

## Gdy mieszkasz w Wielkiej Brytanii: czerwona kartka, doręczenia i brytyjskie cło

W Wielkiej Brytanii Royal Mail doręcza przesyłki od poniedziałku do soboty, zazwyczaj do godziny 16:30, a w wybranych rejonach także w niedziele dla części paczek, z wyłączeniem dni ustawowo wolnych (*bank holidays*). Listy *2nd Class* przechodzą stopniowo do grudnia 2026 r. na doręczanie co drugi dzień roboczy, natomiast paczki (w tym *Tracked 24*) nadal chodzą sześć dni w tygodniu. Od 1 kwietnia 2026 r. cele jakościowe Ofcom wynoszą 90% dla listów *1st Class* następnego dnia roboczego i 95% dla *2nd Class* w trzy dni (w kwartale kwiecień–czerwiec 2026 r. Royal Mail nie osiągnęła tych progów według Citizens Advice).

Pod nieobecność pod brytyjskim adresem listonosz może zostawić paczkę w bezpiecznym miejscu (*safe place*) albo u sąsiada (ustawienia w aplikacji są ważne przez rok, a naklejka pozwala wykluczyć sąsiadów), lub wrzuca czerwoną kartkę (*red card*) z adresem i godzinami otwarcia *Customer Service Point*, skąd możesz [odebrać przesyłkę po nieudanym doręczeniu](guide:missed-delivery) albo zamówić ponowne doręczenie na `royalmail.com`. Przy paczkach z zagranicy objętych brytyjskimi opłatami Royal Mail wysyła rachunek i przechowuje przesyłkę przez około **3 tygodnie** przed zwrotem (prezenty zwolnione z VAT do 39 £; zakupy do 135 £ zwykle z VAT doliczonym w sklepie; korekta opłat na formularzu BOR 286; w Irlandii Północnej towary spoza UK i UE uznane za „zagrożone” wjazdem do UE płacą należne cło oraz 2 € za rodzaj produktu w funtach).

## Paczka Royal Mail utknęła, spóźnia się lub zaginęła

- **Brak skanów na początku:** zanim paczka trafi do fizycznego skanowania, Royal Mail może nie rozpoznawać numeru, a `Sender preparing item` oznacza jedynie przygotowanie etykiety. Jeśli po kilku dniach status się nie zmienia, upewnij się u sprzedawcy, że nadał przesyłkę. Sprawdź, [dlaczego śledzenie się nie aktualizuje](guide:tracking-not-updating).
- **Zatrzymanie na `Item Leaving the UK`:** kilka dni przerwy między wylotem z Wielkiej Brytanii a skanem w polskim urzędzie wymiany to norma. Wpisz ten sam numer `…GB` w śledzeniu [Poczty Polskiej](carrier:poczta-polska), aby zobaczyć, czy paczka nie jest już na odprawie.
- **Opóźnienie lub zaginięcie:** przy zakupach internetowych zgłoś sprawę najpierw sprzedawcy (w UE ma ustawowo **30 dni** na dostarczenie zamówienia, o ile nie umówiliście się inaczej). W Royal Mail o odszkodowanie za przesyłkę nadaną w Wielkiej Brytanii może wystąpić nadawca lub adresat na podstawie dowodu nadania (lub ostemplowanego opakowania), z wyjątkiem usług *Tracked 24* i *Tracked 48*, gdzie roszczenie składa wyłącznie nadawca. Za samo opóźnienie *Tracked 24*, *Tracked 48* ani przesyłek międzynarodowych odszkodowanie nie przysługuje. Przy zaginięciu paczki do Europy trzeba odczekać **20 dni roboczych** od planowanej daty doręczenia (25 dni poza Europę) i złożyć wniosek w ciągu **6 miesięcy** od nadania (w obrocie brytyjskim: 11 dni roboczych od nadania dla *1st Class*, 14 dla *2nd Class*, 5 od planowanej dostawy dla *Special Delivery*, plus 1 dzień między pierwszym poniedziałkiem grudnia a pierwszym dniem roboczym stycznia, w terminie 80 dni). Podstawowe limity bez dodatkowego ubezpieczenia to 20 £ (*1st/2nd Class*), 75 £ (*Tracked 24/48*), 50 £ (międzynarodowe rejestrowane) i 750 £ (*Special Delivery Guaranteed by 1pm*), a Royal Mail odpowiada w 30 dni (90 dni dla zagranicy). Dla powszechnych usług pocztowych w obrocie zagranicznym z udziałem Poczty Polskiej UKE wskazuje również **6 miesięcy** od nadania na złożenie reklamacji i **3 miesiące** na odpowiedź operatora (oraz 14 dni na odwołanie). Zobacz też [zagubiona przesyłka](guide:lost-parcel) i [zwrot przesyłki do nadawcy](guide:returned-to-sender).
- **Status doręczono, ale paczki nie ma:** zapytaj domowników, sąsiadów lub ochronę, sprawdź skrzynkę i niezwłocznie napisz do sprzedawcy. Przeczytaj [przesyłka oznaczona jako doręczona, ale nieodebrana](guide:delivered-not-received).

## Kontakt z Royal Mail i Pocztą Polską

- **Royal Mail (Wielka Brytania):** `03457 740 740` (brytyjski numer krajowy — połączenie z Polski bywa kosztowne, więc wygodniej działać przez sprzedawcę), pon.–pt. 8:00–18:30, sob. 8:00–15:00, niedz. 9:00–14:00 czasu brytyjskiego. Textphone: `03456 000 606`; Relay UK: `18001` oraz `0345 266 0075`. W Wielkiej Brytanii od decyzji można odwołać się w 30 dni do *Postal Review Panel*, a następnie do niezależnego arbitrażu POSTRS (`020 7520 3766`, `postrs@cedr.com`).
- **Poczta Polska:** infolinia `801 333 444` oraz `438 420 600` (w sprawach niedoręczonych przesyłek także `438 420 800`) i adres `kontakt@poczta-polska.pl`.

> Dostałeś SMS o dopłacie kilku złotych z linkiem do odblokowania paczki? Nie klikaj. Poczta Polska ostrzega, że Pocztex nigdy nie wysyła SMS-ów z linkami do płatności ani nie prosi w nich o dane karty płatniczej. Prawdziwe należności celne mogą być pobierane przed doręczeniem przesyłki lub przy jej odbiorze, więc sprawdzaj je tylko przez oficjalne śledzenie na `poczta-polska.pl/sledzenie-przesylek` lub `pocztex.pl/sledzenie-przesylek`. Fałszywe wiadomości w Polsce zgłosisz do **CSIRT NASK** przez `incydent.cert.pl` lub przekażesz na numer **8080** (w Wielkiej Brytanii SMS-y na **7726**, e-maile na `report@phishing.gov.uk`). Więcej w poradniku [jak rozpoznać fałszywe SMS-y o paczce](guide:delivery-scam-texts).

## Śledzenie przesyłki Royal Mail w Peek

[Peek](/) od razu rozpoznaje prawidłowy numer w układzie `AB482910576GB` i pokazuje „Wykryty przewoźnik”. Jeśli cyfra kontrolna się nie zgadza, zobaczysz komunikat „Cyfra kontrolna tego numeru się nie zgadza.”: sprawdź kod w wiadomości ze sklepu, przyjmij podpowiedzianą korektę przy pomylonej literze i cyfrze (`O` i `0`) albo wybierz „Śledź tak, jak wpisano” i wskaż Royal Mail przyciskiem „Zmień”. Przy brytyjskich numerach krajowych o długości 21 i 16 znaków Peek najpierw pyta serwisy śledzenia, a stronę Royal Mail w przeglądarce otwiera na końcu, co może chwilę potrwać; jeśli nic nie odpowie albo rzadki 21-znakowy numer zostanie przypisany do Australia Post, wybierz Royal Mail przez „Zmień”. Link `royalmail.com` z parametrem `trackNumber=` wskazuje Royal Mail natychmiast dla każdego formatu, natomiast link `#/tracking-results/` działa tylko dla numerów S10 zakończonych na `GB`.

Peek otwiera śledzenie Royal Mail w przeglądarce na własnym serwerze. Ponieważ Royal Mail mocno ogranicza ruch automatyczny, Peek sam sprawdza przesyłkę Royal Mail mniej więcej raz na godzinę — zarówno w zwykły dzień, jak i w dniu dostawy, nawet gdy podane jest okno godzinowe; ręczne odświeżenie może uruchomić sprawdzenie wcześniej. Na ekranie widzisz aktualny etap, każdy skan na mapie z nazwą miejscowości (bez kodów pocztowych), przewidywany dzień doręczenia (wraz z przedziałem godzinowym, jeśli jest podany), godzinę wydania oraz kraj docelowy. Brytyjskie godziny są przeliczane z dopiskiem „Godziny w Twojej strefie czasowej: …”. Wpisy doręczenia pokazują „Dostarczona” bez nazwiska odbiorcy, adresu ani zdjęcia, a pozostałe zdarzenia zachowują oryginalne angielskie brzmienie. Powiadomienia możesz ustawić na „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”.

W przypadku paczek z końcówką `…GB` wysyłanych poza Wielką Brytanię, gdy tylko Royal Mail poda kraj przeznaczenia (albo na podstawie kraju ustawionego w Peek), Peek odpytuje również narodowego operatora pocztowego w ponad 60 krajach — w tym [Pocztę Polską](carrier:poczta-polska) — i przełącza widok na „Doręcza: …”, jak tylko pojawią się tam nowe skany, przechodząc wtedy na częstsze sprawdzanie (nawet co 10 minut, a w dniu doręczenia nawet co 2 minuty). Opłaty celno-podatkowe, przekierowania i reklamacje załatwia się bezpośrednio w Royal Mail lub Poczcie Polskiej.

## Najczęstsze pytania o śledzenie Royal Mail

### Kto doręcza paczkę Royal Mail w Polsce?

Przesyłkę z końcówką `…GB` przejmuje i doręcza [Poczta Polska](carrier:poczta-polska) pod tym samym numerem śledzenia. Gdy tylko Poczta Polska opublikuje własne skany, Peek automatycznie przełączy się na jej aktualizacje.

### Ile wynoszą opłaty celne za paczkę z Wielkiej Brytanii?

Do zakupów wysyłanych z Anglii, Szkocji lub Walii doliczany jest polski VAT (23%, jeśli nie pobrano go w sklepie), zryczałtowane cło UE 3 € za rodzaj produktu przy zamówieniach do 150 € (od lipca 2026 r.), stała opłata unijna 2 € za rodzaj produktu (od listopada 2026 r.) oraz opłata Poczty Polskiej za dokonanie zgłoszenia celnego. Prezenty między osobami prywatnymi do 45 € są zwolnione z cła i VAT.

### Jak często aktualizuje się śledzenie Royal Mail?

Nowy wpis pojawia się dopiero przy kolejnym fizycznym skanie przesyłki, a między `Item Leaving the UK` a przyjęciem paczki w Polsce mija często kilka dni bez zmian. Peek odpytuje Royal Mail mniej więcej raz na godzinę, a po przejęciu przesyłki przez Pocztę Polską sprawdza ją nawet co 10 minut (nawet co 2 minuty w doręczeniu).

### Co oznacza `Customs charges raised` lub `Your item is currently with Customs`?

`Your item is currently with Customs in the destination country.` oznacza, że paczka przechodzi odprawę celną w kraju docelowym. `Customs charges raised` informuje o naliczeniu opłat importowych — w Polsce Poczta Polska może je pobrać przed doręczeniem przesyłki lub przy jej odbiorze.

:::sources
- [Citizens Advice: Check Royal Mail collection and delivery times](https://www.citizensadvice.org.uk/consumer/post-and-parcels/post-collection-delivery-times/) – dni i godziny doręczeń w Wielkiej Brytanii, doręczanie listów 2nd Class co drugi dzień roboczy, Customer Service Points oraz infolinia
- [Citizens Advice: Telling delivery companies about your delivery or collection needs](https://www.citizensadvice.org.uk/consumer/post-and-parcels/telling-delivery-companies-about-your-delivery-or-collection-needs/) – bezpieczne miejsce, sąsiad, naklejka, ważność preferencji przez rok, czerwona kartka i ponowne doręczenie
- [Citizens Advice: Claiming compensation from Royal Mail](https://www.citizensadvice.org.uk/consumer/post-and-parcels/claiming-compensation-from-royal-mail/) – kto może złożyć reklamację, dowód nadania, terminy oczekiwania i zgłoszenia w Wielkiej Brytanii oraz do Europy, kwoty odszkodowań i czas odpowiedzi
- [Citizens Advice: Damaged, lost or delayed post or parcel](https://www.citizensadvice.org.uk/consumer/post-and-parcels/damaged-lost-delayed-post-or-parcel/) – kontakt ze sprzedawcą, odwołanie do Postal Review Panel w 30 dni i POSTRS
- [Citizens Advice: Response to Royal Mail's Quality of Service results, August 2026](https://www.citizensadvice.org.uk/about-us/media-centre/press-releases/citizens-advice-responds-to-royal-mails-latest-quality-of-service-results-august-2026/) – normy Ofcom od 1 kwietnia 2026 r., wyniki za kwiecień–czerwiec 2026 r. i wdrażanie 2nd Class do grudnia 2026 r.
- [GOV.UK: Tax and customs for goods sent from abroad: Tax and duty](https://www.gov.uk/goods-sent-from-abroad/tax-and-duty) – rachunek od Royal Mail, przechowywanie przez ok. 3 tygodnie, progi 39 £ i 135 £, formularz BOR 286 oraz opłata 2 € za rodzaj produktu w Irlandii Północnej
- [GOV.UK: Avoid and report internet scams and phishing](https://www.gov.uk/report-suspicious-emails-websites-phishing) – zgłaszanie brytyjskich SMS-ów na 7726 i e-maili na report@phishing.gov.uk
- [UPU: standard S10, Identification of postal items](https://www.upu.int/UPU/media/upu/files/postalSolutions/programmesAndServices/standards/S10-12.pdf) – 13-znakowa budowa numeru, cyfra kontrolna i niezmienny identyfikator aż do doręczenia
- [Komisja Europejska: Guidance on the temporary EUR 3 customs duty and the Union handling fee](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – cło 3 € za rodzaj produktu niezależnie od procedury VAT, zgłoszenie H1 dla pochodzenia preferencyjnego poza IOSS oraz bezzwrotna opłata 2 € za rodzaj produktu
- [Komisja Europejska: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_pl) – reforma celna UE i zniesienie zwolnienia z cła do 150 € od 1 lipca 2026 r.
- [Your Europe: Wysyłka i dostawa](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_pl.htm) – 30-dniowy termin dostawy i naliczanie cła 3 € za rodzaj produktu
- [Poczta Polska: Zmiany w opłatach dla przesyłek spoza UE od 1 lipca 2026 r.](https://www.poczta-polska.pl/news/zmiany-w-oplatach-dla-przesylek-spoza-unii-europejskiej-od-1-lipca-2026-r/) – cło 3 € za rodzaj produktu do 150 €, VAT, opłata pocztowa przy doręczeniu lub w placówce oraz limit 45 € dla prezentów
- [Poczta Polska: Tak działa nowa opłata za przesyłki spoza UE](https://media.poczta-polska.pl/releases/nowe-przepisy-unii-europejskiej-tak-dziala-nowa-oplata-za-przesylki-spoza-ue) – unijna opłata manipulacyjna 2 € za rodzaj produktu od 1 listopada 2026 r. bez względu na wartość paczki
- [Poczta Polska: Regulamin świadczenia usług powszechnych (od 01.08.2026 r.)](https://www.poczta-polska.pl/wp-content/uploads/2026/07/Regulamin-swiadczenia-uslug-powszechnych-tekst-jednolity.pdf) – powtórne zawiadomienie po 7 dniach, zwrot po 14 dniach i odpłatne przedłużenie przechowywania
- [Poczta Polska: Uwaga na fałszywe wiadomości Pocztex](https://www.poczta-polska.pl/news/uwaga-na-falszywe-wiadomosci-pocztex-grozna-kampania-phishingowa/) – oficjalny adres śledzenia, brak linków płatniczych w SMS-ach, zgłaszanie na 8080 i incydent.cert.pl oraz numery infolinii
- [CIK UKE: Reklamacje usług pocztowych](https://cik.uke.gov.pl/uslugi-pocztowe/dochodzenie-roszczen/reklamacje/) – 6 miesięcy na reklamację zagranicznej usługi powszechnej, 3 miesiące na odpowiedź i 14 dni na odwołanie
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – rozpoznawane formaty, statusy Royal Mail, sprawdzanie co godzinę i przekazywanie śledzenia do Poczty Polskiej w Peek
:::
