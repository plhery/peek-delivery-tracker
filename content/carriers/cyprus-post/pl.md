---
title: Śledzenie Cyprus Post: numery, statusy i cło
description: Śledź przesyłkę Cyprus Post: 13-znakowy kod S10, znaczenie angielskich statusów UPU, opłaty celne UE spoza Unii i gdzie złożyć skargę.
slug: sledzenie-cyprus-post
published: 2026-11-22
updated: 2026-11-22
---

Cyprus Post (Κυπριακά Ταχυδρομεία), czyli Department of Postal Services, to poczta narodowa Republiki Cypryjskiej. Śledź przesyłkę na stronie Cyprus Post pod adresem cypruspost.post albo wklej numer powyżej. Nie masz żadnego numeru? Zobacz [jak znaleźć numer przesyłki](guide:find-tracking-number).

## Numer przesyłki Cyprus Post

Wyszukiwarka Cyprus Post obsługuje 13-znakowe numery standardu S10 Światowego Związku Pocztowego, nadawane przez poczty przesyłkom poleconym, EMS, paczkom i listom śledzonym: [dwie litery, dziewięć cyfr i dwie litery](guide:tracking-number-formats).

:::anatomy RR 482917367 CY
- RR | Wskaźnik usługi: pierwsza litera określa rodzaj przesyłki
- 482917367 | Ośmiocyfrowy numer seryjny i cyfra kontrolna
- CY | Kod kraju: CY oznacza numer wydany z upoważnienia Cypru
:::

| Pierwsza litera (wymyślone przykłady) | Co to jest |
| --- | --- |
| `RR482917367CY` | R: przesyłka polecona |
| `CP482917367CY` | C: paczka pocztowa |
| `EE482917367CY` | E: przesyłka EMS |
| `LA482917367CY` | L: list śledzony |
| `UA482917367CY` | U: przesyłka listowa z towarami, bez śledzenia dla odbiorcy |

Przesyłka nadana za granicą zachowuje numer nadany przez tamtejszą pocztę z jej kodem kraju (`…GB`, `…FR`, `…GR`), a strona Cyprus Post również go obsługuje wraz ze skanami poczty zagranicznej. `There is no information for this item` oznacza brak danych o tym numerze — sprawdź go u nadawcy.

## Co oznaczają statusy śledzenia Cyprus Post

W angielskiej wersji strony Cyprus Post, którą odczytuje Peek, każdy skan zawiera czas, kraj, lokalizację, zdarzenie, następną placówkę i dodatkowe informacje. Nazwy zdarzeń pochodzą z [międzynarodowego standardu pocztowego (UPU)](guide:tracking-statuses): `(Otb)` oznacza etap w kraju nadania, `(Inb)` w kraju przeznaczenia. Następna placówka wskazuje, dokąd przesyłka zmierza, a nie gdzie jest. Dla przesyłki do ciebie:

| Status Cyprus Post | Co oznacza dla ciebie |
| --- | --- |
| `Receive item from customer (Otb)` | Przesyłka została nadana na Cyprze lub za granicą. |
| `Insert item into bag (Otb)` | Zapakowana do worka w urzędzie wymiany na wywóz za granicę. |
| `Receive item at office of exchange (Inb)` | Dotarła do kraju docelowego. |
| `Send items to delivery point/ collection point` | W drodze do placówki, która ją doręczy lub wyda do odbioru. |
| `Receive item at delivery office (Inb)` | W placówce odpowiedzialnej za doręczenie. |
| `Hold item at point of delivery (Inb)` | W placówce doręczającej, czeka na wyjście w trasę. |
| `Handover item to delivery agent (Inb)` | Przekazana listonoszowi. |
| `Item not delivered/ Pending delivery` | Niedoręczona: powód podaje kolumna Extra Information. |
| `Receive item at collection point for pick-up (Inb)` | Gotowa do odbioru na poczcie. |
| `Deliver item (Inb)` | Doręczona albo odebrana w placówce pocztowej. |

## Opłaty celne za towary spoza UE

