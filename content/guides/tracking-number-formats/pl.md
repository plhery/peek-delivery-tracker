---
title: Jaki kurier po numerze przesyłki? PX, 1Z, InPost, DPD i inne
description: PX i 10 cyfr to Poczta Polska, 24 cyfry to InPost, 1Z to UPS: jak rozpoznać kuriera po numerze paczki i ile cyfr mają numery DPD, DHL i przesyłek z zagranicy.
slug: jaki-kurier-po-numerze-przesylki
picture: Trzy etykiety z numerami przesyłek podzielonymi na kolorowe części, lupa na kodzie kraju CH i zaciekawiony Pip.
published: 2026-10-09
updated: 2026-10-27
---

Litery i długość numeru przesyłki często zdradzają, który przewoźnik ma Twoją paczkę. `1Z` i 16 liter lub cyfr to UPS, `PX` i 10 cyfr to [Poczta Polska](carrier:poczta-polska), numer [InPost](carrier:inpost) ma 24 cyfry, a 13 znaków w układzie `RR000000005CH` (dwie litery, dziewięć cyfr, dwie litery) to zwykle numer pocztowy, w którym dwie ostatnie litery wskazują kraj poczty, która go nadała. Najtrudniej jest z samymi cyframi: 10 albo 14 cyfr pasuje do kilku firm, więc rozstrzyga e-mail o wysyłce albo narzędzie, które sprawdzi numer u wszystkich pasujących przewoźników.

:::checker
:::

## Jak rozpoznać kuriera po numerze przesyłki?

Ten numer bywa różnie nazywany: numer przesyłki, numer paczki, numer listu przewozowego, a w aplikacjach często numer śledzenia. Niezależnie od nazwy sprawdzaj go po kolei:

:::steps
- Spójrz na litery | To one najbardziej zawężają wybór. `1Z` na początku to UPS, `PX` to Poczta Polska, a dwie litery na końcu zwykle oznaczają pocztę.
- Policz znaki | Sama długość rzadko przesądza sprawę, ale wyklucza część firm. Spacji nie licz: są tylko dla wygody czytania. Poczta Polska wprost prosi, żeby wpisywać numer „bez spacji oraz nawiasów”.
- Sprawdź e-maila | E-mail albo SMS o wysyłce zwykle podaje przewoźnika albo link do jego strony śledzenia. Na Allegro przewoźnika i numer przesyłki znajdziesz w szczegółach zakupu. To pewniejsze niż zgadywanie.
- Wypróbuj przewoźników | Wpisz numer na stronie każdego przewoźnika, który wchodzi w grę. Ten, u którego pojawi się historia przesyłki, ma Twoją paczkę.
:::

Jeśli nie chcesz bawić się w detektywa, wklej numer, link od przewoźnika albo całego e-maila o wysyłce do [Peeka](/): wyłowi z niego numer i rozpozna przewoźnika spośród ponad 3500. Jak działają takie trackery i czym się różnią, wyjaśnia poradnik o [śledzeniu przesyłek wszystkich firm](guide:universal-tracker).

## Jak wygląda numer przesyłki InPost, DPD, DHL i Poczty Polskiej

Oto, co przewoźnicy i standard pocztowy mówią o numerach, które w Polsce spotkasz najczęściej:

