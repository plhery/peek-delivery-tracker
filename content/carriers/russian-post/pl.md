---
title: Russian Post śledzenie (Почта России): numery i statusy
description: Śledź paczkę Russian Post (Почта России): numery RU i 14-cyfrowe, znaczenie rosyjskich statusów oraz jak śledzi ją Peek.
slug: sledzenie-russian-post
published: 2026-11-22
updated: 2026-11-22
---

Russian Post (Почта России, Pochta Rossii) to poczta państwowa Rosji. Obsługuje przesyłki nadawane z Rosji oraz wysyłane do Rosji. Numer przesyłki podaje nadawca lub sklep: wklej go w pole powyżej. Oficjalna strona Russian Post to pochta.ru, a Peek odczytuje jej śledzenie za ciebie w sposób opisany niżej.

## Numer przesyłki Russian Post: jak wygląda

Z wymyślonymi cyframi:

| Numer (wymyślony) | Co to jest |
| --- | --- |
| `RR482910576RU` | Dwie litery, dziewięć cyfr i `RU`: międzynarodowy format pocztowy z kodem Rosji na końcu i cyfrą kontrolną na dziewiątym miejscu. Gdy zaczyna się od C, E, L lub R, Peek od razu rozpoznaje Russian Post. |
| `VV482910576RU` | Inny międzynarodowy numer z końcówką `RU`, który nie zaczyna się od C, E, L ani R. Peek pokazuje go jako „Nieznany operator pocztowy” — Russian Post możesz wybrać ręcznie. |
| `80482910573625` | 14 cyfr zaczynających się od `80`, gdzie ostatnia cyfra jest kontrolna. Peek kojarzy go z Russian Post tylko jako słabe dopasowanie: podobny kształt mają numery DPD, SEUR, BRT, Delhivery i innych przewoźników. |
| `80482910573620` | Ten sam kształt, ale błędna cyfra kontrolna. Peek nie podpowiada wtedy Russian Post, tylko innych przewoźników: jeśli to numer Russian Post, sprawdź, czy nie ma literówki. |
| `RR482910576CN` | Numer wydany przez inną pocztę, tutaj [China Post](carrier:china-post). Peek śledzi go u tamtego operatora, także gdy paczka jedzie do Rosji. |

Spacje i małe litery nie mają znaczenia po wklejeniu numeru do Peek. Jeśli nie masz swojego numeru, zobacz [gdzie znaleźć numer przesyłki](guide:find-tracking-number) oraz [formaty numerów przesyłek](guide:tracking-number-formats).

## Statusy śledzenia Russian Post: co oznaczają

Russian Post zapisuje zdarzenia po rosyjsku. Peek odczytuje je w takiej postaci, jak w tabeli niżej, na stronie śledzenia Qwintry — pośrednika przekazującego część paczek Russian Post — dla paczki dodanej numerem Qwintry albo numerem RU przypisanym do Qwintry (zobacz „Paczki z Qwintry” poniżej). Tam każdy krok Russian Post składa się z operacji, na przykład `обработка` (opracowanie), wiersza ze szczegółami i nazwy placówki pocztowej na końcu. Gdy nie ma szczegółów, sam opis operacji tworzy krok. Mniej więcej w kolejności drogi paczki:

| Russian Post pisze | Co to oznacza dla ciebie | Peek pokazuje |
| --- | --- | --- |
| `Партионный` | Russian Post przyjęła paczkę w partii razem z innymi (operacja `приём`, przyjęcie). | „W transporcie” |
| `экспорт международной почты` | Eksport poczty międzynarodowej: paczka opuszcza kraj, w którym się znajduje. | „W transporcie” |
| `Прибыло на территорию РФ` | Paczka dotarła do Rosji. | „W transporcie” |
| `импорт международной почты` | Import poczty międzynarodowej: paczka jest przyjmowana na terenie Rosji. | „W transporcie” |
| `приём на таможню` | Przekazana do urzędu celnego. | „W urzędzie celnym” |
| `Выпущено таможней` | Zwolniona przez urząd celny: jedzie dalej. | „W transporcie” |
| `Покинуло место международного обмена` | Opuściła urząd obsługujący przesyłki z zagranicy. | „W transporcie” |
| `Сортировка` | Sortowanie. | „W transporcie” |
| `Прибыло в сортировочный центр` | Dotarła do sortowni. | „W transporcie” |
| `Покинуло сортировочный центр` | Opuściła sortownię. | „W transporcie” |
| `Прибыло в место вручения` | Dotarła do placówki pocztowej, która ją doręczy lub wyda. | „W transporcie” |
| `Передано почтальону` | Przekazana listonoszowi do doręczenia. | „W doręczeniu” |
| `Временное отсутствие адресата` | Niedoręczona: nie było cię pod adresem. | „Próba doręczenia” |
| `Адресат заберет отправление сам` | Niedoręczona, bo odbierzesz paczkę samodzielnie. | „Próba doręczenia” |
| `Вручение адресату` | Wydana odbiorcy. | „Dostarczona” |

