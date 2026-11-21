---
title: Xinghe Freight Sendungsverfolgung: GZXH…YQ-Nummer & Status
description: Xinghe-Freight-Paket (星和货运) aus Guangzhou verfolgen: wie die GZXH…YQ-Nummer aussieht, auf wessen Tracker sie läuft, was die Status heißen und wen du fragst.
slug: xinghe-freight-sendungsverfolgung
published: 2026-11-18
updated: 2026-11-21
---

Xinghe Freight (星和货运, 广州星和货运代理有限公司) ist ein Spediteur aus Guangzhou in China. Hat ein chinesischer Online-Shop deine Bestellung mit Xinghe verschickt, besteht deine Sendungsnummer aus GZXH, zehn Ziffern und YQ – genau die Form, die Peek als Xinghe erkennt. Füg sie oben ins Suchfeld ein. Die Sendungsverfolgung von Xinghe läuft auf dem Tracker von [GZYL](carrier:gzyl), einem weiteren Spediteur aus Guangzhou: Das Suchfeld auf Xinghes eigener Website leitet deine Nummer dorthin weiter. Dieser Tracker liegt ohne Domainnamen auf einer bloßen numerischen Serveradresse über unverschlüsseltes HTTP. Peek liest ihn für dich aus und fragt Tracking-Dienste ab, wenn der Tracker deine Nummer nicht kennt.

## Xinghe Freight Sendungsnummer: so sieht sie aus

Die Nummern, die Peek Xinghe Freight zuordnet, bestehen aus GZXH, zehn Ziffern und YQ, zusammen 16 Zeichen. Mit erfundenen Ziffern:

| Nummer | Was sie ist |
| --- | --- |
| `GZXH4829105736YQ` | GZXH, zehn Ziffern und YQ: das Format von Xinghe Freight. Peek nennt sofort Xinghe Freight. |
| `GZXH482910573YQ` | Nur neun Ziffern: nicht das Format von Xinghe. Prüf, ob eine Ziffer fehlt. |
| `YL4829105736YQ` | YL, zehn Ziffern und YQ: das Frachtbriefformat von [GZYL](carrier:gzyl). Derselbe Tracker, aber ein anderer Spediteur. |
| `JXCAA4829105736YQ` | Endet ebenfalls auf YQ, wie bei vielen chinesischen Spediteuren, beginnt aber mit JXC: eine Nummer von [Juxi Express](carrier:juxi-express). |

Leerzeichen, Bindestriche und Kleinbuchstaben spielen keine Rolle, wenn du die Nummer in Peek einfügst.

**Wo du sie findest.** Der Verkäufer bucht den Versand bei Xinghe und gibt die Nummer an dich weiter. Findest du sie nicht, lies [wo du deine Sendungsnummer findest](guide:find-tracking-number).

## Xinghe Freight Sendungsverfolgung: was die Status bedeuten

Da Xinghes Sendungsverfolgung über den Tracker von GZYL läuft, erscheinen die Schritte im englischen Wortlaut von GZYL – Tippfehler eingeschlossen. Peek ordnet sie anhand von GZYLs Statusliste ein. In der Reihenfolge, in der ein Paket sie erhält:

| Was der Tracker zeigt | Was es für dich bedeutet | Peek zeigt |
| --- | --- | --- |
| `Order information received` | Der Shop hat die Auftragsdaten übermittelt. Noch hat niemand das Paket physisch übernommen. | „Etikett erstellt“ |
| `Arrive at the cross border receiving point` | Der Spediteur hat das Paket erhalten: der erste Scan in seinen Händen. | „Beim Paketdienst eingegangen“ |
| `Goods leave the operation center` | Das Paket hat das Betriebszentrum verlassen. | „Unterwegs“ |
| `Arrive at the international airport` | Am Flughafen, wartet auf den Abflug. | „Unterwegs“ |
| `Departing from the airport` | Das Flugzeug ist gestartet. | „Unterwegs“ |
| `Item arrives at destination airport` | Im Zielland gelandet. | „Unterwegs“ |
| `local carrier Agent Import customs clearance` | Ein Zollagent im Zielland erledigt die Einfuhrverzollung. | „Beim Zoll“ |
| `The import customs keep it for inspection` | Der Zoll deines Landes prüft die Sendung. | „Beim Zoll“ |
| `Customs clearance completed` | Verzollt und wieder auf dem Weg. | „Unterwegs“ |
| `Internationaliy Package received at processing center` | Ein Bearbeitungszentrum in deinem Land hat das verzollte Paket übernommen. Das ist keine erneute Abholung beim Absender. | „Unterwegs“ |
| `Leave the email processing center` | Das Paket hat dieses Bearbeitungszentrum verlassen. | „Unterwegs“ |
| `Arrival sorting, distribution center` | Ankunft in einem Verteilzentrum deines Landes, gefolgt vom Ortsnamen. | „Unterwegs“ |
| `Leaving the sorting center` | Verlässt ein Sortierzentrum, mit Ortsangabe. | „Unterwegs“ |
| `Package is on the way` | Weitertransport im Zielland, mit Ortsangabe. Das heißt noch nicht, dass das Paket im Zustellfahrzeug liegt. | „Unterwegs“ |
| `Delivered` | Die Zustellung, mit Ortsangabe. Kann ein vorab eingetragener Plan statt eines Scans sein (siehe unten). | „Zugestellt“, mit Hinweis auf den festen Zeitplan, sofern Peek den Schritt nicht zurückhält (unten) |

