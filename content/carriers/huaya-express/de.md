---
title: Huaya Express Sendungsverfolgung: HYYT-Nummern & Status
description: Huaya Express (华亚运通) versendet Online-Bestellungen aus Guangzhou. HYYT…GZ-Nummer prüfen, englische und chinesische Scans lesen und Endzusteller finden.
slug: huaya-express-sendungsverfolgung
published: 2026-11-21
updated: 2026-11-21
---

Huaya Express (`华亚运通`) ist die Marke im Logo von `华亚运通国际货运代理有限公司` (*Huaya Yuntong International Freight Forwarding*), einem Weiterleiter (*Freight Forwarder*) in Guangzhou in Festlandchina, der Online-Bestellungen ins Ausland verschickt. Ein Frachtbrief von Huaya beginnt mit `HYYT` und endet auf `GZ`. Füge ihn oben ein oder folge dem Link `物流跟踪` (Logistikverfolgung) im oberen Menü von `hyytgz.com` zu Huayas eigenem Tracker. Huayas Verlauf endet, sobald das Paket an einen Zustelldienst in deinem Land übergeben wird oder nach einigen Scans dieses Dienstes (in den USA teils bis zur Zustellung), ohne den Zusteller zu nennen – außer auf einer Linie über [DHL](carrier:dhl) nach Deutschland, auf der Huaya die DHL-Scans mitkopiert, bei einem schwedischen Hinweis auf Early Bird sowie auf einer Linie nach Mexiko, auf der Huaya die Scans von J&T Express mitkopiert.

## Sendungsnummer von Huaya Express: Aufbau und Beispiele

Huaya veröffentlicht keine Formatbeschreibung. Das Muster, das Peek kennt, besteht aus `HYYT`, zwölf Ziffern und `GZ` (erfundene Beispiele):

| Nummer (erfunden) | Bedeutung |
| --- | --- |
| `HYYT482910573617GZ` | `HYYT`, zwölf Ziffern und `GZ`: der Aufbau eines Frachtbriefs von Huaya Express. |
| `HYYT48291057317GZ` | Nur elf Ziffern: Beim Kopieren fehlt eine Stelle. Kopiere die Nummer erneut aus der Versandnachricht des Händlers. |
| `RR482910576CN` | Postnummer mit `CN` am Ende: wird über [China Post](carrier:china-post) verfolgt, nicht über Huaya. |

Leerzeichen sowie Groß- und Kleinschreibung spielen in Peek keine Rolle.

**Woher die Nummer kommt.** Huaya arbeitet für Online-Händler: Die Startseite nennt Shopify-Shops, Vova, DHgate (`敦煌`) und Facebook-Verkäufer. Die Nummer erhältst du daher vom Shop, bei dem du bestellt hast; fehlt sie noch, hilft [Sendungsnummer finden](guide:find-tracking-number). Huayas Tracker findet Sendungen auch über die Bestellreferenz des Händlers (`参考号`), exakt mit allen Bindestrichen eingegeben.

**Tracking-Link mit numerischer Adresse.** Huayas eigener Tracker liegt nicht direkt unter `hyytgz.com`, sondern auf einer numerischen Adresse über unverschlüsseltes HTTP (`trackIndex.htm` mit `documentCode=` und deiner Nummer) – einen solchen Link kannst du direkt in Peek einfügen. Der gleichnamige Link `物流跟踪` in der Fußzeile von `hyytgz.com` öffnet hingegen den Tracking-Dienst 17TRACK.

## Tracking-Status von Huaya Express erklärt

Huayas Tracker gibt es auf Chinesisch und Englisch. Die in Peek hinterlegten Zeilen sind überwiegend englisch; auf Chinesisch erscheinen der Wareneingang im Lager sowie einzelne Flughafen-, Flug-, Zoll- und Übergabemeldungen, dazu eine schwedische Zeile für Schweden. In der üblichen Reihenfolge:

| Huaya meldet | Was das für dein Paket bedeutet | Peek zeigt |
| --- | --- | --- |
| `Shipping Label Created` | Das Etikett des späteren Zustellers im Zielland existiert bereits: reine Auftragsdaten, Huaya hat das Paket noch nicht. | „Etikett erstellt“ |
| `Shipment not yet received or processed` | Ebenfalls vor dem Eintreffen bei Huayas Annahmestelle übermittelte Zeile des Endzustellers: nur Auftragsdaten. | „Etikett erstellt“ |
| `Package received & scanned at warehouse`, `到达收货点` | Huaya hat das Paket im Lager in Guangzhou (Festlandchina) übernommen: erster Scan in Huayas Händen (`到达收货点` heißt am Annahmepunkt eingetroffen). | „Beim Paketdienst eingegangen“ |
| `Package departed from the warehouse` | Das Paket hat das Lager in Guangzhou verlassen. | „Unterwegs“ |
| `货物发往机场途中`, `货物到达机场` | Unterwegs zum Flughafen bzw. am Flughafen in Festlandchina eingetroffen, vor der Ausfuhrverzollung. | „Unterwegs“ |
| `Customs export clearance completed`, `国内清关完成` | Ausfuhrzoll in Festlandchina erledigt; die Einfuhrprüfung deines Landes folgt erst nach der Landung. | „Unterwegs“ |
| `安排航班中`, `Package handed over to airline` | Flug wird gebucht bzw. Sendung an die Fluggesellschaft übergeben. | „Unterwegs“ |
| `Shipment Announced` | Huaya hat das Paket beim Zusteller im Zielland angekündigt; übernommen hat er es noch nicht. | „Unterwegs“ |
| `Package in transit to destination country`, `航班起飞，预计航班落地时间(…)` | Auf dem Weg ins Zielland (die chinesische Meldung nennt in Klammern den geplanten Landetag). | „Unterwegs“ |
| `Package arrived at destination country` | Im Zielland angekommen. | „Unterwegs“ |
| `航班落地，清关中` | Flugzeug gelandet, Einfuhrverzollung im Zielland läuft. | „Beim Zoll“ |
| `Customs import clearance completed, awaiting pickup by local carrier`, `清关完成` | Der Einfuhrzoll deines Landes hat das Paket freigegeben; der lokale Zusteller muss es noch abholen. | „Unterwegs“ |
| `It has been handed over to the service provider.`, `已交仓`, `Preparing for delivery` | An den lokalen Zustelldienst bzw. dessen Lager übergeben oder vor der Zustelltour vorbereitet. Huaya nennt weder Dienst noch Folgesendungsnummer; danach folgen nur auf US-Routen US-Scans (`Arrived at Origin Hub` nahe Chicago O'Hare, `Departed Origin Hub`, `Arrived at Destination Hub`, `Departed Destination Hub`, `Arrived At Warehouse`, dann `Out for Delivery` als „In Zustellung“ und `Delivered` als „Zugestellt“) oder nach Deutschland kopierte [DHL](carrier:dhl)-Scans oder auf einer Linie nach Mexiko kopierte Scans von J&T Express. Peek kennt außerdem weitere englische Zustellzeilen, deren Land keine Quelle nennt (`Arrived at the regional hub`, `Departed from the regional hub`, `Arrived at the sorting center`, `Departed from the sorting center`, `Arrived in your region`, `Out for delivery with the courier` als „In Zustellung“). | „Unterwegs“ |
| `Din leverans ?r bokad med Early Bird.` | Schwedisch für „Deine Lieferung ist bei Early Bird gebucht“ (`ä` in `är` wird als `?` ausgegeben): In Schweden stellt Early Bird zu. | „Unterwegs“ |

Huayas Zeitstempel enthalten keine Zeitzone: Sie gelten in Pekinger Zeit (`UTC+8`), ausgenommen die kopierten DHL-Scans und US-Scans an einem US-Ort, die die Ortszeit des jeweiligen Scan-Orts behalten, sowie die nach Mexiko kopierten Scans von J&T Express in der Zeit von Mexiko-Stadt. Kennt der Tracker eine Nummer nicht, wiederholt die Spalte `参考号` (Referenz) die Nummer, während der Rest der Zeile leer bleibt.

## Wer stellt Pakete von Huaya Express zu?

Abgesehen von den kopierten [DHL](carrier:dhl)-Scans auf einer Route nach Deutschland, Early Bird in der schwedischen Meldung und den kopierten Scans von J&T Express auf einer Route nach Mexiko verrät Huayas Sendungsverlauf weder das Zustellunternehmen noch eine zweite Sendungsnummer. Auch Huayas eigene Produktseiten widersprechen sich: Die Seite für Großbritannien (`ywfw.php?id=21`) endet mit US-Text zu USPS, und auf der Australien-Seite (`ywfw.php?id=27`) fliegt das Paket für USPS in die USA. Welcher Dienst dein Paket tatsächlich bringt, weiß der Verkäufer – frag ihn nach dem Zusteller und dessen Sendungsnummer. Infos zu Einfuhrabgaben findest du unter [Paket hängt beim Zoll](guide:customs).

## Huaya-Express-Paket hängt fest oder kommt nicht an

**Letzter Eintrag `It has been handed over to the service provider.`.** Oft kommt in Huayas Tracker danach nichts mehr, weil das Paket nun unter der eigenen Nummer des lokalen Paketdienstes weiterreist. Erfrage beim Händler den Namen des Dienstes und diese Nummer ([Pakete aus China verfolgen](guide:tracking-from-china)).

**Pause nach der Einfuhrverzollung.** Der Zoll ist abgeschlossen und das Paket wartet auf die Abholung durch den Zusteller vor Ort. Tut sich mehrere Tage lang nichts, informiere den Verkäufer ([Warum sich das Tracking nicht aktualisiert](guide:tracking-not-updating)).

**Kein Ergebnis.** Da Huaya bei unbekannten Nummern eine leere Zeile mit der Referenz zurückgibt, wertet Peek dies als angekündigte Sendung ohne ersten Scan und zeigt „Warten auf Huaya Express“ ohne Fehlermeldung zum Nummernformat (bei einer Suche ohne Konto prüft Peek in den ersten 6 Stunden weiter, danach beim Öffnen des Links und bis zu 30 Tage lang bei eingeschalteten Mitteilungen). Vergleiche die Schreibweise daher selbst mit der Nachricht des Händlers (`HYYT`, zwölf Ziffern, `GZ`) und frag beim Shop nach, ob er wirklich mit Huaya verschickt hat.

**Verspätet, verloren oder zurückgeschickt.** Huayas Haftungsregeln auf den Linien-Seiten richten sich an einliefernde Shops, nicht an Empfänger. Bitte den Händler um Ersatz oder Erstattung und eröffne bei Funkstille einen Käuferschutzfall auf dem Marktplatz ([Paket verloren](guide:lost-parcel), [Zurück an den Absender](guide:returned-to-sender), [Zugestellt, aber nicht erhalten](guide:delivered-not-received)).

## Kontakt und Kundenservice von Huaya Express

Auf `hyytgz.com` genannte Unternehmensdaten, ausschließlich auf Chinesisch:

- **Telefon**: `18039073131`, bezeichnet als Firmen-Servicehotline (`公司服务热线`). Es handelt sich um eine Mobilnummer in Festlandchina (aus dem Ausland mit `+86`); Zeiten oder Sprachen nennt die Seite nicht.
- **E-Mail**: `2065332407@qq.com`, eine QQ-Mail-Adresse mit dem Label Firmen-E-Mail (`公司邮箱`).
- **Formular**: Die Seite `投诉建议` (`message.php`, Beschwerden und Vorschläge) fragt Name, Mobilnummer, E-Mail-Adresse und Nachricht ab.
- **Standort**: Bezirk Baiyun in Guangzhou (`广州市白云区`), Festlandchina.

Die Unterseiten `关于我们` (`about.php`) und `服务网点` (`outlets.php`) enthalten außer der Überschrift keinen Inhalt, und für Paketempfänger gibt es keine eigene Hilfeseite.

**Vorsicht vor Phishing-Nachrichten.** Huayas Website lautet `hyytgz.com`. Prüfe vor dem Öffnen von Links aus SMS oder E-Mails [Gefälschte Paket-SMS erkennen](guide:delivery-scam-texts). Wende dich zu deiner Bestellung zuerst an den Shop.

## Huaya-Express-Pakete mit Peek verfolgen

Fügst du eine `HYYT…GZ`-Nummer oder einen Tracker-Link mit `documentCode=` ein, ordnet [Peek](/) sie sofort Huaya Express zu („Erkannter Paketdienst“), da kein anderer Dienst im Katalog dieses Muster nutzt. Für eine Bestellreferenz des Händlers (unter mehr als 3.500 Paketdiensten) gibst du die Referenz ein und wählst Huaya Express über „Ändern“ (`华亚运通` oder `GZHY` finden ihn ebenfalls; bei gespeicherten Paketen über „Paketdienst ändern“): Peek zeigt dann auch Huayas eigene Sendungsnummer, entfernt jedoch Bindestriche – Referenzen mit Bindestrich prüfst du direkt in Huayas Tracker.

**Scans, Zeitzonen und vorab eingetragene Schritte.** Peek liest Huayas eigenen Tracker aus, zeigt jeden Schritt mit Ort und Zielland, verwirft personenbezogene Felder (Namen, Adressen, Postleitzahlen, Unterschriften, Fotos, Gewicht) und nennt kein Lieferdatum (Huaya liefert keines). Zeiten rechnet Peek von Pekinger Zeit (`UTC+8`) – bzw. bei kopierten DHL-Scans und US-Scans an einem US-Ort von der dortigen Ortszeit, bei J&T-Express-Scans von der Zeit in Mexiko-Stadt – in deine Gerätezeitzone um („Uhrzeiten in deiner Zeitzone: …“). Liegt eine Zeit in Peking noch in der Zukunft, hält Peek die Zeile bis dahin aus den Scans heraus. Ebenso hält Peek Zeilen zurück, die laut Datensatz mehr als 12 Stunden vor ihrer angegebenen Zeit geschrieben wurden, oder mehr als 6 Stunden davor in derselben Sekunde wie eine solche Zeile (Prognosen statt echter Scans). Dann beginnt der Verlauf bis zur Zustellung oder Rücksendung mit „Andere Seiten zeigen vielleicht mehr Schritte, aber das sind nur Vorhersagen.“, und „Anzeigen“ listet diese Zeilen ausgegraut auf, jede mit „Vorhergesagt für“ und ihrem Tag. Solche Zeilen zählen nie als Scan, ändern nie den Status und lösen keine Mitteilung aus.

**Kein automatischer Wechsel & Mitteilungen.** Da Huayas Datensatz keinen auslesbaren Endzusteller samt Nummer mitliefert, bleibt Peek bei Huayas Tracker; füge die Nummer des lokalen Zustellers vom Händler als zweites Paket hinzu. Peek prüft bis zu alle 10 Minuten bei geöffnetem Paket oder am Liefertag mit aktiven Mitteilungen sowie bis zu alle 2 Minuten ab 6 Uhr Ortszeit am Tag mit Status „In Zustellung“ (sonst und nach 12 Stunden auf „Etikett erstellt“ / „Beim Paketdienst eingegangen“ bzw. zwei ruhigen Tagen stündlich). Von Huayas eigenen Meldungen zählt nur `航班落地，清关中` als „Beim Zoll“ für „Wichtige Schritte“, während „Nur die Zustellung“ lediglich bei kopierten Zustellscans von DHL, J&T Express oder dem US-Partner anschlägt – nutze daher besser „Jeden Scan“ oder „Wichtige Schritte“. Huaya oder den Verkäufer kontaktieren kann Peek nicht.

## Häufige Fragen zur Sendungsverfolgung von Huaya Express

### Wer steckt hinter Huaya Express (`华亚运通`)?

Die Marke gehört zu `华亚运通国际货运代理有限公司` (*Huaya Yuntong International Freight Forwarding*), einem Weiterleiter in Guangzhou (Festlandchina) für Online-Shops. Laut Startseite nutzt er vor allem [China Post](carrier:china-post), eigene verzollte Linien in die USA und nach Europa, Spezialrouten für sensible Waren und die vier großen Expressdienste. Seine Frachtbriefe lauten `HYYT`, zwölf Ziffern, `GZ`.

### Warum endet mein Tracking bei `It has been handed over to the service provider.`?

Weil Huayas Abschnitt dort endet: Ein Zusteller in deinem Land befördert das Paket unter einer eigenen Nummer weiter, die Huayas Zeile nicht nennt. Der Verkäufer kann dir Zusteller und Nummer mitteilen.

### Was bedeuten ausgegraute Zeilen in Peek?

Diese Schritte wurden im System mehr als 12 Stunden vor der angezeigten Scan-Uhrzeit angelegt (oder mehr als 6 Stunden davor in derselben Sekunde): Es sind vorausgeschriebene Prognosen und keine bestätigten Scans, selbst wenn dort eine Zustellung steht.

### An wen wende ich mich, wenn mein Huaya-Paket fehlt?

An den Online-Shop, bei dem du gekauft hast: Er ist Huayas Auftraggeber, und Huayas Reklamationsbedingungen gelten für Versender. Huaya selbst führt nur chinesischsprachige Kontaktdaten.

:::sources
- [Huaya Express: 华亚运通国际货运代理有限公司 (Startseite)](http://hyytgz.com/) – Firmenname und Logo, bediente Shop-Plattformen, Hauptleistungen, 物流跟踪-Links (eigener Tracker und 17TRACK), Hotline 18039073131, E-Mail 2065332407@qq.com und Adresse in Baiyun (Guangzhou)
- [Huaya Express: 欧洲双清包税小包 (Europa- und UK-Linien)](http://hyytgz.com/ywfw.php?id=21) – UK-Linien mit US-Text bis USPS sowie Reklamationsbedingungen für Versender
- [Huaya Express: 澳大利亚专线小包 (Australien-Linie)](http://hyytgz.com/ywfw.php?id=27) – Australien-Linie mit Flug in die USA für USPS sowie Versenderbedingungen
- [Huaya Express: 投诉建议 (Beschwerden und Vorschläge)](http://hyytgz.com/message.php) – Formularfelder (Name, Mobilnummer, E-Mail, Nachricht)
- [Huaya Express: 关于我们 (Über uns)](http://hyytgz.com/about.php) – Seite ohne Inhalt
- [Huaya Express: 服务网点 (Standorte)](http://hyytgz.com/outlets.php) – Seite ohne Inhalt
- [Universal Parcel Scraper: Huaya Express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/huaya-express) – HYYT…GZ-Format, Aliase, trackIndex.htm-Link, englische/chinesische/schwedische Status, DHL- und US-Scans, Zeitzonen und vorab eingetragene Zeilen
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Katalogerkennung, Referenzsuche und Darstellung in Peek
:::