| Przewoźnik | Jak wygląda numer |
| --- | --- |
| InPost | 24 cyfry |
| Poczta Polska | kilka wariantów: `PX` i 10 cyfr (`PX1234567890`), 19 cyfr (`0015900773123456789`), 20 cyfr z `5900773` po trzech pierwszych albo format pocztowy z `PL` na końcu, zaczynający się np. od `RR`, `CP`, `VV` lub `EE`. W tej samej wyszukiwarce śledzisz też Pocztex |
| [DPD](carrier:dpd-pl) | 14 znaków: w DPD Polska 13 cyfr i litera (`1234567890123A`) albo 14 cyfr zaczynających się od 13 (`13000000000000`). Wyszukiwarka DPD Polska przyjmuje też 9-znakowy kod nadania |
| [DHL eCommerce Polska](carrier:dhl-ecommerce-pl) (paczki krajowe) | 11 cyfr, bez liter, według serwisu Mój DHL |
| [DHL Express](carrier:dhl-express) | 10 cyfr, bez liter, według DHL Express w Holandii |
| GLS | numer paczki od nadawcy albo Track-ID z awiza; długości GLS nie podaje |
| [Packeta](carrier:packeta) | litera `Z` i 10 cyfr, np. `Z1234567890`: „Numer śledzenia musi zaczynać się od litery 'Z' i składać się z 10 cyfr” |
| [ORLEN Paczka](carrier:orlen-paczka) | numer paczki to 13 cyfr spod kodu kreskowego na etykiecie, zaczynających się od `21`, `23` albo `26` (`2101234567890`); wyszukiwarka przyjmuje też 10-cyfrowy kod nadania |
| [UPS](carrier:ups) | `1Z` i 16 liter lub cyfr, razem 18 znaków, np. `1Z999AA10123456784`, przykładowy numer z poprawną cyfrą kontrolną UPS |
| Amazon | w danych przewoźników Peek `TBA` i 12 cyfr albo kod kraju i 10 cyfr, więcej niżej |
| [YunExpress](carrier:yunexpress) | `YT` i 16 cyfr; `YT` i 13 cyfr to YTO Express |
| [Cainiao](carrier:cainiao) (AliExpress) | Cainiao nie publikuje jednego formatu. W danych przewoźników, z których korzysta Peek, `DOFR` albo `CNFR`, 13 cyfr i `HD` to Cainiao; `LP` albo `CNG` i 14 cyfr to prawdopodobnie Cainiao, ale nie na pewno |
| [Yanwen](carrier:yanwen) | `BYS` i 9 cyfr albo dwie litery, 9 cyfr i `YP` (często od `UL` albo `UK`); Yanwen nie publikuje przykładowego numeru. `YW`, trzy litery i 12 cyfr to własna usługa doręczeń Yanwen w USA |
| [4PX](carrier:4px) | `4PX`, 13 cyfr i `CN`; 4PX nie publikuje przykładowego numeru, a jego strona przyjmuje od 5 do 50 liter i cyfr. `LP`, 13 cyfr i `CN` to prawdopodobnie też 4PX |
| [China Post](carrier:china-post), EMS | format pocztowy z `CN` na końcu; międzynarodowe numery EMS zaczynają się od `E` |

InPost od razu mówi, gdy długość się nie zgadza. Wyszukiwarka na stronie głównej inpost.pl nie przyjmie innego numeru i wyświetla wtedy komunikat `Podaj jeden numer przesyłki zawierający 24 cyfry`.

### Numer zaczyna się od PX, AD albo LP: jaki to kurier?

- **`PX` i 10 cyfr** to Poczta Polska, np. `PX1234567890`.
- **`AD`** spotkasz przy przesyłkach Allegro Delivery. ORLEN Paczka pisze, że swoje paczki z Allegro Delivery da się śledzić wyłącznie w aplikacji i na stronie Allegro, numerem zaczynającym się od `AD`. Na stronie ORLEN Paczki ich nie znajdziesz.
- **`LP` i 14 cyfr** może oznaczać AliExpress i Cainiao. Tak wynika z danych przewoźników, z których korzysta Peek, choć sam Cainiao tego formatu na swoich stronach nie opisuje.

## Ile cyfr ma numer przesyłki?

Nie ma jednej długości. Poniżej te, które przewoźnicy podają na własnych stronach. Część się pokrywa, dlatego sam numer może pasować do kilku firm:

| Długość | Kto jej używa |
| --- | --- |
| 9 znaków | kod nadania DPD, który wyszukiwarka DPD Polska przyjmuje zamiast numeru przesyłki |
| 10 cyfr | DHL Express; w InPost tyle cyfr ma kod zwrotu, a nie numer przesyłki |
| 11 cyfr | DHL eCommerce Polska, paczki krajowe |
| 11 znaków | Packeta: `Z` i 10 cyfr |
| 12 znaków | Poczta Polska: `PX` i 10 cyfr |
| 13 znaków | każda poczta w formacie pocztowym, np. `RR000000005CH`; ORLEN Paczka: 13 cyfr |
| 14 znaków | DPD: w DPD Polska 13 cyfr i litera albo 14 cyfr od 13 |
| 19 cyfr | Poczta Polska |
| 20 cyfr | Poczta Polska, z `5900773` po trzech pierwszych cyfrach |
| 24 cyfry | InPost |
| 14 do 20 znaków | Hermes w Niemczech, litery i cyfry |
| 10 do 39 znaków | DHL w Niemczech, zależnie od usługi |

