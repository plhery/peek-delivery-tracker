---
title: SPX Express Singapur Sendungsverfolgung & Shopee-Status
description: SPX Express (Shopee Xpress) Paket in Singapur verfolgen: SPXSG- und SG-Nummern, Statusmeldungen, GST bei Einfuhr und Hilfe bei Stillstand.
slug: spx-express-singapore-sendungsverfolgung
published: 2026-11-22
updated: 2026-11-22
---

SPX Express Singapore, auch Shopee Xpress oder Shopee Express genannt, liefert Pakete in Singapur aus. Der Dienst befördert Shopee-Bestellungen, darunter Sendungen von Verkäufern aus China über das Shopee-Sortierzentrum in Hongkong. Der eigene Tracker von SPX liegt unter spx.sg. Um ein Paket hier zu verfolgen, füge die Nummer oben ein. Bei einer Shopee-Bestellung öffnest du sie in Shopee und kopierst die Sendungsnummer aus den Versandinformationen.

## Sendungsnummer von SPX Express Singapur: wie sie aussieht

Peek kennt zwei Formate für SPX Express Singapore (Ziffern erfunden):

| Nummer (erfunden) | Bedeutung |
| --- | --- |
| `SPXSG48291736482K` | SPXSG, 11 Ziffern und ein weiteres Zeichen (Ziffer oder Buchstabe, insgesamt 17 Zeichen): die eigene Nummer von SPX. Peek erkennt SPX Express Singapore sofort. |
| `SG482917364829K` | SG, 12 Ziffern und eine Ziffer oder ein Buchstabe am Ende (15 Zeichen): die zugehörige Shopee-Nummer im SPX-Tracker. Anhand dieses Formats allein ordnet Peek SPX nicht sicher zu (siehe unten). |
| `SPXSG4829173648` | SPXSG mit nur 10 Ziffern: Peek erkennt sie nicht. Prüfe, ob eine Ziffer fehlt. |
| `RA482917367SG` | Zwei Buchstaben, neun Ziffern und SG am Ende: eine Nummer von Singapore Post, nicht von SPX. |

Leerzeichen und Kleinschreibung spielen beim Einfügen in Peek keine Rolle.

**Die Bestellnummer ist keine Sendungsnummer.** Hat eine eingefügte Nummer das Format einer Shopee-Bestellnummer, meldet Peek „Das sieht nach einer Shopee-Bestellnummer aus“. Kopiere stattdessen die Sendungsnummer aus der Bestellung. Nicht gefunden? Siehe [Sendungsnummer finden](guide:find-tracking-number).

## Statusmeldungen von SPX Express Singapur erklärt

Der SPX-Tracker schreibt jede Zeile auf Englisch. Dies sind die Zeilen, die Peeks Code kennt, in der üblichen Reihenfolge einer Auslandssendung:

| SPX meldet | Bedeutung für dich | Peek zeigt |
| --- | --- | --- |
| `[China]Sender is preparing to ship your parcel` | Der Verkäufer in China bereitet das Paket vor. SPX hat es noch nicht. | „Etikett erstellt“ |
| `[China] Parcel has arrived at Hong Kong sorting centre` | Das Paket ist im Shopee-Sortierzentrum in Hongkong angekommen. | „Unterwegs“ |
| `[China] Parcel has departed from Hong Kong sorting centre` | Es hat das Sortierzentrum verlassen. | „Unterwegs“ |
| `[China] Parcel has been handed over to delivery service provider` | Es wurde an einen Luftfrachtpartner zur Weiterbeförderung übergeben. | „Unterwegs“ |
| `[China] Parcel has cleared export customs` | Die Ausfuhrzollabfertigung ist erledigt – noch nicht der Zoll in Singapur. | „Unterwegs“ |
| `Parcel has departed from the origin port: Hongkong` | Es hat Hongkong verlassen. | „Unterwegs“ |
| `Parcel has arrived at the destination port Singapore` | In Singapur angekommen: eine Ankunftsmeldung, kein Zollstopp. | „Unterwegs“ |
| `Parcel has cleared import customs` | Der Einfuhrzoll in Singapur hat das Paket freigegeben. | „Unterwegs“ |
| `Parcel has been picked up by our local logistics partner for further sorting. You will be notified when the parcel is ready for delivery.` | Es ist nun im lokalen Zustellnetz in Singapur zur weiteren Sortierung vor der Auslieferung. | „Unterwegs“ |
| `[Singapore] Parcel has arrived at sorting facility` | In einem Sortierzentrum in Singapur. | „Unterwegs“ |
| `[SPX Express] Parcel is out for delivery` | Heute in Zustellung. | „In Zustellung“ |
| `Parcel has been delivered` | Zugestellt. | „Zugestellt“ |

