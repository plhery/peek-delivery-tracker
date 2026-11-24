---
title: Śledzenie Chukou1: kody EC-FirstClass, przekazanie i pomoc
description: Śledź przesyłkę transgraniczną Chukou1 (CK1 / 出口易) przez EC-FirstClass: budowa numeru, statusy, przekazanie lokalnemu kurierowi i kontakt.
slug: sledzenie-chukou1
published: 2026-11-24
updated: 2026-11-24
---

Chukou1 (*CK1*, po chińsku *出口易*, zarządzane przez *Guangzhou Beifayi Information Technology Co., Ltd.* / *BFE*) to duży chiński operator logistyki transgranicznej e-commerce i magazynów zagranicznych z siedzibą w Kantonie (Guangzhou), oddziałami w Shenzhen, Szanghaju i Wuhan oraz centrami w Dongguan i Yiwu. Prowadząc magazyny fulfillmentu w USA, Wielkiej Brytanii, Niemczech, Francji, Polsce, Kanadzie, Australii, Peru i Argentynie oraz dedykowane międzynarodowe linie małych paczek (superlinie Europa/USA, globalne linie ekonomiczne, Ameryka Łacińska i TEMU-Y2) dla sprzedawców na Amazon, eBay, AliExpress, SHEIN, TikTok Shop, Walmart i Wayfair, Chukou1 udostępnia śledzenie na platformie **EC-FirstClass** ([ec.chukou1.com](https://ec.chukou1.com/), do 100 numerów naraz). Przesyłkę sprawdzisz tam albo wklejając kod w pole powyżej.

## Numery śledzenia Chukou1 (format przetwarzania EC-FirstClass)

Znajdź numer przetwarzania Chukou1 na stronie zamówienia platformy lub w e-mailu wysyłkowym. Własne numery Chukou1 składają się z 3-literowego prefiksu usługi, 6-cyfrowej daty zamówienia `RRMMDD`, 3- lub 4-znakowego kodu sprzedawcy oraz 6- lub 7-cyfrowego numeru seryjnego (łącznie od 18 do 20 znaków):

| Format (wymyślony) | Co to jest |
| --- | --- |
| `CZN261015ABCD482917` | 19-znakowy numer przetwarzania Chukou1 (`CZN` + data `261015` + 4-znakowy kod klienta `ABCD` + 6-cyfrowy numer seryjny). |
| `CUE261015KLM4829173` | 19-znakowy numer przetwarzania Chukou1 (`CUE` + data `261015` + 3-znakowy kod klienta `KLM` + 7-cyfrowy numer seryjny). |

Po odprawie eksportowej Chukou1 przekazuje przesyłkę przewoźnikowi na dalszy odcinek (EC-FirstClass wymienia China Post, [USPS](carrier:usps), [DHL](carrier:dhl), [FedEx](carrier:fedex) i [UPS](carrier:ups) wśród partnerów) i wyświetla pod Twoim numerem zarówno historię pierwszego odcinka Chukou1, jak i przekazywane skany tego przewoźnika. Zobacz [formaty numerów śledzenia](guide:tracking-number-formats).

## Co oznaczają statusy śledzenia Chukou1 (EC-FirstClass)

Chukou1 łączy ogólny status przesyłki (`InProcess`, `InTransit`, `Delivered`) z angielskimi zdarzeniami z pierwszego etapu oraz przekazywanymi skanami przewoźnika docelowego według czasu lokalnego każdego miejsca skanowania:

| Komunikat Chukou1 | Co oznacza dla Ciebie | Peek pokazuje |
| --- | --- | --- |
| `Shipment information received`, `Carrier received the manifest` | Elektroniczne dane zamówienia odebrane od sprzedawcy przed fizycznym przyjęciem w centrum Chukou1. | „Zarejestrowana” |
| `Item received by Logistics Centre`, `Carrier received the shipment` | Fizyczna przesyłka przyjęta i zeskanowana w magazynie nadawczym Chukou1 w Chinach. | „Przyjęta przez przewoźnika” |
| `Item was sent out from Logistics Centre`, `Transfer to Airport of Origin`, `Departed from Airport of Origin`, `Arrived at Airport of destination`, `Shipment started the customs clearance process at origin` | Przekazana z magazynu na lotnisko wylotu, wyeksportowana i wylądowała w kraju docelowym. | „W transporcie” |
| `Customs clearance in progress`, `Shipment started the customs clearance process at destination`, `Shipment is awaiting customs clearance at destination` | Trwa importowa kontrola celna w kraju docelowym. | „W urzędzie celnym” |
| `Customs declaration completed`, `Turned over to last mile facility`, `Last mile Ascan`, `Arrived at Post Office` | Zwolniona z cła importowego, przekazana lokalnemu przewoźnikowi ostatniej mili i zeskanowana w jego sieci. | „W transporcie” |
| `Shipment is out for delivery` / `Out for Delivery` | Wydana do końcowego doręczenia przez przewoźnika docelowego. | „W doręczeniu” |
| `Shipment is available for pickup` | Gotowa do odbioru w lokalnym urzędzie pocztowym lub punkcie odbioru. | „Do odbioru” |
| `Wrong Address`, `Refused`, `Cannot be delivered` | Próba doręczenia nie powiodła się z powodu błędnego adresu, odmowy albo niemożności doręczenia. | „Próba doręczenia” |
| `Return To Sender`, `Delivered To Sender` | Zwracana do nadawcy lub dostarczona z powrotem nadawcy. | „Zwrócona nadawcy” |
| `Lost` | Wyjątek oznaczony jako zagubiona w transporcie; natychmiast skontaktuj się ze sprzedawcą. | „Zgłoszono problem” |
| `Shipment delivered to customer` / `Delivered` | Doręczona przez partnera w kraju docelowym (z nazwiskiem osoby odbierającej, jeśli je zapisano). | „Dostarczona” |

## Dostawa transgraniczna, przekazanie lokalne i cło

Na stronach [chukou1.com](https://www.chukou1.com/) i [ec.chukou1.com](https://ec.chukou1.com/) Chukou1 działa jako transgraniczny konsolidator B2B i operator magazynów zagranicznych, a nie sieć doręczeń detalicznych pod drzwi.

- **Lokalny partner odpowiada za doręczenie:** od pojawienia się `Turned over to last mile facility` i `Last mile Ascan` liczba prób doręczenia, przekierowania, bezpieczne pozostawienie i czas awizacji zależą od lokalnej poczty lub firmy kurierskiej w Twoim kraju.
- **Cło i opłaty importowe:** przesyłki do UE, USA, Wielkiej Brytanii, Kanady czy Australii podlegają odprawie celnej w kraju docelowym (`Customs clearance in progress`), podatkowi VAT i należnemu cłu (w tym cłu UE 3 € do 150 € i unijnej opłacie manipulacyjnej 2 € dla zamówień spoza UE), chyba że sprzedawca rozliczył je przy zakupie. Zobacz [przesyłki zatrzymane na cle](guide:customs).

## Przesyłka Chukou1 utknęła, nie aktualizuje się lub zaginęła

Ponieważ konto wysyłkowe w Chukou1 należy do sklepu internetowego, w sprawie zwrotów pieniędzy, ponownej wysyłki lub poprawy adresu zawsze kontaktuj się najpierw ze sprzedawcą albo platformą.

- **Cisza między Chinami a krajem docelowym:** międzynarodowy transport lotniczy i zbiorcze odprawy celne często powodują kilkudniowe przerwy między `Departed from Airport of Origin`, `Arrived at Airport of destination` i `Last mile Ascan`. Zobacz [dlaczego śledzenie przestaje się aktualizować](guide:tracking-not-updating).
- **Śledź pod lokalnym numerem po przekazaniu:** gdy EC-FirstClass poda kod kreskowy lokalnego partnera, sprawdzenie go bezpośrednio u tego przewoźnika często pokazuje okna doręczenia lub opcje ponownej dostawy.
- **Dostarczona, uszkodzona lub zagubiona:** sprawdź najpierw u domowników, sąsiadów i u lokalnego kuriera, a następnie zgłoś spór u sprzedawcy przed upływem ochrony kupujących. Zobacz [doręczona, ale nieotrzymana](guide:delivered-not-received), [uszkodzona przesyłka](guide:damaged-parcel), [zaginiona przesyłka](guide:lost-parcel) i [zwrócona nadawcy](guide:returned-to-sender).

## Obsługa klienta i kontakt Chukou1

Oficjalne dane kontaktowe ze stron [Kontakt Chukou1](https://www.chukou1.com/About/Contact.aspx) i [chukou1.com](https://www.chukou1.com/):

- **Chińska infolinia ogólnokrajowa:** `4006 908 223`.
- **Linia reklamacyjna (pon.–pt.):** `13360562736`.
- **Oddziały:** siedziba Kanton `020-23380666` (Units 2403–04, Tower A1, Hangxin Centre, No. 840 Huangpu Avenue East, Huangpu District, Kanton), Shenzhen `0755-28263408`, Szanghaj `021-59793099`, Wuhan `027-59083347`.

> Uważaj na fałszywe SMS-y proszące o drobną płatność online za „zwolnienie” paczki zagranicznej z cła. Prawdziwe opłaty importowe sprawdzaj najpierw na stronie zamówienia lub na oficjalnej stronie swojej poczty. Zobacz [fałszywe SMS-y o przesyłkach](guide:delivery-scam-texts).

## Śledzenie przesyłki Chukou1 w Peek

[Peek](/) automatycznie pyta Chukou1 jako jedynego kandydata po wpisaniu numeru przetwarzania Chukou1 (`CZN261015ABCD482917` lub `CUE261015KLM4829173`, wymyślone) i wyświetla „Chukou1 ma tę przesyłkę.”, gdy EC-FirstClass to potwierdzi; Peek rozpoznaje też wklejone linki `ec.chukou1.com/track?track_number=` oraz `chukou1.com/LogistictsTrack.aspx?trackNo=` dla numerów Chukou1. Jeśli musisz wybrać Chukou1 ręcznie, stuknij „Zmień” podczas dodawania lub „Zmień przewoźnika” na ekranie przesyłki (wyszukanie „出口易” albo „CK1” także go znajduje).

Peek odczytuje śledzenie EC-FirstClass Chukou1 bezpośrednio i pokazuje etap („Zarejestrowana”, „Przyjęta przez przewoźnika”, „W transporcie”, „W urzędzie celnym”, „W doręczeniu”, „Do odbioru”, „Próba doręczenia”, „Zgłoszono problem”, „Zwrócona nadawcy”, „Dostarczona”), skany Chukou1 oraz przekazywane skany przewoźnika docelowego bez numerów telefonu i nazwiska kuriera, ich lokalizację oraz kraj docelowy. Docelowy kod pocztowy i nazwisko osoby podpisującej pozostają prywatne i żadna aplikacja Peek ich nie pokazuje. EC-FirstClass podaje godziny bez strefy czasowej: Peek datuje każdy skan według strefy czasowej jego miejsca i pokazuje go według zegara Twojego urządzenia. Gdy EC-FirstClass wskaże przewoźnika doręczającego i jego numer, a Peek go rozpoznaje, sprawdza, czy ma on paczkę: przesyłka pokazuje wtedy „Doręcza:” z nazwą przewoźnika i wyświetla oba numery. Peek pomija plik PDF potwierdzenia doręczenia; EC-FirstClass nie podaje szacowanej daty dostawy, wagi, nazwy usługi ani osobnej godziny doręczenia.

Dopóki masz przesyłkę otwartą w Peek albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza status do co 10 minut; w dniu, gdy Chukou1 pokazuje skan wydania do doręczenia, co 2 minuty od 6:00 według zegara miejsca ostatniego skanu, dopóki odpowiada EC-FirstClass. W pozostałych sytuacjach sprawdza co godzinę. Po 12 godzinach z samym statusem „Zarejestrowana” lub „Przyjęta przez przewoźnika” albo po dwóch dniach bez wiadomości przechodzi na sprawdzanie co godzinę niezależnie od sposobu śledzenia, a po siedmiu dniach na „Zarejestrowana” co 6 godzin. Gdy EC-FirstClass nie odpowiada i Peek korzysta wyłącznie z zewnętrznych usług śledzenia, sprawdza co godzinę, po spokojnym dniu co 3 godziny i do co 15 minut w dniu, w którym paczka jest w doręczeniu, dopóki masz przesyłkę otwartą lub włączone powiadomienia.

Kiedy najnowszy skan ma cztery dni, a przesyłka nadal stoi na „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie”, Peek oznacza ją dniem tego skanu, np. „Brak aktualizacji od pon 12 paź”. Na stronie przesyłki wyświetla się wtedy „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika Chukou1.” oraz link „Poproś przewoźnika Chukou1 o pomoc” do strony kontaktowej Chukou1. Po trzech tygodniach bez nowego skanu pojawia się komunikat „Od ponad trzech tygodni nic nowego. Jeśli przesyłka wciąż nie dotarła, zapytaj sprzedawcę. Peek dalej pyta przewoźnika Chukou1.”

Możesz wybrać powiadomienia dla opcji „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek jedynie monitoruje aktualizacje śledzenia i nie może odprawić przesyłki celnie, wyświetlać osadzonych plików PDF z potwierdzeniem dostawy ani kontaktować się z Chukou1 czy lokalnym partnerem za Ciebie.

## Pytania o śledzenie Chukou1

### Czym jest Chukou1 (CK1 / EC-FirstClass)?

Chukou1 (*出口易*, zarządzane przez *Guangzhou Beifayi Information Technology Co., Ltd.*) to chiński operator transgranicznej logistyki e-commerce i magazynów zagranicznych, którego portal śledzenia nazywa się **EC-FirstClass** ([ec.chukou1.com](https://ec.chukou1.com/)).

### Jak wygląda numer śledzenia Chukou1?

Numery przetwarzania Chukou1 mają 3 litery prefiksu, 6-cyfrową datę `RRMMDD`, 3- lub 4-znakowy kod sprzedawcy oraz 6- lub 7-cyfrowy numer seryjny (np. `CZN261015ABCD482917`, wymyślony).

### Co oznacza `Last mile Ascan` w śledzeniu Chukou1?

`Last mile Ascan` oznacza, że lokalna poczta lub kurier w Twoim kraju wykonał pierwszy skan przyjęcia po odebraniu paczki z międzynarodowego transportu Chukou1.

### Jak skontaktować się z obsługą klienta Chukou1?

W sprawach zwrotów pieniędzy lub zmiany adresu napisz najpierw do swojego sklepu internetowego. Opublikowana chińska infolinia Chukou1 to `4006 908 223` (reklamacje pon.–pt. `13360562736`, siedziba Kanton `020-23380666`) na [chukou1.com/About/Contact.aspx](https://www.chukou1.com/About/Contact.aspx).

:::sources
- [Chukou1: oficjalna strona](https://www.chukou1.com/) – transgraniczne linie małych paczek, magazyny zagraniczne (US, UK, DE, FR, PL, CA, AU, PE, AR), pierwsza mila FBA i siedziba w Kantonie (*广州市贝法易信息科技有限公司*)
- [Chukou1: kontakt](https://www.chukou1.com/About/Contact.aspx) – `4006 908 223`, reklamacje `13360562736`, telefony i adresy oddziałów Kanton/Shenzhen/Szanghaj/Wuhan (URL pomocy linkowany przez Peek)
- [EC-FirstClass (platforma śledzenia Chukou1)](https://ec.chukou1.com/) – globalny tracker na 100 numerów i wymienieni partnerzy (China Post, USPS, DHL, FedEx, UPS)
- [Universal Parcel Scraper: chukou1](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/chukou1) – format numeru przetwarzania, reguły linków `ec.chukou1.com`/`chukou1.com`, parsowanie skanów pierwszego etapu i partnerów, odczyt odbiorcy/kodu pocztowego/przekazania, pominięty PDF POD i URL pomocy
:::
