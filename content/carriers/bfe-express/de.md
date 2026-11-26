---
title: BFE Express Sendungsverfolgung: Nummern, Status & Hilfe
description: Verfolge BFE-Express-Pakete nach Argentinien, Peru, Uruguay und in die USA: CK- und WYB-Nummern, Zollübergabe, Statusmeldungen und Support-Mails.
slug: bfe-express-sendungsverfolgung
published: 2026-11-24
updated: 2026-11-24
---

BFE Express (*BFEexpress*, betrieben von Hongkong Bfe Info Tech Co Limited und 2005 gegründet) ist ein grenzüberschreitendes E-Commerce-Logistik- und Zustellnetzwerk, das internationale Verkäufer — darunter Händler auf Temu Argentinien — mit Empfängern in Argentinien, Peru, Uruguay und den Vereinigten Staaten verbindet. Das Unternehmen übernimmt internationale Fracht, Zollabfertigung, lokale Lagerung und die Zustellung auf der letzten Meile mit Sendungsverfolgung auf [bfeexpress.com/tracking](https://www.bfeexpress.com/tracking).

Da BFE Express den internationalen Ankunfts- und Zollabschnitt steuert, bevor die Sendungen an regionale Zustelldepots gehen, kombiniert ein Verlauf häufig englische Flughafen- und Zollscans mit spanischen Zustellscans vor Ort.

## BFE Express Sendungsnummern im Überblick

Peek nimmt BFE-Express-Nummern mit 6 bis 50 Buchstaben und Ziffern an. Das Kontaktformular von BFE Express akzeptiert diese Formate nach Zielland:

| Format (erfunden) | Zielland & Details |
| --- | --- |
| `CK54829173604518` | `CK54` + 12 Ziffern (16 Zeichen) — Argentinien; kein anderer Paketdienst in Peek nutzt diese Form. |
| `CK548291736045182` | `CK54` + 13 Ziffern (17 Zeichen) — alternatives Argentinien-Format (überschneidet sich in der Länge mit [Colis Privé](carrier:colis-prive)). |
| `CK512829173604518` | `CK512` + 12 Ziffern (17 Zeichen) — Peru-Format (überschneidet sich in der Länge mit [Colis Privé](carrier:colis-prive)). |
| `CK598829173604518` | `CK598` + 12 Ziffern (17 Zeichen) — Uruguay-Format (überschneidet sich in der Länge mit [Colis Privé](carrier:colis-prive)). |
| `WYB482917360` | `WYB` + 9 Ziffern (12 Zeichen) — Frachtbrief-Format für Peru (überschneidet sich mit [Purolator](carrier:purolator) und [TNT Australia](carrier:tnt-au)). |

Die Ziffern nach `CK` stehen für das Zielland: `54` für Argentinien, `512` für Peru und `598` für Uruguay. Fügst du einen direkten Link wie `bfeexpress.com/track?track_number=...` ein, liest Peek die Nummer aus und wählt sofort BFE Express. Weitere internationale Muster erklärt der Leitfaden zu [Sendungsnummern-Formaten](guide:tracking-number-formats).

## Bedeutung der BFE Express Statusmeldungen

Der Tracker von BFE Express zeigt zwei Gruppen von Zeilen — den internationalen ersten Abschnitt und die letzte Meile — und Peek wertet beide aus.

| BFE Express Wortlaut | Was er bedeutet |
| --- | --- |
| `Arrived at Airport of destination` | Der Flug mit deinem Paket ist im Zielland gelandet. |
| `Transport to next facility` | Weitertransport zwischen Flughafenterminal und lokalem Zoll- oder Sortierdepot. |
| `Customs declaration completed` | Zollabfertigung im Zielland abgeschlossen, vor der Übergabe an die letzte Meile. |
| `Turned over to last mile facility` | Übergabe vom BFE-Zollabschnitt an das örtliche Zustelldepot. |
| `Se ha recibido el paquete.` / `Tu paquete fue recibido por en: ...` | Im lokalen Zustelldepot in Argentinien oder Uruguay eingegangen. |
| `En embarque` / `En transporte` / `Tu paquete está en viaje a ...` | Verladen und unterwegs zwischen inländischen Depots Richtung Zielstadt. |
| `Tu paquete se encuentra en ...` / `En espera para salir a reparto en ...` | Im zuständigen Auslieferungsdepot in Wartestellung für die nächste Zustelltour. |
| `Reparto en curso` | Heute mit dem lokalen Zusteller unterwegs. |
| `Entregado` | An den Empfänger zugestellt. Daneben meldet der eigene Sendungsstatus von BFE `Delivered`. |

In den englischen Zeilen von BFE Express steht der Ort vor einem Komma (etwa `<City>, Customs declaration completed`); die spanischen Zeilen in Argentinien nennen zuerst das Land, während die letzte Meile in Uruguay den Ort nach `en` oder `a` nennt. Wenn der eigene Sendungsstatus von BFE `Delivered` lautet, markiert Peek das Paket auch dann als zugestellt, wenn die neueste Zeile etwas anderes nennt.

## Zollabfertigung, Übergabe an die letzte Meile und Verzögerungen

Die Website von BFE Express bietet Paketversand, internationale Fracht, Zollabfertigung, lokale Lagerung sowie Zustellung auf der letzten Meile und richtet sich auch an Verkäufer auf Temu Argentinien. Ein paar Punkte helfen beim Lesen des Verlaufs:

- **Vor dem Zielflughafen:** Zeigt der Tracker von BFE Express noch nichts an, prüfe die Bestellseite des Verkäufers und lies den Leitfaden bei [stehengebliebener Sendungsverfolgung](guide:tracking-not-updating).
- **Zoll und letzte Meile:** `Customs declaration completed` und `Turned over to last mile facility` können im Abstand von einer Stunde am selben Ort erscheinen, während der Eingang im lokalen Depot (`Se ha recibido el paquete.` oder `Tu paquete fue recibido por en: ...`) einen Tag später folgt. Hängt eine Sendung beim Zoll im Zielland, hilft der [Zoll-Leitfaden](guide:customs).
- **Geplanter Zustelltag:** BFE Express kann unterwegs einen geplanten Zustelltag nennen, den Peek anzeigt. Peek blendet ihn wieder aus, sobald das Paket zugestellt, zurückgesandt oder abholbereit ist oder sobald ein Scan nach diesem Tag liegt.
- **Als zugestellt markiert oder beschädigt:** Die [BFE-Express-Supportseite](https://www.bfeexpress.com/contactUS) führt beide Fälle in den FAQ auf und bittet darum, die Sendungsnummer bereitzuhalten, wenn du dein lokales Team kontaktierst. Siehe auch den Leitfaden [zugestellt, aber nicht erhalten](guide:delivered-not-received).

Für reine Inlandspakete in Argentinien mit örtlichen Zustellern vergleiche [OCA](carrier:oca) und [Andreani](carrier:andreani).

## BFE Express mit Peek verfolgen

Füge deinen `CK...`- oder `WYB...`-Code in Peek ein, das mehr als 4.500 Paketdienste verfolgt. An der Form allein erkennt Peek nicht, dass die Nummer von BFE Express stammt. Deshalb fragt Peek beim Hinzufügen BFE Express („Tracking-Dienste werden geprüft…“) und zeigt „BFE Express hat dieses Paket.“, wenn BFE Express die Nummer kennt. Bei einem 16-stelligen `CK54`-Code fragt es nur BFE Express. Bei einem 17-stelligen `CK`-Code fragt es auch [Colis Privé](carrier:colis-prive): in Frankreich zuerst, anderswo erst, wenn BFE Express und die Tracking-Dienste die Nummer nicht kennen. Bei einem `WYB`-Code fragt es BFE Express, [TNT Australia](carrier:tnt-au) und [Purolator](carrier:purolator): in Argentinien, Peru, Uruguay und den USA zuerst BFE Express, in Kanada zuerst Purolator, anderswo zuerst TNT Australia. Um das Paket von Anfang an bei BFE Express zu verfolgen, wähle beim Hinzufügen „Ändern“ und tippe unter „Passt zu dieser Nummer“ auf BFE Express — oder nutze auf der Paketseite „Paketdienst ändern“. Ein Link `bfeexpress.com/track?track_number=...` führt sofort zu BFE Express.

Peek ruft die Tracking-API von BFE Express direkt über den Open-Source-[BFE-Express-Adapter von universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/bfe-express) ab. Es liest beide Zeilengruppen in zeitlicher Reihenfolge, übernimmt Stadt, Filiale oder Land als Ort und behält den geplanten Zustelltag sowie das Zielland. Eine Zeitangabe mit GMT-Offset behält genau diesen Zeitpunkt; eine Zeit ohne Offset datiert Peek auf die Zeitzone des Ortes (Buenos-Aires-Zeit für Ezeiza, Lima-Zeit für Lima, Montevideo-Zeit für Montevideo), einen Scan ohne Ort auf die Zeitzone des Nachbar-Scans, sonst auf Buenos-Aires-Zeit, und zeigt alle Scans in der Uhrzeit deines Geräts an. Straßenadressen und Zustellfotos aus dem BFE-Portal werden verworfen.

Peek ordnet die Zeilen von BFE Express als „Unterwegs“ ein — außer `Reparto en curso` als „In Zustellung“ und `Entregado` als „Zugestellt“. Ebenso markiert Peek das Paket als „Zugestellt“, wenn der eigene Sendungsstatus von BFE `Delivered` lautet.

Solange du das Paket in Peek geöffnet hast oder am Zustelltag Benachrichtigungen aktiviert sind, fragt Peek bis zu alle 10 Minuten nach; an dem Tag, an dem BFE Express `Reparto en curso` meldet, ab 6 Uhr auf der Uhr des neuesten Scans alle 2 Minuten, solange der Tracker von BFE Express antwortet. Andernfalls prüft Peek stündlich. Nach 12 Stunden mit lediglich „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ oder nach zwei Tagen ohne Neuigkeiten prüft Peek stündlich, egal wie du die Sendung verfolgst, und nach sieben Tagen auf „Etikett erstellt“ alle 6 Stunden. Antwortet BFE Express nicht und Peek verfolgt das Paket allein über Tracking-Dienste, prüft Peek stündlich, nach einem ruhigen Tag alle 3 Stunden und an dem Tag, an dem das Paket in Zustellung ist, bis zu alle 15 Minuten, solange du die Sendung geöffnet hast oder Benachrichtigungen aktiviert sind.

Ist der neueste Scan vier Tage alt und das Paket steht noch auf „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“, markiert Peek es mit dem Tag dieses Scans, etwa „Keine Aktualisierung seit Mo 12. Okt“. Auf der Paketseite steht dann „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei BFE Express nach.“ samt dem Link „Hol dir Hilfe bei BFE Express“ zur Kontaktseite von BFE Express. Nach drei Wochen ohne neuen Scan heißt es „Seit über drei Wochen nichts Neues. Ist das Paket noch nicht angekommen, frag beim Verkäufer nach. Peek fragt weiter bei BFE Express nach.“

Über Peeks Benachrichtigungs-Voreinstellungen — „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“ — legst du fest, welche Ereignisse eine Meldung auslösen.

## Kontakt zu BFE Express

BFE Express nennt auf seiner [Kontaktseite](https://www.bfeexpress.com/contactUS) regionale Support-E-Mail-Adressen, besetzt von **Montag bis Freitag, 9:00 bis 18:00 Uhr** (außer an Feiertagen):

- **Argentinien:** `info@ar.bfeexpress.com`
- **Peru:** `info@pe.bfeexpress.com`
- **Uruguay:** `info@uy.bfeexpress.com`
- **Vereinigte Staaten:** `info@us.bfeexpress.com`
- **Paraguay:** `info_py@bfeexpress.com`
- **Weitere unterstützte Regionen (Kolumbien, Ecuador, Serbien, Dominica):** `info@other.bfeexpress.com`
- **Offizielle Website & Tracking:** [bfeexpress.com](https://www.bfeexpress.com/) und [bfeexpress.com/tracking](https://www.bfeexpress.com/tracking)

Halte deine vollständige `CK...`- oder `WYB...`-Nummer bereit, wenn du dem zuständigen Länderteam schreibst.

## Häufig gestellte Fragen

### Warum beginnt mein BFE-Express-Tracking erst am Zielflughafen ohne frühere Scans?

Der Tracker von BFE Express kann erst mit `Arrived at Airport of destination`, der Ankunft des Flugs im Zielland, beginnen. Für den Versand davor prüfe die Bestellseite beim Verkäufer.

### Warum sind meine BFE-Express-Scans sowohl auf Englisch als auch auf Spanisch?

BFE Express erfasst die internationale Flughafenankunft, Zollanmeldung und Übergabe an die letzte Meile auf Englisch, während das lokale Zustelldepot für den letzten Abschnitt seine Scans auf Spanisch einträgt (bis zu `Reparto en curso` und `Entregado`).

### Was bedeuten die Ziffern `54`, `512` und `598` in `CK`-Sendungsnummern?

Sie kennzeichnen das Zielland: `CK54...` steht für Argentinien (16 oder 17 Zeichen), `CK512...` für Peru (17 Zeichen) und `CK598...` für Uruguay (17 Zeichen). Sendungen nach Peru können außerdem ein 12-stelliges Format aus `WYB` + 9 Ziffern nutzen.

### Was tun, wenn BFE Express meine Nummer nicht findet?

Prüfe deinen `CK`- oder `WYB`-Code auf fehlende Zeichen und frage den Verkäufer, ob er das Paket bereits an BFE Express übergeben hat.

:::sources
- [BFE Express](https://www.bfeexpress.com/)
- [BFE Express Tracking](https://www.bfeexpress.com/tracking)
- [BFE Express Customer Support](https://www.bfeexpress.com/contactUS)
- [universal-parcel-scraper BFE Express adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/bfe-express)
:::
