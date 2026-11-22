---
title: Śledzenie DHL eCommerce Iberia: statusy i ServicePoint
description: Śledzenie paczki DHL eCommerce w Hiszpanii lub Portugalii: znaczenie statusów, nieudane doręczenia, ServicePoint, CTT Express i pomoc.
slug: sledzenie-dhl-ecommerce-iberia
published: 2026-11-22
updated: 2026-11-22
---

DHL eCommerce Iberia to sieć paczkowa DHL w Hiszpanii i Portugalii, dawniej DHL Parcel Iberia — nazwa DHL Parcel wciąż pojawia się w niektórych miejscach. Wklej numer w polu powyżej, aby śledzić przesyłkę; na jej karcie przycisk „Otwórz stronę przewoźnika DHL eCommerce Iberia” prowadzi do tej samej paczki na stronie DHL. Numer otrzymasz ze sklepu, który nadał zamówienie.

## Numer śledzenia DHL eCommerce: skąd go wziąć i jak wygląda

Brak numeru? Zapytaj sklep albo zajrzyj do [gdzie znaleźć numer śledzenia](guide:find-tracking-number).

Formaty znane w katalogu Peek (przykłady wymyślone):

| Format | Przykład | Do kogo może należeć |
| --- | --- | --- |
| 10 cyfr | `4829173649` | DHL eCommerce Iberia, ale też [DHL Express](carrier:dhl-express), [Mondial Relay](carrier:mondial-relay), Relais Colis i GLS Hiszpania |
| Dwie litery, 7 cyfr, następnie `0` | `KL48291730` | DHL eCommerce Iberia (i TNT Włochy) |
| `JJD000061`, `JJD000268`, `JJD000269` lub `JJD000368` oraz 14 cyfr | `JJD00006148291736482917` | DHL eCommerce Iberia |

Jeśli e-mail ze sklepu wskazuje dla 10-cyfrowego numeru [Mondial Relay](carrier:mondial-relay) lub GLS Hiszpania, śledź go tam. Dwie litery, dziewięć cyfr i `ES` to numer [Correos](carrier:correos-spain).

## Znaczenie statusów śledzenia DHL eCommerce

Peek pobiera angielskie komunikaty DHL, choć pojedyncze skany wciąż przychodzą po hiszpańsku, np. `Llegada a`, `Tránsito en`, `Salida de` i `Entregado`. Typowe statusy po kolei:

| DHL wyświetla | Co to oznacza dla ciebie |
| --- | --- |
| `Shipment data received` | Nadawca zarejestrował przesyłkę. DHL nie ma jeszcze paczki. |
| `Picked up` | DHL odebrał paczkę od nadawcy; dalej widnieje miasto. |
| `Shipment received at DHL Depot` | Paczka dotarła do magazynu DHL. |
| `Arrived at` | W drodze między magazynami; dalej widnieje miasto. `In transit in` i `Departed from` działają tak samo. |
| `Out for delivery` | Dziś w samochodzie kuriera. |
| `The delivery could not be made due to consignee absent` | Nikogo nie było. Zobacz kolejną sekcję. |
| `Delivered to DHL ServicePoint, ready for collection by the recipient` | Gotowa do odbioru w punkcie DHL ServicePoint. |
| `Parcel picked up by recipient` | Odebrana w punkcie ServicePoint. |
| `Delivered` | Doręczona pod adres. |
| `Shipment being returned to sender` | Trwa zwrot do nadawcy. |

Kilka komunikatów wymaga wyjaśnienia:

- `Delivered, POD pending`: oznaczona jako doręczona, ale brak jeszcze potwierdzenia odbioru. Może pojawić się przedwcześnie — wciąż może nastąpić kolejna próba doręczenia.
- `Shipment with incidence If you are the sender, please contact DHL Parcel, if you are the recipient please contact the sender`: wystąpił problem z paczką. Zgodnie z komunikatem odbiorca powinien skontaktować się z nadawcą — napisz do sklepu.
- `The expected delivery date might be postponed one working day` i `Not delivered today: delivery expected for the next working day` oznaczają krótkie przesunięcie, nie zaginięcie przesyłki.

## Nieudane doręczenie i punkty ServicePoint

