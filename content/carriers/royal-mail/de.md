---
title: Royal Mail Sendungsverfolgung: Nummer, Status und Zoll
description: Royal-Mail-Paket in Deutschland, Österreich oder der Schweiz verfolgen: GB-Nummern, englische Statusmeldungen, Übergabe an DHL oder Post sowie Zollkosten.
slug: royal-mail-sendungsverfolgung
published: 2026-11-17
updated: 2026-11-17
---

Royal Mail ist die staatliche britische Post; ihre englischsprachige Sendungsverfolgung *Track your item* auf `royalmail.com` zeigt zugleich Pakete von Parcelforce Worldwide an. Gib deine Sendungsnummer dort ein oder füge sie direkt oben in das Suchfeld ein. Die Nummer erhältst du vom Absender – sie steht in der Bestellbestätigung, der Versandmail oder auf der Rechnung des britischen Händlers.

## Royal-Mail-Sendungsnummer: Aufbau und wo du sie findest

Sendungsnummern von Royal Mail kommen in drei Formaten vor. Fehlt sie in den Nachrichten des Shops, frag beim Verkäufer nach; weitere Tipps findest du unter [Sendungsnummer finden](guide:find-tracking-number).

| Format (erfundenes Beispiel) | Bedeutung |
| --- | --- |
| `AB482910576GB` | Die klassische internationale und nationale Sendungsnummer nach UPU-Standard S10: 2 Buchstaben, 9 Ziffern und `GB`. Die neunte Ziffer ist eine Prüfziffer. |
| `0A1234567000000000ABC` | 21-stellige Referenz aus einem 2D-Barcode auf innerbritischen Versandetiketten. |
| `4829105738ABCDEF` | 16-stellige Referenz aus Ziffern und den Buchstaben `A` bis `F`. |

Ähnliche Nummern, die zu anderen Netzen gehören:

- Beginnt der Code mit `E` und einem weiteren Buchstaben (`EA`, `EB`…), `CP` oder `GI` vor 9 Ziffern und `GB`, gehört das Paket zu Parcelforce Worldwide (der Tracker von Royal Mail liest auch 14-stellige Parcelforce-Nummern aus `PB`, zwei Buchstaben und zehn Ziffern).
- Derselbe S10-Aufbau mit einem anderen Länderkürzel am Ende (`RR482910576DE`, `RR482910576AT`, `RR482910576CH`) stammt von der Absenderpost. Laut UPU-Norm S10 behält eine Postsendung diese eine Nummer bis zur Haustür; auf dem Weg nach Großbritannien übernimmt Royal Mail sie nach der Ankunft (außer bei `E…`-EMS-Sendungen, die Parcelforce zustellt).
- 16 reine Ziffern ohne Buchstaben sind kein Royal-Mail-Format: Peek fragt dafür gleichzeitig andere Paketdienste ab, darunter [DHL eCommerce](carrier:dhl-ecommerce) und [TNT](carrier:tnt).

## Was die englischen Statusmeldungen von Royal Mail bedeuten

Royal Mail führt seine Sendungsverfolgung ausschließlich auf Englisch. Das sind die häufigsten Meldungen in der üblichen Reihenfolge:

| Royal Mail meldet | Was das für dich bedeutet |
| --- | --- |
| `Sender preparing item` / `Sender has despatched item` | Der Absender hat die Sendung angekündigt; Royal Mail hat das Paket noch nicht erhalten. |
| `We're expecting it` | Elektronisch angekündigt, aber noch nicht bei Royal Mail eingeliefert. |
| `Item Received` | Royal Mail hat die Sendung übernommen. Derselbe Wortlaut erscheint erneut in britischen Sortierzentren und bei der Ankunft im Zielland. |
| `In transit` | Unterwegs zwischen zwei Standorten im Postnetz. |
| `Item Leaving the UK` | Die Sendung hat Großbritannien in Richtung Zielland verlassen. |
| `Your item is currently with Customs in the destination country.` | Die Sendung durchläuft die Importverzollung im Zielland. |
| `Released from Customs` | Der Zoll hat die Sendung zur Weiterbeförderung freigegeben. |
| `Customs charges raised` | Vor der Übergabe sind Einfuhrumsatzsteuer, Zoll oder Bearbeitungsgebühren zu zahlen (siehe unten). |
| `Item has left the overseas International Processing Centre` | Das Paket hat das internationale Postzentrum im Zielland verlassen. |
| `Despatched to delivery office` / `Item received at Sorting Office` | Unterwegs zum lokalen Zustellstützpunkt. |
| `Ready for Delivery` | In der Zustellbasis eingetroffen oder für die heutige Tour vorbereitet. |
| `Out for delivery` / `Due to be delivered today` | Heute mit der Zustellung zu dir unterwegs. |
| `Delivery attempted` | Zustellversuch fehlgeschlagen – prüfe Briefkasten, E-Mail oder SMS. |
| `Due for Redelivery the Next Working Day` | Innerhalb Großbritanniens folgt am nächsten Werktag ein neuer Zustellversuch. |
| `Available for Redelivery or Collection` | In Großbritannien bereit zur Abholung am *Customer Service Point* oder für eine erneute Zustellung. |
| `Delivered` | Zugestellt (`Delivered to Neighbour/Reception by` nennt in Großbritannien Nachbarn oder Empfang). |
| `Returned to sender` | Auf dem Rückweg zum Absender. |

Steht in der Zusammenfassung von Royal Mail `Pending` – etwa während eines Zollscans –, zeigt Peek den Status des neuesten konkreten Scans an, zum Beispiel „Beim Zoll“.

## Wer stellt Royal Mail in Deutschland, Österreich und der Schweiz zu?

Postsendungen mit einer `…GB`-Nummer bleiben im Weltpostnetz und behalten ihre Nummer bei der nationalen Post deines Landes:

- **Deutschland:** Deutsche Post und [DHL Paket](carrier:dhl) übernehmen die Verzollung und Zustellung. Du verfolgst denselben `…GB`-Code auch in der DHL-Sendungsverfolgung. Trifft dich niemand an, geht die Sendung an eine Postfiliale oder einen Paketshop; fehlt eine Benachrichtigungskarte im Briefkasten, kommt sie per Post nach oder du siehst den Abholort nach 3 bis 4 Tagen in der DHL-Sendungsverfolgung (warentragende Sendungen aus Nicht-EU-Ländern lassen sich nicht vorab direkt an eine Filiale adressieren).
- **Österreich:** Die [Österreichische Post](carrier:austrian-post) stellt das Paket zu oder hinterlegt es mit Benachrichtigung („gelber Zettel“) in einer Post-Geschäftsstelle. Als Empfänger kannst du deine Sendung auch ohne gelben Zettel mit einem amtlichen Lichtbildausweis (Reisepass, Personalausweis oder Führerschein) abholen; den Abholzeitpunkt zeigt dein Post Account. Transportschäden musst du spätestens 7 Tage nach Zustellung (ohne Sonn- und Feiertage) melden und Verpackung samt Inhalt bis zu 7 Werktage aufbewahren.
- **Schweiz:** Die [Schweizerische Post](carrier:swiss-post) übernimmt Postverzollung und Zustellung. Verpasst du die Zustellung, erhältst du eine Abholungseinladung: Pakete liegen in der Regel 7 Tage (bei Auslandsendungen wie dieser 15 Tage) in der Filiale bereit; die Frist lässt sich online kostenlos verlängern. Mit einem Login bei „Meine Sendungen“ kannst du zudem kostenlos eine Einmal-Vollmacht erteilen oder eine kostenlose zweite Zustellung von Montag bis Freitag an dieselbe Adresse beauftragen.

## Zoll, Einfuhrumsatzsteuer und Gebühren für Pakete aus Großbritannien

Seit dem Brexit zählt Großbritannien zollrechtlich als Drittland außerhalb der EU; lediglich Nordirland wird im Warenverkehr wie EU-Gebiet behandelt.

### In Deutschland und Österreich (Europäische Union)

