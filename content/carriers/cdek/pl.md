---
title: Śledzenie CDEK (СДЭК): numery zamówień i statusy
description: Śledź paczkę CDEK (СДЭК): format numeru zamówienia, rosyjskie statusy wyjaśnione po polsku, doręczenie kurierem lub odbiór w punkcie oraz Peek.
slug: sledzenie-cdek
published: 2026-11-22
updated: 2026-11-22
---

CDEK (СДЭК, zapisywany też jako SDEK) to firma kurierska ujęta w katalogu Peek dla Rosji, Kazachstanu, Białorusi i Serbii. Dostarcza paczki kurierem pod drzwi albo do punktu odbioru lub automatu paczkowego. Numer przekazuje sklep lub nadawca — wklej go w pole powyżej. Własna wyszukiwarka CDEK działa pod adresem my.cdek.ru. CDEK zapisuje etapy po rosyjsku, a poniższa tabela wyjaśnia je po polsku.

## Numer śledzenia CDEK: jak wygląda

CDEK nazywa każdą przesyłkę zamówieniem (заказ), więc numer śledzenia to numer zamówienia CDEK (inny niż numer zamówienia w sklepie). Wyszukiwarka CDEK przyjmuje kody od 5 do 40 liter i cyfr: własne 10- lub 11-cyfrowe numery oraz powiązane numery zewnętrzne, np. zagraniczne numery pocztowe. Peek przypisuje CDEK jeden wzorzec jako słabe dopasowanie (cyfry fikcyjne):

| Numer (fikcyjny) | Co oznacza |
| --- | --- |
| `10482917365` | 11 cyfr zaczynających się od `10`: jedyny wzorzec łączony przez Peek z CDEK (słabe dopasowanie). 11 cyfr używają też [GLS Niemcy](carrier:gls-de), GLS Szwajcaria, GLS Francja, GLS Austria, GLS Polska, PostLogistics, Coordinadora i Mainfreight. |
| `48291736548` | 11 cyfr niezaczynających się od `10`: Peek nie podpowiada CDEK; sprawdź numer lub wybierz CDEK ręcznie (niżej). |
| `RR482917367CN` | Zagraniczny numer pocztowy, tutaj [China Post](carrier:china-post). Peek śledzi go na tej poczcie; jeśli CDEK powiązał go z zamówieniem, wybierz CDEK, aby zobaczyć jego etapy. |

Nie możesz znaleźć numeru? Zobacz [gdzie znaleźć numer śledzenia](guide:find-tracking-number) i [formaty numerów śledzenia](guide:tracking-number-formats).

## Statusy śledzenia CDEK po polsku

Każdy krok CDEK ma kod (według którego działa Peek) oraz rosyjski opis wyświetlany w oryginale. CDEK grupuje skany pod szerszymi etapami takimi jak `В пути`: Peek pokazuje szeroki etap tylko wtedy, gdy brakuje pod nim szczegółowych skanów. W przybliżonej kolejności:

| Komunikat CDEK | Co to oznacza dla ciebie | Status w Peek |
| --- | --- | --- |
| `Создан` | Utworzono: zlecenie istnieje w CDEK, ale paczki może jeszcze nie być u przewoźnika. | „Zarejestrowana” |
| `Назначен курьер` | Kurier jedzie odebrać zamówienie od nadawcy. | „Zarejestrowana” |
| `Курьер забрал заказ у отправителя` | Kurier odebrał paczkę od nadawcy. | „Przyjęta przez przewoźnika” |
| `Принят на доставку` | Przyjęta do doręczenia: CDEK ma już paczkę. | „Przyjęta przez przewoźnika” |
| `В пути` | W drodze: zbiorczy etap między nadaniem a końcową fazą transportu. | „W transporcie” |
| `Принят СДЭК на территории РФ` | Zagraniczna paczka trafiła do sieci CDEK w Rosji. | „W transporcie” |
| `Отправлен в сортировочный центр` | Wysłana do sortowni. | „W transporcie” |
| `Прибыл в сортировочный центр` | Dotarła do sortowni. | „W transporcie” |
| `Отправлено в г.` + miasto | Wysłana do tego miasta. | „W transporcie” |
| `Отправлен в пункт доставки` | Wysłana do oddziału, z którego wyjeżdżają kurierzy. | „W transporcie” |
| `Прибыл в пункт доставки` | Dotarła do oddziału doręczeń. | „W transporcie” |
| `Отправлен в пункт выдачи` | Wysłana do twojego punktu odbioru. | „W transporcie” |
| `Доставляет курьер` | W doręczeniu przez kuriera (obejmuje próby doręczenia). | „W doręczeniu” |
| `Выдан курьеру` | Wydana kurierowi do doręczenia; pojawia się przy każdej kolejnej próbie. | „W doręczeniu” |
| `Курьер не смог доставить заказ` | Kurier nie zdołał doręczyć przesyłki. | „Próba doręczenia” |
| `Готов к выдаче`, `Поступил. Заберите заказ` | Gotowa: dotarła do punktu odbioru lub automatu. Możesz ją odebrać. | „Do odbioru” |
| `Вручён` | Wydana odbiorcy: doręczona. | „Dostarczona” |
| `Не вручён` | Niewydana: zamówienie zakończyło się bez doręczenia (nieodebrane lub odmowa). | „Zgłoszono problem” |

CDEK może też zakończyć zamówienie jako anulowane („Zgłoszono problem”) albo częściowo doręczone, jeśli odebrałeś część zawartości, a reszty odmówiłeś („Dostarczona” w Peek).

## Jak doręcza CDEK: kurier lub punkt odbioru

Kolejne wpisy pokazują, jak dotrze twoja przesyłka.

**Pod drzwi.** Po przybyciu do oddziału (`Прибыл в пункт доставки`) paczka rusza z kurierem (`Выдан курьеру`). Po nieudanej próbie `Выдан курьеру` pojawia się ponownie przy następnej.

**Do punktu odbioru lub automatu.** Po wpisie `Отправлен в пункт выдачи` poczekaj na `Поступил. Заберите заказ` i wtedy idź po odbiór.

Strona zamówienia na my.cdek.ru pokazuje punkt odbioru, jego adres, kontakt, godziny otwarcia i ostatni dzień odbioru. Peek pomija te informacje, więc sprawdź je tam. Ponieważ główna strona cdek.ru blokuje automatyczne zapytania, ta strona nie podaje czasów dostawy, czasu przechowywania ani liczby prób. Zobacz [nieodebrane paczki](guide:uncollected-parcel), [nieudane doręczenie](guide:missed-delivery) i [automaty paczkowe](guide:parcel-lockers).

**Z zagranicy.** `Принят СДЭК на территории РФ` oznacza przejęcie zagranicznej paczki przez CDEK w Rosji. Bez dostępu do oficjalnych stron cdek.ru nie podajemy tu zasad ani opłat celnych; zobacz [paczki zatrzymane na cle](guide:customs).

## Paczka CDEK nie dociera lub śledzenie stoi

**Tylko `Создан` albo brak etapów.** Nadawca mógł jeszcze nie oddać paczki albo w numerze jest błąd. Peek sprawdza dalej. Porównaj cyfry z wiadomością sklepu, a po kilku dniach zapytaj sprzedawcę.

**Brak zmian przez kilka dni.** Gdy ostatni wpis ma cztery dni lub więcej przed wyjściem do doręczenia albo punktem odbioru, Peek pokazuje na liście dzień ostatniego skanu („Brak aktualizacji od …”), a na karcie paczki „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika CDEK.” wyłącznie w tej informacji z linkiem „Poproś przewoźnika CDEK o pomoc” do FAQ. Zobacz [śledzenie nie aktualizuje się](guide:tracking-not-updating).

**`Курьер не смог доставить заказ`.** Nieudana próba. Kolejna pojawi się znów jako `Выдан курьеру`; jeśli nie, zapytaj sklep.

