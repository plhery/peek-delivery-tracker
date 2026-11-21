---
title: R+L Carriers Sendungsverfolgung: PRO-Nummer und Status
description: Verfolge eine Frachtsendung von R+L Carriers in den USA: Aufbau der PRO-Nummer, Bedeutung jeder Statuszeile und Fristen bei Verspätung, Schaden oder Verlust.
slug: rl-carriers-sendungsverfolgung
published: 2026-11-18
updated: 2026-11-21
---

R+L Carriers ist eine Frachtspedition in den **Vereinigten Staaten**. Im Code von Peek holt R+L die Fracht beim Versender ab, befördert sie von Terminal zu Terminal und stellt sie vom Zielterminal in den USA aus zu. Wenn eine größere Bestellung oder sonstige Fracht in den USA mit R+L zu dir unterwegs ist, verfolgst du sie über ihre PRO-Nummer, die dir der Onlineshop oder das beauftragende Unternehmen mitteilt: Füge sie oben in das Eingabefeld ein. Die eigene Sendungsverfolgung von R+L ist die Seite *Shipment Tracing* auf rlcarriers.com: Sie nimmt eine PRO-Nummer oder eine Abholauftragsnummer (*Pickup Request ID*) entgegen — bis zu 25 Nummern gleichzeitig — und mit einem MyRLC-Konto auch weitere Referenznummern.

## R+L Carriers Sendungsnummer: die PRO-Nummer

Das Suchformular von R+L schlägt Fracht anhand der PRO-Nummer nach. Peek kennt dafür folgende Muster, hier mit frei erfundenen Ziffern:

| Nummer (erfunden) | Was das ist |
| --- | --- |
| `48291057-3` | Neun Ziffern, deren letzte eine Prüfziffer ist, hier nach einem Bindestrich geschrieben. |
| `482910573` | Dieselbe PRO-Nummer ohne Bindestrich. |
| `RL482910-5` | Zwei Buchstaben und sieben Ziffern, die letzte davon eine Prüfziffer. Auch ohne Bindestrich gültig (`RL4829105`). |
| `I482910573` | Eine PRO-Nummer in der Schreibweise des R+L-Tracers mit vorangestelltem `I`. Das `I` gehört nicht zur eigentlichen Nummer: Peek akzeptiert die PRO mit oder ohne `I`. |

Der Tracer von R+L prüft die letzte Ziffer, sodass ein Zahlendreher oder Tippfehler auffällt (siehe unten). Peek entfernt Leerzeichen und Bindestriche vor der Abfrage bei R+L. Im Katalog von Peek ist R+L Carriers außerdem unter R&L Carriers, R and L Carriers sowie RL Carriers hinterlegt.

**Wo du sie findest.** In der Versandbestätigung des Shops oder auf deiner Bestellseite; bei gewerblicher Fracht zwischen Unternehmen fragst du die absendende Firma. Mehr dazu unter [Sendungsnummer finden](guide:find-tracking-number).

**Andere Dienste mit gleichem Muster.** Neun Ziffern allein passen auch zu [TNT](carrier:tnt), TForce Freight und XPO, und ein `I` gefolgt von neun Ziffern passt zu Australia Post. Nennt deine Versandnachricht einen dieser Anbieter, verfolge die Sendung dort. Weitere Hinweise unter [Formate von Sendungsnummern](guide:tracking-number-formats).

## Was die Statusmeldungen von R+L Carriers bedeuten

Die Sendungsverfolgung von R+L fasst den Zustand der Sendung oberhalb des Verlaufs kurz zusammen und listet darunter jede Station mit Datum und Uhrzeit auf. US-Terminals werden mit Ort und Bundesstaat benannt. Da die Zeilen keinen Statuscode tragen, wertet Peek den englischen Wortlaut aus. In der typischen Reihenfolge einer Frachtsendung in den **USA** (die Auslassungspunkte stehen für Ort und Bundesstaat oder ein Terminal):

