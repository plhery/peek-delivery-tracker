---
title: Śledzenie Lingdong International: numer SZLD i statusy
description: Śledź paczkę Lingdong International (领东国际) z Shenzhen: jak wygląda numer SZLD, co oznacza każdy etap śledzenia, kto doręcza przesyłkę i opłaty celne.
slug: sledzenie-lingdong
published: 2026-11-18
updated: 2026-11-21
---

Lingdong International (领东国际, pełna nazwa 深圳市领东国际货运代理有限公司, Shenzhen Lingdong International Freight Forwarding Co., Ltd.) to spedytor z Shenzhen w Chinach, który wysyła za granicę zamówienia z chińskich sklepów internetowych. Jego własne numery śledzenia zaczynają się od SZLD: wklej swój numer w polu powyżej albo wpisz go w oknie 快件查询 (wyszukiwanie przesyłek) na stronie lingdong56.com, które otwiera stronę śledzenia Lingdong. Numer przekazuje sklep, który nadał Twoje zamówienie.

## Numer śledzenia Lingdong: jak wygląda

Lingdong nie publikuje wzorca swoich numerów. Listy przewozowe rozpoznawane bezpośrednio przez jego wyszukiwarkę składają się z liter SZLD i dziesięciu cyfr. Z przykładowymi cyframi:

| Numer | Co oznacza |
| --- | --- |
| `SZLD4829105736` | SZLD i dziesięć cyfr: numer listu przewozowego Lingdong International. |
| `SZLD482910573` | Tylko dziewięć cyfr: to nie jest format Lingdong. Sprawdź, czy nie brakuje jednej cyfry. |
| `LX482910576CN` | Dwie litery, dziewięć cyfr i CN: numer [China Post](carrier:china-post), a nie Lingdong. |

Spacje i małe litery nie przeszkadzają przy wklejaniu numeru do Peek. Jeśli wpiszesz numer taki jak `SZLD482910573`, w którym brakuje jednego znaku, Peek podpowie: „Zaczyna się jak numer śledzenia przewoźnika Lingdong International, ale brakuje jednego znaku: może jest w nim literówka. Porównaj go z wiadomością o wysyłce.”

**Gdzie go znaleźć.** Klientami Lingdong są sprzedawcy w Chinach: na stronie znajdą logowanie do konta, kalkulator cen i instrukcję nadawania, ale poza oknem wyszukiwania nic nie jest przeznaczone dla odbiorców. To sklep zleca wysyłkę i podaje Ci numer, zazwyczaj w wiadomości o nadaniu lub w szczegółach zamówienia. Nie możesz go znaleźć? Zobacz [jak znaleźć numer śledzenia przesyłki](guide:find-tracking-number).

**Inne numery.** Strona śledzenia Lingdong wyszukuje paczkę także po numerze referencyjnym nadanym przez sklep, pokazywanym jako 参考号 (numer referencyjny). Lingdong sprzedaje również przesyłki [China Post](carrier:china-post), [PostNL](carrier:postnl), [UPS](carrier:ups), [DHL Express](carrier:dhl-express) i [FedEx](carrier:fedex), więc numer w formacie którejś z tych firm należy do niej: sprawdź [jak rozpoznać przewoźnika po numerze śledzenia](guide:tracking-number-formats).

## Statusy śledzenia Lingdong: co oznacza każdy wpis

Lingdong nie publikuje listy swoich statusów. Na jego stronie śledzenia wpisy pojawiają się po angielsku, wyłącznie po chińsku albo po chińsku z angielskim odpowiednikiem (po ukośniku `/` lub w nawiasach `【…】`). W kolejności, w jakiej spotyka je paczka, wraz ze statusem wyświetlanym w Peek, dopóki dany wpis jest najnowszy:

| Wpis na stronie Lingdong | Co to oznacza dla Ciebie | Peek pokazuje |
| --- | --- | --- |
| `货物电子信息已经收到` | Po chińsku: otrzymano elektroniczne dane przesyłki. Sprzedawca utworzył zlecenie, ale Lingdong nie ma jeszcze paczki. | „Zarejestrowana” |
| `Shipping Label Created` | Etykieta utworzona dla zamówienia, kilka godzin przed pierwszym skanem w magazynie Lingdong. | „Zarejestrowana” |
| `Shipment arrived at facility` | Paczka dotarła do magazynu Lingdong w Shenzhen: pierwszy fizyczny skan w rękach Lingdong. | „Przyjęta przez przewoźnika” |
| `Shipment departed from facility` | Opuściła ten magazyn w drodze za granicę. | „W transporcie” |
| `预计…航班/Flight scheduled for …`, `预计…号航班【Expect flight ….】`, `预计…航班/Scheduled flight on …` | Zarezerwowano lot: w miejscu kropek widnieje data lotu (w pierwszym wariancie jako miesiąc.dzień, np. `10.28` to 28 października). | „W transporcie” |
| `送往航司途中【On the way to the airline company】` | W drodze do linii lotniczej. | „W transporcie” |
| `航班已起飞【The flight has taken off.】`, `航班已启运/Package in transit to destination country` | Samolot wystartował. | „W transporcie” |
| `航班抵达【Flight arrival】`, `航班已落地，拆板中/Package arrived at destination country` | Samolot wylądował; drugi komunikat informuje dodatkowo o rozładunku palet lotniczych. | „W transporcie” |
| `拆板作业中，等待取货通知【Under destuffing, waiting for pickup instructions.】` | Po lądowaniu trwa rozładunek palet, a ładunek czeka na odbiór do odprawy celnej: Ty nie musisz niczego odbierać osobiście. | „W transporcie” |
| `货物在清关【Customs clearance in progress】` | Trwa odprawa celna w kraju docelowym po przylocie. | „W urzędzie celnym” |
| `已完成国内报关手续【Domestic customs clearance procedures have been completed.】`, `货物已清关完成/Import customs clearance complete`, `货物已完成清关【The goods have completed customs clearance】` | Urząd celny kraju docelowego zwolnił przesyłkę po przylocie, przed doręczeniem. Słowo *Domestic* oznacza tu kraj docelowy, a nie Chiny. | „W transporcie” |
| `货物已交服务商【The shipment has been transferred to the logistics partner.】`, `包裹交接给本地物流商处理/Received by local delivery company` | Po odprawie celnej Lingdong przekazał paczkę firmie, która doręcza ją na miejscu. | „W transporcie” |

Jedyny wpis wyłącznie po chińsku (`货物电子信息已经收到`) nie jest fizycznym skanem paczki: system Lingdong dopisuje go automatycznie dla zlecenia bez skanów z datą utworzenia zamówienia. W Peek wpisy dwujęzyczne chińsko-angielskie pokazują tylko swoją angielską część (np. `Flight arrival` albo `Customs clearance in progress`), natomiast `货物电子信息已经收到` pozostaje po chińsku.

Żaden z własnych wpisów Lingdong nie oznacza wydania kurierowi do doręczenia ani doręczenia pod drzwi: ostatni to przekazanie partnerowi po odprawie importowej. We **Włoszech** wyszukiwarka Lingdong może potem dopisywać skany [Poste Italiane](carrier:poste-italiane): `First parcel scan along Poste Italiane’s logistic network`, `In transit along Poste Italiane’s logistic network` i `In transito, la spedizione è in transito`, aż do `In consegna` („W doręczeniu”) i `Consegnata, la spedizione è stata consegnata` („Dostarczona”). W **Stanach Zjednoczonych** może pokazać `Arrived at GOFO Regional Hub`, czyli przyjęcie w regionalnym centrum [GOFO Express](carrier:gofo) („W transporcie”). W Polsce doręczeniem zajmuje się lokalny przewoźnik i dalsze kroki widać w jego śledzeniu.

## Kto doręcza paczki Lingdong w Polsce i jakie są opłaty celne

Na swojej stronie Lingdong wymienia usługi oferowane sprzedawcom w Chinach: linie małych paczek (专线小包) do USA, Wielkiej Brytanii, Unii Europejskiej, Australii, Meksyku, Kanady i Japonii; ePacket (E邮宝) i duże paczki China Post; PostNL (荷兰邮政); linie dla lekkich przesyłek gabarytowych; przesyłki ekspresowe UPS, DHL i FedEx, dla których działa jako agent, a także fracht lotniczy, magazynowanie i wysyłkę pojedynczych zamówień.

