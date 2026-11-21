---
title: WanbExpress Sendungsverfolgung: WNB…YQ-Nummern und Status
description: WanbExpress-Paket (Wanb Express) aus China verfolgen: Aufbau der WNB…YQ-Nummer, Bedeutung der englischen Status, Zusteller vor Ort und Hilfe bei Problemen.
slug: wanbexpress-sendungsverfolgung
published: 2026-11-21
updated: 2026-11-21
---

WanbExpress (auch Wanb Express, WanBang Express oder WANB) ist ein chinesischer Paketdienstleister, der Sendungen ins Ausland befördert, wo ein lokaler Zusteller das Paket ausliefert. Hat ein Shop deine Bestellung mit WanbExpress verschickt, beginnt die Nummer mit `WNB` und endet auf `YQ`: Füge sie oben in das Feld ein. Der eigene Tracker von WanbExpress unter tracking.wanbexpress.com kann zusätzlich den Zusteller in deinem Land und dessen Sendungsnummer nennen.

## WanbExpress-Sendungsnummer: So sieht sie aus

Sendungsnummern von WanbExpress bestehen aus `WNB`, zwei Buchstaben, zehn Ziffern und `YQ`, also insgesamt 17 Zeichen. Erfundene Beispiele:

| Nummer (erfunden) | Was es ist |
| --- | --- |
| `WNBKG4829105736YQ` | `WNB`, zwei Buchstaben, zehn Ziffern und `YQ`: eine WanbExpress-Nummer. |
| `WNBKG482910573YQ` | Nur neun Ziffern: passt nicht zum Format von WanbExpress. Prüfe auf eine fehlende Ziffer. |
| `JSEKG4829105736YQ` | Endet ebenfalls auf `YQ`, beginnt aber mit `JSE`: eine Nummer von [JS Express](carrier:js-express). |
| `JXCKG4829105736YQ` | `JXC` am Anfang: eine Nummer von [Juxi Express](carrier:juxi-express). |

Mehrere chinesische Dienstleister beenden ihre Nummern auf `YQ`, jeweils mit eigenen Anfangsbuchstaben: `WNB` steht für WanbExpress. Leerzeichen, Bindestriche und Kleinschreibung spielen beim Einfügen in Peek keine Rolle.

**Wo du sie findest.** Der Shop schickt die Nummer in der Versandmail oder zeigt sie auf der Bestellseite an. Findest du sie nicht, hilft [Sendungsnummer finden](guide:find-tracking-number).

**Ein Tracking-Link.** Ein Link zum WanbExpress-Tracker, der auf `?trackingNumbers=` und eine Nummer endet, öffnet genau dieses Paket dort. Die `WNB`-Nummer steht hinter dem Gleichheitszeichen: Kopiere sie von dort in Peek.

**Eine zweite Nummer.** Der Tracker von WanbExpress kann auch die Nummer anzeigen, die der ausliefernde Paketdienst verwendet. Diese Nummer gehört zu dem anderen Dienstleister: Verfolge sie dort oder füge sie als eigenes Paket in Peek hinzu.

## WanbExpress-Statusmeldungen erklärt

Die Scans von WanbExpress erscheinen auf Englisch, jeweils mit Uhrzeit und manchmal einem Ort. Hier sind die im Peek-Code hinterlegten Meldungen in typischer Reihenfolge:

| Meldung von WanbExpress | Was das für dich bedeutet | Peek zeigt |
| --- | --- | --- |
| `Shipping Order Created.`, `Parcel Data Received` | Der Shop hat die Sendung angemeldet und Daten übermittelt. WanbExpress hat das Paket noch nicht. | „Etikett erstellt“ |
| `Parcel received at Shenzhen Sorting Centre.` | Das Sortierzentrum in Shenzhen, China, hat das Paket erhalten: erster physischer Scan. | „Beim Paketdienst eingegangen“ |
| `Parcel processed at Shenzhen Sorting Centre.`, `Parcel departure in Shenzhen Sorting Centre.` | Dort sortiert und anschließend weiterbefördert. | „Unterwegs“ |
| `A-Scan: shipment in international transit` | Weitergeleiteter Eingangsscan zwischen Bearbeitung im Sortierzentrum und Abgang. | „Unterwegs“ |
| `Customs declaration completed` | Eine Zollanmeldung wurde abgeschlossen. Die Meldung nennt kein Land und ist noch nicht die Einfuhrfreigabe – diese lautet `Custom clearance completed`. | „Unterwegs“ |
| `Customs inspection has not yet released` | Ausfuhrkontrolle im Abgangsland nach der Anmeldung und vor dem Abflug. | „Unterwegs“ |
| `Parcel departure in Transit Facility, Parcel Departure` | Das Paket hat eine Transitanlage verlassen. | „Unterwegs“ |
| `Parcel is leaving Airport, Parcel Leaving Port` | Abflug in Richtung Zielland. | „Unterwegs“ |
| `Parcel arrives at Airport, Parcel Arrival` | Im Zielland oder in der Nähe gelandet. Danach folgt der Zoll. | „Unterwegs“ |
| `Custom clearance completed` | Der Einfuhrzoll hat das Paket freigegeben. | „Unterwegs“ |
| `Parcel received by in JFK01.` | Von WanbExpress als Eingang für die letzte Meile verbucht, ohne den Empfängerbetrieb zu nennen. | „Unterwegs“ |
| `Your package is being prepared by the sender and will be handed over to us soon`, `Your parcel will soon be handed over to us! It is being prepared by the sender.`, `Order information received. We're expecting your parcel to arrive with us.` | Meldung eines Partners, ohne eigenen WanbExpress-Code weitergereicht: Das Paket wurde ihm noch nicht übergeben. | „Etikett erstellt“, oder ein späterer Status, falls das Paket schon weiter ist |
| `In transit along Poste Italiane’s logistic network` | Im Netz des ausliefernden Paketdienstes unterwegs, hier [Poste Italiane](carrier:poste-italiane). | „Unterwegs“ |
| `Parcel is out for delivery.` | Auf der Zustelltour. | „In Zustellung“ |
| `Consignee absent, Consignee could not be found.` | Der Zusteller hat niemanden angetroffen. | „Zustellversuch“ |
| `Placed in Parcelshop/Parcel locker` | Nach dem erfolglosen Versuch wurde das Paket in einem Paketshop oder einer Paketstation hinterlegt. | „Abholbereit“ |
| `Parcel has been delivered.(Front door)` | Zugestellt. Der Zusatz `(Front door)` nennt den Ablageort. | „Zugestellt“ |

**Scans des Zustellers vor Ort.** Nach dem Flug reicht der Tracker von WanbExpress die Scans des lokalen Zustellers in dessen eigenen Worten weiter, meist ohne WanbExpress-Code. Bei einer Sendung nach Deutschland erscheint im Verlauf [iMile](carrier:imile) als Zusteller, jeweils nach dem Namen der Station in `【】`-Klammern:

| Meldung von iMile | Was das für dich bedeutet | Peek zeigt |
| --- | --- | --- |
| `Received.` | Das iMile-Zentrum im Zielland hat das Paket erhalten. | „Unterwegs“ |
| `Arrived.` | In der Zustellbasis für dein Gebiet eingetroffen. | „Unterwegs“ |
| `Dispatch task assigned to DA.` | Ein Fahrer (DA, Delivery Associate) wurde für die Tour eingeteilt, oft schon Stunden vor dem Start. | „Unterwegs“ |
| `Shipment out for delivery.` | Auf der Zustelltour. | „In Zustellung“ |
| `<Route not completed>`, `Back To DS.` | Die Tour endete ohne Zustellung, das Paket ging zurück an die Zustellbasis (DS). | „Zustellversuch“ (allein nach der Tour; nach einem bereits gemeldeten Versuch bleibt `Back To DS.` ein Hinweis, nach „Unterwegs“ bleibt es „Unterwegs“) |
| `Scheduled to <2026-11-02> .` | Geplanter Tag für den nächsten Versuch nach Rückkehr zur Basis. | Weiterhin „Zustellversuch“ |