| R+L schreibt | Was das für dich bedeutet | Peek zeigt |
| --- | --- | --- |
| `Picked Up in …` | R+L hat die Fracht beim Absender in dessen Ort übernommen. Peek übernimmt den vollen Wortlaut, verwendet den Absenderort aber nicht als Scan-Standort. | „Beim Paketdienst eingegangen“ |
| `Arrived at origin terminal` | Die Sendung hat das Startterminal von R+L aufseiten des Absenders erreicht. | „Unterwegs“ |
| `Departed … in route to …` | Sie hat ein Terminal in Richtung des nächsten Terminals oder des Zielterminals verlassen. | „Unterwegs“ |
| `Arrived at …` | Sie ist an einem unterwegs gelegenen Terminal eingetroffen, benannt nach Stadt und US-Bundesstaat. | „Unterwegs“ |
| `Arrived at destination terminal` | Sie liegt im Zielterminal in deiner Region, der letzten Station vor der Auslieferung. | „Unterwegs“ |
| `Shipment Delayed` | Etwas hat die Beförderung aufgehalten. Die Übersicht von R+L führt die Sendung dann als verspätet (*Delayed*) und behält den voraussichtlichen Zustelltag bei. | „Unterwegs“ |
| `Delivered` | Zugestellt. | „Zugestellt“ |

**Die Zusammenfassung über dem Verlauf.** Oberhalb der Einzelzeilen stuft R+L die Sendung als *In Transit*, *Delayed* oder *Delivered* ein. Ist diese Zusammenfassung weiter fortgeschritten als die Verlaufszeilen, richtet sich Peek nach der Zusammenfassung: Eine dort als zugestellt geführte Sendung zeigt „Zugestellt“, selbst wenn die jüngste Zeile dies noch nicht ausspricht. Liefert R+L vorerst nur die Zusammenfassung ohne Verlaufszeilen, zeigt Peek allein den Status an.

Im Code von Peek gibt es keinen eigenen R+L-Wortlaut für „In Zustellung“, einen fehlgeschlagenen Zustellversuch, ein Problem oder eine Rücksendung. Andere Formulierungen laufen über die allgemeinen Regeln, die Peek auf alle Anbieter anwendet. Eine Zeile, die keine Regel erkennt, bleibt im Originalwortlaut sichtbar und gilt vor der Auslieferung als Lebenszeichen („Unterwegs“); nach erfolgter Zustellung ändert sie am Status nichts mehr. Mehr dazu unter [Sendungsstatus erklärt](guide:tracking-statuses).

## Wie R+L Carriers in den USA zustellt

Solange die Fracht in den **USA** unterwegs ist, nennt der R+L-Tracer einen voraussichtlichen Zustelltag, den Peek bis zur Auslieferung anzeigt. Der R+L-Tracer nennt keinen Drittanbieter für die letzte Meile, und der Code von Peek übergibt die Sendung an keinen anderen Paketdienst. Alle Angaben auf dieser Seite betreffen Fracht innerhalb der Vereinigten Staaten.

Diese Seite enthält keine Angaben zu Zustellzeitfenstern, Avisierungen, Zustellversuchen oder zur Selbstabholung am Terminal in den USA. Erkundige dich beim Shop oder dem Unternehmen, das den Transport gebucht hat, wie und wann die Ware angeliefert wird — dort liegt der Frachtauftrag bei R+L.

## R+L Carriers Sendung nicht gefunden, verspätet, beschädigt oder verloren

**Nicht gefunden.** Kennt der Tracer von R+L eine PRO-Nummer nicht, antwortet er mit `No records found for Number:` gefolgt von der PRO mit vorangestelltem `I`. Peek zeigt für eine unter R+L Carriers gespeicherte Nummer dann „Warten auf R+L Carriers“ und fragt später erneut nach. Vergleiche jede Ziffer mit der Versandmail des Händlers; stimmt die Nummer, frage beim Versender nach, ob R+L die Fracht bereits abgeholt hat.

**Prüfziffer stimmt nicht.** Geht die Prüfziffer einer PRO-Nummer nicht auf, antwortet R+L mit der PRO-Nummer gefolgt von `Pro number failed check digit validation.` Peek meldet daraufhin „Sendungsnummer prüfen“. Gleiche die Nummer Ziffer für Ziffer mit der E-Mail des Shops oder den Frachtpapieren ab.

