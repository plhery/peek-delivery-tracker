---
title: SPX Express Brasilien Sendungsverfolgung: Shopee & Status
description: SPX Express Sendung einer Shopee-Bestellung in Brasilien verfolgen: BR-Sendungsnummer finden, portugiesische Statusmeldungen, Agência Shopee und Erstattung.
slug: spx-express-brasilien-sendungsverfolgung
published: 2026-11-21
updated: 2026-11-21
---

SPX Express (Shopee Xpress) ist das eigene Logistiknetz von Shopee in **Brasilien**. Es holt Bestellungen bei Verkäufern ab und stellt sie an der Haustür oder in einer *Agência Shopee* zu – einem Partnergeschäft zur Paketabholung. Shopee nennt diesen Versandweg *Entrega Padrão* (Standardlieferung) und verweist für Sendungsverfolgung, voraussichtliches Lieferdatum und Rückerstattungen auf die Shopee-App in Brasilien. Auch auf [spx.com.br](https://spx.com.br/track) sowie im Suchfeld oben lässt sich die Nummer direkt abfragen.

## SPX Express Sendungsnummer: Fundort und Aufbau

Öffne in der brasilianischen Shopee-App `Eu` > `Minhas Compras`, wähle die Bestellung unter `A Caminho` (in manchen Hilfeartikeln `A Receber`) und tippe auf `Status de Envio`, um alle Schritte zu sehen. Shopee hinterlegt die Sendungsnummer dort automatisch, sobald sie vorliegt, zusammen mit dem voraussichtlichen Zustelldatum.

Diese Nummernformen erkennt Peek (mit frei erfundenen Zeichen):

| Aufbau | Bedeutung |
| --- | --- |
| `BR4829105736491` | SPX Express in Brasilien: `BR`, 12 Ziffern und eine Endziffer (15 Zeichen). |
| `BR482910573649X` | SPX Express in Brasilien: `BR`, 12 Ziffern und ein Endbuchstabe (15 Zeichen). |
| `BR482910573649XSPXLM48291057` | SPX Express: derselbe 15-stellige Code gefolgt von `SPXLM` und 8 Ziffern, wie auf manchen Marktplatz-Etiketten. |
| `EA482910576BR` | Zwei Buchstaben, neun Ziffern und `BR`: eine Postsendungsnummer der brasilianischen Correios, nicht von SPX. |

Die Bestellnummer (`ID do pedido`) ist keine Sendungsnummer, wird aber vom Shopee-Kundendienst bei Rückfragen benötigt.

Nicht jede Shopee-Bestellung in Brasilien läuft über SPX. Shopee unterscheidet *Entrega Padrão* (Shopee Xpress), *Entrega Correios* (deren Code auch bei den Correios funktioniert) und *Entrega do Vendedor*, bei der der Händler selbst zustellt und den Status pflegt. Für internationale Bestellungen nach Brasilien nennt Shopee die Correios – mit lokaler Nummer in der App – oder den Logistikpartner Anjun Courier; dennoch führt auch das SPX-Tracking eigene Meldungen für bestimmte Auslandssendungen (siehe unten). Weitere Hinweise bieten [Formate von Sendungsnummern](guide:tracking-number-formats) und [Sendungsnummer finden](guide:find-tracking-number).

## Was die SPX-Statusmeldungen bedeuten

SPX gibt seine Statusmeldungen auf Portugiesisch aus (einzelne Zeilen auf Englisch); bei manchen Einträgen folgt nach einem Doppelpunkt der Ort.

| Meldung bei SPX | Bedeutung für dein Paket | Anzeige in Peek |
| --- | --- | --- |
| `Pedido em preparação` | Der Verkäufer bereitet die Bestellung vor; SPX hat das Paket noch nicht. | „Etikett erstellt“ |
| `Pedido postado …` | Der Verkäufer hat das Paket an einer Annahmestelle in Brasilien abgegeben (spx.com.br nennt dahinter die Stadt); SPX holt es dort ab. | „Beim Paketdienst eingegangen“ |
| `Seu pedido foi coletado` | SPX hat das Paket beim Verkäufer abgeholt. | „Beim Paketdienst eingegangen“ |
| `Seu pedido chegou ao centro logístico: …` | Ankunft in einem SPX-Logistikzentrum in Brasilien. | „Unterwegs“ |
| `Seu pedido saiu do centro logístico: …` | Weitertransport aus einem Logistikzentrum. | „Unterwegs“ |
| `O pedido está em separação no centro logístico, novas atualizações em até 1 dia.` | Sortierung im Zentrum; SPX kündigt neue Meldungen innerhalb eines Tages an. | „Unterwegs“ |
| `Pedido em trânsito` | Unterwegs zwischen Standorten. Zwischen brasilianischen Bundesstaaten oder weit entfernten Städten bleibt das Paket laut Shopee oft mehrere Tage im Fernverkehr-Lkw, bis es im Verteilzentrum des Zielbundesstaats entladen und gescannt wird. | „Unterwegs“ |
| `Pedido em rota de entrega para seu endereço, aguarde pelas próximas horas.` | In der Zustelltour zu deiner Adresse für die nächsten Stunden. | „In Zustellung“ |
| `Pedido deve ser retirado na agência: …` | Das Paket liegt in der genannten Agentur zur Abholung bereit. | „Abholbereit“ |
| `Houve um imprevisto durante a entrega do seu pedido …`, `Um imprevisto ocorreu …`, `Insucesso na entrega por endereço incorreto…` | Die Zustellung konnte an diesem Tag nicht abgeschlossen werden; SPX plant einen weiteren Versuch ein. | „Zustellversuch“ |
| `Pedido entregue` | Zugestellt. | „Zugestellt“ |
| `Pedido será devolvido ao vendedor`, `Pedido será devolvido ao vendedor. Por favor, aguarde.` | Das Paket geht an den Verkäufer zurück; der Zusatz bittet lediglich um Geduld. | „Zurück an Absender“ |
| `Pedido devolvido` | Beim Verkäufer eingetroffen. | „Zurück an Absender“ |
| `Pedido extraviado. Estorno será processado automaticamente.` | Sendung als verloren gemeldet; laut SPX erfolgt die Rückerstattung automatisch. | „Problem gemeldet“ |
| `Pedido descartado` | Nach gescheiterter Zustellung wurde das Paket entsorgt – weder zugestellt noch an den Händler zurückgesandt. Wende dich an Shopee. | „Problem gemeldet“ |

Manche Pakete erhalten stattdessen Meldungen aus dem Flottensystem von SPX, beginnend mit `Seu pacote`, etwa `Seu pacote foi recebido pelo centro de triagem` (im Sortierzentrum), `Seu pacote foi recebido pelo centro de distribuição` (im regionalen Verteilzentrum), `Seu pacote está sendo entregue pelo entregador` (mit vorangestelltem Hub: in Zustellung) und `Seu pacote foi entregue` (zugestellt). Peek ordnet ihnen dieselben Phasen zu, zum Beispiel „In Zustellung“.

**Sendungen aus dem Ausland nach Brasilien.** Zeilen mit `[China]` stammen aus dem Abgangsland, etwa `[China]O pedido foi liberado na alfândega de exportação` nach erledigter Ausfuhrzollabfertigung. Danach folgen `Pedido chegou no Brasil` (in Brasilien eingetroffen), `Pedido está na alfândega de importação` (beim brasilianischen Einfuhrzoll), `Pedido em inspeção na alfândega de importação` (in Zollprüfung) und auf Englisch `Import Customs Cleared` (vom Zoll freigegeben). Peek zeigt während der beiden Zollschnitte „Beim Zoll“ und nach `Import Customs Cleared` wieder „Unterwegs“.

Die Meldung `O pacote não pôde ser importado, será retornado ou confiscado pela alfândega` bedeutet hingegen, dass der brasilianische Zoll die Einfuhr abgelehnt hat und das Paket zurückgeht oder eingezogen wird; Peek zeigt „Problem gemeldet“. Beantrage in diesem Fall die Erstattung über Shopee.

## Zustellung in Brasilien: Versuche, Codewort und Agência Shopee

Alle folgenden Zustellregeln gelten für Shopee-Lieferungen innerhalb **Brasiliens**:

**Laufzeiten.** Weder Käufer noch Verkäufer können einen Liefertag oder ein Zeitfenster vereinbaren. Shopee nennt für Bestellungen bei Händlern in Brasilien 1 bis 25 Werktage ab Abholung und für internationale Sendungen in der Regel 30 bis 50 Kalendertage. Das voraussichtliche Datum steht auf der Bestellseite.

**Zustellversuche.** Die Logistikpartner von Shopee unternehmen 3 Zustellversuche, bevor die Bestellung an den Verkäufer zurückgeht; schlägt die Zustellung endgültig fehl, wird der Kaufpreis automatisch erstattet. Die Lieferadresse lässt sich nur einmal vor Bestätigung der Zahlung ändern; danach rät Shopee, die drei Versuche abzuwarten und die automatische Erstattung zu nutzen. Siehe auch [Zustellung verpasst](guide:missed-delivery).

**Codewort (*palavra-chave*).** Bestimmte Bestellungen in Brasilien erhalten ein zufälliges Passwort, das an der Tür genannt werden muss; nur wer es kennt, darf das Paket entgegennehmen. Es steht auf der Bestellseite sowie in der E-Mail und App-Mitteilung von Shopee. Wer nicht zu Hause ist, kann es einer Vertrauensperson wie einem Nachbarn oder dem Pförtner (*porteiro*) weitergeben. Nenne das Codewort niemals, bevor du das Paket in den Händen hältst, und lehne Sendungen auf fremden Namen oder eine falsche Adresse ab.

**Abholung in der Agência Shopee (`Retire Fácil`).** Wo in Brasilien verfügbar, wird diese Option beim Kauf unter `Opção de Envio` > `Ver mais` > `Retire Fácil` gewählt; ein nachträglicher Wechsel auf Haustürzustellung ist ausgeschlossen. Nach Eintreffen sendet Shopee eine PIN per WhatsApp, E-Mail oder App, wo auch der letzte Abholtag steht. Am Schalter werden PIN und Sendungsnummer vorgezeigt; jede Person mit beiden Angaben kann abholen. Die Lagerfrist beträgt 7 Werktage, danach holt SPX das Paket zurück. Fällt eine Agentur vorübergehend aus, kann Shopee an die hinterlegte Standardadresse zustellen. Über die [Standortkarte von SPX](https://spx.com.br/service-point) lassen sich Agenturen mit der Auswahl `Retirar um pacote` suchen.

## Auslandskäufe nach Brasilien: Remessa Conforme und Abgaben

Die brasilianische Steuer- und Zollbehörde Receita Federal führt Shopee unter den zertifizierten Plattformen ihres Programms *Remessa Conforme* (PRC). Bei zertifizierten Shops werden Einfuhrabgabe und ICMS direkt beim Bezahlen eingezogen. Prüfe an der Kasse, ob die Steuern ausgewiesen sind: Fehlen sie dort, werden sie laut Receita Federal bei Ankunft in Brasilien erhoben und der ermäßigte Zollsatz entfällt.

Für Einfuhranmeldungen in Brasilien, die ab dem 12. Mai 2026 registriert wurden, nennt die Receita Federal folgende Sätze:

- **Einfuhrsteuer (*Imposto de Importação*)** auf einer PRC-zertifizierten Plattform für Sendungen an Privatpersonen: 0 % bis zu einem Zollwert von 50 US$ (Warenwert plus Versand und Versicherung); über 50 US$ gilt ein Satz von 60 % abzüglich eines Betrags im Gegenwert von 30 US$ auf die berechnete Steuer.
- **ICMS** (Steuer der brasilianischen Bundesstaaten): derzeit 17 % bis 20 % je nach Bundesstaat, unabhängig von einer PRC-Zertifizierung.

Laut Shopee kann eine ungeklärte brasilianische Steuernummer (CPF) internationale Käufe blockieren; gezahlte Einfuhrabgaben werden nicht erstattet. Hält der brasilianische Zoll eine von Anjun Courier beförderte Sendung fest, verlangt Shopee Zahlungs- und Kaufpreisnachweise, eine Kopie eines brasilianischen Ausweises (RG oder CNH) sowie das Wertnachweisformular RCV der Receita Federal. Bei Correios-Sendungen sind offene Schritte im Correios-Tracking ersichtlich. Die Receita Federal verschickt keine Zahlscheine (*boletos*) und fordert Abgaben weder telefonisch noch per E-Mail an. Allgemeines dazu unter [Paket beim Zoll](guide:customs).

## SPX-Tracking steht still, Lieferung verspätet oder fehlt

**Tagelang kein neues Ereignis.** Laut Shopee ist das in Brasilien normal: Ein neuer Eintrag entsteht erst beim Scan im nächsten Zentrum, sodass mehrtägige Lkw-Fahrten zwischen Bundesstaaten, Wochenenden und Rückstaus an Hubs die Anzeige unverändert lassen. Auch scheinbare Umwege gehören zur Netzplanung; oft erscheinen beim Start der Zustelltour mehrere Schritte auf einmal. Mehr unter [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating).

**Nummer funktioniert nicht.** Das Paket wurde eventuell noch nicht abgeholt, oder der Code ist fehlerhaft beziehungsweise noch nicht im System aktiv. Frage den Verkäufer im Shopee-Chat; laut Shopee heißt das nicht, dass die Sendung verloren ist.

**Verspätet.** Warte das voraussichtliche Lieferdatum ab. Danach lässt sich in der App eine Rückerstattung beantragen: `Eu` > `A Caminho` > Bestellung > `Pedido de Reembolso` mit dem Grund `Não recebi o pedido`. Reagiert der Verkäufer nicht binnen 2 Tagen, wird die Erstattung automatisch ausgelöst. Laut Shopee treffen die meisten verspäteten Bestellungen innerhalb von 5 Tagen nach dem Schätzdatum ein.

**Als zugestellt markiert, aber nicht erhalten.** Frage bei Nachbarn, Haushaltsangehörigen und an der Hauspforte (*porteiro*) nach und beantrage dann in der App die Erstattung – bei *Entrega Padrão* innerhalb von 7 Tagen ab Zustellung. Shopee kann eine Nachforschung beim Transportdienst (*acareação*) veranlassen, bei der sich dieser per WhatsApp oder E-Mail meldet oder der Fahrer nochmals vorbeikommt. Siehe auch [zugestellt, aber nicht erhalten](guide:delivered-not-received).

**Rücksendung.** Nach `Pedido será devolvido ao vendedor` ist das Paket auf dem Rückweg zum Händler. Nach fehlgeschlagener Zustellung erstattet Shopee automatisch; siehe [zurück an den Absender](guide:returned-to-sender).

## Kontakt zu SPX Express: Abwicklung über Shopee

Shopee weist darauf hin, dass Käufer den Logistikdienstleister zu Sendungsverfolgung oder Zustellung nicht direkt kontaktieren können – Anfragen laufen über Shopee im Rahmen der *Garantia Shopee* in Brasilien:

- **Chat Shopee:** in der App unter `Eu` > `Central de Ajuda` bis `Atendimento ao Cliente` scrollen und `Chat Shopee` öffnen. Erreichbar Montag bis Freitag von 8:00 bis 22:00 Uhr sowie Samstag und Sonntag von 8:00 bis 20:00 Uhr (Brasília-Zeit).
- **Anfrageformular:** jederzeit über die [Hilfeseite zu den Servicezeiten von Shopee](https://help.shopee.com.br/portal/4/article/141507) mit bereitlegender `ID do pedido`. (Im Fußbereich von `spx.com.br`, dem Portal für Partnerfahrer und Agenturen, steht zudem der WhatsApp-Kanal `(11) 2770-1438`, Montag bis Samstag außer an Feiertagen von 8:00 bis 20:00 Uhr Brasília-Zeit.)
- **Fehlverhalten eines Zustellers** (etwa Geldforderungen an der Tür): über `Chat Shopee` melden.
- **Weiterhin ungelöst in Brasilien:** Shopee Brasil nimmt an der staatlichen Schlichtungsplattform [consumidor.gov.br](https://www.consumidor.gov.br/pages/principal/) teil, auf der registrierte Unternehmen innerhalb von 10 Tagen antworten (`gov.br`-Konto der Stufe Silber oder Gold erforderlich).

> Shopee kontaktiert Nutzer ausschließlich über offizielle Kanäle wie die App, verifizierte Social-Media-Konten oder E-Mail-Adressen auf `shopee.com`, und Versandkosten werden immer in der App bezahlt. Tipps gegen gefälschte Paketnachrichten bietet [Gefälschte Paket-SMS erkennen](guide:delivery-scam-texts).

## SPX Express Pakete mit Peek verfolgen

[Peek](/) erkennt SPX Express Brazil sofort anhand eines `BR`-Codes mit 12 Ziffern und Endziffer oder Endbuchstabe (auch mit Anhang `SPXLM…`) und blendet „Erkannter Paketdienst“ samt „Ändern“ ein. Ebenso ordnet ein Link von `spx.com.br` (`/track?` oder `/detail/` mit der `BR`-Nummer) das Paket direkt SPX zu – selbst ohne vorangestelltes `https://`. Peek fragt bei SPX ausschließlich `BR`-Sendungsnummern ab, keine Shopee-Bestellnummern.

Peek liest dieselben Trackingdaten wie `spx.com.br` und zeigt den aktuellen Status, die Meldungen von SPX (auf Portugiesisch, teils Englisch), den Ort von Scans in Logistikzentren, Hubs, Häfen oder Abholagenturen sowie den genauen Zustellzeitpunkt umgerechnet in die Zeitzone deines Geräts – auf `peektracker.com` sowie in der iPhone- und Android-App endet der Verlauf mit „Uhrzeiten in deiner Zeitzone: …“. Außerdem macht Peek Meilensteine sichtbar, die `spx.com.br` ausblendet, darunter `Seu pedido chegou ao último centro logístico` (Ankunft im letzten Logistikzentrum) oder eine fehlgeschlagene Abholung beim Verkäufer („Etikett erstellt“); folgt eine fehlgeschlagene Abholung auf `Pedido postado …`, liegt das Paket weiter in der Annahmestelle („Beim Paketdienst eingegangen“), sofern nicht vermerkt ist, dass der Absender die Bestellung noch nicht vorbereitet hatte („Etikett erstellt“). Ein Lieferdatum, Gewicht oder den Namen eines Zustellpartners liefert SPX nicht. Über einen geteilten Link erscheint bei einer Zustellung nur „Zugestellt“ ohne den Namen der annehmenden Person. Codewort, Agentur-PIN und Rückerstattungen bleiben in der Shopee-App.

Solange SPX die Nummer noch nicht kennt, zeigt Peek „Warten auf SPX Express Brazil“ und prüft weiter. Ist die Nummer auch nach zwei Tagen weder bei SPX noch anderswo bekannt, meldet Peek „Auch nach zwei Tagen kennt der Paketdienst diese Nummer nicht. Prüfe sie, wähle einen anderen Paketdienst oder lösche das Paket.“ und fragt danach einmal täglich sowie eine Woche nach dem Hinzufügen wöchentlich ab. Bleibt eine bereits gestartete Sendung vier Tage ohne neuen Scan unterwegs, blendet Peek „Hol dir Hilfe bei SPX Express Brazil“ ein – der Link führt zur Servicezeiten-Seite von Shopee.

Solange du das Paket geöffnet hast oder am Zustelltag Benachrichtigungen aktiviert sind, prüft Peek bis zu alle 10 Minuten auf Neuigkeiten – und ab 6:00 Uhr Ortszeit an dem Tag, an dem SPX das Paket als in Zustellung scannt, bis zu alle 2 Minuten, solange der eigene Tracker von SPX antwortet (über externe Trackingdienste allein stündlich, alle 3 Stunden nach einem ruhigen Tag und am Zustelltag bei geöffnetem Paket oder aktiven Mitteilungen bis zu alle 15 Minuten); ansonsten sowie nach zwei Tagen ohne Änderung (oder 12 Stunden bei „Etikett erstellt“ oder „Beim Paketdienst eingegangen“) stündlich. Benachrichtigungen lassen sich auf „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“ stellen. Weder SPX noch Shopee kann Peek für dich kontaktieren.

## Häufige Fragen zur SPX Express Brasilien Sendungsverfolgung

### Gehört SPX Express zu Shopee?

Ja: Shopee bezeichnet SPX Express als seine eigene offizielle Logistik in Brasilien. Hinter `spx.com.br` steht die SPX Express Brasil Ltda. mit über 100 Logistik-Hubs und 11 Verteilzentren in Brasilien.

### Wie verfolge ich ein Paket von SPX Express?

In der brasilianischen Shopee-App unter `Eu` > `Minhas Compras` > Bestellung > `Status de Envio`, durch Einfügen der `BR`-Nummer in Peek oder über die Suche auf `spx.com.br`.

### Was bedeutet Pedido em rota de entrega para seu endereço?

Das Paket befindet sich in Brasilien auf der Zustelltour zu deiner Adresse und soll in den nächsten Stunden eintreffen. Falls für die Bestellung ein Codewort (*palavra-chave*) vergeben wurde, halte es für den Fahrer bereit.

### Wie lange dauert die Lieferung mit SPX Express in Brasilien?

Shopee nennt 1 bis 25 Werktage ab Abholung bei nationalen Verkäufern in Brasilien und im Allgemeinen 30 bis 50 Kalendertage bei internationalen Bestellungen. Das individuelle Schätzdatum steht in der Bestellübersicht der App.

### Kann ich SPX Express direkt anrufen oder anschreiben?

Für Bestellstatus und Zustellung verweist Shopee ausschließlich an den eigenen Kundendienst über `Chat Shopee` in der App (Montag bis Freitag 8:00 bis 22:00 Uhr, am Wochenende 8:00 bis 20:00 Uhr Brasília-Zeit) oder das Online-Anfrageformular.

:::sources
- [SPX Express: Startseite](https://spx.com.br/br) – integrierte Shopee-Logistik in Brasilien, über 100 Hubs und 11 Verteilzentren, SPX Express Brasil Ltda., Partner-/Agentur-WhatsApp
- [SPX Express: Sendungsverfolgung](https://spx.com.br/track) – öffentliche Tracking-Suche von SPX Brasilien
- [SPX Express: Sobre Nós](https://spx.com.br/br/help-center/about-us.html) – Sendungsverfolgung in der Shopee-App und landesweites Netz
- [SPX Express: Encontre uma Agência Shopee](https://spx.com.br/service-point) – Agenturkarte und Auswahl Retirar um pacote
- [Shopee: O que é a SPX Express?](https://help.shopee.com.br/portal/4/article/197188) – eigene offizielle Logistik von Shopee in Brasilien
- [Shopee: O que é Shopee Xpress (SPX)?](https://help.shopee.com.br/portal/4/article/76163) – Abholung, Verteilzentren und Logistikpartner
- [Shopee: Posso entrar em contato com os parceiros logísticos da Shopee?](https://help.shopee.com.br/portal/4/article/76264) – Versandarten, kein Direktkontakt zum Zusteller und 3 Zustellversuche
- [Shopee: Como faço para verificar o status do meu pedido?](https://help.shopee.com.br/portal/4/article/76323) – Verfolgung von Correios-Codes
- [Shopee: Quais são as opções de envio disponíveis na Shopee?](https://help.shopee.com.br/portal/4/article/76335) – automatische Bereitstellung der Sendungsnummer
- [Shopee: Como eu acho a data prevista de entrega para o meu pedido?](https://help.shopee.com.br/portal/4/article/81887) – voraussichtliches Lieferdatum und Menü Status de Envio
- [Shopee: Como encontro o ID do pedido da minha compra?](https://help.shopee.com.br/portal/4/article/76194) – Bestellkennung ID do pedido
- [Shopee: O que é logística do vendedor?](https://help.shopee.com.br/portal/4/article/132039) – Zustellung durch den Verkäufer selbst
- [Shopee: Como minha encomenda do exterior será entregue quando chegar ao Brasil?](https://help.shopee.com.br/portal/4/article/76363) – Zustellung von Auslandssendungen über die Correios
- [Shopee: Por que meu pedido está parado na alfândega do Brasil?](https://help.shopee.com.br/portal/4/article/153683) – Correios oder Anjun Courier, Ausweisdokumente und Formular RCV
- [Shopee: Quando receberei meu pedido?](https://help.shopee.com.br/portal/4/article/76349) – 1 bis 25 Werktage im Inland und 30 bis 50 Kalendertage international
- [Shopee: Posso agendar a data e hora de entrega do meu pedido?](https://help.shopee.com.br/portal/4/article/76333) – keine Terminbuchung, Versuche und automatische Erstattung
- [Shopee: Posso alterar meu endereço de entrega depois que meu pedido for feito?](https://help.shopee.com.br/portal/4/article/76279) – einmalige Adressänderung vor Zahlungsbestätigung
- [Shopee: O que é a palavra-chave no meu pedido?](https://help.shopee.com.br/portal/4/article/127204) – Ablauf mit Codewort an der Haustür
- [Shopee: O que fazer quando não estiver em casa para informar a palavra-chave?](https://help.shopee.com.br/portal/4/article/127206) – Weitergabe an eine Vertrauensperson
- [Shopee: O que fazer se minha palavra-chave não funcionar corretamente?](https://help.shopee.com.br/portal/4/article/127205) – Code erst bei Übergabe nennen und falsch adressierte Pakete ablehnen
- [Shopee: Retirada de pacotes na Agência Shopee](https://help.shopee.com.br/portal/4/article/164642) – Auswahl Retire Fácil, Benachrichtigungen, PIN und Abholfrist
- [Shopee: Sobre a Agência Shopee](https://help.shopee.com.br/portal/4/article/164639) – Abholung mit PIN und Sendungsnummer, 7 Werktage Lagerfrist und Umleitung bei Ausfall
- [Shopee: Meu pedido está sem atualização no rastreamento](https://help.shopee.com.br/portal/4/article/223973) – mehrtägige Fernfahrten zwischen Bundesstaaten und 5 Tage nach Schätzdatum
- [Shopee: Por que meu pedido está parado ou mudou de rota?](https://help.shopee.com.br/portal/4/article/159363) – scheinbare Umwege über Hubs
- [Shopee: Por que meu código de rastreio não está funcionando?](https://help.shopee.com.br/portal/4/article/76369) – Gründe für noch unbekannte Codes
- [Shopee: O que devo fazer se não receber meu pedido até a data prevista de entrega?](https://help.shopee.com.br/portal/4/article/76321) – Erstattungsantrag und 2 Tage Antwortfrist des Verkäufers
- [Shopee: O que devo fazer se não receber um pedido, mas o status está como Entregue?](https://help.shopee.com.br/portal/4/article/76207) – Nachbarschaftsprüfung und Nachforschung acareação
- [Shopee: Como faço para solicitar devolução/reembolso?](https://help.shopee.com.br/portal/4/article/76204) – 7-Tage-Frist nach Zustellung bei Entrega Padrão
- [Shopee: Como entro em contato com o Atendimento ao Cliente da Shopee?](https://help.shopee.com.br/portal/4/article/78354) – Zugang zum Chat Shopee in der App
- [Shopee: Qual horário do Atendimento ao Cliente da Shopee?](https://help.shopee.com.br/portal/4/article/141507) – Servicezeiten nach Brasília-Zeit und Anfrageformular
- [Shopee: O entregador do meu pedido teve uma má conduta](https://help.shopee.com.br/portal/4/article/224511) – Beschwerde über Zusteller im Chat
- [Shopee: Como faço para detectar um golpe?](https://help.shopee.com.br/portal/4/article/76330) – offizielle Kanäle auf shopee.com und Versandzahlung ausschließlich in der App
- [Shopee: Por que não consigo concluir minha compra internacional?](https://help.shopee.com.br/portal/4/article/141561) – Sperre bei unregelmäßigem CPF
- [Shopee: Como solicitar devolução/reembolso ou realizar o cancelamento da minha compra internacional?](https://help.shopee.com.br/portal/4/article/134867) – Rückerstattung bei Auslandskäufen ohne Erstattung der Einfuhrsteuern
- [Receita Federal: Tributação](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/topicos/tributacao) – PRC-Steuersätze ab 12. Mai 2026, ICMS von 17 % bis 20 % und Warnung vor gefälschten Zahlscheinen
- [Receita Federal: Em que momento vou pagar os impostos?](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/preciso-pagar-impostos-nas-compras-internacionais/em-que-momento-pagarei-o-imposto) – Steuerzahlung beim Kaufabschluss
- [Receita Federal: Empresas certificadas no Programa Remessa Conforme](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/empresas-certificadas-no-programa-remessa-conforme-prc) – Zertifizierung von Shopee im PRC-Programm
- [Consumidor.gov.br: Início](https://www.consumidor.gov.br/pages/principal/) – 10 Tage Antwortfrist und gov.br-Konto Silber oder Gold
- [Consumidor.gov.br: Empresas participantes](https://www.consumidor.gov.br/pages/principal/empresas-participantes) – Teilnahme von Shopee Brasil
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Erkennung von BR-Nummern und spx.com.br-Links, SPX-Statuscodes und Flottenmeldungen, ausgeblendete Meilensteine und Datenschutz
:::
