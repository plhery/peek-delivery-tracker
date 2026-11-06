---
title: Etsy-Sendungsverfolgung: wo deine Bestellung ist
description: Warum deine Etsy-Bestellung noch „Nicht verschickt“ zeigt, welcher Versanddienst sie bringt, wie die Nummer aussieht und wann du einen Fall eröffnest.
slug: etsy-sendungsverfolgung
picture: Ein mit Schnur verschnürtes, frankiertes Paket, ein Postauto und Pip, der eilig vorbeiläuft.
published: 2026-10-17
updated: 2026-10-17
---

Etsy verschickt selbst nichts: Jeder Shop fertigt oder packt seine Bestellungen selbst, gibt sie bei einem Versanddienst seiner Wahl auf und trägt eine Sendungsnummer ein, wenn der Dienst eine hat. Unter deinen Einkäufen siehst du, ob die Bestellung den Shop verlassen hat und wann sie ankommen soll; die Sendungsnummer zeigt, wo sie gerade ist. Zu spät oder verschwunden? Frag zuerst den Shop, dann Etsy.

## Warum zeigt meine Etsy-Bestellung noch „Nicht verschickt“?

Weil der Shop sie noch nicht angefertigt, gepackt oder als verschickt markiert hat. Etsy rechnet deinen Liefertermin so: „Bearbeitungszeit + Transitzeit des Versandanbieters + 1 Tag = Liefertermin“. Die Bearbeitungszeit ist die Zeit, die der Shop braucht, um die Bestellung fertig zu machen, bevor er sie übergibt; bei Anfertigungen auf Bestellung gehört das Herstellen dazu. Auf deinem Beleg steht außerdem das Versanddatum, zu dem sich der Shop verpflichtet hat.

Unter **Einkäufe und Bewertungen** (App: **Du** → **Einkäufe**) hat jede Bestellung einen dieser Status:

| Status | Was er bedeutet |
| --- | --- |
| `Nicht verschickt` | Der Shop hat sie noch nicht verschickt oder auf Etsy nicht aktualisiert |
| `Verschickt` | Der Shop hat die Bestellung auf Etsy abgeschlossen |
| `Vor Paketaufgabe` | Eine Sendungsnummer ist eingetragen, aber der Versanddienst hat das Paket noch nicht gescannt |
| `Unterwegs` | Der Versanddienst hat es, es ist auf dem Weg |
| `Zugestellt` | Es ist angekommen, und du kannst eine Bewertung abgeben |

`Verschickt` heißt nicht immer, dass ein Versanddienst das Paket hat. Ein Shop kann das Versanddatum bis zu 3 Tage im Voraus setzen, und Etsy schreibt dir an diesem Tag. Nur bei auf Etsy gekauften Etiketten von Asendia, Evri, FedEx, USPS (außer Briefen) oder UPS wartet die E-Mail auf den ersten Scan. Ist der Liefertermin vorbei und steht die Bestellung noch auf `Nicht verschickt`, hat der Shop laut Etsy vielleicht vergessen, sie abzuschließen, oder noch nicht verschickt: Frag über **Hilfe bei einer Bestellung** nach.

Wo die Nummer auf Etsy und in der Versand-E-Mail steht: [Sendungsnummer finden](guide:find-tracking-number).

## Welcher Versanddienst liefert eine Etsy-Bestellung?

Der, den der Shop gewählt hat. Versandetiketten verkauft Etsy nur an Shops in den USA, in Kanada und im Vereinigten Königreich; alle anderen kaufen das Porto selbst bei der Post oder einem Paketdienst. Für Pakete ins Ausland nennt Etsy die Versanddienste, die in jedem Land üblich sind:

| Shop in | Etiketten auf Etsy | Von Etsy genannt für den Versand ins Ausland |
| --- | --- | --- |
| USA | USPS, [FedEx](carrier:fedex), [UPS](carrier:ups) (nur innerhalb der USA), Global Postal Shipping | [DHL Express](carrier:dhl-express), FedEx, UPS, USPS |
| Vereinigtes Königreich | Royal Mail, Evri | DHL, Evri, Royal Mail, UPS |
| Kanada | Canada Post, Chit Chats | Canada Post, Chit Chats, DHL, FedEx, UPS |
| Deutschland | Keine | Deutsche Post, [DHL](carrier:dhl), UPS |
| Österreich | Keine | [Österreichische Post](carrier:austrian-post) |
| Schweiz | Keine | [Die Post](carrier:swiss-post) |
| China, Hongkong | Keine | [Yanwen](carrier:yanwen), [YunExpress](carrier:yunexpress), [4PX](carrier:4px), aus China auch SF Express |

Nutzt der Shop einen Versanddienst, den Etsy direkt unterstützt, steht neben der Bestellung der Button **Paket verfolgen**. Sonst findest du die Nummer nur unter **Beleg anzeigen** und verfolgst sie auf der Seite des Versanddienstes.

Seit dem 1. Juli 2026 verkauft Etsy vorübergehend keine USPS-Etiketten nach Deutschland, Österreich und in die Schweiz und keine von Canada Post nach Deutschland und Österreich, nach eigenen Angaben wegen einer neuen EU-Bearbeitungsgebühr, die einige Versanddienste betrifft. Shops in den USA nehmen dann Global Postal Shipping oder kaufen das Etikett woanders. Pakete aus China nehmen einen eigenen Weg: siehe [Pakete aus China verfolgen](guide:tracking-from-china).

## Wie sieht eine Etsy-Sendungsnummer aus?

Es ist die Nummer des Versanddienstes, keine von Etsy. Die Posten nutzen das internationale Postformat: 13 Zeichen, die über Grenzen hinweg gleich bleiben.

:::anatomy RR 00000000 5 US
- RR | Zwei Buchstaben für die Art der Sendung und den Dienst
- 00000000 | Seriennummer, acht Ziffern
- 5 | Prüfziffer, aus der Seriennummer berechnet
- US | Land der Post, die sie vergeben hat: die USA
:::

Die Formen, die Peek für die Versanddienste oben kennt, mit erfundenen Ziffern:

| Die Nummer sieht so aus | Meistens |
| --- | --- |
| Zwei Buchstaben, neun Ziffern, dann `DE`, `AT`, `CH`, `US` oder `GB` | Deutsche Post, Österreichische Post, Die Post, USPS oder Royal Mail |
| 20 Ziffern ab `0034043`, oder `JJD` und weitere Zeichen | DHL Paket |
| 12 Ziffern | DHL Paket oder FedEx |
| `98` oder `99` und 16 weitere Ziffern | Die Post (Schweiz) |
| 22 Ziffern ab `91` bis `95`, etwa `9400111111111111111114` | USPS |
| Andere Nummern mit 22 oder 24 Ziffern | Österreichische Post |
| `1Z` und 16 Buchstaben und Ziffern | UPS |

Unsicher, was du hast? Tipp sie hier ein:

:::checker
:::

Alle anderen Formate, Dienst für Dienst: [Sendungsnummern erkennen](guide:tracking-number-formats).

## Warum stoppt oder wechselt die Etsy-Sendungsverfolgung?

Meist aus einem dieser Gründe:

- **Es hat eine Grenze überquert.** Laut Etsy wird bei einigen internationalen Sendungen „das Tracking nicht mehr angezeigt, sobald die Bestellung das Herkunftsland verlässt“. Es sollte sich wieder aktualisieren, wenn das Paket in deinem Land ankommt.
- **Deine Post hat übernommen.** Bei einer Postnummer schickt die Post des Absenders das Paket ins Ausland, und deine Post stellt es zu. Der Weltpostverein erlaubt „nur eine S10-Nummer“ pro Sendung, dieselbe Nummer funktioniert also meist auch bei deiner Post.
- **Zwei Etiketten, ein Weg.** Bei Etsys Global Postal Shipping schickt ein US-Shop das Paket mit einem USPS-Etikett an Asendia, das das internationale Etikett anbringt. Etsy fügt beide Teile unter Einkäufe und Bewertungen zu einem Verlauf zusammen; in den E-Mails steht „Tracked on Etsy“.
- **Der Shop hat die Nummer spät eingetragen oder korrigiert.** Shops können eine Nummer 3 Tage nach Abschluss der Bestellung ändern; danach schicken sie dir die richtige per Nachricht. Sie können auch später ein Paket hinzufügen, dessen Nummer neben der ersten erscheint.
- **Mehrere Bestellungen, ein Karton.** Packt ein Shop mehrere deiner Bestellungen zusammen, bekommen alle dieselbe Nummer.

