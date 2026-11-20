---
title: Śledzenie przesyłki Amazon: gdzie znaleźć numer śledzenia
description: Gdzie Amazon.pl pokazuje przewoźnika i numer śledzenia Twojej paczki, na stronie i w aplikacji, i jak śledzić ją w Peek: ręcznie albo z Claude lub ChatGPT.
slug: sledzenie-zamowienia-amazon
picture: Telefon pokazuje postęp zamówienia, dwie furgonetki przekazują sobie paczkę, a Pip się przygląda.
published: 2026-10-16
updated: 2026-11-20
---

Amazon pokazuje przewoźnika i numer śledzenia Twojej paczki w jednym miejscu: na stronie „Moje zamówienia”, w opcji „Śledź przesyłkę”. Skopiuj numer do [Peek](/), a będziesz śledzić tę paczkę obok wszystkich innych. Zajmuje to minutę i działa tak samo na amazon.pl i na każdej innej stronie Amazona. Wyjątkiem jest paczka, którą Amazon doręcza sam: zwykle śledzisz ją tylko na swoim koncie Amazon.

> Używasz Claude albo ChatGPT w Chrome? [Poproś, żeby zrobił to za Ciebie](#czy-claude-albo-chatgpt-moga-dodac-za-mnie-paczki-z-amazona).

## Gdzie na Amazonie znajdę numer śledzenia?

Na stronie „Śledź przesyłkę” każdej paczki. Tak tam trafisz:

:::steps
- Otwórz „Moje zamówienia” | Na stronie wybierz „Zamówienia” w prawym górnym rogu. W aplikacji Amazon stuknij ikonę osoby na dole ekranu, a potem przejdź do zamówień.
- Znajdź zamówienie | Najnowsze zamówienia są na górze. Gdy zamówienie przychodzi w kilku paczkach, każda paczka ma własny przycisk „Śledź przesyłkę”.
- Wybierz „Śledź przesyłkę” | Na stronie ten przycisk jest obok zamówienia. W aplikacji najpierw stuknij produkt.
- Znajdź przewoźnika i numer | Strona podaje przewoźnika, na przykład InPost albo DPD, i jego numer śledzenia. Pomoc Amazona nazywa go identyfikatorem przesyłki. Jeśli go nie widzisz, otwórz wszystkie aktualizacje przesyłki: Amazon podaje tam numer.
- Wklej go do Peek | Peek rozpozna przewoźnika po numerze i zacznie śledzić paczkę.
:::

### Kiedy pojawia się numer?

Zwykle po wysłaniu paczki. Nowe zamówienie może jeszcze nie mieć numeru: paczka jedzie dopiero do przewoźnika, a numer pojawia się po jego pierwszym skanie. Czasem numer widać już wcześniej. Wklej go i tak: Peek pokaże pierwszą aktualizację, gdy tylko przewoźnik ją poda.

Paczkę z centrum logistycznego za granicą da się śledzić dopiero wtedy, gdy dotrze do centrum dystrybucyjnego przewoźnika w Polsce. Paczka od sprzedawcy ma numer tylko wtedy, gdy sprzedawca wybrał wysyłkę ze śledzeniem i przekazał numer Amazonowi.

E-maile od Amazona zwykle nie zawierają numeru śledzenia. Przycisk śledzenia w tych e-mailach prowadzi na tę samą stronę.

### Czy to działa też na amazon.de i innych stronach Amazona?

Tak. Każda strona Amazona działa tak samo. Zmieniają się tylko słowa, zależnie od języka strony:

| Strona | Moje zamówienia | Śledź przesyłkę |
| --- | --- | --- |
| amazon.pl | Moje zamówienia | Śledź przesyłkę |
| amazon.de | Meine Bestellungen | Sendung verfolgen |
| amazon.com, amazon.co.uk | Your Orders | Track package |
| amazon.fr | Vos commandes | Suivre le colis |
| amazon.it | I miei ordini | Traccia il mio pacco |
| amazon.es | Mis pedidos | Localiza tu paquete |

## Który numer jest numerem śledzenia?

Zamówienie na Amazonie może pokazywać trzy rodzaje numerów. Tylko numer śledzenia przewoźnika zawsze pozwala śledzić paczkę poza Amazonem.

- **Numer śledzenia przewoźnika.** Nadaje go przewoźnik: InPost, DPD, DHL, Poczta Polska, UPS i inni. Amazon pokazuje go obok nazwy przewoźnika. Peek śledzi go jak każdą inną paczkę.
- **Numer zamówienia.** Trzy grupy cyfr połączone myślnikami, na przykład `123-1234567-1234567`. Jest w zamówieniu, na fakturze i w e-mailach od Amazona. To nigdy nie jest numer śledzenia: gdy wkleisz go do Peek, Peek odpowie „To wygląda na numer zamówienia Amazon”.
- **Numer Amazona.** Amazon nadaje go paczkom, które doręcza jego własna sieć, a Peek sprawdza go w Amazonie: [zobacz niżej](#czy-peek-sledzi-numer-tba-od-amazona). Wygląda tak, tu z wymyślonymi cyframi:

:::anatomy TBA 123456789012
- TBA | Prefiks Amazona. Także `TBC` (Kanada) i `TBM` (Meksyk) albo `TBAU` z 9 cyframi (Australia)
- 123456789012 | Dwanaście cyfr
:::

W Europie numer Amazona może też składać się z dwóch liter kraju, na przykład `PL` albo `DE`, i 10 cyfr. Nie wiesz, który numer masz? Wpisz go tutaj:

:::checker
:::

Paczka może po drodze zmienić przewoźnika: Amazon przekazuje niektóre paczki partnerowi na ostatni etap dostawy. „Śledź przesyłkę” podaje wtedy tego partnera: skopiuj jego numer.

## Czy Peek śledzi numer TBA od Amazona?

Czasem. Gdy wkleisz numer Amazona, Peek najpierw sprawdza go w publicznym systemie śledzenia Amazona:

- **Amazon go zna.** To paczka, którą Amazon doręcza dla innej firmy, w ramach [Amazon Shipping](carrier:amazon-shipping). Peek śledzi ją jak każdą inną.
- **Amazon go nie zna.** Wtedy najpewniej [Amazon Logistics](carrier:amazon-logistics) doręcza Twoje własne zamówienie z Amazona, a Amazon pokazuje je tylko na Twoim koncie. Peek nie doda tego numeru. Powie Ci o tym i zaproponuje przycisk „Otwórz moje zamówienia Amazon”, który otwiera Twoje zamówienia na właściwej stronie Amazona. Peek nigdy nie prosi o hasło do Amazona.
- **Nie ma połączenia z Amazonem.** Peek powie Ci o tym i zaproponuje „Sprawdź ponownie”.

Gdy Amazon doręcza paczkę sam, zamówienie może nie pokazywać żadnego numeru śledzenia. Nie ma wtedy czego kopiować: tę paczkę śledzi tylko strona „Śledź przesyłkę” na Amazonie.

## Czy Claude albo ChatGPT mogą dodać za mnie paczki z Amazona?

Tak, jeśli asystent może korzystać z Twojej przeglądarki. Te dwa potrafią:

- **Claude in Chrome,** rozszerzenie Claude do Chrome, w płatnych planach Claude.
- **Rozszerzenie ChatGPT do Chrome,** które instalujesz i obsługujesz z aplikacji ChatGPT na komputer.

Oba działają w tym oknie Chrome, w którym korzystasz z Amazona po zalogowaniu. Poproś, a asystent otworzy „Moje zamówienia”, przejdzie przez każdą stronę „Śledź przesyłkę” i zanotuje każdego przewoźnika i numer śledzenia.

Co dalej, zależy od tego, czy Peek jest połączony z asystentem:

- **Jeśli Peek jest połączony** i asystent może z niego korzystać w tym czacie, sam doda paczki do Twojego konta Peek, każdą z nazwą produktu. W Claude otwórz „Peek Parcel Tracker” w [katalogu konektorów Claude](https://claude.ai/directory/peek-parcel-tracker), wybierz „Connect”, zaloguj się do Peek i wybierz „Zezwól”. W ChatGPT Peek można dodać jako wtyczkę w planie Business, Enterprise lub Edu. Wszystkie kroki znajdziesz w poradniku [jak śledzić przesyłki w ChatGPT lub Claude](guide:ai-assistants).
- **Jeśli nie jest,** asystent wypisze numery w czacie. Skopiuj całą listę i wklej ją do [Peek](/): Peek wyłowi z niej każdy numer. Po zalogowaniu śledzi je wszystkie, bez logowania po jednym.

Wklej asystentowi to polecenie:

> Otwórz w tej przeglądarce „Moje zamówienia” na Amazonie. Dla każdej paczki, która jest w drodze albo właśnie dotarła, otwórz „Śledź przesyłkę” i zanotuj przewoźnika oraz numer śledzenia. Nigdy nie używaj numeru zamówienia (trzy grupy cyfr połączone myślnikami). Jeśli Peek jest połączony, dodaj każdą paczkę do Peek z nazwą jej produktu. Jeśli nie, wypisz tu numery, po jednym w wierszu. Napisz mi, które paczki nie mają numeru śledzenia. Na Amazonie tylko czytaj: niczego nie kupuj, nie anuluj, nie zwracaj ani nie zmieniaj i nie loguj się za mnie.

W obu przypadkach asystent ma na Amazonie tylko czytać. Nie powinien niczego kupować, anulować ani zwracać i nigdy nie powinien logować się za Ciebie. Jeśli Amazon poprosi o zalogowanie, zaloguj się samodzielnie w tym oknie, a potem poproś jeszcze raz. Gdy paczka nie ma numeru śledzenia albo Peek jej nie przyjmie, Amazon śledzi ją tylko na Twoim koncie.

## Co zrobić, gdy paczka z Amazona się spóźnia albo ma status „Dostarczono”?

Kto pomoże, zależy od tego, kto ją wysłał. Pokazuje to strona produktu, pod przyciskami zakupu, a na stronie „Moje zamówienia” widać nazwę sprzedawcy. Amazon zajmuje się własnymi zamówieniami i tymi, które wysyła za sprzedawców. Sprzedawca, który wysyła sam, pierwszy odpowiada na pytania o dostawę.

:::steps
- Sprawdź „Śledź przesyłkę” | Tam jest najnowsza aktualizacja. Większość spóźnionych paczek dociera w ciągu 48 godzin od podanej daty dostawy.
- Rozejrzyj się | Sprawdź miejsce z Twoich instrukcji dostawy i zdjęcie z doręczenia, jeśli kurier je zrobił. „Dostarczono” może też znaczyć, że paczka czeka w punkcie odbioru albo w automacie paczkowym.
- Zapytaj przewoźnika | Jeśli doręczał InPost, DPD albo inny przewoźnik, strona pomocy Amazona „Informacje kontaktowe przewoźników” podaje jego telefon i stronę.
- Napisz do sprzedawcy | Jeśli sprzedawca sprzedał i wysłał produkt, napisz do niego z zamówienia na stronie „Moje zamówienia”.
- Zwróć się do Amazona | Przy zamówieniach wysłanych przez Amazon napisz na czacie albo zadzwoń do Działu Obsługi Klienta. Gdy sprzedawca nie rozwiąże problemu ze swoim zamówieniem, Gwarancja od A do Z obejmuje spóźnioną dostawę i stan produktu.
:::

Więcej pomocy: [paczka dostarczona, ale jej nie ma](guide:delivered-not-received), [zaginiona paczka](guide:lost-parcel) i [dlaczego śledzenie się nie aktualizuje](guide:tracking-not-updating).

:::sources
- [Pomoc Amazon.pl: Śledzenie paczki](https://www.amazon.pl/gp/help/customer/display.html?nodeId=GENAFPTNLHV7ZACW) – „Moje zamówienia”, „Śledź przesyłkę”, identyfikator przesyłki, paczki z centrów logistycznych za granicą, numery od sprzedawców, nazwa sprzedawcy, „Dostarczono”, 48 godzin; „Zamówienia” w prawym górnym rogu; ta sama strona na amazon.com, .co.uk, .de, .fr, .it i .es podaje ich nazwy
- [Pomoc Amazon.pl: Informacje kontaktowe przewoźników](https://www.amazon.pl/gp/help/customer/display.html?nodeId=GBPEXEXYULHB5WCH) – nazwa przewoźnika i identyfikator przesyłki w „Śledź przesyłkę”; DHL, InPost, Poczta Polska, UPS i DPD z telefonem i stroną
- [Pomoc Amazon.pl: Zwrot prezentu](https://www.amazon.pl/gp/help/customer/display.html?nodeId=GMTDJ6XPUMN7NWNB) – 17-cyfrowy numer zamówienia
- [Amazon Help: Missing Tracking Information](https://www.amazon.com/gp/help/customer/display.html?nodeId=G6ZFEB9ZDU7QHMER) – brak numeru, gdy paczka jedzie do przewoźnika, szczegóły po pierwszym skanie
- [Amazon Help: About Deliveries Shipped with Amazon](https://www.amazon.com/gp/help/customer/display.html?nodeId=GEW3XT9JEMBLTKRV) – zamówienia doręczane przez Amazon Logistics
- [About Amazon: How to track your packages](https://www.aboutamazon.com/news/retail/track-amazon-package) – „Returns & Orders”, ikona osoby w aplikacji, „Track package”
- [About Amazon: 10 tips to protect your Amazon packages from porch pirates](https://www.aboutamazon.com/news/operations/amazon-porch-pirate-prevention-tips) – instrukcje dostawy, zdjęcia z doręczenia
- [About Amazon UK: How to change your delivery address on Amazon](https://www.aboutamazon.co.uk/news/retail/how-to-change-your-delivery-address-on-amazon) – kto sprzedaje i wysyła, pod przyciskiem zakupu
- [About Amazon UK: How to contact a seller on Amazon](https://www.aboutamazon.co.uk/news/small-businesses/how-to-contact-amazon-seller) – kontakt ze sprzedawcą z zamówienia
- [About Amazon: Contact Amazon Customer Service](https://www.aboutamazon.com/news/company-news/contact-amazon-customer-service) – czat lub telefon, pomoc przy spóźnionej dostawie
- [About Amazon: Pierwsze kroki w sprzedaży na Amazon](https://www.aboutamazon.pl/wiadomosci/sprzedaz-na-amazon/pierwsze-kroki-w-sprzedazy-na-amazon) – wysyłka przez sprzedawcę albo przez Amazon, odpowiedzialność sprzedawcy za dostawę
- [About Amazon: Amazon rozszerza w Europie Gwarancję od A do Z](https://www.aboutamazon.pl/wiadomosci/zakupy-na-amazon/amazon-rozszerza-w-europie-gwarancje-od-a-do-z) – najpierw sprzedawca, dostawa i stan produktu, zwrot pieniędzy od Amazona
- [About Amazon: 5 wskazówek, jak rozpoznawać oszustów podszywających się pod Amazon](https://www.aboutamazon.pl/wiadomosci/zakupy-na-amazon/5-wskazowek-jak-rozpoznawac-oszustow-podszywajacych-sie-pod-amazon-i-unikac-ich) – Gwarancja od A do Z dla każdego produktu: terminowa dostawa i stan produktu
- [About Amazon: Amazon unowocześnia dostawy w Europie](https://www.aboutamazon.pl/wiadomosci/miejsca-pracy-i-inwestycje/amazon-unowoczesnia-dostawy-w-europie) – stacje dostawcze, partnerzy na ostatnim etapie dostawy
- [About Amazon UK: Amazon CEO Andy Jassy's 2023 Letter to Shareholders](https://www.aboutamazon.co.uk/news/company-news/ceo-andy-jassys-2023-letter-to-shareholders) – Amazon Shipping, sieć dostaw Amazona dla paczek innych firm
- [Claude Help Center: Get started with Claude in Chrome](https://support.claude.com/en/articles/12012173-get-started-with-claude-in-chrome) – płatne plany Claude, tylko Chrome, instalacja rozszerzenia
- [Claude Help Center: Use Claude in Chrome safely](https://support.claude.com/en/articles/12902428-use-claude-in-chrome-safely) – za zakupy odpowiadasz Ty; uprawnienia dla każdej strony
- [ChatGPT: Chrome extension](https://learn.chatgpt.com/docs/chrome-extension) – konfiguracja z aplikacji ChatGPT na komputer; działa na stronach, na których korzystasz z konta po zalogowaniu
- [Peek: Połącz Peek z Claude, ChatGPT lub Gemini](https://peektracker.com/pl/assistants) – kroki Peek dla Claude i ChatGPT
- [Peek na GitHubie: narzędzia asystentów](https://github.com/plhery/peek-delivery-tracker/blob/main/src/server/assistants/tools.ts) – co Peek mówi asystentowi o Amazonie: zamówienia i strona śledzenia przesyłki, brak numeru śledzenia w e-mailach Amazona, tylko czytanie
- [Peek na GitHubie: teksty po polsku](https://github.com/plhery/peek-delivery-tracker/blob/main/shared/locales/pl.json) – komunikat o numerze zamówienia Amazon, „Otwórz moje zamówienia Amazon”, „Sprawdź ponownie”, kilka numerów w jednym tekście
- [Universal Parcel Scraper: dane przewoźników](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers) – kształty numerów Amazona i co sprawdza Peek
:::
