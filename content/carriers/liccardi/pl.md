---
title: Śledzenie Liccardi Express Courier: kody LDV i pomoc
description: Śledź przesyłkę Liccardi Express Courier (F.lli Liccardi) we Włoszech: kody 660 i LDV, skany oddziałów i automatów, giacenza oraz obsługa klienta.
slug: sledzenie-liccardi
published: 2026-11-24
updated: 2026-11-24
---

**Liccardi Express Courier** (*F.lli Liccardi S.r.l.*) to włoski krajowy kurier ekspresowy z siedzibą w Casandrino koło Neapolu ([liccarditrasporti.com](https://www.liccarditrasporti.com/)). Uruchomiona w obecnej formie w 1992 roku z rodzinnego przedsiębiorstwa transportowego założonego trzydzieści lat wcześniej przez Giovanniego Liccardiego, firma rozwinęła się z przewozu dokumentów bankowych w latach 90. w ogólnokrajową dystrybucję paczek B2C i e-commerce, obsługując ponad 5,9 miliona przesyłek rocznie w przeszło 20 włoskich oddziałach i hubach, m.in. w Neapolu, Rzymie, Mediolanie, Bolonii, Turynie, Florencji, Weronie, Bari i Salerno. Przesyłkę sprawdzisz na stronie Liccardi lub wklejając 13-cyfrowy kod etykiety `660` albo 7- lub 8-cyfrowy numer *lettera di vettura* (LDV) w pole powyżej.

## Numery śledzenia Liccardi Express Courier (etykieta `660` i LDV)

Liccardi oznacza każdą przesyłkę dwoma numerami — 13-cyfrowym kodem kreskowym paczki zaczynającym się od `66` (zwykle `660`) oraz krótszym 7- lub 8-cyfrowym numerem przesyłki (*lettera di vettura* / LDV):

| Format (wymyślony) | Co to jest |
| --- | --- |
| `6604829173605` | 13-cyfrowy kod kreskowy paczki Liccardi (`660` + 8-cyfrowy numer przesyłki + 2 cyfry kontrolne/paczki). |
| `48291736` | 7- lub 8-cyfrowy numer przesyłki Liccardi (*LDV* / *lettera di vettura*). |

Sprawdź e-mail wysyłkowy sklepu lub powiadomienie Liccardi, aby znaleźć jeden z tych kodów; oba wskazują tę samą przesyłkę w systemie Liccardi. Zobacz [formaty numerów przesyłek](guide:tracking-number-formats).

## Co oznaczają statusy śledzenia Liccardi Express Courier

Tracker Liccardi publikuje liczbowe kody wyników (*codici esito*) wraz z włoskimi opisami z oddziałów. W przypadku przesyłek wielopaczkowych (*colli*) Liccardi rejestruje zdarzenia dla każdej paczki z osobna; Peek utrzymuje przesyłkę jako aktywną, dopóki wszystkie paczki nie zostaną doręczone:

| Liccardi podaje (kod) | Co to oznacza dla Ciebie | Peek pokazuje |
| --- | --- | --- |
| `DATI SPEDIZIONE TRASMESSI A LICCARDI` (`50`), `SERVIZIO PROGRAMMATO` (`58`), `RITIRO ASSEGNATO AD AUTISTA` (`36`), `IN RITIRO` (`92`) | Nadawca przekazał dane przesyłki albo odbiór przez kierowcę został zaplanowany lub trwa przed przyjęciem. | „Zarejestrowana” |
| `SPEDIZIONE RITIRATA` (`37`), `COLLO PRESO IN CARICO` (`3`), `SPEDIZIONE PRESA IN CARICO` (`4`) | Paczka odebrana od nadawcy i przyjęta do sieci Liccardi. | „Przyjęta przez przewoźnika” |
| `ARRIVATO IN FILIALE` (`47`), `IN TRANSITO` (`48`), `SPEDIZIONE PARTITA` (`91`), `ARRIVO SU FILIALE DI DESTINO` (`72`), `IN GESTIONE` (`49`), `GIACENZA SVINCOLATA` (`70`), `IN PRENOTAZIONE` (`33`) | W drodze między hubami, w oddziale docelowym, w trakcie umawiania terminu lub zwolniona z magazynu. | „W transporcie” |
| `CARICO VETTURA` (`51`), `CARICO VETTURA CONFERMATO` (`95`), `IN CONSEGNA` (`52`) | Załadowana do lokalnego samochodu dostawczego i w dzisiejszym doręczeniu. | „W doręczeniu” |
| `CONSEGNATA AL PUDO` (`6`), `CHECK-IN AL PUDO` (`80`), `SPEDIZIONE DEPOSITATA NEL LOCKER` (`11`), `FERMO DEPOSITO` (`32`) | Gotowa do odbioru w punkcie PUDO, automacie paczkowym lub lokalnym oddziale Liccardi (*fermo deposito*). | „Do odbioru” |
| `ASSENTE` (`28`), `INDIRIZZO ERRATO` (`40`), `CONSEGNA NON ESEGUITA` (`43`), `MANCA CONTRASSEGNO` (`44`), `INDIRIZZO NON TROVATO` (`45`), `CHIUSO` (`46`), `GIRO DI CONSEGNA NON ULTIMATO` (`67`) | Doręczenie nie powiodło się (nieobecność odbiorcy, błędny adres, brak pobrania, zamknięte lub nieukończona trasa). | „Próba doręczenia” |
| `IN GIACENZA` (`35`), `COLLO DANNEGGIATO` (`30`), `MERCE RESPINTA` (`42`), `SPEDIZIONE SMARRITA` (`54`), `COLLO DISGUIDATO` (`68`), `ORDINE SOSPESO: IN ATTESA DI ISTRUZIONI MITTENTE` (`76`), `DISCONOSCIMENTO FIRMA` (`103`), `CONTATTARE ASSISTENZA` (`107`) | Przesyłka wstrzymana w magazynie w oczekiwaniu na dyspozycje nadawcy (*giacenza*), uszkodzona, odmówiona, błędnie skierowana, zaginiona lub wymagająca wsparcia. | „Zgłoszono problem” |
| `RIENTRO AL MITTENTE` (`31`), `IN CONSEGNA AL MITTENTE` (`53`), `RESO A MITTENTE` (`8`) | W drodze powrotnej lub zwrócona do nadawcy. | „Zwrócona nadawcy” |
| `SPEDIZIONE CONSEGNATA` (`1`), `SPEDIZIONE CONSEGNATA A COMMITTENTE` (`5`), `SPEDIZIONE RITIRATA DA LOCKER DAL DESTINATARIO` (`12`) | Doręczona odbiorcy lub odebrana z automatu paczkowego. | „Dostarczona” |

Pamiętaj, że własny pasek postępu Liccardi traktuje umieszczenie w automacie (`11`) jako doręczenie, natomiast Peek przypisuje skany PUDO, automatu i *fermo deposito* do etapu **„Do odbioru”**, dopóki odbiorca faktycznie nie odbierze przesyłki (`12`).

## Czasy doręczeń, usługi i włoska sieć oddziałów

Według [Usług Liccardi](https://www.liccarditrasporti.com/servizi/), [O firmie](https://www.liccarditrasporti.com/azienda/) i [Oddziałów](https://www.liccarditrasporti.com/filiali/):

- **Czasy tranzytu:** Liccardi odbiera zamówienia od sklepów w ciągu 48 godzin, waży, mierzy i fotografuje każdą paczkę w sortowniach oraz deklaruje krajowe doręczenie w **24–48 godzin** (statystycznie w **72 godziny** na całym obszarze kraju; odległe miejscowości — *località disagiate* — i szczyty sezonowe mogą wydłużyć ten czas).
- **Opcje doręczenia:** standardowy ekspres B2C, wniesienie na piętro (*consegne al piano*), terminy umówione (*consegne tassative*), doręczenie tego samego dnia, pobranie (*contrassegno*), odbiór w oddziale (*fermo deposito*), odbiory wymienne/zwrotne oraz opcjonalne ubezpieczenie dodatkowe.
- **Sieć oddziałów:** ponad 20 hubów i oddziałów w całych Włoszech, w tym centralny hub Casandrino/Neapol (`NA1`), Rzym (`RM1`, `RM3`), Mediolan (`MI1`), Bolonia (`BO1`), Turyn (`TO2`), Florencja (`FI1`), Werona (`VR1`), Bari (`BA1`), Salerno (`SA1`), Avellino (`AV1`), Frosinone (`FR1`), Latina (`LT1`), Modena (`MO1`), Parma (`PR1`), Foggia (`FG1`), Lecce (`LE2`), Matera (`MT1`), Potenza (`PZ1`), Cosenza (`CS1`), Catanzaro (`CZ1`) i Reggio Calabria (`RC1`). Gdy paczkę przejmuje inny kurier, tracker Liccardi potrafi wyświetlić jego list przewozowy (LDV).

## Nieudane doręczenie, wstrzymanie *giacenza* i reklamacje

- **`IN GIACENZA` (`35`) lub nieudane doręczenie (`28`, `40`, `45`):** po nieudanej próbie lub błędzie adresu Liccardi kieruje przesyłkę do *giacenza* (magazyn) i czeka na dyspozycję zwolnienia (*svincolo giacenza*) od **nadawcy**. Skontaktuj się najpierw ze sklepem, aby wydał zwolnienie, albo skorzystaj z portalu dla odbiorców [Assistenza / Reclami Destinatari](https://www.liccarditrasporti.com/assistenza-reclami-destinatari/), aby zaktualizować wskazówki doręczenia, zgłosić błąd adresu lub zamówić odbiór w oddziale (*ritiro in filiale*).
- **Kwestionowanie wyniku (`Contestazione Esito`) lub brak przesyłki:** przewodnik reklamacji Liccardi pozwala zgłosić błędny status, wpis „odbiorca nieobecny” mimo obecności w domu albo paczkę oznaczoną jako dostarczona, która nie dotarła, i udostępnia formularze *Modulo reclamo*, *Modulo conciliazione* oraz *Formulario CP*. Zawsze poinformuj też sprzedawcę. Zobacz [dostarczona, ale nieodebrana](guide:delivered-not-received), [uszkodzona przesyłka](guide:damaged-parcel), [zaginiona przesyłka](guide:lost-parcel), [zwrócona nadawcy](guide:returned-to-sender) oraz [dlaczego śledzenie przestaje się aktualizować](guide:tracking-not-updating).

## Obsługa klienta i kontakty Liccardi Express Courier

Oficjalne dane kontaktowe opublikowane na [Liccardi Contatti](https://www.liccarditrasporti.com/contatti/) i [Assistenza / Reclami Destinatari](https://www.liccarditrasporti.com/assistenza-reclami-destinatari/):

- **Bezpłatny numer (*Numero Verde* we Włoszech):** `800 029 764` (`800029764`).
- **Telefon:** `+39 081 5053642` (faks `081 5053071`).
- **E-mail obsługi klienta:** `assistenzaclienti@liccarditrasporti.com` (zapytania ogólne: `info@liccarditrasporti.com`; PEC: `flliliccardi@legalmail.it`).
- **Portal pomocy i reklamacji dla odbiorców:** [liccarditrasporti.com/assistenza-reclami-destinatari/](https://www.liccarditrasporti.com/assistenza-reclami-destinatari/).
- **Siedziba i centralny hub:** *F.lli Liccardi S.r.l.* (P.IVA `06588280633`), Via Paolo Borsellino 133 (siedziba rejestrowa 123), 80025 Casandrino (NA), Włochy.

> Uważaj na fałszywe SMS-y żądające drobnej opłaty online za zmianę terminu doręczenia włoskiego kuriera. Każdą informację o doręczeniu sprawdzaj bezpośrednio u sprzedawcy albo przez oficjalną stronę i kanały obsługi Liccardi. Zobacz [fałszywe SMS-y o doręczeniu](guide:delivery-scam-texts).

## Śledzenie przesyłki Liccardi Express Courier w Peek

Gdy wpiszesz 13-cyfrowy kod kreskowy `660` (`6604829173605`, wymyślony), [Peek](/) pyta Liccardi Express Courier w pierwszej kolejności wszędzie poza Rosją, gdzie najpierw pyta Ozon, i pokazuje „Liccardi Express Courier ma tę przesyłkę.”, gdy tracker Liccardi to potwierdzi. Peek rozpoznaje również jako Liccardi wklejony link `my.liccarditrasporti.com/segui?ldv=`. 7- lub 8-cyfrowy numer LDV (`48291736`, wymyślony) sam w sobie nie wskazuje żadnego przewoźnika: wklej link albo stuknij „Zmień” podczas dodawania przesyłki lub „Zmień przewoźnika” na jej stronie i wybierz **Liccardi Express Courier** (wyszukanie „Liccardi Trasporti”, „Liccardi Express” albo „F.lli Liccardi” też go znajduje).

Peek czyta tracker Liccardi i wyświetla etap („Zarejestrowana”, „Przyjęta przez przewoźnika”, „W transporcie”, „W doręczeniu”, „Do odbioru”, „Próba doręczenia”, „Zgłoszono problem”, „Zwrócona nadawcy”, „Dostarczona”), włoski opis Liccardi oraz oddział. Kody `codEsito` wyznaczają etap, lecz żadna aplikacja Peek ich nie pokazuje. Peek datuje skany według czasu włoskiego i wyświetla je w strefie Twojego urządzenia. Pokazuje także szacowany dzień doręczenia, jeśli Liccardi go podaje (bez przedziału godzinowego), godzinę doręczenia po dostawie oraz status podsumowujący przed pierwszym skanem paczki. Gdy Liccardi poda numer listu innego kuriera, a Peek go rozpozna, sprawdza, czy ma tę przesyłkę: ekran pokazuje wtedy „Doręcza:” z nazwą kuriera oraz oba numery. W przesyłce wielopaczkowej Peek scala powtórzone skany oddziału z jednej minuty i utrzymuje przesyłkę otwartą do doręczenia wszystkich paczek. Peek nie wyświetla miejscowości ani kodu pocztowego odbiorcy, nazwisk kierowców ani podpisów potwierdzenia odbioru.

Gdy masz przesyłkę otwartą w Peek albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza status nawet co 10 minut; w dniu, gdy Liccardi pokazuje `CARICO VETTURA` (`51`), `CARICO VETTURA CONFERMATO` (`95`) lub `IN CONSEGNA` (`52`), co 2 minuty od godz. 6:00 czasu włoskiego, dopóki własny tracker Liccardi odpowiada. W pozostałym czasie Peek sprawdza co godzinę. Po 12 godzinach wyłącznie na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika” albo po dwóch dniach bez zmian sprawdza co godzinę niezależnie od sposobu śledzenia, a po siedmiu dniach na „Zarejestrowana” — co 6 godzin. Gdy tracker Liccardi nie odpowiada i Peek śledzi przesyłkę wyłącznie przez usługi śledzące, sprawdza co godzinę, co 3 godziny po spokojnym dniu i nawet co 15 minut w dniu doręczenia, dopóki masz ją otwartą lub włączone powiadomienia.

Jeśli najnowszy skan ma cztery dni, a przesyłka nadal widnieje jako „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie”, Peek oznacza ją dniem tego skanu, np. „Brak aktualizacji od pon., 12 paź”. Na stronie przesyłki pojawia się wtedy komunikat „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika Liccardi Express Courier.” wraz z linkiem „Poproś przewoźnika Liccardi Express Courier o pomoc” do strony pomocy i reklamacji dla odbiorców Liccardi. Po trzech tygodniach bez nowego skanu pojawia się informacja „Od ponad trzech tygodni nic nowego. Jeśli przesyłka wciąż nie dotarła, zapytaj sprzedawcę. Peek dalej pyta przewoźnika Liccardi Express Courier.”

Możesz wybrać powiadomienia dla opcji „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek śledzi wyłącznie aktualizacje statusu i nie może zwolnić *giacenza*, zmienić adresu doręczenia ani skontaktować się za Ciebie z Liccardi Express Courier.

## Pytania o śledzenie Liccardi Express Courier

### Jak wygląda numer śledzenia Liccardi?

Liccardi używa 13-cyfrowego kodu kreskowego etykiety zaczynającego się od `66` (zwykle `660`, np. `6604829173605`, wymyślony) albo 7- lub 8-cyfrowego numeru przesyłki (*LDV*, np. `48291736`, wymyślony).

### Co oznacza `IN GIACENZA` w śledzeniu Liccardi?

`IN GIACENZA` (kod `35`) oznacza, że paczka jest wstrzymana w oddziale Liccardi po nieudanym doręczeniu lub problemie z adresem i wymaga dyspozycji zwolnienia (*svincolo*) od nadawcy. Skontaktuj się ze sprzedawcą i przekaż zaktualizowane dane doręczenia przez [Assistenza / Reclami Destinatari](https://www.liccarditrasporti.com/assistenza-reclami-destinatari/).

### Ile trwa doręczenie Liccardi Express Courier we Włoszech?

Liccardi deklaruje krajowe doręczenie w **24–48 godzin** od odbioru (statystycznie w **72 godziny** w całej swojej sieci we Włoszech), z możliwym wydłużeniem dla odległych miejscowości (*località disagiate*) i szczytów sezonowych.

### Jak skontaktować się z obsługą klienta Liccardi Express Courier?

Zadzwoń na włoski bezpłatny numer `800 029 764` lub `+39 081 5053642`, napisz na `assistenzaclienti@liccarditrasporti.com` albo wyślij zgłoszenie lub reklamację odbiorcy na [liccarditrasporti.com/assistenza-reclami-destinatari/](https://www.liccarditrasporti.com/assistenza-reclami-destinatari/).

:::sources
- [Liccardi Express Courier: oficjalna strona](https://www.liccarditrasporti.com/) – *F.lli Liccardi S.r.l.*, ponad 20 oddziałów, ponad 5,9 mln przesyłek rocznie, centrala w Casandrino (NA) i kontakty
- [Liccardi Express Courier: Azienda](https://www.liccarditrasporti.com/azienda/) – historia od 1992 r. (początki w latach 60.), rozwój B2C od 2004 r. i członkostwo w Unione Industriali
- [Liccardi Express Courier: Servizi](https://www.liccarditrasporti.com/servizi/) – doręczenie 24/48 godz. (do 72 godz.), odbiór w 48 godz., *contrassegno*, *fermo deposito* i automatyczne fotosortowanie
- [Liccardi Express Courier: Filiali](https://www.liccarditrasporti.com/filiali/) – pełna lista ponad 20 włoskich hubów i oddziałów (NA1, RM1, MI1, BO1, TO2, FI1, VR1, BA1 itd.)
- [Liccardi Express Courier: Contatti](https://www.liccarditrasporti.com/contatti/) – `800 029 764`, `+39 081 5053642`, e-maile, PEC i adres w Casandrino
- [Liccardi Express Courier: Assistenza / Reclami Destinatari](https://www.liccarditrasporti.com/assistenza-reclami-destinatari/) – kategorie wsparcia dla odbiorców, spory oraz formularze reklamacji i koncyliacji (adres pomocy linkowany przez Peek)
- [Universal Parcel Scraper: liccardi](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/liccardi) – reguły `660`/LDV, reguła linków `my.liccarditrasporti.com`, pełna mapa `codEsito`, obsługa wielopaczkowa, szacowany dzień doręczenia i pobieranie numeru LDV partnera
:::
