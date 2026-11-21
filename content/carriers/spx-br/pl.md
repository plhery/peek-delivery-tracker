---
title: Śledzenie SPX Express Brazylia: zamówienia Shopee i statusy
description: Śledź paczkę SPX Express z zamówienia Shopee w Brazylii: gdzie znaleźć numer BR, co oznaczają portugalskie statusy, odbiór w Agência Shopee i zwrot pieniędzy.
slug: sledzenie-spx-express-brazylia
published: 2026-11-21
updated: 2026-11-21
---

SPX Express (Shopee Xpress) to własna sieć logistyczna platformy Shopee w **Brazylii**. Odbiera zamówienia od sprzedawców i doręcza je pod wskazany adres albo do punktu partnerskiego *Agência Shopee*. Shopee nazywa tę opcję *Entrega Padrão* (dostawa standardowa) i kieruje kupujących do aplikacji Shopee w Brazylii, w której znajdują się szczegóły zamówienia, przewidywany termin doręczenia oraz obsługa zwrotów pieniędzy. Numer przesyłki sprawdzisz także na stronie [spx.com.br](https://spx.com.br/track) lub wklejając go w pole powyżej.

## Numer przesyłki SPX Express: gdzie go znaleźć i jak wygląda

W brazylijskiej aplikacji Shopee wejdź w `Eu` > `Minhas Compras`, wybierz zamówienie w zakładce `A Caminho` (część artykułów pomocy Shopee używa nazwy `A Receber`) i dotknij `Status de Envio`, aby zobaczyć wszystkie aktualizacje. Shopee dodaje tam numer przesyłki automatycznie, gdy tylko powstanie, razem z szacowaną datą dostawy.

Oto formaty rozpoznawane przez Peek (ze zmyślonymi znakami):

| Format | Co oznacza |
| --- | --- |
| `BR4829105736491` | SPX Express w Brazylii: `BR`, 12 cyfr i cyfra końcowa (15 znaków). |
| `BR482910573649X` | SPX Express w Brazylii: `BR`, 12 cyfr i litera końcowa (15 znaków). |
| `BR482910573649XSPXLM48291057` | SPX Express: ten sam 15-znakowy kod z dopiskiem `SPXLM` i 8 cyframi, spotykany na etykietach marketplace'u. |
| `EA482910576BR` | Dwie litery, dziewięć cyfr i `BR`: numer pocztowy brazylijskiego operatora Correios, a nie SPX. |

Identyfikator zamówienia (`ID do pedido`) nie jest numerem śledzenia, ale obsługa klienta Shopee może o niego poprosić.

Nie każde zamówienie na Shopee w Brazylii jedzie z SPX. Shopee rozróżnia *Entrega Padrão* (Shopee Xpress), *Entrega Correios* (z kodem działającym również na stronie Correios) oraz *Entrega do Vendedor*, gdzie sam sprzedawca dostarcza towar i aktualizuje status. Przy zakupach wysyłanych z zagranicy do Brazylii Shopee wymienia Correios — z lokalnym numerem w aplikacji — albo partnera Anjun Courier; mimo to w śledzeniu SPX pojawiają się osobne wpisy dla części paczek zagranicznych (patrz niżej). Inne układy numerów opisują poradniki [formaty numerów przesyłek](guide:tracking-number-formats) oraz [jak znaleźć numer przesyłki](guide:find-tracking-number).

## Co oznaczają statusy śledzenia SPX Express

SPX publikuje komunikaty po portugalsku (z pojedynczymi wpisami po angielsku); część linii kończy się nazwą miejscowości po dwukropku.

| Komunikat SPX | Co oznacza dla paczki | Peek pokazuje |
| --- | --- | --- |
| `Pedido em preparação` | Sprzedawca przygotowuje zamówienie; SPX nie ma jeszcze paczki. | „Zarejestrowana” |
| `Pedido postado …` | Sprzedawca nadał paczkę w punkcie nadawczym w Brazylii (spx.com.br dopisuje miasto); SPX odbierze ją stamtąd. | „Przyjęta przez przewoźnika” |
| `Seu pedido foi coletado` | Kurier SPX odebrał paczkę od sprzedawcy. | „Przyjęta przez przewoźnika” |
| `Seu pedido chegou ao centro logístico: …` | Przesyłka dotarła do centrum logistycznego SPX w Brazylii. | „W transporcie” |
| `Seu pedido saiu do centro logístico: …` | Wyjechała z centrum logistycznego do kolejnego etapu. | „W transporcie” |
| `O pedido está em separação no centro logístico, novas atualizações em até 1 dia.` | Trwa sortowanie; SPX zapowiada nowe wpisy w ciągu jednego dnia. | „W transporcie” |
| `Pedido em trânsito` | W drodze między centrami. Między brazylijskimi stanami lub odległymi miastami paczka — jak wyjaśnia Shopee — spędza nieraz kilka dni w ciężarówce liniowej, a kolejny status pojawia się dopiero po rozładunku i skanie w centrum dystrybucyjnym docelowego stanu. | „W transporcie” |
| `Pedido em rota de entrega para seu endereço, aguarde pelas próximas horas.` | Wydana kurierowi do doręczenia pod Twój adres w najbliższych godzinach. | „W doręczeniu” |
| `Pedido deve ser retirado na agência: …` | Przesyłka czeka na odbiór przez adresata we wskazanym punkcie *Agência Shopee*. | „Do odbioru” |
| `Houve um imprevisto durante a entrega do seu pedido …`, `Um imprevisto ocorreu …`, `Insucesso na entrega por endereço incorreto…` | Tego dnia nie udało się doręczyć paczki; SPX zapowiada ponowną próbę pod wskazanym adresem. | „Próba doręczenia” |
| `Pedido entregue` | Doręczono. | „Dostarczona” |
| `Pedido será devolvido ao vendedor`, `Pedido será devolvido ao vendedor. Por favor, aguarde.` | Paczka wraca do sprzedawcy; dłuższa wersja prosi jedynie o cierpliwość. | „Zwrócona nadawcy” |
| `Pedido devolvido` | Zwrócona sprzedawcy. | „Zwrócona nadawcy” |
| `Pedido extraviado. Estorno será processado automaticamente.` | Przesyłka uznana za zagubioną; SPX informuje o automatycznym zwrocie pieniędzy. | „Zgłoszono problem” |
| `Pedido descartado` | Po nieudanym doręczeniu paczka została zutylizowana — ani nie trafiła do odbiorcy, ani nie wróciła do sprzedawcy. Skontaktuj się z Shopee. | „Zgłoszono problem” |

Niektóre przesyłki otrzymują wpisy z systemu floty kurierskiej SPX zaczynające się od `Seu pacote`, m.in. `Seu pacote foi recebido pelo centro de triagem` (w sortowni), `Seu pacote foi recebido pelo centro de distribuição` (w lokalnym centrum dystrybucyjnym), `Seu pacote está sendo entregue pelo entregador` (z nazwą hubu na początku: w doręczeniu) oraz `Seu pacote foi entregue` (doręczona). Peek przypisuje im te same etapy, np. „W doręczeniu”.

**Przesyłki z zagranicy do Brazylii.** Wpisy z prefiksem `[China]` pochodzą z kraju nadania, jak `[China]O pedido foi liberado na alfândega de exportação` po odprawie wywozowej. Następnie pojawiają się `Pedido chegou no Brasil` (dotarła do Brazylii), `Pedido está na alfândega de importação` (w brazylijskim urzędzie celnym przywozu), `Pedido em inspeção na alfândega de importação` (kontrola celna) oraz po angielsku `Import Customs Cleared` (zwolniona przez urząd celny). Peek pokazuje przy obu krokach celnych „W urzędzie celnym”, a po `Import Customs Cleared` ponownie „W transporcie”.

Z kolei komunikat `O pacote não pôde ser importado, será retornado ou confiscado pela alfândega` oznacza odmowę przywozu przez brazylijskie służby celne i zwrot lub zajęcie przesyłki; Peek wyświetla „Zgłoszono problem”. W takiej sytuacji złóż wniosek o zwrot środków na Shopee.

## Doręczenie SPX w Brazylii: próby, hasło odbioru i Agência Shopee

Poniższe zasady obowiązują dla dostaw Shopee na terenie **Brazylii**:

**Czas dostawy.** Ani kupujący, ani sprzedawca nie mogą umówić konkretnego dnia ani godziny doręczenia. Shopee podaje od 1 do 25 dni roboczych od odbioru paczki u sprzedawców w Brazylii oraz zazwyczaj od 30 do 50 dni kalendarzowych dla zamówień międzynarodowych. Szacowana data widnieje na karcie zamówienia.

**Próby doręczenia.** Partnerzy logistyczni Shopee podejmują 3 próby doręczenia przed zwrotem do sprzedawcy, a nieudana dostawa kończy się automatycznym zwrotem pieniędzy. Adres można zmienić tylko raz i wyłącznie przed potwierdzeniem płatności; później Shopee zaleca poczekać na wyczerpanie prób i automatyczny zwrot. Zobacz też [nieudana próba doręczenia](guide:missed-delivery).

**Hasło odbioru (*palavra-chave*).** Część zamówień w Brazylii ma losowe hasło, które trzeba podać kurierowi przy drzwiach; paczkę odbierze tylko osoba znająca ten kod. Jest on widoczny na stronie zamówienia oraz w e-mailu i powiadomieniu z Shopee. Gdy nie ma Cię w domu, możesz przekazać go zaufanej osobie, np. sąsiadowi albo portierowi budynku (*porteiro*). Nigdy nie podawaj hasła przed otrzymaniem paczki do rąk i odmów przyjęcia przesyłki zaadresowanej na inne nazwisko lub adres.

**Odbiór w Agência Shopee (`Retire Fácil`).** Tam, gdzie usługa jest dostępna w Brazylii, wybiera się ją przy składaniu zamówienia w `Opção de Envio` > `Ver mais` > `Retire Fácil`; późniejsza zmiana na dostawę do domu nie jest możliwa. Po dotarciu paczki Shopee wysyła kod PIN przez WhatsApp, e-mail lub w aplikacji, gdzie podaje również ostatni dzień odbioru. Przy ladzie pokaż PIN oraz numer przesyłki — paczkę może odebrać w Twoim imieniu każdy, kto ma oba te dane. Czas oczekiwania wynosi 7 dni roboczych, po czym SPX zabiera przesyłkę z powrotem. Gdyby wybrany punkt był chwilowo niedostępny, Shopee może przekierować dostawę na Twój domyślny adres. [Mapa punktów SPX](https://spx.com.br/service-point) pozwala znaleźć agencję po wybraniu `Retirar um pacote`.

## Zakupy z zagranicy do Brazylii: Remessa Conforme i podatki

Brazylijska administracja skarbowa i celna Receita Federal wymienia Shopee wśród platform certyfikowanych w programie *Remessa Conforme* (PRC). W certyfikowanym sklepie cło przywozowe oraz podatek ICMS opłaca się już przy finalizacji zakupu. Receita Federal zaleca sprawdzić, czy podatki widnieją na ekranie podsumowania: jeśli ich tam nie ma, zostaną naliczone po dotarciu towaru do Brazylii bez obniżonej stawki cła.

Stawki podawane przez Receita Federal w Brazylii dla zgłoszeń celnych rejestrowanych od 12 maja 2026 r.:

- **Cło przywozowe (*Imposto de Importação*)** w sklepie certyfikowanym PRC dla przesyłek do osób fizycznych: 0% do wartości celnej 50 USD (towar, transport i ubezpieczenie łącznie); powyżej 50 USD stawka wynosi 60% z odliczeniem równowartości 30 USD od wyliczonej kwoty podatku.
- **ICMS** (brazylijski podatek stanowy): obecnie od 17% do 20% zależnie od stanu w Brazylii, zarówno w sklepach certyfikowanych, jak i pozostałych.

Shopee zaznacza, że nieuregulowany brazylijski numer podatkowy CPF może zablokować zakup zagraniczny, a opłacone podatki importowe nie podlegają zwrotowi. Jeśli brazylijski urząd celny wstrzyma przesyłkę obsługiwaną przez Anjun Courier, Shopee prosi o dowód płatności i ceny zakupu, kopię brazylijskiego dokumentu tożsamości (RG lub CNH) oraz formularz potwierdzenia wartości RCV z Receita Federal. Przy przesyłkach Correios ewentualne formalności sprawdzisz w ich śledzeniu. Receita Federal przypomina, że nie wysyła blankietów płatniczych (*boletos*), nie dzwoni ani nie rozsyła e-maili z żądaniem zapłaty tych należności. Więcej w poradniku [paczka zatrzymana przez urząd celny](guide:customs).

## Brak aktualizacji SPX, opóźnienie lub niedoręczona paczka

**Kilka dni bez nowego skanu.** Shopee wyjaśnia, że w Brazylii to normalne: status zmienia się dopiero po zeskanowaniu paczki w kolejnym centrum, dlatego wielodniowy przewóz ciężarówką między stanami, weekendy i kolejki w hubach zatrzymują widoczną historię. Trasa pozornie oddalająca się od odbiorcy również wynika z siatki połączeń, a przy wydaniu do doręczenia często pojawia się naraz kilka kroków. Zobacz też [dlaczego śledzenie się nie aktualizuje](guide:tracking-not-updating).

**Numer nie działa.** Przesyłka mogła jeszcze nie zostać odebrana od nadawcy albo numer zawiera błąd lub nie trafił jeszcze do systemu. Zapytaj sprzedawcę na czacie Shopee — platforma podkreśla, że nie oznacza to zaginięcia paczki.

**Opóźniona dostawa.** Poczekaj, aż minie przewidywana data doręczenia. Od tego momentu możesz wystąpić o zwrot pieniędzy w aplikacji: `Eu` > `A Caminho` > zamówienie > `Pedido de Reembolso`, powód `Não recebi o pedido`. Sprzedawca ma 2 dni na odpowiedź; w razie braku reakcji zwrot realizuje się automatycznie. Według Shopee większość spóźnionych przesyłek dociera w ciągu 5 dni po planowanym terminie.

**Status doręczono, ale paczki brak.** Sprawdź u sąsiadów, domowników oraz w portierni (*porteiro*), a następnie złóż wniosek o zwrot w aplikacji — przy *Entrega Padrão* masz na to 7 dni od doręczenia. Shopee może zlecić przewoźnikowi postępowanie wyjaśniające (*acareação*): firma może skontaktować się przez WhatsApp lub e-mail albo ponownie przysłać kuriera. Więcej w poradniku [dostarczona, ale nieodebrana](guide:delivered-not-received).

**Zwrot do sprzedawcy.** Po statusie `Pedido será devolvido ao vendedor` paczka wraca do nadawcy. Przy nieudanym doręczeniu Shopee zwraca pieniądze automatycznie; zobacz [zwrócona do nadawcy](guide:returned-to-sender).

## Kontakt z SPX Express: wyłącznie przez Shopee

Shopee informuje, że kupujący nie mogą kontaktować się bezpośrednio z firmą kurierską w sprawie śledzenia ani dostawy — sprawy te prowadzi Shopee w ramach *Garantia Shopee* w Brazylii:

- **Chat Shopee:** w aplikacji otwórz `Eu` > `Central de Ajuda`, przewiń do `Atendimento ao Cliente` i wybierz `Chat Shopee`. Czat działa od poniedziałku do piątku w godz. 8:00–22:00 oraz w soboty i niedziele w godz. 8:00–20:00 czasu Brasília.
- **Formularz zgłoszeniowy:** dostępny całodobowo ze [strony godzin pracy obsługi klienta Shopee](https://help.shopee.com.br/portal/4/article/141507) — przygotuj wcześniej `ID do pedido`. (W stopce `spx.com.br`, przeznaczonej dla kierowców partnerskich i agencji, widnieje ponadto kanał WhatsApp `(11) 2770-1438`, czynny od poniedziałku do soboty z wyjątkiem świąt w godz. 8:00–20:00 czasu Brasília.)
- **Niewłaściwe zachowanie kuriera** (np. żądanie dodatkowej opłaty): zgłoś przez `Chat Shopee`.
- **Spór nierozwiązany w Brazylii:** Shopee Brasil uczestniczy w rządowej platformie reklamacyjnej [consumidor.gov.br](https://www.consumidor.gov.br/pages/principal/), gdzie zarejestrowane firmy zobowiązują się odpowiedzieć w ciągu 10 dni (wymagane konto `gov.br` na poziomie srebrnym lub złotym).

> Shopee kontaktuje się z użytkownikami wyłącznie oficjalnymi kanałami — przez aplikację, zweryfikowane profile społecznościowe lub e-maile kończące się na `shopee.com` (publiczna wyszukiwarka SPX działa pod adresem `spx.com.br`) — a koszt wysyłki zawsze opłaca się w aplikacji. Jak rozpoznać oszustwo, opisuje poradnik [fałszywe SMS-y o paczce](guide:delivery-scam-texts).

## Śledzenie przesyłek SPX Express w Peek

[Peek](/) od razu rozpoznaje SPX Express Brazil po kodzie `BR` z 12 cyframi i końcową cyfrą lub literą (także z rozszerzeniem `SPXLM…`), wyświetlając „Wykryty przewoźnik” oraz przycisk „Zmień”. Również wklejony link z `spx.com.br` (`/track?` lub `/detail/` z numerem `BR`) natychmiast przypisuje przesyłkę do SPX, nawet gdy pochodzi z wiadomości SMS bez `https://`. Peek odpytuje SPX wyłącznie po numerach `BR`, a nie po identyfikatorze zamówienia Shopee.

Peek pobiera te same dane śledzenia co `spx.com.br` i pokazuje bieżący status, komunikaty SPX (po portugalsku, czasem po angielsku), miejscowość zapisaną przy skanach w centrach logistycznych, hubach, portach lub agencjach odbioru oraz dokładną godzinę doręczenia przeliczoną na strefę czasową Twojego urządzenia — na `peektracker.com` oraz w aplikacjach na iPhone'a i Androida pod historią widnieje dopisek „Godziny w Twojej strefie czasowej: …”. Odsłania także etapy ukryte na publicznej stronie SPX, takie jak `Seu pedido chegou ao último centro logístico` (dotarcie do ostatniego centrum logistycznego) czy nieudany odbiór u sprzedawcy („Zarejestrowana”); jeśli nieudany odbiór nastąpi po `Pedido postado …`, paczka nadal czeka w punkcie nadania („Przyjęta przez przewoźnika”), chyba że komunikat wskazuje, iż nadawca nie przygotował zamówienia („Zarejestrowana”). SPX nie udostępnia przewidywanej daty doręczenia, wagi ani nazwy firmy partnerskiej. Pod udostępnionym linkiem skan doręczenia pokazuje jedynie `Delivered` bez imienia i nazwiska odbiorcy. Hasło odbioru, kod PIN do *Agência Shopee* oraz zwroty pieniędzy pozostają w aplikacji Shopee.

Dopóki SPX nie zna jeszcze numeru, Peek wyświetla „Oczekiwanie na przewoźnika SPX Express Brazil” i ponawia próby. Jeśli po dwóch dniach SPX nadal nie rozpoznaje kodu i żadne inne źródło nie odpowiedziało, Peek pokazuje komunikat „Po dwóch dniach przewoźnik nadal nie zna tego numeru. Sprawdź go, wybierz innego przewoźnika lub usuń przesyłkę.”, po czym sprawdza status raz dziennie, a po tygodniu od dodania — raz w tygodniu. Gdy przesyłka jest już w drodze i przez cztery dni nie pojawi się żaden nowy skan, Peek udostępnia link „Poproś przewoźnika SPX Express Brazil o pomoc”, prowadzący do strony godzin pracy obsługi klienta Shopee.

Gdy masz otwartą kartę przesyłki lub w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza aktualizacje nawet co 10 minut — a od godz. 6:00 czasu lokalnego w dniu zeskanowania paczki jako wydanej do doręczenia nawet co 2 minuty, dopóki odpowiada własny system śledzenia SPX (przez zewnętrzne serwisy śledzenia same: co godzinę, co 3 godziny po spokojnym dniu i do co 15 minut w dniu doręczenia przy otwartej paczce lub włączonych alertach) —; w pozostałych przypadkach, a także po dwóch dniach bez zmian (lub po 12 godzinach na etapie „Zarejestrowana” bądź „Przyjęta przez przewoźnika”), sprawdza co godzinę. Powiadomienia możesz ustawić na „Każdy skan”, „Ważne etapy” albo „Tylko doręczenie”. Peek nie może skontaktować się w Twoim imieniu ani z SPX, ani z Shopee.

## Najczęstsze pytania o śledzenie SPX Express Brazylia

### Czy SPX Express należy do Shopee?

Tak — Shopee określa SPX Express jako własną oficjalną logistykę w Brazylii. Za serwisem `spx.com.br` stoi spółka SPX Express Brasil Ltda., dysponująca ponad 100 hubami logistycznymi i 11 centrami dystrybucyjnymi w całym kraju.

### Jak śledzić paczkę SPX Express?

W brazylijskiej aplikacji Shopee (`Eu` > `Minhas Compras` > zamówienie > `Status de Envio`), wklejając kod `BR` w Peek albo korzystając z wyszukiwarki na `spx.com.br`.

### Co oznacza status Pedido em rota de entrega para seu endereço?

Paczka wyjechała w Brazylii do doręczenia pod Twój adres i według SPX powinna dotrzeć w najbliższych godzinach. Jeśli zamówienie wymaga hasła odbioru (*palavra-chave*), przygotuj je dla kuriera.

### Ile trwa dostawa SPX Express w Brazylii?

Shopee podaje od 1 do 25 dni roboczych od odbioru przesyłki u krajowych sprzedawców w Brazylii oraz zazwyczaj od 30 do 50 dni kalendarzowych przy zamówieniach z zagranicy. Przewidywany termin dla Twojego zakupu znajduje się na stronie zamówienia.

### Czy można skontaktować się bezpośrednio z SPX Express?

Shopee informuje, że nie w sprawach śledzenia ani doręczenia: obsługę prowadzi `Chat Shopee` w aplikacji (od poniedziałku do piątku 8:00–22:00, w weekendy 8:00–20:00 czasu Brasília) oraz całodobowy formularz zgłoszeniowy.

:::sources
- [SPX Express: Strona główna](https://spx.com.br/br) – zintegrowana logistyka Shopee w Brazylii, ponad 100 hubów i 11 centrów dystrybucyjnych, SPX Express Brasil Ltda. oraz WhatsApp dla partnerów i agencji
- [SPX Express: Śledzenie](https://spx.com.br/track) – publiczna wyszukiwarka przesyłek SPX w Brazylii
- [SPX Express: Sobre Nós](https://spx.com.br/br/help-center/about-us.html) – śledzenie w aplikacji Shopee i zasięg ogólnokrajowy
- [SPX Express: Encontre uma Agência Shopee](https://spx.com.br/service-point) – mapa punktów i opcja Retirar um pacote
- [Shopee: O que é a SPX Express?](https://help.shopee.com.br/portal/4/article/197188) – własna oficjalna logistyka Shopee w Brazylii
- [Shopee: O que é Shopee Xpress (SPX)?](https://help.shopee.com.br/portal/4/article/76163) – odbiór, centra dystrybucyjne i partnerzy logistyczni
- [Shopee: Posso entrar em contato com os parceiros logísticos da Shopee?](https://help.shopee.com.br/portal/4/article/76264) – rodzaje dostaw, brak bezpośredniego kontaktu z przewoźnikiem i 3 próby doręczenia
- [Shopee: Como faço para verificar o status do meu pedido?](https://help.shopee.com.br/portal/4/article/76323) – kody przesyłek Correios
- [Shopee: Quais são as opções de envio disponíveis na Shopee?](https://help.shopee.com.br/portal/4/article/76335) – automatyczne udostępnianie numeru śledzenia
- [Shopee: Como eu acho a data prevista de entrega para o meu pedido?](https://help.shopee.com.br/portal/4/article/81887) – szacowana data doręczenia i zakładka Status de Envio
- [Shopee: Como encontro o ID do pedido da minha compra?](https://help.shopee.com.br/portal/4/article/76194) – identyfikator zamówienia ID do pedido
- [Shopee: O que é logística do vendedor?](https://help.shopee.com.br/portal/4/article/132039) – dostawa realizowana bezpośrednio przez sprzedawcę
- [Shopee: Como minha encomenda do exterior será entregue quando chegar ao Brasil?](https://help.shopee.com.br/portal/4/article/76363) – doręczanie przesyłek zagranicznych przez Correios
- [Shopee: Por que meu pedido está parado na alfândega do Brasil?](https://help.shopee.com.br/portal/4/article/153683) – Correios lub Anjun Courier, dokumenty RG/CNH i formularz RCV
- [Shopee: Quando receberei meu pedido?](https://help.shopee.com.br/portal/4/article/76349) – 1–25 dni roboczych w kraju i 30–50 dni kalendarzowych z zagranicy
- [Shopee: Posso agendar a data e hora de entrega do meu pedido?](https://help.shopee.com.br/portal/4/article/76333) – brak wyboru terminu, próby doręczenia i automatyczny zwrot pieniędzy
- [Shopee: Posso alterar meu endereço de entrega depois que meu pedido for feito?](https://help.shopee.com.br/portal/4/article/76279) – jednorazowa zmiana adresu przed potwierdzeniem płatności
- [Shopee: O que é a palavra-chave no meu pedido?](https://help.shopee.com.br/portal/4/article/127204) – działanie hasła odbioru przy doręczeniu
- [Shopee: O que fazer quando não estiver em casa para informar a palavra-chave?](https://help.shopee.com.br/portal/4/article/127206) – przekazanie hasła zaufanej osobie
- [Shopee: O que fazer se minha palavra-chave não funcionar corretamente?](https://help.shopee.com.br/portal/4/article/127205) – zakaz podawania hasła przed odbiorem i odmowa przyjęcia cudzej paczki
- [Shopee: Retirada de pacotes na Agência Shopee](https://help.shopee.com.br/portal/4/article/164642) – wybór Retire Fácil, powiadomienia, PIN i termin odbioru
- [Shopee: Sobre a Agência Shopee](https://help.shopee.com.br/portal/4/article/164639) – odbiór z kodem PIN i numerem śledzenia, 7 dni roboczych na odbiór i przekierowanie
- [Shopee: Meu pedido está sem atualização no rastreamento](https://help.shopee.com.br/portal/4/article/223973) – wielodniowy przewóz ciężarówką między stanami i dostawa do 5 dni po terminie
- [Shopee: Por que meu pedido está parado ou mudou de rota?](https://help.shopee.com.br/portal/4/article/159363) – zmiany trasy między hubami
- [Shopee: Por que meu código de rastreio não está funcionando?](https://help.shopee.com.br/portal/4/article/76369) – przyczyny braku danych dla nowego numeru
- [Shopee: O que devo fazer se não receber meu pedido até a data prevista de entrega?](https://help.shopee.com.br/portal/4/article/76321) – wniosek o zwrot i 2 dni na odpowiedź sprzedawcy
- [Shopee: O que devo fazer se não receber um pedido, mas o status está como Entregue?](https://help.shopee.com.br/portal/4/article/76207) – weryfikacja na miejscu i wyjaśnienie acareação
- [Shopee: Como faço para solicitar devolução/reembolso?](https://help.shopee.com.br/portal/4/article/76204) – 7 dni od doręczenia w Entrega Padrão
- [Shopee: Como entro em contato com o Atendimento ao Cliente da Shopee?](https://help.shopee.com.br/portal/4/article/78354) – ścieżka do Chat Shopee w aplikacji
- [Shopee: Qual horário do Atendimento ao Cliente da Shopee?](https://help.shopee.com.br/portal/4/article/141507) – godziny pracy wg czasu Brasília i formularz zgłoszeń
- [Shopee: O entregador do meu pedido teve uma má conduta](https://help.shopee.com.br/portal/4/article/224511) – zgłoszenie zachowania kuriera na czacie
- [Shopee: Como faço para detectar um golpe?](https://help.shopee.com.br/portal/4/article/76330) – oficjalne kanały shopee.com i opłacanie wysyłki wyłącznie w aplikacji
- [Shopee: Por que não consigo concluir minha compra internacional?](https://help.shopee.com.br/portal/4/article/141561) – blokada przy nieprawidłowym numerze CPF
- [Shopee: Como solicitar devolução/reembolso ou realizar o cancelamento da minha compra internacional?](https://help.shopee.com.br/portal/4/article/134867) – zwroty zagraniczne i brak zwrotu podatków importowych
- [Receita Federal: Tributação](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/topicos/tributacao) – stawki PRC od 12 maja 2026 r., ICMS 17–20% i ostrzeżenie przed fałszywymi boletos
- [Receita Federal: Em que momento vou pagar os impostos?](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/preciso-pagar-impostos-nas-compras-internacionais/em-que-momento-pagarei-o-imposto) – pobór podatków przy finalizacji zakupu
- [Receita Federal: Empresas certificadas no Programa Remessa Conforme](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/empresas-certificadas-no-programa-remessa-conforme-prc) – Shopee na liście certyfikowanych platform PRC
- [Consumidor.gov.br: Início](https://www.consumidor.gov.br/pages/principal/) – odpowiedź w 10 dni oraz konto gov.br na poziomie srebrnym lub złotym
- [Consumidor.gov.br: Empresas participantes](https://www.consumidor.gov.br/pages/principal/empresas-participantes) – udział Shopee Brasil
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – rozpoznawanie numerów BR i linków spx.com.br, statusy i flota SPX, ukryte etapy oraz prywatność
:::
