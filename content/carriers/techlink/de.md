---
title: Techlink Sendungsverfolgung: ZHPHD…ML-Nummer und Status
description: Techlink (智禾) Paket aus China verfolgen: Aufbau der ZHPHD…ML-Sendungsnummer, Bedeutung der englischen und chinesischen Scans, Zusteller und Hilfe bei Stillstand.
slug: techlink-sendungsverfolgung
published: 2026-11-21
updated: 2026-11-21
---

Techlink (智禾国际物流, Zhihe International Logistics) ist ein Logistikunternehmen aus Shenzhen in Festlandchina, das Pakete von Onlinehändlern nach Europa und Amerika verschickt. Techlink-Sendungsnummern beginnen mit `ZHPHD` und enden auf `ML`: Gib deine Nummer auf der Trackingseite von Techlink (`www.techlinksz.cn/trajectory`) oder direkt oben ins Suchfeld ein. Den Code erhältst du vom Shop in der Versandbestätigung deiner Bestellung.

## Techlink Sendungsnummer: Aufbau und Beispiele

Techlink veröffentlicht auf seiner Website keine Formatbeschreibung. Seine Frachtbriefe haben genau 19 Zeichen: `ZHPHD`, sechs Ziffern als Kalenderdatum (Jahr, Monat, Tag), sechs weitere Ziffern und `ML`. Mit frei erfundenen Ziffern:

| Nummer | Bedeutung |
| --- | --- |
| `ZHPHD260315482910ML` | `ZHPHD`, ein sechsstelliges Datum, sechs Ziffern und `ML`: eine Techlink-Sendungsnummer. |
| `ZHPHD261315482910ML` | Einen Monat `13` gibt es nicht: kein gültiges Techlink-Format. Prüfe die Nummer auf Tippfehler. |
| `RR482910576CN` | Zwei Buchstaben, neun Ziffern und `CN`: eine Postsendung von [China Post](carrier:china-post), nicht von Techlink. |

Groß-/Kleinschreibung und Leerzeichen spielen in Peek keine Rolle. Andere Nummernmuster erklärt der Ratgeber [Formate von Sendungsnummern](guide:tracking-number-formats).

**Wo du sie findest.** Kunden von Techlink sind Onlinehändler. Laut Startseite bedienen seine Kleinpaket-Linien nach Europa und Amerika Verkäufer auf Amazon, Walmart und `TK` sowie unabhängige Onlineshops; AliExpress, Temu oder Shein nennt Techlink nicht. Der Händler bucht den Versand und teilt dir die Nummer per Versand-E-Mail oder in der Bestellübersicht mit. Fehlt sie dort, hilft [Sendungsnummer finden](guide:find-tracking-number).

## Techlink Statusmeldungen erklärt

Die Trackingseite von Techlink nimmt bis zu 40 Sendungsnummern gleichzeitig entgegen, zeilenweise oder durch Kommas getrennt. Sie bleibt auf Chinesisch, selbst wenn man im Menü Englisch wählt. Zu jeder Nummer zeigt sie den jüngsten Eintrag, die Gesamtzahl der Scans (`条轨迹`) sowie Abgangs- und Zielland auf Chinesisch. Ein Klick auf die Zeile oder auf `展开全部` (alles ausklappen) öffnet alle Stationen mit Uhrzeit und Land.

Ein farbiges Symbol vor jeder Nummer fasst den Stand zusammen: `▤` im Lager von Techlink, `➤` unterwegs oder in Zustellung, ein grünes Häkchen `✓` zugestellt, ein rotes Ausrufezeichen `!` eine Störung (etwa ein fehlgeschlagener Zustellversuch) und `?` kein Treffer.

Techlink veröffentlicht kein Statusverzeichnis, speichert seine Scans aber auf Englisch und Chinesisch samt Zeitstempel und meist dem Land:

| Meldung bei Techlink | Bedeutung für dein Paket | Anzeige in Peek |
| --- | --- | --- |
| `Parcel electronic information received` (`收到电子信息`) | Der Händler hat die Auftragsdaten an Techlink übermittelt; das Paket liegt Techlink noch nicht vor. | „Etikett erstellt“ |
| `Parcel has been received and processed at original facility` (`仓库入库`) | Das Paket wurde im Lager von Techlink in Festlandchina vereinnahmt. | „Beim Paketdienst eingegangen“ |
| `Parcel has been packed and is ready for outbound shipping` (`包裹装箱`) | Versandfertig verpackt für den internationalen Weitertransport. | „Unterwegs“ |
| `Departed from the original facility` | Hat das Ursprungslager von Techlink in Festlandchina verlassen. | „Unterwegs“ |
| `Export customs declaration completed` | Ausfuhrzollanmeldung in Festlandchina nach Lagerausgang abgeschlossen; das Paket hängt nicht beim Zoll fest. | „Unterwegs“ |
| `Parcel is in transit to origin airport` | Straßentransport in Festlandchina zum Abflughafen. | „Unterwegs“ |
| `Depart from the original airport` | Der Flug ist vom Abgangsflughafen gestartet. | „Unterwegs“ |
| `Arrive at the destination airport` | Das Flugzeug ist im Zielland gelandet, vor der Einfuhrverzollung. | „Unterwegs“ |
| `Shipment Received at Facility` | Eingang in einem Hub im Zielland nach dem Flug. | „Unterwegs“ |
| `Your shipment has arrived at sorting center` | Im Sortierzentrum des Ziellands eingetroffen. | „Unterwegs“ |
| `Your shipment has been processed at sorting center and ready for shipping` | Dort für den nächsten Abschnitt sortiert. | „Unterwegs“ |
| `Your shipment has departed from the transit origin` | Hat das Sortierzentrum in Richtung nächster Station verlassen. | „Unterwegs“ |
| `In transit, it is currently in transit to the next facility` | Unterwegs zwischen zwei Standorten im Zielland. | „Unterwegs“ |
| `The parcel has been successfully picked up and is being prepared for transportation.` | Bei Paketen nach Australien: Übernahme durch J&T Express Australia nach dem Flug, gefolgt von J&Ts Transit- und Verteilzentrums-Scans. | „Unterwegs“ |
| `Parcel is out for delivery.` | Durchgereichter Scan des lokalen Zustellers: Das Paket ist in der Zustellung. | „In Zustellung“ |
| `Parcel delivery failed [Wrong Address]. Parcel will be returned to the warehouse.` | Fehlgeschlagener Zustellversuch beim lokalen Zusteller (hier falsche Adresse; in eckigen Klammern stehen je nach Fall andere Gründe) mit Rückkehr ins lokale Depot. | „Zustellversuch“ |

Bei einer unbekannten Nummer meldet die Seite von Techlink `查询不到轨迹` (keine Sendungsverfolgung gefunden), `未知 → 未知` (unbekannte Länder) und `暂无最新轨迹` (noch kein aktueller Scan).

## Wer stellt Techlink-Pakete im Zielland zu

Techlink nennt auf seiner Website keine Zustellpartner. Die Startseite beschreibt Tür-zu-Tür-Linien mit Luftfracht ab Festlandchina, Zollabfertigung im Ausland über eine 2023 gegründete eigene Auslandsgesellschaft und letzter Meile über eigene Versandkonten bei Paketdiensten im Zielland – ohne deren Namen auf der Website oder im Tracking anzugeben. In Verläufen, die Peek ausgewertet hat, gingen Sendungen in Italien an [iMile](carrier:imile), erkennbar am vorangestellten Zentrum in Klammern wie `【Milan center】Parcel shipped to【VEN 07】.` In Australien übernimmt [J&T Express Australia](carrier:j-and-t-au) nach dem Flug ab `The parcel has been successfully picked up and is being prepared for transportation.` In den USA reicht Techlink teils Scans von [YWE](carrier:ywe) weiter, darunter `Parcel departed from Facility.` und `Parcel received by local Facility.` bis hin zu `Parcel is out for delivery.` oder einem Fehlversuch.

Im Datensatz von Techlink kann die eigene Sendungsnummer des Zustellpartners hinterlegt sein, jedoch ohne dessen Namen, und die Webseite von Techlink zeigt dieses Feld nicht an. Sobald feststeht, welcher lokale Dienstleister zustellt, gelten dessen Regeln für Zustellversuche, Benachrichtigungskarten, Paketshops und Lagerfristen – Techlink selbst veröffentlicht dazu nichts.

Techlinks Tür-zu-Tür-Expressdienst wird verzollt (`DDP`) oder unverzollt (`DDU`) angeboten. Welche Variante der Verkäufer gebucht hat, entscheidet darüber, ob bei der Einfuhr Abgaben auf dich zukommen können: siehe [Paket beim Zoll](guide:customs).

## Techlink-Paket hängt fest oder kommt nicht an

