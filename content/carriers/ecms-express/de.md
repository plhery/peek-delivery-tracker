---
title: ECMS Express Sendungsverfolgung: 16-stellige Nummern & Zoll
description: Verfolge dein ECMS-Express-Paket (易客满): Aufbau der 16-stelligen Nummer, Bedeutung englischer und chinesischer Scans, Zoll und Zustellung mit Peek.
slug: ecms-express-sendungsverfolgung
published: 2026-11-21
updated: 2026-11-21
---

ECMS Express (`易客满`, auch `ECMS Global` oder `ECMSGlobal`, chinesischer Firmenname `天津易客满国际物流有限公司`) ist ein 2013 gegründeter internationaler Express- und E-Commerce-Logistiker für Sendungen in über 200 Länder und Regionen. Hat ein Online-Shop deine Bestellung mit ECMS verschickt, besteht deine Sendungsnummer aus 16 Zeichen und beginnt mit `EC`, `ES`, `AP` oder `AX`: Füge sie oben ins Suchfeld ein. ECMS befördert das Paket per Luftfracht ins Zielland und durch den Zoll; die Auslieferung an der Haustür übernimmt ein lokaler Paketdienst. In Peeks Katalog findest du den Anbieter auch unter `ECMS`, `ECMS Global`, `ECMSGlobal`, `ECMS International Logistics Co.`, `易客满`, `易客满国际物流有限公司` und `易客满国际快递`.

## ECMS-Express-Sendungsnummer: Aufbau und Beispiele

ECMS-Nummern sind immer genau 16 Zeichen lang: `EC`, `ES`, `AP` oder `AX`, danach drei bis fünf Buchstaben und anschließend Ziffern. Mit erfundenen Zeichen:

| Nummer (erfunden) | Bedeutung |
| --- | --- |
| `ECLAX48291057364` | `EC`, drei Buchstaben und elf Ziffern: eine ECMS-Express-Nummer. |
| `ECSZXA4829105736` | `EC`, vier Buchstaben und zehn Ziffern: ebenfalls ECMS. |
| `ECSZXAB482910573` | `EC`, fünf Buchstaben und neun Ziffern: ebenfalls ECMS. |
| `ESLAX48291057364`, `APLAX48291057364`, `AXLAX48291057364` | Dieselben 16-stelligen Muster mit `ES`, `AP` oder `AX` am Anfang. |
| `ECLAX4829105736` | Nur 15 Zeichen: kein ECMS-Format, und Peek erkennt keinen Paketdienst (wählst du ECMS Express manuell aus, meldet Peek, dass der Dienst „vergibt keine Nummern wie diese“). Prüfe, ob eine Ziffer fehlt. |

Kein anderer Paketdienst in Peeks Katalog verwendet diese Muster. Leerzeichen und Kleinschreibung spielen in Peek keine Rolle.

**Wo du die Nummer findest.** ECMS druckt die Frachtbriefnummer (*waybill*) aufs Versandetikett, und der Shop nennt sie in der Versandmail oder im Bestellkonto. Falls du sie nicht findest, lies [Sendungsnummer finden](guide:find-tracking-number) und [Formate von Sendungsnummern](guide:tracking-number-formats).

**Ein Tracking-Link.** Ein Link zum ECMS-Tracker mit `?number=` trägt die Nummer ein und öffnet die Bildprüfung (*Security verification*). Andere ECMS-Links übergeben die Nummer hinter `orderNumber=`, `trackingno=` (etwa auf `consignee.ecmsglobal.com`) oder `order_number=`. Du kannst jeden davon in Peek einfügen: Peek liest die ECMS-Nummer automatisch aus dem Link.

## ECMS-Express-Tracking-Status erklärt

Auf `ecmsglobal.com` verlangt der Tracker zunächst eine Bildprüfung (*Security verification*: *Enter the characters shown below to continue tracking.*), fasst die Sendung dann unter einem von acht Statusbegriffen zusammen (von *Shipment Information Received* bis *Delivered* oder *Exception*) und listet die Scans absteigend mit Datum, Uhrzeit und Ort auf. Peek zeigt jeden Scan im Wortlaut des ECMS-Datensatzes. In der Reihenfolge des Verlaufs:

| ECMS-Eintrag | Was das für dein Paket bedeutet | Peek zeigt |
| --- | --- | --- |
| `Virtual Manifest has been received by carrier`, `Virtual Manifest has been received`, `Shipment info confirmed` | Der Shop hat die Paketdaten elektronisch an ECMS geschickt; das Paket selbst ist noch nicht bei ECMS. | „Etikett erstellt“ |
| `Documents and information required for clearance have been received by the carrier.`, `ID information has been reviewed and verified` | ECMS hat die Verzollungsunterlagen erhalten oder die Ausweisdaten geprüft; noch kein physischer Paketscan. | „Etikett erstellt“ |
| `Shipment arrived at carrier facility.`, `Shipment arrived at origin facility.` | Erster echter Scan im Abgangslager von ECMS. | „Beim Paketdienst eingegangen“ |
| `Parcel has arrived at export distribution warehouse` | Ankunft im Exportlager von ECMS am Abgangsort. | „Unterwegs“ |
| `Shipment departed from carrier facility.`, `Shipment departed from origin facility.` | Das Paket hat das Abgangslager verlassen. | „Unterwegs“ |
| `Flight departure`, `Flight arrival` | Abflug und Landung des Frachtflugs. | „Unterwegs“ |
| `Arriving notification received from terminal` | Das Frachtterminal am Zielflughafen bestätigt den Eingang der Flugzeugladung. | „Unterwegs“ |
| `Shipment has arrived at ECMS transit warehouse`, `Shipment has departed from ECMS transit warehouse` | Transitlager von ECMS am Ankunftsflughafen vor bzw. nach der Einfuhrverzollung. | „Unterwegs“ |
| `Shipment started customs clearance process.` | Der Einfuhrzoll im Zielland prüft die Sendung. | „Beim Zoll“ |
| `Shipment completed customs clearance process.` | Der Einfuhrzoll hat die Sendung freigegeben. | „Unterwegs“ |
| `Shipment arrived at destination facility`, `Parcel at destination facility, ready for next step`, `Parcel Weight` | Im Zieldepot angekommen, gewogen und bereit für die Übergabe an den lokalen Zusteller. | „Unterwegs“ |
| `Shipment is tendering to third party carrier for final delivery.` | ECMS übergibt das Paket an den lokalen Zusteller; ab hier kann dessen eigene Sendungsnummer erscheinen. | „Unterwegs“ |
| `Return to origin` | Störungsmeldung im Zieldepot – nicht zwingend eine echte Rücksendung: In einem in Peeks Code hinterlegten Verlauf übernahm der lokale Zusteller das Paket am Folgetag und stellte es normal zu. | „Problem gemeldet“ |
| `Local carrier has accepted package for final delivery` | Der lokale Zusteller hat das Paket übernommen. | „Unterwegs“ |
| `Shipment is in Transit within the carrier‘s network`, `Parcel in transit during last mile delivery` | Weitertransport im Netz des lokalen Zustellers laut ECMS-Weiterleitung (die zweite Meldung kann an Folgetagen erneut auftauchen). | „Unterwegs“ |
| `Shipment is out for delivery.` | Auf dem Zustellfahrzeug. | „In Zustellung“ |
| `Available for pickup` | Der lokale Zusteller hält das Paket zur Abholung bereit. | „Abholbereit“ |
| `Shipment delivered to customer` | Zugestellt. | „Zugestellt“ |

**Chinesische Zeilen.** Manche Sendungsverläufe liefern diese Schritte auf Chinesisch; Peek zeigt den chinesischen Wortlaut und ordnet ihm dieselbe Stufe zu wie dem englischen Gegenstück: `发货预报已收到，正在等待货物到达我们的始发站仓库` („Etikett erstellt“), `干线航班起飞` und `干线航班已到达` („Unterwegs“), `货物已进入清关流程` („Beim Zoll“), `货物已清关放行`, `货物已到达目的站仓库` und `货物出库前往配送中心安排最终派送` („Unterwegs“).

