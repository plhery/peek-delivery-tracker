---
title: Śledzenie PDU: kody Parcel Delivery Union, statusy i pomoc
description: Śledź przesyłkę zagraniczną PDU (Parcel Delivery Union / 友递联): formaty numerów, statusy tranzytu i ostatniej mili, odprawa celna i kontakty.
slug: sledzenie-pdu
published: 2026-11-24
updated: 2026-11-24
---

**PDU** (*Parcel Delivery Union*, prowadzona przez *Shanghai Youdilian Technology Co., Ltd.*, po chińsku *友递联科技*) to założony w 2022 roku operator inteligentnej logistyki transgranicznej z siedzibą w Szanghaju ([pduni.com](https://www.pduni.com/)). Prowadząc własne oddziały i lokalne huby operacyjne w ponad 10 krajach Ameryki Północnej, Europy, Afryki i Azji, PDU zapewnia sklepom e-commerce i producentom kompleksową odprawę celną, odbiór, zagraniczne doręczenie ostatniej mili oraz obsługę pobrań (COD). Przesyłkę sprawdzisz na angielskim portalu PDU pod adresem [pduni.com/MV/en/customer-services/waybill-inquiry](https://www.pduni.com/MV/en/customer-services/waybill-inquiry) lub wklejając numer śledzenia w pole powyżej.

## Numery śledzenia PDU (końcówka `PD` i niestandardowe listy przewozowe)

Wiele transgranicznych paczek PDU nosi 12-znakowy kod złożony z dwóch liter, 8 cyfr i `PD`:

| Format (wymyślony) | Co to jest |
| --- | --- |
| `MX48291736PD` | 12-znakowy transgraniczny list przewozowy PDU (`MX` + 8 cyfr + `PD`). |
| `CO73918462PD` | Inny 12-znakowy format listu `..PD` używany na trasach międzynarodowych. |

Publiczna wyszukiwarka PDU przyjmuje również inne alfanumeryczne numery listów przewozowych od 6 do 40 znaków po ręcznym wyborze PDU. Taki sam schemat pasuje do numerów [Colis Privé](carrier:colis-prive): Peek pyta najpierw Colis Privé, a następnie PDU — lub PDU jako pierwszego w Chinach. Zobacz [formaty numerów przesyłek](guide:tracking-number-formats).

## Co oznaczają statusy śledzenia PDU

PDU przypisuje każdej przesyłce i skanowi ogólny kod stanu (`TRANSIT`, `COLLECTING`, `DELIVERY`, `SIGNED` itd.) wraz z opisem po angielsku lub w języku kraju docelowego (często po hiszpańsku na trasach do Ameryki Łacińskiej, nierzadko z krótkim prefiksem lokalnego przewoźnika jak `ENT,Entregado`, który Peek oczyszcza do wyświetlania):

| Stan lub opis PDU | Co to oznacza dla Ciebie | Peek pokazuje |
| --- | --- | --- |
| `Shipment information sent to PDU.`, `Package not found in last mile or customs process.` | Zlecenie elektroniczne odebrane przez PDU przed fizycznym przyjęciem lub przed pierwszym skanem w sieci ostatniej mili. | „Zarejestrowana” |
| `COLLECTING` (`Being Collected`), `FINAL_COLLECTING` (`Last-Mile Collection`), `TRANSIT` (`In Transit`) | Odebrana z miejsca nadania albo w drodze w tranzycie międzynarodowym i regionalnych centrach dystrybucyjnych. | „W transporcie” |
| `Recogida en Aduanas`, `Ingreso a CEDI`, `En tránsito nacional` | Odebrana z urzędu celnego, przyjęta w krajowym centrum dystrybucyjnym (CEDI) lub w transporcie krajowym. | „W transporcie” |
| `DELIVERY` (bez opisu wyjazdu na trasę) | Przekazana do sieci lokalnego doręczyciela; Peek utrzymuje status tranzytu do faktycznego rozpoczęcia trasy doręczeń. | „W transporcie” |
| `Salida a Ruta` | Wydana dziś lokalnemu kurierowi do doręczenia. | „W doręczeniu” |
| `DELIVERY_EXCEPTION` (`Delivery Exception`), `TO_BE_RETURN` (`Awaiting Return`) | Zgłoszono problem z doręczeniem lub paczka czeka na zwrot do nadawcy; skontaktuj się ze sprzedawcą lub lokalnym kurierem. | „Zgłoszono problem” |
| `RETURNED` (`Returned`) | Zwrócona w stronę nadawcy lub magazynu źródłowego. | „Zwrócona nadawcy” |
| `SIGNED` (`Delivered`), `Entregado` | Doręczona i pokwitowana pod adresem odbiorcy. | „Dostarczona” |

Pamiętaj, że wczesny komunikat `Package not found in last mile or customs process.` w PDU oznacza jedynie, że lokalny partner ostatniej mili jeszcze nie zeskanował paczki — **nie** oznacza to zatrzymania przez urząd celny.

## Odprawa celna, zagraniczna ostatnia mila i płatność przy odbiorze

Według [pduni.com](https://www.pduni.com/) i [Centrum pomocy PDU](https://www.pduni.com/MV/en/customer-services/help-center):

- **Własna odprawa i dopasowanie kodów HS przez AI:** PDU prowadzi własną sieć odprawy celnej w ponad 10 krajach ze zautomatyzowanym doborem kodów HS i przedpłatą ceł; według FAQ PDU nie pobiera oddzielnej opłaty za formalne zgłoszenia eksportowe, a sprzedawcy mogą dosłać online brakujące dokumenty celne.
- **Lokalne doręczenie ostatniej mili:** po zwolnieniu przez urząd celny kraju docelowego (`Recogida en Aduanas` / `Ingreso a CEDI`) lokalne oddziały lub partnerzy kurierscy doręczają paczkę pod drzwi oraz — gdy wybrano tę opcję — pobierają należność przy odbiorze (COD).
- **Podatki i cła importowe:** podatek VAT od importu lub opłaty celne zależą od lokalnych progów podatkowych oraz tego, czy sprzedawca pobrał je przy zakupie. Zobacz [przesyłki zatrzymane w urzędzie celnym](guide:customs).

## Przesyłka PDU zatrzymana, bez skanów lub zaginiona

Ponieważ konto wysyłkowe w PDU należy do sklepów internetowych i producentów, **zawsze najpierw kontaktuj się ze sprzedawcą lub platformą** w sprawie poprawki adresu, zwrotu pieniędzy lub ponownej wysyłki.

- **Cisza między Chinami a przyjęciem lokalnym:** skany często zatrzymują się na kilka dni między `Shipment information sent to PDU.` (lub `Package not found in last mile or customs process.`) a `Recogida en Aduanas` / `Ingreso a CEDI`, gdy skonsolidowany transport lotniczy lub morski dociera do kraju docelowego. Zobacz [dlaczego śledzenie przestaje się aktualizować](guide:tracking-not-updating).
- **`DELIVERY_EXCEPTION` lub `TO_BE_RETURN`:** reaguj szybko — potwierdź adres doręczenia i numer telefonu ze sprzedawcą, aby lokalny hub zaplanował kolejną próbę, zanim przesyłka przejdzie na `RETURNED`.
- **Oznaczona jako dostarczona lub uszkodzona:** sprawdź najpierw u domowników, w recepcji budynku i u sąsiadów, a potem złóż reklamację u sprzedawcy przed końcem ochrony kupujących. Zobacz [dostarczona, ale nieodebrana](guide:delivered-not-received), [zaginiona przesyłka](guide:lost-parcel), [uszkodzona przesyłka](guide:damaged-parcel) oraz [zwrócona nadawcy](guide:returned-to-sender).

## Obsługa klienta PDU i kontakty w Szanghaju

Oficjalne dane kontaktowe opublikowane na [PDU Waybill Inquiry](https://www.pduni.com/MV/en/customer-services/waybill-inquiry) i [PDU Help Center](https://www.pduni.com/MV/en/customer-services/help-center):

- **Nazwa firmy:** *Shanghai Youdilian Technology Co., Ltd.* (*友递联科技*, ICP `沪ICP备2022026205号`).
- **Infolinia telefoniczna:** `021-50837888` (`+86 21 5083 7888`).
- **E-mail:** `service@pduni.com`.
- **Siedziba główna:** Room 305, 6F, Zhonghai Building, Zhenbei Road, Putuo District, Szanghaj, Chiny.
- **Tracker i pomoc online:** [pduni.com/MV/en/customer-services/waybill-inquiry](https://www.pduni.com/MV/en/customer-services/waybill-inquiry) oraz [pduni.com/MV/en/customer-services/help-center](https://www.pduni.com/MV/en/customer-services/help-center).

> Centrum pomocy PDU wyraźnie ostrzega przed oszustwami z podszywaniem się pod logo lub dane kontaktowe PDU. Nigdy nie opłacaj cła ani doręczenia pod niezweryfikowanym linkiem z SMS-a — najpierw potwierdź każdą należność u sprzedawcy lub na oficjalnym portalu przewoźnika. Zobacz [fałszywe SMS-y o doręczeniu](guide:delivery-scam-texts).

## Śledzenie przesyłki PDU w Peek

Gdy wpiszesz 12-znakowy kod `..PD` (`MX48291736PD` lub `CO73918462PD`, wymyślone), [Peek](/) pyta kolejno Colis Privé i PDU (w Chinach najpierw PDU) i wyświetla „PDU ma tę przesyłkę.”, gdy tracker PDU ją rozpozna. Jeśli Twój numer PDU ma inny format od 6 do 40 znaków, stuknij „Zmień” podczas dodawania lub „Zmień przewoźnika” na ekranie przesyłki i wybierz **PDU** (wyszukanie „Parcel Delivery Union” lub „友递联” też go znajduje).

Peek czyta tracker PDU i wyświetla etap („Zarejestrowana”, „W transporcie”, „W doręczeniu”, „Zgłoszono problem”, „Zwrócona nadawcy”, „Dostarczona”), opis skanu bez prefiksu lokalnego przewoźnika (`Entregado`, nie `ENT,Entregado`), miejscowość oraz godzinę doręczenia. Kody stanu PDU wyznaczają etap i nie pojawiają się w żadnej aplikacji Peek. PDU podaje czas każdego skanu z przesunięciem UTC albo w czasie pekińskim, a Peek wyświetla je według zegara Twojego urządzenia. Tracker PDU nie podaje szacowanej daty doręczenia.

Gdy masz przesyłkę otwartą w Peek albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza status nawet co 10 minut; w dniu, gdy PDU pokazuje `Salida a Ruta`, co 2 minuty od godz. 6:00 czasu najnowszego skanu, dopóki tracker PDU odpowiada. W pozostałym czasie Peek sprawdza co godzinę. Po 12 godzinach wyłącznie na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika” albo po dwóch dniach bez zmian sprawdza co godzinę niezależnie od sposobu śledzenia, a po siedmiu dniach na „Zarejestrowana” — co 6 godzin. Gdy tracker PDU nie odpowiada i Peek śledzi przesyłkę wyłącznie przez usługi śledzące, sprawdza co godzinę, co 3 godziny po spokojnym dniu i nawet co 15 minut w dniu doręczenia, dopóki masz ją otwartą lub włączone powiadomienia.

Jeśli najnowszy skan ma cztery dni, a przesyłka nadal widnieje jako „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie”, Peek oznacza ją dniem tego skanu, np. „Brak aktualizacji od pon., 12 paź”. Na stronie przesyłki pojawia się wtedy komunikat „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika PDU.” Po trzech tygodniach bez nowego skanu pojawia się informacja „Od ponad trzech tygodni nic nowego. Jeśli przesyłka wciąż nie dotarła, zapytaj sprzedawcę. Peek dalej pyta przewoźnika PDU.”

Możesz wybrać powiadomienia dla opcji „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek śledzi wyłącznie aktualizacje statusu i nie może odprawić przesyłki celnie, zmienić Twojego adresu doręczenia ani skontaktować się za Ciebie z PDU.

## Pytania o śledzenie PDU

### Czym jest PDU (*Parcel Delivery Union*)?

PDU (*Shanghai Youdilian Technology Co., Ltd.*, *友递联科技*) to założona w 2022 roku szanghajska firma logistyki transgranicznej zapewniająca międzynarodową odprawę celną, odbiór, doręczenie ostatniej mili i pobrania COD w ponad 10 krajach.

### Jak wygląda numer śledzenia PDU?

Wiele międzynarodowych listów przewozowych PDU ma 12 znaków: dwie litery, 8 cyfr i `PD` (na przykład `MX48291736PD`, wymyślony), choć portal PDU śledzi też inne alfanumeryczne kody o długości od 6 do 40 znaków.

### Czy `Package not found in last mile or customs process.` oznacza, że paczka utknęła w urzędzie celnym?

Nie. W trackerze PDU ten wczesny wpis oznacza wyłącznie, że utworzono elektroniczne dane przesyłki i nie otrzymała ona jeszcze pierwszego fizycznego skanu w lokalnym systemie doręczeń lub celnym.

### Jak skontaktować się z obsługą klienta PDU?

W sprawach zwrotu pieniędzy lub zmian doręczenia skontaktuj się najpierw ze sprzedawcą. Opublikowane kontakty obsługi PDU w Szanghaju na [pduni.com/MV/en/customer-services/help-center](https://www.pduni.com/MV/en/customer-services/help-center) to `021-50837888` oraz `service@pduni.com`.

:::sources
- [PDU: oficjalna strona](https://www.pduni.com/) – założona w 2022 r. w dzielnicy Putuo w Szanghaju, własna sieć odpraw celnych i ostatniej mili w ponad 10 krajach, COD
- [PDU: portal śledzenia przesyłek](https://www.pduni.com/MV/en/customer-services/waybill-inquiry) – publiczne śledzenie po angielsku, `service@pduni.com` i `021-50837888`
- [PDU: centrum pomocy i FAQ](https://www.pduni.com/MV/en/customer-services/help-center) – FAQ celne, ostrzeżenie przed podszywaniem się i adres centrali w Szanghaju (*Shanghai Youdilian Technology Co., Ltd.*)
- [Universal Parcel Scraper: pdu](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pdu) – reguła wykrywania `^[A-Z]{2}\d{8}PD$`, API `traceByWeb`, mapowanie stanów i opisów (`DELIVERY` vs. `Salida a Ruta`), usuwanie prefiksów i obsługa czasu UTC/Pekin
:::
