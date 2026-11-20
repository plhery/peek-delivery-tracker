---
title: Śledzenie CTT Express: statusy po polsku, punkty i kontakt
description: Śledź paczkę CTT Express w Hiszpanii lub Portugalii: numery 22- i 25-cyfrowe, hiszpańskie statusy po polsku, punkty Collectt, awizo i obsługa klienta.
slug: sledzenie-ctt-express
published: 2026-11-17
updated: 2026-11-20
---

CTT Express to hiszpańska firma kurierska należąca do portugalskiej grupy CTT (dawniej Tourline Express, przemianowana w 2019 roku), która doręcza przesyłki w całej Hiszpanii — na półwyspie, Balearach, Wyspach Kanaryjskich, w Ceucie i Melilli — oraz w Portugalii. Numer przesyłki sprawdzisz w wyszukiwarce [localizador](https://www.cttexpress.com/localizador-de-envios/) na stronie `cttexpress.com` albo wklejając go w polu powyżej. Kod przekazuje sklep wysyłający zamówienie, a CTT Express wysyła go również SMS-em lub e-mailem. Nie myl tej spółki z [CTT](carrier:ctt), narodową pocztą Portugalii: numer z literami zakończony na `PT` należy do sieci [CTT](carrier:ctt).

## Jak wygląda numer śledzenia CTT Express

Wyszukiwarka CTT Express prosi o *código de envío* (numer przesyłki) — zazwyczaj długi ciąg cyfr podany w SMS-ie lub e-mailu z potwierdzeniem nadania. Katalog Peek rozpoznaje następujące formaty (przykłady zmyślone):

| Przykładowy numer | Do kogo należy |
| --- | --- |
| `0012340012345678901234` | 22 cyfry: `00`, cztery cyfry, `00`, a następnie 14 cyfr (kody oddziałów na pozycjach 1–2 oraz 7–8 zaczynają się od `00`). CTT Express. |
| `0012340012345678901234001` | 25 cyfr: te same 22 cyfry oraz trzycyfrowy licznik paczki (`001` dla pierwszej lub jedynej paczki zgodnie ze specyfikacją etykiet CTT Express). CTT Express. |
| `0000100012345678901234` | 22 cyfry zaczynające się od `00001000`: format współdzielony przez CTT Express, TIPSA, Austrian Post i innych przewoźników. |
| `RR482910576PT` | Dwie litery, dziewięć cyfr i `PT`: numer pocztowy lub kurierski CTT Expresso w sieci [CTT](carrier:ctt), śledzony na `ctt.pt`. |

**CTT, CTT Expresso czy CTT Express?** Jedna grupa kapitałowa, ale dwie osobne sieci: [CTT](carrier:ctt) (*Correios de Portugal*) odpowiada za powszechną usługę pocztową w Portugalii i prowadzi tamtejszą spółkę kurierską CTT Expresso używającą kodów literowo-cyfrowych zakończonych na `PT` (`DA`, `EA` itd.), natomiast CTT Express obsługuje hiszpańską sieć ekspresową (`CTT 24h`, `CTT 48h`, `CTT e24h`, `CTT 10h` oraz `CTT 14h`). W maju 2026 roku CTT Expresso i DHL eCommerce zawarły dodatkowo strategiczną spółkę joint venture na Półwyspie Iberyjskim (wzajemne udziały po 25 %, bez fuzji): w Hiszpanii CTT Express realizuje doręczenia B2C pod szyldem *CTT Express together with DHL*, a DHL eCommerce odpowiada za segment B2B i przesyłki międzynarodowe, zaś w Portugalii CTT Expresso integruje działalność DHL eCommerce. Obie wyszukiwarki częściowo się jednak pokrywają: portugalska wersja językowa na `cttexpress.com` przekierowuje do narzędzia „Seguir objeto” na `ctt.pt`, które odczytuje również kody 22- i 25-cyfrowe, a sama strona śledzenia CTT Express podaje kod `PT` jako jeden z przykładów. Jeśli jedna witryna nic nie znajduje, sprawdź drugą.

Nie masz jeszcze żadnego numeru? Poproś o niego sprzedawcę lub sprawdź, [gdzie znaleźć numer przesyłki](guide:find-tracking-number).

## Statusy śledzenia CTT Express przetłumaczone na polski

CTT Express nie udostępnia śledzenia po polsku ani po angielsku: nawet po zmianie języka na `cttexpress.com` panel śledzenia (`shipping-tracking.production.cloud2.cttexpress.com`) działa wyłącznie po hiszpańsku. Na górze widnieją cztery etapy (`Grabado`, `Enviado`, `En reparto` oraz `Entrega prevista` z datą lub `Sin determinar`), a poniżej szczegółowe opisy poszczególnych skanów.

| Status po hiszpańsku | Co oznacza w praktyce |
| --- | --- |
| `Pendiente de recepción en CTT Express` | Nadawca zarejestrował przesyłkę elektronicznie, ale fizyczna paczka nie trafiła jeszcze do sieci CTT Express. |
| `Recogido` | Odebrana od nadawcy (`Recogida fallida`, jeśli próba odbioru się nie powiodła). |
| `Enviado` lub `En tránsito` | W drodze między oddziałami i sortowniami (`En clasificación` podczas sortowania, `Aceptado por la última milla` po przyjęciu w lokalnym oddziale doręczającym). |
| `En tránsito internacional. Pendiente de recepción en CTT Express` | W transporcie z zagranicy; CTT Express nie przyjęło jeszcze paczki w Hiszpanii ani w Portugalii. |
| `En reparto` lub `Entrega hoy` | Przypisana do trasy kuriera i wydana dziś do doręczenia. |
| `Incidencia en el reparto` lub `Reparto fallido` | Przeszkoda wstrzymała doręczenie; dokładny powód widnieje wiersz niżej. |
| `Recogerán en Delegación` | „Odbiór w oddziale”: przy paczce doręczanej w Portugalii przez [CTT](carrier:ctt) próba doręczenia nie powiodła się i przesyłka czeka na odbiór. Śledzenie CTT pokazuje w tej samej chwili nieudaną próbę, a chwilę później gotowość do odbioru. |
| `Almacenado temporalmente` | Tymczasowo złożona w magazynie oddziału po nieudanej próbie — do kolejnego wyjazdu kuriera lub zwrotu. |
| `Nuevo reparto` | Ponownie wydana kurierowi na kolejną próbę doręczenia. |
| `Disponible en Punto Collectt Express` | Gotowa do odbioru w punkcie Collectt Express; kod PIN przychodzi e-mailem oraz SMS-em. |
| `El envío fue entregado correctamente` | Doręczona. |
| `En devolución` | W drodze powrotnej do nadawcy. |
| `Devuelto a remitente` | Zwrócona nadawcy. |

`Pendiente de recepción en CTT Express` budzi najwięcej obaw, ale oznacza jedynie wygenerowanie etykiety. W przypadku paczek nadawanych z Hiszpanii lub Portugalii CTT Express wyjaśnia, że jeśli ten stan utrzymuje się dłużej niż **24 godziny** od zlecenia podjazdu kuriera, można zgłosić sprawę przez formularz kontaktowy, wybierając opcję *„Mi envío sigue en pendiente de entrada en red”*.

Pod komunikatem o utrudnieniu pojawia się szczegółowy powód, np. `No pudimos contactar contigo` („nie udało nam się z Tobą skontaktować”) albo `Dirección incorrecta` („błędny adres”). W FAQ CTT Express uprzedza, że adnotacja o nieobecności odbiorcy może pojawić się nawet wtedy, gdy byłeś w domu, jeśli kurier nie mógł dostać się do bramy lub domofonu.

**Zamówienia z zagranicy i spoza UE:** paczka kupiona poza Półwyspem Iberyjskim musi najpierw zostać przyjęta w kraju nadania i dotrzeć do Hiszpanii lub Portugalii, dlatego status `En tránsito` potrafi utrzymywać się dłużej. Dla sklepów wysyłających spoza Unii Europejskiej (np. z Chin) całodobowe centrum celne CTT Express sortuje ponad 300 000 paczek dziennie i odprawia je średnio w **12 godzin**, po czym doręcza je własną siecią w **24 lub 48 godzin**. Gdy urząd celny zatrzyma przesyłkę, zobaczysz to w śledzeniu lub otrzymasz powiadomienie o brakujących dokumentach bądź należnościach. Od 1 lipca 2026 roku zamówienia spoza UE o wartości do 150 € podlegają w UE zryczałtowanemu cłu **3 € za rodzaj produktu**, a od 1 listopada 2026 roku do wszystkich takich przesyłek doliczana jest ponadto unijna opłata manipulacyjna **2 € za rodzaj produktu**, niezależnie od ich wartości. Kto pobiera te kwoty i jak zwolnić paczkę: [paczka zatrzymana przez urząd celny](guide:customs).

## Godziny doręczeń, punkty Collectt i nieobecność odbiorcy

W Hiszpanii CTT Express doręcza od poniedziałku do piątku, zazwyczaj w godzinach **9:00–19:00** (zależnie od wybranej usługi i miejscowości); doręczenia w soboty odbywają się **wyłącznie w okresie świąt Bożego Narodzenia**. Kurier nie ma możliwości wcześniejszego zapowiedzenia wizyty i dzwoni tylko wtedy, gdy nikogo nie zastanie lub ma problem ze znalezieniem adresu. Po doręczeniu CTT Express wysyła potwierdzenie e-mailem.

W cenie zawarte są **dwie próby doręczenia**, z czego druga przypada w następnym dniu roboczym (trzecia próba jest możliwa po przekazaniu dyspozycji po drugiej nieudanej wizycie). Po nieudanym doręczeniu otrzymasz powiadomienie z linkiem pozwalającym wybrać nowy termin, inny adres, sąsiada lub punkt Collectt Express.

Warunki doręczenia zmienisz także samodzielnie w wyszukiwarce localizador, wpisując pełny numer przesyłki, pierwotny kod pocztowy odbiorcy oraz numer telefonu lub adres e-mail podany przez nadawcę. Do wyboru jest zmiana adresu, inna data, pora poranna lub popołudniowa, sąsiad albo punkt Collectt. Modyfikacje są dostępne **wyłącznie przed pojawieniem się statusu `En reparto`** i mogą wydłużyć dostawę o 24 godziny. Zmiana adresu w tej samej strefie doręczeń oraz przekierowanie do punktu Collectt są **bezpłatne** (punkty Collectt przyjmują przesyłki jednopaczkowe do 20 kg, o sumie trzech wymiarów do 160 cm i najdłuższym boku do 60 cm, bez pobrania, weryfikacji dokumentu tożsamości ani usługi zwrotnej).

**Punkty Collectt Express i automaty Locky:** sieć CTT Express obejmuje ponad **20 000** punktów partnerskich i automatów paczkowych Locky w Hiszpanii i Portugalii. Punkt Collectt przechowuje przesyłkę przez **10 dni** (według FAQ o godzinach — do 10 dni roboczych), a skrytka Locky przez **5 dni**; odbiór jest darmowy za pomocą kodu PIN przesłanego e-mailem i/lub SMS-em. Po upływie terminu paczka jest obsługiwana zgodnie z warunkami umowy nadawcy. Jeśli skrytka Locky się nie otwiera, zadzwoń pod numer podany na automacie lub w wiadomości ze śledzeniem.

**W Portugalii** obowiązują zasady pomocy [CTT](carrier:ctt): tamtejsza sieć Collectt skupia placówki *Lojas CTT*, punkty *Pontos CTT*, agentów *Payshop* oraz automaty *Cacifos Locky*. Po nieudanej próbie paczka może trafić do jednego z tych miejsc, wyjechać ponownie lub wrócić do nadawcy. CTT daje **10 dni roboczych** na odbiór paczki Expresso nadanej z Hiszpanii oraz **5 dni** w automacie Locky.

## Opóźniona, zagubiona lub uszkodzona paczka CTT Express

W sprawie zwrotu pieniędzy lub ponownej wysyłki zwracaj się zawsze bezpośrednio do sklepu, w którym złożono zamówienie.

- **Opóźniona:** CTT Express uznaje przesyłkę za opóźnioną po przekroczeniu szacowanego terminu; weekendy, dni ustawowo wolne, zła pogoda i szczyt świąteczny wydłużają transport. Zobacz też poradnik [śledzenie przesyłki się nie aktualizuje](guide:tracking-not-updating).
- **Oznaczona jako doręczona, ale nieodebrana:** najpierw upewnij się, czy paczki nie odebrał domownik, sąsiad lub portiernia (w skanie może widnieć `El envío fue entregado en conserjería` albo `El envío fue entregado a vecino según las instrucciones indicadas.`), a następnie skorzystaj z opcji w formularzu kontaktowym (*„No tengo mi paquete y pone entregado”*). Więcej wskazówek w poradniku [status doręczono, ale brak paczki](guide:delivered-not-received).
- **Uszkodzona:** sfotografuj przesyłkę przed rozpakowaniem, zachowaj karton i etykiety, a szkodę zgłoś przez formularz w ciągu **7 dni kalendarzowych** od odbioru, zaznaczając, czy ucierpiało opakowanie zewnętrzne, zawartość czy jedno i drugie (nadawca mógł wykupić dodatkową ochronę od utraty lub kradzieży do 2000 €). CTT Express otwiera sprawę i informuje, kto ją prowadzi.
- **Anulowana:** jeśli śledzenie informuje o anulowaniu przesyłki, skontaktuj się ze sklepem w celu jej ponownej aktywacji.
- **W Portugalii:** ogólne warunki przewozu CTT Expresso przyznają prawo do reklamacji zleceniodawcy transportu — w terminie **8 dni** od doręczenia przy uszkodzeniu oraz **30 dni** przy zaginięciu lub opóźnieniu.

## Kontakt i obsługa klienta CTT Express

Aktualne dane z oficjalnych stron CTT Express oraz CTT (brak infolinii w języku polskim ani angielskim):

- **Hiszpania:** `900 841 352` (bezpłatny numer na terenie Hiszpanii na stronach pomocy; artykuł celny z 27 października 2026 r. podaje `900 110 898`) lub `916 602 200` (`+34 916 602 200` z zagranicy), od poniedziałku do piątku w godzinach **9:00–19:00** z wyjątkiem świąt. Pisemnie: `ccaweb@cttexpress.com` albo [formularz kontaktowy](https://www.cttexpress.com/contacto-atc) (wymaga numeru przesyłki). Obsługa klienta działa centralnie w Madrycie wyłącznie telefonicznie, mailowo lub przez formularz — bez stacjonarnych punktów obsługi, czatu na żywo ani WhatsAppa — i odpowiada zazwyczaj w ciągu **24 godzin roboczych**.
- **Portugalia:** infolinia [CTT](carrier:ctt) `21 047 16 16` (`+351 21 047 16 16` z zagranicy), w dni robocze w godzinach **8:30–19:30**, oraz formularz na `ctt.pt/ajuda`.

> CTT Express podkreśla, że nigdy nie prosi o dzwonienie na płatne numery o podwyższonej opłacie zaczynające się od `807`, i ostrzega przed fałszywymi SMS-ami oraz e-mailami żądającymi dopłaty (*gastos adicionales*) przez link w celu odblokowania paczki. W Portugalii CTT ostrzega z kolei przed podrobionymi SMS-ami „CTT Expresso” o opłatach celnych; prawdziwe linki CTT zawsze zaczynają się od `www.ctt.pt`. Sprawdzaj przesyłki wyłącznie na oficjalnych stronach.

## Śledzenie przesyłek CTT Express w Peek

Wklej numer w jednym z dwóch pierwszych formatów z powyższej tabeli, a [Peek](/) od razu rozpozna CTT Express — również dla 25-cyfrowego wariantu zakończonego na `001` utworzonego z numeru `00001000` (natomiast 22-cyfrowego numeru bez `00` na pozycjach 1–2 i 7–8 Peek nie rozpoznaje jako CTT Express). W przypadku 22 cyfr zaczynających się od `00001000` Peek odpytuje jednocześnie TIPSA, Austrian Post oraz CTT Express (a jeśli żaden z nich nie zna paczki, może też sprawdzić USPS); gdy nie da się jednoznacznie wskazać jednego przewoźnika, wybierzesz go przyciskiem „Zmień”. Bezpośrednio działa także link skopiowany ze strony `shipping-tracking.production.cloud2.cttexpress.com` z parametrem `sc=`. Wpisany osobno kod zakończony na `PT` trafia do [CTT](carrier:ctt), natomiast przesyłki wielopaczkowe są śledzone przez uniwersalne serwisy śledzenia.

Peek sprawdza CTT Express nawet co 10 minut, a od godziny 6:00 czasu lokalnego w dniu, w którym paczka przejdzie w stan „W doręczeniu” — nawet co 2 minuty, dopóki odpowiada bezpośredni system CTT Express (przez serwisy uniwersalne co 15 minut). Po 48 godzinach bez zmian — lub po 12 godzinach na etapie „Zarejestrowana” bądź „Przyjęta przez przewoźnika” — odpytywanie przechodzi w tryb godzinny, a powiadomienia można ustawić na „Wszystkie aktualizacje”, „Tylko ważne” lub „Tylko w dniu dostawy”. Peek wyświetla bieżący status, historię skanów (bez miejscowości na mapie, bo publiczny kanał CTT Express ich nie podaje), przewidywaną datę dostawy w trakcie bezproblemowego transportu, wagę, godzinę doręczenia oraz — gdy paczkę przekazała inna poczta — jej międzynarodowy numer pocztowy. Jeśli numer ten kończy się na `PT`, doręczenie w Portugalii realizuje [CTT](carrier:ctt): na karcie zobaczysz „Doręcza: CTT Portugal” wraz z kodem (lub „Zgłoszona przewoźnikowi CTT Portugal, jeszcze nieprzyjęta”, dopóki CTT wykazuje przesyłkę bez własnego skanu). Gdy tylko na `ctt.pt` pojawi się skan co najmniej tak świeży jak ostatni wpis CTT Express, Peek przełącza dalsze odświeżanie na tempo CTT, zachowując wcześniejszą historię z CTT Express. Treści skanów pozostają w oryginalnym języku hiszpańskim (`En reparto` albo `Disponible en Punto Collectt Express` bez nazwy i adresu punktu), zwroty od `En devolución` wzwyż są oznaczane jako problem aż do `Devuelto a remitente`, a na `peektracker.com` i w aplikacjach na iPhone'a i Androida historia kończy się dopiskiem „Godziny w Twojej strefie czasowej: …”.

## Najczęstsze pytania o śledzenie CTT Express

### Czym jest CTT Express?

To hiszpańska spółka kurierska należąca do portugalskiej grupy CTT, powstała w 2019 roku po zmianie nazwy Tourline Express. Nie jest tym samym co [CTT](carrier:ctt), czyli portugalska poczta, choć obie firmy należą do jednej grupy, a w maju 2026 roku CTT Expresso utworzyło iberyjską spółkę joint venture z DHL eCommerce. Doręcza przesyłki w całej Hiszpanii (część kontynentalna, Baleary, Wyspy Kanaryjskie, Ceuta i Melilla) oraz w Portugalii.

### Czy CTT Express doręcza paczki w soboty?

W Hiszpanii wyłącznie w sezonie świąt Bożego Narodzenia. Przez resztę roku kurierzy doręczają przesyłki od poniedziałku do piątku, zazwyczaj między 9:00 a 19:00.

### Czy można śledzić paczkę CTT Express po polsku lub po angielsku?

Nie. Oficjalna strona śledzenia działa wyłącznie po hiszpańsku; zamieszczona wyżej tabela tłumaczy na polski wszystkie popularne statusy i powody utrudnień.

### Czy CTT Express ma czat na żywo lub obsługę przez WhatsApp?

Na oficjalnej stronie nie ma czatu na żywo ani kontaktu przez WhatsApp: dostępne kanały to telefon (`900 841 352` w Hiszpanii lub `+34 916 602 200` z zagranicy), e-mail (`ccaweb@cttexpress.com`) oraz formularz kontaktowy.

### Czy da się wyśledzić przesyłkę CTT Express bez numeru nadania?

Nie. Wyszukiwarka localizador ma tylko jedno pole przeznaczone na numer przesyłki, a formularz obsługi klienta również go wymaga. Poszukaj numeru w SMS-ie lub wiadomości e-mail z potwierdzeniem zamówienia albo poproś o niego sklep, który nadał paczkę.

:::sources
- [CTT Express: Localizador de envíos](https://www.cttexpress.com/localizador-de-envios/) – wyszukiwarka przesyłek, zmiana doręczenia i e-mail potwierdzający dostawę
- [CTT Express: Localizador de envios (portugalski)](https://www.cttexpress.com/localizador-de-envios/?language_code=pt) – przekierowanie do „Seguir objeto” na `ctt.pt`
- [CTT Express: Seguimiento de envíos](https://shipping-tracking.production.cloud2.cttexpress.com/) – śledzenie wyłącznie po hiszpańsku, etapy, powody utrudnień, jedno pole wyszukiwania i przykład kodu `PT`
- [CTT Express: Empresa de Paquetería y Mensajería](https://www.cttexpress.com/) – zasięg działania, sieć Collectt i przełącznik języka
- [CTT Express: Quiénes somos](https://www.cttexpress.com/nosotros/quienes-somos) – grupa CTT i zmiana nazwy z Tourline Express w 2019 r.
- [CTT Express: CTT Express y DHL unen fuerzas](https://www.cttexpress.com/nosotros/ctt-express-y-dhl-unen-fuerzas) – strategiczny sojusz na Półwyspie Iberyjskim z DHL eCommerce
- [CTT Express: Komunikat prasowy CTT Expresso i DHL eCommerce](https://www.cttexpress.com/zona-de-prensa/notas-de-prensa/ctt-expresso-y-dhl-ecommerce-sellan-un-hito-en-su-alianza-para-construir-la-red-de-paqueteria-mas-completa-de-la-peninsula-iberica) – spółka joint venture z maja 2026 r. i wzajemne udziały po 25 %
- [CTT Express: Envíos urgentes 24 horas](https://www.cttexpress.com/envios/24-h) – usługi ekspresowe `CTT 24h`, `CTT e24h`, `CTT 14h` oraz `CTT 10h`
- [CTT Express: Envíos 48 horas](https://www.cttexpress.com/envios/48-h) – usługa `CTT 48h` obejmująca Baleary, Wyspy Kanaryjskie, Ceutę, Melillę, Maderę i Azory
- [CTT Express: Shipment Number Calculation and Package Codes (PDF)](https://transfer.cttexpress.com/IT/export/integra/Shipment_Number_Calculation_and_Package_Codes_of_CttExpress.pdf) – budowa kodów 22- i 25-cyfrowych oraz licznik paczki `001`
- [CTT Express: FAQs sobre los estados de envío](https://www.cttexpress.com/preguntas-frecuentes/estados-envio) – statusy, brak wejścia do sieci po 24 h, paczka oznaczona jako doręczona i 7 dni kalendarzowych na zgłoszenie szkody
- [CTT Express: Preguntas frecuentes de incidencias](https://www.cttexpress.com/preguntas-frecuentes/incidencias) – dwie próby doręczenia, opóźnienia i reklamacje
- [CTT Express: Horarios de reparto](https://www.cttexpress.com/preguntas-frecuentes/horarios) – godziny 9:00–19:00, soboty przed świętami i telefon kuriera
- [CTT Express: Cambiar dirección de entrega](https://www.cttexpress.com/cambiar-direccion-entrega/) – zasady bezpłatnej zmiany dostawy przed statusem `En reparto`
- [CTT Express: Servicios incluidos y adicionales](https://www.cttexpress.com/servicios/incluidos-y-adicionales) – próby w cenie, limity punktów Collectt (20 kg, 160 cm) i dodatkowa ochrona do 2000 €
- [CTT Express: Recoge y devuelve tus paquetes en nuestros puntos](https://www.cttexpress.com/red-collectt-express/envio-y-recogida) – ponad 20 000 punktów Collectt (10 dni) i automatów Locky (5 dni)
- [CTT Express: Preguntas frecuentes Puntos Collectt Express](https://www.cttexpress.com/preguntas-frecuentes/puntos-collectt-express) – odbiór na kod PIN i bezpłatne przekierowanie
- [CTT Express: Qué significa „aceptado por la última milla”](https://www.cttexpress.com/blog/logistica/que-significa-aceptado-ultima-milla) – znaczenie statusów operacyjnych
- [CTT Express: Informacja prasowa o przesyłkach](https://www.cttexpress.com/zona-de-prensa/notas-de-prensa/como-localizo-mi-paquete-con-ctt-express-y-otras-preguntas-frecuentes-sobre-envios) – czas transportu przesyłek z zagranicy
- [CTT Express: Servicio de importación de paquetería](https://www.cttexpress.com/servicios/importacion/) – odprawa spoza UE średnio w 12 godzin (ponad 300 000 paczek dziennie) i dostawa w 24 lub 48 godzin
- [CTT Express: ¿Qué hacer si mi paquete ha quedado retenido en aduanas?](https://www.cttexpress.com/blog/expansion-internacional/que-hacer-si-mi-paquete-ha-quedado-retenido-en-aduanas) – zatrzymania celne, Wyspy Kanaryjskie i numer `900 110 898`
- [Komisja Europejska: Reforma unii celnej UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_pl) – cło 3 € za rodzaj produktu do 150 € od 1 lipca 2026 r. oraz opłata manipulacyjna 2 € od 1 listopada 2026 r.
- [Komisja Europejska: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – naliczanie 3 € cła i 2 € opłaty za rodzaj produktu niezależnie od wartości
- [CTT Express: Cómo contactar con CTT Express](https://www.cttexpress.com/preguntas-frecuentes/como-contactar-con-ctt) – telefony, godziny pracy, e-mail, odpowiedź w 24 godziny robocze i ostrzeżenie przed numerami `807`
- [CTT Express: Contacto y Atención al Cliente](https://www.cttexpress.com/contacto-atc) – formularz kontaktowy wymagający numeru przesyłki
- [CTT Express: Qué es Phishing](https://www.cttexpress.com/blog/actualidad/como-evitar-phishing) – fałszywe SMS-y i e-maile z prośbą o dopłatę
- [CTT: Encontrar o código de envio](https://www.ctt.pt/ajuda/empresas/seguir-ou-alterar-entrega/seguir/encontrar-o-codigo-de-envio) – 13-znakowe kody `PT` oraz numery 22- i 25-cyfrowe
- [CTT: Condições Gerais do Serviço Postal Universal](https://www.ctt.pt/home/servico-postal-universal/condicoes-gerais-de-acesso-e-utilizacao-do-servico-universal) – CTT jako operator powszechnej usługi pocztowej w Portugalii
- [CTT: Empresas do grupo](https://www.ctt.pt/grupo-ctt/a-empresa/quem-somos/empresas-do-grupo/) – CTT Express i CTT Expresso w grupie CTT
- [CTT: Prazo para levantar](https://www.ctt.pt/ajuda/particulares/receber/levantar-correspondencia-ou-encomendas-/prazo-para-levantar) – terminy odbioru w Portugalii (10 dni roboczych z Hiszpanii, 5 dni w Locky)
- [CTT: Collectt](https://www.ctt.pt/particulares/receber/locais-de-entrega-alternativos/collectt) – sieć Collectt w Portugalii
- [CTT: Entrega não conseguida](https://www.ctt.pt/ajuda/particulares/receber/problemas-na-entrega/entrega-nao-conseguida) – zasady po nieudanym doręczeniu w Portugalii
- [CTT: Condições gerais de transporte de Encomendas Expresso](https://www.ctt.pt/transversais/condicoes-gerais-transporte-encomendas-expresso) – terminy reklamacji 8 i 30 dni
- [CTT: Linhas de Apoio](https://www.ctt.pt/ajuda/particulares/apoio-ao-cliente/contactos/linhas-de-apoio) – infolinia i godziny pracy w Portugalii
- [CTT: Alertas de Phishing](https://www.ctt.pt/transversais/alertas-de-phishing) – fałszywe SMS-y podszywające się pod CTT Expresso
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – rozpoznawanie numerów, status `Recogerán en Delegación` i przekazanie do CTT Portugal w Peek
:::
