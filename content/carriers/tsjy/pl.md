---
title: Śledzenie TSJY (通世集运): numery zamówień i statusy
description: Śledź skonsolidowaną paczkę TSJY (通世集运) z Chin: jak wygląda numer TSJY…CN, co oznaczają chińskie statusy i kiedy przesyłkę przejmuje 17FEIA.
slug: sledzenie-tsjy
published: 2026-11-23
updated: 2026-11-23
---

TSJY (通世集运) to usługa konsolidacji przesyłek w Chinach prowadzona przez 苏州通世供应链有限公司, firmę logistyczną z Suzhou. Kupujesz na chińskich platformach takich jak Taobao, Tmall, Pinduoduo, JD lub Douyin, a sklepy wysyłają towary do magazynu TSJY w Suzhou. TSJY przechowuje je, pakuje razem i wysyła za granicę. Swój numer TSJY sprawdzisz na [stronie TSJY](https://www.tsjy56.com/) w zakładce 订单查询 (wyszukiwanie zamówienia) lub po wklejeniu go w pole powyżej.

## Numer śledzenia TSJY: jak wygląda

Strona TSJY nie opisuje formatu swoich numerów, ale mają one jeden stały wzór. TSJY to inicjały nazwy 通世集运, Tongshi Jiyun. Wszystkie poniższe przykłady są wymyślone:

| Numer | Co to jest |
| --- | --- |
| `TSJY482917365CN` | TSJY, dziewięć cyfr i CN: numer zamówienia TSJY. |
| `TSJY48291736CN` | Tylko osiem cyfr: to nie format TSJY i Peek nie rozpoznaje TSJY. Sprawdź, czy nie brakuje cyfry. |
| `FEIKM4829173650YQ` | FEI, dwie litery, dziesięć cyfr i YQ: numer [17FEIA](carrier:17feia), linii przewożącej część przesyłek TSJY za granicę. |

W Peek małe litery, spacje i myślniki nie mają znaczenia.

**Gdzie go znaleźć.** To numer zlecenia wysyłki (订单号), które składasz na stronie zamówień TSJY (网页下单) lub w WeChat (微信下单), gdy towary dotrą do magazynu. Jeśli wysyłkę zlecił ktoś inny, poproś tę osobę o numer.

**Dwa numery.** Wyszukiwarka TSJY przyjmuje też numer linii (运单号). Na telefonie karta każdego zamówienia pokazuje numer TSJY, a po ukośniku numer linii. Kliknij kartę, aby otworzyć wpisy śledzenia (轨迹详情).

## Statusy śledzenia TSJY wyjaśnione

Serwis TSJY działa po chińsku. Na telefonie każda karta wyniku podaje jeden krótki status dla całego zamówienia:

| Karta podaje | Co to oznacza dla ciebie |
| --- | --- |
| `等待处理` | Oczekuje na realizację: TSJY ma twoje zlecenie wysyłki. |
| `开始拣货`, `正在拣货`, `拣货完成`, `开始打包`, `已称重`, `已录单` | Magazyn kompletuje towary, pakuje i waży paczkę oraz rejestruje zlecenie. |
| `等待出库`, `已出库`, `运输途中` | Czeka na wydanie z magazynu; opuściła magazyn; w transporcie. |
| `已送达`, `已完成` | Doręczona; zakończona. |
| `运输异常` | Problem w drodze. Zapytaj TSJY. |

Peek nie odczytuje statusu karty, lecz wpisy śledzenia (轨迹) w środku. Najpierw pojawiają się własne etapy TSJY w magazynie, po chińsku:

| TSJY podaje | Co to oznacza dla ciebie | Peek pokazuje |
| --- | --- | --- |
| `订单已提交，等待系统确认` | Zamówienie złożone, czeka na potwierdzenie systemu. Nic jeszcze nie skompletowano. | „Zarejestrowana” |
| `仓库开始拣货` | Magazyn zaczyna kompletować towary. | „Przyjęta przez przewoźnika” |
| `仓库拣货完成` | Kompletowanie zakończone. | „Przyjęta przez przewoźnika” |
| `订单打包完成，等待出库` | Spakowana, czeka na wydanie z magazynu. Potrafi pojawić się dwa razy w ciągu minuty. | „Przyjęta przez przewoźnika” |
| `订单已出库，开始国际运输` | Opuściła magazyn TSJY i rozpoczyna podróż międzynarodową. | „W transporcie” |

Na linii 17FEIA serwis TSJY kopiuje następnie wpisy 17FEIA po angielsku:

| Linia podaje | Co to oznacza dla ciebie | Peek pokazuje |
| --- | --- | --- |
| `Item information received.` | 17FEIA otrzymało dane zamówienia. TSJY może nadal mieć paczkę. | „Przyjęta przez przewoźnika” |
| `The package is on the way to the facility.`, `Warehouse receipt.` | W drodze do magazynu 17FEIA w Chinach, a następnie tam przyjęta. | „W transporcie” |
| `The order has information pending customer confirmation.`, `Order Confirmed.` | Magazyn 17FEIA wstrzymuje paczkę do potwierdzenia zamówienia. | „W transporcie” |
| `Order dispatched from warehouse.` | Opuściła magazyn 17FEIA. | „W transporcie” |
| `Export declaration in progress.` | Trwa zgłoszenie eksportowe z Chin — rutynowy krok przed wylotem. Urząd celny jej nie blokuje i nie jest to cło w twoim kraju. | „W transporcie” |
| `Flight has departed.` | Samolot wystartował. | „W transporcie” |
| `Flight delayed.` | Lot jest opóźniony. Paczka się nie przemieściła. | status poprzedniego wpisu |
| `Flight Arrived at Transit Hub.`, `Flight Departed from Transit Hub.` | Przystanek tranzytowy po drodze. | „W transporcie” |
| `Flight Arrived at Clearance Port.` | Wylądowała tam, gdzie przejdzie odprawę importową. | „W transporcie” |
| `Broker picked up Shipment and delivered to warehouse.` | Agencja celna zabrała ją do swojego magazynu do odprawy. Nie jest to doręczenie do ciebie. | „W urzędzie celnym” |
| `Customs Clearance is in progress.` | Urząd celny importowy sprawdza przesyłkę. | „W urzędzie celnym” |
| `Customs Clearance Completed.` | Odprawa celna zakończona. | „W transporcie” |

Na innej linii może pojawić się także jej wpis: `已下单` (zamówienie złożone), po chińsku i bez miejsca, między pakowaniem w TSJY a wydaniem. Podobnie jak `Item information received.` z 17FEIA pojawia się, gdy TSJY nadal ma paczkę: „Przyjęta przez przewoźnika”. Po wydaniu z TSJY wpis `已出库` (wydano z magazynu), również po chińsku bez miejsca, oznacza, że magazyn tej linii wysłał paczkę dalej dzień po przyjęciu: „W transporcie”.

Peek pomija wpis 17FEIA z szacowaną datą wylotu, który nie jest skanem, oraz każdy wpis z datą z przyszłości. Pozostałe wpisy, na przykład lokalnego kuriera, odczytuje po słowach kluczowych, takich jak doręczenie lub odprawa celna.

**Godziny.** Strona szczegółów TSJY wyświetla każdą godzinę według zegara twojego urządzenia i Peek pokazuje własne kroki TSJY tak samo. Wpisy magazynowe 17FEIA Peek odczytuje w czasie pekińskim (UTC+8), tak jak zapisuje je 17FEIA. Gdy miejsce późniejszego wpisu kończy się kodem kraju, np. `(DE)`, Peek przyjmuje strefę czasową tego kraju, jeśli ma on jedną strefę.

## Kto doręcza paczki TSJY

Strona TSJY podaje, że wysyła do ponad 220 krajów i regionów, ekspresowo lotniczo (加急空运) lub ekonomicznie drogą morską (经济海运). Nie wskazuje, kto doręcza w danym kraju, ani nie podaje czasów dostawy. Strona szczegółów nazywa linię (渠道, kanał), którą jedzie zamówienie.

Jedną linię Peek zna z nazwy: [17FEIA](carrier:17feia) (一起飞), chińską firmę logistyczną z własnym śledzeniem na www.17feia.com. Śledzenie 17FEIA może wskazać firmę doręczającą w twoim kraju wraz z jej numerem.

TSJY nie publikuje zasad prób doręczenia ani punktów odbioru — ustala je doręczający przewoźnik. Zobacz [nieudane doręczenia](guide:missed-delivery).

**Cło w UE.** Towary kupione online i sprowadzane do UE spoza niej objęte są tymczasowym cłem 3 € za pozycję dla paczek do 150 € od 1 lipca 2026 roku oraz unijną opłatą manipulacyjną 2 € za pozycję niezależnie od wartości od 1 listopada 2026 roku. Jedną pozycję stanowią towary o tej samej klasyfikacji taryfowej, opisie oraz — gdy wymagane — pochodzeniu. Obie opłaty dotyczą sprzedaży na odległość: towarów wysyłanych przez sprzedawcę lub w jego imieniu. Wytyczne Komisji wskazują, że towary nie są wysyłane przez sprzedawcę, gdy klient organizuje transport bez udziału sprzedawcy, co może dotyczyć samodzielnego zlecenia TSJY — decyduje urząd celny w twoim kraju. Strona TSJY nie wyjaśnia, jak opłaca się cło lub opłaty. Zobacz [paczki zatrzymane na cle](guide:customs).

## Paczka TSJY bez aktualizacji, zaginiona lub zwrócona

W TSJY to zazwyczaj ty jesteś klientem, bo zleciłeś wysyłkę. Przy opóźnieniu, zaginięciu lub uszkodzeniu skontaktuj się najpierw z TSJY.

**Towary jeszcze poza magazynem.** Zanim zakupy dotrą do TSJY, jadą z chińskimi kurierami sklepów: śledź je tam, gdzie kupiłeś. Strona TSJY podaje, że jej śledzenie zaczyna się po przyjęciu paczki w magazynie i trwa do podpisania odbioru.

**Brak danych dla numeru.** Dla nieznanego numeru strona mobilna TSJY wyświetla `未找到相关的轨迹信息。` (nie znaleziono informacji o śledzeniu). Sprawdź literówki. Paczka pokazuje „Oczekiwanie na przewoźnika TSJY”, a Peek sprawdza dalej. Jeśli po dwóch dniach TSJY nadal nie zna numeru i nikt inny nie odpowiedział, Peek pokazuje „Po dwóch dniach przewoźnik nadal nie zna tego numeru. Sprawdź go, wybierz innego przewoźnika lub usuń przesyłkę.” i sprawdza odtąd codziennie, a po tygodniu od dodania raz w tygodniu.

**Utknęła w magazynie.** Jeśli własne kroki TSJY zatrzymają się na kilka dni, zapytaj obsługę klienta.

**Cisza po wylocie.** Po `Flight has departed.` może nie być nic nowego aż do lądowania lub odprawy celnej. Gdy przesyłka nadal na etapie „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie” nie ma nowego wpisu od czterech dni, Peek oznacza ją komunikatem „Brak aktualizacji od czterech dni” (lub „Brak aktualizacji od …”), wyjaśnia: „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika TSJY.” i wyświetla „Poproś przewoźnika TSJY o pomoc”. Zobacz [dlaczego śledzenie przestaje się aktualizować](guide:tracking-not-updating) oraz [śledzenie paczek z Chin](guide:tracking-from-china).

**Zaginiona lub uszkodzona.** Strona TSJY obiecuje pełną rekompensatę za zaginięcie lub uszkodzenie (丢破必赔), ale publiczne strony nie podają zasad ani terminów: skontaktuj się od razu z obsługą klienta i zachowaj pudełko oraz zawartość. Zobacz [zagubione paczki](guide:lost-parcel) i [uszkodzone paczki](guide:damaged-parcel).

**Doręczona, ale jej nie ma, lub zwrócona.** Sprawdź wokół domu i zapytaj sąsiadów, a potem powiadom TSJY. Strona nic nie mówi o zwrotach. Zobacz [doręczona, ale nieotrzymana](guide:delivered-not-received) i [zwrócona do nadawcy](guide:returned-to-sender).

## Obsługa klienta i kontakt TSJY

Na [stronie głównej TSJY](https://www.tsjy56.com/), w sekcji 客服中心 (centrum obsługi klienta):

- **Telefon i e-mail:** numer obsługi klienta (客户服务电话) oraz adres e-mail (客服邮箱). Godziny pracy nie są podane.
- **WeChat:** kod QR do dodania obsługi klienta WeChat (添加微信客服), a także oficjalne konto usługowe i miniaplikacja WeChat.
- **Magazyn:** w Huaqiao (花桥镇), Suzhou, z pełnym adresem na stronie.

Strona jest wyłącznie po chińsku i nie wspomina o obsłudze po angielsku.

## Śledzenie paczki TSJY w Peek

[Peek](/) od razu rozpoznaje TSJY po numerze `TSJY…CN` („Wykryty przewoźnik”): żaden inny przewoźnik obsługiwany przez Peek nie ma tego formatu. Działa też link do wyników TSJY z numerem TSJY po `nos=`; przy kilku numerach Peek bierze pierwszy. Link TSJY zawierający numer linii albo numer spoza formatu TSJY nie trafia pod TSJY: Peek odczytuje ten kod osobno, jak po wklejeniu samego numeru.

Każdy inny numer również wklej śmiało: Peek obsługuje ponad 4500 przewoźników. Gdy kształt pasuje do kilku firm, podczas sprawdzania widać „Sprawdzanie usług śledzenia…”; gdy nie pasuje do żadnej, Peek sprawdza numer po dodaniu. Jeśli Peek nie wskaże przewoźnika, kliknij „Zmień” i wybierz go w sekcji „Wszyscy przewoźnicy”, albo użyj później „Zmień przewoźnika”. Gdy wybierzesz TSJY dla innego numeru, Peek informuje, że TSJY „nie wydaje takich numerów”.

Peek odczytuje system TSJY: każdy wpis z miejscem i godziną. Pomija nadanie, kraj docelowy i nazwę linii. TSJY nie podaje daty dostawy, wagi, kodu odbioru ani podpisu odbiorcy. Status w Peek odpowiada najnowszemu wpisowi, który rozumie.

Gdy TSJY podaje numer 17FEIA albo numer linii wskazujący na przewoźnika znanego Peek, Peek pyta tego przewoźnika. Dopóki ten nie pokaże skanu co najmniej tak świeżego jak najnowszy w TSJY, Peek zostaje na śledzeniu TSJY, pokazując już, kto doręcza („Doręcza: 17FEIA”, lub „Zgłoszona przewoźnikowi 17FEIA, jeszcze nieprzyjęta”, gdy 17FEIA ma tylko zgłoszenie bez własnego skanu) wraz z jego numerem. Potem Peek śledzi przesyłkę u niego i zachowuje historię TSJY.

Dopóki masz paczkę otwartą w Peek albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza ją nawet co 10 minut, dopóki własny system TSJY odpowiada (gdy 17FEIA lub lokalny kurier przejmuje przesyłkę, Peek przechodzi na rytm tego przewoźnika — w tym nawet co 2 minuty od 6:00 czasu lokalnego w dniu doręczenia, jeśli jego własny system odpowiada). Po dwóch dniach bez wieści (po 12 godzinach, gdy przesyłka nadal ma status „Zarejestrowana” lub „Przyjęta przez przewoźnika”) Peek sprawdza co godzinę, a jeśli przez siedem dni pozostaje na etapie „Zarejestrowana” — co 6 godzin. Może wysyłać powiadomienia „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek nie kontaktuje się w twoim imieniu z TSJY, 17FEIA ani ze sklepami.

## Pytania o śledzenie TSJY

### Czym jest TSJY (通世集运)?

Usługą konsolidacji przesyłek (集运) dla osób za granicą kupujących na chińskich stronach. Według swojej strony działa od 2005 roku, przechowuje paczki bezpłatnie przez 180 dni i wysyła do ponad 220 krajów i regionów.

### Gdzie mogę śledzić numer TSJY?

Na [stronie TSJY](https://www.tsjy56.com/) w zakładce 订单查询: wpisz numer TSJY albo numer linii i kliknij 查询. Albo wklej go do Peek.

### Co oznacza 订单打包完成，等待出库?

Po chińsku to „zamówienie spakowane, czeka na wydanie z magazynu”: paczka jest spakowana w magazynie TSJY i czeka na linię wysyłkową. Peek pokazuje „Przyjęta przez przewoźnika”. Następnie pojawia się `订单已出库，开始国际运输`: wyszła z magazynu i ruszyła w drogę za granicę.

### Dlaczego moja paczka TSJY ma numer 17FEIA?

[17FEIA](carrier:17feia) to linia przewożąca paczkę za granicę: karta wyników TSJY podaje jej numer `FEI…YQ` po ukośniku. Gdy 17FEIA dogoni historię, Peek przełącza się na nią.

### Jak skontaktować się z TSJY?

Przez sekcję 客服中心 na stronie głównej: numer telefonu, adres e-mail i WeChat, wszystko po chińsku.

:::sources
- [TSJY: 通世集运 (home page)](https://www.tsjy56.com/) – firma, usługi, obietnica rekompensaty, zakres śledzenia, wyszukiwanie zamówień i wyniki na telefonie, zamawianie, kontakty, magazyn
- [TSJY: 轨迹查询 (tracking results)](https://www.tsjy56.com/orderResult.html) – wyniki na telefonie: karty z oboma numerami, statusy kart, komunikat braku wyników
- [TSJY: 轨迹详情 (track details)](https://www.tsjy56.com/orderDetail.html) – nazwa i numer kanału (渠道), wpisy śledzenia, godziny według zegara urządzenia
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – cło 3 € od 1 lipca 2026, unijna opłata manipulacyjna 2 € od 1 listopada 2026
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € za pozycję niezależnie od wartości, czym jest pozycja, sprzedaż na odległość i transport organizowany przez klienta
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – format numeru, link, komunikaty i etapy, strefy czasowe, przekazanie do linii, co odczytuje Peek
:::
