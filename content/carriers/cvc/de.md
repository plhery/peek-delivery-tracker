---
title: CVC Sendungsverfolgung (Си Ви Си): Paket in Bulgarien
description: CVC-Paket (Си Ви Си, e-CVC) in Bulgarien mit Peek verfolgen: Nummer hinzufügen, CVC wählen, bulgarische Status erklärt und Hilfe bei Stillstand.
slug: cvc-sendungsverfolgung
published: 2026-11-23
updated: 2026-11-23
---

CVC ist ein Paketdienst in Bulgarien. Im Katalog von Peek ist er auch als Си Ви Си, Си Ви Си ЕООД und e-CVC bekannt. Um ein CVC-Paket zu verfolgen, füge oben einen Link zum CVC-Tracking ein, oder füge die Nummer ein und wähle CVC als Paketdienst: An der Form allein erkennt Peek keine CVC-Nummer. Die Nummer kommt vom Shop oder von der Person, die das Paket verschickt hat. Das eigene Tracking von CVC liegt auf my.e-cvc.bg/track auf Bulgarisch: es fragt nach der Frachtbriefnummer und hat eine einzige Schaltfläche, „Провери“ (Prüfen).

Viel mehr zeigen die Seiten von CVC nicht: my.e-cvc.bg enthält nur dieses Tracking und eine Anmeldeseite, ohne veröffentlichte Lieferregeln, Preise oder Kontakte. Diese Seite beschreibt daher, was das Tracking von CVC verlangt und wie Peek ein CVC-Paket verfolgt, nennt aber keine Lieferbedingungen, Preise oder Kontaktdaten.

## CVC Sendungsnummer: Wie sie aussieht

Das Tracking von CVC verlangt eine Frachtbriefnummer (*товарителница* auf Bulgarisch). Das Feld akzeptiert nur Ziffern: Buchstaben und Leerzeichen werden beim Tippen entfernt, und maximal 20 Zeichen sind zulässig. Eine CVC-Nummer besteht also aus bis zu 20 Ziffern, doch im Katalog von Peek ist kein festes Muster für CVC hinterlegt: Peek erkennt sie nicht automatisch als CVC, und CVC erscheint beim Hinzufügen nie unter „Passt zu dieser Nummer“. Ein Beispiel nennt diese Seite nicht, da das CVC-Tracking kein Beispiel für eine vollständige Nummer zeigt.

| Was du einfügst | Was Peek tut |
| --- | --- |
| Nur die CVC-Nummer | Peek nennt CVC nicht. Eventuell zeigt es andere Dienste, deren Muster passt, oder keinen. Wähle CVC selbst, wie unter „Ein CVC-Paket mit Peek verfolgen“ unten beschrieben. |
| Ein Link zum CVC-Tracking: `my.e-cvc.bg/track?wb=`, dann die Nummer | Peek liest die Nummer aus dem Link und erkennt CVC sofort („Erkannter Paketdienst“). |
| Eine Nummer, die laut Shop zu einem anderen Dienst gehört | Verfolge sie bei diesem Dienst: die E-Mail des Shops ist der beste Hinweis auf den Zusteller. |

**Wo du sie findest.** In der Versand-E-Mail des Shops oder auf deiner Bestellseite, teils als Frachtbriefnummer (*waybill*). Bei Privatsendungen frag den Absender. Keine Nummer? Siehe [wie du eine Sendungsnummer findest](guide:find-tracking-number) und [Formate von Sendungsnummern](guide:tracking-number-formats) für andere Paketdienste.

## Bedeutung der CVC Tracking-Status in Peek

Peek liest das Tracking von CVC auf my.e-cvc.bg/track direkt aus und zeigt jede Zeile im bulgarischen Wortlaut von CVC, zugeordnet zu einer deutschen Stufe in Peek. Die wichtigsten von Peek erkannten bulgarischen Meldungen:

| CVC meldet | Peek zeigt | Was das für dich bedeutet |
| --- | --- | --- |
| `Приета за събиране` | Etikett erstellt | Die Sendung ist zur Abholung angemeldet; CVC hat das Paket noch nicht übernommen. |
| `Отложено събиране за …` | Etikett erstellt | Die Abholung wurde auf das genannte Datum verschoben. |
| `Взета от куриер …` | Beim Paketdienst eingegangen | Ein CVC-Kurier hat das Paket abgeholt. |
| `Приета в Хъб …`, `Транспорт към Хъб …` | Unterwegs | Im CVC-Hub eingetroffen oder auf dem Weg zu einem Hub. |
| `Приета за разнос` | In Zustellung | Das Paket ist heute auf der Auslieferungstour. |
| `Доставена`, `Доставена на …` | Zugestellt | Das Paket wurde zugestellt. |