**Verspätet oder ohne neue Scans.** Nach `Shipment Delayed` belässt Peek die Sendung auf „Unterwegs“ samt dem von R+L genannten Zustelltag. Bleibt eine Sendung im Status „Beim Paketdienst eingegangen“ oder „Unterwegs“ vier Tage lang ohne neue Zeile, blendet Peek einen Hinweis mit dem Tag der letzten Zeile ein („Keine Aktualisierung seit Mo 12. Okt“). Wende dich an den Shop oder den Absender. Siehe auch [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating).

**Beschädigt, unvollständig oder verloren in den USA.** Informiere zuerst den Händler oder das absendende Unternehmen, das die Fracht beauftragt hat. Für Fracht, die ein Straßengüterverkehrsunternehmen **zwischen US-Bundesstaaten** befördert, schreibt das Bundesrecht der **Vereinigten Staaten** (`49 U.S.C. 14706` und `49 CFR Part 370`) folgende Regeln vor:

- Der Frachtführer haftet gegenüber dem aus Empfangsschein oder Frachtbrief (*bill of lading*) Berechtigten für den tatsächlichen Verlust oder Schaden am Gut. Die Haftung kann auf einen Wert begrenzt werden, den der Absender schriftlich oder elektronisch deklariert oder schriftlich mit dem Frachtführer vereinbart hat.
- Für die Einreichung einer Schadensforderung darf der Frachtführer keine kürzere Frist als 9 Monate setzen, und für eine Klage keine kürzere Frist als 2 Jahre ab dem Tag, an dem er schriftlich mitteilt, dass er irgendeinen Teil der Forderung ablehnt. Die konkrete Frist ergibt sich aus dem Frachtbrief oder Vertrag.
- Eine formgültige Reklamation ist eine fristgerecht beim Frachtführer eingereichte schriftliche Forderung, die die Sendung bezeichnet, den Frachtführer haftbar macht und einen bestimmten oder bestimmbaren Geldbetrag verlangt. Ein bloßer Schadens- oder Fehlmengenvermerk auf dem Ablieferbeleg zählt für sich genommen noch nicht als Reklamation.
- Soweit für die Prüfung erforderlich, sind der Frachtbrief, der Nachweis über die Frachtkosten und die Handelsrechnung beizufügen. Geht ein ganzes Packstück oder die gesamte Sendung verloren und lässt sich der Verlust bei der Untersuchung nicht anderweitig bestätigen, holt der Frachtführer vom Empfänger (*consignee*) eine beglaubigte schriftliche Erklärung ein, dass die Ware auch aus keiner anderen Quelle eingetroffen ist.
- Der Frachtführer bestätigt den Eingang einer Reklamation schriftlich binnen 30 Tagen. Innerhalb von 120 Tagen zahlt er, lehnt schriftlich ab oder unterbreitet ein verbindliches Vergleichsangebot; ist der Fall bis dahin nicht entscheidungsreif, teilt er zu diesem Zeitpunkt und danach alle 60 Tage schriftlich den Bearbeitungsstand mit.

Steht die Sendung auf `Delivered`, ohne dass etwas angekommen ist, lies [zugestellt, aber nicht erhalten](guide:delivered-not-received). Weitere Hinweise bieten [beschädigtes Paket](guide:damaged-parcel) und [verlorenes Paket](guide:lost-parcel).

## Kundenservice und Kontakt von R+L Carriers

Auf der US-Website von R+L Carriers stehen folgende Kontaktwege:

- **Telefon:** `800-543-5589`, die oben auf den Seiten von R+L genannte Rufnummer.
- **R+L Cares:** ein Kontaktformular auf der Website von R+L für offene Anliegen, Fragen oder Rückmeldungen; laut R+L geht die Nachricht direkt an die Unternehmensleitung (*R+L Executive Leadership*).
- **Frachtschäden:** Im Menü der Website verweist *Cargo Claims* auf die Anleitung von R+L zur Anmeldung eines Transportschadens.

