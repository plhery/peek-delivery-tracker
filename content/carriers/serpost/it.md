---
title: Tracciamento Serpost: codici S10 Perù, dogana e aiuto
description: Traccia un pacco Serpost (Servicios Postales del Perú): codici S10 PE da 13 caratteri, stati dogana SUNAT, ritiro in ufficio, reclami e call center Lima.
slug: tracciamento-serpost
published: 2026-11-24
updated: 2026-11-24
---

**Serpost** (*Servicios Postales del Perú S.A.*) è l’operatore postale nazionale statale del Perù, dipendente dal Ministero dei Trasporti e delle Comunicazioni (MTC) ([gob.pe/serpost](https://www.gob.pe/serpost)). Oltre alla posta e ai pacchi nazionali in Perù, gestisce i servizi internazionali in uscita (tra cui [EMS](carrier:ems) ed *Exporta Fácil*) e la posta in ingresso dell’Unione Postale Universale (UPU) dall’estero, con tracciamento online su [serpost.com.pe/Cliente/SegumientoLinea](https://www.serpost.com.pe/Cliente/SegumientoLinea). Puoi controllare lì una spedizione S10 peruviana o internazionale in arrivo, oppure incollare il codice di 13 caratteri nel riquadro qui sopra.

## Codici di tracciamento Serpost (formato UPU S10 da 13 caratteri)

Serpost traccia codici a barre standard UPU S10 da 13 caratteri: due lettere di servizio, 8 cifre seriali, 1 cifra di controllo S10 e il codice paese di origine a 2 lettere (`PE` per spedizioni dal Perù, oppure il codice estero come `US`, `CN`, `ES` o `JP` per pacchi internazionali in arrivo in Perù):

| Formato (inventato) | Che cos’è |
| --- | --- |
| `RR482917367PE` | Raccomandata da 13 caratteri spedita dal Perù (`RR` + 9 cifre con cifra di controllo S10 valida + `PE`). |
| `EE739184627PE` | Pacco espresso internazionale [EMS](carrier:ems) da 13 caratteri spedito dal Perù (`EE` + 9 cifre con cifra di controllo S10 valida + `PE`). |

Le lettere economiche non tracciate prive di codice a barre S10 da 13 caratteri non compaiono nel tracciamento online di Serpost. Per pacchi internazionali in arrivo dall’estero (ad esempio terminanti con `CN`, `US` o `GB`), una volta giunto l’invio in Perù, selezionando Serpost vedrai le scansioni locali della dogana peruviana (*Aduana*) e dell’ufficio postale sotto lo stesso codice S10 da 13 caratteri. Consulta la guida ai [formati dei codici di tracciamento](guide:tracking-number-formats).

## Significato degli stati di tracciamento Serpost

Serpost pubblica descrizioni in spagnolo che indicano la specifica amministrazione postale peruviana (*Administración Postal*) in cui è avvenuta ogni scansione:

| Serpost mostra | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `ENVÍO ADMITIDO EN ...` | Accettato allo sportello dell’ufficio postale Serpost peruviano indicato. | «Preso in carico» |
| `ENVÍO LLEGÓ A SERPOST`, `ENVÍO EN PROCESAMIENTO`, `ENVÍO EN TRÁNSITO` | Pacco internazionale in arrivo ricevuto da Serpost in Perù, in smistamento alla sede centrale o in transito nazionale. | «In transito» |
| `ENVÍO SUJETO A TRÁMITE ANTE ADUANA` | Selezionato dalla dogana peruviana (SUNAT) per sdoganamento, valutazione o verifica imposte di importazione. | «In dogana» |
| `ENVÍO SIN OBSERVACIÓN POR LA ADUANA` | Sdoganato senza osservazioni dalla dogana peruviana e riaffidato a Serpost. | «In transito» |
| `ENVÍO SALIÓ DE PERÚ`, `ENVÍO RUMBO AL PAÍS DE DESTINO` | Spedizione internazionale in uscita partita dal Perù verso l’operatore postale del paese di destinazione. | «In transito» |
| `ENVÍO LLEGÓ A LA ADMINISTRACIÓN POSTAL ...` | Arrivato all’ufficio postale locale peruviano che prepara il giro di consegna. | «In transito» |
| `ENVÍO EN PROCESO DE DISTRIBUCIÓN`, `SALIÓ A DISTRIBUCIÓN` | Affidato al portalettere Serpost per la consegna a domicilio. | «In consegna» |
| `ENVÍO DISPONIBLE PARA ENTREGA O RECOJO` | Pronto per il ritiro presso l’ufficio Serpost assegnato. | «Pronto per il ritiro» |
| `INTENTO FALLIDO DE ENTREGA`, `SE INTENTÓ ENTREGAR PERO FUE IMPOSIBLE` | Consegna non riuscita (destinatario assente o problema di accesso); controlla eventuali avvisi di ritiro in ufficio. | «Tentativo di consegna» |
| `ENTREGADO`, `ENVÍO ENTREGADO`, `FUE ENTREGADO` | Consegnato al destinatario o ritirato all’ufficio postale. | «Consegnato» |

## Tempi di consegna, dogana e termini ufficiali di reclamo

Secondo [Servizi di spedizione Serpost](https://www.gob.pe/institucion/serpost/pages/20800-enviar-cartas-dentro-y-fuera-del-pais-con-serpost) e [Reclami e richieste Serpost](https://www.gob.pe/institucion/serpost/pages/33693-reclamos-y-consultas-servicios-postales-del-peru):

- **Tempi pubblicati per le lettere:** **Expreso** nazionale **48–72 ore max**; **Estándar** nazionale **5–7 giorni lavorativi**; **Económico** nazionale **7–10 giorni lavorativi**; **Expreso** internazionale **7–10 giorni lavorativi**; **Económico** internazionale **10–30 giorni lavorativi**. Solo le lettere Expreso ed Estándar sono tracciabili online.
- **Dogana peruviana (`ENVÍO SUJETO A TRÁMITE ANTE ADUANA`):** l’invio è trattenuto per formalità doganali e non è consegnabile finché la dogana non lo rilascia (`ENVÍO SIN OBSERVACIÓN POR LA ADUANA`). Il tracker Serpost può mostrare un avviso di arrivo (*Aviso de llegada*). Vedi la guida ai [pacchi fermi in dogana](guide:customs).
- **Termini e tempi ufficiali di reclamo:** i reclami per invii **EMS** possono essere presentati dal giorno successivo alla scadenza del termine di consegna fino a **4 mesi** dalla spedizione (risposta entro **30 giorni solari**); i reclami per raccomandate internazionali sono accettati dopo **20 giorni solari** e quelli per pacchi internazionali dopo **30 giorni solari**; i reclami nazionali ricevono risposta entro **16 giorni lavorativi** (indennizzo nazionale pari a **3× l’affrancatura pagata**; indennizzo internazionale secondo le norme UPU).

## Pacco Serpost in ritardo, in dogana o smarrito

- **Pause silenziose nella posta internazionale:** i pacchi in uscita fermi dopo `ENVÍO SALIÓ DE PERÚ` o quelli in ingresso dall’estero possono attendere diversi giorni o settimane tra la spedizione aerea e l’ingresso in dogana. Quando un invio `...PE` lascia il Perù, controlla le poste nazionali del paese di destinazione (come [USPS](carrier:usps)). Vedi la guida sul [tracciamento fermo](guide:tracking-not-updating).
- **Presentare un reclamo Serpost (*reclamo*):** invia il modulo via email a `servicioalcliente@serpost.com.pe`, telefona al `(01) 511-5110` oppure recati nell’ufficio Serpost più vicino — riceverai via email un numero di pratica (*número de expediente*) per seguirne lo stato. Vedi anche [pacco smarrito](guide:lost-parcel), [pacco danneggiato](guide:damaged-parcel), [consegnato ma non ricevuto](guide:delivered-not-received) e [restituito al mittente](guide:returned-to-sender).

## Assistenza clienti Serpost e contatti a Lima

Canali ufficiali pubblicati su [Serpost Contacto (gob.pe)](https://www.gob.pe/institucion/serpost/contacto-y-numeros-de-emergencias):

- **Call Center (Perù):** `(01) 511-5110`.
- **Centralino principale:** `511-5000` (linee sede centrale `(511) 533-2001` / `533-2002` / `533-2003` / `533-2004`).
- **Email assistenza clienti e reclami:** `servicioalcliente@serpost.com.pe` (webmaster: `webmaster@serpost.com.pe`).
- **Sede centrale (*Sede Central*):** Av. Tomás Valle cdra. 7 s/n, Los Olivos, Lima 15302, Perù.
- **Tracker online e portale governativo:** [serpost.com.pe/Cliente/SegumientoLinea](https://www.serpost.com.pe/Cliente/SegumientoLinea) e [gob.pe/serpost](https://www.gob.pe/serpost).

> Diffida di SMS fasulli secondo cui un pacco Serpost sarebbe bloccato fino al pagamento di una piccola tariffa tramite un link sconosciuto. Verifica sempre eventuali avvisi doganali direttamente su [serpost.com.pe/Cliente/SegumientoLinea](https://www.serpost.com.pe/Cliente/SegumientoLinea) o sui portali ufficiali `gob.pe` / SUNAT. Vedi [SMS truffa sulle consegne](guide:delivery-scam-texts).

## Tracciare un pacco Serpost con Peek

Quando inserisci un codice S10 peruviano da 13 caratteri (`RR482917367PE` o `EE739184627PE`, inventati) in [Peek](/), Peek verifica la cifra di controllo S10 e interroga prima Serpost, poi Chronopost. Per un codice S10 estero in arrivo in Perù, o per passare manualmente a Serpost, tocca «Cambia» mentre aggiungi il pacco oppure «Cambia corriere» nella schermata del pacco e seleziona **Serpost** (anche cercando «SERPOST», «Peru Post» o «Servicios Postales del Perú»). Peek mostra «Serpost ha questo pacco.» non appena Serpost restituisce scansioni.

Peek legge il tracker Serpost e mostra la fase («Preso in carico», «In transito», «In dogana», «In consegna», «Pronto per il ritiro», «Tentativo di consegna», «Consegnato»), la dicitura spagnola di Serpost senza l’invito WhatsApp dell’ufficio, il nome dell’ufficio e l’orario di consegna. Quando Serpost collega la mappa di un ufficio in Perù, Peek posiziona lì la scansione sulla mappa del pacco; quando l’invio risulta in consegna, pronto per il ritiro o consegnato, Peek mostra il Perù come destinazione. Serpost indica gli orari sull’ora di Lima: Peek li data sull’ora di Lima e li mostra con l’ora del tuo dispositivo. Il tracker Serpost non fornisce data stimata di consegna, peso né nome del firmatario.

Mentre tieni il pacco aperto in Peek, o il giorno della consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti; nel giorno in cui Serpost mostra `ENVÍO EN PROCESO DE DISTRIBUCIÓN`, ogni 2 minuti dalle 6:00 ora di Lima finché il tracker Serpost risponde. Altrimenti Peek controlla ogni ora. Dopo 12 ore con solo «Etichetta creata» o «Preso in carico», o dopo due giorni senza novità, controlla ogni ora comunque tu lo segua, e ogni 6 ore dopo sette giorni su «Etichetta creata». Quando il tracker di Serpost non risponde e Peek segue il pacco solo tramite servizi di tracciamento, controlla ogni ora, ogni 3 ore dopo una giornata ferma e fino a ogni 15 minuti nel giorno in cui il pacco è in consegna, mentre hai il pacco aperto o le notifiche attive.

Quando la scansione più recente risale a quattro giorni prima e il pacco è ancora su «Etichetta creata», «Preso in carico» o «In transito», Peek lo segnala con il giorno di quella scansione, ad esempio «Nessun aggiornamento da lun 12 ott». La pagina del pacco mostra quindi «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a Serpost.», con il link «Chiedi aiuto a Serpost» alla pagina contatti Serpost su gob.pe. Dopo tre settimane senza nuove scansioni mostra «Niente di nuovo da più di tre settimane. Se il pacco non è ancora arrivato, contatta il venditore. Peek continua a chiedere a Serpost.»

Puoi scegliere gli avvisi «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek monitora solo gli aggiornamenti di tracciamento e non può sdoganare presso la SUNAT, presentare reclami né contattare Serpost al posto tuo.

## Domande sul tracciamento Serpost

### Che aspetto ha un codice di tracciamento Serpost?

Serpost traccia codici UPU S10 da 13 caratteri — due lettere, 9 cifre (terminate da una cifra di controllo) e un codice paese di origine a 2 lettere come `PE` (ad esempio `RR482917367PE` o `EE739184627PE`, inventati), oltre ai codici S10 esteri in arrivo in Perù.

### Cosa significa `ENVÍO SUJETO A TRÁMITE ANTE ADUANA`?

Significa che la dogana peruviana (SUNAT) ha selezionato il pacco internazionale per lo sdoganamento o il controllo dei dazi di importazione prima che Serpost possa liberarlo per la consegna locale o il ritiro in ufficio.

### Quanto tempo impiega Serpost per consegnare?

Serpost pubblica su [gob.pe](https://www.gob.pe/institucion/serpost/pages/20800-enviar-cartas-dentro-y-fuera-del-pais-con-serpost) i tempi per le lettere: **48–72 ore** per Expreso nazionale, **5–7 giorni lavorativi** per Estándar nazionale, **7–10 giorni lavorativi** per Económico nazionale o Expreso internazionale, e **10–30 giorni lavorativi** per Económico internazionale.

### Come contatto l’assistenza clienti Serpost o presento un reclamo?

Chiama il Call Center Serpost al `(01) 511-5110`, scrivi a `servicioalcliente@serpost.com.pe` o recati in un ufficio Serpost; tutti i contatti ufficiali sono elencati su [gob.pe/institucion/serpost/contacto-y-numeros-de-emergencias](https://www.gob.pe/institucion/serpost/contacto-y-numeros-de-emergencias).

:::sources
- [Serpost: Seguimiento en Línea](https://www.serpost.com.pe/Cliente/SegumientoLinea) – portale ufficiale di tracciamento online e Call Center `(01) 511-5110`
- [Serpost: Portal Institucional (gob.pe)](https://www.gob.pe/serpost) – portale istituzionale ufficiale *Servicios Postales del Perú S.A.* (MTC)
- [Serpost: Contacto y Números de Atención](https://www.gob.pe/institucion/serpost/contacto-y-numeros-de-emergencias) – Call Center `511-5110`, centralino `511-5000`, `servicioalcliente@serpost.com.pe` e sede di Los Olivos (link di aiuto in Peek)
- [Serpost: Reclamos y Consultas](https://www.gob.pe/institucion/serpost/pages/33693-reclamos-y-consultas-servicios-postales-del-peru) – termini reclamo (20 gg raccomandate, 30 gg pacchi, 4 mesi EMS), tempi di risposta (16 gg nazionali / 30 gg EMS) e regole indennizzo
- [Serpost: Envíos Nacionales e Internacionales](https://www.gob.pe/institucion/serpost/pages/20800-enviar-cartas-dentro-y-fuera-del-pais-con-serpost) – tempi di consegna pubblicati per lettere, per servizio
- [Universal Parcel Scraper: serpost](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/serpost) – verifica cifra di controllo UPU S10, ricerca `SegumientoLinea`, regole stati spagnoli, nome ufficio & coordinate Perù, orari `America/Lima`
:::
