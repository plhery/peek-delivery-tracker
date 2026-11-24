---
title: Śledzenie DobroPost: kody DBRPST, statusy i magazyny
description: Śledź przesyłkę DobroPost z Chin do Rosji lub Białorusi: kody DBRPST, skany magazynowe i celne, odbiór w punktach PVZ, przekazania i wsparcie.
slug: sledzenie-dobropost
published: 2026-11-24
updated: 2026-11-24
---

DobroPost (*ДоброПост*, prowadzony przez *OOO „Dobropost Logistika”* w Moskwie wspólnie z *OOO ICOM DP SOLUTIONS* w Armenii) to pośrednik przesyłek z Chin do Rosji i Białorusi używany przez kupujących na chińskich platformach takich jak Taobao, Poizon, 1688, Pinduoduo, Tmall, 95, Goofish i JD. Kupujący kierują zakupy do magazynu DobroPost w Jixi (Heilongjiang), gdzie towar jest sprawdzany, pakowany i ważony; następnie paczka przechodzi odprawę celną i trafia do własnego punktu odbioru DobroPost (PVZ) albo do [CDEK](carrier:cdek), DPD lub [Russian Post](carrier:russian-post). Śledzenie DobroPost przekazuje też skany Boxberry. Przesyłkę sprawdzisz na [tracking.dobropost.com](https://tracking.dobropost.com/) albo wklejając swój kod `DBRPST` w pole powyżej.

## Numery śledzenia DobroPost (format `DBRPST`)

Numer paczki DobroPost zaczyna się od `DBRPST`, po którym następuje 10 lub 11 cyfr:

| Format (wymyślony) | Co to jest |
| --- | --- |
| `DBRPST6482917304` | 16-znakowy numer paczki DobroPost (`DBRPST` + 10 cyfr). |
| `DBRPST73918462051` | 17-znakowy numer paczki DobroPost (`DBRPST` + 11 cyfr). |

Numery nadawane przez chińskich kurierów w drodze do magazynu DobroPost należą do tych kurierów, a nie do DobroPost: [tracking.dobropost.com](https://tracking.dobropost.com/) i Peek śledzą paczkę pod jej numerem `DBRPST`. Zobacz [formaty numerów śledzenia](guide:tracking-number-formats).

## Co oznaczają statusy śledzenia DobroPost

DobroPost publikuje etapy po rosyjsku wraz z wewnętrznymi kodami statusów — najpierw z własnego magazynu w Chinach i odprawy celnej, a następnie przekazywane bezpośrednio od krajowego przewoźnika ostatniej mili ([CDEK](carrier:cdek), Boxberry lub [Russian Post](carrier:russian-post)):

| Kod lub komunikat DobroPost | Co oznacza dla Ciebie | Peek pokazuje |
| --- | --- | --- |
| `VIRTUAL_PARCEL` | Przesyłka widnieje w rejestrze DobroPost; magazyn w Chinach zapakuje do niej zakupy kilka dni później. | „Zarejestrowana” |
| `ON_ASSEMBLY`, `ON_WEIGHING`, `AWAITING_PAYMENT`, `READY_FOR_SENT`, `ADDED_TO_THE_REGISTRY` | Zapakowana i zważona w chińskim magazynie DobroPost, czeka na opłacenie wysyłki lub została dodana do rejestru wysyłkowego. | „Przyjęta przez przewoźnika” |
| `LEFT_THE_DP_WERHOUSE_IN_CHINA`, `FLEW_OUT_FROM_CHINA` | Opuściła magazyn DobroPost w Chinach i wyruszyła w transport międzynarodowy. | „W transporcie” |
| `AT_CUSTOMS_IN_CHINA`, `ARRIVED_AT_CUSTOMS_IN_THE_COUNTRY_OFF_DESTINATION`, `START_OF_CUSTOMS_CLEARANCE`, `Выпуск с таможни` | W trakcie odprawy eksportowej w Chinach lub odprawy importowej w Rosji albo na Białorusi. | „W urzędzie celnym” |
| `RELEASE_OF_GOODS_WITHOUT_PAYMENT_OF_CUSTOMS_DUTIES`, `LEFT_CUSTOMS_AND_HEADING_TO_LAST_MILE_CARRIER'S_WAREHOUSE`, `READY_FOR_SHIPMENT_FOR_LAST_MILE_DELIVERY` | Zwolniona z cła i skierowana do lokalnej sieci doręczeń. | „W transporcie” |
| `REFUSAL_TO_RELEASE_GOODS_WITH_INDICATION_OF_THE_CODE_OF_THE_REASON_FOR_REFUSAL` | Urząd celny odmówił zwolnienia; śledzenie podaje przyczynę, np. towar nie do użytku osobistego. Sprawdź konto DobroPost. | „Zgłoszono problem” |
| `RECEIVED_AT_SHIPMENT_WAREHOUSE`, `SEND_TO_TRANSIT_OFFICE`, `SEND_TO_RECIPIENT_OFFICE`, `ACCEPTED_FOR_DELIVERY`, `SENT_TO_DESTINATION_CITY`, `Прием`, `Обработка` | Przekazywane skany krajowe z CDEK, Boxberry lub Russian Post w drodze do Twojego miasta. | „W transporcie” |
| `ACCEPTED_AT_WAREHOUSE_ON_DEMAND` (CDEK), `RECEIVED_AT_THE_POINT_OF_ISSUE` (Boxberry) | Dotarła do wybranego punktu odbioru lub oddziału i czeka na odbiór. | „Do odbioru” |
| `GETTING_READY_TO_RETURN`, `SENT_TO_COLLECTION_POINT`, `RETURNED_TO_COLLECTION_POINT`, `RETURNED`, `Возврат` | Nieodebrana z punktu lub zwrócona na ostatnim odcinku, wraca do DobroPost, który może wysłać ją ponownie. | „Zwrócona nadawcy” |
| `RELEASE_OF_GOODS_CUSTOMS_PAYMENTS_PAID` (`Возврат в Китай разрешен`) | Urząd celny odmówił zwolnienia i zezwolił na zwrot paczki do Chin. | „Zwrócona nadawcy” |
| `PARCEL_DELIVERED_TO_RECIPIENT`, `DELIVERED`, `Вручение` | Odebrana w punkcie odbioru lub doręczona odbiorcy. | „Dostarczona” |

## Czasy dostawy, taryfy i punkty odbioru DobroPost PVZ

Według [Taryf DobroPost](https://dobropost.com/tariffs) czasy liczy się od wysyłki z magazynu w Chinach do przybycia do punktu odbioru (PVZ) lub przekazania krajowemu kurierowi:

- **Taryfy Express** (waga wolumetryczna, bez wrażliwych towarów): zwykle **9–13 dni** do Moskwy, **10–14 dni** do Petersburga, **9–12 dni** do Nowosybirska, Krasnodaru, Jekaterynburga, Kazania, Irkucka i Ułan Ude oraz **11–15 dni** do Mińska (Białoruś).
- **Taryfy Economy** (waga rzeczywista, odpowiednie dla cięższych lub gabarytowych paczek): zwykle **19–25 dni** do Moskwy, **20–27 dni** do Petersburga, **15–23 dni** do Irkucka i **22–29 dni** do Mińska.
- **Taryfy Daleki Wschód i Ciężkie Paczki:** **6–9 dni** do Władywostoku, **8–12 dni** do Błagowieszczeńska i **6–9 dni** dla pojedynczych ciężkich paczek od 5 do 30 kg.
- **Punkty odbioru DobroPost PVZ:** wypisane na [dobropost.com/pvz](https://dobropost.com/pvz) w Moskwie, Odincowie, Petersburgu, Kazaniu, Krasnodarze, Nowosybirsku, Jekaterynburgu, Irkucku, Ułan Ude, Błagowieszczeńsku, Władywostoku, Ussuryjsku i Mińsku. Opublikowane opłaty za wydanie w PVZ to `100 RUB` (do 5 kg), `200 RUB` (do 10 kg) i `300 RUB` (powyżej 10 kg); miasta bez własnego PVZ korzystają z partnerskich punktów i kurierów. Paczka czeka w PVZ DobroPost 7 dni bezpłatnie, potem za 50 ₽ dziennie, a po 30 dniach od przybycia jest utylizowana.

## Cło, przerwy w śledzeniu i problemy z paczką

- **Wymagana opłata przed wysyłką:** jeśli śledzenie zatrzymuje się na `AWAITING_PAYMENT`, paczka pozostaje w magazynie w Chinach do czasu opłacenia faktury wysyłkowej na koncie klienta DobroPost.
- **Przerwy na granicy i w cle:** skany często milkną między `FLEW_OUT_FROM_CHINA` a `ARRIVED_AT_CUSTOMS_IN_THE_COUNTRY_OFF_DESTINATION` albo podczas kontroli celnej. Zobacz [przesyłki zatrzymane na cle](guide:customs) i [dlaczego śledzenie przestaje się aktualizować](guide:tracking-not-updating).
- **Uszkodzenia, braki lub zwroty:** [regulamin DobroPost](https://dobropost.com/rules) wymaga wpisania uszkodzeń w protokole przy odbiorze oraz okazania zawartości i oryginalnego opakowania przy reklamacji. [Baza wiedzy](https://dobropost.com/baza_main) wyjaśnia postępowanie w razie braku towaru. Nieodebrana paczka wraca do DobroPost (`RETURNED`), który może ją ponownie wysłać po kontakcie z obsługą. Zobacz [zaginiona przesyłka](guide:lost-parcel), [uszkodzona przesyłka](guide:damaged-parcel), [doręczona, ale nieotrzymana](guide:delivered-not-received) i [zwrócona nadawcy](guide:returned-to-sender).

## Obsługa klienta i oficjalne linki DobroPost

Strona DobroPost udostępnia przycisk „Напишите в поддержку” (napisz do wsparcia), społeczność klientów oraz kanał Telegram, a także umożliwia zgłaszanie skarg i pomysłów bezpośrednio prezesowi Sergeyowi Serbinowi. [Baza wiedzy](https://dobropost.com/baza_main) opisuje kontakt z obsługą, zmianę taryfy utworzonej paczki i odprawę celną.

- **Zarejestrowany operator w Rosji:** *OOO „Dobropost Logistika”* (*ООО «Добропост Логистика»*, INN `9722023780`), Moskwa (`119192, Lomonosovsky Prospekt, 43 bldg. 2, office 4/1/1`; strona podaje również `Michurinsky Prospekt, 54 bldg. 2`).
- **Spółka w Armenii:** *OOO ICOM DP SOLUTIONS* (*ООО ИКОМ ДП СОЛУШНС*), Wanadzor, prowincja Lori, Armenia.
- **Regulamin i taryfy:** [dobropost.com/rules](https://dobropost.com/rules), [dobropost.com/tariffs](https://dobropost.com/tariffs) i [dobropost.com/pvz](https://dobropost.com/pvz).

> Uważaj na fałszywe wiadomości na Telegramie lub linki SMS z żądaniem dopłat celnych poza oficjalnym kontem klienta DobroPost lub oficjalnym portalem agenta celnego. Każdą płatność weryfikuj najpierw na swoim koncie DobroPost. Zobacz [fałszywe SMS-y o przesyłkach](guide:delivery-scam-texts).

## Śledzenie przesyłki DobroPost w Peek

[Peek](/) wykrywa DobroPost automatycznie jako jedynego kandydata po wpisaniu numeru `DBRPST` (`DBRPST6482917304` lub `DBRPST73918462051`, wymyślone) i wskazuje go od razu etykietą „Wykryty przewoźnik”; Peek rozpoznaje też wklejone linki `tracking.dobropost.com/?trackNumber=`. Jeśli musisz wybrać DobroPost ręcznie, stuknij „Zmień” podczas dodawania lub „Zmień przewoźnika” na ekranie przesyłki (wyszukanie „ДоброПост” albo „Dobropost” także go znajduje).

Peek odczytuje śledzenie DobroPost bezpośrednio i pokazuje etap („Zarejestrowana”, „Przyjęta przez przewoźnika”, „W transporcie”, „W urzędzie celnym”, „Do odbioru”, „Zgłoszono problem”, „Zwrócona nadawcy”, „Dostarczona”), rosyjskie opisy DobroPost i przekazywanych partnerów (CDEK, Boxberry i Russian Post) bez numerów telefonów oraz godzinę doręczenia po dostarczeniu. Miejscowość podana przez CDEK w nawiasach na końcu skanu stanowi lokalizację tego skanu. Kody statusów nie trafiają do żadnej aplikacji Peek. DobroPost przekazuje każdy skan z moskiewskim przesunięciem UTC, włącznie ze skanami magazynu w Chinach, a Peek przelicza je na zegar Twojego urządzenia. Śledzenie DobroPost nie podaje szacowanej daty dostawy, wagi, nazwiska odbierającego ani numeru przewoźnika ostatniej mili.

Dopóki masz przesyłkę otwartą w Peek albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza status do co 10 minut, dopóki odpowiada śledzenie DobroPost. W pozostałych sytuacjach sprawdza co godzinę. Śledzenie DobroPost nie pokazuje etapu w doręczeniu, więc Peek nigdy nie przełącza się na sprawdzanie co 2 minuty w dniu dostawy. Po 12 godzinach z samym statusem „Zarejestrowana” lub „Przyjęta przez przewoźnika” albo po dwóch dniach bez wiadomości przechodzi na sprawdzanie co godzinę niezależnie od sposobu śledzenia, a po siedmiu dniach na „Zarejestrowana” co 6 godzin. Gdy śledzenie DobroPost nie odpowiada i Peek korzysta wyłącznie z zewnętrznych usług śledzenia, sprawdza co godzinę, po spokojnym dniu co 3 godziny i do co 15 minut w dniu doręczenia, dopóki masz przesyłkę otwartą lub włączone powiadomienia.

Kiedy najnowszy skan ma cztery dni, a przesyłka nadal stoi na „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie”, Peek oznacza ją dniem tego skanu, np. „Brak aktualizacji od pon., 12 paź”. Na stronie przesyłki wyświetla się wtedy „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika DobroPost.” Po trzech tygodniach bez nowego skanu pojawia się komunikat „Od ponad trzech tygodni nic nowego. Jeśli przesyłka wciąż nie dotarła, zapytaj sprzedawcę. Peek dalej pyta przewoźnika DobroPost.”

Możesz wybrać powiadomienia dla opcji „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek jedynie monitoruje aktualizacje śledzenia i nie może opłacić faktur za wysyłkę, przesłać danych paszportowych do odprawy ani skontaktować się z DobroPost czy partnerami krajowymi za Ciebie.

## Pytania o śledzenie DobroPost

### Jak wygląda numer śledzenia DobroPost?

Międzynarodowe numery śledzenia DobroPost zaczynają się od `DBRPST`, po którym następuje 10 lub 11 cyfr (na przykład `DBRPST6482917304` lub `DBRPST73918462051`, wymyślone).

### Jak długo trwa dostawa DobroPost z Chin?

Szacunki na [dobropost.com/tariffs](https://dobropost.com/tariffs) wynoszą od **6–9 dni** do Władywostoku i **9–13 dni** (Express) lub **19–25 dni** (Economy) do Moskwy, po **11–15 dni** (Express) lub **22–29 dni** (Economy) do Mińska, licząc od wyjścia z magazynu w Chinach.

### Którzy przewoźnicy krajowi doręczają paczki DobroPost w Rosji i na Białorusi?

DobroPost doręcza do własnych punktów PVZ w Rosji i Mińsku oraz — zgodnie z taryfami — przez [CDEK](carrier:cdek), DPD i [Russian Post](carrier:russian-post) (do Mińska wyłącznie CDEK). Śledzenie przekazuje skany CDEK, Boxberry i Russian Post.

### Dlaczego moja paczka DobroPost utknęła na `AWAITING_PAYMENT` lub na cle?

`AWAITING_PAYMENT` oznacza, że przesyłka została spakowana i zważona w magazynie DobroPost w Chinach i czeka na opłacenie wysyłki na Twoim koncie. Przy postoju celnym [Baza wiedzy](https://dobropost.com/baza_main) opisuje odprawę, a wsparcie podaje aktualny status.

:::sources
- [DobroPost: oficjalna strona](https://dobropost.com/) – przesyłki z chińskich platform do Rosji/Białorusi, magazyn w Jixi, wsparcie i kontakt do CEO, spółki w Moskwie i Armenii (*OOO „Dobropost Logistika”*, INN 9722023780)
- [DobroPost: śledzenie paczek](https://tracking.dobropost.com/) – publiczna strona śledzenia `DBRPST`
- [DobroPost: taryfy i czasy dostawy](https://dobropost.com/tariffs) – czasy Express, Economy, Daleki Wschód i Ciężkie Paczki, zasady wagi i przewoźnicy partnerscy
- [DobroPost: punkty odbioru (PVZ)](https://dobropost.com/pvz) – lokalizacje PVZ DobroPost w Rosji i Mińsku, opłaty i przechowywanie
- [DobroPost: Baza wiedzy](https://dobropost.com/baza_main) – poradniki zakupowe, zgłoszenia wsparcia, zmiana taryfy, odprawa celna i brakujące przedmioty
- [DobroPost: Ogólne warunki przewozu](https://dobropost.com/rules) – warunki przewozu i agencji celnej
- [Universal Parcel Scraper: dobropost](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dobropost) – format `DBRPST`, reguła linku `tracking.dobropost.com`, mapowanie statusów DobroPost/CDEK/Boxberry/Russian Post, miasto w nawiasach jako lokalizacja i znaczniki czasu Moskwy
:::
