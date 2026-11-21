---
title: GOFO Italy Sendungsverfolgung: GFIT-Nummern und Status
description: GOFO-Paket in Italien verfolgen: Aufbau der GFIT- und CIIT-Nummern, Bedeutung der italienischen Status, AGCOM-Beschwerdeweg und Tracking in Peek.
slug: gofo-italy-sendungsverfolgung
published: 2026-11-21
updated: 2026-11-21
---

GOFO Italy ist das italienische Netz von GOFO, einem Paketdienst mit Netzen in mehreren Ländern, und stellt Sendungen innerhalb Italiens zu. In der Paketdienstliste von Peek erscheint es auch als CIRRO Parcel Italy. Die Sendungsnummern beginnen mit `GFIT` oder `CIIT`: Verfolge ein Paket auf der italienischen Tracking-Seite von GOFO unter gofo.com oder füge die Nummer oben ein. Noch keine Nummer? Siehe [Sendungsnummer finden](guide:find-tracking-number).

## GOFO-Italy-Sendungsnummer: So sieht sie aus

Diese Formate ordnet Peek GOFO Italy zu, groß oder klein geschrieben:

| Format | Erfundenes Beispiel |
| --- | --- |
| `GFIT` und 13 Ziffern | `GFIT4829105736284` |
| `GFIT` und 14 Ziffern (die letzte als Prüfziffer) | `GFIT48291057362848` |
| `CIIT` und 13 Ziffern | `CIIT4829105736284` |
| `CIIT` und 14 Ziffern | `CIIT48291057362841` |

Die übrigen Netze von GOFO folgen demselben Muster mit ihrem eigenen Länderkürzel nach `GF` oder `CI`. `GFFR` und `CIFR` gehören zu [GOFO France](carrier:gofo-fr), `GFNL` zu GOFO Niederlande und `GFES` zu GOFO Spanien. `GFUS` mit 14 Ziffern, `GF` mit 13 oder `CR` mit 12 gehören zu [GOFO Express](carrier:gofo) in den USA, einem eigenen Netz, und `GFCA` mit 14 Ziffern zu GOFO Canada. Eine Nummer mit `GFIT` und 12 oder 15 Ziffern – oder eine 14-stellige `GFIT`-Nummer mit falscher Prüfziffer – ordnet Peek nicht GOFO Italy zu: Prüfe auf Tippfehler. Mehr dazu unter [Sendungsnummern-Formate](guide:tracking-number-formats).

## GOFO-Statusmeldungen: Was die italienischen Texte bedeuten

Peek liest das italienische Tracking von GOFO aus, sodass die Scans unabhängig von deiner Sprache in GOFOs italienischem Wortlaut erscheinen. Die üblichen Meldungen in typischer Reihenfolge:

| GOFOs Wortlaut | Was das für dich bedeutet |
| --- | --- |
| `Il pacco è in fase di preparazione da parte del mittente e ci sarà presto consegnato` | Der Absender bereitet das Paket vor und übergibt es bald an GOFO. |
| `Registrato presso il centro di trasferimento` | In einem GOFO-Umschlagszentrum erfasst. |
| `Il pacco ha lasciato il centro di smistamento` | Hat ein GOFO-Sortierzentrum verlassen. |
| `Arrivato alla struttura di destinazione regionale GOFO` | Im regionalen GOFO-Zentrum für dein Gebiet angekommen. |
| `Preparazione alla consegna` | Wird im Depot für die Abfahrt des Fahrers vorbereitet. |
| `Il corriere è fuori per la consegna` | Unterwegs mit dem Zusteller. |
| `Consegna fallita, tipo:Destinatario assente` | Nicht zugestellt: niemand angetroffen. |
| `Consegna fallita, tipo:L'indirizzo è errato` | Nicht zugestellt: falsche Adresse. |
| `Il pacco è stato restituito al centro di smistamento` | Nach einem fehlgeschlagenen Versuch zurück im GOFO-Sortierzentrum. |
| `Consegnato` | Zugestellt. |

