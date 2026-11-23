---
title: Metropolitan Warehouse (MWD) Sendungsverfolgung
description: Metropolitan Warehouse & Delivery (MWD) Möbellieferung verfolgen: 12-stellige Nummer, Statusmeldungen, Liefertermin, 72-Stunden-Schadensfrist und Kontakt.
slug: metropolitan-warehouse-sendungsverfolgung
published: 2026-11-23
updated: 2026-11-23
---

Metropolitan Warehouse & Delivery (MWD) ist ein 1989 in Perth Amboy, New Jersey, gegründeter Familienbetrieb für Möbellieferungen und 3PL-Logistik. Über 47 Hubs und Cross-Docking-Standorte liefert MWD für Online-Shops, Möbelhändler und Interior-Designer in die 48 zusammenhängenden US-Bundesstaaten. Gib die 12-stellige Sendungsnummer auf der [MWD-Trackingseite](https://www.gomwd.com/track-a-shipment) ein oder füge sie oben in das Feld ein.

## MWD Sendungsnummer: 12 Ziffern

Der [MWD-Tracker](https://main.gomwd.com/client/wixtrackorder) hat genau ein Feld, „Tracking #“, und akzeptiert ausschließlich 12 Ziffern (Ziffern erfunden):

| Nummer | Was sie bedeutet |
| --- | --- |
| `584927163048` | Die MWD-Sendungsnummer. Auf der Bestellseite steht sie in Vierergruppen: `5849 2716 3048`. |
| `POMS #`, `Client REF# 1`, `Client REF# 2`, `Carrier PRO #` | Weitere Referenzen auf der MWD-Bestellseite (intern, vom Shop oder von einem Partnerdienst). Damit lässt sich nicht tracken. |

Der Tracker verlangt weder Postleitzahl noch Telefonnummer, nutzt aber reCAPTCHA und kann zur Eingabe eines Text-Captchas auffordern. Alles andere als 12 Ziffern ergibt `Please enter a valid tracking number of 12 digits.`; eine unbekannte Nummer meldet `Tracking id does not exist.`. Prüfe die Nummer in der Bestell-E-Mail oder frag beim Händler nach ([Sendungsnummer finden](guide:find-tracking-number)). Da MWD auch gebrandete Trackingseiten für Händler anbietet, schickt dein Shop dir eventuell einen Link unter eigenem Namen.

## Was die MWD-Statusmeldungen bedeuten

Der MWD-Tracker listet Ereignisse auf Englisch, neueste zuerst, mit Datum, Beschreibung sowie Ort und Bundesstaat. Die in [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/metropolitan-warehouse) erfassten Meldungen in üblicher Reihenfolge:

| MWD-Meldung | In Peek | Was das für dich bedeutet |
| --- | --- | --- |
| `Order registered` | Etikett erstellt | Der Auftrag ist bei MWD registriert. |
| `Pickup document attached` | keine Änderung | Dokument für die Abholung angehängt; noch keine Bewegung. |
| `Pickup scheduled for 11/02/2026` | Etikett erstellt | Abholung beim Hersteller/Händler geplant; MWD hat das Möbelstück noch nicht. |
| `Pickup completed, No exceptions` | Beim Paketdienst eingegangen | MWD hat die Ware ohne Beanstandung abgeholt. |
| `Order enroute to Metro-Hub` | Unterwegs | Auf dem Weg zu einem MWD-Hub. |
| `Furniture Received by Metro-Hub` | Unterwegs | In einem MWD-Hub gescannt (mit Ortsname). |
| `Reached Delivery Hub` | Unterwegs | Im ausliefernden Hub eingetroffen. |
| `Delivery Scheduled for WEEK OF 11/09/2026` | keine Änderung | Lieferung für diese Woche eingeplant; genauer Tag folgt mit dem Termin. |
| `Delivery Rescheduled for WEEK OF 11/16/2026` | keine Änderung | Lieferung in eine andere Woche verschoben. |
| `Delivery Appointment Canceled` | keine Änderung | Termin storniert; meist folgt ein neuer, sonst beim Shop oder MWD nachfragen. |
| `Arrived at Delivery Location` | In Zustellung | Das Lieferteam ist an deiner Adresse eingetroffen. |
| `Delivery Completed, No Exceptions` | Zugestellt | Ohne Beanstandung zugestellt (trotzdem sofort auf Schäden prüfen). |

Auf der MWD-Seite steht manchmal der Platzhalter `Metro-<Warehouse>-Hub` ohne Namen; Peek kürzt das zu `Metro-Hub`. Eine eigene Meldung „in Zustellung“ beim Losfahren kennt der Verlauf nicht: Der erste Eintrag am Liefertag ist `Arrived at Delivery Location`, wenn das Team bereits vor der Tür steht.

## Termin, Servicelevel und verpasste Zustellung

**Liefertermin und Live-Tracking.** Laut den Seiten [White Glove Delivery](https://www.gomwd.com/services/white-glove-delivery) und [Services](https://www.gomwd.com/services) buchst du dein Zeitfenster per Self-Service, bekommst automatische Erinnerungen und am Liefertag einen Live-Link mit aktuellem Standort des Teams und geschätzter Ankunftszeit. Die MWD-Trackingseite zeigt außerdem Zeitfenster und Zustellnachweis.

**Was das Lieferteam übernimmt.** Das richtet sich nach dem vom Shop gebuchten Servicelevel (unter „Service Type“ auf der MWD-Seite):

| Servicelevel | Leistung des Teams |
| --- | --- |
| Full White Glove | Tragen in den Wunschraum, leichte Montage und Mitnahme der gesamten Verpackung. |
| White Glove (No Assembly) | Dasselbe für vormontierte Möbel und Teppiche, ohne Montage. |
| Room of Choice | Tragen ins Haus in den gewünschten Raum, ohne Montage. |
| Threshold Delivery | Abstellen im ersten trockenen Bereich (Eingang oder Garage), ohne Auspacken oder Montage. |
| Doorstep Delivery | Abstellen vor der Außentür ohne Unterschrift, für leichtere Sendungen. |
| Curbside Delivery | Lieferung bis Bordsteinkante oder Einfahrt; den Rest übernimmst du. |

Fehlt Montage oder Verpackungsentsorgung in deinem Level, kläre das mit dem Shop, der die Lieferung verkauft hat.

**Nicht zu Hause.** Die Infoseiten für Empfänger erklären nicht, wie ein verpasster Termin umgebucht wird — kontaktiere MWD oder den Händler also rechtzeitig vorher. Laut den [Beförderungsbedingungen](https://sso.gomwd.com/Account/TermsandConditions) geht die Haftung bei Annahmeverweigerung oder einer Zustellung, die am Versender oder Empfänger scheitert, auf die eines Lagerhalters über: Nach versuchter Benachrichtigung können ab dem nächsten Werktag Lagergebühren anfallen; bleiben Weisungen 48 Stunden aus, folgt eine zweite und letzte Mitteilung, und nach weiteren 10 Tagen darf MWD die Sendung öffentlich verkaufen. Siehe auch [verpasste Zustellung](guide:missed-delivery).

## Beschädigte, verspätete oder fehlende MWD-Lieferung

**Beschädigt.** Prüfe jedes Teil, bevor das Team geht. Die [Beförderungsbedingungen](https://sso.gomwd.com/Account/TermsandConditions) im Kundenportal setzen eine Frist von **72 Stunden** nach Zustellung für schriftliche Verlust- oder Schadensmeldungen und erlauben MWD Reparatur oder Ersatz. Ohne anderslautenden Vertrag oder gebuchte *Enhanced Valuation* ist die Haftung standardmäßig auf 500 USD pro Sendung begrenzt; verdeckte Schäden an vorverpackter Ware sind ausgeschlossen, sofern die Verpackung keine sichtbaren Beschädigungen aufweist. Da der Shop MWD-Kunde ist, melde Schäden sofort auch dort. Mehr unter [beschädigtes Paket](guide:damaged-parcel).

**Verspätet.** Solange der Verlauf bei `Pickup scheduled for` steht, wurde die Ware noch nicht abgeholt. Nach dem [FTC-Leitfaden zur Mail, Internet, or Telephone Order Merchandise Rule](https://www.ftc.gov/business-guidance/resources/business-guide-ftcs-mail-internet-or-telephone-order-merchandise-rule) gilt eine Bestellung erst mit Übergabe an den Spediteur als versandt: Kann ein Händler nicht innerhalb der zugesagten Frist — oder ohne Angabe binnen **30 Tagen** — versenden, muss er deine Zustimmung zur Verzögerung einholen und dir eine kostenlose Stornierung mit rascher voller Rückerstattung anbieten. Bleibt der Verlauf Tage nach der Abholung stehen, hilft [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating).

**Nicht zugestellt oder verschwunden.** Dieselben Bedingungen gewähren **30 Tage** nach Ablauf einer angemessenen Lieferzeit für eine schriftliche Nichtzustellungs-Reklamation (und 6 Monate Klagefrist nach schriftlicher Ablehnung). Wende dich an Shop und MWD und lies [als zugestellt markiert, aber nicht erhalten](guide:delivered-not-received) sowie [verlorenes Paket](guide:lost-parcel).

## Metropolitan Warehouse & Delivery kontaktieren

Laut der [MWD-Kontaktseite](https://www.gomwd.com/contact-us):

- **Support-Telefon:** (800) 300-7417 — Montag bis Freitag erreichbar (ohne veröffentlichte Uhrzeiten).
- **Allgemeine E-Mail:** `comments@gomwd.com`.
- **Standorte:** Hauptsitz in Perth Amboy, New Jersey (laut [About Us](https://www.gomwd.com/about-metropolitan) und [Services](https://www.gomwd.com/services)), sowie das auf der Kontaktseite genannte Corporate Office: 310 Ramco Street, Suite 150, West Sacramento, CA 95691.

Die Option „Info About My Order“ auf der Kontaktseite richtet sich an bestehende Geschäftskunden (Händler); wende dich für Reklamationen an das Customer-Success-Team am besten über deinen Shop.

## MWD-Bestellung mit Peek verfolgen

[Peek](/) erkennt MWD nicht allein am Format, da zwölf Ziffern zu vielen Paketdiensten passen ([warum Nummernformate sich überschneiden](guide:tracking-number-formats)). Füge die Nummer trotzdem ein: Peek verfolgt mehr als 4.500 Paketdienste. **In den USA** fragt Peek MWD unter den ersten 12-Ziffern-Diensten ab — neben [BRT](carrier:brt), [Colis Privé](carrier:colis-prive) und [Purolator](carrier:purolator) (bei Ziffern 0–6 am Anfang und gültiger Prüfziffer) — und zeigt „Metropolitan Warehouse & Delivery hat dieses Paket.“, sobald MWD den Auftrag kennt. **Außerhalb der USA** behandelt Peek MWD als letzte Option: Es fragt zuerst die anderen infrage kommenden Paketdienste und wendet sich erst an MWD, wenn keiner die Nummer kennt. Gibst du die Nummer im Feld auf dieser Seite ein, fragt Peek zuerst MWD, egal wo du bist. Oder wähle Metropolitan Warehouse & Delivery beim Hinzufügen über „Ändern“ oder später über „Paketdienst ändern“ (die Suche nach „MWD“ findet ihn ebenfalls). Leerzeichen stören nicht; füge die Ziffern direkt ein, da Peek keine Link-Regel für MWD hat.

Peek liest den MWD-Tracker direkt aus: Status, Verlauf in MWD-Worten, Ort und Bundesstaat auf der Karte sowie das Gewicht, je nach Einstellung in Kilogramm oder Pfund. Jede Uhrzeit zeigt Peek in der Zeitzone deines Geräts und nennt diese unter dem Verlauf. Nicht übernommen werden deine Ziel-PLZ, Bestell- und Kundenreferenzen, die PRO-Nummer eines Partnerdienstes, Stückzahl, Volumen sowie die Fortschrittsleiste. Voraussichtliches Lieferdatum, Zustellnachweis und Live-Standort des Teams zeigt Peek nicht, und Partner-Spediteure werden nicht weiterverfolgt.

Peek prüft direkt bei MWD bis zu alle 10 Minuten, solange du die Sendung geöffnet hast oder am Liefertag mit aktivierten Benachrichtigungen, und alle 2 Minuten ab 6 Uhr Ortszeit an dem Tag, an dem `Arrived at Delivery Location` erscheint; sonst stündlich. Nach 12 Stunden bei „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ oder nach zwei Tagen ohne neuen Scan fragt Peek auch bei geöffneter Ansicht stündlich ab, und nach sieben Tagen weiterhin bei „Etikett erstellt“ alle 6 Stunden. Über Tracking-Dienste prüft Peek stündlich, nach einem Tag ohne Updates alle 3 Stunden und am Liefertag bis zu alle 15 Minuten.

Bleibt ein Auftrag bei „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“ vier Tage lang ohne neuen Scan, markiert Peek ihn mit dem Tag dieses letzten Scans (etwa „Keine Aktualisierung seit Mo 12. Okt“), zeigt „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei Metropolitan Warehouse & Delivery nach.“ und zeigt den Link „Hol dir Hilfe bei Metropolitan Warehouse & Delivery“ zur Kontaktseite von MWD. Nach über drei Wochen ohne Neuigkeiten erscheint: „Seit über drei Wochen nichts Neues. Ist das Paket noch nicht angekommen, frag beim Verkäufer nach. Peek fragt weiter bei Metropolitan Warehouse & Delivery nach.“

Für Benachrichtigungen stehen „Jeden Scan“, „Wichtige Schritte“ und „Nur die Zustellung“ bereit — letztere startet bei MWD mit `Arrived at Delivery Location`. Termine buchen oder ändern, Schäden reklamieren oder MWD bzw. den Shop kontaktieren kann Peek nicht.

## Fragen zur Metropolitan Warehouse Sendungsverfolgung

### Was ist Metropolitan Warehouse & Delivery?

Ein familiengeführtes US-Unternehmen für Möbellogistik und 3PL-Lagerung mit Sitz in Perth Amboy, New Jersey, seit 1989 und 47 Hubs in den 48 zusammenhängenden US-Staaten. Händler, Möbelmarken und Designer beauftragen MWD; Privatpersonen buchen nicht direkt.

### Was bedeutet „Delivery Scheduled for WEEK OF“?

MWD hat die Zustellung für die Woche dieses Datums eingeplant. Der genaue Tag und das Zeitfenster folgen bei der Terminbuchung. `Delivery Rescheduled for WEEK OF` bedeutet eine Verschiebung in eine andere Woche.

### Montiert Metropolitan Warehouse Möbel?

Nur im Tarif *Full White Glove*, der leichte Montage und Verpackungsentsorgung umfasst. Alle anderen Stufen — auch *White Glove (No Assembly)* und *Room of Choice* — enthalten keine Montage. Dein Tarif steht unter „Service Type“ auf der MWD-Bestellseite.

### Wie verschiebe ich einen MWD-Liefertermin?

Die MWD-Seiten beschreiben die erstmalige Self-Service-Terminbuchung, aber keine Umbuchung. Ruf Montag bis Freitag die Support-Nummer (800) 300-7417 an oder wende dich an den Händler.

### Wie lange habe ich Zeit, Schäden an MWD zu melden?

Die Beförderungsbedingungen im Kundenportal setzen eine Frist von 72 Stunden nach Zustellung für schriftliche Verlust- oder Schadensmeldungen. Informiere zugleich sofort den verkaufenden Shop.

:::sources
- [Metropolitan Warehouse & Delivery: Startseite](https://www.gomwd.com/) – seit 1989, Support-Telefon
- [MWD: About Us](https://www.gomwd.com/about-metropolitan) – Familienunternehmen, Hauptsitz Perth Amboy, 48 zusammenhängende Bundesstaaten
- [MWD: Track a Shipment](https://www.gomwd.com/track-a-shipment) – Sendungsverfolgung, Lieferfenster und Zustellnachweis
- [MWD: Track Your Order](https://main.gomwd.com/client/wixtrackorder) – 12-stelliges Eingabefeld, Fehlermeldung, reCAPTCHA / Text-Captcha
- [MWD: White Glove Delivery](https://www.gomwd.com/services/white-glove-delivery) – Servicelevel, Self-Service-Terminbuchung, Erinnerungen und Live-Tracking
- [MWD: Services](https://www.gomwd.com/services) – Doorstep Delivery, 47 Hubs und Cross-Docks, 5 volle 3PL-Hubs, gebrandete Trackingseiten
- [MWD: Contact Us](https://www.gomwd.com/contact-us) – Telefon (800) 300-7417 Mo–Fr, comments@gomwd.com, Corporate Office West Sacramento, Geschäftskunden-Kontakt
- [MWD: Terms & Conditions](https://sso.gomwd.com/Account/TermsandConditions) – 72-Stunden-Schadensfrist, 30-Tage-Frist bei Nichtzustellung, 500-USD-Haftungsgrenze, Lagerkosten bei Nichtannahme
- [FTC: Business Guide to the Mail, Internet, or Telephone Order Merchandise Rule](https://www.ftc.gov/business-guidance/resources/business-guide-ftcs-mail-internet-or-telephone-order-merchandise-rule) – Versanddefinition, 30-Tage-Regel, Zustimmung zu Verzögerungen und Rückerstattung
- [Universal Parcel Scraper: Metropolitan Warehouse & Delivery](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/metropolitan-warehouse) – 12-Ziffern-Regel, unbekannte Nummern, Statusliste und von Peek gelesene Felder
:::
