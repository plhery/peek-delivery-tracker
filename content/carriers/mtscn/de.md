---
title: Meiteshun (MTSCN) Sendungsverfolgung: AMTS-Nummer, Status
description: Meiteshun-Paket (美特顺) aus Shenzhen verfolgen: wie die AMTS-Nummer aussieht, was die Schritte bedeuten und warum Meiteshun es vorab als zugestellt anzeigen kann.
slug: meiteshun-sendungsverfolgung
published: 2026-11-03
updated: 2026-11-03
---

Meiteshun Logistics (美特顺物流, 深圳市美特顺物流有限公司, auch MTSCN) ist ein Spediteur aus Shenzhen in China. Hat ein Shop in China deine Bestellung mit Meiteshun verschickt, beginnt deine Sendungsnummer mit AMTS und endet auf FS: Füg sie oben ins Feld ein. Lies Meiteshuns Sendungsverfolgung aber mit Vorsicht: Das System trägt den ganzen Weg eines Pakets samt Zustellung schon ein, wenn die Bestellung angelegt wird. Ein `Delivered` dort beweist also nicht, dass dein Paket angekommen ist.

## Meiteshun Sendungsnummer: so sieht sie aus

Die Frachtbriefnummern von Meiteshun bestehen aus AMTS, neun Ziffern und FS, zusammen 15 Zeichen. Mit erfundenen Ziffern:

| Nummer | Was sie ist |
| --- | --- |
| `AMTS000000017FS` | AMTS, neun Ziffern und FS: eine Frachtbriefnummer von Meiteshun. |
| `AMTS00000001FS` | Nur acht Ziffern: So sehen die Nummern von Meiteshun nicht aus. Prüf, ob eine Ziffer fehlt. |
| `RR000000005CN` | Zwei Buchstaben, neun Ziffern und CN: eine Nummer der [China Post](carrier:china-post), keine von Meiteshun. |

Leerzeichen und Kleinbuchstaben stören Peek beim Einfügen nicht.

**Wo du sie findest.** Der Shop bucht den Versand bei Meiteshun und gibt dir die Nummer weiter. Findest du sie nicht, lies, [wo du die Sendungsnummer findest](guide:find-tracking-number).

**Eine Referenz des Shops.** Meiteshuns Tracking-Seite findet eine Bestellung auch über die Referenz, die der Shop selbst vergeben hat, wenn du sie genau so eintippst, wie der Shop sie schreibt, mit allen Bindestrichen. Sie zeigt dann die AMTS-Nummer dazu.

**Ein Link mit Ziffern statt Namen.** Meiteshuns Tracking-Seite läuft nicht unter einem Domainnamen, sondern unter einer Serveradresse aus Ziffern, und nur über unverschlüsseltes HTTP. Ein Link dorthin hat diese Ziffern an der Stelle, wo sonst der Name einer Website steht, und enthält `trackIndex.htm`, `documentCode=` und deine Nummer oder Referenz: Du kannst ihn so, wie er ist, in Peek einfügen.

## Meiteshun Sendungsverfolgung: was die Status bedeuten

Jede Bestellung bei Meiteshun folgt demselben Ablauf, und die meisten Schritte stehen schon vorab im Datensatz (mehr unten). Meiteshun schreibt sie auf Englisch. Hier stehen sie in der Reihenfolge des Ablaufs, jeweils mit dem Status, den Peek zeigt, solange der Schritt der neueste ist:

| Meiteshun schreibt | Was es für dich heißt | Peek zeigt |
| --- | --- | --- |
| `The package has been collected and is ready to be sent to the distribution center` | Der erste Schritt: Meiteshun übernimmt das Paket in Shenzhen. | „Beim Paketdienst eingegangen“ |
| `item inbound in sorting center` | In einem Sortierzentrum, mit dem Ort `ShenZhen`. | „Unterwegs“ |
| `item outbound in sorting center` | Das Paket verlässt dieses Sortierzentrum. | „Unterwegs“ |
| `Arriving at the terminal` | An einem Flughafenterminal, mit dem Ort `Hong Kong`. | „Unterwegs“ |
| `Leaving the terminal, the airline will take off` | Der Flug ab Hongkong startet. | „Unterwegs“ |
| `Arriving at the destination country` | In deinem Land gelandet. Als Ort steht der zweistellige Ländercode, etwa `DE` für Deutschland. | „Unterwegs“ |
| `Customs clearanceisin progress` | Die Einfuhrverzollung in deinem Land. Die fehlenden Leerzeichen stammen von Meiteshun. | „Beim Zoll“ |
| `Customs clearance completed` | Verzollt und wieder unterwegs. | „Unterwegs“ |
| `Arriving at the mail processing center` | In einem Bearbeitungszentrum in deinem Land. | „Unterwegs“ |
| `Arrive at the sorting and distribution center` | In einem Verteilzentrum in deiner Nähe, mit deinem Ort oder deiner Region. | „Unterwegs“ |
| `package is on the way` | Der Schritt vor der Zustellung. Dass das Paket in Zustellung ist, heißt das nicht. | „Unterwegs“ |
| `Delivered` | Der letzte Schritt des Ablaufs. Er kann vorab geschrieben sein. | „Zugestellt“, sofern Peek den Schritt nicht zurückhält (siehe unten) |

