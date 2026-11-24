---
title: Liccardi Express Courier Sendungsverfolgung: LDV & Hilfe
description: Verfolge ein Paket von Liccardi Express Courier (F.lli Liccardi) in Italien: 660- und LDV-Codes, Filial- und Locker-Scans, Giacenza und Kontakt.
slug: liccardi-sendungsverfolgung
published: 2026-11-24
updated: 2026-11-24
---

**Liccardi Express Courier** (*F.lli Liccardi S.r.l.*) ist ein italienischer nationaler Expresskurier mit Sitz in Casandrino bei Neapel ([liccarditrasporti.com](https://www.liccarditrasporti.com/)). Das 1992 in seiner heutigen Form gestartete Unternehmen ging aus einem dreißig Jahre zuvor von Giovanni Liccardi gegründeten Familienbetrieb hervor und wuchs vom Bankdokumententransport in den 1990er-Jahren zur landesweiten B2C- und E-Commerce-Paketverteilung mit über 5,9 Millionen Sendungen pro Jahr über mehr als 20 italienische Filialen und Hubs, darunter Neapel, Rom, Mailand, Bologna, Turin, Florenz, Verona, Bari und Salerno. Du kannst eine Sendung auf der Website von Liccardi prüfen oder deinen 13-stelligen `660`-Etikettencode bzw. die 7- bis 8-stellige *lettera di vettura* (LDV) oben einfügen.

## Liccardi-Express-Courier-Sendungsnummern (`660`-Etikett & LDV)

Liccardi versieht jede Sendung mit zwei numerischen Referenzen — einem 13-stelligen Paket-Barcode mit Anfang `66` (meist `660`) und einer kürzeren 7- oder 8-stelligen Sendungsnummer (*lettera di vettura* / LDV):

| Format (erfunden) | Worum es sich handelt |
| --- | --- |
| `6604829173605` | 13-stelliger Paket-Barcode von Liccardi (`660` + 8-stellige Sendungsnummer + 2 Prüf-/Packstückziffern). |
| `48291736` | 7- oder 8-stellige Sendungsnummer von Liccardi (*LDV* / *lettera di vettura*). |

Schau in die Versand-E-Mail deines Händlers oder in die Lieferbenachrichtigung von Liccardi nach einem der beiden Codes; beide verweisen im Liccardi-System auf dieselbe Sendung. Siehe [Sendungsnummern-Formate](guide:tracking-number-formats).

## Was die Sendungsstatus von Liccardi Express Courier bedeuten

Der Tracker von Liccardi gibt numerische Ergebniscodes (*codici esito*) zusammen mit italienischen Filialbeschreibungen aus. Bei Mehrpaketsendungen (*colli*) erfasst Liccardi Ereignisse je Packstück; Peek hält die Sendung aktiv, bis jedes Packstück zugestellt ist:

| Liccardi meldet (Code) | Was das für dich bedeutet | Peek zeigt |
| --- | --- | --- |
| `DATI SPEDIZIONE TRASMESSI A LICCARDI` (`50`), `SERVIZIO PROGRAMMATO` (`58`), `RITIRO ASSEGNATO AD AUTISTA` (`36`), `IN RITIRO` (`92`) | Der Absender hat die Sendungsdaten übermittelt oder eine Fahrerabholung ist vor dem Eingang eingeplant bzw. im Gange. | „Etikett erstellt“ |
| `SPEDIZIONE RITIRATA` (`37`), `COLLO PRESO IN CARICO` (`3`), `SPEDIZIONE PRESA IN CARICO` (`4`) | Paket beim Absender abgeholt und im Liccardi-Netz übernommen. | „Beim Paketdienst eingegangen“ |
| `ARRIVATO IN FILIALE` (`47`), `IN TRANSITO` (`48`), `SPEDIZIONE PARTITA` (`91`), `ARRIVO SU FILIALE DI DESTINO` (`72`), `IN GESTIONE` (`49`), `GIACENZA SVINCOLATA` (`70`), `IN PRENOTAZIONE` (`33`) | Unterwegs zwischen Hubs, in der Zielfiliale eingetroffen, in Terminvereinbarung oder aus der Lagerhaltung freigegeben. | „Unterwegs“ |
| `CARICO VETTURA` (`51`), `CARICO VETTURA CONFERMATO` (`95`), `IN CONSEGNA` (`52`) | In den lokalen Lieferwagen geladen und heute auf Zustelltour. | „In Zustellung“ |
| `CONSEGNATA AL PUDO` (`6`), `CHECK-IN AL PUDO` (`80`), `SPEDIZIONE DEPOSITATA NEL LOCKER` (`11`), `FERMO DEPOSITO` (`32`) | Abholbereit in einem PUDO-Shop, einem Paketfach oder einer lokalen Liccardi-Filiale (*fermo deposito*). | „Abholbereit“ |
| `ASSENTE` (`28`), `INDIRIZZO ERRATO` (`40`), `CONSEGNA NON ESEGUITA` (`43`), `MANCA CONTRASSEGNO` (`44`), `INDIRIZZO NON TROVATO` (`45`), `CHIUSO` (`46`), `GIRO DI CONSEGNA NON ULTIMATO` (`67`) | Zustellung nicht abgeschlossen (Empfänger abwesend, Adressfehler, Nachnahme nicht bereit, geschlossen oder Tour nicht beendet). | „Zustellversuch“ |
| `IN GIACENZA` (`35`), `COLLO DANNEGGIATO` (`30`), `MERCE RESPINTA` (`42`), `SPEDIZIONE SMARRITA` (`54`), `COLLO DISGUIDATO` (`68`), `ORDINE SOSPESO: IN ATTESA DI ISTRUZIONI MITTENTE` (`76`), `DISCONOSCIMENTO FIRMA` (`103`), `CONTATTARE ASSISTENZA` (`107`) | Paket im Lager in Wartestellung auf Freigabe durch den Absender (*giacenza*), beschädigt, verweigert, fehlgeleitet, verloren oder klärungsbedürftig. | „Problem gemeldet“ |
| `RIENTRO AL MITTENTE` (`31`), `IN CONSEGNA AL MITTENTE` (`53`), `RESO A MITTENTE` (`8`) | Auf dem Rückweg zum Absender oder bereits zurückgegeben. | „Zurück an Absender“ |
| `SPEDIZIONE CONSEGNATA` (`1`), `SPEDIZIONE CONSEGNATA A COMMITTENTE` (`5`), `SPEDIZIONE RITIRATA DA LOCKER DAL DESTINATARIO` (`12`) | An den Empfänger zugestellt oder aus dem Paketfach abgeholt. | „Zugestellt“ |

Beachte, dass die eigene Fortschrittsanzeige von Liccardi Einlegungen in einen Locker (`11`) bereits als zugestellt wertet, während Peek PUDO-, Locker- und *fermo deposito*-Scans auf **„Abholbereit“** belässt, bis der Empfänger das Paket tatsächlich abholt (`12`).

## Lieferzeiten, Services und italienisches Filialnetz

Laut [Liccardi Services](https://www.liccarditrasporti.com/servizi/), [Über uns](https://www.liccarditrasporti.com/azienda/) und [Filialen](https://www.liccarditrasporti.com/filiali/):

- **Laufzeiten:** Liccardi holt Bestellungen innerhalb von 48 Stunden bei Händlern ab, wiegt, vermisst und fotografiert jedes Paket in seinen Sortierzentren und nennt eine landesweite Zustellung in **24–48 Stunden** (statistisch innerhalb von **72 Stunden** im gesamten nationalen Netz; abgelegene Gebiete — *località disagiate* — und Spitzenzeiten können länger dauern).
- **Zustelloptionen:** Standard-B2C-Express, Lieferung bis in die Etage (*consegne al piano*), Terminlieferungen (*consegne tassative*), Zustellung am selben Tag, Nachnahme (*contrassegno*), Filialabholung (*fermo deposito*), Austausch-/Rückholaufträge und optionale Zusatzversicherung.
- **Filialnetz:** Über 20 operative Hubs und Depots in ganz Italien, darunter Zentralhub Casandrino/Neapel (`NA1`), Rom (`RM1`, `RM3`), Mailand (`MI1`), Bologna (`BO1`), Turin (`TO2`), Florenz (`FI1`), Verona (`VR1`), Bari (`BA1`), Salerno (`SA1`), Avellino (`AV1`), Frosinone (`FR1`), Latina (`LT1`), Modena (`MO1`), Parma (`PR1`), Foggia (`FG1`), Lecce (`LE2`), Matera (`MT1`), Potenza (`PZ1`), Cosenza (`CS1`), Catanzaro (`CZ1`) und Reggio Calabria (`RC1`). Übernimmt ein anderer Kurier ein Paket, kann der Liccardi-Tracker dessen Frachtbrief (LDV) anzeigen.

## Verpasste Zustellung, *giacenza*-Lagerung und Reklamationen

- **`IN GIACENZA` (`35`) oder fehlgeschlagene Zustellung (`28`, `40`, `45`):** Nach einem erfolglosen Versuch oder Adressproblem stellt Liccardi die Sendung in *giacenza* (Lagerung) und wartet auf Freigabeanweisungen (*svincolo giacenza*) des **Absenders**. Kontaktiere zuerst deinen Onlineshop, damit dieser die Freigabe erteilt, oder nutze das Empfängerportal [Assistenza / Reclami Destinatari](https://www.liccarditrasporti.com/assistenza-reclami-destinatari/), um Zustellhinweise zu aktualisieren, einen Adressfehler zu melden oder Filialabholung (*ritiro in filiale*) zu beantragen.
- **Einspruch gegen ein Ergebnis (`Contestazione Esito`) oder fehlendes Paket:** Der Empfänger-Reklamationsbereich von Liccardi ermöglicht Meldungen zu falschen Status, „Empfänger abwesend“ trotz Anwesenheit oder als zugestellt markierten, aber nicht erhaltenen Paketen und stellt die Formulare *Modulo reclamo*, *Modulo conciliazione* und *Formulario CP* zum Download bereit. Informiere immer auch den Verkäufer. Siehe [zugestellt, aber nicht erhalten](guide:delivered-not-received), [beschädigtes Paket](guide:damaged-parcel), [verlorenes Paket](guide:lost-parcel), [zurück an Absender](guide:returned-to-sender) und [warum die Sendungsverfolgung nicht aktualisiert](guide:tracking-not-updating).

## Liccardi Express Courier Kundenservice und Kontakt

Offizielle Kontaktdaten auf [Liccardi Contatti](https://www.liccarditrasporti.com/contatti/) und [Assistenza / Reclami Destinatari](https://www.liccarditrasporti.com/assistenza-reclami-destinatari/):

- **Kostenlose Hotline (*Numero Verde* in Italien):** `800 029 764` (`800029764`).
- **Telefon:** `+39 081 5053642` (Fax `081 5053071`).
- **Kundenservice-E-Mail:** `assistenzaclienti@liccarditrasporti.com` (allgemeine Anfragen: `info@liccarditrasporti.com`; PEC: `flliliccardi@legalmail.it`).
- **Empfänger-Support- und Reklamationsportal:** [liccarditrasporti.com/assistenza-reclami-destinatari/](https://www.liccarditrasporti.com/assistenza-reclami-destinatari/).
- **Hauptsitz & Zentralhub:** *F.lli Liccardi S.r.l.* (P.IVA `06588280633`), Via Paolo Borsellino 133 (Rechtssitz 123), 80025 Casandrino (NA), Italien.

> Vorsicht vor gefälschten SMS, die eine kleine Onlinezahlung verlangen, um eine italienische Kurierzustellung neu zu planen. Prüfe jede Liefermeldung immer direkt beim Händler oder über die offizielle Website und die Kundenservice-Kanäle von Liccardi. Siehe [Betrugs-SMS zu Paketlieferungen](guide:delivery-scam-texts).

## Ein Liccardi-Express-Courier-Paket mit Peek verfolgen

Gibst du einen 13-stelligen `660`-Barcode (`6604829173605`, erfunden) ein, fragt [Peek](/) überall zuerst bei Liccardi Express Courier an — außer in Russland, wo zuerst Ozon geprüft wird — und zeigt „Liccardi Express Courier hat dieses Paket.“, sobald der Liccardi-Tracker es bestätigt. Eingefügte Links im Format `my.liccarditrasporti.com/segui?ldv=` erkennt Peek ebenfalls direkt als Liccardi. Eine 7- oder 8-stellige LDV (`48291736`, erfunden) verweist allein auf keinen Paketdienst: Füge den Link ein oder tippe beim Hinzufügen auf „Ändern“ bzw. auf der Paketseite auf „Paketdienst ändern“ und wähle **Liccardi Express Courier** (auch die Suche nach „Liccardi Trasporti“, „Liccardi Express“ oder „F.lli Liccardi“ findet den Dienst).

Peek liest den Liccardi-Tracker aus und zeigt die Phase („Etikett erstellt“, „Beim Paketdienst eingegangen“, „Unterwegs“, „In Zustellung“, „Abholbereit“, „Zustellversuch“, „Problem gemeldet“, „Zurück an Absender“, „Zugestellt“), den italienischen Text von Liccardi und die Filiale an. Die `codEsito`-Codes legen die Phase fest, werden aber in keiner Peek-App angezeigt. Peek datiert die Scans auf italienische Zeit und stellt sie auf der Uhr deines Geräts dar. Außerdem zeigt Peek den voraussichtlichen Liefertag, sofern Liccardi einen nennt (ohne Zeitfenster), die Zustelluhrzeit nach erfolgter Lieferung sowie den Übersichtsstatus vor dem ersten Paketscan. Gibt Liccardi den Frachtbrief eines anderen Kuriers an und erkennt Peek diesen, prüft Peek dort die Sendung: Die Ansicht zeigt dann „Zustellung durch“ samt Kuriername und beiden Nummern. Bei Sendungen mit mehreren Packstücken führt Peek doppelte Filialscans innerhalb einer Minute zusammen und lässt die Sendung offen, bis jedes Packstück zugestellt ist. Peek zeigt weder Ort noch Postleitzahl des Empfängers, keine Fahrernamen und keine Zustellunterschriften an.

Solange du das Paket in Peek geöffnet hast oder am Zustelltag Benachrichtigungen aktiviert sind, fragt Peek bis zu alle 10 Minuten nach; an dem Tag, an dem Liccardi `CARICO VETTURA` (`51`), `CARICO VETTURA CONFERMATO` (`95`) oder `IN CONSEGNA` (`52`) meldet, ab 6 Uhr italienischer Zeit alle 2 Minuten, solange der eigene Tracker von Liccardi antwortet. Andernfalls prüft Peek stündlich. Nach 12 Stunden mit lediglich „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ oder nach zwei Tagen ohne Neuigkeiten fragt Peek unabhängig von deiner Nutzung stündlich nach, und nach sieben Tagen auf „Etikett erstellt“ alle 6 Stunden. Antwortet der Tracker von Liccardi nicht und Peek verfolgt das Paket allein über Tracking-Dienste, prüft Peek stündlich, nach einem ruhigen Tag alle 3 Stunden und am Zustelltag bis zu alle 15 Minuten, solange du die Sendung geöffnet hast oder Benachrichtigungen aktiviert sind.

Ist der neueste Scan vier Tage alt und das Paket steht noch auf „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“, markiert Peek es mit dem Tag dieses Scans, etwa „Keine Aktualisierung seit Mo., 12. Okt.“. Auf der Paketseite steht dann „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei Liccardi Express Courier nach.“, samt Link „Hol dir Hilfe bei Liccardi Express Courier“ zur Empfängerhilfe- und Reklamationsseite von Liccardi. Nach drei Wochen ohne neuen Scan heißt es „Seit über drei Wochen nichts Neues. Ist das Paket noch nicht angekommen, frag beim Verkäufer nach. Peek fragt weiter bei Liccardi Express Courier nach.“

Bei den Benachrichtigungen kannst du zwischen „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“ wählen. Peek beobachtet ausschließlich Tracking-Updates und kann weder eine *giacenza*-Lagerung freigeben noch Lieferadressen ändern oder Liccardi Express Courier für dich kontaktieren.

## Häufige Fragen zur Sendungsverfolgung von Liccardi Express Courier

### Wie sieht eine Liccardi-Sendungsnummer aus?

Liccardi verwendet entweder einen 13-stelligen Etiketten-Barcode mit Anfang `66` (typischerweise `660`, zum Beispiel `6604829173605`, erfunden) oder eine 7- bis 8-stellige Sendungsnummer (*LDV*, zum Beispiel `48291736`, erfunden).

### Was bedeutet `IN GIACENZA` bei Liccardi?

`IN GIACENZA` (Code `35`) bedeutet, dass das Paket nach einer verpassten Zustellung oder einem Adressproblem in einer Liccardi-Filiale eingelagert ist und eine Freigabeanweisung (*svincolo*) vom Absender benötigt. Kontaktiere deinen Verkäufer und übermittle aktualisierte Zustellangaben über [Assistenza / Reclami Destinatari](https://www.liccarditrasporti.com/assistenza-reclami-destinatari/).

### Wie lange dauert eine Lieferung mit Liccardi Express Courier in Italien?

Liccardi nennt **24–48 Stunden** für nationale Lieferungen nach der Abholung (statistisch innerhalb von **72 Stunden** im gesamten italienischen Netz), wobei abgelegene Orte (*località disagiate*) und Saisonspitzen länger dauern können.

### Wie erreiche ich den Kundenservice von Liccardi Express Courier?

Rufe in Italien die kostenlose Nummer `800 029 764` oder `+39 081 5053642` an, schreibe an `assistenzaclienti@liccarditrasporti.com` oder reiche eine Empfängeranfrage bzw. Reklamation über [liccarditrasporti.com/assistenza-reclami-destinatari/](https://www.liccarditrasporti.com/assistenza-reclami-destinatari/) ein.

:::sources
- [Liccardi Express Courier: Offizielle Website](https://www.liccarditrasporti.com/) – *F.lli Liccardi S.r.l.*, 20+ Filialen, über 5,9 Mio. Sendungen jährlich, Sitz in Casandrino (NA) und Kontakte
- [Liccardi Express Courier: Azienda](https://www.liccarditrasporti.com/azienda/) – Firmengeschichte seit 1992 (Ursprung 1960er-Jahre), B2C-Ausbau ab 2004 und Mitgliedschaft in der Unione Industriali
- [Liccardi Express Courier: Servizi](https://www.liccarditrasporti.com/servizi/) – 24/48-Std.-Zustellung (bis 72 Std.), 48-Std.-Abholung, *contrassegno*, *fermo deposito* und automatisierte Fotosortierung
- [Liccardi Express Courier: Filiali](https://www.liccarditrasporti.com/filiali/) – vollständige Liste der 20+ italienischen Hubs und Filialen (NA1, RM1, MI1, BO1, TO2, FI1, VR1, BA1 usw.)
- [Liccardi Express Courier: Contatti](https://www.liccarditrasporti.com/contatti/) – `800 029 764`, `+39 081 5053642`, E-Mails, PEC und Adresse in Casandrino
- [Liccardi Express Courier: Assistenza / Reclami Destinatari](https://www.liccarditrasporti.com/assistenza-reclami-destinatari/) – Empfängersupport, Einspruchsoptionen und Reklamations-/Schlichtungsformulare (von Peek verlinkte Hilfeseite)
- [Universal Parcel Scraper: liccardi](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/liccardi) – `660`/LDV-Nummernregeln, Link-Regel `my.liccarditrasporti.com`, vollständige `codEsito`-Statuszuordnung, Mehrpaketlogik, voraussichtlicher Liefertag und Partner-LDV-Erkennung
:::
