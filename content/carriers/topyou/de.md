---
title: TopYou Sendungsverfolgung: TYZR-Nummer, Status, Zustellung
description: TopYou-Paket (通邮) aus China verfolgen: wie die TYZR-Nummer aussieht, was die englischen Scans bedeuten und wer in Deutschland, Österreich und der Schweiz zustellt.
slug: topyou-sendungsverfolgung
published: 2026-10-30
updated: 2026-10-30
---

TopYou (通邮, auch 通邮集团) ist 深圳市通邮物流科技（集团）股份有限公司, ein Spediteur aus Shenzhen in China, der Sendungen über eigene internationale Linien ins Ausland bringt. Hat ein chinesischer Shop deine Bestellung mit TopYou verschickt, bekommst du eine Nummer, die mit TYZR beginnt und auf YQ endet: Füg sie oben ins Feld ein. TopYous Verlauf reicht vom Lager in China über Flug und Zoll bis in dein Land, wo ein Paketdienst vor Ort das Paket übernimmt.

## TopYou Sendungsnummer: so sieht sie aus

Eine TopYou-Nummer besteht aus TYZR, einem Buchstaben, zehn Ziffern und YQ, zusammen 17 Zeichen. Mit erfundenen Ziffern:

| Nummer | Was sie ist |
| --- | --- |
| `TYZRA0000000017YQ` | TYZR, ein Buchstabe, zehn Ziffern, YQ: eine TopYou-Nummer. |
| `TYZRA000000017YQ` | Nur neun Ziffern: So sieht keine TopYou-Nummer aus. Prüf, ob eine Ziffer fehlt. |
| `JXCAB0000000017YQ` | Endet auch auf YQ, wie bei vielen chinesischen Spediteuren, beginnt aber mit JXC: eine Nummer von Juxi Express. |

Leerzeichen und Kleinbuchstaben stören Peek nicht. TopYous eigene Tracking-Seite unterscheidet aber zwischen Groß- und Kleinschreibung, also tipp die Nummer dort in Großbuchstaben ein. Einen Link, der dort ein einzelnes Paket öffnet, gibt es nicht. Steht die Nummer nicht in deiner Bestellung, lies [wo du die Sendungsnummer findest](guide:find-tracking-number).

**Eine zweite Nummer.** TopYous Datensatz kann auch die Nummer des Paketdiensts enthalten, der das Paket bei dir zustellt, und TopYous Tracking-Seite zeigt sie an. Unter dieser Nummer verfolgt der Zusteller das Paket.

## TopYou Sendungsverfolgung: was die Status bedeuten

TopYou schreibt seine Scans auf Englisch, und so zeigt sie auch Peek. In der Reihenfolge, in der ein Paket sie bekommt:

| TopYou schreibt | Was es für dich heißt | Peek zeigt |
| --- | --- | --- |
| `Arrived at the first mile sorting center` | TopYous Lager in China hat das Paket: der erste Scan bei TopYou. | „Beim Paketdienst eingegangen“ |
| `Departed from the first mile sorting center` | Es hat dieses Lager verlassen. | „Unterwegs“ |
| `Arrived at airport of the original country` | Am Abflughafen in China, vor dem Flug. | „Unterwegs“ |
| `Departed from Airport to destination country` | Der Flug in dein Land ist gestartet. | „Unterwegs“ |
| `Arrived at destination airport` | In deinem Land gelandet. | „Unterwegs“ |
| `Departed from customs of` | Der Zoll in deinem Land hat das Paket freigegeben. Das Land nach „of“ lässt TopYou leer, Peek lässt das „of“ weg. | „Unterwegs“ |

**Neuere Sendungsnummern.** Bei neueren Nummern formuliert TopYou die Schritte anders:

| TopYou schreibt | Was es für dich heißt | Peek zeigt |
| --- | --- | --- |
| `Carrier received the shipment` | TopYou hat das Paket in China: sein erster Scan. | „Beim Paketdienst eingegangen“ |
| `Shipment arrived at a carrier facility` | Das Paket verlässt TopYous erstes Lager. Eine zweite Annahme ist das nicht. | „Unterwegs“ |
| `Shipment started the customs clearance process at origin` | Ausfuhrabfertigung in China, vor dem Flug. Mit deinem Zoll hat das noch nichts zu tun. | „Beim Zoll“ |
| `Shipment arrived in the destination country` | Das Paket ist in deinem Land. | „Unterwegs“ |
| `Shipment started the customs clearance process at destination` | Die Einfuhrverzollung in deinem Land hat begonnen. | „Beim Zoll“ |
| `Shipment completed the customs clearance process at destination` | Der Zoll ist erledigt, das Paket ist auf dem Weg zum Paketdienst, der zustellt. | „Unterwegs“ |

