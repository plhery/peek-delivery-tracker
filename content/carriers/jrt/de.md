---
title: JRT Sendungsverfolgung: JRT…MM-Nummer, Flüge und DHL
description: JRT-Paket (今日通) aus Xiamen verfolgen: wie die JRT…MM-Nummer aussieht, was die chinesischen und englischen Schritte heißen und wer zustellt.
slug: jrt-sendungsverfolgung
published: 2026-11-03
updated: 2026-11-13
---

JRT (今日通, Jinritong) ist 厦门今日通商贸有限公司, ein Spediteur aus Xiamen in China. Hat ein Shop in China deine Bestellung mit JRT verschickt, bekommst du eine Nummer, die mit JRT beginnt und auf MM endet: Füg sie oben ins Feld ein. JRTs Tracking-Seite zeigt den Weg des Pakets von China über den Flug und den Zoll bis zur Firma, die zustellt, und übernimmt deren Scans: bei Sendungen nach Europa die von DHL, bei Sendungen in die USA die von UPS. Auf JRTs FedEx-Linie nach Irland übernimmt FedEx die letzte Strecke, auf seiner Linie nach Spanien CTT Express, auf der nach Polen InPost und auf der nach Mexiko J&T Express Mexiko.

## JRT Sendungsnummer: so sieht sie aus

Die Frachtbriefnummern von JRT bestehen aus JRT, zehn Ziffern und MM, zusammen 15 Zeichen; JRT sind die Initialen von 今日通. Mit erfundenen Ziffern:

| Nummer | Was sie ist |
| --- | --- |
| `JRT0000000000MM` | JRT, zehn Ziffern und MM: eine Frachtbriefnummer von JRT. |
| `JRT000000000MM` | Nur neun Ziffern: So sehen die Nummern von JRT nicht aus. Prüf, ob eine Ziffer fehlt. |
| `JRTO0000000000MM` | Ein Buchstabe O nach JRT: Peek liest die Nummer als `JRT0000000000MM`, denn nur so kennt sie JRTs Tracking-Seite. |
| `UP0000000000000000` | UP und sechzehn Ziffern: eine Referenz des Versenders, unter der JRT eine Bestellung finden kann. Dass nur Versender von JRT diese Form nutzen, ist nicht belegt. |
| `RR000000005CN` | Zwei Buchstaben, neun Ziffern und CN: eine Nummer der [China Post](carrier:china-post), nicht von JRT. |

Leerzeichen, Bindestriche und Kleinbuchstaben in einer JRT…MM-Nummer stören Peek nicht. Findest du die Nummer nicht, lies, [wo du die Sendungsnummer findest](guide:find-tracking-number). JRTs Tracking-Seite findet eine Bestellung auch über die eigene Referenz des Shops, wenn du sie genau so eingibst, wie der Shop sie geschrieben hat, samt Bindestrichen.

**Ein Link mit Zahlen statt Namen.** JRTs Tracking-Seite läuft nur unter einer IP-Adresse statt unter einer Domain, über unverschlüsseltes HTTP, und eine andere Website von JRT kennt Peek nicht. Ein Link dorthin hat an der Stelle des Website-Namens diese Zahlenadresse und enthält `trackIndex.htm`, `documentCode=` und eine Nummer oder Referenz: Du kannst ihn so, wie er ist, in Peek einfügen. Peek selbst verlinkt die Tracking-Seite nicht, denn über einen unverschlüsselten Link ginge deine Nummer im Klartext durchs Netz.

## JRT Sendungsverfolgung: was die Status bedeuten

Viele Zeilen von JRT sind auf Chinesisch, gefolgt von JRTs eigenem Englisch, und Peek zeigt beide Teile. In der Reihenfolge, in der ein Paket sie bekommt:

