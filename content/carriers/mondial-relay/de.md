---
title: Mondial Relay Sendungsverfolgung: Status, Vinted & Abholung
description: Mondial Relay Sendungsverfolgung für Deutschland, Österreich & die Schweiz: 8-stellige Nummer mit PLZ, französische Status übersetzt, Vinted-Versand & InPost.
slug: mondial-relay-sendungsverfolgung
published: 2026-11-17
updated: 2026-11-27
---

Mondial Relay ist der französische Paketdienst der **InPost Group** für *Points Relais* (Paketshops), *Lockers* und Haustürzustellungen — im deutschsprachigen Raum begegnet er dir vor allem bei Bestellungen aus Frankreich, Belgien oder den Niederlanden, besonders auf Vinted. Gib deine 8-stellige Sendungsnummer zusammen mit der Postleitzahl der Lieferadresse oben im Suchfeld oder auf `mondialrelay.fr` ein (in Peek funktioniert der 26-stellige Etikett-Barcode auch ohne Postleitzahl).

Im deutschsprachigen Raum begegnet dir Mondial Relay vor allem bei Bestellungen aus Frankreich, Belgien oder den Niederlanden, besonders auf [Vinted](guide:vinted-orders). Auf Vinted ist Deutschland mit Frankreich, Italien, den Niederlanden, Belgien und Österreich vernetzt, Österreich wiederum mit Deutschland, Italien und Frankreich: Gibt eine französische oder belgische Verkäuferin dein Paket in einem Point Relais oder Locker ab, kann es mit Mondial Relay (oder [Vinted Go](carrier:vinted-go)) unterwegs sein. Als Versandanbieter nennt Vinted in Deutschland [Hermes](carrier:hermes-de), [DPD](carrier:dpd-de), GLS, [DHL Paket](carrier:dhl) und DHL Express, in Österreich GLS, Hermes und DPD.

## Mondial Relay Sendungsnummer: Formate und wo du sie findest

Mondial Relay nennt den Code *numéro d'expédition*: eine 8-stellige Ziffernfolge in der Versand-E-Mail, per SMS oder im Vinted-Chat unter „Sendung verfolgen“. Findest du keine Nummer, frag den Absender oder lies [wo du deine Sendungsnummer findest](guide:find-tracking-number). Auf Etiketten und in Benelux-Portalen tauchen auch längere Ziffernfolgen auf:

| Format (erfundenes Beispiel) | Was dahintersteckt |
| --- | --- |
| `48291057` | Die klassische 8-stellige Sendungsnummer. Dieselbe Länge nutzen auch Colisweb und [Heppner](carrier:heppner). |
| `0012345678` | 10 Ziffern: zweistelliger Markencode plus Sendungsnummer. Passt auch zu [DHL Express](carrier:dhl-express), Relais Colis und anderen. |
| `001234567802` | 12 Ziffern: Markencode, Sendungsnummer und Paketnummer. Dieselbe Länge haben DPD Frankreich, Colis Privé, [BRT](carrier:brt) u. a. |
| `00123456780101` | 14 Ziffern: Anfang des Etikett-Barcodes mit Markencode, Sendungsnummer, Paketfolge und Paketanzahl. Auch bei [DPD](carrier:dpd-de) und [Chronopost](carrier:chronopost) üblich. |
| `00123456780101700000000000` | 26 Ziffern: der vollständige Barcode des Versandetiketts (allein bei Mondial Relay), mit der Sendungsnummer an Stelle 3 bis 10. |
| `FV48291057` | `FV` gefolgt von 8 Ziffern: Sendungsnummer von InPost Spanien (seit der Übernahme von Mondial Relay 2021 tritt das Netz in Spanien und Portugal als InPost auf). |

Auf manchen französischen Etiketten hängen Sendungsnummer und 5-stellige französische Postleitzahl direkt aneinander (13, 15 oder 17 Ziffern). Und falls dir der Vinted-Chat oder die Shop-Mail zusätzlich eine deutsche oder österreichische Nummer von [Hermes](carrier:hermes-de), [DPD](carrier:dpd-de) oder [DHL Paket](carrier:dhl) anzeigt, gehört diese dem Zusteller für die letzte Meile; siehe [Sendungsnummern den richtigen Paketdiensten zuordnen](guide:tracking-number-formats).

## Französische Mondial-Relay-Status auf Deutsch erklärt