Zwei Zeilen führen oft in die Irre. Solange der ersten Zeile kein weiterer Scan folgt, hat GOFO das Paket noch nicht, und Peek zeigt „Etikett erstellt“. Und `Il pacco è stato restituito al centro di smistamento` bedeutet nicht, dass das Paket an den Shop zurückgeht: Es liegt wieder im GOFO-Sortierzentrum, und Peek zeigt „Problem gemeldet“. Eine Zustellung erscheint in jeder Sprache schlicht als `Consegnato`: GOFOs eigene Zustellzeile kann weitere Angaben (etwa zum Empfänger) enthalten, die Peek weglässt.

GOFO ordnet Sendungen zudem groben Kategorien zu. `Avviso` meldet ein von GOFO vermerktes Problem, das Peek als „Problem gemeldet“ zeigt; für sich allein bedeutet es keinen Zustellversuch. `Restituito` steht für ein Paket auf dem Rückweg zum Absender („Zurück an Absender“).

Hat [YunExpress](carrier:yunexpress) das Paket an GOFO Italy übergeben, kann das Tracking von YunExpress GOFO-Scans auf Englisch weitergeben: `Preparing for delivery` im Depot vor der Tour und `Package departed from sorting center` neben dem italienischen `Il pacco ha lasciato il centro di smistamento`.

## GOFO-Paket verspätet, verpasst, verloren oder beschädigt

**Keine Bewegung.** Peek fragt weiter bei GOFO nach. Vergehen vor der Zustelltour vier Tage ohne neuen Scan, meldet Peek das („Seit vier Tagen keine Aktualisierung“) und verlinkt auf die Hilfeseite von GOFO Italy („Hol dir Hilfe bei GOFO Italy“). Siehe auch [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating).

**Verpasste Zustellung.** Nach einer Meldung mit `Consegna fallita` unter dem Hinweiscode von GOFO – ob `Destinatario assente`, `L'indirizzo è errato` oder ein anderer Grund nach `tipo:` – zeigt Peek „Zustellversuch“. Wie es weitergeht, liegt bei GOFO: Hinweise stehen unter [verpasste Zustellung](guide:missed-delivery).

**Verspätet.** Nach EU-Verbraucherrecht muss ein Online-Shop binnen 30 Tagen liefern, sofern nichts anderes vereinbart wurde. Geschieht das nicht, setze eine angemessene Nachfrist; verstreicht auch diese, kannst du vom Vertrag zurücktreten und bekommst dein Geld zurück.

**Als `Consegnato` markiert, aber nichts angekommen.** Der Shop haftet für die Lieferung: Er muss den Paketdienst kontaktieren und die Zustellung nachweisen; gelingt das nicht, muss er erneut liefern oder erstatten. Was du rund ums Haus zuerst prüfen solltest, steht unter [Paket zugestellt, aber nicht erhalten](guide:delivered-not-received).

**Beschädigt.** Der Shop haftet für Schäden vom Versand bis zur Übergabe; prüfe das Paket daher nach Möglichkeit im Beisein des Zustellers. Ist der Karton sichtbar beschädigt, weist die italienische Postregulierungsbehörde AGCOM darauf hin, dass du die Annahme verweigern oder unter Vorbehalt annehmen kannst. Mehr in [beschädigtes Paket](guide:damaged-parcel).

## Beschwerde über eine GOFO-Italy-Lieferung einreichen

Beginne beim Shop, der das Paket verschickt hat, da er für die Zustellung einsteht. Für Beschwerden gegen den Paketdienst selbst legt die italienische Regulierungsbehörde AGCOM einen festen Ablauf für Postbetreiber fest, wobei Pakete bis 31,5 kg als Postsendungen gelten:

1. Reiche beim Betreiber eine schriftliche Beschwerde ein – über das Formular auf seiner Website oder eines, das du per E-Mail anforderst. Er muss binnen 45 Tagen antworten.
2. Befriedigt dich die Antwort nicht oder bleibt sie nach 45 Tagen aus, beantrage beim Betreiber eine Schlichtung. Sie endet innerhalb von 60 Tagen.
3. Scheitert auch sie, hast du 90 Tage Zeit, bei der AGCOM mit dem Formulario CP eine Streitbeilegung zu beantragen. Die AGCOM entscheidet binnen 150 Tagen verbindlich für beide Seiten.