**Orte und Uhrzeiten.** Den Ort eines Schritts schreibt Meiteshun an den Anfang, vor ein Komma. Schritte in deinem Land tragen hinter dem Wortlaut außerdem deinen Ort oder deine Region und die Postleitzahl. Alle Uhrzeiten stehen in Pekinger Zeit (UTC+8), auch bei Schritten in deinem Land, ohne Angabe der Zeitzone. Steht auf Meiteshuns Tracking-Seite 21:00 Uhr, ist es in Deutschland, Österreich und der Schweiz 14:00 Uhr (MEZ), während der Sommerzeit 15:00 Uhr (MESZ).

**Schritte im Voraus.** Meiteshuns Tracking-Seite läuft auf Hualei (华磊), einer Spediteursplattform, die auch andere chinesische Spediteure wie [GZYL](carrier:gzyl) und [Tianhui Hengyuan](carrier:thhy) nutzen. Bei Meiteshun stehen alle Schritte einer Bestellung im Datensatz, sobald sie angelegt ist, jeder mit dem Zeitpunkt, für den er geplant ist, von der Übernahme bis `Delivered`. Die Tracking-Seite blendet einen Schritt ein, sobald seine Uhrzeit erreicht ist. Solche Schritte sind Vorhersagen, keine Scans deines Pakets: Sie sagen, wo das Paket an einem bestimmten Tag sein sollte, nicht, wo es ist. Tracking-Dienste, die Meiteshuns Datensatz übernehmen, zeigen dieselben Schritte.

## Wer Meiteshun-Pakete in Deutschland, Österreich und der Schweiz zustellt

Meiteshuns Schritte in deinem Land nennen keine Firma, die dort zustellt. Als Versandart steht in den Datensätzen MTS全球小包 (etwa: weltweites Kleinpaket von MTS). Übergeben kann Peek ein Meiteshun-Paket nur an die [Schweizerische Post](carrier:swiss-post): bei einem Paket in die Schweiz oder nach Liechtenstein, und nur wenn der Datensatz sie mit einer ihrer Nummern nennt (mehr unten).

Lieferzeiten von Meiteshun hat Peek nicht gefunden, ebenso wenig Regeln zu Zustellversuchen, Abholstellen oder dazu, wie lange ein Paket zur Abholung bereitliegt. Frag den Shop, welcher Paketdienst deine Bestellung zustellt und unter welcher Nummer: Maßgeblich sind dessen Sendungsverfolgung und Regeln. Sollst du Einfuhrabgaben zahlen, lies [Paket beim Zoll](guide:customs).

## Meiteshun Paket kommt nicht an oder Tracking hängt

**`Delivered`, aber nichts angekommen.** Der Schritt kann zum vorab geschriebenen Ablauf gehören und ist dann kein Scan. Sag dem Shop, dass das Paket nicht angekommen ist, und frag nach der Nummer des Paketdiensts, der zustellt. Mehr unter [Paket zugestellt, aber nicht erhalten](guide:delivered-not-received).

**Jeder Schritt pünktlich, aber kein Paket.** Die Schritte wurden beim Anlegen der Bestellung geschrieben: Sie zeigen den Plan, nicht, wo das Paket gerade ist. Halte dich an die Lieferzeit, die der Shop genannt hat, und frag dort nach, wenn sie verstrichen ist.