Dwa ostatnie wiersze przydadzą się przy zakupach z Niemiec: 14 cyfr może wtedy należeć do DPD, Hermesa albo DHL. Przy paczce z Ukrainy 14 cyfr, na przykład zaczynających się od 204, może być listem przewozowym [Nova Post](carrier:nova-poshta); paczki między Polską a Ukrainą wozi też [Meest](carrier:meest).

## Numer zaczyna się od TBA albo PL: czy to Amazon?

Jeśli zgadza się długość, najpewniej tak. W danych przewoźników, z których korzysta Peek, `TBA`, `TBC` lub `TBM` i 12 cyfr to [Amazon Logistics](carrier:amazon-logistics), czyli dostawy samego Amazona. To samo oznacza kod kraju, np. `PL` albo `DE`, z 10 cyframi. Nie myl tego z numerem Poczty Polskiej, który na `PL` się kończy, a nie zaczyna. Zamówienie z Amazon.pl śledzisz na stronie zamówień w swoim koncie, w aplikacji Amazon Shopping albo w przeglądarce.

## Format pocztowy: dwie litery, dziewięć cyfr, dwie litery

Poczty na całym świecie używają wspólnego 13-znakowego formatu, który Światowy Związek Pocztowy (UPU) opisał w standardzie S10. Oto jego budowa:

:::anatomy RR 00000000 5 CH
- RR | Rodzaj przesyłki: R to list polecony
- 00000000 | Numer seryjny, osiem cyfr
- 5 | Cyfra kontrolna, wyliczona z numeru seryjnego
- CH | Kraj, którego poczta nadała numer: Szwajcaria
:::

W przesyłkach zagranicznych pierwsza litera mówi, co to za przesyłka. W obrocie krajowym poczty mogą używać liter inaczej.

- `E`: EMS, ekspresowa usługa poczt
- `C`: paczka
- `R`: list polecony
- `L`: list ze śledzeniem
- `V`: list wartościowy
- `U`: towar wysłany jako list, bez śledzenia dla Ciebie

Poczta Polska podaje w swojej wyszukiwarce przykłady w tym formacie, zaczynające się od `RR`, `CP`, `VV` i `EE`, wszystkie z `PL` na końcu. To tylko wzory: opisanego niżej testu cyfry kontrolnej nie przechodzą.

Ostatnie dwie litery nie zawsze mówią, skąd paczka wyruszyła. Według UPU kod kraju nie jest pewną wskazówką, skąd przesyłka pochodzi: Poczta Szwajcarska używa `CH` także dla przesyłek wysyłanych ze swoich placówek za granicą. A gdy w kraju działa kilku operatorów pocztowych, sam kod nie mówi, który z nich nadał numer.

Cyfra kontrolna wyłapuje literówki. Tak sprawdzisz numer:

1. Pomnóż osiem cyfr numeru seryjnego kolejno przez 8, 6, 4, 2, 3, 5, 9 i 7, a wyniki dodaj.
2. Podziel sumę przez 11 i odejmij resztę od 11.
3. Wynik to cyfra kontrolna, z dwoma wyjątkami: 10 daje 0, a 11 daje 5.

W `RR000000005CH` wszystkie osiem cyfr numeru seryjnego to 0: suma wynosi 0, z dzielenia przez 11 zostaje reszta 0, a 11 − 0 = 11, co daje 5. Jeśli przepisany ręcznie numer pocztowy nie przechodzi tego testu, szukaj literówki albo dwóch przestawionych cyfr.

### Numer przesyłki zaczyna się od UL albo UU: czyj to numer?

Jeśli ma 13 znaków i kończy się dwiema literami, np. `CN`, to numer pocztowy z zakresu `U`: towar wysłany jako list. Dwie ostatnie litery mówią, poczta którego kraju go nadała. Dłuższy numer zaczynający się od `UU` nie ma tego formatu: przewoźnika sprawdź w e-mailu o wysyłce.

> Na przesyłce z zagranicy numer pocztowy zaczynający się od `U` (od `UA` do `UZ`) nie służy do śledzenia przez odbiorcę. UPU przeznacza ten zakres dla towarów wysyłanych jako listy, bez śledzenia dla klienta, a belgijska poczta bpost pisze wprost, że paczki z kodem kreskowym zaczynającym się od U nie da się śledzić. Nie licz na to, że śledzenie ruszy później.

