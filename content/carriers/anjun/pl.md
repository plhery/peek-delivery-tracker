---
title: Śledzenie Anjun Logistics: statusy Chiny–Brazylia i SAC
description: Śledź przesyłkę Anjun Logistics lub Anjun Express: kody AJ, statusy z Chin i Brazylii, odprawa Remessa Conforme i aktywne kontakty SAC.
slug: sledzenie-anjun-logistics
published: 2026-11-24
updated: 2026-11-24
---

Anjun Logistics (działająca w Brazylii jako Anjun Express) to założony w 2011 roku w Chinach przewoźnik e-commerce, który wszedł na rynek brazylijski w 2019 roku, a w 2025 roku rozszerzył doręczenia na całą Brazylię. Firma odpowiada za odbiór przesyłek w Chinach, eksport lotniczy, odprawę celną w Brazylii i dostawę na ostatniej mili — własną siecią kurierską i punktami odbioru albo przekazując paczki po odprawie do [Correios](carrier:correios-br). Przesyłki międzynarodowe sprawdzisz w [globalnym śledzeniu Anjun](https://anjuntrack.com/trajectory), krajowe przesyłki brazylijskie na stronie [Anjun Express Brazylia](https://anjunexpress.com/trackPackage), lub wklejając numer powyżej.

## Numer śledzenia Anjun: format i gdzie go znaleźć

Numer przesyłki znajdziesz w powiadomieniu o wysyłce ze sklepu internetowego lub platformy zakupowej (w tym dla zamówień z chińskich serwisów wysyłanych do Brazylii). Własne kody Anjun mają 17 znaków: `AJ`, 6-cyfrową datę (`RRMMDD`) i 9 cyfr.

| Format (wymyślony) | Co to jest |
| --- | --- |
| `AJ261015482917364` | Kod Anjun Logistics / Anjun Express: `AJ` + 6-cyfrowa data + 9 cyfr. |

Anjun prowadzi dwa portale śledzenia: [anjuntrack.com](https://anjuntrack.com/trajectory) obsługuje przesyłki transgraniczne Chiny–Brazylia, a [anjunexpress.com](https://anjunexpress.com/trackPackage) dotyczy ostatniej mili i paczek krajowych w Brazylii. Jeśli zamówienie ma 15-znakowy kod `BR`, sprawdź [SPX Express Brazil](carrier:spx-br); 15-cyfrowy numer zaczynający się od `8880` należy do [J&T Express Brazil](carrier:j-and-t-br); a 13-znakowy kod pocztowy kończący się na `BR` należy do [Correios](carrier:correios-br). Inne prefiksy opisuje poradnik [formaty numerów przesyłek](guide:tracking-number-formats).

## Co oznaczają statusy Anjun Logistics

Przesyłki transgraniczne Anjun zaczynają zwykle od wpisów po angielsku w Chinach i Hongkongu, a po dotarciu do Brazylii przechodzą na komunikaty po portugalsku.

| Anjun podaje | Co to oznacza dla ciebie | Peek pokazuje |
| --- | --- | --- |
| `Objeto postado` | Elektroniczna zapowiedź przesyłki lub nadanie przed przyjęciem do magazynu. | „Zarejestrowana” |
| `The goods have arrived at Anjun`; `Objeto coletado` jako pierwszy skan paczki wysłanej w Brazylii | Magazyn Anjun w Chinach przyjął paczkę lub Anjun odebrał ją od nadawcy w Brazylii. | „Przyjęta przez przewoźnika” |
| `The goods have been issued`, `Departed Facility in SHENZHEN`, `Arrived at Sort Facility HONG KONG INTERNATIONAL AIRPORT`, `Departed Facility in HONG KONG INTERNATIONAL AIRPORT`, `Arrived at Sort Facility Destination Airport` | Obsługa eksportowa, wylot z Shenzhen lub Hongkongu i przylot na lotnisko docelowe w Brazylii. | „W transporcie” |
| `Recebimento de cargas no armazém courier` | Przyjęcie w terminalu kurierskim w Brazylii przed odprawą celną. | „W transporcie” |
| `Entrada no armazém alfândegado de TFK`, `Informações enviadas para análise da autoridade aduaneira/órgãos anuentes`, `Análise concluída - importação autorizada` | Przyjęcie do magazynu celnego, przesłanie deklaracji celnej i zakończenie kontroli. | „W urzędzie celnym” |
| `Pacote Liberado Aguadando a Coleta de Last Mile`, `Saída do Centro Internacional`, `Coletado por Last Mile`, `Objeto coletado`, `Objeto recebido pelos Correios do Brasil` | Zwolnienie z cła i przekazanie do sieci ostatniej mili Anjun lub do Correios na doręczenie krajowe. | „W transporcie” |
| `O pacote chegou ao centro de trânsito`, `O pacote saiu do centro de trânsito`, `Objeto chegou ao ponto de entrega` | Transport przez brazylijskie centra tranzytowe i przyjazd do lokalnej bazy doręczeń (jeszcze nie w doręczeniu). | „W transporcie” |
| `Objeto saiu para entrega ao destinatário` | Lokalny kurier wyruszył dziś doręczyć przesyłkę. | „W doręczeniu” |
| `A entrega falhou` (np. `Cliente ausente` lub `Endereço fora da rota`) | Doręczenie nie powiodło się (nieobecność odbiorcy lub adres poza trasą). | „Próba doręczenia” |
| `A entrega falhou: Pacote não pertence à Base` | Paczka trafiła do błędnej bazy lokalnej i jest przekierowywana zamiast próby doręczenia pod twój adres. | „W transporcie” |
| `O pacote está a caminho do centro de trânsito para devolução.` | Powrót do centrum tranzytowego po nieudanych doręczeniach; paczka może jeszcze wyjść ponownie do doręczenia. | „Zgłoszono problem” |
| `O pacote foi assinado`, `Objeto entregue ao destinatário` | Anjun zarejestrował podpisanie i doręczenie przesyłki. | „Dostarczona” |

Wpis `Objeto chegou ao ponto de entrega` oznacza dotarcie do lokalnej bazy Anjun — nie do punktu odbioru osobistego — więc na kuriera czekaj dopiero przy statusie `Objeto saiu para entrega ao destinatário`.

## Sieć i doręczenia Anjun Express w Brazylii

Na [brazylijskiej stronie Anjun Express](https://anjunexpress.com/home) firma wymienia odbiory pierwszej mili, [doręczenia ostatniej mili](https://anjunexpress.com/lastMile), logistykę B2B, magazynowanie, wysyłki bezpośrednie i Super Express; podstrona ostatniej mili wspomina o magazynie celnym o powierzchni 25 000 m² przy lotnisku GRU w São Paulo. Strona główna podaje ponad 1 000 punktów odbioru, ponad 350 pojazdów i ponad 2 000 pracowników ([strona partnerskich punktów](https://anjunexpress.com/collectionPoint) wymienia ponad 500 punktów).

Anjun Express nie publikuje na ogólnodostępnych stronach stałej liczby prób doręczenia, gwarantowanego czasu tranzytu ani czasu przechowywania paczek. Dokładne terminy dla zakupu online sprawdź na stronie zamówienia w sklepie lub serwisie sprzedażowym.

## Odprawa celna i Remessa Conforme w Brazylii

Ponieważ większość przesyłek Anjun przylatuje do Brazylii z Chin, podlegają one odprawie celnej w ramach [Programa Remessa Conforme (PRC) urzędu Receita Federal](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/programa-remessa-conforme-o-que-e-como-funciona):

- **Certyfikowany sklep lub platforma:** podatki pobierane są przy kasie. Dla przesyłek indywidualnych do 50 USD (towar, fracht i ubezpieczenie) federalne cło wynosi 0 %, plus stanowy ICMS 17 %–20 %. Powyżej 50 USD cło wynosi 60 % ze zniżką 30 USD od kwoty podatku, plus 17 %–20 % ICMS.
- **Sklep niecertyfikowany:** 60 % federalnego cła bez zniżki (nawet do 50 USD) plus 17 %–20 % ICMS, naliczane przy wjeździe przesyłki do Brazylii przed jej zwolnieniem.

Gdy przesyłka ma status `Entrada no armazém alfândegado de TFK` lub `Informações enviadas para análise da autoridade aduaneira/órgãos anuentes`, urząd Receita Federal lub organy kontrolne sprawdzają zgłoszenie. Jeśli wymagane są dokumenty albo opłata, sprawdzaj wiadomości w zamówieniu i kontakt celny Anjun zamiast płacić z przypadkowych linków. Zobacz [przesyłki zatrzymane przez urząd celny](guide:customs).

## Paczka Anjun stoi w miejscu, nieudane doręczenie lub brak przesyłki

W przypadku zakupów na platformach sprzedażowych za zwroty pieniędzy, ponowne wysyłki i spory celne odpowiada sprzedawca lub platforma.

**Brak skanów między Chinami a Brazylią.** Eksport lotniczy, kolejki lotniskowe i przyjęcie do magazynu celnego często powodują kilkudniowe przerwy między `Departed Facility in HONG KONG INTERNATIONAL AIRPORT` a `Recebimento de cargas no armazém courier`. Zobacz [dlaczego śledzenie przestaje się aktualizować](guide:tracking-not-updating).

**Błędna baza lokalna.** Komunikat `A entrega falhou: Pacote não pertence à Base` brzmi niepokojąco, lecz oznacza jedynie trafienie paczki do niewłaściwej sortowni lokalnej i konieczność jej wewnętrznego przekazania przed właściwą próbą doręczenia.

**Doręczona, ale nieotrzymana.** Zapytaj najpierw domowników, sąsiadów oraz *portarię* (recepcję budynku), a następnie zgłoś sprawę platformie i obsłudze SAC Anjun. Zobacz [doręczona, ale nieotrzymana](guide:delivered-not-received).

**Uszkodzona, zaginiona lub zwracana.** Zachowaj zdjęcia uszkodzonego opakowania i szybko skontaktuj się ze sprzedawcą; zobacz [uszkodzona paczka](guide:damaged-parcel), [zaginiona paczka](guide:lost-parcel) i [zwrot do nadawcy](guide:returned-to-sender).

## Obsługa klienta Anjun Express i nowy adres e-mail

Na [anjunexpress.com/home](https://anjunexpress.com/home) Anjun Express informuje, że dawny adres `sac@anjunexpress.com.br` został wyłączony 12 sierpnia 2026 roku, i podaje aktywne kanały w Brazylii:

- **Krajowy telefon SAC:** `(11) 5026-7008`, od poniedziałku do piątku w godzinach 08:00–17:30 (z wyjątkiem świąt).
- **E-mail SAC:** `sac@anjun.com.br`.
- **Czat online:** dostępny na [anjunexpress.com](https://anjunexpress.com/home).
- **Sprawy przesyłek międzynarodowych / celnych:** `atendimento.aduaneiro@anjun.com.br`.
- **Rzecznik celny (*Ouvidoria*):** `ouvidoria.aduaneira@anjun.com.br`.
- **Mediacja konsumencka:** w razie nierozwiązanego sporu z firmą uczestniczącą w programie konsumenci w Brazylii ze srebrnym lub złotym kontem gov.br mogą skorzystać z [Consumidor.gov.br](https://www.consumidor.gov.br/pages/conteudo/publico/1), gdzie firmy odpowiadają w ciągu 10 dni.

> Uważaj na fałszywe wiadomości SMS lub WhatsApp z żądaniem opłaty celnej za rzekomo zablokowaną przesyłkę Anjun. Zanim zapłacisz, zweryfikuj każde wezwanie na koncie zamówienia lub bezpośrednio pod adresem `atendimento.aduaneiro@anjun.com.br`. Zobacz [fałszywe SMS-y o paczkach](guide:delivery-scam-texts).

## Śledzenie paczki Anjun Logistics w Peek

[Peek](/) automatycznie rozpoznaje Anjun Logistics po każdym 17-znakowym kodzie `AJ` (z poprawną datą `RRMMDD`) oraz po wklejonych linkach `anjuntrack.com` (`/trackQuery?t=` lub `/trajectory?orderNum=`) i `anjunexpress.com/trackPackage?tracking=` zawierających kod `AJ`. Jeśli potrzebujesz wybrać przewoźnika ręcznie, kliknij „Zmień” podczas dodawania lub „Zmień przewoźnika” w widoku paczki i wybierz Anjun Logistics (Peek obsługuje ponad 4 500 przewoźników).

Peek sprawdza najpierw `anjuntrack.com` pod kątem historii międzynarodowej, a gdy brak tam jeszcze skanów, automatycznie odpytuje też `anjunexpress.com`, dzięki czemu brazylijskie paczki krajowe pojawiają się bez dodatkowych kroków. Peek pokazuje etap („Zarejestrowana”, „Przyjęta przez przewoźnika”, „W transporcie”, „W urzędzie celnym”, „W doręczeniu”, „Próba doręczenia”, „Zgłoszono problem”, „Dostarczona”), opisy skanów po angielsku i portugalsku, ewentualny powód nieudanego doręczenia (np. `Cliente ausente`), miasto i stan w Brazylii (ulice są usuwane), kraj docelowy oraz czasy lokalne (czas chiński w Chinach, brazylijski w Brazylii, przeliczone na strefę urządzenia). Peek pomija pasek postępu strony, adres uliczny odbiorcy, współrzędne GPS, imię kierowcy, telefony/e-maile wsparcia w notatkach, podpisy i zdjęcia; system Anjun nie udostępnia szacowanej daty dostawy, wagi ani lokalnego numeru przekazania.

Gdy masz paczkę otwartą w Peek lub w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza aktualizacje co maksymalnie 10 minut; w dniu pojawienia się wpisu `Objeto saiu para entrega ao destinatário` — co 2 minuty od 6:00 czasu lokalnego, o ile odpowiada własne śledzenie Anjun. W pozostałym czasie sprawdza co godzinę. Po 12 godzinach z samym statusem „Zarejestrowana” lub „Przyjęta przez przewoźnika” albo po dwóch dniach bez nowych wpisów sprawdza co godzinę niezależnie od sposobu śledzenia, a po siedmiu dniach na etapie „Zarejestrowana” — co 6 godzin, chyba że paczka jest otwarta albo oczekiwana dziś. Gdy śledzenie Anjun nie odpowiada i Peek korzysta wyłącznie z zewnętrznych usług śledzących, sprawdza co godzinę, po jednym spokojnym dniu co 3 godziny, a w dniu doręczenia do co 15 minut, gdy paczka jest otwarta lub powiadomienia są włączone.

Gdy najnowszy skan ma cztery dni, a paczka nadal ma status „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie”, Peek oznacza ją datą tego skanu (na przykład „Brak aktualizacji od pon., 12 paź”). Strona przesyłki wyświetla wtedy komunikat „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika Anjun Logistics.” Po trzech tygodniach bez nowego skanu pojawia się „Od ponad trzech tygodni nic nowego. Jeśli przesyłka wciąż nie dotarła, zapytaj sprzedawcę. Peek dalej pyta przewoźnika Anjun Logistics.”

Możesz wybrać powiadomienia dla „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek jedynie śledzi statusy i nie może odprawić przesyłki ani skontaktować się z Anjun Express w twoim imieniu.

## Pytania o śledzenie Anjun Logistics

### Dlaczego w śledzeniu Anjun są wpisy po angielsku i portugalsku?

Przesyłki transgraniczne zaczynają trasę w chińskich magazynach Anjun i centrach eksportowych w Hongkongu, które zapisują skany po angielsku, a po przylocie do Brazylii przechodzą na język portugalski na czas odprawy celnej i lokalnego doręczenia.

### Co oznacza `A entrega falhou: Pacote não pertence à Base`?

Oznacza to, że paczka omyłkowo trafiła do niewłaściwej bazy lokalnej Anjun, a nie że kurier nie zastał cię w domu. Anjun przekierowuje ją do właściwej bazy, dlatego Peek utrzymuje etap „W transporcie”.

### Czy adres `sac@anjunexpress.com.br` nadal działa?

Nie. Anjun Express wyłączył `sac@anjunexpress.com.br` 12 sierpnia 2026 roku. Używaj `sac@anjun.com.br`, dzwoń pod `(11) 5026-7008` (pon.–pt., 08:00–17:30) lub pisz w sprawach celnych na `atendimento.aduaneiro@anjun.com.br`.

### Czy Anjun może przekazać moją paczkę do Correios?

Tak. Część przesyłek międzynarodowych po odprawie celnej dostaje status `Objeto recebido pelos Correios do Brasil`, co oznacza, że krajowe doręczenie końcowe wykonuje Correios, a system Anjun odnotowuje przekazanie.

:::sources
- [Anjun Express: Strona główna](https://anjunexpress.com/home) – usługi w Brazylii, skala sieci, aktywny telefon/e-mail SAC, kontakty celne/Ouvidoria i komunikat o wyłączeniu `sac@anjunexpress.com.br` (12.08.2026)
- [Anjun Express: O nas](https://anjunexpress.com/aboutUs) – założenie w 2011 r., wejście do Brazylii w 2019 r., zasięg ogólnokrajowy w 2025 r.
- [Anjun Express: Śledzenie przesyłek](https://anjunexpress.com/trackPackage) – brazylijski portal śledzenia
- [AnjunTrack: Śledzenie międzynarodowe](https://anjuntrack.com/trajectory) – portal transgraniczny Chiny–Brazylia
- [Anjun Express: Ostatnia mila](https://anjunexpress.com/lastMile) – doręczenia ostatniej mili i magazyn celny GRU
- [Anjun Express: Punkty odbioru](https://anjunexpress.com/collectionPoint) – sieć partnerskich punktów odbioru
- [Receita Federal: Programa Remessa Conforme](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/programa-remessa-conforme-o-que-e-como-funciona) – brazylijskie zasady cła i ICMS
- [Consumidor.gov.br](https://www.consumidor.gov.br/pages/conteudo/publico/1) – publiczna platforma mediacyjna (odpowiedź w 10 dni, srebrne/złote konto gov.br)
- [Universal Parcel Scraper: anjun](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/anjun) – wykrywanie `AJ`, odpytywanie obu portali, mapowanie statusów, pomijane pola i częstotliwość
:::
