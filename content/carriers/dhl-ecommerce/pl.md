---
title: Śledzenie DHL eCommerce: numery GM, statusy i kontakt
description: Śledź paczkę DHL eCommerce (dawniej DHL Global Mail): format GM, znaczenie statusów, doręczenie przez USPS lub lokalną pocztę i kontakt.
slug: sledzenie-dhl-ecommerce
published: 2026-11-23
updated: 2026-11-23
---

DHL eCommerce, dawniej DHL Global Mail, to oddział paczkowy DHL dla sklepów internetowych. Sortuje przesyłkę we własnych centrach dystrybucji, a następnie przekazuje ją firmie doręczającej w twoim kraju: [USPS](carrier:usps) w USA, a gdzie indziej lokalnej poczcie lub innemu przewoźnikowi. Śledź ją numerem z e-maila sklepu na stronie Webtrack DHL eCommerce albo wklejając go w pole powyżej.

## Format numeru śledzenia DHL eCommerce

Numery DHL eCommerce mają co najmniej dziesięć znaków i zazwyczaj zaczynają się od `GM`. Webtrack przyjmuje do 10 numerów naraz, oddzielonych przecinkami. Brak numeru? Zapytaj sklep.

| Format (fikcyjny) | Co oznacza |
| --- | --- |
| `GM4829103756182947` | `GM` i od 16 do 18 cyfr: typowy kształt numeru DHL eCommerce. |
| `GM4829103756` | `GM` i od 8 do 15 cyfr: krótszy numer `GM`. |
| `9261482910375618294751` | 22 cyfry zaczynające się od `9261` lub `9361`: numer USPS, który otrzymuje paczka w USA po przekazaniu do USPS. |
| `7394028156342819` | 16 lub 17 cyfr: inny format DHL eCommerce. |
| `0031482910375618294751829364728` | `0031` i 27 cyfr: kolejny format DHL eCommerce. |

Wiersz „Delivery by” na Webtrack podaje nazwę przewoźnika ostatniej mili, którego własny numer może pojawić się później.

Podobne numery: 16 cyfr mogą używać także TNT, Evri, Canada Post czy Correos Express, a `9261` lub `9361` — USPS. Numer 14-cyfrowy należy do [DHL eCommerce UK](carrier:dhl-ecommerce-uk) (osobna sieć z własnym śledzeniem), kod `JVGL` do [DHL eCommerce Netherlands](carrier:dhl-ecommerce-nl), a [DHL Express](carrier:dhl-express) i [DHL Paket](carrier:dhl) mają inne formaty. Więcej w [formaty numerów śledzenia](guide:tracking-number-formats).

## Statusy śledzenia DHL eCommerce

Webtrack pokazuje jednolinijkowy status (`En Route`, `Delivered`, `Exception`…) nad historią („Tracking History”) pisaną wielkimi literami. Najczęstsze skany po kolei:

| DHL eCommerce podaje | Co oznacza dla ciebie |
| --- | --- |
| `LABEL CREATED` | Sklep utworzył etykietę. DHL eCommerce nie ma jeszcze paczki. |
| `EN ROUTE TO DHL ECOMMERCE DISTRIBUTION CENTER OR AWAITING PROCESSING` | W drodze do DHL albo nadal czeka w sklepie. |
| `PACKAGE RECEIVED AT DHL ECOMMERCE DISTRIBUTION CENTER` | DHL eCommerce przyjęło paczkę. |
| `PROCESSED` | Posortowana w centrum dystrybucji. |
| `DEPARTURE ORIGIN DHL ECOMMERCE FACILITY` | Opuściła pierwszy obiekt DHL eCommerce. |
| `ARRIVAL DESTINATION DHL ECOMMERCE FACILITY` | Dotarła do obiektu DHL eCommerce bliżej ciebie. |
| `CUSTOMS CLEARANCE` | W odprawie celnej. |
| `TENDERED TO DELIVERY SERVICE PROVIDER, ALLOW 1-3 DAYS FOR UPDATES FOR PACKAGES WITHIN THE US` | Przekazana przewoźnikowi doręczającemu. W USA aktualizacje mogą mieć przerwę 1–3 dni. |
| `OUT FOR DELIVERY` | Dziś u kuriera lub listonosza. |
| `DELIVERY ATTEMPT FAILED` | Nikomu nie udało się wręczyć paczki. Szukaj awiza lokalnego przewoźnika. |
| `DELIVERED` | Dostarczona. `[IN/AT MAILBOX]` po statusie oznacza skrzynkę pocztową. |
| `RETURNED TO SENDER` | Wraca do sklepu. |

Brytyjska wersja Webtrack formułuje część wpisów inaczej, np. `PROCESSED THROUGH SORT FACILITY`. Paczki w USA pokazują po przekazaniu także skany USPS, jak `ARRIVAL AT POST OFFICE`.

