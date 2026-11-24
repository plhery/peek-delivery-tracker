---
title: Paquetexpress Sendungsverfolgung: Guías, Ocurre & Hilfe
description: Paquetexpress-Paket in Mexiko verfolgen: Guía & 11–13 Ziffern, spanische Status, 2 Versuche, 5 Tage Ocurre-Abholung, Reklamationen & Kontakt.
slug: paquetexpress-sendungsverfolgung
published: 2026-11-24
updated: 2026-11-24
---

Paquetexpress (*Operadora de Servicios Paquetexpress, S.A.P.I. de C.V.* mit Sitz in Los Mochis, Sinaloa) ist einer der größten mexikanischen Paket-, Kurier- und Frachtdienstleister mit 40 Jahren Erfahrung, über 12.200 Mitarbeitenden, landesweitem Filialnetz in Mexiko und internationalem Versand in mehr als 240 Länder. Verfolge eine Sendung ohne Konto auf der [Tracking-Seite von Paquetexpress](https://www.paquetexpress.com.mx/rastreo) oder füge deinen Code bzw. deine *Guía* oben ein.

## Paquetexpress-Sendungsnummern: Tracking-Codes und Guías

Die [FAQ von Paquetexpress](https://www.paquetexpress.com.mx/preguntas-frecuentes/) nennen drei mögliche Suchnummern: Tracking-Code (*código de rastreo*), Frachtbrief (*número de guía*) oder Absenderreferenz (Peek liest nur Tracking-Codes und *Guías*). Den Code findest du in der Versand-E-Mail des Shops, im SMS-Link von Paquetexpress oder auf dem Etikett:

| Format (erfunden) | Was es ist |
| --- | --- |
| `MEX48WW2917364` | Paquetexpress-*Guía*: 3-stelliger Filialcode + 2 Ziffern + 2 Buchstaben + 5 bis 7 Ziffern (deckt alle Pakete eines Frachtbriefs ab). |
| `482917364058` | 11- bis 13-stelliger numerischer Code (*código de rastreo*). Teilt diese Länge mit Diensten wie [Mondial Relay](carrier:mondial-relay), [Colis Privé](carrier:colis-prive), [MRW](carrier:mrw), [BRT](carrier:brt) und [SF Express](carrier:sf-express). |

Beginnt dein mexikanisches Paket mit `JMX`, prüfe [J&T Express Mexico](carrier:j-and-t-mx); weitere Formate findest du unter [Sendungsnummern-Formate](guide:tracking-number-formats).

## Was die Paquetexpress-Sendungsstatus bedeuten

Paquetexpress zeigt den Verlauf auf Spanisch mit internen Ereigniscodes (`SWB`, `BOK`, `TRN`, `WHU`, `HTC`, `HDC` usw.):

| Paquetexpress zeigt (Code) | Was das für dich bedeutet | Peek zeigt |
| --- | --- | --- |
| `Documentación De Servicios Web` (`SWB`), `Solicitud De Rad` (`HCR`), `Recolección Planeada` (`HCP`) | Etikett elektronisch erstellt oder Abholung angefordert/geplant; Paquetexpress hat das physische Paket noch nicht. | „Etikett erstellt“ |
| `Documentada` (`BOK`), `Recolección Realizada` (`HCM`), `Confirmación De Recolección En Almacen` (`HCA`) | Am Filialschalter eingeliefert, vom Fahrer abgeholt oder im Ausgangslager erfasst. | „Beim Paketdienst eingegangen“ |
| `Mercancía En Tránsito` (`TRN`), `Transbordo De Remolque` (`TTM`), `Recibida En Sucursal de Transbordo` (`WHU`), `Planeado Para Entrega A Domicilio` (`HLP`) | Unterwegs zwischen Filialen/Aufliegern oder in der Zielfiliale für eine Zustelltour vorbereitet. | „Unterwegs“ |
| `Recibida En Sucursal destino` (`WHU`) | In der Zielfiliale eingetroffen: noch „Unterwegs“ bei Hauszustellung (`ENT_EAD`), oder abholbereit bei *Ocurre* (`ENT_OCU`). | „Unterwegs“ oder „Abholbereit“ |
| `En Ruta De Entrega A Domicilio` (`HTC`) | Heute mit dem Fahrer zur Hauszustellung unterwegs. | „In Zustellung“ |
| `Mercancía Entregada A Domicilio` (`HDC`), `Entregada En Sucursal` (`BDL`), `Pagada En Destino (Contado)` (`PDC`) | An der Tür zugestellt, in der Filiale abgeholt oder bei Übergabe am Zielort bezahlt. | „Zugestellt“ |

Hinweis: `Rad Cancelación` (`HCC`, stornierte Abholanfrage) beendet die Sendung nicht, weil der Absender das Paket weiter einliefern kann – Peek behält daher die Phase des vorherigen Scans bei.

## Laufzeiten, 2 Zustellversuche, Ocurre-Abholung und Lagergebühren

Gemäß [Leistungsseite](https://www.paquetexpress.com.mx/servicios/), [FAQ](https://www.paquetexpress.com.mx/preguntas-frecuentes/) und bei PROFECO registriertem [Leistungsvertrag (Clausulado, Vertrag 3808-2020)](https://www.paquetexpress.com.mx/clausulado-solicitud-servicios/):

- **Laufzeiten:** Inländische Pakete und Kuriersendungen laufen je nach Entfernung **24, 48, 72, 96 oder 120 Stunden** (mit Express am selben Tag vor 12:00 Uhr, 24 h, 48 h sowie Samstagszustellung). Bei Übergabe erfasst Paquetexpress stets Name, Unterschrift, Datum und Uhrzeit (*Acuse*).
- **Höchstens 2 Zustellversuche:** Nach Klausel 5 unternimmt Paquetexpress bei Hauszustellung (*Entrega a Domicilio*) bis zu **2 Versuche**. Ist niemand daheim, hinterlässt der Fahrer eine Besuchskarte (*aviso de visita*) mit Filial- und Servicenummern, und das Paket lagert in der Filiale.
- **5 Kalendertage kostenlose Abholung (Ocurre oder nach fehlgeschlagenem Versuch):** Nach Klausel 5 müssen sowohl *Ocurre*-Filialpakete als auch Sendungen nach verpasster Zustellung binnen **5 Kalendertagen** ab Ankunft/Benachrichtigung abgeholt werden. Danach darf Paquetexpress das Paket ohne Vorankündigung ins Fundlager (*bodega de rezagos*) verlegen und **2 % Lagergebühr pro Tag** (der Versandkosten) berechnen. Nach **90 Kalendertagen** nicht abgeholte Pakete können versteigert oder vernichtet werden.
- **Zustellart oder Adresse ändern:** Laut [FAQ](https://www.paquetexpress.com.mx/preguntas-frecuentes/) kannst du telefonisch unter `(+52) 800 821 0208` zwischen *Ocurre* und Hauszustellung wechseln oder eine Lieferadresse auch nach Abgang aus der Filiale korrigieren. Filialen findest du unter [Paquetexpress Sucursales](https://www.paquetexpress.com.mx/sucursales).

## Reklamationen, beschädigte Pakete und PROFECO-Schlichtung

Bei Onlinebestellungen kontaktiere zuerst den Shop für Erstattung oder Ersatz. Nach den [Leistungsbedingungen](https://www.paquetexpress.com.mx/clausulado-solicitud-servicios/) (Klausel 8 für Reklamationen):

- **Bei Übergabe prüfen:** Kontrolliere die Verpackung vor dem Fahrer, vermerke Beschädigungen auf der Empfangsquittung (*acuse de recibo*) und verlange eine genaue Prüfung.
- **15 Werktage Reklamationsfrist:** Reiche eine formelle Reklamation binnen **15 Werktagen** nach Feststellung ein – persönlich in jeder Filiale (Mo–Fr 08:00–18:00 Uhr), per E-Mail an `reclamaciones@paquetexpress.com.mx` oder über das [Reklamationsportal](https://www.paquetexpress.com.mx/contacto/solicitud-de-reclamacion/). Paquetexpress antwortet binnen **15 Werktagen**.
- **Haftung und Versicherung:** Nach Klausel 5 haftet Paquetexpress ohne Versicherung bei Verlust, Schaden oder Nichtzustellung bis zu 15 Tagessätzen des allgemeinen Mindestlohns je Tonne (bzw. anteilig bei leichteren Sendungen) sowie bei verschuldeter Verspätung mit 5 Tagessätzen je Verzugstag. Nach Klausel 6 deckt die optionale Transportversicherung bis zum deklarierten Wert (kein Selbstbehalt bei Schaden, 20 % bei bewaffnetem Raub); sie endet nach 5 Kalendertagen in einer *Ocurre*-Filiale bzw. bei Hauszustellung nach dem 2. fehlgeschlagenen Versuch oder 10 Kalendertagen nach Ankunft in der Zielfiliale.
- **PROFECO:** Nach Klausel 10 ist Mexikos Verbraucherschutzbehörde ([PROFECO](https://www.gob.mx/profeco), registrierter Vertrag `3808-2020`) administrativ zuständig.

Bei fehlenden Scans oder Falschzustellung (die Paquetexpress unter `800 821 0208` zu melden bittet) siehe [Warum sich die Sendungsverfolgung nicht aktualisiert](guide:tracking-not-updating), [Zugestellt, aber nicht erhalten](guide:delivered-not-received), [Beschädigtes Paket](guide:damaged-parcel), [Verlorenes Paket](guide:lost-parcel) und [Zurück an Absender](guide:returned-to-sender).

## Kundenservice und Kontakte von Paquetexpress

Veröffentlichte Kontakte auf [Paquetexpress Contact](https://www.paquetexpress.com.mx/en/contacto/), [Contacto (ES)](https://www.paquetexpress.com.mx/contacto/) und im [Clausulado](https://www.paquetexpress.com.mx/clausulado-solicitud-servicios/):

- **Kostenlose Hotline:** `+52 800 821 0208` – Montag bis Freitag 08:00–21:00 Uhr und Samstag 08:00–15:00 Uhr; der Leistungsvertrag nennt 07:00–20:00 Uhr bzw. 07:00–14:00 Uhr Pazifikzeit.
- **WhatsApp:** `+52 668 195 0169` (verlinkt auf [paquetexpress.com.mx](https://www.paquetexpress.com.mx/)).
- **Reklamations-E-Mail & Portal:** `reclamaciones@paquetexpress.com.mx` und [Solicitud de Reclamación](https://www.paquetexpress.com.mx/contacto/solicitud-de-reclamacion/).
- **Datenschutz / ARCO-Anfragen:** `solicitud.arco@paquetexpress.com.mx`.
- **Zentrale:** Operadora de Servicios Paquetexpress, S.A.P.I. de C.V., Blvd. Rosendo G. Castro No. 32 Poniente, Col. Centro, Los Mochis, Sinaloa, C.P. 81200, Mexiko.

> Vorsicht vor gefälschten SMS- oder E-Mail-Links mit Online-Kartenzahlung zur Umterminierung eines Paquetexpress-Pakets: Paquetexpress bestätigt, dass du Zustellwünsche direkt telefonisch unter `800 821 0208` ändern oder Sendungen auf [paquetexpress.com.mx/rastreo](https://www.paquetexpress.com.mx/rastreo) prüfen kannst. Siehe [Betrügerische Paket-SMS](guide:delivery-scam-texts).

## Paquetexpress-Paket mit Peek verfolgen

Da 11- bis 13-stellige Codes von vielen internationalen Paketdiensten genutzt werden, richtet sich [Peek](/) nach deiner Region. In Mexiko fragt Peek Paquetexpress schon in der ersten Runde für passende *Guías* (`MEX48WW2917364`, erfunden) und 11- bis 13-stellige Codes (`482917364058`) und zeigt „Paquetexpress hat dieses Paket.“, sobald Paquetexpress es bestätigt. Außerhalb Mexikos geht eine *Guía* trotzdem allein an Paquetexpress, während ein 11- bis 13-stelliger Code Paquetexpress als Letztes probiert. Ein eingefügter `paquetexpress.com.mx/rastreo/...`-Link (oder `/en/rastreo/...`) wählt Paquetexpress weltweit direkt aus; sonst tippe beim Hinzufügen auf „Ändern“ oder in der Paketansicht auf „Paketdienst ändern“ (auch die Suche nach „Paquete Express“ findet ihn).

Peek fragt direkt den Verlaufsdienst von Paquetexpress ab (für Tracking-Codes und *Guías*, keine Absenderreferenzen) und zeigt die Phase („Etikett erstellt“, „Beim Paketdienst eingegangen“, „Unterwegs“, „In Zustellung“, „Abholbereit“, „Zugestellt“), spanische Statuszeilen (Personennamen nach `POR`, `a` und `, recibió` entfernt), Filial-/Stadtorte, das voraussichtliche Lieferdatum sofern aktiv, die Zustellzeit sowie die jeweiligen mexikanischen Filial-Zeitzonen (Pazifik, Mountain, Central und Quintana Roo, angezeigt in deiner Gerätezeit). Bei Mehrpaket-*Guías* hält Peek die Sendung offen, solange sich noch ein Paket bewegt, selbst wenn ein anderes bereits zugestellt ist, und zeigt `Recibida En Sucursal destino` bei *Ocurre*-Sendungen (`ENT_OCU`) automatisch als „Abholbereit“. Fortschrittsleiste, Diensttyp, Herkunfts-/Zielkopfzeilen, Kundenreferenzen, Absender-/Empfängernamen, Filialstraßen und Unterschriften-/POD-Bilder lässt Peek weg; Gewicht und Maße werden nicht geliefert.

Solange du das Paket in Peek geöffnet hast oder am Zustelltag mit aktivierten Benachrichtigungen prüft Peek bis zu alle 10 Minuten; an dem Tag, an dem Paquetexpress `En Ruta De Entrega A Domicilio` meldet, alle 2 Minuten ab 6 Uhr nach der Uhrzeit des neuesten Paquetexpress-Scans, solange der Paquetexpress-Tracker antwortet. Sonst prüft Peek stündlich. Nach 12 Stunden mit nur „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ oder nach zwei Tagen ohne Neuigkeiten prüft es unabhängig davon stündlich, und nach sieben Tagen auf „Etikett erstellt“ alle 6 Stunden. Antwortet der Paquetexpress-Tracker nicht und verfolgt Peek das Paket allein über Tracking-Dienste, prüft es stündlich, nach einem stillen Tag alle 3 Stunden und am Zustelltag bis zu alle 15 Minuten, solange du die Sendung geöffnet hast oder Benachrichtigungen aktiv sind.

Ist der neueste Scan vier Tage alt und das Paket steht noch auf „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“, markiert Peek es mit dem Datum dieses Scans, etwa „Keine Aktualisierung seit Mo., 12. Okt.“. Auf der Paketseite steht dann: „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei Paquetexpress nach.“, mit dem Link „Hol dir Hilfe bei Paquetexpress“ zur Kontaktseite von Paquetexpress. Nach drei Wochen ohne neuen Scan heißt es: „Seit über drei Wochen nichts Neues. Ist das Paket noch nicht angekommen, frag beim Verkäufer nach. Peek fragt weiter bei Paquetexpress nach.“

Als Benachrichtigungsstufe kannst du „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“ wählen. Peek überwacht nur das Tracking und kann weder zwischen *Ocurre* und Hauszustellung wechseln noch Reklamationen bei Paquetexpress für dich einreichen.

## Fragen zur Paquetexpress-Sendungsverfolgung

### Wie viele Zustellversuche macht Paquetexpress?

Nach Klausel 5 der [Leistungsbedingungen](https://www.paquetexpress.com.mx/clausulado-solicitud-servicios/) unternimmt Paquetexpress bei Hauszustellung bis zu **2 Versuche**. Kann niemand annehmen, hinterlässt der Fahrer eine Besuchskarte (*aviso de visita*) und das Paket lagert in der örtlichen Filiale.

### Wie lange lagert Paquetexpress ein Ocurre- oder nicht zugestelltes Paket?

Du hast **5 Kalendertage** kostenlos ab Ankunft in der Filiale (oder ab Besuchskarte). Danach darf Paquetexpress das Paket mit **2 % Lagergebühr pro Tag** ins Fundlager bringen und nach 90 Kalendertagen versteigern oder vernichten.

### Kann ich von Ocurre-Abholung auf Hauszustellung wechseln oder die Adresse ändern?

Ja. Laut den [FAQ von Paquetexpress](https://www.paquetexpress.com.mx/preguntas-frecuentes/) kannst du unter `(+52) 800 821 0208` zwischen *Ocurre* und Hauszustellung wechseln oder die Lieferadresse selbst nach Abgang aus der Filiale korrigieren lassen.

### Wie lange habe ich Zeit für eine Schadens- oder Verlustreklamation?

Vermerke sichtbare Schäden bei Übergabe auf dem Lieferschein (*acuse de recibo*) und reiche binnen **15 Werktagen** eine formelle Reklamation in einer Filiale, per E-Mail an `reclamaciones@paquetexpress.com.mx` oder über das [Online-Reklamationsformular](https://www.paquetexpress.com.mx/contacto/solicitud-de-reclamacion/) ein. Paquetexpress antwortet binnen 15 Werktagen.

:::sources
- [Paquetexpress: Home](https://www.paquetexpress.com.mx/) – Tracking-Einstieg, WhatsApp `+52 668 195 0169`, kostenlose Hotline `+52 800 821 0208`
- [Paquetexpress: Rastreo](https://www.paquetexpress.com.mx/rastreo) – Sendungsverfolgung
- [Paquetexpress: Servicios](https://www.paquetexpress.com.mx/servicios/) – 24–120 h Inlandslaufzeiten, Express am selben Tag/24/48 h, Samstagszustellung, Acuse-Unterschrift, 240+ Länder
- [Paquetexpress: International](https://www.paquetexpress.com.mx/en/internacional/) – internationale Versanddienste
- [Paquetexpress: Conócenos](https://www.paquetexpress.com.mx/conocenos/) – 40 Jahre Betrieb, 12.200+ Mitarbeitende, 240+ Länder
- [Paquetexpress: Sucursales](https://www.paquetexpress.com.mx/sucursales) – Filialsuche
- [Paquetexpress: Preguntas Frecuentes](https://www.paquetexpress.com.mx/preguntas-frecuentes/) – Tracking ohne Konto, Code vs. Guía vs. Referenz, Wechsel Ocurre/Hauszustellung, Adresskorrektur, Besuchskarte, Meldung geöffneter Pakete
- [Paquetexpress: Contact (EN)](https://www.paquetexpress.com.mx/en/contacto/) – `+52 800 821 0208`, Mo–Fr 08:00–21:00, Sa 08:00–15:00
- [Paquetexpress: Contacto (ES)](https://www.paquetexpress.com.mx/contacto/) – spanische Kontaktseite (Hilfe-URL in Peek)
- [Paquetexpress: Solicitud de Reclamación](https://www.paquetexpress.com.mx/contacto/solicitud-de-reclamacion/) – Online-Reklamationsportal
- [Paquetexpress: Clausulado de Solicitud de Servicios](https://www.paquetexpress.com.mx/clausulado-solicitud-servicios/) – PROFECO-Vertrag `3808-2020`: 2 Versuche, 5 Kalendertage kostenlose Abholung, 2 % tägliche Lagergebühr, 90 Tage Verfall, 15 Werktage Reklamation (`reclamaciones@paquetexpress.com.mx`), Sitz Los Mochis
- [PROFECO](https://www.gob.mx/profeco) – mexikanische Verbraucherschutzbehörde
- [Universal Parcel Scraper: paquetexpress](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/paquetexpress) – Guía- und 11–13-Ziffern-Erkennung, Mexiko-Priorität, `/rastreo/`-Linkregel, Ereigniscodes, ETA, Mehrpaket-Guías, `ENT_OCU`-Abholung, entfernte Personennamen und Hilfe-URL
:::