Kennt das CVC-Tracking eine Nummer nicht, meldet es `Пратката не е открита!` (Sendung nicht gefunden). Antwortet CVCs eigener Tracker nicht, kann Peek zusätzlich Tracking-Dienste wie ParcelsApp, Ship24 und 17TRACK abfragen und den Verlauf des ersten Dienstes anzeigen, der das Paket kennt, eingeordnet in die üblichen Peek-Stufen („Abholbereit“, „Zustellversuch“, „Zurück an Absender“, „Problem gemeldet“).

Bevor ein Eintrag vorliegt, zeigt Peek beim Abfragen „Aktualisierungen werden geprüft“, danach „Warten auf CVC“. Scheitern zwei Abfragen hintereinander, bleibt es bei „Warten auf CVC“, und auf der Paketseite steht „CVC war noch nicht erreichbar. Peek versucht es weiter.“ Mehr unter [Tracking-Status erklärt](guide:tracking-statuses).

## CVC Zustellung in Bulgarien

Auf den Seiten von CVC stehen keine Lieferzeiten, Zustellversuche, Filialen, Nachnahmeregeln oder Abholfristen: my.e-cvc.bg zeigt nur Tracking und Login. Frag beim Shop oder Absender nach, wie und wann CVC zustellt, denn sie haben den Versand gebucht. Scheitert eine Zustellung oder liegt ein Paket zur Abholung bereit, siehe [verpasste Zustellung](guide:missed-delivery) und [nicht abgeholte Pakete](guide:uncollected-parcel).

## CVC-Paket aktualisiert nicht, verloren oder zurückgesandt

**Noch nichts zu sehen.** Möglicherweise kennt CVC die Nummer noch nicht. Peek zeigt dann „Warten auf CVC“ und fragt weiter nach. Kennt CVC die Nummer zwei Tage nach seiner ersten solchen Antwort noch immer nicht, bittet Peek dich, sie zu prüfen, einen anderen Paketdienst zu wählen oder das Paket zu löschen. Vergleiche die Nummer mit der E-Mail des Shops. Kommt nach einigen Tagen nichts, öffne das CVC-Tracking über „Website von CVC öffnen“ auf der Paketseite und frage beim Shop nach, ob wirklich verschickt wurde.

**Tagelang keine Neuigkeiten.** Nach vier Tagen ohne neuen Eintrag erhält ein Paket im Status „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“ den Hinweis „Keine Aktualisierung seit …“ mit dem Tag dieser Zeile. Siehe [warum sich die Sendungsverfolgung nicht aktualisiert](guide:tracking-not-updating).

**Zugestellt, aber nicht angekommen.** Schau rund um die Tür und frag Nachbarn, danach den Shop. Siehe [zugestellt, aber nicht erhalten](guide:delivered-not-received).

**Verloren oder beschädigt.** Melde dich zuerst beim Shop: er hat den Versand gebucht und kann die Sache mit CVC klären oder Ersatz schicken. Siehe [verlorene Pakete](guide:lost-parcel) und [beschädigte Pakete](guide:damaged-parcel).

**Zurückgesandt.** „Zurück an Absender“ bedeutet, das Paket ging an Shop oder Absender zurück. Bitte dort um Erstattung oder Neulieferung. Siehe [zurück an den Absender](guide:returned-to-sender).

## CVC Kontakt und Kundenservice

Die Seiten von CVC nennen weder Telefonnummer noch E-Mail oder Filiale: my.e-cvc.bg zeigt nur das Tracking und eine Anmeldeseite. Da Peek nur Kontakte nennt, die auf den offiziellen Seiten des Paketdienstes stehen, führt diese Seite keine auf. Wende dich zuerst mit Bestell- und Sendungsnummer an den Shop oder Absender, die den Versand bei CVC gebucht haben.

## Ein CVC-Paket mit Peek verfolgen

[Peek](/) unterstützt mehr als 4.500 Paketdienste, darunter CVC, erkennt ein CVC-Paket aber nicht an der Nummer allein. Ein Link zum CVC-Tracking erkennt CVC sofort; mit der Nummer allein wählst du den Dienst selbst:

1. Füge die Nummer oben ein.
2. Tippe auf „Ändern“ neben der Paketdienst-Zeile und suche nach CVC. Auch „e-CVC“ oder „Си Ви Си“ findet es (mit „Auch e-CVC“ oder „Auch Си Ви Си“ unter dem Namen), ebenso „Bulgaria“.
3. Wähle CVC: die Zeile zeigt dann „Von dir gewählt“. Starte das Tracking.

Bei einem bereits gespeicherten Paket nutze „Paketdienst ändern“. Für CVC akzeptiert Peek nur 1 bis 20 Ziffern; bei allem anderen zeigt Peek: CVC „vergibt keine Nummern wie diese“. Listet Peek andere Dienste unter „Passt zu dieser Nummer“, passen diese nur zur Form der Nummer. Vor deiner Auswahl kann „noch nicht gefunden · Peek prüft weiter“ stehen – normal bei neuen Nummern.

