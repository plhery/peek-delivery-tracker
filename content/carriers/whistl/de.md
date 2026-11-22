---
title: Whistl Sendungsverfolgung: wer liefert und was tun
description: Whistl-Paket (Parcelhub) verfolgen: Herkunft der Referenz, Bedeutung der Status, welcher Paketdienst am Ende liefert und an wen du dich wendest.
slug: whistl-sendungsverfolgung
published: 2026-11-22
updated: 2026-11-22
---

Whistl, früher TNT Post UK, ist ein britischer Versanddienstleister, der Pakete für Online-Shops abwickelt und für die letzte Meile an Paketdienste wie [Royal Mail](carrier:royal-mail), [Evri](carrier:evri) oder [DPD UK](carrier:dpd-uk) übergibt. Verfolge deine Sendung im Parcelhub-Tracker von Whistl mit der Referenz aus der Versandmail des Shops oder füge den Tracking-Link oben ein. Nach der Übergabe liefern Nummer und Tracking des ausliefernden Dienstes meist genauere Angaben.

## Whistl-Sendungsnummer: wo du sie findest

Whistl veröffentlicht kein einheitliches Nummernformat. Laut den Handbüchern sucht der Tracker „nach UPI (meist dem Paket-Barcode) oder Consignment Number“: Die UPI kennzeichnet ein einzelnes Paket, eine Consignment Number alle zusammen an dich verschickten Pakete. Referenzen unterscheiden sich je nach Shop und Paketdienst — von einem Buchstaben mit vielen Ziffern (etwa `L48291736482917364`) bis zum eigenen Barcode des Zustellpartners.

Du findest sie in der Versandmail des Shops, oft direkt als Link zum Whistl-Tracker. Bei voll verfolgbaren Sendungen (*fully tracked*) benachrichtigt dich der ausliefernde Paketdienst am Liefertag zudem unter eigener Marke per E-Mail und SMS mit eigener Nummer.

Der Tracker fragt nach „Tracking Reference“ und optionaler Postleitzahl und zeigt danach pro Paket „Tracking Number:“, Scans sowie gebuchten „Carrier:“ und „Service:“.

Eine Referenz kann wie die Nummer eines anderen Paketdienstes aussehen, etwa wie der Barcode des Zustellpartners (erfundene Zeichen):

| Format (erfunden) | Wem es ähnelt |
| --- | --- |
| `L48291736482917364` | `L` plus 17 Ziffern: Whistl-Referenz, die Peek direkt bei Whistl anfragt |
| `RR482917367GB` | Royal Mail (zwei Buchstaben, neun Ziffern, `GB`) |
| `H00KLM0048291708` | 16-stelliger Evri-Barcode, siehe [Evri Sendungsverfolgung](carrier:evri) |
| `48291736482917` | 14 Ziffern: unter anderem DPD UK, siehe [DPD UK](carrier:dpd-uk) |
| `ZDBKLM482917` | XDP |

Mehr zur Einordnung von Nummern unter [Sendungsnummer-Formate](guide:tracking-number-formats).

## Bedeutung der Whistl-Sendungsstatus

Der Whistl-Tracker erscheint nur auf Englisch und überführt die Scans aller Partner in dieselben „normalisierten Ereignisse“; voll verfolgbare Pakete zeigen zusätzlich Kommentare des Paketdienstes. In typischer Reihenfolge:

| Whistl meldet | Bedeutung für dich |
| --- | --- |
| `Shipment Created` | Der Shop hat das Paket angemeldet. Noch keine Bewegung. |
| `Item receipted and processed by Whistl` | Whistl hat das Paket in einem seiner Depots bearbeitet. |
| `Handed over to Delivery Carrier` | Der auf der Seite genannte Paketdienst übernimmt die Zustellung. |
| `Handed over to Royal Mail` | Royal Mail hat es für die letzte Meile übernommen. |
| `Arrived At Depot` | In einem Depot unterwegs eingetroffen. |
| `Shipment is Out for Delivery` | Heute in Zustellung. |
| `DELIVERED - Left as authorised` | Wie erlaubt ohne Unterschrift abgelegt. |
| `Left with Neighbour` | Beim Nachbarn abgegeben. |
| `Delivered col. point` | Wartet in einer Abholstelle auf dich. |
| `No-one to receive` | Niemand angetroffen: Prüfe Benachrichtigungskarte oder Nachricht des Paketdienstes. |
| `Held by customs` | Beim [Zoll](guide:customs) zurückgehalten. |
| `Return - Delivered` | Zurückgesandt und wieder beim Absender eingetroffen. |