Od 1 lipca 2026 roku towary kupione online spoza UE — w tym przesyłki pocztowe — nie są już zwolnione z cła przy wartości przesyłki do 150 €: UE pobiera tymczasowe cło w wysokości 3 € od pozycji towarowej. Jedna pozycja obejmuje wszystkie towary w przesyłce o tej samej klasyfikacji taryfowej, opisie oraz — jeśli zadeklarowano — pochodzeniu, bez względu na liczbę sztuk. Od 1 listopada 2026 roku UE pobiera również unijną opłatę manipulacyjną w wysokości 2 € od pozycji dla zakupów online spoza UE niezależnie od wartości, a od tej opłaty należny jest VAT. Zapłata obciąża w pierwszej kolejności podmiot zgłaszający towar do odprawy: platformę, sprzedawcę, przewoźnika lub przedstawiciela. Na Cyprze Department of Customs and Excise pobiera cło przywozowe i VAT od towarów spoza UE. Więcej w [przesyłkach zatrzymanych na cle](guide:customs).

## Przesyłka Cyprus Post opóźniona lub nie dotarła

Jeśli śledzenie stanęło, sprawdź [gdy śledzenie się nie aktualizuje](guide:tracking-not-updating). Gdy [widnieje jako doręczona](guide:delivered-not-received), ale jej nie masz, zweryfikuj numer u nadawcy. W razie zaginięcia lub uszkodzenia zobacz [zagubiona paczka](guide:lost-parcel) i [uszkodzona przesyłka](guide:damaged-parcel).

**Zakup online wciąż nie dotarł?** Cypryjski Consumer Protection Service przypomina, że przedsiębiorca musi co do zasady dostarczyć towar w ciągu 30 dni, chyba że uzgodniono inny termin. Jeśli tego nie zrobi, wyznacz mu rozsądny dodatkowy termin; po jego bezskutecznym upływie możesz odstąpić od umowy. Infolinia konsumencka 1429 jest bezpłatna.

**Wiadomość prosi o dopłatę za zwolnienie paczki?** Przeczytaj [fałszywe SMS-y o przesyłkach](guide:delivery-scam-texts), zanim klikniesz jakikolwiek link.

## Śledzenie przesyłek Cyprus Post w Peek

Dla numeru pocztowego kończącego się na `CY` z poprawną cyfrą kontrolną [Peek](/) pyta jednocześnie Cyprus Post i [Chronopost](carrier:chronopost), pokazując „Cyprus Post ma tę przesyłkę.”, gdy Cyprus Post ją zna, albo „Jeszcze nie znaleziono u: …. Peek będzie sprawdzać dalej po dodaniu.”. Przy błędnej cyfrze kontrolnej Peek nie pyta Cyprus Post nawet po wybraniu jej przez „Zmień”: sprawdź numer u nadawcy. Link z cypruspost.post działa, gdy zawiera numer.

Dla przesyłki nadanej za granicą Peek pyta najpierw pocztę wystawiającą numer, jeśli to możliwe — np. [ELTA](carrier:elta) dla `…GR` — albo usługi śledzenia. Wyszukiwarka Cyprus Post również przyjmuje takie numery: wybierz „Cyprus Post” przyciskiem „Zmień”. Peek sprawdza Cyprus Post automatycznie, gdy poczta zagraniczna wskaże Cypr jako kraj docelowy albo — bez podanego kraju docelowego — gdy dodajesz numer z Cypru, i przełącza się, gdy Cyprus Post ma skany co najmniej tak świeże jak poczta zagraniczna. Peek pyta Cyprus Post tylko o numery 13-znakowe. Nie masz pewności co do przewoźnika? Wklej numer mimo to: Peek obsługuje ponad 4500 przewoźników.

Peek pokazuje statusy Cyprus Post w oryginale, placówkę i kraj każdego skanu na mapie, czas doręczenia oraz urząd pocztowy, w którym przesyłka czeka lub została odebrana. Cypryjskie skany zachowują dokładną godzinę, a zagraniczne odczytywane są według zegara swojego kraju (przy kilku strefach czasowych Peek ustala strefę po nazwie miejsca, co może powodować przesunięcie), całość wyświetlana jest w twojej strefie czasowej („Godziny w twojej strefie czasowej:”). Brak przewidywanego terminu i wagi, a Extra Information, następna placówka, kody pocztowe i nazwisko odbierającego są pomijane — powód niedoręczenia sprawdzisz na cypruspost.post.