Vertragspartner von R+L ist der Auftraggeber der Fracht: Wende dich bei Onlinekäufen zuerst an den Shop, bei Firmenfracht an das versendende Unternehmen.

## R+L Carriers Sendung mit Peek verfolgen

Einer neunstelligen PRO-Nummer sieht Peek nicht von selbst an, dass sie zu R+L gehört, da auch andere Dienste neun Ziffern nutzen. Füge sie trotzdem ein: Peek unterstützt über 4.000 Paketdienste und Speditionen. Bei zwei Buchstaben und sieben Ziffern fragt Peek sofort bei R+L an und zeigt „R+L Carriers hat dieses Paket.“, sobald R+L die PRO-Nummer kennt. Tippst du neun Ziffern mit Bindestrich vor der letzten Ziffer ein, führt Peek R+L Carriers ebenfalls unter „Passt zu dieser Nummer“ auf und prüft R+L; beim Speichern legt Peek die Nummer jedoch ohne Bindestrich ab (und ebenso, wenn du neun Ziffern ohne Bindestrich einfügst), sodass Peek dann TNT, TForce Freight und XPO abfragt, sofern deren Prüfziffer passt, nicht aber R+L: Tippe auf „Ändern“ und suche R+L Carriers über den Namen. Bei einer bereits gespeicherten Sendung nutzt du „Paketdienst ändern“. Meldet später ein Tracking-Dienst Bewegung für die Fracht und nennt dabei R+L Carriers, ordnet Peek die Sendung R+L zu und fragt bei künftigen Prüfungen direkt den Tracer von R+L ab.

Mit vorangestelltem `I` vor neun Ziffern passt die Nummer zu R+L Carriers und Australia Post: Peek fragt beide ab und speichert die Sendung unter dem Anbieter, der sie kennt. Auch ein Link zur *Shipment Tracing*-Seite von R+L mit `pro=` funktioniert — Peek erkennt R+L daraus sofort („Erkannter Paketdienst“).

Peek fragt den Tracer von R+L allein über die PRO-Nummer ab, ohne Kundenkonto oder Postleitzahl. Angezeigt werden der Status, jede Verlaufszeile (mit Ort und US-Bundesstaat des Terminals, sofern die Zeile sie nennt), der voraussichtliche Zustelltag, der Empfängername in deiner eigenen Sendungsansicht („Empfänger“, auf geteilten Links ausgeblendet) sowie das Gewicht in der Einheit deines Geräts (in Pfund auf Geräten mit US-Maßsystem, sonst in Kilogramm; R+L liefert Pfund). Weggelassen werden die Empfängeradresse, Absender und Rechnungsempfänger, Packstückzahl, Referenzen, Serviceart, Frachtbriefnummer und Dokumente. Einen Zustellnachweis liefert der R+L-Tracer an Peek nicht. Über „Website von R+L Carriers öffnen“ auf der Sendungsseite öffnest du die PRO direkt bei R+L.

Ist der Tracer von R+L nicht erreichbar oder lässt sich die Antwort nicht auslesen, greift Peek auf [Tracking-Dienste](guide:universal-tracker) zurück und zeigt deren Verlauf im jeweiligen Wortlaut.

Die Zeitstempel von R+L enthalten Datum und Uhrzeit ohne Zeitzone. Peek liest jede Zeile in der Zeitzone des Terminalorts, hilfsweise des Bundesstaats (in Staaten mit mehreren Zonen der Hauptzone), sonst anhand der vorherigen oder nächsten Zeile und andernfalls in US-Ostküstenzeit (`America/New_York`), und rechnet alle Angaben in die Zeitzone deines Geräts um („Uhrzeiten in deiner Zeitzone: …“).

Solange du die Sendung geöffnet hast oder am Zustelltag mit aktivierten Benachrichtigungen, prüft Peek bis zu alle 10 Minuten; sonst — sowie nach zwei Tagen ohne neue Zeile (12 Stunden bei „Etikett erstellt“ oder „Beim Paketdienst eingegangen“) — stündlich. Benachrichtigungen lassen sich auf „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“ stellen. Peek kann weder R+L, den Händler noch den Absender für dich kontaktieren, keinen Liefertermin vereinbaren und keinen Schadensantrag einreichen.

