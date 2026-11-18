---
title: Pengyou Sendungsverfolgung: PY-Nummer, Status & Hilfe
description: Verfolge ein Pengyou-Paket (捧友) aus Shenzhen: Aufbau der PY-Sendungsnummer, Bedeutung der Scans, vorab geschriebene Schritte und an wen du dich wendest.
slug: pengyou-sendungsverfolgung
published: 2026-11-18
updated: 2026-11-18
---

Pengyou (捧友, 深圳市捧友供应链有限公司), das seinen englischen Namen im Logo seiner Website als Shenzhen Pony Supply Chain Co., Ltd schreibt, ist ein Spediteur aus Shenzhen, dessen Linien Pakete per Luftfracht nach Europa und in die Vereinigten Staaten für Exporteure, andere Spediteure und Online-Händler befördern. Um eine Sendung zu verfolgen, gib ihre Nummer in das Feld `运单查询` (Frachtbriefabfrage) auf der Startseite von Pengyou, szpy56.com, oder direkt in das Suchfeld oben ein. Der Shop, bei dem du bestellt hast, teilt dir die Nummer zusammen mit den Versanddaten mit.

## Pengyou-Sendungsnummer: wie sie aufgebaut ist

Pengyou veröffentlicht kein Nummernformat, und auf keiner öffentlichen Seite ist eine vollständige Pengyou-Nummer zu sehen. Das Muster, das [Peek](/) mit Pengyou verknüpft, besteht aus drei Buchstaben, neun Ziffern und der Endung `PY` (14 Zeichen). Mit erfundenen Ziffern:

| Nummer | Was das ist |
| --- | --- |
| `ZXA246813579PY` | `ZXA`, neun Ziffern und `PY`: eine Nummernserie, auf die Pengyous eigener Tracker antwortet und die Peek sofort erkennt. |
| `ZZZ999999980PY` | Drei andere Buchstaben, neun Ziffern und `PY`: das Pengyou zugeordnete Muster (nur eine schwache Übereinstimmung, bis der Tracker antwortet). |
| `ZZZ24681357PY` | Nur acht Ziffern: passt nicht zu diesem Aufbau. Prüfe, ob eine Ziffer fehlt. |
| `RR246813576PY` | Zwei Buchstaben, neun Ziffern und `PY`: eine internationale Postsendungsnummer, nicht von Pengyou. |

Kleinschreibung und Leerzeichen spielen keine Rolle, wenn du die Nummer in Peek einfügst.

**Wo du sie findest.** Auf seiner Website bezeichnet sich Pengyou als Versandspezialist für unabhängige Online-Shops mit Verkauf nach Europa und in die USA (`欧美独立站运输专家`). Abgesehen vom Abfragefeld auf der Startseite richtet sich nichts auf der Website an Paketempfänger. Wenn du die Nummer nicht findest, hilft [Sendungsnummer finden](guide:find-tracking-number).

**Referenznummer des Shops.** Pengyous Tracker findet einen Auftrag auch über die eigene Referenznummer des Verkäufers.

## Pengyou-Tracking-Status erklärt

Pengyou veröffentlicht keine Liste seiner Statusmeldungen. Das kann dir im Tracker begegnen:

| Anzeige im Tracker | Was das für dich bedeutet |
| --- | --- |
| `Shenzhen Processing Center has received it` | Das Bearbeitungszentrum von Pengyou in Shenzhen hat das Paket erhalten. Es ist der erste Scan in den Händen von Pengyou. |
| Ein Ortsname in eckigen Klammern nach einer Zeile, etwa `[Shenzhen City]` | Der Ort, an dem dieser Schritt erfasst wurde. |
| Schritte in exakt gleichmäßigen Zeitabständen | Ein bereits beim Anlegen des Auftrags im Voraus geschriebener Zeitplan, keine echten Scans deines Pakets (siehe unten). |
| Deine Nummer unter `参考号` (Referenz) und alle übrigen Felder leer | Pengyous Tracker kennt diese Nummer nicht. Prüfe sie beim Verkäufer. |

