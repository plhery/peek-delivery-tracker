---
title: Śledź swoje przesyłki w Home Assistant
description: Integracja Peek dla Home Assistant pokazuje twoje przesyłki jako czujniki, kalendarz dostaw i kartę na pulpicie. Czego potrzebujesz i jak ją zainstalować.
slug: sledzenie-przesylek-home-assistant
picture: Tablet na ścianie w kuchni z pulpitem domu: lista przesyłek, mały kalendarz dostaw i Pip, paczka z buzią, uśmiechnięty na jednej z kart.
published: 2026-11-19
updated: 2026-11-21
---

Żeby śledzić przesyłki w Home Assistant, zainstaluj Peek for Home Assistant: integracja przenosi twoją listę przesyłek z [Peek](/) do Home Assistant jako czujniki, kalendarz dostaw, kartę na pulpit i narzędzie dla Assist. Potrzebujesz darmowego konta Peek i Home Assistant 2026.11 lub nowszego, gdzie wyszukujesz Peek w Marketplace; w wersji 2026.10 wyszukujesz go w HACS.

## Co Peek pokazuje w Home Assistant?

Twoje przesyłki z Peek, w formach, których używa Home Assistant:

| Co | Do czego służy |
| --- | --- |
| Czujniki | Liczą przesyłki w drodze, te, które docierają dzisiaj, te, które wymagają twojej uwagi (czekają w punkcie odbioru, nieudane doręczenie, cło), i dzisiejsze doręczenia, a także podają najbliższy dzień dostawy |
| Kalendarz „Deliveries” | Wydarzenia całodniowe: każda przesyłka w drodze w przewidywanym dniu, każde doręczenie w dniu, w którym nastąpiło |
| Zdarzenie „Parcel updates” | Uruchamia się, gdy przesyłka zostanie dodana, zmieni status, wyjedzie do doręczenia, zacznie wymagać twojej uwagi, zostanie doręczona albo zmieni się jej przewidywany dzień |
| Akcje | Dodawanie przesyłek z tekstu, zmiana nazwy przesyłki lub jej przewoźnika, wyłączanie i włączanie jej powiadomień, archiwizowanie lub przywracanie jej albo odczyt listy |
| Karta „Peek parcels” | Twoje przesyłki na pulpicie, jako lista albo karty z Pipem |
| Narzędzie dla Assist | Odpowiada na pytania typu „co dziś dotrze?” |

Home Assistant odczytuje dane z Peek co 30 minut, co 10 minut w dniu, w którym przesyłka jest w doręczeniu albo ma dotrzeć, i zaraz po tym, jak jedna z jego akcji zmieni przesyłkę. Żeby odczytać je od razu, wywołaj `homeassistant.update_entity` dla dowolnej encji Peek. „Dzisiaj” to dzień w strefie czasowej Home Assistant.

## Czego potrzebuję?

- **Home Assistant 2026.11 lub nowszego**, jak podaje strona Peek, i konta administratora: Marketplace w Home Assistant jest tylko dla administratorów. W wersji 2026.10 zainstaluj ją z HACS.
- **Konta Peek.** Konfiguracja otwiera logowanie do Peek: sześciocyfrowym kodem, który Peek wysyła ci e-mailem, albo przez Google lub Apple, jeśli ekran to proponuje. Pierwsze logowanie tworzy konto; gość nie może połączyć Home Assistant.
- **Niczego więcej.** Peek jest darmowy i nie trzeba wpisywać żadnego identyfikatora klienta, sekretu ani „Application Credentials”. Z każdym Home Assistant można połączyć jedno konto Peek.

Integracja łączy się tak samo jak asystenci, a połączenia Peek dla asystentów są w wersji beta. Jeśli Peek wyświetli komunikat „Peek nie może jeszcze łączyć asystentów z tym kontem.”, integracji nie da się jeszcze skonfigurować.

## Jak zainstalować integrację Peek?

Wyszukaj Peek w Marketplace w Home Assistant. W wersji 2026.10 wyszukaj go w HACS albo zainstaluj integrację ręcznie.

### Z Marketplace (Home Assistant 2026.11 i nowsze)

