---
title: JS Express Sendungsverfolgung: JSE-Nummer, Status, Zusteller
description: JS-Express-Paket (急速国际) aus China verfolgen: wie die JSE…YQ-Nummer aussieht, was die Scans bedeuten und wer in Deutschland, Österreich und der Schweiz zustellt.
slug: js-express-sendungsverfolgung
published: 2026-11-03
updated: 2026-11-14
---

JS Express (急速国际, auch JS EXP) ist ein chinesisches Logistikunternehmen für grenzüberschreitende Onlineshops, betrieben von 急创速供应链管理（苏州）有限公司 in Suzhou. Hat ein Shop deine Bestellung mit JS Express verschickt, beginnt die Sendungsnummer mit JSE und endet auf YQ: Füg sie oben ins Feld ein. JS Express bringt das Paket aus China in dein Land, die Zustellung übernimmt dann ein Paketdienst vor Ort. In Deutschland tauchen im Verlauf von JS Express die Scans von DHL, iMile oder J&T Express auf.

## JS Express Sendungsnummer: so sieht sie aus

Die Frachtbriefnummern von JS Express bestehen aus JSE, zwei Buchstaben, zehn Ziffern und YQ, zusammen 17 Zeichen. Mit erfundenen Ziffern:

| Nummer | Was sie ist |
| --- | --- |
| `JSEAA0000000000YQ` | JSE, zwei Buchstaben, zehn Ziffern und YQ: eine Frachtbriefnummer von JS Express. |
| `JSEAA000000000YQ` | Nur neun Ziffern: So sehen die Nummern von JS Express nicht aus. Prüf, ob eine Ziffer fehlt. |
| `JXCAA0000000000YQ` | Endet auch auf YQ, beginnt aber mit JXC: eine Nummer von Juxi Express. |

Andere chinesische Spediteure nutzen dieselbe Form mit eigenen Buchstaben vorne: FEI bei 17FEIA, WNB bei WanbExpress, SFC bei SFC, CJP bei CJ Packet. JSE steht für JS Express. Leerzeichen und Kleinbuchstaben stören Peek nicht. Eine Null an der Stelle des zweiten Buchstabens liest Peek als O, ein O unter den zehn Ziffern als Null. Peek braucht aber die ganze Nummer.

**Wo du sie findest.** Der Shop schickt dir die Nummer mit der Versandbestätigung oder zeigt sie bei deiner Bestellung. Laut JS Express arbeitet das Unternehmen eng mit Amazon, Walmart, Shopify, eBay, AliExpress, Wish und Shopee zusammen. Findest du die Nummer nicht: [So findest du deine Sendungsnummer](guide:find-tracking-number).

**Kein Link zum Teilen.** Die Tracking-Seiten von JS Express nehmen die Nummer nur über ein Formular an; einen Link, der direkt ein Paket öffnet, gibt es nicht. Kopier also die Nummer. Die englische Seite „Express Query“ antwortet sofort, die chinesische verlangt vorher einen Bildcode (验证码).

## JS Express Sendungsverfolgung: was die Status bedeuten

Die Seite von JS Express listet jeden Scan mit Datum und Uhrzeit, einem Ort in 【Klammern】 und einer Meldung auf, den neuesten zuerst, oben mit „Latest“ markiert. Die Meldungen sind meist auf Englisch. In der Reihenfolge, in der ein Paket sie bekommt:

| JS Express schreibt | Was es für dich heißt | Peek zeigt |
| --- | --- | --- |
| `Electronic notification of order creation received.` | Der Shop hat JS Express die Daten der Bestellung geschickt. Das Paket hat JS Express noch nicht. | „Etikett erstellt“ |
| `PRE-ADVICE` | Ein Partner hat die Daten des Pakets, bevor das Lager von JS Express das Paket hat. | „Etikett erstellt“ |
| `Item has been collected` | Das Lager von JS Express in China hat das Paket: der erste echte Scan. | „Beim Paketdienst eingegangen“ |
| `Item inbound in sorting center.`, `Item outbound in sorting center.` | Bei JS Express in China sortiert. | „Unterwegs“ |
| `Domestic allocation has begun`, `Under domestic allocation` | Noch im Lager von JS Express in China, nach der Sortierung und bevor es losgeht. | „Unterwegs“ |
| `Waybill Generated for` | Der Paketdienst in deinem Land hat seinen Frachtbrief erstellt, während das Paket noch im Lager von JS Express in China liegt. | „Etikett erstellt“ oder der Status, den das Paket schon hatte, meist „Unterwegs“ |
| `Arrive at transit center` | Im Umschlagzentrum von JS Express in China, zwischen Sortierung und Abfahrt. | „Unterwegs“ |
| `Item departed from origin.`, `Arrive at international airport to abroad` | Auf dem Weg aus dem Lager, dann am Flughafen in China. | „Unterwegs“ |
| `Arrived at export center`, `Exports Center Sorting and Packing`, `Arrived at Origin Port` | Auf manchen Linien zwischen diesen beiden: Ein Exportzentrum in China sortiert und verpackt das Paket, dann kommt es am Hafen an, über den es China verlässt. | „Unterwegs“ |
| `Shipment arrived at facility and measured.`, `Depart from facility to service provider.` | Eine Anlage in China wiegt und misst das Paket und schickt es dann weiter an die Linie, mit der es fliegt. | „Unterwegs“ |
| `Start on-site transportation and shipment` | Am Flughafen in China, nach der Ankunft dort: Der Transport vor Ort hat begonnen, noch bevor der Zoll in China das Paket zur Ausfuhr freigibt. | „Unterwegs“ |
| `Released From Export Customs` | Die Ausfuhrabfertigung in China ist erledigt. Mit der Einfuhr hat das noch nichts zu tun. | „Unterwegs“ |
| `DEPART ORIGIN AIRPORT`, `Departure from the original airport`, `Item Flight departure`, `Initial landing`, `Connecting flights flight takeoff` | Der Flug ist gestartet, manchmal mit Zwischenlandung und Anschlussflug. | „Unterwegs“ |
| `ARRIVED AT DESTINATION AIRPORT`, `Arrival to the destination airport`, `Item Flight landing`, `NOA-Notification of arrival from GHA` | Am Zielflughafen gelandet. Mit NOA meldet der Abfertiger am Flughafen die Ankunft. | „Unterwegs“ |
| `The customs clearance agent is queuing up to pick up the goods` | Der Zollagent in deinem Land wartet am Frachtterminal des Flughafens darauf, die Sendung abzuholen: Noch hat sie weder er noch der Zoll. | „Unterwegs“ |
| `The customs clearance agent has picked up the goods` | Der Zollagent hat die Sendung am Frachtterminal für die Einfuhrabfertigung abgeholt. | „Beim Zoll“ |
| `The goods have been picked up abroad` | Der Partner von JS Express hat die Sendung am Zielflughafen übernommen. Eine neue Abholung beim Verkäufer ist das nicht. | „Unterwegs“ |
| `Customs clearance agent received the shipment in destnation country.` | Der Zollagent in deinem Land hat die Sendung nach dem Flug für die Einfuhrabfertigung übernommen. „destnation“ schreibt JS Express selbst so. | „Beim Zoll“ |
| `Inbound to customs temporary warehouse` | In einem Zolllager, wo es auf die Einfuhrabfertigung wartet. | „Beim Zoll“ |
| `Released From Import Customs`, `Released from customs: customs cleared.` | Der Zoll hat das Paket zur Einfuhr freigegeben. | „Unterwegs“ |
| `Depart from the destination distribution center.` | Ein Verteilzentrum in deinem Land schickt das Paket nach dem Zoll weiter. | „Unterwegs“ |
| `In transit, it's progressing through post network` | Die Post in deinem Land befördert das Paket durch ihr Netz weiter, noch vor jeder Zustelltour. | „Unterwegs“ |
| `collection by overseas warehouse` | Das Lager von JS Express in deinem Land hat das Paket nach dem Zoll übernommen. | „Unterwegs“ |
| `Load cargo overseas`, `Vehicles depart from` | Dieses Lager verlädt das Paket für den Zusteller, dann fährt der Lkw ab; der Ort steht beim Scan. | „Unterwegs“ |
| `Delivery to local courier`, `Last Mile Delivery` | Übergabe an den Paketdienst, der in deinem Land zustellt, noch vor dessen erstem Scan. Bei dir ist das Paket noch nicht. | „Unterwegs“ |
| `Delivery in progress` | Auf manchen Strecken: Der Paketdienst in deinem Land hat das Paket, nachdem es in seiner Station angekommen ist, und noch vor jeder Zustellrunde. Trotz des Wortlauts ist es noch nicht in Zustellung. | „Unterwegs“ |
| `到达待取` | Ein eigener Status von JS Express auf Chinesisch: angekommen, wartet auf Abholung. Als Ort steht nur das Land da. | „Abholbereit“ |
| `The delivery address is incorrect. Please ask the recipient to contact their local area?` | Eine eigene Meldung von JS Express nach einem gescheiterten Zustellversuch: Die Adresse ist unklar oder falsch. Du sollst dich bei der Stelle vor Ort melden, deren Telefonnummer auf der Seite von JS Express steht; Peek lässt Telefonnummern weg. | „Zustellversuch“ |

