---
title: Śledzenie Chunghwa Post: numer TW, statusy i odprawa celna
description: Jak śledzić paczkę Chunghwa Post z Tajwanu lub na Tajwan: format numeru z końcówką TW, chińskie statusy, doręczenie przez Pocztę Polską i cło.
slug: chunghwa-post-tracking
published: 2026-11-21
updated: 2026-11-21
---

Chunghwa Post (中華郵政, Chunghwa Post Co., Ltd.) to narodowy operator pocztowy Tajwanu – państwowa spółka należąca w całości do Ministerstwa Transportu i Komunikacji, prowadząca oprócz usług pocztowych także rachunki oszczędnościowe oraz ubezpieczenia na życie. Przesyłkę sprawdzisz w oficjalnym serwisie [Track & Trace Service](https://postserv.post.gov.tw/pstmail/main_mail.html?targetTxn=EB500201) (po angielsku lub chińsku) albo wkleisz jej numer w pole powyżej. Numer nadania znajduje się na dowodzie nadania otrzymanym w placówce – przy zakupach z Tajwanu poproś o niego sprzedawcę lub sklep. Wyszukiwarka Chunghwa Post wymaga przepisania kodu z obrazka (Captcha) i obejmuje wyłącznie przesyłki nadane w ciągu ostatnich sześciu miesięcy.

## Numer śledzenia Chunghwa Post: jak wygląda

Przesyłka międzynarodowa nadana z Tajwanu ma 13 znaków: dwie litery, dziewięć cyfr (ostatnia to cyfra kontrolna) oraz `TW`. Instrukcja w serwisie Chunghwa Post wyjaśnia znaczenie pierwszej litery. Przykłady ze zmyślonymi cyframi:

| Przykład (fikcyjny) | Rodzaj usługi |
| --- | --- |
| `EE482910576TW` | `E`: EMS, międzynarodowa przesyłka kurierska (albo `EE`/`EZ` i `FT`/`FZ`, np. `FT482910576TW`, do Chin kontynentalnych). |
| `RR482910576TW` | `R`: międzynarodowy list polecony. |
| `LX482910576TW` | `L`: międzynarodowy ePacket (śledzony mały pakiet do 2 kg, doręczany pod adres bez podpisu odbiorcy). |
| `CP482910576TW` | `C`: międzynarodowa paczka pocztowa. |
| `48291057364918` | 14 cyfr (lub 20): przesyłka krajowa na terenie Tajwanu, sprawdzana w krajowej zakładce Chunghwa Post. |

Chunghwa Post informuje, że zwykłe małe pakiety zagraniczne z numerem zaczynającym się na literę `U` (np. `UH482910576TW`) nie są objęte śledzeniem online. Jeśli nie masz jeszcze numeru, zobacz poradnik [jak znaleźć numer śledzenia](guide:find-tracking-number).

## Statusy Chunghwa Post po chińsku – co oznaczają

Poniższe zdarzenia w tradycyjnym języku chińskim Chunghwa Post rozpoznaje i przypisuje do etapów w Peek:

| Chunghwa Post wyświetla | Co to oznacza dla przesyłki |
| --- | --- |
| `交寄郵件` | Nadana przy okienku pocztowym („Przyjęta przez przewoźnika”). |
| `運輸途中` | W drodze między jednostkami Chunghwa Post („W transporcie”). |
| `離開進口局` | Opuściła urząd pocztowy na trasie przewozu. |
| `到達出口互換局` | Dotarła do urzędu wymiany eksportowej wysyłającego pocztę za granicę. |
| `已出口－資料詳如右方` | Wyeksportowana: przesyłka opuściła Tajwan. |
| `郵袋接收` | Odebrano worek pocztowy, w którym podróżuje przesyłka. |
| `到達寄達國互換局` | Dotarła do urzędu wymiany w kraju przeznaczenia. |
| `海關查驗` | Przekazana do kontroli celnej („W urzędzie celnym”). |
| `離開寄達國互換局` | Opuściła urząd wymiany w kraju przeznaczenia. |
| `到達寄達國投遞局` | Dotarła do urzędu doręczeń w kraju przeznaczenia. |
| `安排投遞中` | W urzędzie doręczeń, przygotowywana do wydania listonoszowi lub kurierowi. |
| `郵件投遞中` | Wydana do doręczenia („W doręczeniu”). |
| `投遞不成功` | Nieudana próba doręczenia („Próba doręczenia”). Powód oraz ewentualny zwrot do nadawcy znajdują się w kolumnie uwag, której Peek nie pobiera – sprawdź ją w serwisie Chunghwa Post. |
| `投遞成功` | Doręczona („Dostarczona”). |

`安排投遞中` oraz `郵件投遞中` to własne etapy doręczenia Chunghwa Post dla przesyłek przychodzących na Tajwan. W przypadku paczek z Tajwanu do Polski końcowe statusy pochodzą z [Poczty Polskiej](carrier:poczta-polska).

Jeżeli wyszukiwarka zwraca komunikat `查無此號碼資料！！` (brak danych dla tego numeru), sprawdź wszystkie 13 znaków oraz czy od nadania nie minęło więcej niż sześć miesięcy.

## Paczki Chunghwa Post do Polski: kto doręcza i jakie opłaty celne obowiązują

Chunghwa Post wywozi przesyłkę z Tajwanu, a po przylocie do Polski przejmuje ją [Poczta Polska](carrier:poczta-polska) pod tym samym numerem zakończonym na `TW`. W tabeli zagranicznych operatorów na stronie Chunghwa Post Polska ma aktywne odnośniki do śledzenia Poczty Polskiej (`sledzenie.poczta-polska.pl`) dla wszystkich trzech kategorii: EMS, paczek międzynarodowych oraz listów poleconych (Polska nie znajduje się na liście trzynastu krajów – takich jak Francja, Włochy, Holandia, Wielka Brytania czy USA – które zakończyły internetowe śledzenie zagranicznych listów poleconych).

Po pojawieniu się statusu `已出口－資料詳如右方` wpisz ten sam numer `TW` w śledzeniu [Poczty Polskiej](carrier:poczta-polska). Zasady awizowania i terminy odbioru w placówce określa Poczta Polska; więcej w poradniku [nieudane doręczenie](guide:missed-delivery).

**Cło i VAT w Polsce.** Tajwan leży poza Unią Europejską, więc przesyłki z towarami przechodzą odprawę celną. Od każdej przesyłki naliczany jest polski VAT od importu (chyba że sprzedawca pobrał go już przy zakupie w procedurze IOSS). Od 1 lipca 2026 r. zamówienia internetowe spoza UE o wartości do 150 € objęte są zryczałtowanym cłem UE w wysokości 3 € za każdą pozycję towarową, a od 1 listopada 2026 r. dochodzi unijna opłata manipulacyjna w wysokości 2 € za pozycję towarową niezależnie od wartości zamówienia. Szczegóły znajdziesz w artykule [paczka zatrzymana przez urząd celny](guide:customs).

## Wysyłka paczki na Tajwan: doręczenie i przepisy celne

Przesyłkę nadaną z Polski na Tajwan doręcza na miejscu Chunghwa Post. Według Chunghwa Post każda importowana paczka przechodzi kontrolę tajwańskiego urzędu celnego, który decyduje o wymiarze podatku. Urząd Celny w Tajpej (Taipei Customs) podaje trzy progi dla paczek pocztowych:

- **Do 2 000 NT$** (wartość CIF: towar, ubezpieczenie i transport): bez cła i podatków.
- **Od 2 000 NT$ do 5 000 US$**: urząd celny zazwyczaj zleca urzędowi pocztowemu pobranie należności przy doręczeniu na podstawie kwitu *Duty Memo for Foreign Imports by Parcel Post*.
- **Powyżej 5 000 US$** (wartość FOB): adresat dokonuje odprawy i opłaca należności bezpośrednio w urzędzie celnym.

Taipei Customs przypomina też o towarach objętych ograniczeniami lub zakazem przywozu – należą do nich m.in. wyroby medyczne (np. preparaty z kwasem hialuronowym i urządzenia kosmetyczne), nasiona roślin, produkty zawierające mięso, podgrzewane wyroby tytoniowe, e-papierosy oraz podróbki.

## Brak aktualizacji śledzenia Chunghwa Post, opóźnienie lub zaginięcie

**Cisza po statusie `已出口－資料詳如右方`.** Przesyłka opuściła już Tajwan i jest w transporcie lotniczym albo czeka na przyjęcie i kontrolę celną w Polsce. Sprawdź ten sam numer w [Poczcie Polskiej](carrier:poczta-polska) i przeczytaj [dlaczego śledzenie się nie aktualizuje](guide:tracking-not-updating). (Przy przesyłkach EMS do Niemiec Chunghwa Post celowo ukrywa dalsze statusy z powodu opóźnień wymiany danych z Deutsche Post i odsyła na stronę [DHL](carrier:dhl)).

**Reklamacja zagubionej lub opóźnionej przesyłki.** Pisemną reklamację może złożyć nadawca w tajwańskim urzędzie pocztowym nadania, okazując oryginał dowodu nadania: w ciągu **4 miesięcy** od dnia następującego po nadaniu dla EMS oraz w ciągu **6 miesięcy** dla paczek i przesyłek poleconych. Po upływie tego terminu Chunghwa Post może odmówić poszukiwań i nie wypłaca odszkodowania. Poproś sprzedawcę o złożenie wniosku w terminie i zobacz [zagubiona paczka](guide:lost-parcel).

**Status doręczono, ale paczki brak.** Ostatni odcinek w Polsce realizuje [Poczta Polska](carrier:poczta-polska) – zacznij od sprawdzenia jej śledzenia i potwierdzenia odbioru, a także poradnika [dostarczona, ale nieodebrana](guide:delivered-not-received).

## Kontakt z obsługą klienta Chunghwa Post

Godziny pracy podane są według czasu tajwańskiego (`UTC+8`, bez zmiany na czas letni):

- **Całodobowa infolinia**: `0800-700-365` (bezpłatna z telefonów stacjonarnych na Tajwanie; wybierz `3`, aby połączyć się z obsługą po angielsku). Z telefonu komórkowego dzwoń pod płatny numer `(04) 2354-2030`, czyli z Polski `+886 4 2354 2030` (z pominięciem początkowego `0` w numerze kierunkowym).
- **Przesyłki przychodzące na Tajwan**: `(03) 3180-700`, wew. `2431` (EMS) oraz `2432` (paczki) od poniedziałku do piątku z wyjątkiem świąt w godz. 8:00–12:00 i 13:30–16:30; wew. `2430` (listy polecone lotnicze) w godz. 8:00–12:00 i 13:00–16:00.
- **Paczki lotnicze wychodzące z Tajwanu**: `(03) 383-3776`, wew. `271` lub `272`, od poniedziałku do piątku z wyjątkiem świąt w godz. 8:00–16:30.
- **Online**: formularz *Opinion Box* na stronie [Contact Us](https://www.post.gov.tw/post/internet/U_english2/index.jsp?ID=35090601) z odpowiedzią w ciągu 7 dni (podaj numer przesyłki oraz imiona, nazwiska i adresy nadawcy i adresata, a następnie kliknij link potwierdzający w e-mailu).

## Śledzenie przesyłek Chunghwa Post w Peek

[Peek](/) od razu rozpoznaje jako Chunghwa Post 13-znakowy numer złożony z dwóch liter, dziewięciu cyfr i `TW`, gdy pierwszą literą jest `C`, `E`, `L` lub `R` (albo prefiksem `FT` bądź `FZ`) i zgadza się cyfra kontrolna; przy błędnej cyfrze sprawdź pokwitowanie. Działają też linki `CSController?MAILNO=`. Numer `UH…TW` jest po samym kształcie przypisywany do poczty międzynarodowej (Chunghwa Post możesz wskazać ręcznie przyciskiem „Zmień” lub „Zmień przewoźnika”), natomiast pozostałe kody `U…TW` oraz 14- i 20-cyfrowe numery krajowe są odrzucane przez adapter Chunghwa Post („nie wydaje takich numerów”).

Peek odczytuje międzynarodową wyszukiwarkę Chunghwa Post i pokazuje każdy skan w oryginalnym zapisie chińskim wraz z urzędem (gdy to możliwe, chińska nazwa urzędu zostaje przypisana do miasta). Zdarzenia z tajwańskich jednostek są odczytywane w strefie `Asia/Taipei` i przeliczane na Twój czas lokalny („Godziny w twojej strefie czasowej: …”), a skany z zagranicy są odczytywane w czasie lokalnym wskazanego kraju i również przeliczane. Chunghwa Post nie podaje przewidywanej daty doręczenia ani wagi, a Peek pomija boczną kolumnę uwag.

Dla numeru zakończonego na `TW` Peek odpytuje również operatora pocztowego kraju, z którego sprawdzasz przesyłkę – z Polski [Pocztę Polską](carrier:poczta-polska) – i przełącza na niego śledzenie po pojawieniu się aktualnych skanów, zachowując wcześniejszą historię z Tajwanu. W drugą stronę, gdy poczta nadania wskazuje Tajwan jako kraj docelowy, Peek pyta Chunghwa Post o ten sam numer pocztowy, o ile nie zaczyna się on na literę `U`.

Gdy masz otwartą kartę przesyłki lub w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza bezpośredni serwis Chunghwa Post nawet co 10 minut, a po przejściu przesyłki w status „W doręczeniu” – nawet co 2 minuty (tego dnia od godz. 6:00 czasu lokalnego, dopóki odpowiada bezpośrednio serwis Chunghwa Post); w pozostałym czasie oraz po dwóch dniach bez nowych zdarzeń (12 godzinach, gdy widnieje „Zarejestrowana” lub „Przyjęta przez przewoźnika”) sprawdza co godzinę. Przez zewnętrzne serwisy śledzenia w dniu statusu „W doręczeniu” na tych samych warunkach odpytuje nawet co 15 minut, poza tym co godzinę albo co 3 godziny po dobie bez zmian. Po przejęciu przesyłki przez pocztę docelową Peek stosuje częstotliwość właściwą dla tego operatora. Powiadomienia możesz ustawić na „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek nie składa reklamacji w Chunghwa Post ani w Poczcie Polskiej w Twoim imieniu.

## Najczęstsze pytania o śledzenie Chunghwa Post

### Czy Chunghwa Post to to samo co Taiwan Post?

Tak. Chunghwa Post (中華郵政) jest oficjalną pocztą Tajwanu i Peek wyszukuje ją pod obiema nazwami. Jej międzynarodowe numery nadania kończą się na `TW`.

### Gdzie śledzić paczkę Chunghwa Post w Polsce?

W wyszukiwarce Chunghwa Post aż do statusu `已出口－資料詳如右方`, a następnie pod tym samym numerem `TW` w [Poczcie Polskiej](carrier:poczta-polska), do której Chunghwa Post linkuje zarówno dla EMS, jak i paczek oraz listów poleconych do Polski. Gdy sprawdzasz przesyłkę z Polski, Peek sam odpytuje Pocztę Polską.

### Co oznacza status `海關查驗`?

Przesyłka przechodzi kontrolę celną („W urzędzie celnym”). Zasady naliczania VAT, cła 3 € (dla zamówień do 150 €) oraz opłaty manipulacyjnej 2 € w Polsce opisuje poradnik [paczka zatrzymana przez urząd celny](guide:customs).

### Jak długo Chunghwa Post przechowuje historię śledzenia?

Serwis internetowy udostępnia dane tylko z ostatnich sześciu miesięcy. Pisemną reklamację z oryginałem dowodu nadania nadawca może złożyć na Tajwanie w ciągu 4 miesięcy dla EMS oraz 6 miesięcy dla paczek i listów poleconych, licząc od dnia następnego po nadaniu.

:::sources
- [Chunghwa Post: Company Profile](https://www.post.gov.tw/post/internet/U_english2/index.jsp?ID=1637282383451) – państwowy operator pocztowy Tajwanu, usługi pocztowe, oszczędnościowe i ubezpieczeniowe
- [Chunghwa Post: Tracking Service](https://www.post.gov.tw/post/internet/U_english2/index.jsp?ID=351402) – dostęp do śledzenia EMS, listów i paczek
- [Chunghwa Post: Track & Trace Service](https://postserv.post.gov.tw/pstmail/main_mail.html?targetTxn=EB500201) – Captcha, okres 6 miesięcy, prefiksy E/R/L/C/U, terminy reklamacji, odnośniki do poczt zagranicznych (Polska: Poczta Polska) i numery krajowe 14/20 cyfr
- [Chunghwa Post: 國際e小包 (ePacket)](https://www.post.gov.tw/post/internet/Postal/index.jsp?ID=1499060698896) – limit 2 kg, doręczenie bez podpisu i brak pisemnych reklamacji
- [Chunghwa Post: 國際郵件查詢電話](https://www.post.gov.tw/post/internet/Customer_service/index.jsp?ID=160101) – numery telefonów, numery wewnętrzne i godziny pracy działów międzynarodowych
- [Chunghwa Post: English home page](https://www.post.gov.tw/post/internet/U_english2/) – infolinia 0800-700-365, numer dla komórek i zapis kierunkowy +886
- [Chunghwa Post: 客服專線語音操作說明](https://www.post.gov.tw/post/internet/Customer_service/index.jsp?ID=160103) – całodobowe menu głosowe, klawisz 3 dla języka angielskiego
- [Chunghwa Post: Contact Us](https://www.post.gov.tw/post/internet/U_english2/index.jsp?ID=35090601) – formularz Opinion Box, odpowiedź w 7 dni i wymagane dane
- [Chunghwa Post: 國際包裹 FAQ](https://www.post.gov.tw/post/internet/Customer_service/index.jsp?ID=16020307&defaultAllOpen=1&sn=24FA12A9-11A6-4365-A8F5-C154C379AF02) – obowiązkowa kontrola celna paczek importowanych na Tajwan
- [Chunghwa Post: 郵件查詢的期限 FAQ](https://www.post.gov.tw/post/internet/Customer_service/index.jsp?ID=16020315&defaultAllOpen=1&sn=44431DCF-5197-43D5-95B6-5EFE13F5D67B) – odmowa poszukiwań i odszkodowania po terminie
- [Taipei Customs: International Parcel](https://web.customs.gov.tw/etaipei/singlehtml/1342) – progi 2 000 NT$ i 5 000 US$, kwit Duty Memo
- [Taipei Customs: Cross-border online purchases](https://web.customs.gov.tw/etaipei/singlehtml/1373?cntId=1a74ff69fdd0483085a6a8c9eda54b07) – towary zakazane i objęte ograniczeniami w imporcie
- [Your Europe: Wysyłka i dostawa](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_pl.htm) – cło 3 € za pozycję towarową dla zamówień spoza UE do 150 €
- [Komisja Europejska: Customs Guidance on EUR 3 customs duty](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – obowiązywanie cła 3 € od 1 lipca 2026 r.
- [Komisja Europejska: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – unijna opłata manipulacyjna 2 € za pozycję towarową od 1 listopada 2026 r.
- [Universal Parcel Scraper: Chunghwa Post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/chunghwa-post) – rozpoznawanie numerów TW, chińskie statusy i przekazanie do poczty docelowej
:::