:::journey
- shop | Shop | Fertigt und packt es: bis dahin `Nicht verschickt`
- label | Etikett | `Vor Paketaufgabe`: eine Nummer, noch kein Scan
- truck | Absenderpost | Erste Scans im Land des Shops
- plane | Flug | Hier wird das Tracking oft still
- customs | Dein Zoll | Prüft, manchmal mit Gebühren
- handover | Deine Post | Stellt zu, meist unter derselben Nummer
- home | Du | `Zugestellt`
:::

Wie lange eine stille Phase dauern kann: [Sendungsverfolgung aktualisiert nicht](guide:tracking-not-updating).

### Warum gibt es gar keine Sendungsnummer?

Nicht jeder Dienst hat eine. Etsy sagt es klar: „Tracking-Informationen sind nicht bei allen Versandmethoden enthalten.“ Für Briefe und Großbriefe aus den USA bringen Etsys eigene Etiketten Tracking über Pitney Bowes mit, aber ein zu steifes Stück zeigt eventuell kein Tracking. Etsy verlangt Tracking von Shops in den USA, doch jeder Shop kann eine Bestellung als ohne Tracking markieren und einen Grund angeben. Keine Nummer? Schreib dem Shop über **Hilfe bei einer Bestellung**: Er weiß, welchen Dienst er genutzt hat und wann er das Paket aufgegeben hat.

## Zahlst du Zoll auf eine Etsy-Bestellung aus dem Ausland?

Laut Etsy zahlen Käufer Zölle, Steuern und Abfertigungsgebühren in der Regel selbst, meist bei der Ankunft des Pakets. In der EU nimmt Etsy einen Teil davon schon an der Kasse:

| Bestellung | Was gilt |
| --- | --- |
| Aus der EU nach Deutschland oder Österreich | Die Umsatzsteuer ist im Preis enthalten, mehr zahlst du nicht |
| Von außerhalb der EU, unter 150 EUR | Etsy berechnet die Umsatzsteuer an der Kasse. Dazu kommt eine Zollgebühr von 3 EUR pro Artikel, an der Kasse oder bei der Lieferung |
| Von außerhalb der EU, über 150 EUR | Umsatzsteuer und Zoll können bei der Lieferung fällig werden, außer der Shop verschickt mit vorab bezahlten Zollgebühren |
| In die Schweiz | Etsy nennt keine eigene Regel. Laut BAZG übernehmen Post oder Kuriere meist die Zollanmeldung. Die MWST beträgt 8,1 %, für manche Waren 2,6 %; Steuer- und Zollbeträge bis 5 Franken werden nicht erhoben |

Kommt ein Paket zu spät oder gar nicht, weil du Gebühren bei der Lieferung verweigert oder nicht bezahlt hast, deckt Etsys Einkaufsschutz das nicht ab. Mehr zu Gebühren und Wartezeiten: [Paket beim Zoll](guide:customs).

## Was tun, wenn deine Etsy-Bestellung zu spät kommt oder fehlt?

Geh in dieser Reihenfolge vor:

:::steps
- Termine prüfen | Der Liefertermin steht an der Bestellung. Etsy rechnet ihn neu, wenn der Shop früher verschickt.
- Beim Versanddienst verfolgen | Über Paket verfolgen, oder mit der Nummer unter Beleg anzeigen auf der Seite des Dienstes.
- Den Shop fragen | Einkäufe und Bewertungen, dann Hilfe bei einer Bestellung. Shops können den Status prüfen, die Adresse ändern oder erstatten.
- Einen Fall eröffnen | 48 Stunden nach deiner Anfrage, sobald der Liefertermin vorbei ist. Bei einer Bestellung, die nie kam: 7 Tage nach dem spätesten Liefertermin.
- Die Frist beachten | Du hast bis 30 Tage nach dem Liefertermin Zeit.
:::

