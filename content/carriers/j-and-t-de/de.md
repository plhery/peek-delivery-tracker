---
title: J&T Express Sendungsverfolgung: JTDE-Nummer und Status
description: J&T-Paket in Deutschland verfolgen: wie eine JTDE-Nummer aussieht, was jede Zeile der Sendungsverfolgung heißt und was du tust, wenn das Paket fehlt.
slug: jt-express-sendungsverfolgung
published: 2026-10-21
updated: 2026-10-27
---

J&T Express Germany ist das deutsche Netz von J&T Express und stellt Pakete in Deutschland zu. Hat ein Shop deine Bestellung mit J&T verschickt, beginnt die Sendungsnummer mit `JTDE`: Füg sie oben ins Feld ein oder schau in der Sendungsverfolgung von J&T unter www.jtexpress.de/tracking nach. Der Shop, der deine Bestellung verschickt hat, kennt die Nummer: Sie steht in seiner Versand-E-Mail oder in deinen Bestelldetails. Diese Seite stützt sich nicht auf die Website von J&T Express Germany, sondern auf das, was Peeks Code über die Sendungsverfolgung von J&T weiß, und auf deutsches Recht.

## J&T Sendungsnummer: wie sie aussieht und wo du sie findest

Die Sendungsnummer von J&T Express Germany besteht aus `JTDE` und 12 Ziffern, 16 Zeichen insgesamt. Eine andere Form kennt Peek für sie nicht. Mit erfundenen Ziffern:

| Nummer (erfunden) | Was sie ist |
| --- | --- |
| `JTDE000000000000` | `JTDE` und 12 Ziffern: ein Paket von J&T Express Germany. Peek erkennt J&T Express Germany sofort. |
| `JTDE00000000000` | Eine Ziffer zu wenig: keine Nummer von J&T Express Germany, und Peek erkennt keinen Paketdienst. Prüf, ob eine Ziffer fehlt. |

**Wo sie steht.** In der Versand-E-Mail des Shops, auf der Bestellseite des Shops oder Marktplatzes oder in jeder Nachricht zur Zustellung. Findest du sie nicht, lies nach, [wie du die Sendungsnummer findest](guide:find-tracking-number), oder frag den Shop.

**Ähnliche Nummern.** Die J&T-Netze anderer Länder haben eigene Sendungsverfolgungen und Nummern: `JTUK` mit 12 Ziffern gehört zu J&T Express UK, `JTAU` mit 12 Ziffern zu J&T Express Australia, `JTE` mit 12 Ziffern zu J&T Express Middle East, und `JT` mit 13 Ziffern nutzt J&T in Südostasien und in China. Nur `JTDE` gehört zum deutschen Netz. Zeigt Peek weiter „Warten auf den Paketdienst“, vergleich die Nummer mit der E-Mail des Shops oder frag den Shop.

## J&T Sendungsverfolgung: was die Status bedeuten

In der Sendungsverfolgung von J&T besteht jeder Scan aus einer Uhrzeit und einem Satz, ohne Ort. Peek fragt die Sätze auf Englisch ab und übersetzt sie nicht, auch nicht in der deutschen App. Diese Sätze zeigt Peek, in der Reihenfolge, in der ein Paket sie durchläuft:

| J&T schreibt | Was es für dich heißt | Peek zeigt |
| --- | --- | --- |
| `The shipment has been successfully picked up and is being prepared for transportation` | J&T hat das Paket beim Versender abgeholt. | „Beim Paketdienst eingegangen“ |
| `The shipment arrives at the distribution center and is prepared for transport to the next destination.` | Es ist in einem Umschlagzentrum von J&T angekommen. | „Unterwegs“ |
| `The shipment is dispatched from the transit center to the next destination` | Es hat das Zentrum Richtung nächste Station verlassen. | „Unterwegs“ |
| `Your package has been loaded and is on its way to you` | Es liegt im Zustellfahrzeug: in Zustellung. | „In Zustellung“ |
| `Delivery failed because the address could not be found` | Der Fahrer hat deine Adresse nicht gefunden. | „Zustellversuch“ |
| `Delivery failed, customer refused to accept` | Der Empfänger hat die Annahme verweigert. | „Zustellversuch“ |
| `Your package has been successfully delivered.` | Zugestellt. J&T schreibt dazu, wie (siehe unten). | „Zugestellt“ |
| `Returns have begun` | Nach einer gescheiterten Zustellung geht das Paket zurück an den Versender. | „Zurück an Absender“ |
| `The shipment has been returned` | Die Rücksendung ist abgeschlossen. | „Zurück an Absender“ |

