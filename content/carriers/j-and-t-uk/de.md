---
title: J&T Express UK Sendungsverfolgung: JTUK-Nummern und Status
description: J&T Express UK-Paket verfolgen: Aufbau der JTUK-Nummer, Bedeutung der Statuszeilen, Einfuhrregeln im Vereinigten Königreich und Hilfe bei Verzögerung.
slug: jt-express-uk-sendungsverfolgung
published: 2026-11-21
updated: 2026-11-21
---

J&T Express UK stellt Pakete im Vereinigten Königreich zu. Hat ein Shop deine Bestellung mit J&T verschickt, beginnt die Sendungsnummer mit `JTUK`: Füge sie oben ein oder prüfe sie auf dem eigenen Tracker von J&T unter www.jtexpress.uk/tracking. Die Nummer findest du in der Versandmail oder auf deiner Bestellseite. Da Peek die Website von J&T Express UK nicht auslesen konnte, stützt sich diese Seite auf Peeks Code-Wissen über den J&T-Tracker und auf offizielle britische Quellen.

## J&T-Express-UK-Sendungsnummer: So sieht sie aus

Eine Sendungsnummer von J&T Express UK besteht aus `JTUK` und 12 Ziffern, insgesamt 16 Zeichen. Peek kennt kein anderes Format dafür. Erfundene Beispiele:

| Nummer (erfunden) | Was es ist |
| --- | --- |
| `JTUK482910573628` | `JTUK` und 12 Ziffern: ein Paket von J&T Express UK. Peek erkennt J&T Express UK sofort. |
| `JTUK48291057362` | Eine Ziffer zu kurz: passt nicht zu J&T Express UK, und Peek erkennt keinen Paketdienst. Prüfe auf eine fehlende Ziffer. |
| `JTDE482910573628` | `JTDE` und 12 Ziffern: [J&T Express Germany](carrier:j-and-t-de), nicht das britische Netz. |
| `JTAU482910573628` | `JTAU` und 12 Ziffern: [J&T Express Australia](carrier:j-and-t-au). |
| `JTE482910573628` | `JTE` und 12 Ziffern: [J&T Express Middle East](carrier:j-and-t-middle-east). |
| `JT4829105736284` | `JT` und 13 Ziffern: J&T-Netze in Südostasien und China ([J&T Express China](carrier:j-and-t-cn)). |

Auf peektracker.com erhält eine `JTUK`-Nummer, der ein Zeichen fehlt oder die eines zu viel hat, einen Hinweis wie: „Das beginnt wie eine Sendungsnummer von J&T Express UK, aber ein Zeichen fehlt. Vielleicht ist es ein Tippfehler: Vergleiche die Nummer mit deiner Versandnachricht.“ Leerzeichen, Bindestriche und Kleinschreibung spielen in Peek keine Rolle: `jtuk 4829 1057 3628` wird wie die erste Zeile gelesen. Im Peek-Katalog steht der Dienst auch unter J&T Express (UK), J&T Express UK Ltd und J&T UK.

**Wo du sie findest.** In der Versandmail des Shops, auf der Bestellseite des Händlers oder Marktplatzes oder in einer Lieferbenachrichtigung. Falls du sie nicht findest, hilft [Sendungsnummer finden](guide:find-tracking-number) oder eine Nachfrage beim Shop.

## J&T-Express-UK-Statusmeldungen erklärt

Der Tracker von J&T zeigt zu jedem Scan eine Uhrzeit und einen Satz, ohne Ortsangabe. Hier sind die im Peek-Code bekannten Sätze von J&T Express UK in der Reihenfolge, in der ein Paket sie durchläuft:

| Meldung von J&T | Was das für dich bedeutet | Peek zeigt |
| --- | --- | --- |
| `The parcel has been successfully picked up and is being prepared for transportation` | J&T hat das Paket beim Absender übernommen und bereitet den Transport vor. | „Beim Paketdienst eingegangen“ |
| `The parcel arrives at the distribution center and is prepared for transport to the next destination` | In einem J&T-Sortierzentrum eingetroffen. | „Unterwegs“ |
| `The parcel is dispatched from the transit center to the next destination` | Hat das Zentrum Richtung nächster Station verlassen. | „Unterwegs“ |
| `The parcel has been collected and is on its way` | Der Zusteller hat das Paket für die Zustelltour übernommen. Trotz des Wortlauts ist das nicht die erste Abholung: J&T verbucht es als Zustellscan. | „In Zustellung“ |
| `The parcel has been successfully delivered to the recipient` | Zugestellt. | „Zugestellt“ |
| `The delivery address is incomplete or incorrect.` | Zustellung fehlgeschlagen: Die Adresse war unvollständig oder fehlerhaft. In den Verläufen im Peek-Code erschien dies Stunden nach dem Start der Tour am selben Tag. | „Zustellversuch“ |
| `Delivery was attempted, but no one was available to receive the package at the address; the package was taken to a pickup location for the customer to collect.` | Niemand war zur Annahme da; das Paket wurde zur Abholung an einen Abholort gebracht. | „Zustellversuch“ |

