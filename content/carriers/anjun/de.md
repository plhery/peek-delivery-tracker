---
title: Anjun Logistics Sendungsverfolgung: Status & SAC-Kontakt
description: Verfolge ein Anjun-Logistics- oder Anjun-Express-Paket: AJ-Nummern, China- und Brasilien-Status, Zoll Remessa Conforme und aktive SAC-Kontakte.
slug: anjun-logistics-sendungsverfolgung
published: 2026-11-24
updated: 2026-11-24
---

Anjun Logistics (in Brasilien als Anjun Express tätig) ist ein 2011 in China gegründeter Paketdienst für grenzüberschreitenden und inländischen E-Commerce, der 2019 in Brasilien startete und 2025 eine landesweite Zustellung aufbaute. Das Unternehmen übernimmt die Abholung in China, den Luftexport, die Zollabfertigung in Brasilien und die Zustellung auf der letzten Meile — entweder über das eigene Zustellnetz und Abholpunkte oder durch Übergabe an [Correios](carrier:correios-br) nach der Einfuhrfreigabe. Internationale Sendungen prüfst du im [globalen Anjun-Tracker](https://anjuntrack.com/trajectory), brasilianische Inlandspakete bei [Anjun Express Brasilien](https://anjunexpress.com/trackPackage), oder du fügst die Sendungsnummer oben ein.

## Anjun Sendungsnummer: Format und Fundort

Die Sendungsnummer steht in der Versandbenachrichtigung des Online-Shops oder Marktplatzes (etwa bei Bestellungen von chinesischen Plattformen nach Brasilien). Eigene Anjun-Codes bestehen aus 17 Zeichen: `AJ`, einem 6-stelligen Datum (`JJMMTT`) und 9 Ziffern.

| Format (erfunden) | Was es ist |
| --- | --- |
| `AJ261015482917364` | Anjun-Logistics- / Anjun-Express-Code: `AJ` + 6-stelliges Datum + 9 Ziffern. |

Anjun betreibt zwei Tracking-Portale: [anjuntrack.com](https://anjuntrack.com/trajectory) zeigt grenzüberschreitende Sendungen von China nach Brasilien, während [anjunexpress.com](https://anjunexpress.com/trackPackage) auf brasilianische Last-Mile- und Inlandspakete ausgerichtet ist. Beginnt dein Code stattdessen mit `BR` (15 Zeichen), prüfe [SPX Express Brazil](carrier:spx-br); eine 15-stellige Nummer mit `8880` gehört zu [J&T Express Brazil](carrier:j-and-t-br); und ein 13-stelliger Postcode mit Endung `BR` gehört zu [Correios](carrier:correios-br). Weitere Präfixe findest du unter [Sendungsnummern-Formate](guide:tracking-number-formats).

## Bedeutung der Anjun Logistics Tracking-Status

Grenzüberschreitende Anjun-Pakete wechseln meist von englischen Scan-Zeilen in China und Hongkong zu portugiesischen Einträgen, sobald sie in Brasilien ankommen.

| Anjun meldet | Was das für dich bedeutet | Peek zeigt |
| --- | --- | --- |
| `Objeto postado` | Elektronische Vorankündigung oder Einlieferung vor dem Lagereingang. | „Etikett erstellt“ |
| `The goods have arrived at Anjun`; `Objeto coletado` als erster Scan eines innerhalb Brasiliens verschickten Pakets | Anjuns Lager in China hat das Paket übernommen, oder Anjun hat es bei einem Absender in Brasilien abgeholt. | „Beim Paketdienst eingegangen“ |
| `The goods have been issued`, `Departed Facility in SHENZHEN`, `Arrived at Sort Facility HONG KONG INTERNATIONAL AIRPORT`, `Departed Facility in HONG KONG INTERNATIONAL AIRPORT`, `Arrived at Sort Facility Destination Airport` | Exportabwicklung, Abflug ab Shenzhen oder Hongkong und Ankunft am Zielflughafen in Brasilien. | „Unterwegs“ |
| `Recebimento de cargas no armazém courier` | Eingang im Kurierterminal in Brasilien vor der Zollprüfung. | „Unterwegs“ |
| `Entrada no armazém alfândegado de TFK`, `Informações enviadas para análise da autoridade aduaneira/órgãos anuentes`, `Análise concluída - importação autorizada` | Eingang im Zolllager, Übermittlung der Zollanmeldung und Abschluss der Einfuhrprüfung. | „Beim Zoll“ |
| `Pacote Liberado Aguadando a Coleta de Last Mile`, `Saída do Centro Internacional`, `Coletado por Last Mile`, `Objeto coletado`, `Objeto recebido pelos Correios do Brasil` | Vom Zoll freigegeben und an Anjuns Zustellnetz oder Correios übergeben. | „Unterwegs“ |
| `O pacote chegou ao centro de trânsito`, `O pacote saiu do centro de trânsito`, `Objeto chegou ao ponto de entrega` | Durchlauf durch brasilianische Transit-Hubs und Ankunft im örtlichen Depot (noch nicht in Zustellung). | „Unterwegs“ |
| `Objeto saiu para entrega ao destinatário` | Ein Zusteller ist heute mit deinem Paket auf Auslieferungstour. | „In Zustellung“ |
| `A entrega falhou` (z. B. `Cliente ausente` oder `Endereço fora da rota`) | Zustellung fehlgeschlagen (Empfänger abwesend oder Adresse außerhalb der Route). | „Zustellversuch“ |
| `A entrega falhou: Pacote não pertence à Base` | Das Paket landete im falschen lokalen Depot und wird intern umgeleitet, statt bei dir zugestellt zu werden. | „Unterwegs“ |
| `O pacote está a caminho do centro de trânsito para devolução.` | Rücklauf zu einem Transitzentrum nach fehlgeschlagenen Zustellungen; ein weiterer Versuch ist noch möglich. | „Problem gemeldet“ |
| `O pacote foi assinado`, `Objeto entregue ao destinatário` | Anjun hat das Paket als unterschrieben und zugestellt erfasst. | „Zugestellt“ |

Beachte, dass `Objeto chegou ao ponto de entrega` die Ankunft im örtlichen Anjun-Depot bedeutet — nicht an einem Abholschalter für Kunden. Warte auf `Objeto saiu para entrega ao destinatário`, bevor du den Fahrer erwartest.

## Netzwerk und Zustellung von Anjun Express in Brasilien

Auf der [brasilianischen Website von Anjun Express](https://anjunexpress.com/home) nennt das Unternehmen First-Mile-Abholung, [Last-Mile-Zustellung](https://anjunexpress.com/lastMile), B2B-Logistik, Lagerung, Direktversand und Super Express; die Last-Mile-Seite nennt zusätzlich ein 25.000 m² großes Zolllager am Flughafen São Paulo GRU. Die Startseite gibt mehr als 1.000 Sammelpunkte, über 350 Fahrzeuge und mehr als 2.000 Beschäftigte an (die [Partnerseite für Sammelpunkte](https://anjunexpress.com/collectionPoint) nennt über 500 Punkte).

Anjun Express veröffentlicht auf seinen öffentlichen Seiten weder eine feste Anzahl an Zustellversuchen noch eine garantierte Lieferzeit oder Lagerfrist. Genaue Fristen zu einer Online-Bestellung stehen auf der Bestellseite des jeweiligen Shops oder Marktplatzes.

## Zollabfertigung und Remessa Conforme in Brasilien

Da die meisten Anjun-Sendungen aus China nach Brasilien kommen, durchlaufen sie den brasilianischen Zoll nach dem [Programa Remessa Conforme (PRC) der Receita Federal](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/programa-remessa-conforme-o-que-e-como-funciona):

- **Zertifizierter Marktplatz oder Shop:** Abgaben werden beim Checkout gezahlt. Für Privatsendungen bis 50 US-Dollar (Warenwert, Fracht und Versicherung) gilt 0 % Einfuhrzoll plus 17 % bis 20 % bundesstaatliche ICMS. Über 50 US-Dollar fallen 60 % Einfuhrzoll minus 30 US-Dollar Rabatt auf den Zollbetrag plus 17 % bis 20 % ICMS an.
- **Nicht zertifizierter Shop:** 60 % Einfuhrzoll ohne Rabatt (auch bis 50 US-Dollar) plus 17 % bis 20 % ICMS, die bei der Einfuhr vor Freigabe berechnet werden.

Steht dein Paket auf `Entrada no armazém alfândegado de TFK` oder `Informações enviadas para análise da autoridade aduaneira/órgãos anuentes`, prüfen Receita Federal oder beteiligte Behörden die Anmeldung. Prüfe eventuelle Zahlungs- oder Dokumentenanforderungen in deinen Marktplatz-Nachrichten oder direkt beim Zollkontakt von Anjun statt über fremde Links. Siehe [Paket hängt beim Zoll](guide:customs).

## Anjun Paket hängt, Zustellung fehlgeschlagen oder fehlt

Bei Bestellungen über Online-Marktplätze sind Plattform oder Verkäufer für Erstattungen, Ersatzsendungen und Zollklärungen zuständig.

**Kein Scan zwischen China und Brasilien.** Luftexport, Wartezeiten am Flughafen und der Eingang im Zolllager verursachen oft mehrtägige Lücken zwischen `Departed Facility in HONG KONG INTERNATIONAL AIRPORT` und `Recebimento de cargas no armazém courier`. Siehe [warum die Sendungsverfolgung nicht aktualisiert](guide:tracking-not-updating).

**Falsch sortiertes Depot.** `A entrega falhou: Pacote não pertence à Base` klingt alarmierend, heißt aber nur, dass das Paket ans falsche lokale Depot sortiert wurde und vor dem eigentlichen Zustellversuch intern weitergeleitet wird.

**Als zugestellt markiert, aber nicht erhalten.** Frag zuerst Angehörige, Nachbarn und die *portaria* (Gebäudeempfang) und melde den Fall dann beim Marktplatz und beim SAC von Anjun. Siehe [zugestellt, aber nicht erhalten](guide:delivered-not-received).

**Beschädigt, verloren oder auf dem Rückweg.** Hebe Fotos der beschädigten Verpackung auf und wende dich umgehend an den Verkäufer; siehe [beschädigtes Paket](guide:damaged-parcel), [verlorenes Paket](guide:lost-parcel) und [zurück an den Absender](guide:returned-to-sender).

## Anjun Express Kundenservice und neue E-Mail-Adresse

Auf [anjunexpress.com/home](https://anjunexpress.com/home) weist Anjun Express darauf hin, dass die frühere Adresse `sac@anjunexpress.com.br` seit dem 12. August 2026 abgeschaltet ist, und nennt die aktiven brasilianischen Support-Kanäle:

- **Nationales SAC-Telefon:** `(11) 5026-7008`, Montag bis Freitag von 08:00 bis 17:30 Uhr (außer an Feiertagen).
- **SAC-E-Mail:** `sac@anjun.com.br`.
- **Online-Chat:** verfügbar auf [anjunexpress.com](https://anjunexpress.com/home).
- **Internationaler Express / Zollanfragen:** `atendimento.aduaneiro@anjun.com.br`.
- **Zoll-Ombudsstelle (*Ouvidoria*):** `ouvidoria.aduaneira@anjun.com.br`.
- **Verbraucherschlichtung:** Bleibt ein Streitfall mit einem teilnehmenden Unternehmen ungelöst, können Verbraucher in Brasilien mit Silber- oder Gold-gov.br-Konto [Consumidor.gov.br](https://www.consumidor.gov.br/pages/conteudo/publico/1) nutzen, wo sich Firmen zu einer Antwort binnen 10 Tagen verpflichten.

> Vorsicht vor gefälschten Zoll-Zahlungsnachrichten per SMS oder WhatsApp, die behaupten, dein Anjun-Paket sei blockiert. Prüfe jede Zollmeldung zuerst im Bestellkonto deines Marktplatzes oder direkt über `atendimento.aduaneiro@anjun.com.br`. Siehe [gefälschte Liefer-SMS](guide:delivery-scam-texts).

## Ein Anjun Logistics Paket mit Peek verfolgen

[Peek](/) erkennt Anjun Logistics automatisch an jeder 17-stelligen `AJ`-Sendungsnummer (mit gültigem `JJMMTT`-Datum) sowie an eingefügten Links von `anjuntrack.com` (`/trackQuery?t=` oder `/trajectory?orderNum=`) oder `anjunexpress.com/trackPackage?tracking=` mit einem `AJ`-Code. Willst du den Dienst manuell ändern, tippe beim Hinzufügen auf „Ändern“ oder in der Paketansicht auf „Paketdienst ändern“ und wähle Anjun Logistics (Peek verfolgt mehr als 4.500 Paketdienste).

Peek fragt zuerst `anjuntrack.com` für den grenzüberschreitenden Verlauf ab und prüft, falls dort noch keine Scans stehen, automatisch auch `anjunexpress.com`, sodass brasilianische Inlandspakete ohne Zusatzschritt erscheinen. Peek zeigt die Phase („Etikett erstellt“, „Beim Paketdienst eingegangen“, „Unterwegs“, „Beim Zoll“, „In Zustellung“, „Zustellversuch“, „Problem gemeldet“, „Zugestellt“), englische und portugiesische Scan-Texte, etwaige Gründe fehlgeschlagener Zustellungen (wie `Cliente ausente`), Stadt und Bundesstaat in Brasilien (Straßenadressen werden entfernt), das Zielland sowie lokale Zeiten (China-Zeit in China, Brasilien-Zeit in Brasilien, umgerechnet in deine Gerätezeitzone). Peek entfernt Fortschrittsbalken, Straßenadresse des Empfängers, GPS-Daten, Fahrernamen, Support-Telefonnummern/E-Mails in Scan-Notizen, Unterschriften und Fotos; voraussichtliche Lieferdaten, Gewichte oder nachgelagerte lokale Nummern liefert Anjuns Tracker nicht.

Solange du das Paket in Peek geöffnet hast oder am Zustelltag Benachrichtigungen aktiviert sind, prüft Peek bis zu alle 10 Minuten; an dem Tag, an dem Anjun `Objeto saiu para entrega ao destinatário` meldet, alle 2 Minuten ab 6 Uhr Ortszeit, solange Anjuns eigenes Tracking antwortet. Sonst prüft Peek stündlich. Nach 12 Stunden nur bei „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ oder zwei Tagen ohne Neuigkeiten prüft Peek unabhängig von der Nutzung stündlich, und alle 6 Stunden nach sieben Tagen bei „Etikett erstellt“, außer das Paket ist geöffnet oder für heute erwartet. Antwortet Anjuns Tracker nicht und Peek folgt dem Paket allein über Tracking-Dienste, prüft es stündlich, nach einem stillen Tag alle 3 Stunden und an dem Tag, an dem das Paket in Zustellung ist, bis zu alle 15 Minuten, solange du es geöffnet hast oder Benachrichtigungen an sind.

Ist der neueste Scan vier Tage alt und das Paket noch bei „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“, markiert Peek dies mit dem Datum jenes Scans (zum Beispiel „Keine Aktualisierung seit Mo 12. Okt“). Die Paketseite zeigt dann „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei Anjun Logistics nach.“ Nach drei Wochen ohne neuen Scan heißt es „Seit über drei Wochen nichts Neues. Ist das Paket noch nicht angekommen, frag beim Verkäufer nach. Peek fragt weiter bei Anjun Logistics nach.“

Du kannst Benachrichtigungen für „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“ wählen. Peek überwacht nur den Verlauf und kann weder Pakete verzollen noch Anjun Express für dich kontaktieren.

## Fragen zur Anjun Logistics Sendungsverfolgung

### Warum zeigt mein Anjun-Tracking sowohl Englisch als auch Portugiesisch?

Grenzüberschreitende Sendungen starten in Anjuns Lagern in China und den Export-Hubs in Hongkong mit englischen Scans und wechseln nach Ankunft in Brasilien für Zollabfertigung und lokale Zustellung auf Portugiesisch.

### Was bedeutet `A entrega falhou: Pacote não pertence à Base`?

Das bedeutet, dass das Paket versehentlich an das falsche lokale Anjun-Depot geleitet wurde — nicht, dass ein Fahrer dich verpasst hat. Anjun leitet es ans richtige Depot weiter, deshalb behält Peek es als „Unterwegs“.

### Ist `sac@anjunexpress.com.br` noch aktiv?

Nein. Anjun Express hat `sac@anjunexpress.com.br` am 12. August 2026 abgeschaltet. Nutze `sac@anjun.com.br`, rufe `(11) 5026-7008` an (Mo–Fr, 08:00–17:30 Uhr) oder schreibe bei Zollfragen an `atendimento.aduaneiro@anjun.com.br`.

### Kann Anjun mein Paket an Correios übergeben?

Ja. Manche Auslandspakete zeigen nach dem Zoll `Objeto recebido pelos Correios do Brasil`, was bedeutet, dass Correios die inländische Endzustellung übernimmt, während der Anjun-Tracker die Übergabe protokolliert.

:::sources
- [Anjun Express: Startseite](https://anjunexpress.com/home) – brasilianische Dienste, Netzzahlen, aktive SAC-Kontakte, Zoll-/Ouvidoria-E-Mails und Hinweis zur Abschaltung von `sac@anjunexpress.com.br` (12.08.2026)
- [Anjun Express: Über uns](https://anjunexpress.com/aboutUs) – Gründung 2011, Eintritt in Brasilien 2019, landesweite Abdeckung 2025
- [Anjun Express: Paket verfolgen](https://anjunexpress.com/trackPackage) – brasilianisches Tracking-Portal
- [AnjunTrack: Verlauf](https://anjuntrack.com/trajectory) – grenzüberschreitendes Portal China–Brasilien
- [Anjun Express: Last-Mile](https://anjunexpress.com/lastMile) – brasilianische Zustellung auf der letzten Meile und GRU-Zolllager
- [Anjun Express: Sammelpunkte](https://anjunexpress.com/collectionPoint) – Partnernetz der Sammelpunkte
- [Receita Federal: Programa Remessa Conforme](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/programa-remessa-conforme-o-que-e-como-funciona) – brasilianische Einfuhrzoll- und ICMS-Regeln
- [Consumidor.gov.br](https://www.consumidor.gov.br/pages/conteudo/publico/1) – öffentliche Schlichtungsstelle (10 Tage Antwortfrist, Silber-/Gold-gov.br-Konto)
- [Universal Parcel Scraper: anjun](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/anjun) – `AJ`-Erkennung, Dual-Portal-Abfrage, Status-Mapping, entfernte Felder und Taktung
:::
