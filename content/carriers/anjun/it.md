---
title: Tracking Anjun Logistics: stati Cina-Brasile e contatti SAC
description: Traccia un pacco Anjun Logistics o Anjun Express: codici AJ, stati in Cina e Brasile, dogana Remessa Conforme e contatti SAC attivi.
slug: tracciamento-anjun-logistics
published: 2026-11-24
updated: 2026-11-24
---

Anjun Logistics (attiva in Brasile come Anjun Express) è un corriere e-commerce transfrontaliero e nazionale fondato in Cina nel 2011, presente in Brasile dal 2019 ed esteso a copertura nazionale brasiliana nel 2025. Gestisce il ritiro in Cina, l'esportazione aerea, lo sdoganamento in Brasile e la consegna finale nel paese — tramite la propria rete e i punti di ritiro oppure affidando i pacchi a [Correios](carrier:correios-br) dopo lo sdoganamento. Puoi controllare le spedizioni internazionali sul [tracker globale Anjun](https://anjuntrack.com/trajectory), le spedizioni nazionali brasiliane su [Anjun Express Brasile](https://anjunexpress.com/trackPackage), oppure incollare il codice qui sopra.

## Codice di tracciamento Anjun: formato e dove trovarlo

Il codice compare nella notifica di spedizione del negozio online o del marketplace (inclusi ordini internazionali da piattaforme cinesi verso il Brasile). I codici di Anjun hanno 17 caratteri: `AJ`, una data di 6 cifre (`AAMMGG`) e 9 cifre.

| Formato (inventato) | Che cos'è |
| --- | --- |
| `AJ261015482917364` | Codice Anjun Logistics / Anjun Express: `AJ` + data a 6 cifre + 9 cifre. |