Einen Satz, der nicht in der Tabelle steht, liest Peek nach den Regeln, die es für alle Paketdienste nutzt. Kennt Peek ihn nicht, steht er so da, wie J&T ihn schreibt, und zählt als „Unterwegs“. Ist das Paket schon in Zustellung oder weiter, bleibt der Status, wie er war.

Den Satz zum Verladen kann es mehrmals geben: J&T schreibt ihn bei jeder Zustelltour neu. Auf den Zustellsatz folgt in 【】 die Zustellart und zum Schluss ein Satz zum Kundenservice. Diesen Satz lässt Peek weg, und die Zustellart behält es ohne Klammern nur, wenn sie eine dieser sechs ist:

- `Left with neighbor`: beim Nachbarn;
- `Front Door Porch`: an der Haustür;
- `Front Desk Reception`: am Empfang;
- `Garden Yard`: im Garten oder Hof;
- `Mailbox`: im Briefkasten;
- `Garage`: in der Garage.

Ein beim Nachbarn abgegebenes Paket steht in Peek also als `Your package has been successfully delivered. The delivery type is Left with neighbor.`

**Welcher Nachbar?** Peek zeigt nur, dass das Paket bei einem Nachbarn liegt: keinen Namen und keine Adresse. Namen und Telefonnummern in einer Zeile von J&T lässt Peek weg.

## J&T Zustellung: was die Sendungsverfolgung verrät

In den Verläufen, die Peeks Code kennt, lädt der Zusteller von J&T das Paket für eine Zustelltour ein und stellt es dann zu oder meldet, warum die Zustellung gescheitert ist. Eine Übergabe an einen anderen Paketdienst zeigt Peeks Code nicht: Die Sendungsverfolgung von J&T begleitet das Paket bis zu deiner Tür.

**In Zustellung.** `Your package has been loaded and is on its way to you` heißt: Dein Paket ist auf einer Zustelltour. In diesen Verläufen kommt die Zeile Stunden vor der Zustellung, am selben Morgen. Ein Zeitfenster oder einen erwarteten Zustelltag gibt die Sendungsverfolgung von J&T nicht an, also kann Peek auch keinen zeigen.

**Verweigert oder Adresse nicht gefunden.** Nach `Delivery failed, customer refused to accept` hat der Zusteller das Paket in den Verläufen, die Peeks Code kennt, am nächsten Morgen für eine neue Tour verladen. Nach `Delivery failed because the address could not be found` prüf die Adresse in deiner Bestellung. Fehlt etwas, etwa der Name am Klingelschild oder die Wohnungsnummer, sag dem Shop sofort Bescheid: Er hat die Zustellung mit der Adresse gebucht, die du ihm gegeben hast. Auf eine gescheiterte Zustellung kann `Returns have begun` folgen. Mehr in [Lieferadresse ändern](guide:change-delivery-address) und [Zustellung verpasst](guide:missed-delivery).

**Zustellversuche, Ablageort, Abholung.** Die Website von J&T dient dieser Seite nicht als Quelle. Wie oft J&T eine Zustellung versucht, ob du einen Ablageort wählen kannst und wie lange J&T ein nicht zugestelltes Paket aufbewahrt, steht deshalb hier nicht. Keine Zeile von J&T Express Germany, die Peeks Code kennt, schickt ein Paket zum Abholen in einen Paketshop oder an einen Paketautomaten. Frag den Shop oder schau in deine Nachrichten zur Zustellung.

## J&T-Paket kommt nicht an, ist weg oder geht zurück