Online-Bestellungen aus Großbritannien unterliegen in der EU ab dem ersten Euro der Einfuhrumsatzsteuer (in Deutschland 19 % bzw. ermäßigt 7 %; liegt der Steuerbetrag unter 1,00 €, verzichtet der deutsche Zoll auf den Einzug). Hinzu kommen seit 2026 zwei EU-weite Abgaben für Fernverkäufe aus Drittländern:

- **Pauschaler EU-Zoll von 3 € pro Warenart (bis 150 €):** Seit dem 1. Juli 2026 ist die frühere 150-Euro-Zollfreigrenze entfallen. Für Sendungen bis 150 € Warenwert fällt ein pauschaler Zoll von 3 € pro Warenart an – unabhängig davon, welches Mehrwertsteuerverfahren genutzt wird (also sowohl mit IOSS als auch ohne IOSS). Vier gleiche Paar Socken zählen als eine Warenart (3 €), Socken plus ein Ladekabel als zwei Warenarten (6 €). Ein Präferenzzollsatz aus einem EU-Handelsabkommen (wie dem Abkommen zwischen der EU und Großbritannien) lässt sich laut EU-Leitfaden nur über eine reguläre `H1`-Zollanmeldung geltend machen, sofern für die Steuer nicht IOSS genutzt wurde; alle IOSS-Sendungen zahlen die 3 €. Über 150 € gilt der reguläre Zolltarif.
- **EU-Abfertigungspauschale (*Union Handling Fee*) von 2 € pro Warenart:** Seit dem 1. November 2026 erhebt die EU auf alle Online-Bestellungen aus Nicht-EU-Ländern zusätzlich eine nicht erstattungsfähige Gebühr von 2 € pro Warenart – unabhängig vom Warenwert und auch bei Präferenzursprung.
- **Auslagepauschale bei Zustellung:** Hat der Shop die Einfuhrabgaben nicht schon beim Kauf abgeführt, tritt die Post beim Zoll in Vorleistung. In Deutschland kassiert die Deutsche Post / [DHL Paket](carrier:dhl) die Abgaben bar an der Haustür oder in der Filiale und berechnet dafür eine Auslagepauschale von **7,50 € brutto** (sie entfällt bei Selbstverzollung im Binnenzollamt oder wenn bei Zustellung nichts zu zahlen ist). Gelegentliche private Geschenksendungen bis 45 € bleiben abgabenfrei – Käufe von Privatpersonen auf Auktionsplattformen oder Tauschgeschäfte gelten beim Zoll jedoch nicht als Geschenk. Mehr dazu unter [Paket hängt beim Zoll](guide:customs).

### In der Schweiz (nicht Teil der EU)

In der Schweiz gelten weder der 3-Euro-Zoll noch die 2-Euro-EU-Gebühr. Laut Bundesamt für Zoll und Grenzsicherheit (BAZG) bedeutet der Status `Your item is being held in Customs` bzw. „Vom Zoll einbehalten“ im Regelfall keine Beschlagnahmung, sondern die reguläre Postverzollung durch die [Schweizerische Post](carrier:swiss-post):

- **Schweizer MWST und Gewichtszoll:** Die Einfuhrsteuer beträgt 8,1 % (2,6 % unter anderem für Bücher, Lebensmittel, Zeitschriften und Medikamente) auf Warenwert inklusive Versandkosten. Steuerbeträge bis 5 CHF werden nicht erhoben – das entspricht einer Bemessungsgrundlage bis 62 CHF bei 8,1 % bzw. 193 CHF bei 2,6 %. Auch Gewichtszölle bis 5 CHF entfallen. Private Geschenksendungen aus dem Ausland sind bis 100 CHF abgabenfrei (ausgenommen Alkohol und Tabak).
- **Verzollungspreis der Schweizerischen Post:** Fallen Abgaben an, berechnet die Schweizerische Post für Sendungen aus Nicht-EU-Ländern wie Großbritannien einen Grundpreis von 16,00 CHF (exkl. MWST) zuzüglich 3 % Zuschlag auf den Warenwert; zahlst du online über „Meine Sendungen“ oder die Post-App, gibt es 1,50 CHF Rabatt auf den Grundpreis.

