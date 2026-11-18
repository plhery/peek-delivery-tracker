---
title: India Post Sendungsverfolgung: Speed-Post-Nummer und Status
description: Sendung von India Post oder Speed Post verfolgen: Aufbau der IN-Nummer, Bedeutung der Statusmeldungen, Zustellung in Deutschland, Österreich und der Schweiz.
slug: india-post-sendungsverfolgung
published: 2026-11-18
updated: 2026-11-18
---

India Post ist das Department of Posts, der staatliche Postdienst **Indiens**, der nach eigenen Angaben 164.999 Postämter betreibt – das größte Postnetz der Welt. Innerhalb Indiens befördert India Post unter anderem Speed Post, Einschreiben und India Post Parcel; ins Ausland gehen EMS (International Speed Post), verfolgbare Kleinpakete (International Tracked Packet) und Luftpostpakete. Eine Sendung lässt sich auf [indiapost.gov.in](https://www.indiapost.gov.in/) verfolgen, wo ein CAPTCHA abgefragt wird, oder direkt oben im Suchfeld. India Post nennt den Code *article barcode number* – bei einer Online-Bestellung bekommst du ihn vom Verkäufer.

## India-Post-Sendungsnummer: Aufbau und Beispiele

Eine Sendungsnummer von India Post hat 13 Zeichen im [internationalen Postformat](guide:tracking-number-formats): zwei Buchstaben, neun Ziffern und am Ende `IN`. Die letzte der neun Ziffern ist eine Prüfziffer. Beispiele mit frei erfundenen Ziffern:

| Beispielnummer | Was sie bezeichnet |
| --- | --- |
| `EE482910576IN` | Eine von India Post nummerierte Sendung innerhalb Indiens oder ins Ausland. Die beiden Anfangsbuchstaben wechseln je nach Versandart; entscheidend ist `IN` am Schluss. |
| `EE482910576DE` | Endet auf ein anderes Länderkürzel, hier `DE`: Eine ausländische Post hat die Nummer vergeben. Ein aus dem Ausland nach Indien verschicktes Paket behält die Nummer der Aufgabepost. |

Die Sendungsverfolgung von India Post deckt Speed Post, eingeschriebene und wertversicherte Sendungen, Business Parcel und Express Parcel, elektronische Postanweisungen (*electronic money orders*) sowie International EMS ab. Nummer noch nicht zur Hand? Siehe [Sendungsnummer finden](guide:find-tracking-number).

## Statusmeldungen von India Post erklärt

In den FAQ fasst India Post den Sendungsverlauf mit den Schritten *booked*, *received*, *bagged*, *dispatched*, *out for delivery* und *delivered* zusammen. Diese Meldungen kennt Peek in der üblichen Reihenfolge:

| India Post meldet | Was das für deine Sendung bedeutet |
| --- | --- |
| `Item Booked` | Der Absender hat die Sendung eingeliefert oder online gebucht und India Post hat sie angenommen. |
| `Bag Received for Forwarding` | Ein Versandsack mit der Sendung ist zur Weiterleitung in einem Postamt eingetroffen. |
| `Assigned to load plan` | Die Sendung ist für den nächsten Transport eingeplant. |
| `Transport Leg Completed` | Ein Transportabschnitt ist abgeschlossen. |
| `Transfer to OOE (Otb)` | Unterwegs zum indischen Auswechslungsamt (*Office of Exchange*), das Auslandspost abfertigt. |
| `Aircraft Departure` | Das Flugzeug mit der Sendung ist gestartet. Mitunter schreibt India Post dafür `UPLIFT`. |
| `Bag unloaded at destination Airport` | Der Versandsack wurde am Zielflughafen ausgeladen. |
| `Send item to domestic location (Inb)` | Das Auswechslungsamt im Zielland hat die Sendung in das dortige Inlandsnetz weitergeleitet. |
| `Item Delivered` | Zugestellt. |

Der Zusatz `(Otb)` kennzeichnet einen Scan der absendenden Post in Indien, `(Inb)` eine Meldung der Post im Zielland, die India Post weiterreicht. In den Daten, die Peek ausliest, liefert India Post inzwischen häufig Kurz-Codes wie `BAG_DISPATCH`; bekannte Codes schlüsselt Peek als Klartext wie `Bag Dispatched` oder `Item Presented To Customs` auf und wandelt übrige Codes in lesbare Wörter um. Allgemeines zu Versandphasen steht unter [Was Tracking-Status bedeuten](guide:tracking-statuses).

## Empfang einer India-Post-Sendung innerhalb Indiens

Die folgenden Regeln gelten für Zustellungen in **Indien**:

**Laufzeiten.** Für Speed Post innerhalb Indiens nennt India Post ab Einlieferung bis Zustellung als Richtwert 1 bis 2 Tage im Ortsverkehr, 1 bis 3 Tage zwischen Metropolen, 1 bis 4 Tage zwischen Bundesstaatshauptstädten oder innerhalb eines Bundesstaats sowie 4 bis 5 Tage im übrigen Land – zuzüglich eines Tags, wenn ein Zweigpostamt (*branch office*) zustellt. 24 Speed Post stellt in sechs indischen Metropolen (New Delhi, Mumbai, Bengaluru, Chennai, Kolkata und Hyderabad) an sieben Tagen pro Woche am nächsten Tag zu; bei Verspätung kann der Absender das Porto binnen 7 Tagen online zurückfordern.

**SMS und Einmalcode (OTP).** Bei einem Speed-Post-Paket in Indien erhält die hinterlegte Mobilnummer eine SMS beim Eintreffen im Zustellpostamt und eine zweite bei der Übergabe. Bestimmte Speed-Post-Sendungen werden nur gegen einen Einmalcode (OTP) ausgehändigt: Bei 24 Speed Post gehört das zum Tarif, bei anderem Speed Post ist es eine kostenpflichtige Zusatzleistung.

**Verpasste Zustellung.** Im Tracking von India Post erscheint ein Vermerk, sobald eine *Delivery Intimation Notice* ausgestellt wurde, die über die Bereitstellung zur Zustellung informiert. Siehe auch [Paketbote verpasst](guide:missed-delivery) und [Nicht abgeholte Pakete](guide:uncollected-parcel).

**Adressänderung.** Wer in Indien online über ein India-Post-Konto gebucht hat, kann die Sendung zurückrufen oder umleiten lassen, solange sie noch nicht zugestellt oder einem Zusteller für die Tour zugeteilt wurde.

## India-Post-Pakete ins Ausland: Zustellung und Zoll

Ins Ausland verschickt India Post EMS (International Speed Post, bis 35 kg), das International Tracked Packet für Online-Händler (bis 5 kg) und International Air Parcel (bis 20 kg) – jeweils mit Sendungsverfolgung. Nach der Landung stammen Scans mit `(Inb)` von der Post deines Landes; mit derselben `IN`-Nummer lässt sich die Sendung dort weiterverfolgen, etwa bei [DHL](carrier:dhl) in **Deutschland**, der [Österreichischen Post](carrier:austrian-post) in **Österreich**, der [Schweizerischen Post](carrier:swiss-post) in **der Schweiz**, [USPS](carrier:usps) in den **USA** oder [Royal Mail](carrier:royal-mail) (Parcelforce für EMS) im **Vereinigten Königreich**.

**Zoll in der Europäischen Union (Deutschland, Österreich).** Da Indien außerhalb der EU liegt, durchläuft jedes Paket im Bestimmungsland den Zoll. In der **EU** fällt seit dem 1. Juli 2026 auf online außerhalb der EU gekaufte Waren in Sendungen mit einem Sachwert bis 150 € ein EU-Zoll von 3 € pro Position (*per item*) an; seit dem 1. November 2026 kommt unabhängig vom Wert eine Unions-Bearbeitungsgebühr von 2 € pro Position hinzu, zusätzlich zur Einfuhrumsatzsteuer im Zielland und – oberhalb von 150 € – den regulären Zollsätzen. Als eine Position zählen Waren in einem Paket, die dieselbe Zolltarifnummer, Warenbezeichnung und – soweit erforderlich – denselben Ursprung teilen. India Post führt Delivery Duty Paid (DDP) unter den Zusatzleistungen für Exporteure auf: Frag beim Shop nach, ob DDP gebucht wurde.

**Zoll in der Schweiz.** In **der Schweiz** beträgt die Einfuhrsteuer laut Bundesamt für Zoll und Grenzsicherheit (BAZG) 8,1 % der Bemessungsgrundlage bzw. 2,6 % zum reduzierten Satz (unter anderem für Bücher und Nahrungsmittel). Aus verwaltungsökonomischen Gründen werden laut BAZG „Zollabgaben bis 5 Schweizerfranken nicht erhoben“ und ebenso „Steuerbeträge bis 5 Schweizerfranken nicht erhoben“ (das entspricht einer Bemessungsgrundlage von 62 Schweizerfranken bei 8,1 % MWST oder 193 Schweizerfranken bei 2,6 % MWST), zuzüglich etwaiger Verzollungskosten des Beförderers. Mehr zum Ablauf steht unter [Paket hängt beim Zoll](guide:customs).

**Ländersperren und Wiederaufnahmen.** Setzt India Post den Versand in ein Land aus oder nimmt ihn wieder auf – wie bei der Aussetzung für Kanada im Oktober 2025 oder der Wiederaufnahme für Irak, Kuwait und Libanon im August 2026 –, veröffentlicht sie dazu eine Mitteilung unter [News and Updates](https://www.indiapost.gov.in/media/newsandupdates).

**Sendungen nach Indien.** Ein aus dem Ausland nach Indien aufgegebenes Paket trägt die Sendungsnummer der Aufgabepost. Verfolge es zuerst dort; die FAQ von India Post weisen darauf hin, dass Meldungen ausländischer Postverwaltungen verspätet eintreffen können.

## Sendungsverfolgung steht still, Paket verloren oder zurückgeschickt

**Länger kein neuer Scan.** Laut India Post heißt ein fehlender Scan nicht, dass die Sendung nicht unterwegs ist: Vor allem aus ländlichen Gebieten Indiens oder von ausländischen Postgesellschaften können Statusmeldungen verzögert online erscheinen. Zeigt ein Auslandspaket zuletzt `Aircraft Departure`, hat es Indien verlassen – prüfe dieselbe Nummer dann bei deiner heimischen Post. Weitere Ursachen erklärt [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating).

**Verspätung, Verlust oder Beschädigung (Regeln von India Post).** Bei India Post lässt sich eine Beschwerde unter Angabe der Sendungsnummer einreichen, sobald die Regellaufzeit überschritten ist, spätestens jedoch binnen 60 Tagen nach der Einlieferung; als Regeldauer für die Bearbeitung nennt India Post 90 Tage. Beim innerindischen Speed Post erstattet India Post bei Verspätung das Beförderungsentgelt und zahlt bei Verlust, Beraubung oder Beschädigung das Doppelte des Entgelts oder 1.000 ₹, je nachdem, welcher Betrag niedriger ist. Bei EMS-Warensendungen ins Ausland ist der Ersatz auf den Warenwert oder 130 SZR (Sonderziehungsrechte) begrenzt, je nachdem, was niedriger ist, zuzüglich Porto; eine Verzögerung von mehr als 5 Tagen über die veröffentlichte Norm hinaus bringt 5 % des Portos zurück. Ein International Tracked Packet ist bis zum Warenwert oder 1.000 ₹ abgesichert, je nachdem, welcher Betrag geringer ist. Hast du online bestellt, wende dich zugleich an den Händler: siehe [Paket verloren](guide:lost-parcel) und [Paket beschädigt](guide:damaged-parcel).

**Als zugestellt gemeldet, aber nicht da.** Reiche eine Beschwerde über die unten genannten Wege ein oder – innerhalb Indiens – im Beschwerdebuch, das jedes Postamt führt: siehe [Zugestellt, aber nicht erhalten](guide:delivered-not-received).

**Rücksendung.** Bei einer Online-Buchung über India Post kann der Absender eine abweichende Rücksendeadresse hinterlegen. Peek zeigt Rückläufer unter „Zurück an Absender“: siehe [Zurück an den Absender](guide:returned-to-sender).

## Kundendienst von India Post und Beschwerden

Offizielle Kontaktwege von India Post:

- **Kundenbetreuung (gebührenfrei innerhalb Indiens):** `1800 266 6868`. Mitarbeitende sind von 8:00 bis 20:00 Uhr indischer Zeit (IST) erreichbar; das Sprachmenü (IVRS) läuft rund um die Uhr inklusive sonntags, außer an gesetzlichen Feiertagen (*gazetted holidays*) in Indien.
- **Beschwerden:** online über die Seite [File a Complaint](https://www.indiapost.gov.in/grievance-redressal/file-a-complaint) von India Post (dort lässt sich auch der Bearbeitungsstand abfragen), in Indien direkt im Postamt (beim *Postmaster* oder *Superintendent of Post Offices*) oder telefonisch über das Callcenter. Ein Benutzerkonto ist nicht nötig.
- **Eskalation in Indien:** hilft India Post nicht in angemessener Frist ab, nennt India Post das staatliche Directorate of Public Grievances (`dpg.gov.in`).

Auf der Startseite warnt India Post vor gefälschten Links im Namen der Post: Nutze ausschließlich `www.indiapost.gov.in` und gib über unautorisierte Links niemals OTP-Codes oder Bank-, Karten- oder UPI-Daten preis („do not share OTP, banking, card, UPI or other personal/financial information through unauthorized links“). Siehe dazu [Paket-Phishing per SMS erkennen](guide:delivery-scam-texts).

## India-Post-Sendung mit Peek verfolgen

[Peek](/) erkennt zwei Buchstaben, neun Ziffern und `IN` bei gültiger Prüfziffer sofort als India Post und zeigt „Erkannter Paketdienst“; über „Ändern“ lässt sich ein anderer Dienst wählen. Stimmt die Prüfziffer nicht (etwa bei `EE482910577IN`), meldet Peek „Die Prüfziffer dieser Nummer geht nicht auf.“: Vergleiche die Nummer mit der Versandbestätigung oder wähle „Wie eingegeben verfolgen“. Ist die Sendung unter India Post angelegt, steht auf peektracker.com bis zum ersten Scan „Warten auf India Post“.

Peek fragt nicht das eigene Portal von India Post ab, sondern liest die Scans über MySpeedPost aus, einen externen Tracking-Dienst außerhalb der Domain von India Post, der sie bei India Post abruft (oder über track91).

Peek übernimmt den Wortlaut von India Post samt Postamt und verortet das Amt auf der Karte, sobald der Ort bestätigt ist. Ein Abflug erscheint als `Aircraft Departure`. Zugleich ordnet Peek die Meldungen seinen eigenen Phasen zu: Ein Fehlversuch wird zu „Zustellversuch“, eine Annahmeverweigerung, Beschädigung, ein Verlust oder eine unzureichende Adresse zu „Problem gemeldet“. Über track91 fallen die Texte kürzer aus: `Dispatched` (vom Auswechslungsamt ins Ausland geschickt) und `Aircraft arrival in Transit country` (Zwischenlandung unterwegs) stehen unter „Unterwegs“, `Delivered` unter „Zugestellt“. Name, Anschrift und Unterschrift des Empfängers lässt Peek weg; ein voraussichtliches Zustelldatum gibt es nicht. Scans in Indien liest Peek in indischer Zeit (UTC+5:30) und rechnet alle Zeiten in die Zeitzone deines Geräts um; auf peektracker.com und in der iPhone-App steht diese Zone unter dem Verlauf („Uhrzeiten in deiner Zeitzone: …“).

Nennt India Post das Zielland – oder nennt sie keines und du fragst von außerhalb Indiens ab –, prüft Peek dieselbe `IN`-Nummer zusätzlich bei einem bekannten Zustelldienst vor Ort, etwa [DHL](carrier:dhl) in Deutschland, der [Österreichischen Post](carrier:austrian-post) in Österreich, der [Schweizerischen Post](carrier:swiss-post) in der Schweiz, [La Poste](carrier:la-poste) in Frankreich, USPS, Royal Mail (bzw. Parcelforce bei EMS), Canada Post oder Australia Post. Liefert dieser Dienst frische Scans, wechselt Peek unter Erhalt der bisherigen India-Post-Historie zu ihm. Für den Großteil Europas sowie unter anderem NZ Post in Neuseeland und Qatar Post in Katar kennt Peek eine solche Post, nicht aber für die Vereinigten Arabischen Emirate, Kuwait, Oman, Singapur, Malaysia oder Sri Lanka. Nummern fremder Postverwaltungen fragt Peek bei India Post gar nicht erst an: Wählst du India Post manuell dafür aus, erscheint „vergibt keine Nummern wie diese“.

Solange du das Paket geöffnet hältst oder am Zustelltag Benachrichtigungen eingeschaltet hast, prüft Peek bis zu alle 10 Minuten – und ab 6:00 Uhr Ortszeit an dem Tag, an dem das Paket auf „In Zustellung“ springt, bis zu alle 2 Minuten, solange MySpeedPost oder der eigene Tracker deiner Zielpost antwortet (nach der Übergabe folgt Peek dem Takt der Zielpost; über andere Tracking-Dienste allein stündlich, nach einem ruhigen Tag alle 3 Stunden und am Zustelltag bei geöffnetem Paket oder aktiven Benachrichtigungen bis zu alle 15 Minuten). Außerhalb dieser Bedingungen sowie nach zwei Tagen ohne Neuigkeiten (oder nach 12 Stunden auf „Etikett erstellt“ bzw. „Beim Paketdienst eingegangen“) fragt Peek stündlich ab. Benachrichtigungen gibt es für „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“. Bleibt die Sendung bei India Post hängen, öffnet „Hol dir Hilfe bei India Post“ die Kontaktseite von India Post. Kontakt zu India Post, deiner Post oder dem Händler kann Peek nicht für dich aufnehmen.

## Häufige Fragen zur India-Post-Sendungsverfolgung

### Wie verfolge ich eine Speed-Post-Sendung von India Post?

Gib die 13-stellige Sendungsnummer mit der Endung `IN` auf indiapost.gov.in ein oder füge sie in Peek ein – dort läuft die Verfolgung auch im Zielland bis zur Zustellung weiter.

### Warum aktualisiert sich mein India-Post-Tracking nicht?

Scans aus ländlichen Postämtern in Indien oder von ausländischen Postverwaltungen erscheinen teils verzögert online. Steht bei einer Auslandssendung zuletzt `Aircraft Departure`, hat sie Indien verlassen: Prüfe dieselbe Nummer dann bei der Post deines Ziellands.

### Wie lange braucht Speed Post?

Innerhalb Indiens liegt der Richtwert von India Post bei 1 bis 3 Tagen zwischen Metropolen und 4 bis 5 Tagen ins übrige Land, plus einen Tag bei Zustellung über ein Zweigpostamt; 24 Speed Post liefert in sechs indischen Metropolen am nächsten Tag. Ins Ausland hängt die Dauer vom gewählten Tarif und der Einfuhrverzollung ab.

### Wie lautet die Telefonnummer des Kundendienstes von India Post?

`1800 266 6868` (innerhalb Indiens gebührenfrei): Mitarbeitende antworten von 8:00 bis 20:00 Uhr indischer Zeit, das Sprachsystem läuft rund um die Uhr außer an indischen gesetzlichen Feiertagen. Aus dem Ausland kannst du Beschwerden auch ohne Konto online auf indiapost.gov.in einreichen.

:::sources
- [India Post: Home](https://www.indiapost.gov.in/) – Sendungsverfolgung, Hotline, Betrugswarnung
- [India Post: Contact Us](https://www.indiapost.gov.in/help&Support/contactus) – gebührenfreie Nummer, Zeiten, Beschwerden im Postamt
- [India Post: Help and FAQs](https://www.indiapost.gov.in/grievance-redressal/help-faq) – Barcode-Nummer, verfolgbare Sendungen, Status, Verzögerungen, Beschwerden, Rückruf, Nachsendung und Rücksendeadresse
- [India Post: About Us](https://www.indiapost.gov.in/ministry/aboutus) – Department of Posts, 164.999 Postämter
- [India Post: Speed Post](https://www.indiapost.gov.in/mail&Parcel/mailproducts/premiumservices/speedpost) – OTP, Laufzeitstandards, SMS bei Speed Post Parcel, Haftung
- [India Post: 24/48 Speed Post FAQs](https://www.indiapost.gov.in/speedpost/faqs) – Zustellung am nächsten Tag in sechs Metropolen, OTP, Portoerstattung
- [India Post: India Post Parcel](https://www.indiapost.gov.in/mail&Parcel/mailproducts/domesticservices/indiapostparcel) – India Post Parcel
- [India Post: International Mail](https://www.indiapost.gov.in/mail&Parcel/mailproducts/mailservices/international) – Auslandsprodukte, DDP für Exporteure
- [India Post: International services](https://www.indiapost.gov.in/mail&Parcel/mailproducts/internationalservices) – EMS, International Tracked Packet, Luftpostpakete, Entschädigungsgrenzen
- [India Post: News and Updates](https://www.indiapost.gov.in/media/newsandupdates) – Länderhinweise zu Aussetzungen und Wiederaufnahmen
- [India Post: File a Complaint](https://www.indiapost.gov.in/grievance-redressal/file-a-complaint) – Online-Beschwerde und Eskalation über DPG
- [India Post: Guidelines on Complaint Registration](https://www.indiapost.gov.in/grievance-redressal/guidelines) – Fristen und Beschwerdewege
- [Europäische Kommission: EU-Zollreform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 3-€-Zoll und 2-€-Bearbeitungsgebühr
- [Europäische Kommission: Leitlinien zum 3-€-Zollsatz und zur Unions-Bearbeitungsgebühr (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – Berechnung pro Position und Definition einer Position
- [BAZG: Empfangen von Briefen und Paketen](https://www.bazg.admin.ch/de/empfangen-von-briefen-und-paketen) – Schweizer Einfuhrsteuer 8,1 % / 2,6 % und Nichterhebung von Beträgen bis 5 Schweizerfranken
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Scan-Wortlaute (inklusive track91), Zeitzonen und Übergabe an Zielpostgesellschaften
:::
