---
title: Tracciamento Sengi Express: codici Cina–Italia e Poste
description: Traccia un pacco Sengi Express (gruppo Poste Italiane) da Cina a Italia o Europa: codici SDA/MT, dogana, passaggio Crono, ritardi e contatti.
slug: tracciamento-sengi-express
published: 2026-11-24
updated: 2026-11-24
---

**Sengi Express** (*Guangzhou Sengi Logistics Co., Ltd.* / *Seng Yi (Shenzhen) Global Supply Chain Management Co., Ltd.*, in cinese *广州森吉物流有限公司*) è un operatore logistico transfrontaliero controllato a maggioranza da **Poste Italiane** ([portal.sengiexpress.com/about/](https://portal.sengiexpress.com/about/)). Specializzata nell’iniezione diretta e-commerce Cina–Italia e Cina–Europa, Sengi gestisce consolidamento in Cina, trasporto aereo e marittimo, sdoganamento europeo B2C/B2B presso gateway come Milano Malpensa (**MXP**), Amsterdam (**AMS**), Liegi (**LGG**) e Parigi (**CDG**), trasporto su gomma paneuropeo tramite Sennder del gruppo Poste Italiane e consegna finale a domicilio o in locker in Italia tramite la rete Crono di [Poste Italiane](carrier:poste-italiane). Puoi tracciare un pacco su [portal.sengiexpress.com](https://portal.sengiexpress.com/home) oppure incollare il codice nel riquadro qui sopra.

## Codici di tracciamento Sengi Express (formati SDA `...C...F` e `MT`)

Sengi Express emette spesso già dalla Cina una lettera di vettura **Poste Italiane / SDA** a 13 caratteri così che lo stesso codice funzioni dal magazzino cinese di Sengi fino alla consegna in Italia, accanto a codici transfrontalieri da 16 caratteri con suffisso `MT`:

| Formato (inventato) | Che cos’è |
| --- | --- |
| `482C73918462F` | Lettera di vettura SDA / Poste Italiane da 13 caratteri (`3 cifre` + `C` + `8 cifre` + `F`) tracciata da Sengi Express prima che Poste Italiane la scansioni in Italia. |
| `SENGI482917360MT` | Codice transfrontaliero Sengi da 16 caratteri (`5 lettere` + `9 cifre` + `MT`). |

Sengi assegna a un pacco diretto in Italia la lettera di vettura SDA prima che SDA la conosca, perciò il tracciamento di Poste Italiane può non trovare subito il numero; il tracciamento Sengi segue il pacco dal magazzino in Cina fino al destinatario in Italia. Se selezionato manualmente, il tracker Sengi accetta anche altre lettere di vettura partner alfanumeriche da 8 a 40 caratteri. Vedi i [formati dei codici di tracciamento](guide:tracking-number-formats).

## Cosa significano gli stati di tracciamento Sengi Express

Sengi Express pubblica descrizioni inglesi (e cinesi) delle scansioni transfrontaliere insieme a codici evento facoltativi e data/ora:

| Sengi Express mostra | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `Order information received. We're expecting your parcel to arrive with us.` (codice `10`) | Il venditore ha trasmesso i dati elettronici prima dell’ingresso fisico nel magazzino cinese di Sengi. | «Etichetta creata» |
| `Arrived at Destinated Airport`, `Terminal Notification of Arrival` | Il volo internazionale è atterrato al gateway aeroportuale europeo di destinazione (come MXP, AMS, LGG o CDG). | «In transito» |
| `Arrived at customs clearance warehouse` | Il pacco è entrato nel magazzino doganale europeo per lo sdoganamento. | «In dogana» |
| `Customs clearance inspection, Reason: supposed as undervaluation, waiting for customs check(VM)` | Selezionato dalle dogane europee per verifica del valore dichiarato o del contenuto (`VM`). | «In dogana» |
| `Ready for transit to last mile operator` | Sdoganato e in coda per il passaggio a [Poste Italiane](carrier:poste-italiane) o al corriere di destinazione. | «In transito» |

Una scansione con un’altra dicitura ricava la fase dal proprio testo. Sengi precisa che i destinatari possono seguire il pacco anche sul sito di [Poste Italiane](carrier:poste-italiane), con lo stesso codice `...C...F` o con il nuovo numero in caso di rietichettatura da parte di Sengi.

## Iniezione diretta, gateway doganali europei e consegna Poste Italiane

Secondo [Chi è Sengi Express](https://portal.sengiexpress.com/about/) e [Prodotti Sengi Express](https://portal.sengiexpress.com/product/):

- **Gateway aerei europei:** Sengi gestisce sdoganamento diretto B2C e B2B a Milano Malpensa (`MXP`), Amsterdam (`AMS`), Liegi (`LGG`) e Parigi Charles de Gaulle (`CDG`).
- **Servizi ultimo miglio Poste Italiane:** in Italia Sengi immette i pacchi nei servizi **Crono** standard, **Crono Express**, **Extralarge** (fuori misura / ultimo miglio FBA), **Crono ADP** (locker di ritiro) e **Crono Reverse** (resi) di Poste Italiane.
- **Controlli sul valore doganale (`VM`) e oneri UE:** le spedizioni dalla Cina verso l’UE sono soggette alle norme doganali UE, all’IVA di importazione (IOSS o pagata alla consegna) e agli oneri doganali/di gestione UE sugli ordini extra-UE. Se il tracciamento indica `supposed as undervaluation, waiting for customs check(VM)`, la dogana sta verificando il valore dichiarato con importatore o spedizioniere — attendi lo sdoganamento o avvisa il venditore. Vedi [pacchi fermi in dogana](guide:customs).

## Pacco Sengi Express fermo, silenzioso o smarrito

Poiché i marketplace online e i venditori cinesi detengono il contratto di spedizione con Sengi Express, **contatta sempre prima il venditore o il marketplace** per rimborsi, rispedizioni o documentazione sul valore doganale.

- **Pausa prima delle scansioni italiane:** i pacchi restano spesso alcuni giorni senza novità tra `Order information received`, `Arrived at Destinated Airport` e `Ready for transit to last mile operator` mentre i lotti aerei completano la dogana europea e raggiungono l’Italia su gomma. Vedi [perché il tracciamento smette di aggiornarsi](guide:tracking-not-updating).
- **Segui su Poste Italiane dopo il passaggio:** quando compare `Ready for transit to last mile operator` su una spedizione italiana `PI`, inserire lo stesso numero `...C...F` (o il nuovo numero rietichettato mostrato da Peek) su [Poste Italiane](carrier:poste-italiane) mostra smistamento italiano, tentativi di consegna e dettagli Punto Poste o locker.
- **Smarrito, danneggiato o segnato consegnato:** controlla prima con i vicini e presso il tuo ufficio Poste Italiane / Punto Poste, poi apri una contestazione sul marketplace prima che scada la protezione acquisti. Vedi [consegnato ma non ricevuto](guide:delivered-not-received), [pacco smarrito](guide:lost-parcel), [pacco danneggiato](guide:damaged-parcel) e [restituito al mittente](guide:returned-to-sender).

## Assistenza clienti e contatti Sengi Express

Recapiti ufficiali pubblicati su [Chi è Sengi Express](https://portal.sengiexpress.com/about/) e [Contatti Sengi Express](https://portal.sengiexpress.com/contact/):

- **Email:** `service@sengiexpress.com`.
- **Modulo di contatto online:** [portal.sengiexpress.com/contact/](https://portal.sengiexpress.com/contact/).
- **Operatore:** *Guangzhou Sengi Logistics Co., Ltd.* (*广州森吉物流有限公司*, ICP `粤ICP备2021101066号`), controllato a maggioranza da Poste Italiane.

> Fai attenzione ai falsi SMS che sostengono di dover pagare un onere Poste Italiane o doganale attraverso un link sconosciuto. Verifica sempre qualsiasi importo reale dalla pagina ordine del marketplace o dal sito ufficiale di Poste Italiane. Vedi [truffe via SMS sulle consegne](guide:delivery-scam-texts).

## Tracciare un pacco Sengi Express con Peek

Quando inserisci un codice `...C...F` da 13 caratteri (`482C73918462F`, inventato), [Peek](/) interroga prima [Poste Italiane](carrier:poste-italiane), poi AliExpress e Sengi Express (in Italia Sengi prima di AliExpress; in Cina prima AliExpress e poi Sengi), e mostra «Sengi Express ha questo pacco.» quando Sengi lo gestisce. Per un codice `...MT` (`SENGI482917360MT`, inventato), Peek interroga [MaltaPost](carrier:maltapost), i cui codici terminano anch’essi in `MT`: per quel codice o un’altra lettera di vettura partner, tocca «Cambia» in fase di aggiunta oppure «Cambia corriere» nella schermata del pacco e seleziona **Sengi Express** (anche cercare «Sengi» lo trova).

Peek legge il tracker Sengi e mostra la fase («Etichetta creata», «In transito», «In dogana» o un’altra fase se il testo di una scansione lo indica), il testo inglese di Sengi (o cinese in assenza dell’inglese) e il luogo. I codici evento impostano la fase e restano esclusi da tutte le app Peek. Sengi indica lo scarto da UTC delle scansioni; una scansione senza scarto viene datata nel fuso del luogo (Italia per `MXP`, Pechino per Canton) o in quello della scansione vicina, e Peek le mostra tutte con l’ora del tuo dispositivo. Sulla linea Poste Italiane di Sengi, Peek verifica che Poste Italiane abbia il pacco: la scheda mostra quindi «Consegna con» seguito da Poste Italiane e riporta il nuovo numero quando Sengi rietichetta il pacco.

Mentre tieni il pacco aperto in Peek, o il giorno della consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti; nel giorno in cui Sengi mostrasse una scansione di uscita in consegna (finora mai osservata), ogni 2 minuti dalle 6:00 sull’orologio dell’ultima scansione finché il tracciamento Sengi risponde. Altrimenti Peek controlla ogni ora. Dopo 12 ore con solo «Etichetta creata» o «Preso in carico», o dopo due giorni senza novità, controlla ogni ora comunque tu lo segua, e ogni 6 ore dopo sette giorni su «Etichetta creata». Quando il tracciamento Sengi non risponde e Peek segue il pacco solo tramite servizi di tracciamento, controlla ogni ora, ogni 3 ore dopo una giornata ferma e fino a ogni 15 minuti nel giorno in cui il pacco è in consegna, mentre hai il pacco aperto o le notifiche attive.

Quando la scansione più recente risale a quattro giorni prima e il pacco è ancora su «Etichetta creata», «Preso in carico» o «In transito», Peek lo segnala con il giorno di quella scansione, ad esempio «Nessun aggiornamento da lun 12 ott». La pagina del pacco mostra quindi «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a Sengi Express.», con un link «Chiedi aiuto a Sengi Express» alla pagina contatti di Sengi. Dopo tre settimane senza nuove scansioni mostra «Niente di nuovo da più di tre settimane. Se il pacco non è ancora arrivato, contatta il venditore. Peek continua a chiedere a Sengi Express.»

Puoi scegliere gli avvisi per «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek monitora solo gli aggiornamenti di tracciamento e non può sdoganare pacchi, modificare indirizzi di consegna né contattare Sengi Express o Poste Italiane al posto tuo.

## Domande sul tracciamento Sengi Express

### Che cos’è Sengi Express?

Sengi Express (*Guangzhou Sengi Logistics Co., Ltd.*) è un corriere logistico e-commerce Cina–Italia e Cina–Europa controllato da **Poste Italiane**, che cura consolidamento in origine, sdoganamento europeo (MXP, AMS, LGG, CDG) e immissione nell’ultimo miglio Crono di Poste Italiane.

### Perché il mio codice `...C...F` di Poste Italiane compare prima su Sengi Express?

Sengi assegna a un pacco per l’Italia la sua lettera di vettura SDA / [Poste Italiane](carrier:poste-italiane) (`482C73918462F`, inventato) prima che SDA la conosca. Il tracciamento Sengi mostra le scansioni di volo e dogana, mentre quello di Poste Italiane può non trovare subito il codice.

### Cosa significa `supposed as undervaluation, waiting for customs check(VM)`?

La dogana europea ha segnalato il pacco per un controllo del valore dichiarato (`VM`). Lo spedizioniere doganale o il venditore gestiscono i documenti; contatta il venditore se lo stato non si sblocca.

### Come contatto l’assistenza clienti Sengi Express?

Contatta prima il negozio online o il marketplace per rimborsi o documenti doganali. Sengi Express indica `service@sengiexpress.com` e un modulo di contatto online su [portal.sengiexpress.com/contact/](https://portal.sengiexpress.com/contact/).

:::sources
- [Sengi Express: portale di tracciamento](https://portal.sengiexpress.com/home) – tracciamento pubblico e dati *Guangzhou Sengi Logistics Co., Ltd.*
- [Sengi Express: chi siamo](https://portal.sengiexpress.com/about/) – controllo di maggioranza Poste Italiane, servizi transfrontalieri e `service@sengiexpress.com`
- [Sengi Express: prodotti e servizi](https://portal.sengiexpress.com/product/) – gateway doganali MXP/AMS/LGG/CDG, Poste Italiane Crono / Crono Express / Extralarge / Crono ADP / Crono Reverse e trasporto UE Sennder
- [Sengi Express: contatti](https://portal.sengiexpress.com/contact/) – pagina ufficiale contatti destinatari/clienti (URL di aiuto collegato da Peek)
- [Universal Parcel Scraper: sengi-express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/sengi-express) – regole rilevamento `...C...F` e `...MT`, parser `api-v2/tracking/web`, mappatura stati, gestione dello scarto da UTC dichiarato e passaggio Poste Italiane `PI` / `relabelTrackingNumber`
:::
