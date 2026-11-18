---
title: Tracking Hermes Germania: stati spiegati, consegna e dogana
description: Traccia un pacco Hermes Germany (myHermes) in italiano: formati del numero di spedizione, significato degli stati, consegna in Italia o Svizzera, dogana e Peek.
slug: tracking-hermes
published: 2026-11-18
updated: 2026-11-18
---

Hermes Germany (indicato anche come *myHermes* o *Hermes Borderguru*) è il corriere pacchi tedesco del gruppo Otto (*Otto Group*). Per seguire un ordine partito dalla Germania puoi usare il sito tedesco `myhermes.de` oppure incollare il codice nel riquadro qui sopra su [Peek](/) per leggere subito lo stato in italiano e gli orari nel tuo fuso orario.

Nel Regno Unito l'ex Hermes UK è diventato [Evri](carrier:evri) nel marzo 2022, mentre Hermes Germany resta un corriere tedesco distinto. Per mobili e grandi elettrodomestici consegnati in Germania da squadre di due operatori opera una divisione separata (*Hermes Einrichtungs-Service*).

## Numero di tracking Hermes: dove trovarlo e formati

Il codice di spedizione (*Sendungsnummer*) si trova nell'e-mail di invio del negozio o nel riepilogo dell'ordine (ad esempio in *Mein Konto* su OTTO). Se non lo trovi, chiedilo al mittente o leggi [come trovare il numero di tracciamento](guide:find-tracking-number).

Il servizio pubblico Hermes Germany accetta codici alfanumerici da 8 a 20 caratteri con almeno una cifra:

| Formato (inventato) | Che cos'è | Riconoscimento in Peek |
| --- | --- | --- |
| `H1048291057364928` | `H` seguita da 16–19 cifre: esclusivo di Hermes Germany. | Riconosciuto subito come Hermes Germany. |
| `74829105736493` | 14 cifre con controllo Hermes: condiviso con [DPD](carrier:dpd-de), [BRT](carrier:brt), [Chronopost](carrier:chronopost) e altri. Con prefisso `022` (`02248291057367`), Hermes viene interrogato per primo. | Proposto come "Probabilmente Hermes Germany"; un codice a 15 cifre con zero iniziale extra (`002248291057367`) viene verificato allo stesso modo. |
| `H482910573649283` | `H` seguita da 15 cifre: Hermes Germany oppure [Evri](carrier:evri) dal Regno Unito. | Peek interroga Hermes Germany (ed Evri se la cifra finale supera il controllo Evri). |

Il servizio bagagli *Hermes Gepäckversand* (Deutsche Bahn) e i riferimenti a 14 cifre fuori controllo codice a barre si cercano tramite numero d'ordine: premi "Cambia" (o "Cambia corriere" nella scheda) e scegli **Hermes Germany**.

## Stati del tracking Hermes spiegati in italiano

Il sito `myhermes.de` è in tedesco; per chi legge in italiano, Peek richiede a Hermes Germany i testi ufficiali in inglese, anche se alcune righe restano in tedesco:

| Messaggio di Hermes | Fase in Peek | Significato pratico |
| --- | --- | --- |
| `The shipment was announced to Hermes electronically. Further information will follow as soon as Hermes received the shipment.` | "Etichetta creata" | Dati trasmessi dal mittente; Hermes non ha ancora ricevuto fisicamente il pacco. |
| `The shipment was taken over by Hermes in … and is prepared for further shipping.` | "Preso in carico" | Pacco preso in carico nella filiale tedesca indicata. |
| `Die Sendung wird an Hermes weitergeleitet. Weitere Informationen folgen, sobald uns die Sendung erreicht hat.` | "In transito" | Spedizione internazionale in viaggio verso la rete Hermes. |
| `The shipment has arrived in the destination country Italy and is prepared for the further shipping.` | "In transito" | Arrivato nel paese di destinazione (es. Italia) e in smistamento verso il partner locale. |
| `The shipment was prepared for further shipping (Italy).` | "In transito" | Smistato dal partner nel paese indicato tra parentesi. |
| `The shipment was loaded into the delivery vehicle and is expected to be delivered today.` / `The shipment was loaded into the delivery vehicle. The delivery is expected to take place today.` | "In consegna" | Caricato sul furgone per la consegna prevista oggi. |
| `The shipment is ready for pick up in a parcel shop/store.` | "Pronto per il ritiro" | Disponibile in un punto di ritiro o *PaketShop*. |
| `The shipment could not be delivered.` / `The shipment could not be delivered because the address was not found.` | "Tentativo di consegna" | Consegna non riuscita o indirizzo non trovato (`Die Zustelladresse wurde nicht gefunden. Mögliche Gründe: Fehler in der Adressübermittlung. Bitte PaketShop buchen oder Kundenservice kontaktieren.`). |
| `The shipment was not picked up in the parcel shop/store.` | "Problema segnalato" | Non ritirato entro la giacenza nel punto di ritiro; rientra al mittente. |
| `The shipment was delivered to the recipient address.` / `Die Sendung wurde erfolgreich zugestellt.` | "Consegnato" | Consegnato all'indirizzo indicato o dal partner estero. |

