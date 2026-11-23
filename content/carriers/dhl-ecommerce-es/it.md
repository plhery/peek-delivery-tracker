---
title: Tracciamento DHL eCommerce Iberia: stati e ServicePoint
description: Traccia un pacco DHL eCommerce in Spagna o Portogallo: significato degli stati, mancate consegne, ServicePoint, CTT Express e assistenza.
slug: tracciamento-dhl-ecommerce-iberia
published: 2026-11-22
updated: 2026-11-22
---

DHL eCommerce Iberia è la rete pacchi di DHL in Spagna e Portogallo, in passato DHL Parcel Iberia — il nome DHL Parcel compare ancora qua e là. Incolla il numero nel campo sopra per tracciare il pacco; nella sua scheda, «Apri il sito DHL eCommerce Iberia» apre la stessa spedizione sul tracciamento DHL. Il numero viene fornito dal negozio che ha spedito l’ordine.

## Numero di tracciamento DHL eCommerce: dove trovarlo e com’è fatto

Nessun codice? Chiedi al negozio oppure consulta [dove trovare il numero di tracciamento](guide:find-tracking-number).

I formati noti al catalogo di Peek (esempi inventati):

| Formato | Esempio | A chi può appartenere |
| --- | --- | --- |
| 10 cifre | `4829173649` | DHL eCommerce Iberia, ma anche [DHL Express](carrier:dhl-express), [Mondial Relay](carrier:mondial-relay), Relais Colis e GLS Spagna |
| Due lettere, 7 cifre, poi `0` | `KL48291730` | DHL eCommerce Iberia (e [TNT Italia](carrier:tnt-it)) |
| `JJD000061`, `JJD000268`, `JJD000269` o `JJD000368`, poi 14 cifre | `JJD00006148291736482917` | DHL eCommerce Iberia |

Se l’e-mail del negozio cita [Mondial Relay](carrier:mondial-relay) o GLS Spagna per un codice a 10 cifre, traccialo lì. Due lettere, nove cifre ed `ES` identificano un numero [Correos](carrier:correos-spain).

## Significato degli stati di tracciamento DHL eCommerce

Peek richiede a DHL le diciture in inglese, anche se qualche scansione arriva ancora in spagnolo, come `Llegada a`, `Tránsito en`, `Salida de` ed `Entregado`. Le diciture abituali, in ordine di comparsa:

| DHL indica | Cosa significa per te |
| --- | --- |
| `Shipment data received` | Il mittente ha registrato la spedizione. DHL non ha ancora il pacco. |
| `Picked up` | DHL ha ritirato il pacco dal mittente; segue la città. |
| `Shipment received at DHL Depot` | Il pacco è arrivato in un deposito DHL. |
| `Arrived at` | In transito tra depositi; segue la città. `In transit in` e `Departed from` funzionano allo stesso modo. |
| `Out for delivery` | Sul furgone per la consegna oggi. |
| `The delivery could not be made due to consignee absent` | Nessuno ha risposto. Vedi sotto per i passi successivi. |
| `Delivered to DHL ServicePoint, ready for collection by the recipient` | Pronto per il ritiro in un ServicePoint. |
| `Parcel picked up by recipient` | Ritirato al ServicePoint. |
| `Delivered` | Consegnato all’indirizzo. |
| `Shipment being returned to sender` | Reso al mittente in corso. |

Alcune diciture richiedono attenzione:

- `Delivered, POD pending`: segnato consegnato ma prova di consegna non ancora caricata. Può apparire in anticipo: potrebbe ancora seguire un giro di consegna o un tentativo fallito.
- `Shipment with incidence If you are the sender, please contact DHL Parcel, if you are the recipient please contact the sender`: si è verificata un’anomalia. Come dice la nota stessa, il destinatario deve rivolgersi al mittente: contatta il negozio.
- `The expected delivery date might be postponed one working day` e `Not delivered today: delivery expected for the next working day` indicano brevi rinvii, non uno smarrimento.

