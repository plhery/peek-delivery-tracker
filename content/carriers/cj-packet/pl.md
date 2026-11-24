---
title: Śledzenie CJ Packet: kody CJP...YQ, przekazanie i kontakt
description: Śledź przesyłkę CJ Packet (CJ Dropshipping) z Chin: kody CJP...YQ, statusy własne i lokalnego kuriera, przerwy celne oraz e-mail wsparcia.
slug: sledzenie-cj-packet
published: 2026-11-24
updated: 2026-11-24
---

CJ Packet (*CJPacket*) to międzynarodowa usługa wysyłki i śledzenia paczek powiązana z [CJ Dropshipping](https://cjdropshipping.com/), przewożąca małe zamówienia e-commerce, ekonomiczne przesyłki zbiorcze oraz szybkie przesyłki ekspresowe z chińskich magazynów do kupujących na całym świecie z wykorzystaniem transportu lotniczego i lokalnych partnerów ostatniej mili. Przesyłkę sprawdzisz bezpośrednio na [cjpacket.com](https://www.cjpacket.com/) albo wklejając numer śledzenia w pole powyżej.

## Numery śledzenia CJ Packet: format CJP...YQ

Znajdź numer listu przewozowego CJ Packet w e-mailu z potwierdzeniem wysyłki ze sklepu lub na stronie zamówienia. Kody CJ Packet zaczynają się od `CJP`, po których następują 2 lub 3 litery (oznaczające linię wysyłkową), 10 cyfr oraz `YQ` (łącznie 17 lub 18 znaków):

| Format (wymyślony) | Co to jest |
| --- | --- |
| `CJPOS4829173640YQ` | 17-znakowy list przewozowy CJ Packet (`CJP` + 2 litery linii + 10 cyfr + `YQ`). |
| `CJPAEE4829173640YQ` | 18-znakowy list przewozowy CJ Packet (`CJP` + 3 litery linii + 10 cyfr + `YQ`). |

Po przylocie zamówienia do kraju docelowego i odprawie celnej CJ Packet przekazuje paczkę lokalnemu kurierowi lub poczcie narodowej — np. [USPS](carrier:usps), [Canada Post](carrier:canada-post), [FedEx](carrier:fedex) albo [CTT Express](carrier:ctt-express) — i przekazuje lokalny numer śledzenia oraz skany doręczenia w historii kodu `CJP...YQ`. Inne chińskie prefiksy e-commerce opisuje poradnik [formaty numerów śledzenia](guide:tracking-number-formats).

## Co oznaczają statusy śledzenia CJ Packet

CJ Packet łączy zbiorczy status przesyłki (`0` do `7`) ze szczegółowymi skanami z magazynu CJ, od spedytora eksportowego i od lokalnego kuriera w kraju docelowym:

| Status zbiorczy CJ Packet (kod) / treść skanu | Co oznacza dla Ciebie | Peek pokazuje |
| --- | --- | --- |
| `Processing` (`0`) / `Label created. Warehouse is processing this order.`, `Shipment info received` | Zamówienie spakowane lub etykieta utworzona w magazynie CJ przed odbiorem przez spedytora. | „Zarejestrowana” |
| `Dispatched` (`1`) / `Item has been collected` | Paczka przekazana z magazynu CJ spedytorowi międzynarodowemu. | „Przyjęta przez przewoźnika” |
| `En Route` (`2`), `Arrived Courier Facility` (`3`) / `Item Flight departure`, `Item Flight landing`, `Customs Clearance Completed`, `Delivery to local courier` | Wysłana samolotem z Chin do kraju docelowego, odprawiona celnie i przekazana do sortowni lokalnego kuriera. | „W transporcie” |
| `Inbound to customs temporary warehouse`, `In clearance processing - Import` | W trakcie importowej odprawy celnej w kraju docelowym. | „W urzędzie celnym” |
| `Out for Delivery` (`4`) / `Out for delivery` / `En reparto` | Dziś w drodze z lokalnym kurierem do doręczenia. | „W doręczeniu” |
| `Available for Pickup` (`5`) | Gotowa do odbioru w lokalnym urzędzie pocztowym lub punkcie odbioru. | „Do odbioru” |
| `Unsuccessful Delivery` (`6`) | Doręczenie nie powiodło się (np. błędny adres lub nieobecność odbiorcy). | „Zgłoszono problem” |
| `Delivered` (`7`) / `Package delivered`, `Delivered, At the door/Yard`, `Entregado` | Doręczona przez partnera w kraju docelowym (czasem ze wskazaniem osoby odbierającej). | „Dostarczona” |

Komunikaty informacyjne takie jak `EXPECTED DELIVERY DATE UPDATED` od lokalnego kuriera to jedynie szacunki i nie zmieniają faktycznego etapu transportu przesyłki.

## Jak działa transgraniczna dostawa CJ Packet

Na [cjpacket.com](https://www.cjpacket.com/) CJ Packet oferuje trzy główne modele — *Small-package Transportation*, *Low-cost Bulk Packages* oraz *Fast Express* — łącząc partnerskie linie lotnicze, obsługę celną i lokalnych kurierów na świecie.

**Kto doręcza paczkę pod drzwi.** Gdy pojawi się `Delivery to local courier`, przejmująca poczta lub kurier decyduje o liczbie prób doręczenia, bezpiecznym pozostawieniu, przechowywaniu w punktach odbioru i zwrotach. Strona CJ Packet jest skierowana do sprzedawców: w sprawie opóźnień, zwrotów lub reklamacji skontaktuj się ze sklepem internetowym, w którym kupiłeś. O VAT importowym, cle i postojach celnych (w tym zasadach UE dla paczek spoza UE) przeczytasz w poradniku [przesyłki zatrzymane na cle](guide:customs).

## Przesyłka CJ Packet utknęła, nie aktualizuje się lub oznaczona jako dostarczona

Ponieważ umowę zakupu zawarłeś ze sklepem internetowym (a nie z CJ Dropshipping czy CJ Packet), przy opóźnieniu, uszkodzeniu lub braku paczki zawsze najpierw kontaktuj się ze sprzedawcą.

- **Przerwy po `Label created` lub wylocie:** kilkudniowe oczekiwanie podczas pakowania w magazynie CJ (`Label created. Warehouse is processing this order.`) albo podczas odprawy celnej w kraju docelowym jest częste przy małych paczkach z zagranicy. Zobacz [dlaczego śledzenie przestaje się aktualizować](guide:tracking-not-updating).
- **Użyj numeru lokalnego kuriera:** gdy CJ Packet pokaże numer lokalnego partnera (np. USPS, Canada Post lub regionalnego kuriera), bezpośrednie sprawdzenie na stronie tego przewoźnika bywa dokładniejsze co do odbioru czy ponownej dostawy.
- **Nieudane doręczenie (`6`) lub status doręczenia bez paczki:** sprawdź najpierw u domowników, sąsiadów, w recepcji i u lokalnego kuriera, a potem od razu napisz do sprzedawcy, zanim miną terminy reklamacji kartowej lub na platformie. Zobacz [doręczona, ale nieotrzymana](guide:delivered-not-received), [uszkodzona przesyłka](guide:damaged-parcel), [zaginiona przesyłka](guide:lost-parcel) i [zwrócona nadawcy](guide:returned-to-sender).

## Obsługa klienta i kontakt CJ Packet

Oficjalne dane kontaktowe ze stron [cjpacket.com](https://www.cjpacket.com/) i [cjdropshipping.com](https://cjdropshipping.com/):

- **E-mail wsparcia logistycznego:** `logistics@cjpacket.com` (wsparcie 24/7 wymienione na [cjpacket.com](https://www.cjpacket.com/)).
- **Portal śledzenia:** [cjpacket.com](https://www.cjpacket.com/).
- **Platforma dropshippingowa:** [cjdropshipping.com](https://cjdropshipping.com/) (wsparcie logistyczne dla sprzedawców).

> Ignoruj niezamówione SMS-y lub wiadomości WhatsApp proszące o drobną płatność kartą pod zewnętrznym linkiem za „doręczenie” lub „odprawę” paczki. Prawdziwe opłaty importowe sprawdzaj wyłącznie na stronie zamówienia w sklepie lub na oficjalnej stronie poczty narodowej. Zobacz [fałszywe SMS-y o przesyłkach](guide:delivery-scam-texts).

## Śledzenie przesyłki CJ Packet w Peek

[Peek](/) automatycznie i z wysoką pewnością rozpoznaje `CJP` + 2 lub 3 litery + 10 cyfr + `YQ` (`CJPOS4829173640YQ` oraz `CJPAEE4829173640YQ`, cyfry wymyślone) jako CJ Packet, a także wklejone linki `cjpacket.com/?trackingNumber=`. Jeśli musisz wybrać CJ Packet ręcznie, stuknij „Zmień” podczas dodawania lub „Zmień przewoźnika” na ekranie przesyłki (wyszukanie „CJPacket” albo „CJ Dropshipping” także go znajduje).

Peek odczytuje śledzenie CJ Packet bezpośrednio i pokazuje etap („Zarejestrowana”, „Przyjęta przez przewoźnika”, „W transporcie”, „W urzędzie celnym”, „W doręczeniu”, „Do odbioru”, „Zgłoszono problem”, „Dostarczona”), treść skanów magazynu, spedytora i lokalnego kuriera bez numerów telefonu, adresów e-mail i linii wsparcia kurierów, miejsce skanu oraz kraj docelowy. Nazwa usługi lub linii nie trafia do żadnej aplikacji Peek, a nazwisko odbiorcy pozostaje prywatne. Skan z przesunięciem strefowym je zachowuje, skan bez niego jest datowany według strefy czasowej miejsca, a Peek pokazuje wszystkie na zegarze Twojego urządzenia — wraz z godziną doręczenia, jeśli skan doręczenia ma przesunięcie. Gdy CJ Packet poda lokalnego przewoźnika i jego numer, a Peek go rozpoznaje, sprawdza, czy ma on paczkę: przesyłka pokazuje wtedy „Doręcza:” z nazwą przewoźnika i wyświetla oba numery. Peek pomija kraj nadania, numery aliasowe, referencje zamówienia i sprzedawcy oraz dane kontaktowe; śledzenie CJ Packet nie podaje przewidywanej daty dostawy, wagi ani zdjęcia potwierdzenia doręczenia.

Dopóki masz przesyłkę otwartą w Peek albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza status do co 10 minut; w dniu, gdy CJ Packet pokazuje `Out for Delivery` (`4`), co 2 minuty od 6:00 według zegara miejsca ostatniego skanu, dopóki odpowiada śledzenie CJ Packet. W pozostałych sytuacjach sprawdza co godzinę. Po 12 godzinach z samym statusem „Zarejestrowana” lub „Przyjęta przez przewoźnika” albo po dwóch dniach bez wiadomości przechodzi na sprawdzanie co godzinę niezależnie od sposobu śledzenia, a po siedmiu dniach na „Zarejestrowana” co 6 godzin. Gdy śledzenie CJ Packet nie odpowiada i Peek korzysta wyłącznie z zewnętrznych usług śledzenia, sprawdza co godzinę, po spokojnym dniu co 3 godziny i do co 15 minut w dniu doręczenia, dopóki masz przesyłkę otwartą lub włączone powiadomienia.

Kiedy najnowszy skan ma cztery dni, a przesyłka nadal stoi na „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie”, Peek oznacza ją dniem tego skanu, np. „Brak aktualizacji od pon., 12 paź”. Na stronie przesyłki wyświetla się wtedy „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika CJ Packet.” Po trzech tygodniach bez nowego skanu pojawia się komunikat „Od ponad trzech tygodni nic nowego. Jeśli przesyłka wciąż nie dotarła, zapytaj sprzedawcę. Peek dalej pyta przewoźnika CJ Packet.”

Możesz wybrać powiadomienia dla opcji „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek jedynie monitoruje aktualizacje śledzenia i nie może odprawić przesyłki celnie, skontaktować się z CJ Packet ani wystąpić o zwrot pieniędzy do Twojego sklepu internetowego.

## Pytania o śledzenie CJ Packet

### Czym jest CJ Packet?

CJ Packet to międzynarodowa usługa wysyłki i śledzenia używana przez sklepy realizujące zamówienia przez [CJ Dropshipping](https://cjdropshipping.com/) z magazynów w Chinach do kupujących na całym świecie.

### Co oznacza numer śledzenia `CJP...YQ`?

Numery zaczynające się od `CJP`, 2 lub 3 liter trasy, 10 cyfr i `YQ` (takie jak `CJPOS4829173640YQ` lub `CJPAEE4829173640YQ`, cyfry wymyślone) to międzynarodowe listy przewozowe CJ Packet.

### Kto doręcza paczkę CJ Packet lokalnie?

Po przylocie z Chin i odprawie celnej w kraju docelowym CJ Packet przekazuje przesyłkę (`Delivery to local courier`) lokalnej poczcie narodowej lub firmie kurierskiej (np. USPS, Canada Post, FedEx lub europejskiemu kurierowi regionalnemu) do doręczenia pod drzwi.

### Jak skontaktować się z obsługą klienta CJ Packet?

W sprawie zwrotów pieniędzy lub błędnego adresu napisz najpierw do sklepu, w którym złożyłeś zamówienie. E-mail logistyczny podany przez CJ Packet na [cjpacket.com](https://www.cjpacket.com/) to `logistics@cjpacket.com`.

:::sources
- [CJ Packet: oficjalny portal śledzenia](https://www.cjpacket.com/) – Small-package Transportation, Low-cost Bulk Packages, Fast Express, wsparcie 24/7 i `logistics@cjpacket.com`
- [CJ Dropshipping](https://cjdropshipping.com/) – nadrzędna platforma realizacji zamówień e-commerce
- [Universal Parcel Scraper: cj-packet](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/cj-packet) – wykrywanie `CJP[A-Z]{2,3}\d{10}YQ`, reguła linku `cjpacket.com/?trackingNumber=`, kody zbiorcze `0`–`7`, przekazanie lokalnemu partnerowi i parsowanie odbiorcy, zegary z przesunięciem/lokalne oraz pomijane pola
:::
