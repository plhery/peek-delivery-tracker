---
title: Śledzenie GLS Szwajcaria: statusy, Swiss Post i kontakt
description: Śledź paczkę GLS w Szwajcarii: formaty numerów, dlaczego potrzebny jest kod pocztowy, statusy po polsku, doręczenie przez Swiss Post i kontakt.
slug: sledzenie-gls-szwajcaria
published: 2026-11-22
updated: 2026-11-22
---

GLS to międzynarodowa sieć paczkowa z siedzibą w Amsterdamie, która nie ma własnej spółki w Szwajcarii: jej partner, Swiss Post, odprawia celnie i doręcza paczki sprowadzane przez GLS do Szwajcarii. Przesyłkę możesz śledzić na [stronie śledzenia GLS](https://gls-group.com/EU/en/parcel-tracking), podając numer paczki i kod pocztowy, albo wklejając numer w pole powyżej. Numer przekazuje nadawca — zacznij od wiadomości o wysyłce ze sklepu.

## Numer paczki i Track ID w GLS Szwajcaria

System śledzenia GLS przyjmuje numery paczek lub Track ID. GLS drukuje Track ID na swoich awizach, jednak w Szwajcarii doręcza Swiss Post i żadna strona nie pokazuje szwajcarskiego awiza GLS. Ani GLS, ani Swiss Post nie publikują oficjalnego wzorca numerów. Formaty rozpoznawane przez Peek jako GLS to:

| Numer (wymyślony) | Co to jest |
| --- | --- |
| `48291736542` | 11 cyfr. Pasuje też do [GLS Germany](carrier:gls-de), [GLS France](carrier:gls-fr), PostLogistics i [DHL eCommerce Poland](carrier:dhl-ecommerce-pl). |
| `482917365424` | 12 cyfr, ostatnia to cyfra kontrolna. Pasuje też do BRT, Mondial Relay, Colis Privé i innych. |
| `K7M4P9X2` | 8 liter i cyfr. Pasuje też do GLS Germany i GLS France. |
| Sześć liter | Np. `HMPQRT`. Pasuje też do GLS Germany. |

Skopiuj numer zamiast przepisywać go ręcznie: 12-cyfrowy numer z błędną ostatnią cyfrą albo 11-cyfrowy bez jednej cyfry nie pasuje już do GLS. Jeśli e-mail sklepu wskazuje innego przewoźnika, numer należy do niego. Nie masz numeru? Zobacz [jak znaleźć numer przesyłki](guide:find-tracking-number).

## Śledzenie GLS w Szwajcarii: co znaczy każdy status

Śledzenie GLS dla paczek w Szwajcarii działa wyłącznie po angielsku, a GLS nie publikuje szwajcarskiej listy statusów. Oto komunikaty używane przez śledzenie grupowe GLS, po kolei:

| GLS podaje | Co to oznacza dla Ciebie |
| --- | --- |
| `Preadvice`, `The parcel has not been handed over to GLS.` | Nadawca wprowadził dane przesyłki. GLS jeszcze nie ma paczki. |
| `The parcel was handed over to GLS.` | GLS przejął paczkę. |
| `In transit`, `The parcel has left the parcel center` | W drodze między sortowniami GLS. |
| `Customs Consignment via Customs Portal`, `The parcel has not been released by customs.` | Na cle, które dla paczek GLS obsługuje Swiss Post. |
| `The parcel was released by customs.` | Zwolniona przez urząd celny i ponownie w drodze. |
| `Final parcel center` | W ostatniej sortowni — kolejnym etapem jest doręczenie. |
| `In delivery`, `The parcel is in delivery.` | W doręczeniu, przewidywana w ciągu dnia. |
| `The parcel could not be delivered as the consignee was absent` | Niedoręczona z powodu nieobecności odbiorcy. |
| `The parcel is stored in the parcel center` | Przechowywana w oddziale po nieudanej próbie, np. z powodu nieobecności. |
| `The parcel is ready for collection at the ParcelShop.` | Czeka w punkcie ParcelShop lub automacie na odbiór. |
| `The parcel has been delivered.`, `Delivered` | Doręczona. |

`Preadvice` oznacza wyłącznie, że sklep przekazał dane do systemu GLS — jeśli trwa to dniami, zapytaj nadawcę. GLS wpisuje też `Delivered` dla paczki zostawionej w punkcie ParcelShop, którą nadal musisz odebrać. Te dwa komunikaty o nieudanym doręczeniu i ich przyczyny pochodzą z GLS Holandia, gdzie GLS doręcza samodzielnie; w Szwajcarii doręcza Swiss Post.

## Doręczenia GLS w Szwajcarii: Swiss Post i awiza odbioru

**Kto doręcza.** Partnerem GLS dla Szwajcarii i Liechtensteinu jest Swiss Post GLS, jednostka Swiss Post w Bazylei. Swiss Post odprawia celnie nadchodzące paczki GLS i doręcza je, zastrzegając, że ich odprawa może różnić się od standardowej procedury.

**Punkty ParcelShop.** Żadna strona GLS nie wskazuje własnych punktów ParcelShop ani automatów GLS w Szwajcarii.

**Nieudane doręczenie.** GLS nie publikuje dla Szwajcarii zasad dotyczących liczby prób, awiz ani doręczeń w soboty. Jeśli Swiss Post zostawi awizo odbioru, dla jej własnych przesyłek obowiązują następujące zasady (żadna strona nie wymienia wprost paczek GLS):

- Odbierz paczkę z awizem lub kodem QR w aplikacji Post-App, zwykle w ciągu siedmiu dni (15 dni dla przesyłek międzynarodowych). Przedłużenie terminu jest bezpłatne i nie wymaga konta.
- Po zalogowaniu do „My consignments” możesz zlecić ponowne doręczenie, przekierować paczkę do automatu My Post 24, pod inny adres lub do placówki (8 CHF w dni robocze) albo bezpłatnie upoważnić inną osobę do jednorazowego odbioru.

## Paczka GLS opóźniona, na cle, zaginiona lub uszkodzona

Nadawca wybrał GLS i — jak wskazują strony Swiss Post GLS — zawsze opłaca transport, więc sklep jest Twoim pierwszym punktem kontaktu.

**Brak aktualizacji.** `Preadvice` oznacza, że paczkę wciąż ma sklep; status celny oznacza oczekiwanie na odprawę Swiss Post. W innych przypadkach zobacz [dlaczego śledzenie nie aktualizuje się](guide:tracking-not-updating).

**Opłaty celne.** Kto płaci, zależy od warunków Incoterm wybranych przez nadawcę: przy Incoterm 20 GLS płacisz opłatę celną, cło i VAT, przy 30 lub 40 koszty są dzielone i płacisz co najmniej VAT. Swiss Post stosuje wobec paczek GLS swoje [zwykłe opłaty odprawowe](https://www.post.ch/en/business-solutions/exports-imports-and-customs-clearance/exports/swiss-post-gls/import-costs). Aby zakwestionować rachunek, zadzwoń do międzynarodowej obsługi Swiss Post pod +41 848 48 48 47; urząd celny daje 60 dni od decyzji podatkowej na korektę procedury, adresu lub cła.

**Zaginiona lub uszkodzona.** GLS nie publikuje dla Szwajcarii zasad reklamacji, terminów ani limitów odszkodowania — skontaktuj się ze sklepem.

## Telefon i e-mail kontaktowy GLS Szwajcaria

GLS nie ma własnej szwajcarskiej infolinii. Jej strona kontaktu odsyła na stronę krajowej spółki GLS, którą dla Szwajcarii i Liechtensteinu jest Swiss Post GLS:

- **Telefon:** +41 848 48 48 47, linia importowa Swiss Post GLS, 0,08 CHF za minutę ze szwajcarskiego numeru stacjonarnego, od poniedziałku do piątku 8:00–12:00 i 13:30–17:30 (czasu szwajcarskiego). Swiss Post opisuje ją jako „GLS (parcels)” na stronach biznesowych i celnych, nie jako infolinię śledzenia.
- **E-mail:** infogls@swisspost.ch (angielski), infogls@post.ch, infogls@poste.ch lub infogls@posta.ch.
- **Poczta:** Swiss Post GLS, Post-Passage 11, 4002 Basel.
- **Grupa GLS:** [formularz kontaktowy](https://gls-group.com/GROUP/en/contact-form/) po angielsku.

> GLS ostrzega przed fałszywymi e-mailami, SMS-ami, stronami i oszustami pod drzwiami podszywającymi się pod jej markę. Poza UE GLS lub upoważniony agent może rzeczywiście poprosić o opłacenie należności celnych e-mailem, SMS-em lub listownie, jednak GLS nigdy nie wysyła e-maili z żądaniem zapłaty za towar kartą, płatnością online czy przelewem ani nie prosi o dane płatnicze e-mailem lub SMS-em przy zakładaniu konta. Główna strona to gls-group.com. Oszustwa zgłaszaj na security@gls-group.com.

## Śledzenie paczki GLS Szwajcaria w Peek

[Peek](/) przyjmuje wszystkie cztery formaty i pyta prawdopodobnych przewoźników. GLS Germany i GLS Switzerland znają te same numery, więc Peek kieruje się Twoim krajem (regionem telefonu lub krajem połączenia w przeglądarce). W Szwajcarii i Liechtensteinie zwykle zobaczysz „GLS Switzerland ma tę przesyłkę.”; jeśli paczka idzie do Niemiec, zmień przewoźnika i wybierz GLS Germany. Z innych krajów pojawi się GLS Germany, która równie dobrze śledzi szwajcarskie paczki ze szwajcarskim kodem pocztowym. Link gls-group z `/EU/` lub `/GROUP/` jest śledzony jako GLS Germany, a link gls-group.eu z `/CH/` trafia do GLS Switzerland.

GLS pokazuje historię skanów wyłącznie z kodem pocztowym doręczenia, więc pole „Kod pocztowy odbiorcy” jest wymagane: cztery cyfry, np. 8000. Kod trafia do GLS i w razie potrzeby do usług śledzenia; jeśli nie pasuje do przesyłki, Peek poinformuje Cię („GLS Switzerland nie znajduje tej przesyłki z kodem pocztowym …”), abyś mógł go poprawić.

Zobaczysz wtedy status, miejscowość i kraj każdego skanu na mapie, przewidywany dzień doręczenia, wagę, punkt ParcelShop (gdy paczka tam czeka) oraz czas doręczenia — bez Twojego nazwiska, adresu, danych kontaktowych ani podpisu. Statusy zachowują angielskie brzmienie GLS; przybycie do ParcelShop Peek pokazuje jako „Do odbioru”. Godziny GLS są odczytywane według czasu zurychskiego i przeliczane na Twoją strefę czasową (historia kończy się dopiskiem „Godziny w twojej strefie czasowej:” z jej nazwą). Peek sprawdza GLS mniej więcej raz na godzinę, również w dniu doręczenia (co 4 godziny po nieudanej próbie), a ręczne odświeżanie nie przyspiesza sprawdzenia. Gdy doręczenie przejmie inny przewoźnik, Peek przyjmuje jego rytm. Możesz włączyć powiadomienia dla „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Po czterech dniach bez nowego skanu w drodze Peek pokazuje na liście dzień ostatniego skanu („Brak aktualizacji od …”), a na karcie paczki „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika GLS Switzerland.”.

Gdy GLS wskaże Swiss Post jako doręczyciela, strona paczki pokazuje „Doręcza: Swiss Post” (albo „Zgłoszona przewoźnikowi Swiss Post, jeszcze nieprzyjęta”, gdy Swiss Post ma tylko zapowiedź bez własnego skanu), a Peek może przełączyć śledzenie na Swiss Post pod tym samym numerem, gdy Swiss Post pokaże skan co najmniej tak świeży jak ostatni skan GLS.

## Pytania o śledzenie GLS Szwajcaria

### Jaką firmą kurierską jest GLS?

GLS to General Logistics Systems z siedzibą w Amsterdamie, należąca do International Distributions Services (dawniej Royal Mail Group). W Szwajcarii przesyłki obsługuje jej partner, Swiss Post.

### Ile idzie paczka GLS do Szwajcarii?

GLS podaje czas tylko z Austrii: standardowo 2 dni robocze transportu (48 godzin). Z innych krajów kieruj się szacunkiem sklepu.

### Dlaczego śledzenie GLS nie działa?

Najpierw sprawdź kod pocztowy: GLS nie wyświetla historii skanów, dopóki nie podasz kodu pocztowego odbiorcy. Sprawdź też, czy nie brakuje cyfry lub nie ma błędu; stare adresy `gls-group.com/CH/` prowadzą do strony błędu.

### Czy GLS jest wiarygodny?

Tak, ale oszuści podszywają się pod jego nazwę — przeczytaj ostrzeżenie powyżej.

:::sources
- [GLS: Parcel Tracking](https://gls-group.com/EU/en/parcel-tracking) – numery, kod pocztowy, tylko angielski
- [GLS: GLS partners, Switzerland](https://gls-group.com/GROUP/en/gls-partners/?partner=switzerland) – Swiss Post GLS
- [GLS: GLS partners, Liechtenstein](https://gls-group.com/GROUP/en/gls-partners/?partner=liechtenstein) – ten sam partner
- [GLS: About us](https://gls-group.com/GROUP/en/about-us/) – firma
- [GLS: Contact](https://gls-group.com/GROUP/en/contact-form/) – formularz, infolinie
- [GLS: Security advice](https://gls-group.com/GROUP/en/security-advice/) – oszustwa
- [GLS Netherlands: FAQ recipients](https://gls-group.com/NL/en/faq-recipient/) – komunikaty i ich znaczenie, doręczenie w ParcelShop
- [GLS Austria: Versand in die Schweiz](https://gls-group.com/AT/de/versand-in-die-schweiz/) – Swiss Post doręcza, 48 godzin
- [GLS Austria: Regellaufzeiten](https://gls-group.com/AT/de/regellaufzeiten/) – standardowy czas przewozu
- [Swiss Post: Swiss Post GLS](https://www.post.ch/en/business-solutions/exports-imports-and-customs-clearance/exports/swiss-post-gls) – telefon, e-mail
- [Swiss Post: Import costs](https://www.post.ch/en/business-solutions/exports-imports-and-customs-clearance/exports/swiss-post-gls/import-costs) – Incoterms, reklamacje celne
- [Swiss Post: Importverzollung](https://www.post.ch/de/empfangen/importverzollung) – odprawa GLS, opłaty
- [Swiss Post: Collection note](https://www.post.ch/en/receiving-mail/collection-note) – terminy, opcje
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – co odczytuje Peek, oznaczanie ParcelShop jako doręczone
:::
