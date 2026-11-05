---
title: BRT Sendungsverfolgung: Paket aus Italien, Status
description: BRT-Paket verfolgen: wo die Sendungsnummer steht, was BRTs Statusmeldungen bedeuten, wer in Deutschland, Österreich und der Schweiz zustellt, Kontakt.
slug: brt-sendungsverfolgung
published: 2026-11-03
updated: 2026-11-03
---

BRT ist ein italienischer Expresskurier und gehört wie DPD zur Geopost-Gruppe. Ein Paket verfolgst du mit dem Link in BRTs SMS oder E-Mail, im Suchfeld auf brt.it oder hier: Füg die Nummer oben ins Feld ein. Diese Nummer, den BRTcode, schickt dir BRT, sobald es das Paket hat und der Absender deine Kontaktdaten weitergegeben hat.

## BRT Sendungsnummer: wo sie steht und wie sie aussieht

Den BRTcode findest du in BRTs SMS und E-Mails und auf dem Zettel, den der Zusteller nach einem verpassten Zustellversuch hinterlässt. Keine Nachricht bekommen? Siehe [Sendungsnummer finden](guide:find-tracking-number).

Diese Formen nimmt Peek für BRT an:

| Nummer (erfunden) | Was es ist |
| --- | --- |
| `012345678912` | 12 Ziffern: die Sendungsnummer (numero spedizione), die BRT der Sendung gibt. |
| `01234567891234` | 14 Ziffern: ein BRTcode. |
| `012345678912345` | 15 Ziffern: eine Paket-ID (ID collo cliente), die der Absender vergibt. |
| `EOB01234567890123456` | `EOB` und 17 Ziffern: ebenfalls eine Paket-ID des Absenders. |

BRTs Suchformular verlangt für den BRTcode allerdings 19 Ziffern; so lange Nummern nimmt Peek für BRT nicht an. Der Sicherheitscode (security code), mit dem du eine Zustellung änderst, ist keine Sendungsnummer.