Lingdong nie podaje, która firma doręcza przesyłki na poszczególnych liniach w **Polsce**. O serwisie paczkowym China Post pisze jedynie, że dociera do ponad 200 krajów i regionów drogą lotniczą, lądową lub łączoną, ze śledzeniem na całej trasie. Lingdong nie publikuje czasów dostawy ani zasad dotyczących prób doręczenia, awizowania, punktów odbioru czy czasu przechowywania paczki: gdy przesyłkę przejmie przewoźnik w Polsce, obowiązują jego zasady.

**Cło i opłaty w Unii Europejskiej oraz w Polsce.** Według Komisji Europejskiej od 1 lipca 2026 r. zamówienia internetowe spoza UE o wartości do 150 € objęte są tymczasowym cłem w wysokości 3 € za każdą kategorię towaru; od 1 listopada 2026 r. dochodzi unijna opłata manipulacyjna (*Union handling fee*) w wysokości 2 € za kategorię towaru niezależnie od wartości przesyłki (jedna kategoria obejmuje towary o tej samej klasyfikacji taryfowej, tym samym opisie i — gdy podlega zgłoszeniu — tym samym pochodzeniu). Cło 3 € nie zmienia zasad poboru VAT do 150 €: jeśli sklep rozlicza VAT w procedurze IOSS, podatek płacisz przy zakupie i nie jest pobierany ponownie przy imporcie. Lingdong nie informuje, czy sprzedawca opłacił należności z góry.

W przypadku przesyłek spoza UE odprawianych w **Polsce** przez Pocztę Polską, Poczta Polska informuje o zmianach w opłatach celnych od 1 lipca 2026 r. oraz ostrzega przed fałszywymi wiadomościami podszywającymi się pod markę „Pocztex”, które nakłaniają do klikania linków, by wyłudzić dane osobowe i dane kart płatniczych — jeśli Twoją paczkę w Polsce odprawia inny przewoźnik, obowiązują jego oficjalne kanały. Więcej informacji w poradniku [paczka zatrzymana przez urząd celny](guide:customs).

## Paczka Lingdong nie dociera lub śledzenie stoi w miejscu

**Zatrzymane na `Shipment departed from facility`.** Ten skan oznacza wyjazd z magazynu Lingdong w Shenzhen. Później mogą pojawić się wpisy o locie i odprawie celnej, ale żaden własny wpis Lingdong nie dotyczy doręczenia, więc historia u Lingdong może się zatrzymać, podczas gdy paczka jedzie dalej. Zapytaj sprzedawcę, jaki przewoźnik doręcza paczkę w Polsce i pod jakim numerem śledzenia. Zobacz też [dlaczego śledzenie przesyłki się nie aktualizuje](guide:tracking-not-updating) oraz [śledzenie paczek z Chin](guide:tracking-from-china).

**Brak wpisów po przekazaniu partnerowi.** `货物已交服务商【The shipment has been transferred to the logistics partner.】` kończy odcinek obsługiwany przez Lingdong. Cztery dni po ostatnim wpisie Peek oznacza przesyłkę komunikatem z dniem tego wpisu („Brak aktualizacji od pon 12 paź”), nawet jeśli lokalny kurier już ją wiezie. Jeśli strona śledzenia Lingdong pokazuje dla Twojej paczki 渠道转单号 (numer przekazu kanału), którego Peek nie przejął automatycznie, dodaj go w Peek jako osobną przesyłkę; jeśli go nie ma, poproś o niego sprzedawcę.

**Tylko `货物电子信息已经收到`.** Sklep zarejestrował wysyłkę, ale paczka nie trafiła jeszcze do magazynu Lingdong. Gdy przez kilka dni nic się nie zmienia, zapytaj sprzedawcę, czy faktycznie ją nadał.