## Numery, które nie są numerem przesyłki

Numer zamówienia i numer oferty z Allegro należą do sklepu. Kod odbioru, którym otwierasz skrytkę w automacie, i 10-cyfrowy kod zwrotu InPost też nie służą do śledzenia, za to kod nadania DPD i ORLEN Paczki prowadzi do paczki. Masz tylko numer zamówienia? Przeczytaj, [gdzie znaleźć numer przesyłki](guide:find-tracking-number).

## Dlaczego numer przesyłki może się zmienić po drodze

Paczka, która przekracza granicę, często zmienia przewoźnika, a każda firma może nakleić własną etykietę. UPU pozwala poczcie kraju docelowego dodać własny kod kreskowy obok pierwotnego, o ile nie jest on w 13-znakowym formacie pocztowym.

:::journey
- shop | Sprzedawca | Drukuje etykietę i pierwszy numer
- plane | Długi lot | Paczka leci z pierwszym numerem
- customs | Urząd celny | [Odprawa celna](guide:customs) w Polsce
- handover | Przewoźnik w Polsce | Może nakleić własną etykietę z nowym numerem
- home | Twoje drzwi | Ostatnie skany mogą być już pod nowym numerem
:::

- **Przesyłki pocztowe** to najprostszy przypadek: UPU pozwala na jeden numer S10 na przesyłkę, więc zwykle działa on na stronach poczt obu krajów.
- **Allegro Delivery** to, jak pisze Allegro, program dostaw realizowany przez [Allegro One](carrier:allegro-one) razem z ORLEN Paczką i DHL, a wśród jego metod są też automaty, punkty i kurier DPD. Paczkę wiezie więc jeden z partnerów, a śledzić ją możesz w Allegro. Przesyłek ORLEN Paczki z tego programu nie znajdziesz na jej stronie, tylko w Allegro, pod numerem od `AD`.
- **AliExpress, tania wysyłka:** Cainiao pisze, że śledzenie kończy się po przekazaniu paczki lokalnemu przewoźnikowi, a o kontakt do niego możesz zapytać sprzedawcę. Więcej w poradniku o [śledzeniu paczki z Chin](guide:tracking-from-china).

Numer, który nic nie pokazuje, nie zawsze jest źle wpisany. Mój DHL wśród powodów braku informacji podaje też numer innej firmy i paczkę z zagranicy, która nie dotarła jeszcze do Polski. Co znaczy pusta strona u innych przewoźników, ile czekać na pierwszy skan i co robić, gdy paczka po prostu stoi w miejscu, przeczytasz w poradniku [dlaczego status przesyłki się nie zmienia](guide:tracking-not-updating).

