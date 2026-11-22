---
title: Ukrposhta Sendungsverfolgung: Nummern, Status & Abholung
description: Verfolge ein Ukrposhta-Paket (Ukrainische Post): 13-stellige und UA-Nummern, englische Status auf Deutsch, Filialabholung und internationale Sendungen.
slug: ukrposhta-sendungsverfolgung
published: 2026-11-22
updated: 2026-11-22
---

Ukrposhta (Укрпошта) ist die nationale Post der Ukraine. Verfolge dein Paket auf track.ukrposhta.ua, in der Ukrposhta-App oder indem du die Nummer oben einfügst. Die Nummer kommt vom Absender: 13 Ziffern innerhalb der Ukraine oder zwei Buchstaben, neun Ziffern und zwei Buchstaben für Sendungen ins oder aus dem Ausland.

## Ukrposhta Sendungsnummer

Ukrposhta verwendet zwei Formate; die 13-stellige Inlandsnummer endet auf einer Prüfziffer (Modulo 11).

| Format (erfunden) | Bedeutung |
| --- | --- |
| `0548291736543` | Eine Inlandssendung innerhalb der Ukraine: 13 Ziffern. |
| `RR482917367UA` | Eine internationale Sendung aus der Ukraine: zwei Buchstaben, neun Ziffern, `UA`. |
| `RR482917367US` | Eine Sendung in die Ukraine behält die Nummer der absendenden Post samt Ländercode. |

Keine Nummer? Frag den Absender oder sieh unter [Sendungsnummer finden](guide:find-tracking-number).

[Nicht verwechseln](guide:tracking-number-formats): Frachtbriefe von Nova Poshta innerhalb der Ukraine haben 14 Ziffern, und viele Meest-Nummern enden auf `G`.

## Was Ukrposhta Tracking-Status bedeuten

Peek ruft Ukrposhtas englische Texte ab. Die üblichen Meldungen, ungefähr der Reihe nach:

| Ukrposhta-Status | Was er für dich bedeutet |
| --- | --- |
| `Created online. Waiting acceptance` | Der Absender hat [das Etikett online erstellt](guide:tracking-statuses); Ukrposhta hat die Sendung noch nicht. |
| `Registered` | Von Ukrposhta angenommen. |
| `Departed from the Branch` | Hat die Einlieferungsfiliale verlassen. |
| `Arrived to the Logistics Center` | In Sortierung auf dem Weg durch die Ukraine. |
| `Arrival at inward office of exchange` | Eine Auslandssendung hat die Ukraine erreicht. |
| `Held at inward office of exchange` | Nach Ankunft für den Zoll zurückgehalten. |
| `Item presented to customs` | Dem Zoll zur Prüfung vorgelegt. |
| `Released from customs for further delivery` | Vom Zoll freigegeben. |
| `Departure from inward office of exchange` | Durch den Zoll und unterwegs in deine Region. |
| `Item out for physical delivery` | In Zustellung. |
| `Arrived to the Branch` | In deiner Filiale eingetroffen und abholbereit. |
| `Final delivery` | Zugestellt: an dich übergeben. |
| `Returned to Sender (Due to Recipient's Refusal)` | Vom Empfänger abgelehnt und auf dem Rückweg. |
| `Return: Delivered to Sender` | Zurück beim Absender: retourniert, nicht an dich zugestellt. |

Schritte auf dem Rückweg beginnen mit `Return:`. Sobald eine Zeile die Rücksendung meldet, zeigt Peek eine spätere Zustellung als „Zurück an Absender“ statt „Zugestellt“.

## Filialabholung und Zustellung bei Ukrposhta

Der Verlauf zeigt, ob das Paket zu dir gebracht wird (`Item out for physical delivery`) oder in der Filiale wartet (`Arrived to the Branch`, in Peek „Abholbereit“). Die Ukrposhta-App zeigt Filialen auf einer Karte mit Postleitzahl, Adresse und Öffnungszeiten. Erkundige dich bei deiner Filiale nach Lagerfrist und benötigtem Ausweis.

## Ukrposhta-Paket verspätet, verloren oder beschädigt

Tut sich tagelang nichts, siehe [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating); bei Zollstopp siehe [Paket hängt beim Zoll](guide:customs).

