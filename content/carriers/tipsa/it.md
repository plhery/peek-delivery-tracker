---
title: Tracking TIPSA: codice a 22 cifre, stati e consegne
description: Traccia un pacco TIPSA in Spagna o Portogallo: codice a 22 cifre, significato degli stati in spagnolo, consegne mancate, agenzie e contatti.
slug: tracking-tipsa
published: 2026-11-21
updated: 2026-11-21
---

TIPSA è un corriere espresso spagnolo parte del gruppo Geopost, con agenzie in Spagna, Portogallo e Andorra. Traccia il tuo pacco con il codice a 22 cifre sul [localizzatore spedizioni TIPSA](https://www.tip-sa.com/en/delivery-locator), oppure incollalo nel campo qui sopra. Il codice viene fornito dal negozio o dal mittente, di solito nell'e-mail o nell'SMS di spedizione. Se non lo hai, chiedilo al negozio o all'agenzia TIPSA che consegna nella tua zona, che trovi per CAP nel [trova agenzie](https://www.tip-sa.com/es/agencias-de-transporte). Con la stessa agenzia concordi la consegna dopo un `Ausente` o il ritiro diretto; in caso di smarrimento o danni, avvisa il negozio.

## Codice di tracking TIPSA: com'è fatto

Il localizzatore di TIPSA richiede il codice di spedizione a 22 cifre e indica come obbligatorio anche il CAP di destinazione. Il codice unisce due codici di agenzia seguiti dalla lettera di vettura (`albarán`):

:::anatomy 028145 028145 4829105736
- 028145 | Primo codice agenzia: un'agenzia TIPSA che gestisce la spedizione
- 028145 | Secondo codice agenzia: un'altra agenzia della spedizione
- 4829105736 | Lettera di vettura (albarán): il numero proprio della spedizione
:::

| Forma (inventata) | Cos'è |
| --- | --- |
| `0281450281454829105736` | Il codice TIPSA completo da 22 cifre. Usa questo. |
| `028145 028145 4829105736` | Lo stesso codice con spazi. Peek lo legge; il campo del localizzatore TIPSA accetta solo 22 caratteri, quindi lì va digitato senza spazi. |
| `4829105736` | Solo la lettera di vettura da 10 cifre. Né il localizzatore TIPSA né Peek lo accettano come codice TIPSA. |

I negozi possono far inviare da TIPSA e-mail e SMS alla partenza, all'arrivo in agenzia, in caso di anomalia e a consegna avvenuta, con link alla pagina della spedizione. Senza il codice a 22 cifre, il localizzatore cerca anche per riferimento, ma richiede un identificativo (`Agency + Client`) da chiedere al mittente. Altri suggerimenti in [come trovare il numero di tracciamento](guide:find-tracking-number).

Ventidue cifre corrispondono anche a Austrian Post, [USPS](carrier:usps) ed Estafeta. Un codice che inizia con `00` e ha anche il secondo codice agenzia che comincia con `00` può appartenere a [CTT Express](carrier:ctt-express), un altro corriere spagnolo: controlla quale corriere indica l'e-mail del negozio. Funziona solo la forma completa da 22 cifre, mai le sole 10 cifre finali.

## Significato degli stati del tracking TIPSA

La pagina di dettaglio TIPSA è solo in spagnolo, anche aprendo il localizzatore dal sito inglese. In alto mostra una barra sintetica (`Documentado`, `En camino`, `En reparto`, `Entregado`); sotto, una tabella elenca ogni scansione con data, ora e località. Le diciture nell'ordine abituale:

| Stato TIPSA | Cosa significa per te |
| --- | --- |
| `PENDIENTE DE ENTREGAR A TIPSA` | Il negozio ha registrato la spedizione, ma TIPSA non ha ancora il pacco: è uno stato di [etichetta creata](guide:tracking-statuses). |
| `TRANSITO` | In viaggio tra agenzie e hub di TIPSA. |
| `LECTURA EN HUB MADRID` | Scansionato in un hub TIPSA; il nome cambia secondo la sede (`LECTURA EN HUB BARCELONA`, `LECTURA EN HUB BARBERA`). |
| `Pendiente de llegada` | Non ancora arrivato all'agenzia che effettuerà la consegna. |
| `LEIDO EN DESTINO` | Scansionato a destinazione. |
| `LECTURA EN AGENCIA DESTINO` | Scansionato dall'agenzia di consegna, seguito dal nome dell'agenzia. |
| `REPARTO` | In consegna. |
| `Ausente` | Il corriere non è riuscito a consegnare perché non ha trovato nessuno. |
| `Faltan Datos / Datos incorrectos` | Mancano dati dell'indirizzo o sono errati; annotato sul giro di consegna prima di un nuovo tentativo. |
| `Cerrado` | Indirizzo trovato chiuso durante il giro, dopo `REPARTO`. |
| `Solicitada en otra dirección` | È stata richiesta la consegna a un altro indirizzo e il pacco viene reindirizzato. |
| `DISPONIBLE` o `Pendiente de recoger en Tipsa` | Dopo una consegna mancata, il pacco ti aspetta in agenzia. |
| `Devolver a Origen` | In rientro al mittente. |
| `ENTREGADO` | Consegnato. |

`Ausente` include una nota di TIPSA: non è stato possibile contattarti, verrà fatto un nuovo tentativo il giorno lavorativo successivo e puoi chiamare l'agenzia di destinazione per concordare la consegna. Nome e indirizzo compaiono sotto «AGENCIA DESTINO».

## Consegna mancata, agenzie e punti di ritiro TIPSA

TIPSA consegna tramite rete propria in Spagna continentale e Portogallo, Baleari, Canarie, Ceuta e Melilla, e all'estero tramite DPDgroup. Dopo un `Ausente` il secondo tentativo avviene il giorno lavorativo successivo; la tariffa per i negozi include due tentativi, oppure tre con il servizio Premium per e-commerce.

**Ritiro in agenzia.** Se sei assente o preferisci ritirarlo di persona, le FAQ di TIPSA indicano che il pacco rimane nell'agenzia più vicina al tuo CAP per 15 giorni dalla spedizione, non dall'arrivo. Trascorso quel termine torna al mittente. Porta il documento d'identità (DNI) o il passaporto; chi ritira per te deve presentare una fotocopia del tuo documento. Il [trova agenzie](https://www.tip-sa.com/es/agencias-de-transporte) cerca per provincia o CAP.

**Cambio indirizzo.** Secondo TIPSA solo il mittente può autorizzare una modifica dell'indirizzo o dei dati della spedizione: chiedilo al negozio.

**Punti di ritiro e locker.** TIPSA condivide con [SEUR](carrier:seur) una rete di punti Pickup in negozi e locker attivi 24 ore su 24, 7 giorni su 7. La maggior parte dei punti apre almeno sei giorni alla settimana e hai 7 giorni per ritirare il pacco. La consegna presso un punto dipende dalle opzioni offerte dal negozio. Trova un punto nel [localizzatore Pickup](https://aplicaciones.tip-sa.com/puntospickup/).

Alcuni negozi attivano anche la consegna di sabato o il preavviso via SMS ed e-mail che annuncia il giorno di arrivo a destinazione. Con il servizio Premium è possibile scegliere una fascia oraria con margine di un'ora; poiché TIPSA non precisa online dove sceglierla, chiedi all'agenzia di destinazione.

**Pagamento alla consegna.** Con il contrassegno (`contrarreembolso`) TIPSA riscuote alla consegna l'importo fissato dal negozio, fino a 1.000 € per spedizione; con il porto assegnato (`portes debidos`) riscuote le spese di spedizione a destinazione. TIPSA non specifica se si paga in contanti o con carta: chiedi al negozio o all'agenzia. Poiché entrambi si pagano alla consegna, un SMS o un'e-mail «da TIPSA» che chiede di pagare tramite un link non corrisponde né all'uno né all'altro: non pagare e controlla il codice sul localizzatore di tip-sa.com. Vedi [SMS truffa sulle consegne](guide:delivery-scam-texts).

## Pacco TIPSA in ritardo, smarrito o restituito

Le pagine di TIPSA non trattano i pacchi il cui tracciamento si ferma. Chiama l'agenzia di destinazione indicata sulla pagina della spedizione oppure chiedi al negozio; [perché il tracking non si aggiorna](guide:tracking-not-updating) aiuta a capire quando intervenire.

Per smarrimento o danni, le FAQ di TIPSA sono sintetiche: contatta il mittente per avvisarlo. Il contratto con TIPSA è del negozio, che presenta il reclamo; TIPSA non pubblica procedure né termini di reclamo per i destinatari. Manca anche una pagina per i pacchi segnati `ENTREGADO` ma mai arrivati: avvisa il negozio anche in quel caso.

Un pacco non ritirato entro 15 giorni dalla spedizione torna al mittente: accordati con il negozio per rimborso o nuova spedizione.

## Contatti e assistenza clienti TIPSA

I canali indicati sulla pagina contatti e nelle FAQ di TIPSA:

- **Telefono:** `902 10 10 47` o `900 10 10 47` (le FAQ spagnole indicano solo `900 10 10 47`). TIPSA non riporta orari di apertura né costi di chiamata.
- **Modulo di contatto** su [tip-sa.com/en/contact](https://www.tip-sa.com/en/contact), selezionando «I have a problem with my shipment» e inserendo il numero della spedizione.
- **E-mail:** `info@tip-sa.com`, indicato nelle FAQ se manca il numero di spedizione.
- **L'agenzia di destinazione**, riportata sulla pagina della spedizione, per concordare la consegna.

Non esiste una linea dedicata al Portogallo: la pagina contatti portoghese di TIPSA riporta gli stessi due numeri. TIPSA pubblica anche una pagina sulla propria app su [tip-sa.com/en/recursos/app](https://www.tip-sa.com/en/recursos/app).

## Tracciare un pacco TIPSA con Peek

[Peek](/) accetta il codice a 22 cifre con o senza spazi. Poiché altri corrieri condividono questa lunghezza, Peek interroga contemporaneamente i candidati, TIPSA per primo: vedrai «verifica presso …» e poi «TIPSA ha questo pacco.» quando TIPSA lo riconosce. Un numero in cui entrambi i codici agenzia iniziano con `00` e il secondo corrisponde a una delle filiali elencate per CTT Express viene assegnato direttamente a CTT Express; per gli altri codici `00…00…`, Peek interroga TIPSA (preferito), Austrian Post, CTT Express, USPS ed Estafeta. Se il negozio ha indicato TIPSA, premi «Cambia» e selezionalo. Non serve il CAP.

La lettera di vettura da 10 cifre non può essere seguita tramite TIPSA, nemmeno con «Cambia» o da un link. Peek non suggerisce TIPSA per 10 cifre; scegliendo TIPSA a mano, segnala che TIPSA «non emette numeri come questo» e interroga i servizi di tracciamento. Cerca il codice completo a 22 cifre o il link nell'e-mail o SMS del negozio. Peek legge i link `tip-sa.com` con `id=` su `datos_env.php`, `datos_prestashop.php`, `datos.php` e sulla home `tip-sa.com`, ma non i link al localizzatore né quelli su `tipsa-dinapaq.com`.

Peek mostra lo stato, la cronologia con la località di ogni scansione sulla mappa, il nome del mittente (se non oscurato da TIPSA) e il peso. Le diciture restano nello spagnolo di TIPSA e Peek le riassume nelle proprie fasi: `PENDIENTE DE ENTREGAR A TIPSA` («Etichetta creata»), `REPARTO` («In consegna», inclusa una scansione di agenzia lo stesso giorno dopo `REPARTO`), `Ausente`, `Cerrado` e `Faltan Datos / Datos incorrectos` («Tentativo di consegna»), `DISPONIBLE` e `Pendiente de recoger en Tipsa` («Pronto per il ritiro»), `Devolver a Origen` («Problema segnalato»), `ENTREGADO` («Consegnato») e le altre scansioni («In transito»). Peek legge tutti gli orari sull'ora di Madrid, agenzie portoghesi incluse, e li mostra nel tuo fuso orario; su peektracker.com e nelle app iPhone e Android la cronologia si chiude con «Orari nel tuo fuso orario:» e il suo nome. Scarta destinatario, indirizzo, CAP, riferimento, numero di colli, volume e prova di consegna, non mostra una data di consegna prevista e non segue il passaggio ad altri corrieri.

Finché tieni il pacco aperto sullo schermo o nel giorno della consegna con le notifiche attive, e mentre risponde il tracciamento di TIPSA, Peek controlla fino a ogni 10 minuti, e fino a ogni 2 minuti dalle 6:00 ora di Madrid nel giorno in cui TIPSA registra `REPARTO`; altrimenti ogni ora. Tramite i servizi di tracciamento controlla ogni ora, e fino a ogni 15 minuti il giorno della consegna finché lo tieni aperto o hai le notifiche attive. Dopo due giorni senza novità (12 ore finché resta su «Etichetta creata» o «Preso in carico»), Peek controlla ogni ora. Può avvisarti per «Ogni scansione», «Passaggi importanti» o «Solo la consegna».

## Domande sul tracking TIPSA

### Che cos'è TIPSA in Spagna?

TIPSA sta per Transporte Integral de Paquetería, S.A., corriere espresso spagnolo del gruppo Geopost. La pagina di presentazione indica oltre 330 agenzie (oltre 300 nella home portoghese) e 13 hub, tra cui Madrid, Barcellona e Valencia, con spedizioni internazionali tramite DPDgroup.

### Quanto tempo impiega TIPSA per consegnare?

Dipende dal servizio scelto dal negozio. TIPSA vende ai negozi consegne in 24 ore (un tentativo il giorno lavorativo successivo) o entro 48 ore; in Portogallo, 24 ore nelle principali città e 24-48 ore nel resto del Paese.

### Posso tracciare un pacco TIPSA con 10 cifre?

No: il localizzatore di TIPSA richiede il codice da 22 cifre, e nemmeno Peek accetta 10 cifre come numero TIPSA. Le 10 cifre sono solo la parte finale della lettera di vettura: chiedi il codice completo al mittente.

### TIPSA dispone di punti di ritiro?

Sì, punti Pickup presso negozi e locker condivisi con SEUR, dove il pacco resta 7 giorni. L'opzione è disponibile solo se il negozio la propone al momento dell'ordine.

:::sources
- [TIPSA: Delivery locator](https://www.tip-sa.com/en/delivery-locator) – codice a 22 cifre, modulo, ricerca per riferimento con identificativo (agenzia + cliente)
- [TIPSA: Frequently Asked Questions](https://www.tip-sa.com/en/recursos/faqs) – numero mancante, giacenza di 15 giorni, documento, cambio indirizzo, smarrimento o danni, telefoni, e-mail
- [TIPSA: Contact](https://www.tip-sa.com/en/contact) – telefoni, motivi del modulo
- [TIPSA: Collection points](https://www.tip-sa.com/en/services/collection-points) – rete Pickup
- [TIPSA: Pre-alert](https://www.tip-sa.com/en/services/pre-alert) – preavviso SMS ed e-mail, tempi in Portogallo
- [TIPSA: TIPSA APP](https://www.tip-sa.com/en/recursos/app) – pagina dell'app
- [TIPSA: Consulta de Envíos](https://dinapaqweb.tipsa-dinapaq.com/dinapaqweb/detalle_envio.php) – pagina spedizione solo in spagnolo, diciture e agenzia di destinazione
- [TIPSA: Localizar mi envío](https://www.tip-sa.com/es/localizacion-envios) – localizzatore spagnolo, telefono nel piè di pagina
- [TIPSA: Preguntas frecuentes](https://www.tip-sa.com/es/recursos/faqs) – copertura, numero `900 10 10 47`
- [TIPSA: Empresa de transporte urgente](https://www.tip-sa.com/es/tipsa) – Geopost, oltre 330 agenzie, 13 hub, locker
- [TIPSA: Página inicial (Portugal)](https://www.tip-sa.com/pt) – oltre 300 agenzie
- [TIPSA: FAQs eTIPSA](https://www.tip-sa.com/es/faqs-etipsa) – due tentativi, 24 e 48 ore, fascia oraria eTIPSA Premium, sabato, notifiche negozi
- [TIPSA: 360º eCommerce](https://www.tip-sa.com/es/envios-de-paquetes/360o-ecommerce) – tre tentativi con Premium
- [TIPSA: Recogida en puntos de recogida](https://www.tip-sa.com/es/envios-de-paquetes/puntos-de-recogidas) – rete condivisa con SEUR, 7 giorni, giorni di apertura
- [TIPSA: Agencias de transporte](https://www.tip-sa.com/es/agencias-de-transporte) – trova agenzie
- [TIPSA: Puntos Pickup](https://aplicaciones.tip-sa.com/puntospickup/) – localizzatore punti Pickup
- [TIPSA: Contrarreembolso](https://www.tip-sa.com/es/envios-de-paquetes/reembolso) – incasso alla consegna, fino a 1.000 € per spedizione
- [TIPSA: Portes debidos](https://www.tip-sa.com/es/envios-de-paquetes/portes-debidos) – spese di spedizione riscosse a destinazione
- [TIPSA: Aviso legal](https://www.tip-sa.com/es/tipsa-aviso-legal) – ragione sociale
- [TIPSA: Contacto (Portugal)](https://www.tip-sa.com/pt/contacto) – stessi numeri per il Portogallo
- [Universal Parcel Scraper: TIPSA](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/tipsa) – formato a 22 cifre, link letti, fuso di Madrid, mappatura stati (`Cerrado`, `Devolver a Origen`, scansione agenzia dopo `REPARTO`), campi mostrati ed esclusi
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – sovrapposizione con CTT Express, Austrian Post, USPS ed Estafeta
:::
