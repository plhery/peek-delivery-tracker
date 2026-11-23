---
title: Śledzenie MHI: statusy Trackmail i kto doręcza
description: Masz numer MHI ze sklepu online? Gdzie go sprawdzić, co oznaczają statusy Trackmail, który kurier doręcza w twoim kraju i do kogo pisać przy opóźnieniu.
slug: sledzenie-mhi
published: 2026-11-23
updated: 2026-11-23
---

MHI (Mail Handling International) to firma z Bristolu wysyłająca zamówienia brytyjskich sklepów internetowych do kupujących za granicą. Sklepy dostarczają paczki do jej centrum w Bristolu; MHI je eksportuje i przekazuje każdą kurierowi lub poczcie w twoim kraju, która doręcza ją pod własnym numerem. Numer MHI sprawdzisz w serwisie śledzenia MHI [Trackmail](https://www.trackmail.co.uk/) lub w polu powyżej. Numer pochodzi ze sklepu: MHI nadaje klientom kod śledzenia dla każdej paczki, więc szukaj go w e-mailu o wysyłce albo w szczegółach zamówienia.

## Numer śledzenia MHI: jak wygląda

MHI nie publikuje formatu dla kupujących. Oto postacie, jakie przyjmują jej numery (z wymyślonymi cyframi):

| Numer | Co to jest |
| --- | --- |
| `MHI482W736405829173` | MHI, trzy cyfry, potem W i 12 cyfr: MHI. |
| `MHI482WBB736405829` | MHI, trzy cyfry, potem WBB i 9 cyfr: MHI. |
| `MHI-482-P736405829` | MHI, trzy cyfry, potem P i 9 cyfr: MHI. Na etykietach ten format ma myślniki. |
| `MHI482736405829173` | MHI, trzy cyfry i kolejne 12 cyfr: MHI. |
| `RR482917367US` | Dwie litery, 9 cyfr, dwie litery: numer pocztowy tej samej przesyłki, który Trackmail podaje dla części przesyłek nierejestrowanych. |
| Inny format | Własny numer doręczającego kuriera, widoczny na Trackmail po „Tracking number:” przy przesyłce śledzonej. |

Trzy cyfry po MHI oznaczają konto sklepu w MHI. Numer typu P wpisuj na Trackmail z myślnikami dokładnie tak jak na etykiecie — bez nich Trackmail go nie znajdzie. Jedna paczka może więc mieć dwa numery: MHI oraz kuriera. Więcej w artykule [formaty numerów śledzenia](guide:tracking-number-formats).

## Statusy śledzenia MHI wyjaśnione

Trackmail działa po angielsku. Nad historią widnieje usługa wybrana przez sklep: „The seller requested this item be shipped via the following service:”, a po niej `TRACKED` lub `UNTRACKED`. Niżej znajdują się zdarzenia od najnowszego, każde z datą, godziną i treścią, najczęściej też z kategorią i miejscem: skan eksportowy MHI, a potem skany kuriera w brzmieniu MHI. W kolejności, w jakiej trafia na nie paczka:

| Trackmail podaje | Co to oznacza dla ciebie |
| --- | --- |
| `Your order has been processed at the UK export facility. It is now on route to the receiver.` | MHI odprawiło paczkę w Wielkiej Brytanii i wysłało ją za granicę. W usłudze nierejestrowanej często jest to jedyny wpis. |
| `Shipment information sent to last mile service provider` | Kurier w twoim kraju otrzymał dane przesyłki, ale jeszcze nie samą paczkę. |
| `Notification sent to recipient` | Kurier powiadomił cię, że przesyłka jest w drodze. |
| `Handed Over to Delivery Partner` | Kurier przejął paczkę. |
| `Arrived at sorting center` | Skan w sortowni w sieci kuriera. |
| `Dispatched from sorting center` | Paczka opuściła tę sortownię. |
| `Assigned to Transport` | Jedzie pojazdem między oddziałami. |
| `Shipment at final delivery center` | Dotarła do oddziału odpowiedzialnego za doręczenie. |
| `Delivery scheduled in the final delivery depot` | Zaplanowana na dzisiejszą trasę kuriera. |
| `Delivery attempted` | Próba doręczenia nie powiodła się — nikt nie odebrał paczki. |
| `Arrived at Pick-up Point` | Czeka na ciebie w punkcie odbioru. |
| `Successfully delivered` | Doręczona lub odebrana. |

Czytaj treść, nie kategorię obok. Trackmail przypisuje `Shipment at final delivery center` oraz `Arrived at Pick-up Point` do kategorii „Out for delivery”, choć pierwszy poprzedza wyjazd kuriera, a drugi oznacza, że musisz odebrać paczkę samodzielnie. Z kolei `Delivery attempted` trafia pod „Delivery” jak udane doręczenie. Wyjazd kuriera i nieudana próba mogą się powtarzać przez kilka dni.

Godziny na Trackmail nie mają strefy czasowej: własny skan MHI podawany jest w czasie brytyjskim, a skany kuriera w jego czasie lokalnym.

Dla nieznanego numeru Trackmail wyświetla jeden wiersz zaczynający się od `No details found for Tracking Code, or Tracking Code not recognised.` i dodaje, że przy niedawnym zamówieniu MHI może jeszcze nie mieć danych.

## Doręczenie MHI: kto przywozi paczkę

MHI nie doręcza przesyłek za granicą samodzielnie. Podaje, że współpracuje z ponad 100 przewoźnikami, docierając do 252 krajów i terytoriów. W centrum w Bristolu kod kreskowy każdej paczki jest skanowany, a system MHI dobiera przewoźnika ostatniej mili według kraju docelowego. Sklepy mogą wybrać usługę śledzoną, nierejestrowaną lub ekspresową, więc to, kto doręczy paczkę, zależy od twojego kraju i wybranej przez sklep opcji.

**Przesyłki śledzone.** Trackmail może pokazać numer doręczającego kuriera po „Tracking number:” wraz z linkiem „View carrier tracking” do jego strony. W razie pytań Trackmail odsyła do tego linku lub do sprzedawcy.

**Przesyłki nierejestrowane.** Trackmail zaznacza, że śledzenie jest ograniczone i często pokazuje wyłącznie skan eksportowy MHI. Dla części przesyłek podaje „local poster number”: według Trackmail po przekazaniu go lokalnej poczcie lub placówce pocztowej mogą oni podać więcej informacji. Warto też sprawdzić go u swojej poczty: [USPS](carrier:usps), [Canada Post](carrier:canada-post), [Australia Post](carrier:australia-post) albo An Post.

Awiza, próby doręczenia, ponowne dostawy i punkty odbioru podlegają zasadom doręczającego kuriera: sprawdź jego śledzenie przez „View carrier tracking” i zobacz [nieudane doręczenia](guide:missed-delivery).

## Paczka MHI bez aktualizacji, zaginiona lub zwrócona

**`No details found…`.** Sprawdź numer i wpisz numer P z myślnikami. Jeśli zamówienie jest świeże, MHI może nie mieć jeszcze danych — spróbuj później.

**Tylko skan eksportowy.** W usłudze nierejestrowanej na tym Trackmail może się zatrzymać: użyj „local poster number” na swojej poczcie, jeśli jest podany, albo zapytaj sklep. Przy przesyłce śledzonej kurier mógł jej jeszcze nie zeskanować; gdy pojawi się jego numer, jego własny system może być bardziej aktualny. Zobacz [dlaczego śledzenie się zatrzymuje](guide:tracking-not-updating).

**Doręczona, ale jej nie ma.** Paczkę doręczył lokalny kurier, więc najpierw sprawdź jego śledzenie przez „View carrier tracking”, a potem skontaktuj się ze sklepem. Zobacz [doręczona, ale nieotrzymana](guide:delivered-not-received).

**Zaginiona lub uszkodzona.** Skontaktuj się ze sklepem. MHI działa na zlecenie sklepu, nie publikuje procedury reklamacji dla odbiorców, a sam Trackmail odsyła do sprzedawcy. Zobacz [zagubione paczki](guide:lost-parcel).

**Cło.** MHI oferuje sklepom różne warunki celne, w tym DDP (Delivered Duty Paid), więc to, czy zostaje coś do zapłaty, zależy od wyboru sklepu. Możliwe opłaty omawia poradnik [paczki zatrzymane na cle](guide:customs).

**Zwrócona.** Czas przechowywania nieodebranej paczki przed zwrotem ustala doręczający kurier — sprawdź jego śledzenie. Gdy przesyłka wróci, poproś sklep o zwrot pieniędzy lub ponowną wysyłkę. Zobacz [zwrócona do nadawcy](guide:returned-to-sender).

## Kontakt i obsługa klienta MHI

Dane podawane przez MHI:

- **Pytania o śledzenie:** e-mail tracking@mhi.co, podany na stronie eCommerce delivery oraz na stronie kontaktowej Nexae (platformy wysyłkowej MHI).
- **Telefon:** +44 (0) 117 977 6655 — brytyjski numer obok adresu w Bristolu, 82 Feeder Road, BS2 0TQ. MHI nie podaje godzin pracy ani nie deklaruje obsługi pytań o paczki pod tym numerem.
- **Formularz Enquiries:** wyłącznie do spraw ogólnych. MHI uprzedza, że pytania o śledzenie wysłane tym formularzem nie będą podejmowane ani rozpatrywane.

Rada MHI dla kupujących: sprawdzaj Trackmail albo skontaktuj się ze sprzedawcą bądź platformą zakupową. Klientem MHI jest sklep i to on może interweniować; w sprawie nieudanej dostawy lub odbioru zwróć się do kuriera wskazanego na Trackmail.

## Śledzenie paczki MHI w Peek

[Peek](/) rozpoznaje numer MHI po kształcie we wszystkich czterech seriach powyżej, z myślnikami lub bez. Starsze etykiety z zaledwie dwiema cyframi po MHI albo ośmioma na końcu nie są wyszukiwane — sprawdź je na Trackmail. Możesz też wkleić link Trackmail zawierający numer; Peek rozpozna go jako MHI, jeśli numer ma format MHI.

Peek odczytuje Trackmail i pokazuje status oraz historię wraz z miejscem każdego skanu na mapie. Trackmail nie podaje przewidywanej daty doręczenia, wagi, potwierdzenia doręczenia ani podpisu odbiorcy, więc Peek nie pobiera żadnej z tych informacji z MHI, a także pomija twój adres i kod pocztowy zawarte w linku kuriera.

Gdy link z Trackmail prowadzi do przewoźnika znanego Peek, strona paczki wyświetla „Doręcza:” wraz z nazwą tego kuriera i jego numerem. Dopóki kurier ma tylko zapowiedź paczki bez własnego skanu, zamiast tego widać „Zgłoszona przewoźnikowi …, jeszcze nieprzyjęta”. Jeśli Peek odczytuje śledzenie tego kuriera bezpośrednio, odpytuje go tym numerem i przełącza się na niego, gdy tylko pojawią się nowsze zdarzenia. Przewoźnik nieznany Peek nie jest wymieniany — skorzystaj wtedy z „View carrier tracking” na Trackmail. Przy przesyłce nierejestrowanej z „local poster number” Peek może sprawdzić ten numer na poczcie krajowej, zazwyczaj w kraju, z którego dodajesz paczkę, i przełączyć się w ten sam sposób. Nie dotyczy to jednak każdej poczty: numer `RR…` kończący się na `AU` dodany z Australii nie trafia do Australia Post, więc sprawdź go bezpośrednio na stronie Australia Post.

Dopóki masz paczkę otwartą w Peek albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza ją nawet co 10 minut, a od 6:00 czasu lokalnego w dniu wyjścia przesyłki do doręczenia nawet co 2 minuty, dopóki Trackmail (lub własny system przewoźnika przejmującego doręczenie) odpowiada. Po dwóch dniach bez wieści (po 12 godzinach, gdy przesyłka nadal ma status „Zarejestrowana” lub „Przyjęta przez przewoźnika”) Peek sprawdza co godzinę, a jeśli przez siedem dni pozostaje na etapie „Zarejestrowana” — co 6 godzin.

Gdy przesyłka nadal na etapie „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie” nie ma nowego skanu od czterech dni, Peek oznacza ją komunikatem „Brak aktualizacji od czterech dni” (lub „Brak aktualizacji od …”), wyjaśnia: „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika MHI.” i wyświetla „Poproś przewoźnika MHI o pomoc”. Możesz ustawić powiadomienia „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek nie kontaktuje się w twoim imieniu z MHI, kurierem ani sklepem.

## Pytania o śledzenie MHI

### Gdzie jest moja paczka MHI?

Najnowszy wiersz na Trackmail wskazuje etap, a skany kuriera podają miasto. Przed `Handed Over to Delivery Partner` paczkę ma MHI albo leci ona do twojego kraju; później przejmuje ją kurier lokalny, a przy przesyłce śledzonej jego numer widnieje na Trackmail po „Tracking number:”.

### Co oznacza „processed at the UK export facility”?

MHI przetworzyło paczkę w swoim brytyjskim centrum eksportowym i wysłało ją w stronę odbiorcy. To własny skan MHI i przy usłudze nierejestrowanej bywa jedynym.

### Czy trackmail.co.uk jest wiarygodne?

Tak. Trackmail to serwis śledzenia MHI: link „Track my Parcel” na stronie MHI prowadzi prosto tam. Companies House wymienia Mail Handling International Limited jako aktywną spółkę zarejestrowaną w 1996 roku pod adresem w Bristolu podawanym przez MHI.

### Czy MHI ma numer obsługi klienta?

Podaje numer +44 (0) 117 977 6655, ale w sprawach śledzenia prosi o e-mail na tracking@mhi.co lub kontakt ze sklepem. Za samo doręczenie odpowiada kurier podany na Trackmail.

:::sources
- [MHI: Home](https://www.mhi.co/) – link Track my Parcel do Trackmail, pytania o paczki do sprzedawcy, formularz ogólny nie do śledzenia, ponad 100 przewoźników, 252 kraje i terytoria, śledzone, nierejestrowane i ekspresowe, DDP, adres, telefon
- [MHI: eCommerce Delivery](https://www.mhi.co/ecommerce-delivery) – centrum w Bristolu, dobór kuriera według kraju, kody śledzenia dla sklepów, eksport, tracking@mhi.co
- [Nexae: Contact Us](https://nexae.co.uk/ContactUsHomepage) – tracking@mhi.co, Nexae należy do MHI
- [MHI: Trackmail](https://www.trackmail.co.uk/) – serwis śledzenia MHI, język angielski, kontakt do śledzenia
- [Companies House: Mail Handling International Limited](https://find-and-update.company-information.service.gov.uk/company/03241562) – aktywna spółka, zarejestrowana 23 sierpnia 1996, siedziba 82 Feeder Road, Bristol
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formaty numerów, komunikaty i kategorie Trackmail, wiersz usługi, link kuriera, lokalny numer pocztowy, komunikat o nieznanym numerze, strefy czasowe, co pokazuje Peek i którego kuriera lub pocztę odpytuje
:::