## Mancata consegna e ServicePoint

**Nessuno in casa.** Un tentativo non riuscito appare come `The delivery could not be made due to consignee absent`, oppure `Receiver closed: delivery not possible` se l’indirizzo risultava chiuso. Scansioni successive come `New delivery date requested` o `Delivery agreed with recipient` indicano una nuova data chiesta o concordata. Se compare `Incorrect address details: delivery not possible`, i dati dell’indirizzo non hanno consentito la consegna: Peek mostra «Problema segnalato» e tu o il mittente dovete correggerlo prima che il pacco riparta.

**ServicePoint.** `Parcel will be delivered to a DHL ServicePoint` e `Parcel is on it's way to our DHL ServicePoint` indicano che il pacco sta andando in un punto DHL ServicePoint. Quando è pronto lì, Peek mostra una scheda «Punto di ritiro» con nome e indirizzo del negozio, indicazioni stradali e pulsante per copiare l’indirizzo. Peek non riceve gli orari di apertura: verificali, insieme ai giorni di giacenza, presso DHL.

## Pacco in ritardo, perso o danneggiato

**Nessun aggiornamento.** Vedi [perché il tracciamento si ferma](guide:tracking-not-updating).

**Perso o danneggiato.** Inizia dal negozio: la stessa dicitura `Shipment with incidence` di DHL invita il destinatario a rivolgersi al mittente. Nell’UE, Your Europe ricorda che il venditore risponde dei danni fino alla consegna. Senza data concordata, deve consegnare entro 30 giorni; in mancanza, sollecitalo con un termine supplementare ragionevole, scaduto il quale puoi annullare e ottenere il rimborso. Vedi [pacchi persi](guide:lost-parcel) e [pacchi danneggiati](guide:damaged-parcel).

**Dogana.** Per un pacco da fuori UE consulta la [guida alla dogana](guide:customs).

**CTT Express.** DHL eCommerce e CTT hanno firmato una joint venture il 12 maggio 2026: in Portogallo l’attività di DHL eCommerce confluisce in CTT Expresso, e in Spagna [CTT Express](carrier:ctt-express) rileva le consegne ai privati mentre DHL mantiene le consegne alle aziende. Alcuni pacchi portano un codice CTT Express: tracciali con [CTT Express](carrier:ctt-express).

> Un SMS o un’e-mail sospetti su un pacco DHL? Leggi [come riconoscere i falsi messaggi di consegna](guide:delivery-scam-texts).

## Contattare DHL eCommerce

Cerca i recapiti di DHL eCommerce sul sito DHL del paese di consegna, Spagna o Portogallo. Per pacchi persi o danneggiati contatta prima il negozio.

## Tracciare un pacco DHL eCommerce Iberia con Peek

[Peek](/) segue oltre 4.500 corrieri. Per due lettere, 7 cifre e `0` interroga DHL eCommerce Iberia e [TNT Italia](carrier:tnt-it), le cui lettere di vettura hanno lo stesso formato. Per 10 cifre interroga DHL eCommerce Iberia, [Packeta](carrier:packeta) e BOX NOW, e DHL Freight (dal Portogallo o senza paese noto solo quando gli altri non conoscono il numero), più DHL Express per primo se supera la cifra di controllo (insieme ad ACS Courier e [Geniki Taxydromiki](carrier:geniki-taxydromiki), che superano lo stesso controllo); se anche DHL Express riconosce il numero, prevale DHL Express. Se più marchi lo conoscono o nessuno lo conosce, scegli con «Cambia corriere». Un codice `JJD` della forma in tabella viene assegnato subito a DHL eCommerce Iberia («Corriere rilevato»). Altri numeri DHL (12 o 22 cifre, altri `JJD`, numeri di tracciamento postali) funzionano solo selezionando manualmente «DHL eCommerce Iberia». Non occorre il codice postale.

