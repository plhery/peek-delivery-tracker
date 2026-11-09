---
title: Fake-Paket-SMS erkennen: echt oder Betrug?
description: Woran du eine Fake-SMS vom Paketdienst erkennst, was DHL, Hermes, DPD und die Post wirklich schicken und was du tust, wenn du den Link geöffnet oder bezahlt hast.
slug: fake-paket-sms-erkennen
picture: Ein Handy mit einer Nachricht mit Warndreieck und einem Angelhaken am Link, eine durchgestrichene Münze und ein besorgter Pip mit einer erhobenen Stopp-Hand in seiner Sprechblase.
published: 2026-10-14
updated: 2026-11-09
---

Schreibt dir jemand per SMS oder E-Mail, dein Paket hänge fest, es fehle noch eine kleine Gebühr oder die Adresse müsse korrigiert werden, und schickt gleich einen Link mit, ist das fast immer Betrug. Tipp nichts an: Öffne die Website oder App des Paketdienstes selbst und verfolge dein Paket mit der Sendungsnummer aus der Versandmail des Shops. Hast du schon Kartendaten eingegeben, ruf sofort deine Bank an oder lass die Karte sperren, in Deutschland auch über den Sperr-Notruf 116 116.

## Wie erkenne ich eine Fake-SMS vom Paketdienst?

Eine Fake-SMS hat meist mehrere dieser Merkmale:

- **Du erwartest gar kein Paket.** Das BSI rät, als Erstes zu überlegen, ob überhaupt eine Sendung unterwegs ist. Bei manchen Absendern ist die Sache sofort klar: „Hermes Germany nutzt keinen SMS-Service“, jede SMS in seinem Namen ist also Betrug (einzige Ausnahme: der [Hermes Einrichtungsservice](carrier:hermes-einrichtungs-service) für Möbel).
- **Sie will Geld für die Zustellung.** DHL fragt per SMS nie nach persönlichen Daten und fordert nie zu Zahlungen auf. Bei Hermes zahlt der Absender den Versand, du als Empfänger nie etwas dazu. Die Österreichische Post verlangt nie deine IBAN und BIC, um ein Paket zuzustellen.
- **Sie macht Druck.** „Dein Paket wird zurückgesendet, wenn Du nicht sofort reagierst“ ist ein Beispiel, das Hermes selbst zitiert. Die Schweizerische Post droht nicht und setzt keine extrem kurzen Fristen, und das österreichische Bundeskriminalamt nennt Zeitdruck mit angedrohten Folgen ein typisches Warnzeichen.
- **Der Link gehört nicht dem Paketdienst.** Typisch sind Kurzlinks wie bit.ly (laut DHL), nachgebaute Adressen wie `post.ch.paysget.info` (Schweizerische Post) oder `dpd.pay3d.shop` (DPD) und gefälschte Seiten wie `postaustria.org` (Österreichische Post). Auch ein `https://` am Anfang ist keine Garantie, warnt die Schweizerische Post.
- **Der Absender passt nicht.** DPD Deutschland verschickt SMS „ausschließlich von der Nummer +49 177 1787807“. DHL-Mails kommen von @dhl.de, @dhl.com, @dpdhl.com oder einer anderen DHL-Länderdomain, nie von Gmail oder Yahoo. Mails der Österreichischen Post enden auf @post.at, die der Schweizerischen Post auf @post.ch oder auf .post.ch, etwa @newsletter.post.ch. Laut DHL zeigen Fake-SMS oft eine unerwartete Ländervorwahl wie +235 oder statt einer Nummer einen Namen wie „Lieferung“.
- **Sprache und Anrede wirken falsch.** Das BSI rät, auf Rechtschreibfehler zu achten, vor allem bei ä, ö und ü. Das Bundeskriminalamt nennt die unpersönliche Anrede „Sehr geehrte Kundin / Sehr geehrter Kunde“. Die Schweizerische Post schreibt auf Deutsch, Französisch oder Italienisch, manchmal auf Englisch.

Ein bekannter Absender beweist allerdings nichts. Die Österreichische Post warnt vor einer Mail „Dein Paket wartet\!“, die scheinbar von meine.post@post.at kommt und eine „Liefergebühr“ verlangt. Auch eine Anrede mit deinem Namen beweist nichts: Die Absender setzen Namen automatisch ein, die etwa aus ausgelesenen Adressbüchern anderer Handys stammen können, erklärt die Verbraucherzentrale. Und eine Sendungsnummer ebenso wenig: Echte DPD-Paketnummern haben 14 Ziffern, die Schweizerische Post zeigt als Beispiel die gefälschte Nummer `34632900-371`. Prüf eine Nummer deshalb nur auf der Seite oder in der App des Paketdienstes.

