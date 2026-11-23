---
title: Śledzenie APC Overnight: numery, statusy i doręczenia
description: Śledź przesyłkę APC: 7- i 22-cyfrowy numer przesyłki, znaczenie statusu Carded, zmiana terminu nieudanego doręczenia i kontakt z lokalnym oddziałem.
slug: sledzenie-apc-overnight
published: 2026-11-23
updated: 2026-11-23
---

APC Overnight, występująca obecnie jako The APC, to największa niezależna sieć kurierska w Wielkiej Brytanii: ponad 90 lokalnie prowadzonych oddziałów, które zazwyczaj doręczają paczki następnego dnia roboczego. Przesyłkę możesz sprawdzić w APC Choice po numerze przesyłki i kodzie pocztowym albo wkleić długi numer lub link do śledzenia w pole powyżej. Numer znajdziesz w potwierdzeniu nadania od sprzedawcy, w e-mailu lub SMS-ie od APC albo sklepu, bądź na awizo APC „Sorry we missed you”.

## Numer śledzenia APC: jak wygląda

APC posługuje się jednym numerem: „consignment number” to 7-cyfrowy numer referencyjny przypisany do paczki. Formularz śledzenia wymaga podania tego numeru razem z kodem pocztowym doręczenia.

Sklepy często przesyłają zamiast tego dłuższy kod: 22-cyfrowy numer w linku APC Choice (`apcchoice.apc-overnight.com/track-parcel?id=…`). Zaczyna się od daty zlecenia przesyłki i kończy tym samym 7-cyfrowym numerem przesyłki.

:::anatomy 20260715 4829173 7364058
- 20260715 | Data zlecenia przesyłki w formacie 20RRMMDD: tutaj 15 lipca 2026
- 4829173 | Siedem kolejnych cyfr
- 7364058 | 7-cyfrowy numer przesyłki, o który pytają strony APC
:::

Z długim numerem w linku APC Choice otwiera historię przesyłki bez kodu pocztowego. Kod pocztowy jest potrzebny dopiero do podglądu potwierdzenia doręczenia albo zmiany opcji dostawy.

Na dwie rzeczy warto zwrócić uwagę:

- 22-cyfrowy numer, który nie zaczyna się od prawdziwej daty (np. `4829173640582917364058`), nie należy do APC. Kody tej długości mogą należeć do [USPS](carrier:usps), [Poczty Austriackiej](carrier:austrian-post) lub Estafeta.
- Numery APC nie przypominają numerów [Royal Mail](carrier:royal-mail) (z końcówką `GB`), [Parcelforce](carrier:parcelforce), [Evri](carrier:evri) ani [DPD UK](carrier:dpd-uk).

Jeśli nic się nie pojawia, FAQ APC podaje najczęstsze powody: nadawca jeszcze nie nadał paczki, nie została ona jeszcze zeskanowana w sieci albo w numerze jest literówka. Spróbuj później lub sprawdź numer u nadawcy. Nie masz numeru? Zobacz [jak znaleźć numer śledzenia](guide:find-tracking-number).

## Statusy śledzenia APC wyjaśnione

APC Choice działa wyłącznie po angielsku. Pokazuje podsumowanie przesyłki (datę nadania, wagę, usługę np. doręczenie następnego dnia roboczego), a poniżej „Parcel journey” oraz „Delivery summary” dla każdej paczki — po jednym wierszu na skan z dniem i godziną w nawiasie.

| APC podaje | Co to oznacza dla ciebie |
| --- | --- |
| `At sending depot` | Lokalny oddział nadawcy odebrał paczkę i ją zeskanował. |
| `At Hub` | W centralnej sortowni APC — National Sortation Centre w Cannock. |
| `At delivery depot` | W lokalnym oddziale, który dostarczy paczkę do ciebie. |
| `Out for delivery` | Dziś w samochodzie kuriera. APC podaje 2-godzinne szacowane okno dostawy. |
| `Carded` | Kurier podjął próbę doręczenia, nikogo nie zastał i zostawił awizo „Sorry we missed you”. |
| `Delivery Rearranged By Customer` | Ty (lub ktoś pod adresem) zmieniłeś termin lub opcję dostawy w APC Choice. |
| `Delivered` | Doręczono. Potwierdzenie doręczenia jest dostępne po podaniu kodu pocztowego. |
| `Returned To Sender` | W drodze powrotnej do sklepu, na przykład po nieudanych próbach doręczenia bez odpowiedzi. |

`Delivery Rearranged By Customer` nie oznacza przemieszczenia paczki: czeka ona w dotychczasowym miejscu do nowego dnia. Gdy śledzenie milknie między skanami, APC wyjaśnia, że przesyłka może jechać między oddziałami, czekać na przyjęcie w twoim oddziale albo skaner kuriera może mieć awarię. Zobacz [śledzenie, które się nie aktualizuje](guide:tracking-not-updating), by ocenić, kiedy działać.