**In Peek hängt das Paket in Shenzhen.** Zeigt Peek nur die ersten Schritte in Shenzhen, ein Tracking-Link des Shops aber mehr, dann sind die Schritte, die Peek weglässt, vorab geschrieben oder auf einen Zeitpunkt nach Peeks Abfrage datiert (mehr unten). Siehe auch [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating) und [Paket aus China verfolgen](guide:tracking-from-china).

**Nichts gefunden.** Kennt Meiteshuns Tracking-Seite eine Nummer nicht, zeigt sie deine Nummer unter 参考号 (Referenz), alle anderen Felder leer. Vergleich die Nummer mit der Nachricht des Shops.

**Verloren, beschädigt oder zurück.** Den Versand bei Meiteshun hat der Shop gebucht, also bittest du ihn oder den Marktplatz um Erstattung oder ein neues Paket. Siehe [Paket verloren](guide:lost-parcel) und [Paket zurück an den Absender](guide:returned-to-sender).

## Meiteshun Kontakt und Kundenservice

Stand 28. Oktober 2026 hat Peek keine Kontaktdaten von Meiteshun auf einer Seite gefunden, die es lesen konnte: keine Telefonnummer, E-Mail-Adresse oder Anschrift für Empfänger. Für alles rund um deine Bestellung ist der Shop zuständig, und wenn er nicht antwortet, der Marktplatz.

## Meiteshun Paket verfolgen mit Peek

[Peek](/) erkennt eine Meiteshun-Nummer an ihrer Form: AMTS, neun Ziffern und FS, eine Form, die kein anderer Paketdienst in Peeks Katalog nutzt. Einen Link zu Meiteshuns Tracking-Seite mit `documentCode=` und der Nummer oder der Referenz des Shops kannst du ebenfalls einfügen. Peek verfolgt mehr als 3.500 Paketdienste, aber einer Referenz des Shops sieht es nicht an, zu welchem sie gehört. Füg sie trotzdem ein, tipp auf „Ändern“ und wähl Meiteshun Logistics (auch über 美特顺 oder MTSCN findest du Meiteshun), oder nimm „Paketdienst ändern“ bei einem Paket, das du schon hinzugefügt hast. Peek zeigt dann auch die AMTS-Nummer der Bestellung, mit der Beschriftung „Meiteshun Logistics führt die Sendung unter“. Peek entfernt allerdings Bindestriche, ob eingetippt oder im Link, und findet eine Referenz mit Bindestrichen deshalb nicht: Such sie auf Meiteshuns eigener Tracking-Seite oder frag den Shop nach der AMTS-Nummer.

Peek liest Meiteshuns eigene Tracking-Seite und zeigt den Status, jeden Schritt mit seinem Ort und das Zielland. Die Schritte zeigt Peek so, wie Meiteshun sie schreibt, auf Englisch; nur `Delivered` steht im Verlauf auf Deutsch, als „Zugestellt“. `Customs clearanceisin progress` zählt als „Beim Zoll“, `package is on the way` als „Unterwegs“, nicht als „In Zustellung“. Aus den Schritten in deinem Land übernimmt Peek deinen Ort und lässt die Postleitzahl weg. Die Uhrzeiten liest Peek in Pekinger Zeit und zeigt sie in der Zeitzone deines Geräts. Namen, Adresse, Gewicht, Gebühren, Unterschrift und Zustellnachweis, die Meiteshuns Datensatz enthalten kann, lässt Peek weg. Einen voraussichtlichen Liefertermin zeigt es nicht, weil Meiteshun keinen nennt.

Peek hält jeden Schritt zurück, der mehr als zwölf Stunden vor seiner eigenen Uhrzeit geschrieben wurde, und jeden, dessen Uhrzeit nach dem Moment der Abfrage liegt, gemessen in Pekinger Zeit. Peek richtet sich nach den Schritten, die es behält, nicht nach dem Gesamtstatus des Datensatzes. Wurde der ganze Ablauf einer Bestellung schon bei ihrem Anlegen geschrieben, behält Peek nur die Schritte, die vor diesem Moment oder bis zu zwölf Stunden danach datiert sind: Das damals geschriebene `Delivered` hält es zurück. Muss Peek die Seite `trackIndex.htm` lesen, weil die Abfrage der Tracking-Seite für die Nummer keine Schritte liefert, hält es dort jedes `Delivered` zurück: Diese Seite zeigt nicht, wann ein Schritt geschrieben wurde. Hält Peek alle Schritte einer Bestellung zurück, hat das Paket in Peek noch keinen Sendungsverlauf, und Peek zeigt „Warten auf den Paketdienst“. Ob Peek einige Schritte zurückhält oder alle: Kopien der vorab geschriebenen Schritte lässt es auch bei Tracking-Diensten weg.

