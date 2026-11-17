---
title: Poste Italiane śledzenie: kody 1UW i 3UW oraz statusy
description: Śledzenie przesyłki Poste Italiane z Włoch do Polski: jak wygląda numer (1UW, 3UW, 8UW, 5P), co znaczą włoskie statusy, odbiór w Punto Poste i kontakt.
slug: poste-italiane-sledzenie
published: 2026-11-17
updated: 2026-11-17
---

Poste Italiane to włoski operator pocztowy, który przez sieci Crono i SDA doręcza też zamówienia z włoskich sklepów internetowych, z Vinted czy eBaya. Przesyłkę sprawdzisz w „Cerca Spedizioni” na poste.it (dawniej Dove Quando), w aplikacji Poste Italiane albo wklejając kod powyżej. W drodze z Włoch do Polski poste.it pokazuje etapy do międzynarodowego centrum wymiany; przy numerach pocztowych z końcówką `IT` dalszą obsługę przejmuje operator w Polsce.

## Numer przesyłki Poste Italiane: gdzie go znaleźć i jak wygląda

Poste nazywa go *codice spedizione*. Na dowodzie nadania lub liście przewozowym (*lettera di vettura*) widnieje pod kodem kreskowym – wpisz go bez spacji i myślników. Przy zakupach online podaje go sklep w e-mailu (albo powiadomienie Scegli Tu od Poste); we Włoszech awizo (*avviso di giacenza*) wskazuje numer listu poleconego, urząd pocztowy i ewentualny odbiór przez Ritiro Digitale. „Codice Prenotazione Ritiro” (`CP` i cyfry) służy tylko nadawcy do zamówienia podjazdu kuriera, nie do śledzenia. Brak numeru? Zobacz, [gdzie szukać numeru przesyłki](guide:find-tracking-number).

Wszystkie poniższe przykłady są wymyślone:

| Format (wymyślony) | Co oznacza |
| --- | --- |
| `RA00000000000` | `RA` i 11 cyfr: kod przesyłki oraz listu poleconego. |
| `3UW0000000000` | `1UW`, `3UW` lub `8UW` i 10 liter/cyfr: Poste Delivery Business. |
| `5PA0000000000` | `5P` i 11 liter/cyfr: paczka Crono. |
| `JF00000000000` | `EF`, `EG`, `GF`, `JF`, `JG` lub `LF` i 11 cyfr: Poste Delivery Web i MyPoste. |
| `JJITA0000000000000` | `JJITA` i 13 liter/cyfr: włoski kod paczki ISO. |
| `2IMA0000000000` | `2IMA` i 10 cyfr (albo `2IVC`, 4 cyfry, litera/cyfra, 5 cyfr): etykieta Posta1. |
| `000000A000000` | Cyfry i litery (6 cyfr, litera, 6 cyfr albo początek `1C`, `3C`, `9C`, `F`, `D`): styl SDA i Crono. |
| `050000201242` | 12 cyfr z cyfrą kontrolną: wzór z poste.it dla poleconych, wartościowych, pism sądowych i paczek. |
| `RR000000005IT` | Dwie litery, dziewięć cyfr i `IT` (`RR…IT`, `CW…IT`, `ZC…IT`): przesyłka międzynarodowa z Włoch. |

Część formatów dzielą inni przewoźnicy: `5P` z jedenastoma cyframi zgodnymi z sumą Colissimo może należeć do La Poste; 12 cyfr używa m.in. BRT czy Colis Privé, a `000A00000000B`, `9C0000I000000` i `9C0000J000000` także [AliExpress / Cainiao](carrier:cainiao). Zobacz [formaty numerów śledzenia](guide:tracking-number-formats).

