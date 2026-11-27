---
title: Śledzenie Hermes Niemcy: statusy po polsku i doręczenie
description: Śledzenie przesyłek Hermes Germany (myHermes) po polsku: numery H... i 14-cyfrowe, tłumaczenie statusów, doręczenie do Polski, brak cła w UE i reklamacje.
slug: sledzenie-hermes
published: 2026-11-18
updated: 2026-11-27
---

Hermes Germany (*myHermes*, *Hermes Borderguru*) to niemiecki przewoźnik paczkowy z grupy Otto (*Otto Group*) obsługujący zakupy z niemieckich sklepów i wysyłki do Polski. Wklej numer nadania (*Sendungsnummer*, 14–20 znaków) w polu powyżej w [Peek](/) lub sprawdź go na niemieckiej stronie `myhermes.de`, aby zobaczyć polski etap przesyłki, godziny w swojej strefie czasowej oraz ewentualne przekazanie polskiemu partnerowi.

W Wielkiej Brytanii dawny oddział Hermes UK zmienił w marcu 2022 roku nazwę na [Evri](carrier:evri), natomiast Hermes Germany pozostaje odrębną niemiecką firmą kurierską. Do dostaw mebli i dużego sprzętu AGD z wniesieniem przez dwuosobowe zespoły na terenie Niemiec służy osobna spółka (*Hermes Einrichtungs-Service*).

## Numer śledzenia Hermes: gdzie go znaleźć i jak wygląda

Numer przesyłki (*Sendungsnummer*) otrzymasz w wiadomości e-mail z potwierdzeniem wysyłki od sprzedawcy lub znajdziesz go w szczegółach zamówienia w sklepie (na przykład w zakładce *Mein Konto* na platformie OTTO). Jeśli nie masz jeszcze numeru, poproś o niego nadawcę albo zajrzyj do poradnika, [jak znaleźć numer śledzenia](guide:find-tracking-number).

Publiczny system śledzenia Hermes Germany prosi o numer z 14–20 liter lub cyfr. Najczęściej spotkasz trzy wzorce:

| Format (przykład zmyślony) | Co oznacza | Jak rozpoznaje go Peek |
| --- | --- | --- |
| `H1048291057364928` | Litera `H` oraz od 16 do 19 cyfr: kod kreskowy używany wyłącznie przez Hermes Germany. | Rozpoznawany od razu jako Hermes Germany. |
| `74829105736493` | 14 cyfr spełniających sumę kontrolną Hermes: format współdzielony z [DPD](carrier:dpd-de), [BRT](carrier:brt), [Chronopost](carrier:chronopost) i innymi sieciami. Gdy zaczyna się od `022` (np. `02248291057367`), Hermes Germany jest sprawdzany w pierwszej kolejności. | Hermes Germany jest sprawdzany wśród prawdopodobnych przewoźników; 15-cyfrowy numer z dodatkowym zerem na początku, np. `002248291057367`, jest weryfikowany tak samo. |
| `H482910573649283` | Litera `H` i 15 cyfr: format stosowany przez Hermes Germany lub brytyjskie [Evri](carrier:evri). | Peek odpytuje Hermes Germany (oraz Evri, gdy ostatnia cyfra zgadza się również z sumą kontrolną Evri). |

Wybrane przesyłki — takie jak usługa przewozu bagażu *Hermes Gepäckversand* oferowana podróżnym Deutsche Bahn czy 14-cyfrowe numery referencyjne niebędące kodem kreskowym — wyszukuje się po numerze zamówienia. Jeżeli Peek nie wybierze przewoźnika automatycznie, kliknij "Zmień" przy dodawaniu paczki (lub "Zmień przewoźnika" w widoku przesyłki) i wskaż **Hermes Germany**.

## Statusy śledzenia Hermes wyjaśnione po polsku

Strona `myhermes.de` działa wyłącznie po niemiecku; gdy sprawdzasz paczkę po polsku, Peek pobiera z systemu Hermes Germany komunikaty w języku angielskim, choć pojedyncze wpisy pozostają po niemiecku. Poniżej znajdziesz oryginalne komunikaty z historii oraz odpowiadający im etap w Peek:

| Komunikat Hermes | Etap w Peek | Co to oznacza dla odbiorcy |
| --- | --- | --- |
| `The shipment was announced to Hermes electronically. Further information will follow as soon as Hermes received the shipment.` | "Zarejestrowana" | Nadawca zgłosił przesyłkę elektronicznie, ale Hermes nie odebrał jeszcze fizycznie paczki. |
| `The shipment was taken over by Hermes in … and is prepared for further shipping.` | "Przyjęta przez przewoźnika" | Hermes przyjął paczkę we wskazanym niemieckim oddziale i przygotowuje ją do dalszej drogi. |
| `Die Sendung wird an Hermes weitergeleitet. Weitere Informationen folgen, sobald uns die Sendung erreicht hat.` | "W transporcie" | Przesyłka międzynarodowa zmierza do sieci Hermes przed fizycznym przejęciem w Niemczech. |
| `The shipment has arrived in the destination country … and is prepared for the further shipping.` | "W transporcie" | Paczka dotarła do kraju docelowego i jest sortowana do przekazania lokalnemu partnerowi. |
| `The shipment was prepared for further shipping (…).` | "W transporcie" | Przesyłka została opracowana w sortowni partnera w kraju podanym w nawiasie. |
| `The shipment was loaded into the delivery vehicle and is expected to be delivered today.` / `The shipment was loaded into the delivery vehicle. The delivery is expected to take place today.` | "W doręczeniu" | Kurier załadował paczkę do samochodu z planowanym doręczeniem dzisiaj. |
| `The shipment is ready for pick up in a parcel shop/store.` | "Do odbioru" | Przesyłka czeka na odbiór w punkcie odbioru lub *PaketShopie*. |
| `The shipment could not be delivered.` / `The shipment could not be delivered because the address was not found.` | "Próba doręczenia" | Nieudane doręczenie lub problem z odnalezieniem adresu (`Die Zustelladresse wurde nicht gefunden. Mögliche Gründe: Fehler in der Adressübermittlung. Bitte PaketShop buchen oder Kundenservice kontaktieren.`). |
| `The shipment was not picked up in the parcel shop/store.` | "Zgłoszono problem" | Paczka nie została odebrana z punktu w terminie i wraca do nadawcy. |
| `The shipment was delivered to the recipient address.` / `Die Sendung wurde erfolgreich zugestellt.` | "Dostarczona" | Przesyłka została doręczona pod wskazany adres lub przez partnera za granicą. |

Dwie istotne uwagi przy czytaniu osi czasu:

- Wpisy `Für die Sendung wurde ein WunschPaketShop gebucht.` oraz `Ein WunschTag für die Zustellung wurde gebucht.` potwierdzają jedynie zapisanie preferencji doręczenia przez odbiorcę, a nie ruch paczki — Peek je pomija, aby nie cofać aktualnego etapu przesyłki.
- Jeśli kurierowi zabraknie czasu na trasie przed dotarciem pod twój adres (*round ran out of time*), próba doręczenia nie została podjęta: przesyłka zachowuje status "W transporcie" i wyjeżdża ponownie w następnym dniu roboczym.

## Doręczenie w Niemczech, punkty odbioru i paczki do Polski

**Na terenie Niemiec.** Sieć obejmuje ponad 17 000 punktów *Hermes PaketShop*. Dostępne od ręki towary wysyłane przez OTTO usługą *Hermes Paketversand* docierają w Niemczech zazwyczaj w 1–2 dni robocze (od poniedziałku do soboty). Dzięki opcji *Hermes WunschZustellung* (w śledzeniu lub aplikacji Hermes) odbiorca w Niemczech może wskazać bezpieczne miejsce pozostawienia (*Ablageort*), sąsiada (*Nachbar*), przekierowanie do punktu *PaketShop* albo inny dzień (*Tag*). Paczka skierowana do *Hermes PaketShop* czeka na odbiór do 7 dni roboczych i wymaga osobistego okazania dowodu tożsamości (*Personalausweis*). W automatach *Hermes Box* odbiorca pobiera kod odbioru na stronie śledzenia i ma 72 godziny na wyjęcie paczki — dopóki skrytka czeka na otwarcie, Peek pokazuje etap "Do odbioru" zamiast "Dostarczona". Przy dostawach mebli i dużego AGD w Niemczech realizowanych przez dwuosobowe ekipy (*2-Mann-Teams*) Hermes powiadamia o terminie e-mailem oraz SMS-em, dzwoni na 30–60 minut przed przyjazdem i udostępnia w dniu dostawy podgląd trasy ciężarówki na mapie z dokładnością do pół godziny.

