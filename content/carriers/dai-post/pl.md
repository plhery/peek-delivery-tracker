---
title: Śledzenie DAI Post: numery, statusy i pomoc w dostawie
description: Śledź paczkę DAI Post (Parcel Right) w Australii, Nowej Zelandii i Singapurze: formaty numerów, znaczenie statusów, dostawa bez podpisu, odbiór i kontakt.
slug: sledzenie-dai-post
published: 2026-11-23
updated: 2026-11-23
---

DAI Post to australijska firma kurierska dla sklepów internetowych założona w Melbourne w 2000 roku. Przewozi paczki ze Stanów Zjednoczonych, Wielkiej Brytanii, Europy i Azji do odbiorców w Australii, Nowej Zelandii i Singapurze. Jej własna dostawa ostatniej mili nazywa się Parcel Right. Paczkę sprawdzisz na [stronie śledzenia DAI Post](https://daipost.com/track) lub wklejając numer w pole powyżej. Numer znajdziesz w potwierdzeniu zamówienia ze sklepu; jeśli go nie masz, zapytaj sklep.

## Numer śledzenia DAI Post: jak wygląda

DAI Post śledzi numery drukowane przez sklepy na etykietach, dlatego przybierają różne kształty. Strona śledzenia przyjmuje do 10 numerów naraz. Wzory znane kodowi Peek, z wymyślonymi cyframi:

| Numer (wymyślony) | Co to jest |
| --- | --- |
| `NZC482910375` | `NZC` i dziewięć cyfr: w kodzie Peek numer DAI Post dla paczek do Nowej Zelandii. Peek rozpoznaje DAI Post od razu. |
| `00393580944829103754` | 20 cyfr zaczynających się od `0039358094`, z ostatnią cyfrą kontrolną: kod kreskowy SSCC z etykiety, czasem drukowany jako `(00) 3 9358094 482910375 4`. Inni przewoźnicy również używają 20 cyfr, więc po samym kształcie Peek nie ma pewności. |
| `KPR48291037561` | Własna referencja sklepu, litery i cyfry. Peek nie rozpoznaje jej po kształcie. |

Spacje, nawiasy i małe litery nie mają znaczenia. Jeśli w 20-cyfrowym numerze pomylisz choć jedną cyfrę, cyfra kontrolna nie będzie się zgadzać i Peek nie powiąże go z DAI Post: sprawdź numer. Więcej w [jak znaleźć numer śledzenia](guide:find-tracking-number) i [formaty numerów przesyłek](guide:tracking-number-formats).

## Wyjaśnienie statusów śledzenia DAI Post

Wyszukiwarka DAI Post pokazuje przyjazne zdanie dla każdego kroku. Część paczek wyświetla krótszą wersję tego samego etapu, np. `Checked in at origin hub`. Peek odczytuje kod zdarzenia, więc obie wersje znaczą to samo. Wpisy znane Peek, po kolei:

| DAI Post podaje | Co to oznacza | Peek pokazuje |
| --- | --- | --- |
| `We’ve received your delivery details. We’ll let you know as soon as your parcel is in our hands.` lub `Customer label information received` | Sklep przesłał dane przesyłki; DAI Post jeszcze nie ma paczki. | „Zarejestrowana” |
| `Your parcel’s checked in and being prepared for its journey.` lub `Checked in at origin hub` | Hub wyjściowy DAI Post przyjął paczkę. | „Przyjęta przez przewoźnika” |
| `We’ve processed your parcel at our origin facility. Next, on the move to you!` lub `Parcel processed at origin facility` | Posortowana i gotowa do wysłania. | „W transporcie” |
| `There’s a departure delay. Thanks for your patience, we’ll update you soon.` lub `There’s a transit delay. Thanks for your patience, we’ll update you soon.` | Opóźniony wylot albo opóźnienie w drodze. | „W transporcie” |
| `Your parcel is in the air and is heading your way.` lub `Parcel departed origin country` | W locie z kraju nadania. | „W transporcie” |
| `Parcel arrived in trans-shipment country.`, potem `Your parcel’s left the transfer country and is back on the move.` | Postój w kraju tranzytowym. | „W transporcie” |
| `Touchdown! Your parcel has landed.` lub `Parcel arrived in destination country` | Dotarła do twojego kraju. | „W transporcie” |
| `We’ve collected your parcel from the airline and it’s being processed.` lub `Collected from the airline for processing` | DAI Post odebrał ją od linii lotniczej. | „W transporcie” |
| `Your parcel is being checked by customs. We’ll let you know when it’s cleared.` lub `Parcel processed by clearance facility` | Kontrola celna w toku. | „W urzędzie celnym” |
| `Customs clearance delay` | Urząd celny nadal ją zatrzymuje. | „W urzędzie celnym” |
| `All clear! Your parcel’s passed customs and is ready for the next leg.` lub `Customs cleared` | Zwolniona z cła. | „W transporcie” |
| `We’ve got your parcel and it’s been sorted for delivery.` lub `Parcel received and sorted` | Posortowana do doręczenia. | „W transporcie” |
| `Your parcel’s on an interstate journey. We’ll keep you updated as it travels.` | W transporcie między stanami. | „W transporcie” |
| `Your parcel’s arrived at our local facility, it's on the way.` | W oddziale blisko ciebie. | „W transporcie” |
| `Your parcel’s on its way to you, almost there!` | W drodze do oddziału doręczającego, jeszcze nie w trasie kuriera. | „W transporcie” |
| `It’s happening, your parcel will be delivered today. Get ready!` lub `Parcel out for final mile carrier delivery` | W doręczeniu dzisiaj. | „W doręczeniu” |
| `Parcel unsafe to leave at address` | Kurier nie zostawił paczki pod adresem (zobacz niżej). | „Próba doręczenia” |
| `Parcel available for collection` | Czeka w punkcie odbioru. | „Do odbioru” |
| `Delivered! Your parcel has arrived. Enjoy!` | Doręczona. | „Dostarczona” |
| `Delivered, left where you can find it.` lub `Parcel delivered - authority to leave` | Zostawiona pod adresem bez podpisu. | „Dostarczona” |

Dla nieznanego numeru strona DAI Post odpowiada `Sorry, we couldn't find your parcel.` Więcej w [co oznaczają statusy przesyłek](guide:tracking-statuses).

**Godziny.** DAI Post podaje każdą godzinę według czasu lokalnego miejsca skanu, bez strefy czasowej. Peek ustala strefę na podstawie miejscowości i przelicza godziny na strefę twojego urządzenia; godzina pierwszego wpisu bywa przesunięta.

## Doręczenie DAI Post: dostawa bez podpisu, odbiór i zwroty

DAI Post podaje, że jego sieć obejmuje 100 % populacji Australii. Pora doręczenia zależy od trasy kierowcy, zmieniającej się każdego dnia wraz z liczbą paczek i ruchem drogowym.

- **Dostawa bez podpisu (*Authority to leave*).** Podpis nie jest potrzebny. Jeśli nikt nie otwiera, a kierowca uzna miejsce za bezpieczne, zostawia paczkę.
- **Niebezpieczne do pozostawienia.** Jeśli paczka byłaby zbyt odsłonięta, trafia do najbliższego punktu odbioru w okolicy, a DAI Post wysyła instrukcję odbioru. Na stronie DAI Post wpis `Parcel available for collection` linkuje do mapy punktu; Peek pokazuje tylko miejscowość.
- **7 dni na odbiór.** Paczka nieodebrana w ciągu 7 dni wraca do oddziału DAI Post, a następnie do sklepu. To samo dotyczy przesyłek z niepełnymi danymi adresowymi.
- **Powiadomienia.** Powiadomienia o kluczowych etapach dostajesz tylko wtedy, gdy sklep przekazał DAI Post twój adres e-mail i numer telefonu komórkowego.

Warunki DAI Post dodają, że w razie nieobecności paczka może zostać zostawiona w bezpiecznym miejscu lub przekazana do *Collection Point*, co liczy się jako doręczenie. Strony firmy nie podają, kto doręcza w Nowej Zelandii lub Singapurze. Zobacz [nieudane doręczenie](guide:missed-delivery) i [nieodebrane przesyłki](guide:uncollected-parcel).

## Cło dla paczki DAI Post

**Zatrzymana na cle (*Customs held*).** Strona pomocy DAI Post wyjaśnia, że zdarzenie *Customs held* oznacza, iż ABF lub DAFF — australijskie służby graniczne i bezpieczeństwa biologicznego — potrzebują prawdopodobnie dodatkowych informacji przed zwolnieniem paczki. Zapytaj obsługę klienta DAI Post, czego brakuje: formularz zapytań ma opcję „My parcel is Customs held.”

**Opłaty.** Regulamin DAI Post nakłada na nadawcę odpowiedzialność za koszty wysyłki, w tym podatki i cła. Jeśli odmówisz uiszczenia opłaty przy odbiorze, nadal obciąża ona nadawcę, a DAI Post może wstrzymać przesyłkę do zapłaty lub zwrócić ją nadawcy. Ewentualne należności zależą od przepisów twojego kraju: zobacz [przesyłki zatrzymane na cle](guide:customs). Wiadomość o opłacie wygląda podejrzanie? Zobacz [fałszywe SMS-y o paczkach](guide:delivery-scam-texts).

## Śledzenie DAI Post stoi, paczka zaginiona lub zwrócona

**Brak nowego wpisu od kilku dni.** DAI Post skanuje paczki tylko w kluczowych punktach i zaznacza, że pomiędzy skanami przesyłka nadal się przemieszcza. W transporcie międzystanowym lub regionalnym może wyglądać na nieruchomą przez kilka dni. Gdy paczka przez cztery dni nie ma nowej linii przy „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie”, Peek pokazuje „Brak aktualizacji od …” z dniem tej linii i link „Poproś przewoźnika DAI Post o pomoc”. Zobacz [dlaczego śledzenie się nie aktualizuje](guide:tracking-not-updating).

**Wciąż „Zarejestrowana”.** DAI Post jeszcze nie ma paczki. Jeśli trwa to kilka dni, zapytaj sklep, czy faktycznie ją wysłał.

**Doręczona, ale jej nie ma.** Przy dostawie bez podpisu rozejrzyj się najpierw wokół domu. DAI Post może wysłać e-mailem dowód doręczenia *Authority to leave*: poproś przez formularz, a potem skontaktuj się ze sklepem. Zobacz [doręczona, ale nieotrzymana](guide:delivered-not-received).

**Zaginiona lub zwrócona.** Gdy przesyłka nigdy nie dotarła, skorzystaj z formularza DAI Post z powodem „I did not receive my parcel.” W obu przypadkach dostawę zlecił sklep: poproś go o zwrot pieniędzy lub nową paczkę. Zobacz [zaginione paczki](guide:lost-parcel) i [zwrócona do nadawcy](guide:returned-to-sender).

## Obsługa klienta i kontakt z DAI Post

Kanały kontaktu DAI Post:

- **Telefon:** Australia +61 3 8586 1200, Nowa Zelandia +64 800 451 718, Singapur +65 800 321 1587. Godziny pracy nie są podane.
- **E-mail:** consumercare@daipost.com.
- **Online:** [formularz zapytania](https://daipost.com/contact). Prosi o imię i nazwisko, telefon, e-mail, numer śledzenia i powód kontaktu.

## Śledzenie paczki DAI Post w Peek

[Peek](/) śledzi paczkę DAI Post po samym numerze: bez konta i bez kodu pocztowego.

- **Numer `NZC`.** Peek natychmiast rozpoznaje DAI Post („Wykryty przewoźnik”). Jeśli dwa dni po pierwszej odpowiedzi DAI Post nadal nie zna numeru, Peek poprosi cię o jego sprawdzenie, wybór innego przewoźnika albo usunięcie paczki.
- **20-cyfrowy numer zaczynający się od `0039358094`.** Wklej go mimo to: Peek obsługuje ponad 4 500 przewoźników. Podczas sprawdzania zobaczysz „Sprawdzanie usług śledzenia…”, a DAI Post należy do pierwszych pytanych. Jeśli ma paczkę, Peek ją wskaże; jeśli jeszcze nie, paczka pokaże „Oczekiwanie na pierwszy skan przewoźnika DAI Post”, a karta „Prawdopodobnie DAI Post”; po tygodniu Peek zasugeruje sprawdzenie numeru u nadawcy. Aby wybrać DAI Post ręcznie, kliknij „Zmień”: widnieje w „Pasują do numeru”.
- **Każdy inny numer.** Wklej go mimo to, kliknij „Zmień” i wybierz DAI Post (wyszukaj DAI Post lub Parcel Right). Na już dodanej paczce użyj „Zmień przewoźnika”.
- **Link.** Wklej link śledzenia z daipost.com, parcelright.com.au albo parcelrig.ht: Peek wyodrębni numer i od razu wskaże DAI Post.

Peek wyświetla status, każdy wpis w brzmieniu DAI Post, miejscowość i kraj każdego skanu, przewidywaną datę dostawy (gdy DAI Post ją podaje) oraz godzinę doręczenia. Nie pokazuje punktu odbioru, nazwy usługi, wagi ani potwierdzenia doręczenia. „Otwórz stronę przewoźnika DAI Post” otwiera numer w oficjalnym śledzeniu. W kodzie Peek część paczek [Asendia](carrier:asendia) oraz część paczek [ePost Global](carrier:epost-global) do Nowej Zelandii trafia do DAI Post, więc ich śledzenie zawiera jego skany.

Gdy paczka jest otwarta w Peek albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza ją nawet co 10 minut, a co 2 minuty od 6:00 czasu lokalnego w dniu, gdy DAI Post zeskanuje ją w doręczeniu. Po dwóch dniach bez zmian (12 godzinach przy „Zarejestrowana” lub „Przyjęta przez przewoźnika”) sprawdza co godzinę, a po siedmiu dniach bez zmian przy „Zarejestrowana” co 6 godzin. Powiadomienia mogą obejmować „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek nie może skontaktować się za ciebie z DAI Post ani ze sklepem.

## Pytania o śledzenie DAI Post

### Czy Parcel Right to to samo co DAI Post?

Parcel Right to własna dostawa ostatniej mili realizowana przez DAI Post. W Peek możesz wyszukać obie nazwy.

### Co oznacza „Parcel unsafe to leave at address”?

Kierowca nikogo nie zastał, a miejsce nie było bezpieczne. Paczka trafia do najbliższego punktu odbioru: odbierz ją w ciągu 7 dni, inaczej wróci do sklepu.

### Ile czasu trwa dostawa DAI Post?

Regulamin DAI Post podaje, że standardowe czasy dostawy są szacunkami, nie gwarancją. Strona śledzenia może wyświetlać przewidywaną datę dostawy, którą pokazuje też Peek.

:::sources
- [DAI Post: Home](https://daipost.com/) – Melbourne, 2000, paczki z USA, Wielkiej Brytanii, Europy i Azji do Australii, Nowej Zelandii i Singapuru, Parcel Right
- [DAI Post: Help and support](https://daipost.com/help-and-support) – telefony, zasięg, czasy dostaw, brak aktualizacji, powiadomienia, dostawa bez podpisu, odbiór, zwroty, cło, dowód doręczenia
- [DAI Post: Delivery tracking](https://daipost.com/track) – śledzenie, do 10 numerów
- [DAI Post: Contact](https://daipost.com/contact) – formularz zapytania i powody, telefony, consumercare@daipost.com
- [DAI Post: Terms & conditions](https://daipost.com/terms) – bezpieczne miejsce lub Collection Point, szacowane czasy dostaw, opłaty
- [Universal Parcel Scraper: DAI Post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dai-post) – nazwy, formaty numerów, linki, komunikaty i etapy, godziny, przewidywana dostawa
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – wykrywanie, skany DAI Post w Asendia i ePost Global
:::
