---
title: Śledzenie Serpost: kody S10 z Peru, cło SUNAT i pomoc
description: Śledź przesyłkę Serpost (Servicios Postales del Perú): 13-znakowe kody S10 PE, statusy celne SUNAT, odbiór w placówce, reklamacje i infolinia Lima.
slug: sledzenie-serpost
published: 2026-11-24
updated: 2026-11-24
---

**Serpost** (*Servicios Postales del Perú S.A.*) to państwowy narodowy operator pocztowy Peru podlegający Ministerstwu Transportu i Komunikacji (MTC) ([gob.pe/serpost](https://www.gob.pe/serpost)). Obsługuje krajową pocztę i paczki w Peru, międzynarodowe wysyłki wychodzące (w tym [EMS](carrier:ems) i *Exporta Fácil*) oraz przychodzącą pocztę Światowego Związku Pocztowego (UPU) z zagranicy, udostępniając śledzenie online pod adresem [serpost.com.pe/Cliente/SegumientoLinea](https://www.serpost.com.pe/Cliente/SegumientoLinea). Możesz sprawdzić tam peruwiańską lub przychodzącą międzynarodową przesyłkę S10 albo wkleić jej 13-znakowy kod w pole powyżej.

## Numery przesyłek Serpost (13-znakowy format UPU S10)

Serpost śledzi standardowe 13-znakowe kody kreskowe UPU S10: dwie litery usługi, 8 cyfr seryjnych, 1 cyfrę kontrolną S10 oraz 2-literowy kod kraju nadania (`PE` dla przesyłek z Peru lub kod kraju zagranicznego, np. `US`, `CN`, `ES` albo `JP`, dla paczek przychodzących do Peru):

| Format (wymyślony) | Co oznacza |
| --- | --- |
| `RR482917367PE` | 13-znakowy list polecony wysłany z Peru (`RR` + 9 cyfr z poprawną cyfrą kontrolną S10 + `PE`). |
| `EE739184627PE` | 13-znakowa międzynarodowa paczka ekspresowa [EMS](carrier:ems) wysłana z Peru (`EE` + 9 cyfr z poprawną cyfrą kontrolną S10 + `PE`). |

Nierejestrowane listy ekonomiczne bez 13-znakowego kodu S10 nie pojawiają się w trackerze Serpost. W przypadku paczek zagranicznych przychodzących do Peru (np. zakończonych na `CN`, `US` czy `GB`), po dotarciu do kraju wybór Serpost pokazuje lokalne skany peruwiańskiego urzędu celnego (*Aduana*) i placówki pocztowej pod tym samym 13-znakowym kodem S10. Zobacz poradnik o [formatach numerów śledzenia](guide:tracking-number-formats).

## Co oznaczają statusy śledzenia Serpost

Serpost publikuje hiszpańskojęzyczne opisy zdarzeń, podając konkretną peruwiańską jednostkę pocztową (*Administración Postal*), w której wykonano skan:

| Serpost podaje | Co oznacza dla Ciebie | Peek pokazuje |
| --- | --- | --- |
| `ENVÍO ADMITIDO EN ...` | Przyjęta w okienku wskazanej peruwiańskiej placówki Serpost. | „Przyjęta przez przewoźnika” |
| `ENVÍO LLEGÓ A SERPOST`, `ENVÍO EN PROCESAMIENTO`, `ENVÍO EN TRÁNSITO` | Przychodząca paczka międzynarodowa przyjęta przez Serpost w Peru, sortowana w centrali lub w transporcie krajowym. | „W transporcie” |
| `ENVÍO SUJETO A TRÁMITE ANTE ADUANA` | Skierowana przez peruwiański urząd celny (SUNAT) do odprawy celnej, wyceny lub naliczenia należności. | „W urzędzie celnym” |
| `ENVÍO SIN OBSERVACIÓN POR LA ADUANA` | Zwolniona bez uwag przez urząd celny i przekazana z powrotem do Serpost. | „W transporcie” |
| `ENVÍO SALIÓ DE PERÚ`, `ENVÍO RUMBO AL PAÍS DE DESTINO` | Wychodząca przesyłka międzynarodowa opuściła Peru w stronę poczty kraju docelowego. | „W transporcie” |
| `ENVÍO LLEGÓ A LA ADMINISTRACIÓN POSTAL ...` | Dotarła do lokalnego peruwiańskiego urzędu doręczającego przed wydaniem na trasę. | „W transporcie” |
| `ENVÍO EN PROCESO DE DISTRIBUCIÓN`, `SALIÓ A DISTRIBUCIÓN` | Wydana listonoszowi Serpost do doręczenia pod adres. | „W doręczeniu” |
| `ENVÍO DISPONIBLE PARA ENTREGA O RECOJO` | Gotowa do odbioru we wskazanej placówce Serpost. | „Do odbioru” |
| `INTENTO FALLIDO DE ENTREGA`, `SE INTENTÓ ENTREGAR PERO FUE IMPOSIBLE` | Nie udało się doręczyć (nieobecność odbiorcy lub problem z dostępem); sprawdź awizo z placówki. | „Próba doręczenia” |
| `ENTREGADO`, `ENVÍO ENTREGADO`, `FUE ENTREGADO` | Doręczona odbiorcy lub odebrana na poczcie. | „Dostarczona” |

## Czasy doręczeń, cło i oficjalne terminy reklamacji

Według [Usług nadawczych Serpost](https://www.gob.pe/institucion/serpost/pages/20800-enviar-cartas-dentro-y-fuera-del-pais-con-serpost) oraz [Reklamacji i zapytań Serpost](https://www.gob.pe/institucion/serpost/pages/33693-reclamos-y-consultas-servicios-postales-del-peru):

- **Deklarowane czasy dla listów:** krajowy **Expreso** **maks. 48–72 godziny**; krajowy **Estándar** **5–7 dni roboczych**; krajowy **Económico** **7–10 dni roboczych**; międzynarodowy **Expreso** **7–10 dni roboczych**; międzynarodowy **Económico** **10–30 dni roboczych**. Tylko listy Expreso i Estándar można śledzić online.
- **Peruwiańskie cło (`ENVÍO SUJETO A TRÁMITE ANTE ADUANA`):** przesyłka jest wstrzymana na czas formalności celnych i nie trafi do doręczenia, dopóki urząd celny jej nie zwolni (`ENVÍO SIN OBSERVACIÓN POR LA ADUANA`). Tracker Serpost może wyświetlać zawiadomienie o nadejściu (*Aviso de llegada*). Zobacz poradnik o [paczkach w urzędzie celnym](guide:customs).
- **Oficjalne okna i terminy reklamacji:** reklamacje przesyłek **EMS** można składać od dnia po upływie terminu doręczenia do **4 miesięcy** od nadania (odpowiedź w ciągu **30 dni kalendarzowych**); dla międzynarodowych listów poleconych po **20 dniach kalendarzowych**, a dla paczek międzynarodowych po **30 dniach kalendarzowych**; reklamacje krajowe otrzymują odpowiedź w ciągu **16 dni roboczych** (odszkodowanie krajowe wynosi **3× opłaconej opłaty pocztowej**, a międzynarodowe podlega zasadom UPU).

## Przesyłka Serpost opóźniona, na cle lub zaginiona

- **Cisza w poczcie międzynarodowej:** przesyłki wychodzące zatrzymane po `ENVÍO SALIÓ DE PERÚ` lub przychodzące z zagranicy często czekają wiele dni albo tygodni między wysyłką lotniczą a przyjęciem na cło. Gdy wychodząca przesyłka `...PE` opuści Peru, sprawdzaj pocztę kraju docelowego (np. [USPS](carrier:usps)). Zobacz poradnik o [braku aktualizacji śledzenia](guide:tracking-not-updating).
- **Złożenie reklamacji (*reclamo*) w Serpost:** wyślij formularz na `servicioalcliente@serpost.com.pe`, zadzwoń pod `(01) 511-5110` lub odwiedź najbliższą placówkę Serpost — otrzymasz e-mailem numer sprawy (*número de expediente*) do śledzenia postępu. Zobacz też [zaginiona paczka](guide:lost-parcel), [uszkodzona paczka](guide:damaged-parcel), [dostarczona, ale nieodebrana](guide:delivered-not-received) oraz [zwrócona do nadawcy](guide:returned-to-sender).

## Obsługa klienta Serpost i kontakty w Limie

Oficjalne kanały opublikowane na [Serpost Contacto (gob.pe)](https://www.gob.pe/institucion/serpost/contacto-y-numeros-de-emergencias):

- **Call Center (Peru):** `(01) 511-5110`.
- **Centrala główna:** `511-5000` (linie centrali `(511) 533-2001` / `533-2002` / `533-2003` / `533-2004`).
- **E-mail obsługi klienta i reklamacji:** `servicioalcliente@serpost.com.pe` (webmaster: `webmaster@serpost.com.pe`).
- **Siedziba główna (*Sede Central*):** Av. Tomás Valle cdra. 7 s/n, Los Olivos, Lima 15302, Peru.
- **Tracker online i portal rządowy:** [serpost.com.pe/Cliente/SegumientoLinea](https://www.serpost.com.pe/Cliente/SegumientoLinea) oraz [gob.pe/serpost](https://www.gob.pe/serpost).

> Uważaj na fałszywe SMS-y twierdzące, że przesyłka Serpost została zablokowana do czasu wniesienia drobnej opłaty przez nieznany link. Zawsze sprawdzaj powiadomienia celne bezpośrednio na [serpost.com.pe/Cliente/SegumientoLinea](https://www.serpost.com.pe/Cliente/SegumientoLinea) albo na oficjalnych stronach `gob.pe` / SUNAT. Zobacz poradnik o [fałszywych SMS-ach o paczkach](guide:delivery-scam-texts).

## Śledzenie Serpost w Peek

Gdy wpiszesz w [Peek](/) 13-znakowy peruwiański kod S10 (`RR482917367PE` lub `EE739184627PE`, wymyślone), Peek sprawdza cyfrę kontrolną S10 i pyta najpierw Serpost, a potem Chronopost. W przypadku zagranicznego kodu S10 zmierzającego do Peru albo ręcznej zmiany na Serpost kliknij „Zmień” podczas dodawania lub „Zmień przewoźnika” na ekranie paczki i wybierz **Serpost** (wyszukanie „SERPOST”, „Peru Post” lub „Servicios Postales del Perú” też działa). Gdy Serpost zwróci skany, Peek wyświetla „Serpost ma tę przesyłkę.”

Peek odczytuje tracker Serpost i pokazuje etap („Przyjęta przez przewoźnika”, „W transporcie”, „W urzędzie celnym”, „W doręczeniu”, „Do odbioru”, „Próba doręczenia”, „Dostarczona”), hiszpańską treść Serpost bez zaproszenia na WhatsApp placówki, nazwę placówki oraz godzinę doręczenia. Gdy Serpost linkuje mapę placówki w Peru, Peek zaznacza tam skan na mapie przesyłki; gdy przesyłka jest w doręczeniu, do odbioru lub dostarczona, Peek pokazuje Peru jako kraj docelowy. Serpost podaje czasy według zegara w Limie: Peek datuje je według czasu Limy i wyświetla na zegarze Twojego urządzenia. Tracker Serpost nie podaje szacowanej daty doręczenia, wagi ani nazwiska odbierającego.

Gdy masz przesyłkę otwartą w Peek albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza status nawet co 10 minut; w dniu, gdy Serpost pokazuje `ENVÍO EN PROCESO DE DISTRIBUCIÓN`, co 2 minuty od godz. 6:00 czasu Limy, dopóki tracker Serpost odpowiada. W pozostałym czasie Peek sprawdza co godzinę. Po 12 godzinach wyłącznie na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika” albo po dwóch dniach bez zmian sprawdza co godzinę niezależnie od sposobu śledzenia, a po siedmiu dniach na „Zarejestrowana” — co 6 godzin. Gdy tracker Serpost nie odpowiada i Peek śledzi przesyłkę wyłącznie przez usługi śledzące, sprawdza co godzinę, co 3 godziny po spokojnym dniu i nawet co 15 minut w dniu, w którym przesyłka jest w doręczeniu, dopóki masz ją otwartą lub włączone powiadomienia.

Jeśli najnowszy skan ma cztery dni, a przesyłka nadal widnieje jako „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie”, Peek oznacza ją dniem tego skanu, np. „Brak aktualizacji od pon 12 paź”. Na stronie przesyłki pojawia się wtedy komunikat „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika Serpost.” wraz z linkiem „Poproś przewoźnika Serpost o pomoc” do strony kontaktowej Serpost w gob.pe. Po trzech tygodniach bez nowego skanu pojawia się informacja „Od ponad trzech tygodni nic nowego. Jeśli przesyłka wciąż nie dotarła, zapytaj sprzedawcę. Peek dalej pyta przewoźnika Serpost.”

Możesz wybrać powiadomienia „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek monitoruje wyłącznie aktualizacje śledzenia i nie może dokonać odprawy celnej w SUNAT, złożyć reklamacji ani skontaktować się z Serpost w Twoim imieniu.

## Pytania o śledzenie Serpost

### Jak wygląda numer śledzenia Serpost?

Serpost śledzi 13-znakowe kody UPU S10 — dwie litery, 9 cyfr (z cyfrą kontrolną na końcu) i 2-literowy kod kraju nadania, np. `PE` (jak `RR482917367PE` lub `EE739184627PE`, wymyślone), a także zagraniczne kody S10 przychodzące do Peru.

### Co oznacza `ENVÍO SUJETO A TRÁMITE ANTE ADUANA`?

Oznacza, że peruwiański urząd celny (SUNAT) skierował paczkę międzynarodową do odprawy celnej lub kontroli opłat importowych, zanim Serpost będzie mógł przekazać ją do lokalnego doręczenia albo odbioru w placówce.

### Ile trwa doręczenie z Serpost?

Serpost publikuje na [gob.pe](https://www.gob.pe/institucion/serpost/pages/20800-enviar-cartas-dentro-y-fuera-del-pais-con-serpost) czasy dla listów: **48–72 godziny** dla krajowego Expreso, **5–7 dni roboczych** dla krajowego Estándar, **7–10 dni roboczych** dla krajowego Económico lub międzynarodowego Expreso oraz **10–30 dni roboczych** dla międzynarodowego Económico.

### Jak skontaktować się z obsługą klienta Serpost lub złożyć reklamację?

Zadzwoń do Call Center Serpost pod `(01) 511-5110`, napisz na `servicioalcliente@serpost.com.pe` lub odwiedź placówkę Serpost; pełna lista kontaktów widnieje na [gob.pe/institucion/serpost/contacto-y-numeros-de-emergencias](https://www.gob.pe/institucion/serpost/contacto-y-numeros-de-emergencias).

:::sources
- [Serpost: Seguimiento en Línea](https://www.serpost.com.pe/Cliente/SegumientoLinea) – oficjalny portal śledzenia online i Call Center `(01) 511-5110`
- [Serpost: Portal Institucional (gob.pe)](https://www.gob.pe/serpost) – oficjalny portal rządowy *Servicios Postales del Perú S.A.* (MTC)
- [Serpost: Contacto y Números de Atención](https://www.gob.pe/institucion/serpost/contacto-y-numeros-de-emergencias) – Call Center `511-5110`, centrala `511-5000`, `servicioalcliente@serpost.com.pe` i siedziba Los Olivos (strona pomocy linkowana w Peek)
- [Serpost: Reclamos y Consultas](https://www.gob.pe/institucion/serpost/pages/33693-reclamos-y-consultas-servicios-postales-del-peru) – okna reklamacyjne (20 dni polecone, 30 dni paczki, 4 mies. EMS), terminy odpowiedzi (16 dni krajowe / 30 dni EMS) i zasady odszkodowań
- [Serpost: Envíos Nacionales e Internacionales](https://www.gob.pe/institucion/serpost/pages/20800-enviar-cartas-dentro-y-fuera-del-pais-con-serpost) – opublikowane czasy doręczeń dla listów według usług
- [Universal Parcel Scraper: serpost](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/serpost) – weryfikacja cyfry kontrolnej UPU S10, zapytanie `SegumientoLinea`, reguły hiszpańskich statusów, odczyt nazwy placówki i współrzędnych w Peru oraz znaczniki `America/Lima`
:::
