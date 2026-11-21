---
title: Śledź swoje przesyłki w ChatGPT, Claude lub Gemini
description: Połącz Peek z ChatGPT, Claude lub Gemini i pytaj, gdzie są Twoje przesyłki, albo poproś o dodanie numerów śledzenia z e-maili. Kto może i jak to ustawić.
slug: sledzenie-przesylek-chatgpt-claude-gemini
picture: Pip, paczka z buzią, wychyla się z okna czatu na laptopie, a na pytanie o przesyłki z tego tygodnia przychodzi tam krótka lista.
published: 2026-11-19
updated: 2026-11-21
---

Swoje przesyłki możesz śledzić w Claude, ChatGPT lub Gemini, jeśli połączysz je z [Peek](/), darmowym trackerem przesyłek: asystent może Ci wtedy powiedzieć, gdzie są Twoje przesyłki, i dodać numery śledzenia, które mu podasz. Potrzebujesz konta Peek, a o tym, kto może z tego korzystać, decyduje każdy asystent: Claude w każdym planie, ChatGPT w planie Business, Enterprise lub Edu, w przypadku Gemini dorośli w USA z osobistym kontem Google, a w przypadku OpenClaw ten, kto go uruchamia. Peek oznacza tę funkcję jako „Beta”, więc może się ona jeszcze zmienić.

## Czy ChatGPT, Claude albo Gemini mogą śledzić moje przesyłki?

Tak, gdy połączysz je z Peek. Każdy asystent stawia własne warunki:

| Asystent | Gdzie działa | Kto może dodać Peek |
| --- | --- | --- |
| Claude | claude.ai oraz aplikacje Claude na komputer, iPhone’a i Androida | Każdy plan. W planie Team lub Enterprise najpierw właściciel dodaje Peek |
| ChatGPT | chatgpt.com | Plan Business, Enterprise lub Edu. W obszarze roboczym być może najpierw administrator musi dodać Peek lub na niego zezwolić |
| Gemini | Łączysz na gemini.google.com, potem działa też w aplikacji Gemini | Osoby od 18 lat, w USA, z osobistym kontem Google i włączoną historią, po angielsku |
| OpenClaw | Komputer lub serwer, na którym go uruchamiasz, w czatach jego agentów | Ten, kto go uruchamia, z terminala na tym urządzeniu |
| Inny asystent | Każdy, w którym można dodać własny konektor lub serwer MCP | Ten, kto może go tam dodać |

Po stronie Peek połączyć asystenta może każde konto (gość nie może), a Peek jest darmowy.

Home Assistant łączy się inaczej, przez własną integrację Peek: zobacz poradnik [Śledź swoje przesyłki w Home Assistant](guide:home-assistant).

## Jak dodać Peek do Claude, ChatGPT lub Gemini?