Dla pozostałych przesyłek Russian Post Peek korzysta z własnego śledzenia Russian Post, które odpowiada po angielsku: zobacz „Śledzenie przesyłki Russian Post w Peek” poniżej.

## Jak doręcza Russian Post

Powyższe kroki pokazują trasę w Rosji: sortownie, a następnie placówkę pocztową odpowiedzialną za doręczenie. Stamtąd paczkę może przynieść listonosz (`Передано почтальону`). Jeśli nikogo nie było albo masz odebrać ją osobiście, krok o tym informuje. W śledzeniu Qwintry Peek pokazuje miejscowość placówki jako lokalizację zdarzenia. Gdy używasz Peek po rosyjsku albo gdy Peek nie może zlokalizować urzędu, wyświetla jego nazwę tak, jak zapisuje ją Russian Post, abyś wiedział, gdzie jest paczka.

Ta strona nie podaje terminów doręczenia, czasu przechowywania ani zasad prób doręczenia, ponieważ Peek nie mógł ich zweryfikować na stronie Russian Post. Jeśli paczka czeka w placówce, sprawdź [nieodebrane przesyłki](guide:uncollected-parcel) oraz [nieudane doręczenia](guide:missed-delivery).

**Wysłane z Rosji do innego kraju.** Peek nie wie z góry, jaka firma doręczy paczkę Russian Post w twoim kraju. Przy numerze RU pyta także pocztę kraju wskazanego przez Russian Post jako kraj docelowy i może przejąć tam śledzenie (zobacz niżej).

**Wysłane do Rosji z innego kraju.** Peek śledzi przesyłkę u przewoźnika, który wydał numer — bezpośrednio lub przez usługi śledzenia. Jeśli to śledzenie wskaże Russian Post jako przewoźnika doręczającego, Peek pyta także śledzenie Russian Post i przenosi tam paczkę, gdy pojawi się skan co najmniej tak świeży jak ostatni znany. Sam kierunek do Rosji nie wystarcza do przeniesienia.

## Paczka Russian Post nie dociera lub śledzenie stoi

**Brak kroków.** Śledzenie Russian Post albo usługi śledzenia mogą jeszcze nie znać numeru, albo nadawca nie nadał jeszcze paczki. Peek sprawdza dalej. Jeśli po kilku dniach nic się nie pojawi, potwierdź numer u nadawcy. Więcej w poradniku [dlaczego śledzenie się nie aktualizuje](guide:tracking-not-updating).

**Zatrzymana na cle.** Gdy Peek pokazuje „W urzędzie celnym”, przesyłka jest na odprawie celnej. Ta strona nie podaje zasad ani opłat celnych, bo nie udało się ich potwierdzić na oficjalnej stronie. Zobacz [przesyłki zatrzymane przez urząd celny](guide:customs).

**Peek ma opóźnienie.** Kiedy śledzenie Russian Post nie odpowiada Peek, wchodzi usługa śledzenia, która potrafi spóźniać się względem oficjalnej strony. Zanim się zaniepokoisz, sprawdź pochta.ru.

**Dostarczona, ale nic nie przyszło.** Jeśli Peek pokazuje „Dostarczona”, a paczki nie ma, zapytaj w swojej placówce pocztowej i powiadom nadawcę. Więcej w [paczka doręczona, ale nieodebrana](guide:delivered-not-received).

**Zaginiona lub zwrócona.** Najpierw skontaktuj się z nadawcą albo sklepem: to oni zlecili wysyłkę. Zobacz [zaginione paczki](guide:lost-parcel) i [zwrot do nadawcy](guide:returned-to-sender).

## Obsługa klienta i kontakt z Russian Post

Ponieważ Peek nie mógł odczytać strony Russian Post, ta strona nie podaje numeru telefonu ani adresu e-mail: zamieszczamy wyłącznie kontakty odczytane ze stron samego przewoźnika. Strony pomocy Russian Post znajdują się pod adresem info.pochta.ru. Gdy przesyłka przestaje się poruszać, Peek wyświetla link do strony pomocy Russian Post o śledzeniu jako „Poproś przewoźnika Russian Post o pomoc”.

