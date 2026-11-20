---
title: Pakete in Home Assistant verfolgen
description: Peeks Integration bringt deine Pakete als Sensoren, Lieferkalender und Dashboard-Karte in Home Assistant. Was du brauchst, wie du sie installierst und einrichtest.
slug: pakete-verfolgen-home-assistant
picture: Ein Tablet an einer Küchenwand zeigt ein Dashboard fürs Zuhause, mit einer Paketliste, einem kleinen Lieferkalender und Pip, dem Paket mit Gesicht, das aus einer der Karten lächelt.
published: 2026-11-19
updated: 2026-11-20
---

Um deine Pakete in Home Assistant zu verfolgen, installiere Peek für Home Assistant: Die Integration holt deine Paketliste aus [Peek](/) in Home Assistant, in Form von Sensoren, einem Lieferkalender, einer Dashboard-Karte und einem Werkzeug für Assist. Du brauchst ein kostenloses Peek-Konto und Home Assistant 2026.11 oder neuer, wo du im Marketplace nach Peek suchst; unter 2026.10 suchst du in HACS danach.

## Was zeigt Peek in Home Assistant?

Deine Peek-Pakete, in den Formen, die Home Assistant nutzt:

| Was | Was es tut |
| --- | --- |
| Sensoren | Zählen die Pakete, die unterwegs sind, die heute ankommen, die dich brauchen (an einer Abholstelle wartend, eine verpasste Zustellung, Zoll) und die heutigen Zustellungen, und nennen den nächsten Liefertag |
| Ein Kalender „Deliveries“ | Ganztägige Termine: jedes Paket unterwegs an seinem erwarteten Tag, jede Zustellung an dem Tag, an dem sie kam |
| Ein Ereignis „Parcel updates“ | Wird ausgelöst, wenn ein Paket hinzugefügt wird, seinen Status ändert, in Zustellung geht, dich braucht, zugestellt wird oder sich sein erwarteter Tag ändert |
| Aktionen | Pakete aus einem Text hinzufügen, ein Paket umbenennen, seinen Paketdienst ändern, seine Mitteilungen aus- oder einschalten, es archivieren oder wiederherstellen oder die Liste lesen |
| Die Karte „Peek parcels“ | Deine Pakete auf einem Dashboard, als Liste oder als Karten mit Pip |
| Ein Werkzeug für Assist | Beantwortet Fragen wie „Was kommt heute an?“ |

Home Assistant fragt Peek alle 30 Minuten ab, alle 10 Minuten an einem Tag, an dem ein Paket in Zustellung ist oder erwartet wird, und direkt nachdem eine seiner Aktionen ein Paket geändert hat. Um sofort abzufragen, rufe `homeassistant.update_entity` für eine beliebige Peek-Entität auf. „Heute“ ist der Tag in der Zeitzone von Home Assistant.

## Was brauche ich?

- **Home Assistant 2026.11 oder neuer**, wie Peeks Seite sagt, und eine Anmeldung als Administrator: Der Marketplace von Home Assistant ist nur für Administratoren da. Unter 2026.10 installierst du sie über HACS.
- **Ein Peek-Konto.** Beim Einrichten öffnet sich Peek zur Anmeldung, mit einem sechsstelligen Code, den Peek dir mailt, oder mit Google oder Apple, wo der Bildschirm das anbietet. Bei deiner ersten Anmeldung wird das Konto angelegt; als Gast kannst du Home Assistant nicht verbinden.
- **Sonst nichts.** Peek ist kostenlos, und du musst weder eine Client-ID noch ein Secret noch Application Credentials eintippen. Mit jedem Home Assistant lässt sich nur ein Peek-Konto verbinden.

Die Integration verbindet sich wie die Assistenten, und Peeks Verbindungen für Assistenten sind in der Betaphase. Meldet Peek „Peek kann mit diesem Konto noch keine Assistenten verbinden.“, lässt sich die Integration noch nicht einrichten.

## Wie installiere ich die Peek-Integration?

Suche im Marketplace von Home Assistant nach Peek. Unter 2026.10 suche in HACS danach oder installiere sie von Hand.

### Aus dem Marketplace (Home Assistant 2026.11 und neuer)