**Weitere Schritte.** Peeks Code erkennt weitere SPX-Meldungen: Abholung beim Verkäufer oder Abgabe an einer Annahmestelle („Beim Paketdienst eingegangen“), erfolgloser Zustellversuch („Zustellversuch“) sowie Rücksendung an den Verkäufer („Zurück an Absender“, oder „Problem gemeldet“, wenn die Rücksendung stockt). Mehr unter [Sendungsstatus erklärt](guide:tracking-statuses).

## Zustellung mit SPX Express in Singapur

Nach `[SPX Express] Parcel is out for delivery` sollte als Nächstes `Parcel has been delivered` erscheinen. Liefert SPX das Paket an einen Abholpunkt statt an deine Haustür, zeigt Peek auf dem Weg dorthin „In Zustellung“.

Da Peek die Website von SPX Express Singapore nicht auslesen konnte, nennt diese Seite keine Lieferzeiten, Zustellversuche oder Abholregeln. Frage den Verkäufer oder Shopee, falls du dort bestellt hast. Siehe [verpasste Zustellungen](guide:missed-delivery) und [nicht abgeholte Pakete](guide:uncollected-parcel).

## Pakete aus dem Ausland: GST und Zoll in Singapur

Regeln von Singapore Customs für Auslandskäufe per Luftfracht oder Post:

- **GST beim Checkout.** Kostet jeder einzelne Artikel höchstens 400 S$ und ist der ausländische Verkäufer oder Marktplatz GST-registriert, ist die GST bereits im Preis enthalten. Der Verkäufer übermittelt die Zahlungsdaten an den Kurier, sodass bei der Einfuhr keine zweite GST anfällt. Der Betrag bezieht sich nur auf den Warenwert ohne Versand und Versicherung.
- **GST bei der Einfuhr.** Ist der Verkäufer nicht GST-registriert und übersteigt der gesamte CIF-Wert der Sendung (Warenwert, Versicherung und Fracht) 400 S$, wird die GST bei der Einfuhr erhoben. Bis 400 S$ fällt keine Einfuhr-GST an, außer bei Alkohol und Tabak.
- **Doppelt berechnet?** Hast du GST bereits beim Checkout bezahlt und wirst bei der Einfuhr erneut zur Zahlung aufgefordert, zahle zunächst die Einfuhr-GST, um die Ware zu erhalten, und verlange dann mit Zahlungsnachweis eine Erstattung vom Verkäufer.

**Beim Zoll festgehalten.** Die Immigration & Checkpoints Authority (ICA) kann Kurierpakete mit zollpflichtigen, kontrollierten oder verbotenen Waren sowie Sendungen über 400 S$ CIF ohne beigefügte Rechnung einbehalten. Die ICA stellt dem Kurier dann eine Einlagerungsquittung aus; für Rückfragen lässt du dir vom Kurier die Sendungsdaten und diese Quittung geben und reichst sie bei der auf der Quittung genannten Behörde ein.

**Betrug.** Singapore Customs weist darauf hin, dass keine GST-Zahlungen oder persönlichen Daten per SMS, E-Mail oder Messenger angefordert werden. Siehe [Betrugs-SMS](guide:delivery-scam-texts) und [Paket beim Zoll](guide:customs).

## SPX-Paket ohne Updates, verloren oder zurückgesendet

