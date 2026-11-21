---
title: Śledzenie 17FEIA: numery FEI…YQ, statusy i doręczenie
description: Śledź przesyłkę 17FEIA (一起飞) z Chin: jak wygląda numer FEI…YQ, co oznaczają kolejne wpisy, kto ją doręcza, cło i co pokazuje Peek.
slug: sledzenie-17feia
published: 2026-11-21
updated: 2026-11-21
---

17FEIA (一起飞, znana także jako Shanghai Hongshan International Logistics, 上海宏杉国际物流) to chińska firma logistyczna. Przyjmuje przesyłki w swoim magazynie w Shenzhen lub Szanghaju, wysyła je z Chin, a po odprawie celnej w kraju docelowym przekazuje przewoźnikowi doręczającemu. Jej numery zaczynają się od `FEI` i kończą na `YQ`. Wklej swój numer w pole powyżej albo sprawdź go w śledzeniu 17FEIA na www.17feia.com. Numer podaje nadawca przesyłki: sprzedawca lub agent wysyłkowy z Chin.

## Numer śledzenia 17FEIA: jak wygląda

Peek rozpoznaje numery 17FEIA jako `FEI`, dwie litery, dziesięć cyfr i `YQ`: razem 17 znaków. Dwie litery po `FEI` oznaczają oddział 17FEIA, np. `SZ` dla Shenzhen lub `SH` dla Szanghaju. Wszystkie przykłady poniżej są zmyślone:

| Numer (zmyślony) | Co to jest |
| --- | --- |
| `FEISZ4829105736YQ` | `FEI`, dwie litery, dziesięć cyfr i `YQ`: numer 17FEIA. Peek rozpoznaje 17FEIA po samym numerze. |
| `FEISZ482910573YQ` | Tylko dziewięć cyfr: nie pasuje do formatu 17FEIA. Sprawdź, czy nie brakuje cyfry. |
| `FEISZ4829105736AB` | Dwie litery inne niż `YQ` na końcu. Peek nie wskazuje 17FEIA po samym formacie, ale pyta śledzenie 17FEIA, czy zna ten numer. |
| `HHWKG4829105736YQ` | Kończy się na `YQ`, ale zaczyna od `HHW`: to numer [HuaHan Logistics](carrier:huahan). |

W Peek małe litery, spacje i myślniki nie mają znaczenia.

**Gdzie go znaleźć.** W e-mailu o wysyłce, na stronie zamówienia lub na koncie agenta wysyłkowego, jeśli z niego korzystałeś. W przeciwnym razie sprawdź [jak znaleźć numer przesyłki](guide:find-tracking-number). Później rekord 17FEIA może też przekazać Peek własny numer przewoźnika doręczającego.

## Statusy śledzenia 17FEIA wyjaśnione

Rekord 17FEIA przypisuje większości wpisów krótką etykietę po chińsku i wiersz opisu, od najnowszego do najstarszego. Peek pokazuje tekst po angielsku i odczytuje etap z chińskiej etykiety. Własne wpisy 17FEIA w typowej kolejności:

| Co podaje 17FEIA | Co to oznacza dla ciebie | Peek pokazuje |
| --- | --- | --- |
| `Item information received.` | 17FEIA ma dane przesyłki, ale jeszcze nie samą paczkę. | „Zarejestrowana” |
| `The package is on the way to the facility.` (`包裹在去设施路上`) | Paczka jest w drodze do magazynu 17FEIA, który jeszcze jej nie otrzymał. | „Zarejestrowana” |
| `Warehouse receipt.` (`仓库收货`) | Magazyn 17FEIA w Shenzhen lub Szanghaju przyjął przesyłkę. | „Przyjęta przez przewoźnika” |
| `The order has information pending customer confirmation.` (`订单待客户确认`) | Magazyn czeka na potwierdzenie zlecenia przez klienta 17FEIA, który zamówił wysyłkę. | „Przyjęta przez przewoźnika” |
| `Order Confirmed.` (`订单已确认`) | Zlecenie zostało potwierdzone. | „Przyjęta przez przewoźnika” |
| `Order dispatched from warehouse.` (`订单已出库`) | Paczka opuściła magazyn. | „W transporcie” |
| `Export declaration in progress.` (`出口报关中`) | Trwa zgłoszenie eksportowe w Chinach – rutynowy etap przed lotem. Urząd celny jej nie zatrzymał i nie jest to cło w twoim kraju. | „W transporcie” |
| `Flight has departed.` (`航班已起飞`) | Samolot wyleciał z Chin. | „W transporcie” |
| `Flight Arrived at Transit Hub.`, `Flight Departed from Transit Hub.` | Międzylądowanie w węźle tranzytowym i odlot dalej. | „W transporcie” |
| `Flight Arrived at Clearance Port.` (`航班抵达清关港`) | Lądowanie w miejscu odprawy celnej przywozowej. | „W transporcie” |
| `Broker picked up Shipment and delivered to warehouse.` (`清关行提货到仓`) | Agencja celna przewiozła przesyłkę do swojego magazynu do odprawy (lub po zwolnieniu). To nie jest doręczenie do ciebie. | „W urzędzie celnym” (lub „W transporcie”, jeśli pojawia się po `清关完成`) |
| `Customs Clearance is in progress.` (`清关中`) | Urząd celny w kraju docelowym odprawia przesyłkę. | „W urzędzie celnym” |
| `Customs inspection in Progress, Release Pending.` (`海关查验，暂未放行`) | Po przylocie do portu odprawy urząd celny kontroluje przesyłkę i jeszcze jej nie zwolnił. | „W urzędzie celnym” |
| `Customs Clearance Completed.` (`清关完成`) | Urząd celny zwolnił przesyłkę. | „W transporcie” |
| `Arrived at delivery hub，Awaiting carrie scan.` (`已交仓，等待上网`) | Przekazana do magazynu przewoźnika doręczającego. Dalej następuje jego pierwszy skan („carrie” to pisownia ze strony śledzenia). | „W transporcie” |
| `包裹已做国内退回处理` (`国内退回`) | Zwrot krajowy w Chinach: po potwierdzeniu zlecenia w magazynie paczka wraca do nadawcy w Chinach zamiast wyjechać za granicę. | „Zwrócona nadawcy” |

