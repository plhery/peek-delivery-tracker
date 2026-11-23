---
title: TForce Freight Sendungsverfolgung: PRO-Nummer & Status
description: TForce Freight Sendung in den USA oder Kanada verfolgen: PRO-Nummer, Bedeutung der Statusmeldungen, Hauszustellung und Reklamationen.
slug: tforce-freight-sendungsverfolgung
published: 2026-11-22
updated: 2026-11-22
---

TForce Freight ist eine Stückgutspedition (Less-Than-Truckload, LTL): sie transportiert Paletten und schwere Fracht zwischen ihren Service-Centern in den Vereinigten Staaten und ganz Kanada sowie zu mehr als 600 Orten in Mexiko. Wenn eine große Bestellung oder Frachtsendung mit TForce zu dir unterwegs ist, verfolge sie mit der PRO-Nummer des Shops oder Auftraggebers, indem du sie oben einfügst. Der eigene Tracker von TForce liegt auf der Tracking-Seite von www.tforcefreight.com.

## Sendungsnummer von TForce Freight: die PRO-Nummer

TForces Tracking-Seite sucht Sendungen per PRO-Nummer oder per Frachtbriefnummer (BOL) beziehungsweise Bestellnummer (PO) zusammen mit einem Versandzeitraum. Peek verfolgt per PRO (Ziffern erfunden):

| Nummer (erfunden) | Bedeutung |
| --- | --- |
| `482917363` | Die PRO-Nummer: neun Ziffern. Die letzte ist eine Prüfziffer – der Rest beim Teilen der ersten acht Ziffern durch 7, also immer 0 bis 6. |
| `48291736` | Eine PRO ohne führende 0. TForces Tracker ergänzt die 0, und Peek ebenso, sobald du TForce Freight wählst. |
| `2205482917363` | Eine PARS-Nummer von Zollagenten für Fracht nach Kanada: 2205 gefolgt von der PRO. Füge sie komplett ein: Peek fragt TForce mit der enthaltenen PRO ab. |

Eine neunstellige PRO mit Endziffer 7, 8 oder 9 enthält einen Tippfehler. Peeks Katalog kennt TForce Freight auch als UPS Freight und TForce.

**Wo du sie findest.** In der Versand-E-Mail des Shops oder auf deiner Bestellseite; bei gewerblicher Fracht frage den Absender. Siehe [Sendungsnummer finden](guide:find-tracking-number).

**Gleiches Format bei anderen Diensten.** Neun Ziffern mit derselben Prüfziffer passen auch zu [TNT](carrier:tnt) und XPO. TForce Logistics (TForce Final Mile), deren Nummern mit `TF` beginnen, ist ein eigener Paketdienst in Peek. Nennt deine E-Mail einen davon, wähle diesen Dienst. Mehr unter [Sendungsnummern-Formate](guide:tracking-number-formats).

## Bedeutung der Statusmeldungen von TForce Freight

TForces Tracker zeigt neben der PRO einen Status wie `In Transit` oder `Delivered` und darunter den Verlauf, neuestes Ereignis zuerst, jeweils mit Ort und Bundesstaat des Service-Centers, Datum und Uhrzeit. Da die Zeilen keinen Code tragen, wertet Peek den Wortlaut aus. In typischer Reihenfolge:

| TForce Freight meldet | Bedeutung für dich | Peek zeigt |
| --- | --- | --- |
| `SHIPMENT HAS BEEN PICKED-UP.` | Der Fahrer hat die Fracht beim Absender abgeholt. | „Beim Paketdienst eingegangen“ |
| `SHIPMENT IS AT A TFORCE FREIGHT LOCATION` | Die Sendung liegt im Service-Center beim Absender. | „Unterwegs“ |
| `DEPARTURE` | Hat ein Service-Center verlassen. | „Unterwegs“ |
| `SHIPMENT HAS ARRIVED AT A SERVICE CENTER.` | In einem Service-Center unterwegs oder im zustellenden Zentrum angekommen. | „Unterwegs“ |
| `SENT EMAIL. TFORCE FREIGHT IS ATTEMPTING TO OBTAIN APPOINTMENT INFORMATION` | TForce hat den Empfänger angemailt, um einen Liefertermin zu vereinbaren. Antworte darauf. | „Unterwegs“ |
| `A DELIVERY APPOINTMENT HAS BEEN SCHEDULED WITH THE CONSIGNEE.` | Ein Liefertag wurde vereinbart. Diese Zeile erscheint manchmal erst nach der Zustellung. | „Unterwegs“ |
| `OUT FOR DELIVERY` | Auf dem Zustell-Lkw unterwegs. | „In Zustellung“ |
| `Shipment not delivered. Delivery attempted after normal business hours. Tforce will attempt delivery on next business day we service this area.` | Auf dieser Tour nicht zugestellt. Jede Zeile ab `Shipment not delivered.` meldet einen fehlgeschlagenen Versuch samt Grund. | „Zustellversuch“ |
| `SHIPMENT HAS BEEN DELIVERED TO THE CONSIGNEE.` | Zugestellt. TForces Seite zeigt, wer unterschrieben hat. | „Zugestellt“ |

