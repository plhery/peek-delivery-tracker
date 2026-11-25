---
title: NACEX Sendungsverfolgung: Nummer, Status und Kontakt
description: NACEX-Paket aus Spanien oder Portugal verfolgen: Nummer 4829/10573649, spanische Statusmeldungen auf Deutsch, NACEX.shop-Punkte, Zoll und Kontakt.
slug: nacex-sendungsverfolgung
published: 2026-11-17
updated: 2026-11-17
---

NACEX ist ein Express-Paketdienst der Logista-Gruppe mit eigenem Franchisenetz in Spanien, Portugal, Andorra und den Benelux-Ländern sowie Straßen- und Luftversand ins übrige Europa. Im deutschsprachigen Raum begegnet dir NACEX meist nach einer Bestellung bei einem spanischen oder portugiesischen Onlineshop oder bei einer Lieferung an eine Ferien- oder Geschäftsadresse auf der Iberischen Halbinsel. Zur Sendungsverfolgung gibst du die Sendungsnummer (vier Ziffern, Schrägstrich, acht Ziffern) auf der [Startseite von NACEX](https://www.nacex.es/irHome.do) oder oben ins Suchfeld ein – allein mit einer Bestellnummer des Händlers oder deiner Postleitzahl kann NACEX kein Paket finden.

## NACEX-Sendungsnummer: Aufbau und häufige Verwechslungen

Das Suchfeld auf der Startseite zeigt das Muster `0000/00000000`, während das ausführliche Formular auf der [NACEX-Tracking-Seite](https://www.nacex.es/irSeguimiento.do) beide Teile getrennt abfragt (*Agencia* und *Albarán*). Anhand einer frei erfundenen Nummer:

:::anatomy 4829 / 10573649
- 4829 | Agentur: vierstelliger Code der absendenden NACEX-Franchise-Niederlassung
- / | Schrägstrich zwischen Agenturcode und Sendungsschein
- 10573649 | Lieferschein (*albarán*): achtstellige Nummer der eigentlichen Sendung
:::

NACEX gibt nicht an, in welcher Nachricht Empfänger die Nummer erhalten oder unter welchem Absendernamen SMS erscheinen; unser Ratgeber [Sendungsnummer finden](guide:find-tracking-number) zeigt die üblichen Stellen wie Versandbestätigung, Kundenkonto des Shops oder Paketlabel.

| Was dir vorliegt | Worum es sich handelt |
| --- | --- |
| `4829/10573649` | Vollständige NACEX-Sendungsnummer: direkt so verfolgen (Leerzeichen rund um den Schrägstrich wie `4829 / 10573649` werden beim direkten Einfügen ebenfalls akzeptiert). |
| `482910573649` | 12 Ziffern ohne Schrägstrich: vermutlich eine NACEX-Nummer, bei der der `/` nach der vierten Ziffer weggelassen wurde. Ohne Schrägstrich schlägt Peek nicht NACEX vor, sondern prüft andere Paketdienste mit [12-stelligen Nummern](guide:tracking-number-formats). Setze den `/` nach der vierten Ziffer ein. |
| `4829105736` | Bis zu 10 Ziffern: möglicherweise die auf dem Etikett gedruckte „alternative Referenz“ (*referencia alternativa*). Wo man sie eingibt, sagt NACEX nicht; Peek fragt NACEX damit nicht ab. |
| `3230 0000 0000 0000` | 16 Ziffern (oder 23 Ziffern) ohne Schrägstrich: kein NACEX-Format, sondern [Correos Express](carrier:correos-express) oder ein anderer Paketdienst wie [DHL eCommerce](carrier:dhl-ecommerce). |

Achte außerdem darauf, nicht den Buchstaben `O` statt der Ziffer `0` einzutippen – Buchstaben verhindern die Erkennung. Meldet das [externe NACEX-Suchformular](https://www.nacex.es/seguimientoFormularioExterno.do?intcli=0000/00000000) *„No existe ningún albarán introducido en el sistema cumpliendo los criterios especificados. Consulte con su agencia NACEX más cercana“* („Kein Lieferschein im System entspricht den angegebenen Kriterien. Wenden Sie sich an Ihre nächstgelegene NACEX-Agentur“), prüfe die vier Ziffern vor und die acht Ziffern nach dem Schrägstrich. Wurde das Paket gerade erst angekündigt, warte einige Stunden oder frag beim Shop nach.

## NACEX-Sendungsstatus: Spanische Meldungen auf Deutsch erklärt

Die Website `nacex.es` lässt sich oben zwar auf Deutsch, Englisch, Französisch, Portugiesisch, Katalanisch oder Spanisch umstellen, NACEX veröffentlicht jedoch keine offizielle Statusliste, und die von Peek gelesenen Statuszeilen sind auf Spanisch. In der typischen Reihenfolge bedeuten sie:

| Spanischer NACEX-Status | Bedeutung für deine Lieferung |
| --- | --- |
| `Notificado` | „Angekündigt“: elektronische Meldung der Sendung am Tag der Buchung, vor oder nach der Annahme; kein physischer Scan. |
| `Aceptada` | „Angenommen“: NACEX hat das Paket in einer Agentur übernommen (steht dahinter `WEB`, handelt es sich nur um eine Online-Anmeldung und das Paket wurde noch nicht übergeben). |
| `En tránsito` | „Unterwegs“: Transport zwischen NACEX-Franchise-Agenturen und Umschlagplattformen. |
| `En reparto` | „In Zustellung“: der Fahrer hat das Paket heute auf seiner Tour dabei. |
| `Ausente` | „Abwesend“: Zustellversuch ohne Erfolg, weil niemand angetroffen wurde. |
| `Gestión de servicio, posible demora` | „Servicebearbeitung, mögliche Verzögerung“: die Agentur klärt ein Problem im Ablauf (etwa eine abgebrochene Tour) und weist auf eine mögliche Verspätung hin. |
| `Solución de entrega concertada` | Nach einem Hindernis wurde ein neuer Zustelltermin vereinbart. |
| `Solución de entrega en punto` | Die Umleitung an einen NACEX-Abholpunkt wurde veranlasst – das Paket liegt dort aber noch nicht bereit. |
| `Cambio de dirección` | „Adressänderung“: Weiterleitung an eine geänderte Zustelladresse. |
| `Disponible en punto NACEX` | „Im NACEX-Punkt verfügbar“: jetzt abholbereit im Shop, Locker oder in der Agentur. |
| `Contacta con agencia` | „Agentur kontaktieren“: die zustellende Niederlassung bittet um Rückmeldung. |
| `Solucionado sin OK` | „Ohne OK abgeschlossen“: Vorgang ohne erfolgreiche Zustellung beendet, etwa durch Rücksendung an den Absender. |
| `Entregado` | „Zugestellt“: Sendung übergeben. |

Wichtig für Abholer: `Solución de entrega concertada` und `Solución de entrega en punto` sind bloße Bearbeitungsvermerke auf dem Weg – mach dich erst auf den Weg, wenn `Disponible en punto NACEX` erscheint. Taucht `Sin estado` („kein Status“) auf, ist das eine reine Verwaltungsnotiz (zum Beispiel über eine gesendete Benachrichtigung oder erreichte Zustellversuche), während der vorherige Status weiterhin gilt. Bei `Solucionado sin OK` solltest du direkt NACEX oder den Händler kontaktieren. Wie sich diese Phasen allgemein einordnen lassen, liest du unter [Bedeutung von Sendungsstatus](guide:tracking-statuses).

## Zustellung, Abwesenheit und NACEX.shop-Abholpunkte

**Versand nach Deutschland, Österreich und Europa:** Von der Iberischen Halbinsel und Portugal aus bedient NACEX das übrige Europa auf der Straße mit `Business Europa` (für Firmen) und `Ecommerce Europa` (Einzelpakete an Privatadressen bis 40 kg oder an Abholpunkte bis 20 kg, je nach Verfügbarkeit) sowie weltweit per Luftfracht (`Internacional exprés`) über internationale Partnerunternehmen.

**Wenn niemand zu Hause ist:** Laut den NACEX-Transportbedingungen 2026 hinterlässt der Kurier bei Privatadressen eine Benachrichtigung, um eine zweite und endgültige Zustellung (*segunda y definitiva entrega*) zu vereinbaren. Die NACEX-FAQ ergänzt die Einschränkung „sofern möglich“ (*siempre que sea posible*) und erklärt, dass sich die zuständige Agentur telefonisch meldet, falls deine Telefonnummer vorliegt. Weißt du vorab, dass du nicht da bist, informiere den Kundenservice oder die nächste Agentur.

**Vorab-Benachrichtigung (*Pre-Alerta*) und *Mi Entrega Preferida*:** Hat der Absender einen Tarif mit *Pre-Alerta* gebucht, schickt NACEX vorab eine E-Mail oder SMS. Über den enthaltenen Link öffnet sich *Mi Entrega Preferida* („Meine bevorzugte Zustellung“), wo du Datum oder Zeitfenster ändern oder ein Einzelpaket an einen NACEX-Punkt (Agentur oder NACEX.shop) umleiten kannst. Bei *Entrega con PIN* erfolgt die Übergabe gegen einen Sicherheitscode aus der Vorab-Nachricht.

**NACEX.shop und Smart Locker:** Das Netz umfasst über 4.000 Partnershops und Paketautomaten (*Smart Lockers*) in Spanien und Portugal, viele davon auch samstags und sonntags geöffnet. Ein dorthin adressiertes Einzelpaket steht am nächsten Werktag ab 09:00 Uhr – oder spätestens ab 12:00 Uhr bei später öffnenden Läden – nach einer Verfügbarkeitsnachricht bereit. Standorte findest du im [NACEX-Punktefinder](https://www.nacex.es/irCerca.do) (Reiter für Spanien und Andorra). Eine maximale Lagerfrist für NACEX.shop-Läden oder Locker veröffentlicht NACEX nicht.

**Abholung in einer NACEX-Agentur:** Muss eine Sendung in einer Franchise-Niederlassung abgeholt werden, lagert sie dort höchstens **15 Tage** und geht danach kostenpflichtig an den Absender zurück. Gib der Agentur vorher Bescheid; die Abholung ist montags bis freitags möglich (je nach Standort durchgehend 08:00–20:00 Uhr oder 08:00–14:00 und 16:00–20:00 Uhr). Holt jemand anderes dein Paket ab, verlangt NACEX eine **unterschriebene Vollmacht** sowie eine **Kopie deines Ausweises**.

**Samstage, Feiertage und Annahmeverweigerung:** Samstage, Sonntage und Feiertage gelten nicht als Werktage. Samstagszustellung (09:00–13:00 Uhr) ist ein kostenpflichtiger Zusatzservice in Orten mit NACEX-Franchise und im Umkreis von 15 km (auf die Kanaren und in Zollgebiete nur für Dokumente). Ein bereits unterwegs befindliches Paket lässt sich nicht stornieren, du kannst die Annahme aber an der Tür verweigern oder deiner Agentur vorab Bescheid geben.

## Zoll und Steuern: Deutschland, Österreich, Schweiz und Sondergebiete

Zwischen dem spanischen oder portugiesischen Festland (sowie den Balearen) und Deutschland oder Österreich bewegen sich Pakete innerhalb des EU-Mehrwertsteuergebiets ohne Zollkontrolle. Anders sieht es in zwei Fällen aus:

- **Lieferungen in die Schweiz:** Die Schweiz gehört nicht zur EU. Bei der Einfuhr fällt die Schweizer Mehrwertsteuer von **8,1 %** an (bzw. **2,6 %** ermäßigter Satz etwa für Bücher, Lebensmittel oder Medikamente). Laut Bundesamt für Zoll und Grenzsicherheit (BAZG) werden Zollabgaben und Steuerbeträge **bis 5 CHF** aus verwaltungsökonomischen Gründen nicht erhoben (das entspricht einer Bemessungsgrundlage von 62 CHF bei 8,1 % bzw. 193 CHF bei 2,6 %). Liegt der Betrag darüber, kommen Einfuhrabgaben und Verzollungskosten des Zustellers (etwa der [Schweizerischen Post](carrier:swiss-post) oder eines Kurierpartners) hinzu; mehr dazu im Leitfaden [Zoll und Einfuhrabgaben](guide:customs).
- **Kanarische Inseln, Ceuta, Melilla, Andorra und Gibraltar:** Warensendungen an diese Ziele laufen über den Zoll. NACEX verlangt dafür Handelsrechnungen bzw. eidesstattliche Erklärungen bei Privatsendungen und weist auf mögliche Verzögerungen durch die Zollabfertigung hin.

## Paket verspätet, verschwunden oder beschädigt

Offizielle Reklamationen wickelt NACEX mit seinem Auftraggeber ab – beim Onlinekauf also mit dem Shop, über dessen Franchise-Agentur auch etwaige Gutschriften laufen:

- **Sendung hängt fest oder kommt nicht an:** Laut NACEX-FAQ kannst du dich per E-Mail an `atencion.cliente@nacex.com` oder telefonisch unter `900 100 000` melden; eine Mindestwartezeit nennt NACEX nicht. Hilfreiche Schritte findest du unter [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating) sowie bei Status [`Entregado` ohne erhaltene Lieferung](guide:delivered-not-received).
- **Verspätung:** Der Auftraggeber kann innerhalb von **31 Kalendertagen** (*31 días naturales*) reklamieren, wenn die vereinbarte Laufzeit ohne gerechtfertigten Grund überschritten wurde (für Black Friday und Weihnachten weist NACEX auf mögliche Beeinträchtigungen bei Privatzustellungen hin).
- **Verlust, fehlender Inhalt oder Transportschaden:** Sichtbare Schäden oder Teilverluste vermerkst du schriftlich **direkt bei der Übergabe**, nicht erkennbare binnen 7 Kalendertagen (Artikel 60 des spanischen Landtransportgesetzes, *Ley 15/2009*). Reklamationen wegen Verlust, fehlendem Inhalt oder Beschädigung nimmt NACEX binnen **7 Kalendertagen** an (bei fehlendem Inhalt oder Schaden ab Lieferung), mit Fotos und Inhaltsrechnung.

Bei Verbraucherkäufen in der EU haftet laut *Your Europe* der Onlinehändler für die Ware bis zur physischen Übergabe (sofern du den Frachtführer nicht selbst unabhängig vom Shop beauftragt hast) und muss mangels anderer Vereinbarung binnen **30 Tagen** liefern. Melde Schäden oder Verluste daher immer sofort auch dem Verkäufer.

## NACEX Kontakt und Kundenservice

Auf seinen spanischen Kontaktseiten nennt NACEX folgende Kanäle (ohne veröffentlichte Servicezeiten und ohne WhatsApp-Nummer):

- **Telefon (Spanien und Andorra):** `900 100 000` (innerhalb Spaniens laut NACEX-FAQ und CNMC kostenlos aus Fest- und Mobilnetz; ob die Nummer aus dem Ausland erreichbar ist, sagt NACEX nicht).
- **E-Mail:** `atencion.cliente@nacex.com` (sowie `shop@nacex.com` auf der Kontaktseite).
- **Live-Chat:** auf der [NACEX-Kontaktseite](https://www.nacex.es/irContactenosContacto.do) (*„Chatee ahora con nuestros agentes“*).
- **Lokale Agenturen:** Adressen und Öffnungszeiten im [NACEX-Punktefinder](https://www.nacex.es/irCerca.do).
- **Reklamationen:** Hinweise für Auftraggeber auf der [NACEX-Reklamationsseite](https://www.nacex.es/irReclamaciones.do) (erste Prüfung binnen 48 Arbeitsstunden, angestrebter Abschluss binnen 7 Kalendertagen je nach Vorgang).

## NACEX-Pakete mit Peek verfolgen

[Peek](/) erkennt eine NACEX-Sendung sofort am Muster aus vier Ziffern, Schrägstrich und acht Ziffern (`4829/10573649`) unter „Erkannter Paketdienst“ (über „Ändern“ anpassbar). Ebenso funktionieren ein `nacex.es`-Trackinglink mit `intcli=` oder die gesamte Versand-E-Mail.

Peek prüft bis zu alle 10 Minuten und am Zustelltag ab 06:00 Uhr Ortszeit bis zu alle 2 Minuten, sobald NACEX das Paket als „In Zustellung“ scannt und solange der NACEX-Tracker direkt antwortet (über Tracking-Dienste bis zu alle 15 Minuten). Nach zwei Tagen ohne Ereignis – oder nach 12 Stunden auf „Etikett erstellt“ bzw. „Beim Paketdienst eingegangen“ – wechselt Peek auf stündliche Abfragen. Mitteilungen lassen sich auf „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“ stellen. Peek übersetzt die Statusphasen, zeigt unterwegs das voraussichtliche Lieferdatum und stellt jeden Scan auf einer Karte dar. Da NACEX öffentlich nur für die Zustellung eine Uhrzeit nennt, siehst du bei Zwischenschritten das Datum. Empfängerdaten, Zustellnachweis, Zustellkommentare, Kontaktdaten und Händlerreferenz lässt Peek weg und kann weder Zustelloptionen ändern noch NACEX für dich kontaktieren.

## Häufige Fragen zur NACEX-Sendungsverfolgung

### Wer steckt hinter NACEX und ist der Dienst seriös?

Ja. NACEX wurde 1995 gegründet, gehört über DRONAS 2002, S.L.U. zur Logista-Gruppe und betreibt mehr als 300 Franchise-Agenturen sowie 35 Plattformen in Spanien, Portugal, Andorra und den Niederlanden/Benelux. Offizielle Websites laufen unter `nacex.es`, `nacex.pt` und `nacex.com`, Kundenservice-Adressen enden auf `@nacex.com`.

### Wie lange dauert der Versand mit NACEX?

Je nach gebuchtem Tarif nennt NACEX im iberischen Onlinehandel 24 Stunden (`e-NACEX` und `e-NACEX.shop`) oder 48 bis 72 Stunden (`e-NACEX economy`). `NACEX 19:00 H` stellt bis 19:00 Uhr am nächsten Werktag auf dem spanischen Festland, in Andorra, Portugal und auf den Balearen zu – oder bis 13:00 Uhr am zweiten Werktag in Orten über 50 km von der zustellenden Agentur entfernt. Per Luftfracht dauern die Kanaren 24 bis 48 Stunden, Azoren und Madeira 2 bis 5 Tage; bei `Ecommerce Europa` richtet sich die Laufzeit nach dem Zielland.

### Kann ich die Lieferadresse meines NACEX-Pakets nachträglich ändern?

Eine Änderung der Hausadresse muss vom zahlenden Auftraggeber (meist dem Shop) freigegeben werden: Im Gebiet derselben NACEX-Franchise ist sie ohne Aufpreis möglich, über Agenturgrenzen hinweg kostenpflichtig. Mit *Pre-Alerta* kannst du ein Einzelpaket über *Mi Entrega Preferida* selbst an einen NACEX.shop-Punkt oder eine NACEX-Agentur umleiten.

### Warum erkennt Peek meine 12-stellige NACEX-Nummer nicht?

Weil das NACEX-Muster nach den ersten vier Ziffern einen Schrägstrich hat (`4829/10573649`). Ohne `/` prüft Peek Paketdienste mit durchgehend 12 Ziffern – füge den `/` nach der vierten Ziffer ein.

:::sources
- [NACEX: Startseite](https://www.nacex.es/irHome.do) – Suchfeld, Logista, Netz, Sprachen
- [NACEX: Seguimiento](https://www.nacex.es/irSeguimiento.do) – Agentur/Sendung, alternative Referenz
- [NACEX: Formulario de Seguimiento](https://www.nacex.es/seguimientoFormularioExterno.do?intcli=0000/00000000) – Fehlerhinweis
- [NACEX: FAQs](https://www.nacex.es/irFaqs.do) – Kontakt, 15 Tage, Vollmacht, Adressänderung, Verweigerung
- [NACEX: FAQ Zustellung](https://www.nacex.es/visualizarfaq.do?category=900) – Benachrichtigung, Agenturabholung
- [NACEX: FAQ Servicearten](https://www.nacex.es/visualizarfaq.do?category=901) – Samstag 9–13 Uhr, Pre-Alerta
- [NACEX: Contacto](https://www.nacex.es/irContactenosContacto.do) – Telefon, E-Mail, Chat
- [NACEX: Reclamaciones](https://www.nacex.es/irReclamaciones.do) – 31 und 7 Tage, 48 Arbeitsstunden
- [NACEX: Servicios internacionales](https://www.nacex.es/irServicioNacional.do?seccion=internacionales) – Europa, 40 kg / 20 kg
- [NACEX: Servicios eCommerce](https://www.nacex.es/irServicioNacional.do?seccion=ecommerce) – 24 h, 48/72 h
- [NACEX: Servicios urgentes](https://www.nacex.es/irServicioNacional.do?seccion=urgentes) – 19:00 H, Kanaren
- [NACEX: Servicios Portugal](https://www.nacex.es/irServicioNacional.do?seccion=portugal) – Azoren, Madeira
- [NACEX: Mi Entrega Preferida](https://www.nacex.es/irServicioNacional.do?seccion=mep) – Pre-Alerta, Locker
- [NACEX: Servicios de valor añadido](https://www.nacex.es/irServicioValor.do) – Entrega con PIN
- [NACEX: NACEX.shop](https://www.nacex.es/irEnvioSostenible.do) – ab 09:00 / 12:00 Uhr
- [NACEX: Localizador de puntos](https://www.nacex.es/irCerca.do) – Punkte und Agenturen
- [NACEX Blog: NACEX.shop](https://blog.nacex.es/2025/las-entregas-en-puntos-nacex-shop-se-duplican-en-el-ultimo-ano/) – über 4.000 Punkte
- [NACEX: ¿Quiénes somos?](https://www.nacex.es/irEmpresaQuienes.do) – 1995, Logista, Niederlande
- [NACEX: Tramitaciones aduaneras](https://www.nacex.es/irTramitacionesAduaneras.do?seccion=tramaduan) – Zollgebiete
- [NACEX: Condiciones de transporte 2026](https://www.nacex.es/pages/img/servicios/condiciones_tramitaciones/condiciones-generales-2026-ES.pdf) – Zweitzustellung, 50 km, 15 km
- [BOE: Ley 15/2009](https://www.boe.es/buscar/act.php?id=BOE-A-2009-18004) – 7-Tage-Frist (Art. 60)
- [CNMC: 800- und 900-Nummern](https://blog.cnmc.es/2019/04/24/llamadas-a-numeros-800-y-900-gratis-para-el-consumidor-y-quien-paga/) – 900 kostenlos
- [Your Europe: Versand](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_de.htm) – 30 Tage, Händlerhaftung
- [Your Europe: Mehrwertsteuer](https://europa.eu/youreurope/citizens/consumers/shopping/vat/index_de.htm) – EU-MWST-Gebiet
- [BAZG: Briefe und Pakete](https://www.bazg.admin.ch/de/empfangen-von-briefen-und-paketen) – 8,1 % / 2,6 %, bis 5 CHF
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – was Peek liest
:::
