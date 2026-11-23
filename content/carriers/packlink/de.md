---
title: Packlink Sendungsverfolgung: Referenz, Status und Kontakt
description: Packlink Paket verfolgen: ES…COM-Referenz, Bedeutung der Tracking-Zeilen, verpasste Zustellungen, Schadensmeldung und Kontakt zu Packlink.
slug: packlink-sendungsverfolgung
published: 2026-11-23
updated: 2026-11-23
---

Packlink ist kein Paketdienst. Es handelt sich um eine Buchungsplattform mit Sitz in Madrid, die sich selbst als Versandvergleichsportal bezeichnet: Privatpersonen oder Shops vergleichen dort Paketdienste wie SEUR, Correos, Correos Express oder UPS, bezahlen, drucken das Etikett, und der gewählte Dienst holt das Paket ab und stellt es zu. Händler nutzen Packlink PRO, den Geschäftsdienst mit Anbindung an Onlineshops und Marktplätze wie eBay und Amazon. Um ein Paket zu verfolgen, gib die Packlink-Referenz oder die Nummer des Paketdienstes auf [Packlinks Tracking-Seite](https://www.packlink.es/seguimiento-envios/) oder im Feld oben ein.

## Packlink Sendungsnummer: wie sie aussieht

Packlink vergibt für jede Sendung eine eigene Referenz: zwei Buchstaben für den Markt, das Jahr, `COM` oder `PRO` und dann zehn Ziffern. Auf der spanischen Seite von Packlink heißt es, die Referenzen beginnen mit ES. Der Paketdienst vergibt zusätzlich seine eigene Nummer, und Packlinks Tracking-Seite akzeptiert beide. Mit erfundenen Ziffern:

| Nummer (erfunden) | Was sie bedeutet |
| --- | --- |
| `ES2026COM4829173650` | ES, Jahr, COM und 10 Ziffern: eine Packlink-Referenz. |
| `ES2026PRO6194028375` | Dasselbe mit PRO: ebenfalls eine Packlink-Referenz. |
| `48291736405829` | 14 Ziffern: eines der Formate von SEUR, das auch DPD und andere Dienste teilen. |
| `1Z9X482W0348291737` | 1Z und 16 Buchstaben oder Ziffern: eine UPS-Nummer. |

**Wo du sie findest.** Packlink schickt die Referenz in der Bestätigungsmail bei der Buchung. Hat dir jemand etwas verkauft und über Packlink verschickt, hat der Verkäufer diese E-Mail: Frag ihn nach der Referenz oder der Nummer des Paketdienstes. Weitere Stellen zum Suchen: [Sendungsnummer finden](guide:find-tracking-number) und [Secondhand-Bestellungen](guide:second-hand-orders).

## Packlink Tracking-Status erklärt

Packlink übernimmt jeden Scan des Paketdienstes unter einem eigenen Statuscode, der auf der Tracking-Seite einen englischen Text hat. Viele Scans behalten daneben die Worte des Paketdienstes, etwa die spanischen Großbuchstaben von SEUR oder Correos Express. Häufige Zeilen in typischer Reihenfolge:

| Tracking-Zeile | Was das für dich bedeutet |
| --- | --- |
| `Waiting for carrier to collect the order` | Der Absender hat gebucht und das Etikett erstellt. Der Paketdienst hat das Paket noch nicht. |
| `SIN RECEPCION` | Dasselbe bei Correos Express: Der Auftrag liegt vor, das Paket noch nicht. |
| `RECIBIDO EN ORIGEN` | Das erste SEUR-Depot hat das Paket. Packlink führt es trotzdem noch als wartend auf Abholung. |
| `Shipment accepted by carrier (scanned)` | Der Paketdienst hat das Paket gescannt. Es ist unterwegs. |
| `EN ARRASTRE` | Transport zwischen den Depots des Paketdienstes. |
| `DELEGACION DESTINO` | Im Depot, das deine Region beliefert. |
| `TU ENVÍO ESTÁ PENDIENTE DE LLEGAR A DESTINO` | SEUR: noch nicht im lokalen Depot angekommen. Packlink verbucht das als Zustellproblem, das Paket ist aber schlicht weiter unterwegs. |
| `EN REPARTO` | In Zustellung. |
| `REPARTO FALLIDO` | Ein Zustellversuch schlug fehl. |
| `Package at pick-up point and ready for collection` | Wartet an einer Abholstelle auf dich. Packlink zählt es bei seinen Zustellcodes mit, du musst es aber noch abholen. |
| `ENTREGADO` | Zugestellt. |
| `Package returned to sender` | Das Paket ging zurück an den Absender. |

Andere Zeilen stehen in klarem Englisch: `Recipient not present at indicated address` und `Unsuccessful delivery and final attempt` stehen für verpasste Zustellungen, und `Packet delivered to customs for clearance` für einen Zollschrittt bei grenzüberschreitenden Sendungen.

Zwei Zeilen sagen nichts über das Paket selbst. `Pending, there is no information available on the carrier’s page or the tracking number has not yet been activated` heißt, Packlink hat beim Paketdienst noch nichts gefunden. `Expired, there is no tracking information for the last 30 days` heißt, der Paketdienst lieferte einen Monat lang nichts Neues und Packlink hat die Verfolgung beendet.

## Wer ein Packlink Paket zustellt

Der vom Absender gewählte Paketdienst. Packlinks Tracking-Seite nennt unter anderem [SEUR](carrier:seur), [Correos](carrier:correos-spain), [Correos Express](carrier:correos-express), [UPS](carrier:ups), GLS, DHL, [TNT](carrier:tnt), [InPost](carrier:inpost), [Mondial Relay](carrier:mondial-relay), [NACEX](carrier:nacex) und Zeleris. Laut Packlinks Bedingungen gilt:

- **Haustür oder Abholstelle.** Der Absender wählt bei der Buchung, ob das Paket an eine Adresse oder an eine Abholstelle geht.
- **Verpasste Zustellung.** Der Paketdienst kann eine Karte für einen neuen Termin hinterlassen und das Paket ins Depot mitnehmen. Nicht jeder Dienst hinterlässt eine Karte, weshalb Packlink den Absender bittet, das Tracking zu beobachten und dich zu informieren.
- **Zustellversuche.** Meist gibt es einen ersten und einen weiteren kostenlosen Versuch. Jeder zusätzliche Versuch kostet den Absender je nach Paketdienst und Fahrzeug 2 € bis 15 € zuzüglich MwSt. In manchen Ländern darf der Dienst das Paket auch bei Nachbarn oder an einer Abholstelle abgeben.
- **Abholung.** Ein im Depot gelagertes Paket muss binnen der Frist des jeweiligen Paketdienstes abgeholt werden. Danach können für den Absender Lager-, Rücksende- oder Vernichtungskosten anfallen.
- **Abgelehnt oder unzustellbar.** Packlink informiert den Absender im Tracking oder per E-Mail. Dieser hat dann 2 Werktage Zeit zu entscheiden, ob das Paket zurück oder an eine andere Adresse gehen soll.

Siehe auch [verpasste Zustellung](guide:missed-delivery) und [nicht abgeholte Pakete](guide:uncollected-parcel).

**Zoll.** Kommt dein Paket von außerhalb der EU und enthält online gekaufte Waren, gilt seit dem 1. Juli 2026 für Bestellungen bis 150 € ein EU-Zoll von 3 € pro Produktart. Seit dem 1. November 2026 fällt außerdem unabhängig vom Wert eine Unions-Bearbeitungsgebühr von 2 € pro Produktart an. Packlinks Bedingungen besagen, dass der Empfänger Einfuhrabgaben und Steuern trägt, sagen aber nicht, wie diese beiden Gebühren abgerechnet werden. Siehe [Pakete beim Zoll](guide:customs).

## Packlink Paket kommt nicht an, beschädigt oder zurückgesendet

**Wer handeln kann.** Nach Packlinks Bedingungen kann nur die Person, die gebucht und bezahlt hat, Reklamationen und Anfragen stellen – von der bei der Buchung genutzten E-Mail-Adresse. Dritte erhalten nur mit einer eidesstattlichen Vollmacht des Absenders Zugang. Als Käufer muss also der Verkäufer Packlink kontaktieren.

**Kein Update.** Solange `Waiting for carrier to collect the order` angezeigt wird, hat der Paketdienst das Paket noch nicht abgeholt: Frag den Absender, ob es übergeben wurde. Bei jeder Verspätung oder fehlenden Aktualisierung kontaktiert der Absender Packlink. Mehr unter [Warum sich die Sendungsverfolgung nicht mehr aktualisiert](guide:tracking-not-updating).

**Verloren.** Packlink bearbeitet eine Verlustmeldung erst, wenn der Paketdienst seine Depots durchsucht und das Paket für verloren erklärt hat. Diese Suche dauert meist bis zu 30 Tage ab dem voraussichtlichen Lieferdatum. Siehe [verlorene Pakete](guide:lost-parcel).

**Beschädigt.** Sind bei Zustellung Schäden sichtbar, schreibe *Con Daños* (mit Schäden) auf den Abliefernachweis oder das Handgerät des Fahrers, wie Packlink verlangt. Ohne diesen Vermerk kann Packlink die Reklamation nach eigenen Angaben nicht zufriedenstellend bearbeiten. Bewahre Ware und komplette Verpackung zur Prüfung auf: Packlink lehnt Reklamationen ab, wenn die Ware verändert oder repariert oder die Verpackung weggeworfen wurde. Bei Verlust oder verdeckten Schäden muss der Absender innerhalb von 7 Tagen bei Straßentransport (in Spanien oder international), 10 Tagen bei Luftfracht im Inland und 14 Tagen bei internationaler Luftfracht reklamieren, gezählt ab dem Tag nach der Zustellung. Siehe [beschädigte Pakete](guide:damaged-parcel).

**Zurückgesendet.** `Package returned to sender` bedeutet, das Paket ist wieder beim Absender. Bitte ihn um erneuten Versand oder Erstattung. Siehe [zurück an den Absender](guide:returned-to-sender).

**Zugestellt, aber nicht da.** Frag bei Nachbarn und an der Abholstelle nach und informiere sofort den Absender. Siehe [zugestellt, aber nicht erhalten](guide:delivered-not-received).

## Packlink Telefonnummer und Kontakt

Kontaktdaten laut Packlink:

- **Telefon:** keines. Packlink erklärt, ausschließlich online zu arbeiten und keinen Telefonsupport anzubieten. Es warnt davor, dass Telefonnummern für Packlink auf anderen Websites nicht echt oder von Packlink verifiziert sind: Nutze sie nicht.
- **Kontaktformular:** im Hilfezentrum von Packlink, verlinkt von der Telefon-Infoseite. Wähle die passende Kategorie und halte bei einer bestehenden Sendung die Referenz aus der Bestätigungsmail bereit. Packlink bietet außerdem Live-Chat mit dem Kundenservice.
- **Reklamationen:** über dasselbe Hilfezentrum, ausschließlich durch die Person, die gebucht hat.

## Packlink Paket mit Peek verfolgen

Füge die Packlink-Referenz in [Peek](/) ein. Ihr Format gehört allein zu Packlink, daher zeigt das Eingabefeld Packlink sofort als „Erkannter Paketdienst“. Du kannst stattdessen auch die Nummer des Paketdienstes einfügen: Peek verfolgt über 4.500 Paketdienste. Passt diese Nummer zu mehreren Diensten – wie 14 Ziffern bei SEUR –, zeigt Peek „Tracking-Dienste werden geprüft…“, während es sie abfragt; über „Ändern“ wählst du den Dienst selbst.

Solange du das Paket in Peek geöffnet hast, oder am Zustelltag mit aktivierten Benachrichtigungen, fragt Peek Packlink direkt bis zu alle 10 Minuten ab, und ab 6:00 Uhr Madrider Zeit am Tag der Zustellung bis zu alle 2 Minuten, solange das Tracking von Packlink antwortet. Nach zwei Tagen ohne Neuigkeiten (nach 12 Stunden, solange das Paket noch „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ zeigt) prüft Peek stündlich; nach sieben Tagen ohne Neuigkeiten bei „Etikett erstellt“ alle 6 Stunden. Nach vier Tagen ohne Scan bei einem Paket im Status „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“ zeigt Peek „Keine Aktualisierung seit …“, den Hinweis „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei Packlink nach.“ und den Link „Hol dir Hilfe bei Packlink“. Es zeigt jeden Scan im Wortlaut des Paketdienstes, soweit Packlink ihn liefert, sonst im englischen Packlink-Text, gelesen in Madrider Zeit und angezeigt in deiner Gerätezeitzone. Bei einer Abholstellen-Zeile zeigt Peek „Abholbereit“, bei `RECIBIDO EN ORIGEN` „Beim Paketdienst eingegangen“. Das von Packlink erwartete Lieferdatum erscheint unterwegs, und eine Mehrpaket-Sendung erscheint als ein Verlauf. Peek lässt Ortsangaben weg – daher gibt es keine Karte – sowie die Paketanzahl. Gewicht, Absender und Empfänger nennt Packlinks Tracking nicht.

Packlinks Tracking enthält auch die eigene Nummer des Paketdienstes. Nennt deren Form einen Paketdienst eindeutig – wie eine UPS-Nummer beginnend mit 1Z –, fragt Peek auch diesen Dienst an. Sobald dieser einen Scan mindestens so aktuell wie den neuesten von Packlink zeigt, verfolgt Peek das Paket dort und behält Packlinks Verlauf bei: Das Paket zeigt dann „Zustellung durch UPS“ und beide Nummern in den Details. Kein Format von SEUR oder Correos Express ist so eindeutig, daher bleiben deren Pakete auf dem Packlink-Tracking.

Peek kann dich über „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“ benachrichtigen, aber weder reklamieren noch die Zustellung ändern oder jemanden für dich kontaktieren.

## Fragen zur Packlink Sendungsverfolgung

### Ist Packlink ein Paketdienst?

Nein. Packlink ist eine Plattform, auf der Privatpersonen und Shops Paketdienste vergleichen und buchen. Der gewählte Dienst – etwa SEUR, Correos Express oder UPS – holt das Paket ab und stellt es zu.

### Wie lautet die Telefonnummer von Packlink?

Es gibt keine: Packlink arbeitet ausschließlich online und warnt, dass Telefonnummern auf anderen Websites nicht verifiziert sind. Nutze das Kontaktformular im Hilfezentrum oder den Live-Chat.

### Wo finde ich meine Packlink Sendungsnummer?

In der Bestätigungsmail, die Packlink bei der Buchung geschickt hat: zwei Buchstaben wie ES, das Jahr, COM oder PRO und zehn Ziffern. Bist du der Käufer, frag den Absender danach.

### Warum steht bei meinem Packlink Tracking „Expired“?

`Expired, there is no tracking information for the last 30 days` bedeutet, dass der Paketdienst einen Monat lang nichts gemeldet hat und Packlink die Verfolgung beendet hat. Es heißt nicht, dass das Paket verloren ist. Bitte den Absender, Packlink zu kontaktieren.

:::sources
- [Packlink: Seguimiento envios](https://www.packlink.es/seguimiento-envios/) – Tracking über Packlink-Referenz oder Paketdienstnummer, Referenzen ab ES, gelistete Dienste, Packlink PRO
- [Packlink: ¿Y cómo funciona esto de Packlink?](https://www.packlink.es/como-funciona/) – Vergleich und Buchung von Paketdiensten, Etiketten, Packlink-PRO-Anbindungen an eBay und Amazon
- [Packlink: Teléfono Packlink](https://www.packlink.es/telefono-packlink/) – Vergleichsportal, kein Telefonsupport, nicht verifizierte Nummern auf anderen Seiten, Kontaktformular, Live-Chat, Referenz in der Bestätigungsmail
- [Packlink: Términos y Condiciones](https://www.packlink.es/terminos-condiciones/) – Firmensitz Madrid, nur der Buchende darf reklamieren, Adresse oder Abholstelle, Benachrichtigungen, Versuche und Kosten, Abholfrist, abgelehnte Pakete, Einfuhrabgaben, Reklamationsfristen, Schadensvermerk, 30-Tage-Suche
- [Europäische Kommission: EU-Zollreform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 3 € Zoll je Produktart bis 150 € seit 1. Juli 2026, 2 € Unions-Bearbeitungsgebühr seit 1. November 2026
- [Europäische Kommission: Leitfaden zu 3 € Zoll und Unions-Bearbeitungsgebühr (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € je Produktart unabhängig vom Wert für Fernabsatzwaren
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Referenzformat, Nummernformate der Dienste, Status-Wortlaute und Codes, Madrider Zeit, was Peek aus Packlinks Tracking ausliest, Übergabe an den Paketdienst
:::
