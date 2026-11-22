---
title: Aras Kargo Sendungsverfolgung: Nummern und Status
description: Aras Kargo Paket verfolgen: Aufbau der 13-stelligen Nummer, türkische Status wie TESLİMAT ŞUBESİNDE auf Deutsch erklärt und Tracking in Peek.
slug: aras-kargo-sendungsverfolgung
published: 2026-11-22
updated: 2026-11-22
---

Aras Kargo (auch Aras Cargo geschrieben) ist ein Paketdienst in der Türkei. Das Tracking von Aras, wie Peek es ausliest, schreibt jeden Schritt auf Türkisch. Den Sendungscode bekommst du vom Shop oder Absender: Füge ihn oben ein. Die eigene Sendungsverfolgung von Aras Kargo liegt unter kargotakip.araskargo.com.tr. Die Tabellen unten erklären die türkischen Statusmeldungen auf Deutsch.

## Aras Kargo Sendungsnummer: wie sie aussieht

Peeks Code kennt Sendungsnummern von Aras Kargo als 13 Ziffern. Nummern, die Peek automatisch Aras zuordnet, beginnen mit 60 oder 65. Erfundene Beispiele:

| Nummer (erfunden) | Bedeutung |
| --- | --- |
| `6048291736542` | 13 Ziffern mit 60 am Anfang: das Format, das Peek mit Aras Kargo verknüpft. Auch andere Paketdienste wie [Delhivery](carrier:delhivery), Econt, Ozon Logistics, Paquetexpress, Correos Chile, Yunda und Korea Post nutzen 13-stellige Nummern. |
| `6548291736542` | 13 Ziffern mit 65 am Anfang: dasselbe. |
| `1482917365482` | 13 Ziffern mit anderem Anfang: Peek ordnet sie Aras nicht automatisch zu, doch das Aras-Tracking nimmt 13 Ziffern an. Wenn der Shop Aras Kargo nennt, wähle Aras Kargo selbst aus (siehe unten). |
| `604829173654` | Nur 12 Ziffern. Wählst du dafür Aras Kargo, meldet Peek, Aras Kargo „vergibt keine Nummern wie diese“. Prüfe, ob eine Ziffer fehlt. |

Leerzeichen stören beim Einfügen in Peek nicht. Auch ein Link zum Aras-Tracking mit der Nummer (kargotakip.araskargo.com.tr mit `code=` und der Nummer) funktioniert.

**Wo du sie findest.** In der Versandbestätigung des Shops oder auf deiner Bestellseite. Falls du sie nicht findest, schau unter [Sendungsnummer finden](guide:find-tracking-number) und [Sendungsnummer-Formate](guide:tracking-number-formats).

## Aras Kargo Tracking-Status auf Deutsch

Das Tracking von Aras zeigt den aktuellen Status (`Son Durum`), einen geplanten Zustelltag und eine Liste der Fahrzeugbewegungen (`Araç Hareketleri`). Jede Bewegung trägt Datum und Uhrzeit (`İşlem Tarihi`), Provinz (`İl`), Einheit (`Birim`, Filiale oder Umschlagzentrum) und Beschreibung (`İşlem Açıklaması`). Falls vorgekommen, listet die Seite auch Tage ohne erfolgreiche Zustellung und Weiterleitungen an eine andere Filiale auf.

**Aktueller Status.** Oben steht einer dieser Werte:

| Aras meldet | Was das für dich bedeutet | Peek zeigt |
| --- | --- | --- |
| `ÇIKIŞ ŞUBESİNDE` | In der Abgangsfiliale, die das Paket angenommen hat. | „Beim Paketdienst eingegangen“ |
| `YOLDA` | Unterwegs zwischen den Umschlagzentren von Aras. | „Unterwegs“ |
| `TESLİMAT ŞUBESİNDE` | In der Zustellfiliale, die das Paket zu dir bringt. Bleibt auch nach einem fehlgeschlagenen Zustellversuch gleich. | „Unterwegs“ |
| `TESLİM EDİLDİ` | Zugestellt. Aras nennt Tag und Uhrzeit (fehlt die Uhrzeit, bleibt der Status „Zugestellt“ ohne eigenen Zustell-Scan). | „Zugestellt“ |

**Fahrzeugbewegungen.** Jeder Scan beim Verladen, Entladen oder Einsacken:

| Aras meldet | Was das für dich bedeutet | Peek zeigt |
| --- | --- | --- |
| `ŞUBEDE ARAÇ YÜKLEME` | In einer Filiale in ein Fahrzeug geladen. | „Unterwegs“ |
| `ŞUBEDE ARAÇ İNDİRME` | In einer Filiale aus einem Fahrzeug entladen. | „Unterwegs“ |
| `AKTARMADA HAT ARACI YÜKLEME` | Im Umschlagzentrum in ein Fernverkehrsfahrzeug geladen. | „Unterwegs“ |
| `AKTARMADA HAT ARACI İNDİRME` | Im Umschlagzentrum aus einem Fernverkehrsfahrzeug entladen. | „Unterwegs“ |
| `AKTARMADA SUBE ARACI YUKLEME` | Im Umschlagzentrum in ein Filialfahrzeug geladen. | „Unterwegs“ |
| `AKTARMADA SUBE ARACI INDIRME` | Im Umschlagzentrum aus einem Filialfahrzeug entladen. | „Unterwegs“ |
| `AKTARMADA TORBA DOLDURMA` | Im Umschlagzentrum mit anderen Paketen in einen Sack gelegt. | „Unterwegs“ |

Das Tracking schreibt die beiden Zeilen mit `SUBE` ohne türkische Sonderzeichen, genau wie oben.

**Zustellung nicht erfolgt** (`Teslim Edilememe Nedeni`, Grund). Eine Zeile pro betroffenem Tag:

| Aras meldet | Was das für dich bedeutet | Peek zeigt |
| --- | --- | --- |
| `TESLİMAT GÜN İÇERİSİNDE YAPILAMADI` | Das Paket konnte an diesem Tag nicht zugestellt werden. | „Zustellversuch“ |
| `TATİL DOLAYISIYLA TESLİMAT YAPILAMADI` | Keine Zustellung wegen eines Feiertags. | „Zustellversuch“ |
| `UĞRAMA NOTU BIRAKILDI (1-2)` | Der Zusteller war da und hat eine Benachrichtigung hinterlassen. | „Zustellversuch“ |
| `ALICI ADRESİ DAĞITIM ALANI DIŞINDA` | Deine Adresse liegt außerhalb des Zustellgebiets der Filiale. | „Zustellversuch“ |

**Weiterleitung.** `YÖNLENDİRİLDİ` bedeutet, das Paket wurde an eine andere Filiale weitergeleitet. Aras nennt den Grund, und Peek setzt ihn in Klammern dahinter. Peek zeigt „Unterwegs“.

Diese beiden Zeilenarten haben nur ein Datum, keine Uhrzeit. Das Aras-Tracking kann neben einem gescheiterten Versuch auch eine freie Notiz des Zustellers anzeigen, die Peek weglässt. Mehr zu den Stufen unter [Sendungsstatus verstehen](guide:tracking-statuses).

## Aras Kargo Zustellung in der Türkei

Ein Paket läuft von der annehmenden Aras-Filiale (`ÇIKIŞ ŞUBESİNDE`) über Umschlagzentren (Zeilen mit `AKTARMADA`) bis zur Zustellfiliale (`TESLİMAT ŞUBESİNDE`), die es an deine Adresse ausliefert.

**Geplanter Zustelltag.** Das Aras-Tracking gibt ein Datum an. Peek zeigt es als voraussichtliches Lieferdatum, solange das Paket unterwegs ist und der Tag nicht vor dem letzten Scan liegt. Nach einem fehlgeschlagenen Zustellversuch oder nach Ablauf des Tages blendet Peek es aus.

**Außerhalb des Zustellgebiets.** `ALICI ADRESİ DAĞITIM ALANI DIŞINDA` heißt, dass die Filiale deine Adresse nicht anfährt: Frage beim Shop nach, wie es weitergeht.

**Was diese Seite nicht sagen kann.** Peek konnte die Hilfeseiten von Aras Kargo nicht auslesen; deshalb stehen hier keine Laufzeiten, Zustellversuche, Lagerfristen oder Abholregeln. Das Aras-Tracking liefert für Peek keinen Abholpunkt, kein Gewicht und keinen Servicenamen. Bei verpasster Lieferung helfen [Zustellung verpasst](guide:missed-delivery) und [Nicht abgeholtes Paket](guide:uncollected-parcel).

## Aras Kargo Sendung hängt, verloren oder zurückgeschickt

