---
title: Chronopost Sendungsverfolgung: Paket aus Frankreich, Status
description: Chronopost-Paket aus Frankreich verfolgen: wie die Sendungsnummer aussieht, was die französischen Status bedeuten, wer zustellt, Pickup-Relais und Kontakt.
slug: chronopost-sendungsverfolgung
published: 2026-11-04
updated: 2026-11-04
---

Chronopost ist der Expressdienst für Pakete der französischen Gruppe La Poste und gehört zu Geopost, der Paketsparte der Gruppe, zu der auch DPD gehört. Ein Paket verfolgst du in Chronoposts Sendungsverfolgung auf chronopost.fr oder hier: Füg die Nummer oben ins Feld ein.

## Chronopost Sendungsnummer: so sieht sie aus

Meist hat die Sendungsnummer von Chronopost 13 Zeichen: zwei Buchstaben, neun Ziffern, zwei Buchstaben. Es gibt auch Nummern mit 14 oder 15 Zeichen. Hast du keine Nummer, frag den Shop oder lies [Sendungsnummer finden](guide:find-tracking-number).

| Nummer (erfunden) | Was es ist |
| --- | --- |
| `XY000000005FR` | Zwei Buchstaben, neun Ziffern, zwei Buchstaben: die übliche Chronopost-Nummer. |
| `AB000000000JB` | Dieselbe Form, aber mit JB, JF oder TS am Ende. |
| `XN000000000000` | XN und 12 Ziffern: auch diese Nummern verfolgt Chronopost. |
| `00000000000000U` | 15 Zeichen, 14 Ziffern und ein Prüfzeichen: die Form der DPD-Paketnummern, die auch Chronopost verfolgt. |
| `20000000000000` | 14 Ziffern, nicht mit 10 am Anfang: kann eine Chronopost-Nummer sein, aber auch DPD und andere Paketdienste nutzen diese Form. |

Die 13-stellige Form ist das internationale Format der Postgesellschaften. Eine Nummer mit FR am Ende kann auch zu Colissimo von La Poste gehören, eine mit AT, CH oder DE zur Post dieses Landes, etwa zur [Österreichischen Post](carrier:austrian-post) oder zur [Schweizerischen Post](carrier:swiss-post). Die 15-stellige Form nutzt auch DPD; das Paket kann also bei [DPD Schweiz](carrier:dpd-ch), [DPD Deutschland](carrier:dpd-de), DPD Portugal oder DPD UK sein. Kennt Chronopost die Nummer nicht, schau, welchen Paketdienst die Versand-E-Mail des Shops nennt. Mehr in [Sendungsnummer: Welcher Paketdienst steckt dahinter?](guide:tracking-number-formats)

## Chronopost Sendungsverfolgung: was die Statusmeldungen bedeuten

Peek liest Chronoposts Sendungsverfolgung auf Französisch und zeigt die Meldungen im Verlauf so, wie Chronopost sie schreibt; nur `Livraison effectuée` heißt dort „Zugestellt“. Rechts steht die Stufe, die Peek dafür zeigt. In der Reihenfolge, in der ein Paket sie meist durchläuft:

| Chronopost schreibt | Was es für dich heißt | In Peek |
| --- | --- | --- |
| `Colis en cours de préparation chez l'expéditeur` | Das Etikett ist erstellt, der Shop hat das Paket aber noch nicht übergeben. | „Etikett erstellt“ |
| `Colis déposé par l'expéditeur` | Der Shop hat es Chronopost übergeben. | „Beim Paketdienst eingegangen“ |
| `Tri effectué dans l'agence de départ` | Im ersten Depot sortiert. | „Unterwegs“ |
| `Colis en cours d'acheminement` | Es ist in Chronoposts Netz unterwegs. | „Unterwegs“ |
| `Colis entré dans le pays de destination` | Es ist im Zielland angekommen. | „Unterwegs“ |
| `Tri effectué dans l'agence de distribution` | Im Depot sortiert, das dir zustellt. | „Unterwegs“ |
| `Livraison reportée de 24h` | Das Depot behält es einen Tag, ohne zuzustellen; danach geht es von selbst weiter. | „Unterwegs“ |
| `Livraison reportée` | Der Partner, der im Zielland zustellt, verschiebt die Zustellung, ohne Angabe eines Grunds und ohne Zustellversuch. | „Unterwegs“ |
| `Colis en cours de livraison` | Heute in Zustellung. | „In Zustellung“ |
| `Echec de livraison suite à l'absence du destinataire.` | Du warst nicht da. | „Zustellversuch“ |
| `Colis non livré : client absent` | Der zustellende Partner hat dich nicht angetroffen. | „Zustellversuch“ |
| `Colis mis à disposition au point de retrait` | Es liegt an einer Abholstelle für dich bereit. | „Abholbereit“ |
| `Colis remis au destinataire au point de retrait` | Du hast es an der Abholstelle abgeholt. | „Zugestellt“ |
| `Colis en retour expéditeur` | Es geht zurück an den Shop. | „Problem gemeldet“ |
| `Livraison effectuée` | Das Paket ist zugestellt. | „Zugestellt“ |