Ein Satz außerhalb dieser Tabelle folgt Peeks allgemeinen Regeln: Erkennt Peek ihn nicht, erscheint er im Wortlaut von J&T als „Unterwegs“, außer das Paket ist bereits in Zustellung oder weiter – dann bleibt der bisherige Status stehen.

Endet ein Satz mit einer Kundenservice-Nummer oder einer Aufforderung zum Anruf, lässt Peek das weg. Ebenso entfernt es Texte in Klammern, Telefonnummern sowie Namen von Unterzeichnern, Empfängern, Fahrern oder Absendern und behält den Rest.

## J&T-Express-UK-Zustellung: Was das Tracking verrät

In den Verläufen im Peek-Code nimmt der J&T-Zusteller das Paket mit auf Tour und stellt es entweder zu, meldet ein Adressproblem oder bringt es nach erfolglosem Versuch an einen Abholort. Eine Übergabe an einen anderen Paketdienst zeigt Peeks Code nicht: J&T verfolgt das Paket bis zur Tür.

**In Zustellung.** `The parcel has been collected and is on its way` bedeutet, dass dein Paket auf der heutigen Tour ist. Da J&T weder Zeitfenster noch voraussichtlichen Liefertag liefert, kann Peek keinen anzeigen.

**Adressproblem.** Nach `The delivery address is incomplete or incorrect.` prüfe sofort die Bestelladresse. Fehlt etwas (etwa Wohnungsnummer oder Postleitzahl), melde es umgehend dem Shop, der den Versand gebucht hat. Siehe [Lieferadresse ändern](guide:change-delivery-address) und [verpasste Zustellung](guide:missed-delivery).

**Versuche, Ablageorte und Abholstellen.** Da Peek die Website von J&T Express UK nicht auslesen konnte, macht diese Seite keine Angaben zur Zahl der Zustellversuche, zu Nachbar- oder Ablage-Regeln oder zur Lagerfrist. Frage den Shop oder prüfe deine Lieferbenachrichtigungen.

**Bestellungen von außerhalb des Vereinigten Königreichs.** Laut GOV.UK meldet sich das Zustellunternehmen bei dir, falls Einfuhrumsatzsteuer (VAT), Zoll oder Gebühren anfallen, und schickt eine genaue Rechnung. Normalerweise bewahrt es das Paket etwa 3 Wochen auf und schickt es bei Nichtzahlung zurück. Bei selbst gekauften Waren bis einschließlich 135 £ (außer verbrauchsteuerpflichtigen Waren wie Alkohol, Tabak oder E-Zigaretten) hat der Verkäufer die VAT bereits im Preis berücksichtigt. Über 135 £ zahlst du VAT sowie eventuellen Zoll vor Lieferung oder bei Abholung an das Zustellunternehmen. Für Nordirland gelten eigene Regeln. Wie J&T Einfuhrabgaben abrechnet, konnte Peek nicht prüfen. Mehr in [Paket beim Zoll](guide:customs).

## J&T-Express-UK-Paket verspätet, verloren oder zurückgeschickt

Der Shop hat J&T beauftragt und ist daher bei Online-Bestellungen dein erster Ansprechpartner. Nach Section 29 des Consumer Rights Act 2015 bleiben bei einem Unternehmer gekaufte Waren auf Risiko des Verkäufers, bis sie dir oder einer von dir benannten Person physisch übergeben wurden – es sei denn, du hast selbst einen Paketdienst beauftragt, den der Verkäufer nicht angeboten hatte.

**Keine Bewegung.** Zeigt ein Paket auf „Beim Paketdienst eingegangen“ oder „Unterwegs“ vier Tage keinen neuen Eintrag, weist Peek mit dem Tag des letzten Scans darauf hin („Keine Aktualisierung seit …“) und verlinkt auf die Kontaktseite von J&T Express UK („Hol dir Hilfe bei J&T Express UK“). Tut sich danach nichts, melde dich beim Shop. Siehe [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating).