Peek dodajesz w asystencie, podając adres Peek dla asystentów: `https://peektracker.com/mcp`. Poniższe kroki to [kroki samego Peek](https://peektracker.com/pl/assistants); po zalogowaniu na stronie Peek (nie w aplikacjach) znajdziesz je też w „Ustawienia” → „Dostawy” → „Asystenci”, z przyciskiem „Kopiuj adres”.

### Claude

:::steps
- Otwórz „Peek Parcel Tracker” | W [katalogu konektorów Claude](https://claude.ai/directory/peek-parcel-tracker) albo w Claude otwórz „Customize › Connectors”, wybierz „+” i wyszukaj Peek.
- Wybierz „Connect” | Claude poprosi Cię wtedy o zalogowanie się do Peek.
- Zaloguj się do Peek | Zdecyduj, co Claude może robić, i wybierz „Zezwól”.
- Włącz Peek w czacie | Wybierz „+” w lewym dolnym rogu, potem „Connectors”, i włącz Peek.
:::

W planie Team lub Enterprise najpierw właściciel dodaje Peek: „Organization settings › Connectors”, „Browse connectors”, a potem „Add to your team”.

Jeśli Claude nie pokazuje „Peek Parcel Tracker”, dodaj Peek jako własny konektor na claude.ai lub w aplikacji na komputer (plan Free pozwala na jeden):

:::steps
- Otwórz „Customize › Connectors” | Wybierz „+ Add”, a potem „Add custom connector”.
- Nazwij go Peek | Wklej `https://peektracker.com/mcp` i wybierz „Continue”.
- Zostaw ustawienia logowania, które znajdzie Claude | Wybierz „Continue”, a potem „Add”.
:::

### ChatGPT

:::steps
- Wejdź na „chatgpt.com/plugins” | Wybierz „+”, a potem „Add custom MCP server”.
- Nazwij go Peek | Wklej `https://peektracker.com/mcp` i jako uwierzytelnianie wybierz „OAuth”. Wybierz „I understand and want to continue”, a potem „Create as a plugin”.
- Zaloguj się do Peek | Zdecyduj, co ChatGPT może robić, i wybierz „Zezwól”.
- Zainstaluj go | Otwórz Peek w swoich wtyczkach i wybierz „+”. Potem wpisz @ w nowym czacie i wybierz Peek.
:::

### Gemini

Strona pomocy Google podaje, że aby połączyć własną aplikację, taką jak Peek, musisz mieć ukończone 18 lat i być w USA, używać osobistego konta Google, a nie służbowego ani szkolnego, i mieć włączoną historię („History”). Łączysz ją na gemini.google.com, a potem działa też w aplikacji mobilnej Gemini, tylko po angielsku. Google nie wymienia żadnego płatnego planu. Strona Google i strona Peek podają, że adres Peek dodaje się w „Settings” Gemini, potem w „Connected Apps”, w sekcji „Custom apps”.

### OpenClaw

OpenClaw to asystent, którego uruchamiasz na własnym komputerze lub serwerze. Peek dodajesz z terminala na tym urządzeniu, a jego agenci korzystają potem z Twojego konta Peek w każdym czacie, niezależnie od tego, z kim rozmawiają. Polecenia `openclaw mcp set` i `openclaw mcp login` nie działają, gdy działa jego Gateway, więc najpierw zatrzymujesz Gateway:

:::steps
- Zatrzymaj Gateway | Uruchom `openclaw gateway stop`.
- Dodaj Peek | Uruchom `openclaw mcp set peek '{"url":"https://peektracker.com/mcp","transport":"streamable-http","auth":"oauth"}'`.
- Zaloguj się do Peek | Uruchom `openclaw mcp login peek` i otwórz link, który wyświetli to polecenie. Peek nazywa je „OpenClaw MCP”: zdecyduj, co może robić, i wybierz „Zezwól”.
- Uruchom ponownie Gateway | Uruchom `openclaw gateway start`, a potem zapytaj swojego agenta o przesyłki.
:::

Jeśli przeglądarka jest na innym urządzeniu, strona, którą otworzy po zalogowaniu, się nie wczyta: dokończ z kodem z jej adresu, tak jak wyjaśnia OpenClaw w terminalu.

## O co mogę go poprosić?

Pytaj własnymi słowami, na przykład:

- „Przejrzyj moje e-maile i dodaj numery śledzenia, które znajdziesz”. To działa, jeśli asystent sam może czytać Twoją pocztę, na przykład przez swój konektor Gmail: Peek nigdy nie czyta Twojej poczty.
- „Co dotrze w tym tygodniu?”
- „Gdzie jest moja przesyłka z IKEA?”
- „Zarchiwizuj wszystko, co zostało doręczone”.
- „Jaka była moja ostatnia doręczona przesyłka?”

Potrafi też wskazać przesyłki, które wymagają Twojej uwagi, na przykład taką, która czeka w punkcie odbioru, oraz te spodziewane dzisiaj, według Twojej strefy czasowej.

Żeby dodać przesyłki, podaj mu numer śledzenia albo część e-maila o wysyłce: Peek śledzi każdy znaleziony numer, do 10 naraz, i zapisuje tylko numery oraz podaną nazwę, nigdy tekst. Numer zamówienia to nie numer śledzenia: [zobacz, jak je odróżnić](guide:find-tracking-number).

### Zamówienia z Amazona

E-maile od Amazona zwykle nie zawierają numeru śledzenia. Asystent potrzebuje więc przeglądarki, w której masz otwartą sesję na Amazonie, na komputerze: Claude in Chrome albo rozszerzenia przeglądarki ChatGPT. Z aplikacji w telefonie albo przez przeglądarkę, którą uruchamia na własnych serwerach, nie dostanie się do Twoich zamówień i ma polecenie, żeby to powiedzieć, zamiast próbować. Jeśli asystent może przeglądać stronę Amazona, na której masz otwartą sesję, dostaje polecenie, żeby otworzyć „Twoje zamówienia”, potem „Track package”, i przekazać przewoźnika oraz „Tracking ID”, nigdy numer zamówienia. Może tam tylko czytać: nie wolno mu niczego kupować, anulować, zwracać ani zmieniać, ani logować się za Ciebie. W przeciwnym razie znajdź numer samodzielnie: [nasz poradnik o Amazonie](guide:amazon-orders) pokazuje, gdzie. Przesyłkę, którą Amazon doręcza sam i bez identyfikatora śledzenia, śledzisz tylko na swoim koncie Amazon.

## Czy asystent może usunąć lub zmienić moje przesyłki?

Nigdy nie może usunąć przesyłki: to robisz Ty w Peek. Reszta zależy od Ciebie: gdy go łączysz, ekran Peek (na przykład „Połączyć Claude z Peek?”) pokazuje Twój adres e-mail i pole wyboru przy każdym uprawnieniu, o które prosi asystent:

| Uprawnienie na ekranie Peek | Co asystent może wtedy robić |
| --- | --- |
| „Widzieć Twoje przesyłki: gdzie jest każda z nich, kiedy ma dotrzeć i jej historię” | Wyświetlać listę Twoich przesyłek i otwierać pojedynczą przesyłkę z jej skanami |
| „Dodawać przesyłki na podstawie numerów śledzenia, które mu podasz, i je archiwizować” | Dodawać przesyłki i archiwizować pojedynczą przesyłkę |
| „Zmieniać nazwę, przewoźnika lub powiadomienia Twoich przesyłek i przywracać te z archiwum” | Zmieniać nazwę lub przewoźnika przesyłki, wyłączać lub włączać jej powiadomienia albo e-maile od Peek i przywracać przesyłkę z archiwum |

Odznacz to, czego nie chcesz; przycisk „Zezwól” pozostaje wyszarzony, dopóki nie zaznaczysz co najmniej jednego pola. Jeśli później poprosisz o coś, na co nie było zgody, asystent dostanie informację, że ma ponownie połączyć Peek z tym uprawnieniem.

## Co widzi asystent i jak go odłączyć?

Dostaje to, na co mu pozwalasz: nazwy, nadawców, przewoźników, numery śledzenia, statusy, przewidywane dni, punkty odbioru i skany Twoich przesyłek. Nigdy imienia i nazwiska odbiorcy ani podanego przez Ciebie kodu pocztowego czy cyfr telefonu: skan, który wymienia odbiorcę, osobę podpisującą odbiór, kod albo drzwi, trafia do niego tylko jako etap, na przykład „Dostarczona”. Miejsce skanu zostaje takie, jak zapisał je przewoźnik, więc może zawierać miejscowość doręczenia i jej kod pocztowy. Potem obowiązują zasady prywatności dostawcy asystenta. [Informacja o prywatności](https://peektracker.com/privacy.html) Peek opisuje, co Peek przechowuje o każdym połączonym asystencie.

Aby odłączyć asystenta, na stronie Peek:

:::steps
- Otwórz „Ustawienia” | Przez okrągły przycisk na górze z pierwszą literą Twojego adresu e-mail.
- Wybierz „Asystenci” | W sekcji „Dostawy”. Przy każdym asystencie widać, co może robić i kiedy ostatnio go używano.
- Wybierz „Odłącz” | Obok asystenta.
:::

Możesz też usunąć Peek w ustawieniach samego asystenta. Połączenie nieużywane przez 60 dni wygasa samo, a usunięcie konta Peek usuwa połączonych z nim asystentów.

## Dlaczego nie mogę dodać Peek do swojego asystenta?

Zwykle chodzi o plan, kraj albo konto:

- **Twój plan lub kraj.** Sprawdź tabelę powyżej, a w planie zespołowym lub w obszarze roboczym zapytaj jego właściciela albo administratora.
- **Nie masz konta Peek.** Zaloguj się, gdy asystent o to poprosi, sześciocyfrowym kodem, który Peek wyśle Ci e-mailem, albo przez Google lub Apple, jeśli ekran to proponuje. Pierwsze logowanie tworzy konto.
- **Peek znowu prosi o zalogowanie.** Żeby zezwolić asystentowi na dostęp, potrzebne jest niedawne logowanie, nawet przy otwartej sesji: zaloguj się, a potem wybierz „Zezwól”.
- **„Ten link nie może połączyć asystenta”.** Zacznij łączenie od nowa w swoim asystencie.

Więcej przypadków znajdziesz w części „Gdy coś nie działa” na [stronie Peek o asystentach](https://peektracker.com/pl/assistants), a na [stronie pomocy](https://peektracker.com/support.html) Peek jest kontakt do Peek.

:::sources
- [Peek: Połącz Peek z Claude, ChatGPT lub Gemini](https://peektracker.com/pl/assistants) – kto może, kroki dla każdego asystenta, o co go poprosić, co Peek udostępnia, gdy coś nie działa
- [Peek na GitHubie: konfiguracja każdego asystenta](https://github.com/plhery/peek-delivery-tracker/blob/main/src/assistants/setups.tsx) – kroki i nazwy menu, logowanie przed ostatnim krokiem
- [Peek na GitHubie: teksty po polsku](https://github.com/plhery/peek-delivery-tracker/blob/main/shared/locales/pl.json) – plany i miejsca, uprawnienia, Beta, gdy coś nie działa
- [Peek na GitHubie: narzędzia asystentów](https://github.com/plhery/peek-delivery-tracker/blob/main/src/server/assistants/tools.ts) – co asystent może wyświetlać, dodawać i edytować, brak usuwania, Amazon
- [Peek na GitHubie: ekran zgody](https://github.com/plhery/peek-delivery-tracker/blob/main/src/components/AssistantConsentScreen.tsx) – pola wyboru, Zezwól, niedawne logowanie
- [Peek na GitHubie: Ustawienia, Asystenci](https://github.com/plhery/peek-delivery-tracker/blob/main/src/components/Assistants.tsx) – adres, połączeni asystenci, Odłącz
- [Peek: Privacy notice](https://peektracker.com/privacy.html) – co dostaje asystent, co przechowuje Peek, 60 dni, usunięcie konta
- [Peek na GitHubie: Terms](https://github.com/plhery/peek-delivery-tracker/blob/main/TERMS.md) – za darmo, bez opłat i abonamentu
- [Peek: Help](https://peektracker.com/support.html) – logowanie sześciocyfrowym kodem
- [Claude Help Center: Get started with custom connectors using remote MCP](https://support.claude.com/en/articles/11175166-get-started-with-custom-connectors-using-remote-mcp) – kroki dodawania własnego konektora, jeden w planie Free
- [Gemini Apps Help: Connect & manage custom apps for Gemini Apps](https://support.google.com/gemini/answer/17209137?hl=en&co=GENIE.Platform%3DDesktop) – kto może połączyć własną aplikację, włączona historia, angielski, łączenie w przeglądarce
- [OpenClaw: Transports and OAuth](https://docs.openclaw.ai/cli/mcp/transports) – zapisanie serwera z OAuth, `openclaw mcp login`, dokończenie z kodem, jedno wspólne logowanie
- [OpenClaw: Connect MCP servers](https://docs.openclaw.ai/tools/mcp) – agenci korzystają z narzędzi zapisanego serwera
- [OpenClaw na GitHubie: polecenia MCP i Gateway](https://github.com/openclaw/openclaw/blob/v2026.11.5/src/cli/mcp-cli-state-owner.ts) – `mcp set` i `mcp login` odrzucane, gdy działa Gateway
:::