**Co można śledzić:** polecone, wartościowe, pisma sądowe i paczki (od następnego dnia roboczego w Poste Delivery Express – dawne Paccocelere – i Standard – dawne *pacco ordinario*). Poste Delivery International Express ma śledzenie do doręczenia (poza Tunezją, Grenlandią, Wyspami Owczymi i częścią kodów w Szwajcarii); International Standard i Postapriority Internazionale kończą się na poste.it w centrum wymiany we Włoszech. Posta Ordinaria (dawniej Posta4) i Postamail Internazionale nie mają śledzenia; Posta1 wycofano 1 maja 2026 r. Historia jest widoczna 2 miesiące dla paczek i 24 miesiące dla listów.

## Statusy Poste Italiane po włosku

Każdy skan ma włoskie zdanie z *Legenda degli stati di spedizione*, pokazywane w Peek bez zmian:

| Komunikat Poste Italiane | Co oznacza |
| --- | --- |
| `Presa in carico` | Przesyłka przyjęta i kierowana w stronę adresata. |
| `In attesa di sdoganamento` | Kontrola celna; urząd może poprosić o dokumenty. |
| `completata la fase di verifica per lo svincolo…` | Odprawa celna zakończona, przesyłka zwolniona. |
| `In transito` | W drodze między sortowniami Poste Italiane. |
| `In transito presso il Centro di lavorazione Internazionale` | Opuściła Włochy do operatora zagranicznego (ostatni skan dla Postapriority Internazionale i International Standard). |
| `presso il Centro di lavorazione Internazionale` | Ten sam etap dla listu poleconego, w osobnej linii po `In transito`. |
| `In consegna` | Wydana do doręczenia. |
| `consegna non andata a buon fine…` | Nieudane doręczenie, np. przez błędny lub niepełny adres. |
| `disponibile per il ritiro dal giorno lavorativo successivo alla data indicata` | Do odbioru na poczcie od następnego dnia roboczego po podanej dacie. |
| `disponibile per il ritiro entro i termini indicati su SMS/email` | Czeka w Punto Poste lub automacie (*locker*) w terminie z SMS-a/e-maila. |
| `la spedizione è stata consegnata` | Doręczona. |
| `sono in corso delle verifiche sulla spedizione. contatta assistenza` | Trwa wyjaśnianie sprawy – skontaktuj się z obsługą klienta. |
| `in restituzione al mittente…` | W drodze powrotnej do nadawcy. |
| `Resa al mittente` | Niedoręczona (upłynął termin, błędny adres lub odmowa) i zwracana nadawcy. |
| `Tracciatura non disponibile` | Nadana przed chwilą lub starsza niż 2 miesiące (paczki) bądź 24 miesiące (listy). |

`In consegna` potrafi wisieć jeszcze po doręczeniu lub gdy paczka czeka już w placówce, a po nieudanej próbie wraca `In transito`. Planowana data jest „wyłącznie orientacyjna” (*meramente indicativa*). Więcej: [statusy przesyłek](guide:tracking-statuses).

## Dostawa do Polski i odbiór we Włoszech

**Między Włochami a Polską.** Międzynarodową przesyłkę pocztową `…IT` po wyjściu z włoskiego centrum wymiany przejmuje operator w Polsce (np. [Poczta Polska](carrier:poczta-polska) w eMonitoringu); w drugą stronę poste.it pokazuje paczkę dopiero od przyjęcia we Włoszech. W unii celnej UE między Włochami a Polską nie ma ceł.

**We Włoszech (*giacenza* i Punto Poste).** Poste podejmuje 2 próby pod budynek (*piano strada*): po pierwszej zostawia awizo, ponawia następnego dnia roboczego w Crono i Poste Delivery Business, a potem bezpłatnie przechowuje paczkę (*giacenza*) na poczcie; zobacz [nieodebrana paczka](guide:uncollected-parcel):