Le righe `Für die Sendung wurde ein WunschPaketShop gebucht.` e `Ein WunschTag für die Zustellung wurde gebucht.` indicano una preferenza prenotata, non uno spostamento: Peek le ignora. Se il giro termina per mancanza di tempo prima del tuo indirizzo, il collo resta "In transito" per il giorno lavorativo successivo.

## Consegna in Germania e spedizioni verso Italia e Svizzera

**In Germania.** La rete conta oltre 17.000 *Hermes PaketShops*. Sugli articoli disponibili spediti da OTTO con *Hermes Paketversand*, la consegna avviene di norma in 1–2 giorni lavorativi (lunedì–sabato). Con *Hermes WunschZustellung* (tracking o app) chi riceve in Germania può scegliere luogo sicuro (*Ablageort*), vicino (*Nachbar*), *PaketShop* o altro giorno (*Tag*). Nei *PaketShop* la giacenza è fino a 7 giorni lavorativi con documento d'identità (*Personalausweis*). Nei locker *Hermes Box* si richiede il codice sul tracking per ritirare entro 72 ore (mostrato come "Pronto per il ritiro" fino al prelievo). Le consegne a due operatori (*2-Mann-Teams*) prevedono avviso via e-mail/SMS, chiamata 30–60 minuti prima e *Live-Tracking* sulla mappa alla mezz'ora.

**Verso Italia e Svizzera.** All'estero Hermes affida l'ultimo miglio a un corriere partner locale. Quando Hermes espone il link del partner internazionale e Peek ne riconosce dominio e codice, la scheda mostra "Consegna con …" (o "Annunciato a …, non ancora ricevuto" prima della prima scansione del partner) e segue gli eventi locali. Giacenze e punti di ritiro seguono le regole del corriere locale.

## Dogana, IVA e truffe via SMS

- **Verso l'Italia:** dalla Germania le merci circolano nell'UE senza dazi né sdoganamento, salvo i territori esclusi dall'ambito doganale/IVA UE come **Livigno** e **Campione d'Italia**. Per le vendite a distanza extra-UE l'esenzione sotto i 150 € cessa: dal **1° luglio 2026** dazio fisso di **3 €** per categoria di articolo fino a 150 €, più dal **1° novembre 2026** commissione UE di gestione di **2 €** per categoria di articolo indipendentemente dal valore.
- **Verso la Svizzera:** secondo l'UDSC, IVA e dazi non si riscuotono sotto **5 CHF** ciascuno (regali tra privati esenti fino a **100 CHF**, esclusi alcol e tabacchi); oltre soglia si aggiungono le spese di sdoganamento del vettore, come quelle di [La Posta Svizzera](carrier:swiss-post).

> **Attenzione al phishing:** un pacco dalla Germania verso il territorio doganale italiano non paga dogana. In Italia [Poste Italiane](carrier:poste-italiane) riscuote gli oneri sulle sole spedizioni extra-UE alla consegna o tramite *Gestione delle spese di importazione* su `poste.it` e invita a diffidare di SMS o e-mail con link di pagamento sospetti.

## Pacco Hermes in ritardo, non ricevuto o danneggiato

- **Fermo su "Etichetta creata":** Hermes attende ancora il collo dal mittente; contatta il negozio e leggi [perché il tracking non si aggiorna](guide:tracking-not-updating).
- **Risulta consegnato ma manca:** verifica vicino e punto di ritiro e avvisa il venditore; vedi [consegnato ma non ricevuto](guide:delivered-not-received).
- **Acquisti online (UE e Germania):** il venditore professionale risponde del trasporto fino alla consegna fisica (§ 475 BGB) entro **30 giorni** salvo diverso accordo; hai **14 giorni** dalla ricezione per il recesso (§§ 355 e 356 BGB).
- **Diritto tedesco dei trasporti e conciliazione:** per il § 438 HGB i danni visibili vanno contestati alla consegna, quelli occulti per iscritto entro **7 giorni** e il ritardo entro **21 giorni** (diritti del destinatario al § 421 HGB). Se un reclamo scritto a Hermes per perdita, furto o danno di un invio senza condizioni speciali non si risolve (lite tra 10 € e 2.500 €), mittente o destinatario possono adire gratuitamente in tedesco la **Schlichtungsstelle Post** (*Bundesnetzagentur*), obbligatoria per l'operatore se ricorre un consumatore. In Italia, dopo reclamo all'operatore locale rimasto senza esito per 45 giorni, è attiva ConciliaWeb di AGCOM.

## Tracciare un pacco Hermes Germany con Peek

