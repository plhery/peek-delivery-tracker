---
title: GOFO Express Sendungsverfolgung: GFUS-Nummer & Status
description: Verfolge dein GOFO-Express-Paket in den USA: Nummernformate GFUS, GF, CR und MGP, Bedeutung englischer Scan-Status und Hilfe bei Verzögerungen.
slug: gofo-express-sendungsverfolgung
published: 2026-11-17
updated: 2026-11-17
---

GOFO Express ist ein Paketdienst, der Sendungen in den Vereinigten Staaten zustellt: Er holt Pakete im Lager des Absenders oder über einen Versandpartner ab, leitet sie über Sortierzentren und regionale Hubs an die lokale Zustellbasis weiter und bringt sie mit eigenen Fahrern an die Haustür. Egal ob du eine Bestellung an eine US-Adresse schickst oder vor Ort in den USA auf eine Lieferung wartest: Füge die Sendungsnummer oben in das Suchfeld ein; GOFO betreibt außerdem eine eigene Sendungsverfolgung auf seiner Website. Die Nummer erhältst du vom Online-Shop, bei dem du bestellt hast. Falls du sie nicht findest, lies [wie du deine Sendungsnummer findest](guide:find-tracking-number).

## GOFO Express Sendungsnummer: Formate in den USA

GOFO Express nutzt in den Vereinigten Staaten vier eigene Nummernserien. Hier sind sie mit erfundenen Ziffern – zusammen mit zwei Partnerformaten, die bei denselben Paketen vorkommen:

| Nummer (erfunden) | Was dahintersteckt |
| --- | --- |
| `GFUS12345678901234` | `GFUS` und 14 Ziffern: die reguläre US-Frachtbriefnummer (*Waybill*) von GOFO. Steht direkt nach `GFUS` versehentlich der Buchstabe `O` statt einer Null, liest Peek ihn automatisch als `0`. |
| `GF1234567890123` | `GF` und 13 Ziffern: eine ältere US-Nummernserie, die der GOFO-Tracker weiterhin abfragt. |
| `CR123456789012` | `CR` und 12 Ziffern: eine weitere US-Serie von GOFO, teils zusätzlich zu einer `GFUS`-Nummer vergeben. |
| `MGP1234567890123` | `MGP` und 13 Ziffern: eine Zweitnummer, die GOFO bei manchen `GFUS`-Frachtbriefen als Sendungsnummer speichert. |
| `FC1234567890123` | `FC` und 13 Ziffern: eigene Nummer von [DHLink](carrier:dhlink) für DHgate-Pakete, die GOFO in den USA ausliefert. |
| `YT1234567890123456` | `YT` und 16 Ziffern: Nummer von [YunExpress](carrier:yunexpress), auch wenn GOFO die letzte Meile in den USA übernimmt. |

**Zwei Nummern für dasselbe Paket.** Manche Sendungen tragen gleichzeitig einen `GFUS`-Frachtbrief und eine `CR`- (12 Ziffern) oder `MGP`-Sendungsnummer (13 Ziffern): Mit beiden Nummern findest du exakt dasselbe Paket sowohl in Peek als auch im US-Tracker von GOFO. Bei DHgate-Bestellungen, die GOFO im Auftrag von [DHLink](carrier:dhlink) zustellt, ist die Sendungsnummer die von DHLink, `FC` und 13 Ziffern – Peek fragt damit DHLink ab, und auch der US-Tracker von GOFO findet die Sendung unter dieser `FC`-Nummer.

**`YT`-Nummern von YunExpress.** Stellt GOFO in den USA ein Paket von [YunExpress](carrier:yunexpress) zu, zeigt die Sendungsverfolgung von YunExpress auch die Scans von GOFO an, und der US-Tracker von GOFO akzeptiert dieselbe `YT`-Nummer.

**GOFO-Netze in anderen Ländern.** GOFO betreibt eigenständige Ländernetze mit eigenen Präfixen: Nummern mit `GFCA` gehören zu GOFO Canada, mit `GFFR` oder `CIFR` (sowie bestimmte 20-stellige `PK`-Nummern) zu GOFO France, mit `GFIT` oder `CIIT` zu GOFO Italy, mit `GFES` oder `CIES` zu GOFO Spain und mit `GFNL` oder `CINL` zu GOFO Netherlands. Peek erkennt diese Präfixe sofort und fragt das jeweilige Landesnetz ab.

