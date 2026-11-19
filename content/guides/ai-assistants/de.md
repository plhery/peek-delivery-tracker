---
title: Pakete mit ChatGPT, Claude oder Gemini verfolgen
description: Verbinde Peek mit ChatGPT, Claude oder Gemini und frag, wo deine Pakete sind, oder lass die Sendungsnummern aus deinen Mails hinzufügen. Wer es kann und wie es geht.
slug: pakete-verfolgen-chatgpt-claude-gemini
picture: Pip, das Paket mit Gesicht, lehnt sich aus einem Chatfenster auf einem Laptop, in dem eine Frage zu den Paketen dieser Woche mit einer kurzen Liste beantwortet wird.
published: 2026-11-19
updated: 2026-11-19
---

Du kannst deine Pakete in Claude, ChatGPT oder Gemini verfolgen, wenn du sie mit [Peek](/) verbindest, einem kostenlosen Paket-Tracker: Der Assistent kann dir dann sagen, wo deine Pakete sind, und die Sendungsnummern hinzufügen, die du ihm gibst. Du brauchst ein Peek-Konto, und wer es nutzen kann, legt jeder Assistent selbst fest: Claude mit jedem Tarif, ChatGPT mit einem Business-, Enterprise- oder Edu-Tarif und Gemini für Erwachsene in den USA mit einem privaten Google-Konto. Peek kennzeichnet die Funktion mit „Beta“, sie kann sich also noch ändern.

## Können ChatGPT, Claude oder Gemini meine Pakete verfolgen?

Ja, sobald du sie mit Peek verbindest. Jeder Assistent legt eigene Bedingungen fest:

| Assistent | Wo es funktioniert | Wer Peek hinzufügen kann |
| --- | --- | --- |
| Claude | claude.ai und die Claude-Apps für Desktop, iPhone und Android | Jeder Tarif. Mit einem Team- oder Enterprise-Tarif fügt zuerst ein Inhaber Peek hinzu |
| ChatGPT | chatgpt.com | Ein Business-, Enterprise- oder Edu-Tarif. In einem Workspace muss ein Admin Peek eventuell zuerst hinzufügen oder erlauben |
| Gemini | Verbunden auf gemini.google.com, danach auch in der Gemini-App | Ab 18, in den USA, mit privatem Google-Konto und eingeschalteter Einstellung „History“, auf Englisch |
| Ein anderer Assistent | Jeder, bei dem du einen benutzerdefinierten Konnektor oder MCP-Server hinzufügen kannst | Wer dort einen hinzufügen darf |

Auf Peeks Seite kann jedes Konto einen Assistenten verbinden (ein Gast nicht), und Peek ist kostenlos.

Home Assistant wird stattdessen über Peeks eigene Integration verbunden: siehe den Ratgeber [Pakete in Home Assistant verfolgen](guide:home-assistant).

## Wie füge ich Peek zu Claude, ChatGPT oder Gemini hinzu?

