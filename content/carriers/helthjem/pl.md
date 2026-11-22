---
title: Śledzenie Helthjem: statusy, punkty odbioru i kontakt
description: Śledzenie paczki Helthjem w Norwegii: skąd wziąć numer, tłumaczenie norweskich statusów, dostawy nocne, punkty odbioru i kontakt.
slug: sledzenie-helthjem
published: 2026-11-22
updated: 2026-11-22
---

Helthjem to norweska usługa kurierska dla sklepów internetowych: zostawia paczki na wycieraczce lub w skrzynce pocztowej — również podczas nocnych kursów — albo przekazuje je do punktu odbioru. Wklej numer w polu powyżej, aby śledzić przesyłkę; własna strona `helthjem.no` też ma śledzenie. Ponieważ Helthjem podaje komunikaty po norwesku, poniżej znajdziesz ich tłumaczenia.

## Gdzie znaleźć numer śledzenia Helthjem

Numer podaje sklep, który nadał paczkę — zwykle w e-mailu z potwierdzeniem wysyłki lub na stronie zamówienia. Brak numeru? Zapytaj sklep i zobacz [gdzie znaleźć numer śledzenia](guide:find-tracking-number).

Numery, które Peek przypisuje Helthjem, wyglądają tak (cyfry wymyślone), w porównaniu z podobnymi numerami Bring:

| Numer | Do kogo należy |
| --- | --- |
| `370724764829173648` | Helthjem: 18 cyfr zaczynających się od `37072476`. |
| `00370724764829173648` | Ten sam numer z `00` na początku, jak pod kodem kreskowym: 20 cyfr. |
| `370748291736482919` | Bring: własne 18-cyfrowe numery również zaczynają się od `370` lub `373`. |
| `RR482917367NO` | Bring: kody pocztowe kończące się na `NO`. |

Ostatnia cyfra jest cyfrą kontrolną. Wyszukiwarka Helthjem przyjmuje też 17-cyfrowy numer przesyłki, z prefiksem `401` lub bez.

Numery Helthjem [wyglądają bardzo podobnie do zakresu](guide:tracking-number-formats) `370` firmy Bring; Peek rozróżnia je po początku `37072476` i cyfrze kontrolnej. Jeśli Helthjem nie zna numeru lub już go nie przechowuje, wyszukiwarka nic nie pokaże. Sprawdź cyfry, spróbuj Bring lub zapytaj sklep o wybranego przewoźnika.

## Znaczenie statusów śledzenia Helthjem

Strona Helthjem pokazuje status, skany z miejscowością, przewidywany dzień, sklep nadający oraz — gdy dotyczy — punkt odbioru z adresem i zdjęcie zostawionej paczki. Helthjem klasyfikuje przesyłkę i skany w dwunastu stanach po norwesku (bokmål). W typowej kolejności:

| Status | Co to oznacza dla ciebie |
| --- | --- |
| `Nettbutikken har meldt at en pakke er på vei!` | Sklep [zgłosił przesyłkę](guide:tracking-statuses). Helthjem nie ma jej jeszcze u siebie. |
| `Vi har mottatt pakken din, og gjør den klar for veien videre.` | Przyjęta w podanym terminalu Helthjem, przygotowywana do dalszej drogi. |
| `Pakken er under transport.` | W transporcie: skan terminalu lub sortowni. |
| `Planlagt levering natt til 8. oktober!` | Zaplanowana na nocny kurs prowadzący do podanej daty. Kurier jeszcze jej nie zabrał — wyjedzie tej nocy. |
| `Budet fikk ikke med seg pakken din ut i natt.` | Nocny kurier nie zabrał paczki. Czeka na późniejszy kurs. |
| `Pakken er levert på dørmatten din!` | Doręczona na wycieraczkę. |
| `Pakken er levert i postkassen din!` | Doręczona do skrzynki pocztowej. |
| `På vei til hentested`, `Leveres til hentested` | Przekierowana do punktu odbioru zamiast do domu. |
| `Klar til henting` | Gotowa do odbioru w punkcie wskazanym na stronie Helthjem. |
| `Pakken er hentet` | Odebrana w punkcie odbioru. |
| `Pakke ikke hentet` | Nieodebrana na czas — wraca do sklepu. |
| `Returneres` | W drodze powrotnej do sklepu lub już zwrócona. |
| `Pakken er kansellert` | Przesyłka została anulowana. |

