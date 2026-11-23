---
title: Tracciamento euShipments: stati e corriere locale
description: Traccia un ordine euShipments: provenienza del codice, significato di New, Loaded hub e On delivery, corriere locale e contatti.
slug: tracciamento-eushipments
published: 2026-11-22
updated: 2026-11-22
---

euShipments è una società di logistica e-commerce con sede a Ruse, in Bulgaria. Stocca, imballa e spedisce gli ordini dei negozi online verso i 28 paesi del suo elenco «We deliver to» — i 27 dell’UE e il Regno Unito — affidando poi ogni pacco a un corriere locale per l’ultimo miglio. Puoi tracciare il codice sulla pagina «Track my order», [eushipments.com/tracking](https://eushipments.com/tracking), oppure incollarlo nel riquadro sopra. Il codice è quello inviato dal negozio.

## Numero di tracciamento euShipments

euShipments non pubblica un formato fisso. Spiega che, una volta elaborato l’ordine, ricevi «tracking code, AWB number or tracking ID» da inserire dove il modulo recita «Enter your tracking number to track packages.» Il tracciamento trova l’ordine con il numero inviato dal negozio, che può coincidere con quello del corriere finale (con cifre inventate):

| Numero | Che cos’è |
| --- | --- |
| `74829103` | Otto cifre: l’unico formato visto da Peek sul portale euShipments, per un ordine consegnato in Bulgaria con lo stesso codice dal corriere locale. |
| Il numero dopo «Tracking Number:» | Il codice del corriere locale, mostrato nel risultato di euShipments appena il corriere prende in carico il pacco. Il tracciamento di quel corriere lo riconosce. |

Otto cifre corrispondono anche a [Mondial Relay](carrier:mondial-relay), Colisweb e [Heppner](carrier:heppner), quindi il codice da solo non prova che si tratti di euShipments: verifica il nome indicato nell’e-mail del negozio. Un link alla pagina di tracciamento di euShipments non apre l’ordine, perché accetta il codice solo tramite modulo. Nessun numero? Vedi [come trovare un numero di tracciamento](guide:find-tracking-number).

## Stati di tracciamento euShipments spiegati

Il risultato mostra «My order N» con il codice, seguito da una tabella a tre colonne: «Date/Time», «Tracking Status» e «Tracking Information». Lo stato usa le diciture inglesi di euShipments, uguali qualunque sia il corriere locale. «Tracking Information» riporta accanto i testi del corriere non appena ha il pacco. Ogni riga è datata al minuto, come «10 August, 2026 15:39», senza fuso orario.

| euShipments indica | Cosa significa per te |
| --- | --- |
| `New` | Il negozio ha inserito l’ordine presso euShipments. Potrebbe non essere ancora imballato. |
| `Warehouse` | L’ordine è nel magazzino logistico di euShipments indicato dopo la parola. |
| `Loaded hub` | Caricato in un hub di euShipments verso quello successivo. Segue il nome dell’hub con il paese tra parentesi, ad esempio (HR). |
| `Unloaded hub` | Scaricato in un hub, indicato allo stesso modo. |
| `Picked up` | Il corriere locale ha preso in carico il pacco. |
| `In transit` | In transito nella rete del corriere. |
| `On delivery` | In consegna con il corriere. |
| `Delivered` | Consegnato. È lo stato finale dell’ordine. |

`Loaded hub` e `Unloaded hub` possono comparire più volte, per ogni hub lungo il percorso. Dopo `Picked up`, sotto la tabella compaiono tre righe: «Courier Name:», «Tracking Number:» e «Tracking Link:», cioè il corriere che consegna, il suo codice e il link al suo tracciamento.

Peek non ha osservato stati propri di euShipments per consegna mancata, giacenza in punto di ritiro o reso. In questi casi leggi il testo del corriere in «Tracking Information» o apri il suo link.

## Chi consegna un pacco euShipments nel tuo paese

euShipments affida l’ultimo miglio a corrieri locali. Trasporta i colli via linea dai propri centri di fulfillment e cross-docking (Sofia e Ruse in Bulgaria, Oradea in Romania, Zagreb in Croazia, Lubiana in Slovenia, Senec in Slovacchia e Budapest in Ungheria) al deposito di un corriere locale. Le FAQ indicano oltre 60 corrieri locali, almeno due in ogni paese europeo, e l’elenco «We deliver to» copre 28 paesi: i 27 dell’UE e il Regno Unito.

La home page mostra i loghi di partner chiave («Key eCommerce partnerships»), tra cui Speedy, Sameday, [FAN Courier](carrier:fan-courier), Express One, GLS, DPD, BOX NOW, Pošta Slovenije, [Slovenská pošta](carrier:slovenska-posta), PPL, ACS e [Geniki Taxydromiki](carrier:geniki-taxydromiki), senza indicare quale consegna dove. La storia aziendale cita una partnership strategica con Austrian Post nel 2026 senza precisarne il perimetro. Quale corriere consegna il tuo pacco dipende dal paese e dal metodo scelto al checkout: lo mostra «Courier Name:».

**Dove può essere consegnato.** In Bulgaria euShipments consegna a domicilio o in azienda, oppure fuori casa: filiale del corriere, punto di ritiro (PUDO) o locker. Le pagine per Croazia e Romania elencano opzioni analoghe. Conta oltre 150.000 punti fuori casa nella propria rete e precisa che, quando il pacco è pronto per il ritiro, ricevi una notifica con le istruzioni.

**Tentativi, giacenza e riconsegna.** Valgono le regole del corriere locale: euShipments non ne pubblica alcuna. Usa «Tracking Link:» e «Tracking Number:» per consultare la pagina del corriere e contattarlo per un nuovo tentativo o un cambio indirizzo.

**Contrassegno.** euShipments offre il pagamento alla consegna in diversi paesi europei: il corriere locale riscuote l’importo, che poi va al negozio.

## Pacco euShipments fermo, smarrito o restituito

**Fermo su `New`.** Il negozio ha inviato l’ordine a euShipments, ma non c’è ancora nessuna scansione di magazzino o hub. Chiedi al negozio quando spedirà.

**Fermo tra due hub.** Dopo `Loaded hub` il pacco è su strada verso l’hub successivo e la riga seguente appare di solito allo scarico. Se resta fermo oltre la stima del negozio, contatta il negozio. Vedi [perché il tracciamento non si aggiorna](guide:tracking-not-updating).

**Fermo dopo `Picked up`.** Il pacco è presso il corriere locale. Apri «Tracking Link:» e controlla il suo tracciamento con il suo numero.

**Consegnato ma non arrivato.** La consegna è stata eseguita dal corriere locale: rivolgiti prima al corriere, poi al negozio. Vedi [consegnato ma non ricevuto](guide:delivered-not-received).

**Restituito al mittente.** La pagina resi di euShipments considera non consegnata una spedizione impossibile da recapitare per dati errati del destinatario o problemi di contatto: torna al magazzino euShipments o al negozio, non a te. Chiedi al negozio una nuova spedizione o un rimborso. Altro in [restituito al mittente](guide:returned-to-sender).

**Fare un reso da soli.** euShipments gestisce i resi per i negozi in tre modi: richiesta sul sito del negozio scegliendo giorno, ora e luogo per il ritiro; ritiro prenotato dal negozio; oppure invio autonomo con qualsiasi corriere all’indirizzo locale di reso indicato dal negozio (un magazzino euShipments). Le condizioni di reso del negozio indicano la modalità valida. Vedi [tracciare un reso](guide:returns-tracking).

**Smarrito o danneggiato.** euShipments non pubblica procedure di reclamo per destinatari: il contratto è in capo al negozio. Nell’UE il venditore è responsabile dei danni dalla spedizione fino alla ricezione e deve consegnare entro 30 giorni salvo diverso accordo. In caso contrario sollecita il negozio concedendo un termine supplementare ragionevole; se ancora non consegna, puoi annullare e ottenere il rimborso. Vedi [pacco smarrito](guide:lost-parcel) e [pacco danneggiato](guide:damaged-parcel).

## Numero di telefono e assistenza euShipments

euShipments pubblica questi recapiti:

- **Telefono:** +359 700 70 755, numero bulgaro presente sulle pagine contatti, FAQ e tracciamento. Non indica orari.
- **E-mail:** office@eushipments.com.
- **Modulo di contatto:** un questionario di preventivo per negozi (volumi mensili, mercati), non pensato per i destinatari.

Le FAQ per i negozi indicano che il supporto telefonico, e-mail e live chat per i destinatari è disponibile su richiesta («when requested»). Inizia quindi dal negozio; per la consegna vera e propria, il corriere indicato sotto «Courier Name:» è più rapido.

## Tracciare un pacco euShipments con Peek

[Peek](/) non può riconoscere un codice euShipments dalla forma: non esiste una regola di formato per euShipments e un codice di 8 cifre corrisponde debolmente ad altri corrieri. Incollalo comunque: Peek segue oltre 4.500 corrieri. Premi poi «Cambia» e seleziona «euShipments», oppure usa «Cambia corriere» su un pacco già aggiunto.

Peek legge il tracciamento di euShipments. Mostra stato, cronologia e, per le righe di magazzino e hub, il luogo (con il paese per gli hub). Mantiene le diciture inglesi di euShipments, tranne «In transit» e «Delivered», mostrate come «In transito» e «Consegnato», ed esclude il testo del corriere da «Tracking Information». Quando «Tracking Link:» punta a un corriere seguito da Peek — come [Econt](carrier:econt), Sameday, [FAN Courier](carrier:fan-courier), [Packeta](carrier:packeta), [Slovenská pošta](carrier:slovenska-posta) o [Geniki Taxydromiki](carrier:geniki-taxydromiki) — con il suo numero nel link, Peek mostra «Consegna con …» e quel numero se diverso dal tuo. Finché quel corriere ha solo l’annuncio senza una scansione propria, appare «Annunciato a …, non ancora ricevuto». Quando mostra un evento datato almeno recente quanto l’ultima riga di euShipments, Peek passa a seguirlo lì conservando le righe precedenti; altrimenti rimane su euShipments.

euShipments non fornisce data prevista di consegna né prova di consegna, perciò Peek non le mostra. Gli orari senza fuso orario sono letti sull’ora del paese dell’hub per le righe di hub (ora bulgara per un hub in Bulgaria), su quella di una riga di hub vicina per le altre, o sull’ora bulgara se non ce n’è nessuna, e convertiti nel tuo fuso orario («Orari nel tuo fuso orario:»). Peek controlla fino a ogni 10 minuti, e ogni 2 minuti dalle 6:00 secondo l’orologio dell’ultima scansione il giorno in cui entra «In consegna», mentre il pacco è aperto in Peek o nel giorno di consegna con le notifiche attive, quando il portale di euShipments risponde. Dopo due giorni senza novità (12 ore finché resta su «Etichetta creata» o «Preso in carico»), controlla ogni ora, e dopo sette giorni senza novità su «Etichetta creata», ogni 6 ore. Per le notifiche puoi scegliere «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Dopo quattro giorni senza scansioni su un invio in «Etichetta creata», «Preso in carico» o «In transito», Peek mostra «Nessun aggiornamento da …», la nota «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a euShipments.» e «Chiedi aiuto a euShipments». Peek non può contattare euShipments, il negozio né il corriere per te.

## Domande sul tracciamento euShipments

### euShipments è affidabile?

Sì. È un’azienda logistica di Ruse, in Bulgaria, fondata nel 2012 come InOut Trade e rinominata euShipments.com nel 2019. Spedisce per negozi online, compresi venditori su Temu ed eMAG grazie a integrazioni dedicate.

### Quale corriere consegna il mio pacco euShipments?

Un corriere locale nel tuo paese. Appena ha il pacco, il risultato di euShipments mostra «Courier Name:», «Tracking Number:» e «Tracking Link:» sotto la tabella.

### Quanto impiega euShipments a consegnare?

Dipende dal magazzino in cui si trova la merce. Da Sofia e Ruse euShipments indica consegna il giorno successivo in Bulgaria e Romania e 2–4 giorni verso Croazia, Slovenia, Slovacchia e Repubblica Ceca. Da Zagabria, Lubiana o Senec indica 1 giorno lavorativo nel rispettivo paese. Fai riferimento alla stima del negozio. Altro in [tempi di consegna](guide:delivery-times).

### Cosa significa Loaded hub su euShipments?

Il pacco è stato caricato in un hub di euShipments per la tratta successiva, indicato con il paese tra parentesi. `Unloaded hub` è l’arrivo all’hub successivo.

### In quali paesi consegna euShipments?

Il suo elenco «We deliver to» comprende 28 paesi: i 27 dell’UE e il Regno Unito. In ciascuno un corriere locale effettua la consegna finale.

:::sources
- [euShipments: Track Your Order](https://eushipments.com/tracking) – modulo di tracciamento e testi, telefono, e-mail
- [euShipments: Parcel Tracking For End Customers](https://eushipments.com/ecommerce-delivery/new-parcel-tracking/parcel-tracking-for-end-customers) – tracking code, AWB o tracking ID, oltre 60 partner dell’ultimo miglio
- [euShipments: We deliver to](https://eushipments.com/we-deliver-to) – i 28 paesi elencati: i 27 dell’UE e il Regno Unito
- [euShipments: FAQ](https://eushipments.com/faq) – oltre 60 corrieri, almeno due per paese, supporto ai destinatari su richiesta, telefono, e-mail
- [euShipments: Contact Us](https://eushipments.com/contact) – telefono, e-mail, Ruse, modulo di preventivo per negozi
- [euShipments: About us](https://eushipments.com/about-us) – InOut Trade, 2012, cambio nome 2019, partnership con Austrian Post
- [euShipments: Home](https://eushipments.com/) – loghi dei partner chiave dell’e-commerce
- [euShipments: eCommerce Deliveries To Bulgaria](https://eushipments.com/countries/bulgaria) – Sofia e Ruse, trasporto al deposito del corriere, consegna a domicilio e fuori casa, tempi, contrassegno
- [euShipments: International eCommerce Delivery To Romania](https://eushipments.com/countries/romania) – Oradea, Budapest, opzioni di consegna
- [euShipments: Domestic and International eCommerce Delivery To Croatia](https://eushipments.com/countries/croatia) – Zagabria e altri centri, consegna il giorno dopo, locker, punti di ritiro, filiali
- [euShipments: International Parcel Delivery To Slovenia](https://eushipments.com/countries/slovenia) – Lubiana, 1 giorno lavorativo
- [euShipments: International Delivery To Slovakia](https://eushipments.com/countries/slovakia) – Senec, 1 giorno lavorativo
- [euShipments: Out-of-home Delivery](https://eushipments.com/ecommerce-delivery/out-of-home-delivery) – 150.000 punti, notifiche di ritiro
- [euShipments: Returns Management](https://eushipments.com/returns-management) – spedizioni non consegnate, tre modalità di reso
- [euShipments: Temu Integration](https://eushipments.com/integration/ecommerce-platform-integration/temu) – venditori Temu
- [euShipments: eMAG Integration](https://eushipments.com/integration/ecommerce-platform-integration/emag-integration) – venditori eMAG
- [Your Europe: Shipping and delivery](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_en.htm) – 30 giorni, responsabilità del venditore fino alla ricezione, sollecito e rimborso
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – etichette della pagina risultati, stati, numeri, cosa legge e mostra Peek
:::
