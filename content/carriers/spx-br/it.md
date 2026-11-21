---
title: Tracciamento SPX Express Brasile: ordini Shopee e stati
description: Traccia un pacco SPX Express di un ordine Shopee in Brasile: dove trovare il codice BR, cosa significano gli stati in portoghese, Agência Shopee e rimborso.
slug: tracciamento-spx-express-brasile
published: 2026-11-21
updated: 2026-11-21
---

SPX Express (Shopee Xpress) è la rete logistica proprietaria di Shopee in **Brasile**. Ritira gli ordini presso i venditori e li consegna a domicilio oppure in un'*Agência Shopee*, un esercizio convenzionato dove ritirare il pacco. Shopee chiama questo servizio *Entrega Padrão* (consegna standard) e indica di seguire la spedizione nell'app Shopee in Brasile, dove si trovano l'ordine, la data stimata e gli eventuali rimborsi. È possibile controllare il codice anche su [spx.com.br](https://spx.com.br/track) o incollarlo nel riquadro qui sopra.

## Codice di tracciamento SPX Express: dove trovarlo e formato

Nell'app Shopee in Brasile apri `Eu` > `Minhas Compras`, seleziona l'ordine sotto `A Caminho` (alcuni articoli dell'assistenza Shopee riportano `A Receber`) e tocca `Status de Envio` per leggere ogni aggiornamento. Shopee inserisce lì automaticamente il codice di tracciamento appena disponibile, insieme alla data di consegna prevista.

Ecco i formati riconosciuti da Peek (con caratteri inventati):

| Formato | Che cos'è |
| --- | --- |
| `BR4829105736491` | SPX Express in Brasile: `BR`, 12 cifre e una cifra finale (15 caratteri). |
| `BR482910573649X` | SPX Express in Brasile: `BR`, 12 cifre e una lettera finale (15 caratteri). |
| `BR482910573649XSPXLM48291057` | SPX Express: lo stesso codice di 15 caratteri seguito da `SPXLM` e 8 cifre, come su alcune etichette marketplace. |
| `EA482910576BR` | Due lettere, nove cifre e `BR`: una spedizione postale dei Correios brasiliani, non di SPX. |

Il numero d'ordine (`ID do pedido`) non è un codice di tracciamento, ma l'assistenza clienti Shopee può chiederlo.

Non tutti gli ordini Shopee in Brasile viaggiano con SPX. Shopee distingue *Entrega Padrão* (Shopee Xpress), *Entrega Correios* (il cui codice funziona anche sul sito dei Correios) ed *Entrega do Vendedor*, in cui è il venditore stesso a consegnare e aggiornare lo stato. Per gli acquisti spediti dall'estero verso il Brasile, Shopee indica i Correios — con un codice locale nell'app — oppure il partner Anjun Courier; tuttavia anche il tracciamento di SPX mostra righe dedicate per alcuni pacchi internazionali (vedi sotto). Per altri codici consulta [formati dei numeri di tracciamento](guide:tracking-number-formats) e [come trovare il numero di tracciamento](guide:find-tracking-number).

## Cosa significano gli stati di tracciamento di SPX Express

SPX scrive gli aggiornamenti in portoghese (con qualche dicitura in inglese); alcune righe terminano con la località dopo i due punti.

| Riga di SPX | Cosa significa per il pacco | Peek mostra |
| --- | --- | --- |
| `Pedido em preparação` | Il venditore sta preparando l'ordine; SPX non ha ancora il pacco. | «Etichetta creata» |
| `Pedido postado …` | Il venditore ha lasciato il pacco in un punto di accettazione in Brasile (spx.com.br aggiunge la città); SPX passa a ritirarlo lì. | «Preso in carico» |
| `Seu pedido foi coletado` | SPX ha ritirato il pacco dal venditore. | «Preso in carico» |
| `Seu pedido chegou ao centro logístico: …` | Arrivato in un centro logistico SPX in Brasile. | «In transito» |
| `Seu pedido saiu do centro logístico: …` | Partito da un centro logistico verso la tappa successiva. | «In transito» |
| `O pedido está em separação no centro logístico, novas atualizações em até 1 dia.` | In smistamento; SPX preannuncia nuovi aggiornamenti entro un giorno. | «In transito» |
| `Pedido em trânsito` | In trasferimento tra centri. Tra Stati brasiliani o città distanti, spiega Shopee, il pacco resta anche giorni sul camion di linea e la riga successiva compare solo allo scarico e alla scansione nel centro di distribuzione dello Stato di destinazione. | «In transito» |
| `Pedido em rota de entrega para seu endereço, aguarde pelas próximas horas.` | In uscita per la consegna al tuo indirizzo nelle prossime ore. | «In consegna» |
| `Pedido deve ser retirado na agência: …` | Il pacco attende il ritiro da parte del destinatario nell'agenzia indicata. | «Pronto per il ritiro» |
| `Houve um imprevisto durante a entrega do seu pedido …`, `Um imprevisto ocorreu …`, `Insucesso na entrega por endereço incorreto…` | La consegna non è andata a buon fine in giornata; SPX riprogramma un nuovo tentativo all'indirizzo. | «Tentativo di consegna» |
| `Pedido entregue` | Consegnato. | «Consegnato» |
| `Pedido será devolvido ao vendedor`, `Pedido será devolvido ao vendedor. Por favor, aguarde.` | Il pacco sta tornando al venditore; la variante più lunga invita solo ad attendere. | «Restituito al mittente» |
| `Pedido devolvido` | Rientrato al venditore. | «Restituito al mittente» |
| `Pedido extraviado. Estorno será processado automaticamente.` | Pacco dichiarato smarrito; SPX segnala che il rimborso viene elaborato in automatico. | «Problema segnalato» |
| `Pedido descartado` | Dopo una mancata consegna il pacco è stato smaltito: né consegnato né restituito al venditore. Contatta Shopee. | «Problema segnalato» |

Alcuni pacchi ricevono invece le righe della flotta di consegna SPX che iniziano con `Seu pacote`, tra cui `Seu pacote foi recebido pelo centro de triagem` (al centro di smistamento), `Seu pacote foi recebido pelo centro de distribuição` (al centro di distribuzione di zona), `Seu pacote está sendo entregue pelo entregador` (con l'hub all'inizio: in consegna) e `Seu pacote foi entregue` (consegnato). Peek mostra per queste righe le stesse fasi, come «In consegna».

**Pacchi dall'estero verso il Brasile.** Le righe che iniziano con `[China]` sono scansioni nel Paese di partenza, ad esempio `[China]O pedido foi liberado na alfândega de exportação` a sdoganamento d'esportazione concluso. Seguono `Pedido chegou no Brasil` (arrivato in Brasile), `Pedido está na alfândega de importação` (alla dogana d'importazione brasiliana), `Pedido em inspeção na alfândega de importação` (in ispezione doganale) e, in inglese, `Import Customs Cleared` (svincolato dalla dogana). Peek mostra «In dogana» durante i due passaggi doganali e torna a «In transito» dopo `Import Customs Cleared`.

Al contrario, `O pacote não pôde ser importado, será retornado ou confiscado pela alfândega` indica che la dogana brasiliana ha rifiutato l'importazione e il pacco verrà rispedito indietro o confiscato; Peek mostra «Problema segnalato». Rivolgiti a Shopee seguendo la normale procedura di rimborso.

## Consegna SPX in Brasile: tentativi, parola chiave e Agência Shopee

Le regole seguenti riguardano le consegne Shopee in **Brasile**:

**Tempi.** Né chi compra né chi vende può fissare giorno o fascia oraria di consegna. Shopee indica da 1 a 25 giorni lavorativi dal ritiro per gli ordini da venditori in Brasile, e di norma da 30 a 50 giorni di calendario per gli ordini internazionali. La data prevista compare nella pagina dell'ordine.

**Tentativi.** I partner logistici di Shopee effettuano 3 tentativi di consegna prima del reso al venditore, con rimborso automatico in caso di mancata consegna definitiva. L'indirizzo può essere modificato una sola volta prima della conferma del pagamento; dopo quel momento Shopee consiglia di lasciar esaurire i tentativi e ricevere il rimborso. Vedi anche [mancata consegna](guide:missed-delivery).

**Parola chiave (*palavra-chave*).** Alcuni ordini in Brasile hanno una password casuale da comunicare al corriere alla porta; solo chi la conosce può ritirare il pacco. Si trova nella scheda dell'ordine e arriva via e-mail e notifica Shopee. Se sei fuori casa, puoi lasciarla a una persona di fiducia come un vicino o il portiere dello stabile (*porteiro*). Non comunicarla mai prima di avere il pacco in mano e rifiuta consegne intestate a un altro nome o indirizzo.

**Ritiro in Agência Shopee (`Retire Fácil`).** Dove attivo in Brasile, si sceglie al pagamento in `Opção de Envio` > `Ver mais` > `Retire Fácil`; una volta confermato non si può passare alla consegna a domicilio. All'arrivo del pacco, Shopee invia un PIN via WhatsApp, e-mail o nell'app, dove è indicato anche l'ultimo giorno utile per il ritiro. Al banco servono PIN e numero di tracciamento; chiunque abbia entrambi può ritirare al posto tuo. La giacenza dura 7 giorni lavorativi prima che SPX riprenda il pacco. Se un'agenzia è temporaneamente indisponibile, Shopee può dirottare la consegna sull'indirizzo predefinito. La [mappa dei punti SPX](https://spx.com.br/service-point) mostra le agenzie selezionando `Retirar um pacote`.

## Acquisti dall'estero verso il Brasile: Remessa Conforme e imposte

In Brasile la Receita Federal annovera Shopee tra i siti certificati nel programma *Remessa Conforme* (PRC). Su un sito certificato, il dazio d'importazione e l'ICMS vengono pagati già al checkout. La Receita Federal raccomanda di controllare che le imposte compaiano nella schermata di riepilogo: se mancano, verranno addebitate all'arrivo in Brasile senza l'aliquota ridotta.

Aliquote indicate dalla Receita Federal in Brasile per le dichiarazioni d'importazione registrate dal 12 maggio 2026:

- **Dazio d'importazione (*Imposto de Importação*)** su sito certificato PRC per spedizioni destinate a persone fisiche: 0 % fino a 50 US$ di valore doganale (somma di merce, trasporto e assicurazione); oltre 50 US$, 60 % con uno sconto pari al controvalore di 30 US$ sull'imposta dovuta.
- **ICMS** (imposta statale brasiliana): attualmente dal 17 % al 20 % a seconda dello Stato brasiliano, sia su siti certificati sia non certificati.

Shopee avverte che un CPF (codice fiscale brasiliano) irregolare può bloccare l'acquisto internazionale e che le imposte d'importazione non sono rimborsabili. Se la dogana brasiliana trattiene un ordine affidato ad Anjun Courier, Shopee richiede ricevuta di pagamento e prova del prezzo d'acquisto, copia di un documento brasiliano (RG o CNH) e il modulo di prova del valore RCV della Receita Federal. Per un pacco gestito dai Correios, controlla eventuali pendenze sul loro tracciamento. La Receita Federal ricorda di non inviare bollettini (*boletos*), non telefonare e non mandare e-mail per riscuotere queste imposte. Maggiori dettagli in [pacco fermo in dogana](guide:customs).

## Pacco SPX senza aggiornamenti, in ritardo o non ricevuto

**Fermo da giorni.** Shopee spiega che in Brasile è frequente: il tracciamento cambia solo quando il pacco viene scansionato in un nuovo centro, perciò le lunghe tratte su gomma tra Stati, i fine settimana e le code negli hub lasciano lo stato invariato. Anche un percorso che sembra allontanarsi rientra nella pianificazione della rete, e spesso più passaggi compaiono tutti insieme quando il pacco entra in consegna. Approfondisci in [perché il tracciamento non si aggiorna](guide:tracking-not-updating).

**Codice non funzionante.** Il pacco potrebbe non essere ancora stato ritirato, oppure il codice è errato o non ancora registrato a sistema. Chiedi al venditore nella chat di Shopee; Shopee precisa che non significa che la spedizione sia persa.

**In ritardo.** Attendi che sia trascorsa la data di consegna prevista. Da quel momento puoi chiedere il rimborso nell'app: `Eu` > `A Caminho` > l'ordine > `Pedido de Reembolso`, motivo `Não recebi o pedido`. Il venditore ha 2 giorni per rispondere, altrimenti il rimborso parte in automatico. Secondo Shopee, la maggior parte degli ordini in ritardo arriva entro 5 giorni dalla data stimata.

**Risulta consegnato ma non c'è.** Chiedi a vicini, familiari e portineria (*porteiro*), poi apri la richiesta di rimborso nell'app: per *Entrega Padrão*, entro 7 giorni dalla consegna. Shopee può avviare una verifica presso il corriere (*acareação*), che può contattarti via WhatsApp o e-mail o rimandare l'autista all'indirizzo. Leggi anche [consegnato ma non ricevuto](guide:delivered-not-received).

**Rispedito indietro.** Dopo `Pedido será devolvido ao vendedor`, il pacco sta rientrando dal venditore. In caso di mancata consegna Shopee rimborsa automaticamente; vedi [rispedito al mittente](guide:returned-to-sender).

## Contatti SPX Express: assistenza tramite Shopee

Shopee specifica che non è possibile contattare direttamente il corriere per tracciamento o consegna: se ne occupa Shopee tramite la *Garantia Shopee* in Brasile:

- **Chat Shopee:** nell'app apri `Eu` > `Central de Ajuda`, scorri fino ad `Atendimento ao Cliente` e scegli `Chat Shopee`. Attiva dal lunedì al venerdì dalle 8:00 alle 22:00 e nel fine settimana dalle 8:00 alle 20:00 (ora di Brasilia).
- **Modulo di richiesta:** sempre accessibile dalla [pagina degli orari del servizio clienti Shopee](https://help.shopee.com.br/portal/4/article/141507), tenendo a portata di mano l'`ID do pedido`. (Nel piè di pagina di `spx.com.br`, rivolto ad autisti partner e agenzie, è indicato il canale WhatsApp `(11) 2770-1438`, dal lunedì al sabato esclusi i festivi dalle 8:00 alle 20:00, ora di Brasilia.)
- **Comportamento scorretto del corriere** (ad esempio richieste di denaro extra): segnalalo tramite `Chat Shopee`.
- **Problema ancora aperto in Brasile:** Shopee Brasil aderisce a [consumidor.gov.br](https://www.consumidor.gov.br/pages/principal/), il portale pubblico brasiliano dei reclami dove le aziende partecipanti si impegnano a rispondere entro 10 giorni (serve un account `gov.br` di livello argento o oro).

> Shopee contatta gli utenti solo tramite canali ufficiali come l'app, profili social verificati o indirizzi e-mail che terminano in `shopee.com` (mentre il portale pubblico di tracciamento SPX è `spx.com.br`), e la spedizione si paga sempre nell'app. Per riconoscere gli SMS truffa leggi [SMS truffa sulle consegne](guide:delivery-scam-texts).

## Tracciare un pacco SPX Express con Peek

[Peek](/) riconosce subito SPX Express Brazil da un codice `BR` di 12 cifre seguite da una cifra o lettera finale (anche con suffisso `SPXLM…`), mostrando «Corriere rilevato» e il tasto «Cambia». Anche un link di `spx.com.br` (`/track?` o `/detail/` con il codice `BR`) assegna all'istante il pacco a SPX, perfino se incollato da un SMS senza `https://`. Peek interroga SPX solo con codici `BR`, non con l'ID ordine di Shopee.

Peek legge lo stesso tracciamento di `spx.com.br` e mostra lo stato, le righe di SPX (in portoghese e alcune in inglese), la località registrata nei centri logistici, hub, porti o agenzie di ritiro e l'ora esatta della consegna convertita nel fuso orario del tuo dispositivo — su `peektracker.com` e nelle app per iPhone e Android la cronologia si chiude con «Orari nel tuo fuso orario: …». Mostra anche passaggi nascosti dalla pagina pubblica di SPX, come `Seu pedido chegou ao último centro logístico` (arrivo all'ultimo centro logistico) o un ritiro non riuscito presso il venditore («Etichetta creata»); se il ritiro fallito viene dopo `Pedido postado …`, il pacco resta al punto di accettazione («Preso in carico»), a meno che la riga non precisi che il mittente non aveva preparato l'ordine («Etichetta creata»). SPX non fornisce data di consegna prevista, peso o corriere partner. Su un link condiviso, la consegna mostra solo `Delivered` senza il nome di chi ha ritirato il pacco. Parola chiave, PIN dell'*Agência Shopee* e rimborsi restano nell'app Shopee.

Finché SPX non conosce ancora il numero, Peek mostra «In attesa di SPX Express Brazil» e continua a verificare. Se dopo due giorni SPX non riconosce ancora il codice e nessun'altra fonte ha risposto, Peek mostra «Dopo due giorni il corriere non riconosce ancora questo numero. Controllalo, scegli un altro corriere o elimina il pacco.» e passa a un controllo giornaliero e, trascorsa una settimana dall'aggiunta, settimanale. Se il pacco è in viaggio e passano quattro giorni senza nuove scansioni, Peek propone «Chiedi aiuto a SPX Express Brazil», che apre la pagina degli orari dell'assistenza Shopee.

Mentre tieni il pacco aperto sullo schermo, o nel giorno di consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti — e fino a ogni 2 minuti dalle 6:00 del mattino (ora locale) nel giorno in cui SPX registra l'uscita in consegna, finché risponde il tracciamento diretto di SPX (tramite i soli servizi di tracciamento esterni: ogni ora, ogni 3 ore dopo una giornata senza novità e fino a ogni 15 minuti il giorno della consegna a pacco aperto o con notifiche attive) —; negli altri casi, e dopo due giorni senza aggiornamenti (o 12 ore su «Etichetta creata» o «Preso in carico»), controlla ogni ora. Puoi ricevere avvisi per «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek non può contattare né SPX né Shopee al posto tuo.

## Domande frequenti sul tracciamento SPX Express Brasile

### SPX Express fa parte di Shopee?

Sì: Shopee definisce SPX Express la propria logistica ufficiale in Brasile. Dietro `spx.com.br` opera SPX Express Brasil Ltda., con oltre 100 hub logistici e 11 centri di distribuzione in Brasile.

### Come faccio a tracciare un pacco SPX Express?

Nell'app Shopee in Brasile (`Eu` > `Minhas Compras` > l'ordine > `Status de Envio`), incollando il codice `BR` in Peek oppure tramite la ricerca su `spx.com.br`.

### Cosa vuol dire Pedido em rota de entrega para seu endereço?

Il pacco è uscito in consegna verso il tuo indirizzo in Brasile e SPX invita ad attendere nelle prossime ore. Se l'ordine prevede una parola chiave (*palavra-chave*), tienila pronta per l'autista.

### Quanto impiega SPX Express a consegnare in Brasile?

Shopee indica da 1 a 25 giorni lavorativi dal ritiro per ordini da venditori in Brasile e generalmente da 30 a 50 giorni di calendario per gli ordini dall'estero. La data stimata del tuo ordine è indicata nella pagina dell'ordine.

### Posso contattare direttamente SPX Express?

Shopee risponde di no per tracciamento e consegna degli ordini: l'assistenza passa da `Chat Shopee` nell'app, dal lunedì al venerdì dalle 8:00 alle 22:00 e nei fine settimana dalle 8:00 alle 20:00 (ora di Brasilia), oppure dal modulo online.

:::sources
- [SPX Express: Home](https://spx.com.br/br) – logistica integrata di Shopee in Brasile, oltre 100 hub e 11 centri di distribuzione, SPX Express Brasil Ltda. e WhatsApp partner/agenzie
- [SPX Express: Tracking](https://spx.com.br/track) – pagina pubblica di ricerca spedizioni SPX in Brasile
- [SPX Express: Sobre Nós](https://spx.com.br/br/help-center/about-us.html) – tracciamento nell'app Shopee e copertura nazionale
- [SPX Express: Encontre uma Agência Shopee](https://spx.com.br/service-point) – mappa delle agenzie e scelta Retirar um pacote
- [Shopee: O que é a SPX Express?](https://help.shopee.com.br/portal/4/article/197188) – logistica proprietaria e ufficiale di Shopee in Brasile
- [Shopee: O que é Shopee Xpress (SPX)?](https://help.shopee.com.br/portal/4/article/76163) – ritiro, centri di distribuzione e corrieri partner
- [Shopee: Posso entrar em contato com os parceiros logísticos da Shopee?](https://help.shopee.com.br/portal/4/article/76264) – modalità di spedizione, nessun contatto diretto col corriere e 3 tentativi
- [Shopee: Como faço para verificar o status do meu pedido?](https://help.shopee.com.br/portal/4/article/76323) – codici dei Correios
- [Shopee: Quais são as opções de envio disponíveis na Shopee?](https://help.shopee.com.br/portal/4/article/76335) – condivisione automatica del codice di tracciamento
- [Shopee: Como eu acho a data prevista de entrega para o meu pedido?](https://help.shopee.com.br/portal/4/article/81887) – data stimata e schermata Status de Envio
- [Shopee: Como encontro o ID do pedido da minha compra?](https://help.shopee.com.br/portal/4/article/76194) – codice ordine ID do pedido
- [Shopee: O que é logística do vendedor?](https://help.shopee.com.br/portal/4/article/132039) – consegna curata dal venditore
- [Shopee: Como minha encomenda do exterior será entregue quando chegar ao Brasil?](https://help.shopee.com.br/portal/4/article/76363) – consegna tramite Correios per ordini dall'estero
- [Shopee: Por que meu pedido está parado na alfândega do Brasil?](https://help.shopee.com.br/portal/4/article/153683) – Correios o Anjun Courier, documenti RG/CNH e modulo RCV
- [Shopee: Quando receberei meu pedido?](https://help.shopee.com.br/portal/4/article/76349) – 1–25 giorni lavorativi nazionali e 30–50 giorni di calendario internazionali
- [Shopee: Posso agendar a data e hora de entrega do meu pedido?](https://help.shopee.com.br/portal/4/article/76333) – nessuna prenotazione oraria, tentativi e rimborso automatico
- [Shopee: Posso alterar meu endereço de entrega depois que meu pedido for feito?](https://help.shopee.com.br/portal/4/article/76279) – modifica unica dell'indirizzo prima della conferma pagamento
- [Shopee: O que é a palavra-chave no meu pedido?](https://help.shopee.com.br/portal/4/article/127204) – funzionamento della parola chiave alla consegna
- [Shopee: O que fazer quando não estiver em casa para informar a palavra-chave?](https://help.shopee.com.br/portal/4/article/127206) – delega a una persona di fiducia
- [Shopee: O que fazer se minha palavra-chave não funcionar corretamente?](https://help.shopee.com.br/portal/4/article/127205) – non dare la password prima della consegna e rifiutare pacchi errati
- [Shopee: Retirada de pacotes na Agência Shopee](https://help.shopee.com.br/portal/4/article/164642) – opzione Retire Fácil, avvisi, PIN e scadenza ritiro
- [Shopee: Sobre a Agência Shopee](https://help.shopee.com.br/portal/4/article/164639) – ritiro con PIN e numero di tracciamento, 7 giorni lavorativi e reindirizzamento
- [Shopee: Meu pedido está sem atualização no rastreamento](https://help.shopee.com.br/portal/4/article/223973) – giorni sul camion tra Stati e arrivo entro 5 giorni dalla data prevista
- [Shopee: Por que meu pedido está parado ou mudou de rota?](https://help.shopee.com.br/portal/4/article/159363) – variazioni di percorso tra centri
- [Shopee: Por que meu código de rastreio não está funcionando?](https://help.shopee.com.br/portal/4/article/76369) – motivi per cui il codice non mostra ancora dati
- [Shopee: O que devo fazer se não receber meu pedido até a data prevista de entrega?](https://help.shopee.com.br/portal/4/article/76321) – richiesta di rimborso e 2 giorni per la risposta del venditore
- [Shopee: O que devo fazer se não receber um pedido, mas o status está como Entregue?](https://help.shopee.com.br/portal/4/article/76207) – controlli e indagine acareação
- [Shopee: Como faço para solicitar devolução/reembolso?](https://help.shopee.com.br/portal/4/article/76204) – 7 giorni dalla consegna per Entrega Padrão
- [Shopee: Como entro em contato com o Atendimento ao Cliente da Shopee?](https://help.shopee.com.br/portal/4/article/78354) – accesso a Chat Shopee nell'app
- [Shopee: Qual horário do Atendimento ao Cliente da Shopee?](https://help.shopee.com.br/portal/4/article/141507) – orari di Brasilia e modulo richieste
- [Shopee: O entregador do meu pedido teve uma má conduta](https://help.shopee.com.br/portal/4/article/224511) – segnalazione del corriere in chat
- [Shopee: Como faço para detectar um golpe?](https://help.shopee.com.br/portal/4/article/76330) – canali ufficiali shopee.com e pagamento della spedizione solo nell'app
- [Shopee: Por que não consigo concluir minha compra internacional?](https://help.shopee.com.br/portal/4/article/141561) – blocco per CPF irregolare
- [Shopee: Como solicitar devolução/reembolso ou realizar o cancelamento da minha compra internacional?](https://help.shopee.com.br/portal/4/article/134867) – rimborsi internazionali e imposte d'importazione non rimborsabili
- [Receita Federal: Tributação](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/topicos/tributacao) – aliquote PRC dal 12 maggio 2026, ICMS dal 17 % al 20 % e avviso contro falsi boletos
- [Receita Federal: Em que momento vou pagar os impostos?](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/preciso-pagar-impostos-nas-compras-internacionais/em-que-momento-pagarei-o-imposto) – pagamento delle imposte al momento dell'acquisto
- [Receita Federal: Empresas certificadas no Programa Remessa Conforme](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/empresas-certificadas-no-programa-remessa-conforme-prc) – Shopee tra le aziende certificate PRC
- [Consumidor.gov.br: Início](https://www.consumidor.gov.br/pages/principal/) – risposta entro 10 giorni e livello argento o oro dell'account gov.br
- [Consumidor.gov.br: Empresas participantes](https://www.consumidor.gov.br/pages/principal/empresas-participantes) – adesione di Shopee Brasil
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – riconoscimento dei codici BR e link spx.com.br, stati e flotta SPX, passaggi nascosti e privacy
:::