Schritte anderer Partner erscheinen auf dieselbe Weise. Bei Sendungen in die USA stehen `Parcel received by Transit Facility.` für den Eingang in einer Transitanlage des Zustellers, `In Processing at Transit Facility.` für die Sortierung, `In Transit to Next Facility.` für den Weitertransport und `Parcel received by local Facility.` für die Ankunft im Zustelldepot. `It is currently in transit to the next facility` und `The package is being transferred to the next facility` melden einen Weitertransport nach dem Flug, `Shipment arrived to sorting centre / Shipment in the sorting centre` den Eingang im Sortierzentrum eines Partners. Peek zeigt alle sieben als „Unterwegs“. Bei einer Sendung ins Vereinigte Königreich können Zeilen von [InPost](carrier:inpost) schon vor dem ersten Scan im WanbExpress-Sortierzentrum erscheinen: `Your parcel is on its way to InPost. Please check back for updates` („Etikett erstellt“) und `We have your parcel. Please check back for updates` („Beim Paketdienst eingegangen“). `We'll apply your My Account preferences to this parcel delivery` ist lediglich ein Hinweis von InPost auf Kontoeinstellungen, kein Scan: Peek behält die Zeile im Verlauf, ändert aber den Status nicht. Dasselbe gilt für `Consignee Contacted`, einen Anruf des Fahrers zur selben Minute wie ein erfolgloser Versuch.

**Uhrzeiten.** Jeder Scan enthält seinen Versatz zu UTC. Peek liest diese Angabe aus und zeigt alle Zeiten in der Zeitzone deines Geräts an, die neueste zuerst. Typische Pausen und Übergaben bei Sendungen aus China erklärt [Pakete aus China verfolgen](guide:tracking-from-china).

## Wer WanbExpress-Pakete zustellt

Die Scans von WanbExpress begleiten das Paket aus China durch den Zoll bis zu einem lokalen Paketdienst, der es zustellt. Der WanbExpress-Tracker kann diesen Dienstleister, seine Sendungsnummer und ein Zustellfoto anzeigen.

**Was der Peek-Code zeigt.** Bei einer Sendung nach Deutschland nennt der Verlauf iMile als Zusteller, und ein Scan verweist auf das Netz von Poste Italiane. Bei einer Sendung ins Vereinigte Königreich erscheinen Zeilen von InPost. Zudem kennt der Code für Frankreich Umetikettierungen auf Cainiao-Express-Nummern (`DOFR…HD` oder `CNFR…HD`) und für Australien weitergeleitete Scans von J&T Express Australia. Für die USA, Irland oder Kanada nennt der Code keinen bestimmten Zusteller.

**Zustellversuche und Abholung.** Es gelten stets die Regeln des ausliefernden Dienstes: Zahl der Versuche, Benachrichtigungskarten, Lagerort und Lagerfrist. Eigene WanbExpress-Regeln hierzu konnte Peek nicht finden. Sobald du Zusteller und dessen Nummer kennst, prüfe sein eigenes Tracking und vereinbare dort eine neue Zustellung. Siehe auch [verpasste Zustellung](guide:missed-delivery).

## WanbExpress-Paket kommt nicht an oder Status steht still

**Nummer nicht gefunden.** Kennt der Tracker von WanbExpress die Nummer noch nicht, zeigt Peek „Warten auf WanbExpress“ und fragt weiter nach. Kennt WanbExpress sie zwei Tage später noch immer nicht und hat auch sonst kein Dienst geantwortet, zeigt Peek „Auch nach zwei Tagen kennt der Paketdienst diese Nummer nicht. Prüfe sie, wähle einen anderen Paketdienst oder lösche das Paket.“ und prüft danach täglich, nach einer Woche wöchentlich. Vergleiche jedes Zeichen mit der Nachricht des Shops: `WNB`, zwei Buchstaben, zehn Ziffern, `YQ`. Tut sich nach einigen Tagen nichts, frag beim Shop nach, ob wirklich verschickt wurde.

**Hängt bei `Shipping Order Created.` oder `Parcel Data Received`.** Der Shop hat die Sendung angemeldet, WanbExpress hat das Paket aber noch nicht gescannt. Frag den Shop, wann es übergeben wurde.