:::steps
- Otwórz „Settings › Marketplace” | Wyszukaj „Peek” i otwórz go.
- Zainstaluj Peek | Wybierz „Install”, a potem „Set up”.
- Zaloguj się do Peek | Przeglądarka otworzy Peek. Sprawdź, o co prosi Home Assistant (widzieć twoje przesyłki, dodawać je oraz zmieniać, archiwizować i przywracać), i wybierz „Zezwól”.
- Wróć do Home Assistant | Peek przekieruje cię z powrotem przez My Home Assistant (my.home-assistant.io). Za pierwszym razem zapyta o adres twojego Home Assistant.
:::

### Przez HACS (Home Assistant 2026.10)

:::steps
- Wyszukaj Peek | W HACS wyszukaj „Peek” i otwórz go.
- Pobierz Peek | Potem uruchom ponownie Home Assistant.
- Dodaj integrację | Przejdź do „Settings › Devices & services › Add integration”, wybierz Peek, zaloguj się do Peek i wybierz „Zezwól”.
:::

Jeśli Peek nie ma na liście, dodaj `https://github.com/plhery/peek-ha` przez link: w Marketplace przez „Add from link” (za pierwszym razem Marketplace poprosi o połączenie z GitHubem) albo w HACS jako własne repozytorium typu „Integration”.

Możesz też zainstalować ją ręcznie: skopiuj folder `custom_components/peek` do folderu `custom_components` w swojej konfiguracji, uruchom ponownie Home Assistant i dodaj integrację jak wyżej.

## Jak dodać kartę Peek parcels do pulpitu?

Integracja sama wczytuje swoją kartę na każdym pulpicie: nie trzeba dodawać żadnego zasobu. Strona otwarta już w trakcie konfiguracji Peek jeszcze tej karty nie ma: najpierw ją odśwież albo zamknij aplikację Home Assistant i otwórz ją ponownie.

:::steps
- Edytuj pulpit | Otwórz go i wybierz ołówek. Na pulpicie, który Home Assistant utworzył za ciebie, najpierw poprosi cię o przejęcie kontroli.
- Wybierz „Add card” | Potem zakładkę „By card”. Zakładka „By entity” wyszukuje encje, a nie karty.
- Wybierz „Peek parcels” | Wyszukaj „Peek”, wybierz „Peek parcels” i zapisz.
:::

Karta ma dwa style: listę, domyślną, z wierszem dla każdej przesyłki, w którym widać furgonetkę jej przewoźnika, etap i dzień; albo osobną kartę dla każdej przesyłki w kolorach przewoźnika, jak widżety Peek na iPhone’a i Androida, z Pipem, miejscowością ostatniego skanu, nazwą przesyłki, etapem i dniem. Oba style trzymają się sekcji Peek, zaczynając od przesyłek, które wymagają twojej uwagi, i kończą się napisem „+N more”, gdy przesyłek jest więcej niż wierszy.

Karta dopasowuje się do twojego motywu, jasnego lub ciemnego, i do twojego języka, jeśli jest to angielski lub francuski. W YAML to `type: custom:peek-parcels-card`; README integracji wymienia jej opcje.

## Czy mogę jej używać w automatyzacjach albo pytać Assist?

Tak. README zawiera cztery gotowe automatyzacje:

- powiadomienie na telefonie, gdy przesyłka wyjedzie do doręczenia;
- dodawanie numerów śledzenia z e-maili o wysyłce, odczytywanych przez integrację IMAP w Home Assistant;
- poranną wiadomość z listą tego, co dziś dotrze;
- archiwizowanie doręczonych przesyłek co wieczór.

Gdy dodajesz przesyłki z tekstu, Peek dodaje każdy znaleziony numer śledzenia, maksymalnie 10 naraz, i zapisuje tylko numery oraz podaną nazwę, więc w miarę możliwości wysyłaj tylko część e-maila o wysyłce.

Gdy jedna z encji Peek jest udostępniona dla Assist („Settings › Voice assistants › Expose”), możesz zapytać „co dziś dotrze?” albo gdzie jest twoja przesyłka z jakiegoś sklepu. Narzędzie Peek w Assist tylko odczytuje dane: nigdy nie dodaje, nie zmienia ani nie archiwizuje przesyłek. To, co zwraca (nazwy, sklepy, przewoźnicy, etapy, przewidywane dni i miejsce ostatniego skanu), trafia do wybranego przez ciebie agenta konwersacji, którym może być usługa w chmurze.

