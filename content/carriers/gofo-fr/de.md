---
title: GOFO France Sendungsverfolgung: GFFR-Nummern, Status & Hilfe
description: GOFO-Paket in Frankreich verfolgen: Aufbau von GFFR- und CIFR-Nummern, Bedeutung der französischen Statusmeldungen und an wen du dich bei Verspätung wendest.
slug: gofo-france-sendungsverfolgung
published: 2026-11-17
updated: 2026-11-17
---

GOFO France ist das französische Zustellnetz von GOFO, einem Paketdienst mit Netzen in mehreren Ländern, und stellt Sendungen innerhalb Frankreichs zu. In der Paketdienst-Auswahl von Peek findest du das Unternehmen auch unter dem Namen CIRRO Parcel France. Seine Sendungsnummern beginnen mit `GFFR` oder `CIFR`: Du kannst dein Paket auf der französischen Tracking-Seite von GOFO unter gofo.com verfolgen oder die Nummer oben ins Suchfeld einfügen. Noch keine Sendungsnummer erhalten? Lies [Paket ohne Sendungsnummer finden](guide:find-tracking-number).

## GOFO France-Sendungsnummer: Aufbau und Beispiele

Diese Formate ordnet Peek direkt GOFO France zu – unabhängig von Groß- oder Kleinschreibung:

| Format | Erfundenes Beispiel |
| --- | --- |
| `GFFR` und 13 Ziffern | `GFFR4829105736492` |
| `GFFR` und 14 Ziffern | `GFFR48291057364928` |
| `CIFR` und 13 Ziffern | `CIFR4829105736492` |
| `CIFR` und 14 Ziffern | `CIFR48291057364928` |

Auch eine Händlerreferenz kann als Sendungsnummer dienen: `PK` gefolgt von 20 Ziffern, mitunter mit Bindestrichen geschrieben, etwa `PK-4829105736492817364-5` (erfunden). Die Sendungsverfolgung von GOFO France findet das Paket ebenfalls über diese Referenz, die GOFO zusammen mit der eigenen `GFFR`- oder `CIFR`-Frachtbriefnummer speichert.

Die übrigen Landesgesellschaften von GOFO folgen demselben Muster mit dem jeweiligen Länderkürzel hinter `GF` oder `CI`: `GFIT`- und `CIIT`-Nummern gehören zu GOFO Italy, `GFNL` und `CINL` zu GOFO Netherlands sowie `GFES` und `CIES` zu GOFO Spain. Ein `GFUS` mit 14 Ziffern, ein `GF` mit 13 Ziffern oder ein `CR` mit 12 Ziffern gehört zu [GOFO Express](carrier:gofo), einem eigenständigen Netz in den USA, und `GFCA` mit 14 Ziffern zu GOFO Canada. Keine dieser Nummern stammt von GOFO France. Zum Unterscheiden anderer Muster hilft [welcher Paketdienst hat diese Sendungsnummer?](guide:tracking-number-formats).

## GOFO-Sendungsstatus: was die französischen Meldungen bedeuten

Peek liest die französische Sendungsverfolgung von GOFO aus; deshalb erscheinen die einzelnen Scans im Originalwortlaut auf Französisch. Die häufigsten Meldungen in der Reihenfolge, in der sie üblicherweise auftreten:

| GOFO schreibt | Was das für dich bedeutet |
| --- | --- |
| `Votre colis est en cours de préparation par l'expéditeur` | Der Händler bereitet das Paket vor; GOFO hat es noch nicht übernommen. |
| `Expédition en transit vers GOFO` | Die Sendung ist auf dem Weg zu GOFO, aber dort noch nicht eingetroffen. |
| `Arrivé au site de prise en charge` | Im Annahmezentrum von GOFO angekommen – das ist der erste eigene Scan von GOFO. |
| `Votre colis a été pris en charge au centre de tri` | Beim Eingang in einem Sortierzentrum von GOFO gescannt. |
| `Colis en cours d'acheminement` | Unterwegs zwischen zwei Standorten von GOFO. |
| `Votre colis est arrivé à l’agence régionale GOFO de destination` | In der regionalen GOFO-Zustellbasis deiner Region eingetroffen, von der aus zugestellt wird. |
| `Le chauffeur est en route pour la livraison` | Mit dem Zusteller auf dem Weg zu dir. |
| `Échec de la livraison, motif : Le destinataire n'est pas chez lui` | Zustellversuch fehlgeschlagen: niemand war zu Hause. |
| `Livré` | Zugestellt. |

Vor allem die ersten beiden Zeilen sorgen oft für Verwirrung: Solange weder `Arrivé au site de prise en charge` noch der Scan im Sortierzentrum auftaucht, hat GOFO das Paket physisch noch nicht erhalten, und Peek zeigt den Status „Etikett erstellt“. Die Zustellung steht im Verlauf sprachunabhängig schlicht als `Livré` mit dem Status „Zugestellt“: Etwaige Zusätze von GOFO – darunter Empfängerangaben – blendet Peek aus.