**Steht nach `Parcel arrives at Airport, Parcel Arrival`.** Das Paket wartet wahrscheinlich auf den Einfuhrzoll. Bei Online-Bestellungen aus Ländern außerhalb der EU gelten seit dem 1. Juli 2026 vorübergehend 3 € Zoll pro Artikel (Warenart) für Sendungen bis 150 € Warenwert sowie seit dem 1. November 2026 eine EU-Bearbeitungsgebühr von 2 € pro Artikel unabhängig vom Wert. Wirst du zu Einfuhrabgaben aufgefordert, lies [Paket beim Zoll](guide:customs); bleibt es lange liegen, melde dich beim Verkäufer. Mehr unter [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating).

**Nichts nach `Custom clearance completed`.** Der lokale Zusteller hat das Paket oder übernimmt es in Kürze, und sein eigenes Tracking kann weiter sein als das von WanbExpress. Suche seinen Namen und seine Nummer unter tracking.wanbexpress.com oder frag den Verkäufer danach.

**Zustellversuch.** Nach einer Meldung wie `<Route not completed>` entscheidet der lokale Zusteller über den nächsten Versuch oder die Abholung. Wende dich an ihn, nicht an WanbExpress.

**Zugestellt, aber nichts angekommen.** Prüfe unter tracking.wanbexpress.com, ob ein Zustellfoto vorliegt, und kontaktiere anschließend den lokalen Zusteller und den Verkäufer. Mehr in [Paket zugestellt, aber nicht erhalten](guide:delivered-not-received).

**Verloren oder zurückgeschickt.** Der Verkäufer hat WanbExpress beauftragt; er oder der Marktplatz ist Ansprechpartner für Erstattung oder Ersatz. Siehe [verlorenes Paket](guide:lost-parcel) und [zurück an Absender](guide:returned-to-sender).

## WanbExpress-Kontakt und Kundenservice

Peek konnte die Website von WanbExpress nicht prüfen und nennt hier daher keine Kontaktdaten für WanbExpress. Für Fragen zur Bestellung wende dich an den Verkäufer, der den Versand gebucht hat, oder an den Marktplatz, falls der Verkäufer nicht reagiert. Für die Zustellung selbst ist der lokale Paketdienst zuständig.

## Ein WanbExpress-Paket mit Peek verfolgen

[Peek](/) erkennt eine WanbExpress-Nummer am Format (`WNB`, zwei Buchstaben, zehn Ziffern und `YQ`, das kein anderer Dienst im Katalog nutzt) und fragt den WanbExpress-Tracker allein mit dieser Nummer ab. Konto, Postleitzahl oder Telefonnummer sind nicht nötig.

Da der Tracker von WanbExpress eine Prüfung verlangt, ruft Peek ihn über einen Browser ab. Antwortet der Tracker nicht, fragt Peek Tracking-Dienste ab und zeigt deren Verlauf in deren Worten an; dieser kann hinter WanbExpress zurückliegen.

Aus dem WanbExpress-Tracker übernimmt Peek den Status, die bis zu 100 neuesten Scans mit Ortsangabe sowie Herkunfts- und Zielland. Meldet der Datensatz das Paket als unterwegs, in Zustellung oder zugestellt, richtet sich Peek danach, selbst wenn der letzte Scan weniger aussagt. Übermittelt WanbExpress zusätzlich eine Nummer des lokalen Zustellers und nennt das Last-Mile-Panel einen im Katalog bekannten Paketdienst, zu dem diese Nummer passt, kann Peek „Zustellung durch …“ (oder „Bei … angekündigt, noch nicht eingetroffen“) anzeigen und das Paket dort weiterverfolgen. Ein voraussichtliches Lieferdatum gibt Peek nicht an; Zustellfotos und Empfängeradresse lässt es weg – dafür schau auf tracking.wanbexpress.com nach oder füge die Nummer des Zustellers selbst in Peek hinzu. Peek verfolgt über 4500 Paketdienste.