**Miejscowość wygląda błędnie.** Przy `Vi har mottatt pakken din…` i `Pakken er under transport.` miejscowość oznacza terminal lub sortownię Helthjem, a nie twój adres. W 2023 r. Helthjem prowadził własny terminal paczkowy w Vestby pod Oslo, a rejestr firm nadal wykazuje tam oddział — skan w Vestby pokazuje miejsce sortowania, nie cel podróży.

## Dostawa Helthjem: nocne kursy, wycieraczka i punkty odbioru

**Kto doręcza.** W 2023 r. Helthjem sprzedawał dostawy sklepom i powierzał paczki kolporterom prasy sieci Helthjem, doręczającym sześć dni w tygodniu. Większe paczki, których nie dało się zostawić pod drzwiami, przewoził PostNord.

**Nocne kursy.** W przeddzień dostawy do domu Helthjem wpisuje paczkę na kurs nocny z komunikatem `Planlagt levering natt til 8. oktober!`, co oznacza wyjazd w nocy poprzedzającej 8 października. W chwili tego wpisu kurier jeszcze jej nie ma.

**Gdzie jest zostawiana.** Na wycieraczce (`Pakken er levert på dørmatten din!`) lub w skrzynce (`Pakken er levert i postkassen din!`). Strona Helthjem może wyświetlić zdjęcie z doręczenia; Peek go nie pokazuje, więc sprawdź tam.

**Punkty odbioru (*hentested*).** W 2023 r. Helthjem wydawał w sklepach większe paczki, przesyłki klientów wybierających odbiór oraz przesyłki tam, gdzie kolporterzy nie docierali. Paczka skierowana do punktu ma status `På vei til hentested` i dostaje nową etykietę z nowym numerem, ale twój pierwotny numer nadal ją odnajduje. Gdy jest gotowa, pojawia się `Klar til henting` z nazwą i adresem punktu na stronie Helthjem. Po przekroczeniu terminu pojawia się `Pakke ikke hentet` i paczka wraca do sklepu.

**Paczki PostNord.** Gdy Helthjem oznacza paczkę jako usługę PostNord, jego strona kieruje do śledzenia PostNord z numerem przesyłki.

**Paczki z Chin przez 4PX.** Wpis `Parcel arrival notified` w historii 4PX to powiadomienie Helthjem o zgłoszeniu paczki przez sklep, przekazywane po angielsku po przekazaniu z 4PX.

## Paczka Helthjem opóźniona, zaginiona lub uszkodzona

**Brak aktualizacji.** Po zaplanowaniu nocnego kursu lub komunikacie `Budet fikk ikke med seg pakken din ut i natt.` paczka czeka na kolejny nocny kurs. Jeśli przez dni nic się nie zmienia, zapytaj sklep i zobacz [dlaczego śledzenie przestaje się aktualizować](guide:tracking-not-updating).

**Doręczona, ale jej nie ma.** Ostatni skan podaje, gdzie została zostawiona: na wycieraczce czy w skrzynce. Sprawdź ewentualne zdjęcie na stronie Helthjem, oba miejsca oraz domowników. Nadal jej nie ma? Skontaktuj się ze sklepem i zobacz [doręczona, ale nieotrzymana](guide:delivered-not-received).

**Zaginiona, uszkodzona lub opóźniona.** Klientem Helthjem jest sklep — zacznij od niego. Norweska Rada Konsumentów (*Forbrukerrådet*) wskazuje, by przy niedostarczonym zamówieniu online najpierw zwrócić się do sprzedawcy. W przypadku norweskiego sklepu możesz potem zgłosić sprawę do mediacji w Urzędzie Ochrony Konsumentów (*Forbrukertilsynet*). Przy płatności kartą kredytową masz ustawowe prawo żądać zwrotu od banku, gdy nie otrzymasz zamówionego towaru. Zobacz [zaginiona paczka](guide:lost-parcel).

