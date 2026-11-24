---
title: Śledzenie DHL Freight: kody ActiveTracing, statusy i pomoc
description: Śledź przesyłkę drogową lub kolejową DHL Freight w ActiveTracing: kody JND, numery zleceń, ID przesyłki, SSCC, awizacja dostawy i kontakt.
slug: sledzenie-dhl-freight
published: 2026-11-24
updated: 2026-11-24
---

DHL Freight (*DHL Freight GmbH*, część DHL Group) to europejski dział frachtu drogowego i kolejowego DHL, działający przez spółki zależne w ponad 40 krajach i przewożący ponad 45 milionów ton towarów rocznie w sieciach LTL (drobnicowych), PTL (częściowych), FTL (całopojazdowych), w temperaturze kontrolowanej, o podwyższonym bezpieczeństwie oraz w europejskim transporcie kolejowym. Oprócz paletowych ładunków B2B (jak *EuroConnect*), DHL Freight doręcza także duże zamówienia dla klientów indywidualnych (*DHL Home Delivery*) i szwedzkie przesyłki krajowe *DHL Service Point*. Przesyłkę sprawdzisz w [DHL ActiveTracing](https://activetracing.dhl.com/DatPublic/datSelection.do) albo wklejając swój numer referencyjny w pole powyżej.

## Numery śledzenia DHL Freight: kody zleceń, ID przesyłek i kody JND

DHL ActiveTracing przyjmuje kilka rodzajów numerów referencyjnych i przechowuje dane o przesyłkach online przez **sześć miesięcy**:

| Format (wymyślony) | Co to jest |
| --- | --- |
| `JND48291736405829173` | 20-znakowy **DHL Package ID / License Plate** (`JND` + 17 cyfr). |
| `FRA-EC-4829173` | **Kod zlecenia DHL (Order code)**: 3-literowy kod terminala + 2-literowy kod produktu (np. `EC` dla EuroConnect) + 7 cyfr (z myślnikami lub bez). |
| `4829173640` | **ID przesyłki (Consignment ID)**: 9, 10 lub 11 cyfr (10-cyfrowe numery są częste w szwedzkich przesyłkach krajowych *DHL Service Point*). Długość 10 cyfr współdzieli z [DHL](carrier:dhl), [Mondial Relay](carrier:mondial-relay), [Relais Colis](carrier:relais-colis) i [Packeta](carrier:packeta). |
| `00348291736405829177` | 20-cyfrowy identyfikator jednostki **GS1 SSCC** zaczynający się od `00` (z poprawną cyfrą kontrolną GS1). 20-cyfrowy układ SSCC współdzieli z [USPS](carrier:usps) i [NZ Post](carrier:nz-post). |

Zwróć uwagę, że standardowe paczki w Niemczech obsługuje [DHL](carrier:dhl) (*DHL Paket*), a międzynarodowe lotnicze przesyłki ekspresowe [DHL Express](carrier:dhl-express), natomiast palety, ciężkie meble i europejski fracht drogowy lub kolejowy śledzi się w portalu ActiveTracing DHL Freight. Zobacz [formaty numerów śledzenia](guide:tracking-number-formats).

## Co oznaczają statusy DHL Freight ActiveTracing

ActiveTracing podaje dla każdego wpisu zdanie ze statusem, po angielsku lub niemiecku, terminal (na przykład `DE-KOBLENZ`) oraz datę i godzinę terminala bez wskazanej strefy czasowej:

| Komunikat DHL Freight (EN / DE) | Co oznacza dla Ciebie | Peek pokazuje |
| --- | --- | --- |
| `Consignment created`, `Collection arranged - OK`, `Out for Collection`, `Not Collected - ...` / `Auftrag erfasst`, `In Abholung` | Zlecenie frachtowe utworzone lub kierowca skierowany po odbiór; towar wciąż znajduje się u nadawcy. | „Zarejestrowana” |
| `Collected`, `Shipment handover by sender` / `Abholung` | Odebrano od nadawcy albo nadano w szwedzkim punkcie DHL Service Point. | „Przyjęta przez przewoźnika” |
| `Received at terminal`, `Shipped from terminal`, `Arrival at destination`, `Loaded into ferry`, `Direct delivery`, `Receiver notified`, `Shipment in warehouse - Awaiting delivery appointment` / `Delivery appointment booked` / `Empfangen im Terminal`, `Verladen`, `Avisierung` | W drodze między terminalami DHL Freight, na przeprawie promowej, po odprawie celnej (`Customs cleared`) lub oczekuje w terminalu docelowym na ustalenie terminu dostawy. | „W transporcie” |
| `On hold - in customs clearance` | Zatrzymana do transgranicznej odprawy celnej (np. Szwajcaria, Wielka Brytania lub Norwegia). | „W urzędzie celnym” |
| `Out for Delivery` / `In Zustellung` | Załadowana na lokalną ciężarówkę do dzisiejszej dostawy. | „W doręczeniu” |
| `Shipment in warehouse - ready for self collection by consignee` | Gotowa do odbioru własnego przez odbiorcę w lokalnym terminalu DHL Freight. | „Do odbioru” |
| `Attempted DHL delivery - ...` | Próba dostawy nie powiodła się (np. odbiorca zamknięty lub odmowa przyjęcia). | „Próba doręczenia” |
| `Shipment in warehouse - waiting for consignee payment`, `Shipment delivered with exception - partly delivered`, `Exception occurred - Strike`, `New shipping instruction - consignment cancelled by sender` | Wstrzymanie do czasu płatności, dostawa częściowa z brakiem części sztuk, utrudnienia strajkowe albo anulowanie przez nadawcę. | „Zgłoszono problem” |
| `New shipping instruction - shipment returned` | Ładunek zwrócony nadawcy na podstawie nowych dyspozycji. | „Zwrócona nadawcy” |
| `Delivered`, `Received by consignee`, `Picked up by receiver`, `Shipment delivered with exception` / `Zugestellt` | Doręczona odbiorcy albo odebrana w terminalu / Service Point. | „Dostarczona” |

## Awizacja dostawy (*Avisierung*), cło i zasady transportu

Według [DHL Freight Niemcy](https://www.dhl.com/de-en/home/freight.html) oraz [Centrum Pomocy DHL Freight](https://www.dhl.com/de-en/home/freight/help-center-for-european-road-and-rail.html):

- **Awizacja dostawy (*Avisierung*):** przesyłka może oczekiwać w terminalu doręczającym na statusie `Shipment in warehouse - Awaiting delivery appointment`, dopóki nie zostanie uzgodniony dzień dostawy z odbiorcą; po jego ustaleniu pojawia się `Delivery appointment booked`. Jeśli przesyłka czeka, skontaktuj się z nadawcą lub terminalem doręczającym.
- **Sześciomiesięczne archiwum ActiveTracing:** [activetracing.dhl.com](https://activetracing.dhl.com/DatPublic/datSelection.do) udostępnia historię przesyłek z **ostatnich sześciu miesięcy** po angielsku, niemiecku, hiszpańsku, francusku, szwedzku, polsku i włosku.
- **Europejskie cło transgraniczne:** transport drogowy między UE a sąsiednimi krajami spoza UE (takimi jak Wielka Brytania, Szwajcaria czy Norwegia) wymaga odprawy celnej (`On hold - in customs clearance`). Dla przesyłek e-commerce spoza UE wjeżdżających do UE obowiązują cło 3 € od przesyłek do 150 € i unijna opłata manipulacyjna 2 € zgodnie z [reformą celną UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en); zobacz [przesyłki zatrzymane na cle](guide:customs).

## Przesyłka DHL Freight opóźniona, uszkodzona lub dostarczona częściowo

Transport z DHL Freight zamówił nadawca, dlatego w pierwszej kolejności skontaktuj się ze sprzedawcą lub nadawcą.

- **Widoczne uszkodzenia lub brak części sztuk (`partly delivered`):** sprawdź towar i liczbę sztuk przy odbiorze i od razu powiadom nadawcę. W Centrum Pomocy DHL Freight znajduje się poradnik *How to Prepare a Claim*.
- **Oczekiwanie na awizację lub płatność:** jeśli ActiveTracing pokazuje `Awaiting delivery appointment` albo `waiting for consignee payment`, skontaktuj się od razu z terminalem docelowym wskazanym w ostatnim skanie (lub z nadawcą).
- Zobacz [dlaczego śledzenie przestaje się aktualizować](guide:tracking-not-updating), [doręczona, ale nieotrzymana](guide:delivered-not-received), [uszkodzona przesyłka](guide:damaged-parcel), [zaginiona przesyłka](guide:lost-parcel) i [zwrócona nadawcy](guide:returned-to-sender).

## Obsługa klienta i kontakt DHL Freight

Kanały wsparcia opublikowane w [Centrum Pomocy DHL Freight](https://www.dhl.com/de-en/home/freight/help-center-for-european-road-and-rail.html) i na stronie [DHL Freight Niemcy](https://www.dhl.com/de-en/home/freight.html):

- **Centrum Pomocy dla europejskiego frachtu drogowego i kolejowego:** [dhl.com/de-en/home/freight/help-center-for-european-road-and-rail.html](https://www.dhl.com/de-en/home/freight/help-center-for-european-road-and-rail.html) — FAQ, *How to Prepare a Claim*, Incoterms 2020, regulaminy i przepisy dotyczące towarów niebezpiecznych.
- **Viva i wyszukiwarka terminali:** Centrum Pomocy udostępnia **Viva**, asystenta obsługi klienta AI DHL Freight, oraz *Freight Terminals and Locations* do wyszukiwania terminali i oddziałów według kraju.
- **Portal biznesowy:** *myDHLFreight*, wraz z filmami instruktażowymi i FAQ w Centrum Pomocy.

> Ignoruj niezamówione linki w SMS-ach lub e-mailach proszące o nieoczekiwaną płatność online za „zwolnienie” przesyłki DHL Freight. Sprawdzaj status wyłącznie bezpośrednio na [activetracing.dhl.com](https://activetracing.dhl.com/DatPublic/datSelection.do) lub w docelowym terminalu DHL Freight. Zobacz [fałszywe SMS-y o przesyłkach](guide:delivery-scam-texts).

## Śledzenie przesyłki DHL Freight w Peek

[Peek](/) pyta DHL Freight o kod `JND` (`JND48291736405829173`, wymyślony) — jedynego przewoźnika odpytywanego dla tego formatu — i wyświetla „DHL Freight ma tę przesyłkę.”, gdy tylko ActiveTracing odpowie. Dla 10-cyfrowego ID przesyłki (`4829173640`) Peek pyta DHL Freight wśród przewoźników 10-cyfrowych, gdy śledzisz z Niemiec, Szwecji, Austrii, Polski, Holandii, Belgii, Francji, Włoch, Hiszpanii lub Wielkiej Brytanii, a z innych krajów dopiero w ostateczności. Peek rozpoznaje też wklejony link `activetracing.dhl.com/DatPublic/datSelection.do` (`a=`, `valuePackageField=` lub `valueShipmentOrderField=`). Dla kodu zlecenia (`FRA-EC-4829173`), 9- lub 11-cyfrowego ID przesyłki albo numeru SSCC (`00348291736405829177`) stuknij „Zmień” podczas dodawania lub „Zmień przewoźnika” na ekranie przesyłki i wybierz DHL Freight (wyszukanie „DHL ActiveTracing”, „DHL Frachtverfolgung” albo „DHL Freight GmbH” także go znajduje).

Peek odczytuje bezpośrednio angielską stronę ActiveTracing i pokazuje etap („Zarejestrowana”, „Przyjęta przez przewoźnika”, „W transporcie”, „W urzędzie celnym”, „W doręczeniu”, „Do odbioru”, „Próba doręczenia”, „Zgłoszono problem”, „Zwrócona nadawcy”, „Dostarczona”), zdania statusów z ActiveTracing z nazwą terminala (np. `KOBLENZ, DE`), wagę brutto i kraj docelowy. Peek datuje każdy wpis według strefy czasowej danego terminala i przelicza go na zegar Twojego urządzenia. Nazwa usługi nie pojawia się w żadnej aplikacji Peek. Miejscowość odbiorcy — jedyna część adresu wyświetlana przez ActiveTracing — może zamykać trasę na Twojej mapie, gdy przesyłka jest w drodze; udostępnione linki jej nie pokazują. Peek pomija miejsce nadania, liczbę sztuk, objętość, datę nadania, osobną godzinę doręczenia, inne numery referencyjne, potwierdzenie doręczenia i podpis; ActiveTracing nie podaje szacowanej daty dostawy, kodów statusów ani nazw nadawcy czy odbiorcy.

Dopóki masz przesyłkę otwartą w Peek albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza status do co 10 minut; w dniu, gdy ActiveTracing pokazuje `Out for Delivery`, co 2 minuty (co 5 po sprawdzeniu, które otworzyło przeglądarkę) od 6:00 według zegara miejsca ostatniego skanu, dopóki ActiveTracing odpowiada. W pozostałych sytuacjach sprawdza co godzinę. Po 12 godzinach z samym statusem „Zarejestrowana” lub „Przyjęta przez przewoźnika” albo po dwóch dniach bez wiadomości przechodzi na sprawdzanie co godzinę niezależnie od sposobu śledzenia, a po siedmiu dniach na „Zarejestrowana” co 6 godzin. Gdy ActiveTracing nie odpowiada i Peek korzysta wyłącznie z zewnętrznych usług śledzenia, sprawdza co godzinę, po spokojnym dniu co 3 godziny i do co 15 minut w dniu doręczenia, dopóki masz przesyłkę otwartą lub włączone powiadomienia.

Kiedy najnowszy skan ma cztery dni, a przesyłka nadal stoi na „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie”, Peek oznacza ją dniem tego skanu, np. „Brak aktualizacji od pon., 12 paź”. Na stronie przesyłki wyświetla się wtedy „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika DHL Freight.” oraz link „Poproś przewoźnika DHL Freight o pomoc” do Centrum Pomocy DHL Freight. Po trzech tygodniach bez nowego skanu pojawia się komunikat „Od ponad trzech tygodni nic nowego. Jeśli przesyłka wciąż nie dotarła, zapytaj sprzedawcę. Peek dalej pyta przewoźnika DHL Freight.”

Możesz wybrać powiadomienia dla opcji „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek jedynie monitoruje aktualizacje śledzenia i nie może umówić terminu dostawy, opłacić kosztów transportu ani skontaktować się z terminalem DHL Freight w Twoim imieniu.

## Pytania o śledzenie DHL Freight

### Czym różni się DHL Freight od DHL Parcel i DHL Express?

DHL Freight obsługuje europejski fracht drogowy i kolejowy (palety, LTL/PTL/FTL, wielkogabarytowe *DHL Home Delivery* oraz szwedzki transport krajowy) śledzony w [ActiveTracing](https://activetracing.dhl.com/DatPublic/datSelection.do), podczas gdy standardowe paczki obsługuje [DHL](carrier:dhl), a terminowe międzynarodowe przesyłki lotnicze [DHL Express](carrier:dhl-express).

### Jakie numery śledzenia działają w DHL ActiveTracing?

ActiveTracing akceptuje kody zleceń DHL (`FRA-EC-4829173`, wymyślony), 9–11-cyfrowe ID przesyłek (`4829173640`), 20-znakowe identyfikatory paczek / License Plate `JND` (`JND48291736405829173`) oraz 20-cyfrowe numery GS1 SSCC (`00348291736405829177`).

### Dlaczego moja przesyłka czeka na statusie `Awaiting delivery appointment`?

Terminal doręczający przechowuje ładunek do czasu uzgodnienia dnia dostawy z odbiorcą. Zapytaj nadawcę albo terminal wymieniony w ostatnim wierszu śledzenia.

### Jak długo DHL ActiveTracing przechowuje historię przesyłki?

[ActiveTracing](https://activetracing.dhl.com/DatPublic/datSelection.do) informuje, że dane przesyłki są dostępne online przez **ostatnie sześć miesięcy**.

:::sources
- [DHL Freight: ActiveTracing](https://activetracing.dhl.com/DatPublic/datSelection.do) – wyszukiwanie po ID przesyłki, kodzie zlecenia i Package ID / License Plate, 6-miesięczne archiwum, *DHL Freight GmbH*
- [DHL Freight: strona Niemcy (angielski)](https://www.dhl.com/de-en/home/freight.html) – ponad 40 krajów, ponad 45 mln ton/rok, LTL/PTL/FTL, GoGreen Plus Flex, Rail Freight Europe i sieć terminali
- [DHL Freight: Centrum Pomocy dla transportu drogowego i kolejowego w Europie (angielski)](https://www.dhl.com/de-en/home/freight/help-center-for-european-road-and-rail.html) – asystent AI Viva, wyszukiwarka terminali, How to Prepare a Claim, Incoterms 2020 i myDHLFreight (URL pomocy linkowany przez Peek)
- [Komisja Europejska: reforma celna UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – cło 3 € do 150 € i unijna opłata manipulacyjna 2 €
- [Universal Parcel Scraper: dhl-freight](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dhl-freight) – parsowanie `JND`, kodu zlecenia, 9–11-cyfrowego ID przesyłki i SSCC, reguły linków `activetracing.dhl.com`, mapa statusów angielski/niemiecki, czas lokalny każdego terminala, odczytywana waga i kraj docelowy oraz URL pomocy
:::
