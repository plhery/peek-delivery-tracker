---
title: DHLink Sendungsverfolgung: Nummer, Status und Zustellung
description: Verfolge dein DHLink-Paket von DHgate: welche Nummern das Tracking annimmt, was die englischen Status heißen, wer zustellt und wen du bei Problemen fragst.
slug: dhlink-sendungsverfolgung
published: 2026-11-16
updated: 2026-11-20
---

DHLink (驼飞侠) ist die grenzüberschreitende Logistikplattform von DHgate (敦煌网). Sie wurde 2014 gegründet, verschickt Bestellungen von DHgate-Händlern und anderen Kunden direkt aus China oder aus ihren Auslands-Lagern und übergibt sie im Zielland für die letzte Meile an einen Paketdienst vor Ort. Gib deine Nummer oben ins Feld ein oder prüfe sie auf der [Tracking-Seite von DHLink](https://www.dhlink.com/trackQuery?lang=en). Die Nummer findest du in deiner DHgate-Bestellung; steht dort noch keine, frag den Verkäufer.

## DHLink Sendungsnummer: so sieht sie aus

DHLink veröffentlicht kein festes Nummernformat. Die Tracking-Seite nimmt bis zu 40 Nummern auf einmal an und akzeptiert laut Eingabefeld *Global Tracking No.*, *Logistics Delivery No.* und *DHLink pickup No.* Diese Formen tauchen auf DHLink-Paketen auf, mit erfundenen Ziffern:

| Nummer | Was es ist |
| --- | --- |
| `FC9999999999980` | `FC` und 13 Ziffern: DHLinks eigene Nummer, im Ergebnis „DHLink No.“ genannt. Auf manchen Linien ist sie zugleich die Sendungsnummer. |
| `XZG999999980WL` | `XZG`, 9 Ziffern und `WL`: Nummer eines Linienpartners, die DHLink als Sendungsnummer erkennt. |
| `YLUS9999999980` | `YLUS` und 10 Ziffern: eine US-Linie, die GOFO zustellt. |
| `QH999999999980FS` | `QH`, 12 Ziffern und `FS`: eine weitere US-Linie mit Zustellung durch GOFO. |
| `EYT9999999980HZ` | `EYT`, 10 Ziffern und `HZ`: eine US-Linie, die SwiftX zustellt. |
| `CYS99999999980` | `CYS` und 11 Ziffern: eine US-Linie, die UniUni zustellt. |
| `JXCYI9999999990YQ` | `JXC`, `YI`, `ZO`, `US` oder `ZI`, 10 Ziffern und `YQ`: ein Frachtbriefformat von [Juxi Express](carrier:juxi-express), auf das bei manchen US-Paketen auch DHLinks Tracking antwortet. Peek fragt bei dieser Form zuerst DHLink; andere `JXC`-Nummern gehen an Juxi Express. |
| `RR482910576CN` | Zwei Buchstaben, 9 Ziffern und `CN`: eine Nummer von [China Post](carrier:china-post). Manche DHLink-Sendungen laufen unter einer solchen Nummer über den Postweg. |
| `GFUS99999999999980` | Keine DHLink-Nummer: eine Nummer von GOFO Express für die letzte Meile mancher US-Linien. |

Im Ergebnis zeigt DHLink „DHLink No.“ und „Tracking No.“ sowie – wenn ein anderer Dienst zustellt – eine „Last Leg No.“ dieses Paketdiensts. Weicht die Sendungsnummer von der DHLink-Nummer ab, such nach der Sendungsnummer: Mit der `FC`-Nummer allein erscheint ein solches Paket unter Umständen nicht. Ohne `lang=en` in der Adresse öffnet sich die Seite auf Chinesisch.

## DHLink Sendungsstatus: was die Meldungen heißen

Die Ergebnisseite ordnet Sendungen unter `In transit`, `Delivered` oder `Not found` ein, zeigt die Ländercodes von Abgangs- und Zielland sowie jeden Scan mit Uhrzeit und ergänzt nach der Zustellung `Signed for`. Weil die Einträge aus mehreren Systemen zusammenlaufen, steht derselbe Schritt bei DHLink oft doppelt. Die häufigsten englischen Zeilen in der Reihenfolge des Verlaufs:

| DHLink schreibt | Was das für dich heißt |
| --- | --- |
| `Data Received; Data transferred and received by carrier` | Auf einer Linie nach Südosteuropa: Pactic, die Plattform in Ungarn, über die diese Route läuft, hat die Paketdaten erhalten. Vor dem Lagerscan bei DHLink zeigt Peek „Etikett erstellt“; taucht die Zeile nach dem Flug erneut auf, bleibt das Paket auf „Unterwegs“. |
| `Arrive at the collection warehouse` | Das Paket ist im Sammellager von DHLink in China angekommen. |
| `The collection warehouse has completed sorting` | Im Sammellager sortiert. |
| `Package received & scanned at warehouse` | Im DHLink-Lager eingebucht und gescannt. |
| `Parcel handed over to carrier,waiting for transportation` | An der Annahmestelle übergeben, wartet auf den Weitertransport. |
| `Package departed from the warehouse`, `The package has sent from the warehouse` | Hat das Lager verlassen. |
| `Warehouse scanning bagging`, `Master airway bill number received`, `Leave the warehouse` | Auf manchen Linien: im chinesischen Lager für den Flug eingesackt, Hauptluftfrachtbrief (*master air waybill*) eingegangen und Lager verlassen („Unterwegs“ in Peek). |
| `Customs export clearance completed`, `Package handed over to airline` | Vom chinesischen Ausfuhrzoll freigegeben und an die Fluggesellschaft übergeben. |
| `Flight departure`, `The flight has taken off` | Aus China abgeflogen. |
| `Shipment Departed From Original Country at …`, `Departed from airport of origin` | Derselbe Schritt: Der Flug hat China verlassen („Unterwegs“ in Peek). |
| `The flight has arrived and the board is being dismantled`, `Dismantling the board` | Gelandet; am Zielflughafen werden die Luftfrachtpaletten abgebaut („Unterwegs“ in Peek). |
| `Shipment has arriveved at destination Country at …` | Im Zielland angekommen (der Tippfehler in *arriveved* stammt von DHLink). |
| `Arrived to … hub`, `Linehaul transit; Parcel has entered the linehaul network.` | Nach dem Flug: Ankunft im europäischen Hub eines Partners (mit Code anstelle der Punkte, etwa `HU` in Ungarn bei Pactic) und Weitertransport im Fernnetz („Unterwegs“ in Peek). |
| `Customs clearance completed`, `Customs import clearance completed, awaiting pickup by local carrier` | Einfuhrverzollung abgeschlossen; der Paketdienst im Zielland holt die Sendung als Nächstes ab. |
| `UPS/USPS/Fedex` | Die Übergabe an den US-Paketdienst steht bevor. |
| `货物已交UPS/USPS/Fedex,The goods have been delivered to UPS/USPS/Fedex` | An [UPS](carrier:ups), [USPS](carrier:usps) oder [FedEx](carrier:fedex) in den USA übergeben – noch nicht bei dir zugestellt. |
| `Package picked up by last-mile carrier` | Der Zustelldienst in deinem Land hat das Paket übernommen. |
| `Scheduled for Delivery` | In Zustellung. |
| `Shipment has delivered at …` | Zugestellt. |

Auf US-Linien kommen Hub-Scans wie `Arrived at Origin Hub`, `Departed Destination Hub` oder `Shipping Order Created.` hinzu („Etikett erstellt“ in Peek, rund eine Stunde bevor das Lager das Paket hat). Ein Standort des Zustelldiensts kann nach der Abholung `Parcel received by ABC01.` (mit eigenem Stationscode statt `ABC01`) und Minuten später `Parcel departed from Facility.` schreiben, beides „Unterwegs“. Danach folgen die Zeilen des Zustellers, etwa `Arrived at GOFO Delivery Station` bei GOFO, `No Access to Delivery Location` bei USPS (gescheiterter Zustellversuch) oder bei UniUni `Parcel received at UniUni sorting hub.`, `Parcel processed.` und `Parcel scanned and in transit.` Auf Linien mit [China Post](carrier:china-post) erscheinen einzelne Zeilen nur auf Chinesisch: `中国邮政 已收取邮件` (von China Post angenommen), `送交出口海关` (dem Ausfuhrzoll vorgelegt) und `已妥投` hinter einem Ländercode (zugestellt). Kennt DHLink eine Nummer nicht, meldet die Seite `There is no track for this waybill number`.

## Wer DHLink-Pakete zustellt

Die Website von DHLink richtet sich an Händler und nennt keine Zustellpartner nach Ländern: Wer übernimmt, siehst du erst in der Sendungsverfolgung. In der Schweiz kann als „Last Leg No.“ eine 14-stellige Nummer mit `060` oder `061` am Anfang für [DPD Schweiz](carrier:dpd-ch) stehen. Auf US-Linien kündigt ein Scan mitunter pauschal „UPS/USPS/Fedex“ an, bevor die Scans des jeweiligen Dienstes folgen; andere US-Linien enden bei GOFO Express (dessen eigenes Tracking bei DHgate-Paketen auch die 13-stellige `FC`-Nummer findet) oder SwiftX. Sendungen, die mit [China Post](carrier:china-post) reisen, behalten ihre `CN`-Nummer bis zur Haustür: Auf der China-Post-Seite siehst du, welche Landespost in Deutschland, Österreich oder der Schweiz zustellt.

Sobald der lokale Paketdienst und seine Nummer feststehen, liefert dessen eigenes Tracking die Details zu Zustelltour, Benachrichtigungskarte und Abholstelle: siehe auch [Zustellung verpasst](guide:missed-delivery).

**Zoll und Gebühren in der EU und in der Schweiz.** In der Europäischen Union (Deutschland, Österreich) ist laut Zoll am 1. Juli 2026 die Zollfreigrenze bis 150 € für E-Commerce-Sendungen aus Nicht-EU-Staaten entfallen: Bis 150 € Warenwert fällt neben der Einfuhrumsatzsteuer eine pauschale Zollabgabe von 3 € je Warenkategorie (Position der Zollanmeldung, nicht pro Stück) an, und seit dem 1. November 2026 erhebt die EU bei jedem Warenwert zusätzlich eine Abfertigungspauschale (*Union Handling Fee*) von 2 € je Warenkategorie, fällig mit Annahme der Zollanmeldung. In der Regel übernimmt der Beförderer die Zollanmeldung, legt die Abgaben aus und kann dafür eine eigene Servicepauschale verlangen, sofern Zoll und Abfertigungspauschale nicht schon beim Kauf berücksichtigt wurden. In der Schweiz (außerhalb der EU) ist laut Bundesamt für Zoll und Grenzsicherheit (BAZG) jede Sendung aus dem Ausland grundsätzlich zoll- und mehrwertsteuerpflichtig; der Transporteur erledigt die Verzollung, erhebt Verzollungskosten und ist dein Ansprechpartner für Fragen zu diesen Kosten oder zum Verbleib der Sendung. Mehr dazu im [Zoll-Ratgeber](guide:customs).

## DHLink Paket hängt fest, nicht gefunden oder als zugestellt gemeldet

**Nicht gefunden.** Prüfe die Nummer in deiner DHgate-Bestellung und versuche die Sendungsnummer (*Tracking No.*) statt der `FC`-Nummer von DHLink. Eine Nummer des lokalen Zustellers liefert auf DHLinks Seite kein Ergebnis: Such sie direkt bei diesem Paketdienst. Hast du für eine Nummer, die DHLink nicht kennt, über „Ändern“ DHLink gewählt, zeigt Peek „Warten auf DHLink“ und prüft weiter. Kennt DHLink die Nummer auch zwei Tage später noch nicht und antwortet auch kein anderer Dienst dafür, zeigt Peek „Auch nach zwei Tagen kennt der Paketdienst diese Nummer nicht. Prüfe sie, wähle einen anderen Paketdienst oder lösche das Paket.“ und prüft sie ab dann täglich, und wöchentlich, sobald seit dem Hinzufügen eine Woche vergangen ist.

**Keine Updates nach Flug oder Zoll.** Schau im Ergebnis nach einer „Last Leg No.“ und prüfe sie beim lokalen Zusteller – dessen Scans sind oft aktueller als die von DHLink. Mehr in [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating) und [Paket aus China verfolgen](guide:tracking-from-china).

**Als zugestellt gemeldet, verspätet oder verloren.** DHLinks Erstattungs- und Entschädigungsregeln für Problemsendungen gelten für Verkäufer: Der Händler reklamiert bei DHLink, und du klärst den Fall mit dem Verkäufer oder über DHgate. Ausgeschlossen sind vom Käufer verursachte Probleme – etwa eine verspätete Zustellung, weil das Paket nicht abgeholt oder unterschrieben wurde, oder eine gescheiterte Verzollung, weil der Empfänger nicht rechtzeitig mitgewirkt hat. Reagiere deshalb rasch auf Rückfragen von Zoll oder Zusteller und gib dem Verkäufer früh Bescheid: Er kann Ansprüche nur innerhalb von 90 Kalendertagen nach Anlage des Logistikauftrags einreichen. Siehe [zugestellt, aber nicht erhalten](guide:delivered-not-received) und [Paket verloren](guide:lost-parcel).

## DHLink Kontakt und Kundenservice

Stand 16. November 2026 nennt die Kontaktseite von DHLink eine E-Mail-Adresse für den Kundenservice (`dhlinkservice@dhlink.com`), einen Anfragezugang auf der Plattform sowie den Chat-Assistenten „AI 小飞“. Die Seite ist auf Chinesisch und führt keine Telefonnummer auf. Wende dich zu deiner Bestellung zuerst über DHgate an den Verkäufer; bei Zustellproblemen vor Ort an den Paketdienst, der in deinem Land ausliefert.

## DHLink Paket mit Peek verfolgen

[Peek](/) kann DHLink allein an der Nummernform nicht sicher erkennen: `FC`, `XZG`, `YLUS`, `QH`, `EYT`, `CYS`, `JXCYI`, `JXCZO`, `JXCUS` und `JXCZI` sind nur schwache Treffer. Füg die Nummer trotzdem ein: Peek verfolgt mehr als 3.500 Paketdienste, zeigt „Tracking-Dienste werden geprüft…“ und danach „DHLink hat dieses Paket.“, sobald DHLink sie kennt; andernfalls wähl DHLink über „Ändern“. Eine `JXCYI`-, `JXCZO`-, `JXCUS`- oder `JXCZI`-Nummer kann auch zu [Juxi Express](carrier:juxi-express) gehören: Peek fragt zuerst DHLink und zeigt das Paket, solange noch niemand antwortet, als „Wahrscheinlich DHLink“; hat der Händler mit Juxi verschickt, wechsle mit „Ändern“ zu Juxi Express. Eine China-Post-Nummer (zwei Buchstaben, 9 Ziffern, `CN`) geht an [China Post](carrier:china-post).

Peek liest DHLinks eigene Sendungsverfolgung, zeigt den Status, das Zielland und jeden Schritt nur einmal (so wie DHLink ihn schreibt, meist auf Englisch) und lässt Dubletten zwischen den Feeds ebenso weg wie Postleitzahlen und Bestellreferenzen. Scans in China liest Peek nach Pekinger Zeit, USPS-Scans nach der Zeit des jeweiligen US-Bundesstaats und Zeilen von Pactic über Ungarn nach Budapester Zeit, und zeigt sie in der Zeitzone deines Geräts; auf peektracker.com und in der iPhone-App nennt eine Zeile unter dem Verlauf diese Zeitzone („Uhrzeiten in deiner Zeitzone: …“). Andere Scans behalten die Uhrzeit, die DHLink angibt. Gewicht, Dienstname oder Lieferzeit liefert DHLink nicht; eine Lieferschätzung zeigt Peek nur dann, wenn ein weitergereichter USPS-Scan vom Typ `Out for Delivery, Expected Delivery …` der neueste ist und für denselben Tag ein Zeitfenster nennt. Ist die „Last Leg No.“ eine Nummer von GOFO (`GFUS` und 14 Ziffern), SwiftX (`SWX` und 18 Ziffern) oder [DPD Schweiz](carrier:dpd-ch) (14 Ziffern mit `060` oder `061` am Anfang), zeigt Peek diesen Dienst samt Nummer, fragt ihn ab und wechselt dorthin, sobald er neueren Fortschritt meldet; andere Nummern der letzten Meile bleiben auf DHLinks Verlauf.

Solange DHLinks eigenes Tracking direkt antwortet, prüft Peek bis zu alle 10 Minuten, und am Tag der Zustellung bis zu alle 2 Minuten (ab 6:00 Uhr Ortszeit oder, wenn ein Zeitfenster genannt ist, ab einer halben Stunde vor dessen Beginn); über Tracking-Dienste stündlich, am Tag der Zustellung bis zu alle 15 Minuten. Sobald Peek das Paket bei GOFO weiterverfolgt, prüft es höchstens alle 30 Minuten, auch in Zustellung. Nach zwei Tagen ohne neuen Scan (oder nach 12 Stunden, solange das Paket noch auf „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ steht) fragt Peek stündlich nach. Mitteilungen gibt es für „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“. DHLink, DHgate oder den Verkäufer kann Peek nicht für dich kontaktieren.

## Fragen zur DHLink Sendungsverfolgung

### Was ist DHLink?

DHLink ist die grenzüberschreitende Logistikplattform von DHgate, betrieben in Peking von der 驼飞侠科技（北京）有限公司. Sie wurde 2014 gegründet und versendet Bestellungen aus China sowie aus Lagern im Ausland über eigene Linien und Partnerdienste.

### Wer stellt mein DHLink-Paket zu?

Ein Paketdienst in deinem Land. In der Schweiz übernimmt mitunter DPD Schweiz die letzte Meile; auf US-Linien übergibt DHLink Pakete an UPS, USPS, FedEx, GOFO, SwiftX oder UniUni; und bei Postsendungen mit `CN` am Ende stellt die jeweilige Landespost zu, die China Post weiterleitet. Achte im DHLink-Ergebnis auf das Feld „Last Leg No.“.

### Was bedeutet „The goods have been delivered to UPS/USPS/Fedex“?

DHLink hat das Paket an den US-Paketdienst übergeben, der den Weitertransport übernimmt. Bei dir zugestellt ist es damit noch nicht – danach folgen die Scans dieses Zustellers.

:::sources
- [DHLink: Über uns (关于我们)](https://www.dhlink.com/about/introduce) – 2014 gegründet, grenzüberschreitende Logistikplattform von DHgate, Auslands-Lager und Zustellung im Zielland
- [DHLink: Startseite (首页)](https://www.dhlink.com/) – Direktversand aus China und Lager im Ausland
- [DHLink: Sendungsverfolgung (轨迹查询)](https://www.dhlink.com/trackQuery?lang=en) – bis zu 40 Nummern, akzeptierte Nummerntypen, Felder DHLink No., Tracking No., Last Leg No., Signed for, Hinweis bei unbekannter Nummer und Standardsprache
- [DHLink: Kontakt (联系我们)](https://www.dhlink.com/about/connect) – E-Mail dhlinkservice@dhlink.com, Plattform-Anfrage, Chat-Assistent AI 小飞, keine Telefonnummer
- [DHLink: Nutzungsvereinbarung (服务协议)](https://www.dhlink.com/agreementPage/service) – Betreiber 驼飞侠科技（北京）有限公司
- [DHgate Seller Help: DHLink-Entschädigungsregeln (DHLink物流异常件赔付规则)](https://seller.dhgate.com/help/c7502/516002.html) – Entschädigung für Händler, Ausschlüsse bei käuferbedingter Verspätung oder gescheiterter Verzollung, Frist von 90 Kalendertagen nach dem Logistikauftrag
- [Zoll: Internetbestellungen](https://www.zoll.de/DE/Privatpersonen/Postsendungen-Internetbestellungen/Sendungen-aus-einem-Nicht-EU-Staat/Zoll-und-Steuern/Internetbestellungen/internetbestellungen_node.html) – Wegfall der 150-€-Zollfreigrenze und 3 € Pauschalzoll je Warenkategorie seit 1. Juli 2026, 2 € Abfertigungspauschale ab 1. November 2026, Zollabwicklung und mögliche Servicepauschale durch den Beförderer
- [EU-Kommission: EU Customs Reform (auf Englisch)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – Einführung der 2-€-Bearbeitungsgebühr zum 1. November 2026
- [EU-Kommission: Customs Guidance on EUR 3 customs duty, PDF (auf Englisch)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € je Warenkategorie wie beim 3-€-Zoll, unabhängig vom Warenwert, fällig mit Annahme der Zollanmeldung
- [BAZG: Verzollungskosten und Paketverfolgung](https://www.bazg.admin.ch/de/verzollungskosten-und-paketverfolgung) – in der Schweiz Zoll, Mehrwertsteuer und Verzollungskosten durch den Transporteur
- [Universal Parcel Scraper: DHLink (auf Englisch)](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dhlink) – Nummernformen, Scan-Wortlaute (Pactic, UniUni, China Post, USPS), Zeitzonen, Übergabe an GOFO, SwiftX und DPD Schweiz sowie Darstellung in Peek
- [Universal Parcel Scraper: GOFO Express (auf Englisch)](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/gofo) – Unterstützung von DHLinks FC-Nummern im GOFO-Tracking und 30-Minuten-Abfrageintervall
:::
