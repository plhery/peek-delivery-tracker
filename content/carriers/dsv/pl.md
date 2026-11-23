---
title: Śledzenie DSV: numery przesyłek, statusy i DB Schenker
description: Śledź przesyłkę DSV lub dawną DB Schenker (drogową, lotniczą, morską): jaki numer wpisać, znaczenie statusów, dostawa, reklamacje i kontakt.
slug: sledzenie-dsv
published: 2026-11-23
updated: 2026-11-23
---

DSV to duńska grupa transportowo-logistyczna realizująca przewozy drogowe, lotnicze i morskie, która w 2025 roku przejęła Schenker (DB Schenker) od Deutsche Bahn. Na dsv.com pole „Track your shipment” ma dwie opcje: „Track DSV shipment” otwiera system DSV (myDSV), a „Track Schenker shipment” dawny system DB Schenker. Konto nie jest wymagane. Numer pochodzi od zlecającego transport – zwykle nadawcy lub sklepu – wklej go w pole powyżej.

## Numer śledzenia DSV: jak wygląda i gdzie go znaleźć

Numer posiada firma, która zleciła transport w DSV: sprawdź potwierdzenie wysyłki ze sklepu, stronę zamówienia lub zapytaj nadawcę. Więcej w [jak znaleźć numer śledzenia](guide:find-tracking-number).

W trakcie łączenia systemów DSV utrzymuje oba narzędzia: przesyłka zlecona w Schenker może być widoczna wyłącznie w śledzeniu Schenker. Jeśli jedno nic nie zwraca, sprawdź drugie.

| Numer (wymyślony) | Co oznacza |
| --- | --- |
| `DEKLM482917365` | 14 znaków: europejski kod kraju jak DE, SE, DK czy PL, trzy litery i dziewięć cyfr. Dawny numer drogowy DB Schenker i format przypisany DSV w katalogu Peek. |
| `KLM4829173` | Trzy litery i siedem lub osiem cyfr: inny dawny numer drogowy DB Schenker. Peek nie rozpoznaje DSV po tym formacie. |
| `094827361549283711` | 18 cyfr (20 z `00` z przodu): kod kreskowy pojedynczej paczki z dawnej przesyłki drogowej DB Schenker. |
| Litery i cyfry | Numer przesyłki, rezerwacji lub *house bill* w myDSV. Peek pyta myDSV dla dowolnego ciągu 5–40 liter i cyfr zawierającego co najmniej jedną cyfrę. |
| `mydsv.com/track-shipment?query=DEKLM482917365` | Link do śledzenia myDSV, otwierany przez „Track DSV shipment” na dsv.com. |

Numer referencyjny klienta lub zamówienia albo główny list lotniczy (*master air waybill*) pozwalają wyszukać przesyłkę w myDSV, ale Peek ich nie obsługuje – użyj numeru przesyłki, rezerwacji lub *house bill* DSV. Peek nie wybiera też jednej przesyłki, jeśli numer pasuje do kilku.

## Znaczenie statusów śledzenia DSV

myDSV rejestruje każdy skan od rezerwacji po potwierdzenie doręczenia. Widok publiczny pokazuje ogólny przebieg, a prywatny (na wniosek) szczegóły. Etapy myDSV odczytywane przez Peek:

| DSV pokazuje | Co to oznacza dla ciebie | Status w Peek |
| --- | --- | --- |
| `Booking` | DSV ma rezerwację, z datą bez godziny. | „Zarejestrowana” |
| `Creation` | Przesyłka utworzona w systemie drogowym DSV. | „Zarejestrowana” |
| `Actual pickup` | DSV odebrało towar od nadawcy. | „Przyjęta przez przewoźnika” |
| `Cargo received` | Towar dotarł do DSV – na terminal lub z odbioru. | „Przyjęta przez przewoźnika” |
| `Actual Departure`, `Actual departure - Origin Port` | Wyjście z portu lub lotniska na główny odcinek. | „W transporcie” |
| `Actual arrival`, `Actual arrival- Destination Port` | Przybycie do portu lub na lotnisko docelowe odcinka głównego. | „W transporcie” |
| `Handover responsibilty of cargo`, `Handover of Cargo` | DSV przekazało ładunek na końcu swojej usługi, co kończy przesyłkę (pierwsza pisownia pochodzi z DSV). | „Dostarczona” |
| `Actual delivery` | Doręczono. | „Dostarczona” |

`Estimated Departure`, `Estimated arrival`, `Estimated pickup`, `Estimated delivery`, `Requested pickup` i `Requested delivery` to plany, nie skany: Peek pomija je w historii, a `Estimated delivery` pokazuje jako przewidywany dzień (lub zakres dni) doręczenia, dopóki przesyłka nie zostanie doręczona.