**Über Hongkong.** Bei manchen Sendungen steht `香港处理中心已收寄【Hong Kong】`: die Einlieferung im Bearbeitungszentrum Hongkong, auf Chinesisch mit der Stadt in Klammern. Peek übernimmt die Zeile unverändert unter „Beim Paketdienst eingegangen“.

**Eine Zeile auf Chinesisch.** Liegt für eine Bestellung noch kein Scan vor, kann die Tracking-Seite `货物电子信息已经收到` zeigen („die elektronischen Warendaten wurden empfangen“). Die Plattform trägt diese Zeile selbst ein: Der Shop hat den Auftrag angelegt, aber niemand hat das Paket gescannt. Peek zeigt die Zeile auf Chinesisch unter „Etikett erstellt“.

**Uhrzeiten.** Peek liest jede Uhrzeit auf dem Tracker als Pekinger Zeit (UTC+8) – auch für Schritte im Zielland – und rechnet sie in die Zeitzone deines Geräts um. Ein Eintrag um 20:00 Uhr Pekinger Zeit entspricht in Deutschland, Österreich und der Schweiz im Winter 13:00 Uhr (MEZ) und während der Sommerzeit vom letzten Sonntag im März bis zum letzten Sonntag im Oktober 14:00 Uhr (MESZ).

**Vorab eingetragene Schritte.** Auf dieser Plattform können Spediteure den gesamten Verlauf eines Pakets im Voraus anlegen, von der Übernahme bis zum letzten Schritt im Zielland; der Tracker blendet jeden Eintrag ein, sobald seine Uhrzeit erreicht ist. Solche Einträge sind ein Fahrplan, kein echter Scan. Auf GZYLs Tracker endet dieser Plan mit einem `Delivered`, das die Plattform am angezeigten Tag abends gesammelt ergänzt: datiert auf den Tag nach dem letzten geplanten Schritt, zu Bürozeiten in Peking, unabhängig vom Zielland. Peek hält solche Schritte zurück (siehe unten), doch die Kopie eines Tracking-Diensts kann sie trotzdem anzeigen – ein `Delivered` dort beweist also nicht, dass dein Paket da ist.

## Wer Xinghe-Freight-Pakete zustellt

Peek weiß nicht, welcher Paketdienst Xinghe-Pakete in Deutschland, Österreich oder anderen europäischen Ländern zustellt: Weder die Schritte des Trackers im Zielland noch Xinghes Website nennen ein Zustellunternehmen. Der einzige Zusteller, an den Peek ein Xinghe-Paket übergeben kann, ist die [Schweizerische Post](carrier:swiss-post) in der Schweiz und in Liechtenstein, sofern der Datensatz sie nennt.

Xinghes eigene Website enthält keinerlei Angaben für Online-Käufer: keine Lieferzeiten, keine Regeln zu Zustellversuchen, Paketshops oder Lagerfristen. Dafür gelten die Bedingungen des Paketdiensts, der vor Ort ausliefert. Frag den Verkäufer, welches Unternehmen deine Bestellung zustellt, und lass dir dessen Sendungsnummer geben.