**Uhrzeiten.** ECMS versieht eigene Scans mit der Ortszeit und dem UTC-Versatz der jeweiligen Station, und Peek übernimmt diesen Versatz unverändert. Weitergeleitete Scans des lokalen Zustellers tragen den Versatz null: Peek liest sie als UTC-Zeitpunkt, außer ein Scan dieses Partner-Feeds läge damit mehr als zehn Minuten nach dem Speicherzeitpunkt bei ECMS – dann behandelt Peek diese Angaben als Ortszeit des jeweiligen Ortes. Eine exakt auf Mitternacht gestempelte Flugzeile (`Flight departure`, `Flight arrival`) nennt nur den Flugtag und erscheint in Peek ohne Uhrzeit. Siehe auch [Paket aus China verfolgen](guide:tracking-from-china).

## Wer liefert ECMS-Express-Pakete in Deutschland, Österreich und der Schweiz?

Für die letzte Meile (*Final Mile Delivery*) arbeitet ECMS laut seiner Seite *Parcel Delivery Service* mit zuverlässigen lokalen Zustellern (*trusted local carriers*) in über 200 Ländern und Regionen zusammen, nennt für Europa jedoch keine feste Länderliste auf der Website. Im Tracker auf `ecmsglobal.com` kann das Feld *Last Mile Delivery* den Zusteller unter `LM Provider` samt seiner Sendungsnummer unter `LM Tracking No.` und einem Link `Track with …` ausweisen (in US-Verläufen in Peeks Code ist das etwa SpeedX).

**Laufzeiten.** Laut ECMS-FAQ hängt die Dauer von Abgangsort, Ziel und Tarif ab. Ab den Vereinigten Staaten (*U.S. Origin* auf *Parcel Delivery Service*) nennt ECMS `4–8 business days` in die **Europäische Union** sowie `4–8 business days` in die **Schweiz** (außerdem `3–6 business days` nach Großbritannien und `3–11 business days` nach Kanada). Zustellversuche und Abholfristen richten sich nach dem lokalen Zusteller: Siehe [Zustellung verpasst](guide:missed-delivery) und [Paket nicht abgeholt](guide:uncollected-parcel).

**Zoll in Deutschland, Österreich, der EU und der Schweiz.** Laut ECMS-FAQ muss der Empfänger bei `DDU/DAP`-Sendungen Zölle und Einfuhrabgaben gegebenenfalls vor der Zustellung bezahlen, während sie bei `DDP`-Sendungen üblicherweise vorab vom Absender übernommen werden (manche Länder verlangen für die Verzollung zudem eine Ausweiskopie). In der **Europäischen Union** (**Deutschland** und **Österreich**) fällt seit dem 1. Juli 2026 für online außerhalb der EU gekaufte Waren in Sendungen mit einem Sachwert bis 150 € ein EU-Zollsatz von 3 € pro Position (*per item*) an; seit dem 1. November 2026 kommt unabhängig vom Wert eine EU-Bearbeitungsgebühr (*Union handling fee*) von 2 € pro Position hinzu (eine Position bündelt Waren desselben Pakets mit gleicher Zolltarifnummer, Beschreibung und – falls anzugeben – gleichem Ursprung; über 150 € gelten reguläre Zollsätze), zuzüglich Einfuhrumsatzsteuer in **Deutschland** und **Österreich**, sofern sie nicht schon beim Kauf über IOSS berechnet wurde. In der **Schweiz** erhebt das Bundesamt für Zoll und Grenzsicherheit (BAZG) 8,1 % Einfuhrsteuer (2,6 % ermäßigter Satz), wobei „Zollabgaben bis 5 Schweizerfranken nicht erhoben“ und „Steuerbeträge bis 5 Schweizerfranken nicht erhoben“ werden – zuzüglich möglicher Verzollungsgebühren des Zustellers. Mehr unter [Paket hängt beim Zoll](guide:customs).

