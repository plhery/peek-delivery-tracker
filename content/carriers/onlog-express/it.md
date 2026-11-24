---
title: Tracciamento Onlog Express: codici AA, stati e aiuto
description: Traccia un pacco Onlog Express in Brasile: codici AA da 12 caratteri, stati dei centri di distribuzione, avvisi antifrode e supporto a San Paolo.
slug: tracciamento-onlog-express
published: 2026-11-24
updated: 2026-11-24
---

**Onlog Express** (*Onlog Franqueadora*) è una rete brasiliana di consegna pacchi e logistica con sede a San Paolo, oltre 20 anni di esperienza in partnership logistiche e più di 18 milioni di pacchi consegnati in tutto il Brasile ([onlogexpress.com.br](https://onlogexpress.com.br/)). Collegando e-commerce e aziende agli operatori di consegna nazionali con livelli che vanno dalla consegna urgente in pochi minuti o in giornata (*Entrega em Minutos*, *Entrega no Dia*) fino a prioritaria (*Expresso D1*) ed economica (*Econômico D2*), insieme a fulfillment e trasporto internazionale, Onlog offre il tracciamento pubblico per codice oggetto o CPF/CNPJ su [conecta.log.br/rastreio.php](https://conecta.log.br/rastreio.php). Puoi controllare una spedizione lì o incollare il codice `AA` da 12 caratteri nel riquadro qui sopra.

## Codici di tracciamento Onlog Express (formato `AA`)

Il tracciamento pubblico ospite di Onlog Express usa un codice oggetto a 12 caratteri che inizia con `AA` seguito da 10 cifre:

| Formato (inventato) | Che cos’è |
| --- | --- |
| `AA4829173605` | Codice oggetto Onlog Express da 12 caratteri (`AA` + 10 cifre). |
| `AA7391846208` | Altro riferimento pacco `AA` Onlog Express da 12 caratteri. |

Controlla l’e-mail di spedizione del negoziante o il link di tracciamento `onlog.app.br/rastreio/...` per il codice `AA`. Se non hai il codice, il portale web Onlog su [conecta.log.br/rastreio.php](https://conecta.log.br/rastreio.php) permette ai destinatari di cercare con il proprio CPF o CNPJ brasiliano (e include una ricerca separata per oggetti anteriori allo 01/01/2025). Vedi i [formati dei codici di tracciamento](guide:tracking-number-formats).

## Cosa significano gli stati di tracciamento Onlog Express

Onlog Express pubblica descrizioni delle scansioni in portoghese dalla creazione dell’etichetta e dal ritiro fino ai trasferimenti nei centri di distribuzione e alla firma del destinatario:

| Onlog Express mostra (PT-BR) | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `OBJETO CRIADO`, `ETIQUETA EMITIDA` | Spedizione creata ed etichetta emessa elettronicamente prima del ritiro fisico. | «Etichetta creata» |
| `OBJETO POSTADO`, `COLETA DE ENCOMENDA` | Pacco consegnato presso un’unità Onlog o ritirato dal mittente. | «Preso in carico» |
| `OBJETO EM TRANSFERÊNCIA`, `ENTRADA NO CENTRO DE DISTRIBUIÇÃO`, `ENCAMINHADO PARA O OPERADOR LOGÍSTICO`, `BIPE DE EXPEDIÇÃO`, `BIPE DE RECEBIMENTO` | Ricevuto in un centro di distribuzione, trasferito tra impianti o inoltrato all’operatore logistico regionale. | «In transito» |
| `OBJETO SAIU PARA ENTREGA AO DESTINATÁRIO`, `BIPE DE SAÍDA PARA ENTREGA` | In consegna oggi con il corriere locale per il recapito finale. | «In consegna» |
| `OBJETO ENTREGUE AO DESTINATÁRIO`, `ASSINATURA DE ENCOMENDA` | Consegnato e firmato all’indirizzo di destinazione. | «Consegnato» |

`ENCAMINHADO PARA O OPERADOR LOGÍSTICO` significa che il pacco è stato inoltrato all’operatore logistico che cura la tratta successiva.

## Servizi di consegna e avviso ufficiale contro le truffe

Secondo [onlogexpress.com.br](https://onlogexpress.com.br/):

- **Livelli di servizio:** **Entrega em Minutos** (urgente), **Entrega no Dia** (in giornata), **Expresso D1** (transito prioritario) ed **Econômico D2** (economico), con tempi esatti e disponibilità legati ai codici postali (CEP) di origine e destinazione.
- **Avviso antifrode importante:** Onlog pubblica un avviso esplicito in home page dichiarando che **Onlog non richiede pagamenti di tariffe via email, WhatsApp o telefono**. Non pagare mai tasse o spese di sblocco chieste con messaggi non richiesti a nome di Onlog. Vedi [truffe via SMS sulle consegne](guide:delivery-scam-texts).

## Pacco Onlog Express in ritardo, silenzioso o smarrito

- **Pausa dopo `ENCAMINHADO PARA O OPERADOR LOGÍSTICO`:** le scansioni si fermano spesso tra hub regionali o durante il passaggio al corriere locale dell’ultimo miglio tra gli stati brasiliani. Vedi [perché il tracciamento smette di aggiornarsi](guide:tracking-not-updating).
- **Consegnato senza ricevuta o pacco smarrito:** controlla prima con la portineria (*portaria*), familiari e vicini, poi contatta il negozio online e rivolgiti all’assistenza Onlog di San Paolo in orario d’ufficio con il tuo codice `AA`. Vedi [consegnato ma non ricevuto](guide:delivered-not-received), [pacco smarrito](guide:lost-parcel), [pacco danneggiato](guide:damaged-parcel) e [restituito al mittente](guide:returned-to-sender).

## Assistenza clienti Onlog Express e contatti a San Paolo

Canali ufficiali pubblicati su [onlogexpress.com.br](https://onlogexpress.com.br/):

- **Telefono (San Paolo):** `(11) 5662-8108` e `(11) 5663-3829`.
- **Email:** `atendimento@onlogexpress.com.br`.
- **Orari di assistenza:** dal lunedì al venerdì, dalle `08:00` alle `18:00` BRT (chiuso sabato e domenica).
- **Sede (*Matriz São Paulo*):** Rua Estados Unidos, 2031, Jardim América, São Paulo – SP, Brasile.
- **Tracciamento online:** [conecta.log.br/rastreio.php](https://conecta.log.br/rastreio.php).

> Ricorda l’avviso ufficiale di Onlog: Onlog non addebita mai spese di sblocco via email, WhatsApp o telefono. Ignora e segnala qualsiasi messaggio che richieda un pagamento PIX o con carta per sbloccare un pacco. Vedi [truffe via SMS sulle consegne](guide:delivery-scam-texts).

## Tracciare un pacco Onlog Express con Peek

Quando inserisci un codice `AA` da 12 caratteri (`AA4829173605` o `AA7391846208`, inventati), [Peek](/) interroga [Colis Privé](carrier:colis-prive), [Aramex Australia](carrier:aramex-au) e [Aramex Nuova Zelanda](carrier:aramex-nz), poi i servizi di tracciamento e infine Onlog Express — aprendone il tracker nel browser —, mostrando «Onlog Express ha questo pacco.» non appena Onlog conferma. Peek riconosce anche come Onlog i link incollati `onlog.app.br/rastreio/AA...`. Se devi selezionare Onlog manualmente, tocca «Cambia» in fase di aggiunta oppure «Cambia corriere» nella schermata del pacco (anche cercare «ONLOG» o «Onlog Franqueadora» lo trova).

Peek legge il tracker di Onlog in un browser e mostra la fase («Etichetta creata», «Preso in carico», «In transito», «In consegna», «Consegnato»), il testo portoghese della scansione e il luogo. Onlog fornisce ogni orario al minuto senza scarto da UTC: Peek lo data nel fuso orario del relativo luogo (San Paolo per una scansione a San Paolo, Manaus per Manaus), una scansione senza luogo nel fuso della scansione vicina, altrimenti sull’ora di San Paolo, e mostra ogni scansione con l’ora del tuo dispositivo. Il tracker Onlog non indica giorno stimato di consegna, peso, codici evento né ora di consegna.

Mentre tieni il pacco aperto in Peek, o il giorno della consegna con le notifiche attive, Peek controlla fino a ogni 30 minuti — il tempo minimo consentito da Onlog tra due controlli — e un’ora dopo un controllo fallito; non effettua controlli ogni 2 minuti nel giorno in cui il pacco è in consegna. Altrimenti Peek controlla ogni ora. Dopo 12 ore con solo «Etichetta creata» o «Preso in carico», o dopo due giorni senza novità, controlla ogni ora comunque tu lo segua, e ogni 6 ore dopo sette giorni su «Etichetta creata». Quando il tracciamento Onlog non risponde e Peek segue il pacco solo tramite servizi di tracciamento, controlla ogni ora, ogni 3 ore dopo una giornata ferma e fino a ogni 30 minuti nel giorno in cui il pacco è in consegna, mentre hai il pacco aperto o le notifiche attive.

Quando la scansione più recente risale a quattro giorni prima e il pacco è ancora su «Etichetta creata», «Preso in carico» o «In transito», Peek lo segnala con il giorno di quella scansione, ad esempio «Nessun aggiornamento da lun 12 ott». La pagina del pacco mostra quindi «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a Onlog Express.» Dopo tre settimane senza nuove scansioni mostra «Niente di nuovo da più di tre settimane. Se il pacco non è ancora arrivato, contatta il venditore. Peek continua a chiedere a Onlog Express.»

Puoi scegliere gli avvisi per «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek monitora solo gli aggiornamenti di tracciamento e non può cercare pacchi per CPF/CNPJ, modificare indirizzi di consegna né contattare Onlog Express al posto tuo.

## Domande sul tracciamento Onlog Express

### Come si presenta un numero di tracciamento Onlog Express?

I codici di tracciamento Onlog Express iniziano con `AA` seguiti da 10 cifre (12 caratteri totali, ad esempio `AA4829173605`, inventato).

### Posso tracciare un pacco Onlog Express con il mio CPF o CNPJ?

Sì: sul sito di Onlog su [conecta.log.br/rastreio.php](https://conecta.log.br/rastreio.php) puoi passare alla scheda **POR CPF/CNPJ** se non hai il codice `AA`. Peek utilizza soltanto il codice oggetto `AA`.

### Ho ricevuto un messaggio WhatsApp o un’email che chiede una tariffa da Onlog: è autentico?

No. Onlog dichiara esplicitamente su [onlogexpress.com.br](https://onlogexpress.com.br/) che **non richiede mai** pagamenti di tariffe via email, WhatsApp o telefono. Non pagare.

### Come contatto l’assistenza clienti Onlog Express?

Chiama `(11) 5662-8108` o `(11) 5663-3829`, oppure scrivi a `atendimento@onlogexpress.com.br` dal lunedì al venerdì dalle 08:00 alle 18:00 BRT.

:::sources
- [Onlog Express: sito ufficiale](https://onlogexpress.com.br/) – livelli di servizio (*Entrega em Minutos*, *Entrega no Dia*, *Expresso D1*, *Econômico D2*), 18M+ pacchi, 20+ anni, avviso antitruffa e contatti sede San Paolo
- [Onlog Express: tracker ufficiale (Conecta)](https://conecta.log.br/rastreio.php) – tracciamento pubblico per codice oggetto o CPF/CNPJ
- [Universal Parcel Scraper: onlog-express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/onlog-express) – formato `AA\d{10}`, regola link `onlog.app.br/rastreio/`, mappatura 13 frasi portoghesi, consultazione browser al massimo ogni 30 minuti (60 dopo errore) e fusi delle scansioni
:::
