---
title: Russian Post Sendungsverfolgung (Почта России)
description: Paket von Russian Post (Почта России) verfolgen: RU- und 14-stellige Nummern, russische Statusmeldungen erklärt und Tracking mit Peek.
slug: russian-post-sendungsverfolgung
published: 2026-11-22
updated: 2026-11-22
---

Russian Post (Почта России, Pochta Rossii) ist der Postdienst Russlands. Das Unternehmen befördert Pakete aus Russland und nach Russland. Die Sendungsnummer erhältst du vom Absender oder vom Shop: Füge sie oben ins Feld ein. Die Website von Russian Post lautet pochta.ru, und Peek fragt das Tracking für dich ab, wie unten beschrieben.

## Sendungsnummer von Russian Post: wie sie aussieht

Mit erfundenen Ziffern:

| Nummer (erfunden) | Was es ist |
| --- | --- |
| `RR482910576RU` | Zwei Buchstaben, neun Ziffern und `RU`: das internationale Postformat mit Ländercode für Russland und Prüfziffer als neunter Ziffer. Beginnt die Nummer mit C, E, L oder R, erkennt Peek Russian Post sofort. |
| `VV482910576RU` | Eine andere internationale `RU`-Nummer, die nicht mit C, E, L oder R beginnt. Peek zeigt sie als „Unknown postal carrier“ (unbekannter Postdienst); du kannst Russian Post manuell auswählen. |
| `80482910573625` | 14 Ziffern, beginnend mit `80`, die letzte Ziffer ist eine Prüfziffer. Peek ordnet sie Russian Post nur als schwachen Treffer zu, weil auch DPD, SEUR, BRT, Delhivery und andere Paketdienste solche Nummern nutzen. |
| `80482910573620` | Dasselbe Format mit falscher Prüfziffer. Peek schlägt dafür nicht Russian Post vor, sondern nur die anderen Paketdienste: Prüfe die Nummer auf Tippfehler, falls sie von Russian Post stammt. |
| `RR482910576CN` | Eine Nummer einer anderen Post, hier [China Post](carrier:china-post). Peek verfolgt sie bei dieser Post, auch wenn die Sendung nach Russland geht. |

Leerzeichen und Kleinschreibung spielen beim Einfügen in Peek keine Rolle. Falls dir die Nummer fehlt, lies [wo du deine Sendungsnummer findest](guide:find-tracking-number) und [Sendungsnummern-Formate](guide:tracking-number-formats).

## Statusmeldungen von Russian Post erklärt

Russian Post schreibt seine Sendungsschritte auf Russisch. Peek liest sie so, wie sie in der Tabelle stehen, über das Tracking von Qwintry, einem Weiterleitungsdienst, der einen Teil seiner Pakete an Russian Post übergibt: bei Sendungen, die mit einer Qwintry-Nummer hinzugefügt wurden, oder bei `RU`-Nummern, die unter Qwintry geführt werden (siehe „Pakete von Qwintry“ unten). Dort enthält jeder Schritt von Russian Post eine Operation wie `обработка` (Bearbeitung), danach eine Detailzeile und am Ende das Postamt. Fehlt die Detailzeile, steht die Operation allein. Ungefähr in der Reihenfolge eines Pakets:

| Russian Post schreibt | Was das für dich bedeutet | Peek zeigt |
| --- | --- | --- |
| `Партионный` | Russian Post hat das Paket zusammen mit anderen in einer Sammelsendung angenommen (Operation `приём`, Annahme). | „Unterwegs“ |
| `экспорт международной почты` | Export internationaler Post: Das Paket verlässt das jeweilige Land. | „Unterwegs“ |
| `Прибыло на территорию РФ` | Das Paket ist in Russland eingetroffen. | „Unterwegs“ |
| `импорт международной почты` | Import internationaler Post: Das Paket wird in Russland übernommen. | „Unterwegs“ |
| `приём на таможню` | An den Zoll übergeben. | „Beim Zoll“ |
| `Выпущено таможней` | Vom Zoll freigegeben: Das Paket ist wieder unterwegs. | „Unterwegs“ |
| `Покинуло место международного обмена` | Hat das internationale Austauschamt verlassen. | „Unterwegs“ |
| `Сортировка` | Sortierung. | „Unterwegs“ |
| `Прибыло в сортировочный центр` | In einem Sortierzentrum eingetroffen. | „Unterwegs“ |
| `Покинуло сортировочный центр` | Hat ein Sortierzentrum verlassen. | „Unterwegs“ |
| `Прибыло в место вручения` | In der Postfiliale eingetroffen, die das Paket zustellt oder ausgibt. | „Unterwegs“ |
| `Передано почтальону` | Dem Zusteller zur Auslieferung übergeben. | „In Zustellung“ |
| `Временное отсутствие адресата` | Nicht zugestellt: Niemand war da. | „Zustellversuch“ |
| `Адресат заберет отправление сам` | Nicht zugestellt, weil du das Paket selbst abholst. | „Zustellversuch“ |
| `Вручение адресату` | An dich als Empfänger übergeben. | „Zugestellt“ |

