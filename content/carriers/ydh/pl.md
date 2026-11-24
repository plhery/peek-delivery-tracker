---
title: Śledzenie YDH: kody YDH...YQ i AZ, przekazania i pomoc
description: Śledź paczkę transgraniczną YDH Express z Chin: kody YDH...YQ i AZ, statusy DSD i CARR, lokalni przewoźnicy, cło oraz kontakt.
slug: sledzenie-ydh
published: 2026-11-24
updated: 2026-11-24
---

YDH (*YDH Express* / *YDH Cross Border*, po chińsku *义达跨境*) to chiński operator transgranicznej logistyki e-commerce założony w 2005 roku z siedzibą w Szanghaju. Obsługując ponad 20 chińskich centrów e-commerce — w tym Shenzhen, Kanton, Hangzhou, Ningbo i Hongkong — oraz biura w Japonii, Stanach Zjednoczonych, Wielkiej Brytanii i Belgii, YDH przewozi małe paczki i fracht komercyjny do ponad 200 krajów i regionów, a następnie przekazuje je lokalnym przewoźnikom. Przesyłkę sprawdzisz w [portalu śledzenia YDH](https://track.ydhex.com/track_query.aspx) albo wklejając numer w pole powyżej.

## Numery śledzenia YDH: formaty YQ i AZ

Numer listu przewozowego znajdziesz w szczegółach zamówienia na platformie sprzedażowej lub w e-mailu wysyłkowym. Numery YDH mają 15 znaków: zaczynają się od `YDH`, dalej jest 10 cyfr i końcówka `YQ` lub `AZ`:

| Format (zmyślony) | Co to jest |
| --- | --- |
| `YDH4829173640YQ` | Transgraniczny numer paczki YDH: `YDH` + 10 cyfr + `YQ`. |
| `YDH4829173640AZ` | Transgraniczny numer paczki YDH: `YDH` + 10 cyfr + `AZ`. |

Po przejściu odprawy celnej w kraju docelowym YDH przekazuje paczkę lokalnemu przewoźnikowi: [USPS](carrier:usps) lub [GOFO](carrier:gofo) w USA oraz [Poste Italiane](carrier:poste-italiane) we Włoszech to przykłady firm, których skany YDH przekazuje pod twoim numerem `YDH`. YDH może także podać własny numer lokalnego przewoźnika. Zobacz [formaty numerów przesyłek](guide:tracking-number-formats), aby sprawdzić inne kody transgraniczne.

## Co oznaczają statusy i kody YDH

YDH przypisuje krótkie kody etapów do własnych skanów magazynowych, eksportowych i lotniczych (po chińsku lub angielsku), a jednocześnie przekazuje skany lokalnego partnera doręczeniowego w jego oryginalnym brzmieniu.

| Kod / opis YDH | Co to oznacza dla ciebie | Peek pokazuje |
| --- | --- | --- |
| `IR` (`Shipment information received`, `Label Created`) | Dane elektroniczne otrzymane od sprzedawcy przed fizycznym skanem paczki przez YDH. | „Zarejestrowana” |
| `AF` (`Shipment arrived at facility`), `GNZY` (`快件到达作业中心`) | Paczka dotarła do magazynu lub sortowni YDH w Chinach. | „W transporcie” |
| `HBYP`, `DF`, `DA` (`Shipment departed from airport` / `航班起飞`), `TDDY` (`Flight delay`), `AA` (`Shipment arrived at airport` / `航班到达`) | Oczekuje na lot, wyleciała z Chin (lub lot opóźniony) i wylądowała na lotnisku docelowym. | „W transporcie” |
| `RC` (`Released from customs`), `CARR` (`handed over to the final carrier`), `DSD` (`D-SCAN DELAY`), `PU` | Zwolniona z cła w kraju docelowym, przekazana przewoźnikowi ostatniej mili, oczekuje na jego pierwszy skan (`D-SCAN DELAY`) lub została przez niego odebrana. | „W transporcie” |
| `OD` (`Out for Delivery`) | Załadowana przez przewoźnika docelowego do doręczenia. | „W doręczeniu” |
| `DD` | Problem z doręczeniem lub nieudana próba zgłoszona przez lokalnego partnera. | „Próba doręczenia” / „Zgłoszono problem” |
| `CC` | Doręczona przez partnera docelowego (z nazwiskiem odbierającego, gdy jest podane). | „Dostarczona” |

**Co oznacza `D-SCAN DELAY` (`DSD`).** Gdy YDH przekaże zbiorczą partię przesyłek poczcie lub kurierowi w kraju docelowym (`CARR`), może minąć kilka dni, zanim partner zeskanuje pojedynczą paczkę we własnej sieci. `D-SCAN DELAY` sygnalizuje właśnie ten poślizg przy przekazaniu — przesyłka nadal jest w drodze, nie zaginęła.

## Jak działa transgraniczna dostawa YDH

Na [ydhex.com](https://www.ydhex.com/) YDH wymienia ponad 50 linii lotniczych i żeglugowych, z którymi współpracuje, oraz odprawę celną w 12 krajach. [Strona kontaktowa](https://www.ydhex.com/product/59/) podaje biura w Japonii (Tokio, Narita, Osaka, Kansai i Nagoja), Stanach Zjednoczonych (La Puente, Kalifornia), Wielkiej Brytanii (Hounslow) oraz Belgii (Grâce-Hollogne pod Liège).

**Doręczenie ostatniej mili.** YDH odpowiada za odbiór eksportowy, transport lotniczy i odprawę celną, a następnie przekazuje paczkę (`CARR`) lokalnej poczcie lub firmie kurierskiej w twoim kraju na doręczenie pod drzwi, do punktu odbioru lub zwrot. Liczbę prób, bezpieczne miejsca i czas przechowywania ustala lokalny przewoźnik.

**Cło i opłaty importowe.** Transgraniczne zamówienia wysyłane przez YDH podlegają krajowemu podatkowi VAT od importu, cłu i ewentualnym lokalnym opłatom manipulacyjnym, chyba że sprzedawca lub platforma pobrała je przy zakupie. Zobacz [przesyłki zatrzymane w urzędzie celnym](guide:customs).

## Paczka YDH utknęła, milczy lub nie dotarła

Ponieważ umowę z YDH zawiera sprzedawca albo platforma, a nie kupujący, w sprawie opóźnień, korekty adresu czy zwrotu pieniędzy zawsze kontaktuj się najpierw ze sprzedawcą lub otwórz zgłoszenie na platformie.

- **Przerwy w czasie lotu lub przekazania:** kilkudniowe luki są częste w oczekiwaniu na miejsce w samolocie (`HBYP`), w czasie odprawy celnej (`RC`) lub przed pierwszym skanem lokalnego partnera (`DSD` / `D-SCAN DELAY`). Zobacz [dlaczego śledzenie przestaje się aktualizować](guide:tracking-not-updating).
- **Użyj lokalnego numeru po przekazaniu:** gdy YDH pokaże własny numer lokalnego przewoźnika, na jego stronie mogą być dostępne opcje doręczenia nieobecne w trackerze YDH.
- **Doręczona, uszkodzona lub zaginiona:** jeśli widnieje `CC` / dostarczona, a paczki nie ma, sprawdź najpierw z domownikami, sąsiadami, recepcją i lokalnym przewoźnikiem, po czym szybko powiadom sprzedawcę. Zobacz [dostarczona, ale nieodebrana](guide:delivered-not-received), [uszkodzona paczka](guide:damaged-parcel), [zagubiona paczka](guide:lost-parcel) i [zwrócona nadawcy](guide:returned-to-sender).

## Obsługa klienta i kontakty YDH

Oficjalne dane biur opublikowane na [stronie kontaktowej YDH](https://www.ydhex.com/product/59/):

- **Infolinie obsługi klienta w Chinach:** `800-988-1378` / `400-888-1378`.
- **Obsługa klienta w Szanghaju:** `(+86) 21-52277600` / `(+86) 21-52277688` (centrala: 28/F, AFC Grand Hongqiao International, No. 999 Li'an Road, Minhang District, Szanghaj 201100, Chiny).
- **Biuro w Hongkongu:** `+852 2690-1686`, `hk@ydhexpress.com`.
- **Biuro w Japonii (Tokio):** `+81 3-5644-5733`.
- **Biuro w Belgii (Liège):** `+32 470 588 200`.

> Uważaj na niechciane SMS-y i e-maile z linkiem do niewielkiej opłaty kartą za „odprawę celną” albo „aktualizację adresu” paczki z Chin. Prawdziwe należności sprawdzaj na stronie zamówienia na platformie lub w oficjalnym serwisie poczty swojego kraju. Zobacz [fałszywe SMS-y o przesyłce](guide:delivery-scam-texts).

## Śledzenie paczki YDH w Peek

[Peek](/) automatycznie rozpoznaje `YDH` + 10 cyfr + `YQ` lub `AZ` (`YDH4829173640YQ` i `YDH4829173640AZ`, cyfry zmyślone) jako YDH z wysoką pewnością oraz obsługuje wklejone linki `track.ydhex.com/track_query.aspx?track_number=`. Jeśli kiedykolwiek trzeba wybrać YDH ręcznie, stuknij „Zmień” podczas dodawania lub „Zmień przewoźnika” na ekranie paczki (wyszukanie „YDH Express”, „YDH Cross Border” lub „义达跨境” też go znajdzie).

Peek odczytuje bezpośrednio tracker YDH i wyświetla etap („Zarejestrowana”, „W transporcie”, „W doręczeniu”, „Próba doręczenia”, „Zgłoszono problem”, „Dostarczona”), opis skanu — z YDH albo od lokalnego przewoźnika — wraz z miejscem oraz kraj docelowy. Kody YDH takie jak `CARR` i `DSD` wyznaczają etap; Peek ich nie pokazuje. YDH oznacza skany w Chinach czasem szanghajskim, a skany lokalnego przewoźnika jego lokalną strefą czasową; Peek przelicza je na zegar twojego urządzenia. Gdy YDH poda własny numer lokalnego przewoźnika, a Peek go rozpozna, sprawdza, czy ma tę paczkę: na ekranie pojawia się wtedy „Doręcza:” z nazwą przewoźnika oraz oba numery. Nazwisko podpisującego nigdy nie trafia do aplikacji Peek, a Peek pomija referencję klienta i etykietę partnera YDH. Tracker YDH nie podaje przewidywanej daty dostawy, wagi, nazwy usługi, punktu odbioru ani zdjęcia doręczenia.

Gdy masz paczkę otwartą w Peek albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza status nawet co 10 minut; w dniu, gdy YDH pokaże `OD`, co 2 minuty od 6:00 według zegara najnowszego skanu YDH, dopóki tracker YDH odpowiada. Poza tym sprawdza co godzinę. Po 12 godzinach wyłącznie na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika”, albo po dwóch dniach bez zmian, sprawdza co godzinę niezależnie od sposobu śledzenia, a po siedmiu dniach na etapie „Zarejestrowana” — co 6 godzin. Gdy tracker YDH nie odpowiada i Peek korzysta wyłącznie z zewnętrznych usług śledzenia, sprawdza co godzinę, co 3 godziny po dniu ciszy i do co 15 minut w dniu doręczenia, gdy masz paczkę otwartą lub masz włączone powiadomienia.

Gdy najnowszy skan ma cztery dni, a przesyłka nadal widnieje jako „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie”, Peek oznacza ją dniem tego skanu, np. „Brak aktualizacji od pon., 12 paź”. Na stronie paczki pojawia się wtedy napis: „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika YDH.”, wraz z linkiem „Poproś przewoźnika YDH o pomoc” do strony kontaktowej YDH. Po trzech tygodniach bez nowego skanu widnieje komunikat: „Od ponad trzech tygodni nic nowego. Jeśli przesyłka wciąż nie dotarła, zapytaj sprzedawcę. Peek dalej pyta przewoźnika YDH.”

Możesz wybrać powiadomienia: „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek tylko monitoruje śledzenie — nie skontaktuje się z YDH, nie odprawi przesyłki celnie ani nie przekieruje jej u lokalnego partnera.

## Pytania o śledzenie YDH

### Czym jest YDH Express?

YDH (*义达跨境*) to założony w 2005 roku operator transgranicznej logistyki e-commerce z siedzibą w Szanghaju, wysyłający małe paczki z Chin do ponad 200 krajów i przekazujący je lokalnym pocztom lub kurierom do doręczenia.

### Co oznaczają numery śledzenia `YDH...YQ` i `YDH...AZ`?

Oba to 15-znakowe formaty listów przewozowych YDH Express składające się z `YDH`, 10 cyfr oraz końcówki `YQ` lub `AZ` (np. `YDH4829173640YQ` lub `YDH4829173640AZ`, cyfry zmyślone).

### Co oznacza `D-SCAN DELAY` (`DSD`) w śledzeniu YDH?

`D-SCAN DELAY` oznacza, że YDH przekazał paczkę w stronę lokalnego przewoźnika ostatniej mili w kraju docelowym, ale ten partner nie dodał jeszcze pierwszego skanu przyjęcia. Paczka pozostaje w transporcie, czekając na lokalny skan.

### Jak skontaktować się z obsługą klienta YDH?

Najpierw skontaktuj się ze sprzedawcą lub platformą w sprawie zwrotu lub problemu z dostawą. W sprawach przewozowych YDH podaje infolinie w Chinach (`800-988-1378` / `400-888-1378`), Szanghaju (`+86 21-52277600` / `7688`), Hongkongu (`+852 2690-1686`, `hk@ydhexpress.com`), Japonii (`+81 3-5644-5733`) i Belgii (`+32 470 588 200`) na [ydhex.com/product/59/](https://www.ydhex.com/product/59/).

:::sources
- [YDH: Oficjalna strona](https://www.ydhex.com/) – założony w 2005 r., 20+ hubów w Chinach, 200+ krajów/regionów, 50+ linii lotniczych i żeglugowych, odprawa celna w 12 krajach
- [YDH: Contact Us](https://www.ydhex.com/product/59/) – centrala w Szanghaju, infolinie w Chinach (`800-988-1378`, `400-888-1378`, `+86 21-52277600`/`7688`), biura w Hongkongu, Japonii, USA, UK i Belgii (adres pomocy linkowany przez Peek)
- [YDH: Portal śledzenia](https://track.ydhex.com/track_query.aspx) – publiczne sprawdzanie przesyłek YDH
- [Universal Parcel Scraper: ydh](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ydh) – wykrywanie `YDH` + 10 cyfr + `YQ`/`AZ`, reguła linku `track.ydhex.com`, kody etapów (`IR`, `AF`, `DA`, `AA`, `RC`, `CARR`, `DSD`, `PU`, `OD`, `CC`, `DD`), numer lokalnego przewoźnika, strefy czasowe skanów i adres pomocy
:::
