---
title: GZYL Sendungsverfolgung: YL…YQ-Nummer, Status und Zustellung
description: GZYL-Paket (广州云链) aus Guangzhou verfolgen: wie die YL…YQ-Nummer aussieht, was die Schritte heißen und warum ein Paket dort vorab als zugestellt stehen kann.
slug: gzyl-sendungsverfolgung
published: 2026-11-02
updated: 2026-11-06
---

GZYL (广州云链, Guangzhou Yunlian) ist 广州云链货运代理有限公司, ein Spediteur aus Guangzhou in China. Hat ein Shop in China deine Bestellung mit GZYL verschickt, besteht deine Sendungsnummer aus YL, zehn Ziffern und YQ: Füg sie oben ins Feld ein. GZYLs Website, yunlianhouyun.com, ist auf Chinesisch, und ihre Paketsuche (小包查询) öffnet eine Tracking-Seite, die nur unter einer IP-Adresse läuft. Lies die Tracking-Seite mit Vorsicht: GZYL schreibt einen großen Teil des Verlaufs im Voraus, deshalb ist ein Schritt dort, selbst `Delivered`, nicht immer ein Scan.

## GZYL Sendungsnummer: so sieht sie aus

Die Frachtbriefnummern von GZYL bestehen aus YL, zehn Ziffern und YQ, zusammen 14 Zeichen. Mit erfundenen Ziffern:

| Nummer | Was sie ist |
| --- | --- |
| `YL0000000000YQ` | YL, zehn Ziffern und YQ: So sehen die Nummern von GZYL aus. Peek fragt dazu bei GZYLs Tracking-Seite nach. |
| `YL000000000YQ` | Nur neun Ziffern: So sehen die Nummern von GZYL nicht aus. Prüf, ob eine Ziffer fehlt. |
| `GZXH0000000000YQ` | GZXH, zehn Ziffern und YQ: Xinghe Freight (星和货运), ein anderer Spediteur. Seine Sendungsverfolgung läuft über GZYLs Tracking-Seite, mit GZYLs Wortlaut. |
| `JXCAA0000000000YQ` | Endet auch auf YQ, wie die Nummern vieler chinesischer Spediteure, beginnt aber mit JXC: eine Nummer von Juxi Express. |

Leerzeichen und Kleinbuchstaben stören Peek beim Einfügen nicht.

**Wo du sie findest.** Der Shop bucht den Versand bei GZYL und gibt dir die Nummer weiter. Findest du sie nicht, lies, [wo du die Sendungsnummer findest](guide:find-tracking-number).

## GZYL Sendungsverfolgung: was die Status bedeuten

GZYL schreibt seine eigenen Schritte auf Englisch, samt Tippfehlern. In der Reihenfolge, in der ein Paket sie bekommt:

| GZYL schreibt | Was es für dich heißt | Peek zeigt |
| --- | --- | --- |
| `Order information received` | Der Shop hat GZYL die Daten der Bestellung geschickt. Das Paket hat GZYL noch nicht. | „Etikett erstellt“ |
| `Arrive at the cross border receiving point` | GZYL hat das Paket: der erste Scan in seinen Händen. | „Beim Paketdienst eingegangen“ |
| `Goods leave the operation center` | Das Paket hat das Umschlagzentrum von GZYL verlassen. | „Unterwegs“ |
| `Arrive at the international airport` | Am Flughafen, wo es auf seinen Flug wartet. | „Unterwegs“ |
| `Departing from the airport` | Der Flug ist gestartet. | „Unterwegs“ |
| `Item arrives at destination airport` | In deinem Land gelandet. | „Unterwegs“ |
| `local carrier Agent Import customs clearance` | Ein Dienstleister in deinem Land verzollt das Paket für die Einfuhr. | „Beim Zoll“ |
| `The import customs keep it for inspection` | Der Zoll in deinem Land kontrolliert das Paket. | „Beim Zoll“ |
| `Customs clearance completed` | Verzollt und wieder unterwegs. | „Unterwegs“ |
| `Internationaliy Package received at processing center` | Ein Bearbeitungszentrum in deinem Land hat das verzollte Paket übernommen. Eine neue Annahme ist das nicht. | „Unterwegs“ |
| `Leave the email processing center` | Das Paket hat dieses Bearbeitungszentrum verlassen. | „Unterwegs“ |
| `Arrival sorting, distribution center` | In einem Verteilzentrum in deinem Land. Den Ort schreibt GZYL hinter den Wortlaut. | „Unterwegs“ |
| `Leaving the sorting center` | Verlässt ein Sortierzentrum, mit dessen Ort. | „Unterwegs“ |
| `Package is on the way` | Weiter unterwegs in deinem Land, mit einem Ort. Dass es in der Zustellung ist, heißt das nicht. | „Unterwegs“ |
| `Delivered` | Die Zustellung, mit dem Ort. Das kann ein Plan sein und kein Scan: siehe unten. | „Zugestellt“, mit einem Hinweis auf den festen Zeitplan, sofern Peek den Schritt nicht zurückhält (siehe unten) |