## Wenn du in Großbritannien wohnst: rote Karte und Zustellung

Innerhalb Großbritanniens stellt Royal Mail montags bis samstags meist bis 16:30 Uhr zu, vielerorts auch sonntags, nicht aber an gesetzlichen Feiertagen (*bank holidays*). Briefe der *2nd Class* werden bis Dezember 2026 schrittweise auf jeden zweiten Werktag umgestellt; Pakete inklusive *Tracked 24* kommen weiterhin montags bis samstags. Seit dem 1. April 2026 verlangt Ofcom 90 % Zustellung am nächsten Werktag bei *1st Class* und 95 % binnen drei Tagen bei *2nd Class* (laut Citizens Advice verfehlte Royal Mail beide Ziele von April bis Juni 2026).

Öffnet niemand, legt der Zusteller das Paket am hinterlegten Ablageort (*safe place*) oder bei Nachbarn ab (Einstellungen bleiben in der Royal-Mail-App ein Jahr gespeichert; gegen Nachbarschaftsabgabe schickt Royal Mail auf Wunsch einen Briefkasten-Aufkleber). Sonst erhältst du eine rote Benachrichtigungskarte (*red card*) mit Adresse und Öffnungszeiten des *Customer Service Point* für die [Abholung oder Zweitzustellung](guide:missed-delivery). Bei fälligen britischen Einfuhrabgaben schickt Royal Mail eine Rechnung und bewahrt die Sendung rund 3 Wochen auf (Geschenke bis 39 £ steuerfrei, Käufe bis 135 £ meist schon beim Kauf versteuert, Rückerstattung per Formular BOR 286; in Nordirland gelten für gefährdete Drittlandswaren Zoll plus 2 € Bearbeitungsgebühr pro Warenart in Pfund).

## Royal-Mail-Paket bewegt sich nicht, ist verspätet oder verloren

- **Noch keine Sendungsdaten:** Vor dem ersten Scan kennt Royal Mail eine Nummer oft noch nicht, und `Sender preparing item` heißt, dass das Paket noch beim Absender liegt. Frag nach einigen Tagen beim Händler nach; siehe [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating).
- **Steht auf `Item Leaving the UK`:** Zwischen dem Abflug aus Großbritannien und dem Eingangsscan bei [DHL Paket](carrier:dhl), der [Österreichischen Post](carrier:austrian-post) oder der [Schweizerischen Post](carrier:swiss-post) vergehen oft mehrere Tage ohne Meldung. Prüfe dieselbe Nummer direkt bei deiner Landespost.
- **Verspätet oder verloren:** Wende dich bei Online-Käufen immer zuerst an den Verkäufer (in der EU gilt ohne andere Abrede eine 30-tägige Lieferfrist, und der Händler trägt das Transportrisiko bis zur Übergabe). Bei Royal Mail selbst können Absender oder Empfänger einer in Großbritannien aufgegebenen Sendung mit Einlieferungsbeleg bzw. abgestempelter Verpackung Schadenersatz beantragen – bei *Tracked 24* und *Tracked 48* jedoch ausschließlich der Absender. Für Verspätungen ins Ausland oder bei *Tracked 24/48* zahlt Royal Mail nichts. Als verloren gilt eine Europasendung 20 Werktage nach dem geplanten Liefertermin (weltweit 25 Werktage), die Frist endet 6 Monate nach Einlieferung (innerhalb UK: 11 Werktage bei *1st Class*, 14 bei *2nd Class*, 5 nach Fälligkeit bei *Special Delivery*, im Weihnachtszeitraum jeweils 1 Tag länger, binnen 80 Tagen). Höchstbeträge ohne Zusatzversicherung: 20 £ (*1st/2nd Class*), 75 £ (*Tracked 24/48*), 50 £ (International Tracked/Signed) und 750 £ (*Special Delivery Guaranteed by 1pm*). Antwortfrist: 30 Tage im Inland, 90 Tage international. Mehr unter [Paket verloren](guide:lost-parcel) und [Zurück an den Absender](guide:returned-to-sender).
- **Als zugestellt markiert, aber nicht da:** Frag Mitbewohner, Nachbarn oder Empfang, prüfe den Ablageort und melde dich beim Händler. Siehe [zugestellt, aber nicht erhalten](guide:delivered-not-received).

