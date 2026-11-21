---
title: Śledzenie przesyłki Amazon: gdzie znaleźć numer śledzenia
description: Gdzie Amazon.pl pokazuje przewoźnika i numer śledzenia twojej paczki i jak śledzić ją w Peek: samodzielnie albo z Claude lub ChatGPT, które zrobią to za ciebie.
slug: sledzenie-zamowienia-amazon
picture: Telefon pokazuje postęp zamówienia, dwie furgonetki przekazują sobie paczkę, a Pip się przygląda.
published: 2026-10-16
updated: 2026-11-21
---

Amazon pokazuje przewoźnika i numer śledzenia każdej paczki w „Moje zamówienia”, a potem w „Śledź przesyłkę”. Skopiuj numer do [Peek](/), a będziesz śledzić paczkę razem ze wszystkimi innymi. Na każdej stronie Amazona działa to tak samo.

## Gdzie na Amazonie znajdę numer śledzenia?

:::steps
- Otwórz „Moje zamówienia” | Na stronie znajdziesz je w prawym górnym rogu. W aplikacji Amazon dotknij ikony osoby, a potem „Moje zamówienia”.
- Wybierz „Śledź przesyłkę” | Przycisk jest obok każdej paczki w drodze albo właśnie dostarczonej. Jeśli zamówienie przychodzi w kilku paczkach, każda ma własny.
- Skopiuj numer śledzenia | Pogrubiona jest nazwa przewoźnika, na przykład InPost, a tuż pod nią jego numer śledzenia.
- Wklej go do Peek | Peek rozpozna przewoźnika i będzie śledzić paczkę.
:::

:::picture amazon-orders/orders-pl.webp 480x300
„Moje zamówienia” na Amazonie: zamówienie z wyróżnionym obok przyciskiem „Śledź przesyłkę”. Przykładowe zamówienie, nie prawdziwe.
:::

:::picture amazon-orders/track-pl.webp 480x300
Strona „Śledź przesyłkę” na Amazonie: przewoźnik InPost, a tuż pod nim numer śledzenia, oba wyróżnione. Przykładowe dane.
:::

Na innych stronach Amazona te same przyciski nazywają się tak:

| Strona | Moje zamówienia | Śledź przesyłkę |
| --- | --- | --- |
| amazon.com, amazon.co.uk | Your Orders | Track package |
| amazon.de | Meine Bestellungen | Sendung verfolgen |
| amazon.fr | Vos commandes | Suivre le colis |
| amazon.it | I miei ordini | Traccia il mio pacco |
| amazon.es | Mis pedidos | Localiza tu paquete |

### Brak numeru śledzenia?

- **Nowe zamówienie.** Numer zwykle pojawia się, gdy przewoźnik ma już paczkę.
- **Amazon dostarcza sam.** Zamówienie może nie pokazywać żadnego numeru albo numer Amazona: dwie litery kraju i 10 cyfr, np. `PL1234567890`. Mimo to wklej go do Peek: Peek będzie go śledzić, jeśli Amazon pokazuje go w publicznym śledzeniu, a jeśli widać go tylko na twoim koncie Amazon, Peek ci o tym powie.
- **Numer zamówienia,** trzy grupy cyfr, np. `123-1234567-1234567`, nigdy nie jest numerem śledzenia.

## Czy Claude albo ChatGPT mogą to zrobić za mnie?

Tak, jeśli asystent może korzystać z przeglądarki, w której logujesz się na Amazonie: Claude in Chrome w płatnym planie Claude albo rozszerzenie przeglądarki ChatGPT, które konfigurujesz w aplikacji ChatGPT na komputer. Potrzebny jest do tego komputer: z aplikacji w telefonie albo przez przeglądarkę, którą uruchamia na własnych serwerach, asystent nie dostanie się do twoich zamówień, a e-maile od Amazona nie podają numeru śledzenia.

### Bez konektora Peek

Skopiuj to do asystenta:

> Otwórz w tej przeglądarce „Moje zamówienia” na Amazonie. Dla każdej paczki, która jest w drodze albo właśnie dotarła, otwórz „Śledź przesyłkę” i zanotuj przewoźnika oraz numer śledzenia, nigdy numer zamówienia. Wypisz je tutaj, po jednej paczce w wierszu, a potem napisz mi, że listę mam wkleić na peektracker.com. Na Amazonie tylko czytaj: niczego nie kupuj, nie anuluj, nie zwracaj ani nie zmieniaj i nie loguj się za mnie.