**Zoll und Einfuhrabgaben in der EU.** Pakete aus China kommen von außerhalb des Zollgebiets der Europäischen Union. Seit dem 1. Juli 2026 fällt auf online außerhalb der EU gekaufte Waren in Sendungen mit einem Sachwert bis 150 € ein Zoll von 3 € pro Warenkategorie an. Seit dem 1. November 2026 kommt unabhängig vom Warenwert eine EU-Bearbeitungsgebühr von 2 € pro Warenkategorie hinzu; eine Kategorie umfasst alle Waren einer Sendung mit derselben Zolltarifnummer, Warenbezeichnung und – soweit anzugeben – demselben Ursprung, ungeachtet der Stückzahl. Wer Einfuhrumsatzsteuer und Gebühren in Deutschland, Österreich oder der Schweiz einzieht, erfährst du unter [Paket beim Zoll](guide:customs).

## Xinghe-Freight-Paket kommt nicht an oder Tracking hängt

**Nur `Order information received` oder `货物电子信息已经收到`.** Der Shop hat die Bestellung angelegt, aber noch niemand hat das Paket gescannt. Tut sich nach einigen Tagen nichts, frag beim Verkäufer nach, ob es tatsächlich übergeben wurde.

**„Warten auf Xinghe Freight“.** Peek hat zu deiner Nummer noch keinen bestätigten Schritt: Entweder kennt GZYLs Tracker die Nummer nicht oder er enthält nur vorab eingetragene Schritte (die bis `Delivered` reichen können), und auch die abgefragten Tracking-Dienste liefern nichts. Bei einem Paket in der iPhone- oder Android-App oder in deinem Konto fragt Peek GZYLs Tracker nach den ersten Stunden alle 6 Stunden weiter ab. Bei einer Abfrage ohne Konto prüft Peek in den ersten 6 Stunden, danach beim Öffnen des Links (höchstens alle 6 Stunden, nach den ersten zwei Tagen höchstens einmal täglich) sowie bei eingeschalteten Mitteilungen einmal täglich bis 30 Tage nach der Abfrage. Kennt GZYLs Tracker eine Nummer nicht, gibt er einen einzelnen Eintrag mit deiner Nummer als Referenz und sonst leeren Feldern zurück: Peek wertet sowohl diese Antwort als auch einen vorab geschriebenen Plan als erfasste Sendung ohne Scan und zeigt weiter „Warten auf Xinghe Freight“, ohne dich zur Prüfung der Nummer aufzufordern. Vergleich sie deshalb selbst Zeichen für Zeichen mit der Nachricht des Shops.

**Hängt beim Zoll.** Wirst du zur Zahlung von Einfuhrabgaben aufgefordert, lies [Paket beim Zoll](guide:customs). Bleibt die Sendung tagelang beim Zoll stehen, gib dem Verkäufer Bescheid.

**Der Tracker oder ein Tracking-Dienst zeigt mehr als Peek.** Zusätzliche Einträge – einschließlich `Delivered` – können zum vorab geschriebenen Plan gehören, den Peek zurückhält. Orientiere dich am Lieferzeitraum des Shops und schreib dem Verkäufer, sobald diese Frist abgelaufen ist. Mehr dazu unter [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating) und [Paket aus China verfolgen](guide:tracking-from-china).

**`Delivered`, aber nichts angekommen.** Der Eintrag kann Teil eines im Voraus angelegten Zeitplans sein. Sofern nicht die Schweizerische Post zustellt, blendet Peek unter „Zugestellt“ den Hinweis ein: „Xinghe Freight aktualisiert nach festem Zeitplan, ohne Unterschrift. Ist nichts angekommen, frag beim Verkäufer nach.“ Melde dem Shop, dass nichts eingetroffen ist, und frag nach dem lokalen Zusteller und dessen Sendungsnummer. Siehe auch [Paket zugestellt, aber nicht erhalten](guide:delivered-not-received).

**Verloren oder zurückgeschickt.** Den Versand mit Xinghe hat der Verkäufer gebucht; wende dich daher für eine Erstattung oder Ersatzlieferung an den Shop oder den Marktplatz. Siehe [Paket verloren](guide:lost-parcel) und [Paket zurück an den Absender](guide:returned-to-sender).

## Xinghe Freight Kontakt und Kundenservice

Xinghes Website (`xinghe-express.cn`), die ausschließlich auf Chinesisch vorliegt, nennt folgende Angaben:

- **Hotline**: `19924483103`, laut Website rund um die Uhr an sieben Tagen die Woche erreichbar (`+86 199 2448 3103` aus dem Ausland).
- **E-Mail**: `3975605292@qq.com`, gedacht für Geschäftsanfragen und Preisangebote.
- **Anschrift**: Guangzhou, Provinz Guangdong (`广东省广州市`), ohne Straßenangabe; vor einem Besuch bittet das Unternehmen um vorherige Kontaktaufnahme.