`Livraison reportée de 24h` (etwa nach einer Fehlleitung, zur Prüfung der Adresse oder weil ein Betrieb geschlossen hat) heißt nicht, dass das Paket verloren ist. `Destinataire informé par SMS ou mail` und `Instruction de livraison reçue` halten nur eine Benachrichtigung an dich oder deine Anweisung fest: Das Paket hat sich nicht bewegt, und Peeks Stufe bleibt, wie sie war. Folgt `Livraison effectuée` auf `Colis en retour expéditeur`, ist das Paket wieder beim Shop, nicht bei dir: Peek zeigt dann „Zurück an Absender“, auch wenn die Zeile im Verlauf „Zugestellt“ heißt. Mehr in [Sendungsstatus erklärt](guide:tracking-statuses).

## Chronopost-Paket nach Deutschland, Österreich oder in die Schweiz

Chronopost liefert laut La Poste Groupe in 230 Länder.

**Deutschland.** Nennt Chronopost für ein Paket nach Deutschland eine Paketnummer von DPD, stellt DPD in Deutschland es zu. Peek zeigt diese Nummer dann mit „Zustellung durch DPD Germany“ und fragt damit bei [DPD Germany](carrier:dpd-de) nach. Solange Peek das Paket bei DPD nur angekündigt findet, noch ohne eigenen Scan, zeigt es stattdessen „Bei DPD Germany angekündigt, noch nicht eingetroffen“. Sobald DPD einen datierten Scan zeigt, der mindestens so neu ist wie Chronoposts neuester, verfolgt Peek das Paket bei DPD weiter und behält Chronoposts Verlauf; bei Chronopost fragt Peek noch einen Tag lang weiter nach, höchstens alle 55 Minuten.

**Österreich und Schweiz.** Hier nennt Peek keinen Partner und zeigt keine Partnernummer: Es verfolgt das Paket weiter bei Chronopost.

**Zoll.** Von Frankreich nach Deutschland oder Österreich bleibt ein Paket in der EU, und zwischen EU-Ländern ist laut EU kein Zoll zu entrichten. Die Schweiz gehört nicht zur EU: Dort meldet laut dem Bundesamt für Zoll und Grenzsicherheit (BAZG) die Post oder ein anderer Paketdienst jede Sendung aus dem Ausland beim Zoll an. Mehr in [Paket beim Zoll](guide:customs). Schreibt Chronopost `Problème douanier résolu, colis en cours d'acheminement`, ist ein Problem beim Zoll gelöst und das Paket wieder unterwegs; Peek zeigt „Unterwegs“.

## In Frankreich: Pickup-Relais, Paketautomat und verpasste Zustellung

Bist du nicht da, kann das Paket an einer Abholstelle auf dich warten. In Frankreich sind das bei Chronopost Pickup-Relais (Paketshops) und Pickup-Automaten, Postfilialen und Chronoposts eigene Depots, laut La Poste Groupe 19.555 Stellen. Liegt das Paket an einem Pickup-Punkt bereit, bekommst du eine E-Mail oder SMS mit den Öffnungszeiten und der Frist zum Abholen. Laut Pickup ist diese Frist nie länger als:

- 8 Tage in einem Pickup-Relais;
- 5 Tage in einem Pickup-Automaten.

Danach geht das Paket automatisch an den Absender zurück. Mehr in [Paket nicht abgeholt](guide:uncollected-parcel).

Zum Abholen zeigst du den Pickup Pass (QR-Code) oder die Codes aus E-Mail oder SMS; im Relais geht auch dein Ausweis im Original. Die Automaten bedienst du selbst, manche sind rund um die Uhr zugänglich. Jemand anderes kann das Paket mit deinem Pickup Pass oder deinen Codes abholen, im Relais auch mit den Originalen seines und deines Ausweises. Schriftliche Vollmachten nimmt Pickup nicht an. Mehr in [Packstation und Paketautomat](guide:parcel-lockers).

## Chronopost-Paket hängt fest, kommt nicht an oder ist verloren