Si possono incollare anche i link della pagina di tracciamento DHL eCommerce Iberia in spagnolo, portoghese o inglese (`/LiveTracking/ModificarEnvio/`, `ModificarMeuEnvio` o `ModifyMyShipment`): rilevano subito DHL eCommerce Iberia qualunque sia il formato, ignorando l’eventuale codice postale in coda.

Le scansioni DHL non includono il fuso orario: Peek le legge nell’ora locale del luogo di scansione (ora di Madrid in Spagna, ma ora delle Canarie alle isole Canarie; ora di Lisbona in Portogallo) e le mostra nel tuo fuso («Orari nel tuo fuso orario:» seguito dal nome). Mostra stato, peso, il punto di ritiro ServicePoint sia in attesa sia dopo il ritiro e, quando la risposta DHL riporta un codice CTT Express, interroga anche [CTT Express](carrier:ctt-express) e passa a seguirlo non appena presenta una scansione non più vecchia dell’ultima di DHL. Le diciture restano nell’inglese di DHL tranne `Delivered` e `Out for delivery`, tradotti da Peek. Non vengono mostrati data prevista né orario separato di consegna per DHL eCommerce Iberia.

Mentre hai il pacco aperto in Peek — o il giorno della consegna con le notifiche attive — Peek interroga direttamente DHL eCommerce Iberia fino a ogni 10 minuti, e fino a ogni 2 minuti dalle 6:00 nell’ora dell’ultima scansione nel giorno in cui DHL indica «In consegna», finché il tracker risponde; altrimenti, e dopo due giorni senza novità (12 ore se resta su «Etichetta creata» o «Preso in carico»), controlla ogni ora; dopo sette giorni senza novità su «Etichetta creata», ogni 6 ore. Per le notifiche scegli «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Dopo quattro giorni senza scansioni su «Etichetta creata», «Preso in carico» o «In transito», Peek mostra la data dell’ultima scansione («Nessun aggiornamento da …») e «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a DHL eCommerce Iberia.», con il link «Chiedi aiuto a DHL eCommerce Iberia» alla pagina contatti. Peek non può contattare DHL né il negozio per te.

## Domande frequenti su DHL eCommerce Iberia

### DHL eCommerce è la stessa cosa di DHL Express?

No. Le spedizioni DHL Express hanno un tracciamento proprio (MyDHL+) e Peek gestisce i due corrieri separatamente. Un codice a 10 cifre può appartenere a entrambi: quando la cifra di controllo torna, Peek chiede prima a DHL Express e, se DHL Express lo conosce, ha la precedenza.

### Perché il mio pacco DHL eCommerce non ha tracciamento?

Verifica numero e corriere con il negozio: diversi corrieri usano codici di 10 cifre (vedi tabella sopra). Anche una spedizione datata può sparire dal tracciamento DHL; non è nota la durata di conservazione dello storico.

### CTT Express consegna i pacchi di DHL eCommerce?

Una parte sì. Con la joint venture firmata nel maggio 2026 tra DHL eCommerce e CTT, CTT Express gestisce le consegne ai consumatori in Spagna, mentre DHL eCommerce in Portogallo confluisce in CTT Expresso. Se il tracciamento indica un numero CTT Express, traccialo con [CTT Express](carrier:ctt-express).

:::sources
- [CTT: CTT e DHL Ecommerce selam parceria estratégica](https://www.ctt.pt/grupo-ctt/media/noticias/ctt-e-dhl-ecommerce-selam-parceria-estrategica-para-criar-rede-de-distribuicao-de-encomendas-mais-completa-da-peninsula-iberica) – joint venture firmata il 12 maggio 2026, DHL eCommerce Portogallo a CTT Expresso, CTT Express per i consumatori in Spagna, DHL per consegne business
- [Your Europe: Shipping and delivery](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_en.htm) – responsabilità del venditore fino alla consegna, 30 giorni, termine supplementare, rimborso
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – campi letti da Peek, diciture degli stati e fasi, ServicePoint, link spedizione, vecchio nome DHL Parcel Iberia, codici CTT Express e frequenza controlli
:::
