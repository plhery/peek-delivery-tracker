---
title: Śledzenie Jadlog: 14 cyfr, Pickup, Predict i statusy
description: Śledź paczkę Jadlog w Brazylii: 14-cyfrowe numery, statusy portugalskie i Geopost, punkty Jadlog Pickup, SMS Predict 1 godz. oraz kontakt.
slug: sledzenie-jadlog
published: 2026-11-24
updated: 2026-11-24
---

Jadlog to jeden z największych brazylijskich operatorów przesyłek ekspresowych i logistyki e-commerce oraz brazylijska spółka europejskiej grupy Geopost (obok DPD, Chronopost, SEUR, BRT i Speedy). W całej Brazylii prowadzi ponad 500 franczyz obejmujących ok. 5 000 gmin, ponad 7 000 pojazdów oraz ponad 4 000 punktów partnerskich Jadlog Pickup. Przesyłkę możesz sprawdzić w polu `RASTREIE SUA ENCOMENDA` na [stronie głównej Jadlog](https://www.jadlog.com.br/jadlog/home) albo wkleić 14-cyfrowy numer w pole powyżej.

## Numer śledzenia Jadlog: 14-cyfrowy format i gdzie go znaleźć

Kodu przesyłki (*código de rastreio* lub *remessa*) szukaj w e-mailu z potwierdzeniem nadania ze sklepu, na ekranie zamówienia na platformie sprzedażowej albo na etykiecie. Numery Jadlog składają się z 14 kolejnych cyfr:

| Format (zmyślony) | Co to jest |
| --- | --- |
| `48291736405821` | 14-cyfrowy numer paczki Jadlog. Dzieli 14-cyfrowy format z europejskimi przewoźnikami Geopost/DPD, takimi jak [DPD Niemcy](carrier:dpd-de), [Chronopost](carrier:chronopost), [SEUR](carrier:seur) i [BRT](carrier:brt). |

Peek śledzi przesyłkę wyłącznie po 14-cyfrowym numerze i nigdy nie pyta o CPF. Jeśli twój numer ma 15 cyfr zaczynających się od `8880`, należy do [J&T Express Brazil](carrier:j-and-t-br); 15-znakowy kod `BR` należy do [SPX Express Brazil](carrier:spx-br); 17-znakowy kod `AJ` należy do [Anjun Logistics](carrier:anjun); a 13-znakowy kod zakończony na `BR` należy do [Correios](carrier:correios-br). Zobacz [formaty numerów przesyłek](guide:tracking-number-formats), aby porównać inne formaty.

## Co oznaczają statusy śledzenia Jadlog

Ponieważ Jadlog należy do grupy Geopost, komunikaty śledzenia pojawiają się po portugalsku na stronie Jadlog albo po angielsku przy odczycie przez API Geopost.

| Jadlog / Geopost pokazuje | Co to oznacza dla ciebie | Peek pokazuje |
| --- | --- | --- |
| `Order created`, `COLETA SOLICITADA` | Sprzedawca utworzył przesyłkę elektronicznie i zamówił odbiór; Jadlog nie zeskanował jeszcze fizycznej paczki. | „Zarejestrowana” |
| `EMISSAO` | Jadlog wystawił dokument przewozowy i przyjął przesyłkę do swojej sieci. | „Przyjęta przez przewoźnika” |
| `Em transferência` | Paczka przemieszcza się między oddziałami franczyzowymi Jadlog, sortowniami i liniami międzyregionalnymi w Brazylii. | „W transporcie” |
| `Em rota de entrega` | Lokalny kurier załadował paczkę i ruszył z nią na twój adres. | „W doręczeniu” |
| `ENTREGUE` | Jadlog zarejestrował paczkę jako doręczoną. | „Dostarczona” |

Gdy sklep drukuje etykiety zbiorczo przed przyjazdem kuriera Jadlog po odbiór, `Order created` albo `COLETA SOLICITADA` potrafi być jedynym wpisem przez dwa dni robocze. Po pojawieniu się `Em transferência` długie trasy drogowe między stanami Brazylii również powodują kilkudniowe przerwy przed skanem w oddziale docelowym.

## Usługi Jadlog: Predict, punkty Pickup i eksport międzynarodowy

Jadlog opisuje swoje główne usługi na stronach [jadlog.com.br/jadlog/servicos](https://www.jadlog.com.br/jadlog/servicos), [Quem Somos](https://www.jadlog.com.br/jadlog/quemsomos) oraz dedykowanych podstronach:

- **Jadlog.com i Jadlog Package:** doręczenia e-commerce (Jadlog.com obsługuje paczki do 30 kg; Jadlog Package obejmuje drogowe przesyłki B2C i B2B).
- **Jadlog Rodo, RodoEconômico, Cargo i DOC:** cięższy transport drogowy do 50 kg (Rodo), ekonomiczny transport drogowy, fracht lotniczy B2B oraz przewóz dokumentów.
- **[Jadlog Predict](https://www.jadlog.com.br/jadlog/jadlogPredict):** usługa w aglomeracjach i rejonach o dużym wolumenie, wysyłająca automatyczny SMS z 1-godzinnym oknem doręczenia.
- **[Jadlog Pickup](https://www.jadlog.com.br/jadlog/pickup):** ponad 4 000 sklepów partnerskich w Brazylii (zasięg ponad 90% populacji) do odbioru, nadawania i zwrotów e-commerce (*Reversa*), z powiadomieniami SMS i e-mail.
- **[Jadlog Internacional](https://www.jadlog.com.br/jadlog/internacional):** wysyłki eksportowe z Brazylii do 154 krajów (300 kierunków) przez międzynarodową sieć Geopost.

**Czego Jadlog nie podaje publicznie.** Strona Jadlog nie publikuje jednego uniwersalnego limitu prób doręczenia, gwarancji czasu dostawy ani stałego czasu przechowywania w punktach Pickup wspólnego dla wszystkich sklepów. Sprawdź SMS lub e-mail wysłany dla twojej paczki Pickup — albo ekran zamówienia w sklepie — pod kątem dokładnego terminu odbioru i zasad ponownego doręczenia.

## Paczka Jadlog utknęła, ma opóźnienie lub nie dotarła

W przypadku zakupów online klientem umownym Jadlog jest sprzedawca i to on odpowiada za zwroty pieniędzy, korekty adresu oraz ponowną wysyłkę.

**Utknęła na `Order created` lub `COLETA SOLICITADA`.** Sprzedawca wygenerował dane przesyłki, ale Jadlog nie odebrał jeszcze paczki ani nie przyjął jej do sieci. Zapytaj sklep, czy paczka opuściła magazyn.

**Cisza w trasie (`Em transferência`).** Przewozy drogowe między stanami Brazylii, weekendy oraz zatory w sortowniach regionalnych regularnie wstrzymują aktualizacje między skanami franczyz. Zobacz [dlaczego śledzenie przestaje się aktualizować](guide:tracking-not-updating).

**Status `ENTREGUE`, ale paczki nie ma.** Najpierw zapytaj domowników, sąsiadów oraz *portaria* (portiernię/recepcję) budynku i sprawdź, czy przy zakupie nie wybrano punktu Jadlog Pickup. Jeśli nikt jej nie odebrał, natychmiast skontaktuj się ze sklepem, by otworzył wyjaśnienie w Jadlog. Zobacz [dostarczona, ale nieodebrana](guide:delivered-not-received).

**Uszkodzona, zagubiona lub zwrócona.** Zachowaj zewnętrzne opakowanie i zrób wyraźne zdjęcia uszkodzeń oraz etykiety przed kontaktem ze sprzedawcą; zobacz [uszkodzona paczka](guide:damaged-parcel), [zagubiona paczka](guide:lost-parcel) i [zwrócona nadawcy](guide:returned-to-sender).

## Kontakt z Jadlog i prawa konsumenta

Strona [Atendimento Jadlog](https://www.jadlog.com.br/jadlog/atendimento) nie publikuje ogólnego telefonu ani adresu e-mail dla odbiorców indywidualnych. Praktyczne opcje:

- **Najpierw napisz do sklepu:** to sprzedawca dysponuje dokumentem przewozowym (`CT-e` / fakturą) wymaganym do wszczęcia reklamacji, korekty adresu lub zwrotu środków.
- **Wyszukiwarka lokalnych franczyz:** właściwy oddział znajdziesz na stronie [Jadlog Unidades e Franquias](https://www.jadlog.com.br/jadlog/unidades), jeśli potrzebujesz informacji o lokalnym magazynie.
- **Wnioski o dane osobowe (LGPD):** Jadlog udostępnia osobny portal pod adresem [jadlog.com.br/jadlog/lgpd](https://www.jadlog.com.br/jadlog/lgpd).
- **Mediacja konsumencka:** gdy sporu z uczestniczącym sklepem lub przewoźnikiem nie da się rozstrzygnąć bezpośrednio, konsumenci w Brazylii ze srebrnym lub złotym kontem gov.br mogą bezpłatnie złożyć skargę przez [Consumidor.gov.br](https://www.consumidor.gov.br/pages/conteudo/publico/1), gdzie uczestniczące firmy zobowiązują się odpowiedzieć w ciągu 10 dni.

> Uważaj na linki w SMS-ach, WhatsAppie i e-mailach wymagające drobnej „opłaty za zwolnienie” albo „ponowne doręczenie” krajowej paczki Jadlog. Każdą niespodziewaną opłatę najpierw potwierdź bezpośrednio w sklepie. Zobacz [fałszywe SMS-y o przesyłce](guide:delivery-scam-texts).

## Śledzenie paczki Jadlog w Peek

Ponieważ 14-cyfrowe numery są współdzielone między przewoźnikami Geopost/DPD na świecie, [Peek](/) kieruje się twoją lokalizacją przy wyborze pierwszego przewoźnika do zapytania. W Brazylii Peek sprawdza Jadlog w pierwszej kolejności wśród 14-cyfrowych kandydatów (przed europejskimi spółkami DPD, Chronopost, SEUR i BRT) i po potwierdzeniu wyświetla „Jadlog ma tę przesyłkę.”. Poza Brazylią Peek traktuje Jadlog jako przewoźnika zapasowego — najpierw sprawdza europejskie firmy Geopost, a Jadlog dopiero gdy żadna nie rozpozna numeru. Wklejenie linku `jadlog.com.br/jadlog/captcha?cte=` wszędzie wskazuje bezpośrednio Jadlog; w innym razie stuknij „Zmień” podczas dodawania lub „Zmień przewoźnika” na ekranie paczki i wybierz Jadlog (wyszukanie „DPD Brazil” też go znajdzie).

Peek odpytuje bezpośrednio publiczne API śledzenia Geopost dla Jadlog bez pytania o kod pocztowy czy CPF. Wyświetla bieżący etap („Zarejestrowana”, „Przyjęta przez przewoźnika”, „W transporcie”, „W doręczeniu”, „Dostarczona”), skróconą historię skanów (po angielsku z API Geopost lub po portugalsku z lokalnego trackera Jadlog) oraz godzinę doręczenia. Przewidywana data dostawy, waga, adres punktu Jadlog Pickup oraz imię lub podpis odbiorcy nie są w Peek pokazywane.

Gdy masz paczkę otwartą w Peek albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza status nawet co 10 minut; w dniu, gdy przesyłka rusza do doręczenia, co 2 minuty od 6:00 czasu lokalnego, dopóki śledzenie Jadlog odpowiada. Poza tym sprawdza co godzinę. Po 12 godzinach wyłącznie na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika”, albo po dwóch dniach bez zmian, sprawdza co godzinę niezależnie od sposobu śledzenia, a po siedmiu dniach na etapie „Zarejestrowana” — co 6 godzin. Gdy śledzenie Jadlog nie odpowiada i Peek korzysta wyłącznie z zewnętrznych usług śledzenia, sprawdza co godzinę, co 3 godziny po dniu ciszy i do co 15 minut w dniu, w którym paczka jest w doręczeniu, gdy masz paczkę otwartą lub masz włączone powiadomienia.

Gdy najnowszy skan ma cztery dni, a przesyłka nadal widnieje jako „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie”, Peek oznacza ją dniem tego skanu, np. „Brak aktualizacji od pon 12 paź”. Na stronie paczki pojawia się wtedy napis: „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika Jadlog.” Po trzech tygodniach bez nowego skanu widnieje komunikat: „Od ponad trzech tygodni nic nowego. Jeśli przesyłka wciąż nie dotarła, zapytaj sprzedawcę. Peek dalej pyta przewoźnika Jadlog.”

Możesz wybrać powiadomienia: „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek tylko monitoruje śledzenie — nie zamówi ponownego doręczenia ani nie skontaktuje się za ciebie z oddziałem Jadlog.

## Pytania o śledzenie Jadlog

### Jak wygląda numer śledzenia Jadlog?

Numer paczki Jadlog ma 14 cyfr (na przykład `48291736405821`, cyfry zmyślone). Możesz go śledzić za pomocą tego 14-cyfrowego kodu w Peek albo na [stronie głównej Jadlog](https://www.jadlog.com.br/jadlog/home).

### Czym jest Jadlog Predict?

[Jadlog Predict](https://www.jadlog.com.br/jadlog/jadlogPredict) to automatyczna usługa powiadomień SMS dostępna w aglomeracjach i rejonach o dużym wolumenie, informująca odbiorców o przewidywanym 1-godzinnym oknie doręczenia.

### Jak działa Jadlog Pickup?

[Jadlog Pickup](https://www.jadlog.com.br/jadlog/pickup) to ponad 4 000 punktów partnerskich w Brazylii, gdzie odbiorcy mogą odbierać paczki lub nadawać zwroty. Jadlog wysyła powiadomienia SMS i e-mail, aby można było śledzić przesyłkę.

### Dlaczego Jadlog jest powiązany z DPD i Geopost?

Jadlog należy do europejskiej grupy Geopost razem z DPD, Chronopost, SEUR, BRT i Speedy. Dlatego korzysta z 14-cyfrowych numerów paczek Geopost i ma dostęp do międzynarodowej sieci Geopost w 154 krajach.

:::sources
- [Jadlog: Home](https://www.jadlog.com.br/jadlog/home) – wyszukiwarka `RASTREIE SUA ENCOMENDA` i przegląd sieci w Brazylii
- [Jadlog: Quem Somos](https://www.jadlog.com.br/jadlog/quemsomos) – członkostwo w grupie Geopost, ponad 500 franczyz, ponad 4 000 punktów Pickup, ponad 7 000 pojazdów, 7 000 pracowników bezpośrednich i pośrednich, 154 kraje
- [Jadlog: Produtos e Serviços](https://www.jadlog.com.br/jadlog/servicos) – Jadlog.com (do 30 kg), Package, Rodo (do 50 kg), RodoEconômico, Cargo, DOC
- [Jadlog: Pickup](https://www.jadlog.com.br/jadlog/pickup) – ponad 4 000 sklepów partnerskich, zasięg >90% populacji, powiadomienia SMS/e-mail i zwroty Reversa
- [Jadlog: Predict](https://www.jadlog.com.br/jadlog/jadlogPredict) – automatyczne SMS-y z 1-godzinnym oknem doręczenia
- [Jadlog: Internacional](https://www.jadlog.com.br/jadlog/internacional) – eksport do 154 krajów / 300 kierunków przez Geopost
- [Jadlog: Unidades e Franquias](https://www.jadlog.com.br/jadlog/unidades) – wyszukiwarka franczyz i oddziałów w Brazylii
- [Jadlog: Atendimento](https://www.jadlog.com.br/jadlog/atendimento) – zakres publicznej strony obsługi
- [Jadlog: LGPD](https://www.jadlog.com.br/jadlog/lgpd) – portal wniosków o ochronę danych osobowych
- [Consumidor.gov.br](https://www.consumidor.gov.br/pages/conteudo/publico/1) – publiczna platforma mediacji konsumenckiej (odpowiedź w 10 dni, konto gov.br srebrne lub złote)
- [Universal Parcel Scraper: jadlog](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/jadlog) – wykrywanie 14 cyfr i priorytet dla Brazylii (alias `DPD Brazil`), reguła linku `captcha?cte=`, publiczne API śledzenia Geopost, mapowanie statusów i częstotliwość odświeżania
:::