## ECMS-Express-Paket kommt nicht an oder Tracking hängt

**„We could not find a shipment for this tracking number.“** Der ECMS-Tracker kennt die Nummer noch nicht – wegen eines Tippfehlers oder weil der Händler die Daten noch nicht übermittelt hat. Peek zeigt „Warten auf ECMS Express“ und fragt weiter nach. Kennt ECMS die Nummer auch nach zwei Tagen noch nicht und findet kein Tracking-Dienst etwas, meldet Peek: „Auch nach zwei Tagen kennt der Paketdienst diese Nummer nicht. Prüfe sie, wähle einen anderen Paketdienst oder lösche das Paket.“

**Steht bei `Virtual Manifest has been received`.** ECMS hat erst die elektronischen Daten, noch nicht das Paket. Frag beim Shop nach, wann es übergeben wurde.

**Keine neuen Zeilen nach `Shipment is tendering to third party carrier for final delivery.`** Der lokale Zusteller hat übernommen oder tut es in Kürze, und sein eigenes Tracking ist oft aktueller. Schau im Feld *Last Mile Delivery* nach `LM Provider` und `LM Tracking No.` und füge diese Nummer separat in Peek hinzu, falls Peek das Paket dort noch nicht verfolgt. Nach vier Tagen ohne neuen Scan zeigt Peek den Tag des letzten Scans („Keine Aktualisierung seit Mo 12. Okt“) sowie den Link „Hol dir Hilfe bei ECMS Express“ (oder den des lokalen Zustellers, sobald Peek dorthin gewechselt hat und dessen Hilfeseite kennt). Weitere Tipps unter [Warum sich das Tracking nicht aktualisiert](guide:tracking-not-updating).

**Zugestellt, aber nichts da – oder verloren, beschädigt bzw. zurückgeschickt.** Frag zuerst beim lokalen Zusteller nach dem Ablageort und wende dich dann an den Verkäufer: Die ECMS-FAQ verweist Käufer für Ersatz oder Erstattung direkt an den Händler, denn Schadensersatzansprüche kann ausschließlich der Versender schriftlich innerhalb von 30 Tagen nach Annahme durch ECMS einreichen (maximal ein Antrag pro Sendung), und ECMS kann Bestellungen weder stornieren noch ändern. Als Rücksendegründe nennt ECMS verbotene oder beschränkte Güter, unvollständige oder falsche Adressdaten, Zollprobleme, unbezahlte Zölle und Steuern sowie fehlende Mitwirkung bei der Verzollung; eine einzelne Zeile `Return to origin` beweist aber noch keinen endgültigen Rückversand. Siehe [Zugestellt, aber nicht erhalten](guide:delivered-not-received), [Paket verloren](guide:lost-parcel) und [Zurück an den Absender](guide:returned-to-sender).

## ECMS Express Kundenservice und Kontakt

Laut *Contact Us* auf `www.ecmsglobal.com` (ohne Zeitzonenangabe für die Sprechzeiten; für Deutschland, Österreich und die Schweiz führt ECMS keine eigene Landesnummer):

- **Kontaktformular**: auf `www.ecmsglobal.com/en-us/contact-us.html` unter *Inquiry Type* die Option `Shipment Support` wählen und optional die Sendungsnummer unter *Tracking Number (Optional)* eintragen.
- **Europa (Großbritannien)**: `(+44) 01753528888`, `cs.uk@ecmsglobal.com`, Montag bis Freitag `08:30` bis `17:00`.
- **Festlandchina**: `(+86) 400 086 1756`, `service@ecmsglobal.com`, Montag bis Freitag `08:30` bis `17:30`.
- **Vereinigte Staaten**: `(+1) 626 956 5388`, `customerserviceus@ecmsglobal.com`, Montag bis Freitag `09:00` bis `18:00`.

