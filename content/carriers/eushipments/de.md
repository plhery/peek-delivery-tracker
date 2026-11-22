---
title: euShipments Sendungsverfolgung: Status und Zusteller
description: euShipments Bestellung verfolgen: Nummern, Bedeutung von New, Loaded hub und On delivery, lokaler Paketdienst und Kontakt.
slug: eushipments-sendungsverfolgung
published: 2026-11-22
updated: 2026-11-22
---

euShipments ist ein E-Commerce-Logistikunternehmen mit Sitz in Russe, Bulgarien. Es lagert, verpackt und versendet Online-Bestellungen in die 28 Länder seiner Liste „We deliver to“ – die 27 EU-Staaten und das Vereinigte Königreich – und übergibt jedes Paket auf der letzten Meile an einen lokalen Paketdienst. Verfolge deine Nummer auf der Seite „Track my order“ unter [eushipments.com/tracking](https://eushipments.com/tracking) oder füge sie oben ein. Die Nummer kommt vom Shop.

## euShipments Sendungsnummer: So sieht sie aus

euShipments veröffentlicht kein festes Nummernformat. Sobald die Bestellung bearbeitet ist, erhältst du laut euShipments einen „tracking code, AWB number or tracking ID“ für das Feld „Enter your tracking number to track packages.“ Das Tracking zeigt die Bestellung unter der vom Shop gesendeten Nummer an, die auch direkt vom ausliefernden Kurier stammen kann (mit erfundenen Ziffern):

| Nummer | Was es ist |
| --- | --- |
| `74829103` | Acht Ziffern: die einzige Form, die Peek im Tracking von euShipments gesehen hat, für eine Lieferung in Bulgarien, die der lokale Kurier unter derselben Nummer zustellte. |
| Die Nummer nach „Tracking Number:“ | Die eigene Nummer des lokalen Paketdienstes, die im Ergebnis von euShipments erscheint, sobald der Kurier das Paket hat. Sein eigenes Tracking akzeptiert sie direkt. |

Acht Ziffern passen auch zu [Mondial Relay](carrier:mondial-relay), Colisweb und [Heppner](carrier:heppner), daher beweist die Nummer allein nicht euShipments: Prüfe, welches Unternehmen die Versandmail nennt. Ein Link zur Tracking-Seite von euShipments öffnet deine Bestellung nicht, da die Seite Nummern nur über das Formular entgegennimmt. Keine Nummer vorhanden? Siehe [Sendungsnummer finden](guide:find-tracking-number).

## euShipments Statusmeldungen erklärt

Das Ergebnis zeigt „My order N“ mit deiner Nummer und eine Tabelle mit drei Spalten: „Date/Time“, „Tracking Status“ und „Tracking Information“. Der Status steht im einheitlichen englischen Wortlaut von euShipments, unabhängig vom lokalen Zusteller. „Tracking Information“ zeigt daneben die eigenen Meldungen des Paketdienstes, sobald dieser das Paket hat. Jede Zeile ist minutengenau datiert, etwa „10 August, 2026 15:39“, ohne Zeitzone.

| euShipments meldet | Was das für dich bedeutet |
| --- | --- |
| `New` | Der Shop hat die Bestellung bei euShipments angelegt. Sie ist womöglich noch nicht verpackt. |
| `Warehouse` | Die Bestellung befindet sich im genannten Fulfillment-Lager von euShipments. |
| `Loaded hub` | An einem Hub von euShipments für die Fahrt zum nächsten verladen. Es folgt der Hub-Name mit Länderkürzel in Klammern, z. B. (HR). |
| `Unloaded hub` | An einem Hub entladen, ebenso benannt. |
| `Picked up` | Der lokale Paketdienst hat das Paket übernommen. |
| `In transit` | Unterwegs im Netz des Paketdienstes. |
| `On delivery` | Beim Fahrer in Zustellung. |
| `Delivered` | Zugestellt – der letzte Status einer Bestellung. |

`Loaded hub` und `Unloaded hub` können mehrfach erscheinen, einmal je Hub unterwegs. Nach `Picked up` stehen drei Zeilen unter der Tabelle: „Courier Name:“, „Tracking Number:“ und „Tracking Link:“ – dein ausliefernder Paketdienst, seine Nummer und der Link zu seinem Tracking.

Peek hat bei euShipments keinen eigenen Status für verpasste Zustellungen, Abholstellen oder Retouren gesehen. Lies dafür den Text des Kuriers unter „Tracking Information“ oder öffne dessen Tracking-Link.

## Wer liefert ein euShipments Paket in deinem Land?

Die letzte Meile überlässt euShipments lokalen Paketdiensten. Pakete laufen per Linienverkehr von den Fulfillment- und Cross-Docking-Zentren (Sofia und Russe in Bulgarien, Oradea in Rumänien, Zagreb in Kroatien, Ljubljana in Slowenien, Senec in der Slowakei, Budapest in Ungarn) zum Depot eines lokalen Kuriers. Laut FAQ arbeitet euShipments mit mehr als 60 lokalen Paketdiensten zusammen, mindestens zwei pro europäischem Land. Die Liste „We deliver to“ nennt 28 Länder: die 27 EU-Staaten und das Vereinigte Königreich.

Die Startseite zeigt Logos wichtiger Partner („Key eCommerce partnerships“), darunter Speedy, Sameday, [FAN Courier](carrier:fan-courier), Express One, GLS, DPD, BOX NOW, Pošta Slovenije, [Slovenská pošta](carrier:slovenska-posta), PPL, ACS und [Geniki Taxydromiki](carrier:geniki-taxydromiki), ohne zuzuordnen, wer wo liefert. Die Firmengeschichte nennt zudem eine strategische Partnerschaft mit der Österreichischen Post im Jahr 2026 ohne weitere Details. Welcher Paketdienst dein Paket bringt, hängt von deinem Land und der Versandart im Shop ab – „Courier Name:“ verrät es dir.

**Wohin geliefert wird.** In Bulgarien liefert euShipments an Privat- oder Geschäftsadressen sowie außer Haus: Filiale des Paketdienstes, Paketshop (PUDO) oder Paketautomat. Die Seiten für Kroatien und Rumänien nennen ähnliche Optionen. euShipments zählt über 150.000 Out-of-Home-Standorte und weist darauf hin, dass du bei Abholbereitschaft eine Benachrichtigung mit Abholhinweisen erhältst.

**Zustellversuche, Lagerfrist und Zweitzustellung.** Hier gelten die Regeln des ausliefernden Paketdienstes – euShipments veröffentlicht keine eigenen. Nutze „Tracking Link:“ und „Tracking Number:“ auf der Seite des Paketdienstes und wende dich für einen neuen Versuch oder eine Adressänderung an ihn.

**Nachnahme.** euShipments bietet Nachnahme in zahlreichen europäischen Ländern an; der lokale Paketdienst kassiert den Betrag und leitet ihn an den Shop weiter.

## euShipments Paket hängt, verloren oder zurückgesendet

**Hängt bei `New`.** Der Shop hat die Bestellung angelegt, aber noch kein Lager- oder Hub-Scan ist gefolgt. Frag den Shop, wann versendet wird.

**Hängt zwischen Hubs.** Nach `Loaded hub` ist das Paket auf der Straße zum nächsten Hub; der nächste Eintrag kommt meist beim Entladen dort. Bleibt alles länger als die Schätzung des Shops stehen, frag den Shop. Siehe [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating).

**Hängt nach `Picked up`.** Der Paketdienst hat das Paket. Öffne „Tracking Link:“ und prüfe dessen eigenes Tracking mit seiner Nummer.

**Zugestellt, aber nicht da.** Die Zustellung erfolgte durch den lokalen Paketdienst: Frag zuerst dort, dann beim Shop. Siehe [Zugestellt, aber nicht erhalten](guide:delivered-not-received).

**Zurückgesendet.** Laut der Retourenseite von euShipments gelten unzustellbare Pakete – etwa wegen falscher Empfängerdaten oder fehlender Erreichbarkeit – als nicht zugestellte Sendungen und gehen an das euShipments-Lager oder den Shop zurück, nicht an dich. Frag den Shop nach Neulieferung oder Erstattung. Mehr unter [zurück an Absender](guide:returned-to-sender).

**Selbst zurücksenden.** euShipments wickelt Retouren für Shops auf drei Wegen ab: über einen Retourenantrag auf der Website des Shops mit Wahl von Tag, Zeit und Ort für die Abholung; über eine vom Shop gebuchte Abholung; oder indem du das Paket selbst mit einem beliebigen Paketdienst an die lokale Rücksendeadresse des Shops (ein euShipments-Lager) schickst. Welche Option gilt, steht in den Rückgabebedingungen des Shops. Siehe [Retoure verfolgen](guide:returns-tracking).

**Verloren oder beschädigt.** euShipments veröffentlicht kein Reklamationsverfahren für Empfänger: Vertragspartner ist der Shop. In der EU haftet der Verkäufer ab Versand bis zur Übergabe für Schäden und muss ohne andere Vereinbarung innerhalb von 30 Tagen liefern. Tut er das nicht, mahne ihn mit angemessener Nachfrist an; liefert er auch dann nicht, kannst du stornieren und dein Geld zurückverlangen. Siehe [verlorenes Paket](guide:lost-parcel) und [beschädigtes Paket](guide:damaged-parcel).

## euShipments Telefonnummer und Kundenservice

euShipments nennt diese Kontaktwege:

- **Telefon:** +359 700 70 755, eine bulgarische Nummer auf Kontakt-, FAQ- und Tracking-Seite. Öffnungszeiten nennt euShipments nicht.
- **E-Mail:** office@eushipments.com.
- **Kontaktformular:** ein Angebotsfragebogen für Online-Shops (monatliches Volumen, Zielmärkte), nicht für Empfänger gedacht.

Die FAQ für Shops nennt Telefon-, E-Mail- und Live-Chat-Support für Endkunden „auf Anfrage“ („when requested“). Wende dich daher zuerst an den Shop; bei Fragen zur Zustellung selbst ist der unter „Courier Name:“ genannte Paketdienst schneller.

## euShipments Paket mit Peek verfolgen

[Peek](/) kann eine euShipments-Nummer nicht an ihrer Form erkennen: Es gibt keine Nummernregel für euShipments, und acht Ziffern passen schwach zu anderen Diensten. Füge sie trotzdem ein – Peek verfolgt über 4.500 Paketdienste. Tippe dann auf „Ändern“ und wähle „euShipments“, oder nutze „Paketdienst ändern“ bei einem bereits hinzugefügten Paket.

Peek liest das Tracking von euShipments und zeigt Status, Verlauf sowie bei Lager- und Hub-Zeilen den Ort (mit Land bei Hubs). Es behält die englischen Status von euShipments und lässt die Kuriertexte aus „Tracking Information“ weg. Führt „Tracking Link:“ zu einem Paketdienst, den Peek verfolgen kann – etwa [Econt](carrier:econt), Sameday, [FAN Courier](carrier:fan-courier), [Packeta](carrier:packeta), [Slovenská pošta](carrier:slovenska-posta) oder [Geniki Taxydromiki](carrier:geniki-taxydromiki) – samt dessen Nummer im Link, zeigt Peek „Zustellung durch …“ und die Kuriernummer, falls sie abweicht. Liegt dort erst die Ankündigung ohne eigenen Scan vor, erscheint „Bei … angekündigt, noch nicht eingetroffen“. Sobald der Paketdienst datierten Fortschritt mindestens so aktuell wie euShipments zeigt, wechselt Peek dorthin und behält die bisherigen Einträge; sonst bleibt es beim Verlauf von euShipments.

euShipments nennt weder voraussichtliches Lieferdatum noch Zustellnachweis, sodass Peek beides nicht anzeigt. Zeiten ohne Zeitzone liest Peek in der jeweiligen Ortszeit (bulgarische Zeit in Bulgarien) und rechnet sie in deine Zeitzone um („Uhrzeiten in deiner Zeitzone:“). Peek prüft bis zu alle 10 Minuten und am Tag der Zustellung ab 6:00 Uhr nach der Uhr des letzten Scans alle 2 Minuten, solange das Paket in Peek geöffnet ist oder Benachrichtigungen aktiv sind und der Tracker von euShipments antwortet. Nach zwei Tagen ohne Neuigkeiten (nach 12 Stunden bei „Etikett erstellt“ oder „Beim Paketdienst eingegangen“) prüft Peek stündlich. Für Benachrichtigungen wählst du „Alle Updates“, „Nur wichtige“ oder „Nur am Liefertag“. Nach vier Tagen ohne neuen Scan unterwegs, außerhalb von Zoll oder gemeldeten Problemen, zeigt Peek „Keine Aktualisierung seit …“, den Hinweis „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei euShipments nach.“ sowie „Hol dir Hilfe bei euShipments“. Peek kann weder euShipments noch den Shop oder den Paketdienst für dich kontaktieren.

## Häufige Fragen zu euShipments

### Ist euShipments seriös?

Ja. Es ist ein 2012 als InOut Trade gegründetes und 2019 in euShipments.com umbenanntes Logistikunternehmen in Russe, Bulgarien. Es versendet für Online-Shops, darunter Händler auf Temu und eMAG über eigene Integrationen.

### Welcher Paketdienst liefert mein euShipments Paket?

Ein lokaler Paketdienst in deinem Land. Sobald dieser das Paket hat, stehen unter der Tabelle bei euShipments „Courier Name:“, „Tracking Number:“ und „Tracking Link:“.

### Wie lange dauert die Lieferung mit euShipments?

Das hängt vom Lagerstandort des Shops ab. Ab Sofia und Russe nennt euShipments Zustellung am nächsten Tag nach Bulgarien und Rumänien sowie 2 bis 4 Tage nach Kroatien, Slowenien, in die Slowakei und nach Tschechien. Ab Zagreb, Ljubljana oder Senec nennt es 1 Werktag im jeweiligen Land. Richte dich nach der Schätzung deines Shops. Mehr unter [Lieferzeiten](guide:delivery-times).

### Was bedeutet Loaded hub bei euShipments?

Das Paket wurde an einem Hub von euShipments für die nächste Etappe verladen, angegeben mit dem Länderkürzel in Klammern. `Unloaded hub` ist die Ankunft am nächsten Hub.

### In welche Länder liefert euShipments?

Die Liste „We deliver to“ nennt 28 Länder: die 27 EU-Staaten und das Vereinigte Königreich. In jedem übernimmt ein lokaler Paketdienst die letzte Meile.

:::sources
- [euShipments: Track Your Order](https://eushipments.com/tracking) – Tracking-Formular und Wortlaut, Telefon, E-Mail
- [euShipments: Parcel Tracking For End Customers](https://eushipments.com/ecommerce-delivery/new-parcel-tracking/parcel-tracking-for-end-customers) – Tracking-Code, AWB oder Tracking-ID, über 60 Last-Mile-Partner
- [euShipments: We deliver to](https://eushipments.com/we-deliver-to) – die 28 genannten Länder: 27 EU-Staaten und Vereinigtes Königreich
- [euShipments: FAQ](https://eushipments.com/faq) – über 60 Paketdienste, mindestens zwei pro Land, Endkunden-Support auf Anfrage, Telefon, E-Mail
- [euShipments: Contact Us](https://eushipments.com/contact) – Telefon, E-Mail, Russe, Angebotsformular für Shops
- [euShipments: About us](https://eushipments.com/about-us) – InOut Trade, 2012, Umbenennung 2019, Partnerschaft Österreichische Post
- [euShipments: Home](https://eushipments.com/) – Logos der Key eCommerce partnerships
- [euShipments: eCommerce Deliveries To Bulgaria](https://eushipments.com/countries/bulgaria) – Sofia und Russe, Linienverkehr zum Kurierdepot, Haus- und Out-of-Home-Zustellung, Laufzeiten, Nachnahme
- [euShipments: International eCommerce Delivery To Romania](https://eushipments.com/countries/romania) – Oradea, Budapest, Zustelloptionen
- [euShipments: Domestic and International eCommerce Delivery To Croatia](https://eushipments.com/countries/croatia) – Zagreb und weitere Zentren, Zustellung am nächsten Tag, Automaten, Abholpunkte, Filialen
- [euShipments: International Parcel Delivery To Slovenia](https://eushipments.com/countries/slovenia) – Ljubljana, 1 Werktag
- [euShipments: International Delivery To Slovakia](https://eushipments.com/countries/slovakia) – Senec, 1 Werktag
- [euShipments: Out-of-home Delivery](https://eushipments.com/ecommerce-delivery/out-of-home-delivery) – 150.000 Standorte, Abholbenachrichtigungen
- [euShipments: Returns Management](https://eushipments.com/returns-management) – unzustellbare Sendungen, drei Rücksendewege
- [euShipments: Temu Integration](https://eushipments.com/integration/ecommerce-platform-integration/temu) – Temu-Händler
- [euShipments: eMAG Integration](https://eushipments.com/integration/ecommerce-platform-integration/emag-integration) – eMAG-Händler
- [Your Europe: Shipping and delivery](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_en.htm) – 30 Tage, Händlerhaftung bis zum Erhalt, Mahnung und Erstattung
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Beschriftungen der Ergebnisseite, Status, Nummern, was Peek liest und anzeigt
:::