**Danach.** TopYous Tracking-Seite kann die Scans des Zustellers weitergeben, bis zur Zustellung, in dessen eigenen Worten und mit Ort.

## Wer TopYou-Pakete in Deutschland, Österreich und der Schweiz zustellt

Nach dem Einfuhrzoll gibt TopYou das Paket an einen Paketdienst in deinem Land weiter. Welchen, verrät TopYous Datensatz nicht: Er kann dessen Nummer enthalten, aber nicht den Namen. Auch der Name der TopYou-Linie hilft nicht, denn eine Linie kann wie ein anderer Paketdienst heißen: Ein Paket, das USPS zustellte, lief auf einer Linie namens „DHL Paket“. Frag also den Shop, wer deine Bestellung bringt und unter welcher Nummer.

Hat die Post in deinem Land das Paket, gilt Folgendes:

**Deutschland: Deutsche Post und [DHL Paket](carrier:dhl).** Eine Sendung aus dem Ausland muss laut DHL nicht die Deutsche Post oder DHL Paket bringen: Auch andere Firmen stellen in Deutschland zu. Ob DHL dein Paket hat, siehst du, wenn du die Nummer des Zustellers in die DHL-Sendungsverfolgung eingibst. Findet sie nichts, ist das Paket vielleicht noch nicht in DHLs Netz, oder eine andere Firma bringt es; dann kann DHL dir nichts dazu sagen. Ware von außerhalb der EU meldet die Deutsche Post beim Zoll an, und die Prüfung kann mehrere Tage dauern. In einer Filiale wartet ein Paket sieben Werktage, danach geht es zurück an den Absender.

**Österreich: [Österreichische Post](carrier:austrian-post).** Bei `Sendung ins Zielland weitergeleitet` aktualisiert sich der Status erst, wenn ein Logistikzentrum der Post das Paket erfasst hat. `Sendung in Verteilung` heißt: Es ist in Österreich und kommt in den nächsten Werktagen. Warst du nicht da, liegt ein gelber Zettel im Briefkasten, und die Abholfrist läuft ab dem nächsten Tag bis zum dritten darauffolgenden Montag, bei manchen Versandhändlern nur 5 Werktage.

**Schweiz: [Schweizerische Post](carrier:swiss-post).** Hat die Post dich verpasst, bekommst du eine Abholungseinladung, auf der die Abholstelle steht. Pakete aus dem Ausland liegen in der Regel 15 Tage bereit statt 7, und die Frist kannst du kostenlos und ohne Konto verlängern.

Wie Abholung, Ausweis und Fristen genau laufen, steht unter [Zustellung verpasst](guide:missed-delivery) und [nicht abgeholtes Paket](guide:uncollected-parcel).

## TopYou Paket kommt nicht an oder Tracking hängt

**Hängt beim Zoll.** Steht `Shipment started the customs clearance process at destination` da, oder zeigt Peek nach dem Flug „Beim Zoll“, wartet das Paket auf den Einfuhrzoll. Sollst du Abgaben zahlen, lies [Paket beim Zoll](guide:customs); bleibt es tagelang dort, sag dem Shop Bescheid. Mehr unter [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating).

**Nichts mehr nach der Zollfreigabe.** Nach `Departed from customs of` oder `Shipment completed the customs clearance process at destination` sollte der Paketdienst vor Ort das Paket haben. Such auf TopYous Tracking-Seite nach seiner Nummer und gib sie in seine Sendungsverfolgung ein, oder frag den Shop, wer zustellt und unter welcher Nummer. Hintergrund unter [Paket aus China verfolgen](guide:tracking-from-china).

**Nicht gefunden oder noch keine Scans.** Eine Nummer, die TopYou nicht kennt, beantwortet die Tracking-Seite mit 查询不到 (nicht gefunden). Bei einer bekannten Nummer ohne Scan kann 暂无轨迹信息 (noch keine Sendungsdaten) stehen. Peek zeigt in beiden Fällen „Warten auf den Paketdienst“ und fragt weiter nach. Vergleiche die Nummer Zeichen für Zeichen mit der Nachricht des Shops. Kommt nach ein paar Tagen nichts, frag den Shop, ob er das Paket wirklich verschickt hat.