Solange du das Paket geöffnet hast oder am Zustelltag mit aktivierten Mitteilungen, prüft Peek bei erreichbarem WanbExpress-Tracker bis zu alle 10 Minuten, und ab 6 Uhr in der Zeitzone des Paketdienstes am Tag der Zustellung bis zu alle 5 Minuten, wenn die Abfrage einen Browser öffnet; sonst stündlich. Über Tracking-Dienste prüft Peek stündlich, am Zustelltag bis zu alle 15 Minuten, solange das Paket geöffnet ist oder Mitteilungen aktiv sind. Nach zwei Tagen ohne Neuigkeiten (nach 12 Stunden bei „Etikett erstellt“ oder „Beim Paketdienst eingegangen“) prüft Peek stündlich. Du kannst Mitteilungen für „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“ aktivieren. „Wichtige Schritte“ (Zustellung, Abholung, Zoll, verpasste Versuche und Rücksendungen) erfasst Zustelltour, Fehlversuch und Zustellung, nicht aber die Zollzeilen von WanbExpress, die Peek als „Unterwegs“ führt. Peek kann weder WanbExpress noch den Verkäufer für dich kontaktieren.

## Fragen zur WanbExpress-Sendungsverfolgung

### Was ist WanbExpress?

Ein chinesischer Versanddienstleister, auch bekannt als Wanb Express, WanBang Express oder WANB. Im Code von Peek beginnen seine Scans in einem Sortierzentrum in Shenzhen, folgen Flug und Zoll und enden mit den Scans des Zustellers im Zielland.

### Wo kann ich ein WanbExpress-Paket verfolgen?

Oben im Eingabefeld oder direkt beim Tracker von WanbExpress unter tracking.wanbexpress.com. Dort prüft eine Abfrage gegen Bots die Eingabe; der Tracker kann auch den lokalen Zusteller nennen. Gib die gesamte `WNB…YQ`-Nummer ein.

### Was bedeutet „Customs declaration completed“ bei WanbExpress?

Dass eine Zollanmeldung für das Paket abgeschlossen wurde. Die Meldung nennt kein Land und ist nicht die Einfuhrfreigabe – diese heißt `Custom clearance completed`. Peek führt beide als „Unterwegs“.

### Wer liefert WanbExpress-Pakete in meinem Land aus?

Ein lokaler Paketdienst, den der Tracker von WanbExpress samt eigener Sendungsnummer nennen kann. Der Code von Peek zeigt iMile bei einem Paket nach Deutschland, InPost-Zeilen ins Vereinigte Königreich und einen Scan mit dem Netz von Poste Italiane sowie Übergaben an J&T Express Australia oder Nummern vom Typ `DOFR…HD`/`CNFR…HD` in Frankreich; für andere Länder schau auf tracking.wanbexpress.com nach oder frag den Verkäufer.

### Wen kontaktiere ich, wenn mein WanbExpress-Paket fehlt?

Den Verkäufer, bei dem du bestellt hast: Er hat die Sendung bei WanbExpress gebucht. Da Peek die Website von WanbExpress nicht abrufen konnte, nennt diese Seite keine direkten Kontaktdaten von WanbExpress.

:::sources
- [Universal Parcel Scraper: WanbExpress](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/wanbexpress) – Namen, `WNB…YQ`-Format, Browser-Abruf, Scan-Wortlaute und Stufen (einschließlich `Customs inspection has not yet released`, `A-Scan: shipment in international transit`, iMile-, InPost-, Poste-Italiane- und US-Partnerzeilen), UTC-Zeitstempel, Zusteller-Erkennung (`TrackLastMileCarrier`), verworfene Fotos und Adresse, 100 neueste Scans
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – andere `YQ`-Formate (`JS Express`, `Juxi Express`), Tracking-Dienste als Rückfall und Übergabe an den Zusteller
- [Europäische Kommission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – vorübergehender Zoll von 3 € pro Artikel bis 150 € seit 1. Juli 2026, EU-Bearbeitungsgebühr von 2 € seit 1. November 2026
- [Europäische Kommission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € Bearbeitungsgebühr pro Artikel unabhängig vom Wert
:::