Daneben gruppiert GOFO Sendungen in übergeordnete Kategorien. `Alerte` kennzeichnet ein von GOFO gemeldetes Problem und erscheint in Peek als „Problem gemeldet“; für sich genommen heißt das nicht, dass bereits ein Zustellversuch stattgefunden hat. `Retourné` steht für eine Rücksendung an den Absender und wird in Peek als „Zurück an Absender“ geführt.

## GOFO-Paket verspätet, nicht zugestellt, verloren oder beschädigt

**Keine Aktualisierung.** Peek fragt weiter bei GOFO nach. Vergehen vier Tage ohne neuen Scan, bevor das Paket in Zustellung geht, weist die Paketseite in Peek darauf hin („Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei GOFO France nach.“) und verlinkt über „Hol dir Hilfe bei GOFO France“ auf die Hilfeseite von GOFO France. Siehe auch [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating).

**Fehlgeschlagener Zustellversuch.** Nach `Échec de la livraison, motif : Le destinataire n'est pas chez lui` zeigt Peek „Zustellversuch“; nennt GOFO hinter `motif :` einen anderen Grund, lautet der Status „Problem gemeldet“. Das weitere Vorgehen bestimmt GOFO; was du prüfen kannst, steht unter [Zustellung verpasst](guide:missed-delivery).

**Verspätete Lieferung.** Nach den EU-Verbraucherrechten muss der Online-Shop deine Bestellung innerhalb von 30 Tagen liefern, sofern ihr keine andere Lieferfrist vereinbart habt. Hält er diese Frist nicht ein, erinnere ihn daran und setze eine angemessene Nachfrist; liefert er auch dann nicht, kannst du vom Vertrag zurücktreten und dein Geld zurückverlangen.

**Als `Livré` markiert, aber nicht erhalten.** Für die Lieferung haftet der Händler: Er muss sich an den Paketdienst wenden und nachweisen, dass dir die Ware zugestellt wurde. Kann er diesen Nachweis nicht erbringen, muss er dir entweder einen identischen Artikel zusenden oder den Kaufpreis erstatten. Was du vorab rund ums Haus prüfen solltest, liest du unter [zugestellt, aber nicht erhalten](guide:delivered-not-received).

**Beschädigtes Paket.** Der Händler trägt das Transportrisiko ab dem Versand bis zu dem Moment, in dem du die Ware erhältst – auch für Schäden, die während des Transports entstehen. Prüfe den Zustand der Sendung nach Möglichkeit direkt im Beisein des Zustellers. Mehr dazu unter [beschädigtes Paket](guide:damaged-parcel).

**Einfuhrabgaben in der EU.** Laut Europäischer Kommission fällt bei Online-Bestellungen von außerhalb der EU seit dem 1. Juli 2026 auf Sendungen im Wert von bis zu 150 € ein Zoll von 3 € je Position an; ab dem 1. November 2026 kommt eine Unions-Bearbeitungsgebühr von 2 € je Position hinzu, die auch für Fernverkäufe über 150 € gilt. Eine Position fasst Waren mit demselben Warencode und demselben Ursprungsland zusammen: Für zwei T-Shirts fallen 3 € Zoll und 2 € Gebühr an, für ein T-Shirt und eine Hose jeweils das Doppelte. Wirst du zur Zahlung aufgefordert? Lies [Paket hängt beim Zoll](guide:customs).

## GOFO France Kundenservice und Kontakt

Die Hilfeseite von GOFO France liegt auf der französischen Website unter gofo.com; Peek führt dich mit „Hol dir Hilfe bei GOFO France“ dorthin, wenn vier Tage ohne neuen Scan vergehen, bevor das Paket in Zustellung ist. Kommt deine Bestellung zu spät, geht verloren oder trifft beschädigt ein, wende dich zuerst an den Shop, bei dem du gekauft hast – er ist gegenüber dir für die Lieferung verantwortlich. Halte deine `GFFR`- oder `CIFR`-Nummer sowie deine Bestellnummer bereit.

## GOFO France-Sendung mit Peek verfolgen

[Peek](/) erkennt `GFFR`- und `CIFR`-Nummern automatisch. Ebenso kannst du einen Link von der französischen Tracking-Seite von GOFO einfügen (unter gofo.com oder gofoexpress.fr mit `id=` und der Nummer). Eine `PK`-Händlerreferenz funktioniert ebenfalls: Peek fragt sie bei GOFO France ab und ordnet das Paket GOFO France zu, sobald das Netz die Nummer kennt; bis dahin steht dort „Warten auf den ersten Scan von GOFO France“.