Ein `Delivered` neben der PRO erscheint immer als „Zugestellt“; sonst entscheidet die neueste von Peek erkannte Zeile, und übrige Zeilen erscheinen im Originaltext von TForce. Die Tracking-Seite von TForce kann auch E-Mail-Benachrichtigungen senden. Mehr unter [Sendungsstatus erklärt](guide:tracking-statuses).

## So liefert TForce Freight

TForce befördert die Fracht zum zuständigen Service-Center und liefert sie per Lkw aus. Der Tracker nennt einen voraussichtlichen Liefertag, den Peek bis zur Zustellung oder Rücksendung anzeigt (solange er nicht abgelaufen ist). Peeks Code übergibt die Sendung an keinen anderen Paketdienst.

TForces Tarifbestimmungen (LTL Rules Tariff UPGF 102-T, gültig seit 17. August 2026) regeln den Ablauf:

- **Privathaushalte.** Wo die Lkw rechtlich fahren dürfen, muss TForce zunächst Datum und ungefähre Uhrzeit mit dir oder dem Absender vereinbaren. Ein von zu Hause betriebenes Gewerbe zählt als Privathaushalt. Geliefert wird an den ebenerdigen Eingang, Garageneingang oder Carport, oder du holst die Sendung im Terminal ab. Hauszustellung ist ein kostenpflichtiger Zusatzservice zulasten des Auftraggebers oder Frachtzahlers.
- **Ab Lkw.** Standardzustellung bedeutet Entladen neben dem Lkw. Vertragen ins Gebäude und Hebebühne kosten extra: frage den Auftraggeber, ob sie gebucht sind.
- **Ankunftsavis.** Wenn die Frachtpapiere es vorsehen, benachrichtigt TForce den Empfänger bis zum nächsten Werktag nach Eintreffen im Zustellzentrum, meist telefonisch oder per E-Mail.
- **Nicht zugestellt.** Bei Annahmeverweigerung oder unauffindbarem Empfänger informiert TForce den Absender über die Einlagerung; es können Lagergebühren anfallen. Ein neuer Versuch ist kostenpflichtig, sofern du nicht im Service-Center abholst – bei Privathaushalten nur, wenn du einen vereinbarten Termin verpasst hast.

**Nach Kanada.** Wie TForces Kanada-Seite erklärt, wird Fracht aus den USA an der Grenze über einen Zollagenten abgefertigt. Nicht verzollte Fracht wartet am Zielort in einem Zolllager gegen Lagergebühren. Gebühren des Zollagenten, Zölle und Steuern trägt der eingetragene Importeur.

## TForce-Freight-Sendung nicht gefunden, verspätet, beschädigt oder verloren

**Nicht gefunden.** Bei unbekannter PRO meldet TForces Tracker `Not Available` und `Please check ProNumber` mit der Nummer. Peek zeigt dann „Warten auf TForce Freight“ (falls du TForce gewählt hast) oder „Noch kein Sendungsverlauf“ und fragt später erneut. Prüfe jede Ziffer und frage den Absender, ob TForce die Ware schon hat.

**Falsche Ziffer.** Eine PRO mit ungültiger Prüfziffer gehört nicht zu TForce, und Peek fragt TForce nie dazu ab. Wählst du TForce Freight dennoch, meldet Peek „vergibt keine Nummern wie diese“ und zeigt „Sendungsnummer prüfen“.