**Keine Bewegung oder Verspätung.** Wende dich an den Shop, der Chronopost beauftragt hat. In der EU soll der Händler laut Your Europe innerhalb von 30 Tagen liefern, wenn nichts anderes vereinbart ist; liefert er nicht, setzt du ihm eine angemessene Nachfrist und kannst danach den Vertrag beenden und dein Geld zurückverlangen. Siehe [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating).

**Verloren oder beschädigt.** Reklamier beim Shop: In der EU haftet laut Your Europe der Händler für Schäden an der Ware vom Versand bis zu dem Moment, in dem du sie erhältst. Kommt das Paket beschädigt an, kannst du das auf dem Lieferschein vermerken und solltest dich schnell beim Kundenservice des Shops melden, rät die französische Verwaltung. Im Pickup-Relais meldest du den Schaden dem Ladeninhaber, der ihn erfasst; das Paket kannst du trotzdem mitnehmen oder ablehnen. Mehr in [Paket verloren](guide:lost-parcel) und [Paket beschädigt](guide:damaged-parcel).

**Als zugestellt markiert, aber nicht da.** Melde dich beim Shop. Siehe [Paket zugestellt, aber nicht erhalten](guide:delivered-not-received).

## Chronopost Kundenservice und Kontakt

Chronoposts eigene Kontaktseite liegt auf chronopost.fr. La Poste Groupe, Geopost und Pickup nennen außerdem, Stand 4. November 2026:

- **Léonard**, Chronoposts Chatbot, für Fragen zur Zustellung; schwierige Fälle übernimmt ein Mensch.
- **Telefon** 09 69 39 13 91, eine französische Nummer, laut Pickup etwa, wenn deine Abholcodes nicht angekommen sind.
- Für Shop2Shop-Pakete: chronoshop2shop.fr.

Antwortet Chronopost mindestens zwei Monate nicht auf deine schriftliche, datierte und unterschriebene Beschwerde oder passt dir die Antwort nicht, wende dich binnen eines Jahres nach der Beschwerde an die Schlichtungsstelle der Gruppe, den Médiateur du Groupe La Poste.

> Betrüger verschicken SMS und E-Mails im Namen von Chronopost. Laut der französischen Plattform Cybermalveillance verlangt Chronopost für Zustellungen in Frankreich weder einen Ausweis noch Liefergebühren. Verdächtige E-Mails leitest du an abuse@chronopost.fr weiter. Siehe [Fake-Paket-SMS erkennen](guide:delivery-scam-texts).

## Chronopost Paket verfolgen mit Peek

Füg die Nummer bei [Peek](/) einfach ein. 13-stellige Nummern, die mit PZ, XR, XT, XU, XW oder XY beginnen und eine gültige Prüfziffer haben oder auf JB, JF oder TS enden, erkennt Peek sofort als Chronopost. Stimmt bei diesen Anfangsbuchstaben die Prüfziffer nicht, endet die Nummer auf RV oder VF oder beginnt sie mit XN, fragt Peek nur Chronopost; solange Chronopost sie noch nicht kennt, zeigt Peek „Warten auf den ersten Scan von Chronopost“. Gültige Nummern auf FR mit anderen Anfangsbuchstaben gehen an La Poste.

Bei 15 Zeichen fragt Peek Chronopost zusammen mit den vier DPD-Netzen, die diese Form nutzen. Bei 14 Ziffern, die nicht mit 10 beginnen, fragt Peek mehrere Paketdienste mit dieser Form, darunter Chronopost und DPD. Solange Peek fragt, zeigt es „Tracking-Dienste werden geprüft…“. Kennt Chronopost die Nummer, steht neben dem Namen „hat dieses Paket“. Kennen sie mehrere, nennt Peek sie, und du tippst auf „Wählen“; kennt sie noch keiner, steht da „noch nicht gefunden · Peek prüft weiter“. Willst du selbst wählen, tippst du auf „Ändern“ und wählst Chronopost unter „Passt zu dieser Nummer“ oder „Alle Paketdienste“. Links aus Chronoposts Sendungsverfolgung und von chronoshop2shop.fr kannst du ebenfalls einfügen. Eine Postleitzahl braucht Peek nicht.

Du siehst den Status, den Verlauf mit dem Ort jedes Scans auf der Karte, das Zielland, den Zeitpunkt der Zustellung und, solange das Paket an einer Abholstelle wartet, Name und Adresse des Relais oder Automaten. Einen voraussichtlichen Liefertag zeigt Peek nur, wenn Chronopost einen Termin für die neue Zustellung oder ein Zeitfenster nennt. Deine Adresse und die Kontaktdaten der Abholstelle zeigt Peek nicht. Nennt Chronopost zu einem Scan keine Zeitzone, gilt die Uhrzeit des Orts, an dem er entstand.

