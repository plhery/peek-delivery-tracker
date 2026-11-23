---
title: Śledzenie DPD France: numery, statusy i punkty Pickup
description: Śledź paczkę DPD France: formaty numerów, francuskie statusy po polsku, Predict, punkty i automaty Pickup, nieudane doręczenia oraz kontakt.
slug: sledzenie-dpd-fr
published: 2026-11-23
updated: 2026-11-23
---

DPD France (do 2015 roku Exapaq) to francuska sieć paczkowa grupy Geopost należącej do [La Poste](carrier:la-poste). Paczkę sprawdzisz na trace.dpd.fr, przez link z SMS-a albo e-maila od DPD lub wklejając numer w pole powyżej. Numer znajdziesz w potwierdzeniu wysyłki ze sklepu albo w wiadomości od DPD France.

## Numer śledzenia DPD France: gdzie go szukać

DPD France podaje, że numery paczek zaczynają się od `250`, `1` albo `0` i mają od 12 do 15 cyfr. Nie masz numeru? Według DPD dysponuje nim tylko nadawca — zapytaj sklep albo sprawdź [jak znaleźć numer śledzenia](guide:find-tracking-number).

| Format (wymyślony) | Co to jest |
| --- | --- |
| `250482917364058` | 15 cyfr zaczynających się od `250`: najczęstszy numer DPD France. |
| `2504829173640586` | 16 cyfr: ten sam numer plus cyfra kontrolna drukowana na etykiecie. |
| `10482917364058` | 14 cyfr na `10` (lub `11`–`19`, np. `14829173640582`): kolejny format DPD France. |
| `048291736405` | 12 cyfr zaczynających się od `0`: krótszy format DPD France. |

Śledzenie DPD przyjmuje też numer awiza (*avis de passage*) oraz kod SMS Predict i nie wymaga kodu pocztowego. Autentyczne SMS-y przychodzą od nadawcy „DPD France”.

Czternaście cyfr z literą na końcu (`48291736405829U`) należy do innych sieci DPD albo [Chronopost](carrier:chronopost), nie do DPD France. Paczki z [DPD UK](carrier:dpd-uk), [DPD Niemcy](carrier:dpd-de) lub innych spółek DPD trafiają we Francji czasem do Chronopost — DPD France wyświetla wtedy `Numéro de colis inconnu` (nieznany numer) i odsyła do Chronopost.

## Co oznaczają statusy śledzenia DPD France

Poniżej francuskie komunikaty, które wyświetla także Peek. trace.dpd.fr ma również wersję angielską (`Handed to DPD`, `In transit`, `At parcel delivery center`, `Out for delivery`, `Delivered`).

| Status DPD | Co oznacza dla ciebie |
| --- | --- |
| `Colis en préparation chez l'expéditeur` | Etykieta gotowa, ale sklep nie przekazał jeszcze paczki. |
| `Colis pris en charge par DPD France` | DPD France odebrało paczkę. |
| `Colis en cours d'acheminement` | W transporcie między sortownią a oddziałami. |
| `Colis en agence DPD France` | W oddziale doręczającym w twojej okolicy. |
| `Colis en livraison` | Dziś w doręczeniu u kuriera. |
| `Livraison reportée, prise de rendez-vous nécessaire` | Doręczenie przełożone — wybierz nowy termin. |
| `Colis à disposition du destinataire au point relais Pickup` | Czeka do odbioru w punkcie Pickup. |
| `Colis à disposition à l'agence DPD` | Czeka do odbioru w oddziale DPD. |
| `Colis refusé, en attente de vos instructions` | Odmowa przyjęcia; DPD czeka na dyspozycje nadawcy. |
| `Colis livré` | Doręczona pod adres lub odebrana w punkcie Pickup. |
| `Colis en retour vers l'adresse d'expédition, en attente de livraison chez l'expéditeur` | Wraca do nadawcy. |

Dopóki paczka nie trafi do DPD, informacji udziela tylko nadawca. `Colis en agence DPD France` może pojawiać się kilkakrotnie podczas pobytu w oddziale. `Numéro de colis inconnu` zwykle oznacza, że DPD jeszcze jej nie zeskanowało: odczekaj kilka godzin i sprawdź numer.

## Doręczenie DPD France: Predict, punkty Pickup i automaty