| JRT schreibt | Was es für dich heißt | Peek zeigt |
| --- | --- | --- |
| `货物电子信息已经收到` | Die elektronischen Warendaten sind eingegangen: Der Shop hat die Bestellung angelegt. Die Zeile schreibt Hualei (华磊), die Plattform hinter JRTs Tracking-Seite, von selbst. Ein Scan ist sie nicht. | „Etikett erstellt“ |
| `Shipment information received` | Die Daten der Sendung sind bei JRT angekommen. Gescannt hat das Paket noch niemand. | „Etikett erstellt“ |
| `到达收货点` | Am Annahmepunkt von JRT angekommen: der erste Scan in JRTs Händen. | „Beim Paketdienst eingegangen“ |
| `货物离开操作中心` | Das Paket hat JRTs Umschlagzentrum verlassen. | „Unterwegs“ |
| `深圳集散中心 已收货` | JRTs Verteilzentrum in Shenzhen hat das Paket übernommen, nach dem Umschlagzentrum und vor dem Flug. Peek zeigt die Zeile auf Englisch: „Received at the Shenzhen distribution center“. | „Unterwegs“ |
| `货物打包完成，已发往航司` | Verpackt und auf dem Weg zur Fluggesellschaft, vor dem Flug. Peek zeigt: „Goods packed and sent to the airline“. | „Unterwegs“ |
| `抵达航司已起飞` | Bei der Fluggesellschaft, und der Flug ist von dem Flughafen gestartet, den die Zeile nennt. Peek zeigt: „Arrived at the airline, flight departed“. | „Unterwegs“ |
| `货物已交机场,等待航班安排中 Cargo delivered to airport, pending flight arrangement` | Am Flughafen abgegeben, wartet auf einen Flug. `delivered` heißt hier: an den Flughafen, nicht an dich. | „Unterwegs“ |
| `报关已放行等待…号航班起飞 Customs released,Awaiting departure on flight of the …` | In China für die Ausfuhr verzollt, wartet auf den Flug; dessen Tag steht an der Stelle der Punkte. | „Unterwegs“ |
| `航班起飞，以实际抵达为准 Flight departure subject to actual arrival time` | Der Flug ist gestartet. | „Unterwegs“ |
| `航班落地，拆板中 Flight landing, dismantling board in progress` | Gelandet. Vor dem Zoll werden die Paletten zerlegt. | „Unterwegs“ |
| `机场内等待提货，等待清关 Waiting for cargo pickup and customs clearance at the airport.` | Am Zielflughafen, wartet auf Abholung und Einfuhrverzollung. | „Beim Zoll“ |
| `已提货，清关中 The goods have been picked up and are currently undergoing customs clearance` | Die Einfuhrverzollung läuft. | „Beim Zoll“ |
| `清关完成，卡车发运 Customs clearance completed; truck dispatched.` | Verzollt und auf einem Lkw unterwegs. | „Unterwegs“ |
| `已交服务商 Delivered to service provider` | An die Firma übergeben, die die letzte Strecke fährt. Zugestellt ist das Paket damit nicht. | „Unterwegs“ |

Einige Zeilen schreibt JRT nur auf Chinesisch, und Peek zeigt sie in dem Englisch, das andere Spediteure dafür verwenden: `货物电子信息已经收到` als „The electronic cargo information has been received“, `到达收货点` als „Arrived at the receiving point“, `货物离开操作中心` als „Goods leave the operation center“ und `快件操作完成` als „Shipment departed from facility“. Die Flughafenzeile mit dem gebuchten Flug, `货物已交机场,预计…号的航班 Goods delivered to airport, scheduled on flight of the …`, nennt den Tag des Flugs in beiden Hälften. Peek zeigt sie als „Goods delivered to airport“, weil der Flug noch bevorsteht.

**Auf der Linie nach Europa** kommen drei spätere Zeilen ebenfalls nur auf Chinesisch, und Peek zeigt sie auf Englisch, alle als „Unterwegs“: `航班已抵达` als „Flight has arrived“ (die Landung an dem europäischen Flughafen, den die Zeile nennt), `清关完成，中转德国中` als „Customs cleared, in transit to Germany“ (am Ankunftsflughafen verzollt, unterwegs zur Übergabe in Deutschland) und `抵达德国，已交服务商` als „Arrived in Germany, handed to the delivering carrier“ (an die Firma übergeben, die zustellt, etwa DHL; zugestellt ist das Paket damit nicht).

**Auf dem Landweg durch Kasachstan.** Auf JRTs Straßenroute nach Europa verlässt das Paket China über Land statt per Flugzeug. Die vier Zeilen dieser Route schreibt JRT nur auf Chinesisch, und Peek zeigt sie auf Englisch, alle als „Unterwegs“: `已装柜发车，待抵达新疆` als „Loaded into a container and on its way to Xinjiang“, `已抵达新疆，换装/报关，待出境` als „Arrived in Xinjiang for transloading and export declaration, waiting to leave China“, `因哈萨克斯坦拥堵，外车入境新疆拥堵缓慢，在等待外车倒装后报关出境。` als „Congestion in Kazakhstan is slowing foreign trucks into Xinjiang; the parcel will be declared for export once it is reloaded onto one“ (eine Wartezeit an der Grenze, kein verlorenes Paket) und `已出境，哈萨克斯坦阿拉木图` als „Left China, Almaty, Kazakhstan“.