## Häufige Fragen zur R+L Carriers Sendungsverfolgung

### Was ist eine PRO-Nummer bei R+L Carriers?

Die Sendungsnummer von R+L Carriers in den USA: neun Ziffern oder zwei Buchstaben und sieben Ziffern, wobei die letzte Ziffer eine Prüfziffer ist und manchmal hinter einem Bindestrich steht. Im eigenen Tracer schreibt R+L ein `I` davor. Du erhältst die Nummer vom Shop oder vom Unternehmen, das den Transport beauftragt hat.

### Warum meldet R+L Carriers „No records found for Number“?

Der Tracer von R+L kennt diese PRO-Nummer noch nicht oder sie ist falsch geschrieben. Prüfe jede Ziffer anhand der Versandmail, vergewissere dich, dass tatsächlich R+L Carriers beauftragt wurde, und frage beim Absender nach, ob R+L die Ware schon übernommen hat.

### Was bedeutet „Shipment Delayed“ bei R+L Carriers?

Auf dem Transportweg in den USA ist eine Verzögerung eingetreten. Die Zeile selbst nennt keinen Grund; die Übersicht von R+L führt die Sendung dann als verspätet bei weiterhin angezeigtem voraussichtlichem Zustelltag, und Peek belässt sie auf „Unterwegs“. Den Grund erfährst du beim Händler oder Versender.

### Wie lange habe ich Zeit für eine Schadensmeldung bei R+L Carriers?

Bei Straßengütertransporten zwischen US-Bundesstaaten gewährt das US-Bundesrecht mindestens 9 Monate für eine schriftliche Schadensforderung beim Frachtführer und mindestens 2 Jahre für eine Klage, gerechnet ab der schriftlichen Ablehnung eines beliebigen Teils der Forderung. Die genaue Frist steht im Frachtbrief oder Vertrag; die eigenen Tarifbedingungen von R+L gibt diese Seite nicht wieder. Wende dich zuerst an den Auftraggeber der Fracht; Hinweise von R+L findest du auf deren Website unter *Cargo Claims*.

:::sources
- [Universal Parcel Scraper: R+L Carriers](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/rl-carriers) – Namen, PRO-Formate und `I`-Präfix, Tracking-Links, Statuszeilen und Zuordnung, Zusammenfassung, Fehlermeldungen bei unbekannter Nummer und Prüfziffer, Zustelltag, Empfängername, Gewicht, Zeitzonen und ausgelassene Felder
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – andere Dienste mit gleichem Nummernformat, keine Übergabe an Drittanbieter und Rückgriff auf Tracking-Dienste
- [R+L Carriers: Shipment Tracing](https://www.rlcarriers.com/freight/shipping/shipment-tracing) – Abfrage per PRO-Nummer oder Pickup Request ID für bis zu 25 Sendungen sowie weitere Referenzen über MyRLC
- [R+L Carriers: R+L Cares](https://www.rlcarriers.com/contact/rl-cares) – Kontaktformular direkt an die Unternehmensleitung von R+L, Rufnummer 800-543-5589 im Seitenkopf und Verweis auf Cargo Claims
- [GovInfo: 49 U.S.C. 14706, Liability of carriers under receipts and bills of lading](https://www.govinfo.gov/content/pkg/USCODE-2024-title49/html/USCODE-2024-title49-subtitleIV-partB-chap147-sec14706.htm) – Haftung gegenüber dem Frachtbriefberechtigten, Wertbegrenzung sowie Mindestfristen von 9 Monaten und 2 Jahren in den USA
- [GovInfo: 49 CFR Part 370, Investigation and voluntary disposition of loss and damage claims and processing salvage](https://www.govinfo.gov/content/pkg/CFR-2025-title49-vol5/xml/CFR-2025-title49-vol5-part370.xml) – Pflichtangaben einer schriftlichen Reklamation, Vermerke auf Ablieferbelegen, Nachweise sowie 30-, 120- und 60-Tage-Fristen
:::