Potem wklej całą listę do [Peek](/): znajdzie w niej każdy numer.

### Z Peek w asystencie

Połącz Peek raz. W Claude dodaj „Peek Parcel Tracker” z [katalogu konektorów Claude](https://claude.ai/directory/peek-parcel-tracker). W ChatGPT dodaj Peek jako wtyczkę, w planie Plus, Pro, Business, Enterprise lub Edu. [Tu są wszystkie kroki](guide:ai-assistants).

Potem napisz:

> Zaimportuj moje paczki z Amazona do Peek.

Peek mówi asystentowi, gdzie Amazon pokazuje numery. Asystent dodaje każdą paczkę z nazwą jej produktu i mówi ci, które Amazon śledzi tylko na twoim koncie.

W obu przypadkach asystent na Amazonie tylko czyta. Jeśli Amazon poprosi o zalogowanie, zaloguj się samodzielnie w tym oknie, a potem poproś jeszcze raz.

Paczka się spóźnia albo jest oznaczona jako dostarczona, ale jej nie ma? Przeczytaj [paczka dostarczona, ale jej nie ma](guide:delivered-not-received) i [zaginiona paczka](guide:lost-parcel).

:::sources
- [Pomoc Amazon.pl: Śledzenie paczki](https://www.amazon.pl/gp/help/customer/display.html?nodeId=GENAFPTNLHV7ZACW) – „Moje zamówienia”, „Śledź przesyłkę”, numery śledzenia; ta sama strona na amazon.com, .co.uk, .de, .fr, .it i .es podaje ich słowa
- [Pomoc Amazon.pl: Informacje kontaktowe przewoźników](https://www.amazon.pl/gp/help/customer/display.html?nodeId=GBPEXEXYULHB5WCH) – nazwa przewoźnika i numer śledzenia w „Śledź przesyłkę”, jeden przewoźnik na paczkę
- [Amazon Help: Missing Tracking Information (po angielsku)](https://www.amazon.com/gp/help/customer/display.html?nodeId=G6ZFEB9ZDU7QHMER) – brak numeru, gdy paczka jedzie do przewoźnika
- [Amazon Help: About Deliveries Shipped with Amazon (po angielsku)](https://www.amazon.com/gp/help/customer/display.html?nodeId=GEW3XT9JEMBLTKRV) – zamówienia, które Amazon doręcza sam
- [Pomoc Amazon.pl: Zwrot prezentu](https://www.amazon.pl/gp/help/customer/display.html?nodeId=GMTDJ6XPUMN7NWNB) – 17-cyfrowy numer zamówienia
- [About Amazon: How to track your packages (po angielsku)](https://www.aboutamazon.com/news/retail/track-amazon-package) – zamówienia w prawym górnym rogu strony, ikona osoby w aplikacji, „Track package”
- [Claude Help Center: Get started with Claude in Chrome (po angielsku)](https://support.claude.com/en/articles/12012173-get-started-with-claude-in-chrome) – płatne plany Claude, panel boczny, Claude in Chrome wśród konektorów czatu
- [ChatGPT: Browser extension (po angielsku)](https://learn.chatgpt.com/docs/chrome-extension) – konfiguracja z aplikacji ChatGPT na komputer; czyta strony, na których korzystasz z konta po zalogowaniu, i korzysta też z wtyczek
- [Peek: Połącz Peek z Claude, ChatGPT lub Gemini](https://peektracker.com/pl/assistants) – kroki Peek dla Claude i ChatGPT
- [Peek na GitHubie: narzędzia asystentów](https://github.com/plhery/peek-delivery-tracker/blob/main/src/server/assistants/tools.ts) – co Peek mówi asystentowi o Amazonie: Your Orders, Track package, „Delivery By”, „Tracking ID”, tylko czytanie
- [Peek na GitHubie: teksty po polsku](https://github.com/plhery/peek-delivery-tracker/blob/main/shared/locales/pl.json) – kilka numerów w jednym wklejonym tekście
- [Universal Parcel Scraper: dane przewoźników](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers) – kształty numerów Amazona
:::