**Zeilen nur auf Englisch.** Andere Zeilen schreibt JRT nur auf Englisch. Peek zeigt sie alle als „Unterwegs“, wenn nicht anders angegeben:

- `Shipment arrived at facility`, `Sending to the airport`, `The goods have been packed and sent to the airline`, `Package has been packed and delivered to airport`, `Cargo handed over to the airline`: auf dem Weg zum Flug. `Domestic customs clearance completed` ist die Ausfuhrverzollung in China, nicht der Zoll in deinem Land.
- `Arriving at the airport - arranging flights`, `Arriving at the airline, in the process of booking a flight`: Ein Flug wird gebucht.
- `Flight is expected to take off on …`, `The flight is expected to depart on … and land on …`, `Estimated departure is …` und `Estimated arrival date is …`, mit Daten an der Stelle der Punkte: Der Flug ist gebucht.
- `Flight has flown`, `The flight has departed and is expected to … land`, `The flight has taken off`: in der Luft.
- `Sorry, the second flight has been delayed and is expected to … land`: Der Anschlussflug hat Verspätung, nachdem der erste gestartet ist; an der Stelle der Punkte steht der neue Landetag.
- `During transportation to …`, mit dem Zielland an der Stelle der Punkte: unterwegs dorthin.
- `Flight has arrived`, `The flight has arrived and is awaiting dismantling`, `Arrived at Destinated Airport`, `The NOA has been received` (die Ankunftsmeldung der Fluggesellschaft), `Dismantling and picking up goods in progress`: gelandet, vor dem Zoll.
- `Customs clearance of the cargo is in progress` und `Arrived at customs clearance warehouse` (im Zolllager am Ziel): „Beim Zoll“. Danach `Customs clearance completed` oder `Customs clearance in destination country completed`.
- `In transit to final service provider`, `Delivery to final service provider` und `The goods have been handed over to the carrier`: unterwegs zur Firma, die zustellt, oder an sie übergeben. Zugestellt ist das Paket damit nicht.
- `The cargo has been picked up and arrived at the warehouse，awaiting outbound shipment` (in JRTs Lager im Zielland) und `It has been handed over to the service provider.` (an die Firma übergeben, die zustellt, etwa FedEx): nach dem Zoll, kein erster Scan.

In den drei Zeilen oben, die „land“ enthalten, sind die Daten als Monat.Tag geschrieben: 11.12 ist der 12. November.

**Zeilen mit Schrägstrich.** Bei manchen Paketen verbindet JRT Chinesisch und Englisch mit einem Schrägstrich, in Peek alle „Unterwegs“: `预计…航班/Flight scheduled for …` (der Flug ist gebucht, sein Tag steht in beiden Hälften an der Stelle der Punkte), `航班已启运/Package in transit to destination country` (in der Luft) und `航班已落地，拆板中/Package arrived at destination country` (gelandet, vor dem Zoll). Davor meldet `国内清关完成` die Ausfuhrverzollung in China, nicht den Zoll in deinem Land („Unterwegs“). `预计配载航班…月…日,Expected flight on …`, mit Komma statt Schrägstrich, nennt den Flug, auf den das Paket verladen werden soll, mit seinem Tag in beiden Hälften an der Stelle der Punkte (`11月11日` und `November 11th` für den 11. November): Der Flug steht noch bevor („Unterwegs“), und Peek nimmt diesen Tag nicht als Liefertermin. `航班已起飞,The flight has taken off from the origin`, ebenfalls mit Komma: Der Flug ist gestartet („Unterwegs“).

**Zeilen von DHL.** Auf der Linie nach Europa übernimmt JRTs Tracking-Seite danach die Scans von DHL, meist kleingeschrieben:

| DHL schreibt (bei JRT) | Was es für dich heißt | Peek zeigt |
| --- | --- | --- |
| `the instruction data for this shipment have been provided by the sender to dhl electronically` | DHL hat die Daten des Etiketts. | „Etikett erstellt“ |
| `shipment in transit to dhl` | Auf dem Weg zu DHL, vor dem Flug. | „Unterwegs“ |
| `A preferred location was designated as a reception option for this shipment.` | Der Ort, den du für die Zustellung gewählt hast, festgehalten, während das Paket noch auf dem Weg zu DHL ist. | Status bleibt, wie er war |
| `The shipment has been posted by the sender at the retail outlet` | JRT hat das Paket in einer Filiale der Deutschen Post eingeliefert. | „Unterwegs“ |
| `pick-up was successful.` | DHL hat das Paket nach JRTs Übergabe abgeholt. Gemeint ist nicht deine Abholung. | „Unterwegs“ |
| `the shipment has been processed in the parcel center of origin`, `loaded to movement / tour vehicle` | Bei DHL unterwegs. | „Unterwegs“ |
| `The shipment has arrived in the destination country/destination area.`, `The shipment has left the import parcel center in the destination country/destination area.` | Im Zielland angekommen, dann aus DHLs Paketzentrum für die Einfuhr weitergeschickt. | „Unterwegs“ |
| `The shipment is being prepared for delivery in the delivery depot` | Das Zustelldepot bereitet die Tour vor. Losgefahren ist sie noch nicht. | „Unterwegs“ |
| `The shipment has been loaded onto the delivery vehicle` | Ins Zustellfahrzeug geladen: Dein Paket ist auf der Zustelltour. | „In Zustellung“ |
| `not delivered-consignee could not be reached`, `The shipment could not be delivered. Please find more information here.` | Die Zustellung hat nicht geklappt; beim ersten Wortlaut war niemand da. | „Zustellversuch“ |
| `The recipient was not present. Please find more information here.` | Du warst nicht da, als DHL zustellen wollte. „Please find more information here“ ist der Text von DHLs Hilfelink. | „Zustellversuch“ |
| `not delivered-bad address` | Der Zusteller hat die Adresse nicht gefunden. Prüf die Adresse in deiner Bestellung und sag dem Shop sofort Bescheid. | „Zustellversuch“ |
| `held for consignee collection` | Liegt in einer Abholstelle für dich bereit. | „Abholbereit“ |
| `The shipment is ready for collection from a ParcelShop/retail outlet. Please find more information here.` | Liegt in einem Paketshop oder einer Filiale zur Abholung für dich bereit. | „Abholbereit“ |
| `shipment will be returned to origin-not ordered` | Als nicht bestellt abgelehnt, das Paket geht zurück. | „Zurück an Absender“ |
| `shipment will be returned to origin-bad address` | Die Adresse war nicht zu finden, das Paket geht zurück. Bitte den Shop um Erstattung oder ein neues Paket. | „Zurück an Absender“ |
| `the shipment has been successfully delivered` | Zugestellt. Folgt die Zeile auf eine Rücksendung, ist das Paket wieder beim Absender. | „Zugestellt“, nach einer Rücksendung „Zurück an Absender“ |

**Zeilen von FedEx.** Manche Pakete beginnen stattdessen mit `Shipment information sent to FedEx`: Die Daten des Etiketts sind an FedEx gegangen, das die letzte Strecke fährt („Etikett erstellt“). Danach übernimmt die Tracking-Seite FedEx' Scans auf Englisch, etwa `Arrived at FedEx hub`, `Departed FedEx hub` und `On the way`, alle „Unterwegs“. `Delivery option requested, hold request received - check back later for shipment status`, ebenfalls von FedEx: Der Empfänger hat FedEx gebeten, das Paket zurückzuhalten, und FedEx hat die Bitte erhalten („Unterwegs“).

**In die USA** schreibt JRT nur auf Chinesisch, und Peek zeigt die Zeilen auf Englisch, alle als „Unterwegs“: `…号航班` mit Monat und Tag des Flugs an der Stelle der Punkte (`10/22号航班` ist der 22. Oktober) als „Booked on a flight“, `航班已飞` als „Flight has flown“, `航班抵达` als „Flight has arrived“, `提货中` als „Picking up the goods“ (am Flughafen, nach der Landung und vor dem Zoll), `清关完成，已提货，待交仓` als „Customs clearance completed, goods picked up, awaiting hand-over to the warehouse“ und `已交仓` als „Handed over to the warehouse“: im Lager der Firma, die zustellt, vor dem ersten Scan von UPS in einem seiner Standorte. Danach übernimmt die Tracking-Seite die Scans von UPS auf Chinesisch, und Peek zeigt sie in UPS' eigenem Englisch: `发件人已创建标签，但是 UPS 尚未收到包裹。` als „Shipper created a label, UPS has not received the package yet.“ („Etikett erstellt“); `抵达设施` („Arrived at Facility“), `离开设施` („Departed from Facility“), `UPS 设施处理` („Processing at UPS Facility“) und `已提取` („Picked Up“, zwischen zwei Standort-Scans von UPS, keine Abholung durch dich), alle „Unterwegs“; `已装上递送车辆` („Loaded on Delivery Vehicle“) und `准备今天递送` („Out For Delivery Today“) als „In Zustellung“; `已递送` („Delivered“) als „Zugestellt“.