Dieselbe Meldung kann auch auf Chinesisch kommen, ebenfalls mit der Telefonnummer am Ende: `收件人地址不详或有误，派送失败，逾期退回或销毁的具体费用以账单为准，麻烦让收件人联系下当地` (Adresse unklar oder falsch, Zustellung gescheitert; die Kosten für Rücksendung oder Vernichtung nach Ablauf der Frist stehen in der Rechnung; der Empfänger soll sich vor Ort melden). Auch sie zeigt Peek als „Zustellversuch“.

**Die Scans von DHL.** Der Verlauf von JS Express übernimmt auch die Scans des Zustellers in dessen eigenen Worten, in Deutschland die von DHL, auf Englisch. Die ersten beiden kommen schon, bevor das Paket das Lager von JS Express in China verlässt:

| DHL schreibt | Was es für dich heißt | Peek zeigt |
| --- | --- | --- |
| `The instruction data for this shipment have been provided by the sender to DHL electronically.` | DHL hat die Daten des Pakets bekommen, noch bevor das Lager von JS Express das Paket hat. | „Etikett erstellt“ |
| `The shipment is in transit to DHL.` | Das Paket ist auf dem Weg zu DHL. Die Zeile kommt, während JS Express es in China noch sortiert. | „Unterwegs“ |
| `The shipment has been processed in the parcel center of origin` | Der erste Scan von DHL in Deutschland, nach der Übergabe an DHL. | „Unterwegs“ |
| `The shipment arrived in the region of recipient and will be transported to the delivery base in the next step.` | Das Paket ist in deiner Region und kommt als Nächstes zur Zustellbasis. | „Unterwegs“ |
| `Die Sendung wurde im Briefzentrum bearbeitet.` | Ein Briefzentrum von DHL hat ein kleines Paket bearbeitet. Diese Zeile kommt auf Deutsch. | „Unterwegs“ |

Die ersten beiden Zeilen gibt es auch ohne Schlusspunkt am Ende; Peek zeigt sie genauso an.

**Die Scans von iMile.** Stellt [iMile](carrier:imile) zu, kann sein Fahrer eine Tour mit `Route not completed` beenden: Das Paket wurde nicht zugestellt, Peek zeigt „Zustellversuch“.

**Die Scans von J&T Express.** Stellt [J&T Express Germany](carrier:j-and-t-de) zu, kommen seine Scans auf Deutsch, mit den Orten in den Klammern 【】: `【】【】Ihr Sendung wurde abgeholt.` ist sein erster Scan, wenn es das Paket nach Flug und Zoll übernimmt. `【】Die Sendung hat【】verlassen. Weiterversand nach【】` heißt, dass das Paket von einem Standort zum nächsten geht, `【】Die Sendung ist bei【】 angekommen.`, dass es in einem Umschlagzentrum oder, vor der Tour, im Depot angekommen ist, das zustellt. Peek zeigt alle drei als „Unterwegs“.