## GOFO Express Sendungsstatus: englische Scans erklärt

Die Statusmeldungen von GOFO Express in den USA sind auf Englisch verfasst. In ungefähr dieser Reihenfolge durchläuft ein Paket die Stationen (nicht jede Sendung zeigt jeden Zwischenschritt):

| GOFO schreibt | Was das für dich bedeutet |
| --- | --- |
| `Shipping Label Created` | Der Absender hat das Versandetikett erstellt; GOFO hat das Paket noch nicht übernommen. |
| `Pick Up Start` | GOFO hat die Abholung beim Absender gestartet, hält das Paket aber noch nicht in den Händen. |
| `Pickup Exception, GOFO has not Received the Package from the Warehouse yet.` | GOFO hat das Paket noch nicht aus dem Lager des Händlers erhalten. |
| `Picked up by Shipping Partner, GOFO Awaiting Item` | Ein Versandpartner hat das Paket für GOFO abgeholt; GOFO wartet auf den Eingang im Sortierzentrum. |
| `Pick Up Success` | GOFO hat das Paket beim Absender abgeholt. |
| `Arrived at GOFO Regional Origin Hub` | Erster physischer Scan in einem regionalen Start-Hub von GOFO. |
| `Arrived at Sorting Center` | Ankunft in einem Sortierzentrum vor dem Regional-Hub. |
| `Arrived at GOFO Regional Hub` | Ankunft in einem regionalen Umschlagzentrum von GOFO. |
| `Processing at GOFO Facility` | Das Paket wird in einer GOFO-Anlage sortiert. |
| `Departed GOFO Regional Hub` | Das Paket hat das regionale Umschlagzentrum verlassen. |
| `Loaded on Vehicle` | Für den Weitertransport zur nächsten Station in ein Fahrzeug verladen. |
| `In Transit to Next Facility` | Unterwegs zur nächsten GOFO-Station. |
| `Arrived at GOFO Delivery Station` | Ankunft in der lokalen GOFO-Zustellbasis für die Auslieferung. |
| `Departed GOFO Delivery Station` | Hat die Zustellbasis verlassen. |
| `Out for Delivery` | Das Paket ist heute mit dem Zusteller auf Tour. |
| `Delivered` | Zugestellt. |
| `Delivery Exception, Business Closed` | Fehlgeschlagener Zustellversuch, weil das Geschäft geschlossen war (der Grund nach dem Komma wechselt je nach Vorfall). |
| `Package Returned to GOFO Facility` | Am Tag nach einem fehlgeschlagenen Zustellversuch ist das Paket für einen neuen Versuch zurück in der lokalen GOFO-Station – keine Rücksendung an den Händler. |
| `Incorrect/Insufficient Address, Additional Details Required for Delivery.` | Falsche oder unvollständige Adresse (beim Eingang im Hub oder am Tag nach einem Fehlversuch); das Paket wartet auf fehlende Adressangaben. |
| `Address Corrected.` | Die Adresse wurde ergänzt und das Paket setzt seinen Weg fort. |
| `Label Cancelled` | Das Etikett wurde nach der Erstellung storniert: Unter dieser Nummer kommt kein Paket. |

Bei `Delivery Exception, …` setzt Peek die Sendung auf „Zustellversuch“ und zeigt den vollständigen englischen Satz unter „Das sagt GOFO Express“. Folgt am nächsten Tag `Package Returned to GOFO Facility`, steht das Paket wieder auf „Unterwegs“ in der Zustellbasis für einen weiteren Zustellversuch (nicht auf „Zurück an Absender“). Sowohl `Incorrect/Insufficient Address…` als auch `Label Cancelled` wechseln hingegen auf „Problem gemeldet“: Melde dich im ersten Fall umgehend beim Händler, um Apartmentnummer, Zugangscode oder Geschäftszeiten nachzureichen, und bitte im zweiten Fall um die gültige neue Sendungsnummer. An manche Scans hängt GOFO am Ende eine Support-Zeile mit Kontaktdaten auf Englisch oder Spanisch an; Peek blendet diesen Zusatz aus.

