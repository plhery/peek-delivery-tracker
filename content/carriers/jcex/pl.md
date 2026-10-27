---
title: JCEX śledzenie przesyłki: numer JCZ, statusy, kto doręcza
description: Paczka JCEX z Chin: jak wygląda numer JCZ, co znaczą statusy po angielsku i chińsku, kto doręcza ją w Polsce i do kogo pisać, gdy utknie.
slug: jcex-sledzenie
published: 2026-10-23
updated: 2026-10-27
---

JCEX (佳成国际, Jiacheng International Logistics) to chińska firma logistyczna z Hangzhou, która wysyła za granicę paczki sprzedawców internetowych, a w kraju odbiorcy przekazuje je przewoźnikowi na ostatni odcinek. Paczkę śledzisz na stronie JCEX, [track.jcex.com](https://track.jcex.com/), albo wklejasz numer w pole powyżej. Numer dostajesz od sklepu, razem z danymi wysyłki zamówienia.

## Numer przesyłki JCEX: jak wygląda

JCEX nie opisuje formatu numeru, ale jego listy przewozowe zaczynają się od JCZ, a kończą na DH. Przykłady z wymyślonymi cyframi:

| Numer | Co to jest |
| --- | --- |
| `JCZ0000000000DH` | JCZ, 10 cyfr i DH: numer JCEX. |
| `JCZ000000000DH` | Tylko 9 cyfr: tak numer JCEX nie wygląda. Sprawdź, czy nie brakuje cyfry. |

W Peek wielkość liter i spacje nie mają znaczenia. Strona JCEX przyjmuje do 100 numerów naraz, oddzielonych przecinkami.

**Gdzie go znaleźć.** JCEX tego nie opisuje. Paczkę nadaje sprzedawca, więc numer jest w mailu o wysyłce albo przy zamówieniu. Nie ma go tam? Zobacz, [gdzie jeszcze szukać numeru przesyłki](guide:find-tracking-number).

## JCEX śledzenie: co znaczą statusy

Strona śledzenia JCEX jest tylko po chińsku, ale same linie bywają po angielsku, po chińsku albo w obu językach naraz. Każda ma przesunięcie względem UTC, godzinę i zwykle miejsce. Gdy JCEX ma linię w dwóch wersjach, jego strona pokazuje chińską, a Peek angielską: `签收` to `Delivered`, `正在派送` to `out for delivery`, a `预报订单信息已收到` to `Shipment order info received`. Poniżej linie tak, jak pokazuje je Peek, w kolejności, w jakiej zwykle spotyka je paczka:

| JCEX pisze | Co to znaczy dla Ciebie |
| --- | --- |
| `Shipment order info received` | Sprzedawca przesłał JCEX dane zamówienia. JCEX nie ma jeszcze paczki. |
| `Arrive at the delivery point` | Magazyn JCEX w Chinach przyjął paczkę. To nie punkt odbioru w Twojej okolicy. |
| `Item outbound in sorting center` | Paczka wyjechała z sortowni JCEX. |
| `Departed from origin facility to airport` | Jedzie na lotnisko. |
| `Flight dispatched（航班已飞）` | Samolot z paczką wystartował. |
| `Flight arrived（航班已落地）` | Samolot wylądował. |
| `Customs held（清关中）` | Odprawa celna przywozowa. Chiński dopisek znaczy „trwa odprawa”: to nie musi oznaczać problemu. |
| `Clearance processing complete（清关完成）` | Odprawa zakończona. |
| `Handed over to the trucking company, waiting for transport（已交卡车公司 待运输）` | Paczka czeka na ciężarówkę do przewoźnika, który ją doręczy. |
| `Delivered to warehouse, waiting to be scanned（已交仓，待扫描）` | Trafiła do magazynu przewoźnika doręczającego, nie do Ciebie. |
| `CONSIGNMENT RECEIVED BY OVERSEAS FORWARDER` | Przewoźnik doręczający przejął paczkę. |
| `out for delivery` | Paczka jest w doręczeniu. |
| `Delivered` | Paczka została doręczona. |

`The order information has been sent` i `货物电子信息已经收到` znaczą to samo co pierwszy wiersz, a `到达收货点(包裹已入库)` – że paczkę przyjął punkt przyjęć JCEX w Chinach. Linia `under clearance` to odprawa w kraju docelowym, a `CUSTOMS CLEARANCE COMPLETE` jej koniec. `InTransit_Arrival` i `InTransit_Departure` oznaczają przyjazd do hubu w Hongkongu i wyjazd z niego, a `turck transferring（卡车运输中）` (pisownia JCEX), że paczka jedzie ciężarówką. W znanych historiach JCEX po przekazaniu pojawiały się też linie przewoźnika doręczającego, w jego języku: w paczkach do USA angielskie linie USPS, takie jak `ARRIVAL AT POST OFFICE`, a w paczkach do Francji francuskie linie Colissimo.

Nad liniami strona JCEX pokazuje kategorię paczki z krótkim objaśnieniem: `查询不到` (brak informacji o śledzeniu), `收到信息` (paczka czeka, aż odbierze ją przewoźnik), `运输中` (w drodze), `到达待取` (dotarła do punktu w kraju docelowym i czeka na odbiór), `派送途中` (w doręczeniu), `投递失败` (nieudana próba doręczenia), `成功签收` (doręczona), `可能异常` (możliwy problem w drodze) albo `运输过久` (w drodze bardzo długo i wciąż niedoręczona).

Uwaga na `已签收` (doręczona): JCEX zapisuje jako doręczenie przekazanie paczki do magazynu przewoźnika, więc jego strona może pokazać tę kategorię, choć paczkę ma jeszcze przewoźnik. Czytaj najnowsze linie. Ogólne etapy drogi paczki opisuje poradnik o [śledzeniu paczki z Chin](guide:tracking-from-china).

## Kto doręcza paczki JCEX w Polsce

JCEX sam nie doręcza w Polsce i nie podaje, z kim tu współpracuje. Pisze tylko, że małe paczki (do 2 kg, samolotem) doręcza poczta, a przesyłki ekspresowe od 0,5 do 20 kg poczta albo firma kurierska.

Kto ma Twoją paczkę, sprawdzisz tak:

- **Najnowsze linie.** Po `CONSIGNMENT RECEIVED BY OVERSEAS FORWARDER` albo `Delivered to warehouse, waiting to be scanned（已交仓，待扫描）` paczkę ma przewoźnik doręczający. Jego własne linie i miejsca skanów mogą zdradzić, kto to jest.
- **Drugi numer.** Gdy JCEX go ma, jego strona pokazuje obok numeru listu przewozowego („运单号”) drugi, „跟踪单号” (numer śledzenia); w znanych historiach go nie było. Jeśli go widzisz, wklej go do Peek. Gdy Peek nie rozpozna przewoźnika po samym kształcie numeru, dodaj go mimo to: Peek śledzi ponad 3500 przewoźników, a właściwego wybierzesz przyciskiem „Zmień”.
- **Sprzedawca.** To on nadał paczkę i może zapytać JCEX. Poproś go o nazwę przewoźnika w Polsce i numer przesyłki u niego.

JCEX nie publikuje własnych zasad prób doręczenia, awizo, punktów odbioru ani czasu przechowania: to sprawa przewoźnika, który doręcza w Polsce. Jeśli to [Poczta Polska](carrier:poczta-polska) albo [InPost](carrier:inpost), odbiór i czas na odbiór opisują nasze strony o tych przewoźnikach.

## Cło za paczkę JCEX z Chin

Paczka z Chin przychodzi spoza Unii, więc musi przejść odprawę celną. Od 1 lipca 2026 r. do 1 lipca 2028 r. obowiązuje cło 3 euro za pozycję w przesyłce o łącznej wartości do 150 euro, gdy sprzedawca rozlicza VAT w systemie IOSS albo paczka jest przesyłką pocztową, podaje KAS. Od 1 listopada 2026 r. Unia będzie do tego pobierać opłatę manipulacyjną 2 euro za pozycję, zapowiada Komisja Europejska.

Czy cło i VAT są już w cenie, czy zapłacisz je przy odbiorze, zależy od sklepu i przewoźnika. Wyjaśnia to poradnik [paczka zatrzymana przez urząd celny](guide:customs). W historii JCEX odprawę widać jako `Customs held（清关中）` albo `under clearance` (na stronie JCEX `进口清关中`).

## Paczka JCEX nie dochodzi albo utknęła

**Do kogo się zwrócić.** Klientami JCEX są firmy, które u niego nadają paczki, a nie odbiorcy. JCEX nie publikuje dla odbiorców ani regulaminu, ani drogi reklamacji. Przy spóźnionej, zgubionej, uszkodzonej albo zwróconej paczce pisz więc do sklepu albo platformy, na której kupowałeś: to z nimi masz umowę i to oni mogą oddać pieniądze albo wysłać towar jeszcze raz.

**Brak śledzenia.** Dopóki JCEX nie zeskanuje paczki, a także przy numerze, którego nie zna, jego strona pokazuje kategorię `查询不到` i napis `暂无轨迹详情` (brak szczegółów śledzenia). Jeśli to się nie zmienia, sprawdź numer u sprzedawcy.

**Bez zmian od dawna.** JCEX nie podaje, po ilu dniach paczka trafia do `运输过久`. Gdy nic się nie dzieje, napisz do sprzedawcy, który może zapytać JCEX. Więcej w poradniku [status przesyłki się nie zmienia](guide:tracking-not-updating).

**Doręczona, a jej nie ma.** Sprawdź, czy linia o doręczeniu pochodzi od przewoźnika w Polsce, a nie jest przekazaniem do jego magazynu. Jeśli doręczenie zgłosił przewoźnik, zajrzyj do poradnika [paczka doręczona, a jej nie ma](guide:delivered-not-received).

**Reklamacja u przewoźnika w Polsce.** Jeśli w Polsce doręczał operator pocztowy, a problem dotyczy jego usługi, reklamację składa się u niego. Według UKE może to zrobić nadawca, a adresat dopiero wtedy, gdy przesyłka zostanie mu doręczona albo nadawca zrzeknie się na jego rzecz prawa do dochodzenia roszczeń. Termin to 12 miesięcy od nadania, a przy powszechnej usłudze pocztowej w obrocie zagranicznym 6 miesięcy. Jeśli odpowiedź operatora Cię nie zadowoli, możesz poprosić o interwencję Prezesa UKE, skorzystać z bezpłatnego pozasądowego rozwiązywania sporów albo pójść do sądu. UKE zajmuje się usługami pocztowymi, nie JCEX ani sklepem.

## JCEX kontakt: telefon i e-mail

Stan na 23 października 2026: JCEX podaje jeden zestaw kanałów, wszystkie w Chinach.

- **Infolinia:** 400-887-1083, chiński numer usługowy, od poniedziałku do piątku w godz. 9:00–18:00 czasu chińskiego. W weekendy i święta nieczynna.
- **E-mail:** cs@jcex.com.cn, który stopka strony nazywa adresem do skarg („投诉邮箱”, na angielskiej stronie „Complaint Email”).
- **WeChat:** konto obsługi klienta ze stopki strony, czynne w tych samych godzinach.
- **Siedziba:** Jiacheng International, Tiehui Development Centre, budynek T4, 8. piętro, 520 Jiuhe Road, dzielnica Shangcheng, Hangzhou, prowincja Zhejiang, Chiny.

Strona kontaktowa wymienia też oddziały w całych Chinach, ale nie podaje żadnego kontaktu dla odbiorców za granicą. W sprawie zamówienia pytaj sklep, w sprawie doręczenia przewoźnika w Polsce.

## Śledzenie paczki JCEX w Peek

[Peek](/) od razu rozpoznaje JCEX po numerze `JCZ…DH`: takich numerów nie ma żaden inny przewoźnik z jego katalogu. Zadziała też wklejony link z track.jcex.com z `?nums=` i numerem. Peek śledzi tylko numery JCZ: jeśli masz od JCEX inny numer, poproś sprzedawcę o numer listu przewozowego.

Peek odczytuje śledzenie JCEX i pokazuje status, historię z miejscami skanów na mapie, godzinę doręczenia i kraj docelowy. Linie pokazuje w wersji angielskiej, jeśli JCEX ją ma, a linie przewoźnika doręczającego tak, jak je zapisał. `Delivered` pokazuje po swojemu, jako „Dostarczona”. `Arrive at the delivery point` i przekazanie do magazynu przewoźnika liczą się jako „W transporcie”, nie jako „Do odbioru” ani „Dostarczona”. Godzinę każdego skanu Peek odczytuje na zegarze, który podaje JCEX (chińskim albo kraju doręczenia), i pokazuje ją w Twojej strefie czasowej.

JCEX nie podaje nazwy przewoźnika doręczającego, a Peek nie odczytuje pola „跟踪单号”, więc śledzi paczkę numerem JCEX do końca i nie przełącza się na przewoźnika w Polsce. Nie pokazuje też przewidywanej daty doręczenia, nazwy usługi ani potwierdzenia doręczenia.

Śledzenie jest sprawdzane w dzień co 10 minut, a co 2 minuty, gdy paczka jest w doręczeniu; w nocy co godzinę lub dwie. Powiadomienia możesz dostawać o każdym skanie („Wszystkie aktualizacje”), tylko o ważnych etapach („Tylko ważne”) albo „Tylko w dniu dostawy”. Peek nie skontaktuje się za Ciebie z JCEX ani ze sklepem.

## Pytania o śledzenie JCEX

### Czy JCEX to wiarygodna firma?

Tak. JCEX to marka Jiacheng International Logistics, firmy założonej w 2000 roku, z siedzibą w Hangzhou. Od 2021 roku ma w niej udziały Huamao Logistics, spółka notowana na giełdzie w Szanghaju. JCEX pisze też, że jest jednym z dostawców logistycznych wyznaczonych przez platformy takie jak Alibaba.com, Cainiao i DHgate.

### Co znaczy „Arrive at the delivery point” w JCEX?

To znaczy, że magazyn JCEX w Chinach przyjął paczkę. Ta linia pojawia się na początku historii, przed lotem, i nie ma nic wspólnego z punktem odbioru w Polsce.

### Dlaczego JCEX pokazuje doręczenie, a paczki nie mam?

JCEX zapisuje jako doręczenie przekazanie paczki do magazynu przewoźnika w kraju docelowym: linia `Delivered to warehouse, waiting to be scanned（已交仓，待扫描）` znaczy, że paczkę ma teraz przewoźnik doręczający. Doręczenie do Ciebie potwierdzi dopiero ten przewoźnik.

### Kto doręcza paczki JCEX w Polsce?

JCEX tego nie podaje. Pisze tylko, że małe paczki doręcza poczta, a cięższe poczta albo firma kurierska. Kto ma Twoją paczkę, pokażą linie po przekazaniu; jeśli nic z nich nie wynika, zapytaj sprzedawcę.

### Ile idzie paczka JCEX do Polski?

Dla małych paczek JCEX podaje sprzedawcom 9–13 dni roboczych do Europy, bez osobnego terminu dla Polski. Kieruj się terminem, który sklep pokazał przy Twoim zamówieniu.

:::sources
- [JCEX: 物流查询 (śledzenie)](https://track.jcex.com/) – strona tylko po chińsku, do 100 numerów, chińska wersja linii, kategorie z opisami, pole 跟踪单号, 查询不到 i 暂无轨迹详情
- [JCEX: 联系我们 (kontakt)](https://www.jcex.com/bangzhuzhongxin/lianxiwomen/index.html) – infolinia, godziny, e-mail, siedziba, oddziały w Chinach
- [JCEX: 佳成国际物流 (strona główna)](https://www.jcex.com/) – e-mail do skarg, WeChat, godziny, udziały Huamao, notowanie w Szanghaju
- [JCEX: 佳成介绍 (o firmie)](https://www.jcex.com/guanyujiacheng/jiachengjieshao/index.html) – rok 2000, handel internetowy, Huamao od 2021
- [JCEX: English site](https://enjc.jcex.com/) – Jiacheng International Logistics, Alibaba.com, Cainiao, DHgate, Complaint Email
- [JCEX: 国际小包 (małe paczki)](https://www.jcex.com/chanpinyufuwu/guojixiaobaoyukuaidi/index.html) – do 2 kg, samolotem, doręcza poczta, 9–13 dni roboczych do Europy
- [JCEX: 国际快递与快运 (ekspres i fracht)](https://www.jcex.com/chanpinyufuwu/guojikuaiyun/1438101270410715138.html) – 0,5–20 kg, poczta albo firma kurierska
- [KAS: Zwolnienia celne](https://podatki.gov.pl/informacje-o-cle/pozostale-informacje/zwolnienia-celne) – 3 EUR za pozycję od 1 lipca 2026 do 1 lipca 2028, do 150 EUR, IOSS albo przesyłka pocztowa
- [Komisja Europejska: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 3 euro od 1 lipca 2026, 2 euro od 1 listopada 2026
- [Komisja Europejska: Customs Guidance on EUR 3 customs duty](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – opłata 2 euro za pozycję
- [UKE: Reklamacje](https://cik.uke.gov.pl/uslugi-pocztowe/dochodzenie-roszczen/reklamacje/) – kto składa reklamację, 12 i 6 miesięcy, interwencja Prezesa UKE, pozasądowe rozwiązywanie sporów
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – co Peek odczytuje i pokazuje, kształt numeru, angielskie i chińskie wersje linii, brak drugiego numeru w znanych historiach, przekazanie zapisane jako doręczenie, linie USPS i Colissimo
:::