**Ein Zusteller ohne Namen.** Auf manchen Linien übernimmt JS Express die Scans eines Zustellers in Großbuchstaben, ohne den Zusteller oder sein Land zu nennen. Mit `SHIPMENT RECEIVED`, `PROCESSED AT HUB` und `SHIPMENT PROCESSED` hat sein Umschlagzentrum das Paket angenommen und bearbeitet, mit `DEPARTED FROM DEPOT` geht es von dort zum Depot, das zustellt; Peek zeigt jeweils „Unterwegs“. `RECEIVED AT LOCAL DEPOT` ist dieses Depot, vor jeder Tour und nach einem gescheiterten Zustellversuch noch einmal: das Depot des Zustellers, keine Abholstelle, deshalb behält Peek den Status der Zeile davor. `DELIVERY ATTEMPTED: 1ST DELIVERY ATTEMPT`, `DELIVERY ATTEMPTED: 2ND DELIVERY ATTEMPT` und `DELIVERY ATTEMPTED: 3RD DELIVERY ATTEMPT` sind gescheiterte Zustellversuche („Zustellversuch“). Nach dem dritten sollst du beim Zusteller eine Zustellung vereinbaren; `SHIPMENT REDIRECTED: ON CONSIGNEE'S REQUEST` heißt dann, dass das Paket zu der vereinbarten Zustellung unterwegs ist („Unterwegs“). `DELIVERY EXCEPTION` ist ein Problem im Umschlagzentrum, noch vor jeder Tour; `DELIVERY EXCEPTION, WAIT FOR INFORMATION CONFIRMATION` nach `ARRIVE AT THE WAREHOUSE` („Unterwegs“) heißt, dass der Zusteller das Paket zurückhält, bis Angaben dazu bestätigt sind. Peek zeigt beides als „Problem gemeldet“. Zeigt Peek keine Nummer eines Zustellers, frag den Shop, wer zustellt und was der Zusteller braucht.

**Zeilen, die keine Scans sind.** `SHIPPING INFORMATION RECEIVED`, `SHIPMENT CREATED`, `Data Received`, `Your package is being prepared by the sender and will be handed over to us soon` und, an einem Umschlagplatz in Europa, `Shipment Data Uploaded` mit `Processed` gleich danach sind Einträge eines Partners mit den Daten des Pakets, oft bevor er das Paket überhaupt hat. `Consignee Contacted` kommt im selben Moment wie ein gescheiterter Zustellversuch: Der Zusteller hat dich angerufen oder dir geschrieben. Auch diese Zeile ändert den Status nicht.

**Uhrzeiten durcheinander.** Die Zeiten stehen ohne Zeitzone da und folgen verschiedenen Uhren: Pekinger Zeit in China, an Flughäfen und bei den Zustellern meist die Ortszeit. Richte dich nach der Reihenfolge der Zeilen, nicht nach den Uhrzeiten. In Peek zeigen die deutschen Zeilen von J&T Express Germany dieselben Zeiten wie die Sendungsverfolgung von J&T Express Germany selbst.

## Wer JS-Express-Pakete in Deutschland, Österreich und der Schweiz zustellt

**Deutschland.** Im Verlauf von JS Express ist [DHL Paket](carrier:dhl) der Paketdienst vor Ort, mit DHLs Scans bis in deine Region (siehe oben). Dass DHL jedes JS-Express-Paket in Deutschland bringt, sagt JS Express aber nicht: Manche Pakete stellt [iMile](carrier:imile) aus einem deutschen Zentrum zu, andere [J&T Express](carrier:j-and-t-de) (siehe oben).

