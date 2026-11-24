---
title: Śledzenie Memphis Courier: kody M-MIA, cło ANVISA i pomoc
description: Śledź import Memphis Courier z USA do Brazylii: kody M-MIA, statusy lotniska GRU i odprawy ANVISA, zasady CPF oraz oficjalne kontakty obsługi.
slug: sledzenie-memphis-courier
published: 2026-11-24
updated: 2026-11-24
---

Memphis Courier to w 100 % brazylijska firma logistyczna z ponad 25-letnią historią, obsługująca ekspresowe i formalne przesyłki eksportowe oraz importowe. Ze swojego terminalu kurierskiego na międzynarodowym lotnisku Guarulhos (GRU) oraz biura w São Paulo zapewnia transport lotniczy, odprawę celną w Receita Federal, odprawę regulacyjną w ANVISA (w tym import medycznej marihuany w ramach RDC 660 i RDC 327 dla leków produkowanych w Stanach Zjednoczonych, Europie, Kolumbii i Urugwaju) oraz doręczenie na terenie całej Brazylii.

Jej system śledzenia prowadzi przesyłkę na lotnisko Guarulhos, przez kontrolę Receita Federal i przegląd ANVISA, aż do doręczenia.

## Format numeru śledzenia Memphis Courier

Numery śledzenia Memphis Courier mają charakterystyczny 11-znakowy układ alfanumeryczny:

- **`M` + 7 cyfr + `MIA`** (łącznie 11 znaków, np. `M4829173MIA`) — wzorzec rozpoznawany przez Peek jako Memphis Courier.

Peek przyjmuje kody Memphis Courier o długości od 6 do 30 liter i cyfr, usuwa spacje i zamienia litery na wielkie. Portal śledzenia Memphis Courier (`clients.memphiscourier.com.br`) sprawdza kod bez logowania na konto klienta. Dla formatu `M` + 7 cyfr + `MIA` Peek odpytuje wyłącznie Memphis Courier i po odpowiedzi wyświetla „Memphis Courier ma tę przesyłkę.” Inne formaty spotykane przy imporcie do Brazylii opisuje [przewodnik po formatach numerów przesyłek](guide:tracking-number-formats).

## Statusy śledzenia Memphis Courier

Usługa śledzenia Memphis Courier zwraca 3-literowe kody skanów z portugalskimi opisami oraz kolumną lokalizacji (stan lub kraj). Pasek postępu na stronie obejmuje etapy Criado (`FLT`), cło (`BRP`), Liberado i Entregue; skany odpowiadające dwóm ostatnim etapom (`CLA` i `DLV`) są znane wyłącznie pod tymi nazwami kroków.

| Kod | Opis Memphis Courier | Co oznacza |
| --- | --- | --- |
| `FLT` | Seu pedido foi criado com sucesso | Zlecenie przesyłki zostało zarejestrowane w systemie Memphis Courier przed wylotem. |
| `CHK` | Entrada registrada no Centro de Distribuição | Przyjęcie zarejestrowane w centrum dystrybucyjnym Memphis Courier. |
| `ENO` | Encomenda em trânsito | W transporcie w sieci Memphis Courier. |
| `TRA` | Encomenda em transferência | W transferze między obiektami operacyjnymi. |
| `DEP` | A encomenda saiu da origem e está a caminho do aeroporto de destino (Guarulhos - SP) | Wyleciała z hubu nadania w stronę międzynarodowego lotniska Guarulhos (GRU). |
| `ARR` | Seu pedido chegou ao aeroporto de destino (Guarulhos - SP) | Wylądowała na lotnisku Guarulhos w São Paulo. |
| `AAZ` | Pedido recebido pela GRU Airport | Przyjęta przez terminal cargo lotniska GRU przed formalnym zgłoszeniem celnym. |
| `BRP` | Processo de desembaraço iniciado | Rozpoczęto brazylijską odprawę celną. |
| `HOC` | Encomenda sendo inspecionada pela Alfândega | W trakcie kontroli prowadzonej przez Receita Federal (brazylijski urząd celny). |
| `ANV` | Em inspeção pela ANVISA | W trakcie kontroli regulacyjnej prowadzonej przez brazylijską agencję zdrowia ANVISA podczas odprawy. |
| `CLA` | Liberado (pasek etapów) | Urząd celny i wymagane organy regulacyjne zwolniły przesyłkę do krajowego doręczenia. |
| `DLV` | Entregue (pasek etapów) | Doręczona pod adres odbiorcy w Brazylii. |

## Cło, weryfikacja CPF i odprawa ANVISA

