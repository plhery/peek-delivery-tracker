---
title: Śledzenie przesyłek open source: kod i silnik Peeka
description: Peek to open source (Apache 2.0). Hostuj go u siebie, uruchom lokalnie albo śledź przesyłki z własnego kodu przez Universal Parcel Scraper: CLI, Node lub HTTP.
slug: sledzenie-przesylek-open-source
picture: Pip, paczka z buzią, siedzi na otwartym laptopie, którego terminal wypisuje skany przesyłki linijka po linijce.
published: 2026-11-19
updated: 2026-11-19
---

[Peek](/) to darmowy tracker przesyłek o otwartym kodzie: aplikacja webowa, API oraz aplikacje na iPhone’a i Androida są na GitHubie na licencji Apache 2.0, a dane od przewoźników odczytuje osobny silnik open source, Universal Parcel Scraper. Peek nie ma publicznego API z kluczami, więc żeby śledzić przesyłki z własnego kodu, użyj scrapera: jako polecenia, biblioteki Node albo serwera HTTP, bez konta i bez klucza API. Możesz też postawić własnego Peeka albo uruchomić go na swoim komputerze z wymyślonymi przesyłkami.

Żeby wypróbować scraper w terminalu:

:::steps
- Zainstaluj go | Potrzebujesz aktualnego Node.js (minimalną wersję podaje README). Uruchom `npm install -g universal-parcel-scraper` albo wpisuj `npx universal-parcel-scraper` wszędzie tam, gdzie poniżej pojawia się `parcel-scraper`.
- Ustal przewoźnika | `parcel-scraper detect 1Z999AA10123456784` mówi offline, do którego przewoźnika należy numer.
- Pobierz historię | `parcel-scraper track` z Twoim numerem pyta przewoźnika i wypisuje historię przesyłki jako JSON. Jeśli numer pasuje do kilku przewoźników, dodaj na przykład `--carrier dhl`. Niektórzy przewoźnicy chcą też `--postcode`, `--phone-digits` albo `--tracking-url`.
:::

## Czy Peek jest open source i na jakiej licencji?