**Przesyłki z Niemiec do Polski.** Poza granicami Niemiec Hermes Germany przekazuje paczkę zagranicznemu partnerowi doręczającemu (w dokumentacji technicznej wymieniane są m.in. sieci takie jak InPost, PostNord czy Correos). Gdy strona śledzenia Hermes udostępnia link do zagranicznego partnera, a Peek rozpoznaje z niego domenę i numer lokalny, na karcie pojawia się informacja "Doręcza: …" (albo "Zgłoszona przewoźnikowi …, jeszcze nieprzyjęta", dopóki partner nie zapisze pierwszego własnego skanu) i śledzenie przełącza się na świeższe zdarzenia lokalnego kuriera. Zasady awizowania, przekierowania do automatu lub punktu oraz czas oczekiwania na odbiór w Polsce zależą od operatora realizującego ostatni odcinek dostawy.

## Cło, VAT i fałszywe dopłaty do paczki

- **Z Niemiec do Polski (wewnątrz UE):** przesyłki nadawane z Niemiec do Polski przemieszczają się swobodnie na wspólnym rynku Unii Europejskiej i nie podlegają odprawie celnej ani dopłatom celno-skarbowym.
- **Przesyłki spoza UE:** jeżeli paczka w sprzedaży na odległość idzie z państwa trzeciego spoza UE, unijna reforma celna znosi zwolnienie z cła dla przesyłek o wartości do 150 EUR: od **1 lipca 2026 r.** obowiązuje ryczałtowe cło **3 EUR** za każdą kategorię taryfową towaru (do 150 EUR), a od **1 listopada 2026 r.** dochodzi unijna opłata manipulacyjna **2 EUR** za każdą kategorię towaru niezależnie od wartości przesyłki.

> **Uwaga na fałszywe SMS-y o dopłacie:** paczka wysłana z Niemiec do Polski nie wymaga żadnych opłat celnych. W Polsce [Poczta Polska](carrier:poczta-polska) ostrzega przed kampaniami phishingowymi podszywającymi się pod powiadomienia kurierskie (np. Pocztex) z linkami do „opłacenia” drobnej dopłaty lub ustalenia terminu dostawy — należności celno-podatkowe od przesyłek spoza UE obsługiwanych przez Pocztę Polską pobiera się przy doręczeniu przesyłki lub przed jej odbiorem, a nie przez podejrzane linki z wiadomości SMS.

## Opóźniona, zagubiona lub uszkodzona paczka Hermes

- **Brak ruchu po "Zarejestrowana":** dopóki widnieje wyłącznie elektroniczne zgłoszenie, nadawca nie przekazał jeszcze paczki do sieci Hermes. Gdy stan nie zmienia się przez kilka dni, skontaktuj się ze sklepem i sprawdź, [dlaczego śledzenie przestaje się aktualizować](guide:tracking-not-updating).
- **Status "Dostarczona", ale paczki nie ma:** upewnij się, czy przesyłka nie trafiła do sąsiada albo punktu odbioru, i od razu zgłoś sprawę sprzedawcy; kolejne kroki opisuje poradnik [przesyłka oznaczona jako dostarczona, ale nieodebrana](guide:delivered-not-received).
- **Zakupy w sklepie internetowym (prawa w UE i Niemczech):** zgodnie z prawem unijnym oraz niemieckim kodeksem cywilnym (§ 475 BGB) profesjonalny sprzedawca ponosi ryzyko utraty lub uszkodzenia rzeczy w transporcie aż do chwili wydania jej kupującemu i ma obowiązek dostarczyć zamówienie w ciągu **30 dni**, o ile nie uzgodniono inaczej. Od odebrania towaru przysługuje ci także **14 dni** na odstąpienie od umowy zawartej na odległość (§§ 355 i 356 BGB). W razie zaginięcia, opóźnienia lub szkody reklamację kieruj bezpośrednio do sklepu.
- **Niemieckie prawo przewozowe i bezpłatny arbitraż pocztowy:** przy przewozach podlegających niemieckiemu kodeksowi handlowemu (§ 438 HGB) widoczne uszkodzenie należy zgłosić przy odbiorze, szkodę ukrytą pisemnie w ciągu **7 dni** od wydania, a przekroczenie terminu dostawy w ciągu **21 dni**; odbiorca może też dochodzić we własnym imieniu roszczeń wynikających z umowy przewozu (§ 421 HGB). Jeśli pisemna reklamacja w Hermes Germany dotycząca utraty, kradzieży lub uszkodzenia przesyłki pocztowej bez warunków specjalnych nie przyniesie porozumienia (wartość sporu od 10 EUR do 2500 EUR), nadawca lub odbiorca może bezpłatnie złożyć wniosek po niemiecku do **Schlichtungsstelle Post** przy urzędzie *Bundesnetzagentur* — gdy wniosek składa konsument, operator pocztowy ma obowiązek wziąć udział w postępowaniu.