**`Не вручён`.** Zamówienie zakończyło się bez odbioru. Ustal ze sklepem zwrot pieniędzy lub ponowną wysyłkę. Zobacz [zwrot do nadawcy](guide:returned-to-sender).

**Doręczona, ale nic nie dotarło.** Sprawdź, czy ktoś nie odebrał jej za ciebie, potem skontaktuj się ze sklepem. Zobacz [doręczono, ale nie ma paczki](guide:delivered-not-received).

**Zaginiona.** Nadawcą w umowie z CDEK jest sklep — zacznij od sprzedawcy. Zobacz [zaginione paczki](guide:lost-parcel).

## Obsługa klienta i kontakt CDEK

Ponieważ główny serwis cdek.ru blokuje automatyczny odczyt, ta strona nie zawiera numeru telefonu ani adresu e-mail: Peek podaje tylko kontakty odczytane bezpośrednio z oficjalnych stron przewoźnika. Śledzenie i strony zamówień działają na my.cdek.ru.

Przy zakupie ze sklepu zacznij od kontaktu ze sprzedawcą.

## Śledzenie paczki CDEK w Peek

[Peek](/) śledzi ponad 4500 przewoźników, lecz po samym układzie cyfr nie ma pewności, że to CDEK. Mimo to wklej numer:

- **11 cyfr zaczynających się od `10`.** Peek pyta pasujących przewoźników po pięciu naraz, wyświetlając „Sprawdzanie usług śledzenia…”. Przy sprawdzaniu z Rosji, Kazachstanu, Białorusi lub Serbii CDEK jest pytany jako pierwszy; z innych krajów nadal należy do pierwszej piątki. Jeśli CDEK zna numer, Peek wyświetla „CDEK ma tę przesyłkę.”. Gdy zna go kilku przewoźników, wybierasz właściwego; jeśli jeszcze żaden, widzisz „jeszcze nie znaleziono · Peek sprawdza dalej”. Możesz też kliknąć „Zmień” i wybrać CDEK w sekcji „Pasują do numeru”.
- **Inny format (w tym 10 cyfr).** Kliknij „Zmień” i wyszukaj CDEK (wpisanie СДЭК lub SDEK również działa).
- **Link.** Wklej link zamówienia z my.cdek.ru (`/lkfl/order/...`), aby od razu śledzić je w CDEK.

W już dodanej paczce użyj „Zmień przewoźnika”. Paczka bez przewoźnika pokazuje „Brak historii śledzenia”, a przypisana do CDEK — „Oczekiwanie na przewoźnika CDEK” do pierwszej odpowiedzi.

**Co pokazuje Peek.** Peek odczytuje publiczne śledzenie CDEK po samym numerze, bez konta i potwierdzenia telefonicznego: status, rosyjskie wpisy, miasto i dokładne godziny (UTC przeliczone na twoją strefę czasową, z podpisem „Godziny w twojej strefie czasowej:” pod historią). Anonimowa odpowiedź ukrywa kod odbioru dostępny po zalogowaniu, a Peek pomija adres nadawcy, dane punktu odbioru (adres, telefon, godziny, termin odbioru), płatności, wagę, wymiary i przewidywaną datę doręczenia. Link „Otwórz stronę przewoźnika CDEK” otwiera zamówienie na my.cdek.ru.

**Drugi numer tego samego zamówienia.** Jeśli śledzisz numer powiązany w CDEK (np. zagraniczny kod pocztowy), CDEK zwraca własny numer zamówienia, pokazany jako „CDEK śledzi przesyłkę pod numerem”.

**Gdy CDEK nie odpowiada.** Strony CDEK potrafią ograniczać zapytania automatyczne. Peek pyta wtedy kolejno ParcelsApp, Ship24 i 17TRACK (kopie z ParcelsApp opisują czasem odcinki trasy bez dokładnej godziny) i wraca do CDEK, gdy ten znów odpowiada.