Tak. Kod Peeka jest publiczny na [github.com/plhery/peek-delivery-tracker](https://github.com/plhery/peek-delivery-tracker), na licencji Apache 2.0: aplikacja webowa i API (Next.js, React, TypeScript), natywna aplikacja w SwiftUI na iPhone’a, iPada i Maca oraz aplikacja na Androida. [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper), którego Peek instaluje z npm, też jest na licencji Apache 2.0; tylko jego opcjonalny obraz przeglądarki TRAWL jest na AGPL-3.0.

Peek to niekomercyjny projekt bez reklam; porównanie z innymi trackerami znajdziesz w poradniku o [śledzeniu przesyłek wszystkich firm w jednym miejscu](guide:universal-tracker).

Błędy i pomysły zgłaszaj w [Issues Peeka na GitHubie](https://github.com/plhery/peek-delivery-tracker/issues), które są publiczne: nie wpisuj tam prawdziwych numerów przesyłek, kodów pocztowych ani kodów logowania. Problemy z bezpieczeństwem zgłaszaj przez [prywatne zgłoszenie podatności](https://github.com/plhery/peek-delivery-tracker/security/advisories/new) na GitHubie. [CONTRIBUTING.md](https://github.com/plhery/peek-delivery-tracker/blob/main/CONTRIBUTING.md) wymienia sprawdzenia, które trzeba uruchomić przed pull requestem.

## Czy Peek ma API, z którego mogę korzystać?

Nie ma publicznego: Peek nie wydaje kluczy API. Jego API to API samej aplikacji, które wywołują strona oraz aplikacje na iPhone’a i Androida, z sesją zalogowanego użytkownika. Opisuje je [plik OpenAPI](https://peektracker.com/api/openapi.json), przechowywany w repozytorium jako `contracts/openapi.json`; sam adres peektracker.com/api zwraca 404.

Katalog przewoźników, `GET /api/carriers`, nie wymaga logowania. Śledzenie przesyłki bez konta też nie, ale nowe wyszukiwanie musi tam najpierw przejść weryfikację przeglądarki albo aplikacji. Osobiste tokeny do skryptów są wyłączone, chyba że serwer je włączy, a w sekcji „Ustawienia” nie ma ekranu, który by taki token tworzył.

Z zewnątrz jedyną drogą jest logowanie asystenta. Claude, ChatGPT, Gemini albo inny asystent loguje się na Twoje konto przez standardowy OAuth i korzysta z serwera MCP Peeka, `https://peektracker.com/mcp`, w zakresie, na jaki mu pozwalasz. Tym samym tokenem można odczytać Twoje przesyłki jako JSON z `GET /api/parcels`, a integracja Peeka z Home Assistant loguje się w ten sam sposób. Zobacz [poradnik o asystentach](guide:ai-assistants), [stronę Peeka dla asystentów](https://peektracker.com/pl/assistants) i [poradnik o Home Assistant](guide:home-assistant).

## Jak śledzić przesyłki z własnego kodu bez klucza API?

Przez Universal Parcel Scraper, silnik, który odczytuje dla Peeka dane od przewoźników. Ustala przewoźnika, czyta jego własną stronę, sięga po uniwersalne trackery tylko wtedy, gdy musi, i zwraca ten sam JSON dla każdego przewoźnika, z każdym skanem przypisanym do wspólnego etapu doręczenia.

Jego README mówi o ponad 4000 przewoźnikach, obsługiwanych przez ponad 800 dedykowanych adapterów i uniwersalne trackery zapasowe. W jego benchmarku 100 przewoźników wybranych pod kątem kupujących w Europie adaptery zwracają historię dla 91, a ze wszystkimi trackerami zapasowymi dla 95, wobec 73 w przypadku najlepszego pojedynczego agregatora.

Oprócz wiersza poleceń działa na trzy sposoby:

| Gdzie | Jak |
| --- | --- |
| Node | Zaimportuj `createTracker` z `universal-parcel-scraper/node`; `tracker.track({ number })` zwraca przewoźnika, to, kto odpowiedział, skany, bieżący etap i to, co pokazuje przewoźnik, na przykład przewidywane doręczenie |
| Przeglądarka | Tylko rozpoznawanie przewoźnika, przez `parseTrackingInput`: bez importów z Node i bez zapytań |
| HTTP | `parcel-scraper serve --port 8080` albo obraz Dockera `ghcr.io/plhery/universal-parcel-scraper`, tagowany wyłącznie numerami wydań; potem POST na `/v1/track`. Serwer trzyma odpowiedzi w pamięci podręcznej i ogranicza liczbę zapytań od klientów; nie ma bazy danych i nigdy sam niczego nie odpytuje |

Zanim zaczniesz na nim budować:

- **Odpowiada na jedno zapytanie naraz.** Zapisywanie przesyłek i ponowne ich sprawdzanie należą do Ciebie: to właśnie tę część dokłada Peek.
- **Niektóre strony przewoźników odpowiadają tylko przeglądarce.** Do nich potrzebuje Chromium, którym steruje przez opcjonalne pakiety, albo swojej usługi przeglądarkowej TRAWL. Obraz Dockera ma Chromium w środku.
- **Trackery zapasowe trzeba włączyć.** Domyślnie włączony jest tylko UPU; ParcelsApp, Ship24, 17TRACK, Postal Ninja i Track718 czekają, aż je włączysz, a każdy włączony dostaje numer przesyłki.
- **Zapytanie wysyła numer do przewoźnika**, razem z kodem pocztowym, linkiem albo cyframi telefonu, jeśli przewoźnik o nie prosi. Telemetrii nie ma.

## Czy mogę hostować własnego Peeka?

Tak. Samodzielnie hostowany Peek to jeden stale działający kontener z aplikacją webową, API i workerem, który wciąż odpytuje przewoźników, obok Supabase do logowania i Postgresa, za HTTPS. Potrzebujesz:

- **stacku Supabase** (Auth, PostgREST i Postgres) z przetestowaną kopią zapasową i odtwarzaniem;
- **SMTP**, jeśli włączone jest logowanie e-mailem albo e-mail o dostarczeniu;
- **hosta, który zbuduje Dockerfile i utrzyma kontener w działaniu.** Hosting serverless się nie nada: worker musi cały czas działać.

Web push, powiadomienia na iPhonie (zespół Apple i klucz APNs) i na Androidzie (Firebase) są opcjonalne. Asystenci też: bez `ASSISTANT_ACCOUNTS` nie ma `/mcp` ani `/oauth`. Ta zmienna wymienia konta, które mogą się łączyć, albo `*` dla wszystkich, i wymaga `CANONICAL_ORIGIN`. Gdy jest ustawiona, `PERSONAL_TOKENS=on` włącza osobiste tokeny dla Twoich skryptów, tworzone przez API. [docs/DEPLOYMENT.md](https://github.com/plhery/peek-delivery-tracker/blob/main/docs/DEPLOYMENT.md) opisuje kroki: od bazy danych i logowania po aktualizacje, kopie zapasowe i przenosiny na inny host.

### Najpierw uruchom go na swoim komputerze

Bez konta, bez bazy danych i bez pliku `.env`:

:::steps
- Pobierz kod | Sklonuj `https://github.com/plhery/peek-delivery-tracker.git`. Potrzebujesz nvm i wersji Node.js podanej w README.
- Zainstaluj | W jego folderze uruchom `nvm use`, a potem `npm ci`.
- Wystartuj | Wpisz `npm run dev` i otwórz localhost:3000: Peek odpowiada w przeglądarce wymyślonymi przesyłkami. Pod localhost:3000/demo jest lista przykładowych dostaw, które posuwają się naprzód przy każdym odświeżeniu.
:::

Do prawdziwych kont i śledzenia skopiuj `.env.example` do `.env.local`, wpisz dane projektu Supabase i ustaw `NEXT_PUBLIC_USE_API=true`; kroki logowania opisuje [docs/AUTHENTICATION.md](https://github.com/plhery/peek-delivery-tracker/blob/main/docs/AUTHENTICATION.md). Aplikację na iPhone’a zbudujesz w Xcode na symulator iPhone’a; startuje z przykładowymi przesyłkami, bez konta, serwera i zespołu Apple.

## Brakuje przewoźnika: jak go zgłosić albo dodać?

Zgłoś go do scrapera, bo przewoźnicy są właśnie tam. Na jego [stronie nowego zgłoszenia](https://github.com/plhery/universal-parcel-scraper/issues/new/choose), do której prowadzi link „Tell the scraper” w README Peeka, wybierz „Add a carrier” (nazwa przewoźnika, kraj macierzysty i publiczna strona śledzenia) albo „A carrier stopped working”. Nowi przewoźnicy i poprawki trafiają do scrapera jako pull requesty: każdy przewoźnik ma własny folder z syntetycznymi fixtures i testami offline, a `npm run carrier:new` tworzy szkielet nowego.

Peek sam przyjmuje nowe wydania scrapera, więc przewoźnik nie wymaga żadnego pull requesta do Peeka. A aplikacja na iPhone’a odświeża listę przewoźników z serwera, więc nie trzeba też nowego wydania aplikacji.

Nie masz konta na GitHubie? Daj znać Peekowi ze strony przesyłki, w przeglądarce, na iPhonie albo na Androidzie, z kontem Peeka lub bez:

:::steps
- Odpowiedz na pytanie | Gdy Peek nie znajduje przewoźnika dla przesyłki, jej strona pyta „Czy strona przewoźnika ją pokazuje?”. Wybierz „Tak”.
- Wskaż przewoźnika | Pod pytaniem „Kto ją wiezie?” wpisz go w polu „Przewoźnik lub sklep” albo wklej jego link do śledzenia w polu „Strona śledzenia, jeśli masz link”. Wystarczy jedno z dwóch.
- Wyślij | Wybierz „Wyślij”. Wraz z odpowiedzią idzie numer przesyłki i nic o Tobie.
:::

Te odpowiedzi czyta człowiek, ale nikt nie może Ci odpisać: jeśli chcesz odpowiedzi, napisz na hello@peektracker.com, adres podany na [stronie pomocy Peeka](https://peektracker.com/support.html). Jak Peek ustala przewoźnika na podstawie tego, co wklejasz, opisuje poradnik [Śledź przesyłkę, nawet gdy nie znasz przewoźnika](guide:track-without-carrier).

:::sources
- [Peek na GitHubie: README](https://github.com/plhery/peek-delivery-tracker) – co zawiera repozytorium, przewoźnicy w scraperze, uruchomienie lokalne, własny hosting, build na iPhone’a
- [Peek na GitHubie: LICENSE](https://github.com/plhery/peek-delivery-tracker/blob/main/LICENSE) – Apache License 2.0
- [Universal Parcel Scraper na GitHubie](https://github.com/plhery/universal-parcel-scraper) – polecenia, opcje, Node, przeglądarka, HTTP i Docker, Chromium w obrazie, trackery zapasowe, prywatność, liczba przewoźników, benchmark, licencje, dodawanie przewoźnika
- [Universal Parcel Scraper: new issue](https://github.com/plhery/universal-parcel-scraper/issues/new/choose) – formularze „Add a carrier” i „A carrier stopped working”
- [Universal Parcel Scraper na npm](https://www.npmjs.com/package/universal-parcel-scraper) – pakiet i wymagana wersja Node.js
- [Peek: plik OpenAPI](https://peektracker.com/api/openapi.json) – własne API aplikacji, trasy bez logowania i ich weryfikacja, odczyt przesyłek tokenem asystenta
- [Peek na GitHubie: Architecture](https://github.com/plhery/peek-delivery-tracker/blob/main/docs/ARCHITECTURE.md) – logowanie asystentów i Home Assistant, GET /api/parcels, osobiste tokeny wyłączone
- [Peek na GitHubie: konfiguracja asystentów na serwerze](https://github.com/plhery/peek-delivery-tracker/blob/main/src/server/assistants/settings.ts) – osobiste tokeny wyłączone, o ile PERSONAL_TOKENS nie jest włączone, ASSISTANT_ACCOUNTS
- [Peek: Połącz Peek z Claude, ChatGPT lub Gemini](https://peektracker.com/pl/assistants) – droga dla asystentów
- [Peek na GitHubie: Deployment](https://github.com/plhery/peek-delivery-tracker/blob/main/docs/DEPLOYMENT.md) – czego potrzeba do własnego hostingu, części opcjonalne, asystenci i osobiste tokeny na Twojej instancji
- [Peek na GitHubie: Contributing](https://github.com/plhery/peek-delivery-tracker/blob/main/CONTRIBUTING.md) – sprawdzenia przed pull requestem, przewoźnicy trafiają do scrapera, nowe wydania przyjmowane automatycznie
- [Peek na GitHubie: opinia o przesyłce](https://github.com/plhery/peek-delivery-tracker/blob/main/src/peek/parcel/Feedback.tsx) – „Kto ją wiezie?”, gdy nie znaleziono przewoźnika, do wysłania wystarczy jedno z dwóch pól
- [Peek: Help](https://peektracker.com/support.html) – zgłaszanie przewoźnika bez GitHuba, publiczne Issues, zgłoszenia bezpieczeństwa, kontakt
- [Peek: Terms of use](https://peektracker.com/terms.html) – darmowy, niekomercyjny projekt bez reklam
:::
