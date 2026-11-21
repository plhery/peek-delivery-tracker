---
title: PostNL Sendungsverfolgung: 3S- und NL-Nummern & Status
description: PostNL-Sendung außerhalb der Niederlande verfolgen: Aufbau von NL- und 3S-Nummern, Bedeutung der Status auf postnl.post und wer das Paket bei dir zustellt.
slug: postnl-sendungsverfolgung
published: 2026-11-17
updated: 2026-11-21
---

PostNL ist die niederländische Post. Sie befördert Pakete und Briefe niederländischer Online-Shops ins Ausland und transportiert über ihre internationale Tochter Spring GDS weltweit Sendungen für Online-Händler. Beide verfolgst du auf der internationalen Seite von PostNL, [postnl.post](https://postnl.post/) (auf Englisch), oder oben im Suchfeld. In deinem Land übernimmt anschließend oft der nationale Postdienst oder ein lokaler Partner die Zustellung. Die Nummer steht in der Versandbestätigung des Shops oder in deiner Bestellübersicht; fehlt sie, hilft [Sendungsnummer finden](guide:find-tracking-number).

## PostNL-Sendungsnummer: Aufbau und Beispiele

Laut ihrer Entwicklerdokumentation nutzt PostNL zwei Arten von Barcodes (mit frei erfundenen Ziffern):

| Nummer (erfunden) | Was dahintersteckt |
| --- | --- |
| `CW482910576NL` | Zwei Buchstaben, 9 Ziffern, dann `NL`: internationale S10-Postnummer von PostNL. Ein `C` am Anfang (etwa `CW`, `CC`, `CD` oder `CP`) steht für ein *Parcels Non-EU*-Paket außerhalb der EU, zum Beispiel in die Schweiz. |
| `LA482910576NL` | `L`: kleines Päckchen oder Brief mit Sendungsverfolgung (*Boxable* oder *Packet Track & Trace*). `LW` ist derselbe Dienst. |
| `RI482910576NL` | `R`: Einschreiben (*Registered Letter*). `RF` ist derselbe Dienst. |
| `UL482910576NL` | `U`: nicht verfolgbare Kleinsendung (*Untracked*, auch `UE` oder `UM`). Im Zielland gibt es damit meist keine Scans. |
| `3SABCD482910576` | `3S`, ein Kundenkürzel aus 1 bis 4 Buchstaben und Ziffern, insgesamt 15 Zeichen: ein Paket-Barcode von PostNL. |
| `3SABCD4829105` | Derselbe `3S`-Barcode in der 13-stelligen Form. PostNL nutzt ihn für *Parcel EU*, seinen Paketdienst aus den Niederlanden in andere EU-Staaten wie Deutschland, Österreich und Luxemburg. |

Die neunte Ziffer einer `NL`-Nummer ist eine S10-Prüfziffer: Schon ein einzelner Zahlendreher macht die Nummer in der Regel ungültig.

Auf postnl.post lassen sich bis zu 150 Sendungsnummern gleichzeitig abfragen. Das Portal beantwortet `NL`-Nummern und 15-stellige `3S`-Barcodes, lehnt 13-stellige `3S`-Codes jedoch mit `The shipment barcode was incorrect.` ab. Frag bei einem 13-stelligen *Parcel EU*-Code den Shop nach dem Barcode des Zustellpartners (*DownPartnerBarcode*): Sobald der Händler ein *Parcel EU*-Etikett erzeugt, übermittelt PostNL ihm die Sendungsnummer des ausländischen Partners, der das Paket zustellt. Solange sich die Sendung noch in den Niederlanden befindet, kann sich diese Partnernummer allerdings noch ändern.

## PostNL-Sendungsstatus auf postnl.post erklärt

postnl.post zeigt oben eine Fortschrittsleiste mit `Pre-advised`, `Preparing`, `Departed`, `Transit`, `Arrived`, `Customs`, `Processing` und `Delivered` sowie darunter die einzelnen Scans auf Englisch. Fast jeder Scan nennt das Land, in dem er stattgefunden hat. Die wichtigsten Meldungen in typischer Reihenfolge:

| postnl.post schreibt | Was das für dich bedeutet |
| --- | --- |
| `Pre-advised`, `Preparing` | Der Absender hat die Sendung angemeldet und die Daten an PostNL geschickt; das Paket ist vielleicht noch nicht übergeben. |
| `Consignment received at the PostNL Acceptance Centre`, `The shipment is handed over in bulk final acceptance of the item to be confirmed` | PostNL hat die Sammellieferung des Händlers angenommen; der Einzelscan deines Pakets kann noch folgen. |
| `The item is received by PostNL` | PostNL hat deine Sendung einzeln gescannt – teils erst Tage nach der Sammelannahme. |
| `Departed`, `Transit` | Unterwegs zwischen zwei Ländern, oft per Flugzeug. |
| `Arrived` | In einem Land auf der Route eingetroffen, meistens im Zielland. |
| `Customs` | Beim Zoll im Bestimmungsland. |
| `The item has arrived at the domestic sorting centre`, `The item is at the local sorting centre` | Wird in einem Sortierzentrum des angegebenen Landes bearbeitet. |
| `The item is at the local delivery office` | In der Zustellbasis eingetroffen, die dein Paket ausliefert. |
| `The item is out for delivery` | Auf dem Zustellfahrzeug zu dir unterwegs. |
| `Driver is en route to the pickup location` | Auf dem Weg zu einer Abholstelle, nicht an deine Haustür. |
| `Pick-up point` | Liegt in einer Abholstelle zur Abholung bereit. |
| `Unsuccesfull`, `Undelivered` | Fehlgeschlagener Zustellversuch (die Schreibweise stammt von PostNL). |
| `Undeliverable item, has been returned to shipper` | Unzustellbar, geht zurück an den Absender. |
| `The item has been returned and arrived at PostNL` | Die Rücksendung ist wieder bei PostNL eingetroffen. |
| `Delivered` | Zugestellt. |

`The shipment barcode was not found.` heißt, dass postnl.post zu dieser Nummer noch keine Daten hat oder ein Zeichen falsch getippt wurde. „Destination: unknown“ bedeutet, dass noch kein Scan das Zielland genannt hat. Gibst du in Peek zu einem `3S`-Paket die Postleitzahl der Lieferadresse an, liest Peek zusätzlich PostNLs eigene Sendungsverfolgung aus; bei Paketen ins Ausland lauten deren englische Meldungen unter anderem `Your parcel has crossed the border`, `Your parcel's at a sorting centre in the destination country`, `Your shipment is on its way to the recipient` (in Zustellung) und `We missed you; we’ll try again` (fehlgeschlagener Zustellversuch).

## Wer stellt PostNL-Pakete in Deutschland, Österreich und der Schweiz zu?

PostNL oder Spring GDS bringt das Paket in dein Land. Spring GDS schreibt, dass es die internationale Strecke übernimmt, während ein lokaler Partner die Schlusszustellung erledigt, und dass die Sendung nach der Einreise häufig an den örtlichen Postdienst übergeben wird (als Beispiele nennt Spring GDS USPS, Royal Mail und Correos).

- **`NL`-Nummer (S10-Standard):** Dieser 13-stellige Code des Weltpostvereins (UPU) wird von Postgesellschaften untereinander genutzt. Sobald das Paket im Zielland angekommen ist, kannst du dieselbe Nummer beim jeweiligen Postdienst ausprobieren – etwa bei [DHL](carrier:dhl) in Deutschland, der [Österreichischen Post](carrier:austrian-post) in Österreich, der [Schweizerischen Post](carrier:swiss-post) in der Schweiz oder [POST Luxembourg](carrier:post-luxembourg) in Luxemburg. Eine nicht verfolgbare `UL`-Sendung erscheint dort unter Umständen gar nicht.
- **`3S`-Code (*Parcel EU*):** In Deutschland, Österreich und Luxemburg stellt ein ausländischer Partner von PostNL zu, den PostNL für die Haustürzustellung nicht namentlich aufführt; die Partnernummer erhält der Shop beim Erstellen des Labels. Für *International Pickup Points* in Deutschland nennt PostNL in seiner API-Dokumentation ausdrücklich die Zustellung an Paketshops über [Hermes](carrier:hermes-de) (Pakete bis 25 kg und maximal 120 cm, berechnet aus kürzester und längster Seite; Benachrichtigung per E-Mail oder SMS).
- **Nicht-EU-Sendungen (*Parcels Non-EU*):** Pakete in die Schweiz erhalten einen S10-Barcode mit `C` am Anfang (`CW…NL`).

Zustellversuche, Abholkarten und Lagerfristen richten sich nach dem jeweiligen Zusteller vor Ort; lies dazu [Zustellung verpasst](guide:missed-delivery) und [Paket nicht abgeholt](guide:uncollected-parcel). Einfuhrabgaben – etwa bei Sendungen in die Schweiz oder von Händlern außerhalb der EU – erklärt [Paket hängt beim Zoll](guide:customs): Laut Spring GDS handelt es sich um staatliche Abgaben, und der Zolldienstleister meldet sich per E-Mail oder SMS, wenn eine Rechnung oder ein Nachweis fehlt.

## PostNL-Sendung hängt, verloren oder zurückgeschickt

**Noch kein Scan sichtbar.** Bei `Pre-advised` oder `Preparing` hat der Händler das Etikett gebucht, das Paket aber womöglich noch nicht übergeben. Spring GDS empfiehlt, nach Erhalt der Nummer 24 bis 48 Stunden auf den ersten Scan zu warten.

**Keine Bewegung unterwegs.** Nach `Departed` oder `Transit` gibt es auf Luft- oder Seestrecken bis zur Ankunft keine Scans, und die Zollabfertigung (`Customs`) dauert oft mehrere Tage. Prüfe danach eine `NL`-Nummer beim nationalen Postdienst. Bleibt ein Paket auf „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“ vier Tage ohne neuen Scan, zeigt Peek in deiner Liste den Tag des letzten Scans („Keine Aktualisierung seit Mo 12. Okt“) und auf der Paketseite: „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei PostNL nach.“, samt dem Link „Hol dir Hilfe bei PostNL“. Nach drei Wochen ohne Scan steht dort stattdessen: „Seit über drei Wochen nichts Neues. Ist das Paket noch nicht angekommen, frag beim Verkäufer nach. Peek fragt weiter bei PostNL nach.“ Mehr unter [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating).

**Als zugestellt gemeldet, aber nicht da.** Spring GDS rät, bei Nachbarn nachzufragen, typische Ablageorte wie Veranda oder Garage sowie Empfang oder Pförtner zu prüfen, und weist darauf hin, dass Zusteller Sendungen mitunter bis zu 24 Stunden vor der tatsächlichen Ankunft als `Delivered` markieren. Taucht nichts auf, kontaktiere den Verkäufer und lies [zugestellt, aber nicht erhalten](guide:delivered-not-received).

**Verloren oder beschädigt.** Melde dich sofort beim Händler und fotografiere beschädigte Verpackungen: Laut Spring GDS benötigt der Verkäufer diese Fotos, um eine Reklamation einzureichen. Siehe [Paket verloren](guide:lost-parcel).

**An den Absender zurück.** Bitte den Shop um erneuten Versand oder Erstattung. Als häufige Gründe nennt Spring GDS eine falsche Adresse (*Incorrect Address*) und nicht rechtzeitig abgeholte Pakete (*Unclaimed*); eine bereits eingeleitete Rücksendung lässt sich nicht mehr stoppen. Siehe [zurück an den Absender](guide:returned-to-sender).

## PostNL Kontakt und Kundenservice

Stand der offiziellen Seiten:

- **postnl.post:** Bei Fragen zu Lieferungen außerhalb der Niederlande verweist das Portal an den Online-Shop (*„Questions about your delivery outside The Netherlands? Please contact your webshop.“*). Für Sendungen innerhalb der Niederlande verweist es auf den Kundenservice von PostNL.
- **Spring GDS:** Aus Datenschutzgründen und wegen der Verträge mit den Händlern gibt Spring GDS Empfängern keine Auskunft zu einzelnen Sendungen. Nachforschungen eröffnet immer der Verkäufer als Vertragspartner.
- **Zusteller vor Ort:** Wegen einer verpassten Zustellung oder eines neuen Termins wende dich direkt an den lokalen Zustelldienst; Spring GDS kann keine erneuten Zustellungen vor Ort veranlassen.

Für alle übrigen Anliegen ist der Shop oder Marktplatz, bei dem du bestellt hast, der schnellste Weg.

## PostNL-Paket mit Peek verfolgen

[Peek](/) erkennt `NL`-Nummern mit gültiger S10-Prüfziffer sowie `3S`-Barcodes mit vierstelligem Buchstaben-Kundencode sofort. Ein `3S`-Code mit drei Buchstaben wird DHL eCommerce Netherlands zugeordnet, obwohl auch die Richtlinien von PostNL dreistellige Buchstabencodes vorsehen. Bei ein oder zwei Buchstaben lässt sich der Paketdienst nicht allein an der Form ablesen (bei zwei Buchstaben fragt Peek auch DHL eCommerce Netherlands an): Füge den Code trotzdem ein, Peek unterstützt über 4.500 Paketdienste. Während der Prüfung erscheint „Tracking-Dienste werden geprüft…“; liegt ein anderer Dienst vorn, tippe auf „Ändern“ und wähle PostNL. Links von postnl.post (`/track?barcodes=` oder `/details/…`) und mailingtechnology.com (`/tracking?tn=`), dem Tracker von Spring GDS, liest Peek direkt als PostNL. Bei einem `3S`-Barcode erscheint zudem das optionale Feld „Postleitzahl der Lieferadresse“: „PostNL zeigt eine innerhalb der Niederlande verschickte Sendung nur mit der Postleitzahl der Lieferadresse an.“ Mit Postleitzahl (außerhalb der Niederlande zusammen mit „Lieferland“) fragt Peek zuerst PostNLs eigenen Tracker ab; ohne sie springen andere Quellen ein.

Peek liest postnl.post und – wenn die Postleitzahl hinterlegt ist – PostNLs eigenen Tracker aus. Angezeigt werden der Status, der Verlauf mit dem Land jedes Scans, die Zustellzeit und das Zielland. Die Uhrzeiten rechnet Peek in die Zeitzone deines Geräts um (unter dem Verlauf steht „Uhrzeiten in deiner Zeitzone: …“). Ein voraussichtliches Lieferdatum oder den Absendernamen liefert PostNL nicht. Bei einer `NL`-Nummer fragt Peek, sobald PostNL das Zielland nennt (oder sonst anhand deines Landes – der Geräteregion in der iPhone- oder Android-App bzw. des Verbindungslandes im Browser), mit derselben Nummer den nationalen Postdienst an – [DHL](carrier:dhl) in Deutschland, die [Österreichische Post](carrier:austrian-post) in Österreich, die [Schweizerische Post](carrier:swiss-post) in der Schweiz oder [POST Luxembourg](carrier:post-luxembourg) in Luxemburg – und wechselt dorthin, sobald dieser neuere Fortschritte meldet („Zustellung durch …“). Da postnl.post 13-stellige `3S`-Codes als ungültig ablehnt, sucht Peek sie ohne Postleitzahl über [universelle Tracking-Dienste](guide:universal-tracker).

Peek prüft PostNL bis zu alle 30 Minuten, auch wenn das Paket in Zustellung ist; ein manuelles Aktualisieren kann früher nachsehen. Übernimmt ein anderer Zusteller die Auslieferung, folgt Peek dem Abfragetakt dieses Zustellers. Benachrichtigungen kannst du auf „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“ stellen. PostNL oder den Shop kontaktieren kann Peek nicht für dich.

## Häufige Fragen zur PostNL-Sendungsverfolgung

### Wo befindet sich mein PostNL-Paket gerade?

Der jüngste Scan nennt den aktuellen Schritt und das Land, in dem sich die Sendung befindet. Sobald bei einer `NL`-Nummer dein Land auftaucht, probiere dieselbe Nummer auf der Website deines nationalen Postdienstes aus – dort stehen meist genauere lokale Scans.

### Was bedeutet „The shipment barcode was incorrect“ bei PostNL?

postnl.post akzeptiert diesen Barcode nicht. Meist handelt es sich um einen 13-stelligen `3S`-Code des *Parcel EU*-Dienstes von PostNL. Frag beim Online-Shop nach der Sendungsnummer des Zustellpartners vor Ort und verfolge das Paket dort.

### Stellt DHL, die Österreichische Post oder die Schweizerische Post PostNL-Pakete zu?

Häufig ja. Spring GDS, die internationale Sparte von PostNL, gibt an, dass Sendungen im Zielland oft an den örtlichen Postdienst übergeben werden, und `NL`-Nummern folgen dem gemeinsamen S10-Poststandard. *Parcel EU*-Pakete mit 13-stelligem `3S`-Code stellt dagegen ein ausländischer Partner von PostNL zu (für Abholstellen-Sendungen in Deutschland nennt PostNL ausdrücklich Hermes).

:::sources
- [PostNL: International Tracking](https://postnl.post/) – bis zu 150 Nummern, Fortschrittsleiste, Meldungen „not found“ und „incorrect“, Zielland und Verweis an den Webshop
- [PostNL Developer: Barcode guidelines](https://developer.postnl.nl/docs/#/http/api-endpoints/send-track/barcode/guidelines) – 13- und 15-stellige 3S-Barcodes, Buchstaben-Kundencode und S10-Nummern auf NL
- [PostNL Developer: Product codes International](https://developer.postnl.nl/docs/#/http/reference-data/product-codes-international) – Parcel EU mit 13-stelligem 3S-Code (DE, AT, LU), Partner-Barcode, deutsche Abholstellen über Hermes bis 25 kg, Parcels Non-EU mit C/CW, LA/LW, RI/RF und UL/UE/UM
- [Spring GDS: We are Spring GDS](https://www.spring-gds.com/about-us/) – internationale E-Commerce-Tochter der PostNL-Gruppe
- [Spring GDS: Track and Trace](https://www.spring-gds.com/track/) – Übergabe an lokalen Postdienst, lokaler Partner, 24–48 Stunden bis zum ersten Scan, Pausen im Transit und beim Zoll, zugestellt aber nicht erhalten, Schadensfotos, Rücksendungen und kein Endkundenkontakt
- [Spring GDS: Contact us](https://www.spring-gds.com/contact-us/) – Rückfragen zur Sendungsverfolgung gehen an den Webshop
- [UPU: S10-Standard, Identification of postal items (PDF)](https://www.upu.int/UPU/media/upu/files/postalSolutions/programmesAndServices/standards/S10-12.pdf) – gemeinsames 13-stelliges Nummernsystem der Postgesellschaften und Prüfziffer
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Nummernerkennung, optionale 3S-Postleitzahl, Übergabe an nationale Postdienste und 30-Minuten-Intervall in Peek
:::
