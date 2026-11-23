---
title: DAI Post Sendungsverfolgung: Nummern, Status & Hilfe
description: DAI-Post-Paket (Parcel Right) in Australien, Neuseeland und Singapur verfolgen: Nummern, Status erklärt, Abstellgenehmigung, Abholung und Kontakte.
slug: dai-post-sendungsverfolgung
published: 2026-11-23
updated: 2026-11-23
---

DAI Post ist ein australisches Versandunternehmen für Online-Shops, gegründet im Jahr 2000 in Melbourne. Es bringt Pakete aus den USA, Großbritannien, Europa und Asien zu Käufern in Australien, Neuseeland und Singapur. Die eigene Zustellung auf der letzten Meile heißt Parcel Right. Du verfolgst ein Paket auf der [Tracking-Seite von DAI Post](https://daipost.com/track) oder im Feld oben. Die Nummer steht in der Bestellbestätigung des Shops; findest du sie nicht, frag beim Shop nach.

## DAI Post Sendungsnummer: Wie sie aussieht

DAI Post verfolgt die Nummern, die Shops auf ihre Pakete drucken, weshalb sie verschiedene Formen haben. Der Tracker nimmt bis zu 10 Nummern gleichzeitig an. Die Formen, die Peeks Code kennt (erfundene Ziffern):

| Nummer (erfunden) | Was sie ist |
| --- | --- |
| `NZC482910375` | `NZC` und neun Ziffern: in Peeks Code die DAI-Post-Nummer für Pakete nach Neuseeland. Peek erkennt DAI Post sofort. |
| `00393580944829103754` | 20 Ziffern beginnend mit `0039358094`, die letzte eine Prüfziffer: ein SSCC-Etikettenbarcode, oft gedruckt als `(00) 3 9358094 482910375 4`. Auch andere Paketdienste nutzen 20 Ziffern, sodass die Form allein nicht eindeutig ist. |
| `KPR48291037561` | Eine eigene Shop-Referenz, Buchstaben und Ziffern. An der Form erkennt Peek sie nicht. |

Leerzeichen, Klammern und Kleinschreibung spielen keine Rolle. Stimmt eine Ziffer einer 20-stelligen Nummer nicht, schlägt die Prüfziffer fehl und Peek ordnet sie DAI Post nicht zu: prüfe die Nummer. Mehr in [wie du eine Sendungsnummer findest](guide:find-tracking-number) und [Formate von Sendungsnummern](guide:tracking-number-formats).

## DAI Post Tracking-Status erklärt

Der Tracker von DAI Post zeigt für jeden Schritt einen freundlichen Satz. Manche Pakete zeigen eine kürzere Fassung desselben Schritts, etwa `Checked in at origin hub`. Peek liest den Statuscode aus, daher bedeuten beide Fassungen dasselbe. Die Zeilen, die Peek kennt, in Reihenfolge:

| DAI Post meldet | Bedeutung | Peek zeigt |
| --- | --- | --- |
| `We’ve received your delivery details. We’ll let you know as soon as your parcel is in our hands.` oder `Customer label information received` | Der Shop hat deine Daten übermittelt; DAI Post hat das Paket noch nicht. | „Etikett erstellt“ |
| `Your parcel’s checked in and being prepared for its journey.` oder `Checked in at origin hub` | Das Abgangs-Hub von DAI Post hat das Paket erhalten. | „Beim Paketdienst eingegangen“ |
| `We’ve processed your parcel at our origin facility. Next, on the move to you!` oder `Parcel processed at origin facility` | Sortiert und abfahrbereit. | „Unterwegs“ |
| `There’s a departure delay. Thanks for your patience, we’ll update you soon.` oder `There’s a transit delay. Thanks for your patience, we’ll update you soon.` | Der Flug verspätet sich oder es gibt eine Verzögerung unterwegs. | „Unterwegs“ |
| `Your parcel is in the air and is heading your way.` oder `Parcel departed origin country` | Im Flug aus dem Absenderland. | „Unterwegs“ |
| `Parcel arrived in trans-shipment country.`, dann `Your parcel’s left the transfer country and is back on the move.` | Zwischenstopp in einem Transitland. | „Unterwegs“ |
| `Touchdown! Your parcel has landed.` oder `Parcel arrived in destination country` | Im Zielland angekommen. | „Unterwegs“ |
| `We’ve collected your parcel from the airline and it’s being processed.` oder `Collected from the airline for processing` | DAI Post hat es von der Fluggesellschaft übernommen. | „Unterwegs“ |
| `Your parcel is being checked by customs. We’ll let you know when it’s cleared.` oder `Parcel processed by clearance facility` | Der Zoll prüft das Paket. | „Beim Zoll“ |
| `Customs clearance delay` | Der Zoll hält es weiter fest. | „Beim Zoll“ |
| `All clear! Your parcel’s passed customs and is ready for the next leg.` oder `Customs cleared` | Vom Zoll freigegeben. | „Unterwegs“ |
| `We’ve got your parcel and it’s been sorted for delivery.` oder `Parcel received and sorted` | Für die Zustellung sortiert. | „Unterwegs“ |
| `Your parcel’s on an interstate journey. We’ll keep you updated as it travels.` | Zwischen Bundesstaaten unterwegs. | „Unterwegs“ |
| `Your parcel’s arrived at our local facility, it's on the way.` | In einem Depot in deiner Nähe. | „Unterwegs“ |
| `Your parcel’s on its way to you, almost there!` | Auf dem Weg zum Zustelldepot, noch nicht in Auslieferung. | „Unterwegs“ |
| `It’s happening, your parcel will be delivered today. Get ready!` oder `Parcel out for final mile carrier delivery` | Heute in Zustellung. | „In Zustellung“ |
| `Parcel unsafe to leave at address` | Der Fahrer konnte es nicht sicher abstellen (siehe unten). | „Zustellversuch“ |
| `Parcel available for collection` | Wartet an einer Abholstelle. | „Abholbereit“ |
| `Delivered! Your parcel has arrived. Enjoy!` | Zugestellt. | „Zugestellt“ |
| `Delivered, left where you can find it.` oder `Parcel delivered - authority to leave` | Ohne Unterschrift an deiner Adresse abgelegt. | „Zugestellt“ |

Bei einer unbekannten Nummer meldet DAI Post `Sorry, we couldn't find your parcel.` Mehr in [was Tracking-Status bedeuten](guide:tracking-statuses).

**Uhrzeiten.** DAI Post nennt jede Uhrzeit auf der Ortszeit des Scan-Ortes, ohne Zeitzone. Peek leitet die Zeitzone vom Ort ab und zeigt Zeiten in deiner Geräte-Zeitzone; die Stunde der ersten Zeile kann abweichen.

## DAI Post Zustellung: Abstellgenehmigung, Abholung und Rücksendungen

Laut DAI Post erreicht sein Netz 100 % der australischen Bevölkerung. Die Uhrzeit hängt von der Route des Fahrers ab, die je nach Tagesaufkommen und Verkehr wechselt.

- **Abstellgenehmigung (*Authority to leave*).** Keine Unterschrift nötig. Öffnet niemand und hält der Fahrer den Ort für sicher, stellt er das Paket ab.
- **Nicht sicher zum Abstellen.** Wäre das Paket zu ungeschützt, geht es zur nächstgelegenen Abholstelle in deiner Gegend, und DAI Post sendet Abholhinweise. Im DAI-Post-Tracking verlinkt `Parcel available for collection` eine Karte; Peek zeigt nur den Ort.
- **7 Tage zur Abholung.** Ein innerhalb von 7 Tagen nicht abgeholtes Paket geht zurück ins DAI-Post-Depot und dann an den Shop. Dasselbe gilt bei unvollständigen Lieferdaten.
- **Nachrichten.** Benachrichtigungen zu wichtigen Schritten erhältst du nur, wenn der Shop DAI Post deine E-Mail-Adresse und Handynummer übermittelt hat.

In den Bedingungen von DAI Post steht zudem, dass das Paket bei Abwesenheit an einem sicheren Ort abgelegt oder zu einem *Collection Point* gebracht werden kann und dann als zugestellt gilt. Wer in Neuseeland oder Singapur zustellt, nennen die Seiten nicht. Siehe [verpasste Zustellung](guide:missed-delivery) und [nicht abgeholte Pakete](guide:uncollected-parcel).

## Zoll bei einem DAI-Post-Paket

**Vom Zoll festgehalten (*Customs held*).** Laut Hilfeseite von DAI Post bedeutet *Customs held*, dass ABF oder DAFF – Australiens Grenz- und Biosicherheitsbehörden – vermutlich weitere Angaben vor der Freigabe benötigen. Frage beim DAI-Post-Kundenservice nach: im Kontaktformular gibt es den Grund „My parcel is Customs held.“

**Gebühren.** Laut den Bedingungen von DAI Post haftet der Absender für sämtliche Versandkosten einschließlich Steuern und Zöllen. Verweigerst du bei Zustellung eine Gebühr, schuldet der Absender sie weiterhin, und DAI Post darf das Paket bis zur Zahlung einbehalten oder zurücksenden. Was du zahlen musst, hängt von den Regeln deines Landes ab: siehe [Pakete beim Zoll](guide:customs). Wirkt eine Nachricht über Gebühren verdächtig? Siehe [Betrugs-SMS zu Paketen](guide:delivery-scam-texts).

## DAI Post Tracking aktualisiert nicht, verloren oder zurückgesandt

**Tagelang keine neue Zeile.** DAI Post scannt Pakete nur an Schlüsselpunkten und betont, dass Sendungen dazwischen weiterlaufen. Bei Sendungen zwischen Bundesstaaten oder in Regionen kann das Tracking tagelang stehen. Bleibt das Paket vier Tage ohne neue Zeile bei „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“, zeigt Peek „Keine Aktualisierung seit …“ mit dem Tag dieser Zeile und einen Link „Hol dir Hilfe bei DAI Post“. Siehe [warum sich die Sendungsverfolgung nicht aktualisiert](guide:tracking-not-updating).

**Weiterhin „Etikett erstellt“.** DAI Post hat das Paket noch nicht. Bleibt das tagelang so, frag beim Shop nach, ob wirklich verschickt wurde.

**Zugestellt, aber nicht da.** Schau wegen der Abstellgenehmigung zuerst rund ums Haus. DAI Post kann dir per E-Mail einen Zustellnachweis für *Authority to Leave* senden: frage über das Kontaktformular an und wende dich dann an den Shop. Siehe [zugestellt, aber nicht erhalten](guide:delivered-not-received).

**Verloren oder zurückgesandt.** Bei einem nicht angekommenen Paket nutze das Anfrageformular von DAI Post mit dem Grund „I did not receive my parcel.“ In beiden Fällen hat der Shop den Versand gebucht – bitte dort um Erstattung oder Ersatz. Siehe [verlorene Pakete](guide:lost-parcel) und [zurück an den Absender](guide:returned-to-sender).

## DAI Post Kundenservice und Kontakt

Die Kontaktkanäle von DAI Post:

- **Telefon:** Australien +61 3 8586 1200, Neuseeland +64 800 451 718, Singapur +65 800 321 1587. Öffnungszeiten werden nicht genannt.
- **E-Mail:** consumercare@daipost.com.
- **Online:** das [Anfrageformular](https://daipost.com/contact). Es fragt nach Name, Telefonnummer, E-Mail, Sendungsnummer und dem Grund der Anfrage.

## Ein DAI-Post-Paket mit Peek verfolgen

[Peek](/) verfolgt ein DAI-Post-Paket allein mit der Nummer – ohne Konto und ohne Postleitzahl.

- **Eine `NZC`-Nummer.** Peek erkennt DAI Post sofort („Erkannter Paketdienst“). Kennt DAI Post die Nummer zwei Tage nach seiner ersten Antwort noch nicht, bittet Peek dich, sie zu prüfen, einen anderen Paketdienst zu wählen oder das Paket zu löschen.
- **Eine 20-stellige Nummer ab `0039358094`.** Füge sie trotzdem ein: Peek unterstützt mehr als 4.500 Paketdienste. Während der Prüfung steht dort „Tracking-Dienste werden geprüft…“, und DAI Post gehört zu den ersten angefragten Diensten. Hat DAI Post das Paket, nennt Peek ihn. Falls noch nicht, zeigt das Paket „Warten auf den ersten Scan von DAI Post“ und die Karte „Wahrscheinlich DAI Post“; nach einer Woche schlägt Peek vor, die Nummer beim Absender zu prüfen. Zum manuellen Auswählen tippe auf „Ändern“: DAI Post steht unter „Passt zu dieser Nummer“.
- **Jede andere Nummer.** Füge sie ein, tippe auf „Ändern“ und wähle DAI Post (suche nach DAI Post oder Parcel Right). Bei einem bereits gespeicherten Paket nutze „Paketdienst ändern“.
- **Ein Link.** Füge einen Tracking-Link von daipost.com, parcelright.com.au oder parcelrig.ht ein: Peek übernimmt die Nummer und wählt sofort DAI Post.

Peek zeigt Status, jede Zeile im Wortlaut von DAI Post, Ort und Land jedes Scans, das voraussichtliche Lieferdatum (falls DAI Post eines nennt) und die Zustellzeit. Es zeigt keinen Abholpunkt, keinen Servicenamen, kein Gewicht und keinen Zustellnachweis. „Website von DAI Post öffnen“ öffnet die Nummer direkt im DAI-Post-Tracking. In Peeks Code laufen manche [Asendia](carrier:asendia)-Pakete und manche [ePost Global](carrier:epost-global)-Pakete nach Neuseeland über DAI Post: deren Tracking zeigt dann die DAI-Post-Scans.

Solange das Paket in Peek geöffnet ist, oder an seinem Zustelltag mit aktivierten Benachrichtigungen, prüft Peek es bis zu alle 10 Minuten, und alle 2 Minuten ab 6 Uhr Ortszeit an dem Tag, an dem DAI Post das Paket in Zustellung scannt. Nach zwei Tagen ohne Neuigkeiten (12 Stunden bei „Etikett erstellt“ oder „Beim Paketdienst eingegangen“) prüft Peek stündlich, nach sieben Tagen ohne Neuigkeiten bei „Etikett erstellt“ alle 6 Stunden. Benachrichtigungen können „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“ folgen. Peek kann weder DAI Post noch den Shop für dich kontaktieren.

## Fragen zur DAI Post Sendungsverfolgung

### Ist Parcel Right dasselbe wie DAI Post?

Parcel Right ist die eigene Zustellung auf der letzten Meile von DAI Post. In Peek findest du den Dienst unter beiden Namen.

### Was bedeutet „Parcel unsafe to leave at address“?

Der Fahrer traf niemanden an, und der Ort war nicht sicher genug. Das Paket geht zur nächsten Abholstelle: hole es innerhalb von 7 Tagen ab, sonst geht es zurück an den Shop.

### Wie lange liefert DAI Post?

Laut Bedingungen von DAI Post sind Regellaufzeiten Schätzungen, keine Garantien. Der Tracker kann ein voraussichtliches Lieferdatum anzeigen, und Peek zeigt es ebenfalls.

:::sources
- [DAI Post: Home](https://daipost.com/) – Melbourne, 2000, Pakete aus den USA, Großbritannien, Europa und Asien nach Australien, Neuseeland und Singapur, Parcel Right
- [DAI Post: Help and support](https://daipost.com/help-and-support) – Telefonnummern, Abdeckung, Lieferzeiten, fehlende Updates, Benachrichtigungen, Abstellgenehmigung, Abholung, Rücksendungen, Zoll, Zustellnachweis
- [DAI Post: Delivery tracking](https://daipost.com/track) – Tracker, bis zu 10 Nummern
- [DAI Post: Contact](https://daipost.com/contact) – Anfrageformular und Gründe, Telefonnummern, consumercare@daipost.com
- [DAI Post: Terms & conditions](https://daipost.com/terms) – sicherer Ort oder Collection Point, geschätzte Lieferzeiten, Gebühren
- [Universal Parcel Scraper: DAI Post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dai-post) – Namen, Nummernformate, Links, Texte und Stufen, Ortszeiten, erwartete Lieferung
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Erkennung, DAI-Post-Scans bei Asendia und ePost Global
:::
