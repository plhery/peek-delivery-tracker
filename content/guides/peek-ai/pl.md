---
title: Kiedy przyjdzie paczka? Prognoza dostawy Peek AI
description: Peek AI to własna prognoza Peeka, kiedy przyjdzie twoja przesyłka, pokazywana obok daty przewoźnika. Kiedy się pojawia, jak często trafia i jak wyłączyć Peek AI.
slug: kiedy-przyjdzie-paczka
picture: Pip, paczka z buzią, zagląda w telefon, na którym karta przesyłki pokazuje małą gwiazdkę i „Peek AI do 16:00” pod datą przewoźnika.
published: 2026-11-24
updated: 2026-11-25
---

Gdy [Peek](/) ma mocną wskazówkę, kiedy przyjdzie twoja przesyłka, Peek AI podaje to obok daty przewoźnika albo zamiast niej, jeśli przewoźnik żadnej nie podaje: „pewnie do 16:00”, „pewnie dziś lub jutro”, „może wcześniej” albo „dziś raczej nie”. To własna prognoza Peeka, nigdy obietnica przewoźnika, a większość przesyłek nie pokazuje żadnej.

Peek AI działa domyślnie, z kontem i bez, na peektracker.com, w aplikacji na iPhone’a, iPada i Maca oraz w [wersji beta na Androida](guide:android-app), którą instalujesz z testów w Google Play, z własnego repozytorium F-Droid Peeka albo z GitHuba.

:::steps
- Spójrz na listę | Pod datą przewoźnika na karcie przesyłki albo obok statusu na karcie „Najbliższa dostawa”: „✦ Peek AI” i kilka słów, na przykład „do 16:00”.
- Otwórz przesyłkę | Krótka karta mówi na przykład „✦ Peek AI pewnie do 16:00”, a jedno zdanie pod nią wyjaśnia dlaczego.
- Stuknij (i) | Przycisk (i) („O Peek AI”) otwiera okno „Peek AI”: prognozę, a pod nią to, co mówi przewoźnik, na przykład „Według przewoźnika DPD: dziś”.
- Przeczytaj szczegóły | Często prawdopodobne dni albo godziny, zacieniowane według szans; potem „Dlaczego”, „Pewność” i przełącznik „Pokazuj Peek AI”.
:::

