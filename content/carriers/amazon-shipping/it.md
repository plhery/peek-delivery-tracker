---
title: Amazon Shipping tracking: dov'è il pacco e chi contattare
description: Amazon Shipping: com'è fatto l'ID di tracciamento, quale tracker lo conosce e chi contattare se il pacco non arriva, arriva danneggiato o risulta consegnato.
slug: amazon-shipping-tracking
published: 2026-10-12
updated: 2026-10-27
---

Amazon Shipping è Amazon che consegna pacchi per conto di altri negozi online: un e-commerce che non è Amazon può spedirti l'ordine con la rete di Amazon. Lo segui con il suo ID di tracciamento sul tracker pubblico di Amazon Shipping (track.amazon.it per i numeri italiani). Gli ordini fatti su Amazon.it invece si seguono da I miei ordini.

## Numero di tracking Amazon Shipping: com'è fatto

Le forme che Peek riconosce come numeri Amazon sono queste (cifre inventate):

| Forma | Che cos'è |
| --- | --- |
| `IT0000000000` | Un codice di Paese europeo e 10 cifre (`IT`, `CH`, `FR`, `DE`, `UK` e altri). |
| `TBA123456789012` | `TBA`, `TBC` o `TBM` e 12 cifre. |

Attenzione: Amazon Logistics, che consegna gli ordini fatti su Amazon, usa esattamente le stesse forme. Solo il tracker di Amazon Shipping le distingue, perché i pacchi di Amazon Logistics non li trova. Se il tracker non trova nulla e hai comprato su Amazon.it, leggi [il tracking di Amazon Logistics](carrier:amazon-logistics). Un numero come `000-0000000-0000000` è un numero d'ordine Amazon, non di tracciamento. Niente numero? Ce l'ha il negozio: vedi [dove trovare il codice tracking](guide:find-tracking-number).

## Pacco Amazon Shipping non consegnato, in ritardo o smarrito

Il tuo interlocutore è il negozio da cui hai comprato: il cliente di Amazon Shipping è lui. Nell'UE, secondo Your Europe, il commerciante è responsabile della consegna e di eventuali danni alla merce, dal momento della spedizione fino a quello della consegna.

**Consegnato ma non ricevuto.** Spetta al commerciante contattare il servizio di consegna e dimostrare che l'articolo è stato consegnato. Se non è in grado di farlo, deve inviarti un articolo identico o rimborsarti. Altri passi in [pacco consegnato ma non ricevuto](guide:delivered-not-received).

**Danneggiato.** Il commerciante risponde dei danni durante il trasporto: deve riparare o sostituire l'articolo, o rimborsarti.

**Smarrito o fermo.** Se non hai concordato un'altra data, la merce va consegnata entro 30 giorni. Se non arriva, sollecita il venditore e concedigli un'ulteriore proroga ragionevole. Se non la rispetta, puoi annullare l'ordine e avere il rimborso. Se è fermo solo il tracciamento, i motivi più comuni sono in [perché il tracking non si aggiorna](guide:tracking-not-updating).

## Amazon Shipping contatti e servizio clienti

Se il pacco non arriva o arriva danneggiato, il tuo interlocutore è il negozio, come spiegato sopra.

> Se ricevi un messaggio su un acquisto, Amazon consiglia di non rispondere e di non cliccare sui link. Amazon non ti chiede di pagare al telefono o via e-mail, né di dare al telefono i dati di pagamento, nemmeno per una carta regalo. Non chiamare numeri ricevuti via e-mail o SMS o trovati nei risultati di ricerca, e segnala i messaggi sospetti dalla pagina «Segnala una truffa» di Amazon. Altri consigli in [truffa pacco via SMS](guide:delivery-scam-texts).

## Tracciare un pacco Amazon Shipping con Peek