### So liest du einen Link, bevor du ihn antippst

Betrugslinks beginnen oft mit dem Namen eines Paketdienstes. Entscheidend ist aber, was direkt vor dem ersten einfachen `/` steht: Wem dieser Teil gehört, dem gehört die Adresse. Das erfundene Beispiel nutzt die britische Royal Mail, mit dhl.de oder post.ch funktioniert der Trick genauso:

:::anatomy royalmail.co.uk. redelivery-fee.example /pay
- royalmail.co.uk. | Der Name des Paketdienstes, als Köder vorangestellt. Hier nur Tarnung
- redelivery-fee.example | Die echte Adresse dahinter: nicht Royal Mail
- /pay | Die Seite, die nach deinen Kartendaten fragt
:::

Auch wenn ein Link richtig aussieht: Nutz ihn nicht, sondern tipp die Adresse des Paketdienstes selbst ein.

## Schicken DHL, Hermes, DPD und die Post wirklich SMS?

Die meisten schon, und genau deshalb funktionieren die Fakes. Was die Paketdienste selbst über ihre Nachrichten schreiben:

| Paketdienst | So kontaktiert er dich | Was er laut eigener Seite nie tut |
| --- | --- | --- |
| [DHL](carrier:dhl), Deutsche Post | Mails von @dhl.de, @dhl.com, @dpdhl.com oder anderen DHL-Domains, Links nur auf eigene Seiten | Per SMS nach persönlichen Daten fragen oder zur Zahlung auffordern |
| [Hermes](carrier:hermes-de) | Gar nicht per SMS (außer Einrichtungsservice), Links nur auf myhermes.de und hermesworld.com | Nach Zahlungsdaten fragen, Zollgebühren eintreiben, dich in sozialen Netzwerken zu Paketen anschreiben, dich zum Installieren von Software auffordern |
| GLS Österreich | Mails nur zu geschäftlichen Belangen wie Versandanweisungen oder Lieferstatus, Hauptwebsite gls-group.com | Per Mail Zahlung verlangen, dich von sich aus per Messenger anschreiben |
| [DPD Deutschland](carrier:dpd-de) | SMS nur von +49 177 1787807 | Mit Neosurf, iGift oder Paysafe zusammenarbeiten |
| [Österreichische Post](carrier:austrian-post) | Mails von @post.at, Absender „Österreichische Post AG“ oder „Meine Post“ | IBAN und BIC verlangen, um ein Paket zuzustellen |
| [Schweizerische Post](carrier:swiss-post) | Mails von @post.ch oder Adressen auf .post.ch, Links auf post.ch, poste.ch, posta.ch, swisspost.ch, swisspost.com | Per SMS, E-Mail oder WhatsApp nach Passwörtern, Codes oder vollständigen Kartendaten fragen |

Echte Paketdienste schicken durchaus Links, und es gibt auch echte Zahlungsaufforderungen, fast alle für Einfuhrabgaben: DPD kann bei Paketen von außerhalb der EU vor der Zustellung Zollgebühren verlangen. In der Regel stellt der Paketdienst die Einfuhrabgaben in Deutschland aber erst bei der Zustellung in Rechnung, erklärt die Verbraucherzentrale unter Berufung auf den Zoll. Vorab zahlst du dort nur bei Paketdiensten, die das als Service anbieten und bei denen du dich dafür registriert hast, und ihre Nachrichten nennen dann immer die Sendungsnummer und den Absender. Ein Link oder eine Gebühr allein sagt also wenig. Ob eine Forderung echt ist, zeigt dir die Seite des Paketdienstes. Bist du dir unsicher, frag nach: bei DPD Deutschland unter info@dpd.de, beim Kundenservice der Österreichischen Post unter +43 800 010 100.

## Typische Betrugsmaschen bei der Paketzustellung

Die Maschen wiederholen sich. Diese Beispiele stammen von den Warnseiten der Paketdienste und Behörden:

- **Zustellung verpasst, Gebühr zahlen.** Ein Formular im DPD-Design will eine kleine Gebühr und deine Kartendaten. Bei der Österreichischen Post heißt die angebliche Gebühr „Liefergebühr“.
- **Problem mit der Adresse.** Eine „Gebühr für falsche Lieferadresse“ oder die Bitte, „Informationen ergänzen, Sendung bestätigen“ (Österreichische Post). In SMS, die der Verbraucherzentrale gemeldet wurden, fehlt angeblich die Hausnummer, oder du sollst die Adresse „innerhalb von 12 Stunden“ bestätigen. Wie du eine Adresse wirklich änderst, steht unter [Paket umleiten](guide:change-delivery-address).
- **Paket hängt fest.** „Ihr Paket wird im Sortierzentrum aufbewahrt“ (Bundesnetzagentur), „Paket in Warteschleife“ (Österreichische Post), „Paket im Terminal festgehalten“ (Schweizerische Post).
- **Zollgebühr.** Die Bundesnetzagentur hat vor SMS gewarnt, die eine angebliche „Zollgebühr“ verlangen, die Österreichische Post vor Mails „Zahlung von Einfuhrzoll/Zoll/Steuer erforderlich“. Die Beträge in den Beispielen der Verbraucherzentrale sind klein, zwischen 0,99 und 3,99 Euro. Manche Forderungen sind aber echt: Woran du sie erkennst, steht im Ratgeber [Paket beim Zoll](guide:customs).
- **SMS-Code oder Rückrufnummer.** Die Schweizerische Post nennt Fakes, die dich einen „SMS-Code an 41414 senden“ lassen oder auf 0901-Nummern verweisen.
- **„Mit Y antworten“.** Manche Fakes bitten dich, mit Y zu antworten und den Link danach erneut zu öffnen (Verbraucherzentrale). Antworte nie: Jede Reaktion zeigt, dass deine Nummer genutzt wird.
- **Eine App installieren.** Hinter manchen Links steckt eine nachgebaute DHL-Seite, die dir erklärt, wie du eine „App“ installierst. Solche Apps lesen laut Verbraucherzentrale Daten aus und verschicken massenhaft SMS an deine Kontakte.
- **WhatsApp statt SMS.** DHL warnt vor falschen Konten, die das Gespräch zu WhatsApp oder Telegram verlagern. GLS Österreich schreibt dich nie von sich aus per Messenger an.
- **Zettel mit QR-Code im Briefkasten.** Die Österreichische Post warnt vor einer gefälschten Benachrichtigung im Briefkasten mit QR-Code oder Telefonnummer, die nicht von ihr stammt. Bei der Schweizerischen Post führte der QR-Code einer gefälschten Abholeinladung auf eine Seite, die Geld für eine neue Zustellung wollte. Online zahlst du eine neue Zustellung bei ihr nur nach dem Login in „Meine Sendungen“. Scannst du doch einen Code, prüf die angezeigte Adresse, bevor du die Seite öffnest.
- **„Kurier“ für deinen Kleinanzeigen-Verkauf.** Die Österreichische Post warnt vor einem falschen „Post-Kurier“ und „Post-Käuferschutz“, die Schweizerische Post vor gefälschten Abholaufträgen in Kleinanzeigen.
- **Paket ohne Bestellung an der Tür.** GLS Österreich warnt vor Leuten in Zustellerkleidung, die eine unerwartete Nachnahme bringen, oft „für den Nachbarn“, mit wertlosem Inhalt. Zahl keine Nachnahme, die du nicht erwartest.

## Paket-SMS echt oder Fake? So prüfst du sie sicher

Fünf Schritte, und für keinen brauchst du die Nachricht:

:::steps
- Überleg, ob du etwas erwartest | Keine Bestellung, niemand, der dir etwas schickt? Dann behandle die Nachricht als Betrug.
- Hol dir die echte Sendungsnummer | Aus der Versandmail oder dem Kundenkonto des Shops, nie die Nummer aus der SMS.
- Öffne den Paketdienst selbst | Tipp seine Adresse ein oder öffne seine App. Nichts aus der Nachricht antippen, scannen oder anrufen.
- Schau, ob wirklich etwas fällig ist | Eine echte Gebühr siehst du auch auf der Seite oder in der App des Paketdienstes.
- Melden, dann löschen | In Deutschland bei der Bundesnetzagentur, in Österreich bei der Watchlist Internet, in der Schweiz beim BACS (mehr unten).
:::

Keine Nummer zur Hand? Lies, [wo du die Sendungsnummer findest](guide:find-tracking-number). Du kannst sie auch in [Peek](/) einfügen: Peek fragt den Paketdienst und, wenn nötig, andere Quellen, und du siehst die neuesten Scans, ohne den Link anzufassen. Bezahlen kannst du dort nichts: Was wirklich fällig ist, zahlst du beim Paketdienst.

