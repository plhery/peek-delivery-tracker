---
title: UPS tracking: śledzenie paczki w Polsce, statusy, reklamacje
description: Śledzenie paczki UPS: jak wygląda numer 1Z, co znaczą Exception i inne skany, punkty Access Point, cło od paczek spoza UE i reklamacja w UPS Polska.
slug: ups-tracking-sledzenie-paczki
published: 2026-10-13
updated: 2026-10-27
---

Numery przesyłek UPS (United Parcel Service) zwykle zaczynają się od `1Z`. Wklej numer albo link do śledzenia UPS w pole powyżej, żeby śledzić paczkę; własne śledzenie UPS znajdziesz na ups.com.

## UPS tracking number: gdzie jest numer przesyłki i jak wygląda

Numer dostajesz od sklepu, który wysyła paczkę, zwykle w e-mailu o wysyłce albo na stronie zamówienia. Link do śledzenia z ups.com też go zawiera, po `tracknum=`. Nadal nic? Zobacz, [gdzie jeszcze szukać numeru przesyłki](guide:find-tracking-number).

| Numer (wymyślony) | Co to jest |
| --- | --- |
| `1Z1234567812345674` | Numer paczki UPS: `1Z` i 16 liter i cyfr, razem 18 znaków. Ostatni znak to cyfra kontrolna wyliczana z 15 znaków przed nią. |
| `H1234567890` | Litera (H, J, K lub V) i 10 cyfr. To może być numer UPS, ale taki sam kształt mają numery innych przewoźników. |

## Statusy UPS: co znaczy „Exception” i inne

Angielska strona śledzenia UPS ma pasek postępu (`Label Created`, `We Have Your Package`, `On the Way`, `Out for Delivery`, `Delivered`), a pod nim skany. Peek czyta te skany po angielsku i pokazuje nad nimi status po polsku. Oto najważniejsze, mniej więcej w kolejności, w jakiej spotyka je paczka:

| UPS pisze | Peek pokazuje | Co to znaczy dla Ciebie |
| --- | --- | --- |
| `Shipper created a label, UPS has not received the package yet.` | Zarejestrowana | Nadawca utworzył etykietę. UPS nie ma jeszcze paczki. |
| `Drop-Off`, `Pickup Scan` | Przyjęta przez przewoźnika | UPS ma paczkę: nadawca nadał ją w punkcie Access Point albo UPS ją od niego odebrał. Pierwszy skan `Arrived at Facility` też się tu liczy. |
| `Arrived at Facility`, `Departed from Facility`, `Processing at UPS Facility` | W transporcie | Paczka jedzie przez sieć UPS. `Export Scan` i `Import Scan` oznaczają przekroczenie granicy. |
| `Your package is prepared for clearance.` | W urzędzie celnym | Trwa odprawa celna. |
| `Duties or taxes are due on this package.` | W urzędzie celnym | Musisz zapłacić należności przywozowe, zanim UPS doręczy paczkę (więcej niżej). |
| `Loaded on Delivery Vehicle`, `Out For Delivery Today` | W doręczeniu | Paczkę wiezie kurier. |
| `The receiver was not available at the time of the final delivery attempt.` | Próba doręczenia | Ostatnia próba doręczenia też się nie udała. |
| `Delivered to UPS Access Point™`, `UPS Access Point™ possession` | Do odbioru | Paczka czeka na Ciebie w punkcie UPS Access Point. |
| `DELIVERED` | Dostarczona | Doręczona albo odebrana przez Ciebie w punkcie Access Point. |
| `Return to Sender` | Zwrócona nadawcy | Paczka wraca do nadawcy. |

`Exception` to ogólny status UPS i może oznaczać zwykłe opóźnienie. Liczy się skan pod nim: `We're sorry this package may experience a temporary delay.` oznacza właśnie opóźnienie, a `The company or receiver name is incorrect. This will delay delivery.` – że doręczenie czeka na Twoje dane (Peek pokazuje wtedy „Zgłoszono problem”). Peek kieruje się najnowszym skanem, nie ogólnym statusem.

## Kurier UPS Cię nie zastał i punkty Access Point

Gdy kurier nie może doręczyć paczki, skan mówi, że odbiorcy nie było albo firma była zamknięta, a UPS próbuje ponownie. Po ostatniej próbie kolejny skan pokazuje, co dalej: UPS przechowuje paczkę, zawozi ją do punktu Access Point albo odsyła nadawcy. Więcej w poradniku [awizo i nieudana próba doręczenia](guide:missed-delivery).

UPS może też zawieźć paczkę do punktu UPS Access Point. Wtedy skany brzmią `Delivered to UPS Access Point™`, a potem `UPS Access Point™ possession`, a Peek pokazuje nazwę i adres punktu. Nie zwlekaj z odbiorem: gdy nieodebrana paczka wraca, skan brzmi `The UPS Access Point™ location has prepared the package for return to UPS or pickup by UPS.` Gdy punkt Access Point się zamyka, UPS zabiera z niego paczki, a skan informuje, że paczka czeka na doręczenie do innego punktu. Zobacz też [nieodebrana paczka](guide:uncollected-parcel).

