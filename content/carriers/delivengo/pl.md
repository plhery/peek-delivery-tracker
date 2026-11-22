---
title: Śledzenie Delivengo: paczka La Poste wysłana z Francji
description: Jak śledzić przesyłkę Delivengo nadaną z Francji: numer z końcówką FR, etapy La Poste, doręczenie przez Pocztę Polską, prawa kupującego i kontakt.
slug: delivengo-tracking
published: 2026-11-21
updated: 2026-11-21
---

Delivengo to usługa eksportowa francuskiej poczty [La Poste](carrier:la-poste) przeznaczona dla drobnych przesyłek międzynarodowych. Gdy francuski sklep internetowy wysyła zamówienie przez Delivengo, przesyłka opuszcza Francję z La Poste, a ostatni odcinek w Twoim kraju realizuje lokalny operator pocztowy – w Polsce [Poczta Polska](carrier:poczta-polska). Paczkę sprawdzisz w śledzeniu La Poste na `laposte.fr` po numerze od sprzedawcy albo wklejając go w pole powyżej.

## Numer śledzenia Delivengo: format i przykłady

Numery Delivengo mają międzynarodowy format pocztowy złożony z 13 znaków: dwóch liter, dziewięciu cyfr (ostatnia jest cyfrą kontrolną) oraz `FR`.

**Skąd go wziąć.** Numer przekazuje sklep, zazwyczaj w wiadomości e-mail potwierdzającej wysyłkę. Serwis `mydelivengo.laposte.fr` (MyDelivengo) to panel klienta biznesowego dla nadawców, a nie wyszukiwarka dla odbiorców. Jeśli nie możesz znaleźć kodu, zobacz [jak znaleźć numer śledzenia](guide:find-tracking-number).

**Podobne numery pocztowe** (wszystkie cyfry fikcyjne):

| Przykład (fikcyjny) | Do którego operatora należy |
| --- | --- |
| `LD482910576FR` | [La Poste](carrier:la-poste): ten sam układ mają przesyłki Delivengo, Colissimo i francuskie listy rejestrowane. |
| `RR482910576PL` | [Poczta Polska](carrier:poczta-polska) (końcówka `PL`), a nie Delivengo. |
| `RR482910576DE` | [DHL](carrier:dhl) / Deutsche Post w Niemczech (końcówka `DE`), a nie Delivengo. |

Dwie ostatnie litery wskazują pocztę, która nadała numer. W samym kodzie zakończonym na `…FR` nic nie odróżnia na zewnątrz Delivengo od innej przesyłki La Poste, ale śledzenie działa identycznie. Ponieważ numer zawiera cyfrę kontrolną, pojedyncza literówka sprawia, że jest nieważny i Peek go nie rozpozna – najlepiej go skopiować.

## Statusy Delivengo: co oznaczają zdarzenia z La Poste

System La Poste obsługuje numery Delivengo tak samo jak pozostałe przesyłki pocztowe z Francji. Peek przypisuje każdy skan do czytelnego etapu:

| Etap w Peek | Co oznacza skan La Poste |
| --- | --- |
| „Zarejestrowana” | Przesyłka zapowiedziana elektronicznie: sklep wygenerował etykietę, ale La Poste nie ma jeszcze paczki. |
| „Przyjęta przez przewoźnika” | La Poste przyjęła przesyłkę do przewozu we Francji. |
| „W transporcie” | Paczka przemieszcza się we Francji, czeka na wylot albo dotarła już do kraju docelowego. |
| „W urzędzie celnym” | Przekazana do odprawy celnej (poza unią celną/podatkową); po zwolnieniu wraca do „W transporcie”. |
| „W doręczeniu” | W jednostce doręczeniowej, przygotowywana na dzisiejszy rejon doręczeń. |
| „Próba doręczenia” / „Do odbioru” | Nieudana próba doręczenia pod adresem albo paczka czekająca w placówce pocztowej lub punkcie odbioru. |
| „Dostarczona” | Doręczona. Gdy przesyłki nie ma, zajrzyj do poradnika [dostarczona, ale nieodebrana](guide:delivered-not-received). |
| „Zgłoszono problem” | Problem na trasie (uszkodzenie, odmowa przyjęcia, błędny adres, przekroczony termin odbioru) lub trwający zwrot do sklepu. |
| „Zwrócona nadawcy” | Przesyłka wróciła już do nadawcy. |

