---
title: Correos Express Sendungsverfolgung: Status und Kontakt
description: Correos-Express-Paket aus Spanien oder Portugal verfolgen: 16- oder 23-stellige Nummer, spanische Statusmeldungen auf Deutsch, Abholung und Kontakt.
slug: correos-express-sendungsverfolgung
published: 2026-11-17
updated: 2026-11-17
---

Correos Express (früher Chronoexprés, rechtlich *Correos Express Paquetería Urgente, S.A., S.M.E.*) ist die Express-Pakettochter der spanischen Correos-Gruppe. Das Unternehmen stellt in ganz Spanien und Portugal zu und verschickt über seine internationalen Tarife auch nach Deutschland, Österreich, in die Schweiz und weltweit; es ist nicht identisch mit Correos, der staatlichen spanischen Post. Zur Verfolgung gibst du deine Nummer auf der [spanischsprachigen Tracking-Seite von Correos Express](https://s.correosexpress.com/) oder direkt oben ins Suchfeld ein. Da der Absender (meist der Onlineshop) das Paket anmeldet, frag bei fehlender Nummer zuerst beim Händler nach.

## Correos oder Correos Express: Welcher Dienst hat dein Paket?

Die Muttergesellschaft Correos betreibt in Spanien den postalischen Universaldienst, während Correos Express auf eilige Paketsendungen spezialisiert ist. Beide nutzen zwar das gemeinsame Abholnetz *Punto Correos* sowie Postfilialen, haben im Web aber getrennte Sendungsverfolgungen und unterschiedliche Nummernformate:

- **Ausschließlich Ziffern (16 oder 23 Stellen):** Das spricht für Correos Express (wobei 16 Ziffern auch von anderen Paketdiensten genutzt werden, siehe unten). Gib die Ziffern oben ins Feld ein.
- **Buchstaben und Ziffern gemischt:** Das ist eine Sendung von Correos, der spanischen Post. Deren Codes bestehen aus 13 Zeichen mit `ES` am Ende, aus `PR` plus 15 Ziffern plus `C` oder aus 16 bzw. 23 Zeichen, die mit einem Buchstaben beginnen und enden. Correos bietet dafür einen eigenen [englischsprachigen Tracker](https://www.correos.es/es/en/tools/tracker/items) an.

## Correos-Express-Sendungsnummer: 16- und 23-stellige Codes

Correos Express veröffentlicht selbst keine Formatbeschreibung, sondern fragt im Suchfeld nach der *número de envío* („Sendungsnummer“). Peeks Katalog erkennt zwei rein numerische Formate, die jeweils mit einer GS1-Prüfziffer enden (frei erfundene Beispiele):

| Beispielnummer | Zu welchem Dienst sie gehört und wie Peek prüft |
| --- | --- |
| `3230 0000 0000 0000` | **16 Ziffern** (mit gültiger GS1-Prüfziffer, nicht mit sechs Nullen beginnend): Correos Express, aber ebenso [DHL eCommerce](carrier:dhl-ecommerce), [Canada Post](carrier:canada-post), [TNT](carrier:tnt) und [Evri](carrier:evri). Peek fragt beim ersten Check alle fünf Dienste parallel ab. |
| `32300000000000001000007` | **23 Ziffern** (mit gültiger GS1-Prüfziffer): verweist eindeutig auf Correos Express – außer wenn die Stellen 15 bis 17 auf `000` lauten (diese ordnet Peek Australia Post zu). |
| `RR100000003ES` | **Buchstaben und Ziffern** im [internationalen Postformat](guide:tracking-number-formats): Correos (die spanische Post), nicht Correos Express. |
| `0100/00000005` | **4 Ziffern, Schrägstrich, 8 Ziffern:** [NACEX](carrier:nacex), ein anderer iberischer Expressdienst. |

Füge immer **nur die Nummer selbst** ein (Leerzeichen, Punkte oder Bindestriche werden automatisch entfernt), keinen `s.correosexpress.com`-Link und keine komplette Versandmail – da reine Zifferncodes mehrdeutig sind, liest Peek Correos-Express-Nummern nicht aus Links oder Fließtext aus. Tippfehler verändern die GS1-Prüfziffer, sodass Correos Express bei 16 Ziffern aus der automatischen Vorauswahl fällt (über „Ändern“ kannst du den Dienst dennoch manuell auswählen). Einen SMS-Absendernamen nennt Correos Express nicht; typische Fundstellen listet [Sendungsnummer finden](guide:find-tracking-number).

Antwortet das Formular mit *„Lo sentimos, no se ha encontrado ningún envío con el número indicado“* („Leider wurde unter der angegebenen Nummer keine Sendung gefunden“), rät Correos Express zu prüfen, ob Nummer und Paketdienst stimmen, und bei frisch angemeldeten Paketen ein paar Stunden abzuwarten. Ändert sich 24 bis 48 Stunden lang nichts, wende dich an den Absender.

## Spanische Statusmeldungen von Correos Express auf Deutsch

Eine deutsche oder englische Sendungsverfolgung gibt es bei Correos Express nicht: Der Haupttracker ist auf Spanisch, die [portugiesische Suchseite](https://s.correosexpress.com/SeguimientoSinCP/search-pt) hat portugiesische Menüs. Für deutschsprachige Nutzer fragt Peek die spanische Seite ab und übersetzt diese Meldungen:

| Status bei Correos Express | Was das für dich bedeutet |
| --- | --- |
| `INFORMADO` | Der Absender hat das Paket elektronisch angemeldet, es ist aber noch nicht bei Correos Express eingetroffen. |
| `ADMITIDO` | Sendung abgeholt bzw. übernommen und vom Startdepot unterwegs (als `ADMITIDO EN OFICINA DE CORREOS` in einer Correos-Postfiliale eingeliefert). |
| `EN RUTA A LOCALIDAD DE DESTINO` | Auf dem Weg zur zuständigen Zielniederlassung. In dieser Phase stehen meist noch die Änderungsoptionen unter *Gestionar entrega* offen. |
| `EN DESTINO` | „Am Zielort“: im lokalen Zustelldepot angekommen. |
| `EN REPARTO` | In Zustellung beim Fahrer. Ab jetzt lassen sich Optionen der flexiblen Zustellung nicht mehr ändern. |
| `Su envío no ha podido ser entregado` | „Ihre Sendung konnte nicht zugestellt werden“: fehlgeschlagener Zustellversuch. |
| `NUEVO REPARTO` | „Neue Zustellung“: ein weiterer Zustellversuch ist eingeplant. |
| `CAMBIO DE DESTINO` | „Zieländerung“: Umleitung an eine geänderte Adresse oder Abholstelle. |
| `ESTACIONADO` | „Zurückgestellt“: laut portugiesischer FAQ konnte ein Zustellversuch nicht abgeschlossen werden und das Paket lagert vorerst in den Räumen von Correos Express. |
| `EN ALMACÉN` / `EN GESTIÓN` | „Im Lager“ / „In Bearbeitung“: Sendung liegt im Depot, während eine Rückfrage oder Klärung läuft. |
| `ENTREGADO EN PUNTO DE CONVENIENCIA` | Im Abholpunkt (*Punto Correos*) eingetroffen, aber für dich **noch nicht abholbereit**. |
| `DISPONIBLE EN PUNTO DE CONVENIENCIA` | Jetzt im Abholpunkt bereit zur Abholung mit deinem PIN-Code. |
| `ENTREGADO` | An dich oder eine empfangsberechtigte Person zugestellt. |
| `DEVUELTO` | An den Absender zurückgeschickt (gilt ebenso für den langen Statussatz nach Ablauf der Abholfrist im Paketshop). |

**Häufige Stolperfalle:** Lass dich von `ENTREGADO EN PUNTO DE CONVENIENCIA` nicht täuschen – *entregado* heißt hier nur, dass der Fahrer das Paket im Shop abgegeben hat. Geh erst los, wenn der Status auf `DISPONIBLE EN PUNTO DE CONVENIENCIA` umspringt und du deinen PIN-Code hast. Auf seiner spanischen Hilfeseite erläutert Correos Express nur `INFORMADO`, `ADMITIDO`, `EN RUTA A LOCALIDAD DE DESTINO`, `EN REPARTO` und `ENTREGADO`, dazu `ESTACIONADO` in der portugiesischen FAQ; die übrigen Erklärungen folgen dem Wortlaut und Peeks Zuordnung (allgemeiner Überblick unter [Bedeutung von Sendungsstatus](guide:tracking-statuses)).

## Versand nach Europa, Zustellung ändern und Abholung im Punto Correos

**Versand aus Spanien nach Deutschland, Österreich, in die Schweiz und weltweit:** Correos Express bietet ab Spanien zwei internationale Tarife an: *Internacional Estándar* (Tür-zu-Tür-Straßenversand für Einzelpakete innerhalb Europas mit unverbindlichen Richtlaufzeiten von 2 bis 3 Werktagen in die wichtigsten EU-Länder) und *Internacional Express* (dringender Luftversand in über 180 Länder, in Europa je nach Ziel 1 bis 2 Werktage, inklusive bis zu zwei Zustellversuchen und Online-Verfolgung bis zur Übergabe). Wochenenden und Feiertage in Durchgangs- und Zielländern zählen bei den Richtzeiten nicht mit.

**Zustellung in Spanien steuern:** Solange das Paket noch nicht `EN REPARTO` ist und Tarif sowie Absender Änderungen zulassen, erscheint im Tracking die Schaltfläche **Gestionar entrega** – darüber kannst du es an einen *Punto Correos* umleiten, die Adresse anpassen oder das Lieferdatum verschieben. Tarife wie *Paq 24* und *Paq 14* umfassen bis zu zwei Zustellversuche ohne Aufpreis; nach einem Fehlversuch lässt sich über [Solicita nueva entrega](https://s.correosexpress.com/SeguimientoSinCP/saveNewDelivery) ein neuer Tag (Vormittags- oder Nachmittagsfenster), eine Correos-Postfiliale oder ein Paketshop wählen, und *Paq e-Commerce*-Sendungen können bei freien Fächern auch im nächsten *Punto Correos* hinterlegt werden. Bei Nachnahme (*contra reembolso*) kassiert der Zusteller an der Haustür; Correos Express empfiehlt, den Betrag passend in bar bereitzuhalten.

**Zustellung in Portugal:** Nach einem Fehlversuch folgt automatisch ein zweiter Versuch; parallel bietet eine Benachrichtigung einen anderen Tag, eine andere Adresse im selben Distrikt oder einen *Ponto Correos* an. Bleibt ein Paket nach einer Fehlzustellung **3 Werktage** unbeansprucht, geht es laut portugiesischen Transportbedingungen an den Absender zurück.

**Lagerfristen und PIN-Abholung:**

- **Punto Correos (Spanien):** Lagerfrist **7 Kalendertage** ab Eintreffen, danach automatische Rücksendung. Zur Abholung zeigst du den per SMS und/oder E-Mail gesendeten PIN vor; fehlt er auch im Spam-Ordner, klicke im Tracking nach Authentifizierung auf **Visualizar PIN**.
- **Correos-Postfiliale (*Entrega en oficina de Correos*, Spanien):** bei *Paq 24* und *Baleares Express* (ohne Nachnahme) bis zu **15 Kalendertage** Lagerfrist mit drei SMS-Hinweisen (bei Ankunft sowie nach 5 und 10 Tagen).
- **Portugal (*Ponto Correos* und Paketautomaten):** bis zu **7 Tage** im Shop bzw. bis zu **3 Tage** in *CitiBox*- und *CityPaq*-Automaten; wer den einmalig gültigen PIN vorzeigt, gilt als abholberechtigt.

## Zoll, Sondergebiete und Warnung vor gefälschten Paket-SMS

Zwischen Festlandspanien (oder den Balearen) und Deutschland bzw. Österreich reisen Pakete innerhalb des EU-Mehrwertsteuergebiets ohne Zollabfertigung. Echte Einfuhrabgaben und Zollpapiere fallen jedoch an, sobald eine Steuer- oder Zollgrenze überschritten wird:

- **Schweiz:** Bei Einfuhren in die Schweiz gilt die Mehrwertsteuer von **8,1 %** (**2,6 %** ermäßigter Satz). Laut BAZG werden Zollabgaben und Steuerbeträge **bis 5 CHF** nicht erhoben (entspricht bis zu 62 CHF Bemessungsgrundlage bei 8,1 % bzw. 193 CHF bei 2,6 %). Darüber fallen MWST, eventuelle Gewichtszölle und Verzollungsentgelte des Zustellers (etwa der [Schweizerischen Post](carrier:swiss-post) oder des Expresspartners) an; Einzelheiten erklärt [Zoll und Einfuhrabgaben](guide:customs).
- **Kanaren, Ceuta, Melilla, Andorra, Gibraltar, Azoren, Madeira und Nicht-EU-Staaten:** Die Kanarischen Inseln, Ceuta und Melilla gehören nicht zum EU-Mehrwertsteuergebiet. Correos Express weist darauf hin, dass Sendungen auf die Kanaren, nach Andorra, Ceuta, Melilla oder Gibraltar zwingend eine Handels- oder Proformarechnung (für die Kanaren zusätzlich eine unterschriebene eidesstattliche Warenerklärung, *declaración jurada*) benötigen und dort lokale Steuern sowie Abfertigungsgebühren entstehen können, die nicht im Versandpreis enthalten sind; auch Luftfrachtsendungen auf die Azoren und nach Madeira erfordern Inhalts- und Zolldokumente. Für das Vereinigte Königreich gelten seit dem Austritt aus EU-Binnenmarkt und Zollunion Ende 2020 ebenfalls Zollanforderungen und Zusatzkosten (*Internacional Express* übernimmt außerhalb der EU die Verzollung).

> **Vorsicht vor gefälschten SMS (*Smishing*):** Auf seiner Seite *Alerta de seguridad* stellt Correos Express klar, dass das Unternehmen **auf keinem Weg Bankdaten oder Zugangsdaten zu seiner Website abfragt** und **keine kostenpflichtigen 807-Sonderrufnummern betreibt**; im Zweifel sollst du ausschließlich `www.correosexpress.es` nutzen oder `900 122 333` anrufen. Die spanische Cybersicherheitsbehörde INCIBE warnte im März 2022 vor betrügerischen SMS im Namen von Correos und Correos Express, die eine angeblich gescheiterte Zustellung wegen offener Zoll- oder Versandkosten vortäuschten und auf gefälschte Zahlungsseiten führten. Auch wenn bei Sendungen über echte Zollgrenzen (Schweiz, Drittländer, Kanaren, Ceuta oder Melilla) tatsächlich Einfuhrabgaben anfallen können oder Nachnahme an der Tür bezahlt wird: Öffne niemals Zahlungslinks aus verdächtigen SMS, prüfe deine Nummer direkt auf `s.correosexpress.com` und kontaktiere sofort deine Bank, falls du bereits Kartendaten eingegeben hast.

## Paket verspätet, unauffindbar oder beschädigt

Vertragspartner von Correos Express ist der Absender, und die Transportbedingungen lassen nur eine einzige Reklamation pro Sendung zu – stimme dich deshalb immer mit dem Shop ab:

- **Kein neuer Scan:** Zwischen Logistikzentren kann die Anzeige einige Stunden nachhängen. Tut sich **24 bis 48 Stunden** nichts, verweist Correos Express an den Absender; siehe auch [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating).
- **Fehlversuch trotz Anwesenheit:** Prüfe die Detailansicht im Tracking (Zugangsprobleme oder unvollständige Adressdaten können die Zustellung verhindern) und buche einen neuen Versuch.
- **Status `ENTREGADO`, aber kein Paket da:** In Spanien zuerst die Zustelldetails im Tracking prüfen und dann den Händler einschalten; in Portugal können Empfänger direkt an `destinatarioscep@correosexpress.com` schreiben. Weitere Tipps unter [zugestellt, aber nicht erhalten](guide:delivered-not-received).
- **Beschädigung oder Verlust:** Äußerlich erkennbare Schäden sofort bei der Übergabe schriftlich vermerken lassen. Für verdeckte Schäden gelten laut spanischen Transportbedingungen **7 Kalendertage** im Inland (und **21 Tage** für Verspätungsvorbehalte) bzw. im grenzüberschreitenden Verkehr **7 Tage auf der Straße** und **14 Tage per Luftfracht** (in Portugal **8 Tage** laut AGB sowie Fotomeldung an den Absender binnen **7 Tagen** laut FAQ).
- **Annahme verweigert oder `DEVUELTO`:** Bei Annahmeverweigerung informiert Correos Express den Absender und wartet auf dessen Weisung. Bei Verbraucherkäufen in der EU trägt laut *Your Europe* der Händler das Transportrisiko bis zur physischen Übergabe (Lieferfrist **30 Tage**, sofern nichts anderes vereinbart wurde) – kläre Ersatzlieferung oder Erstattung direkt mit dem Verkäufer.

## Correos Express Kontakt und Kundenservice

Kundenservice, Hilfe und Sendungsverfolgung stehen ausschließlich auf Spanisch und Portugiesisch bereit:

- **Spanien:** `900 122 333` (ohne veröffentlichte Öffnungszeiten; innerhalb Spaniens laut CNMC aus Fest- und Mobilnetz kostenlos, aus dem Ausland jedoch oft gesperrt). Auf der Tracking-Seite steht zusätzlich die Madrider Festnetznummer `913 277 020` (aus dem Ausland unter `+34 913 277 020` zum Auslandstarif erreichbar). Daneben gibt es das [Kundenservice-Formular](https://www.correosexpress.es/es/atencion-al-cliente/contacto/atencion-al-cliente) (mit Sendungsnummer und Postleitzahl) sowie `@Cex_responde` auf X.
- **Portugal:** `229 762 810` (zum portugiesischen Festnetztarif), Montag bis Freitag von 08:30 bis 19:00 Uhr, sowie für Empfänger `destinatarioscep@correosexpress.com`.

## Correos-Express-Pakete mit Peek verfolgen

Da 16-stellige Nummern von fünf Paketdiensten genutzt werden, fragt [Peek](/) diese Kandidaten gemeinsam ab (in Deutschland startet [DHL eCommerce](carrier:dhl-ecommerce) als Erster in derselben Abfrage); eine gültige 23-stellige Nummer zeigt direkt auf Correos Express (abgesehen vom `000`-Bereich von Australia Post). Verknüpft Correos Express einen 23-stelligen Paketcode mit seiner 16-stelligen Sendungsnummer, blendet Peek diese zusätzlich unter „Correos Express führt die Sendung unter“ ein. Im Hintergrund gleicht Peek sowohl Correos Express als auch den Konzern-Localizador von Correos ab – dadurch bleibt der Verlauf selbst dann abrufbar, wenn das Webformular von Correos Express eine ältere zugestellte Sendung nicht mehr anzeigt.

Peek prüft bis zu alle 10 Minuten und am Zustelltag ab 06:00 Uhr Ortszeit bis zu alle 2 Minuten, sobald das Paket als „In Zustellung“ gescannt ist und solange der eigene Tracker von Correos Express antwortet (über Tracking-Dienste bis zu alle 15 Minuten). Nach zwei Tagen ohne Neuigkeiten – oder nach 12 Stunden auf „Etikett erstellt“ bzw. „Beim Paketdienst eingegangen“ – fragt Peek stündlich ab. Du kannst Mitteilungen für „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“ wählen. Peek zeigt den deutschen Status, das voraussichtliche Lieferdatum unterwegs und jeden Scan auf einer Karte; Depotzeiten vom spanischen Festland, den Balearen, aus Ceuta, Melilla und Kontinentalportugal werden mit dem Hinweis „Uhrzeiten in deiner Zeitzone: …“ umgerechnet (Scans auf den Kanaren und Azoren bleiben in Ortszeit). Zustellnachweis, Unterzeichner, Abhol-PIN, Kontaktdaten und freie Fahrernotizen lässt Peek weg und kann Correos Express nicht für dich kontaktieren.

## Häufige Fragen zur Correos-Express-Sendungsverfolgung

### Ist Correos dasselbe wie Correos Express?

Nein. Correos ist die staatliche spanische Post für den Universaldienst; Correos Express (*Correos Express Paquetería Urgente, S.A., S.M.E.*) ist das Konzernunternehmen für den Express-Paketversand. Beide teilen sich das *Punto Correos*-Netz, aber rein numerische 16- oder 23-stellige Nummern gehören zu Correos Express, alphanumerische Codes wie `RR100000003ES` dagegen zu Correos.

### Wie schnell und um welche Uhrzeit liefert Correos Express?

Auf der Iberischen Halbinsel nennt Correos Express 24 Stunden (*Paq 24*), zwischen Spanien und dem portugiesischen Festland üblicherweise 24 bis 48 Stunden und nach Ceuta, Melilla, Andorra sowie Gibraltar den zweiten Werktag (mit kostenpflichtiger Option *Entrega en sábados* am Samstag auf dem spanischen Festland; sonntags erfolgt keine Zustellung). Zeitdefinierte Tarife liefern vor 10:00 Uhr (*Paq 10*) oder vor 14:00 Uhr (*Paq 14*). Innerhalb Europas nennt Correos Express unverbindlich 2 bis 3 Werktage für *Internacional Estándar* und 1 bis 2 Werktage für *Internacional Express*. Die im Tracker angezeigte Uhrzeit basiert auf früheren Zustellungen in deiner Postleitzahl und kann sich mit der Tour verschieben.

### Warum steht dort `ENTREGADO EN PUNTO DE CONVENIENCIA`, obwohl ich noch keinen PIN habe?

Weil dieser Status nur bestätigt, dass der Fahrer das Paket im Paketshop abgeliefert hat – noch nicht, dass es dort fertig eingebucht ist. Warte auf `DISPONIBLE EN PUNTO DE CONVENIENCIA`: Erst dann kommt der Abhol-PIN per SMS oder E-Mail (oder lässt sich über **Visualizar PIN** im Tracking abrufen), und ab dann laufen in Spanien die 7 Kalendertage Abholfrist.

### Kann ich Correos Express auf Deutsch oder Englisch kontaktieren?

Nein. Sowohl die Websites und Tracking-Seiten als auch der Kundenservice sind ausschließlich auf Spanisch und Portugiesisch verfügbar. Bei Bestellungen aus Deutschland, Österreich oder der Schweiz wendest du dich bei Problemen am einfachsten an den Kundenservice des Onlineshops.

:::sources
- [Correos Express: Seguimiento de envíos](https://www.correosexpress.es/es/atencion-al-cliente/seguimiento-envio) – Statuserklärungen, 24–48 h Wartezeit, Lieferzeit
- [Correos Express: Gestiona tu entrega](https://www.correosexpress.es/es/atencion-al-cliente/seguimiento-envio/gestiona-tu-entrega) – Gestionar entrega, Paq e-Commerce
- [Correos Express: Punto Correos](https://www.correosexpress.es/es/atencion-al-cliente/seguimiento-envio/recogida-en-punto-correos) – PIN, Visualizar PIN, 7 Kalendertage
- [Correos Express: Otras consultas](https://www.correosexpress.es/es/atencion-al-cliente/seguimiento-envio/otras-consultas-entrega) – Nachnahme, Kontaktkanäle
- [Correos Express: Sigue tu envío](https://s.correosexpress.com/) – Tracker, Fehlermeldung, 913 277 020
- [Correos Express: Solicita nueva entrega](https://s.correosexpress.com/SeguimientoSinCP/saveNewDelivery) – neue Zustellung, Filiale, Paketshop
- [Correos Express: Atención al Cliente](https://www.correosexpress.es/es/atencion-al-cliente/contacto/atencion-al-cliente) – Kontaktformular
- [Correos Express: Términos y condiciones](https://www.correosexpress.es/es/atencion-al-cliente/tyc-transporte) – Reklamationsfristen, Annahmeverweigerung
- [Correos Express: Aviso Legal](https://www.correosexpress.es/es/legales/aviso-legal) – Firmenname
- [Correos Express: Alerta de seguridad](https://www.correosexpress.es/es/legales/alerta-de-seguridad) – Sicherheitshinweise, 900 122 333
- [Correos Express: Startseite](https://www.correosexpress.es/) – Paq 10, Paq 14, Paq 24
- [Correos Express: Quiénes somos](https://www.correosexpress.es/es/sobre-correos-express/quienes-somos) – Abgrenzung zu Correos
- [Correos Express: España y Portugal](https://www.correosexpress.es/es/atencion-al-cliente/envios-en-espana-y-portugal) – Portugal, Sondergebiete, Zolldokumente
- [Correos Express: Envíos internacionales](https://www.correosexpress.es/es/atencion-al-cliente/envios-internacionales1) – Internacional Estándar, Großbritannien
- [Correos Express: Internacional Express](https://www.correosexpress.es/es/particulares/enviar/internacionales/internacional-express) – 180 Länder, 1–2 Werktage
- [Correos Express: Internacional Express Empresas](https://www.correosexpress.es/es/empresas/productos/internacional-express) – zwei Versuche, Drittlandsverzollung
- [Correos Express: Paq 24](https://www.correosexpress.es/es/particulares/enviar/peninsulares/paq-24) – zwei Versuche, Samstagsoption
- [Correos Express: Paq 14](https://www.correosexpress.es/es/particulares/enviar/peninsulares/paq-14) – vor 14:00 Uhr, zwei Versuche
- [Correos Express: Entrega en oficina](https://clientes.correosexpress.com/web/correosexpress/entrega-en-oficina-de-correos) – 15 Kalendertage, 3 SMS
- [Correos Express: Punto Correos Netz](https://www.correosexpress.es/es/sobre-correos-express/sala-de-prensa/2025/grupo-correos-lanza-su-red-de-puntos-de-conveniencia-y-ampliara-) – Konzernnetz
- [Correos: Tracker](https://www.correos.es/es/en/tools/tracker/items) – Correos-Sendungsverfolgung
- [Correos Express PT: Siga seu envio](https://s.correosexpress.com/SeguimientoSinCP/search-pt) – portugiesischer Tracker
- [Correos Express PT: FAQ](https://www.correosexpress.pt/pt/apoio-ao-cliente/perguntas-frequentes) – ESTACIONADO, Zweitversuch, Schäden
- [Correos Express PT: Apoio ao Cliente](https://www.correosexpress.pt/pt/apoio-ao-cliente/contacto/apoio-cliente) – 229 762 810, Servicezeiten, E-Mail
- [Correos Express PT: Termos](https://www.correosexpress.pt/pt/apoio-ao-cliente/tec-transporte) – 3 Werktage, 8 Tage, PIN
- [Correos Express PT: Paq Ponto](https://www.correosexpress.pt/pt/empresa/enviar/peninsulares/paq-ponto) – 7 Tage Shop, 3 Tage Automat
- [CNMC: 800- und 900-Nummern](https://blog.cnmc.es/2019/04/24/llamadas-a-numeros-800-y-900-gratis-para-el-consumidor-y-quien-paga/) – kostenlose 900-Rufnummern
- [INCIBE: Smishing-Warnung](https://www.incibe.es/ciudadania/avisos/en-circulacion-sms-fraudulentos-que-suplantan-correos-y-otras-empresas-de) – gefälschte Paket-SMS
- [Your Europe: Versand](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_de.htm) – 30 Tage, Händlerrisiko
- [Your Europe: Mehrwertsteuer](https://europa.eu/youreurope/citizens/consumers/shopping/vat/index_de.htm) – EU-Steuergebiete
- [BAZG: Briefe und Pakete](https://www.bazg.admin.ch/de/empfangen-von-briefen-und-paketen) – Schweizer MWST, 5-CHF-Grenze
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – GS1-Erkennung und Statuszuordnung
:::