Peek prüft GOFO France bis zu alle 10 Minuten, solange du das Paket geöffnet hast oder am Zustelltag Benachrichtigungen eingeschaltet sind, sonst stündlich – ebenso nach zwei Tagen ohne neuen Scan oder nach 12 Stunden auf „Etikett erstellt“ bzw. „Beim Paketdienst eingegangen“. Am Zustelltag prüft Peek ab 6:00 Uhr Ortszeit bis zu alle 2 Minuten, sobald das Paket in Zustellung ist und solange GOFOs eigener Tracker antwortet; antworten nur Tracking-Dienste, prüft Peek stündlich, nach einem Tag ohne neuen Scan alle 3 Stunden und am Zustelltag bis zu alle 15 Minuten, solange du das Paket geöffnet hast oder Benachrichtigungen eingeschaltet sind. Peek zeigt Status, Verlauf mit dem von GOFO genannten Ort jedes Scans, Zustellzeit und diese Orte auf einer Karte; Benachrichtigungen gibt es für „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“. Nennt GOFO lediglich einen Depotcode oder das Land, erscheint der Scan ohne Ortsangabe. Die Ortszeiten von GOFO rechnet Peek in deine Zeitzone um (unter dem Verlauf steht „Uhrzeiten in deiner Zeitzone: …“). Solange GOFO noch keine Zeile listet, zeigt peektracker.com „Warten auf GOFO France“ und fragt weiter nach. Kennt GOFO die Nummer auch nach zwei Tagen nicht und meldet sich keine andere Quelle, erscheint „Auch nach zwei Tagen kennt der Paketdienst diese Nummer nicht. Prüfe sie, wähle einen anderen Paketdienst oder lösche das Paket.“, woraufhin Peek einmal täglich und nach einer Woche ab dem Hinzufügen einmal wöchentlich prüft. Ist die Sendung bei GOFO bereits registriert, aber noch ungescannt, bleibt es bei „Warten auf GOFO France“, ohne dass du die Nummer prüfen musst. Das voraussichtliche Lieferdatum von GOFO blendet Peek ein, solange das Paket unterwegs ist, bis es in Zustellung geht, zugestellt oder zurückgeschickt wird, in einer Abholstelle liegt oder ein Zustellversuch bzw. Problem gemeldet wird; Gewicht, Zustellfotos und GOFO-Hinweise zeigt Peek nicht. GOFO oder den Shop kontaktieren kann Peek nicht für dich.

## Häufige Fragen zur Sendungsverfolgung von GOFO France

### Was bedeutet „Expédition en transit vers GOFO“?

Das Paket ist auf dem Weg zu GOFO, wurde dort aber noch nicht übernommen. Erst danach folgt der erste eigene Scan von GOFO: `Arrivé au site de prise en charge` (Ankunft im Annahmezentrum) oder `Votre colis a été pris en charge au centre de tri` (Übernahme im Sortierzentrum). Bis dahin zeigt Peek „Etikett erstellt“.

### Ist GOFO France ein echtes Unternehmen?

Ja. GOFO FRANCE ist eine französische Kapitalgesellschaft (*SAS, société par actions simplifiée*), eingetragen im Sirene-Register des INSEE unter der Siren-Nummer 978 559 839. Das Unternehmen ist seit dem 26. Mai 2023 aktiv und hat seinen Sitz in Marly-la-Ville (Département Val-d’Oise).

### Sind GOFO und CIRRO Parcel dasselbe?

Für die Sendungsverfolgung in Frankreich ja: In der Paketdienst-Auswahl von Peek ist CIRRO Parcel France ein weiterer Name für GOFO France. Steht in einer Sendungsverfolgung dagegen nur „Cirro“, kann damit das französische, italienische oder niederländische Netz von GOFO gemeint sein – oder die eigene grenzüberschreitende Linie von Cirro, die Pakete unter nationalen Postnummern an die Landesposten übergibt.

### Was tue ich, wenn mein GOFO-Paket verloren gegangen ist?

Wende dich an den Online-Shop. Nach den EU-Regeln trägt der Händler die Verantwortung für die Lieferung, bis du die Ware in den Händen hältst: Kann er die Zustellung nicht nachweisen, muss er dir einen identischen Artikel schicken oder den Kaufpreis erstatten. Mehr dazu unter [Paket verloren](guide:lost-parcel).

:::sources
- [INSEE: Avis de situation au répertoire Sirene](https://avis-situation-sirene.insee.fr/) – GOFO FRANCE, Siren 978 559 839, SAS, aktiv seit 26. Mai 2023, Sitz in 95670 Marly-la-Ville
- [Your Europe: Versand und Lieferung](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_de.htm) – Lieferung innerhalb von 30 Tagen, Nachfrist, Haftung bis zum Erhalt, 3 € Zoll je Position bis 150 € und Definition einer Position
- [Your Europe: Fragen und Antworten – Versand und Lieferung](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/faq/index_de.htm) – Zustellnachweis durch den Händler, Transportrisiko und Prüfung im Beisein des Zustellers
- [Europäische Kommission: EU Customs Reform (EN)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 3 € Zoll seit 1. Juli 2026 auf Sendungen bis 150 € und Bearbeitungsgebühr ab 1. November 2026
- [Europäische Kommission: Guidance on the 3 EUR customs duty and Union handling fee (PDF, EN)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € Bearbeitungsgebühr je Position im Fernverkauf, auch über 150 €
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Nummernmuster, PK-Referenzen, Links, französische Statusmeldungen und Kategorien, CIRRO Parcel France, übrige GOFO-Netze und Anzeige in Peek
:::
