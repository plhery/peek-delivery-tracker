---
title: Tracciamento Correos de México: codici S10 MX e reclami
description: Traccia un pacco Correos de México (SEPOMEX / Mexpost): codici S10 MX, ricerca per anno, stati spagnoli, dogana, reclami entro 30 giorni e aiuto.
slug: tracciamento-correos-de-mexico
published: 2026-11-24
updated: 2026-11-24
---

Correos de México (*Servicio Postal Mexicano* / *SEPOMEX*) è il servizio postale nazionale del Messico, che gestisce raccomandate nazionali (*Registrados*), pacchi espressi *Mexpost* e spedizioni postali internazionali UPU sia in partenza dal Messico sia in arrivo dagli operatori postali di tutto il mondo. Puoi controllare una spedizione sulla [pagina di tracciamento di Correos de México](https://www.correosdemexico.gob.mx/SSLServicios/SeguimientoEnvio/Seguimiento.aspx) (inserendo sia il codice di tracciamento sia l’anno di spedizione, *Ejercicio*), oppure incollare il codice nel campo qui sopra.

## Codici di tracciamento Correos de México: formati UPU S10

Controlla la ricevuta dell’ufficio postale messicano, la lettera di vettura Mexpost o l’e-mail di spedizione del venditore estero. Correos de México traccia codici UPU S10 da 13 caratteri — sia i codici in uscita terminanti in `MX` sia gli invii S10 esteri diretti in Messico (come `...US`, `...CN`, `...ES` o `...JP`):

| Formato (inventato) | Che cos’è |
| --- | --- |
| `RR482917367MX` | Raccomandata (*Correo Registrado*) spedita dal Messico (UPU S10 terminante in `MX`). |
| `MP482917367MX` | Pacco postale nazionale o espresso *Mexpost* (UPU S10 terminante in `MX`). |
| `EE482917367MX` | Posta espressa internazionale EMS dal Messico (UPU S10 terminante in `MX`). |

**Selettore dell’anno (*Ejercicio*) su SEPOMEX.** A differenza della maggior parte dei tracker, il [portale Seguimiento di Correos de México](https://www.correosdemexico.gob.mx/SSLServicios/SeguimientoEnvio/Seguimiento.aspx) archivia le scansioni per anno solare (`2026`, `2025`, `2024`, ecc.). Se un pacco spedito a fine dicembre continua a viaggiare a gennaio, sul sito potrebbe servire controllare entrambi gli anni per vedere tutto il percorso. Per corrieri privati messicani come [Estafeta](carrier:estafeta), [J&T Express Mexico](carrier:j-and-t-mx) o [Paquetexpress](carrier:paquetexpress), vedi [formati dei codici di tracciamento](guide:tracking-number-formats).

## Cosa significano gli stati di tracciamento di Correos de México

Correos de México mostra descrizioni degli eventi in spagnolo accanto all’ufficio postale messicano o all’ufficio di scambio estero e all’orario:

| Correos de México indica | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `Depósito en Oficina de Correos` / `Deposito del Cliente en ...` | Accettato allo sportello di un ufficio postale messicano o spedito all’estero dall’operatore postale d’origine. | «Preso in carico» |
| `En tránsito hacia destino`, `Recepción en Oficina de Correos`, `En zona de intercambio int. hacia el país destino, previo Aduana`, `Pieza depositada en valija y enviada a oficina del País destino` | In movimento tra uffici di distribuzione messicani oppure inserito in dispaccio e spedito all’estero. | «In transito» |
| `Arribo a Zona Intercambio México, en espera de ingreso a la Aduana`, `Inicio de la revisión aduanal en país destino` | Arrivato all’ufficio di scambio internazionale in attesa o in corso di ispezione doganale. | «In dogana» |
| `Revisión aduanal concluida` | Ispezione doganale conclusa; invio rimesso in circolo nella rete postale. | «In transito» |
| `Con mensajero para entrega` | Affidato al portalettere locale per la consegna odierna. | «In consegna» |
| `Puesta en ventanilla` | In giacenza allo sportello dell’ufficio postale locale indicato, pronto per il ritiro. | «Pronto per il ritiro» |
| `Pieza devuelta ...` (es. `por domicilio destino incorrecto`) | In restituzione al mittente con la motivazione riportata. | «Restituito al mittente» |
| `Entrega` / `Entrega Internacional` | Consegnato in Messico o confermato consegnato all’estero dalle poste di destinazione. | «Consegnato» |

## Ritiro allo sportello (*Puesta en ventanilla*), dogana e reclami

Secondo [Servicio Postal Mexicano su gob.mx](https://www.gob.mx/correosdemexico) e la pagina ufficiale [Reclamo de un Envío (Reclamo spedizione)](https://www.gob.mx/correosdemexico/es/acciones-y-programas/reclamo-de-un-envio):

- **Ritiro allo sportello (*Puesta en ventanilla*):** quando compare `Puesta en ventanilla`, il pacco ti attende presso l’ufficio postale indicato nella colonna *Origen*.
- **Dogana in ingresso:** i pacchi postali diretti in Messico passano attraverso il controllo doganale messicano (`Inicio de la revisión aduanal...`). I tempi variano in base al periodo e alla valutazione doganale; vedi [pacchi fermi in dogana](guide:customs).
- **Presentare un reclamo formale (*Reclamo de un Envío*):** indagini postali e indennizzi si applicano **solo ai servizi Raccomandati (*Registrados*) e *Mexpost*** (non alla posta ordinaria non tracciata). Puoi aprire un reclamo quando una spedizione supera i tempi di consegna o arriva danneggiata — di persona in qualsiasi ufficio di Correos de México oppure online. Il tempo massimo di risposta di SEPOMEX per reclami Registrados e Mexpost è di **30 giorni lavorativi** (*30 días hábiles*).

## Pacco Correos de México fermo, senza aggiornamenti o restituito

- **Lunghe pause in dogana o nel transito estero:** i pacchi postali in entrata o uscita dal Messico restano spesso senza scansioni per giorni o settimane su `Arribo a Zona Intercambio México` o tra `Pieza depositada en valija` e l’arrivo a destinazione. Vedi [perché il tracciamento non si aggiorna](guide:tracking-not-updating).
- **Invii restituiti (`Pieza devuelta`):** quando SEPOMEX registra `Pieza devuelta` (ad esempio indirizzo errato o mancato ritiro allo sportello), anche i successivi `Puesta en ventanilla` ed `Entrega` riguardano la riconsegna al mittente originario.
- Per gli acquisti online contatta anche il venditore oltre ad aprire un reclamo. Vedi [consegnato ma non ricevuto](guide:delivered-not-received), [pacco danneggiato](guide:damaged-parcel), [pacco smarrito](guide:lost-parcel) e [restituito al mittente](guide:returned-to-sender).

## Assistenza clienti e contatti Correos de México

Canali ufficiali pubblicati su [gob.mx/correosdemexico](https://www.gob.mx/correosdemexico), [Seguimiento.aspx](https://www.correosdemexico.gob.mx/SSLServicios/SeguimientoEnvio/Seguimiento.aspx) e [Reclamo de un Envío](https://www.gob.mx/correosdemexico/es/acciones-y-programas/reclamo-de-un-envio):

- **E-mail ufficiale per informazioni:** `contacto@correosdemexico.gob.mx`.
- **Reclami e richieste:** [Reclamo de un Envío](https://www.gob.mx/correosdemexico/es/acciones-y-programas/reclamo-de-un-envio) oppure di persona presso il tuo ufficio postale messicano.
- **Controversie consumatori in Messico:** per controversie commerciali irrisolte, i consumatori possono rivolgersi a [PROFECO](https://www.gob.mx/profeco).

> Fai attenzione a SMS o messaggi WhatsApp falsi che si spacciano per Correos de México / SEPOMEX chiedendo un pagamento online con carta per «correggere il CAP» o «sbloccare un pacco». Verifica sempre il codice S10 direttamente su [correosdemexico.gob.mx](https://www.correosdemexico.gob.mx/SSLServicios/SeguimientoEnvio/Seguimiento.aspx) o scrivi a `contacto@correosdemexico.gob.mx`. Vedi [SMS truffa sulle consegne](guide:delivery-scam-texts).

## Tracciare un pacco Correos de México con Peek

[Peek](/) riconosce direttamente come Correos de México i link incollati di `correosdemexico.gob.mx/SSLServicios/SeguimientoEnvio/Seguimiento.aspx?guia=`. Quando incolli un codice UPU S10 da 13 caratteri che termina in `MX` (`RR482917367MX`, `MP482917367MX` o `EE482917367MX`, inventati), Peek interroga prima Correos de México e mostra «Correos de México ha questo pacco.» non appena lo conferma. Un pacco spedito dall’estero conserva il codice delle poste mittenti (che termina in `US`, `CN` ecc.), a cui Peek chiede; per seguirlo su Correos de México, tocca «Cambia» mentre aggiungi o «Cambia corriere» nella schermata del pacco e scegli Correos de México (cercare «SEPOMEX», «Mexpost» o «Mexico Post» lo trova ugualmente).

Peek legge direttamente il tracker di Correos de México risalendo agli anni precedenti offerti dal modulo affinché un viaggio a cavallo di Capodanno resti intero, e mostra la fase («Preso in carico», «In transito», «In dogana», «In consegna», «Pronto per il ritiro», «Restituito al mittente», «Consegnato»), il testo spagnolo della scansione, l’ufficio postale o di scambio, l’ufficio in cui l’invio attende quando mostra `Puesta en ventanilla` e l’orario di consegna se presente. Dopo `Pieza devuelta`, Peek mantiene i successivi eventi di sportello e consegna sotto «Restituito al mittente», perché riguardano il rientro al mittente e non una consegna a te. Correos de México fornisce orari senza fuso: Peek data ogni scansione sul fuso orario del rispettivo ufficio (Tijuana per una scansione a Tijuana, Città del Messico per Città del Messico) e la mostra con l’ora del tuo dispositivo. Peek omette il nome del firmatario, l’ora della ricerca e gli avvisi di pagina; il tracker non indica data stimata di consegna, peso, nome del servizio né foto di prova di consegna.

Mentre hai il pacco aperto in Peek, oppure il giorno della consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti; il giorno in cui Correos de México mostra `Con mensajero para entrega`, ogni 2 minuti dalle 6:00 secondo l’ora locale dell’ufficio dell’ultima scansione, finché risponde il tracker di Correos de México. Altrimenti Peek controlla ogni ora. Dopo 12 ore con solo «Etichetta creata» o «Preso in carico», oppure dopo due giorni senza novità, controlla ogni ora comunque tu lo segua, e ogni 6 ore dopo sette giorni su «Etichetta creata». Quando il tracker di Correos de México non risponde e Peek segue il pacco solo tramite servizi di tracciamento, controlla ogni ora, ogni 3 ore dopo una giornata ferma e fino a ogni 15 minuti nel giorno in cui il pacco è in consegna, mentre hai il pacco aperto o le notifiche attive.

Quando l’ultima scansione risale a quattro giorni prima e il pacco è ancora su «Etichetta creata», «Preso in carico» o «In transito», Peek lo segnala con la data di quella scansione, come «Nessun aggiornamento da lun 12 ott». La pagina del pacco mostra allora «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a Correos de México.», con il link «Chiedi aiuto a Correos de México» verso la pagina *Reclamo de un Envío*. Dopo tre settimane senza nuovi aggiornamenti mostra «Niente di nuovo da più di tre settimane. Se il pacco non è ancora arrivato, contatta il venditore. Peek continua a chiedere a Correos de México.»

Puoi scegliere gli avvisi per «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek si limita a monitorare gli aggiornamenti di tracciamento e non può sdoganare pacchi né presentare un *Reclamo de un Envío* presso SEPOMEX al posto tuo.

## Domande frequenti su Correos de México

### Quali codici di tracciamento funzionano con Correos de México?

Correos de México traccia codici UPU S10 della posta da 13 caratteri: 2 lettere + 9 cifre + 2 lettere paese (per esempio `RR482917367MX` o `MP482917367MX`, inventati), inclusa la posta S10 estera in ingresso in Messico.

### Cosa significa `Puesta en ventanilla`?

Significa che il pacco è in giacenza allo sportello dell’ufficio postale indicato nella colonna *Origen* della scansione per il ritiro di persona.

### Perché Correos de México non ha trovato il mio codice di tracciamento?

Su [Seguimiento.aspx](https://www.correosdemexico.gob.mx/SSLServicios/SeguimientoEnvio/Seguimiento.aspx) assicurati di scegliere l’anno di spedizione giusto (*Ejercicio*) — soprattutto per pacchi spediti a fine anno precedente — e tieni conto che la posta ordinaria non tracciata non genera scansioni.

### Come presento un reclamo a Correos de México?

Per invii Raccomandati (*Registrados*) e *Mexpost* in ritardo o danneggiati, presenta un *Reclamo de un Envío* in un ufficio postale o su [gob.mx/correosdemexico/es/acciones-y-programas/reclamo-de-un-envio](https://www.gob.mx/correosdemexico/es/acciones-y-programas/reclamo-de-un-envio) (risposta entro 30 giorni lavorativi) oppure scrivi a `contacto@correosdemexico.gob.mx`.

:::sources
- [Correos de México (SEPOMEX): portale gob.mx](https://www.gob.mx/correosdemexico) – portale ufficiale Servicio Postal Mexicano e `contacto@correosdemexico.gob.mx`
- [Correos de México: Seguimiento de Envíos](https://www.correosdemexico.gob.mx/SSLServicios/SeguimientoEnvio/Seguimiento.aspx) – ricerca S10 per anno (*Ejercicio*) e `contacto@correosdemexico.gob.mx`
- [Correos de México: Reclamo de un Envío](https://www.gob.mx/correosdemexico/es/acciones-y-programas/reclamo-de-un-envio) – idoneità Registrados & Mexpost, invio allo sportello/online e risposta in 30 giorni lavorativi (URL di aiuto collegato da Peek)
- [PROFECO](https://www.gob.mx/profeco) – Agenzia federale messicana per la tutela dei consumatori
- [Universal Parcel Scraper: correos-de-mexico](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/correos-de-mexico) – formato S10, regola link `Seguimiento.aspx?guia=`, ricerca multi-anno, mappa stati spagnoli, punto ritiro `Puesta en ventanilla`, gestione resi, fuso orario dell’ufficio e URL di aiuto
:::