**Noch kein Tracking.** Eine Nummer, die J&Ts Tracker noch nicht kennt, steht auf „Warten auf J&T Express UK“. Ein Shop kann die Nummer schon vor der Übergabe erstellen. Kennt J&T sie zwei Tage später noch immer nicht und hat auch keine andere Quelle geantwortet, zeigt Peek „Auch nach zwei Tagen kennt der Paketdienst diese Nummer nicht. Prüfe sie, wähle einen anderen Paketdienst oder lösche das Paket.“ und prüft danach täglich, nach einer Woche wöchentlich. Prüfe die Nummer beim Shop und frage, ob wirklich verschickt wurde.

**Zugestellt, aber nichts angekommen.** Schau rund um die Tür und frage Haushalt und Nachbarn, danach melde dich beim Shop: Laut jenem Gesetz trägt der Verkäufer das Risiko bis zur Übergabe – verlange Erstattung oder Ersatz. Siehe [Paket zugestellt, aber nicht erhalten](guide:delivered-not-received).

**Verloren oder beschädigt.** Bitte den Shop mit deiner Sendungsnummer um Erstattung oder Ersatz. J&Ts eigene Reklamationsregeln konnte Peek nicht einsehen; siehe [verlorenes Paket](guide:lost-parcel) und [beschädigtes Paket](guide:damaged-parcel).

**Zurückgesandt.** Geht das Paket nach fehlgeschlagener Zustellung an den Shop zurück, klärt der Shop Erstattung oder Neuversand. Siehe [zurück an Absender](guide:returned-to-sender).

## J&T Express UK Kundenservice und Kontakt

Da Peek die Website von J&T Express UK nicht auslesen konnte, nennt diese Seite weder Telefonnummer noch E-Mail oder Chat: Peek führt nur Kontakte auf, die auf den Seiten des Paketdienstes selbst geprüft wurden. Companies House nennt als eingetragenen Sitz `C/O Tmf Group 13th Floor, One Angel Court, London, United Kingdom, EC2R 7HJ` – die Rechtsanschrift bei einem Dienstleister, keinen Paketschalter.

Wende dich zuerst an den Shop, der den Versand gebucht hat, oder nutze bei Marktplatz-Bestellungen die Hilfe auf der Bestellseite. J&Ts eigener Tracker ist unter www.jtexpress.uk/tracking erreichbar.

## Ein J&T-Express-UK-Paket mit Peek verfolgen

[Peek](/) erkennt J&T Express UK sofort an einer `JTUK`-Nummer („Erkannter Paketdienst“) und fragt J&Ts eigenen Tracker allein mit der Nummer ab – ohne Postleitzahl oder Konto. Ein Link auf www.jtexpress.uk/tracking mit `?q=` und der Nummer funktioniert ebenfalls; enthält er eine andere Nummer, behandelt Peek sie so, als hättest du sie direkt eingefügt. Auch jede andere Nummer kannst du einfügen: Peek verfolgt über 4500 Paketdienste. Bei einer 13-stelligen `JT`-Nummer fragt Peek für Nutzer in China [J&T Express China](carrier:j-and-t-cn) an; anderswo zeigt es „Tracking-Dienste werden geprüft…“, während es nachfragt. Für ein anderes Format J&T Express UK über „Ändern“ zu wählen hilft nicht: Peek meldet, J&T „vergibt keine Nummern wie diese“.

Peek zeigt Status, jeden Scan mit exakter Uhrzeit und die Zustellzeit. Da J&T jeden Scan als genauen Zeitpunkt übermittelt, zeigt Peek alle Zeiten sommers wie winters in der Zeitzone deines Geräts; auf peektracker.com und in den iPhone- und Android-Apps endet der Verlauf mit „Uhrzeiten in deiner Zeitzone:“ und deren Namen. Die Texte bleiben in J&Ts Englisch. Da J&T weder Ort noch Lieferdatum oder Gewicht angibt, gibt es keine Karte. Einen Zustellnachweis des J&T-Trackers lässt Peek weg. Über „Website von J&T Express UK öffnen“ auf der Paketseite gelangst du direkt zu deiner Nummer im J&T-Tracker.

Bis J&T das Paket scannt, fragt Peek weiter nach und zeigt „Warten auf J&T Express UK“ (oder nach zwei Tagen die Bitte zur Prüfung). Ist J&Ts Tracker nicht erreichbar oder kennt die Nummer nicht, fragt Peek Tracking-Dienste und zeigt deren Verlauf in deren Worten.