Bei allen anderen Paketen von Russian Post fragt Peek den eigenen Tracker von Russian Post ab, der auf Englisch antwortet: siehe „Ein Russian-Post-Paket mit Peek verfolgen“ unten.

## Wie Russian Post zustellt

Die Schritte oben zeigen den Weg innerhalb Russlands: über Sortierzentren bis zu der Postfiliale, die das Paket ausgibt oder zustellt. Von dort kann es ein Zusteller bringen (`Передано почтальону`). Warst du nicht da oder holst du es selbst ab, steht das im Status. Beim Tracking über Qwintry zeigt Peek den Ort des Postamts als Scan-Ort an. Ist Peek auf Russisch eingestellt oder lässt sich das Amt nicht zuordnen, zeigt Peek den Namen des Postamts genau so an, wie Russian Post ihn schreibt.

Lieferzeiten, Lagerfristen und Regeln für Zustellversuche nennt diese Seite nicht, weil Peek sie nicht auf der Website von Russian Post prüfen konnte. Wenn ein Paket in einer Filiale wartet, helfen [nicht abgeholte Pakete](guide:uncollected-parcel) und [verpasste Zustellungen](guide:missed-delivery).

**Aus Russland in ein anderes Land verschickt.** Peek weiß nicht von vornherein, welches Unternehmen ein Russian-Post-Paket in deinem Land zustellt. Bei einer `RU`-Nummer fragt es jedoch die Post des Landes an, das der Russian-Post-Tracker als Zielland nennt, und kann das Paket dort weiterverfolgen (siehe unten).

**Aus einem anderen Land nach Russland verschickt.** Peek verfolgt das Paket bei dem Paketdienst, der die Nummer ausgestellt hat – direkt oder über Tracking-Dienste. Nennt dieses Tracking Russian Post als zustellenden Dienst, fragt Peek auch den Tracker von Russian Post an und übernimmt das Paket dorthin, sobald dort ein mindestens ebenso aktueller Scan vorliegt. Allein das Zielland Russland reicht für den Wechsel nicht aus.

## Paket von Russian Post hängt fest oder kommt nicht an

**Noch keine Schritte.** Der Tracker von Russian Post oder die Tracking-Dienste kennen die Nummer eventuell noch nicht, oder der Absender hat das Paket noch nicht abgegeben. Peek fragt weiter nach. Tut sich nach einigen Tagen nichts, prüfe die Nummer beim Absender. Mehr dazu unter [wenn sich das Tracking nicht aktualisiert](guide:tracking-not-updating).

**Beim Zoll.** Zeigt Peek „Beim Zoll“, liegt das Paket beim Zoll. Diese Seite nennt keine Zollregeln oder Gebühren, da sie nicht auf einer offiziellen Seite geprüft werden konnten. Siehe [Pakete beim Zoll](guide:customs).

**Peek hinkt hinterher.** Antwortet der Tracker von Russian Post Peek nicht, springt ein Tracking-Dienst ein und kann hinter dem eigenen Tracker zurückliegen. Vergleiche im Zweifel mit pochta.ru.

**Als zugestellt markiert, aber nichts erhalten.** Zeigt Peek „Zugestellt“ und du hast kein Paket, frage bei deiner Postfiliale nach und informiere den Absender. Mehr unter [zugestellt, aber nicht erhalten](guide:delivered-not-received).

**Verloren oder zurückgeschickt.** Wende dich zuerst an den Absender oder Shop, denn er hat den Versand beauftragt. Siehe [verlorene Pakete](guide:lost-parcel) und [zurück an den Absender](guide:returned-to-sender).

## Kundenservice und Kontakt von Russian Post

Weil Peek die Website von Russian Post nicht direkt abrufen konnte, nennt diese Seite keine Telefonnummer und keine E-Mail-Adresse: Wir führen nur Kontakte auf, die wir auf den Seiten des Paketdienstes selbst prüfen konnten. Die Hilfeseiten von Russian Post liegen auf info.pochta.ru. Bleibt eine Sendung stehen, verlinkt Peek die Tracking-Hilfeseite von Russian Post unter „Hol dir Hilfe bei Russian Post“.