**Brak wyników.** Dla nieznanego numeru strona śledzenia Lingdong wyświetla jeden wiersz z tym numerem jako referencją i pustymi pozostałymi kolumnami. Peek traktuje taką odpowiedź jak zarejestrowane zlecenie bez skanów: nadal sprawdza przesyłkę i pokazuje „Oczekiwanie na przewoźnika Lingdong International”, nie prosząc o sprawdzenie numeru. Porównaj więc sam numer znak po znaku z wiadomością od sprzedawcy.

**Zagubiona, uszkodzona lub zwrócona.** Lingdong nie publikuje procedury reklamacyjnej dla odbiorców: jego strona warunków przewozu (运输条款) jest pusta. Na stronie widnieje przycisk czatu dotyczący problematycznych paczek, odszkodowań i skarg, ale umowę z Lingdong ma sprzedawca: o zwrot pieniędzy lub ponowną wysyłkę zwróć się do sklepu albo platformy zakupowej. Zobacz [zagubiona paczka](guide:lost-parcel) i [paczka zwrócona do nadawcy](guide:returned-to-sender).

## Lingdong: kontakt i obsługa klienta

Na stronie lingdong56.com, prowadzonej wyłącznie po chińsku:

- **Telefon:** dwa chińskie numery komórkowe w nagłówku strony oraz w zakładce kontaktu (联系我们), bez podanych godzin pracy ani języków obsługi.
- **QQ:** przyciski czatu na stronie (wyceny, duże zlecenia oraz problematyczne paczki, roszczenia i skargi) otwierają rozmowę w chińskim komunikatorze QQ.
- **Adres:** 深圳市宝安区福海街道展城社区福园二路10号濠和智创B栋625, w dzielnicy Bao'an w Shenzhen.

Lingdong nie podaje adresu e-mail ani biur poza Chinami. W każdej sprawie związanej z zamówieniem kontaktuj się ze sprzedawcą.

## Śledzenie paczki Lingdong z Peek

[Peek](/) od razu rozpoznaje dziesięciocyfrowy numer SZLD jako Lingdong International — żaden inny przewoźnik w katalogu nie używa takiego układu. Możesz też wkleić link do strony śledzenia Lingdong zawierający `documentCode=` i numer przesyłki.

Jeśli sprzedawca podał Ci wyłącznie własny numer referencyjny, wklej go mimo to, stuknij „Zmień” i wybierz Lingdong International spośród ponad 3500 przewoźników obsługiwanych przez Peek (znajdziesz go też wpisując 领东国际 lub LDGJ) albo użyj opcji „Zmień przewoźnika” przy zapisanej już paczce. Gdy Lingdong prowadzi przesyłkę pod innym własnym numerem, Peek wyświetli go pod etykietą „Lingdong International śledzi przesyłkę pod numerem”. Peek usuwa jednak myślniki, więc referencję z myślnikami trzeba wpisać bezpośrednio na stronie Lingdong.

Peek odczytuje bezpośrednio wyszukiwarkę Lingdong i pokazuje status, każdy etap z miejscem oraz — gdy dane Lingdong go zawierają — kraj docelowy. W dwujęzycznych wierszach chińsko-angielskich Peek wyświetla część angielską; `货物电子信息已经收到` pozostaje po chińsku, a skany Poste Italiane po włosku (gdy Poste Italiane widnieje dwa razy o tej samej godzinie i w tym samym miejscu z krótkim nagłówkiem `In transito` oraz pełnym zdaniem `In transito, la spedizione è in transito`, Peek zostawia tylko pełny wiersz). Ponieważ godziny Lingdong nie zawierają strefy czasowej, Peek odczytuje je według czasu pekińskiego (UTC+8) — z wyjątkiem `Shipping Label Created`, czytanego według czasu pacyficznego na każdej trasie, oraz skanów przypisanych do amerykańskiego miasta i stanu (jak w GOFO), czytanych według czasu lokalnego tego punktu — i przelicza na strefę Twojego urządzenia: skan z 17:00 w Chinach zobaczysz w Polsce o 10:00 zimą (o 11:00 w czasie letnim); na peektracker.com i w aplikacjach na iPhone'a i Androida pod historią widnieje dopisek „Godziny w twojej strefie czasowej: …”.