**Brak odbiorcy w domu.** Nieudana próba widnieje jako `The delivery could not be made due to consignee absent` lub `Receiver closed: delivery not possible`, gdy lokal był zamknięty. Późniejsze wpisy jak `New delivery date requested` albo `Delivery agreed with recipient` oznaczają prośbę lub ustalenie nowej daty dostawy. Jeśli widzisz `Incorrect address details: delivery not possible`, dane adresowe uniemożliwiły doręczenie: Peek pokazuje „Zgłoszono problem”, a ty lub nadawca musicie poprawić adres, by paczka ruszyła dalej.

**ServicePoint.** `Parcel will be delivered to a DHL ServicePoint` i `Parcel is on it's way to our DHL ServicePoint` oznaczają kierowanie paczki do punktu DHL ServicePoint. Gdy będzie gotowa do odbioru, Peek wyświetli kartę „Punkt odbioru” z nazwą i adresem punktu, wskazówkami dojazdu i przyciskiem kopiowania adresu. Peek nie pobiera godzin otwarcia — sprawdź je wraz z czasem przechowywania w DHL.

## Paczka opóźniona, zaginiona lub uszkodzona

**Brak aktualizacji.** Zobacz [dlaczego śledzenie przestaje się aktualizować](guide:tracking-not-updating).

**Zaginiona lub uszkodzona.** Zacznij od sklepu: sam skan `Shipment with incidence` kieruje odbiorców do nadawcy. W UE serwis Your Europe przypomina, że sprzedawca odpowiada za uszkodzenia do chwili odbioru towaru. Bez uzgodnionej daty ma dostarczyć przesyłkę w ciągu 30 dni; inaczej wezwij go z rozsądnym terminem dodatkowym, po czym możesz odstąpić i odzyskać pieniądze. Zobacz [zaginione przesyłki](guide:lost-parcel) i [uszkodzone przesyłki](guide:damaged-parcel).

**Cło.** Przy paczkach spoza UE zajrzyj do [poradnika celnego](guide:customs).

**CTT Express.** 12 maja 2026 r. DHL eCommerce i CTT podpisały umowę joint venture: w Portugalii działalność DHL eCommerce przechodzi do CTT Expresso, a w Hiszpanii [CTT Express](carrier:ctt-express) przejmuje doręczenia dla konsumentów, podczas gdy DHL zachowuje doręczenia dla firm. Część paczek ma numer CTT Express — śledź je w [CTT Express](carrier:ctt-express).

> Podejrzany SMS lub e-mail o paczce DHL? Przeczytaj [jak rozpoznać fałszywe wiadomości o dostawie](guide:delivery-scam-texts).

## Kontakt z DHL eCommerce

Danych kontaktowych DHL eCommerce szukaj na stronie DHL właściwej dla kraju dostawy — Hiszpanii lub Portugalii. W sprawie zaginionej lub uszkodzonej paczki najpierw skontaktuj się ze sklepem.

## Śledzenie paczek DHL eCommerce Iberia w Peek

[Peek](/) obsługuje ponad 4500 przewoźników. Dla dwóch liter, 7 cyfr i `0` pyta DHL eCommerce Iberia oraz TNT Włochy, które stosują ten sam format. Dla 10 cyfr pyta DHL eCommerce Iberia, DHL Freight, [Packeta](carrier:packeta) i BOX NOW, a przy poprawnej cyfrze kontrolnej najpierw DHL Express (oraz ACS Courier i [Geniki Taxydromiki](carrier:geniki-taxydromiki), które spełniają tę samą kontrolę); jeśli DHL Express także zna numer, ma pierwszeństwo. Gdy paczkę zna kilka marek lub żadna, wybierz przewoźnika przez „Zmień przewoźnika”. Kod `JJD` w formacie z tabeli powyżej od razu wskazuje DHL eCommerce Iberia („Wykryty przewoźnik”). Pozostałe numery DHL (12 lub 22 cyfry, inne `JJD`, kody pocztowe) działają dopiero po ręcznym wyborze „DHL eCommerce Iberia”. Kod pocztowy nie jest wymagany.