**Nach Mexiko** übernimmt die Tracking-Seite nach dem Zoll die Zeilen von J&T Express Mexiko auf Spanisch, jeweils mit dem Ort und dem Standort von J&T in 【】 (hier 【…】), in Peek bis zur Zustelltour alle „Unterwegs“: `【…】【…】¡Recolección exitosa!. El mensajero de J&T asignado … recolectó tu envío.` (J&Ts Kurier, dessen Name an der Stelle der Punkte steht, hat das verzollte Paket abgeholt: ein Schritt unterwegs, weder der erste Scan deines Pakets noch eine Abholung durch dich; JRTs Tracking-Seite zeigt das ¡ manchmal als ?, und dahinter kann J&Ts Telefonnummer stehen), dann `【…】Tu envío ha llegado a【…】` (an einem Standort von J&T angekommen) und `【…】Tu envío ha salido de【…】con destino a【…】` (von einem Standort zum nächsten losgeschickt). Ist das Paket an dem Standort von J&T angekommen, der zustellt, heißt `【…】【…】El mensajero de J&T Express … está en camino a tu domicilio.`, dass J&Ts Kurier zu dir nach Hause unterwegs ist: „In Zustellung“ (hinter dem Satz kann weiterer Text stehen).

**Uhrzeiten.** JRTs eigene Zeilen stehen in Pekinger Zeit (UTC+8), ohne Angabe der Zeitzone: Steht dort 17:00 Uhr, ist es in Deutschland, Österreich und der Schweiz 10:00 Uhr (MEZ), während der Sommerzeit 11:00 Uhr (MESZ). Die Zeilen, die JRT von DHL übernimmt, stehen dagegen in DHLs Ortszeit: die, die mit `The shipment …`, `The international shipment …`, `the instruction data …` oder `A preferred location …` beginnen, ob groß- oder kleingeschrieben, die Zeilen zum `movement / tour vehicle`, `pick-up was successful.`, `not delivered-consignee …`, `not delivered-bad address`, `held for consignee collection` und `shipment will be returned to origin …`. Bei Paketen nach Deutschland, Österreich und in die Schweiz ist das MEZ oder MESZ, und so liest Peek sie auch. Die übernommenen UPS-Scans und Zeilen mit einem Ort in den USA liest Peek in der Ortszeit dieses Orts. Die übernommenen Zeilen von J&T Express Mexiko stehen in J&Ts Ortszeit in Mexiko-Stadt, wo es 14 Stunden früher ist als in Peking, und so liest Peek sie auch; JRTs eigene Zeilen auf dieser Linie bleiben in Pekinger Zeit.

## Wer JRT-Pakete zustellt

**Nach Deutschland** endet JRTs Linie bei DHL: JRT liefert das Paket in einer Filiale der Deutschen Post ein, und die Tracking-Seite übernimmt die Scans von DHL bis zur Zustellung. Sobald JRT das Paket abgegeben hat, steht in JRTs Datensatz die Nummer von DHL statt der von JRT, aber weiterhin kein Name der Linie. Erkennt Peek die Nummer sicher als DHL-Nummer, zeigt es „Zustellung durch DHL“ mit der Nummer (oder „Bei DHL angekündigt, noch nicht eingetroffen“, solange es bei DHL nur angekündigt ist, noch ohne eigenen Scan) und wechselt zu DHLs eigener Sendungsverfolgung, sobald DHL das Paket gescannt hat (mehr unten). Sonst frag den Shop nach der DHL-Nummer: Mit ihr verfolgst du das Paket bei DHL bis zur Zustellung. Zustellversuche, Filialen, Packstationen und Lagerfristen regelt dann DHL, siehe [DHL Paket](carrier:dhl).