## Cło za paczkę UPS spoza UE

Jeśli w śledzeniu pojawi się `Duties or taxes are due on this package.`, UPS doręczy paczkę dopiero, gdy zapłacisz należności przywozowe; zaraz potem zwykle widać `Your package is being held until duties and taxes are paid. Awaiting customer action.`

Według Krajowej Administracji Skarbowej od 1 lipca 2026 r. zakupy internetowe spoza UE nie korzystają już ze zwolnienia z cła dla przesyłek do 150 EUR. Zamiast niego obowiązuje ryczałtowe cło 3 EUR za każdą pozycję towarową w zgłoszeniu celnym: dwie bawełniane koszulki to jedna pozycja, koszulka i etui na telefon – dwie. Tego cła nie płaci konsument, tylko sprzedawca, platforma internetowa lub ich przedstawiciel, ale sprzedawca może je doliczyć do ceny lub kosztów dostawy. Po zwrocie towaru cło i VAT nie są zwracane. Więcej w poradniku [paczka zatrzymana przez urząd celny](guide:customs).

## Paczka UPS opóźniona, zaginiona lub uszkodzona

Jeśli status stoi w miejscu, zajrzyj do poradnika [status przesyłki się nie zmienia](guide:tracking-not-updating).

Przy zakupach w sklepie to sprzedawca odpowiada za to, żeby paczka do Ciebie dotarła, nawet gdy wiezie ją UPS. Według unijnego portalu Your Europe sprzedawca odpowiada za uszkodzenia towaru od wysyłki aż do chwili, gdy go otrzymasz. Jeśli nie umówiliście się inaczej, musi dostarczyć towar w ciągu 30 dni; gdy go nie ma, przypomnij sprzedawcy o zamówieniu i wyznacz mu dodatkowy, rozsądny termin. Jeśli i tego nie dotrzyma, możesz rozwiązać umowę i odzyskać pieniądze.

UPS Polska Sp. z o.o. jest wpisana do rejestru operatorów pocztowych, który prowadzi Prezes UKE. O reklamacjach usług pocztowych UKE pisze tak: reklamację może złożyć nadawca albo adresat – gdy przesyłka zostanie mu doręczona albo gdy nadawca zrzeknie się na jego rzecz prawa dochodzenia roszczeń. Można to zrobić pisemnie, elektronicznie (np. przez formularz na stronie operatora) lub ustnie, w ciągu 12 miesięcy od dnia nadania. Operator ma 30 dni na odpowiedź; jeśli nie odpowie w terminie, reklamacja zostaje uznana. Od odmowy możesz się odwołać w ciągu 14 dni. Gdy odpowiedź Cię nie zadowala, zostaje interwencja Prezesa UKE, bezpłatne pozasądowe rozwiązywanie sporów konsumenckich albo sąd.

**Paczka przyszła uszkodzona?** Według UKE podstawą reklamacji jest protokół spisany w obecności pracownika operatora, który ją doręczył. Uszkodzenia, których nie było widać z zewnątrz przy odbiorze, trzeba zgłosić operatorowi najpóźniej 7 dni po otrzymaniu przesyłki. Więcej w poradniku [uszkodzona paczka](guide:damaged-parcel).

**Status `DELIVERED`, a paczki nie ma?** Strona UPS może pokazać, gdzie zostawiono paczkę (np. `Left at: Front Door`), i potwierdzenie doręczenia; Peek tego nie zapisuje. Sprawdź to na ups.com, a potem skontaktuj się ze sprzedawcą. Więcej w poradnikach [paczka doręczona, a jej nie ma](guide:delivered-not-received) i [zaginiona paczka](guide:lost-parcel).

## Śledzenie paczki UPS w Peek

[Peek](/) od razu rozpoznaje UPS po numerze `1Z`, którego ostatnia cyfra przechodzi kontrolę UPS. Jeśli nie przechodzi, proponuje tylko UPS, a UPS odrzuca numer jako błędnie wpisany. W samym UPS sprawdza tylko numery `1Z`. Inne kształty, takie jak `H1234567890`, dostają więc odpowiedź tylko od serwisów śledzenia przesyłek, także po „Zmień przewoźnika”. Możesz wkleić link z ups.com z numerem, a także każdy link, w którym jest numer `1Z`.