- **Terminy i pełnomocnictwo:** od 3 dni roboczych w automacie do 10 na poczcie (Poste Delivery Web: 10 poczta, 7 Punto Poste, 3 automat; Crono: 10 w Punto Poste; polecone: 30 dni, Raccomandata 1: 15 dni). Rezerwacja wizyty przez „Prenota” lub pod 06 4526 4526; na poczcie osoba trzecia odbiera ze szczegółowym upoważnieniem, swoim dokumentem i kopią Twojego, a w Punto Poste/automacie kodem PIN.
- **Scegli Tu i PostePlus:** w Scegli Tu jednorazowo zmienisz dzień, adres we Włoszech, placówkę/Punto Poste, sąsiada, bezpieczne miejsce lub urlop; PostePlus działa dla mieszkańców Włoch.
- **Odprawa spoza UE we Włoszech:** płatna przy odbiorze, na poczcie lub taniej na www.poste.it przyciskiem „Paga” (opłata handlowa Poste: 3 € do 22 €, 7 € do 150 €, 21 € powyżej; online: 1 € do 150 €, 18 € powyżej). Według Komisji Europejskiej od 1 lipca 2026 r. dochodzi cło 3 € za każdy rodzaj towaru w przesyłkach do 150 €, a od 1 listopada 2026 r. opłata unijna 2 € za każdy rodzaj towaru przy zakupach online, niezależnie od wartości. Poste ostrzega też o znacznych spowolnieniach (*significativi rallentamenti*) odpraw spoza UE z przyczyn technicznych, bez możliwego terminu rozładowania zaległości; zobacz [paczka na cle](guide:customs).

## Paczka utknęła, zaginęła lub przyszła uszkodzona

Przy zakupach online zgłoś się najpierw do sklepu (w UE ma 30 dni na dostawę i odpowiada do wydania paczki); zobacz [brak nowych skanów](guide:tracking-not-updating), [zagubiona paczka](guide:lost-parcel) i [dostarczona, ale nieodebrana](guide:delivered-not-received). W Poste reklamację złoży nadawca lub adresat, ale odszkodowanie trafia do nadawcy bez formalnego zrzeczenia (przy paczkach z zagranicy – przez pocztę nadania). Uszkodzenia zgłoś przy odbiorze (*consegna cautelare* lub odmowa); zobacz [uszkodzona paczka](guide:damaged-parcel).

