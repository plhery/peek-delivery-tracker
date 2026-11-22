---
title: Śledzenie GOFO Italy: numery GFIT, statusy i reklamacje
description: Śledź przesyłkę GOFO we Włoszech: format numerów GFIT i CIIT, znaczenie włoskich statusów, procedura reklamacyjna AGCOM i śledzenie w Peek.
slug: sledzenie-gofo-italy
published: 2026-11-21
updated: 2026-11-21
---

GOFO Italy to włoska sieć przewoźnika GOFO, obecnego w kilku krajach, doręczająca paczki na terenie Włoch. Lista przewoźników Peek zna ją również pod nazwą CIRRO Parcel Italy. Jej numery śledzenia zaczynają się od `GFIT` lub `CIIT`: sprawdź przesyłkę na włoskiej stronie śledzenia GOFO w serwisie gofo.com albo wklej numer w pole powyżej. Nie masz jeszcze numeru? Zobacz [jak znaleźć numer przesyłki](guide:find-tracking-number).

## Numer śledzenia GOFO Italy: jak wygląda

Oto kształty, które Peek przypisuje do GOFO Italy, niezależnie od wielkości liter:

| Kształt | Zmyślony przykład |
| --- | --- |
| `GFIT` i 13 cyfr | `GFIT4829105736284` |
| `GFIT` i 14 cyfr (ostatnia jest cyfrą kontrolną) | `GFIT48291057362848` |
| `CIIT` i 13 cyfr | `CIIT4829105736284` |
| `CIIT` i 14 cyfr | `CIIT48291057362841` |

Pozostałe sieci GOFO stosują ten sam schemat z kodem kraju po `GF` lub `CI`. Numery `GFFR` i `CIFR` należą do [GOFO France](carrier:gofo-fr), `GFNL` do GOFO Holandia, a `GFES` do GOFO Hiszpania. Numery `GFUS` z 14 cyframi, `GF` z 13 albo `CR` z 12 należą do [GOFO Express](carrier:gofo) w USA (odrębna sieć), a `GFCA` z 14 cyframi do GOFO Canada. Numer zaczynający się od `GFIT`, który ma 12 lub 15 cyfr, albo 14-cyfrowy `GFIT` z błędną cyfrą kontrolną, nie zostanie przypisany do GOFO Italy: sprawdź, czy nie ma literówki. Więcej w [formatach numerów przesyłek](guide:tracking-number-formats).

## Statusy śledzenia GOFO Italy: co oznaczają po włosku

Peek odczytuje włoskie śledzenie GOFO, więc skany pojawiają się w oryginalnym włoskim brzmieniu niezależnie od języka aplikacji. Najczęstsze wpisy w typowej kolejności:

| Co podaje GOFO | Co to oznacza dla ciebie |
| --- | --- |
| `Il pacco è in fase di preparazione da parte del mittente e ci sarà presto consegnato` | Nadawca przygotowuje paczkę i wkrótce przekaże ją GOFO. |
| `Registrato presso il centro di trasferimento` | Zeskanowana w centrum przeładunkowym GOFO. |
| `Il pacco ha lasciato il centro di smistamento` | Opuściła sortownię GOFO. |
| `Arrivato alla struttura di destinazione regionale GOFO` | Dotarła do regionalnego oddziału GOFO dla twojego obszaru. |
| `Preparazione alla consegna` | Przygotowywana w magazynie przed wyjazdem kuriera. |
| `Il corriere è fuori per la consegna` | W doręczeniu z kurierem. |
| `Consegna fallita, tipo:Destinatario assente` | Niedoręczona: nikogo nie było pod adresem. |
| `Consegna fallita, tipo:L'indirizzo è errato` | Niedoręczona: błędny adres. |
| `Il pacco è stato restituito al centro di smistamento` | Wróciła do sortowni GOFO po nieudanej próbie doręczenia. |
| `Consegnato` | Dostarczona. |

