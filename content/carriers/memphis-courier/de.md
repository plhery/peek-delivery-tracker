---
title: Memphis Courier Sendungsverfolgung: M-MIA, Zoll & Hilfe
description: Verfolge Memphis-Courier-Importe aus den USA nach Brasilien: M-MIA-Codes, Flughafen-GRU- und ANVISA-Zollstatus, CPF-Regeln und offizielle Kontakte.
slug: memphis-courier-sendungsverfolgung
published: 2026-11-24
updated: 2026-11-24
---

Memphis Courier ist ein rein brasilianisches Logistikunternehmen mit mehr als 25 Jahren Erfahrung bei Express- und formellen Import- sowie Exportsendungen. Über sein Kurierterminal am internationalen Flughafen Guarulhos (GRU) und sein Büro in São Paulo bietet es Luftfracht, Verzollung über die Receita Federal, behördliche Freigaben bei der ANVISA (einschließlich medizinischer Cannabis-Importe nach RDC 660 und RDC 327 für Arzneimittel aus den USA, Europa, Kolumbien und Uruguay) sowie Zustellung in ganz Brasilien.

Das Tracking begleitet das Paket bis zum Flughafen Guarulhos, durch die Prüfung von Receita Federal und ANVISA und weiter bis zur Zustellung.

## Format der Sendungsnummer von Memphis Courier

Sendungsnummern von Memphis Courier folgen einem prägnanten 11-stelligen alphanumerischen Muster:

- **`M` + 7 Ziffern + `MIA`** (insgesamt 11 Zeichen, etwa `M4829173MIA`) — das Muster, das Peek als Memphis Courier erkennt.

Peek akzeptiert Memphis-Courier-Codes mit 6 bis 30 Buchstaben und Ziffern, entfernt Leerzeichen und wandelt Buchstaben in Großbuchstaben um. Das Tracking-Portal von Memphis Courier (`clients.memphiscourier.com.br`) prüft Codes ohne Kundenlogin. Bei der Form `M` + 7 Ziffern + `MIA` fragt Peek ausschließlich Memphis Courier an und zeigt „Memphis Courier hat dieses Paket.“, sobald eine Antwort eintrifft. Weitere typische Paketformate für Brasilien-Importe zeigt der [Leitfaden zu Sendungsnummern-Formaten](guide:tracking-number-formats).

## Sendungsstatus bei Memphis Courier

Der Tracking-Dienst von Memphis Courier liefert dreibuchstabige Scancodes mit portugiesischen Beschreibungen und einer Ortsspalte (Bundesstaat oder Land). Die Fortschrittsleiste auf der Website läuft über Criado (`FLT`), Zoll (`BRP`), Liberado und Entregue; die Scans zu den letzten beiden Schritten (`CLA` und `DLV`) sind nur über diese Schrittbezeichnungen bekannt.

| Code | Meldung von Memphis Courier | Bedeutung |
| --- | --- | --- |
| `FLT` | Seu pedido foi criado com sucesso | Der Versandauftrag wurde vor dem Abflug im System von Memphis Courier angelegt. |
| `CHK` | Entrada registrada no Centro de Distribuição | Eingang in einem Verteilzentrum von Memphis Courier erfasst. |
| `ENO` | Encomenda em trânsito | Unterwegs innerhalb des Netzes von Memphis Courier. |
| `TRA` | Encomenda em transferência | Im Transfer zwischen Umschlagstandorten. |
| `DEP` | A encomenda saiu da origem e está a caminho do aeroporto de destino (Guarulhos - SP) | Hat das Ausgangs-Hub per Flug Richtung Flughafen Guarulhos (GRU) verlassen. |
| `ARR` | Seu pedido chegou ao aeroporto de destino (Guarulhos - SP) | Am Flughafen Guarulhos in São Paulo gelandet. |
| `AAZ` | Pedido recebido pela GRU Airport | Vom Frachtterminal des Flughafens GRU vor der formellen Zollanmeldung übernommen. |
| `BRP` | Processo de desembaraço iniciado | Die brasilianische Zollabfertigung hat begonnen. |
| `HOC` | Encomenda sendo inspecionada pela Alfândega | In Prüfung durch die Receita Federal (brasilianischer Zoll). |
| `ANV` | Em inspeção pela ANVISA | In behördlicher Prüfung durch die brasilianische Gesundheitsbehörde ANVISA während der Abfertigung. |
| `CLA` | Liberado (Fortschrittsleiste) | Zoll und erforderliche Aufsichtsbehörden haben die Sendung für die Inlandszustellung freigegeben. |
| `DLV` | Entregue (Fortschrittsleiste) | An der Empfängeradresse in Brasilien zugestellt. |

