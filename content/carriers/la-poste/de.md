---
title: Colissimo & La Poste Sendungsverfolgung: Status & Zoll
description: Paket von La Poste oder Colissimo verfolgen: Nummernformate, französische Status auf Deutsch erklärt, Zustellung in D-A-CH, Zoll in der Schweiz und Kontakt.
slug: colissimo-la-poste-sendungsverfolgung
published: 2026-11-18
updated: 2026-11-20
---

Colissimo ist der Paketdienst von La Poste, der staatlichen französischen Post. Wenn du in Deutschland, Österreich oder der Schweiz auf ein Paket oder ein Einschreiben aus Frankreich wartest, kannst du die Sendungsnummer – oder einen Tracking-Link von `laposte.fr` – direkt oben ins Suchfeld einfügen. Die eigene Sendungsverfolgung von La Poste läuft auf `laposte.fr`. Die Nummer bekommst du vom Absender, bei Online-Bestellungen meist in der Versandbestätigung des Shops.

## Sendungsnummer von Colissimo und La Poste: so sieht sie aus

Mit frei erfundenen Beispielnummern erkennt man die gängigen Formate so:

| Format (erfunden) | Was dahintersteckt |
| --- | --- |
| `6A48291057365` | 1 Ziffer, 1 Buchstabe und 11 Ziffern: ein Colissimo-Paket oder ein verfolgter bzw. eingeschriebener Brief. Die letzte Ziffer ist eine Prüfziffer. |
| `LA482910576FR` | 2 Buchstaben, 9 Ziffern und `FR`: eine internationale Postsendungsnummer aus Frankreich für Colissimo-Pakete sowie Einschreiben und verfolgte Briefe. Die Ziffer vor `FR` ist eine Prüfziffer. |
| `87048291057364R` | 15 Zeichen mit den Anfängen `863` bis `866`, `870`, `875`, `880` oder `885`: eine Smart-Data-Nummer für verfolgte und eingeschriebene Briefe von La Poste. Das letzte Zeichen ist ein Prüfzeichen; auch ohne dieses letzte Zeichen lässt sich die 14-stellige Nummer zuordnen. |

Internationale Postnummern, die mit `XU`, `XR`, `XT`, `XW`, `XY` oder `PZ` beginnen und auf `FR` enden, gehören zu [Chronopost](carrier:chronopost), dem Expressdienst der Gruppe. Zwölfstellige Codes sprechen oft für Colis Privé, und reine 14-stellige Ziffernfolgen werden auch von [DPD](carrier:dpd-de) und anderen Paketdiensten genutzt – wirf im Zweifel einen Blick in die Versandmail des Händlers oder in unsere Übersicht zu [Sendungsnummern-Formaten](guide:tracking-number-formats). Nummer unauffindbar? Sieh nach, [wo du deine Sendungsnummer findest](guide:find-tracking-number).

## Französische Colissimo-Status auf Deutsch erklärt

Peek übernimmt die Scan-Texte von La Poste im französischen Wortlaut und ordnet dem Paket anhand des letzten Ereignisses eine eigene Phase auf Deutsch zu. Diese Meldungen siehst du in der typischen Reihenfolge einer Sendung (die Zustellmeldungen stammen vom französischen Netz):