Dwa komunikaty często mylą odbiorców. Dopóki po pierwszym wpisie nie pojawi się kolejny skan, GOFO nie ma jeszcze paczki i Peek pokazuje „Zarejestrowana”. Z kolei `Il pacco è stato restituito al centro di smistamento` nie oznacza zwrotu do sklepu: paczka wróciła do sortowni GOFO i Peek pokazuje „Zgłoszono problem”. Doręczenie wyświetla się jako samo `Consegnato` w każdym języku: własny opis GOFO może zawierać dodatkowe dane o odbiorcy, które Peek pomija.

Śledzenie GOFO przypisuje też paczki do ogólnych kategorii. `Avviso` to problem oznaczony przez GOFO, który Peek wyświetla jako „Zgłoszono problem”; samo w sobie nie oznacza próby doręczenia. `Restituito` to przesyłka wracająca do nadawcy („Zwrócona nadawcy”).

Jeśli [YunExpress](carrier:yunexpress) przekazał paczkę do GOFO Italy, śledzenie YunExpress może pokazywać skany GOFO po angielsku: `Preparing for delivery` w magazynie przed wyjazdem oraz `Package departed from sorting center` obok włoskiego `Il pacco ha lasciato il centro di smistamento`.

## Paczka GOFO opóźniona, niedoręczona, zaginiona lub uszkodzona

**Brak aktualizacji.** Peek nadal pyta GOFO. Jeśli przed wyjazdem do doręczenia miną cztery dni bez nowego skanu, Peek to sygnalizuje („Brak aktualizacji od …” z dniem ostatniego skanu) i wyświetla link do strony pomocy GOFO Italy („Poproś przewoźnika GOFO Italy o pomoc”). Zobacz też [dlaczego śledzenie nie aktualizuje się](guide:tracking-not-updating).

**Nieudane doręczenie.** Po każdym wpisie `Consegna fallita` z kodem alertu GOFO — niezależnie czy po `tipo:` widnieje `Destinatario assente`, `L'indirizzo è errato`, czy inny powód — Peek wyświetla „Próba doręczenia”. Dalsze kroki zależą od GOFO: sprawdź [nieudane doręczenie](guide:missed-delivery).

**Opóźnienie.** Zgodnie z unijnymi przepisami konsumenckimi sklep musi dostarczyć zamówienie w ciągu 30 dni, chyba że uzgodniono inny termin. Jeśli tego nie zrobi, wyznacz dodatkowy rozsądny termin; po jego bezskutecznym upływie możesz odstąpić od umowy i odzyskać pieniądze.

**Status `Consegnato`, ale paczki nie ma.** Za doręczenie odpowiada sklep: to on kontaktuje się z przewoźnikiem i musi udowodnić doręczenie, a jeśli nie potrafi, ma obowiązek ponownie wysłać towar lub zwrócić pieniądze. Co sprawdzić najpierw w okolicy domu: [dostarczona, ale nieotrzymana](guide:delivered-not-received).

**Uszkodzenie.** Sklep ponosi odpowiedzialność za uszkodzenia od nadania aż do odbioru paczki, dlatego w miarę możliwości sprawdź opakowanie przy kurierze. Jeśli karton jest wyraźnie uszkodzony, włoski regulator pocztowy AGCOM wskazuje, że możesz odmówić przyjęcia lub odebrać przesyłkę z zastrzeżeniem. Więcej w [uszkodzona przesyłka](guide:damaged-parcel).

## Jak złożyć reklamację na doręczenie GOFO Italy

Zacznij od sklepu, który nadał paczkę, bo to on odpowiada za dostawę. W przypadku reklamacji wobec samego przewoźnika włoski urząd regulacji komunikacji AGCOM określa procedurę dla operatorów pocztowych, przy czym przesyłki do 31,5 kg zaliczają się do usług pocztowych:

1. Wyślij operatorowi pisemną reklamację na formularzu z jego strony lub poproś o formularz e-mailem. Operator ma 45 dni na odpowiedź.
2. Jeśli odpowiedź cię nie satysfakcjonuje albo nie nadejdzie w ciągu 45 dni, wystąp do operatora o postępowanie pojednawcze (conciliazione), które kończy się w ciągu 60 dni.
3. Jeśli to również nie pomoże, masz 90 dni na zwrócenie się do AGCOM o rozstrzygnięcie sporu za pomocą Formulario CP. AGCOM wydaje wiążącą obie strony decyzję w ciągu 150 dni.