**Zwrócona lub anulowana.** Po `Pakke ikke hentet`, `Returneres` lub `Pakken er kansellert` ustal ze sklepem zwrot pieniędzy lub ponowną wysyłkę.

## Obsługa klienta i kontakt Helthjem

Na stronie `helthjem.no` znajduje się dział pomocy i kontaktu. Gdy przed doręczeniem miną cztery dni bez skanu, komunikat w Peek kieruje tam linkiem „Poproś przewoźnika Helthjem o pomoc”. W sprawach zamówienia, zwrotu lub reklamacji pisz do sklepu, który nadał paczkę przez Helthjem.

> Nkom, norweski urząd łączności, ostrzega przed każdym linkiem w SMS-ie, nawet jeśli czekasz na paczkę: takie linki prowadzą na fałszywe strony wyłudzające dane kart, a rzekomy link od norweskiej firmy bez domeny `.no` wymaga szczególnej ostrożności. Policja radzi samemu wyszukać stronę zamiast klikać link. Więcej w [fałszywe SMS-y o dostawie](guide:delivery-scam-texts).

## Śledzenie paczki Helthjem w Peek

[Peek](/) obsługuje ponad 4500 przewoźników i od razu rozpoznaje Helthjem po 18 cyfrach zaczynających się od `37072476` lub tym samym numerze z `00` na początku, gdy zgadza się cyfra kontrolna („Wykryty przewoźnik”). Inne numery od `370` lub `373` mogą należeć do Bring — Peek pyta Bring i pozostałych pasujących przewoźników. 17-cyfrowe numery przesyłek Helthjem i pozostałe 20-cyfrowe kody wymagają przycisku „Zmień” albo linku ze strony `helthjem.no/sporing/`.

Peek pokazuje status, miejsce każdego skanu (w tym skany sortowni ukryte na stronie Helthjem), godzinę doręczenia, sklep nadający oraz nazwę punktu odbioru w trakcie oczekiwania i po odbiorze. Gdy Helthjem nadaje nową etykietę dla punktu odbioru, Peek wyświetla nowy numer pod „Helthjem śledzi przesyłkę pod numerem”. Statusy pozostają w norweskim brzmieniu Helthjem, a godziny przeliczane są na twoją strefę („Godziny w twojej strefie czasowej:” i jej nazwa). Po zaplanowaniu nocnego kursu Peek wyświetla datę z `Planlagt levering natt til …` jako przewidywany dzień. Pomija ogólny dzień przewidywany ze strony Helthjem, adres punktu odbioru, zdjęcie oraz kody pocztowe.

Gdy Helthjem oznacza paczkę jako doręczaną przez PostNord, karta pokazuje „Doręcza: PostNord” i Peek może śledzić ją dalej w PostNord pod numerem przesyłki. Dopóki PostNord ma tylko zgłoszenie bez własnego skanu, wyświetla się „Zgłoszona przewoźnikowi PostNord, jeszcze nieprzyjęta”.

Gdy masz przesyłkę otwartą w Peek — albo w dniu dostawy przy włączonych powiadomieniach — Peek pyta bezpośrednio Helthjem nawet co 10 minut, a w dniu, gdy Helthjem (lub PostNord po przekazaniu) nada status „W doręczeniu”, nawet co 2 minuty od 6:00 czasu w Oslo, dopóki tracker odpowiada; w pozostałym czasie oraz po dwóch dniach bez zmian (12 godzinach, gdy przesyłka nadal ma status „Zarejestrowana” lub „Przyjęta przez przewoźnika”) sprawdza co godzinę. Dla powiadomień wybierz „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Po czterech dniach bez nowego skanu w drodze, poza odprawą celną i zgłoszonym problemem, Peek pokazuje datę ostatniego skanu („Brak aktualizacji od …”) i komunikat „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika Helthjem.” z linkiem „Poproś przewoźnika Helthjem o pomoc”. Peek nie może kontaktować się z Helthjem w twoim imieniu.