**Zugestellt, aber nicht da.** Zugestellt hat der Paketdienst vor Ort, also fang bei ihm an und frag danach den Shop. Siehe [Paket zugestellt, aber nicht erhalten](guide:delivered-not-received).

**Verloren oder zurück.** Den Versand mit TopYou hat der Shop gebucht, also bittest du ihn oder den Marktplatz um Erstattung oder ein neues Paket. Auch die Österreichische Post sagt: Bei Sendungen aus dem Ausland kann nur der Versender eine Nachforschung einleiten, oder es geht über das Transportunternehmen im Ausland. Siehe [Paket verloren](guide:lost-parcel) und [Paket zurück an den Absender](guide:returned-to-sender).

## TopYou Kontakt: wen du fragst

Stand 30. Oktober 2026 konnte Peek TopYous Website nicht prüfen, deshalb stehen hier keine Kontaktdaten von TopYou. Für deine Bestellung ist der Shop zuständig, der das Paket bei TopYou gebucht hat, und der Marktplatz, wenn der Shop nicht antwortet. Hat die Post in deinem Land das Paket, erreichst du sie so:

- **DHL (Deutschland):** 0228 4333112 für internationale Sendungen, Montag bis Freitag 8 bis 20 Uhr, Samstag 8 bis 18 Uhr, außer an bundeseinheitlichen Feiertagen. Halte die Sendungsnummer bereit, dazu deine Postnummer oder, ohne DHL-Konto, deine vollständige Adresse.
- **Österreichische Post:** Privatkundenhotline +43 800 010 100, Montag bis Freitag 7 bis 19 Uhr, Samstag 8 bis 13 Uhr. Schriftlich geht es rund um die Uhr über das Kontaktformular auf post.at.
- **Schweizerische Post:** +41 848 888 888, CHF 0.08 pro Minute vom Schweizer Festnetz, Montag bis Freitag 7.30 bis 18 Uhr, Samstag 8 bis 12 Uhr. Dazu gibt es einen Chat.

## TopYou Paket verfolgen mit Peek

[Peek](/) erkennt eine TopYou-Nummer an ihrer Form: TYZR, ein Buchstabe, zehn Ziffern und YQ. Kein anderer Paketdienst in Peeks Katalog nutzt sie, und Peek fragt TopYou nur mit solchen Nummern. Hast du nur die Nummer des Zustellers, füg sie ein: Peek verfolgt mehr als 3.500 Paketdienste. Erkennt Peek den Paketdienst nicht an der Nummer, wähl ihn mit „Ändern“ aus.

Peek liest TopYous eigene Tracking-Seite und zeigt den Status, jeden Scan in den Worten von TopYou oder des Zustellers, dessen Scans TopYou weitergibt, das Zielland und das Gewicht aus TopYous Datensatz. Bei TopYous eigenen Scans lässt Peek einen Ort weg, der nur ein Land ist, damit der Scan am Flughafen in China nicht so aussieht, als wäre das Paket schon bei dir. Meldet TopYous Datensatz das Paket als zugestellt oder zurückgeschickt, zeigt Peek „Zugestellt“ oder „Zurück an Absender“, auch wenn die letzte Zeile anders lautet. TopYous eigene Scans laufen auf Pekinger Zeit, die weitergegebenen auf Ortszeit; Peek zeigt alle in der Zeitzone deines Geräts. Einen voraussichtlichen Liefertermin gibt es nicht, weil TopYou keinen nennt.

Bis zu einer Übergabe zeigt Peek nur die TopYou-Nummer. Steht in TopYous Datensatz die Nummer des Zustellers, fragt Peek damit nach: Bei einer internationalen Postnummer (zwei Buchstaben, neun Ziffern, zwei Buchstaben) fragt es die Post des Ziellands, das TopYou nennt, also DHL in Deutschland, die Österreichische Post in Österreich und die Schweizerische Post in der Schweiz. Sonst fragt es den einen Paketdienst, zu dem die Form der Nummer sicher passt, sofern Peek dessen eigene Sendungsverfolgung liest. Zeigt dieser einen Scan, der mindestens so neu ist wie TopYous letzter, verfolgt Peek das Paket dort weiter, zeigt beide Nummern und behält TopYous Scans im Verlauf. Eine Nummer, die zu keinem Paketdienst sicher passt, etwa 20 Ziffern allein, probiert Peek nicht aus; dann bleibt es bei TopYous Tracking-Seite.

