---
title: Śledzenie Correos Express: numer, statusy i kontakt
description: Śledź paczkę Correos Express z Hiszpanii lub Portugalii: 16- lub 23-cyfrowy numer, znaczenie hiszpańskich statusów po polsku, odbiór z PIN-em i kontakt.
slug: correos-express-sledzenie
published: 2026-11-17
updated: 2026-11-17
---

Correos Express (dawniej Chronoexprés, pełna nazwa spółki *Correos Express Paquetería Urgente, S.A., S.M.E.*) to ekspresowa spółka kurierska należąca do hiszpańskiej grupy Correos. Doręcza przesyłki w całej Hiszpanii i Portugalii oraz wysyła paczki do Polski, Europy i na cały świat w ramach usług międzynarodowych; nie należy jej mylić z Correos, czyli publiczną pocztą hiszpańską. Aby sprawdzić losy przesyłki, wpisz numer na [hiszpańskojęzycznej stronie śledzenia Correos Express](https://s.correosexpress.com/) albo wklej go w polu powyżej. Przesyłkę rejestruje nadawca (zazwyczaj sklep internetowy), więc w razie braku numeru zapytaj sprzedawcę.

## Correos czy Correos Express: który przewoźnik wiezie Twoją paczkę?

Correos to spółka matka realizująca w Hiszpanii powszechne usługi pocztowe, natomiast Correos Express odpowiada za pilne przesyłki kurierskie. Choć korzystają ze wspólnej sieci placówek pocztowych i punktów *Punto Correos*, w internecie mają osobne wyszukiwarki i inne formaty numerów:

- **Wyłącznie cyfry (16 lub 23 cyfry):** to numer Correos Express (przy czym 16 cyfr stosują również inni przewoźnicy – szczegóły w tabeli niżej). Wklej go w polu powyżej.
- **Litery i cyfry razem:** to przesyłka Correos, hiszpańskiej poczty. Jej numery mają 13 znaków zakończonych literami `ES`, prefiks `PR` + 15 cyfr + `C` albo 16 lub 23 znaki zaczynające i kończące się literą. Correos udostępnia własną [angielską stronę śledzenia](https://www.correos.es/es/en/tools/tracker/items).

## Numer śledzenia Correos Express: formaty 16- i 23-cyfrowe

Correos Express nie publikuje specyfikacji swoich numerów i prosi po prostu o *número de envío* („numer przesyłki”). Katalog aplikacji Peek rozpoznaje dwa w pełni cyfrowe formaty zakończone cyfrą kontrolną GS1 (przykłady zmyślone):

| Przykładowy numer | Do kogo należy i jak sprawdza go Peek |
| --- | --- |
| `3230 0000 0000 0000` | **16 cyfr** (z poprawną cyfrą kontrolną GS1, niezaczynający się od sześciu zer): Correos Express, ale także DHL eCommerce, Canada Post, [TNT](carrier:tnt) oraz [Evri](carrier:evri). Przy pierwszym sprawdzeniu Peek odpytuje równolegle wszystkich pięciu przewoźników. |
| `32300000000000001000007` | **23 cyfry** (z poprawną cyfrą kontrolną GS1): wskazuje wyłącznie na Correos Express – z wyjątkiem numerów mających `000` na pozycjach 15–17 (przypisanych do Australia Post). |
| `RR100000003ES` | **Litery i cyfry** w [międzynarodowym formacie pocztowym](guide:tracking-number-formats): Correos (poczta hiszpańska), a nie Correos Express. |
| `0100/00000005` | **4 cyfry, ukośnik, 8 cyfr:** [NACEX](carrier:nacex), inna iberyjska firma kurierska. |

Wklej **sam numer** (spacje, kropki i myślniki zostaną usunięte automatycznie), a nie link `s.correosexpress.com` ani całą treść wiadomości e-mail – Peek nie zawsze znajduje numery Correos Express w linkach i tekstach. Uważaj też na literówki: pomyłka w jednej cyfrze psuje sumę kontrolną GS1 i wyklucza Correos Express z automatycznego wykrywania 16-cyfrowych kodów. Przewoźnik nie podaje nazwy nadawcy swoich SMS-ów; więcej podpowiedzi znajdziesz w poradniku [jak znaleźć numer śledzenia](guide:find-tracking-number).

Jeżeli wyszukiwarka zwróci komunikat *„Lo sentimos, no se ha encontrado ningún envío con el número indicado”* („Przepraszamy, nie znaleziono żadnej przesyłki o podanym numerze”), Correos Express radzi upewnić się, że numer jest poprawny i dotyczy tej firmy, a przy świeżo nadanych paczkach odczekać kilka godzin. Jeśli po 24–48 godzinach nadal brak informacji, skontaktuj się z nadawcą.

## Statusy Correos Express: co oznaczają komunikaty po hiszpańsku?

Correos Express nie prowadzi śledzenia po polsku ani po angielsku: główna wyszukiwarka działa po hiszpańsku, a [wersja portugalska](https://s.correosexpress.com/SeguimientoSinCP/search-pt) ma portugalskie menu. Dla użytkowników z Polski Peek odpytuje system hiszpański i tłumaczy poniższe statusy:

| Status Correos Express | Co oznacza dla Twojej paczki |
| --- | --- |
| `INFORMADO` | Nadawca zarejestrował przesyłkę, ale paczka nie dotarła jeszcze do obiektów Correos Express. |
| `ADMITIDO` | Paczka została odebrana i wyruszyła z oddziału nadawczego (jako `ADMITIDO EN OFICINA DE CORREOS` – nadana w placówce pocztowej Correos). |
| `EN RUTA A LOCALIDAD DE DESTINO` | W drodze do lokalnego oddziału doręczającego. Zazwyczaj na tym etapie działa jeszcze przycisk *Gestionar entrega*. |
| `EN DESTINO` | „W miejscu przeznaczenia”: przesyłka dotarła do docelowego oddziału dystrybucyjnego. |
| `EN REPARTO` | W doręczeniu u kuriera. Na tym etapie nie można już zmieniać opcji elastycznej dostawy. |
| `Su envío no ha podido ser entregado` | „Twoja przesyłka nie mogła zostać doręczona”: nieudana próba doręczenia. |
| `NUEVO REPARTO` | „Nowe doręczenie”: zaplanowano kolejną wizytę kuriera. |
| `Cambio de destino` | „Zmiana miejsca przeznaczenia”: nadawca zlecił doręczenie pod nowy adres. |
| `ESTACIONADO` | „Wstrzymana w oddziale”: według portugalskiego FAQ próba doręczenia nie powiodła się i paczka znajduje się obecnie w magazynie Correos Express. |
| `EN ALMACÉN` / `EN GESTIÓN` | „W magazynie” / „W trakcie obsługi”: paczka oczekuje w oddziale na wyjaśnienie incydentu. |
| `ENTREGADO EN PUNTO DE CONVENIENCIA` | Dostarczona do punktu odbioru *Punto Correos*, ale **jeszcze niegotowa** do wydania odbiorcy. |
| `DISPONIBLE EN PUNTO DE CONVENIENCIA` | Gotowa do odbioru w punkcie za okazaniem kodu PIN. |
| `ENTREGADO` | Doręczona Tobie lub osobie upoważnionej. |
| `DEVUELTO` | Zwrócona do nadawcy (podobnie jak długi komunikat informujący o upływie terminu odbioru w punkcie). |

**Częsta pułapka:** wpis `ENTREGADO EN PUNTO DE CONVENIENCIA` zawiera słowo *entregado* („doręczono”), ale oznacza wyłącznie przekazanie paczki obsłudze punktu przez kuriera. Z wyjściem po paczkę zaczekaj na status `DISPONIBLE EN PUNTO DE CONVENIENCIA` i przygotuj kod PIN. Na hiszpańskiej stronie pomocy Correos Express opisuje jedynie `INFORMADO`, `ADMITIDO`, `EN RUTA A LOCALIDAD DE DESTINO`, `EN REPARTO` oraz `ENTREGADO`, a `ESTACIONADO` wyjaśnia w portugalskim FAQ; pozostałe objaśnienia wynikają z dosłownego brzmienia i mapowania w Peek (ogólny przewodnik znajdziesz w artykule [co oznaczają statusy śledzenia](guide:tracking-statuses)).

## Wysyłka do Polski i Europy, zmiana dostawy oraz odbiór w Punto Correos

**Z Hiszpanii do Polski, Europy i na świat:** Correos Express realizuje wysyłki zagraniczne z Hiszpanii w dwóch wariantach: *Internacional Estándar* (usługa od drzwi do drzwi w Europie dla pojedynczych paczek, z orientacyjnym czasem przewozu 2–3 dni robocze do głównych krajów UE) oraz *Internacional Express* (pilna przesyłka lotnicza do ponad 180 krajów, w Europie orientacyjnie 1–2 dni robocze zależnie od celu, obejmująca do dwóch prób doręczenia i śledzenie online aż do wydania). Weekendy oraz święta w krajach tranzytowych i docelowych nie wliczają się do podanych terminów.

**Zarządzanie dostawą w Hiszpanii:** zanim przesyłka wejdzie w fazę `EN REPARTO`, jeśli zezwala na to usługa i nadawca, przycisk **Gestionar entrega** w śledzeniu pozwala przekierować paczkę do punktu *Punto Correos*, zmienić adres lub wybrać inną datę. Usługi takie jak *Paq 24* i *Paq 14* obejmują do dwóch prób doręczenia bez dopłaty; po nieudanej wizycie formularz [Solicita nueva entrega](https://s.correosexpress.com/SeguimientoSinCP/saveNewDelivery) umożliwia wybór nowego dnia (przedział poranny lub popołudniowy), placówki pocztowej Correos albo punktu odbioru, a paczki *Paq e-Commerce* mogą też trafić do najbliższego punktu *Punto Correos* z wolnym miejscem. Przy przesyłce za pobraniem (*contra reembolso*) kurier pobiera należność przy drzwiach; Correos Express zaleca przygotowanie odliczonej gotówki.

**W Portugalii:** po nieobecności druga próba odbywa się automatycznie, a powiadomienie pozwala wybrać inny dzień, inny adres w tym samym dystrykcie lub *Ponto Correos* (tam, gdzie jest dostępny). Nieodebrana paczka po **3 dniach roboczych** od nieudanego doręczenia wraca zgodnie z portugalskim regulaminem do nadawcy.

**Czas oczekiwania na odbiór i kod PIN:**

- **Punto Correos (Hiszpania):** paczka czeka **7 dni kalendarzowych** od przyjęcia w punkcie, po czym jest automatycznie zwracana. Przy odbiorze pokaż bezpieczny kod PIN z wiadomości SMS i/lub e-mail; jeśli nie ma go także w folderze spam, kliknij **Visualizar PIN** na stronie śledzenia po przejściu weryfikacji.
- **Placówka pocztowa Correos (*Entrega en oficina de Correos*, Hiszpania):** opcja dla *Paq 24* i *Baleares Express* (bez pobrania), z czasem przechowywania do **15 dni kalendarzowych** i trzema powiadomieniami SMS (przy nadejściu oraz po 5 i 10 dniach).
- **Portugalia (*Ponto Correos* i automaty):** do **7 dni** w punkcie odbioru albo do **3 dni** w automatach *CitiBox* i *CityPaq*; paczkę może odebrać każda osoba dysponująca jednorazowym kodem PIN.

## Cło, terytoria specjalne i ostrzeżenie przed fałszywymi SMS-ami

Między kontynentalną Hiszpanią (lub Balearami) a Polską paczki krążą wewnątrz unijnego obszaru VAT bez odprawy celnej. Rzeczywiste formalności celne i podatki pojawiają się natomiast wtedy, gdy przesyłka przekracza granicę podatkową lub celną:

- **Wyspy Kanaryjskie, Ceuta, Melilla, Andora, Gibraltar, Azory, Madera oraz kraje spoza UE:** Correos Express przypomina, że przesyłki na Wyspy Kanaryjskie, do Andory, Ceuty, Melilli lub Gibraltaru wymagają faktury handlowej bądź proforma (a w przypadku Wysp Kanaryjskich także podpisanego oświadczenia towarowego – *declaración jurada*) i mogą wiązać się z lokalnymi podatkami oraz kosztami odprawy niewliczonymi w cenę nadania; dokumentacji celnej wymagają również przesyłki lotnicze na Azory i Maderę. Od końca 2020 r., po wyjściu Wielkiej Brytanii z jednolitego rynku i unii celnej UE, wymogi oraz koszty celne dotyczą też tras brytyjskich (*Internacional Express* obsługuje odprawę poza UE); zobacz poradnik [cło i opłaty importowe](guide:customs).

> **Uwaga na fałszywe SMS-y (*smishing*):** na stronie *Alerta de seguridad* Correos Express podkreśla, że **nigdy żadnym kanałem nie prosi o dane bankowe ani hasła dostępu do swojego serwisu** oraz **nie posiada ani nie obsługuje płatnych infolinii o prefiksie 807**; w razie wątpliwości należy korzystać wyłącznie z `www.correosexpress.es` lub zadzwonić pod `900 122 333`. W marcu 2022 r. hiszpański instytut cyberbezpieczeństwa INCIBE ostrzegł przed fałszywymi SMS-ami podszywającymi się pod Correos i Correos Express, które pod pretekstem wstrzymanego doręczenia i rzekomej dopłaty celnej lub transportowej kierowały na podrobione strony płatności. SMS w imieniu Correos Express, według którego paczki nie doręczono z powodu nieopłaconych należności i który prosi o otwarcie linku, to oszustwo: nie otwieraj linku. Pobranie płacisz kurierowi przy doręczeniu. Sprawdź numer bezpośrednio na `s.correosexpress.com`, a jeśli podałeś już dane karty na fałszywej stronie, natychmiast skontaktuj się ze swoim bankiem.

## Paczka Correos Express opóźniona, niedoręczona lub uszkodzona

Stroną umowy przewozu z Correos Express jest nadawca, a regulamin dopuszcza tylko jedną reklamację na przesyłkę – w większości spraw należy więc działać przez sprzedawcę:

- **Brak aktualizacji statusu:** między centrami logistycznymi wpisy mogą pojawiać się z kilkugodzinnym poślizgiem. Jeżeli przez **24–48 godzin** nic się nie zmienia, Correos Express zaleca kontakt z nadawcą; sprawdź też [co zrobić, gdy śledzenie stoi w miejscu](guide:tracking-not-updating).
- **Wpis o próbie doręczenia mimo Twojej obecności:** zajrzyj do szczegółów w śledzeniu (przeszkodą bywa utrudniony dostęp do budynku lub niepełny adres), a następnie umów ponowną dostawę.
- **Status `ENTREGADO`, ale brak paczki:** w Hiszpanii sprawdź szczegóły doręczenia w wyszukiwarce i zgłoś sprawę sprzedawcy; w Portugalii odbiorcy mogą napisać na `destinatarioscep@correosexpress.com`. Więcej wskazówek w poradniku [status dostarczono, ale brak przesyłki](guide:delivered-not-received).
- **Uszkodzenie lub ubytek:** widoczne z zewnątrz szkody zawsze wpisz do protokołu w chwili odbioru. Przy szkodach ukrytych hiszpańskie warunki przewozu wymagają pisemnych zastrzeżeń w ciągu **7 dni kalendarzowych** w ruchu krajowym (oraz **21 dni** przy opóźnieniu), natomiast w transporcie międzynarodowym – w ciągu **7 dni w przewozie drogowym** i **14 dni w przewozie lotniczym** (w Portugalii **8 dni** według regulaminu oraz zgłoszenie ze zdjęciami do nadawcy w ciągu **7 dni** według FAQ).
- **Odmowa przyjęcia lub `DEVUELTO`:** po odmowie przyjęcia Correos Express powiadamia nadawcę i czeka na jego dyspozycje. Przy zakupach konsumenckich w UE serwis *Your Europe* przypomina, że sprzedawca odpowiada za przesyłkę aż do fizycznego wydania jej kupującemu (dostawa w ciągu **30 dni**, o ile nie uzgodniono inaczej) – w sprawie ponownej wysyłki lub zwrotu pieniędzy skontaktuj się ze sklepem.

## Kontakt z obsługą klienta Correos Express

Correos Express prowadzi obsługę klienta i strony pomocy wyłącznie po hiszpańsku i portugalsku:

- **Hiszpania:** `900 122 333` (brak opublikowanych godzin pracy; w Hiszpanii połączenie darmowe z sieci stacjonarnych i komórkowych według CNMC). Wyszukiwarka przesyłek podaje także numer `913 277 020`. Do dyspozycji jest również [formularz obsługi klienta](https://www.correosexpress.es/es/atencion-al-cliente/contacto/atencion-al-cliente) (wymaga numeru przesyłki i kodu pocztowego) oraz profil `@Cex_responde` w serwisie X.
- **Portugalia:** `229 762 810` (opłata jak za połączenie na portugalską sieć stacjonarną), od poniedziałku do piątku w godz. 08:30–19:00, a dla odbiorców adres e-mail `destinatarioscep@correosexpress.com`.

## Śledzenie paczek Correos Express w aplikacji Peek

Ponieważ 16-cyfrowe numery są używane przez pięciu przewoźników, [Peek](/) odpytuje ich równocześnie, aby ustalić właściwego kuriera; poprawny numer 23-cyfrowy wskazuje bezpośrednio na Correos Express (poza zakresem `000` należącym do Australia Post).

Peek odświeża status nawet co 10 minut, a w dniu wydania paczki kurierowi („W doręczeniu”) od 06:00 czasu lokalnego nawet co 2 minuty, dopóki odpowiada bezpośrednio system Correos Express (przez pośrednie serwisy śledzące – nawet co 15 minut). Po dwóch dniach bez nowych wpisów – albo po 12 godzinach na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika” – aplikacja sprawdza przesyłkę raz na godzinę. Powiadomienia możesz włączyć dla opcji „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek pokazuje przetłumaczony status, przewidywaną dostawę w czasie podróży oraz każdy skan na mapie. Aplikacja pomija potwierdzenie odbioru, dane kontaktowe i komentarze do doręczenia oraz nie kontaktuje się w Twoim imieniu z Correos Express.

## Najczęstsze pytania o śledzenie Correos Express

### Czy Correos i Correos Express to ta sama firma?

Nie. Correos to hiszpański publiczny operator pocztowy realizujący usługę powszechną, natomiast Correos Express (*Correos Express Paquetería Urgente, S.A., S.M.E.*) jest spółką grupy wyspecjalizowaną w przesyłkach ekspresowych. Dzielą sieć punktów *Punto Correos*, ale numery złożone wyłącznie z 16 lub 23 cyfr śledzi się w Correos Express, a kody alfanumeryczne w rodzaju `RR100000003ES` – w Correos.

### Ile idzie paczka Correos Express i o której godzinie przyjeżdża kurier?

Na Półwyspie Iberyjskim Correos Express deklaruje dostawę w 24 godziny (*Paq 24*), między Hiszpanią a kontynentalną Portugalią zazwyczaj 24–48 godzin, a do Ceuty, Melilli, Andory i Gibraltaru na drugi dzień roboczy (z płatną opcją sobotnią *Entrega en sábados* dla *Paq 24* w kontynentalnej Hiszpanii). Usługi terminowe doręczają przed 10:00 (*Paq 10*) lub przed 14:00 (*Paq 14*). W Europie orientacyjny czas przewozu wynosi 2–3 dni robocze dla *Internacional Estándar* oraz 1–2 dni robocze dla *Internacional Express*. Szacowana godzina w śledzeniu jest wyliczana na podstawie wcześniejszych doręczeń w Twoim kodzie pocztowym i może ulec zmianie na trasie.

### Dlaczego widzę status `ENTREGADO EN PUNTO DE CONVENIENCIA`, ale nie mam jeszcze kodu PIN?

Ponieważ ten status oznacza jedynie zdanie przesyłki w punkcie przez kuriera, zanim obsługa udostępni ją do wydania. Poczekaj na zmianę statusu na `DISPONIBLE EN PUNTO DE CONVENIENCIA` – wtedy otrzymasz PIN SMS-em lub e-mailem (albo podejrzysz go przyciskiem **Visualizar PIN** na stronie śledzenia) i od tego momentu w Hiszpanii biegnie 7 dni kalendarzowych na odbiór.

### Czy z Correos Express można skontaktować się po polsku lub angielsku?

Nie. Zarówno strony śledzenia i działy pomocy, jak i infolinia działają wyłącznie po hiszpańsku oraz portugalsku. Zamawiając towar do Polski, w razie kłopotów najwygodniej zgłosić sprawę bezpośrednio obsłudze sklepu internetowego.

:::sources
- [Correos Express: Seguimiento de envíos](https://www.correosexpress.es/es/atencion-al-cliente/seguimiento-envio) – statusy, 24–48 h, godzina dostawy
- [Correos Express: Gestiona tu entrega](https://www.correosexpress.es/es/atencion-al-cliente/seguimiento-envio/gestiona-tu-entrega) – Gestionar entrega, Paq e-Commerce
- [Correos Express: Punto Correos](https://www.correosexpress.es/es/atencion-al-cliente/seguimiento-envio/recogida-en-punto-correos) – kod PIN, Visualizar PIN, 7 dni
- [Correos Express: Otras consultas](https://www.correosexpress.es/es/atencion-al-cliente/seguimiento-envio/otras-consultas-entrega) – pobranie, kanały kontaktu
- [Correos Express: Sigue tu envío](https://s.correosexpress.com/) – wyszukiwarka, błąd, 913 277 020
- [Correos Express: Solicita nueva entrega](https://s.correosexpress.com/SeguimientoSinCP/saveNewDelivery) – ponowna dostawa, urząd, punkt
- [Correos Express: Atención al Cliente](https://www.correosexpress.es/es/atencion-al-cliente/contacto/atencion-al-cliente) – formularz online
- [Correos Express: Términos y condiciones](https://www.correosexpress.es/es/atencion-al-cliente/tyc-transporte) – terminy reklamacji, odmowa
- [Correos Express: Aviso Legal](https://www.correosexpress.es/es/legales/aviso-legal) – nazwa spółki
- [Correos Express: Alerta de seguridad](https://www.correosexpress.es/es/legales/alerta-de-seguridad) – ostrzeżenie bezpieczeństwa, 900 122 333
- [Correos Express: Strona główna](https://www.correosexpress.es/) – Paq 10, Paq 14, Paq 24
- [Correos Express: Quiénes somos](https://www.correosexpress.es/es/sobre-correos-express/quienes-somos) – różnica względem Correos
- [Correos Express: España y Portugal](https://www.correosexpress.es/es/atencion-al-cliente/envios-en-espana-y-portugal) – Portugalia, terytoria specjalne, cło
- [Correos Express: Envíos internacionales](https://www.correosexpress.es/es/atencion-al-cliente/envios-internacionales1) – Internacional Estándar, Wielka Brytania
- [Correos Express: Internacional Express](https://www.correosexpress.es/es/particulares/enviar/internacionales/internacional-express) – 180 krajów, 1–2 dni robocze
- [Correos Express: Internacional Express Empresas](https://www.correosexpress.es/es/empresas/productos/internacional-express) – dwie próby, odprawa poza UE
- [Correos Express: Paq 24](https://www.correosexpress.es/es/particulares/enviar/peninsulares/paq-24) – dwie próby, opcja sobotnia
- [Correos Express: Paq 14](https://www.correosexpress.es/es/particulares/enviar/peninsulares/paq-14) – przed 14:00, dwie próby
- [Correos Express: Entrega en oficina](https://clientes.correosexpress.com/web/correosexpress/entrega-en-oficina-de-correos) – 15 dni w urzędzie, 3 SMS-y
- [Correos Express: Sieć Punto Correos](https://www.correosexpress.es/es/sobre-correos-express/sala-de-prensa/2025/grupo-correos-lanza-su-red-de-puntos-de-conveniencia-y-ampliara-) – wspólna sieć grupy
- [Correos: Tracker](https://www.correos.es/es/en/tools/tracker/items) – wyszukiwarka Correos
- [Correos Express PT: Siga seu envio](https://s.correosexpress.com/SeguimientoSinCP/search-pt) – portugalskie śledzenie
- [Correos Express PT: FAQ](https://www.correosexpress.pt/pt/apoio-ao-cliente/perguntas-frequentes) – ESTACIONADO, druga próba, szkody
- [Correos Express PT: Apoio ao Cliente](https://www.correosexpress.pt/pt/apoio-ao-cliente/contacto/apoio-cliente) – 229 762 810, godziny, e-mail
- [Correos Express PT: Termos](https://www.correosexpress.pt/pt/apoio-ao-cliente/tec-transporte) – 3 dni robocze, 8 dni, PIN
- [Correos Express PT: Paq Ponto](https://www.correosexpress.pt/pt/empresa/enviar/peninsulares/paq-ponto) – 7 dni w punkcie, 3 dni w automacie
- [CNMC: Numery 800 i 900](https://blog.cnmc.es/2019/04/24/llamadas-a-numeros-800-y-900-gratis-para-el-consumidor-y-quien-paga/) – bezpłatne linie 900
- [INCIBE: Ostrzeżenie o smishingu](https://www.incibe.es/ciudadania/avisos/en-circulacion-sms-fraudulentos-que-suplantan-correos-y-otras-empresas-de) – fałszywe SMS-y
- [Your Europe: Dostawa](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_pl.htm) – 30 dni, ryzyko sprzedawcy
- [Your Europe: VAT w UE](https://europa.eu/youreurope/citizens/consumers/shopping/vat/index_pl.htm) – obszar VAT UE
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – kody GS1 i statusy w Peek
:::
