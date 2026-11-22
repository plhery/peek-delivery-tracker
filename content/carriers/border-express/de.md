---
title: Border Express (BEX) Sendungsverfolgung: Nummern & Status
description: Border Express (BEX) Paket in Australien verfolgen: wie Consignment-Nummern und Etiketten-Barcodes aussehen, was jeder Status bedeutet und wie Peek hilft.
slug: border-express-sendungsverfolgung
published: 2026-11-22
updated: 2026-11-22
---

Border Express, kurz BEX, ist ein Paket- und Frachtdienst, den Peeks Katalog ausschließlich in Australien führt. Hat ein Online-Shop deine Bestellung über Border Express verschickt, ist die maßgebliche Nummer die Consignment-Nummer: Füge sie oben ins Feld ein. Der Shop nennt sie in der Versand-E-Mail oder auf deiner Bestellseite. Der eigene Tracker von Border Express ist Quick Track auf BEX Online (`bexonline.borderexpress.com.au`). Da Peek die Website von Border Express für diese Seite nicht abrufen konnte, stützt sich das Folgende auf das, was Peeks Code über diesen Tracker weiß.

## Border Express Sendungsnummer: wie sie aussieht

Border Express verfolgt eine Lieferung über ihre Consignment-Nummer. Peeks Code kennt drei Formen. Mit erfundenen Zeichen:

| Nummer (erfunden) | Was es ist |
| --- | --- |
| `48291736` | 8 Ziffern: eine Consignment-Nummer. |
| `KLMN482917` | 4 Buchstaben (Absender-Präfix), gefolgt von Ziffern: ebenfalls eine Consignment-Nummer. Peek akzeptiert 5 bis 8 Ziffern nach den Buchstaben. |
| `KLMNBX00000482917001` | 20 Zeichen: der Barcode auf dem Etikett eines einzelnen Packstücks. Die 4 Absender-Buchstaben, `BX`, die Ziffern der Sendung mit führenden Nullen auf 11 Stellen aufgefüllt, sowie eine 3-stellige Stücknummer. |

Eine Sendung kann mehrere Packstücke umfassen, jedes mit eigenem Etiketten-Barcode. Alle Etiketten beginnen mit denselben Buchstaben und Ziffern, und die Consignment-Nummer fasst sie zusammen: Nach ihr sucht der Border-Express-Tracker, der keine Etiketten-Barcodes annimmt. Peek nimmt sie hingegen an und leitet die Consignment-Nummer daraus ab (siehe unten).

Allein anhand der Form kann Peek eine Border-Express-Nummer nicht erkennen: Im Katalog gibt es keine Regel für die Consignment-Nummern, und die Regel für den Etiketten-Barcode ist nur ein schwacher Treffer. Andere Dienste nutzen dieselben Formen: Bei 8 Ziffern schlägt Peek [Mondial Relay](carrier:mondial-relay), Colisweb und [Heppner](carrier:heppner) in Europa vor, sowie TForce Freight in Nordamerika, wenn die Prüfziffer passt. Vier Buchstaben mit 8 Ziffern passen auch zu den 12-stelligen Nummern von Colis Privé. Nennt der Shop Border Express, wähle Border Express manuell aus (unten). Leerzeichen und Kleinbuchstaben spielen beim Einfügen keine Rolle. Gar keine Nummer? Siehe [Sendungsnummer finden](guide:find-tracking-number) und [Sendungsnummer-Formate](guide:tracking-number-formats).

## Border Express Tracking-Status erklärt

Der Border-Express-Tracker zeigt eine Fortschrittsleiste und eine Liste der Schritte, von neu nach alt, jeweils mit Vorort und Bundesstaat. Diese Schritte kennt Peeks Code in typischer Reihenfolge:

| Border Express schreibt | Was das für dich bedeutet | Peek zeigt |
| --- | --- | --- |
| `Shipment Data Received` | Der Absender hat die Sendungsdaten an Border Express übermittelt. Border Express hat das Paket noch nicht. Die Fortschrittsleiste zeigt `Data Received`. | „Etikett erstellt“ |
| `Picked Up` | Border Express hat das Paket beim Absender abgeholt. Der Tracker listet diesen Schritt meist ohne Uhrzeit, und Peek lässt zeitlose Schritte weg. | „Beim Paketdienst eingegangen“, nur wenn der Schritt eine Zeit hat |
| `In Transit` | Unterwegs im Netzwerk von Border Express. | „Unterwegs“ |
| `On Board For Delivery` | Im Zustellfahrzeug. Die Fortschrittsleiste zeigt `On Board`. | „In Zustellung“ |
| `Partially Delivered` | Ein Teil der Packstücke ist zugestellt, der Rest ist noch unterwegs. | „Unterwegs“ |
| `Partially Delivered - Final` | Die Zustellung ist abgeschlossen: Die Sendung wird mit den eingetroffenen Packstücken geschlossen. Fehlt etwas, frage den Absender. | „Zugestellt“ |
| `Delivered` | Zugestellt. | „Zugestellt“ |

