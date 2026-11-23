---
title: nox NachtExpress Sendungsverfolgung: Status & Abstellplatz
description: nox-NachtExpress-Sendung verfolgen: wie der Barcode aussieht, was die Status bedeuten, Nachtzustellung zum Abstellplatz, Fristen und Kontakt.
slug: nox-sendungsverfolgung
published: 2026-11-23
updated: 2026-11-23
---

nox (nox NachtExpress) ist ein deutscher Nachtexpressdienst, die nox Germany GmbH mit Sitz in Langenfeld. Das Unternehmen befördert Ersatzteile und andere eilige Güter für Geschäftskunden. Es holt sie nachmittags ab und stellt sie über Nacht zu – in der Regel vor 8:00 Uhr (deutscher Zeit) an einem mit dem Empfänger vereinbarten Abstellplatz: ein Depotkasten, eine Garage, eine Werkstatt oder der Kofferraum eines Servicefahrzeugs. Verfolge eine Sendung mit dem Barcode über die [nox-Sendungsverfolgung](https://trackandtrace.app.nox.express/scannungen) oder füge die Nummer oben in das Feld ein.

## nox-Barcode: Format und wo du ihn findest

nox bezeichnet seine Sendungsnummer als Barcode. Jedes Packstück muss ein nox-Label tragen, das der Versender beim Buchen erstellt – der Absender hat den Barcode also ebenfalls. Keine Nummer? Frag beim Absender nach oder lies [Sendungsnummer finden](guide:find-tracking-number). Diese Formate kennt Peek:

| Barcode (erfunden) | Was er ist |
| --- | --- |
| `220004829173640715482917` | 24 Ziffern ab 22000: Mandantencode, 000, Kundennummer des Absenders, Druckdatum des Labels und laufende Nummer. Dieses Format kennt der Peek-Katalog. Auch [Ciblex](carrier:ciblex), [InPost](carrier:inpost), [bpost](carrier:bpost) und die [Österreichische Post](carrier:austrian-post) nutzen 24 Ziffern. |
| `SWR482917364058291736405` | SWR und 21 Ziffern: ein älterer nox-Barcode. |
| `RA1482917364058482917364` | RA, dann 1 oder 2 (Abholart), 12 Zeichen für die Kunden und eine 9-stellige Seriennummer: ein Retouren-Barcode. |
| `C48291736405829173640582917364` | C und 29 Ziffern: ein eigener Barcode des Absenders, den die nox-Sendungsverfolgung ebenfalls beantwortet. |
| `trackandtrace.app.nox.express/scannungen/SWR482917364058291736405` | Ein direkter Link zu einer Sendung im nox-Tracker. Füge den gesamten Link in Peek ein. |

Weitere nox-Barcodes bestehen aus 24 Ziffern mit einem anderen zweistelligen Mandantencode vor 000. In Peek spielen Leerzeichen keine Rolle, und Kleinbuchstaben funktionieren ebenfalls.

## nox-Sendungsstatus erklärt

Der nox-Tracker zeigt zu jedem Scan `Status`, `Scanort`, `Bemerkung`, `Datum` und `Uhrzeit`. Im Sprachgebrauch von nox steht Collo für ein einzelnes Packstück deiner Sendung und Niederlassung für einen nox-Standort.

| nox zeigt an | Was das für dich bedeutet | Status in Peek |
| --- | --- | --- |
| `Collo durch Versender bereitgestellt zur Abholung` | Der Versender hält das Packstück für nox bereit. nox hat es noch nicht abgeholt. | „Etikett erstellt“ |
| `Collo in der nox Niederlassung eingetroffen` | Das Packstück ist in einer nox-Niederlassung eingegangen. | „Unterwegs“ |
| `Collo von der nox Niederlassung weiterverladen` | Die Niederlassung hat es an eine weitere Niederlassung weiterverladen. | „Unterwegs“ |
| `Collo steht am Lager in nox Niederlassung, Zustellung nicht im geplanten Zeitfenster` | Es liegt in der Niederlassung und wurde nicht im geplanten Zeitfenster zugestellt. Es geht in einer späteren Nacht raus. | „Unterwegs“ |
| `Collo in nox Zustellfahrzeug geladen` | Es wurde ins Zustellfahrzeug geladen. | „In Zustellung“ |
| `Collo von nox zugestellt` | Zugestellt: meist ohne Unterschrift am vereinbarten Abstellplatz abgelegt. | „Zugestellt“ |

Meldet der Tracker `Der von Ihnen angegebene Barcode ist uns nicht bekannt.`, kennt nox diesen Barcode nicht: Prüfe ihn beim Absender. Für weitere Details oder eine Reklamation verlangt der Tracker die Empfänger-Postleitzahl über `PLZ validieren/Reklamation erfassen`.

## So liefert nox: nachts an deinen Abstellplatz

**Wann.** nox holt beim Versender üblicherweise zwischen 14:00 und 18:00 Uhr (deutscher Zeit) ab. Laut AGB liefert NachtExpress am Folgetag von Dienstag bis Samstag einschließlich Feiertagen in der Regel bis 8:00 Uhr (deutscher Zeit). Der Versender kann auch Zustellung vor 7:00 Uhr oder vor 6:00 Uhr (deutscher Zeit) buchen. Zudem bietet nox Wochenend- und Feiertagszustellungen als eigenen Service (WochenendService) an. Bei Packstücken über 100 kg oder länger als 3,20 m, breiter als 1,20 m oder höher als 1,50 m kann die Lieferzeit abweichen. Für Sendungen auf Nord- und Ostseeinseln gibt es keine Laufzeitzusage.

**Wo.** Da nox zustellt, während viele Kunden noch schlafen, legt der Fahrer die Sendung an einem vorab vereinbarten Abstellplatz ab, meist ohne Quittung. Beispiele von nox:

- eine separate Box auf deinem Firmengelände;
- Werkstatt, Waschhalle, Pausenraum oder Garage;
- der Kofferraum eines Servicetechniker-Fahrzeugs;
- der Pförtner, der die Sendung persönlich übernimmt;
- eine der nox-Servicestationen mit 24-Stunden-Zugang, etwa eine rund um die Uhr geöffnete Tankstelle.

**Abstellplatz festlegen.** Registriere oder ändere ihn jederzeit mit Fotos und Beschreibung im Online-Tool [ready2nox](https://ready2nox.de/). Laut AGB ist dies der einzige Weg zur Festlegung, und der Abstellplatz bleibt gültig, bis du ihn dort änderst. Sobald das Packstück dort liegt, gilt es als zugestellt.

**Kein Abstellplatz.** Laut AGB darf nox die Sendung dann an deiner Anschrift abstellen. Ist das Verlustrisiko offenkundig zu hoch, stellt nox sie gegebenenfalls nicht zu und schickt sie an den Versender zurück.

**Selbstabholung.** Als Zusatzleistung kannst du Sendungen stattdessen in der nox-Niederlassung abholen, wo nox sie für dich bereithält.

**Nachweis.** Ist niemand vor Ort, fotografiert nox das Packstück und den Ablageort und speichert die Fotos als Beleg. Im Einzelfall kann nox ein Foto bei berechtigtem Interesse herausgeben, ein Rechtsanspruch darauf besteht jedoch nicht.

**Außerhalb Deutschlands.** Über Schwestergesellschaften und Partner ist nox in 20 weiteren europäischen Ländern aktiv. Auslandssendungen erfordern eine vorherige Vereinbarung des Absenders mit nox, und Reklamationsregeln können je nach Land abweichen.

## nox-Sendung verspätet, beschädigt oder verschwunden

Für Ersatzlieferungen ist der Absender der Ware dein Ansprechpartner.

**Keine Bewegung.** Ist `Collo durch Versender bereitgestellt zur Abholung` noch der einzige Scan, hat nox das Packstück noch nicht abgeholt: Frag beim Absender nach. Lagert es in der Niederlassung, geht es in einer späteren Nacht raus (siehe Tabelle oben). Bei einer Verspätung nutze den nox-Tracker: Postleitzahl prüfen und dann `Laufzeitverzug` oder `Sonstige Anliegen` wählen. Siehe auch [warum die Sendungsverfolgung stehen bleibt](guide:tracking-not-updating).

**Zugestellt, aber nicht da.** Prüfe zuerst deinen vereinbarten Abstellplatz, dann weitere Ablagestellen im Betrieb. Frag den Pförtner oder den Techniker, dessen Fahrzeug beliefert worden sein könnte. Informiere nox dann rasch vor Ablauf der unten genannten Frist. Mehr unter [Paket als zugestellt markiert, aber nicht erhalten](guide:delivered-not-received).

**Beschädigt.** Bei NachtExpress muss jede Reklamation und Schadensmeldung bis 12:00 Uhr (deutscher Zeit) am Zustelltag schriftlich mit Beschreibung bei nox eingehen. Fällt der Zustelltag auf einen Samstag, Sonntag oder Feiertag, gilt 12:00 Uhr (deutscher Zeit) am nächsten Werktag. Ein pauschaler Vorbehalt reicht nicht, und nach Ablauf der Frist schließen die AGB eine Haftung aus. Bewahre die Ware in der Originalverpackung auf, damit nox sie prüfen und zurücknehmen kann, sonst haftet nox nicht. Melde den Schaden im Tracker nach PLZ-Prüfung (`Reklamation einer Beschädigung`), über das Kontaktformular oder im Kundenportal. Siehe [beschädigte Pakete](guide:damaged-parcel).

**Zoll.** Die nox-Seiten erwähnen Zoll lediglich als Leistung für Geschäftskunden bei Import und Export. Frag beim Absender nach und lies [Pakete beim Zoll](guide:customs).

## nox Kontakt und Kundenservice

Peek kann weder nox noch den Absender für dich kontaktieren. Kontaktdaten von den nox-Seiten:

- **Kundenservice:** +49 (0) 4224 920 000, Montag bis Freitag von 7:00 bis 17:00 Uhr (deutscher Zeit), oder customerservice@nox-nachtexpress.de (wie im nox-Tracker angegeben).
- **Kontaktformular:** [nox-Kontaktformular](https://www.nox.eu/de/de/kontakt/kontaktformular/) für Fragen und Reklamationen.
- **Reklamationen:** im Tracker nach der PLZ-Prüfung. Die Formulare (Verzug, Beschädigung, Sonstiges) fragen, ob du Empfänger, Versender oder ein beauftragender Dritter bist.
- **Unternehmen:** nox Germany GmbH, Katzbergstr. 3, 40764 Langenfeld, Deutschland; welcome@nox.eu.

Um alle Sendungen gebündelt zu verfolgen, braucht ein Unternehmen ein Konto im nox-Kundenportal: Anfrage über das Kontaktformular.

## nox-Sendung mit Peek verfolgen

Allein an der Nummer erkennt Peek nox nicht sicher: Die `22000`-Barcodes teilen ihre Länge mit Ciblex, InPost, bpost und der Österreichischen Post, und die übrigen Barcode-Formen ordnet Peek nicht per Format zu. Füge die Nummer dennoch ein: Peek verfolgt über 4.500 Paketdienste. Beim Hinzufügen eines `22000`-Barcodes fragt Peek die passenden Dienste ab – zuerst die deines Landes („Tracking-Dienste werden geprüft…“) – und zeigt „nox hat dieses Paket.“, sobald der nox-Tracker die Sendung kennt. Kennen mehrere Dienste sie, wählst du aus.

Kennt noch kein Dienst sie, steht „Noch kein Sendungsverlauf“, während Peek weiter prüft, oder „Warten auf nox“, sobald du nox gewählt hast. Zum eigenen Auswählen tippe auf „Ändern“ und wähle nox: Bei einem `22000`-Barcode steht es unter „Passt zu dieser Nummer“; bei einem SWR-, RA- oder C-Barcode suche danach (auch NOX Nachtexpress, Innight Express und TNT Innight finden es). Bei einer bereits hinzugefügten Sendung nutze „Paketdienst ändern“. Wählst du nox für eine Nummer mit anderem Format, meldet Peek, dass nox „vergibt keine Nummern wie diese“, und fragt nox nicht ab: Nach dem Hinzufügen erscheint – sofern kein Tracking-Dienst die Sendung kennt – „Sendungsnummer prüfen“. Ein Link aus dem nox-Tracker unter `trackandtrace.app.nox.express` oder `tcs.app.nox.express` ordnet die Sendung sofort nox zu.

Peek liest den Status, den deutschen Text jedes Scans samt Uhrzeit (in deutscher Zeit) und die Zustellzeit aus. Scanort und Bemerkung bleiben weg, und Peek fragt keine Postleitzahl ab, zeigt also nichts an, was erst nach der PLZ-Prüfung erscheint. Peek zeigt kein voraussichtliches Lieferdatum, kein Gewicht, keinen Abstellplatz, kein Zustellfoto und keinen Namen eines Unterzeichners.

Tag und Nacht prüft Peek, solange du die Sendung in Peek geöffnet hast oder am Zustelltag Benachrichtigungen aktiviert sind, bis zu alle 10 Minuten, und ab 6:00 Uhr deutscher Zeit am Tag, an dem nox eine Sendung in Zustellung scannt, bis zu alle 2 Minuten, solange der eigene nox-Tracker antwortet. Nach zwei Tagen ohne Neuigkeiten (nach 12 Stunden, solange die Sendung noch „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ zeigt) prüft Peek stündlich; bleibt sie sieben Tage lang bei „Etikett erstellt“, alle 6 Stunden.

Bleibt eine Sendung im Status „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“ vier Tage ohne neuen Scan, meldet Peek „Seit vier Tagen keine Aktualisierung“ (oder „Keine Aktualisierung seit …“), erklärt: „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei nox nach.“ und bietet „Hol dir Hilfe bei nox“ an. Peek kann dich bei „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“ benachrichtigen. Es kann keinen Abstellplatz einrichten und keine Reklamation für dich einreichen.

## Häufige Fragen zur nox-Sendungsverfolgung

### Was ist nox NachtExpress?

Der Nachtexpressdienst der nox Germany GmbH aus Langenfeld. Bis zum frühen Abend abgeholte Waren erreichen den Empfänger vor Arbeitsbeginn am nächsten Werktag. Zu den Kernbranchen zählen Automobil, Nutzfahrzeuge, Baumaschinen und Agrartechnik sowie Speziallösungen für das Gesundheitswesen.

### Wann liefert nox aus?

In der Nacht, in der Regel bis 8:00 Uhr (deutscher Zeit), von Dienstag bis Samstag einschließlich Feiertagen. Der Versender kann auch Zustellung vor 7:00 Uhr oder vor 6:00 Uhr (deutscher Zeit) buchen.

### Wo legt nox meine Sendung ab?

Am vereinbarten Abstellplatz für deine Adresse, den du in nox’ Tool ready2nox hinterlegst. Ohne Abstellplatz darf nox sie an deiner Anschrift abstellen.

### Braucht nox eine Unterschrift?

Meist nicht: nox stellt ohne Quittung zu, oft wenn niemand vor Ort ist, und fotografiert das Packstück am Ablageort. Beim Pförtner übergibt der Fahrer es persönlich.

### Wie melde ich eine beschädigte nox-Lieferung?

Bei NachtExpress bis 12:00 Uhr (deutscher Zeit) am Zustelltag schriftlich über den nox-Tracker oder das Kontaktformular. Bewahre Ware und Originalverpackung auf. Fällt der Zustelltag auf ein Wochenende oder einen Feiertag, hast du bis 12:00 Uhr (deutscher Zeit) am nächsten Werktag Zeit.

:::sources
- [nox: Sendungsverfolgung/Reklamation erfassen](https://www.nox.eu/de/de/kundenbereich/sendungsverfolgung/) – Barcode-Formular zum nox-Tracker, Kundenportal für alle Sendungen
- [nox: Track & Trace](https://trackandtrace.app.nox.express/scannungen) – Spalten des Trackers, unbekannter Barcode, PLZ-Prüfung, Reklamationsformulare, Telefon, Zeiten und E-Mail des Kundenservice, ready2nox
- [nox: Hinweise zum Nachtversand](https://www.nox.eu/de/de/kundenbereich/hinweise-zum-nachtversand/) – Abholung 14:00 bis 18:00 Uhr, Zustellung vor 8:00 Uhr, Abstellplätze, Großpackstücke, 20 weitere Länder, Reklamationen über Kontaktformular, Schadensfrist
- [nox: NachtExpress](https://www.nox.eu/de/de/leistungen/nachtexpress/) – vor 8:00, 7:00 oder 6:00 Uhr, quittungslose Zustellung, Beispiele für Abstellplätze, Abholung in der Niederlassung, Zollservice
- [nox: Abstellplatzmanagement](https://www.nox.eu/de/de/leistungen/abstellplatzmanagement/) – Beispiele für Abstellplätze, ready2nox mit Fotos und Beschreibungen
- [nox: Über uns](https://www.nox.eu/de/de/ueber-uns/) – Zustellung vor Arbeitsbeginn, schlafende Kunden, Kernbranchen
- [nox: Allgemeine Geschäftsbedingungen, from 1 September 2026 (PDF)](https://cms.nox.eu/de/wp-content/uploads/sites/4/2026/09/Allgemeine-Geschaftsbedingungen-fur-Geschaftskunden-nox-DE-Sept-2026-Final.pdf) – nox-Labels, Zustelltage, Abstellplatz über ready2nox, Zustellung ohne Abstellplatz, Fotos, Inseln, Auslandsversand, Reklamationsfrist, Länderregeln
- [nox: Allgemeine Geschäftsbedingungen](https://www.nox.eu/de/de/agb/) – geltende AGB
- [nox: Kontakt](https://www.nox.eu/de/de/kontakt/kontaktformular/) – Kontaktformular
- [nox: Impressum](https://www.nox.eu/de/de/impressum) – nox Germany GmbH, Langenfeld, Telefon, E-Mail
- [Universal Parcel Scraper: nox](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/nox) – Barcode-Formate, Tracker-Links, Scan-Wortlaute und Stufen, deutsche Zeit, was Peek ausliest
:::