Die AGCOM kann Erstattungen oder vorgesehene Entschädigungen anordnen, jedoch keinen Schadensersatz – dafür und jederzeit steht dir der ordentliche Rechtsweg offen. Antwortet der Betreiber nicht auf Beschwerde oder Schlichtungsantrag, melde ihn über das Portale delle segnalazioni der AGCOM. Das Kontaktcenter der AGCOM ist montags bis freitags von 10:00 bis 14:00 Uhr unter `800.185060` (italienisches Festnetz) oder `081.750750` (Mobilfunk oder Ausland) erreichbar.

## GOFO Italy Kundenservice

Die Hilfeseite von GOFO Italy liegt auf der italienischen Website gofo.com; Peek verlinkt sie als „Hol dir Hilfe bei GOFO Italy“, wenn vor der Zustelltour vier Tage kein neuer Scan erscheint. Die Gesellschaft dahinter, GOFO Italia s.r.l., hat ihren eingetragenen Sitz in der Via Juan Manuel Fangio 11, 20045 Lainate (MI). Halte deine `GFIT`- oder `CIIT`-Nummer sowie deine Bestellnummer bereit. Die AGCOM erinnert zudem daran, dass jeder Postbetreiber den Nutzern kostenlose Kommunikationskanäle bereitstellen muss.

## Ein GOFO-Italy-Paket mit Peek verfolgen

[Peek](/) erkennt `GFIT`- und `CIIT`-Nummern direkt (bei 14-stelligen `GFIT`-Nummern samt Prüfziffer). Du kannst auch einen Link der italienischen Tracking-Seite von GOFO einfügen (auf gofo.com oder gofoexpress.it, mit `id=` und der Nummer).

Solange du das Paket geöffnet hast oder am Zustelltag mit aktivierten Mitteilungen, und solange das Tracking von GOFO antwortet, prüft Peek bis zu alle 10 Minuten, und ab 6 Uhr italienischer Zeit am Tag, an dem GOFO das Paket in Zustellung scannt, bis zu alle 2 Minuten; sonst stündlich. Über Tracking-Dienste prüft Peek stündlich, am Zustelltag bis zu alle 15 Minuten, solange du es geöffnet hast oder Mitteilungen aktiv sind. Nach zwei Tagen ohne Neuigkeiten (nach 12 Stunden bei „Etikett erstellt“ oder „Beim Paketdienst eingegangen“) prüft Peek stündlich.

Peek zeigt den Status, den Verlauf mit dem von GOFO genannten Ort je Scan, die Zustellzeit und diese Orte auf einer Karte. Nennt GOFO nur einen Depotcode oder das Land, bleibt der Ort beim Scan leer. Die Zeiten werden nach italienischer Zeit gelesen und in deiner Zeitzone angezeigt; auf peektracker.com sowie in den iPhone- und Android-Apps erinnert eine Zeile unter dem Verlauf daran („Uhrzeiten in deiner Zeitzone: …“). Mitteilungen lassen sich auf „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“ einstellen. Bis GOFO einen ersten Scan führt, zeigt Peek „Warten auf GOFO Italy“ und fragt weiter nach. Kennt GOFO die Nummer nach zwei Tagen noch immer nicht und hat auch keine andere Quelle geantwortet, zeigt Peek „Auch nach zwei Tagen kennt der Paketdienst diese Nummer nicht. Prüfe sie, wähle einen anderen Paketdienst oder lösche das Paket.“ und prüft ab dann täglich, nach einer Woche wöchentlich. Kennt GOFO die Sendung bereits ohne Scan, bleibt es bei „Warten auf GOFO Italy“ ohne Prüfhinweis. Peek zeigt GOFOs voraussichtliches Lieferdatum, bis das Paket zugestellt, zurückgesendet oder abholbereit ist; Gewicht, Zustellfotos und Hinweise von GOFO lässt es weg. Es kann weder GOFO noch den Shop für dich kontaktieren.

## Fragen zum GOFO-Italy-Tracking

### Wo ist mein GOFO-Paket?