**Österreich und Schweiz.** Hier zeigt der Verlauf von JS Express, soweit Peek ihn kennt, keinen Zusteller. Der Datensatz von JS Express kann die Nummer des Paketdiensts vor Ort enthalten, aber nicht seinen Namen, und die Tracking-Seite von JS Express zeigt beides nicht. Peek nennt den Zusteller, wenn die Form dieser Nummer eindeutig zu einem Paketdienst in deinem Land passt. Ist es eine gültige [internationale Postnummer](guide:tracking-number-formats) (zwei Buchstaben, neun Ziffern, zwei Buchstaben), fragt Peek die Post deines Landes, also die [Österreichische Post](carrier:austrian-post) oder die [Schweizerische Post](carrier:swiss-post); mehr dazu unten. Nennt Peek keinen Zusteller, frag den Shop, wer zustellt.

Zustellversuche, Benachrichtigungen und Abholstellen regelt der Paketdienst, der zustellt. Eine Zeile `到达待取` nennt als Ort nur das Land: Schau in der Sendungsverfolgung des Zustellers nach oder frag den Shop, wo das Paket wartet.

## JS Express Paket kommt nicht an oder Tracking hängt

**`未获取到任何数据,请提供有效单号!`** So antwortet die englische Tracking-Seite von JS Express auf Chinesisch, wenn sie eine Nummer nicht kennt (keine Daten gefunden, bitte eine gültige Frachtbriefnummer angeben). `No track data for now!` heißt: noch keine Scans. Peek zeigt in beiden Fällen „Warten auf den Paketdienst“ und fragt weiter nach. Prüf die Nummer Zeichen für Zeichen gegen die Nachricht des Shops. Kommt nach ein paar Tagen nichts, frag den Shop, ob er das Paket wirklich verschickt hat.

**Hängt bei `Electronic notification of order creation received.`** JS Express hat das Paket noch nicht bekommen. Frag den Shop, wann er es übergeben hat.

**Hängt bei `Inbound to customs temporary warehouse`.** Das Paket wartet auf die Einfuhrabfertigung. Sollst du Einfuhrabgaben zahlen, hilft [Paket beim Zoll](guide:customs) weiter; liegt es tagelang dort, sag dem Shop Bescheid. Mehr unter [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating) und [Paket aus China verfolgen](guide:tracking-from-china).

**Nichts mehr nach `Delivery to local courier`.** Jetzt hat der Paketdienst vor Ort das Paket. Zeigt Peek „Zustellung durch“ mit seinem Namen und seiner Nummer, gib die Nummer in seine Sendungsverfolgung ein. Sonst frag den Shop, wer zustellt und unter welcher Nummer.

**Zugestellt, aber nichts da.** Frag zuerst den Paketdienst, der zugestellt hat, in Deutschland etwa DHL, dann den Shop. Siehe [Paket zugestellt, aber nicht erhalten](guide:delivered-not-received).

**Verloren, beschädigt oder zurück.** Kunde von JS Express ist der Verkäufer. Nach den Bedingungen von JS Express muss er Ansprüche innerhalb von 30 Tagen, nachdem JS Express das Paket erhalten hat, schriftlich anmelden; Schäden an versicherten Paketen meldet er über sein Kundenkonto. Wende dich also gleich an den Shop oder den Marktplatz und verlange eine Erstattung oder ein neues Paket, statt abzuwarten. Kann ein Paket nicht zugestellt werden (niemand da, Annahme verweigert, falsche Adresse, Zoll), schickt JS Express es gegen Gebühr zurück, wenn der Absender das will; sonst vernichtet es der Zoll im Zielland. Siehe [Paket verloren](guide:lost-parcel) und [Paket zurück an den Absender](guide:returned-to-sender).

## JS Express Kontakt und Kundenservice

Stand 2. November 2026 nennt die chinesische Kontaktseite von JS Express Folgendes. Die englische Kontaktseite ist älter und nennt noch andere Nummern und Adressen.

