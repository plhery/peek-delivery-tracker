---
title: Śledzenie Redur: 14-cyfrowe kody, Hiszpania i pomoc
description: Śledź przesyłkę Redur w Hiszpanii, Portugalii lub Europie: 14-cyfrowe numery, skany oddziałów i doręczeń, odbiór w oddziale, trasy Eurodis i pomoc.
slug: sledzenie-redur
published: 2026-11-24
updated: 2026-11-24
---

**Redur** (*Redur S.A.*) to hiszpański operator transportu ekspresowego i logistyki z ponad 50-letnią historią oraz siedzibą w Algete pod Madrytem ([redur.es](https://redur.es/)). Prowadząc w 100 % własną sieć ponad 61 oddziałów (*delegaciones*) i 3 000 pracowników w Hiszpanii kontynentalnej, na wyspach hiszpańskich i w Portugalii, Redur jest też członkiem założycielem **Eurodis**, europejskiej sieci przesyłek paczkowych i paletowych. Oprócz pilnych wysyłek krajowych B2B/B2C i codziennych wyjazdów drogowych w całej Europie Redur prowadzi dystrybucję farmaceutyczną z certyfikatem GDP (*Farma 15-25*) i logistykę kontraktową. Przesyłkę sprawdzisz na [redur.es/track-trace/](https://redur.es/track-trace/) lub wklejając 14-cyfrowy numer Redur albo link śledzenia w pole powyżej.

## Numery śledzenia Redur (14-cyfrowy format)

Numery śledzenia Redur (*N° Tracking*) mają 14 cyfr bez liter i spacji:

| Format (wymyślony) | Co to jest |
| --- | --- |
| `58492716380419` | Standardowy 14-cyfrowy numer przesyłki Redur (*NTRACKING*). |
| `61738492051628` | Inny 14-cyfrowy kod śledzenia Redur dla tras w Hiszpanii, Portugalii lub Eurodis. |

Na [redur.es/track-trace/](https://redur.es/track-trace/) formularz szuka także po numerze ekspedycji oraz referencjach nadawcy lub partnera, natomiast automatyczne śledzenie w Peek korzysta z 14-cyfrowego numeru (`NTRACKING`) lub bezpośredniego linku `redur.es/track-trace/?...&valor=`. Peek nie rozpoznaje Redur po samym numerze: goły 14-cyfrowy kod kieruje do DPD, [SEUR](carrier:seur) i innych przewoźników (w Hiszpanii najpierw SEUR), dlatego wklej link Redur lub wybierz Redur ręcznie. Zobacz [formaty numerów przesyłek](guide:tracking-number-formats).

## Co oznaczają statusy śledzenia Redur

Strona śledzenia Redur wyświetla 7-etapowy pasek postępu wraz z wierszami skanów z oddziałów (po hiszpańsku na `redur.es/track-trace/` albo po angielsku w wersji angielskiej):

| Redur podaje (ES / EN) | Co to oznacza dla Ciebie | Peek pokazuje |
| --- | --- | --- |
| `DOCUMENTACION` / `DATA ENTERING` | Elektroniczne dane wysyłkowe przekazane przez nadawcę przed fizycznym przyjęciem w oddziale (datowane tylko dniem). | „Zarejestrowana” |
| `ENTRADA PLAZA ORIGEN` / `INBOUND ORIGIN DEPOT` | Pierwszy fizyczny skan przyjęcia w oddziale nadania Redur. | „Przyjęta przez przewoźnika” |
| `SALIDA PLAZA ORIGEN` (`OUTBOUND ORIGIN DEPOT`), `ENTRADA/SALIDA PLAZA TRANSITO` (`INBOUND/OUTBOUND TRANSIT DEPOT`), `ENTRADA PLAZA DESTINO` (`INBOUND FINAL DEPOT`) | W drodze między oddziałami nadania, tranzytowymi i docelowymi w Hiszpanii lub Portugalii. | „W transporcie” |
| `CARGA CAMION T. INTERNACIONAL` / `TRUCK LOAD INTERNATIONAL TRAFFIC` | Załadowana na ciężarówkę do innego kraju, gdzie doręczenie realizuje partner. | „W transporcie” |
| `PENDIENTE DE FECHA CONCERTADA`, `FECHA DE ENTREGA CONCERTADA` / `BOOKING AGREED DATE` | Oczekuje na uzgodnienie daty doręczenia z odbiorcą lub potwierdzono umówiony termin dostawy. | „W transporcie” |
| `SALIDA A REPARTO` / `OUT FOR DELIVERY` | W dzisiejszym doręczeniu lokalnym pojazdem Redur. | „W doręczeniu” |
| `RECOGERÁN EN AGENCIA` | Zatrzymana w docelowym oddziale Redur do odbioru przez odbiorcę. | „Do odbioru” |
| `DESTINATARIO AUSENTE`, `INCORRECT ADDRESS`, `CERRADO PERMANENTE/TEMPORAL` | Nieudana próba doręczenia z powodu nieobecności odbiorcy, błędnego adresu lub zamkniętego lokalu. | „Próba doręczenia” |
| `DEVOLUCIÓN A ORIGEN` | W drodze powrotnej do nadawcy po nieudanym doręczeniu lub odmowie. | „Zwrócona nadawcy” |
| `ENTREGA` / `DELIVERY` | Doręczona odbiorcy (lub potwierdzona aktywnym ostatnim krokiem na pasku postępu Redur). | „Dostarczona” |

## Usługi w Hiszpanii, Portugalii i Europie w sieci Eurodis

Według [Redur](https://redur.es/) oraz [Usług Redur](https://redur.es/servicios/):

- **Krajowe Półwysep Iberyjski (*Servicio Doméstico*):** pilna dystrybucja paczek, palet i ładunków mieszanych w Hiszpanii kontynentalnej, na wyspach i w Portugalii przez ponad 61 własnych oddziałów, z powiadomieniami e-mail i SMS *Redur Connected* oraz systemem *Maia*.
- **Międzynarodowe Europa (*Servicio Internacional*):** codzienne wyjazdy po Europie dla przesyłek B2B i B2C w sieci partnerskiej **Eurodis**. Gdy pojawi się `CARGA CAMION T. INTERNACIONAL`, końcowe doręczenie w kraju docelowym przejmuje lokalny partner Eurodis.
- **Farmacja w kontrolowanej temperaturze (*Farma 15-25*):** dystrybucja zgodna z GDP w zakresie 15–25 °C dla branży medycznej i farmaceutycznej.

## Nieudane doręczenie, oczekiwanie na termin i zaginione paczki

- **`PENDIENTE DE FECHA CONCERTADA` (oczekiwanie na uzgodnienie daty):** Redur czeka na ustalenie z Tobą dnia doręczenia przed wyjazdem paczki; `FECHA DE ENTREGA CONCERTADA` lub `BOOKING AGREED DATE` oznacza uzgodniony termin. Sprawdź telefon i e-mail albo zadzwoń do Redur z 14-cyfrowym numerem.
- **`DESTINATARIO AUSENTE`, `INCORRECT ADDRESS` lub `RECOGERÁN EN AGENCIA`:** skontaktuj się szybko ze sprzedawcą lub obsługą klienta Redur z 14-cyfrowym kodem, aby potwierdzić adres, umówić kolejną próbę albo sprawdzić godziny odbioru w oddziale.
- **Przesyłki opóźnione, uszkodzone lub zaginione:** najpierw powiadom sklep lub nadawcę (ma umowę z Redur i może złożyć formalną reklamację) oraz ustal z Redur, w którym oddziale znajduje się paczka. Zobacz [dostarczona, ale nieodebrana](guide:delivered-not-received), [uszkodzona przesyłka](guide:damaged-parcel), [zaginiona przesyłka](guide:lost-parcel), [zwrócona nadawcy](guide:returned-to-sender) oraz [dlaczego śledzenie przestaje się aktualizować](guide:tracking-not-updating).

## Obsługa klienta Redur i centrala w Madrycie

Oficjalne dane kontaktowe opublikowane na [Redur Contacto](https://redur.es/contacto/) i [Usługach Redur](https://redur.es/servicios/):

- **Centrala telefoniczna (Hiszpania):** `(+34) 916 20 46 10` (faks `91 628 15 60`).
- **E-mail:** `info@redur.es`.
- **Formularz kontaktowy i katalog oddziałów:** [redur.es/contacto/](https://redur.es/contacto/).
- **Centrala (*Servicios Centrales Redur*):** *Redur S.A.*, Carretera Algete–Madrid Km 2.5 (P.I. Redur), 28110 Algete, Madryt, Hiszpania.

> Uważaj na fałszywe SMS-y z prośbą o drobną opłatę online za zmianę terminu doręczenia w Hiszpanii lub Portugalii. Najpierw weryfikuj komunikaty na [redur.es/track-trace/](https://redur.es/track-trace/) albo u sprzedawcy. Zobacz [fałszywe SMS-y o doręczeniu](guide:delivery-scam-texts).

## Śledzenie przesyłki Redur w Peek

Wklej bezpośredni link `redur.es/track-trace/?...&valor=` do [Peek](/), a Peek rozpozna Redur automatycznie. Ponieważ gołe 14-cyfrowe numery (`58492716380419` lub `61738492051628`, wymyślone) współdzieli wielu europejskich przewoźników, a Redur nie ma wyłącznego prefiksu, wpisz 14-cyfrowy numer i stuknij „Zmień” podczas dodawania lub „Zmień przewoźnika” na ekranie przesyłki, aby wybrać **Redur** (wyszukanie „Redur Spain”, „Redur Portugal” lub „Redur S.A.” też go znajduje); Peek sprawdzi go bezpośrednio w trackerze Redur i po potwierdzeniu wyświetli „Redur ma tę przesyłkę.”

Peek czyta tracker Redur i wyświetla etap („Zarejestrowana”, „Przyjęta przez przewoźnika”, „W transporcie”, „W doręczeniu”, „Do odbioru”, „Próba doręczenia”, „Zwrócona nadawcy”, „Dostarczona”), opis Redur oraz oddział. Gdy zaświeci się ostatni etap paska postępu Redur, Peek oznacza przesyłkę jako dostarczoną. Redur podaje godziny skanów bez strefy: Peek przypisuje każdy skan do strefy czasowej jego oddziału (Madryt dla `ALGETE`, Lizbona dla `LISBOA`, Wyspy Kanaryjskie dla `LAS PALMAS`), a skan z nierozpoznanego oddziału do strefy sąsiedniego skanu, i pokazuje wszystko w czasie Twojego urządzenia; `DOCUMENTACION` zachowuje wyłącznie datę dzienną. Peek nie zapisuje liczby paczek. Tracker Redur nie podaje szacowanej daty doręczenia, wagi, nazwy usługi, osobnej godziny doręczenia ani numeru partnera.

Gdy masz przesyłkę otwartą w Peek albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza status nawet co 10 minut; w dniu, gdy Redur pokazuje `SALIDA A REPARTO` lub `OUT FOR DELIVERY`, co 2 minuty od godz. 6:00 czasu oddziału najnowszego skanu, dopóki tracker Redur odpowiada. W pozostałym czasie Peek sprawdza co godzinę. Po 12 godzinach wyłącznie na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika” albo po dwóch dniach bez zmian sprawdza co godzinę niezależnie od sposobu śledzenia, a po siedmiu dniach na „Zarejestrowana” — co 6 godzin. Gdy tracker Redur nie odpowiada i Peek śledzi przesyłkę wyłącznie przez usługi śledzące, sprawdza co godzinę, co 3 godziny po spokojnym dniu i nawet co 15 minut w dniu, w którym przesyłka jest w doręczeniu, dopóki masz ją otwartą lub włączone powiadomienia.

Jeśli najnowszy skan ma cztery dni, a przesyłka nadal widnieje jako „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie”, Peek oznacza ją dniem tego skanu, np. „Brak aktualizacji od pon 12 paź”. Na stronie przesyłki pojawia się wtedy komunikat „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika Redur.” wraz z linkiem „Poproś przewoźnika Redur o pomoc” do strony kontaktowej Redur. Po trzech tygodniach bez nowego skanu pojawia się informacja „Od ponad trzech tygodni nic nowego. Jeśli przesyłka wciąż nie dotarła, zapytaj sprzedawcę. Peek dalej pyta przewoźnika Redur.”

Możesz wybrać powiadomienia dla opcji „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek śledzi wyłącznie aktualizacje statusu i nie może umówić terminu doręczenia, zmienić adresu ani skontaktować się za Ciebie z Redur.

## Pytania o śledzenie Redur

### Jak wygląda numer śledzenia Redur?

Numery śledzenia Redur to 14 cyfr bez liter i spacji (na przykład `58492716380419`, wymyślony).

### Co oznacza `PENDIENTE DE FECHA CONCERTADA` w Redur?

Oznacza, że Redur czeka na uzgodnienie z Tobą dnia doręczenia przed wyjazdem przesyłki do dostawy.

### Co oznacza `RECOGERÁN EN AGENCIA`?

Oznacza, że paczka oczekuje na odbiór osobisty w lokalnym oddziale Redur (*delegación*).

### Jak skontaktować się z obsługą klienta Redur?

Zadzwoń na hiszpańską centralę Redur pod `(+34) 916 20 46 10`, napisz na `info@redur.es` albo skorzystaj ze strony kontaktowej na [redur.es/contacto/](https://redur.es/contacto/).

:::sources
- [Redur: oficjalna strona](https://redur.es/) – ponad 50 lat, ponad 61 oddziałów w Hiszpanii i Portugalii, 3 000 pracowników, członek założyciel Eurodis, *Maia* i *Redur Connected*
- [Redur: Track & Trace](https://redur.es/track-trace/) – publiczna wyszukiwarka przesyłek
- [Redur: Servicios](https://redur.es/servicios/) – *Servicio Doméstico*, *Servicio Internacional*, *Farma 15-25* (GDP) i *Logística integral*
- [Redur: Contacto](https://redur.es/contacto/) – `(+34) 916 20 46 10`, `info@redur.es` oraz centrala w Algete (Madryt) (adres pomocy linkowany przez Peek)
- [Universal Parcel Scraper: redur](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/redur) – 14-cyfrowy format, reguła linków `redur.es/track-trace/`, hiszpańsko-angielskie mapowanie opisów, obsługa paska postępu doręczenia oraz datowanie skanów w strefie czasowej każdego oddziału
:::
