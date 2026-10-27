---
title: GLS Svizzera tracking: stati, Posta Svizzera e contatti
description: Segui un pacco GLS in Ticino e nel resto della Svizzera: com'è fatto il numero, perché serve l'NPA, cosa vogliono dire gli stati in inglese e chi contattare.
slug: gls-svizzera-tracking
published: 2026-10-11
updated: 2026-10-27
---

GLS è una rete internazionale di spedizione pacchi con sede ad Amsterdam, senza una società propria in Svizzera: i pacchi GLS che arrivano qui li sdogana e li consegna il suo partner, la Posta Svizzera. Per seguirne uno inserisci il numero del pacco e il tuo NPA sulla [pagina di tracking di GLS](https://gls-group.com/EU/en/parcel-tracking), oppure incolla il numero nel box qui sopra. Il numero lo ha il mittente: cercalo nell'e-mail di spedizione del negozio o nella pagina dell'ordine.

## Numero di tracking GLS: com'è fatto e dove trovarlo

Il tracking di GLS accetta numeri di pacco o Track ID. GLS stampa il Track ID sui suoi avvisi di passaggio, ma in Svizzera consegna la Posta e nessuna pagina parla di avvisi GLS. Né GLS né la Posta pubblicano il formato del numero, né dicono come lo ricevi in Svizzera: se non lo trovi nell'e-mail o nell'ordine, chiedilo al negozio. Queste sono le forme che Peek associa a GLS, con cifre inventate:

| Numero (inventato) | Che cos'è |
| --- | --- |
| `11111111111` | 11 cifre. Stessa forma di GLS Germany, GLS France, PostLogistics e DHL eCommerce Poland. |
| `000000000009` | 12 cifre, l'ultima è una cifra di controllo. Stessa forma di BRT, Mondial Relay, Colis Privé e altri. |
| `ABCD1234` | 8 lettere e cifre. Stessa forma di GLS Germany e GLS France. |
| Sei lettere | Per esempio `ABCDEF`. Stessa forma di GLS Germany. |

Copia il numero invece di riscriverlo: un numero di 12 cifre con l'ultima sbagliata, o uno di 11 a cui manca una cifra, non corrisponde più a GLS. Se l'e-mail del negozio nomina un altro corriere, come [BRT](carrier:brt), il numero è suo. Nessun numero? Leggi [dove si trova il codice tracking](guide:find-tracking-number).

## Tracciamento GLS in Svizzera: cosa vogliono dire gli stati

Per i pacchi diretti in Svizzera la pagina di tracking di GLS offre solo l'inglese, e GLS non pubblica un elenco degli stati per la Svizzera. Queste sono le diciture del tracking del gruppo, nell'ordine in cui le incontra un pacco:

| GLS scrive | Cosa vuol dire per te |
| --- | --- |
| `Preadvice`, `The parcel has not been handed over to GLS.` | Il mittente ha creato i dati dell'etichetta, ma GLS non ha ancora il pacco. |
| `The parcel was handed over to GLS.` | GLS ha il pacco. |
| `In transit`, `The parcel has left the parcel center` | Viaggia tra i centri pacchi di GLS. |
| `Customs Consignment via Customs Portal`, `The parcel has not been released by customs.` | È in dogana: per i pacchi GLS lo sdoganamento in Svizzera lo fa la Posta. |
| `The parcel was released by customs.` | Sdoganato, riparte. |
| `Final parcel center` | È all'ultimo centro pacchi: il passo successivo è la consegna. |
| `In delivery`, `The parcel is in delivery.` | In consegna, previsto in giornata. |
| `Not delivered` | Non consegnato, per esempio per un indirizzo sbagliato o un destinatario non trovato. GLS avvia una verifica dell'indirizzo. |
| `Stored` | Di nuovo al centro pacchi finale. |
| `The parcel is ready for collection at the ParcelShop.` | Ti aspetta in un ParcelShop o in un locker. |
| `The parcel has been delivered.`, `Delivered` | Consegnato. |

`Preadvice` vuol dire solo che il negozio ha mandato a GLS i dati dell'etichetta: se resta così per giorni, chiedi al mittente. GLS scrive `Delivered` anche per un pacco lasciato in un ParcelShop, che devi ancora ritirare. Nessuna pagina di GLS spiega `Customs Consignment via Customs Portal`: Peek lo considera una fase di dogana. I significati vengono dalle pagine d'aiuto di GLS in Germania e nei Paesi Bassi, che usano lo stesso tracking.

## Consegna GLS in Svizzera: la Posta e l'invito di ritiro

**Chi te lo porta.** Il partner di GLS per la Svizzera e il Liechtenstein è Swiss Post GLS, un'unità della Posta con sede a Basilea. La Posta sdogana i pacchi GLS in arrivo e li tratta nella sua rete, e avverte che per gli invii che riceve dalla rete GLS lo sdoganamento può scostarsi dalla procedura abituale.

**ParcelShop e locker.** Nessuna pagina di GLS indica un ParcelShop o un locker GLS in Svizzera. Swiss Post GLS offre il FlexDeliveryService di GLS (scegliere giorno, indirizzo o ParcelShop) solo a chi spedisce dalla Svizzera verso l'estero.

**Consegna mancata.** Su tentativi di consegna, avvisi di passaggio e consegne al sabato GLS non pubblica nulla per la Svizzera. Se la Posta ti lascia un invito di ritiro, ecco le regole che applica ai suoi invii (nessuna pagina le cita espressamente per i pacchi GLS):

- Ritira il pacco nel luogo indicato sull'invito, con l'invito stesso o con il codice QR nella Post-App: di norma entro sette giorni, 15 per gli invii dall'estero. Prolungare il termine è gratuito e non serve un account.
- Con il login a «I miei invii» puoi chiedere un secondo recapito allo stesso indirizzo (deposito del pacco o consegna a un vicino gratis, il sabato CHF 7.00) o farlo inoltrare a uno sportello automatico My Post 24, a un altro indirizzo in Svizzera o nel Liechtenstein, a una filiale o a un punto PickPost (CHF 8.00 dal lunedì al venerdì).
- Sempre con quel login, una procura singola gratuita permette a un'altra persona di ritirarlo per te.

## Pacco GLS in ritardo, fermo in dogana, perso o rotto

GLS l'ha scelto il mittente, che secondo le pagine GLS della Posta paga in ogni caso il trasporto: il tuo primo contatto è il negozio.

**Nessun aggiornamento.** Con `Preadvice` il pacco è ancora dal negozio; con uno stato di dogana aspetta lo sdoganamento della Posta. Negli altri casi leggi [perché il tracking non si aggiorna](guide:tracking-not-updating).

**Dazio e IVA.** Chi paga dipende dall'Incoterm GLS scelto dal mittente. Con l'Incoterm 20 sdoganamento, dazio e IVA sono a carico dell'importatore; con il 30 e il 40 si dividono tra mittente e destinatario, e tu paghi almeno l'IVA. Per i pacchi GLS la Posta applica le stesse tariffe di sdoganamento degli altri pacchi: le cifre sono nella guida [pacco fermo in dogana](guide:customs). Se non sei d'accordo con i costi di sdoganamento, la Posta consiglia di contattare il suo Servizio clienti internazionale, al +41 848 48 48 47. Per correggere regime doganale, indirizzo o tributi hai 60 giorni dalla data della decisione d'imposizione, termine fissato dall'Ufficio federale della dogana e della sicurezza dei confini (UDSC).

**Non consegnato.** Con `Not delivered` GLS verifica l'indirizzo: controlla con il negozio che quello dell'ordine sia giusto e completo.

**Perso o rotto.** GLS non pubblica per la Svizzera né una procedura di reclamo né termini o limiti di risarcimento: rivolgiti al negozio, che ha il contratto con GLS.

## GLS Svizzera contatti: telefono ed e-mail

GLS non ha un numero proprio per la Svizzera. La sua pagina contatti rimanda al sito della società GLS nazionale, che per la Svizzera e il Liechtenstein è Swiss Post GLS. Recapiti verificati il 10 ottobre 2026:

- **Telefono:** +41 848 48 48 47, la linea «Importazione» di Swiss Post GLS, CHF 0.08 al minuto da rete fissa svizzera, dal lunedì al venerdì 8.00–12.00 e 13.30–17.30. La Posta la indica come «GLS (pacchi)» sulle pagine per le aziende e per lo sdoganamento, non come linea per il tracking. Il +41 848 454 454 è per chi spedisce dalla Svizzera.
- **E-mail:** infogls@posta.ch in italiano, oppure infogls@post.ch, infogls@poste.ch o infogls@swisspost.ch.
- **Indirizzo:** Swiss Post GLS, Post-Passage 11, 4002 Basel.
- **Gruppo GLS:** un [modulo di contatto](https://gls-group.com/GROUP/en/contact-form/) in inglese.

> GLS mette in guardia da e-mail, SMS e siti falsi e da finti fattorini alla porta. Fuori dall'UE, GLS o un agente autorizzato possono davvero chiederti dazi e tasse per e-mail, SMS o lettera, ma GLS non ti chiede mai per e-mail di pagare la merce con carta di credito, pagamento online o bonifico. Non pagare un pacco in contrassegno che non aspettavi. Il sito principale di GLS è gls-group.com; segnala i falsi a security@gls-group.com.

## Seguire un pacco GLS con Peek

[Peek](/) accetta tutte e quattro le forme e chiede ai corrieri probabili. GLS Germany e GLS Switzerland conoscono gli stessi numeri, quindi conta il tuo paese: la regione del tuo iPhone o, sul web, il paese da cui ti colleghi. In Svizzera o nel Liechtenstein di solito vedi «GLS Switzerland ha questo pacco»; se il pacco va in Germania, premi «Cambia» e scegli GLS Germany. Altrove compare GLS Germany, che segue altrettanto bene i pacchi diretti in Svizzera, con l'NPA svizzero. Puoi incollare anche un link di tracking gls-group con /EU/ o /GROUP/: viene seguito con GLS Germany.

GLS mostra la cronologia delle scansioni solo con l'NPA di consegna, quindi il campo «Codice postale di consegna» è obbligatorio: quattro cifre (il campo propone 8000 come esempio), e finché è vuoto Peek non segue il pacco. L'NPA va a GLS e, se serve, a servizi di tracciamento; uno sbagliato fa sembrare il numero sconosciuto. Se l'hai già inserito su questo dispositivo, il campo te lo ripropone.

Poi vedi lo stato, la città e il paese di ogni scansione su una mappa, il giorno di consegna previsto, il peso e l'ora di consegna, senza il tuo nome, indirizzo, recapiti o firma. Gli stati restano nell'inglese di GLS; quello del ParcelShop compare come «Pronto per il ritiro». Gli orari delle scansioni sono letti come ora svizzera. GLS viene controllato di giorno circa una volta all'ora e di notte ogni una o due ore, anche il giorno di consegna, e dopo un controllo non riuscito si riprova dopo 4 ore. Puoi ricevere notifiche per ogni scansione, solo per i passaggi importanti o «Solo giorno di consegna».

Quando GLS indica che a consegnare è la Posta, la pagina del pacco mostra «Consegna con Swiss Post», e Peek può seguire il pacco sul tracking della Posta con lo stesso numero, appena la Posta mostra un avanzamento più recente. Peek non può contattare GLS o la Posta al posto tuo.

## Domande sul tracking GLS in Svizzera

### Che corriere è GLS?

È General Logistics Systems, un corriere internazionale con sede ad Amsterdam, fondato nel 1999 e parte di International Distributions Services (ex Royal Mail Group). In Svizzera e nel Liechtenstein il suo partner è Swiss Post GLS, e i pacchi li consegna la Posta.

### Quanto tempo ci mette GLS a consegnare in Svizzera?

GLS dà una cifra solo per i pacchi dall'Austria: 2 giorni di spedizione, sia per la Svizzera sia per il Liechtenstein. Per le altre provenienze vale la stima del negozio.

### Pacco GLS non consegnato: cosa fare?

Cerca nella bucalettere un invito di ritiro della Posta: indica dove ritirare il pacco. Se il tracking dice `Not delivered`, GLS sta verificando l'indirizzo, quindi scrivi al negozio.

### Quanto tempo ho per ritirare un pacco GLS?

Con un invito di ritiro della Posta, di norma sette giorni, 15 per gli invii dall'estero, e il termine si può prolungare gratis. Sono le regole della Posta per i suoi invii: nessuna pagina le indica espressamente per i pacchi GLS.

### GLS consegna il sabato?

Per la Svizzera né GLS né la Posta lo dicono. Dopo un invito di ritiro, però, la Posta offre per i suoi invii un secondo recapito anche di sabato, a CHF 7.00.

:::sources
- [La Posta: Invito di ritiro: ricezione di invii mancati](https://www.post.ch/it/ricezione/invito-di-ritiro) – termini di ritiro, «I miei invii», secondo recapito, inoltro, prezzi
- [La Posta: Swiss Post GLS](https://www.post.ch/it/soluzioni-commerciali/esportazione-importazione-e-sdoganamento/esportazione/swiss-post-gls) – telefono, orari, e-mail in italiano
- [La Posta: Costi dell'importazione](https://www.post.ch/it/soluzioni-commerciali/esportazione-importazione-e-sdoganamento/esportazione/swiss-post-gls/costi-dell-importazione) – Incoterm GLS, trasporto a carico del mittente, contestazioni, «GLS (pacchi)»
- [La Posta: Importazione](https://www.post.ch/it/soluzioni-commerciali/esportazione-importazione-e-sdoganamento/importazione) – pacchi importati GLS nella rete della Posta
- [La Posta: Sendungen aus dem Ausland – Importverzollung](https://www.post.ch/de/empfangen/importverzollung) – pacchi GLS, stesse tariffe, ripartizione con Incoterm 30 e 40
- [La Posta: GLS Zusatzleistungen](https://www.post.ch/de/geschaeftsloesungen/export-import-und-verzollung/export/swiss-post-gls/paketversand/gls-zusatzleistungen) – FlexDeliveryService solo per chi spedisce dalla Svizzera
- [GLS: Parcel Tracking](https://gls-group.com/EU/en/parcel-tracking) – numeri e Track ID, NPA, solo inglese
- [GLS: GLS partners, Switzerland](https://gls-group.com/GROUP/en/gls-partners/?partner=switzerland) – Swiss Post GLS, indirizzo
- [GLS: GLS partners, Liechtenstein](https://gls-group.com/GROUP/en/gls-partners/?partner=liechtenstein) – stesso partner
- [GLS: About us](https://gls-group.com/GROUP/en/about-us/) – società, sede, 1999
- [GLS: Contact](https://gls-group.com/GROUP/en/contact-form/) – modulo, numeri delle società nazionali
- [GLS: Security advice](https://gls-group.com/GROUP/en/security-advice/) – truffe, sito ufficiale
- [GLS Germany: Shipment status](https://www.gls-pakete.de/en/glossary-private/parcel-status) – significato degli stati
- [GLS Netherlands: Frequently asked questions recipients](https://gls-group.com/NL/en/faq-recipient/) – testi degli eventi
- [GLS Austria: Versand in die Schweiz](https://gls-group.com/AT/de/versand-in-die-schweiz/) – consegna della Posta
- [GLS Austria: Regellaufzeiten](https://gls-group.com/AT/de/regellaufzeiten/) – tempi di consegna dall'Austria
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – cosa legge Peek
:::