**Verloren oder verspätet im Auslandsverkehr.** Nach den Regeln des Weltpostvereins (UPU) muss eine nationale Post innerhalb von sechs Monaten ab dem Tag nach Einlieferung einen Nachforschungsantrag für Pakete, Einschreiben oder Wertsendungen sowohl vom Absender als auch vom Empfänger annehmen. Solche Anträge sind regulär kostenlos, doch die Ukraine behält sich vor, eine Gebühr zu erheben. Entschädigung erhält der Absender, oder du mit dessen schriftlicher Abtretung. Bei Online-Bestellungen wende dich zuerst an den Verkäufer.

**Beschädigt angekommen.** Weise vor der Annahme auf den Schaden hin. Nach denselben UPU-Regeln haften die Postgesellschaften für Schäden, die vor oder bei Übergabe festgestellt werden; die ausliefernde Post erstellt ein Protokoll, das du gegenzeichnest und in Kopie erhältst.

## Ukrposhta Kundenservice

Bei App-Problemen nennt der Google-Play-Eintrag von Ukrposhta mobile-app@ukrposhta.ua. Bei verlorenen oder verspäteten internationalen Sendungen reiche wie oben beschrieben eine Nachforschung ein: Nach UPU-Regeln muss Ukrposhta sie sowohl von dir als auch vom Absender entgegennehmen.

## Ukrposhta mit Peek verfolgen

[Peek](/) verfolgt über 4500 Paketdienste. Bei 13 Ziffern mit gültiger Ukrposhta-Prüfziffer fragt Peek bei Ukrposhta nach — sowie bei [FAN Courier](carrier:fan-courier) oder iMile, falls das Muster auch dort passt — und zeigt Ukrposhta mit „hat dieses Paket“, wenn nur Ukrposhta es kennt; kennt es ein weiterer Paketdienst, wählst du aus. Eine 13-stellige Nummer mit falscher Prüfziffer wird Ukrposhta nicht angeboten: Wähle Ukrposhta über „Ändern“. Eine gültige S10-Nummer mit Beginn `C`, `E`, `L` oder `R` und Endung `UA` wird sofort als Ukrposhta erkannt; bei anderem gültigen Anfangsbuchstaben fragt Peek Ukrposhta und [Chronopost](carrier:chronopost) und prüft nach dem Hinzufügen weiter, falls noch keiner antwortet. Ein track.ukrposhta.ua-Link mit Nummer (`barcode=`) wählt immer Ukrposhta.

Eine ausländische Postnummer wird Ukrposhta nicht automatisch zugewiesen. Ist das Paket für die Ukraine bestimmt, kann Peek es zu Ukrposhta weiterverfolgen, oder du wählst „Ukrposhta“ über „Ändern“.

Peek liest den Dienst hinter der Ukrposhta-App; schlägt dieser fehl oder kennt eine Auslandssendung nicht, öffnet Peek die Tracking-Seite von Ukrposhta serverseitig im Browser. Jeder Schritt erscheint mit Ort und Land auf der Karte, samt Zollschritten und Zustellzeit. Ukrposhta liefert Zeiten ohne Zeitzone: Scans in der Ukraine werden in Kiewer Zeit gelesen und in deine Zeitzone umgerechnet (am Ende des Verlaufs steht „Uhrzeiten in deiner Zeitzone:“ mit ihrem Namen); die Zeitzone eines Scans im Ausland oder ohne Land (etwa eines Flugabschnitts) leitet Peek aus seinem Ort oder den Schritten davor und danach ab, sodass diese Uhrzeit abweichen kann. Peek zeigt keinen voraussichtlichen Liefertag und lässt geplantes Datum und Postleitzahlen weg; einziger genannter Abholort ist die Filiale bei `Arrived to the Branch`. Status behalten Ukrposhtas englischen Wortlaut, `Final delivery` eingeschlossen.

