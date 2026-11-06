---
title: J&T Express Sendungsverfolgung: JTDE-Nummer und Status
description: J&T-Paket in Deutschland verfolgen: wie eine JTDE-Nummer aussieht, was jede Zeile der Sendungsverfolgung heißt und was du tust, wenn das Paket fehlt.
slug: jt-express-sendungsverfolgung
published: 2026-10-21
updated: 2026-11-04
---

J&T Express Germany (J&T Express Germany GmbH aus Frankfurt am Main) ist das deutsche Netz der Paketgruppe J&T Express und stellt Pakete in Deutschland zu. Hat ein Shop deine Bestellung mit J&T verschickt, beginnt die Sendungsnummer mit `JTDE`: Füg sie oben ins Feld ein oder schau in der [Sendungsverfolgung auf jtexpress.de](https://www.jtexpress.de/tracking) nach. Der Shop, der deine Bestellung verschickt hat, kennt die Nummer: Sie steht in seiner Versand-E-Mail oder in deinen Bestelldetails.

## J&T Sendungsnummer: wie sie aussieht und wo du sie findest

Laut FAQ von J&T beginnt die Sendungsnummer mit JTDE. Peek kennt dafür nur eine Form: `JTDE` und 12 Ziffern, 16 Zeichen insgesamt. Mit erfundenen Ziffern:

| Nummer (erfunden) | Was sie ist |
| --- | --- |
| `JTDE000000000000` | `JTDE` und 12 Ziffern: ein Paket von J&T Express Germany. Peek erkennt J&T Express Germany sofort. |
| `JTDE00000000000` | Eine Ziffer zu wenig: keine Nummer von J&T Express Germany, und Peek erkennt keinen Paketdienst. Prüf, ob eine Ziffer fehlt. |

**Wo sie steht.** Laut J&T bekommst du sie vom Absender, und sie steht auf der Bestellseite des Online-Shops, in der Bestellbestätigung, in der Versandbenachrichtigung per E-Mail oder SMS, in E-Mails vor oder nach der Zustellung und auf dem Paketschein. Findest du sie nicht, schickt dich J&T zum Absender oder zum Kundenservice der Einkaufsplattform. Lies auch nach, [wie du die Sendungsnummer findest](guide:find-tracking-number).

Die Sendungsverfolgung von J&T nimmt bis zu 10 Nummern pro Abfrage an, getrennt durch Leerzeichen oder Kommas.

**Ähnliche Nummern.** Die J&T-Netze anderer Länder haben eigene Sendungsverfolgungen und Nummern: `JTUK` mit 12 Ziffern gehört zu J&T Express UK, `JTAU` mit 12 Ziffern zu J&T Express Australia, `JTE` mit 12 Ziffern zu J&T Express Middle East, und `JT` mit 13 Ziffern nutzt J&T in Südostasien und in China. Nur `JTDE` gehört zum deutschen Netz. Meldet die Sendungsverfolgung von J&T `Keine Abfrageergebnisse` oder zeigt Peek weiter „Warten auf den Paketdienst“, vergleich die Nummer mit der E-Mail des Shops oder frag den Shop.

## J&T Sendungsverfolgung: was die Status bedeuten

In der Sendungsverfolgung von J&T besteht jeder Scan aus einer Uhrzeit und einem Satz, ohne Ort. Die Seite ist zunächst auf Deutsch; stellst du sie im Sprachmenü auf Englisch oder Chinesisch um, lädt sie die Scans in dieser Sprache neu. Peek fragt die Sätze auf Englisch ab und übersetzt sie nicht, auch nicht in der deutschen App. Diese Sätze zeigt Peek, in der Reihenfolge, in der ein Paket sie durchläuft:

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

**Welcher Nachbar?** In der Sendungsverfolgung von J&T steht unter diesem Scan `Zustelldetails anzeigen`. Klick darauf und gib die Postleitzahl der Empfängeradresse ein, dann siehst du Namen und Hausnummer des Nachbarn. Nach fünf falschen Postleitzahlen musst du eine Weile warten, bevor du es erneut versuchst. Peek fragt ohne Postleitzahl ab und zeigt nur, dass das Paket bei einem Nachbarn liegt: keinen Namen und keine Adresse. Namen und Telefonnummern in einer Zeile von J&T lässt Peek weg.

## J&T Zustellung: Nachbar, Ablageort und Zustellfoto

In den Verläufen, die Peeks Code kennt, lädt der Zusteller von J&T das Paket für eine Zustelltour ein und stellt es dann zu oder meldet, warum die Zustellung gescheitert ist. Eine Übergabe an einen anderen Paketdienst zeigt Peeks Code nicht: Die Sendungsverfolgung von J&T begleitet das Paket bis zu deiner Tür.

**In Zustellung.** `Your package has been loaded and is on its way to you` heißt: Dein Paket ist auf einer Zustelltour. In diesen Verläufen kommt die Zeile Stunden vor der Zustellung, am selben Morgen. Ein Zeitfenster oder einen erwarteten Zustelltag gibt die Sendungsverfolgung von J&T nicht an, also kann Peek auch keinen zeigen.

**Zustellpräferenzen.** In deiner Versandbenachrichtigung steckt ein Link zur Seite [„Zustellpräferenzen festlegen“](https://www.jtexpress.de/delivery-preference). Dort bestätigst du deine Postleitzahl und wählst aus den Zustellarten, die J&T dir anbietet: mit Unterschrift „Vom Empfänger unterschrieben“, „Beim Nachbarn abgegeben“ (mit dem Namen des Nachbarn und ob er nebenan oder auf derselben Etage wohnt) oder „Empfang/Reception“; ohne Unterschrift Haustür oder Veranda, Garten oder Hof, Briefkasten oder Garage. Laut J&T hält sich der Zusteller an deine Wahl. Wählst du einen Ort ohne Unterschrift, weist die Seite darauf hin, dass du das Verlustrisiko trägst, sobald das Paket dort liegt. Lässt der Stand der Sendung keine Wahl mehr zu, sagt sie dir das.

**Zustellfoto.** Laut dem Datenschutzhinweis zum fotografischen Zustellnachweis macht der Zusteller ein Foto vom Paket, wenn er es dir gibt oder am vereinbarten Ort abstellt. Auf dem Bild sollen nur das Paket und so viel vom Ablageort wie nötig zu sehen sein; wer versehentlich mit draufkommt, wird automatisch unkenntlich gemacht. J&T löscht das Foto in der Regel 6 Monate nach der Zustellung; geht in dieser Zeit eine Reklamation ein, kann es bis zu deren Abschluss gespeichert bleiben. Um eine Reklamation zu klären, kann J&T es dem Absender zeigen.

**Verweigert oder Adresse nicht gefunden.** Nach `Delivery failed, customer refused to accept` hat der Zusteller das Paket in den Verläufen, die Peeks Code kennt, am nächsten Morgen für eine neue Tour verladen. Nach `Delivery failed because the address could not be found` prüf die Adresse in deiner Bestellung. Fehlt etwas, etwa der Name am Klingelschild oder die Wohnungsnummer, sag dem Shop sofort Bescheid: Er hat die Zustellung mit der Adresse gebucht, die du ihm gegeben hast. Auch die FAQ von J&T schickt dich bei einer falschen Adresse gleich zum Absender oder zur Einkaufsplattform: Ob sich die Adresse nach dem Versand noch ändern lässt, hängt davon ab, wo das Paket gerade ist und was der Absender zulässt. Auf eine gescheiterte Zustellung kann `Returns have begun` folgen. Mehr in [Lieferadresse ändern](guide:change-delivery-address) und [Zustellung verpasst](guide:missed-delivery).

**Zustellversuche und Abholung.** Wie oft J&T eine Zustellung versucht, wie lange es ein Paket lagert und zu welchen Uhrzeiten es zustellt, nennt die Website von J&T nicht. Paketshops, Paketautomaten oder Depots zum Abholen nennt sie auch keine, und keine Zeile von J&T Express Germany, die Peeks Code kennt, schickt ein Paket dorthin. Frag den Shop oder schau in deine Nachrichten zur Zustellung.

## J&T-Paket kommt nicht an, ist weg oder geht zurück

Den Auftrag an J&T hat der Shop erteilt, bei einem Online-Kauf ist er also deine erste Adresse. Kaufst du als Verbraucher, trägt der Händler das Transportrisiko, außer du hast den Paketdienst selbst beauftragt und der Händler hatte ihn dir nicht vorher genannt (§ 475 BGB).

**Keine Bewegung.** Laufzeiten für Empfänger nennt J&T nicht. Zeigt ein Paket „Beim Paketdienst eingegangen“ oder „Unterwegs“ und kommt vier Tage lang keine neue Zeile, markiert Peek es („Seit vier Tagen keine Aktualisierung“) und zeigt den Link „Hol dir Hilfe bei J&T Express Germany“. Tut sich danach nichts, frag den Shop oder ruf mit deiner Sendungsnummer bei der Hotline von J&T an. Mehr in [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating).

**Noch keine Sendungsverfolgung.** Kennt die Sendungsverfolgung von J&T die Nummer noch nicht, zeigt Peek „Warten auf den Paketdienst“. Shops legen die Nummer oft an, bevor sie das Paket übergeben. Bleibt es mehrere Tage dabei, lass dir die Nummer vom Shop bestätigen und frag, ob das Paket wirklich verschickt wurde.

**Zugestellt, aber nicht da.** Lies den Zustellsatz in Peek: Nennt er einen Ort, etwa `Mailbox` oder `Garage`, schau dort nach. Steht dort `Left with neighbor`, frag deine Nachbarn oder lass dir in der Sendungsverfolgung von J&T zeigen, welcher es ist (siehe oben). Die FAQ von J&T rät außerdem nachzufragen, ob Familie, Nachbarn, die Hausverwaltung oder andere Berechtigte das Paket angenommen haben. Immer noch nichts? Melde es der Hotline von J&T und bitte den Shop mit deiner Sendungsnummer um Erstattung oder ein neues Paket. Mehr in [Paket zugestellt, aber nicht erhalten](guide:delivered-not-received).

**Verloren, beschädigt oder zurück.** Bitte den Shop mit deiner Sendungsnummer um Erstattung oder Ersatz. Ein Schadensformular, Fristen oder eine Entschädigung für Empfänger nennt die FAQ von J&T nicht; die Fristen stehen im Gesetz. Einen sichtbaren Schaden zeigst du dem Paketdienst nach § 438 HGB spätestens bei der Ablieferung an (dann genügt es, ihn dem Zusteller zu nennen), einen verdeckten innerhalb von sieben Tagen danach in Textform, etwa per E-Mail an J&T. Einigst du dich mit J&T über Verlust oder Beschädigung nicht, kannst du auch als Empfänger die gebührenfreie Schlichtungsstelle Post der Bundesnetzagentur anrufen. Bei E-Commerce-Sendungen großer Onlinehändler gelten aber in der Regel Sonderbedingungen, die eine Schlichtung ausschließen; dann bleibt dir der Verbraucherservice Post der Bundesnetzagentur. Mehr in [Paket verloren](guide:lost-parcel), [Paket beschädigt](guide:damaged-parcel) und [Paket zurück an den Absender](guide:returned-to-sender).

## J&T Kontakt und Kundenservice

So stand es am 3. November 2026 auf der [Kontaktseite von J&T](https://www.jtexpress.de/contact):

- **Hotline:** +49 6931090650, Mo. – Fr. 8:00 bis 20:00 Uhr, an Feiertagen geschlossen.
- **Live-Chat:** „Chat starten“ auf der Kontakt- und der FAQ-Seite.
- **E-Mail:** de-jtservice@jtexpress.de.
- **Büro:** J&T Express Germany GmbH, Am Giener 2, 55268 Nieder-Olm. Das Impressum nennt als Anschrift Wiesenhüttenstraße 11, 60329 Frankfurt am Main.
- **Zustellfotos und Datenschutz:** de-dpo@jtexpress.de (aus dem Datenschutzhinweis zum Zustellfoto).

Bei einer Online-Bestellung ist trotzdem der Shop deine erste Adresse: Er hat die Zustellung bei J&T gebucht. Bei einer Bestellung auf einem Marktplatz nutzt du die Hilfe auf der Bestellseite. Bei einem Paket, das sich nicht mehr bewegt, öffnet der Link „Hol dir Hilfe bei J&T Express Germany“ in Peek die Kontaktseite von J&T.

## J&T-Paket mit Peek verfolgen

[Peek](/) erkennt eine `JTDE`-Nummer sofort als J&T Express Germany („Erkannter Paketdienst“), mit oder ohne Leerzeichen, groß oder klein geschrieben, und fragt die Sendungsverfolgung von J&T nur mit der Nummer ab: ohne Postleitzahl und ohne Konto. Einen Link von www.jtexpress.de/tracking mit `?q=` und der Nummer kannst du ebenfalls einfügen; steht darin eine Nummer anderer Form, liest Peek sie, als hättest du sie allein eingefügt. Hast du eine Nummer anderer Form, füg sie trotzdem ein: Peek verfolgt mehr als 3.500 Paketdienste. Wählst du für eine andere Nummer über „Ändern“ J&T Express Germany, hilft das nicht: Peek schreibt dann, J&T Express Germany „vergibt keine Nummern wie diese“.

Peek zeigt den Status, jeden Scan mit genauer Uhrzeit und den Zeitpunkt der Zustellung. J&T gibt jeden Scan als genauen Zeitpunkt an, Peek zeigt ihn also in der Zeitzone deines Geräts. Die Scans zeigt Peek im englischen Wortlaut von J&T. Orte, einen erwarteten Zustelltag oder das Gewicht gibt J&T nicht heraus, Peek zeigt also keine Karte. Bei sehr langem Verlauf behält Peek die neuesten 100 Scans. „Website von J&T Express Germany öffnen“ auf der Seite des Pakets öffnet deine Nummer in der Sendungsverfolgung von J&T.

Bis J&T das Paket scannt, zeigt Peek „Warten auf den Paketdienst“ und fragt weiter nach. Ist die Sendungsverfolgung von J&T nicht erreichbar oder kennt sie die Nummer nicht, fragt Peek bei Tracking-Diensten nach und zeigt deren Verlauf in deren Worten.

Peek schaut alle 10 Minuten nach, alle 2 Minuten, sobald das Paket in Zustellung ist (am Tag der Zustelltour ab 6:00 Uhr Ortszeit und solange J&T selbst antwortet; über Tracking-Dienste alle 15 Minuten). Kommt zwei Tage lang kein neuer Scan (solange es „Beim Paketdienst eingegangen“ zeigt, schon nach 12 Stunden), schaut Peek stündlich nach. Auf Wunsch schickt es dir Mitteilungen: „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“. J&T oder den Shop kann Peek nicht für dich kontaktieren, und es kann weder die Adresse ändern noch eine neue Zustellung buchen.

## Fragen zur J&T Express Sendungsverfolgung

### Ist J&T Express Germany seriös?

Ja. Die J&T Express Germany GmbH mit Sitz in Frankfurt am Main ist die deutsche Gesellschaft von J&T Express, einer 2015 in Indonesien gegründeten und in Hongkong börsennotierten Paketgruppe. Die Bundesnetzagentur führt die GmbH seit dem 1. September 2026 in ihrem Anbieterverzeichnis Post. J&T wirbt bundesweit um Franchise-Partner, die auf der letzten Meile Pakete zustellen und abholen. Jeder Satz in der Statustabelle oben stammt aus der Sendungsverfolgung echter Pakete von J&T Express Germany.

### Welcher Nachbar hat mein J&T-Paket?

Das zeigt die Sendungsverfolgung von J&T: Klick unter dem Zustellscan auf `Zustelldetails anzeigen` und gib die Postleitzahl der Empfängeradresse ein, dann siehst du Namen und Hausnummer des Nachbarn. Peek zeigt nur `Left with neighbor`, ohne Namen und Adresse.

### Hat J&T Express Paketshops in Deutschland?

Die deutsche Website von J&T nennt keine, und keine Zeile von J&T Express Germany, die Peek kennt, schickt ein Paket in einen Paketshop oder an einen Paketautomaten. Über die Zustellpräferenzen kannst du dir die Zustellung an dich, an einen Nachbarn, am Empfang oder an einen Ablageort an deiner Adresse wünschen.

### Warum findet die J&T Sendungsverfolgung meine Nummer nicht?

Prüf, ob sie mit `JTDE` beginnt und danach 12 Ziffern hat: Nummern aus anderen J&T-Netzen, etwa `JTUK` aus Großbritannien, sind keine deutschen. Stimmt die Nummer, hat J&T das Paket vielleicht noch nicht: Die Sendungsverfolgung von J&T meldet dann `Keine Abfrageergebnisse`, Peek zeigt bis zum ersten Scan „Warten auf den Paketdienst“. Dauert das mehrere Tage, frag den Shop.

### Wie erreiche ich J&T Express in Deutschland?

Ruf +49 6931090650 an (Mo. – Fr. 8:00 bis 20:00 Uhr), nutze den Live-Chat auf jtexpress.de oder schreib an de-jtservice@jtexpress.de. Bei einer Online-Bestellung frag aber zuerst den Shop: Er hat die Zustellung gebucht.

:::sources
- [J&T Express: Sendungsverfolgung](https://www.jtexpress.de/tracking) – Abfrage, bis zu 10 Nummern, Sprachen, Scans neu laden, Meldung ohne Ergebnis, Nachbardetails mit Postleitzahl, fünf Versuche
- [J&T Express: Häufig gestellte Fragen](https://www.jtexpress.de/faq) – JTDE-Nummern und wo sie stehen, zugestellt aber nicht erhalten, falsche Adresse, Live-Chat; keine Laufzeiten, Zustellversuche oder Schadensregeln
- [J&T Express: Zustellpräferenzen festlegen](https://www.jtexpress.de/delivery-preference) – Link in der Versandbenachrichtigung, Postleitzahl, Zustellarten mit und ohne Unterschrift, Verlustrisiko
- [J&T Express: Kontaktieren Sie uns](https://www.jtexpress.de/contact) – Hotline, Servicezeiten, Chat, E-Mail, Büroadresse
- [J&T Express: Impressum](https://www.jtexpress.de/legal-statement) – J&T Express Germany GmbH, Frankfurt am Main
- [J&T Express: POD Datenschutzerklärung](https://www.jtexpress.de/pod-policy) – Zustellfoto, 6 Monate, Reklamation, Datenschutzkontakt
- [J&T Express: Werden Sie unser Partner](https://www.jtexpress.de/career) – Franchise-Partner für Zustellung und Abholung auf der letzten Meile
- [J&T Express: Über uns](https://www.jtexpress.de/about) – Gründung 2015 in Indonesien, Börsennotierung in Hongkong
- [Universal Parcel Scraper: J&T Express Germany](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/j-and-t-de) – Namen, JTDE-Nummern, Tracking-Links, Sätze der Sendungsverfolgung von echten Paketen und ihre Status, Zustelltouren, Zustellarten, was Peek weglässt, Hilfeseite
- [Universal Parcel Scraper: gemeinsame Abfrage der J&T-Netze](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/j-and-t-au) – englische Sätze, genaue Scanzeiten, kein Ort, kein Zustelltag, kein Gewicht, Namen und Telefonnummern weggelassen, neueste 100 Scans
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Nummern anderer J&T-Netze, keine Übergabe an einen anderen Paketdienst, Tracking-Dienste als Rückfall
- [Gesetze im Internet: § 475 BGB](https://www.gesetze-im-internet.de/bgb/__475.html) – Transportrisiko beim Verbrauchsgüterkauf
- [Gesetze im Internet: § 438 HGB](https://www.gesetze-im-internet.de/hgb/__438.html) – Schadensanzeige bei der Ablieferung, verdeckte Schäden binnen sieben Tagen, Textform
- [Bundesnetzagentur: Anbieterverzeichnis Post](https://www.bundesnetzagentur.de/DE/Fachthemen/Post/Anbieterverzeichnis/PostAnbieterverzeichnis.html) – J&T Express Germany GmbH, eingetragen am 1. September 2026
- [Bundesnetzagentur: Häufig gestellte Fragen zur Schlichtung Post](https://www.bundesnetzagentur.de/DE/Vportal/Schlichtung/Schlichtung_Post/_FAQ_Schlichtung/faqSchlichtung.html) – Antrag auch durch Empfänger, gebührenfrei, Sonderbedingungen bei E-Commerce-Sendungen, Verbraucherservice Post
:::
