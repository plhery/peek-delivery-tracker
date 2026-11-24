---
title: PDU Sendungsverfolgung: Parcel Delivery Union & Hilfe
description: Verfolge ein grenzüberschreitendes PDU-Paket (Parcel Delivery Union / 友递联): Frachtbrief-Formate, Transit- und Zustellstatus, Zoll und Kontakte.
slug: pdu-sendungsverfolgung
published: 2026-11-24
updated: 2026-11-24
---

**PDU** (*Parcel Delivery Union*, betrieben von *Shanghai Youdilian Technology Co., Ltd.*, chinesisch *友递联科技*) ist ein 2022 gegründeter grenzüberschreitender Logistikanbieter mit Hauptsitz in Shanghai ([pduni.com](https://www.pduni.com/)). Mit eigenen Niederlassungen und lokalen Betriebs-Hubs in über 10 Ländern in Nordamerika, Europa, Afrika und Asien bietet PDU E-Commerce-Händlern und Herstellern durchgängige Zollabfertigung, Abholung, lokale Zustellung im Ausland sowie Nachnahme-Inkasso (COD). Du kannst eine Sendung im englischen Frachtbriefportal unter [pduni.com/MV/en/customer-services/waybill-inquiry](https://www.pduni.com/MV/en/customer-services/waybill-inquiry) prüfen oder deine Sendungsnummer oben einfügen.

## PDU-Sendungsnummern (`PD`-Endung und eigene Frachtbriefe)

Viele grenzüberschreitende PDU-Pakete tragen einen 12-stelligen Code aus zwei Buchstaben, 8 Ziffern und `PD`:

| Format (erfunden) | Worum es sich handelt |
| --- | --- |
| `MX48291736PD` | 12-stelliger grenzüberschreitender PDU-Frachtbrief (`MX` + 8 Ziffern + `PD`). |
| `CO73918462PD` | Alternatives 12-stelliges `..PD`-Frachtbriefformat auf internationalen Routen. |

Das öffentliche Abfrageportal von PDU akzeptiert auch andere alphanumerische Frachtbriefe mit 6 bis 40 Zeichen, wenn du PDU manuell wählst. Dasselbe Muster passt zu Nummern von [Colis Privé](carrier:colis-prive): Peek fragt zuerst Colis Privé und dann PDU an, in China jedoch PDU zuerst. Siehe [Sendungsnummern-Formate](guide:tracking-number-formats).

## Was die PDU-Sendungsstatus bedeuten

PDU versieht jede Sendung und jeden Scan mit einem Gesamtstatuscode (`TRANSIT`, `COLLECTING`, `DELIVERY`, `SIGNED` usw.) zusammen mit englischen oder landessprachlichen Scantexten (auf Lateinamerika-Strecken häufig Spanisch, oft mit einem kurzen lokalen Kurierpräfix wie `ENT,Entregado`, das Peek zur Anzeige bereinigt):

| PDU-Status oder Scantext | Was das für dich bedeutet | Peek zeigt |
| --- | --- | --- |
| `Shipment information sent to PDU.`, `Package not found in last mile or customs process.` | Elektronischer Auftrag bei PDU eingegangen, noch vor physischer Annahme oder erstem Eintrag beim lokalen Zustellnetz. | „Etikett erstellt“ |
| `COLLECTING` (`Being Collected`), `FINAL_COLLECTING` (`Last-Mile Collection`), `TRANSIT` (`In Transit`) | Am Ausgangsort abgeholt oder unterwegs im internationalen Transit und in regionalen Verteilzentren. | „Unterwegs“ |
| `Recogida en Aduanas`, `Ingreso a CEDI`, `En tránsito nacional` | Vom Zoll abgeholt, im nationalen Verteilzentrum (CEDI) eingegangen oder im Inland unterwegs. | „Unterwegs“ |
| `DELIVERY` (ohne Text zum Tourenstart) | Ins Netz des lokalen Zielpaketdienstes übergeben; Peek belässt dies als Transit, bis die eigentliche Zustelltour beginnt. | „Unterwegs“ |
| `Salida a Ruta` | Heute mit dem lokalen Kurier auf Zustelltour. | „In Zustellung“ |
| `DELIVERY_EXCEPTION` (`Delivery Exception`), `TO_BE_RETURN` (`Awaiting Return`) | Zustellproblem gemeldet oder Paket wartet auf Rücksendung an den Absender; kontaktiere Verkäufer oder lokalen Kurier. | „Problem gemeldet“ |
| `RETURNED` (`Returned`) | Auf dem Rückweg zum Absender oder Ausgangslager. | „Zurück an Absender“ |
| `SIGNED` (`Delivered`), `Entregado` | An der Empfängeradresse zugestellt und quittiert. | „Zugestellt“ |

Beachte, dass die frühe PDU-Meldung `Package not found in last mile or customs process.` lediglich bedeutet, dass der lokale Zustellpartner das Paket noch nicht gescannt hat — sie bedeutet **nicht**, dass der Zoll dein Paket zurückhält.

## Zollabfertigung, lokale Zustellung im Ausland und Nachnahme

Laut [pduni.com](https://www.pduni.com/) und dem [PDU Help Center](https://www.pduni.com/MV/en/customer-services/help-center):

- **Eigene Verzollung & KI-HS-Zuordnung:** PDU betreibt ein eigenes Zollabfertigungsnetz in über 10 Ländern mit automatisierter HS-Code-Zuordnung und Vorauszahlung von Abgaben; laut Help-Center-FAQ berechnet PDU keine gesonderte Gebühr für formelle Exportzollanmeldungen, und Händler können fehlende Zolldokumente online nachreichen.
- **Lokale Zustellung:** Nach Freigabe durch den Einfuhrzoll (`Recogida en Aduanas` / `Ingreso a CEDI`) übernehmen lokale Niederlassungen oder Partnerkuriere die Haustürzustellung und bei Bedarf den Nachnahmeeinzug (COD).
- **Einfuhrsteuern & Zölle:** Einfuhrumsatzsteuer oder Zollgebühren im Zielland richten sich nach lokalen Freibeträgen und danach, ob der Händler Abgaben beim Kauf erhoben hat. Siehe [Pakete beim Zoll](guide:customs).

## PDU-Paket hängt, bleibt still oder fehlt

Da Onlinehändler und Hersteller das Versandkonto bei PDU halten, **wende dich zuerst immer an deinen Verkäufer oder Marktplatz** für Adresskorrekturen, Erstattungen oder Ersatzlieferungen.

- **Sendepausen zwischen China und lokalem Eingang:** Scans pausieren oft mehrere Tage zwischen `Shipment information sent to PDU.` (oder `Package not found in last mile or customs process.`) und `Recogida en Aduanas` / `Ingreso a CEDI`, während Luft- oder Seefrachtkonsolidierungen das Zielland erreichen. Siehe [warum die Sendungsverfolgung nicht aktualisiert](guide:tracking-not-updating).
- **`DELIVERY_EXCEPTION` oder `TO_BE_RETURN`:** Handle rasch — überprüfe Lieferadresse und Telefonnummer mit dem Verkäufer, damit das lokale Depot einen neuen Versuch ansetzen kann, bevor der Status auf `RETURNED` wechselt.
- **Als zugestellt markiert oder beschädigt:** Prüfe zuerst bei Haushaltsmitgliedern, Gebäudeempfang und Nachbarn und reiche dann vor Ablauf des Käuferschutzes eine Reklamation beim Händler ein. Siehe [zugestellt, aber nicht erhalten](guide:delivered-not-received), [verlorenes Paket](guide:lost-parcel), [beschädigtes Paket](guide:damaged-parcel) und [zurück an Absender](guide:returned-to-sender).

## PDU Kundenservice und Kontakte in Shanghai

Offizielle Kontaktdaten auf [PDU Waybill Inquiry](https://www.pduni.com/MV/en/customer-services/waybill-inquiry) und [PDU Help Center](https://www.pduni.com/MV/en/customer-services/help-center):

- **Unternehmen:** *Shanghai Youdilian Technology Co., Ltd.* (*友递联科技*, ICP `沪ICP备2022026205号`).
- **Telefon-Hotline:** `021-50837888` (`+86 21 5083 7888`).
- **E-Mail:** `service@pduni.com`.
- **Hauptsitz:** Room 305, 6F, Zhonghai Building, Zhenbei Road, Putuo District, Shanghai, China.
- **Online-Tracker & Hilfe:** [pduni.com/MV/en/customer-services/waybill-inquiry](https://www.pduni.com/MV/en/customer-services/waybill-inquiry) und [pduni.com/MV/en/customer-services/help-center](https://www.pduni.com/MV/en/customer-services/help-center).

> Das PDU Help Center warnt ausdrücklich vor Betrugsversuchen, die Logo oder Kontaktdaten von PDU missbrauchen. Bezahle Zoll- oder Liefergebühren niemals über einen ungeprüften SMS-Link — kläre jede Forderung zuerst über den Händler oder das offizielle Portal des Paketdienstes. Siehe [Betrugs-SMS zu Paketlieferungen](guide:delivery-scam-texts).

## Ein PDU-Paket mit Peek verfolgen

Gibst du einen 12-stelligen `..PD`-Code (`MX48291736PD` oder `CO73918462PD`, erfunden) ein, fragt [Peek](/) nacheinander Colis Privé und PDU an (in China PDU zuerst) und zeigt „PDU hat dieses Paket.“, sobald der PDU-Tracker es kennt. Nutzt deine PDU-Sendungsnummer ein anderes 6- bis 40-stelliges Format, tippe beim Hinzufügen auf „Ändern“ oder auf der Paketseite auf „Paketdienst ändern“ und wähle **PDU** (auch die Suche nach „Parcel Delivery Union“ oder „友递联“ findet ihn).

Peek liest den PDU-Tracker aus und zeigt die Phase („Etikett erstellt“, „Unterwegs“, „In Zustellung“, „Problem gemeldet“, „Zurück an Absender“, „Zugestellt“), den Scantext ohne das vorangestellte Kürzel des lokalen Zustellers (`Entregado` statt `ENT,Entregado`), den Ort und die Zustellzeit an. Die PDU-Statuscodes bestimmen die Phase, bleiben aber in jeder Peek-App ausgeblendet. PDU nennt jede Scan-Zeit mit UTC-Versatz oder andernfalls in Pekinger Zeit, und Peek stellt sie auf der Uhr deines Geräts dar. Der PDU-Tracker enthält kein voraussichtliches Lieferdatum.

Solange du das Paket in Peek geöffnet hast oder am Zustelltag Benachrichtigungen aktiviert sind, fragt Peek bis zu alle 10 Minuten nach; an dem Tag, an dem PDU `Salida a Ruta` meldet, ab 6 Uhr auf der Uhr des neuesten Scans alle 2 Minuten, solange der PDU-Tracker antwortet. Andernfalls prüft Peek stündlich. Nach 12 Stunden mit lediglich „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ oder nach zwei Tagen ohne Neuigkeiten fragt Peek unabhängig von deiner Nutzung stündlich nach, und nach sieben Tagen auf „Etikett erstellt“ alle 6 Stunden. Antwortet der PDU-Tracker nicht und Peek verfolgt das Paket allein über Tracking-Dienste, prüft Peek stündlich, nach einem ruhigen Tag alle 3 Stunden und am Zustelltag bis zu alle 15 Minuten, solange du die Sendung geöffnet hast oder Benachrichtigungen aktiviert sind.

Ist der neueste Scan vier Tage alt und das Paket steht noch auf „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“, markiert Peek es mit dem Tag dieses Scans, etwa „Keine Aktualisierung seit Mo., 12. Okt.“. Auf der Paketseite steht dann „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei PDU nach.“ Nach drei Wochen ohne neuen Scan heißt es „Seit über drei Wochen nichts Neues. Ist das Paket noch nicht angekommen, frag beim Verkäufer nach. Peek fragt weiter bei PDU nach.“

Bei den Benachrichtigungen kannst du zwischen „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“ wählen. Peek beobachtet ausschließlich Tracking-Updates und kann weder Sendungen verzollen noch deine Lieferadresse ändern oder PDU für dich kontaktieren.

## Häufige Fragen zur PDU-Sendungsverfolgung

### Was ist PDU (*Parcel Delivery Union*)?

PDU (*Shanghai Youdilian Technology Co., Ltd.*, *友递联科技*) ist ein 2022 gegründeter grenzüberschreitender Logistikdienstleister aus Shanghai mit internationaler Zollabfertigung, Abholung, lokaler Zustellung und Nachnahme in über 10 Ländern.

### Wie sieht eine PDU-Sendungsnummer aus?

Viele grenzüberschreitende PDU-Frachtbriefe sind 12 Zeichen lang: zwei Buchstaben, 8 Ziffern und `PD` (zum Beispiel `MX48291736PD`, erfunden), das PDU-Portal verfolgt aber auch andere alphanumerische Codes mit 6 bis 40 Zeichen.

### Bedeutet `Package not found in last mile or customs process.`, dass mein Paket beim Zoll festhängt?

Nein. Im PDU-Tracker zeigt diese frühe Meldung nur, dass die elektronischen Sendungsdaten vorliegen, das Paket aber noch keinen ersten physischen Scan im lokalen Zustell- oder Zollsystem erhalten hat.

### Wie erreiche ich den PDU-Kundenservice?

Wende dich für Erstattungen oder Lieferänderungen zuerst an deinen Onlinehändler. Die auf [pduni.com/MV/en/customer-services/help-center](https://www.pduni.com/MV/en/customer-services/help-center) veröffentlichten Support-Kontakte von PDU in Shanghai sind `021-50837888` und `service@pduni.com`.

:::sources
- [PDU: Offizielle Website](https://www.pduni.com/) – 2022 im Bezirk Putuo in Shanghai gegründet, eigenes Verzollungs- und Zustellnetz in 10+ Ländern, COD
- [PDU: Frachtbrief-Tracking-Portal](https://www.pduni.com/MV/en/customer-services/waybill-inquiry) – öffentliche englische Frachtbriefsuche, `service@pduni.com` und `021-50837888`
- [PDU: Help Center & FAQ](https://www.pduni.com/MV/en/customer-services/help-center) – Zoll-FAQ, Betrugswarnung und Adresse des Hauptsitzes in Shanghai (*Shanghai Youdilian Technology Co., Ltd.*)
- [Universal Parcel Scraper: pdu](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pdu) – Erkennungsregel `^[A-Z]{2}\d{8}PD$`, API `traceByWeb`, Status-/Textzuordnung (`DELIVERY` vs. `Salida a Ruta`), Präfixbereinigung und UTC-/Pekinger Zeitverarbeitung
:::