**Terminy:** 20 dni kalendarzowych od przejęcia w Crono/Business; od 6. dnia roboczego do 3 miesięcy w Standard. Zgłoszenie przez [Reclami](https://www.poste.it/assistenza/reclami), PEC `reclamiretail@postecert.it`, poleconym na Casella Postale 160, 00144 Roma lub w placówce (odpowiedź w 45 dni kalendarzowych, potem postępowanie pojednawcze i AGCOM).

## Kontakt i infolinia Poste Italiane

Sprawdzone na poste.it 16 listopada 2026 r.:

- **Z Polski, zagranicy i komórki:** `+39 06 4526 3160`, pon.–sob. poza włoskimi świętami, 8:00–20:00, według cennika Twojego operatora.
- **Włoski telefon stacjonarny:** `803 160` (bezpłatny, te same godziny). Informacje tylko dla właściciela konta.
- **Pisemnie i czat:** `assistenza.spedizioni@posteitaliane.it`, [Scrivici](https://www.poste.it/assistenza/scrivici), [Contact us](https://www.poste.it/assistenza/scrivici-en) oraz całodobowy *Assistente Digitale*.

> Fałszywe SMS-y potrafią przez spoofing trafić do prawdziwego wątku Poste Italiane. Nie klikaj linków – wpisz `www.poste.it`. Poste nigdy nie prosi o kartę, OTP ani hasła; zgłaszaj na `antiphishing@posteitaliane.it` i zobacz [fałszywe SMS-y o paczce](guide:delivery-scam-texts).

## Śledzenie przesyłki Poste Italiane w Peek

[Peek](/) od razu rozpoznaje Poste Italiane po `RA`, `1UW`, `3UW`, `8UW`, `JJITA`, `2IMA`, `2IVC`, `EF`, `EG`, `GF`, `JF`, `JG`, `LF` i `ZC…IT`, po poprawnych numerach pocztowych na `IT` zaczynających się od `C`, `E`, `L` lub `R` oraz po `5P` (chyba że po `5P` stoi jedenaście cyfr zgodnych z Colissimo: wtedy pyta La Poste i Poste Italiane, we Włoszech najpierw Poste). Inne poprawne numery pocztowe na `IT` pokazują „Nieznany operator pocztowy” (Poste, potem [Chronopost](carrier:chronopost)); `X…IT` bez cyfry pocztowej (poza Chronopost) oraz kody SDA/Crono idą tylko do Poste (`000A00000000B`, `9C0000I000000`, `9C0000J000000` też do [AliExpress / Cainiao](carrier:cainiao)). Przy 12 poprawnych cyfrach sprawdza m.in. Poste, BRT i Colis Privé („Sprawdzanie usług śledzenia…”), a ręczny wybór daje „Zmień” / „Zmień przewoźnika”. Działają linki `poste.it` (`#/risultati-spedizioni/`) i `sda.it` (`tracing.letteraVettura=`) bez kodu pocztowego.

Z Polski Peek sprawdza też poprawne numery pocztowe na `IT` (`RR…IT`, `CW…IT`, `ZC…IT`) w [Poczcie Polskiej](carrier:poczta-polska) i przełącza na nią przy nowszych skanach; w drugą stronę 16-znakowe etykiety [Vinted Go](carrier:vinted-go) lub [Cainiao](carrier:cainiao) z kodem `1UW`, `3UW`, `8UW` albo `5P` przechodzą automatycznie na Poste Italiane.

Odpytywanie co godzinę i powiadomienia „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”. Peek pokazuje do 20 skanów na mapie, planowany dzień, usługę, „Opłaty importowe” i godziny lokalne („Godziny w Twojej strefie czasowej: …” na peektracker.com i iPhonie). Nadawcy, wagi, wymiarów ani adresu placówki Peek nie pokazuje. Po dwóch dniach bez rozpoznania: „Po dwóch dniach przewoźnik nadal nie zna tego numeru. Sprawdź go, wybierz innego przewoźnika lub usuń przesyłkę.”

## Najczęstsze pytania o śledzenie Poste Italiane

### Kod 1UW, 3UW lub 8UW: jaki to przewoźnik?

Poste Italiane: `1UW`, `3UW` i `8UW` z dziesięcioma znakami to kody paczek Poste Delivery Business, śledzone w „Cerca Spedizioni” albo na górze tej strony.

### Ile idzie paczka wysłana przez Poste Italiane?

Orientacyjnie we Włoszech: 4 dni robocze w Crono, 1–2 w Crono Express i 1–4 po nadaniu w Poste Delivery Express (bez sobót, płatnej opcji w Business Express). Zagranica: 2 dni do UE i 2–4 poza UE w International Express; 10–15 dni do UE i 10–25 poza UE w International Standard (bez sobót i świąt).

### Ile czasu paczka czeka w Punto Poste lub na poczcie?

W Poste Delivery Web: 7 dni roboczych w Punto Poste, 3 w automacie i 10 w urzędzie pocztowym; w Crono: 10 dni roboczych w Punto Poste; list polecony: 30 dni na poczcie (15 w Raccomandata 1). Wiążący jest termin z awizo lub SMS-a.

### Jak zadzwonić do Poste Italiane z Polski?

Pod `+39 06 4526 3160`, od poniedziałku do soboty w godz. 8:00–20:00 (poza włoskimi świętami; koszt według Twojego operatora); `803 160` działa tylko z włoskich telefonów stacjonarnych.

:::sources
- [Poste Italiane: FAQ Spedizioni](https://www.poste.it/assistenza/faq-spedizioni-e-consegne/) – kod, awizo, pełnomocnictwo, polecone
- [Poste Italiane: FAQ Delivery Web](https://www.poste.it/assistenza/faq-poste-delivery-web) – 2 próby, terminy (10/7/3), PIN, CP
- [Poste Italiane: Legenda stati](https://www.poste.it/cerca/views/risultati-spedizioni.html) – statusy, 12 cyfr, 2 i 24 miesiące
- [TG Poste: Tracking Poste](https://tgposte.poste.it/2022/03/09/tracking-poste-come-seguire-lo-stato-delle-spedizioni-di-poste-italiane/) – Dove Quando i sortownie
- [Poste Italiane: FAQ Scegli Tu](https://www.poste.it/assistenza/faq-scegli-tu) – zmiana doręczenia
- [Poste Italiane: FAQ PostePlus](https://www.poste.it/assistenza/faq-posteplus) – mieszkańcy Włoch
- [Poste Italiane: FAQ Punto Poste](https://www.poste.it/assistenza/faq-rete-punto-poste) – punkty i automaty
- [Poste Italiane: Chiamaci](https://www.poste.it/assistenza/chiamaci/) – numery, godziny i właściciel konta
- [Poste Italiane: Scrivici](https://www.poste.it/assistenza/scrivici) – e-mail i 06 4526 4526
- [Poste Italiane: Contact us](https://www.poste.it/assistenza/scrivici-en) – kontakt po angielsku
- [Poste Italiane: Reclami](https://www.poste.it/assistenza/reclami) – reklamacje i PEC
- [Poste Italiane: Raccomandata](https://www.poste.it/posta-raccomandata/raccomandata) – numer na awizo
- [Poste Italiane: Ritiro Digitale](https://www.poste.it/ritiro-digitale) – odbiór cyfrowy
- [Poste Italiane: Difendersi dalle truffe](https://www.poste.it/sicurezza-online/guide-per-operare-in-sicurezza/come-difendersi-dalle-truffe) – spoofing SMS
- [Poste Italiane: Delivery Express](https://www.poste.it/prodotti/poste-delivery-express.html) – 1–4 dni robocze
- [Poste Italiane: International Express](https://www.poste.it/prodotti/poste-delivery-international-express.html) – terminy i wyjątki
- [Poste Italiane: International Standard](https://www.poste.it/prodotti/poste-delivery-international-standard.html) – terminy
- [Poste Italiane: CGC Express](https://www.media.poste.it/d9e41964-551f-4f58-9887-6608322cdf87/web/poste-delivery-express-cgc) – dawne Paccocelere
- [Poste Italiane: CGC Standard](https://www.media.poste.it/e12354a8-ea52-44ce-a996-e83c184000ca/web/poste-delivery-standard-cgc) – dawne pacco ordinario
- [Poste Italiane: Carta non universali](https://www.media.poste.it/37381c9f-a87e-4292-8b89-00e88fa53323/web/carta-servizi-non-universali) – Crono, terminy, sobota
- [Poste Italiane: Carta universali](https://www.media.poste.it/c919f7cf-8236-428e-82fc-f8b2be8428b8/web/carta-servizi-universali) – Standard i Posta4
- [Poste Italiane: Posta Ordinaria](https://www.poste.it/lettera/posta-ordinaria) – brak śledzenia
- [Poste Italiane: Servizio Universale](https://www.poste.it/variazione-servizio-postale-universale) – wycofanie Posta1
- [Poste Italiane: Spedizioni da estero](https://www.poste.it/ricevere-spedizioni-da-estero) – odprawa i opłata 2 €
- [Poste Italiane: Aggiornamenti operativi](https://www.poste.it/aggiornamenti-operativi) – opóźnienia celne
- [Komisja Europejska: Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – cło 3 € i opłata 2 €
- [UE: Unia celna](https://european-union.europa.eu/priorities-and-actions/actions-topic/customs_pl) – brak ceł w UE
- [Poczta Polska: Śledź przesyłkę](https://www.poczta-polska.pl/sledzenie-przesylek/) – eMonitoring
- [Your Europe: Wysyłka i dostawa](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_pl.htm) – dostawa w 30 dni
- [Universal Parcel Scraper: Poste Italiane](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/poste-italiane) – zasady w Peek
:::