**Dwa wpisy, które nie zmieniają etapu.** `Estimated departure date:` z datą (`预计起飞日期`) to plan wpisany przed lotem, a nie skan – Peek go pomija. `Flight delayed.` (`航班延误`) oznacza opóźnienie lotu – Peek utrzymuje etap z poprzedniego wiersza.

**Pasek postępu.** Strona śledzenia 17FEIA wyświetla też pasek od `仓库收货` (przyjęcie w magazynie) przez `飞机起飞` (start samolotu) i `飞机落地` (lądowanie) po `成功签收` (podpis/odbiór). Peek nie kopiuje tego paska, ale wiersz z etykietą `成功签收` pokazuje jako „Dostarczona”.

**Wpisy przewoźnika doręczającego.** Po przekazaniu śledzenie 17FEIA potrafi przekazywać skany przewoźnika doręczającego z etykietą `运输途中` (w transporcie), w jego brzmieniu i z miejscem – np. DHL (`The international shipment has been processed in the export parcel center`), UPS w USA (`Arrived at Facility`, `Departed from Facility`, `On the Way, Delivery will be delayed by one business day.`) czy partnera w Wielkiej Brytanii (`Shipment received in depot`). Peek rozpoznaje je po treści: przyjęcie w magazynie pozostaje „W transporcie”, natomiast doręczenie przesuwa status dalej.

**Dlaczego godziny wyglądają na niepo kolei.** Etapy magazynowe w Chinach (w tym zwrot krajowy) zapisywane są według czasu pekińskiego (UTC+8). Przekazywane skany lokalnego przewoźnika mają czas lokalny, jeśli miejsce wyznacza jedną strefę czasową. Wpisy lotnicze i celne nie podają miejsca, a strefa dopisywana przez 17FEIA nie zawsze im odpowiada: kieruj się kolejnością wierszy.

## Kto doręcza przesyłki 17FEIA

Po odprawie przywozowej 17FEIA przekazuje paczkę przewoźnikowi, który dostarcza ją do ciebie. Peek nie mógł sprawdzić strony 17FEIA, więc nie podaje listy wszystkich partnerów ani obsługiwanych obszarów.

**Co wynika z kodu Peek.** Peek rozpoznaje trzy nazwy, którymi 17FEIA oznacza partnerów: `DPD-DE` dla [DPD Germany](carrier:dpd-de), `DE-DHL` dla [DHL](carrier:dhl) w Niemczech oraz `FEDEX-GROUND` dla [FedEx](carrier:fedex). Odczytuje pięć krajów przeznaczenia w rekordzie 17FEIA: Niemcy, Francję, Holandię, Polskę i Stany Zjednoczone. Jedna z historii w kodzie pokazuje DHL w Niemczech przyjmujący przesyłkę do Francji w celu przekazania jej firmie doręczającej na miejscu.

**Nieudane doręczenie.** Próby doręczenia i odbiór podlegają zasadom przewoźnika doręczającego: skontaktuj się z nim, podając jego numer. Zobacz [nieudane doręczenie](guide:missed-delivery).