Po eksporcie z Francji ostatni odcinek obsługuje poczta kraju docelowego, dlatego historia na `laposte.fr` potrafi zamilknąć po opuszczeniu Francji.

## Doręczenie, awizo i cło w Polsce

**Kto doręcza i co w razie nieobecności.** W Polsce doręczeniem zajmuje się [Poczta Polska](carrier:poczta-polska), a nie La Poste. To jej przepisy określają zasady awizowania, ponownej próby i termin odbioru w placówce; szczegóły opisuje poradnik [nieudane doręczenie](guide:missed-delivery).

**Cło.** Między metropolitalną Francją a Polską w ramach unii celnej UE nie ma ceł importowych. Wyjątkiem są francuskie departamenty i wspólnoty zamorskie (Gwadelupa, Martynika, Gujana Francuska, Reunion, Majotta i Saint-Martin leżą poza unijnym obszarem VAT, a francuskie terytoria zamorskie także poza obszarem celnym UE) – przy przesyłkach z takich terytoriów zobacz [paczka zatrzymana przez urząd celny](guide:customs).

## Przesyłka Delivengo opóźniona, bez aktualizacji lub zagubiona

W pierwszej kolejności skontaktuj się ze sklepem, który nadał paczkę ze swojego konta Delivengo.

**Brak nowych skanów od kilku dni.** Po wyjeździe z Francji historia w La Poste może przestać się zmieniać, podczas gdy paczkę wiezie poczta docelowa. Wpisz ten sam numer `…FR` w śledzeniu [Poczty Polskiej](carrier:poczta-polska) i sprawdź [dlaczego śledzenie się nie aktualizuje](guide:tracking-not-updating).

**Zagubiona, uszkodzona albo zwrócona.** Poproś sprzedawcę o ponowną wysyłkę lub zwrot pieniędzy. Jak przypomina serwis Your Europe, w UE sprzedawca odpowiada za wszelkie uszkodzenia towaru od chwili wysyłki aż do odebrania go przez Ciebie; jeśli zamówienie nie dotrze w ciągu 30 dni (lub w uzgodnionym terminie), wyznacz sprzedawcy dodatkowy rozsądny czas, a gdy nadal nie dostarczy towaru, masz prawo odstąpić od umowy i odzyskać wpłatę. Zobacz też [zagubiona paczka](guide:lost-parcel) oraz [zwrot do nadawcy](guide:returned-to-sender).

## Kontakt w sprawie Delivengo

- **Najpierw sklep**: to on jest właścicielem konta MyDelivengo i stroną umowy z La Poste.
- **La Poste (reklamacje)**: według francuskiego regulatora pocztowego Arcep reklamację do La Poste można złożyć telefonicznie pod numerem `36 31` (poniedziałek–piątek 8:30–19:00, sobota 8:30–13:00) albo pod numerem `+33 810 821 821` (poniedziałek–piątek 8:00–19:00 czasu francuskiego), przez stronę internetową, listownie lub w punkcie pocztowym; Arcep zaznacza, że uprawnienia nadawcy i adresata nie są identyczne i nie wyszczególnia osobno usługi Delivengo.
- **[Poczta Polska](carrier:poczta-polska)**, gdy przesyłka dotrze już do Polski (pod tym samym numerem `…FR`).

## Śledzenie przesyłek Delivengo w Peek

[Peek](/) nie korzysta z osobnego połączenia dla Delivengo – odczytuje wspólne śledzenie [La Poste](carrier:la-poste), tak samo jak dla Colissimo. Po wklejeniu poprawnego numeru `…FR` Peek od razu wskazuje „La Poste / Colissimo”; przyciskiem „Zmień” lub „Zmień przewoźnika” możesz wybrać „Delivengo”, a wynik będzie taki sam. Działają również linki śledzenia z `laposte.fr` (także z `mydelivengo.laposte.fr`).

