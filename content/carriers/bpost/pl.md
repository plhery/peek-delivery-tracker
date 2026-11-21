---
title: Śledzenie bpost: kod kreskowy, statusy i punkty odbioru
description: Śledź paczkę bpost z Belgii lub do Belgii: formaty kodu kreskowego, angielskie statusy Track & Trace, czas odbioru z automatów bbox i kontakt.
slug: bpost-sledzenie
published: 2026-11-17
updated: 2026-11-20
---

bpost to belgijski operator pocztowy i marka grupy bnode (do grudnia 2025 r. bpostgroup) odpowiadająca za doręczenia ostatniej mili w Belgii oraz w Holandii. Przesyłkę sprawdzisz w [Track & Trace bpost](https://track.bpost.cloud/), w aplikacji My bpost albo wklejając numer powyżej. Kod kreskowy znajdziesz w e-mailu ze sklepu, w wiadomości od bpost, na etykiecie paczki lub na awizie w skrzynce.

## Numer śledzenia bpost: gdzie znaleźć kod kreskowy

W bpost numer śledzenia nazywa się kodem kreskowym (*barcode*). Jest na etykiecie nadawczej, dowodzie nadania na poczcie, awizie oraz w e-mailu z potwierdzeniem. Sklepy wysyłają go zazwyczaj e-mailem, często jako link do Track & Trace. Bez kodu kreskowego bpost nie odnajdzie paczki — poproś o niego sprzedawcę. Więcej w poradniku [jak znaleźć numer śledzenia przesyłki](guide:find-tracking-number).

Najczęstsze formaty podawane przez bpost (przykłady zmyślone):

| Format | Przykład | Co oznacza |
| --- | --- | --- |
| 24 cyfry od `3232` lub `3299` | `323200000000000000000001` | Kod kreskowy paczki bpost |
| 18 cyfr od `3232` | `323212345000000001` | Kod kreskowy paczki bpost |
| 23 lub 27 znaków z `JJBE` + literą | `JJBEA0000000000000000000001` | Krajowy kod bpost (paczka lub list polecony) |
| 2 litery (poza `U`), 9 cyfr i `BE` | `CE482910576BE` | Międzynarodowa paczka pocztowa z Belgii |
| `R` + litera, 9 cyfr i `BE` | `RR482910576BE` | Międzynarodowa przesyłka polecona z Belgii |
| 30 cyfr od `010` | `010000000000000000000000000001` | Krajowa przesyłka polecona w Belgii |

Kod zaczynający się od `U` (`UA…BE`) służy wyłącznie do odprawy celnej: nie ma Track & Trace, idzie jak zwykły list i dociera maksymalnie w 5 dni roboczych po ocleniu.

**Podobne numery.** Numer pocztowy zakończony literami innego kraju należy do tamtejszej poczty — np. `…PL` to [Poczta Polska](carrier:poczta-polska). Gdy wśród dziewięciu cyfr numeru `…BE` wpiszesz literę `O` zamiast zera, Peek odczyta ją jako `0`; jednak błędna cyfra psująca sumę kontrolną S10 blokuje rozpoznanie do czasu korekty. Część numerów 24-cyfrowych należy do [InPost](carrier:inpost), Ciblex lub Austrian Post, a 18-cyfrowe od `323200000` pokrywają się z [Ecoscooting](carrier:ecoscooting).

## Statusy Track & Trace bpost i ich znaczenie

Serwis Track & Trace bpost działa tylko po angielsku, francusku, niderlandzku i niemiecku, a dla zwykłych paczek wymaga kodu pocztowego odbiorcy. Peek odpytuje bpost bezpośrednio bez kodu pocztowego i wyświetla angielskie wpisy:

| Komunikat bpost | Co to dla ciebie oznacza |
| --- | --- |
| `Confirmation of preparation of the shipment received` | Nadawca zgłosił przesyłkę do bpost, ale jeszcze jej nie przekazał. |
| `Item accepted after deposit by sender` | Nadana przez nadawcę w punkcie bpost i przyjęta do sieci. |
| `Item is announced / Landmark Global (bpost) received the information` | Przesyłka międzynarodowa zgłoszona do Landmark Global, transgranicznej marki grupy bnode. |
| `Landmark Global (bpost) has received the item` | Landmark Global przejął paczkę. |
| `Arrival at facilities` / `Arrival at international depot` | Przybycie do sortowni w kraju nadania lub do centrum międzynarodowego bpost. |
| `Your item has been sorted` | Posortowana i skierowana do kolejnego etapu. |
| `Awaiting presentation to Customs` / `No extra costs` | Oczekuje na odprawę celną albo została zwolniona bez opłat. |
| `Departure to country of destination` | Wyruszyła z Belgii do kraju docelowego. |
| `Your shipment has arrived at the postal operator of the country of destination and will be delivered in the coming days` | Poczta kraju docelowego (w Polsce [Poczta Polska](carrier:poczta-polska)) przejęła paczkę do doręczenia. |
| `The postman's delivery round for your item has started` | Listonosz wyjechał dziś z twoją paczką w trasę. |
| `Delivery interrupted due to unforeseen circumstances` | Trasa została przerwana po stronie bpost przed twoim adresem; wyznaczono nowy dzień (`New delivery scheduled`). |
| `Item on it's way to bbox locker` | W drodze do automatu bbox, jeszcze niedostępna do odbioru. |
| `Item available at Pick-up point` / `Item available at the bbox locker` | Gotowa do odbioru w Punkcie odbioru lub w automacie bbox. |
| `Item delivered` / `Item delivered in mailbox` / `Item delivered at safeplace` | Doręczona do rąk lub odebrana w punkcie, w skrzynce albo w bezpiecznym miejscu. |
| `Your parcel was delivered at one of your neighbours. You can find all delivery details in your mailbox.` | Zostawiona u sąsiada; szczegóły znajdziesz na awizie w skrzynce. |

Komunikat `Item arrived at the distribution center - Item can't be assigned to a delivery round - missing information` oznacza brak danych adresowych, a `Returned to Landmark Global (bpost), Incorrect / illegible / incomplete address` zwrot przez pocztę zagraniczną z powodu błędnego adresu. W Belgii standardowym celem po przyjęciu paczki jest następny dzień roboczy (95% w podanym oknie); przy umowie weekendowej ze sklepem bpost doręcza też w soboty (8:00–17:00 do punktu lub automatu) i niedziele (9:00–19:00 do domu).

## Doręczenie w Belgii, Punkty odbioru i automaty bbox

**Nieobecność w domu.** Bez ustawionych preferencji listonosz próbuje u sąsiada lub w bezpiecznym miejscu. W przeciwnym razie odbierzesz paczkę we wskazanym Punkcie odbioru od następnego dnia roboczego (od 11:00 przy paczkach z zagranicy). bpost zawsze wysyła e-mail, a papierowe awizo zostawia tylko przy braku kontaktu cyfrowego; sprawdź punkt w Track & Trace przed wyjściem. Nie ma powtórnej wizyty tego samego dnia, z wyjątkiem nieudanej sobotniej dostawy do domu (druga próba w kolejny dzień roboczy).

**Czas odbioru i upoważnienia.** Punkt odbioru trzyma paczkę 14 dni kalendarzowych, a automat bbox 5 dni kalendarzowych bez przedłużenia (6. dnia wraca do nadawcy); paczki z punktu nie można przenieść do innego punktu. Zwykłą paczkę odbierze każdy z kodem kreskowym, natomiast poleconą (`010…` lub `R…`) wyłącznie adresat z dowodem tożsamości i kodem kreskowym albo pełnomocnik z podpisem na awizie.

**Automaty bbox i preferencje.** Ponad 2500 automatów bbox działa 24/7 bez konta: z ekranem skanujesz kod QR z e-maila lub aplikacji My bpost (z 6-cyfrowym kodem przy dostawie bezpośredniej); bez ekranu otwierasz z aplikacji przez Bluetooth i lokalizację. Za duże paczki trafiają do innego punktu, a małe najpierw do skrzynki na listy. W MyPreferences lub aplikacji zmienisz sąsiada bądź bezpieczne miejsce do 5 minut przed dostawą (poza pobraniem, kodem zabezpieczającym, poleconymi `R`, a przy bezpiecznym miejscu także przesyłkami z gwarancją lub ubezpieczeniem), punkt lub automat do północy poprzedniego dnia, albo raz przełożysz dostawę o 1 dzień roboczy przed poranną trasą.

## Paczka utknęła, jest w urzędzie celnym albo nie dotarła

**Brak skanów i reklamacje.** Odczekaj kilka dni roboczych w Belgii, 5 dni roboczych do lub z krajów sąsiednich albo 10 dni roboczych do lub z Polski, Wielkiej Brytanii i innych krajów, a potem poproś nadawcę o kontakt z bpost — formalne dochodzenie może wszcząć tylko nadawca (zobacz [dlaczego śledzenie przesyłki się nie aktualizuje](guide:tracking-not-updating)). Przy zakupie od firmy z UE sprzedawca ma domyślnie 30 dni na dostawę ([Your Europe](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_pl.htm)).

**Uszkodzenie, brak paczki lub zwrot.** Uszkodzoną paczkę możesz od razu odrzucić w Punkcie odbioru; po odbiorze zgłoś szkodę ze zdjęciami sprzedawcy w 7 dni (a przy paczce z zagranicy w Belgii także w bpost; patrz [uszkodzona paczka](guide:damaged-parcel)). Gdy widnieje jako doręczona, sprawdź sąsiadów, skrzynkę, zdjęcie bezpiecznego miejsca w aplikacji oraz [formularz niedoręczonej przesyłki bpost](https://www.bpost.be/fr/formulaires/colis/pas_recu/step1) i poradnik [przesyłka dostarczona, ale nieodebrana](guide:delivered-not-received). Przy zwrocie do nadawcy poproś sklep o zwrot pieniędzy lub ponowną wysyłkę ([zwrot przesyłki do nadawcy](guide:returned-to-sender)).

**Urząd celny w Belgii.** Opłaty importowe uiszczasz w aplikacji My bpost lub w Track & Trace w ciągu 14 dni (kontrola fizyczna trwa 3–7 dni). Dla zamówień spoza UE poniżej 150 € (bez wysyłki) bpost nalicza **3 € cła za rodzaj produktu** (od 1 lipca 2026 r.) oraz **2 € opłaty europejskiej za rodzaj produktu** (od 1 listopada 2026 r.) — dwie identyczne koszulki to 3 € + 2 € = 5 €, a koszulka i spodenki kąpielowe to 6 € + 4 € = 10 € — plus VAT (zwykle 21%) i **21,50 €** opłaty bpost, jeśli sklep nie pobrał VAT i cła przy zakupie. Powyżej 150 € dochodzą cła taryfowe, VAT, **40 €** opłaty bpost i **2 €** za rodzaj produktu. Prezenty od osób prywatnych do 45 € są zwolnione (poza alkoholem, perfumami i tytoniem). Przy podwójnym VAT z powodu braku IOSS możesz odmówić paczki w aplikacji albo zapłacić i żądać zwrotu od sklepu. Sprawdź [zakupy spoza UE w bpost](https://www.bpost.be/en/shopping-outside-eu) i poradnik [paczka w urzędzie celnym](guide:customs).

> W przesyłkach wewnątrz UE bpost nigdy nie żąda dopłat w transporcie, danych osobowych przez e-mail ani SMS i nie straszy komornikiem. Gdy za paczkę spoza UE trzeba zapłacić opłaty importowe, bpost naprawdę wysyła jednak wezwanie do zapłaty e-mailem, SMS-em, powiadomieniem w aplikacji My bpost lub listem: płać je tylko w aplikacji My bpost, w Track & Trace lub w urzędzie pocztowym. Podejrzane wiadomości prześlij na `abuse@bpost.be` i zobacz [fałszywe SMS-y o paczce](guide:delivery-scam-texts).

## Kontakt z bpost i rzecznik pocztowy

- **Telefon:** `02 278 51 26` na stronach angielskich/niderlandzkich (`+32 2 278 51 26` z zagranicy) i `02 278 51 27` na francuskich/niemieckich; pon.–pt. 08:00–12:30 i 13:00–18:00, sob. 09:00–13:00 (opłata strefowa).
- **Czat i placówki:** czat m.in. przy Track & Trace (bez podanych godzin) oraz obsługa w Post Office, Post Point i Parcel Point.
- **Listownie (bez znaczka w Belgii):** `bpost, Box 5000, 1000 Brussels`.

bpost nie podaje e-maila do biura obsługi klienta. Po bezskutecznej reklamacji w bpost nadawcy i odbiorcy mogą bezpłatnie zwrócić się do [Rzecznika Sektora Pocztowego w Belgii](https://www.ombudsmanposte.be/en/) (`Boulevard Roi Albert II 8 box 4, 1000 Brussels`, `+32 2 221 02 21`).

## Śledzenie przesyłek bpost w Peek

[Peek](/) od razu wykrywa bpost („Wykryty przewoźnik”) dla poprawnego numeru z dwóch liter (poza `U`), dziewięciu cyfr i `BE` oraz dla `JJBE` + litera + 18 lub 22 cyfry. Przy 24 cyfrach od `3232` lub `3299` pyta bpost, [InPost](carrier:inpost), Austrian Post i Ciblex („Sprawdzanie usług śledzenia…”) i automatycznie wybiera bpost po potwierdzeniu („bpost ma tę przesyłkę.”). Przy 18 cyfrach od `3232` pyta bpost, a gdy cyfry 6.–9. to zera (`323200000…`), także [Ecoscooting](carrier:ecoscooting) — przycisk „Wybierz” pojawia się tylko, gdy znają go obaj. Krajowe polecone z 30 cyfr (`010…`) dodasz przez „Zmień”; kody `U` sprawdzane są tylko w [Chronopost](carrier:chronopost). Działają też linki `bpost.be` lub Track & Trace z `itemCodes=`. W przesyłkach S10 między Belgią a Polską Peek automatycznie łączy bpost i [Pocztę Polską](carrier:poczta-polska), zachowując historię.

Peek odpytuje bpost bez kodu pocztowego i pokazuje angielskie skany pod „Co mówi bpost” (z polskimi etapami: „W transporcie”, „W urzędzie celnym”, „W doręczeniu”, „Do odbioru”, „Zgłoszono problem”, „Zwrócona nadawcy”), miejscowość, wagę, wymiary, nazwę i adres punktu lub automatu (także po odbiorze) oraz czas lokalny („Godziny w twojej strefie czasowej: …”). Sprawdza nawet co 10 minut, nawet co 2 minuty od 06:00 czasu lokalnego „W doręczeniu” bezpośrednio w bpost (co 15 minut przez usługi), a co godzinę po dwóch dniach ciszy lub po 12 godzinach na „Zarejestrowana” / „Przyjęta przez przewoźnika”. Gdy przy komunikacie „Oczekiwanie na przewoźnika bpost” numer po dwóch dniach nadal jest nieznany, pojawia się „Po dwóch dniach przewoźnik nadal nie zna tego numeru. Sprawdź go, wybierz innego przewoźnika lub usuń przesyłkę.”; po czterech dniach postoju przed doręczeniem widać „Poproś przewoźnika bpost o pomoc”. Peek pomija przewidywaną datę, dane nadawcy i odbiorcy, adresy prywatne, kontakty, preferencje oraz dowód odbioru, wysyłając powiadomienia na „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”.

## Najczęstsze pytania o śledzenie bpost

### Ile czasu mam na odbiór paczki bpost z punktu lub automatu?

14 dni kalendarzowych w Punkcie odbioru oraz 5 dni kalendarzowych w automacie paczkowym bbox, bez przedłużenia. Szóstego dnia w automacie, lub po 14 dniach w punkcie, paczka wraca do nadawcy.

### Czy bpost doręcza paczki w soboty i niedziele?

Tak, jeśli sklep internetowy ma umowę weekendową z bpost: w soboty 8:00–17:00 do Punktu odbioru lub automatu, a w niedziele 9:00–19:00 do domu. Listy i polecone doręczane są tylko w dni powszednie.

### Kto może odebrać przesyłkę poleconą bpost?

Wyłącznie adresat z dokumentem tożsamości i kodem kreskowym albo pełnomocnik z upoważnieniem podpisanym na awizie. Zwykłą paczkę może odebrać każdy z kodem kreskowym.

### Dlaczego mój kod kreskowy bpost nie działa w śledzeniu?

Zwykle sprzedawca nie przekazał jeszcze paczki do bpost („Oczekiwanie na pierwszy skan przewoźnika bpost” przy większości formatów współdzielonych). Kody z `U` nie mają Track & Trace, a błędnie przepisany numer `…BE` nie przechodzi kontroli S10.

:::sources
- [bpost: What does a barcode look like and where can I find it?](https://www.bpost.be/en/faq/what-does-barcode-look-and-where-can-i-find-it) – formaty i kody U
- [bpost: Track & Trace, follow a parcel](https://www.bpost.be/en/faq/can-i-follow-my-parcel-online) – kanały i zagraniczna poczta
- [bpost: When can you follow a parcel with Track & Trace?](https://www.bpost.be/en/faq/when-can-i-follow-my-parcel-track-trace) – start śledzenia
- [bpost: Parcel in transit but didn't receive a barcode](https://www.bpost.be/en/faq/i-am-expecting-parcel-have-not-received-barcode-what-do-i-do-now) – prośba do sklepu
- [bpost: When will my parcel be delivered?](https://www.bpost.be/en/faq/when-will-my-parcel-be-delivered) – następny dzień roboczy i okno 95%
- [bpost: Track & Trace](https://track.bpost.cloud/) – portal śledzenia
- [bpost: Track & Trace (en.json)](https://track.bpost.cloud/assets/i18n/en.json) – języki i angielskie statusy
- [bpost: Not home, where and when can I pick it up?](https://www.bpost.be/en/faq/my-parcel-could-not-be-delivered-my-home-where-and-when-can-i-pick-it) – następny dzień roboczy i e-mail
- [bpost: Missed parcel, can the postman pass again today?](https://www.bpost.be/en/faq/i-just-missed-my-parcel-can-postman-pass-again-today) – brak drugiego przejścia tego samego dnia
- [bpost: Second pass after Saturday notice](https://www.bpost.be/en/faq/why-will-postman-be-passing-second-time-after-leaving-failed-delivery-notice) – druga próba po sobocie
- [bpost: Pick-up point](https://www.bpost.be/en/receive-parcel/pick-up-point) – 14 i 5 dni, polecone i zakaz transferu
- [bpost: Remove parcel from locker](https://www.bpost.be/en/faq/how-do-i-remove-parcel-parcel-locker) – QR, 6 cyfr, Bluetooth i zwrot 6. dnia
- [bpost: Bbox](https://www.bpost.be/en/bbox) – +2500 automatów 24/7
- [bpost: Can I change the time of delivery?](https://www.bpost.be/en/faq/can-i-change-time-delivery) – przełożenie o 1 dzień roboczy
- [bpost: Delivery preferences](https://www.bpost.be/en/mybpostapp-pref-parcel) – terminy i wyjątki w MyPreferences
- [bpost: Receive a parcel from abroad](https://www.bpost.be/en/receive-parcel/abroad) – kontrola 3–7 dni, IOSS i odbiór od 11:00
- [bpost: Parcel stopped moving](https://www.bpost.be/en/faq/my-parcel-seems-be-stuck-somewhere-it-has-not-moved-while-track-trace) – 5 i 10 dni roboczych
- [bpost: File a complaint](https://www.bpost.be/en/faq/how-do-i-file-complaint-if-my-parcel-damaged-or-lost-or-hasnt-been-delivered) – reklamacja nadawcy i 7 dni na szkody
- [bpost: Delivered, haven't received anything](https://www.bpost.be/en/faq/i-get-message-my-parcel-has-been-delivered-i-havent-received-anything-what-now) – weryfikacja i zdjęcie miejsca
- [bpost: Formulaire colis pas reçu](https://www.bpost.be/fr/formulaires/colis/pas_recu/step1) – formularz online
- [bpost: Colis renvoyé à l'expéditeur](https://www.bpost.be/fr/faq/mon-colis-aurait-du-etre-livre-domicile-mais-ete-renvoye-lexpediteur-pourquoi-et-comment-puis-je) – powody zwrotu
- [bpost: Customs](https://www.bpost.be/en/receive-parcel/customs) – 14 dni na opłatę i prezenty do 45 €
- [bpost: Shopping outside the EU](https://www.bpost.be/en/shopping-outside-eu) – 3 € i 2 € za rodzaj produktu, VAT, 21,50 € i 40 €
- [bpost: Saturday and Sunday delivery](https://www.bpost.be/en/faq/which-mail-or-shipments-does-bpost-deliver-saturday-and-sunday) – godziny weekendowe
- [bpost: Contact customer services](https://www.bpost.be/en/faq/how-do-i-contact-bposts-customer-services) – 02 278 51 26, godziny, czat i Box 5000
- [bpost: Contacter le service clientèle](https://www.bpost.be/fr/faq/comment-contacter-le-service-clientele-de-bpost) – 02 278 51 27
- [Ombudsman for the Postal Sector](https://www.ombudsmanposte.be/en/) – bezpłatna mediacja
- [Ombudsman: Qui peut introduire une plainte ?](https://www.ombudsmanposte.be/fr/a-propos-de-nous/notre-fonctionnement/qui-peut-introduire-une-plainte/) – nadawcy i odbiorcy
- [bpost: Phishing](https://www.bpost.be/en/faq/i-received-suspicious-text-message-or-email-allegedly-bpost-what-should-i-do) – ostrzeżenia w UE i abuse@bpost.be
- [Your Europe: Wysyłka i dostawa](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_pl.htm) – termin 30 dni w UE
- [bnode: bpostgroup becomes bnode](https://press.bnode.com/bpostgroup-becomes-bnode) – grupa bnode i marki
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – zasady śledzenia w Peek
:::