Für Paketempfänger im Ausland bietet die Seite keinen Kundendienst; das Kontaktformular öffnet lediglich dein E-Mail-Programm mit dieser Adresse. Vertragspartner von Xinghe ist der Verkäufer – wende dich bei Fragen zu deiner Bestellung zuerst an den Shop und, falls er nicht reagiert, an den Marktplatz.

## Xinghe-Freight-Paket mit Peek verfolgen

[Peek](/) ordnet eine GZXH…YQ-Nummer sofort Xinghe Freight zu („Erkannter Paketdienst“). Hat dir der Shop eine Nummer in einem anderen Format geschickt, füg sie trotzdem ein: Peek unterstützt mehr als 4.500 Paketdienste. Um eine Sendung von Hand Xinghe zuzuweisen, tipp auf „Ändern“ und such nach Xinghe Freight (auch 星和货运 findet den Eintrag), oder nutze „Paketdienst ändern“ bei einem bereits gespeicherten Paket.

Peek fragt GZYLs Tracker ab – genau wie das Suchfeld auf Xinghes eigener Website. Es zeigt den Status, jeden Schritt mit seinem Ort und das Zielland, sofern der Datensatz es enthält. Uhrzeiten werden als Pekinger Zeit gelesen und in der Zeitzone deines Geräts dargestellt; auf peektracker.com und in den Apps für iPhone und Android endet der Verlauf mit „Uhrzeiten in deiner Zeitzone:“ und dem Namen der Zeitzone. Namen, Anschrift, Postleitzahlen, Gewicht, Zahlungsangaben, Unterschrift und Zustellnachweis lässt Peek weg; ein voraussichtliches Lieferdatum gibt es nicht, weil der Tracker keines nennt.

Kennt GZYLs Tracker deine Nummer nicht, fragt Peek stattdessen Tracking-Dienste ab und zeigt deren Kopie der Schritte – manchmal die einzige Quelle für eine Xinghe-Nummer. Solange keine Quelle einen bestätigten Schritt liefert, steht „Warten auf Xinghe Freight“.

Peek hält jeden Schritt zurück, der mehr als 12 Stunden vor der angezeigten Uhrzeit geschrieben wurde, oder mehr als 6 Stunden davor in derselben Sekunde wie ein solcher Schritt, sowie jeden Schritt mit einem Zeitpunkt nach der Abfrage (jeweils in Pekinger Zeit). Enthält ein Datensatz vorab geschriebene Schritte, hält Peek auch `Delivered` zurück, selbst wenn es am selben Tag eingetragen wurde. Maßgeblich sind allein die behaltenen Schritte, nicht der Zusammenfassungsstatus des Datensatzes; auch in den Kopien von Tracking-Diensten sortiert Peek die als vorab geplant erkannten Schritte samt `Delivered` aus. Hat der Tracker allerdings gar keinen Datensatz zur Nummer oder antwortet er nicht, kann Peek in der Kopie eines Tracking-Diensts keinen Plan von einem echten Scan unterscheiden. Hält Peek vorab eingetragene Schritte zurück, beginnt der Sendungsverlauf bis zur Zustellung oder Rücksendung mit einer Zeile: „Andere Seiten zeigen vielleicht mehr Schritte, aber das sind nur Vorhersagen.“ Mit „Anzeigen“ dahinter siehst du diese Schritte, ausgegraut, die neuesten zuerst, jeden mit dem Tag, den der Tracker ihm gibt („Vorhergesagt für Mo 12. Okt“). Ein solcher Schritt zählt nie als Scan: Er ändert keinen Status und löst keine Mitteilung aus.

Abgesehen von der Schweizerischen Post übergibt Peek ein Xinghe-Paket an keinen Folge-Paketdienst: Bekommst du vom Verkäufer die Nummer des Zustellers vor Ort, leg sie als eigenes Paket an. Peek prüft bis zu alle 10 Minuten, solange du das Paket geöffnet hast oder am Zustelltag Mitteilungen aktiv sind, und sonst stündlich – ebenso, wenn keine neuen Schritte mehr kommen (nach 12 Stunden bei „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ bzw. nach zwei ruhigen Tagen). Über Tracking-Dienste prüft Peek stündlich, nach einem Tag ohne Scan alle 3 Stunden, und am Tag der Zustellung bis zu alle 15 Minuten, solange du das Paket offen hast oder Mitteilungen eingeschaltet sind. Als Benachrichtigung wählst du „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“: „Wichtige Schritte“ meldet die beiden Zollschritte und `Delivered`, nicht aber Flüge oder Sortierzentren. Xinghe Freight, GZYL oder den Verkäufer kann Peek nicht für dich kontaktieren.

