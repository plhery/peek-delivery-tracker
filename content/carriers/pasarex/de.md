---
title: PasarEX Sendungsverfolgung: Amazon-AMZPSR, Zoll & Hilfe
description: Verfolge PasarEX-Importe (Pasar Express) in Kolumbien, Chile, Panama und Barbados: AMZPSR-Codes, Zollstatus, PQR-Regeln und Telefonnummern.
slug: pasarex-sendungsverfolgung
published: 2026-11-24
updated: 2026-11-24
---

PasarEX (die Handelsmarke der 1993 gegründeten kolumbianischen Gesellschaft *PASAR EXPRESS SAS*) bietet Expresskurier, Luftfracht und Zustellung auf der letzten Meile für E-Commerce-Plattformen. Seine Websites decken **Kolumbien**, **Chile**, **Panama** und **Barbados** ab, ergänzt um ein Logistikzentrum in Miami und multimodale Transporte aus China.

PasarEX verzollt Pakete direkt als Zollagent, sodass das Tracking vom Manifest über die Ankunft der Sammel-Importladung, den Zoll und Lagerhäuser bis zur lokalen Zustellung reicht.

## PasarEX-Sendungsnummern-Formate

Peek akzeptiert PasarEX-Nummern mit 6 bis 30 Buchstaben und Ziffern sowie mindestens einer Ziffer. Eine Form gehört ausschließlich zu PasarEX:

| Format (erfunden) | Worum es sich handelt |
| --- | --- |
| `AMZPSR482917360` | `AMZPSR` + 9 Ziffern (15 Zeichen) — eine Amazon-Nummer, die der PasarEX-Tracker abruft; kein anderer Paketdienst in Peek nutzt dieses Muster. |

Enthält deine Bestellbestätigung eine direkte Tracking-URL (`tracking.pasarex.com/?awb=...`), liest Peek den Frachtbrief aus und weist sofort PasarEX zu. Für weitere E-Commerce-Formate siehe den [Leitfaden zu Sendungsnummern-Formaten](guide:tracking-number-formats) und den [Amazon-Bestellungen-Ratgeber](guide:amazon-orders).

## Was die PasarEX-Sendungsstatus bedeuten

PasarEX ordnet Scan-Zeilen im Tracker unter groben Rubriken (`COMMENTS`, `IN CUSTOMS PROCESS`, `RECEIVED FOR DELIVERY`, `IN TRANSIT`, `IN WAREHOUSE`, `OUT FOR DISTRIBUTION`, `DELIVERED`, `SHIPPING WITH DELIVERY ISSUE`) ein, während einzelne Scan-Sätze auf Spanisch oder Englisch samt Ort und Zeitstempel erscheinen.

| PasarEX-Scantext | Bedeutung |
| --- | --- |
| `Orden de pedido recibida - paquete no arribado` / `Information (Manifest) of the shipment received.` | Elektronische Manifestdaten sind bei PasarEX eingegangen, bevor das physische Paket im Import-Hub eintraf. |
| `Carga consolidada para importación recibida` / `Reception of consolidated cargo in import.` | Die konsolidierte Luftfracht-Importladung ist vor der Verzollung an der PasarEX-Eingangsstation (etwa Bogotá) angekommen. |
| `Entry to station for customs processing.` | Das Paket wurde für die Einfuhrverzollung an der Zollstation erfasst. |
| `Cleared by customs by PasarEx.` / `Cleared by customs and transferred to OPS.` | PasarEX hat die Sendung verzollt und an den Inlandsbetrieb übergeben. |
| `Ingresó a bodega` / `Package received in warehouse.` / `Reception of consolidated cargo at a station.` | Zwischen inländischen Streckenabschnitten in einem PasarEX-Lager oder einer Zielstation eingebucht. |
| `Package in transit` / `Package in ground transit` | Auf dem Landweg oder im Inlandstransport zwischen PasarEX-Stationen unterwegs. |
| `Applies to cargo that may experience delivery delays due to disturbances or protests` | Hinweis auf mögliche Verzögerungen durch örtliche Störungen; die Sendung läuft anschließend weiter. |
| `Shipment left to local route for final delivery` | Heute mit dem lokalen Kurier auf Auslieferungstour. |
| `Shipment delivered in perfect condition` | An der Empfängeradresse zugestellt. |

## Zollabfertigung, PQR-Reklamationen und Liefererwartungen