Das zeigt die jüngste Tracking-Zeile. `Arrivato alla struttura di destinazione regionale GOFO` bedeutet, dass es im regionalen GOFO-Zentrum liegt, `Preparazione alla consegna`, dass es für den Fahrer vorbereitet wird, und `Il corriere è fuori per la consegna`, dass es unterwegs zum Empfänger ist. Erscheint nur die Vorbereitungszeile des Absenders, hat GOFO das Paket noch nicht übernommen.

### Ist GOFO Italy seriös?

Die italienische Gesellschaft ist GOFO Italia s.r.l. mit eingetragenem Sitz in Lainate bei Mailand. Die Postregulierungsbehörde AGCOM hält in ihrer Determina n. 12/26/DSP vom 2. September 2026 fest, dass sie der Gesellschaft am 17. Juni 2026 drei mutmaßliche Verstöße mitteilte: Posttätigkeit im Bereich des Universaldienstes ohne die erforderliche Einzellizenz, fehlende Dienstleistungscharta (Carta dei servizi) sowie fehlende Streitbeilegungsverfahren für Nutzer. Laut Verfügung räumte die Gesellschaft die Vorwürfe zur Carta dei servizi und zu den Streitbeilegungsverfahren ein und zahlte das ermäßigte Bußgeld, sodass AGCOM diesen Teil des Verfahrens abschloss; den Vorwurf zur unzureichenden Genehmigung bestritt sie, sodass dieser Teil im regulären Verfahren weitergeführt werden sollte.

### Sind GOFO und CIRRO Parcel dasselbe?

In Italien für die Sendungsverfolgung ja: Peeks Paketdienstliste führt GOFO Italy auch unter dem Namen CIRRO Parcel Italy. Ein Tracking, das nur „Cirro“ nennt, kann jedoch GOFOs italienisches, französisches oder niederländisches Netz meinen oder Cirros eigene internationale Linie, die Pakete unter deren Nummern an nationale Postdienste übergibt.

### Was kann ich tun, wenn mein GOFO-Paket verloren ist?

Kontaktiere den Shop. Nach EU-Recht trägt er die Verantwortung für die Lieferung bis zur Übergabe: Kann er die Zustellung nicht belegen, muss er neu liefern oder erstatten. Liegt der Fehler beim Paketdienst, greift zusätzlich der oben genannte Beschwerdeweg der AGCOM. Mehr unter [verlorenes Paket](guide:lost-parcel).

:::sources
- [AGCOM: Determina n. 12/26/DSP](https://www.agcom.it/sites/default/files/provvedimenti/determina/2026/Determina%20arch%20parziale%2012_26_DSP%20-%20Gofo%20pdf.pdf) – GOFO Italia s.r.l., Sitz in Lainate, AGCOM-Verfahren 2026 und ermäßigte Geldbuße (2. September 2026)
- [AGCOM: FAQ contenzioso tra utenti e operatori postali](https://www.agcom.it/faq-contenzioso-tra-utenti-e-operatori-postali) – Beschwerdefrist 45 Tage, Schlichtung 60 Tage, Formulario CP binnen 90 Tagen, Entscheidung binnen 150 Tagen, Pakete bis 31,5 kg, beschädigte Pakete, kostenlose Kanäle, Kontaktcenter
- [Your Europe: Shipping and delivery](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_en.htm) – Lieferung binnen 30 Tagen, Nachfrist, Haftung für Schäden bis zum Erhalt
- [Your Europe: FAQs, shipping and delivery](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/faq/index_en.htm) – Nachweispflicht des Shops für die Zustellung, Prüfung bei Übergabe
- [Universal Parcel Scraper: GOFO Italy](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/gofo-it) – `GFIT`/`CIIT`-Formate samt `gofo-europe`-Prüfziffer, Links, italienische Scans und Kategorien, `Consegna fallita` unter Code `206`, CIRRO Parcel Italy, übernommene und verworfene Felder
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – übrige GOFO-Netze, englische YunExpress-Weiterleitungen, Ende der Lieferdatumsanzeige (`GOFO_ENDED_STAGES`)
:::