**Vorsicht vor Phishing-Nachrichten.** Offizielle Tracking-Domains von ECMS sind `www.ecmsglobal.com` (`ecmsglobal.com`) und das ältere Portal `consignee.ecmsglobal.com`. Peek verwirft etwaige Zahlungslinks aus ECMS-Datensätzen; verlangt eine SMS Zoll- oder Zustellgebühren, lies [Gefälschte Paket-SMS erkennen](guide:delivery-scam-texts).

## ECMS-Express-Pakete mit Peek verfolgen

[Peek](/) erkennt eine 16-stellige ECMS-Nummer sofort („Erkannter Paketdienst“) und fragt den ECMS-Tracker ohne Konto, Postleitzahl oder Telefonnummer ab.

Die Bildprüfung von ECMS ist eine kleine einstellige Rechenaufgabe: Peek liest sie selbst aus und versucht es bei einer Ablehnung einmal mit einem neuen Bild. Antwortet ECMS dennoch nicht, weicht Peek auf Tracking-Dienste aus und zeigt deren Verlauf in deren Wortlaut.

Peek zeigt den Status, jeden Scan mit Stadt, Region und Land sowie das Zielland – neuester Eintrag zuerst – in der Zeitzone deines Geräts; auf `peektracker.com` sowie in der iPhone- und Android-App endet der Verlauf mit „Uhrzeiten in deiner Zeitzone: …“. Händlerreferenz, Ortsangaben auf reinen Manifestzeilen und Zahlungslinks lässt Peek weg; Gewicht oder voraussichtliches Lieferdatum zeigt es nicht. Über „Website von ECMS Express öffnen“ öffnest du deine Sendung auf `www.ecmsglobal.com`.

Nennt ECMS einen lokalen Zusteller, den Peek kennt, steht auf der Paketkarte „Zustellung durch …“ (samt dessen Nummer, sofern die weitergeleiteten Partnerzeilen genau eine enthalten) oder „Bei … angekündigt, noch nicht eingetroffen“, solange dieser Zusteller die Sendung noch ohne eigenen Scan führt. Liest Peek dessen direkten Tracker ohne Zusatzangaben, fragt es auch dort nach und wechselt, sobald ein mindestens ebenso aktueller Scan wie der letzte von ECMS vorliegt, wobei die ECMS-Historie erhalten bleibt; andernfalls füge die `LM Tracking No.` als eigenes Paket hinzu – Peek verfolgt mehr als 4.500 Paketdienste.

Solange du das Paket geöffnet hast oder am Liefertag Mitteilungen aktiviert sind, prüft Peek bis zu alle 10 Minuten – und ab 6 Uhr Ortszeit am Tag mit Status „In Zustellung“ bis zu alle 2 Minuten, solange der eigene Tracker von ECMS antwortet (übernimmt der lokale Zusteller, folgt Peek dessen Takt; über Tracking-Dienste allein: stündlich, alle 3 Stunden nach einem ruhigen Tag und bis zu alle 15 Minuten am Liefertag bei geöffnetem Paket oder aktiven Mitteilungen). Sonst sowie nach zwei Tagen ohne Neuigkeiten (12 Stunden bei „Etikett erstellt“ oder „Beim Paketdienst eingegangen“) prüft Peek stündlich. Benachrichtigungen gibt es für „Jeden Scan“, „Wichtige Schritte“ (Einfuhrzoll, `Return to origin`, Zustelltour, `Available for pickup` und Zustellung – nicht Flüge oder Lagerdurchläufe) oder „Nur die Zustellung“. ECMS oder den Verkäufer kontaktieren kann Peek nicht.

## Häufige Fragen zur ECMS-Express-Sendungsverfolgung

### Wer ist ECMS Express (易客满)?

Ein 2013 gegründetes internationales Express- und Logistikunternehmen (`天津易客满国际物流有限公司`) mit eigenen Niederlassungen auf dem chinesischen Festland, in Hongkong, Singapur, den USA, Großbritannien, Europa, Südkorea und Japan.

