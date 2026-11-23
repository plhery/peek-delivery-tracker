---
title: Śledzenie Chronodiali: numery CHPU, statusy i kontakt
description: Śledź paczkę Chronodiali w Maroku: numer CHPU, francuskie statusy wyjaśnione po polsku, płatność przy odbiorze, trzy próby doręczenia i kontakt.
slug: sledzenie-chronodiali
published: 2026-11-23
updated: 2026-11-23
---

Chronodiali (zapisywane też jako Chrono Diali) to marokańska firma kurierska dla sklepów internetowych, spółka joint venture Barid Al-Maghrib (Poste Maroc) i Geopost. Doręcza przesyłki e-commerce w całym Maroku pod wskazany adres lub do jednej ze swoich agencji. Jeśli sklep wybrał płatność przy odbiorze, płacisz kurierowi przy doręczeniu gotówką lub kartą bankową. Paczkę sprawdzisz w sekcji „Suivre mon colis” na [chronodiali.ma](https://chronodiali.ma/suivi_colis.html) lub w polu powyżej. Numer podaje sklep, który nadał paczkę; własny przykład Chronodiali zaczyna się od `CHPU`.

## Twój numer śledzenia Chronodiali

Wzory rozpoznawane przez Peek, z wymyślonymi cyframi:

| Numer | Co to jest |
| --- | --- |
| `CHPU4829103756182947305` | `CHPU` i 19 cyfr: numer Chronodiali. Peek rozpoznaje go natychmiast. |
| `CD4829103756` | `CD` i 10 cyfr. Peek może śledzić go w Chronodiali, ale ten sam kształt pasuje do [Colis Privé](carrier:colis-prive), Aramex Australia i [Aramex Nowa Zelandia](carrier:aramex-nz), więc Peek pyta wszystkie cztery firmy (zobacz niżej). |
| `chronodiali.ma/suivi_colis.html?id=…` | Link do strony śledzenia Chronodiali. Peek rozpoznaje Chronodiali niezależnie od zawartego numeru. |

Chronodiali to nie [Chronopost](carrier:chronopost), francuski kurier ekspresowy: ich numery i systemy śledzenia są odrębne. Nie masz numeru? Zapytaj sklep albo zajrzyj do poradnika [gdzie znaleźć numer śledzenia](guide:find-tracking-number).

## Statusy śledzenia Chronodiali po polsku

Chronodiali zapisuje komunikaty po francusku. W kolejności, w jakiej paczka zwykle je otrzymuje:

| Chronodiali podaje | Peek pokazuje | Co to oznacza dla ciebie |
| --- | --- | --- |
| `Les données du colis ont été intégrées dans nos systèmes.` | Zarejestrowana | Sklep przesłał dane paczki do Chronodiali, ale firma jeszcze jej nie ma. |
| `Le ramassage a été programmé.` | Zarejestrowana | Zaplanowano odbiór w sklepie. |
| `Le ramassage est en attente, le livreur se présentera sous peu.` | Zarejestrowana | Oczekiwanie na odbiór paczki przez kuriera od nadawcy. |
| `Le colis a été collecté par le livreur.` | Przyjęta przez przewoźnika | Kurier odebrał paczkę. |
| `Le colis est arrivé au hub d'origine.` | W transporcie | Dotarła do pierwszego hubu Chronodiali. |
| `Le colis a quitté notre hub.`, `Le colis est arrivé au hub.` | W transporcie | W ruchu między hubami Chronodiali. |
| `Delivery Scheduled` | W transporcie | Przypisana do trasy doręczeń w hubie docelowym. |
| `Out For Delivery`, `Le colis est en cours de livraison.` | W doręczeniu | Jest dziś u kuriera. Przy pobraniu przygotuj należną kwotę. |
| `Le colis a été livré.` | Dostarczona | Doręczona. |
| `La livraison a échoué : le client n'a pas répondu ou était injoignable.` | Próba doręczenia | Kurier nie zdołał się z tobą skontaktować telefonicznie ani pod drzwiami. |
| `La livraison a échoué : le lieu de livraison est incorrect ou introuvable.` | Próba doręczenia | Adres jest błędny lub kurier nie mógł go znaleźć. |
| `La livraison a échoué : le client est indisponible.` | Próba doręczenia | Nie byłeś dostępny. |
| `La livraison a échoué : le client n'a pas le montant COD requis.` | Próba doręczenia | Brak wymaganej kwoty za pobraniem (COD). |
| `La livraison a échoué : le client a refusé le colis.` | Próba doręczenia | Odmowa przyjęcia przesyłki. |
| `La livraison a échoué.` | Próba doręczenia | Doręczenie nieudane bez podanej przyczyny. |
| `Le colis est retenu au hub.` | Zgłoszono problem | Przesyłka jest wstrzymana w hubie. |
| `La livraison a été annulée : le colis est endommagé et ne peut pas être livré.` | Zgłoszono problem | Paczka jest uszkodzona i nie zostanie doręczona. |
| `Le colis a été annulé.` | Zgłoszono problem | Przesyłka została anulowana. |
| `Le colis va être renvoyé à l'expéditeur.` | Zwrócona nadawcy | Wraca do sklepu. |
| `Le colis a été retourné à l'adresse de l'expéditeur.` | Zwrócona nadawcy | Wróciła pod adres sklepu. |

**Brak na chronodiali.ma.** Oficjalna strona śledzenia pomija część powyższych wpisów: `Delivery Scheduled`, `Out For Delivery`, nieudane próby, anulowane lub przełożone dostawy oraz `Le colis a été confié à un partenaire tiers.` Peek je wyświetla, więc jego historia bywa pełniejsza niż na chronodiali.ma.

**Inne wpisy.** `La livraison a été reprogrammée : une nouvelle date a été définie.` oznacza nowy termin dostawy; `La livraison a été reprogrammée.` nie podaje szczegółów. `La livraison a été annulée à la demande du client.` to anulowanie na prośbę klienta. `Le colis est en attente au hub.` oznacza oczekiwanie w hubie. Informacje o dwóch wpisach z `partenaire tiers` znajdziesz w pytaniach poniżej; Peek pokazuje oba jako „W transporcie”.

**Paczki z zagranicy.** Część przesyłek trafia do Maroka z innego kraju. Wtedy mogą pojawić się `arrivé dans le pays.` (dotarła do Maroka; Peek pokazuje „W transporcie”), `dédouanement en cours` (odprawa celna w toku; „W urzędzie celnym”) oraz `dédouanement terminé` (zwolniona z cła; „W transporcie”). Strona Chronodiali nie podaje zasad opłat celnych ani kto je reguluje: zapytaj sklep i zobacz [przesyłki zatrzymane na cle](guide:customs).

## Doręczenie Chronodiali: płatność przy odbiorze, trzy próby i agencje

Chronodiali deklaruje doręczenia w całym Maroku do ponad 300 miejscowości, pod drzwi lub do agencji.

- **Płatność przy odbiorze:** strona śledzenia pokazuje kwotę do zapłaty (`Montant à payer`) w dirhamach (DH). Według Chronodiali zapłacisz przy doręczeniu gotówką lub kartą bankową.
- **Trzy próby:** Chronodiali wymienia „3 Tentatives de livraison” wśród swoich usług. W Peek każda nieudana próba pojawia się jako wpis zaczynający się od `La livraison a échoué`, z powodem lub bez.
- **Miej włączony telefon:** jednym z powodów nieudanego doręczenia jest brak odpowiedzi lub brak kontaktu.
- **Powiadomienia:** strona usług wymienia powiadomienia SMS i e-mail oraz informowanie klienta na każdym etapie.
- **Agencje:** Agadir, Casablanca, Fez, Marrakesz, Rabat i Tanger, czynne od poniedziałku do piątku 9:00–18:00 i w soboty 9:00–13:00 (czasu marokańskiego). Adresy znajdują się na stronie „Points de livraison”.

Strona nie określa, jak długo agencja przechowuje paczkę ani co dzieje się po trzeciej nieudanej próbie: zapytaj Chronodiali lub sklep. Nie zastałeś kuriera? Zobacz [nieudane doręczenia](guide:missed-delivery).

## Paczka Chronodiali opóźniona, odrzucona, zwrócona lub nieotrzymana

W sprawie zwrotu pieniędzy lub ponownej wysyłki zwróć się do sklepu: to sklep ma umowę z Chronodiali i zamawia odbiory przez swoją platformę.

- **Brak ruchu po `Les données du colis ont été intégrées dans nos systèmes.`:** sklep utworzył wysyłkę, ale Chronodiali jeszcze nie odebrało paczki. Zapytaj sklep, kiedy ją przekaże. Jeśli chronodiali.ma pokazuje `En cours de traitement.`, Chronodiali zna numer, ale nie ma jeszcze żadnego skanu.
- **Opóźnienie:** Chronodiali nie podaje stałych czasów dostawy. Regulamin wskazuje, że termin podawany jest przy zamówieniu i zależy od kierunku. Gdy najnowszy wpis ma cztery dni lub więcej, a paczka wciąż jest na etapie „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie”, Peek oznacza ją dniem tego wpisu („Brak aktualizacji od …”) i dodaje link „Poproś przewoźnika Chronodiali o pomoc”. Zobacz [śledzenie, które się nie aktualizuje](guide:tracking-not-updating).
- **Odrzucona lub nieopłacona:** jeśli odmówisz odbioru albo nie możesz zapłacić, kurier rejestruje nieudaną próbę, a paczka może wrócić do sklepu: `Le colis va être renvoyé à l'expéditeur.`, a potem `Le colis a été retourné à l'adresse de l'expéditeur.` Zobacz [zwrócona do nadawcy](guide:returned-to-sender).
- **Doręczona, ale nieotrzymana:** Chronodiali udostępnia sklepom cyfrowe potwierdzenie doręczenia w ich panelu klienta. Poproś sklep o sprawdzenie. Więcej w [doręczona, ale nieotrzymana](guide:delivered-not-received).
- **Uszkodzona lub zaginiona:** powiadom najpierw sklep. Możesz też zaznaczyć „Réclamation ?” w formularzu kontaktowym Chronodiali albo zadzwonić lub napisać z numerem przesyłki. Zobacz [uszkodzone paczki](guide:damaged-parcel) i [zaginione paczki](guide:lost-parcel).
- **Wiadomość z żądaniem opłaty:** na swojej stronie Chronodiali opisuje płatność jako dokonywaną przy doręczeniu, gotówką lub kartą. Jeśli SMS lub e-mail wymaga wpłaty przez link, najpierw sprawdź śledzenie i zobacz [fałszywe SMS-y o paczkach](guide:delivery-scam-texts).

## Numer kontaktowy i obsługa klienta Chronodiali

Dane ze strony chronodiali.ma:

- **Telefon:** +212 5 20 61 62 63 (05 20 61 62 63 z Maroka).
- **E-mail:** service-client@chronodiali.ma.
- **Formularz kontaktowy:** na stronie [Contact](https://chronodiali.ma/contact_us.html) wybierz „Réclamation ?” dla reklamacji lub „Demande d'information ?” dla pytania. Formularz prosi wyłącznie o imię i nazwisko, e-mail, telefon i nazwę sklepu, bez pola na treść wiadomości czy numer śledzenia; Chronodiali deklaruje kontakt zwrotny.
- **Agencje:** sześć wymienionych wyżej miast, pod tym samym numerem telefonu i adresem e-mail.

Strona nie podaje osobnych godzin działania infolinii ani numeru WhatsApp.

## Śledzenie paczki Chronodiali w Peek

Wklej numer `CHPU`, a [Peek](/) od razu rozpozna Chronodiali w polu „Wykryty przewoźnik”. Małe litery i spacje nie przeszkadzają. Działa również link do strony śledzenia chronodiali.ma.

10-cyfrowy numer `CD` sam nie wskazuje jednoznacznie przewoźnika. Wklej go mimo to: Peek obsługuje ponad 4 500 przewoźników. Wyświetla „Sprawdzanie usług śledzenia…”, pytając Chronodiali i pozostałych przewoźników używających tego formatu; jeśli tylko Chronodiali ma paczkę, jego nazwa pojawia się z opisem „ma tę przesyłkę”. Możesz też kliknąć „Zmień” i wybrać „Chronodiali” w sekcji „Pasują do numeru”. Jeśli wybierzesz Chronodiali dla numeru w innym kształcie, Peek poinformuje, że Chronodiali „nie wydaje takich numerów”, a paczka pokaże „Sprawdź numer śledzenia”.

Peek odczytuje bezpośrednio śledzenie Chronodiali: status, każdy wpis w oryginalnym brzmieniu Chronodiali (po francusku, a w dwóch powyższych przypadkach po angielsku), nazwę hubu jako miejsce, kraj docelowy i godzinę doręczenia. Jeśli Chronodiali je zwraca, Peek pokazuje ci też imię i nazwisko odbiorcy pod „Odbiorca” oraz, dopóki paczka jest w drodze, miasto docelowe na mapie, jeśli Peek umiejscowi je w Maroku; osoba, której udostępnisz paczkę, nie widzi ani jednego, ani drugiego, a nazwisko osoby, która podpisała odbiór, pozostaje prywatne: żadna aplikacja go nie pokazuje. Godziny wyświetlane są w strefie czasowej twojego urządzenia. Pomijane są nazwa i miasto nadawcy, kwota do zapłaty, potwierdzenie doręczenia oraz połączenia kuriera. Chronodiali nie podaje przewidywanej daty dostawy ani wagi. Wpisy o firmie partnerskiej nie podają jej nazwy, a Peek nie przechodzi na śledzenie tego partnera.

Jeśli Chronodiali nie zna jeszcze numeru, Peek wyświetla „Oczekiwanie na przewoźnika Chronodiali” i sprawdza dalej; jeśli po dwóch dniach Chronodiali nadal go nie zna, Peek prosi cię o sprawdzenie numeru, wybór innego przewoźnika lub usunięcie paczki. Gdy paczka jest otwarta w Peek albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza ją nawet co 10 minut, a co 2 minuty od 6:00 czasu marokańskiego w dniu, w którym Chronodiali skanuje ją w doręczeniu, dopóki śledzenie Chronodiali odpowiada. Po dwóch dniach bez nowości (12 godzinach przy „Zarejestrowana” lub „Przyjęta przez przewoźnika”) sprawdza co godzinę, a po siedmiu dniach bez nowości przy „Zarejestrowana” co 6 godzin. Możesz ustawić powiadomienia: „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek nie może dokonać płatności, przełożyć dostawy ani skontaktować się za ciebie z Chronodiali lub ze sklepem.

## Pytania o śledzenie Chronodiali

### Czy Chronodiali należy do Poste Maroc?

Częściowo. Strona aktualności Chronodiali opisuje ją jako spółkę joint venture: 51 % należy do Barid Al-Maghrib (Poste Maroc), a 49 % do Geopost. Ma własne śledzenie na chronodiali.ma i własną obsługę klienta.

### Ile prób doręczenia podejmuje Chronodiali?

Trzy razy, jak podaje Chronodiali. W Peek każda nieudana próba pojawia się jako wpis zaczynający się od `La livraison a échoué`, z powodem lub bez niego.

### Czy za paczkę Chronodiali można zapłacić kartą?

Tak. Chronodiali informuje, że przy doręczeniu można zapłacić gotówką lub kartą bankową.

### Co oznacza „partenaire tiers” w śledzeniu Chronodiali?

`Le colis a été assigné à un partenaire tiers.` i `Le colis a été confié à un partenaire tiers.` oznaczają, że Chronodiali przydzieliło lub przekazało paczkę firmie partnerskiej. Śledzenie nie podaje nazwy partnera: poczekaj na kolejny wpis albo zapytaj Chronodiali, podając numer.

### Jak skontaktować się z Chronodiali w sprawie paczki?

Zadzwoń pod +212 5 20 61 62 63 lub napisz na service-client@chronodiali.ma z numerem śledzenia. O zwrot pieniędzy lub ponowną wysyłkę poproś sklep.

:::sources
- [Chronodiali: Les experts de la livraison e-commerce](https://chronodiali.ma/) – Barid Al-Maghrib i Geopost, płatność przy odbiorze gotówką lub kartą, dostawa pod adres lub do agencji, ponad 300 miejscowości, agencje i godziny, telefon, e-mail
- [Chronodiali: Suivre mon colis](https://chronodiali.ma/suivi_colis.html) – śledzenie, przykład `CHPU`, kwota do zapłaty, francuskie statusy, co wyświetla strona i „En cours de traitement.” (w skrypcie strony)
- [Chronodiali: Nos services](https://chronodiali.ma/nos_service.html) – trzy próby doręczenia, powiadomienia SMS i e-mail, gotówka lub karta, cyfrowe potwierdzenie doręczenia
- [Chronodiali: Points de livraison](https://chronodiali.ma/point_de_livraison.html) – dostawa w całym Maroku, agencje i godziny otwarcia
- [Chronodiali: Envoyer un colis](https://chronodiali.ma/envoyercolis.html) – konta i odbiory sklepów, potwierdzenia doręczenia dla sklepów
- [Chronodiali: Contact](https://chronodiali.ma/contact_us.html) – formularz kontaktowy z „Réclamation ?” i jego polami, telefon, e-mail
- [Chronodiali: Gouvernance](https://chronodiali.ma/aboutus.html) – Barid Al-Maghrib (Poste Maroc)
- [Chronodiali: Actualités](https://chronodiali.ma/actualites.html) – spółka joint venture, 51 % Barid Al-Maghrib i 49 % Geopost
- [Chronodiali: Mention légale](https://chronodiali.ma/mentions_legal.html) – Chrono Diali S.A., Casablanca; regulamin: umowa i odbiory sklepu, czasy dostawy podawane przy zamówieniu
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formaty numerów, linie statusów i ich etapy, co pokazują strona Chronodiali i Peek
:::