Peek odpytuje Cyprus Post bezpośrednio nawet co 10 minut, a w dniu, gdy przesyłka wychodzi z listonoszem — co 2 minuty od 6:00 według zegara ostatniego skanu (czasu cypryjskiego na Cyprze), gdy przesyłka jest otwarta w Peek albo w dniu dostawy z włączonymi powiadomieniami. Po dwóch dniach bez zmian (po 12 godzinach przy statusie „Zarejestrowana” lub „Przyjęta przez przewoźnika”) Peek sprawdza co godzinę. Dla powiadomień możesz wybrać „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Po czterech dniach bez skanu przy statusie „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie” Peek wyświetla „Brak aktualizacji od…”, przypomina, że „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika Cyprus Post.” oraz podsuwa „Poproś przewoźnika Cyprus Post o pomoc”.

## Najczęstsze pytania o Cyprus Post

### Czy Cyprus Post to Department of Postal Services?

Tak. Światowy Związek Pocztowy wymienia Department of Postal Services (Cyprus Post) jako wyznaczonego operatora Cypru, państwowy urząd odpowiedzialny za usługi pocztowe, a regulator OCECPR wskazuje Cyprus Post jako operatora usługi powszechnej.

### Co oznacza CY na końcu numeru?

Że numer został wydany z upoważnienia Cypru. W standardzie S10 UPU dwie ostatnie litery oznaczają kraj, pod którego zwierzchnictwem wydano kod, więc nie przesądzają o miejscu nadania. Peek pyta przy takich numerach Cyprus Post.

### Czy mogę śledzić przesyłkę z numerem na literę U?

Nie jako przesyłkę śledzoną. Standard S10 nadaje numery U przesyłkom listowym z towarami podróżującym bez śledzenia dla odbiorcy: numer służy widoczności w łańcuchu dostaw, np. w danych elektronicznych przekazywanych z wyprzedzeniem. Peek mimo to pyta Cyprus Post o numer U zakończony na `CY`.

### Jak złożyć skargę na Cyprus Post?

Najpierw napisz do Cyprus Post. Jeśli nie odpowie w ciągu 15 dni lub odpowiedź cię nie satysfakcjonuje, złóż skargę do regulatora pocztowego, Office of the Commissioner for Electronic Communications and Postal Regulation (OCECPR). Możesz zrobić to online; strona jest po grecku, ale udostępnia formularz skargi po angielsku.

:::sources
- [Universal Postal Union: S10, identification of postal items](https://www.upu.int/UPU/media/upu/files/postalSolutions/programmesAndServices/standards/S10-12.pdf) – 13 znaków, pierwsze litery R, C, E, L i U, cyfra kontrolna, kod kraju wydania
- [Universal Postal Union: member countries, Cyprus](https://www.upu.int/en/Universal-Postal-Union/About-UPU/Member-Countries?csid=-1&cid=86) – Cyprus Post, wyznaczony operator
- [Universal Postal Union: list of designated operators](https://www.upu.int/getmedia/0ff98b1d-9dd4-46e7-bdd1-dda1b654308f/SettlementCircularsEn.pdf) – Department of Postal Services (Cyprus Post)
- [National Open Data Portal: list of post offices](https://www.data.gov.cy/el/dataset/katalogos-kai-theseis-tahydromikon-grafeion) – Department of Postal Services, urząd państwowy ds. poczty (po grecku)
- [OCECPR: universal postal service](https://ocecpr.ee.cy/katanalotes/katholiki-ypiresia/taxidromeia) – Cyprus Post jako operator usługi powszechnej (po grecku)
- [OCECPR: complaints](https://ocecpr.ee.cy/katanalotes/parapona) – najpierw pisemnie do operatora, 15 dni, skarga online, formularz angielski (po grecku)
- [Consumer Protection Service: online purchases](https://consumer.gov.cy/en/consumer-rights/66/59/?ctype=ar) – dostawa w 30 dni, termin dodatkowy, odstąpienie od umowy (po grecku)
- [Consumer Protection Service: contact us](https://consumer.gov.cy/en/contact-us) – bezpłatna infolinia konsumencka 1429 (po grecku)
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – cło 3 € za pozycję od 1 lipca 2026 r., unijna opłata manipulacyjna 2 € od 1 listopada 2026 r.
- [European Commission: guidance on the EUR 3 customs duty and the Union handling fee](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – definicja pozycji, przesyłki pocztowe, opłata niezależna od wartości, podmiot zobowiązany, VAT od opłaty
- [Department of Customs and Excise: About us](https://www.mof.gov.cy/mof/customs/customs.nsf/All/289500205CFF33DFC22572AC003A2B02?OpenDocument) – nakładanie cła przywozowego i VAT
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – dane odczytywane przez Peek: angielskie śledzenie Cyprus Post, kolumny i nazwy zdarzeń
:::
