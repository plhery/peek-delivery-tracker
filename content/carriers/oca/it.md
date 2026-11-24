---
title: Tracciamento OCA: numeri a 19 cifre, stati, filiali e aiuto
description: Traccia pacchi OCA ed e-Pak in Argentina: numeri a 19 cifre, stati in spagnolo, 2 visite a domicilio, ritiro in filiale per 5 giorni, SmartLocker e aiuto.
slug: tracciamento-oca
published: 2026-11-24
updated: 2026-11-24
---

OCA è un corriere argentino di pacchi e corrispondenza. La sua piattaforma ePak serve l’e-commerce, le grandi aziende e le piccole imprese in tutto il paese e collabora con Mercado Envíos. I destinatari tracciano i pacchi tramite la [ricerca spedizioni di OCA](https://www.oca.com.ar/Busquedas/Envios), ritirano i colli in giacenza presso le filiali Puntos OCA nelle province argentine oppure presso gli OCA SmartLockers.

Il tracciamento OCA registra in spagnolo ogni scansione di deposito, trasferimento tra filiali, visita a domicilio e finestra di ritiro.

## Formati dei codici di tracciamento OCA

I codici standard dei pacchi OCA e-Pak seguono una struttura a 19 cifre:

- **19 cifre con `0000` nelle posizioni 6–9** (come `3654800004829173605`) — il numero di lettera di vettura o ordine di ammissione (`número de guía` / `orden de admisión`) che il Centro assistenza OCA indica di usare per il tracciamento.
- **Un `-1` separato** — sulle etichette può comparire `-1` dopo il numero, come `3654800004829173605-1`. Il [Centro assistenza OCA](https://www.oca.com.ar/CentroDeAyuda) raccomanda di non digitarlo. Omettilo anche in Peek: Peek rimuove solo il trattino, trasformando `...-1` in un numero a 20 cifre sconosciuto a OCA.
- **Pacchi e buste affrancate** — OCA emette anche invii affrancati con 3 lettere maiuscole (`YAE`, `YAA`, `MDA`, `PAA`) più 8 cifre per i pacchi, oppure 2 lettere più 8 cifre per le buste affrancate, da inserire senza spazi né parentesi sul sito di OCA. La posta semplice non affrancata (`postales simples`) non dispone di tracciamento e viene lasciata direttamente nella cassetta o sotto la porta.

Sul sito di OCA è possibile sostituire la sequenza di zeri centrali con `//`; Peek richiede invece tutte le 19 cifre oppure un link diretto `oca.com.ar/Seguimiento/Paquetes/...` o un vecchio link e-Pak, che Peek legge in automatico. In Argentina Peek interroga OCA per un numero a 19 cifre con questa forma; altrove interroga OCA solo come ultima risorsa. Per altri formati regionali, consulta la [guida ai formati dei codici di tracciamento](guide:tracking-number-formats).

## Cosa significano gli stati di tracciamento OCA

Il servizio di tracciamento e-Pak di OCA riporta gli stati in spagnolo e aggiunge spesso la filiale competente dopo un trattino (`- Suc: ...`) o una motivazione tra parentesi.

| Dicitura di stato OCA | Cosa significa |
| --- | --- |
| `En proceso de Retiro` | Il ritiro presso il mittente è programmato; OCA non ha ancora preso in carico il pacco. |
| `Retirado` / `Retirado en Origen` | OCA ha ritirato il pacco presso il mittente. |
| `En Proceso en OCA` / `Procesado en OCA` | Lavorato in un centro di smistamento o una filiale OCA. |
| `En viaje a Centro de Distribución de Destino` / `En viaje a Sucursal de Destino` / `En Tránsito a Suc. de Destino` | In transito verso l’hub di destinazione o la filiale di consegna. |
| `Arribado a Centro de Distribución de Destino` / `Llegó a nuestra sucursal` | Arrivato all’hub di destinazione o alla filiale OCA locale. |
| `Programado para visita a domicilio` | Inserito nella programmazione consegne (`en programación`) della filiale locale in vista di una visita a domicilio. |
| `Visita a Domicilio en Curso` | In consegna oggi con il corriere per la consegna a domicilio. |
| `No entregado (No Visitado)` / `No entregado (...)` | Consegna non riuscita; il testo tra parentesi indica il motivo (ad esempio non visitato o fermato su richiesta del mittente). |
| `Reprogramado para nueva visita` | Riprogrammato per un successivo tentativo di consegna. |
| `En Espera de Retiro por Sucursal` / `Disponible para que lo retires en tu Sucursal` | In giacenza presso la filiale OCA assegnata, pronto per il ritiro. |
| `Estado de Visita ONLINE (Entregado)` / `Entregado` | Consegnato al destinatario o ritirato in filiale. |
| `Acuse digitalizado` | Prova di consegna scansionata nell’impianto OCA giorni dopo la consegna — solo amministrativa, non sposta il pacco. |
| `Devolución en Proceso` / `Devuelto al Remitente` | In rientro verso il mittente o già restituito. |

## Consegne a domicilio, filiali e SmartLockers

Il [Centro assistenza OCA](https://www.oca.com.ar/CentroDeAyuda) stabilisce regole chiare per le consegne in tutta l’Argentina:

- **Due tentativi di consegna a domicilio:** OCA effettua due visite senza appuntamento dal lunedì al sabato, dalle 09:00 alle 18:00. Chiunque abbia almeno 18 anni all’indirizzo può ricevere il pacco mostrando il proprio DNI (documento d’identità nazionale). Le giornate di pioggia possono limitare i giri di consegna.
- **Ritiro in filiale (5 giorni lavorativi):** se entrambe le visite falliscono, il pacco resta fermo per **5 giorni lavorativi** nella filiale Punto OCA assegnata (trova le filiali tramite [Buscador de Puntos OCA](https://www.oca.com.ar/Busquedas/Sucursales)). Il destinatario deve presentare il proprio DNI e il codice di tracciamento; una persona delegata maggiorenne deve portare copia del DNI del destinatario, delega scritta a mano e codice di tracciamento. I pacchi non ritirati tornano al mittente.
- **SmartLockers (48 ore + estensione facoltativa):** quando OCA deposita un pacco in uno SmartLocker, ricevi un’e-mail e/o un SMS con codice QR e PIN alfanumerico. Hai **48 ore consecutive** per il ritiro; dopo i promemoria, OCA invia un link per pagare con carta fino a **3 giorni extra di giacenza** prima del rientro al mittente.
- **Indirizzo errato o pacchi restituiti:** OCA non consente di interrompere una spedizione né di modificare l’indirizzo di consegna durante il transito. Attendi la conclusione delle visite per ritirare il pacco in filiale (oppure lascialo rientrare al mittente e chiedigli di rispedirlo). Per altre opzioni dopo una consegna mancata, consulta la [guida alla consegna mancata](guide:missed-delivery).
- **Spedizioni speciali (DNI, passaporti, carte OCA Geo, buste):** i documenti DNI vengono consegnati solo a domicilio (con ricevuta RENAPER) e non possono essere ritirati in filiale; i passaporti passano al ritiro in filiale dopo due visite a domicilio. Le carte OCA Geo richiedono la consegna diretta al titolare. Le buste affrancate ricevono due visite a domicilio e tornano al mittente senza giacenza in filiale.
- **48 ore lavorative per la prima scansione:** OCA segnala che le spedizioni appena consegnate in filiale possono impiegare fino a **48 ore lavorative** per comparire online. Se dopo 48 ore lavorative non compare nulla, solo il mittente può aprire una segnalazione. Vedi [quando il tracciamento smette di aggiornarsi](guide:tracking-not-updating) per i controlli generali.

Per altri corrieri nazionali argentini, confronta con [Andreani](carrier:andreani).

## Tracciare OCA con Peek

Incolla un numero OCA a 19 cifre senza `-1`, oppure un link `oca.com.ar/Seguimiento/Paquetes/...`, in Peek. Se Peek non seleziona automaticamente OCA, scegli OCA tramite **Cambia** quando aggiungi il pacco oppure **Cambia corriere** nella pagina di tracciamento.

Peek interroga direttamente il web service e-Pak `Tracking_Pieza` di OCA tramite l’[adattatore OCA open source di universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/oca). Ogni data e ora argentina viene salvata come istante preciso in `America/Argentina/Buenos_Aires` (`-03:00`), i nomi delle filiali vengono estratti in modo pulito nel campo località e le voci amministrative `Acuse digitalizado` non sovrascrivono né alterano mai una consegna già completata. Il flusso e-Pak di OCA non include data stimata di consegna né peso del pacco.

Peek traduce gli stati OCA in fasi chiare: **Etichetta creata** (`En proceso de Retiro`), **Preso in carico** (`Retirado`), **In transito** (centri di smistamento e trasferimenti tra filiali), **In consegna** (`Programado para visita a domicilio` e `Visita a Domicilio en Curso`), **Tentativo di consegna** (`No entregado` e `Reprogramado para nueva visita`), **Pronto per il ritiro** (`En Espera de Retiro por Sucursal`), **Consegnato** (`Entregado`) e **Restituito al mittente** (`Devolución en Proceso` / `Devuelto al Remitente`).

Mentre tieni il pacco aperto in Peek, o il giorno della consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti; nel giorno in cui OCA mostra `Programado para visita a domicilio` o `Visita a Domicilio en Curso`, ogni 2 minuti dalle 6:00 ora argentina, finché il tracciamento OCA risponde. Altrimenti Peek controlla ogni ora. Dopo 12 ore con solo «Etichetta creata» o «Preso in carico», o dopo due giorni senza novità, controlla ogni ora comunque tu lo segua, e ogni 6 ore dopo sette giorni su «Etichetta creata». Quando OCA non risponde e Peek segue il pacco solo tramite servizi di tracciamento, controlla ogni ora, ogni 3 ore dopo una giornata ferma e fino a ogni 15 minuti nel giorno in cui il pacco è in consegna, mentre hai il pacco aperto o le notifiche attive.

Quando la scansione più recente risale a quattro giorni prima e il pacco è ancora su «Etichetta creata», «Preso in carico» o «In transito», Peek lo segnala con il giorno di quella scansione, ad esempio «Nessun aggiornamento da lun 12 ott». La pagina del pacco mostra quindi «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a OCA.», con un link «Chiedi aiuto a OCA» al Centro assistenza di OCA. Dopo tre settimane senza nuove scansioni mostra «Niente di nuovo da più di tre settimane. Se il pacco non è ancora arrivato, contatta il venditore. Peek continua a chiedere a OCA.»

Scegli **Ogni scansione**, **Passaggi importanti** o **Solo la consegna** nelle impostazioni degli avvisi di Peek per stabilire quali aggiornamenti inviano una notifica.

## Contattare OCA

- **Centro assistenza e modulo di supporto online:** [OCA Centro de Ayuda](https://www.oca.com.ar/CentroDeAyuda) (include categorie FAQ e modulo di contatto diretto in fondo alla pagina)
- **Ricerca spedizioni ufficiale:** [OCA Búsqueda de envío](https://www.oca.com.ar/Busquedas/Envios)
- **Ricerca filiali:** [Buscador de Puntos OCA](https://www.oca.com.ar/Busquedas/Sucursales)
- **Sito principale:** [oca.com.ar](https://www.oca.com.ar/)
- **Prima il mittente per codici mancanti o rispedizioni:** OCA stabilisce che solo il mittente è autorizzato a indagare su una spedizione priva di record dopo 48 ore lavorative dalla consegna in filiale, o a organizzare una nuova spedizione in caso di rientro.

## Domande frequenti

### Perché il mio codice di tracciamento OCA termina con `-1`?

Il Centro assistenza OCA indica di omettere il `-1` separato e tracciare con il numero di guida o ammissione a 19 cifre. Fai lo stesso in Peek: non rimuove `-1`, e il numero che lo include non viene riconosciuto da OCA.

### Per quanto tempo OCA trattiene un pacco in filiale o in uno SmartLocker?

OCA custodisce i pacchi nelle filiali Puntos OCA per **5 giorni lavorativi** dopo tentativi di consegna falliti o l’arrivo in filiale. Negli SmartLockers hai **48 ore consecutive** gratuite, con la possibilità di pagare online fino a **3 giorni aggiuntivi** prima del reso al mittente.

### Posso cambiare l’indirizzo di consegna dopo che OCA ha ritirato il pacco?

No. Il Centro assistenza OCA afferma che né il percorso della spedizione né l’indirizzo di consegna possono essere modificati durante il transito. Dopo le visite a domicilio puoi ritirare il pacco di persona nella filiale OCA assegnata con il tuo DNI, oppure lasciarlo tornare al mittente.

### Perché il mio codice di tracciamento OCA non mostra ancora aggiornamenti?

OCA precisa che un pacco può richiedere fino a **48 ore lavorative** dall’accettazione in filiale per apparire sul sito di tracciamento. Trascorse 48 ore lavorative senza riscontri, contatta il mittente, unico soggetto autorizzato ad aprire una verifica con OCA.

:::sources
- [OCA Argentina](https://www.oca.com.ar/)
- [OCA Centro de Ayuda](https://www.oca.com.ar/CentroDeAyuda)
- [OCA Búsqueda de envío](https://www.oca.com.ar/Busquedas/Envios)
- [OCA Buscador de Puntos OCA](https://www.oca.com.ar/Busquedas/Sucursales)
- [adattatore OCA di universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/oca)
:::