## Kontakt zu Royal Mail und Schutz vor Phishing

- **Royal Mail Customer Services (UK):** `03457 740 740` (britische Inlandsnummer, Mo–Fr 8:00–18:30, Sa 8:00–15:00, So 9:00–14:00 Uhr britischer Zeit; Textphone `03456 000 606`, Relay UK `18001` + `0345 266 0075`). Aus dem deutschsprachigen Raum ist der Weg über den britischen Shop meist günstiger und schneller. Ungelöste Beschwerden prüft in Großbritannien das *Postal Review Panel* (Antwort binnen 30 Tagen) und danach die unabhängige Schlichtungsstelle POSTRS (`020 7520 3766`, `postrs@cedr.com`).
- **Im Zielland:** Sobald das Paket im Land gescannt wurde, hilft dir der Kundenservice von [DHL Paket](carrier:dhl), der [Österreichischen Post](carrier:austrian-post) oder der [Schweizerischen Post](carrier:swiss-post) weiter.

> Vorsicht vor gefälschten Paket-SMS und E-Mails: DHL weist ausdrücklich darauf hin, dass Kunden nie per SMS nach persönlichen Daten gefragt oder zu Zahlungen aufgefordert werden – verdächtige Nachrichten kannst du an `phishing@dhl.com` melden. In Großbritannien leitest du Betrugs-SMS kostenlos an **7726** und Phishing-Mails an `report@phishing.gov.uk` weiter. Mehr Tipps unter [Paket-SMS-Betrug erkennen](guide:delivery-scam-texts).

## Royal-Mail-Pakete mit Peek verfolgen

[Peek](/) erkennt eine gültige Nummer im Stil `AB482910576GB` sofort als Royal Mail und zeigt „Erkannter Paketdienst“. Geht die Prüfziffer nicht auf, meldet Peek „Die Prüfziffer dieser Nummer geht nicht auf.“: Vergleiche den Code mit deiner Versandmail, übernimm den Korrekturvorschlag bei verwechselten Zeichen wie `O` und `0` oder tippe auf „Wie eingegeben verfolgen“ und wähle über „Ändern“ Royal Mail aus. Bei 21- und 16-stelligen Inlandsreferenzen fragt Peek zuerst Trackingdienste ab und öffnet erst zuletzt die Website von Royal Mail; wird eine seltene 21-stellige Referenz Australia Post zugeordnet oder nicht gefunden, wähle Royal Mail über „Ändern“. Ein `royalmail.com`-Link mit `trackNumber=` erkennt Royal Mail sofort mit jedem dieser Formate, ein `#/tracking-results/`-Link nur mit einer `GB`-Nummer.

Da Peek die Seite von Royal Mail in einem Browser auf dem eigenen Server öffnet und Royal Mail automatisierte Abrufe streng begrenzt, prüft Peek eine Royal-Mail-Sendung von sich aus etwa einmal pro Stunde – an normalen Tagen ebenso wie am Zustelltag, selbst wenn ein Zeitfenster vorliegt. Du siehst den Status, jeden Scan auf einer Karte mit Ort (ohne Postleitzahlen), den voraussichtlichen Liefertag samt Zeitfenster, die Zustellzeit und das Zielland, umgerechnet unter „Uhrzeiten in deiner Zeitzone: …“. Zugestellte Sendungen erscheinen als „Zugestellt“ ohne Empfängername, Unterschrift, Adresse oder Foto; alle übrigen Meldungen behalten den englischen Wortlaut. Benachrichtigungen stellst du auf „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“.