Den Auftrag an J&T hat der Shop erteilt, bei einem Online-Kauf ist er also deine erste Adresse. Kaufst du als Verbraucher, trägt der Händler das Transportrisiko, außer du hast den Paketdienst selbst beauftragt und der Händler hatte ihn dir nicht vorher genannt (§ 475 BGB).

**Keine Bewegung.** Zeigt ein Paket „Beim Paketdienst eingegangen“ oder „Unterwegs“ und kommt vier Tage lang keine neue Zeile, markiert Peek es („Seit vier Tagen keine Aktualisierung“) und zeigt den Link „Hol dir Hilfe bei J&T Express Germany“. Tut sich danach nichts, frag den Shop. Mehr in [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating).

**Noch keine Sendungsverfolgung.** Kennt die Sendungsverfolgung von J&T die Nummer noch nicht, zeigt Peek „Warten auf den Paketdienst“. Shops legen die Nummer oft an, bevor sie das Paket übergeben. Bleibt es mehrere Tage dabei, lass dir die Nummer vom Shop bestätigen und frag, ob das Paket wirklich verschickt wurde.

**Zugestellt, aber nicht da.** Lies den Zustellsatz in Peek: Nennt er einen Ort, etwa `Mailbox` oder `Garage`, schau dort nach. Steht dort `Left with neighbor`, frag deine Nachbarn. Immer noch nichts? Melde es dem Shop mit deiner Sendungsnummer und bitte um Erstattung oder ein neues Paket. Mehr in [Paket zugestellt, aber nicht erhalten](guide:delivered-not-received).

**Verloren, beschädigt oder zurück.** Bitte den Shop mit deiner Sendungsnummer um Erstattung oder Ersatz. Regeln von J&T für Reklamationen nennt diese Seite nicht. Mehr in [Paket verloren](guide:lost-parcel), [Paket beschädigt](guide:damaged-parcel) und [Paket zurück an den Absender](guide:returned-to-sender).

## J&T Kontakt und Kundenservice

Stand 2. November 2026 nennt diese Seite keine Telefonnummer, keine E-Mail-Adresse und keinen Chat von J&T Express Germany: Peek gibt nur Kontakte an, die auf den eigenen Seiten eines Paketdienstes stehen, und die Website von J&T dient dieser Seite nicht als Quelle.

Wende dich zuerst an den Shop: Er hat die Zustellung bei J&T gebucht. Bei einer Bestellung auf einem Marktplatz nutzt du die Hilfe auf der Bestellseite. Die Sendungsverfolgung von J&T findest du unter www.jtexpress.de/tracking. Bei einem Paket, das sich nicht mehr bewegt, öffnet der Link „Hol dir Hilfe bei J&T Express Germany“ in Peek die Kontaktseite von J&T.

## J&T-Paket mit Peek verfolgen

[Peek](/) erkennt eine `JTDE`-Nummer sofort als J&T Express Germany („Erkannter Paketdienst“), mit oder ohne Leerzeichen, groß oder klein geschrieben, und fragt die Sendungsverfolgung von J&T nur mit der Nummer ab: ohne Postleitzahl und ohne Konto. Einen Link von www.jtexpress.de/tracking mit `?q=` und der Nummer kannst du ebenfalls einfügen; steht darin eine Nummer anderer Form, liest Peek sie, als hättest du sie allein eingefügt. Hast du eine Nummer anderer Form, füg sie trotzdem ein: Peek verfolgt mehr als 3.500 Paketdienste. Wählst du für eine andere Nummer über „Ändern“ J&T Express Germany, hilft das nicht: Peek schreibt dann, J&T Express Germany „vergibt keine Nummern wie diese“.

Peek zeigt den Status, jeden Scan mit genauer Uhrzeit und den Zeitpunkt der Zustellung. J&T gibt jeden Scan als genauen Zeitpunkt an, Peek zeigt ihn also in der Zeitzone deines Geräts. Die Scans zeigt Peek im englischen Wortlaut von J&T. Orte, einen erwarteten Zustelltag oder das Gewicht gibt J&T nicht heraus, Peek zeigt also keine Karte. Bei sehr langem Verlauf behält Peek die neuesten 100 Scans. „Website von J&T Express Germany öffnen“ auf der Seite des Pakets öffnet deine Nummer in der Sendungsverfolgung von J&T.

