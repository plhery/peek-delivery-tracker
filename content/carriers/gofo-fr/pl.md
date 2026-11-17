---
title: Śledzenie GOFO France: numery GFFR, statusy i pomoc
description: Śledź paczkę GOFO we Francji: jak wygląda numer GFFR lub CIFR, co oznaczają francuskie statusy GOFO i do kogo się zwrócić, gdy przesyłka się opóźnia lub ginie.
slug: gofo-france-sledzenie
published: 2026-11-17
updated: 2026-11-17
---

GOFO France to francuska sieć kurierska firmy GOFO, działającej w kilku krajach, która doręcza przesyłki na terenie Francji. Na liście przewoźników w Peek znajdziesz ją również pod nazwą CIRRO Parcel France. Jej numery śledzenia zaczynają się od `GFFR` lub `CIFR`: paczkę sprawdzisz na francuskiej stronie śledzenia GOFO w serwisie gofo.com albo wklejając numer w polu powyżej. Nie masz jeszcze numeru? Zobacz, [jak namierzyć przesyłkę bez numeru śledzenia](guide:find-tracking-number).

## Numer śledzenia GOFO France: jak wygląda

Poniższe formaty Peek przypisuje od razu do GOFO France, niezależnie od wielkości liter:

| Format | Zmyślony przykład |
| --- | --- |
| `GFFR` i 13 cyfr | `GFFR4829105736492` |
| `GFFR` i 14 cyfr | `GFFR48291057364928` |
| `CIFR` i 13 cyfr | `CIFR4829105736492` |
| `CIFR` i 14 cyfr | `CIFR48291057364928` |

Rolę numeru śledzenia może pełnić również referencja nadawcy ze sklepu: `PK` oraz 20 cyfr, czasem zapisywana z myślnikami, np. `PK-4829105736492817364-5` (przykład zmyślony). Wyszukiwarka GOFO France odnajduje przesyłkę także po tej referencji, zapisanej u przewoźnika obok właściwego numeru listu przewozowego `GFFR` lub `CIFR`.

Pozostałe krajowe sieci GOFO stosują ten sam układ z kodem kraju po `GF` lub `CI`: numery `GFIT` i `CIIT` należą do GOFO Italy, `GFNL` i `CINL` do GOFO Netherlands, a `GFES` i `CIES` do GOFO Spain. Kod `GFUS` z 14 cyframi, `GF` z 13 cyframi albo `CR` z 12 cyframi należy do GOFO Express (osobnej sieci w USA), natomiast `GFCA` z 14 cyframi – do GOFO Canada. Żaden z nich nie jest numerem GOFO France. W rozróżnieniu formatów pomoże poradnik [jakiego przewoźnika jest ten numer śledzenia?](guide:tracking-number-formats).

## Statusy śledzenia GOFO: co oznaczają komunikaty po francusku

Peek pobiera dane z francuskiego systemu GOFO, dlatego skany wyświetlają się w oryginalnym brzmieniu francuskim, bez względu na wybrany język aplikacji. Najczęstsze wpisy w typowej kolejności:

| Komunikat GOFO | Co to oznacza dla Ciebie |
| --- | --- |
| `Votre colis est en cours de préparation par l'expéditeur` | Sklep przygotowuje przesyłkę; GOFO jeszcze jej nie odebrało. |
| `Expédition en transit vers GOFO` | Paczka jedzie do GOFO, ale przewoźnik jeszcze jej fizycznie nie przyjął. |
| `Arrivé au site de prise en charge` | Dotarła do punktu przyjęć GOFO – to pierwszy skan wykonany przez GOFO. |
| `Votre colis a été pris en charge au centre de tri` | Zeskanowana przy wejściu do sortowni GOFO. |
| `Colis en cours d'acheminement` | W drodze między oddziałami GOFO. |
| `Votre colis est arrivé à l’agence régionale GOFO de destination` | Jest w regionalnym oddziale GOFO w Twojej okolicy, skąd wyjedzie do doręczenia. |
| `Le chauffeur est en route pour la livraison` | Kurier wyjechał z paczką na trasę doręczeń. |
| `Échec de la livraison, motif : Le destinataire n'est pas chez lui` | Nieudana próba doręczenia: nikogo nie było w domu. |
| `Livré` | Dostarczona. |

Dwa pierwsze wiersze często mylą kupujących: dopóki nie pojawi się `Arrivé au site de prise en charge` albo skan z sortowni, GOFO nie ma jeszcze paczki u siebie, a Peek wyświetla etap „Zarejestrowana”. Doręczenie widnieje w historii wyłącznie jako `Livré`, w każdym języku, ze statusem „Dostarczona”: jeśli GOFO dopisze dalszy ciąg – w tym dane odbiorcy – Peek go pomija.

System GOFO dzieli ponadto przesyłki na ogólne kategorie. `Alerte` (alert) oznacza problem odnotowany przez GOFO i w Peek ma postać „Zgłoszono problem”; sam w sobie nie oznacza, że podjęto już próbę doręczenia. `Retourné` (zwrócona) to paczka wracająca do nadawcy, widoczna w Peek jako „Zwrócona nadawcy”.

