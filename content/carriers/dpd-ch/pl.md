---
title: DPD Szwajcaria śledzenie: status paczki, Pickup i kontakt
description: Śledź przesyłkę DPD Szwajcaria (DPD Switzerland): 14-cyfrowy numer od 060, statusy w myDPD, punkty DPD Pickup w soboty, odprawa celna i telefon.
slug: dpd-szwajcaria-sledzenie
published: 2026-11-17
updated: 2026-11-17
---

DPD Szwajcaria to spółka **DPD (Schweiz) AG** z siedzibą w Buchs ZH, należąca do sieci Geopost i dysponująca ponad 1000 punktów **DPD Pickup Location** na terenie Szwajcarii. Swoją paczkę sprawdzisz, wpisując 14-cyfrowy numer przesyłki w portalu [myDPD](https://www.dpdgroup.com/ch/mydpd/?lang=de) albo wklejając go w polu powyżej. Numer przychodzi zazwyczaj e-mailem ze sklepu, a po nieudanej próbie doręczenia znajdziesz go również na awizie wrzuconym do skrzynki pocztowej.

## Numer śledzenia DPD Szwajcaria: gdzie go znaleźć i jak wygląda

DPD nazywa go „numerem paczki” (*parcel number* / *Paketnummer* / *numéro de colis*). Najczęściej otrzymujesz go w wiadomości od nadawcy lub od DPD; jeśli kurier nie zastał Cię w domu, numer jest wydrukowany także na karcie awiza, której kod kreskowy potrafi zeskanować aplikacja myDPD. Brak wiadomości? Sprawdź poradnik [jak znaleźć numer śledzenia](guide:find-tracking-number).

| Numer (przykładowy) | Co oznacza |
| --- | --- |
| `06090000000000` | 14-cyfrowy numer paczki. W formularzach szkodowych dla nadawców DPD Szwajcaria podaje, że szwajcarskie numery zaczynają się od `060` (Peek rozpoznaje także szwajcarski zakres oddziałów `061`). |
| `06090000000000U` | Ten sam numer z 15. znakiem kontrolnym na końcu (cyfrą lub wielką literą), akceptowany przez Peek, gdy znak kontrolny się zgadza. |

Kod zaczynający się od `P00` na awizie **nie** jest numerem paczki — to numer powiadomienia (*notification number*), służący do zamówienia ponownego doręczenia. Jeżeli myDPD niczego nie znajduje, paczka mogła jeszcze nie trafić do DPD, numer może mieć ponad **31 dni** (został już zarchiwizowany) albo zawiera literówkę. Ciągi 14 cyfr występują również w [DPD Polska](carrier:dpd-pl) (np. od `13`, podczas gdy krajowe polskie numery z 13 cyfr i litery na końcu należą wyłącznie do DPD Polska), a także w DPD Niemcy, DPD Francja, DPD Wielka Brytania, SEUR czy BRT — upewnij się w e-mailu ze sklepu, który oddział obsługuje przesyłkę, i zobacz [formaty numerów śledzenia](guide:tracking-number-formats).

## Co oznaczają statusy śledzenia DPD Szwajcaria

Szwajcarski portal myDPD działa po angielsku, niemiecku, francusku i włosku (nie ma wersji polskiej). Poniżej znajdziesz angielskie komunikaty z myDPD — te same, które wyświetla Peek — w typowej kolejności na trasie przesyłki:

| Status w DPD Szwajcaria | Co oznacza dla Ciebie |
| --- | --- |
| `Order created` | Utworzono etykietę: DPD nie ma jeszcze fizycznie paczki. |
| `Parcel handed to DPD` | DPD odebrało przesyłkę od nadawcy. |
| `Your parcel is on its way`, `In transit` | Paczka przemieszcza się między sortowniami DPD; nie wyjechała jeszcze do doręczenia. |
| `At delivery center`, `At delivery centre` | Dotarła do oddziału doręczającego w Twoim regionie. |
| `Parcel out for delivery` | Jest w samochodzie kuriera z planowanym doręczeniem dzisiaj. |
| `Unsuccessful delivery attempt` | Nie udało się doręczyć — poszukaj awiza w skrzynce na listy. |
| `Ready for collection`, `Available for collection` | Gotowa do odbioru w punkcie DPD Pickup Location. |
| `Delivered`, `Your parcel has been delivered successfully` | Doręczona (pojedynczy wpis `Delivered` Peek wyświetla po polsku jako „Dostarczona”). |
| `Return to sender`, `Return to shipper` | Przesyłka wraca do nadawcy. |
| `Delivered (Returned to Sender)` | Zwrócona i wydana z powrotem nadawcy — nie Tobie. |

Jeśli przeglądasz myDPD po francusku, uważaj na dwa zwodnicze sformułowania: `En cours de livraison` oznacza zwykły transport między magazynami (dopiero `Colis en cours de livraison` to wyjazd kuriera na rejon), a `Disponible pour enlèvement` informuje, że paczka czeka na Ciebie w punkcie DPD Pickup. Więcej wyjaśnień w poradniku [statusy śledzenia przesyłek](guide:tracking-statuses).

W sekcji ochrony danych („Data protection”) myDPD prosi o kod pocztowy odbiorcy albo pozwala przejść dalej bez weryfikacji („Continue without verification”): bez kodu widać tylko po jednym skróconym wpisie na etap; po podaniu kodu odblokowują się szczegółowe skany z sortowni (`Your parcel arrived at our hub`), komunikaty doręczeniowe (`Your parcel will be delivered to a safe place according to your instructions`) oraz okno godzinowe.

## Dostawa DPD w Szwajcarii: Predict, punkty Pickup, cło i awizo

**Predict.** Jeśli nadawca wykupi usługę Predict, DPD przysyła SMS-em lub e-mailem dzień dostawy, a rano w dniu doręczenia **1-godzinne okno czasowe**; na mapie możesz śledzić podjazd kuriera na żywo i zmienić dyspozycję doręczenia do **15 minut przed** przekazaniem paczki.

**Zmiany i zgoda na pozostawienie paczki.** W myDPD możesz przełożyć datę dostawy, przekierować paczkę do pracy, do sąsiada lub do punktu DPD Pickup Location albo wyrazić zgodę na zostawienie przesyłki bez podpisu w „bezpiecznym miejscu” (*Safe place* / *Abstellgenehmigung*) — od 12 października 2026 r. wymaga to zweryfikowanego numeru telefonu komórkowego. Gdy nadawca zastrzegł podpis, a brak aktywnej zgody na pozostawienie, kurier nie zostawi paczki pod drzwiami.

**Nieobecność w domu.** Kurier zostawia kartę awiza; zobacz też [nieudana próba doręczenia](guide:missed-delivery). W zależności od usługi nadawcy DPD może ponowić próbę (maksymalnie **trzy razy**, według poradnika DPD Szwajcaria), zostawić przesyłkę u sąsiada w promieniu **50 metrów**, przekazać ją do punktu DPD Pickup Location albo odesłać nadawcy. Nowy termin doręczenia zamówisz na [stronie ponownej dostawy myDPD](https://www.dpdgroup.com/ch/mydpd/fmp/login), wpisując kod z SMS-a lub numer `P00` z awiza.

**Punkty DPD Pickup Location.** Ponad 1000 sklepów partnerskich w Szwajcarii, z których wiele działa wieczorami oraz w **soboty** — godziny otwarcia sprawdzisz w [wyszukiwarce punktów DPD Pickup](https://www.dpd.com/ch/en/dpd-pickup-location/). Od powiadomienia e-mailem lub SMS-em masz **7 dni kalendarzowych** (wliczając weekend) na odbiór, zanim paczka wróci do nadawcy; sprawdź też [nieodebrana przesyłka](guide:uncollected-parcel). Zabierz urzędowy dokument ze zdjęciem; osoba odbierająca w Twoim imieniu musi okazać własny dokument ze zdjęciem oraz Twoje pełnomocnictwo cyfrowe lub podpisany odwrót awiza.

**Odprawa celna w Szwajcarii.** Szwajcaria nie należy do unii celnej UE: przy paczkach wysyłanych z Polski lub innych krajów do Szwajcarii Federalny Urząd Celny i Bezpieczeństwa Granic (BAZG) wskazuje, że operatorzy pocztowi i kurierzy zgłaszają każdą przesyłkę do odprawy. Szwajcarski podatek VAT wynosi **8,1 %** (**2,6 %** stawki obniżonej, np. na książki i żywność), należności celnych lub podatku VAT do kwoty **5 CHF** nie pobiera się, natomiast przewoźnik może doliczyć opłatę za obsługę celną. Więcej w artykule [paczka w urzędzie celnym](guide:customs).

## Paczka DPD Szwajcaria stoi w miejscu, nie dotarła lub jest uszkodzona

Umowę przewozu zawiera z DPD nadawca, dlatego w razie uszkodzenia, zaginięcia lub braków w zawartości DPD Szwajcaria kieruje odbiorcę do sklepu lub nadawcy, który zgłasza sprawę do działu likwidacji szkód DPD:

- **Brak nowych skanów od kilku dni:** skorzystaj z formularza kontaktowego DPD, podając 14-cyfrowy numer paczki; wcześniej przeczytaj [śledzenie nie aktualizuje się](guide:tracking-not-updating).
- **Status doręczono, ale paczki nie ma:** poproś sprzedawcę o wyjaśnienie doręczenia z DPD; pomocne kroki opisuje poradnik [dostarczona, ale nieotrzymana](guide:delivered-not-received).
- **Uszkodzona przesyłka:** jeśli uszkodzenie widać już przy odbiorze, możesz odmówić przyjęcia paczki, a kurier zabierze ją z powrotem. Gdy paczkę już odebrałeś, powiadom nadawcę (najlepiej tego samego dnia): według wytycznych DPD Szwajcaria nadawca ma **7 dni kalendarzowych** od doręczenia na zgłoszenie szkody, a zdjęcia, opakowanie i zawartość należy zachować do **3 miesięcy**. Zobacz [uszkodzona paczka](guide:damaged-parcel) oraz [zagubiona paczka](guide:lost-parcel).
- **Spory:** po wyczerpaniu drogi reklamacyjnej u operatora w Szwajcarii można zwrócić się do organu pojednawczego **Ombud-PostCom** (opłata manipulacyjna `CHF 20.–`).

## Kontakt z DPD Szwajcaria: infolinia i formularz

Dane kontaktowe ze szwajcarskich stron DPD (stan na listopad 2026 r., dla całego kraju):

- **Telefon:** `058 311 13 13` (z zagranicy: `+41 58 311 13 13`), od poniedziałku do piątku w godz. `08:00–18:00`, maksymalnie `CHF 0.08`/min w Szwajcarii. W dniach 24 i 31 grudnia 2026 r.: `08:00–12:00`.
- **Formularz online:** przycisk „Contact DPD” na [stronie wsparcia DPD Szwajcaria](https://www.dpd.com/ch/en/support-centre/your-request/).
- **Adres e-mail:** brak skrzynki e-mail dla odbiorców paczek (nota prawna *Impressum* odsyła wyłącznie do formularza kontaktowego).

> DPD Szwajcaria ostrzega przed oszustwami przez SMS, WhatsApp i e-mail (np. wiadomościami z numerów zaczynających się od `+63` lub `+212` albo żądaniami dopłaty do kosztów transportu). Nie klikaj linków. Jako prawdziwe adresy nadawcze DPD wymienia `no-replyinvoicing@dpd.ch`, `import@dpd.ch`, `export@dpd.ch` oraz `faktura@dpd.ch`, a fałszywe strony zaleca zgłaszać na `antiphishing.ch`. Więcej w poradniku [fałszywe SMS-y o paczkach](guide:delivery-scam-texts).

## Śledzenie paczek DPD Szwajcaria w aplikacji Peek

[Peek](/) przyjmuje 14 cyfr zarówno bez 15. znaku kontrolnego, jak i z nim. Dla 14-cyfrowych numerów zaczynających się od `060` lub `061` — oraz neutralnych 15-znakowych numerów DPD z poprawnym znakiem kontrolnym — odpytuje w pierwszej kolejności **DPD Switzerland**, wyświetlając „Sprawdzanie usług śledzenia…”. Przy neutralnych 14-cyfrowych numerach bez szwajcarskiego prefiksu w Szwajcarii pyta najpierw **DPD Switzerland**, natomiast w Polsce najpierw DPD Belgium, a następnie **DPD Switzerland**, jeśli DPD Belgium nie zna przesyłki. Przy numerach z polskim prefiksem `13` (14 cyfr lub 15 znaków z poprawnym znakiem kontrolnym) Peek odpytuje najpierw [DPD Polska](carrier:dpd-pl) (po [FedEx](carrier:fedex), gdy wszystkie 15 znaków to cyfry), a w dalszej kolejności **DPD Switzerland**, o ile wcześniejsi przewoźnicy nie potwierdzą paczki — natomiast 14-znakowe numery krajowe złożone z 13 cyfr i litery na końcu należą wyłącznie do [DPD Polska](carrier:dpd-pl). DPD Switzerland potwierdza wyłącznie paczki znajdujące się w Szwajcarii („DPD Switzerland ma tę przesyłkę.”); gdy 14-cyfrowy numer `060` lub `061` nie figuruje jeszcze w bazie, zobaczysz „Oczekiwanie na pierwszy skan przewoźnika DPD Switzerland”. Dla 14 cyfr zaczynających się od `10` (DPD France) Peek nie odpytuje DPD Switzerland — wybierz ją ręcznie przyciskiem „Zmień” lub „Zmień przewoźnika”. Działają również linki z `dpd.com` lub `dpdgroup.com` z parametrem `parcelNumber=` (`/ch/` wybiera **DPD Switzerland**, a `/pl/` otwiera [DPD Polska](carrier:dpd-pl)).

Opcjonalne pole „Kod pocztowy odbiorcy” przyjmuje format dowolnego kraju i trafia do DPD oraz — gdy trzeba — do serwisów śledzenia. Bez kodu zobaczysz po jednym wpisie na etap i przewidywaną datę doręczenia; z kodem dochodzą skany na mapie z miejscowością i krajem, komunikaty DPD, okno godzinowe (w czasie szwajcarskim), nazwa nadawcy, waga oraz pełny adres punktu Pickup (bez kodu pocztowego widoczna jest zazwyczaj tylko nazwa sklepu, o ile DPD ją podaje). Gdy DPD odrzuci kod pocztowy, Peek pokaże „Nie znaleziono z podanym kodem pocztowym” i przycisk „Zmień kod pocztowy”.

Statusy pozostają po angielsku (poza pojedynczym `Delivered`, pokazywanym jako „Dostarczona”), a godziny są przeliczane na Twoją strefę czasową („Godziny w Twojej strefie czasowej: …” na peektracker.com i w aplikacji na iPhone’a); imię i nazwisko, adres domowy oraz link do potwierdzenia odbioru są pomijane. Peek sprawdza przesyłkę co 10 minut, a w dniu doręczenia co 2 minuty od 6:00 czasu lokalnego lub na pół godziny przed otwarciem okna czasowego (co 5 minut, gdy musi otworzyć stronę śledzenia DPD w przeglądarce; co 15 minut po błędzie lub przez serwisy śledzenia), z powiadomieniami „Wszystkie aktualizacje”, „Tylko ważne” lub „Tylko w dniu dostawy”. Po dwóch dniach bez zmian — albo po 12 godzinach na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika” — przechodzi na sprawdzanie co godzinę; jeśli po dwóch dniach żaden przewoźnik nie rozpozna numeru, wyświetla komunikat: „Po dwóch dniach przewoźnik nadal nie zna tego numeru. Sprawdź go, wybierz innego przewoźnika lub usuń przesyłkę.” Paczka nie jest przekazywana do innego przewoźnika.

## Najczęstsze pytania o śledzenie DPD Szwajcaria

### Ile dni paczka czeka w punkcie DPD Pickup w Szwajcarii?

Siedem dni kalendarzowych (łącznie z weekendem) od powiadomienia e-mailem lub SMS-em, zgodnie z pomocą myDPD; po upływie tego terminu przesyłka wraca do nadawcy.

### Czy DPD Szwajcaria doręcza paczki w soboty?

Dla doręczeń pod drzwi DPD nie publikuje ogólnej zasady, ale wiele punktów DPD Pickup Location jest otwartych w soboty zgodnie z godzinami pracy sklepu, a harmonogram świąteczny DPD Szwajcaria przewiduje doręczenia w sobotę 28 listopada 2026 r.

### Ile idzie paczka DPD w Szwajcarii?

Standardowo **1 dzień** od rozpoczęcia realizacji zamówienia w ruchu krajowym na terenie Szwajcarii (według pomocy myDPD); przy przesyłkach międzynarodowych czas zależy od kraju nadania, a ogólne warunki handlowe zaznaczają, że czasy przewozu są orientacyjne.

### Jak zwrócić paczkę przez DPD w Szwajcarii?

Zanieś paczkę do punktu DPD Pickup Location z naklejoną wcześniej etykietą zwrotną od nadawcy — bez gotowej etykiety zwrotnej punkt Pickup nie przyjmie przesyłki.

:::sources
- [DPD Switzerland: Parcel tracking (angielski)](https://www.dpd.com/ch/en/mydpd/) – usługa Predict, zmiana doręczenia i punkty Pickup w soboty
- [DPD Switzerland: Contact us (angielski)](https://www.dpd.com/ch/en/support-centre/your-request/) – infolinia 058 311 13 13, formularz, numer P00 i zwroty z etykietą
- [DPD Switzerland: DPD Pickup Location (angielski)](https://www.dpd.com/ch/en/dpd-pickup-location/) – ponad 1000 punktów, dokument ze zdjęciem i pełnomocnictwo
- [DPD Switzerland: DPD liability for recipients (angielski)](https://www.dpd.com/ch/en/support-centre/liability-overview/recipient/) – zgłaszanie roszczeń przez nadawcę
- [DPD Switzerland: DPD liability for senders (angielski)](https://www.dpd.com/ch/en/support-centre/liability-overview/shipper/) – szwajcarskie numery paczek zaczynające się od 060
- [DPD Switzerland: Parcel service Switzerland (angielski)](https://www.dpd.com/ch/en/) – siedziba w Buchs ZH, sieć Geopost i kalendarz świąteczny 2026
- [DPD Switzerland: IT Security, Fraud (angielski)](https://www.dpd.com/ch/en/security-at-dpd/) – oszustwa +63/+212, prawdziwe adresy @dpd.ch i antiphishing.ch
- [DPD Switzerland: Parcel not delivered, what to do? (angielski)](https://www.dpd.com/ch/en/news/parcel-not-delivered-what-to-do/) – do trzech prób doręczenia i wymóg podpisu
- [myDPD: Help (angielski)](https://www.dpdgroup.com/ch/mydpd/api/help?lang=en) – 7 dni kalendarzowych na odbiór i 1-dniowy czas doręczenia krajowego
- [myDPD: English translations (angielski)](https://www.dpdgroup.com/ch/mydpd/api/translations/DPD-CH/en) – 14 cyfr, statusy po angielsku, kod pocztowy i archiwizacja po 31 dniach
- [myDPD: Tracking (niemiecki)](https://www.dpdgroup.com/ch/mydpd/?lang=de) – weryfikacja numeru telefonu przy zgodzie na pozostawienie paczki
- [DPD Schweiz: Paket kaputt angekommen (niemiecki)](https://www.dpd.com/ch/de/news/paket-kaputt-angekommen-was-tun/) – widoczne uszkodzenia, 7 dni kalendarzowych i przechowywanie dowodów przez 3 miesiące
- [DPD Schweiz: AGB Business, PDF (niemiecki)](https://www.dpd.com/wp-content/uploads/sites/223/2022/04/202204-AGB-Business-2022-DE.pdf) – doręczenie sąsiadowi do 50 m i orientacyjne czasy przewozu
- [DPD Schweiz: Impressum (niemiecki)](https://www.dpd.com/ch/de/impressum/) – brak adresu e-mail dla zapytań o paczki
- [DPD Schweiz: Ombud-PostCom (niemiecki)](https://www.dpd.com/ch/de/schlichtungsstelle-ombud-postcom/) – postępowanie pojednawcze
- [Ombud-PostCom: Vorgehen (niemiecki)](https://www.ombud-postcom.ch/vorgehen-1) – opłata CHF 20.–
- [BAZG: Postversand und Kurierverkehr (niemiecki)](https://www.bazg.admin.ch/de/postversand-interneteinkauf-kurierverkehr-einfuhr-firmen) – szwajcarski VAT 8,1 % / 2,6 % i zwolnienie z poboru do 5 CHF
- [BAZG: Verzollungskosten (niemiecki)](https://www.bazg.admin.ch/de/verzollungskosten-und-paketverfolgung) – opłaty przewoźnika za odprawę celną
- [Universal Parcel Scraper: DPD Switzerland (angielski)](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dpd) – rozpoznawanie numerów, kolejność zapytań i zakres danych w Peek
:::
