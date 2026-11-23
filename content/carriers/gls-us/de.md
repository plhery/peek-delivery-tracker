---
title: GLS US Sendungsverfolgung (GSO): Nummern, Status & Kontakt
description: GLS-US-Paket (auch GSO) verfolgen: SH-Nummer, Bedeutung der Scans, Liefertage, Unterschriften, verpasste Zustellung und Kontakt.
slug: gls-us-sendungsverfolgung
published: 2026-11-23
updated: 2026-11-23
---

GLS US (General Logistics Systems US, Inc.) ist die US-Gesellschaft der GLS Group und liefert im Westen der USA. Peeks Katalog kennt sie auch als GLS USA, GLS United States, General Logistics Systems US, GSO und Golden State Overnight – das Unternehmen, das GLS 2016 übernommen hat. Um ein GLS-US-Paket zu verfolgen, füge die Nummer oben ein oder gib sie auf [der US-Tracking-Seite von GLS](https://gls-group.com/US/en/send-and-receive/track-a-parcel/) ein.

## GLS-US-Sendungsnummer: wie sie aussieht

Peeks Katalog kennt ein Format für GLS-US-Nummern (Ziffern erfunden):

| Was du einfügst (erfunden) | Was Peek tut |
| --- | --- |
| `SH605182704936815247` | SH plus 18 Ziffern, 20 Zeichen insgesamt. Nur GLS US nutzt dieses Format im Peek-Katalog, aber die Zuordnung ist schwach, daher fragt Peek vor der Benennung bei GLS US nach. |
| `sh 6051 8270 4936 8152 47` | Dieselbe Nummer. Groß-/Kleinschreibung, Leerzeichen und Bindestriche spielen keine Rolle. |
| Eine GLS-US-Nummer anderen Formats | Peek erkennt sie nicht automatisch. Wähle GLS US selbst (siehe „Ein GLS-US-Paket mit Peek verfolgen“ unten). |
| Ein Tracking-Link von gls-group.com mit `/US/`, oder von gls-us.com oder gso.com | Peek liest die Nummer aus dem Link aus und benennt sofort GLS US („Erkannter Paketdienst“). |

**Wo sie steht.** In der Versandmail oder auf der Bestellseite des Shops, oder in E-Mails/SMS von GLS, falls der Shop dich angemeldet hat. Jeder Karton trägt ein eigenes Etikett, mehrere Kartons bedeuten also mehrere Nummern. Eine Suche ohne Nummer bei GLS verlangt Kundennummer und Referenz des Absenders: Frag den Shop. Siehe [Sendungsnummer finden](guide:find-tracking-number). Eine Bestellnummer funktioniert nicht.

**Nicht das europäische GLS.** GLS US gehört zur GLS Group, doch Peek folgt seinen Paketen auf dem eigenen Tracker von GLS US. Ein gls-group.com-Link mit `/EU/` oder `/DE/` führt zu [GLS Germany](carrier:gls-de), nicht zu GLS US. Nennt der Shop eine europäische GLS-Gesellschaft oder einen anderen Dienst, verfolge das Paket dort. [Sendungsnummern-Formate](guide:tracking-number-formats) zeigt andere Formate.

## Was die GLS-US-Statusmeldungen bedeuten

Jeder Scan im GLS-US-Tracker enthält Datum und Uhrzeit, und die meisten nennen Ort, Bundesstaat und Land. Die von Peek erkannten Scans stehen auf Englisch in Großbuchstaben, in typischer Reihenfolge:

| GLS US schreibt | Was das für dich bedeutet | Peek zeigt |
| --- | --- | --- |
| `LABEL CREATED` | Der Versender hat das Etikett erstellt. GLS US hat das Paket vielleicht noch nicht. Dieser Scan nennt keinen Ort. | „Etikett erstellt“ |
| `ARRIVAL SCAN - DELIVERY SCHED FOR 07/15/2026` | GLS US hat das Paket bei Ankunft gescannt. Das Datum am Ende (Monat zuerst) ist der geplante Zustelltag. | „Unterwegs“ |
| `SCANNED AT DELIVERY DEPOT` | Im zustellenden Depot gescannt. | „Unterwegs“ |
| `ON ROUTE FOR DELIVERY` | Heute auf der Zustelltour. | „In Zustellung“ |
| `SHIPMENT DELIVERED` | Zugestellt. | „Zugestellt“ |

**Gesamtstatus der Sendung.** Über den Scans fasst der Tracker das Paket kurz zusammen: `label created`, `in transit`, `out for delivery`, `delivered` oder `returned`. Ist dieser Status weiter als die Scans, richtet sich Peek danach: `delivered` zeigt „Zugestellt“ auch ohne entsprechenden letzten Scan, und `returned` zeigt „Zurück an Absender“.

**Weitere Meldungen.** Peeks Code kennt keine eigenen GLS-US-Wortlaute für verpasste Zustellung, Problem, Lagerung oder Abholstelle. Unbekannte Zeilen bleiben im Originalwortlaut stehen und werden nach Peeks allgemeinen Regeln eingeordnet. Mehr unter [Bedeutung von Tracking-Status](guide:tracking-statuses).

Folgt auf `LABEL CREATED` mindestens einen Tag lang nichts, zeigt Peek: „Der Absender hat es noch nicht an GLS US übergeben. Die Sendungsverfolgung beginnt mit der Übergabe.“ Frag beim Shop nach.

**E-Mails und SMS von GLS.** Meldet der Shop dich beim Erstellen des Etiketts an, schreibt GLS beim Depot-Eingang (mit erwartetem Lieferdatum), bei Verspätung oder fehlgeschlagenem Versuch, bei der Auslieferung (mit geschätzter Zeit) und bei Zustellung.

## Lieferzeiten, Unterschriften und verpasste Zustellungen bei GLS US

**Adresse.** GLS liefert nicht an Postfächer (P.O. Boxes) und sortiert nach ZIP-Code – ein falscher Code kann verzögern. Umleitungen erfolgen nur mit Genehmigung des Absenders: Frag den Shop. Siehe [Lieferadresse ändern](guide:change-delivery-address).

**Tage.** Montag bis Freitag; Samstag nur in bestimmten Gebieten auf Wunsch des Absenders; nie Sonntag. GLS hat an sechs US-Feiertagen geschlossen (Neujahr, Memorial Day, Independence Day, Labor Day, Thanksgiving und Weihnachten). Ein an einem Feiertag fälliges Paket kommt am nächsten Werktag.

**Zeiten.** Abhängig vom gewählten Service: Priority kommt in den meisten Gebieten bis 10:30 Uhr am nächsten Werktag, Ground bis 17:00 Uhr (18:00 Uhr in ländlichen Regionen). Auf längeren Strecken kommt Priority bis 10:30 Uhr am geplanten Tag und Ground bis Tagesende. Samstagszustellungen kommen meist bis 10:30 Uhr.

**Unterschrift.** Der Absender wählt die Option. Standardmäßig holt der Fahrer an Geschäftsadressen eine Unterschrift ein. An einem Einfamilienhaus wird das Paket abgelegt, wenn niemand unterschreiben kann und der Platz sicher und trocken ist; in Mehrfamilienhäusern vor der Wohnungstür oder beim Verwaltungs-/Vermietungsbüro, falls die Tür nicht erreichbar ist. Bei *Signature Required* erfolgt keine Ablage ohne Unterschrift; bei *Adult Signature Required* (z. B. Alkohol und Tabak) muss die unterzeichnende Person mindestens 21 Jahre alt sein.

**Verpasste Zustellung.** Versuch verpasst oder Benachrichtigung an der Tür gefunden? Vereinbare über den Link in GLS' E-Mails oder SMS einen neuen Termin oder ruf die Empfänger-Hotline an (unten). Die Seiten von GLS nennen weder Versuchszahl noch Lagerfrist und führen GLS Points nur als Abgabestellen für Absender auf. Siehe [verpasste Zustellung](guide:missed-delivery) und [nicht abgeholte Pakete](guide:uncollected-parcel).

## GLS-US-Paket nicht gefunden, ohne Update, verloren oder zurück

**Nicht gefunden.** Der GLS-US-Tracker meldet, wenn er eine Nummer nicht kennt. Peek zeigt dann „Warten auf den ersten Scan von GLS US“ bei einer SH-Nummer oder „Warten auf GLS US“, wenn du GLS US selbst gewählt hast, und fragt weiter. Neue Etiketten brauchen oft etwas Zeit. Kommt zwei Tage nach dem Hinzufügen einer SH-Nummer weiter nichts, setzt Peek den Hinweis „Warten auf das erste Update des Paketdienstes“. Hast du GLS US selbst gewählt und es kennt die Nummer nach zwei Tagen weiter nicht (ohne andere Quellen), meldet Peek „Auch nach zwei Tagen kennt der Paketdienst diese Nummer nicht. Prüfe sie, wähle einen anderen Paketdienst oder lösche das Paket.“ und prüft fortan täglich, nach einer Woche wöchentlich. Prüfe die Nummer in der Versandmail und frag den Shop, ob wirklich versendet wurde.

**Tagelang nichts Neues.** Nach vier Tagen ohne neuen Scan erhält ein Paket mit „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“ den Hinweis „Keine Aktualisierung seit …“ mit dem Tag dieses Scans. Peek weist darauf hin, dass Pakete übers Wochenende oft ruhen, fragt weiter bei GLS US an und zeigt einen Link „Hol dir Hilfe bei GLS US“. Siehe [Tracking aktualisiert sich nicht](guide:tracking-not-updating).

**Zugestellt, aber nichts da.** `SHIPMENT DELIVERED` ohne Paket vor der Tür? Prüfe im GLS-Tracker, ob jemand unterschrieben hat, schau rund um die Tür, in der Poststelle, beim Vermietungsbüro und bei Nachbarn nach, ruf dann GLS' Empfänger-Hotline an und informiere den Shop. Siehe [zugestellt, aber nicht erhalten](guide:delivered-not-received).

**Verloren oder beschädigt.** Melde dich zuerst beim Shop, der GLS US beauftragt hat und Ersatz senden oder reklamieren kann. Nur der Absender kann binnen 90 Tagen ab Versand reklamieren; GLS strebt eine Klärung binnen 45 Tagen an. Bei Schäden verlangt GLS vom Absender Fotos von Artikel, Innenverpackung und Karton – mach sie vor dem Entsorgen. Siehe [verlorene Pakete](guide:lost-parcel) und [beschädigte Pakete](guide:damaged-parcel).

**Zurückgesendet.** „Zurück an Absender“ bedeutet, das Paket ging an den Shop zurück. Bitte dort um Erstattung oder Neuversand. Siehe [Zurück an Absender](guide:returned-to-sender).

## GLS US Kundenservice und Kontakt

Auf der [Kundenservice-Seite von GLS US](https://gls-group.com/US/en/support/customer-support/):

- **Telefon:** 1-800-322-5555, Option 2 (Empfängersupport: Tracking, Zustellprobleme, Zustellhilfe).
- **Zeiten:** Montag bis Freitag 7:00 bis 19:00 Uhr und Samstag 7:00 bis 16:00 Uhr Pacific Time.
- **Live-Chat**, angeboten in der GLS-FAQ. Eine E-Mail-Adresse für Empfänger nennt GLS nicht.

> **Betrug.** GLS bittet Empfänger niemals per E-Mail um Kartenzahlung, Online-Zahlung oder Überweisung für Waren – bei Auslandssendungen kann GLS oder ein Agent jedoch Zoll und Steuern anfordern. Echte GLS-Seiten sind gls-group.com und davon verlinkte Seiten. Melde Fälschungen an security@gls-us.com und lies [Betrugs-SMS zu Paketen](guide:delivery-scam-texts).

## Ein GLS-US-Paket mit Peek verfolgen

**Eine SH-Nummer.** Füge sie oben ein. Während Peek GLS US fragt, steht in der Zeile „Tracking-Dienste werden geprüft…“. Kennt GLS US sie, nennt die Zeile GLS US mit „hat dieses Paket“ und legt es dort ab. Kennt GLS US sie noch nicht, erscheint „noch nicht gefunden · Peek prüft weiter“. Füge es trotzdem hinzu: Bis zum ersten Scan zeigt es „Wahrscheinlich GLS US“.

**Eine andere Nummer.** Füge sie trotzdem ein: Peek verfolgt über 4500 Paketdienste. Wähle GLS US dann selbst:

1. Tippe neben der Paketdienst-Zeile auf „Ändern“ und suche nach GLS US (auch „GSO“, „Golden State Overnight“, „General Logistics Systems US“ oder „United States“ finden es).
2. Wähle GLS US: Die Zeile zeigt nun „Von dir gewählt“. Starte das Tracking.

Bei einem bereits hinzugefügten Paket nutze „Paketdienst ändern“. Peek fragt GLS US nur für Nummern aus 5 bis 20 Buchstaben und Ziffern ab.

**Was du siehst.** Peek fragt den GLS-US-Tracker allein mit der Nummer ab – ohne Konto und ohne ZIP-Code. Es zeigt Status, jeden Scan mit Stadt und Bundesstaat auf einer Karte sowie, sofern angegeben, den erwarteten Liefertag bis zur Zustellung oder Rücksendung. Auf deiner eigenen Paketseite zeigt es den Empfängernamen, den GLS US angibt (unter „Empfänger“), und lässt die Route deiner Karte in der Lieferstadt enden; geteilte Links zeigen beides nicht. Wer unterschrieben hat, Unterschrift, Abliefernachweis und Absenderreferenz zeigt Peek nicht. Peeks Code übergibt GLS-US-Pakete an keinen anderen Dienst. Führt GLS US mehrere Sendungen unter einer Nummer, folgt Peek der mit dem jüngsten Scan. „Website von GLS US öffnen“ auf der Paketseite öffnet die Nummer direkt im US-Tracker von GLS.

**Uhrzeiten.** GLS US liefert Zeiten ohne Zeitzone. Peek liest sie in der Ortszeit der genannten Stadt oder sonst des Bundesstaats (Hauptzeitzone bei mehrzonigen Staaten). Fehlt beides, gilt die Zone des nächstgelegenen früheren, sonst späteren Scans, und mangels beider Pacific Time. Peek zeigt alle Zeiten in deiner Gerätezeitzone an.

**Wenn GLS US nicht antwortet.** Ist der Tracker nicht erreichbar, fragt Peek [Tracking-Dienste](guide:universal-tracker) und zeigt deren Verlauf in ihren eigenen Worten.

**Häufigkeit.** Solange das Paket in Peek geöffnet ist, oder an seinem Zustelltag mit aktiven Benachrichtigungen, prüft Peek es bis zu alle 10 Minuten, und alle 2 Minuten ab 6:00 Uhr Ortszeit an dem Tag, an dem GLS US es auf Zustelltour schickt; sonst stündlich. Nach zwei Tagen ohne Neuigkeiten (12 Stunden bei „Etikett erstellt“ oder „Beim Paketdienst eingegangen“) prüft Peek stündlich, und nach sieben Tagen ohne Neuigkeiten bei „Etikett erstellt“ alle 6 Stunden. Benachrichtigungen decken „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“ ab.

Peek kann weder GLS US oder den Shop kontaktieren noch die Zustellung ändern oder Reklamationen einreichen.

## Fragen zur GLS-US-Sendungsverfolgung

### Ist GSO dasselbe wie GLS US?

GLS hat Golden State Overnight (GSO) 2016 übernommen, und gso.com leitet heute zu GLS US weiter. GSO wurde 1995 für Lieferungen in Kalifornien, Arizona, Nevada und New Mexico gegründet. Peek behandelt beide als einen Dienst: Der Katalog listet GSO, Golden State Overnight, GLS USA, GLS United States und General Logistics Systems US als Synonyme für GLS US und erkennt Tracking-Links von gso.com und gls-us.com als GLS US.

### Wie sieht eine GLS-US-Sendungsnummer aus?

Das bekannte Format ist SH gefolgt von 18 Ziffern, z. B. `SH605182704936815247` (erfunden). GLS nennt auf seinen Seiten kein Format und verwendet eventuell weitere Formen: Füge sie einfach ein und wähle GLS US über „Ändern“.

### Was bedeutet der GLS-US-Arrival-Scan?

`ARRIVAL SCAN - DELIVERY SCHED FOR 07/15/2026` heißt, GLS US hat das Paket bei Ankunft gescannt und die Zustellung für das Datum am Ende (Monat zuerst) angesetzt. Peek zeigt „Unterwegs“.

### Warum zeigt Peek „Warten auf den ersten Scan von GLS US“?

Deine Nummer hat das GLS-US-Format und Peek hat bei GLS US angefragt, doch GLS US führt sie noch nicht. Bei neuen Etiketten ist das normal und Peek fragt weiter. Bleibt sie eine Woche lang unbekannt, weist Peek darauf hin und rät, die Nummer beim Absender zu prüfen oder einen anderen Dienst zu wählen.

### Ist GLS US dasselbe wie GLS in Europa?

Es gehört zur selben GLS Group, aber Peek führt GLS US als eigenen Paketdienst mit eigenem Tracker. Europäische GLS-Gesellschaften wie [GLS Germany](carrier:gls-de) haben eigene Tracker – verfolge dein Paket mit der in der Shop-E-Mail genannten GLS-Gesellschaft.

:::sources
- [GLS US: Track a parcel](https://gls-group.com/US/en/send-and-receive/track-a-parcel/) – US-Tracker, Suche ohne Nummer, Unterzeichner
- [GLS US: About GLS US](https://gls-group.com/US/en/about-gls-us/) – US-Gesellschaft der GLS Group, Westen der USA, GSO-Geschichte
- [GSO: gso.com](https://www.gso.com/) – leitet zu GLS US weiter
- [GLS US: Domestic shipping services](https://gls-group.com/US/en/Shipping/shipping-services/domestic/) – Lieferzeiten, Samstag in einigen Gebieten
- [GLS US: Holiday schedule](https://gls-group.com/US/en/resources/holiday-schedule/) – Liefertage, Feiertage
- [GLS US: Parcel Tariff](https://gls-group.com/US/en/parcel-tariff/) – kein Sonntag, Feiertage, Unterschriften, ZIP-Codes, ein Etikett pro Karton, Reklamationen binnen 90 Tagen
- [GLS US: FAQ](https://gls-group.com/US/en/support/faq/) – Postfächer, Umleitung, Neuterminierung, Türzettel, Benachrichtigungen, Live-Chat, Reklamationen
- [GLS US: Claims](https://gls-group.com/US/en/support/claims/) – Absender reklamiert, 45 Tage, Fotos
- [GLS US: Customer support](https://gls-group.com/US/en/support/customer-support/) – Empfänger-Hotline, Servicezeiten
- [GLS US: Find a GLS Point](https://gls-group.com/US/en/Shipping/shipping-tools/find-drop-point/) – nur Paketabgabe
- [GLS US: Security advice](https://gls-group.com/US/en/security-advice/) – Zahlungsaufforderungen, Zoll und Steuern, echte Websites, Meldung
- [Universal Parcel Scraper: GLS US](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/gls-us) – Namen, SH-Format und schwache Zuordnung, Links, Scans und Gesamtstatus, Nicht-gefunden-Antwort, Liefertag, mehrere Sendungen unter einer Nummer, Zeitzonen, weggelassene Daten
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – europäische GLS-Netze und Links, Tracking-Dienste als Ausweichquelle, Ortszeiten nach Stadt
:::
