---
title: Wann kommt mein Paket? Die Lieferschätzung von Peek AI
description: Peek AI ist Peeks eigene Schätzung, wann dein Paket kommt, neben dem Datum des Paketdienstes. Wann sie erscheint, wie sicher sie ist, wie du sie ausschaltest.
slug: wann-kommt-mein-paket
picture: Pip, das Paket mit Gesicht, späht auf ein Handy, auf dem die Karte eines Pakets unter dem Datum des Paketdienstes ein kleines Funkeln und „Peek AI bis 16:00“ zeigt.
published: 2026-11-24
updated: 2026-11-25
---

Wenn [Peek](/) einen starken Hinweis hat, wann dein Paket ankommt, sagt Peek AI es neben dem Datum des Paketdienstes, oder an dessen Stelle, wenn der Paketdienst keins nennt: „wahrscheinlich bis 16:00“, „wahrscheinlich heute oder morgen“, „vielleicht früher“ oder „heute eher nicht“. Das ist Peeks eigene Schätzung, nie ein Versprechen des Paketdienstes, und bei den meisten Paketen steht keine.

Peek AI ist standardmäßig eingeschaltet, mit oder ohne Konto: auf peektracker.com, in der App für iPhone, iPad und Mac und in der [Android-App](guide:android-app), ob von Google Play, aus Peeks eigener F-Droid-Paketquelle oder von GitHub.

:::steps
- Schau in deine Liste | Unter dem Datum des Paketdienstes auf der Karte eines Pakets, oder neben dem Status bei „Als Nächstes“: „✦ Peek AI“ und ein paar Worte, etwa „bis 16:00“.
- Öffne das Paket | Eine kleine Karte zeigt etwa „✦ Peek AI wahrscheinlich bis 16:00“, mit einer Zeile, die sagt, warum.
- Tippe auf (i) | Der Knopf (i) („Über Peek AI“) öffnet das Fenster „Peek AI“: die Schätzung, darunter das, was der Paketdienst selbst sagt, etwa „Laut DPD: heute“.
- Lies die Einzelheiten | Oft die wahrscheinlichen Tage oder Uhrzeiten, farblich abgestuft nach ihrer Wahrscheinlichkeit; dann „Warum“, „Wie sicher“ und der Schalter „Peek AI zeigen“.
:::