Eine echte verpasste Zustellung hinterlässt meist eine Benachrichtigung. Wie es danach weitergeht, steht unter [Paket verpasst](guide:missed-delivery).

## Fake-SMS geöffnet oder Link angeklickt: was tun?

Das hängt davon ab, was du auf der Seite gemacht hast:

:::steps
- Seite schließen | Gib nichts mehr ein. Antworte nicht, auch nicht mit „STOP“.
- Bank anrufen oder Karte sperren | Über die Nummer auf der Karte oder in der Banking-App, in Deutschland auch über den Sperr-Notruf 116 116.
- Passwörter ändern | Überall, wo du das eingegebene Passwort benutzt hast.
- Anrufern nicht trauen | Ruft jemand an, auch angeblich von der Bank oder vom Paketdienst? Leg auf und ruf unter einer Nummer zurück, die du selbst nachgeschlagen hast.
- Anzeige erstatten | Bei der Polizei, wenn du Geld oder Daten verloren hast.
:::

**Link nur geöffnet, nichts eingegeben?** Dann gib nichts mehr ein, lade keine App und keine Datei herunter und schließ die Seite, rät die Schweizerische Post. Apps der Post installierst du nur aus dem App Store oder von Google Play, nie über einen Link aus einer Nachricht. Melde die SMS trotzdem.

**Eine App installiert?** Schalte das Handy in den Flugmodus, damit die App keine Daten mehr senden kann. Mach Screenshots und erstatte Anzeige, oder geh mit dem Handy zur Polizei, damit dort Beweise gesichert werden. Starte es dann im abgesicherten Modus neu und lösche unbekannte, kürzlich installierte Apps; im schlimmsten Fall hilft nur das Zurücksetzen auf Werkseinstellungen. Sag auch deinem Mobilfunkanbieter Bescheid und lass dir die Kosten möglicherweise verschickter SMS auflisten. So rät es die Verbraucherzentrale; die Schweizerische Post empfiehlt, das Gerät mit aktueller Sicherheitssoftware oder einem Online-Check wie ibarry.ch zu prüfen. Auf Android verhinderst du solche Installationen, wenn du in den Einstellungen unter **Unbekannte Apps installieren** nichts erlaubst. Auf dem iPhone lassen sich Apps ohnehin nur aus dem App Store und aus Stores mit vorab geprüften Apps installieren.

**Kartendaten eingegeben oder bezahlt?** Lass Onlinebanking, Konto und Karten „unverzüglich“ sperren, rät das österreichische Bundeskriminalamt, und beobachte deine Kontobewegungen. Der deutsche Sperr-Notruf 116 116 ist rund um die Uhr erreichbar, aus dem deutschen Festnetz kostenlos (aus dem Mobilfunknetz können Gebühren anfallen) und aus dem Ausland unter +49 116 116 oder +49 30 4050 4050. Jede girocard lässt sich darüber sperren, eine Kreditkarte nur, wenn ihr Herausgeber mitmacht: Sonst nimm die Nummer auf deiner Karte oder auf der Website deiner Bank. Hast du per Kreditkarte bezahlt, frag deine Bank, ob du das Geld per Chargeback zurückholen kannst, rät die Verbraucherzentrale. Hast du ein Passwort eingegeben, ändere es sofort, rät die Schweizerische Post.

Auch eine winzige Gebühr ist nicht harmlos: Laut DPD schließt du über solche Kartenformulare unbemerkt einen Vertrag mit Betrügern über weitere Zahlungen ab. Prüf also auch in den nächsten Wochen deine Kontoauszüge und Kartenabrechnungen, dazu deine Handyrechnung: Auf dem Smartphone können über solche Seiten unbemerkt teure Abos bei Drittanbietern entstehen, warnt die Bundesnetzagentur. Die Verbraucherzentrale rät, bei deinem Mobilfunkanbieter eine Drittanbietersperre einzurichten.

## Fake-Paket-SMS melden: wo und wie

Melde eine Fake-SMS, auch wenn du nicht geklickt hast, und lösche sie dann.