**Nach Österreich und in die Schweiz** kann JRTs Linie nach Europa ebenfalls bei DHL enden. Zugestellt wird dann von DHL oder einem Partner von DHL: Wer das ist, sagt nicht JRTs Datensatz, sondern DHLs Sendungsverfolgung. Manche Linien von JRT enden dagegen bei der [Schweizerischen Post](carrier:swiss-post). An sie übergibt Peek ein JRT-Paket, wenn JRTs Datensatz sie als Linie nennt, eine Nummer der Schweizerischen Post enthält und kein Zielland außerhalb der Schweiz und Liechtensteins angibt. Das Paket zeigt dann „Zustellung durch Swiss Post“, oder „Bei Swiss Post angekündigt, noch nicht eingetroffen“, solange es bei der Post nur angekündigt ist. Steht im Datensatz statt JRTs Nummer eine internationale Postnummer, die nicht von DHL ist (zwei Buchstaben, neun Ziffern, zwei Buchstaben, wie die von China Post oben), fragt Peek damit die Post des Ziellands: DHL in Deutschland, die [Österreichische Post](carrier:austrian-post) in Österreich und die Schweizerische Post in der Schweiz.

**In die USA** endet JRTs Linie bei UPS: JRT übergibt das Paket im Lager der Firma, die zustellt (`已交仓`), und die Tracking-Seite übernimmt die Scans von UPS bis zur Zustellung.

**Nach Mexiko** endet JRTs Linie bei J&T Express Mexiko, dessen Scans auf Spanisch die Tracking-Seite übernimmt (oben).

**Auf der FedEx-Linie nach Irland**, deren Produktname FedEx enthält, übergibt JRT das Paket an FedEx, und im Datensatz steht dann die Nummer von FedEx statt der von JRT. Hat sie eine der Formen von FedEx, zeigt Peek „Zustellung durch FedEx“ mit der Nummer (oder „Bei FedEx angekündigt, noch nicht eingetroffen“, solange es bei FedEx nur angekündigt ist) und wechselt wie bei DHL zu FedEx, sobald FedEx das Paket gescannt hat. Stellt FedEx zu und zeigt Peek keine FedEx-Nummer, frag den Shop danach und verfolge sie bei [FedEx](carrier:fedex).

**Anderswo** weiß Peek nicht, wer JRTs Pakete zustellt. Frag den Shop, welche Firma deine Bestellung bringt und unter welcher Nummer: Zustellversuche, Abholstellen und Lagerfristen regelt diese Firma.

**Zoll.** Was du an Einfuhrabgaben zahlen musst und wer sie in Deutschland, Österreich und der Schweiz verlangt, steht unter [Paket beim Zoll](guide:customs).

## JRT Paket kommt nicht an oder Tracking hängt

**Nur die ersten Zeilen.** `货物电子信息已经收到`, `Shipment information received` oder eine Etikettzeile von DHL oder FedEx allein heißen: Der Shop hat das Paket gebucht, aber niemand hat es gescannt. Tut sich nach ein paar Tagen nichts, frag den Shop, ob er es wirklich verschickt hat.

**Warten auf den Flug.** Eine Flugzeile nennt den Tag, für den der Flug gebucht ist, und `航班延误，预计…号飞出 Flight delayed, estimated departure on …` einen neuen. Vergeht dieser Tag ohne eine Zeile zum Abflug, sag dem Shop Bescheid.

**Nichts mehr nach `已交服务商`, `Delivery to final service provider` oder `已交仓`.** JRTs Teil ist erledigt. Solange die Tracking-Seite keine Scans von DHL, UPS, FedEx oder J&T Express Mexiko übernimmt und Peek keine Nummer von DHL oder FedEx zeigt, stehen die nächsten Schritte in der Sendungsverfolgung der Firma, die zustellt: Frag den Shop nach deren Nummer. Mehr unter [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating) und [Paket aus China verfolgen](guide:tracking-from-china).

**Nichts gefunden.** Kennt JRTs Tracking-Seite eine Nummer nicht, zeigt sie ein einziges Ergebnis mit deiner Nummer unter 参考号 (Referenz) und alle anderen Felder leer. Peek zeigt dann „Warten auf den Paketdienst“ und fragt weiter nach (bei einer UP-Referenz, die Peek selbst zuordnen sollte: „Warten auf den ersten Scan von JRT“). Vergleich die Nummer Zeichen für Zeichen mit der Nachricht des Shops.