- **Hotline:** 400-0353-518, eine chinesische Servicenummer, auch für internationale Anfragen. Servicezeiten nennt JS Express nicht, ebenso wenig, ob die Nummer aus dem Ausland erreichbar ist.
- **QQ und WeChat:** Unternehmens-QQ 4000353518 und das offizielle WeChat-Konto js-express.
- **Beschwerden:** ein Formular auf der englischen Beschwerdeseite und dieselbe Hotline.
- **Standorte:** Hauptsitz und Lager in Suzhou, weitere Lager in China und Büros seines Netzwerks in Hongkong und in Walnut, Kalifornien.

Ansprüche gegenüber JS Express stellt der Verkäufer, der das Paket gebucht hat. Zu deiner Bestellung fragst du also den Shop, zur Zustellung den Paketdienst vor Ort.

## JS Express Paket verfolgen mit Peek

[Peek](/) erkennt eine JS-Express-Nummer an ihrer Form: JSE, zwei Buchstaben, zehn Ziffern und YQ. Kein anderer Paketdienst in Peeks Katalog nutzt diese Form, und mit der Nummer fragt Peek direkt bei JS Express nach. Hast du nur die Nummer des Zustellers, füg stattdessen die ein: Peek verfolgt mehr als 3.500 Paketdienste.

Peek liest die englische Tracking-Seite von JS Express und zeigt den Verlauf, jeden Scan mit seinem Ort und dazu das Zielland, das diese Seite nicht anzeigt. `到达待取` steht bei Peek auf Englisch als `Arrived, awaiting collection`, und auch die chinesische Meldung zur Adresse zeigt Peek auf Englisch. Namen von Personen, die ein Paket angenommen haben, und Telefonnummern lässt Peek weg. Ebenso Zustellfotos und die Bestellnummer des Shops, die der Datensatz von JS Express enthalten kann. Als Liefertermin zeigt Peek den Tag aus der Prognose von JS Express; sobald das Paket in Zustellung ist, auch mit Straightships `Package will be delivered before 9:00pm`, zeigt Peek keinen Tag mehr, denn „In Zustellung“ heißt schon: heute. Zeilen, die keine Scans sind, ändern den Status nicht.

Die Scans in China liest Peek in Pekinger Zeit, die deutschen Zeilen von J&T Express Germany mit dem UTC-Versatz, den jede Zeile angibt; bei den anderen leitet es die Zeitzone aus dem Ort oder dem Zielland ab. Alle zeigt es in der Zeitzone deines Geräts, den neuesten zuerst.

Passt die Form der Zustellernummer eindeutig zu einem einzigen Paketdienst, der in deinem Land arbeitet, zeigt Peek „Zustellung durch“ mit dem Namen dieses Paketdiensts und seiner Nummer und fragt auch dort nach. Solange Peek das Paket bei diesem Paketdienst nur angekündigt findet, noch ohne eigenen Scan, zeigt es stattdessen „Bei … angekündigt, noch nicht eingetroffen“. DHL-Nummern aus zwölf Ziffern oder aus JJD und 16 Ziffern nutzen auch andere Paketdienste: In Deutschland zählen sie nur, wenn der Verlauf von JS Express eine der Zeilen von DHL aus der Tabelle oben enthält. Eine internationale Postnummer geht an die Post deines Landes: DHL in Deutschland, die Österreichische Post in Österreich, die Schweizerische Post in der Schweiz. Sobald dieser Paketdienst einen Scan zeigt, der mindestens so neu ist wie der neueste von JS Express, verfolgt Peek das Paket dort weiter, zeigt beide Nummern und behält die Scans von JS Express im Verlauf. Bis dahin bleibt Peek bei JS Express, dessen Verlauf oft auch die Scans des Zustellers enthält.

Peek schaut alle 10 Minuten nach, alle 2 Minuten (alle 5, wenn Peek die Seite von JS Express im Browser öffnen muss), sobald das Paket „In Zustellung“ ist (am Tag der Zustelltour ab 6:00 Uhr Ortszeit und solange JS Express selbst antwortet; über Tracking-Dienste alle 15 Minuten). Übernimmt ein anderer Paketdienst die Zustellung, richtet sich Peek nach dem Takt dessen, der das Paket zustellt: stündlich bei Royal Mail und Poste Italiane, alle 30 Minuten bei PostNL und USPS, auch wenn das Paket in Zustellung ist. Nach zwei Tagen ohne neuen Scan (12 Stunden, solange es „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ zeigt) schaut Peek nur noch stündlich nach. Als Mitteilung wählst du „Jeden Scan“, „Wichtige Schritte“ (Zoll, Probleme, Zustellversuche, „Abholbereit“, „In Zustellung“, die Zustellung und eine Rücksendung) oder „Nur die Zustellung“. JS Express oder den Shop kontaktieren kann Peek nicht für dich.