## Czego nie potrafi?

- **Usuwać przesyłek.** Home Assistant może widzieć, dodawać, zmieniać i archiwizować twoje przesyłki, ale nigdy ich nie usuwa. Jeśli Peek został połączony przed wersją 0.2.0 integracji, przy pierwszej zmianie trzeba będzie jeszcze raz zalogować się do Peek, tylko ten jeden raz.
- **Połączyć Claude, ChatGPT ani Gemini.** Łączą się z Peek same: zobacz poradnik [Śledź swoje przesyłki w ChatGPT, Claude lub Gemini](guide:ai-assistants).

## Co przechowuje Home Assistant i jak usunąć integrację?

Home Assistant łączy się tylko z Peek, pod adresem peektracker.com, i jak każda integracja przechowuje tokeny logowania w swojej konfiguracji. Jego historia zapisuje liczby z czujników i zdarzenia „Parcel updates” z nazwą przesyłki, przewoźnikiem, etapem i ostatnim miejscem, ale nie listy przesyłek z czujników. Karta działa w twojej przeglądarce na danych samego Home Assistant i niczego nie wczytuje z innych miejsc.

Aby ją usunąć, skasuj Peek w „Settings › Devices & services”: Home Assistant oddaje Peek swój dostęp, a Peek zapomina to połączenie, przy czym twoje przesyłki zostają w Peek. Jeśli Peek jest wtedy nieosiągalny albo chcesz odciąć Home Assistant po stronie Peek, wybierz „Odłącz” obok Home Assistant na stronie Peek (nie w aplikacjach), w „Ustawienia” → „Dostawy” → „Asystenci”. Integracja, którą potem zostawisz, pokaże „Authentication expired for Peek”; ponowna konfiguracja uruchamia to samo logowanie.

:::sources
- [Peek for Home Assistant: README](https://github.com/plhery/peek-ha) – co dodaje, akcje, wersje, instalacja, logowanie, karta, automatyzacje, Assist, prywatność, usuwanie
- [Peek for Home Assistant: hacs.json](https://github.com/plhery/peek-ha/blob/main/hacs.json) – najstarsza wersja Home Assistant, na której się instaluje: 2026.10
- [Home Assistant: Marketplace](https://www.home-assistant.io/integrations/marketplace/) – Settings › Marketplace, wyszukiwanie, Add from link, połączenie z GitHubem, Install, tylko administratorzy, zastępuje HACS od wersji 2026.11
- [HACS: domyślna lista integracji](https://github.com/hacs/default/blob/master/integration) – wpis Peek, plhery/peek-ha, w katalogu, który przeszukują HACS i Marketplace
- [Home Assistant na GitHubie: katalog Marketplace](https://github.com/home-assistant/core/blob/dev/homeassistant/components/marketplace/const.py) – Marketplace korzysta z domyślnej listy HACS, hacs/default
- [Peek: Połącz Peek z Claude, ChatGPT lub Gemini](https://peektracker.com/pl/assistants) – kroki dla Home Assistant na stronie Peek, 2026.11 lub nowszy
- [Peek na GitHubie: konfiguracja każdego asystenta](https://github.com/plhery/peek-delivery-tracker/blob/main/src/assistants/setups.tsx) – nazwy menu Home Assistant
- [Peek na GitHubie: teksty po polsku](https://github.com/plhery/peek-delivery-tracker/blob/main/shared/locales/pl.json) – wersja, kroki, konto, beta, co może Home Assistant
- [Peek na GitHubie: Ustawienia, Asystenci](https://github.com/plhery/peek-delivery-tracker/blob/main/src/components/Assistants.tsx) – połączeni asystenci, Odłącz
- [Peek: Help](https://peektracker.com/support.html) – logowanie sześciocyfrowym kodem
- [Peek na GitHubie: Terms](https://github.com/plhery/peek-delivery-tracker/blob/main/TERMS.md) – za darmo, bez opłat i abonamentu
:::