DPD France doręcza od poniedziałku do piątku 8:00–18:00 oraz w soboty 8:00–15:00 (czasu francuskiego) w wybranych kodach pocztowych.

**Predict.** Jeśli nadawca zamówił usługę, otrzymujesz SMS i e-mail z planowanym dniem, a w dniu doręczenia 3-godzinny przedział. Przez link lub aplikację myDPD możesz do 23:00 (czasu francuskiego) w dniu wiadomości wybrać inny dzień lub adres, punkt Pickup, oddział, sąsiada albo bezpieczne miejsce (za zgodą nadawcy).

**Nieudane doręczenie.** Kurier zostawia awizo w skrzynce — nowy termin ustalisz online. Z Predict paczka może trafić od razu do pobliskiego punktu Pickup. DPD France nie podaje stałej liczby prób: regulamin przewiduje kolejną próbę następnego dnia lub w wybranym terminie.

**Punkty Pickup.** Przechowują paczkę 9 dni kalendarzowych. Zabierz dowód tożsamości: DPD zastrzega, że sam kod QR Pickup Pass albo numer paczki nie wystarczą. Inna osoba potrzebuje upoważnienia, twojego dowodu i własnego.

**Automaty Pickup.** W większości całodobowe, przyjmują paczki do 20 kg i otwierają się kodem lub kodem QR od DPD. Czas przechowywania: 5 dni kalendarzowych.

**W oddziale DPD.** Masz 6 dni roboczych według FAQ (5 w regulaminie), z dowodem tożsamości i numerem paczki lub awiza. Nieodebrane paczki wracają do nadawcy.

## Paczka DPD France opóźniona, zaginiona lub uszkodzona

W razie opóźnienia albo uznania paczki za zaginioną DPD France odsyła do nadawcy będącego stroną umowy; terminy doręczeń nie są gwarantowane. Zobacz [śledzenie nie aktualizuje się](guide:tracking-not-updating).

Status to `Colis livré`, ale paczki nie ma, albo nie dotarła do punktu Pickup? Obsługa klienta może wszcząć poszukiwania; zobacz [doręczona, ale nie otrzymana](guide:delivered-not-received).

Uszkodzona lub niekompletna: odmów odbioru z podaniem przyczyny (wróci do nadawcy) albo przyjmij z precyzyjnymi, datowanymi i podpisanymi zastrzeżeniami. Następnie wyślij do DPD France list polecony za potwierdzeniem odbioru z 4 zdjęciami. FAQ daje odbiorcom prywatnym we Francji 10 dni roboczych, a regulamin tylko 3 — wyślij pismo jak najszybciej i zachowaj towar przez 15 dni.

## Numer kontaktowy i obsługa klienta DPD France