Mondial Relay veröffentlicht seine Sendungsverfolgung auf Französisch (in Belgien und den Niederlanden auch auf Niederländisch), ohne deutsche Übersetzung. Das bedeuten die wichtigsten Meldungen in der typischen Reihenfolge:

| Mondial Relay zeigt | Was das für dich bedeutet |
| --- | --- |
| `Colis en préparation chez l'expéditeur` | Das Versandetikett wurde erstellt, aber Mondial Relay hat das Paket noch nicht erhalten. |
| `Prise en charge` / `Colis pris en charge en Locker` | Der Absender hat das Paket im Point Relais oder an einem Locker abgegeben. |
| `Prise en charge de votre colis sur notre site logistique` / `Colis expédié depuis le site` | Das Paket ist im genannten Logistikzentrum eingetroffen bzw. hat es wieder verlassen. |
| `Colis en route vers le point de livraison` | Unterwegs zum Ziel-Paketshop, zum Locker oder in Richtung Zustellpartner. |
| `Sollicitation Client pour replace colis` | Der gewählte Point Relais kann das Paket nicht annehmen; du wirst gebeten, einen Ersatzshop zu wählen. |
| `Relais de substitution choisi` | Ein Ausweich-Paketshop übernimmt die Sendung – prüfe dessen Adresse. |
| `En cours de livraison` | Heute in Zustellung an deine Hausadresse. |
| `Disponible dans votre Point Relais` / `Disponible dans votre locker` | Abholbereit im Paketshop oder im Locker. |
| `jours restants pour retirer le colis` | Countdown der verbleibenden Tage bis zur Rücksendung an den Absender. |
| `Retiré par le destinataire` / `Colis livré au destinataire` | Vom Empfänger abgeholt oder an der Adresse zugestellt. |
| `Retour à l'expéditeur` | Das Paket geht zurück an den Absender. |

Warte bei Shop- oder Locker-Lieferungen immer auf `Disponible…` (oder die Abholnachricht des lokalen Partners), bevor du losgehst. `Échec de livraison` meldet einen fehlgeschlagenen Hauszustellversuch. Die Zeile `Votre colis a été livré à l'enseigne.` bedeutet, dass das Paket beim Händler angekommen ist: Stand im Verlauf zuvor `Retour à l'expéditeur`, wertet Peek das als „Zurück an Absender“; bei einer Retoure mit eigener Sendungsnummer an den Shop zeigt Peek „Zugestellt“. Bei spanischen `FV`-Sendungen liest Peek die spanischen Meldungen von `inpost.es` (etwa `En preparación`, `Recogido por el mensajero`, `Listo para recoger en Punto Pack`, `Entregado`).

## Abholung, Paketstationen und Maße im InPost- und Vinted-Netz

- **Abholcode und Ausweis:** Sobald dein Paket in der Abholstelle oder [Paketstation](guide:parcel-lockers) eintrifft, schickt der zuständige Paketdienst einen Abholcode (im InPost-Netz ein 6-stelliger PIN-Code oder QR-Code) an deine hinterlegte E-Mail-Adresse und Telefonnummer; in Paketshops wird meist zusätzlich ein amtlicher Lichtbildausweis verlangt.
- **Lagerfrist und Nichtabholung:** Die Aufbewahrungsdauer richtet sich nach dem Zustellnetz im Zielland und steht in deiner Abholbenachrichtigung (bei InPost in Spanien sind es für Inlandssendungen 5 Kalendertage, auf einer portugiesischen InPost-Seite werden 5 bzw. in den FAQ 8 Kalendertage genannt). Holst du eine Vinted-Bestellung nicht rechtzeitig ab, geht sie automatisch an den Verkäufer zurück, die Bestellung wird storniert und Vinted erstattet dir den Kaufpreis. In der Mondial-Relay-App werden zugestellte oder abgeholte Pakete 7 Tage nach der Übergabe automatisch archiviert (und alle 160 Tage gelöscht).
- **Größen- und Gewichtsgrenzen nach Deutschland:** Laut InPost Spanien und Portugal dürfen Pakete nach Deutschland maximal **120 cm** lang und **25 kg** schwer sein (in Lockers höchstens 64 × 38 × 39 cm und 25 kg, in Punto-Pack-Shops bis 30 kg bei höchstens 150 cm Länge + Breite + Höhe).

## Paket steckt fest, ist verloren oder beschädigt