Peek schaut alle 10 Minuten nach, in Zustellung ab 6:00 Uhr französischer Zeit (wie in Deutschland, Österreich und der Schweiz) alle 2 Minuten, solange Chronopost selbst antwortet, nach einer fehlgeschlagenen Abfrage nach 15 Minuten; über Tracking-Dienste alle 15 Minuten. Nennt Chronopost ein Zeitfenster, bleibt es bis 30 Minuten vor dessen Beginn bei 10 Minuten, ebenso bei einer Zustellung, die seit einem früheren Tag läuft. Nach zwei Tagen ohne neuen Scan (nach 12 Stunden, solange es „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ zeigt) schaut Peek nur noch stündlich nach. Für Mitteilungen wählst du „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“. Chronopost oder den Shop kontaktieren kann Peek nicht für dich.

## Fragen zur Chronopost Sendungsverfolgung

### Gehört Chronopost zu La Poste?

Ja. Chronopost gehört zu Geopost, und Geopost gehört zur La Poste Groupe.

### Ist Chronopost dasselbe wie DPD?

Nein. Chronopost tritt nicht als DPD auf, aber beide gehören zu Geopost, der Paketsparte von La Poste. Manche Chronopost-Nummern haben die Form von DPD-Paketnummern.

### Wer stellt ein Chronopost-Paket in Deutschland zu?

DPD, wenn Chronopost eine Paketnummer von DPD nennt. Peek zeigt dann „Zustellung durch DPD Germany“ (solange es bei DPD nur angekündigt ist: „Bei DPD Germany angekündigt, noch nicht eingetroffen“) und verfolgt das Paket bei DPD weiter, sobald DPD einen aktuellen Scan zeigt.

### Wie lange bleibt ein Chronopost-Paket im Pickup-Relais?

In Frankreich bis zu 8 Tage, in einem Pickup-Automaten bis zu 5, laut Pickup, das die Relais betreibt. Die genaue Frist steht in der E-Mail oder SMS.

:::sources
- [La Poste Groupe: Chronopost (Englisch)](https://www.lapostegroupe.com/en/focus/la-poste-groupes-subsidiaries/chronopost) – Expressdienst von La Poste, 230 Länder, 19.555 Abholstellen in Frankreich
- [La Poste Groupe: 2025 Integrated Report, PDF (Englisch)](https://www.lapostegroupe.com/assets/media/le-groupe-laposte/ad4Q_p1ZCF7ETKqb_MEL_LAPO_RI_seul_2025_EN_14042026.pdf) – Geopost mit DPD und Chronopost, Léonard, Chronoposts Chatbot (S. 18)
- [Pickup: FAQ (Französisch)](https://www.pickup.fr/faq-particulier/) – Benachrichtigung, Fristen, Rücksendung, Abholung, Automaten, Chronoposts Telefonnummer, Schaden im Relais
- [Your Europe: Versand und Lieferung](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_de.htm) – 30 Tage, Nachfrist, Erstattung, Haftung des Händlers bis zum Erhalt
- [Europäische Union: Die EU-Zollunion in der Praxis](https://european-union.europa.eu/priorities-and-actions/actions-topic/customs_de) – kein Zoll zwischen EU-Ländern
- [BAZG: Postversand, Interneteinkauf, Kurierverkehr](https://www.bazg.admin.ch/de/postversand-interneteinkauf-kurierverkehr-einfuhr-firmen) – Sendungen aus dem Ausland meldet die Post oder das Kurierunternehmen beim Zoll an
- [Service-Public: Achat à distance, livraison du bien (Französisch)](https://www.service-public.gouv.fr/particuliers/vosdroits/F10037) – Vermerk auf dem Lieferschein, schnell den Shop kontaktieren
- [Médiateur du groupe La Poste: Conditions de saisine (Französisch)](https://mediateur.groupelaposte.com/pages/conditions-requises-a-la-saisine-du-mediateur-de-la-consommation-du-groupe-la-poste) – Streit mit Chronopost, zwei Monate, ein Jahr
- [Cybermalveillance.gouv.fr: L'hameçonnage à la livraison de colis (Französisch)](https://www.cybermalveillance.gouv.fr/tous-nos-contenus/actualites/escroqueries-livraison-colis) – kein Ausweis, keine Gebühren in Frankreich, abuse@chronopost.fr
- [Geopost: leboncoin is teaming up with Chronopost (Englisch)](https://www.geopost.com/en/news/leboncoin-is-teaming-up-with-chronopost/) – Chronopost in Geopost, Shop2Shop
- [Geopost: About us (Englisch)](https://www.geopost.com/en/about-us/) – Geopost gehört zur La Poste Groupe
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – was Peek liest, zeigt und an DPD Germany übergibt
:::