**Über die USA.** Ein Paket kann auch `Los Angeles Processing Center has received it` zeigen, mit einer Stadt in Klammern dahinter: Ein Bearbeitungszentrum in den USA hat es auf dem Weg in sein Zielland übernommen. Peek zeigt „Unterwegs“.

**Eine Zeile auf Chinesisch.** Bei einer Bestellung ohne Scans schreibt die Plattform, auf der die Tracking-Seite von GZYL läuft, von selbst `货物电子信息已经收到` (die elektronischen Warendaten sind eingegangen). Der Shop hat die Bestellung angelegt, aber niemand hat das Paket gescannt. Peek zeigt die Zeile auf Chinesisch, unter „Etikett erstellt“.

**Über China Post.** Ein Paket kann auch `广州处理中心已收寄` zeigen, mit der Stadt in Klammern dahinter: die Annahme durch China Post in ihrem Bearbeitungszentrum in Guangzhou, die GZYL auf Chinesisch weitergibt. Bei Nummern von Xinghe Freight ist `香港处理中心已收寄` dieselbe Annahme im Bearbeitungszentrum in Hongkong. Peek zeigt beide Zeilen auf Chinesisch, unter „Beim Paketdienst eingegangen“.

**Uhrzeiten.** Alle Zeiten auf GZYLs Tracking-Seite stehen in Pekinger Zeit (UTC+8), auch bei Schritten in deinem Land, ohne Angabe der Zeitzone. Steht dort 20:00 Uhr, ist es in Deutschland, Österreich und der Schweiz 13:00 Uhr (MEZ), während der Sommerzeit 14:00 Uhr (MESZ).

**Schritte im Voraus.** GZYL schreibt einen großen Teil des Verlaufs vorab, von der Annahme bis zum letzten Schritt in deinem Land. Ein so geschriebener Schritt steht schon lange vor der Uhrzeit, die er zeigt, im Datensatz, und die Tracking-Seite blendet ihn ein, sobald diese Uhrzeit erreicht ist. Er ist ein Plan, kein Scan deines Pakets. Meiteshun, ein Spediteur auf derselben Tracking-Plattform, macht das auch. Am Ende von GZYLs Plan steht dann ein `Delivered`, das die Plattform an dem Tag, den es zeigt, in einem abendlichen Durchlauf einträgt: datiert auf den Tag nach dem letzten Schritt des Plans, zu Pekinger Bürozeiten, egal in welchem Land du wohnst. Tracking-Dienste, die den Datensatz von GZYL übernehmen, können diese Schritte ebenfalls zeigen. Ein `Delivered` bei GZYL oder bei einem Tracking-Dienst beweist also nicht, dass dein Paket angekommen ist.

## Wer GZYL-Pakete zustellt