Überlässt du Peek die Wahl, fragt es Tracking-Dienste ab: Zeigen sie das Paket unterwegs und nennen CVC als Zusteller, ordnet Peek es automatisch CVC zu.

**Was du siehst.** Den Status und jede Zeile aus dem Verlauf mit Datum und Uhrzeit in bulgarischer Ortszeit. CVC liefert keinen Scan-Ort, kein voraussichtliches Lieferdatum, keinen Abholpunkt und kein Gewicht. „Website von CVC öffnen“ öffnet deine Nummer direkt auf dem bulgarischen CVC-Tracking.

**Anderer Paketdienst.** Kennt CVCs Tracking die Nummer nicht, fragt Peek in den ersten 30 Tagen auch die Dienste an, deren Nummernformat zu deiner Nummer passt. Zeigt einer von ihnen das Paket unterwegs, wechselt Peek dorthin und vermerkt „Automatisch von CVC gewechselt“.

**Wie oft.** Solange das Paket in Peek geöffnet ist, oder mit aktivierten Benachrichtigungen an dem Tag, an dem CVC es mit `Приета за разнос` scannt, prüft Peek es bis zu alle 10 Minuten, an diesem Tag ab 6 Uhr bulgarischer Zeit alle 2 Minuten. Nach zwei Tagen ohne Neuigkeiten (oder 12 Stunden bei „Etikett erstellt“ oder „Beim Paketdienst eingegangen“) prüft Peek stündlich, nach sieben Tagen ohne Neuigkeiten bei „Etikett erstellt“ alle 6 Stunden. Benachrichtigungen können „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“ folgen.

Peek kann weder CVC noch den Shop oder Absender für dich kontaktieren, keine Zustellung ändern und keine Reklamation einreichen.

## Fragen zur CVC Sendungsverfolgung

### Wie verfolge ich ein CVC-Paket?

Füge einen Link zum CVC-Tracking oben ein, dann erkennt Peek CVC automatisch. Mit der Nummer allein tippst du auf „Ändern“ und wählst CVC, da Peek die Form nicht automatisch zuordnen kann. Bei einem bereits gespeicherten Paket nutze „Paketdienst ändern“. CVCs eigenes Tracking liegt auf my.e-cvc.bg/track: Frachtbriefnummer eingeben und „Провери“ drücken.

### Wie sieht eine CVC Sendungsnummer aus?

Es ist eine Frachtbriefnummer (*товарителница*) aus Ziffern: das CVC-Tracking entfernt Buchstaben und Leerzeichen und akzeptiert höchstens 20 Ziffern. Peek hat dafür kein eigenes Muster, wähle CVC daher manuell.

### Ist e-CVC dasselbe wie CVC?

Ja. Das CVC-Tracking auf my.e-cvc.bg nennt sich e-CVC.bg und trägt das CVC-Logo, und Peeks Katalog führt e-CVC, Си Ви Си und Си Ви Си ЕООД als weitere Namen für CVC. Die Suche nach jedem dieser Namen in Peek findet CVC.

### Warum steht bei meinem CVC-Paket „Warten auf CVC“?

Auf peektracker.com erscheint das, solange das CVC-Tracking (oder ein Tracking-Dienst) noch keinen Eintrag für die Nummer hat oder nicht erreichbar war: die Seite zeigt dann „CVC war noch nicht erreichbar. Peek versucht es weiter.“ Prüfe die Nummer beim Shop und schau über „Website von CVC öffnen“ direkt bei CVC nach.

### Wie lautet die Telefonnummer von CVC?

Diese Seite nennt keine, weil CVCs eigene Seiten keine Telefonnummer veröffentlichen. Wende dich an den Shop, der das Paket verschickt hat: er hat die Lieferung bei CVC gebucht.

:::sources
- [CVC: e-CVC.bg Tracking](https://my.e-cvc.bg/track) – Tracking auf Bulgarisch, „Потърсете товарителница“, nur Ziffern, höchstens 20 Zeichen, „Провери“, Nummer nach `wb=`, Name e-CVC.bg, CVC-Logo
- [CVC: e-CVC.bg Login](https://my.e-cvc.bg/) – nur Anmeldeseite, keine Liefer-, Preis- oder Kontaktseiten
- [Universal Parcel Scraper: CVC](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/cvc) – Namen und weitere Bezeichnungen, Bulgarien, Tracking-Adresse, Link-Regel, kein Nummernformat, direkter Abruf von my.e-cvc.bg/track und bulgarische Status
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – ergänzende Tracking-Dienste, Zuordnung des Paketdienstnamens zu CVC, Link zur Website des Dienstes
:::
