---
title: Śledzenie przesyłek SEUR: numer, statusy i punkty Pickup
description: Śledź paczkę SEUR z Hiszpanii do Polski: formaty numerów, znaczenie hiszpańskich statusów, punkty Pickup, doręczenie w sieci Geopost i kontakt.
slug: sledzenie-seur
published: 2026-11-17
updated: 2026-11-17
---

SEUR to hiszpańska firma kurierska z grupy Geopost — tej samej, do której należą [DPD Polska](carrier:dpd-pl), [DPD Szwajcaria](carrier:dpd-ch), [Chronopost](carrier:chronopost) i [BRT](carrier:brt). Paczkę sprawdzisz bez konta w miSEUR na seur.com, przez link SEUR albo wklejając numer powyżej.

Przy zamówieniach z Hiszpanii do Polski SEUR odbiera przesyłkę w Hiszpanii (z komunikatami po hiszpańsku).

## Numer śledzenia SEUR: format i gdzie go znaleźć

Numery SEUR mają zwykle 14 lub 21 cyfr, ale działa pięć formatów (wszystkie przykłady są zmyślone):

| Przykład (zmyślony) | Format |
| --- | --- |
| `48291057364928` | 14 cyfr — standardowy format w sieci Geopost |
| `482910573649281736492` | 21 cyfr |
| `4829105` | 7 cyfr — krótki numer nadawany przez SEUR ponownie |
| `2026150848291057364928` | 22 cyfry z datą na początku (rok, dzień, miesiąc): referencja nadawcy |
| `REC482910573649` | `REC` i 12 cyfr: referencja odbioru |

Szukaj go w e-mailu o wysyłce lub w zamówieniu; w razie braku zobacz [jak znaleźć numer przesyłki](guide:find-tracking-number). Czternaście cyfr pasuje między innymi też do [DPD Polska](carrier:dpd-pl), [DPD Szwajcaria](carrier:dpd-ch), [DPD Niemcy](carrier:dpd-de), [BRT](carrier:brt), [Chronopost](carrier:chronopost) czy Hermes (a 22 cyfry do USPS, Austrian Post lub Estafeta), więc sprawdź przewoźnika w wiadomości ze sklepu.

## Statusy śledzenia SEUR: co oznaczają po hiszpańsku

SEUR zapisuje opisy skanów wielkimi literami po hiszpańsku:

| Status w SEUR | Znaczenie dla ciebie |
| --- | --- |
| `EL ENVÍO HA SIDO REGISTRADO.` | Nadawca zgłosił przesyłkę; SEUR nie ma jeszcze paczki. |
| `LA RECOGIDA HA SIDO REGISTRADA.` | Zamówiono odbiór u nadawcy; paczka nie jest jeszcze w SEUR. |
| `LA RECOGIDA HA SIDO CONFIRMADA POR EL REPATIDOR.` | Kurier ma odbiór na trasie (*REPATIDOR* to pisownia SEUR). |
| `RECOGIDA NO REALIZADA: CLIENTE CERRADO O AUSENTE. SE PUEDE REPROGRAMAR UNA NUEVA FECHA DE RECOGIDA.` | Nieudany odbiór u nadawcy, nie u ciebie: Peek pokazuje „Zarejestrowana”. |
| `LA RECOGIDA HA SIDO REALIZADA.` | Kurier odebrał paczkę od nadawcy — ma ją SEUR. |
| `EL ENVÍO HA SIDO DEPOSITADO EN UN PUNTO SEUR PICKUP.` | Nadawca zostawił paczkę w punkcie SEUR Pickup. |
| `EL ENVÍO HA SIDO RECEPCIONADO EN LAS INSTALACIONES DE ORIGEN DE SEUR. COMIENZA EL TRÁNSITO A DESTINO` | Przyjęta w oddziale nadawczym SEUR; rusza w trasę. |
| `EL ENVÍO ESTÁ EN TRÁNSITO. PRÓXIMAMENTE LLEGARÁ A LAS INSTALACIONES DE DESTINO.` | W transporcie do oddziału docelowego. |
| `EN ENVÍO HA SIDO RECEPCIONADO EN LAS INSTALACIONES DE DESTINO. PRONTO SALDRÁ A REPARTO.` | W oddziale docelowym (*EN ENVÍO* to pisownia SEUR), przed wyjazdem kuriera: Peek pokazuje „W transporcie”. |
| `EL ENVÍO HA LLEGADO A LA ADUANA DE DESTINO. INICIAMOS LOS TRÁMITES DE DESPACHO ADUANERO.` | W urzędzie celnym kraju docelowego. |
| `INCIDENCIA EN ADUANAS: FALTA FACTURA. CONTACTA CON SEUR DESDE EL ÁREA DE SOPORTE.` | Brak faktury w odprawie: weź ją ze sklepu i zgłoś przez miSEUR. |
| `LA MERCANCÍA HA SIDO DESPACHADA Y PRÓXIMAMENTE SALDRÁ A REPARTO.` | Po odprawie celnej; wkrótce wyjedzie do doręczenia. |
| `EL ENVÍO SERÁ ENTREGADO EN LA FECHA CONCERTADA. ACTUALIZAREMOS LA SITUACIÓN.` | Ustalono uzgodniony dzień doręczenia. |
| `EL ENVÍO HA SUFRIDO UN RETRASO Y ES POSIBLE QUE SE DEMORE LA ENTREGA. DISCULPA LAS MOLESTIAS.` | Opóźnienie bez podania przyczyny ani nowego terminu. |
| `EL ENVÍO ESTÁ EN REPARTO.` | Dziś w doręczeniu (`INTERNACIONAL` przy przesyłce międzynarodowej). |
| `EL ENVÍO NO SE HA ENTREGADO POR AUSENCIA O CIERRE. SE PUEDE REPROGRAMAR UNA NUEVA ENTREGA.` | Nikogo nie zastano lub zamknięte; można przełożyć dostawę. |
| `EL ENVÍO NO SE HA ENTREGADO PORQUE LA DIRECCIÓN DE ENTREGA ES INCORRECTA.` | Błędny adres (lub `INCOMPLETA O ERRÓNEA`): Peek pokazuje „Próba doręczenia”. |
| `EL ENVÍO NO SE HA ENTREGADO PORQUE NO ES LA DIRECCIÓN ACTUAL DEL DESTINATARIO.` | Nieaktualny adres odbiorcy: Peek pokazuje „Zgłoszono problem”. |
| `NO SE HA PODIDO DEPOSITAR EL ENVÍO EN EL PUNTO SOLICITADO` | Wybrany punkt Pickup nie mógł przyjąć paczki. |
| `EL ENVÍO ESTÁ DISPONIBLE PARA RECOGER EN EL PUNTO SEUR PICKUP.` | Gotowa do odbioru w punkcie SEUR Pickup. |
| `EL ENVÍO ESTÁ ENTREGADO.` | Doręczona (`ENTREGADO A UN VECINO.` przy odbiorze przez sąsiada). |

Po odbiorze w hiszpańskim sklepie Pickup pojawia się `EL DESTINATARIO HA RETIRADO EL ENVÍO DE LA TIENDA SEUR PICKUP SELECCIONADA.`

## Doręczenie w Hiszpanii i w Polsce oraz urząd celny

**W Hiszpanii.** SEUR ma tysiące sklepów i automatów Pickup na seur.com. Przekierowanie do punktu daje status `LOS DATOS DEL ENVÍO HAN SIDO MODIFICADOS Y SE ENTREGARÁ EN UN PUNTO SEUR PICKUP.`, a brak miejsca w automacie — `EL ENVÍO NO HA SIDO DEPOSITADO EN EL LOCKER SELECCIONADO. SE DEBE ELEGIR OTRO PUNTO SEUR PICKUP.`

**Do Polski.** W usłudze SEUR Classic serwis Packlink podaje orientacyjnie 2–4 dni do głównych miast europejskich (bez gwarantowanego okna godzinowego). Jeśli w Polsce paczkę doręcza [DPD Polska](carrier:dpd-pl), dyspozycje wydasz w aplikacji DPD Mobile lub na mojapaczka.dpd.com.pl, a w punkcie lub automacie DPD Pickup przesyłka czeka 2 pełne dni robocze.