Bei einer Online-Bestellung wende dich zuerst an den Shop: Er hat den Versand gebucht.

## Ein Russian-Post-Paket mit Peek verfolgen

Füge deine Sendungsnummer in [Peek](/) ein: Peek verfolgt mehr als 4500 Paketdienste. Je nach Format verhält sich Peek so:

- **`RU`-Nummer mit Anfangsbuchstabe C, E, L oder R und gültiger Prüfziffer.** Peek erkennt Russian Post sofort („Erkannter Paketdienst“).
- **Andere `RU`-Nummer (anderer Anfangsbuchstabe).** Peek zeigt sie als „Unknown postal carrier“. Tippe auf „Ändern“ (oder später auf „Paketdienst ändern“) und wähle Russian Post; auch die Eingabe Почта России findet den Dienst. Danach zeigt Peek Russian Post an.
- **14 Ziffern, beginnend mit `80`, mit gültiger Prüfziffer.** Beim Hinzufügen zeigt Peek „Tracking-Dienste werden geprüft…“ und fragt bei Russian Post sowie bei DPD und anderen passenden Paketdiensten nach, wer das Paket kennt. Hat nur Russian Post es, meldet Peek „Russian Post hat dieses Paket.“ und legt es dort ab. Andernfalls tippe auf „Ändern“: Russian Post steht unter „Passt zu dieser Nummer“, zusammen mit den übrigen passenden Paketdiensten, denen deines Landes zuerst. Bei einem bereits gespeicherten Paket nutze „Paketdienst ändern“.

Peek fragt den eigenen Tracker von Russian Post ab, der auf Englisch antwortet. Jeder Schritt erscheint in den englischen Formulierungen von Russian Post, zum Beispiel `Sorting`, `Arrived at the local distribution center` oder `Forwarded to the postman` („In Zustellung“), samt Uhrzeit, Ort, Land und Zielland. `Available for pickup at the Post Office` erscheint als „Abholbereit“ und `Handed over for temporary storage` als „Problem gemeldet“. Nach `Returned to the sender` (ebenfalls „Problem gemeldet“) geht das Paket zurück, und die Übergabe beim Absender erscheint als „Zurück an Absender“. Ein voraussichtliches Lieferdatum gibt Russian Post nicht an. Solange der Tracker die Nummer noch nicht kennt, zeigt Peek „Warten auf Russian Post“, beziehungsweise „Warten auf den ersten Scan von Russian Post“ bei einer `80`-Nummer ohne gewählten Paketdienst. Antwortet Russian Post nicht, fragt Peek nacheinander ParcelsApp, Ship24 und 17TRACK an – bei einer `RU`-Nummer zuletzt auch das Tracking des Weltpostvereins (UPU) – und zeigt den Verlauf des ersten Dienstes, der das Paket kennt, in dessen Wortlaut; dieser kann hinter pochta.ru zurückliegen. Ein Dienst, der die Nummer nicht kannte, wird nach einem Tag erneut gefragt.

Bei einer `80`-Nummer ohne gewählten Paketdienst oder einer als „Unknown postal carrier“ geführten `RU`-Nummer fragt Peek in den ersten 30 Tagen vor den Tracking-Diensten auch mögliche Paketdienste direkt an (etwa Russian Post, DPD und andere bei einer `80`-Nummer). Zeigt einer davon Bewegung, verschiebt Peek das Paket dorthin. Nennt bei einer `80`-Nummer ohne Paketdienst ein Tracking-Dienst ausschließlich Russian Post samt datierten Schritten, ordnet Peek sie selbst Russian Post zu. Bei einer `RU`-Nummer unter „Unknown postal carrier“ genügt diese Nennung allein nicht: Entweder muss der Tracker von Russian Post die Sendung zeigen, oder du wählst Russian Post selbst.

Peek bietet keinen direkten Link zum Tracker von Russian Post. Ist ein Tracking-Dienst eingesprungen, öffnet der Link auf der Paketseite diesen Dienst, etwa „Website von ParcelsApp öffnen“. Für pochta.ru öffne die Seite selbst.