**Zugestellt, aber nichts angekommen.** Den Zustellscan schreibt die Firma, die zustellt, nicht JRT: Frag dort nach und sag dann dem Shop Bescheid. Siehe [Paket zugestellt, aber nicht erhalten](guide:delivered-not-received).

**Verloren oder zurück.** Den Versand bei JRT hat der Shop gebucht, also bittest du ihn oder den Marktplatz um Erstattung oder ein neues Paket. Siehe [Paket verloren](guide:lost-parcel) und [Paket zurück an den Absender](guide:returned-to-sender).

## JRT Kontakt und Kundenservice

Stand 3. November 2026 hat Peek keine Kontaktdaten von JRT gefunden: keine Website außer der Tracking-Seite und keine Telefonnummer, E-Mail-Adresse oder Anschrift für Empfänger. Der Shop hat den Versand bei JRT gebucht und ist für deine Bestellung zuständig, und wenn er nicht antwortet, der Marktplatz.

## JRT Paket verfolgen mit Peek

[Peek](/) erkennt eine JRT-Nummer an ihrer Form: JRT, zehn Ziffern und MM, die kein anderer Paketdienst in Peeks Katalog nutzt. Auch einen Link zu JRTs Tracking-Seite (oben) erkennt es. Bei einer UP-Referenz mit sechzehn Ziffern fragt Peek bei JRTs Tracking-Seite nach („Tracking-Dienste werden geprüft…“) und wählt JRT, wenn sie die Referenz kennt („JRT hat dieses Paket.“). Jede andere Referenz hat keine Form, an der Peek sie erkennen könnte: Füg sie trotzdem ein, Peek verfolgt mehr als 3.500 Paketdienste. Tipp dann auf „Ändern“ und wähl JRT (auch über 今日通 oder Jinritong findest du JRT), oder nimm „Paketdienst ändern“ bei einem Paket, das du schon hinzugefügt hast. Verfolgst du eine Referenz, zeigt Peek danach die JRT-Nummer unter „JRT führt die Sendung unter“, bis JRT das Paket übergibt und im Datensatz stattdessen die Nummer der Firma steht, die zustellt. Peek entfernt allerdings Bindestriche, deshalb findet es eine Referenz mit Bindestrichen nicht: Frag den Shop nach der JRT…MM-Nummer.

Peek zeigt den Status, jeden Schritt mit seinem Ort und, wenn JRTs Datensatz es nennt, das Zielland. Den Status liest Peek aus den Schritten, nicht aus der Zusammenfassung des Datensatzes. Die Uhrzeiten liest es wie oben beschrieben und zeigt sie in der Zeitzone deines Geräts. Peek hält jeden Schritt zurück, der mehr als zwölf Stunden vor seiner eigenen Uhrzeit geschrieben wurde, und jeden, dessen Uhrzeit nach der Abfrage liegt, gemessen in Pekinger Zeit. Namen, Adresse, Postleitzahlen, Gewicht, Gebühren, Unterschrift und Zustellnachweis lässt Peek weg. Einen voraussichtlichen Liefertermin zeigt es nicht, weil JRT keinen nennt.

Steht in JRTs Datensatz statt JRTs Nummer die Nummer der Firma, die zustellt, fragt Peek in den oben beschriebenen Fällen DHL, FedEx, die Schweizerische Post oder die Post des Ziellands, sonst den einzigen Paketdienst, zu dessen Nummernform sie sicher passt. Sobald diese Firma einen Scan zeigt, der mindestens so neu ist wie der neueste von JRT, verfolgt Peek das Paket dort weiter und behält JRTs Schritte im Verlauf. Einen Tag lang fragt Peek daneben auch noch bei JRT nach, höchstens alle 55 Minuten. Bis zu diesem Wechsel bleibt Peek bei JRTs Tracking-Seite und den Scans von DHL, UPS oder FedEx, die sie übernimmt. Gibt dir der Shop eine andere Nummer der Firma, die zustellt, füg sie als eigenes Paket hinzu.

Peek schaut alle 10 Minuten nach. Kommt zwei Tage lang nichts Neues (solange das Paket noch „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ zeigt, schon nach 12 Stunden), schaut Peek nur noch stündlich nach. Ist das Paket „In Zustellung“, schaut Peek am Tag der Zustelltour ab 6:00 Uhr Ortszeit alle 2 Minuten nach, solange JRT oder der übernehmende Paketdienst selbst antwortet (alle 5 Minuten, solange dessen Abfrage einen Browser braucht, wie es bei DHL, FedEx und UPS sein kann); über Tracking-Dienste alle 15 Minuten. Diesen Status setzen die oben mit „In Zustellung“ genannten Zeilen: auf der Linie nach Europa die von DHL, auf der Linie in die USA die von UPS und auf der Linie nach Mexiko die von J&T Express Mexiko; nach einer Übergabe tut das die Sendungsverfolgung des übernehmenden Paketdiensts.