**Nummer noch nicht gefunden.** Kennt SPX eine SPXSG-Nummer noch nicht, zeigt Peek „Warten auf SPX Express Singapore“ und prüft weiter. Möglicherweise hat der Verkäufer das Paket noch nicht übergeben; prüfe auch die Nummer.

**Steht bei `[China]Sender is preparing to ship your parcel`.** Der Verkäufer hat es noch nicht versendet. Frage dort nach, wann es rausgeht.

**Tagelang keine Aktualisierung.** Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Nach vier Tagen ohne neuen Scan bei einem Paket auf „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“ markiert Peek das Paket mit „Keine Aktualisierung seit …“ und fragt weiter bei SPX nach. Siehe [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating) und [Pakete aus China verfolgen](guide:tracking-from-china).

**Als zugestellt markiert, aber nichts angekommen.** Schau rund um die Haustür, frage Mitbewohner und Nachbarn und wende dich dann an den Verkäufer oder Shopee. Siehe [zugestellt, aber nicht erhalten](guide:delivered-not-received).

**Verloren oder zurückgesendet.** Der Verkäufer hat den Versand gebucht; kläre Erstattung oder Ersatzlieferung mit ihm oder über Shopee. Siehe [verlorenes Paket](guide:lost-parcel) und [zurück an Absender](guide:returned-to-sender).

## SPX Express Singapur Kundenservice und Kontakt

Da Peek die Website von SPX Express Singapore nicht auslesen konnte, nennt diese Seite weder Telefonnummer noch E-Mail-Adresse: Peek gibt nur Kontakte wieder, die direkt auf den eigenen Seiten eines Paketdienstes stehen.

Bei Shopee-Bestellungen wendest du dich an Shopee; bei einem anderen Online-Shop zuerst an den Shop als Auftraggeber. Singapore Customs kann nicht helfen, da die Behörde keinen Zugriff auf Kurierdaten hat und an den Kurier verweist.

## SPX-Express-Singapur-Paket mit Peek verfolgen

[Peek](/) verfolgt ein SPX-Paket allein mit der Nummer – ohne Konto, Telefonnummer oder Postleitzahl.

- **Eine SPXSG-Nummer.** Peek erkennt SPX Express Singapore sofort („Erkannter Paketdienst“) und fragt direkt den SPX-Tracker ab. Über „Ändern“ kannst du einen anderen Dienst wählen.
- **Ein Link.** Füge einen Link zum SPX-Tracker auf spx.sg mit SPXSG-Nummer (`/track?`, `/m/track?` oder `/detail/`) ein; Peek liest die Nummer heraus.
- **Eine SG-Nummer.** An diesem Format allein kann Peek den Dienst nicht sicher erkennen. Füge sie trotzdem ein: Peek unterstützt über 4.500 Paketdienste. Während der Prüfung erscheint „Tracking-Dienste werden geprüft…“. Über „Ändern“ findest du SPX Express Singapore unter „Passt zu dieser Nummer“. Noch besser ist die SPXSG-Nummer, da der SPX-Tracker Shopee-Pakete oft nur darüber findet.

Ohne festgelegten Dienst zeigt ein Paket „Noch kein Sendungsverlauf“, während Peek Tracking-Dienste abfragt. Nennt einer davon allein SPX Express Singapore mit datierten Schritten, ordnet Peek das Paket SPX zu.

**Was Peek anzeigt.** Status, jede Zeile im Wortlaut von SPX sowie den Ort bei Scans in Lagern, Häfen und Sortierzentren. SPX liefert den genauen Zeitpunkt jedes Scans, sodass Peek ihn in deiner Zeitzone darstellt („Uhrzeiten in deiner Zeitzone:“). Peek behält auch einzelne Schritte, die SPXs eigene Seite ausblendet, etwa Einfuhrzollabfertigung und laufende Rücksendungen. Verkäuferstandort, Büroadressen und den Namen der annehmenden Person lässt Peek weg. Da SPX weder Liefertermin noch Gewicht oder Abholpunkt nennt, zeigt Peek dazu nichts an.