Geht ein `…GB`-Paket ins Ausland, fragt Peek in über 60 Ländern – darunter [DHL Paket](carrier:dhl) in Deutschland, die [Österreichische Post](carrier:austrian-post) in Österreich und die [Schweizerische Post](carrier:swiss-post) in der Schweiz – automatisch auch die Zielpost ab, wechselt bei neuen lokalen Scans auf „Zustellung durch …“ und übernimmt dann deren dichteren Abfragetakt. Zollzahlungen, Umleitungen und Reklamationen erledigst du direkt auf den Seiten von Royal Mail oder deiner Zielpost.

## Häufige Fragen zur Royal-Mail-Sendungsverfolgung

### Wer liefert ein Royal-Mail-Paket in Deutschland, Österreich oder der Schweiz?

Die jeweilige Landespost übernimmt das Paket unter derselben `…GB`-Nummer: [DHL Paket](carrier:dhl) bzw. die Deutsche Post in Deutschland, die [Österreichische Post](carrier:austrian-post) in Österreich und die [Schweizerische Post](carrier:swiss-post) in der Schweiz.

### Muss ich für ein Royal-Mail-Paket aus Großbritannien Zoll bezahlen?

In Deutschland und Österreich fallen Einfuhrumsatzsteuer, seit Juli 2026 bis 150 € pauschal 3 € Zoll pro Warenart und seit November 2026 zusätzlich 2 € EU-Abfertigungsgebühr pro Warenart an; wurden sie nicht beim Kauf bezahlt, zieht die Post sie zuzüglich Auslagepauschale (7,50 € bei DHL) ein. In der Schweiz werden MWST (8,1 % oder 2,6 %) und Gewichtszoll erst ab über 5 CHF Steuer- bzw. Zollbetrag erhoben, dann zuzüglich Postverzollungsgebühr.

### Warum bleibt der Status tagelang auf `Item Leaving the UK` stehen?

Zwischen dem Warenausgang in Großbritannien und dem ersten Eingangsscan im internationalen Postzentrum deines Landes gibt es keine Zwischenscans. Prüfe dieselbe `…GB`-Nummer auch bei DHL, der Österreichischen Post oder der Schweizerischen Post – dort erscheint zuerst, ob die Verzollung läuft.

### Wie oft aktualisiert sich die Royal-Mail-Sendungsverfolgung?

Neue Einträge entstehen erst beim nächsten Scan der Sendung. Peek ruft Royal Mail automatisch rund einmal pro Stunde ab und schaltet nach der Übergabe an DHL, die Österreichische Post oder die Schweizerische Post auf deren schnelleren Abfragetakt um.

