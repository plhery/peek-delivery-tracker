---
title: Tracciamento GEODIS: numero 1G, stati e contatti
description: Traccia una spedizione GEODIS: dove trovare il numero 1G, cosa significano gli stati in francese, come cambiare data o ritirare in filiale e contatti.
slug: geodis-tracciamento
published: 2026-11-17
updated: 2026-11-17
---

GEODIS è un gruppo francese di trasporto e logistica controllato al 100% dalle ferrovie pubbliche francesi SNCF dal 2008. La sua rete di pacchi e pallet (*Distribution & Express*) consegna ordini in Francia e su strada in Europa. Come destinatario segui una spedizione senza account nello spazio destinatari (*Espace destinataire*) su [espace-client.geodis.com/services/destinataires/](https://espace-client.geodis.com/services/destinataires/) con il numero `1G` da 12 caratteri, oppure incollandolo qui sopra. Il portale *Mon espace GEODIS*, invece, è riservato ai clienti mittenti registrati.

## Numero di tracciamento GEODIS: formato, dove trovarlo e avvisi autentici

Secondo GEODIS, il codice ti viene fornito dal mittente o dal negozio online nell'e-mail di conferma o nella notifica di spedizione. Se l'autista è passato mentre non c'eri, annota il codice `1G` anche sull'avviso di passaggio nella cassetta postale. Non lo trovi? Chiedilo al venditore o leggi [come trovare il numero di tracciamento](guide:find-tracking-number).

Se il mittente ha attivato le notifiche, GEODIS invia un SMS o un'e-mail uno o due giorni prima della consegna e un secondo messaggio la mattina stessa del giro. I messaggi autentici di GEODIS contengono un link breve `edesti.com/1G…` (nelle FAQ compare l'esempio `edesti.com/1G123GEODIS0`) diretto all'*Espace destinataire*. Se dubiti di un SMS, inserisci manualmente il codice `1G` su `espace-client.geodis.com/services/destinataires/` e consulta [come riconoscere gli SMS truffa sulle consegne](guide:delivery-scam-texts).

Lo spazio destinatari accetta due codici (esempi inventati):

| Formato (inventato) | Cosa identifica |
| --- | --- |
| `1GGDXF0B27F1` | **Numero 1G** (*Numéro de suivi 1G*, in italiano *«Numero di tracciamento 1G»*): 12 maiuscole e cifre che iniziano con `1G`. Basta da solo sul portale GEODIS e in Peek. |
| `19847263510492` | **Numero di spedizione a 14 cifre** (*n° de récépissé* / *«Numero di spedizione (14 caratteri digitali)»*): 14 cifre. Sul portale richiede anche il *Codice Postale* del destinatario; Peek non lo abbina a GEODIS. |

La pagina [Traccia la tua spedizione su geodis.com](https://geodis.com/it-it/traccia-la-tua-spedizione) cerca spedizioni aeree, marittime e dell'ultimo miglio, e per il trasporto su strada rimanda allo strumento dedicato. Un numero a 8 cifre non appartiene a GEODIS in Peek: può essere di Heppner o di [Mondial Relay](carrier:mondial-relay); verifica il corriere nell'e-mail del negozio o i [formati dei numeri di tracciamento](guide:tracking-number-formats). Come indica il messaggio di errore del portale, le spedizioni oltre i 12 mesi non sono più ricercabili.

## Stati di tracciamento GEODIS: cosa significano le diciture

L'*Espace destinataire* si apre in francese e passa in italiano (*Traccia la mia spedizione*), inglese o spagnolo. Mostra quattro tappe con quella attuale evidenziata e, quando esistono scansioni datate, lo storico (*Historique d'acheminement* / *Storico delle spedizioni*) con la sede GEODIS di ogni evento. Alcune spedizioni mostrano solo le quattro tappe.

Poiché Peek legge la versione francese di GEODIS, vedrai i testi originali in francese (prima colonna), affiancati dall'etichetta inglese delle quattro tappe principali:

| GEODIS (francese) | Sito inglese di GEODIS | Cosa significa per te |
| --- | --- | --- |
| `En préparation chez l'expéditeur` | `In preparation at the shipper's` | Il mittente ha registrato la spedizione, ma GEODIS non l'ha ancora ritirata. |
| `En attente de prise en charge` | – | Il venditore non ha ancora affidato fisicamente il collo a GEODIS. |
| `Envoi pris en charge` | – | GEODIS ha preso in carico la spedizione dal mittente. |
| `En cours d’acheminement` | `In transit` | Viaggia tra gli hub e le filiali della rete GEODIS. |
| `Mise en livraison` | `Delivery in progress` | Messa in consegna: programmata per il giro dell'autista. |
| `En cours de livraison` | – | Sul mezzo dell'autista durante il giro di consegna. |
| `Disponible pour retrait en agence` | – | Pronta per il ritiro nella filiale GEODIS indicata. |
| `Expédition livrée` | `Delivered shipment` | Consegnata; in italiano la data segue *«Spedizione consegnata il :»*. |
| `Livraison effectuée` | – | Consegna effettuata (in Peek mostrata come «Consegnato»). |