**Pakete von Qwintry.** Wurde dein Paket über Qwintry nach Russland verschickt, füge stattdessen die Qwintry-Nummer ein: `QR` und acht Ziffern. Peek erkennt Qwintry sofort und liest dessen Tracker, der nach der Übergabe auch die Schritte von Russian Post anzeigt. Nennt Qwintrys Tracker Russian Post, zeigt das Paket „Zustellung durch Russian Post“ samt `RU`-Nummer, und Peek fragt zusätzlich Russian Post ab: Sobald dort ein mindestens ebenso aktueller Scan erscheint, folgt Peek dem Paket dort und behält Qwintrys bisherigen Verlauf. Liegt bei Russian Post erst die Ankündigung ohne eigenen Scan vor, steht stattdessen „Bei Russian Post angekündigt, noch nicht eingetroffen“. Qwintrys Tracker akzeptiert auch die `RU`-Nummer eines Pakets, das Qwintry der Post übergeben hat: Wenn du nur diese Nummer hast und der Tracker von Russian Post sie nicht kennt, tippe auf „Ändern“ und wähle Qwintry.

**Die Post in deinem Land.** Bei einer unter Russian Post geführten `RU`-Nummer nennt der Russian-Post-Tracker das Zielland, und Peek fragt mit derselben Nummer auch die Post dieses Landes an, sofern es deren Tracking direkt liest – etwa [USPS](carrier:usps), [Royal Mail](carrier:royal-mail), An Post, [Canada Post](carrier:canada-post) und Australia Post. Bei einem Paket unter „Unknown postal carrier“ reicht es, wenn ein Tracking-Dienst die zustellende Post samt Nummer – oder bei einer `RU`-Nummer das Zielland – nennt: Peek fragt diese Post genauso an und wechselt dorthin, sobald sie einen mindestens ebenso aktuellen Scan zeigt.

Solange du das Paket auf dem Bildschirm geöffnet hast, oder am Zustelltag mit aktivierten Benachrichtigungen, prüft Peek den Tracker von Russian Post bis zu alle 10 Minuten – und ab 6:00 Uhr Ortszeit am Tag der Zustellung bis zu alle 2 Minuten –, solange dieser Tracker antwortet; über Tracking-Dienste prüft Peek stündlich, nach einem Tag ohne neuen Scan alle 3 Stunden und am Tag der Zustellung bis zu alle 15 Minuten. Ansonsten sowie nach zwei Tagen ohne Neuigkeiten (nach 12 Stunden, solange das Paket noch auf „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ steht) prüft Peek stündlich. Benachrichtigungen lassen sich auf „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“ einstellen. Peek kann weder Russian Post noch den Absender für dich kontaktieren.

## Fragen zur Sendungsverfolgung von Russian Post

### Was bedeutet Почта России auf Deutsch?

Russische Post, international Russian Post oder in lateinischer Schrift Pochta Rossii. Die Website lautet pochta.ru.

### Wie sieht eine Sendungsnummer von Russian Post aus?

Eine internationale Nummer besteht aus zwei Buchstaben, neun Ziffern und `RU`, etwa `RR482910576RU`. Außerdem verknüpft Peek 14-stellige Nummern mit `80` am Anfang mit Russian Post, wenn die Prüfziffer stimmt.

### Warum weichen die Schritte in Peek von pochta.ru ab?

Peek fragt den Tracker von Russian Post auf Englisch ab und zeigt daher meist englische statt russischer Formulierungen. Antwortet dieser Tracker Peek nicht, übernimmt ein Tracking-Dienst mit eigenem Wortlaut, der hinterherhinken kann. Die russischen Schritte aus der Tabelle oben zeigt Peek bei Paketen an, die über Qwintrys Tracker laufen.

### Kann Peek ein Paket aus einem anderen Land nach Russland verfolgen?

Ja. Füge die Nummer vom Absender ein: Peek verfolgt sie beim ausstellenden Paketdienst und wechselt erst zu Russian Post, wenn dieses Tracking Russian Post als zustellenden Dienst nennt.

:::sources
- [Universal Parcel Scraper: Russian Post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/russian-post) – Namen, 80- und RU-Nummernformate mit Prüfziffern und Treffern, abgefragter Tracker samt englischen Statusmeldungen, Orte, Zielland, Stufen und Hilfeseite
- [Universal Parcel Scraper: Qwintry](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/qwintry) – russische Russian-Post-Schritte auf Qwintrys Tracker und ihre Stufen, Postamt als Ort, QR-Nummer und akzeptierte RU-Nummern
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – RU-Postnummern, zuerst angefragte Paketdienste, Reihenfolge der Tracking-Dienste, Übergabe an die Post im Zielland und an Russian Post
:::