## Paczka GOFO opóźniona, niedoręczona, zagubiona lub uszkodzona

**Brak nowych skanów.** Peek nadal odpytuje GOFO. Jeśli przed wydaniem paczki do doręczenia miną cztery dni bez nowego skanu, karta przesyłki w Peek informuje o tym („Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika GOFO France.”) i odsyła do strony pomocy GOFO France przyciskiem „Poproś przewoźnika GOFO France o pomoc”. Zobacz też [dlaczego śledzenie przesyłki się nie aktualizuje](guide:tracking-not-updating).

**Nieudana próba doręczenia.** Po wpisie `Échec de la livraison, motif : Le destinataire n'est pas chez lui` Peek pokazuje „Próba doręczenia”; gdy po `motif :` widnieje inny powód, status zmienia się na „Zgłoszono problem”. O dalszych krokach decyduje GOFO; co warto sprawdzić, opisuje poradnik [nieudana próba doręczenia](guide:missed-delivery).

**Opóźniona dostawa.** Zgodnie z unijnymi przepisami konsumenckimi sprzedawca musi dostarczyć zamówienie w terminie 30 dni, chyba że wspólnie ustaliliście inny termin. Jeśli tego nie zrobi, skontaktuj się z nim i wyznacz dodatkowy rozsądny termin; po jego bezskutecznym upływie możesz odstąpić od umowy i odzyskać pieniądze.

**Oznaczona jako `Livré`, ale jej nie masz.** Za dostarczenie zamówienia odpowiada sklep: to sprzedawca musi skontaktować się z przewoźnikiem i udowodnić, że paczka została Ci doręczona, a jeśli nie potrafi tego wykazać – wysłać identyczny produkt albo zwrócić pieniądze. Co najpierw sprawdzić w domu i u sąsiadów, przeczytasz w poradniku [dostarczona, ale nieodebrana](guide:delivered-not-received).

**Uszkodzona przesyłka.** Sprzedawca odpowiada za wszelkie uszkodzenia towaru od momentu wysyłki aż do chwili jego odbioru przez Ciebie, w tym za szkody powstałe podczas transportu. Jeśli masz taką możliwość, sprawdź stan paczki w obecności kuriera. Więcej w artykule [uszkodzona paczka](guide:damaged-parcel).

**Opłaty przy imporcie do UE.** Według Komisji Europejskiej przy zakupach internetowych spoza UE od 1 lipca 2026 r. od przesyłek o wartości do 150 € pobierane jest cło w wysokości 3 € za każdą pozycję towarową, a od 1 listopada 2026 r. dochodzi unijna opłata manipulacyjna 2 € za pozycję (obejmująca także sprzedaż na odległość powyżej 150 €). Jedna pozycja grupuje towary o tym samym kodzie taryfowym i tym samym kraju pochodzenia: za dwie koszulki zapłacisz 3 € cła i 2 € opłaty, natomiast za koszulkę i spodnie – podwójnie. Dostałeś wezwanie do dopłaty? Sprawdź [przesyłka zatrzymana w urzędzie celnym](guide:customs).

## Obsługa klienta GOFO France

Strona pomocy GOFO France znajduje się we francuskim serwisie gofo.com; Peek kieruje do niej linkiem „Poproś przewoźnika GOFO France o pomoc”, gdy przed wydaniem paczki do doręczenia miną cztery dni bez nowego skanu. Jeśli zamówienie dociera z opóźnieniem, zaginie lub przyjdzie uszkodzone, zacznij od kontaktu ze sklepem, który je wysłał – to on odpowiada przed Tobą za dostawę. Przygotuj numer `GFFR` lub `CIFR` oraz numer zamówienia.

## Śledzenie przesyłki GOFO France z Peek

[Peek](/) sam rozpoznaje numery `GFFR` i `CIFR`. Możesz też wkleić link z francuskiej strony śledzenia GOFO (w domenie gofo.com lub gofoexpress.fr, z parametrem `id=` i numerem). Działa również referencja `PK`: Peek pyta o nią GOFO France i przypisuje przesyłkę do tego przewoźnika, gdy tylko GOFO rozpozna numer; do tego czasu widzisz komunikat „Oczekiwanie na pierwszy skan przewoźnika GOFO France”.