:::steps
- Öffne „Settings › Marketplace“ | Suche nach „Peek“ und öffne den Eintrag.
- Installiere Peek | Wähle „Install“, dann „Set up“.
- Melde dich bei Peek an | Dein Browser öffnet Peek. Prüfe, worum Home Assistant bittet (deine Pakete sehen, sie hinzufügen und archivieren, sie ändern und wiederherstellen), und wähle dann „Erlauben“.
- Geh zurück zu Home Assistant | Peek schickt dich über My Home Assistant (my.home-assistant.io) zurück. Beim ersten Mal fragt es nach der Adresse deines Home Assistant.
:::

### Mit HACS (Home Assistant 2026.10)

:::steps
- Suche nach Peek | Suche in HACS nach „Peek“ und öffne den Eintrag.
- Lade Peek herunter | Starte Home Assistant dann neu.
- Füge die Integration hinzu | Geh zu „Settings › Devices & services › Add integration“, wähle Peek, melde dich bei Peek an und wähle „Erlauben“.
:::

Steht Peek nicht in der Liste, füge `https://github.com/plhery/peek-ha` über seinen Link hinzu: im Marketplace mit „Add from link“, der dich beim ersten Mal bittet, GitHub zu verbinden, oder in HACS als benutzerdefiniertes Repository vom Typ „Integration“.

Du kannst sie auch von Hand installieren: Kopiere den Ordner `custom_components/peek` in den Ordner `custom_components` deiner Konfiguration, starte neu und füge die Integration wie oben hinzu.

## Wie füge ich die Karte Peek parcels zu meinem Dashboard hinzu?

Die Integration lädt ihre Karte selbst auf jedem Dashboard: Du musst keine Ressource hinzufügen. Eine Seite, die beim Einrichten von Peek schon offen war, hat die Karte noch nicht: Lade sie zuerst neu, oder schließe die Home-Assistant-App und öffne sie wieder.

:::steps
- Bearbeite ein Dashboard | Öffne es und wähle den Stift. Bei einem Dashboard, das Home Assistant für dich angelegt hat, bittet es dich zuerst, die Kontrolle zu übernehmen.
- Wähle „Add card“ | Dann den Tab „By card“. Der Tab „By entity“ sucht Entitäten, keine Karten.
- Wähle „Peek parcels“ | Suche nach „Peek“, wähle „Peek parcels“ und speichere.
:::

Die Karte gibt es in zwei Stilen: als Liste, die Standardansicht, mit einer Zeile pro Paket, die den Lieferwagen seines Paketdiensts, seinen Status und seinen Tag zeigt; oder als eine Karte pro Paket in den Farben seines Paketdiensts, wie Peeks Widgets für iPhone und Android, mit Pip, dem Ort des letzten Scans, dem Namen des Pakets, seinem Status und seinem Tag. Beide folgen Peeks Abschnitten, beginnend mit den Paketen, die dich brauchen, und enden mit „+N more“, wenn es mehr Pakete als Zeilen gibt.

Die Karte folgt deinem Design, hell oder dunkel, und deiner Sprache, wenn es Englisch oder Französisch ist. In YAML ist sie `type: custom:peek-parcels-card`; das README der Integration listet ihre Optionen auf.

## Kann ich sie in Automatisierungen nutzen oder Assist fragen?

Ja. Das README hat vier fertige Automatisierungen:

- eine Mitteilung auf dem Handy, wenn ein Paket in Zustellung geht;
- die Sendungsnummern aus deinen Versandmails hinzufügen, gelesen mit der IMAP-Integration von Home Assistant;
- eine Nachricht am Morgen mit dem, was heute ankommt;
- jeden Abend archivieren, was zugestellt wurde.

Wenn du Pakete aus einem Text hinzufügst, fügt Peek jede Sendungsnummer hinzu, die es findet, höchstens 10 auf einmal, und speichert nur die Nummern und den Namen, den du angibst. Schick also möglichst nur den Versandteil einer E-Mail.

Sobald eine der Entitäten von Peek für Assist freigegeben ist („Settings › Voice assistants › Expose“), kannst du fragen „Was kommt heute an?“ oder wo dein Paket von einem Shop ist. Das Peek-Werkzeug von Assist liest nur: Es fügt nie Pakete hinzu, ändert und archiviert keine. Was es zurückgibt (Namen, Shops, Paketdienste, Status, erwartete Tage und den Ort des letzten Scans), geht an den Konversationsagenten, den du gewählt hast, und das kann ein Cloud-Dienst sein.