Peek behält den englischen Wortlaut von Border Express mit seinem eigenen Status darüber bei. Ein Schritt außerhalb dieser Tabelle erscheint wie geschrieben, und Peek leitet den Status nach Möglichkeit aus den Wörtern ab.

## Border Express Zustellung in Australien

Der Tracker von Border Express nennt keinen weiteren Paketdienst: Peek verfolgt das Paket also vom ersten Schritt bis zur Zustellung bei Border Express, ohne Übergabe an einen anderen Dienst.

Diese Seite nennt keine Regeln zu Zustellversuchen, Benachrichtigungskarten, Abstellgenehmigungen, Depotabholung oder Lagerfristen, da Peek sie auf der Website von Border Express nicht prüfen konnte. Frag beim versendenden Shop oder bei Border Express nach. Siehe [verpasste Zustellung](guide:missed-delivery) und [nicht abgeholte Pakete](guide:uncollected-parcel).

## Border Express Paket aktualisiert sich nicht, verloren oder zugestellt, aber nicht da

**Nummer nicht gefunden.** Solange der Border-Express-Tracker die Nummer nicht listet, zeigt ein unter Border Express geführtes Paket „Warten auf Border Express“ und Peek prüft weiter. Kennt Border Express die Nummer nach zwei Tagen noch immer nicht, ohne dass eine andere Quelle antwortet, zeigt Peek „Auch nach zwei Tagen kennt der Paketdienst diese Nummer nicht. Prüfe sie, wähle einen anderen Paketdienst oder lösche das Paket.“ und prüft ab dann täglich, nach einer Woche wöchentlich. Vergleiche die Nummer mit der E-Mail des Shops: 8 Ziffern oder 4 Buchstaben und 5 bis 8 Ziffern. Tut sich nach ein paar Tagen nichts, frag den Shop, ob wirklich versendet wurde.

**Steht bei „Etikett erstellt“.** Border Express hat die Sendungsdaten, aber noch keinen datierten Scan. Im eigenen Tracker steht womöglich `Picked Up` ohne Uhrzeit, das Peek weglässt — schau also zuerst dort nach. Steht auch dort nur `Shipment Data Received`, frag den Shop nach der Übergabe.

**Seit Tagen kein Update.** Bleibt ein Paket unterwegs vier Tage ohne neuen Schritt, zeigt Peek das Datum des letzten Scans („Keine Aktualisierung seit …“) und „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei Border Express nach.“ samt dem Link „Hol dir Hilfe bei Border Express“ zur Kundenservice-Seite. Siehe [warum die Sendungsverfolgung stoppt](guide:tracking-not-updating).

**Teilweise zugestellt.** Gleiche das Gelieferte mit deiner Bestellung ab. Der Rest der Sendung ist noch unterwegs; die Einzelscans jedes Packstücks stehen im Border-Express-Tracker, Peek zeigt sie nicht.

**Zugestellt, aber nichts angekommen.** Schau rund um deine Adresse und frag Nachbarn. Nach der Zustellung liest Peek zudem den Namen des Unterzeichners mit, sofern der Tracker ihn angibt. Kontaktiere danach den Shop. Mehr unter [zugestellt, aber nicht erhalten](guide:delivered-not-received).

**Verloren, beschädigt oder zurückgesendet.** Der Shop hat Border Express beauftragt, also bitte den Shop um Erstattung oder Neulieferung. Siehe [Paket verloren](guide:lost-parcel), [beschädigte Pakete](guide:damaged-parcel) und [zurück an Absender](guide:returned-to-sender).

## Border Express Kontakt und Kundenservice

Da Peek die Website von Border Express nicht prüfen konnte, stehen hier keine Telefonnummer und keine E-Mail-Adresse: Peek gibt nur Kontakte wieder, die direkt von den eigenen Seiten eines Paketdienstes gelesen wurden. Da der Shop den Versand gebucht hat, frag zuerst dort nach.

Auf der Paketseite in Peek öffnet „Website von Border Express öffnen“ deine Nummer im Tracker von Border Express. Bevor du etwas zahlst, das eine Paket-SMS verlangt, lies [Betrugs-SMS zu Paketlieferungen](guide:delivery-scam-texts).

## Ein Border Express Paket mit Peek verfolgen

Füge die Nummer in [Peek](/) ein, das über 4500 Paketdienste verfolgt. Dabei erscheint eventuell „Tracking-Dienste werden geprüft…“, während Peek nachfragt. Tippe dann auf „Ändern“ und wähle Border Express, indem du `Border Express` eintippst (`BEX` findet es auch, listet aber zuerst BEX Express aus Serbien). Bei einem Etiketten-Barcode steht Border Express unter „Passt zu dieser Nummer“. Bei einem schon gespeicherten Paket nutze „Paketdienst ändern“. Konto, Postleitzahl oder Telefonnummer sind nicht nötig.

Fügst du die Nummer ohne Auswahl eines Paketdienstes hinzu, fragt Peek nur Tracking-Dienste an, nie Border Express selbst, und zeigt „Noch kein Sendungsverlauf“, bis einer davon das Paket kennt. Erst wenn ein Tracking-Dienst Border Express nennt, fragt Peek dort weiter. Border Express direkt auszuwählen ist daher der sicherste Weg zu dessen eigenen Schritten.

