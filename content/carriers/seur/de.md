---
title: SEUR Sendungsverfolgung: Nummer, Status und Pickup
description: SEUR-Paket aus Spanien nach Deutschland, Österreich oder in die Schweiz verfolgen: Nummernformate, spanische Statusmeldungen, Pickup-Punkte und Hilfe.
slug: seur-sendungsverfolgung
published: 2026-11-17
updated: 2026-11-17
---

SEUR ist ein spanischer Paket- und Expressdienst der Geopost-Gruppe, zu der auch [DPD Deutschland](carrier:dpd-de), DPD Österreich und [DPD Schweiz](carrier:dpd-ch) gehören. Du verfolgst deine Sendung ohne Konto unter miSEUR auf seur.com, per SEUR-Link oder oben im Suchfeld.

Bei Bestellungen aus Spanien nach Deutschland, Österreich oder in die Schweiz übernimmt SEUR den Start in Spanien (mit spanischen Statusmeldungen) und übergibt das Paket im Geopost-Netz an die jeweilige DPD-Landesgesellschaft.

## SEUR-Sendungsnummer: Formate und Fundort

Meist hat eine SEUR-Nummer 14 oder 21 Ziffern; insgesamt funktionieren fünf Formate (alle Beispiele sind erfunden):

| Beispiel (erfunden) | Bedeutung |
| --- | --- |
| `48291057364928` | 14 Ziffern – Standardformat im Geopost-Netz |
| `482910573649281736492` | 21 Ziffern |
| `4829105` | 7 Ziffern – kurze Versandnummer, die SEUR erneut vergibt |
| `2026150848291057364928` | 22 Ziffern mit Datum am Anfang (Jahr, Tag, Monat): Absenderreferenz |
| `REC482910573649` | `REC` plus 12 Ziffern: Abholreferenz |

Die Nummer steht in der Versandmail oder im Kundenkonto des Shops; sonst hilft [Sendungsnummer finden](guide:find-tracking-number). Da 14 Ziffern unter anderem auch zu [DPD Deutschland](carrier:dpd-de), [DPD Schweiz](carrier:dpd-ch), [Hermes](carrier:hermes-de), [Chronopost](carrier:chronopost) und [BRT](carrier:brt) passen (und 22 Ziffern zu [Österreichische Post](carrier:austrian-post), [USPS](carrier:usps) oder Estafeta), prüfe den Paketdienst in der Shop-Mail.

## Was die spanischen Statusmeldungen bedeuten

SEUR schreibt seine Scans in spanischen Großbuchstaben:

| Status bei SEUR | Was das für dich heißt |
| --- | --- |
| `EL ENVÍO HA SIDO REGISTRADO.` | Sendung angemeldet; SEUR hat das Paket noch nicht. |
| `LA RECOGIDA HA SIDO REGISTRADA.` | Abholung beim Absender gebucht; Paket noch nicht bei SEUR. |
| `LA RECOGIDA HA SIDO CONFIRMADA POR EL REPATIDOR.` | Fahrer hat die Abholung auf der Tour (*REPATIDOR* schreibt SEUR so). |
| `RECOGIDA NO REALIZADA: CLIENTE CERRADO O AUSENTE. SE PUEDE REPROGRAMAR UNA NUEVA FECHA DE RECOGIDA.` | Abholung beim Absender gescheitert, kein Zustellversuch bei dir: Peek zeigt „Etikett erstellt“. |
| `LA RECOGIDA HA SIDO REALIZADA.` | Beim Absender abgeholt: SEUR hat das Paket. |
| `EL ENVÍO HA SIDO DEPOSITADO EN UN PUNTO SEUR PICKUP.` | Vom Absender im SEUR-Pickup-Punkt abgegeben. |
| `EL ENVÍO HA SIDO RECEPCIONADO EN LAS INSTALACIONES DE ORIGEN DE SEUR. COMIENZA EL TRÁNSITO A DESTINO` | Im Startdepot eingetroffen; Weitertransport läuft. |
| `EL ENVÍO ESTÁ EN TRÁNSITO. PRÓXIMAMENTE LLEGARÁ A LAS INSTALACIONES DE DESTINO.` | Unterwegs zum Zustelldepot deiner Region. |
| `EN ENVÍO HA SIDO RECEPCIONADO EN LAS INSTALACIONES DE DESTINO. PRONTO SALDRÁ A REPARTO.` | Im Zieldepot (*EN ENVÍO* schreibt SEUR so), noch nicht im Fahrzeug: Peek zeigt „Unterwegs“. |
| `EL ENVÍO HA LLEGADO A LA ADUANA DE DESTINO. INICIAMOS LOS TRÁMITES DE DESPACHO ADUANERO.` | Beim Zoll im Zielland eingetroffen. |
| `INCIDENCIA EN ADUANAS: FALTA FACTURA. CONTACTA CON SEUR DESDE EL ÁREA DE SOPORTE.` | Rechnung fehlt beim Zoll: beim Shop anfordern und über miSEUR einreichen. |
| `LA MERCANCÍA HA SIDO DESPACHADA Y PRÓXIMAMENTE SALDRÁ A REPARTO.` | Vom Zoll freigegeben; geht bald in Zustellung. |
| `EL ENVÍO SERÁ ENTREGADO EN LA FECHA CONCERTADA. ACTUALIZAREMOS LA SITUACIÓN.` | Vereinbarter Liefertag hinterlegt. |
| `EL ENVÍO HA SUFRIDO UN RETRASO Y ES POSIBLE QUE SE DEMORE LA ENTREGA. DISCULPA LAS MOLESTIAS.` | Verzögerung ohne Grund oder neue Dauer. |
| `EL ENVÍO ESTÁ EN REPARTO.` | Heute in Zustellung (`INTERNACIONAL` bei Auslandssendungen). |
| `EL ENVÍO NO SE HA ENTREGADO POR AUSENCIA O CIERRE. SE PUEDE REPROGRAMAR UNA NUEVA ENTREGA.` | Niemand da oder geschlossen; neuer Termin buchbar. |
| `EL ENVÍO NO SE HA ENTREGADO PORQUE LA DIRECCIÓN DE ENTREGA ES INCORRECTA.` | Adresse falsch (oder `INCOMPLETA O ERRÓNEA`): Peek zeigt „Zustellversuch“. |
| `EL ENVÍO NO SE HA ENTREGADO PORQUE NO ES LA DIRECCIÓN ACTUAL DEL DESTINATARIO.` | Nicht mehr die aktuelle Empfängeradresse: Peek zeigt „Problem gemeldet“. |
| `NO SE HA PODIDO DEPOSITAR EL ENVÍO EN EL PUNTO SOLICITADO` | Gewünschter Pickup-Punkt konnte das Paket nicht annehmen. |
| `EL ENVÍO ESTÁ DISPONIBLE PARA RECOGER EN EL PUNTO SEUR PICKUP.` | Abholbereit im SEUR-Pickup-Punkt. |
| `EL ENVÍO ESTÁ ENTREGADO.` | Zugestellt (`ENTREGADO A UN VECINO.` bei Nachbarschaftsabgabe). |

Nach Abholung im spanischen Pickup-Shop steht `EL DESTINATARIO HA RETIRADO EL ENVÍO DE LA TIENDA SEUR PICKUP SELECCIONADA.`

## Zustellung in Spanien, Deutschland, Österreich und der Schweiz

**In Spanien.** SEUR betreibt tausende Pickup-Shops und Locker auf seur.com. Geht ein Paket an einen Shop, meldet SEUR `LOS DATOS DEL ENVÍO HAN SIDO MODIFICADOS Y SE ENTREGARÁ EN UN PUNTO SEUR PICKUP.` ; klappt ein Locker nicht, verlangt `EL ENVÍO NO HA SIDO DEPOSITADO EN EL LOCKER SELECCIONADO. SE DEBE ELEGIR OTRO PUNTO SEUR PICKUP.` die Wahl eines anderen Punkts.

**Nach Deutschland, Österreich und in die Schweiz.** Mit SEUR Classic nennt Packlink 2 bis 4 Tage in wichtige europäische Städte (ohne festes Zeitfenster außer bei Zeit-Express):

- **Deutschland ([DPD Deutschland](carrier:dpd-de)):** 10.000 Paketshops; Abholfrist laut Hilfeseite 7 Kalendertage im Paketshop und 3 an der Paketstation gegen amtlichen Lichtbildausweis.
- **Österreich (DPD Österreich):** Zustellung im Land, Depots auf dpd.com/at.
- **Schweiz ([DPD Schweiz](carrier:dpd-ch)):** Abholung an einer DPD Pickup Location mit amtlichem Lichtbildausweis (oder Vollmacht und Ausweis der abholenden Person).

## Zoll: Schweiz sowie Kanaren, Ceuta und Melilla

