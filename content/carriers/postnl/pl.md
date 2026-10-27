---
title: PostNL śledzenie przesyłki: numer, statusy, doręczenie
description: Śledzenie przesyłki PostNL: jak wygląda numer z 3S lub NL, co znaczą statusy na postnl.post, kto doręcza paczkę w Polsce i co zrobić, gdy utknie po drodze.
slug: postnl-sledzenie-przesylki
published: 2026-10-22
updated: 2026-10-27
---

PostNL to holenderska poczta. Wysyła za granicę paczki z holenderskich sklepów, a przez Spring GDS, swój oddział międzynarodowy, przewozi też przesyłki sprzedawców internetowych z całego świata. Paczkę śledzisz na [postnl.post](https://postnl.post/), międzynarodowej stronie PostNL po angielsku, albo wklejając numer w polu powyżej. W Polsce doręcza ją zwykle Poczta Polska, a paczki Parcel EU z kodem 3S partner PostNL. Numer znajdziesz w e-mailu o wysyłce ze sklepu.

## Numer przesyłki PostNL: jak wygląda

Według dokumentacji PostNL dla sklepów firma używa dwóch rodzajów kodów kreskowych. Z wymyślonymi cyframi:

| Numer | Co to jest |
| --- | --- |
| `3SABCD0000000` | 3S, kod klienta nadawcy z liter, potem cyfry, razem 13 znaków. To Parcel EU, usługa paczkowa PostNL z Holandii do innych krajów UE, także do Polski. |
| `3SABCD000000000` | Ten sam rodzaj kodu PostNL, w wersji z 15 znakami. |
| `LA000000005NL` | Dwie litery, dziewięć cyfr i NL: międzynarodowy numer pocztowy PostNL. L: mała paczka lub list ze śledzeniem. Także `LW`. |
| `RI000000005NL` | R: list polecony. Także `RF`. |
| `UL000000005NL` | U: mała paczka bez śledzenia. Nie licz na skany w Polsce. |

Ostatnia z dziewięciu cyfr w numerze z NL to cyfra kontrolna: pomyłka w jednej cyfrze zwykle sprawia, że numer staje się nieważny.

Na postnl.post sprawdzisz naraz do 150 numerów. Strona przyjmuje numery z NL i kody 3S z 15 znakami. Kod 3S z 13 znakami odrzuca komunikatem „The shipment barcode was incorrect.”. Wtedy poproś sklep o numer partnera. Gdy sklep tworzy etykietę Parcel EU, PostNL przekazuje mu też kod kreskowy partnera w Polsce, który doręczy paczkę, a pod tym numerem śledzisz ją u partnera. Dopóki paczka jest w Holandii, ten numer może się jeszcze zmienić.

## Statusy PostNL: co znaczą

postnl.post pokazuje pasek postępu: `Pre-advised`, `Preparing`, `Departed`, `Transit`, `Arrived`, `Customs`, `Processing`, `Delivered`. Pod nim są skany, po angielsku. Większość z nich podaje kraj, w którym paczkę zeskanowano. Najczęstsze wpisy, mniej więcej w kolejności:

| postnl.post pisze | Co to dla Ciebie znaczy |
| --- | --- |
| `Pre-advised`, `Preparing` | Nadawca zamówił wysyłkę i przekazał PostNL dane. Paczka mogła jeszcze nie wyjechać. |
| `Consignment received at the PostNL Acceptance Centre` | PostNL przyjął przesyłkę od nadawcy. |
| `The item is received by PostNL` | PostNL zeskanował Twoją paczkę. Ten wpis może się pojawić kilka dni po poprzednim. |
| `Departed`, `Transit` | W drodze między krajami. |
| `Arrived` | Dotarła do kraju na trasie, zwykle do Polski. |
| `The item is at the local sorting centre` | W sortowni w kraju podanym obok skanu. |
| `The item is at the local delivery office` | W placówce, która ją doręczy. |
| `The item is out for delivery` | W doręczeniu. |
| `Driver is en route to the pickup location` | Jedzie do punktu odbioru, nie pod Twój adres. |
| `Pick-up point` | Czeka na Ciebie w punkcie odbioru. |
| `Unsuccesfull`, `Undelivered` | Próba doręczenia się nie udała. Literówka pochodzi od PostNL. |
| `Undeliverable item, has been returned to shipper` | Wraca do nadawcy. |
| `Delivered` | Doręczona. |

`The shipment barcode was not found.` znaczy, że postnl.post nie ma jeszcze nic pod tym numerem albo że w numerze jest błąd.

## Kto doręcza paczki PostNL w Polsce

PostNL albo Spring GDS dowozi paczkę do Polski. Kto ją doręcza, zależy od numeru:

- **Numer z NL**: to międzynarodowy numer pocztowy w standardzie S10, którego poczty używają między sobą. W Polsce taką przesyłkę doręcza zwykle [Poczta Polska](carrier:poczta-polska). W jej wyszukiwarce „Śledź przesyłkę” wpisz numer bez spacji. Poczta Polska pokazuje tam przesyłki rejestrowane, a jej eMonitoring podaje status i po stronie Poczty Polskiej, i po stronie operatorów zagranicznych. Przy listach poleconych widać tylko dzień nadania i doręczenia, bez drogi przesyłki.
- **Kod 3S (Parcel EU)**: paczkę doręcza partner PostNL w Polsce. PostNL nie podaje, kto to jest, i nazywa go po prostu swoim partnerem za granicą. Numer partnera ma sklep.
- **Paczki od sprzedawców spoza Holandii (Spring GDS)**: Spring GDS odpowiada za odcinek międzynarodowy, a ostatni etap przejmuje lokalny partner. Jak pisze, po wjeździe do Twojego kraju paczka często trafia do lokalnej poczty. Nazwę przewoźnika znajdziesz w historii śledzenia.

Liczba prób doręczenia, awizo i czas odbioru zależą od przewoźnika, który doręcza. Zasady Poczty Polskiej znajdziesz na stronie [Poczta Polska](carrier:poczta-polska), a ogólne wskazówki w poradniku o [nieodebranej paczce](guide:uncollected-parcel).

## Paczka PostNL nie aktualizuje się, zaginęła lub wraca

**Brak skanów.** Przy `Pre-advised` lub `Preparing` sprzedawca utworzył etykietę, ale mógł jeszcze nie oddać paczki. Spring GDS radzi odczekać 24–48 godzin od otrzymania numeru.

**Utknęła w drodze.** Po `Departed` lub `Transit` paczka leci samolotem albo płynie statkiem i do przylotu nie ma skanów. Potem sprawdź ten sam numer z NL w Poczcie Polskiej. Więcej w poradniku o [śledzeniu, które stoi](guide:tracking-not-updating).

**Doręczona, ale jej nie ma.** Spring GDS radzi zapytać sąsiadów, sprawdzić miejsca, w których kurier zostawia paczki, i recepcję. Pisze też, że paczka bywa oznaczona jako doręczona nawet 24 godziny przed faktycznym dostarczeniem. Potem napisz do sprzedawcy. Zobacz [doręczona, ale nieodebrana](guide:delivered-not-received).

**Zaginęła lub jest uszkodzona.** Od razu zgłoś to sprzedawcy i zrób zdjęcia zniszczonego opakowania: sprzedawca potrzebuje ich, by złożyć reklamację w Spring GDS. Zobacz [zaginiona paczka](guide:lost-parcel).

**Wraca do nadawcy.** Poproś sprzedawcę o ponowną wysyłkę albo zwrot pieniędzy. Spring GDS jako częste powody podaje błędny adres i nieodebranie paczki w terminie. Rozpoczętego zwrotu nie da się cofnąć. Zobacz [zwrot do nadawcy](guide:returned-to-sender).

## Kontakt z PostNL

Stan na 22 października 2026 r.:

- **postnl.post**: z pytaniami o doręczenie poza Holandią odsyła do sklepu („Please contact your webshop”). W sprawie doręczeń w Holandii kieruje do biura obsługi klienta PostNL.
- **Spring GDS**: nie rozmawia o przesyłkach z odbiorcami, ze względu na ochronę danych i umowy ze sprzedawcami. Umowę ma sprzedawca i to on zgłasza sprawę do Spring GDS.
- **Przewoźnik w Polsce**: w sprawie nieudanej próby doręczenia albo nowego terminu skontaktuj się bezpośrednio z nim; Spring GDS nie umawia ponownych doręczeń. Kontakty Poczty Polskiej są na stronie [Poczta Polska](carrier:poczta-polska).

W pozostałych sprawach najszybciej pomoże sklep albo platforma, na której kupujesz.

## Śledzenie paczki PostNL w Peek

[Peek](/) od razu rozpoznaje numery kończące się na NL z poprawną cyfrą kontrolną i kody 3S z czteroliterowym kodem klienta. Kod 3S z trzema literami trafia do DHL eCommerce Netherlands, choć wytyczne PostNL przewidują trzyliterowe kody także dla jego paczek. Przy krótszym kodzie klienta Peek nie rozpozna przewoźnika po samym numerze. I tak wklej numer: Peek śledzi ponad 3500 przewoźników. W trakcie sprawdzania zobaczysz „Peek pyta: …”. Jeśli wybierze złego przewoźnika, wybierz PostNL przyciskiem „Zmień”. Linki z postnl.post (`/track?barcodes=`) i z mailingtechnology.com, strony śledzenia Spring GDS (`/tracking?tn=`), są czytane jako PostNL.

Peek odczytuje postnl.post. Widzisz status, historię z krajem każdego skanu, godzinę doręczenia i kraj docelowy. Godziny skanów Peek odczytuje według czasu lokalnego każdego kraju i pokazuje w strefie czasowej Twojego urządzenia. PostNL nie podaje przewidywanej daty doręczenia ani nadawcy. Przy numerze z NL, gdy PostNL wskaże Polskę jako cel, Peek pyta też Pocztę Polską o ten sam numer i przełącza się na nią, gdy pokaże nowsze skany. Gdy PostNL nie podaje kraju docelowego, Peek bierze Twój kraj: w aplikacji na iPhone'a ustawiony region, w przeglądarce kraj, z którego dodano paczkę. Kodów 3S z 13 znakami postnl.post nie przyjmuje, więc dla nich Peek szuka paczki w [uniwersalnych serwisach śledzenia](guide:universal-tracker).

Peek sprawdza PostNL co 30 minut, także gdy paczka jest w doręczeniu, a w nocy co godzinę lub dwie. Powiadomienia możesz dostawać o każdym skanie, o ważnych etapach albo tylko w dniu dostawy. Peek nie skontaktuje się za Ciebie z PostNL, Pocztą Polską ani ze sklepem.

## Pytania o śledzenie przesyłki PostNL

### Gdzie jest moja paczka PostNL?

Ostatni skan mówi, na jakim jest etapie i w jakim kraju. Jeśli ma numer z NL i dotarła do Polski, sprawdź ten sam numer w Poczcie Polskiej: tam zobaczysz polskie skany.

### Co znaczy „The shipment barcode was incorrect”?

Że postnl.post nie przyjmuje tego numeru. Zwykle to kod 3S z 13 znakami, czyli paczka Parcel EU. Poproś sklep o numer partnera, który doręcza ją w Polsce, i sprawdź go u tego partnera.

### Czy PostNL doręcza przez Pocztę Polską?

Przy numerze z NL zwykle tak. Spring GDS, międzynarodowy oddział PostNL, pisze, że po wjeździe do Twojego kraju paczka często trafia do lokalnej poczty, a eMonitoring Poczty Polskiej pokazuje też status u operatorów zagranicznych. Paczki Parcel EU z kodem 3S doręcza partner PostNL, którego firma nie wymienia z nazwy.

:::sources
- [PostNL: International Tracking](https://postnl.post/) – 150 numerów, pasek postępu, skany, komunikaty „not found” i „incorrect”, odesłanie do sklepu
- [PostNL Developer: Barcode guidelines](https://developer.postnl.nl/docs/#/http/api-endpoints/send-track/barcode/guidelines) – kody 3S z 13 lub 15 znaków, kod klienta z liter, numery S10 z NL
- [PostNL Developer: Product codes International](https://developer.postnl.nl/docs/#/http/reference-data/product-codes-international) – Parcel EU z 13-znakowym kodem 3S, Polska wśród krajów, numer partnera za granicą, LA/LW, RI/RF, UL
- [Spring GDS: We are Spring GDS](https://www.spring-gds.com/about-us/) – część grupy PostNL
- [Spring GDS: Track and Trace](https://www.spring-gds.com/track/) – lokalny partner, lokalna poczta, pierwszy skan, doręczona a nieodebrana, szkody, zwroty, ponowne doręczenia, brak kontaktu z odbiorcami
- [Poczta Polska: Śledź przesyłkę](https://www.poczta-polska.pl/sledzenie-przesylek/) – przesyłki rejestrowane, numer bez spacji, listy polecone bez drogi przesyłki
- [Poczta Polska: eMonitoring](https://emonitoring.poczta-polska.pl/) – status po stronie Poczty Polskiej i operatorów zagranicznych
- [UPU: S10 standard, Identification of postal items](https://www.upu.int/UPU/media/upu/files/postalSolutions/programmesAndServices/standards/S10-12.pdf) – format numeru używany między pocztami, cyfra kontrolna
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – co Peek odczytuje i pokazuje
:::
