---
title: CTT Express Sendungsverfolgung: Status, Punkte & Kontakt
description: CTT Express Paket in Spanien oder Portugal verfolgen: 22- und 25-stellige Nummern, spanische Statusmeldungen auf Deutsch, Collectt-Punkte und Kontakt.
slug: ctt-express-sendungsverfolgung
published: 2026-11-17
updated: 2026-11-20
---

CTT Express ist die spanische Express-Pakettochter der portugiesischen CTT-Gruppe (ehemals Tourline Express, 2019 umbenannt) und stellt in ganz Spanien — einschließlich der Balearen, Kanaren, Ceuta und Melilla — sowie in Portugal zu. Sie können Ihre Sendung im [Localizador](https://www.cttexpress.com/localizador-de-envios/) auf `cttexpress.com` abfragen oder die Nummer oben in das Suchfeld einfügen. Den Sendungscode erhalten Sie vom Händler sowie per SMS oder E-Mail von CTT Express. Verwechseln Sie das Unternehmen nicht mit [CTT](carrier:ctt), der portugiesischen Post: Eine Kennung mit Buchstaben und der Endung `PT` gehört zu [CTT](carrier:ctt).

## Ihre CTT Express Sendungsnummer

Die Sendungsverfolgung von CTT Express fragt nach dem *código de envío* (Sendungscode) — meist einer langen Ziffernfolge aus Ihrer Bestätigungs-SMS oder E-Mail. Diese Nummernformen erkennt Peeks Katalog (Beispiele frei erfunden):

| Nummer (erfunden) | Zuordnung |
| --- | --- |
| `0012340012345678901234` | 22 Ziffern: `00`, vier Ziffern, `00` und 14 weitere Ziffern (Agenturcodes an Stelle 1–2 und 7–8 beginnen jeweils mit `00`). CTT Express. |
| `0012340012345678901234001` | 25 Ziffern: dieselben 22 Ziffern plus dreistelliger Paketzähler (`001` für das erste bzw. einzige Paket laut Etikettenspezifikation von CTT Express). CTT Express. |
| `0000100012345678901234` | 22 Ziffern mit Präfix `00001000`: geteiltes Format von CTT Express, TIPSA, [Austrian Post](carrier:austrian-post) und weiteren Diensten. |
| `RR482910576PT` | Zwei Buchstaben, neun Ziffern, `PT`: Post- oder CTT-Expresso-Nummer von [CTT](carrier:ctt), verfolgbar auf `ctt.pt`. |

**CTT, CTT Expresso oder CTT Express?** Ein Konzern, aber getrennte Netze: [CTT](carrier:ctt) (*Correios de Portugal*) betreibt den postalischen Universaldienst in Portugal sowie den dortigen Expressdienst CTT Expresso mit Buchstaben-Codes auf `PT` (`DA`, `EA` usw.), während CTT Express das spanische Expressnetz (`CTT 24h`, `CTT 48h`, `CTT e24h`, `CTT 10h` und `CTT 14h`) führt. Im Mai 2026 haben CTT Expresso und DHL eCommerce zudem ein strategisches Gemeinschaftsunternehmen auf der Iberischen Halbinsel geschlossen (gegenseitige Beteiligung von je 25 %, keine Fusion): In Spanien übernimmt CTT Express das B2C-Geschäft unter *CTT Express together with DHL* und DHL eCommerce das B2B- und Auslandsgeschäft, während CTT Expresso in Portugal die Aktivitäten von DHL eCommerce integriert. Beide Portale überschneiden sich dennoch: Die portugiesische Sprachwahl auf `cttexpress.com` leitet zu „Seguir objeto“ auf `ctt.pt` weiter, das auch 22- und 25-stellige Zifferncodes liest, und der Tracker von CTT Express nennt selbst einen `PT`-Code als Beispiel. Findet eine Seite nichts, lohnt sich die Gegenprobe auf der anderen.

Noch gar keine Nummer erhalten? Fragen Sie beim Shop nach oder lesen Sie nach, [wo Sie Ihre Sendungsnummer finden](guide:find-tracking-number).

## CTT Express Statusmeldungen auf Deutsch erklärt

Eine deutsche oder englische Sendungsverfolgung bietet CTT Express nicht an: Selbst nach einem Sprachwechsel auf `cttexpress.com` bleibt die Tracking-Seite (`shipping-tracking.production.cloud2.cttexpress.com`) rein spanisch. Oben stehen vier Prozessschritte (`Grabado`, `Enviado`, `En reparto` sowie `Entrega prevista` mit Datum oder `Sin determinar`), darunter die einzelnen Scanzeilen im Wortlaut.

| Spanischer Status | Was das konkret bedeutet |
| --- | --- |
| `Pendiente de recepción en CTT Express` | Der Absender hat die Sendung elektronisch angemeldet; das Paket ist noch nicht im Netz von CTT Express eingetroffen. |
| `Recogido` | Beim Absender abgeholt (`Recogida fallida`, falls die Abholung gescheitert ist). |
| `Enviado` oder `En tránsito` | Unterwegs zwischen Depots und Sortierzentren (`En clasificación` in der Sortierung, `Aceptado por la última milla` nach Eingang im Zustelldepot vor Ort). |
| `En tránsito internacional. Pendiente de recepción en CTT Express` | Unterwegs aus dem Ausland; CTT Express hat das Paket in Spanien oder Portugal noch nicht übernommen. |
| `En reparto` oder `Entrega hoy` | Einer Zustelltour zugewiesen und heute in Auslieferung. |
| `Incidencia en el reparto` oder `Reparto fallido` | Die Zustellung wurde gestoppt; der genaue Grund steht in der Zeile darunter. |
| `Recogerán en Delegación` | „Abholung in der Niederlassung“: Bei einem Paket, das [CTT](carrier:ctt) in Portugal zustellt, schlug der Zustellversuch fehl und die Sendung liegt zur Abholung bereit. Auf `ctt.pt` erscheint zeitgleich der Fehlversuch und kurz darauf die Abholbereitschaft. |
| `Almacenado temporalmente` | Nach einem Fehlversuch vorübergehend im Depot eingelagert, bis zur nächsten Tour oder Rücksendung. |
| `Nuevo reparto` | Erneut auf dem Zustellfahrzeug für einen weiteren Zustellversuch. |
| `Disponible en Punto Collectt Express` | Abholbereit in einem Collectt-Express-Punkt; der PIN-Code kommt per E-Mail und SMS. |
| `El envío fue entregado correctamente` | Zugestellt. |
| `En devolución` | Auf dem Rückweg zum Absender. |
| `Devuelto a remitente` | An den Absender zurückgegeben. |

`Pendiente de recepción en CTT Express` sorgt am häufigsten für Rückfragen, heißt aber zunächst nur, dass das Versandetikett existiert. Wurde für ein Paket mit Versandort in Spanien oder Portugal die Abholung bereits beauftragt und ändert sich dieser Status nach mehr als **24 Stunden** noch immer nicht, verweist CTT Express auf die entsprechende Auswahl im Kontaktformular (*„Mi envío sigue en pendiente de entrada en red“*).

Bei einer Störung nennt der Zusatztext den Auslöser, etwa `No pudimos contactar contigo` („wir konnten Sie nicht erreichen“) oder `Dirección incorrecta` („falsche Adresse“). Laut FAQ von CTT Express kann ein Vermerk über einen abwesenden Empfänger auch dann erscheinen, wenn Sie zu Hause waren, der Fahrer aber keinen Zugang zum Haus oder zur Klingelanlage hatte.

**Bestellungen aus dem Ausland und aus Nicht-EU-Ländern:** Ein im Ausland gekauftes Paket muss zunächst im Ursprungsland abgefertigt und auf die Iberische Halbinsel befördert werden, weshalb `En tránsito` länger stehen bleiben kann. Für Händler außerhalb der EU (etwa aus China) sortiert das Zollzentrum von CTT Express rund um die Uhr über 300.000 Pakete pro Tag und verzollt in **durchschnittlich 12 Stunden** und stellt anschließend im eigenen Netz binnen **24 oder 48 Stunden** zu. Hält der Zoll ein Paket fest, zeigt die Sendungsverfolgung den Stopp an oder Sie erhalten eine Benachrichtigung über offene Unterlagen bzw. Abgaben. Seit dem 1. Juli 2026 fällt in der EU auf solche Nicht-EU-Bestellungen bis 150 € ein pauschaler Zoll von **3 € pro Warenart** an, und seit dem 1. November 2026 zusätzlich auf alle Sendungen unabhängig vom Warenwert eine **EU-Abfertigungsgebühr von 2 € pro Warenart**. Wer diese Beträge einzieht und wann ein Paket weiterläuft, erklärt unser Ratgeber [Paket hängt beim Zoll](guide:customs).

## Zustellzeiten, Collectt-Punkte und verpasste Zustellung

In Spanien liefert CTT Express von Montag bis Freitag in der Regel zwischen **9:00 und 19:00 Uhr** aus (je nach Tarif und Ort); am Samstag wird **ausschließlich in der Weihnachtszeit** zugestellt. Der Fahrer kann seine Ankunft vorab nicht ankündigen und ruft nur an, wenn niemand öffnet oder die Adresse schwer zu finden ist. Nach erfolgreicher Übergabe schickt CTT Express eine Bestätigungs-E-Mail.

Enthalten sind **zwei Zustellversuche**, der zweite am nächsten Werktag (ein dritter Versuch ist möglich, wenn nach dem zweiten Fehlversuch eine Lösung übermittelt wird). Nach einer erfolglosen Anfahrt erhalten Sie eine Benachrichtigung mit Link, über den Sie ein neues Datum, eine andere Adresse, die Abgabe beim Nachbarn oder einen Collectt-Express-Punkt wählen können.

Auch vorab lässt sich die Zustellung im Localizador anpassen — mit der vollständigen Sendungsnummer, der ursprünglichen Empfänger-Postleitzahl sowie der beim Kauf hinterlegten Telefonnummer oder E-Mail-Adresse. Zur Wahl stehen Adressänderung, Wunschtag, Vor- oder Nachmittagsfenster, Nachbar oder Collectt-Punkt. Änderungen greifen **nur vor dem Status `En reparto`** und können die Laufzeit um 24 Stunden verlängern. Eine neue Adresse im selben Zustellgebiet sowie die Umleitung an einen Collectt-Punkt sind **kostenlos** (Collectt ist auf Einzelsendungen bis 20 kg begrenzt, Gurtmaß Länge + Breite + Höhe maximal 160 cm und längste Seite höchstens 60 cm, ohne Nachnahme, Ausweisprüfung oder Rücktausch).

**Collectt-Express-Punkte und Locky-Paketstationen:** CTT Express zählt über **20.000** Annahme- und Abholstellen sowie Locky-Schließfächer in Spanien und Portugal. Ein Collectt-Punkt bewahrt das Paket **10 Tage** lang auf (laut Zustellzeiten-FAQ bis zu 10 Werktage), ein Locky-Fach **5 Tage**; die Abholung erfolgt kostenlos per PIN aus der E-Mail bzw. SMS. Danach greifen die Versandbedingungen des Auftrags. Öffnet sich ein Locky-Fach nicht, hilft die Telefonnummer direkt am Automaten oder in der Tracking-E-Mail.

**In Portugal** gelten die Hilfeseiten von [CTT](carrier:ctt): Zum Collectt-Netz gehören dort *Lojas CTT*, *Pontos CTT*, *Payshop*-Stellen und *Cacifos Locky*. Nach einem Fehlversuch geht das Paket je nach Absendervorgabe an eine dieser Stellen, in einen neuen Versuch oder zurück. Für ein Expresso-Paket aus Spanien räumt CTT **10 Werktage** Abholfrist ein, für ein Locky-Fach **5 Tage**.

## CTT Express Paket verspätet, verloren oder beschädigt

Für eine Rückerstattung oder Ersatzlieferung ist stets der Online-Shop Ihr Vertragspartner.

- **Verspätet:** CTT Express stuft eine Sendung als verspätet ein, sobald das voraussichtliche Lieferfenster überschritten ist; Wochenenden, Feiertage, Unwetter und das Weihnachtsgeschäft verlängern die Laufzeit. Mehr dazu unter [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating).
- **Als zugestellt markiert, aber nicht erhalten:** Prüfen Sie zuerst, ob Haushaltsangehörige, Nachbarn oder der Pförtner das Paket angenommen haben (im Scan steht dann mitunter `El envío fue entregado en conserjería` oder `El envío fue entregado a vecino según las instrucciones indicadas.`), und nutzen Sie danach im Kontaktformular den Punkt *„No tengo mi paquete y pone entregado“*. Weitere Schritte unter [zugestellt, aber nicht erhalten](guide:delivered-not-received).
- **Beschädigt:** Fotografieren Sie das Paket noch vor dem Auspacken, bewahren Sie Karton und Versandetiketten auf und melden Sie den Schaden innerhalb von **7 Kalendertagen** nach Erhalt über das Kontaktformular — samt Angabe, ob Außenkarton, Inhalt oder beides betroffen sind (Absender können optional einen Verlust- und Diebstahlschutz bis 2.000 € buchen). CTT Express legt eine Vorgangsnummer an und teilt mit, wer den Fall bearbeitet.
- **Storniert:** Meldet das Tracking eine stornierte Sendung, wenden Sie sich zur Reaktivierung an den Händler.
- **In Portugal:** Nach den Allgemeinen Beförderungsbedingungen von CTT Expresso stehen Ersatzansprüche dem Auftraggeber des Transports zu — binnen **8 Tagen** nach Zustellung bei Beschädigung und **30 Tagen** bei Verlust oder Verzögerung.

## CTT Express Kontakt, Telefonnummer und Kundenservice

Offizielle Kontaktdaten laut CTT Express und CTT (kein deutsch- oder englischsprachiger Kundendienst ausgewiesen):

- **Spanien:** `900 841 352` (innerhalb Spaniens gebührenfrei laut Hilfeseiten; der Zollratgeber vom 27. Oktober 2026 nennt `900 110 898`) oder `916 602 200` (`+34 916 602 200` aus dem Ausland), Montag bis Freitag von **9:00 bis 19:00 Uhr** außer an Feiertagen. Schriftlich per E-Mail an `ccaweb@cttexpress.com` oder über das [Kontaktformular](https://www.cttexpress.com/contacto-atc) (Sendungsnummer erforderlich). Der Kundenservice sitzt zentral in Madrid, arbeitet ausschließlich per Telefon, E-Mail oder Formular — ohne Publikumsverkehr, Live-Chat oder WhatsApp — und antwortet in der Regel innerhalb von **24 Arbeitsstunden**.
- **Portugal:** [CTT](carrier:ctt)-Hotline `21 047 16 16` (`+351 21 047 16 16` aus dem Ausland), werktags **8:30 bis 19:30 Uhr**, sowie das Formular unter `ctt.pt/ajuda`.

> CTT Express stellt klar, dass niemals zum Rückruf auf kostenpflichtige `807`-Mehrwertnummern aufgefordert wird, und warnt vor gefälschten SMS und E-Mails, die über einen Link zusätzliche Gebühren (*gastos adicionales*) für ein angeblich wartendes Paket verlangen. In Portugal warnt CTT ebenso vor gefälschten „CTT Expresso“-SMS zu Zollgebühren; echte CTT-Links beginnen immer mit `www.ctt.pt`. Verfolgen Sie Pakete nur auf den offiziellen Seiten.

## CTT Express Pakete mit Peek verfolgen

Fügen Sie eine Nummer in einer der ersten beiden Tabellenformen oben ein, erkennt [Peek](/) sofort CTT Express — einschließlich der 25-stelligen `001`-Form einer `00001000`-Nummer (eine 22-stellige Nummer ohne `00` an den Stellen 1–2 und 7–8 erkennt Peek hingegen nicht als CTT Express). Bei 22 Ziffern mit Präfix `00001000` prüft Peek gleichzeitig TIPSA, [Austrian Post](carrier:austrian-post) und CTT Express (sowie [USPS](carrier:usps), falls keiner der drei die Sendung kennt); lässt sich kein eindeutiger Dienst bestimmen, wählen Sie ihn über „Ändern“. Auch ein Tracking-Link von `shipping-tracking.production.cloud2.cttexpress.com` mit `sc=` wird direkt erkannt. Ein einzeln eingegebener `PT`-Code geht an [CTT](carrier:ctt), und Mehrpaketsendungen laufen über universelle Tracking-Dienste.

Peek fragt CTT Express bis zu alle 10 Minuten ab — und ab 6 Uhr Ortszeit am Tag, an dem das Paket auf „In Zustellung“ springt, bis zu alle 2 Minuten, solange der eigene Tracker von CTT Express antwortet (über universelle Dienste alle 15 Minuten). Nach 48 Stunden ohne Bewegung — oder nach 12 Stunden im Stand „Etikett erstellt“ bzw. „Beim Paketdienst eingegangen“ — wechselt Peek auf stündliche Abfragen; Benachrichtigungen lassen sich auf „Alle Updates“, „Nur wichtige“ oder „Nur am Liefertag“ stellen. Angezeigt werden Status, Scanverlauf (ohne Kartenorte, da der öffentliche Feed von CTT Express keine mitliefert), das voraussichtliche Lieferdatum bei laufender Beförderung, Gewicht, Zustellzeitpunkt und — falls eine andere Post das Paket übergeben hat — die internationale Sendungsnummer. Endet diese Nummer auf `PT`, übernimmt [CTT](carrier:ctt) die Auslieferung in Portugal: Auf der Karte erscheint „Zustellung durch CTT Portugal“ samt `PT`-Code (bzw. „Bei CTT Portugal angekündigt, noch nicht eingetroffen“, solange CTT die Sendung noch ohne eigenen Scan führt). Sobald `ctt.pt` einen mindestens ebenso aktuellen Scan wie CTT Express zeigt, verfolgt Peek das Paket dort im Takt des Zustellers weiter und behält die Historie von CTT Express bei. Die Scantexte bleiben im spanischen Original (`En reparto` oder `Disponible en Punto Collectt Express` ohne Namen und Anschrift des Punkts), Rücksendungen ab `En devolución` markiert Peek bis `Devuelto a remitente` als Problem, und auf `peektracker.com` sowie in den Apps für iPhone und Android schließt der Verlauf mit „Uhrzeiten in deiner Zeitzone: …“.

## Häufige Fragen zur CTT Express Sendungsverfolgung

### Was ist CTT Express?

CTT Express ist die spanische Expresspaket-Tochter der portugiesischen CTT-Gruppe, entstanden 2019 durch die Umbenennung von Tourline Express. Sie ist nicht mit [CTT](carrier:ctt), der portugiesischen Post, identisch, gehört aber zur selben Gruppe und arbeitet auf der Iberischen Halbinsel seit Mai 2026 im Rahmen eines Gemeinschaftsunternehmens von CTT Expresso mit DHL eCommerce zusammen. Zugestellt wird in ganz Spanien (Festland, Balearen, Kanaren, Ceuta und Melilla) sowie in Portugal.

### Liefert CTT Express auch am Samstag?

In Spanien nur während der Weihnachtssaison. Im übrigen Jahr erfolgen Zustellungen von Montag bis Freitag, üblicherweise zwischen 9:00 und 19:00 Uhr.

### Gibt es die CTT Express Sendungsverfolgung auf Deutsch oder Englisch?

Nein. Das offizielle Tracking-Portal ist ausschließlich auf Spanisch verfügbar; die Übersichtstabelle oben übersetzt alle gängigen Statusmeldungen und Störungsgründe.

### Hat CTT Express einen Live-Chat oder WhatsApp-Support?

Auf der offiziellen Website gibt es weder Live-Chat noch WhatsApp: Erreichbar ist der Kundenservice telefonisch (`900 841 352` in Spanien bzw. `+34 916 602 200` aus dem Ausland), per E-Mail (`ccaweb@cttexpress.com`) und über das Kontaktformular.

### Kann ich CTT Express ohne Sendungsnummer verfolgen?

Nein. Der Localizador besitzt nur ein einziges Eingabefeld für die Sendungsnummer, und auch das Kontaktformular setzt diese Nummer voraus. Prüfen Sie Ihre SMS und Bestätigungs-E-Mails oder fragen Sie beim versendenden Shop nach.

:::sources
- [CTT Express: Localizador de envíos](https://www.cttexpress.com/localizador-de-envios/) – Sendungsverfolgung, Zustelländerung und Zustell-E-Mail
- [CTT Express: Localizador de envios (Portugiesisch)](https://www.cttexpress.com/localizador-de-envios/?language_code=pt) – Weiterleitung zu „Seguir objeto“ auf `ctt.pt`
- [CTT Express: Seguimiento de envíos](https://shipping-tracking.production.cloud2.cttexpress.com/) – rein spanisches Tracking, Prozessschritte, Störungsgründe, einzelnes Suchfeld und `PT`-Beispiel
- [CTT Express: Empresa de Paquetería y Mensajería](https://www.cttexpress.com/) – Abdeckung, Collectt-Netz und Sprachumschalter
- [CTT Express: Quiénes somos](https://www.cttexpress.com/nosotros/quienes-somos) – CTT-Gruppe und Umbenennung von Tourline Express 2019
- [CTT Express: CTT Express y DHL unen fuerzas](https://www.cttexpress.com/nosotros/ctt-express-y-dhl-unen-fuerzas) – strategische Allianz auf der Iberischen Halbinsel mit DHL eCommerce
- [CTT Express: Pressemitteilung CTT Expresso und DHL eCommerce](https://www.cttexpress.com/zona-de-prensa/notas-de-prensa/ctt-expresso-y-dhl-ecommerce-sellan-un-hito-en-su-alianza-para-construir-la-red-de-paqueteria-mas-completa-de-la-peninsula-iberica) – Joint Venture vom Mai 2026 und wechselseitige 25-%-Beteiligung
- [CTT Express: Envíos urgentes 24 horas](https://www.cttexpress.com/envios/24-h) – Expressdienste `CTT 24h`, `CTT e24h`, `CTT 14h` und `CTT 10h`
- [CTT Express: Envíos 48 horas](https://www.cttexpress.com/envios/48-h) – Tarif `CTT 48h` inklusive Balearen, Kanaren, Ceuta, Melilla, Madeira und Azoren
- [CTT Express: Shipment Number Calculation and Package Codes (PDF)](https://transfer.cttexpress.com/IT/export/integra/Shipment_Number_Calculation_and_Package_Codes_of_CttExpress.pdf) – Aufbau der 22- und 25-stelligen Nummern sowie Paketzähler `001`
- [CTT Express: FAQs sobre los estados de envío](https://www.cttexpress.com/preguntas-frecuentes/estados-envio) – Statusmeldungen, 24-Stunden-Hinweis, zugestellt-aber-nicht-erhalten und 7-Tage-Schadensfrist
- [CTT Express: Preguntas frecuentes de incidencias](https://www.cttexpress.com/preguntas-frecuentes/incidencias) – zwei Zustellversuche, Verspätungen und Reklamationen
- [CTT Express: Horarios de reparto](https://www.cttexpress.com/preguntas-frecuentes/horarios) – Zustellfenster 9:00–19:00 Uhr, Samstage zu Weihnachten und Fahreranruf
- [CTT Express: Cambiar dirección de entrega](https://www.cttexpress.com/cambiar-direccion-entrega/) – kostenlose Änderungsmöglichkeiten vor `En reparto`
- [CTT Express: Servicios incluidos y adicionales](https://www.cttexpress.com/servicios/incluidos-y-adicionales) – Zustellversuche, Collectt-Maßgrenzen (20 kg, 160 cm) und Zusatzschutz bis 2.000 €
- [CTT Express: Recoge y devuelve tus paquetes en nuestros puntos](https://www.cttexpress.com/red-collectt-express/envio-y-recogida) – über 20.000 Collectt-Punkte (10 Tage) und Locky-Schließfächer (5 Tage)
- [CTT Express: Preguntas frecuentes Puntos Collectt Express](https://www.cttexpress.com/preguntas-frecuentes/puntos-collectt-express) – PIN-Abholung und kostenlose Umleitung
- [CTT Express: Qué significa „aceptado por la última milla“](https://www.cttexpress.com/blog/logistica/que-significa-aceptado-ultima-milla) – Bedeutung der Sortier- und Zustellstatus
- [CTT Express: Pressemitteilung zu Sendungsfragen](https://www.cttexpress.com/zona-de-prensa/notas-de-prensa/como-localizo-mi-paquete-con-ctt-express-y-otras-preguntas-frecuentes-sobre-envios) – Laufzeiten internationaler Sendungen
- [CTT Express: Servicio de importación de paquetería](https://www.cttexpress.com/servicios/importacion/) – Nicht-EU-Verzollung in durchschnittlich 12 Stunden (über 300.000 Pakete/Tag) und Zustellung in 24 oder 48 Stunden
- [CTT Express: ¿Qué hacer si mi paquete ha quedado retenido en aduanas?](https://www.cttexpress.com/blog/expansion-internacional/que-hacer-si-mi-paquete-ha-quedado-retenido-en-aduanas) – Zollstopps, Kanaren und Rufnummer `900 110 898`
- [Europäische Kommission: EU-Zollreform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_de) – 3 € Zoll pro Warenart bis 150 € seit 1. Juli 2026 und 2 € EU-Abfertigungsgebühr seit 1. November 2026
- [Europäische Kommission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – Erhebung von 3 € Zoll und 2 € Gebühr jeweils pro Warenart unabhängig vom Wert
- [CTT Express: Cómo contactar con CTT Express](https://www.cttexpress.com/preguntas-frecuentes/como-contactar-con-ctt) – Telefonnummern, Zeiten, E-Mail, Antwort binnen 24 Arbeitsstunden und `807`-Warnung
- [CTT Express: Contacto y Atención al Cliente](https://www.cttexpress.com/contacto-atc) – Kontaktformular mit Pflichtfeld Sendungsnummer
- [CTT Express: Qué es Phishing](https://www.cttexpress.com/blog/actualidad/como-evitar-phishing) – Warnung vor gefälschten SMS und E-Mails
- [CTT: Encontrar o código de envio](https://www.ctt.pt/ajuda/empresas/seguir-ou-alterar-entrega/seguir/encontrar-o-codigo-de-envio) – 13-stellige `PT`-Codes sowie 22- und 25-stellige Nummern
- [CTT: Condições Gerais do Serviço Postal Universal](https://www.ctt.pt/home/servico-postal-universal/condicoes-gerais-de-acesso-e-utilizacao-do-servico-universal) – CTT als Universaldienstleister in Portugal
- [CTT: Empresas do grupo](https://www.ctt.pt/grupo-ctt/a-empresa/quem-somos/empresas-do-grupo/) – CTT Express und CTT Expresso innerhalb der CTT-Gruppe
- [CTT: Prazo para levantar](https://www.ctt.pt/ajuda/particulares/receber/levantar-correspondencia-ou-encomendas-/prazo-para-levantar) – Abholfristen in Portugal (10 Werktage aus Spanien, 5 Tage Locky)
- [CTT: Collectt](https://www.ctt.pt/particulares/receber/locais-de-entrega-alternativos/collectt) – Collectt-Netz in Portugal
- [CTT: Entrega não conseguida](https://www.ctt.pt/ajuda/particulares/receber/problemas-na-entrega/entrega-nao-conseguida) – Vorgehen nach Fehlversuch in Portugal
- [CTT: Condições gerais de transporte de Encomendas Expresso](https://www.ctt.pt/transversais/condicoes-gerais-transporte-encomendas-expresso) – Reklamationsfristen von 8 und 30 Tagen
- [CTT: Linhas de Apoio](https://www.ctt.pt/ajuda/particulares/apoio-ao-cliente/contactos/linhas-de-apoio) – Kundenservice und Zeiten in Portugal
- [CTT: Alertas de Phishing](https://www.ctt.pt/transversais/alertas-de-phishing) – gefälschte CTT-Expresso-SMS
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Nummernerkennung, `Recogerán en Delegación` und Übergabe an CTT Portugal in Peek
:::
