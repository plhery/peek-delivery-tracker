---
title: SAMUS Sendungsverfolgung: Nummern, Status & Abholung
description: Verfolge einen Brief oder ein Paket von SAMUS in Litauen: wo die Nummer steht, litauische Statusmeldungen, Abholpunkt in Vilnius und Kontakt.
slug: samus-sendungsverfolgung
published: 2026-11-23
updated: 2026-11-23
---

SAMUS (UAB „SAMUS“) ist ein litauischer Kurierdienst für Briefe und Pakete, tätig seit 2011. Er liefert für Geschäftskunden mit Vertrag: Einschreiben und Expressbriefe, persönlich gegen Unterschrift übergebene Briefe sowie Expresspakete in Litauen und ins Ausland. Du kannst eine Sendung auf [samus.lt unter „Parcel tracking“](https://www.samus.lt/en/parcel-tracking/) verfolgen oder die Nummer oben in das Feld einfügen.

## SAMUS-Sendungsnummer: Wie sie aussieht

Laut den SAMUS-FAQ steht die Sendungsnummer auf dem Etikett, das Geschäftskunden im Selbstbedienungsportal erstellen. Frage daher das absendende Unternehmen nach der Nummer.

Die Dokumentation für Geschäftskunden zeigt Nummer und Strichcode auf dem Etikett in einer einzigen Form: zwei Buchstaben, neun Ziffern und LT, insgesamt 13 Zeichen. Das ist exakt die Form einer litauischen Postnummer.

| Nummer (erfunden) | Was sie bedeutet |
| --- | --- |
| `RD482917367LT` | Zwei Buchstaben, neun Ziffern, LT: das Format aus der SAMUS-Dokumentation. Peek wertet die neunte Ziffer wie bei Postnummern als Prüfziffer. |
| `RD482917360LT` | Dasselbe Format mit falscher Prüfziffer: Peek ordnet keinen Paketdienst zu. Prüfe die Nummer oder wähle SAMUS selbst (siehe unten). |

Leerzeichen, Bindestriche und Kleinschreibung spielen beim Einfügen in Peek keine Rolle. Auf samus.lt zeigt das Ergebnis eine „Parcel number“ und einen „Parcel code“. Meldet die Seite `Your letter with number "…" is not found`, prüfe die Nummer beim Absender.

Hat deine Nummer eine andere Form, liefert möglicherweise ein anderes Unternehmen, etwa [Lietuvos paštas](carrier:lietuvos-pastas), Omniva oder Venipak. [Sendungsnummern-Formate](guide:tracking-number-formats) helfen bei der Einordnung.

## SAMUS-Sendungsstatus

SAMUS zeigt seine Statusmeldungen auf der Trackingseite ausschließlich auf Litauisch an. Auch die englische Seite gibt dieselben litauischen Zeilen aus. Die wichtigsten:

| Status auf samus.lt | Was das für dich bedeutet |
| --- | --- |
| `Siunta įvesta savitarnoje` | Der Absender hat die Sendung im SAMUS-Portal angelegt. SAMUS hat sie noch nicht. |
| `Siunta paruošta išsiuntimui` | Die Sendung ist versandbereit, liegt aber noch beim Absender. |
| `Siunta neperduota kurjeriui surinkimo metu` | Der Absender hat die Sendung bei der Abholung nicht an den Kurier übergeben. |
| `Siunta surinkta iš siuntėjo` | Der Kurier hat sie beim Absender abgeholt. |
| `Siunta rūšiavimo centre` | Sie befindet sich im SAMUS-Sortierzentrum. |
| `Siunta pas kurjerį` | Ein Kurier ist damit zur Zustellung unterwegs. |
| `Gavėjui paliktas pranešimas` | Der Kurier hat dich nicht angetroffen und eine Benachrichtigung hinterlassen. |
| `Gavėjas atvyks atsiimti į terminalą` | Du holst die Sendung im Terminal ab. |
| `Siunta palikta UAB Samus Žalgirio g. 92 (vidinis kiemas), Vilnius` | Sie wartet am SAMUS-Abholpunkt in Vilnius. Eine entsprechende Zeile nennt Minijos g. 49 in Klaipėda. |
| `Siunta sėkmingai įteikta` | Zugestellt. |
| `Siunta sėkmingai įteikta, įteikimo pranešimas grąžinamas siuntėjui` | Zugestellt, und der unterschriebene Zustellnachweis geht an den Absender zurück. |
| `Siunta neįteikta, neatsiliepė į pranešimą` | Nicht zugestellt: Auf die Benachrichtigung hat niemand reagiert. |
| `Siunta neįteikta (…)` | Nicht zugestellt, mit Grund in Klammern: verweigert, falsche Adresse, Empfänger unbekannt, vom Absender storniert, Empfänger umgezogen, reine Meldeadresse, Justizvollzugsanstalt, defekter Briefkasten oder ein anderer Grund. |
| `Siunta išsiųsta iš Lietuvos` | Sie hat Litauen verlassen. Spätere Zeilen folgen ihr bis ins Sortierzentrum, zum Zoll und zur Post im Zielland. |
| `Siunta perduota Lietuvos paštui` | SAMUS hat sie an Lietuvos paštas übergeben. |
| `Siunta perduota į tranzitinį paštą` | SAMUS hat sie an eine Transitpost übergeben, die weiterbefördert. |
| `Siunta peradresuota` | Sie wurde an eine andere Adresse weitergeleitet. |
| `Siunta neįteikta. Grąžinama siuntėjui` | Nicht zugestellt und auf dem Rückweg zum Absender. `Siunta grąžinta siuntėjui` bedeutet, dass sie dort angekommen ist. |
| `Siunta pamesta` | Die Sendung ist verloren. |

Peek kann den SAMUS-Tracker nicht auslesen und zeigt diese Zeilen daher nicht an: siehe „Ein SAMUS-Paket mit Peek verfolgen“ weiter unten. Allgemeine Statusmeldungen erklärt [Sendungsstatus erklärt](guide:tracking-statuses).

## Zustellung und Abholung bei SAMUS

**Lieferzeiten.** Laut den SAMUS-FAQ erfolgt die reguläre Kurierzustellung in Litauen am nächsten Werktag; Zustellung am selben Tag oder bis 10:00 beziehungsweise 12:00 Uhr lässt sich als Zusatzleistung buchen. Die Bedingungen geben für Briefe sowie S-, M- und L-Sendungen 2 bis 6 Werktage in Litauen, bis zu 10 in der EU und bis zu 20 außerhalb der EU an. Eine Express-S-Sendung erreicht den Empfänger in Litauen am nächsten Werktag bis 17:00 Uhr.

**Briefkasten oder Unterschrift.** Briefe und S-Sendungen ohne Verfolgung landen im Briefkasten. Verfolgte Sendungen werden gegen Unterschrift an dich oder eine von dir bevollmächtigte Person übergeben. Bei einer Firmenadresse darf jeder Mitarbeiter unterschreiben; erreicht der Kurier den Empfänger nicht persönlich, hinterlässt er die Sendung an der Rezeption, beim Diensthabenden oder beim Sicherheitsdienst. M- und L-Sendungen erfordern immer eine Unterschrift.

**Wenn du nicht da bist.** Der Kurier hinterlässt nach jedem Versuch eine Benachrichtigung. Steht deine Telefonnummer auf der Sendung, ruft er möglicherweise an, um Zeit und Ort abzustimmen. Laut Bedingungen muss der Kurier höchstens 10 Minuten warten. Auf der Benachrichtigung steht, wo du die Sendung abholen kannst; stimme den Zeitpunkt mit dem Kurier ab. Briefe bewahrt der Kurier nach der Benachrichtigung 14 Werktage auf und sendet sie dann an den Absender zurück. Expresspakete lagern 3 Tage kostenlos im Terminal.

**SAMUS-Abholpunkt in Vilnius.** Žalgirio g. 92, LT-09303 Vilnius, Erdgeschoss im Innenhof. Suche das Gebäude mit orangefarbener Fassade: Eine Durchfahrt führt in den Innenhof. Dort weisen das Schild „Siuntų išdavimo vieta“ (Paketausgabe) und die Tür mit dem SAMUS-Schild den Weg. Vom Stadtzentrum oder Bahnhof fahren die Busse 1G oder 53 bis zur Haltestelle „Žalgirio st.“ an der Kalvarijų g., ebenso die Busse 10 oder 34, danach sind es wenige Minuten zu Fuß.

**Sendung prüfen.** Kontrolliere den Zustand gemeinsam mit dem Kurier. Vermerke Schäden auf dem Frachtbrief und fülle ein Schadensprotokoll aus. Unterschreibst du ohne Vorbehalt, wertet SAMUS die Sendung als unbeschädigt zugestellt. Bewahre die Originalverpackung auf, sonst kann der Anspruch auf Entschädigung entfallen.

**Adresse ändern.** Nach Aufgabe der Sendung kann der Absender die Empfängeradresse möglichst frühzeitig kostenpflichtig über SAMUS ändern lassen. Wende dich an den Absender.

**Sendungen ins Ausland.** SAMUS liefert in die meisten europäischen Länder und weltweit. Expresspakete brauchen meist 1 bis 5 Werktage. Im Tracking kann der Zoll des Ziellands auftauchen. Laut Bedingungen trägt der Empfänger Zölle und Steuern, sofern der Frachtbrief nicht den Absender oder jemand anderen als Zahler nennt. Ein Standardpaket darf höchstens 30 kg wiegen.

## SAMUS-Sendung verspätet, nicht zugestellt oder verloren

**Kein Update.** Prüfe zuerst das Tracking auf samus.lt. Ist der Status unklar oder fehlen Angaben, bittet SAMUS um eine E-Mail an info@samus.lt oder einen Anruf unter +370 602 33354, um nach der Sendung zu suchen. Bei verspäteter Zustellung in Litauen erstattet SAMUS laut Bedingungen dem Auftraggeber (Absender) das Beförderungsentgelt. Mehr in [warum die Sendungsverfolgung nicht mehr aktualisiert](guide:tracking-not-updating).

**Verloren oder beschädigt.** Nur der Absender oder eine von ihm bevollmächtigte Person kann Ansprüche gegen SAMUS geltend machen. Eine Reklamation muss binnen 6 Monaten ab Versand eingehen. SAMUS reguliert innerhalb von 30 Tagen bei Sendungen in Litauen und binnen 90 Tagen bei Auslandssendungen. Für verlorene oder beschädigte Expresspakete zahlt es bis zu 200 €. Für Sendungen ohne Verfolgung nimmt es keine Reklamationen an. Frage daher bei Verlust oder Schaden das absendende Unternehmen. Siehe auch [verlorene Pakete](guide:lost-parcel).

**Zurückgesendet.** Eine Sendung, die SAMUS nicht zustellen kann, geht ohne anderslautende Weisung an den Absender zurück. Siehe [zurück an Absender](guide:returned-to-sender).

**Zugestellt, aber nicht da.** Bei einer Firmenadresse hat eventuell ein Kollege, die Rezeption oder der Sicherheitsdienst unterschrieben. Andernfalls frage beim Absender nach. Siehe [zugestellt, aber nicht erhalten](guide:delivered-not-received).

**Streitfälle.** Laut SAMUS-Bedingungen werden Streitigkeiten nach litauischem Recht geregelt.

## SAMUS-Kontakt und Kundenservice

Angaben auf samus.lt:

- **Hauptnummer**: +370 602 33354.
- **E-Mail**: info@samus.lt für allgemeine Fragen; paieska@samus.lt für Sendungen.
- **Büro und Abholpunkt Vilnius**: Žalgirio g. 92, LT-09303 Vilnius.
- **Weitere Büros**: Partizanų g. 19 in Kaunas, Minijos g. 49-10 in Klaipėda (klaipeda@samus.lt) und Trakų g. 20 in Šiauliai.
- **Zeiten**: Die Bedingungen nennen Montag bis Freitag, 8:00 bis 17:00 Uhr (litauische Zeit). Die Kontaktseite nennt keine Öffnungszeiten.

Gib beim Schreiben die Sendungsnummer an. Für die Bestellung selbst, eine Rücksendung oder Erstattung wende dich an das absendende Unternehmen.

## Ein SAMUS-Paket mit Peek verfolgen

[Peek](/) erkennt eine SAMUS-Sendung nicht allein anhand ihrer Nummer. Eine Nummer im SAMUS-Format — zwei Buchstaben, neun Ziffern und LT — gilt für Peek als Postnummer. Beim Hinzufügen („Tracking-Dienste werden geprüft…“) fragt Peek zuerst Lietuvos paštas und Chronopost an, die dieses Format kennen, und danach Tracking-Dienste. Kennt einer davon die Sendung, zeigt die Liste das an; nennt ein Dienst SAMUS mit echten Bewegungs-Scans, wählt Peek SAMUS. Findet keiner etwas, meldet die Liste „Bei den Tracking-Diensten noch nicht gefunden. Peek prüft nach dem Hinzufügen weiter.“.

Um die Sendung unter SAMUS zu verfolgen, tippe auf „Ändern“ und gib „SAMUS“ in „Paketdienste durchsuchen“ ein. Auch „UAB SAMUS“ findet den Dienst. Unter „Passt zu dieser Nummer“ erscheint er nicht. Bei einer bereits hinzugefügten Sendung tippe auf „Paketdienst ändern“.

Du kannst auch einen Link vom litauischen SAMUS-Tracker einfügen, der auf `/siuntu-sekimas/?id=` gefolgt von der Nummer endet. Peek liest dann die Nummer aus und wählt SAMUS („Erkannter Paketdienst“). Ein Link vom englischen Tracker (`/en/parcel-tracking/?id=`) benennt SAMUS gegenüber Peek nicht: Peek liest die Nummer nur, wenn sie die Postform hat, und behandelt sie dann als Postnummer. Füge stattdessen die Nummer oder den litauischen Link ein.

Für eine unter SAMUS gespeicherte Sendung fragt Peek nacheinander ParcelsApp, Ship24 und 17TRACK an und behält den ersten Dienst mit Treffer; bei gültiger Prüfziffer fragt es zuletzt das Tracking des Weltpostvereins (UPU) an. Solange die Sendung in den ersten 30 Tagen in Peek unterwegs ist, prüft Peek vorab Lietuvos paštas und Chronopost; zeigt einer davon echten Fortschritt, verschiebt Peek die Sendung dorthin („Automatisch von SAMUS gewechselt“). Peek zeigt Status und Verlauf im Wortlaut des Dienstes. Er kann hinter samus.lt zurückliegen, und für manche Sendungen fehlen Daten ganz. Hast du die Wahl Peek überlassen und nennen die Dienste SAMUS mit echten Bewegungs-Scans, setzt Peek automatisch SAMUS. Solange kein Dienst die Sendung kennt, öffnet der Link in Peek den SAMUS-Tracker mit deiner Nummer; danach die Seite des Dienstes. Peek gibt weder deine IP-Adresse noch dein Land an die Dienste weiter.

Bis ein Dienst die Nummer kennt, zeigt eine unter SAMUS gespeicherte Sendung „Warten auf SAMUS“. Sobald ein Dienst sie führt, prüft Peek stündlich, alle 3 Stunden sobald ein Tag ohne neuen Scan vergeht, nach sieben Tagen ohne Neuigkeiten bei „Etikett erstellt“ alle 6 Stunden, und bis zu alle 15 Minuten am Tag der Zustellung, solange du sie geöffnet hast oder Benachrichtigungen aktiviert sind. Ein Dienst ohne Treffer wird einen Tag später erneut gefragt.

Bleibt eine Sendung vier Tage lang ohne neues Update auf „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“, zeigt Peek „Seit vier Tagen keine Aktualisierung“ (oder „Keine Aktualisierung seit {{date}}“) an, zusammen mit dem Hinweis „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei SAMUS nach.“ und dem Link „Hol dir Hilfe bei SAMUS“.

Peek kann dich über „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“ benachrichtigen. Es zeigt keinen Abholcode und keinen Unterzeichner an und kann weder SAMUS noch den Absender für dich kontaktieren.

## Fragen zur SAMUS-Sendungsverfolgung

### Warum fragt Peek bei meiner SAMUS-Nummer Lietuvos paštas an?

Weil SAMUS-Nummern genau das Format litauischer Postnummern haben und Peek sie nicht unterscheiden kann. Tippe auf „Ändern“ und suche nach „SAMUS“, oder füge den Link vom litauischen SAMUS-Tracker ein.

### Wo liegt der SAMUS-Abholpunkt in Vilnius?

In der Žalgirio g. 92, LT-09303 Vilnius, im Innenhof unter dem Schild „Siuntų išdavimo vieta“. Die Busse 1G, 53, 10 und 34 halten wenige Gehminuten entfernt.

### Wie lange bewahrt SAMUS einen verpassten Brief auf?

Briefe bleiben nach der Benachrichtigung 14 Werktage beim Kurier und gehen dann an den Absender zurück. Expresspakete warten 3 Tage kostenlos im Terminal.

### Kann ich als Empfänger bei SAMUS reklamieren?

Nein. Nur der Absender oder eine von ihm bevollmächtigte Person kann innerhalb von 6 Monaten ab Versand reklamieren. Wende dich an das absendende Unternehmen.

### Wie erreiche ich SAMUS?

Telefonisch unter +370 602 33354 oder per E-Mail an info@samus.lt (paieska@samus.lt für Sendungen), Montag bis Freitag, 8:00 bis 17:00 Uhr (litauische Zeit).

:::sources
- [SAMUS: Laiškai ir siuntos](https://www.samus.lt/) – Briefe und Pakete, Selbstbedienungsportal
- [SAMUS: Kompanija](https://www.samus.lt/kompanija/) – seit 2011 auf dem litauischen Markt
- [SAMUS: Kurjerių paslaugos](https://www.samus.lt/paslaugos/kurjeriu-paslaugos/) – Briefe, persönliche Übergabe gegen Unterschrift, Expresspakete
- [SAMUS: Parcel tracking](https://www.samus.lt/en/parcel-tracking/) – Trackingformular, Nicht-gefunden-Meldung, litauische Statusmeldungen
- [SAMUS: Siuntų sekimas](https://www.samus.lt/siuntu-sekimas/) – litauischer Tracker, Link mit `id`, Statusliste
- [SAMUS: Frequently asked questions](https://www.samus.lt/en/information/faq/) – Nummer auf Etikett, Lieferzeiten, Zusatzleistungen, Adressänderung, Schäden, Ausland, Verspätungen
- [SAMUS: Contacts](https://www.samus.lt/en/contacts/) – Telefon, E-Mails, Abholpunkt Vilnius, Büros
- [SAMUS: Paslaugų teikimo taisyklės](https://www.samus.lt/informacija/taisykles/) – Zeiten, Lieferzeiten, Unterschrift, Benachrichtigungen, Lagerung, Schäden, Zölle, Reklamationen, Streitfälle (auf Litauisch)
- [SAMUS: SAMUS API v2.12](https://api-documentation.samus.lt/) – Nummern- und Strichcodeformat, auf Etikett gedruckter Strichcode
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – kein eigenes SAMUS-Nummernformat, Post-Erkennung von S10-Nummern, Tracker-Links, Namen, Tracking-Dienste
:::