**Cło w UE.** Towary kupione online spoza UE podlegają od 1 lipca 2026 r. tymczasowemu cłu w wysokości 3 € za pozycję w przesyłkach o wartości do 150 € oraz, od 1 listopada 2026 r., unijnej opłacie manipulacyjnej 2 € za pozycję niezależnie od wartości. Jedna pozycja to towary o tej samej klasyfikacji taryfowej, opisie oraz – jeśli wymagane – pochodzeniu. Peek nie mógł sprawdzić, jak są pobierane w przypadku 17FEIA: zapytaj sprzedawcę lub agenta, czy cena je obejmowała. Poza UE należności zależą od przepisów danego kraju. Gdy urząd celny kontroluje przesyłkę i jeszcze jej nie zwolnił, w 17FEIA pojawia się `Customs inspection in Progress, Release Pending.` Zobacz [przesyłki zatrzymane na cle](guide:customs), a przed zapłatą po SMS-ie lub e-mailu przeczytaj [fałszywe SMS-y o paczkach](guide:delivery-scam-texts).

## Przesyłka 17FEIA bez aktualizacji, zaginiona lub zwrócona

17FEIA realizuje zlecenie dla podmiotu, który wykupił wysyłkę: sprzedawcy albo agenta wysyłkowego. W razie opóźnienia, zaginięcia, uszkodzenia lub zwrotu skontaktuj się najpierw z nimi.

**Nie znaleziono numeru.** Dla nieznanego numeru strona 17FEIA nie pokazuje postępu. Peek wyświetla „Oczekiwanie na przewoźnika 17FEIA” i nadal sprawdza. Jeśli dwa dni później 17FEIA wciąż nie zna numeru, a żadne inne źródło nie odpowiedziało, Peek pokazuje „Po dwóch dniach przewoźnik nadal nie zna tego numeru. Sprawdź go, wybierz innego przewoźnika lub usuń przesyłkę.” i sprawdza ją raz dziennie, a po tygodniu od dodania raz w tygodniu. Porównaj numer z wiadomością o wysyłce: `FEI`, dwie litery, dziesięć cyfr, `YQ`.

**Stoi na `The order has information pending customer confirmation.`** Jeśli korzystałeś z agenta wysyłkowego, sprawdź tam konto lub wiadomości. W przeciwnym razie zapytaj sprzedawcę.

**Cisza po wylocie.** Po wpisie `Flight has departed.` przez kilka dni może nie być nic nowego. Po czterech dniach bez nowego wpisu Peek oznacza przesyłkę („Brak aktualizacji od czterech dni”). Zobacz [dlaczego śledzenie nie aktualizuje się](guide:tracking-not-updating) i [śledzenie przesyłek z Chin](guide:tracking-from-china).

**Dostarczona, ale nic nie przyszło.** Sprawdź wokół domu i zapytaj sąsiadów, potem przewoźnika doręczającego i sprzedawcę. Zobacz [dostarczona, ale nieotrzymana](guide:delivered-not-received).

**Zaginiona lub zwrócona.** Poproś sprzedawcę lub agenta o zwrot pieniędzy albo nową wysyłkę. Zobacz [zaginiona przesyłka](guide:lost-parcel) i [zwrot do nadawcy](guide:returned-to-sender).

## Obsługa klienta i kontakt z 17FEIA

Strona 17FEIA odpowiedziała Peek kontrolą przeciw botom, więc nie ma tu numeru telefonu ani adresu e-mail do 17FEIA. W sprawie zamówienia zapytaj sprzedawcę lub agenta, który nadał paczkę – to oni są klientami 17FEIA. W sprawie doręczenia skontaktuj się z lokalnym przewoźnikiem.

## Śledzenie przesyłki 17FEIA w Peek

[Peek](/) od razu rozpoznaje 17FEIA po numerze `FEI…YQ` („Wykryty przewoźnik”): żaden inny przewoźnik w Peek nie używa tego kształtu. Działa też link do strony śledzenia 17FEIA z numerem po `orderNumber=`. Kod pocztowy ani konto nie są wymagane.

Każdy inny numer, np. przewoźnika doręczającego, wklej tak samo: Peek śledzi ponad 4500 przewoźników. Gdy kształt pasuje do kilku firm bez pewności, Peek wyświetla „Sprawdzanie usług śledzenia…” podczas zapytań; gdy nie pasuje do żadnej, sprawdza go po dodaniu. Aby wybrać przewoźnika ręcznie, stuknij „Zmień” i wpisz `17FEIA`, `Feia` lub `一起飞`, albo użyj opcji „Zmień przewoźnika” na już dodanej przesyłce. Peek pyta 17FEIA tylko o numery złożone z `FEI`, dwóch liter, dziesięciu cyfr i dwóch liter: dla innego formatu komunikat podaje, że 17FEIA „nie wydaje takich numerów”.

