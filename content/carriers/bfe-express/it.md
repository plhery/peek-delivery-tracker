---
title: Tracciamento BFE Express: codici CK e WYB, stati e contatti
description: Traccia pacchi BFE Express verso Argentina, Perù, Uruguay e USA: codici CK e WYB, sdoganamento, stati di consegna ed email di supporto.
slug: tracciamento-bfe-express
published: 2026-11-24
updated: 2026-11-24
---

BFE Express (*BFEexpress*, gestito da Hongkong Bfe Info Tech Co Limited e fondato nel 2005) è una rete logistica e-commerce transfrontaliera e di consegna dell’ultimo miglio che collega venditori internazionali — inclusi i commercianti di Temu Argentina — con destinatari in Argentina, Perù, Uruguay e Stati Uniti. L’azienda fornisce trasporto internazionale, sdoganamento, magazzinaggio locale e consegna dell’ultimo miglio tracciabili su [bfeexpress.com/tracking](https://www.bfeexpress.com/tracking).

Poiché BFE Express gestisce l’arrivo internazionale e lo sdoganamento prima di affidare i pacchi ai depositi regionali dell’ultimo miglio, una stessa cronologia combina di solito scansioni aeroportuali e doganali in inglese con scansioni di consegna locali in spagnolo.

## Formati dei codici di tracciamento BFE Express

Peek accetta codici BFE Express composti da 6 a 50 lettere e cifre. Il modulo di contatto di BFE Express riconosce questi schemi per destinazione:

| Formato (inventato) | Destinazione e dettagli |
| --- | --- |
| `CK54829173604518` | `CK54` + 12 cifre (16 caratteri) — Argentina; nessun altro corriere su Peek usa questa forma. |
| `CK548291736045182` | `CK54` + 13 cifre (17 caratteri) — formato alternativo per l’Argentina (stessa lunghezza di [Colis Privé](carrier:colis-prive)). |
| `CK512829173604518` | `CK512` + 12 cifre (17 caratteri) — formato Perù (stessa lunghezza di [Colis Privé](carrier:colis-prive)). |
| `CK598829173604518` | `CK598` + 12 cifre (17 caratteri) — formato Uruguay (stessa lunghezza di [Colis Privé](carrier:colis-prive)). |
| `WYB482917360` | `WYB` + 9 cifre (12 caratteri) — lettera di vettura per il Perù (si sovrappone a [Purolator](carrier:purolator) e [TNT Australia](carrier:tnt-au)). |

Le cifre dopo `CK` indicano la destinazione: `54` per l’Argentina, `512` per il Perù e `598` per l’Uruguay. Se incolli un link diretto `bfeexpress.com/track?track_number=...`, Peek estrae il codice e seleziona subito BFE Express. Per altri formati internazionali, consulta la guida ai [formati dei codici di tracciamento](guide:tracking-number-formats).

## Significato degli stati di tracciamento BFE Express

Il tracker di BFE Express elenca due gruppi di righe — la prima tratta internazionale e l’ultimo miglio — e Peek legge entrambi.

| Dicitura BFE Express | Cosa significa |
| --- | --- |
| `Arrived at Airport of destination` | Il volo che trasporta il pacco è atterrato nel paese di destinazione. |
| `Transport to next facility` | In trasferimento tra il terminal aeroportuale e il deposito locale di sdoganamento o smistamento. |
| `Customs declaration completed` | Sdoganamento a destinazione completato, prima del passaggio all’ultimo miglio. |
| `Turned over to last mile facility` | Affidato dalla tratta di sdoganamento BFE al deposito locale incaricato della consegna. |
| `Se ha recibido el paquete.` / `Tu paquete fue recibido por en: ...` | Ricevuto presso il deposito locale dell’ultimo miglio in Argentina o in Uruguay. |
| `En embarque` / `En transporte` / `Tu paquete está en viaje a ...` | Caricato e in viaggio tra depositi nazionali verso la tua città. |
| `Tu paquete se encuentra en ...` / `En espera para salir a reparto en ...` | Fermo nella filiale locale in attesa di uscire con il giro di consegne. |
| `Reparto en curso` | In consegna oggi con il corriere locale. |
| `Entregado` | Consegnato al destinatario. Accanto, lo stato complessivo di BFE riporta `Delivered`. |

Le righe in inglese di BFE Express mettono prima il luogo seguito da una virgola (come `<City>, Customs declaration completed`); le righe in spagnolo in Argentina mettono prima il paese, mentre l’ultimo miglio in Uruguay indica il luogo dopo `en` o `a`. Quando lo stato proprio di BFE per il pacco dice `Delivered`, Peek lo segna come consegnato anche se la riga più recente riporta altro.

## Sdoganamento, passaggio all’ultimo miglio e ritardi

Il sito di BFE Express offre spedizione pacchi, trasporto internazionale, sdoganamento, magazzinaggio locale e consegna dell’ultimo miglio, rivolgendosi anche ai venditori di Temu Argentina. Alcuni punti aiutano a interpretare i tempi:

- **Prima dell’aeroporto di destinazione:** se il tracker di BFE Express non mostra ancora nulla, controlla la pagina dell’ordine presso il venditore e leggi la guida sul [tracciamento fermo](guide:tracking-not-updating).
- **Dogana e ultimo miglio:** `Customs declaration completed` e `Turned over to last mile facility` possono comparire a un’ora di distanza nello stesso luogo, seguiti un giorno dopo dalla ricezione al deposito locale (`Se ha recibido el paquete.` o `Tu paquete fue recibido por en: ...`). Se un pacco si ferma in dogana a destinazione, consulta la [guida alla dogana](guide:customs).
- **Giorno di consegna previsto:** BFE Express può indicare un giorno di consegna previsto mentre il pacco è in viaggio e Peek lo mostra. Peek lo rimuove appena il pacco risulta consegnato, restituito o pronto per il ritiro, oppure quando una scansione supera quel giorno.
- **Segnato come consegnato o danneggiato:** la pagina di [Assistenza clienti BFE Express](https://www.bfeexpress.com/contactUS) tratta entrambi i casi nelle FAQ e invita a tenere a portata di mano il codice di tracciamento prima di scrivere al team locale. Vedi anche la guida [consegnato ma non ricevuto](guide:delivered-not-received).

Per spedizioni nazionali in Argentina gestite direttamente da corrieri locali, confronta con [OCA](carrier:oca) e [Andreani](carrier:andreani).

## Tracciare BFE Express con Peek

Incolla il codice `CK...` o `WYB...` in Peek, che segue oltre 4.500 corrieri. Peek non interroga mai BFE Express per un numero nudo: per un codice `CK54` di 16 caratteri non interroga alcun corriere, per un codice `CK` di 17 caratteri interroga [Colis Privé](carrier:colis-prive), e per un codice `WYB` interroga [TNT Australia](carrier:tnt-au) e [Purolator](carrier:purolator) (prima Purolator in Canada, prima TNT Australia altrove). Interroga poi i servizi di tracciamento («Verifica dei servizi di tracciamento…») e assegna il pacco a BFE Express solo se un servizio di tracciamento nomina esclusivamente lui con passaggi datati. Premi quindi «Cambia» e scegli BFE Express sotto «Compatibili con questo numero», oppure usa «Cambia corriere» su un pacco già aggiunto. Un link `bfeexpress.com/track?track_number=...` va subito su BFE Express.

Peek interroga direttamente l’API di tracciamento BFE Express tramite l’[adattatore open source BFE Express di universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/bfe-express). Legge entrambi i gruppi di righe in ordine cronologico, usa città, filiale o paese come luogo e conserva il giorno di consegna previsto e il paese di destinazione. Un orario con offset GMT mantiene esattamente quell’istante; uno senza offset viene datato sul fuso del luogo (ora di Buenos Aires per Ezeiza, ora di Lima per Lima, ora di Montevideo per Montevideo), una scansione senza luogo su quello della scansione vicina, altrimenti sull’ora di Buenos Aires, e ogni scansione appare con l’ora del tuo dispositivo. Gli indirizzi civici e le foto di prova di consegna presenti sul portale BFE vengono esclusi.

Peek mostra le righe di BFE Express come «In transito», tranne `Reparto en curso` («In consegna») ed `Entregado` («Consegnato»). Segna il pacco «Consegnato» anche quando lo stato proprio di BFE dice `Delivered`.

Mentre tieni il pacco aperto in Peek, o il giorno della consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti; nel giorno in cui BFE Express mostra `Reparto en curso`, ogni 2 minuti dalle 6:00 sull’orologio della scansione più recente finché il tracker BFE Express risponde. Altrimenti Peek controlla ogni ora. Dopo 12 ore con solo «Etichetta creata» o «Preso in carico», o dopo due giorni senza novità, controlla ogni ora comunque tu lo segua, e ogni 6 ore dopo sette giorni su «Etichetta creata». Quando BFE Express non risponde e Peek segue il pacco solo tramite servizi di tracciamento, controlla ogni ora, ogni 3 ore dopo una giornata ferma e fino a ogni 15 minuti nel giorno in cui il pacco è in consegna, mentre hai il pacco aperto o le notifiche attive.

Quando la scansione più recente risale a quattro giorni prima e il pacco è ancora su «Etichetta creata», «Preso in carico» o «In transito», Peek lo segnala con il giorno di quella scansione, ad esempio «Nessun aggiornamento da lun 12 ott». La pagina del pacco mostra quindi «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a BFE Express.», insieme al link «Chiedi aiuto a BFE Express» verso la pagina Contact Us di BFE Express. Dopo tre settimane senza nuove scansioni mostra «Niente di nuovo da più di tre settimane. Se il pacco non è ancora arrivato, contatta il venditore. Peek continua a chiedere a BFE Express.»

Usa le impostazioni di avviso di Peek — «Ogni scansione», «Passaggi importanti» o «Solo la consegna» — per decidere quali eventi generano notifiche.

## Contattare BFE Express

BFE Express pubblica gli indirizzi email dell’assistenza clienti regionale sulla pagina [Contact Us](https://www.bfeexpress.com/contactUS), tutti attivi dal **lunedì al venerdì, 9:00 – 18:00** (festivi esclusi):

- **Argentina:** `info@ar.bfeexpress.com`
- **Perù:** `info@pe.bfeexpress.com`
- **Uruguay:** `info@uy.bfeexpress.com`
- **Stati Uniti:** `info@us.bfeexpress.com`
- **Paraguay:** `info_py@bfeexpress.com`
- **Altre regioni supportate (Colombia, Ecuador, Serbia, Dominica):** `info@other.bfeexpress.com`
- **Sito ufficiale e tracciamento:** [bfeexpress.com](https://www.bfeexpress.com/) e [bfeexpress.com/tracking](https://www.bfeexpress.com/tracking)

Tieni pronto il codice completo `CK...` o `WYB...` quando scrivi all’assistenza del tuo paese.

## Domande frequenti

### Perché il tracciamento BFE Express inizia dall’aeroporto di destinazione senza scansioni precedenti?

Il tracker BFE Express può iniziare con `Arrived at Airport of destination`, l’arrivo del volo nel paese di destinazione. Prima di quel momento, verifica la spedizione nella pagina dell’ordine presso il venditore.

### Perché le mie scansioni BFE Express sono sia in inglese sia in spagnolo?

BFE Express registra in inglese l’arrivo internazionale in aeroporto, la dichiarazione doganale e il passaggio all’ultimo miglio, mentre il deposito locale che effettua la consegna finale pubblica le proprie scansioni in spagnolo (fino a `Reparto en curso` ed `Entregado`).

### Cosa significano le cifre `54`, `512` e `598` nei codici `CK`?

Identificano il paese di destinazione: `CK54...` è usato per l’Argentina (16 o 17 caratteri), `CK512...` per il Perù (17 caratteri) e `CK598...` per l’Uruguay (17 caratteri). Le spedizioni verso il Perù possono usare anche il formato di 12 caratteri `WYB` + 9 cifre.

### Cosa faccio se BFE Express non trova il mio codice?

Controlla che non manchino caratteri nel tuo codice `CK` o `WYB` e chiedi al venditore se ha già consegnato il pacco a BFE Express.

:::sources
- [BFE Express](https://www.bfeexpress.com/)
- [BFE Express Tracking](https://www.bfeexpress.com/tracking)
- [BFE Express Customer Support](https://www.bfeexpress.com/contactUS)
- [universal-parcel-scraper BFE Express adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/bfe-express)
:::