## Was kann die Integration nicht?

- **Pakete löschen.** Home Assistant kann deine Pakete sehen, hinzufügen, ändern und archivieren, aber nie löschen. Hast du Peek vor Version 0.2.0 der Integration verbunden, bittet dich die erste Änderung, dich noch einmal bei Peek anzumelden, nur dieses eine Mal.
- **Claude, ChatGPT oder Gemini verbinden.** Die verbinden sich selbst mit Peek: siehe den Ratgeber [Pakete mit ChatGPT, Claude oder Gemini verfolgen](guide:ai-assistants).

## Was speichert Home Assistant, und wie entferne ich die Integration?

Home Assistant spricht nur mit Peek, unter peektracker.com, und speichert die Tokens der Anmeldung wie jede Integration in seiner Konfiguration. Sein Verlauf zeichnet die Zahlen der Sensoren und die Ereignisse „Parcel updates“ auf, mit Name, Paketdienst, Status und letztem Ort des Pakets, aber nicht die Paketlisten der Sensoren. Die Karte läuft in deinem Browser mit den Daten von Home Assistant selbst und lädt nichts von anderswo.

Zum Entfernen löschst du Peek unter „Settings › Devices & services“: Home Assistant gibt seinen Zugang an Peek zurück, Peek vergisst die Verbindung, und deine Pakete bleiben in Peek. Ist Peek dann nicht erreichbar, oder willst du Home Assistant von Peeks Seite aus trennen, wähle neben Home Assistant „Trennen“, auf Peeks Website (nicht in den Apps) unter „Einstellungen“ → „Sendungen“ → „Assistenten“. Eine Integration, die du danach behältst, meldet „Authentication expired for Peek“; wenn du sie neu konfigurierst, startet dieselbe Anmeldung.

:::sources
- [Peek für Home Assistant: README (auf Englisch)](https://github.com/plhery/peek-ha) – was es hinzufügt, Aktionen, Versionen, Installation, Anmeldung, die Karte, Automatisierungen, Assist, Datenschutz, Entfernen
- [Peek für Home Assistant: hacs.json](https://github.com/plhery/peek-ha/blob/main/hacs.json) – die älteste Version von Home Assistant, auf der es sich installieren lässt, 2026.10
- [Home Assistant: Marketplace (auf Englisch)](https://www.home-assistant.io/integrations/marketplace/) – Settings › Marketplace, Suche, Add from link, GitHub verbinden, Install, nur für Administratoren, ersetzt HACS ab 2026.11
- [HACS: Standardliste der Integrationen](https://github.com/hacs/default/blob/master/integration) – Peeks Eintrag, plhery/peek-ha, im Katalog, den HACS und der Marketplace durchsuchen
- [Home Assistant auf GitHub: Katalog des Marketplace](https://github.com/home-assistant/core/blob/dev/homeassistant/components/marketplace/const.py) – der Marketplace nutzt die Standardliste von HACS, hacs/default
- [Peek: Peek mit Claude, ChatGPT oder Gemini verbinden](https://peektracker.com/de/assistants) – die Schritte für Home Assistant auf Peeks Seite, 2026.11 oder neuer
- [Peek auf GitHub: Einrichtung der einzelnen Assistenten](https://github.com/plhery/peek-delivery-tracker/blob/main/src/assistants/setups.tsx) – die Menünamen von Home Assistant
- [Peek auf GitHub: deutsche Texte](https://github.com/plhery/peek-delivery-tracker/blob/main/shared/locales/de.json) – Version, Schritte, Konto, Beta, was Home Assistant darf
- [Peek auf GitHub: Einstellungen, Assistenten](https://github.com/plhery/peek-delivery-tracker/blob/main/src/components/Assistants.tsx) – verbundene Assistenten, Trennen
- [Peek: Hilfe (auf Englisch)](https://peektracker.com/support.html) – Anmeldung mit einem sechsstelligen Code
- [Peek auf GitHub: Nutzungsbedingungen (auf Englisch)](https://github.com/plhery/peek-delivery-tracker/blob/main/TERMS.md) – kostenlos, keine Gebühren, kein Abo
:::