Wer GZYL-Pakete in Deutschland, Österreich oder der Schweiz zustellt, sagen die Schritte von GZYL nicht: Sie nennen in deinem Land keine Firma. Übergeben kann Peek ein GZYL-Paket nur an die [Schweizerische Post](carrier:swiss-post), und nur wenn der Datensatz von GZYL sie als Linie nennt, eine Nummer der Schweizerischen Post enthält und kein Zielland außerhalb der Schweiz und Liechtensteins angibt. Das Paket zeigt dann „Zustellung durch Swiss Post“ und diese Nummer. Solange Peek das Paket bei der Post nur angekündigt findet, noch ohne eigenen Scan, zeigt es stattdessen „Bei Swiss Post angekündigt, noch nicht eingetroffen“. Sobald die Schweizerische Post einen Scan zeigt, der mindestens so neu ist wie der neueste von GZYL, verfolgt Peek das Paket dort weiter und behält die Schritte von GZYL im Verlauf.

Lieferzeiten von GZYL hat Peek nicht gefunden, ebenso wenig Regeln zu Zustellversuchen, Abholstellen oder dazu, wie lange ein Paket zur Abholung bereitliegt: Das regelt der Paketdienst, der zustellt. Frag den Shop, wer deine Bestellung bringt und unter welcher Nummer. Bringt sie die Schweizerische Post, steht auf Peeks [Seite zur Post](carrier:swiss-post), was nach einer Abholungseinladung zu tun ist.

## GZYL Paket kommt nicht an oder Tracking hängt

**Nur `Order information received` oder `货物电子信息已经收到`.** Der Shop hat die Bestellung angelegt, aber GZYL hat das Paket nicht gescannt. Tut sich nach ein paar Tagen nichts, frag den Shop, ob er es wirklich verschickt hat.

**Hängt beim Zoll.** Sollst du Einfuhrabgaben zahlen, lies [Paket beim Zoll](guide:customs). In Deutschland und Österreich kommt seit dem 1. November 2026 eine Abfertigungspauschale der EU von 2 € je Warenkategorie dazu, egal wie viel die Ware wert ist. Sind die Abgaben nicht schon im Preis des Shops enthalten, legt sie in Deutschland laut Zoll in der Regel der Paketdienst aus, und du zahlst sie bei der Zustellung. Bleibt das Paket tagelang dort, sag dem Shop Bescheid.

**Die Tracking-Seite oder ein Tracking-Dienst ist weiter als Peek.** Die zusätzlichen Schritte können vorab geschriebene sein, samt dem `Delivered` am Ende von GZYLs Plan, und die hält Peek zurück (mehr unten): ein Plan von GZYL, keine Scans. Halte dich an die Lieferzeit, die der Shop genannt hat, und frag dort nach, wenn sie verstrichen ist. Mehr unter [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating) und [Paket aus China verfolgen](guide:tracking-from-china).

**`Delivered`, aber nichts angekommen.** Der Schritt kann zum Plan von GZYL gehören und ist dann kein Scan. Stellt nicht die Schweizerische Post das Paket zu, schreibt Peek das unter „Zugestellt“ dazu: „GZYL aktualisiert nach festem Zeitplan, ohne Unterschrift. Ist nichts angekommen, frag beim Verkäufer nach.“ Sag dem Shop, dass das Paket nicht da ist, und frag, welcher Paketdienst es zustellt und unter welcher Nummer. Siehe [Paket zugestellt, aber nicht erhalten](guide:delivered-not-received).

**Nichts gefunden.** Kennt GZYLs Tracking-Seite eine Nummer nicht, zeigt sie ein einziges Ergebnis mit deiner Nummer unter 参考号 (Referenz), alle anderen Felder leer. Peek prüft weiter, aber vergleich die Nummer mit der Nachricht des Shops.

**Verloren oder zurück.** Den Versand bei GZYL hat der Shop gebucht, also bittest du ihn oder den Marktplatz um Erstattung oder ein neues Paket. Siehe [Paket verloren](guide:lost-parcel) und [Paket zurück an den Absender](guide:returned-to-sender).

## GZYL Kontakt und Kundenservice

