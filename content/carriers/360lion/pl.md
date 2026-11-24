---
title: Śledzenie 360Lion: kody WSH i AR, statusy i kontakt
description: Śledź paczkę 360Lion Express z Chin do Ameryki Łacińskiej lub USA: numery WSH i AR, kody skanów, lokalne doręczenie, przerwy i pomoc.
slug: sledzenie-360lion
published: 2026-11-24
updated: 2026-11-24
---

360Lion (*360Lion Express*, po chińsku *纬狮物流 / 纬狮物联网*) to chiński operator transgranicznej logistyki e-commerce założony w 2015 roku z siedzibą w Shenzhen oraz oddziałami i centrami operacyjnymi w Szanghaju, Kantonie, Dongguan, Yiwu i Hongkongu. Specjalizuje się w dedykowanych liniach lotniczych dla małych paczek, spedycji FBA/FBM oraz magazynach zagranicznych z Chin do Ameryki Łacińskiej i Stanów Zjednoczonych (Meksyk, Chile, Peru, Brazylia, Kolumbia, Ekwador, Argentyna, Urugwaj, Boliwia, Paragwaj, Salwador, Kostaryka, Gwatemala, Wenezuela, Panama i USA) dla sprzedawców na platformach Mercado Libre, Amazon, AliExpress, Shopee, SHEIN, Temu, TikTok Shop, Walmart i Falabella. Przesyłki sprawdzisz w [portalu śledzenia 360Lion](https://toms.360lion.com/track) lub na [360lion.com/en/](https://www.360lion.com/en/) (do 50 numerów naraz), albo wklejając numer w pole powyżej.

## Numery śledzenia 360Lion: formaty WSH i AR

Numer przesyłki znajdziesz na ekranie zamówienia na platformie sprzedażowej lub w e-mailu z potwierdzeniem wysyłki. Paczki 360Lion występują zwykle w dwóch formatach:

| Format (zmyślony) | Co to jest |
| --- | --- |
| `WSHMX4829173640YQ` | List przewozowy linii dedykowanej 360Lion: `WSH`, dwuliterowy kod kraju docelowego (np. `MX` dla Meksyku), 10 cyfr i `YQ`. |
| `AR4829173640` | `AR` i 10 cyfr (stosowane dla paczek kierowanych do Argentyny). Format współdzielony z [Colis Privé](carrier:colis-prive), [Aramex Australia](carrier:aramex-au) i [Aramex New Zealand](carrier:aramex-nz). |

Jeśli zamówienie do Brazylii lub Meksyku jedzie pod kodem innego przewoźnika — np. `AJ` dla [Anjun Logistics](carrier:anjun), `8880` dla [J&T Express Brazil](carrier:j-and-t-br) albo `JMX` dla [J&T Express Mexico](carrier:j-and-t-mx) — sprawdź [formaty numerów przesyłek](guide:tracking-number-formats).

## Co oznaczają statusy śledzenia 360Lion

360Lion grupuje przesyłki w ogólne stany (`Processing`, `Pre-alert`, `In transit.`, `Returned`, `Delivered`) i dodaje kody skanów (`WSO*` przy nadaniu w Chinach, `WSD*` w kraju docelowym). Gdy paczka trafi do lokalnego kuriera ostatniej mili, tracker może wyświetlać opisy tego przewoźnika.

| 360Lion pokazuje (kod / grupa) | Co to oznacza dla ciebie | Peek pokazuje |
| --- | --- | --- |
| `Processing` (`PRECESSING`) | Sprzedawca utworzył zlecenie wysyłki w 360Lion przed rozpoczęciem skanów liniowych. | „Zarejestrowana” |
| Etap `HUB` | Przyjęta do sortowni początkowej 360Lion w Chinach. | „Przyjęta przez przewoźnika” |
| `departed from airport` (`WSO04`), `arrived at airport` (`WSD00`), `In transit.` (`IN_TRANSIT`) | Wyleciała z Chin (z kodem lotniska, np. PVG) i wylądowała na lotnisku docelowym. | „W transporcie” |
| Etap `CUSTOMS` | Przechodzi odprawę celną eksportową lub importową w kraju docelowym. | „W urzędzie celnym” |
| `handover to lastmile` (`WSD02`), `Arrived at sorting center` (`WSD051`), `package on the way to delivery office` (`WSD050`) | Przekazana lokalnemu partnerowi ostatniej mili, posortowana i wysłana do lokalnej placówki doręczeniowej. | „W transporcie” |
| `Returned` (`RETURNED_BACK`) | W drodze powrotnej do nadawcy lub magazynu wyjściowego. | „Zwrócona nadawcy” |
| `Delivered` (`DELIVERED`) | Doręczona odbiorcy przez lokalnego partnera ostatniej mili. | „Dostarczona” |

Stan `Pre-alert` (`WARNING` / *预警*) w 360Lion może oznaczać zarówno wcześniejsze powiadomienie, jak i ostrzeżenie, dlatego Peek ustala etap na podstawie szczegółowych wpisów skanów, zamiast traktować samo `Pre-alert` jako awarię.

## Jak działa transgraniczna dostawa 360Lion

Na stronach [360lion.com/en/](https://www.360lion.com/en/) i [360lion.com](https://www.360lion.com/) 360Lion opisuje się jako transgraniczny przewoźnik liniowy oraz lotniczy agent sprzedaży generalnej (GSSA), obsługujący regularne loty czarterowe z Chin do Ameryki Łacińskiej przy wsparciu magazynów zagranicznych w Meksyku, Chile, Brazylii, Kolumbii i USA.

**Doręczenie ostatniej mili.** Po przylocie na lotnisko docelowe (`WSD00`) i odprawie importowej 360Lion przekazuje paczkę (`WSD02`) lokalnemu kurierowi w twoim kraju, który ją doręcza.

**Zasady dla odbiorców i cło.** Ponieważ 360Lion obsługuje nadawców biznesowych zamiast klientów detalicznych, strona nie podaje limitów prób doręczenia, okresów przechowywania w punktach odbioru ani gwarancji czasu dostawy dla małych paczek ([strona FAQ i warunków](https://www.360lion.com/?m=home&c=Lists&a=index&tid=82) opisuje zasady dla sprzedawców, m.in. fracht lotniczy SEND, towary zakazane i 30-dniowe okno reklamacji sprzedawcy). VAT i cło importowe podlegają przepisom kraju docelowego. Zobacz [przesyłki zatrzymane w urzędzie celnym](guide:customs).

## Paczka 360Lion utknęła, milczy lub nie dotarła

Ponieważ 360Lion ma umowę ze sklepem lub platformą sprzedażową, a nie z kupującym, w razie opóźnień, błędów adresu czy zwrotów zawsze kontaktuj się najpierw ze sprzedawcą lub platformą.

**Brak aktualizacji między Chinami a krajem docelowym.** Długodystansowe loty z Chin do Ameryki Łacińskiej i kolejki celne często zostawiają kilkudniowe przerwy między `departed from airport` (`WSO04`) a `arrived at airport` (`WSD00`). Zobacz [dlaczego śledzenie przestaje się aktualizować](guide:tracking-not-updating).

**Lokalne przekazanie bez drugiego numeru.** Peek odczytuje komunikaty przewoźnika ostatniej mili przez tracker 360Lion pod numerem `WSH` lub `AR` i nie pokazuje oddzielnego numeru lokalnego. Jeśli doręczenie nie powiedzie się i potrzebujesz kontaktu lub referencji lokalnego partnera, zapytaj sprzedawcę albo napisz do działu zapytań o paczki 360Lion.

**Doręczona, uszkodzona lub zagubiona.** Najpierw sprawdź z domownikami, sąsiadami i recepcją budynku, jeśli widnieje `Delivered`; w razie zagubienia lub uszkodzenia zbierz dokumentację i otwórz spór u sprzedawcy lub na platformie przed końcem ochrony kupujących. Zobacz [dostarczona, ale nieodebrana](guide:delivered-not-received), [uszkodzona paczka](guide:damaged-parcel), [zagubiona paczka](guide:lost-parcel) i [zwrócona nadawcy](guide:returned-to-sender).

## Obsługa klienta i kontakty 360Lion

Kontakty opublikowane na [stronie kontaktowej 360Lion](https://www.360lion.com/?m=home&c=Lists&a=index&tid=79) i na [360lion.com/en/](https://www.360lion.com/en/):

- **E-mail do zapytań o przesyłki (*查件业务*):** `customerservice@360lion.com`.
- **E-mail ogólny / biznesowy:** `info@360lion.com`.
- **Telefon centrali w Shenzhen:** `+86 755 2360 1325` (Szanghaj `+86 21 6142 1360`, Kanton `+86 20 8653 4133`).
- **Adres siedziby:** 7/F, Block A, Star River World, No. 1 Yabao Road, Bantian Street, Longgang District, Shenzhen, Guangdong, Chiny.

> Uważaj na fałszywe SMS-y lub wiadomości WhatsApp z pilnym linkiem do opłaty za „zwolnienie” przesyłki międzynarodowej z cła. Prawdziwe należności celne zawsze weryfikuj najpierw na stronie zamówienia na platformie albo w oficjalnym portalu pocztowym lub celnym swojego kraju. Zobacz [fałszywe SMS-y o przesyłce](guide:delivery-scam-texts).

## Śledzenie paczki 360Lion w Peek

[Peek](/) automatycznie sprawdza 360Lion po wpisaniu kodu `WSH...YQ` (np. `WSHMX4829173640YQ`, cyfry zmyślone) — gdzie 360Lion jest jedynym kandydatem — lub kodu `AR` + 10 cyfr (np. `AR4829173640`), dla którego Peek odpytuje 360Lion razem z [Colis Privé](carrier:colis-prive), [Aramex Australia](carrier:aramex-au) oraz [Aramex New Zealand](carrier:aramex-nz) i po potwierdzeniu wyświetla „360Lion ma tę przesyłkę.”. Wklejenie linku `toms.360lion.com/track?trackNumber=` wybiera 360Lion bezpośrednio; w innym razie stuknij „Zmień” podczas dodawania lub „Zmień przewoźnika” na ekranie paczki i wybierz 360Lion (wyszukanie „360Lion Express”, „WSH” lub „纬狮物流” też go znajdzie).

Peek odczytuje bezpośrednio tracker 360Lion i wyświetla etap („Zarejestrowana”, „Przyjęta przez przewoźnika”, „W transporcie”, „W urzędzie celnym”, „Zwrócona nadawcy” lub „Dostarczona”), treść każdego skanu (opisy kuriera ostatniej mili, gdy 360Lion je przekazuje, w przeciwnym razie angielski tekst 360Lion), lokalizację jako nazwę kraju lub trzyliterowy kod lotniska, kraj docelowy i czas doręczenia. Każdy skan ma strefę czasową miejsca zdarzenia, np. `+8` w Chinach i `-3` w Argentynie, i wyświetla się w czasie twojego urządzenia. Peek pomija pasek postępu ze strony, kraj pochodzenia, licznik dni w transporcie oraz potwierdzenie doręczenia; tracker 360Lion nie podaje przewidywanej daty dostawy, nazwy usługi ani wagi.

Gdy masz paczkę otwartą w Peek albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza status nawet co 10 minut, dopóki tracker 360Lion odpowiada. Poza tym sprawdza co godzinę. Po 12 godzinach wyłącznie na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika”, albo po dwóch dniach bez zmian, sprawdza co godzinę niezależnie od sposobu śledzenia, a po siedmiu dniach na etapie „Zarejestrowana” — co 6 godzin. Gdy tracker 360Lion nie odpowiada i Peek korzysta wyłącznie z zewnętrznych usług śledzenia, sprawdza co godzinę, co 3 godziny po dniu ciszy i do co 15 minut w dniu doręczenia, gdy masz paczkę otwartą lub masz włączone powiadomienia.

Gdy najnowszy skan ma cztery dni, a przesyłka nadal widnieje jako „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie”, Peek oznacza ją dniem tego skanu, np. „Brak aktualizacji od pon., 12 paź”. Na stronie paczki pojawia się wtedy napis: „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika 360Lion.”, wraz z linkiem „Poproś przewoźnika 360Lion o pomoc” do strony kontaktowej 360Lion. Po trzech tygodniach bez nowego skanu widnieje komunikat: „Od ponad trzech tygodni nic nowego. Jeśli przesyłka wciąż nie dotarła, zapytaj sprzedawcę. Peek dalej pyta przewoźnika 360Lion.”

Możesz wybrać powiadomienia: „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek tylko monitoruje aktualizacje śledzenia — nie odprawi paczki celnie ani nie skontaktuje się za ciebie z 360Lion czy lokalnym kurierem.

## Pytania o śledzenie 360Lion

### Czym jest 360Lion Express?

360Lion (założony w 2015 roku w Shenzhen w Chinach) to transgraniczny przewoźnik e-commerce skupiony na lotniczych liniach małych paczek z Chin do Ameryki Łacińskiej i USA, z którego korzystają sprzedawcy z Mercado Libre, Amazon, AliExpress, Shopee, SHEIN i Temu.

### Co oznaczają numery śledzenia `WSH...YQ` i `AR...`?

`WSH` + dwie litery kraju + 10 cyfr + `YQ` (np. `WSHMX4829173640YQ`, cyfry zmyślone) to format dedykowanej linii transgranicznej 360Lion, natomiast `AR` + 10 cyfr (`AR4829173640`) stosuje się na paczkach 360Lion do Argentyny.

### Kto lokalnie doręcza paczki 360Lion?

360Lion przewozi paczki lotniczo z Chin na lotnisko w twoim kraju i przekazuje je (`handover to lastmile`) lokalnej poczcie lub firmie kurierskiej do doręczenia pod adres. Wpisy lokalnego partnera pojawiają się w strumieniu śledzenia 360Lion.

### Jak skontaktować się z 360Lion w sprawie zaginionej paczki?

Najpierw napisz do sprzedawcy lub na platformę w celu uruchomienia ochrony kupujących i zwrotu. Możesz też napisać do biura zapytań 360Lion na `customerservice@360lion.com` lub zadzwonić do biura w Shenzhen pod `+86 755 2360 1325`.

:::sources
- [360Lion Express: English Home](https://www.360lion.com/en/) – założony w 2015 r., oddziały Shenzhen/Szanghaj/Kanton/Yiwu/Dongguan/Hongkong, tracker 50 numerów, partnerzy marketplace i adres siedziby
- [360Lion: Chinese Home](https://www.360lion.com/) – dedykowane trasy małych paczek do 15 krajów Ameryki Łacińskiej i USA, loty czarterowe i magazyny zagraniczne
- [360Lion: Contact Us](https://www.360lion.com/?m=home&c=Lists&a=index&tid=79) – `customerservice@360lion.com`, `info@360lion.com`, telefony i adresy biur Shenzhen/Kanton/Szanghaj (adres pomocy linkowany przez Peek)
- [360Lion: About Us](https://www.360lion.com/?m=home&c=Lists&a=index&tid=78) – profil firmy
- [360Lion: Dedicated Small Parcels](https://www.360lion.com/?m=home&c=Lists&a=index&tid=90) – transgraniczna usługa małych paczek B2C
- [360Lion: FAQ & Terms](https://www.360lion.com/?m=home&c=Lists&a=index&tid=82) – warunki transportu dla sprzedawców, towary zakazane i 30-dniowe reklamacje sprzedawców
- [360Lion: Tracking Portal](https://toms.360lion.com/track) – publiczny portal śledzenia
- [Universal Parcel Scraper: 360lion](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/360lion) – wykrywanie `WSH` i `AR`, reguły linków `toms.360lion.com`, kody `WSO`/`WSD`, jawne strefy czasowe, pomijane pola i adres pomocy
:::