Kanały na [stronie obsługi klienta DPD France](https://www.dpd.com/fr/fr/contact/service-clients/):

- **Telefon:** 09 70 80 85 66 (+33 9 70 80 85 66 z zagranicy), płatny jak zwykłe połączenie, poniedziałek–piątek 8:00–20:00 i sobota 10:00–18:00 (czasu francuskiego), bez świąt. DPD określa go jako jedyny oficjalny numer obsługi klienta.
- **Formularz kontaktowy** na tej samej stronie, wymagający numeru paczki lub awiza i kodu pocztowego doręczenia. Brak publicznego e-maila.
- **Wiadomości prywatne** przez WhatsApp, Messenger lub X, podlinkowane na tej samej stronie.

Obejmują one sieć DPD France; dla Belgii ani Szwajcarii nie ma odrębnych numerów.

> DPD France nigdy nie pisze z numerów 06 ani 07 i nie żąda dopłat za doręczenie. Oficjalne linki zaczynają się od my.dpd.fr. Podejrzane SMS-y we Francji przesyłaj na 33700.

## Śledzenie paczek DPD France w Peek

[Peek](/) rozpoznaje od razu 15-cyfrowy numer zaczynający się od `250`. Inne formaty współdzielą różni przewoźnicy: przy 14 cyfrach na `11`–`19` (`14829173640582`) Peek pyta DPD France równolegle z innymi pasującymi firmami. Przy 14 cyfrach na `10` (`10482917364058`) i 16-cyfrowym numerze z etykiety (`2504829173640586`) Peek sprawdza najpierw szybkich przewoźników (SEUR, BRT, Ciblex i Jadlog dla `10…`) oraz usługi śledzenia; jeśli żaden nie znajdzie paczki, otwiera stronę DPD France w przeglądarce (co może zająć do 20 sekund więcej) i wybiera DPD France, gdy pojawią się świeże datowane skany. Dla pozostałych wzorów (jak 12 cyfr na `0`) kliknij „Zmień” i wybierz DPD France. Działają linki trace.dpd.fr (`/fr/trace/` i `/trace-particuliers/`); inne linki działają tylko z 15-cyfrowym numerem `250`, a linki dpd.com lub dpdgroup.com z `parcelNumber=` trafiają do DPD France przy `/fr/` i do [DPD Szwajcaria](carrier:dpd-ch) przy `/ch/`.

Peek pokazuje status, historię z oddziałem każdego skanu, przewidywany dzień, wagę i godzinę doręczenia oraz zaznacza oddział doręczający na mapie. Opisy pozostają po francusku; godziny odczytywane są w czasie paryskim i przeliczane na twoją strefę czasową. Imię i nazwisko, adres, podpis, numer klienta oraz nazwa i adres punktu Pickup są pomijane (widać tylko „Do odbioru”). Gdy DPD pokazuje drogę tam i zwrot, Peek zachowuje trasę twojego numeru i nie przełącza na innego przewoźnika.

Dopóki masz paczkę otwartą w Peek albo w dniu doręczenia przy włączonych powiadomieniach, Peek sprawdza status do co 10 minut — a od 6:00 (czasu francuskiego) w dniu, gdy DPD France zeskanuje paczkę w doręczeniu, co 2 minuty (co 5 minut, gdy musi otworzyć śledzenie DPD France w przeglądarce), dopóki odpowiada własne śledzenie DPD France (przez usługi śledzenia w tych samych warunkach do co 15 minut). Po dwóch dniach bez zmian (12 godzinach, gdy przesyłka nadal ma status „Zarejestrowana” lub „Przyjęta przez przewoźnika”) Peek sprawdza co godzinę, a po siedmiu dniach na etapie „Zarejestrowana” — co 6 godzin. Jeśli paczka na etapie „Zarejestrowana”, „Przyjęta przez przewoźnika” lub „W transporcie” nie ma nowego skanu przez cztery dni, Peek pokazuje „Brak aktualizacji od czterech dni” (lub „Brak aktualizacji od…” z datą), komunikat „Przesyłki często milkną podczas długich lotów, w urzędzie celnym albo w weekend. Peek dalej pyta przewoźnika DPD France.” oraz link „Poproś przewoźnika DPD France o pomoc”. Peek wysyła powiadomienia w trybie „Każdy skan”, „Ważne etapy” lub „Tylko doręczenie”.

## Pytania o śledzenie DPD France

### Gdzie jest moja paczka DPD France?

Mówi o tym ostatni wpis: `Colis en agence DPD France` oznacza lokalny oddział, a `Colis en livraison` — doręczenie przez kuriera.

### Jak długo DPD France przechowuje paczki?

9 dni kalendarzowych w punkcie Pickup, 5 dni kalendarzowych w automacie Pickup i około tygodnia w oddziale; potem paczka wraca do nadawcy.

### Ile trwa doręczenie DPD France?

DPD France podaje 24 do 48 godzin od nadania na terenie Francji.

### Czy w DPD France można porozmawiać z konsultantem?

Tak: zadzwoń pod 09 70 80 85 66 w godzinach pracy albo napisz przez formularz lub WhatsApp na stronie kontaktowej.

:::sources
- [DPD France: Où puis-je trouver mon numéro de colis ?](https://www.dpd.com/fr/en/faq/ou-puis-je-trouver-mon-numero-de-colis/) – formaty numerów i miejsce znalezienia
- [DPD France: Comment puis-je suivre la livraison de mon colis ?](https://www.dpd.com/fr/en/faq/comment-puis-je-suivre-la-livraison-de-mon-colis/) – numer awiza i kod Predict
- [DPD France: Suivi colis DPD](https://www.dpd.com/fr/fr/recevoir-des-colis/) – zakres danych w śledzeniu
- [DPD France: Pourquoi mon suivi colis indique-t-il « numéro de colis inconnu » ?](https://www.dpd.com/fr/fr/faq/pourquoi-mon-suivi-colis-indique-t-il-numero-de-colis-inconnu/) – nieznany numer i Chronopost
- [DPD France: Quand mon colis sera-t-il livré ?](https://www.dpd.com/fr/en/faq/quand-mon-colis-sera-t-il-livre-2/) – godziny, 24–48 godz., okno 3-godzinne
- [DPD France: Dois-je répondre au sms ?](https://www.dpd.com/fr/en/faq/dois-je-repondre-au-sms-que-jai-recu-pour-ma-livraison-a-domicile/) – powiadomienia Predict
- [DPD France: Comment re-planifier la livraison de mon colis ?](https://www.dpd.com/fr/en/faq/comment-re-planifier-la-livraison-de-mon-colis/) – limit 23:00, awizo, punkt Pickup
- [DPD France: myDPD, my way](https://www.dpd.com/fr/en/mydpd-my-way/) – opcje doręczenia
- [DPD France: Livraison à domicile DPD Predict](https://www.dpd.com/fr/fr/expedier-des-colis/domicile-predict/) – opcje i aplikacja myDPD
- [DPD France: Pendant combien de temps mon colis est-il gardé en relais Pickup ?](https://www.dpd.com/fr/en/faq/pendant-combien-de-temps-mon-colis-est-il-garde-en-relais-pickup/) – 9 dni kalendarzowych
- [DPD France: Retrait en agence](https://www.dpd.com/fr/en/faq/suite-a-une-demande-de-retrait-en-agence-pendant-combien-de-temps-mon-colis-est-il-garde-en-agence-d/) – 6 dni roboczych
- [DPD France: Carte d'identité en relais Pickup](https://www.dpd.com/fr/en/faq/devrai-je-presenter-ma-carte-didentite-lors-du-retrait-dun-colis-dans-un-relais-pickup/) – wymóg dokumentu
- [DPD France: Relais et consignes Pickup](https://www.dpd.com/fr/fr/expedier-des-colis/relais-consignes/) – automaty i limit 20 kg
- [DPD France: Conditions Générales de Vente](https://www.dpd.com/fr/fr/conditions-generales-vente/) – ponowna próba, terminy, upoważnienie, reklamacje
- [DPD France: Retard de livraison](https://www.dpd.com/fr/en/faq/suis-je-indemnise-en-cas-de-retard-de-livraison/) – kontakt z nadawcą
- [DPD France: Perte totale](https://www.dpd.com/fr/en/faq/comment-proceder-en-cas-de-perte-totale-de-ma-commande/) – zaginięcie przez nadawcę
- [DPD France: Colis endommagé ou incomplet](https://www.dpd.com/fr/fr/faq/que-dois-je-faire-si-mon-colis-arrive-endommage-ou-incomplet/) – odmowa, zastrzeżenia, polecony, terminy
- [DPD France: Réserves en relais Pickup](https://www.dpd.com/fr/en/faq/puis-je-emettre-des-reserves-si-mon-colis-est-endommage-au-relais-pickup/) – zastrzeżenia w punkcie Pickup
- [DPD France: Colis absent du relais](https://www.dpd.com/fr/en/faq/comment-faire-si-mon-colis-nest-pas-au-relais-pickup/) – poszukiwanie przez BOK
- [DPD France: Pas d'avis de passage](https://www.dpd.com/fr/en/faq/ma-livraison-est-prevue-ce-jour-mais-je-nai-pas-recu-davis-de-passage-que-faire/) – kontakt z BOK
- [DPD France: Contacter notre Service Clients](https://www.dpd.com/fr/fr/contact/service-clients/) – godziny, formularz, komunikatory
- [DPD France: Comment contacter le Service Clients ?](https://www.dpd.com/fr/en/faq/comment-contacter-le-service-clients-dpd-france/) – telefon, brak e-maila
- [DPD France: Alerte aux fraudes](https://www.dpd.com/fr/fr/alerte-aux-fraudes/) – fałszywe SMS-y, 33700
- [DPD France: Scam alert](https://www.dpd.com/fr/en/scam-alert/) – numer z zagranicy
- [DPD France: About DPD France](https://www.dpd.com/fr/en/about-dpd-france/) – Geopost, La Poste, Exapaq
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – rozpoznawanie, sprawdzanie w przeglądarce i dane w Peek
:::