Stand 4. November 2026 nennt die Kontaktseite (联系我们) von GZYLs chinesischer Website eine 24-Stunden-Hotline, ein WeChat-Konto und ein QQ-Konto für den Kundendienst, aber nichts für Empfänger: Diese Kanäle sind für GZYLs Kunden, die Shops. Der Shop hat den Versand gebucht und ist für deine Bestellung zuständig, und wenn er nicht antwortet, der Marktplatz.

## GZYL Paket verfolgen mit Peek

[Peek](/) kann eine GZYL-Nummer an ihrer Form allein nicht sicher zuordnen: YL, zehn Ziffern und YQ ist nur ein schwacher Hinweis. Füg sie trotzdem ein: Peek verfolgt mehr als 3.500 Paketdienste. Beim Hinzufügen fragt Peek bei der Tracking-Seite von GZYL nach („Tracking-Dienste werden geprüft…“) und zeigt „GZYL hat dieses Paket.“, wenn sie die Nummer kennt. Sonst tipp auf „Ändern“ und wähl GZYL unter „Passt zu dieser Nummer“ (auch über 广州云链 findest du GZYL), oder nimm „Paketdienst ändern“ bei einem Paket, das du schon hinzugefügt hast. Eine Referenz des Shops funktioniert auch, aber nur, wenn du GZYL selbst wählst: Tipp auf „Ändern“ (oder „Paketdienst ändern“) und such nach GZYL. Peek zeigt dann die eigene Nummer der Bestellung mit der Beschriftung „GZYL führt die Sendung unter“. Peek entfernt allerdings Bindestriche, deshalb findet es eine Referenz mit Bindestrichen nicht: Frag den Shop nach der YL…YQ-Nummer.

Peek liest die eigene Tracking-Seite von GZYL und zeigt den Status, jeden Schritt mit seinem Ort und, wenn der Datensatz es nennt, das Zielland. Die Schritte zeigt Peek so, wie GZYL sie schreibt, auf Englisch und die chinesischen Zeilen unübersetzt; nur `Delivered` steht im Verlauf auf Deutsch, als „Zugestellt“. Die Uhrzeiten liest Peek in Pekinger Zeit und zeigt sie in der Zeitzone deines Geräts. Namen, Adresse, Postleitzahlen, Gewicht, Gebühren, Unterschrift und Zustellnachweis, die der Datensatz enthalten kann, lässt Peek weg. Einen voraussichtlichen Liefertermin zeigt es nicht, weil GZYL keinen nennt.

Peek hält jeden Schritt zurück, der mehr als zwölf Stunden vor seiner eigenen Uhrzeit geschrieben wurde, und jeden, dessen Uhrzeit nach dem Moment der Abfrage liegt, gemessen in Pekinger Zeit. Hat eine Bestellung vorab geschriebene Schritte, hält Peek auch `Delivered` zurück, selbst eines, das erst an seinem eigenen Tag geschrieben wurde. Hat die Datenschnittstelle der Tracking-Seite keine Schritte zu einer Nummer, liest Peek stattdessen die Tracking-Seite selbst; dort steht nicht, wann ein Schritt geschrieben wurde, deshalb hält Peek dort jedes `Delivered` zurück. Peek richtet sich nach den Schritten, die es behält, nicht nach dem Gesamtstatus des Datensatzes. Hält Peek alle Schritte einer Bestellung zurück, hat sie in Peek noch keinen Sendungsverlauf. Kopien der Schritte, die Peek als Plan von GZYL zurückhält, `Delivered` eingeschlossen, lässt Peek auch bei Tracking-Diensten weg, selbst wenn es andere Schritte der Bestellung behält. Ist GZYLs Tracking-Seite aber nicht erreichbar, fragt Peek Tracking-Dienste, und deren Kopien können vorab geschriebene Schritte enthalten.

Ohne Übergabe an die Schweizerische Post bleibt Peek bei GZYL: Gibt dir der Shop die Nummer des Paketdiensts, der zustellt, füg sie als eigenes Paket hinzu.

