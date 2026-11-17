---
title: Tracking CTT Express: stati in italiano, punti e contatti
description: Traccia un pacco CTT Express in Spagna o Portogallo: codici a 22 e 25 cifre, stati spagnoli tradotti in italiano, punti Collectt, assenza e contatti.
slug: ctt-express-tracking
published: 2026-11-17
updated: 2026-11-17
---

CTT Express è la filiale spagnola di corrieri espressi del gruppo portoghese CTT (ex Tourline Express, ribattezzata nel 2019) e consegna in tutta la Spagna — penisola, Baleari, Canarie, Ceuta e Melilla — oltre che in Portogallo. Puoi inserire il codice nel [localizador](https://www.cttexpress.com/localizador-de-envios/) su `cttexpress.com` oppure incollarlo nel riquadro qui sopra. Il numero arriva dal negozio che ha spedito l'ordine e viene inviato anche da CTT Express via SMS o e-mail. Non confonderla con [CTT](carrier:ctt), le poste nazionali portoghesi: un codice con lettere che termina in `PT` appartiene a [CTT](carrier:ctt).

## Il numero di tracking CTT Express

La ricerca di CTT Express richiede il *código de envío* (codice di spedizione), di solito una lunga sequenza numerica indicata nell'SMS o nell'e-mail di conferma. Ecco i formati riconosciuti dal catalogo di Peek (esempi inventati):

| Formato (inventato) | A chi appartiene |
| --- | --- |
| `0012340012345678901234` | 22 cifre: `00`, quattro cifre, `00` e altre 14 cifre (i codici agenzia alle posizioni 1–2 e 7–8 iniziano entrambi con `00`). CTT Express. |
| `0012340012345678901234001` | 25 cifre: le stesse 22 cifre seguite dal contatore collo a tre cifre (`001` per il primo o unico collo secondo la specifica etichette di CTT Express). CTT Express. |
| `0000100012345678901234` | 22 cifre che iniziano con `00001000`: formato condiviso tra CTT Express, TIPSA, Austrian Post e altri corrieri. |
| `RR000000005PT` | Due lettere, nove cifre e `PT`: codice postale o CTT Expresso di [CTT](carrier:ctt), tracciabile su `ctt.pt`. |

**CTT, CTT Expresso o CTT Express?** Un unico gruppo, ma due reti distinte: [CTT](carrier:ctt) (*Correios de Portugal*) gestisce il servizio postale universale in Portogallo e la controllata espressa portoghese CTT Expresso con codici alfanumerici terminanti in `PT` (`DA`, `EA`, ecc.), mentre CTT Express gestisce la rete espressa spagnola (`CTT 24h`, `CTT 48h`, `CTT e24h`, `CTT 10h` e `CTT 14h`). Nel maggio 2026 CTT Expresso e [DHL](carrier:dhl) eCommerce hanno inoltre siglato una joint venture strategica nella penisola iberica (partecipazioni incrociate del 25 %, senza fusione): in Spagna CTT Express cura le consegne B2C con il marchio *CTT Express together with DHL* mentre [DHL](carrier:dhl) eCommerce segue il B2B e il traffico transfrontaliero; in Portogallo CTT Expresso integra le attività di DHL eCommerce. I due portali comunque si sovrappongono in parte: il selettore portoghese su `cttexpress.com` rimanda a «Seguir objeto» su `ctt.pt`, che legge anche i codici a 22 e 25 cifre, e il tracker di CTT Express mostra proprio un codice `PT` tra i suoi esempi. Se uno dei due siti non restituisce risultati, prova sull'altro.

Non hai ancora nessun codice? Chiedilo al venditore oppure leggi [dove trovare il numero di spedizione](guide:find-tracking-number).

## Stati di spedizione CTT Express tradotti in italiano

CTT Express non offre una pagina di tracciamento in italiano né in inglese: anche cambiando lingua su `cttexpress.com`, il portale di ricerca (`shipping-tracking.production.cloud2.cttexpress.com`) resta interamente in spagnolo. Mostra quattro fasi principali (`Grabado`, `Enviado`, `En reparto` ed `Entrega prevista` con una data o `Sin determinar`) e sotto ciascuna il testo originale delle scansioni.

| Stato in spagnolo | Cosa significa in pratica |
| --- | --- |
| `Pendiente de recepción en CTT Express` | Il mittente ha registrato la spedizione, ma il pacco fisico non è ancora entrato nella rete di CTT Express. |
| `Recogido` | Ritirato presso il mittente (`Recogida fallida` se il ritiro non è andato a buon fine). |
| `Enviado` o `En tránsito` | In viaggio tra filiali e hub di smistamento (`En clasificación` durante lo smistamento, `Aceptado por la última milla` all'arrivo nella filiale locale di consegna). |
| `En tránsito internacional. Pendiente de recepción en CTT Express` | In arrivo dall'estero; CTT Express non lo ha ancora preso in carico in Spagna o Portogallo. |
| `En reparto` o `Entrega hoy` | Affidato al corriere e in consegna oggi. |
| `Incidencia en el reparto` o `Reparto fallido` | Un imprevisto ha bloccato la consegna; la causale specifica compare nella riga sottostante. |
| `Recogerán en Delegación` | «Ritiro in filiale»: su un pacco consegnato in Portogallo da [CTT](carrier:ctt), il tentativo è fallito e il collo attende il ritiro. Il tracking di CTT segnala nello stesso istante la mancata consegna e subito dopo la disponibilità al punto di ritiro. |
| `Almacenado temporalmente` | In giacenza temporanea in filiale dopo un tentativo fallito, in attesa di nuova uscita o reso. |
| `Nuevo reparto` | Nuovamente in uscita per un ulteriore tentativo di consegna. |
| `Disponible en Punto Collectt Express` | Pronto per il ritiro presso un punto Collectt Express; il codice PIN arriva via e-mail e SMS. |
| `El envío fue entregado correctamente` | Consegnato. |
| `En devolución` | In fase di restituzione al mittente. |
| `Devuelto a remitente` | Restituito al mittente. |

`Pendiente de recepción en CTT Express` è la dicitura che preoccupa più spesso, ma indica soltanto che l'etichetta è stata creata. Per le spedizioni partite da Spagna o Portogallo, CTT Express precisa che se lo stato non cambia oltre **24 ore** dopo la richiesta di ritiro puoi segnalarlo tramite l'apposita voce del modulo contatti (*«Mi envío sigue en pendiente de entrada en red»*).

Sotto ogni anomalia compare il motivo, come `No pudimos contactar contigo` («non siamo riusciti a contattarti») o `Dirección incorrecta` («indirizzo errato»). Le FAQ di CTT Express avvertono che la causale di destinatario assente può comparire anche se eri in casa, quando l'autista non ha potuto raggiungere il portone o il citofono.

**Ordini dall'estero e da Paesi extra-UE:** un pacco acquistato fuori dalla penisola iberica deve prima essere accettato nel Paese d'origine e viaggiare fino in Spagna o Portogallo, quindi `En tránsito` può durare diversi giorni. Per gli e-commerce che spediscono da fuori dell'Unione europea (ad esempio dalla Cina), l'hub doganale di CTT Express smista oltre 300.000 pacchi al giorno 24 ore su 24 e sdogana in **12 ore in media** e consegna poi sulla propria rete in **24 o 48 ore**. Se la dogana trattiene il pacco, il tracking lo segnala oppure ricevi un avviso per documenti o oneri da saldare. Dal 1° luglio 2026 gli ordini extra-UE di valore fino a 150 € pagano nell'UE un dazio doganale fisso di **3 € per tipologia di prodotto**, e dal 1° novembre 2026 tutte queste spedizioni pagano inoltre **2 € di commissione europea di gestione doganale per tipologia di prodotto**, indipendentemente dal valore. Chi li riscuote e come sbloccare la spedizione: [pacchi fermi in dogana](guide:customs).

## Orari di consegna, punti Collectt e mancata consegna

In Spagna CTT Express consegna dal lunedì al venerdì, di norma tra le **9:00 e le 19:00** (gli orari variano in base al servizio e alla località); la consegna di sabato è attiva **solo nel periodo natalizio**. Il corriere non può avvisare in anticipo dell'orario di passaggio e telefona solo se non trova nessuno o se l'indirizzo risulta difficile da individuare. A consegna avvenuta, CTT Express invia un'e-mail di conferma.

Sono inclusi **due tentativi di consegna**, il secondo nel giorno lavorativo successivo (è previsto un terzo tentativo se invii una soluzione dopo il secondo passaggio a vuoto). Dopo un tentativo fallito ricevi un avviso con un link per scegliere una nuova data, un nuovo indirizzo, un vicino o un punto Collectt Express.

Puoi modificare la consegna anche autonomamente dal localizador inserendo il codice completo della spedizione, il CAP di destinazione originario e il numero di telefono o l'indirizzo e-mail registrati dal mittente. Puoi scegliere un altro indirizzo, un'altra data, fascia mattutina o pomeridiana, consegna a un vicino o punto Collectt. Le modifiche sono ammesse **solo prima dello stato `En reparto`** e possono aggiungere 24 ore ai tempi di consegna. Il cambio di indirizzo nella stessa zona di distribuzione e la deviazione verso un punto Collectt sono **gratuiti** (i punti Collectt accettano spedizioni monocollo fino a 20 kg, somma dei tre lati entro 160 cm e lato massimo 60 cm, senza contrassegno, verifica d'identità o ritiro contestuale di resi).

**Punti Collectt Express e locker Locky:** CTT Express conta oltre **20.000** punti convenzionati e armadietti automatici Locky tra Spagna e Portogallo. Un punto Collectt conserva il pacco per **10 giorni** (fino a 10 giorni lavorativi secondo la FAQ sugli orari), un locker Locky per **5 giorni**; il ritiro è gratuito con il PIN inviato via e-mail e/o SMS. Scaduto il termine, si applicano le istruzioni contrattuali del mittente. Se uno sportello Locky non si apre, chiama il numero indicato sull'armadietto o nell'e-mail di tracciamento.

**In Portogallo** valgono le regole di assistenza di [CTT](carrier:ctt): lì la rete Collectt comprende *Lojas CTT*, *Pontos CTT*, ricevitorie *Payshop* e armadietti *Cacifos Locky*. In caso di assenza il pacco può andare in uno di questi punti, uscire per un nuovo tentativo o tornare indietro secondo le disposizioni del mittente. CTT concede **10 giorni lavorativi** per ritirare un pacco Expresso proveniente dalla Spagna e **5 giorni** per un locker Locky.

## Pacco CTT Express in ritardo, smarrito o danneggiato

Per chiedere un rimborso o una nuova spedizione rivolgiti sempre al negozio da cui hai acquistato.

- **In ritardo:** CTT Express considera in ritardo una spedizione che supera la stima prevista; fine settimana, festività, maltempo e picco natalizio allungano i tempi. Leggi anche [tracking fermo che non si aggiorna](guide:tracking-not-updating).
- **Risulta consegnato ma non ricevuto:** controlla prima se qualcuno in casa, un vicino o la portineria lo ha ritirato (la scansione può riportare `El envío fue entregado en conserjería` oppure `El envío fue entregado a vecino según las instrucciones indicadas.`), poi usa l'opzione dedicata del modulo contatti (*«No tengo mi paquete y pone entregado»*). Altri consigli in [pacco segnato consegnato ma non ricevuto](guide:delivered-not-received).
- **Danneggiato:** fotografa il pacco prima di disimballarlo, conserva scatola ed etichette e invia la segnalazione tramite il modulo entro **7 giorni di calendario** dalla ricezione, specificando se il danno riguarda l'imballo esterno, il contenuto o entrambi (il mittente può aver attivato una copertura facoltativa contro furto o smarrimento fino a 2.000 €). CTT Express apre una pratica e ti comunica chi la gestisce.
- **Annullato:** se il tracciamento indica che la spedizione è stata annullata, contatta il venditore per farla riattivare.
- **In Portogallo:** le condizioni generali di CTT Expresso attribuiscono i reclami al cliente che ha commissionato il trasporto: **8 giorni** dalla consegna per danni e **30 giorni** per smarrimento o ritardo.

## Contatti, telefono e assistenza clienti CTT Express

Recapiti pubblicati sui siti ufficiali di CTT Express e di CTT (non è indicato un servizio in italiano né in inglese):

- **Spagna:** `900 841 352` (gratuito dalla Spagna sulle pagine di aiuto; l'articolo doganale del 27 ottobre 2026 riporta `900 110 898`) oppure `916 602 200` (`+34 916 602 200` dall'estero), dal lunedì al venerdì dalle **9:00 alle 19:00** esclusi i festivi. Per iscritto: `ccaweb@cttexpress.com` o il [modulo di contatto](https://www.cttexpress.com/contacto-atc) (codice di spedizione obbligatorio). L'assistenza è centralizzata a Madrid esclusivamente via telefono, e-mail o modulo — senza sportelli al pubblico, chat dal vivo né WhatsApp — e risponde di solito entro **24 ore lavorative**.
- **Portogallo:** linea [CTT](carrier:ctt) `21 047 16 16` (`+351 21 047 16 16` dall'estero), nei giorni lavorativi dalle **8:30 alle 19:30**, e modulo su `ctt.pt/ajuda`.

> CTT Express precisa di non chiedere mai di chiamare numerazioni a sovrapprezzo con prefisso `807` e mette in guardia da SMS ed e-mail truffaldini che domandano il pagamento di costi aggiuntivi (*gastos adicionales*) tramite link per sbloccare un pacco. In Portogallo anche CTT segnala falsi SMS «CTT Expresso» su oneri doganali; i link autentici di CTT iniziano sempre con `www.ctt.pt`. Traccia le spedizioni solo sui siti ufficiali.

## Tracciare i pacchi CTT Express con Peek

Incolla un codice nei primi due formati della tabella qui sopra e [Peek](/) riconosce subito CTT Express, inclusa la variante a 25 cifre terminante in `001` di un numero che inizia con `00001000` (al contrario, un codice a 22 cifre privo di `00` nelle posizioni 1–2 e 7–8 non viene riconosciuto da Peek come CTT Express). Per le 22 cifre che iniziano con `00001000`, Peek interroga contemporaneamente TIPSA, Austrian Post e CTT Express (e può verificare USPS se nessuno dei tre riconosce il collo); se non riesce a individuarne uno solo, puoi selezionarlo con «Cambia». Funziona direttamente anche un link copiato da `shipping-tracking.production.cloud2.cttexpress.com` con parametro `sc=`. Inserito da solo, un codice che termina in `PT` viene indirizzato a [CTT](carrier:ctt), mentre le spedizioni multicollo passano dai servizi universali di tracciamento.

Peek controlla CTT Express fino a ogni 10 minuti, e fino a ogni 2 minuti dalle 6:00 ora locale nel giorno in cui il pacco passa «In consegna» finché il tracker diretto di CTT Express risponde (ogni 15 minuti tramite i servizi universali). Dopo 48 ore senza novità — o 12 ore se il pacco resta su «Etichetta creata» o «Preso in carico» — il controllo passa a cadenza oraria, e puoi scegliere le notifiche «Tutti gli aggiornamenti», «Solo importanti» o «Solo giorno di consegna». Peek mostra lo stato attuale, la cronologia delle scansioni (senza località sulla mappa, poiché il flusso pubblico di CTT Express non ne fornisce), la data di consegna prevista mentre il pacco è in viaggio senza blocchi, il peso, l'ora di recapito e, quando un'altra posta ha affidato il collo, il numero postale internazionale. Se quel codice termina in `PT`, in Portogallo consegna [CTT](carrier:ctt): la scheda mostra «Consegna con CTT Portugal» insieme al codice (oppure «Annunciato a CTT Portugal, non ancora ricevuto» finché CTT registra il pacco senza una propria scansione). Non appena il tracker di CTT pubblica una scansione almeno altrettanto recente dell'ultima di CTT Express, Peek segue la spedizione su CTT al ritmo del corriere che consegna mantenendo la cronologia di CTT Express. I testi delle scansioni restano nello spagnolo originale (`En reparto`, oppure `Disponible en Punto Collectt Express` senza nome né indirizzo del punto), i resi da `En devolución` in poi vengono segnalati come anomalia fino a `Devuelto a remitente`, e su `peektracker.com` come nell'app per iPhone la cronologia termina con «Orari nel tuo fuso orario: …».

## Domande frequenti sul tracking CTT Express

### Che cos'è CTT Express?

È la controllata spagnola di corrieri espressi del gruppo portoghese CTT, nata nel 2019 dal cambio di nome di Tourline Express. Non va confusa con [CTT](carrier:ctt), le poste del Portogallo, anche se entrambe fanno parte dello stesso gruppo e nel maggio 2026 CTT Expresso ha avviato una joint venture iberica con [DHL](carrier:dhl) eCommerce. Consegna in tutta la Spagna (penisola, Baleari, Canarie, Ceuta e Melilla) e in Portogallo.

### CTT Express consegna il sabato?

In Spagna solo durante il periodo natalizio. Nel resto dell'anno le consegne avvengono dal lunedì al venerdì, generalmente tra le 9:00 e le 19:00.

### Posso tracciare CTT Express in italiano o in inglese?

No. La pagina di tracciamento ufficiale è disponibile unicamente in spagnolo; la tabella qui sopra traduce in italiano tutti gli stati e le causali più frequenti.

### CTT Express ha una chat dal vivo o WhatsApp?

Sul sito ufficiale non compaiono né live chat né WhatsApp: i canali di assistenza sono il telefono (`900 841 352` in Spagna o `+34 916 602 200` dall'estero), l'e-mail (`ccaweb@cttexpress.com`) e il modulo di contatto.

### Si può tracciare un pacco CTT Express senza numero di spedizione?

No. Il localizador ha un solo campo dedicato al codice di spedizione e anche il modulo di assistenza lo richiede obbligatoriamente. Cercalo nell'SMS o nell'e-mail di conferma, oppure chiedilo al negozio che ha spedito la merce.

:::sources
- [CTT Express: Localizador de envíos](https://www.cttexpress.com/localizador-de-envios/) – ricerca spedizioni, modifica della consegna ed e-mail di recapito
- [CTT Express: Localizador de envios (portoghese)](https://www.cttexpress.com/localizador-de-envios/?language_code=pt) – rimando a «Seguir objeto» su `ctt.pt`
- [CTT Express: Seguimiento de envíos](https://shipping-tracking.production.cloud2.cttexpress.com/) – tracking solo in spagnolo, fasi, causali di anomalia, singolo campo di ricerca ed esempio `PT`
- [CTT Express: Empresa de Paquetería y Mensajería](https://www.cttexpress.com/) – copertura iberica, punti Collectt e selettore lingua
- [CTT Express: Quiénes somos](https://www.cttexpress.com/nosotros/quienes-somos) – gruppo CTT e passaggio da Tourline Express nel 2019
- [CTT Express: CTT Express y DHL unen fuerzas](https://www.cttexpress.com/nosotros/ctt-express-y-dhl-unen-fuerzas) – alleanza strategica iberica con DHL eCommerce
- [CTT Express: Comunicato stampa CTT Expresso e DHL eCommerce](https://www.cttexpress.com/zona-de-prensa/notas-de-prensa/ctt-expresso-y-dhl-ecommerce-sellan-un-hito-en-su-alianza-para-construir-la-red-de-paqueteria-mas-completa-de-la-peninsula-iberica) – joint venture di maggio 2026 e quote incrociate del 25 %
- [CTT Express: Envíos urgentes 24 horas](https://www.cttexpress.com/envios/24-h) – servizi espressi `CTT 24h`, `CTT e24h`, `CTT 14h` e `CTT 10h`
- [CTT Express: Envíos 48 horas](https://www.cttexpress.com/envios/48-h) – servizio `CTT 48h` per Baleari, Canarie, Ceuta, Melilla, Madera e Azzorre
- [CTT Express: Shipment Number Calculation and Package Codes (PDF)](https://transfer.cttexpress.com/IT/export/integra/Shipment_Number_Calculation_and_Package_Codes_of_CttExpress.pdf) – struttura dei codici a 22 e 25 cifre e contatore collo `001`
- [CTT Express: FAQs sobre los estados de envío](https://www.cttexpress.com/preguntas-frecuentes/estados-envio) – stati, attesa oltre 24 ore, pacco segnato consegnato e reclamo danni entro 7 giorni di calendario
- [CTT Express: Preguntas frecuentes de incidencias](https://www.cttexpress.com/preguntas-frecuentes/incidencias) – due tentativi, ritardi e reclami
- [CTT Express: Horarios de reparto](https://www.cttexpress.com/preguntas-frecuentes/horarios) – orari 9:00–19:00, sabati natalizi e chiamata dell'autista
- [CTT Express: Cambiar dirección de entrega](https://www.cttexpress.com/cambiar-direccion-entrega/) – regole di modifica gratuita prima di `En reparto`
- [CTT Express: Servicios incluidos y adicionales](https://www.cttexpress.com/servicios/incluidos-y-adicionales) – tentativi inclusi, limiti Collectt (20 kg, 160 cm) e copertura facoltativa fino a 2.000 €
- [CTT Express: Recoge y devuelve tus paquetes en nuestros puntos](https://www.cttexpress.com/red-collectt-express/envio-y-recogida) – oltre 20.000 punti Collectt (10 giorni) e locker Locky (5 giorni)
- [CTT Express: Preguntas frecuentes Puntos Collectt Express](https://www.cttexpress.com/preguntas-frecuentes/puntos-collectt-express) – ritiro con PIN e cambio gratuito verso punto Collectt
- [CTT Express: Qué significa «aceptado por la última milla»](https://www.cttexpress.com/blog/logistica/que-significa-aceptado-ultima-milla) – significato degli stati operativi
- [CTT Express: Nota stampa sulle spedizioni](https://www.cttexpress.com/zona-de-prensa/notas-de-prensa/como-localizo-mi-paquete-con-ctt-express-y-otras-preguntas-frecuentes-sobre-envios) – tempi di transito dall'estero
- [CTT Express: Servicio de importación de paquetería](https://www.cttexpress.com/servicios/importacion/) – sdoganamento extra-UE in 12 ore in media (oltre 300.000 pacchi/giorno) e consegna in 24 o 48 ore
- [CTT Express: ¿Qué hacer si mi paquete ha quedado retenido en aduanas?](https://www.cttexpress.com/blog/expansion-internacional/que-hacer-si-mi-paquete-ha-quedado-retenido-en-aduanas) – fermi doganali, Canarie e numero `900 110 898`
- [Commissione europea: Riforma doganale dell'UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_it) – dazio di 3 € per tipologia di prodotto fino a 150 € dal 1° luglio 2026 e commissione di gestione di 2 € dal 1° novembre 2026
- [Commissione europea: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – dazio di 3 € e commissione di 2 € calcolati per tipologia di prodotto a prescindere dal valore
- [CTT Express: Cómo contactar con CTT Express](https://www.cttexpress.com/preguntas-frecuentes/como-contactar-con-ctt) – numeri, orari, e-mail, risposta entro 24 ore lavorative e avviso sui numeri `807`
- [CTT Express: Contacto y Atención al Cliente](https://www.cttexpress.com/contacto-atc) – modulo contatti con obbligo del codice di spedizione
- [CTT Express: Qué es Phishing](https://www.cttexpress.com/blog/actualidad/como-evitar-phishing) – falsi SMS ed e-mail con richiesta di costi aggiuntivi
- [CTT: Encontrar o código de envio](https://www.ctt.pt/ajuda/empresas/seguir-ou-alterar-entrega/seguir/encontrar-o-codigo-de-envio) – codici alfanumerici `PT` e codici a 22 e 25 cifre
- [CTT: Condições Gerais do Serviço Postal Universal](https://www.ctt.pt/home/servico-postal-universal/condicoes-gerais-de-acesso-e-utilizacao-do-servico-universal) – CTT gestore del servizio postale universale in Portogallo
- [CTT: Empresas do grupo](https://www.ctt.pt/grupo-ctt/a-empresa/quem-somos/empresas-do-grupo/) – CTT Express e CTT Expresso nel gruppo CTT
- [CTT: Prazo para levantar](https://www.ctt.pt/ajuda/particulares/receber/levantar-correspondencia-ou-encomendas-/prazo-para-levantar) – termini di ritiro in Portogallo (10 giorni lavorativi dalla Spagna, 5 giorni per Locky)
- [CTT: Collectt](https://www.ctt.pt/particulares/receber/locais-de-entrega-alternativos/collectt) – rete Collectt in Portogallo
- [CTT: Entrega não conseguida](https://www.ctt.pt/ajuda/particulares/receber/problemas-na-entrega/entrega-nao-conseguida) – mancata consegna in Portogallo
- [CTT: Condições gerais de transporte de Encomendas Expresso](https://www.ctt.pt/transversais/condicoes-gerais-transporte-encomendas-expresso) – termini di reclamo di 8 e 30 giorni
- [CTT: Linhas de Apoio](https://www.ctt.pt/ajuda/particulares/apoio-ao-cliente/contactos/linhas-de-apoio) – telefono e orari di assistenza in Portogallo
- [CTT: Alertas de Phishing](https://www.ctt.pt/transversais/alertas-de-phishing) – falsi SMS a nome CTT Expresso
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – riconoscimento dei codici, stato `Recogerán en Delegación` e passaggio a CTT Portugal su Peek
:::