- **Noch kein Tracking sichtbar:** Auf Vinted kann es nach dem Versand bis zu **48 Stunden** dauern, bis die ersten Tracking-Daten erscheinen. Lies auch [warum sich die Sendungsverfolgung nicht aktualisiert](guide:tracking-not-updating).
- **Verspätet oder verloren auf Vinted:** Normalerweise kommen Vinted-Sendungen innerhalb von **5 Werktagen** an, beim internationalen Versand innerhalb von **7 Werktagen**. Bewegt sich der Status länger nicht, bittet Vinted darum, beim integrierten Versand bis zu **25 Werktage** ab dem Versandtag zu warten: Danach (oder sobald der Chat „Die Sendung ist anscheinend verloren gegangen“ meldet) tippst du im Chat auf **„Ich habe ein Problem“**, damit Vinted die Bestellung storniert, dir den Betrag erstattet und den Verkäufer entschädigt. Siehe [verlorenes Paket](guide:lost-parcel).
- **Geliefert gemeldet oder beschädigt:** Sobald eine Vinted-Sendung als „geliefert“ markiert wird, bleiben dir **2 Tage** (**14 Tage** beim Kauf von gewerblichen Pro-Verkäufern), um im Chat auf „Ich habe ein Problem“ zu tippen. Bei Bestellungen in Online-Shops muss der Händler EU-weit innerhalb von **30 Tagen** liefern (sofern nichts anderes vereinbart wurde) und haftet für Transportschäden bis zur Übergabe an dich; nimm sichtbar aufgerissene Pakete im Shop am besten gar nicht erst an. Mehr unter [beschädigtes Paket](guide:damaged-parcel).

## Kontakt zu Mondial Relay und InPost

- **Mondial Relay (Frankreich & Benelux):** Mondial Relay SASU hat ihren Sitz in `1 avenue de l'Horizon, 59650 Villeneuve d'Ascq` (Frankreich, Handelsregister Lille Métropole `385 218 631`) und gehört wie InPost zur luxemburgischen InPost S.A. Den Kundendienst und das Beschwerdeformular erreichst du über `mondialrelay.fr` (in den Niederlanden wechselt Mondial Relay schrittweise zur Marke InPost unter `inpost.nl`, während der laufende Betrieb weiter über `mondialrelay.nl` läuft). Bei Vinted-Käufen läuft der gesamte Support direkt über die jeweilige Bestellung im Vinted-Hilfe-Center.
- **Spanien und Portugal (heute InPost):** Spanien unter `900 900 137` (innerhalb Spaniens kostenlos) oder `+34 938 16 62 34` (aus jedem Land erreichbar, Mo–Fr 8:30–20:00 Uhr, Sa 9:00–13:00 Uhr); Portugal unter `211 245 624` (Mo–Fr 9:00–18:00 Uhr).

> Eine SMS oder E-Mail fordert plötzlich ein paar Euro Nachzahlung für dein Mondial-Relay-Paket? Klicke auf keinen Link. Wie das französische staatliche Portal *Cybermalveillance.gouv.fr* festhält, verlangt Mondial Relay niemals zusätzliche Gebühren per SMS oder E-Mail. Leite gefälschte Mondial-Relay-Mails an **`phishing@inpost-group.com`** weiter; Betrugsnachrichten rund um Vinted meldest du über „Kontaktiere uns“ im Vinted-Hilfe-Center. Mehr Tipps in [Paket-Phishing per SMS erkennen](guide:delivery-scam-texts).

## Mondial Relay mit Peek verfolgen

[Peek](/) erkennt den 26-stelligen Etikett-Barcode sofort und zeigt „Erkannter Paketdienst“. Bei 8-, 10-, 12- oder 14-stelligen Nummern nennt Peek Mondial Relay in Deutschland, Österreich und der Schweiz nicht von selbst: Diese Ziffernlängen teilen sich mehrere Paketdienste, und Peek fragt Mondial Relay bei der automatischen Suche nur für spanische `FV`-Nummern (`FV` plus 8 Ziffern) ab — dort prüft Peek gleichzeitig Mondial Relay (über `inpost.es`) und TNT Italy. Den automatischen Vorschlag für unbekannte 8-stellige Nummern bietet Peek nur in Frankreich, Belgien, Luxemburg, Spanien, Portugal und den Niederlanden an (in der Schweiz schlägt Peek stattdessen [Heppner](carrier:heppner) vor). Tippe deshalb in Deutschland, Österreich oder der Schweiz auf „Ändern“ und wähle Mondial Relay aus, oder füge einen Tracking-Link von `mondialrelay.fr`, `.be`, `.nl` oder `.com` ein (Links von `.es`, `.pt` oder `inpost.es` liest Peek nicht).

