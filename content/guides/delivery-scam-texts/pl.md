---
title: Oszustwo na paczkę: jak rozpoznać fałszywy SMS
description: Jak rozpoznać fałszywy SMS o paczce, co naprawdę wysyłają DPD i Poczta Polska, gdzie zgłosić phishing i co zrobić po kliknięciu w link.
slug: oszustwo-paczka-falszywy-sms
picture: Telefon z wiadomością z trójkątem ostrzegawczym i haczykiem wędki na linku, przekreślona moneta i zmartwiony Pip z uniesioną dłonią „stop” w dymku.
published: 2026-10-14
updated: 2026-10-28
---

Jeśli SMS albo e-mail informuje, że Twoja paczka czeka na dopłatę, wróci do nadawcy albo wymaga poprawienia adresu, i podsuwa link, żeby to załatwić, to prawie na pewno oszustwo. Nie klikaj: sam otwórz stronę albo aplikację przewoźnika i sprawdź numer przesyłki z e-maila od sklepu. Jeśli dane karty są już wpisane na stronie z linku, od razu zadzwoń do banku. Kartę zastrzeżesz też pod numerem +48 828 828 828, jeśli Twój bank uczestniczy w Systemie Zastrzegania Kart.

## Jak rozpoznać fałszywy SMS o paczce?

Fałszywka zwykle ma kilka z tych cech:

- **Chce pieniędzy za doręczenie.** Prosi o dopłatę za paczkę albo za kuriera. [DPD Polska](carrier:dpd-pl) nigdy nie wysyła linków do dopłat SMS-em ani e-mailem, a [Poczta Polska](carrier:poczta-polska) nie wysyła SMS-ów z prośbą o dodatkową płatność za odebranie przesyłki.
- **Przychodzi akurat wtedy, gdy czekasz na paczkę.** To, że czekasz na zamówienie, niczego nie dowodzi. Policja ostrzega, że ktoś, kto często dostaje paczki, jest łatwym celem, „zwłaszcza jeśli oczekuje akurat na przesyłkę”.
- **Popędza Cię.** DPD podaje typowy przykład: „kliknij teraz, inaczej paczka wróci do nadawcy”. Presję czasu wymienia też Poczta Polska.
- **Link nie prowadzi do przewoźnika.** Strona spoza domeny dpd.com.pl, skrócony adres (DPD) albo adres, który tylko przypomina prawdziwy.
- **Nadawca wygląda podejrzanie.** DPD wśród sygnałów ostrzegawczych wymienia nietypowego nadawcę wiadomości. Poczta Polska opisuje fałszywki wysyłane z zagranicznych numerów, na przykład francuskich (+33) i filipińskich (+63).
- **Prosi o coś dziwnego:** instalację aplikacji z linku albo otwarcie załącznika. DPD nie każe instalować aplikacji z linków i nie wysyła e-maili z załącznikami do potwierdzenia nadania ani doręczenia.

Znany nadawca też niczego nie dowodzi. Poczta Polska ostrzega, że fałszywy SMS bywa podpisany nazwą „Poczta Polska” i wyświetla się w telefonie pod jej prawdziwymi wiadomościami. Zwracaj więc uwagę na treść: pośpiech, błędy językowe, brak interpunkcji (na te dwa ostatnie sygnały zwraca uwagę gov.pl).

CERT Polska prowadzi listę ostrzeżeń ze szkodliwymi domenami, a wejścia na strony z tej listy są blokowane. Nowej domeny może jednak jeszcze na niej nie być, więc to, że strona się otworzyła, nie znaczy, że jest bezpieczna.

### Jak rozpoznać fałszywy link, zanim w niego klikniesz

Linki oszustów często zaczynają się od nazwy przewoźnika. Do kogo naprawdę należy adres, poznasz po tym, co stoi tuż przed pierwszym pojedynczym `/`. Ten zmyślony przykład podszywa się pod brytyjską pocztę Royal Mail, ale z dpd.com.pl czy poczta-polska.pl trik działa tak samo:

:::anatomy royalmail.co.uk. redelivery-fee.example /pay
- royalmail.co.uk. | Nazwa przewoźnika na początku, jako przynęta. Tu to tylko etykieta
- redelivery-fee.example | Prawdziwy właściciel adresu: nie Royal Mail
- /pay | Strona, która prosi o dane karty
:::

Nawet gdy link wygląda dobrze, nie korzystaj z niego: sam wpisz adres przewoźnika.

## Czy przewoźnicy naprawdę wysyłają SMS-y?

Tak, większość wysyła, i właśnie dlatego fałszywki działają. Oto co DPD i Poczta Polska piszą o swoich wiadomościach:

| Przewoźnik | Jak naprawdę się z Tobą kontaktuje | Czego według swojej strony nigdy nie robi |
| --- | --- | --- |
| DPD Polska | Oficjalne informacje tylko na dpd.com/pl, status na tracktrace.dpd.com.pl, aplikacja tylko z Google Play i App Store | Nie wysyła linków do dopłat, nie prosi przez link o dane ani hasła, nie pośredniczy w płatnościach między osobami prywatnymi, nie każe instalować aplikacji z linku, nie wysyła załączników do potwierdzenia nadania czy doręczenia |
| Poczta Polska | SMS-y o statusie przesyłki; śledzenie na emonitoring.poczta-polska.pl i pocztex.pl. Opłaty, na przykład pobranie, płacisz dopiero przy odbiorze | Nie wysyła SMS-ów z prośbą o dopłatę za odebranie przesyłki |

Zasady [InPostu](carrier:inpost) i [DHL](carrier:dhl-ecommerce-pl) sprawdzisz na ich stronach. Prawdziwi przewoźnicy wysyłają linki, więc sam link niczego nie przesądza. Prawdziwe prośby o zapłatę też się zdarzają, prawie zawsze za cło i podatki przy paczkach spoza UE: jak przewoźnicy naprawdę o nie proszą, pokazuje poradnik [o paczce zatrzymanej przez urząd celny](guide:customs).

## Oszustwo na paczkę: co piszą oszuści?

Scenariusze się powtarzają. Te pochodzą ze stron ostrzegawczych przewoźników i policji:

- **Nieudane doręczenie.** Kurier „nie mógł doręczyć paczki lub umieścić jej w automacie paczkowym”, a link ma służyć do zmiany terminu (DPD).
- **Mała dopłata.** Za nadwagę, dezynfekcję albo błąd w adresie (DPD). Policja pisze, że kwota bywa „w granicach 1 zł”: mężczyzna z powiatu kozienickiego dopłacił 0,87 zł, a z konta zniknęło mu kilka tysięcy złotych.
- **Przesyłka wstrzymana.** Niewielka „opłata celno-skarbowa” albo „opłata za dalsze procedowanie przesyłki”, z przyciskiem do natychmiastowej płatności (Poczta Polska).
- **Aplikacja z linku.** „Błędny adres dostawy” i link do rzekomej aplikacji DPD, która może zainfekować telefon. Policja dodaje, że takie aplikacje potrafią przekierowywać SMS-y i przejąć konto bankowe.
- **Załącznik.** E-mail z „potwierdzeniem nadania” albo „listem przewozowym” w załączniku (DPD).
- **„Dostawa DPD” na OLX.** Sprzedajesz coś, a „kupujący” pisze na portalu albo na WhatsAppie, że płatność i wysyłkę obsługuje DPD, i przysyła link do „odebrania pieniędzy” (DPD).

## Jak sprawdzić, czy SMS o paczce jest prawdziwy

Żaden z tych kroków nie korzysta z wiadomości:

:::steps
- Zastanów się, czy na coś czekasz | Nie czekasz na żadne zamówienie ani przesyłkę od znajomych? Traktuj SMS jak oszustwo.
- Znajdź prawdziwy numer przesyłki | Weź go z e-maila od sklepu albo ze szczegółów zamówienia, nigdy z SMS-a.
- Sam wejdź na stronę przewoźnika | Wpisz jej adres albo otwórz aplikację przewoźnika. Nie klikaj, nie skanuj i nie dzwoń pod nic z wiadomości.
- Sprawdź, czy coś jest do zapłaty | Opłata, która naprawdę się należy, będzie widoczna na stronie albo w aplikacji przewoźnika.
- Zgłoś i usuń | Przekaż SMS na numer 8080 (więcej niżej), a potem go usuń.
:::

DPD i Poczta Polska radzą sprawdzać status przesyłki samodzielnie, wyłącznie oficjalnymi kanałami. Nie masz numeru? Zobacz, [gdzie znaleźć numer przesyłki](guide:find-tracking-number). Możesz go też wkleić do [Peeka](/): Peek pyta przewoźnika i w razie potrzeby inne źródła śledzenia, więc zobaczysz najnowsze skany, nie dotykając linku z SMS-a.

Prawdziwe nieudane doręczenie wygląda inaczej niż SMS z linkiem do płatności. Jak działa awizo i jak odebrać paczkę po nieudanej próbie, wyjaśnia poradnik [o nieudanym doręczeniu](guide:missed-delivery). Prawdziwą zmianę adresu albo terminu też załatwisz tylko w aplikacji lub na stronie przewoźnika: zobacz, [jak zmienić adres dostawy i przekierować paczkę](guide:change-delivery-address).

## Co zrobić po kliknięciu w podejrzany link?

Sam SMS w telefonie nic Ci nie zrobi. Kłopot zaczyna się, gdy klikniesz, odpowiesz, zadzwonisz albo coś wpiszesz. Co robić dalej, zależy od tego, jak daleko to zaszło:

:::steps
- Zamknij stronę | Nic więcej nie wpisuj i nie odpowiadaj na SMS.
- Zadzwoń do banku | Użyj numeru z karty albo z aplikacji banku i zastrzeż kartę.
- Zmień hasła | Do poczty i bankowości, i wszędzie tam, gdzie używasz tego samego hasła.
- Uważaj na telefony | Ktoś dzwoni „z banku”? Rozłącz się, odczekaj co najmniej 30 sekund i sam wybierz oficjalny numer.
- Zgłoś to | Na policję albo do prokuratury, jeśli zniknęły pieniądze albo wyciekły dane.
:::

**Było tylko kliknięcie?** Zamknij stronę i zgłoś SMS. Telefon pobrał z linku aplikację? Nie instaluj jej: oficjalną aplikację DPD znajdziesz tylko w Google Play i App Store. DPD radzi też po kliknięciu sprawdzić urządzenie programem antywirusowym.

**Dane karty podane albo płatność wysłana?** Poczta Polska radzi „niezwłocznie” skontaktować się z bankiem i zmienić hasła. Kartę zastrzeżesz też pod numerem +48 828 828 828: System Zastrzegania Kart działa całą dobę i jest bezpłatny, ale obsługuje tylko banki, które w nim uczestniczą. Mała kwota nie oznacza małej szkody: pamiętaj o tych 0,87 zł, po których z konta zniknęło kilka tysięcy złotych. Sprawdzaj też wyciągi.

Telefon „z banku” może wyglądać wiarygodnie. Związek Banków Polskich, policja i FinCERT ostrzegają, że oszuści potrafią wyświetlić na Twoim ekranie numer infolinii banku albo jego nazwę, więc nie oddzwaniaj na ostatnie połączenie.

## Fałszywy SMS o paczce: gdzie zgłosić oszustwo?

Zgłoś fałszywą wiadomość, nawet bez klikania w link, a potem ją usuń.