Widżet „Przesyłki” na [iPhonie](guide:iphone-app) i Androidzie pokazuje prognozę tam, gdzie jest na nią miejsce, a [podłączeni asystenci](guide:ai-assistants) dostają ją jako prognozę Peek AI, nie przewoźnika. [Powiadomienia](guide:parcel-notifications), e-maile o dostawie, paszporty znajomych i podgląd udostępnionego linku jej nie zawierają. W [demo Peeka](https://peektracker.com/demo) są przykładowe przesyłki z prognozą.

## Kiedy przyjdzie moja paczka? Co może ci powiedzieć Peek AI

Peek AI odzywa się tylko wtedy, gdy ma jedną z pięciu wskazówek, a każda z nich trafiała co najmniej 8 razy na 10 przy przesyłkach śledzonych przez Peek.

| Wskazówka | Peek AI mówi | Zdanie wyjaśnienia, na przykład |
| --- | --- | --- |
| W doręczeniu, przewoźnik nie podaje godziny | „pewnie do 16:00” | „W doręczeniu od 09:12. Przewoźnik DPD nie podaje godziny.” |
| Przewoźnik podaje dzisiejszą datę; po południu przesyłka wciąż nie jest w doręczeniu | „dziś raczej nie” | „Przewoźnik DPD podaje dzisiejszą datę, ale przesyłka nie jest jeszcze w doręczeniu.” |
| Data przewoźnika przypadała wczoraj; skan w ciągu ostatnich 24 godzin | „pewnie dziś lub jutro” | „Przewoźnik DPD podawał wczorajszą datę, ale przesyłka znów jest w drodze.” |
| Data przewoźnika za kilka dni; podobne przesyłki zwykle docierają przed nią | „może wcześniej” | „Przewoźnik DPD podaje pt 27 lis. Przesyłka jest przed czasem.” |
| Przewoźnik nie podaje daty | „pewnie do czw 26 lis”, „pewnie w tym tygodniu” i inne | „Przewoźnik UPS nie podaje daty. Dotarła do urzędu celnego wczoraj.” |

DPD i UPS zastępują tu twojego przewoźnika. Na karcie „dziś raczej nie” skraca się do „nie dziś” albo do „raczej nie” obok daty przewoźnika, która przypada na dziś. Godziny są w twoim formacie, 12- albo 24-godzinnym.

Każda prognoza znika, gdy traci ważność, nawet ze strony pozostawionej otwartej: „dziś raczej nie” o północy, „pewnie do 16:00” o 16:00, „może wcześniej” w dniu podanym przez przewoźnika, a prognoza na dany dzień, gdy ten dzień minie.

### Dlaczego przy mojej przesyłce nie ma prognozy?

Zwykle dlatego, że Peek nie ma mocnej wskazówki. Peek AI milczy też:

- gdy przesyłka jest doręczona, zarchiwizowana, czeka w punkcie odbioru albo wraca do nadawcy, a także po próbie doręczenia (zobacz [awizo po nieudanym doręczeniu](guide:missed-delivery));
- przed pierwszym prawdziwym skanem: sama etykieta nie wystarczy;
- gdy przewoźnik ma doręczenie wśród zaplanowanych etapów albo podaje na dziś godzinę lub przedział, które jeszcze nie minęły;
- zanim przesyłka trafi do doręczenia, jeśli Peek nie sprawdzał jej ostatnio (w ciągu 36 godzin, poza „dziś raczej nie”);
- gdy data przewoźnika minęła ponad dzień temu (zobacz, [kiedy się martwić](guide:tracking-not-updating));
- przy „może wcześniej” i prognozach bez daty przewoźnika: 45 dni po pierwszym skanie albo 30 dni po najnowszym.

## Czy data dostawy od przewoźnika jest dokładna? „Może wcześniej” i „dziś raczej nie”

Nie zawsze, i Peek AI mówi o tym w dwóch przypadkach: „może wcześniej”, gdy przesyłki o skanach podobnych do twoich zwykle docierają przed datą przewoźnika, i „dziś raczej nie”, gdy przewoźnik podaje dzisiejszą datę, ale po południu przesyłka wciąż nie jest w doręczeniu.

„Może wcześniej” może podać pierwszy prawdopodobny dzień („może wcześniej, już od jutra”, „może wcześniej, od czw 26 lis”) i nigdy się nie pojawia, gdy przewoźnik podaje przedział dni.

## Przesyłka wydana do doręczenia, a wciąż jej nie ma: czy dotrze dziś?

Peek AI może ci to powiedzieć. „Pewnie do 16:00” znaczy, że większość przesyłek tego przewoźnika śledzonych przez Peek docierała do tej godziny w dniu doręczenia; pojawia się tylko wtedy, gdy przewoźnik nie podaje godziny, a przesyłka wyjechała przed tą godziną. Pod koniec dnia „dziś raczej nie” znaczy, że tak późno dociera niewiele przesyłek: „O tej porze wciąż w doręczeniu. U przewoźnika DPD niewiele przesyłek dociera tak późno.” U niektórych przewoźników Peek AI nic nie mówi, dopóki przesyłka jest w doręczeniu, bo Peek nie ufa ich skanom „W doręczeniu”.

Zwykłe godziny każdego przewoźnika i to, dlaczego kurier może jeszcze jeździć o 20:00, opisuje poradnik [o której dostarczają paczki](guide:delivery-times); co oznacza status, wyjaśniają [statusy przesyłek](guide:tracking-statuses).

## Przewoźnik nie podaje daty dostawy: ile to potrwa?

Peek AI może podać dzień albo przedział, zależnie od tego, czego jego model jest wystarczająco pewny: „pewnie do czw 26 lis”, „pewnie dziś lub jutro” (tylko gdy przewoźnik doręcza jutro), „pewnie w tym tygodniu” (do niedzieli) albo „pewnie do końca przyszłego tygodnia” (do kolejnej niedzieli).

Ile zwykle idą przesyłki z Chin, piszemy w poradniku [śledzenie paczki z Chin](guide:tracking-from-china).

## Jak Peek AI przewiduje dostawę i jak często trafia?

Peek AI to wytrenowany model i tabele reguł na własnym serwerze Peeka, a pokazuje tylko te rodzaje prognoz, które trafiały co najmniej 8 razy na 10.

Model, klasyfikator gradient boosting złożony z drzew decyzyjnych, podaje, z jaką szansą przesyłka dotrze najpóźniej w każdy z najbliższych dni doręczeń przewoźnika. Bierze pod uwagę skany i miejsca, w których je zrobiono, przewoźnika, usługę, wagę, strefę czasową i zadeklarowany kraj docelowy, a także to, czy przesyłkę sprawdzano bez konta. Tabele reguł zawierają zwykłą godzinę doręczeń każdego przewoźnika i to, jak często sprawdzał się każdy rodzaj prognozy.

Serwer wylicza prognozę za każdym razem, gdy wysyła dane przesyłki do twojego telefonu lub przeglądarki, i nie korzysta przy tym z żadnej zewnętrznej usługi; one tylko ją pokazują. Model ma wyłącznie serwer Peeka: ani model, ani kod, który go trenuje, nie są częścią [otwartego kodu Peeka](guide:developers). To nie jest chatbot.

„Pewność” mówi „Przy podobnych przesyłkach trafia 8 razy na 10.”, a przy „dziś raczej nie” – „O tej porze trafia 8 razy na 10.” Liczba to 8 albo 9: Peek AI nie pokazuje niczego poniżej 8 na 10, nigdy nie podaje więcej niż 9 i zaokrągla w dół.

## Jak wyłączyć Peek AI?

Wyłącz „Peek AI” w sekcji „Preferencje” ekranu „Ustawienia” Peeka albo stuknij (i) na karcie Peek AI przesyłki i wyłącz „Pokazuj Peek AI”.

:::steps
- Otwórz „Ustawienia” | Na stronie: przycisk konta (twój inicjał), a bez konta przycisk z kołem zębatym „Ustawienia”. W aplikacjach: przycisk Konto u góry ekranu Dostawy; na Macu okno ustawień Peeka, karta „Ogólne”.
- Znajdź Peek AI | „Peek AI” to ostatni wiersz w sekcji „Preferencje”, z opisem „Gdy Peek ma mocną wskazówkę, podaje, kiedy jego zdaniem przyjdzie przesyłka.”
- Wyłącz przełącznik | Peek AI znika z kart, z karty „Najbliższa dostawa”, ze stron przesyłek i z widżetów na tym urządzeniu.
:::

Z kontem twój wybór przenosi się na pozostałe urządzenia, a podłączeni asystenci przestają dostawać prognozę. Bez konta zostaje w tej przeglądarce albo na tym urządzeniu; poradnik [Peek bez konta](guide:account-or-guest) porównuje obie możliwości.

:::sources
- [Peek: Peek AI na serwerze](https://github.com/plhery/peek-delivery-tracker/blob/main/src/server/peekAi.ts) – pięć wskazówek, 8 na 10, 36 godzin, kiedy milczy
- [Peek: słowa Peek AI](https://github.com/plhery/peek-delivery-tracker/blob/main/src/lib/peekAi.ts) – słowa na kartach i stronach, kiedy kończy się każda prognoza, Pewność
- [Peek: karta i okno Peek AI](https://github.com/plhery/peek-delivery-tracker/blob/main/src/components/PeekAi.tsx) – karta, okno pod (i), Pokazuj Peek AI
- [Peek: model](https://github.com/plhery/peek-delivery-tracker/blob/main/src/server/peekAiModel.ts) – drzewa decyzyjne, co bierze pod uwagę, 45 i 30 dni
- [Peek: Architecture](https://github.com/plhery/peek-delivery-tracker/blob/main/docs/ARCHITECTURE.md) – jak działa Peek AI, gdzie się nie pojawia, wybór zapisany na koncie
- [Peek: polskie teksty](https://github.com/plhery/peek-delivery-tracker/blob/main/shared/locales/pl.json) – każda cytowana tu etykieta Peek AI
- [Peek: Preferencje na stronie](https://github.com/plhery/peek-delivery-tracker/blob/main/src/components/Preferences.tsx) – przełącznik Peek AI, który podąża za kontem
- [Peek: Ustawienia na iPhonie, iPadzie i Macu](https://github.com/plhery/peek-delivery-tracker/blob/main/ios/PeekDeliveryTracker/SettingsViews.swift) – Peek AI w Preferencjach, Ogólne na Macu
- [Peek: Ustawienia na Androidzie](https://github.com/plhery/peek-delivery-tracker/blob/main/android/app/src/main/kotlin/com/plhery/peekdeliverytracker/ui/settings/SettingsSheet.kt) – Peek AI w Preferencjach
- [Peek: narzędzia asystentów](https://github.com/plhery/peek-delivery-tracker/blob/main/src/server/assistants/tools.ts) – prognoza na liście przesyłek asystenta
- [App Store: Peek](https://apps.apple.com/pl/app/id6823615370) – Peek AI w opisie nowości
- [Peek: informacje o wersji na Androida](https://github.com/plhery/peek-delivery-tracker/blob/main/fastlane/metadata/android/en-US/changelogs/21.txt) – Peek AI w wersji beta na Androida
- [Peek: Wypróbuj Peek na Androida](https://peektracker.com/android) – wersja beta z testów w Google Play, z F-Droid i z GitHuba
- [Peek: demo](https://peektracker.com/demo) – przykładowe przesyłki z prognozą
:::
