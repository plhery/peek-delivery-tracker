---
title: 360Lion Sendungsverfolgung: WSH- & AR-Codes & Kontakt
description: 360Lion-Express-Paket aus China nach Lateinamerika oder in die USA verfolgen: WSH- und AR-Nummern, Scan-Codes, letzte Meile, Pausen und Kontakt.
slug: 360lion-sendungsverfolgung
published: 2026-11-24
updated: 2026-11-24
---

360Lion (*360Lion Express*, chinesisch *纬狮物流 / 纬狮物联网*) ist ein chinesischer Logistikdienstleister für grenzüberschreitenden E-Commerce, gegründet 2015 mit Hauptsitz in Shenzhen sowie Niederlassungen und Betriebszentren in Shanghai, Guangzhou, Dongguan, Yiwu und Hongkong. Das Unternehmen ist auf eigene Luftfrachtlinien für Kleinsendungen, FBA-/FBM-Spedition und Auslandslager aus China nach Lateinamerika und in die USA spezialisiert (Mexiko, Chile, Peru, Brasilien, Kolumbien, Ecuador, Argentinien, Uruguay, Bolivien, Paraguay, El Salvador, Costa Rica, Guatemala, Venezuela, Panama und USA) – genutzt von Händlern auf Mercado Libre, Amazon, AliExpress, Shopee, SHEIN, Temu, TikTok Shop, Walmart und Falabella. Sendungen lassen sich im [360Lion-Tracking-Portal](https://toms.360lion.com/track) oder auf [360lion.com/en/](https://www.360lion.com/en/) (bis zu 50 Nummern gleichzeitig) prüfen, oder du fügst deine Nummer oben ein.

## 360Lion-Sendungsnummern: WSH- und AR-Formate

Deine Sendungsnummer findest du in der Bestellübersicht des Marktplatzes oder in der Versandbestätigung per E-Mail. 360Lion-Pakete nutzen vor allem zwei Formate:

| Format (erfunden) | Was es ist |
| --- | --- |
| `WSHMX4829173640YQ` | 360Lion-Linienfrachtbrief: `WSH`, zweistelliger Ländercode des Ziellands (z. B. `MX` für Mexiko), 10 Ziffern und `YQ`. |
| `AR4829173640` | `AR` gefolgt von 10 Ziffern (für Sendungen nach Argentinien). Teilt sich dieses Format mit [Colis Privé](carrier:colis-prive), [Aramex Australia](carrier:aramex-au) und [Aramex New Zealand](carrier:aramex-nz). |

Trägt deine Bestellung nach Brasilien oder Mexiko den Barcode eines anderen Dienstes – etwa `AJ` bei [Anjun Logistics](carrier:anjun), `8880` bei [J&T Express Brazil](carrier:j-and-t-br) oder `JMX` bei [J&T Express Mexico](carrier:j-and-t-mx) – findest du weitere Hinweise in [Sendungsnummern-Formate](guide:tracking-number-formats).

## Was die 360Lion-Sendungsstatus bedeuten

360Lion ordnet Sendungen übergeordneten Zuständen (`Processing`, `Pre-alert`, `In transit.`, `Returned`, `Delivered`) zu und versieht Scans mit Codes (`WSO*` im Ursprungsland China, `WSD*` im Zielland). Sobald das Paket den lokalen Zusteller im Zielland erreicht, kann der Tracker dessen eigene Meldungen anzeigen.

| 360Lion zeigt (Code / Gruppe) | Was das für dich bedeutet | Peek zeigt |
| --- | --- | --- |
| `Processing` (`PRECESSING`) | Der Verkäufer hat den Versandauftrag bei 360Lion angelegt, bevor die Hauptlauf-Scans beginnen. | „Etikett erstellt“ |
| Schritt `HUB` | Im chinesischen Sortierzentrum von 360Lion eingegangen. | „Beim Paketdienst eingegangen“ |
| `departed from airport` (`WSO04`), `arrived at airport` (`WSD00`), `In transit.` (`IN_TRANSIT`) | Per Luftfracht aus China abgeflogen (mit Flughafencode wie PVG) und am Zielflughafen gelandet. | „Unterwegs“ |
| Schritt `CUSTOMS` | In der Ausfuhr- oder Einfuhrzollabfertigung. | „Beim Zoll“ |
| `handover to lastmile` (`WSD02`), `Arrived at sorting center` (`WSD051`), `package on the way to delivery office` (`WSD050`) | An den lokalen Zustellpartner im Zielland übergeben, sortiert und Richtung Zustellbasis weitergeleitet. | „Unterwegs“ |
| `Returned` (`RETURNED_BACK`) | Auf dem Rückweg zum Absender oder Ursprungslager. | „Zurück an Absender“ |
| `Delivered` (`DELIVERED`) | Vom lokalen Zustellpartner an den Empfänger zugestellt. | „Zugestellt“ |

Beachte, dass der Zustand `Pre-alert` (`WARNING` / *预警*) bei 360Lion sowohl eine Vorankündigung als auch einen Warnhinweis bedeuten kann; Peek richtet sich daher nach den eigentlichen Scan-Zeilen und wertet `Pre-alert` allein nicht als Problem.

## So läuft der grenzüberschreitende 360Lion-Versand ab

Auf [360lion.com/en/](https://www.360lion.com/en/) und [360lion.com](https://www.360lion.com/) beschreibt sich 360Lion als grenzüberschreitender Linienanbieter und Airline-Generalverkaufsagent (GSSA) mit regelmäßigen Charterflügen von China nach Lateinamerika sowie Auslandslagern in Mexiko, Chile, Brasilien, Kolumbien und den USA.

**Zustellung auf der letzten Meile.** Nach dem Zielflughafen (`WSD00`) und dem Einfuhrzoll übergibt 360Lion das Paket (`WSD02`) an einen lokalen Paketdienst in deinem Land, der es zustellt.

**Empfängerregeln und Zoll.** Da 360Lion geschäftliche Versender statt Privatkunden bedient, nennt die Website für Kleinsendungen weder maximale Zustellversuche noch Paketshop-Lagerfristen oder Laufzeitgarantien (die Seite [FAQ & Bedingungen](https://www.360lion.com/?m=home&c=Lists&a=index&tid=82) behandelt Händlerregeln wie SEND-Luftfracht, verbotene Güter und eine 30-tägige Händler-Reklamationsfrist). Einfuhrumsatzsteuer und Zölle richten sich nach dem Zollrecht deines Ziellands. Siehe [Paket beim Zoll](guide:customs).

## 360Lion-Paket steckt fest, bewegt sich nicht oder kam nicht an

Da 360Lion mit dem Onlineshop oder Marktplatz statt direkt mit dem Käufer kontrahiert, wende dich bei Verspätungen, Adressfehlern oder Erstattungen immer zuerst an Verkäufer oder Plattform.

**Keine Updates zwischen China und Zielland.** Langstreckenflüge von China nach Lateinamerika und Warteschlangen beim Einfuhrzoll verursachen häufig mehrtägige Lücken zwischen `departed from airport` (`WSO04`) und `arrived at airport` (`WSD00`). Siehe [Warum sich die Sendungsverfolgung nicht aktualisiert](guide:tracking-not-updating).

**Lokale Übergabe ohne zweite Nummer.** Peek liest die Meldungen des lokalen Zustellers über den 360Lion-Tracker unter deiner `WSH`- oder `AR`-Nummer mit und zeigt keine zweite, lokale Nummer an. Scheitert eine Zustellung und du brauchst Kontakt oder Nummer des lokalen Partners, frag den Verkäufer oder schreib an die Paketauskunft von 360Lion.

**Zugestellt, beschädigt oder verloren.** Frag bei `Delivered` zuerst im Haushalt, bei Nachbarn und am Empfang nach; fehlt die Sendung oder ist sie beschädigt, sichere Belege und melde den Fall rechtzeitig vor Ablauf des Käuferschutzes beim Händler oder Marktplatz. Siehe [Zugestellt, aber nicht erhalten](guide:delivered-not-received), [Beschädigtes Paket](guide:damaged-parcel), [Verlorenes Paket](guide:lost-parcel) und [Zurück an Absender](guide:returned-to-sender).

## Kundenservice und Kontakte von 360Lion

Veröffentlichte Kontakte auf der [Kontaktseite von 360Lion](https://www.360lion.com/?m=home&c=Lists&a=index&tid=79) und auf [360lion.com/en/](https://www.360lion.com/en/):

- **E-Mail für Sendungsanfragen (*查件业务*):** `customerservice@360lion.com`.
- **Allgemeine / geschäftliche E-Mail:** `info@360lion.com`.
- **Telefon Zentrale Shenzhen:** `+86 755 2360 1325` (Shanghai `+86 21 6142 1360`, Guangzhou `+86 20 8653 4133`).
- **Hauptsitz:** 7/F, Block A, Star River World, No. 1 Yabao Road, Bantian Street, Longgang District, Shenzhen, Guangdong, China.

> Vorsicht vor gefälschten SMS oder WhatsApp-Nachrichten mit dringendem Zahlungslink zur „Zollfreigabe“ einer Auslandssendung. Prüfe echte Abgabenforderungen immer zuerst auf deiner Marktplatz-Bestellseite oder im offiziellen Post-/Zollportal deines Landes. Siehe [Betrügerische Paket-SMS](guide:delivery-scam-texts).

## 360Lion-Paket mit Peek verfolgen

[Peek](/) prüft automatisch 360Lion, wenn du einen `WSH...YQ`-Code eingibst (wie `WSHMX4829173640YQ`, Ziffern erfunden) – dort ist 360Lion der einzige Kandidat – oder einen Code aus `AR` + 10 Ziffern (wie `AR4829173640`), bei dem Peek 360Lion neben [Colis Privé](carrier:colis-prive), [Aramex Australia](carrier:aramex-au) und [Aramex New Zealand](carrier:aramex-nz) fragt und „360Lion hat dieses Paket.“ zeigt, sobald 360Lion antwortet. Ein eingefügter `toms.360lion.com/track?trackNumber=`-Link wählt 360Lion direkt aus; sonst tippe beim Hinzufügen auf „Ändern“ oder in der Paketansicht auf „Paketdienst ändern“ und wähle 360Lion (die Suche nach „360Lion Express“, „WSH“ oder „纬狮物流“ findet ihn ebenfalls).

Peek liest den 360Lion-Tracker direkt aus und zeigt die Phase („Etikett erstellt“, „Beim Paketdienst eingegangen“, „Unterwegs“, „Beim Zoll“, „Zurück an Absender“ oder „Zugestellt“), den Text jedes Scans (den des lokalen Zustellers, wo 360Lion ihn durchreicht, sonst das Englisch von 360Lion), den Ort als Ländername oder dreistelligen Flughafencode, das Zielland und die Zustellzeit. Jeder Scan enthält den Zeitversatz seines Orts – etwa `+8` in China und `-3` in Argentinien – und erscheint in der Uhrzeit deines Geräts. Peek blendet die Fortschrittsleiste der Website, das Ursprungsland, den Laufzeitzähler und den Zustellnachweis aus; der 360Lion-Tracker liefert kein voraussichtliches Lieferdatum, keinen Dienstnamen und kein Gewicht.

Solange du das Paket in Peek geöffnet hast oder am Zustelltag mit aktivierten Benachrichtigungen prüft Peek bis zu alle 10 Minuten, solange der 360Lion-Tracker antwortet. Sonst prüft Peek stündlich. Nach 12 Stunden mit nur „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ oder nach zwei Tagen ohne Neuigkeiten prüft es unabhängig davon stündlich, und nach sieben Tagen auf „Etikett erstellt“ alle 6 Stunden. Antwortet der 360Lion-Tracker nicht und verfolgt Peek das Paket allein über Tracking-Dienste, prüft es stündlich, nach einem stillen Tag alle 3 Stunden und am Zustelltag bis zu alle 15 Minuten, solange du die Sendung geöffnet hast oder Benachrichtigungen aktiv sind.

Ist der neueste Scan vier Tage alt und das Paket steht noch auf „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“, markiert Peek es mit dem Datum dieses Scans, etwa „Keine Aktualisierung seit Mo., 12. Okt.“. Auf der Paketseite steht dann: „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei 360Lion nach.“, mit dem Link „Hol dir Hilfe bei 360Lion“ zur Kontaktseite von 360Lion. Nach drei Wochen ohne neuen Scan heißt es: „Seit über drei Wochen nichts Neues. Ist das Paket noch nicht angekommen, frag beim Verkäufer nach. Peek fragt weiter bei 360Lion nach.“

Als Benachrichtigungsstufe kannst du „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“ wählen. Peek überwacht nur Sendungsstatus und kann weder verzollen noch 360Lion oder deinen lokalen Zustellpartner für dich kontaktieren.

## Fragen zur 360Lion-Sendungsverfolgung

### Was ist 360Lion Express?

360Lion (gegründet 2015 in Shenzhen, China) ist ein grenzüberschreitender E-Commerce-Logistiker für Luftpaketlinien aus China nach Lateinamerika und in die USA, genutzt von Händlern auf Mercado Libre, Amazon, AliExpress, Shopee, SHEIN und Temu.

### Was bedeuten die Sendungsnummern `WSH...YQ` und `AR...`?

`WSH` + zwei Länderbuchstaben + 10 Ziffern + `YQ` (z. B. `WSHMX4829173640YQ`, Ziffern erfunden) ist das Linienformat von 360Lion, während `AR` + 10 Ziffern (`AR4829173640`) bei 360Lion-Sendungen nach Argentinien vorkommt.

### Wer stellt 360Lion-Pakete vor Ort zu?

360Lion fliegt Pakete aus China zum Flughafen deines Ziellands und übergibt sie (`handover to lastmile`) an eine lokale Post oder einen Paketdienst für die Haustürzustellung. Die Meldungen des lokalen Partners erscheinen im 360Lion-Tracking.

### Wie kontaktiere ich 360Lion bei einem vermissten Paket?

Frag für Käuferschutz und Erstattungen zuerst Verkäufer oder Marktplatz. Du kannst außerdem die Sendungsauskunft von 360Lion unter `customerservice@360lion.com` anschreiben oder das Büro in Shenzhen unter `+86 755 2360 1325` anrufen.

:::sources
- [360Lion Express: English Home](https://www.360lion.com/en/) – gegründet 2015, Standorte Shenzhen/Shanghai/Guangzhou/Yiwu/Dongguan/Hongkong, 50-Nummern-Tracker, Marktplatzpartner und Sitzadresse
- [360Lion: Chinese Home](https://www.360lion.com/) – eigene Kleinsendungslinien in 15 lateinamerikanische Länder und die USA, Charter-Luftfrachtkapazität und Auslandslager
- [360Lion: Contact Us](https://www.360lion.com/?m=home&c=Lists&a=index&tid=79) – `customerservice@360lion.com`, `info@360lion.com`, Rufnummern und Adressen Shenzhen/Guangzhou/Shanghai (Hilfe-URL in Peek)
- [360Lion: About Us](https://www.360lion.com/?m=home&c=Lists&a=index&tid=78) – Unternehmensporträt
- [360Lion: Dedicated Small Parcels](https://www.360lion.com/?m=home&c=Lists&a=index&tid=90) – grenzüberschreitender B2C-Kleinsendungsdienst
- [360Lion: FAQ & Terms](https://www.360lion.com/?m=home&c=Lists&a=index&tid=82) – Händler-Versandbedingungen, verbotene Güter und 30-Tage-Händlerreklamation
- [360Lion: Tracking Portal](https://toms.360lion.com/track) – öffentliches Tracking-Portal
- [Universal Parcel Scraper: 360lion](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/360lion) – `WSH`- und `AR`-Erkennung, `toms.360lion.com`-Linkregeln, `WSO`/`WSD`-Scan-Codes, explizite Zeitzonen-Offsets, verworfene Felder und Hilfe-URL
:::
