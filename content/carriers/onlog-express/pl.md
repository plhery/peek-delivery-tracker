---
title: Śledzenie Onlog Express: kody AA, statusy Brazylia i pomoc
description: Śledź przesyłkę Onlog Express w Brazylii: 12-znakowe kody AA, statusy z centrów dystrybucyjnych, ostrzeżenie przed oszustwami i wsparcie São Paulo.
slug: sledzenie-onlog-express
published: 2026-11-24
updated: 2026-11-24
---

**Onlog Express** (*Onlog Franqueadora*) to brazylijska sieć doręczeń paczek i logistyki z siedzibą w São Paulo, mająca ponad 20 lat doświadczenia w partnerstwach logistycznych i ponad 18 milionów doręczonych paczek w całej Brazylii ([onlogexpress.com.br](https://onlogexpress.com.br/)). Łącząc sklepy internetowe i firmy z ogólnokrajowymi przewoźnikami w wariantach od pilnych dostaw w kilka minut lub tego samego dnia (*Entrega em Minutos*, *Entrega no Dia*) po priorytet (*Expresso D1*) i wariant ekonomiczny (*Econômico D2*), obok fulfillmentu i frachtu międzynarodowego, Onlog udostępnia publiczne śledzenie po kodzie obiektu lub CPF/CNPJ na [conecta.log.br/rastreio.php](https://conecta.log.br/rastreio.php). Przesyłkę sprawdzisz tam lub wklejając swój 12-znakowy kod `AA` w pole powyżej.

## Numery śledzenia Onlog Express (format `AA`)

Publiczne śledzenie Onlog Express korzysta z 12-znakowego kodu obiektu zaczynającego się od `AA`, po którym następuje 10 cyfr:

| Format (wymyślony) | Co to jest |
| --- | --- |
| `AA4829173605` | 12-znakowy kod obiektu Onlog Express (`AA` + 10 cyfr). |
| `AA7391846208` | Inny 12-znakowy numer przesyłki `AA` Onlog Express. |

Sprawdź e-mail wysyłkowy sklepu lub link śledzenia `onlog.app.br/rastreio/...`, aby odszukać numer `AA`. Jeśli nie masz kodu, portal Onlog na [conecta.log.br/rastreio.php](https://conecta.log.br/rastreio.php) pozwala odbiorcom szukać po brazylijskim numerze CPF lub CNPJ (z osobną wyszukiwarką dla przesyłek sprzed 01.01.2025). Zobacz [formaty numerów przesyłek](guide:tracking-number-formats).

## Co oznaczają statusy śledzenia Onlog Express

Onlog Express publikuje portugalskie opisy skanów od utworzenia etykiety i odbioru po transfery w centrach dystrybucyjnych i podpis odbiorcy:

| Onlog Express podaje (PT-BR) | Co to oznacza dla Ciebie | Peek pokazuje |
| --- | --- | --- |
| `OBJETO CRIADO`, `ETIQUETA EMITIDA` | Przesyłka utworzona i etykieta wystawiona elektronicznie przed fizycznym odbiorem. | „Zarejestrowana” |
| `OBJETO POSTADO`, `COLETA DE ENCOMENDA` | Paczka nadana w placówce Onlog lub odebrana od nadawcy. | „Przyjęta przez przewoźnika” |
| `OBJETO EM TRANSFERÊNCIA`, `ENTRADA NO CENTRO DE DISTRIBUIÇÃO`, `ENCAMINHADO PARA O OPERADOR LOGÍSTICO`, `BIPE DE EXPEDIÇÃO`, `BIPE DE RECEBIMENTO` | Przyjęta w centrum dystrybucyjnym, przemieszczana między obiektami albo przekazana regionalnemu operatorowi logistycznemu. | „W transporcie” |
| `OBJETO SAIU PARA ENTREGA AO DESTINATÁRIO`, `BIPE DE SAÍDA PARA ENTREGA` | Wydana lokalnemu kurierowi do dzisiejszego doręczenia pod drzwi. | „W doręczeniu” |
| `OBJETO ENTREGUE AO DESTINATÁRIO`, `ASSINATURA DE ENCOMENDA` | Doręczona i pokwitowana pod adresem odbiorcy. | „Dostarczona” |

`ENCAMINHADO PARA O OPERADOR LOGÍSTICO` oznacza przekazanie przesyłki operatorowi logistycznemu realizującemu kolejny odcinek.

## Usługi doręczeń i oficjalne ostrzeżenie przed fałszywymi opłatami

Według [onlogexpress.com.br](https://onlogexpress.com.br/):

- **Poziomy usług:** **Entrega em Minutos** (pilne), **Entrega no Dia** (tego samego dnia), **Expresso D1** (priorytet) i **Econômico D2** (ekonomiczne), przy czym dokładne czasy i dostępność zależą od kodów pocztowych (CEP) nadania i doręczenia.
- **Ważne ostrzeżenie przed oszustwami:** Onlog zamieszcza na stronie głównej wyraźny komunikat, że **Onlog nie prosi o wpłaty opłat e-mailem, przez WhatsApp ani telefonicznie**. Nigdy nie opłacaj podatku ani opłaty za zwolnienie paczki żądanej w niezamówionych wiadomościach w imieniu Onlog. Zobacz [fałszywe SMS-y o doręczeniu](guide:delivery-scam-texts).

## Przesyłka Onlog Express opóźniona, bez skanów lub zaginiona

- **Cisza po `ENCAMINHADO PARA O OPERADOR LOGÍSTICO`:** skany często zatrzymują się między hubami regionalnymi lub podczas przekazania lokalnemu partnerowi ostatniej mili w stanach Brazylii. Zobacz [dlaczego śledzenie przestaje się aktualizować](guide:tracking-not-updating).
- **Dostarczona bez potwierdzenia lub zaginiona:** sprawdź najpierw w portierni (*portaria*), u domowników i sąsiadów, a potem skontaktuj się ze sklepem internetowym i w godzinach pracy z obsługą Onlog w São Paulo, podając numer `AA`. Zobacz [dostarczona, ale nieodebrana](guide:delivered-not-received), [zaginiona przesyłka](guide:lost-parcel), [uszkodzona przesyłka](guide:damaged-parcel) oraz [zwrócona nadawcy](guide:returned-to-sender).

## Obsługa klienta Onlog Express i kontakty w São Paulo

Oficjalne kanały opublikowane na [onlogexpress.com.br](https://onlogexpress.com.br/):

- **Telefon (São Paulo):** `(11) 5662-8108` oraz `(11) 5663-3829`.
- **E-mail:** `atendimento@onlogexpress.com.br`.
- **Godziny pracy:** od poniedziałku do piątku, `08:00`–`18:00` BRT (nieczynne w soboty i niedziele).
- **Centrala (*Matriz São Paulo*):** Rua Estados Unidos, 2031, Jardim América, São Paulo – SP, Brazylia.
- **Tracker online:** [conecta.log.br/rastreio.php](https://conecta.log.br/rastreio.php).

> Pamiętaj o oficjalnym ostrzeżeniu Onlog: Onlog nigdy nie pobiera opłat za zwolnienie przesyłki e-mailem, przez WhatsApp ani telefon. Zignoruj i zgłoś każdą wiadomość żądającą płatności PIX lub kartą za odblokowanie paczki. Zobacz [fałszywe SMS-y o doręczeniu](guide:delivery-scam-texts).

## Śledzenie przesyłki Onlog Express w Peek

Gdy wpiszesz 12-znakowy kod `AA` (`AA4829173605` lub `AA7391846208`, wymyślone), [Peek](/) pyta kolejno [Colis Privé](carrier:colis-prive), [Aramex Australia](carrier:aramex-au) i [Aramex Nowa Zelandia](carrier:aramex-nz), następnie usługi śledzące, a potem Onlog Express — otwierając jego tracker w przeglądarce — i pokazuje „Onlog Express ma tę przesyłkę.” po potwierdzeniu. Peek rozpoznaje też jako Onlog wklejone linki `onlog.app.br/rastreio/AA...`. Jeśli musisz wybrać Onlog samodzielnie, stuknij „Zmień” podczas dodawania lub „Zmień przewoźnika” na ekranie przesyłki (wyszukanie „ONLOG” albo „Onlog Franqueadora” też go znajduje).

Peek czyta tracker Onlog w przeglądarce i wyświetla etap („Zarejestrowana”, „Przyjęta przez przewoźnika”, „W transporcie”, „W doręczeniu”, „Dostarczona”), portugalski opis skanu i lokalizację. Onlog podaje czas skanu z dokładnością do minuty bez przesunięcia UTC: Peek przypisuje go do strefy czasowej miejsca (São Paulo dla skanu w São Paulo, Manaus dla Manaus), skan bez miejsca do strefy sąsiedniego skanu, a w ostateczności do czasu São Paulo, i pokazuje wszystko w czasie Twojego urządzenia. Tracker Onlog nie podaje szacowanego dnia doręczenia, wagi, kodów zdarzeń ani godziny doręczenia.

Gdy masz przesyłkę otwartą w Peek albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza status najwyżej co 30 minut — najkrótszy odstęp dopuszczany przez Onlog między dwoma sprawdzeniami — oraz godzinę po nieudanym sprawdzeniu; w dniu, w którym przesyłka jest w doręczeniu, nie sprawdza co 2 minuty. W pozostałym czasie Peek sprawdza co godzinę. Po 12 godzinach wyłącznie na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika” albo po dwóch dniach bez zmian sprawdza co godzinę niezależnie od sposobu śledzenia, a po siedmiu dniach na „Zarejestrowana” — co 6 godzin. Gdy tracker Onlog nie odpowiada i Peek śledzi przesyłkę wyłącznie przez usługi śledzące, sprawdza co godzinę, co 3 godziny po spokojnym dniu i najwyżej co 30 minut w dniu, w którym przesyłka jest w doręczeniu, dopóki masz ją otwartą lub włączone powiadomienia.

Jeśli najnowszy skan ma cztery dni, a przesyłka nadal widnieje jako „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie”, Peek oznacza ją dniem tego skanu, np. „Brak aktualizacji od pon 12 paź”. Na stronie przesyłki pojawia się wtedy komunikat „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika Onlog Express.” Po trzech tygodniach bez nowego skanu pojawia się informacja „Od ponad trzech tygodni nic nowego. Jeśli przesyłka wciąż nie dotarła, zapytaj sprzedawcę. Peek dalej pyta przewoźnika Onlog Express.”

Możesz wybrać powiadomienia dla opcji „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek śledzi wyłącznie aktualizacje statusu i nie może wyszukiwać paczek po CPF/CNPJ, zmieniać adresu doręczenia ani kontaktować się za Ciebie z Onlog Express.

## Pytania o śledzenie Onlog Express

### Jak wygląda numer śledzenia Onlog Express?

Kody śledzenia Onlog Express zaczynają się od `AA`, po których następuje 10 cyfr (łącznie 12 znaków, na przykład `AA4829173605`, wymyślony).

### Czy mogę śledzić paczkę Onlog Express przy użyciu CPF lub CNPJ?

Tak, na stronie Onlog pod adresem [conecta.log.br/rastreio.php](https://conecta.log.br/rastreio.php) możesz przełączyć się na zakładkę **POR CPF/CNPJ**, jeśli nie masz kodu `AA`. Peek korzysta wyłącznie z kodu obiektu `AA`.

### Dostałem wiadomość na WhatsApp lub e-mail z prośbą o opłatę od Onlog — czy to prawda?

Nie. Onlog wyraźnie zaznacza na [onlogexpress.com.br](https://onlogexpress.com.br/), że **nigdy** nie żąda płatności opłat przez e-mail, WhatsApp ani telefon. Nie płać.

### Jak skontaktować się z obsługą klienta Onlog Express?

Zadzwoń pod `(11) 5662-8108` lub `(11) 5663-3829` albo napisz na `atendimento@onlogexpress.com.br` od poniedziałku do piątku w godzinach 08:00–18:00 BRT.

:::sources
- [Onlog Express: oficjalna strona](https://onlogexpress.com.br/) – poziomy usług (*Entrega em Minutos*, *Entrega no Dia*, *Expresso D1*, *Econômico D2*), ponad 18 mln paczek, ponad 20 lat, ostrzeżenie przed fałszywymi opłatami i kontakty centrali w São Paulo
- [Onlog Express: oficjalny tracker (Conecta)](https://conecta.log.br/rastreio.php) – publiczne śledzenie po kodzie obiektu lub CPF/CNPJ
- [Universal Parcel Scraper: onlog-express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/onlog-express) – format `AA\d{10}`, reguła linków `onlog.app.br/rastreio/`, mapa 13 portugalskich opisów, odpytywanie w przeglądarce co najwyżej co 30 minut (60 po błędzie) i strefy czasowe skanów
:::