Da sapere sugli stati:

- `Mise en livraison` (`Delivery in progress`) indica che il pacco è in consegna con l'autista, non già recapitato.
- Se resta per giorni su `En préparation chez l'expéditeur` o `En attente de prise en charge`, il negozio non ha ancora consegnato la merce a GEODIS: senti il venditore.
- Il giorno della consegna può comparire la mappa in tempo reale con avvisi come *«Il conducente è in arrivo»*, *«Tu sei il prossimo»* e *«L'autista arriva!»*.
- Se leggi *«Non avete ancora fissato il vostro appuntamento»*, GEODIS aspetta che tu scelga una data nel portale.
- Per i ritiri, un avviso segna *«Avete ancora … giorni per ritirare la vostra spedizione»* e un altro avverte *«Si prega di notare che in … giorni la spedizione verrà restituita al mittente»*. Più dettagli in [stati della spedizione](guide:tracking-statuses).

## Consegna GEODIS: cambio data, ritiro in filiale e opzioni

Il portale mostra solo le opzioni attive per la tua spedizione. Nello standard cambi data o modalità solo dopo il primo tentativo fallito; con l'opzione *On Demand* scelta dal mittente, già dal primo SMS o e-mail. Confermata una nuova scelta, non si può ripristinare la consegna prevista per quel giorno. GEODIS può proporre:

- **Nuova data a domicilio**, con fascia oraria se offerta. La mattina della consegna, con notifiche attive, GEODIS invia una finestra di 2 ore in espresso o 3 ore nello standard; per i privati le FAQ indicano che l'autista di norma chiama tre fermate prima.
- **Ritiro in filiale** (*retrait en agence*) in una delle 110 agenzie francesi, se previsto per la zona. Tutte hanno uno sportello con orari indicati sulla pagina. Porta documento d'identità e SMS o e-mail di GEODIS. Un delegato deve portare modulo di delega GEODIS compilato, proprio documento originale, tuo documento originale e il messaggio; per aziende senza referente nominativo servono visura (*Kbis*) sotto i 3 mesi e delega del legale rappresentante.
- **Vicino o portiere** (*voisin / gardien*), indicato con nome e telefono per firmare in tua assenza.
- **Luogo sicuro** (*lieu sûr*), come pianerottolo, garage o giardino: l'autista lascia il pacco e scatta una foto.
- **Punto vicino Pickme** (*Voisin Pickme*): consegna a un vicino registrato dal giorno lavorativo successivo, che ti avvisa appena il collo è disponibile. Al lancio nel dicembre 2024, GEODIS indicava 14 giorni per il ritiro con codice segreto.

Una fascia più stretta o la consegna al piano richiedono che il mittente abbia acquistato il servizio (sotto i 70 kg movimentabili da un autista, somma dei lati sotto i 2 m e ascensore dal primo piano); i pallet restano di default al cancello o all'ingresso dell'edificio. Dopo un tentativo fallito, la filiale francese conserva il collo per 8 giorni di calendario secondo le FAQ prima del reso (vedi [consegna mancata](guide:missed-delivery)). GEODIS non pubblica il numero di tentativi. Per correggere indirizzo o telefono usa il modulo di contatto o avvisa il negozio.

## Spedizione GEODIS in ritardo, danneggiata o non consegnata

Il contratto di trasporto è tra mittente e GEODIS, quindi il venditore resta il primo riferimento.

**In ritardo o ferma.** GEODIS indica tempi orientativi di 24–48 ore in Francia nello standard e il giorno dopo entro le 12:00 nell'espresso; in Europa, 1–2 giorni in espresso e 3–5 giorni nello standard. Le condizioni generali (*Conditions générales de vente*, maggio 2025) precisano che non sono garantiti. Se tutto tace per giorni, vedi [tracciamento che non si aggiorna](guide:tracking-not-updating) e contatta il negozio.

**Il numero non funziona.** Controlla il formato (`1G` più 10 caratteri) e senti il mittente o venditore.

**In attesa di istruzioni.** Se la consegna è impossibile, GEODIS chiede istruzioni al committente secondo le proprie condizioni. Contatta il negozio.