| Land | Nachricht melden | Geld oder Daten verloren |
| --- | --- | --- |
| Deutschland | Bundesnetzagentur, Formular „SMS oder Messenger“; Fakes im Namen von DHL als Screenshot mit Absendernummer an phishing@dhl.com, Hermes an info-paket@myhermes.de; verdächtige Mails an phishing@verbraucherzentrale.nrw | Bank oder Sperr-Notruf 116 116, dann Strafanzeige bei der Polizei oder online bei der Onlinewache deines Bundeslandes |
| Österreich | Watchlist Internet, Formular „Internetfalle melden“; gefälschte Post-Seiten über das Formular der Post; Fakes im Namen von GLS als Screenshot mit Namen und Nummer des Absenders an security@gls-group.com; against-cybercrime@bmi.gv.at | Bank, dann Anzeige bei der Polizei (die Meldung an against-cybercrime ersetzt sie nicht) |
| Schweiz | Mails, wenn möglich als Anhang, an reports@antiphishing.ch weiterleiten oder Meldeformular des Bundesamts für Cybersicherheit (BACS) | Anzeige bei der Kantonspolizei, in manchen Kantonen online über Suisse ePolice (das BACS nimmt keine Strafanzeigen entgegen) |

Für das Formular der Bundesnetzagentur brauchst du die genaue Absendernummer oder den angezeigten Absendernamen, den vollständigen Text und das Datum, und ein Screenshot erhöht die Beweiskraft deutlich: Lösch die SMS also erst danach. Die Bundesnetzagentur kann Absendernummern abschalten lassen, aber keine Bußgelder für SMS-Spam verhängen. Das BACS gibt dir nach ein paar Fragen eine automatische Ersteinschätzung, bearbeitet Meldungen aber nicht rund um die Uhr: Im Notfall wählst du 112 oder 117.

Auf dem iPhone steht unter Nachrichten von unbekannten Absendern **Spam melden**: Tipp darauf und dann auf **Spam löschen und melden**. Hast du schon geantwortet, geht das nicht mehr. Melden blockiert den Absender nicht: Dafür tippst du oben in der Unterhaltung auf sein Symbol, dann auf **Info** und **Kontakt blockieren**. Unter **Filter verwalten** in der Konversationsliste kannst du **Unbekannte Absender:innen überprüfen** einschalten, dann landen Nachrichten fremder Nummern ohne Mitteilung in einem eigenen Ordner. In Google Messages auf Android öffnest du die Unterhaltung und wählst im Dreipunkt-Menü unter **Details** die Option **Blockieren und als Spam melden**, die den Absender zugleich blockiert. Einzelne Nummern zu sperren hilft allerdings wenig, denn die Absender wechseln sie ständig, schreibt die Verbraucherzentrale. Viele Nachrichten-Apps lassen sich auch so einstellen, dass nur SMS von gespeicherten Kontakten ankommen; Nummern für Terminerinnerungen oder Banking-Infos musst du dann speichern.