Peek wstrzymuje każdy wpis datowany po chwili sprawdzenia, a także wiersze zapisane ponad dwanaście godzin przed widniejącą w nich godziną, wiersze zapisane w tej samej sekundzie ponad sześć godzin przed własną godziną oraz wpisy funkcji niestandardowego śledzenia (轨迹定制), potrafiące umieścić paczkę za granicą zanim dotrze na lotnisko: to prognozy, a nie skany. Historia zaczyna się wtedy, aż do doręczenia lub zwrotu, od zdania „Inne strony mogą pokazywać więcej etapów, ale to tylko prognozy.”, a „Pokaż” wyświetla te wpisy na szaro, każdy z dopiskiem „Prognoza:” i dniem. Taki etap nigdy nie liczy się jako skan: nie zmienia statusu i nie wysyła powiadomień. Peek pomija imię i nazwisko odbiorcy, adres, wagę, liczbę paczek i potwierdzenie odbioru oraz nie pokazuje przewidywanej daty doręczenia, bo Lingdong jej nie podaje.

O ile rekord Lingdong nie wskazuje przewoźnika końcowego, któremu Peek może przekazać śledzenie, historia kończy się tam, gdzie kończy się u Lingdong. Peek rozpoznaje [Pocztę Szwajcarską](carrier:swiss-post), gdy rekord wymienia linię Swiss Post, zawiera numer Swiss Post i nie wskazuje kraju poza Szwajcarią i Liechtensteinem: przesyłka pokazuje wtedy „Doręcza: Swiss Post” (albo „Zgłoszona przewoźnikowi Swiss Post, jeszcze nieprzyjęta”, dopóki Swiss Post ma tylko zapowiedź bez własnego skanu), a po pierwszym skanie nie starszym niż ostatni wpis Lingdong Peek przechodzi do Swiss Post, zachowując wcześniejsze skany (sam Lingdong nie wymienia Swiss Post na stronie). Gdy w rekordzie numer SZLD zostanie zastąpiony innym numerem, Peek odpytuje pocztę kraju docelowego dla międzynarodowego numeru pocztowego (dwie litery, dziewięć cyfr, dwie litery) i przełącza śledzenie w ten sam sposób. Każdy inny numer lokalny otrzymany od sprzedawcy dodaj jako osobną przesyłkę.

Dopóki masz otwartą przesyłkę lub w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza status nawet co 10 minut, a w dniu statusu „W doręczeniu” nawet co 2 minuty (od 6:00 czasu lokalnego albo pół godziny przed zapowiedzianym oknem doręczenia, dopóki odpowiada bezpośrednio Lingdong lub przewoźnik końcowy; co 5 minut, jeśli zapytanie tego przewoźnika otwiera przeglądarkę; przez serwisy śledzenia co godzinę, co 3 godziny po dobie bez skanu i nawet co 15 minut w dniu doręczenia przy otwartej paczce lub włączonych powiadomieniach; po przekazaniu Peek sprawdza w tempie przewoźnika końcowego). W samym śledzeniu Lingdong status „W doręczeniu” ustawia wpis `In consegna` od Poste Italiane. Poza tymi warunkami oraz po 12 godzinach bez nowego skanu na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika” (albo po dwóch dniach na pozostałych etapach) Peek sprawdza co godzinę. Do wyboru masz powiadomienia: „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie” — spośród własnych wpisów Lingdong „Ważne etapy” obejmują `货物在清关【Customs clearance in progress】`. Peek nie skontaktuje się za Ciebie z Lingdong ani ze sprzedawcą.

## Najczęstsze pytania o śledzenie Lingdong

### Co to jest Lingdong International?

To 深圳市领东国际货运代理有限公司 (Shenzhen Lingdong International Freight Forwarding Co., Ltd.), w skrócie 领东国际 — firma spedycyjna z Shenzhen wysyłająca za granicę paczki chińskich sprzedawców internetowych. Jej numery zaczynają się od SZLD i można je śledzić w polu 快件查询 na lingdong56.com albo w Peek.

