---
title: A Tu Hora Express Sendungsverfolgung: Codes, PLZ & Hilfe
description: Verfolge ein A-Tu-Hora-Express-Paket in Spanien: 10-, 16- und 24-stellige Codes, warum eine 5-stellige PLZ nötig ist, spanische Status und Kontakt.
slug: a-tu-hora-express-sendungsverfolgung
published: 2026-11-24
updated: 2026-11-24
---

A Tu Hora Express (*A TU HORA EXPRESS, S.L.*) ist ein spanischer Zustelldienst für die letzte Meile mit Hauptsitz in San Fernando de Henares (Madrid) und Niederlassungen auf dem spanischen Festland, unter anderem in Madrid, Barcelona, Valencia, Sevilla, Málaga, Alicante, Murcia, Almería, Cádiz, Castellón, Córdoba, Girona, Granada, Huelva, Jaén, Lleida und Tarragona. Das Unternehmen liefert Online-Handels- und B2B-Bestellungen in Tarifen wie *Express 24h*, *Economy 48h*, *Masivo 72h*, *Reverse Logistics* (*Logística Inversa*) und *B2B Premium* aus. Du kannst ein Paket im [Tracking-Portal von A Tu Hora Express](https://mydelivery.atuhoraexpress.com/#/login-form-cli) prüfen oder deine Sendungsnummer direkt oben einfügen.

## Sendungsnummern von A Tu Hora Express und Postleitzahl der Lieferadresse

Suche deine Service- oder Sendungsnummer in der Versand-E-Mail des Händlers oder auf der Bestellseite. Sendungen von A Tu Hora Express erscheinen in drei rein numerischen Längen:

| Format (erfunden) | Worum es sich handelt |
| --- | --- |
| `0000004829173640` | 16-stellige Servicenummer von A Tu Hora Express (`000000` + 10 Ziffern). Erfordert im Tracker deine 5-stellige spanische Liefer-Postleitzahl. |
| `4829173640` | 10-stelliger Kurzcode (mit sechs führenden Nullen zur 16-stelligen Servicenummer aufgefüllt). Erfordert ebenfalls deine 5-stellige Liefer-Postleitzahl. |
| `000000482917364000128013` | 24-stelliger vollständiger Barcode: die 16-stellige Servicenummer + `001` + die 5-stellige spanische Liefer-Postleitzahl (hier `28013`). Teilt die 24-stellige Länge mit [Ciblex](carrier:ciblex), [InPost](carrier:inpost), [bpost](carrier:bpost), [Austrian Post](carrier:austrian-post) und [Express One](carrier:express-one). |

**Warum die Postleitzahl wichtig ist.** Auf [mydelivery.atuhoraexpress.com](https://mydelivery.atuhoraexpress.com/#/login-form-cli) geben 10- und 16-stellige Nummern nur zusammen mit der **5-stelligen spanischen Postleitzahl der Lieferadresse** Paketdetails zurück. Der 24-stellige Barcode enthält diese Postleitzahl bereits in seinen letzten fünf Ziffern und funktioniert daher ohne gesonderte Eingabe. Weitere spanische und internationale Barcode-Muster findest du unter [Sendungsnummern-Formate](guide:tracking-number-formats).

## Was die Sendungsstatus von A Tu Hora Express bedeuten

A Tu Hora Express liefert jeden Scan auf Spanisch, zusammen mit einem Statuscode (`cod`) und der Uhrzeit in Madrider Ortszeit:

| A Tu Hora Express meldet (Code) | Was das für dich bedeutet | Peek zeigt |
| --- | --- | --- |
| `Documentado` (`0`) | Der Absender hat die Sendung elektronisch vor der Übergabe im Depot angemeldet. | „Etikett erstellt“ |
| `En tránsito` (`1`) | Unterwegs im spanischen Netz von A Tu Hora Express zwischen den Niederlassungen. | „Unterwegs“ |
| `En agencia distribución` (`11`) | In der zuständigen spanischen Zustellniederlassung für deine Postleitzahl eingetroffen. | „Unterwegs“ |
| `En reparto` (`2`) / `En realización` | Mit dem örtlichen Zusteller heute auf Auslieferungstour. | „In Zustellung“ |
| `Incidencia - Pendiente nuevo reparto` (`3`), `Ausente`, `Dirección incorrecta o incompleta`, `Rechazado` | Die Zustellung konnte nicht abgeschlossen werden (Empfänger abwesend, unvollständige Adresse, verweigert oder für einen weiteren Versuch eingeplant). | „Zustellversuch“ |
| `Incidencia - Falta parcial` (`3`), `Incidencia - No cumplimiento agencia` / `Incumplimiento de la agencia` | Betriebliche Störung, etwa ein fehlendes Packstück bei einer Mehrpaket-Sendung oder ein Zustellproblem der Niederlassung. | „Problem gemeldet“ |
| `Pendiente de realizar` (`5`) | Erscheint mit demselben Zeitstempel neben einer Störung und zeigt an, dass eine erneute Zustellung ansteht; die Störungszeile bestimmt die Phase. | *(behält die Störungsphase)* |
| `Realizado con éxito` (`4`) / `Entregado con éxito` | Von A Tu Hora Express zugestellt. | „Zugestellt“ |
| `Devuelto` | Auf dem Rückweg an den Absender. | „Zurück an Absender“ |

## Zustellservices und Angaben auf der öffentlichen Website

Auf [atuhoraexpress.com/home/](https://atuhoraexpress.com/home/) und [atuhoraexpress.com](https://atuhoraexpress.com/) nennt A Tu Hora Express fünf Hauptservices — *Express 24h*, *Economy 48h*, *Masivo 72h*, *Reverse Logistics* und *B2B Premium* — für sein spanisches Niederlassungsnetz, mit Tracking über [atuhoraexpress.com/tracking/](https://atuhoraexpress.com/tracking/) und [mydelivery.atuhoraexpress.com](https://mydelivery.atuhoraexpress.com/#/login-form-cli).

Die Website von A Tu Hora Express veröffentlicht weder eine Anzahl von Zustellversuchen noch Gewichts- oder Maßgrenzen oder eine Lagerfrist. Kam deine Bestellung von außerhalb der Europäischen Union, fallen 3 € Zoll pro Artikel an, wenn sie höchstens 150 € wert ist, dazu 2 € EU-Bearbeitungsgebühr pro Artikel, sofern der Shop sie nicht bereits beim Kauf erhoben hat; siehe [Pakete beim Zoll](guide:customs).

## Paket von A Tu Hora Express verspätet, blockiert oder vermisst

Da der Online-Shop den Beförderungsvertrag mit A Tu Hora Express geschlossen hat, wende dich zuerst an den Händler, wenn ein Paket wiederholt in `Incidencia` hängt, einen Adressfehler (`Dirección incorrecta o incompleta`) zeigt oder den erwarteten Liefertag verpasst.

- **Wiederholte Störungen oder `Pendiente nuevo reparto`:** Bestätige Straße, Stockwerk/Wohnung und Telefonnummer beim Händler und schreibe an `info@atuhoraexpress.com` mit deiner 16- oder 24-stelligen Nummer und der Liefer-Postleitzahl.
- **Als `Realizado con éxito` markiert, aber nicht erhalten:** Frag zuerst bei Mitbewohnern, Nachbarn und dem Hausmeister (*portería*) nach und kontaktiere anschließend sofort Händler und A Tu Hora Express.
- Schritt-für-Schritt-Hilfe findest du unter [warum die Sendungsverfolgung nicht aktualisiert](guide:tracking-not-updating), [zugestellt, aber nicht erhalten](guide:delivered-not-received), [beschädigtes Paket](guide:damaged-parcel), [verlorenes Paket](guide:lost-parcel) und [zurück an Absender](guide:returned-to-sender).

## Kundenservice und Kontakt von A Tu Hora Express

Offizielle Kontaktdaten auf [atuhoraexpress.com/home/](https://atuhoraexpress.com/home/), [atuhoraexpress.com/aviso-legal/](https://atuhoraexpress.com/aviso-legal/) und [atuhoraexpress.com/ayudanos-a-mejorar/](https://atuhoraexpress.com/ayudanos-a-mejorar/):

- **Telefon:** `911 631 356` (Spanien).
- **Kunden-E-Mail:** `info@atuhoraexpress.com`.
- **Feedback-Formular:** [Ayúdanos a mejorar](https://atuhoraexpress.com/ayudanos-a-mejorar/).
- **Datenschutz-E-Mail:** `protecciondedatos@atuhoraexpress.com`.
- **Hauptsitz Madrid:** Calle Artesanos 10, 28830 San Fernando de Henares (Madrid), Spanien (eingetragener Rechtssitz der *A TU HORA EXPRESS, S.L.*, NIF `B19300748`: C/ Espliego 16, 19208 Alovera, Guadalajara).

> Vorsicht vor SMS oder WhatsApp-Nachrichten, die eine kleine Kartenzahlung verlangen, um eine Lieferung von A Tu Hora Express neu zu terminieren. Prüfe dein Paket direkt auf [mydelivery.atuhoraexpress.com](https://mydelivery.atuhoraexpress.com/#/login-form-cli) oder über `info@atuhoraexpress.com`. Siehe [Betrugs-SMS zu Paketlieferungen](guide:delivery-scam-texts).

## Ein Paket von A Tu Hora Express mit Peek verfolgen

[Peek](/) erkennt jeden 16-stelligen Code, der mit sechs Nullen beginnt (`0000004829173640`, Ziffern erfunden), automatisch mit hoher Sicherheit als A Tu Hora Express. Ein 24-stelliger Barcode (`000000482917364000128013`) passt zu mehreren Paketdiensten: Peek fragt bei A Tu Hora Express (mit der im Barcode enthaltenen Postleitzahl), [Austrian Post](carrier:austrian-post), [InPost](carrier:inpost) und [Ciblex](carrier:ciblex) an, ob sie ihn kennen, und erst bei [Express One](carrier:express-one), wenn keiner davon ihn kennt, und zeigt „A Tu Hora Express hat dieses Paket.“, sobald A Tu Hora Express antwortet. Bei einem 10-stelligen Code (`4829173640`) tippe beim Hinzufügen auf „Ändern“ oder in der Sendungsansicht auf „Paketdienst ändern“ und wähle A Tu Hora Express (auch die Suche nach „A Tu Hora“ findet den Dienst).

Bei 10- und 16-stelligen Codes bietet Peek das optionale Feld „Postleitzahl der Lieferadresse“ mit dem Hinweis „A Tu Hora Express zeigt die Sendung nur mit der Postleitzahl der Lieferadresse an.“ Solange du sie nicht einträgst, bietet die Sendungsansicht „Postleitzahl eingeben“, und Peek verfolgt das Paket über Tracking-Dienste, nach einem Tag ohne Antwort einmal täglich. Sobald du die Postleitzahl eingibst, fragt Peek sofort nach. Ein 24-stelliger Barcode trägt seine Postleitzahl in den letzten fünf Ziffern und braucht keine extra Eingabe.

Antwortet A Tu Hora Express, zeigt Peek die Phase („Etikett erstellt“, „Unterwegs“, „In Zustellung“, „Zustellversuch“, „Problem gemeldet“, „Zurück an Absender“, „Zugestellt“), den spanischen Scan-Text samt Ort, den voraussichtlichen Zustelltag, solange A Tu Hora Express einen bei einer offenen Sendung nennt, sowie das Gewicht. Die Statuscodes bestimmen die Phase; Peek blendet sie selbst nicht ein. Scans werden in Madrider Zeit datiert und in der Uhrzeit deines Geräts angezeigt. Peek lässt die GPS-Koordinaten an Scans, die Fortschrittsleiste, das Versanddatum, die Kundenreferenz, Absender- und Empfängernamen, die Zieladresse, Paketanzahl, Volumen, Bemerkungen, Zustellnachweis und Zustelloptionen weg; der Tracker von A Tu Hora Express nennt weder Abholpunkt noch Tarifnamen.

Solange du die Sendung in Peek geöffnet hast oder am Zustelltag Benachrichtigungen aktiviert sind, fragt Peek bis zu alle 10 Minuten nach; an dem Tag, an dem A Tu Hora Express `En reparto` oder `En realización` meldet, alle 2 Minuten ab 6 Uhr Madrider Zeit, solange der Tracker von A Tu Hora Express antwortet. Sonst fragt Peek stündlich ab. Nach 12 Stunden nur bei „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ oder nach zwei Tagen ohne Neuigkeiten prüft Peek stündlich, egal wie du die Sendung verfolgst, und nach sieben Tagen bei „Etikett erstellt“ alle 6 Stunden. Antwortet der Tracker von A Tu Hora Express nicht und Peek verfolgt das Paket allein über Tracking-Dienste, fragt Peek stündlich nach, nach einem ruhigen Tag alle 3 Stunden und an dem Tag, an dem das Paket in Zustellung ist, bis zu alle 15 Minuten, solange du die Sendung geöffnet hast oder Benachrichtigungen aktiviert sind.

Ist der neueste Scan vier Tage alt und das Paket steht noch auf „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“, markiert Peek es mit dem Tag dieses Scans, etwa „Keine Aktualisierung seit Mo 12. Okt“. Auf der Paketseite steht dann „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei A Tu Hora Express nach.“ Nach drei Wochen ohne neuen Scan heißt es „Seit über drei Wochen nichts Neues. Ist das Paket noch nicht angekommen, frag beim Verkäufer nach. Peek fragt weiter bei A Tu Hora Express nach.“

Bei den Benachrichtigungen kannst du zwischen „Jeden Scan“, „Wichtige Schritte“ und „Nur die Zustellung“ wählen. Peek beobachtet ausschließlich Tracking-Updates und kann weder Lieferungen umterminieren noch deine Adresse ändern oder A Tu Hora Express in deinem Namen kontaktieren.

## Häufige Fragen zur Sendungsverfolgung von A Tu Hora Express

### Warum fragt A Tu Hora Express nach meiner Liefer-Postleitzahl?

A Tu Hora Express zeigt einen 10- oder 16-stelligen Code nur zusammen mit seiner 5-stelligen spanischen Liefer-Postleitzahl an — auf [mydelivery.atuhoraexpress.com](https://mydelivery.atuhoraexpress.com/#/login-form-cli) ebenso wie in Peek, wo das Feld optional ist, A Tu Hora Express ohne es aber nicht antwortet. Hast du vom Shop einen 24-stelligen Code bekommen, enthalten die letzten fünf Ziffern die Postleitzahl bereits.

### Was bedeutet `Realizado con éxito` bei A Tu Hora Express?

`Realizado con éxito` (Statuscode `4`) bedeutet, dass A Tu Hora Express die Sendung als zugestellt verbucht hat. Hast du sie nicht erhalten, frag bei Haushaltsmitgliedern, Nachbarn und dem Hausmeister nach und kontaktiere dann den Händler und `info@atuhoraexpress.com`.

### Was bedeutet `Incidencia - Pendiente nuevo reparto`?

Das bedeutet, dass ein Zustellversuch nicht abgeschlossen werden konnte und das Paket für eine weitere Auslieferung eingeplant ist (oft zusammen mit `Pendiente de realizar` zum selben Zeitstempel). Bestätige bei Wiederholung Adresse und Telefonnummer beim Händler.

### Wie erreiche ich A Tu Hora Express in Spanien?

Rufe `911 631 356` an, schreibe an `info@atuhoraexpress.com` oder sende das Formular [Ayúdanos a mejorar](https://atuhoraexpress.com/ayudanos-a-mejorar/) mit deiner Sendungsnummer und der 5-stelligen Liefer-Postleitzahl ab.

:::sources
- [A Tu Hora Express: Startseite (Spanisch)](https://atuhoraexpress.com/) – spanischer Zustelldienst, Niederlassungen, Hauptsitz Madrid, `911 631 356` und `info@atuhoraexpress.com`
- [A Tu Hora Express: Startseite (Englisch)](https://atuhoraexpress.com/home/) – Express 24h, Economy 48h, Masivo 72h, Reverse Logistics, B2B Premium und Liste der Niederlassungen
- [A Tu Hora Express: Tracking-Seite](https://atuhoraexpress.com/tracking/) – Einstiegsseite zur Sendungsverfolgung
- [A Tu Hora Express: MyDelivery-Portal](https://mydelivery.atuhoraexpress.com/#/login-form-cli) – direktes Tracking-Portal
- [A Tu Hora Express: Impressum](https://atuhoraexpress.com/aviso-legal/) – *A TU HORA EXPRESS, S.L.*, NIF `B19300748`, Rechtssitz in Alovera (Guadalajara) und `protecciondedatos@atuhoraexpress.com`
- [A Tu Hora Express: Feedback-Formular](https://atuhoraexpress.com/ayudanos-a-mejorar/) – Kunden-Feedbackformular
- [Universal Parcel Scraper: a-tu-hora-express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/a-tu-hora-express) – 10/16/24-Ziffern-Erkennung, optionale 5-stellige PLZ-Angabe, spanische Statuscodes (`0`, `1`, `11`, `2`, `3`, `4`, `5`), Ortszeit `Europe/Madrid`, voraussichtliches Lieferdatum, Gewicht und verworfene Felder
:::