**Vorab eingetragene Schritte.** Auf der Tracking-Plattform, die Pengyou nutzt, kann der gesamte Verlauf eines Auftrags schon beim Erstellen der Bestellung festgelegt werden: jeder Schritt bis hin zur Zustellung wird mit festem Abstand vordatiert und eingeblendet, sobald sein Zeitpunkt verstrichen ist. Pengyous eigener Tracker und Tracking-Dienste, die seine Daten kopieren, können diese Zeilen anschließend wie echte Scans darstellen. Tatsächlich handelt es sich um eine Prognose: Sie zeigt, wo das Paket an einem bestimmten Tag planmäßig sein sollte, nicht wo es sich wirklich befindet. Verlass dich bei solchen Einträgen nicht auf ein Lieferdatum und werte ein auf diese Weise erzeugtes `Delivered` nicht als Zustellnachweis.

**Uhrzeiten nach Pekinger Zeit.** Die Zeitstempel tragen keine Zeitzonenangabe und laufen auf der Pekinger Uhr der Plattform (`UTC+8`). Während der europäischen Winterzeit (vom letzten Sonntag im Oktober bis zum letzten Sonntag im März) entspricht `20:00` Uhr in Peking `13:00` Uhr MEZ in Deutschland, Österreich und der Schweiz; während der Sommerzeit (vom letzten Sonntag im März bis zum letzten Sonntag im Oktober) entspricht sie `14:00` Uhr MESZ.

## Wer Pengyou-Pakete zustellt

Laut seiner Website betreibt Pengyou vor allem Linien nach Europa und in die USA mit „Doppelverzollung inklusive Abgaben“ (`双清包税`), also Zollabfertigung auf beiden Seiten und im Frachtpreis eingerechneten Abgaben. Der Hauptsitz liegt am Flughafen Bao'an in Shenzhen, mit Niederlassungen in Bantian, Guangzhou und Hongkong.

Auf der Startseite nennt Pengyou vier Hauptlinien beim Namen: `欧洲DHL`, `欧洲DHL专线` und `欧洲DPD专线` (`欧洲` steht für Europa) sowie `美国UPSP专线` (`美国` steht für die USA). Die Unterseite jeder einzelnen Linie enthält nichts außer ihrer Überschrift. Unter `合作伙伴` (Partner) zeigt die Seite die Logos von DHL, UPS, FedEx, TNT, USPS, DPD und des Tracking-Diensts 17TRACK. Der dargestellte Ablauf endet zwar mit der Auslieferung am Zielort (`配送目的地`) und der Unterschrift des Empfängers, doch die Website nennt nirgends, welcher Paketdienst ein konkretes Paket im Zielland übergibt. Ebenso fehlen Angaben zu Laufzeiten, Zustellversuchen, Abholstationen oder Aufbewahrungsfristen.

**Zoll und Einfuhrabgaben (EU).** Wenn für ein als abgabenfrei angekündigtes Paket dennoch Einfuhrabgaben verlangt werden, frag vor einer Zahlung beim Verkäufer nach. In der **Europäischen Union** gilt seit dem 1. Juli 2026 für im Fernverkauf außerhalb des EU-Zollgebiets gekaufte Waren in Sendungen mit einem Sachwert bis 150 € ein Zoll von 3 € pro Warenkategorie; seit dem 1. November 2026 fällt unabhängig vom Warenwert zusätzlich eine EU-Bearbeitungsgebühr von 2 € pro Warenkategorie an (eine Kategorie umfasst alle Waren einer Sendung mit derselben Zolltarifnummer, derselben Warenbezeichnung und – soweit anzugeben – demselben Ursprung, ungeachtet der Stückzahl). In **Deutschland** gehören die Insel Helgoland und das Gebiet von Büsingen weder zum Zollgebiet noch zum Mehrwertsteuergebiet der EU. Wie Einfuhrumsatzsteuer, Zollabfertigung und echte Zahlungsaufforderungen vor Ort ablaufen, liest du unter [Paket beim Zoll](guide:customs) und [gefälschte Paket-SMS erkennen](guide:delivery-scam-texts).