Peek pokazuje status, do 100 najnowszych wpisów w angielskim brzmieniu 17FEIA wraz z miejscem (gdy jest podane) oraz kraj docelowy. Etapy magazynowe odczytuje w czasie pekińskim, a przekazane skany z krajów jednostrefowych (lub jednostrefowych stanów USA) w ich czasie lokalnym i przelicza na strefę twojego urządzenia; wiersze bez miejsca lub określonej strefy (loty, cło lub magazyn bez podanego kraju) zachowują godzinę podaną przez 17FEIA. Peek pomija pasek postępu, kraj nadania, kody pocztowe i wagę. 17FEIA nie podaje przewidywanej daty doręczenia, nazwy usługi, danych nadawcy lub odbiorcy ani potwierdzenia odbioru.

Gdy 17FEIA wskazuje DPD Germany, DHL lub FedEx z numerem pasującym do formatu tego przewoźnika, karta przesyłki to pokazuje (np. „Doręcza: DPD Germany”) i wyświetla ten numer po numerze 17FEIA. Dopóki ten przewoźnik ma tylko zapowiedź bez własnego skanu, karta pokazuje „Zgłoszona przewoźnikowi …, jeszcze nieprzyjęta”. Gdy tylko pojawi się jego skan co najmniej tak świeży jak ostatni w 17FEIA, Peek śledzi paczkę tam, zachowując historię z 17FEIA. Jeśli śledzenie 17FEIA nie odpowiada, Peek pyta usługi śledzenia i pokazuje ich historię w ich brzmieniu.

Dopóki masz przesyłkę otwartą na ekranie albo w dniu doręczenia przy włączonych powiadomieniach, a śledzenie 17FEIA odpowiada, Peek sprawdza ją nawet co 10 minut, a od godz. 6:00 czasu przewoźnika w dniu trafienia do doręczenia nawet co 2 minuty; w pozostałym czasie co godzinę. Przez usługi śledzenia sprawdza co godzinę, a w dniu doręczenia nawet co 15 minut, dopóki masz ją otwartą lub włączone powiadomienia. Po dwóch dniach bez nowości (po 12 godzinach na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika”) Peek sprawdza przesyłkę co godzinę. Może wysyłać powiadomienia „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”; „Ważne etapy” obejmują odprawę przywozową w porcie docelowym, nie zgłoszenie eksportowe w Chinach. Peek nie skontaktuje się za ciebie z 17FEIA, sprzedawcą ani agentem.

## Pytania o śledzenie 17FEIA

### Czym jest 17FEIA (一起飞)?

Chińską firmą logistyczną, znaną też jako Shanghai Hongshan International Logistics (上海宏杉国际物流). Wysyła przesyłki z Chin z magazynu w Shenzhen lub Szanghaju, a po odprawie celnej przywozowej przekazuje je przewoźnikowi doręczającemu. Jej numery zaczynają się od `FEI` i kończą na `YQ`.

### Gdzie śledzić numer FEI…YQ?

W polu powyżej albo w śledzeniu 17FEIA na www.17feia.com. Wpisz cały numer: `FEI`, dwie litery, dziesięć cyfr i `YQ`.

### Co oznacza „Awaiting carrie scan” w śledzeniu 17FEIA?

Pełny wpis brzmi `Arrived at delivery hub，Awaiting carrie scan.`: odprawa celna przywozowa dobiegła końca i 17FEIA przekazała paczkę do magazynu przewoźnika doręczającego, który jeszcze jej nie zeskanował. Jego własne śledzenie może wkrótce wyprzedzić 17FEIA.

### Kto doręcza paczki 17FEIA?

Przewoźnik, któremu 17FEIA przekazuje przesyłkę po odprawie przywozowej. Rekord 17FEIA może podać jego nazwę i numer: jeśli jest to DPD Germany, DHL w Niemczech lub FedEx, pokazuje to karta w Peek. W innym wypadku zapytaj sprzedawcę.

:::sources
- [Universal Parcel Scraper: 17FEIA](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/17feia) – nazwy, format `FEI…YQ` i oddziały (`SZ`/`SH`), strona śledzenia i link `orderNumber=`, chińskie etykiety, angielskie opisy i etapy (w tym kontrola celna, `国内退回`, zmiana etapu `清关行提货到仓` po `清关完成` oraz przekazane wpisy DHL/UPS/magazynu w Wielkiej Brytanii), pasek postępu, zasady stref czasowych, partnerzy (`DPD-DE`, `DE-DHL`, `FEDEX-GROUND`), kierunki i 100 najnowszych skanów
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – inne formaty zakończone na `YQ` (`HuaHan Logistics`), zapasowe usługi śledzenia i przejęcie przez przewoźnika lokalnego
- [Komisja Europejska: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – tymczasowe cło 3 € za pozycję do 150 € od 1 lipca 2026 r., unijna opłata manipulacyjna 2 € od 1 listopada 2026 r.
- [Komisja Europejska: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – opłata 2 € za pozycję niezależnie od wartości, definicja pozycji
:::