Serwer Peek otwiera w dzień stronę śledzenia ups.com w przeglądarce co 10 minut, a co 5 minut, gdy paczka jest w doręczeniu; w nocy co godzinę lub dwie. Gdy UPS odmówi albo da się odczytać tylko status, historię uzupełniają serwisy śledzenia. Widzisz status, każdy skan z miejscem i dokładną godziną na mapie, planowany dzień doręczenia (nigdy przedział godzin), godzinę doręczenia, kraj docelowy i, dopóki paczka czeka, nazwę i adres punktu Access Point. Powiadomienia możesz dostawać o każdym skanie, tylko o ważnych etapach albo tylko w dniu dostawy. Nie zobaczysz tam, kto podpisał, ani Twojego adresu, potwierdzenia doręczenia, miejsca pozostawienia czy zdjęcia. Wagi ani nadawcy UPS nie przekazuje.

Skany są w każdym języku po angielsku, tak jak pisze je UPS: doręczenie to „DELIVERED”. Gdy w USA UPS przekaże paczkę Ground Saver poczcie amerykańskiej i poda jej numer, śledzenie przechodzi tam, gdy USPS pokaże postęp („Doręcza: USPS”).

## Pytania o UPS tracking

### Dlaczego UPS odrzuca mój numer przesyłki?

Najczęściej przez literówkę: jeden błędny znak w numerze `1Z` zwykle psuje cyfrę kontrolną i UPS odrzuca numer. Albo numer należy do innego przewoźnika: sprawdź, którego wymienia e-mail od sklepu.

### Kiedy pojawia się śledzenie paczki UPS?

Pierwszy skan, `Shipper created a label, UPS has not received the package yet.`, pojawia się, gdy nadawca utworzy etykietę. Dalej status ruszy dopiero, gdy nadawca nada paczkę albo UPS ją odbierze (`Drop-Off`, `Pickup Scan`).

### Czy mogę zmienić doręczenie UPS w Peek?

Nie: Peek tylko odczytuje śledzenie UPS. Zmiany, które zlecisz w UPS, pojawią się w skanach, np. `The package will be delivered to the UPS Access Point™ location requested by the receiver.`

### Czy SMS od UPS z prośbą o dopłatę jest prawdziwy?

Może to być oszustwo. CERT Polska (CSIRT NASK) ostrzegał przed SMS-ami, w których przestępcy podszywają się pod firmy kurierskie: piszą o problemie z doręczeniem, a link prowadzi do fałszywej strony, gdzie pod pretekstem dopłaty wyłudzają dane karty. Nie klikaj linku: numer przesyłki wpisz sam na ups.com albo w Peek, a w razie wątpliwości skontaktuj się z firmą przez numer lub adres z jej oficjalnej strony, nie z SMS-a. Podejrzany SMS przekaż w całości, bez wycinania linku, na bezpłatny numer 8080. Jeśli dane karty trafiły już na fałszywą stronę, od razu zadzwoń do banku. Pamiętaj też, że według KAS ryczałtowego cła 3 EUR nie płaci konsument. Więcej w poradniku [oszustwo na paczkę](guide:delivery-scam-texts).

:::sources
- [Krajowa Administracja Skarbowa: Zmiany w e-commerce od 1 lipca 2026 r.](https://www.gov.pl/web/kas/zmiany-w-e-commerce-od-1-lipca-2026-r) – koniec zwolnienia do 150 EUR, 3 EUR za pozycję towarową, cła nie płaci konsument, brak zwrotu cła i VAT
- [Your Europe: Wysyłka i dostawa](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_pl.htm) – 30 dni, dodatkowy termin, rozwiązanie umowy, odpowiedzialność sprzedawcy za uszkodzenia
- [UKE: Rejestr operatorów pocztowych, UPS Polska Sp. z o.o.](https://bip.uke.gov.pl/rop/rejestr-operatorow-pocztowych/?p=2&page=1&rejestr=B-00044) – wpis B-00044 od 17.12.2004
- [UKE, Centrum Informacji Konsumenckiej: Reklamacje](https://cik.uke.gov.pl/uslugi-pocztowe/dochodzenie-roszczen/reklamacje/) – kto składa reklamację, 12 miesięcy, 30 dni, odwołanie, interwencja Prezesa UKE, ADR
- [UKE, Centrum Informacji Konsumenckiej: Reklamacje i odszkodowania](https://cik.uke.gov.pl/uslugi-pocztowe/pytania-i-odpowiedzi/reklamacje-i-odszkodowania-faq/2.html) – protokół szkody, 7 dni na ukryte uszkodzenia
- [CSIRT NASK: oszustwo na „przesyłkę”](https://www.gov.pl/web/baza-wiedzy/uwaga-csirt-nask-ostrzega-w-okresie-przedswiatecznej-goraczki-zakupowej-wraca-popularne-oszustwo-na-przesylke) – fałszywe SMS-y o przesyłce, dopłata, numer 8080, bank
- [Universal Parcel Scraper: UPS](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ups) – kształty numerów, cyfra kontrolna, skany UPS, co Peek odczytuje z UPS
:::