Dwa wpisy często mylą odbiorców: `DELIVERED TO RETURNS AGENT OR COLLEGE/UNIVERSITY FOR FINAL DELIVERY` oznacza przekazanie pośrednikowi (np. punktowi pocztowemu kampusu), który dostarczy przesyłkę tobie; `Picked up at parcelshop` znaczy, że nadawca nadał paczkę w punkcie — nic tam na ciebie nie czeka.

## Jak doręcza DHL eCommerce: USPS, poczta lokalna i nieobecność

W USA FAQ Webtrack podaje, że USPS doręcza paczkę o zwykłej porze roznoszenia poczty. W przesyłkach międzynarodowych przejmuje ją lokalny operator („local delivery service provider”), którym może być poczta krajowa lub inna firma kurierska.

Awiza, punkty odbioru, czas przechowywania i ponowne próby podlegają więc zasadom tego przewoźnika. DHL zaleca sprawdzić skrzynkę na paczkę lub awizo i przekazywać ewentualne instrukcje bezpośrednio do USPS albo lokalnego kuriera.

Zmiana adresu: według FAQ Webtrack po opuszczeniu sklepu DHL eCommerce nie może zmienić adresu — skontaktuj się ze sklepem.

## Paczka DHL eCommerce opóźniona, zagubiona lub zwrócona

**Brak skanów.** FAQ Webtrack podaje, że zdarzenia pojawiają się zwykle w ciągu 24–48 godzin; jeśli po 48 godzinach nic nie ma, zapytaj sklep. Gdy Webtrack nic nie znajduje, zaleca sprawdzić numer, a potem skontaktować się ze sklepem.

**Brak aktualizacji od dni.** FAQ wymienia pogodę, święta, odprawę celną, strajki i inne niezależne zdarzenia. W USA sam skan przekazania przewiduje 1–3 dni przerwy w aktualizacjach. Zobacz [dlaczego śledzenie przestaje się aktualizować](guide:tracking-not-updating).

**Nie dotarła.** Jeśli paczka nie przyszła w przewidywanym oknie dostawy, FAQ Webtrack zaleca kontakt ze sklepem. W UE Your Europe przypomina, że sprzedawca odpowiada za uszkodzenia aż do odbioru i powinien dostarczyć towar w 30 dni, chyba że uzgodniono inaczej; po ponagleniu i dodatkowym terminie możesz odstąpić i odzyskać pieniądze.

**Cło.** `CUSTOMS CLEARANCE` oznacza odprawę celną. Poradnik [przesyłki zatrzymane na cle](guide:customs) wyjaśnia możliwe opłaty.

**Zwrócona.** `RETURNED TO SENDER` oznacza powrót do sklepu — poproś o zwrot pieniędzy lub ponowną wysyłkę.

## Obsługa klienta i telefon DHL eCommerce

Najpierw skontaktuj się ze sklepem, jak radzi FAQ Webtrack. Strona „Contact Customer Service” Webtrack ma karty według krajów:

- **USA i Kanada** (paczki z USA): +1 317-554-5191, pon.–pt. 8:00–20:00 EST (karta Kanady podaje 8:00–21:00 ET).
- **Niemcy:** odsyła do [obsługi klienta DHL Niemcy](https://www.dhl.de/de/privatkunden/hilfe-kundenservice.html), pon.–ndz. 7:00–20:00 (czasu niemieckiego).
- **Irlandia:** 818 370050 (z Irlandii), pon.–pt. 8:00–18:00 (czasu irlandzkiego).
- **Australia:** +61 37 0356818, pon.–pt. 8:00–18:00 (czasu lokalnego).
- **Austria i Szwajcaria:** +49 228 92 95 95 95, pon.–pt. 9:00–18:00 (od 8:00 w Austrii, czasu środkowoeuropejskiego).
- **Wielka Brytania:** podaje linię DHL eCommerce UK, 024 7693 7770. Zobacz stronę [DHL eCommerce UK](carrier:dhl-ecommerce-uk).

Formularz „Contact Support” na Webtrack zapowiada odpowiedź w ciągu 24–72 godzin. Kosztu połączeń nie podano.

## Śledzenie paczki DHL eCommerce w Peek

[Peek](/) od razu wskazuje DHL eCommerce („Wykryty przewoźnik”) dla `GM` i 16–18 cyfr. Krótszy numer `GM` (8–15 cyfr) sprawdza w DHL eCommerce i u innych pasujących przewoźników. Przy współdzielonych formatach (16 lub 17 cyfr, `9261`/`9361`) odpytuje jednocześnie prawdopodobnych przewoźników (np. TNT dla 16 cyfr, Austrian Post a potem USPS dla `9261`/`9361`). Jeśli zna ją tylko DHL eCommerce, Peek to poda; jeśli kilka marek, pokaże każdą z etykietą „Ma tę przesyłkę”, abyś wybrał właściwą. Pod „Zmień” (lub „Zmień przewoźnika”) akceptuje dowolne 5–40 liter i cyfr dla DHL eCommerce. Działają też linki z `webtrack.dhlglobalmail.com`, `ecommerceportal.dhl.com` oraz `dhl.com`, gdy numer ma kształt DHL eCommerce.

Najpierw odpytywany jest Webtrack. Jeśli nie ma tam paczki, Peek otwiera globalną stronę śledzenia `dhl.com` przez przeglądarkę, co trwa dłużej i może zawieść, gdy DHL blokuje automatyczne zapytania.

Peek pokazuje status, każdy skan z miastem i krajem na mapie, przewidywany dzień doręczenia (gdy DHL go podaje), nadawcę, nazwę usługi i godzinę doręczenia, a z Webtrack także wagę i przewoźnika końcowego. Pomija twój adres i referencję klienta. Opisy zachowują amerykański angielski DHL, a każde doręczenie wyświetla się jako „Dostarczona”. Godziny są czytane według lokalnego zegara każdego skanu i przeliczane na twoją strefę czasową („Godziny w twojej strefie czasowej:”).

Gdy Webtrack wskazuje USPS, paczkę można śledzić dalej w USPS z jej numerem jako „Doręcza: USPS” (albo „Zgłoszona przewoźnikowi USPS, jeszcze nieprzyjęta”, dopóki USPS nie doda własnego skanu). Przesyłki odczytywane z globalnej strony DHL (Europa, Azja) nie przechodzą na lokalną pocztę. Peek sprawdza status do co 10 minut, a w dniu przejścia paczki do etapu „W doręczeniu” do co 5 minut od 6:00 według zegara ostatniego skanu, gdy masz paczkę otwartą w Peek lub włączone powiadomienia (awaryjne użycie przeglądarki ogranicza tempo). Po dwóch dniach bez zmian (po 12 godzinach na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika”) sprawdza co godzinę. W powiadomieniach możesz wybrać „Wszystkie aktualizacje”, „Tylko ważne” albo „Tylko w dniu dostawy”. Po czterech dniach bez skanu w transporcie, poza odprawą celną i zgłoszonym problemem, Peek pokazuje „Brak aktualizacji od …”, uwagę „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika DHL eCommerce.” i opcję „Poproś przewoźnika DHL eCommerce o pomoc”. Peek śledzi ponad 4500 przewoźników, ale nie skontaktuje się za ciebie z DHL eCommerce ani ze sklepem.

## Pytania o śledzenie DHL eCommerce

### Czy DHL eCommerce to to samo co DHL Express?

Nie. DHL eCommerce to oddział paczkowy DHL dla sklepów internetowych, który zwykle powierza doręczenie końcowe lokalnej poczcie lub innemu przewoźnikowi. DHL Express ma osobne śledzenie i Peek śledzi obie firmy oddzielnie.

### Czy DHL eCommerce doręcza przez USPS?

W USA tak: według FAQ Webtrack doręczenie końcowe wykonuje USPS. Za granicą dostarcza lokalna poczta lub inna firma kurierska.

### Dlaczego nie ma jeszcze informacji o śledzeniu DHL eCommerce?

Według FAQ Webtrack zdarzenia pojawiają się zwykle w ciągu 24–48 godzin. Jeśli po 48 godzinach nadal nic nie ma, skontaktuj się ze sklepem, który nadał paczkę.

### Czy DHL eCommerce to prawdziwa firma?

Tak. To oddział paczkowy DHL dla handlu internetowego, znany wcześniej jako DHL Global Mail.

:::sources
- [DHL eCommerce: Webtrack tracking](https://webtrack.dhlecs.com/) – strona śledzenia, statusy, okno szczegółów, do 10 numerów, wskazówki przy braku wyników, formularz kontaktowy, aktualizacje e-mail
- [DHL eCommerce: Webtrack Contact Customer Service](https://webtrack.dhlecs.com/support/customer-service) – karty krajów, telefony, godziny, FAQ o 24–48 godzinach, doręczenie USPS, opóźnienia, zaginione paczki, zmiana adresu, instrukcje
- [Your Europe: Shipping and delivery](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_en.htm) – odpowiedzialność sprzedawcy do doręczenia, 30 dni, dodatkowy termin, zwrot pieniędzy
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – dawniej DHL Global Mail, formaty numerów i numer USPS, brzmienia i etapy, osobne śledzenie DHL Express, co czyta i pokazuje Peek
:::
