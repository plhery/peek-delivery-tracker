---
title: Correos de México Sendungsverfolgung: S10-MX-Codes & Hilfe
description: Verfolge ein Paket von Correos de México (SEPOMEX / Mexpost): S10-MX-Codes, Jahresauswahl, spanische Status, Zoll, 30-Tage-Reklamation & Kontakt.
slug: correos-de-mexico-sendungsverfolgung
published: 2026-11-24
updated: 2026-11-24
---

Correos de México (*Servicio Postal Mexicano* / *SEPOMEX*) ist die staatliche Post Mexikos für nationale Einschreiben (*Registrados*), *Mexpost*-Expresspakete sowie internationale UPU-Postsendungen aus Mexiko und aus dem Ausland nach Mexiko. Du kannst eine Sendung auf der [Trackingseite von Correos de México](https://www.correosdemexico.gob.mx/SSLServicios/SeguimientoEnvio/Seguimiento.aspx) prüfen (unter Angabe von Sendungsnummer und Einlieferungsjahr, *Ejercicio*) oder deinen Code oben einfügen.

## Sendungsnummern von Correos de México: UPU-S10-Formate

Schau auf deine mexikanische Postquittung, deinen Mexpost-Frachtbrief oder die Versandbestätigung des ausländischen Verkäufers. Correos de México verfolgt 13-stellige UPU-S10-Codes — sowohl abgehende Codes mit der Endung `MX` als auch eingehende ausländische S10-Sendungen nach Mexiko (etwa `...US`, `...CN`, `...ES` oder `...JP`):

| Format (erfunden) | Worum es sich handelt |
| --- | --- |
| `RR482917367MX` | Einschreiben (*Correo Registrado*) aus Mexiko (UPU S10 mit Endung `MX`). |
| `MP482917367MX` | *Mexpost*-Inlands- oder Expresspaket (UPU S10 mit Endung `MX`). |
| `EE482917367MX` | Internationaler EMS-Expressversand aus Mexiko (UPU S10 mit Endung `MX`). |

**Jahresauswahl (*Ejercicio*) bei SEPOMEX.** Anders als die meisten Tracker speichert das [Seguimiento-Portal von Correos de México](https://www.correosdemexico.gob.mx/SSLServicios/SeguimientoEnvio/Seguimiento.aspx) Scans nach Kalenderjahr (`2026`, `2025`, `2024` usw.). Läuft ein Ende Dezember eingeliefertes Paket bis in den Januar, musst du auf der Website mitunter beide Jahre abfragen, um den gesamten Verlauf zu sehen. Für private mexikanische Paketdienste wie [Estafeta](carrier:estafeta), [J&T Express Mexico](carrier:j-and-t-mx) oder [Paquetexpress](carrier:paquetexpress) siehe [Sendungsnummern-Formate](guide:tracking-number-formats).

## Was die Sendungsstatus von Correos de México bedeuten

Correos de México zeigt spanische Ereignistexte samt mexikanischer Postfiliale oder ausländischem Auswechselungsamt und Zeitstempel:

| Correos de México meldet | Was das für dich bedeutet | Peek zeigt |
| --- | --- | --- |
| `Depósito en Oficina de Correos` / `Deposito del Cliente en ...` | Am Schalter einer mexikanischen Postfiliale eingeliefert oder im Ausland bei der dortigen Post aufgegeben. | „Beim Paketdienst eingegangen“ |
| `En tránsito hacia destino`, `Recepción en Oficina de Correos`, `En zona de intercambio int. hacia el país destino, previo Aduana`, `Pieza depositada en valija y enviada a oficina del País destino` | Unterwegs zwischen mexikanischen Verteilzentren oder im Postsack international versandt. | „Unterwegs“ |
| `Arribo a Zona Intercambio México, en espera de ingreso a la Aduana`, `Inicio de la revisión aduanal en país destino` | Im internationalen Auswechselungsamt eingetroffen, wartet auf die Zollprüfung oder befindet sich darin. | „Beim Zoll“ |
| `Revisión aduanal concluida` | Zollprüfung abgeschlossen; Sendung wieder im Postlauf. | „Unterwegs“ |
| `Con mensajero para entrega` | Heute mit dem örtlichen Zusteller auf Auslieferungstour. | „In Zustellung“ |
| `Puesta en ventanilla` | Liegt am Schalter der genannten örtlichen Postfiliale zur Abholung bereit. | „Abholbereit“ |
| `Pieza devuelta ...` (z. B. `por domicilio destino incorrecto`) | Mit angegebenem Grund zurück an den Absender unterwegs. | „Zurück an Absender“ |
| `Entrega` / `Entrega Internacional` | In Mexiko zugestellt oder von der Zielpost im Ausland als zugestellt gemeldet. | „Zugestellt“ |

## Schalterabholung (*Puesta en ventanilla*), Zoll und Reklamationen

Laut [Servicio Postal Mexicano auf gob.mx](https://www.gob.mx/correosdemexico) und der offiziellen Seite [Reclamo de un Envío (Sendungsreklamation)](https://www.gob.mx/correosdemexico/es/acciones-y-programas/reclamo-de-un-envio):

- **Schalterabholung (*Puesta en ventanilla*):** Zeigt das Tracking `Puesta en ventanilla`, wartet deine Sendung in der Postfiliale aus der Spalte *Origen*.
- **Einfuhrzoll:** Aus dem Ausland nach Mexiko eingehende Postpakete durchlaufen die mexikanische Zollprüfung (`Inicio de la revisión aduanal...`). Die Wartezeit hängt von Saison und Zollbewertung ab; siehe [Pakete beim Zoll](guide:customs).
- **Formelle Reklamation (*Reclamo de un Envío*):** Nachforschungen und Entschädigungsanträge gelten **nur für Einschreiben (*Registrados*) und *Mexpost*** (nicht für gewöhnliche unregistrierte Post). Eine Reklamation ist bei überschrittener Laufzeit oder Beschädigung möglich — persönlich in jeder Filiale von Correos de México oder online. Die maximale Antwortfrist von SEPOMEX für Registrados- und Mexpost-Reklamationen beträgt **30 Werktage** (*30 días hábiles*).

## Paket von Correos de México steckt fest, pausiert oder geht zurück

- **Lange Pausen beim Zoll oder im Auslandstransit:** Internationale Postpakete nach oder aus Mexiko bleiben häufig Tage oder Wochen ohne neuen Scan bei `Arribo a Zona Intercambio México` oder zwischen `Pieza depositada en valija` und Ankunft im Zielland. Siehe [warum die Sendungsverfolgung nicht aktualisiert](guide:tracking-not-updating).
- **Zurückgesandte Sendungen (`Pieza devuelta`):** Sobald SEPOMEX eine Sendung als `Pieza devuelta` markiert (etwa falsche Adresse oder nicht abgeholt), betreffen nachfolgende `Puesta en ventanilla`- und `Entrega`-Scans die Rückgabe an den ursprünglichen Absender.
- Kontaktiere bei Online-Bestellungen zusätzlich zur Nachforschung auch den Verkäufer. Siehe [zugestellt, aber nicht erhalten](guide:delivered-not-received), [beschädigtes Paket](guide:damaged-parcel), [verlorenes Paket](guide:lost-parcel) und [zurück an Absender](guide:returned-to-sender).

## Kundenservice und Kontakt von Correos de México

Offizielle Kanäle auf [gob.mx/correosdemexico](https://www.gob.mx/correosdemexico), [Seguimiento.aspx](https://www.correosdemexico.gob.mx/SSLServicios/SeguimientoEnvio/Seguimiento.aspx) und [Reclamo de un Envío](https://www.gob.mx/correosdemexico/es/acciones-y-programas/reclamo-de-un-envio):

- **Offizielle Anfrage-E-Mail:** `contacto@correosdemexico.gob.mx`.
- **Reklamationen & Anfragen:** [Reclamo de un Envío](https://www.gob.mx/correosdemexico/es/acciones-y-programas/reclamo-de-un-envio) oder persönlich in deiner mexikanischen Postfiliale.
- **Verbraucherschlichtung in Mexiko:** Bei ungelösten Handels- oder Service-Streitfällen können sich Verbraucher an [PROFECO](https://www.gob.mx/profeco) wenden.

> Vorsicht vor gefälschten SMS oder WhatsApp-Nachrichten im Namen von Correos de México / SEPOMEX, die eine Online-Kartenzahlung zur „PLZ-Korrektur“ oder „Paketfreigabe“ verlangen. Prüfe deinen S10-Code immer direkt auf [correosdemexico.gob.mx](https://www.correosdemexico.gob.mx/SSLServicios/SeguimientoEnvio/Seguimiento.aspx) oder schreibe an `contacto@correosdemexico.gob.mx`. Siehe [Betrugs-SMS zu Paketlieferungen](guide:delivery-scam-texts).

## Ein Paket von Correos de México mit Peek verfolgen

[Peek](/) erkennt eingefügte Links von `correosdemexico.gob.mx/SSLServicios/SeguimientoEnvio/Seguimiento.aspx?guia=` direkt als Correos de México. Fügst du einen 13-stelligen UPU-S10-Code mit Endung `MX` ein (`RR482917367MX`, `MP482917367MX` oder `EE482917367MX`, erfunden), fragt Peek zuerst Correos de México an und zeigt „Correos de México hat dieses Paket.“, sobald der Dienst es bestätigt. Eine im Ausland aufgegebene Sendung behält den Code ihrer Absenderpost (`US`, `CN` usw.), die Peek zuerst fragt; um sie bei Correos de México zu verfolgen, tippe beim Hinzufügen auf „Ändern“ oder in der Sendungsansicht auf „Paketdienst ändern“ und wähle Correos de México (auch die Suche nach „SEPOMEX“, „Mexpost“ oder „Mexico Post“ findet den Dienst).

Peek liest den Tracker von Correos de México direkt aus, prüft auch frühere im Formular angebotene Jahre, damit eine Sendung über den Jahreswechsel vollständig bleibt, und zeigt die Phase („Beim Paketdienst eingegangen“, „Unterwegs“, „Beim Zoll“, „In Zustellung“, „Abholbereit“, „Zurück an Absender“, „Zugestellt“), den spanischen Scan-Text, die Postfiliale oder das Auswechselungsamt, die Abholfiliale bei `Puesta en ventanilla` sowie die Zustelluhrzeit, sofern vorhanden. Nach `Pieza devuelta` behält Peek spätere Schalter- und Übergabescans unter „Zurück an Absender“, da sie die Rückgabe an den Absender betreffen, nicht eine Zustellung an dich. Correos de México gibt Scan-Zeiten ohne Zeitzone aus: Peek datiert jeden Scan in der Zeitzone seiner Filiale (Tijuana für einen Scan in Tijuana, Mexiko-Stadt für Mexiko-Stadt) und zeigt ihn auf der Uhrzeit deines Geräts. Peek lässt den Namen des Unterzeichners, den Abfragezeitpunkt und Seitenhinweise weg; der Tracker nennt kein voraussichtliches Zustelldatum, kein Gewicht, keinen Tarifnamen und kein Zustellfoto.

Solange du das Paket in Peek geöffnet hast oder am Zustelltag Benachrichtigungen aktiviert sind, fragt Peek bis zu alle 10 Minuten nach; an dem Tag, an dem Correos de México `Con mensajero para entrega` zeigt, alle 2 Minuten ab 6 Uhr auf der Uhrzeit der Filiale des neuesten Scans, solange der eigene Tracker von Correos de México antwortet. Sonst fragt Peek stündlich ab. Nach 12 Stunden nur bei „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ oder nach zwei Tagen ohne Neuigkeiten prüft Peek unabhängig davon stündlich und nach sieben Tagen bei „Etikett erstellt“ alle 6 Stunden. Antwortet der Tracker von Correos de México nicht und Peek verfolgt das Paket allein über Tracking-Dienste, fragt Peek stündlich nach, nach einem ruhigen Tag alle 3 Stunden und am Zustelltag bis zu alle 15 Minuten, solange du die Sendung geöffnet hast oder Benachrichtigungen aktiviert sind.

Ist der neueste Scan vier Tage alt und das Paket steht noch auf „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“, markiert Peek es mit dem Tag dieses Scans, etwa „Keine Aktualisierung seit Mo., 12. Okt.“. Auf der Paketseite steht dann „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei Correos de México nach.“, samt Link „Hol dir Hilfe bei Correos de México“ zur Seite *Reclamo de un Envío*. Nach drei Wochen ohne neuen Scan heißt es „Seit über drei Wochen nichts Neues. Ist das Paket noch nicht angekommen, frag beim Verkäufer nach. Peek fragt weiter bei Correos de México nach.“

Bei den Benachrichtigungen kannst du zwischen „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“ wählen. Peek beobachtet ausschließlich Tracking-Updates und kann weder die Verzollung übernehmen noch eine *Reclamo de un Envío* bei SEPOMEX in deinem Namen einreichen.

## Häufige Fragen zur Sendungsverfolgung von Correos de México

### Welche Sendungsnummern funktionieren bei Correos de México?

Correos de México verfolgt 13-stellige UPU-S10-Postcodes: 2 Buchstaben + 9 Ziffern + 2 Länderbuchstaben (zum Beispiel `RR482917367MX` oder `MP482917367MX`, erfunden), einschließlich eingehender ausländischer S10-Sendungen nach Mexiko.

### Was bedeutet `Puesta en ventanilla`?

Das bedeutet, dass dein Paket am Schalter der in der Spalte *Origen* genannten Postfiliale zur persönlichen Abholung bereitliegt.

### Warum hat Correos de México meine Sendungsnummer nicht gefunden?

Achte auf [Seguimiento.aspx](https://www.correosdemexico.gob.mx/SSLServicios/SeguimientoEnvio/Seguimiento.aspx) darauf, das richtige Einlieferungsjahr (*Ejercicio*) auszuwählen — besonders bei Sendungen vom Ende des Vorjahres — und beachte, dass gewöhnliche nicht registrierte Post kein Tracking hat.

### Wie reiche ich eine Reklamation bei Correos de México ein?

Für verspätete oder beschädigte *Registrados*- und *Mexpost*-Sendungen reiche eine *Reclamo de un Envío* in einer Postfiliale oder über [gob.mx/correosdemexico/es/acciones-y-programas/reclamo-de-un-envio](https://www.gob.mx/correosdemexico/es/acciones-y-programas/reclamo-de-un-envio) (30 Werktage Antwortfrist) ein oder schreibe an `contacto@correosdemexico.gob.mx`.

:::sources
- [Correos de México (SEPOMEX): Portal gob.mx](https://www.gob.mx/correosdemexico) – offizielles Portal von Servicio Postal Mexicano und `contacto@correosdemexico.gob.mx`
- [Correos de México: Seguimiento de Envíos](https://www.correosdemexico.gob.mx/SSLServicios/SeguimientoEnvio/Seguimiento.aspx) – jahresbasierte (*Ejercicio*) S10-Sendungsverfolgung und `contacto@correosdemexico.gob.mx`
- [Correos de México: Reclamo de un Envío](https://www.gob.mx/correosdemexico/es/acciones-y-programas/reclamo-de-un-envio) – Anspruchsberechtigung Registrados & Mexpost, Einreichung vor Ort/online und 30-Werktage-Frist (von Peek verlinkte Hilfeseite)
- [PROFECO](https://www.gob.mx/profeco) – mexikanische Bundesbehörde für Verbraucherschutz
- [Universal Parcel Scraper: correos-de-mexico](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/correos-de-mexico) – S10-Format, Link-Regel `Seguimiento.aspx?guia=`, mehrjährige Formularabfrage, spanische Statustabelle, Abholfiliale `Puesta en ventanilla`, Rücklauf-Logik, Filial-Zeitzone pro Scan und Hilfe-URL
:::
