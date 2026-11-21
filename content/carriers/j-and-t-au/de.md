---
title: J&T Express Australia Tracking: JTAU-Nummern und Status
description: Verfolge ein Paket von J&T Express Australia: JTAU-Format mit 12 Ziffern, englische Statusmeldungen, Zustellfoto, Hotline 13 18 18 und Peek.
slug: jt-express-australia-sendungsverfolgung
published: 2026-11-21
updated: 2026-11-21
---

J&T Express Australia (*J&T Express Australia Pty Ltd* mit Sitz in Villawood, New South Wales) ist der australische Zweig der Paketgruppe J&T Express aus Südostasien und Festlandchina. Das Unternehmen stellt in ganz Australien Pakete für E-Commerce-Plattformen, Marken und Händler zu – auch Auslandsbestellungen nach der Einfuhrverzollung. Sendungsnummern beginnen mit `JTAU`: Füge deine Nummer oben ein oder prüfe sie im [offiziellen Tracker von J&T Australia](https://www.jtexpress.au/tracking). Du findest sie in der Versand-E-Mail oder Bestellübersicht des Shops.

## Sendungsnummer von J&T Express Australia: Aufbau

Ein australischer Frachtbrief (*waybill*) von J&T besteht aus `JTAU` und 12 Ziffern, insgesamt 16 Zeichen – das einzige Muster, das Peek für diesen Dienst kennt (erfundene Beispiele):

| Nummer (erfunden) | Bedeutung |
| --- | --- |
| `JTAU482910573649` | `JTAU` und 12 Ziffern: ein Paket von J&T Express Australia, das Peek sofort erkennt. |
| `JTAU48291057364` | Eine Ziffer zu wenig: Antwortet kein Dienst, zeigt `peektracker.com` „Das beginnt wie eine Sendungsnummer von J&T Express Australia, aber ein Zeichen fehlt. Vielleicht ist es ein Tippfehler: Vergleiche die Nummer mit deiner Versandnachricht.“ |
| `JTDE482910573649` | `JTDE` und 12 Ziffern: [J&T Express Deutschland](carrier:j-and-t-de). |
| `JTE482910573649` | `JTE` und 12 Ziffern: [J&T Express Middle East](carrier:j-and-t-middle-east) (Saudi-Arabien, VAE und Kuwait). `JTUK` (`JTUK482910573649`) gehört zu J&T Express UK. |
| `JT4829105736491` | `JT` und 13 Ziffern: J&T-Netze in Südostasien sowie [J&T Express China](carrier:j-and-t-cn). |

Leerzeichen, Bindestriche und Kleinschreibung spielen in Peek keine Rolle; im Katalog ist der Dienst auch als *J&T Express (AU)*, *J&T Express AU*, *J&T Australia* und *JT Express Australia* hinterlegt.

**Wo du sie findest.** J&T erhält Name, Adresse und Kontaktdaten vom Absender oder Marktplatz – prüfe daher Versandmail oder Bestellkonto ([Sendungsnummer finden](guide:find-tracking-number)). Auf `jtexpress.au/tracking` meldet die Seite für unbekannte Nummern `No tracking records yet`.

## Tracking-Status von J&T Express Australia erklärt

J&Ts Tracker nennt zu jedem Scan eine Uhrzeit und einen Satz ohne Orts- oder Depotangabe. Peek fragt die englischen Sätze ab (einige frühe Schritte führt der Tracker auch auf Chinesisch: `包裹已从清关行取件。`, `快件已揽收成功，运输准备中。`, `快件到达转运中心，做发往下一站的运输准备`, `快件从转运中心发往下一站`):

| J&T-Meldung | Was das für dich bedeutet | Peek zeigt |
| --- | --- | --- |
| `The parcel has been picked up from customs clearance.` | Auslandspaket: Die Verzollung ist abgeschlossen, und J&T hat das Paket beim Zollagenten abgeholt (erster J&T-Scan eines Imports, kein Zollstopp). | „Beim Paketdienst eingegangen“ |
| `The parcel has been successfully picked up and is being prepared for transportation.` | J&T hat das Paket übernommen und bereitet den Weitertransport vor. | „Beim Paketdienst eingegangen“ |
| `The parcel arrives at the distribution center and is prepared for transport to the next destination.` | In einem J&T-Verteilzentrum für die nächste Etappe eingetroffen. | „Unterwegs“ |
| `The parcel is dispatched from the transit center to the next destination` | Hat ein Transitzentrum in Richtung nächster Station verlassen. | „Unterwegs“ |
| `Your parcel is out for delivery.` | Auf Zustelltour zu deiner Adresse. | „In Zustellung“ |
| `The building was closed when we arrived. We'll try again.` | Gebäude bei Ankunft geschlossen; J&T unternimmt einen weiteren Versuch. | „Zustellversuch“ |
| `We couldn't deliver - the address details appear to be incorrect. Please update your delivery address.` | Zustellung wegen unvollständiger oder fehlerhafter Adresse gescheitert; J&T bittet um Korrektur. | „Zustellversuch“ |
| `Your parcel has been delivered.` | Zugestellt. | „Zugestellt“ |

Mehr zu Einfuhrsendungen steht unter [Paket hängt beim Zoll](guide:customs). Andere Sätze stuft Peek nach ihrem Wortlaut ein (standardmäßig als „Unterwegs“, außer das Paket ist bereits in Zustellung oder weiter).

Auf J&Ts Tracker enden manche Sätze mit der Servicenummer oder der Bitte anzurufen: Peek entfernt diesen Zusatz ebenso wie Angaben in Klammern (`[…]`, `【…】`), Telefonnummern sowie Fahrer- oder Absendernamen. Nennt der Zustellscan, wer unterschrieben oder das Paket entgegengenommen hat, behält Peek diesen Namen für die Person, die das Paket hinzugefügt hat, und blendet ihn auf geteilten Links aus. Mangels Ortsangaben zeigen weder J&T noch Peek den Bundesstaat oder das Depot.

## Zustellung in Australien und Zustellnachweis

J&T arbeitet landesweit mit Zustellpartnern für Abholung und letzte Meile zusammen, übergibt Sendungen im Code aber keinem Fremddienst mit Zweitnummer: Die `JTAU`-Nummer gilt bis an deine Tür.

**Sendungen internationaler Linien.** Internationale Versender übergeben Pakete in Australien teils gesammelt am Ankunftshafen an J&T – etwa [Yanwen](carrier:yanwen), dessen eigenes Tracking danach J&Ts Sätze übernimmt (dort stuft Peek J&Ts Übernahme als „Unterwegs“ ein, da Yanwen das Paket bereits zuvor führte).

**Gebäude geschlossen oder Adressfehler.** Nach `The building was closed when we arrived. We'll try again.` folgt ein neuer Zustellversuch; bei `We couldn't deliver - the address details appear to be incorrect. Please update your delivery address.` prüfe sofort Wohnungsnummer und Postleitzahl mit dem Shop ([Lieferadresse ändern](guide:change-delivery-address), [Zustellung verpasst](guide:missed-delivery)). Wie viele Versuche J&T macht oder wie lange unzustellbare Pakete lagern, nennt die Website nicht; Paketshops oder Packstationen kommen in den bekannten Statuszeilen nicht vor.

**Zustellfoto (Proof of Delivery).** Laut J&Ts Datenschutzerklärung dokumentiert der Fahrer Zustellungen und Versuche in der Fahrer-App:
- Fotos des Pakets am Ablageort, Datum und Uhrzeit, Gerätestandort, Unterschrift (falls nötig) und Zustellnotizen;
- Anweisung an Fahrer, Personen, Kfz-Kennzeichen und Wohnungsinneres nach Möglichkeit aus dem Bild zu halten;
- Versand eines Links zum Zustellnachweis per SMS oder elektronischer Nachricht an dich (jeder mit dem Link kann ihn öffnen; Fahrername und -kontakt stehen nicht darin) sowie an Absender bzw. Marktplatz;
- wenn kein Foto gemacht werden soll, kannst du über `dataprotection@jtexpress.au` entsprechende Zustellhinweise hinterlegen.

## Paket bewegt sich nicht, fehlt oder gilt als zugestellt

Da der Shop J&T beauftragt hat, wende dich bei Online-Käufen zuerst an den Händler.

**Keine neuen Scans seit vier Tagen.** Steht ein Paket vier Tage lang ohne neuen Eintrag auf „Beim Paketdienst eingegangen“ oder „Unterwegs“, zeigt Peek „Seit vier Tagen keine Aktualisierung“ samt Link „Hol dir Hilfe bei J&T Express Australia“ ([Warum sich das Tracking nicht aktualisiert](guide:tracking-not-updating)).

**Noch kein Verlauf.** Für eine J&T noch unbekannte Nummer zeigt Peek „Warten auf J&T Express Australia“. Bleibt sie zwei Tage lang überall unbekannt, erscheint „Auch nach zwei Tagen kennt der Paketdienst diese Nummer nicht. Prüfe sie, wähle einen anderen Paketdienst oder lösche das Paket.“ und Peek prüft danach täglich bzw. nach einer Woche wöchentlich.

**Zugestellt gemeldet, verloren oder beschädigt.** Öffne den Foto-Link aus J&Ts SMS, prüfe Ablageort, Haushalt und Nachbarn und informiere anschließend Shop und J&T: J&T nutzt die Zustelldaten zur Klärung fehlender, beschädigter oder falsch zugestellter Pakete, veröffentlicht aber kein eigenes Erstattungsverfahren für Empfänger – fordere Rückzahlung oder Ersatz beim Shop oder Marktplatz an ([Zugestellt, aber nicht erhalten](guide:delivered-not-received), [Paket verloren](guide:lost-parcel), [Paket beschädigt](guide:damaged-parcel)).

## Kundenservice und Kontakt von J&T Express Australia

Auf [J&Ts Kontaktseite](https://www.jtexpress.au/contact) stehen:

- **Telefon:** `13 18 18`, Arbeitszeiten `08:00-20:00` (ohne Angabe von Wochentagen).
- **E-Mail Kundenservice:** `service@jtexpress.au` (`business@jtexpress.au` und `partner@jtexpress.au` gelten für Geschäftskunden und Zustellpartner; Datenschutzanfragen gehen laut Datenschutzerklärung an `dataprotection@jtexpress.au`, Sitz `10/2 Christina Road, Villawood NSW 2163`).

Laut Datenschutzerklärung sitzen J&Ts Callcenter- und Kundendienstleister in Malaysia und auf den Philippinen.

Eine SMS mit Zustellfoto-Link von J&T kann echt sein; fordert eine Nachricht jedoch Zahlungen oder sensible Daten an, lies zuerst [Gefälschte Paket-SMS erkennen](guide:delivery-scam-texts).

## J&T Express Australia mit Peek verfolgen

[Peek](/) erkennt J&T Express Australia sofort anhand einer `JTAU`-Nummer („Erkannter Paketdienst“) oder eines Links `www.jtexpress.au/tracking` mit `?q=` und einer `JTAU`-Nummer (enthält `?q=` eine andere Nummer, prüft Peek diese wie direkt eingefügt; bei manueller Wahl für andere Muster meldet Peek, dass J&T „vergibt keine Nummern wie diese“). Bei 13-stelligen `JT`-Nummern fragt Peek J&T Express und Ausweichdienste ab.

Peek fragt J&Ts Tracker in `UTC` (`GMT+0000`) ab und stellt jeden Scanzeitpunkt in deiner Gerätezeitzone dar („Uhrzeiten in deiner Zeitzone: …“), begrenzt auf die neuesten 100 Scans, ohne Karte, Lieferprognose, Gewicht, Stationskontakte oder Zustellfoto (das über den SMS-Link abrufbar ist). Ist J&Ts Tracker nicht erreichbar oder kennt die Nummer noch nicht, zieht Peek Tracking-Dienste heran.

Peek prüft bis zu alle 10 Minuten bei geöffnetem Paket oder am Liefertag mit aktiven Mitteilungen, und am „In Zustellung“-Tag ab `06:00` Uhr Ortszeit bis zu alle 2 Minuten, solange J&Ts Tracker antwortet (über Tracking-Dienste: stündlich, alle 3 Stunden nach einem ruhigen Tag und am Liefertag bis zu alle 15 Minuten bei geöffnetem Paket oder aktiven Mitteilungen); sonst sowie nach 12 Stunden auf „Beim Paketdienst eingegangen“ oder zwei ruhigen Tagen stündlich. Mitteilungen wahlweise: „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“. J&T oder den Shop kontaktieren oder Adressen ändern kann Peek nicht.

## Häufige Fragen zu J&T Express Australia

### Ist J&T Express Australia seriös?

Ja. Im *Australian Business Register* ist *J&T EXPRESS AUSTRALIA PTY LTD* als australische Privatgesellschaft mit aktiver ABN seit dem 4. Juni 2026 eingetragen (`18 698 743 609`, ACN `698 743 609`, Standort `NSW 2163`; eingetragener Sitz laut Datenschutzerklärung `10/2 Christina Road, Villawood NSW 2163`) und gehört zur 2015 in Indonesien gegründeten J&T-Express-Gruppe (`01519.HK`).

### Was bedeutet `picked up from customs clearance` im J&T-Tracking?

Der Zoll ist erledigt: J&T hat das verzollte Importpaket beim Zollagenten abgeholt. Peek zeigt das als „Beim Paketdienst eingegangen“, nicht als Zollrückhalt.

### Warum wird meine J&T-Sendungsnummer nicht gefunden?

Prüfe, ob sie mit `JTAU` plus 12 Ziffern beginnt (nicht `JTUK` oder `JTDE`). Stimmt die Nummer, hat J&T das Paket eventuell noch nicht (`No tracking records yet`, in Peek „Warten auf J&T Express Australia“); bleibt sie nach zwei Tagen unbekannt, frag beim Shop nach.

### Wie erreiche ich J&T Express Australia?

Telefonisch unter `13 18 18` (`08:00-20:00`) oder per E-Mail an `service@jtexpress.au` – und informiere bei Online-Käufen zusätzlich den Händler.

### Macht J&T Express Australia bei der Zustellung ein Foto?

Ja: Laut Datenschutzerklärung fotografieren Fahrer das Paket am Ablageort, und du erhältst den Link zum Zustellnachweis per SMS oder elektronischer Mitteilung.

:::sources
- [J&T Express Australia: Track & Trace](https://www.jtexpress.au/tracking) – offizieller Tracker und Hinweis `No tracking records yet`
- [J&T Express Australia: Contact Us](https://www.jtexpress.au/contact) – Hotline 13 18 18 (08:00-20:00), service@jtexpress.au, business@jtexpress.au, partner@jtexpress.au und ABN 18 698 743 609
- [J&T Express Australia: Delivery Partners](https://www.jtexpress.au/career) – Zustellpartner in Australien für Abholung und letzte Meile
- [J&T Express Australia: About Us](https://www.jtexpress.au/about) – J&T-Express-Gruppe (01519.HK), Gründung 2015 in Indonesien, Südostasien und Festlandchina
- [J&T Express Australia: Privacy Notice](https://www.jtexpress.au/privacy-policy) – Sitz 10/2 Christina Road, Villawood NSW 2163, Zustellfotos und SMS-Link, dataprotection@jtexpress.au, Nachforschungen sowie Support in Malaysia und auf den Philippinen
- [ABN Lookup: J&T Express Australia Pty Ltd](https://abr.business.gov.au/ABN/View?abn=18698743609) – aktive ABN 18 698 743 609 seit 4. Juni 2026, australische Privatgesellschaft, NSW 2163
- [Universal Parcel Scraper: J&T Express Australia](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/j-and-t-au) – JTAU-Format, englische und chinesische Statuszeilen, UTC-Zeiten, bereinigte Zusätze, Unterzeichner und Hilfeseite
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Formate anderer J&T-Netze, Sammelübergabe von Yanwen und Tracking-Dienste als Rückfallweg
:::
