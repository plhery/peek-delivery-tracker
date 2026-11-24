---
title: Serpost Sendungsverfolgung: Peru S10-Codes, Zoll & Hilfe
description: Verfolge ein Serpost-Paket aus oder nach Peru: 13-stellige PE-S10-Codes, SUNAT-Zollstatus, Abholung in der Filiale, Fristen und Callcenter Lima.
slug: serpost-sendungsverfolgung
published: 2026-11-24
updated: 2026-11-24
---

**Serpost** (*Servicios Postales del Perú S.A.*) ist der staatliche nationale Postbetreiber Perus unter dem Ministerium für Verkehr und Kommunikation (MTC) ([gob.pe/serpost](https://www.gob.pe/serpost)). Serpost befördert Briefe und Pakete innerhalb Perus sowie internationale Ausgangssendungen (darunter [EMS](carrier:ems) und *Exporta Fácil*) und eingehende Weltpostsendungen (UPU) aus dem Ausland. Die Online-Sendungsverfolgung steht unter [serpost.com.pe/Cliente/SegumientoLinea](https://www.serpost.com.pe/Cliente/SegumientoLinea) bereit. Du kannst dort eine peruanische oder eingehende internationale S10-Postsendung prüfen oder deinen 13-stelligen Code oben einfügen.

## Serpost Sendungsnummern (13-stelliges UPU-S10-Format)

Serpost verfolgt standardmäßige 13-stellige UPU-S10-Barcodes: zwei Dienstbuchstaben, 8 laufende Ziffern, 1 S10-Prüfziffer sowie ein 2-buchstabiges Herkunftsland (`PE` für Sendungen aus Peru oder den ausländischen Ländercode wie `US`, `CN`, `ES` oder `JP` für eingehende Auslandspakete nach Peru):

| Format (erfunden) | Was es ist |
| --- | --- |
| `RR482917367PE` | 13-stelliges Einschreiben aus Peru (`RR` + 9 Ziffern mit gültiger S10-Prüfziffer + `PE`). |
| `EE739184627PE` | 13-stelliges internationales [EMS](carrier:ems)-Expresspaket aus Peru (`EE` + 9 Ziffern mit gültiger S10-Prüfziffer + `PE`). |

Unverfolgte Economy-Briefe ohne 13-stelligen S10-Barcode erscheinen nicht im Online-Tracker von Serpost. Bei eingehenden Auslandspaketen (z. B. mit Endung `CN`, `US` oder `GB`) zeigt die Auswahl von Serpost nach Ankunft in Peru die lokalen Scans des peruanischen Zolls (*Aduana*) und der Postfiliale unter demselben 13-stelligen S10-Code. Mehr dazu im Leitfaden zu [Sendungsnummern-Formaten](guide:tracking-number-formats).

## Bedeutung der Serpost Statusmeldungen

Serpost veröffentlicht spanische Statusbeschreibungen und nennt dabei die jeweilige peruanische Poststelle (*Administración Postal*), in der der Scan erfolgte:

| Serpost meldet | Was es für dich bedeutet | Peek zeigt |
| --- | --- | --- |
| `ENVÍO ADMITIDO EN ...` | Am Schalter der genannten peruanischen Serpost-Filiale eingeliefert. | „Beim Paketdienst eingegangen“ |
| `ENVÍO LLEGÓ A SERPOST`, `ENVÍO EN PROCESAMIENTO`, `ENVÍO EN TRÁNSITO` | Eingehendes Auslandspaket bei Serpost in Peru eingetroffen, in der Zentrale sortiert oder im Inland unterwegs. | „Unterwegs“ |
| `ENVÍO SUJETO A TRÁMITE ANTE ADUANA` | Vom peruanischen Zoll (SUNAT) für Verzollung, Wertermittlung oder Einfuhrabgabenprüfung ausgewählt. | „Beim Zoll“ |
| `ENVÍO SIN OBSERVACIÓN POR LA ADUANA` | Ohne Beanstandung durch den peruanischen Zoll gegangen und wieder an Serpost freigegeben. | „Unterwegs“ |
| `ENVÍO SALIÓ DE PERÚ`, `ENVÍO RUMBO AL PAÍS DE DESTINO` | Ausgehende Auslandssendung hat Peru Richtung Post des Ziellandes verlassen. | „Unterwegs“ |
| `ENVÍO LLEGÓ A LA ADMINISTRACIÓN POSTAL ...` | In der zuständigen peruanischen Zustellpoststelle zur Vorbereitung der Tour angekommen. | „Unterwegs“ |
| `ENVÍO EN PROCESO DE DISTRIBUCIÓN`, `SALIÓ A DISTRIBUCIÓN` | Mit einem Serpost-Zusteller unterwegs zur Zustellung an der Haustür. | „In Zustellung“ |
| `ENVÍO DISPONIBLE PARA ENTREGA O RECOJO` | Bereit zur Abholung in der zugewiesenen Serpost-Filiale. | „Abholbereit“ |
| `INTENTO FALLIDO DE ENTREGA`, `SE INTENTÓ ENTREGAR PERO FUE IMPOSIBLE` | Zustellung war nicht möglich (Empfänger nicht angetroffen oder Zugangsproblem); prüfe auf eine Abholbenachrichtigung. | „Zustellversuch“ |
| `ENTREGADO`, `ENVÍO ENTREGADO`, `FUE ENTREGADO` | An den Empfänger zugestellt oder in der Postfiliale abgeholt. | „Zugestellt“ |

## Laufzeiten, Zoll und offizielle Reklamationsfristen

Laut [Serpost Versanddienste](https://www.gob.pe/institucion/serpost/pages/20800-enviar-cartas-dentro-y-fuera-del-pais-con-serpost) und [Serpost Reklamationen & Anfragen](https://www.gob.pe/institucion/serpost/pages/33693-reclamos-y-consultas-servicios-postales-del-peru):

- **Veröffentlichte Laufzeiten für Briefe:** **Expreso** national **maximal 48–72 Stunden**; **Estándar** national **5–7 Werktage**; **Económico** national **7–10 Werktage**; **Expreso** international **7–10 Werktage**; **Económico** international **10–30 Werktage**. Nur Expreso- und Estándar-Briefe lassen sich online verfolgen.
- **Peruanischer Zoll (`ENVÍO SUJETO A TRÁMITE ANTE ADUANA`):** Die Sendung wird für Zollformalitäten angehalten und erst nach Freigabe durch den Zoll (`ENVÍO SIN OBSERVACIÓN POR LA ADUANA`) zugestellt. Der Serpost-Tracker kann eine Ankunftsmitteilung (*Aviso de llegada*) anzeigen. Siehe den Leitfaden zu [Paketen beim Zoll](guide:customs).
- **Offizielle Reklamationsfristen & Antworten:** Reklamationen für **EMS**-Sendungen können ab dem Tag nach Ablauf der Lieferfrist bis zu **4 Monate** nach Einlieferung gestellt werden (Antwort binnen **30 Kalendertagen**); bei internationalen Einschreiben ab **20 Kalendertagen** und bei internationalen Paketen ab **30 Kalendertagen**; nationale Reklamationen werden innerhalb von **16 Werktagen** beantwortet (nationale Entschädigung entspricht dem **3-Fachen des gezahlten Portos**; international gelten UPU-Regeln).

## Serpost-Paket verspätet, beim Zoll oder verschwunden

- **Stille Phase bei internationaler Post:** Ausgehende Sendungen nach `ENVÍO SALIÓ DE PERÚ` oder eingehende Sendungen aus dem Ausland warten zwischen Luftpostabgang und Zolleingang oft mehrere Tage oder Wochen. Prüfe die nationale Post des Ziellandes (wie [USPS](carrier:usps)), sobald eine ausgehende `...PE`-Sendung Peru verlassen hat. Siehe [stehengebliebene Sendungsverfolgung](guide:tracking-not-updating).
- **Eine Serpost-Reklamation (*reclamo*) einreichen:** Sende das Formular per E-Mail an `servicioalcliente@serpost.com.pe`, rufe `(01) 511-5110` an oder gehe zur nächsten Serpost-Filiale — du erhältst per E-Mail eine Vorgangsnummer (*número de expediente*) zur Nachverfolgung. Siehe auch [verlorenes Paket](guide:lost-parcel), [beschädigtes Paket](guide:damaged-parcel), [zugestellt, aber nicht erhalten](guide:delivered-not-received) und [zurück an den Absender](guide:returned-to-sender).

## Serpost Kundenservice und Kontakte in Lima

Offizielle Kanäle laut [Serpost Contacto (gob.pe)](https://www.gob.pe/institucion/serpost/contacto-y-numeros-de-emergencias):

- **Callcenter (Peru):** `(01) 511-5110`.
- **Hauptzentrale:** `511-5000` (Zentralen-Durchwahlen `(511) 533-2001` / `533-2002` / `533-2003` / `533-2004`).
- **E-Mail Kundenservice & Reklamationen:** `servicioalcliente@serpost.com.pe` (Webmaster: `webmaster@serpost.com.pe`).
- **Hauptsitz (*Sede Central*):** Av. Tomás Valle cdra. 7 s/n, Los Olivos, Lima 15302, Peru.
- **Online-Tracker & Regierungsportal:** [serpost.com.pe/Cliente/SegumientoLinea](https://www.serpost.com.pe/Cliente/SegumientoLinea) und [gob.pe/serpost](https://www.gob.pe/serpost).

> Vorsicht vor gefälschten SMS, die behaupten, ein Serpost-Paket sei bis zur Zahlung einer kleinen Gebühr über einen fremden Link blockiert. Prüfe Zollhinweise immer direkt auf [serpost.com.pe/Cliente/SegumientoLinea](https://www.serpost.com.pe/Cliente/SegumientoLinea) oder über offizielle `gob.pe`- und SUNAT-Portale. Siehe [Betrugs-SMS zu Paketen](guide:delivery-scam-texts).

## Serpost mit Peek verfolgen

Gibst du einen 13-stelligen peruanischen S10-Code (`RR482917367PE` oder `EE739184627PE`, erfunden) in [Peek](/) ein, prüft Peek die S10-Prüfziffer und fragt zuerst Serpost, danach Chronopost ab. Für einen ausländischen S10-Code nach Peru oder um manuell zu Serpost zu wechseln, tippe beim Hinzufügen auf „Ändern“ oder auf der Paketseite auf „Paketdienst ändern“ und wähle **Serpost** (auch „SERPOST“, „Peru Post“ oder „Servicios Postales del Perú“ findet den Dienst). Sobald Serpost Scans liefert, zeigt Peek „Serpost hat dieses Paket.“

Peek liest den Serpost-Tracker und zeigt die Stufe („Beim Paketdienst eingegangen“, „Unterwegs“, „Beim Zoll“, „In Zustellung“, „Abholbereit“, „Zustellversuch“, „Zugestellt“), den spanischen Wortlaut von Serpost ohne die WhatsApp-Einladung der Filiale, den Namen der Poststelle und den Zustellzeitpunkt. Verlinkt Serpost die Karte einer Filiale in Peru, setzt Peek den Scan dort auf die Paketkarte; sobald die Sendung in Zustellung, abholbereit oder zugestellt ist, nennt Peek Peru als Zielland. Serpost gibt Scan-Zeiten nach Lima-Zeit an: Peek datiert sie in Lima-Zeit und zeigt sie in der Uhrzeit deines Geräts an. Ein voraussichtliches Lieferdatum, Gewicht oder Unterschriftsnamen nennt der Serpost-Tracker nicht.

Solange du das Paket in Peek geöffnet hast oder am Zustelltag Benachrichtigungen aktiviert sind, fragt Peek bis zu alle 10 Minuten nach; an dem Tag, an dem Serpost `ENVÍO EN PROCESO DE DISTRIBUCIÓN` meldet, ab 6 Uhr Lima-Zeit alle 2 Minuten, solange der Serpost-Tracker antwortet. Andernfalls prüft Peek stündlich. Nach 12 Stunden mit lediglich „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ oder nach zwei Tagen ohne Neuigkeiten prüft Peek stündlich, egal wie du die Sendung verfolgst, und nach sieben Tagen auf „Etikett erstellt“ alle 6 Stunden. Antwortet der Serpost-Tracker nicht und Peek verfolgt das Paket allein über Tracking-Dienste, prüft Peek stündlich, nach einem ruhigen Tag alle 3 Stunden und an dem Tag, an dem das Paket in Zustellung ist, bis zu alle 15 Minuten, solange du die Sendung geöffnet hast oder Benachrichtigungen aktiviert sind.

Ist der neueste Scan vier Tage alt und das Paket steht noch auf „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“, markiert Peek es mit dem Tag dieses Scans, etwa „Keine Aktualisierung seit Mo 12. Okt“. Auf der Paketseite steht dann „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei Serpost nach.“ samt dem Link „Hol dir Hilfe bei Serpost“ zur Kontaktseite auf gob.pe. Nach drei Wochen ohne neuen Scan heißt es „Seit über drei Wochen nichts Neues. Ist das Paket noch nicht angekommen, frag beim Verkäufer nach. Peek fragt weiter bei Serpost nach.“

Du kannst Benachrichtigungen für „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“ wählen. Peek überwacht nur Sendungsaktualisierungen und kann weder die SUNAT-Verzollung übernehmen noch Reklamationen einreichen oder Serpost für dich kontaktieren.

## Fragen zur Sendungsverfolgung bei Serpost

### Wie sieht eine Serpost-Sendungsnummer aus?

Serpost verfolgt 13-stellige UPU-S10-Codes — zwei Buchstaben, 9 Ziffern (mit Prüfziffer am Ende) und ein 2-buchstabiges Herkunftsland wie `PE` (etwa `RR482917367PE` oder `EE739184627PE`, erfunden) sowie ausländische S10-Codes auf dem Weg nach Peru.

### Was bedeutet `ENVÍO SUJETO A TRÁMITE ANTE ADUANA`?

Der peruanische Zoll (SUNAT) hat die internationale Sendung zur Zollabfertigung oder Prüfung von Einfuhrabgaben ausgewählt, bevor Serpost sie zur örtlichen Zustellung oder Abholung in der Filiale freigeben kann.

### Wie lange dauert die Zustellung mit Serpost?

Serpost nennt auf [gob.pe](https://www.gob.pe/institucion/serpost/pages/20800-enviar-cartas-dentro-y-fuera-del-pais-con-serpost) Laufzeiten für Briefe: **48–72 Stunden** für nationales Expreso, **5–7 Werktage** für nationales Estándar, **7–10 Werktage** für nationales Económico oder internationales Expreso sowie **10–30 Werktage** für internationales Económico.

### Wie erreiche ich den Serpost-Kundenservice oder reiche eine Reklamation ein?

Rufe das Serpost-Callcenter unter `(01) 511-5110` an, schreibe an `servicioalcliente@serpost.com.pe` oder wende dich an eine Serpost-Filiale; alle Kontaktwege stehen auf [gob.pe/institucion/serpost/contacto-y-numeros-de-emergencias](https://www.gob.pe/institucion/serpost/contacto-y-numeros-de-emergencias).

:::sources
- [Serpost: Seguimiento en Línea](https://www.serpost.com.pe/Cliente/SegumientoLinea) – offizielles Online-Tracking und Callcenter `(01) 511-5110`
- [Serpost: Portal Institucional (gob.pe)](https://www.gob.pe/serpost) – offizielles Regierungsportal *Servicios Postales del Perú S.A.* (MTC)
- [Serpost: Contacto y Números de Atención](https://www.gob.pe/institucion/serpost/contacto-y-numeros-de-emergencias) – Callcenter `511-5110`, Zentrale `511-5000`, `servicioalcliente@serpost.com.pe` und Sitz Los Olivos (von Peek verlinkte Hilfeseite)
- [Serpost: Reclamos y Consultas](https://www.gob.pe/institucion/serpost/pages/33693-reclamos-y-consultas-servicios-postales-del-peru) – Reklamationsfristen (20 T Einschreiben, 30 T Pakete, 4 M EMS), Antwortzeiten (16 T national / 30 T EMS) und Entschädigungsregeln
- [Serpost: Envíos Nacionales e Internacionales](https://www.gob.pe/institucion/serpost/pages/20800-enviar-cartas-dentro-y-fuera-del-pais-con-serpost) – veröffentlichte Laufzeiten für Briefe nach Dienst
- [Universal Parcel Scraper: serpost](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/serpost) – UPU-S10-Prüfziffer, `SegumientoLinea`-Abfrage, spanische Statusregeln, Filialname & Peru-Kartenkoordinaten sowie `America/Lima`-Zeitstempel
:::