| Co się stało | Gdzie zgłosić |
| --- | --- |
| Fałszywy SMS | Przekaż go bez zmian na 8080 opcją „Przekaż” albo „Prześlij dalej”, z linkiem. W Polsce to bezpłatne |
| Fałszywy e-mail albo strona | CERT Polska: [incydent.cert.pl](https://incydent.cert.pl) albo cert@cert.pl |
| Ktoś podszywa się pod Pocztę Polską | incydent@poczta-polska.pl |
| Ktoś podszywa się pod DPD | Contact Center DPD |
| Ktoś podszywa się pod InPost albo DHL | Sprawdź na stronie przewoźnika, jak przyjmuje zgłoszenia |
| Zniknęły pieniądze albo wyciekły dane | Najpierw bank, potem zawiadomienie o przestępstwie w jednostce policji albo w prokuraturze |

Na iPhonie pod wiadomością od nieznanego nadawcy jest link **Zgłoś jako spam** (przy SMS-ach zależy to od operatora). W Wiadomościach Google na Androidzie przytrzymaj rozmowę i wybierz **Zablokuj**, a potem **Zgłoś spam**.

:::sources
- [DPD Polska: Uwaga na fałszywe SMS-y i e-maile podszywające się pod DPD](https://www.dpd.com/pl/pl/o-dpd/falszywe-maile/) – sygnały ostrzegawcze, czego DPD nigdy nie robi, scenariusze, OLX i WhatsApp, co zrobić po kliknięciu
- [Poczta Polska: Uwaga na fałszywe wiadomości podszywające się pod Pocztę Polską](https://www.poczta-polska.pl/news/uwaga-na-falszywe-wiadomosci-podszywajace-sie-pod-poczte-polska/) – wstrzymana przesyłka, małe kwoty, incydent@poczta-polska.pl, 8080, kontakt z bankiem
- [Poczta Polska: Poczta Polska ostrzega przed fałszywymi SMS-ami](https://www.poczta-polska.pl/news/poczta-polska-ostrzega-przed-falszywymi-sms-ami/) – brak SMS-ów o dodatkowej płatności za odebranie przesyłki, fałszywki podpisane „Poczta Polska” pod prawdziwymi SMS-ami, SMS-y tylko o statusie, opłaty przy odbiorze
- [Poczta Polska: Polish Post warns against phishing attacks](https://www.poczta-polska.pl/en/news/poczta-polska-ostrzega-przed-akcjami-phishingowymi/) – numery +33 i +63
- [Policja: Dostałeś sms-a z prośbą o dopłatę do przesyłki?](https://policja.pl/pol/aktualnosci/183650,Dostales-sms-a-z-prosba-o-doplate-do-przesylki-Uwazaj-to-moze-byc-oszustwo.html) – dopłata w granicach 1 zł, 0,87 zł, zainfekowane aplikacje
- [gov.pl: Coraz więcej prób wyłudzenia przez tzw. fałszywe SMS-y](https://www.gov.pl/web/baza-wiedzy/coraz-wiecej-prob-wyludzenia-przez-tzw-falszywe-sms-y-zachowaj-czujnosc) – 8080, „Przekaż”, incydent.cert.pl, cert@cert.pl, lista ostrzeżeń, błędy językowe
- [System Zastrzegania Kart: zastrzegam.pl](https://zastrzegam.pl/) – 828 828 828, bezpłatnie, całą dobę
- [bank.pl (ZBP, Policja, FinCERT): Uwaga na fałszywe połączenia telefoniczne](https://bank.pl/uwaga-na-falszywe-polaczenia-telefoniczne-przestepcow-podszywajacych-sie-pod-banki/) – spoofing, rozłącz się, 30 sekund
- [Prokuratura Okręgowa w Warszawie: Chcę złożyć zawiadomienie](https://www.gov.pl/web/po-warszawa/chce-zlozyc-zawiadomienie) – zawiadomienie w prokuraturze albo w jednostce policji
- [Apple: Zgłaszanie spamu i blokowanie nadawców w Wiadomościach na iPhonie](https://support.apple.com/pl-pl/guide/iphone/iph3f94d910d/ios) – Zgłoś jako spam
- [Google: Zgłaszanie spamu w Wiadomościach Google](https://support.google.com/messages/answer/9061432?hl=pl&co=GENIE.Platform%3DAndroid) – Zablokuj, Zgłoś spam
:::