Findet BRT nichts, ist die Nummer vielleicht vertippt oder die Sendung noch nicht in BRTs System. Die [erweiterte Suche](https://vas.brt.it/vas/sped_numspe_par.htm) von BRT (Rintraccia spedizione) reicht 12 Monate zurück, mit einer Paket-ID nur 2 Monate.

14-stellige Nummern vergeben auch DPD in Deutschland, der Schweiz und Großbritannien, 12-stellige auch Mondial Relay, Colis Privé und andere. Schau deshalb in der Versand-E-Mail des Shops oder in der SMS nach, welchen Paketdienst sie nennt. Mehr in [Sendungsnummer: Welcher Paketdienst steckt dahinter?](guide:tracking-number-formats)

## BRT Sendungsverfolgung: was die Statusmeldungen bedeuten

BRTs Sendungsverfolgung auf vas.brt.it öffnet auf Italienisch und lässt sich auf Englisch, Französisch oder Deutsch umstellen. Peek liest die englische Fassung, in der die Spaltenköpfe italienisch bleiben: Data (Datum), Ora (Uhrzeit), Filiale (die BRT-Filiale, bei der du nachfragen kannst) und Evento (Ereignis). Die Meldungen unten stehen dort wörtlich so, und so zeigt sie auch Peeks Verlauf; nur jede Zustellung, auch die Abholung im BRT-fermopoint oder am Locker, heißt in Peek „Zugestellt“. Ist dein Peek auf Italienisch eingestellt, zeigt es stattdessen BRTs italienische Meldungen und eine Zustellung als „Consegnata“; hat ein Paket eine Meldung, die Peek nur auf Englisch kennt, etwa vom Locker, bleibt es auf Englisch. Rechts steht die Stufe, die Peek dafür zeigt.

| BRT schreibt | Was es für dich heißt | In Peek |
| --- | --- | --- |
| `SHIPPING INFO SENT TO BRT` | BRT hat die Daten des Absenders, das Paket aber noch nicht. Siehe [Sendungsstatus erklärt](guide:tracking-statuses). | „Etikett erstellt“ |
| `COLLECTED` | BRT hat das Paket beim Absender abgeholt. | „Beim Paketdienst eingegangen“ |
| `DEPARTED` | Es hat eine Filiale oder ein Umschlaglager von BRT verlassen. | „Unterwegs“ |
| `ARRIVED AT DEPOT` | Es ist in der Filiale, die in der Spalte Filiale steht. | „Unterwegs“ |
| `FOR DELIVERY` | Der Zusteller ist damit zu dir unterwegs. | „In Zustellung“ |
| `NOTICE OF ATTEMPTED DELIVERY` | Der Zusteller hat niemanden angetroffen und eine Nachricht hinterlassen. | „Zustellversuch“ |
| `UNKNOWN/INCOMPLETE CONSIGNEE` | Nicht zugestellt: Empfänger nicht gefunden oder Adresse unvollständig. | „Zustellversuch“ |
| `TURMOIL` | Eine Demonstration hält das Paket vorübergehend auf. | „Problem gemeldet“ |
| `ARRIVED AT BRT-fermopoint` | Es liegt in einem BRT-fermopoint (Abholstelle in einem Geschäft) für dich bereit. | „Abholbereit“ |
| `COLLECTED AT BRT-fermopoint` | Du hast es im BRT-fermopoint abgeholt. | „Zugestellt“ |
| `ARRIVED AT BRT LOCKER` | Es liegt in einem BRT-Locker (Paketautomat) bereit. | „Abholbereit“ |
| `COLLECTED AT BRT LOCKER` | Du hast es aus dem Locker geholt. | „Zugestellt“ |
| `To collect at the parcel shop` | Der zustellende Paketdienst im Ausland hat es in seinem Paketshop hinterlegt. | „Abholbereit“ |
| `DELIVERED` | Zugestellt. | „Zugestellt“ |
| `RETURNED TO SENDER` | Es geht an den Absender zurück. | „Zurück an Absender“ |

`COLLECTED` ist BRTs Abholung beim Absender, nicht deine. Kam das Paket mit Chronopost oder DPD aus dem Ausland zu BRT, ist `COLLECTED` die Übergabe an BRT; Peek zeigt dann „Unterwegs“. Der consignee in `UNKNOWN/INCOMPLETE CONSIGNEE` bist du, der Empfänger; ein unbekannter Empfänger oder eine unvollständige Adresse ist einer der Gründe, aus denen BRT ein Paket einlagert (siehe unten). Folgt `DELIVERED` auf `RETURNED TO SENDER`, ist das Paket wieder beim Absender, nicht bei dir, auch wenn Peek dann „Zugestellt“ zeigt.

## BRT-Paket nach Deutschland, Österreich oder in die Schweiz

**Wer zustellt.** Ins europäische Ausland bringt BRT Pakete über das Netz von DPD, schwere oder sperrige Sendungen über einen anderen Partner. Bei DPD-Sendungen zeigt BRTs Sendungsdetail einen Kasten, der zu DPDs Sendungsverfolgung führt. Peek fragt DPD selbst, wenn die Sendung aus einem einzigen Paket besteht, BRT dessen 14-stellige DPD-Nummer nennt (Parcel number dpd) und der neueste Scan aus einem DPD-Depot in Deutschland, Österreich oder der Schweiz kommt. Dann zeigt Peek die DPD-Nummer mit „Zustellung durch DPD Germany“, „Zustellung durch DPD Austria“ oder „Zustellung durch DPD Switzerland“ und fragt mit ihr [DPD Germany](carrier:dpd-de), DPD Austria oder [DPD Switzerland](carrier:dpd-ch). Sobald DPD einen datierten Scan zeigt, der mindestens so neu ist wie BRTs neuester, verfolgt Peek das Paket bei DPD weiter und behält BRTs Verlauf; BRT fragt Peek noch einen Tag lang mit, höchstens alle 55 Minuten. Bei einer Sendung aus mehreren Paketen bleibt Peek bei BRT.

**Zoll.** Von Italien nach Deutschland oder Österreich bleibt ein Paket in der EU, also ohne Zoll. Die Schweiz gehört nicht zur EU; wie die Verzollung dort läuft, steht in [Paket beim Zoll](guide:customs).

## Paket in Italien nicht zugestellt: Zustellversuch, BRT-fermopoint und Locker

Bist du nicht da, gibt dir BRT per E-Mail, SMS oder Zettel Bescheid. Den neuen Zustellversuch plant BRT meist für den nächsten Werktag; klappt auch der nicht, ruft dich laut BRTs Dienstleistungscharta (Carta dei servizi) die Filiale an.

Über den Link in BRTs Nachricht oder in der App myBRT (mit BRTcode und Postleitzahl oder PIN) kannst du je nach Sendung einen anderen Tag oder ein anderes Zeitfenster wählen, das Paket an einen BRT-fermopoint oder zur Abholung in die Filiale schicken lassen, ein eingelagertes Paket freigeben oder, wenn der Absender zustimmt, die Adresse ändern. Mehr in [Paket verpasst](guide:missed-delivery).

**Eingelagert (in giacenza).** Gründe sind etwa ein abwesender oder unbekannter Empfänger, eine falsche oder unvollständige Adresse oder eine verweigerte Annahme; welcher es ist, zeigt BRTs Sendungsdetail. Freigeben kannst du das Paket über BRTs Nachricht oder die Sendungsverfolgung. BRT fragt den Absender, was mit dem Paket passieren soll; kommt innerhalb von 10 Tagen nach dieser Benachrichtigung keine schriftliche Anweisung, geht es laut Charta zurück. Hat der Absender geantwortet, zeigt das Sendungsdetail auch, ob Lagerkosten anfallen und wer sie trägt, er oder du.

**In der Filiale** holst du das Paket selbst ab oder schickst jemanden mit eigenem Ausweis, einer Kopie deines Ausweises und BRTs Vollmachtsformular. Hat der Absender deine Kontaktdaten angegeben, schickt dir BRT per E-Mail oder SMS Adresse und Öffnungszeiten der Filiale und, falls fällig, den Nachnahmebetrag.

**BRT-fermopoint** sind Abholstellen in Zeitungskiosken, Tabakläden, Schreibwarengeschäften und anderen Läden, manche auch am Wochenende geöffnet. Du bekommst Bescheid per E-Mail oder SMS, und das Paket bleibt 10 Tage dort.

**BRT-Locker** stehen in Geschäften, Einkaufszentren und an Tankstellen; die im Freien sind rund um die Uhr zugänglich. Zum Abholen scannst du den QR-Code aus BRTs E-Mail oder SMS oder tippst die 8-stellige PIN ein. Pakete über 20 kg gehen weder in einen Locker noch an einen BRT-fermopoint. Funktioniert der Automat nicht oder ist das Fach leer, nimm das Kontaktformular unten in BRTs Abhol-E-Mail. Mehr in [Packstation und Paketautomat](guide:parcel-lockers).

## BRT-Paket verspätet, verloren oder beschädigt

BRTs Lieferzeiten sind laut BRT nur Richtwerte. Bewegt sich die Sendungsverfolgung nicht mehr, frag bei der Filiale aus der Spalte Filiale nach oder beim Shop, denn der hat den Vertrag mit BRT. Siehe [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating).

**Verlust oder Schaden.** Hast du die Transportkosten bezahlt, meldest du das über BRTs Schadensformular, bei einem Schaden mit Fotos; sonst sagst du dem Absender Bescheid. Sichtbare Schäden vermerkst du bei der Übergabe konkret und genau auf dem Frachtbrief, denn allgemeine Vorbehalte zählen laut BRT nicht. Versteckte Schäden meldest du BRT schriftlich, bei Sendungen innerhalb Italiens binnen 8 Kalendertagen nach Erhalt; für Sendungen ins Ausland verweist BRT auf die Fristen des CMR-Übereinkommens. Mehr in [Paket beschädigt](guide:damaged-parcel).

**Beschwerde.** Lad BRTs Beschwerdeformular (Formulario Reclami) als PDF von der [Seite zur Carta dei servizi](https://www.brt.it/it/carta-dei-servizi/), füll es aus und häng es an das Formular auf services.brt.it/it/form-agcom. BRT antwortet innerhalb von 45 Tagen. Kommt keine Antwort oder passt sie dir nicht, beantragst du per E-Mail an conciliazioni@brt.it eine Schlichtung, mit der Verbraucherorganisation Casa del Consumatore oder einer anderen Stelle deiner Wahl. Für Casa del Consumatore musst du Mitglied sein (50 € im Jahr, 25 € bei einem Streitwert bis 50 €); gelingt die Schlichtung, erstattet dir BRT den Beitrag. Danach bleibt die italienische Regulierungsbehörde AGCOM. Gilt das Paket als zugestellt, ist aber nicht da, siehe [Paket zugestellt, aber nicht erhalten](guide:delivered-not-received).

## BRT Kundenservice und Kontakt

So erreichst du BRT als Empfänger, Stand 5. November 2026:

- **Formulare** über die englische Seite [Support](https://services.brt.it/en/support), die die frühere Seite Contact us ersetzt: Du wählst deinen Fall, etwa eine Verspätung, einen Schaden oder ein Paket, das als zugestellt gilt, aber nicht angekommen ist, und kommst so zum passenden Formular. Eine Telefonnummer steht dort nicht.
- **Gebührenfreie Nummer** (numero verde) 800 278 708, montags bis freitags von 8:30 bis 18:30 Uhr, laut BRTs Charta. Ob sie aus dem Ausland erreichbar ist, sagt BRT nicht.
- **E-Mail** an assistenzaclienti@brt.it, ebenfalls laut Charta, mit Vor- und Nachnamen, den Postleitzahlen von Abholung und Zustellung und dem BRTcode; andere E-Mails bearbeitet BRT nicht.
- **Chat** auf brt.it oder deine **Filiale**, zu finden über die [Filialsuche](https://services.brt.it/en/customer-service/search-for-a-branch).

Die Bologneser Nummer in der Charta gehört zum Verwaltungssitz, nicht zum Kundenservice. Eine SMS oder E-Mail im Namen von BRT kommt dir seltsam vor? Siehe [Fake-Paket-SMS erkennen](guide:delivery-scam-texts).

## BRT Paket verfolgen mit Peek

Füg die Nummer bei [Peek](/) einfach ein. Bei `EOB`-Nummern und den meisten 15-stelligen Nummern fragt Peek nur BRT, bei 12 Ziffern BRT und andere Paketdienste mit derselben Form. Bei 14 Ziffern fragt Peek BRT gleich zusammen mit den DPD-Netzen, wenn dein Land Italien ist (in der iPhone-App die eingestellte Region, im Browser das Land, aus dem du die Nummer eingibst); in Deutschland, Österreich und der Schweiz fragt es zuerst DPD und Chronopost und BRT erst, wenn keiner von ihnen die Nummer kennt. Solange Peek sucht, zeigt es „Tracking-Dienste werden geprüft…“. Hat Peek einen anderen Paketdienst gewählt, tippst du auf „Ändern“ und wählst BRT unter „Passt zu dieser Nummer“ oder „Alle Paketdienste“; die Suche findet BRT auch unter Bartolini. Links von vas.brt.it mit `brtCode=` kannst du ebenfalls einfügen, Links von myBRT nicht.

Peek fragt BRT direkt, ohne Browser. Du siehst den Status, den Verlauf mit dem Ort jedes Scans auf der Karte und das Gewicht. Absender, Empfänger, Referenz des Absenders, Paketzahl und Volumen aus BRTs Sendungsdetail zeigt Peek nicht, ebenso wenig die Abholstelle. BRT selbst nennt keinen Liefertermin; bei einer 14-stelligen Nummer kann Peek den Termin aus der Sendungsverfolgung von DPD zeigen, die auch BRT-Nummern kennt, oder den von Chronopost, wenn Chronopost das Paket nach Italien gebracht hat. Der Termin verschwindet, sobald er verstrichen ist, oder schon vorher, wenn BRT das Paket zustellt, zurückschickt oder an eine Abholstelle bringt, wenn ein Zustellversuch scheitert oder wenn BRT ein Problem meldet. BRT nennt zu seinen Uhrzeiten keine Zeitzone; Peek liest sie als italienische Zeit, die auch in Deutschland, Österreich und der Schweiz gilt. Scans, zu denen BRT nur ein Datum nennt, bleiben ohne Uhrzeit.

**Aus dem Ausland nach Italien.** Bringen Chronopost oder DPD ein Paket nach Italien und stellt BRT es zu, ergänzt Peek bei der 14-stelligen Nummer die früheren Scans des Partners. Kennt BRT die Nummer noch nicht, fragt Peek Chronopost und DPD und zeigt deren Verlauf, sobald er das Paket auf dem Weg nach Italien zeigt. Ist BRTs Sendungsverfolgung wegen Wartung nicht erreichbar, zeigt Peek bei 14 Ziffern die Schritte aus dem DPD-Netz, jeden einmal und ohne Ort.

Peek schaut alle 10 Minuten nach, ab 6:00 Uhr alle 2 Minuten, sobald das Paket in Zustellung ist und BRT oder DPD selbst antwortet (alle 5 Minuten, wenn DPDs Abfrage einen Browser braucht). Nach zwei Tagen ohne neuen Scan (nach 12 Stunden, solange es „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ zeigt) schaut Peek nur noch stündlich nach. Für Mitteilungen wählst du „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“. BRT oder den Shop kontaktieren kann Peek nicht für dich.

## Fragen zur BRT Sendungsverfolgung

### Wo ist mein BRT-Paket gerade?

Das sagt BRTs neueste Zeile: Bei `ARRIVED AT DEPOT` liegt es in der Filiale aus der Spalte Filiale, bei `FOR DELIVERY` bringt es der Zusteller. Noch keine Zeile? Dann hat BRT das Paket wohl noch nicht, und Peek zeigt „Warten auf den Paketdienst“; ist noch kein Paketdienst gewählt, bei einer `EOB`-Nummer „Warten auf den ersten Scan von BRT“.

### Ist BRT dasselbe wie DPD?

Nein, aber beide gehören zur Geopost-Gruppe, und ins europäische Ausland bringt BRT Pakete über das Netz von DPD.

### Wie lange braucht ein Paket mit BRT?

Innerhalb Italiens meist 24 bis 48 Stunden, auf der Straße in die meisten europäischen Länder 48 bis 72 Stunden, schreibt BRT, und nennt das Richtwerte.

### Wer stellt ein BRT-Paket in Deutschland, Österreich oder der Schweiz zu?

Meist DPD in deinem Land, mit einer eigenen DPD-Nummer. Besteht die Sendung aus einem Paket und nennt BRT diese Nummer, zeigt Peek etwa „Zustellung durch DPD Germany“ und verfolgt das Paket bei DPD weiter, sobald DPD einen aktuellen Scan zeigt.

:::sources
- [BRT: FAQ (Englisch)](https://www.brt.it/en/customer-service/faq/) – BRTcode, verpasste Zustellung, Einlagerung, Lieferzeiten, Schäden, Locker, 20 kg
- [BRT: Rintraccia spedizione (Italienisch)](https://vas.brt.it/vas/sped_numspe_par.htm) – Nummernlängen, Suchzeiträume, Sprachen
- [BRT: Details of the shipment, Hilfe (Englisch)](https://vas.brt.it/vas/aiuto/aiuto.hsm?urlaiuto=sped_det_show.htm&lang=en) – Filiale, Einlagerung, Lagerkosten, DPD
- [BRT: BRT-fermopoint (Englisch)](https://www.brt.it/en/brt-fermopoint/) – Geschäfte, Wochenende, 10 Tage
- [BRT: Locker (Englisch)](https://www.brt.it/en/network/locker/) – Standorte, rund um die Uhr, QR-Code, PIN
- [BRT: Can I pick up my shipment at a BRT branch? (Englisch)](https://www.brt.it/en/faq/can-i-pick-up-my-shipment-at-a-brt-branch/) – Abholung mit Vollmacht
- [BRT: Support (Englisch)](https://services.brt.it/en/support) – Formulare je nach Fall, keine Telefonnummer
- [BRT: Carta dei servizi, PDF (Italienisch)](https://www.brt.it/wp-content/uploads/sites/275/2026/03/Carta-dei-Servizi_marzo_2026.pdf) – Geopost, DPD-Netz, Zustellversuche, Einlagerung, Schäden, CMR, Richtwerte, Kontakt, 45 Tage, Schlichtung
- [BRT: Carta dei servizi (Italienisch)](https://www.brt.it/it/carta-dei-servizi/) – Beschwerde, Schlichtung, AGCOM
- [BRT: Modulo di Conciliazione con istruzioni (Italienisch)](https://www.brt.it/wp-content/uploads/sites/275/2026/02/3.-Modulo-di-Conciliazione-con-istruzioni.pdf) – Mitgliedsbeitrag, Erstattung, andere Stellen
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Nummernformen, Wortlaute und ihre Stufen, Uhrzeiten, was Peek zeigt und wie es an DPD übergibt
:::