Działają też linki ze strony śledzenia DHL eCommerce Iberia po hiszpańsku, portugalsku lub angielsku (`/LiveTracking/ModificarEnvio/`, `ModificarMeuEnvio` lub `ModifyMyShipment`) — od razu wskazują DHL eCommerce Iberia niezależnie od formatu numeru, a kod pocztowy na końcu linku jest pomijany.

Skany DHL nie podają strefy czasowej: Peek odczytuje je w czasie lokalnym miejsca skanu (czasie Madrytu w Hiszpanii, Lizbony w Portugalii) i wyświetla w twojej strefie („Godziny w twojej strefie czasowej:” i jej nazwa). Pokazuje status, wagę, punkt odbioru ServicePoint podczas oczekiwania i po odbiorze oraz przekazuje dalsze śledzenie do [CTT Express](carrier:ctt-express), gdy odpowiedź DHL zawiera numer CTT Express. Opisy zachowują angielską formę DHL z wyjątkiem `Delivered` i `Out for delivery`, tłumaczonych przez Peek. Przewidywany dzień dostawy ani osobna godzina doręczenia nie są wyświetlane.

Gdy masz przesyłkę otwartą w Peek — albo w dniu dostawy przy włączonych powiadomieniach — Peek pyta bezpośrednio DHL eCommerce Iberia nawet co 10 minut, a w dniu, gdy DHL nada status „W doręczeniu”, nawet co 2 minuty od 6:00 czasu ostatniego skanu (czasu Madrytu w Hiszpanii), dopóki śledzenie DHL odpowiada; w pozostałym czasie oraz po dwóch dniach bez zmian (12 godzinach, gdy przesyłka nadal ma status „Zarejestrowana” lub „Przyjęta przez przewoźnika”) sprawdza co godzinę. Dla powiadomień wybierz „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Po czterech dniach bez nowego skanu w drodze, poza odprawą celną i zgłoszonym problemem, Peek pokazuje datę ostatniego skanu („Brak aktualizacji od …”) i komunikat „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika DHL eCommerce Iberia.” z linkiem „Poproś przewoźnika DHL eCommerce Iberia o pomoc” do strony kontaktowej. Peek nie może kontaktować się z DHL ani ze sklepem w twoim imieniu.

## Pytania o śledzenie DHL eCommerce Iberia

### Czy DHL eCommerce to to samo co DHL Express?

Nie. Listy przewozowe DHL Express mają własne śledzenie (MyDHL+), a Peek traktuje obie firmy jako osobnych przewoźników. Numer 10-cyfrowy może należeć do obu: gdy cyfra kontrolna pasuje, Peek pyta najpierw DHL Express i jeśli DHL Express zna numer, wygrywa.

### Dlaczego moja paczka DHL eCommerce nie ma historii śledzenia?

Sprawdź numer i przewoźnika w sklepie — 10-cyfrowych numerów używa wiele firm (zobacz tabelę wyżej). Starsze przesyłki znikają też ze śledzenia DHL; okres przechowywania historii nie jest podany.

### Czy CTT Express doręcza paczki DHL eCommerce?

Część z nich. Na mocy umowy joint venture podpisanej w maju 2026 r. między DHL eCommerce i CTT doręczenia konsumenckie w Hiszpanii przejmuje CTT Express, a działalność DHL eCommerce w Portugalii przechodzi do CTT Expresso. Jeśli śledzenie poda numer CTT Express, śledź go w [CTT Express](carrier:ctt-express).

:::sources
- [CTT: CTT e DHL Ecommerce selam parceria estratégica](https://www.ctt.pt/grupo-ctt/media/noticias/ctt-e-dhl-ecommerce-selam-parceria-estrategica-para-criar-rede-de-distribuicao-de-encomendas-mais-completa-da-peninsula-iberica) – umowa joint venture z 12 maja 2026 r., DHL eCommerce Portugalia do CTT Expresso, CTT Express dla konsumentów w Hiszpanii, DHL dla doręczeń biznesowych
- [Your Europe: Shipping and delivery](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_en.htm) – odpowiedzialność sprzedawcy do doręczenia, 30 dni, dodatkowy termin, zwrot pieniędzy
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – pola odczytywane przez Peek, statusy i etapy, ServicePoint, linki śledzenia, dawna nazwa DHL Parcel Iberia, przekazanie CTT Express i częstotliwość sprawdzania
:::