Drei Einträge sorgen oft für Verwirrung:

- **`Shipment Created` allein** bedeutet nur gebucht. Folgt tagelang nichts, hat der Shop das Paket womöglich noch nicht übergeben.
- **`Handed over to …`** ist oft der letzte Eintrag bei Whistl. Für teilverfolgte Sendungen (*part-tracked*) erklärt Whistl, nach der Übergabe sei keine weitere Verfolgung möglich — nutze dann die Nummer des Zustellpartners.
- **Probleme** wie `Delayed`, `Bad Address`, `Refused`, `Lost` oder `Item damaged` zeigen eine Störung an; `Shipment Deleted` heißt, der Absender hat storniert. Zeilen wie `Item Weight Changed` oder `Label reprinted` sind bloße Notizen ohne Bewegung des Pakets.

## Whistl-Zustellung und verpasste Lieferungen

Zugestellt wird durch den ausliefernden Paketdienst, nicht durch Whistl — es gelten dessen Regeln. Nach einer verpassten Zustellung hinterlässt der Dienst eine Karte oder elektronische Nachricht mit dem nächsten Schritt.

Das Parcelhub-Carrier-Handbuch von Whistl (August 2025) nennt je Dienst:

| Paketdienst | Versuche und weiteres Vorgehen |
| --- | --- |
| Royal Mail | 1 Versuch und „Something for you“-Karte; Abholung im Zustellbüro oder Postamt, sonst Rücksendung nach 18 Tagen. Passt ein Paket nicht in den Briefkasten oder braucht eine Unterschrift und du bist nicht da, wird es seit Mai 2023 sicher oder beim Nachbarn hinterlegt. |
| Evri | Bis zu drei Versuche; Einstellungen in der Evri-App; 2-Stunden-Fenster per E-Mail oder SMS. |
| DPD | Karte plus SMS oder E-Mail für Nachbar, Ablageort, neuen Termin oder Paketshop; 1-Stunden-Fenster. |
| Yodel | Bis zu drei Versuche; Inseladressen erhalten teils nur einen Versuch vor Abholung in der Postfiliale. |
| [Amazon Shipping](carrier:amazon-shipping) | Bis zu drei Versuche an 3 aufeinanderfolgenden Tagen. |
| DHL Parcel UK | Bis zu 2 Versuche. |
| Parcelforce | 1 Versuch, mit Optionen für sicheren Ablageort und Umleitung. |
| [UPS](carrier:ups) | 1 Versuch, dann digitale Nachricht mit Anweisungen zur Neuzustellung. |

Teilverfolgte Auslandssendungen gehen an internationale Partner, sodass die Regeln der örtlichen Post gelten. Whistl betreibt keine eigenen Abholstellen: `Delivered col. point` meint Shop oder Paketstation des Zustellpartners. Ein verweigertes Paket geht an den Absender zurück und wird nicht erneut zugestellt.

Als zugestellt markiert, aber nichts da? Whistls Leitfäden raten, zuerst Karte, Ablageorte und Nachbarn zu prüfen. Dann siehe [zugestellt, aber nicht erhalten](guide:delivered-not-received).

## Whistl-Paket nicht geliefert, verspätet oder verloren

Whistls Kontaktseite bittet Verbraucher, sich an den Händler zu wenden, bei dem bestellt wurde. Der Shop hält den Vertrag, stellt eine „Where Is My Item“-Anfrage bei Whistl und reicht Reklamationen ein.

Angaben aus den Whistl-Handbüchern:

- Ein unzustellbares britisches Paket geht binnen 5 Werktagen nach Eingang im Whistl-Netz an den Absender zurück.
- Bei teilverfolgten Sendungen gibt es keine Entschädigung bei Verspätung, und nach der Übergabe kann Whistl bei Nichtzustellung nicht helfen.
- Reklamationsfristen des Absenders richten sich nach dem Zustellpartner: z. B. binnen 28 Tagen ab Versand bei Evri, oder bei Royal Mail mindestens 10 Kalendertage Wartezeit vor einer Reklamation und Antrag binnen 80 Tagen. Melde dich früh beim Shop.

