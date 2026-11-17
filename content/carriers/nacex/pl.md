---
title: Śledzenie przesyłek NACEX: numer, statusy i kontakt
description: Śledź paczkę NACEX z Hiszpanii lub Portugalii: format numeru 0100/00000005, znaczenie hiszpańskich statusów po polsku, punkty NACEX.shop i kontakt.
slug: nacex-sledzenie
published: 2026-11-17
updated: 2026-11-17
---

NACEX to firma kurierska ekspresowa należąca do grupy Logista, która posiada własną sieć franczyzową w Hiszpanii, Portugalii, Andorze oraz krajach Beneluksu, a drogą lądową i lotniczą wysyła przesyłki także do pozostałych krajów Europy. W Polsce spotkasz się z NACEX najczęściej po zakupach w hiszpańskim lub portugalskim sklepie internetowym albo podczas odbioru zamówienia na Półwyspie Iberyjskim. Aby sprawdzić status paczki, wpisz jej numer (cztery cyfry, ukośnik, osiem cyfr) na [stronie głównej NACEX](https://www.nacex.es/irHome.do) lub wklej go w polu powyżej – NACEX nie wyszukuje przesyłek po samym numerze zamówienia ze sklepu ani po kodzie pocztowym.

## Numer śledzenia NACEX: jak wygląda i z czym go nie mylić

Pole wyszukiwania na stronie NACEX podpowiada wzór `0000/00000000`, a szczegółowy formularz na [stronie śledzenia NACEX](https://www.nacex.es/irSeguimiento.do) prosi o wpisanie obu części osobno (*Agencia* oraz *Albarán*). Na przykładzie zmyślonego numeru:

:::anatomy 0100 / 00000005
- 0100 | Agencja: czterocyfrowy kod oddziału franczyzowego NACEX nadającego paczkę
- / | Ukośnik oddzielający kod oddziału od numeru listu przewozowego
- 00000005 | List przewozowy (*albarán*): ośmiocyfrowy numer samej przesyłki
:::

NACEX nie podaje, w której wiadomości odbiorca znajdzie ten numer ani pod jaką nazwą nadawcy przychodzą SMS-y; poradnik [jak znaleźć numer śledzenia](guide:find-tracking-number) podpowiada typowe miejsca, takie jak e-mail potwierdzający wysyłkę, konto klienta w sklepie czy etykieta.

| Co masz pod ręką | Co to za numer i jak go śledzić |
| --- | --- |
| `0100/00000005` | Pełny numer przesyłki NACEX: wklej go bez zmian (spacje wokół ukośnika, np. `0100 / 00000005`, są również akceptowane przy bezpośrednim wpisywaniu numeru). |
| `010000000005` | 12 cyfr bez ukośnika: prawdopodobnie numer NACEX, w którym sklep pominął znak `/` po czwartej cyfrze. Bez ukośnika Peek nie podpowiada NACEX i sprawdza przewoźników używających [12-cyfrowych numerów](guide:tracking-number-formats). Dopisz `/` po czwartej cyfrze. |
| `0100000005` | Do 10 cyfr: to zapewne „numer referencyjny alternatywny” (*referencia alternativa*) widoczny na etykiecie NACEX. NACEX nie podaje, gdzie go wpisać; Peek nie odpytuje po nim systemu NACEX. |
| `3230 0000 0000 0000` | 16 cyfr (lub 23 cyfry) bez ukośnika: to format należący do [Correos Express](carrier:correos-express) albo kurierów takich jak [TNT](carrier:tnt), a nie do NACEX. |

Upewnij się też, że zamiast cyfry `0` nie wpisano litery `O` – obecność liter uniemożliwia rozpoznanie numeru. Jeśli [zewnętrzny formularz śledzenia](https://www.nacex.es/seguimientoFormularioExterno.do?intcli=0000/00000000) wyświetla komunikat *„No existe ningún albarán introducido en el sistema cumpliendo los criterios especificados. Consulte con su agencia NACEX más cercana”* („W systemie nie ma żadnego listu przewozowego spełniającego podane kryteria. Skontaktuj się z najbliższą agencją NACEX”), sprawdź cztery pierwsze cyfry, ukośnik i osiem kolejnych cyfr. Gdy sprzedawca dopiero wygenerował wysyłkę, odczekaj kilka godzin lub poproś sklep o potwierdzenie numeru.

## Statusy śledzenia NACEX: tłumaczenie z hiszpańskiego

Serwis `nacex.es` oferuje u góry strony wybór języka (hiszpański, portugalski, angielski, kataloński, francuski i niemiecki – bez polskiego), ale NACEX nie publikuje oficjalnego słownika statusów, a komunikaty odczytywane przez Peek przychodzą po hiszpańsku. W typowej kolejności oznaczają one:

| Hiszpański status NACEX | Co oznacza dla Ciebie |
| --- | --- |
| `Notificado` | „Zgłoszono”: elektroniczna informacja o przesyłce z dnia jej zlecenia, przed przyjęciem paczki lub po nim, a nie fizyczny skan. |
| `Aceptada` | „Przyjęta”: oddział NACEX przyjął paczkę (jeśli obok widnieje dopisek `WEB`, oznacza to jedynie rejestrację online, a przewoźnik nie ma jeszcze paczki w rękach). |
| `En tránsito` | „W transporcie”: przesyłka przemieszcza się między agencjami franczyzowymi i sortowniami NACEX. |
| `En reparto` | „W doręczeniu”: kurier zabrał paczkę na dzisiejszą trasę. |
| `Ausente` | „Nieobecny”: próba doręczenia nie powiodła się, bo nikogo nie zastano pod adresem. |
| `Gestión de servicio, posible demora` | „Obsługa serwisowa, możliwe opóźnienie”: oddział wyjaśnia utrudnienie operacyjne (np. niedokończoną trasę) i uprzedza o możliwym poślizgu. |
| `Solución de entrega concertada` | Po przeszkodzie ustalono nowe warunki doręczenia (np. inny dzień wizyty kuriera). |
| `Solución de entrega en punto` | Uzgodniono przekierowanie do punktu odbioru NACEX – paczka jest jednak dopiero w drodze do tego punktu. |
| `Cambio de dirección` | „Zmiana adresu”: przesyłka jedzie pod nowy, zatwierdzony adres. |
| `Disponible en punto NACEX` | „Dostępna w punkcie NACEX”: paczka dotarła na miejsce i czeka na odbiór w punkcie lub oddziale. |
| `Contacta con agencia` | „Skontaktuj się z agencją”: oddział doręczający prosi o kontakt w sprawie przesyłki. |
| `Solucionado sin OK` | „Zamknięto bez OK”: sprawa zakończona bez doręczenia odbiorcy, np. zwrotem do nadawcy. |
| `Entregado` | „Doręczono”: przesyłka wydana odbiorcy. |

Zwróć uwagę na statusy `Solución de entrega concertada` oraz `Solución de entrega en punto`: informują o przyjęciu dyspozycji, a nie o gotowości do odbioru. Do sklepu lub automatu wybierz się dopiero wtedy, gdy zobaczysz `Disponible en punto NACEX`. W historii bywa też widoczny wpis `Sin estado` („brak statusu”) – to adnotacja administracyjna (np. o wysłaniu powiadomienia lub wyczerpaniu limitu prób), która nie zmienia wcześniejszego etapu. Natomiast `Solucionado sin OK` wymaga szybkiego kontaktu z NACEX albo ze sprzedawcą. Ogólne zasady działania poszczególnych etapów opisuje poradnik [co oznaczają statusy śledzenia](guide:tracking-statuses).

## Doręczenie NACEX, nieobecność w domu i punkty NACEX.shop

**Wysyłka do Polski i reszty Europy:** z kontynentalnej Hiszpanii i Portugalii NACEX realizuje połączenia drogowe do pozostałych państw Europy w ramach usług `Business Europa` (dla firm) oraz `Ecommerce Europa` (przesyłki jednopaczkowe dla klientów indywidualnych do 40 kg pod drzwi lub do 20 kg do punktu odbioru, zależnie od dostępności), a także przesyłki lotnicze na cały świat (`Internacional exprés`) we współpracy z międzynarodowymi partnerami.

**Gdy kurier nikogo nie zastanie:** zgodnie z ogólnymi warunkami przewozu NACEX na 2026 rok przy doręczeniach na adres prywatny kurier zostawia awizo w celu umówienia drugiej i ostatecznej próby doręczenia (*segunda y definitiva entrega*). Sekcja FAQ doprecyzowuje, że awizo jest zostawiane „w miarę możliwości” (*siempre que sea posible*), a jeśli agencja posiada Twój numer telefonu, kontaktuje się później, aby ustalić kolejny termin. Gdy wiesz wcześniej, że nie będzie Cię w domu, skontaktuj się z biurem obsługi klienta lub najbliższym oddziałem NACEX.

**Powiadomienie *Pre-Alerta* i *Mi Entrega Preferida* („Moja preferowana dostawa”):** jeżeli nadawca wykupił usługę z powiadomieniem wyprzedzającym (*Pre-Alerta*), NACEX wysyła SMS lub e-mail z linkiem do panelu *Mi Entrega Preferida*. Możesz w nim zmienić datę lub przedział godzinowy doręczenia albo przekierować przesyłkę jednopaczkową do punktu NACEX (oddziału franczyzowego lub punktu NACEX.shop). W usłudze *Entrega con PIN* wydanie paczki następuje po podaniu kodu otrzymanego w powiadomieniu.

**Sieć NACEX.shop i automaty paczkowe:** w Hiszpanii i Portugalii działa ponad 4000 partnerskich punktów handlowych i automatów (*Smart Lockers*), z których wiele jest otwartych także w soboty i niedziele. Przesyłka jednopaczkowa nadana do punktu NACEX.shop jest gotowa do odbioru następnego dnia roboczego od godziny 09:00 – lub najpóźniej od 12:00, jeśli dany punkt otwiera się później – po wysłaniu powiadomienia. Mapę placówek znajdziesz w [wyszukiwarce punktów NACEX](https://www.nacex.es/irCerca.do) (zakładki dla Hiszpanii i Andory). NACEX nie publikuje maksymalnego czasu przechowywania paczek w sklepach NACEX.shop ani w automatach.

**Odbiór w oddziale franczyzowym NACEX:** przesyłka skierowana do odbioru własnego w agencji NACEX czeka tam maksymalnie **15 dni**, po czym wraca do nadawcy na koszt związany ze zwrotem. Przed wizytą uprzedź oddział; odbiór odbywa się od poniedziałku do piątku (zależnie od placówki w godzinach 08:00–20:00 bez przerwy albo 08:00–14:00 i 16:00–20:00). Inna osoba może odebrać paczkę w Twoim imieniu, okazując **podpisane przez Ciebie upoważnienie** oraz **kserokopię Twojego dokumentu tożsamości**.

**Soboty, święta i odmowa przyjęcia:** soboty, niedziele i dni świąteczne nie są w NACEX dniami roboczymi. Doręczenie w sobotę (09:00–13:00) to dodatkowo płatna opcja dostępna w miejscowościach z agencją NACEX i w promieniu do 15 km wokół niej (na Wyspy Kanaryjskie oraz kierunki wymagające odprawy celnej – wyłącznie dla dokumentów). Paczki będącej już w transporcie nie da się anulować, ale możesz odmówić jej przyjęcia przy kurierze lub wcześniej powiadomić swój oddział NACEX.

## Cło i podatki: Polska oraz hiszpańskie terytoria specjalne

Przesyłki z kontynentalnej Hiszpanii, Balearów lub kontynentalnej Portugalii do Polski przemieszczają się wewnątrz unijnego obszaru VAT bez odprawy celnej. Wyjątkiem są przesyłki towarów na Wyspy Kanaryjskie, do Ceuty, Melilli, Andory i Gibraltaru: przechodzą przez odprawę celną, a NACEX wymaga faktury handlowej lub oświadczenia w obrocie prywatnym, a formalności celne mogą wydłużyć dostawę; zobacz też poradnik o [odprawie celnej i opłatach importowych](guide:customs).

## Opóźniona, zagubiona lub uszkodzona paczka NACEX

Formalną reklamację w NACEX składa klient zlecający przewóz (zwykle sklep internetowy), a rozliczenia prowadzone są przez oddział obsługujący nadawcę:

- **Brak ruchu w śledzeniu lub niedoręczona paczka:** FAQ NACEX zaleca kontakt pod adresem `atencion.cliente@nacex.com` lub numerem `900 100 000` (bez minimalnego czasu oczekiwania). Sprawdź też, co zrobić, gdy [śledzenie przestaje się aktualizować](guide:tracking-not-updating) albo gdy paczka ma status [`Entregado`, ale jej nie masz](guide:delivered-not-received).
- **Opóźnienie:** klient NACEX ma **31 dni kalendarzowych** (*31 días naturales*) na reklamację przekroczenia terminu bez uzasadnionej przyczyny (NACEX uprzedza o możliwych utrudnieniach podczas Black Friday i świąt Bożego Narodzenia).
- **Zaginięcie, brak zawartości lub uszkodzenie:** widoczne szkody zewnętrzne lub braki zgłoś na piśmie **od razu przy odbiorze**, a szkody niewidoczne w ciągu 7 dni kalendarzowych (art. 60 hiszpańskiej ustawy *Ley 15/2009*). NACEX przyjmuje reklamacje z tytułu zaginięcia, braku towaru lub uszkodzenia w ciągu **7 dni kalendarzowych** (przy braku towaru lub uszkodzeniu od doręczenia), ze zdjęciami i fakturą.

Przy zakupach konsumenckich w UE serwis *Your Europe* przypomina, że sprzedawca odpowiada za towar aż do fizycznego wydania paczki (chyba że sam wybrałeś przewoźnika spoza opcji sklepu) i ma dostarczyć zamówienie w ciągu **30 dni**, o ile nie ustalono inaczej. Zgłaszaj więc problem również sprzedawcy.

## Kontakt z obsługą klienta NACEX

Dane ze stron hiszpańskich NACEX (bez opublikowanych godzin pracy infolinii, e-maila czy czatu i bez numeru WhatsApp):

- **Telefon (Hiszpania i Andora):** `900 100 000` (w Hiszpanii bezpłatny z sieci stacjonarnych i komórkowych według FAQ NACEX oraz CNMC; NACEX nie podaje, czy działa z zagranicy).
- **E-mail:** `atencion.cliente@nacex.com` (oraz `shop@nacex.com` na stronie kontaktowej).
- **Czat online:** na [stronie kontaktowej NACEX](https://www.nacex.es/irContactenosContacto.do) (*„Chatee ahora con nuestros agentes”*).
- **Oddziały lokalne:** adresy i godziny w [wyszukiwarce punktów NACEX](https://www.nacex.es/irCerca.do).
- **Reklamacje:** zasady dla klientów na [stronie reklamacji NACEX](https://www.nacex.es/irReclamaciones.do) (wstępna weryfikacja w 48 godzin roboczych, docelowe zamknięcie w 7 dni kalendarzowych zależnie od sprawy).

## Śledzenie przesyłek NACEX w aplikacji Peek

[Peek](/) rozpoznaje numer NACEX od razu po układzie czterech cyfr, ukośnika i ośmiu cyfr (`0100/00000005`) w polu „Wykryty przewoźnik” (z przyciskiem „Zmień”). Możesz też wkleić link `nacex.es` z `intcli=` albo cały e-mail ze sklepu.

Peek sprawdza status nawet co 10 minut, a w dniu wydania kurierowi („W doręczeniu”) od 06:00 czasu lokalnego nawet co 2 minuty, dopóki odpowiada system NACEX (przez serwisy śledzące – nawet co 15 minut). Po dwóch dniach bez nowości – albo po 12 godzinach na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika” – aplikacja odpytuje co godzinę. Powiadomienia ustawisz na „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek tłumaczy etapy na polski, pokazuje przewidywaną datę dostawy w drodze i zaznacza skany na mapie. Ponieważ NACEX podaje godzinę tylko dla samego doręczenia, przy wcześniejszych krokach widoczna jest data. Peek pomija dane odbiorcy, potwierdzenie odbioru, komentarze kurierskie, dane kontaktowe i referencję sklepu, i nie zmienia dostawy ani nie kontaktuje się z NACEX za Ciebie.

## Najczęstsze pytania o śledzenie NACEX

### Czym jest NACEX i czy to wiarygodny kurier?

Tak. Założona w 1995 r. firma NACEX (DRONAS 2002, S.L.U.) należy do grupy Logista i ma ponad 300 oddziałów franczyzowych oraz 35 sortowni w Hiszpanii, Portugalii, Andorze i Holandii/Beneluksie. Oficjalne domeny to `nacex.es`, `nacex.pt` i `nacex.com`, a e-maile kończą się na `@nacex.com`.

### Ile trwa dostawa kurierem NACEX?

W e-commerce na Półwyspie Iberyjskim NACEX podaje 24 godziny (`e-NACEX`, `e-NACEX.shop`) lub 48–72 godziny (`e-NACEX economy`). `NACEX 19:00 H` doręcza przed 19:00 następnego dnia roboczego w kontynentalnej Hiszpanii, Andorze, Portugalii i na Balearach, albo przed 13:00 drugiego dnia roboczego powyżej 50 km od oddziału. Lotniczo Wyspy Kanaryjskie zajmują 24–48 godzin, Azory i Madera 2–5 dni, a w `Ecommerce Europa` czas zależy od kraju docelowego.

### Czy mogę zmienić adres doręczenia paczki NACEX?

Zmiana adresu domowego wymaga zgody płatnika przesyłki (zwykle sklepu): w rejonie tego samego oddziału NACEX jest bezpłatna, a między oddziałami płatna. Gdy przesyłka ma *Pre-Alerta*, link *Mi Entrega Preferida* pozwala samodzielnie przekierować pojedynczą paczkę do punktu NACEX.shop lub oddziału NACEX.

### Dlaczego mój 12-cyfrowy numer NACEX nie działa?

Wzór numeru NACEX ma ukośnik po czwartej cyfrze (`0100/00000005`). Bez `/` Peek sprawdza przewoźników z 12 cyframi ciągiem – wstaw `/` między czwartą a piątą cyfrą.

:::sources
- [NACEX: Strona główna](https://www.nacex.es/irHome.do) – wyszukiwarka, Logista, sieć, języki
- [NACEX: Seguimiento](https://www.nacex.es/irSeguimiento.do) – agencja i przesyłka, numer alternatywny
- [NACEX: Formulario de Seguimiento](https://www.nacex.es/seguimientoFormularioExterno.do?intcli=0000/00000000) – brak przesyłki
- [NACEX: FAQs](https://www.nacex.es/irFaqs.do) – kontakt, 15 dni, upoważnienie, adres, odmowa
- [NACEX: FAQ doręczenia](https://www.nacex.es/visualizarfaq.do?category=900) – awizo, odbiór w oddziale
- [NACEX: FAQ rodzaje usług](https://www.nacex.es/visualizarfaq.do?category=901) – sobota 9–13, Pre-Alerta
- [NACEX: Contacto](https://www.nacex.es/irContactenosContacto.do) – telefon, e-mail, czat
- [NACEX: Reclamaciones](https://www.nacex.es/irReclamaciones.do) – 31 i 7 dni, 48 godzin roboczych
- [NACEX: Servicios internacionales](https://www.nacex.es/irServicioNacional.do?seccion=internacionales) – Europa, 40 kg / 20 kg
- [NACEX: Servicios eCommerce](https://www.nacex.es/irServicioNacional.do?seccion=ecommerce) – 24 h, 48/72 h
- [NACEX: Servicios urgentes](https://www.nacex.es/irServicioNacional.do?seccion=urgentes) – 19:00 H, Kanary
- [NACEX: Servicios Portugal](https://www.nacex.es/irServicioNacional.do?seccion=portugal) – Azory, Madera
- [NACEX: Mi Entrega Preferida](https://www.nacex.es/irServicioNacional.do?seccion=mep) – Pre-Alerta, automaty
- [NACEX: Servicios de valor añadido](https://www.nacex.es/irServicioValor.do) – Entrega con PIN
- [NACEX: NACEX.shop](https://www.nacex.es/irEnvioSostenible.do) – od 09:00 / 12:00
- [NACEX: Localizador de puntos](https://www.nacex.es/irCerca.do) – punkty i oddziały
- [NACEX Blog: NACEX.shop](https://blog.nacex.es/2025/las-entregas-en-puntos-nacex-shop-se-duplican-en-el-ultimo-ano/) – ponad 4000 punktów
- [NACEX: ¿Quiénes somos?](https://www.nacex.es/irEmpresaQuienes.do) – 1995, Logista, Holandia
- [NACEX: Tramitaciones aduaneras](https://www.nacex.es/irTramitacionesAduaneras.do?seccion=tramaduan) – odprawa celna
- [NACEX: Condiciones de transporte 2026](https://www.nacex.es/pages/img/servicios/condiciones_tramitaciones/condiciones-generales-2026-ES.pdf) – druga próba, 50 km, 15 km
- [BOE: Ley 15/2009](https://www.boe.es/buscar/act.php?id=BOE-A-2009-18004) – 7 dni (art. 60)
- [CNMC: Numery 800 i 900](https://blog.cnmc.es/2019/04/24/llamadas-a-numeros-800-y-900-gratis-para-el-consumidor-y-quien-paga/) – darmowe 900
- [Your Europe: Dostawa](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_pl.htm) – 30 dni, sprzedawca
- [Your Europe: VAT](https://europa.eu/youreurope/citizens/consumers/shopping/vat/index_pl.htm) – obszar VAT UE
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – co odczytuje Peek
:::