Das Widget „Pakete“ auf dem [iPhone](guide:iphone-app) und unter Android zeigt die Schätzung, wo sie Platz hat, und [verbundene Assistenten](guide:ai-assistants) bekommen sie als Schätzung von Peek AI, nicht als Angabe des Paketdienstes. [Benachrichtigungen](guide:parcel-notifications), Zustell-E-Mails, die Reisepässe von Freunden und die Vorschau eines geteilten Links lassen sie weg. [Peeks Demo](https://peektracker.com/demo) hat Beispielpakete mit einer Schätzung.

## Wann kommt mein Paket an? Was Peek AI dir sagen kann

Peek AI meldet sich nur, wenn einer von fünf Hinweisen zutrifft, von denen jeder bei den Paketen, die Peek verfolgt hat, in mindestens 8 von 10 Fällen richtig lag.

| Der Hinweis | Peek AI sagt | Die Zeile mit dem Grund, zum Beispiel |
| --- | --- | --- |
| In Zustellung, keine Uhrzeit vom Paketdienst | „wahrscheinlich bis 16:00“ | „Seit 09:12 in der Zustellung. DPD nennt keine Uhrzeit.“ |
| Der Paketdienst sagt heute; am Nachmittag noch nicht in Zustellung | „heute eher nicht“ | „Laut DPD kommt das Paket heute, aber es ist noch nicht in der Zustellung.“ |
| Das Datum des Paketdienstes war gestern; in den letzten 24 Stunden gescannt | „wahrscheinlich heute oder morgen“ | „Das Datum von DPD war gestern, aber das Paket ist wieder unterwegs.“ |
| Das Datum des Paketdienstes ist noch Tage entfernt; ähnliche Pakete kommen meist früher | „vielleicht früher“ | „Laut DPD kommt das Paket am Fr 27. Nov. Es ist früher dran.“ |
| Kein Datum vom Paketdienst | „wahrscheinlich bis Do 26. Nov“, „wahrscheinlich diese Woche“ und andere | „UPS nennt kein Datum. Das Paket ist gestern beim Zoll angekommen.“ |

DPD und UPS stehen hier für deinen Paketdienst. Auf einer Karte wird „heute eher nicht“ zu „nicht heute“, oder zu „eher nicht“ neben einem Datum des Paketdienstes, das heute ist. Uhrzeiten richten sich nach deiner Einstellung (12- oder 24-Stunden-Format).

Jede Schätzung verschwindet, wenn sie abläuft, auch von einer offen gelassenen Seite: „heute eher nicht“ um Mitternacht, „wahrscheinlich bis 16:00“ um 16 Uhr, „vielleicht früher“ am Datum des Paketdienstes und ein Tag, sobald er vorbei ist.

### Warum zeigt mein Paket keine Schätzung?

Meist, weil Peek keinen starken Hinweis hat. Peek AI schweigt außerdem:

- sobald das Paket zugestellt, archiviert, an einer Abholstelle oder auf dem Rückweg ist, oder nach einem Zustellversuch (siehe [Paket verpasst](guide:missed-delivery));
- vor seinem ersten echten Scan: Ein Etikett allein reicht nicht;
- wenn der Paketdienst die Zustellung unter seinen geplanten Schritten führt oder für heute eine Uhrzeit oder ein Zeitfenster nennt, das noch nicht vorbei ist;
- bevor das Paket in Zustellung ist, wenn Peek es länger nicht abgefragt hat (seit 36 Stunden, außer bei „heute eher nicht“);
- sobald das Datum des Paketdienstes mehr als einen Tag zurückliegt (siehe [wann du dir Sorgen machen solltest](guide:tracking-not-updating));
- bei „vielleicht früher“ und Schätzungen ohne Datum des Paketdienstes 45 Tage nach dem ersten Scan oder 30 Tage nach dem neuesten Scan.

## Stimmt das Lieferdatum des Paketdienstes? „Vielleicht früher“ und „heute eher nicht“

Nicht immer, und Peek AI sagt es in zwei Fällen: „vielleicht früher“, wenn Pakete mit Scans wie deinen meist vor dem Datum des Paketdienstes ankommen, und „heute eher nicht“, wenn der Paketdienst heute sagt, das Paket aber am Nachmittag noch nicht in Zustellung ist.

„Vielleicht früher“ kann den ersten wahrscheinlichen Tag nennen („vielleicht früher, ab morgen“, „vielleicht früher, ab Do 26. Nov“) und kommt nie, wenn der Paketdienst eine Spanne von Tagen nennt.

## Paket in Zustellung, aber noch nicht da: Kommt es heute noch?

Vielleicht sagt Peek AI es dir. „Wahrscheinlich bis 16:00“ heißt, dass die meisten Pakete dieses Paketdienstes, die Peek verfolgt hat, an ihrem Zustelltag bis zu dieser Uhrzeit ankamen; es erscheint nur, wenn der Paketdienst keine Uhrzeit nennt und das Paket vorher losgefahren ist. Spät am Tag heißt „heute eher nicht“, dass so spät nur wenige ankommen: „Um diese Zeit noch in der Zustellung. Bei DPD kommen so spät nur wenige an.“ Bei manchen Paketdiensten sagt Peek AI nichts, solange das Paket in Zustellung ist, weil Peek ihren Scans „In Zustellung“ nicht traut.

Die üblichen Uhrzeiten jedes Paketdienstes, und warum ein Wagen um 20 Uhr noch unterwegs sein kann, stehen unter [wann Pakete zugestellt werden](guide:delivery-times); was der Status bedeutet, unter [Sendungsstatus](guide:tracking-statuses).

## Der Paketdienst nennt kein Lieferdatum: Wie lange dauert es?

Peek AI kann einen Tag oder eine Spanne nennen, je nachdem, wessen sich sein Modell sicher genug ist: „wahrscheinlich bis Do 26. Nov“, „wahrscheinlich heute oder morgen“ (nur wenn der Paketdienst morgen zustellt), „wahrscheinlich diese Woche“ (bis Sonntag) oder „wahrscheinlich bis Ende nächster Woche“ (bis zum Sonntag danach).

Wie lange Pakete aus China meist brauchen, steht unter [Paket aus China verfolgen](guide:tracking-from-china).

## Wie sagt Peek AI Lieferzeiten voraus, und wie genau ist es?

Peek AI ist ein trainiertes Modell plus Regeltabellen auf Peeks eigenem Server, und es zeigt nur Arten von Schätzungen, die in mindestens 8 von 10 Fällen richtig lagen.

Das Modell, ein Gradient-Boosting-Klassifikator aus Entscheidungsbäumen, gibt die Wahrscheinlichkeit an, dass ein Paket bis zu jedem der kommenden Zustelltage des Paketdienstes angekommen ist. Es liest die Scans und wo sie stattfanden, den Paketdienst, den Service, das Gewicht, die Zeitzone und das angegebene Zielland, und ob das Paket ohne Konto abgefragt wurde. Die Regeltabellen enthalten die übliche Zustelluhrzeit jedes Paketdienstes und wie oft jede Art von Schätzung eintraf.

Der Server berechnet die Schätzung jedes Mal, wenn er die Angaben zum Paket an dein Handy oder deinen Browser schickt, ohne einen externen Dienst aufzurufen; dort wird sie nur angezeigt. Nur Peeks Server hat das Modell: Weder das Modell noch der Code, mit dem es trainiert wird, steht in [Peeks Open-Source-Code](guide:developers). Peek AI ist kein Chatbot.

„Wie sicher“ sagt „Bei Paketen wie diesem in 8 von 10 Fällen richtig.“, bei „heute eher nicht“ dagegen „Zu dieser Uhrzeit in 8 von 10 Fällen richtig.“ Die Zahl ist 8 oder 9: Peek AI zeigt nichts unter 8 von 10, behauptet nie mehr als 9 und rundet ab.

## Wie schalte ich Peek AI aus?

Schalte „Peek AI“ unter „Präferenzen“ in Peeks „Einstellungen“ aus, oder tippe auf der Peek-AI-Karte eines Pakets auf (i) und schalte „Peek AI zeigen“ aus.

:::steps
- Öffne die Einstellungen | Auf der Website mit dem Konto-Knopf (dein Anfangsbuchstabe) oder, ohne Konto, mit dem Zahnrad-Knopf „Einstellungen“. In den Apps mit dem Konto-Knopf oben in „Sendungen“; auf dem Mac im Fenster „Einstellungen“ von Peek, Tab „Allgemein“.
- Finde Peek AI | „Peek AI“ ist die letzte Zeile unter „Präferenzen“, mit dem Hinweis „Wenn Peek einen starken Hinweis hat, sagt es dir, wann ein Paket wohl kommt.“
- Schalte es aus | Peek AI verschwindet auf diesem Gerät von den Karten, aus „Als Nächstes“, von den Seiten der Pakete und aus den Widgets.
:::

Mit Konto folgt dir die Wahl auf deine anderen Geräte, und verbundene Assistenten bekommen die Schätzung nicht mehr. Ohne Konto bleibt sie in diesem Browser oder auf diesem Gerät; [Peek ohne Konto nutzen](guide:account-or-guest) vergleicht beides.

:::sources
- [Peek: Peek AI auf dem Server](https://github.com/plhery/peek-delivery-tracker/blob/main/src/server/peekAi.ts) – die fünf Hinweise, 8 von 10, 36 Stunden, wann Peek AI schweigt
- [Peek: die Worte von Peek AI](https://github.com/plhery/peek-delivery-tracker/blob/main/src/lib/peekAi.ts) – die Worte auf Karten und Seiten, wann jede Schätzung abläuft, „Wie sicher“
- [Peek: Karte und Fenster von Peek AI](https://github.com/plhery/peek-delivery-tracker/blob/main/src/components/PeekAi.tsx) – die Karte, das Fenster hinter (i), „Peek AI zeigen“
- [Peek: das Modell](https://github.com/plhery/peek-delivery-tracker/blob/main/src/server/peekAiModel.ts) – Entscheidungsbäume, was es liest, 45 und 30 Tage
- [Peek: Architektur (auf Englisch)](https://github.com/plhery/peek-delivery-tracker/blob/main/docs/ARCHITECTURE.md) – wie Peek AI funktioniert, wo es nicht erscheint, die Wahl des Kontos
- [Peek: deutsche Texte](https://github.com/plhery/peek-delivery-tracker/blob/main/shared/locales/de.json) – jede hier zitierte Peek-Beschriftung
- [Peek: Präferenzen auf der Website](https://github.com/plhery/peek-delivery-tracker/blob/main/src/components/Preferences.tsx) – der Schalter „Peek AI“, der dem Konto folgt
- [Peek: Einstellungen für iPhone, iPad und Mac](https://github.com/plhery/peek-delivery-tracker/blob/main/ios/PeekDeliveryTracker/SettingsViews.swift) – „Peek AI“ unter „Präferenzen“, „Allgemein“ auf dem Mac
- [Peek: Einstellungen unter Android](https://github.com/plhery/peek-delivery-tracker/blob/main/android/app/src/main/kotlin/com/plhery/peekdeliverytracker/ui/settings/SettingsSheet.kt) – „Peek AI“ unter „Präferenzen“
- [Peek: die Werkzeuge der Assistenten](https://github.com/plhery/peek-delivery-tracker/blob/main/src/server/assistants/tools.ts) – die Schätzung in der Paketliste eines Assistenten
- [App Store: Peek](https://apps.apple.com/app/id6823615370) – Peek AI in den Versionshinweisen der App
- [Peek: Versionshinweise für Android (auf Englisch)](https://github.com/plhery/peek-delivery-tracker/blob/main/fastlane/metadata/android/en-US/changelogs/21.txt) – Peek AI in der Android-App
- [Peek: Peek für Android](https://peektracker.com/android) – die App bei Google Play, auf F-Droid und GitHub
- [Peek: Demo](https://peektracker.com/demo) – Beispielpakete mit einer Schätzung
:::