Solange du das Paket geöffnet hast oder am Zustelltag mit aktivierten Mitteilungen, und solange J&Ts eigener Tracker antwortet, prüft Peek bis zu alle 10 Minuten, und ab 6 Uhr Ortszeit am Tag, an dem J&T das Paket in Zustellung scannt, bis zu alle 2 Minuten; sonst stündlich. Über Tracking-Dienste prüft Peek stündlich (nach einem Tag ohne Neuigkeiten alle 3 Stunden), am Zustelltag bis zu alle 15 Minuten, solange du es geöffnet hast oder Mitteilungen aktiv sind. Nach zwei Tagen ohne neuen Scan (nach 12 Stunden auf „Beim Paketdienst eingegangen“) prüft Peek stündlich. Mitteilungen gibt es für „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“. Peek kann weder J&T noch den Shop kontaktieren, die Adresse ändern oder eine neue Zustellung buchen.

## Fragen zur J&T-Express-UK-Sendungsverfolgung

### Ist J&T Express UK seriös?

Ja. Companies House führt `J&T EXPRESS UK LTD` als aktive Private Limited Company, gegründet am 18. Dezember 2025, mit eingetragenen Geschäftszwecken wie Straßengüterverkehr, Lagerhaltung und Postdiensten. Die Sendungsnummern beginnen mit `JTUK` und Peek liest den Tracker direkt aus.

### Was bedeutet „The parcel has been collected and is on its way“ bei J&T?

Dein Paket ist in Zustellung. Der Fahrer hat es für seine Zustelltour übernommen; J&T verbucht diese Zeile als Zustellscan und Peek zeigt „In Zustellung“. Es bedeutet nicht, dass das Paket gerade erst beim Absender abgeholt wurde.

### Warum meldet J&T, meine Lieferadresse sei unvollständig oder falsch?

Der Fahrer konnte nicht zustellen, weil Angaben fehlten oder nicht passten. Prüfe die Adresse in deiner Bestellung und melde dich umgehend mit vollständiger Adresse und Sendungsnummer beim Shop.

### Warum wird meine J&T-Sendungsnummer nicht gefunden?

Prüfe, ob sie mit `JTUK` und 12 Ziffern dahinter beginnt: Nummern anderer J&T-Netze wie `JTDE` in Deutschland gehören nicht zum britischen Netz. Stimmt die Nummer, hat J&T das Paket vielleicht noch nicht erhalten und Peek zeigt „Warten auf J&T Express UK“. Kennt J&T sie nach zwei Tagen noch nicht und hat auch keine andere Quelle geantwortet, bittet Peek um Prüfung, Wechsel des Dienstes oder Löschen. Bleibt es nach mehreren Tagen leer, frage den Shop.

### Wie kontaktiere ich J&T Express UK?

Wende dich zuerst an den Shop, der die Lieferung beauftragt hat. Da Peek die Website von J&T Express UK nicht auslesen konnte, stehen auf dieser Seite keine Telefonnummer und keine E-Mail-Adresse.

:::sources
- [Universal Parcel Scraper: J&T Express UK](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/j-and-t-uk) – Namen, `JTUK`-Format, Tracking-Links, Hilfeseite `jtexpress.uk/contact`, Statussätze und Stufen (einschließlich Abholung am Abholort nach Abwesenheit), exakte Zeiten, kein Ort/Lieferdatum/Gewicht, kein Zustellnachweis
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Formate anderer J&T-Netze, Herausfiltern von Hotline-Hinweisen, Klammertexten, Namen und Telefonnummern, keine Übergabe an andere Paketdienste, Tracking-Dienste als Rückfall
- [Companies House: J&T EXPRESS UK LTD](https://find-and-update.company-information.service.gov.uk/company/16920174) – aktive Private Limited Company, gegründet am 18. Dezember 2025, Geschäftszweck, eingetragener Sitz
- [GOV.UK: Tax and customs for goods sent from abroad](https://www.gov.uk/goods-sent-from-abroad/tax-and-duty) – Rechnung vom Zustellunternehmen, rund 3 Wochen Lagerung, VAT bis 135 £ und darüber, Customs Duty, Nordirland
- [UK legislation: Consumer Rights Act 2015, section 29](https://www.legislation.gov.uk/ukpga/2015/15/section/29) – Gefahrtragung des Händlers bis zur physischen Übergabe an den Verbraucher
:::