**Keine Aktualisierung.** Nach vier Tagen ohne neuen Scan bei einer Sendung auf „Beim Paketdienst eingegangen“ oder „Unterwegs“ zeigt Peek „Keine Aktualisierung seit …“, erklärt „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei TForce Freight nach.“ und bietet „Hol dir Hilfe bei TForce Freight“. Frage Shop oder Absender. Siehe [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating).

**Verpasste Zustellung.** Nach einer Zeile `Shipment not delivered.` zeigt Peek „Nächste Schritte beim Paketdienst prüfen“. Rufe TForce an oder bitte den Auftraggeber, einen neuen Termin abzustimmen. Mehr unter [verpasste Zustellungen](guide:missed-delivery).

**Fehlmenge oder Schaden bei Lieferung.** Laut TForces Seite Cargo Claims Information prüfst du jede Lieferung sofort und informierst den Fahrer:

- Zähle die Packstücke gegen den Ablieferbeleg und öffne beschädigte Kartons gemeinsam mit dem Fahrer.
- Vermerke genau, was fehlt oder beschädigt ist – auf deiner Kopie und auf der von TForce.
- Verweigere die Annahme nur, wenn der Schaden die Ware wertlos gemacht hat.
- Bitte die Stelle für Over, Short & Damage (OS&D) in deinem lokalen Service-Center, fehlende Teile zu suchen.

**Später entdeckter Schaden.** Melde ihn innerhalb von 5 Werktagen nach Zustellung und fordere eine Besichtigung an, während die Verpackung unverändert bleibt. Ein Besichtigungsbericht ersetzt keine Reklamation.

**Reklamation einreichen.** Eine Reklamation ist eine schriftliche Zahlungsforderung des Eigentümers der Sendung. Bei Online-Bestellungen beginnst du beim Shop als Auftraggeber. TForces Regeln:

- Reiche sie innerhalb von neun Monaten nach Zustellung ein – oder ab Versanddatum, falls nichts ankam.
- Nutze TForces Standard Claim Form oder ein Schreiben mit Sendungsangaben und Betrag, samt Händlerrechnung, Frachtrechnung, Frachtbrief und etwaigen Reparaturrechnungen. Per Post oder Fax einsenden (siehe unten), nicht beides.
- TForce bestätigt den Eingang innerhalb von 30 Tagen. Die Haftung ist auf 25,00 $ pro Pfund und Packstück begrenzt – für manche Waren niedriger –, sofern der Absender auf dem Frachtbrief keine höhere Deckung gekauft hat.

Meldet das Tracking zugestellt, obwohl nichts ankam, siehe [zugestellt, aber nicht erhalten](guide:delivered-not-received). Mehr unter [beschädigtes Paket](guide:damaged-parcel) und [verlorenes Paket](guide:lost-parcel).

## TForce Freight Kundenservice und Kontakt

Angaben von www.tforcefreight.com (Seiten Tracking, Fact Sheet, Rules Tariffs mit LTL Rules Tariff UPGF 102-T, Canada, Cargo Claims Information, Contact Us mit Email TForce Freight Customer Support und Service Information):

- **Kundenservice:** (800) 333-7400, gebührenfrei aus den USA, Kanada, Guam, Puerto Rico und den Amerikanischen Jungferninseln, für Tracking, Abliefernachweise und Probleme. Von anderswo: (804) 231-8555.
- **E-Mail:** das Formular Email TForce Freight Customer Support auf der Contact-Us-Seite von TForce mit eigenem Feld für die PRO-Nummer.
- **Schadensabteilung:** (888) 653-9720 für den Bearbeitungsstand. Reklamationen gehen an Claims Processing, TForce Freight, PO Box 1216, Richmond, VA 23218, oder per Fax an (866) 580-1944.
- **Dein Service-Center:** das Service Center Directory unter Service Information auf www.tforcefreight.com.

Der Shop oder Absender hilft oft schneller, da er die Buchung bei TForce hält.

## TForce-Freight-Sendung mit Peek verfolgen