Sendungen vom spanischen Festland oder den Balearen nach Deutschland (ohne Helgoland und Büsingen) oder Österreich sind innerhalb der EU zollfrei. Zoll fällt in zwei Fällen an:

- **Schweiz:** Nicht Teil der EU-Zollunion; laut BAZG können Einfuhrsteuer, Zölle und Verzollungsgebühren des Zustellers anfallen (Ansprechpartner für Gebühren und Status ist der Paketdienst).
- **Kanarische Inseln, Ceuta, Melilla oder Drittländer:** Die Kanaren liegen außerhalb des EU-Mehrwertsteuergebiets, Ceuta und Melilla außerhalb des EU-Zoll- und Mehrwertsteuergebiets. In der EU gilt seit dem 1. Juli 2026 bei Nicht-EU-Sendungen bis 150 € ein vorläufiger Zollsatz von 3 € pro Artikel und seit dem 1. November 2026 zusätzlich eine EU-Bearbeitungsgebühr von 2 € pro unterschiedlichem Artikel (weitergabefähig). Mehr unter [Paket beim Zoll](guide:customs).

## Paket nicht gefunden, verspätet oder zugestellt ohne Erhalt

**Nicht gefunden.** SEUR liefert für ganz neue Sendungen dieselbe Fehlermeldung wie für unbekannte Nummern – frag nach einem Werktag beim Absender nach.

**Verspätet oder nicht erhalten.** Nach dem Liefertermin hilft [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating). Laut Your Europe haftet bei EU-Onlinekäufen der Händler bis zur Übergabe. Bei `ENTREGADO A UN VECINO.` frag zuerst Nachbarn, sonst siehe [Paket zugestellt, aber nicht erhalten](guide:delivered-not-received).

## Kundendienst und Kontakte vor Ort

SEUR erreichst du im Supportbereich von miSEUR auf seur.com. Lokal im Geopost-Netz:

- **Deutschland ([DPD Deutschland](carrier:dpd-de)):** `06021-150415` (international `+49 6021 150 415`), `info@dpd.de` oder [Kontaktformular](https://www.dpd.com/de/de/support/kontakt/).
- **Österreich (DPD Österreich):** [Kontakt- und Depotseite](https://www.dpd.com/at/en/support/contact/).
- **Schweiz ([DPD Schweiz](carrier:dpd-ch)):** `058 311 13 13` (Mo–Fr 8:00–18:00 Uhr, max. CHF 0.08/Min.) oder [Supportcenter](https://www.dpd.com/ch/de/supportcenter/anliegen-schildern/).

> Im Februar 2021 warnte Spaniens Cybersicherheitsinstitut INCIBE vor gefälschten SEUR-SMS, die per Link eine kleine Zahlung forderten, um Kartendaten zu stehlen: Öffne den Link nicht, gib SEURs Adresse selbst im Browser ein und informiere sofort deine Bank, falls du Kartendaten eingetragen hast.

## SEUR-Pakete mit Peek verfolgen

[Peek](/) fragt 21-stellige Nummern und `REC`-Codes direkt bei SEUR ab (außer `000` an Stelle 13 bis 15 für Australia Post: tippe auf „Ändern“ und wähle SEUR unter „Passt zu dieser Nummer“). Bei 22 Ziffern fragt Peek SEUR und [Österreichische Post](carrier:austrian-post) vor Tracking-Diensten und [USPS](carrier:usps). Bei 14 Ziffern prüft Peek in Deutschland erst [Hermes](carrier:hermes-de), [DPD Deutschland](carrier:dpd-de), DPD Belgium, [DPD Schweiz](carrier:dpd-ch) und DPD Niederlande, dann [Chronopost](carrier:chronopost), SEUR, [BRT](carrier:brt), DPD UK und DPD Portugal. Nach „Tracking-Dienste werden geprüft…“ erscheint „SEUR hat dieses Paket.“, wenn nur SEUR antwortet; sonst wählst du SEUR über „Ändern“ (oder „Paketdienst ändern“) – ebenso bei 7-stelligen Nummern, die SEUR wiederverwendet. Links mit `tracking=` gehen ohne Postleitzahl.

Peek zeigt Status, Verlauf und Gewicht, ohne Karte mangels Ortsangaben. Spanische Texte bleiben erhalten, nur Zustellungen heißen `Delivered` („Zugestellt“); Verspätungsmeldungen ändern die Phase nicht. Paketanzahl, Absender, Empfänger, PIN und QR-Code verwirft Peek, liest Zeiten als Madrider Zeit („Zeiten in deiner Zeitzone: …“) und verfolgt Mehrpaketsendungen über andere Tracking-Dienste.

Peek prüft bis zu alle 10 Minuten, solange du das Paket geöffnet hast oder am Zustelltag Benachrichtigungen eingeschaltet sind, sonst stündlich (auch nach zwei Tagen ohne Neuigkeiten oder nach 12 Stunden auf „Etikett erstellt“ oder „Beim Paketdienst eingegangen“). Bei „In Zustellung“ prüft Peek ab 6:00 Uhr Madrider Zeit bis zu alle 2 Minuten, solange SEUR direkt antwortet; über reine Tracking-Dienste stündlich, nach einem ruhigen Tag alle 3 Stunden und am Zustelltag bis zu alle 15 Minuten, solange du das Paket geöffnet hast oder Benachrichtigungen eingeschaltet sind („Jeden Scan“, „Wichtige Schritte“, „Nur die Zustellung“).

## Häufige Fragen zur SEUR Sendungsverfolgung

### Warum findet SEUR meine Nummer nicht?

SEUR gibt für frisch angelegte Sendungen dieselbe Antwort wie für unbekannte Nummern. Bleibt es dabei, frag beim Händler nach der Übergabe.

### Wie lange dauert der Versand mit SEUR?

Laut Packlink: 24 Werktagsstunden auf dem Festland mit SEUR 24, 48 bis 72 Stunden auf die Kanaren (72 bis 96 nach La Gomera und El Hierro) und 2 bis 4 Tage in europäische Großstädte mit SEUR Classic.

### Was heißt SEURs Verspätungsmeldung?

Die Lieferung kann sich verzögern, ohne Grund oder neuen Termin; Peek behält die bisherige Phase bei.

### Gehören SEUR und DPD zusammen?

SEUR ist die spanische Marke der Geopost-Gruppe, neben [DPD Deutschland](carrier:dpd-de), DPD Österreich, [DPD Schweiz](carrier:dpd-ch) und [Chronopost](carrier:chronopost).

:::sources
- [Geopost: Who we are](https://www.geopost.com/en/about-us/who-we-are/) – Geopost-Gruppe
- [Geopost: SEUR](https://www.geopost.com/en/about-us/our-companies-and-partners/seur/) – Pickup-Netz
- [Packlink: SEUR](https://www.packlink.es/seur/) – Laufzeiten
- [Your Europe: Versand und Lieferung](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_de.htm) – Verkäuferhaftung
- [INCIBE: Gefälschte SEUR-SMS](https://www.incibe.es/ciudadania/avisos/circulan-sms-fraudulentos-que-suplantan-seur-que-no-te-enganen) – Phishing-Warnung
- [DPD Deutschland: Startseite](https://www.dpd.com/de/de/) – 10.000 Paketshops
- [DPD Deutschland: Paket abholen](https://www.dpd.com/de/de/support/empfaenger/paket-abholen/) – 7 und 3 Tage
- [DPD Deutschland: Ohne Paketinfokarte](https://www.dpd.com/de/de/faq/kann-ich-mein-paket-ohne-paketinfokarte-abholen/) – Lichtbildausweis
- [DPD Deutschland: Kontakt](https://www.dpd.com/de/de/support/kontakt/) – 06021-150415, info@dpd.de
- [DPD Deutschland: Delivered but not here](https://www.dpd.com/de/en/faq/my-parcel-was-delivered-but-it-is-not-here-where-is-it/) – +49 6021 150 415
- [DPD Österreich: Kontakt](https://www.dpd.com/at/en/support/contact/) – Depots
- [DPD Schweiz: Pickup Location](https://www.dpd.com/ch/de/dpd-pickup-location/) – Ausweis
- [DPD Schweiz: Kontakt](https://www.dpd.com/ch/de/supportcenter/anliegen-schildern/) – 058 311 13 13
- [BAZG: Empfangen von Paketen](https://www.bazg.admin.ch/de/empfangen-von-briefen-und-paketen) – Schweizer Zoll
- [BAZG: Verzollungskosten](https://www.bazg.admin.ch/de/verzollungskosten-und-paketverfolgung) – Ansprechpartner
- [EU-Kommission: Territorial Scope](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Kanaren, Ceuta, Melilla
- [EU-Kommission: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 3 € und 2 €
- [Revenue: Union Handling Fee](https://www.revenue.ie/en/corporate/press-office/press-releases/2026/pr-092926-handling-fee.aspx) – Weitergabe
- [Universal Parcel Scraper: SEUR](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/seur) – was Peek liest
:::
