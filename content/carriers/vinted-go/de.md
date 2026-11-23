---
title: Vinted Go Sendungsverfolgung: Status, Fristen & Hilfe
description: Vinted-Go-Paket verfolgen: Wo die Sendungsnummer steht, was die englischen Statuszeilen heißen, wie Versand nach Deutschland läuft und wer bei Problemen hilft.
slug: vinted-go-sendungsverfolgung
published: 2026-11-16
updated: 2026-11-23
---

Vinted Go ist der eigene Paketdienst von Vinted: Er betreibt ein Netz aus Paketautomaten (*Lockers*), Paketshops und Nachbarschaftspunkten in Frankreich, Belgien, den Niederlanden, Spanien, Portugal und Finnland. Eigene Automaten oder Shops in Deutschland, Österreich oder der Schweiz führt Vinted Go nicht — laut dem französischen Vinted-Hilfe-Center sind vorfrankierte Vinted-Go-Versandscheine aber auch für Sendungen zwischen Frankreich und Deutschland nutzbar, und Pakete, die das eigene Netz von Vinted Go verlassen, reisen im Zielland mit einem Partnernetz weiter. Um deine Sendung zu verfolgen, öffne den Bestell-Chat auf Vinted und tippe auf „Sendung verfolgen“, gib die Nummer auf der [Sendungsverfolgungsseite von Vinted Go](https://vintedgo.com/en/tracking) ein — die keine deutsche Sprachversion hat — oder füge sie oben in das Suchfeld ein.

## Wo du deine Vinted-Go-Sendungsnummer findest

Direkt im Chatverlauf zur Bestellung auf Vinted kannst du auf „Sendung verfolgen“ tippen, dem Link zur Verfolgungsseite des Paketdienstes folgen oder die Sendungsnummer herauskopieren. Nach dem Versand durch den Verkäufer kann es bis zu 48 Stunden dauern, bis die Informationen zur Sendungsverfolgung auf Vinted erscheinen.

Vinted Go veröffentlicht keine Beschreibung seiner Nummernformate. Diese Formen kennt die Paketdienst-Liste von Peek (mit frei erfundenen Ziffern):

| Nummer (erfunden) | Was es ist |
| --- | --- |
| `1700000000000001` | Typische Form einer Vinted-Go-Nummer: 16 Ziffern, beginnend mit `17` oder `18`, mit gültiger Prüfziffer am Ende. Auch [DHL eCommerce](carrier:dhl-ecommerce) und [TNT](carrier:tnt) nutzen 16-stellige Nummern. |
| `1700000000000002` | 16 Ziffern, deren letzte Ziffer die Prüfung nicht besteht: hat nicht die Form einer Vinted-Go-Nummer. |
| `VGS48291057364829` | `VGS` gefolgt von 13 oder 14 Ziffern: eine Nummer, auf die ausschließlich die Sendungsverfolgung von Vinted Go antwortet. |
| `ABC0UW0000000000` | 16 Zeichen: 3 Buchstaben, eine Ziffer, `UW`, eine Ziffer und 9 Buchstaben oder Ziffern. Auch auf solche Codes antwortet Vinted Go; bei Sendungen nach Italien können die letzten 13 Zeichen zugleich der Paketcode von Poste Italiane sein. |

Zwei Dinge werden leicht verwechselt:

- **Der Tracking-Link.** Der Button im Chat führt auf eine Adresse wie `vintedgo.com/en/tracking/1700000000000001`. Dieser Link ist das sicherste Zeichen dafür, dass dein Paket mit Vinted Go unterwegs ist.
- **Der Abholcode.** Er dient nicht zur Sendungsverfolgung, sondern kommt erst per E-Mail (und teils per SMS), sobald das Paket abholbereit ist. An Vinted-Go-Punkten beginnt er immer mit einem Großbuchstaben; fügst du versehentlich einen sechsstelligen Zahlen-PIN oben bei Peek ein, meldet die App „Das sieht nach einem Abholcode aus“.

Keine Nummer gefunden? Lies nach, [wie du deine Sendungsnummer findest](guide:find-tracking-number). Wurde ein anderer Dienst gewählt, gibt der Ratgeber zum [Vinted-Versand](guide:vinted-orders) einen Überblick über die integrierten Paketdienste.

## Vinted-Go-Sendungsstatus auf Deutsch erklärt

Die englischsprachige Sendungsverfolgung von Vinted Go liefert ihre Statusmeldungen auf Englisch mit Zeitstempel. Diese Meldungen kennt der Code von Peek aus dem Tracking-Dienst von Vinted Go — in der üblichen Reihenfolge vom Versandschein bis zur Abholung oder Rücksendung:

| Vinted Go schreibt | Was das für dich heißt |
| --- | --- |
| `Shipment created` | Der Verkäufer hat den Versandschein erstellt, das Paket aber noch nicht bei Vinted Go abgegeben. |
| `Parcel was dropped off at the locker` | Der Verkäufer hat das Paket in den Automaten gelegt (oder `Parcel was dropped off at the parcel shop` im Paketshop). |
| `At sorting center in <town>, <country>` | Ankunft in einem Sortierzentrum unterwegs; daneben gibt es `In transit to <town>, <country>` und `The parcel is on its way to the locker (<point>)`. |
| `Parcel redirected to the parcel shop` | Der gewählte Punkt konnte das Paket nicht aufnehmen; es wird an einen anderen Paketshop, Automaten, ein Depot oder ein Partnernetz (etwa `Change: Parcel redirected to Colissimo`) umgeleitet. |
| `Parcel is on the way to <country>` | Das Paket hat das eigene Netz von Vinted Go verlassen und reist mit einem anderen Netz im Zielland weiter (etwa Poste Italiane für Italien). |
| `Ready to collect at the locker` | Es wartet im Automaten auf dich (oder `Ready to collect at the parcel shop`). An Vinted-Go-Punkten läuft die 7-tägige Abholfrist. |
| `Parcel was collected by the recipient` | Du — oder jemand mit deinem Abholcode — hast das Paket abgeholt. |
| `Delivered` | Folgt im Dienst von Vinted Go innerhalb einer Sekunde auf `Parcel was collected by the recipient` (Peek blendet diese doppelte Zeile aus). |
| `Missing from locker` | Das Fach im Automaten war leer: Melde das Problem über den Bestell-Chat auf Vinted. |
| `Return: Parcel will be returned. Reason: pickup time has expired` | Die Abholfrist ist abgelaufen, der Rückweg zum Absender beginnt (gefolgt von `Return: At sorting center in <town>, <country>`, `Return ready to collect` und `Return delivered`). |

Beachte den Unterschied zum Sprachgebrauch in der Vinted-Go-FAQ: Nimmt ein Automat deinen Abholcode nicht an, rät die FAQ zu prüfen, ob die Sendung im Tracking bereits als eingetroffen vermerkt ist — im eigentlichen Verlauf heißt die Abholbereitschaft `Ready to collect at the locker`, während `Delivered` erst nach der Abholung geschrieben wird. Daneben kennt der Dienst Ausnahmemeldungen wie `Parcel lost` (spätere Scans können zeigen, dass das Paket wieder aufgetaucht ist), `Couldn’t be delivered`, `Shipment was cancelled` und `Parcel is oversized`.

## Automaten, Paketshops und Versand nach Deutschland

**Wo Vinted Go zustellt.** Eigene Paketautomaten, Paketshops und Nachbarschaftspunkte betreibt Vinted Go in Frankreich, Belgien, den Niederlanden, Spanien, Portugal und Finnland. Ist der gewählte Vinted-Go-Punkt voll oder vorübergehend geschlossen, bringt Vinted Go das Paket mitunter zu einem anderen Punkt in der Nähe und teilt dir den neuen Abholort mit, sobald es abholbereit ist. Auf Vinted Deutschland ist Deutschland mit Frankreich, Italien, den Niederlanden, Belgien und Österreich verbunden; das französische Vinted-Hilfe-Center führt Deutschland unter den Ländern auf, zwischen denen Pakete mit vorfrankierten Vinted-Go-Scheinen verschickt und empfangen werden können (mit dem Hinweis, dass die Versandart verfügbar ist, wenn ein Vinted-Go-Automat in der Nähe von Käufer und Verkäufer liegt). Wie Sendungen auf deutschem Boden zugestellt werden oder welcher Partner sie übernimmt, schlüsselt Vinted Go öffentlich nicht auf; im integrierten Versand auf Vinted Deutschland stehen allgemein DPD, GLS, [DHL Paket](carrier:dhl), [Hermes](carrier:hermes-de) und [DHL Express](carrier:dhl-express) zur Auswahl.

**Benachrichtigung und Abholung.** Sobald eine Sendung an einer Abholstelle eintrifft, schickt der Paketdienst einen Abholcode an die E-Mail-Adresse und gegebenenfalls Telefonnummer deines Vinted-Kontos, und Vinted aktualisiert den Chat; an eigenen Vinted-Go-Punkten sehen die Geschäftsbedingungen von Vinted Go zusätzlich nach 3 Tagen eine Erinnerung vor. Am Vinted-Go-Automaten scannst oder tippst du den Code ein, damit sich das Fach öffnet; im Paketshop oder Nachbarschaftspunkt zeigst du Code oder Ausweis vor. Hast du die Sendung bereits abgeholt, der Chat auf Vinted steht aber noch nicht auf „geliefert“, kannst du im Chat auf „Als erhalten markieren“ tippen.

**Abholfrist.** An Automaten und Paketshops im eigenen Netz von Vinted Go liegt das Paket **7 Kalendertage**; Vinted Go kann diese Frist weder verlängern noch eine bereits gestartete Rücksendung stoppen. Stellt ein anderer Paketdienst zu, gilt dessen Lagerfrist, die normalerweise in dessen Abholnachricht steht. Holst du das Paket nicht fristgerecht ab, geht es an den Verkäufer zurück, Vinted storniert die Bestellung automatisch und erstattet dir den Kaufbetrag.

**Haustürzustellung und Laufzeit.** In Frankreich bieten bestimmte Punkte eine Haustürlieferung per Uber-Kurier zu einem über einen Vinted-Link gebuchten Zeitfenster an (laut Vinted-Go-Bedingungen ein Zustellversuch mit gebuchtem Zeitfenster und PIN, zwei Versuche ohne Zeitfenster). Innerhalb des eigenen Netzes peilen die Geschäftsbedingungen von Vinted Go **3 bis 6 Werktage** ab Abgabe an; das deutsche Vinted-Hilfe-Center nennt allgemein 5 Werktage für Inlands- und rund 7 Werktage für Auslandssendungen.

## Sendungsverfolgung hängt, Paket verloren oder beschädigt

Dein Ansprechpartner bei allen Bestellproblemen ist immer Vinted selbst, nicht Vinted Go: Der Vinted-Support wickelt Rückerstattungen ab und leitet bei Bedarf Nachforschungen beim Paketdienst ein.

**Mehrere Tage kein neuer Scan.** Vinted Go weist darauf hin, dass es unterwegs völlig normal ist, wenn sich die Zeitleiste einige Tage lang nicht verändert. Mehr Hintergründe liest du unter [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating).

**Sendung kommt nicht an.** Sowohl Vinted als auch Vinted Go bitten darum, bis zu **25 Werktage** ab dem Tag abzuwarten, an dem die Bestellung als „Versendet“ markiert wurde (das genaue Datum steht direkt im Bestell-Chat). Ist das Paket danach noch immer nicht da, tippe im Chat auf „Ich habe ein Problem“.

**Beschädigtes Paket, falscher Artikel oder leeres Fach.** Sobald die Bestellung als „geliefert“ angezeigt wird, hast du **2 Tage** Zeit, im Chat auf „Ich habe ein Problem“ zu tippen (bei Bestellungen von gewerblichen Pro-Verkäufern beträgt die Reklamationsfrist **14 Tage**). Liegt ein beschädigtes Paket in einem Vinted-Go-Automaten, nimm es heraus und fotografiere es sofort: Nach dem Schließen lässt sich das Fach nicht wieder öffnen.

**Als zugestellt markiert, aber nicht erhalten.** Melde das ebenfalls innerhalb von **2 Tagen** nach der Zustellmeldung über „Ich habe ein Problem“ im Vinted-Chat. Siehe auch [Paket laut Tracking zugestellt, aber nicht erhalten](guide:delivered-not-received).

## Vinted Go Kontakt und Kundenservice

Wie am 16. November 2026 geprüft, stellt Vinted Go Käufern weder eine Kundenservice-E-Mail noch ein Kontaktformular bereit; telefonische Hilfe gibt es ausschließlich für Störungen direkt an einem Vinted-Go-Automaten im Ausland:

- **Probleme mit der Bestellung** (Verzögerung, Verlust, Schaden, falscher Artikel oder Rückerstattung): Öffne die Bestellung in deinem Vinted-Konto und wende dich über den Chatverlauf an den Vinted-Support.
- **Störung an einem Vinted-Go-Automaten** in Frankreich, Belgien, den Niederlanden oder Spanien: Tippe auf dem Startbildschirm des Automaten auf das „?“-Symbol, um Telefonnummer und Sprechzeiten von Vinted Go einzusehen. Für die Niederlande und Belgien nennt die Vinted-Go-FAQ zusätzlich die Nummer `+31 20 793 2587` (auf Englisch, für Belgien auch auf Französisch).

> Vinted warnt vor Phishing über Vinted-Nachrichten, SMS, E-Mails und Anhänge mit QR-Codes: Du musst Vinted niemals verlassen, um Zahlungen zu bestätigen oder Sendungen anzunehmen. Verdächtige E-Mails kannst du an `phishing@vinted.com` weiterleiten.

## Vinted-Go-Paket mit Peek verfolgen

[Peek](/) liest die Sendungsverfolgung von Vinted Go direkt aus — ohne Konto und ohne Postleitzahl: jeden Scan mit seiner genauen Uhrzeit im englischen Wortlaut von Vinted Go (wie `Ready to collect at the locker`), den Ort jedes durchlaufenen Sortierzentrums auf der Karte und eine deutsche Statusüberschrift („Abholbereit“, „Zugestellt“, „Zurück an Absender“). Peek nennt den Automaten oder Paketshop, in dem das Paket auf dich wartet oder abgeholt wurde, lässt aber Nachbarschaftspunkte aus Rücksicht auf Privatadressen unbenannt; genaue Anschrift, Öffnungszeiten und Abholfrist bleiben auf der Seite von Vinted Go, die auch kein voraussichtliches Lieferdatum liefert. In der Zeitleiste siehst du die Uhrzeiten in der Zeitzone deines Geräts; auf peektracker.com und in den Apps für iPhone und Android steht sie in der letzten Zeile unter dem Verlauf („Uhrzeiten in deiner Zeitzone: …“).

Bei einer Nummer mit `VGS` — oder wenn du einen `vintedgo.com`-Tracking-Link einfügst — erkennt Peek Vinted Go sofort („Erkannter Paketdienst“). Gibst du eine 16-stellige Nummer mit `17` oder `18` allein ein, fragt Peek bei Vinted Go und [DHL eCommerce](carrier:dhl-ecommerce) (bei manchen Nummern auch bei weiteren Paketdiensten) nach und ordnet das Paket Vinted Go zu, sobald Vinted Go antwortet („Vinted Go hat dieses Paket.“); kennt keiner von ihnen die Nummer, fragt Peek zuletzt bei [TNT](carrier:tnt) nach. Bei einem `UW`-Code fragt Peek direkt Vinted Go ab. Andernfalls tippe auf „Ändern“ und wähle Vinted Go aus.

Übergibt Vinted Go das Paket unter einer neuen Sendungsnummer an ein anderes Netz, folgt Peek ihm dort weiter, sofern Peek diesen Paketdienst direkt unterstützt; wenn die letzten 13 Zeichen eines 16-stelligen `UW`-Codes ein Paketcode von Poste Italiane sind, fragt Peek zusätzlich Poste Italiane ab und wechselt auf „Zustellung durch Poste Italiane“, sobald dort der neueste Scan vorliegt. Solange das Paket bei Vinted Go läuft, schaut Peek bis zu alle 10 Minuten nach, oder stündlich nach zwei Tagen ohne neuen Scan (schon nach 12 Stunden, solange es auf „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ steht). Auf Wunsch schickt es dir Mitteilungen: „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“. Vinted oder Vinted Go kontaktieren oder ein Problem melden kann Peek nicht für dich.

## Häufige Fragen zur Vinted Go Sendungsverfolgung

### Ist Vinted Go seriös – und warum läuft mein Vinted-Paket darüber?

Ja. Vinted Go ist der hauseigene Versanddienst von Vinted, betrieben von der Vinted Go, UAB mit Sitz in Vilnius (Litauen). Wenn du auf Vinted etwa bei einem Verkäufer in Frankreich kaufst, kann dieser das Paket an einem französischen Vinted-Go-Automaten oder Paketshop aufgeben; verlässt eine Sendung das eigene Netz von Vinted Go, etwa nach Italien, stellt ein Partnernetz sie im Zielland zu.

### Wie lange dauert der Versand mit Vinted Go?

Innerhalb des eigenen Netzes nennen die Geschäftsbedingungen von Vinted Go ein Ziel von 3 bis 6 Werktagen ab Abgabe (ohne Wochenenden und Feiertage); Vinted Deutschland gibt für internationalen Versand allgemein rund 7 Werktage an. Bevor eine Sendung als verloren gemeldet werden kann, bittet Vinted um bis zu 25 Werktage Geduld.

### Gibt es eine deutsche Telefonnummer für Vinted Go?

Nein. Telefonischen Support bietet Vinted Go nur direkt an seinen eigenen Paketautomaten im Ausland an (über die „?“-Taste am Bildschirm, in Belgien und den Niederlanden unter `+31 20 793 2587`). Für alle Fragen rund um deine Bestellung nach Deutschland ist ausschließlich der Vinted-Support im Bestell-Chat zuständig.

### Warum funktioniert meine Vinted-Go-Sendungsnummer nicht?

Nach dem Versand kann es bis zu 48 Stunden dauern, bis die ersten Tracking-Daten sichtbar sind, und bei einem Tippfehler meldet die Seite `We can’t find a parcel with this information`. Außerdem stellt Vinted Go sechs Monate nach Erstellung des Versandscheins keine Sendungsverfolgung mehr zu der Nummer bereit.

:::sources
- [Vinted Go: Track your parcel](https://vintedgo.com/en/tracking) – englische Tracking-Seite ohne deutsche Version und Fehlermeldung bei unbekannter Nummer
- [Vinted Go: Frequently Asked Questions](https://vintedgo.com/en/faq?country=fr&region=europe) – Abholcode mit Großbuchstabe, 7 Tage Abholfrist, Umleitung an nahen Punkt, Sendung schon als zugestellt markiert, Pausen im Verlauf und Hilfe am Automaten
- [Vinted Go: Frequently Asked Questions (Netherlands)](https://vintedgo.com/en/faq?country=nl&region=europe) – Automaten-Hotline `+31 20 793 2587` für die Niederlande auf Englisch
- [Vinted Go: Frequently Asked Questions (Belgium)](https://vintedgo.com/en/faq?country=be&region=europe) – Automaten-Hotline `+31 20 793 2587` für Belgien auf Englisch und Französisch
- [Vinted Go: Terms and Conditions for Delivery Services](https://vintedgo.com/en/terms-and-conditions?country=fr&region=europe) – Vinted Go, UAB (Vilnius), Punkttypen, 3–6 Werktage, Erinnerung nach 3 Tagen, Zustellversuche an der Haustür und Foto bei Schaden am Automaten
- [Vinted Go: Shipping service, trusted by millions of Vinted users](https://vintedgo.com/en?country=fr&region=europe) – sechs Länder des eigenen Vinted-Go-Netzes und Start 2022
- [Vinted Deutschland: Eine Sendung verfolgen](https://www.vinted.de/help/100) – Button „Sendung verfolgen“ im Chat und bis zu 48 Stunden Wartezeit
- [Vinted Deutschland: Der Versand auf Vinted](https://www.vinted.de/help/234) – mit Deutschland verbundene Länder und integrierte Versandanbieter
- [Vinted Frankreich: Envoi avec Vinted Go](https://www.vinted.fr/help/1110) – Vinted-Go-Versand zwischen Frankreich und Deutschland, Voraussetzung naher Automaten und Uber-Direct-Haustürlieferung in Frankreich
- [Vinted Frankreich: Comment utiliser les consignes Vinted Go ?](https://www.vinted.fr/help/1109) – „?“-Taste am Automaten sowie 2- und 14-Tage-Frist
- [Vinted Niederlande: Hulp met een Vinted Go locker](https://www.vinted.nl/help/1109) – „?“-Taste auf niederländischen Automaten
- [Vinted Spanien: Ayuda en las taquillas de Vinted Go](https://www.vinted.es/help/1109) – telefonische Hilfe über die „?“-Taste in Spanien
- [Vinted Deutschland: Eine Sendung abholen](https://www.vinted.de/help/1601) – Abholcode, „Als erhalten markieren“, 2 Tage (14 Tage bei Pro-Verkäufern) für „Ich habe ein Problem“, Rücksendung und Rückerstattung
- [Vinted Deutschland: Meine Sendung ist nicht angekommen](https://www.vinted.de/help/657) – 5 Werktage national, 7 international und Wartezeit bis zu 25 Werktage ab „Versendet“
- [Vinted Deutschland: Phishing-Nachrichten und Scams erkennen und melden](https://www.vinted.de/help/628) – Phishing per Nachricht, SMS, E-Mail und QR-Code sowie `phishing@vinted.com`
- [Universal Parcel Scraper: Vinted Go](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/vinted-go) – englische Statusmeldungen, Sortierzentren, Abholpunkte ohne Nachbarschaftspunkte, Übergabe an Partnernetze und Sechs-Monats-Grenze
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Nummernerkennung für Vinted Go, Abgleich mit DHL eCommerce und TNT sowie `UW`-Übergabe an Poste Italiane
:::
