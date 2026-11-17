---
title: CTT Portugal Sendungsverfolgung: Status, Abholung und Zoll
description: CTT-Paket aus oder in Portugal verfolgen: PT-Sendungsnummern, portugiesische Statusmeldungen auf Deutsch, Abholfristen, Zollgebühren und Kontakt.
slug: ctt-portugal-sendungsverfolgung
published: 2026-11-17
updated: 2026-11-17
---

CTT (CTT – Correios de Portugal) ist die nationale Post Portugals mit ihrem portugiesischen Expresszweig CTT Expresso. Sendungen lassen sich über „Seguir objeto“ auf [ctt.pt](https://www.ctt.pt/feapl_2/app/open/objectSearch/objectSearch.jspx?request_locale=pt) (auf Portugiesisch und Englisch verfügbar), in der App CTT oder direkt über das Eingabefeld oben verfolgen. Die Sendungsnummer erhältst du vom Absender, per SMS oder E-Mail von CTT oder auf einer Benachrichtigungskarte im Briefkasten.

Wichtig zur Unterscheidung innerhalb derselben Unternehmensgruppe: Briefpost und Pakete von CTT Expresso laufen über `ctt.pt`, während [CTT Express](carrier:ctt-express) – die spanische Expresspaket-Tochter, früher Tourline Express – ein eigenes spanischsprachiges Tracking auf `cttexpress.com` betreibt.

## CTT-Sendungsnummer: Aufbau und Beispiele

Die meisten Sendungen auf `ctt.pt` tragen einen 13-stelligen internationalen Postcode: zwei Buchstaben für das Produkt, neun Ziffern und zwei Buchstaben für das Land, in dem der Code erzeugt wurde (`PT` für Portugal – bei Vertragskunden von CTT teils auch dann, wenn die Ware aus Spanien oder China startet). Iberische Expresspakete nutzen dagegen 22- oder 25-stellige Zahlencodes.

| Format (erfunden) | Bedeutung |
| --- | --- |
| `RR482910576PT` | Post- oder CTT-Expresso-Code aus Portugal (`RR`, `CA`, `DA`, `EA` usw.). Die neunte Ziffer ist eine Prüfziffer. |
| `RR482910576DE` | Gleiches Muster einer anderen Landespost – hier [DHL](carrier:dhl) / Deutsche Post (`AT` bei der [Österreichischen Post](carrier:austrian-post), `CH` bei der [Schweizerischen Post](carrier:swiss-post)). |
| `0012340012345678901234` | 22 Ziffern (`00`, vier Ziffern, `00` und 14 Ziffern): iberisches Paket von [CTT Express](carrier:ctt-express), auch auf `ctt.pt` abfragbar. |
| `0012340012345678901234001` | 25 Ziffern: dieselben 22 Ziffern gefolgt von `001`. |

Gib die Nummer ohne Leerzeichen ein: Laut der englischen CTT-Hilfe bedeutet `Item not found`, dass die Sendung im CTT-Netz nicht erkannt wurde (oder der Code nur für Zollzwecke dient). Geht ein Postpaket mit `PT` nach Deutschland, Österreich oder in die Schweiz, übernimmt dort [DHL](carrier:dhl), die [Österreichische Post](carrier:austrian-post) oder die [Schweizerische Post](carrier:swiss-post) mit derselben Nummer die Zustellung. Gar keine SMS oder E-Mail mit Nummer erhalten? Dann reist die Sendung vermutlich ohne Sendungsverfolgung; mehr dazu unter [Sendungsnummer finden](guide:find-tracking-number).

## Was die CTT-Statusmeldungen bedeuten

„Seguir objeto“ gibt es auf Portugiesisch und Englisch. Diese portugiesischen und internationalen Meldungen tauchen am häufigsten auf:

| CTT-Status | Was das für dein Paket heißt |
| --- | --- |
| `Aguarda entrada nos CTT` | CTT hat die elektronischen Sendungsdaten erhalten, das Paket aber noch nicht übernommen. |
| `Aceite` | Bei CTT oder der Aufgabepost eingeliefert – der Transport beginnt. |
| `Em exportação` | Zollabfertigung im Ursprungsland vor dem Abgang ins Ausland. |
| `International dispatch` | Tag, an dem die Sendung das Ursprungsland verlassen hat. |
| `International reception` | Tag, an dem die Sendung im Zielland eingetroffen ist. |
| `Em importação` | Zollprüfung im Bestimmungsland. |
| `Em trânsito` | Unterwegs zwischen Verteil- oder Logistikzentren; erscheint oft mehrfach. |
| `Em entrega` | Heute mit der Zustellerin oder dem Zusteller auf Tour. |
| `Entrega não conseguida` | Zustellversuch fehlgeschlagen; achte auf Karte im Briefkasten, SMS oder E-Mail. |
| `No ponto de entrega` | Liegt zur Abholung in einer Abholstelle oder Paketstation bereit. |
| `Em espera` | Wartet auf Anweisungen oder auf einen vereinbarten Liefertag. |
| `Cancelado` | Der Versand wurde storniert; keine Zustellung. |
| `Entregue` | Zugestellt. |
| `Devolvido` | An den Absender zurückgeschickt. |

Auch `Não entregue` steht für einen erfolglosen Zustellversuch. Bei Einfuhren nach Portugal aus Nicht-EU-Ländern erscheinen im Zollprozess außerdem Meldungen wie `O envio foi selecionado para apresentação à Alfândega.` oder `Foi enviada notificação ao destinatário. O envio está em desalfandegamento.`.

## Nicht angetroffen: Abholstellen, Fristen und Locky-Stationen

**In Deutschland, Österreich oder der Schweiz.** Außerhalb Portugals legt laut CTT allein der Zustelldienst des Ziellandes Abholort und Lagerfrist fest – also [DHL](carrier:dhl) in Deutschland, die [Österreichische Post](carrier:austrian-post) in Österreich oder die [Schweizerische Post](carrier:swiss-post) in der Schweiz.

**In Portugal.** Trifft CTT niemanden an (eine Übergabe an Minderjährige ist ausgeschlossen), richtet sich das weitere Vorgehen nach dem gebuchten Tarif und den Absendervorgaben: Weiterleitung an eine CTT-Stelle (Loja CTT, Ponto CTT, Cacifo-Locky-Paketstation oder Payshop-Agentur), neuer Zustellversuch, Warten im Logistikzentrum oder sofortige Rücksendung. Eine *Encomenda Postal* ohne Hauszustellung sowie eine *Encomenda Expresso Em Dois Dias* ohne Wunschadresse gehen direkt an eine Abholstelle. Die Abholfrist zählt in Werktagen ab dem Werktag nach einer Papierbenachrichtigung (SMS und E-Mail kommen erst bei Abholbereitschaft samt Ablaufdatum):

| Sendungsart in Portugal | Abholfrist (Werktage) |
| --- | --- |
| Briefe und Einschreiben (*Correio Registado*) | 6 (10 in einer Loja CTT für die *Cartão de Cidadão*) |
| Nationales Postpaket (*Encomenda Postal Nacional*) | 10 |
| Internationales Postpaket (`CA` bis `CZ`) | 15 |
| CTT-Expresso-Paket (`DA` bis `EV`, `EX`, `EE`) | 5 (10 bei `CE`, `EP`, `EH`, `ES` sowie Expresso aus Spanien) |

Bei einer *Encomenda Postal* lässt sich die Frist auf bis zu 30 Tage verlängern, bei *Encomenda Expresso* mit Freigabe des Absenders um bis zu 5 Werktage. Bringe die Benachrichtigung (oder die Sendungsnummer) und einen gültigen Ausweis mit: Personalausweis (*Bilhete de Identidade* / EU-*Cartão de Cidadão*), Reisepass, Aufenthaltstitel oder Führerschein mit vollem Namen, Foto und Unterschrift.

**Locky-Paketstationen:** Sie nehmen CTT-Expresso- und Nacex-Pakete auf (keine Briefe, *Encomenda Postal* oder Nachnahme) und lagern sie **5 Tage**. Den PIN-Code schickt CTT per SMS oder E-Mail; nachdem das Tracking die Ankunft am Standort meldet, kann es bis zu 48 Stunden dauern, bis das Fach abholbereit ist, und bis zu weitere 24 Stunden bis zum Eintreffen des PINs.

**Expresso-Zustellung ändern:** Nur Codes mit Anfangsbuchstabe `D` (*Para Amanhã* und *Em Dois Dias*) lassen sich über „Seguir objeto“ oder die App CTT kostenpflichtig ändern (z. B. neues Lieferdatum bis maximal 5 Werktage später für 5,01 € zzgl. MwSt.; Prüfung binnen 2 Werktagen) – und nur solange das Paket noch nicht an einer Abholstelle bereitliegt. Bei [CTT Express](carrier:ctt-express) gibt es montags bis freitags von 9:00 bis 19:00 Uhr bis zu zwei Zustellversuche, 10 Tage Lagerfrist im Collectt-Express-Punkt und 5 Tage im Locky-Fach.

## Paket hängt fest, liegt beim Zoll oder geht zurück

CTT weist darauf hin, dass manche Zielländer internationale Scans verspätet oder gar nicht übermitteln. Bewegt sich tagelang nichts, wende dich an den Absender und lies unseren Ratgeber [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating).

**Zoll.** Zwischen Portugal und Deutschland oder Österreich fallen keine Zollformalitäten an (EU-Zollunion). Bei Lieferungen in die **Schweiz** erhebt das Bundesamt für Zoll und Grenzsicherheit (BAZG) die Schweizer Mehrwertsteuer (8,1 % bzw. 2,6 % ermäßigter Satz etwa für Bücher oder Lebensmittel), verzichtet aber auf Steuerbeträge bis **5 CHF** – das entspricht einer Bemessungsgrundlage von **62 CHF** bei 8,1 % oder **193 CHF** bei 2,6 %; private Geschenksendungen sind bis **100 CHF** Warenwert abgabenfrei (ohne Alkohol und Tabak).

Kommt eine Bestellung von **außerhalb der EU nach Portugal**, gilt in der EU seit dem 1. Juli 2026 bei Privatkäufen bis 150 € ein pauschaler Zoll von **3 €** (in Portugal zzgl. MwSt.) pro Warenart, und seit dem 1. November 2026 zusätzlich eine EU-Zollbearbeitungsgebühr von **2 €** (zzgl. MwSt.) pro Warenart unabhängig vom Warenwert (Sendungen bis 150 € ohne vorgeschriebene Produktkennungen des Händlers können zurückgeschickt werden). Wurden Abgaben und MwSt. nicht schon beim Checkout bezahlt, fällt im CTT-Zollportal neben der Einfuhrumsatzsteuer das CTT-Abfertigungsentgelt an: **7 € (+ MwSt.)** bis 150 € Warenwert bzw. **14 €** (plus Wertzölle) über 150 € bis 1.000 €. Ab Ankunft in Portugal bleiben **20 Tage** Zeit für die Verzollung, danach geht die Sendung unwiderruflich zurück; mehr unter [Paket hängt beim Zoll](guide:customs).

**Reklamationen und Rücksendungen.** Postreklamationen nimmt CTT über das Kontaktformular, telefonisch oder in einer Loja bzw. einem Ponto CTT innerhalb von **einem Jahr** (national) oder **sechs Monaten** (international) ab Einlieferung entgegen. Nach den Allgemeinen Geschäftsbedingungen von CTT Expresso muss der Auftraggeber Schäden binnen **8 Tagen** nach Zustellung (mit Originalverpackung oder Fotos) und Verlust oder Verspätung binnen **30 Tagen** schriftlich melden. Laufende Rücksendungen lassen sich nicht stoppen. Pakete, die weder zugestellt noch zurückgegeben werden können, landen im *Refugo Postal*: Post- und Expresso-Pakete lassen sich dort über den Kundendienst zwischen **20 Tagen und 3 Monaten** (bis 5 Tage vor der Versteigerung) gegen Gebühr zurückfordern.

## CTT Kundenservice und Telefonnummer

Offizielle Kontaktwege laut CTT (geprüft am 17. November 2026):

- **Telefon CTT (Privatkunden):** `+351 21 047 16 16` aus dem Ausland (zum Tarif eines Auslandsgesprächs nach Portugal; innerhalb Portugals `21 047 16 16` zum Festnetztarif), werktags von 8:30 bis 19:30 Uhr. Geschäftskunden: `+351 21 244 00 35` aus dem Ausland (`707 200 115` in Portugal) zu denselben Zeiten. Eine deutsch- oder englischsprachige Hotline nennt CTT nicht.
- **Kontaktformular:** auf [ctt.pt](https://www.ctt.pt/ajuda/particulares/apoio-ao-cliente/contactos/formulario-de-contacto), Rückmeldung montags bis freitags von 8:30 bis 19:30 Uhr, oder persönlich in jeder Loja bzw. jedem Ponto CTT in Portugal.
- **CTT Express (Spanien):** `900 841 352` (innerhalb Spaniens kostenlos), `+34 916 602 200` aus dem Ausland oder `ccaweb@cttexpress.com`, Montag bis Freitag 9:00 bis 19:00 Uhr (außer an Feiertagen).

> CTT warnt vor gefälschten SMS, E-Mails und WhatsApp-Nachrichten über angeblich offene Zollgebühren, fehlende Hausnummern oder gestoppte Lieferungen sowie vor Nachahmer-Domains wie `ctt-pt.org` oder `cttportugues.com`. Echte CTT-Links beginnen mit `www.ctt.pt`.

## CTT-Pakete mit Peek verfolgen

[Peek](/) erkennt portugiesische `PT`-Sendungsnummern mit gültiger Prüfziffer sowie `ctt.pt`-Links mit `objects=` sofort als CTT. Ist eine Ziffer vertauscht (etwa `RR482910575PT`), schlägt die Prüfziffer fehl und Peek ordnet keinen Paketdienst zu – prüfe dann die Nummer oder tippe auf „Ändern“. Andere CTT-Referenzen außerhalb des internationalen Postformats liest Peek über universelle Tracking-Dienste; 22- und 25-stellige Nummern gehen an [CTT Express](carrier:ctt-express).

Peek zeigt den aktuellen Status, bis zu 20 neueste Scans mit grober Ortsangabe (Zentrum oder Abholpunkt) auf der Karte sowie den numerischen CTT-Statuscode; ein voraussichtliches Lieferdatum liefert CTT nicht. Portugiesische Scans liest Peek in Lissabon-Zeit (bzw. Azoren-Zeit ab Postleitzahl `9500`) und spanische CTT-Express-Stationen in Madrider oder Kanaren-Zeit, umgerechnet in deine Gerätezeitzone (auf peektracker.com und in der iPhone-App steht unter dem Verlauf: „Uhrzeiten in deiner Zeitzone: …“). Reist ein `PT`-Paket nach Deutschland, Österreich oder in die Schweiz, fragt Peek zusätzlich [DHL](carrier:dhl), die [Österreichische Post](carrier:austrian-post) oder die [Schweizerische Post](carrier:swiss-post) ab: Solange dort nur die Vorankündigung ohne eigenen Scan vorliegt, steht auf der Karte z. B. „Bei DHL angekündigt, noch nicht eingetroffen“; ab einem mindestens ebenso aktuellen Scan wechselt die Anzeige auf „Zustellung durch DHL“ und behält den CTT-Verlauf bei (umgekehrt „CTT Portugal“ bei Paketen nach Portugal).

Peek prüft CTT bis zu alle 10 Minuten – am Zustelltag ab 6:00 Uhr Ortszeit bis zu alle 2 Minuten, solange das Paket „In Zustellung“ ist und CTTs eigener Tracker antwortet (über Tracking-Dienste alle 15 Minuten) – sowie stündlich nach 48 Stunden ohne Änderung oder nach 12 Stunden auf „Etikett erstellt“ bzw. „Beim Paketdienst eingegangen“. Benachrichtigungen lassen sich auf „Alle Updates“, „Nur wichtige“ oder „Nur am Liefertag“ stellen.

## Häufige Fragen zur CTT-Sendungsverfolgung

### Wo ist mein CTT-Paket gerade?

Die oberste Zeile in „Seguir objeto“ oder in Peek verrät es: `Em entrega` bedeutet Zustellung heute, `No ponto de entrega` Abholbereitschaft. Bei einem `PT`-Paket nach Deutschland, Österreich oder in die Schweiz zeigt nach dem Grenzübertritt [DHL](carrier:dhl), die [Österreichische Post](carrier:austrian-post) oder die [Schweizerische Post](carrier:swiss-post) die lokale Zustellung.

### Ist CTT dasselbe wie CTT Express?

Beide gehören zur CTT-Gruppe, werden aber getrennt verfolgt: CTT betreibt den portugiesischen Postdienst und CTT Expresso auf `ctt.pt`, während [CTT Express](carrier:ctt-express) als spanischer Expressdienst in Spanien und Portugal über `cttexpress.com` (auf Spanisch) läuft.

### Was ist ein CTT-Abholpunkt?

In Portugal eine *Loja CTT* (Postfiliale), ein *Ponto CTT* (Partnershop), eine *Cacifo-Locky*-Paketstation oder ein *Payshop*-Agent. Außerhalb Portugals übernimmt das Filial- und Paketstationsnetz der jeweiligen Landespost.

### Spricht der CTT-Kundenservice Deutsch oder Englisch?

CTT führt weder eine deutsch- noch eine englischsprachige Telefonlinie auf. Unter `+351 21 047 16 16` erreichst du CTT werktags von 8:30 bis 19:30 Uhr zum Auslandstarif nach Portugal; schriftliche Anfragen nimmt das Formular auf `ctt.pt` montags bis freitags zu denselben Zeiten entgegen.

:::sources
- [CTT: Results in Seguir objeto (Englisch)](https://www.ctt.pt/ajuda/particulares/seguir-ou-alterar-entrega/seguir/resultados-que-podem-aparecer-na-pesquisa-do-seguir-objeto?language_id=1) – englische Statusmeldungen und `Item not found`
- [CTT: Resultados do Seguir objeto (Portugiesisch)](https://www.ctt.pt/ajuda/particulares/seguir-ou-alterar-entrega/seguir/resultados-que-podem-aparecer-na-pesquisa-do-seguir-objeto) – Bedeutung der portugiesischen Statusmeldungen
- [CTT: Seguir objeto](https://www.ctt.pt/feapl_2/app/open/objectSearch/objectSearch.jspx?request_locale=pt) – Sendungsverfolgung auf Portugiesisch und Englisch
- [CTT: Como seguir uma entrega](https://www.ctt.pt/ajuda/particulares/seguir-ou-alterar-entrega/seguir/como-seguir-uma-entrega) – Web, App CTT und internationale Sendungen
- [CTT: Encontrar o código de envio](https://www.ctt.pt/ajuda/empresas/seguir-ou-alterar-entrega/seguir/encontrar-o-codigo-de-envio) – `PT`-Codes und 22-/25-stellige Nummern
- [CTT: Não tenho código de envio](https://www.ctt.pt/ajuda/particulares/seguir-ou-alterar-entrega/alterar/a-entrega-vem-a-caminho-mas-nao-tenho-codigo-de-envio-o-que-posso-fazer) – Sendungen ohne Tracking-Code
- [CTT: Entrega não conseguida](https://www.ctt.pt/ajuda/particulares/receber/problemas-na-entrega/entrega-nao-conseguida) – Ablauf nach Fehlversuch und nicht stornierbare Rücksendung
- [CTT: O que precisa de levar consigo](https://www.ctt.pt/ajuda/particulares/receber/levantar-correspondencia-ou-encomendas-/o-que-precisa-de-levar-consigo) – anerkannte Ausweisdokumente
- [CTT: Cacifos Locky](https://www.ctt.pt/ajuda/particulares/receber/cacifos-locky/como-levantar-uma-encomenda) – 5 Tage Frist, PIN und bis zu 48 Stunden Bereitstellung
- [CTT: Prazo para levantar](https://www.ctt.pt/ajuda/particulares/receber/levantar-correspondencia-ou-encomendas-/prazo-para-levantar) – Abholfristen in Werktagen und Regeln außerhalb Portugals
- [CTT: Alterar uma entrega Expresso](https://www.ctt.pt/particulares/receber/seguir-ou-alterar-uma-entrega/alterar-uma-entrega-expresso) – `D`-Sendungen und Änderungsgebühren (5,01 € + MwSt.)
- [CTT: Serviços cobrados](https://www.ctt.pt/ajuda/particulares/importacoes-e-desalfandegamento/taxas-e-servicos-cobrados/servicos-cobrados) – CTT-Verzollungsentgelte (7 € + MwSt. und 14 €)
- [CTT: Fim da isenção de direitos aduaneiros até 150 €](https://www.ctt.pt/particulares/receber/desalfandegar/fim-da-isencao-de-direitos-aduaneiros-em-compras-ate-150-euros-fora-da-uniao-europeia) – 3 € Zoll + MwSt. und 2 € Bearbeitungsgebühr + MwSt. pro Warenart (abgerufen am 17. November 2026)
- [Europäische Kommission: EU-Zollreform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_de) – 3 € Zoll seit 1. Juli 2026 und 2 € Bearbeitungsgebühr seit 1. November 2026
- [Europäische Kommission: Guidance on the €3 customs duty and Union handling fee (PDF, Englisch)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – Erhebung pro Warenart unabhängig vom Warenwert
- [BAZG: Empfangen von Briefen und Paketen](https://www.bazg.admin.ch/de/empfangen-von-briefen-und-paketen) – Schweizer MWST (8,1 % / 2,6 %) und Abgabenfreigrenze bis 5 CHF (62 CHF / 193 CHF)
- [BAZG: Einfuhr in die Schweiz](https://www.bazg.admin.ch/bazg/de/home/information-private/interneteinkauf--post--und-kuriersendungen/einfuhr-in-die-schweiz.html) – Geschenksendungen bis 100 CHF
- [CTT: Prazo para desalfandegar](https://www.ctt.pt/ajuda/particulares/importacoes-e-desalfandegamento/prazo-de-desalfandegamento/prazo-para-desalfandegar-a-minha-encomenda) – 20 Tage Verzollungsfrist
- [CTT: Condições gerais de transporte de Encomendas Expresso](https://www.ctt.pt/transversais/condicoes-gerais-transporte-encomendas-expresso) – Reklamationsfristen Expresso (8 und 30 Tage)
- [CTT: Refugo Postal](https://www.ctt.pt/ajuda/particulares/receber/levantar-correspondencia-ou-encomendas-/recuperar-o-correio-que-esta-no-refugo-postal) – Rückforderung zwischen 20 Tagen und 3 Monaten
- [CTT: Reclamações](https://www.ctt.pt/ajuda/particulares/apoio-ao-cliente/contactos/reclamacoes) – Postreklamationen (1 Jahr national, 6 Monate international)
- [CTT: Linhas de apoio](https://www.ctt.pt/ajuda/particulares/apoio-ao-cliente/contactos/linhas-de-apoio) – Telefonnummern und Zeiten (abgerufen am 17. November 2026)
- [CTT: Formulário de contacto](https://www.ctt.pt/ajuda/particulares/apoio-ao-cliente/contactos/formulario-de-contacto) – Kontaktformular (Montag–Freitag, 8:30–19:30 Uhr)
- [CTT: Alertas de phishing](https://www.ctt.pt/transversais/alertas-de-phishing) – Warnungen vor Phishing-Nachrichten und Fake-Domains
- [CTT Express: Envíos a Portugal](https://www.cttexpress.com/envios/portugal) – zwei Zustellversuche
- [CTT Express: Horarios de reparto](https://www.cttexpress.com/preguntas-frecuentes/horarios) – Lieferzeiten Montag–Freitag 9:00–19:00 Uhr
- [CTT Express: Envío y recogida Collectt](https://www.cttexpress.com/red-collectt-express/envio-y-recogida) – 10 Tage im Collectt-Punkt, 5 Tage im Locky-Fach
- [CTT Express: Cómo contactar](https://www.cttexpress.com/preguntas-frecuentes/como-contactar-con-ctt) – Kontakt in Spanien
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Erkennungsregeln, Zeitzonen und Post-Übergaben in Peek
:::
