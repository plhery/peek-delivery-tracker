---
title: Śledzenie paczek CTT Portugal: numery, statusy i odbiór
description: Śledź przesyłkę CTT Portugal z Polski lub w Portugalii: numery PT, znaczenie portugalskich statusów, terminy odbioru, odprawa celna i kontakt z CTT.
slug: sledzenie-ctt-portugal
published: 2026-11-17
updated: 2026-11-17
---

CTT (CTT – Correios de Portugal) to narodowy operator pocztowy Portugalii, do którego należy też portugalskie ramię kurierskie CTT Expresso. Przesyłkę sprawdzisz w wyszukiwarce „Seguir objeto” na [ctt.pt](https://www.ctt.pt/feapl_2/app/open/objectSearch/objectSearch.jspx?request_locale=pt) (dostępnej po portugalsku i angielsku), w aplikacji mobilnej App CTT albo wklejając numer w polu powyżej. Kod śledzenia dostajesz od nadawcy, w SMS-ie lub e-mailu od CTT bądź na papierowym awizie w skrzynce.

Nie pomyl dwóch spółek z tej samej grupy: przesyłki pocztowe i CTT Expresso śledzi się na `ctt.pt`, natomiast [CTT Express](carrier:ctt-express) — hiszpańska spółka kurierska grupy, dawniej Tourline Express — prowadzi osobny, wyłącznie hiszpańskojęzyczny lokalizator na `cttexpress.com`.

## Numer śledzenia CTT: jak wygląda

Większość numerów obsługiwanych na `ctt.pt` ma międzynarodowy układ 13 znaków: dwie litery oznaczające usługę, dziewięć cyfr oraz dwie litery kraju, w którym wygenerowano numer (`PT` dla Portugalii — u klientów umownych CTT także wtedy, gdy paczka rusza fizycznie z Hiszpanii lub Chin). Przesyłki kurierskie między Hiszpanią a Portugalią mają z kolei 22 albo 25 cyfr.

| Format (przykładowy) | Co oznacza |
| --- | --- |
| `RR482910576PT` | Kod pocztowy lub CTT Expresso z Portugalii (`RR`, `CA`, `DA`, `EA` itd.). Dziewiąta cyfra jest cyfrą kontrolną. |
| `RR482910576PL` | Ten sam układ nadany przez inną pocztę narodową — tutaj [Pocztę Polską](carrier:poczta-polska). |
| `0012340012345678901234` | 22 cyfry (`00`, cztery cyfry, `00` i 14 cyfr): iberyjska przesyłka [CTT Express](carrier:ctt-express), widoczna również na `ctt.pt`. |
| `0012340012345678901234001` | 25 cyfr: powyższe 22 cyfry z dopisaną końcówką `001`. |

Wpisuj numer bez spacji: według angielskiej pomocy CTT komunikat `Item not found` oznacza, że przesyłki nie odnaleziono w sieci CTT (albo że numer służy wyłącznie do odprawy celnej). Gdy pocztowa paczka z końcówką `PT` jedzie do Polski, doręcza ją [Poczta Polska](carrier:poczta-polska) pod tym samym numerem. Nie dostałeś żadnego SMS-a ani e-maila z kodem? Przesyłka najprawdopodobniej idzie bez śledzenia; zajrzyj do poradnika [jak znaleźć numer śledzenia](guide:find-tracking-number).

## Co oznaczają statusy śledzenia CTT

Wyszukiwarka „Seguir objeto” działa po portugalsku i angielsku. Oto najczęstsze statusy portugalskie oraz międzynarodowe:

| Status CTT | Co oznacza dla ciebie |
| --- | --- |
| `Aguarda entrada nos CTT` | CTT otrzymało dane elektroniczne od nadawcy, ale nie ma jeszcze samej paczki. |
| `Aceite` | Przyjęta przez CTT lub pocztę kraju nadania — rozpoczyna się przewóz. |
| `Em exportação` | Odprawa celna w kraju nadania przed wysłaniem za granicę. |
| `International dispatch` | Dzień wylotu lub wyjazdu przesyłki z kraju nadania. |
| `International reception` | Dzień wejścia przesyłki do kraju docelowego. |
| `Em importação` | Odprawa celna w kraju docelowym. |
| `Em trânsito` | W drodze lub sortowaniu między centrami operacyjnymi; status może powtarzać się wielokrotnie. |
| `Em entrega` | Dziś w doręczeniu u listonosza lub kuriera. |
| `Entrega não conseguida` | Nieudana próba doręczenia; sprawdź skrzynkę na listy, SMS-y lub e-mail. |
| `No ponto de entrega` | Gotowa do odbioru w punkcie lub automacie paczkowym. |
| `Em espera` | Czeka na dyspozycje albo na ustalony dzień doręczenia. |
| `Cancelado` | Nadanie zostało anulowane i przesyłka nie zostanie doręczona. |
| `Entregue` | Doręczona. |
| `Devolvido` | Zwrócona do nadawcy. |

Nieudaną próbę doręczenia oznacza też status `Não entregue`. Przy paczkach spoza UE trafiających do Portugalii w trakcie odprawy pojawiają się ponadto komunikaty `O envio foi selecionado para apresentação à Alfândega.` lub `Foi enviada notificação ao destinatário. O envio está em desalfandegamento.`.

## Nieudane doręczenie: awizo, punkty odbioru i automaty Locky

**W Polsce.** CTT wyraźnie zaznacza, że poza Portugalią miejsce i czas oczekiwania na odbiór ustala wyłącznie operator pocztowy kraju przeznaczenia — w Polsce zasady i terminy awizowania określa więc [Poczta Polska](carrier:poczta-polska).

**W Portugalii.** Jeśli nikogo nie ma pod adresem (doręczenie do rąk własnych wyklucza osoby niepełnoletnie), dalszy los przesyłki zależy od wykupionej usługi i instrukcji nadawcy: paczka trafia do punktu CTT (Loja CTT, Ponto CTT, automat Cacifo Locky lub agent Payshop), ma kolejną próbę doręczenia, czeka na dyspozycję w centrum operacyjnym albo od razu wraca do nadawcy. *Encomenda Postal* bez opcji doręczenia pod drzwi oraz *Encomenda Expresso Em Dois Dias* bez wskazanego adresu trafiają od razu do punktu odbioru. Terminy liczą się w dniach roboczych od następnego dnia roboczego po papierowym awizie (SMS lub e-mail przychodzi dopiero, gdy paczka czeka już w punkcie, razem z datą końcową):

| Rodzaj przesyłki w Portugalii | Czas na odbiór (dni robocze) |
| --- | --- |
| Listy zwykłe i polecone (*Correio Registado*) | 6 (10 w Loja CTT przy odbiorze *Cartão de Cidadão*) |
| Krajowa paczka pocztowa (*Encomenda Postal Nacional*) | 10 |
| Międzynarodowa paczka pocztowa (`CA`–`CZ`) | 15 |
| Przesyłki CTT Expresso (`DA`–`EV`, `EX`, `EE`) | 5 (10 dla `CE`, `EP`, `EH`, `ES` oraz Expresso z Hiszpanii) |

Odbiór *Encomenda Postal* można przedłużyć maksymalnie do 30 dni, a *Encomenda Expresso* — o maksymalnie 5 dni roboczych, o ile zezwolił na to nadawca. Zabierz awizo (lub podaj numer przesyłki przy okienku) i dokument tożsamości: dowód osobisty, paszport, kartę pobytu albo prawo jazdy z pełnym imieniem i nazwiskiem, zdjęciem oraz podpisem.

**Automaty Locky:** przyjmują paczki CTT Expresso oraz Nacex (bez listów, *Encomenda Postal* i przesyłek pobraniowych) i przechowują je przez **5 dni**. Kod PIN przychodzi SMS-em lub e-mailem; od momentu, gdy śledzenie pokaże dotarcie do lokalizacji, udostępnienie skrytki może zająć do 48 godzin, a wysyłka samego PIN-u — kolejne 24 godziny.

**Zmiana doręczenia Expresso:** płatne zmiany w „Seguir objeto” lub aplikacji App CTT działają wyłącznie dla numerów zaczynających się od litery `D` (*Para Amanhã* i *Em Dois Dias*), dopóki paczka nie czeka już w punkcie odbioru (np. nowa data do 5 dni roboczych później kosztuje 5,01 € netto; decyzja zapada w ciągu 2 dni roboczych). W sieci [CTT Express](carrier:ctt-express) kurier podejmuje do dwóch prób od poniedziałku do piątku w godz. 9:00–19:00, punkt Collectt Express przechowuje paczkę 10 dni, a automat Locky — 5 dni.

## Paczka stoi w miejscu, jest w urzędzie celnym albo wraca

CTT uprzedza, że niektóre kraje przekazują skany międzynarodowe z opóźnieniem lub nie udostępniają ich wcale. Gdy status nie zmienia się od kilku dni, skontaktuj się ze sprzedawcą i sprawdź poradnik [śledzenie przesyłki się nie aktualizuje](guide:tracking-not-updating).

**Cło i podatki.** Między Portugalią a Polską nie ma granic celnych (unia celna UE). Inaczej wygląda zakup **spoza Unii Europejskiej adresowany do Portugalii**: od 1 lipca 2026 r. zamówienia prywatne o wartości do 150 € podlegają w UE zryczałtowanemu cłu **3 €** (w Portugalii plus VAT) za każdy rodzaj produktu, a od 1 listopada 2026 r. dochodzi unijna opłata manipulacyjna w wysokości **2 €** (plus VAT) za każdy rodzaj produktu niezależnie od wartości przesyłki (paczki do 150 € bez wymaganych danych identyfikacyjnych produktu od sprzedawcy mogą zostać zwrócone). Jeżeli VAT i opłaty nie zostały pobrane przy zakupie, w portalu celnym CTT zapłacisz portugalski VAT oraz opłatę CTT za odprawę: **7 € (+ VAT)** do 150 € albo **14 €** (plus cło *ad valorem*) powyżej 150 € do 1000 €. Od wejścia przesyłki do Portugalii masz **20 dni** na odprawę — potem paczka wraca do nadawcy bez możliwości cofnięcia zwrotu; więcej w poradniku [paczka zatrzymana przez urząd celny](guide:customs).

**Reklamacje i zwroty.** Reklamację pocztową złożysz przez formularz internetowy, telefonicznie lub w placówce Loja/Ponto CTT w ciągu **roku** (krajowe) albo **sześciu miesięcy** (międzynarodowe) od nadania. Ogólne warunki CTT Expresso dają klientowi zlecającemu przewóz **8 dni** od doręczenia na zgłoszenie uszkodzenia (z oryginalnym opakowaniem lub zdjęciami) oraz **30 dni** przy zaginięciu lub opóźnieniu. Rozpoczętego zwrotu nie da się anulować. Paczki, których nie udało się ani doręczyć, ani zwrócić, trafiają do *Refugo Postal*: paczki pocztowe i Expresso można tam odzyskać przez obsługę klienta między **20. dniem a 3. miesiącem** (najpóźniej 5 dni przed licytacją).

## Obsługa klienta i telefon CTT

Oficjalne kanały CTT sprawdzone 17 listopada 2026 r.:

- **Telefon CTT (klienci indywidualni):** `+351 21 047 16 16` z zagranicy (opłata jak za połączenie międzynarodowe do Portugalii; w Portugalii `21 047 16 16` po stawce na numery stacjonarne), w dni robocze od 8:30 do 19:30. Dla firm: `+351 21 244 00 35` z zagranicy (`707 200 115` w Portugalii) w tych samych godzinach. CTT nie podaje infolinii po polsku ani po angielsku.
- **Formularz kontaktowy:** na [ctt.pt](https://www.ctt.pt/ajuda/particulares/apoio-ao-cliente/contactos/formulario-de-contacto), z odpowiedziami od poniedziałku do piątku w godz. 8:30–19:30, albo osobiście w dowolnej placówce Loja lub Ponto CTT w Portugalii.
- **CTT Express (Hiszpania):** `900 841 352` (bezpłatny w Hiszpanii), `+34 916 602 200` z zagranicy lub `ccaweb@cttexpress.com`, od poniedziałku do piątku w godz. 9:00–19:00 (poza świętami).

> CTT ostrzega przed fałszywymi SMS-ami, e-mailami i wiadomościami WhatsApp o rzekomej dopłacie celnej, brakującym numerze domu czy wstrzymanej dostawie, a także przed podrobionymi domenami takimi jak `ctt-pt.org` czy `cttportugues.com`. Prawdziwe linki CTT zaczynają się od `www.ctt.pt`.

## Śledzenie przesyłek CTT w Peek

[Peek](/) natychmiast rozpoznaje kody pocztowe i Expresso należące do CTT (`PT` z poprawną cyfrą kontrolną) oraz linki `ctt.pt` zawierające `objects=`. Gdy pomylisz jedną cyfrę (np. `RR482910575PT`), suma kontrolna się nie zgadza i Peek nie przypisze żadnego przewoźnika — sprawdź wtedy numer lub naciśnij „Zmień”. Pozostałe numery CTT spoza standardu pocztowego są odczytywane przez uniwersalne serwisy śledzenia, a kody 22- i 25-cyfrowe trafiają do [CTT Express](carrier:ctt-express).

Peek pokazuje aktualny status, do 20 najnowszych skanów z przybliżoną lokalizacją (sortownia lub punkt odbioru) na mapie oraz numeryczny kod statusu CTT; CTT nie podaje przewidywanej daty doręczenia. Portugalskie skany są odczytywane według czasu lizbońskiego (lub azorskiego dla kodów pocztowych od `9500`), a hiszpańskie oddziały CTT Express według czasu Madrytu lub Wysp Kanaryjskich — po czym Peek przelicza je na strefę twojego urządzenia (na peektracker.com i w aplikacji na iPhonie pod historią widnieje dopisek: „Godziny w twojej strefie czasowej: …”). Gdy paczka `PT` zmierza do Polski, Peek odpytuje również [Pocztę Polską](carrier:poczta-polska): dopóki widnieje tam sama zapowiedź bez własnego skanu, karta pokazuje „Zgłoszona przewoźnikowi Poczta Polska, jeszcze nieprzyjęta”, a po pojawieniu się aktualnego skanu zmienia opis na „Doręcza: Poczta Polska”, zachowując wcześniejszą historię z CTT (i odwrotnie: „CTT Portugal” dla paczek z Polski do Portugalii).

Peek sprawdza CTT nawet co 10 minut — a w dniu wyjazdu kuriera od 6:00 czasu lokalnego nawet co 2 minuty, gdy przesyłka ma etap „W doręczeniu” i odpowiada bezpośredni system CTT (przez serwisy śledzenia co 15 minut) — oraz co godzinę po 48 godzinach bez zmian lub po 12 godzinach na etapie „Zarejestrowana” bądź „Przyjęta przez przewoźnika”. Powiadomienia możesz ustawić jako „Wszystkie aktualizacje”, „Tylko ważne” albo „Tylko w dniu dostawy”.

## Najczęstsze pytania o śledzenie CTT

### Gdzie jest moja paczka CTT?

Wskazuje to ostatni wpis w „Seguir objeto” albo w Peek: `Em entrega` oznacza dzisiejsze doręczenie, a `No ponto de entrega` — oczekiwanie w punkcie odbioru. Jeśli przesyłka z końcówką `PT` idzie do Polski, krajowy odcinek pokaże także [Poczta Polska](carrier:poczta-polska).

### Czy CTT i CTT Express to ta sama firma?

Należą do jednej grupy kapitałowej, ale mają osobne śledzenie: CTT obsługuje pocztę oraz CTT Expresso w Portugalii na `ctt.pt`, natomiast [CTT Express](carrier:ctt-express) to hiszpański kurier ekspresowy działający w Hiszpanii i Portugalii, śledzony po hiszpańsku na `cttexpress.com`.

### Czym jest punkt odbioru CTT?

W Portugalii to placówka pocztowa (*Loja CTT*), sklep partnerski (*Ponto CTT*), automat paczkowy *Cacifo Locky* albo punkt *Payshop*. W Polsce przesyłki pocztowe nadane przez CTT odbiera się w placówkach i punktach [Poczty Polskiej](carrier:poczta-polska).

### Czy infolinia CTT obsługuje po polsku lub angielsku?

CTT nie wymienia linii w języku polskim ani angielskim: numer `+351 21 047 16 16` działa w dni robocze w godz. 8:30–19:30 (koszt połączenia międzynarodowego do Portugalii), a formularz kontaktowy na `ctt.pt` odpowiada pisemnie od poniedziałku do piątku w tych samych godzinach.

:::sources
- [CTT: Results in Seguir objeto (angielski)](https://www.ctt.pt/ajuda/particulares/seguir-ou-alterar-entrega/seguir/resultados-que-podem-aparecer-na-pesquisa-do-seguir-objeto?language_id=1) – angielskie statusy oraz `Item not found`
- [CTT: Resultados do Seguir objeto (portugalski)](https://www.ctt.pt/ajuda/particulares/seguir-ou-alterar-entrega/seguir/resultados-que-podem-aparecer-na-pesquisa-do-seguir-objeto) – objaśnienia portugalskich statusów
- [CTT: Seguir objeto](https://www.ctt.pt/feapl_2/app/open/objectSearch/objectSearch.jspx?request_locale=pt) – wyszukiwarka przesyłek po portugalsku i angielsku
- [CTT: Como seguir uma entrega](https://www.ctt.pt/ajuda/particulares/seguir-ou-alterar-entrega/seguir/como-seguir-uma-entrega) – śledzenie na stronie, w App CTT i przesyłki zagraniczne
- [CTT: Encontrar o código de envio](https://www.ctt.pt/ajuda/empresas/seguir-ou-alterar-entrega/seguir/encontrar-o-codigo-de-envio) – kody `PT` oraz numery 22- i 25-cyfrowe
- [CTT: Não tenho código de envio](https://www.ctt.pt/ajuda/particulares/seguir-ou-alterar-entrega/alterar/a-entrega-vem-a-caminho-mas-nao-tenho-codigo-de-envio-o-que-posso-fazer) – przesyłki bez numeru śledzenia
- [CTT: Entrega não conseguida](https://www.ctt.pt/ajuda/particulares/receber/problemas-na-entrega/entrega-nao-conseguida) – zasady po nieudanym doręczeniu i brak możliwości cofnięcia zwrotu
- [CTT: O que precisa de levar consigo](https://www.ctt.pt/ajuda/particulares/receber/levantar-correspondencia-ou-encomendas-/o-que-precisa-de-levar-consigo) – dokumenty akceptowane przy odbiorze
- [CTT: Cacifos Locky](https://www.ctt.pt/ajuda/particulares/receber/cacifos-locky/como-levantar-uma-encomenda) – 5 dni na odbiór, kod PIN i do 48 godzin na udostępnienie
- [CTT: Prazo para levantar](https://www.ctt.pt/ajuda/particulares/receber/levantar-correspondencia-ou-encomendas-/prazo-para-levantar) – terminy odbioru w dniach roboczych i przesyłki poza Portugalią
- [CTT: Alterar uma entrega Expresso](https://www.ctt.pt/particulares/receber/seguir-ou-alterar-uma-entrega/alterar-uma-entrega-expresso) – numery na literę `D` i cennik zmian (5,01 € + VAT)
- [CTT: Serviços cobrados](https://www.ctt.pt/ajuda/particulares/importacoes-e-desalfandegamento/taxas-e-servicos-cobrados/servicos-cobrados) – opłaty CTT za odprawę celną (7 € + VAT oraz 14 €)
- [CTT: Fim da isenção de direitos aduaneiros até 150 €](https://www.ctt.pt/particulares/receber/desalfandegar/fim-da-isencao-de-direitos-aduaneiros-em-compras-ate-150-euros-fora-da-uniao-europeia) – cło 3 € + VAT i opłata manipulacyjna 2 € + VAT za rodzaj produktu (odczyt 17 listopada 2026 r.)
- [Komisja Europejska: Reforma celna UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_pl) – cło 3 € od 1 lipca 2026 r. oraz opłata 2 € od 1 listopada 2026 r.
- [Komisja Europejska: Guidance on the €3 customs duty and Union handling fee (PDF, angielski)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – naliczanie za rodzaj towaru niezależnie od wartości
- [CTT: Prazo para desalfandegar](https://www.ctt.pt/ajuda/particulares/importacoes-e-desalfandegamento/prazo-de-desalfandegamento/prazo-para-desalfandegar-a-minha-encomenda) – 20 dni na odprawę w Portugalii
- [CTT: Condições gerais de transporte de Encomendas Expresso](https://www.ctt.pt/transversais/condicoes-gerais-transporte-encomendas-expresso) – terminy reklamacji Expresso (8 i 30 dni)
- [CTT: Refugo Postal](https://www.ctt.pt/ajuda/particulares/receber/levantar-correspondencia-ou-encomendas-/recuperar-o-correio-que-esta-no-refugo-postal) – odzyskiwanie paczek między 20 dniami a 3 miesiącami
- [CTT: Reclamações](https://www.ctt.pt/ajuda/particulares/apoio-ao-cliente/contactos/reclamacoes) – reklamacje pocztowe (1 rok krajowe, 6 miesięcy międzynarodowe)
- [CTT: Linhas de apoio](https://www.ctt.pt/ajuda/particulares/apoio-ao-cliente/contactos/linhas-de-apoio) – telefony i godziny pracy (odczyt 17 listopada 2026 r.)
- [CTT: Formulário de contacto](https://www.ctt.pt/ajuda/particulares/apoio-ao-cliente/contactos/formulario-de-contacto) – formularz kontaktowy (poniedziałek–piątek, 8:30–19:30)
- [CTT: Alertas de phishing](https://www.ctt.pt/transversais/alertas-de-phishing) – ostrzeżenia o fałszywych SMS-ach, e-mailach i domenach
- [CTT Express: Envíos a Portugal](https://www.cttexpress.com/envios/portugal) – dwie próby doręczenia
- [CTT Express: Horarios de reparto](https://www.cttexpress.com/preguntas-frecuentes/horarios) – godziny doręczeń poniedziałek–piątek 9:00–19:00
- [CTT Express: Envío y recogida Collectt](https://www.cttexpress.com/red-collectt-express/envio-y-recogida) – 10 dni w punkcie Collectt i 5 dni w automacie Locky
- [CTT Express: Cómo contactar](https://www.cttexpress.com/preguntas-frecuentes/como-contactar-con-ctt) – kontakt w Hiszpanii
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – rozpoznawanie numerów, strefy czasowe i przekazania pocztowe w Peek
:::
