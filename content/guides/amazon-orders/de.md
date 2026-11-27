---
title: Amazon-Sendungsverfolgung: So findest du die Sendungsnummer
description: Amazon-Sendungsnummer finden unter „Meine Bestellungen“ und „Sendung verfolgen“: Paketdienst erkennen, Amazon Logistics verstehen und Pakete in Peek verfolgen.
slug: amazon-sendungsverfolgung
picture: Ein Handy zeigt den Fortschritt einer Bestellung, zwei Lieferwagen reichen sich ein Paket weiter, und Pip schaut zu.
published: 2026-10-16
updated: 2026-11-27
---

Die Amazon-Sendungsnummer steht nicht in der Bestell-E-Mail, sondern in deinem Konto unter „Meine Bestellungen“ und dann „Sendung verfolgen“ – direkt unter dem Namen des Paketdiensts. Die 17-stellige Bestellnummer funktioniert dort nicht. Kopier die Sendungsnummer einfach in [Peek](/), um dein Paket neben allen anderen zu verfolgen; wenn Amazon selbst mit Amazon Logistics liefert, erkennst du es an der eigenen Nummer mit zwei Länderbuchstaben und zehn Ziffern.

## Wo finde ich die Sendungsnummer bei Amazon?

:::steps
- Öffne „Meine Bestellungen“ | Auf der Website findest du sie oben rechts. In der Amazon-App tippst du auf das Personensymbol, dann auf „Meine Bestellungen“.
- Wähle „Sendung verfolgen“ | Der Button steht neben jedem Paket, das unterwegs ist oder gerade zugestellt wurde. Kommt eine Bestellung in mehreren Paketen, hat jedes seinen eigenen.
- Kopier die Sendungsnummer | Fett steht dort der Paketdienst, zum Beispiel DHL, und gleich darunter seine Sendungsnummer.
- Füg sie in Peek ein | Peek erkennt den Paketdienst und verfolgt das Paket.
:::

:::picture amazon-orders/orders-de.webp 480x300
„Meine Bestellungen“ auf Amazon: eine Bestellung, daneben der hervorgehobene Button „Sendung verfolgen“. Eine Beispielbestellung, keine echte.
:::

:::picture amazon-orders/track-de.webp 480x300
Amazons Seite „Sendung verfolgen“: der Paketdienst DHL und gleich darunter die Sendungsnummer, beide hervorgehoben. Beispielwerte.
:::

Auf anderen Amazon-Seiten heißen dieselben Buttons so:

| Seite | Meine Bestellungen | Sendung verfolgen |
| --- | --- | --- |
| amazon.com, amazon.co.uk | Your Orders | Track package |
| amazon.fr | Vos commandes | Suivre le colis |
| amazon.it | I miei ordini | Traccia il mio pacco |
| amazon.es | Mis pedidos | Localiza tu paquete |
| amazon.pl | Moje zamówienia | Śledź przesyłkę |

### Warum sehe ich keine Sendungsnummer bei Amazon?

- **Eine neue Bestellung.** Die Nummer erscheint meist, sobald der Paketdienst das Paket hat.
- **Amazon liefert selbst.** Dann zeigt die Bestellung vielleicht gar keine Nummer oder Amazons eigene: zwei Buchstaben für das Land und zehn Ziffern, etwa `DE1234567890`. Füg sie trotzdem in Peek ein: Peek verfolgt sie, wenn Amazons öffentliche Sendungsverfolgung sie kennt, und sagt dir, wenn nur dein Amazon-Konto sie zeigt.
- **Die Bestellnummer,** drei Zifferngruppen wie `123-1234567-1234567`, ist nie eine Sendungsnummer.

## Wie finden Claude oder ChatGPT meine Amazon-Sendungsnummern?

Ja, an einem Computer: Der Assistent muss den Browser steuern, in dem du bei Amazon angemeldet bist. Claude macht das mit Claude in Chrome, mit einem bezahlten Claude-Tarif; ChatGPT aus einem Work-Chat in seiner Desktop-App, mit seiner Browser-Erweiterung. Auf dem Handy oder in einem Chat, der deine offenen Tabs nur lesen kann oder von seinen eigenen Servern aus surft, kommt der Assistent nicht an deine Bestellungen, und in den E-Mails von Amazon steht die Sendungsnummer nicht.

### Mit Peek im Assistenten