**Zeitzonen.** GOFO versieht `Shipping Label Created` immer mit der US-Pazifikzeit (`America/Los_Angeles`) und jeden weiteren Scan mit der US-Zeitzone der jeweiligen Station. Peek rechnet alle Zeitangaben in die Zeitzone deines Smartphones oder Computers um („Uhrzeiten in deiner Zeitzone: …“) und nutzt das 24-Stunden-Format: Ein um 22:00 Uhr Pazifikzeit erstelltes Etikett erscheint in Deutschland, Österreich oder der Schweiz als 07:00 Uhr am Folgetag (in den wenigen Wochen im März und Ende Oktober, in denen die USA und Europa die Uhren nicht am selben Tag umstellen, als 06:00 Uhr; an der US-Ostküste als 01:00 Uhr).

## GOFO-Express-Paket hängt fest, verspätet sich oder fehlt: was tun?

**Nur `Shipping Label Created`, `Pick Up Start` oder `Pickup Exception`.** Solange einer dieser drei Einträge oben steht, hat GOFO die Ware noch nicht aus dem Lager des Verkäufers erhalten. Tut sich mehrere Tage nichts, frag beim Shop nach, wann übergeben wird. Mehr dazu unter [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating).

**Unterwegs stehen geblieben.** Kommen vier Tage lang keine neuen Scans hinzu, bevor das Paket in Zustellung geht, informiere den Shop oder nutze den Hilfe-Link, den Peek dann einblendet.

**Fehlgeschlagener Zustellversuch oder Adressfehler.** Lies die Begründung nach dem Komma bei `Delivery Exception, …` oder den Hinweis `Incorrect/Insufficient Address…`. Fehlen Türnummer, Gate-Code oder Öffnungszeiten, gib sie dem Händler durch, damit der Status auf `Address Corrected.` springt.

**Als zugestellt markiert, aber nicht auffindbar.** Peek übernimmt keine Zustellfotos von GOFO. Prüfe zuerst Veranda, Postraum (*Mailroom*), Rezeption oder direkt die Sendungsverfolgung von GOFO auf ein hinterlegtes Zustellfoto und wende dich anschließend an den Händler. Weitere Schritte unter [Paket zugestellt, aber nicht erhalten](guide:delivered-not-received).

