---
title: FAR International Sendungsverfolgung: Status und Kontakt
description: Verfolge ein FAR-International-Paket (泛远国际) aus China: FAR…YQ-Format, Bedeutung der Status, letzter Zusteller, EU-Zoll und Kontaktmöglichkeiten.
slug: far-international-sendungsverfolgung
published: 2026-11-22
updated: 2026-11-22
---

FAR International (泛远国际, FAR International Logistics) ist ein 2004 gegründetes Logistikunternehmen für grenzüberschreitenden Versand in Hangzhou, China. Online-Shops und Marktplätze nutzen es für Auslandssendungen: FAR holt und prüft die Pakete in China, während Partner den Transport ins Ausland, die Verzollung und die Zustellung vor Ort übernehmen. Verfolge die Nummer auf [FARs englischer Track-and-Trace-Seite](https://www.far800.com/en/logistics) oder füge sie oben ein. Die Nummer erhältst du vom Shop in den Versandinfos deiner Bestellung.

## FAR International Sendungsnummer: wie sie aussieht

FAR veröffentlicht kein offizielles Format. Diese Struktur erkennt Peek als FAR International (erfundene Ziffern):

| Nummer | Bedeutung |
| --- | --- |
| `FARAA4829173654YQ` | `FAR`, zwei Buchstaben, 10 Ziffern, dann `YQ`: eine FAR-International-Nummer. |
| `FARAA482917365YQ` | Nur 9 Ziffern: passt nicht zum FAR-Format. Peek ordnet sie nicht FAR International zu; prüfe auf eine fehlende Ziffer. |

Kleinbuchstaben, Leerzeichen und Bindestriche spielen in Peek keine Rolle. FARs Tracker erwartet die vollständige Nummer und verarbeitet bis zu 50 Nummern gleichzeitig, eine pro Zeile.

**Wo du sie findest.** In der Versand-E-Mail oder auf der Bestellseite. Sonst helfen [Sendungsnummer finden](guide:find-tracking-number) sowie die Seiten zu [Temu-Bestellungen](guide:temu-orders) und [SHEIN-Bestellungen](guide:shein-orders).

**Eine zweite Nummer.** Die Ergebnisseite von FAR kann zusätzlich eine Nummer für die letzte Meile (尾程单号) anzeigen: Das ist die Nummer des Zustelldienstes in deinem Land, mit der du dort direkt weitersuchen kannst.

## FAR International Tracking-Status erklärt

Lager- und Flugscans von FAR verbinden oft Chinesisch mit Englisch, etwa `仓库收到包裹 Arrived at Warehouse`: Peek behält das Englische. Spätere Zeilen stehen teils nur auf Englisch oder Chinesisch. Jede Zeile nennt Uhrzeit und Ort, und FARs Seite hat eine Spalte für die Zeitzone. Die häufigsten Meldungen der Reihe nach:

| FAR-Meldung | Was sie für dich bedeutet | Anzeige in Peek |
| --- | --- | --- |
| `Arrived at Warehouse` | Das Paket ist im FAR-Lager in China eingetroffen. | „Beim Paketdienst eingegangen“ |
| `Inspection by Warehouse` | Das Lager prüft die Ware (FAR öffnet Sendungen bei der Annahme zur Kontrolle). | „Beim Paketdienst eingegangen“ |
| `Await Release from Sender` | Das Lager hält das Paket bis zur Freigabe durch den Verkäufer zurück. | „Beim Paketdienst eingegangen“ |
| `Departed Warehouse`, `Pickup Scan` | Aus dem FAR-Lager ausgelaufen und für die nächste Etappe abgeholt. | „Unterwegs“ |
| `In Transit to Export Port` | Auf dem Weg zum Flughafen oder Hafen in China. | „Unterwegs“ |
| `Export Custom Clearance Processing and Flight Booking` | Chinesische Ausfuhrabfertigung und Flugbuchung — ein Routineschritt vor dem Abflug, nicht der Zoll deines Landes. | „Unterwegs“ |
| `Scheduled to Take off`, `Export Scan` | Wartet auf den Abflug, dann für den Export gescannt. | „Unterwegs“ |
| `Arrived at departure transport hub`, `Leaving from departure country`, `Flight has departed` | Am Abflughub eingetroffen, dann ausgeflogen. | „Unterwegs“ |
| `Arrived at Facility`, `Departed from Facility` | In einem Umschlagzentrum angekommen und wieder abgefahren. | „Unterwegs“ |
| `Last Mile Label Create` | Das Etikett des Zustellers wurde erstellt (erscheint oft erst nach dem Abflughub — kein Rückschritt). | vorheriger Status |
| `At destination country` | Im Zielland eingetroffen; als Nächstes folgen Zoll und Sortierung. | „Unterwegs“ |
| `Picked up by Customs clearance company`, `Clearance started`, `开始清关` | Von einem Zollagenten übernommen; Einfuhrverzollung gestartet. | „Beim Zoll“ |
| `Customs Released`, `Import Scan`, `进口清关完成` | Vom Einfuhrzoll freigegeben. | „Unterwegs“ |
| `Handover To Last Mile`, `Received by Last Mile Provider`, `Received by local delivery company` | FAR hat das Paket an den Zustelldienst in deinem Land übergeben. | „Unterwegs“ |
| `包裹开始派送` | Dieser Zusteller hat das Paket übernommen; seine Scans folgen (es ist noch nicht zwingend auf Zustelltour). | „Unterwegs“ |
| `Transferred to Next Facility`, `In transit`, `In Transit to Destination` | Unterwegs zwischen Sortierzentren. | „Unterwegs“ |
| `Out For Delivery` | In Zustellung. | „In Zustellung“ |
| `DELIVERED` | Zugestellt. | „Zugestellt“ |

Nach der Übergabe enthält FARs Verlauf teils Originalzeilen des Zustellers, etwa von FedEx (`Left FedEx origin facility`, `At local FedEx facility`, `On FedEx vehicle for delivery` als „In Zustellung“ und `Package delivered` als „Zugestellt“). Bei anderen Formulierungen wertet Peek geläufige Schlüsselwörter wie delivered oder customs aus; unklare Zeilen bleiben im Verlauf mit dem vorherigen Status stehen.

Laut FAR-FAQ deutet `RETURN` im Tracking auf eine Zustellstörung hin. Bei einer Zeile, die nur `RETURN` enthält, ändert Peek den Status nicht selbst: Lies die Meldung und wende dich an den Verkäufer.

## Wer FAR-International-Pakete zustellt

FAR bezeichnet sich als „Light-Asset“-Logistiker: Eigene Standorte in China übernehmen Annahme, Kontrolle, Verpackung, Etikettierung und Sortierung; externe Partner (Flughafen- und Hafenbetreiber, Airlines, Reedereien, Zollagenten und Drittdienstleister) erledigen Zoll, Langstrecke und letzte Meile. Welche Zusteller in welchem Land liefern, nennt FAR nicht — schau nach der Last-Mile-Nummer auf FARs Ergebnisseite.

Regeln zu Zustellversuchen, Benachrichtigungen oder Abholstellen veröffentlicht FAR nicht: Sie gelten jeweils beim lokalen Zusteller. Verfolge dessen Nummer auf seiner Website und lies [Zustellung verpasst](guide:missed-delivery).

**Zoll in der EU.** Online-Bestellungen von außerhalb der EU unterliegen seit dem 1. Juli 2026 einem Zoll von 3 € pro Warenart bei Sendungen bis 150 € sowie seit dem 1. November 2026 einer EU-Bearbeitungsgebühr von 2 € pro Warenart unabhängig vom Wert. Als eine Warenart gelten Güter mit gleicher Zolltarifnummer, Beschreibung und gegebenenfalls gleichem Ursprung. FAR beschreibt nicht, wie diese Beträge erhoben werden. Außerhalb der EU gelten die jeweiligen Landesregeln; mehr unter [Paket hängt beim Zoll](guide:customs).

**Wenn der Zoll Unterlagen verlangt.** Laut FAR-FAQ erfordert eine Zollstörung im Zielland die Mitwirkung des Empfängers bei den örtlichen Behörden. Scheitert die Verzollung nach Fristablauf, wird die Sendung gemäß lokalen Vorschriften aufgegeben oder zurückgesandt; die Kosten trägt der Absender.

## FAR-International-Paket aktualisiert sich nicht, verloren oder zurück

FAR arbeitet für Shops und Plattformen, die in der FAQ an ihren Kundenbetreuer verwiesen werden. Bei Verspätung, Verlust, Schaden oder Rücksendung ist daher zuerst der Verkäufer zuständig.

**Noch nichts sichtbar.** Findet FAR noch keine Daten, zeigt Peek „Warten auf FAR International“ und fragt weiter nach. Bleibt es einige Tage leer, prüfe die Nummer beim Verkäufer.

**Tagelang keine Meldung.** Die FAR-FAQ weist darauf hin, dass Flugscans verzögert erscheinen können. Kommt unterwegs vier Tage lang keine neue Zeile, zeigt Peek in deiner Liste den Tag des letzten Scans („Keine Aktualisierung seit …“) und auf der Paketseite „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei FAR International nach.“. Siehe [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating) und [Pakete aus China verfolgen](guide:tracking-from-china).

**Zugestellt, aber nicht da.** Die FAR-FAQ rät, rund um das Haus zu suchen (Ecken, Eingang, Postfach, Paketfach oder ähnliche Ablagen) und Verwandte sowie Nachbarn zu fragen. Bleibt das Paket verschwunden, informiere sofort den Verkäufer: Eine Nachforschung muss binnen **14 Tagen** ab dem Zustelldatum über den Verkäufer bei FAR gestartet werden. Siehe [zugestellt, aber nicht erhalten](guide:delivered-not-received).

**Beschädigt.** Bewahre den Umkarton und die beschädigte Ware auf, wie die FAR-FAQ verlangt, und kontaktiere den Verkäufer für die Schadensmeldung bei FAR. Siehe [beschädigte Pakete](guide:damaged-parcel).

**Zurückgesandt oder verloren.** Bitte den Shop um Ersatz oder Erstattung. Siehe [zurück an Absender](guide:returned-to-sender) und [verlorene Pakete](guide:lost-parcel).

## FAR International Kundenservice und Kontakt

Angaben auf [FARs englischer Website](https://www.far800.com/en):

- **Globale Hotline:** 400-128-5656 (chinesische Servicenummer; die Leistungsseite nennt eine 24/7-Hotline für Kunden, ohne Angaben zur Erreichbarkeit aus dem Ausland oder zu Sprachen).
- **E-Mail:** csc@far800.com.
- **Adresse:** Room 201, No. 22 Great Wall Street, Gongshu District, Hangzhou, Zhejiang Province, China, Postleitzahl 310022.

Bei Fragen zu deiner Bestellung wende dich zuerst an den Verkäufer, der FAR beauftragt hat.

## FAR-International-Paket mit Peek verfolgen

[Peek](/) erkennt FAR International sofort an einer `FAR…YQ`-Nummer („Erkannter Paketdienst“). Auch ein far800.com-Ergebnislink mit `key=` und FAR-Nummer funktioniert. Jede andere Nummer — etwa für die letzte Meile — kannst du ebenfalls einfügen: Peek verfolgt über 4500 Paketdienste und erkennt den Dienst, wenn das Format eindeutig ist. Passt es zu mehreren Diensten, zeigt Peek „Tracking-Dienste werden geprüft…“; passt es zu keinem, sucht Peek nach dem Hinzufügen weiter. Falls nötig, tippe auf „Ändern“ und wähle unter „Alle Paketdienste“, oder nutze „Paketdienst ändern“ bei einem bereits hinzugefügten Paket. FAR International antwortet nur auf `FAR…YQ`-Nummern.

Peek liest FARs eigenes Tracking direkt aus: Status, jede Zeile samt Ort und Zeit, Zustellzeit und Nummer für die letzte Meile. Nennt FAR eine Zeitzone, rechnet Peek die Zeit daraus in deine Gerätezeitzone um (am Ende des Verlaufs steht „Uhrzeiten in deiner Zeitzone:“ mit ihrem Namen). Lieferprognose und Gewicht liefert FAR nicht.

Enthält FARs Tracking eine Last-Mile-Nummer, fragt Peek auch den zugehörigen Dienst ab: bei einer Postnummer (zwei Buchstaben, neun Ziffern, zwei Buchstaben) die Post deines Landes — etwa [Correos](carrier:correos-spain) in Spanien — oder den eindeutigen Dienst des Formats. Sobald dieser einen mindestens ebenso aktuellen Scan wie FAR zeigt, wechselt Peek unter Erhalt des Verlaufs dorthin und zeigt den Zusteller an (z. B. „Zustellung durch Correos“). Ohne solchen Dienst bleibt Peek bei FAR.

Solange du die Sendung in Peek geöffnet hast — oder am Zustelltag mit aktivierten Benachrichtigungen — fragt Peek direkt bei FAR bis zu alle 10 Minuten nach, und ab 6:00 Uhr Ortszeit am Zustelltag bis zu alle 2 Minuten; sonst sowie nach zwei Tagen ohne Neuigkeiten (oder 12 Stunden bei „Etikett erstellt“ bzw. „Beim Paketdienst eingegangen“) stündlich. Benachrichtigungen stellst du auf „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“. Kontakt zu FAR oder dem Verkäufer übernimmt Peek nicht.

## Häufige Fragen zu FAR International

### Ist FAR International seriös?

Ja. FAR International Logistics wurde 2004 in Hangzhou gegründet. Die Muttergesellschaft FAR International Holdings Group entstand im November 2022 und ging laut FAR-Meldungen im Dezember 2023 an die Börse in Hongkong. FAR gibt an, seit 2005 Dienstleister für UPS China zu sein, 2023 ein grenzüberschreitendes Logistikprojekt mit Temu gestartet und 2024 strategische Kooperationen mit TikTok und SHEIN begonnen zu haben.

### Wie lange dauert der Versand mit FAR International?

Für Geschäftskunden nennt FAR folgende Richtwerte: Express 3 bis 5 Werktage, Standard 6 bis 9 und Economy 10 bis 30 Werktage, abhängig von der Ziel-Postleitzahl. Maßgeblich ist die Lieferangabe deines Shops.

### Was bedeutet „Await Release from Sender“?

Das FAR-Lager in China hat das Paket, wartet aber vor dem Weiterversand auf die Freigabe des Verkäufers. Bleibt der Status stehen, frag beim Shop nach.

### Wie erreiche ich FAR International?

Über die globale Hotline 400-128-5656 oder per E-Mail an csc@far800.com. Bei Bestellfragen wende dich zuerst an den Verkäufer.

:::sources
- [FAR International: Home](https://www.far800.com/en) – Unternehmen, 泛远国际, 2004 und 2022, Börsengang Hongkong (22. Dezember 2023), Hotline, E-Mail, Adresse
- [FAR International: Track and Trace](https://www.far800.com/en/logistics) – vollständige Nummer, bis zu 50 Nummern zeilenweise
- [FAR International: About FAR](https://www.far800.com/en/about) – 2004, 2022, UPS China 2005, Temu 2023, TikTok und SHEIN 2024
- [FAR International: Cross-border End-to-end Delivery](https://www.far800.com/en/service/info) – Light-Asset-Modell, Partner für Zoll und letzte Meile, Kontrolle bei Annahme, Laufzeiten Express/Standard/Economy, 24/7-Hotline
- [FAR International: FAQs](https://www.far800.com/en/service/qa) – verzögerte Flugdaten, RETURN, zugestellt aber nicht erhalten (14 Tage), beschädigte Pakete, Zoll im Zielland
- [FAR International: Privacy Policy](https://www.far800.com/en/support/privacy) – Firmenname, Hotline, E-Mail, Adresse
- [Europäische Kommission: EU-Zollreform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 3-€-Zoll seit 1. Juli 2026 und 2-€-Bearbeitungsgebühr seit 1. November 2026
- [Europäische Kommission: Leitfaden zu 3-€-Zoll und Bearbeitungsgebühr (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € pro Warenart unabhängig vom Wert und Definition einer Warenart
- [Universal Parcel Scraper: FAR-International-Adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/far-international) – FAR…YQ-Format, Links, Wortlaute, Last-Mile-Nummer, Zeitzonen und Übergabe
:::