| La Poste schreibt | Peek zeigt | Was das für dich heißt |
| --- | --- | --- |
| `Votre Colissimo va bientôt nous être confié ! Il est en cours de préparation chez votre expéditeur.` | Etikett erstellt | Der Absender bereitet die Sendung vor; La Poste hat sie noch nicht übernommen. |
| `Votre colis a été déposé dans un point postal.` | Beim Paketdienst eingegangen | Das Paket wurde an einer Annahmestelle von La Poste eingeliefert. |
| `Votre colis est en transit sur nos plateformes logistiques.` | Unterwegs | Die Sendung läuft zwischen den Logistik- und Sortierzentren. |
| `Les formalités import/export sont en cours sur votre colis.` | Beim Zoll | Ein- oder Ausfuhrformalitäten beim Zoll laufen (etwa auf dem Weg in die Schweiz). |
| `Votre Colissimo est en cours de traitement sur le site de tri local.` | Unterwegs | Ankunft und Sortierung im regionalen Verteilzentrum. |
| `Votre colis est sur son site de distribution. Nous le préparons pour le mettre en livraison.` | In Zustellung | Wird am Zustellstandort für die heutige Tour vorbereitet. |
| `Votre colis n'a pas pu vous être remis. Il sera mis en livraison demain (hors dimanche et jours fériés).` | Zustellversuch | Zustellung hat nicht geklappt; nächster Versuch am folgenden Werktag (außer sonn- und feiertags). |
| `Nous sommes passés mais nous n'avons pu vous remettre votre colis. Il va être acheminé vers votre point de retrait.` | Zustellversuch | Niemand angetroffen; das Paket wird an eine Abholstelle weitergeleitet. |
| `Votre colis est disponible dans votre point de retrait pendant un délai de 15 jours calendaires.` | Abholbereit | Liegt zur Abholung bereit – die Frist (zum Beispiel 15 Kalendertage oder `Le délai de retrait est de 5 jours.`) steht direkt in der Meldung. |
| `Votre colis est livré dans votre boite aux lettres.` | Zugestellt | Im Briefkasten zugestellt. |
| `Votre colis vous a attendu dans votre point de retrait jusqu'à la date limite. Nous sommes contraints de le renvoyer à l'expéditeur.` | Problem gemeldet | Abholfrist verstrichen; das Paket geht zurück an den Absender. |
| `Votre colis est livré à son expéditeur à la suite d'un retour.` | Zurück an Absender | Die Rücksendung ist wieder beim Absender angekommen. |

Besonders leicht missverständlich ist `Votre colis est sur son site de distribution…`: Das klingt, als läge das Paket noch ruhend im Depot, markiert bei La Poste aber bereits die morgendliche Vorbereitung für die Zustelltour desselben Tages. Mehr zu erfolglosen Zustellungen und Abholfristen steht in unseren Ratgebern [Zustellung verpasst](guide:missed-delivery) und [Paket nicht abgeholt](guide:uncollected-parcel).

## Wer stellt Colissimo in Deutschland, Österreich und der Schweiz zu?

Sobald ein Paket Frankreich verlässt, kann La Poste im Sendungsverlauf den ausländischen Zustellpartner und dessen eigene Sendungsnummer nennen; Peek blendet beides direkt auf der Paketkarte ein. Bei internationalen Postnummern auf `FR` wie `LA482910576FR` verknüpft Peek die Sendung je nach Zielland außerdem mit der jeweiligen Landespost – in Deutschland mit [DHL](carrier:dhl), in Österreich mit der [Österreichischen Post](carrier:austrian-post) und in der Schweiz mit der [Schweizerischen Post](carrier:swiss-post) – und wechselt dorthin, sobald dort neuere Scans vorliegen.

Meldet sich bei dir vor Ort [DHL](carrier:dhl), [DPD](carrier:dpd-de), die [Österreichische Post](carrier:austrian-post) oder die [Schweizerische Post](carrier:swiss-post) per E-Mail, App oder Abholkarte mit einer eigenen Nummer, gelten für Abstellgenehmigung, Paketshop, Packstation und Lagerfrist die Regeln dieses Zustellers.

## Zoll: EU-Binnenmarkt vs. Schweiz

- **Deutschland und Österreich:** Zwischen dem europäischen Frankreich, Deutschland und Österreich fallen keine Zollformalitäten an. Die französischen Überseedepartements (Guadeloupe, Französisch-Guayana, Martinique, Réunion, Mayotte) liegen dagegen außerhalb des EU-Mehrwertsteuergebiets: Für ein Paket von dort kann Einfuhrumsatzsteuer anfallen. Kommt eine Bestellung jedoch ursprünglich aus einem Nicht-EU-Land in die EU, gilt laut Europäischer Kommission und deutschem Zoll seit dem 1. Juli 2026 auf Sendungen bis 150 € ein pauschaler Zoll von 3 € je Warenkategorie und seit dem 1. November 2026 zusätzlich eine EU-Abfertigungsgebühr von 2 € je Warenkategorie unabhängig vom Warenwert.
- **Schweiz:** Da die Schweiz nicht zur EU gehört, durchläuft jedes Paket aus Frankreich die Schweizer Einfuhrverzollung. Laut Bundesamt für Zoll und Grenzsicherheit (BAZG) und [Schweizerischer Post](carrier:swiss-post) zieht der befördernde Zusteller anfallende Mehrwertsteuer, Zölle und Verzollungsgebühren ein; Abgaben bis 5 CHF werden nicht erhoben (das entspricht einem Warenwert inklusive Versandkosten von rund 62 CHF zum Normalsatz von 8,1 % bzw. 193 CHF zum reduzierten Satz von 2,6 %), und private Geschenksendungen sind bis 100 CHF (ohne Alkohol und Tabak) abgabenfrei. Details findest du unter [Paket beim Zoll](guide:customs).

