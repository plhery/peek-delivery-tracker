---
title: Ecoscooting śledzenie przesyłek: statusy, punkty i kontakt
description: Śledź paczkę Ecoscooting w Hiszpanii lub Portugalii: numery CNESP, CNPRT i 18 cyfr, znaczenie statusów, punkty odbioru, próby doręczenia oraz kontakt.
slug: ecoscooting-sledzenie
published: 2026-11-17
updated: 2026-11-17
---

Ecoscooting to hiszpańska firma kurierska obsługująca lokalne doręczenia ostatniej mili w **Hiszpanii i Portugalii** dla zagranicznych platform zakupowych (takich jak [AliExpress](guide:aliexpress-orders) czy [SHEIN](guide:shein-orders)) oraz lokalnych sprzedawców. W Polsce nie doręcza przesyłek — trafisz na nią, gdy zamawiasz paczkę na adres w Hiszpanii lub Portugalii. Ecoscooting odpowiada wyłącznie za końcowy odcinek dostawy pod drzwi lub do punktu odbioru. Paczkę sprawdzisz na [ecoscooting.com](https://www.ecoscooting.com/), wpisując numer z wiadomości e-mail od Ecoscooting, albo wklejając go w polu powyżej.

## Numer śledzenia Ecoscooting: gdzie jest i jak wygląda

Numer śledzenia znajdziesz w e-mailach od Ecoscooting, wysyłanych od momentu ruszenia przesyłki w drogę; w wiadomości potwierdzającej doręczenie widnieje on w samym temacie. Ecoscooting nie podaje nazwy nadawcy wiadomości SMS. Nie możesz znaleźć numeru? Zobacz poradnik [jak znaleźć numer śledzenia](guide:find-tracking-number).

Przewoźnik nie publikuje na stronie wzoru numeracji, ale w praktyce jego numery przyjmują następujące formy (cyfry przykładowe):

| Format | Co oznacza |
| --- | --- |
| `CNESP12345678901234567890` | `CNESP` i 20 cyfr: przesyłka do Hiszpanii (jeśli wkleisz wariant bez `CN`, czyli `ESP` + 5-cyfrowy kod pocztowy + `1234` + 11 cyfr, Peek sam dopisze `CN`). |
| `CNPRT00000000000000000000` | `CNPRT` i 20 cyfr: przesyłka do Portugalii (analogicznie po wklejeniu `PRT` + 7-cyfrowy kod pocztowy + `1234` + 9 cyfr Peek przywraca prefiks `CN`). |
| `280010000123456789` | 18 cyfr, hiszpańska etykieta Ecoscooting: 5-cyfrowy kod pocztowy w Hiszpanii (`01`–`52`), `0000` oraz 9 cyfr. |
| `C26111512345678` | `C` lub `E`, 6-cyfrowa data (`RRMMDD`) i 8 cyfr: numer paczki z zamówienia SHEIN, które w Hiszpanii doręcza również [Paack](carrier:paack). |

Zbliżone oznaczenia często należą do wcześniejszych operatorów na trasie: przy zakupach na AliExpress zobaczysz zwykle kod `LP` z 14 cyframi — to numer logistyczny śledzony przez [Cainiao](carrier:cainiao). Przed lokalnym doręczeniem paczka z Chin przechodzi też odprawę celną, np. u agenta celnego CACESA. Jeśli `ecoscooting.com` nie rozpoznaje jeszcze Twojego kodu, sprawdź numer na karcie zamówienia; więcej szczegółów znajdziesz w poradnikach [śledzenie paczki z Chin](guide:tracking-from-china) oraz [formaty numerów śledzenia](guide:tracking-number-formats).

Dla nieznanego numeru strona `ecoscooting.com` wyświetla `No data found` i prosi o sprawdzenie numeru zamówienia.

## Co oznaczają statusy śledzenia Ecoscooting

Serwis `ecoscooting.com` wyświetla śledzenie po angielsku, hiszpańsku lub portugalsku zależnie od języka przeglądarki (przy polskiej przeglądarce otwiera się po angielsku, z przełącznikiem języka na górze). Każdy wpis składa się z pogrubionego nagłówka i opisu pod spodem — poniżej znajdują się angielskie opisy widoczne na stronie przewoźnika oraz w Peek:

| Status w Ecoscooting | Co oznacza dla Ciebie |
| --- | --- |
| `Shipment data information received` | Zamówienie zostało zarejestrowane; Ecoscooting nie ma jeszcze fizycznie paczki. |
| `Shipment is in transit to Cainiao Express` | Przesyłka jest nadal w transporcie w sieci [Cainiao](carrier:cainiao), zanim dotrze do Hiszpanii lub Portugalii. |
| `Your shipment has been accepted by delivery carrier` | Ecoscooting przyjął paczkę w Hiszpanii lub Portugalii. |
| `Your shipment has arrived at sorting center` | Paczka dotarła do sortowni Ecoscooting. |
| `Your shipment is out for delivery` | Jest dziś u kuriera w doręczeniu. |
| `Your shipment delivery attempt failed [Recipient not at home]` | Kurier był pod adresem, ale nikt nie odebrał; sprawdź e-mail, aby dowiedzieć się, co dalej. |
| `Service point closed` | Po nieudanych próbach pod drzwiami kurier nie mógł zostawić paczki także w punkcie odbioru — przesyłka tam na Ciebie nie czeka, a Peek pokazuje „Próba doręczenia”. |
| `Your package has been processed by the courier. We will notify you when it is ready for pickup at the point.` | Kurier przekazał paczkę do punktu odbioru, ale punkt jeszcze jej nie przyjął do wydania. |
| `Your shipment has arrived at parcelshop and you‘ll receive pickup instructions shortly` | Gotowa do odbioru w punkcie (parcelshop) — kod PIN przyjdzie e-mailem lub SMS-em. |
| `Your shipment has been collected by consignee at the parcelshop` | Odebrana przez adresata w punkcie odbioru. |
| `Your shipment has been delivered successfully` | Doręczona. |
| `Your shipment has expired at the parcelshop, and will be returned to sender` | Minął czas na odbiór w punkcie — paczka wraca do nadawcy. |
| `Your shipment will be returned to its origin` | Przesyłka jest odsyłana do nadawcy. |

Najłatwiej pomylić etap przekazania paczki do punktu: na `ecoscooting.com` jego pogrubiony tytuł brzmi `Delivered to PUDO` (*pick-up and drop-off point*), co wygląda jak doręczenie do rąk własnych. Tak nie jest — poczekaj na wiadomość o przyjęciu w punkcie oraz kod PIN, zanim pójdziesz po odbiór. Inne komunikaty wyjaśnia artykuł [statusy śledzenia przesyłek](guide:tracking-statuses).

Nad historią `ecoscooting.com` wyświetla też główny nagłówek: pozostaje on ustawiony na `In transit` nawet po doręczeniu paczki, dlatego patrz zawsze na najnowszy wiersz w historii. `Delivery Failure` pojawia się po skanie oznaczonym jako problem, `Exceeded pickup time` przy przekroczeniu terminu odbioru w punkcie, a `No trajectory` oznacza brak jakichkolwiek skanów.

## Dostawa Ecoscooting, punkty odbioru i nieudane próby

W **Hiszpanii** Ecoscooting doręcza paczki **codziennie, również w soboty i niedziele**; w **Portugalii** od **poniedziałku do soboty**. W żadnym z tych krajów nie można wybrać przedziału godzinowego dostawy.

Pod wskazany adres kurier podejmuje **dwie próby doręczenia**; zobacz też [nieudana próba doręczenia](guide:missed-delivery). Po nieudanym podjeździe Ecoscooting wysyła e-mail z podaniem przyczyny: jeśli adres był błędny, poprawisz go bezpośrednio z linku w wiadomości, a kolejna próba nastąpi w ciągu **3 dni roboczych**; przy innych powodach kurier również ponawia próbę w ciągu **3 dni roboczych**. Po dwóch nieudanych próbach dalszy tryb zależy od sytuacji i jest potwierdzany e-mailem — w Hiszpanii, tam gdzie to możliwe, przesyłka trafia do pobliskiego punktu odbioru.

Wiesz z wyprzedzeniem, że nie będzie Cię w domu? Z linku w e-mailu wysyłanym przez Ecoscooting na początku trasy możesz upoważnić inną osobę do podpisu albo przekierować paczkę do punktu odbioru lub automatu paczkowego.

Sieć Ecoscooting obejmuje **ponad 8000 punktów odbioru** (*pickup points*) oraz automaty paczkowe (*lockers*). Po dotarciu paczki do punktu otrzymasz e-mail lub SMS z **kodem PIN**, adresem i godzinami otwarcia. Na odbiór masz **14 dni** — po tym czasie przesyłka wraca do nadawcy i możesz poprosić sprzedawcę o zwrot pieniędzy; sprawdź też [nieodebrana przesyłka](guide:uncollected-parcel). Hiszpańskie FAQ zastrzega, że paczki znajdującej się już w punkcie odbioru nie da się przenieść do innego punktu ani z powrotem pod adres domowy.

## Paczka Ecoscooting niedostarczona, zagubiona lub skradziona

Ecoscooting podkreśla, że realizuje wyłącznie lokalne doręczenie: w sprawie brakujących lub pomylonych produktów, zwrotów i refundacji należy kontaktować się ze sklepem lub platformą zakupową (w UE sprzedawca odpowiada za towar „od momentu wysyłki do chwili odbioru” i bez innych ustaleń ma 30 dni na dostawę). Gdy status stoi w miejscu od kilku dni, zajrzyj do poradnika [śledzenie nie aktualizuje się](guide:tracking-not-updating). Przy komunikacie `Your shipment has an operational anomaly occurred at the sorting center. Issue is being resolved, updates will follow` Peek zmienia etap na „Zgłoszono problem”: w sortowni Ecoscooting wystąpiło zakłócenie operacyjne, nad którego rozwiązaniem pracuje przewoźnik.

- **Oznaczona jako doręczona, ale nie dotarła:** odszukaj e-mail o doręczeniu od Ecoscooting (po hiszpańsku zatytułowany *„¡Bien! Hemos entregado tu paquete …”*) i kliknij przycisk **„No recibido”** (*„Não recebido”* po portugalsku) albo wyślij formularz zgłoszeniowy, na który Ecoscooting odpowiada w ciągu **10 dni roboczych**. Więcej w poradniku [dostarczona, ale nieotrzymana](guide:delivered-not-received).
- **Zagubiona lub skradziona:** jeśli w historii pojawi się `Package stolen` lub wyjątek `"Parcel lost"`, sprawdź, czy przyszedł e-mail zatytułowany *„Aviso importante: Su paquete no puede ser entregado”* (po portugalsku *„Aviso importante: Não foi possível entregar a sua encomenda”*). Jeśli go masz, przekaż go sprzedawcy, występując o zwrot środków; jeśli nie dotarł, skontaktuj się z obsługą klienta Ecoscooting. Zobacz też [zagubiona paczka](guide:lost-parcel).
- **Uszkodzona przesyłka:** na stronach Ecoscooting brak informacji o procedurze szkód, terminach roszczeń czy odszkodowaniach — zgłoś szkodę bezpośrednio sprzedawcy, korzystając ze wskazówek w artykule [uszkodzona paczka](guide:damaged-parcel).

> W sekcji FAQ Ecoscooting ostrzega przed podejrzanymi telefonami i wiadomościami e-mail oraz przypomina, by nigdy nie przekazywać poufnych danych niezaufanym podmiotom. Więcej w poradniku [fałszywe SMS-y o paczkach](guide:delivery-scam-texts).

## Kontakt z Ecoscooting: telefon, e-mail i czat

Dane kontaktowe podane na `ecoscooting.com` w listopadzie 2026 r.:

- **Hiszpania:** `+34 912 15 93 69` lub bezpłatna infolinia `+34 800 00 03 69`; e-mail `customersupport@ecoscooting.com`.
- **Portugalia:** `+351 300 609 006`; e-mail `suportecliente@ecoscooting.com`.
- **Godziny pracy:** od poniedziałku do piątku `09:00–18:00`, w soboty i niedziele `09:00–13:00`.
- **Czat i formularz kontaktowy:** na [stronie obsługi klienta Ecoscooting](https://www.ecoscooting.com/atencionCliente), gdzie opisano również, jak pobrać urzędowy hiszpański arkusz reklamacyjny (*Hoja de reclamaciones*).

Nota prawna (*Aviso legal*) podaje ponadto numer `931 222 879` do zapytań i skarg oraz potwierdza dostępność urzędowych arkuszy reklamacyjnych tam, gdzie wymaga tego prawo. Operatorem jest spółka **Ecoscooting Delivery, S.L.** (NIF `B65874893`) z siedzibą w San Fernando de Henares pod Madrytem.

## Śledzenie przesyłek Ecoscooting w aplikacji Peek

[Peek](/) od razu rozpoznaje jako Ecoscooting numery `CNESP` i `CNPRT` z 20 cyframi (uzupełniając pominięte `CN`, gdy wpiszesz pełny układ numeru bez tych dwóch liter) oraz większość 18-cyfrowych hiszpańskich etykiet złożonych z kodu pocztowego (`01`–`52`), `0000` i 9 cyfr — z wyjątkiem prefiksu `3232`, dla którego Peek pyta bpost i Ecoscooting w tej samej pierwszej rundzie (bpost jako pierwszy, z wyjątkiem Hiszpanii i Portugalii), oraz prefiksu `44`, przypisanego do Quickpac. Dla numeru paczki SHEIN (`C` lub `E`, sześciocyfrowa data i 8 cyfr) Peek odpytuje Ecoscooting, a na liście „Pasują do numeru” pokazuje również [Paack](carrier:paack). Innych 18-cyfrowych numerów Peek nie wysyła do Ecoscooting (mogą należeć do bpost, GLS Spain lub Poczty Szwajcarskiej): użyj przycisku „Zmień” i wybierz Ecoscooting albo wklej link śledzenia z `ecoscooting.com`.

Gdy [Cainiao](carrier:cainiao) lub CACESA zwróci numer `CNESP`/`CNPRT` bądź 18-cyfrową etykietę rozpoznawaną przez Peek jako Ecoscooting, Peek automatycznie przełącza dalsze śledzenie na Ecoscooting; pozostałe 18-cyfrowe numery tylko wtedy, gdy Cainiao lub CACESA wskaże Ecoscooting jako przewoźnika.

Peek pokazuje aktualny status, historię z dokładnymi godzinami skanów Ecoscooting (bez miejscowości, ponieważ przewoźnik ich nie podaje), wagę, godzinę doręczenia oraz — po dotarciu do punktu odbioru — nazwę i adres punktu, zachowywane również po odebraniu przesyłki. Przewidywana data dostawy nie jest udostępniana. Godziny widnieją w Twojej strefie czasowej, o czym na peektracker.com i w aplikacji na iPhone’a przypomina wiersz pod historią: „Godziny w Twojej strefie czasowej: …”. W chwili zostawienia paczki przez kuriera w punkcie (`Delivered to PUDO`) Peek utrzymuje jeszcze etap „W doręczeniu” i przełącza go na „Do odbioru” dopiero wtedy, gdy punkt potwierdzi przyjęcie paczki — mimo to zaczekaj na e-mail lub SMS z kodem PIN. Peek pomija kod PIN, telefon i godziny otwarcia punktu, kody pocztowe oraz numery zamówień. Komunikaty w historii pozostają po angielsku, z wyjątkiem pojedynczego `Out for delivery`, wyświetlanego jako „W doręczeniu”. Ponieważ Ecoscooting nie zwraca jednoznacznej odmowy dla nieistniejącego numeru, błędny kod pozostaje po prostu bez aktualizacji.

Peek sprawdza status nawet co 10 minut, a w dniu wydania paczki do doręczenia nawet co 2 minuty od 6:00 czasu lokalnego, dopóki odpowiada bezpośrednio system Ecoscooting (przez serwisy śledzenia co 15 minut), wysyłając powiadomienia „Wszystkie aktualizacje”, „Tylko ważne” lub „Tylko w dniu dostawy”. Po dwóch dniach bez nowości lub po 12 godzinach na etapie „Zarejestrowana” bądź „Przyjęta przez przewoźnika” sprawdzanie przechodzi na tryb godzinowy.

## Najczęstsze pytania o śledzenie Ecoscooting

### Czy Ecoscooting to prawdziwa, wiarygodna firma kurierska?

Tak. Ecoscooting Delivery, S.L. to zarejestrowana w Hiszpanii spółka z siedzibą w San Fernando de Henares koło Madrytu. Działa na rynku logistycznym w Hiszpanii i Portugalii, deklarując ponad 8 lat doświadczenia w obsłudze międzynarodowych platform e-commerce oraz lokalnych sprzedawców.

### Na czym polega dostawa Ecoscooting?

To lokalne doręczenie ostatniego odcinka trasy w Hiszpanii lub Portugalii dla paczek zamówionych na zagranicznych platformach internetowych — pod drzwi lub do punktu odbioru. Sama umowa sprzedaży, zwroty i reklamacje towaru pozostają po stronie sklepu.

### Czy Ecoscooting doręcza paczki w weekendy?

W Hiszpanii tak — przez cały tydzień, włącznie z sobotami i niedzielami. W Portugalii kurierzy doręczają od poniedziałku do soboty, a w żadnym z obu krajów nie ma wyboru godzin dostawy.

### Ile czasu zajmuje dostawa przez Ecoscooting?

W materiałach dla sklepów Ecoscooting deklaruje **24–48 godzin** na doręczenie krajowe od momentu przejęcia paczki w swojej sieci. Nie obejmuje to jednak wcześniejszego transportu międzynarodowego, a strona śledzenia nie podaje przewidywanej daty doręczenia.

:::sources
- [Ecoscooting: Customer Service / Frequent Questions](https://www.ecoscooting.com/atencionCliente) – kontakty, godziny pracy, czat, nieudane próby, 3 dni robocze, upoważnienie do podpisu, 14 dni na odbiór, doręczono lecz nie otrzymano, zagubienie/kradzież, Hoja de reclamaciones, ostrzeżenie przed oszustwami i ponad 8 lat doświadczenia (wersja angielska, hiszpańska i portugalska)
- [Ecoscooting: Home](https://www.ecoscooting.com/) – Hiszpania i Portugalia, dni dostaw, dwie próby, przekierowanie do punktu w Hiszpanii, 24/48 godzin, wyszukiwarka śledzenia i nagłówki statusów
- [Ecoscooting: Home Deliveries](https://www.ecoscooting.com/toDoor) – dwie próby pod drzwiami, punkt odbioru w Hiszpanii, 24–48 godzin
- [Ecoscooting: Network of Pickup Points and Lockers](https://www.ecoscooting.com/pickUpPoint) – ponad 8000 punktów odbioru i automaty paczkowe
- [Ecoscooting: Aviso legal (hiszpański)](https://www.ecoscooting.com/aviso-legal) – dane spółki Ecoscooting Delivery, S.L., siedziba w San Fernando de Henares, telefon 931 222 879 i arkusze reklamacyjne
- [Your Europe: Wysyłka i dostawa](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_pl.htm) – odpowiedzialność sprzedawcy do chwili odbioru i 30-dniowy termin dostawy w UE
- [Universal Parcel Scraper: Ecoscooting (angielski)](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ecoscooting) – rozpoznawane formaty w Peek, przejęcie śledzenia po Cainiao/CACESA, obsługa `Delivered to PUDO` i odczytywane pola
:::