Für eine 8-stellige Nummer benötigt Peek außerdem die „Postleitzahl der Lieferadresse“: Gib erst die 8 Ziffern ein und trage dann die Postleitzahl des Zielorts (bzw. des Ziel-Paketshops) ein; portugiesische Postleitzahlen brauchen alle sieben Ziffern (`1234-567`, mit oder ohne Bindestrich), und 13-, 15- oder 17-stellige französische Etikettnummern mit 5-stelliger französischer Postleitzahl am Ende zerlegt Peek nach Auswahl von Mondial Relay selbst. Mit einer falschen Postleitzahl meldet der Dienst kein Paket. Die Postleitzahl wird an Mondial Relay und bei Bedarf an Tracking-Dienste übermittelt, erscheint aber nie in geteilten Links.

Peek zeigt die französischen Originalmeldungen von Mondial Relay unverändert an, verortet jedes Logistikzentrum auf einer Karte und blendet alle Zeiten aus `Europe/Paris` mit dem Hinweis „Uhrzeiten in deiner Zeitzone: …“ ein. Solange das Paket auf dich wartet („Abholbereit“), siehst du Name und Adresse des Point Relais oder Lockers, niemals aber deinen Abholcode oder deinen Namen. Peek prüft bis zu alle 10 Minuten, am Tag der Zustellung ab 6 Uhr Pariser Zeit sogar bis zu alle 2 Minuten (alle 5 Minuten im Browser, alle 15 Minuten über Tracking-Dienste) und wechselt nach zwei Tagen ohne Neuigkeiten — oder nach 12 Stunden auf „Etikett erstellt“ bzw. „Beim Paketdienst eingegangen“ — auf stündliche Abfragen. Mitteilungen lassen sich auf „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“ stellen. Da Mondial Relay alte Nummern wiederverwendet, kann eine sehr alte Nummer später ein fremdes Paket zeigen. Peek verfolgt Mondial-Relay-Sendungen nicht automatisch zu anderen Paketdiensten weiter: Zeigt dir Vinted für Deutschland oder Österreich eine zweite Nummer von [Hermes](carrier:hermes-de), [DPD](carrier:dpd-de), GLS oder [DHL Paket](carrier:dhl), füge sie als eigenes Paket in Peek hinzu.

## Häufige Fragen zur Mondial Relay Sendungsverfolgung

### Wer stellt ein Mondial-Relay-Paket in Deutschland oder Österreich zu?

Laut InPost stellen die Paketdienste der Gruppe auch in Deutschland und Österreich zu. Bei integrierten Vinted-Bestellungen nennt Vinted in Deutschland [Hermes](carrier:hermes-de), [DPD](carrier:dpd-de), GLS, [DHL Paket](carrier:dhl) und DHL Express als Versandpartner, in Österreich GLS, Hermes und DPD; eine im Vinted-Chat angezeigte lokale Sendungsnummer kannst du zusätzlich in Peek eintragen.

### Kann ich ein Mondial-Relay-Paket ohne Postleitzahl verfolgen?