## Paket hängt fest, ist verloren oder beschädigt

**Keine Bewegung im Verlauf.** Prüfe, ob bereits ein Zusteller in deinem Land übernommen hat, und kontaktiere nach einigen ruhigen Tagen den Absender. Hintergründe erklärt [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating).

**Verloren oder beschädigt.** Bei Online-Käufen in der EU ist zuerst der Händler zuständig: Laut *Your Europe* trägt der Verkäufer das Transportrisiko bis zur Übergabe an dich und muss ohne anderweitige Vereinbarung innerhalb von 30 Tagen liefern; nach einer angemessenen Nachfrist kannst du vom Vertrag zurücktreten und dein Geld zurückverlangen. Siehe [Paket verloren](guide:lost-parcel), [Paket beschädigt](guide:damaged-parcel), [zugestellt, aber nicht erhalten](guide:delivered-not-received) und [zurück an den Absender](guide:returned-to-sender).

**Beschwerde bei La Poste.** Nach Angaben der französischen Postregulierungsbehörde Arcep ist eine Reklamation kostenlos und sowohl für Absender als auch für Empfänger möglich (wobei sich deren Ansprüche unterscheiden). Hilft die erste Antwort des Kundenservice nicht weiter, kannst du über dieselben Kontaktwege den Beschwerdedienst (*service recours*) von La Poste um eine zweite Prüfung bitten. Bleibt auch das erfolglos oder erhältst du zwei Monate lang keine Antwort, kannst du innerhalb eines Jahres ab deiner ersten schriftlichen Beschwerde kostenlos den *Médiateur du Groupe La Poste* einschalten.

## La Poste & Colissimo Kontakt

Laut Arcep und dem amtlichen französischen Verwaltungsportal *Service-Public.fr* erreichst du den Verbraucherservice von La Poste so:

- **Aus Deutschland, Österreich und der Schweiz:** `+33 810 821 821` (internationaler Kundenservice), Montag bis Freitag von 8:00 bis 19:00 Uhr französischer Zeit.
- **Innerhalb Frankreichs:** `3631`, Montag bis Freitag von 8:30 bis 19:00 Uhr und Samstag von 8:30 bis 13:00 Uhr (Service kostenlos zzgl. Festnetzgesprächstarif).
- **Online und per Post:** über das Kontaktformular auf `laposte.fr` oder schriftlich an `Service consommateurs, 99 999 LA POSTE`.

> **Vorsicht vor gefälschten Paket-Nachrichten:** Laut dem staatlichen französischen Portal *Cybermalveillance.gouv.fr* weist La Poste ausdrücklich darauf hin, dass sie innerhalb ihres Dienstes **niemals per SMS oder E-Mail eine Zahlung verlangt, um ein Paket abzuholen**; Phishing im Namen von La Poste oder Colissimo lässt sich an `phishing@laposte.fr` melden (in Frankreich außerdem per SMS an `33700`). Beachte aber den Unterschied im Zielland: Bei einer verzollungspflichtigen Sendung in die **Schweiz** (oder aus einem Nicht-EU-Staat nach Deutschland oder Österreich) informiert dich der zuständige lokale Zusteller wie die [Schweizerische Post](carrier:swiss-post) über seine eigenen offiziellen Kanäle über Einfuhrabgaben. Öffne keine unbekannten Kurzlinks, sondern prüfe die Sendung direkt auf `laposte.fr`, im Portal deiner Landespost oder in Peek. Mehr unter [gefälschte Paket-SMS erkennen](guide:delivery-scam-texts).

## Colissimo-Pakete mit Peek verfolgen