**Kein Ergebnis.** Kennt Aras die Nummer nicht, antwortet das Tracking `Girdiğiniz bilgiler için bir sonuç bulunamamıştır.` (kein Ergebnis für deine Eingabe). Vielleicht hat der Shop das Paket noch nicht übergeben. Peek fragt weiter nach: Ein unter Aras Kargo geführtes Paket zeigt „Warten auf Aras Kargo“. Prüfe die 13 Ziffern mit dem Shop; kommt nach einigen Tagen nichts, frage nach, ob Aras das Paket tatsächlich erhalten hat.

**Hängt bei `TESLİMAT ŞUBESİNDE`.** Das Paket liegt in der Zustellfiliale, und dieser Status bleibt auch nach einem fehlgeschlagenen Zustellversuch unverändert. Steht für einen Tag ein Grund (Tabelle oben), klappte die Zustellung dort nicht und der Grund erklärt warum. Andernfalls wartet es auf die Auslieferung. Da Aras keinen eigenen Schritt für die Zustelltour meldet, zeigt Peek „Unterwegs“ bis zur Zustellung oder bis zu einem fehlgeschlagenen Versuch.

**Tagelang keine Aktualisierung.** Nach vier Tagen ohne neuen Scan bei einem Paket auf dem Weg zeigt Peek in deiner Liste den Tag des letzten Scans („Keine Aktualisierung seit …“) und auf der Paketseite „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei Aras Kargo nach.“, erst dann samt Link „Hol dir Hilfe bei Aras Kargo“ zur Kontaktseite. Übers Wochenende pausieren Scans häufig. Mehr unter [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating).

**Als zugestellt markiert, aber nichts angekommen.** Frage Nachbarn und Mitbewohner. Peek zeigt den maskierten Namen des Übernehmers (`Teslim Alan`), wie die Übersicht von Aras ihn anzeigt; schau bei Bedarf auch direkt im Aras-Tracking nach. Wende dich danach an Shop und Aras. Mehr unter [Zugestellt, aber nicht erhalten](guide:delivered-not-received).

**Verloren oder zurückgeschickt.** Ein eigener Retouren-Status von Aras ist in Peek noch nicht hinterlegt. Da der Shop den Versand beauftragt hat, wende dich zuerst an den Shop für Erstattung oder Ersatzlieferung. Siehe [Verlorenes Paket](guide:lost-parcel) und [An Absender zurückgesendet](guide:returned-to-sender).

## Aras Kargo Kundenservice und Kontakt

Peek konnte die Website von Aras Kargo nicht auslesen; deshalb nennt diese Seite keine Telefonnummer und keine E-Mail-Adresse, da Peek nur Kontakte übernimmt, die direkt auf den offiziellen Seiten des Paketdienstes gelesen wurden.

Bei Onlinebestellungen frage zuerst den Shop als Auftraggeber. In Peek nennt der Ort jeder Bewegung die Filiale oder das Umschlagzentrum, und der Ort der Zustellung die zustellende Filiale — gib diesen Namen bei deiner Anfrage an.

## Aras Kargo Paket mit Peek verfolgen

[Peek](/) kann eine Nummer von Aras Kargo nicht allein an der Form sicher erkennen, weil auch andere Dienste 13 Ziffern verwenden. Füge sie trotzdem ein: Peek unterstützt über 4500+ Paketdienste.

- **13 Ziffern ab 60 oder 65.** Peek fragt Aras Kargo zusammen mit weiteren Paketdiensten an, die zu diesem 13-stelligen Muster passen (darunter [Delhivery](carrier:delhivery), Econt, Ozon Logistics und Paquetexpress); bei einer Abfrage aus der Türkei fragt Peek zuerst bei Aras Kargo nach. Währenddessen erscheint „Tracking-Dienste werden geprüft…“. Bestätigt nur Aras Kargo das Paket, meldet Peek „Aras Kargo hat dieses Paket.“ und legt es dort ab; kennen es mehrere Dienste, fragt Peek nach dem richtigen.
- **Andere 13 Ziffern oder noch nicht gefunden.** Wechsle den Paketdienst und wähle Aras Kargo (tippe Aras), damit Peek direkt das Aras-Tracking abfragt. Auch bei einem bereits gespeicherten Paket kannst du den Paketdienst wechseln.
- **Link zum Aras-Tracking.** Peek liest die Nummer aus dem Link und erkennt Aras Kargo sofort („Erkannter Paketdienst“).