### Dlaczego śledzenie Lingdong zatrzymało się na „Shipment departed from facility”?

Ten wpis oznacza wyjazd paczki z magazynu Lingdong w Shenzhen. Dalej mogą pojawić się etapy lotu i odprawy celnej, ale żaden własny wpis Lingdong nie jest skanem doręczenia: w Polsce paczkę doręcza inna firma. Zapytaj sprzedawcę, jaki to przewoźnik i jaki masz u niego numer śledzenia.

### Ile idzie paczka Lingdong z Chin?

Lingdong nie publikuje czasów doręczenia na swojej stronie. Kieruj się terminem dostawy podanym przez sklep przy składaniu zamówienia.

### Czy za paczkę Lingdong trzeba zapłacić cło?

W Unii Europejskiej zamówienia spoza UE o wartości do 150 € podlegają od 1 lipca 2026 r. cłu 3 € za kategorię towaru, a od 1 listopada 2026 r. także unijnej opłacie manipulacyjnej 2 € za kategorię niezależnie od wartości, oprócz podatku VAT (chyba że został pobrany przy zakupie w IOSS). Lingdong nie podaje, które opłaty sprzedawca uregulował z góry.

### Do kogo zgłosić zaginioną paczkę Lingdong?

Do sklepu, w którym kupiłeś towar: to on zlecił wysyłkę w Lingdong i jest stroną umowy. Lingdong podaje wyłącznie dwa chińskie numery komórkowe oraz czat QQ przeznaczone dla nadawców.

:::sources
- [Lingdong: 深圳市领东国际货运代理有限公司 (strona główna)](http://www.lingdong56.com/) – pole wyszukiwania 快件查询, oferta dla sprzedawców, telefony, przyciski QQ, adres
- [Lingdong: 公司介绍 (o firmie)](http://www.lingdong56.com/gsjs.html) – spedycja międzynarodowa, małe paczki, przesyłki ekspresowe, fracht lotniczy
- [Lingdong: 主营业务 (główne usługi)](http://www.lingdong56.com/zyyw.html) – linie według krajów, ePacket, PostNL, duże paczki China Post, ponad 200 krajów i rodzaje transportu
- [Lingdong: 快递业务 (przesyłki ekspresowe)](http://www.lingdong56.com/kdyw.html) – agent UPS, DHL i FedEx
- [Lingdong: 联系我们 (kontakt)](http://www.lingdong56.com/lxwm.html) – adres w dzielnicy Bao'an (Shenzhen) i telefony
- [Lingdong: 运输条款 (warunki przewozu)](http://www.lingdong56.com/ystk.html) – strona nie zawiera opublikowanych warunków
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – format SZLD, referencje i linki, angielska część wpisów dwujęzycznych, etapy zapisane z wyprzedzeniem pokazywane na szaro, strefy czasowe oraz przekazanie do Swiss Post lub przewoźnika końcowego
- [Komisja Europejska: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – tymczasowe cło 3 € do 150 € od 1 lipca 2026 r. oraz opłata manipulacyjna 2 € od 1 listopada 2026 r.
- [Komisja Europejska: The temporary EUR 3 customs duty and the Union handling fee](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – definicja kategorii towaru, opłata 2 € niezależnie od wartości, bez zmian w IOSS do 150 €
- [Poczta Polska: Zmiany w opłatach dla przesyłek spoza Unii Europejskiej od 1 lipca 2026 r.](https://www.poczta-polska.pl/news/zmiany-w-oplatach-dla-przesylek-spoza-unii-europejskiej-od-1-lipca-2026-r/) – odprawa i opłaty dla przesyłek spoza UE obsługiwanych przez Pocztę Polską w Polsce
- [Poczta Polska: Uwaga na fałszywe wiadomości „Pocztex” – groźna kampania phishingowa](https://www.poczta-polska.pl/news/uwaga-na-falszywe-wiadomosci-pocztex-grozna-kampania-phishingowa/) – ostrzeżenie przed fałszywymi SMS-ami o nieudanym doręczeniu, wyłudzającymi dane osobowe i dane kart płatniczych
:::