W przypadku zamówienia ze sklepu zapytaj najpierw sklep: to on nadał przesyłkę.

## Śledzenie przesyłki Russian Post w Peek

Wklej numer do [Peek](/): Peek śledzi ponad 4500 przewoźników. W zależności od formatu numeru:

- **Międzynarodowy numer `RU` zaczynający się od C, E, L lub R (poprawna cyfra kontrolna).** Peek rozpoznaje Russian Post od razu („Wykryty przewoźnik”).
- **Inny międzynarodowy numer `RU` (inna pierwsza litera).** Peek wyświetla go jako „Nieznany operator pocztowy”. Kliknij „Zmień” (lub później „Zmień przewoźnika”) i wybierz Russian Post — wpisanie Почта России również go znajduje. Peek pokaże wtedy Russian Post.
- **14 cyfr zaczynających się od `80` (poprawna cyfra kontrolna).** Podczas dodawania Peek pokazuje „Sprawdzanie usług śledzenia…” i pyta śledzenie Russian Post, a także DPD i innych pasujących przewoźników, czy mają tę przesyłkę. Jeśli zna ją tylko Russian Post, Peek wyświetli „Russian Post ma tę przesyłkę.” i tam ją zapisze. Jeśli nie, kliknij „Zmień”: Russian Post znajdziesz w sekcji „Pasują do numeru” razem z innymi pasującymi przewoźnikami, zaczynając od tych z twojego kraju. Przy już dodanej paczce użyj „Zmień przewoźnika”.

Peek odczytuje własne śledzenie Russian Post, które odpowiada po angielsku. Wyświetla każdy krok w angielskich słowach Russian Post, takich jak `Sorting`, `Arrived at the local distribution center` czy `Forwarded to the postman` („W doręczeniu”), wraz z godziną, miejscowością, krajem oraz krajem docelowym. `Available for pickup at the Post Office` pojawia się jako „Do odbioru”, a `Handed over for temporary storage` jako „Zgłoszono problem”. Po `Returned to the sender` (również „Zgłoszono problem”) paczka wraca do nadawcy, a jej wydanie trafia do statusu „Zwrócona nadawcy”. Russian Post nie podaje planowanej daty doręczenia. Dopóki jego śledzenie nie zna numeru, Peek pokazuje „Oczekiwanie na przewoźnika Russian Post”, albo „Oczekiwanie na pierwszy skan przewoźnika Russian Post” dla numeru na `80` dodanego bez przewoźnika. Kiedy śledzenie Russian Post nie odpowiada Peek, Peek pyta usługi śledzenia — po kolei ParcelsApp, Ship24 i 17TRACK, a dla numeru RU na końcu śledzenie Światowego Związku Pocztowego — i pokazuje historię pierwszej, która zna paczkę, w jej własnych słowach, co może być opóźnione względem pochta.ru. Usługę, która nie znała numeru, Peek pyta ponownie następnego dnia.

Dla numeru `80` dodanego bez przewoźnika lub numeru RU pokazywanego jako „Nieznany operator pocztowy” Peek w ciągu pierwszych 30 dni pyta przed usługami śledzenia również przewoźników mogących mieć paczkę (dla numeru `80` m.in. Russian Post i DPD). Jeśli któryś pokaże ruch, Peek przenosi tam paczkę. Gdy przy numerze `80` dodanym bez przewoźnika usługa śledzenia wskaże wyłącznie Russian Post ze skanami opatrzonymi datą, Peek przypisze paczkę do Russian Post sam. Przy numerze RU pokazywanym jako „Nieznany operator pocztowy” samo wskazanie przez usługę nie wystarcza: paczkę musi pokazać śledzenie Russian Post albo musisz wybrać Russian Post ręcznie.

Peek nie ma bezpośredniego linku do śledzenia Russian Post. Jeśli zadziałała usługa śledzenia, link na stronie paczki otwiera tę usługę, na przykład „Otwórz stronę przewoźnika ParcelsApp”. Stronę pochta.ru otwórz samodzielnie.

