---
title: JD Logistics Sendungsverfolgung: Nummern, Status & Kontakt
description: JD Logistics (京东物流) Paket verfolgen: JD- und VG-Nummernformate, Bedeutung der englischen und chinesischen Scans, Lager in Deutschland, Zoll und Kontakt.
slug: jd-logistics-sendungsverfolgung
published: 2026-11-18
updated: 2026-11-21
---

JD Logistics (京东物流, Jingdong Logistics) ist die an der Hongkonger Börse notierte Logistiksparte von JD.com (`HKEX: 2618`). International tritt das Unternehmen unter den Marken JoyLogistics und JoyExpress auf: Es versendet Händlerware aus eigenen Lagern in **Deutschland**, **Frankreich**, den **Niederlanden**, **Polen**, **Großbritannien**, den **USA** und **Australien** und befördert E-Commerce-Pakete aus **China**. Verfolge dein Paket auf Englisch im [internationalen Tracker von JD Logistics](https://www.jingdonglogistics.com/Tracking) oder füge die Sendungsnummer direkt oben ins Suchfeld ein. Die Nummer erhältst du vom Online-Shop, der deine Bestellung verschickt hat.

## JD Logistics Sendungsnummer: Aufbau und Beispiele

Diese Nummernformen ordnet Peek JD Logistics zu – ergänzt um ein ähnliches Format, das nicht zu JD gehört (jeweils mit erfundenen Ziffern):

| Nummer | Bedeutung |
| --- | --- |
| `JD4829105736491` | `JD` und 13 Ziffern: Frachtbriefnummer von JD Logistics. |
| `JDA482910573649` | `JD`, ein Buchstabe und 12 Ziffern: ebenfalls JD Logistics. |
| `JDAP48291057364` | `JD`, zwei Buchstaben und 11 Ziffern: ebenfalls JD Logistics. |
| `VG48291057364` | `VG` und 11 Ziffern: wird von JD Logistics genutzt, passt aber auch zum Nummernformat von [La Poste](carrier:la-poste), weshalb Peek JD hier nicht sofort festlegt (siehe unten). |
| `JD482910573649182736` | `JD` und 18 Ziffern: nicht JD Logistics. Peek fragt [DHL Paket](carrier:dhl) ab, das dieses Format verwendet. |

Kleinbuchstaben, Leerzeichen und Bindestriche spielen in Peek keine Rolle. Im internationalen Tracker von JD lassen sich bis zu 100 Sendungsnummern gleichzeitig durch Leerzeichen getrennt abfragen (Eingabetaste oder Klick auf „Track“).

**Wo du die Nummer findest.** Der Shop, der JD Logistics beauftragt hat, nennt sie in der Versandbestätigung per E-Mail oder in deiner Bestellübersicht. Falls du sie nicht findest, lies [Sendungsnummer finden](guide:find-tracking-number).

**Paket innerhalb Chinas.** Eine Sendung, die innerhalb **Chinas** unterwegs ist – etwa zum Lager eines Weiterleitungsdienstes –, wird auf JDs chinesischer Website [jdl.com](https://www.jdl.com/orderSearch) verfolgt: Gib die Frachtbrief- oder Paketnummer unter `运单查询` ein und klicke auf `立即查询` (mehrere Nummern lassen sich durch Kommas, Leerzeichen oder Zeilenumbrüche trennen).

## JD Logistics Sendungsstatus und ihre Bedeutung

Der internationale Tracker von JD zeigt jeden Schritt auf Englisch mit dem jeweiligen Ort. Diese Originalmeldungen von JD begegnen einem Paket aus einem JD-Auslandslager typischerweise in folgender Reihenfolge:

| Was JD anzeigt | Status in Peek | Was das für dein Paket bedeutet |
| --- | --- | --- |
| `Picking Completed.` | „Beim Paketdienst eingegangen“ | Deine Bestellung wurde in einem JD-Lager kommissioniert, in dem die Ware des Händlers bereits lag. |
| `Weighed at Warehouse.` | „Beim Paketdienst eingegangen“ | Im selben Lager vor dem Ausgang gewogen. |
| `Departed from Warehouse.` | „Unterwegs“ | Das Paket hat das Lager von JD Logistics verlassen. |
| `Handed over to Carrier` | „Unterwegs“ | JD hat die Sendung an den Paketdienst übergeben, der bei dir zustellt. |
| `Shipping Label Created` | kein Rücksprung | Das von JD weitergemeldete Etikett des lokalen Zustellers; ein bereits unterwegs befindliches Paket behält seinen Status. |
| `Arrived at GOFO Regional Hub` | „Unterwegs“ | In den **USA**: [GOFO Express](carrier:gofo), der dort laut JD-Verlauf zustellt, hat das Paket in seinem Regional-Hub gescannt. |

**Ein Paket aus China** durchläuft andere Stationen: zuerst `Shipment Picked up` (beim Händler abgeholt), danach `Arrived at Warehouse`, `Weighed at Warehouse` und `Consolidation Completed` in JDs Lager in China, wo die Sendung bis zum Abflug wartet – allesamt „Beim Paketdienst eingegangen“ (`Consolidation Completed` heißt mit anderen Paketen für den Flug gebündelt, nicht zugestellt). Anschließend folgen unter „Unterwegs“: `Departed from Warehouse.`, `Arrive at airport`, `Customs released from origin port` (Ausfuhrzoll erledigt), `Departure from origin port`, `Arrive at destination port`, `Cleanrance agency picked` (Schreibweise von JD: der Zollagent hat die verzollte Sendung übernommen, nicht du) und `Handover to local carrier`. In den **USA** schließen sich unter JDs Ereigniscodes die Schritte von GOFO an: `Arrived at GOFO Regional Hub`, `In Transit to Next Facility`, `Loaded on Vehicle` (im GOFO-Hub verladen) und `Arrived at GOFO Delivery Station` („Unterwegs“) sowie `Out for Delivery` („In Zustellung“).

**Die Sendungsnummer des lokalen Zustellers.** Bei Paketen aus China endet `Departed from Warehouse.` im JD-Tracker teils mit `Tracking no` und der Nummer des lokalen Zustellers (in den **USA** etwa einer `GFUS`-Nummer mit 14 Ziffern wie `GFUS48291057364918` von GOFO Express). Peek blendet diese Nummer aus dem Scantext aus, fragt den lokalen Paketdienst aber automatisch damit ab, sobald das Nummernformat ihn eindeutig benennt: Hat GOFO einen mindestens ebenso aktuellen Scan wie JD, verfolgt Peek das Paket dort weiter, behält die JD-Schritte im Verlauf und zeigt „Zustellung durch GOFO Express“. Lässt sich eine Folgenummer keinem Dienst zuordnen, prüfe sie direkt beim lokalen Zusteller.

Weitere JD-Schritte liest Peek anhand von JDs internem Code: Auftrag angelegt („Etikett erstellt“); Verzollung gestartet („Beim Zoll“); Verzollung abgeschlossen, Ent- oder Beladung im Sortierzentrum, Entladung am Zielstandort oder Weiterleitung an ein Sortierzentrum im Ausland („Unterwegs“); JD-Zusteller auf Tour („In Zustellung“); quittiert („Zugestellt“). Nennungen von Kuriername und Telefonnummer in Klammern `【…】` beim Zustellscan lässt Peek weg.

**Meldungen aus JDs chinesischem Tracker (`jdl.com`).** Für Sendungen innerhalb **Chinas** erscheinen chinesische Zeilen, darunter `京东快件已取件` (beim Absender abgeholt), `已到达【…】` (am in Klammern genannten Sortierzentrum oder Zustellstützpunkt eingetroffen), `正在派送中` (in Zustellung), `已与收件人约定新的派送时间` (neuer Zustelltermin mit dem Empfänger vereinbart) und `已送达` (zugestellt).

## Zustellung durch JD Logistics in Deutschland, Europa und weltweit

**Pakete aus China.** JDs Dienst „Cross-Border Parcels“ für Online-Händler mit Versand ab **China** nennt die **USA**, **Großbritannien**, **Australien**, **Kanada**, **Mexiko**, **Russland** sowie weitere im Aufbau befindliche Länder und bietet Standard-, Express- und Economy-Versand. JD fliegt die Sendungen aus und übergibt sie für die letzte Meile an lokale Partner mit einer laut JD durchschnittlichen Zustelldauer von 3 bis 6 Werktagen, ohne diese Partner namentlich aufzulisten (in den **USA** zeigt JDs Verlauf [GOFO Express](carrier:gofo)). In **Europa** hat JD.com zudem im April 2025 seine eigene Express-Zustellmarke JoyExpress gestartet.

**Pakete aus einem JD-Lager in Europa oder Übersee.** JD Logistics betreibt eigene Auslandslager – in **Deutschland** im Raum Frankfurt (`DE-JD-FRA1` in Buseck) und Dortmund (`DE-JD-DTM1` in Unna), außerdem in **Frankreich** bei Paris (`FR-JD-PAR1` in Dugny), in den **Niederlanden** in Venray (`NL-JD-VEN1`) und Tilburg (`NL-JD-TIL1`), in **Polen** bei Warschau (`PL-JD-WAW1` in Urzut und `PL-JD-WAW2` in Brwinów), in **Großbritannien** (`GB-JD-BHM1` in Lutterworth), in den **USA** (New Jersey, Raum Los Angeles und Georgia) sowie in **Australien** (Sydney). Verschickt ein Händler aus solchem lokalen Bestand, beginnt der Verlauf mit `Picking Completed.` und `Weighed at Warehouse.` statt mit `Shipment Picked up`.

Wie viele Zustellversuche der lokale Paketdienst unternimmt und wo ein nicht angetroffenes Paket lagert, nennt JD auf seinen Seiten nicht – dafür gelten die Regeln des Zustellers in deinem Land. Siehe [Zustellung verpasst](guide:missed-delivery).

## Zoll, Einfuhrabgaben und Schutz vor gefälschten SMS

Für seinen internationalen Expressversand ab **Festlandchina** hält JD Logistics in seinen Servicebedingungen ([jdl.com/serviceDetails](https://www.jdl.com/serviceDetails), Reiter `国际`) fest, dass bei der grenzüberschreitenden Verzollung Zölle und Steuern anfallen können, die vom Absender oder Empfänger zu tragen sind und nicht im Frachtpreis von JD enthalten sind. Kläre beim Shop, von wo deine Bestellung verschickt wurde und ob Einfuhrabgaben im Kaufpreis enthalten waren. Siehe auch [Paket beim Zoll](guide:customs) und [gefälschte Paket-SMS erkennen](guide:delivery-scam-texts).

- **Europäische Union.** Seit dem 1. Juli 2026 fällt in der **EU** auf im Fernverkauf außerhalb des EU-Zollgebiets gekaufte Waren in Sendungen mit einem Sachwert bis 150 € ein Zoll von 3 € pro Warenkategorie an; seit dem 1. November 2026 kommt unabhängig vom Sendungswert eine EU-Bearbeitungsgebühr von 2 € pro Warenkategorie hinzu (eine Kategorie umfasst Waren mit derselben Zolltarifnummer, derselben Warenbezeichnung und – soweit vorgeschrieben – demselben Ursprung, ungeachtet der Stückzahl).
- **Deutschland.** In **Deutschland** gehören die Insel Helgoland und das Gebiet von Büsingen weder zum Zoll- noch zum Mehrwertsteuergebiet der EU. Meldet die Deutsche Post eine abgabenpflichtige Nicht-EU-Sendung für dich beim Zoll an und legt die Einfuhrabgaben aus, berechnet sie laut [DHL Einfuhrabgaben-Hilfe](https://www.dhl.de/de/privatkunden/hilfe-kundenservice/themen/international/zoll/fragen-zu-kosten.html) zusätzlich eine Auslagepauschale von 7,50 € brutto; alle Einfuhrabgaben werden bei der Zustellung in bar oder bei Abholung in einer Filiale über die dort verfügbaren Zahlungsarten bezahlt (keine Auslagepauschale fällt an, wenn vom Empfänger keine Abgaben einzuziehen sind oder die Sendung vom Empfänger selbst bei einem Binnenzollamt verzollt wird). In seinen [Sicherheitshinweisen](https://www.dhl.de/de/toolbar/footer/sicherheitshinweise.html) betont DHL zudem, dass DHL niemals per SMS nach persönlichen Daten fragt oder per SMS zu Zahlungen auffordert; verdächtige Nachrichten lassen sich an `phishing@dhl.com` melden.
- **Schweiz.** In der **Schweiz** verzichtet das Bundesamt für Zoll und Grenzsicherheit ([BAZG](https://www.bazg.admin.ch/bazg/de/home/information-private/interneteinkauf--post--und-kuriersendungen/einfuhr-in-die-schweiz/empfangen-von-briefen-und-paketen.html)) aus verwaltungsökonomischen Gründen auf die Erhebung von Zoll- und Einfuhrsteuerbeträgen bis jeweils 5 Schweizerfranken (entspricht 62 CHF Bemessungsgrundlage beim MWST-Satz von 8,1 % bzw. 193 CHF beim Satz von 2,6 %); eigene Verzollungsgebühren von Post oder Kurierdienst fallen unabhängig davon an.

## JD Logistics Paket aktualisiert sich nicht, ist verspätet oder geht zurück

**Noch kein Eintrag.** Findet JDs Tracker unter der Nummer noch nichts, prüfe sie beim Verkäufer. Peek zeigt „Warten auf JD Logistics“ und fragt weiter nach; bleibt die Nummer zwei Tage lang unbekannt, erscheint „Auch nach zwei Tagen kennt der Paketdienst diese Nummer nicht. Prüfe sie, wähle einen anderen Paketdienst oder lösche das Paket.“.

**Tagelang kein neuer Scan.** Hat ein Paket unterwegs seit vier Tagen keinen neuen Scan erhalten, zeigt Peek den Tag des letzten Scans („Keine Aktualisierung seit Mo 12. Okt“), weist darauf hin, dass Sendungen auf Langstreckenflügen, beim Zoll oder am Wochenende oft ruhen (nach drei Wochen ohne Scan rät es stattdessen, beim Verkäufer nachzufragen), und blendet den Link „Hol dir Hilfe bei JD Logistics“ zu JDs Kontaktseite ein (beziehungsweise zum lokalen Zusteller, sobald etwa GOFO übernommen hat). Mehr unter [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating) und [Paket aus China verfolgen](guide:tracking-from-china).

**Verspätet oder verloren.** Da der Händler JD Logistics gebucht hat, wende dich zuerst an den Shop. Für seinen internationalen Express ab **Festlandchina** bezeichnet JDs Regelwerk die auf dem Frachtbrief angegebene voraussichtliche Lieferzeit ausdrücklich als unverbindlichen Richtwert: Bei Überschreitung erstattet JD weder Versandkosten noch zahlt es Schadenersatz. Siehe [verlorene Pakete](guide:lost-parcel).

**Rücksendung.** Für seinen internationalen Express ab **Festlandchina** bietet JD keinen Rückhol- oder Rücksendedienst an, sobald die Ware Festlandchina verlassen hat. Frag den Online-Shop, wie eine Retoure abläuft. Siehe [zurück an den Absender](guide:returned-to-sender).

**Als zugestellt gemeldet, aber nicht da.** Frag beim Shop nach, prüfe Nachbarn sowie Hausverwaltung und lies [zugestellt, aber nicht erhalten](guide:delivered-not-received).

## JD Logistics Kundenservice und Kontakt

Auf seiner internationalen [Contact-Us-Seite](https://www.jingdonglogistics.com/contact) nennt JD Logistics den Kundenservice nach Ländern (an Wochenenden und gesetzlichen Feiertagen geschlossen, sofern nicht anders vermerkt; ohne Angabe einer Zeitzone):

- **Deutschland, Niederlande und Großbritannien:** `org.cs.eu1@jd.com`, Montag bis Freitag, 09:00 bis 18:00 Uhr.
- **Frankreich:** `org.cs.eu1@jd.com`, Montag bis Freitag, 09:00 bis 17:00 Uhr.
- **Polen:** `org.cs.intl1@jd.com`, Montag bis Freitag, 09:00 bis 17:00 Uhr.
- **USA:** `org.cs.us1@jd.com`, Montag bis Freitag, 08:00 bis 17:00 Uhr.
- **Australien:** `+61 (02)87239755` und `org.cs.intl1@jd.com`, Montag bis Freitag, 09:00 bis 18:00 Uhr (sonntags und an Feiertagen geschlossen).

Für Österreich, die Schweiz oder Kanada führt JD dort keinen eigenen Kontakt auf. In **China** lautet die zentrale Hotline `950616` (auf JDs englischer International-Express-Seite `0086 950616`); die [Kontaktseite auf jdl.com](https://www.jdl.com/en/contactUs) bietet zusätzlich die Schaltfläche „Online Consultation“. Bei Fragen zur Bestellung selbst wende dich an den Verkäufer.

## Ein JD Logistics Paket mit Peek verfolgen

[Peek](/) erkennt JD Logistics sofort anhand der `JD…`-Formate aus der Tabelle oben („Erkannter Paketdienst“). Da eine `VG`-Nummer mit 11 Ziffern auch zu La Poste passt, prüft Peek sie zunächst bei La Poste und weiteren Tracking-Diensten („Tracking-Dienste werden geprüft…“); wird sie dort nicht gefunden, tippe auf „Ändern“ und wähle JD Logistics. Ein Tracking-Link von `jingdonglogistics.com` mit `waybillCodes=` legt das Paket unabhängig vom Nummernformat unter JD Logistics an; aus einem `jdl.com`-Link übernimmt Peek die Nummer nur dann, wenn sie einem der `JD…`-Formate entspricht. Sieht deine JD-Nummer anders aus, füge sie trotzdem ein: Peek unterstützt über 4.500 Paketdienste – tippe auf „Ändern“ und wähle JD Logistics unter „Alle Paketdienste“ (die Suche findet auch `Jingdong` oder `京东物流`) oder nutze „Paketdienst ändern“ bei einem gespeicherten Paket.

Peek liest JDs internationale Sendungsverfolgung aus: den Status, jeden Schritt im Wortlaut von JD samt Stadt und Land auf Englisch (auch wenn JD das Land auf Chinesisch einträgt) sowie die Zustellzeit. Uhrzeiten liest Peek nach der Ortszeit des Scan-Orts und rechnet sie in die Zeitzone deines Geräts um („Uhrzeiten in deiner Zeitzone: …“ auf peektracker.com und in den Apps für iPhone und Android). Ein voraussichtliches Lieferdatum zeigt Peek für JD-Pakete nicht an; Empfängerdaten, eventuelle Zustellfotos, Name und Telefonnummer des Fahrers sowie die am Ende einer Ausgangszeile angehängte Partnernummer lässt Peek weg.

Bei `JD`-Nummernserien des **chinesischen Inlandsverkehrs** (`JD` und 13 Ziffern beginnend mit `0`; `JDJ`, `JDK`, `JDV` oder `JDX` und 12 Ziffern; `JDVA`, `JDVB`, `JDVC` oder `JDAZ` und 11 Ziffern) öffnet Peek direkt JDs chinesischen Tracker auf `jdl.com`. Bei `JDW` mit 12 Ziffern oder `JDAP` mit 11 Ziffern fragt Peek ausschließlich den internationalen Tracker ab; bei übrigen `JD`-Nummern zuerst den internationalen und – falls dieser leer bleibt – anschließend den chinesischen. Die chinesischen Zeilen zeigt Peek auf Chinesisch ohne Kuriernamen, Telefonnummer oder Namen des Unterzeichners. Da `jdl.com` häufig eine Sicherheitsprüfung verlangt, die Peek nicht lösen kann, pausiert Peek das Öffnen von `jdl.com` für diese Nummer nach einer Ablehnung zunächst für 6 Stunden oder länger, bis zu einem Tag: Zeigt ein Paket innerhalb Chinas in Peek nichts an, schau direkt auf `jdl.com` nach.

Solange du das Paket geöffnet hast oder am Zustelltag mit eingeschalteten Mitteilungen, prüft Peek bis zu alle 10 Minuten – und ab 6:00 Uhr Ortszeit an dem Tag, an dem JD das Paket als in Zustellung scannt, bis zu alle 2 Minuten, solange JDs internationaler Tracker antwortet (alle 5 Minuten beim Abruf über `jdl.com`); allein über Tracking-Dienste prüft Peek stündlich, nach einem ruhigen Tag alle 3 Stunden und am Zustelltag bis zu alle 15 Minuten, solange das Paket geöffnet ist oder Mitteilungen aktiv sind. Sonst sowie nach zwei Tagen ohne Neuigkeiten (12 Stunden auf „Etikett erstellt“ oder „Beim Paketdienst eingegangen“) prüft Peek stündlich. Übernimmt ein lokaler Zusteller die Auslieferung, folgt Peek dessen Takt (in den **USA** GOFO Express: bis zu alle 30 Minuten bzw. stündlich nach einem Fehlversuch, auch am Zustelltag). Du kannst Benachrichtigungen für „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“ aktivieren. Peek kann weder JD Logistics noch den Shop für dich kontaktieren.

## Häufige Fragen zur JD Logistics Sendungsverfolgung

### Ist JD Logistics seriös?

Ja. JD.com begann 2007 mit dem Aufbau seines landesweiten Logistiknetzes, gründete im April 2017 die Geschäftsgruppe JD Logistics und brachte sie im Mai 2021 an die Börse in Hongkong (Börsenkürzel `2618`). Laut Unternehmensseite umfasst das Netz über 1.600 selbst betriebene Lager und 2.000 von Dritten betriebene Cloud-Lager in 26 Märkten. Weltweit betreibt JD Logistics über 200 Zoll-, Direktvertriebs- und Überseelager.

### Wie verfolge ich ein JD Logistics Paket?

Auf `jingdonglogistics.com/Tracking` (bis zu 100 Nummern getrennt durch Leerzeichen), für Sendungen innerhalb Chinas auf `jdl.com/orderSearch` oder direkt im Eingabefeld oben auf dieser Seite.

### Wer stellt JD Logistics Pakete in Deutschland und Europa zu?

Für Pakete aus China veröffentlicht JD keine Partnerliste für die letzte Meile; in Europa betreibt JD.com jedoch seit April 2025 seine Expressmarke JoyExpress sowie eigene Lager in Buseck bei Frankfurt, Unna bei Dortmund, Dugny bei Paris, Venray, Tilburg, im Raum Warschau und in Mittelengland. Nennt JD am Ende von `Departed from Warehouse.` hinter `Tracking no` die Nummer eines Peek bekannten lokalen Zustellers, verfolgt Peek diese Nummer automatisch weiter, sobald dort ein Scan vorliegt.

### Warum aktualisiert sich meine JD Logistics Sendungsverfolgung nicht?

Entweder hat JD die Sendung noch nicht erhalten, sie befindet sich im Flugzeug oder beim Zoll, oder der lokale Zusteller hat noch keinen Scan übermittelt. Meldet sich vier Tage lang nichts, frag beim Verkäufer nach oder schreibe an `org.cs.eu1@jd.com` (für Deutschland Montag bis Freitag 09:00–18:00 Uhr). Bei Paketen innerhalb Chinas prüfe `jdl.com`.

### Wie erreiche ich den JD Logistics Kundenservice?

Über die auf `jingdonglogistics.com/contact` genannten E-Mail-Adressen – für Deutschland, die Niederlande und Großbritannien `org.cs.eu1@jd.com` (Mo–Fr 09:00–18:00 Uhr). In China lautet die Hotline `950616` (`0086 950616` aus dem Ausland). Wegen deiner Bestellung oder einer Erstattung wende dich zuerst an den Shop.

:::sources
- [JD Logistics: Track your shipment (auf Englisch)](https://www.jingdonglogistics.com/Tracking) – internationaler Tracker, bis zu 100 Nummern durch Leerzeichen getrennt, Eingabetaste oder „Track“
- [JD Logistics: About JINGDONG Logistics (auf Englisch)](https://www.jingdonglogistics.com/about) – Logistiksparte von JD.com (HKEX: 2618), Marken JoyLogistics und JoyExpress, über 1.600 eigene Lager, 2.000 Cloud-Lager und über 200 internationale Lager in 26 Märkten
- [JD Logistics: Contact Us (auf Englisch)](https://www.jingdonglogistics.com/contact) – Kundenservice-Postfächer, australische Rufnummer, Servicezeiten nach Ländern (Deutschland, Niederlande, Großbritannien, Frankreich, Polen, USA, Australien) und Hotline 950616
- [JD Logistics: Cross-Border Parcels (auf Englisch)](https://www.jingdonglogistics.com/ser/CrossExport) – bediente Länder, Standard-/Express-/Economy-Versand, Flüge und Letzte-Meile-Zustellung in durchschnittlich 3 bis 6 Werktagen
- [JD Logistics: JINGDONG Logistics (Germany) (auf Englisch)](https://www.jingdonglogistics.com/ser/GlobalWarehouseDE) – deutsche Lager DE-JD-FRA1 in Buseck und DE-JD-DTM1 in Unna
- [JD Logistics: JINGDONG Logistics (France) (auf Englisch)](https://www.jingdonglogistics.com/ser/GlobalWarehouseFR) – Lager FR-JD-PAR1 in Dugny bei Paris
- [JD Logistics: JINGDONG Logistics (The Netherlands) (auf Englisch)](https://www.jingdonglogistics.com/ser/GlobalWarehouseNL) – Lager NL-JD-VEN1 in Venray und NL-JD-TIL1 in Tilburg
- [JD Logistics: JINGDONG Logistics (Poland) (auf Englisch)](https://www.jingdonglogistics.com/ser/GlobalWarehousePL) – Lager PL-JD-WAW1 in Urzut und PL-JD-WAW2 in Brwinów bei Warschau
- [JD Logistics: JINGDONG Logistics (United Kingdom) (auf Englisch)](https://www.jingdonglogistics.com/ser/GlobalWarehouseUK) – Lager GB-JD-BHM1 in Lutterworth (England)
- [JD Logistics: JINGDONG Logistics (USA) (auf Englisch)](https://www.jingdonglogistics.com/ser/GlobalWarehouseUS) – US-Lager in New Jersey, Raum Los Angeles und Georgia
- [JD Logistics: JINGDONG Logistics (Australia) (auf Englisch)](https://www.jingdonglogistics.com/ser/GlobalWarehouseAS) – Lager in Sydney
- [JDL: 运单查询 (Sendungssuche, auf Chinesisch)](https://www.jdl.com/orderSearch) – chinesischer Tracker für Frachtbrief- oder Paketnummern, mehrere Nummern möglich
- [JDL: 服务细则 (Servicebedingungen, auf Chinesisch)](https://www.jdl.com/serviceDetails) – Reiter International: Zölle und Steuern exklusive Fracht, Lieferzeitschätzung nur als Richtwert ohne Erstattung, keine Rücksendung nach Verlassen Festlandchinas
- [JDL: International Express (auf Englisch)](https://www.jdl.com/en/crossBorder/internationalC2C) – Expressversand ab Festlandchina ins Ausland und Rufnummer 0086 950616
- [JDL: Contact Us (auf Englisch)](https://www.jdl.com/en/contactUs) – Hotline 950616 und Online Consultation
- [JDL: Company Profile (auf Englisch)](https://www.jdl.com/en/profile) – Gründung April 2017, Börsengang in Hongkong Mai 2021 und Start von JoyExpress in Europa im April 2025
- [Europäische Kommission: EU Customs Reform (auf Englisch)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 3 € Zoll je Warenkategorie bis 150 € seit 1. Juli 2026 und 2 € EU-Bearbeitungsgebühr seit 1. November 2026
- [Europäische Kommission: Guidance on the EUR 3 customs duty and the Union handling fee (PDF, auf Englisch)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € Gebühr unabhängig vom Warenwert sowie Definition einer Warenkategorie
- [Europäische Kommission: Räumlicher Geltungsbereich Zoll und MwSt.](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Sonderstatus von Helgoland und Büsingen
- [DHL Deutschland: Fragen zu Zoll und Einfuhrabgaben](https://www.dhl.de/de/privatkunden/hilfe-kundenservice/themen/international/zoll/fragen-zu-kosten.html) – 7,50 € brutto Auslagepauschale und Bezahlung in Deutschland
- [DHL Deutschland: Sicherheitshinweise](https://www.dhl.de/de/toolbar/footer/sicherheitshinweise.html) – keine SMS-Zahlungsaufforderungen durch DHL und Meldung an phishing@dhl.com
- [BAZG: Empfangen von Briefen und Paketen](https://www.bazg.admin.ch/bazg/de/home/information-private/interneteinkauf--post--und-kuriersendungen/einfuhr-in-die-schweiz/empfangen-von-briefen-und-paketen.html) – Verzicht auf Zoll- und Einfuhrsteuerbeträge bis jeweils 5 CHF in der Schweiz sowie gesonderte Verzollungsgebühren
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – `JD`- und `VG`-Formate, JD-Scantexte und Ereigniscodes, GOFO-Übergabe, `jdl.com`-Abfrage und Darstellung in Peek
:::
