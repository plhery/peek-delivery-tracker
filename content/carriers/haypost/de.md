---
title: HayPost Sendungsverfolgung: AM-Codes, Status & Kontakt
description: HayPost-Paket aus oder nach Armenien verfolgen: S10-AM-Nummern, IPS-Status im In- und Ausland, Dienste, Jerewan-Kontakte und Tracking in Peek.
slug: haypost-sendungsverfolgung
published: 2026-11-24
updated: 2026-11-24
---

HayPost (*„Haypost“ Closed Joint-Stock Company*, armenisch *Հայփոստ*) ist der nationale Postbetreiber der Republik Armenien. 1994 gegründet und 1998 als staatliche geschlossene Aktiengesellschaft (CJSC) neu organisiert, betreibt HayPost rund 777 Postfilialen in ganz Armenien, 23 Regionalzentren sowie ein internationales Brief- und Paket-Austauschzentrum und ist Mitglied im Weltpostverein (UPU) und bei PostEurop. Sendungen lassen sich auf der [Track-&-Trace-Seite von HayPost](https://www.haypost.am/en/track-trace) prüfen, oder du fügst die Sendungsnummer oben ein.

## HayPost-Sendungsnummern: S10-Format und Fundort

Deine Sendungsnummer steht auf dem Einlieferungsbeleg der Postfiliale oder in der Versand-E-Mail des Onlineshops. HayPost verfolgt standardmäßige 13-stellige UPU-S10-Barcodes mit Prüfziffer:

| Format (erfunden) | Was es ist |
| --- | --- |
| `RR482917367AM` | Internationales Einschreiben oder Päckchen aus Armenien (`R` + zweiter Buchstabe + 9 Ziffern + `AM`). |
| `CP482917367AM` | Postpaket aus Armenien (`CP` + 9 Ziffern + `AM`). |

Der HayPost-Tracker findet auch eingehende internationale UPU-S10-Codes aus anderen Ländern (mit dem zweistelligen ISO-Code des Absenderlands, etwa `US`, `DE` oder `CN`), sobald die Absenderpost elektronische Versanddaten übermittelt oder die Sendung in Armenien eintrifft. Einfache Sendungen ohne Nachweis tragen keinen S10-Code und lassen sich online nicht verfolgen. Siehe [Sendungsnummern-Formate](guide:tracking-number-formats), wenn deine Bestellung über einen privaten Kurier läuft.

## Was die HayPost-Sendungsstatus bedeuten

HayPost liefert englische Weltpostverein-Scans (IPS) mit Hinweis auf den jeweiligen Abschnitt: `(Otb)` ausgehend aus dem Absenderland, `(Inb)` eingehend im Zielland oder `(EDI-received)` als elektronische Versandmeldung der Absenderpost.

| HayPost zeigt | Was das für dich bedeutet | Peek zeigt |
| --- | --- | --- |
| `Receive item from customer (Otb)` | Vom Absender in einer Postfiliale eingeliefert und angenommen. | „Beim Paketdienst eingegangen“ |
| `Insert item into bag (Otb)`, `Send item abroad (EDI-received)`, `Receive item at office of exchange (Otb)` / `(Inb)` | Für den Auslandsversand verpackt, elektronisch ins Ausland avisiert oder im internationalen Auswechselpostamt eingetroffen. | „Unterwegs“ |
| `Send item to customs (Otb)` | Ausfuhrzollabfertigung im Ausgangs-Auswechselpostamt vor Verlassen des Ursprungslands. | „Unterwegs“ |
| `Send item to customs (Inb)`, `Record item customs information (Inb)` | Beim Einfuhrzoll des Ziellands in Prüfung oder zurückgehalten. | „Beim Zoll“ |
| `Return item from customs (Inb)` | Vom Zoll freigegeben und zurück ins Postnetz übergeben (keine Rücksendung an den Absender). | „Unterwegs“ |
| `Send item to domestic location (Inb)`, `Receive item at location (Inb)`, `Receive item at sorting centre (Inb)`, `Item was delivered to the postal office (Inb)` | Durchlauf durch inländische Sortierzentren bis zum Eingang in der zuständigen Postfiliale. | „Unterwegs“ |
| `Send item out for physical delivery (Inb)` | Heute mit dem Postzusteller unterwegs. | „In Zustellung“ |
| `Unsuccessful item delivery attempt (Inb)`, `The notification was sent to the customer: ...` | Zustellung nicht abgeschlossen; HayPost ergänzt oft Grund und nächsten Schritt (z. B. `Addressee not available; Scheduled for further action/processing today`). | „Zustellversuch“ |
| `Receive item at collection point for pick-up (Inb)` | Abholbereit in der Postfiliale. | „Abholbereit“ |
| `Deliver item (Inb)` | Übergeben und als zugestellt verbucht. | „Zugestellt“ |

Achte auf zwei leicht missverständliche Formulierungen: Erstens ist `Return item from customs (Inb)` eine gute Nachricht – der Zoll hat die Prüfung abgeschlossen und das Paket für den Weitertransport an die Post zurückgegeben. Zweitens bedeutet `Item was delivered to the postal office (Inb)` (häufig von Zielpostdiensten im Ausland gemeldet), dass die lokale Postfiliale die Sendung übernommen hat – nicht, dass sie bereits an dich übergeben wurde.

## Inlands-, Auslands- und Expressdienste von HayPost

In seinem Netz aus rund 777 Postfilialen ([Über HayPost](https://www.haypost.am/en/about-us)) bietet HayPost:

- **Inlandspost ([haypost.am/en/postal-services/domestic-postal-services](https://www.haypost.am/en/postal-services/domestic-postal-services)):** Postkarten, Briefe, Päckchen bis 2 kg, Pakete bis 20 kg, **Yerevan Express Service (YES)** für verfolgte Filial- oder Hauszustellung innerhalb Jerewans (Aufträge 09:00–16:00 Uhr am selben Tag; nach 16:00 Uhr am nächsten Werktag; Samstagsaufträge am Montag; Bestellnummer `+374 10 514 513`) sowie **HayPost Sprint** für verfolgte inländische E-Commerce-Päckchen bis 2 kg.
- **Auslandspost ([haypost.am/en/postal-services/international-delivery](https://www.haypost.am/en/postal-services/international-delivery)):** Auslandsbriefe, Päckchen bis 2 kg (Länge + Breite + Höhe ≤ 0,9 m, längste Seite ≤ 0,6 m), Pakete bis 20 kg (keine Seite über 1,05 m), M-Säcke bis 30 kg und beschleunigter EMS-Versand.
- **HayPost Express in die USA ([haypost.am/en/postal-services/Haypost-Express](https://www.haypost.am/en/postal-services/Haypost-Express)):** Luftpaketversand aus jeder armenischen Postfiliale nach Los Angeles (Abholbüros in Glendale, Van Nuys und Sylmar), bis 30 kg, durchschnittliche Laufzeit 7 bis 10 Werktage.

**HayPost-FAQ.** Die [FAQ von HayPost](https://www.haypost.am/en/faq) führen Fragen zu inländischen Regellaufzeiten, Zollgebühren, Entschädigung und Aufbewahrungsfristen für EMS-Sendungen auf; verbindliche Werte für deine Sendung erfährst du bei deiner Postfiliale oder bei HayPost.

## Verspätete, verzollte oder vermisste HayPost-Pakete

Bei Onlinekäufen auf dem Postweg von oder nach Armenien wende dich wegen Erstattungen oder Ersatzlieferungen zuerst an den Verkäufer.

**Funkstille zwischen Ländern.** Nach `Send item abroad (EDI-received)` wartet Auslandspost auf Lufttransport, Eingangssortierung und Einfuhrzoll im Auswechselpostamt des Ziellands – mehrtägige Lücken sind normal. Siehe [Warum sich die Sendungsverfolgung nicht aktualisiert](guide:tracking-not-updating).

**Zollabfertigung.** Eingehende Pakete nach Armenien sowie ausgehende `AM`-Sendungen etwa in die EU oder USA durchlaufen den Einfuhrzoll des Ziellands (`Send item to customs (Inb)`). Halte Kaufrechnung und Sendungsnummer bereit, falls der Zoll einen Wertnachweis anfordert. Siehe [Paket beim Zoll](guide:customs).

**Zugestellt, beschädigt oder zurückgesendet.** Steht `Deliver item (Inb)`, obwohl du nichts erhalten hast, frag im Haushalt und bei der örtlichen Filiale (über das [HayPost-Filialverzeichnis](https://www.haypost.am/en/our-network)) nach und kontaktiere dann Verkäufer und HayPost-Support. Siehe [Zugestellt, aber nicht erhalten](guide:delivered-not-received), [Beschädigtes Paket](guide:damaged-parcel), [Verlorenes Paket](guide:lost-parcel) und [Zurück an Absender](guide:returned-to-sender).

## Kundenservice und Kontaktdaten von HayPost

Kontakte auf [haypost.am/en/contact-us](https://www.haypost.am/en/contact-us) (und [haypost.am/am/contact-us](https://www.haypost.am/am/contact-us)):

- **Kundenservice-Telefon:** `+374 10 514-514`.
- **Geschäftskunden & Vertrieb:** `+374 10 544-444`.
- **YES Yerevan Express Bestellhotline:** `+374 10 514 513`.
- **E-Mail:** `info@haypost.am`.
- **Zentrale:** 65/5 Arshakunyats Ave., Jerewan 0061, Republik Armenien.
- **Online-Kontaktformular:** auf [haypost.am/en/contact-us](https://www.haypost.am/en/contact-us), außerdem Live-Chat auf [haypost.am](https://www.haypost.am/en).

> Vorsicht bei SMS-, WhatsApp- oder E-Mail-Nachrichten, die dich über einen fremden Link zur Kartenzahlung einer kleinen „Neuzustellungs-“ oder „Adressbestätigungsgebühr“ für ein HayPost-Paket auffordern. Prüfe deinen S10-Code direkt auf [haypost.am/en/track-trace](https://www.haypost.am/en/track-trace) oder rufe `+374 10 514-514` an. Siehe [Betrügerische Paket-SMS](guide:delivery-scam-texts).

## HayPost-Paket mit Peek verfolgen

Bei UPU-S10-Codes mit Endung `AM` (wie `RR482917367AM` oder `CP482917367AM`, Ziffern erfunden) folgt [Peek](/) automatisch HayPost als bevorzugter Ursprungspost und verfolgt auch eingehende S10-Codes nach Armenien, sobald du HayPost auswählst. Da HayPost in Peek keine eigenen Tracking-URL-Regeln hat, füge die 13-stellige S10-Nummer direkt ein; musst du HayPost manuell wählen, tippe beim Hinzufügen auf „Ändern“ oder in der Paketansicht auf „Paketdienst ändern“ und wähle HayPost (auch die Suche nach „Armenia Post“ oder „Հայփոստ“ findet ihn).

Peek liest die öffentliche englische JSON-API von HayPost direkt aus und zeigt die zugeordnete Phase („Beim Paketdienst eingegangen“, „Unterwegs“, „Beim Zoll“, „In Zustellung“, „Zustellversuch“, „Abholbereit“, „Zugestellt“), IPS-Texte (einschließlich Gründen und Folgeschritten wie `Addressee not available`), Filial-/Länderort (ohne numerische Postleitzahlen), Zielland und Zustellzeit. Armenische Scans und `Send item abroad (EDI-received)`-Zeilen tragen Jerewaner Zeit, Scans ausländischer Postdienste deren jeweilige Ortszeit (etwa französische Zeit für Paris); Peek rechnet sie auf dein Gerät um. Veröffentlicht HayPost vorab eine vordatierte `Send item abroad (EDI-received)`-Planzeile (oft Stunden im Voraus), blendet Peek sie aus, bis der tatsächliche Zeitpunkt erreicht ist. Die HayPost-API liefert kein voraussichtliches Lieferdatum, Gewicht, keinen Dienstnamen, keine Absender-/Empfängerdaten und kein Zustellfoto.

Solange du das Paket in Peek geöffnet hast oder am Zustelltag mit aktivierten Benachrichtigungen prüft Peek bis zu alle 10 Minuten; an dem Tag, an dem HayPost `Send item out for physical delivery (Inb)` meldet, alle 2 Minuten ab 6 Uhr nach der Uhrzeit des neuesten HayPost-Scans, solange der HayPost-Tracker antwortet. Sonst prüft Peek stündlich. Nach 12 Stunden mit nur „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ oder nach zwei Tagen ohne Neuigkeiten prüft es unabhängig davon stündlich, und nach sieben Tagen auf „Etikett erstellt“ alle 6 Stunden. Antwortet der HayPost-Tracker nicht und verfolgt Peek das Paket allein über Tracking-Dienste, prüft es stündlich, nach einem stillen Tag alle 3 Stunden und am Zustelltag bis zu alle 15 Minuten, solange du die Sendung geöffnet hast oder Benachrichtigungen aktiv sind.

Ist der neueste Scan vier Tage alt und das Paket steht noch auf „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“, markiert Peek es mit dem Datum dieses Scans, etwa „Keine Aktualisierung seit Mo., 12. Okt.“. Auf der Paketseite steht dann: „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei HayPost nach.“, mit dem Link „Hol dir Hilfe bei HayPost“ zur Kontaktseite von HayPost. Nach drei Wochen ohne neuen Scan heißt es: „Seit über drei Wochen nichts Neues. Ist das Paket noch nicht angekommen, frag beim Verkäufer nach. Peek fragt weiter bei HayPost nach.“

Als Benachrichtigungsstufe kannst du „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“ wählen. Peek überwacht nur Sendungsstatus und kann weder den Zoll abwickeln noch Nachforschungen bei HayPost für dich einreichen.

## Fragen zur HayPost-Sendungsverfolgung

### Wie sieht eine HayPost-Sendungsnummer aus?

HayPost verwendet 13-stellige UPU-S10-Postcodes: zwei Buchstaben, neun Ziffern und `AM` am Ende für Sendungen aus Armenien (zum Beispiel `RR482917367AM` oder `CP482917367AM`, Ziffern erfunden). Eingehende Auslandspost behält den zweistelligen Ländercode des Absenderlands.

### Warum meldet mein HayPost-Paket `Return item from customs (Inb)`?

Dieser IPS-Status bedeutet, dass der Zoll die Sendung freigegeben und für den Weitertransport an die Post zurückgegeben hat – er bedeutet nicht, dass das Paket an den Absender zurückgeht.

### Kann HayPost Pakete verfolgen, die aus dem Ausland nach Armenien gesendet wurden?

Ja. Jeder verfolgte internationale UPU-S10-Code mit Ziel Armenien lässt sich auf [haypost.am/en/track-trace](https://www.haypost.am/en/track-trace) oder in Peek abfragen, sobald elektronische Versanddaten eintreffen oder HayPost ihn in Armenien scannt.

### Wie erreiche ich den Kundenservice von HayPost?

Ruf `+374 10 514-514` an (oder `+374 10 544-444` für Geschäftskunden), schreib an `info@haypost.am` oder nutze das Kontaktformular auf [haypost.am/en/contact-us](https://www.haypost.am/en/contact-us) bzw. den Live-Chat auf der HayPost-Website.

:::sources
- [HayPost: Home](https://www.haypost.am/en) – Portal des nationalen Postbetreibers
- [HayPost: Track & Trace](https://www.haypost.am/en/track-trace) – Online-Sendungsverfolgung
- [HayPost: About Us](https://www.haypost.am/en/about-us) – gegründet 1994, CJSC seit 1998, ca. 777 Postfilialen, 23 Regionalzentren, UPU- und PostEurop-Mitglied
- [HayPost: Domestic Postal Services](https://www.haypost.am/en/postal-services/domestic-postal-services) – 2-kg-Päckchen, 20-kg-Pakete, YES Yerevan Express Service (`+374 10 514 513`) und HayPost Sprint
- [HayPost: International Delivery](https://www.haypost.am/en/postal-services/international-delivery) – Grenzen für Auslandspäckchen (2 kg), Pakete (20 kg), M-Säcke (30 kg) und EMS
- [HayPost: HayPost Express](https://www.haypost.am/en/postal-services/Haypost-Express) – Luftpaketversand nach Los Angeles (30 kg, 7–10 Werktage)
- [HayPost: Our Network](https://www.haypost.am/en/our-network) – armenisches Postfilialverzeichnis
- [HayPost: Contact Us](https://www.haypost.am/en/contact-us) – `+374 10 514-514`, `+374 10 544-444`, `info@haypost.am`, 65/5 Arshakunyats Ave., Jerewan
- [HayPost: Contact Us (Armenisch)](https://www.haypost.am/am/contact-us) – offizielle Hilfe-URL in Peek
- [HayPost: FAQ](https://www.haypost.am/en/faq) – FAQ-Themenübersicht
- [Universal Parcel Scraper: haypost](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/haypost) – S10-`AM`-Erkennung, `api.haypost.am`-JSON-Abfrage, IPS-Statuszuordnung, Jerewaner Zeit, Filter für vordatierte EDI-Einträge und Hilfe-URL
:::