Incolla in [Peek](/) il numero o un link di track.amazon.it (`/tracking/` seguito dal numero). Peek chiede al tracker pubblico di Amazon se conosce il numero: per i numeri `IT` a track.amazon.it, per `ES` a track.amazon.es, per `UK` e `GB` a track.amazon.co.uk, per `TBA`, `TBC` e `TBM` a track.amazon.com, per `CH` e gli altri prefissi europei a track.amazon.fr. Se Amazon risponde, vedi «Il tracciamento pubblico è disponibile con Amazon Shipping. Peek terrà aggiornata questa consegna.» Se no, è una consegna Amazon Logistics, che di solito si segue da I miei ordini su Amazon. Se la verifica non riesce, premi «Riprova». Amazon Shipping non è nell'elenco di «Cambia corriere»: questa verifica è l'unica strada. Non serve il CAP.

Peek mostra lo stato, la cronologia con città, regione e Paese di ogni scansione, il giorno di consegna previsto e l'ora di consegna. Controlla ogni 10 minuti di giorno (ogni 2 quando il pacco è in consegna) e di notte ogni una o due ore, mette ogni scansione su una mappa e può mandarti notifiche quando lo stato cambia. Usa le sue etichette brevi in italiano al posto delle frasi di Amazon, tranne «Arrived at sorting center», «Arrived at delivery center» e «Departed facility», che restano in inglese. Lascia fuori il nome del negozio, il tuo nome e indirizzo, la firma e la foto di consegna. Amazon è l'unica fonte: quando smette di dare la cronologia, Peek lo dice e smette di controllare. Non segue il pacco se passa a un altro corriere.

## Domande sul tracking Amazon Shipping

### Amazon Shipping cos'è?

È il servizio con cui Amazon mette la sua rete di consegna dell'ultimo miglio a disposizione dei venditori che gestiscono da sé le spedizioni: compri su un altro sito e Amazon porta il pacco a te. Per i problemi ti rivolgi al negozio, non ad Amazon.it.

### Chi contatto se il pacco Amazon Shipping non arriva?

Il negozio da cui hai comprato: nell'UE, secondo Your Europe, il commerciante è responsabile della consegna e, se non dimostra di aver consegnato, deve inviarti un articolo identico o rimborsarti.

### In quanto tempo consegna Amazon Shipping?

Se il tracker di Amazon dà un giorno di consegna previsto, Peek lo mostra, tranne per i numeri `TBA`, `TBC` e `TBM`. Se con il negozio non hai concordato un'altra data, nell'UE l'ordine va consegnato entro 30 giorni, secondo Your Europe.

:::sources
- [About Amazon Italia: Lettera agli azionisti dal CEO Andy Jassy - 2024](https://www.aboutamazon.it/notizie/company-news/lettera-agli-azionisti-dal-ceo-andy-jassy-2024) – cos'è Amazon Shipping: la rete di consegna dell'ultimo miglio di Amazon per i venditori che gestiscono da sé le spedizioni
- [Your Europe: Spedizione e consegna](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_it.htm) – commerciante responsabile dei danni fino alla consegna, 30 giorni, proroga, rimborso
- [Your Europe: FAQ su spedizione e consegna](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/faq/index_it.htm) – commerciante responsabile della consegna, prova della consegna, articolo identico o rimborso, annullare l'ordine
- [About Amazon Italia: Come evitare le truffe basate sui furti di identità](https://www.aboutamazon.it/notizie/company-news/come-evitare-le-truffe-basate-sui-furti-di-identita-di-amazon-in-questo-periodo-festivo) – messaggi su un acquisto, pagamenti, numeri da non chiamare
- [About Amazon Italia: Un'alleanza pubblico-privato contro le truffe](https://www.aboutamazon.it/news/customer-trust/unalleanza-pubblico-privato-per-contrastare-le-truffe-basate-sui-furti-didentita) – dati di pagamento e carte regalo al telefono, Segnala una truffa
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – cosa riconosce e mostra Peek, quale tracker conosce quali numeri, ordini Amazon solo nell'account
:::
