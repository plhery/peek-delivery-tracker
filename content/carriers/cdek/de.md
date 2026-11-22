---
title: CDEK Sendungsverfolgung (СДЭК): Nummern und Status
description: Verfolge ein CDEK-Paket (СДЭК): wie die Bestellnummer aussieht, russische Status auf Deutsch erklärt, Kurier oder Abholpunkt und Tracking mit Peek.
slug: cdek-sendungsverfolgung
published: 2026-11-22
updated: 2026-11-22
---

CDEK (СДЭК, auch SDEK geschrieben) ist ein Kurierdienst, den Peeks Katalog für Russland, Kasachstan, Belarus und Serbien führt. Pakete gehen per Kurier an die Haustür oder an einen Abholpunkt bzw. eine Paketstation. Die Nummer erhältst du vom Shop oder Absender: Füge sie oben ein. CDEKs eigener Tracker liegt auf my.cdek.ru. CDEK schreibt alle Schritte auf Russisch — die Tabelle unten erklärt sie auf Deutsch.

## CDEK Sendungsnummer: wie sie aussieht

CDEK nennt jede Sendung eine Bestellung (заказ), daher ist die Trackingnummer eine CDEK-Bestellnummer (nicht die Bestellnummer des Shops). Der Tracker nimmt Codes aus 5 bis 40 Buchstaben und Ziffern an: eigene 10- oder 11-stellige Nummern sowie verknüpfte Codes wie internationale Postnummern. Peek ordnet CDEK genau ein Muster schwach zu (erfundene Ziffern):

| Nummer (erfunden) | Bedeutung |
| --- | --- |
| `10482917365` | 11 Ziffern mit Beginn `10`: das einzige Muster, das Peek mit CDEK verknüpft, nur als schwacher Treffer. Auch [GLS Deutschland](carrier:gls-de), [GLS Schweiz](carrier:gls-ch), GLS Frankreich, GLS Österreich, GLS Polen, PostLogistics, Coordinadora und Mainfreight nutzen 11 Ziffern. |
| `48291736548` | 11 Ziffern ohne Beginn `10`: Peek schlägt CDEK nicht vor; prüfe die Nummer oder wähle CDEK selbst (unten). |
| `RR482917367CN` | Eine Postnummer aus dem Ausland, hier [China Post](carrier:china-post). Peek verfolgt sie dort; hat CDEK sie mit einer Bestellung verknüpft, wähle CDEK für dessen Schritte. |

Nummer nicht gefunden? Siehe [Sendungsnummer finden](guide:find-tracking-number) und [Formate von Sendungsnummern](guide:tracking-number-formats).

## CDEK Tracking-Status auf Deutsch erklärt

Jeder CDEK-Schritt trägt einen Code (nach dem Peek die Stufe setzt) und russischen Text, den Peek unverändert anzeigt. CDEK fasst Scans unter großen Schritten wie `В пути` zusammen; Peek zeigt einen großen Schritt nur, wenn er keine Einzelscans enthält. Ungefähr in Reihenfolge:

| CDEK-Meldung | Was sie für dich bedeutet | Anzeige in Peek |
| --- | --- | --- |
| `Создан` | Erstellt: Der Auftrag steht im CDEK-System, das Paket liegt eventuell noch nicht vor. | „Etikett erstellt“ |
| `Назначен курьер` | Ein Kurier ist zur Abholung beim Absender unterwegs. | „Etikett erstellt“ |
| `Курьер забрал заказ у отправителя` | Der Kurier hat das Paket beim Absender abgeholt. | „Beim Paketdienst eingegangen“ |
| `Принят на доставку` | Zur Zustellung angenommen: CDEK hat das Paket. | „Beim Paketdienst eingegangen“ |
| `В пути` | Unterwegs: Sammelschritt zwischen Annahme und letztem Abschnitt. | „Unterwegs“ |
| `Принят СДЭК на территории РФ` | Ein Auslandspaket ist in CDEKs Netz in Russland eingetroffen. | „Unterwegs“ |
| `Отправлен в сортировочный центр` | An ein Sortierzentrum geschickt. | „Unterwegs“ |
| `Прибыл в сортировочный центр` | In einem Sortierzentrum angekommen. | „Unterwegs“ |
| `Отправлено в г.` + Stadt | In diese Stadt versandt. | „Unterwegs“ |
| `Отправлен в пункт доставки` | An das Auslieferungsdepot geschickt. | „Unterwegs“ |
| `Прибыл в пункт доставки` | Im Auslieferungsdepot angekommen. | „Unterwegs“ |
| `Отправлен в пункт выдачи` | An deinen Abholpunkt geschickt. | „Unterwegs“ |
| `Доставляет курьер` | In Zustellung durch Kurier (umfasst Zustellversuche). | „In Zustellung“ |
| `Выдан курьеру` | Dem Zusteller übergeben; erscheint bei jedem Versuch erneut. | „In Zustellung“ |
| `Курьер не смог доставить заказ` | Der Kurier konnte nicht zustellen. | „Zustellversuch“ |
| `Готов к выдаче`, `Поступил. Заберите заказ` | Abholbereit am Abholpunkt oder in der Paketstation. | „Abholbereit“ |
| `Вручён` | Übergeben: zugestellt. | „Zugestellt“ |
| `Не вручён` | Nicht übergeben: Auftrag endete ohne Zustellung (nicht abgeholt oder verweigert). | „Problem gemeldet“ |

CDEK kann Aufträge auch storniert („Problem gemeldet“) oder teilweise zugestellt abschließen („Zugestellt“ in Peek, wenn du einen Teil angenommen und den Rest abgelehnt hast).

## Wie CDEK zustellt: Kurier oder Abholpunkt

Der Verlauf zeigt dir, wie dein Paket ankommt.

**An die Haustür.** Nach Ankunft im Depot (`Прибыл в пункт доставки`) geht es zum Kurier (`Выдан курьеру`). Nach einem fehlgeschlagenen Versuch erscheint `Выдан курьеру` erneut.

**An Abholpunkt oder Paketstation.** Warte nach `Отправлен в пункт выдачи` auf `Поступил. Заберите заказ`, dann kannst du abholen.

Auf der Bestellseite unter my.cdek.ru stehen Abholpunkt, Adresse, Kontaktdaten, Öffnungszeiten und letzter Abholtag. Peek zeigt diese Angaben nicht an — schau dort nach. Da CDEKs Hauptwebsite cdek.ru automatische Abrufe blockiert, nennt diese Seite keine Laufzeiten, Lagerfristen oder Regeln für Zustellversuche. Siehe [nicht abgeholte Pakete](guide:uncollected-parcel), [verpasste Zustellung](guide:missed-delivery) und [Paketstationen](guide:parcel-lockers).

**Aus dem Ausland.** `Принят СДЭК на территории РФ` zeigt die Übernahme eines Auslandspakets in Russland. Mangels direktem Zugriff auf offizielle Seiten von cdek.ru enthält diese Seite keine Zollgebühren oder -regeln; siehe [Paket hängt beim Zoll](guide:customs).

## CDEK-Paket kommt nicht an oder Tracking hängt

**Nur `Создан` oder keine Schritte.** Eventuell hat der Absender das Paket noch nicht übergeben, oder die Nummer hat einen Tippfehler. Peek fragt weiter nach. Prüfe die Nummer Ziffer für Ziffer und frag nach einigen Tagen beim Shop nach.

**Tagelang kein neuer Schritt.** Ist der letzte Schritt vier Tage oder älter, bevor es in Zustellung oder an den Abholpunkt geht, zeigt Peek in deiner Liste den Tag des letzten Scans („Keine Aktualisierung seit …“) und auf der Paketseite „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei CDEK nach.“ erst dann samt Link „Hol dir Hilfe bei CDEK“ zur FAQ. Mehr unter [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating).

**`Курьер не смог доставить заказ`.** Zustellung fehlgeschlagen. Ein weiterer Versuch erscheint wieder als `Выдан курьеру`; bleibt er aus, kontaktiere den Shop.

