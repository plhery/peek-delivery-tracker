---
title: Śledzenie GLS Niemcy: numer, Track-ID, statusy i kontakt
description: Śledź paczkę GLS Niemcy (GLS Germany): numer przesyłki lub Track-ID, wymagany kod pocztowy, statusy po angielsku, punkty ParcelShop, cło i reklamacje.
slug: sledzenie-gls-niemcy
published: 2026-11-18
updated: 2026-11-18
---

GLS Germany (*General Logistics Systems Germany GmbH & Co. OHG*) doręcza od poniedziałku do piątku i według danych grupy dysponuje w Niemczech 9 000 punktów ParcelShop oraz 200 automatami Parcelstation. Niezależnie od tego, czy czekasz na przesyłkę pod niemieckim adresem, czy na zamówienie wysłane z Niemiec do Polski, sprawdzisz ją na [europejskiej stronie śledzenia GLS](https://gls-group.com/EU/en/parcel-tracking) po wpisaniu numeru paczki i kodu pocztowego odbiorcy albo wklejając numer w polu powyżej. Numer znajdziesz w e-mailu o wysyłce ze sklepu, a awizo zostawione przez GLS w Niemczech zawiera kod Track-ID.

## Numer paczki GLS i Track-ID: gdzie je znaleźć

Zajrzyj najpierw do wiadomości od sprzedawcy z potwierdzeniem nadania. Śledzenie działa od chwili, gdy nadawca przekaże GLS dane elektroniczne przesyłki, a paczkę międzynarodową nadaną przez GLS w Niemczech sprawdzisz na tej samej europejskiej stronie grupy (a w Polsce również na [polskiej stronie śledzenia paczek GLS](https://gls-group.com/PL/pl/sledzenie-paczek/)). Papierowe lub mailowe awizo zostawione przez kuriera GLS w Niemczech po nieudanej próbie doręczenia podaje sześcioliterowy kod Track-ID, który w wyszukiwarce działa tak samo jak numer paczki.

Oto formaty rozpoznawane przez Peek, z wymyślonymi przykładami:

| Numer (wymyślony) | Co oznacza |
| --- | --- |
| `48291057364` | 11 cyfr. Ten sam format mają GLS Szwajcaria, GLS Francja, GLS Austria, GLS Polska, PostLogistics i inni przewoźnicy. |
| `482910573604` | 12 cyfr, z ostatnią cyfrą kontrolną. Ten sam układ występuje w [BRT](carrier:brt), [Mondial Relay](carrier:mondial-relay), Colis Privé, DPD Francja, [MRW](carrier:mrw) i u innych. |
| `AB482910` | 8 liter i cyfr: taką postać może przyjąć także Track-ID. Wspólny dla GLS Szwajcaria, GLS Francja, GLS Holandia i Loggi. |
| `KGLSDE` | Sześcioliterowy Track-ID z awiza w Niemczech (wspólny z GLS Szwajcaria). Jeśli nie zwraca wyników, wpisz pełny numer paczki. |

Numer odrzucony? Jeżeli w wiadomości ze sklepu widnieje inna firma kurierska, kod należy do jej sieci: sprawdź [formaty numerów śledzenia](guide:tracking-number-formats).

## Co oznaczają statusy śledzenia GLS

Międzynarodowa strona grupy GLS wyświetla komunikaty po angielsku w typowej kolejności drogi przesyłki:

| Komunikat GLS | Co to oznacza dla Ciebie |
| --- | --- |
| `The parcel data was entered into the GLS IT system…`, `The parcel has not been handed over to GLS.` | GLS otrzymał dane elektroniczne od nadawcy, ale nie ma jeszcze fizycznie paczki. |
| `The parcel was handed over to GLS.` | GLS przyjął paczkę do transportu. |
| `The parcel has not been released by customs.`, `Customs Consignment via Customs Portal` | Przesyłka spoza UE czeka na odprawę celną; Peek pokazuje „W urzędzie celnym”. |
| `The parcel was released by customs.` | Urząd celny zwolnił przesyłkę do dalszej drogi; Peek pokazuje „W transporcie”. |
| `The parcel is in delivery.`, `The parcel is expected to be delivered during the day.` | Paczka jedzie dziś w samochodzie kuriera GLS. |
| `The parcel is ready for collection at the ParcelShop.` | Gotowa do odbioru w punkcie ParcelShop albo w automacie Parcelstation. |
| `The parcel has been delivered.` | Doręczona Tobie albo osobie, która przyjęła ją w Twoim imieniu, np. sąsiadowi. |

Między tymi etapami pojawiają się skany wejścia i wyjścia z sortowni oraz filii GLS. Jeśli kurier nikogo nie zastał lub nie mógł doręczyć przesyłki, Peek pokazuje „Próba doręczenia”. W Niemczech GLS podejmuje drugą próbę albo przekazuje paczkę do pobliskiego punktu ParcelShop, filii lub automatu Parcelstation, podając adres na awizie lub w e-mailu. Gdy paczka nie zostanie odebrana w terminie albo doręczenie okaże się niemożliwe, wraca do nadawcy, a Peek wyświetla „Zwrócona nadawcy”. Uwaga: GLS oznacza przyjęcie paczki w punkcie ParcelShop jako przesyłkę doręczoną, choć nadal czeka tam na Twój odbiór. Dopóki ostatnim wpisem pozostaje `The parcel has not been handed over to GLS.`, sprzedawca nie przekazał paczki kurierowi — skontaktuj się ze sklepem.

## Doręczenia GLS w Niemczech, punkty ParcelShop i automaty

**Dni i czas przewozu.** W Niemczech GLS doręcza od poniedziałku do piątku, nigdy w soboty, podając szacunkowe, niegwarantowane terminy (jedynie usługa biznesowa *Guaranteed24Service* przewiduje doręczenie w następnym dniu roboczym na terenie kontynentalnych Niemiec, z wyłączeniem wysp). Według grupy GLS przesyłka krajowa dociera zazwyczaj w mniej niż 24 godziny, a paczka wysyłana po Europie w 24 do 96 godzin.

**Skrzynka pocztowa.** W Niemczech mała paczka do 2 kg, o wymiarach maksymalnie DIN C4 i grubości do 5 cm może zostać włożona bezpośrednio do skrzynki na listy.

**Gdy nikogo nie ma pod adresem.** W Niemczech kurier może wydać paczkę osobie obecnej w mieszkaniu lub firmie albo sąsiadowi z tego samego lub najbliższego budynku, o ile Ty lub nadawca tego nie wykluczyliście; na awizie lub w e-mailu widnieje nazwisko sąsiada. W przeciwnym razie GLS kieruje przesyłkę do najbliższego punktu ParcelShop, filii lub automatu Parcelstation. GLS wykonuje najwyżej dwie próby doręczenia.

**Zmiana opcji doręczenia.** Gdy GLS udostępnia w Niemczech opcje przekierowania, możesz wybrać inny dzień, inny adres, punkt ParcelShop lub odbiór w filii; informacja konsumencka GLS Germany (styczeń 2018 r.) potwierdza, że opcje te są bezpłatne dla odbiorcy. Przy przesyłkach wysyłanych z zagranicy z usługą *FlexDeliveryService* GLS powiadamia e-mailem, podaje przewidywaną datę lub przedział czasowy, gdy jest dostępny, i pozwala zmienić sposób wydania; jeśli już przy zakupie w zagranicznym sklepie wybierzesz punkt odbioru (*ShopDeliveryService*), paczka trafia od razu do ParcelShopu bez próby doręczenia pod drzwi. Upoważnienie do pozostawienia przesyłki w Niemczech pozwala też zostawić ją we wskazanym miejscu z potwierdzeniem na karcie lub e-mailem.

**ParcelShop i Parcelstation w Niemczech.** Zgodnie z ogólnymi warunkami dla klientów biznesowych GLS Germany paczka pozostawiona po nieobecności w punkcie ParcelShop, filii lub automacie Parcelstation czeka na odbiór co najmniej siedem dni roboczych, zanim wróci do nadawcy. Otwarcie niemieckiego automatu *Parcelstation* wymaga własnego urządzenia mobilnego, a sprzeciw wobec umieszczania przesyłek w automacie — jednorazowy albo stały — zgłosisz według instrukcji na awizie.

## Paczka GLS opóźniona, na cle lub uszkodzona

Umowa przewozu łączy GLS z nadawcą: w sprawie zwrotu pieniędzy albo ponownej wysyłki zwracaj się zawsze do sklepu.

**Śledzenie stoi w miejscu.** Napisz do sprzedawcy i zobacz poradnik [dlaczego śledzenie się nie aktualizuje](guide:tracking-not-updating).

**Oznaczona jako doręczona, ale jej nie masz.** Sprawdź w śledzeniu, czy nie czeka w punkcie ParcelShop, a potem zajrzyj do skrzynki i e-maila, czy nie wpisano sąsiada albo miejsca pozostawienia. Więcej wskazówek w tekście [przesyłka dostarczona, ale nieodebrana](guide:delivered-not-received).

**Cło w zależności od trasy.** Między kontynentalnymi Niemcami a Polską nie nalicza się cła, ponieważ paczka przemieszcza się wewnątrz obszaru celnego i VAT Unii Europejskiej (w Niemczech poza obydwoma obszarami leżą Helgoland i Büsingen). Odprawie celnej podlegają natomiast przesyłki nadane spoza UE: GLS informuje, że cło, podatek importowy i zazwyczaj opłata za obsługę celną muszą zostać uregulowane przed doręczeniem, zależnie od warunków wysyłki wybranych przez sprzedawcę. Przy zakupach na odległość spoza UE do Unii Europejskiej od 1 lipca 2026 r. do przesyłek o wartości do 150 € doliczane jest cło 3 € za każdą kategorię towaru, a od 1 listopada 2026 r. także unijna opłata manipulacyjna 2 € za kategorię towaru niezależnie od wartości przesyłki, należna od zgłaszającego celnego (jak przypomina w Polsce [Poczta Polska](carrier:poczta-polska), należności i opłaty związane z odprawą mogą wystąpić również wtedy, gdy VAT opłacono już przy zakupie w procedurze IOSS). Więcej w poradniku [paczka zatrzymana na cle](guide:customs).

**Uszkodzona przesyłka (prawo niemieckie).** Przy przewozach podlegających prawu niemieckiemu (niemiecki kodeks handlowy, § 438 HGB) widoczne uszkodzenie trzeba zgłosić przy odbiorze (wystarczy poinformować kuriera), szkodę ukrytą w ciągu 7 dni, a opóźnienie w ciągu 21 dni w formie tekstowej (e-mail lub pismo); o zachowaniu terminu decyduje wysłanie zawiadomienia na czas. Utratę lub ubytek może zgłosić zarówno odbiorca, jak i nadawca. Przy zakupie konsumenckim w sklepie ryzyko transportu ponosi sprzedawca, chyba że sam zleciłeś przewóz przewoźnikowi niewskazanemu przez sklep (niemiecki kodeks cywilny, § 475 BGB) — zawiadom więc najpierw sprzedawcę.

**Polubowne rozwiązywanie sporów w Niemczech.** Spór dotyczący zaginięcia lub uszkodzenia paczki, którego nie uda się wyjaśnić z GLS Germany, można bezpłatnie skierować do *Schlichtungsstelle Post* przy Bundesnetzagentur — zarówno jako nadawca, jak i odbiorca — przy wartości sporu od 10 € do 2 500 €; GLS ma obowiązek wziąć udział w postępowaniu wszczętym przez konsumenta.

**Odstąpienie od umowy (*Widerruf*).** Nieodebranie paczki nie oznacza odstąpienia od zakupu. W umowach konsumenckich zawartych na odległość lub poza lokalem przedsiębiorstwa według prawa niemieckiego masz z reguły 14 dni od otrzymania towaru na złożenie sprzedawcy wyraźnego oświadczenia o odstąpieniu bez podawania przyczyny (niemiecki kodeks cywilny, §§ 355 i 356 BGB).

## Obsługa klienta GLS Niemcy i dane kontaktowe

W sprawie zamówienia ze sklepu internetowego pierwszym kontaktem pozostaje sprzedawca. Krajowe kanały kontaktu GLS Germany znajdują się na niemieckiej stronie `gls-pakete.de`. Z pytaniami o status przesyłki grupa GLS prosi zwracać się do działu obsługi klienta GLS we własnym kraju (numer infolinii widnieje na krajowej stronie GLS); zapytania odbiorców przyjmuje także [formularz kontaktowy grupy GLS](https://gls-group.com/GROUP/en/contact-form/). Jeśli sam nadałeś paczkę przez GLS-Pakete.de lub niemiecką aplikację, regulamin z sierpnia 2023 r. podaje adres `versand-service@gls-germany.com` oraz telefon `+49 6677 646907040`.

> **Bezpieczeństwo i płatności w GLS.** Grupa GLS ostrzega przed fałszywymi e-mailami, SMS-ami i stronami podszywającymi się pod jej markę. Jej główną witryną jest `gls-group.com` i tylko ta domena oraz powiązane z nią serwisy krajowe — takie jak `gls-pakete.de` — są prawdziwe. GLS nigdy nie prosi odbiorców e-mailem o opłacenie towaru kartą, przelewem ani płatnością internetową. W przypadku przesyłki spoza UE GLS lub upoważniony agent może jednak poprosić e-mailem, SMS-em albo listownie o uregulowanie ceł i podatków. Przesyłki pobraniowe nadane w usłudze *GLS Cash Service* są wydawane wyłącznie za gotówkę płaconą kurierowi, a GLS odradza opłacanie niespodziewanej paczki za pobraniem — dla siebie lub dla sąsiada. Podejrzane wiadomości e-mail prześlij jako załącznik na adres `security@gls-group.com`.

## Śledzenie przesyłki GLS Niemcy w Peek

[Peek](/) odpytuje przewoźników pasujących do powyższych formatów numerów. Wspólny serwis grupy GLS odpowiada dla przesyłek niemieckich i szwajcarskich: w Polsce (według regionu iPhone'a lub połączenia sieciowego) przy numerach 11- i 12-cyfrowych Peek wybiera domyślnie GLS Poland (korzystający z tego samego serwisu grupy z opcjonalnym polem kodu pocztowego — aby przełączyć przesyłkę na GLS Germany, naciśnij „Zmień” i wskaż GLS Germany), natomiast przy 8-znakowych lub sześcioliterowych kodach Track-ID wskazuje bezpośrednio GLS Germany („GLS Germany ma tę przesyłkę.”), albo GLS Francja, jeśli numer odpowiada bez kodu pocztowego w sieci francuskiej. Wklejenie linku `gls-pakete.de` albo linku `gls-group` z `/DE/`, `/EU/` lub `/GROUP/` od razu wybiera GLS Germany (natomiast link `gls-group` z `/PL/` wybiera GLS Poland).

Ponieważ GLS Germany wymaga kodu pocztowego odbiorcy do pobrania szczegółowej historii, pole „Kod pocztowy odbiorcy” jest obowiązkowe: bez niego Peek sprawdza jedynie, czy GLS zna numer, a gdy wpisany kod nie zgadza się z przesyłką („GLS Germany nie znajduje tej przesyłki z kodem pocztowym …”), poprawisz go przyciskiem „Zmień kod pocztowy”. Działa format kodu pocztowego z dowolnego kraju; trafia on do GLS oraz, w razie potrzeby, do serwisów śledzenia przesyłek.

Zobaczysz wtedy aktualny status, komunikaty po angielsku w brzmieniu GLS, miejscowość i kraj każdego skanu na mapie, przewidywany dzień doręczenia, wagę, godzinę wydania oraz — przy odbiorze w ParcelShopie — jego nazwę i adres pod statusem „Do odbioru”. Twoje imię i nazwisko, adres, dane kontaktowe, podpis i numer referencyjny nadawcy są pomijane. Godziny są odczytywane według czasu niemieckiego i wyświetlane w strefie czasowej Twojego urządzenia; na `peektracker.com` i w aplikacji na iPhonie przypomina o tym dopisek pod historią („Godziny w Twojej strefie czasowej: …”). Peek sprawdza GLS mniej więcej raz na godzinę, również w dniu doręczenia (co 4 godziny po nieudanym zapytaniu), a ręczne odświeżenie nie przyspiesza kolejnego sprawdzenia. Tylko przesyłki przekazane przez GLS do [Swiss Post](carrier:swiss-post) przełączają się automatycznie na drugiego przewoźnika; gdy doręczenie przejmuje inny przewoźnik, Peek sprawdza paczkę w tempie tego, który ją doręcza. Powiadomienia do wyboru: „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”.

## Najczęstsze pytania o śledzenie GLS Niemcy

### Gdzie jest moja paczka GLS?

Śledzenie GLS pokaże to zaraz po wpisaniu numeru przesyłki i kodu pocztowego odbiorcy: `The parcel is in delivery.` oznacza, że paczka jest dziś w samochodzie kuriera, a `The parcel is ready for collection at the ParcelShop.` — że czeka w punkcie odbioru.

### Czym jest Track-ID w GLS?

To sześcioliterowy kod z papierowego lub elektronicznego awiza zostawionego przez kuriera GLS w Niemczech po próbie doręczenia. Działa w wyszukiwarce śledzenia tak samo jak numer paczki.

### Ile idzie paczka GLS z Niemiec?

GLS podaje orientacyjne, niegwarantowane czasy przewozu (poza usługą *Guaranteed24Service* na kolejny dzień roboczy w kontynentalnych Niemczech, bez wysp). Według grupy GLS paczka krajowa dociera zwykle w mniej niż 24 godziny, a przesyłka międzynarodowa w Europie w 24 do 96 godzin.

### Jak długo GLS trzyma paczkę w punkcie ParcelShop?

W Niemczech po nieudanym doręczeniu pod adres punkt ParcelShop, filia lub automat Parcelstation przechowuje paczkę przez co najmniej siedem dni roboczych, po czym odsyła ją do nadawcy.

### Czy GLS Germany to prawdziwy przewoźnik?

Tak: GLS Germany to spółka *General Logistics Systems Germany GmbH & Co. OHG* z siedzibą w Neuenstein w Hesji. Pod jej nazwę podszywają się jednak oszuści, dlatego zawsze sprawdzaj, czy linki prowadzą do `gls-group.com` lub oficjalnych stron krajowych powiązanych z tą witryną.

:::sources
- [GLS: Parcel Tracking](https://gls-group.com/EU/en/parcel-tracking) – europejska strona śledzenia grupy GLS, kod pocztowy i Track-ID z awiza
- [GLS Polska: śledzenie paczek](https://gls-group.com/PL/pl/sledzenie-paczek/) – polska strona śledzenia grupy GLS
- [GLS: About us](https://gls-group.com/GROUP/en/about-us/) – 9 000 punktów ParcelShop i 200 automatów w Niemczech
- [GLS: Parcel, Logistics & Express](https://gls-group.com/GROUP/en/our-business/parcels/) – czas przewozu w kraju (< 24 h) i w Europie (24–96 h), cło, podatki i odprawa przed doręczeniem
- [GLS: B2C Services](https://gls-group.com/GROUP/en/our-business/cross-border-services/b2c/) – usługi FlexDeliveryService i ShopDeliveryService w ruchu międzynarodowym
- [GLS: Contact](https://gls-group.com/GROUP/en/contact-form/) – formularz kontaktowy grupy GLS i odesłanie do krajowych biur obsługi
- [GLS Germany: General Terms and Conditions for business customers (PDF)](https://gls-group.com/DE/media/downloads/AGB_GLS_Germany_2025-01_EN_2024-10-25_FINAL-WEB.pdf) – brak doręczeń w soboty, małe paczki do skrzynki, sąsiedzi, dwie próby, siedem dni roboczych w ParcelShopie, Parcelstation i Guaranteed24Service
- [GLS Germany: Terms for GLS-Pakete.de and the app (PDF)](https://gls-group.com/DE/media/downloads/AGB_GLS-paketeApp_EN_2023-08-21.pdf) – siedziba w Neuenstein, awizo, upoważnienie do pozostawienia paczki, Schlichtungsstelle Post i kontakt dla nadawców
- [GLS Germany: Consumer information on delivery options (PDF)](https://gls-group.com/DE/media/downloads/180130_Verbraucherinformation_Umverfuegung_DE_EN.pdf) – bezpłatne opcje przekierowania dla odbiorców
- [GLS: Security advice](https://gls-group.com/GROUP/en/security-advice/) – oficjalne witryny, ostrzeżenia przed oszustwami, należności spoza UE, GLS Cash Service i zgłoszenia na `security@gls-group.com`
- [Poczta Polska: nowe przepisy UE i opłata za przesyłki spoza UE](https://media.poczta-polska.pl/releases/nowe-przepisy-unii-europejskiej-tak-dziala-nowa-oplata-za-przesylki-spoza-ue) – opłaty związane z odprawą również przy opłaconym wcześniej podatku VAT (IOSS)
- [Bundesnetzagentur: Schlichtungsstelle Post](https://www.bundesnetzagentur.de/DE/Vportal/Schlichtung/Schlichtung_Post/start.html) – bezpłatne postępowanie polubowne od 10 € do 2 500 €
- [Gesetze im Internet: § 438 HGB](https://www.gesetze-im-internet.de/hgb/__438.html) – terminy zgłoszenia uszkodzeń (odbiór / 7 dni) i opóźnienia (21 dni)
- [Gesetze im Internet: § 475 BGB](https://www.gesetze-im-internet.de/bgb/__475.html) – przejście ryzyka w sprzedaży konsumenckiej
- [Gesetze im Internet: § 355 BGB](https://www.gesetze-im-internet.de/bgb/__355.html) – prawo odstąpienia od umowy w ciągu 14 dni bez podawania przyczyny
- [Gesetze im Internet: § 356 BGB](https://www.gesetze-im-internet.de/bgb/__356.html) – bieg terminu na odstąpienie od otrzymania rzeczy
- [Komisja Europejska: zakres terytorialny cła i VAT w UE](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – wyłączenie Helgolandu i Büsingen z obszaru celnego i VAT UE
- [Komisja Europejska: reforma celna UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – cło 3 € do 150 € od 1 lipca 2026 r. i opłata manipulacyjna 2 € od 1 listopada 2026 r.
- [Komisja Europejska: wytyczne dotyczące cła 3 € i opłaty manipulacyjnej (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – opłata 2 € za kategorię towaru niezależnie od wartości, należna od zgłaszającego
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – rozpoznawanie formatów GLS i GLS Poland, kod pocztowy, przekazanie do Swiss Post i sprawdzanie co godzinę
:::