**Numer innego przewoźnika.** Jeśli 11-cyfrowy numer zapisany w CDEK pozostaje nieznany, Peek przez pierwsze 30 dni odpytuje innych pasujących przewoźników i przełącza przesyłkę („Automatycznie zmieniono przewoźnika (wcześniej: CDEK)”), gdy inny pokaże ruch.

**Paczki przekazane CDEK.** Qwintry przekazuje część przesyłek do CDEK: gdy wskaże CDEK i jego numer, pojawia się „Doręcza: CDEK” (lub „Zgłoszona przewoźnikowi CDEK, jeszcze nieprzyjęta” przed pierwszym skanem CDEK), a Peek przechodzi na CDEK, gdy ten pokaże co najmniej równie świeży wpis. DobroPost pokazuje skany CDEK we własnym śledzeniu (`DBRPST`). Dla zagranicznego numeru pocztowego pod CDEK sprawdzanego z Białorusi Peek może też zapytać Belpost.

**Częstotliwość i powiadomienia.** Gdy masz paczkę otwartą na ekranie — albo w dniu doręczenia z włączonymi powiadomieniami — Peek odpytuje CDEK bezpośrednio nawet co 10 minut, a od 6:00 czasu lokalnego w dniu wydania kurierowi co 2 minuty; poza tym i po dwóch dniach bez zmian (12 godzin przy „Zarejestrowana” lub „Przyjęta przez przewoźnika”) sprawdza co godzinę. Przez same serwisy śledzące sprawdza co godzinę, co 3 godziny po dniu bez skanu i do 15 minut w dniu doręczenia, gdy paczka jest otwarta na ekranie lub włączono powiadomienia. Wybierz „Każdy skan”, „Ważne etapy” (zalecane przy odbiorze w punkcie, bo „Tylko doręczenie” nie powiadamia o gotowości do odbioru) albo „Tylko doręczenie”. Peek nie kontaktuje się z CDEK ani ze sklepem za ciebie.

## Pytania o śledzenie CDEK

### Co oznacza СДЭК po polsku i angielsku?

CDEK (pisany także SDEK lub CDEK Express). Jego śledzenie działa na my.cdek.ru.

### Jak wygląda numer śledzenia CDEK?

Peek łączy z CDEK 11 cyfr zaczynających się od `10`, np. `10482917365`, ale 11 cyfr stosują też inni przewoźnicy (a CDEK nadaje również numery 10-cyfrowe).

### Co oznacza „Поступил. Заберите заказ” w CDEK?

Paczka dotarła do punktu odbioru lub automatu i czeka na odbiór — Peek wyświetla „Do odbioru”. Adres i termin odbioru sprawdź na my.cdek.ru.

### Czy mogę śledzić paczkę CDEK po polsku?

Tak. Peek pokazuje główny status po polsku (np. „W transporcie”), a poszczególne etapy w oryginalnym rosyjskim brzmieniu CDEK, przetłumaczonym w powyższej tabeli.

### Dlaczego Peek prosi mnie o wybór przewoźnika dla numeru CDEK?

GLS, PostLogistics i inni przewoźnicy też stosują 11 cyfr. Gdy ten sam numer zna więcej niż jedna firma, wybierz CDEK z listy.

:::sources
- [Universal Parcel Scraper: CDEK](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/cdek) – nazwy, kraje, format 11 cyfr (`10`) i 10 cyfr, linki my.cdek.ru, kody, rosyjskie teksty i etapy, czasy UTC, numer kanoniczny oraz ochrona antybotowa
- [Universal Parcel Scraper: Qwintry](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/qwintry) – śledzenie Qwintry wskazujące CDEK i jego numer
- [Universal Parcel Scraper: DobroPost](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dobropost) – kroki CDEK w śledzeniu DobroPost
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – inni przewoźnicy 11-cyfrowi, kolejność odpytywania i serwisów śledzących, kopie z ParcelsApp, przekazania i Belpost na Białorusi
:::
