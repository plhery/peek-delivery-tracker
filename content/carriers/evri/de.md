---
title: Evri Sendungsverfolgung: Nummer, Status, Zoll und Zustellung
description: Evri-Paket in Deutschland, Österreich oder der Schweiz verfolgen: 16-stelliger Code, GlobalEco- und UK-Status, Zoll ab Juli 2026, Paketshops und Kontakt.
slug: evri-sendungsverfolgung
published: 2026-11-17
updated: 2026-11-20
---

Evri ist der britische Paketdienst, der bis März 2022 Hermes UK hieß (mit [Hermes in Deutschland](carrier:hermes-de) ist er nicht identisch). Bestellst du bei einem britischen Onlineshop wie ASOS, Boohoo, Gymshark, Lookfantastic, eBay UK oder Vinted, übernimmt Evri häufig den Versand ab Großbritannien und übergibt das Paket im Zielland an einen lokalen Zustellpartner. Internationale Sendungen verfolgst du im [GlobalEco-Portal von Evri International](https://globaleco.app/track/), innerbritische Pakete dagegen auf [evri.com](https://www.evri.com/track-a-parcel) mit der 16-stelligen Sendungsnummer oder der 8-stelligen Nummer einer Benachrichtigungskarte (*calling card*). Du kannst den 16-stelligen Barcode oder deinen `evri.com`-Trackinglink auch direkt oben eingeben.

## Evri-Sendungsnummer: Aufbau und wo du sie findest

Evri spricht abwechselnd von einem 16-stelligen Code (*16-character code*) und einer 16-stelligen Ziffernfolge (*16-digit number*), obwohl die eigenen Beispiele Buchstaben enthalten. Du findest die Nummer in der Versandbestätigung des Shops oder in den E-Mails und SMS von Evri (Absenderkennung „Evri“).

| Format (erfundenes Beispiel) | Bedeutung |
| --- | --- |
| `H00AAC0000000008` | 16-stelliger Evri-Barcode (`H` oder seltener `T`, vier Buchstaben/Ziffern, `C` an 6. Stelle, danach 10 Ziffern inklusive Prüfziffer) – typisch für Evri International (etwa `H04G0C…` bei Post-Office-Aufgabe oder `H0595C…` bei Buchung auf evri.com). |
| `H00AAA0000000006` | 16-stelliger Evri-Barcode mit `A`, `D` oder einem anderen Zeichen an 6. Stelle (z. B. `H00AAB0000000007`), üblich bei Evri UK und von beiden Evri-Diensten genutzt. |
| `C00HHA0000000000` | `C00HHA` gefolgt von 10 Ziffern: innerbritischer Evri-UK-Code, der unter anderem in `evri.com`-Trackinglinks vorkommt. |
| `1000000000000007` | 16 Ziffern mit gültiger GS1-Prüfziffer: kommt bei Evri UK vor, ebenso bei [DHL eCommerce](carrier:dhl-ecommerce), Correos Express, [Canada Post](carrier:canada-post) und TNT. |
| `12340000` | 8-stellige britische Benachrichtigungskarte (*calling card*), funktioniert nur direkt auf evri.com. |

Ein `H` gefolgt von 15 reinen Ziffern (`H000000000000008`) passt zugleich zum Nummernkreis von [Hermes in Deutschland](carrier:hermes-de) – richte dich danach, welchen Paketdienst der Händler nennt. Auf [globaleco.app/track/](https://globaleco.app/track/) zeigt das Feld `Carrier` nach der Übergabe im Zielland den lokalen Zustellpartner und `Carrier Tracking` dessen eigene Sendungsnummer.

**Über eBay UK bestellt?** Verschickt ein britischer Verkäufer über eBays *Global Shipping Programme* (GSP), liefert Evri das Paket zunächst nur innerhalb Großbritanniens mit einer Evri-UK-Nummer an das britische eBay-Versandzentrum; den Weitertransport ins Ausland übernimmt anschließend das eBay-Programm. Nummer unauffindbar? Sieh unter [Sendungsnummer finden](guide:find-tracking-number) nach.

## Was die Evri-Tracking-Status bedeuten

Für Sendungen aus Großbritannien nach Kontinentaleuropa meldet das GlobalEco-Portal von Evri International englische Statusbezeichnungen:

| GlobalEco meldet | Was das für dein Paket heißt |
| --- | --- |
| `Created` | Versanddaten übermittelt; Evri hat das Paket noch nicht erhalten. |
| `Received at Evri`, `Accepted` | Evri hat das Paket in Großbritannien übernommen. |
| `Consolidated`, `Cross Docked`, `Processed at Export Gateway` | Sortierung und Exportabfertigung im britischen Ausfuhrzentrum von Evri. |
| `Departed Origin Country`, `In transit` | Das Paket hat Großbritannien verlassen oder ist unterwegs ins Zielland. |
| `Arrived In Country` | Im Bestimmungsland eingetroffen. |
| `In customs`, `Customs cleared` | Zollprüfung läuft beziehungsweise ist abgeschlossen (siehe [Paket hängt beim Zoll](guide:customs)). |
| `Handed to Partner Carrier`, `Received by carrier`, `At the local depot` | An den lokalen Zustellpartner übergeben und im regionalen Depot sortiert; prüfe auf GlobalEco die Felder `Carrier` und `Carrier Tracking` für die Anschlussnummer. |
| `Scheduled For Delivery` | Kommt bei Paketen mit Paack-Zustellung nach Ankunft im Zielland vor, wenn Liefertag oder Zeitfenster verschoben wurden, während die Sendung im Depot wartet. |
| `Out for delivery` | Heute mit dem Zusteller unterwegs zu dir. |
| `Delivery Attempted` | Zustellversuch ohne Übergabe. |
| `Delivery awaiting collection` | Liegt in einem Paketshop oder einer Postfiliale zur Abholung bereit. |
| `Delivered` | Zugestellt oder abgeholt. |
| `Delayed`, `Delivery Exception`, `Carrier label error` | Verzögerung, Zustellhindernis oder Etikettenfehler: lokales Partner-Tracking prüfen oder den Händler informieren. |

Verfolgst du eine innerbritische Sendung direkt auf `evri.com`, verwendet Evri eigene Meldungen:

| Evri meldet (UK) | Was das bedeutet |
| --- | --- |
| `We're expecting it` | Der Absender hat das Paket noch nicht an Evri übergeben. |
| `on its way` (ParcelShop-Lieferung) | Unterwegs durch die britischen Evri-Depots zum gewählten ParcelShop; sobald es abholbereit ist, folgt eine SMS oder E-Mail. |
| `Evri gateway` | Aus dem Ausland in Großbritannien eingetroffen und während der britischen Zollabfertigung in einem Evri-Gateway-Standort gepuffert, bevor es an ein Hub weitergeht. |
| `We're sorry we haven't been able to deliver today. We'll update your tracking when it's on the way on the next working day` | Tour verzögert ohne heutigen Zustellversuch; das Paket geht am nächsten Werktag wieder raus. |
| `On its way back to the retailer` | Letzter Scan einer Retoure: Evri hat die Rücksendung an den Händler übergeben und schickt keine weiteren Updates. |

Bittet dich `evri.com` um Kontaktaufnahme, fehlen Angaben für die Zustellung – starte dort einen Chat.

## Lieferung nach Deutschland, Österreich und in die Schweiz, Zoll und UK-Abholung

**Laufzeiten und Zustellung in DACH.** In seiner allgemeinen Auslands-FAQ nennt Evri weltweit 3 bis 7 Werktage, auf den einzelnen Länderseiten jedoch konkretere Richtwerte ab Großbritannien: 5 bis 8 Tage nach Deutschland (Standard ParcelShop), 5 bis 8 Tage im Schnitt nach Österreich und 7 bis 10 Tage in die Schweiz, zuzüglich etwaiger Feiertage oder Zollprüfungen. Laut Evris Allgemeinen Geschäftsbedingungen unternimmt Evri bei internationalen Diensten mindestens einen Zustellversuch; ob danach ein weiterer Versuch oder die Hinterlegung im Paketshop folgt, richtet sich nach dem lokalen Partnerdienst im Zielland.

**Zoll und Einfuhrabgaben aus Großbritannien.** Da das Vereinigte Königreich weder zur EU-Zollunion noch zum schweizerischen Zollgebiet gehört, durchläuft jede Warensendung den Zoll:

- **Deutschland und Österreich (EU):** Evri weist auf seinen Länderseiten darauf hin, dass beim internationalen Standardversand Steuern und Abgaben entweder vor Abgang in Großbritannien oder bei Ankunft im Zielland anfallen können; Evri oder die zuständige Behörde schickt dann vor der Auslieferung einen Zahlungslink. Seit dem 1. Juli 2026 gilt in der EU keine Zollbefreiung bis 150 € mehr: Auf Sendungen bis 150 € fällt unabhängig vom Mehrwertsteuerverfahren (mit oder ohne IOSS) ein Zoll von 3 € je Warenart an, und seit dem 1. November 2026 kommt unabhängig vom Wert eine EU-Bearbeitungsgebühr von 2 € je Warenart hinzu. Evri rechnet beides je Zollgruppe (Waren mit denselben ersten 6 Stellen des HS-Codes, etwa 2 T-Shirts = 1 Gruppe, aber 1 Buch + 1 T-Shirt = 2 Gruppen – also nicht pro einzelnem Stück).
- **Schweiz:** Das Bundesamt für Zoll und Grenzsicherheit (BAZG) erhebt 8,1 % Einfuhrsteuer (beziehungsweise 2,6 % reduzierten Satz, etwa auf Bücher und Lebensmittel) einschließlich Versandkosten. Steuer- und Zollbeträge bis 5 CHF werden nicht erhoben (das entspricht einer Bemessungsgrundlage von 62 CHF bei 8,1 % oder 193 CHF bei 2,6 %); für die Zollanmeldung berechnet der Transporteur Verzollungskosten. Einzelheiten findest du im Ratgeber [Zollgebühren und Pakete beim Zoll](guide:customs).

**Zustellung innerhalb Großbritanniens (Calling Cards, ParcelShops und Lockers).** Bei britischen Lieferadressen legt der Kurier das Zeitfenster am Morgen fest (laut Evri-Startseite 1 Stunde, laut Hilfecenter 2 Stunden). Trifft er niemanden an, legt er das Paket wenn möglich an einem sicheren Ort (*safe place*) ab oder wirft es in den Briefkasten; andernfalls hinterlässt er eine 8-stellige *calling card* für bis zu drei Versuche insgesamt (danach geht das Paket laut Safe-Place-Ratgeber ins Depot, laut AGB zurück an den Absender und laut `evri.com/send` bei Online-Buchungen an den nächsten ParcelShop). Mit britischer Postleitzahl lässt sich die Sendung auf `evri.com` an Nachbarn, einen Ablageort, einen ParcelShop oder einen Locker umleiten, sofern der Absender das erlaubt und keine Unterschrift verlangt wird; der Liefertag ist nicht änderbar, die Adresse kann nur der Absender korrigieren, und solange ein Kurier das Paket hat, ist keine Selbstabholung möglich. Britische ParcelShops (Geschäfte und Post-Office-Filialen) lagern Sendungen 10 Tage gegen Ausweis oder zugesandte PIN, rund um die Uhr geöffnete Lockers 3 Tage per Code oder Barcode aus der E-Mail (Taste „help“ am Terminal bei Problemen).

## Evri-Paket hängt fest, verspätet, verloren oder beschädigt

- **Kein Fortschritt im Tracking:** Bleibt `We're expecting it` oder `Created` stehen, hat Evri das Paket noch nicht vom Absender erhalten – wende dich an den Händler. Ändert sich ein anderer UK-Status 24 Stunden lang nicht (oder bei Standardversand aus Absendersicht 5 Tage lang), rät Evri zum Chat. Mehr unter [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating).
- **Nach 7 Tagen noch nicht da:** Informiere den Verkäufer, der den Beförderungsvertrag mit Evri hält. Bei Käufen in der EU muss der Händler binnen 30 Tagen liefern, sofern nichts anderes vereinbart wurde, und trägt das Transportrisiko bis zur Übergabe an dich.
- **Als zugestellt gemeldet, aber unauffindbar:** Frage im Haushalt und bei direkten Nachbarn nach. In Großbritannien stößt ein Chat die Nachforschung im lokalen Depot an; bei Auslandssendungen prüfe zusätzlich das Partner-Tracking und melde den Fall dem Shop. Siehe [Paket laut Tracking zugestellt, aber nicht erhalten](guide:delivered-not-received).
- **Verloren oder beschädigt:** Evri erstattet Empfängern keinen Kaufpreis; [nur der Absender kann Schadensersatz beantragen](guide:damaged-parcel) – Evri empfiehlt dafür eine Frist von 28 Tagen ab Bestellung bei UK-Diensten und 45 Tagen bei internationalen Sendungen.

## Evri-Kontakt und Schutz vor gefälschten Nachrichten

Evri nennt ausschließlich englischsprachige Kanäle für Großbritannien (geprüft am 17. November 2026) und keine eigene Hotline für Deutschland, Österreich oder die Schweiz:

- **Chat rund um die Uhr (24/7)** auf [evri.com/contact-us](https://www.evri.com/contact-us) über den digitalen Assistenten Ezra, mit Rückmeldung binnen höchstens 24 Stunden. Halte die Sendungsnummer, die ParcelShop-Quittung (`P` plus 7 Ziffern) oder die 8-stellige *calling card* bereit. Für internationale Ziele ohne Postleitzahl bittet Evri darum, bei Ezras Postleitzahl-Abfrage `0` einzugeben.
- **Britische Telefonnummer:** `0330 808 5456`, Sprachassistent 24/7, Mitarbeitende Montag bis Freitag 8 bis 16 Uhr sowie Samstag 8 bis 14 Uhr britischer Zeit. Über die Telefontastatur werden die letzten 8 Ziffern der Sendungsnummer, die 7 Ziffern nach dem `P` des ParcelShop-Belegs oder die 8-stellige *calling card* abgefragt. Innerhalb Großbritanniens kosten `03`-Rufnummern laut GOV.UK so viel wie normale Festnetznummern (`01`/`02`); aus DACH gilt der Auslandstarif deines Anbieters nach Großbritannien.
- **Tell us straight:** Konnte der Kundendienst das Problem nicht lösen, öffnet die Schaltfläche „Email us“ auf der Kontaktseite eine E-Mail an `noreply.priority@evri.com` für das Senior-Team (Eingangsbestätigung innerhalb einer Stunde zu üblichen britischen Bürozeiten und tägliches Update bei laufender Prüfung).

> Echte Evri-E-Mails kommen üblicherweise von `@evri.com`, `@hermes-europe.co.uk` oder `@myhermes.co.uk`, ergänzend von `@custhelp.com`, `@globalco.uk`, `@globaleco.app` sowie Subdomains dieser sechs Domains (etwa `@international.evri.com`). Britische Evri-SMS tragen stets den Absendernamen „Evri“ statt einer Handynummer, verlangen niemals Gebühren für einen neuen Zustelltermin oder die Installation einer App und verlinken nur auf `evri.com` oder `evri.link` (was Betrüger laut Evri dennoch fälschen können). Antworte im Zweifel nicht, sondern prüfe die Nummer direkt auf GlobalEco oder `evri.com`; in Großbritannien lassen sich verdächtige SMS kostenlos an `7726` weiterleiten. Mehr dazu unter [Paket-Phishing per SMS erkennen](guide:delivery-scam-texts).

## Ein Evri-Paket mit Peek verfolgen

[Peek](/) fragt bei jedem eingegebenen 16-stelligen `H`- oder `T`-Barcode mit gültiger Prüfziffer sowohl **Evri International** als auch **Evri UK** ab: Steht an 6. Stelle ein `C`, führt Peek Evri International zuerst auf, bei `A` oder `D` Evri UK, legt sich aber erst fest, wenn einer der Dienste das Paket bestätigt (kennen es beide, nimmt Peek bei `C` Evri International, sonst Evri UK). Bei `H` plus 15 Ziffern mit passender Evri-Prüfziffer fragt Peek zusätzlich [Hermes in Deutschland](carrier:hermes-de) an; geht die Evri-Prüfziffer nicht auf, passt nur Hermes in Deutschland. Kennt noch keiner der beiden Evri-Dienste den Code, tippe auf „Ändern“ und wähle unter „Passt zu dieser Nummer“ **Evri International** oder **Evri UK**, oder füge einen `evri.com/track/parcel/…/details`-Link ein, den Peek sofort Evri UK zuordnet. Bei 16 Ziffern prüft Peek Evri UK zusammen mit anderen Diensten, sofern die GS1-Prüfziffer stimmt, sonst nur die übrigen; `C00HHA` mit 10 Ziffern geht direkt an Evri UK. Eine Postleitzahl ist nicht nötig, 8-stellige *calling cards* lassen sich in Peek jedoch nicht verfolgen.

Bei **Evri International** zeigt Peek den GlobalEco-Verlauf (die meisten Ereignisse bleiben auf Englisch, allgemeine Meldungen wie „Unterwegs“, „In Zustellung“ oder „Zugestellt“ erscheinen auf Deutsch), die Scan-Orte, das Paketgewicht und ein in den Kommentaren genanntes Lieferdatum (`Estimated delivery date is: …`) bis zur Zustellung oder Rücksendung; Absendername und Zielort entfallen. Da GlobalEco keine Zeitzone mitliefert, übernimmt Peek die angegebenen Ortszeiten unverändert.

Bei **Evri UK** siehst du Status und Verlauf. Da Evri UK keine Scan-Orte nennt, bleibt die Karte leer. Jede Kurierzustellung und jede Abholung im ParcelShop oder Locker erscheint als „Zugestellt“ ohne Ablageort; ein abholbereites Paket im ParcelShop oder Locker steht auf „Abholbereit“, alle übrigen behalten den englischen Evri-Satz. Name, Adresse, Zustellfoto und Abholcodes werden verworfen. Auf peektracker.com und in den Apps für iPhone und Android endet ein Verlauf mit Zeitzonenangabe auf „Uhrzeiten in deiner Zeitzone: …“, und für Benachrichtigungen wählst du zwischen „Jeden Scan“, „Wichtige Schritte“ und „Nur die Zustellung“.

## Häufige Fragen zur Evri-Sendungsverfolgung

### Ist Evri dasselbe wie Hermes?

In Großbritannien ja: Evri ist der seit März 2022 gültige Name von Hermes UK. [Hermes in Deutschland](carrier:hermes-de) ist dagegen ein eigenständiges Unternehmen; beginnt deine Nummer mit `H` und 15 Ziffern, prüfe in der Versandmail, welchen Dienst der Shop beauftragt hat.

### Gehört Evri zu DHL oder Royal Mail?

Zu keinem von beiden. Nach der Freigabe durch die britische Wettbewerbsbehörde im September 2025 schloss Evri (damals im Besitz von durch Apollo verwalteten Fonds) am 1. Oktober 2025 den Zusammenschluss mit DHL eCommerce UK zur Evri Group ab, wobei die DHL Group eine Minderheitsbeteiligung an Evri übernahm. DHL eCommerce UK tritt seit 2026 unter dem Namen **Evri Premium – a network of DHL** auf, bleibt aber ein getrenntes Netzwerk für hochwertige und größere Pakete; gewöhnliche Sendungen von [DHL](carrier:dhl) oder [DHL eCommerce](carrier:dhl-ecommerce) laufen nicht über das Standard-Tracking von Evri.

### Wie lange dauert ein Evri-Paket aus Großbritannien?

Während Evris allgemeine Auslands-FAQ weltweit 3 bis 7 Werktage nennt, geben die Länderseiten ab Großbritannien 5 bis 8 Tage nach Deutschland, durchschnittlich 5 bis 8 Tage nach Österreich und 7 bis 10 Tage in die Schweiz an, jeweils vorbehaltlich Feiertagen und Zollabfertigung. Innerhalb Großbritanniens nennt das Evri-Hilfecenter 1 bis 2 Tage für *Next Day* und 3 bis 5 Tage für *Standard* (andere Evri-Seiten sprechen von 1 bis 3 Tagen).

### Ist eine E-Mail von noreply.priority@evri.com echt?

Evri gibt diese Adresse selbst als Kontaktweg an: Auf `evri.com/contact-us` öffnet „Email us“ unter *Tell us straight* eine Nachricht an `noreply.priority@evri.com`, um nach einem ersten Klärungsversuch des Kundendienstes das Senior-Team zu erreichen (mit Bestätigung binnen einer Stunde während britischer Bürozeiten). Da Evris Sicherheitsseiten nur Domains wie `@evri.com` aufführen, nicht diese Adresse, und Absenderadressen gefälscht oder minimal vertippt sein können, prüfe die Schreibweise Buchstabe für Buchstabe und antworte im Zweifel nicht direkt, sondern nutze die Kontaktwege auf `evri.com`.

### Kann ich ein Evri-Paket nur mit meiner Postleitzahl verfolgen?

Nein. Sowohl auf GlobalEco als auch auf `evri.com` und in Peek brauchst du immer zuerst den 16-stelligen Sendungscode (beziehungsweise auf `evri.com` die 8-stellige britische *calling card*); die Postleitzahl dient auf `evri.com` nur als Zusatzangabe für Details oder Umleitungen innerhalb Großbritanniens.

:::sources
- [Evri: Track a parcel](https://www.evri.com/track-a-parcel) – 16-stelliger Trackingcode, 8-stellige Calling Card und Link zum Auslands-Tracking
- [GlobalEco: Evri International parcel tracking](https://globaleco.app/track/) – offizielles Trackingportal für Evri International
- [Evri: International courier services](https://www.evri.com/our-services/international-courier-services) – Versand in über 200 Länder und Gebiete
- [Evri: Postage to Europe](https://www.evri.com/our-services/international-courier-services/europe) – IOSS und Benachrichtigung bei Zollabgaben
- [Evri: Sending a parcel to Germany](https://www.evri.com/our-services/international-courier-services/germany) – 5 bis 8 Tage Standardlaufzeit und Hinweis zu Einfuhrabgaben
- [Evri: Sending a parcel to Austria](https://www.evri.com/our-services/international-courier-services/austria) – durchschnittlich 5 bis 8 Tage nach Österreich
- [Evri: Sending a parcel to Switzerland](https://www.evri.com/our-services/international-courier-services/switzerland) – durchschnittlich 7 bis 10 Tage und Zahlungslink für den Zoll
- [Evri: EU Customs Update November 2026](https://www.evri.com/news/eu-customs-update-november-2026) – 3 € Zoll und 2 € Bearbeitungsgebühr je Zollgruppe (6-stelliger HS-Code)
- [Evri: International deliveries FAQ](https://www.evri.com/faqs/sending-a-parcel/international-deliveries) – 3 bis 7 Werktage Richtwert und eBay Global Shipping Programme
- [Evri: How to contact customer services for international parcels](https://www.evri.com/faqs/sending-a-parcel/how-to-contact-customer-services-for-international-parcels) – Präfixe `H04G0C` und `H0595C`, Assistent Ezra und Eingabe `0` ohne Postleitzahl
- [Evri: What is an Evri gateway?](https://www.evri.com/faqs/receiving-a-parcel/what-is-an-evri-gateway) – britische Zollabfertigung im Evri Gateway
- [Evri: How do I track my parcel?](https://www.evri.com/faqs/receiving-a-parcel/how-do-i-track-my-parcel) – ParcelShop-Status „on its way“, Calling Card und Lockers
- [Evri: Tracking status hasn't changed](https://www.evri.com/help-and-support/help-centre/receiving/awaiting-delivery/tracking-status) – „We're expecting it“ und 24-Stunden-Regel
- [Evri: Tracking status hasn't changed (senders)](https://www.evri.com/help-and-support/help-centre/sending/delivery-problem/status-has-not-changed) – 24 Stunden bei Next Day, 5 Tage bei Standard
- [Evri: Update on my delivery](https://www.evri.com/help-and-support/help-centre/receiving/awaiting-delivery/update-delivery) – 1 bis 2 / 3 bis 5 Tage und 2-Stunden-Zeitfenster
- [Evri: You asked me to contact you](https://www.evri.com/help-and-support/help-centre/receiving/awaiting-delivery/contact-you) – Rückfrage per Chat bei fehlenden Zustellangaben
- [Evri: Change my delivery place or date](https://www.evri.com/help-and-support/help-centre/receiving/awaiting-delivery/change-delivery) – Adressänderung nur durch Absender und Umleitungsoptionen
- [Evri: Can't find my parcel](https://www.evri.com/help-and-support/help-centre/receiving/not-find-parcel/all-correct) – zugestellt gemeldet, aber nicht auffindbar
- [Evri: Haven't received my parcel](https://www.evri.com/faqs/receiving-a-parcel/what-can-i-do-if-i-havent-received-my-parcel) – Meldung an den Verkäufer nach 7 Tagen
- [Evri: Has the retailer received my return?](https://www.evri.com/faqs/returning-a-parcel/how-will-i-know-the-retailer-has-received-my-return) – Abschlussstatus „On its way back to the retailer“
- [Evri: Home](https://www.evri.com/) – 1-Stunden-Fenster und bis zu 3 Zustellversuche in UK
- [Evri: Safe place guide](https://www.evri.com/guides/safe-place) – Ablageort und Rückkehr ins Depot
- [Evri: Send a parcel](https://www.evri.com/send) – nächster ParcelShop nach 3 Versuchen
- [Evri: Diversions](https://www.evri.com/diversions) – Umleitung einer UK-Zustellung
- [Evri: Expected delivery time](https://www.evri.com/faqs/receiving-a-parcel/expected-delivery-time) – Zeitfenster am Morgen und Sendungen mit Unterschrift
- [Evri: Can I collect my parcel?](https://www.evri.com/faqs/receiving-a-parcel/can-i-collect-my-parcel) – keine Abholung während der Kuriertour
- [Evri: ParcelShop delivery](https://www.evri.com/our-services/parcelshop-delivery) – 10 Tage Lagerfrist, Ausweisdokumente und Post Office
- [Evri: Lockers](https://www.evri.com/parcelshops/lockers) – 24/7-Zugang, 3 Tage Abholfrist und „help“-Taste
- [Evri: Terms and conditions](https://www.evri.com/terms-and-conditions) – Zustellversuche im In- und Ausland sowie 28/45 Tage Reklamationsfrist
- [Evri: Claims](https://www.evri.com/faqs/account/how-do-i-submit-a-claim-for-a-lost-or-damaged-parcel) – Reklamation durch den Absender
- [Evri: Contact us](https://www.evri.com/contact-us) – 24/7-Chat, Rufnummer 0330 808 5456 und Tell us straight
- [GOV.UK: Call charges](https://www.gov.uk/call-charges) – Kosten britischer 03-Rufnummern
- [Evri: Cyber security](https://www.evri.com/cyber-security) – offizielle E-Mail-Domains, SMS-Absender „Evri“ und Meldung an 7726
- [Evri: How will Evri contact you?](https://www.evri.com/faqs/receiving-a-parcel/how-will-evri-contact-me) – `evri.link`-Nachrichten
- [Evri: Is this Evri text/email genuine?](https://www.evri.com/faqs/receiving-a-parcel/is-this-evri-text-email-genuine) – gefälschte Absenderadressen erkennen
- [Evri: About us](https://www.evri.com/about-us) – Umbenennung von Hermes UK im März 2022 und Partnerhändler
- [Evri: Introducing the Evri Group](https://www.evri.com/press/introducing-the-evri-group-the-uks-premier-parcel-delivery-company) – Zusammenschluss am 1. Oktober 2025 und getrenntes Netzwerk Evri Premium
- [Evri: Evri Premium – a network of DHL](https://www.evri.com/our-services/evri-premium-dhl) – eigenes Premium-Netz für hochwertige und größere Sendungen
- [GOV.UK: EVRi / DHL merger inquiry](https://www.gov.uk/cma-cases/evri-slash-dhl-merger-inquiry) – Apollo, Minderheitsbeteiligung der DHL Group und CMA-Freigabe
- [Europäische Kommission: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – Wegfall der 150-€-Zollfreigrenze, 3-€-Zoll und EU-Bearbeitungsgebühr
- [Zoll.de: Internetbestellungen](https://www.zoll.de/DE/Privatpersonen/Postsendungen-Internetbestellungen/Sendungen-aus-einem-Nicht-EU-Staat/Zoll-und-Steuern/Internetbestellungen/internetbestellungen_node.html) – deutsche Einfuhrregeln für Drittlandssendungen
- [BAZG: Postversand, Interneteinkauf, Kurierverkehr](https://www.bazg.admin.ch/de/postversand-interneteinkauf-kurierverkehr-einfuhr-firmen) – Schweizer Einfuhrsteuer 8,1 % / 2,6 % und 5-CHF-Grenze
- [BAZG: Verzollungskosten und Paketverfolgung](https://www.bazg.admin.ch/de/verzollungskosten-und-paketverfolgung) – Verzollungskosten des Transporteurs
- [Your Europe: Versand und Lieferung](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_de.htm) – 30-Tage-Lieferfrist und Haftung des Händlers bis zum Empfang
- [Universal Parcel Scraper: Evri International](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/evri) – `C` an 6. Stelle, GlobalEco-Felder und Partnerübergabe
- [Universal Parcel Scraper: Evri UK](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/evri-uk) – 16-stellige Barcodes, `C00HHA`, 16 Ziffern und UK-Statuscodes
:::
