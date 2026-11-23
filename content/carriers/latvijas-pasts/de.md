---
title: Latvijas Pasts Sendungsverfolgung: Nummern und Status
description: Pakete von Latvijas Pasts (Lettische Post) verfolgen: Nummernformat, Statusmeldungen, Paketstationen, verpasste Zustellungen und Kontakt.
slug: latvijas-pasts-sendungsverfolgung
published: 2026-11-23
updated: 2026-11-23
---

Latvijas Pasts ist die staatliche Post Lettlands und betreibt das größte Netz an Paketstationen (Pakomāti) im Land. Sendungen verfolgst du auf [mans.pasts.lv/track](https://mans.pasts.lv/track?locale=en-GB) – die Seite öffnet auf Lettisch, lässt sich aber auf Deutsch oder Englisch umstellen – oder indem du die Nummer oben einfügst. Die Nummer steht in der Versandnachricht des Shops oder Absenders oder in der SMS des Kuriers vor der Zustellung.

## Latvijas Pasts Sendungsnummer

Eine bei Latvijas Pasts aufgegebene Sendung hat eine 13-stellige [internationale Postnummer](guide:tracking-number-formats): zwei Buchstaben, neun Ziffern und am Ende `LV`. Die ersten beiden Buchstaben zeigen die Versandart:

| Anfangsbuchstaben (erfundene Beispiele) | Versandart |
| --- | --- |
| `RR482917367LV` | Einschreiben (Standard +): Übergabe gegen Unterschrift. |
| `LA482917367LV` | Nachverfolgbare Sendung (Standard): auf jedem Abschnitt gescannt, Zustellung in den Briefkasten ohne Unterschrift. |
| `CA482917367LV` | Paket: Du erhältst eine Abholbenachrichtigung für die Postfiliale. |
| `EE482917367LV` | Express Mail Service (EMS / Premium). |

Simple- oder Economy-Sendungen werden unterwegs nicht registriert, lassen sich also nicht verfolgen, und bei Beschädigung gibt es keine Entschädigung. Hast du keine Nummer, wurde die Sendung möglicherweise so verschickt; mehr dazu unter [Sendungsnummer finden](guide:find-tracking-number).

Wo du nachsehen kannst: Die SMS des Kuriers nennt Sendungsnummer, voraussichtliche Zustellzeit und einen Link zur Umleitung. Die SMS einer Paketstation enthält Adresse, Abholfrist und Öffnungscode.

Andere Länderkürzel stehen für andere Postgesellschaften: `…LT` ist [Lietuvos paštas](carrier:lietuvos-pastas), `…EE` ist [Omniva](carrier:omniva). Sendungen aus dem Ausland behalten ihre ausländische Nummer; mans.pasts.lv/track nimmt auch diese an, sobald Latvijas Pasts die Sendung oder ihre Daten hat. Ausländische Scans zeigen dann das Land statt einer lettischen Filiale.

## Was die Statusmeldungen von Latvijas Pasts bedeuten

Die Sendungsverfolgung listet jeden Schritt mit Datum, Uhrzeit und lettischer Filiale. In der englischen Ansicht, die Peek abruft, lauten die häufigsten Statusmeldungen in üblicher Reihenfolge:

| Status von Latvijas Pasts | Was das für dich bedeutet |
| --- | --- |
| `Electronic data about shipment received` | [Die Daten des Absenders](guide:tracking-statuses) sind bei Latvijas Pasts eingegangen; das Paket liegt noch nicht vor. |
| `Item information received` | Der Absender hat die Sendung angemeldet; Latvijas Pasts hat sie noch nicht erhalten. |
| `Item received from sender` | Latvijas Pasts hat die Sendung vom Absender übernommen. |
| `Item posted / received from customer` | In einer Filiale eingeliefert; auch der Einlieferungsscan einer ausländischen Post lautet so. |
| `Item sent to the processing facility` | Hat die Einlieferungsfiliale Richtung Sortierzentrum verlassen. |
| `Item arrived at office of exchange` | Im internationalen Auswechslungsamt angekommen, in Riga oder im Ausland. |
| `Item sent to the Customs` | Dem Zoll am Auswechslungsamt bei der Ausfuhr vorgelegt. |
| `Item returned from Customs` | Vom Zoll freigegeben – keine Rücksendung an den Absender. |
| `Item sent abroad` | Hat das Auswechslungsamt Richtung Zielland verlassen. |
| `Item sent abroad (EDI-received)` | Die ausländische Post hat die Sendung nach Lettland abgeschickt; Latvijas Pasts kennt dies über elektronische Daten. |
| `Item accepted for temporary storage` | Sendung aus dem Ausland in vorübergehender Verwahrung unter Zollaufsicht bis zur Verzollung. |
| `Electonic notification (SMS) of customs clearance sent to recipient` | Dir wurde eine SMS zur Verzollung gesendet (die Schreibweise stammt von Latvijas Pasts). Die Sendung wartet noch in der Verwahrung. |
| `Customs clearance completed, item to be delivered to recipient` | Verzollung abgeschlossen; die Sendung geht weiter an das Zustellamt. |
| `Item arrived at processing facility`, `Item arrived at sorting centre` | Im Sortierzentrum eingetroffen. |
| `Item sent to next processing facility` | Auf dem Weg zum nächsten Sortierzentrum. |
| `Item sent to delivery facility` | Auf dem Weg zur zuständigen Zustell- oder Abholstelle. |
| `Item received at delivery office` | In der Zustellstelle eingetroffen; von dort kann sie noch in die Auslieferung gehen. |
| `Item redirected` | Umgeleitet, etwa an eine andere Abholstelle (siehe Umleitung unten). |
| `Item address re-labelled` | Für die neue Adresse neu etikettiert und weitergeleitet. |
| `Item handed over for delivery` | In Zustellung. |
| `Unsuccessful delivery attempt` | Zustellversuch fehlgeschlagen (siehe unten). Erscheint er in derselben Sekunde wie `Item address re-labelled`, dokumentiert er eine Umleitung: Die Sendung geht weiter, und Peek zeigt „Unterwegs“ statt „Zustellversuch“. |
| `Notification - pick-up invitation printed`, `Electronic notification (SMS) - pick-up invitation sent to recipient` | Abholbereit: Die Zustellstelle hat eine Abholbenachrichtigung gedruckt oder per SMS gesendet. |
| `Item arrived at pick-up point` | Scan einer ausländischen Post: Die Sendung wartet im Ausland an einer Abholstelle (genannt wird nur das Land). |
| `Item delivered` | Zugestellt – auch wenn die Post im Zielland eine Sendung aus Lettland ausliefert. |

`Item returned from Customs` sorgt oft für Verunsicherung, bedeutet aber lediglich, dass die Sendung vom Zoll zurück im Postlauf ist und weiterbefördert wird. `Item awaiting next planned cycle` heißt, dass sie im Auswechslungsamt auf die nächste Abfahrt wartet. Händlerpakete über ein Transit-Hub nutzen teils eigene Meldungen (`Parcel notify received`, `Parcel data received`, `Parcel has arrived at transit location`, `Parcel sent to destination country`, `Parcel has arrived in last mile`, `Parcel has gone thought last mile customs`). Zu Zöllen und Gebühren bei Bestellungen außerhalb der EU siehe [Pakete beim Zoll](guide:customs).

Meldet die Suche `No information received on the tracking of the postal item. Please, try again!`, prüfe die Nummer oder warte ab: Die Sendung wurde womöglich noch nicht gescannt, und Sendungen aus dem Ausland erscheinen erst, wenn die dortige Post Daten übermittelt hat. `Postal item number entered incorrectly. Please check it and enter it again.` weist auf einen Tippfehler hin. Bleibt eine korrekte Nummer tagelang unbekannt, frag beim Absender nach, ob überhaupt mit Sendungsverfolgung verschickt wurde: Simple- und Economy-Sendungen tauchen nie auf.

## Paketstationen, Kurier und verpasste Zustellungen

**Paketstationen (Pakomāti).** Sobald dein Paket im Fach liegt, erhältst du eine SMS mit Adresse, Abholfrist und sechsstelligem Code (und eine E-Mail, falls der Absender deine Adresse angegeben hat). Die Lagerfrist beträgt **7 Kalendertage**; liegt das Paket am dritten Tag noch dort, wird der Code erneut gesendet, und vor Ablauf folgt eine Erinnerung. Nach sieben Tagen gilt das Paket als unzustellbar und geht an den Absender zurück – es sei denn, es gilt eine längere Gesamtlagerfrist; dann wird es an die nächstgelegene Kundenstelle weitergeleitet und dort **30 Kalendertage** aufbewahrt. Kam kein Code an, bitte den Absender, Latvijas Pasts zu kontaktieren, denn die Telefonnummer wird nur auf Wunsch des Absenders korrigiert. Maximalmaße sind 30 kg und 38 × 38 × 58 cm (in Außenstationen bis 60 cm Länge).

**Unisend-Paketstationen.** Ein Paket aus Lettland an eine Unisend-Station in Litauen oder Estland liegt ebenfalls 7 Kalendertage bereit; der sechsstellige Code geht per SMS und E-Mail an lettische, litauische oder estnische Rufnummern und wird nach 48 Stunden erneut gesendet. Nicht abgeholte Pakete gehen direkt an den Absender zurück.

**Kurierzustellung an die Adresse.** Latvijas Pasts gibt an, bis zu drei Zustellversuche zu unternehmen. Wenn du nicht da bist und nicht umgeleitet hast, hinterlässt der Kurier eine Benachrichtigung und kommt erneut; scheitert auch das, wird das Paket eingelagert und kann in der Hauptpost abgeholt oder über den Kundenservice neu terminiert werden (die Lagerdauer legt der Absender fest). Bei einer verpassten Express-Mail-Sendung (EMS) liegt eine Benachrichtigung „You have received a shipment“ im Briefkasten; ruf die dort genannte Nummer mit der Sendungsnummer bereit an.

**Postfiliale oder Customer Centre.** Du erhältst eine SMS oder schriftliche Benachrichtigung mit der genauen Filiale. Bring einen gültigen Ausweis mit; ein Führerschein reicht für Einschreiben oder versicherte Sendungen bis 150 €. Kennst du die Sendungsnummer, geht die Abholung auch ohne Benachrichtigungsschein. Alternativ kannst du über die Nummer auf dem Schein eine Hauszustellung bestellen und die Gebühr bei Übergabe zahlen. Eine andere Person kann mit deiner Vollmacht und eigenem Ausweis abholen. Bei Einschreiben und versicherten Sendungen folgt nach 5 Tagen eine zweite Benachrichtigung, nach 30 Tagen ab der ersten geht die Sendung zurück. Steht bei einer ausländischen Onlinebestellung nur ein Teil deines Namens auf dem Etikett, bring Ausweis und Bestellnachweis mit.

**Umleitung.** Wenn der Absender deine Telefonnummer angegeben hat oder du für SMS-Benachrichtigungen angemeldet bist, enthält die SMS einen Link zur Umleitung: kostenlos (0,00 € inkl. MwSt.) an eine Paketstation, für 1,89 € inkl. MwSt. an eine Kundenstelle, für 4,99 € inkl. MwSt. per Kurier an eine andere Adresse oder kostenlos über „Postman at home“, sofern an deiner Adresse verfügbar. Nicht umleitbar sind Sendungen, die bereits in einer Paketstation liegen, unter Zollaufsicht stehen, Einschreiben oder versicherte Sendungen mit Zustellnachweis an eine Station, Nachnahmesendungen über 38 × 38 × 58 cm an eine Station sowie Sendungen ohne Telefonnummer. Bei der SMS-Anmeldung kannst du auch eine Lieblings-Paketstation festlegen.

## Paket von Latvijas Pasts verloren, beschädigt oder verspätet

Bei Verlust oder Schaden einer Paketstations-Sendung muss der Absender die Reklamation einreichen (innerhalb von 6 Monaten laut Paketstationsbedingungen); bei Onlinekäufen wendest du dich also an den Shop. Nötig sind Inhalts- und Wertangabe mit Beleg (Rechnung, Quittung oder Zahlungsnachweis) sowie Fotos bei Schäden. Latvijas Pasts prüft Anträge innerhalb eines Monats; der Absender kann die Entschädigung an den Empfänger abtreten.

Bei Einschreiben oder versicherten Kuriersendungen prüfe den Inhalt im Beisein des Kuriers, damit ein Schadensprotokoll unterzeichnet wird. Öffnest du das Paket allein und bemerkst Schäden, mache Fotos und melde dich beim Absender für Ersatz oder Erstattung; laut Latvijas Pasts haftet die Post dann nicht mehr.

Kommt eine Sendung ins Ausland nicht an, kann der Absender eine internationale Nachforschung stellen: vor Ablauf eines Monats kostenpflichtig, nach 30 Tagen ab Einlieferung kostenlos.

Keine neuen Scans oder beim Zoll hängen geblieben? Siehe [Sendungsverfolgung aktualisiert nicht](guide:tracking-not-updating) und [Pakete beim Zoll](guide:customs).

## Latvijas Pasts Kontakt und Kundenservice

Die auf pasts.lv genannten Kontaktwege:

- **Telefon:** +371 27008001 (in den Paketstationsbedingungen auch +371 67008001).
- **E-Mail:** info@pasts.lv.
- **Erreichbarkeit:** Montag bis Freitag 8:00–19:00 Uhr, Samstag 9:00–17:00 Uhr, Sonntag geschlossen.
- **Problem melden:** über das Formular auf [mans.pasts.lv/complaint](https://mans.pasts.lv/complaint), bearbeitet während der Servicezeiten.

> Latvijas Pasts warnt vor gefälschten SMS und E-Mails, die Adressdaten abfragen, Zollgebühren auf nachgeahmten Seiten fordern oder angeblich „nicht abgeholte Pakete“ verkaufen. Echte E-Mails enden stets auf @pasts.lv, die offizielle Website ist pasts.lv, und die Post fragt per SMS oder E-Mail niemals nach Bank- oder Kartendaten oder einer Smart-ID-Bestätigung.

## Latvijas Pasts mit Peek verfolgen

[Peek](/) ordnet eine Postnummer mit Anfangsbuchstaben C, E, L oder R und Endung `LV` bei gültiger Prüfziffer sofort Latvijas Pasts zu. Bei anderen gültigen `LV`-Nummern wie `VV482917367LV` fragt Peek gleichzeitig bei Latvijas Pasts und [Chronopost](carrier:chronopost) an und zeigt „Latvijas Pasts hat dieses Paket.“, sobald ein Treffer vorliegt, oder „Bei … noch nicht gefunden. Peek prüft nach dem Hinzufügen weiter.“. Bei falscher Prüfziffer wird Latvijas Pasts nicht abgefragt, und auch eine manuelle Auswahl hilft nicht (Peek meldet, der Paketdienst „vergibt keine Nummern wie diese“) – prüfe die Nummer beim Absender. Ein Link zu mans.pasts.lv/track mit enthaltener Nummer wählt immer Latvijas Pasts, und die Suche nach „Latvian Post“ findet den Dienst ebenfalls. Kennt Latvijas Pasts die Nummer noch nicht, zeigt das Paket „Warten auf Latvijas Pasts“; bleibt es zwei Tage lang unbekannt, meldet Peek „Auch nach zwei Tagen kennt der Paketdienst diese Nummer nicht. Prüfe sie, wähle einen anderen Paketdienst oder lösche das Paket.“ und prüft ab dann täglich, nach einer Woche wöchentlich.

Eine Sendung aus dem Ausland verfolgt Peek zunächst bei der ausländischen Post. Meldet diese Lettland als Ziel (oder nennt kein Ziel und du bist in Lettland), fragt Peek dieselbe Nummer zusätzlich bei Latvijas Pasts ab und wechselt dorthin, sobald ein neuer Scan eintrifft; der bisherige Verlauf bleibt erhalten. Möchtest du sofort bei Latvijas Pasts starten, wähle beim Hinzufügen über „Ändern“ (oder später über „Paketdienst ändern“) direkt „Latvijas Pasts“.

Die Zeitstempel von Latvijas Pasts enthalten keine Zeitzone: Peek liest sie in der Ortszeit des jeweiligen Scan-Orts (lettische Zeit für lettische Filialen) und übernimmt die englischen Status texte von Latvijas Pasts inklusive `Item delivered`. Peek kann die Abholfiliale anzeigen, liefert aber weder voraussichtlichen Liefertag noch Gewicht.

Peek prüft bis zu alle 10 Minuten, solange das Paket in Peek geöffnet ist oder am Zustelltag mit aktivierten Mitteilungen; an dem Tag, an dem Latvijas Pasts das Paket zur Zustellung übergibt, ab 6:00 Uhr Ortszeit (lettische Zeit für lettische Filialen), alle 2 Minuten, solange der eigene Tracker von Latvijas Pasts antwortet. Sonst prüft Peek stündlich. Nach 12 Stunden im Status „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ sowie nach zwei Tagen ohne Neuigkeiten prüft Peek stündlich, auch wenn das Paket geöffnet ist, und nach sieben Tagen bei „Etikett erstellt“ alle 6 Stunden. Antwortet der Tracker von Latvijas Pasts nicht und Peek muss über Tracking-Dienste abfragen, prüft Peek stündlich, nach einem Tag ohne neuen Scan alle 3 Stunden und am Tag der Zustellung bis zu alle 15 Minuten, solange das Paket geöffnet ist oder Mitteilungen aktiv sind. Übernimmt ein anderer Paketdienst die Zustellung, folgt Peek dessen Takt.

Kommt vier Tage lang kein neuer Scan, während das Paket auf „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“ steht, markiert Peek es mit dem Tag des letzten Scans, etwa „Keine Aktualisierung seit Mo., 12. Okt.“. Dazu siehst du den Hinweis „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei Latvijas Pasts nach.“ mit dem Link „Hol dir Hilfe bei Latvijas Pasts“. Nach mehr als drei Wochen ohne neue Meldung zeigt Peek: „Seit über drei Wochen nichts Neues. Ist das Paket noch nicht angekommen, frag beim Verkäufer nach. Peek fragt weiter bei Latvijas Pasts nach.“

Für Mitteilungen wählst du zwischen „Jeden Scan“, „Wichtige Schritte“ und „Nur die Zustellung“.

## Häufige Fragen zu Latvijas Pasts

### Was ist Latvijas Pasts?

Latvijas Pasts ist die staatliche nationale Post Lettlands und bietet Post-, Logistik- und Finanzdienstleistungen im In- und Ausland über pasts.lv an.

### Ist Unisend dasselbe wie Latvijas Pasts?

Nein. Unisend ist die Paketmarke der litauischen Post Lietuvos paštas. Beide betreiben ein gemeinsames Paketstationsnetz, sodass Kunden von Latvijas Pasts an Unisend-Stationen in Litauen und Estland versenden können.

### Kann ich internationale Sendungen von Latvijas Pasts verfolgen?

Ja, sofern sie mit Sendungsverfolgung verschickt wurden. mans.pasts.lv/track zeigt auch Scans der ausländischen Post mit dem Land statt einer Filiale und bietet über „Pārrobežu izsekošana“ einen Link zur internationalen Nachverfolgung. Im Zielland stellt die dortige Post zu.

### Hat normale Post aus Lettland eine Sendungsnummer?

Nicht immer. Simple- und Economy-Sendungen werden nicht registriert und lassen sich nicht verfolgen; Einschreiben, nachverfolgbare Sendungen, Pakete und EMS schon.

:::sources
- [Latvijas Pasts: Customer support](https://pasts.lv/en/client-support) – Buchstaben nach Versandart, keine Entschädigung bei Simple, Nachforschung, Servicezeiten, Telefon
- [Latvijas Pasts: Contacts](https://pasts.lv/en/about-us/contacts) – Telefonnummer, Servicezeiten
- [Latvijas Pasts: Receive a parcel](https://pasts.lv/en/private-person/receive-parcel) – Empfangsarten, Vollmacht
- [Latvijas Pasts: Receive by courier](https://pasts.lv/en/private-person/receive-parcel/receive-by-courier) – Kurier-SMS, drei Versuche, Lagerung, Hauptpost, Postman at home
- [Latvijas Pasts: Receive item in a parcel locker](https://pasts.lv/en/useful/receiving-items-private/receive-in-parcel-locker) – Paketstations-SMS, 7 Tage, Erinnerung, 30 kg und 38 × 38 × 58 cm, Lieblingsstation
- [Latvijas Pasts: Delivery to address](https://pasts.lv/en/useful/receiving-items-private/delivery-to-address) – zweite Benachrichtigung nach 5 Tagen, Rücksendung nach 30 Tagen, EMS-Schein, unvollständiger Name
- [Latvijas Pasts: Receive at a Customer Centre](https://pasts.lv/en/private-person/receive-parcel/receive-at-customer-centre) – Benachrichtigung, Ausweis, Führerschein bis 150 €, Abholung ohne Schein, Hauszustellung
- [Latvijas Pasts: Shipment redirection](https://pasts.lv/en/services/private-person/receiving/redirection-of-shipments) – Umleitungslink, Optionen, Preise inkl. MwSt., Ausnahmen
- [Latvijas Pasts: Lost or damaged parcel locker shipments](https://pasts.lv/en/services/private-person/shipment-management/lost-or-damaged-parcel-locker-shipments) – Reklamation durch Absender, Belege, 1 Monat Bearbeitungszeit
- [Latvijas Pasts: Damaged shipment](https://pasts.lv/en/useful/faulty-shipment) – Prüfung mit dem Kurier, Ansprüche beim Absender
- [Latvijas Pasts: Pakomātu pakalpojumu noteikumi](https://pasts.lv/media/789/download/Pakomatu_noteikumi.pdf) – sechsstelliger Code, E-Mail, erneuter Code am 3. Tag, Rücksendung nach 7 Tagen bzw. 30 Tage in Kundenstelle, 6 Monate Reklamationsfrist, Unisend-Stationen, info@pasts.lv und +371 67008001
- [Latvijas Pasts: Pasargā sevi no krāpniekiem](https://pasts.lv/pasarga-sevi-no-krapniekiem) – Warnung vor Phishing-SMS und -E-Mails
- [Latvijas Pasts: Mans pasts, track shipment](https://mans.pasts.lv/track?locale=en-GB) – Sendungsverfolgung, Sprachwahl, internationale Suche
- [Latvijas Pasts: Complaint form](https://mans.pasts.lv/complaint) – Beschwerdeformular
- [Latvijas Pasts: About us](https://pasts.lv/en/about-us/par-pastu) – staatliche Post, Leistungen
- [Latvijas Pasts: Joint parcel locker network with UNISEND](https://pasts.lv/en/news/latvijas-pasts-and-unisend-launch-their-joint-parcel-locker-network) – Unisend als Marke von Lietuvos paštas, gemeinsames Netz
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – was Peek liest und anzeigt, Status-Texte und Stufen, Nummernprüfung, Übergabe von ausländischen Postdiensten
:::