Peek schaut tagsüber alle 10 Minuten nach, alle 2 Minuten, sobald das Paket in Zustellung ist, nachts alle ein bis zwei Stunden, und kann dir Mitteilungen schicken: „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“. Mit „Wichtige Schritte“ meldet sich Peek bei den Zollzeilen, auch der zur Ausfuhr in China, und bei der Zustellung, nicht aber beim Flug oder bei der Zollfreigabe. TopYou, den Shop oder die Post kontaktieren kann Peek nicht für dich.

## Fragen zur TopYou Sendungsverfolgung

### Was ist TopYou (通邮)?

深圳市通邮物流科技（集团）股份有限公司, kurz 通邮 oder 通邮集团, ist ein Spediteur aus Shenzhen in China mit internationalen Linien. Seine Nummern bestehen aus TYZR, einem Buchstaben, zehn Ziffern und YQ.

### Welcher Paketdienst stellt TopYou-Pakete in Deutschland zu?

Das steht nicht fest: TopYous Datensatz kann die Nummer des Zustellers enthalten, aber nicht seinen Namen, und laut DHL muss eine Sendung aus dem Ausland nicht die Deutsche Post bringen. Frag den Shop. Ist die zweite Nummer eine Postnummer und Deutschland das Ziel, fragt Peek selbst bei DHL nach.

### Warum zeigt TopYou mein Land, obwohl das Paket noch in China ist?

Weil TopYou bei seinen eigenen Scans das Zielland als Ort angibt, auch am Abflughafen. Halte dich an den Text: `Arrived at airport of the original country` heißt, dass das Paket noch auf seinen Flug wartet.

### Wen frage ich, wenn mein TopYou-Paket nicht ankommt?

Den Shop, bei dem du bestellt hast: Er hat den Versand bei TopYou gebucht. Kontaktdaten von TopYou konnte Peek nicht prüfen. Hat ein Paketdienst in deinem Land, etwa die Post, das Paket schon, fragst du ihn zur Zustellung.

:::sources
- [DHL: Hilfe zu meiner Sendung aus dem Ausland (Import)](https://www.dhl.de/de/privatkunden/hilfe-kundenservice/themen/international/import.html) – Zustellung nicht zwingend durch Deutsche Post oder DHL Paket, Prüfung in der Sendungsverfolgung, Zollanmeldung, mehrere Tage
- [DHL: Hilfe zur Lieferung an eine Filiale](https://www.dhl.de/de/privatkunden/hilfe-kundenservice/empfangen/filiale-direkt.html) – sieben Werktage, Rücksendung
- [DHL: Kontaktkanal Telefon](https://www.dhl.de/de/privatkunden/hilfe-kundenservice/themen/international/probleme-loesungen/kontaktkanal-telefon.html) – Telefonnummer für internationale Sendungen, Zeiten, Sendungsnummer, Postnummer oder Anschrift
- [Österreichische Post: Hilfe zu Ihrem Sendungsstatus](https://www.post.at/p/c/hilfe-sendungsstatus) – Status, gelber Zettel, Abholfrist, Nachforschung bei Sendungen aus dem Ausland
- [Österreichische Post: Kontaktieren Sie uns telefonisch](https://www.post.at/k/c/hotline) – Hotline, Zeiten, Kontaktformular
- [Die Post: Abholungseinladung](https://www.post.ch/de/empfangen/abholungseinladung) – Abholstelle, 15 Tage für Auslandsendungen, kostenlose Verlängerung ohne Konto
- [Die Post: Hilfe und Kontakt](https://www.post.ch/de/hilfe-und-kontakt) – Telefon, Preis, Zeiten, Chat
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Firmenname, TYZR-Form, Großbuchstaben auf TopYous Seite, kein Link, Scan-Texte und ihre Stufen, Zielland bei TopYous Scans, Pekinger und Ortszeit, weitergegebene Scans des Zustellers, zweite Nummer und Linienname, unbekannte Nummern und Nummern ohne Scans, was Peek zeigt und wie es übergibt
:::