## Śledzenie przesyłek Hermes Germany w Peek

[Peek](/) odpytuje bezpośrednio publiczną usługę odbiorczą Hermes Germany bez podawania kodu pocztowego i obsługuje wklejanie linków z `myhermes.de`. Peek odczytuje z treści komunikatów miejscowość niemieckiego oddziału, regionu, kraju docelowego albo punktu *PaketShop*, aby zaznaczyć ją na mapie, przelicza znaczniki czasu z dopiskiem "Godziny w twojej strefie czasowej: …" i nigdy nie pobiera prywatnego adresu domowego ani danych sąsiada (wyświetla wyłącznie nazwę i adres punktu *PaketShop*, w którym paczka czeka lub została odebrana).

Dopóki masz otwartą kartę przesyłki na ekranie — albo w dniu doręczenia przy włączonych powiadomieniach — Peek sprawdza status nawet co 10 minut, a w dniu wydania paczki do doręczenia co 2 minuty od 6:00 czasu niemieckiego (lub pół godziny przed początkiem okna czasowego podanego przez Hermes), o ile system Hermes odpowiada bezpośrednio (przez zewnętrzne serwisy śledzenia: co godzinę, co 3 godziny po dniu bez skanu i do co 15 minut w dniu doręczenia); poza tym sprawdza paczkę co godzinę i utrzymuje rytm godzinny po dwóch dniach bez nowego skanu (lub po 12 godzinach na etapie "Zarejestrowana" bądź "Przyjęta przez przewoźnika"). Gdy doręczenie w Polsce przejmie lokalny partner, Peek dostosowuje częstotliwość do tego przewoźnika. Możesz włączyć powiadomienia o opcji "Każdy skan", "Ważne etapy" lub "Tylko doręczenie"; po usunięciu archiwalnych doręczonych numerów przez Hermes zapytanie zwraca brak przesyłki (404).

## Najczęstsze pytania o śledzenie Hermes Germany

### Kto doręcza paczki Hermes z Niemiec w Polsce?

Za granicą Hermes Germany przekazuje przesyłkę lokalnemu partnerowi doręczającemu (w dokumentacji technicznej pojawiają się m.in. InPost, PostNord i Correos). Gdy strona śledzenia Hermes zawiera link do partnera rozpoznawanego przez Peek, karta paczki pokazuje „Doręcza: …” i przełącza się na bieżące skany lokalnego kuriera.

### Czy Hermes i Evri to ta sama firma?

Tylko w Wielkiej Brytanii, gdzie dawny Hermes UK działa od marca 2022 roku jako [Evri](carrier:evri). Hermes Germany to odrębna niemiecka firma kurierska z grupy Otto; przy 16-znakowych numerach zaczynających się od `H` i 15 cyfr Peek sprawdza obie sieci, jeśli suma kontrolna pasuje do Evri.

### Dlaczego statusy Hermes są po angielsku lub po niemiecku?

Strona `myhermes.de` działa wyłącznie po niemiecku, natomiast Peek pobiera angielską wersję opisów (część wpisów zostaje po niemiecku) i tłumaczy główny etap przesyłki na język polski („Zarejestrowana”, „Przyjęta przez przewoźnika”, „W transporcie”, „W doręczeniu”, „Do odbioru”, „Dostarczona”).

