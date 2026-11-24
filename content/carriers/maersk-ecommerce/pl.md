---
title: Śledzenie Maersk eCommerce: kody 1M, statusy i pomoc
description: Śledź przesyłkę Maersk eCommerce (B2C Europe / InterPost): 19-znakowy kod 1M, kody skanów 70000–77200, przekazania ostatniej mili i pomoc Maersk.
slug: sledzenie-maersk-ecommerce
published: 2026-11-24
updated: 2026-11-24
---

**Maersk eCommerce** (*Maersk Logistics & Services USA Inc.*, łączące dawne platformy paczkowe *B2C Europe* i *InterPost*) to krajowa i transgraniczna sieć doręczeń B2C firmy A.P. Moller–Maersk ([maersk.com/supply-chain-logistics/e-commerce-logistics](https://www.maersk.com/supply-chain-logistics/e-commerce-logistics)). Obsługując ponad 80 milionów paczek rocznie z udziałem przeszło 75 zintegrowanych przewoźników w Ameryce Północnej i Europie, Maersk eCommerce sortuje przesyłki sklepów internetowych we własnych zakładach, odprawia celnie paczki B2C na trasach międzynarodowych i przekazuje doręczenie końcowe lokalnym kurierom oraz pocztom, takim jak [Amazon Logistics](carrier:amazon-logistics), [OnTrac](carrier:ontrac) i [DPD Austria](carrier:dpd-at). Wklej 19-znakowy numer `1M` w pole powyżej, aby śledzić zarówno skany z centrów Maersk, jak i przekazywane dalej zdarzenia lokalnego doręczyciela.

## Numery śledzenia Maersk eCommerce (format `1M`)

Maersk eCommerce nadaje 19-znakowe alfanumeryczne numery paczek zaczynające się od `1M`, po których następuje 7-znakowy kod klienta/sklepu (zawierający co najmniej jedną literę) oraz 10 cyfr:

| Format (wymyślony) | Co to jest |
| --- | --- |
| `1MAB4829C7360514928` | 19-znakowy kod paczki Maersk eCommerce (`1M` + 7-znakowy kod klienta + 10 cyfr). |
| `1MKX7391E4829173605` | Inny 19-znakowy numer `1M` używany na trasach w Ameryce Północnej lub Europie. |

Sprawdź e-mail wysyłkowy sprzedawcy lub link śledzenia zamówienia (`maersk.com/tracking/parcel/...`), aby odszukać numer `1M`. Tracker Maersk potrafi podać nazwę lokalnego przewoźnika ostatniej mili i jego własny numer przesyłki. Zobacz [formaty numerów przesyłek](guide:tracking-number-formats).

## Co oznaczają statusy śledzenia Maersk eCommerce

Własne skany Maersk mają kody od `70000` do `72004`, a skany lokalnego przewoźnika ostatniej mili od `77100`:

| Kod i opis Maersk | Co to oznacza dla Ciebie | Peek pokazuje |
| --- | --- | --- |
| `70000` `Shipment Manifest Received`, `70001` `Maersk Shipping Label Created` | `70000`: manifest sprzedawcy obejmuje paczkę, zanim trafi do Maersk. `70001`: własna etykieta Maersk powstająca po wejściu paczki do sieci, często kilka minut przed `70002`. | „Zarejestrowana” |
| `70002` `Parcel Received by Maersk` | Fizyczna paczka przyjęta i zeskanowana w centrum przyjęcia Maersk eCommerce. | „Przyjęta przez przewoźnika” |
| `72001` `Arrived at Maersk Facility`, `72002` `Processing at Maersk Facility`, `72004` `Departed from Maersk Facility` | Posortowana w hubie konsolidacyjnym Maersk i wysłana w stronę regionu docelowego lub bramy tranzytowej. | „W transporcie” |
| `77100` `In-Transit Scan`, `77101` `Enroute to Facility`, `77102` `Arrival at Facility`, `77103` `Processing at Facility`, `77105` `Departed from Facility`, `77106` `Parcel In-transit` | Krajowe skany tranzytowe przekazywane od lokalnego przewoźnika ostatniej mili (np. OnTrac, Amazon Logistics lub DPD). | „W transporcie” |
| `77203` `Out for Delivery` | Załadowana do auta lokalnego kuriera na dzisiejszą trasę doręczeń. | „W doręczeniu” |
| `77200` `Parcel Successfully Delivered` | Doręczona pod adres odbiorcy przez partnera ostatniej mili. | „Dostarczona” |

Skan bez żadnego z tych kodów wyświetla się jako „Zgłoszono problem”, gdy Maersk oznacza go jako wyjątek, a w innym razie przejmuje etap z kroku widocznego w trackerze Maersk.

## Zasięg, zwroty i odprawa celna

Maersk E-Commerce zapewnia pełny zasięg doręczeń w 32 krajach Ameryki Północnej i Europy oraz punkty wprowadzania paczek w Chinach i Azji Południowo-Wschodniej ([Maersk E-Commerce](https://www.maersk.com/supply-chain-logistics/e-commerce-logistics)):

- **Obiekty i partnerzy:** w Europie 8 centrów sortowania i cross-dockingu, huby wejściowe w Londynie, Taulov i Amsterdamie oraz ponad 40 partnerów ostatniej mili i poczt z doręczeniem do domu i do punktów odbioru. W Ameryce Północnej sortownie i bramy przy JFK, LAX i ORD; Standard trwa 2–7 dni w USA i 1–10 dni w Kanadzie, a Expedited 2–5 oraz 1–5 dni.
- **Międzynarodowa odprawa celna:** Maersk odprawia paczki B2C wysyłane do Wielkiej Brytanii, UE i USA. To, czy przy odbiorze pojawią się cło i VAT, zależy od tego, czy sprzedawca naliczył je przy zakupie. Zobacz [przesyłki zatrzymane w urzędzie celnym](guide:customs).
- **Zwroty e-commerce:** krajowe i międzynarodowe zwroty przez nadanie w punkcie lub odbiór z domu u sprzedawców oferujących tę usługę, na części tras bez papieru i zwolnione z cła.

## Przesyłka Maersk eCommerce opóźniona, bez skanów lub zaginiona

Ponieważ Maersk eCommerce jest konsolidatorem B2B wynajmowanym przez marki e-commerce, **zawsze najpierw kontaktuj się ze sklepem internetowym** w sprawie zmiany adresu, brakujących artykułów, wymiany lub zwrotu pieniędzy.

- **Cisza podczas przewozu głównego lub przekazania:** śledzenie często pauzuje na kilka dni między `72004` (`Departed from Maersk Facility`) a pierwszym skanem `77100+` przewoźnika lokalnego, gdy ciężarówki lub samoloty przekazują paczki lokalnemu kurierowi. Zobacz [dlaczego śledzenie przestaje się aktualizować](guide:tracking-not-updating).
- **Korzystaj z numeru lokalnego przewoźnika:** gdy Maersk wskaże przewoźnika i jego numer, to jego tracker i obsługa przejmują dalszą pomoc. Peek wyświetla „Doręcza:” dla [Amazon Logistics](carrier:amazon-logistics), [OnTrac](carrier:ontrac) i [DPD Austria](carrier:dpd-at).
- **Oznaczona jako dostarczona lub uszkodzona:** sprawdź przy wejściach, w paczkowni i u sąsiadów, zajrzyj do śledzenia lokalnego partnera i niezwłocznie zawiadom sklep. Zobacz [dostarczona, ale nieodebrana](guide:delivered-not-received), [zaginiona przesyłka](guide:lost-parcel), [uszkodzona przesyłka](guide:damaged-parcel) oraz [zwrócona nadawcy](guide:returned-to-sender).

## Pomoc Maersk eCommerce i oficjalne portale

Strona wsparcia Maersk oferuje czat na żywo i obsługę zgłoszeń, głównie dla klientów biznesowych; sprzedawcy nadają przesyłki przez panel biznesowy MyParcel w Maersk.

- **Przegląd Maersk E-Commerce i zapytania biznesowe:** [maersk.com/supply-chain-logistics/e-commerce-logistics](https://www.maersk.com/supply-chain-logistics/e-commerce-logistics).
- **Ogólne wsparcie i FAQ Maersk:** [maersk.com/support](https://www.maersk.com/support) oraz [maersk.com](https://www.maersk.com/).
- **Ścieżka wsparcia dla odbiorcy:** skontaktuj się ze sklepem internetowym, w którym dokonano zakupu (lub z lokalnym przewoźnikiem ostatniej mili, gdy zeskanuje paczkę).

> Uważaj na fałszywe SMS-y z prośbą o kliknięcie nieznanego linku i drobną opłatę za zwolnienie „paczki Maersk”. Zawsze weryfikuj komunikaty na stronie zamówienia w sklepie lub na oficjalnej stronie lokalnego kuriera. Zobacz [fałszywe SMS-y o doręczeniu](guide:delivery-scam-texts).

## Śledzenie przesyłki Maersk eCommerce w Peek

[Peek](/) automatycznie odpytuje Maersk eCommerce jako jedynego kandydata po wpisaniu 19-znakowego numeru `1M` (`1MAB4829C7360514928` lub `1MKX7391E4829173605`, wymyślone) i pokazuje „Maersk eCommerce ma tę przesyłkę.”, gdy API paczkowe Maersk to potwierdzi; Peek rozpoznaje też bezpośrednio wklejone linki `maersk.com/tracking/parcel/`. Jeśli musisz wybrać przewoźnika ręcznie, stuknij „Zmień” podczas dodawania lub „Zmień przewoźnika” na ekranie przesyłki (wyszukanie „B2C Europe”, „InterPost”, „Maersk Parcel” albo „Maersk E-Delivery” też go znajduje).

Peek czyta tracker paczkowy Maersk i wyświetla etap („Zarejestrowana”, „Przyjęta przez przewoźnika”, „W transporcie”, „W doręczeniu”, „Zgłoszono problem”, „Dostarczona”), opisy skanów Maersk i lokalnego przewoźnika, miejscowość i region, szacowany dzień doręczenia (gdy Maersk go podaje), kraj docelowy i godzinę doręczenia. Kody statusów i nazwa usługi pozostają ukryte we wszystkich aplikacjach Peek. Maersk podaje przesunięcie UTC dla własnych skanów i większości partnerów; skan partnera bez przesunięcia (np. DPD) otrzymuje strefę czasową swojego miejsca (Wiedeń dla skanu w Grazu) lub sąsiedniego skanu, a Peek pokazuje wszystko w czasie Twojego urządzenia. Powtórzony dwukrotnie skan partnera zostaje raz. Gdy Maersk podaje [Amazon Logistics](carrier:amazon-logistics), [OnTrac](carrier:ontrac) albo [DPD Austria](carrier:dpd-at) z pasującym numerem, Peek sprawdza, czy ten przewoźnik ma przesyłkę: karta pokazuje wtedy „Doręcza:” z nazwą przewoźnika i oba numery. Peek nigdy nie pobiera numeru zamówienia klienta, miejscowości i kodu pocztowego odbiorcy ani linku partnera.

Gdy masz przesyłkę otwartą w Peek albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza status nawet co 10 minut; w dniu, gdy Maersk pokazuje `77203` (`Out for Delivery`), co 2 minuty od godz. 6:00 czasu najnowszego skanu, dopóki tracker Maersk odpowiada. W pozostałym czasie Peek sprawdza co godzinę. Po 12 godzinach wyłącznie na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika” albo po dwóch dniach bez zmian sprawdza co godzinę niezależnie od sposobu śledzenia, a po siedmiu dniach na „Zarejestrowana” — co 6 godzin. Gdy tracker Maersk nie odpowiada i Peek śledzi przesyłkę wyłącznie przez usługi śledzące, sprawdza co godzinę, co 3 godziny po spokojnym dniu i nawet co 15 minut w dniu, w którym przesyłka jest w doręczeniu, dopóki masz ją otwartą lub włączone powiadomienia.

Jeśli najnowszy skan ma cztery dni, a przesyłka nadal widnieje jako „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie”, Peek oznacza ją dniem tego skanu, np. „Brak aktualizacji od pon 12 paź”. Na stronie przesyłki pojawia się wtedy komunikat „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika Maersk eCommerce.” Po trzech tygodniach bez nowego skanu pojawia się informacja „Od ponad trzech tygodni nic nowego. Jeśli przesyłka wciąż nie dotarła, zapytaj sprzedawcę. Peek dalej pyta przewoźnika Maersk eCommerce.”

Możesz wybrać powiadomienia dla opcji „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek śledzi wyłącznie aktualizacje statusu i nie może odprawić przesyłki celnie, przekierować paczki ani skontaktować się za Ciebie z Maersk eCommerce czy jego partnerami ostatniej mili.

## Pytania o śledzenie Maersk eCommerce

### Czym jest Maersk eCommerce (B2C Europe / InterPost)?

Maersk eCommerce to sieć konsolidacji i doręczeń paczek B2C należąca do A.P. Moller–Maersk w Ameryce Północnej i Europie (integrująca dawne sieci B2C Europe i InterPost), obsługująca ponad 80 mln paczek rocznie poprzez ponad 75 partnerów doręczeniowych.

### Jak wygląda numer śledzenia Maersk eCommerce?

Numery paczek Maersk eCommerce mają 19 znaków: `1M`, 7-znakowy alfanumeryczny kod klienta (z co najmniej jedną literą) oraz 10 cyfr — na przykład `1MAB4829C7360514928` (wymyślony).

### Kto doręcza moją przesyłkę Maersk eCommerce pod moje drzwi?

Maersk konsoliduje i przewozi paczkę między hubami, a końcowe doręczenie przekazuje regionalnemu partnerowi w Twoim kraju, takiemu jak [Amazon Logistics](carrier:amazon-logistics), [OnTrac](carrier:ontrac), [DPD Austria](carrier:dpd-at) lub lokalnej poczcie.

### Dlaczego moja paczka Maersk eCommerce stoi dniami na `70000 Shipment Manifest Received`?

`70000` oznacza, że manifest sprzedawcy zgłosił paczkę, zanim Maersk ją fizycznie odebrał, więc nic się nie rusza do czasu przekazania przez sprzedawcę — zapytaj go, jeśli status nie znika. Własną etykietę `70001` Maersk dodaje po wejściu paczki do sieci, często kilka minut przed `70002` (`Parcel Received by Maersk`).

:::sources
- [Maersk: usługi doręczeń paczek E-Commerce](https://www.maersk.com/supply-chain-logistics/e-commerce-logistics) – ponad 80 mln paczek rocznie, ponad 75 zintegrowanych przewoźników, sieci krajowe, międzynarodowe i zwroty w Ameryce Północnej i Europie
- [Maersk: oficjalna strona](https://www.maersk.com/) – globalny portal logistyczny i dostęp do śledzenia przesyłek
- [Maersk: pomoc i FAQ](https://www.maersk.com/support) – oficjalny portal wsparcia Maersk
- [Universal Parcel Scraper: maersk-ecommerce](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/maersk-ecommerce) – 19-znakowy format `1M`, reguła linków `maersk.com/tracking/parcel/`, kody 70000–77200, szacowana data i kraj oraz reguły przekazania Amazon/OnTrac/DPD-AT
:::