Da PasarEX grenzüberschreitende Importe vor der Inlandsverteilung selbst als Zollagent abfertigt:

- **Vor Ankunft des Pakets:** Solange das Tracking nur `Information (Manifest) of the shipment received.` oder `Orden de pedido recibida - paquete no arribado` anzeigt, ist die Sendung angekündigt, aber physisch noch nicht bei PasarEX eingetroffen. Rechne mit einer Pause, bis `Reception of consolidated cargo in import.` oder `Carga consolidada para importación recibida` erscheint. Siehe den [Zoll-Ratgeber](guide:customs) und [wenn die Sendungsverfolgung stehen bleibt](guide:tracking-not-updating).
- **Kolumbianische PQR- und Entschädigungsregeln (CRC-Resolution 5050 von 2016):** Auf [PasarEX Colombia Support](https://pasarex.com/co/soporte/) veröffentlicht Pasar Express SAS die gesetzlichen Reklamationsfristen nach Gesetz 1369/2009 und Resolution 5050/2016. Empfänger müssen Beraubungs- oder Schadensmeldungen innerhalb von **5 Werktagen** nach Erhalt einreichen, während Absender **10 Kalendertage** (Inland) oder **6 Monate** (International) bei Verlust, Beraubung oder Schaden haben (Firmenverträge können eigene Bedingungen vorsehen). Erforderlich sind Kopien des Luftfrachtbriefs (*Guía Aérea*), der Handelsrechnung, der Pasar-Express-Rechnung, Artikelliste, Verpackungsbeschreibung und Fotos. PasarEX bearbeitet PQRs üblicherweise binnen **15 Werktagen** (bei weiterer Prüfung verlängerbar); nach Mitteilung haben Nutzer **10 Werktage** für einen Einspruch (*recurso de reposición y en subsidio de apelación*).

## PasarEX mit Peek verfolgen

Füge deine Nummer oder einen Link `tracking.pasarex.com/?awb=...` in Peek ein, das über 4.500 Paketdienste verfolgt. Ein Link geht sofort an PasarEX. Bei einer bloßen Nummer fragt Peek PasarEX nie von sich aus an: Es prüft zunächst andere Dienste mit derselben Form, fragt dann Tracking-Dienste ab („Tracking-Dienste werden geprüft…“) und ordnet das Paket PasarEX nur zu, wenn ein Tracking-Dienst ausschließlich PasarEX mit datierten Schritten nennt. Tippe daher auf „Ändern“ und wähle PasarEX (bei einer `AMZPSR`-Nummer unter „Passt zu dieser Nummer“), oder nutze „Paketdienst ändern“ bei einem bereits hinzugefügten Paket.

Da der PasarEX-Tracker bei jeder Abfrage ein reCAPTCHA erfordert, liest Peek ihn über den Open-Source-[PasarEX-Adapter von universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pasarex) mit einer Browser-Hilfe aus. Peek liest Text, Stadt und Uhrzeit jedes Scans aus und ordnet sie chronologisch von neu nach alt statt nach den PasarEX-Rubriken. PasarEX nennt Ortszeiten ohne UTC-Versatz: Peek ordnet jeden Scan der Zeitzone seiner Stadt zu (New Yorker Zeit für Miami, Bogotá-Zeit für Bogotá, Medellín und Cali sowie Santiago-, Panama- oder Barbados-Zeit für diese Orte), einen Scan ohne Stadt der Zeitzone des Nachbarscans oder sonst der Bogotá-Zeit, und stellt alle auf der Uhr deines Geräts dar. Der PasarEX-Tracker liefert kein voraussichtliches Lieferdatum, kein Gewicht und keinen Zustellnachweis.

Peek überführt die Scan-Sätze von PasarEX in klare Phasen: **Etikett erstellt** für den Manifesteingang vor physischer Ankunft, **Unterwegs** von der Ankunft der Sammel-Importladung über Zollfreigabe und Lagereingang bis zum Landtransit (PasarEX springt vom Manifest direkt zum Importeingang ohne eigenen Scan **Beim Paketdienst eingegangen**), **Beim Zoll** während der Zollbearbeitung, **In Zustellung** bei Abfahrt auf die lokale Route und **Zugestellt** bei erfolgter Lieferung.

Peek prüft den PasarEX-Tracker höchstens stündlich — der kürzeste von PasarEX zugelassene Abstand zwischen zwei Abfragen, auch nach einem Fehlschlag und unabhängig davon, ob du das Paket geöffnet hast; am Zustelltag erfolgen keine 2-Minuten-Abfragen. Nach sieben Tagen auf „Etikett erstellt“ prüft Peek alle 6 Stunden. Antwortet der PasarEX-Tracker nicht und Peek verfolgt das Paket allein über Tracking-Dienste, prüft Peek stündlich und nach einem ruhigen Tag alle 3 Stunden.

Ist der neueste Scan vier Tage alt und das Paket steht noch auf „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“, markiert Peek es mit dem Tag dieses Scans, etwa „Keine Aktualisierung seit Mo., 12. Okt.“. Auf der Paketseite steht dann „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei PasarEX nach.“ Nach drei Wochen ohne neuen Scan heißt es „Seit über drei Wochen nichts Neues. Ist das Paket noch nicht angekommen, frag beim Verkäufer nach. Peek fragt weiter bei PasarEX nach.“

Wähle **Jeden Scan**, **Wichtige Schritte** oder **Nur die Zustellung** in den Peek-Benachrichtigungen, um zu bestimmen, welche Ereignisse eine Meldung auslösen.

## Kontakt zu PasarEX

PasarEX veröffentlicht Telefonnummern und E-Mail-Adressen für jedes Land:

- **Kolumbien ([pasarex.com/co/soporte](https://pasarex.com/co/soporte/)):** `+57 601 384 7000`, `+57 601 390 6000` (auch PQR-Nummer Bogotá), `+57 601 291 6500`, `+57 300 919 7521`, `+57 300 919 7532`; E-Mail `sac@pasar.net` (Support) und `pqr@pasar.net` (PQR/Reklamationen)
- **Chile ([pasarex.com/cl](https://pasarex.com/cl/)):** `+56 2 2571 7500`; E-Mail `clgestionpe@pasar.net`
- **Panama ([pasarex.com/pa](https://pasarex.com/pa/)):** `+507 836 0399`; E-Mail `pagestionpe@pasar.net` und `pqr@pasar.net`
- **Barbados:** `+1 246 4370277`, `+1 305 7042435`
- **Unternehmensübersicht:** [PasarEX Colombia](https://pasarex.com/co/) und [Nosotros](https://pasarex.com/co/nosotros/)

## Häufige Fragen

### Was ist eine `AMZPSR`-Sendungsnummer?

`AMZPSR` gefolgt von 9 Ziffern (insgesamt 15 Zeichen, etwa `AMZPSR482917360`) ist eine Amazon-Nummer, die der PasarEX-Tracker abfragt. Kein anderer Paketdienst in Peek hat diese Form, Peek fragt PasarEX aber nicht automatisch an: Wähle PasarEX unter „Passt zu dieser Nummer“.

### Warum meldet mein PasarEX-Paket `Orden de pedido recibida - paquete no arribado`?

Das bedeutet, dass PasarEX das Sendungsmanifest erhalten hat, das physische Paket aber noch nicht in der Ziel-Importstation von PasarEX eingetroffen ist. Sobald der Sammelflug ankommt, folgen die nächsten Scans.

### Warum aktualisiert Peek PasarEX seltener als manche andere Paketdienste?

PasarEX erlaubt höchstens eine Abfrage pro Stunde, auch nach einem Fehlschlag, weshalb Peek stündlich prüft. Zudem erfordert der Tracker bei jeder Abfrage ein reCAPTCHA.

### Wie lange habe ich Zeit, in Kolumbien einen Schaden bei PasarEX zu melden?

Nach der auf [pasarex.com/co/soporte](https://pasarex.com/co/soporte/) genannten kolumbianischen CRC-Resolution 5050/2016 haben Empfänger **5 Werktage** nach Zustellung für Schadens- oder Fehlteilmeldungen, während Absendern 10 Kalendertage (Inland) oder 6 Monate (International) für Verlust oder Schaden zustehen.

:::sources
- [PasarEX Colombia](https://pasarex.com/co/)
- [PasarEX Colombia — Soporte y PQR](https://pasarex.com/co/soporte/)
- [PasarEX Colombia — Nosotros](https://pasarex.com/co/nosotros/)
- [PasarEX Chile](https://pasarex.com/cl/)
- [PasarEX Panamá](https://pasarex.com/pa/)
- [universal-parcel-scraper PasarEX-Adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pasarex)
:::
