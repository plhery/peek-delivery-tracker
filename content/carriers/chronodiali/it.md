---
title: Tracciamento Chronodiali: numeri CHPU, stati e contatti
description: Traccia un pacco Chronodiali in Marocco: il numero CHPU, gli stati in francese spiegati, pagamento alla consegna, tre tentativi e contatti.
slug: tracciamento-chronodiali
published: 2026-11-23
updated: 2026-11-23
---

Chronodiali (scritto anche Chrono Diali) è una società marocchina di consegne per negozi online, joint venture tra Barid Al-Maghrib (Poste Maroc) e Geopost. Consegna pacchi e-commerce in tutto il Marocco, a domicilio o in una delle sue agenzie. Se il negozio ha scelto il contrassegno, paghi il corriere alla consegna, in contanti o con carta bancaria. Traccialo sotto «Suivre mon colis» su [chronodiali.ma](https://chronodiali.ma/suivi_colis.html) o nella casella qui sopra. Il numero arriva dal negozio che ha spedito il pacco; l’esempio di Chronodiali inizia con `CHPU`.

## Il tuo numero di tracciamento Chronodiali

I formati noti a Peek, con cifre inventate:

| Numero | Cos’è |
| --- | --- |
| `CHPU4829103756182947305` | `CHPU` e 19 cifre: un numero Chronodiali. Peek lo riconosce subito. |
| `CD4829103756` | `CD` e 10 cifre. Peek può seguirlo anche con Chronodiali, ma la stessa forma corrisponde a [Colis Privé](carrier:colis-prive), [Aramex Australia](carrier:aramex-au) e [Aramex Nuova Zelanda](carrier:aramex-nz), quindi Peek interroga tutti e quattro (vedi sotto). |
| `chronodiali.ma/suivi_colis.html?id=…` | Un link alla pagina di tracciamento di Chronodiali. Peek lo riconosce come Chronodiali qualunque numero contenga. |

Chronodiali non è [Chronopost](carrier:chronopost), il corriere espresso francese: numeri e tracciamento sono diversi. Nessun numero? Chiedi al negozio o vedi [dove trovare un numero di tracciamento](guide:find-tracking-number).

## Stati del tracciamento Chronodiali in italiano

Chronodiali scrive gli stati in francese. Nell’ordine in cui compaiono di solito:

| Chronodiali dice | Peek mostra | Cosa significa per te |
| --- | --- | --- |
| `Les données du colis ont été intégrées dans nos systèmes.` | Etichetta creata | Il negozio ha inviato i dati del pacco a Chronodiali, che non ha ancora il pacco. |
| `Le ramassage a été programmé.` | Etichetta creata | È prenotato il ritiro presso il negozio. |
| `Le ramassage est en attente, le livreur se présentera sous peu.` | Etichetta creata | In attesa che il corriere ritiri il pacco dal mittente. |
| `Le colis a été collecté par le livreur.` | Preso in carico | Il corriere ha ritirato il pacco. |
| `Le colis est arrivé au hub d'origine.` | In transito | Arrivato al primo hub di Chronodiali. |
| `Le colis a quitté notre hub.`, `Le colis est arrivé au hub.` | In transito | In movimento tra gli hub di Chronodiali. |
| `Delivery Scheduled` | In transito | Inserito in un giro di consegna presso l’hub di destinazione. |
| `Out For Delivery`, `Le colis est en cours de livraison.` | In consegna | È con il corriere oggi. Se paghi alla consegna, tieni pronto l’importo. |
| `Le colis a été livré.` | Consegnato | Consegnato. |
| `La livraison a échoué : le client n'a pas répondu ou était injoignable.` | Tentativo di consegna | Il corriere non è riuscito a raggiungerti al telefono o alla porta. |
| `La livraison a échoué : le lieu de livraison est incorrect ou introuvable.` | Tentativo di consegna | L’indirizzo è errato o introvabile. |
| `La livraison a échoué : le client est indisponible.` | Tentativo di consegna | Non eri disponibile. |
| `La livraison a échoué : le client n'a pas le montant COD requis.` | Tentativo di consegna | Non avevi l’importo richiesto per il contrassegno (COD). |
| `La livraison a échoué : le client a refusé le colis.` | Tentativo di consegna | Il pacco è stato rifiutato. |
| `La livraison a échoué.` | Tentativo di consegna | Consegna fallita senza motivo indicato. |
| `Le colis est retenu au hub.` | Problema segnalato | Il pacco è trattenuto in un hub. |
| `La livraison a été annulée : le colis est endommagé et ne peut pas être livré.` | Problema segnalato | Il pacco è danneggiato e non può essere consegnato. |
| `Le colis a été annulé.` | Problema segnalato | La spedizione è stata annullata. |
| `Le colis va être renvoyé à l'expéditeur.` | Restituito al mittente | Sta tornando al negozio. |
| `Le colis a été retourné à l'adresse de l'expéditeur.` | Restituito al mittente | È rientrato all’indirizzo del negozio. |

**Assente su chronodiali.ma.** La pagina ufficiale salta alcune di queste voci: `Delivery Scheduled`, `Out For Delivery`, i tentativi falliti, le consegne annullate o riprogrammate e `Le colis a été confié à un partenaire tiers.` Peek le mostra, quindi la cronologia può essere più lunga di quella di Chronodiali.

**Altre righe.** `La livraison a été reprogrammée : une nouvelle date a été définie.` indica una nuova data di consegna; `La livraison a été reprogrammée.` non dà dettagli. `La livraison a été annulée à la demande du client.` segnala una cancellazione richiesta dal cliente. `Le colis est en attente au hub.` significa che il pacco attende in un hub. Per le due righe con `partenaire tiers`, vedi le domande sotto; Peek le mostra entrambe come «In transito».

**Pacchi dall’estero.** Alcuni arrivano in Marocco da un altro Paese. Puoi vedere `arrivé dans le pays.` (arrivato in Marocco; Peek mostra «In transito»), `dédouanement en cours` (sdoganamento in corso; «In dogana») e `dédouanement terminé` (sdoganato; «In transito»). Il sito di Chronodiali non parla di dazi né di chi li paga: chiedi al negozio e consulta [pacchi fermi in dogana](guide:customs).

## Consegna Chronodiali: pagamento alla consegna, tre tentativi e agenzie

Chronodiali dichiara di consegnare in tutto il Marocco, in oltre 300 destinazioni, a domicilio o in agenzia.

- **Pagamento alla consegna:** la pagina di tracciamento mostra l’importo da pagare (`Montant à payer`) in dirham (DH). Secondo Chronodiali puoi pagarlo alla consegna in contanti o con carta bancaria.
- **Tre tentativi:** Chronodiali include «3 Tentatives de livraison» tra i suoi servizi. Su Peek ogni tentativo fallito compare con una riga che inizia per `La livraison a échoué`, con o senza motivo.
- **Tieni acceso il telefono:** uno dei motivi di consegna fallita è non rispondere o risultare irraggiungibile.
- **Notifiche:** la pagina dei servizi indica avvisi via SMS ed e-mail e una notifica al cliente a ogni passaggio.
- **Agenzie:** Agadir, Casablanca, Fès, Marrakech, Rabat e Tangeri, aperte dal lunedì al venerdì 9:00–18:00 e sabato 9:00–13:00 (ora del Marocco). Gli indirizzi sono nella pagina «Points de livraison».

Il sito non specifica per quanti giorni un’agenzia tenga un pacco né cosa accada dopo il terzo tentativo fallito: chiedi a Chronodiali o al negozio. Corriere mancato? Vedi [consegne mancate](guide:missed-delivery).

## Pacco Chronodiali in ritardo, rifiutato, reso o non ricevuto

Per un rimborso o una nuova spedizione contatta il negozio: è il cliente di Chronodiali e prenota i ritiri attraverso la piattaforma legata al contratto.

- **Nessun movimento dopo `Les données du colis ont été intégrées dans nos systèmes.`:** il negozio ha creato la spedizione ma Chronodiali non ha ancora ritirato il pacco. Chiedi al negozio quando lo consegnerà. Se chronodiali.ma mostra `En cours de traitement.`, Chronodiali conosce il numero ma non ha ancora scansioni.
- **In ritardo:** Chronodiali non pubblica tempi di consegna; le condizioni indicano che sono comunicati all’ordine e variano in base alla destinazione. Quando l’ultima riga ha quattro giorni o più e il pacco è ancora su «Etichetta creata», «Preso in carico» o «In transito», Peek lo segnala con il giorno di quella riga («Nessun aggiornamento da …») e aggiunge un link «Chiedi aiuto a Chronodiali». Vedi [tracciamento che non si aggiorna](guide:tracking-not-updating).
- **Rifiutato o non pagato:** se rifiuti il pacco o non puoi pagarlo, il corriere registra una consegna fallita e il pacco può tornare al negozio: `Le colis va être renvoyé à l'expéditeur.`, poi `Le colis a été retourné à l'adresse de l'expéditeur.` Vedi [restituito al mittente](guide:returned-to-sender).
- **Consegnato ma non ricevuto:** Chronodiali fornisce ai negozi una prova digitale di consegna nella loro area clienti. Chiedi al negozio di controllarla. Altro in [consegnato ma non ricevuto](guide:delivered-not-received).
- **Danneggiato o smarrito:** avvisa prima il negozio. Puoi anche spuntare «Réclamation ?» nel modulo contatti di Chronodiali, oppure chiamare o scrivere indicando il numero. Vedi [pacchi danneggiati](guide:damaged-parcel) e [pacchi smarriti](guide:lost-parcel).
- **Un messaggio che chiede denaro:** sul proprio sito Chronodiali descrive il pagamento come effettuato alla consegna, in contanti o con carta. Se un SMS o un’e-mail chiede di pagare tramite un link, controlla prima il tracciamento e consulta [SMS truffa sulle consegne](guide:delivery-scam-texts).

## Numero di contatto e assistenza clienti Chronodiali

Recapiti su chronodiali.ma:

- **Telefono:** +212 5 20 61 62 63 (05 20 61 62 63 dal Marocco).
- **E-mail:** service-client@chronodiali.ma.
- **Modulo di contatto:** nella pagina [Contact](https://chronodiali.ma/contact_us.html) scegli «Réclamation ?» per un reclamo o «Demande d'information ?» per una domanda. Il modulo chiede solo nome, e-mail, telefono e nome del negozio, senza campo per un messaggio o per il numero di tracciamento; Chronodiali indica che ti ricontatterà.
- **Agenzie:** le sei città elencate sopra, con lo stesso numero di telefono ed e-mail.

Il sito non indica orari separati per il telefono e non elenca numeri WhatsApp.

## Tracciare un pacco Chronodiali con Peek

Incolla un numero `CHPU` e [Peek](/) riconosce subito Chronodiali sotto «Corriere rilevato». Minuscolo e spazi vanno bene. Funziona anche un link alla pagina di tracciamento di chronodiali.ma.

Un numero `CD` di 10 cifre non basta da solo a individuare il corriere. Incollalo comunque: Peek segue oltre 4.500 corrieri. Mostra «Verifica dei servizi di tracciamento…» mentre interroga Chronodiali e i corrieri con la stessa forma; se solo Chronodiali ha il pacco, il suo nome appare con «ha questo pacco». Oppure tocca «Cambia» e scegli «Chronodiali» in «Compatibili con questo numero». Se selezioni Chronodiali per un numero di forma diversa, Peek indica che Chronodiali «non emette numeri come questo» e il pacco mostra «Controlla il numero di tracciamento».

Peek legge il tracciamento ufficiale di Chronodiali: lo stato, ogni riga nel testo di Chronodiali (in francese, o in inglese nei due casi sopra), il nome dell’hub come località quando presente, il Paese di destinazione e l’orario di consegna. Quando Chronodiali li restituisce, Peek ti mostra anche il nome del destinatario, sotto «Destinatario», e, finché il pacco è in viaggio, la città di destinazione sulla mappa se Peek la colloca in Marocco; chi riceve da te il pacco condiviso non vede né l’uno né l’altra, e il nome di chi ha firmato resta privato: nessuna app lo mostra. Gli orari vengono mostrati nel fuso orario del tuo dispositivo. Restano fuori nome e città del mittente, importo da pagare, prova di consegna e chiamate del corriere. Chronodiali non indica data di consegna stimata né peso. Le righe su un partner terzo non lo nominano e Peek non segue il tracciamento del partner.

Se Chronodiali non conosce ancora il numero, Peek mostra «In attesa di Chronodiali» e continua a controllare; se dopo due giorni Chronodiali non lo conosce ancora, Peek ti chiede di verificarlo, scegliere un altro corriere o eliminare il pacco. Finché il pacco è aperto in Peek, o nel giorno della consegna con le notifiche attive, Peek lo controlla fino a ogni 10 minuti, e ogni 2 minuti dalle 6:00, ora del Marocco, nel giorno in cui Chronodiali registra il pacco in consegna, finché il tracciamento di Chronodiali risponde. Dopo due giorni senza novità (12 ore se resta su «Etichetta creata» o «Preso in carico»), controlla ogni ora, e dopo sette giorni senza novità su «Etichetta creata», ogni 6 ore. Può notificarti per «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Non può pagare, riprogrammare una consegna né contattare Chronodiali o il negozio per te.

## Domande sul tracciamento Chronodiali

### Chronodiali fa parte di Poste Maroc?

In parte. La pagina notizie di Chronodiali la presenta come joint venture: Barid Al-Maghrib (Poste Maroc) detiene il 51% e Geopost il 49%. Dispone di tracciamento proprio su chronodiali.ma e di una propria assistenza clienti.

### Quante volte Chronodiali tenta la consegna?

Tre volte, secondo Chronodiali. Su Peek un tentativo fallito compare con una riga che inizia per `La livraison a échoué`, con o senza motivo.

### Posso pagare un pacco Chronodiali con carta?

Sì. Chronodiali indica che puoi pagare alla consegna in contanti o con carta bancaria.

### Cosa significa «partenaire tiers» nel tracciamento Chronodiali?

`Le colis a été assigné à un partenaire tiers.` e `Le colis a été confié à un partenaire tiers.` indicano che Chronodiali ha assegnato o affidato il pacco a un’azienda partner. Il tracciamento non nomina il partner: attendi l’aggiornamento successivo o chiedi a Chronodiali con il tuo numero.

### Come contatto Chronodiali per il mio pacco?

Chiama +212 5 20 61 62 63 o scrivi a service-client@chronodiali.ma indicando il numero di tracciamento. Per rimborsi o nuove spedizioni rivolgiti al negozio.

:::sources
- [Chronodiali: Les experts de la livraison e-commerce](https://chronodiali.ma/) – Barid Al-Maghrib e Geopost, pagamento alla consegna in contanti o carta, consegna a domicilio o in agenzia, oltre 300 destinazioni, agenzie e orari, telefono, e-mail
- [Chronodiali: Suivre mon colis](https://chronodiali.ma/suivi_colis.html) – tracciamento, esempio `CHPU`, importo da pagare, righe di stato in francese, voci mostrate dalla pagina e «En cours de traitement.» (nello script della pagina)
- [Chronodiali: Nos services](https://chronodiali.ma/nos_service.html) – tre tentativi di consegna, notifiche SMS ed e-mail, contanti o carta, prova di consegna digitale
- [Chronodiali: Points de livraison](https://chronodiali.ma/point_de_livraison.html) – consegne in tutto il Marocco, agenzie e orari
- [Chronodiali: Envoyer un colis](https://chronodiali.ma/envoyercolis.html) – account e ritiri dei negozi, prove di consegna per i negozi
- [Chronodiali: Contact](https://chronodiali.ma/contact_us.html) – modulo contatti con «Réclamation ?» e i suoi campi, telefono, e-mail
- [Chronodiali: Gouvernance](https://chronodiali.ma/aboutus.html) – Barid Al-Maghrib (Poste Maroc)
- [Chronodiali: Actualités](https://chronodiali.ma/actualites.html) – joint venture, 51% Barid Al-Maghrib e 49% Geopost
- [Chronodiali: Mention légale](https://chronodiali.ma/mentions_legal.html) – Chrono Diali S.A., Casablanca; condizioni: contratto e ritiri del negozio, tempi comunicati all’ordine
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formati numerici, righe di stato e fasi, cosa mostrano la pagina Chronodiali e Peek
:::