Ohne Paketdienst hinzugefügt, zeigt eine Sendung „Noch kein Sendungsverlauf“, während Peek weiter Paketdienste und Tracking-Dienste prüft. Nennt ein Tracking-Dienst Aras Kargo als einzigen Paketdienst mit datierten Schritten, ordnet Peek sie Aras zu.

**Was Peek anzeigt.** Den aktuellen Status, jede Bewegung mit Zeit und Ort (Filiale oder Umschlagzentrum samt Provinz), Tage ohne Zustellung, Weiterleitungen, den geplanten Zustelltag, Zustellzeit und Zustellfiliale sowie die maskierten Namen von Empfänger und Übernehmer (`Teslim Alan`) aus der Übersicht. Weggelassen werden die verborgene ungekürzte Schreibweise, der Absendername, der Zustellcode, die Ausweisprüfung, Zahlungs- und Rechnungsdaten, Volumen und Paketanzahl, die Fortschrittsleiste, freie Zustellnotizen und der SMS-Reiter. Peek liest Aras-Zeiten als türkische Zeit und rechnet sie in deine Zeitzone um (der Verlauf endet mit „Uhrzeiten in deiner Zeitzone:“ samt ihrem Namen). Über „Website von Aras Kargo öffnen“ öffnest du die Sendung direkt im Aras-Tracking.

Ist das Tracking von Aras nicht erreichbar, fragt Peek stattdessen Tracking-Dienste ab und zeigt deren Verlauf in deren Wortlaut.

Solange das Paket auf dem Bildschirm geöffnet ist — oder am Zustelltag mit aktivierten Benachrichtigungen — prüft Peek Aras Kargo direkt bis zu alle 10 Minuten (ohne 2-Minuten-Takt, da Aras keinen Schritt für die Zustelltour sendet). Sonst sowie nach zwei Tagen ohne neuen Schritt (12 Stunden bei „Etikett erstellt“ oder „Beim Paketdienst eingegangen“) prüft Peek stündlich. Über Tracking-Dienste allein prüft es stündlich, nach einem Tag ohne Scan alle 3 Stunden und am Zustelltag bis zu alle 15 Minuten, sofern das Paket geöffnet ist oder Benachrichtigungen aktiv sind. Wähle „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“. Peek kann Aras Kargo oder den Shop nicht für dich kontaktieren.

## Fragen zur Aras Kargo Sendungsverfolgung

### Was bedeutet TESLİMAT ŞUBESİNDE bei Aras Kargo?

Es bedeutet „in der Zustellfiliale“: Die für deine Adresse zuständige Aras-Filiale hat das Paket. Der Status bleibt auch nach einem fehlgeschlagenen Versuch gleich — achte auf eine Zeile mit Grund, die Peek als „Zustellversuch“ zeigt.

### Was bedeutet YOLDA bei Aras Kargo?

Es bedeutet „unterwegs“: Das Paket befindet sich zwischen den Umschlagzentren von Aras auf dem Weg zur Zustellfiliale. Peek zeigt „Unterwegs“.

### Wie sieht eine Aras Kargo Sendungsnummer aus?

13 Ziffern in Peeks Code. Nummern ab 60 oder 65 wie `6048291736542` ordnet Peek Aras Kargo zu. Bei anderen 13-stelligen Nummern wähle Aras Kargo selbst aus.

### Warum findet Aras Kargo meine Sendungsnummer nicht?

`Girdiğiniz bilgiler için bir sonuç bulunamamıştır.` bedeutet, dass Aras noch keinen Treffer hat. Oft hat der Shop das Paket noch nicht übergeben. Prüfe alle 13 Ziffern, warte einen Tag und frage beim Shop nach.

### Wie erreiche ich Aras Kargo?

Wende dich zuerst an den Shop, der den Versand gebucht hat. Da Peek die Website von Aras Kargo nicht auslesen konnte, nennt diese Seite weder Telefonnummer noch E-Mail-Adresse.

:::sources
- [Universal Parcel Scraper: Aras Kargo](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/aras-kargo) – Namen, 13-stellige Nummer und Präfixe 60 und 65, Tracking-Seite und Link, Bereiche und türkische Begriffe samt Stufen, Nicht-gefunden-Meldung, geplanter Zustelltag, türkische Zeit, von Peek gelesene und verworfene Felder
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – andere Paketdienste mit demselben Format, automatische Erkennung und Fallback über Tracking-Dienste
:::