Każdy indywidualny import ekspresowy do Brazylii wymaga numeru CPF (*Cadastro de Pessoas Físicas*) odbiorcy na potrzeby odprawy w Receita Federal. Jak wskazuje Memphis Courier na [stronie usług dodatkowych](https://memphiscourier.com.br/servicos-adicionais/), wiele zagranicznych sklepów internetowych nie ma pola CPF w koszyku; w takich sytuacjach obsługa klienta Memphis Courier kontaktuje się z odbiorcą w celu potwierdzenia CPF, aby nie blokować odprawy.

W przypadku medycznej marihuany i innych leków sprowadzanych przez Memphis Courier ze Stanów Zjednoczonych, Europy, Kolumbii lub Urugwaju przesyłka może przejść na lotnisku Guarulhos zarówno kontrolę Receita Federal (`HOC`), jak i inspekcję ANVISA (`ANV`), zanim pojawi się `CLA` (zwolniona). Jeśli przesyłka pozostaje na statusie `BRP`, `HOC` lub `ANV` przez kilka dni roboczych bez zmiany, sprawdź pocztę e-mail i WhatsApp: Memphis Courier kontaktuje się z klientami e-mailem, przez WhatsApp, czat lub telefon i może prosić o potwierdzenie CPF albo dokumenty. Więcej o odprawie międzynarodowej przeczytasz w [przewodniku celnym](guide:customs) oraz w [gdy śledzenie przestaje się aktualizować](guide:tracking-not-updating).

Po zwolnieniu przesyłki przez urząd celny (`CLA`) krajowa dystrybucja obejmuje wszystkie stany Brazylii. Jeśli osobne zamówienie do Brazylii doręcza inny przewoźnik, porównaj śledzenie z [Anjun Express](carrier:anjun), [Jadlog](carrier:jadlog) lub [J&T Express Brazil](carrier:j-and-t-br).

## Śledzenie Memphis Courier w Peek

Wklej kod `M...MIA` do Peek, a Peek automatycznie przypisze go do Memphis Courier po uzyskaniu odpowiedzi. Jeśli musisz wybrać lub zmienić przewoźnika ręcznie, stuknij **Zmień** podczas dodawania przesyłki albo **Zmień przewoźnika** na ekranie śledzenia.

Peek odpytuje bezpośrednio publiczną usługę śledzenia Memphis Courier przez otwartoźródłowy [adapter Memphis Courier w universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/memphis-courier). Pobiera kod skanu, portugalski opis, lokalizację oraz łączną wagę w kilogramach, gdy jest podana. Memphis Courier podaje godziny skanów jako czas lokalny, dodając przesunięcie UTC tylko wtedy, gdy godzina się nim kończy. Peek zachowuje to przesunięcie; w przeciwnym razie przypisuje skan do strefy czasowej jego miejsca (czas nowojorski dla `Estados Unidos`, czas São Paulo dla `SP` lub `Brasil`), skan bez miejsca do strefy sąsiedniego skanu, a w ostateczności do czasu São Paulo, i wyświetla wszystko według zegara Twojego urządzenia. Dane widoczne w portalu klienta — uwagi wewnętrzne, liczba paczek, kody pocztowe, miejsce przeznaczenia i załączone dokumenty celne — pozostają na stronie Memphis Courier.

Peek grupuje 12 kodów skanów Memphis Courier w przejrzyste etapy: **Zarejestrowana** dla `FLT`, **W transporcie** od `CHK` do `AAZ` oraz ponownie po zwolnieniu celnym przy `CLA` (zestaw kodów Memphis Courier przechodzi bezpośrednio od utworzenia zlecenia do tranzytu w centrum dystrybucyjnym bez osobnego kodu **Przyjęta przez przewoźnika**), **W urzędzie celnym** dla `BRP`, `HOC` i `ANV` oraz **Dostarczona** po pojawieniu się `DLV`.

Gdy masz przesyłkę otwartą w Peek albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza status nawet co 10 minut, dopóki tracker Memphis Courier odpowiada. W pozostałym czasie Peek sprawdza co godzinę. Ponieważ tracker Memphis Courier nie posiada etapu wyjścia do doręczenia, Peek nigdy nie przełącza się na sprawdzanie co 2 minuty w dniu dostawy. Po 12 godzinach wyłącznie na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika” albo po dwóch dniach bez zmian sprawdza co godzinę niezależnie od sposobu śledzenia, a po siedmiu dniach na „Zarejestrowana” — co 6 godzin. Gdy tracker Memphis Courier nie odpowiada i Peek śledzi przesyłkę wyłącznie przez usługi śledzące, sprawdza co godzinę, co 3 godziny po spokojnym dniu i nawet co 15 minut w dniu doręczenia, dopóki masz ją otwartą lub włączone powiadomienia.

Jeśli najnowszy skan ma cztery dni, a przesyłka nadal widnieje jako „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie”, Peek oznacza ją dniem tego skanu, np. „Brak aktualizacji od pon., 12 paź”. Na stronie przesyłki pojawia się wtedy komunikat „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika Memphis Courier.” wraz z linkiem „Poproś przewoźnika Memphis Courier o pomoc” do strony Fale Conosco firmy Memphis Courier. Po trzech tygodniach bez nowego skanu pojawia się informacja „Od ponad trzech tygodni nic nowego. Jeśli przesyłka wciąż nie dotarła, zapytaj sprzedawcę. Peek dalej pyta przewoźnika Memphis Courier.”

Wybierz **Każdy skan**, **Ważne etapy** lub **Tylko doręczenie** w ustawieniach powiadomień Peek, aby zdecydować, jak często chcesz dostawać alerty.

## Kontakt z Memphis Courier

Memphis Courier publikuje na swojej stronie oficjalne kanały obsługi i wyraźnie zastrzega w oficjalnym komunikacie, że nie traktuje zewnętrznych serwisów skargowych, takich jak Reclame Aqui, jako oficjalnego kanału wsparcia ani protokołu.

- **Oficjalna strona kontaktowa:** [Fale Conosco](https://memphiscourier.com.br/fale-conosco/) (formularz kontaktowy)
- **Telefon / obsługa klienta:** +55 11 94264-7550 (podany dla terminalu kurierskiego GRU i biura w São Paulo)
- **E-mail:** `atendimento@memphiscourier.com.br`, `atendimento1@memphiscourier.com.br` oraz `info@memphiscourier.com.br`
- **Terminal kurierski Guarulhos:** Aeroporto Internacional de Guarulhos — Terminal Courier, CEP 07190-100, São Paulo, Brazylia
- **Biuro w São Paulo:** Av. Marques de São Vicente, 230, cj 1216 — Barra Funda, CEP 01139-000, São Paulo, Brazylia ([Quem Somos](https://memphiscourier.com.br/quem-somos/))

Przy kontakcie z obsługą przygotuj numer śledzenia `M...MIA` oraz numer CPF odbiorcy, aby zespół mógł od razu odnaleźć Twoją sprawę celną.

## Najczęstsze pytania

### Co oznacza `ANV` („Em inspeção pela ANVISA”) w Memphis Courier?

`ANV` oznacza, że brazylijska agencja regulacji zdrowotnej (ANVISA) kontroluje przesyłkę w ramach odprawy importowej. Memphis Courier odprawia import medycznej marihuany (RDC 660 / RDC 327) przez ANVISA; paczka pozostaje w urzędzie celnym do pojawienia się `CLA` (zwolniona).

### Dlaczego Memphis Courier skontaktował się ze mną w sprawie numeru CPF?

Receita Federal wymaga numeru CPF odbiorcy przy każdym osobistym imporcie ekspresowym do Brazylii. Gdy zagraniczny sklep nie pobiera numeru CPF przy zamówieniu, obsługa klienta Memphis Courier kontaktuje się z Tobą w celu jego potwierdzenia przed dalszą odprawą.

### Dlaczego między `DEP` a `ARR` nie ma żadnych skanów?

`DEP` oznacza wylot z miejsca nadania w stronę lotniska Guarulhos, a `ARR` przylot na miejsce. Lista kodów Memphis Courier nie obejmuje osobnego skanu podczas samego lotu, więc kolejnym zdarzeniem jest `ARR` albo `AAZ` w terminalu cargo.

### Czy Memphis Courier odpowiada na zgłoszenia w serwisie Reclame Aqui?

Nie. Oficjalna informacja na stronie Memphis Courier stanowi, że reklamacje, pytania i wyjaśnienia są obsługiwane wyłącznie przez oficjalne kanały podane na [memphiscourier.com.br](https://memphiscourier.com.br/) (telefon, e-mail i formularz kontaktowy), aby zapewnić rozliczalność i poufność danych.

:::sources
- [Memphis Courier](https://memphiscourier.com.br/)
- [Memphis Courier — Quem Somos](https://memphiscourier.com.br/quem-somos/)
- [Memphis Courier — Serviços Adicionais](https://memphiscourier.com.br/servicos-adicionais/)
- [Memphis Courier — Fale Conosco](https://memphiscourier.com.br/fale-conosco/)
- [adapter Memphis Courier w universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/memphis-courier)
:::