**Urząd celny.** Między kontynentalną Hiszpanią (lub Balearami) a Polską nie ma odprawy celnej. Odprawa dotyczy natomiast Wysp Kanaryjskich (poza obszarem VAT UE), Ceuty i Melilli (poza obszarem celnym i VAT UE) oraz przesyłek spoza UE: od 1 lipca 2026 r. w UE obowiązuje tymczasowe cło 3 € za artykuł dla paczek spoza UE do 150 €, a od 1 listopada 2026 r. unijna opłata manipulacyjna 2 € za każdy odrębny towar (możliwa do przeniesienia na kupującego); obie kwoty dotyczą przesyłek spoza obszaru celnego UE, także z Ceuty i Melilli, ale nie z Wysp Kanaryjskich. Zobacz [przesyłka w urzędzie celnym](guide:customs).

## Paczka nieznaleziona, opóźniona lub oznaczona jako doręczona

**Brak w systemie.** SEUR zwraca ten sam błąd dla świeżo utworzonej etykiety i dla nieznanego numeru — po dniu roboczym dopytaj sprzedawcę o nadanie.

**Opóźnienie lub brak paczki.** Po terminie zajrzyj do [śledzenie nie aktualizuje się](guide:tracking-not-updating). Według Your Europe przy zakupach w UE sprzedawca odpowiada za towar aż do chwili odbioru. Przy `ENTREGADO A UN VECINO.` zapytaj sąsiadów, inaczej sprawdź [paczka doręczona, ale nieodebrana](guide:delivered-not-received).

## Obsługa klienta SEUR i kontakt w Polsce

Z SEUR skontaktujesz się w sekcji wsparcia miSEUR na seur.com. W Polsce dla sieci [DPD Polska](carrier:dpd-pl):