Bis J&T das Paket scannt, zeigt Peek „Warten auf den Paketdienst“ und fragt weiter nach. Ist die Sendungsverfolgung von J&T nicht erreichbar oder kennt sie die Nummer nicht, fragt Peek bei Tracking-Diensten nach und zeigt deren Verlauf in deren Worten.

Peek schaut alle 10 Minuten nach, alle 2 Minuten, sobald das Paket in Zustellung ist. Kommt zwei Tage lang kein neuer Scan (solange es „Beim Paketdienst eingegangen“ zeigt, schon nach 12 Stunden), schaut Peek stündlich nach. Auf Wunsch schickt es dir Mitteilungen: „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“. J&T oder den Shop kann Peek nicht für dich kontaktieren, und es kann weder die Adresse ändern noch eine neue Zustellung buchen.

## Fragen zur J&T Express Sendungsverfolgung

### Ist J&T Express Germany seriös?

Es ist ein echter Paketdienst: das deutsche Netz von J&T Express, das Peeks Katalog auch als J&T Express Germany GmbH führt. Seine Sendungsverfolgung kennt `JTDE`-Nummern, und jeder Satz in der Statustabelle oben stammt aus der Sendungsverfolgung echter Pakete von J&T Express Germany. Angaben zum Unternehmen macht diese Seite nicht, weil die Website von J&T ihr nicht als Quelle dient.

### Welcher Nachbar hat mein J&T-Paket?

Das kann Peek dir nicht sagen. Steht im Zustellsatz `Left with neighbor`, zeigt Peek genau das, ohne Namen und Adresse. Frag deine Nachbarn. Hat es keiner, wende dich an den Shop.

### Hat J&T Express Paketshops in Deutschland?

Peeks Code kennt keine. Im Zustellsatz zeigt Peek nur diese Zustellarten: beim Nachbarn, an der Haustür, am Empfang, im Garten, im Briefkasten oder in der Garage. Keine Zeile von J&T Express Germany, die Peek kennt, schickt ein Paket in einen Paketshop oder an einen Paketautomaten.

### Warum findet die J&T Sendungsverfolgung meine Nummer nicht?

Prüf, ob sie mit `JTDE` beginnt und danach 12 Ziffern hat: Nummern aus anderen J&T-Netzen, etwa `JTUK` aus Großbritannien, sind keine deutschen. Stimmt die Nummer, hat J&T das Paket vielleicht noch nicht. Peek zeigt bis zum ersten Scan „Warten auf den Paketdienst“. Dauert das mehrere Tage, frag den Shop.

### Wie erreiche ich J&T Express in Deutschland?

Frag zuerst den Shop: Er hat die Zustellung gebucht. Eine Telefonnummer oder E-Mail-Adresse nennt diese Seite nicht. Bei einem Paket, das sich nicht mehr bewegt, öffnet der Link „Hol dir Hilfe bei J&T Express Germany“ in Peek die Kontaktseite von J&T.

:::sources
- [Universal Parcel Scraper: J&T Express Germany](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/j-and-t-de) – Namen, JTDE-Nummern, Tracking-Links, Sätze der Sendungsverfolgung von echten Paketen und ihre Status, Zustelltouren, Zustellarten, was Peek weglässt, Hilfeseite
- [Universal Parcel Scraper: gemeinsame Abfrage der J&T-Netze](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/j-and-t-au) – englische Sätze, genaue Scanzeiten, kein Ort, kein Zustelltag, kein Gewicht, Namen und Telefonnummern weggelassen, neueste 100 Scans
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Nummern anderer J&T-Netze, keine Übergabe an einen anderen Paketdienst, Tracking-Dienste als Rückfall
- [Gesetze im Internet: § 475 BGB](https://www.gesetze-im-internet.de/bgb/__475.html) – Transportrisiko beim Verbrauchsgüterkauf
:::