Wenn das Tracking stillsteht, beginne bei [warum die Sendungsverfolgung stoppt](guide:tracking-not-updating).

## Whistl-Telefonnummer und Kundenservice

Angaben auf der Kontaktseite von Whistl:

- **Fragen zu deinem Paket:** der Online-Shop, bei dem du bestellt hast.
- **Tracking:** die Schaltfläche „Whistl Tracking Portal“ auf der Kontaktseite.
- **Allgemeine Anfragen:** `01628 702 965`, zwischen 08:30 und 17:30 Uhr britischer Zeit. Diese Leitung dient Geschäftskunden von Whistl.
- **Customer-Services-E-Mail:** `packagescustomerservices@whistl.co.uk`, die Privatpersonen laut Whistl nicht bei einzelnen Sendungsanfragen helfen kann.
- **Hauptsitz:** Network House, Third Avenue, Marlow, Buckinghamshire, SL7 1EY.

In Griechenland, Zypern, Luxemburg und Malta hat Whistl kein Büro und keine Telefonnummer. Frag den Shop und nach der Übergabe den im Tracker genannten Paketdienst oder die Landespost.

## Whistl-Pakete mit Peek verfolgen

[Peek](/) verfolgt über 4.500 Paketdienste. Bei einer 18-stelligen Referenz aus `L` und 17 Ziffern fragt Peek direkt bei Whistl nach und zeigt „Whistl hat dieses Paket.“, sobald Whistl sie kennt. Andere Whistl-Referenzen haben kein eigenes Format: Füge stattdessen einen Whistl-Link ein (`web.parcelhub.net` oder `despatch.whistl.co.uk` mit `/Tracking/reference/…` bzw. `trackmyitem.whistl.co.uk` mit `/tracking/…`), der Whistl sofort erkennt („Erkannter Paketdienst“), oder wähle nach Eingabe der Referenz über „Ändern“ Whistl aus. Eine Postleitzahl ist nicht nötig. Eine Referenz im Format eines anderen Dienstes (etwa eine Royal-Mail-Nummer auf `…GB`) verfolgt jenen Dienst, nicht Whistl.

Peek liest Whistls Parcelhub-Tracker aus. Die Uhrzeiten enthalten keine Zeitzone: Peek liest sie nach der Ortszeit des Scan-Orts (britische Zeit im Vereinigten Königreich) und zeigt sie in deiner Zeitzone („Uhrzeiten in deiner Zeitzone:“ gefolgt von ihrem Namen). Peek zeigt Status, Service, den Namen des Unterzeichners nach Zustellung, die kanonische Whistl-Nummer bei Abweichung sowie den ausliefernden Paketdienst samt dessen Nummer. Erwarteter Liefertag, Gewicht oder Abholstelle stehen nicht zur Verfügung. Die Texte bleiben in Whistls Englisch, außer einfachem `Delivered`, `In transit` und `Returned to sender`, die Peek übersetzt.

Nennt Whistl Royal Mail, Parcelforce Worldwide, DPD UK, DHL Parcel UK (DHL eCommerce UK), Amazon Shipping, [InPost](carrier:inpost), UPS oder [FedEx](carrier:fedex), kann Peek dem Paket dorthin folgen. Ein Paket, das nur als Evri, DPD oder Yodel genannt wird, wird nicht automatisch weiterverfolgt: Füge die eigene Nummer des Paketdienstes hinzu oder nutze Yodels Website, da Peek Yodel nicht unterstützt. Eine Sammelreferenz für mehrere Pakete wird nicht getrackt — füge jede einzelne Paketnummer ein.