- **Infolinia DPD Polska:** `801 400 373` lub `22 577-55-55` (pon.–pt. 9:00–17:00, sob. 8:00–14:00) na [stronie kontaktu DPD Polska](https://www.dpd.com/pl/pl/o-dpd/kontakt-2/dzial-obslugi-klienta/).
- **Pomoc techniczna automatów DPD Pickup:** `22 571 52 52` (w dni robocze).

> W lutym 2021 r. hiszpański instytut INCIBE ostrzegł przed fałszywymi SMS-ami w imieniu SEUR żądającymi drobnej dopłaty w celu kradzieży danych karty: nie klikaj linku, wpisz adres SEUR ręcznie w przeglądarce i pilnie skontaktuj się z bankiem, jeśli podałeś dane karty. DPD Polska ostrzega też przed linkami spoza `dpd.com.pl` i skróconymi URL-ami.

## Śledzenie paczek SEUR w Peek

[Peek](/) pyta bezpośrednio SEUR o numery 21-cyfrowe i referencje `REC` (poza kodami z `000` na pozycjach 13–15 przypisywanymi do Australia Post: kliknij „Zmień” i wybierz SEUR pod „Pasują do numeru”). Przy 22 cyfrach pyta SEUR i Austrian Post przed serwisami śledzenia i USPS. Dla 14 cyfr w Polsce sprawdza najpierw Hermes, DPD Belgium, [DPD Szwajcaria](carrier:dpd-ch), DPD Holandia i [DPD Niemcy](carrier:dpd-de), a potem [Chronopost](carrier:chronopost), SEUR, [BRT](carrier:brt), DPD UK i DPD Portugalia. Po „Sprawdzanie usług śledzenia…” zobaczysz „SEUR ma tę przesyłkę.”, jeśli odpowie tylko SEUR; inaczej wybierz SEUR przez „Zmień” (lub „Zmień przewoźnika”) — tak samo dla 7 cyfr, które SEUR nadaje ponownie. Linki z `tracking=` działają bez kodu pocztowego.

Peek pokazuje status, historię i wagę, bez mapy z braku lokalizacji. Opisy pozostają po hiszpańsku poza doręczeniem (`Delivered`, „Dostarczona”); opóźnienia nie zmieniają etapu. Peek pomija liczbę paczek, nadawcę, odbiorcę, PIN i kod QR, czyta czas madrycki („Godziny w twojej strefie czasowej: …”), a wielopaki śledzi przez zewnętrzne serwisy.

Peek sprawdza status do co 10 minut, gdy masz paczkę otwartą albo w dniu doręczenia masz włączone powiadomienia, a w pozostałych przypadkach co godzinę (także po dwóch dniach bez zmian lub 12 godzinach na „Zarejestrowana” bądź „Przyjęta przez przewoźnika”). Przy „W doręczeniu” od 6:00 czasu madryckiego sprawdza do co 2 minut, dopóki odpowiada sam SEUR; przez zewnętrzne serwisy co godzinę, po dobie bez skanu co 3 godziny, a w dniu doręczenia do co 15 minut, gdy masz paczkę otwartą albo włączone powiadomienia („Każdy skan”, „Ważne etapy”, „Tylko doręczenie”).

## Najczęstsze pytania o śledzenie SEUR

### Dlaczego wyszukiwarka SEUR nie widzi mojego numeru?

SEUR daje taką samą odpowiedź dla świeżej wysyłki i dla nieznanego numeru; jeśli stan się utrzyma, spytaj sprzedawcę o nadanie.

### Ile idzie paczka SEUR z Hiszpanii?

Według Packlink: 24 godziny robocze w Hiszpanii kontynentalnej (SEUR 24), 48–72 godziny na Wyspy Kanaryjskie (72–96 na La Gomerę i El Hierro) oraz 2–4 dni do głównych miast Europy (SEUR Classic).

### Co oznacza komunikat SEUR o opóźnieniu?

Zapowiada możliwe przesunięcie dostawy bez przyczyny ani nowej daty; Peek zachowuje dotychczasowy etap.

### Czy SEUR i DPD należą do jednej grupy?

Tak — SEUR to hiszpańska marka grupy Geopost, obok [DPD Polska](carrier:dpd-pl), [DPD Szwajcaria](carrier:dpd-ch), [Chronopost](carrier:chronopost) i [BRT](carrier:brt).

:::sources
- [Geopost: Who we are](https://www.geopost.com/en/about-us/who-we-are/) – grupa Geopost
- [Geopost: SEUR](https://www.geopost.com/en/about-us/our-companies-and-partners/seur/) – sieć Pickup
- [Packlink: SEUR](https://www.packlink.es/seur/) – czasy przewozu
- [Your Europe: Wysyłka i dostawa](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_pl.htm) – odpowiedzialność sprzedawcy
- [INCIBE: Fałszywe SMS-y SEUR](https://www.incibe.es/ciudadania/avisos/circulan-sms-fraudulentos-que-suplantan-seur-que-no-te-enganen) – ostrzeżenie phishingowe
- [DPD Polska: O firmie](https://www.dpd.com/pl/pl/o-dpd/o-dpd-polska/) – Geopost w Polsce
- [DPD Polska: FAQ](https://www.dpd.com/pl/pl/najczesciej-zadawane-pytania-faq/) – 2 dni robocze i 22 571 52 52
- [DPD Polska: Obsługa klienta](https://www.dpd.com/pl/pl/o-dpd/kontakt-2/dzial-obslugi-klienta/) – infolinia i godziny
- [DPD Polska: Fałszywe wiadomości](https://www.dpd.com/pl/pl/o-dpd/falszywe-maile/) – domena dpd.com.pl
- [Komisja Europejska: Territorial Scope](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Wyspy Kanaryjskie, Ceuta, Melilla
- [Komisja Europejska: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 3 € i 2 €
- [Revenue: Union Handling Fee](https://www.revenue.ie/en/corporate/press-office/press-releases/2026/pr-092926-handling-fee.aspx) – opłata 2 €
- [Universal Parcel Scraper: SEUR](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/seur) – co odczytuje Peek
:::