**Verloren, zurückgeschickt und Käuferrechte.** Den Beförderungsvertrag mit GOFO hat stets der Händler geschlossen – verlange dort Ersatzlieferung oder Erstattung (siehe [Paket verloren](guide:lost-parcel) und [Paket zurück an den Absender](guide:returned-to-sender)). Für Lieferungen in den USA verpflichtet die bundesweite FTC-Versandhandelsregel ([16 CFR § 435.2](https://www.law.cornell.edu/cfr/text/16/435.2)) den Verkäufer, innerhalb der zugesagten Frist – oder mangels Angabe binnen 30 Tagen – zu versenden und bei Verzögerungen entweder deine Zustimmung einzuholen oder unverzüglich zu erstatten. Wurde eine US-Kreditkarte für nie gelieferte Ware belastet, erlaubt der Fair Credit Billing Act ([15 U.S.C. § 1666](https://www.law.cornell.edu/uscode/text/15/1666)) innerhalb von 60 Tagen nach Abrechnungsversand einen schriftlichen Abrechnungseinspruch beim Kartenherausgeber. Hast du bei einem gewerblichen Händler mit Sitz in der EU bestellt, gilt ebenfalls die gesetzliche Standardlieferfrist von 30 Tagen ([Your Europe](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_de.htm)). Und falls an einer US-Adresse unbestellte Ware mit der Post eintrifft (*Brushing*), darf sie nach US-Bundesrecht ([39 U.S.C. § 3009](https://www.law.cornell.edu/uscode/text/39/3009)) als Geschenk behalten werden, ohne dafür zu bezahlen oder sie zurückzuschicken; das Gesetz spricht von Postsendungen, nicht ausdrücklich von einer Zustellung durch GOFO.

## GOFO Express Kundenservice erreichen

Wende dich bei Problemen mit deiner Bestellung zuerst an den Online-Shop, der das Paket verschickt hat. Bleibt deine Sendung vor der Auslieferung vier Tage lang ohne neuen Scan (auch wenn bisher nur das Etikett existiert), blendet Peek den Hinweis mit dem Link „Hol dir Hilfe bei GOFO Express“ ein, der die US-Kundendienstseite von GOFO (`gofo.com/us/customer-service`) öffnet.

## GOFO-Express-Pakete mit Peek verfolgen

Bei einer `GFUS`-Nummer mit 14 Ziffern erkennt [Peek](/) GOFO Express sofort („Erkannter Paketdienst“). Bei einer Nummer der Serien `GF` (13 Ziffern), `CR` (12 Ziffern) oder `MGP` (13 Ziffern) fragt Peek bei GOFO an („Tracking-Dienste werden geprüft…“); sobald GOFO die Sendung bestätigt, erscheint „GOFO Express hat dieses Paket.“ und die Verfolgung läuft. Leerzeichen in der Nummer spielen keine Rolle. Da [Cainiao](carrier:cainiao) manche AliExpress-Bestellungen in die USA unter dem `GFUS`-Frachtbrief von GOFO führt, fragt Peek für den vorherigen Transportabschnitt zusätzlich Cainiao ab, sobald GOFO seit zwei Tagen nur das Etikett anzeigt; mehr dazu unter [AliExpress-Bestellungen verfolgen](guide:aliexpress-orders).

Solange GOFO eine `GF`-, `CR`- oder `MGP`-Nummer noch nicht kennt, prüft Peek weiter und zeigt „Warten auf den ersten Scan von GOFO Express“ (weißt du sicher, dass es GOFO Express ist, kannst du den Dienst über „Ändern“ festlegen). Ist ein Frachtbrief bei GOFO bereits ohne ersten Scan vorgemerkt oder hast du eine `GFUS`-Nummer auf peektracker.com gespeichert, siehst du „Warten auf GOFO Express“ und Peek fragt weiter ab, ohne eine Fehlermeldung auszulösen, solange der Frachtbrief gelistet ist. Erst wenn GOFO die Nummer bei einem unter GOFO Express gespeicherten Paket zwei Tage lang als unbekannt zurückweist und auch keine andere Quelle antwortet, zeigt peektracker.com „Auch nach zwei Tagen kennt der Paketdienst diese Nummer nicht. Prüfe sie, wähle einen anderen Paketdienst oder lösche das Paket.“ und prüft danach einmal täglich sowie nach sieben Tagen einmal wöchentlich. Du kannst auch einen US-Tracking-Link von `gofo.com` (`/us…` oder Startseite) oder `gofoexpress.com` mit `searchID=` einfügen.

Peek zeigt den aktuellen Status, jeden Scan mit Ort und US-Bundesstaat laut GOFO (etwa `Jamaica, NY`), die Stationen auf einer Karte, die Zustellzeit und das Zielland. Die Scan-Texte bleiben auf Englisch, während die Hauptphase auf Deutsch erscheint („Etikett erstellt“, „Beim Paketdienst eingegangen“, „Unterwegs“, „In Zustellung“, „Zugestellt“ – ohne den Zusatz, den GOFO bei der Zustellung manchmal hinter ein Komma setzt). Ein von GOFO berechnetes voraussichtliches Lieferdatum zeigt Peek so lange an, bis das Paket auf „In Zustellung“, „Zugestellt“ oder „Zurück an Absender“ wechselt; Zustellfotos, GPS-Koordinaten und Gewicht lässt Peek weg.

Peek fragt GOFO bis zu alle 30 Minuten ab (auch während „In Zustellung“), wartet nach einer fehlgeschlagenen Abfrage 60 Minuten und wechselt nach zwei Tagen ohne Neuigkeiten – oder nach 12 Stunden auf „Etikett erstellt“ beziehungsweise „Beim Paketdienst eingegangen“ – auf stündliche Prüfungen. Benachrichtigungen lassen sich für „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“ einschalten. Weder GOFO noch den Shop kann Peek für dich kontaktieren.

## Häufige Fragen zur GOFO Express Sendungsverfolgung

### Was ist GOFO Express?

GOFO Express ist ein Paketdienst für Abholung im Lager, regionale Sortierung und Zustellung auf der letzten Meile in den Vereinigten Staaten. Daneben betreibt GOFO eigene Ländernetze in Kanada, Frankreich, Italien, Spanien und den Niederlanden mit jeweils eigenen Nummernkreisen.

### Warum funktioniert meine GOFO-Sendungsnummer nicht?

Prüfe zuerst, ob es eine US-Nummer von GOFO ist: `GFUS` (14 Ziffern), `GF` (13 Ziffern), `CR` (12 Ziffern) oder `MGP` (13 Ziffern). Nummern mit `GFCA`, `GFFR`/`CIFR`, `GFIT`/`CIIT`, `GFES`/`CIES` oder `GFNL`/`CINL` gehören zu den anderen Ländernetzen, `YT`-Nummern zu [YunExpress](carrier:yunexpress); ein DHgate-Paket, das GOFO ausliefert, lässt sich im GOFO-Tracker auch über die `FC`-Nummer von [DHLink](carrier:dhlink) finden. Wurde die Nummer gerade erst erzeugt, wartet der Frachtbrief oft noch auf den ersten Scan. Lehnt GOFO die Nummer auf peektracker.com nach zwei Tagen weiterhin als unbekannt ab, kontrolliere sie auf Tippfehler; steht dort `Label Cancelled`, wurde das Etikett storniert und du brauchst vom Shop die neue Nummer.

### Was bedeutet `Package Returned to GOFO Facility` bei GOFO?

Anders als es wörtlich klingt, geht das Paket dabei nicht an den Verkäufer zurück: Dieser Scan erscheint am Tag nach einem fehlgeschlagenen Zustellversuch (`Delivery Exception`), wenn der Fahrer die Sendung für einen neuen Zustellversuch zurück in die lokale GOFO-Station gebracht hat – Peek führt das Paket deshalb weiter als „Unterwegs“.

### Warum hat meine AliExpress- oder DHgate-Bestellung eine GOFO-Nummer?

Für Lieferungen in die USA verfolgt [Cainiao](carrier:cainiao) (die Logistiksparte von AliExpress) manche Sendungen unter der `GFUS`-Nummer von GOFO Express, wenn GOFO sie zustellt, während [DHLink](carrier:dhlink) bestimmte DHgate-Pakete an GOFO übergibt, das sie auch unter der `FC`-Nummer von DHLink findet. Bei einer `GFUS`-Nummer fragt Peek GOFO ab und prüft zusätzlich Cainiao, sobald GOFO seit zwei Tagen nur das Etikett zeigt.

:::sources
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – US-Nummernformate (GFUS, GF, CR, MGP), verknüpfte DHLink- (FC) und YunExpress-Nummern (YT), GOFO-Ländernetze, Scans und Phasen, Zeitzonen, Entfernen der Support-Zeile, Cainiao und verworfene Felder
- [16 CFR § 435.2 – Mail, Internet, or Telephone Order Merchandise Rule (Cornell LII)](https://www.law.cornell.edu/cfr/text/16/435.2) – US-Versandpflicht innerhalb der genannten Frist oder binnen 30 Tagen sowie Erstattungsanspruch bei Verzögerung
- [15 U.S.C. § 1666 – Fair Credit Billing Act (Cornell LII)](https://www.law.cornell.edu/uscode/text/15/1666) – schriftlicher Abrechnungseinspruch binnen 60 Tagen bei nicht gelieferter Kreditkartenbestellung
- [39 U.S.C. § 3009 – Mailing of unordered merchandise (Cornell LII)](https://www.law.cornell.edu/uscode/text/39/3009) – unbestellte Warensendungen in den USA gelten als Geschenk ohne Zahlungspflicht
- [Your Europe: Versand und Lieferung](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_de.htm) – gesetzliche 30-Tage-Standardlieferfrist beim Kauf von Händlern in der EU
:::
