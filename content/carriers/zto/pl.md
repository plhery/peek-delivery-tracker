---
title: Śledzenie ZTO Express: numery 中通快递, statusy i kontakt
description: Śledź paczkę ZTO Express (中通快递) w Chinach: format numerów, znaczenie 已签收 i pozostałych statusów, stacje i automaty, czas dostawy oraz infolinia 95311.
slug: sledzenie-zto-express
published: 2026-11-21
updated: 2026-11-21
---

ZTO Express (`中通快递`, Zhongtong) to chińska firma kurierska założona w **Szanghaju** 8 maja 2002 r.; w 2025 r. obsłużyła 38,52 mld przesyłek. Numer ZTO Express dotyczy zazwyczaj paczki krążącej na terenie **Chin kontynentalnych** — najczęściej zamówienia internetowego (ZTO doręcza przesyłki z Taobao od 2008 r.) jadącego pod adres w Chinach albo do magazynu spedytora międzynarodowego. Przesyłkę sprawdzisz w [chińskiej wyszukiwarce ZTO](https://www.zto.com/check) lub wklejając numer w polu powyżej. Paczki wysyłane przez grupę za granicę obsługuje ZTO International (`中通国际`) z własnymi numerami zaczynającymi się od `771` na stronie `ztoglobal.com`.

## Numer śledzenia ZTO Express: jak wygląda

ZTO nazywa go numerem listu przewozowego (`运单号`). Peek rozpoznaje dwa układy numerów ZTO Express. Przykłady z wymyślonymi cyframi:

| Numer (wymyślony) | Co oznacza |
| --- | --- |
| `784920573280` | 12 cyfr: list przewozowy ZTO Express — z takiego samego formatu korzystają m.in. [FedEx](carrier:fedex), [SF Express](carrier:sf-express), [DHL](carrier:dhl) oraz [BRT](carrier:brt). |
| `73184920573649` | 14 cyfr zaczynających się od `7`: również ZTO Express, format współdzielony z [DPD Niemcy](carrier:dpd-de), [Chronopost](carrier:chronopost), [SEUR](carrier:seur), [BRT](carrier:brt) i innymi sieciami. |
| `77148291057364` | `771` oraz 11 kolejnych cyfr: format ZTO International (`中通国际`). Peek w pierwszej kolejności odpytuje ZTO International. |

Spacje nie mają znaczenia przy wklejaniu do Peek. Oficjalny tracker ZTO przyjmuje do 10 numerów naraz, rozdzielonych przecinkami lub spacjami (`最多可查询10条，以逗号、空格隔开`).

**Gdzie go znaleźć.** Nadawca (zwykle sklep) zachowuje swój odcinek listu przewozowego (`发件联`) i podaje numer w szczegółach wysyłki. Nie widzisz go? Zobacz [jak znaleźć numer śledzenia](guide:find-tracking-number).

**Na stronie ZTO.** Wpisz numer na `zto.com/check` i kliknij `查询` (szukaj). Przed wyświetleniem wyników może pojawić się zagadka weryfikacyjna; zeskanowanie kodu QR z tej strony w aplikacji WeChat włącza powiadomienia na bieżąco.

## Statusy śledzenia ZTO Express i ich znaczenie

Każdy skan ma krótką chińską nazwę etapu, którą Peek pokazuje po angielsku:

| ZTO podaje | Peek wyświetla | Etap w Peek | Co to oznacza dla twojej paczki |
| --- | --- | --- | --- |
| `已取件` | `Collected` | „Przyjęta przez przewoźnika” | Kurier ZTO odebrał przesyłkę od nadawcy. |
| `已收件` | `Received` | „Przyjęta przez przewoźnika” | Placówka ZTO przyjęła paczkę do systemu. |
| `运输中` | `In transit` | „W transporcie” | W drodze między sortowniami a oddziałami ZTO. |
| `派件中` | `Out for delivery` | „W doręczeniu” | Kurier ZTO zabrał paczkę w trasę doręczeń. |
| `已代收` | `Held at a collection point for the recipient` | „Do odbioru” | Punkt odbioru (np. stacja paczkowa) przechowuje przesyłkę do odebrania. |
| `已签收` | `Signed for` | „Dostarczona” | Ktoś pokwitował odbiór (`签收`): ty, osoba upoważniona lub magazyn docelowy. |
| `异常通知` | `Exception notice` | „Zgłoszono problem” | ZTO odnotowało utrudnienie. Peek pokazuje samą nazwę etapu; pełny opis znajduje się w trackerze ZTO. |

Pozostałe wpisy, takie jak zwrot (`退回件`) czy przekierowanie, zachowują w Peek oryginalne chińskie brzmienie. Więcej o ogólnych etapach w poradniku [co oznaczają statusy śledzenia](guide:tracking-statuses).

**Wysłana do spedytora?** Wtedy `已签收` oznacza, że magazyn spedytora w Chinach pokwitował odbiór i krajowy odcinek ZTO Express dobiegł końca — dalszą drogę za granicę śledzisz pod nowym numerem spedytora. ZTO prowadzi też własną usługę konsolidacji międzynarodowej (`中通国际集运`), łączącą kilka paczek w magazynie w jedną przesyłkę zbiorczą (kontakt w Chinach kontynentalnych pod numerem `95311`, dział międzynarodowy `95311转国际`).

## Doręczenia ZTO Express w Chinach: kurierzy, stacje i automaty

Poniższe zasady obowiązują przy doręczeniach na terenie **Chin kontynentalnych**:

Kurier ZTO przynosi paczkę pod adres albo zostawia ją w pobliskiej stacji paczkowej (`驿站`), punkcie odbioru (`代收点`) lub inteligentnym automacie paczkowym (`智能快件箱`) — umowa o świadczenie usług ZTO dopuszcza te formy. Chińskie *Przepisy o zarządzaniu rynkiem usług kurierskich* (`快递市场管理办法`), obowiązujące od 1 marca 2024 r., wymagają jednak twojej zgody na umieszczenie przesyłki w automacie lub stacji i dają prawo sprawdzenia paczki przy odbiorze z listem przewozowym, a przy widocznym uszkodzeniu opakowania lub delikatnej zawartości — zajrzenia do środka albo odmowy przyjęcia.

W przypadku paczek pozostawionych bez osobistego przekazania (pod drzwiami, w automacie lub stacji) umowa ZTO wymaga nagrania wideo (`录制视频`) z odbioru, otwarcia i sprawdzenia zawartości jako dowodu; doręczenie wykonane zgodnie z ustaleniami uznaje się za potwierdzone, jeśli w ciągu trzech dni (`三日`) nikt nie zgłosi pisemnego sprzeciwu.

Według oficjalnego FAQ ZTO w Chinach:

- **Brak kodu odbioru do automatu?** Wyszukaj oddział obsługujący twój adres w [wyszukiwarce oddziałów ZTO](https://www.zto.com/service/station) (`服务网点查询`) i zadzwoń tam albo podaj numer listu na infolinii `95311`, aby uzyskać telefon do kuriera lub oddziału.
- **Automat prosi o dopłatę za przeterminowany odbiór?** ZTO informuje, by przy odbiorze stuknąć pomiń (`跳过`) — nie zapłacisz wtedy nic.
- **Żądanie opłaty za doręczenie?** Oddział doręczający nie ma prawa pobierać od odbiorcy żadnej opłaty za doręczenie (`派件网点不可以向收件客户收取任何派送费`).
- **Adres poza strefą doręczeń?** Odbierz paczkę osobiście w oddziale (`自提`) lub poproś nadawcę o przeadresowanie inną firmą kurierską.
- **Błędny adres?** Gdy przesyłka jest już w doręczeniu lub doręczona, adresu nie da się zmienić; wcześniej nadawca musi skontaktować się z oddziałem nadania.

## Paczka ZTO nie aktualizuje się, zaginęła lub wraca

**Brak wpisów.** Jeśli wyszukiwarka ZTO nic nie znajduje, potwierdź numer u sprzedawcy. Do tego czasu przesyłka zapisana pod ZTO Express ma status „Oczekiwanie na przewoźnika ZTO Express”, a bez przypisanego przewoźnika — „Brak historii śledzenia”.

**Brak nowych skanów.** Gdy paczka będąca nadal w drodze nie ma nowego skanu od 4 dni, Peek wyświetla komunikat „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika ZTO Express.” oraz link „Poproś przewoźnika ZTO Express o pomoc” prowadzący do strony kontaktowej ZTO. Sprawdź [dlaczego śledzenie się nie aktualizuje](guide:tracking-not-updating) i [śledzenie paczki z Chin](guide:tracking-from-china).

**Zagubienie lub uszkodzenie (w Chinach kontynentalnych).** Zgłoś się do sprzedawcy: według umowy ZTO nadawca (lub wskazana przez niego osoba uprawniona) składa pisemną reklamację w ciągu 30 dni (`三十日`) od powzięcia wiadomości o szkodzie. Bez ubezpieczenia (`保价`) ZTO wypłaca maksymalnie 500 juanów (`500元`), a z ubezpieczeniem — do kwoty ubezpieczenia. Braki lub uszkodzenia wewnątrz nienaruszonego opakowania zewnętrznego są wyłączone. Zobacz [zagubiona paczka](guide:lost-parcel).

**Zwrot do nadawcy.** W Chinach kontynentalnych ZTO może odesłać paczkę, gdy adres jest niejasny, odbiorca odmówi przyjęcia lub dwie próby doręczenia nie powiodą się bez winy ZTO (`两次派送未成功的`). Zobacz [zwrot do nadawcy](guide:returned-to-sender).

**Oznaczona jako doręczona, ale jej nie ma.** `已代收` oznacza oczekiwanie w punkcie odbioru, a `已签收` — pokwitowanie odbioru (przez recepcję, stację lub magazyn spedytora). Aby złożyć skargę w Chinach kontynentalnych, podaj numer listu na infolinii `95311` albo skorzystaj z [formularza reklamacyjnego ZTO](https://www.zto.com/after/complaint/problemTypes) (`投诉反馈`, wymaga logowania); w razie braku satysfakcjonującej lub terminowej odpowiedzi chińskie przepisy pozwalają wnieść odwołanie (`申诉`) do organu nadzoru pocztowego (`邮政管理部门`). Zobacz [dostarczona, ale nieotrzymana](guide:delivered-not-received).

## Numer telefonu i obsługa klienta ZTO Express

Oficjalne kanały kontaktu ZTO Express w **Chinach kontynentalnych**:

- **`95311`:** ogólnokrajowa infolinia obsługi klienta (`全国统一客服热线`, na angielskiej stronie ZTO *Customer Service Hotline*), czynna według FAQ w godzinach `08:30`–`17:30` czasu chińskiego.
- **`95720`:** dedykowany numer wychodzący, z którego dzwonią kurierzy ZTO (`中通业务员专属外呼号码`).
- **Obsługa online** (`在线客服`) na `zto.com`, a także miniprogram WeChat (`微信小程序`), oficjalne konto WeChat i aplikacja mobilna ZTO.

W sprawie samego zamówienia skontaktuj się w pierwszej kolejności ze sprzedawcą.

## Śledzenie przesyłki ZTO Express w Peek

Wklej numer w [Peek](/): obsługuje ponad 4 500 przewoźników. Samo wklejenie numeru ZTO nie powoduje automatycznego odpytania ZTO Express: Peek sprawdza najpierw wybranych innych przewoźników używających tego samego układu cyfr (np. [DHL](carrier:dhl) i [BRT](carrier:brt) dla 12 cyfr; DPD, [Chronopost](carrier:chronopost), [Hermes](carrier:hermes-de), [SEUR](carrier:seur) i [BRT](carrier:brt) dla 14 cyfr; najpierw ZTO International dla numerów `771`), a potem usługi śledzenia, pokazując „Sprawdzanie usług śledzenia…”. Jeśli znajdą one przesyłkę i wskażą ZTO Express, Peek zapisze ją pod ZTO Express i odpyta ZTO przy kolejnym sprawdzeniu; jeśli nic się nie pojawi, zobaczysz „jeszcze nie znaleziono · Peek sprawdza dalej”. Aby od razu czytać oficjalny tracker ZTO Express, naciśnij „Zmień” i wybierz **ZTO Express** w sekcji „Pasują do numeru” albo wyszukaj `ZTO` lub `中通快递` (samo `中通` pokazuje też ZTO International); na dodanej już paczce użyj „Zmień przewoźnika”. ZTO Express przyjmuje w Peek wyłącznie 12 cyfr lub 14 cyfr od `7` — przy innym numerze pojawia się komunikat „nie wydaje takich numerów”.

Peek odczytuje własny tracker ZTO i otwiera przeglądarkę, gdy ZTO zażąda weryfikacji. Dla każdego skanu pokazuje angielską nazwę etapu (tabela wyżej), miasto lub chińską nazwę placówki ZTO oraz czas w strefie twojego urządzenia (na peektracker.com oraz w aplikacjach na iPhone'a i Androida pod historią widnieje „Godziny w twojej strefie czasowej: …”). Pomija pełne zdanie opisu skanu wraz z kontaktami do kurierów, numerami telefonów oraz imieniem i adresem odbiorcy, i nie pokazuje szacowanej daty dostawy. Ponieważ publiczny tracker ZTO na komputerze potrafi ukrywać najwcześniejsze wpisy zamówienia, może ich brakować również w Peek. Numer międzynarodowy od spedytora dodaj jako osobną przesyłkę.

Dopóki masz otwartą paczkę lub w dniu doręczenia włączone powiadomienia, Peek sprawdza zmiany nawet co 10 minut — a od 6:00 czasu lokalnego w dniu zeskanowania paczki jako „W doręczeniu” nawet co 2 minuty (co 5 minut, gdy zapytanie do ZTO otworzyło przeglądarkę), dopóki odpowiada tracker ZTO (przez same usługi śledzenia: co godzinę, co 3 godziny po dobie bez ruchu i do co 15 minut w dniu dostawy przy otwartej paczce lub włączonych powiadomieniach). W pozostałych przypadkach oraz po dwóch dniach ciszy (lub 12 godzinach na etapie „Zarejestrowana” bądź „Przyjęta przez przewoźnika”) Peek sprawdza co godzinę. Alerty do wyboru: „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek nie skontaktuje się za ciebie z ZTO, spedytorem ani sprzedawcą.

## Najczęstsze pytania o śledzenie ZTO Express

### Czy ZTO Express to wiarygodna firma?

Tak. ZTO Express powstało w Szanghaju 8 maja 2002 r., zadebiutowało na giełdzie w Nowym Jorku 27 października 2016 r., a w Hongkongu 29 września 2020 r. (jako pierwsza firma kurierska notowana na obu tych rynkach). W 2025 r. jej sieć liczyła ponad 31 000 placówek i docierała do ponad 99 % powiatów i dzielnic w Chinach.

### Czy ZTO Express to to samo co ZTO International?

To jedna grupa kapitałowa, ale dwie osobne usługi i wyszukiwarki. Umowa ZTO Express obejmuje wyłącznie przesyłki nadawane i doręczane w Chinach kontynentalnych (bez Hongkongu, Makau i Tajwanu), śledzone na `zto.com/check`; ZTO International, międzynarodowe ramię grupy działające od 2014 r., obsługuje paczki transgraniczne z numerami `771` na `ztoglobal.com`.

### Ile trwa dostawa ZTO Express w Chinach?

Według FAQ ZTO (czasy orientacyjne) przesyłki wewnątrz jednej prowincji oraz między Jiangsu, Zhejiang, Szanghajem i Anhui docierają zwykle następnego dnia, między pozostałymi prowincjami w 3–5 dni, a do niektórych odległych rejonów w 5–7 dni. Przy wysyłce do magazynu konsolidacyjnego dochodzi potem czas transportu międzynarodowego spedytora.

### Co oznacza status „已签收” w śledzeniu ZTO?

Odebrano za pokwitowaniem: Peek pokazuje `Signed for` i etap „Dostarczona”. Podpisać mógł odbiorca, inna osoba w jego imieniu, recepcja budynku albo magazyn spedytora. Inne znaczenie ma `已代收` („Do odbioru”): paczka czeka na ciebie w punkcie odbioru lub stacji paczkowej.

### Jaki jest numer telefonu do ZTO Express?

`95311` — ogólnokrajowa infolinia obsługi klienta w Chinach kontynentalnych, czynna od `08:30` do `17:30` czasu chińskiego. Połączenia z numeru `95720` pochodzą tam od kurierów ZTO.

:::sources
- [ZTO Express: 运单查询 (wyszukiwanie przesyłek)](https://www.zto.com/check) – chiński tracker, do 10 numerów naraz i powiadomienia WeChat
- [ZTO Express: 关于中通 (o firmie)](https://www.zto.com/introduce/about/home) – założenie w 2002 r., debiuty giełdowe, 38,52 mld paczek i sieć w 2025 r., Taobao od 2008 r.
- [ZTO Express: 首页 (strona główna)](https://www.zto.com/) – 95311, 95720, aplikacja i WeChat
- [ZTO Express: Home (angielski)](https://en.zto.com/) – informacja Customer Service Hotline: 95311
- [ZTO Express: 联系我们 (kontakt)](https://www.zto.com/introduce/about/contact) – obsługa klienta online
- [ZTO Express: 常见问题 (FAQ)](https://www.zto.com/after/answers) – godziny 08:30–17:30, automaty, zakaz opłat za doręczenie, poza strefą, zmiana adresu i czasy dostawy
- [ZTO Express: 服务网点查询 (wyszukiwarka oddziałów)](https://www.zto.com/service/station) – oddział obsługujący dany adres
- [ZTO Express: 投诉反馈 (reklamacje)](https://www.zto.com/after/complaint/problemTypes) – formularz skarg po zalogowaniu
- [ZTO Express: 快递服务协议 (umowa o świadczenie usług)](https://mtoc.zto.com/agreement/service-protocol-pc.html) – zakres Chiny kontynentalne, sposoby doręczenia, wideo i termin 3 dni, zwroty, limit 500 juanów i reklamacja w 30 dni
- [ZTO Express: 中通国际 (ZTO International)](https://www.zto.com/introduce/service/international) – międzynarodowa spółka grupy od 2014 r.
- [ZTO Express: 中通集运 (konsolidacja)](https://www.zto.com/introduce/service/concentrated) – konsolidacja międzynarodowa i kontakt 95311
- [Ministerstwo Transportu Chin: 快递市场管理办法](https://xxgk.mot.gov.cn/gz/202401/t20240104_3980684.html) – sprawdzenie przy odbiorze, zgoda na automat/stację i odwołanie pocztowe
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formaty numerów, angielskie nazwy etapów, weryfikacja w przeglądarce, brak automatycznego rozpoznawania i pomijane pola
:::
