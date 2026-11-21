---
title: Śledzenie YTO Express: numer YT, statusy i dostawa
description: Śledź paczkę YTO Express (圆通): jak wygląda numer YT, co oznaczają chińskie skany, doręczenie w Chinach, czas dostawy do Polski i kontakt.
slug: sledzenie-yto-express
published: 2026-11-18
updated: 2026-11-20
---

YTO Express (圆通速递, Yuantong) to chińska firma kurierska założona w Szanghaju w 2000 roku, dysponująca ponad 5000 oddziałów w całych Chinach. Numer YTO dotyczy najczęściej przesyłki krajowej na terenie Chin – na przykład zamówienia z platform Taobao, Pinduoduo lub Douyin, które YTO obsługuje, jadącego do magazynu spedytora albo pod lokalny adres. Możesz sprawdzić go w [wyszukiwarce YTO](https://www.yto.net.cn/ytoExpress/waybill/search), dostępnej wyłącznie po chińsku, albo w polu powyżej. Przesyłki zagraniczne YTO obsługuje natomiast YTO International z osobną wyszukiwarką na [ytoglobal.com](https://www.ytoglobal.com/service).

## Numer śledzenia YTO Express: jak wygląda

YTO nazywa go numerem listu przewozowego (`运单号`). Z przykładowymi cyframi:

| Numer | Co oznacza |
| --- | --- |
| `YT2468135790246` | `YT` i 13 cyfr: list przewozowy YTO Express. |
| `D24681357902` | `D` i 11 cyfr: format używany przez YTO, ale identyczny układ mogą mieć numery Colis Privé. |
| `G24681357902` | `G` i 11 cyfr: kolejny format YTO, który również pokrywa się z Colis Privé. |
| `YT2612468135790246` | `YT` i 16 cyfr: to nie YTO, lecz [YunExpress](carrier:yunexpress). |

Małe litery i spacje nie mają znaczenia w [Peek](/). Własna wyszukiwarka YTO przyjmuje do 10 numerów naraz, rozdzielonych przecinkami, ukośnikami lub spacjami.

**Gdzie go znaleźć.** Sprzedawca podaje numer w szczegółach wysyłki zamówienia; widnieje on także na etykiecie paczki. Jeśli nie możesz go odszukać, sprawdź poradnik [jak znaleźć numer przesyłki](guide:find-tracking-number).

**Wyszukiwanie na stronie YTO.** Nawet po przejściu z angielskiej wersji serwisu YTO strona śledzenia pozostaje po chińsku: wpisz numer w pole tekstowe i kliknij `查询` (szukaj). Zgodnie z polityką prywatności YTO list przewozowy można sprawdzać przez trzy do sześciu miesięcy w zależności od kanału nadania; po tym okresie YTO usuwa go z wyszukiwarki.

## Statusy śledzenia YTO Express i ich znaczenie

Każdy skan w danych YTO zawiera krótki opis po chińsku, godzinę według czasu chińskiego (`UTC+8`) oraz nazwę oddziału lub stacji, w której go zapisano. Tak poszczególne wpisy pokazuje Peek:

| Skan w YTO | W Peek | Co się wydarzyło |
| --- | --- | --- |
| `揽收扫描` | `Picked up` | Odebrana od nadawcy. |
| `业务员收件` | `Picked up` | Przyjęta bezpośrednio od nadawcy przez kuriera YTO. |
| `装件入车扫描` | `Loaded for transport` | Załadowana do pojazdu na dalszą trasę. |
| `下车扫描` | `Unloaded at a sorting centre` | Rozładowana w sortowni lub oddziale. |
| `快件城市信息` | „W transporcie” | Odnotowanie miasta mijanego po drodze. |
| `冬运会安检` | `Security check` | Kontrola bezpieczeństwa w sieci YTO związana z zimowym wydarzeniem sportowym; następnie paczka jedzie dalej. |
| `派件扫描` | „W doręczeniu” | Przekazana kurierowi do doręczenia. |
| `入柜\|入库` | `In a parcel locker or pickup station` | Umieszczona w automacie paczkowym lub przyjęta w punkcie odbioru. |
| `小件员取出` | `Taken out of the locker by the courier` | Kurier ponownie wyjął paczkę z automatu lub punktu. |
| `PDA正常签收扫描` | „Dostarczona” | Wydana za pokwitowaniem (`签收`), zapisanym na terminalu kuriera. |
| `退回件扫描` | `Return to the sender started` | Rozpoczął się zwrot do nadawcy. |

Trzy wiersze ujęte w cudzysłów Peek wyświetla po polsku; pozostałe pozycje z tabeli pozostają po angielsku, a skany spoza tej listy widnieją w oryginalnym chińskim brzmieniu YTO. Ogólny status przesyłki jest zawsze po polsku, np. „Przyjęta przez przewoźnika” albo „Do odbioru”. Po `入柜|入库` status zmienia się na „Do odbioru”, a gdy kurier wyjmie paczkę z powrotem (`小件员取出`), wraca na „W transporcie”.

Jeżeli adresem dostawy jest magazyn spedytora międzynarodowego w Chinach, `PDA正常签收扫描` oznacza, że ten magazyn pokwitował odbiór paczki. Od tego momentu dalszą drogę śledzisz już pod własnym numerem spedytora.

## Doręczenie YTO w Chinach: kurier, automaty i stacje odbioru

W **Chinach** kurier YTO dostarcza paczkę pod wskazany adres (`派件扫描`) albo trafia ona do odbioru w automacie paczkowym lub stacji (`入柜|入库`). Numer `95161` YTO opisuje jako „Courier Outbound Hotline” – linię, z której dzwonią jego kurierzy.

Przepisy dotyczące rynku usług kurierskich obowiązujące w **Chinach** od 1 marca 2024 r. stanowią, że:

- YTO musi poinformować cię o prawie sprawdzenia przesyłki przy odbiorze pod kątem zgodności z listem przewozowym; jeśli opakowanie jest wyraźnie uszkodzone lub zawartość delikatna, możesz zajrzeć do środka albo odmówić przyjęcia.
- Firma kurierska nie może potwierdzić odbioru w twoim imieniu ani zostawić paczki w inteligentnym automacie paczkowym lub stacji obsługi bez twojej zgody.

Aby zlecić zwrot paczki lub zmianę adresu jeszcze przed wydaniem (`退回/更址`), YTO odsyła m.in. do swojej aplikacji oraz miniprogramu. Według polityki prywatności w sprawie korekty adresu lub numeru telefonu należy skontaktować się z obsługą klienta.

## YTO International: paczki poza Chiny

YTO International (圆通国际) sięga historią 2006 roku i podaje ponad 50 biur w 18 krajach i regionach. Jego własna wyszukiwarka pod adresem [ytoglobal.com/service](https://www.ytoglobal.com/service) działa po angielsku („Express Freight Tracking”) oraz po chińsku (`国际快递查询`) i przyjmuje do 10 numerów oddzielonych przecinkami lub średnikami.

Strony YTO nie podają, który przewoźnik doręcza przesyłkę na ostatnim odcinku w twoim kraju, wspominając jedynie o współpracy z czołowymi globalnymi operatorami logistycznymi („top-notch global logistics service providers”). W usłudze dla klientów indywidualnych International Standard Express YTO wskazuje ze swojego magazynu międzynarodowego wyłącznie orientacyjne czasy przewozu („for reference only”): 7–12 dni roboczych do Polski, Niemiec, Francji, Włoch, Hiszpanii, Portugalii, Szwajcarii, Wielkiej Brytanii i Kanady (12–17 do Bułgarii i Rumunii), 9–12 do USA oraz 8–13 do Australii.

W razie nieobecności odbiorcy sekcja FAQ YTO International zaleca skorzystać z powiadomienia o ponownym doręczeniu („Redelivery Notification”), aby bezpłatnie umówić drugą próbę; jeśli również się nie powiedzie, YTO prosi nadawcę o dalsze dyspozycje. W celu wcześniejszego ustalenia pory dostawy FAQ podaje numer `400 60 95554`. W transporcie lotniczym przesyłki przechodzą kontrolę celną i bezpieczeństwa na lotnisku, a urząd celny może zająć lub zniszczyć paczkę zawierającą przedmioty zabronione.

**Cło i ostrzeżenia przed oszustwami (UE i Polska).** Należności przywozowe zależą od kraju przeznaczenia: zobacz [paczka zatrzymana przez urząd celny](guide:customs). W **Unii Europejskiej** od 1 lipca 2026 r. towary kupowane na odległość spoza obszaru celnego UE w przesyłkach o wartości rzeczywistej do 150 € podlegają cłu w wysokości 3 € za każdą kategorię towaru, a od 1 listopada 2026 r. doliczana jest unijna opłata manipulacyjna w wysokości 2 € za kategorię towaru niezależnie od wartości przesyłki (jedna kategoria obejmuje towary w danej przesyłce mające tę samą klasyfikację taryfową, ten sam opis oraz – o ile podlega zgłoszeniu – to samo pochodzenie, bez względu na liczbę sztuk). W **Polsce** w przypadku przesyłek spoza UE odprawianych przez [Pocztę Polską](carrier:poczta-polska) opłaty – cło, podatek VAT (jeżeli nie został opłacony w momencie zakupu) oraz opłata za dokonanie zgłoszenia celnego przez Pocztę Polską – są pobierane przy doręczeniu przesyłki lub przed jej odbiorem – w zależności od zastosowanej procedury celnej; jednocześnie Poczta Polska ostrzega w komunikatach bezpieczeństwa, że jej usługa kurierska Pocztex nie wysyła w wiadomościach SMS linków prowadzących do płatności ani nie prosi przez SMS o dane kart płatniczych. Więcej w poradniku [fałszywe SMS-y o paczkach](guide:delivery-scam-texts).

## Paczka YTO utknęła, zaginęła albo wraca do nadawcy

**Brak skanów.** Świeżo wystawiony list przewozowy pozostaje pusty do pierwszego fizycznego skanu w YTO. Jeśli nic się nie pojawia, potwierdź numer u sprzedawcy; przy starszej przesyłce wpis mógł już zostać usunięty po trzech–sześciu miesiącach przechowywania.

**Brak nowych aktualizacji.** W **Chinach** krajowa umowa o świadczenie usług kurierskich YTO z nadawcami uznaje paczkę za utraconą, gdy przez 4 dni od pierwszego wpisu nie pojawia się żaden nowy status albo przesyłka nie zostanie pokwitowana w ciągu 7 dni po planowanym terminie. Skontaktuj się ze sprzedawcą, który nadał paczkę, aby zlecił poszukiwanie w YTO. Zobacz też [dlaczego śledzenie się nie aktualizuje](guide:tracking-not-updating) oraz [śledzenie paczek z Chin](guide:tracking-from-china).

**Zagubienie lub uszkodzenie.** Przy zakupie internetowym za wysyłkę płacił sprzedawca – w pierwszej kolejności zgłoś się do niego. Według tej samej umowy w **Chinach** nieubezpieczona paczka (`保价`) jest rekompensowana do kwoty 300 juanów (dokumenty do 100 juanów), natomiast przesyłka z zadeklarowaną wartością – do wysokości rzeczywistej straty w granicach sumy ubezpieczenia. Reklamacje zgłasza się telefonicznie w oddziale nadawczym YTO lub na infolinii YTO; po zalogowaniu w serwisie YTO dostępne są również podstrony skarg (`投诉反馈`) i odszkodowań z tytułu zadeklarowanej wartości (`保价理赔`). Gdy YTO nie odpowiada lub rozstrzygnięcie nie rozwiązuje sprawy, chińskie przepisy pozwalają złożyć skargę do urzędu regulacji poczty State Post Bureau (`消费者申诉` na jego stronie). Sprawdź także [zagubiona paczka](guide:lost-parcel).

**Zwrot do nadawcy.** Po skanie `退回件扫描` przesyłka jest w drodze powrotnej, a późniejsze skany doręczenia dotyczą wydania jej z powrotem nadawcy. Napisz do sprzedawcy; zobacz [zwrot do nadawcy](guide:returned-to-sender).

**Status dostarczona, ale paczki nie ma.** W **Chinach** umowa YTO dopuszcza pokwitowanie przez domowników albo wskazaną przez ciebie osobę, a także pozostawienie przesyłki w automacie lub stacji odbioru w pobliżu adresu – sprawdź te miejsca oraz recepcję budynku. Zobacz [dostarczona, ale nieodebrana](guide:delivered-not-received).

## Numer telefonu i obsługa klienta YTO Express

Na swoich stronach YTO podaje następujące numery:

- **`95554`**: „Service Hotline” (`服务热线`) dla YTO Express na terenie Chin.
- **`400 60 95554`**: „Service Hotline” YTO International dla przesyłek międzynarodowych.
- **`95161`**: „Courier Outbound Hotline”, numer, z którego kurierzy YTO dzwonią do odbiorców.
- **`021-69777888`**: wymieniony obok bez osobnego opisu.

YTO nie podaje godzin pracy ani obsługiwanych języków dla tych numerów. Na chińskiej stronie głównej widnieje przycisk czatu z obsługą online (`在线客服`), a serwis YTO International udostępnia formularz kontaktowy. W sprawach samego zamówienia lub zwrotu pieniędzy kontaktuj się ze sprzedawcą.

## Śledzenie paczki YTO Express w Peek

[Peek](/) od razu rozpoznaje YTO Express po literach `YT` i 13 cyfrach. Kod złożony z `D` lub `G` oraz 11 cyfr pasuje natomiast również do Colis Privé, więc sam układ znaków nie przesądza o przewoźniku. Mimo to wklej numer: Peek obsługuje ponad 4500 przewoźników i odpytuje usługi śledzenia („Sprawdzanie usług śledzenia…”). Jeśli YTO Express nie ustawi się sam, kliknij „Zmień” i wskaż go w sekcji „Pasują do numeru” albo użyj opcji „Zmień przewoźnika” na karcie dodanej już przesyłki. Moduł YTO w Peek odczytuje także listy przewozowe YTO w innych formatach po wybraniu YTO Express z listy „Wszyscy przewoźnicy”. Link z domeny `yto.net.cn` zawierający `mailNo=` lub `waybillNo=` wraz z numerem również od razu przypisuje paczkę do YTO.

Peek odczytuje krajowe chińskie śledzenie YTO, a nie system YTO International: pokazuje status, skany zwrócone przez YTO wraz z miastem oddziału lub stacji (albo ich chińską nazwą, gdy nie da się jej przypisać do miasta) oraz godzinę doręczenia. Czasy są odczytywane według czasu chińskiego (`UTC+8`: `20:00` w Pekinie to w Polsce `13:00` CET zimą i `14:00` CEST w czasie letnim) i przeliczane na strefę czasową twojego urządzenia; na peektracker.com i w aplikacjach na iPhone’a i Androida historia kończy się dopiskiem „Godziny w twojej strefie czasowej:” z jej nazwą. Powtórzone skany wyświetlają się tylko raz. Tuż po `退回件扫描` status zmienia się na „Zgłoszono problem”, a etapy drogi powrotnej Peek pokazuje po angielsku jako `Out for delivery back to the sender` i `In a parcel locker or station on its way back`, natomiast podpis nadawcy jako `Returned to the sender` ze statusem „Zwrócona nadawcy”.

YTO nie udostępnia przewidywanej daty dostawy. Peek pomija imiona i numery telefonów kurierów, dane odbiorcy oraz adresy odbioru osobistego – zobaczysz je wyłącznie w wyszukiwarce YTO. Ponieważ YTO odpowiada na każdy poprawny składniowo numer, brak skanów nie dowodzi literówki: Peek wyświetla „Oczekiwanie na przewoźnika YTO Express” i sprawdza dalej. Jeżeli po dwóch dniach przesyłka zapisana pod YTO nadal nie jest znana YTO i żadne inne źródło nie odpowiedziało, Peek pokazuje komunikat „Po dwóch dniach przewoźnik nadal nie zna tego numeru. Sprawdź go, wybierz innego przewoźnika lub usuń przesyłkę.” i przechodzi na sprawdzanie raz dziennie, a po tygodniu od dodania – raz w tygodniu.

Peek sprawdza aktualizacje nawet co 10 minut, gdy masz przesyłkę otwartą na ekranie lub w dniu doręczenia przy włączonych powiadomieniach, oraz nawet co 2 minuty od 6:00 czasu chińskiego w dniu skanu wydania do doręczenia (`派件扫描`), dopóki odpowiada własna wyszukiwarka YTO; przez zewnętrzne serwisy śledzenia sprawdza najwyżej co godzinę, co 3 godziny po dobie bez nowego skanu i nawet co 15 minut w dniu doręczenia, gdy masz paczkę otwartą lub włączone powiadomienia. W pozostałym czasie oraz po dwóch dniach bez nowych skanów (12 godzin, gdy widnieje „Przyjęta przez przewoźnika”) sprawdza co godzinę. Możesz wybrać powiadomienia: „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek nie skontaktuje się w twoim imieniu z YTO ani ze sprzedawcą.

## Najczęstsze pytania o śledzenie YTO Express

### Czy YTO Express to wiarygodny przewoźnik?

Tak. YTO Express powstało w Szanghaju 28 maja 2000 r. Grupa obejmuje dwie spółki giełdowe – YTO Express (kod 600233) oraz YTO International (06123.HK) – i na koniec 2024 r. deklarowała 550 000 pracowników.

### Co oznacza 签收 w śledzeniu YTO?

Odebrano za podpisem: kurier doręczył przesyłkę i zapisał potwierdzenie na swoim terminalu (`PDA正常签收扫描`), co Peek pokazuje jako „Dostarczona”. Paczkę mogłeś odebrać ty, osoba upoważniona pod twoim adresem albo magazyn spedytora w Chinach. Jeśli nikt jej nie ma, zajrzyj do poradnika [dostarczona, ale nieodebrana](guide:delivered-not-received).

### Dlaczego mój numer YTO nie pokazuje żadnej historii?

Ponieważ YTO jeszcze nie zeskanowało paczki, w numerze jest błąd albo minęło ponad trzy–sześć miesięcy, przez które YTO udostępnia historię zgodnie ze swoją polityką prywatności. Potwierdź numer u sprzedawcy albo wklej go do Peek, który będzie dalej odpytywać przewoźnika.

### Czy YTO Express dostarcza paczki poza Chiny?

Tak, za pośrednictwem YTO International i wyszukiwarki na ytoglobal.com. Strony YTO nie wymieniają lokalnej firmy doręczającej w kraju docelowym i podają czasy wyłącznie orientacyjnie: 7–12 dni roboczych do Polski oraz większości krajów Unii Europejskiej.

### Jaki jest numer telefonu do YTO Express?

`95554` na terenie Chin oraz `400 60 95554` dla YTO International, bez opublikowanych godzin dyżuru. Połączenia z numeru `95161` pochodzą od kurierów YTO.

:::sources
- [YTO Express: About YTO (po angielsku)](https://www.yto.net.cn/en/aboutYto/about) – założenie 28 maja 2000 r. w Szanghaju, spółki giełdowe, ponad 5000 oddziałów i 550 000 pracowników
- [YTO Express: 我要查件 (wyszukiwanie listów przewozowych, po chińsku)](https://www.yto.net.cn/ytoExpress/waybill/search) – wyszukiwarka tylko po chińsku, do 10 numerów, strony skarg i odszkodowań
- [YTO Express: Domestic Express Services (po angielsku)](https://www.yto.net.cn/en/domesticService) – obsługiwane platformy, zwrot lub zmiana adresu przed doręczeniem, infolinie 95554 i 95161
- [YTO Express: Strona główna (po chińsku)](https://www.yto.net.cn/) – obsługa klienta online `在线客服`
- [YTO Express: 国内快递 (usługi krajowe, po chińsku)](https://www.yto.net.cn/domesticService) – `退回/更址`, `服务热线`, `快递员外呼热线`
- [YTO Express: 国内快递服务协议 (krajowa umowa o usługi kurierskie, po chińsku)](https://mec.yto.net.cn/h5/#/subPages/agreement/index?code=DESA) – kryteria utraty w Chinach, odszkodowania z zadeklarowaną wartością i bez niej, reklamacje oraz osoby uprawnione do podpisu
- [YTO Express: 圆通速递隐私政策 (polityka prywatności, po chińsku)](https://mec.yto.net.cn/h5/#/subPages/agreement/index?code=PCPrivacyAgreement) – dostępność wyszukiwania przez trzy–sześć miesięcy i zmiana adresu przez obsługę klienta
- [YTO Express: 寄快递 (nadaj paczkę, po chińsku)](https://www.yto.net.cn/ytoExpress/subCreatePages/create) – rekompensata do 300 juanów bez zadeklarowanej wartości
- [YTO Express: International Business Service (po angielsku)](https://www.yto.net.cn/en/global/globalProduct) – infolinia YTO International 4006095554 oraz 021-69777888
- [YTO International: Company Introduction (po angielsku)](https://www.ytoglobal.com/about-us) – działalność od 2006 r., ponad 50 biur w 18 krajach i regionach oraz globalni partnerzy
- [YTO International: Express Freight Tracking](https://www.ytoglobal.com/service) – wyszukiwarka po angielsku i chińsku do 10 numerów
- [YTO International: International Standard Express (po angielsku)](https://www.ytoglobal.com/product/express-intl) – usługa dla osób prywatnych i orientacyjne czasy dostawy do Polski oraz innych krajów
- [YTO International: FAQ (po angielsku)](https://www.ytoglobal.com/rich/question) – numer 400 60 95554, bezpłatna druga próba doręczenia, umówienie terminu i kontrola celna
- [YTO International: Contact Us (po angielsku)](https://www.ytoglobal.com/about-us/contact) – formularz kontaktowy
- [Ministerstwo Transportu Chin: 快递市场管理办法 (przepisy o rynku usług kurierskich, po chińsku)](https://xxgk.mot.gov.cn/gz/202401/t20240104_3980684.html) – w mocy w Chinach od 1 marca 2024 r., sprawdzenie przy odbiorze, zgoda na automat lub stację i skarga do organu pocztowego
- [State Post Bureau Chin: 国家邮政局主要职责 (główne zadania, po chińsku)](https://www.spb.gov.cn/gjyzj/c100001/c100006/201306/2722edab189242549b320d06e0dbd382.shtml) – urząd regulacji poczty i rynku kurierskiego
- [State Post Bureau Chin: strona główna (po chińsku)](https://www.spb.gov.cn/) – dział skarg konsumenckich (`消费者申诉`)
- [Komisja Europejska: EU Customs Reform (po angielsku)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – cło 3 € za kategorię towaru w przesyłkach do 150 € od 1 lipca 2026 r. oraz unijna opłata manipulacyjna 2 € od 1 listopada 2026 r.
- [Komisja Europejska: Guidance on the EUR 3 customs duty and the Union handling fee (PDF, po angielsku)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – opłata 2 € za kategorię towaru niezależnie od wartości oraz definicja kategorii towaru
- [Poczta Polska: Zmiany w opłatach dla przesyłek spoza Unii Europejskiej od 1 lipca 2026 r.](https://www.poczta-polska.pl/news/zmiany-w-oplatach-dla-przesylek-spoza-unii-europejskiej-od-1-lipca-2026-r/) – pobieranie należności celno-podatkowych w Polsce przy doręczeniu lub przed odbiorem, zależnie od procedury celnej
- [Poczta Polska: Uwaga na fałszywe wiadomości Pocztex](https://www.poczta-polska.pl/news/uwaga-na-falszywe-wiadomosci-pocztex-grozna-kampania-phishingowa/) – ostrzeżenie w Polsce, że Pocztex nie wysyła w SMS-ach linków do płatności ani nie prosi o dane kart
- [Komisja Europejska: Summertime (po angielsku)](https://transport.ec.europa.eu/transport-themes/summertime_en) – czas letni w UE od ostatniej niedzieli marca do ostatniej niedzieli października
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – rozpoznawane formaty, linki `yto.net.cn`, tłumaczenie skanów YTO, czas chiński, przebieg zwrotu oraz dane wyświetlane i pomijane w Peek
:::