Verbinde Peek einmal. In Claude fügst du „Peek Parcel Tracker“ aus dem [Konnektor-Verzeichnis von Claude](https://claude.ai/directory/peek-parcel-tracker) hinzu. In ChatGPT fügst du Peek als Plugin hinzu, mit einem Plus-, Pro-, Business-, Enterprise- oder Edu-Tarif. [Hier stehen alle Schritte](guide:ai-assistants).

Dann schreib:

> Importiere meine Amazon-Pakete in Peek.

Peek sagt dem Assistenten, wo Amazon die Nummern zeigt. Der Assistent fügt jedes Paket hinzu, benannt nach seinem Artikel, und sagt dir, welche Pakete Amazon nur in deinem Konto verfolgt.

### Ohne den Peek-Konnektor

Kopier das in den Assistenten:

> Importiere meine Amazon-Pakete in diesem Browser in Peek, wie https://peektracker.com/de/guides/amazon-sendungsverfolgung es zeigt: Kopiere die Sendungsnummer jedes Pakets aus „Meine Bestellungen“, dann „Sendung verfolgen“, und füg sie auf peektracker.com hinzu. Auf Amazon nur lesen: nichts kaufen, stornieren, zurückschicken oder ändern und mich nicht anmelden.

Melde dich in diesem Browser vorher bei Peek an: Ohne Konto fragt Peek nur ein paar Pakete am Tag ab.

So oder so schaut sich der Assistent Amazon nur an. Will Amazon, dass du dich anmeldest, mach das selbst in diesem Fenster und frag dann noch einmal.

Paket zu spät oder als zugestellt markiert, aber nicht da? Lies [zugestellt, aber nicht erhalten](guide:delivered-not-received) und [Paket verloren](guide:lost-parcel).

:::sources
- [Amazon.de-Hilfe: Sendung verfolgen](https://www.amazon.de/gp/help/customer/display.html?nodeId=GENAFPTNLHV7ZACW) – „Meine Bestellungen“, „Sendung verfolgen“, die Sendungsverfolgungsnummern; dieselbe Seite auf amazon.com, .co.uk, .fr, .it, .es und .pl nennt deren Wörter
- [Amazon.de-Hilfe: Ein Transportunternehmen kontaktieren](https://www.amazon.de/gp/help/customer/display.html?nodeId=GBPEXEXYULHB5WCH) – Name und Sendungsverfolgungsnummer unter „Sendung verfolgen“, ein Transportunternehmen je Paket
- [Amazon.de-Hilfe: Fehlende Details zum Sendungsverlauf](https://www.amazon.de/gp/help/customer/display.html?nodeId=G6ZFEB9ZDU7QHMER) – keine Nummer, solange das Paket auf dem Weg zum Paketdienst ist
- [Amazon.de-Hilfe: Lieferungen von Amazon Logistics](https://www.amazon.de/gp/help/customer/display.html?nodeId=GEW3XT9JEMBLTKRV) – Pakete, die Amazon selbst liefert, verfolgt unter „Meine Bestellungen“
- [Amazon.de-Hilfe: Ein Geschenk zurückgeben](https://www.amazon.de/gp/help/customer/display.html?nodeId=GMTDJ6XPUMN7NWNB) – die 17-stellige Bestellnummer
- [About Amazon: How to track your packages (auf Englisch)](https://www.aboutamazon.com/news/retail/track-amazon-package) – die Bestellungen oben rechts auf der Website, das Personensymbol in der App, Track package
- [Claude Help Center: Get started with Claude in Chrome (auf Englisch)](https://support.claude.com/en/articles/12012173-get-started-with-claude-in-chrome) – bezahlte Claude-Tarife, das Seitenfenster, Claude in Chrome unter den Konnektoren eines Chats
- [ChatGPT: Browser extension (auf Englisch)](https://learn.chatgpt.com/docs/chrome-extension) – Einrichtung über die ChatGPT-Desktop-App; liest und bedient Websites, bei denen du angemeldet bist, aus einem Work-Chat dort, und nutzt auch Plugins
- [ChatGPT: Browser (auf Englisch)](https://learn.chatgpt.com/codex/browser) – ChatGPT Work im Web und auf dem Handy surft in der Cloud, ohne deine Anmeldungen
- [Peek: Peek mit Claude, ChatGPT oder Gemini verbinden](https://peektracker.com/de/assistants) – Peeks Schritte für Claude und ChatGPT
- [Peek auf GitHub: die Tools für Assistenten](https://github.com/plhery/peek-delivery-tracker/blob/main/src/server/assistants/tools.ts) – was Peek einem Assistenten über Amazon sagt: Your Orders, Track package, „Delivery By“, „Tracking ID“, nur lesen
- [Peek auf GitHub: deutsche Texte](https://github.com/plhery/peek-delivery-tracker/blob/main/shared/locales/de.json) – ohne Konto ein paar Abfragen am Tag
- [Universal Parcel Scraper: Zustellerdaten](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers) – die Formen von Amazons eigenen Nummern
:::
