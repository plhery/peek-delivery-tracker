---
title: Onlog Express Sendungsverfolgung: AA-Codes & Hilfe
description: Verfolge ein Onlog-Express-Paket in Brasilien: 12-stellige AA-Codes, Verteilzentrum-Status, Gebührenbetrug-Warnung und Support in São Paulo.
slug: onlog-express-sendungsverfolgung
published: 2026-11-24
updated: 2026-11-24
---

**Onlog Express** (*Onlog Franqueadora*) ist ein brasilianisches Paket- und Logistiknetz mit Sitz in São Paulo, über 20 Jahren Erfahrung in Logistikpartnerschaften und mehr als 18 Millionen zugestellten Paketen in ganz Brasilien ([onlogexpress.com.br](https://onlogexpress.com.br/)). Das Netz verbindet Onlinehändler und Unternehmen mit landesweiten Zustelldiensten über Stufen von sehr schnellem Versand und taggleicher Zustellung (*Entrega em Minutos*, *Entrega no Dia*) über Priorität (*Expresso D1*) bis Wirtschaft (*Econômico D2*) samt Fulfillment und internationaler Fracht; öffentliches Tracking per Objektcode oder CPF/CNPJ bietet [conecta.log.br/rastreio.php](https://conecta.log.br/rastreio.php). Du kannst eine Sendung dort prüfen oder deinen 12-stelligen `AA`-Code oben einfügen.

## Onlog-Express-Sendungsnummern (Format `AA`)

Die öffentliche Sendungsverfolgung von Onlog Express nutzt einen 12-stelligen Objektcode, beginnend mit `AA` und gefolgt von 10 Ziffern:

| Format (erfunden) | Worum es sich handelt |
| --- | --- |
| `AA4829173605` | 12-stelliger Objektcode von Onlog Express (`AA` + 10 Ziffern). |
| `AA7391846208` | Alternative 12-stellige `AA`-Paketreferenz von Onlog Express. |

Schau in die Versand-E-Mail deines Händlers oder in den Tracking-Link `onlog.app.br/rastreio/...` nach der `AA`-Referenz. Hast du den Code nicht, erlaubt das Onlog-Webportal unter [conecta.log.br/rastreio.php](https://conecta.log.br/rastreio.php) auch die Suche mit brasilianischer CPF oder CNPJ (sowie eine separate Abfrage für Sendungen vor dem 01.01.2025). Siehe [Sendungsnummern-Formate](guide:tracking-number-formats).

## Was die Sendungsstatus von Onlog Express bedeuten

Onlog Express veröffentlicht portugiesische Scan-Texte von der Etikettenerstellung und Abholung über Transfers in Verteilzentren bis zur Empfängerunterschrift:

| Onlog Express meldet (PT-BR) | Was das für dich bedeutet | Peek zeigt |
| --- | --- | --- |
| `OBJETO CRIADO`, `ETIQUETA EMITIDA` | Sendung angelegt und Etikett elektronisch vor der physischen Abholung erstellt. | „Etikett erstellt“ |
| `OBJETO POSTADO`, `COLETA DE ENCOMENDA` | Paket in einer Onlog-Stelle eingeliefert oder beim Absender abgeholt. | „Beim Paketdienst eingegangen“ |
| `OBJETO EM TRANSFERÊNCIA`, `ENTRADA NO CENTRO DE DISTRIBUIÇÃO`, `ENCAMINHADO PARA O OPERADOR LOGÍSTICO`, `BIPE DE EXPEDIÇÃO`, `BIPE DE RECEBIMENTO` | Im Verteilzentrum eingetroffen, zwischen Standorten weitergeleitet oder an den regionalen Logistikbetreiber übergeben. | „Unterwegs“ |
| `OBJETO SAIU PARA ENTREGA AO DESTINATÁRIO`, `BIPE DE SAÍDA PARA ENTREGA` | Heute mit dem lokalen Zusteller zur Auslieferung an deine Adresse unterwegs. | „In Zustellung“ |
| `OBJETO ENTREGUE AO DESTINATÁRIO`, `ASSINATURA DE ENCOMENDA` | An der Zieladresse zugestellt und quittiert. | „Zugestellt“ |

`ENCAMINHADO PARA O OPERADOR LOGÍSTICO` bedeutet, dass das Paket an den Logistikbetreiber weitergegeben wurde, der den nächsten Abschnitt übernimmt.

## Lieferdienste und offizielle Warnung vor Gebührenbetrug

Laut [onlogexpress.com.br](https://onlogexpress.com.br/):

- **Servicestufen:** **Entrega em Minutos** (dringend), **Entrega no Dia** (am selben Tag), **Expresso D1** (Priorität) und **Econômico D2** (wirtschaftlich); genaue Laufzeiten und Verfügbarkeit hängen von Ausgangs- und Zielpostleitzahlen (CEPs) ab.
- **Wichtiger Hinweis zu Betrugsversuchen:** Onlog weist auf seiner Startseite ausdrücklich darauf hin, dass **Onlog keine Gebührenzahlungen per E-Mail, WhatsApp oder Telefon anfordert**. Bezahle niemals eine Steuer oder Freigabegebühr aus unaufgeforderten Nachrichten im Namen von Onlog. Siehe [Betrugs-SMS zu Paketlieferungen](guide:delivery-scam-texts).

## Onlog-Express-Paket verspätet, still oder vermisst

- **Sendepause nach `ENCAMINHADO PARA O OPERADOR LOGÍSTICO`:** Scans pausieren häufig zwischen regionalen Hubs oder bei der Übergabe an den lokalen Zustellpartner über brasilianische Bundesstaaten hinweg. Siehe [warum die Sendungsverfolgung nicht aktualisiert](guide:tracking-not-updating).
- **Als zugestellt markiert ohne Beleg oder vermisst:** Frage zuerst beim Pförtner (*portaria*), in der Familie und bei Nachbarn nach, kontaktiere dann den Onlineshop und wende dich während der Geschäftszeiten mit deiner `AA`-Nummer an den Onlog-Support in São Paulo. Siehe [zugestellt, aber nicht erhalten](guide:delivered-not-received), [verlorenes Paket](guide:lost-parcel), [beschädigtes Paket](guide:damaged-parcel) und [zurück an Absender](guide:returned-to-sender).

## Onlog Express Kundenservice und Kontakte in São Paulo

Offizielle Kontaktkanäle auf [onlogexpress.com.br](https://onlogexpress.com.br/):

- **Telefon (São Paulo):** `(11) 5662-8108` und `(11) 5663-3829`.
- **E-Mail:** `atendimento@onlogexpress.com.br`.
- **Servicezeiten:** Montag bis Freitag, `08:00` bis `18:00` Uhr BRT (samstags und sonntags geschlossen).
- **Hauptsitz (*Matriz São Paulo*):** Rua Estados Unidos, 2031, Jardim América, São Paulo – SP, Brasilien.
- **Online-Tracker:** [conecta.log.br/rastreio.php](https://conecta.log.br/rastreio.php).

> Beachte die offizielle Warnung von Onlog: Onlog erhebt niemals Freigabegebühren per E-Mail, WhatsApp oder Telefon. Ignoriere und melde jede Nachricht, die eine PIX- oder Kartenzahlung zur Freigabe eines Pakets fordert. Siehe [Betrugs-SMS zu Paketlieferungen](guide:delivery-scam-texts).

## Ein Onlog-Express-Paket mit Peek verfolgen

Gibst du einen 12-stelligen `AA`-Code (`AA4829173605` oder `AA7391846208`, erfunden) ein, fragt [Peek](/) zuerst [Colis Privé](carrier:colis-prive), [Aramex Australien](carrier:aramex-au) und [Aramex Neuseeland](carrier:aramex-nz), dann Tracking-Dienste und anschließend Onlog Express ab, dessen Tracker Peek im Browser öffnet, und zeigt „Onlog Express hat dieses Paket.“, sobald Onlog es bestätigt. Eingefügte Links im Format `onlog.app.br/rastreio/AA...` erkennt Peek ebenfalls als Onlog. Musst du Onlog selbst auswählen, tippe beim Hinzufügen auf „Ändern“ oder auf der Paketseite auf „Paketdienst ändern“ (auch die Suche nach „ONLOG“ oder „Onlog Franqueadora“ findet ihn).

Peek liest den Onlog-Tracker in einem Browser aus und zeigt die Phase („Etikett erstellt“, „Beim Paketdienst eingegangen“, „Unterwegs“, „In Zustellung“, „Zugestellt“), den portugiesischen Scantext und den Ort an. Onlog gibt jede Scan-Zeit minutengenau ohne UTC-Versatz aus: Peek ordnet sie der Zeitzone ihres Orts zu (São Paulo für einen Scan in São Paulo, Manaus für Manaus), einen Scan ohne Ort der Zeitzone des Nachbarscans oder sonst der Zeit von São Paulo, und zeigt alle auf der Uhr deines Geräts an. Der Onlog-Tracker liefert keinen voraussichtlichen Liefertag, kein Gewicht, keine Ereigniscodes und keine separate Zustellzeit.

Solange du das Paket in Peek geöffnet hast oder am Zustelltag Benachrichtigungen aktiviert sind, prüft Peek bis zu alle 30 Minuten — der kürzeste Abstand, den Onlog zwischen zwei Abfragen zulässt — und eine Stunde nach einer fehlgeschlagenen Abfrage; am Zustelltag erfolgen keine 2-Minuten-Abfragen. Andernfalls prüft Peek stündlich. Nach 12 Stunden mit lediglich „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ oder nach zwei Tagen ohne Neuigkeiten fragt Peek unabhängig von deiner Nutzung stündlich nach, und nach sieben Tagen auf „Etikett erstellt“ alle 6 Stunden. Antwortet der Onlog-Tracker nicht und Peek verfolgt das Paket allein über Tracking-Dienste, prüft Peek stündlich, nach einem ruhigen Tag alle 3 Stunden und am Zustelltag bis zu alle 30 Minuten, solange du die Sendung geöffnet hast oder Benachrichtigungen aktiviert sind.

Ist der neueste Scan vier Tage alt und das Paket steht noch auf „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“, markiert Peek es mit dem Tag dieses Scans, etwa „Keine Aktualisierung seit Mo., 12. Okt.“. Auf der Paketseite steht dann „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei Onlog Express nach.“ Nach drei Wochen ohne neuen Scan heißt es „Seit über drei Wochen nichts Neues. Ist das Paket noch nicht angekommen, frag beim Verkäufer nach. Peek fragt weiter bei Onlog Express nach.“

Bei den Benachrichtigungen kannst du zwischen „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“ wählen. Peek beobachtet ausschließlich Tracking-Updates und kann weder Sendungen per CPF/CNPJ suchen noch Lieferadressen ändern oder Onlog Express für dich kontaktieren.

## Häufige Fragen zur Sendungsverfolgung von Onlog Express

### Wie sieht eine Sendungsnummer von Onlog Express aus?

Sendungsnummern von Onlog Express beginnen mit `AA`, gefolgt von 10 Ziffern (insgesamt 12 Zeichen, zum Beispiel `AA4829173605`, erfunden).

### Kann ich ein Onlog-Express-Paket mit meiner CPF oder CNPJ verfolgen?

Ja, auf der Website von Onlog unter [conecta.log.br/rastreio.php](https://conecta.log.br/rastreio.php) kannst du zum Reiter **POR CPF/CNPJ** wechseln, wenn dir der `AA`-Code fehlt. Peek arbeitet ausschließlich mit dem `AA`-Objektcode.

### Ich habe eine WhatsApp oder E-Mail mit Gebührenforderung von Onlog erhalten — ist das echt?

Nein. Onlog erklärt auf [onlogexpress.com.br](https://onlogexpress.com.br/) ausdrücklich, dass **niemals** Gebührenzahlungen per E-Mail, WhatsApp oder Telefon angefordert werden. Bezahle nicht.

### Wie erreiche ich den Kundenservice von Onlog Express?

Rufe `(11) 5662-8108` oder `(11) 5663-3829` an oder schreibe montags bis freitags von 08:00 bis 18:00 Uhr BRT an `atendimento@onlogexpress.com.br`.

:::sources
- [Onlog Express: Offizielle Website](https://onlogexpress.com.br/) – Servicestufen (*Entrega em Minutos*, *Entrega no Dia*, *Expresso D1*, *Econômico D2*), 18 Mio.+ Pakete, 20+ Jahre, Betrugswarnung und Kontakte der Zentrale São Paulo
- [Onlog Express: Offizieller Tracker (Conecta)](https://conecta.log.br/rastreio.php) – öffentliches Tracking per Objektcode oder CPF/CNPJ
- [Universal Parcel Scraper: onlog-express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/onlog-express) – Format `AA\d{10}`, Link-Regel `onlog.app.br/rastreio/`, 13-Phrasen-Statustabelle auf Portugiesisch, Browser-Abfrage höchstens alle 30 Minuten (60 nach Fehler) und Scan-Zeitzonen
:::