## Fragen zur Xinghe Freight Sendungsverfolgung

### Was ist Xinghe Freight (星和货运)?

广州星和货运代理有限公司 (Guangzhou Xinghe Freight Forwarding), ein Spediteur mit Sitz in Guangzhou, China. Laut eigener Website wurde das Unternehmen 2014 gegründet und übernimmt See-, Luft- und Landfracht, Schiffsagenturdienste sowie Zollabfertigungen. In Peek laufen darunter die Sendungsnummern aus GZXH, zehn Ziffern und YQ.

### Warum zeigt mein Xinghe-Freight-Paket das Tracking von GZYL?

Weil Xinghes Sendungsverfolgung den Tracker von GZYL nutzt: Das Suchfeld auf Xinghes eigener Seite übergibt deine Nummer dorthin, und die Schritte erscheinen in GZYLs Formulierungen. Peek führt das Paket weiter unter Xinghe Freight und liest diesen Tracker für dich aus.

### Wer stellt Xinghe-Freight-Pakete in meinem Land zu?

Das weiß Peek nicht: Weder die Schritte im Tracker noch Xinghes Website nennen einen lokalen Zusteller. Nur in der Schweiz und in Liechtenstein kann Peek ein Xinghe-Paket an die Schweizerische Post weiterreichen. Frag den Verkäufer, welcher Paketdienst deine Bestellung bringt, und bitte um dessen Sendungsnummer.

### Warum zeigt Peek für meine GZXH-Nummer noch keinen Verlauf?

Entweder liegt dem Tracker der Auftrag noch nicht vor, oder es stehen dort bisher nur vorab geschriebene Schritte (bis hin zu `Delivered`), die Peek zurückhält und nur hinter „Anzeigen“ auflistet. Peek fragt zusätzlich Tracking-Dienste ab und prüft im oben unter „Warten auf Xinghe Freight“ genannten Rhythmus weiter. Da Peek dabei keinen Nummern-Warnhinweis einblendet, vergleich die Zeichenfolge selbst mit der Versandnachricht des Shops; passiert nach einigen Tagen nichts, frag beim Verkäufer nach, ob das Paket wirklich verschickt wurde.

:::sources
- [Xinghe Freight: 广州星和货运代理有限公司 (Startseite, auf Chinesisch)](http://xinghe-express.cn/) – Firmenname, Speditionsleistungen, Tracking-Suchfeld, Hotline und E-Mail
- [Xinghe Freight: 关于我们 (Über uns, auf Chinesisch)](http://xinghe-express.cn/about.html) – Gründung 2014, See-, Luft- und Landfracht, Schiffsagentur und Zollanmeldung
- [Xinghe Freight: 产品服务 (Leistungen, auf Chinesisch)](http://xinghe-express.cn/services.html) – Leistungsübersicht ohne Angaben zum Endkunden-Paketversand
- [Xinghe Freight: 联系我们 (Kontakt, auf Chinesisch)](http://xinghe-express.cn/contact.html) – 24/7-Hotline, E-Mail für Geschäftsanfragen und Angebote, Ortsangabe Guangzhou mit Bitte um Voranmeldung, Kontaktformular per E-Mail-Client
- [Europäische Kommission: EU Customs Reform (auf Englisch)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 3 € Zoll je Warenkategorie auf Sendungen bis 150 € seit 1. Juli 2026 und 2 € EU-Bearbeitungsgebühr seit 1. November 2026
- [Europäische Kommission: Guidance on the 3 EUR customs duty and Union handling fee (PDF, auf Englisch)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – Bearbeitungsgebühr von 2 € je Warenkategorie unabhängig vom Wert und Definition einer Warenkategorie
- [Europäische Kommission: Summertime (auf Englisch)](https://transport.ec.europa.eu/transport-themes/summertime_en) – mitteleuropäische Sommerzeit vom letzten Sonntag im März bis zum letzten Sonntag im Oktober
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Namen und Aliase, Format GZXH…YQ, Abfrage über GZYLs Tracker, Wortlaute und Stufen der Schritte, Einlieferung in Hongkong, chinesische Plattformzeile, Pekinger Uhrzeit, vorab geschriebene Verläufe, Tracking-Dienste als Rückfallweg, Antwort bei unbekannter Nummer, Übergabe an die Schweizerische Post, was Peek anzeigt, zurückhält und auslässt
:::