Mit Etsys Einkaufsschutz bekommen berechtigte Bestellungen den vollen Betrag zurück, wenn der Artikel nicht ankommt, beschädigt ankommt, deutlich von der Beschreibung abweicht oder mindestens 7 Tage nach dem spätesten beim Kauf angegebenen Liefertermin ankommt. Deine Anfrage unter **Hilfe bei einer Bestellung** zeigt, ab wann du **Fall öffnen** wählen kannst und bis wann. Von Etsy abgewickelte Erstattungen sind nur bis 180 Tage nach dem Kauf möglich.

Manches ist ausgeschlossen: Verspätungen durch einen Streik beim Versanddienst, schwere Unwetter, Krieg oder Unruhen, an der Tür verweigerte Gebühren und Bestellungen, die du schon bei deiner Bank angefochten hast. Als Gast bestellt? Verknüpfe die Bestellung zuerst mit einem Etsy-Konto. Lief deine Zahlung über eigenständiges PayPal statt über Etsy Payments, meldest du stattdessen innerhalb von 180 Tagen nach dem Kauf einen Konflikt bei PayPal.

### Es steht „Zugestellt“, aber nichts ist da

Etsy rät zuerst zu diesen Schritten: bei Mitbewohnern oder Kollegen nachfragen, einen Tag warten, wenn es nicht eilt, bei deiner Postfiliale oder dem Paketzentrum nachfragen und bei den Nachbarn klingeln. Immer noch nichts? Schick dem Shop eine Anfrage über **Hilfe bei einer Bestellung**: In der Regel stellt der Shop für dich den Nachforschungsauftrag beim Versanddienst. Taucht es nicht auf, eröffne einen Fall. Mehr unter [als zugestellt angezeigt, aber nicht erhalten](guide:delivered-not-received) und [verlorene Pakete](guide:lost-parcel).

## Kann ich eine Etsy-Bestellung außerhalb von Etsy verfolgen?

Ja, mit der Nummer des Versanddienstes. Füg sie in [Peek](/) ein: Es erkennt die Nummern der Posten und Paketdienste oben. Bei einer Postsendung ins Ausland, deren Absenderpost das Zielland meldet, aber keinen Partner nennt, kann Peek eine Abfrage mit derselben Nummer bei der Post deines Landes vorschlagen: in Deutschland bei DHL, in der Schweiz bei der Post. Es behält den Verlauf der Absenderpost und folgt deiner Post, sobald ihr Tracking neue Fortschritte zeigt. Etsys eigene Schritte bleiben bei Etsy: Peek sieht keine Bestellung, die noch `Nicht verschickt` ist, und kann keinen Fall für dich eröffnen.