Ein Tracking-Link von Border Express funktioniert ebenfalls und benennt Border Express sofort („Erkannter Paketdienst“): `borderexpress.com.au/tracking` mit `cn=` oder ein Link `bexonline.borderexpress.com.au/bxo/track`. Wählst du Border Express für eine andere Nummernform, meldet Peek „vergibt keine Nummern wie diese“, und nach dem Hinzufügen erscheint (sofern kein Tracking-Dienst das Paket hat) „Sendungsnummer prüfen“ — kontrolliere dann auf fehlende oder überzählige Zeichen.

**Etiketten-Barcodes.** Da Border Express’ Tracker sie nicht akzeptiert, errechnet Peek die Consignment-Nummer aus dem Barcode: die vier Buchstaben, dann die 11 Ziffern nach `BX` ohne führende Nullen (mindestens sechs Ziffern bleiben erhalten), ohne die letzten 3 Ziffern der Stücknummer. Auf der Paketseite erscheint diese Nummer unter „Border Express führt die Sendung unter“, und „Website von Border Express öffnen“ ruft Quick Track ebenfalls mit dieser Nummer auf. Findet Border Express die Sendung so nicht, nutze die Consignment-Nummer aus der Shop-E-Mail.

**Was du siehst.** Peek zeigt den Status, jeden datierten Schritt mit Vorort und Bundesstaat, den voraussichtlichen Liefertag bis zur Zustellung, die Zustellzeit, den Empfängerort (Vorort, Bundesstaat und Postleitzahl) sowie nach Zustellung den Unterzeichner. Weggelassen werden Fortschrittsleiste, Versanddatum, Stückzahl, Einzel-Barcodes und deren Scans sowie der Absenderort; Gewicht und Zustellnachweis zeigt Peek nicht. Jeder Schritt trägt die Zeitzone seines Bundesstaats, sodass Schritte aus verschiedenen Zeitzonen in der richtigen Reihenfolge stehen und in deiner Geräte-Zeitzone angezeigt werden („Uhrzeiten in deiner Zeitzone:“).

**Wie oft.** Solange du das Paket in Peek geöffnet hast — oder am Zustelltag mit aktivierten Benachrichtigungen — prüft Peek direkt bei Border Express bis zu alle 10 Minuten, und ab 6:00 Uhr Sydney-Zeit am Tag, an dem Border Express das Paket in Zustellung scannt, bis zu alle 2 Minuten, solange der Tracker antwortet; sonst, und nach zwei Tagen ohne Neuigkeiten (12 Stunden bei „Etikett erstellt“ oder „Beim Paketdienst eingegangen“), stündlich. Ist Border Express’ Tracker nicht erreichbar, fragt Peek Tracking-Dienste an und zeigt deren Angaben im jeweiligen Wortlaut. Benachrichtigungen gibt es für „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“. Border Express oder den Shop kontaktieren kann Peek nicht für dich.

## Fragen zur Border Express Sendungsverfolgung

### Was ist eine Border Express Consignment-Nummer?

Die Nummer, unter der Border Express eine Lieferung verfolgt: 8 Ziffern oder die 4 Buchstaben des Absenders gefolgt von Ziffern. Jedes Packstück der Lieferung gehört dazu. Der Shop nennt sie in der Versand-E-Mail oder auf deiner Bestellseite.

### Kann ich Border Express mit dem Barcode auf dem Etikett verfolgen?

In Peek ja: Füge den 20-stelligen Barcode (4 Buchstaben, `BX` und 14 Ziffern) ein und wähle Border Express. Peek leitet die Consignment-Nummer daraus ab. Border Express’ eigener Tracker akzeptiert nur die Consignment-Nummer.

### Was bedeutet „On Board For Delivery“ bei Border Express?

Das Paket befindet sich im Zustellfahrzeug. Peek zeigt es als „In Zustellung“.

### Warum steht bei Border Express „Partially Delivered“?

Deine Sendung umfasst mehrere Packstücke, von denen erst ein Teil zugestellt ist. Der Rest ist noch unterwegs, daher zeigt Peek „Unterwegs“. `Partially Delivered - Final` ist anders: Border Express hat die Sendung mit den eingetroffenen Stücken abgeschlossen, und Peek zeigt „Zugestellt“.

### Was bedeutet „Shipment Data Received“ bei Border Express?

Der Absender hat die Sendungsdaten an Border Express übermittelt, aber Border Express hat das Paket noch nicht gescannt. Peek zeigt „Etikett erstellt“.

:::sources
- [Universal Parcel Scraper: Border Express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/border-express) – Namen, Australien, Consignment-Nummern und Etiketten-Barcodes, Tracking-Links, Quick Track, Wortlaute und Stufen, Schritte ohne Uhrzeit, Bundesstaats-Zeitzonen, Liefertag und Zustellzeit, Empfängerort und Unterzeichner, weggelassene Felder, kein weiterer Paketdienst
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Nummernformen anderer Dienste (Mondial Relay, Colisweb, Heppner, Colis Privé), Name von BEX Express, keine Übergabe an oder von Border Express
:::
