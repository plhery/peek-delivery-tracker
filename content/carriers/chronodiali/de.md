---
title: Chronodiali Sendungsverfolgung: CHPU-Nummern & Kontakt
description: Chronodiali-Paket in Marokko verfolgen: CHPU-Nummer, französische Status erklärt, Zahlung bei Lieferung, drei Zustellversuche und Kontakte.
slug: chronodiali-sendungsverfolgung
published: 2026-11-23
updated: 2026-11-23
---

Chronodiali (auch Chrono Diali geschrieben) ist ein marokkanischer Paketdienst für Online-Shops, ein Gemeinschaftsunternehmen von Barid Al-Maghrib (Poste Maroc) und Geopost. Das Unternehmen liefert E-Commerce-Pakete in ganz Marokko nach Hause oder in eine seiner Agenturen. Hat der Shop Nachnahme gewählt, zahlst du bei der Übergabe an den Kurier in bar oder mit Bankkarte. Verfolge dein Paket unter „Suivre mon colis“ auf [chronodiali.ma](https://chronodiali.ma/suivi_colis.html) oder im Feld oben. Die Nummer kommt vom absendenden Shop; das Beispiel von Chronodiali beginnt mit `CHPU`.

## Deine Chronodiali Sendungsnummer

Die Muster, die Peek kennt (mit erfundenen Ziffern):

| Nummer | Was sie ist |
| --- | --- |
| `CHPU4829103756182947305` | `CHPU` und 19 Ziffern: eine Chronodiali-Nummer. Peek erkennt sie sofort. |
| `CD4829103756` | `CD` und 10 Ziffern. Peek kann sie auch bei Chronodiali verfolgen, doch dasselbe Muster passt zu [Colis Privé](carrier:colis-prive), [Aramex Australien](carrier:aramex-au) und [Aramex Neuseeland](carrier:aramex-nz) – Peek fragt daher alle vier (siehe unten). |
| `chronodiali.ma/suivi_colis.html?id=…` | Ein Link zur Tracking-Seite von Chronodiali. Peek erkennt Chronodiali unabhängig von der enthaltenen Nummer. |

Chronodiali ist nicht [Chronopost](carrier:chronopost), der französische Expressdienst: ihre Nummern und Tracker sind verschieden. Gar keine Nummer? Frag den Shop oder lies [wo du eine Sendungsnummer findest](guide:find-tracking-number).

## Chronodiali Tracking-Status auf Deutsch erklärt

Chronodiali schreibt seine Meldungen auf Französisch. In der üblichen Reihenfolge:

| Chronodiali meldet | Peek zeigt | Was das für dich bedeutet |
| --- | --- | --- |
| `Les données du colis ont été intégrées dans nos systèmes.` | Etikett erstellt | Der Shop hat die Paketdaten an Chronodiali übermittelt. Chronodiali hat das Paket noch nicht. |
| `Le ramassage a été programmé.` | Etikett erstellt | Eine Abholung beim Shop ist geplant. |
| `Le ramassage est en attente, le livreur se présentera sous peu.` | Etikett erstellt | Wartet auf die Abholung durch den Kurier beim Absender. |
| `Le colis a été collecté par le livreur.` | Beim Paketdienst eingegangen | Der Kurier hat das Paket abgeholt. |
| `Le colis est arrivé au hub d'origine.` | Unterwegs | Im ersten Hub von Chronodiali eingetroffen. |
| `Le colis a quitté notre hub.`, `Le colis est arrivé au hub.` | Unterwegs | Unterwegs zwischen den Hubs von Chronodiali. |
| `Delivery Scheduled` | Unterwegs | Im Ziel-Hub für eine Zustelltour eingeplant. |
| `Out For Delivery`, `Le colis est en cours de livraison.` | In Zustellung | Heute beim Kurier. Bei Nachnahme halte den Betrag bereit. |
| `Le colis a été livré.` | Zugestellt | Zugestellt. |
| `La livraison a échoué : le client n'a pas répondu ou était injoignable.` | Zustellversuch | Der Kurier konnte dich telefonisch oder an der Tür nicht erreichen. |
| `La livraison a échoué : le lieu de livraison est incorrect ou introuvable.` | Zustellversuch | Die Adresse ist falsch oder der Kurier fand sie nicht. |
| `La livraison a échoué : le client est indisponible.` | Zustellversuch | Du warst nicht verfügbar. |
| `La livraison a échoué : le client n'a pas le montant COD requis.` | Zustellversuch | Du hattest den Nachnahmebetrag (COD) nicht bereit. |
| `La livraison a échoué : le client a refusé le colis.` | Zustellversuch | Die Annahme wurde verweigert. |
| `La livraison a échoué.` | Zustellversuch | Fehlgeschlagen, ohne genannten Grund. |
| `Le colis est retenu au hub.` | Problem gemeldet | Das Paket wird im Hub zurückgehalten. |
| `La livraison a été annulée : le colis est endommagé et ne peut pas être livré.` | Problem gemeldet | Das Paket ist beschädigt und wird nicht zugestellt. |
| `Le colis a été annulé.` | Problem gemeldet | Die Sendung wurde storniert. |
| `Le colis va être renvoyé à l'expéditeur.` | Zurück an Absender | Geht zurück an den Shop. |
| `Le colis a été retourné à l'adresse de l'expéditeur.` | Zurück an Absender | Wieder an der Adresse des Shops angekommen. |

**Nicht auf chronodiali.ma.** Chronodialis eigene Seite lässt einige dieser Einträge weg: `Delivery Scheduled`, `Out For Delivery`, fehlgeschlagene Zustellungen, stornierte oder neu geplante Lieferungen sowie `Le colis a été confié à un partenaire tiers.` Peek zeigt sie an, sodass der Verlauf dort ausführlicher sein kann.

**Weitere Meldungen.** `La livraison a été reprogrammée : une nouvelle date a été définie.` bedeutet einen neuen Liefertermin; `La livraison a été reprogrammée.` nennt keine Details. `La livraison a été annulée à la demande du client.` bedeutet Stornierung auf Kundenwunsch. `Le colis est en attente au hub.` heißt, das Paket wartet in einem Hub. Zu den beiden Zeilen mit `partenaire tiers` siehe die Fragen unten; Peek zeigt beide als „Unterwegs“.

**Pakete aus dem Ausland.** Manche Sendungen kommen aus dem Ausland nach Marokko. Dann können `arrivé dans le pays.` (in Marokko angekommen; Peek zeigt „Unterwegs“), `dédouanement en cours` (Zollabfertigung läuft; „Beim Zoll“) und `dédouanement terminé` (vom Zoll freigegeben; „Unterwegs“) erscheinen. Über Gebühren oder wer sie trägt, sagt die Website nichts: frag beim Shop und siehe [Pakete beim Zoll](guide:customs).

## Chronodiali Zustellung: Nachnahme, drei Versuche, Agenturen

Chronodiali gibt an, in ganz Marokko an mehr als 300 Ziele zuzustellen, nach Hause oder in eine Agentur.

- **Zahlung bei Lieferung:** Die Tracking-Seite nennt den zu zahlenden Betrag (`Montant à payer`) in Dirham (DH). Laut Chronodiali kannst du bei der Übergabe bar oder mit Bankkarte zahlen.
- **Drei Versuche:** Chronodiali führt „3 Tentatives de livraison“ (drei Zustellversuche) auf. In Peek zeigt jeder fehlgeschlagene Versuch eine Zeile beginnend mit `La livraison a échoué`, mit oder ohne Grund.
- **Telefon erreichbar halten:** Ein Grund für eine fehlgeschlagene Zustellung ist, dass niemand abnahm oder erreichbar war.
- **Benachrichtigungen:** Die Leistungsseite nennt SMS- und E-Mail-Hinweise sowie eine Kundenbenachrichtigung bei jedem Schritt.
- **Agenturen:** Agadir, Casablanca, Fès, Marrakesch, Rabat und Tanger, geöffnet Montag bis Freitag 9:00–18:00 Uhr und Samstag 9:00–13:00 Uhr (marokkanische Zeit). Die Adressen stehen auf der Seite „Points de livraison“.

Wie lange eine Agentur ein Paket aufbewahrt oder was nach dem dritten gescheiterten Versuch geschieht, nennt die Website nicht: frag Chronodiali oder den Shop. Kurier verpasst? Siehe [verpasste Zustellung](guide:missed-delivery).

## Chronodiali-Paket verspätet, verweigert, zurückgesandt oder nicht erhalten

Für eine Erstattung oder Ersatzlieferung wende dich an den Shop. Der Shop ist Vertragspartner von Chronodiali und bucht die Abholungen über seine Kundenplattform.

- **Keine Bewegung nach `Les données du colis ont été intégrées dans nos systèmes.`:** Der Shop hat den Versand angelegt, Chronodiali hat das Paket aber noch nicht abgeholt. Frag den Shop, wann die Übergabe erfolgt. Zeigt chronodiali.ma `En cours de traitement.` (in Bearbeitung), kennt Chronodiali die Nummer, hat aber noch keinen Scan.
- **Verspätet:** Chronodiali veröffentlicht keine Laufzeiten. Laut AGB werden sie bei der Bestellung genannt und hängen vom Zielort ab. Ist die neueste Zeile vier Tage alt oder älter und steht das Paket noch auf „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“, markiert Peek es mit dem Tag dieser Zeile („Keine Aktualisierung seit …“) und fügt den Link „Hol dir Hilfe bei Chronodiali“ hinzu. Siehe [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating).
- **Verweigert oder nicht bezahlt:** Lehnst du ab oder kannst nicht zahlen, verbucht der Kurier einen fehlgeschlagenen Versuch. Danach kann das Paket an den Shop zurückgehen: `Le colis va être renvoyé à l'expéditeur.`, dann `Le colis a été retourné à l'adresse de l'expéditeur.` Siehe [zurück an den Absender](guide:returned-to-sender).
- **Zugestellt, aber nicht erhalten:** Chronodiali stellt Shops einen digitalen Zustellnachweis im Kundenbereich bereit. Bitte den Shop, ihn zu prüfen. Mehr unter [zugestellt, aber nicht erhalten](guide:delivered-not-received).
- **Beschädigt oder verloren:** Informiere zuerst den Shop. Du kannst außerdem im Kontaktformular von Chronodiali „Réclamation ?“ ankreuzen oder mit deiner Nummer anrufen bzw. schreiben. Siehe [beschädigte Pakete](guide:damaged-parcel) und [verlorene Pakete](guide:lost-parcel).
- **Eine Zahlungsaufforderung per Nachricht:** Auf der Website beschreibt Chronodiali die Zahlung als Bezahlung bei Übergabe, bar oder mit Karte. Verlangt eine SMS oder E-Mail eine Zahlung über einen Link, prüfe zuerst das Tracking und siehe [Betrugs-SMS zu Paketen](guide:delivery-scam-texts).

## Chronodiali Kontaktnummer und Kundenservice

Die Angaben auf chronodiali.ma:

- **Telefon:** +212 5 20 61 62 63 (05 20 61 62 63 innerhalb Marokkos).
- **E-Mail:** service-client@chronodiali.ma.
- **Kontaktformular:** Auf der Seite [Contact](https://chronodiali.ma/contact_us.html) wählst du „Réclamation ?“ für eine Beschwerde oder „Demande d'information ?“ für eine Frage. Das Formular fragt nur nach Name, E-Mail, Telefonnummer und Shop-Name, ohne Feld für Nachricht oder Sendungsnummer; laut Chronodiali meldet sich das Team bei dir.
- **Agenturen:** die sechs Städte oben, mit derselben Telefonnummer und E-Mail-Adresse.

Die Website nennt keine gesonderten Telefonzeiten und keine WhatsApp-Nummer.

## Ein Chronodiali-Paket mit Peek verfolgen

Füge eine `CHPU`-Nummer ein und [Peek](/) zeigt sofort Chronodiali unter „Erkannter Paketdienst“. Kleinschreibung und Leerzeichen stören nicht. Ein Link zur Tracking-Seite auf chronodiali.ma funktioniert ebenfalls.

Eine 10-stellige `CD`-Nummer verrät den Paketdienst allein noch nicht. Füge sie trotzdem ein: Peek verfolgt mehr als 4.500 Paketdienste. Während Peek bei Chronodiali und den anderen Diensten mit diesem Muster nachfragt, steht dort „Tracking-Dienste werden geprüft…“; hat nur Chronodiali das Paket, erscheint sein Name mit „hat dieses Paket“. Oder tippe auf „Ändern“ und wähle „Chronodiali“ unter „Passt zu dieser Nummer“. Wählst du Chronodiali für eine anders aufgebaute Nummer, meldet Peek, dass Chronodiali „vergibt keine Nummern wie diese“, und das Paket zeigt „Sendungsnummer prüfen“.

Peek liest Chronodialis eigenes Tracking direkt aus: den Status, jede Zeile wie von Chronodiali geschrieben (französisch, teils englisch wie die beiden Zeilen oben), den Namen des Hubs als Ort falls angegeben, das Zielland und den Zustellzeitpunkt. Übermittelt Chronodiali sie, zeigt Peek dir auch den Namen des Empfängers unter „Empfänger“ und, solange das Paket unterwegs ist, den Zielort auf der Karte, wenn Peek ihn in Marokko findet; wem du das Paket teilst, sieht beides nicht, und der Name der Person, die unterschrieben hat, bleibt privat: Keine App zeigt ihn. Uhrzeiten zeigt Peek in deiner Geräte-Zeitzone. Absendername, Absenderstadt, Zahlbetrag, Zustellnachweis und Anrufe des Kuriers bleiben außen vor; ein voraussichtliches Lieferdatum und Gewicht liefert Chronodiali nicht. Die Zeilen zu einem Partnerunternehmen nennen dessen Namen nicht, und Peek wechselt nicht zu dessen Tracking.

Kennt Chronodiali die Nummer noch nicht, zeigt Peek „Warten auf Chronodiali“ und prüft weiter; kennt Chronodiali sie nach zwei Tagen immer noch nicht, bittet Peek dich, sie zu prüfen, einen anderen Paketdienst zu wählen oder das Paket zu löschen. Solange das Paket in Peek geöffnet ist, oder an seinem Zustelltag mit aktivierten Benachrichtigungen, prüft Peek bis zu alle 10 Minuten, und alle 2 Minuten ab 6 Uhr marokkanischer Zeit an dem Tag, an dem Chronodiali das Paket in Zustellung scannt, solange Chronodialis eigenes Tracking antwortet. Nach zwei Tagen ohne Neuigkeiten (12 Stunden bei „Etikett erstellt“ oder „Beim Paketdienst eingegangen“) prüft Peek stündlich, und nach sieben Tagen ohne Neuigkeiten bei „Etikett erstellt“ alle 6 Stunden. Benachrichtigungen gibt es für „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“. Peek kann nicht bezahlen, keinen neuen Termin buchen und weder Chronodiali noch den Shop für dich kontaktieren.

## Fragen zur Chronodiali Sendungsverfolgung

### Gehört Chronodiali zu Poste Maroc?

Teilweise. Laut Chronodialis News-Seite ist es ein Joint Venture: Barid Al-Maghrib (Poste Maroc) hält 51 % und Geopost 49 %. Es nutzt sein eigenes Tracking auf chronodiali.ma und einen eigenen Kundenservice.

### Wie oft versucht Chronodiali zuzustellen?

Dreimal, so Chronodiali. In Peek erscheint ein fehlgeschlagener Versuch mit einer Zeile ab `La livraison a échoué`, mit oder ohne Begründung.

### Kann ich ein Chronodiali-Paket mit Karte bezahlen?

Ja. Laut Chronodiali kannst du bei Lieferung bar oder mit Bankkarte bezahlen.

### Was bedeutet „partenaire tiers“ beim Chronodiali Tracking?

`Le colis a été assigné à un partenaire tiers.` und `Le colis a été confié à un partenaire tiers.` bedeuten, dass Chronodiali das Paket einem Partnerunternehmen zugewiesen oder übergeben hat. Das Tracking nennt diesen Partner nicht: warte auf die nächste Meldung oder frage Chronodiali mit deiner Nummer.

### Wie erreiche ich Chronodiali wegen meines Pakets?

Ruf +212 5 20 61 62 63 an oder schreibe an service-client@chronodiali.ma mit deiner Sendungsnummer. Für Erstattung oder Neuversand wende dich an den Shop.

:::sources
- [Chronodiali: Les experts de la livraison e-commerce](https://chronodiali.ma/) – Barid Al-Maghrib und Geopost, Zahlung bei Lieferung bar oder mit Karte, Haus- oder Agenturzustellung, über 300 Ziele, Agenturen und Zeiten, Telefon, E-Mail
- [Chronodiali: Suivre mon colis](https://chronodiali.ma/suivi_colis.html) – Tracking, Beispiel `CHPU`, Zahlbetrag, französische Statuszeilen, was die Seite anzeigt und „En cours de traitement.“ (im Seitenskript)
- [Chronodiali: Nos services](https://chronodiali.ma/nos_service.html) – drei Zustellversuche, SMS- und E-Mail-Benachrichtigungen, bar oder Karte, digitaler Zustellnachweis
- [Chronodiali: Points de livraison](https://chronodiali.ma/point_de_livraison.html) – Lieferung in ganz Marokko, Agenturen und Öffnungszeiten
- [Chronodiali: Envoyer un colis](https://chronodiali.ma/envoyercolis.html) – Shop-Konten und Abholungen, Zustellnachweise für Shops
- [Chronodiali: Contact](https://chronodiali.ma/contact_us.html) – Kontaktformular mit „Réclamation ?“ und seinen Feldern, Telefon, E-Mail
- [Chronodiali: Gouvernance](https://chronodiali.ma/aboutus.html) – Barid Al-Maghrib (Poste Maroc)
- [Chronodiali: Actualités](https://chronodiali.ma/actualites.html) – Joint Venture, 51 % Barid Al-Maghrib und 49 % Geopost
- [Chronodiali: Mention légale](https://chronodiali.ma/mentions_legal.html) – Chrono Diali S.A., Casablanca; AGB: Vertrag und Abholungen des Shops, Lieferzeiten bei Bestellung
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Nummernformate, Statuszeilen und Stufen, was Chronodialis Seite und Peek anzeigen
:::
