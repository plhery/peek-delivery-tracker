---
title: Śledzenie XDP: numery przesyłek, statusy i doręczenie
description: Śledź przesyłkę XDP w Wielkiej Brytanii: skąd wziąć numer przesyłki, dlaczego XDP wymaga kodu pocztowego, co oznaczają statusy i jak się skontaktować.
slug: sledzenie-xdp
published: 2026-11-22
updated: 2026-11-22
---

XDP (XDP Express) to brytyjska sieć paczkowa i frachtowa doręczająca sklepom duże i ciężkie towary: meble, materace, sprzęt ogrodowy i fitness, często w obsłudze jedno- lub dwuosobowej. Przesyłkę sprawdzisz na [stronie śledzenia XDP](https://xdp.co.uk/track/) lub w aplikacji XDP za pomocą numeru przesyłki (*consignment number*) i kodu pocztowego doręczenia, albo wklejając numer w polu powyżej. Numer otrzymujesz od sklepu nadającego towar, nie od XDP.

## Numer śledzenia XDP: consignment number

XDP nazywa go *consignment number*. Strona śledzenia mówi jasno: dostajesz go od firmy wysyłającej towar przez XDP, więc szukaj w e-mailu wysyłkowym sklepu lub na stronie zamówienia. Własne SMS-y i e-maile od XDP przychodzą później, gdy przesyłka jest już w drodze.

XDP nie publikuje oficjalnego formatu; numery wyglądają tak:

| Kształt (zmyślony) | Co to jest |
| --- | --- |
| `ZDBKLM482917` | `ZDB`, trzy litery i sześć cyfr: numer przesyłki XDP. |
| `0ZDBKLM482917` | Ten sam numer z początkowym `0`, które można pominąć. |

Tracker XDP wymaga także kodu pocztowego doręczenia lub odbioru. Błędny kod pocztowy i literówka w numerze dają tę samą odpowiedź (`Consignment not found for consignment number`), więc sprawdź oba.

Żaden inny brytyjski przewoźnik nie stosuje układu `ZDB`. Numer [DPD UK](carrier:dpd-uk) ma 14 cyfr, a list przewozowy APC Overnight 22 cyfry — jeśli e-mail wskazuje jedną z tych firm, śledź ją tam. Więcej w [formatach numerów śledzenia](guide:tracking-number-formats).

## Co oznaczają statusy śledzenia XDP

Tracker, aplikacja i FAQ XDP są wyłącznie po angielsku. Numery tras i godziny różnią się między przesyłkami.

| Status XDP | Co to znaczy dla ciebie |
| --- | --- |
| `Order received` | Sklep przesłał XDP dane zamówienia, ale XDP nie ma jeszcze towaru. |
| `At Collection Depot` | XDP ma przesyłkę i wiezie ją do oddziału doręczeń. |
| `At Delivery Depot` | W oddziale, który wykona doręczenie. |
| `Allocated to Delivery Vehicle - Route 03` | Przypisana do trasy doręczeń. |
| `Scheduled for Delivery - Route 11 - ETA: 10:51am to 12:51pm` | Powinna dotrzeć dziś w tym dwugodzinnym oknie. |
| `Next stop: On time` | Jesteś następnym przystankiem kierowcy. XDP wyświetla `On time`, `Late by …` lub `Early by …` wobec okna; Peek dodaje `Next stop:`. |
| `Delivered` | Doręczona. |
| `Carded` | Kierowca próbował, ale nikogo nie było, by odebrać. |
| `Consignment Split` | Oddział ma na razie tylko część zamówienia ze sklepu. |
| `Check Address` | Kierowca nie może znaleźć posesji i potrzebuje dodatkowych informacji. |
| `Service Fail` | Zdarzenie niezależne od XDP (awaria, korek, zła pogoda) opóźniło dostawę. |
| `Return` | Wróciła do nadawcy: skontaktuj się ze sklepem. |

Aplikacja XDP nazywa część statusów inaczej (`Delivery scheduled`). Po `Consignment Split` pozostałe elementy docierają według XDP w ciągu kilku najbliższych dni. `RETURN LABEL CREATED` rozpoczyna drogę powrotną do nadawcy, a `Damaged` oznacza, że przesyłka wygląda na uszkodzoną (niżej).

## Dostawa XDP: okna czasowe, nieudane doręczenia i odbiór

XDP wysyła dwugodzinne okno dostawy w dniu doręczenia po załadowaniu auta, zwykle do południa (czasu brytyjskiego). W okresach szczytu oddział może zaplanować je 24 godziny lub więcej wcześniej. Potem przychodzi wiadomość „You Are Next” SMS-em, e-mailem i w aplikacji. Przy dostawach dwuosobowych (2Man) XDP najpierw ustala datę, a dwugodzinne okno wysyła w dniu dostawy. W usługach 1+ i 2Man kierowca może wnieść towar do wybranego pokoju po podpisaniu oświadczenia wysłanego wcześniej przez XDP.

W aplikacji XDP możesz:

- wybrać **Change Delivery Date**, by przełożyć dostawę lub w części przypadków wybrać termin;
- dodać instrukcje doręczenia, np. zostawienie u sąsiada, na ganku, w szopie, garażu lub za bramą;
- zostawić notatki dla kierowcy i podejrzeć potwierdzenie doręczenia.

XDP ostrzega, że wybierając „Left Safe”, przejmujesz pełną odpowiedzialność za towar od chwili doręczenia.

**Nieudane doręczenia.** `Carded` oznacza, że kierowca nikogo nie zastał. XDP deklaruje trzy próby doręczenia przed zwrotem do nadawcy. Jeśli nie zostawiono awiza, kierowca nie wróci tego samego dnia — przełóż termin przez Change Delivery Date.

**Brak punktów odbioru i odbioru z oddziału.** XDP nie ma punktów paczkowych ani automatów, a aplikacja podaje, że nie oferuje odbioru z oddziałów ani hubów. FAQ odsyła w sprawie odbioru do sklepu.

## Przesyłka XDP niedoręczona, opóźniona lub uszkodzona

XDP pracuje dla nadawcy, nie dla ciebie. Przy każdym problemie zaleca najpierw sprawdzenie śledzenia w aplikacji lub na stronie, a potem kontakt z dostawcą (sklepem), który ma umowę i składa reklamacje.

**Opóźniona lub bez aktualizacji.** XDP nie gwarantuje dostawy następnego dnia, zwłaszcza w szczytach: święta, Black Friday i Cyber Monday. Kierowcy stojący w korkach nie zawsze aktualizują zmienione okno. Zobacz [dlaczego śledzenie się nie aktualizuje](guide:tracking-not-updating).

**Uszkodzona.** XDP prosi, by przyjąć dostawę zamiast jej odmawiać i natychmiast zawiadomić nadawcę. Wpisz uszkodzenie na potwierdzeniu odbioru przy podpisie: poradnik procedur XDP dla nadawców mówi, że reklamacja zostanie odrzucona, jeśli podpis nie zawiera zastrzeżeń, a dopiski takie jak „unchecked” lub „unseen” się nie liczą.

**Zaginiona.** Terminy nadawcy w XDP są krótkie: 33 dni od nadania na reklamację zaginięcia lub ubytku oraz 7 dni od doręczenia na uszkodzenie, łącznie z weekendami. Szybko powiadom sklep. Jeśli śledzenie pokazuje `Delivered`, a nic nie masz, zobacz [dostarczona, ale nieotrzymana](guide:delivered-not-received).

**Zwrócona.** Po `Return` XDP zaleca kontakt ze sprzedawcą.

## Kontakt i obsługa klienta XDP

Kanały XDP dla odbiorców:

- **Formularz Delivery Query** na [stronie kontaktu XDP](https://xdp.co.uk/contact/). Formularz „New Business Enquiry” służy wyłącznie sprzedaży.
- **E-mail:** trackingupdates@xdp.co.uk.
- **Czat na żywo** na `xdp.co.uk` i w aplikacji XDP; XDP nie podaje godzin pracy.
- **Formularz kontaktowy w aplikacji**, wymagający imienia, kodu pocztowego, e-maila oraz opcjonalnie numeru przesyłki.

Strona XDP nie podaje numeru telefonu. Aplikacja wspomina o płatnych połączeniach po 7 pensów za minutę bez podania numeru; numery widniejące na innych stronach nie są publikowane przez XDP.

## Śledzenie przesyłek XDP w Peek

[Peek](/) rozpoznaje numer `ZDB` (z początkowym `0` lub bez) od razu jako XDP. Linki z `xdp.co.uk` (`/track/` z numerem lub `track.php?c=`) także działają, ale kod pocztowy z linku nie jest pobierany.

Peek pokazuje dla XDP opcjonalne pole „Delivery or collection postcode”. Bez kodu pocztowego w ogóle nie pyta XDP i śledzi paczkę wyłącznie przez [uniwersalne usługi śledzenia](guide:universal-tracker). Z kodem pocztowym Peek czyta stronę XDP bezpośrednio, przekazując kod do XDP i w razie potrzeby do usług śledzenia. Błędny kod pocztowy daje wynik „nie znaleziono”, tak jak na stronie XDP — popraw go przez „Zmień”.

Godziny XDP nie mają strefy czasowej: Peek odczytuje je w czasie brytyjskim i wyświetla w twojej strefie („Godziny w twojej strefie czasowej:”). Pokazuje historię XDP (bez wierszy z e-mailami/SMS-ami i bez duplikatów dla wielopaczek), wagę, nazwę usługi, nazwisko odbiorcy (w polu „Odbiorca”) i kod pocztowy doręczenia, pomijając kody oddziałów i kierowców, nadawcę, referencje i liczbę sztuk. Nie ma przewidywanej daty, lokalizacji skanów ani osobnego pola okna czasowego (poza wpisami takimi jak `Scheduled for Delivery - Route 11 - ETA: 10:51am to 12:51pm`).

Z kodem pocztowym, dopóki masz przesyłkę otwartą w Peek — lub w dniu doręczenia z włączonymi powiadomieniami — Peek sprawdza bezpośrednio XDP nawet co 10 minut, a od 6:00 czasu brytyjskiego w dniu przejścia w doręczenie nawet co 2 minuty, dopóki tracker XDP odpowiada; poza tym, oraz po dwóch dniach bez wiadomości (12 godzin przy statusie „Zarejestrowana” lub „Przyjęta przez przewoźnika”), co godzinę. Bez kodu pocztowego, tylko przez uniwersalne usługi śledzenia, sprawdza co godzinę, co 3 godziny po dniu bez skanów i nawet co 15 minut w dniu doręczenia, jeśli przesyłka jest otwarta w Peek lub powiadomienia są włączone. Po czterech dniach bez nowego skanu dla przesyłki w drodze Peek pokazuje datę ostatniego skanu („Brak aktualizacji od …”) i „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika XDP.” z linkiem „Poproś przewoźnika XDP o pomoc” do strony kontaktowej. Wysyła alerty według wyboru: „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”.

## Pytania o śledzenie XDP

### Dlaczego moje śledzenie XDP nie działa?

Zazwyczaj z powodu kodu pocztowego: tracker XDP wymaga numeru przesyłki oraz kodu pocztowego doręczenia lub odbioru, a zły kod zwraca „Consignment not found”. Sprawdź oba w e-mailu ze sklepu.

### Ile czasu XDP potrzebuje na dostawę?

XDP podaje nadawcom 1–3 dni dla usługi 1Man oraz 3–5 dni dla 1+ i 2Man, z zastrzeżeniem transportu morskiego na wyspy. Dzień dostawy obejmuje dwugodzinne okno.

### Czy mogę odebrać paczkę w oddziale XDP?

Nie. Aplikacja XDP podaje, że nie oferuje odbioru w oddziałach ani hubach, a FAQ odsyła do sklepu. Możesz przełożyć dostawę lub poprosić o zostawienie jej u sąsiada.

### Czy XDP to prawdziwa firma kurierska?

Tak. X D P Limited widnieje w brytyjskim rejestrze Companies House od 1995 r., z siedzibą w Curdworth koło Sutton Coldfield, i doręcza na kontynentalnej Wielkiej Brytanii, w Irlandii Północnej, na Wyspach Normandzkich i wyspie Wight.

:::sources
- [XDP: Track](https://xdp.co.uk/track/) – numer przesyłki od nadawcy, kod pocztowy, komunikaty trackera
- [XDP: FAQs](https://xdp.co.uk/faqs/) – znaczenie statusów, kontakt z dostawcą, odbiór z oddziału, zmiana terminu w aplikacji
- [XDP: Get In Touch](https://xdp.co.uk/contact/) – formularz Delivery Query, e-maile
- [XDP: About Us](https://xdp.co.uk/about-us/) – założona w 1995 r., zasięg, dwugodzinne okno, potwierdzenie doręczenia
- [XDP: Home](https://xdp.co.uk/) – duże i niestandardowe ładunki, usługi, aplikacja
- [XDP: 1Man Delivery](https://xdp.co.uk/services/1man-delivery/) – 1–3 dni, powiadomienia, telefon pośredniczący
- [XDP: 1+ Delivery](https://xdp.co.uk/services/1-plus-delivery-service/) – 3–5 dni, wybrany pokój, You Are Next, czat na żywo i e-mail
- [XDP: 2Man Delivery](https://xdp.co.uk/services/2-man-delivery/) – umówiona data, dwugodzinne okno
- [XDP: App (web version)](https://app.xdp.co.uk/) – okno dostawy, trzy próby, Change Delivery Date, left safe, brak odbioru z oddziału, szkody, zwroty, połączenia, formularz kontaktowy
- [XDP: Terms and Conditions of Carriage](https://xdp.co.uk/wp-content/uploads/2024/02/XDP-TCFinal.2.24.V1.pdf) – brak gwarancji następnego dnia, okresy szczytu, terminy reklamacji
- [XDP: Procedure Guide](https://xdp.co.uk/wp-content/uploads/2026/03/Procedure-Guide-V.5.pdf) – 33 i 7 dni, wpisywanie uszkodzeń na potwierdzeniu odbioru
- [XDP: Tracking (track.php)](https://www.xdp.co.uk/track.php) – kod pocztowy i komunikaty o nieznalezieniu przesyłki
- [Companies House: X D P LIMITED](https://find-and-update.company-information.service.gov.uk/company/03139661) – rejestracja, siedziba
- [Apple App Store: XDP](https://apps.apple.com/gb/app/xdp/id1485292140) – aplikacja XDP
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – co Peek odczytuje
:::
