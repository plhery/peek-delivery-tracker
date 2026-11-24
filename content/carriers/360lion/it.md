---
title: Tracciamento 360Lion: codici WSH e AR, stati e contatti
description: Traccia un pacco 360Lion Express dalla Cina all’America Latina o agli USA: numeri WSH e AR, codici di scansione, consegna locale, pause e aiuto.
slug: tracciamento-360lion
published: 2026-11-24
updated: 2026-11-24
---

360Lion (*360Lion Express*, in cinese *纬狮物流 / 纬狮物联网*) è un operatore cinese di logistica e-commerce transfrontaliera fondato nel 2015 con sede a Shenzhen e filiali e centri operativi a Shanghai, Canton, Dongguan, Yiwu e Hong Kong. È specializzato in linee aeree dedicate per piccoli pacchi, spedizioni FBA/FBM e magazzini esteri dalla Cina verso l’America Latina e gli Stati Uniti (Messico, Cile, Perù, Brasile, Colombia, Ecuador, Argentina, Uruguay, Bolivia, Paraguay, El Salvador, Costa Rica, Guatemala, Venezuela, Panama e USA) per venditori su Mercado Libre, Amazon, AliExpress, Shopee, SHEIN, Temu, TikTok Shop, Walmart e Falabella. Puoi controllare una spedizione sul [portale di tracciamento 360Lion](https://toms.360lion.com/track) o su [360lion.com/en/](https://www.360lion.com/en/) (fino a 50 codici alla volta), oppure incollare il codice nel campo qui sopra.

## Codici di tracciamento 360Lion: formati WSH e AR

Cerca il numero di spedizione nella pagina ordini del marketplace o nell’e-mail di conferma spedizione. I pacchi 360Lion usano di solito due formati:

| Formato (inventato) | Che cos’è |
| --- | --- |
| `WSHMX4829173640YQ` | Lettera di vettura della linea dedicata 360Lion: `WSH`, codice paese di destinazione di due lettere (per esempio `MX` per il Messico), 10 cifre e `YQ`. |
| `AR4829173640` | `AR` seguito da 10 cifre (usato sui pacchi diretti in Argentina). Condivide questo formato con [Colis Privé](carrier:colis-prive), [Aramex Australia](carrier:aramex-au) e [Aramex New Zealand](carrier:aramex-nz). |

Se il tuo ordine verso Brasile o Messico usa il codice a barre di un altro corriere — come `AJ` per [Anjun Logistics](carrier:anjun), `8880` per [J&T Express Brazil](carrier:j-and-t-br) o `JMX` per [J&T Express Mexico](carrier:j-and-t-mx) — consulta [formati dei numeri di tracciamento](guide:tracking-number-formats).

## Cosa significano gli stati di tracciamento 360Lion

360Lion raggruppa le spedizioni in stati generali (`Processing`, `Pre-alert`, `In transit.`, `Returned`, `Delivered`) associati a codici di scansione (`WSO*` in partenza dalla Cina, `WSD*` a destinazione). Quando il pacco passa al corriere locale dell’ultimo miglio, il tracciamento può riportare le diciture di quel corriere.

| 360Lion mostra (codice / gruppo) | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `Processing` (`PRECESSING`) | Il venditore ha creato l’ordine di spedizione con 360Lion prima dell’avvio delle scansioni di linea. | «Etichetta creata» |
| Fase `HUB` | Preso in carico nell’hub di smistamento di origine di 360Lion in Cina. | «Preso in carico» |
| `departed from airport` (`WSO04`), `arrived at airport` (`WSD00`), `In transit.` (`IN_TRANSIT`) | Partito in aereo dalla Cina (con codice aeroporto come PVG) e atterrato all’aeroporto di destinazione. | «In transito» |
| Fase `CUSTOMS` | In corso lo sdoganamento all’esportazione o all’importazione nel paese di destinazione. | «In dogana» |
| `handover to lastmile` (`WSD02`), `Arrived at sorting center` (`WSD051`), `package on the way to delivery office` (`WSD050`) | Affidato al partner locale dell’ultimo miglio, smistato e inviato verso l’ufficio di consegna locale. | «In transito» |
| `Returned` (`RETURNED_BACK`) | Restituito verso il mittente o il magazzino di origine. | «Restituito al mittente» |
| `Delivered` (`DELIVERED`) | Consegnato al destinatario dal partner locale dell’ultimo miglio. | «Consegnato» |

Nota che lo stato `Pre-alert` (`WARNING` / *预警*) di 360Lion può indicare sia un preavviso sia un avvertimento: Peek lascia quindi alle singole righe di scansione la determinazione della fase anziché trattare `Pre-alert` da solo come errore.

## Come funziona la consegna transfrontaliera 360Lion

Su [360lion.com/en/](https://www.360lion.com/en/) e [360lion.com](https://www.360lion.com/), 360Lion si presenta come vettore di linea transfrontaliero e agente generale di vendita e servizi aerei (GSSA) con voli charter regolari dalla Cina verso l’America Latina, supportato da magazzini esteri in Messico, Cile, Brasile, Colombia e Stati Uniti.

**Consegna dell’ultimo miglio.** Dopo l’aeroporto di destinazione (`WSD00`) e la dogana d’importazione, 360Lion consegna il pacco (`WSD02`) a un corriere locale nel tuo paese, che effettua la consegna finale.

**Regole per i destinatari e dogana.** Poiché 360Lion serve mittenti commerciali anziché consumatori finali, il sito non pubblica limiti di tentativi di consegna, periodi di giacenza nei punti di ritiro né garanzie sui tempi per piccoli pacchi (la [pagina FAQ e condizioni](https://www.360lion.com/?m=home&c=Lists&a=index&tid=82) tratta regole commerciali per i venditori come trasporto aereo SEND, merci vietate e reclami venditore entro 30 giorni). IVA e dazi d’importazione seguono le norme doganali del tuo paese. Consulta [pacchi fermi in dogana](guide:customs).

## Pacco 360Lion bloccato, fermo o non consegnato

Poiché 360Lion stipula il contratto con il negozio online o marketplace anziché con l’acquirente, rivolgiti sempre prima al venditore o alla piattaforma per ritardi, errori di indirizzo o rimborsi.

**Nessun aggiornamento tra Cina e destinazione.** Voli a lungo raggio dalla Cina all’America Latina e code doganali creano spesso pause di vari giorni tra `departed from airport` (`WSO04`) e `arrived at airport` (`WSD00`). Consulta [perché il tracciamento non si aggiorna](guide:tracking-not-updating).

**Passaggio locale senza secondo codice.** Peek segue le righe del corriere locale attraverso il tracciamento 360Lion sotto il codice `WSH` o `AR` e non mostra un codice locale distinto. Se la consegna fallisce e ti servono i recapiti o il numero del corriere locale, chiedili al venditore o scrivi all’ufficio ricerche pacchi di 360Lion.

**Consegnato, danneggiato o smarrito.** Controlla prima con i familiari, i vicini e la portineria se risulta `Delivered`; se manca o è danneggiato, documenta tutto e apri una contestazione con il venditore o il marketplace prima della scadenza della protezione acquisti. Consulta [consegnato ma non ricevuto](guide:delivered-not-received), [pacco danneggiato](guide:damaged-parcel), [pacco smarrito](guide:lost-parcel) e [restituito al mittente](guide:returned-to-sender).

## Assistenza clienti e contatti 360Lion

Recapiti pubblicati sulla [pagina Contatti di 360Lion](https://www.360lion.com/?m=home&c=Lists&a=index&tid=79) e su [360lion.com/en/](https://www.360lion.com/en/):

- **E-mail ricerca pacchi (*查件业务*):** `customerservice@360lion.com`.
- **E-mail generale / commerciale:** `info@360lion.com`.
- **Telefono sede di Shenzhen:** `+86 755 2360 1325` (Shanghai `+86 21 6142 1360`, Canton `+86 20 8653 4133`).
- **Indirizzo sede:** 7/F, Block A, Star River World, No. 1 Yabao Road, Bantian Street, Longgang District, Shenzhen, Guangdong, Cina.

> Fai attenzione a falsi SMS o messaggi WhatsApp che chiedono un pagamento urgente tramite link per «sbloccare» un pacco internazionale in dogana. Verifica prima qualsiasi richiesta doganale reale nella pagina ordini del marketplace o sul portale postale/doganale ufficiale del tuo paese. Consulta [SMS truffa sulle consegne](guide:delivery-scam-texts).

## Traccia un pacco 360Lion con Peek

[Peek](/) interroga automaticamente 360Lion quando inserisci un codice `WSH...YQ` (come `WSHMX4829173640YQ`, cifre inventate) — dove 360Lion è l’unico candidato — oppure un codice `AR` + 10 cifre (come `AR4829173640`), per cui Peek interroga 360Lion insieme a [Colis Privé](carrier:colis-prive), [Aramex Australia](carrier:aramex-au) e [Aramex New Zealand](carrier:aramex-nz) e mostra «360Lion ha questo pacco.» quando 360Lion risponde. Incollare un link `toms.360lion.com/track?trackNumber=` seleziona direttamente 360Lion; altrimenti tocca «Cambia» mentre aggiungi il pacco oppure «Cambia corriere» nella scheda del pacco e scegli 360Lion (lo trovi anche cercando «360Lion Express», «WSH» o «纬狮物流»).

Peek legge direttamente il tracker di 360Lion e mostra la fase («Etichetta creata», «Preso in carico», «In transito», «In dogana», «Restituito al mittente» o «Consegnato»), il testo di ogni scansione (quello del corriere locale quando 360Lion lo inoltra, altrimenti l’inglese di 360Lion), la località come nome di paese o codice aeroporto di tre lettere, il paese di destinazione e l’ora di consegna. Ogni scansione porta il fuso orario del luogo, come `+8` in Cina e `-3` in Argentina, e viene mostrata sull’orologio del tuo dispositivo. Peek esclude la barra di avanzamento del sito, il paese di origine, il contatore dei giorni e la prova di consegna; il tracker di 360Lion non fornisce data prevista, nome servizio né peso.

Mentre tieni il pacco aperto in Peek, oppure il giorno della consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti finché il tracker di 360Lion risponde. Altrimenti Peek controlla ogni ora. Dopo 12 ore con solo «Etichetta creata» o «Preso in carico», o dopo due giorni senza novità, controlla ogni ora comunque tu lo segua, e ogni 6 ore dopo sette giorni su «Etichetta creata». Quando il tracciamento di 360Lion non risponde e Peek segue il pacco solo tramite servizi di tracciamento, controlla ogni ora, ogni 3 ore dopo una giornata senza movimenti e fino a ogni 15 minuti nel giorno in cui il pacco è in consegna, mentre lo tieni aperto o hai le notifiche attive.

Quando la scansione più recente risale a quattro giorni prima e il pacco è ancora su «Etichetta creata», «Preso in carico» o «In transito», Peek lo segnala con la data di quella scansione, ad esempio «Nessun aggiornamento da lun 12 ott». La pagina del pacco mostra allora: «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a 360Lion.», con il link «Chiedi aiuto a 360Lion» alla pagina contatti di 360Lion. Dopo tre settimane senza nuove scansioni mostra: «Niente di nuovo da più di tre settimane. Se il pacco non è ancora arrivato, contatta il venditore. Peek continua a chiedere a 360Lion.»

Puoi scegliere le notifiche «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek monitora solo gli aggiornamenti e non può gestire pratiche doganali né contattare 360Lion o il corriere locale al posto tuo.

## Domande sul tracciamento 360Lion

### Che cos’è 360Lion Express?

360Lion (fondata nel 2015 a Shenzhen, Cina) è un operatore logistico e-commerce transfrontaliero specializzato in linee aeree per piccoli pacchi dalla Cina all’America Latina e agli Stati Uniti, utilizzato da venditori su Mercado Libre, Amazon, AliExpress, Shopee, SHEIN e Temu.

### Cosa significano i codici `WSH...YQ` e `AR...`?

`WSH` + due lettere paese + 10 cifre + `YQ` (ad esempio `WSHMX4829173640YQ`, cifre inventate) è il formato della linea transfrontaliera dedicata di 360Lion, mentre `AR` + 10 cifre (`AR4829173640`) è usato sui pacchi 360Lion diretti in Argentina.

### Chi consegna localmente i pacchi 360Lion?

360Lion trasporta in aereo i pacchi dalla Cina fino all’aeroporto del tuo paese e li affida (`handover to lastmile`) a un servizio postale o corriere locale per la consegna a domicilio. Le scansioni del partner locale compaiono nel tracciamento di 360Lion.

### Come posso contattare 360Lion per un pacco mancante?

Chiedi prima al venditore o al marketplace per protezione acquisti e rimborsi. Puoi anche scrivere all’ufficio ricerche pacchi di 360Lion all’indirizzo `customerservice@360lion.com` o chiamare la sede di Shenzhen al `+86 755 2360 1325`.

:::sources
- [360Lion Express: English Home](https://www.360lion.com/en/) – fondata nel 2015, filiali Shenzhen/Shanghai/Canton/Yiwu/Dongguan/Hong Kong, tracker fino a 50 codici, marketplace partner e indirizzo sede
- [360Lion: Chinese Home](https://www.360lion.com/) – rotte dedicate per piccoli pacchi in 15 paesi latinoamericani e USA, voli charter e magazzini esteri
- [360Lion: Contact Us](https://www.360lion.com/?m=home&c=Lists&a=index&tid=79) – `customerservice@360lion.com`, `info@360lion.com`, telefoni e sedi Shenzhen/Canton/Shanghai (URL di aiuto collegato da Peek)
- [360Lion: About Us](https://www.360lion.com/?m=home&c=Lists&a=index&tid=78) – presentazione aziendale
- [360Lion: Dedicated Small Parcels](https://www.360lion.com/?m=home&c=Lists&a=index&tid=90) – servizio B2C transfrontaliero piccoli pacchi
- [360Lion: FAQ & Terms](https://www.360lion.com/?m=home&c=Lists&a=index&tid=82) – condizioni per venditori, articoli vietati e reclami venditore entro 30 giorni
- [360Lion: Tracking Portal](https://toms.360lion.com/track) – portale pubblico di tracciamento
- [Universal Parcel Scraper: 360lion](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/360lion) – rilevamento `WSH` e `AR`, regole link `toms.360lion.com`, codici `WSO`/`WSD`, fusi orari espliciti, campi esclusi e URL di aiuto
:::