**Von Janio zu SPX.** Nennt ein Janio-Paket in seinem Tracking SPX mit einer SPX-Singapur-Nummer, zeigt die Paketkarte „Zustellung durch SPX Express Singapore“ und Peek fragt auch SPX ab. Hat SPX bisher nur die Ankündigung ohne eigenen Scan, steht stattdessen „Bei SPX Express Singapore angekündigt, noch nicht eingetroffen“. Sobald SPX einen mindestens ebenso aktuellen Scan meldet wie Janio, folgt Peek dem Paket dort weiter und behält den Janio-Verlauf.

**Prüfungen und Mitteilungen.** Peek fragt SPX direkt bis zu alle 10 Minuten ab – und ab 6:00 Uhr Singapur-Zeit alle 2 Minuten an dem Tag, an dem SPX das Paket in Zustellung scannt, solange das Paket in Peek geöffnet ist oder am Liefertag mit aktiven Mitteilungen. Nach zwei Tagen ohne Neuigkeiten (nach 12 Stunden, solange das Paket noch auf „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ steht) prüft Peek stündlich; nach sieben Tagen ohne Neuigkeiten bei „Etikett erstellt“ alle 6 Stunden. Für Mitteilungen wählst du „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“. Nach vier Tagen ohne Scan bei einem Paket auf „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“ zeigt Peek „Keine Aktualisierung seit …“, erklärt „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei SPX Express Singapore nach.“ und bietet „Hol dir Hilfe bei SPX Express Singapore“. Peek kann weder SPX noch Shopee oder den Verkäufer für dich kontaktieren.

## Häufige Fragen zu SPX Express Singapur

### Ist SPX Express dasselbe wie Shopee Express?

Ja. Peeks Katalog führt SPX Express Singapore auch als Shopee Xpress Singapore und Shopee Express Singapore; das Netz liefert Shopee-Bestellungen aus. In anderen Ländern betreibt SPX eigene Netze wie [SPX Express Brasilien](carrier:spx-br): eine SPXSG-Nummer gehört zu Singapur.

### Worin unterscheiden sich eine SPXSG-Nummer und eine SG-Nummer?

Die SPXSG-Nummer ist SPXs eigene Sendungsnummer – Peek erkennt SPX damit sofort. Eine SG-Nummer ist die zugehörige Shopee-Nummer. Da SPXs Tracker Shopee-Pakete oft nur über die SPXSG-Nummer findet, verwende besser diese.

### Was bedeutet „Parcel has been picked up by our local logistics partner“?

Dein Paket aus dem Ausland ist in Singapur angekommen und zur weiteren Sortierung an das lokale Zustellnetz übergeben worden. Laut SPX wirst du informiert, sobald es bereit zur Zustellung ist. Peek zeigt „Unterwegs“.

### Warum aktualisiert sich mein SPX-Tracking nicht?

Lautet die letzte Zeile `[China]Sender is preparing to ship your parcel`, hat der Verkäufer noch nicht versendet. Später pausieren Updates oft bei Flügen, beim Zoll oder am Wochenende. Nach vier Tagen ohne Neuigkeiten auf „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“ markiert Peek die Sendung; bleibt sie still, frage Verkäufer oder Shopee.

:::sources
- [Singapore Customs: GST on Low-Value Goods](https://www.customs.gov.sg/personal-shipment/buying-from-overseas/gst-on-low-value-goods/) – 400-S$-Regel, GST beim Checkout oder bei Einfuhr, doppelte Berechnung, Betrug
- [Singapore Customs: Courier / Postal Procedures](https://www.customs.gov.sg/personal-shipment/buying-from-overseas/courier-postal-procedures/) – von ICA einbehaltene Pakete, Verweis an den Kurier
- [Universal Parcel Scraper: SPX Express Singapore](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/spx-sg) – Namen, Nummernformate, Tracker-Links, Wortlaute und Statusstufen
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Einordnung jedes Scans in eine Statusstufe, Tracking-Dienste
:::
