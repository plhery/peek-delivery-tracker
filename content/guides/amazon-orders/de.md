---
title: Amazon-Sendungsverfolgung: So findest du die Sendungsnummer
description: Wo Amazon Paketdienst und Sendungsnummer deines Pakets zeigt, auf der Website und in der App, und wie du es in Peek verfolgst: selbst oder mit Claude oder ChatGPT.
slug: amazon-sendungsverfolgung
picture: Ein Handy zeigt den Fortschritt einer Bestellung, zwei Lieferwagen reichen sich ein Paket weiter, und Pip schaut zu.
published: 2026-10-16
updated: 2026-11-20
---

Amazon zeigt Paketdienst und Sendungsnummer deines Pakets an einer Stelle: unter „Meine Bestellungen“, dann „Sendung verfolgen“. Kopier die Nummer in [Peek](/), und du verfolgst das Paket neben all deinen anderen. Das dauert eine Minute und geht auf jeder Amazon-Seite gleich. Die Ausnahme ist ein Paket, das Amazon selbst liefert: Das verfolgst du meist nur in deinem Amazon-Konto.

> Nutzt du Claude oder ChatGPT in Chrome? [Lass den Assistenten das erledigen](#konnen-claude-oder-chatgpt-meine-amazon-pakete-fur-mich-hinzufugen).

## Wo finde ich die Sendungsnummer bei Amazon?

Auf der Seite „Sendung verfolgen“ jedes Pakets. So kommst du hin:

:::steps
- Öffne „Meine Bestellungen“ | Auf der Website findest du sie oben rechts. In der Amazon-App tippst du unten in der Leiste auf das Kontosymbol, dann auf „Meine Bestellungen“.
- Such die Bestellung | Die neuesten stehen oben. Kommt eine Bestellung in mehreren Paketen, hat jedes Paket seinen eigenen Button „Sendung verfolgen“.
- Wähle „Sendung verfolgen“ | Auf der Website steht der Button neben der Bestellung. In der App tippst du zuerst auf den Artikel.
- Finde Paketdienst und Nummer | Die Seite nennt den Paketdienst, zum Beispiel DHL, und seine Sendungsnummer, bei Amazon „Sendungsverfolgungsnummer“. Siehst du sie nicht, lass dir alle Updates anzeigen: Dort steht die Nummer.
- Füg sie in Peek ein | Peek erkennt den Paketdienst an der Nummer und verfolgt das Paket.
:::

### Wann erscheint die Nummer?

Meist, sobald das Paket versendet ist. Bei einer neuen Bestellung kann sie noch fehlen. Laut Amazon hat das mehrere Gründe: Die Daten der Sendungsverfolgung kommen manchmal verspätet an, auf der ersten Strecke fährt vielleicht ein Zwischenzusteller ohne Sendungsverfolgung, und Verkäufer geben ihre Daten nicht immer an Amazon weiter. Manche Sendungen, etwa Großbriefe der Deutschen Post, lassen sich gar nicht verfolgen. Steht die Nummer schon da, aber noch ohne Updates, füg sie trotzdem ein: Peek zeigt das erste Update, sobald der Paketdienst eins hat.

Amazons E-Mails enthalten meist keine Sendungsnummer. Der Button zur Sendungsverfolgung in diesen Mails führt auf dieselbe Seite.

### Klappt das auch auf amazon.com, .fr, .it und anderen Amazon-Seiten?

Ja. Alle Amazon-Seiten funktionieren gleich, nur die Wörter wechseln mit der Sprache. amazon.de liefert auch nach Österreich und in die Schweiz. Aus der Schweiz kannst du auch auf amazon.fr oder amazon.it bestellen.

| Seite | Meine Bestellungen | Sendung verfolgen |
| --- | --- | --- |
| amazon.de | Meine Bestellungen | Sendung verfolgen |
| amazon.com, amazon.co.uk | Your Orders | Track package |
| amazon.fr | Vos commandes | Suivre le colis |
| amazon.it | I miei ordini | Traccia il mio pacco |
| amazon.es | Mis pedidos | Localiza tu paquete |
| amazon.pl | Moje zamówienia | Śledź przesyłkę |

## Welche Nummer ist die Sendungsnummer?

Bei einer Amazon-Bestellung können drei Arten von Nummern stehen. Nur mit der Sendungsnummer des Paketdienstes verfolgst du dein Paket immer auch außerhalb von Amazon.

- **Die Sendungsnummer des Paketdienstes.** Sie kommt vom Paketdienst: DHL, DPD, Hermes, UPS, der Österreichischen Post, der Schweizerischen Post und anderen. Amazon zeigt sie neben dem Namen des Paketdienstes. Peek verfolgt das Paket damit wie jedes andere.
- **Die Bestellnummer.** Drei Zifferngruppen mit Bindestrichen, etwa `123-1234567-1234567`. Sie steht in der Bestellung, auf dem Lieferschein und in Amazons E-Mails. Eine Sendungsnummer ist sie nie: Fügst du sie in Peek ein, sagt Peek „Das sieht nach einer Amazon-Bestellnummer aus“.
- **Eine Amazon-Nummer.** Amazon vergibt sie für Pakete, die sein eigenes Netz liefert, und Peek prüft sie bei Amazon: [siehe unten](#kann-peek-eine-amazon-tba-nummer-verfolgen). So sieht sie aus, hier mit erfundenen Ziffern:

:::anatomy TBA 123456789012
- TBA | Amazons Präfix. Auch `TBC` (Kanada) und `TBM` (Mexiko), oder `TBAU` mit 9 Ziffern (Australien)
- 123456789012 | Zwölf Ziffern
:::

In Europa kann eine Amazon-Nummer auch mit zwei Buchstaben für das Land beginnen, etwa `DE`, `AT` oder `CH`, gefolgt von 10 Ziffern. Unsicher, welche du hast? Tipp sie hier ein:

:::checker
:::

Ein Paket kann unterwegs den Paketdienst wechseln: Für die Schweiz übergibt Amazon Pakete an die Schweizerische Post, die sie über ihr eigenes Netz zustellt. „Sendung verfolgen“ nennt dann diesen Partner: Kopier seine Nummer.

## Kann Peek eine Amazon-TBA-Nummer verfolgen?

Manchmal. Fügst du eine Amazon-Nummer ein, fragt Peek zuerst Amazons öffentlichen Tracker:

- **Amazon kennt sie.** Dann liefert Amazon das Paket für ein anderes Unternehmen, mit [Amazon Shipping](carrier:amazon-shipping). Peek verfolgt es wie jedes andere.
- **Amazon kennt sie nicht.** Dann bringt höchstwahrscheinlich [Amazon Logistics](carrier:amazon-logistics) deine eigene Amazon-Bestellung, und die zeigt Amazon nur in deinem Konto. Peek fügt sie nicht hinzu, sagt dir das und bietet „Meine Amazon-Bestellungen öffnen“ an: Das öffnet deine Bestellungen auf der passenden Amazon-Seite. Peek fragt nie nach deinem Amazon-Passwort.
- **Amazon ist nicht erreichbar.** Peek sagt dir das und bietet „Erneut prüfen“ an.

Liefert Amazon ein Paket selbst, mit seinen lokalen und regionalen Lieferpartnern, zeigt die Bestellung vielleicht gar keine Sendungsnummer. Dann gibt es nichts zu kopieren: Nur Amazons eigene Seite „Sendung verfolgen“ verfolgt dieses Paket.

## Können Claude oder ChatGPT meine Amazon-Pakete für mich hinzufügen?

Ja, wenn der Assistent deinen Browser bedienen kann. Diese beiden können das:

- **Claude in Chrome,** Claudes Erweiterung für Chrome, mit einem bezahlten Claude-Tarif.
- **Die Chrome-Erweiterung von ChatGPT,** die du über die ChatGPT-Desktop-App einrichtest und nutzt.

Beide arbeiten in dem Chrome-Fenster, in dem du schon bei Amazon angemeldet bist. Bitte darum, und der Assistent öffnet „Meine Bestellungen“, geht jede Seite „Sendung verfolgen“ durch und notiert Paketdienst und Sendungsnummer.

Wie es weitergeht, hängt davon ab, ob Peek mit dem Assistenten verbunden ist:

- **Ist Peek verbunden** und kann der Assistent es in diesem Chat nutzen, fügt er die Pakete selbst deinem Peek-Konto hinzu, benannt nach dem Artikel. In Claude öffnest du „Peek Parcel Tracker“ im [Konnektor-Verzeichnis von Claude](https://claude.ai/directory/peek-parcel-tracker), wählst „Connect“, meldest dich bei Peek an und wählst „Erlauben“. In ChatGPT kannst du Peek mit einem Business-, Enterprise- oder Edu-Tarif als Plugin hinzufügen. Alle Schritte stehen in [Pakete mit ChatGPT oder Claude verfolgen](guide:ai-assistants).
- **Wenn nicht,** listet der Assistent die Nummern im Chat auf. Kopier die ganze Liste und füg sie in [Peek](/) ein: Peek findet jede Nummer darin. Angemeldet verfolgt es alle, abgemeldet eine nach der anderen.

Kopier das in den Assistenten:

> Öffne in diesem Browser „Meine Bestellungen“ auf Amazon. Öffne für jedes Paket, das unterwegs ist oder gerade zugestellt wurde, „Sendung verfolgen“ und notiere den Paketdienst und die Sendungsnummer. Nimm nie die Bestellnummer (drei Zifferngruppen mit Bindestrichen). Ist Peek verbunden, füge jedes Paket zu Peek hinzu, benannt nach seinem Artikel. Wenn nicht, liste die Nummern hier auf, eine pro Zeile. Sag mir, welche Pakete keine Sendungsnummer zeigen. Auf Amazon nur lesen: nichts kaufen, stornieren, zurückschicken oder ändern, und melde mich nicht an.

So oder so muss der Assistent auf Amazon nur lesen. Er soll nie etwas kaufen, stornieren oder zurückschicken und dich nie anmelden. Fragt Amazon nach einer Anmeldung, meldest du dich in diesem Fenster selbst an und bittest dann noch einmal. Hat ein Paket keine Sendungsnummer oder lehnt Peek sie ab, verfolgt Amazon dieses Paket nur in deinem Konto.

## Was tun, wenn mein Amazon-Paket zu spät ist oder als zugestellt gilt?

Wer dir hilft, hängt davon ab, wer versendet hat. Das zeigt die Produktseite unter dem Kaufen-Button. Amazon kümmert sich um seine eigenen Bestellungen und um die, die es für Verkäufer versendet („Versand durch Amazon“). Ein Verkäufer, der selbst versendet, beantwortet Fragen zur Lieferung zuerst, außer der Artikel war mit Prime bestellbar. Laut Amazon kommen die meisten verspäteten Pakete innerhalb von 48 Stunden nach dem voraussichtlichen Liefertermin an, und manches Paket wird schon vor der Ankunft als zugestellt gescannt.

:::steps
- Prüf „Sendung verfolgen“ | Dort steht der letzte Stand. Mit Lieferbenachrichtigungen meldet Amazon dir, sobald eine Live-Karte bereitsteht.
- Schau dich um | Prüf den Ablageort aus deinen Lieferanweisungen und das Zustellfoto, falls der Fahrer eins gemacht hat, und frag deine Nachbarn.
- Frag den Paketdienst | Hat ein Paketdienst wie DHL oder Hermes geliefert, nennt Amazons Hilfeseite „Ein Transportunternehmen kontaktieren“ seine Kontaktdaten.
- Schreib dem Verkäufer | Hat ein Verkäufer verkauft und versendet, kontaktier ihn über die Bestellung unter „Meine Bestellungen“. Verkäufer antworten meist innerhalb von 48 Stunden.
- Frag Amazon | Bei Bestellungen, die Amazon versendet hat, hilft der Kundenservice per Chat oder Rückruf, telefonisch täglich von 6 bis 24 Uhr. Bei einem Verkäufer mit eigenem Versand, der das Problem nicht löst, deckt die A-bis-z-Garantie die rechtzeitige Lieferung und den Zustand des Artikels ab.
:::

Mehr Hilfe: [zugestellt, aber nicht erhalten](guide:delivered-not-received), [Paket verloren](guide:lost-parcel) und [warum sich die Sendungsverfolgung nicht aktualisiert](guide:tracking-not-updating).

:::sources
- [Amazon.de-Hilfe: Sendung verfolgen](https://www.amazon.de/gp/help/customer/display.html?nodeId=GENAFPTNLHV7ZACW) – „Meine Bestellungen“, „Sendung verfolgen“ neben der Bestellung, alle Updates mit den Sendungsverfolgungsnummern, verspätete Pakete meist innerhalb von 48 Stunden; dieselbe Seite auf amazon.com, .co.uk, .fr, .it, .es und .pl nennt deren Wörter
- [Amazon.de-Hilfe: Ein Transportunternehmen kontaktieren](https://www.amazon.de/gp/help/customer/display.html?nodeId=GBPEXEXYULHB5WCH) – Name und Sendungsverfolgungsnummer unter „Sendung verfolgen“, ein Transportunternehmen je Paket, DHL, DPD, Hermes, UPS und die Österreichische Post für Deutschland und Österreich, ihre Kontaktdaten
- [Amazon.de-Hilfe: Fehlende Details zum Sendungsverlauf](https://www.amazon.de/gp/help/customer/display.html?nodeId=G6ZFEB9ZDU7QHMER) – verspätete Daten, Zwischenzusteller auf der ersten Strecke, Verkäufer ohne Sendungsdaten, Großbriefe der Deutschen Post
- [Amazon.de-Hilfe: Lieferungen von Amazon Logistics](https://www.amazon.de/gp/help/customer/display.html?nodeId=GEW3XT9JEMBLTKRV) – lokale und regionale Lieferpartner, Sendungsverfolgung unter „Meine Bestellungen“
- [Amazon.de-Hilfe: Ein Geschenk zurückgeben](https://www.amazon.de/gp/help/customer/display.html?nodeId=GMTDJ6XPUMN7NWNB) – die 17-stellige Bestellnummer auf dem Lieferschein
- [Amazon.de-Hilfe: Einen Marketplace-Verkäufer kontaktieren](https://www.amazon.de/gp/help/customer/display.html?nodeId=GLC8ZMBWMBTR6QZZ) – Verkäufer mit eigenem Versand kümmern sich um den Kundenservice, außer bei Prime-Artikeln; Kontakt über „Meine Bestellungen“, Antwort meist innerhalb von 48 Stunden
- [Amazon.de-Hilfe: Ein fehlendes Paket finden, das laut Sendungsverfolgung zugestellt wurde](https://www.amazon.de/gp/help/customer/display.html?nodeId=GCU8BWGTQNJKQEBS) – Ablageort, Zustellfoto, Nachbarn, schon vor der Ankunft als zugestellt gescannt, das Transportunternehmen fragen
- [About Amazon: How to track your packages (auf Englisch)](https://www.aboutamazon.com/news/retail/track-amazon-package) – die Bestellungen oben rechts auf der Website, das Personen-Symbol in der App, Track package
- [About Amazon Deutschland: Amazon-Lieferung: einfache Bestellung und Verfolgung](https://www.aboutamazon.de/news/amazon-prime-und-shopping/amazon-lieferoptionen-sendung-verfolgen) – Live-Karte mit Lieferbenachrichtigungen, Zustellfoto, Lieferanweisungen und Ablageort
- [About Amazon Deutschland: Wie bekomme ich Hilfe bei meiner Amazon Bestellung?](https://www.aboutamazon.de/news/amazon-prime-und-shopping/wie-bekomme-ich-hilfe-bei-meiner-amazon-bestellung) – Kontosymbol unten in der App, Chat, Rückruf, telefonisch täglich 6 bis 24 Uhr, A-bis-z-Garantie für rechtzeitige Lieferung und Zustand bei Artikeln, die Verkäufer selbst versenden
- [About Amazon Deutschland: Amazon A-bis-z-Garantie wird künftig auch Ansprüche bei Sach- und Personenschäden abdecken](https://www.aboutamazon.de/news/customer-trust/a-bis-z-garantie-sach-und-personenschaeden) – Erstattung über Amazon, wenn der Verkäufer das Problem nicht löst
- [About Amazon Deutschland: Steigern Sie Ihre Verkaufschancen mit Versand durch Amazon](https://www.aboutamazon.de/news/chancen/steigern-sie-ihre-verkaufschancen-mit-versand-durch-amazon) – „Versand durch Amazon“: Amazon versendet die Ware der Verkäufer und kümmert sich um den Kundendienst
- [About Amazon UK: How to change your delivery address on Amazon (auf Englisch)](https://www.aboutamazon.co.uk/news/retail/how-to-change-your-delivery-address-on-amazon) – wer verkauft und versendet, unter dem Kaufen-Button
- [About Amazon Deutschland: Amazon in der Schweiz: Alles, was du wissen musst!](https://www.aboutamazon.de/news/company-news/aus-der-schweiz-bei-amazon-bestellen-das-musst-du-wissen) – aus der Schweiz auf amazon.de, amazon.fr oder amazon.it bestellen
- [About Amazon Deutschland: Wie kommt mein Paket zu mir?](https://www.aboutamazon.de/news/verpackung/wie-kommt-mein-paket-zu-mir) – in der Schweiz stellt die Schweizerische Post über ihr eigenes Netz zu
- [About Amazon UK: Amazon CEO Andy Jassy's 2023 Letter to Shareholders (auf Englisch)](https://www.aboutamazon.co.uk/news/company-news/ceo-andy-jassys-2023-letter-to-shareholders) – Amazon Shipping, Amazons Zustellnetz für Pakete anderer Unternehmen
- [Claude Help Center: Get started with Claude in Chrome (auf Englisch)](https://support.claude.com/en/articles/12012173-get-started-with-claude-in-chrome) – bezahlte Claude-Tarife, nur Chrome, die Erweiterung installieren
- [Claude Help Center: Use Claude in Chrome safely (auf Englisch)](https://support.claude.com/en/articles/12902428-use-claude-in-chrome-safely) – du bleibst für Käufe verantwortlich; Berechtigungen je Website
- [ChatGPT: Chrome extension (auf Englisch)](https://learn.chatgpt.com/docs/chrome-extension) – Einrichtung über die ChatGPT-Desktop-App; arbeitet auf Websites, bei denen du angemeldet bist
- [Peek: Peek mit Claude, ChatGPT oder Gemini verbinden](https://peektracker.com/de/assistants) – Peeks Schritte für Claude und ChatGPT
- [Peek auf GitHub: die Tools für Assistenten](https://github.com/plhery/peek-delivery-tracker/blob/main/src/server/assistants/tools.ts) – was Peek einem Assistenten über Amazon sagt: „Meine Bestellungen“, „Sendung verfolgen“, keine Sendungsnummer in Amazons Mails, nur lesen
- [Peek auf GitHub: deutsche Texte](https://github.com/plhery/peek-delivery-tracker/blob/main/shared/locales/de.json) – der Hinweis zur Amazon-Bestellnummer, „Meine Amazon-Bestellungen öffnen“, „Erneut prüfen“, mehrere Nummern in einem Text
- [Universal Parcel Scraper: Zustellerdaten](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers) – Formen der Amazon-Nummern und was Peek prüft
:::