## Pengyou-Paket kommt nicht an oder Sendungsverfolgung steht still

**Auffällig regelmäßige Schritte.** Das ist der vorab angelegte Zeitplan, keine physischen Scans. Folgt über mehrere Tage kein echter Scan, frag beim Verkäufer nach, ob das Paket bereits übergeben wurde.

**Keine Bewegung nach Shenzhen.** Bleibt der Verlauf bei `Shenzhen Processing Center has received it` oder kurz danach stehen, frag den Verkäufer, ob ein anderer Paketdienst in deinem Land ausliefert, und bitte um dessen Sendungsnummer – du kannst sie in Peek als eigenes Paket anlegen. Mehr dazu unter [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating) und [Paket aus China verfolgen](guide:tracking-from-china).

**Verloren, beschädigt oder zurückgeschickt.** Pengyou veröffentlicht weder ein Reklamationsverfahren noch Geschäftsbedingungen für Empfänger: Die Unterseiten zu Firmenprofil und Kontakt sind leer. Da der Verkäufer den Versand bei Pengyou gebucht hat, wende dich für eine Erstattung oder Ersatzlieferung an den Shop oder den Marktplatz. Siehe auch [verlorene Pakete](guide:lost-parcel), [zurück an den Absender](guide:returned-to-sender) und [zugestellt, aber nicht erhalten](guide:delivered-not-received).

## Pengyou Kontakt und Kundenservice

Wie am 18. November 2026 auf szpy56.com aufgeführt, ausschließlich auf Chinesisch:

- **Telefon**: `181 2470 1707` und `188 7908 6605`, zwei chinesische Mobilfunknummern (`+86` aus dem Ausland), ausgewiesen als 24-Stunden-Beratungshotline (`全天24小时咨询热线`). Zu gesprochenen Sprachen macht Pengyou keine Angaben.
- **QQ**: `2853292077`, das offizielle Service-QQ (`官方服务QQ`), das sich über den Link zur Online-Kundenberatung (`在线客服咨询`) öffnet.
- **WeChat**: ein QR-Code für das offizielle Konto (`官方微信二维码`).

Pengyou nennt weder eine E-Mail-Adresse noch eine Straßenanschrift. Bei allen Fragen zu deiner Bestellung ist der Verkäufer dein Ansprechpartner.

## Ein Pengyou-Paket mit Peek verfolgen

[Peek](/) ordnet eine Nummer mit dem Präfix `ZXA` sofort Pengyou zu, da Pengyous eigener Tracker diese Serie beantwortet. Bei anderen Anfangsbuchstaben lässt sich Pengyou allein anhand der Form nicht sicher bestimmen: drei Buchstaben, neun Ziffern und `PY` gelten nur als schwache Übereinstimmung. Füge die Nummer trotzdem ein: Peek unterstützt über 3.500 Paketdienste. Beim Hinzufügen fragt Peek Pengyous Tracker ab („Tracking-Dienste werden geprüft…“) und zeigt „Pengyou hat dieses Paket.“, sobald der Tracker die Nummer kennt. Andernfalls tippe auf „Ändern“ und wähle Pengyou aus (die Suche nach `捧友` findet ihn ebenfalls), oder nutze „Paketdienst ändern“ bei einem bereits gespeicherten Paket. Genauso gehst du mit einer Referenznummer des Verkäufers vor: Peek zeigt dann zusätzlich die eigentliche Sendungsnummer des Auftrags an. Einen direkten Pengyou-Tracking-Link zum Einfügen gibt es nicht, weil die Abfrage über ein Formular läuft.

Peek liest Pengyous eigenen Tracker aus und zeigt den Status, jeden Scan mit Ortsangabe sowie das Zielland, sofern der Datensatz es nennt. Die Scans erscheinen im Wortlaut von Pengyou einschließlich der Stadt in eckigen Klammern – mit einer Ausnahme: Steht hinter `Delivered` ein Ortsname, zeigt Peek `Delivered` an, übernimmt die Stadt als Ort und lässt die Postleitzahl weg. `Shenzhen Processing Center has received it` wird als „Beim Paketdienst eingegangen“ eingestuft. Die Uhrzeiten werden als Pekinger Zeit gelesen und in der Zeitzone deines Geräts dargestellt („Uhrzeiten in deiner Zeitzone: …“ auf peektracker.com und in der iPhone-App). Namen, Anschrift, Postleitzahlen, Gewicht, Gebühren und Unterschrift aus Pengyous Datensatz blendet Peek aus; ein voraussichtliches Lieferdatum erscheint nicht, weil Pengyou keins liefert.