Peek sprawdza status w GOFO France do każdych 10 minut, dopóki ktoś czeka na przesyłkę, a w pozostałym czasie co godzinę – również po dwóch dniach bez nowego skanu lub po 12 godzinach na etapie „Zarejestrowana” bądź „Przyjęta przez przewoźnika”. W dniu doręczenia sprawdza do każdych 2 minut od 6:00 czasu lokalnego, gdy paczka jest w doręczeniu i odpowiada bezpośrednio system GOFO; gdy odpowiadają wyłącznie serwisy śledzące, sprawdza co godzinę, co 3 godziny po dobie bez skanu i do każdych 15 minut w dniu dostawy. Pokazuje aktualny status, historię z miejscem podanym przez GOFO przy każdym skanie, godzinę doręczenia oraz te miejsca na mapie, a powiadomienia wyśle na „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Gdy GOFO podaje sam kod oddziału albo wyłącznie kraj, skan wyświetla się bez miejscowości. Godziny skanów z czasu francuskiego Peek przelicza na Twoją strefę czasową (pod historią widnieje „Godziny w Twojej strefie czasowej: …”). Dopóki GOFO nie zwróci żadnego wpisu, na peektracker.com widać „Oczekiwanie na przewoźnika GOFO France”, a Peek dalej odpytuje przewoźnika. Jeśli po dwóch dniach GOFO wciąż nie zna numeru i nie odpowie żadne inne źródło, Peek wyświetla „Po dwóch dniach przewoźnik nadal nie zna tego numeru. Sprawdź go, wybierz innego przewoźnika lub usuń przesyłkę.” i przechodzi na sprawdzanie raz dziennie, a po tygodniu od dodania – raz w tygodniu. Jeżeli przesyłka jest już zarejestrowana w GOFO, lecz jeszcze niezeskanowana, pozostaje „Oczekiwanie na przewoźnika GOFO France” bez prośby o weryfikację numeru. Przewidywaną datę dostawy od GOFO Peek pokazuje w czasie transportu aż do wydania do doręczenia, dostarczenia, zwrotu, złożenia w punkcie odbioru albo odnotowania próby doręczenia lub problemu; pomija wagę, zdjęcia z doręczenia i komunikaty GOFO. Nie może też skontaktować się w Twoim imieniu z GOFO ani ze sklepem.

## Najczęstsze pytania o śledzenie GOFO France

### Co oznacza status „Expédition en transit vers GOFO”?

Oznacza, że przesyłka jedzie dopiero do GOFO i przewoźnik jeszcze jej nie odebrał. Dopiero później pojawia się pierwszy własny skan GOFO: `Arrivé au site de prise en charge` (przyjęcie w punkcie odbioru) lub `Votre colis a été pris en charge au centre de tri` (przyjęcie w sortowni). Do tego momentu Peek pokazuje status „Zarejestrowana”.

### Czy GOFO France to prawdziwa firma?

Tak. GOFO FRANCE jest francuską spółką kapitałową (*SAS, société par actions simplifiée*) wpisaną do rejestru Sirene prowadzonego przez INSEE pod numerem Siren 978 559 839. Działa od 26 maja 2023 r., a jej siedziba mieści się w Marly-la-Ville (departament Val-d’Oise).

### Czy GOFO i CIRRO Parcel to ten sam przewoźnik?

We Francji na potrzeby śledzenia tak: na liście przewoźników w Peek CIRRO Parcel France to druga nazwa GOFO France. Natomiast sam napis „Cirro” w śledzeniu może oznaczać francuską, włoską albo holenderską sieć GOFO bądź też własną linię międzynarodową firmy Cirro, która przekazuje paczki pocztom krajowym pod ich lokalnymi numerami nadania.

### Co zrobić, jeśli paczka GOFO zaginęła?

Skontaktuj się ze sklepem, w którym kupiłeś towar. Zgodnie z prawem UE sprzedawca odpowiada za przesyłkę aż do chwili, gdy trafia ona w Twoje ręce: jeśli nie potrafi udowodnić doręczenia, musi wysłać identyczny towar ponownie albo zwrócić Ci pieniądze. Więcej w poradniku [zagubiona paczka](guide:lost-parcel).

:::sources
- [INSEE: Avis de situation au répertoire Sirene](https://avis-situation-sirene.insee.fr/) – GOFO FRANCE, Siren 978 559 839, spółka SAS aktywna od 26 maja 2023 r., siedziba w 95670 Marly-la-Ville
- [Your Europe: Wysyłka i dostawa](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_pl.htm) – dostawa w terminie 30 dni, dodatkowy termin, odpowiedzialność do chwili odbioru, cło 3 € za pozycję do 150 € i definicja pozycji
- [Your Europe: Najczęściej zadawane pytania – Wysyłka i dostawa](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/faq/index_pl.htm) – dowód doręczenia po stronie sprzedawcy, szkody w transporcie i sprawdzenie paczki w obecności kuriera
- [Komisja Europejska: EU Customs Reform (EN)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – cło 3 € od 1 lipca 2026 r. dla przesyłek do 150 € oraz opłata manipulacyjna od 1 listopada 2026 r.
- [Komisja Europejska: Guidance on the 3 EUR customs duty and Union handling fee (PDF, EN)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – opłata manipulacyjna 2 € za pozycję w sprzedaży na odległość, również powyżej 150 €
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formaty numerów, referencje PK, obsługiwane linki, francuskie statusy i kategorie, CIRRO Parcel France, inne sieci GOFO oraz zakres danych w Peek
:::
