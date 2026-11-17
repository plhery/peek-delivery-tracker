---
title: Tracciamento SEUR: numero, stati spagnoli e punti Pickup
description: Segui un pacco SEUR dalla Spagna verso l’Italia o la Svizzera: formato del codice, significato degli stati in spagnolo, punti Pickup, dogana e assistenza.
slug: tracciamento-seur
published: 2026-11-17
updated: 2026-11-17
---

SEUR è un corriere espresso spagnolo del gruppo Geopost, lo stesso di [BRT](carrier:brt) in Italia e di [DPD Svizzera](carrier:dpd-ch). Puoi seguire la spedizione senza account nell’area miSEUR su seur.com, tramite link SEUR o incollando il codice qui sopra.

Per gli ordini dalla Spagna verso l’Italia o la Svizzera italiana, SEUR gestisce la partenza in Spagna (con stati in spagnolo) e affida la consegna nella rete Geopost a [BRT](carrier:brt) o a [DPD Svizzera](carrier:dpd-ch).

## Numero di tracciamento SEUR: formato e dove trovarlo

I codici SEUR hanno per lo più 14 o 21 cifre, ma funzionano cinque formati (tutti gli esempi sono inventati):

| Esempio (inventato) | Che cos’è |
| --- | --- |
| `48291057364928` | 14 cifre, formato condiviso nella rete Geopost |
| `482910573649281736492` | 21 cifre |
| `4829105` | 7 cifre (codice breve riutilizzato da SEUR) |
| `2026150848291057364928` | 22 cifre con data iniziale (anno, giorno, mese): riferimento mittente |
| `REC482910573649` | `REC` più 12 cifre: riferimento di ritiro |

Cercalo nell’e-mail di spedizione del negozio o nell’ordine; se manca, vedi [come trovare il numero di tracciamento](guide:find-tracking-number). Poiché 14 cifre valgono tra gli altri anche per [BRT](carrier:brt), [DPD Svizzera](carrier:dpd-ch), [DPD Germania](carrier:dpd-de), [Chronopost](carrier:chronopost) e Hermes (e 22 per USPS, Austrian Post o Estafeta), verifica il corriere indicato dal venditore.

## Stati del tracciamento SEUR: cosa significano

Il portale SEUR è in spagnolo e inglese, ma gli eventi arrivano in maiuscolo spagnolo:

| Stato SEUR | Significato per il tuo pacco |
| --- | --- |
| `EL ENVÍO HA SIDO REGISTRADO.` | Spedizione creata dal mittente; SEUR non ha ancora il pacco. |
| `LA RECOGIDA HA SIDO REGISTRADA.` | Ritiro prenotato presso il mittente; SEUR non ha ancora il collo. |
| `LA RECOGIDA HA SIDO CONFIRMADA POR EL REPATIDOR.` | Ritiro inserito nel giro dell’autista (*REPATIDOR* è la grafia di SEUR). |
| `RECOGIDA NO REALIZADA: CLIENTE CERRADO O AUSENTE. SE PUEDE REPROGRAMAR UNA NUEVA FECHA DE RECOGIDA.` | Ritiro fallito presso il mittente (chiuso o assente), non a casa tua: Peek mantiene «Etichetta creata». |
| `LA RECOGIDA HA SIDO REALIZADA.` | Ritirato dal mittente: ora lo ha SEUR. |
| `EL ENVÍO HA SIDO DEPOSITADO EN UN PUNTO SEUR PICKUP.` | Consegnato dal mittente in un punto SEUR Pickup. |
| `EL ENVÍO HA SIDO RECEPCIONADO EN LAS INSTALACIONES DE ORIGEN DE SEUR. COMIENZA EL TRÁNSITO A DESTINO` | Entrato nella filiale di partenza SEUR; inizia il transito. |
| `EL ENVÍO ESTÁ EN TRÁNSITO. PRÓXIMAMENTE LLEGARÁ A LAS INSTALACIONES DE DESTINO.` | In viaggio verso la filiale della tua zona. |
| `EN ENVÍO HA SIDO RECEPCIONADO EN LAS INSTALACIONES DE DESTINO. PRONTO SALDRÁ A REPARTO.` | Arrivato nella filiale locale (*EN ENVÍO* è la grafia di SEUR), non ancora sul furgone: Peek mostra «In transito». |
| `EL ENVÍO HA LLEGADO A LA ADUANA DE DESTINO. INICIAMOS LOS TRÁMITES DE DESPACHO ADUANERO.` | Arrivato alla dogana del Paese di destinazione. |
| `INCIDENCIA EN ADUANAS: FALTA FACTURA. CONTACTA CON SEUR DESDE EL ÁREA DE SOPORTE.` | Manca la fattura in dogana: chiedila al negozio e contatta SEUR da miSEUR. |
| `LA MERCANCÍA HA SIDO DESPACHADA Y PRÓXIMAMENTE SALDRÁ A REPARTO.` | Sdoganato; prossimo all’uscita in consegna. |
| `EL ENVÍO SERÁ ENTREGADO EN LA FECHA CONCERTADA. ACTUALIZAREMOS LA SITUACIÓN.` | Data di consegna concordata registrata. |
| `EL ENVÍO HA SUFRIDO UN RETRASO Y ES POSIBLE QUE SE DEMORE LA ENTREGA. DISCULPA LAS MOLESTIAS.` | Ritardo segnalato, senza motivo né nuova data. |
| `EL ENVÍO ESTÁ EN REPARTO.` | In consegna oggi (`INTERNACIONAL` per spedizioni estere). |
| `EL ENVÍO NO SE HA ENTREGADO POR AUSENCIA O CIERRE. SE PUEDE REPROGRAMAR UNA NUEVA ENTREGA.` | Assente o chiuso; puoi riprogrammare la consegna. |
| `EL ENVÍO NO SE HA ENTREGADO PORQUE LA DIRECCIÓN DE ENTREGA ES INCORRECTA.` | Indirizzo errato (o `INCOMPLETA O ERRÓNEA`): Peek mostra «Tentativo di consegna». |
| `EL ENVÍO NO SE HA ENTREGADO PORQUE NO ES LA DIRECCIÓN ACTUAL DEL DESTINATARIO.` | Non è più l’indirizzo attuale del destinatario: Peek indica «Problema segnalato». |
| `NO SE HA PODIDO DEPOSITAR EL ENVÍO EN EL PUNTO SOLICITADO` | Il punto Pickup richiesto non ha potuto accogliere il collo. |
| `EL ENVÍO ESTÁ DISPONIBLE PARA RECOGER EN EL PUNTO SEUR PICKUP.` | Pronto per il ritiro nel punto SEUR Pickup. |
| `EL ENVÍO ESTÁ ENTREGADO.` | Consegnato (`ENTREGADO A UN VECINO.` se lasciato a un vicino). |

Dopo il ritiro in un negozio Pickup spagnolo appare `EL DESTINATARIO HA RETIRADO EL ENVÍO DE LA TIENDA SEUR PICKUP SELECCIONADA.`

## Consegna in Italia, Svizzera e Spagna

**In Spagna.** SEUR conta migliaia di negozi Pickup e locker su seur.com. Se la consegna passa a un punto Pickup compare `LOS DATOS DEL ENVÍO HAN SIDO MODIFICADOS Y SE ENTREGARÁ EN UN PUNTO SEUR PICKUP.` ; se un locker non può riceverlo, `EL ENVÍO NO HA SIDO DEPOSITADO EN EL LOCKER SELECCIONADO. SE DEBE ELEGIR OTRO PUNTO SEUR PICKUP.` chiede di sceglierne un altro.

**Verso l’Italia e la Svizzera.** Con SEUR Classic Packlink stima 2–4 giorni verso le principali città europee, senza fascia garantita salvo espressi a orario:

- **Italia ([BRT](carrier:brt)):** oltre 11.600 punti di ritiro [BRT-fermopoint](https://www.brt.it/en/brt-fermopoint/) con avvisi SMS/e-mail e 10 giorni di custodia.
- **Svizzera ([DPD Svizzera](carrier:dpd-ch)):** ritiro nei [DPD Pickup Location](https://www.dpd.com/ch/it/dpd-pickup-location/) con documento d’identità ufficiale valido con foto (o procura e documento del delegato).

## Dogana: Svizzera, Canarie, Ceuta e Melilla

Nessuna dogana tra Spagna peninsulare (o Baleari) e Italia (esclusi Livigno e Campione d’Italia). Fanno eccezione due casi:

- **Svizzera:** fuori dall’unione doganale UE; secondo l’UFDSC possono applicarsi IVA all’importazione, dazi e spese di sdoganamento del corriere (a cui l’UFDSC rimanda per costi e stato).
- **Canarie, Ceuta, Melilla o Paesi extra-UE:** le Canarie sono fuori dall’area IVA UE, mentre Ceuta e Melilla sono fuori sia dal territorio doganale sia dall’area IVA UE. Nell’UE dal 1° luglio 2026 si applica un dazio temporaneo di 3 € per articolo sugli invii extra-UE fino a 150 €, più dal 1° novembre 2026 la commissione dell’Unione di 2 € per articolo distinto (riaddebitabile). Vedi [pacco fermo in dogana](guide:customs).

## Pacco non trovato, in ritardo o segnato consegnato

**Non trovato.** SEUR dà lo stesso errore per una spedizione appena creata e per un codice sconosciuto: attendi un giorno lavorativo e chiedi conferma al venditore.

**In ritardo o non ricevuto.** Oltre la data prevista consulta [tracciamento che non si aggiorna](guide:tracking-not-updating). Per gli acquisti online nell’UE Your Europe ricorda che il venditore risponde della merce fino alla consegna. Se leggi `ENTREGADO A UN VECINO.` chiedi ai vicini, altrimenti vedi [pacco consegnato ma non ricevuto](guide:delivered-not-received).

## Assistenza SEUR e contatti locali

Per SEUR usa il supporto di miSEUR su seur.com. Per la rete Geopost locale:

- **Italia ([BRT](carrier:brt)):** supporto su [services.brt.it](https://services.brt.it/en/support) e [FAQ BRT](https://www.brt.it/en/customer-service/faq/).
- **Svizzera ([DPD Svizzera](carrier:dpd-ch)):** `058 311 13 13` (lun.–ven. 8:00–18:00, max. 0,08 CHF/min) o [pagina di supporto](https://www.dpd.com/ch/it/centro-supporto/la-richiesta/).

> Nel febbraio 2021 l’INCIBE ha segnalato SMS truffa a nome SEUR con link a un sito falso che chiedeva un piccolo pagamento per rubare i dati della carta: non seguire il link, digita l’indirizzo di SEUR nel browser e contatta subito la banca se hai inserito la carta.

## Seguire un pacco SEUR con Peek

[Peek](/) interroga direttamente SEUR per i numeri di 21 cifre e i codici `REC` (salvo `000` tra la 13ª e la 15ª cifra, attribuito ad Australia Post: premi «Cambia» e scegli SEUR sotto «Compatibili con questo numero»). Con 22 cifre interroga SEUR e Austrian Post prima dei servizi di tracciamento e di USPS. Con 14 cifre in Italia interroga prima [BRT](carrier:brt), Hermes, DPD Belgium, [DPD Svizzera](carrier:dpd-ch) e DPD Paesi Bassi, poi [DPD Germania](carrier:dpd-de), [Chronopost](carrier:chronopost), SEUR, DPD UK e DPD Portogallo. Dopo «Verifica dei servizi di tracciamento…» vedrai «SEUR ha questo pacco.» se solo SEUR lo conosce; altrimenti usa «Cambia» (o «Cambia corriere»), anche per i codici a 7 cifre che SEUR riutilizza. I link con `tracking=` funzionano senza CAP.

Peek mostra stato, cronologia e peso, senza mappa perché SEUR non indica località. Gli stati restano in spagnolo tranne la consegna (`Delivered`, «Consegnato»); i ritardi non cambiano la fase. Peek scarta numero di colli, mittente, destinatario, PIN e QR, legge gli orari sull’ora di Madrid («Orari nel tuo fuso orario: …») e segue i multicollo tramite altri servizi.

Peek controlla gli aggiornamenti fino a ogni 10 minuti mentre hai il pacco aperto sullo schermo, o nel giorno della consegna con le notifiche attive, e ogni ora negli altri casi (anche dopo due giorni senza novità o 12 ore su «Etichetta creata» o «Preso in carico»). Quando il pacco è «In consegna», verifica fino a ogni 2 minuti dalle 6:00 (ora di Madrid) finché SEUR risponde direttamente; tramite soli servizi esterni ogni ora, ogni 3 ore dopo un giorno fermo e fino a ogni 15 minuti nel giorno della consegna mentre hai il pacco aperto o le notifiche attive («Ogni scansione», «Passaggi importanti», «Solo la consegna»).

## Domande frequenti sul tracciamento SEUR

### Perché il numero SEUR non viene trovato?

SEUR restituisce la stessa risposta per una spedizione recentissima e per un numero inesistente; se persiste, chiedi al venditore se ha affidato il collo.

### Quanto impiega un pacco SEUR dalla Spagna?

Secondo Packlink: 24 ore lavorative nella Spagna peninsulare con SEUR 24, 48–72 ore per le Canarie (72–96 per La Gomera ed El Hierro) e 2–4 giorni verso le principali città europee con SEUR Classic.

### Cosa significa l’avviso di ritardo di SEUR?

Segnala un possibile slittamento senza motivo né nuova data; Peek mantiene la fase precedente.

### SEUR, DPD e BRT appartengono allo stesso gruppo?

Sì: SEUR è il marchio spagnolo del gruppo Geopost, insieme a DPD e a [BRT](carrier:brt) in Italia.

:::sources
- [Geopost: Who we are](https://www.geopost.com/en/about-us/who-we-are/) – gruppo Geopost
- [Geopost: SEUR](https://www.geopost.com/en/about-us/our-companies-and-partners/seur/) – rete Pickup
- [Packlink: SEUR](https://www.packlink.es/seur/) – tempi stimati
- [Your Europe: Spedizione e consegna](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_it.htm) – responsabilità venditore
- [INCIBE: SMS fraudolenti SEUR](https://www.incibe.es/ciudadania/avisos/circulan-sms-fraudulentos-que-suplantan-seur-que-no-te-enganen) – allerta truffe
- [BRT: BRT-fermopoint](https://www.brt.it/en/brt-fermopoint/) – 11.600 punti e 10 giorni
- [BRT: FAQ](https://www.brt.it/en/customer-service/faq/) – tracciamento BRT
- [BRT: Assistenza](https://services.brt.it/en/support) – supporto
- [DPD Svizzera: Pickup Location](https://www.dpd.com/ch/it/dpd-pickup-location/) – documento
- [DPD Svizzera: Contattateci](https://www.dpd.com/ch/it/centro-supporto/la-richiesta/) – 058 311 13 13
- [UFDSC: Ricevere pacchi](https://www.bazg.admin.ch/it/ricevere-lettere-e-pacchi) – dogana svizzera
- [UFDSC: Spese di imposizione](https://www.bazg.admin.ch/it/costi-imposizione-e-tracciamento-pacchi) – corriere referente
- [Commissione europea: Territorial Scope](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Canarie, Ceuta, Melilla
- [Commissione europea: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 3 € e 2 €
- [Revenue: Union Handling Fee](https://www.revenue.ie/en/corporate/press-office/press-releases/2026/pr-092926-handling-fee.aspx) – riaddebito 2 €
- [Universal Parcel Scraper: SEUR](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/seur) – dati letti da Peek
:::
