---
title: OCA Sendungsverfolgung: Nummern, Status, Filialen & Hilfe
description: Verfolge OCA- und e-Pak-Pakete in Argentinien: 19-stellige Nummern, spanische Status, 2 Zustellversuche, 5 Tage Filialabholung, SmartLocker und Hilfe.
slug: oca-sendungsverfolgung
published: 2026-11-24
updated: 2026-11-24
---

OCA ist ein argentinischer Paket- und Postdienstleister. Seine Plattform ePak bedient E-Commerce, Großunternehmen und kleinere Betriebe landesweit und arbeitet mit Mercado Envíos zusammen. Empfänger verfolgen Sendungen über die [OCA-Sendungssuche](https://www.oca.com.ar/Busquedas/Envios), holen hinterlegte Pakete in den Puntos-OCA-Filialen der argentinischen Provinzen ab oder nutzen OCA SmartLocker.

Das OCA-Tracking erfasst jeden Depot-Scan, Filialtransfer, Hauszustellversuch und Abholzeitraum auf Spanisch.

## OCA-Sendungsnummern-Formate

Standardmäßige OCA-e-Pak-Paketnummern folgen einer 19-stelligen Struktur:

- **19 Ziffern mit `0000` an den Stellen 6–9** (etwa `3654800004829173605`) — die Versand- oder Einlieferungsnummer (`número de guía` / `orden de admisión`), die laut OCA-Hilfecenter zur Sendungsverfolgung dient.
- **Ein angehängtes `-1`** — auf Etiketten kann hinter der Nummer `-1` stehen, etwa `3654800004829173605-1`. Das [OCA-Hilfecenter](https://www.oca.com.ar/CentroDeAyuda) weist darauf hin, es nicht einzugeben. Lass es auch in Peek weg: Peek entfernt nur den Bindestrich, sodass `...-1` zu einer 20-stelligen Nummer wird, die OCA nicht kennt.
- **Frankierte Pakete und Briefe** — OCA vergibt außerdem frankierte Sendungen mit 3 Großbuchstaben (`YAE`, `YAA`, `MDA`, `PAA`) plus 8 Ziffern für Pakete oder 2 Buchstaben plus 8 Ziffern für frankierte Umschläge, die ohne Leerzeichen oder Klammern auf der OCA-Website eingegeben werden. Einfache unfrankierte Post (`postales simples`) hat keine Sendungsverfolgung und wird direkt in den Briefkasten oder unter der Tür durchgeschoben.

Auf der OCA-Website lässt sich die mittlere Nullenfolge durch `//` ersetzen; Peek benötigt alle 19 Ziffern oder einen direkten Link `oca.com.ar/Seguimiento/Paquetes/...` bzw. einen älteren e-Pak-Link, den Peek automatisch liest. In Argentinien fragt Peek bei einer solchen 19-stelligen Nummer direkt bei OCA an; anderswo fragt Peek OCA nur als letzte Option. Weitere regionale Formate findest du im [Leitfaden zu Sendungsnummern-Formaten](guide:tracking-number-formats).

## Was die OCA-Sendungsstatus bedeuten

Der e-Pak-Trackingdienst von OCA meldet Ereignisse auf Spanisch und hängt häufig die bearbeitende Filiale nach einem Bindestrich (`- Suc: ...`) oder einen Grund in Klammern an.

| OCA-Statusmeldung | Bedeutung |
| --- | --- |
| `En proceso de Retiro` | Abholung beim Absender ist angesetzt; OCA hat das Paket noch nicht übernommen. |
| `Retirado` / `Retirado en Origen` | OCA hat das Paket beim Absender abgeholt. |
| `En Proceso en OCA` / `Procesado en OCA` | In einem OCA-Sortierzentrum oder einer Filiale bearbeitet. |
| `En viaje a Centro de Distribución de Destino` / `En viaje a Sucursal de Destino` / `En Tránsito a Suc. de Destino` | Unterwegs zum Ziel-Verteilzentrum oder zur Zustellfiliale. |
| `Arribado a Centro de Distribución de Destino` / `Llegó a nuestra sucursal` | Im Ziel-Verteilzentrum oder in der örtlichen OCA-Filiale eingetroffen. |
| `Programado para visita a domicilio` | In der örtlichen Filiale für die Zustellplanung (`en programación`) vor einem Hausbesuch eingetragen. |
| `Visita a Domicilio en Curso` | Heute mit dem Kurier zur Hauszustellung unterwegs. |
| `No entregado (No Visitado)` / `No entregado (...)` | Zustellung nicht abgeschlossen; der Text in Klammern nennt den Grund (etwa nicht angefahren oder auf Wunsch des Absenders gestoppt). |
| `Reprogramado para nueva visita` | Für einen weiteren Zustellversuch neu eingeplant. |
| `En Espera de Retiro por Sucursal` / `Disponible para que lo retires en tu Sucursal` | In der zugewiesenen OCA-Filiale zur Abholung bereitgestellt. |
| `Estado de Visita ONLINE (Entregado)` / `Entregado` | An den Empfänger zugestellt oder in der Filiale abgeholt. |
| `Acuse digitalizado` | Tage nach der Zustellung im OCA-Zentrum gescannter Zustellnachweis — rein administrativ, bewegt das Paket nicht mehr. |
| `Devolución en Proceso` / `Devuelto al Remitente` | Auf dem Rückweg zum Absender oder bereits zurückgegeben. |

## Hauszustellungen, Filialen und SmartLocker

Das [OCA-Hilfecenter](https://www.oca.com.ar/CentroDeAyuda) legt klare Lieferregeln für ganz Argentinien fest:

- **Zwei Hauszustellversuche:** OCA unternimmt zwei unangekündigte Zustellversuche Montag bis Samstag von 09:00 bis 18:00 Uhr. Jede Person ab 18 Jahren an der Adresse kann das Paket gegen Vorlage ihres DNI (Personalausweis) entgegennehmen. Regentage können die Zustelltouren einschränken.
- **Filialabholung (5 Werktage):** Scheitern beide Versuche, liegt das Paket **5 Werktage** in der zugewiesenen Punto-OCA-Filiale bereit (Filialen über den [Buscador de Puntos OCA](https://www.oca.com.ar/Busquedas/Sucursales)). Der Empfänger braucht seinen DNI und die Sendungsnummer; eine bevollmächtigte Person ab 18 Jahren benötigt eine Kopie des Empfänger-DNI, eine handschriftliche Vollmacht und die Sendungsnummer. Nicht abgeholte Pakete gehen an den Absender zurück.
- **SmartLocker (48 Stunden + optionale Verlängerung):** Legt OCA ein Paket in einen SmartLocker ein, erhältst du eine E-Mail und/oder SMS mit QR-Code und alphanumerischer PIN. Du hast **48 aufeinanderfolgende Stunden** Zeit zur Abholung; nach Erinnerungen schickt OCA einen Link zur Kartenzahlung, über den du bis zu **3 zusätzliche Lagertage** dazukaufen kannst, bevor die Sendung an den Absender zurückgeht.
- **Falsche Adresse oder Rücksendung:** OCA erlaubt weder den Abbruch einer laufenden Sendung noch eine Änderung der Lieferadresse unterwegs. Warte die Zustellversuche ab, um das Paket in der Filiale abzuholen (oder lass es an den Absender zurückgehen und bitte ihn, es neu zu versenden). Weitere Möglichkeiten nach verpasster Zustellung zeigt der [Ratgeber zu verpassten Lieferungen](guide:missed-delivery).
- **Sondersendungen (DNI, Reisepässe, OCA-Geo-Karten, Briefe):** DNI-Ausweise werden ausschließlich an der Wohnadresse zugestellt (gegen RENAPER-Beleg) und können nicht in einer Filiale abgeholt werden; Reisepässe gehen nach zwei Hausbesuchen in die Filialabholung. OCA-Geo-Karten werden nur persönlich an den Karteninhaber ausgehändigt. Frankierte Umschläge erhalten zwei Zustellversuche und gehen danach ohne Filialaufbewahrung zurück.
- **48 Geschäftsstunden bis zum ersten Scan:** Laut OCA kann es nach der Einlieferung in einer Filiale bis zu **48 Geschäftsstunden** dauern, bis eine Sendung online erscheint. Zeigt sich nach 48 Geschäftsstunden nichts, kann ausschließlich der Absender eine Nachforschung einleiten. Siehe [wenn die Sendungsverfolgung stehen bleibt](guide:tracking-not-updating) für allgemeine Prüfungen.

Für weitere inländische argentinische Dienste vergleiche mit [Andreani](carrier:andreani).

## OCA mit Peek verfolgen

Füge eine 19-stellige OCA-Nummer ohne `-1` oder einen Link `oca.com.ar/Seguimiento/Paquetes/...` in Peek ein. Erkennt Peek OCA nicht automatisch, wähle OCA über **Ändern** beim Hinzufügen oder **Paketdienst ändern** auf der Sendungsseite.

Peek ruft den e-Pak-Webdienst `Tracking_Pieza` von OCA direkt über den Open-Source-[OCA-Adapter von universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/oca) ab. Jeder argentinische Zeitstempel wird exakt in `America/Argentina/Buenos_Aires` (`-03:00`) gespeichert, Filialnamen werden sauber im Ortsfeld ausgegeben und administrative `Acuse digitalizado`-Einträge überschreiben oder stören eine abgeschlossene Zustellung nie. Der OCA-e-Pak-Feed enthält kein voraussichtliches Lieferdatum und kein Paketgewicht.

Peek übersetzt die OCA-Ereignisse in klare Phasen: **Etikett erstellt** (`En proceso de Retiro`), **Beim Paketdienst eingegangen** (`Retirado`), **Unterwegs** (Sortierzentrum und Filialtransfers), **In Zustellung** (`Programado para visita a domicilio` und `Visita a Domicilio en Curso`), **Zustellversuch** (`No entregado` und `Reprogramado para nueva visita`), **Abholbereit** (`En Espera de Retiro por Sucursal`), **Zugestellt** (`Entregado`) und **Zurück an Absender** (`Devolución en Proceso` / `Devuelto al Remitente`).

Solange du das Paket in Peek geöffnet hast oder am Zustelltag Benachrichtigungen aktiviert sind, fragt Peek bis zu alle 10 Minuten nach; an dem Tag, an dem OCA `Programado para visita a domicilio` oder `Visita a Domicilio en Curso` meldet, ab 6 Uhr argentinischer Zeit alle 2 Minuten, solange der OCA-Tracker antwortet. Andernfalls prüft Peek stündlich. Nach 12 Stunden mit lediglich „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ oder nach zwei Tagen ohne Neuigkeiten fragt Peek stündlich nach, egal wie du die Sendung verfolgst, und nach sieben Tagen auf „Etikett erstellt“ alle 6 Stunden. Antwortet OCA nicht und Peek verfolgt das Paket allein über Tracking-Dienste, prüft Peek stündlich, nach einem ruhigen Tag alle 3 Stunden und an dem Tag, an dem das Paket in Zustellung ist, bis zu alle 15 Minuten, solange du die Sendung geöffnet hast oder Benachrichtigungen aktiviert sind.

Ist der neueste Scan vier Tage alt und das Paket steht noch auf „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“, markiert Peek es mit dem Tag dieses Scans, etwa „Keine Aktualisierung seit Mo 12. Okt“. Auf der Paketseite steht dann „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei OCA nach.“, samt Link „Hol dir Hilfe bei OCA“ zum OCA-Hilfecenter. Nach drei Wochen ohne neuen Scan heißt es „Seit über drei Wochen nichts Neues. Ist das Paket noch nicht angekommen, frag beim Verkäufer nach. Peek fragt weiter bei OCA nach.“

Wähle in den Peek-Benachrichtigungen **Jeden Scan**, **Wichtige Schritte** oder **Nur die Zustellung**, um festzulegen, welche Ereignisse eine Meldung auslösen.

## Kontakt zu OCA

- **Hilfecenter und Online-Supportformular:** [OCA Centro de Ayuda](https://www.oca.com.ar/CentroDeAyuda) (mit FAQ-Kategorien und direktem Kontaktformular am Seitenende)
- **Offizielle Sendungssuche:** [OCA Búsqueda de envío](https://www.oca.com.ar/Busquedas/Envios)
- **Filialfinder:** [Buscador de Puntos OCA](https://www.oca.com.ar/Busquedas/Sucursales)
- **Hauptwebsite:** [oca.com.ar](https://www.oca.com.ar/)
- **Bei fehlender Nummer oder Neuversand zuerst an den Absender wenden:** OCA betont, dass nur der Absender berechtigt ist, eine 48 Geschäftsstunden nach Abgabe noch nicht erfasste Sendung prüfen zu lassen oder nach einer Rücksendung einen Neuversand zu veranlassen.

## Häufige Fragen

### Warum endet meine OCA-Sendungsnummer auf `-1`?

Das OCA-Hilfecenter weist an, das separate `-1` wegzulassen und nur mit der 19-stelligen Versand- oder Einlieferungsnummer zu suchen. Mache es in Peek genauso: Peek entfernt `-1` nicht, und mit diesem Anhang kennt OCA die Nummer nicht.

### Wie lange bewahrt OCA ein Paket in einer Filiale oder einem SmartLocker auf?

OCA lagert Pakete in Puntos-OCA-Filialen **5 Werktage** nach fehlgeschlagenen Zustellversuchen oder Filialeingang. In SmartLockern hast du **48 aufeinanderfolgende Stunden** kostenfrei, mit der Möglichkeit, online bis zu **3 weitere Tage** vor der Rücksendung hinzuzubuchen.

### Kann ich die Lieferadresse ändern, nachdem OCA das Paket übernommen hat?

Nein. Laut OCA-Hilfecenter lassen sich weder der Versandablauf noch die Lieferadresse unterwegs ändern. Nach den Hauszustellversuchen kannst du das Paket persönlich mit deinem DNI in der zugewiesenen OCA-Filiale abholen oder es an den Absender zurückgehen lassen.

### Warum zeigt meine OCA-Sendungsnummer noch keine Updates an?

OCA weist darauf hin, dass ein Paket nach der Filialeinlieferung bis zu **48 Geschäftsstunden** brauchen kann, um im Web-Tracking zu erscheinen. Verstreichen 48 Geschäftsstunden ohne Eintrag, kontaktiere den Absender, der als einziger eine Nachforschung bei OCA eröffnen darf.

:::sources
- [OCA Argentina](https://www.oca.com.ar/)
- [OCA Centro de Ayuda](https://www.oca.com.ar/CentroDeAyuda)
- [OCA Búsqueda de envío](https://www.oca.com.ar/Busquedas/Envios)
- [OCA Buscador de Puntos OCA](https://www.oca.com.ar/Busquedas/Sucursales)
- [universal-parcel-scraper OCA-Adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/oca)
:::