**Paczki z Qwintry.** Jeśli Qwintry wysłało twoją paczkę do Rosji, dodaj lepiej numer Qwintry: `QR` i osiem cyfr. Peek od razu rozpoznaje Qwintry i czyta jego śledzenie, które po przekazaniu poczcie pokazuje również kroki Russian Post. Gdy śledzenie Qwintry wskaże Russian Post, na paczce pojawia się „Doręcza: Russian Post” oraz numer RU poczty, a Peek pyta także Russian Post: gdy tylko pokaże ono skan co najmniej tak świeży jak ostatni z Qwintry, Peek kontynuuje śledzenie tam, zachowując wcześniejszą historię Qwintry. Dopóki Russian Post ma tylko zapowiedź bez własnego skanu, widać „Zgłoszona przewoźnikowi Russian Post, jeszcze nieprzyjęta”. Śledzenie Qwintry przyjmuje również numer RU paczki przekazanej przez niego poczcie: jeśli masz tylko ten numer, a śledzenie Russian Post go nie znajduje, kliknij „Zmień” i wybierz Qwintry.

**Poczta w twoim kraju.** Przy numerze RU przypisanym do Russian Post śledzenie Russian Post podaje kraj docelowy, a Peek pyta tym samym numerem RU pocztę tego kraju, jeśli czyta jej własne śledzenie — tak jak w przypadku [USPS](carrier:usps), [Royal Mail](carrier:royal-mail), An Post, Canada Post czy Australia Post. Przy paczce oznaczonej jako „Nieznany operator pocztowy” wystarczy, że usługa śledzenia poda nazwę doręczającej poczty z jej numerem albo kraj docelowy dla numeru RU: Peek pyta tę pocztę tak samo i przenosi śledzenie tam, gdy pokaże skan co najmniej tak świeży jak ostatni znany.

Gdy masz paczkę otwartą na ekranie albo w dniu doręczenia z włączonymi powiadomieniami, Peek sprawdza śledzenie Russian Post nawet co 10 minut — a od 6:00 czasu lokalnego w dniu wydania do doręczenia nawet co 2 minuty — dopóki odpowiada jego własne śledzenie; przez usługi śledzenia sprawdza co godzinę, co 3 godziny po dniu bez nowego skanu, a w dniu wydania do doręczenia nawet co 15 minut. Poza tym oraz po dwóch dniach bez nowych informacji (po 12 godzinach, gdy paczka ma wciąż status „Zarejestrowana” lub „Przyjęta przez przewoźnika”) sprawdza co godzinę. Może wysyłać powiadomienia dla opcji „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek nie może skontaktować się za ciebie z Russian Post ani z nadawcą.

## Pytania o śledzenie Russian Post

### Co oznacza Почта России?

Poczta Rosyjska — międzynarodowo znana jako Russian Post lub w zapisie łacińskim Pochta Rossii. Jej strona to pochta.ru.

### Jak wygląda numer przesyłki Russian Post?

Numer międzynarodowy ma dwie litery, dziewięć cyfr i `RU`, na przykład `RR482910576RU`. Peek wiąże z Russian Post także 14-cyfrowe numery zaczynające się od `80`, jeśli cyfra kontrolna jest poprawna.

### Dlaczego kroki w Peek nie pokrywają się z pochta.ru?

Peek pyta śledzenie Russian Post po angielsku, więc pokazuje jego angielskie nazwy dla większości kroków zamiast rosyjskich. Gdy to śledzenie nie odpowiada Peek, zastępuje je usługa śledzenia z własnym słownictwem i możliwym opóźnieniem. Rosyjskie kroki z tabeli wyżej Peek wyświetla przy przesyłkach śledzonych przez Qwintry.

### Czy Peek może śledzić przesyłkę wysłaną do Rosji z innego kraju?

Tak. Wklej numer od nadawcy: Peek śledzi ją u przewoźnika, który wydał numer, i przenosi do Russian Post dopiero wtedy, gdy tamto śledzenie wskaże Russian Post jako przewoźnika doręczającego.

:::sources
- [Universal Parcel Scraper: Russian Post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/russian-post) – nazwy, formaty `80` i `RU` z cyframi kontrolnymi i dopasowaniami, śledzenie czytane przez Peek oraz angielskie kroki, lokalizacje, kraj docelowy, etapy i strona pomocy
- [Universal Parcel Scraper: Qwintry](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/qwintry) – rosyjskie kroki Russian Post w śledzeniu Qwintry i ich etapy, placówka jako lokalizacja, numer `QR` i przyjmowane numery `RU`
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – numery pocztowe `RU`, kolejność odpytywanych przewoźników i usług śledzenia, przekazywanie do poczty w twoim kraju oraz do Russian Post
:::