:::sources
- [DHL: Sicherheitshinweise](https://www.dhl.de/de/toolbar/footer/sicherheitshinweise.html) – Absenderadressen, keine Zahlung per SMS, bit.ly, Ländervorwahl +235, WhatsApp, phishing@dhl.com
- [Hermes: Sicherheitshinweise](https://www.myhermes.de/sicherheit/) – kein SMS-Service, keine Zusatzkosten, keine Software-Installation, Druck-Beispiel, info-paket@myhermes.de
- [GLS Österreich: Sicherheitshinweis](https://gls-group.com/AT/de/press/gls-informs/security-advice/) – Mails nur zu geschäftlichen Belangen wie Versandanweisungen oder Lieferstatus, keine Zahlung per Mail, kein Messenger von sich aus, gls-group.com, Nachnahme an der Haustür, security@gls-group.com
- [DPD: Sicherheit](https://www.dpd.com/de/de/sicherheit/) – SMS-Nummer, 14 Ziffern, Gebührenformular mit Folgezahlungen, Neosurf, iGift, Paysafe, Zoll außerhalb der EU, info@dpd.de
- [Bundesnetzagentur: Archiv Hinweise und Meldungen](https://bundesnetzagentur.de/DE/Vportal/TK/Aerger/Aktuelles/Hinweise/start.html) – Warnung vor Fake-„Zollgebühr“-Nachrichten
- [Bundesnetzagentur: Nicht verlangte SMS](https://www.bundesnetzagentur.de/DE/Vportal/TK/Aerger/Faelle/SMSSpam/start.html) – Fake-Texte, Drittanbieter-Abos, Beschwerdeformular, Abschaltung, keine Bußgelder
- [Bundesnetzagentur: Beschwerdeformular „SMS oder Messenger“](https://www.bundesnetzagentur.de/_tools/_forms/01_Telekommunikation/RumitelStart/Form02SMS/node.html) – Absendernummer, Text, Datum, Bildschirmfoto
- [BSI: Passwortdiebstahl durch Phishing](https://www.bsi.bund.de/DE/Themen/Verbraucherinnen-und-Verbraucher/Cyber-Sicherheitslage/Methoden-der-Cyber-Kriminalitaet/Spam-Phishing-Co/Passwortdiebstahl-durch-Phishing/passwortdiebstahl-durch-phishing_node.html) – Paket erwartet?, Umlaute, Anzeige
- [Polizei: Onlinewachen](https://www.polizei.de/Polizei/DE/Einrichtungen/Onlinewache/onlinewache_node.html) – Strafanzeige online oder bei der Polizei
- [Verbraucherzentrale: Phishing-Radar](https://www.verbraucherzentrale.de/wissen/digitale-welt/phishingradar/phishingradar-aktuelle-warnungen-6059) – phishing@verbraucherzentrale.nrw
- [Verbraucherzentrale: Paketdienst-SMS](https://www.verbraucherzentrale.de/wissen/digitale-welt/mobilfunk-und-festnetz/paketdienstsms-vorsicht-abzocke-58988) – Namen in der Anrede, Beispiele (Hausnummer, 12 Stunden, „mit Y antworten“, Zollbeträge), Einfuhrabgaben bei Zustellung, App-Installation und was danach zu tun ist, Chargeback, Drittanbietersperre, Nummern sperren, nur Kontakte zulassen
- [Sperr-Notruf 116 116](https://www.sperr-notruf.de/) – rund um die Uhr, Kosten, Ausland
- [Sperr-Notruf: Anbieter](https://www.sperr-notruf.de/anbieter) – alle girocards, Kreditkarten nur bei teilnehmenden Herausgebern
- [Österreichische Post: Phishing- und Betrugsversuche](https://www.post.at/i/c/gefahren-im-internet) – @post.at, keine IBAN, aktuelle Fakes, Einfuhrzoll-Mails, Meldeformular, Kundenservice
- [Bundeskriminalamt Österreich: Infoblatt Phishing/Smishing](https://www.bundeskriminalamt.at/212/service/files/294_2025_infoblatt_phishingsmishing_a4_v20251010_bf.pdf) – Warnzeichen, unverzüglich sperren, Anzeige, against-cybercrime@bmi.gv.at
- [Watchlist Internet: Internetfalle melden](https://www.watchlist-internet.at/melde-formular/) – Meldung in Österreich
- [Schweizerische Post: Phishing und Betrug](https://site.post.ch/de/ueber-uns/sicherheit/phishing-und-betrug) – Seite schließen, Passwörter ändern, Zahlungsmittel sperren
- [Schweizerische Post: Phishing erkennen](https://site.post.ch/de/ueber-uns/sicherheit/phishing-und-betrug/phishing-erkennen) – offizielle Adressen und Mail-Absender, https keine Garantie, Adresse hinter QR-Code prüfen, Sprachen, keine Passwörter, Apps nur aus den Stores
- [Schweizerische Post: Aktuelle Betrugsversuche](https://site.post.ch/de/ueber-uns/sicherheit/phishing-und-betrug/aktuelle-betrugsversuche) – 41414, 0901, falsche Sendungsnummer, Abholaufträge, Abholeinladung mit QR-Code, „Meine Sendungen“
- [Schweizerische Post: Betrugsversuch melden](https://site.post.ch/de/ueber-uns/sicherheit/phishing-und-betrug/phishing-melden) – keine App oder Datei laden, Gerät prüfen, reports@antiphishing.ch als Anhang, Kantonspolizei, Suisse ePolice
- [BACS: Melden Sie an das BACS](https://www.bacs.admin.ch/de/melden-sie-an-das-bacs) – Meldeformular, Ersteinschätzung, nicht rund um die Uhr, 112 oder 117, keine Strafanzeigen
- [Apple: Spam melden und Absender:innen blockieren](https://support.apple.com/de-de/guide/iphone/iph3f94d910d/ios) – Spam melden, Spam löschen und melden, Kontakt blockieren
- [Apple: Textnachrichten prüfen und filtern](https://support.apple.com/de-de/guide/iphone/iph203ab0be4/ios) – Filter verwalten, Unbekannte Absender:innen überprüfen
- [Google: Spam in Google Messages melden](https://support.google.com/messages/answer/9061432?hl=de&co=GENIE.Platform%3DAndroid) – Blockieren und als Spam melden, blockiert zugleich
:::
