---
title: Vinted Go śledzenie przesyłki: statusy, automaty i pomoc
description: Śledź paczkę Vinted Go: gdzie znaleźć numer śledzenia, co oznaczają angielskie statusy, ile dni paczka czeka w automacie i do kogo zgłosić problem na Vinted.
slug: vinted-go-sledzenie
published: 2026-11-16
updated: 2026-11-17
---

Vinted Go to własna sieć kurierska platformy Vinted: obsługuje automaty paczkowe (*lockers*), punkty partnerskie (*parcel shops*) i punkty sąsiedzkie we Francji, Belgii, Holandii, Hiszpanii, Portugalii oraz Finlandii. W Polsce Vinted Go nie ma własnych automatów ani punktów odbioru (zintegrowaną wysyłkę na polskim Vinted realizują m.in. ORLEN Paczka, InPost, DPD, Poczta Polska / Pocztex, DHL i GLS, choć w regulaminie wysyłki zintegrowanej Vinted Polska widnieje litewska spółka Vinted Go UAB). Ze śledzeniem Vinted Go spotkasz się więc przede wszystkim wtedy, gdy kupujesz lub sprzedajesz na Vinted w Europie Zachodniej albo odbierasz tam przesyłkę, a [strona śledzenia Vinted Go](https://vintedgo.com/en/tracking) nie ma polskiej wersji językowej. Aby sprawdzić paczkę, otwórz rozmowę o zamówieniu na Vinted i naciśnij „Śledź przesyłkę”, wpisz kod na stronie Vinted Go albo wklej go w pole powyżej.

## Gdzie znaleźć numer śledzenia Vinted Go

W oknie rozmowy z kupującym lub sprzedającym na Vinted możesz nacisnąć „Śledź przesyłkę”, przejść bezpośrednio linkiem na stronę przewoźnika albo skopiować numer śledzenia. Po nadaniu paczki przez sprzedającego może minąć do 48 godzin, zanim pierwsze informacje o statusie pojawią się na Vinted.

Vinted Go nie publikuje wzorów swoich numerów. Oto formaty rozpoznawane przez katalog przewoźników w Peek (na zmyślonych cyfrach):

| Numer (zmyślony) | Co oznacza |
| --- | --- |
| `1700000000000001` | Typowy format Vinted Go: 16 cyfr zaczynających się od `17` lub `18` z poprawną cyfrą kontrolną na końcu. 16-cyfrowych numerów używają też DHL eCommerce oraz [TNT](carrier:tnt). |
| `1700000000000002` | 16 cyfr, w których ostatnia cyfra nie przechodzi weryfikacji kontrolnej: to nie jest prawidłowy numer Vinted Go. |
| `VGS00000000000000` | `VGS` i 13 lub 14 cyfr: kod, na który odpowiada wyłącznie śledzenie Vinted Go. |
| `ABC0UW0000000000` | 16 znaków: 3 litery, cyfra, `UW`, cyfra oraz 9 liter lub cyfr. Śledzenie Vinted Go odpowiada również na te kody, a na trasach do Włoch ostatnie 13 znaków bywa jednocześnie kodem paczki Poste Italiane. |

Łatwo pomylić ze sobą dwie różne rzeczy:

- **Link do śledzenia.** Przycisk w rozmowie na Vinted prowadzi pod adres taki jak `vintedgo.com/en/tracking/1700000000000001`. Taki link najpewniej potwierdza, że paczkę obsługuje Vinted Go.
- **Kod odbioru.** Nie służy do śledzenia trasy: przychodzi e-mailem (i czasem SMS-em) dopiero wtedy, gdy paczka czeka już w punkcie odbioru. W sieci Vinted Go zawsze zaczyna się od wielkiej litery; jeśli przez pomyłkę wkleisz w pole Peek sześciocyfrowy PIN odbioru, aplikacja wyświetli komunikat „To wygląda na kod odbioru”.

Nie możesz znaleźć numeru? Zobacz poradnik, [jak znaleźć numer śledzenia przesyłki](guide:find-tracking-number). Jeśli sprzedający wysłał paczkę innym kurierem, zajrzyj do przewodnika po [przesyłkach z Vinted](guide:vinted-orders).

## Statusy śledzenia Vinted Go po polsku

Anglojęzyczna usługa śledzenia Vinted Go zwraca komunikaty po angielsku wraz ze znacznikiem czasu. Oto skany znane kodowi Peek z systemu Vinted Go — w typowej kolejności od utworzenia etykiety po odbiór lub zwrot:

| Vinted Go wyświetla | Co to oznacza dla Ciebie |
| --- | --- |
| `Shipment created` | Sprzedający wygenerował etykietę, ale nie przekazał jeszcze paczki do Vinted Go. |
| `Parcel was dropped off at the locker` | Sprzedający włożył paczkę do automatu (lub nadał ją w punkcie: `Parcel was dropped off at the parcel shop`). |
| `At sorting center in <town>, <country>` | Przyjęcie w sortowni na trasie; występują też skany `In transit to <town>, <country>` oraz `The parcel is on its way to the locker (<point>)`. |
| `Parcel redirected to the parcel shop` | Wybrany punkt nie mógł przyjąć paczki — jedzie ona do innego punktu, automatu, magazynu albo sieci partnerskiej (np. `Change: Parcel redirected to Colissimo`). |
| `Parcel is on the way to <country>` | Przesyłka opuściła własną sieć Vinted Go i jedzie dalej z inną siecią w kraju docelowym (np. Poste Italiane do Włoch). |
| `Ready to collect at the locker` | Czeka na Ciebie w automacie (albo w punkcie partnerskim: `Ready to collect at the parcel shop`). W punktach Vinted Go masz 7 dni na odbiór. |
| `Parcel was collected by the recipient` | Ty — lub ktoś znający Twój kod odbioru — odebrałeś paczkę. |
| `Delivered` | Pojawia się w systemie Vinted Go w ciągu sekundy po `Parcel was collected by the recipient` (Peek pomija ten zduplikowany wiersz). |
| `Missing from locker` | Skrytka w automacie okazała się pusta: zgłoś problem w oknie rozmowy na Vinted. |
| `Return: Parcel will be returned. Reason: pickup time has expired` | Minął termin odbioru i rusza zwrot do nadawcy (kolejne etapy zwrotu to `Return: At sorting center in <town>, <country>`, `Return ready to collect` oraz `Return delivered`). |

Zwróć uwagę na potoczne sformułowanie w FAQ Vinted Go: gdy automat nie przyjmuje kodu odbioru, FAQ radzi sprawdzić w śledzeniu, czy paczka dotarła już na miejsce — w samej osi zdarzeń gotowość do odbioru oznacza `Ready to collect at the locker`, natomiast `Delivered` zapisuje się dopiero po odebraniu przesyłki. W systemie Vinted Go występują też komunikaty wyjątków: `Parcel lost` (późniejsze skany potrafią odnotować odnalezienie paczki), `Couldn’t be delivered`, `Shipment was cancelled` oraz `Parcel is oversized`.

## Automaty i punkty Vinted Go: gdzie działa sieć i jak odebrać paczkę

**Zasięg sieci.** Własne automaty paczkowe, punkty partnerskie i punkty sąsiedzkie Vinted Go działają w sześciu krajach: we Francji, Belgii, Holandii, Hiszpanii, Portugalii i Finlandii; na przykład we Francji opcja ta pojawia się w zamówieniu tylko wtedy, gdy automat Vinted Go znajduje się w pobliżu zarówno sprzedającego, jak i kupującego, a międzynarodowe etykiety Vinted Go łączą tam Francję z Holandią, Belgią, Włochami, Hiszpanią, Portugalią i Niemcami. Na polskim Vinted Polska jest połączona ze Szwecją, Litwą, Słowacją, Węgrami, Rumunią, Czechami, Danią (bez Grenlandii i Wysp Owczych), Chorwacją kontynentalną, kontynentalną częścią Finlandii, Słowenią, Łotwą i Estonią, a dostawy w Polsce obsługują krajowi i międzynarodowi kurierzy zintegrowani, nie automaty Vinted Go. Jeśli wybrany za granicą punkt Vinted Go jest pełny, nieczynny lub zamknięty, przewoźnik może przekierować paczkę do innego pobliskiego punktu i wskaże go w powiadomieniu o gotowości do odbioru.

**Powiadomienie i odbiór.** Gdy paczka dotrze na miejsce, Vinted Go wysyła e-mail z kodem odbioru na adres przypisany do konta Vinted (czasem także SMS), a status aktualizuje się w rozmowie na Vinted; regulamin Vinted Go przewiduje ponadto ponowne powiadomienie po 3 dniach od dostarczenia paczki do punktu. Przy automacie zeskanuj lub wpisz kod odbioru na ekranie, aby otworzyć skrytkę; w punkcie partnerskim lub sąsiedzkim okaż personelowi kod odbioru lub dokument tożsamości. Godziny otwarcia punktów sprawdzisz na mapie Vinted Go. Jeśli odebrałeś już przesyłkę, a rozmowa na Vinted wciąż nie pokazuje „Doręczono”, możesz wybrać w niej „Oznacz jako odebraną”.

**Ile dni czeka paczka.** W automatach i punktach sieci Vinted Go masz na odbiór **7 dni kalendarzowych** — terminu nie da się przedłużyć ani zatrzymać paczki, gdy ruszy już w drogę powrotną. Nieodebrana przesyłka wraca do punktu nadania sprzedającego, a Vinted automatycznie anuluje zamówienie i zwraca Ci pieniądze.

**Dostawa pod drzwi i czas przewozu.** We Francji wybrane punkty pozwalają zamówić dowóz do domu kurierem Uber Direct na wybraną godzinę przez link z Vinted (regulamin Vinted Go przewiduje jedną próbę doręczenia z umówionym oknem czasowym i kodem PIN oraz dwie próby bez wybranego okna). W ramach własnej sieci regulamin Vinted Go zakłada dostawę w **3 do 6 dni roboczych** od nadania; Centrum Pomocy Vinted podaje ogólnie do 5 dni roboczych dla przesyłek krajowych i około 7 dni roboczych dla międzynarodowych.

## Brak aktualizacji śledzenia, zagubiona lub uszkodzona paczka

W każdej sprawie związanej z zamówieniem kontaktujesz się z Vinted, a nie z Vinted Go: to obsługa klienta Vinted rozlicza zwroty środków i w razie potrzeby zleca przewoźnikowi wyjaśnienie sprawy.

**Brak nowych skanów przez kilka dni.** Vinted Go uspokaja, że kilkudniowa przerwa między wpisami w trakcie transportu jest normalna. Więcej przeczytasz w poradniku [dlaczego śledzenie przesyłki się nie aktualizuje](guide:tracking-not-updating).

**Paczka nie dociera.** Zarówno Vinted, jak i Vinted Go proszą o odczekanie do **25 dni roboczych** od oznaczenia zamówienia jako wysłanego (dokładny termin widnieje w oknie rozmowy na Vinted). Jeśli po tym czasie paczki nadal nie ma, naciśnij w rozmowie „Mam problem”.

**Uszkodzona paczka, niewłaściwy przedmiot lub pusta skrytka.** Od momentu zmiany statusu na „Doręczono” masz **2 dni** na naciśnięcie przycisku „Mam problem” w rozmowie na Vinted (przy zakupach od sprzedawców Pro w krajach, gdzie działają konta Pro, termin wynosi **14 dni**). Gdy wyjmujesz uszkodzoną paczkę z automatu Vinted Go, zabierz ją ze sobą i od razu zrób zdjęcia: po zamknięciu drzwiczek skrytki nie można już ponownie otworzyć.

**Status „Doręczono”, ale paczki nie ma.** Zgłoś to na Vinted w ciągu **2 dni** od aktualizacji statusu zamówienia. Zobacz też poradnik [paczka oznaczona jako doręczona, ale nieodebrana](guide:delivered-not-received).

## Kontakt i obsługa klienta Vinted Go

Według stanu sprawdzonego 16 listopada 2026 r. Vinted Go nie udostępnia kupującym adresu e-mail ani formularza kontaktowego, a pomoc telefoniczna dotyczy wyłącznie awarii na miejscu przy automacie Vinted Go:

- **Problemy z zamówieniem** (opóźnienie, zaginięcie, uszkodzenie, pomylony przedmiot lub zwrot pieniędzy): otwórz zamówienie na swoim koncie Vinted i zgłoś sprawę zespołowi wsparcia Vinted bezpośrednio z okna rozmowy.
- **Problem przy automacie Vinted Go** we Francji, Belgii, Holandii lub Hiszpanii: naciśnij przycisk „?” na ekranie głównym automatu, aby wyświetlić numer telefonu wsparcia i godziny dyżuru. Dla Holandii i Belgii FAQ Vinted Go podaje również numer `+31 20 793 2587` (obsługa po angielsku, a w Belgii także po francusku).

> Vinted ostrzega, że próby phishingu trafiają się w wiadomościach na Vinted, SMS-ach, e-mailach oraz załącznikach z kodami QR. Podejrzane e-maile możesz przekazywać na adres `phishing@vinted.com`.

## Śledzenie przesyłki Vinted Go w Peek

[Peek](/) odczytuje bezpośrednio śledzenie Vinted Go — bez konta i bez kodu pocztowego: pokazuje każdy skan z dokładną godziną w oryginalnym angielskim brzmieniu Vinted Go (np. `Ready to collect at the locker`), miejscowość każdego centrum sortowania na mapie oraz czytelny polski status główny („Do odbioru”, „Dostarczona”, „Zwrócona nadawcy”). Wskazuje nazwę automatu lub punktu partnerskiego, w którym paczka czeka albo z którego została odebrana, pomija natomiast punkty sąsiedzkie mogące być prywatnym adresem; pełny adres punktu, godziny otwarcia i ostateczny termin odbioru pozostają na stronie Vinted Go, która nie podaje też przewidywanej daty doręczenia. Godziny skanów wyświetlają się w strefie czasowej Twojego urządzenia; na peektracker.com i w aplikacji na iPhonie przypomina o tym linijka pod historią („Godziny w Twojej strefie czasowej: …”).

Po numerze zaczynającym się od `VGS` — albo po wklejeniu linku `vintedgo.com/en/tracking/...` — Peek od razu rozpoznaje Vinted Go („Wykryty przewoźnik”). Gdy wpiszesz sam 16-cyfrowy numer z prefiksem `17` lub `18`, Peek odpytuje Vinted Go, [TNT](carrier:tnt) i DHL eCommerce (przy niektórych numerach także innych przewoźników), przypisując przesyłkę do Vinted Go, gdy przewoźnik potwierdzi numer („Vinted Go ma tę przesyłkę.”); kod z `UW` sprawdza w Vinted Go. W innym wypadku naciśnij „Zmień” i wybierz Vinted Go.

Jeśli Vinted Go przekaże paczkę innej sieci pod nowym numerem śledzenia, Peek może śledzić ją dalej u nowego przewoźnika, o ile obsługuje go bezpośrednio; gdy ostatnie 13 znaków 16-znakowego kodu z `UW` tworzy kod paczki Poste Italiane, Peek odpytuje również Poste Italiane i przełącza kartę na „Doręcza: Poste Italiane”, jak tylko włoska poczta zapisze najnowszy skan. Dopóki przesyłka idzie w sieci Vinted Go, Peek sprawdza status co 10 minut, a po dwóch dniach bez nowego skanu (lub po 12 godzinach na etapie „Zarejestrowana” bądź „Przyjęta przez przewoźnika”) — raz na godzinę. Powiadomienia możesz ustawić na „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek nie skontaktuje się za Ciebie z Vinted ani nie zgłosi reklamacji.

## Najczęstsze pytania o śledzenie Vinted Go

### Co to jest Vinted Go i dlaczego widnieje w regulaminie Vinted?

Vinted Go to własny operator logistyczny grupy Vinted, prowadzony przez spółkę Vinted Go, UAB zarejestrowaną w Wilnie na Litwie. Poza obsługą własnej sieci automatów i punktów w sześciu krajach Europy według zasad wysyłki zintegrowanej na Vinted spółka Vinted Go UAB staje się właścicielem zaginionych przesyłek, za które wypłacono odszkodowanie, oraz zwróconych przesyłek, których nikt nie odebrał w terminie.

### Ile idzie paczka Vinted Go?

Regulamin Vinted Go zakłada doręczenie do punktu odbioru w ciągu 3 do 6 dni roboczych od nadania (bez weekendów i świąt), natomiast Centrum Pomocy Vinted podaje orientacyjnie około 7 dni roboczych dla przesyłek międzynarodowych. Przed zgłoszeniem zaginięcia przesyłki należy odczekać do 25 dni roboczych.

### Czy Vinted Go ma infolinię po polsku?

Nie. Vinted Go udostępnia telefon wsparcia wyłącznie na ekranach własnych automatów za granicą (przycisk „?”, a w Belgii i Holandii numer `+31 20 793 2587` obsługiwany po angielsku, a w Belgii także po francusku). Wszystkie sprawy dotyczące zamówień zgłasza się przez pomoc Vinted w oknie rozmowy.

### Dlaczego mój numer śledzenia Vinted Go nie działa?

Po wysyłce może upłynąć do 48 godzin, zanim śledzenie zacznie zwracać wpisy, a literówka w numerze skutkuje komunikatem `We can’t find a parcel with this information`. Ponadto Vinted Go przestaje udostępniać historię przesyłki po upływie sześciu miesięcy od utworzenia etykiety.

:::sources
- [Vinted Go: Track your parcel](https://vintedgo.com/en/tracking) – anglojęzyczna strona śledzenia bez polskiej wersji i komunikat o nieznalezieniu paczki
- [Vinted Go: Frequently Asked Questions](https://vintedgo.com/en/faq?country=fr&region=europe) – kod odbioru od wielkiej litery, 7 dni na odbiór, przekierowanie do pobliskiego punktu, paczka oznaczona już jako dostarczona do punktu, przerwy w śledzeniu i pomoc na ekranie automatu
- [Vinted Go: Frequently Asked Questions (Netherlands)](https://vintedgo.com/en/faq?country=nl&region=europe) – linia wsparcia przy automatach `+31 20 793 2587` w Holandii po angielsku
- [Vinted Go: Frequently Asked Questions (Belgium)](https://vintedgo.com/en/faq?country=be&region=europe) – numer `+31 20 793 2587` w Belgii po angielsku i francusku
- [Vinted Go: Terms and Conditions for Delivery Services](https://vintedgo.com/en/terms-and-conditions?country=fr&region=europe) – spółka Vinted Go, UAB (Wilno), rodzaje punktów, cel 3–6 dni roboczych, przypomnienie po 3 dniach, próby doręczenia do domu i zdjęcie uszkodzonej paczki przy automacie
- [Vinted Go: Shipping service, trusted by millions of Vinted users](https://vintedgo.com/en?country=fr&region=europe) – sześć krajów własnej sieci Vinted Go i start w 2022 r.
- [Vinted Polska: Śledzenie przesyłki](https://www.vinted.pl/help/100) – przycisk „Śledź przesyłkę” w rozmowie i do 48 godzin oczekiwania na pierwsze statusy
- [Vinted Polska: Wysyłka na Vinted](https://www.vinted.pl/help/234) – kraje połączone z Polską, zintegrowani przewoźnicy na polskim Vinted, Vinted Go UAB oraz 2 dni na zgłoszenie niedoręczonej paczki
- [Vinted Francja: Envoi avec Vinted Go](https://www.vinted.fr/help/1110) – trasy międzynarodowe Vinted Go z Francji, wymóg automatu w pobliżu oraz dostawa do domu z Uber Direct
- [Vinted Francja: Comment utiliser les consignes Vinted Go ?](https://www.vinted.fr/help/1109) – przycisk „?” na ekranie automatu oraz termin 2 dni (i 14 dni u sprzedawców Pro)
- [Vinted Holandia: Hulp met een Vinted Go locker](https://www.vinted.nl/help/1109) – przycisk „?” na automatach w Holandii i 14 dni przy zamówieniach od sprzedawców Pro
- [Vinted Hiszpania: Ayuda en las taquillas de Vinted Go](https://www.vinted.es/help/1109) – pomoc telefoniczna pod przyciskiem „?” na automatach w Hiszpanii
- [Vinted Polska: Odbiór paczki](https://www.vinted.pl/help/1601) – kod odbioru, „Oznacz jako odebraną”, 2 dni na „Mam problem”, zwrot nieodebranej paczki i zwrot środków
- [Vinted Polska: Paczka nie dotarła](https://www.vinted.pl/help/657) – 5 dni roboczych w kraju, 7 za granicą oraz odczekanie do 25 dni roboczych przed „Mam problem”
- [Vinted Polska: Jak unikać phishingu oraz oszustw i je zgłaszać?](https://www.vinted.pl/help/628) – phishing przez wiadomości, SMS-y, e-maile i kody QR oraz adres `phishing@vinted.com`
- [Universal Parcel Scraper: Vinted Go](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/vinted-go) – angielskie statusy, sortownie, nazwy automatów i punktów bez punktów sąsiedzkich, przekazanie innemu przewoźnikowi i limit sześciu miesięcy
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – rozpoznawanie formatów Vinted Go, sprawdzanie numerów 16-cyfrowych w TNT i DHL eCommerce oraz przełączenie kodów `UW` na Poste Italiane
:::