Peek zeigt niemals einen Schritt an, der mehr als zwölf Stunden vor seinem ausgewiesenen Zeitstempel geschrieben wurde, oder mehr als sechs Stunden davor in derselben Sekunde wie ein solcher Schritt, und hält jeden Schritt zurück, der nach Pekinger Zeit in der Zukunft liegt. Bei einem Auftrag mit vorab eingetragenen Schritten hält Peek auch `Delivered` zurück, selbst wenn es am selben Tag geschrieben wurde: Pengyous Plattform trägt diesen Abschluss gesammelt am Abend nach. Liefert die Datenschnittstelle des Trackers keine Schritte zu einer Nummer, liest Peek stattdessen Pengyous Tracking-Webseite; da dort nicht ersichtlich ist, wann ein Eintrag geschrieben wurde, hält Peek auf diesem Weg jedes `Delivered` zurück. Auch Kopien solcher Schritte bei anderen Tracking-Diensten sortiert Peek aus und bestimmt den Status allein anhand der behaltenen Scans statt anhand des Zusammenfassungsstatus. Ein Auftrag, dessen Schritte allesamt im Voraus geschrieben wurden, hat in Peek zunächst keinen Verlauf und steht auf peektracker.com auf „Warten auf Pengyou“, ohne Hinweis auf eine falsche Nummer: Peek wertet vorab eingetragene Schritte – genau wie eine ohne Scan angelegte Sendung – als bei Pengyou erfasst und fragt Pengyous Tracker für ein Paket in der iPhone-App oder in deinem Konto nach den ersten Stunden alle 6 Stunden weiter ab. Bei einer Abfrage ohne Konto prüft Peek in den ersten 6 Stunden, danach beim Öffnen des Links (höchstens alle 6 Stunden, nach den ersten zwei Tagen höchstens einmal täglich) und bei eingeschalteten Mitteilungen täglich bis 30 Tage nach der Abfrage. Solange Peek vorab geschriebene Schritte zurückhält, steht unter dem Paketstatus bis zur Zustellung oder Rücksendung: „Die Website von Pengyou zeigt vorab eingetragene Schritte, sogar eine Zustellung. Peek kann sie nicht bestätigen und lässt sie deshalb weg. Such dort nach dem Paketdienst, der in deinem Land zustellt, und seiner Sendungsnummer; steht keiner da, frag den Verkäufer.“ Ein zurückgehaltener Schritt zählt nie als Scan, ändert keinen Status und löst keine Benachrichtigung aus.

Peek prüft bis zu alle 10 Minuten, solange du das Paket geöffnet hast oder am Zustelltag mit eingeschalteten Mitteilungen, und sonst stündlich – ebenso nach zwei Tagen ohne Neuigkeiten (beziehungsweise nach 12 Stunden auf „Etikett erstellt“ oder „Beim Paketdienst eingegangen“); über Tracking-Dienste prüft Peek höchstens stündlich, nach einem Tag ohne Scan alle 3 Stunden und am Zustelltag bis zu alle 15 Minuten, solange das Paket geöffnet ist oder Mitteilungen eingeschaltet sind. Benachrichtigungen lassen sich auf „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“ stellen. Peek kann weder Pengyou noch den Verkäufer für dich kontaktieren.

## Häufige Fragen zur Pengyou-Sendungsverfolgung

### Was ist Pengyou (捧友)?

Dahinter steht 深圳市捧友供应链有限公司 – im eigenen englischen Logo Shenzhen Pony Supply Chain Co., Ltd –, ein Spediteur aus Shenzhen, dessen Luftfrachtlinien Pakete für Exporteure, Spediteure und Online-Händler nach Europa und in die USA befördern. Seine Website ist szpy56.com.