[Peek](/) erkennt La Poste / Colissimo sofort bei 13-stelligen Colissimo-Nummern mit gültiger Prüfziffer, bei 14-stelligen Nummern mit `870` am Anfang sowie bei 15-stelligen Smart-Data-Nummern und französischen Postnummern auf `FR` (außer Chronopost), jeweils mit gültigem Prüfzeichen. Bei anderen Varianten – etwa zwei Buchstaben plus 11 Ziffern, einer abweichenden Prüfziffer oder 14-stelligen Nummern, die auch [DPD](carrier:dpd-de) und andere nutzen – füg die Nummer trotzdem ein: Peek verfolgt über 4.500 Paketdienste und fragt die wahrscheinlichen an, oder du wählst La Poste manuell über „Paketdienst ändern“ (bzw. „Ändern“). Lehnt La Poste eine Nummer wegen eines falschen Formats oder Prüfzeichens ab, meldet Peek sofort: „Dieser Paketdienst vergibt keine Sendungsnummern wie diese. Prüfe die Nummer oder wähle einen anderen Paketdienst.“ Tracking-Links von `laposte.fr` mit `code=`, `idShip=` oder `idship=` funktionieren ebenfalls direkt.

Da Peek La Poste auf Französisch abruft, bleiben die Original-Scanzeilen französisch. Peek zeigt jeden Ort auf einer Karte, das voraussichtliche Lieferdatum, den Absendernamen, die Zustellzeit und – solange die Sendung zur Abholung bereitliegt – Name und Adresse der Abholstelle; deinen Namen und deine Anschrift speichert Peek nicht. Alle Zeiten werden aus `Europe/Paris` in die Zeitzone deines Geräts umgerechnet, auf `peektracker.com` und in den Apps für iPhone und Android mit dem Hinweis „Uhrzeiten in deiner Zeitzone: …“.

Solange du das Paket geöffnet hast oder am Liefertag Mitteilungen aktiviert sind, prüft Peek bis zu alle 10 Minuten – und am Tag der Zustelltour ab 6:00 Uhr Ortszeit bis zu alle 2 Minuten, solange der Tracker von La Poste direkt antwortet (über Tracking-Dienste allein stündlich, nach einem Tag ohne Scan alle 3 Stunden und am Liefertag bei geöffnetem Paket oder aktiven Mitteilungen bis zu alle 15 Minuten). Sonst sowie nach zwei Tagen ohne Neuigkeiten – oder nach 12 Stunden auf „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ – prüft Peek stündlich. Übernimmt ein anderer Zusteller die Auslieferung, zeigt Peek „Zustellung durch …“ (oder vor dessen erstem eigenen Scan „Bei … angekündigt, noch nicht eingetroffen“) und folgt dem Takt des ausliefernden Dienstes: etwa stündlich bei Royal Mail und Poste Italiane oder bis zu alle 30 Minuten bei PostNL und USPS, auch in Zustellung. Du kannst Mitteilungen für „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“ einschalten. Zustelloptionen ändern oder Zollabgaben begleichen kann Peek nicht.

## Häufige Fragen zur Colissimo Sendungsverfolgung

### Wie lange dauert ein Colissimo-Paket und liefert La Poste samstags?

In Frankreich stellt La Poste Groupe Briefe und Pakete an sechs Tagen pro Woche (Montag bis Samstag) zu; nach eigenen Angaben wurden 2025 dort 41 % der Colissimo-Sendungen bereits am Tag nach der Einlieferung zugestellt. Für den Versand nach Deutschland, Österreich oder in die Schweiz hängt der letzte Abschnitt vom lokalen Zustellpartner ab; sobald La Poste ein voraussichtliches Lieferdatum nennt, zeigt Peek es an.

### Muss ich für ein Paket aus Frankreich Zoll bezahlen?

Aus dem europäischen Frankreich nach Deutschland und Österreich nicht. Ein Paket aus den französischen Überseedepartements kann Einfuhrumsatzsteuer kosten, denn sie liegen außerhalb des EU-Mehrwertsteuergebiets. In die Schweiz fällt dagegen die reguläre Einfuhrverzollung an, sofern der Abgabenbetrag über 5 CHF liegt (bzw. bei privaten Geschenken der Wert von 100 CHF überschritten wird).

### Warum funktioniert meine Sendungsnummer auf laposte.fr nicht?