:::sources
- [Citizens Advice: Check Royal Mail collection and delivery times](https://www.citizensadvice.org.uk/consumer/post-and-parcels/post-collection-delivery-times/) – Zustelltage und -zeiten in Großbritannien, 2nd Class an jedem zweiten Werktag, Customer Service Points, Telefon und Zeiten
- [Citizens Advice: Telling delivery companies about your delivery or collection needs](https://www.citizensadvice.org.uk/consumer/post-and-parcels/telling-delivery-companies-about-your-delivery-or-collection-needs/) – Ablageort, Nachbarn, Aufkleber, Speicherung für ein Jahr, rote Karte und Zweitzustellung
- [Citizens Advice: Claiming compensation from Royal Mail](https://www.citizensadvice.org.uk/consumer/post-and-parcels/claiming-compensation-from-royal-mail/) – Antragsberechtigte, Einlieferungsnachweis, Wartefristen (20 Werktage nach Europa), Höchstbeträge und Antwortfristen
- [Citizens Advice: Damaged, lost or delayed post or parcel](https://www.citizensadvice.org.uk/consumer/post-and-parcels/damaged-lost-delayed-post-or-parcel/) – zuerst Verkäufer kontaktieren, Postal Review Panel (30 Tage) und Schlichtungsstelle POSTRS
- [Citizens Advice: Response to Royal Mail's Quality of Service results, August 2026](https://www.citizensadvice.org.uk/about-us/media-centre/press-releases/citizens-advice-responds-to-royal-mails-latest-quality-of-service-results-august-2026/) – Ofcom-Ziele seit 1. April 2026, Ergebnisse April bis Juni 2026, 2nd-Class-Umstellung bis Dezember 2026
- [GOV.UK: Tax and customs for goods sent from abroad: Tax and duty](https://www.gov.uk/goods-sent-from-abroad/tax-and-duty) – Rechnung von Royal Mail, rund 3 Wochen Aufbewahrung, Grenzen 39 £ und 135 £, Formular BOR 286, 2 € Gebühr pro Warenart in Nordirland
- [GOV.UK: Avoid and report internet scams and phishing](https://www.gov.uk/report-suspicious-emails-websites-phishing) – Meldung an 7726 und report@phishing.gov.uk
- [UPU: S10 standard, Identification of postal items](https://www.upu.int/UPU/media/upu/files/postalSolutions/programmesAndServices/standards/S10-12.pdf) – 13-stelliger Aufbau, Prüfziffer und durchgehende Sendungsnummer
- [Europäische Kommission: Guidance on the temporary EUR 3 customs duty and the Union handling fee](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 3 € Zoll pro Warenart unabhängig vom Mehrwertsteuerverfahren, H1-Anmeldung für Präferenzursprung ohne IOSS, nicht erstattungsfähige 2 € EU-Gebühr pro Warenart
- [Europäische Kommission: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_de) – Wegfall der 150-Euro-Zollfreigrenze seit 1. Juli 2026
- [Your Europe: Versand und Lieferung](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_de.htm) – 30-tägige Lieferfrist, Händlerhaftung und 3 € Zoll pro Warenart
- [Deutscher Zoll: Internetbestellungen](https://www.zoll.de/DE/Privatpersonen/Postsendungen-Internetbestellungen/Sendungen-aus-einem-Nicht-EU-Staat/Zoll-und-Steuern/Internetbestellungen/internetbestellungen_node.html) – 3 € Zoll ab 1. Juli 2026 und 2 € Union Handling Fee ab 1. November 2026 pro Warenart, 19 % bzw. 7 % Einfuhrumsatzsteuer
- [DHL: Hilfe zu Einfuhrabgaben](https://www.dhl.de/de/privatkunden/hilfe-kundenservice/themen/international/zoll/fragen-zu-kosten.html) – Auslagepauschale von 7,50 € brutto, 1-Euro-Bagatellgrenze, Geschenksendungen bis 45 € und Ausschluss von Privatkäufen/Tauschbörsen
- [DHL: Hilfe zur Lieferung an eine Filiale](https://www.dhl.de/de/privatkunden/hilfe-kundenservice/empfangen/filiale-direkt.html) – Ausschluss von Nicht-EU-Warensendungen für Filiale Direkt, Benachrichtigungskarte und Abholort in der Sendungsverfolgung
- [DHL: Sicherheitshinweise](https://www.dhl.de/de/toolbar/footer/sicherheitshinweise.html) – keine Zahlungs- oder Datenabfrage per SMS, Meldung an phishing@dhl.com
- [Österreichische Post: Hilfe zu Ihrem Sendungsstatus](https://www.post.at/p/c/hilfe-sendungsstatus) – Abholung mit gelbem Zettel oder Lichtbildausweis, 7-tägige Schadensmeldefrist
- [BAZG: Empfangen von Briefen und Paketen](https://www.bazg.admin.ch/de/empfangen-von-briefen-und-paketen) – Bedeutung von „Vom Zoll einbehalten“, Schweizer MWST 8,1 % / 2,6 % und 5-CHF-Freigrenze
- [Schweizerische Post: Importverzollung](https://www.post.ch/de/empfangen/importverzollung) – Geschenke bis 100 CHF, Grundpreis 16 CHF aus Nicht-EU-Ländern, 1,50 CHF Onlinerabatt und 3 % Zuschlag
- [Schweizerische Post: Abholungseinladung](https://www.post.ch/de/empfangen/abholungseinladung) – 7 bzw. 15 Tage Abholfrist, kostenlose Fristverlängerung, Vollmacht und Zweitzustellung
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – erkannte Nummernformate, Statusmeldungen, stündliche Abfrage und Übergabe an die Zielpost in Peek
:::