Peek schaut alle 10 Minuten nach. Kommt zwei Tage lang nichts Neues (solange das Paket noch „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ zeigt, schon nach 12 Stunden), schaut Peek nur noch stündlich nach. Als Benachrichtigung wählst du „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“: „Wichtige Schritte“ umfasst die beiden Zollschritte von GZYL und die Zustellung, nicht die Flüge oder die Sortierung. GZYL oder den Shop kontaktieren kann Peek nicht für dich.

## Fragen zur GZYL Sendungsverfolgung

### Was ist GZYL (广州云链)?

广州云链货运代理有限公司 (Guangzhou Yunlian), ein Spediteur aus Guangzhou in China. Seine Frachtbriefnummern bestehen aus YL, zehn Ziffern und YQ.

### Ist jede YL…YQ-Nummer eine GZYL-Nummer?

Nicht mit Sicherheit. YL, zehn Ziffern und YQ ist die Form von GZYL, und kein anderer Paketdienst in Peeks Katalog nutzt sie, aber Peek fragt trotzdem bei der Tracking-Seite von GZYL nach, bevor es die Nummer GZYL zuordnet. Mit neun oder elf Ziffern ist es nicht die Form von GZYL.

### Warum meldet GZYL mein Paket als zugestellt, obwohl es nicht da ist?

Vielleicht gehört der Schritt zum Plan von GZYL. GZYL schreibt einen großen Teil des Verlaufs vorab und lässt ihn mit einem `Delivered` enden, und solche Schritte sind ein Plan, keine Scans. Von GZYLs Tracking-Seite hält Peek solche Schritte zurück, `Delivered` eingeschlossen, und unter einem „Zugestellt“, das es zeigt, weist es darauf hin, dass GZYLs Sendungsverfolgung einem festen Zeitplan folgt. Sag dem Shop, dass das Paket nicht angekommen ist, und frag, wer es zustellt.

### Wer stellt GZYL-Pakete in Deutschland, Österreich und der Schweiz zu?

Die Schritte von GZYL nennen keine Firma, deshalb weiß Peek es nicht: Frag den Shop, welcher Paketdienst zustellt und unter welcher Nummer. Nur in der Schweiz und in Liechtenstein kann Peek ein GZYL-Paket an die Schweizerische Post übergeben, wenn der Datensatz von GZYL sie mit einer ihrer Nummern nennt.

:::sources
- [广州云链货运代理有限公司 (GZYL): Startseite (auf Chinesisch)](http://yunlianhouyun.com/) – Firmenname, Website auf Chinesisch, Paketsuche (小包查询) auf GZYLs Tracking-Seite
- [广州云链货运代理有限公司 (GZYL): 联系我们 (Kontakt, auf Chinesisch)](http://yunlianhouyun.com/contact.html) – Hotline, WeChat und QQ für den Kundendienst
- [Zoll: Internetbestellungen](https://www.zoll.de/DE/Privatpersonen/Postsendungen-Internetbestellungen/Sendungen-aus-einem-Nicht-EU-Staat/Zoll-und-Steuern/Internetbestellungen/internetbestellungen_node.html) – Abfertigungspauschale von 2 € je Warenkategorie seit 1. November 2026, Paketdienst legt die Abgaben aus, Zahlung bei der Zustellung, Preis im Shop prüfen
- [EU-Kommission: EU Customs Reform (auf Englisch)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – Abfertigungspauschale der EU von 2 € seit 1. November 2026, in der ganzen EU
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Firmen- und andere Namen, Tracking-Seite unter einer IP-Adresse, YL…YQ-Form und ihre schwache Zuordnung, Xinghe Freight auf GZYLs Tracking-Seite und seine Annahme in Hongkong, Suche nach Nummer oder Referenz mit Bindestrichen, Wortlaute der Schritte und ihre Phasen, die chinesische Zeile der Plattform, Orte hinter dem Wortlaut, Pekinger Zeit, im Voraus geschriebener Verlauf, Antwort bei unbekannter Nummer, Tracking-Seite statt Datenschnittstelle, Übergaberegel der Schweizerischen Post, was Peek zeigt, zurückhält und weglässt
:::