### Warum zeigt Pengyous Tracker Schritte an, die in Peek fehlen?

Weil die von Pengyou genutzte Plattform den gesamten Zeitplan eines Pakets bis zur Zustellung im Voraus anlegen kann. Diese Einträge sind eine Prognose und keine echten Scans; deshalb lässt Peek sie einschließlich `Delivered` weg.

### Wo kann ich ein Pengyou-Paket verfolgen?

Im Feld `运单查询` (Frachtbriefabfrage) auf der Startseite von szpy56.com, das bis zu 10 Nummern (eine pro Zeile) annimmt und Pengyous Tracker öffnet – oder direkt oben in Peek, wo du Pengyou bei Bedarf manuell auswählst.

### Wie lange dauert die Lieferung mit Pengyou?

Pengyou veröffentlicht keine Lieferzeiten. Richte dich nach dem Lieferfenster, das dir der Online-Shop bei der Bestellung genannt hat, und nicht nach den Terminen vorab eingetragener Schritte.

### An wen wende ich mich, wenn mein Pengyou-Paket fehlt?

An den Verkäufer, bei dem du bestellt hast – er hat den Versand bei Pengyou beauftragt. Pengyou selbst nennt auf seiner chinesischen Seite lediglich zwei chinesische Mobilnummern, einen QQ-Kontakt und einen WeChat-Code, aber keinen Empfängerservice.

:::sources
- [Pengyou: 深圳市捧友供应链有限公司 (Startseite, auf Chinesisch)](http://szpy56.com/) – Firmenname und englischer Name im Logo, Europa- und USA-Linien mit Doppelverzollung inklusive Abgaben, Hauptsitz und Niederlassungen, Zielgruppe, Hauptlinien, Suchfeld `运单查询` für bis zu 10 Nummern, Partnerlogos, Serviceablauf, 24-Stunden-Mobilnummern, QQ und WeChat
- [Pengyou: 商业快递 (Kommerzieller Express, auf Chinesisch)](http://szpy56.com/sykd/) – Linien Europa DHL und Europa DPD, deren Unterseiten nur aus der Überschrift bestehen
- [Pengyou: 国际专线 (Internationale Linien, auf Chinesisch)](http://szpy56.com/gjzx/) – USA-Linie und Linie Europa DHL, deren Unterseiten nur aus der Überschrift bestehen
- [Pengyou: 公司简介 (Firmenprofil, auf Chinesisch)](http://szpy56.com/gywm/gsjj/) – Seite ohne Inhalt veröffentlicht
- [Pengyou: 联系我们 (Kontakt, auf Chinesisch)](http://szpy56.com/lxwm/) – Seite ohne Inhalt veröffentlicht
- [Europäische Kommission: EU Customs Reform (auf Englisch)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 3 € Zoll je Warenkategorie auf Sendungen bis 150 € seit 1. Juli 2026 und 2 € EU-Bearbeitungsgebühr seit 1. November 2026
- [Europäische Kommission: Guidance on the EUR 3 customs duty and the Union handling fee (PDF, auf Englisch)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € Bearbeitungsgebühr je Warenkategorie unabhängig vom Warenwert sowie Definition einer Warenkategorie
- [Europäische Kommission: Territorial Scope (auf Englisch)](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Helgoland und das Gebiet von Büsingen außerhalb des Zollgebiets und des Mehrwertsteuergebiets der EU
- [Europäische Kommission: Summertime (auf Englisch)](https://transport.ec.europa.eu/transport-themes/summertime_en) – europäische Sommerzeit vom letzten Sonntag im März bis zum letzten Sonntag im Oktober
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Muster `ZXA…PY` und `…PY`, Wortlaut der Scans und Stadt in Klammern, Antwort bei unbekannter Nummer, Referenzsuche, vorab geschriebene Verläufe und von Peek zurückgehaltene Schritte (einschließlich `Delivered`), Webseite als Rückfallweg, Pekinger Uhrzeit, angezeigte und ausgeblendete Felder
:::