### Wo kann ich ein ECMS-Express-Paket verfolgen?

Direkt oben im Eingabefeld (ohne Bildcode-Eingabe) oder auf der Seite *Track Your Shipment* unter `www.ecmsglobal.com`, nachdem du dort die Sicherheitsabfrage gelöst hast.

### Welcher Paketdienst stellt ECMS-Express-Sendungen bei mir zu?

Ein lokaler Partnerdienst von ECMS, den der ECMS-Tracker im Kasten *Last Mile Delivery* unter `LM Provider` samt lokaler Nummer (`LM Tracking No.`) nennen kann. Steht dort noch nichts, frag beim Verkäufer nach.

### Was bedeutet „Shipment is tendering to third party carrier for final delivery“?

ECMS übergibt dein Paket an den lokalen Paketdienst für die Schlusszustellung; dessen Sendungsnummer kann anschließend unter `LM Tracking No.` erscheinen. Peek zeigt „Unterwegs“.

### An wen wende ich mich, wenn mein ECMS-Express-Paket fehlt?

Zuerst an den Händler, bei dem du gekauft hast: Laut ECMS-FAQ laufen Ersatz und Rückerstattung über den Shop, und nur der Absender kann binnen 30 Tagen nach Annahme schriftlich bei ECMS reklamieren. Zur eigentlichen Auslieferung hilft der lokale Zusteller; ECMS erreichst du über das Formular *Shipment Support* auf `ecmsglobal.com`.

:::sources
- [ECMS Express: Startseite](https://www.ecmsglobal.com/en-us/home.html) – internationaler Express, Fracht und E-Commerce-Fulfillment
- [ECMS Express: About ECMS](https://www.ecmsglobal.com/en-us/about.html) – Gründung im Jahr 2013
- [易客满: 关于我们 (Über uns)](https://www.ecmsglobal.com/zh-cn/about.html) – Gründung 2013, eigene Niederlassungen und Firmenname 天津易客满国际物流有限公司
- [ECMS Express: Parcel Delivery Service](https://www.ecmsglobal.com/en-us/express.html) – lokale Partner auf der letzten Meile, 200+ Länder sowie US-Laufzeiten in die EU, die Schweiz, nach Großbritannien und Kanada
- [ECMS Express: Track Your Shipment](https://www.ecmsglobal.com/en-us/tracking.html) – Bildprüfung, acht Zusammenfassungslabels, Kasten Last Mile Delivery (`LM Provider`, `LM Tracking No.`), Nicht-gefunden-Meldung und `?number=`
- [ECMS Express: FAQ](https://www.ecmsglobal.com/en-us/faq.html) – Frachtbrief auf dem Etikett, DDU/DAP und DDP, Rücksendegründe, keine Bestelländerung und 30-Tage-Reklamationsfrist durch den Absender
- [ECMS Express: Contact Us](https://www.ecmsglobal.com/en-us/contact-us.html) – Formular Shipment Support, regionale Telefonnummern, E-Mail-Adressen und Zeiten
- [Europäische Kommission: EU-Zollreform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 3 € Zollsatz seit 1. Juli 2026 und 2 € Bearbeitungsgebühr seit 1. November 2026
- [Europäische Kommission: Leitlinien zum 3-€-Zoll und zur Bearbeitungsgebühr (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – Erhebung pro Position und IOSS-Einfuhrsteuerbefreiung
- [BAZG: Empfangen von Briefen und Paketen](https://www.bazg.admin.ch/de/empfangen-von-briefen-und-paketen) – Schweizer Einfuhrsteuer 8,1 % / 2,6 % und Nichterhebung bis 5 Franken
- [Universal Parcel Scraper: ECMS Express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ecms-express) – Aliasnamen, 16-stelliges Muster, Links, Rechen-Captcha und Zweitversuch, englische und chinesische Statuszeilen, Zeitzonen und verworfene Felder
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Übergabe an den lokalen Zusteller und Ausweichen auf Tracking-Dienste
:::