Peek wyświetla status, historię z krajem każdego skanu oraz przewidywaną datę doręczenia (gdy podaje ją La Poste), pomijając Twoje imię, nazwisko i adres. Komunikaty pozostają w oryginale francuskim La Poste, a godziny są przeliczane na Twoją strefę („Godziny w twojej strefie czasowej: …”). Gdy La Poste wskaże kraj przeznaczenia lub zagranicznego partnera, Peek odpytuje także obsługiwaną pocztę docelową – w tym [Pocztę Polską](carrier:poczta-polska), [DHL](carrier:dhl), [USPS](carrier:usps) czy Canada Post – i przełącza na nią śledzenie, gdy tylko pokaże nowszy postęp, zachowując wcześniejszą historię z Francji.

Gdy masz otwartą kartę przesyłki lub w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza bezpośredni serwis nawet co 10 minut, a po wejściu przesyłki w status „W doręczeniu” – nawet co 2 minuty (tego dnia od godz. 6:00 czasu lokalnego, dopóki odpowiada bezpośrednio śledzenie La Poste); w pozostałym czasie oraz po dwóch dniach bez nowości (12 godzinach przy statusie „Zarejestrowana” lub „Przyjęta przez przewoźnika”) sprawdza co godzinę. Przez serwisy śledzenia w tym dniu statusu „W doręczeniu” na tych samych warunkach odpytuje nawet co 15 minut, poza tym co godzinę albo co 3 godziny po dobie bez zmian. Po przejęciu przesyłki przez pocztę docelową Peek stosuje częstotliwość tego operatora. Powiadomienia możesz ustawić na „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”.

## Najczęstsze pytania o śledzenie Delivengo

### Czy Delivengo to to samo co Colissimo?

Nie, choć obie usługi należą do [La Poste](carrier:la-poste) i dzielą ten sam system śledzenia: Delivengo to firmowa usługa eksportowa dla małych paczek międzynarodowych.

### Dlaczego śledzenie Delivengo zatrzymuje się po wyjeździe z Francji?

Po opuszczeniu Francji doręczenie przejmuje poczta kraju docelowego, a historia w La Poste może nie pokazywać już dalszych skanów. Sprawdź ten sam kod `…FR` w [Poczcie Polskiej](carrier:poczta-polska) – Peek robi to automatycznie, gdy La Poste podaje Polskę jako kraj docelowy.

### Do kogo zgłosić zaginięcie paczki Delivengo?

Do sklepu internetowego, w którym złożono zamówienie – to on nadał przesyłkę w panelu MyDelivengo i w UE odpowiada za dostarczenie towaru do Twoich rąk.

:::sources
- [Universal Parcel Scraper: Delivengo](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/delivengo) – usługa eksportowa La Poste, doręczenie przez pocztę docelową, konto MyDelivengo i wspólne śledzenie
- [Universal Parcel Scraper: La Poste](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/la-poste) – format S10 z cyfrą kontrolną, kody zdarzeń i przekazanie do poczty zagranicznej
- [Unia Europejska: Unia celna UE w działaniu](https://european-union.europa.eu/priorities-and-actions/actions-topic/customs_pl) – brak ceł w obrocie towarowym między państwami UE
- [Komisja Europejska: Territorial scope of EU VAT and customs](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – status celny i VAT francuskich terytoriów zamorskich
- [Your Europe: Wysyłka i dostawa](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_pl.htm) – odpowiedzialność sprzedawcy do chwili odbioru oraz 30-dniowy termin dostawy
- [Arcep: La résolution des litiges postaux](https://www.arcep.fr/mes-demarches-et-services/consommateurs/fiches-pratiques/la-resolution-des-litiges-postaux.html) – składanie reklamacji w La Poste (36 31, +33 810 821 821 i godziny pracy)
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – zakres danych odczytywanych przez Peek
:::
