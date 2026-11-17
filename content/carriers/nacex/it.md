---
title: Tracciamento NACEX: numero, stati in spagnolo e contatti
description: Traccia un pacco NACEX dalla Spagna o dal Portogallo: codice 0100/00000005, stati in spagnolo spiegati in italiano, punti NACEX.shop e assistenza.
slug: nacex-tracciamento
published: 2026-11-17
updated: 2026-11-17
---

NACEX è un corriere espresso del gruppo Logista con agenzie in Spagna, Portogallo, Andorra e Benelux e collegamenti stradali e aerei verso il resto d'Europa. Dall'Italia o dalla Svizzera italiana lo incontri soprattutto acquistando da negozi spagnoli o portoghesi, oppure ricevendo un pacco nella penisola iberica. Inserisci il codice (quattro cifre, barra obliqua, otto cifre) nella [pagina iniziale di NACEX](https://www.nacex.es/irHome.do) o nel riquadro qui sopra: non basta il solo numero d'ordine o il CAP.

## Numero di tracciamento NACEX: formato ed errori comuni

Il riquadro NACEX mostra il modello `0000/00000000`, mentre la [pagina di tracciamento NACEX](https://www.nacex.es/irSeguimiento.do) separa *Agencia* e *Albarán*. Con cifre inventate:

:::anatomy 0100 / 00000005
- 0100 | Agenzia: codice a 4 cifre della filiale affiliata NACEX di partenza
- / | Barra obliqua tra agenzia e spedizione
- 00000005 | Bolla (*albarán*): numero a 8 cifre della spedizione
:::

NACEX non indica in quale messaggio arrivi il codice né il mittente degli SMS; vedi [come trovare il numero di tracciamento](guide:find-tracking-number) per cercarlo tra e-mail del negozio, account cliente ed etichetta.

| Codice disponibile | Che cos'è e come tracciarlo |
| --- | --- |
| `0100/00000005` | Numero NACEX completo: incollalo così com'è (spazi attorno alla barra come `0100 / 00000005` sono accettati inserendo il numero). |
| `010000000005` | 12 cifre senza barra: probabile codice NACEX privo di `/` dopo la quarta cifra. Senza `/`, Peek non propone NACEX e controlla corrieri con [codici a 12 cifre](guide:tracking-number-formats) come [BRT](carrier:brt). Aggiungi `/`. |
| `0100000005` | Fino a 10 cifre: probabile «riferimento alternativo» (*referencia alternativa*) sull'etichetta. NACEX non dice dove inserirlo; Peek non interroga NACEX con questo formato. |
| `3230 0000 0000 0000` | 16 cifre (o 23 cifre) senza barra: [Correos Express](carrier:correos-express) o un altro corriere, non NACEX. |

Verifica anche di non aver scritto la lettera `O` al posto dello zero `0`. Se il [modulo esterno NACEX](https://www.nacex.es/seguimientoFormularioExterno.do?intcli=0000/00000000) risponde *«No existe ningún albarán introducido en el sistema cumpliendo los criterios especificados. Consulte con su agencia NACEX más cercana»* («Nessuna bolla nel sistema soddisfa i criteri indicati. Consulta l'agenzia NACEX più vicina»), ricontrolla le quattro cifre, la barra e le otto cifre finali, o attendi qualche ora se la spedizione è appena stata creata.

## Stati di tracciamento NACEX: traduzione dallo spagnolo

Il sito `nacex.es` offre un selettore di lingua (spagnolo, portoghese, inglese, catalano, francese e tedesco, non italiano), ma non pubblica un glossario degli stati e le diciture lette da Peek sono in spagnolo:

| Stato NACEX (spagnolo) | Cosa significa per te |
| --- | --- |
| `Notificado` | «Notificato»: registrazione elettronica della spedizione il giorno della prenotazione, prima o dopo la presa in carico; non una scansione fisica. |
| `Aceptada` | «Accettata»: pacco preso in carico in agenzia (seguito da `WEB`, indica solo una prenotazione online non ancora consegnata a NACEX). |
| `En tránsito` | «In transito»: trasferimento tra agenzie affiliate e piattaforme NACEX. |
| `En reparto` | «In consegna»: l'autista ha il pacco sul furgone per la consegna di giornata. |
| `Ausente` | «Assente»: tentativo non riuscito perché non c'era nessuno all'indirizzo. |
| `Gestión de servicio, posible demora` | «Gestione servizio, possibile ritardo»: imprevisto operativo gestito dall'agenzia (es. giro non completato). |
| `Solución de entrega concertada` | Concordata una nuova consegna dopo un imprevisto. |
| `Solución de entrega en punto` | Disposto l'invio a un punto NACEX, ma il pacco non è ancora pronto al ritiro. |
| `Cambio de dirección` | «Cambio di indirizzo»: inoltro verso un nuovo recapito autorizzato. |
| `Disponible en punto NACEX` | «Disponibile presso un punto NACEX»: pronto per il ritiro nel punto o in agenzia. |
| `Contacta con agencia` | «Contatta l'agenzia»: la filiale di consegna chiede un tuo riscontro. |
| `Solucionado sin OK` | «Chiuso senza OK»: pratica chiusa senza consegna, ad esempio per reso al mittente. |
| `Entregado` | «Consegnato»: spedizione recapitata. |

Attenzione: `Solución de entrega concertada` e `Solución de entrega en punto` registrano solo l'istruzione; attendi `Disponible en punto NACEX` prima di andare a ritirare. `Sin estado` («senza stato») è una nota amministrativa che lascia invariata la fase precedente, mentre con `Solucionado sin OK` conviene sentire NACEX o il venditore. Per le fasi generali vedi [cosa significano gli stati di tracciamento](guide:tracking-statuses).

## Consegna NACEX, assenza e punti NACEX.shop

**Verso l'Italia e l'Europa:** dalla penisola iberica e dal Portogallo NACEX spedisce su strada in Europa con `Business Europa` (aziende) ed `Ecommerce Europa` (monocollo per privati fino a 40 kg a domicilio o 20 kg presso punti di ritiro secondo disponibilità) e per via aerea nel mondo (`Internacional exprés`) tramite partner internazionali.

**Se non sei a casa:** secondo le condizioni generali NACEX 2026, alle consegne private il corriere lascia un avviso per concordare una seconda e definitiva consegna (*segunda y definitiva entrega*). Le FAQ precisano «quando possibile» (*siempre que sea posible*) e aggiungono che, se ha il tuo telefono, l'agenzia ti chiama per fissare il passaggio. Se sai in anticipo di non esserci, avvisa l'assistenza o la tua agenzia.

**Pre-allerta e *Mi Entrega Preferida*:** nei servizi con *Pre-Alerta*, NACEX invia via SMS o e-mail un link a *Mi Entrega Preferida* per cambiare data o fascia oraria oppure deviare un pacco singolo a un'agenzia o punto NACEX.shop. Con *Entrega con PIN* la consegna avviene dietro codice ricevuto nella pre-allerta.

**NACEX.shop e locker:** oltre 4.000 negozi convenzionati e *Smart Lockers* in Spagna e Portogallo, molti aperti anche nel fine settimana. Un monocollo diretto a un punto NACEX.shop è ritirabile dal giorno lavorativo successivo dalle 09:00 (o entro le 12:00 se il negozio apre più tardi) dopo l'avviso; trovi le sedi nel [localizzatore NACEX](https://www.nacex.es/irCerca.do). NACEX non pubblica la durata massima di giacenza nei punti NACEX.shop o nei locker.

**Ritiro in agenzia NACEX:** in una filiale affiliata la giacenza massima è di **15 giorni**, poi il pacco torna al mittente con spese di rientro. Avvisa prima l'agenzia; il ritiro è dal lunedì al venerdì (08:00–20:00 continuato oppure 08:00–14:00 e 16:00–20:00 secondo la sede). Un delegato deve portare una **delega firmata** e la **fotocopia del tuo documento**.

**Sabato, festivi e rifiuto:** sabato, domenica e festivi non sono lavorativi. La consegna al sabato (09:00–13:00) è un supplemento nei comuni con agenzia NACEX ed entro 15 km (solo documenti per Canarie e mete doganali). Un pacco già partito non si annulla, ma puoi rifiutarlo alla consegna o avvisando l'agenzia.

## Dogana e IVA: Italia, Svizzera e territori speciali

Dalla Spagna peninsulare, dalle Baleari o dal Portogallo continentale verso l'Italia i pacchi circolano nel territorio IVA dell'UE senza dogana. Fanno eccezione:

- **Svizzera:** si applica l'IVA svizzera all'importazione dell'**8,1%** (**2,6%** ridotta). Secondo l'UDSC, dazi e tributi **inferiori a 5 CHF** non vengono riscossi (fino a 62 CHF imponibili all'8,1% o 193 CHF al 2,6%); oltre tale soglia maturano IVA, eventuali dazi sul peso e spese del vettore (come [La Posta Svizzera](carrier:swiss-post)); vedi [dogana e dazi sui pacchi](guide:customs).
- **Canarie, Ceuta, Melilla, Andorra e Gibilterra:** le spedizioni di merci verso queste destinazioni passano dalla dogana; NACEX richiede fattura commerciale (o dichiarazione giurata tra privati) e segnala possibili tempi doganali aggiuntivi.

## Pacco NACEX in ritardo, fermo o danneggiato

I reclami formali spettano al cliente che ha pagato il trasporto (di norma il negozio online), tramite la propria agenzia NACEX:

- **Tracciamento fermo o mancata consegna:** le FAQ indicano `atencion.cliente@nacex.com` o `900 100 000`, senza attesa minima. Consulta anche [tracciamento che non si aggiorna](guide:tracking-not-updating) e [`Entregado` ma non ricevuto](guide:delivered-not-received).
- **Ritardo:** reclamabile dal cliente entro **31 giorni solari** (*31 días naturales*) se il termine garantito è superato senza causa giustificata (con possibili rallentamenti per Black Friday, Natale o dogana).
- **Perdita, ammanchi o danni:** NACEX accetta il reclamo entro **7 giorni solari** (dalla consegna per ammanchi o danni), con foto e fattura. L'art. 60 della legge spagnola *Ley 15/2009* chiede riserve scritte alla consegna, o entro 7 giorni solari per danni non visibili.

Negli acquisti online nell'UE, *Your Europe* ricorda che il venditore risponde dei beni fino alla consegna fisica (entro **30 giorni** salvo diverso accordo): contatta quindi sempre anche il venditore.

## Contatti e assistenza clienti NACEX

Sulle pagine spagnole NACEX indica questi recapiti (senza orari pubblicati né WhatsApp):

- **Telefono (Spagna e Andorra):** `900 100 000` (gratuito in Spagna da fisso e mobile secondo FAQ NACEX e CNMC; NACEX non dice se funziona dall'estero).
- **E-mail:** `atencion.cliente@nacex.com` (e `shop@nacex.com` sulla pagina contatti).
- **Chat online:** sulla [pagina di contatto NACEX](https://www.nacex.es/irContactenosContacto.do) (*«Chatee ahora con nuestros agentes»*).
- **Agenzie e reclami:** sedi nel [localizzatore NACEX](https://www.nacex.es/irCerca.do); regole per i clienti nella [pagina reclami NACEX](https://www.nacex.es/irReclamaciones.do) (prima verifica entro 48 ore lavorative, chiusura prevista entro 7 giorni solari secondo il caso).

## Tracciare un pacco NACEX con Peek

[Peek](/) riconosce all'istante il formato di quattro cifre, barra obliqua e otto cifre (`0100/00000005`) sotto «Corriere rilevato» (con «Cambia» se serve), così come un link `nacex.es` con `intcli=` o l'intera e-mail di spedizione.

Peek controlla fino a ogni 10 minuti, e fino a ogni 2 minuti dalle 06:00 (ora locale) nel giorno in cui il pacco entra «In consegna», finché risponde il tracciamento NACEX (tramite servizi di tracciamento, fino a ogni 15 minuti). Dopo due giorni senza novità — o dopo 12 ore su «Etichetta creata» o «Preso in carico» — controlla ogni ora. Puoi scegliere avvisi per «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek mostra lo stato tradotto, la data di consegna stimata in viaggio e ogni scansione su mappa; poiché NACEX indica l'ora solo alla consegna, per le tappe precedenti vedi il giorno. Peek esclude destinatario, prova di consegna, note di reparto, recapiti e riferimento del negozio, e non può modificare la consegna né contattare NACEX per te.

## Domande frequenti sul tracciamento NACEX

### Che cos'è NACEX ed è affidabile?

Sì. Fondata nel 1995 e parte del gruppo Logista (DRONAS 2002, S.L.U.), NACEX conta oltre 300 agenzie affiliate e 35 piattaforme in Spagna, Portogallo, Andorra e Paesi Bassi/Benelux. Usa i domini `nacex.es`, `nacex.pt` e `nacex.com` e indirizzi `@nacex.com`.

### Quanto tempo impiega NACEX a consegnare?

Nell'e-commerce iberico NACEX indica 24 ore (`e-NACEX`, `e-NACEX.shop`) o 48–72 ore (`e-NACEX economy`). `NACEX 19:00 H` consegna entro le 19:00 del giorno lavorativo successivo in Spagna peninsulare, Andorra, Portogallo e Baleari, o entro le 13:00 del secondo giorno lavorativo oltre 50 km dall'agenzia. Per via aerea occorrono 24–48 ore per le Canarie e 2–5 giorni per Azzorre e Madera; con `Ecommerce Europa` i tempi dipendono dal Paese di destinazione.

### Posso cambiare l'indirizzo di consegna di un pacco NACEX?

Il cambio di indirizzo civico va autorizzato dal cliente pagante (di norma il negozio): è gratuito nella zona della stessa agenzia NACEX e a pagamento verso un'altra filiale. Con la *Pre-Alerta*, invece, il link *Mi Entrega Preferida* permette di deviare un monocollo a un punto NACEX.shop o a un'agenzia NACEX.

### Perché il mio codice NACEX a 12 cifre non funziona?

Perché il modello NACEX ha una barra obliqua dopo le prime quattro cifre (`0100/00000005`). Senza `/`, Peek interroga corrieri a 12 cifre come [BRT](carrier:brt): aggiungi `/` dopo la quarta cifra.

:::sources
- [NACEX: Pagina iniziale](https://www.nacex.es/irHome.do) – casella di ricerca, Logista, rete, lingue
- [NACEX: Seguimiento](https://www.nacex.es/irSeguimiento.do) – agenzia/spedizione, riferimento alternativo
- [NACEX: Formulario de Seguimiento](https://www.nacex.es/seguimientoFormularioExterno.do?intcli=0000/00000000) – spedizione non trovata
- [NACEX: FAQs](https://www.nacex.es/irFaqs.do) – contatti, 15 giorni, delega, cambio indirizzo, rifiuto
- [NACEX: FAQ consegne](https://www.nacex.es/visualizarfaq.do?category=900) – avviso, ritiro in agenzia
- [NACEX: FAQ servizi](https://www.nacex.es/visualizarfaq.do?category=901) – sabato 9–13, Pre-Alerta
- [NACEX: Contacto](https://www.nacex.es/irContactenosContacto.do) – telefono, e-mail, chat
- [NACEX: Reclamaciones](https://www.nacex.es/irReclamaciones.do) – 31 e 7 giorni, 48 ore lavorative
- [NACEX: Servicios internacionales](https://www.nacex.es/irServicioNacional.do?seccion=internacionales) – Europa, 40 kg / 20 kg
- [NACEX: Servicios eCommerce](https://www.nacex.es/irServicioNacional.do?seccion=ecommerce) – 24 h, 48/72 h
- [NACEX: Servicios urgentes](https://www.nacex.es/irServicioNacional.do?seccion=urgentes) – 19:00 H, Canarie
- [NACEX: Servicios Portugal](https://www.nacex.es/irServicioNacional.do?seccion=portugal) – Azzorre, Madera
- [NACEX: Mi Entrega Preferida](https://www.nacex.es/irServicioNacional.do?seccion=mep) – Pre-Alerta, locker
- [NACEX: Servicios de valor añadido](https://www.nacex.es/irServicioValor.do) – Entrega con PIN
- [NACEX: NACEX.shop](https://www.nacex.es/irEnvioSostenible.do) – dalle 09:00 / 12:00
- [NACEX: Localizador de puntos](https://www.nacex.es/irCerca.do) – punti e agenzie
- [NACEX Blog: NACEX.shop](https://blog.nacex.es/2025/las-entregas-en-puntos-nacex-shop-se-duplican-en-el-ultimo-ano/) – oltre 4.000 punti
- [NACEX: ¿Quiénes somos?](https://www.nacex.es/irEmpresaQuienes.do) – 1995, Logista, Paesi Bassi
- [NACEX: Tramitaciones aduaneras](https://www.nacex.es/irTramitacionesAduaneras.do?seccion=tramaduan) – dogana
- [NACEX: Condiciones de transporte 2026](https://www.nacex.es/pages/img/servicios/condiciones_tramitaciones/condiciones-generales-2026-ES.pdf) – seconda consegna, 50 km, 15 km
- [BOE: Ley 15/2009](https://www.boe.es/buscar/act.php?id=BOE-A-2009-18004) – 7 giorni (art. 60)
- [CNMC: Numeri 800 e 900](https://blog.cnmc.es/2019/04/24/llamadas-a-numeros-800-y-900-gratis-para-el-consumidor-y-quien-paga/) – 900 gratuiti
- [Your Europe: Consegna](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_it.htm) – 30 giorni, responsabilità venditore
- [Your Europe: IVA](https://europa.eu/youreurope/citizens/consumers/shopping/vat/index_it.htm) – area IVA UE
- [UDSC: Lettere e pacchi](https://www.bazg.admin.ch/it/ricevere-lettere-e-pacchi) – 8,1% / 2,6%, sotto 5 CHF
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – cosa legge Peek
:::
