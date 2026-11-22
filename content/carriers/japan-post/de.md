---
title: Japan Post Sendungsverfolgung: EMS-Nummern und Status
description: Japan-Post- oder EMS-Paket aus Japan verfolgen: JP-Nummernformat, Bedeutung der Status, wer in deinem Land zustellt, Zoll und Kontakt.
slug: japan-post-sendungsverfolgung
published: 2026-11-22
updated: 2026-11-22
---

Japan Post (日本郵便, *Japan Post Co., Ltd.*) ist das japanische Postunternehmen im Besitz der Japan Post Holdings mit 24.115 Postfilialen (Stand 31. März 2026). Es versendet EMS, *International Air Packets* und Pakete ins Ausland sowie Yu-Pack-Pakete innerhalb Japans. Verfolge Sendungen über den [Track & Trace Service](https://trackings.post.japanpost.jp/services/srv/search/input?locale=en) von Japan Post (bis zu zehn Nummern gleichzeitig) oder füge die Nummer oben ein. In einem japanischen Shop bestellt? Frag den Verkäufer nach der Nummer.

## Japan-Post-Sendungsnummer: Format und Aufbau

Eine internationale Sendung aus Japan hat 13 Zeichen: zwei Buchstaben, neun Ziffern und `JP`. Die letzte der neun Ziffern ist eine Prüfziffer. Mit erfundenen Ziffern:

| Format (erfunden) | Was es ist |
| --- | --- |
| `EN482917367JP` | EMS, *International Air Packet*, internationales Paket oder Einschreiben aus Japan. |
| `482917364823` | 12 Ziffern: eine Inlandssendung in Japan, etwa ein Yu-Pack-Paket. |
| `48291736485` | 11 Ziffern: ebenfalls Inland, wie auf der Japan-Post-Trackingseite für Post und Pakete angegeben. |

Für Auslandspost akzeptiert der Tracker nur dieses Format, keine Nummern wie `CNG` oder `LP` mit 14 Ziffern. Auch ein Barcode beginnend mit `U` auf einem Päckchen oder seinem Zollformular (CN22) ist keine Sendungsnummer: Diese Sendung reist ohne Tracking. Eine Nummer mit anderer Länderkennung wie `US` stammt von der Post eines anderen Landes. Keine Nummer? Siehe [wo du deine Sendungsnummer findest](guide:find-tracking-number).

## Bedeutung der Statusmeldungen von Japan Post

Der englische Tracker von Japan Post listet den ältesten Scan zuerst, samt Datum, Uhrzeit, Postamt und Präfektur oder Land. Die Meldungen, die Peek kennt, in grober Reihenfolge:

| Japan Post meldet | Bedeutung für dich |
| --- | --- |
| `Posting/Collection` | Vom Absender eingeliefert oder von Japan Post abgeholt. |
| `En route` | Unterwegs zwischen Filialen von Japan Post. |
| `Arrival at outward office of exchange` | Im Auslands-Ausgangsamt eingetroffen. |
| `Dispatch from outward office of exchange` | Hat Japan verlassen (bzw. das Absenderland bei Sendungen nach Japan). |
| `Arrival at inward office of exchange` | Im internationalen Eingangsamt des Ziellands eingetroffen. |
| `Item presented to import Customs`, `In Customs` | Beim Zoll im Zielland. |
| `Item held at inward Office of Exchange` | Im dortigen Eingangsamt zurückgehalten. |
| `Item returned from import Customs` | Vom Zoll an die Post zurückgegeben: bewegt sich wieder. |
| `Departure from inward office of exchange` | Auf dem Weg Richtung Zustellung. |
| `Item out of sorting centre` | Hat ein Sortierzentrum im Zielland verlassen. |
| `Arrival`, `Processing at delivery Post Office` | Im zuständigen Zustellpostamt eingetroffen. |
| `Item held at delivery depot/delivery office` | Liegt im Zustelldepot/-postamt; die Zeile besagt nicht, ob du es abholen kannst. |
| `Item out for physical delivery` | In Zustellung. |
| `Final delivery` | Zugestellt. |
| `Final delivery - Collected at counter` | Am Postschalter in Japan abgeholt. |
| `Retention` | Nach verpasster Zustellung in einer japanischen Filiale gelagert, zur Abholung oder bis zu einem gewählten Tag; die Zeile unterscheidet nicht. |
| `Returned to sender` | Auf dem Rückweg zum Absender. |

Sendungen nach Japan zeigen drei weitere Zeilen: `Transported in bond` bedeutet Weitertransport unter Zollverschluss zum für deine Adresse zuständigen Auswechslungsamt; `Arrived in bond`, dass es dort angekommen ist. Die Verzollung läuft bis `Dispatched from Exchange Office`. Mehr unter [Sendungsstatus verstehen](guide:tracking-statuses).

## Japan-Post-Pakete im Ausland: Zusteller und Zoll

Nach `Dispatch from outward office of exchange` übernimmt die Post deines Landes. Bei EMS verweist Japan Post auf deren Website, wo Updates schneller erscheinen — etwa [DHL](carrier:dhl) in Deutschland, [USPS](carrier:usps), Parcelforce im Vereinigten Königreich, An Post, [Canada Post](carrier:canada-post), [Australia Post](carrier:australia-post), [Chronopost](carrier:chronopost) in Frankreich und NZ Post. Nutze dieselbe `JP`-Nummer. Zustellversuche und Abholung richten sich dann nach deiner Landespost: siehe [verpasste Zustellung](guide:missed-delivery).

**Als zugestellt gemeldet.** Ein *International Air Packet* landet ohne Unterschrift im Briefkasten (sofern keine Abgaben anfallen) und erscheint als zugestellt. Japan Post warnt zudem, dass nicht zugestellte EMS-Sendungen nach Frankreich oder ins Vereinigte Königreich als zugestellt erscheinen können, obwohl sie im nächsten Postamt liegen. EMS nach Deutschland kann als retourniert oder zugestellt markiert sein, während ein Zollagent oder ein anderer Zusteller das Paket hält. Da deine Post zugestellt hat, frag dort zuerst: siehe [zugestellt, aber nicht erhalten](guide:delivered-not-received).

**Vereinigte Staaten.** Seit dem 15. Oktober 2026 nimmt Japan Post wieder Sendungen in die USA in nahezu allen Postämtern an. Waren bis 2.500 USD müssen vorab bei den von Japan Post empfohlenen Anbietern Zonos oder SafePackage mit vorausbezahlten Abgaben registriert werden; das Etikett trägt dann DDP und eine 13-stellige Anmeldenummer. Bis zum 19. Januar 2027 sind Geschenke unter Privatpersonen bis 100 USD ausgenommen. Über 2.500 USD zahlt der Importeur in den USA.

**EU-Zoll.** Japan liegt außerhalb der EU, sodass jedes Paket durch deinen Zoll geht. Seit dem 1. Juli 2026 unterliegen Online-Einkäufe von außerhalb der EU in Bestellungen bis 150 € einem EU-Zoll von 3 € pro Artikel. Seit dem 1. November 2026 fällt zudem unabhängig vom Wert eine EU-Bearbeitungsgebühr von 2 € pro Artikel an. Ein Artikel umfasst Waren eines Pakets mit gleicher Zolltarifnummer, Beschreibung und, sofern nötig, Herkunft. Anderswo gelten lokale Vorschriften: siehe [Paket beim Zoll](guide:customs).

## Empfang eines Japan-Post-Pakets in Japan

**Zoll.** Auslandssendungen werden in einem von sechs Auswechslungsämtern in Japan verzollt. Fällige Abgaben zahlst du bei Zustellung oder am Postschalter mit dem von Japan Post zugestellten Abgabenbescheid. Sendungen über 200.000 ¥ brauchen eine Einfuhranmeldung.

**Verpasste Zustellung.** Ein Auslandspaket lagert 15 Tage im Zustellpostamt — oder einen Monat bei fälligen Abgaben: Hole es dort ab oder bestelle eine Neuzustellung. Das englische Formular von Japan Post nimmt die 6-stellige oder 11- bis 13-stellige Nummer vom Benachrichtigungsschein an und liefert nur an deine Wohnadresse; für einen anderen Ort rufe `0570-046-111` an. Siehe [nicht abgeholte Pakete](guide:uncollected-parcel).

## Japan-Post-Tracking ohne Update, verloren oder retourniert

**Stillstand nach Abreise aus Japan.** Scans kommen danach von deiner eigenen Post. Japan Post weist darauf hin, dass Auslandsdaten unvollständig oder verspätet sein und Einträge wegen Zeitunterschieden außer Reihe stehen können. Prüfe das Tracking deiner Post mit derselben Nummer: siehe [warum die Sendungsverfolgung stoppt](guide:tracking-not-updating).

**Gar kein Tracking.** *Small Packets* haben keins, und seit dem 31. Dezember 2025 nimmt Japan Post keine Waren-Päckchen mehr als Einschreiben an. Ein verfolgbares Päckchen reist als *International Air Packet*. Land-/Seepost ist nur als internationales Paket verfolgbar.

**Verloren oder beschädigt.** Der Absender stellt einen Nachforschungsantrag per Formular im Aufgabepostamt (nicht telefonisch): binnen 6 Monaten für Einschreiben, Wertsendungen und internationale Pakete; bei EMS bestimmt das Zielland die Frist. Ersatz gibt es für Einschreiben, Wertbriefe, internationale Pakete und EMS (kostenlos bis 20.000 ¥, versicherbar bis 2 Mio. ¥); *International Air Packet* bietet keinen Ersatz. Bei Online-Bestellungen wende dich an den Shop: siehe [verlorene Pakete](guide:lost-parcel). Bei Schäden oder Fehlmenge bewahre alles auf und melde es deiner örtlichen Postfiliale: siehe [beschädigte Pakete](guide:damaged-parcel).

**Retourniert.** Laut Japan Post kann eine falsche Retoureneingabe im Ausland nach der Zustellung `Returned to sender` stehen lassen; die Zeile nennt dann das Zielland statt einer Präfektur. Japan Post bittet Absender zudem, keine IOSS-Shop-Bestellungen unter 150 € nach Belgien, Dänemark, Deutschland, Finnland, Frankreich, Luxemburg, Österreich oder Portugal zu schicken, da dortige Zollbehörden oder Postdienste sie zurücksenden können. Siehe [Rücksendung an Absender](guide:returned-to-sender).

## Japan-Post-Kundenservice und Telefonnummern

Telefonnummern von Japan Post (aus dem Ausland nicht erreichbar):

- **Customer Service Center:** `0120-5931-55` (kostenlos), mobil `0570-046-666` (kostenpflichtig).
- **Auf Englisch:** `0570-046-111` (kostenpflichtig), auch für Neuzustellungen.
- **Zeiten:** täglich 8:00 bis 21:00 Uhr japanischer Zeit.

Aus dem Ausland wende dich an Absender oder Shop, die im Aufgabepostamt eine Nachforschung einleiten können. Japan Post erklärt, telefonisch keine über die Website hinausgehenden Tracking-Infos geben zu können.

## Japan-Post-Paket mit Peek verfolgen

[Peek](/) verfolgt über 4.500 Paketdienste und erkennt zwei Buchstaben, neun Ziffern und `JP` bei richtiger Prüfziffer sofort als Japan Post („Erkannter Paketdienst“); mit „Ändern“ wählst du einen anderen. Stimmt die Prüfziffer nicht, meldet Peek „Die Prüfziffer dieser Nummer geht nicht auf.“: Prüfe die Versandmail oder tippe „Wie eingegeben verfolgen“. Links zum Japan-Post-Tracker funktionieren ebenfalls. Nummern mit `U` am Anfang werden nicht als Japan Post erkannt und dort nicht abgefragt, da Japan Post sie nicht verfolgt.

Eine 12-stellige Inlandsnummer gehört nicht eindeutig Japan Post. Füge sie dennoch ein: Peek fragt passende Dienste ab (aus Japan Yamato und Japan Post zuerst) und zeigt „Japan Post hat dieses Paket.“, sobald Japan Post sie kennt. Bei 11 Ziffern fragt Peek Japan Post nicht automatisch — wähle es über „Ändern“. Unter Japan Post eingeordnet zeigt ein Paket „Warten auf Japan Post“ bis zum ersten Scan oder „Sendungsnummer prüfen“, wenn Japan Post die Nummer nach zwei Tagen noch nicht kennt.

Peek zeigt jeden Scan im englischen Wortlaut von Japan Post mit Postamt und Ort (außer `Returned to sender`, angezeigt als „Zurück an Absender“) und ordnet die Meldungen eigenen Phasen zu („Unterwegs“, „Beim Zoll“ usw.). Die Spalte „Details“ von Japan Post (die Gründe wie eine unbekannte Adresse nennen kann) bleibt weg. Zeiten werden nach der Ortszeit des genannten Orts (oder benachbarter Scans) gelesen und in deiner Zeitzone angezeigt („Uhrzeiten in deiner Zeitzone:“ gefolgt von ihrem Namen). Bei Inlandspaketen mit vom Absender gewähltem Liefertag zeigt Peek Tag und Zeitfenster bis zur Zustellung oder bis zum Tagesablauf als erwartete Lieferung.

Aus vielen Ländern fragt Peek mit derselben `JP`-Nummer auch deine Landespost: USPS (USA), [Royal Mail](carrier:royal-mail) (UK, bzw. Parcelforce bei Nummern mit `E` für EMS), An Post (Irland), Canada Post, Australia Post, NZ Post, [DHL](carrier:dhl) (Deutschland), [La Poste](carrier:la-poste) (Frankreich) und jede weitere EU-Post außer der niederländischen. Sobald diese neuere Scans hat, wechselt Peek unter Erhalt der Japan-Post-Historie dorthin. Aus Korea, China, Hongkong oder Singapur wird keine andere Post gefragt. Umgekehrt fragt Peek Japan Post bei fremden Postnummern (außer `U`-Nummern), sobald deren Tracking Japan als Ziel nennt — oder ohne Zielangabe, wenn du aus Japan suchst.

Solange das Paket in Peek geöffnet ist — oder am Liefertag bei aktiven Benachrichtigungen — fragt Peek direkt bei Japan Post bis zu alle 10 Minuten ab, und ab 6:00 Uhr in der Ortszeit des letzten Scans (japanischer Zeit in Japan, bzw. ab 30 Minuten vor einem gewählten Inlands-Zeitfenster) am Tag der Zustellung bis zu alle 2 Minuten, solange der Japan-Post-Tracker antwortet (übernimmt eine andere Post, gilt deren Abfrageintervall); sonst sowie nach zwei ruhigen Tagen (12 Stunden, solange „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ gilt) stündlich. Für Benachrichtigungen wählst du „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“. Nach vier Tagen ohne Scan unterwegs, außerhalb von Zoll oder gemeldetem Problem, zeigt Peek das Datum des letzten Scans („Keine Aktualisierung seit …“) samt „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei Japan Post nach.“ und dem Link „Hol dir Hilfe bei Japan Post“. Peek kann weder Japan Post noch deine Post oder den Shop für dich kontaktieren.

## Häufige Fragen zu Japan Post

### Was ist EMS von Japan Post?

Der schnellste internationale Dienst von Japan Post für Dokumente und Waren bis 30 kg in über 120 Länder und Gebiete mit Sendungsverfolgung. Die Nummern enden auf `JP`.

### Warum aktualisiert sich mein Japan-Post-Tracking nicht?

Lautet der letzte Eintrag `Dispatch from outward office of exchange`, hat das Paket Japan verlassen: Prüfe deine Landespost mit derselben Nummer und plane Flug- und Zollzeit ein.

### Kann ich ein Japan-Post-Päckchen verfolgen?

Nur wenn es als *International Air Packet* verschickt wurde. Ein *Small Packet* hat kein Tracking, und ein Barcode mit `U` ist keine Sendungsnummer. Frag den Absender nach der Versandart.

### Wie lange dauert die Lieferung mit Japan Post?

Laut Japan Post etwa 5 bis 21 Tage für ein *International Air Packet*. EMS ist am schnellsten; der Tarife- und Laufzeitenrechner auf `post.japanpost.jp` nennt die Dauer pro Zielort.

:::sources
- [Japan Post: Track & Trace Service](https://trackings.post.japanpost.jp/services/srv/search/input?locale=en) – Tracker, Nummernbeispiele
- [Japan Post: Item numbers on labels](https://trackings.post.japanpost.jp/slipEn.html) – 12-stellige Yu-Pack-Nummer
- [Japan Post: Notices Concerning EMS Track & Trace Service](https://www.post.japanpost.jp/service/send/oversea/information/ems_search_en.html) – Nummern, Scan-Reihenfolge, Auslandsscans, Spalte Details, Retouren, In-bond-Zeilen
- [Japan Post: Link to Overseas Post Offices](https://www.post.japanpost.jp/service/send/oversea/list/delivery/ems/link_en.html) – Partnerposten für EMS
- [Japan Post: Products and services comparative table](https://www.post.japanpost.jp/service/send/oversea/list/hikaku_en.html) – Tracking nach Produkt
- [Japan Post: International Air Packet](https://www.post.japanpost.jp/service/send/oversea/list/delivery/airpacket_en.html) – Briefkastenzustellung, Laufzeit
- [Japan Post: EMS](https://www.post.japanpost.jp/service/send/oversea/list/delivery/ems/index_en.html) – Dienst, Laufzeitenrechner
- [Japan Post: EMS Insurance Program](https://www.post.japanpost.jp/service/send/oversea/list/delivery/ems/damage_en.html) – Versicherungsschutz
- [Japan Post: Mail destined for the United States (23 October 2026)](https://www.post.japanpost.jp/service/send/oversea/information/2026/1023_01_en.html) – US-Regeln
- [Japan Post: EU duty-free exemption removal (1 July 2026)](https://www.post.japanpost.jp/service/send/oversea/information/2026/0701_01_en.html) – IOSS-Bestellungen
- [Japan Post: Searches for undelivered items](https://www.post.japanpost.jp/question/oversea/18_en.html) – Nachforschungen
- [Japan Post: Searches for EMS](https://www.post.japanpost.jp/question/oversea/138_en.html) – EMS-Nachforschungen
- [Japan Post: Compensation](https://www.post.japanpost.jp/question/oversea/21_en.html) – versicherte Sendungen
- [Japan Post: Compensation claims](https://www.post.japanpost.jp/question/oversea/17_en.html) – Schäden
- [Japan Post: Holding period for parcels from overseas](https://www.post.japanpost.jp/question/oversea/23_en.html) – Lagerfrist
- [Japan Post: Delivery Flow](https://www.post.japanpost.jp/service/send/oversea/use/flow/receive_en.html) – Zoll in Japan
- [Japan Post: Application for redelivery](https://www.post.japanpost.jp/receive/redelivery_form/) – Neuzustellung
- [Japan Post: Yu-Pack](https://www.post.japanpost.jp/service/you_pack/index_en.html) – gewählter Liefertag
- [Japan Post: Inquiry by Phone](https://www.post.japanpost.jp/question/oversea/tel_en.html) – Rufnummern, Zeiten
- [Japan Post: Corporate Information](https://www.post.japanpost.jp/about/profile_en.html) – Unternehmen, Postfilialen
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 3 € Zoll, 2 € Bearbeitungsgebühr
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – pro Artikel, Definition Artikel
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Scan-Texte, von Peek gelesene Felder, Folgepost
:::