Peek verfolgt das Paket auf Meiteshuns Tracking-Seite. Übergeben kann es ein Meiteshun-Paket nur an die Schweizerische Post, und nur wenn der Datensatz sie als Linie nennt, eine Nummer der Schweizerischen Post enthält und kein Zielland außerhalb der Schweiz und Liechtensteins angibt. Das Paket zeigt dann „Zustellung durch Swiss Post“ und diese Nummer. Solange Peek das Paket bei der Post nur angekündigt findet, noch ohne eigenen Scan, zeigt es stattdessen „Bei Swiss Post angekündigt, noch nicht eingetroffen“. Gibt dir der Shop die Nummer eines anderen Paketdiensts, der zustellt, füg sie als eigenes Paket hinzu.

Peek schaut alle 10 Minuten nach und alle 2 Minuten, sobald das Paket in Zustellung ist (`package is on the way` zählt nicht dazu), am Tag der Zustelltour ab 6:00 Uhr Ortszeit oder, wenn der Paketdienst ein Zeitfenster nennt, ab einer halben Stunde vor dessen Beginn, und solange Meiteshun oder die Schweizerische Post selbst antwortet; über Tracking-Dienste alle 15 Minuten. Kommt zwei Tage lang nichts Neues (solange das Paket noch „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ zeigt, schon nach 12 Stunden), schaut Peek nur noch stündlich nach, und bei einer Bestellung, deren ganzer Ablauf beim Anlegen geschrieben wurde, ist das bald so weit. Als Benachrichtigung wählst du „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“. Von den Schritten des Ablaufs meldet „Wichtige Schritte“ nur `Customs clearanceisin progress` und `Delivered`. Meiteshun oder den Shop kontaktieren kann Peek nicht für dich.

## Fragen zur Meiteshun Sendungsverfolgung

### Was ist Meiteshun Logistics (美特顺)?

Ein Spediteur aus Shenzhen in China, mit vollem Namen 深圳市美特顺物流有限公司 (Shenzhen Meiteshun Logistics), in Peeks Katalog auch unter MTSCN zu finden. Seine Frachtbriefnummern bestehen aus AMTS, neun Ziffern und FS, und als Versandart steht in seinen Datensätzen MTS全球小包.

### Ist eine AMTS-Nummer eine Nummer der China Post?

Nein. AMTS, neun Ziffern und FS ist die Form von Meiteshun. Internationale Nummern der China Post bestehen aus zwei Buchstaben, neun Ziffern und CN.

### Warum zeigt Meiteshun mein Paket als zugestellt, obwohl es nicht da ist?

Wahrscheinlich, weil Meiteshuns System jeden Schritt einer Bestellung, die Zustellung eingeschlossen, schon beim Anlegen einträgt. Ein so geschriebenes `Delivered` ist eine Vorhersage, kein Scan. Sag dem Shop, dass das Paket nicht angekommen ist.

### Wer stellt Meiteshun-Pakete in Deutschland, Österreich und der Schweiz zu?

Meiteshuns Schritte sagen es nicht: Keiner nennt einen Paketdienst. Frag den Shop, wer deine Bestellung zustellt und unter welcher Nummer. Nur bei Paketen in die Schweiz und nach Liechtenstein kann Peek das Paket an die Schweizerische Post übergeben, wenn der Datensatz sie mit einer ihrer Nummern nennt.

### Wen frage ich, wenn mein Meiteshun-Paket nicht ankommt?

Den Shop, bei dem du bestellt hast: Er hat den Versand bei Meiteshun gebucht. Kontaktdaten von Meiteshun hat Peek auf keiner Seite gefunden, die es lesen konnte.

:::sources
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Firmen- und andere Namen, Hualei-Plattform, AMTS-Form, Tracking-Seite unter einer Serveradresse aus Ziffern und ihre Links, Suche nach Referenz mit Bindestrichen, Ablauf und Wortlaute der Schritte, Orte und Pekinger Zeit, im Voraus geschriebene Schritte, Antwort bei unbekannter Nummer, Versandart, kein Paketdienst in den Schritten, Übergabe an die Schweizerische Post, was Peek zeigt, zurückhält und weglässt
:::