[Peek](/) interroga il servizio destinatari di Hermes senza CAP e accetta i link `myhermes.de`. Sui 14 cifre condivisi mostra "In attesa della prima scansione di Hermes Germany" fino al primo evento. Estrae dai testi la località del deposito, della regione, del paese o del *PaketShop* per la mappa, converte le date ("Orari nel tuo fuso orario: …") e legge solo l'indirizzo del *PaketShop* attivo o usato, mai quello domestico.

Mentre tieni aperta la spedizione, o nel giorno di consegna con notifiche attive, Peek controlla fino a ogni 10 minuti — e ogni 2 minuti nel giorno di uscita in consegna dalle 6:00 tedesche (o mezz'ora prima della fascia Hermes) — finché Hermes risponde direttamente (tramite servizi di tracciamento: fino a ogni 15 minuti in quel giorno, altrimenti ogni ora); negli altri casi controlla ogni ora, anche dopo due giorni senza novità (o 12 ore su "Etichetta creata" o "Preso in carico"). Con un partner locale segue la cadenza di quest'ultimo. Avvisi per "Ogni scansione", "Passaggi importanti" o "Solo la consegna"; i vecchi codici cancellati restituiscono 404.

## Domande frequenti (FAQ)

### Hermes ed Evri sono lo stesso corriere?

Solo nel Regno Unito, dove Hermes UK è diventato [Evri](carrier:evri) nel marzo 2022. Hermes Germany è una società tedesca distinta del gruppo Otto; sui codici `H` più 15 cifre Peek controlla entrambi se il controllo finale coincide.

### Perché le scansioni di Hermes sono in inglese o in tedesco?

Il servizio Hermes Germany fornisce i testi degli eventi solo in tedesco e in inglese. Per chi legge in italiano Peek richiede l'inglese (alcune righe partner restano in tedesco) e traduce la fase principale in italiano.

### Come seguo mobili spediti con Hermes in Germania?

Le consegne con squadra a due operatori dipendono da *Hermes Einrichtungs-Service*, separato da *myHermes* (preavviso via e-mail/SMS e telefonata 30–60 minuti prima). Selezionalo con "Cambia corriere" su Peek se necessario.

### Si paga la dogana su un pacco Hermes dalla Germania?

No nel territorio doganale italiano (libera circolazione UE, esclusi Livigno e Campione d'Italia). In Svizzera lo sdoganamento scatta da 5 CHF di IVA o dazi.

:::sources
- [Otto Group: Konzernfirmen](https://www.ottogroup.com/de/ueber-uns/konzernfirmen/hermes-germany.php) – gruppo Otto
- [OTTO: Lieferung](https://www.otto.de/service/lieferung/), [FAQ](https://www.otto.de/service/faq/) e [Live-Tracking](https://www.otto.de/shoppages/geographische_sendungsverfolgung) – tempi, *WunschZustellung*, 17.000 *PaketShops* (7 giorni, documento) e squadre a due
- [Evri: About us](https://www.evri.com/about-us) – passaggio da Hermes UK a Evri (marzo 2022)
- [Bundesnetzagentur: Schlichtungsstelle Post](https://www.bundesnetzagentur.de/DE/Vportal/Schlichtung/Schlichtung_Post/start.html) – conciliazione postale gratuita
- [BGB: § 475](https://www.gesetze-im-internet.de/bgb/__475.html), [§ 355](https://www.gesetze-im-internet.de/bgb/__355.html), [§ 356](https://www.gesetze-im-internet.de/bgb/__356.html) e [HGB: § 421](https://www.gesetze-im-internet.de/hgb/__421.html), [§ 438](https://www.gesetze-im-internet.de/hgb/__438.html) – garanzia, recesso e termini di trasporto
- [Your Europe: Consegna](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_it.htm), [EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en), [Guidance 3 €](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) e [Ambito territoriale](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – regole UE e dogana 2026
- [Poste Italiane: Estero](https://www.poste.it/ricevere-spedizioni-da-estero), [Antitruffa](https://www.poste.it/sicurezza-online/guide-per-operare-in-sicurezza/come-difendersi-dalle-truffe) e [AGCOM: Contenzioso postale](https://www.agcom.it/faq-contenzioso-tra-utenti-e-operatori-postali) – oneri extra-UE, phishing e ConciliaWeb
- [UDSC: Ricevere pacchi](https://www.bazg.admin.ch/it/ricevere-lettere-e-pacchi), [Costi](https://www.bazg.admin.ch/it/costi-imposizione-e-tracciamento-pacchi) e [Posta Svizzera: Sdoganamento](https://www.post.ch/it/ricezione/sdoganamento-all-importazione) – importazioni in Svizzera
- [Universal Parcel Scraper: hermes-de](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/hermes-de) e [statuses.json](https://github.com/plhery/universal-parcel-scraper/blob/main/carriers/hermes-de/statuses.json) – lettura in Peek
:::
