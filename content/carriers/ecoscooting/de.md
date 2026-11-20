---
title: Ecoscooting Sendungsverfolgung: Status, Paketshops & Kontakt
description: Ecoscooting-Paket in Spanien oder Portugal verfolgen: CNESP-, CNPRT- und 18-stellige Nummern, Statusmeldungen, Abholpunkte, Zustellversuche und Kontakt.
slug: ecoscooting-sendungsverfolgung
published: 2026-11-17
updated: 2026-11-20
---

Ecoscooting ist ein spanischer Zustelldienst für Online-Bestellungen von grenzüberschreitenden Plattformen (wie [AliExpress](guide:aliexpress-orders) oder [SHEIN](guide:shein-orders)) und lokalen Händlern in **Spanien und Portugal**. In Deutschland, Österreich oder der Schweiz stellt Ecoscooting nicht zu – du triffst auf den Dienst, wenn du an eine Adresse auf der Iberischen Halbinsel oder auf den spanischen Inseln bestellst: Ecoscooting übernimmt dort ausschließlich die letzte Meile bis zur Haustür oder in einen Paketshop. Verfolge deine Sendung auf [ecoscooting.com](https://www.ecoscooting.com/) mit der Nummer aus den E-Mails von Ecoscooting oder füge sie direkt oben ein.

## Ecoscooting-Sendungsnummer: Wo sie steht und wie sie aufgebaut ist

Die Sendungsnummer steht in den E-Mails, die Ecoscooting verschickt, sobald sich dein Paket auf den Weg ans Ziel macht; in der Zustellbestätigung steht sie direkt in der Betreffzeile. Einen eigenen SMS-Absendernamen veröffentlicht Ecoscooting nicht. Keine Nummer gefunden? Sieh unter [Sendungsnummer finden](guide:find-tracking-number) nach.

Ecoscooting nennt auf seiner Website kein offizielles Nummernschema, verwendet in der Praxis aber diese Formate (Ziffern erfunden):

| Format | Bedeutung |
| --- | --- |
| `CNESP12345678901234567890` | `CNESP` und 20 Ziffern: Sendung nach Spanien (fehlt das `CN` vor `ESP` + 5-stelliger PLZ + `1234` + 11 Ziffern, ergänzt Peek `CN` automatisch). |
| `CNPRT00000000000000000000` | `CNPRT` und 20 Ziffern: Sendung nach Portugal (auch ohne `CN` vor `PRT` + 7-stelliger PLZ + `1234` + 9 Ziffern stellt Peek `CN` wieder her). |
| `280010000123456789` | 18 Ziffern, spanische Ecoscooting-Etikettennummer: 5-stellige spanische Postleitzahl (`01`–`52`), `0000` und 9 Ziffern. |
| `C26111512345678` | `C` oder `E`, 6-stelliges Datum (`JJMMTT`) und 8 Ziffern: Paketnummer einer SHEIN-Bestellung, die in Spanien auch [Paack](carrier:paack) ausliefert. |

Ähnlich aussehende Nummern gehören oft zu vorgelagerten Stationen: Bei AliExpress siehst du zunächst meist `LP` mit 14 Ziffern – das ist die Logistikreferenz für [Cainiao](carrier:cainiao). Sendungen aus China durchlaufen vor der lokalen Zustellung außerdem den Zoll, etwa über den Zollagenten CACESA. Kennt `ecoscooting.com` deine Nummer noch nicht, prüfe die Nummer aus deiner Bestellübersicht; mehr dazu unter [Pakete aus China verfolgen](guide:tracking-from-china) und [Formate von Sendungsnummern](guide:tracking-number-formats).

Bei einer unbekannten Nummer zeigt `ecoscooting.com` `No data found` und bittet dich, die Bestellnummer zu prüfen.

## Was die Tracking-Status von Ecoscooting bedeuten

Die Seite `ecoscooting.com` zeigt den Sendungsverlauf je nach Browsereinstellung auf Englisch, Spanisch oder Portugiesisch (bei deutschsprachigem Browser auf Englisch, oben rechts umschaltbar). Jeder Eintrag besteht aus einer fettgedruckten Überschrift und einer erklärenden Zeile darunter; hier sind die englischen Zeilen, wie sie auch in Peek erscheinen:

| Status bei Ecoscooting | Was das für dich bedeutet |
| --- | --- |
| `Shipment data information received` | Die Bestellung wurde angekündigt; Ecoscooting hat das Paket physisch noch nicht. |
| `Shipment is in transit to Cainiao Express` | [Cainiao](carrier:cainiao) transportiert die Sendung noch auf dem Weg nach Spanien oder Portugal. |
| `Your shipment has been accepted by delivery carrier` | Ecoscooting hat das Paket in Spanien oder Portugal übernommen. |
| `Your shipment has arrived at sorting center` | Es ist in einem Sortierzentrum von Ecoscooting eingetroffen. |
| `Your shipment is out for delivery` | Heute mit dem Fahrer in Zustellung. |
| `Your shipment delivery attempt failed [Recipient not at home]` | Der Fahrer hat niemanden angetroffen. Prüfe deine E-Mails für das weitere Vorgehen. |
| `Service point closed` | Nach erfolglosen Zustellversuchen an der Tür konnte der Fahrer das Paket auch im Paketshop nicht abgeben. Es liegt dort nicht bereit; Peek zeigt „Zustellversuch“. |
| `Your package has been processed by the courier. We will notify you when it is ready for pickup at the point.` | Vom Fahrer an einen Abholpunkt übergeben, dort aber noch nicht eingebucht und noch nicht abholbereit. |
| `Your shipment has arrived at parcelshop and you‘ll receive pickup instructions shortly` | Im Paketshop abholbereit; der Abhol-PIN kommt per E-Mail oder SMS. |
| `Your shipment has been collected by consignee at the parcelshop` | Vom Empfänger im Paketshop abgeholt. |
| `Your shipment has been delivered successfully` | Zugestellt. |
| `Your shipment has expired at the parcelshop, and will be returned to sender` | Nicht rechtzeitig abgeholt – das Paket geht zurück an den Absender. |
| `Your shipment will be returned to its origin` | Rücksendung an den Ursprung / Absender läuft. |

Verwirrend ist vor allem die Übergabe an den Paketshop: Ihre fette Überschrift auf `ecoscooting.com` lautet `Delivered to PUDO` (*pick-up and drop-off point*), was wie eine Zustellung an dich klingt. Warte trotzdem unbedingt die Parcelshop-Meldung samt PIN ab, bevor du losgehst. Weitere Begriffe erläutert [Sendungsstatus verstehen](guide:tracking-statuses).

Über dem Verlauf blendet `ecoscooting.com` eine Statusüberschrift ein: Sie bleibt selbst nach erfolgreicher Zustellung auf `In transit` stehen – orientiere dich daher immer an der obersten Zeile im Verlauf. `Delivery Failure` steht bei einem als Problem markierten Scan, `Exceeded pickup time` nach abgelaufener Lagerfrist im Paketshop und `No trajectory`, solange noch keine Scans vorliegen.

## Zustellung, Paketshops und verpasste Lieferungen

In **Spanien** liefert Ecoscooting an **allen sieben Wochentagen** aus, auch samstags und sonntags; in **Portugal** von **Montag bis Samstag**. Feste Lieferzeitfenster bietet Ecoscooting in keinem der beiden Länder an.

An der Haustür unternimmt Ecoscooting **zwei Zustellversuche**; siehe auch [Zustellung verpasst](guide:missed-delivery). Nach einem Fehlversuch erhältst du den Grund per E-Mail: Lag es an einer fehlerhaften Adresse, kannst du sie direkt über den Link in dieser E-Mail korrigieren, woraufhin der Fahrer es innerhalb von **3 Werktagen** erneut versucht; auch aus anderen Gründen folgt der nächste Versuch binnen **3 Werktagen**. Nach zwei erfolglosen Versuchen entscheidet Ecoscooting je nach Fall und informiert dich per E-Mail – in Spanien wird das Paket, sofern verfügbar, an einen nahegelegenen Abholpunkt umgeleitet.

Nicht zu Hause? Über den Link in der E-Mail zum Versandstart kannst du eine andere Person zur Unterschrift bevollmächtigen oder das Paket direkt an einen Abholpunkt oder eine Paketstation umleiten lassen.

Ecoscooting verweist auf **über 8.000 Abholpunkte** (*pickup points*) sowie Paketstationen (*lockers*). Sobald das Paket dort eintrifft, bekommst du per E-Mail oder SMS den **PIN-Code**, die Adresse und die Öffnungszeiten. Hole es innerhalb von **14 Tagen** ab, sonst geht es zurück an den Absender und du kannst beim Händler eine Erstattung anfordern; siehe [Paket nicht abgeholt](guide:uncollected-parcel). Laut den spanischen FAQ lässt sich ein bereits im Abholpunkt liegendes Paket weder in einen anderen Shop noch zurück an die Hausadresse umleiten.

## Ecoscooting-Paket nicht erhalten, verloren oder gestohlen

Ecoscooting betont, dass es ausschließlich die lokale Auslieferung übernimmt: Für fehlende oder falsche Artikel, Retouren und Rückerstattungen ist der Shop oder Marktplatz zuständig, bei dem du gekauft hast (in der EU haftet der Verkäufer für Schäden „ab dem Zeitpunkt des Versands bis zum Erhalt“ und muss ohne abweichende Abrede binnen 30 Tagen liefern). Steht das Tracking seit Tagen still, lies [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating). Bei der Meldung `Your shipment has an operational anomaly occurred at the sorting center. Issue is being resolved, updates will follow` wechselt Peek auf „Problem gemeldet“: Ecoscooting behebt eine Störung im Sortierzentrum und kündigt weitere Updates an.

- **Als zugestellt gemeldet, aber nicht erhalten:** Suche die Zustell-E-Mail von Ecoscooting (auf Spanisch mit dem Betreff *„¡Bien! Hemos entregado tu paquete …“*) und klicke darin auf **„No recibido“** (*„Não recebido“* auf Portugiesisch) oder sende das Anfrageformular ab, das Ecoscooting innerhalb von **10 Werktagen** beantwortet. Mehr unter [zugestellt, aber nicht erhalten](guide:delivered-not-received).
- **Verloren oder gestohlen:** Zeigt der Verlauf `Package stolen` oder eine `"Parcel lost"`-Ausnahme, prüfe dein Postfach auf eine E-Mail mit dem Titel *„Aviso importante: Su paquete no puede ser entregado“* (Portugiesisch: *„Aviso importante: Não foi possível entregar a sua encomenda“*). Liegt sie dir vor, leite sie direkt an den Verkäufer weiter, um die Rückerstattung zu beantragen; andernfalls kontaktiere den Ecoscooting-Kundenservice. Siehe auch [verlorenes Paket](guide:lost-parcel).
- **Beschädigtes Paket:** Zu Transportschäden, Meldefristen oder Haftungshöchstbeträgen macht Ecoscooting auf seinen Seiten keine Angaben – wende dich direkt an den Verkäufer und beachte die Tipps unter [beschädigtes Paket](guide:damaged-parcel).

> In seinen FAQ rät Ecoscooting zur Vorsicht bei verdächtigen Anrufen und E-Mails: Gib niemals vertrauliche Daten an nicht vertrauenswürdige Dritte weiter. Mehr dazu unter [Gefälschte Paket-SMS erkennen](guide:delivery-scam-texts).

## Ecoscooting-Kontakt: Telefon, E-Mail und Chat

Veröffentlichte Kontaktdaten auf `ecoscooting.com` (Stand November 2026):

- **Spanien:** `+34 912 15 93 69` oder gebührenfrei `+34 800 00 03 69`; E-Mail `customersupport@ecoscooting.com`.
- **Portugal:** `+351 300 609 006`; E-Mail `suportecliente@ecoscooting.com`.
- **Servicezeiten:** Montag bis Freitag `09:00–18:00` Uhr, Samstag und Sonntag `09:00–13:00` Uhr.
- **Live-Chat und Kontaktformular:** auf der [Kundenservice-Seite von Ecoscooting](https://www.ecoscooting.com/atencionCliente), die auch erklärt, wo du das offizielle spanische Beschwerdeblatt (*Hoja de reclamaciones*) herunterlädst.

Im Impressum (*Aviso legal*) steht für Rückfragen und Beschwerden zusätzlich die Nummer `931 222 879` sowie der Hinweis auf offizielle Beschwerdeblätter, soweit gesetzlich vorgeschrieben. Betreiber ist die **Ecoscooting Delivery, S.L.** (NIF `B65874893`) mit Sitz in San Fernando de Henares bei Madrid.

## Ecoscooting-Pakete mit Peek verfolgen

[Peek](/) ordnet `CNESP`- und `CNPRT`-Nummern mit 20 Ziffern sofort Ecoscooting zu (und ergänzt ein weggelassenes `CN`, wenn das vollständige Muster dahintersteht) – ebenso die meisten 18-stelligen spanischen Etikettennummern aus Postleitzahl (`01`–`52`), `0000` und 9 Ziffern. Ausgenommen sind `3232…`-Nummern, bei denen Peek [bpost](carrier:bpost) und Ecoscooting in derselben ersten Runde abfragt (bpost zuerst, außer in Spanien und Portugal), sowie `44…`-Nummern, die zu [Quickpac](carrier:quickpac) gehören. Bei einer SHEIN-Paketnummer (`C` oder `E`, sechsstelliges Datum und 8 Ziffern) fragt Peek bei Ecoscooting an und führt unter „Passt zu dieser Nummer“ auch [Paack](carrier:paack) auf. Andere 18-stellige Nummern fragt Peek nicht bei Ecoscooting ab (sie können zu [bpost](carrier:bpost), GLS Spain oder der [Schweizerischen Post](carrier:swiss-post) gehören): Tippe dann auf „Ändern“ und wähle Ecoscooting oder füge den Tracking-Link von `ecoscooting.com` ein.

Meldet [Cainiao](carrier:cainiao) oder CACESA eine `CNESP`-/`CNPRT`-Nummer oder ein 18-stelliges Etikett, das Peek als Ecoscooting-Muster erkennt, verfolgt Peek die Sendung automatisch bei Ecoscooting weiter; sonstige 18-stellige Nummern nur, wenn Cainiao oder CACESA Ecoscooting als Zusteller nennt.

Peek zeigt den Status, den Verlauf mit den exakten Zeiten von Ecoscooting (ohne Orte, da Ecoscooting keine mitliefert), das Gewicht, den Zustellzeitpunkt und – sobald das Paket im Paketshop liegt – Name und Anschrift des Shops, die auch nach der Abholung erhalten bleiben. Ein voraussichtliches Lieferdatum gibt es nicht. Alle Zeiten erscheinen in deiner Zeitzone; auf peektracker.com und in den Apps für iPhone und Android steht am Ende des Verlaufs „Uhrzeiten in deiner Zeitzone: …“. Gibt der Fahrer das Paket im Shop ab (`Delivered to PUDO`), bleibt Peek auf „In Zustellung“ und springt erst auf „Abholbereit“, wenn der Shop den Eingang bestätigt hat – warte den PIN per E-Mail oder SMS trotzdem ab. Abhol-PIN, Telefonnummer und Öffnungszeiten des Shops, Postleitzahlen sowie Bestellreferenzen lässt Peek weg. Die Verlaufstexte bleiben im englischen Wortlaut von Ecoscooting, außer einem einzelnen `Out for delivery`, das als „In Zustellung“ erscheint. Da Ecoscooting eine falsche Nummer technisch nicht eindeutig als unbekannt abweist, bleibt eine fehlerhafte Nummer schlicht ohne Updates.

Peek fragt bis zu alle 10 Minuten ab – am Zustelltag ab 6:00 Uhr Ortszeit bis zu alle 2 Minuten, solange Ecoscootings eigener Tracker antwortet (über Tracking-Dienste alle 15 Minuten) – und benachrichtigt dich wahlweise über „Alle Updates“, „Nur wichtige“ oder „Nur am Liefertag“. Nach zwei Tagen ohne Neuigkeiten oder nach 12 Stunden auf „Etikett erstellt“ bzw. „Beim Paketdienst eingegangen“ prüft Peek stündlich.

## Häufige Fragen zur Ecoscooting-Sendungsverfolgung

### Ist Ecoscooting seriös?

Ja. Die Ecoscooting Delivery, S.L. ist ein im spanischen Handelsregister eingetragenes Unternehmen mit Sitz in San Fernando de Henares bei Madrid. Nach eigenen Angaben verfügt es über mehr als 8 Jahre Logistikerfahrung in Spanien und Portugal für internationale Online-Plattformen und lokale Händler.

### Was ist eine Lieferung mit Ecoscooting?

Ecoscooting übernimmt den lokalen letzten Zustellabschnitt in Spanien oder Portugal für Bestellungen von grenzüberschreitenden Marktplätzen – bis an die Haustür oder in einen Abholpunkt. Ansprechpartner für Kaufvertrag, Retoure und Rückerstattung bleibt der Shop.

### Liefert Ecoscooting am Wochenende?

In Spanien ja: an allen Tagen inklusive Samstag und Sonntag. In Portugal stellt Ecoscooting von Montag bis Samstag zu; Zeitfenster lassen sich in beiden Ländern nicht buchen.

### Wie lange dauert die Zustellung mit Ecoscooting?

Gegenüber Händlern wirbt Ecoscooting mit einer nationalen Laufzeit von **24 bis 48 Stunden** ab Übernahme im Inland. Für die gesamte internationale Bestelldauer ist das jedoch keine Zusage, und im Tracking wird kein Liefertag angekündigt.

:::sources
- [Ecoscooting: Customer Service / Frequent Questions](https://www.ecoscooting.com/atencionCliente) – Kontakte, Zeiten, Chat, Fehlversuche, 3 Werktage, Vollmacht, 14 Tage Abholfrist, zugestellt nicht erhalten, Verlust/Diebstahl, Hoja de reclamaciones, Betrugswarnung und über 8 Jahre Erfahrung (Englisch, Spanisch und Portugiesisch)
- [Ecoscooting: Home](https://www.ecoscooting.com/) – Spanien und Portugal, Zustelltage, zwei Versuche, Paketshop-Umleitung in Spanien, 24/48 Stunden, Tracking-Feld und Statusüberschriften
- [Ecoscooting: Home Deliveries](https://www.ecoscooting.com/toDoor) – zwei Haustürversuche, Paketshop-Option in Spanien, 24–48 Stunden
- [Ecoscooting: Network of Pickup Points and Lockers](https://www.ecoscooting.com/pickUpPoint) – über 8.000 Abholpunkte und Paketstationen
- [Ecoscooting: Aviso legal (Spanisch)](https://www.ecoscooting.com/aviso-legal) – Firmierung Ecoscooting Delivery, S.L., Sitz in San Fernando de Henares, Telefon 931 222 879 und Beschwerdeblätter
- [Your Europe: Versand und Lieferung](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_de.htm) – Händlerhaftung bis zum Erhalt und 30-Tage-Lieferfrist in der EU
- [Universal Parcel Scraper: Ecoscooting (Englisch)](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ecoscooting) – erkannte Nummernformen in Peek, Übergabe von Cainiao/CACESA, Behandlung von `Delivered to PUDO` und gelesene Felder
:::
