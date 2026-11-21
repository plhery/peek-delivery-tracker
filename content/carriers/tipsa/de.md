---
title: TIPSA Sendungsverfolgung: 22-stellige Nummer und Status
description: TIPSA-Paket in Spanien oder Portugal verfolgen: 22-stellige Nummer, Bedeutung der spanischen Status, verpasste Zustellung, Filialen und Kontakt.
slug: tipsa-sendungsverfolgung
published: 2026-11-21
updated: 2026-11-21
---

TIPSA ist ein spanischer Express-Paketdienst aus dem Geopost-Verbund mit Filialen in Spanien, Portugal und Andorra. Verfolge deine Sendung mit der 22-stelligen Nummer im [TIPSA Delivery Locator](https://www.tip-sa.com/en/delivery-locator) oder füge sie oben ein. Die Nummer kommt vom Shop oder Absender, meist per Versandmail oder SMS. Hast du sie nicht, frage den Shop oder die für dein Gebiet zuständige TIPSA-Agentur; du findest sie per Postleitzahl im [Agenturfinder](https://www.tip-sa.com/es/agencias-de-transporte). Mit dieser Agentur vereinbarst du auch die Übergabe nach einem `Ausente` oder eine Selbstabholung; bei Verlust oder Schaden melde dich beim Shop.

## TIPSA-Sendungsnummer: So sieht sie aus

Der TIPSA-Locator verlangt die 22-stellige Sendungsnummer und markiert auch die Zielpostleitzahl als Pflichtfeld. Die Nummer besteht aus zwei Agenturcodes und dem Frachtbrief (`albarán`):

:::anatomy 028145 028145 4829105736
- 028145 | Erster Agenturcode: eine am Versand beteiligte TIPSA-Agentur
- 028145 | Zweiter Agenturcode: eine weitere beteiligte Agentur
- 4829105736 | Frachtbrief (albarán): die eigentliche Sendungsnummer
:::

| Format (erfunden) | Was es ist |
| --- | --- |
| `0281450281454829105736` | Die vollständige 22-stellige TIPSA-Nummer. Diese brauchst du. |
| `028145 028145 4829105736` | Dieselbe Nummer mit Leerzeichen. Peek liest sie; im TIPSA-Locator passen nur 22 Zeichen ins Feld, gib sie dort also ohne Leerzeichen ein. |
| `4829105736` | Nur der 10-stellige Frachtbrief. Weder der TIPSA-Locator noch Peek nehmen ihn als TIPSA-Nummer an. |

Shops können über TIPSA E-Mails und SMS beim Versand, bei Ankunft in der Agentur, bei Zwischenfällen und nach Zustellung verschicken; ihre Tracking-Links führen zur TIPSA-Sendungsseite. Ohne die 22-stellige Nummer sucht der Locator auch nach der Referenz, fordert dann aber eine Kennung (`Agency + Client`), die du vom Absender brauchst. Weitere Hinweise bietet [Sendungsnummer finden](guide:find-tracking-number).

22 Ziffern passen auch zu Austrian Post, [USPS](carrier:usps) und Estafeta. Eine Nummer, die mit `00` beginnt und deren zweiter Agenturcode ebenfalls mit `00` startet, kann zu [CTT Express](carrier:ctt-express) gehören, einem weiteren spanischen Paketdienst: Prüfe in der Versandmail, welcher Dienst genannt wird. Es funktioniert immer nur die volle 22-stellige Nummer, nicht die letzten 10 Ziffern allein.

## Bedeutung der TIPSA-Tracking-Status

Die Sendungsdetailseite von TIPSA ist ausschließlich auf Spanisch, selbst wenn du sie über die englische Website öffnest. Oben zeigt sie eine kurze Leiste mit Schritten wie `Documentado`, `En camino`, `En reparto` und `Entregado`; darunter listet eine Tabelle jeden Scan mit Datum, Uhrzeit und Ort. Die üblichen Meldungen in Reihenfolge:

| TIPSA-Status | Was das für dich bedeutet |
| --- | --- |
| `PENDIENTE DE ENTREGAR A TIPSA` | Der Shop hat die Sendung angemeldet, TIPSA hat das Paket aber noch nicht: ein [Etikett-erstellt-Status](guide:tracking-statuses). |
| `TRANSITO` | Unterwegs zwischen Agenturen und Hubs von TIPSA. |
| `LECTURA EN HUB MADRID` | In einem TIPSA-Hub gescannt; der Name wechselt je nach Standort (`LECTURA EN HUB BARCELONA`, `LECTURA EN HUB BARBERA`). |
| `Pendiente de llegada` | Noch nicht bei der ausliefernden Agentur eingetroffen. |
| `LEIDO EN DESTINO` | Am Zielort gescannt. |
| `LECTURA EN AGENCIA DESTINO` | Von der zustellenden Agentur gescannt, gefolgt von deren Namen. |
| `REPARTO` | In Zustellung. |
| `Ausente` | Der Fahrer konnte niemanden erreichen und nicht zustellen. |
| `Faltan Datos / Datos incorrectos` | Adressangaben fehlen oder sind falsch; wird auf der Tour vor einem weiteren Versuch vermerkt. |
| `Cerrado` | Adresse bei der Zustellung verschlossen angetroffen, nach `REPARTO`. |
| `Solicitada en otra dirección` | Zustellung an eine andere Adresse wurde angefordert, das Paket wird umgeleitet. |
| `DISPONIBLE` oder `Pendiente de recoger en Tipsa` | Nach fehlgeschlagener Zustellung liegt das Paket in der Agentur zur Abholung bereit. |
| `Devolver a Origen` | Rücksendung an den Absender. |
| `ENTREGADO` | Zugestellt. |

Bei `Ausente` blendet TIPSA einen eigenen Hinweis ein: Man konnte dich nicht erreichen, der nächste Zustellversuch erfolgt am nächsten Werktag, und du kannst die Zielagentur anrufen, um die Lieferung abzustimmen. Name und Adresse dieser Agentur stehen unter „AGENCIA DESTINO“.

## Verpasste Zustellung, Agenturen und Pickup-Punkte bei TIPSA

TIPSA liefert über sein eigenes Netz auf dem spanischen Festland und in Portugal, auf den Balearen und Kanaren sowie in Ceuta und Melilla und ins Ausland über DPDgroup. Nach einem `Ausente` folgt der zweite Versuch am nächsten Werktag; der Tarif für Shops umfasst zwei Zustellversuche, oder drei beim Premium-Service für Online-Shops.

**Abholung in der Agentur.** Wenn du nicht da bist oder das Paket lieber selbst abholst, liegt es laut TIPSA-FAQ 15 Tage ab Versanddatum (nicht ab Ankunft) in der Agentur nächst deiner Postleitzahl bereit. Danach geht es an den Absender zurück. Bringe deinen Ausweis (DNI) oder Reisepass mit; holt jemand anderes das Paket ab, braucht die Person eine Fotokopie deines Dokuments. Der [Agenturfinder](https://www.tip-sa.com/es/agencias-de-transporte) sucht nach Provinz oder Postleitzahl.

**Adresse ändern.** Laut TIPSA darf nur der Absender Lieferadresse oder Sendungsdaten ändern: Wende dich dafür an den Shop.

**Pickup-Punkte und Schließfächer.** TIPSA teilt sich mit [SEUR](carrier:seur) ein Netz aus Pickup-Shops und rund um die Uhr zugänglichen Schließfächern. Die meisten Shops öffnen mindestens sechs Tage die Woche, und die Abholfrist beträgt 7 Tage. Die Lieferung an einen Punkt muss der Shop beim Bestellen anbieten. Standorte findest du im [Pickup-Finder](https://aplicaciones.tip-sa.com/puntospickup/).

Manche Shops buchen auch Samstagszustellung oder eine Vorankündigung per SMS und E-Mail für den Ankunftstag. Mit dem Premium-Service lässt sich zudem ein einstündiges Zeitfenster wählen; da TIPSA online nicht erklärt, wo man es auswählt, frage die Zielagentur.

**Zahlung bei Übergabe.** Bei Nachnahme (`contrarreembolso`) kassiert TIPSA bei der Übergabe den vom Shop festgelegten Betrag bis 1.000 € pro Sendung; bei unfreiem Versand (`portes debidos`) zieht TIPSA am Zielort die Versandkosten ein. Ob bar oder per Karte gezahlt wird, nennt TIPSA nicht: Frage den Shop oder die Agentur. Da beides erst bei der Übergabe kassiert wird, passt eine SMS oder E-Mail „von TIPSA“, die zu einer Zahlung über einen Link auffordert, zu keinem dieser Fälle: Zahle nicht und prüfe die Nummer im Locator auf tip-sa.com. Mehr in [gefälschte Paket-SMS](guide:delivery-scam-texts).

## TIPSA-Paket verspätet, verloren oder zurückgeschickt

Die Seiten von TIPSA enthalten keine Anleitung für Sendungen, deren Status stehen bleibt. Rufe die auf der Sendungsseite genannte Zielagentur an oder frage den Shop; [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating) erklärt, wann Handlungsbedarf besteht.

Bei Verlust oder Schaden ist die TIPSA-FAQ knapp: Wende dich an den Absender. Der Shop ist Vertragspartner von TIPSA und stellt die Reklamation; für Empfänger veröffentlicht TIPSA weder Verfahren noch Fristen. Auch für ein als `ENTREGADO` markiertes, aber nicht erhaltenes Paket hat TIPSA keine eigene Seite: Informiere auch dann den Shop.

Ein Paket, das nicht binnen 15 Tagen ab Versand abgeholt wird, geht zurück an den Absender – kläre Erstattung oder Neuversand mit dem Shop.

## TIPSA-Telefonnummer und Kundenservice

Die auf der Kontaktseite und in der FAQ von TIPSA veröffentlichten Kanäle:

- **Telefon:** `902 10 10 47` oder `900 10 10 47` (die spanische FAQ nennt nur `900 10 10 47`). TIPSA nennt weder Servicezeiten noch Gesprächskosten.
- **Kontaktformular** unter [tip-sa.com/en/contact](https://www.tip-sa.com/en/contact) mit dem Grund „I have a problem with my shipment“ und deiner Sendungsnummer.
- **E-Mail:** `info@tip-sa.com`, in der FAQ für fehlende Sendungsnummern genannt.
- **Die Zielagentur**, auf der Sendungsseite aufgeführt, für Lieferabsprachen.

Für Portugal gibt es keine eigene Hotline: Die portugiesische Kontaktseite von TIPSA nennt dieselben zwei Rufnummern. Eine Seite zur mobilen App führt TIPSA unter [tip-sa.com/en/recursos/app](https://www.tip-sa.com/en/recursos/app).

## Ein TIPSA-Paket mit Peek verfolgen

[Peek](/) nimmt die 22-stellige Nummer mit oder ohne Leerzeichen an. Da mehrere Paketdienste diese Länge nutzen, fragt Peek die möglichen Dienste gleichzeitig ab, TIPSA zuerst: Du siehst „Peek fragt bei … nach …“ und danach „TIPSA hat dieses Paket.“, sobald TIPSA es kennt. Beginnen beide Agenturcodes mit `00` und entspricht der zweite einer bei CTT Express hinterlegten Filialkennung, ordnet Peek die Nummer direkt CTT Express zu; bei anderen `00…00…`-Nummern fragt Peek TIPSA (bevorzugt), Austrian Post, CTT Express, USPS und Estafeta ab. Hat dein Shop TIPSA genannt, tippe auf „Ändern“ und wähle TIPSA. Eine Postleitzahl ist nicht nötig.

Ein 10-stelliger Frachtbrief lässt sich nicht über TIPSA verfolgen, auch nicht nach „Ändern“ oder per Link. Peek schlägt TIPSA für 10 Ziffern nicht vor; wählst du TIPSA dennoch, meldet Peek, TIPSA „vergibt keine Nummern wie diese“, und fragt stattdessen Tracking-Dienste. Suche die vollständige 22-stellige Nummer oder den Tracking-Link in der Versandmail oder SMS des Shops. Peek liest `tip-sa.com`-Links mit `id=` auf `datos_env.php`, `datos_prestashop.php`, `datos.php` und der Startseite von `tip-sa.com`; Links auf den Locator oder `tipsa-dinapaq.com` werden nicht ausgewertet.

Peek zeigt Status, Verlauf mit dem Ort jedes Scans auf einer Karte, den Absendernamen (sofern von TIPSA nicht maskiert) und das Gewicht. Die Meldungen bleiben in TIPSAs Spanisch, und Peek ordnet sie seinen Stufen zu: `PENDIENTE DE ENTREGAR A TIPSA` („Etikett erstellt“), `REPARTO` („In Zustellung“, ebenso ein Agenturscan am selben Tag nach `REPARTO`), `Ausente`, `Cerrado` und `Faltan Datos / Datos incorrectos` („Zustellversuch“), `DISPONIBLE` und `Pendiente de recoger en Tipsa` („Abholbereit“), `Devolver a Origen` („Problem gemeldet“), `ENTREGADO` („Zugestellt“), und sonstige Scans („Unterwegs“). Peek liest alle Uhrzeiten als Madrider Zeit – auch bei portugiesischen Agenturen – und zeigt sie in deiner Zeitzone an; auf peektracker.com sowie in den iPhone- und Android-Apps endet der Verlauf mit „Uhrzeiten in deiner Zeitzone:“ und deren Namen. Empfänger, Adresse, Postleitzahlen, Referenz, Paketanzahl, Volumen und Zustellnachweis lässt Peek weg; ein Lieferdatum wird nicht gezeigt und eine Übergabe an andere Dienste nicht weiterverfolgt.

Solange du das Paket geöffnet hast oder am Zustelltag mit aktivierten Mitteilungen, und solange TIPSAs eigener Tracker antwortet, prüft Peek bis zu alle 10 Minuten, und ab 6 Uhr Madrider Zeit am Tag eines `REPARTO`-Scans bis zu alle 2 Minuten; sonst stündlich. Über Tracking-Dienste prüft Peek stündlich, am Zustelltag bis zu alle 15 Minuten, solange du es geöffnet hast oder Mitteilungen aktiv sind. Nach zwei Tagen ohne Neuigkeiten (nach 12 Stunden bei „Etikett erstellt“ oder „Beim Paketdienst eingegangen“) prüft Peek stündlich. Mitteilungen gibt es für „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“.

## Fragen zur TIPSA-Sendungsverfolgung

### Was ist TIPSA in Spanien?

TIPSA steht für Transporte Integral de Paquetería, S.A., einen spanischen Express-Paketdienst der Geopost-Gruppe. Die Unternehmensseite nennt über 330 Agenturen (die portugiesische Startseite über 300) und 13 Umschlagplätze, darunter Madrid, Barcelona und Valencia; Auslandssendungen laufen über DPDgroup.

### Wie lange dauert eine Lieferung mit TIPSA?

Das hängt vom vom Shop gebuchten Tarif ab. TIPSA bietet Shops Lieferungen in 24 Stunden (ein Zustellversuch am nächsten Werktag) oder binnen 48 Stunden; in Portugal 24 Stunden in die wichtigsten Städte und 24 bis 48 Stunden für übrige Ziele.

### Kann ich ein TIPSA-Paket mit 10 Ziffern verfolgen?

Nein: Der TIPSA-Locator verlangt die 22-stellige Nummer, und auch Peek nimmt 10 Ziffern nicht als TIPSA-Nummer an. Die 10 Ziffern sind nur der Frachtbrief am Ende – frage den Absender nach der vollständigen Nummer.

### Hat TIPSA Paketshops?

Ja, Pickup-Punkte in Geschäften und Schließfächern, gemeinsam genutzt mit SEUR, mit 7 Tagen Abholfrist. Diese Option steht nur zur Verfügung, wenn der Shop die Lieferung an einen Abholpunkt anbietet.

:::sources
- [TIPSA: Delivery locator](https://www.tip-sa.com/en/delivery-locator) – 22-stellige Nummer, Formular, Referenzsuche mit Kennung (Agentur + Kunde)
- [TIPSA: Frequently Asked Questions](https://www.tip-sa.com/en/recursos/faqs) – fehlende Nummer, 15 Tage Lagerung, Ausweis, Adressänderung, Verlust oder Schaden, Telefonnummern, E-Mail
- [TIPSA: Contact](https://www.tip-sa.com/en/contact) – Telefonnummern, Formulargründe
- [TIPSA: Collection points](https://www.tip-sa.com/en/services/collection-points) – Pickup-Netz
- [TIPSA: Pre-alert](https://www.tip-sa.com/en/services/pre-alert) – SMS- und E-Mail-Vorankündigung, Laufzeiten Portugal
- [TIPSA: TIPSA APP](https://www.tip-sa.com/en/recursos/app) – App-Seite
- [TIPSA: Consulta de Envíos](https://dinapaqweb.tipsa-dinapaq.com/dinapaqweb/detalle_envio.php) – rein spanische Sendungsseite, Statuswortlaute und Zielagentur
- [TIPSA: Localizar mi envío](https://www.tip-sa.com/es/localizacion-envios) – spanischer Locator, Telefonnummer in der Fußzeile
- [TIPSA: Preguntas frecuentes](https://www.tip-sa.com/es/recursos/faqs) – Netzabdeckung, Nummer `900 10 10 47`
- [TIPSA: Empresa de transporte urgente](https://www.tip-sa.com/es/tipsa) – Geopost, über 330 Agenturen, 13 Hubs, Schließfächer
- [TIPSA: Página inicial (Portugal)](https://www.tip-sa.com/pt) – über 300 Agenturen
- [TIPSA: FAQs eTIPSA](https://www.tip-sa.com/es/faqs-etipsa) – zwei Versuche, 24 und 48 Stunden, Zeitfenster bei eTIPSA Premium, Samstag, Shop-Benachrichtigungen
- [TIPSA: 360º eCommerce](https://www.tip-sa.com/es/envios-de-paquetes/360o-ecommerce) – drei Versuche mit Premium
- [TIPSA: Recogida en puntos de recogida](https://www.tip-sa.com/es/envios-de-paquetes/puntos-de-recogidas) – gemeinsames Netz mit SEUR, 7 Tage, Öffnungstage
- [TIPSA: Agencias de transporte](https://www.tip-sa.com/es/agencias-de-transporte) – Agenturfinder
- [TIPSA: Puntos Pickup](https://aplicaciones.tip-sa.com/puntospickup/) – Pickup-Finder
- [TIPSA: Contrarreembolso](https://www.tip-sa.com/es/envios-de-paquetes/reembolso) – Nachnahme bei Übergabe, bis 1.000 € pro Sendung
- [TIPSA: Portes debidos](https://www.tip-sa.com/es/envios-de-paquetes/portes-debidos) – Versandkosten am Zielort erhoben
- [TIPSA: Aviso legal](https://www.tip-sa.com/es/tipsa-aviso-legal) – Firmenname
- [TIPSA: Contacto (Portugal)](https://www.tip-sa.com/pt/contacto) – dieselben Rufnummern für Portugal
- [Universal Parcel Scraper: TIPSA](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/tipsa) – 22-stelliges Format, gelesene Links, Madrider Zeit, Statuszuordnung (`Cerrado`, `Devolver a Origen`, Agenturscan nach `REPARTO`), übernommene und verworfene Felder
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Überschneidung mit CTT Express, Austrian Post, USPS und Estafeta
:::