Solange das Paket in Peek geöffnet ist — oder am Liefertag bei aktiven Benachrichtigungen — fragt Peek Whistl direkt bis zu alle 10 Minuten ab, und bis zu alle 2 Minuten (bzw. alle 5 Minuten, wenn Peek den Whistl-Tracker im Browser öffnen muss) ab 6:00 Uhr in der Ortszeit des letzten Scans (britische Zeit im Vereinigten Königreich) am Tag, an dem Whistl „In Zustellung“ meldet, solange der Tracker antwortet; sonst sowie nach zwei ruhigen Tagen (12 Stunden, solange „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ gilt) stündlich. Für Benachrichtigungen wählst du „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“. Nach vier Tagen ohne Scan unterwegs, außerhalb von Zoll oder gemeldetem Problem, zeigt Peek das Datum des letzten Scans („Keine Aktualisierung seit …“) samt „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei Whistl nach.“ und dem Link „Hol dir Hilfe bei Whistl“ zur Kontaktseite. Peek kann Whistl oder den Shop nicht für dich kontaktieren.

## Häufige Fragen zu Whistl

### Gehört Whistl zu Royal Mail?

Nein. Whistl UK Ltd ist ein eigenständiges Unternehmen, das frühere TNT Post UK. Es übergibt viele Sendungen für die letzte Meile an Royal Mail, weshalb oft `Handed over to Royal Mail` erscheint.

### Wer liefert Whistl-Pakete aus?

Meist ein anderer Paketdienst: DPD, Evri, Yodel, Royal Mail, Amazon Shipping, DHL Parcel UK, Parcelforce oder UPS im Vereinigten Königreich sowie ausländische Partner.

### Ist Whistl-Tracking seriös?

Ja. Das offizielle Tracking von Whistl läuft auf `web.parcelhub.net`, `despatch.whistl.co.uk` und `trackmyitem.whistl.co.uk`, und Parcelhub gehört zur Whistl Group.

### Warum funktioniert meine Whistl-Sendungsnummer nicht?

Der Tracker meldet „Unable to find shipment“, wenn er die Referenz nicht kennt. Prüfe sie beim Shop oder verfolge die eigene Nummer des ausliefernden Paketdienstes aus dessen SMS oder E-Mail.

:::sources
- [Whistl: Contact Us](https://www.whistl.co.uk/contact) – Verbraucher an Händler verwiesen, Telefon und Zeiten, Customer-Services-E-Mail, Tracking-Portal, Hauptsitz
- [Whistl: home page](https://www.whistl.co.uk/) – Whistl UK Ltd, Geschäftskundenservices, Parcelhub Teil der Whistl Group
- [Whistl: Domestic Fully Tracked Customer Guide (October 2025)](https://www.whistl.co.uk/1817/6062/1711/Whistl_Domestic_Fully_Tracked_Customer_Guide_-_V2.2_October_2025.pdf) – UPI und Consignment, normalisierte Ereignisse, SMS/E-Mails der Partner, Benachrichtigungskarte, Verweigerung, Verlust, 5 Werktage, Absender-Reklamationen
- [Whistl: Part Tracked Services Customer Guide v1.7](https://www.whistl.co.uk/download_file/force/3171/250) – Übergabe an Royal Mail und Partner, 18 Tage, keine Entschädigung bei Verspätung, Tracking-Links, Marken-E-Mails
- [Parcelhub: Domestic Carrier Guide (August 2025)](https://www.whistlparcels.co.uk/hubfs/Parcels/Documents/Parcelhub_Carrier_Guide_Domestic_-_V1.1_Aug_2025.pdf) – eingesetzte Dienste, Zustellversuche und Optionen, Reklamationsfristen
- [Parcelhub: Part Tracked Services Customer Guide V1.0](https://www.whistlparcels.co.uk/hubfs/Parcels/Documents/Parcelhub_Part_Tracked_Customer_Guide_-_V1_September_2024.pdf) – Tracking-Portale, Suche per UPI oder Consignment, keine Hilfe nach Übergabe
- [Parcelhub: Tracking](https://web.parcelhub.net/Tracking) – Formularfelder, Ergebnisaufbau, nur Englisch
- [Whistl Despatch: Tracking](https://despatch.whistl.co.uk/tracking) – derselbe Tracker unter Whistls Namen
- [Wikipedia: Whistl](https://en.wikipedia.org/wiki/Whistl) – Umbenennung von TNT Post UK, Royal-Mail-Endzustellung
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – von Peek gelesene Felder, erfasste Status, Nummernerkennung und Abfrageintervall
:::