## Czasy doręczeń, nieudane próby i odbiór w oddziale APC

APC podaje, że większość przesyłek w Wielkiej Brytanii dociera następnego dnia roboczego po nadaniu, zależnie od lokalizacji. Nadawca wybiera usługę: doręczenie następnego dnia do 10:30, do 12:00 (czasu brytyjskiego) lub standardowo w ciągu następnego dnia roboczego; doręczenie w sobotę jest dostępne w wielu rejonach, jeśli zamówił je nadawca. Gdy sklep przekazał APC twoje dane kontaktowe, możesz otrzymać 2-godzinne okno e-mailem lub SMS-em po zaplanowaniu trasy, a APC Choice pokazuje, jak blisko jest kurier.

APC Choice pozwala zmieniać dostawę aż do chwili doręczenia, także po pozostawieniu awiza. W zależności od usługi i zgody nadawcy możesz:

- przełożyć dostawę na dogodny dzień;
- odebrać paczkę w lokalnym oddziale APC;
- wskazać bezpieczne miejsce lub sąsiada;
- przekierować przesyłkę do punktu Parcelly (ponad 2500 punktów w całej Wielkiej Brytanii).

**Bezpieczne miejsce:** APC może zostawić przesyłkę bez podpisu wyłącznie za zgodą nadawcy lub za twoją zgodą w APC Choice, o ile opcja jest włączona. Adresy firmowe nie mają opcji bezpiecznego miejsca ani sąsiada, a próby doręczenia zaczynają się tam od 8:00 (czasu brytyjskiego).

**Po awizie:** kurier nie wraca tego samego dnia i nie można też tego dnia odebrać paczki z oddziału. Po pierwszej próbie możesz zmienić termin jeszcze raz. Przełożona dostawa korzysta z tej samej usługi; zmianę na godzinę gwarantowaną lub sobotę musi zlecić nadawca.

**Odbiór w oddziale:** zgłoś chęć odbioru przed 19:00 (czasu brytyjskiego) na kolejny dzień i zabierz dokument tożsamości oraz potwierdzenie adresu. Najpierw sprawdź godziny otwarcia oddziału.

**Trzy dni robocze:** jeśli APC nie otrzyma odpowiedzi w ciągu trzech dni roboczych od nieudanej próby doręczenia, przesyłka może wrócić do nadawcy.

## Opóźnione, uszkodzone lub zagubione paczki APC

Umowa APC wiąże przewoźnika z nadawcą, więc reklamacjami zajmuje się sklep. Przy uszkodzeniach FAQ APC wprost zaleca kontakt z nadawcą. Powiadom sklep szybko, ponieważ jego terminy w APC są krótkie: w razie uszkodzenia lub częściowego braku zawartości nadawca musi napisać do APC w ciągu trzech dni roboczych od doręczenia. Zachowaj pudełko i opakowanie — bez nich APC może odrzucić roszczenie. Odpowiedzialność APC wobec nadawcy wynosi maksymalnie 100 £ dla przesyłki paczkowej i 50 £ dla mniejszych pakietów.

Jeśli śledzenie pokazuje doręczenie, a nic nie dotarło, potwierdzenie doręczenia (zdjęcie, punkt GPS, data i godzina) znajdziesz w APC Choice po wpisaniu kodu pocztowego. Następnie skontaktuj się ze sklepem i przeczytaj [doręczona, ale nieotrzymana](guide:delivered-not-received).

## Kontakt z APC

APC nie ma ogólnokrajowej infolinii, czatu ani ogólnego adresu e-mail dla odbiorców; jej strony podają następujące kanały:

- **Twój lokalny oddział:** [Depot Finder](https://apc.co.uk/depot-finder/) pokazuje dane kontaktowe i godziny otwarcia według kodu pocztowego. APC Choice podaje też oddział doręczający i jego e-mail w sekcji „APC Pinpoint”.
- **Formularz Customer Service Enquiry** na [apc.co.uk/contact-us](https://apc.co.uk/contact-us/): podaj numer przesyłki i kod pocztowy doręczenia; APC przekazuje zgłoszenie do lokalnego oddziału, który odpowiada telefonicznie lub e-mailem.

> APC podkreśla, że nigdy nie prosi o płatność ani dane karty za dostawę. Nie klikaj linków w takich wiadomościach: prześlij e-mail na adres phishing@apc-overnight.com i zgłoś go do Action Fraud.

## Śledzenie paczek APC w Peek

Wklej 22-cyfrowy numer albo link `apcchoice.apc-overnight.com` z parametrem `id=` do [Peek](/); kod pocztowy nie jest potrzebny. Ponieważ taki format jest współdzielony, Peek pyta jednocześnie APC Overnight i Pocztę Austriacką, a gdy APC zna paczkę, pokazuje „APC Overnight ma tę przesyłkę.”. 22-cyfrowy numer bez prawdziwej daty nie jest wysyłany do APC i nie pochodzi od APC: sprawdź go w sklepie. Sam 7-cyfrowy numer przesyłki nie może być tu śledzony, a linki `apc.co.uk` nie są odczytywane.

Godziny podawane przez APC nie mają strefy czasowej: Peek odczytuje je w czasie brytyjskim i wyświetla własną historię APC. Może też pokazać wagę. `Delivered` i `Out for delivery` pojawiają się w słowach Peek („Dostarczona” i „W doręczeniu”); pozostałe statusy zachowują brzmienie APC. Nie ma przewidywanej daty dostawy, 2-godzinnego okna, punktu odbioru, potwierdzenia doręczenia ani podpisu odbiorcy, a zmianę terminu nadal wykonuje się w APC Choice.

Dopóki masz przesyłkę otwartą w Peek albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza ją nawet co 10 minut, a od 6:00 czasu brytyjskiego w dniu wyjścia paczki do doręczenia nawet co 2 minuty przy bezpośredniej odpowiedzi APC Choice albo co 5 minut po sprawdzeniu przez przeglądarkę. Po dwóch dniach bez wieści (po 12 godzinach, gdy przesyłka nadal ma status „Zarejestrowana” lub „Przyjęta przez przewoźnika”) Peek sprawdza co godzinę, a jeśli przez siedem dni pozostaje na etapie „Zarejestrowana” — co 6 godzin.

Gdy przesyłka nadal na etapie „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie” nie ma nowego skanu od czterech dni, Peek oznacza ją komunikatem „Brak aktualizacji od czterech dni” (lub „Brak aktualizacji od …”), wyjaśnia: „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika APC Overnight.” i pokazuje link „Poproś przewoźnika APC Overnight o pomoc”. Możesz ustawić powiadomienia dla opcji „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek nie kontaktuje się w twoim imieniu z APC ani ze sklepem.

## Pytania o śledzenie APC

### Gdzie jest moja paczka APC?

W miejscu ostatniego skanu w APC Choice: w oddziale nadawczym, sortowni w Cannock, twoim oddziale doręczającym albo u kuriera. Szczegóły zna lokalny oddział — znajdziesz go przez Depot Finder.

### Jak długo APC przetrzymuje paczki?

APC może odesłać paczkę do nadawcy, jeśli nie zareagujesz w ciągu trzech dni roboczych od nieudanej próby doręczenia. Zmień termin lub umów odbiór w oddziale przed upływem tego czasu.

### O której godzinie doręcza APC?

Zależy to od usługi opłaconej przez nadawcę: do 10:30, do 12:00 (czasu brytyjskiego) albo w ciągu następnego dnia roboczego. Gdy przesyłka jest w doręczeniu, APC Choice oraz e-mail lub SMS od APC podają 2-godzinne okno.

### Czy APC to Parcelforce?

Nie. APC to The Alternative Parcels Company Limited — niezależna sieć lokalnych oddziałów, podczas gdy Parcelforce to osobny przewoźnik z własnymi numerami zakończonymi na `GB` lub zaczynającymi się od `PBAB`.

:::sources
- [APC: Receiving a Parcel](https://apc.co.uk/receiving-a-parcel/) – doręczenie następnego dnia roboczego, usługi, opcje APC Choice, bezpieczne miejsce, sąsiad, nieudane doręczenie, odbiór w oddziale, trzy dni, ostrzeżenia przed phishingiem
- [APC: APC Choice tracking and FAQ](https://apcchoice.apc-overnight.com/track-parcel) – 7-cyfrowy numer przesyłki i gdzie go znaleźć, statusy, 2-godzinne okno, przerwy w śledzeniu, ponowna zmiana terminu, adresy firmowe, uszkodzenia przez nadawcę
- [APC: Contact Us](https://apc.co.uk/contact-us/) – formularz Customer Service Enquiry przekazywany do lokalnego oddziału
- [APC: Depot Finder](https://apc.co.uk/depot-finder/) – dane kontaktowe i godziny otwarcia oddziałów według kodu pocztowego
- [APC: Terms and Conditions of Carriage](https://apc.co.uk/terms-conditions-of-carriage/) – terminy reklamacji, limit 100 £, opakowanie, trzy dni robocze, potwierdzenie doręczenia
- [APC: Home](https://apc.co.uk/) – od 1994 r., ponad 90 oddziałów członkowskich, nazwa firmy, National Sortation Centre
- [APC: Our Products](https://apc.co.uk/our-products/) – punkty Parcelly, lokalizacja kuriera, potwierdzenie doręczenia ze zdjęciem i GPS
- [APC: Parcelly partnership expands to 2,500 stores](https://apc.co.uk/the-apc-expands-parcelly-partnership-to-2500-stores-across-the-uk/) – sieć Parcelly
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – co Peek odczytuje z APC Choice i jak rozpoznaje numer
:::