Das Format einer PRO verrät Peek nicht eindeutig TForce: neun Ziffern passen auch zu [TNT](carrier:tnt), XPO und anderen Diensten. Füge sie trotzdem ein: Peek unterstützt über 4.500 Paketdienste. Während „Tracking-Dienste werden geprüft…“ steht, fragt Peek die passenden Dienste ab; kennt allein TForce die Fracht, erscheint der Name mit „hat dieses Paket“. Bei einer PARS-Nummer (`2205` plus PRO) fragt Peek TForce zusammen mit FAN Courier; bei einer achtstelligen PRO ohne führende Null wählst du TForce Freight über „Ändern“ unter „Passt zu dieser Nummer“ (oder später über „Paketdienst ändern“). Ein Link zu TForces Tracker (`proNumbers=`) oder zum alten UPS-Freight-Tracker (`TrackProNumber=`) erkennt TForce sofort („Erkannter Paketdienst“).

Peek fragt TForces Tracker allein mit der PRO ab. Angezeigt werden Status, jede Zeile mit Ort und Bundesstaat, erwarteter Liefertag, Zielland auf der Karte, Gewicht in deinen Einheiten und, nur für dich, der Ort des Empfängers auf der Karte und sein Name unter „Empfänger“; weggelassen werden Absender, Liefertermin, BOL- und PO-Nummern, Packstückzahl, Auflieger, Depotcodes und der Abliefernachweis als Dokument. „Website von TForce Freight öffnen“ öffnet deine Nummer (oder bei PARS die enthaltene PRO) im TForce-Tracker. Ist der Tracker nicht erreichbar oder verlangt ein CAPTCHA, kann Peek auf [Tracking-Dienste](guide:universal-tracker) ausweichen.

TForce nennt bei jeder Zeile die Ortszeit des jeweiligen Service-Centers ohne Zeitzone. Peek liest sie nach der Uhr des genannten Ortes – sonst des Bundesstaats oder der Provinz, einer Nachbarzeile oder des Ziellands, hilfsweise Eastern Time – und zeigt sie in deiner Zeitzone an („Uhrzeiten in deiner Zeitzone:“).

Peek fragt TForce direkt bis zu alle 10 Minuten ab – und ab 6:00 Uhr nach der Uhr des letzten Scans (hilfsweise Eastern Time) alle 2 Minuten an dem Tag, an dem TForce die Sendung in Zustellung scannt, solange sie in Peek geöffnet ist oder am Liefertag mit aktiven Mitteilungen. Nach zwei Tagen ohne Neuigkeiten (nach 12 Stunden, solange noch „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ steht) prüft Peek stündlich. Für Mitteilungen wählst du „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“. Peek kann weder TForce noch Shop oder Absender für dich kontaktieren und keine Reklamation einreichen.

## Häufige Fragen zu TForce Freight

### Was ist eine TForce-Freight-PRO-Nummer?

Die Sendungsnummer von TForce: neun Ziffern, die letzte als Prüfziffer von 0 bis 6. Du erhältst sie vom Shop oder dem Unternehmen, das den Transport gebucht hat.

### Kann ich TForce Freight ohne PRO-Nummer verfolgen?

Auf TForces Tracking-Seite ja: per Frachtbriefnummer (BOL) oder Bestellnummer (PO) mit Versandzeitraum. Peek verfolgt nur per PRO.

### Liefert TForce Freight an Privatadressen?

Ja, wo die Lkw rechtlich fahren dürfen, gegen Aufpreis. TForce stimmt zuerst Datum und ungefähre Uhrzeit mit dir oder dem Absender ab und liefert an den ebenerdigen Eingang, Garageneingang oder Carport. Vertragen ins Haus und Hebebühne kosten ebenfalls extra.

### Wie viel Zeit habe ich für eine Reklamation bei TForce Freight?

Neun Monate ab Zustellung oder ab Versanddatum, falls nichts ankam. Nach der Zustellung entdeckte Schäden meldest du binnen 5 Werktagen.

### Wie lautet die Telefonnummer des Kundenservice von TForce Freight?

(800) 333-7400, gebührenfrei aus den USA und Kanada. Von anderswo: (804) 231-8555.

:::sources
- [Universal Parcel Scraper: TForce Freight](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/tforce-freight) – Namen, PRO-Formate, Links, Wortlaute und Statusstufen, Nicht-gefunden-Meldung, von Peek gelesene Daten, Uhrzeiten
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – weitere Dienste mit gleichem Format, keine Übergabe, Tracking-Dienste als Ausweichweg
:::