## Zoll, CPF-Prüfung und ANVISA-Freigabe

Jeder private Expressimport nach Brasilien erfordert für die Abfertigung bei der Receita Federal die CPF (*Cadastro de Pessoas Físicas*) des Empfängers. Wie Memphis Courier auf seiner [Seite für Zusatzleistungen](https://memphiscourier.com.br/servicos-adicionais/) erläutert, bieten viele ausländische Onlineshops beim Checkout kein CPF-Feld; in diesem Fall meldet sich der Kundenservice von Memphis Courier beim Empfänger zur Validierung der CPF, damit die Verzollung nicht blockiert wird.

Bei medizinischem Cannabis und anderen Arzneimitteln, die Memphis Courier aus den USA, Europa, Kolumbien oder Uruguay einführt, kann das Paket in Guarulhos sowohl Prüfungen durch die Receita Federal (`HOC`) als auch durch die ANVISA (`ANV`) durchlaufen, bevor `CLA` (freigegeben) erscheint. Steht deine Sendung mehrere Werktage ohne Änderung auf `BRP`, `HOC` oder `ANV`, prüfe E-Mail und WhatsApp: Memphis Courier informiert Kunden per E-Mail, WhatsApp, Chat oder Telefon und hat möglicherweise eine CPF-Bestätigung oder ein Dokument angefordert. Mehr zu internationalen Einfuhrabläufen findest du im [Zoll-Ratgeber](guide:customs) und unter [wenn die Sendungsverfolgung stehen bleibt](guide:tracking-not-updating).

Sobald der Zoll das Paket freigibt (`CLA`), deckt die Inlandszustellung alle brasilianischen Bundesstaaten ab. Liefert ein anderer Paketdienst eine andere Bestellung nach Brasilien, vergleiche die Sendungsverfolgung mit [Anjun Express](carrier:anjun), [Jadlog](carrier:jadlog) oder [J&T Express Brazil](carrier:j-and-t-br).

## Memphis Courier mit Peek verfolgen

Füge einen `M...MIA`-Code in Peek ein, und Peek ordnet ihn automatisch Memphis Courier zu, sobald der Anbieter antwortet. Musst du den Paketdienst manuell wählen oder wechseln, tippe beim Hinzufügen auf **Ändern** oder in der Sendungsansicht auf **Paketdienst ändern**.

Peek fragt den öffentlichen Tracking-Dienst von Memphis Courier direkt über den Open-Source-[Memphis-Courier-Adapter von universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/memphis-courier) ab. Ausgelesen werden Scancode, portugiesischer Text, Ortsangabe und das Gesamtgewicht in Kilogramm, sofern vorhanden. Memphis Courier liefert jede Scan-Zeit als Ortsuhrzeit und gibt nur dort einen UTC-Versatz an, wo die Zeit damit endet. Peek übernimmt diesen Versatz; andernfalls ordnet Peek den Scan der Zeitzone seines Orts zu (New Yorker Zeit für `Estados Unidos`, Zeit von São Paulo für `SP` oder `Brasil`), einen Scan ohne Ort der Zeitzone des Nachbarscans oder sonst der Zeit von São Paulo, und zeigt alle Scans auf der Uhr deines Geräts an. Felder des Kundenportals wie interne Hinweise, Paketanzahl, Postleitzahlen, Zieladresse und angehängte Zolldokumente bleiben auf der eigenen Website von Memphis Courier.

Peek fasst die 12 Scancodes von Memphis Courier in klare Phasen zusammen: **Etikett erstellt** für `FLT`, **Unterwegs** von `CHK` bis `AAZ` und erneut nach Zollfreigabe bei `CLA` (das Code-Schema von Memphis Courier geht direkt von der Auftragserstellung in den Verteilzentrums-Transit über, ohne eigenen Code für **Beim Paketdienst eingegangen**), **Beim Zoll** für `BRP`, `HOC` und `ANV`, sowie **Zugestellt**, sobald `DLV` erscheint.

Solange du das Paket in Peek geöffnet hast oder am Zustelltag Benachrichtigungen aktiviert sind, fragt Peek bis zu alle 10 Minuten nach, solange der Tracker von Memphis Courier antwortet. Andernfalls prüft Peek stündlich. Da der Tracker von Memphis Courier keine eigene Zustelltour-Phase meldet, wechselt Peek nie auf seine 2-Minuten-Abfragen am Liefertag. Nach 12 Stunden mit lediglich „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ oder nach zwei Tagen ohne Neuigkeiten fragt Peek unabhängig von deiner Nutzung stündlich nach, und nach sieben Tagen auf „Etikett erstellt“ alle 6 Stunden. Antwortet der Tracker von Memphis Courier nicht und Peek verfolgt das Paket allein über Tracking-Dienste, prüft Peek stündlich, nach einem ruhigen Tag alle 3 Stunden und am Zustelltag bis zu alle 15 Minuten, solange du die Sendung geöffnet hast oder Benachrichtigungen aktiviert sind.

Ist der neueste Scan vier Tage alt und das Paket steht noch auf „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“, markiert Peek es mit dem Tag dieses Scans, etwa „Keine Aktualisierung seit Mo., 12. Okt.“. Auf der Paketseite steht dann „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei Memphis Courier nach.“, samt Link „Hol dir Hilfe bei Memphis Courier“ zur Seite Fale Conosco von Memphis Courier. Nach drei Wochen ohne neuen Scan heißt es „Seit über drei Wochen nichts Neues. Ist das Paket noch nicht angekommen, frag beim Verkäufer nach. Peek fragt weiter bei Memphis Courier nach.“

Wähle **Jeden Scan**, **Wichtige Schritte** oder **Nur die Zustellung** in den Peek-Benachrichtigungen, um festzulegen, wie häufig du informiert werden möchtest.

## Kontakt zu Memphis Courier

Memphis Courier nennt offizielle Support-Kanäle auf seiner Website und erklärt im offiziellen Hinweis ausdrücklich, dass Beschwerdeportale Dritter wie Reclame Aqui nicht als offizieller Support- oder Protokollkanal gelten.

- **Offizielle Kontaktseite:** [Fale Conosco](https://memphiscourier.com.br/fale-conosco/) (Kontaktformular)
- **Telefon / Kundenservice:** +55 11 94264-7550 (sowohl für das GRU Courier Terminal als auch das Büro in São Paulo angegeben)
- **E-Mail:** `atendimento@memphiscourier.com.br`, `atendimento1@memphiscourier.com.br` und `info@memphiscourier.com.br`
- **Guarulhos Courier Terminal:** Aeroporto Internacional de Guarulhos — Terminal Courier, CEP 07190-100, São Paulo, Brasilien
- **Büro São Paulo:** Av. Marques de São Vicente, 230, cj 1216 — Barra Funda, CEP 01139-000, São Paulo, Brasilien ([Quem Somos](https://memphiscourier.com.br/quem-somos/))

Halte deine `M...MIA`-Sendungsnummer und die CPF des Empfängers bereit, wenn du den Support kontaktierst, damit dein Zollvorgang sofort gefunden werden kann.

## Häufige Fragen

### Was bedeutet `ANV` („Em inspeção pela ANVISA“) bei Memphis Courier?

`ANV` bedeutet, dass die brasilianische Gesundheitsbehörde (ANVISA) die Sendung im Rahmen der Einfuhrabfertigung prüft. Memphis Courier fertigt medizinische Cannabis-Importe (RDC 660 / RDC 327) über die ANVISA ab; das Paket bleibt bis `CLA` (freigegeben) beim Zoll.

### Warum hat mich Memphis Courier wegen meiner CPF kontaktiert?

Die Receita Federal verlangt bei jedem persönlichen Expressimport nach Brasilien die CPF des Empfängers. Erfasst ein ausländischer Shop beim Bezahlen keine CPF, meldet sich der Kundenservice von Memphis Courier bei dir zur Bestätigung, bevor die Verzollung weiterläuft.

### Warum gibt es zwischen `DEP` und `ARR` keine Scans?

`DEP` meldet den Abflug vom Ausgangsort Richtung Flughafen Guarulhos und `ARR` die dortige Ankunft. Die Codeliste von Memphis Courier sieht keinen Scan für den Flug selbst vor, sodass der nächste Eintrag erst `ARR` oder `AAZ` am Frachtterminal ist.

### Beantwortet Memphis Courier Anfragen auf Reclame Aqui?

Nein. Der offizielle Hinweis auf der Website von Memphis Courier stellt klar, dass Beschwerden, Fragen und Klärungen ausschließlich über die auf [memphiscourier.com.br](https://memphiscourier.com.br/) genannten offiziellen Kanäle (Telefon, E-Mail und Kontaktformular) bearbeitet werden, um Nachvollziehbarkeit und Datenschutz zu sichern.

:::sources
- [Memphis Courier](https://memphiscourier.com.br/)
- [Memphis Courier — Quem Somos](https://memphiscourier.com.br/quem-somos/)
- [Memphis Courier — Serviços Adicionais](https://memphiscourier.com.br/servicos-adicionais/)
- [Memphis Courier — Fale Conosco](https://memphiscourier.com.br/fale-conosco/)
- [universal-parcel-scraper Memphis-Courier-Adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/memphis-courier)
:::