**Nur `Parcel electronic information received`.** Der Händler hat den Auftrag elektronisch angemeldet, das Paket ist aber noch nicht im Techlink-Lager in Festlandchina eingetroffen. Tut sich mehrere Tage nichts, frage beim Verkäufer nach, ob die Ware bereits übergeben wurde.

**Keine Updates nach `Arrive at the destination airport`.** Der Flug ist im Zielland gelandet; als Nächstes stehen Einfuhrzoll und Übergabe an den lokalen Paketdienst an, was mehrere Tage ohne Scan dauern kann. Oft endet Techlinks eigene Historie am Ankunftsflughafen oder wenige Schritte später im lokalen Hub oder Sortierzentrum (`Shipment Received at Facility` bis `In transit, it is currently in transit to the next facility`), während sie nur bei manchen Paketen die Scans des lokalen Zustellers bis `Parcel is out for delivery.` weitergibt. Bleibt der Verlauf stehen, erfrage beim Verkäufer den Namen des Zustellers im Zielland und dessen Sendungsnummer. Mehr unter [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating) und [Pakete aus China verfolgen](guide:tracking-from-china).

**Kein Ergebnis.** Prüfe die Schreibweise mit dem Händler: Eine `ZHPHD…ML`-Nummer hat genau 19 Zeichen. Auch unmittelbar nach der Anmeldung ist mitunter noch kein Eintrag vorhanden.

**Verloren, beschädigt oder als zugestellt gemeldet, aber unauffindbar.** Techlink veröffentlicht kein Reklamationsverfahren für Empfänger. Vertragspartner von Techlink ist allein der Verkäufer – wende dich für Ersatz oder Rückerstattung an den Händler oder den Marktplatz. Siehe [verlorenes Paket](guide:lost-parcel), [zugestellt, aber nicht erhalten](guide:delivered-not-received) sowie bei Amazon-Käufen [Amazon-Bestellung verfolgen](guide:amazon-orders).

## Techlink Kontakt und Kundenservice

Die Website von Techlink (`www.techlinksz.cn`) nennt weder Telefonnummer noch E-Mail-Adresse oder Postanschrift. Der Menüpunkt `联系我们` (Kontakt) hat kein Linkziel, und es gibt weder Live-Chat noch Kontaktformular.

Bei Fragen zur Bestellung ist daher immer der Verkäufer dein Ansprechpartner; bei Zustellfragen vor Ort der lokale Paketdienst, sobald du ihn kennst.

## Techlink-Pakete mit Peek verfolgen

[Peek](/) ordnet eine `ZHPHD…ML`-Nummer sofort Techlink zu – kein anderer Paketdienst im Katalog nutzt dieses Muster – und zeigt „Erkannter Paketdienst“ samt „Ändern“. Einen einfügbaren Trackinglink von Techlink gibt es nicht, da `www.techlinksz.cn/trajectory` die Nummer nicht in die Seitenadresse übernimmt.

Peek liest denselben Tracker wie Techlinks Website und zeigt den Status, jeden Scan mit seinem Land sowie das Zielland. Englische Meldungen erscheinen im Wortlaut von Techlink (über einen geteilten Link steht bei einer abgeschlossenen Zustellung nur `Delivered`). Eigene Scans in Festlandchina, die elektronische Auftragsanlage ohne Ort sowie Auslandsscans mit Zentrum in Klammern (`【…】`) liest Peek nach Pekinger Zeit; durchgereichte Scans von J&T Express Australia für Pakete nach Australien nach der Uhrzeit von Sydney unabhängig vom Bundesstaat des Depots; übrige Auslandsscans behalten die Ortszeit ihres Landes, und Peek rechnet jede fest zugeordnete Uhrzeit in die Zeitzone deines Geräts um – auf `peektracker.com` sowie in der iPhone- und Android-App steht unter dem Verlauf „Uhrzeiten in deiner Zeitzone: …“. Voraussichtliches Lieferdatum, Gewicht oder Tarifnamen liefert Techlink nicht.

Kennt Techlink die Nummer noch nicht, zeigt Peek „Warten auf Techlink“; meldet Techlink keinen Auftrag und antwortet auch nach zwei Tagen keine andere Quelle, erscheint „Auch nach zwei Tagen kennt der Paketdienst diese Nummer nicht. Prüfe sie, wähle einen anderen Paketdienst oder lösche das Paket.“

