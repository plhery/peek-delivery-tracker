---
title: Śledzenie Paquetexpress: guías, Ocurre i reklamacje
description: Śledź paczkę Paquetexpress w Meksyku: guía i 11–13 cyfr, statusy po hiszpańsku, 2 próby, odbiór Ocurre 5 dni, reklamacje i 800 821 0208.
slug: sledzenie-paquetexpress
published: 2026-11-24
updated: 2026-11-24
---

Paquetexpress (*Operadora de Servicios Paquetexpress, S.A.P.I. de C.V.* z siedzibą w Los Mochis w stanie Sinaloa) to jeden z największych meksykańskich przewoźników paczkowych, kurierskich i towarowych — działa od 40 lat, zatrudnia ponad 12 200 osób, ma sieć oddziałów w całym Meksyku oraz wysyła przesyłki do ponad 240 krajów. Przesyłkę sprawdzisz bez zakładania konta na [stronie śledzenia Paquetexpress](https://www.paquetexpress.com.mx/rastreo) albo wklejając kod lub numer *guía* powyżej.

## Numery śledzenia Paquetexpress: kody i guías

[FAQ Paquetexpress](https://www.paquetexpress.com.mx/preguntas-frecuentes/) podaje, że wyszukiwarka przyjmuje kod śledzenia (*código de rastreo*), numer listu przewozowego (*número de guía*) lub referencję nadawcy (Peek sprawdza wyłącznie kody śledzenia i *guías*). Numeru szukaj w e-mailu wysyłkowym ze sklepu, w linku SMS od Paquetexpress albo na etykiecie:

| Format (zmyślony) | Co to jest |
| --- | --- |
| `MEX48WW2917364` | *Guía* Paquetexpress: 3-literowy kod oddziału nadania + 2 cyfry + 2 litery + 5–7 cyfr (obejmuje wszystkie paczki na jednym liście). |
| `482917364058` | 11–13-cyfrowy kod liczbowy (*código de rastreo*). Dzieli tę długość z przewoźnikami takimi jak [Mondial Relay](carrier:mondial-relay), [Colis Privé](carrier:colis-prive), [MRW](carrier:mrw), [BRT](carrier:brt) i [SF Express](carrier:sf-express). |

Jeśli meksykańska paczka zaczyna się od `JMX`, sprawdź [J&T Express Mexico](carrier:j-and-t-mx); inne formaty znajdziesz w [formatach numerów przesyłek](guide:tracking-number-formats).

## Co oznaczają statusy śledzenia Paquetexpress

Paquetexpress publikuje historię po hiszpańsku wraz z wewnętrznymi kodami zdarzeń (`SWB`, `BOK`, `TRN`, `WHU`, `HTC`, `HDC` itp.):

| Paquetexpress pokazuje (kod) | Co to oznacza dla ciebie | Peek pokazuje |
| --- | --- | --- |
| `Documentación De Servicios Web` (`SWB`), `Solicitud De Rad` (`HCR`), `Recolección Planeada` (`HCP`) | Etykieta utworzona elektronicznie albo zamówiono/zaplanowano odbiór; Paquetexpress nie ma jeszcze fizycznej paczki. | „Zarejestrowana” |
| `Documentada` (`BOK`), `Recolección Realizada` (`HCM`), `Confirmación De Recolección En Almacen` (`HCA`) | Nadana w oddziale, odebrana przez kierowcę lub przyjęta do magazynu oddziału nadawczego. | „Przyjęta przez przewoźnika” |
| `Mercancía En Tránsito` (`TRN`), `Transbordo De Remolque` (`TTM`), `Recibida En Sucursal de Transbordo` (`WHU`), `Planeado Para Entrega A Domicilio` (`HLP`) | W drodze między oddziałami lub naczepami albo przygotowywana w oddziale docelowym przed wyruszeniem na trasę. | „W transporcie” |
| `Recibida En Sucursal destino` (`WHU`) | W oddziale docelowym: nadal „W transporcie” przy doręczeniu pod adres (`ENT_EAD`), albo czeka na odbiór przy wysyłce *Ocurre* (`ENT_OCU`). | „W transporcie” lub „Do odbioru” |
| `En Ruta De Entrega A Domicilio` (`HTC`) | W trasie z kurierem do doręczenia pod drzwi dziś. | „W doręczeniu” |
| `Mercancía Entregada A Domicilio` (`HDC`), `Entregada En Sucursal` (`BDL`), `Pagada En Destino (Contado)` (`PDC`) | Doręczona pod adres, odebrana w oddziale lub opłacona gotówką przy odbiorze. | „Dostarczona” |

Zwróć uwagę, że `Rad Cancelación` (`HCC`, anulowane zlecenie odbioru) nie zatrzymuje przesyłki, bo nadawca może jeszcze sam nadać paczkę — Peek zachowuje więc etap poprzedniego skanu.

## Czasy dostaw, 2 próby, odbiór Ocurre i opłaty składowe

Według [strony Usług](https://www.paquetexpress.com.mx/servicios/), [FAQ](https://www.paquetexpress.com.mx/preguntas-frecuentes/) oraz zarejestrowanych w PROFECO [Warunków Świadczenia Usług (Clausulado, umowa 3808-2020)](https://www.paquetexpress.com.mx/clausulado-solicitud-servicios/):

- **Czasy przewozu:** krajowe przesyłki kurierskie i paczkowe docierają w **24, 48, 72, 96 lub 120 godzin** zależnie od dystansu (z opcjami Express tego samego dnia przed 12:00, 24 h i 48 h oraz doręczeniami sobotnimi). Paquetexpress zawsze rejestruje imię, podpis, datę i godzinę odbioru (*Acuse*).
- **Maksymalnie 2 próby doręczenia:** zgodnie z klauzulą 5 przy doręczeniu pod adres (*Entrega a Domicilio*) Paquetexpress podejmuje do **2 prób**. Gdy nikogo nie ma, kierowca zostawia awizo (*aviso de visita*) z numerami oddziału i infolinii, a paczka czeka w oddziale.
- **5 dni kalendarzowych bezpłatnego odbioru (Ocurre lub awizo):** według klauzuli 5 zarówno paczki *Ocurre*, jak i przesyłki po nieudanych próbach pod drzwiami trzeba odebrać w ciągu **5 dni kalendarzowych** od przybycia lub awiza. Po 5 dniach kalendarzowych Paquetexpress może bez uprzedzenia przenieść przesyłkę do magazynu przesyłek nieodebranych (*bodega de rezagos*) i naliczać **2% dziennej opłaty składowej** (od ceny wysyłki). Paczki nieodebrane po **90 dniach kalendarzowych** mogą trafić na aukcję lub zostać zniszczone.
- **Zmiana trybu dostawy lub adresu:** [FAQ](https://www.paquetexpress.com.mx/preguntas-frecuentes/) podaje, że możesz poprosić o przełączenie między *Ocurre* a dostawą pod adres — lub poprawić adres nawet po wyjściu z oddziału — dzwoniąc pod `(+52) 800 821 0208`. Oddziały znajdziesz na [Paquetexpress Sucursales](https://www.paquetexpress.com.mx/sucursales).

## Reklamacje, uszkodzone paczki i mediacja PROFECO

Przy zamówieniach online najpierw skontaktuj się ze sklepem w sprawie zwrotu pieniędzy lub wymiany. Zgodnie z [Warunkami Świadczenia Usług](https://www.paquetexpress.com.mx/clausulado-solicitud-servicios/) (klauzula 8 o reklamacjach):

- **Sprawdź przy odbiorze:** obejrzyj opakowanie przy kurierze, zapisz ewentualne naruszenie lub uszkodzenie na pokwitowaniu (*acuse de recibo*) i poproś o szczegółowe oględziny.
- **15 dni roboczych na reklamację:** złóż formalną reklamację w ciągu **15 dni roboczych** od odnotowania nieprawidłowości — osobiście w dowolnym oddziale (pon.–pt. 08:00–18:00), e-mailem na `reclamaciones@paquetexpress.com.mx` lub na [stronie reklamacji](https://www.paquetexpress.com.mx/contacto/solicitud-de-reclamacion/). Paquetexpress zobowiązuje się odpowiedzieć w ciągu **15 dni roboczych**.
- **Odpowiedzialność i ubezpieczenie:** według klauzuli 5 bez ubezpieczenia Paquetexpress odpowiada za utratę, uszkodzenie lub niedoręczenie do 15 dni ogólnej płacy minimalnej za tonę (lub proporcjonalnie dla lżejszej przesyłki), a za zawinione opóźnienie 5 dni płacy minimalnej za każdy dzień zwłoki. Według klauzuli 6 opcjonalne ubezpieczenie pokrywa do zadeklarowanej wartości (bez udziału własnego przy uszkodzeniu, 20% przy napadzie z bronią); wygasa po 5 dniach kalendarzowych w oddziale *Ocurre*, a przy dostawie pod adres przy 2. nieudanej próbie lub po 10 dniach kalendarzowych od przybycia do oddziału docelowego.
- **PROFECO:** na mocy klauzuli 10 spory podlegają administracyjnie meksykańskiej Federalnej Agencji Ochrony Konsumentów ([PROFECO](https://www.gob.mx/profeco), zarejestrowana umowa `3808-2020`).

W razie braku skanów lub wydania niewłaściwej osobie (co Paquetexpress prosi zgłaszać pod `800 821 0208`) zobacz [dlaczego śledzenie przestaje się aktualizować](guide:tracking-not-updating), [dostarczona, ale nieodebrana](guide:delivered-not-received), [uszkodzona paczka](guide:damaged-parcel), [zagubiona paczka](guide:lost-parcel) i [zwrócona nadawcy](guide:returned-to-sender).

## Obsługa klienta i kontakty Paquetexpress

Dane kontaktowe z [Paquetexpress Contact](https://www.paquetexpress.com.mx/en/contacto/), [Contacto (ES)](https://www.paquetexpress.com.mx/contacto/) oraz [Warunków Świadczenia Usług](https://www.paquetexpress.com.mx/clausulado-solicitud-servicios/):

- **Bezpłatna infolinia:** `+52 800 821 0208` — poniedziałek–piątek 08:00–21:00 i sobota 08:00–15:00; Warunki Świadczenia Usług podają 07:00–20:00 i 07:00–14:00 czasu pacyficznego.
- **WhatsApp:** `+52 668 195 0169` (link na [paquetexpress.com.mx](https://www.paquetexpress.com.mx/)).
- **E-mail i portal reklamacji:** `reclamaciones@paquetexpress.com.mx` oraz [Solicitud de Reclamación](https://www.paquetexpress.com.mx/contacto/solicitud-de-reclamacion/).
- **Prywatność / wnioski ARCO:** `solicitud.arco@paquetexpress.com.mx`.
- **Siedziba:** Operadora de Servicios Paquetexpress, S.A.P.I. de C.V., Blvd. Rosendo G. Castro No. 32 Poniente, Col. Centro, Los Mochis, Sinaloa, C.P. 81200, Meksyk.

> Uważaj na fałszywe SMS-y i e-maile z linkiem do opłaty kartą online za zmianę terminu doręczenia Paquetexpress: Paquetexpress potwierdza, że instrukcje dostawy zmienisz bezpośrednio pod numerem `800 821 0208`, a paczkę sprawdzisz na [paquetexpress.com.mx/rastreo](https://www.paquetexpress.com.mx/rastreo). Zobacz [fałszywe SMS-y o przesyłce](guide:delivery-scam-texts).

## Śledzenie paczki Paquetexpress w Peek

Ponieważ 11–13-cyfrowe kody są współdzielone przez wielu międzynarodowych przewoźników, [Peek](/) kieruje się twoim regionem przy wyborze kolejności zapytań. W Meksyku Peek pyta Paquetexpress w pierwszej rundzie dla pasujących *guías* (`MEX48WW2917364`, zmyślony) i kodów 11–13-cyfrowych (`482917364058`), wyświetlając „Paquetexpress ma tę przesyłkę.” po potwierdzeniu. Poza Meksykiem *guía* nadal trafia wyłącznie do Paquetexpress, natomiast kod 11–13-cyfrowy sprawdza Paquetexpress na końcu. Wklej link `paquetexpress.com.mx/rastreo/...` (lub `/en/rastreo/...`), aby wszędzie wybrać Paquetexpress od razu, albo stuknij „Zmień” podczas dodawania / „Zmień przewoźnika” na ekranie paczki (wyszukanie „Paquete Express” też go znajdzie).

Peek odpytuje bezpośrednio usługę historii Paquetexpress (dla kodów śledzenia i *guías*, bez referencji nadawcy) i pokazuje etap („Zarejestrowana”, „Przyjęta przez przewoźnika”, „W transporcie”, „W doręczeniu”, „Do odbioru”, „Dostarczona”), hiszpańskie opisy (z usuniętymi nazwiskami po `POR`, `a` i `, recibió`), oddziały/miasta, przewidywaną datę dostawy gdy jest aktywna, godzinę doręczenia oraz strefy czasowe meksykańskich oddziałów (pacyficzna, górska, centralna i Quintana Roo, przeliczane na czas twojego urządzenia). Dla wielopaczkowych *guías* Peek utrzymuje przesyłkę otwartą, dopóki choć jedna paczka nadal jest w drodze, nawet jeśli inna została już doręczona, a `Recibida En Sucursal destino` w przesyłkach *Ocurre* (`ENT_OCU`) automatycznie oznacza jako „Do odbioru”. Peek pomija pasek postępu ze strony, typ usługi, nagłówki nadania/przeznaczenia, referencje klienta, imiona nadawcy/odbiorcy, ulice oddziałów oraz obrazy podpisu/POD; waga i wymiary nie są udostępniane.

Gdy masz paczkę otwartą w Peek albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza status nawet co 10 minut; w dniu, gdy Paquetexpress pokaże `En Ruta De Entrega A Domicilio`, co 2 minuty od 6:00 według zegara najnowszego skanu Paquetexpress, dopóki tracker Paquetexpress odpowiada. Poza tym sprawdza co godzinę. Po 12 godzinach wyłącznie na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika”, albo po dwóch dniach bez zmian, sprawdza co godzinę niezależnie od sposobu śledzenia, a po siedmiu dniach na etapie „Zarejestrowana” — co 6 godzin. Gdy tracker Paquetexpress nie odpowiada i Peek korzysta wyłącznie z zewnętrznych usług śledzenia, sprawdza co godzinę, co 3 godziny po dniu ciszy i do co 15 minut w dniu, w którym paczka jest w doręczeniu, gdy masz paczkę otwartą lub masz włączone powiadomienia.

Gdy najnowszy skan ma cztery dni, a przesyłka nadal widnieje jako „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie”, Peek oznacza ją dniem tego skanu, np. „Brak aktualizacji od pon 12 paź”. Na stronie paczki pojawia się wtedy napis: „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika Paquetexpress.”, wraz z linkiem „Poproś przewoźnika Paquetexpress o pomoc” do strony kontaktowej Paquetexpress. Po trzech tygodniach bez nowego skanu widnieje komunikat: „Od ponad trzech tygodni nic nowego. Jeśli przesyłka wciąż nie dotarła, zapytaj sprzedawcę. Peek dalej pyta przewoźnika Paquetexpress.”

Możesz wybrać powiadomienia: „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek tylko monitoruje śledzenie — nie przełączy paczki między *Ocurre* a dostawą do domu ani nie złoży za ciebie reklamacji w Paquetexpress.

## Pytania o śledzenie Paquetexpress

### Ile prób doręczenia podejmuje Paquetexpress?

Na mocy klauzuli 5 [Warunków Świadczenia Usług](https://www.paquetexpress.com.mx/clausulado-solicitud-servicios/) Paquetexpress podejmuje do **2 prób** doręczenia pod adres. Gdy nikogo nie ma, kierowca zostawia awizo (*aviso de visita*), a paczka czeka w lokalnym oddziale.

### Jak długo Paquetexpress przechowuje paczkę Ocurre lub niedoręczoną?

Masz **5 dni kalendarzowych** bez opłat od przybycia do oddziału (lub od awiza). Po 5 dniach kalendarzowych Paquetexpress może przenieść paczkę do magazynu nieodebranych przesyłek z **2% dzienną opłatą składową**, a po 90 dniach kalendarzowych ją zlicytować lub zniszczyć.

### Czy mogę zmienić odbiór Ocurre na dostawę do domu albo poprawić adres?

Tak. [FAQ Paquetexpress](https://www.paquetexpress.com.mx/preguntas-frecuentes/) podaje, że możesz zadzwonić pod `(+52) 800 821 0208`, aby przełączyć między *Ocurre* a dostawą pod adres lub poprawić adres nawet po opuszczeniu oddziału.

### Ile czasu mam na zgłoszenie szkody lub utraty w Paquetexpress?

Zaznacz widoczne uszkodzenia na pokwitowaniu (*acuse de recibo*) przy odbiorze, a następnie złóż formalną reklamację w ciągu **15 dni roboczych** w oddziale, na `reclamaciones@paquetexpress.com.mx` lub przez [formularz reklamacji online](https://www.paquetexpress.com.mx/contacto/solicitud-de-reclamacion/). Paquetexpress odpowiada w ciągu 15 dni roboczych.

:::sources
- [Paquetexpress: Home](https://www.paquetexpress.com.mx/) – wejście do śledzenia, WhatsApp `+52 668 195 0169`, bezpłatna infolinia `+52 800 821 0208`
- [Paquetexpress: Rastreo](https://www.paquetexpress.com.mx/rastreo) – strona śledzenia przesyłek
- [Paquetexpress: Servicios](https://www.paquetexpress.com.mx/servicios/) – przewóz krajowy 24–120 h, Express tego samego dnia/24/48 h, sobota, podpis Acuse, 240+ krajów
- [Paquetexpress: International](https://www.paquetexpress.com.mx/en/internacional/) – usługi międzynarodowe
- [Paquetexpress: Conócenos](https://www.paquetexpress.com.mx/conocenos/) – 40 lat działalności, 12 200+ pracowników, 240+ krajów
- [Paquetexpress: Sucursales](https://www.paquetexpress.com.mx/sucursales) – wyszukiwarka oddziałów
- [Paquetexpress: Preguntas Frecuentes](https://www.paquetexpress.com.mx/preguntas-frecuentes/) – śledzenie bez konta, kod vs guía vs referencja, zmiana Ocurre/dom, korekta adresu, awizo, zgłaszanie otwartej paczki
- [Paquetexpress: Contact (EN)](https://www.paquetexpress.com.mx/en/contacto/) – `+52 800 821 0208`, pon.–pt. 08:00–21:00, sob. 08:00–15:00
- [Paquetexpress: Contacto (ES)](https://www.paquetexpress.com.mx/contacto/) – hiszpańska strona kontaktowa (adres pomocy linkowany przez Peek)
- [Paquetexpress: Solicitud de Reclamación](https://www.paquetexpress.com.mx/contacto/solicitud-de-reclamacion/) – formalny formularz reklamacji online
- [Paquetexpress: Clausulado de Solicitud de Servicios](https://www.paquetexpress.com.mx/clausulado-solicitud-servicios/) – umowa PROFECO `3808-2020`: 2 próby, 5 dni kalendarzowych bezpłatnego odbioru, 2% dziennego składowego, przepadek po 90 dniach, reklamacje 15 dni roboczych (`reclamaciones@paquetexpress.com.mx`), siedziba w Los Mochis
- [PROFECO](https://www.gob.mx/profeco) – Federalna Agencja Ochrony Konsumentów Meksyku
- [Universal Parcel Scraper: paquetexpress](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/paquetexpress) – wykrywanie guía i 11–13 cyfr, priorytet w Meksyku, reguła linku `/rastreo/`, kody zdarzeń, ETA, wielopaczkowe guías, obsługa `ENT_OCU`, usuwanie nazwisk i adres pomocy
:::