:::sources
- [UPU: S10 standard, Identification of postal items](https://www.upu.int/UPU/media/upu/files/postalSolutions/programmesAndServices/standards/S10-12.pdf) – format pocztowy, jego litery, cyfra kontrolna, kod kraju, dodatkowy kod poczty docelowej
- [Poczta Polska: Śledzenie przesyłek](https://emonitoring.poczta-polska.pl/) – kształty numerów, wpisywanie bez spacji i nawiasów, przesyłki Pocztex
- [InPost: Śledź paczkę, wyszukiwarka na stronie głównej](https://inpost.pl/) – numer z 24 cyfr i komunikat przy innej długości
- [InPost: Kontakt i pomoc](https://inpost.pl/kontakt) – kod odbioru w Paczkomacie, 10-cyfrowy kod zwrotu
- [DPD Polska: Najczęściej zadawane pytania](https://www.dpd.com/pl/pl/najczesciej-zadawane-pytania-faq/) – 14-znakowy numer przesyłki, 9-znakowy kod nadania
- [DPD Polska: Track & Trace](https://tracktrace.dpd.com.pl/findParcel) – numer przesyłki, numer referencyjny, kod nadania i numer zlecenia
- [Packeta: Śledzenie przesyłki](https://tracking.packeta.com/pl/) – litera Z i 10 cyfr
- [4PX: Global Order Tracking](https://track.4px.com/) – od 5 do 50 liter i cyfr
- [Mój DHL: Śledzenie przesyłek](https://mojdhl.pl/tracking) – 11 cyfr dla przesyłek krajowych DHL eCommerce Polska, powody braku informacji
- [DHL Express Netherlands: DHL Express or DHL eCommerce number](https://www.dhlexpress.nl/en/consumer/faq/express-account-zendingsnummer/my-shipment-number-dhl-express-or-dhl-ecommerce) – 10 cyfr
- [DHL: Sendungsverfolgung, Probleme und Lösungen](https://www.dhl.de/de/privatkunden/hilfe-kundenservice/sendungsverfolgung/probleme-loesungen.html) – od 10 do 39 znaków w Niemczech
- [Hermes: Sendungsverfolgung](https://www.myhermes.de/empfangen/sendungsverfolgung/) – od 14 do 20 znaków
- [GLS Polska: FAQ](https://gls-group.com/PL/pl/faq/) – numer paczki od nadawcy, Track-ID na awizie
- [ORLEN Paczka: Śledź paczkę](https://www.orlenpaczka.pl/sledz-paczke/) – numer paczki lub kod nadania, numery od AD w Allegro Delivery
- [ORLEN Paczka: Reklamacje](https://www.orlenpaczka.pl/reklamacje/) – „Numer paczki (EAN)”
- [Yanwen: YW Tracking (po angielsku)](https://track.yw56.com.cn/en/index) – strona śledzenia Yanwen, bez przykładowego numeru
- [Allegro: Program dostaw Allegro Delivery – informacje dla kupujących](https://allegro.pl/pomoc/dla-kupujacych/metody-dostawy/program-dostaw-allegro-delivery-informacje-dla-kupujacych-Bvw1bwrMmIz) – partnerzy i metody dostawy programu
- [Allegro: Gdzie sprawdzisz numer i status swojej przesyłki](https://allegro.pl/pomoc/dla-kupujacych/metody-dostawy/gdzie-sprawdzisz-numer-i-status-swojej-przesylki-LvP7agrzOhw) – numer oferty i numer przesyłki w szczegółach zakupu
- [About Amazon Polska: 5 sposobów na bezpieczne zakupy z Amazon. Nie daj się oszustom!](https://www.aboutamazon.pl/wiadomosci/zakupy-na-amazon/5-sposobow-na-bezpieczne-zakupy-z-amazon-nie-daj-sie-oszustom) – historię zamówień śledzisz na stronie zamówień w swoim koncie
- [About Amazon Polska: Amazon.pl startuje w Polsce](https://www.aboutamazon.pl/wiadomosci/zakupy-na-amazon/amazon-pl-startuje-w-polsce) – śledzenie zamówień w aplikacji Amazon Shopping i w przeglądarce (2021)
- [About Amazon Polska: Amazon unowocześnia dostawy w Europie](https://www.aboutamazon.pl/wiadomosci/miejsca-pracy-i-inwestycje/amazon-unowoczesnia-dostawy-w-europie) – własna sieć dostaw ostatniej mili Amazona w Europie
- [UPS: sample package label](https://www.pld-certify.ups.com/CerttoolHelp/PLD0200/WebHelp_pld0200/LeadPackage.htm) – numer 1Z
- [Universal Parcel Scraper: carrier catalog](https://github.com/plhery/universal-parcel-scraper/blob/main/data/catalog.json) – PX, LP, TBA oraz PL i 10 cyfr, numery Cainiao (`DOFR`, `CNFR`, `CNG`), a także YunExpress (`YT` i 16 cyfr) i YTO Express (`YT` i 13 cyfr), numery DPD Polska (13 cyfr i litera albo 14 cyfr od 13) 4PX (`4PX` i `LP`, 13 cyfr i `CN`), Yanwen (`BYS`, `YP` i `YW`), Poczty Polskiej (19 i 20 cyfr) oraz ORLEN Paczki (13 cyfr od `21`, `23` i `26`) w danych przewoźników, z których korzysta Peek
- [YunExpress: YunTrack](https://www.yuntrack.com/) – numery od YT
- [Cainiao: Help centre](https://global.cainiao.com/helpDoc.htm) – przy taniej wysyłce brak śledzenia po przekazaniu lokalnemu przewoźnikowi
- [Cainiao: Guidance](https://global.cainiao.com/helpDoc.htm?slug=ofzig4) – numery Cainiao bez opisu formatu, tylko litery i cyfry
- [bpost: Puis-je suivre mon colis en ligne ?](https://www.bpost.be/fr/faq/puis-je-suivre-mon-colis-en-ligne) – kodów kreskowych zaczynających się od U nie da się śledzić
:::