Enthält Techlinks Datensatz die Sendungsnummer des lokalen Zustellers, prüft Peek deren Aufbau: Eine internationale Postnummer (zwei Buchstaben, neun Ziffern, zwei Buchstaben) verweist auf die Postgesellschaft des Ziellands, ein eindeutiges Format aus Peeks Katalog auf den jeweiligen Paketdienst. Kann Peek dessen eigenes Tracking direkt abfragen, wechselt es zu ihm, sobald dort ein mindestens ebenso aktueller Fortschritt vorliegt, und zeigt die zweite Nummer neben dem `ZHPHD…ML`-Code an. Andernfalls bleibt Peek auf Techlinks Tracker, dessen Einträge häufig nach der Landung oder im lokalen Sortierzentrum enden.

Solange du das Paket geöffnet hast oder am Zustelltag Benachrichtigungen aktiviert sind, prüft Peek bis zu alle 10 Minuten – und ab 6:00 Uhr Ortszeit am Tag der Zustelltour bis zu alle 2 Minuten, solange Techlinks eigener Tracker antwortet (über externe Trackingdienste allein stündlich, alle 3 Stunden nach einem ruhigen Tag und am Zustelltag bei geöffnetem Paket oder aktiven Mitteilungen bis zu alle 15 Minuten; nach einer Übergabe folgt Peek dem Takt des zustellenden Paketdienstes) –; ansonsten sowie nach zwei Tagen ohne Neuigkeiten (oder 12 Stunden bei „Etikett erstellt“ oder „Beim Paketdienst eingegangen“) stündlich. Benachrichtigungen gibt es für „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“. Weder Techlink noch den Verkäufer kann Peek für dich kontaktieren.

## Häufige Fragen zur Techlink Sendungsverfolgung

### Was ist Techlink (智禾)?

Techlink ist `智禾国际物流(深圳)有限公司` (Zhihe International Logistics, Shenzhen), ein Logistikdienstleister aus Festlandchina mit Kleinpaket- und Tür-zu-Tür-Linien für Onlinehändler nach Europa und Amerika. Seine Website lautet `www.techlinksz.cn`.

### Was ist eine ZHPHD-Sendungsnummer?

Ein 19-stelliger Frachtbrief von Techlink aus `ZHPHD`, sechs Datumsziffern, sechs weiteren Ziffern und `ML`. Er lässt sich auf `www.techlinksz.cn/trajectory` oder direkt in Peek verfolgen.

### Wer liefert Techlink-Pakete in meinem Land aus?

Techlink übergibt Pakete im Zielland an einen lokalen Zusteller, nennt ihn aber weder auf seiner Website noch im Tracking (in von Peek gelesenen Verläufen tauchten etwa iMile in Italien, J&T Express Australia in Australien und YWE in den USA auf). Zeigt der Verlauf keinen Hinweis und konnte Peek nicht über eine hinterlegte Zweitnummer umschalten, frage den Verkäufer nach Zusteller und lokaler Nummer.

### Warum stoppt mein Techlink-Tracking nach der Landung am Zielflughafen?

Bei vielen Sendungen endet Techlinks eigenes Tracking mit `Arrive at the destination airport` oder wenige Schritte später in einem Hub oder Sortierzentrum des Ziellands, während die restlichen Schritte nur beim lokalen Zusteller erscheinen. Erfrage dessen Sendungsnummer beim Händler und melde dich dort, falls der Liefertermin überschritten ist.

:::sources
- [Techlink: 智禾官网 (Startseite)](http://www.techlinksz.cn/) – Firmenname in Shenzhen, Kleinpaket-Linien nach Europa und Amerika, Händler auf Amazon, Walmart, TK und eigenen Shops, Tür-zu-Tür-Express DDU und DDP, 2023 gegründete Auslandsgesellschaft für Verzollung, Zustellkonten im Zielland und Kontaktlink ohne Ziel
- [Techlink: 轨迹跟踪 (Sendungsverfolgung)](http://www.techlinksz.cn/trajectory) – Abfrage von bis zu 40 Nummern auf Chinesisch, Abgangs- und Zielland, Statussymbole und Meldung bei unbekannter Nummer
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Aufbau der ZHPHD…ML-Nummer, englische und chinesische Meldungen, durchgereichte Scans von iMile in Italien, J&T Express Australia in Australien und YWE in den USA, Zeitzonen Peking und Sydney, Übergabe über die Zweitnummer und Darstellung in Peek
:::