Bei den Mitteilungen wählst du „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“. „Wichtige Schritte“ (Zustellung, Abholung, Zoll, fehlgeschlagene Zustellversuche und Rücksendungen) meldet dir JRTs Zeilen mit „Beim Zoll“, die von DHL übernommenen Zeilen zu Zustellfahrt, Zustellversuch, Abholstelle, Zustellung und Rücksendung, die von UPS übernommenen zur Zustellfahrt und Zustellung und die von J&T Express Mexiko übernommene Zeile `…está en camino a tu domicilio.`, nicht aber JRTs Flüge, `已交服务商` oder `已交仓`. JRT oder den Shop kontaktieren kann Peek nicht für dich.

## Fragen zur JRT Sendungsverfolgung

### Was ist JRT (今日通)?

厦门今日通商贸有限公司 (Xiamen Jinritong), ein Spediteur aus Xiamen in China. Seine Frachtbriefnummern bestehen aus JRT, zehn Ziffern und MM, und seine Tracking-Seite läuft auf Hualei (华磊), einer Plattform, die auch andere chinesische Spediteure nutzen.

### Warum zeigt JRT mein Paket als zugestellt, obwohl es nicht da ist?

Wegen JRTs Übersetzungen. `Cargo delivered to airport` heißt, dass JRT das Paket für den Flug am Flughafen abgegeben hat, und `Delivered to service provider` oder `Delivery to final service provider`, dass es an die Firma übergeben ist, die zustellt. Keine dieser Zeilen ist eine Zustellung an dich, und Peek zeigt dafür „Unterwegs“.

### Wer stellt JRT-Pakete in Deutschland, Österreich und der Schweiz zu?

JRTs Linie nach Europa endet bei DHL in Deutschland, und JRTs Tracking-Seite übernimmt die Scans von DHL. In Deutschland stellt DHL zu, in Österreich und der Schweiz DHL oder ein Partner, den DHLs Sendungsverfolgung nennt. Manche Linien in die Schweiz enden bei der Schweizerischen Post: Nennt JRTs Datensatz sie als Linie und eine ihrer Nummern, übergibt Peek ein Paket in die Schweiz oder nach Liechtenstein dorthin.

### Wen frage ich, wenn mein JRT-Paket fehlt?

Den Shop, bei dem du bestellt hast: Er hat den Versand bei JRT gebucht. Eine Website oder Kontaktdaten von JRT außer der Tracking-Seite kennt Peek nicht.

:::sources
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Firmenname und Initialen, Hualei-Plattform, Tracking-Seite unter einer IP-Adresse über unverschlüsseltes HTTP, ihre Links und kein veröffentlichter Link, JRT…MM- und UP-Form, ein O nach JRT als JRT gelesen, Suche nach Nummer oder Referenz mit Bindestrichen, Wortlaute der Zeilen und ihre Phasen, das Englisch für chinesische Zeilen, die chinesischen Zeilen nach Europa von der Landung bis zur Übergabe in Deutschland, von DHL übernommene Zeilen und die Filiale der Deutschen Post, die Linie in die USA und die übernommenen UPS-Zeilen, FedEx-Linie und ihre Scans, die Nummer der zustellenden Firma im Datensatz nach der Übergabe, Übergabe an DHL, FedEx, die Schweizerische Post und die Post des Ziellands, Pekinger Zeit und DHLs Ortszeit für seine Zeilen, übernommene Zeilen von J&T Express Mexiko auf der Linie nach Mexiko und ihre Uhrzeit in Mexiko-Stadt, abgeschlossene Verzollung im Zielland und DHLs Zeilen zu Ankunft im Zielland, Paketzentrum für die Einfuhr und Zustellfahrzeug (9. November), DHLs Zeilen zu „nicht angetroffen“ und zur Abholung im Paketshop mit dem Text des Hilfelinks (10. November), die Zeile mit dem Flug, auf den das Paket verladen werden soll (13. November), Partner von DHL im Zielland, Antwort bei unbekannter Nummer, was Peek zeigt, zurückhält und weglässt
:::
