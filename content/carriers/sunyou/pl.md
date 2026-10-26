---
title: SunYou tracking: numer SY, statusy i kontakt
description: Paczka SunYou (SYPost) z Chin: jak wygląda numer SY, co znaczą angielskie statusy, kto doręcza ostatni odcinek i do kogo pisać, gdy paczka stoi.
slug: sunyou-sledzenie
published: 2026-10-12
updated: 2026-10-26
---

SunYou, pisane też SYPost, to firma logistyczna z Shenzhen, która wysyła małe paczki za granicę dla sprzedawców internetowych z Chin, a ostatni odcinek oddaje lokalnemu przewoźnikowi w kraju odbiorcy. Jej numery zaczynają się od SY: wpisz swój na stronie śledzenia SunYou, [sypost.net](https://www.sypost.net), albo wklej go w pole powyżej. Numer dostajesz od sklepu, razem z danymi wysyłki zamówienia.

## SunYou tracking number: jak wygląda numer

SunYou nie publikuje formatu numeru, ale numery widoczne w jego śledzeniu mają dwa kształty. Z wymyślonymi cyframi:

| Numer | Czyj to numer |
| --- | --- |
| `SY12345678901` | SY i 11 cyfr: SunYou. |
| `SYPL123456789` | SY, dwie litery i 9 cyfr: też SunYou. |
| `LP00000000000000` | LP i 14 cyfr: numer logistyczny AliExpress i Cainiao. |
| `RR000000005CN` | Dwie litery, 9 cyfr i CN: numer [China Post](carrier:china-post). |

Dwie litery po SY nie zawsze oznaczają kraj docelowy. W śledzeniu SunYou numer `SYRM` trafił do Japonii, inny do Bułgarii, a numer `SYAE` do Grecji. Nie wnioskuj więc z nich, dokąd jedzie paczka.

**Gdzie go znaleźć.** Żadna strona SunYou nie mówi, jak numer do Ciebie trafia. Paczkę nadaje sprzedawca i to on podaje numer w danych wysyłki przy zamówieniu. Nie widzisz go? Zapytaj sklep.

**Podobne numery.** Niektóre sklepy piszą, że paczka z numerem `LP` jedzie z SunYou, ale ten kształt należy do logistyki AliExpress: śledź go jako [paczkę Cainiao](carrier:cainiao). Numer SY z 10 cyframi albo z więcej niż 11 to nie jest kształt SunYou.

**Drugi numer.** Gdy paczka opuści centrum SunYou w Chinach, śledzenie często dopisuje „Carrier Tracking Number:” z numerem lokalnego przewoźnika, jego nazwę po „Distributor:” i jego stronę po „Website:”. Tym numerem śledzisz paczkę u przewoźnika, który ją doręcza.

## SunYou śledzenie: co znaczą statusy

Strona sypost.net ma 13 wersji językowych, ale polskiej wśród nich nie ma: przy polskim zapytaniu odpowiada po angielsku. Najczęstsze linie, w kolejności, w jakiej spotyka je paczka:

| SunYou pisze | Co to znaczy dla Ciebie |
| --- | --- |
| `Pre-Shipment Info Sent To United Kingdom` | Sprzedawca zarejestrował paczkę. SunYou jeszcze jej nie ma. Kraj na końcu to cel paczki. |
| `Acceptance, Sent To United Kingdom` | SunYou odebrał paczkę w Chinach. |
| `Departed Sunyou Facility` | Wyjechała z centrum SunYou, często z numerem lokalnego przewoźnika. |
| `Export Customs Clearance Successed` | Odprawa wywozowa w Chinach zakończona. |
| `Departed From Port Of Origin` | Paczka wyruszyła z portu w kraju nadania. |
| `Arrived At GMTC` | Paczka jest w GMTC, globalnym centrum przeładunkowym SunYou po drodze. |
| `Arrived At Destination Country Airport` | Dotarła na lotnisko w kraju docelowym. |
| `Customs Clearance In Process` | Trwa odprawa celna przywozowa. |
| `Customs Clearance Successed` | Odprawa celna zakończona. |
| `Hand Over To Last Mile` | Paczkę ma lokalny przewoźnik. Od teraz śledź jego numer. |
| `Delivery In Progress` | Paczka jest w doręczeniu. |
| `Delivered` | Doręczona. |

Ta sama linia bywa zapisana różnie: `GMTC, Arrived` znaczy to samo co `Arrived At GMTC`, a `Customs Clearance Was Completed` to samo co `Customs Clearance Successed`. `Delivered, At Mailbox` i `Delivered, Left At The Door` mówią, gdzie zostawiono paczkę: w skrzynce albo pod drzwiami.

Mylić mogą dwie rzeczy. Po pierwsze `Delivered` potrafi pojawić się zaraz po `Hand Over To Last Mile`, a nawet po skanie z lotniska, bez niczego pomiędzy. Brakujące kroki ma śledzenie lokalnego przewoźnika. Po drugie podsumowanie na górze strony ma własne etykiety. `Alert` znaczy możliwy problem, a `Expired`, że paczka jest w drodze zbyt długo; żadna z nich nie oznacza nieudanego doręczenia. Nieudane doręczenie to `Undelivered`, a historia może podać powód, na przykład `Delivery Failed, Reason: Incorrect Address`.

Przy cle zwróć uwagę na `Customs Clearance Hold` i `Delivery failed, Reason: Refusal to pay VAT / customs duties`. Co wtedy zrobić, opisuje poradnik [paczka zatrzymana przez urząd celny](guide:customs).

## Kto doręcza paczki SunYou w Polsce

SunYou sam nie doręcza w Europie: jego linia komercyjna oddaje paczki lokalnym przewoźnikom w kraju docelowym, a śledzenie podaje nazwę tego przewoźnika i jego numer. W historiach odczytanych na stronie SunYou w Wielkiej Brytanii doręczał Royal Mail, w Niemczech [DHL Paket](carrier:dhl), w USA USPS albo GOFO, a w Kanadzie Canada Post.

Dla Polski SunYou nie podaje przewoźnika: nie wymienia jej wśród krajów swojej linii komercyjnej, a wśród logotypów partnerów nie ma ani Poczty Polskiej, ani InPostu. Kto doręcza Twoją paczkę, sprawdzisz więc tylko w jej historii, po `Departed Sunyou Facility`.

Liczby prób doręczenia, awizo, punktów odbioru ani czasu przechowania SunYou nie opisuje. To zasady przewoźnika, który doręcza, więc z jego nazwą i numerem sprawdź na jego stronie, jak zmienić termin albo gdzie czeka paczka.

Część paczek SunYou jedzie jako zwykła przesyłka pocztowa. Według SunYou da się ją śledzić tylko do momentu, gdy przejmie ją poczta kraju docelowego, więc historia może się wtedy urwać.

Śledzenie SunYou ma też link „Proof of Delivery”, który prosi o numer telefonu odbiorcy.

## Paczka SunYou nie dochodzi albo stoi

**Kto działa.** Klientem SunYou jest sprzedawca: zakłada konto, nadaje paczkę i ma umowę. Strony SunYou nie dają odbiorcom żadnej drogi reklamacji, a zasady odszkodowań SunYou dotyczą sprzedawców. Przy spóźnionej, zgubionej, uszkodzonej albo zwróconej paczce pisz więc do sklepu lub platformy, na której kupowałeś, na przykład AliExpress, Temu albo Allegro.

**Ile to trwa.** Sprzedawcom SunYou podaje „4-5 dni od przyjęcia do niektórych głównych krajów” na linii ogólnej i „najszybciej w 5 dni” do głównych krajów Europy i Ameryki na linii komercyjnej. To liczby sprzedażowe dla sklepów, nie termin Twojego zamówienia.

**Brak aktualizacji.** Jeśli historia pokazuje numer przewoźnika, najpierw sprawdź jego śledzenie. Więcej w poradniku [status przesyłki się nie zmienia](guide:tracking-not-updating), a o całej drodze paczki z Chin w [śledzeniu paczki z Chin](guide:tracking-from-china).

**Zgubiona albo zwrócona.** SunYou zapisuje to jako `Unfortunately, Your Parcel Is Lost`, `Returned` albo `Package Return, Reason: Incomplete Address Information`. Każda z tych linii to powód, by napisać do sprzedawcy.

## SunYou kontakt: telefon i e-mail

Stan na 12 października 2026 SunYou podaje te same kanały dla wszystkich krajów, bez biura ani numeru dla Polski:

- **Infolinia:** +86 4006075388, chiński numer. SunYou nie podaje godzin ani kosztu połączenia.
- **E-mail:** mto@sypost.com, skrzynka do spraw śledzenia.
- **Skargi:** tousu@sypost.com, na stronie kontaktowej po chińsku.

Strony kontaktowej po polsku nie ma. W sprawie zamówienia odpowie sklep, a w sprawie samego doręczenia przewoźnik z historii paczki.

## Śledzenie paczki SunYou w Peek

[Peek](/) od razu rozpoznaje oba kształty SY jako SunYou, bo żaden inny przewoźnik w jego katalogu ich nie używa. Możesz też wkleić link z sypost.net z numerem, na przykład ten z przycisku „Copy Link”.

Linie SunYou pojawiają się po angielsku, poza dokładnymi dopasowaniami, takimi jak `Delivered`, które aplikacja pokazuje własnymi słowami. Widzisz status, historię (do 20 skanów, bez miejsca przy każdym), kraj docelowy, godzinę doręczenia i lokalnego przewoźnika z jego numerem. Przewidywanej daty doręczenia ani punktu odbioru przy paczkach SunYou nie ma. Godziny skanów zachowują strefę czasową podaną przez SunYou: najpierw chińską, potem kraju docelowego.

Gdy SunYou podaje numer lokalnego przewoźnika, aplikacja może zapytać tego przewoźnika i przełączyć się na niego, gdy pokaże nowsze postępy. Gdy tym numerem jest numer pocztowy (dwie litery, 9 cyfr, dwie litery) paczki do Polski, pyta [Pocztę Polską](carrier:poczta-polska). Paczka zachowuje numer SunYou, a jej strona pokazuje „Doręcza:” z nazwą przewoźnika oraz jego numer z linkiem. Nie dzieje się tak przy każdej paczce.

Śledzenie jest sprawdzane w dzień co 10 minut, a co 2 minuty, gdy paczka jest w doręczeniu; w nocy co godzinę. Powiadomienia możesz dostawać o każdym skanie, o ważnych etapach albo „Tylko w dniu dostawy”. Peek nie skontaktuje się za Ciebie z SunYou ani ze sklepem.

## Pytania o SunYou tracking

### Czy SunYou tracking to fake?

Nie, SunYou to prawdziwa firma logistyczna, założona w 2008 roku w Shenzhen, a jej śledzenie działa na sypost.net. Jeśli Twój numer pokazuje tam `Not Found`, poproś sprzedawcę, by go sprawdził.

### Co to jest SunYou Post?

To SunYou Logistics, firma z Shenzhen, która wysyła małe paczki za granicę dla sprzedawców z Chin i oddaje je lokalnemu przewoźnikowi do doręczenia. Jako partnerów pokazuje między innymi AliExpress, Joom, Temu i Allegro.

### Co znaczy RM w numerze SunYou Post?

SunYou tego nie wyjaśnia. Numer `SYRM` to zwykły kształt SunYou, a litery nie oznaczają kraju: takie paczki jechały na przykład do Japonii i Bułgarii.

### Czy SunYou ma polski numer telefonu?

Nie, SunYou podaje tylko chińską infolinię +86 4006075388 i e-mail mto@sypost.com. W sprawie zamówienia szybciej pomoże sklep.

:::sources
- [SunYou: Sunyou Package Tracking](https://www.sypost.net/) – strona śledzenia, języki, przyjmowane numery, infolinia, e-mail, etykiety statusów, Proof of Delivery, numer przewoźnika, linie skanów i lokalni przewoźnicy
- [SunYou: 联系我们 (kontakt, po chińsku)](https://www.sypost.com/views/contact.html) – infolinia, e-mail, kontakt w sprawie skarg
- [SunYou: 顺邮宝专线 (linia ogólna, po chińsku)](https://www.sypost.com/views/services-1.html) – śledzenie zwykłych przesyłek, czasy, odszkodowania dla sprzedawców
- [SunYou: 顺速宝专线 (linia komercyjna, po chińsku)](https://www.sypost.com/views/services-2.html) – lokalni przewoźnicy, lista krajów, czasy
- [SunYou: 投寄说明 (zasady nadawania, po chińsku)](https://www.sypost.com/views/document-3.html) – sprzedawcy nadają paczki, śledzenie na sypost.net
- [SunYou: 顺友简介 (o firmie, po chińsku)](https://www.sypost.com/views/about.html) – rok 2008, Shenzhen
- [SunYou: partnerzy, platformy (po chińsku)](https://www.sypost.com/views/customer-1.html) – platformy sprzedażowe
- [SunYou: partnerzy, poczty i przewoźnicy (po chińsku)](https://www.sypost.com/views/customer-2.html) – poczty i przewoźnicy wśród partnerów
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – co Peek odczytuje i pokazuje, kształty numerów
:::