**Danneggiata o incompleta.** Verifica il collo davanti all'autista e scrivi riserve precise e motivate (*réserves précises et motivées*) sulla bolla. Il portale ricorda che il diritto dei trasporti concede tre giorni dalla consegna per contestare per iscritto danni o ammanchi; senza riserve valide, le condizioni GEODIS escludono ricorsi contro il vettore. Dettagli in [pacco danneggiato](guide:damaged-parcel), avvisando anche il venditore.

**Reclami dei consumatori.** Le condizioni francesi chiedono di rivolgersi prima al venditore, poi al servizio clienti della società GEODIS o a `dpm@geodis.com`. Senza risposta entro 2 mesi o a lite aperta, GEODIS indica il servizio francese di mediazione *Médiation de la consommation AME Conso* (197 Boulevard Saint Germain, 75007 Parigi).

## Contatti e assistenza clienti GEODIS

GEODIS non pubblica un telefono per i destinatari dei pacchi. Canali disponibili:

- **Spazio destinatari:** [espace-client.geodis.com/services/destinataires/](https://espace-client.geodis.com/services/destinataires/), con menu *aiuto* (*Aide*).
- **Modulo destinatari:** nella [pagina contatti italiana](https://geodis.com/it-it/contattaci) scegli *Assistenza clienti*, seleziona *«un destinatario»* in *«Chi sei?»* e inserisci il *Numero di tracking* (disponibile anche su [geodis.com/contact-us](https://geodis.com/contact-us) e [geodis.com/fr/nous-contacter](https://geodis.com/fr/nous-contacter)). Secondo le FAQ francesi, il più vicino dei 110 servizi clienti risponde entro 12 ore lavorative.
- **E-mail reclami consumatori:** `dpm@geodis.com`.
- **Filiali in Francia:** nomi e indirizzi nell'[elenco delle agenzie](https://geodis.com/fr/liste-des-agences?territory=FR), incluse le sedi France Express.

## Tracciare una spedizione GEODIS con Peek

Incolla il codice `1G` su [Peek](/) per riconoscere subito GEODIS. Funzionano anche i link a `espace-client.geodis.com/services/destinataires/` (`noSuivi=`, `trackingNumber=` o `#/.../suivi/...`), a `espace-client.france-express.com/#/.../suivi/...` e il link `edesti.com/1G…` o l'SMS completo con un codice `1G`. Il numero a 14 cifre non viene tracciato come GEODIS da Peek; usa il codice `1G`.

Peek legge il tracciamento francese di GEODIS, mostra i testi in francese (`Livraison effectuée` appare come «Consegnato») e segna sulla mappa la sede di ogni scansione. Converte l'ora di Parigi nel fuso del tuo dispositivo e mostra data prevista, mittente, servizio e peso. Un tentativo fallito, problema, reso o ritiro nell'ultima scansione prevale sulla tappa; se ci sono solo tappe senza data, Peek registra l'ora del primo rilevamento. Esclude numero di colli, tuo nome e indirizzo, istruzioni e prove di consegna; oltre i 12 mesi la spedizione risulta non trovata.

Peek controlla fino a ogni 10 minuti mentre qualcuno attende il pacco e ogni ora negli altri casi, come pure dopo due giorni senza scansioni o dopo 12 ore su «Etichetta creata» o «Preso in carico». Il giorno di consegna controlla fino a ogni 2 minuti dalle 6:00 ora locale quando è in consegna e risponde il sistema GEODIS; se rispondono solo i servizi di tracciamento, controlla ogni ora, ogni 3 ore dopo un giorno senza scansioni e fino a ogni 15 minuti il giorno di consegna. Per le notifiche scegli tra «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Per spostare la consegna usa l'*Espace destinataire*.

## Domande frequenti sul tracciamento GEODIS

### Cos'è «Mon espace GEODIS»?

È il portale su [espace-client.geodis.com/index](https://espace-client.geodis.com/index/) per i clienti mittenti di GEODIS, che accedono con credenziali per gestire ritiri e spedizioni (in Italia gli strumenti aziendali sono raccolti anche in [Il vostro spazio di lavoro](https://geodis.com/it-it/il-vostro-spazio-di-lavoro)). Ai destinatari la pagina indica l'*Espace destinataire*, dove basta il codice `1G`.

### Che legame c'è tra GEODIS, Calberson e France Express?

Il gruppo nasce dall'impresa fondata da Émile Calberson nel 1904, ha preso il nome GEODIS a fine 1995, dal 2008 appartiene al 100% a SNCF e nel 2015 ha unificato i suoi marchi sotto GEODIS. France Express è una delle sue reti espresse con un proprio [spazio destinatari](https://espace-client.france-express.com/services/destinataire).

### In quali giorni e orari consegna GEODIS?

Secondo le FAQ, le consegne standard avvengono dal lunedì al venerdì tra le 8:00 e le 18:00. Il sabato tra le 8:00 e le 13:00 è attivo se scelto dal mittente; niente consegne la domenica e nei festivi.

### Quanto impiega GEODIS a consegnare?

Senza garanzia contrattuale, GEODIS indica 24–48 ore in Francia nello standard e il giorno dopo entro le 12:00 nell'espresso; nel resto d'Europa, 1–2 giorni in espresso e 3–5 giorni nello standard.

:::sources
- [GEODIS: Espace destinataire](https://espace-client.geodis.com/services/destinataires/) – campi, italiano, tappe e mappa live, opzioni di consegna, delega, consegna al piano, tre giorni e 12 mesi
- [GEODIS: Mon espace GEODIS](https://espace-client.geodis.com/index/) – portale mittenti e rimando allo spazio destinatari
- [GEODIS: Traccia la tua spedizione (IT)](https://geodis.com/it-it/traccia-la-tua-spedizione) – ricerca aerea, marittima e ultimo miglio
- [GEODIS: Contattaci in Italia](https://geodis.com/it-it/contattaci) – modulo italiano con scelta «un destinatario»
- [GEODIS: Il vostro spazio di lavoro (IT)](https://geodis.com/it-it/il-vostro-spazio-di-lavoro) – strumenti aziendali
- [GEODIS: Contact us (EN)](https://geodis.com/contact-us) – modulo destinatari, pallet e consegna al piano
- [GEODIS: Nous contacter (FR)](https://geodis.com/fr/nous-contacter) – modulo destinatari e rettifica indirizzo
- [GEODIS: FAQ – Accesso all'Espace Client](https://geodis.com/fr/faq?question=comment-acceder-a-mon-espace-client) – accesso mittenti
- [GEODIS: FAQ – Orari e weekend](https://geodis.com/fr/faq?question=peut-on-etre-livre-dehors-heures-bureau-jour-ferie-dimanche) – lun–ven 8:00–18:00, sab 8:00–13:00 opzionale
- [GEODIS: FAQ – Numero di spedizione](https://geodis.com/fr/faq?question=qu-est-ce-qu-un-numero-d-envoi-et-ou-le-trouver-lorsqu-on-est-destinataire) – codice 1G, avviso, edesti.com e 14 cifre
- [GEODIS: FAQ – En attente de prise en charge](https://geodis.com/fr/faq?question=que-signifie-en-attente-de-prise-en-charge-sur-le-suivi-de-ma-livraison-50000) – merce ancora dal venditore
- [GEODIS: FAQ – Fascia oraria](https://geodis.com/fr/faq?question=quand-serai-je-informe-e-du-creneau-horaire-de-ma-livraison) – preavvisi, finestra 2/3 ore e telefonata tre fermate prima
- [GEODIS: FAQ – Modifica data](https://geodis.com/fr/faq?question=comment-modifier-la-date-de-livraison-prevue) – standard dopo primo tentativo, On Demand e 110 agenzie
- [GEODIS: FAQ – Giacenza in agenzia](https://geodis.com/fr/faq?question=pendant-combien-de-temps-mon-envoi-est-il-conserve-en-agence) – 8 giorni di calendario
- [GEODIS: FAQ – Servizio clienti destinatari](https://geodis.com/fr/faq?question=comment-contacter-le-service-relation-client-destinataire) – risposta entro 12 ore lavorative
- [GEODIS: Votre espace de travail (FR)](https://geodis.com/fr/votre-espace-de-travail) – Distribution & Express
- [GEODIS: Transport de colis et de palettes](https://geodis.com/fr/transport-de-marchandises/transport-de-colis-et-de-palettes) – tempi in Francia ed Europa
- [GEODIS: Conditions générales de vente, maggio 2025 (PDF)](https://geodis.com/sites/default/files/widen-migration/2glqzqlnw7/CGV_FR-052025.pdf) – tempi, istruzioni, riserve, dpm@geodis.com e AME
- [GEODIS: Partnership con Pickme](https://geodis.com/fr/newsroom/geodis-sappuie-sur-pickme-pour-elargir-son-offre-de-livraison-en-point-de-retrait-dans) – 14 giorni e codice segreto
- [GEODIS: Notre histoire](https://geodis.com/fr/notre-histoire) – Calberson 1904, GEODIS 1995, SNCF 2008 e marchio unico 2015
- [GEODIS: Liste des agences](https://geodis.com/fr/liste-des-agences?territory=FR) – agenzie e France Express
- [France Express: Home](https://france-express.com/) – spazio destinatari France Express
- [France Express: Espace destinataire](https://espace-client.france-express.com/services/destinataire) – tracciamento destinatari France Express
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – lettura di GEODIS in Peek
:::