Solange das Paket geöffnet ist — oder am Zustelltag mit aktivierten Benachrichtigungen — prüft Peek bis zu alle 10 Minuten, und sobald es in Zustellung ist, ab 6:00 Uhr nach der Uhr des letzten Scans (Kiewer Zeit in der Ukraine) bis zu alle 2 Minuten (oder alle 5 Minuten, wenn der letzte Abruf den Browser brauchte), solange Ukrposhtas Dienst oder Seite antwortet. Sonst sowie nach zwei Tagen ohne neuen Schritt (12 Stunden bei „Etikett erstellt“ oder „Beim Paketdienst eingegangen“) prüft Peek stündlich. Benachrichtigungen stellst du auf „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“. Nach vier Tagen ohne neuen Scan, außer beim Zoll oder bei einem gemeldeten Problem, zeigt Peek in deiner Liste den Tag des letzten Scans („Keine Aktualisierung seit …“) und auf der Paketseite „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei Ukrposhta nach.“, erst dann samt Link „Hol dir Hilfe bei Ukrposhta“ zur Kontaktseite.

## Häufige Fragen zu Ukrposhta

### Wie verfolge ich ein Ukrposhta-Paket im Vereinigten Königreich?

Wenn du eine Ukrposhta-Sendung im Vereinigten Königreich hinzufügst, fragt Peek unter derselben Nummer auch [Royal Mail](carrier:royal-mail) ab — bzw. Parcelforce bei `E`-Nummern (EMS). Sobald diese Post einen mindestens ebenso aktuellen Scan zeigt, verfolgt Peek dort unter Erhalt der Ukrposhta-Schritte weiter.

### Warum funktioniert meine Ukrposhta-Sendungsnummer nicht?

Prüfe sie Zeichen für Zeichen mit der Angabe des Absenders. Eine 13-stellige Nummer endet mit einer Prüfziffer, und bei falscher Prüfziffer bietet Peek Ukrposhta nicht an. Stimmt die Nummer, kennt Ukrposhta sie vielleicht noch nicht: Unter Ukrposhta abgelegt zeigt sie „Warten auf Ukrposhta“, während Peek weiter fragt. Kennt Ukrposhta die Nummer auch zwei Tage später nicht und hat sonst niemand geantwortet, meldet Peek „Auch nach zwei Tagen kennt der Paketdienst diese Nummer nicht. Prüfe sie, wähle einen anderen Paketdienst oder lösche das Paket.“ und prüft ab dann täglich, nach einer Woche wöchentlich. Melde dich nach mehreren ruhigen Tagen beim Absender.

### Wie verfolge ich ein aus dem Ausland in die Ukraine gesendetes Paket?

Füge die Nummer des Absenders ein. Peek verfolgt das Paket bei der absendenden Post; sobald diese die Ukraine als Ziel nennt (oder du es in der Ukraine anlegst), fragt Peek auch Ukrposhta ab und wechselt dorthin, sobald Ukrposhta einen mindestens ebenso neuen Scan hat, unter Beibehaltung der bisherigen Schritte. Willst du von Beginn an bei Ukrposhta suchen, wähle „Ukrposhta“ über „Ändern“. Ein DHL-Paket aus Deutschland mit Anfangsbuchstabe `C` wird stattdessen bei Meest weiterverfolgt.

:::sources
- [Universal Parcel Scraper: Ukrposhta](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ukrposhta) – Nummernformate und Prüfziffern, track.ukrposhta.ua-Links, App-Dienst und Browser-Fallback, englische Status, Rücksendungen, Kiewer Zeit und Auslandsscans, ausgelassene Felder
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – abgefragte Paketdienste, Übergaben an Ukrposhta (und Meest bei DHL-`C`-Nummern) sowie an Royal Mail oder Parcelforce, Nova-Poshta- und Meest-Formate
- [Weltpostverein (UPU): Convention Manual](https://www.upu.int/UPU/media/upu/files/aboutUpu/acts/06-manualsInThreeVolumes/actInThreeVolumesConventionManual202609En.pdf) – Nachforschungen binnen sechs Monaten, Gebührenvorbehalt der Ukraine, Entschädigung an den Absender (oder schriftlicher Verzicht), Schadensprotokoll bei Zustellung
- [Google Play: Ukrposhta](https://play.google.com/store/apps/details?id=ua.ukrposhta.android.app) – App-Tracking, Filialsuche mit Postleitzahlen, Adressen und Öffnungszeiten, App-Support-E-Mail
:::