### Czy za paczkę Hermes z Niemiec do Polski płaci się cło?

Nie — przesyłki z Niemiec do Polski poruszają się wewnątrz obszaru celnego Unii Europejskiej bez cła i opłat za odprawę. Ryczałtowe cło 3 EUR (do 150 EUR) oraz opłata manipulacyjna 2 EUR dotyczą wyłącznie przesyłek nadawanych spoza UE.

### Jak śledzić meble i duże AGD wysłane przez Hermes w Niemczech?

Dostawy gabarytów z dwuosobowym wniesieniem w Niemczech obsługuje osobna spółka *Hermes Einrichtungs-Service*, która potwierdza termin e-mailem i SMS-em oraz dzwoni 30–60 minut przed przyjazdem. W Peek możesz wybrać ją ręcznie przyciskiem „Zmień przewoźnika”.

:::sources
- [Otto Group: Konzernfirmen (Hermes)](https://www.ottogroup.com/de/ueber-uns/konzernfirmen/hermes-germany.php) – przynależność Hermes do grupy Otto
- [OTTO: Lieferung mit Hermes](https://www.otto.de/service/lieferung/) – czas dostawy w Niemczech, *WunschZustellung*, 17 000 punktów *PaketShop*, 7 dni roboczych na odbiór i dowód tożsamości
- [OTTO: Häufig gestellte Fragen (Lieferung & Versand)](https://www.otto.de/service/faq/) – przesyłki paczkowe oraz dostawy dwuosobowe
- [OTTO: Geographische Sendungsverfolgung](https://www.otto.de/shoppages/geographische_sendungsverfolgung) – śledzenie ciężarówki na mapie w dniu dostawy gabarytów
- [Evri: About us](https://www.evri.com/about-us) – zmiana nazwy Hermes UK na Evri w marcu 2022 r.
- [Bundesnetzagentur: Schlichtungsstelle Post](https://www.bundesnetzagentur.de/DE/Vportal/Schlichtung/Schlichtung_Post/start.html) – bezpłatne postępowanie polubowne w Niemczech i jego warunki
- [Niemiecki kodeks cywilny (BGB): § 475](https://www.gesetze-im-internet.de/bgb/__475.html), [§ 355](https://www.gesetze-im-internet.de/bgb/__355.html) oraz [§ 356](https://www.gesetze-im-internet.de/bgb/__356.html) – przejście ryzyka w sprzedaży konsumenckiej i 14 dni na odstąpienie
- [Niemiecki kodeks handlowy (HGB): § 421](https://www.gesetze-im-internet.de/hgb/__421.html) oraz [§ 438](https://www.gesetze-im-internet.de/hgb/__438.html) – uprawnienia odbiorcy i terminy zgłoszenia szkody (7 dni) oraz opóźnienia (21 dni)
- [Your Europe: Dostawa i wysyłka towarów](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_pl.htm) – 30 dni na dostawę i odpowiedzialność sprzedawcy
- [Podatki.gov.pl: Zwolnienia celne](https://podatki.gov.pl/informacje-o-cle/pozostale-informacje/zwolnienia-celne), [Komisja Europejska: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) oraz [Wytyczne ws. cła 3 EUR](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – zasady celne w UE i zmiany w 2026 r.
- [Poczta Polska: Zmiany w opłatach od 1 lipca 2026 r.](https://www.poczta-polska.pl/news/zmiany-w-oplatach-dla-przesylek-spoza-unii-europejskiej-od-1-lipca-2026-r/) oraz [Ostrzeżenie przed fałszywymi wiadomościami](https://www.poczta-polska.pl/news/uwaga-na-falszywe-wiadomosci-pocztex-grozna-kampania-phishingowa/) – pobór opłat spoza UE i ostrzeżenia przed phishingiem
- [Universal Parcel Scraper: hermes-de](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/hermes-de) oraz [statuses.json](https://github.com/plhery/universal-parcel-scraper/blob/main/carriers/hermes-de/statuses.json) – rozpoznawanie numerów, statusy i obsługa w Peek
:::