Du fügst Peek im Assistenten hinzu, mit der Peek-Adresse für Assistenten: `https://peektracker.com/mcp`. Die Schritte unten sind [Peeks eigene](https://peektracker.com/de/assistants); wenn du auf Peeks Website angemeldet bist (nicht in den Apps), stehen sie auch unter „Einstellungen“ → „Sendungen“ → „Assistenten“, mit der Schaltfläche „Adresse kopieren“.

### Claude

:::steps
- Öffne „Peek Parcel Tracker“ | Im [Konnektor-Verzeichnis von Claude](https://claude.ai/directory/peek-parcel-tracker) oder in Claude: Öffne „Customize › Connectors“, wähle „+“ und suche nach Peek.
- Wähle „Connect“ | Claude bittet dich dann, dich bei Peek anzumelden.
- Melde dich bei Peek an | Wähle aus, was Claude tun darf, und dann „Erlauben“.
- Schalte Peek in einem Chat ein | Wähle unten links „+“, dann „Connectors“, und schalte Peek ein.
:::

Mit einem Team- oder Enterprise-Tarif fügt zuerst ein Inhaber Peek hinzu: „Organization settings › Connectors“, „Browse connectors“, dann „Add to your team“.

Zeigt Claude „Peek Parcel Tracker“ nicht an, füge Peek als benutzerdefinierten Konnektor auf claude.ai oder in der Desktop-App hinzu (der Free-Tarif erlaubt einen):

:::steps
- Öffne „Customize › Connectors“ | Wähle „+ Add“, dann „Add custom connector“.
- Nenne ihn Peek | Füge `https://peektracker.com/mcp` ein und wähle „Continue“.
- Behalte die Anmeldeeinstellungen, die Claude findet | Wähle „Continue“, dann „Add“.
:::

### ChatGPT

:::steps
- Geh zu „chatgpt.com/plugins“ | Wähle „+“, dann „Add custom MCP server“.
- Nenne ihn Peek | Füge `https://peektracker.com/mcp` ein und wähle „OAuth“ zur Authentifizierung. Wähle „I understand and want to continue“, dann „Create as a plugin“.
- Melde dich bei Peek an | Wähle aus, was ChatGPT tun darf, und dann „Erlauben“.
- Installiere es | Öffne Peek in deinen Plugins und wähle „+“. Tippe dann in einem neuen Chat @ und wähle Peek.
:::

### Gemini

Laut Googles Hilfeseite musst du, um eine benutzerdefinierte App wie Peek zu verbinden, mindestens 18 Jahre alt sein und dich in den USA befinden, ein privates Google-Konto nutzen, kein Arbeits- oder Schulkonto, und die Einstellung „History“ eingeschaltet haben. Du verbindest sie auf gemini.google.com, danach funktioniert sie auch in der Gemini-App fürs Handy, nur auf Englisch. Einen kostenpflichtigen Tarif nennt Google nicht. Googles Seite und die von Peek sagen beide: Die Adresse von Peek kommt in Gemini unter „Settings“, dann „Connected Apps“, in den Bereich „Custom apps“.

## Was kann ich den Assistenten fragen?

Frag in deinen eigenen Worten, zum Beispiel:

- „Sieh meine E-Mails durch und füge die Sendungsnummern hinzu, die du findest.“ Das klappt, wenn der Assistent deine Mails selbst lesen kann, zum Beispiel über seinen eigenen Gmail-Konnektor: Peek liest nie deine E-Mails.
- „Was kommt diese Woche an?“
- „Wo ist mein Paket von IKEA?“
- „Archiviere alles, was zugestellt wurde.“
- „Welches Paket wurde bei mir zuletzt zugestellt?“

Er kann auch die Pakete heraussuchen, die dich brauchen, etwa eines, das an einer Abholstelle wartet, und die, die heute erwartet werden, in deiner Zeitzone.

Um Pakete hinzuzufügen, gib ihm eine Sendungsnummer oder den Versandteil einer E-Mail: Peek verfolgt jede Sendungsnummer, die es darin findet, bis zu 10 auf einmal, und speichert nur die Nummern und den angegebenen Namen, nie den Text. Eine Bestellnummer ist keine Sendungsnummer: [So unterscheidest du sie](guide:find-tracking-number).

### Amazon-Bestellungen

Amazons E-Mails enthalten meist keine Sendungsnummer. Ein Assistent, der die Amazon-Seite aufrufen kann, auf der du angemeldet bist, wird angewiesen, „Meine Bestellungen“ zu öffnen, dann „Track package“, und den Paketdienst und die „Tracking ID“ zu schicken, nie die Bestellnummer. Er darf dort nur lesen: nie etwas kaufen, stornieren, zurückschicken oder ändern und sich nie für dich anmelden. Sonst such die Nummer selbst: [Unser Amazon-Ratgeber](guide:amazon-orders) zeigt, wo sie steht. Ein Paket, das Amazon selbst zustellt und für das es keine Sendungsnummer gibt, verfolgst du nur in deinem Amazon-Konto.

## Kann der Assistent meine Pakete löschen oder ändern?

Löschen kann er nie ein Paket: Das machst du in Peek. Den Rest bestimmst du: Wenn du ihn verbindest, zeigt Peeks Bildschirm (zum Beispiel „Claude mit Peek verbinden?“) deine E-Mail-Adresse und ein Kästchen für jede Berechtigung, um die der Assistent bittet:

| Berechtigung auf Peeks Bildschirm | Was der Assistent dann tun kann |
| --- | --- |
| „Deine Pakete sehen: wo jedes gerade ist, wann es erwartet wird und seinen Verlauf“ | Deine Pakete auflisten und eines mit seinen Scans öffnen |
| „Pakete aus den Sendungsnummern hinzufügen, die du ihm gibst, und sie archivieren“ | Pakete hinzufügen und eines archivieren |
| „Name, Paketdienst oder Mitteilungen deiner Pakete ändern und archivierte wiederherstellen“ | Ein Paket umbenennen, seinen Paketdienst ändern, seine Mitteilungen oder Peeks E-Mails aus- oder einschalten und ein archiviertes wiederherstellen |

Nimm den Haken bei allem heraus, was du nicht willst; „Erlauben“ bleibt ausgegraut, bis mindestens ein Kästchen angehakt ist. Bittest du später um etwas, das du nicht erlaubt hast, wird der Assistent angewiesen, Peek mit dieser Berechtigung neu zu verbinden.

## Was sieht der Assistent, und wie trenne ich ihn?

Er bekommt, was du erlaubt hast: Namen, Absender, Paketdienste, Sendungsnummern, Status, erwartete Tage, Abholstellen und Scans deiner Pakete. Nie den Namen, die Postleitzahl oder Telefonziffern des Empfängers: Ein Scan, der den Empfänger, die unterschreibende Person, einen Code oder eine Tür nennt, erreicht ihn nur als Status, etwa „Zugestellt“. Danach gelten die Datenschutzbestimmungen seines Anbieters. In Peeks [Datenschutzerklärung](https://peektracker.com/privacy.html) steht, was Peek über jeden Assistenten speichert, den du verbindest.

Um einen Assistenten zu trennen, auf Peeks Website:

:::steps
- Öffne „Einstellungen“ | Über den runden Knopf oben mit dem ersten Buchstaben deiner E-Mail-Adresse.
- Wähle „Assistenten“ | Unter „Sendungen“. Bei jedem Assistenten steht, was er darf und wann er zuletzt genutzt wurde.
- Wähle „Trennen“ | Neben dem Assistenten.
:::

Du kannst Peek auch in den Einstellungen des Assistenten entfernen. Eine Verbindung, die 60 Tage nicht genutzt wird, endet von selbst, und wenn du dein Peek-Konto löschst, werden auch seine verbundenen Assistenten gelöscht.

## Warum kann ich Peek nicht zu meinem Assistenten hinzufügen?

Meist liegt es am Tarif, am Land oder am Konto:

- **Dein Tarif oder dein Land.** Sieh in der Tabelle oben nach, und frag bei einem Team- oder Workspace-Tarif dessen Inhaber oder Admin.
- **Kein Peek-Konto.** Melde dich an, wenn der Assistent danach fragt, mit einem sechsstelligen Code, den Peek dir mailt, oder mit Google oder Apple, wo der Bildschirm das anbietet. Bei deiner ersten Anmeldung wird das Konto angelegt.
- **Peek will, dass du dich erneut anmeldest.** Um einen Assistenten zu erlauben, braucht Peek eine frische Anmeldung, auch wenn du schon angemeldet bist: Melde dich an und wähle dann „Erlauben“.
- **„Dieser Link kann keinen Assistenten verbinden“.** Starte die Verbindung in deinem Assistenten noch einmal.

Weitere Fälle stehen unter „Wenn etwas nicht klappt“ auf [Peeks Seite zu Assistenten](https://peektracker.com/de/assistants), und auf Peeks [Hilfeseite](https://peektracker.com/support.html) steht, wie du Peek erreichst.

:::sources
- [Peek: Peek mit Claude, ChatGPT oder Gemini verbinden](https://peektracker.com/de/assistants) – wer es nutzen kann, die Schritte für jeden Assistenten, was du fragen kannst, was Peek teilt, Hilfe bei Problemen
- [Peek auf GitHub: Einrichtung der einzelnen Assistenten](https://github.com/plhery/peek-delivery-tracker/blob/main/src/assistants/setups.tsx) – Schritte und Menünamen, Anmeldung vor dem letzten Schritt
- [Peek auf GitHub: deutsche Texte](https://github.com/plhery/peek-delivery-tracker/blob/main/shared/locales/de.json) – Tarife und Orte, Berechtigungen, Beta, Hilfe bei Problemen
- [Peek auf GitHub: die Werkzeuge der Assistenten](https://github.com/plhery/peek-delivery-tracker/blob/main/src/server/assistants/tools.ts) – was ein Assistent auflisten, hinzufügen und bearbeiten kann, kein Löschen, Amazon
- [Peek auf GitHub: der Zustimmungsbildschirm](https://github.com/plhery/peek-delivery-tracker/blob/main/src/components/AssistantConsentScreen.tsx) – Kästchen, Erlauben, frische Anmeldung
- [Peek auf GitHub: Einstellungen, Assistenten](https://github.com/plhery/peek-delivery-tracker/blob/main/src/components/Assistants.tsx) – Adresse, verbundene Assistenten, Trennen
- [Peek: Datenschutzerklärung (auf Englisch)](https://peektracker.com/privacy.html) – was ein Assistent bekommt, was Peek speichert, 60 Tage, Löschen des Kontos
- [Peek auf GitHub: Nutzungsbedingungen (auf Englisch)](https://github.com/plhery/peek-delivery-tracker/blob/main/TERMS.md) – kostenlos, keine Gebühren, kein Abo
- [Peek: Hilfe (auf Englisch)](https://peektracker.com/support.html) – Anmeldung mit einem sechsstelligen Code
- [Claude-Hilfe: Erste Schritte mit benutzerdefinierten Connectoren mit Remote MCP](https://support.claude.com/de/articles/11175166-erste-schritte-mit-benutzerdefinierten-connectoren-mit-remote-mcp) – Schritte für einen benutzerdefinierten Konnektor, einer im Free-Tarif
- [Gemini-Apps-Hilfe: Connect & manage custom apps for Gemini Apps (auf Englisch)](https://support.google.com/gemini/answer/17209137?hl=en&co=GENIE.Platform%3DDesktop) – wer eine benutzerdefinierte App verbinden kann, History eingeschaltet, Englisch, Verbinden im Web
:::
