---
title: Śledzenie OCA: 19-cyfrowe numery, statusy, oddziały i pomoc
description: Śledź paczki OCA i e-Pak w Argentynie: 19-cyfrowe numery, hiszpańskie statusy, 2 próby doręczenia, 5 dni odbioru w oddziale, SmartLockers i pomoc.
slug: sledzenie-oca
published: 2026-11-24
updated: 2026-11-24
---

OCA to argentyński przewoźnik paczek i przesyłek pocztowych. Jego platforma ePak obsługuje handel elektroniczny, duże firmy oraz małe przedsiębiorstwa w całym kraju i współpracuje z Mercado Envíos. Odbiorcy śledzą paczki w [wyszukiwarce przesyłek OCA](https://www.oca.com.ar/Busquedas/Envios), odbierają awizowane przesyłki w oddziałach Puntos OCA w argentyńskich prowincjach albo korzystają z automatów OCA SmartLockers.

System śledzenia OCA zapisuje po hiszpańsku każdy skan w sortowni, transfer między oddziałami, wizytę kuriera i czas na odbiór.

## Formaty numerów śledzenia OCA

Standardowe numery paczek OCA e-Pak mają strukturę 19 cyfr:

- **19 cyfr z `0000` na pozycjach 6–9** (np. `3654800004829173605`) — numer listu przewozowego lub zlecenia nadania (`número de guía` / `orden de admisión`), który Centrum Pomocy OCA wskazuje do śledzenia.
- **Osobne `-1`** — na etykietach po numerze może widnieć `-1`, np. `3654800004829173605-1`. [Centrum Pomocy OCA](https://www.oca.com.ar/CentroDeAyuda) zaleca, aby go nie wpisywać. Pomiń je także w Peek: Peek usuwa sam myślnik, więc `...-1` zamienia się w 20-cyfrowy numer nieznany OCA.
- **Paczki i koperty opłacone znaczkiem** — OCA obsługuje też przesyłki opłacone z 3 wielkimi literami (`YAE`, `YAA`, `MDA`, `PAA`) i 8 cyframi dla paczek lub 2 literami i 8 cyframi dla kopert, wpisywane bez spacji i nawiasów na stronie OCA. Zwykła korespondencja bez znaczka (`postales simples`) nie ma śledzenia i trafia bezpośrednio do skrzynki lub pod drzwi.

Na stronie OCA środkowy ciąg zer można zastąpić znakami `//`; Peek wymaga pełnych 19 cyfr albo bezpośredniego linku `oca.com.ar/Seguimiento/Paquetes/...` lub starszego linku e-Pak, które odczytuje automatycznie. W Argentynie Peek pyta OCA o 19-cyfrowy numer tego kształtu; gdzie indziej odpytuje OCA dopiero w ostateczności. Inne formaty regionalne opisuje [przewodnik po formatach numerów przesyłek](guide:tracking-number-formats).

## Co oznaczają statusy śledzenia OCA

Usługa śledzenia e-Pak w OCA zwraca opisy po hiszpańsku i często dopisuje obsługujący oddział po myślniku (`- Suc: ...`) albo powód w nawiasie.

| Opis statusu OCA | Co oznacza |
| --- | --- |
| `En proceso de Retiro` | Zaplanowano odbiór od nadawcy; OCA nie przejęła jeszcze paczki. |
| `Retirado` / `Retirado en Origen` | OCA odebrała paczkę od nadawcy. |
| `En Proceso en OCA` / `Procesado en OCA` | Przetwarzana w sortowni lub oddziale OCA. |
| `En viaje a Centro de Distribución de Destino` / `En viaje a Sucursal de Destino` / `En Tránsito a Suc. de Destino` | W drodze do docelowego centrum dystrybucyjnego lub oddziału doręczającego. |
| `Arribado a Centro de Distribución de Destino` / `Llegó a nuestra sucursal` | Dotarła do docelowego hubu lub lokalnego oddziału OCA. |
| `Programado para visita a domicilio` | Ujęta w planowaniu doręczeń (`en programación`) w lokalnym oddziale przed wizytą pod adresem. |
| `Visita a Domicilio en Curso` | W doręczeniu z kurierem pod wskazany adres w dniu dzisiejszym. |
| `No entregado (No Visitado)` / `No entregado (...)` | Doręczenie nie doszło do skutku; tekst w nawiasie podaje przyczynę (np. brak wizyty lub wstrzymanie na polecenie nadawcy). |
| `Reprogramado para nueva visita` | Zaplanowana na kolejną próbę doręczenia. |
| `En Espera de Retiro por Sucursal` / `Disponible para que lo retires en tu Sucursal` | Oczekuje na odbiór we wskazanym oddziale OCA. |
| `Estado de Visita ONLINE (Entregado)` / `Entregado` | Doręczona odbiorcy lub odebrana w oddziale. |
| `Acuse digitalizado` | Potwierdzenie doręczenia zeskanowane w zakładzie OCA kilka dni po doręczeniu — wpis czysto administracyjny, nie przemieszcza paczki. |
| `Devolución en Proceso` / `Devuelto al Remitente` | W drodze powrotnej lub zwrócona do nadawcy. |

## Wizyty pod adresem, oddziały i SmartLockers

[Centrum Pomocy OCA](https://www.oca.com.ar/CentroDeAyuda) określa jasne zasady doręczeń w całej Argentynie:

- **Dwie próby doręczenia pod adres:** OCA wykonuje dwie niezapowiedziane wizyty od poniedziałku do soboty w godzinach 09:00–18:00. Paczkę może odebrać każda pełnoletnia osoba pod adresem po okazaniu dokumentu DNI. Deszczowe dni mogą ograniczać kursy doręczeń.
- **Odbiór w oddziale (5 dni roboczych):** po dwóch nieudanych wizytach paczka czeka **5 dni roboczych** w wyznaczonym oddziale Punto OCA (oddziały znajdziesz przez [Buscador de Puntos OCA](https://www.oca.com.ar/Busquedas/Sucursales)). Odbiorca musi okazać DNI i numer przesyłki; upoważniona osoba powyżej 18 lat potrzebuje kopii DNI odbiorcy, odręcznego upoważnienia oraz numeru przesyłki. Nieodebrane paczki wracają do nadawcy.
- **SmartLockers (48 godzin + opcjonalne przedłużenie):** gdy OCA umieści paczkę w automacie SmartLocker, otrzymujesz e-mail i/lub SMS z kodem QR oraz alfanumerycznym kodem PIN. Na odbiór masz **48 kolejnych godzin**; po przypomnieniach OCA wysyła link do płatności kartą, pozwalający wykupić do **3 dodatkowych dni przechowywania** przed zwrotem do nadawcy.
- **Błędny adres lub zwrot przesyłki:** OCA nie pozwala przerwać wysyłki ani zmienić adresu doręczenia w trakcie transportu. Poczekaj na zakończenie wizyt, aby odebrać paczkę w oddziale (albo pozwól na jej zwrot do nadawcy i poproś o ponowną wysyłkę). Więcej wskazówek po nieudanym doręczeniu znajdziesz w [poradniku o nieudanej dostawie](guide:missed-delivery).
- **Przesyłki specjalne (DNI, paszporty, karty OCA Geo, koperty):** dokumenty DNI doręczane są wyłącznie pod adres (za okazaniem odcinka RENAPER) bez możliwości odbioru w oddziale; paszporty trafiają do oddziału po dwóch wizytach domowych. Karty OCA Geo wymagają przekazania bezpośrednio posiadaczowi. Opłacone koperty mają dwie wizyty domowe i wracają do nadawcy bez przechowywania w oddziale.
- **48 godzin roboczych na pierwszy skan:** OCA informuje, że nowo nadane przesyłki mogą pojawić się w sieci do **48 godzin roboczych** od nadania w oddziale. Jeśli po 48 godzinach roboczych nic się nie pojawia, zgłoszenie może otworzyć wyłącznie nadawca. Zobacz też [gdy śledzenie przestaje się aktualizować](guide:tracking-not-updating).

Innym krajowym przewoźnikiem w Argentynie, z którym warto porównać zasady, jest [Andreani](carrier:andreani).

## Śledzenie OCA w Peek

Wklej 19-cyfrowy numer OCA bez `-1` albo link `oca.com.ar/Seguimiento/Paquetes/...` do Peek. Jeśli Peek sam nie wskaże OCA, wybierz OCA przez **Zmień** podczas dodawania przesyłki albo **Zmień przewoźnika** na stronie śledzenia.

Peek odpytuje bezpośrednio usługę sieciową e-Pak `Tracking_Pieza` firmy OCA przez otwartoźródłowy [adapter OCA w universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/oca). Każdy argentyński znacznik czasu zapisywany jest jako dokładny moment w strefie `America/Argentina/Buenos_Aires` (`-03:00`), nazwy oddziałów trafiają czytelnie do pola lokalizacji, a administracyjne wpisy `Acuse digitalizado` nigdy nie nadpisują ani nie zakłócają ukończonego doręczenia. Feed e-Pak firmy OCA nie zawiera szacowanej daty doręczenia ani wagi przesyłki.

Peek przekłada stany OCA na przejrzyste etapy: **Zarejestrowana** (`En proceso de Retiro`), **Przyjęta przez przewoźnika** (`Retirado`), **W transporcie** (sortownie i transfery między oddziałami), **W doręczeniu** (`Programado para visita a domicilio` i `Visita a Domicilio en Curso`), **Próba doręczenia** (`No entregado` i `Reprogramado para nueva visita`), **Do odbioru** (`En Espera de Retiro por Sucursal`), **Dostarczona** (`Entregado`) oraz **Zwrócona nadawcy** (`Devolución en Proceso` / `Devuelto al Remitente`).

Gdy masz przesyłkę otwartą w Peek albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza status nawet co 10 minut; w dniu, gdy OCA pokazuje `Programado para visita a domicilio` lub `Visita a Domicilio en Curso`, co 2 minuty od godz. 6:00 czasu argentyńskiego, dopóki tracker OCA odpowiada. W pozostałym czasie Peek sprawdza co godzinę. Po 12 godzinach wyłącznie na etapie „Zarejestrowana” lub „Przyjęta przez przewoźnika” albo po dwóch dniach bez zmian sprawdza co godzinę niezależnie od sposobu śledzenia, a po siedmiu dniach na „Zarejestrowana” — co 6 godzin. Gdy OCA nie odpowiada i Peek śledzi przesyłkę wyłącznie przez usługi śledzące, sprawdza co godzinę, co 3 godziny po spokojnym dniu i nawet co 15 minut w dniu, w którym paczka jest w doręczeniu, dopóki masz ją otwartą lub włączone powiadomienia.

Jeśli najnowszy skan ma cztery dni, a przesyłka nadal widnieje jako „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie”, Peek oznacza ją dniem tego skanu, np. „Brak aktualizacji od pon 12 paź”. Na stronie przesyłki pojawia się wtedy komunikat „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika OCA.” wraz z linkiem „Poproś przewoźnika OCA o pomoc” do Centrum Pomocy OCA. Po trzech tygodniach bez nowego skanu pojawia się informacja „Od ponad trzech tygodni nic nowego. Jeśli przesyłka wciąż nie dotarła, zapytaj sprzedawcę. Peek dalej pyta przewoźnika OCA.”

Wybierz **Każdy skan**, **Ważne etapy** lub **Tylko doręczenie** w ustawieniach alertów Peek, aby zdecydować, które aktualizacje wysyłają powiadomienia.

## Kontakt z OCA

- **Centrum Pomocy i formularz online:** [OCA Centro de Ayuda](https://www.oca.com.ar/CentroDeAyuda) (kategorie FAQ oraz bezpośredni formularz wiadomości u dołu strony)
- **Oficjalne śledzenie przesyłek:** [OCA Búsqueda de envío](https://www.oca.com.ar/Busquedas/Envios)
- **Wyszukiwarka oddziałów:** [Buscador de Puntos OCA](https://www.oca.com.ar/Busquedas/Sucursales)
- **Strona główna:** [oca.com.ar](https://www.oca.com.ar/)
- **Najpierw nadawca w sprawie brakującego rekordu lub ponownej wysyłki:** OCA zastrzega, że wyłącznie nadawca ma prawo zgłosić brak wpisu po 48 godzinach roboczych od nadania lub zlecić ponowną wysyłkę po zwrocie.

## Najczęstsze pytania

### Dlaczego mój numer śledzenia OCA kończy się na `-1`?

Centrum Pomocy OCA zaleca pominąć osobne `-1` i śledzić przesyłkę po 19-cyfrowym numerze listu lub zlecenia. Zrób tak samo w Peek: aplikacja nie usuwa `-1`, a numer z tym dopiskiem jest dla OCA nieznany.

### Jak długo OCA przechowuje paczkę w oddziale lub w SmartLockerze?

OCA przechowuje paczki w oddziałach Puntos OCA przez **5 dni roboczych** od nieudanych prób doręczenia lub przyjęcia w oddziale. W automatach SmartLockers masz **48 kolejnych godzin** za darmo, z możliwością opłacenia online do **3 dodatkowych dni** przed zwrotem do nadawcy.

### Czy mogę zmienić adres doręczenia po odebraniu paczki przez OCA?

Nie. Centrum Pomocy OCA wskazuje, że ani przebiegu przesyłki, ani adresu doręczenia nie można zmieniać w trakcie transportu. Po wizytach kuriera możesz odebrać paczkę osobiście we wskazanym oddziale OCA za okazaniem DNI albo poczekać na jej zwrot do nadawcy.

### Dlaczego mój numer śledzenia OCA nie pokazuje jeszcze żadnych aktualizacji?

OCA informuje, że pojawienie się paczki w śledzeniu internetowym może potrwać do **48 godzin roboczych** od przyjęcia w oddziale. Jeśli po 48 godzinach roboczych nadal brak wpisu, skontaktuj się z nadawcą, który jako jedyny może rozpocząć wyjaśnienie w OCA.

:::sources
- [OCA Argentina](https://www.oca.com.ar/)
- [OCA Centro de Ayuda](https://www.oca.com.ar/CentroDeAyuda)
- [OCA Búsqueda de envío](https://www.oca.com.ar/Busquedas/Envios)
- [OCA Buscador de Puntos OCA](https://www.oca.com.ar/Busquedas/Sucursales)
- [adapter OCA w universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/oca)
:::