Für den 26-stelligen Etikett-Barcode sowie 10-, 12- und 14-stellige Nummern ist in Peek keine Postleitzahl nötig. Nur die 8-stellige Sendungsnummer (*numéro d'expédition*) erfordert zwingend die „Postleitzahl der Lieferadresse“.

### Wie lange dauert der Versand mit Mondial Relay über Vinted?

Laut Vinted-Hilfe kommen internationale Sendungen meist in **7 Werktagen** an (national rund 5 Werktage), und nach der Abgabe kann es bis zu 48 Stunden bis zum ersten Scan dauern. Kommt nach **25 Werktagen** nichts an, melde den Verlust im Vinted-Chat über „Ich habe ein Problem“.

### Gehört Mondial Relay zu InPost?

Ja. Die polnisch gegründete InPost-Gruppe (InPost S.A.) hat Mondial Relay 2021 übernommen und betreibt rund 95.000 Lockers und Abholpunkte in 9 europäischen Ländern; in Spanien und Portugal tritt das Netz bereits als InPost auf, in den Niederlanden läuft die Umstellung auf `inpost.nl`.

### Verlangt Mondial Relay Nachzahlungen per SMS oder E-Mail?

Nein. Laut *Cybermalveillance.gouv.fr* fordert Mondial Relay niemals zusätzliche Liefer- oder Zollgebühren per SMS oder E-Mail. Öffne keine verdächtigen Links und leite gefälschte Mondial-Relay-Mails an `phishing@inpost-group.com` weiter.

:::sources
- [InPost Group: Regulamin Konta InPost (ab 01.10.2026)](https://terms.inpost-group.com/pl/regulamin) – Unternehmensdaten der Mondial Relay SASU in Villeneuve-d'Ascq, Funktionen der Mondial-Relay-Plattform, manuelle Sendungsverfolgung mit Postleitzahl und automatische Archivierung nach 7 Tagen
- [InPost S.A.: Who we are](https://inpost.eu/who-we-are) – InPost Group mit Sitz der Holding in Luxemburg und europäisches Out-of-Home-Netzwerk
- [InPost Nederland: Mondial Relay Nederland maakt plaats voor InPost](https://inpost.nl/) – Zugehörigkeit zur InPost Group (9 Märkte, 95.000 Punkte, 1,4 Mrd. Pakete 2025) und Markenwechsel in den Niederlanden
- [InPost España: Quiénes somos](https://www.inpost.es/quienes-somos) – Übernahme von Mondial Relay durch InPost im Jahr 2021
- [InPost España: Seguimiento del envío](https://www.inpost.es/seguimiento-del-envio) – 8-stellige Sendungsnummer, 5 Kalendertage Lagerfrist in Spanien und Abholung mit Ausweis
- [InPost España: Contacta con nosotros](https://www.inpost.es/contacta-con-nosotros) – spanische Rufnummern (900 900 137 und +34 938 16 62 34), Zeiten sowie Höchstmaße nach Deutschland (120 cm, 25 kg) und für Lockers/Punto Pack
- [InPost Portugal: Quem somos](https://www.inpost.pt/quem-somos) – Übernahme in Portugal 2021
- [InPost Portugal: Como recolher](https://www.inpost.pt/recebe-a-tua-encomenda/como-recolher) – 6-stelliger PIN- oder QR-Code, 24/7-Lockers und 5 bzw. 8 Kalendertage Abholfrist
- [InPost Portugal: Fala connosco](https://www.inpost.pt/fala-connosco) – portugiesische Rufnummer 211 245 624 und Paketmaße im europäischen Netz
- [Vinted Deutschland: Der Versand auf Vinted](https://www.vinted.de/help/234) – Länderanbindung Deutschlands (Frankreich, Italien, Niederlande, Belgien, Österreich) und lokale Versandpartner
- [Vinted Österreich: Der Versand auf Vinted](https://www.vinted.at/help/234) – Länderanbindung Österreichs (Deutschland, Italien, Frankreich) und lokale Versandpartner
- [Vinted France: Comment fonctionne l'envoi ?](https://www.vinted.fr/help/234) – Mondial Relay und Vinted Go als integrierte Versanddienste in Frankreich
- [Vinted Deutschland: Eine Sendung verfolgen](https://www.vinted.de/help/100) – bis zu 48 Stunden bis zum ersten Update, 25 Werktage Frist und 2-Tage-Frist nach Zustellung
- [Vinted Deutschland: Eine Sendung abholen](https://www.vinted.de/help/1601) – Abholcode per SMS/E-Mail, automatische Rücksendung und Erstattung bei Nichtabholung sowie 2 bzw. 14 Tage Reklamationsfrist
- [Vinted Deutschland: Meine Sendung ist nicht angekommen](https://www.vinted.de/help/657) – übliche Laufzeit von 5 bzw. 7 Werktagen international und Ablauf nach 25 Werktagen
- [Vinted Deutschland: Phishing-Nachrichten und Betrug vermeiden und melden](https://www.vinted.de/help/628) – Schutz vor Phishing und Meldung über das Vinted-Hilfe-Center
- [Cybermalveillance.gouv.fr: L'hameçonnage à la livraison de colis](https://www.cybermalveillance.gouv.fr/tous-nos-contenus/actualites/escroqueries-livraison-colis) – Mondial Relay fordert nie Zusatzgebühren per SMS/E-Mail, Meldeadresse phishing@inpost-group.com
- [Your Europe: Versand und Lieferung](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_de.htm) – gesetzliche 30-Tage-Lieferfrist im Online-Handel und Händlerhaftung für Transportschäden
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – von Peek erkannte Formate, gleichzeitige Abfrage bei FV-Nummern, Ländereinschränkung der 8-Ziffern-Vorschläge, Prüfintervalle und kein Handoff zu Dritt-Paketdiensten
:::