Anjun utilizza due portali di tracciamento: [anjuntrack.com](https://anjuntrack.com/trajectory) copre le spedizioni transfrontaliere Cina-Brasile, mentre [anjunexpress.com](https://anjunexpress.com/trackPackage) si concentra sull'ultimo miglio e sui pacchi domestici brasiliani. Se il tuo ordine ha invece un codice `BR` di 15 caratteri, controlla [SPX Express Brazil](carrier:spx-br); un codice di 15 cifre che inizia con `8880` appartiene a [J&T Express Brazil](carrier:j-and-t-br); e un codice postale di 13 caratteri che termina con `BR` è di [Correios](carrier:correios-br). Consulta [formati dei codici di tracciamento](guide:tracking-number-formats) per altri prefissi.

## Significato degli stati Anjun Logistics

I pacchi internazionali Anjun passano in genere da scansioni in inglese in Cina e a Hong Kong a descrizioni in portoghese una volta giunti in Brasile.

| Anjun indica | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `Objeto postado` | Preavviso elettronico o spedizione registrata prima dell'ingresso in magazzino. | «Etichetta creata» |
| `The goods have arrived at Anjun`; `Objeto coletado` come prima scansione di un pacco spedito in Brasile | Il magazzino Anjun in Cina ha ricevuto il pacco, oppure Anjun lo ha ritirato dal mittente in Brasile. | «Preso in carico» |
| `The goods have been issued`, `Departed Facility in SHENZHEN`, `Arrived at Sort Facility HONG KONG INTERNATIONAL AIRPORT`, `Departed Facility in HONG KONG INTERNATIONAL AIRPORT`, `Arrived at Sort Facility Destination Airport` | Lavorazione export, partenza aerea da Shenzhen o Hong Kong e arrivo all'aeroporto di destinazione in Brasile. | «In transito» |
| `Recebimento de cargas no armazém courier` | Ricevuto al terminal corriere in Brasile prima della dogana. | «In transito» |
| `Entrada no armazém alfândegado de TFK`, `Informações enviadas para análise da autoridade aduaneira/órgãos anuentes`, `Análise concluída - importação autorizada` | Ingresso in magazzino doganale, dichiarazione inviata alla dogana e conclusione del controllo. | «In dogana» |
| `Pacote Liberado Aguadando a Coleta de Last Mile`, `Saída do Centro Internacional`, `Coletado por Last Mile`, `Objeto coletado`, `Objeto recebido pelos Correios do Brasil` | Sdoganato e affidato alla rete locale di Anjun o a Correios per la consegna nazionale. | «In transito» |
| `O pacote chegou ao centro de trânsito`, `O pacote saiu do centro de trânsito`, `Objeto chegou ao ponto de entrega` | Transito nei centri brasiliani e arrivo al deposito locale di consegna (non ancora in consegna). | «In transito» |
| `Objeto saiu para entrega ao destinatário` | Un corriere locale è uscito oggi per consegnare il pacco. | «In consegna» |
| `A entrega falhou` (ad esempio `Cliente ausente` o `Endereço fora da rota`) | Consegna non riuscita (destinatario assente o indirizzo fuori percorso). | «Tentativo di consegna» |
| `A entrega falhou: Pacote não pertence à Base` | Il pacco è arrivato alla base locale sbagliata e viene reinstradato anziché tentato a casa tua. | «In transito» |
| `O pacote está a caminho do centro de trânsito para devolução.` | Di ritorno a un centro di transito dopo consegne fallite; può comunque uscire di nuovo in consegna. | «Problema segnalato» |
| `O pacote foi assinado`, `Objeto entregue ao destinatário` | Anjun ha registrato il pacco come firmato e consegnato. | «Consegnato» |

Nota che `Objeto chegou ao ponto de entrega` indica l'arrivo del pacco al deposito locale di Anjun — non a un punto di ritiro per il destinatario — quindi attendi `Objeto saiu para entrega ao destinatário` prima di aspettare il corriere.

## Rete e consegna di Anjun Express in Brasile

Sul [sito brasiliano di Anjun Express](https://anjunexpress.com/home), l'azienda elenca ritiro di primo miglio, [consegna ultimo miglio](https://anjunexpress.com/lastMile), logistica B2B, magazzinaggio, spedizione diretta e Super Express; la pagina ultimo miglio aggiunge un magazzino doganale di 25.000 m² all'aeroporto GRU di San Paolo. La homepage cita oltre 1.000 punti di raccolta, più di 350 veicoli e oltre 2.000 addetti (la [pagina partner punti di raccolta](https://anjunexpress.com/collectionPoint) ne indica oltre 500).

Anjun Express non pubblica sulle pagine aperte un numero fisso di tentativi di consegna, tempi garantiti o giorni di giacenza. Per scadenze e regole di consegna di un acquisto online, controlla la pagina ordine del negozio o marketplace.

## Sdoganamento e Remessa Conforme in Brasile

Poiché la maggior parte dei pacchi Anjun arriva in Brasile dalla Cina, viene sdoganata secondo il [Programa Remessa Conforme (PRC) della Receita Federal](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/programa-remessa-conforme-o-que-e-como-funciona):

- **Marketplace o negozio certificato:** le imposte si pagano al checkout. Le spedizioni a privati fino a 50 USD (merce, trasporto e assicurazione) prevedono 0 % di dazio federale più 17 %–20 % di ICMS statale. Oltre 50 USD, il dazio è del 60 % con sconto di 30 USD sull'imposta, più 17 %–20 % di ICMS.
- **Negozio non certificato:** 60 % di dazio federale senza sconto (anche fino a 50 USD) più 17 %–20 % di ICMS, riscossi all'ingresso in Brasile prima dello svincolo.

Mentre il pacco mostra `Entrada no armazém alfândegado de TFK` o `Informações enviadas para análise da autoridade aduaneira/órgãos anuentes`, la Receita Federal o gli enti competenti controllano la dichiarazione. Se vengono richiesti documenti o pagamenti, verifica nei messaggi dell'ordine o al contatto doganale di Anjun invece di pagare da link casuali. Vedi [pacchi fermi in dogana](guide:customs).

## Pacco Anjun fermo, consegna fallita o smarrito

Per gli acquisti sui marketplace, la piattaforma o il venditore rispondono di rimborsi, nuove spedizioni e contestazioni doganali.

**Nessuna scansione tra Cina e Brasile.** Esportazione aerea, code aeroportuali e ingresso in deposito doganale creano spesso pause di vari giorni tra `Departed Facility in HONG KONG INTERNATIONAL AIRPORT` e `Recebimento de cargas no armazém courier`. Vedi [perché il tracking smette di aggiornarsi](guide:tracking-not-updating).

**Instradato alla base sbagliata.** `A entrega falhou: Pacote não pertence à Base` può preoccupare, ma indica solo che il pacco è stato smistato al deposito locale errato e va trasferito internamente prima del tentativo al tuo indirizzo.

**Consegnato ma non ricevuto.** Chiedi prima a familiari, vicini e alla *portaria* (portineria dell'edificio), poi segnala il problema al marketplace e al SAC di Anjun. Vedi [consegnato ma non ricevuto](guide:delivered-not-received).

**Danneggiato, smarrito o in rientro.** Conserva foto dell'imballaggio danneggiato e avvisa subito il venditore; vedi [pacco danneggiato](guide:damaged-parcel), [pacco smarrito](guide:lost-parcel) e [restituito al mittente](guide:returned-to-sender).

## Assistenza clienti Anjun Express e nuova email

Su [anjunexpress.com/home](https://anjunexpress.com/home), Anjun Express comunica che la vecchia email `sac@anjunexpress.com.br` è stata disattivata dal 12 agosto 2026 ed elenca i canali brasiliani attivi:

- **Telefono SAC nazionale:** `(11) 5026-7008`, dal lunedì al venerdì dalle 08:00 alle 17:30 (festivi esclusi).
- **Email SAC:** `sac@anjun.com.br`.
- **Chat online:** disponibile su [anjunexpress.com](https://anjunexpress.com/home).
- **Richieste express internazionale / dogana:** `atendimento.aduaneiro@anjun.com.br`.
- **Ombudsman doganale (*Ouvidoria*):** `ouvidoria.aduaneira@anjun.com.br`.
- **Mediazione consumatori:** se una controversia con un'azienda aderente non si risolve, i consumatori in Brasile con account gov.br argento o oro possono usare [Consumidor.gov.br](https://www.consumidor.gov.br/pages/conteudo/publico/1), dove le aziende registrate rispondono entro 10 giorni.

> Diffida di finti messaggi SMS o WhatsApp che chiedono pagamenti doganali sostenendo che il tuo pacco Anjun sia bloccato. Verifica ogni avviso dentro il marketplace dell'ordine o scrivendo a `atendimento.aduaneiro@anjun.com.br` prima di pagare. Vedi [SMS truffa sulle consegne](guide:delivery-scam-texts).

## Tracciare un pacco Anjun Logistics con Peek

[Peek](/) identifica automaticamente Anjun Logistics da qualsiasi codice `AJ` di 17 caratteri (con data `AAMMGG` valida) e dai link incollati di `anjuntrack.com` (`/trackQuery?t=` o `/trajectory?orderNum=`) o `anjunexpress.com/trackPackage?tracking=` contenenti un codice `AJ`. Se devi cambiare corriere manualmente, tocca «Cambia» mentre aggiungi il pacco oppure «Cambia corriere» nella scheda del pacco e scegli Anjun Logistics (Peek segue oltre 4.500 corrieri).

Peek interroga prima `anjuntrack.com` per la cronologia internazionale e, se non ci sono ancora scansioni, interroga automaticamente anche `anjunexpress.com`, così i pacchi domestici brasiliani appaiono senza passaggi extra. Peek mostra la fase («Etichetta creata», «Preso in carico», «In transito», «In dogana», «In consegna», «Tentativo di consegna», «Problema segnalato», «Consegnato»), le righe in inglese e portoghese, l'eventuale motivo del mancato recapito (come `Cliente ausente`), città e stato in Brasile (senza indirizzo stradale), il paese di destinazione e gli orari locali (ora cinese in Cina, ora brasiliana in Brasile, mostrati nel tuo fuso orario). Peek elimina la barra di avanzamento, l'indirizzo stradale del destinatario, le coordinate GPS, il nome del corriere, telefoni ed email di assistenza nelle note, firme e foto; il tracker Anjun non fornisce data prevista, peso né codice di passaggio locale.

Mentre tieni il pacco aperto in Peek, o nel giorno della consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti; nel giorno in cui Anjun mostra `Objeto saiu para entrega ao destinatário`, ogni 2 minuti dalle 6:00 ora locale finché risponde il tracker di Anjun. Altrimenti controlla ogni ora. Dopo 12 ore solo su «Etichetta creata» o «Preso in carico», o due giorni senza novità, controlla ogni ora comunque tu lo segua, e ogni 6 ore dopo sette giorni su «Etichetta creata», salvo che sia aperto o previsto quel giorno. Quando il tracker di Anjun non risponde e Peek segue il pacco solo tramite servizi di tracking, controlla ogni ora, ogni 3 ore dopo un giorno senza novità e fino a ogni 15 minuti nel giorno di consegna mentre è aperto o con notifiche attive.

Quando la scansione più recente ha quattro giorni e il pacco è ancora su «Etichetta creata», «Preso in carico» o «In transito», Peek lo indica con il giorno di quella scansione (ad esempio «Nessun aggiornamento da lun 12 ott»). La pagina del pacco mostra allora «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a Anjun Logistics.» Dopo tre settimane senza nuovi eventi indica «Niente di nuovo da più di tre settimane. Se il pacco non è ancora arrivato, contatta il venditore. Peek continua a chiedere a Anjun Logistics.»

Puoi scegliere avvisi per «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek monitora solo gli aggiornamenti e non può sdoganare il pacco né contattare Anjun Express al posto tuo.

## Domande sul tracking Anjun Logistics

### Perché il tracciamento Anjun mostra sia inglese sia portoghese?

Le spedizioni transfrontaliere partono dai magazzini Anjun in Cina e dagli hub export di Hong Kong, che registrano scansioni in inglese, e passano al portoghese all'arrivo in Brasile per dogana e consegna locale.

### Cosa significa `A entrega falhou: Pacote não pertence à Base`?

Indica che il pacco è stato smistato per errore a una base locale Anjun sbagliata, non che il corriere non ti abbia trovato in casa. Anjun lo trasferisce alla base corretta, perciò Peek lo mantiene «In transito».

### L'email `sac@anjunexpress.com.br` è ancora attiva?

No. Anjun Express ha disattivato `sac@anjunexpress.com.br` dal 12 agosto 2026. Scrivi a `sac@anjun.com.br`, chiama `(11) 5026-7008` (lun–ven, 08:00–17:30) oppure usa `atendimento.aduaneiro@anjun.com.br` per la dogana.

### Anjun può affidare il mio pacco a Correios?

Sì. Alcuni pacchi internazionali superano la dogana e mostrano `Objeto recebido pelos Correios do Brasil`, indicando che Correios cura la consegna nazionale finale mentre il tracker Anjun registra il passaggio.

:::sources
- [Anjun Express: Home](https://anjunexpress.com/home) – servizi in Brasile, numeri della rete, contatti SAC attivi, email dogana/Ouvidoria e avviso di disattivazione di `sac@anjunexpress.com.br` (12/08/2026)
- [Anjun Express: Chi siamo](https://anjunexpress.com/aboutUs) – fondazione 2011, ingresso in Brasile 2019, copertura nazionale 2025
- [Anjun Express: Track Package](https://anjunexpress.com/trackPackage) – portale brasiliano di tracciamento
- [AnjunTrack: Trajectory](https://anjuntrack.com/trajectory) – portale transfrontaliero Cina-Brasile
- [Anjun Express: Last-Mile](https://anjunexpress.com/lastMile) – consegna ultimo miglio e deposito doganale GRU
- [Anjun Express: Collection Points](https://anjunexpress.com/collectionPoint) – rete punti di raccolta partner
- [Receita Federal: Programa Remessa Conforme](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/programa-remessa-conforme-o-que-e-como-funciona) – regole su dazi federali e ICMS in Brasile
- [Consumidor.gov.br](https://www.consumidor.gov.br/pages/conteudo/publico/1) – piattaforma pubblica di mediazione (risposta entro 10 giorni, account gov.br argento/oro)
- [Universal Parcel Scraper: anjun](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/anjun) – rilevamento `AJ`, ricerca su doppio portale, mappatura stati, campi filtrati e frequenza
:::
