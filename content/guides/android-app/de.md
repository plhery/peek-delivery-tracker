---
title: Peek für Android: Beta holen, Widget „Pakete“ hinzufügen
description: Peek für Android ist eine Beta: Mach beim Google-Play-Test mit oder installier es über F-Droid oder GitHub. Ohne Konto. Füg das Widget hinzu, teile Links mit Peek.
slug: sendungsverfolgung-app-android
picture: Ein Android-Handy mit dem Widget „Pakete“ auf dem Startbildschirm, und Pip, das Paket mit Gesicht, schaut oben über den Rand des Handys.
published: 2026-11-19
updated: 2026-11-19
---

Peek für Android ist eine Beta und nicht öffentlich bei Google Play: Du bekommst es über [peektracker.com/android](https://peektracker.com/android), indem du beim Google-Play-Test mitmachst oder es aus Peeks eigener F-Droid-Paketquelle oder von GitHub installierst. Es braucht Android 12 oder neuer und kein Konto, und es bringt ein Widget „Pakete“ auf deinen Startbildschirm und „Zu Peek hinzufügen“ in dein Teilen-Menü.

Es verfolgt Pakete von mehr als 4000 Paketdiensten: Füge eine Sendungsnummer, einen Link vom Paketdienst oder eine ganze Versandmail ein, und [Peek](/) findet den Paketdienst und zeigt die Reise auf einer Karte. Es ist kostenlos, ohne In-App-Käufe und ohne Werbung. Für iPhone, iPad oder Mac siehe [Peek für iPhone, iPad und Mac](guide:iphone-app).

## Gibt es Peek bei Google Play?

Nur für Tester. Peek ist in einem geschlossenen Test, und seine Google-Play-Seite öffnet sich für niemanden außerhalb davon. Google gibt es für alle frei, sobald mindestens 12 Tester es 14 Tage am Stück behalten haben, und jeder kann beim Test mitmachen. Öffne auf deinem Android-Handy [peektracker.com/android](https://peektracker.com/android), „Teste Peek für Android“:

:::steps
- „Tritt der Testergruppe bei“ | Tippe auf „Gruppe beitreten“, tritt auf der Seite, die sich öffnet, der Google-Gruppe bei und komm dann zurück.
- „Werde Tester bei Google Play“ | Tippe auf „Google Play öffnen“, dann auf der Seite, die sich öffnet, auf **Tester werden**.
- „Installier sie und behalte sie zwei Wochen“ | Tippe auf „Peek installieren“ und behalte die App 14 Tage.
:::

Bis du dem Link der Gruppe in diesem Browser gefolgt bist, steht auf den nächsten beiden Knöpfen „Zuerst der Gruppe beitreten“. Du kannst den Test jederzeit auf derselben Google-Play-Seite verlassen.

### Wie installiere ich Peek ohne Google Play?

Füge Peeks eigene Paketquelle von Hand zur F-Droid-App hinzu oder lade die App von GitHub herunter. Peek ist nicht im Hauptkatalog von F-Droid.

:::steps
- Kopiere die Adresse | Tippe auf [peektracker.com/android](https://peektracker.com/android) unter „Ohne Google Play“ auf „Adresse kopieren“.
- Füge die Paketquelle hinzu | Geh in der F-Droid-App zu **Einstellungen** › **Paketquellen** › **+** und füge dann die ganze Adresse ein.
- Installiere Peek | Tippe auf die neue Paketquelle, um ihre Apps zu sehen, dann installiere „Peek“. F-Droid installiert nichts, bevor du es auswählst.
:::

Oder tippe auf derselben Seite auf „GitHub öffnen“: Tippe in der neuesten Version unter [Peeks GitHub-Releases](https://github.com/plhery/peek-delivery-tracker/releases) auf die .apk-Datei und erlaube dann deinem Browser, sie zu installieren. Peeks Releases sind dort als „Pre-release“ markiert.

Diese Version ist von Peek signiert, nicht von Google, deshalb kann sie eine Kopie von Google Play nicht aktualisieren, und umgekehrt: Hast du Peek von Google Play oder eine frühe Kopie aus der Paketquelle oder von GitHub, deinstalliere sie vorher. Updates kommen über Peeks F-Droid-Paketquelle. Keiner der beiden Wege macht dich zum Tester bei Google Play.

### Was unterscheidet die Google-Play- und die F-Droid-Version?

| Was | Google-Play-Version | F-Droid- und GitHub-Version |
| --- | --- | --- |
| Dienste von Google | Firebase, ML Kit, Play Integrity | Keine |
| Barcode-Scanner | Ja | Nein, und keine Kameraberechtigung |
| Benachrichtigungen | Über die Google-Play-Dienste | Über eine UnifiedPush-App wie ntfy, oder Peek schaut etwa alle 15 Minuten nach |
| Signiert und aktualisiert von | Google, über Google Play | Peek, über seine F-Droid-Paketquelle |

Wie die Benachrichtigungen in jeder Version funktionieren, steht unter [Paketbenachrichtigungen](guide:parcel-notifications).

## Welche Android-Version braucht Peek?

Android 12 oder neuer, in beiden Versionen. Ein Gerät ohne Kamera auf der Rückseite kann es trotzdem installieren, nur ohne Scanner.

Es spricht zehn Sprachen: Englisch, Deutsch, Französisch, Italienisch, Spanisch, Portugiesisch, brasilianisches Portugiesisch, Polnisch, Russisch und vereinfachtes Chinesisch. Wähl eine unter „Einstellungen“ › „Präferenzen“ › „Sprache“ („Einstellungen“ öffnest du mit dem Konto-Knopf oben in „Sendungen“) oder in Androids eigener Spracheinstellung pro App. Wählst du sie in Peeks Einstellungen, bleiben „Zu Peek hinzufügen“, der Name des Widgets in der Auswahl und die Verknüpfung „Paket hinzufügen“ in der Sprache deines Handys; die Texte im Widget selbst folgen der Sprache von Peek.

## Kann ich Peek auf Android ohne Konto nutzen?

Ja. Füge auf dem ersten Bildschirm eine Sendungsnummer ein, und Peek verfolgt sie, gelistet nur auf diesem Handy; das Widget und die Verknüpfung zum nächsten Paket funktionieren auch. In „Sendungen“ steht „Alle deine Pakete an einem Ort“, mit „Vorerst nur auf diesem Gerät.“ und einem Knopf „Anmelden“.

Ein Paket wird 30 Tage nach der Zustellung vergessen; „Einstellungen“ › „Auf diesem Gerät“ › „Alle vergessen“ vergisst alle auf einmal. Die eigenen Backups des Handys enthalten die Pakete, die es verfolgt, und Peeks Präferenzen.

Ein Konto bringt dieselbe Liste auf jedem Gerät und auf peektracker.com, dazu den Reisepass und Freunde, die Benachrichtigungseinstellungen und „Pakete per E-Mail hinzufügen“ ([Pakete aus deinen E-Mails verfolgen](guide:add-parcels-by-email)). Melde dich mit „Mit Google fortfahren“ oder „Mit E-Mail anmelden“ an; ein Anmelden mit Apple gibt es auf Android nicht. Auch „Demo erkunden“ auf dem Anmeldebildschirm braucht kein Konto. [Peek ohne Konto nutzen](guide:account-or-guest) vergleicht beides.

## Wie lege ich das Peek-Widget auf meinen Startbildschirm?

Füge „Pakete“, Peeks einziges Widget, aus der Widget-Auswahl hinzu, wo dazu steht: „Deine Pakete unterwegs auf einen Blick, das nächste zuerst.“ Die Schritte von Google:

:::steps
- Öffne die Widgets | Halte auf einem Startbildschirm eine freie Stelle gedrückt, dann tippe auf **Widgets**.
- Finde Peek | Tippe auf Peek, um sein Widget „Pakete“ zu sehen.
- Platziere es | Halte das Widget gedrückt, schieb es an die gewünschte Stelle und nimm den Finger weg.
:::

Google warnt: „Einige dieser Schritte funktionieren nur unter Android 14 oder höher.“

„Pakete“ startet mit vier mal zwei Feldern und lässt sich auf zwei mal zwei verkleinern. Es listet die Pakete, die unterwegs sind, dann die am letzten Tag zugestellten, in Peeks Reihenfolge. Jedes Paket öffnet seine Seite; der Rest des Widgets öffnet deine Liste. Es wird mindestens alle 20 Minuten neu gezeichnet, und seine Tage wechseln um Mitternacht, auch bei geschlossener App.

| Das Widget zeigt | Was es bedeutet |
| --- | --- |
| „Gerade ist nichts unterwegs“ | Kein Paket ist unterwegs: „Füge eine Sendungsnummer oder einen Tracking-Link hinzu.“ |

„Pakete“ gibt es nur auf dem Startbildschirm: Es gibt kein Widget für den Sperrbildschirm und nichts Vergleichbares zu Live-Aktivitäten.

### Hat Peek Verknüpfungen auf seinem App-Symbol?

Ja. Halte Peeks Symbol gedrückt: Dort stehen „Paket hinzufügen“, das den gleichnamigen Bildschirm öffnet, und eine Verknüpfung mit dem Namen deines nächsten Pakets unterwegs, die dieses Paket öffnet. Um eine davon an deinen Startbildschirm anzuheften, halte sie gedrückt und schieb sie an ihren Platz. Peek hat auf Android keine Sprachbefehle: Diese Verknüpfungen ersetzen die Siri-Aktionen des iPhones.

## Wie teile ich einen Tracking-Link auf Android mit Peek?

Teile ihn und wähle „Zu Peek hinzufügen“, das jeden geteilten Text und jeden Link annimmt.

:::steps
- Teile | Teile die Sendungsnummer, den Link oder die Seite aus einer beliebigen App, dann wähle „Zu Peek hinzufügen“.
- Speichere | Tippe auf der Karte, die sich öffnet, auf „Sendungsangaben speichern“. Sie meldet „Gespeichert. Öffne Peek innerhalb von 10 Minuten, um dein Paket zu prüfen und hinzuzufügen.“
- Öffne Peek | Innerhalb dieser 10 Minuten öffnet sich „Paket hinzufügen“ mit den ausgefüllten Angaben.
:::

Peeks Paketlinks (peektracker.com/p/…) öffnen sich in der App, sobald sie installiert ist. Willst du ein Paket an jemand anderen weitergeben, siehe [die Sendungsverfolgung eines Pakets teilen](guide:share-parcel).

### Kann Peek auf Android einen Barcode scannen?

Nur die Google-Play-Version kann das. Tippe auf das Barcode-Symbol am Ende des Sendungsfelds (vorgelesen als „Barcode scannen“), auf dem ersten Bildschirm oder in „Paket hinzufügen“; bei leerer Liste gibt es außerdem einen Knopf „Barcode scannen“. „Sendungsbarcode scannen“ öffnet sich mit der Rückkamera und folgt dem Barcode, der der Mitte am nächsten ist. Verweigerst du die Kamera, verschwindet der Scanner, bis du sie in den Android-Einstellungen erlaubst. Unsicher, welche Nummer die Sendungsnummer ist? Siehe [Sendungsnummer finden](guide:find-tracking-number).

:::sources
- [Peek: Teste Peek für Android](https://peektracker.com/android) – die drei Schritte des Tests, Android 12, die F-Droid-Paketquelle, GitHub, die Signatur
- [Peek: Notizen zu Google Play (auf Englisch)](https://github.com/plhery/peek-delivery-tracker/blob/main/docs/google-play/README.md) – geschlossener Test, 12 Tester für 14 Tage, die Testergruppe
- [Peek: Android-App (auf Englisch)](https://github.com/plhery/peek-delivery-tracker/blob/main/android/README.md) – die beiden Versionen, Inhalt und Aktualisierung des Widgets, Verknüpfungen, Signaturschlüssel, Vorabversionen
- [Peek: F-Droid-Eintrag (auf Englisch)](https://github.com/plhery/peek-delivery-tracker/blob/main/android/fdroid/com.plhery.peekdeliverytracker.yml) – Beta, Name, welche früheren Kopien zu deinstallieren sind
- [F-Droid: Wie ein Repo zu F-Droid hinzugefügt wird](https://f-droid.org/de/tutorials/add-repo/) – Einstellungen › Paketquellen › +, dann die zu installierenden Apps wählen
- [Peek: GitHub-Releases](https://github.com/plhery/peek-delivery-tracker/releases) – die .apk-Dateien, als Pre-release markiert
- [Peek: deutsche Texte](https://github.com/plhery/peek-delivery-tracker/blob/main/shared/locales/de.json) – jede hier zitierte Peek-Beschriftung
- [Peek: Android-Manifest](https://github.com/plhery/peek-delivery-tracker/blob/main/android/app/src/main/AndroidManifest.xml) – „Zu Peek hinzufügen“, Paketlinks, Kamera, Backups
- [Peek: das Widget „Pakete“](https://github.com/plhery/peek-delivery-tracker/blob/main/android/app/src/main/res/xml/parcels_widget.xml) – nur auf dem Startbildschirm, seine Größen
- [Peek: Einstellungen](https://github.com/plhery/peek-delivery-tracker/blob/main/android/app/src/main/kotlin/com/plhery/peekdeliverytracker/ui/settings/SettingsSheet.kt) – „Alle vergessen“, „Sprache“, keine Live-Aktivitäten
- [Peek: der Scanner der Play-Version](https://github.com/plhery/peek-delivery-tracker/blob/main/android/app/src/play/kotlin/com/plhery/peekdeliverytracker/platform/CameraLabelScanner.kt) – Barcodes, Kameraberechtigung
- [Peek: Hilfe (auf Englisch)](https://peektracker.com/support.html) – kein Konto nötig, „Alle vergessen“
- [Android-Hilfe: Apps, Verknüpfungen und Widgets auf Startbildschirmen hinzufügen](https://support.google.com/android/answer/9450271?hl=de) – ein Widget hinzufügen, eine Verknüpfung anheften, Android 14
:::