Dawny system DB Schenker obsługuje przesyłki drogowe własnymi komunikatami:

| System DB Schenker pokazuje | Co to oznacza dla ciebie | Status w Peek |
| --- | --- | --- |
| `Booked` | Nadawca zarezerwował transport. | „Zarejestrowana” |
| `Out for collection` | Kierowca jedzie odebrać przesyłkę od nadawcy. | „Zarejestrowana” |
| `Collected` | Odebrano od nadawcy. | „Przyjęta przez przewoźnika” |
| `Delivered to terminal by shipper` | Nadawca dostarczył towar do terminalu. | „Przyjęta przez przewoźnika” |
| `Arrived`, `Departed` | Przyjazd do terminalu lub wyjazd z niego. | „W transporcie” |
| `Customs clearance initiated` | Rozpoczęto odprawę celną. | „W urzędzie celnym” |
| `Customs clearance finalised` | Odprawiono, ponownie w drodze. | „W transporcie” |
| `Out for Delivery` | W samochodzie doręczającym. | „W doręczeniu” |
| `Not delivered` | Doręczenie nieudane; może pojawić się powód. | „Próba doręczenia” |
| `To consignee's disposal`, `Ready for pick up` | Czeka na twój odbiór. | „Do odbioru” |
| `Delivered` | Doręczono. | „Dostarczona” |
| `Picked up by consignee`, `Picked up at agent` | Odebrane przez odbiorcę lub u agenta. | „Dostarczona” |
| `ePOD available` | Dostępne elektroniczne potwierdzenie doręczenia. | „Dostarczona” |

Po drodze pojawiają się też `Linehaul departed`, `Linehaul arrived`, `Terminal inventory`, `Vessel Loaded`, `Vessel Unloaded`, `Train Loaded`, `Train Unloaded`, `Vessel/Train Loaded` i `Vessel/Train Unloaded` – wszystkie jako „W transporcie” w Peek. `Not Collected` (nieudany odbiór) oraz `Not loaded` (niezaładowane na planowany transport, z powodem) pokazują „Zgłoszono problem”. Gdy system dopisuje powód obok etapu, Peek dołącza go po komunikacie. Więcej w [znaczenie statusów śledzenia](guide:tracking-statuses).

## Jak doręcza DSV

**Palety i ładunki częściowe.** DSV określa swoją drogową sieć drobnicową jako największą w Europie: ponad 36 krajów, ponad 400 terminali i przesyłki do 2500 kg – paleta lub duże zamówienie może więc przyjechać ciężarówką z terminalu DSV.

**Zamówienia internetowe.** Usługa Parcel E-commerce odbiera paczki od sklepów, konsoliduje je międzynarodowo i przekazuje doręczenie lokalnym sieciom ostatniej mili, więc paczkę może przynieść inny przewoźnik. Jeśli sklep podaje jego nazwę lub drugi numer, śledź go osobno – zapytanie DSV w Peek nie wskazuje kolejnego przewoźnika.

**Powiadomienia i potwierdzenie doręczenia.** W myDSV użytkownicy mogą włączyć powiadomienia (np. odbiór czy odprawa celna), sprawdzić przewidywany czas przybycia ładunków drogowych i pobrać potwierdzenie doręczenia po zakończeniu. Jeśli nie ty zlecałeś DSV, zapytaj nadawcę. Strony DSV nie podają liczby prób doręczenia ani czasu składowania na terminalu – zapytaj obsługujący oddział DSV.

**Cło.** W większości krajów z własnymi biurami DSV działa jako licencjonowana agencja celna, a gdzie indziej współpracuje z agentami. W sprawach odprawy DSV zaleca kontakt z DSV w danym kraju. W UE zakupy online spoza UE podlegają od 1 lipca 2026 r. cłu 3 € za rodzaj produktu w zamówieniach do 150 €, a od 1 listopada 2026 r. unijnej opłacie manipulacyjnej 2 € za rodzaj produktu niezależnie od wartości. Poza UE obowiązują lokalne przepisy: zobacz [paczki zatrzymane na cle](guide:customs).

## Przesyłka DSV opóźniona, zatrzymana lub uszkodzona

**Brak ruchu.** Jeśli jeden system stoi, sprawdź drugi na dsv.com – przesyłka ze Schenkera mogła jeszcze nie trafić do myDSV. Potem zapytaj nadawcę, który zlecił transport. Zobacz [dlaczego śledzenie się nie aktualizuje](guide:tracking-not-updating).