## Pytania o śledzenie Helthjem

### Czy Helthjem jest wiarygodną firmą?

Tak. Helthjem Netthandel AS jest zarejestrowana w Oslo jako firma pocztowa i kurierska; podczas walnego zgromadzenia w 2025 r. norweska grupa medialna Polaris Media wykazała w niej 34% udziałów. W 2023 r. paczki trafiały do odbiorców za pośrednictwem kolporterów prasy, którzy w 2015 r. przyjęli markę Helthjem. Uważaj jednak na SMS-y podszywające się pod tę nazwę z linkami pytającymi o dane karty.

### O jakiej porze doręcza Helthjem?

Dostawy do domu często odbywają się w ramach nocnych kursów. Gdy Helthjem taki zaplanuje, śledzenie dzień wcześniej wyświetla `Planlagt levering natt til …` z datą kończącą tę noc; Peek pokazuje tę datę jako przewidywany dzień.

### Co oznacza „Budet fikk ikke med seg pakken din ut i natt”?

Nocny kurier nie zabrał twojej przesyłki. Pozostaje ona w Helthjem i czeka na kolejny nocny kurs.

### Dlaczego Helthjem nie znajduje mojego numeru śledzenia?

Helthjem nie zna tego numeru lub już go nie przechowuje, albo należy on do innego przewoźnika — numery Bring również zaczynają się od `370` lub `373`. Sprawdź cyfry, spróbuj Bring lub zapytaj sklep.

### Jak skontaktować się z Helthjem?

Przez stronę pomocy i kontaktu na `helthjem.no`. W sprawie zwrotów pieniędzy i reklamacji kontaktuj się ze sklepem, który wysłał paczkę.

:::sources
- [Brønnøysund Register Centre: Helthjem Netthandel AS](https://virksomhet.brreg.no/nb/oppslag/enheter/914919010) – spółka w Oslo, usługi pocztowe i kurierskie
- [Brønnøysund Register Centre: Helthjem Netthandel AS avd Vestby](https://virksomhet.brreg.no/nb/oppslag/underenheter/925669946) – oddział w Vestby
- [Konkurransetilsynet: Schibsted Delivery's notice of its acquisition of Amedia Distribusjon (23 October 2023)](https://konkurransetilsynet.no/wp-content/uploads/2023/10/2023_0474-1-OFF-Schibsted-Delivery-AS-Amedia-Distribusjon-AS-1.pdf) – sieć Helthjem od 2015 r., doręczenia sześć dni w tygodniu, obsługa sklepów, PostNord dla większych paczek, punkty w sklepach, terminal Vestby
- [Polaris Media: Annual general meeting 2025](https://www.polarismedia.no/wp-content/uploads/2025/05/Presentasjon-ordin%C3%A6r-generalforsamling-2025-1.pdf) – 34% udziałów w Helthjem Netthandel
- [Forbrukerrådet: Netthandel](https://www.forbrukerradet.no/forside/okonomi-og-betaling/netthandel-misfornoyd/) – najpierw kontakt ze sprzedawcą, mediacja, obciążenie zwrotne karty kredytowej
- [Nkom: Slik kan du avsløre SMS-svindel](https://nkom.no/aktuelt/slik-kan-du-avslore-sms-svindel) – linki w SMS-ach o paczkach, fałszywe strony kart, domeny .no
- [Politiet: Beskytt deg mot svindel og ID-tyveri](https://www.politiet.no/rad/beskytt-deg-mot-svindel-og-id-tyveri) – samodzielne wyszukiwanie strony
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formaty numerów, strony śledzenia i pomocy Helthjem, dwanaście stanów i opisy, nocne kursy, punkty odbioru i nowe etykiety, PostNord, 4PX, pola odczytywane przez Peek i częstotliwość sprawdzania
:::
