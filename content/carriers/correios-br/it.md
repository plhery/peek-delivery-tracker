---
title: Tracciamento Correios Brasile: codici, stati e dogana
description: Traccia un pacco Correios da o verso il Brasile: formato dei codici BR, stati in portoghese, dazi di importazione, Minhas Importações e contatti.
slug: tracciamento-correios-brasile
published: 2026-11-22
updated: 2026-11-22
---

Correios (*Empresa Brasileira de Correios e Telégrafos*) è la posta nazionale del Brasile. Un pacco spedito dal Brasile mantiene il codice Correios lungo tutto il percorso ed è consegnato dalla posta del paese di destinazione. Un pacco acquistato all’estero mantiene il codice della posta di partenza ed è consegnato da Correios in Brasile. Traccialo sul [tracker Correios](https://rastreamento.correios.com.br/app/index.php), che accetta fino a 20 codici o un CPF o CNPJ dopo la verifica da immagine, oppure incolla il codice nel campo sopra.

## Codice di tracciamento Correios: com’è fatto

Correios descrive i codici come sequenze di 13 caratteri: due lettere per il tipo di servizio, nove cifre e due lettere per il paese di origine (`BR` per il Brasile). Le lettere seguono lo standard dell’Unione Postale Universale (UPU). Con cifre inventate:

| Codice | Che cos’è |
| --- | --- |
| `EA482917367BR` | `E`: EMS, il servizio espresso. |
| `CA482917367BR` | `C`: pacco postale. |
| `LA482917367BR` | `L`: lettera tracciata. |
| `RA482917367BR` | `R`: raccomandata. |
| `NN482917367BR` | `N`, `B`, `D`, `P` o `Z` (oltre ad `AV`–`AZ`, `GA`, `GD`): lettere riservate dall’UPU a servizi nazionali o bilaterali. |
| `RA482917367CN` | Codice di [China Post](carrier:china-post), non di Correios. In arrivo in Brasile, Correios lo consegna con questo codice. |

La nona cifra controlla le otto precedenti: una cifra errata rende in genere il codice non valido. Correios precisa che anche alcuni pacchi dall’estero portano un codice terminante in `BR` tramite un servizio specifico di Correios. Nessun codice? Correios invita a chiederlo al negozio. Il tracker mantiene la cronologia online per 180 giorni.

## Significato degli stati di tracciamento Correios

Correios pubblica gli stati in portoghese con città e stato federato (*UF*) di ciascuna scansione. Ecco la traduzione:

| Correios indica | Cosa significa per te |
| --- | --- |
| `Etiqueta emitida` | Etichetta emessa. Correios non ha ancora il pacco. |
| `Objeto postado` | Spedito: Correios ha preso in carico il pacco. |
| `Objeto encaminhado para fiscalização aduaneira de exportação` | Un pacco diretto all’estero è passato alla dogana brasiliana di esportazione prima di lasciare il Brasile (sotto). Finché resta lì non è in mano a Correios. |
| `Objeto em transferência - por favor aguarde` | In movimento tra sedi Correios («in trasferimento, attendere prego»). |
| `Objeto recebido pelos Correios do Brasil` | Un pacco dall’estero è arrivato a Correios in Brasile. |
| `Informações enviadas para análise da autoridade aduaneira/órgãos anuentes` | Dati inviati alla dogana e agli enti che controllano le importazioni. |
| `Aguardando pagamento` | Sono dovute imposte o tariffe prima della consegna: vedi Minhas Importações sotto («In dogana» su Peek). |
| `Análise concluída - importação autorizada` | Controllo terminato e importazione autorizzata. |
| `Saída do Centro Internacional` | Uscito dal centro internazionale Correios per la consegna in Brasile. |
| `Objeto saiu para entrega ao destinatário` | In consegna al destinatario. |
| `Objeto não entregue - carteiro não atendido` | Il postino non ha trovato nessuno per ritirarlo. |
| `Objeto aguardando retirada no endereço indicado` | In attesa di ritiro all’indirizzo indicato da Correios, per esempio in un ufficio («Pronto per il ritiro» su Peek). |
| `Inconsistências no endereçamento do objeto` | Problema con l’indirizzo di consegna. |
| `Objeto entregue ao destinatário` | Consegnato al destinatario. |

Alcune voci indicano criticità. `Importação não autorizada` significa importazione non autorizzata; `Prazo de regularização encerrado`, scadenza del termine per regolarizzare; `Devolução determinada pela autoridade competente`, reso ordinato dalle autorità. `Objeto saiu para entrega ao remetente` è in consegna al mittente, non a te.

Le pagine import di Correios citano anche `Em fiscalização aduaneira` (in ispezione doganale). Finché compare, Correios ricorda che il pacco non è sotto la sua custodia.

## Pacchi dal Brasile verso l’Europa e il resto del mondo

Correios sdogana il pacco in uscita dal Brasile e lo affida alla posta del paese di destinazione, che lo consegna con lo stesso codice (l’UPU ne prevede uno per spedizione). Il tracker di quella posta segue l’ultima tratta in prima mano, ad esempio [Poste Italiane](carrier:poste-italiane), [Royal Mail](carrier:royal-mail), [USPS](carrier:usps), [Canada Post](carrier:canada-post), An Post, [Australia Post](carrier:australia-post), [La Poste](carrier:la-poste), [CTT](carrier:ctt), [Correos](carrier:correos-spain) o [Deutsche Post](carrier:dhl).

Se la tua dogana trattiene il pacco, Correios precisa che devi rivolgerti direttamente alla dogana: né Correios né la tua posta possono intervenire. Nell’UE i beni acquistati online fuori UE in pacchi fino a 150 € prevedono dal 1° luglio 2026 un dazio di 3 € per articolo (merci con stessa classificazione tariffaria). Dal 1° novembre 2026 si applica inoltre una tariffa unionale di gestione di 2 € per articolo a prescindere dal valore. Dettagli su cosa si paga e come la posta incassa: [pacchi fermi in dogana](guide:customs).

## Pacchi in ingresso in Brasile: CPF, imposte e Minhas Importações

**CPF.** «Ogni pacco proveniente da altri paesi è un’importazione e ogni importazione richiede un CPF», afferma Correios. Il CPF deve essere regolare e il nome deve coincidere con gli archivi della Receita Federal.

**Minhas Importações.** Correios gestisce la dogana online su [Minhas Importações](https://portalimportador.correios.com.br) e nell’app Correios dopo una registrazione unica su Meu Correios. Lì associ i pacchi al CPF, invii i documenti richiesti dalla dogana e paghi.

**Imposte**, secondo la Receita Federal:

- **Dazio d’importazione:** 60% del valore doganale (merce, trasporto e assicurazione). Questa regola generale copre ogni pacco escluso dalla regola seguente: acquisti fuori da Remessa Conforme o spedizioni per aziende.
- **Acquisto su sito [certificato in Remessa Conforme](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/empresas-certificadas-no-programa-remessa-conforme-prc) (PRC) per privati:** 0% fino a 50 US$; oltre, 60% con 30 US$ di riduzione sul dazio. Vale per dichiarazioni registrate dal 12 maggio 2026 (prima era 20% con 20 US$ di riduzione).
- **ICMS**, imposta statale: dal 17% al 20% a seconda dello stato, con o senza PRC.
- **Tariffa Correios** di gestione doganale (*Despacho Postal*), eventualmente aggiunta. Non è un’imposta.

Oltre 3.000 US$, o se la dogana non svincola il pacco per altro motivo, Correios indica che serve uno spedizioniere doganale (*despachante aduaneiro*).

**Quando pagare.** Sui siti PRC le imposte si pagano al checkout; se al checkout non risultano, saranno addebitate all’arrivo senza riduzione. Altrimenti paga con *boleto* o Pix in Minhas Importações o nell’app entro 20 giorni di calendario dalla comparsa dell’addebito, oppure il pacco rientra all’estero. Ritieni l’importo errato? Richiedi una *Revisão de Tributos* lì prima di generare boleto o Pix. Puoi inviare documenti una sola volta, e Correios non può stimare i tempi della Receita Federal.

**Consegna.** Se Correios non riesce a consegnare, il pacco passa alla filiale più vicina e ricevi un avviso. I pacchi dall’estero restano in giacenza 20 giorni di calendario dopo i tentativi a casa, 7 per EMS e Correios Packet. Un delegato può ritirarli con autorizzazione firmata, documento originale proprio e tuo documento (basta una copia).

**Truffe.** Correios non invia mai link di pagamento né chiede dati personali o bancari via e-mail, SMS o WhatsApp, e la Receita Federal non invia boleto, non telefona e non scrive e-mail per tasse. Vai direttamente su `correios.com.br` senza passare da un motore di ricerca; vedi [SMS truffa sulle consegne](guide:delivery-scam-texts).

## Pacco Correios fermo, perso o restituito

**Il codice non compare mai.** Correios invita a verificare col mittente che il codice sia corretto (lettere incluse) e che il pacco sia stato spedito. Se lo è stato, il mittente apre reclamo.

**Fermo in dogana.** Correios non fornisce tempi per le verifiche doganali. Controlla Minhas Importações per richieste di documenti o pagamento; vedi [perché il tracciamento si ferma](guide:tracking-not-updating).

**Pagato ma non consegnato.** Superata la data prevista nel tracciamento, apri una segnalazione (*manifestação*) sul sito Correios.

**Perso o danneggiato.** I reclami si aprono su Fale Conosco con account Meu Correios da parte di chi ha spedito il pacco o generato il boleto o Pix fiscale. Per l’estero i termini decorrono dalla spedizione: 120 giorni per EMS e servizi espressi, 6 mesi per il resto; per importazioni Correios Packet, 90 giorni da consegna o reso (30 per danni). Correios punta a rispondere entro 5 giorni lavorativi e paga indennizzi solo su un conto bancario brasiliano. Per la legge brasiliana il bene appartiene al mittente fino alla consegna, quindi fuori dal Brasile chiedi a negozio o mittente di reclamare; vedi [pacchi persi](guide:lost-parcel).

**Restituito.** `Objeto entregue ao remetente` indica che è rientrato al mittente. Chiedi al negozio rimborso o nuova spedizione; vedi [restituito al mittente](guide:returned-to-sender).

## Numero e assistenza clienti Correios

Contatti pubblicati da Correios in portoghese (orari nell’ora di Brasilia):

- **Telefono (SAC):** `4003 8210` nelle capitali di stato, aree metropolitane e da cellulare altrove; `0800 881 8210` da fisso altrove. Dal lunedì al venerdì 8:00–20:00, sabato 8:00–14:00, esclusi i festivi.
- **WhatsApp:** `(11) 4003-8210`, utile anche per tracciamento.
- **Chat:** Carol, su `correios.com.br`.
- **Reclami:** il portale [Fale Conosco](https://faleconosco.correios.com.br/inicio).

I centri di smistamento e le unità di consegna non ricevono telefonate.

## Tracciare un pacco Correios con Peek

[Peek](/) segue oltre 4.500 corrieri e riconosce subito Correios per due lettere, nove cifre e `BR` con cifra di controllo valida («Corriere rilevato»). Se la cifra è errata, Peek non assegna alcun corriere e, scegliendo Correios con «Cambia», avvisa che Correios «non emette numeri come questo»: ricontrolla il codice. Funzionano anche i link Correios con `objetos=`.

Peek recupera la cronologia da Correios e mostra stato, servizio, ogni scansione in portoghese con città e stato, ora di consegna e data di consegna prevista da Correios mentre il pacco è in transito e non in ritardo. Gli orari sono letti sull’ora locale del luogo di scansione (ora di Brasilia in Brasile) e convertiti nel tuo fuso («Orari nel tuo fuso orario:» seguito dal nome).

Poiché Correios non comunica a Peek la destinazione, fuori dal Brasile Peek interroga con lo stesso codice anche la tua posta nazionale (Poste Italiane, USPS, Royal Mail, Canada Post, An Post o Australia Post, per esempio) e passa a quella non appena presenta scansioni più recenti, conservando la cronologia Correios. Un pacco diretto in Brasile con codice di un’altra posta è seguito su quella posta; Peek interroga anche Correios appena l’altra posta indica il Brasile come destinazione (o senza destinazione se cerchi dal Brasile).

Mentre hai il pacco aperto in Peek — o il giorno della consegna con le notifiche attive — Peek interroga direttamente Correios fino a ogni 10 minuti, e fino a ogni 2 minuti (o ogni 5 minuti se deve aprire il tracker Correios in un browser) dalle 6:00 nell’ora dell’ultima scansione (ora di Brasilia in Brasile) nel giorno in cui Correios segna «In consegna», finché il tracker risponde; altrimenti, e dopo due giorni senza novità (12 ore se resta su «Etichetta creata» o «Preso in carico»), controlla ogni ora. Per le notifiche scegli «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Dopo quattro giorni senza scansioni in viaggio, fuori da dogana o problemi segnalati, Peek mostra la data dell’ultima scansione («Nessun aggiornamento da …») e «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a Correios Brazil.», con il link «Chiedi aiuto a Correios Brazil» al centro assistenza. Peek non può contattare Correios, pagare tasse né aprire reclami per te.

## Domande frequenti su Correios Brasile

### Cosa significa «Objeto em transferência - por favor aguarde»?

«Oggetto in trasferimento, attendere prego»: il pacco è in viaggio tra due sedi Correios in Brasile e per ora non devi fare nulla.

### Perché il mio pacco Correios dice «Aguardando pagamento»?

Sono dovute tasse o la tariffa Correios. Paga con boleto o Pix su Minhas Importações o nell’app Correios entro 20 giorni di calendario, altrimenti il pacco torna all’estero.

### Quanto tempo impiega Correios a consegnare?

Dipende dal servizio e, in Brasile, dal codice postale (*CEP*); il tracciamento Correios mostra una stima di consegna. Per un pacco dall’estero il tempo decorre da `Saída do Centro Internacional`, dopo lo svincolo doganale.

### Posso tracciare un pacco Correios fuori dal Brasile?

Sì, con lo stesso codice: sul tracker di Correios e, una volta arrivato, su quello della posta del tuo paese.

:::sources
- [Correios: Rastreamento](https://rastreamento.correios.com.br/app/index.php) – tracker, 20 codici o CPF/CNPJ, verifica immagine
- [Correios: Recebimento de Encomendas Internacionais](https://www.correios.com.br/receber/encomenda/internacional) – CPF, Minhas Importações, pagamento, revisione, spedizioniere, stato doganale
- [Correios: Como importar pelos Correios (PDF)](https://www.correios.com.br/receber/encomenda/arquivos/internacional/como-importar-pelos-correios-2024-1.pdf) – formato codice, Aguardando pagamento, tempi di consegna
- [Correios: Prazo de Guarda Objetos Internacionais](https://www.correios.com.br/receber/prazo-de-guarda-objetos-internacionais) – 20 e 7 giorni
- [Correios: Perguntas frequentes](https://www.correios.com.br/acesso-a-informacao/perguntas-frequentes) – codici mancanti, 180 giorni, ritiro in filiale
- [Correios: Envio Internacional de Encomendas](https://www.correios.com.br/enviar/encomendas/internacional/internacional) – sdoganamento export, dogana all’estero
- [Correios: Fale Conosco, Termo de uso](https://faleconosco.correios.com.br/inicio) – reclami, scadenze, indennizzi
- [Correios: Central de Atendimento](https://www.correios.com.br/falecomoscorreios/central-de-atendimento) – telefoni, orari, WhatsApp, Carol
- [Correios: Segurança](https://www.correios.com.br/falecomoscorreios/seguranca/seguranca) – nessun link di pagamento
- [Receita Federal: Tributação](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/topicos/tributacao) – aliquote, ICMS, tariffe, truffe
- [Receita Federal: Em que momento vou pagar os impostos?](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/preciso-pagar-impostos-nas-compras-internacionais/em-que-momento-pagarei-o-imposto) – imposte al checkout
- [Receita Federal: Como realizar pagamento pelo Minhas Importações dos Correios](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/preciso-pagar-impostos-nas-compras-internacionais/como-realizar-o-pagamento-pelo-minhas-importacoes-dos-correios) – evitare i motori di ricerca
- [Receita Federal: Empresas certificadas no Programa Remessa Conforme](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/empresas-certificadas-no-programa-remessa-conforme-prc) – siti certificati, nome completo Correios
- [UPU: S10 standard, Identification of postal items](https://www.upu.int/UPU/media/upu/files/postalSolutions/programmesAndServices/standards/S10-12.pdf) – lettere di servizio, cifra di controllo
- [European Commission: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – dazio 3 €, tariffa di gestione 2 €
- [European Commission: Guidance on the €3 customs duty and Union handling fee](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – articolo, vendite a distanza
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – diciture stati, campi letti da Peek e frequenza controlli
:::