**`Не вручён`.** Der Auftrag endete ohne Übergabe. Kläre mit dem Shop Erstattung oder Neuversand. Siehe [zurück an Absender](guide:returned-to-sender).

**Zugestellt, aber nichts erhalten.** Prüfe, ob jemand für dich angenommen hat, und wende dich an den Shop. Mehr unter [zugestellt, aber nicht erhalten](guide:delivered-not-received).

**Verloren.** Der Shop hat CDEK beauftragt: Kontaktiere zuerst den Shop. Siehe [verlorene Pakete](guide:lost-parcel).

## CDEK Kundenservice und Kontakt

Weil CDEKs Hauptwebsite cdek.ru automatische Abrufe sperrt, listet diese Seite keine Telefonnummer und keine E-Mail-Adresse: Peek gibt nur Kontakte an, die direkt von offiziellen Seiten des Paketdienstes gelesen wurden. Sendungsverfolgung und Bestellseiten findest du auf my.cdek.ru.

Bei einer Online-Bestellung wende dich zuerst an den Händler.

## CDEK-Paket mit Peek verfolgen

[Peek](/) verfolgt über 4500 Paketdienste, kann CDEK aber nicht sicher an der Nummer allein erkennen. Füge sie trotzdem ein:

- **11 Ziffern mit Beginn `10`.** Peek fragt passende Paketdienste zu fünft ab und zeigt „Tracking-Dienste werden geprüft…“. Bei Abfragen aus Russland, Kasachstan, Belarus oder Serbien wird CDEK zuerst gefragt; von anderswo zählt es weiterhin zu den ersten fünf. Kennt CDEK das Paket, zeigt Peek „CDEK hat dieses Paket.“. Kennen es mehrere, wählst du aus; kennt es noch keiner, erscheint „noch nicht gefunden · Peek prüft weiter“. Oder tippe auf „Ändern“ und wähle CDEK unter „Passt zu dieser Nummer“.
- **Anderes Format (auch 10 Ziffern).** Tippe auf „Ändern“ und suche nach CDEK (auch СДЭК oder SDEK funktioniert).
- **Ein Link.** Füge einen my.cdek.ru-Bestelllink (`/lkfl/order/...`) ein, um den Auftrag direkt mit CDEK zu verfolgen.

Bei einem bereits angelegten Paket nutze „Paketdienst ändern“. Ein Paket ohne Paketdienst zeigt „Noch kein Sendungsverlauf“; unter CDEK zeigt es „Warten auf CDEK“, bis CDEK es meldet.

**Was Peek zeigt.** Peek liest CDEKs öffentliches Tracking allein über die Nummer — ohne Konto und Telefonbestätigung. Du siehst Status, jeden Schritt im russischen Wortlaut, die Stadt und genaue Zeiten (UTC in deine Zeitzone umgerechnet, mit „Uhrzeiten in deiner Zeitzone:“ am Ende des Verlaufs). Anonyme Antworten enthalten keinen Abholcode für eingeloggte Empfänger, und Peek lässt Absenderadresse, Filialdaten (Adresse, Telefon, Öffnungszeiten, Koordinaten, Lagerfrist), Zahlungen, Gewicht, Maße und Lieferprognose weg. Über „Website von CDEK öffnen“ gelangst du zur Bestellseite auf my.cdek.ru.

**Zweite Nummer für denselben Auftrag.** Verfolgst du eine mit dem Auftrag verknüpfte Nummer (etwa eine Auslandspostnummer), liefert CDEK seine eigene Bestellnummer zurück, die Peek unter „CDEK führt die Sendung unter“ anzeigt.

**Wenn CDEK nicht antwortet.** CDEKs Seiten können automatische Abrufe blockieren. Peek fragt dann der Reihe nach ParcelsApp, Ship24 und 17TRACK (ParcelsApps Kopien nennen teils nur Abschnitte ohne genaue Uhrzeit) und kehrt zu CDEK zurück, sobald es wieder antwortet.