:::sources
- [Etsy: Was ist der Status meiner Bestellung?](https://help.etsy.com/hc/de/articles/115015521948-Was-ist-der-Status-meiner-Bestellung) – Status, Paket verfolgen, Beleg anzeigen, Tracking im Ausland, kein Tracking, Zugestellt-Tipps
- [Etsy: Was ist ein Liefertermin?](https://help.etsy.com/hc/de/articles/360020601674-Was-ist-ein-Liefertermin) – Berechnung des Liefertermins, 7 Tage bei Nichterhalt
- [Etsy: Bearbeitungszeiten, Bearbeitungsprofile und Versandtermine einrichten](https://help.etsy.com/hc/de/articles/115015588087-Bearbeitungszeiten-Bearbeitungsprofile-und-Versandtermine-einrichten) – Bearbeitungszeit, Anfertigung, Versanddatum auf dem Beleg
- [Etsy: Tracking zu einer Bestellung hinzufügen und Bestellung abschließen](https://help.etsy.com/hc/de/articles/115015774228-Tracking-zu-einer-Bestellung-hinzuf%C3%BCgen-und-Bestellung-abschlie%C3%9Fen) – Versanddatum bis 3 Tage voraus, E-Mail nach dem ersten Scan, Tracking-Pflicht in den USA, 3 Tage zum Ändern, weitere Pakete, zusammengelegte Bestellungen
- [Etsy: Versandetiketten auf Etsy kaufen](https://help.etsy.com/hc/de/articles/360001967188-Versandetiketten-auf-Etsy-kaufen) – Versanddienste, nur USA, Kanada und Vereinigtes Königreich, gesperrte EU-Ziele
- [Etsy: FedEx Shipping Labels on Etsy](https://help.etsy.com/hc/en-us/articles/360000337447-FedEx-Shipping-Labels-on-Etsy) – nur Shops in den USA
- [Etsy: UPS Shipping Labels on Etsy](https://help.etsy.com/hc/en-us/articles/18309523715735-UPS-Shipping-Labels-on-Etsy) – nur innerhalb der USA
- [Etsy: Royal Mail Postage Labels on Etsy](https://help.etsy.com/hc/en-gb/articles/30907634892311-Royal-Mail-Postage-Labels-on-Etsy) – Shops im Vereinigten Königreich
- [Etsy: Evri Postage Labels on Etsy](https://help.etsy.com/hc/en-gb/articles/360002035507-Evri-Postage-Labels-on-Etsy) – Shops im Vereinigten Königreich
- [Etsy: Chit Chats Shipping Labels on Etsy](https://help.etsy.com/hc/en-us/articles/44210045773719-Chit-Chats-Shipping-Labels-on-Etsy) – Shops in Kanada
- [Etsy: Global Postal Shipping Labels on Etsy](https://help.etsy.com/hc/en-us/articles/360037382894-Global-Postal-Shipping-Labels-on-Etsy) – Asendia, zwei Etiketten, Tracked on Etsy
- [Etsy: How to Ship First-Class Flats and Letters](https://help.etsy.com/hc/en-us/articles/360039466434-How-to-Ship-First-Class-Flats-and-Letters-with-Etsy-Shipping-Labels) – Tracking über Pitney Bowes, zu steife Sendungen
- [Etsy: Informationen zur Handhabung internationaler Sendungen](https://help.etsy.com/hc/de/articles/360001987487-Informationen-zur-Handhabung-internationaler-Sendungen) – übliche Versanddienste je Land
- [Etsy: Muss ich für meine Bestellung Steuern, Zollgebühren oder Zölle zahlen?](https://help.etsy.com/hc/de/articles/115015691007-Muss-ich-f%C3%BCr-meine-Bestellung-Steuern-Zollgeb%C3%BChren-oder-Z%C3%B6lle-zahlen) – EU-Regeln, 150 EUR, 3 EUR pro Artikel
- [BAZG: Empfangen von Briefen und Paketen](https://www.bazg.admin.ch/de/empfangen-von-briefen-und-paketen) – Zollanmeldung durch Post oder Kurier, MWST 8,1 % und 2,6 %, Beträge bis 5 Franken
- [Etsy: Etsys Einkaufsschutz-Programm](https://help.etsy.com/hc/de/articles/7471925990807-Etsys-Einkaufsschutz-Programm) – was geschützt ist, Zollgebühren, Streiks und Unwetter, Gäste
- [Etsy: Hilfe bei einer Bestellung erhalten](https://help.etsy.com/hc/de/articles/4402660818583-Hilfe-bei-einer-Bestellung-erhalten) – Hilfe bei einer Bestellung, was Shops tun können
- [Etsy: Einen Fall eröffnen](https://help.etsy.com/hc/de/articles/5745586898199-Einen-Fall-er%C3%B6ffnen) – 48 Stunden, 30 Tage, 180 Tage, PayPal, Rückbuchungen
- [Etsy: What if My Buyer's Package Gets Lost?](https://help.etsy.com/hc/en-us/articles/360050349953-What-if-My-Buyer-s-Package-Gets-Lost) – Nachforschung durch den Shop
- [UPU: S10 standard](https://www.upu.int/UPU/media/upu/files/postalSolutions/programmesAndServices/standards/S10-12.pdf) – das 13-stellige Format, eine Nummer pro Sendung
- [Universal Parcel Scraper: carrier data](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers) – Nummernformen, was Peek prüft
:::
