---
title: Śledzenie TIPSA: 22-cyfrowy numer, statusy i doręczenia
description: Śledź paczkę TIPSA w Hiszpanii lub Portugalii: 22-cyfrowy numer, znaczenie hiszpańskich statusów, nieudane doręczenia, oddziały i kontakt.
slug: sledzenie-tipsa
published: 2026-11-21
updated: 2026-11-21
---

TIPSA to hiszpańska firma kurierska należąca do grupy Geopost, z oddziałami w Hiszpanii, Portugalii i Andorze. Śledź paczkę za pomocą 22-cyfrowego numeru w [lokalizatorze przesyłek TIPSA](https://www.tip-sa.com/en/delivery-locator) albo wklej go w pole powyżej. Numer podaje sklep lub nadawca, na przykład w e-mailu albo SMS-ie o wysyłce. Jeśli go nie masz, zapytaj sklep lub oddział TIPSA obsługujący twój obszar – znajdziesz go po kodzie pocztowym w [wyszukiwarce oddziałów](https://www.tip-sa.com/es/agencias-de-transporte). Z tym samym oddziałem uzgodnisz doręczenie po statusie `Ausente` albo odbiór własny; w razie zaginięcia lub uszkodzenia powiadom sklep.

## Numer śledzenia TIPSA: jak wygląda

Lokalizator TIPSA wymaga 22-cyfrowego numeru przesyłki i oznacza jako wymagany również kod pocztowy odbiorcy. Numer składa się z dwóch kodów oddziałów oraz listu przewozowego (`albarán`):

:::anatomy 028145 028145 4829105736
- 028145 | Pierwszy kod oddziału: oddział TIPSA obsługujący przesyłkę
- 028145 | Drugi kod oddziału: kolejny oddział na trasie przesyłki
- 4829105736 | List przewozowy (albarán): właściwy numer przesyłki
:::

| Kształt (zmyślony) | Co to jest |
| --- | --- |
| `0281450281454829105736` | Pełny 22-cyfrowy numer TIPSA. Tego użyj. |
| `028145 028145 4829105736` | Ten sam numer ze spacjami. Peek go odczytuje; pole lokalizatora TIPSA mieści tylko 22 znaki, więc tam wpisz go bez spacji. |
| `4829105736` | Sam 10-cyfrowy list przewozowy. Ani lokalizator TIPSA, ani Peek nie traktują go jako numeru TIPSA. |

Sklepy mogą zlecić TIPSA wysyłanie e-maili i SMS-ów przy nadaniu, dotarciu do oddziału, incydencie i po doręczeniu, z linkami do strony przesyłki. Bez 22-cyfrowego numeru lokalizator wyszukuje też po referencji, wymaga jednak identyfikatora (`Agency + Client`), który musisz uzyskać od nadawcy. Więcej wskazówek znajdziesz w [jak znaleźć numer przesyłki](guide:find-tracking-number).

Dwadzieścia dwie cyfry pasują również do Austrian Post, [USPS](carrier:usps) i Estafety. Numer zaczynający się od `00`, którego drugi kod oddziału także zaczyna się od `00`, może należeć do [CTT Express](carrier:ctt-express), innego hiszpańskiego przewoźnika: sprawdź, kogo wskazuje e-mail sklepu. Działa wyłącznie pełna postać 22-cyfrowa, nie same ostatnie 10 cyfr.

## Co oznaczają statusy śledzenia TIPSA

Strona szczegółów przesyłki TIPSA jest dostępna wyłącznie po hiszpańsku, nawet jeśli otworzysz lokalizator z angielskiej wersji serwisu. U góry pokazuje skróconą oś (`Documentado`, `En camino`, `En reparto`, `Entregado`), a niżej tabelę ze wszystkimi skanami, datą, godziną i miejscowością. Wpisy:

| Status TIPSA | Co to oznacza dla ciebie |
| --- | --- |
| `PENDIENTE DE ENTREGAR A TIPSA` | Sklep zarejestrował przesyłkę, ale TIPSA nie ma jeszcze paczki: to status [utworzonej etykiety](guide:tracking-statuses). |
| `TRANSITO` | W drodze między oddziałami i hubami TIPSA. |
| `LECTURA EN HUB MADRID` | Zeskanowana w hubie TIPSA; nazwa zmienia się zależnie od lokalizacji (`LECTURA EN HUB BARCELONA`, `LECTURA EN HUB BARBERA`). |
| `Pendiente de llegada` | Nie dotarła jeszcze do oddziału doręczającego. |
| `LEIDO EN DESTINO` | Zeskanowana w miejscu przeznaczenia. |
| `LECTURA EN AGENCIA DESTINO` | Zeskanowana w oddziale doręczającym, wraz z jego nazwą. |
| `REPARTO` | W doręczeniu. |
| `Ausente` | Kurier nie zastał odbiorcy i nie mógł doręczyć przesyłki. |
| `Faltan Datos / Datos incorrectos` | Brakujące lub błędne dane adresowe, odnotowane podczas trasy przed kolejną próbą. |
| `Cerrado` | Adres był zamknięty podczas próby doręczenia, po wpisie `REPARTO`. |
| `Solicitada en otra dirección` | Zgłoszono doręczenie na inny adres i paczka została przekierowana. |
| `DISPONIBLE` lub `Pendiente de recoger en Tipsa` | Po nieudanym doręczeniu paczka czeka na odbiór w oddziale. |
| `Devolver a Origen` | Zwrot do nadawcy. |
| `ENTREGADO` | Dostarczona. |

Wpisowi `Ausente` towarzyszy komunikat TIPSA: nie udało się skontaktować z odbiorcą, kolejna próba nastąpi następnego dnia roboczego, a doręczenie można uzgodnić telefonicznie z oddziałem docelowym. Nazwa i adres tego oddziału widnieją w sekcji „AGENCIA DESTINO”.

## Nieudane doręczenie, oddziały i punkty odbioru TIPSA

TIPSA doręcza we własnej sieci w kontynentalnej Hiszpanii i Portugalii, na Balearach i Wyspach Kanaryjskich, w Ceucie i Melilli, a za granicę przez DPDgroup. Po statusie `Ausente` druga próba odbywa się następnego dnia roboczego; standardowa taryfa dla sklepów obejmuje dwie próby doręczenia, a usługa Premium dla sklepów internetowych trzy.

**Odbiór w oddziale.** Jeśli będziesz nieobecny albo wolisz odebrać paczkę samodzielnie, według FAQ TIPSA czeka ona w oddziale najbliższym twojemu kodowi pocztowemu przez 15 dni od nadania, a nie od dotarcia na miejsce. Potem wraca do nadawcy. Zabierz dowód (DNI) lub paszport; osoba odbierająca w twoim imieniu musi przedstawić fotokopię twojego dokumentu. [Wyszukiwarka oddziałów](https://www.tip-sa.com/es/agencias-de-transporte) szuka po prowincji lub kodzie pocztowym.

**Zmiana adresu.** Według TIPSA wyłącznie nadawca może zmienić adres lub dane przesyłki – skontaktuj się więc ze sklepem.

**Punkty odbioru i automaty.** TIPSA współdzieli z [SEUR](carrier:seur) sieć punktów Pickup w sklepach oraz całodobowych automatów paczkowych (24/7). Większość punktów jest czynna co najmniej sześć dni w tygodniu, a na odbiór masz 7 dni. Dostawa do punktu zależy od opcji oferowanych przez sklep. Lokalizację sprawdzisz w [wyszukiwarce Pickup](https://aplicaciones.tip-sa.com/puntospickup/).

Część sklepów wykupuje także doręczenie w sobotę lub powiadomienie wstępne (SMS i e-mail informujące o dniu dotarcia paczki). Usługa Premium pozwala również wybrać przedział godzinowy z marginesem jednej godziny; ponieważ TIPSA nie opisuje na stronie, gdzie go ustawić, zapytaj oddział docelowy.

**Płatność przy odbiorze.** Przy przesyłce za pobraniem (`contrarreembolso`) TIPSA pobiera przy doręczeniu kwotę ustaloną przez sklep, do 1000 € na przesyłkę; przy opłacie przy odbiorze (`portes debidos`) pobiera koszt transportu w miejscu doręczenia. TIPSA nie podaje, czy płaci się gotówką, czy kartą – zapytaj sklep lub oddział. Ponieważ obie opłaty reguluje się dopiero przy odbiorze, SMS albo e-mail „od TIPSA” żądający dopłaty przez link nie pasuje do żadnej z nich: nie płać i sprawdź numer w lokalizatorze na tip-sa.com. Zobacz [fałszywe SMS-y o paczkach](guide:delivery-scam-texts).

## Paczka TIPSA opóźniona, zaginiona lub zwrócona

Strony TIPSA nie omawiają przesyłek, których status przestał się zmieniać. Zadzwoń do oddziału docelowego podanego na stronie przesyłki albo zapytaj sklep; poradnik [dlaczego śledzenie nie aktualizuje się](guide:tracking-not-updating) wyjaśnia, kiedy reagować.

W razie zaginięcia lub uszkodzenia FAQ TIPSA jest krótkie: skontaktuj się z nadawcą i poinformuj go o problemie. Umowę z TIPSA ma sklep i to on składa reklamację; TIPSA nie publikuje procedury ani terminów reklamacyjnych dla odbiorców. Brakuje też strony dla paczek oznaczonych jako `ENTREGADO`, które nie dotarły – w takim przypadku również powiadom sklep.

Paczka nieodebrana w ciągu 15 dni od nadania wraca do nadawcy: uzgodnij ze sklepem zwrot pieniędzy lub ponowną wysyłkę.

## Kontakt i obsługa klienta TIPSA

Kanały podane na stronie kontaktowej i w FAQ TIPSA:

- **Telefon:** `902 10 10 47` lub `900 10 10 47` (hiszpańskie FAQ podaje wyłącznie `900 10 10 47`). TIPSA nie podaje godzin pracy ani kosztów połączenia.
- **Formularz kontaktowy** na [tip-sa.com/en/contact](https://www.tip-sa.com/en/contact), z powodem „I have a problem with my shipment” i numerem przesyłki.
- **E-mail:** `info@tip-sa.com`, wskazany w FAQ w razie braku numeru przesyłki.
- **Oddział docelowy**, wymieniony na stronie przesyłki, do uzgodnienia doręczenia.

Dla Portugalii nie ma osobnej infolinii: portugalska strona kontaktowa TIPSA podaje te same dwa numery. TIPSA publikuje też stronę swojej aplikacji mobilnej na [tip-sa.com/en/recursos/app](https://www.tip-sa.com/en/recursos/app).

## Śledzenie przesyłki TIPSA w Peek

[Peek](/) przyjmuje 22-cyfrowy numer ze spacjami lub bez nich. Ponieważ tę samą długość stosują inni przewoźnicy, Peek pyta jednocześnie pasujących przewoźników, zaczynając od TIPSA: zobaczysz „Peek pyta: …”, a gdy TIPSA rozpozna paczkę – „TIPSA ma tę przesyłkę.”. Jeśli oba kody oddziałów zaczynają się od `00`, a drugi pasuje do jednego z kodów oddziałów przypisanych do CTT Express, Peek kieruje numer bezpośrednio do CTT Express; dla pozostałych numerów `00…00…` pyta jednocześnie TIPSA (w pierwszej kolejności), CTT Express i Austrian Post. Jeśli sklep wskazał TIPSA, stuknij „Zmień” i wybierz go ręcznie. Kod pocztowy nie jest potrzebny.

Sam 10-cyfrowy list przewozowy nie daje się śledzić przez TIPSA, ani po „Zmień”, ani z linku. Peek nie podpowiada TIPSA dla 10 cyfr; jeśli wybierzesz TIPSA ręcznie, poinformuje, że TIPSA „nie wydaje takich numerów”, i zapyta usługi śledzenia. Poszukaj pełnych 22 cyfr lub linku w e-mailu albo SMS-ie ze sklepu. Peek odczytuje linki `tip-sa.com` z parametrem `id=` na stronach `datos_env.php`, `datos_prestashop.php`, `datos.php` oraz na głównej stronie `tip-sa.com`, lecz nie linki do lokalizatora ani do `tipsa-dinapaq.com`.

Peek pokazuje status, historię z miejscowością każdego skanu na mapie, nazwę nadawcy (jeśli TIPSA jej nie ukrywa) oraz wagę. Opisy pozostają w hiszpańskim brzmieniu TIPSA, a Peek podsumowuje każdy etap: `PENDIENTE DE ENTREGAR A TIPSA` („Zarejestrowana”), `REPARTO` („W doręczeniu”, podobnie jak skan oddziału tego samego dnia po `REPARTO`), `Ausente`, `Cerrado` i `Faltan Datos / Datos incorrectos` („Próba doręczenia”), `DISPONIBLE` i `Pendiente de recoger en Tipsa` („Do odbioru”), `Devolver a Origen` („Zgłoszono problem”), `ENTREGADO` („Dostarczona”), a pozostałe skany („W transporcie”). Wszystkie godziny Peek odczytuje według czasu madryckiego – łącznie z oddziałami w Portugalii – i przelicza na twoją strefę czasową; na peektracker.com oraz w aplikacjach na iPhone’a i Androida pod historią widnieje wiersz „Godziny w twojej strefie czasowej:” z jej nazwą. Peek pomija dane odbiorcy, adres, kody pocztowe, referencję, liczbę paczek, objętość i potwierdzenie odbioru, nie podaje przewidywanej daty doręczenia i nie śledzi przekazania innemu przewoźnikowi.

Dopóki masz przesyłkę otwartą na ekranie albo w dniu doręczenia przy włączonych powiadomieniach, a śledzenie TIPSA odpowiada, Peek sprawdza ją nawet co 10 minut, a od godz. 6:00 czasu madryckiego w dniu skanu `REPARTO` nawet co 2 minuty; w pozostałym czasie co godzinę. Gdy odpowiadają tylko usługi śledzenia, sprawdza co godzinę, co 3 godziny, gdy ostatni skan ma już dzień, a w dniu doręczenia nawet co 15 minut, dopóki masz ją otwartą lub włączone powiadomienia. Po dwóch dniach bez nowości (po 12 godzinach na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika”) Peek sprawdza przesyłkę co godzinę. Możesz włączyć powiadomienia „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”.

## Pytania o śledzenie TIPSA

### Czym jest TIPSA w Hiszpanii?

TIPSA to skrót od Transporte Integral de Paquetería, S.A., hiszpańskiego przewoźnika ekspresowego z grupy Geopost. Strona o firmie podaje ponad 330 oddziałów (portugalska strona główna ponad 300) oraz 13 hubów, w tym w Madrycie, Barcelonie i Walencji; przesyłki zagraniczne realizuje poprzez DPDgroup.

### Ile trwa doręczenie przez TIPSA?

Zależy to od usługi wykupionej przez sklep. TIPSA oferuje sklepom doręczenie w 24 godziny (jedna próba w następnym dniu roboczym) albo w 48 godzin; w Portugalii 24 godziny do głównych miejscowości i 24–48 godzin poza nimi.

### Czy mogę śledzić paczkę TIPSA za pomocą 10 cyfr?

Nie: lokalizator TIPSA wymaga 22-cyfrowego numeru, a Peek również nie przyjmuje 10 cyfr jako numeru TIPSA. Te 10 cyfr to wyłącznie końcowy list przewozowy – poproś nadawcę o pełny numer.

### Czy TIPSA ma punkty odbioru?

Tak, punkty Pickup w sklepach i automatach, współdzielone z SEUR, gdzie paczka czeka 7 dni. Z tej opcji skorzystasz tylko wtedy, gdy sklep oferuje dostawę do punktu.

:::sources
- [TIPSA: Delivery locator](https://www.tip-sa.com/en/delivery-locator) – 22-cyfrowy numer, formularz, wyszukiwanie po referencji z identyfikatorem (oddział + klient)
- [TIPSA: Frequently Asked Questions](https://www.tip-sa.com/en/recursos/faqs) – brakujący numer, 15 dni przechowywania, dokument tożsamości, zmiana adresu, zaginięcie lub uszkodzenie, telefony, e-mail
- [TIPSA: Contact](https://www.tip-sa.com/en/contact) – telefony, powody w formularzu
- [TIPSA: Collection points](https://www.tip-sa.com/en/services/collection-points) – sieć Pickup
- [TIPSA: Pre-alert](https://www.tip-sa.com/en/services/pre-alert) – powiadomienia SMS i e-mail, czasy doręczenia w Portugalii
- [TIPSA: TIPSA APP](https://www.tip-sa.com/en/recursos/app) – strona aplikacji
- [TIPSA: Consulta de Envíos](https://dinapaqweb.tipsa-dinapaq.com/dinapaqweb/detalle_envio.php) – strona przesyłki tylko po hiszpańsku, opisy statusów i oddział docelowy
- [TIPSA: Localizar mi envío](https://www.tip-sa.com/es/localizacion-envios) – hiszpański lokalizator, telefon w stopce
- [TIPSA: Preguntas frecuentes](https://www.tip-sa.com/es/recursos/faqs) – zasięg, numer `900 10 10 47`
- [TIPSA: Empresa de transporte urgente](https://www.tip-sa.com/es/tipsa) – Geopost, ponad 330 oddziałów, 13 hubów, automaty
- [TIPSA: Página inicial (Portugal)](https://www.tip-sa.com/pt) – ponad 300 oddziałów
- [TIPSA: FAQs eTIPSA](https://www.tip-sa.com/es/faqs-etipsa) – dwie próby, 24 i 48 godzin, okno czasowe eTIPSA Premium, soboty, powiadomienia sklepów
- [TIPSA: 360º eCommerce](https://www.tip-sa.com/es/envios-de-paquetes/360o-ecommerce) – trzy próby w Premium
- [TIPSA: Recogida en puntos de recogida](https://www.tip-sa.com/es/envios-de-paquetes/puntos-de-recogidas) – wspólna sieć z SEUR, 7 dni, dni otwarcia
- [TIPSA: Agencias de transporte](https://www.tip-sa.com/es/agencias-de-transporte) – wyszukiwarka oddziałów
- [TIPSA: Puntos Pickup](https://aplicaciones.tip-sa.com/puntospickup/) – wyszukiwarka punktów Pickup
- [TIPSA: Contrarreembolso](https://www.tip-sa.com/es/envios-de-paquetes/reembolso) – płatność przy odbiorze, do 1000 € za przesyłkę
- [TIPSA: Portes debidos](https://www.tip-sa.com/es/envios-de-paquetes/portes-debidos) – koszt transportu pobierany w miejscu doręczenia
- [TIPSA: Aviso legal](https://www.tip-sa.com/es/tipsa-aviso-legal) – nazwa spółki
- [TIPSA: Contacto (Portugal)](https://www.tip-sa.com/pt/contacto) – te same numery dla Portugalii
- [Universal Parcel Scraper: TIPSA](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/tipsa) – 22-cyfrowy format, obsługiwane linki, czas madrycki, mapowanie statusów (`Cerrado`, `Devolver a Origen`, skan oddziału po `REPARTO`), pola pokazywane i pomijane
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – nakładanie się z CTT Express, Austrian Post, USPS i Estafetą
:::
