---
title: GEODIS Sendungsverfolgung: 1G-Nummer, Status & Kontakt
description: GEODIS-Sendung verfolgen: wo die 1G-Sendungsnummer steht, was die französischen Statusmeldungen heißen, wie du die Zustellung änderst und Kontakt.
slug: geodis-sendungsverfolgung
published: 2026-11-17
updated: 2026-11-17
---

GEODIS ist ein französischer Transport- und Logistikkonzern, der seit 2008 zu 100 % zur französischen Staatsbahn SNCF gehört. Sein Paket- und Palettennetz (*Distribution & Express*) liefert Bestellungen in Frankreich und im europäischen Straßenverkehr aus. Eine solche Sendung verfolgst du ohne Konto in GEODIS’ Empfängerbereich (*Espace destinataire*) unter [espace-client.geodis.com/services/destinataires/](https://espace-client.geodis.com/services/destinataires/) mit der 12-stelligen `1G`-Sendungsnummer – oder direkt im Suchfeld oben auf dieser Seite. Das Portal *Mon espace GEODIS* ist dagegen für Versender mit Kundenkonto gedacht.

## GEODIS Sendungsnummer: Aufbau, Fundort und echte Benachrichtigungen

Laut GEODIS erhältst du die Sendungsnummer vom Absender oder Onlineshop, meist in der Bestellbestätigung oder der Versand-E-Mail. Warst du beim Zustellversuch nicht zu Hause, notiert der Fahrer die `1G`-Nummer auch auf der Benachrichtigungskarte im Briefkasten. Keine Nummer gefunden? Frag beim Shop nach oder lies unter [Sendungsnummer finden](guide:find-tracking-number) weiter.

Hat der Absender Benachrichtigungen aktiviert, schickt GEODIS dir außerdem eine SMS oder E-Mail am Vortag oder zwei Tage vor der Zustellung sowie eine zweite Nachricht am Zustellmorgen. Echte Nachrichten von GEODIS enthalten einen Kurzlink der Form `edesti.com/1G…` (in GEODIS’ FAQ etwa `edesti.com/1G123GEODIS0`), der in den *Espace destinataire* führt. Bist du bei einer SMS unsicher, gib deine `1G`-Nummer direkt auf `espace-client.geodis.com/services/destinataires/` ein; woran du gefälschte Nachrichten erkennst, erklärt [Fake-Paket-SMS erkennen](guide:delivery-scam-texts).

Der Empfängerbereich akzeptiert zwei Kennungen (hier mit frei erfundenen Beispielen):

| Format (erfunden) | Bedeutung |
| --- | --- |
| `1GGDXF0B27F1` | **1G-Sendungsnummer** (*Numéro de suivi 1G*): 12 Großbuchstaben und Ziffern, beginnend mit `1G`. Reicht allein zur Verfolgung in GEODIS’ Portal und in Peek. |
| `19847263510492` | **14-stellige Beleg- oder Sendungsnummer** (*n° de récépissé* / *numéro d'envoi*): 14 Ziffern. Funktioniert im *Espace destinataire* nur zusammen mit der Empfänger-Postleitzahl; Peek ordnet sie nicht GEODIS zu. |

Die allgemeine [Sendungsverfolgung auf geodis.com](https://geodis.com/de-de/ihre-sendungsverfolgung) sucht Luftfracht-, Seefracht- und Last-Mile-Sendungen und verweist für Straßentransporte auf das eigene Tracking-Werkzeug. Eine 8-stellige Zahl ist in Peek keine GEODIS-Nummer, sondern kann etwa zu [Heppner](carrier:heppner) oder [Mondial Relay](carrier:mondial-relay) gehören – prüf in der Versand-E-Mail, welchen Dienst der Shop nennt, oder vergleiche die Muster unter [Sendungsnummer-Formate](guide:tracking-number-formats). Sendungen, die älter als 12 Monate sind, findet GEODIS’ Empfängerbereich laut seiner Fehlermeldung nicht mehr.

## GEODIS Statusmeldungen: was die Schritte bedeuten

Der *Espace destinataire* öffnet auf Französisch und lässt sich auf Englisch, Spanisch oder Italienisch umstellen – eine deutsche Fassung gibt es dort nicht. Er zeigt eine vierstufige Zeitleiste, in der der aktuelle Schritt hervorgehoben ist, sowie – sobald datierte Scans vorliegen – den Sendungsverlauf (*Historique d'acheminement*) mit dem jeweiligen GEODIS-Standort. Manche Sendungen zeigen nur die Zeitleiste.

Da Peek die französische Fassung ausliest, erscheinen die Schritte im Originalwortlaut (links), daneben steht die englische Bezeichnung der vier Hauptschritte auf GEODIS’ Seite:

| GEODIS (Französisch) | Auf Englisch bei GEODIS | Bedeutung für dich |
| --- | --- | --- |
| `En préparation chez l'expéditeur` | `In preparation at the shipper's` | Der Absender hat die Sendung angemeldet, GEODIS hat sie noch nicht übernommen. |
| `En attente de prise en charge` | – | Der Händler hat die Ware noch nicht physisch an GEODIS übergeben. |
| `Envoi pris en charge` | – | GEODIS hat die Sendung beim Absender abgeholt. |
| `En cours d’acheminement` | `In transit` | Unterwegs zwischen den GEODIS-Depots. |
| `Mise en livraison` | `Delivery in progress` | Zur Zustellung eingeplant: Die Sendung geht auf die Tour. |
| `En cours de livraison` | – | Auf dem Fahrzeug des Fahrers in der Zustelltour. |
| `Disponible pour retrait en agence` | – | Liegt in einer GEODIS-Niederlassung für dich zur Abholung bereit. |
| `Expédition livrée` | `Delivered shipment` | Zugestellt; auf der Seite steht das Datum hinter „Expédition livrée le :“. |
| `Livraison effectuée` | – | Zugestellt (in Peek als „Zugestellt“ angezeigt). |

Ein paar Hinweise zu den Meldungen:

- `Mise en livraison` (`Delivery in progress`) heißt noch nicht zugestellt, sondern in Zustellung.
- Bleibt der Status tagelang auf `En préparation chez l'expéditeur` oder `En attente de prise en charge`, hat der Shop die Ware noch nicht übergeben – wende dich an den Händler, nicht an GEODIS.
- Am Zustelltag kann die Seite eine Live-Karte des Fahrers einblenden, mit Hinweisen wie *„Le conducteur est en route“* (`The driver is on the way`), *„Vous êtes le prochain“* (`You are the next`) und *„Le conducteur arrive !“* (`The driver is coming !`).
- Steht dort *„Vous n'avez pas encore planifié votre RDV“* (`You have not yet scheduled your appointment`), wartet GEODIS darauf, dass du im Empfängerbereich einen Liefertermin wählst.
- Bei einer Abholung zählt ein Hinweis die verbleibenden Tage herunter (*„You have … day(s) left to pick up your shipment“*); ein weiterer warnt vor der Rücksendung an den Absender. Mehr zu den Phasen unter [Sendungsstatus erklärt](guide:tracking-statuses).

## Zustellung bei GEODIS: Termin ändern, Abholung in der Niederlassung und Optionen

Der Empfängerbereich zeigt nur die Optionen an, die für deine Sendung freigeschaltet sind. Im Standardtarif kannst du Datum oder Zustellart erst nach dem ersten erfolglosen Zustellversuch ändern; hat der Absender die Option *On Demand* gebucht, geht das schon ab der ersten SMS- oder E-Mail-Benachrichtigung. Sobald du eine neue Auswahl bestätigst, lässt sich die ursprünglich für denselben Tag geplante Zustellung nicht wiederherstellen. Möglich sind je nach Sendung:

- **Neues Lieferdatum an der Haustür**, mit Zeitfenster, sofern verfügbar. Am Morgen des Zustelltags schickt GEODIS bei aktivierten Benachrichtigungen ein Zeitfenster von 2 Stunden im Express-Service oder 3 Stunden im Standard-Service; bei Privatkunden sollen Fahrer laut GEODIS in der Regel drei Stopps vor deiner Adresse anrufen.
- **Abholung in der Niederlassung** (*retrait en agence*) in einer der 110 lokalen Agenturen des französischen Netzes, falls für dein Zielgebiet angeboten. Alle Agenturen haben laut GEODIS einen Abholschalter, dessen Öffnungszeiten die Seite nennt. Bring einen Ausweis und die Bestätigungs-E-Mail oder -SMS mit. Holt jemand anders das Paket ab, braucht diese Person GEODIS’ ausgefülltes Vollmachtsformular, ihren eigenen Originalausweis, deinen Originalausweis und die Nachricht; bei Firmensendungen ohne Ansprechpartner verlangt GEODIS einen Handelsregisterauszug (*Kbis*), der jünger als 3 Monate ist, und eine Vollmacht der gesetzlichen Vertretung.
- **Nachbar oder Hausmeister** (*voisin / gardien*), den du namentlich benennst und der bei Abwesenheit unterschreibt.
- **Sicherer Ablageort** (*lieu sûr*), etwa Treppenabsatz, Garage oder Garten: Der Fahrer legt das Paket dort ab und macht ein Foto.
- **Pickme-Abholpunkt** bei einem registrierten Nachbarn (*Voisin Pickme*): Das Paket wird dort am nächsten Werktag abgegeben, und der Punkt meldet sich, sobald es bereitliegt. Zum Start im Dezember 2024 nannte GEODIS 14 Tage Abholfrist mit Geheimcode.

Ein engeres Zeitfenster oder eine Lieferung in die Etage ist nur möglich, wenn der Absender diese Leistung gebucht hat; Paletten stellt GEODIS standardmäßig am Tor oder am Gebäudeeingang ab. Nach einem verpassten Zustellversuch bewahrt die französische Niederlassung die Sendung laut GEODIS-FAQ 8 Kalendertage lang auf und schickt sie ohne Rückmeldung an den Absender zurück (siehe [Zustellung verpasst](guide:missed-delivery)). Wie viele Zustellversuche unternommen werden, veröffentlicht GEODIS nicht. Stimmen Adresse oder Telefonnummer nicht, reichst du die Korrektur über das Kontaktformular auf geodis.com ein oder bittest den Shop, sie weiterzugeben.

## GEODIS-Sendung verspätet, beschädigt oder nicht angekommen

Den Beförderungsvertrag mit GEODIS hat der Absender geschlossen – bei Problemen ist der Onlineshop deshalb meist dein erster Ansprechpartner.

**Verspätet oder ohne Bewegung.** Für sein Paket- und Palettennetz nennt GEODIS innerhalb Frankreichs 24 bis 48 Stunden im Standard-Service und den nächsten Tag vor 12:00 Uhr im Express-Service; europaweit gibt GEODIS 1 bis 2 Tage im Express- und 3 bis 5 Tage im Standard-Service an. Laut den Allgemeinen Geschäftsbedingungen (*Conditions générales de vente*, Stand Mai 2025) sind Lieferfristen jedoch nicht garantiert. Tut sich tagelang nichts, lies [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating) und kontaktiere den Shop.

**Nummer funktioniert nicht.** Prüf zuerst das Format (`1G` plus 10 Zeichen) und wende dich sonst an den Absender oder Händler.

**Warten auf Anweisungen.** Kann eine Sendung nicht zugestellt werden, holt GEODIS laut seinen Bedingungen Anweisungen beim Auftraggeber ein. Melde dich direkt beim Shop.

**Beschädigt oder unvollständig.** Prüfe die Ware im Beisein des Fahrers und vermerke konkrete, begründete Vorbehalte (*réserves précises et motivées*) auf dem Papier- oder digitalen Lieferschein. Der Empfängerbereich weist darauf hin, dass das Transportrecht eine schriftliche Meldung von Schäden oder Fehlmengen innerhalb von drei Tagen nach der Zustellung erlaubt; ohne ordnungsgemäße Vorbehalte schließen GEODIS’ Bedingungen Ansprüche gegen den Frachtführer aus. Mehr dazu unter [Paket beschädigt](guide:damaged-parcel) – und melde den Schaden parallel dem Verkäufer.

**Beschwerde zur Zustellung.** GEODIS’ französische Bedingungen bitten Verbraucher, sich zuerst an den Verkäufer zu wenden, danach an den Kundendienst des ausführenden GEODIS-Betriebs oder per E-Mail an `dpm@geodis.com`. Kommt innerhalb von 2 Monaten keine Antwort oder bleibt der Streit offen, nennt GEODIS die französische Verbraucherschlichtungsstelle *Médiation de la consommation AME Conso* (197 Boulevard Saint Germain, 75007 Paris).

## GEODIS Kontakt und Kundenservice

GEODIS veröffentlicht keine Telefonnummer für Paketempfänger. Diese Wege stehen bereit:

- **Empfängerbereich:** [espace-client.geodis.com/services/destinataires/](https://espace-client.geodis.com/services/destinataires/) mit integriertem Hilfemenü (*Aide* / *help*).
- **Kontaktformular für Empfänger:** Auf der [englischen Kontaktseite](https://geodis.com/contact-us) unter *Customer support* „a recipient“ wählen oder auf der [französischen Kontaktseite](https://geodis.com/fr/nous-contacter) unter *Assistance clientèle* „un destinataire“ wählen und die Sendungsnummer angeben (das Formular auf `geodis.com/de-de/kontaktieren-sie-uns` richtet sich nur an Geschäftskunden für Angebote und Expertenanfragen). Laut französischer Empfänger-FAQ meldet sich der nächstgelegene der 110 Kundenservices innerhalb von 12 Arbeitsstunden zurück.
- **E-Mail für Verbraucherbeschwerden:** `dpm@geodis.com` gemäß den Allgemeinen Geschäftsbedingungen.
- **Niederlassungen in Frankreich:** Namen und Adressen stehen im [Agenturverzeichnis](https://geodis.com/fr/liste-des-agences?territory=FR), darunter auch Standorte des GEODIS-Netzes France Express.

## GEODIS-Pakete mit Peek verfolgen

Fügst du eine `1G`-Nummer bei [Peek](/) ein, erkennt Peek sofort GEODIS – kein anderer Paketdienst nutzt dieses Format. Auch Links zu `espace-client.geodis.com/services/destinataires/` (mit `noSuivi=`, `trackingNumber=` oder `#/.../suivi/...`), Links zu `espace-client.france-express.com/#/.../suivi/...` sowie der `edesti.com/1G…`-Link oder der gesamte SMS-Text von GEODIS funktionieren, solange darin eine `1G`-Nummer steht. Die 14-stellige Belegnummer verfolgt Peek nicht als GEODIS; nutze dafür die `1G`-Nummer.

Peek ruft GEODIS’ französische Sendungsverfolgung ab, zeigt die französischen Meldungen (wobei `Livraison effectuée` als „Zugestellt“ erscheint) und setzt den jeweiligen GEODIS-Standort auf die Karte. Uhrzeiten liest Peek als Pariser Zeit und rechnet sie in die Zeitzone deines Geräts um; dazu zeigt es das voraussichtliche Lieferdatum, den Absender, die Versandart und das Gewicht. Meldet der neueste Scan einen fehlgeschlagenen Zustellversuch, ein Problem, eine Rücksendung oder eine Abholung, hat er Vorrang vor der Zeitleiste. Nennt GEODIS nur Schritte ohne datierte Scans, versieht Peek den Status mit dem Zeitpunkt des ersten Abrufs. Paketanzahl, Empfängername und -adresse, Zustellhinweise und Ablieferbelege lässt Peek weg; Sendungen über 12 Monate gelten als nicht gefunden.

Peek schaut bis zu alle 10 Minuten nach, solange jemand auf das Paket wartet, sonst stündlich – ebenso nach zwei Tagen ohne neuen Scan oder nach 12 Stunden auf „Etikett erstellt“ bzw. „Beim Paketdienst eingegangen“. Am Zustelltag prüft Peek ab 6:00 Uhr Ortszeit bis zu alle 2 Minuten, sobald das Paket in Zustellung ist und solange GEODIS’ eigener Tracker antwortet; antworten nur Tracking-Dienste, prüft Peek stündlich, nach einem Tag ohne neuen Scan alle 3 Stunden und am Tag der Zustelltour bis zu alle 15 Minuten. Für Benachrichtigungen wählst du „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“. Liefertermine ändern kann Peek nicht – nutze dafür den *Espace destinataire*.

## Häufige Fragen zur GEODIS Sendungsverfolgung

### Was ist „Mon espace GEODIS“?

Das ist das Kundenportal unter [espace-client.geodis.com/index](https://espace-client.geodis.com/index/) für versendende Kunden von GEODIS, die sich dort mit Kennung und Passwort anmelden, um Abholungen zu verwalten und Sendungen zu verfolgen. Wartest du auf eine Lieferung, verweist dich die Seite direkt auf den *Espace destinataire*, wo deine `1G`-Nummer ohne Anmeldung genügt.

### Was haben GEODIS, Calberson und France Express miteinander zu tun?

Die Wurzeln des Konzerns liegen in dem Unternehmen, das Émile Calberson 1904 gründete; Ende 1995 nahm die Gruppe den Namen GEODIS an, seit 2008 ist sie eine 100-prozentige Tochter der SNCF, und 2015 führte sie ihre Marken unter dem Namen GEODIS zusammen. France Express ist eines der Expressnetze von GEODIS mit einem eigenen [Empfängerbereich](https://espace-client.france-express.com/services/destinataire).

### Wann und zu welchen Uhrzeiten liefert GEODIS?

Standardzustellungen laufen laut GEODIS-FAQ in der Regel montags bis freitags zwischen 8:00 und 18:00 Uhr. Samstags wird zwischen 8:00 und 13:00 Uhr zugestellt, sofern der Absender diese Option gewählt hat; an Sonn- und Feiertagen ruht die Zustellung.

### Wie lange dauert eine Lieferung mit GEODIS?

Innerhalb Frankreichs nennt GEODIS unverbindlich 24 bis 48 Stunden im Standard-Service und den nächsten Tag vor 12:00 Uhr im Express-Service; für Europa gibt GEODIS 1 bis 2 Tage im Express- und 3 bis 5 Tage im Standard-Service an.

:::sources
- [GEODIS: Espace destinataire](https://espace-client.geodis.com/services/destinataires/) – Eingabefelder, Sprachen, Zeitleiste und Live-Meldungen, Zustelloptionen, Vollmacht, Ablageort, Drei-Tage-Hinweis, 12 Monate
- [GEODIS: Mon espace GEODIS](https://espace-client.geodis.com/index/) – Kundenportal für Versender und Verweis auf den Empfängerbereich
- [GEODIS: Ihre Sendungsverfolgung](https://geodis.com/de-de/ihre-sendungsverfolgung) – wo die Nummer steht, Luft-/See-/Last-Mile-Suche und Hinweis zum Straßentransport
- [GEODIS: Kontaktieren Sie uns (DE)](https://geodis.com/de-de/kontaktieren-sie-uns) – deutsches B2B-Formular für Angebote und Experten
- [GEODIS: Contact us (EN)](https://geodis.com/contact-us) – Empfänger-Auswahl im Supportformular, Abholpunkte, Paletten und Etagenlieferung
- [GEODIS: Nous contacter (FR)](https://geodis.com/fr/nous-contacter) – Empfängerformular, Adresskorrektur, Ausweispflicht
- [GEODIS: FAQ – Zugang zum Espace Client](https://geodis.com/fr/faq?question=comment-acceder-a-mon-espace-client) – Login, Abholungen und Sendungsverfolgung für Versender
- [GEODIS: FAQ – Zustellzeiten und Wochenende](https://geodis.com/fr/faq?question=peut-on-etre-livre-dehors-heures-bureau-jour-ferie-dimanche) – Mo–Fr 8–18 Uhr, Sa 8–13 Uhr als Option, keine Sonn- und Feiertage
- [GEODIS: FAQ – Sendungsnummer finden](https://geodis.com/fr/faq?question=qu-est-ce-qu-un-numero-d-envoi-et-ou-le-trouver-lorsqu-on-est-destinataire) – 1G-Nummer, Benachrichtigungskarte, edesti.com-Link, 14-stellige Belegnummer
- [GEODIS: FAQ – En attente de prise en charge](https://geodis.com/fr/faq?question=que-signifie-en-attente-de-prise-en-charge-sur-le-suivi-de-ma-livraison-50000) – Ware noch beim Händler
- [GEODIS: FAQ – Lieferzeitfenster](https://geodis.com/fr/faq?question=quand-serai-je-informe-e-du-creneau-horaire-de-ma-livraison) – Vorankündigung, 2-h-/3-h-Fenster, Anruf drei Stopps vorher
- [GEODIS: FAQ – Lieferdatum ändern](https://geodis.com/fr/faq?question=comment-modifier-la-date-de-livraison-prevue) – Standard nach erstem Versuch, On Demand sofort, 110 Agenturen
- [GEODIS: FAQ – Aufbewahrung in der Agentur](https://geodis.com/fr/faq?question=pendant-combien-de-temps-mon-envoi-est-il-conserve-en-agence) – 8 Kalendertage Lagerfrist
- [GEODIS: FAQ – Kundenservice für Empfänger](https://geodis.com/fr/faq?question=comment-contacter-le-service-relation-client-destinataire) – kostenloses Formular, Rückmeldung binnen 12 Arbeitsstunden
- [GEODIS: Votre espace de travail](https://geodis.com/fr/votre-espace-de-travail) – Espace Client für Distribution & Express
- [GEODIS: Transport de colis et de palettes](https://geodis.com/fr/transport-de-marchandises/transport-de-colis-et-de-palettes) – Laufzeiten Frankreich und Europa, Samstagsoption
- [GEODIS: Conditions générales de vente, Mai 2025 (PDF)](https://geodis.com/sites/default/files/widen-migration/2glqzqlnw7/CGV_FR-052025.pdf) – unverbindliche Fristen, Anweisungen, Vorbehalte, dpm@geodis.com, Schlichtung AME
- [GEODIS: Partnerschaft mit Pickme](https://geodis.com/fr/newsroom/geodis-sappuie-sur-pickme-pour-elargir-son-offre-de-livraison-en-point-de-retrait-dans) – Nachbarschaftspunkte, 14 Tage, Geheimcode
- [GEODIS: Notre histoire](https://geodis.com/fr/notre-histoire) – Calberson 1904, Name GEODIS 1995, SNCF 2008, Einheitsmarke 2015
- [GEODIS: Liste des agences](https://geodis.com/fr/liste-des-agences?territory=FR) – Agenturen und France-Express-Standorte
- [France Express: Startseite](https://france-express.com/) – Empfängerbereich von France Express
- [France Express: Espace destinataire](https://espace-client.france-express.com/services/destinataire) – Sendungsverfolgung für France-Express-Empfänger
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – was Peek ausliest und anzeigt
:::