AGCOM może nakazać zwrot kosztów lub przewidziane przepisami odszkodowanie, ale nie odszkodowanie za poniesione szkody – w tej sprawie oraz na każdym etapie możesz wystąpić do sądu. Jeśli operator nie odpowie na reklamację ani na wniosek o pojednanie, zgłoś to przez Portale delle segnalazioni AGCOM. Centrum kontaktowe AGCOM działa od poniedziałku do piątku w godzinach 10:00–14:00 pod numerem `800.185060` (z telefonów stacjonarnych) lub `081.750750` (z telefonów komórkowych lub z zagranicy).

## Obsługa klienta GOFO Italy

Strona pomocy GOFO Italy znajduje się na jej włoskim serwisie gofo.com, a Peek linkuje do niej jako „Poproś przewoźnika GOFO Italy o pomoc”, gdy przed wyjazdem do doręczenia miną cztery dni bez nowego skanu. Spółka GOFO Italia s.r.l. ma siedzibę pod adresem Via Juan Manuel Fangio 11, 20045 Lainate (MI). Przygotuj numer `GFIT` lub `CIIT` oraz numer zamówienia. AGCOM przypomina też, że każdy operator pocztowy musi zapewniać użytkownikom bezpłatne kanały komunikacji.

## Śledzenie przesyłki GOFO Italy w Peek

[Peek](/) samodzielnie rozpoznaje numery `GFIT` i `CIIT` (weryfikując cyfrę kontrolną przy 14-cyfrowych numerach `GFIT`). Możesz też wkleić link z włoskiej strony śledzenia GOFO (na gofo.com lub gofoexpress.it, z parametrem `id=` i numerem).

Dopóki masz przesyłkę otwartą na ekranie albo w dniu doręczenia przy włączonych powiadomieniach, a śledzenie GOFO odpowiada, Peek sprawdza ją nawet co 10 minut, a od godz. 6:00 czasu włoskiego w dniu, gdy GOFO zeskanuje ją w doręczeniu, nawet co 2 minuty; w pozostałym czasie co godzinę. Przez usługi śledzenia sprawdza co godzinę (po dniu bez nowości co 3 godziny), a w dniu doręczenia nawet co 15 minut, dopóki masz ją otwartą lub włączone powiadomienia. Po dwóch dniach bez nowości (po 12 godzinach na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika”) Peek sprawdza przesyłkę co godzinę.

Peek pokazuje status, historię z miejscem podanym przez GOFO przy każdym skanie, godzinę doręczenia i te miejsca na mapie. Jeśli GOFO podaje tylko kod magazynu albo kraj, przy skanie nie wyświetla się żadna miejscowość. Godziny są odczytywane według czasu włoskiego i wyświetlane w twojej strefie czasowej; na peektracker.com oraz w aplikacjach na iPhone’a i Androida przypomina o tym wiersz pod historią („Godziny w twojej strefie czasowej: …”). Możesz ustawić powiadomienia „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Dopóki GOFO nie pokaże pierwszego skanu, Peek wyświetla „Oczekiwanie na przewoźnika GOFO Italy” i pyta dalej. Jeśli po dwóch dniach GOFO wciąż nie zna numeru, a żadne inne źródło nie odpowiedziało, Peek pokazuje „Po dwóch dniach przewoźnik nadal nie zna tego numeru. Sprawdź go, wybierz innego przewoźnika lub usuń przesyłkę.” i odtąd sprawdza go raz dziennie, a po tygodniu od dodania raz w tygodniu. Jeśli GOFO ma przesyłkę w systemie bez pierwszego skanu, Peek dalej wyświetla „Oczekiwanie na przewoźnika GOFO Italy” bez prośby o sprawdzenie numeru. Peek pokazuje przewidywaną datę doręczenia z GOFO, dopóki przesyłka nie wyjedzie w doręczenie, nie zostanie dostarczona, zwrócona ani nie będzie gotowa do odbioru; nie pokazuje wagi i pomija zdjęcia z doręczenia oraz komunikaty GOFO. Nie może kontaktować się w twoim imieniu z GOFO ani ze sklepem.