## Fragen zur JS Express Sendungsverfolgung

### Was ist JS Express (急速国际)?

Ein chinesisches Logistikunternehmen für Händler, die grenzüberschreitend online verkaufen, betrieben von 急创速供应链管理（苏州）有限公司. Nach eigenen Angaben 2010 in Hongkong eingetragen und seit 2011 mit einem Betriebszentrum in Suzhou.

### Wo kann ich ein JS-Express-Paket verfolgen?

Oben im Feld oder auf der englischen Seite „Express Query“ von JS Express auf js-exp.com. Die chinesische Tracking-Seite verlangt vorher einen Bildcode.

### Was bedeutet 未获取到任何数据 bei JS Express?

Keine Daten gefunden: Die Tracking-Seite kennt die Nummer nicht, wegen eines Tippfehlers oder weil JS Express das Paket noch nicht erfasst hat. Prüf die Nummer mit dem Shop.

### Wer stellt JS-Express-Pakete in Deutschland zu?

Ein Paketdienst vor Ort: In Deutschland stehen im Verlauf von JS Express die Scans von DHL, iMile oder J&T Express. Für Österreich und die Schweiz kennt Peek aus dem Verlauf keinen Zusteller, dort fragst du den Shop. Steht im Datensatz eine internationale Postnummer, fragt Peek selbst bei der Post deines Landes nach.

### Wen frage ich, wenn mein JS-Express-Paket nicht ankommt?

Den Shop, bei dem du bestellt hast: Er hat das Paket bei JS Express gebucht und stellt die Ansprüche. Die Hotline von JS Express, 400-0353-518, ist eine chinesische Nummer.

:::sources
- [JS Express: 急速国际 (Startseite)](https://www.js-exp.com/) – Betreiberfirma
- [JS Express: 企业介绍 (Unternehmen)](https://www.js-exp.com/index.php?page=6) – Hongkong 2010, Suzhou 2011, grenzüberschreitender Onlinehandel, Plattformen
- [JS Express: Express Query](https://www.js-exp.com/EN/index.php?page=19) – englische Tracking-Seite, Formular ohne Link, was sie zeigt, ihre Antworten
- [JS Express: 快件查询 (Sendungsverfolgung)](https://www.js-exp.com/index.php?page=19) – Bildcode
- [JS Express: 投保理赔 (Versicherung und Schadensfälle)](https://www.js-exp.com/index.php?page=29) – schriftlicher Antrag des Kunden binnen 30 Tagen ab Erhalt des Pakets, Kundenkonto, unzustellbare Pakete
- [JS Express: Complaint](https://www.js-exp.com/EN/index.php?page=32) – Formular, Hotline
- [JS Express: Contact Us](https://www.js-exp.com/EN/index.php?page=35) – ältere Nummern und Adressen
- [JS Express: 联系我们 (Kontakt)](https://www.js-exp.com/index.php?page=35) – Hotline auch für internationale Anfragen, QQ, WeChat, Standorte
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Nummernform, Wortlaute und ihre Stufen, DHL-, iMile- und J&T-Express-Scans in Deutschland, Scans eines ungenannten Zustellers (Umschlagzentrum, Depot, Zustellversuche, Umleitung, Probleme), Uhrzeiten (seit 9. November die Zeilen von J&T Express Germany mit ihrem angegebenen UTC-Versatz), Browser-Abfrage, Nummer des Zustellers und welche Formen Peek einem Paketdienst zuordnet, was Peek zeigt und wie es übergibt
:::