Prüfe die ersten Buchstaben einer `…FR`-Nummer: Beginnt sie mit `XU`, `XR`, `XT`, `XW`, `XY` oder `PZ`, gehört sie zu [Chronopost](carrier:chronopost). Ist das Paket bereits ins Zielland übergeben worden, führt der lokale Partner (etwa [DHL](carrier:dhl), die [Österreichische Post](carrier:austrian-post) oder die [Schweizerische Post](carrier:swiss-post)) die letzten Schritte in seiner eigenen Sendungsverfolgung weiter.

:::sources
- [La Poste Groupe: La branche Services-Courrier-Colis](https://www.lapostegroupe.com/fr/services-courrier-colis) – Colissimo als Paketdienst von La Poste, Zustellung an sechs Tagen pro Woche und 41 % Zustellung am Folgetag (2025)
- [Arcep: Comment résoudre un litige avec un opérateur postal ?](https://www.arcep.fr/mes-demarches-et-services/consommateurs/fiches-pratiques/la-resolution-des-litiges-postaux.html) – kostenlose Beschwerde durch Absender oder Empfänger, Kontaktwege, Service recours und Médiateur du Groupe La Poste
- [Le Médiateur du Groupe La Poste: Foire aux questions](https://mediateur.groupelaposte.com/pages/foire-aux-questions) – gebührenfreie Schlichtung nach zweistufiger Vorbeschwerde oder zwei Monaten ohne Antwort, Jahresfrist
- [Service-Public.fr: Service consommateur de La Poste](https://lannuaire.service-public.gouv.fr/centres-contact/R11647) – Rufnummern 3631 und +33 810 821 821, Sprechzeiten und Postanschrift 99 999 LA POSTE
- [Cybermalveillance.gouv.fr: L'hameçonnage à la livraison de colis](https://www.cybermalveillance.gouv.fr/tous-nos-contenus/actualites/escroqueries-livraison-colis) – La Poste verlangt per SMS oder E-Mail nie Gebühren zur Paketabholung, Meldung an phishing@laposte.fr und 33700
- [Your Europe: Versand und Lieferung](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_de.htm) – 30-Tage-Lieferfrist, Nachfrist, Rücktritt und Haftung des Händlers für Transportschäden
- [Europäische Kommission: EU-Zollreform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) und [Leitfaden zum 3-€-Zoll und zur 2-€-Abfertigungsgebühr (PDF, auf Englisch)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 3 € Pauschalzoll je Warenkategorie bis 150 € seit 1. Juli 2026 und 2 € EU-Abfertigungsgebühr je Warenkategorie seit 1. November 2026
- [Zoll: Internetbestellungen](https://www.zoll.de/DE/Privatpersonen/Postsendungen-Internetbestellungen/Sendungen-aus-einem-Nicht-EU-Staat/Zoll-und-Steuern/Internetbestellungen/internetbestellungen_node.html) – Einfuhrabgaben und 2-€-Abfertigungspauschale bei Sendungen aus Nicht-EU-Staaten nach Deutschland
- [BAZG: Verzollungskosten und Paketverfolgung](https://www.bazg.admin.ch/de/verzollungskosten-und-paketverfolgung) – Zuständigkeit des Beförderers für Verzollungskosten und Sendungsverfolgung in der Schweiz
- [BAZG: Empfangen von Briefen und Paketen](https://www.bazg.admin.ch/de/empfangen-von-briefen-und-paketen) – Abgabenfreigrenze von 5 CHF Steuerbetrag und 100 CHF für private Geschenksendungen in die Schweiz
- [Die Schweizerische Post: Importverzollung](https://www.post.ch/de/empfangen/importverzollung) – Einzug von Einfuhrabgaben und Verzollung durch die Schweizerische Post
- [Universal Parcel Scraper: La Poste](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/la-poste) – Nummernformate, Prüfzeichen, Statuszuordnung, Datenschutz und Abfrageintervall in Peek
- [Universal Parcel Scraper: geprüfter Sendungsverlauf](https://github.com/plhery/universal-parcel-scraper/blob/main/testing/auditedTrackingHistory.json) – im Live-Betrieb bestätigte französische Statusmeldungen von La Poste
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Übergabe internationaler FR-Postnummern an DHL, Österreichische Post und Schweizerische Post
:::