## Pytania o śledzenie GOFO Italy

### Gdzie jest moja paczka GOFO?

Wskazuje to najnowszy wpis w śledzeniu. `Arrivato alla struttura di destinazione regionale GOFO` oznacza dotarcie do regionalnego oddziału dla twojej okolicy, `Preparazione alla consegna` przygotowanie dla kuriera, a `Il corriere è fuori per la consegna` wyjazd do doręczenia. Jeśli widać tylko wpis o przygotowaniu u nadawcy, paczka nie trafiła jeszcze do GOFO.

### Czy GOFO Italy to legalna firma?

Włoską spółką GOFO jest GOFO Italia s.r.l. z siedzibą w Lainate w prowincji Mediolan. W decyzji Determina n. 12/26/DSP z 2 września 2026 r. regulator pocztowy AGCOM odnotował, że 17 czerwca 2026 r. postawił spółce trzy zarzuty: prowadzenie działalności pocztowej w zakresie usług powszechnych bez wymaganej licencji indywidualnej, brak karty usług (Carta dei servizi) oraz brak procedur rozstrzygania sporów z użytkownikami. Zgodnie z decyzją spółka przyznała się do uchybień dotyczących karty usług i procedur spornych oraz zapłaciła karę w obniżonej wysokości, w związku z czym AGCOM umorzył tę część postępowania; zarzut dotyczący niewystarczającego uprawnienia spółka zakwestionowała i ta część miała toczyć się w zwykłym trybie.

### Czy GOFO i CIRRO Parcel to to samo?

We Włoszech w ramach śledzenia tak: lista przewoźników Peek podaje dla GOFO Italy również nazwę CIRRO Parcel Italy. Śledzenie z samym opisem „Cirro” może jednak oznaczać włoską, francuską lub holenderską sieć GOFO.

### Co zrobić, jeśli paczka GOFO zaginęła?

Skontaktuj się ze sklepem. Według przepisów unijnych odpowiada on za przesyłkę do chwili doręczenia: jeśli nie udowodni doręczenia, musi wysłać towar ponownie lub zwrócić pieniądze. Gdy winny jest przewoźnik, zastosowanie ma opisana wyżej ścieżka reklamacyjna AGCOM. Więcej w [zaginiona przesyłka](guide:lost-parcel).

:::sources
- [AGCOM: Determina n. 12/26/DSP](https://www.agcom.it/sites/default/files/provvedimenti/determina/2026/Determina%20arch%20parziale%2012_26_DSP%20-%20Gofo%20pdf.pdf) – GOFO Italia s.r.l., siedziba w Lainate, postępowanie AGCOM z 2026 r. i kara zapłacona w obniżonej wysokości (2 września 2026 r.)
- [AGCOM: FAQ contenzioso tra utenti e operatori postali](https://www.agcom.it/faq-contenzioso-tra-utenti-e-operatori-postali) – reklamacja 45 dni, pojednanie 60 dni, Formulario CP w 90 dni, decyzja w 150 dni, paczki do 31,5 kg, uszkodzenia, bezpłatne kanały, centrum kontaktowe
- [Your Europe: Shipping and delivery](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_en.htm) – dostawa w 30 dni, dodatkowy termin, odpowiedzialność za uszkodzenia do odbioru
- [Your Europe: FAQs, shipping and delivery](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/faq/index_en.htm) – sklep udowadnia doręczenie, sprawdzenie przy odbiorze
- [Universal Parcel Scraper: GOFO Italy](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/gofo-it) – formaty `GFIT`/`CIIT` z cyfrą kontrolną `gofo-europe`, linki, włoskie opisy i kategorie, `Consegna fallita` pod kodem `206`, CIRRO Parcel Italy, pokazywane i pomijane pola
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – inne sieci GOFO, angielskie przekazy YunExpress, koniec wyświetlania przewidywanej daty (`GOFO_ENDED_STAGES`)
:::