**Nummer eines anderen Dienstes.** Kennt CDEK eine dort angelegte 11-stellige Nummer nicht, prüft Peek in den ersten 30 Tagen andere passende Dienste und wechselt automatisch („Automatisch von CDEK gewechselt“), sobald einer Bewegung meldet.

**An CDEK übergebene Pakete.** Qwintry übergibt manche Pakete an CDEK: Nennt Qwintry CDEK samt Nummer, zeigt das Paket „Zustellung durch CDEK“ (bzw. „Bei CDEK angekündigt, noch nicht eingetroffen“ vor dem ersten CDEK-Scan) und wechselt zu CDEK, sobald dessen Scan mindestens ebenso aktuell ist. DobroPost zeigt CDEK-Schritte im eigenen Tracking (`DBRPST`). Bei einer Auslandspostnummer unter CDEK aus Belarus fragt Peek ebenso Belpost.

**Prüfintervall und Mitteilungen.** Solange das Paket geöffnet ist — oder am Zustelltag mit aktivierten Mitteilungen — fragt Peek direkt bei CDEK bis zu alle 10 Minuten nach, und ab 6:00 Uhr Ortszeit am Zustelltag alle 2 Minuten; sonst sowie nach zwei Tagen ohne Neuigkeiten (12 Stunden bei „Etikett erstellt“ oder „Beim Paketdienst eingegangen“) stündlich. Über Tracking-Dienste allein prüft es stündlich, nach einem Tag ohne Scan alle 3 Stunden und am Zustelltag bis zu alle 15 Minuten, sofern das Paket geöffnet ist oder Benachrichtigungen aktiv sind. Wähle „Jeden Scan“, „Wichtige Schritte“ (empfohlen für Abholpunkte, da „Nur die Zustellung“ nicht über die Abholbereitschaft informiert) oder „Nur die Zustellung“. Peek kann weder CDEK noch den Shop für dich kontaktieren.

## Häufige Fragen zu CDEK

### Was heißt СДЭК auf Deutsch oder Englisch?

CDEK (auch SDEK oder CDEK Express geschrieben). Die Sendungsverfolgung liegt auf my.cdek.ru.

### Wie sieht eine CDEK-Trackingnummer aus?

Peek verknüpft 11 Ziffern mit Beginn `10` wie `10482917365` mit CDEK; andere Dienste nutzen ebenfalls 11 Ziffern (und CDEK vergibt auch 10-stellige Nummern).

### Was bedeutet „Поступил. Заберите заказ“ bei CDEK?

Dein Paket ist am Abholpunkt oder in der Paketstation eingetroffen und abholbereit: Peek zeigt „Abholbereit“. Adresse und Abholfrist findest du auf my.cdek.ru.

### Kann ich ein CDEK-Paket auf Deutsch verfolgen?

Ja. Peek zeigt die Statusstufe auf Deutsch (z. B. „Unterwegs“) und die einzelnen Schritte im russischen Original, die oben in der Tabelle übersetzt sind.

### Warum soll ich bei meiner CDEK-Nummer einen Paketdienst auswählen?

GLS, PostLogistics und weitere Dienste nutzen ebenfalls 11 Ziffern. Kennen mehrere Dienste dieselbe Nummer, wählst du CDEK aus der Liste.

:::sources
- [Universal Parcel Scraper: CDEK](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/cdek) – Namen, Länder, 11-stelliges Muster (`10`) und 10-stellige Nummern, my.cdek.ru-Links, Codes, russische Texte und Stufen, UTC-Zeiten, Bestellnummer und Bot-Schutz
- [Universal Parcel Scraper: Qwintry](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/qwintry) – Qwintry-Tracking mit CDEK-Nennung und Nummer
- [Universal Parcel Scraper: DobroPost](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dobropost) – CDEK-Schritte im DobroPost-Tracking
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – weitere 11-stellige Dienste, Abfragereihenfolge, Tracking-Dienste, ParcelsApp-Kopien, Übergaben und Belpost in Belarus
:::