**Uszkodzony lub brakujący towar.** Przy zakupie online zacznij od sklepu. Portal eClaims przyjmuje reklamacje i dokumenty, pokazuje ich status i łączy z lokalnymi likwidatorami szkód; nie podaje tam konkretnego terminu, więc nie zwlekaj. Zobacz [uszkodzone paczki](guide:damaged-parcel) i [zaginione paczki](guide:lost-parcel).

**Podszywanie się pod DSV.** DSV ostrzega, że niepowiązane firmy lub osoby bywają używające jej nazwy do sprzedaży usług frachtowych. Jeśli nie masz pewności co do strony lub kontaktu, sprawdź w lokalnym biurze DSV; przy podejrzanych SMS-ach o paczce zobacz [fałszywe SMS-y o przesyłkach](guide:delivery-scam-texts).

## Kontakt i obsługa klienta DSV

Na dsv.com:

- **Formularz kontaktowy:** na [stronie pomocy i kontaktu DSV](https://www.dsv.com/en/support/help-and-contact).
- **Lokalne biura:** DSV działa w ponad 90 krajach; [znajdź lokalne biuro DSV](https://www.dsv.com/en/support/find-dsv) według kraju.
- **Reklamacje:** przez eClaims ze [strony Claims DSV](https://www.dsv.com/en/support/self-services/claims).
- **DSV A/S:** Hedehusene, Dania, +45 43 20 30 40 zgodnie z notą prawną – nie jest to infolinia do śledzenia przesyłek.

Strony pomocy DSV nie podają numeru telefonu do śledzenia przesyłek.

## Śledzenie przesyłki DSV w Peek

Wklej 14-znakowy numer jak `DEKLM482917365`, a Peek zapyta DSV podczas dodawania („Sprawdzanie usług śledzenia…”) i wyświetli „DSV ma tę przesyłkę.”, gdy DSV ją rozpozna. Jeśli DSV jeszcze jej nie ma, karta pokaże „Oczekiwanie na pierwszy skan przewoźnika DSV” z podpisem „Prawdopodobnie DSV”, a Peek będzie sprawdzał dalej; po tygodniu zasugeruje sprawdzenie numeru lub wybór innego przewoźnika.

Innych formatów Peek nie rozpoznaje automatycznie jako DSV. Wklej je mimo to: Peek śledzi ponad 4500 przewoźników. Aby ustawić DSV ręcznie, naciśnij „Zmień” i wyszukaj DSV – słowo „Schenker” też go znajduje („Znany też jako DB Schenker”). Dla dodanej przesyłki użyj „Zmień przewoźnika”. Link z mydsv.com (`?query=`) przypisuje DSV od razu, podobnie jak link ze śledzenia Schenker na dsv.com z parametrem `refNumber=`.

Peek pyta najpierw myDSV. Jeśli myDSV nie zna dawnego numeru drogowego lub kodu paczki DB Schenker, Peek sprawdza system Schenker – wyłącznie dla przesyłek drogowych; dla lotniczych i morskich widocznych tylko tam użyj „Track Schenker shipment” na dsv.com.

Peek pokazuje status, każdy etap w brzmieniu DSV z miastem i krajem oraz przewidywany dzień doręczenia. DSV podaje lokalny czas skanu bez strefy czasowej: Peek ustala strefę po miejscu (mieście, porcie, kodzie lotniska) lub sąsiednich skanach, a w ostateczności przyjmuje czas kopenhaski, więc sporadycznie godzina może być nieścisła. Imię i nazwisko odbiorcy, gdy DSV je podaje, widzisz tylko ty, pod „Odbiorca”, a miasto z adresu doręczenia może kończyć trasę na twojej mapie, dopóki przesyłka jest w drodze; udostępnione linki nie pokazują ani jednego, ani drugiego. Peek pomija nazwę usługi, miasto nadawcy, ulicę, kody pocztowe i pasek postępu DSV, a z systemu Schenker nadawcę, referencje, towary i nazwisko podpisującego.

Jeśli szwedzka firma Early Bird przekaże twoją paczkę do DSV lub DB Schenker, Peek wskaże DSV jako doręczyciela i zacznie je śledzić, gdy pojawią się nowsze skany.

Gdy przesyłka jest otwarta w Peek albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza ją nawet co 10 minut, a co 2 minuty od 6:00 czasu lokalnego w dniu, w którym DSV zeskanuje ją do doręczenia, i powiadamia o „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Po dwóch dniach bez zmian (po 12 godzinach przy „Zarejestrowana” lub „Przyjęta przez przewoźnika”) sprawdza co godzinę, a po siedmiu dniach bez nowości przy „Zarejestrowana” co 6 godzin. Jeśli przesyłka przez cztery dni nie ma nowego wpisu przy „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie”, Peek pokazuje „Brak aktualizacji od …” z dniem tego wpisu i link „Poproś przewoźnika DSV o pomoc”. Nie może skontaktować się z DSV ani nadawcą, zarezerwować dostawy ani złożyć reklamacji.

## Pytania o śledzenie DSV

### Czy DSV to ta sama firma co DB Schenker?

Tak. Założone w 1976 roku DSV sfinalizowało przejęcie Schenkera od Deutsche Bahn 30 kwietnia 2025 roku, a dbschenker.com połączono z dsv.com. Przesyłki Schenker zachowują na razie osobne narzędzie śledzenia.

### Jak teraz śledzić przesyłkę DB Schenker?

Wpisz numer w „Track Schenker shipment” w polu „Track your shipment” na dsv.com lub wklej go powyżej; o ile nie ma 14 znaków z kodem kraju z przodu, wybierz DSV ręcznie. Peek pyta myDSV, a dla przesyłek drogowych także system Schenker.

### Co oznacza „handover of cargo” w śledzeniu DSV?

DSV przekazało towar na końcu swojej usługi i przesyłka jest zakończona; Peek pokazuje „Dostarczona”. Jeśli nic do ciebie nie dotarło, zapytaj nadawcę, gdzie DSV przekazało ładunek, i przeczytaj [doręczono, ale nie odebrano](guide:delivered-not-received).

### Czy DSV ma telefon do obsługi klienta?

Strony pomocy nie podają numeru do śledzenia. Skorzystaj z formularza kontaktowego lub znajdź lokalne biuro DSV na dsv.com. Numer z noty prawnej DSV A/S (+45 43 20 30 40 w Danii) nie służy do śledzenia przesyłek.

:::sources
- [DSV: Book and track shipments with myDSV](https://www.dsv.com/en/digital-solutions/book-and-track) – śledzenie bez konta, dwa pola, połączenie dbschenker.com z dsv.com
- [DSV: myDSV](https://www.dsv.com/en/digital-solutions/mydsv) – widok publiczny i prywatny, rejestr skanów, powiadomienia, potwierdzenie doręczenia
- [DSV: Self-services](https://www.dsv.com/en/support/self-services) – systemy DSV i Schenker, eClaims, systemy Schenker do migracji
- [DSV: Frequently asked questions](https://www.dsv.com/en/support/faq) – formularz kontaktowy, dwie wersje myDSV w okresie przejściowym
- [DSV: Help and contact](https://www.dsv.com/en/support/help-and-contact) – ponad 90 krajów, wyszukiwarka biur
- [DSV: Find your local DSV office](https://www.dsv.com/en/support/find-dsv) – biura według kraju
- [DSV: Claims](https://www.dsv.com/en/support/self-services/claims) – jak działa eClaims
- [DSV: Groupage transport](https://www.dsv.com/en/our-solutions/modes-of-transport/road-transport/groupage) – 36+ krajów, 400+ terminali, do 2500 kg
- [DSV: E-commerce delivery solutions](https://www.dsv.com/en/our-solutions/contract-logistics/ecommerce-solutions/last-mile-delivery) – konsolidacja transgraniczna, lokalne sieci ostatniej mili
- [DSV: Customs clearance](https://www.dsv.com/en/our-solutions/modes-of-transport/value-added-services/customs-clearance) – licencjonowana agencja celna, agenci, kontakt z DSV w danym kraju
- [DSV: History](https://www.dsv.com/en/about-dsv/history) – założone w 1976 r., droga, powietrze i morze, zakup Schenkera w 2025 r.
- [DSV: DSV completes acquisition of Schenker](https://www.dsv.com/en/about-dsv/press/news/dsv-completes-acquisition-of-schenker-2628130) – finalizacja 30 kwietnia 2025 r., integracja
- [DSV: Legal notice](https://www.dsv.com/en/about-dsv/legal-notice) – DSV A/S w Danii, telefon, nadużycia nazwy DSV
- [Komisja Europejska: reforma celna UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – cło 3 € za rodzaj produktu do 150 €, opłata manipulacyjna 2 €
- [Komisja Europejska: wytyczne dotyczące cła 3 € i unijnej opłaty manipulacyjnej (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € za rodzaj produktu niezależnie od wartości
- [Universal Parcel Scraper: DSV](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dsv) – formaty i linki, oba systemy, komunikaty i etapy DSV, lokalne zegary, co czyta Peek
:::
