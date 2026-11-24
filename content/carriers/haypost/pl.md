---
title: Śledzenie HayPost: kody AM, statusy i kontakty
description: Śledź paczkę HayPost Armenia: numery S10 AM, statusy IPS międzynarodowe i krajowe, usługi pocztowe, kontakt w Erywaniu i śledzenie w Peek.
slug: sledzenie-haypost
published: 2026-11-24
updated: 2026-11-24
---

HayPost (*„Haypost” Closed Joint-Stock Company*, po ormiańsku *Հայփոստ*) to narodowy operator pocztowy Republiki Armenii. Założony w 1994 roku i przekształcony w 1998 roku w państwową zamkniętą spółkę akcyjną (CJSC), zarządza około 777 placówkami pocztowymi w Armenii, 23 węzłami regionalnymi i międzynarodowym urzędem wymiany poczty w ramach Światowego Związku Pocztowego (UPU) i PostEurop. Przesyłkę możesz sprawdzić na [stronie Track & Trace HayPost](https://www.haypost.am/en/track-trace) albo wkleić numer w pole powyżej.

## Numery śledzenia HayPost: format S10 i gdzie je znaleźć

Numer śledzenia znajduje się na potwierdzeniu nadania z poczty albo w e-mailu wysyłkowym ze sklepu. HayPost obsługuje standardowe 13-znakowe kody kreskowe UPU S10 z cyfrą kontrolną:

| Format (zmyślony) | Co to jest |
| --- | --- |
| `RR482917367AM` | Międzynarodowy list polecony lub mały pakiet nadany w Armenii (`R` + druga litera + 9 cyfr + `AM`). |
| `CP482917367AM` | Paczka pocztowa nadana w Armenii (`CP` + 9 cyfr + `AM`). |

Wyszukiwarka HayPost obsługuje także przychodzące międzynarodowe kody UPU S10 nadane w innych krajach (zakończone dwuliterowym kodem ISO kraju nadania, np. `US`, `DE` lub `CN`), gdy poczta nadawcza prześle dane elektroniczne lub przesyłka dotrze do Armenii. Zwykła poczta nierejestrowana nie ma kodu S10 i nie można jej śledzić online. Zobacz [formaty numerów przesyłek](guide:tracking-number-formats), jeśli przesyłkę obsługuje prywatny kurier.

## Co oznaczają statusy śledzenia HayPost

HayPost zwraca angielskie wpisy Światowego Związku Pocztowego (IPS) oznaczone etapem podróży: `(Otb)` wychodzące z kraju nadania, `(Inb)` przychodzące do kraju docelowego lub `(EDI-received)` dla danych elektronicznych przekazanych przez pocztę nadawczą.

| HayPost pokazuje | Co to oznacza dla ciebie | Peek pokazuje |
| --- | --- | --- |
| `Receive item from customer (Otb)` | Przyjęta od nadawcy w placówce pocztowej. | „Przyjęta przez przewoźnika” |
| `Insert item into bag (Otb)`, `Send item abroad (EDI-received)`, `Receive item at office of exchange (Otb)` / `(Inb)` | Spakowana do wysyłki zagranicznej, zgłoszona elektronicznie za granicę lub przyjęta w międzynarodowym urzędzie wymiany. | „W transporcie” |
| `Send item to customs (Otb)` | Eksportowa odprawa celna w wychodzącym urzędzie wymiany przed opuszczeniem kraju nadania. | „W transporcie” |
| `Send item to customs (Inb)`, `Record item customs information (Inb)` | Zatrzymana lub sprawdzana przez urząd celny importu w kraju docelowym. | „W urzędzie celnym” |
| `Return item from customs (Inb)` | Zwolniona przez cło z powrotem do sieci pocztowej (nie jest to zwrot do nadawcy). | „W transporcie” |
| `Send item to domestic location (Inb)`, `Receive item at location (Inb)`, `Receive item at sorting centre (Inb)`, `Item was delivered to the postal office (Inb)` | W drodze przez krajowe sortownie aż do lokalnej placówki pocztowej odpowiedzialnej za wydanie. | „W transporcie” |
| `Send item out for physical delivery (Inb)` | Wydana listonoszowi do doręczenia dziś. | „W doręczeniu” |
| `Unsuccessful item delivery attempt (Inb)`, `The notification was sent to the customer: ...` | Próba doręczenia nieudana; HayPost często dopisuje przyczynę i dalszy krok (np. `Addressee not available; Scheduled for further action/processing today`). | „Próba doręczenia” |
| `Receive item at collection point for pick-up (Inb)` | Gotowa do odbioru w placówce pocztowej. | „Do odbioru” |
| `Deliver item (Inb)` | Wydana i zarejestrowana jako doręczona. | „Dostarczona” |

Uważaj na dwa mylące komunikaty. Po pierwsze, `Return item from customs (Inb)` to dobra wiadomość: cło zakończyło kontrolę i oddało paczkę poczcie do dalszego przewozu. Po drugie, `Item was delivered to the postal office (Inb)` (często przekazywane przez poczty zagraniczne) oznacza, że przesyłkę przejęła lokalna placówka pocztowa, a nie że została wydana odbiorcy.

## Usługi krajowe, międzynarodowe i ekspresowe HayPost

W swojej sieci ok. 777 placówek pocztowych ([O HayPost](https://www.haypost.am/en/about-us)) HayPost prowadzi:

- **Usługi krajowe ([haypost.am/en/postal-services/domestic-postal-services](https://www.haypost.am/en/postal-services/domestic-postal-services)):** kartki, listy, małe pakiety do 2 kg, paczki do 20 kg, **Yerevan Express Service (YES)** z doręczeniem śledzonym placówka–placówka lub pod drzwi na terenie Erywania (zlecenia 09:00–16:00 realizowane tego samego dnia; po 16:00 następnego dnia roboczego; sobotnie w poniedziałek; linia zamówień `+374 10 514 513`) oraz **HayPost Sprint** dla krajowych przesyłek e-commerce do 2 kg.
- **Usługi międzynarodowe ([haypost.am/en/postal-services/international-delivery](https://www.haypost.am/en/postal-services/international-delivery)):** listy zagraniczne, małe pakiety do 2 kg (długość + szerokość + grubość ≤ 0,9 m, najdłuższy bok ≤ 0,6 m), paczki do 20 kg (dowolny bok ≤ 1,05 m), worki M do 30 kg i pocztę przyspieszoną EMS.
- **HayPost Express do Stanów Zjednoczonych ([haypost.am/en/postal-services/Haypost-Express](https://www.haypost.am/en/postal-services/Haypost-Express)):** lotnicze doręczenie paczek z dowolnej ormiańskiej placówki do Los Angeles (punkty odbioru w Glendale, Van Nuys i Sylmar), do 30 kg, średni czas przewozu 7–10 dni roboczych.

**FAQ HayPost.** W [FAQ HayPost](https://www.haypost.am/en/faq) wymieniono pytania o terminy doręczeń krajowych, opłaty celne, odszkodowania i czas przechowywania przesyłek EMS; szczegółowe stawki i terminy dla twojej paczki potwierdzisz na poczcie lub w HayPost.

## Opóźnione, oclone lub zagubione paczki HayPost

Przy zakupach online wysyłanych pocztą do lub z Armenii najpierw skontaktuj się ze sprzedawcą w sprawie zwrotu lub ponownej wysyłki.

**Przerwy między krajami.** Po statusie `Send item abroad (EDI-received)` poczta międzynarodowa czeka na transport lotniczy, sortowanie przylotowe i importową odprawę celną w docelowym urzędzie wymiany, co może powodować kilkudniowe przerwy. Zobacz [dlaczego śledzenie przestaje się aktualizować](guide:tracking-not-updating).

**Odprawa celna.** Paczki wchodzące do Armenii i wychodzące z kodem `AM` do kierunków takich jak UE lub USA przechodzą importową kontrolę celną (`Send item to customs (Inb)`). Przygotuj fakturę zakupu i numer śledzenia na wypadek wezwania do potwierdzenia wartości. Zobacz [przesyłki zatrzymane w urzędzie celnym](guide:customs).

**Doręczona, uszkodzona lub zwrócona.** Jeśli widnieje `Deliver item (Inb)`, a przesyłki nie masz, sprawdź z domownikami i w lokalnym oddziale (przez [wykaz placówek HayPost](https://www.haypost.am/en/our-network)), po czym skontaktuj się ze sprzedawcą i obsługą HayPost. Zobacz [dostarczona, ale nieodebrana](guide:delivered-not-received), [uszkodzona paczka](guide:damaged-parcel), [zagubiona paczka](guide:lost-parcel) i [zwrócona nadawcy](guide:returned-to-sender).

## Obsługa klienta i dane kontaktowe HayPost

Dane opublikowane na [haypost.am/en/contact-us](https://www.haypost.am/en/contact-us) (i [haypost.am/am/contact-us](https://www.haypost.am/am/contact-us)):

- **Telefon obsługi klienta:** `+374 10 514-514`.
- **Sprzedaż i obsługa firm:** `+374 10 544-444`.
- **Linia zamówień YES Yerevan Express:** `+374 10 514 513`.
- **E-mail:** `info@haypost.am`.
- **Siedziba:** 65/5 Arshakunyats Ave., Erywań 0061, Republika Armenii.
- **Formularz kontaktowy:** na [haypost.am/en/contact-us](https://www.haypost.am/en/contact-us), plus czat na żywo na [haypost.am](https://www.haypost.am/en).

> Uważaj na SMS-y, wiadomości WhatsApp i e-maile wzywające do kliknięcia zewnętrznego linku i podania danych karty za drobną opłatę za „ponowne doręczenie” albo „potwierdzenie adresu” przesyłki HayPost. Kod S10 sprawdzaj bezpośrednio na [haypost.am/en/track-trace](https://www.haypost.am/en/track-trace) lub pod numerem `+374 10 514-514`. Zobacz [fałszywe SMS-y o przesyłce](guide:delivery-scam-texts).

## Śledzenie paczki HayPost w Peek

Dla kodów UPU S10 kończących się na `AM` (np. `RR482917367AM` lub `CP482917367AM`, cyfry zmyślone) [Peek](/) automatycznie wybiera HayPost jako preferowaną pocztę nadania, a także potrafi śledzić przychodzące kody S10 wysłane do Armenii po wybraniu HayPost. Ponieważ HayPost nie ma dedykowanych reguł linków śledzenia w Peek, wklej sam 13-znakowy kod S10; aby wybrać HayPost ręcznie, stuknij „Zmień” podczas dodawania lub „Zmień przewoźnika” na ekranie paczki i wybierz HayPost (wyszukanie „Armenia Post” lub „Հայփոստ” też go znajdzie).

Peek odczytuje bezpośrednio publiczne angielskie API JSON HayPost i wyświetla etap („Przyjęta przez przewoźnika”, „W transporcie”, „W urzędzie celnym”, „W doręczeniu”, „Próba doręczenia”, „Do odbioru”, „Dostarczona”), komunikaty IPS (w tym przyczyny nieudanych prób i dalsze kroki takie jak `Addressee not available`), placówkę/kraj (bez numerycznych kodów pocztowych), kraj docelowy i godzinę doręczenia. Skany ormiańskie oraz wpisy `Send item abroad (EDI-received)` mają czas Erywania, a skany zagranicznych poczt strefę czasową tamtego miejsca (np. francuską dla Paryża); Peek pokazuje je na zegarze twojego urządzenia. Gdy HayPost z wyprzedzeniem publikuje zaplanowany wpis `Send item abroad (EDI-received)` z przyszłą datą (często kilka godzin naprzód), Peek pomija go do nadejścia właściwego czasu. API HayPost nie zwraca przewidywanej daty dostawy, wagi, nazwy usługi, danych nadawcy/odbiorcy ani zdjęcia doręczenia.

Gdy masz paczkę otwartą w Peek albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza status nawet co 10 minut; w dniu, gdy HayPost pokaże `Send item out for physical delivery (Inb)`, co 2 minuty od 6:00 według zegara najnowszego skanu HayPost, dopóki system HayPost odpowiada. Poza tym sprawdza co godzinę. Po 12 godzinach wyłącznie na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika”, albo po dwóch dniach bez zmian, sprawdza co godzinę niezależnie od sposobu śledzenia, a po siedmiu dniach na etapie „Zarejestrowana” — co 6 godzin. Gdy system HayPost nie odpowiada i Peek korzysta wyłącznie z zewnętrznych usług śledzenia, sprawdza co godzinę, co 3 godziny po dniu ciszy i do co 15 minut w dniu doręczenia, gdy masz paczkę otwartą lub masz włączone powiadomienia.

Gdy najnowszy skan ma cztery dni, a przesyłka nadal widnieje jako „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie”, Peek oznacza ją dniem tego skanu, np. „Brak aktualizacji od pon., 12 paź”. Na stronie paczki pojawia się wtedy napis: „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika HayPost.”, wraz z linkiem „Poproś przewoźnika HayPost o pomoc” do strony kontaktowej HayPost. Po trzech tygodniach bez nowego skanu widnieje komunikat: „Od ponad trzech tygodni nic nowego. Jeśli przesyłka wciąż nie dotarła, zapytaj sprzedawcę. Peek dalej pyta przewoźnika HayPost.”

Możesz wybrać powiadomienia: „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek tylko monitoruje aktualizacje śledzenia — nie odprawi przesyłki celnie ani nie złoży za ciebie reklamacji w HayPost.

## Pytania o śledzenie HayPost

### Jak wygląda numer śledzenia HayPost?

HayPost używa standardowych 13-znakowych kodów pocztowych UPU S10: dwie litery, dziewięć cyfr i końcówka `AM` dla przesyłek nadanych w Armenii (np. `RR482917367AM` lub `CP482917367AM`, cyfry zmyślone). Przychodząca poczta zagraniczna zachowuje dwuliterowy kod kraju nadania.

### Dlaczego moja paczka HayPost ma status `Return item from customs (Inb)`?

Ten status IPS oznacza, że urząd celny zwolnił przesyłkę i przekazał ją z powrotem poczcie do dalszego przewozu — nie oznacza zwrotu do nadawcy.

### Czy HayPost może śledzić paczki wysłane do Armenii z zagranicy?

Tak. Każdy śledzony międzynarodowy kod UPU S10 skierowany do Armenii można sprawdzić na [haypost.am/en/track-trace](https://www.haypost.am/en/track-trace) lub w Peek, gdy dotrą elektroniczne dane o wysyłce albo HayPost zeskanuje przesyłkę w Armenii.

### Jak skontaktować się z obsługą klienta HayPost?

Zadzwoń pod `+374 10 514-514` (lub `+374 10 544-444` dla firm), napisz na `info@haypost.am`, albo skorzystaj z formularza na [haypost.am/en/contact-us](https://www.haypost.am/en/contact-us) bądź czatu na żywo na stronie HayPost.

:::sources
- [HayPost: Home](https://www.haypost.am/en) – portal narodowego operatora pocztowego
- [HayPost: Track & Trace](https://www.haypost.am/en/track-trace) – śledzenie przesyłek online
- [HayPost: About Us](https://www.haypost.am/en/about-us) – założony w 1994 r., CJSC od 1998 r., ok. 777 placówek, 23 węzły regionalne, członek UPU i PostEurop
- [HayPost: Domestic Postal Services](https://www.haypost.am/en/postal-services/domestic-postal-services) – małe pakiety 2 kg, paczki 20 kg, YES Yerevan Express Service (`+374 10 514 513`) i HayPost Sprint
- [HayPost: International Delivery](https://www.haypost.am/en/postal-services/international-delivery) – limity małych pakietów (2 kg), paczek (20 kg), worków M (30 kg) i EMS
- [HayPost: HayPost Express](https://www.haypost.am/en/postal-services/Haypost-Express) – lotnicze paczki do Los Angeles (30 kg, 7–10 dni roboczych)
- [HayPost: Our Network](https://www.haypost.am/en/our-network) – wykaz ormiańskich placówek pocztowych
- [HayPost: Contact Us](https://www.haypost.am/en/contact-us) – `+374 10 514-514`, `+374 10 544-444`, `info@haypost.am`, 65/5 Arshakunyats Ave., Erywań
- [HayPost: Contact Us (ormiański)](https://www.haypost.am/am/contact-us) – oficjalny adres pomocy linkowany przez Peek
- [HayPost: FAQ](https://www.haypost.am/en/faq) – spis zagadnień FAQ
- [Universal Parcel Scraper: haypost](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/haypost) – wykrywanie S10 `AM`, zapytania JSON `api.haypost.am`, mapowanie statusów IPS, zegar Erywania, filtr przyszłych wpisów EDI i adres pomocy
:::
