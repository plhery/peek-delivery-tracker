---
title: Śledzenie STO Express: numery 申通快递, statusy i kontakt
description: Śledź przesyłkę STO Express (申通快递) w Chinach: formaty numerów, znaczenie 签收 i pozostałych skanów STO, punkty odbioru, automaty i kontakt z STO.
slug: sledzenie-sto-express
published: 2026-11-22
updated: 2026-11-22
---

STO Express (申通快递, Shentong) to chińska firma kurierska założona w 1993 roku, przedstawiająca się jako pierwsze prywatne przedsiębiorstwo kurierskie w Chinach. Numer STO należy zazwyczaj do przesyłki krajowej w Chinach — często zamówienia internetowego jadącego do ciebie, znajomego lub magazynu spedytora. Sprawdzisz ją na [stronie STO](https://www.sto.cn/pc), w polu `查快递` (wyłącznie po chińsku), albo w polu powyżej.

## Numer śledzenia STO Express: jak wygląda

Numery STO (`快递单号`) składają się wyłącznie z cyfr. Wyszukiwanie STO w Peek przyjmuje 12, 13 lub 15 cyfr; najnowsze listy przewozowe mają 15 cyfr. Ze zmyślonymi cyframi:

| Numer (zmyślony) | Co to jest |
| --- | --- |
| `774829173654281` | `77` i 13 kolejnych cyfr: list przewozowy STO Express. [BRT](carrier:brt) i Yunda również używają 15 cyfr, więc Peek sprawdza w STO. |
| `482917365428` | 12 cyfr: starszy format STO, współdzielony z [FedEx](carrier:fedex), [SF Express](carrier:sf-express), [ZTO Express](carrier:zto) i wieloma innymi. |
| `4829173654281` | 13 cyfr: również starszy format STO, którego Peek nie przypisuje automatycznie do STO. |

Spacje nie mają znaczenia w Peek.

**Gdzie go znaleźć.** Sprzedawca podaje go w danych wysyłki zamówienia; znajduje się też na etykiecie (`快递面单`) naklejonej na paczkę. Nie możesz go znaleźć? Zobacz [jak znaleźć numer śledzenia](guide:find-tracking-number).

**Na stronie STO.** Wpisz numer w pole `查快递` na `sto.cn` i kliknij `查询` (szukaj). Umowa o świadczenie usług STO stanowi, że strona, aplikacja i konto WeChat udostępniają śledzenie przez rok od dnia nadania.

## Statusy śledzenia STO Express — wyjaśnienie

Każdy skan w śledzeniu STO ma typ po chińsku, godzinę w czasie chińskim (UTC+8) oraz nazwę placówki STO. Peek pokazuje te typy po angielsku:

| Skan STO | W Peek | Status w Peek | Co to znaczy dla ciebie |
| --- | --- | --- | --- |
| `收件` | `Picked up` | Przyjęta przez przewoźnika | STO odebrało paczkę od nadawcy. |
| `发件` | `Departed`, z `for` i następną placówką, jeśli STO ją podaje | W transporcie | Opuściła placówkę STO w drodze do kolejnej. |
| `到件` | `Arrived` | W transporcie | Dotarła do sortowni lub oddziału. |
| `派件` | `Out for delivery` | W doręczeniu | Kurier ma ją w trasie. |
| `驿站代收` | `At a parcel station for collection` | Do odbioru | Punkt odbioru (`驿站`) przyjął ją dla ciebie. Odbierz ją. |
| `柜机代收` | `In a parcel locker for collection` | Do odbioru | Czeka w automacie paczkowym. Odbierz ją. |
| `客户签收` | `Delivered` | Dostarczona | Ktoś pokwitował odbiór. STO zapisuje to także, gdy pokwituje punkt lub automat. |
| `签收` | `Delivered` | Dostarczona | Pokwitowana. |

Pozostałe typy skanów zachowują w Peek chińskie nazwy STO. Typ wskazujący zwrot (`退回` lub `退件`) wyświetla się jako `Return to the sender started` („Zgłoszono problem”). Późniejsze skany doręczenia dotyczą drogi powrotnej: `Out for delivery back to the sender`, `In a parcel locker or station on its way back` oraz `Returned to the sender`.

**Wysłana do spedytora?** Wtedy `签收` oznacza, że magazyn spedytora pokwitował odbiór i część STO dobiegła końca. Dalej przesyłkę prowadzi numer spedytora.

## Doręczenia STO Express w Chinach: kurierzy, punkty i automaty

STO podaje, że jego sieć obejmuje 99,7 % gmin w Chinach. Umowa o świadczenie usług pozwala doręczyć paczkę na cztery sposoby:

- tobie osobiście;
- osobie mieszkającej z tobą lub wskazanej przez ciebie;
- do punktu odbioru (`代收点`) albo automatu paczkowego (`自提柜`) w okolicy adresu;
- w inny sposób wskazany przez ciebie lub nadawcę.

Po pozostawieniu przesyłki w jeden z tych sposobów z nienaruszonym opakowaniem zewnętrznym umowa uznaje usługę STO za wykonaną. Status „Do odbioru” w Peek jest więc normalny: paczka czeka w punkcie lub automacie w pobliżu adresu. Dokładny adres punktu znajdziesz w śledzeniu samego STO; dopóki paczka tam czeka, Peek odczytuje kod odbioru (`取件码`), gdy widnieje w ostatnim skanie.

Aby zmienić dane odbiorcy, internetowa obsługa klienta STO ma dedykowany temat (`修改收件人信息`): poproś o to tam (kontakt niżej) albo u sprzedawcy.

## Paczka STO nie aktualizuje się, zaginęła lub wróciła

**Jeszcze nic nie ma.** Śledzenie STO nic nie zwraca dla nieznanego jeszcze numeru ani po upływie rocznego limitu. Jeśli pozostaje puste, sprawdź numer u sprzedawcy. W tym czasie paczka przypisana do STO Express pokazuje „Oczekiwanie na przewoźnika STO Express”.

**Brak aktualizacji.** Gdy przesyłka wciąż w drodze nie ma nowego skanu przez 4 dni, Peek pokazuje datę ostatniego skanu („Brak aktualizacji od …”) i „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika STO Express.” z linkiem „Poproś przewoźnika STO Express o pomoc” do internetowej obsługi klienta STO, gdzie brak aktualizacji (`快递单号没有记录更新`) jest jednym z tematów. Zobacz [dlaczego śledzenie przestaje się aktualizować](guide:tracking-not-updating) oraz [śledzenie paczek z Chin](guide:tracking-from-china).

**Zaginiona lub uszkodzona.** Zapytaj najpierw sprzedawcę: to on zlecił przewóz STO, nie ty. Zgodnie z umową STO nadawca — lub wskazana przez niego osoba — składa reklamację w ciągu 30 dni od wykrycia zaginięcia lub szkody, wraz z dowodami. Bez ubezpieczenia (`保价`) STO wypłaca rzeczywistą stratę do 500 juanów, a dla dokumentów do 100 juanów. Ubezpieczona przesyłka jest pokrywana do zadeklarowanej wartości. Bez ubezpieczenia i dowodu wartości STO zwraca trzykrotność opłaty za wysyłkę. W razie opóźnienia z winy STO opłata za przesyłkę jest umarzana. Zobacz [zaginione przesyłki](guide:lost-parcel).

**Zwrócona.** STO może odesłać paczkę, gdy adres jest niejasny, odbiorca odmawia przyjęcia albo dwie próby doręczenia nie powiodą się nie z winy STO; koszty ponosi nadawca. W Peek zobaczysz `Return to the sender started`, a potem drogę powrotną. Zobacz [zwrócona nadawcy](guide:returned-to-sender).

**Dostarczona, ale jej nie ma.** `签收` i `客户签收` oznaczają, że ktoś podpisał: ty, domownik, wskazana osoba albo punkt odbioru lub automat. Sprawdź pobliskie punkty i automaty, recepcję budynku oraz — jeśli dotyczy — magazyn spedytora. Reklamację zgłosisz przyciskiem skargi (`投诉反馈`) w internetowej obsłudze STO. Zobacz [dostarczona, ale nieotrzymana](guide:delivered-not-received).

## Kontakt i obsługa klienta STO Express

Kanały opublikowane przez STO:

- **Obsługa klienta online** na `page.sto.cn`, pod linkiem `在线咨询` na `sto.cn`: asystent czatu z konsultantami czynnymi w godz. 8:30–22:00 czasu pekińskiego, z przyciskami do nadawania paczek, sprawdzania cen i składania skarg (`投诉反馈`).
- **WeChat:** konto usługowe i miniprogram do nadawania przesyłek. STO jest też na Douyin. Wszystkie trzy znajdziesz w stopce `sto.cn`.

Ta strona nie podaje numeru telefonu: Peek podaje tylko kontakty odczytane w tekście własnych stron STO, a żadna z nich numeru nie publikuje. W sprawie samego zamówienia zwróć się do sprzedawcy.

## Śledzenie paczki STO Express w Peek

Sam układ numeru STO nie wystarcza Peek do pewnego rozpoznania. Wklej go mimo to: [Peek](/) obsługuje ponad 4500 przewoźników.

Przy 15-cyfrowym numerze zaczynającym się od `77` Peek od razu pyta STO Express i BRT, wyświetlając „Sprawdzanie usług śledzenia…”. Jeśli STO ma przesyłkę, pojawia się „STO Express ma tę przesyłkę.” i Peek zapisuje ją tam. Jeśli nie — „jeszcze nie znaleziono · Peek sprawdza dalej”. Dodaj ją mimo to: zobaczysz „Prawdopodobnie STO Express” i „Oczekiwanie na pierwszy skan przewoźnika STO Express”, a po pierwszych godzinach Peek pyta STO co 6 godzin (w aplikacji na iPhone’a lub Androida albo na koncie). Dla sprawdzenia bez konta Peek pyta przez pierwsze 6 godzin, potem po otwarciu linku (najwyżej co 6 godzin, a po dwóch dniach raz dziennie) i przy włączonych powiadomieniach raz dziennie przez 30 dni. Po tygodniu bez wyniku prosi o sprawdzenie numeru u nadawcy lub wybór innego przewoźnika.

Automatycznie Peek pyta STO wyłącznie dla numerów zaczynających się od `77`. Przy 12 cyfrach sprawdza innych przewoźników tego formatu (m.in. DHL i BRT), a potem usługi śledzenia; jeśli rozpoznają STO Express, Peek przypisuje paczkę do STO i przy kolejnym sprawdzeniu pyta je bezpośrednio.

Aby Peek czytał śledzenie STO od początku, stuknij „Zmień” i wybierz STO Express w sekcji „Pasują do numeru” (12 cyfr albo 15 zaczynających się od `77`). Przy 13 cyfrach lub 15 niezaczynających się od `77` wybierz go pod „Wszyscy przewoźnicy” albo wyszukaj `STO` lub `申通`. W już dodanej paczce użyj „Zmień przewoźnika”. Wyszukiwanie STO w Peek przyjmuje tylko 12, 13 lub 15 cyfr — dla innej długości wyświetli „nie wydaje takich numerów”.

Peek czyta własne śledzenie STO, stojące za jego obsługą online. Dla każdego skanu pokazuje etap (tabela wyżej), miasto placówki STO (jej chińską nazwę, gdy nie może ustalić miejscowości) oraz godzinę z zegara chińskiego przeliczoną na twoją strefę czasową („Godziny w twojej strefie czasowej:”). Powtórzony skan pojawia się raz, a waga widnieje, jeśli zapisały ją wagi STO. Odczytuje też kod odbioru (`取件码`) z najnowszego skanu punktu lub automatu, dopóki paczka tam czeka, oraz osobę podpisującą przy doręczeniu, pomijając pełny opis STO, nazwiska i telefony kurierów oraz placówek i adresy odbioru. STO nie podaje przewidywanej daty dostawy, więc Peek jej nie wyświetla. Peek śledzi tylko numer STO — numer spedytora dodaj jako osobną przesyłkę.

Dopóki masz przesyłkę otwartą w Peek — lub w dniu doręczenia z włączonymi powiadomieniami — Peek odpytuje bezpośrednio STO nawet co 10 minut, a od 6:00 czasu pekińskiego w dniu skanu w doręczenie nawet co 2 minuty, dopóki tracker STO odpowiada; poza tym, oraz po dwóch dniach bez wiadomości (12 godzin przy statusie „Zarejestrowana” lub „Przyjęta przez przewoźnika”), sprawdza co godzinę. Może wysyłać powiadomienia dla opcji „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek nie skontaktuje się za ciebie ze STO, spedytorem ani ze sprzedawcą.

## Pytania o śledzenie STO Express

### Czy STO Express jest wiarygodne?

Tak. Założone w 1993 r. STO Express określa się jako pierwsza prywatna firma kurierska w Chinach i pionier franczyzy w tej branży. Jest notowane na chińskim rynku akcji A i podaje 75 sortowni, 5075 niezależnych oddziałów oraz ponad 98 000 lokalnych placówek.

### Co oznacza 签收 w śledzeniu STO?

Pokwitowano odbiór: Peek pokazuje `签收` i `客户签收` jako „Dostarczona”. Podpisałeś ty, domownik, wyznaczona osoba albo punkt odbioru czy automat. `驿站代收` i `柜机代收` oznaczają, że paczka czeka w punkcie lub automacie do odbioru.

### Dlaczego mój numer STO nie pokazuje śledzenia?

STO nie zeskanowało jeszcze paczki, numer jest błędny albo przesyłka została nadana ponad rok temu — STO udostępnia śledzenie przez rok. Sprawdź numer u sprzedawcy albo dodaj go w Peek, który sprawdza dalej.

### Gdzie znaleźć kod odbioru paczki STO?

Dopóki paczka czeka w punkcie lub automacie, Peek odczytuje kod odbioru (`取件码`) z najnowszego skanu, jeśli się w nim znajduje, i pokazuje `At a parcel station for collection` lub `In a parcel locker for collection`. Dokładny adres punktu lub automatu pozostaje w śledzeniu STO na `sto.cn`.

### Jak skontaktować się ze STO Express?

Przez obsługę klienta online na stronie STO: konsultanci odpowiadają w godz. 8:30–22:00 czasu pekińskiego, a na czacie jest przycisk skarg (`投诉反馈`). W sprawie zamówienia ze sklepu zapytaj najpierw sprzedawcę.

:::sources
- [STO Express: 申通快递官网 (home)](https://www.sto.cn/pc) – pole śledzenia, konto usługowe i miniprogram WeChat, Douyin
- [STO Express: 关于我们 (about STO)](https://www.sto.cn/pc/about) – założone w 1993 r., pierwsza prywatna firma kurierska, model franczyzowy, notowanie akcji A, sortownie, oddziały, placówki, zasięg gminny
- [STO Express: 申通快递服务协议 (express service agreement)](https://www.sto.cn/h5/standard) – śledzenie przez rok, formy doręczenia, zwroty, odszkodowanie z ubezpieczeniem i bez, reklamacja w 30 dni, opóźnienie
- [STO Express: 在线客服 (online customer service)](https://page.sto.cn/ued-projects/sto-customer-onlinekf?fromSource=pc-stocn&pc=true) – czat, obsługa ludzka 8:30–22:00, skargi, tematy braku aktualizacji i zmiany danych odbiorcy
- [Universal Parcel Scraper: STO Express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/sto) – układy numerów, typy skanów, zwroty, czas chiński, waga, kod odbioru, osoba podpisująca, pola pokazywane i pomijane, brak przewidywanej daty
:::
