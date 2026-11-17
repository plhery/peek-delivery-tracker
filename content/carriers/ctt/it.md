---
title: Tracking CTT Portugal: codici, stati, ritiro e dogana
description: Traccia un pacco CTT Portugal dall'Italia, dalla Svizzera o in Portogallo: codici PT, significato degli stati, tempi di giacenza, dogana e contatti.
slug: ctt-portugal-tracking
published: 2026-11-17
updated: 2026-11-17
---

CTT (CTT – Correios de Portugal) è il servizio postale nazionale portoghese, affiancato in Portogallo dal ramo espresso CTT Expresso. Puoi seguire una spedizione con «Seguir objeto» su [ctt.pt](https://www.ctt.pt/feapl_2/app/open/objectSearch/objectSearch.jspx?request_locale=pt) (disponibile in portoghese e in inglese), nell'app mobile App CTT oppure incollando il codice nel riquadro qui sopra. Il numero arriva dal mittente, via SMS o e-mail da CTT o su un avviso cartaceo lasciato nella cassetta delle lettere.

Attenzione a non confondere due società dello stesso gruppo: posta e pacchi CTT Expresso si tracciano su `ctt.pt`, mentre [CTT Express](carrier:ctt-express) — il corriere espresso spagnolo del gruppo, ex Tourline Express — ha una pagina di tracciamento separata solo in spagnolo su `cttexpress.com`.

## Codice di tracciamento CTT: come riconoscerlo

La maggior parte dei codici seguiti su `ctt.pt` segue lo standard postale internazionale di 13 caratteri: due lettere per il servizio scelto, nove cifre e due lettere per il Paese che ha generato il codice (`PT` per il Portogallo, anche quando clienti contrattuali di CTT spediscono da Spagna o Cina). Alcuni invii espressi iberici usano invece codici numerici di 22 o 25 cifre.

| Formato (inventato) | Che cos'è |
| --- | --- |
| `RR000000005PT` | Codice postale o CTT Expresso emesso in Portogallo (`RR`, `CA`, `DA`, `EA`…). La nona cifra è una cifra di controllo. |
| `RR000000005IT` | Stesso formato creato da un'altra posta nazionale: qui [Poste Italiane](carrier:poste-italiane) (`CH` per la [Posta Svizzera](carrier:swiss-post)). |
| `0012340012345678901234` | 22 cifre (`00`, quattro cifre, `00` e 14 cifre): spedizione iberica di [CTT Express](carrier:ctt-express), consultabile anche su `ctt.pt`. |
| `0012340012345678901234001` | 25 cifre: le stesse 22 cifre seguite da `001`. |

Inserisci il codice senza spazi: nella guida in inglese di CTT, `Item not found` significa che l'invio non è stato identificato nella rete CTT (oppure che il codice ha solo valore doganale). Quando un pacco postale `PT` è diretto in Italia o in Svizzera, la consegna finale passa rispettivamente a [Poste Italiane](carrier:poste-italiane) o alla [Posta Svizzera](carrier:swiss-post) con lo stesso codice. Nessun SMS o e-mail con un codice? Probabilmente la spedizione viaggia senza tracciamento; vedi [come trovare il numero di tracciamento](guide:find-tracking-number).

## Cosa significano gli stati di CTT

«Seguir objeto» è consultabile in portoghese e in inglese. Ecco le diciture portoghesi e internazionali più frequenti:

| Stato CTT | Cosa significa per te |
| --- | --- |
| `Aguarda entrada nos CTT` | CTT ha ricevuto i dati elettronici dal mittente, ma non ancora il pacco. |
| `Aceite` | Preso in carico da CTT o dalle poste del Paese d'origine: inizia il trasporto. |
| `Em exportação` | Verifica doganale nel Paese di partenza prima dell'uscita. |
| `International dispatch` | Giorno in cui l'invio ha lasciato il Paese d'origine. |
| `International reception` | Giorno di ingresso nel Paese di destinazione. |
| `Em importação` | In lavorazione doganale nel Paese di arrivo. |
| `Em trânsito` | In viaggio o smistamento tra centri operativi; può comparire più volte. |
| `Em entrega` | In consegna oggi con il portalettere o il corriere. |
| `Entrega não conseguida` | Consegna non riuscita; controlla cassetta postale, SMS o e-mail. |
| `No ponto de entrega` | Disponibile per il ritiro presso un punto di contatto o locker. |
| `Em espera` | In attesa di istruzioni o di un giorno di consegna concordato. |
| `Cancelado` | Spedizione annullata: la consegna non avrà luogo. |
| `Entregue` | Consegnato. |
| `Devolvido` | Restituito al mittente. |

Anche `Não entregue` indica un tentativo andato a vuoto. Per le spedizioni extra-UE dirette in Portogallo possono inoltre comparire `O envio foi selecionado para apresentação à Alfândega.` o `Foi enviada notificação ao destinatário. O envio está em desalfandegamento.` durante lo sdoganamento portoghese.

## Mancata consegna: avvisi, punti di ritiro e locker Locky

**In Italia o in Svizzera.** Per le spedizioni fuori dal Portogallo, CTT specifica che luoghi e termini di giacenza sono stabiliti esclusivamente dall'operatore postale del Paese di destinazione, quindi [Poste Italiane](carrier:poste-italiane) in Italia o la [Posta Svizzera](carrier:swiss-post) in Svizzera.

**In Portogallo.** Se non c'è nessuno all'indirizzo (la consegna a mano esclude i minorenni), l'esito dipende dal servizio acquistato e dalle istruzioni del mittente: deposito in un punto CTT (Loja CTT, Ponto CTT, locker Cacifo Locky o agente Payshop), nuovo tentativo, attesa in centro operativo o reso immediato. Un'*Encomenda Postal* senza opzione domicilio e un'*Encomenda Expresso Em Dois Dias* senza recapito scelto vanno direttamente al punto di ritiro. I termini si contano in giorni lavorativi dal giorno lavorativo successivo all'avviso cartaceo (SMS ed e-mail partono quando il pacco è già pronto e indicano la scadenza):

| Tipo di invio in Portogallo | Giorni lavorativi per il ritiro |
| --- | --- |
| Lettere e raccomandate (*Correio Registado*) | 6 (10 in Loja CTT per la *Cartão de Cidadão*) |
| Pacco postale nazionale (*Encomenda Postal Nacional*) | 10 |
| Pacco postale internazionale (`CA`–`CZ`) | 15 |
| Pacchi CTT Expresso (`DA`–`EV`, `EX`, `EE`) | 5 (10 per `CE`, `EP`, `EH`, `ES` ed Expresso dalla Spagna) |

La giacenza di un'*Encomenda Postal* si può prorogare fino a 30 giorni, quella di un'*Encomenda Expresso* fino a 5 giorni lavorativi se il mittente lo consente. Porta l'avviso (o comunica il codice allo sportello) insieme a un documento d'identità valido: carta d'identità, passaporto, permesso di soggiorno o patente con nome completo, foto e firma.

**Locker Locky:** riservati ai pacchi CTT Expresso e Nacex (esclusi posta, *Encomenda Postal* e contrassegno), conservano il pacco per **5 giorni**. Il PIN arriva via SMS o e-mail; dopo che il tracking segnala l'arrivo al punto, possono volerci fino a 48 ore perché il pacco diventi ritirabile e fino a 24 ore per ricevere il PIN.

**Modificare una consegna Expresso:** solo i codici che iniziano per `D` (*Para Amanhã* ed *Em Dois Dias*) consentono modifiche a pagamento su «Seguir objeto» o nell'App CTT (ad esempio nuova data entro 5 giorni lavorativi a 5,01 € più IVA; esito entro 2 giorni lavorativi), purché il pacco non sia già fermo in un punto di ritiro. Con [CTT Express](carrier:ctt-express) sono previsti fino a due tentativi dal lunedì al venerdì dalle 9:00 alle 19:00, 10 giorni di giacenza nei punti Collectt Express e 5 nei locker Locky.

## Pacco fermo, in dogana o rispedito al mittente

CTT ricorda che alcuni Paesi trasmettono in ritardo — o non trasmettono affatto — gli eventi internazionali. Se il pacco non avanza, contatta il venditore e leggi la guida sul [tracking che non si aggiorna](guide:tracking-not-updating).

**Dogana.** Tra Portogallo e Italia non ci sono formalità doganali (unione doganale UE). Verso la **Svizzera**, l'Ufficio federale della dogana e della sicurezza dei confini (UDSC) applica l'IVA svizzera (8,1 %, oppure 2,6 % per beni come libri o alimentari), ma non riscuote gli importi d'imposta fino a **5 CHF** — pari a una base di calcolo di **62 CHF** all'8,1 % o **193 CHF** al 2,6 % — ed esenta i regali tra privati fino a **100 CHF** (esclusi alcolici e tabacchi).

Per gli acquisti spediti **da fuori UE verso il Portogallo**, dal 1° luglio 2026 gli ordini fino a 150 € pagano nell'UE un dazio fisso di **3 €** (più IVA in Portogallo) per tipologia di prodotto, e dal 1° novembre 2026 si aggiunge una tassa doganale UE di gestione da **2 €** (più IVA) per tipologia di prodotto, qualunque sia il valore della merce (gli invii fino a 150 € privi dei codici identificativi del prodotto forniti dal venditore possono essere respinti). Se IVA e oneri non sono stati saldati al checkout, sul portale doganale di CTT paghi l'IVA portoghese più il servizio CTT: **7 € (+ IVA)** fino a 150 €, oppure **14 €** (più eventuali dazi *ad valorem*) oltre 150 € e fino a 1.000 €. Hai **20 giorni** dall'arrivo in Portogallo prima del reso definitivo; dettagli in [pacchi bloccati in dogana](guide:customs).

**Reclami e resi.** I reclami postali si presentano tramite modulo web, telefono o in Loja/Ponto CTT entro **un anno** (nazionali) o **sei mesi** (internazionali) dall'accettazione. Le condizioni generali di CTT Expresso assegnano al cliente mittente **8 giorni** dalla consegna per danni (con imballo originale o foto) e **30 giorni** per smarrimento o ritardo. Un reso avviato non si può annullare. I pacchi né consegnabili né restituibili finiscono al *Refugo Postal*, dove i pacchi postali ed Expresso restano recuperabili tramite l'assistenza clienti tra **20 giorni e 3 mesi** (fino a 5 giorni prima dell'asta).

## Assistenza clienti e numero di telefono CTT

Canali ufficiali verificati sulle pagine CTT il 17 novembre 2026:

- **Telefono CTT (privati):** `+351 21 047 16 16` dall'estero (al costo di una chiamata internazionale verso il Portogallo; `21 047 16 16` a tariffa fissa nazionale dal Portogallo), nei giorni lavorativi dalle 8:30 alle 19:30. Linea imprese: `+351 21 244 00 35` dall'estero (`707 200 115` in Portogallo), stessi orari. Non è indicata alcuna linea in italiano né in inglese.
- **Modulo di contatto:** su [ctt.pt](https://www.ctt.pt/ajuda/particulares/apoio-ao-cliente/contactos/formulario-de-contacto), con risposta dal lunedì al venerdì dalle 8:30 alle 19:30, oppure di persona in qualsiasi Loja o Ponto CTT in Portogallo.
- **CTT Express (Spagna):** `900 841 352` (gratuito in Spagna), `+34 916 602 200` dall'estero o `ccaweb@cttexpress.com`, dal lunedì al venerdì dalle 9:00 alle 19:00 (esclusi i festivi).

> CTT segnala frequenti SMS, e-mail e messaggi WhatsApp truffaldini su presunti dazi da pagare, civici mancanti o consegne sospese, oltre a domini falsi come `ctt-pt.org` o `cttportugues.com`. Tutti i link autentici di CTT iniziano con `www.ctt.pt`.

## Tracciare un pacco CTT con Peek

[Peek](/) riconosce subito i codici postali ed Expresso di CTT (`PT` con cifra di controllo valida) e i link di `ctt.pt` contenenti `objects=`. Se una cifra è errata (ad esempio `RR000000004PT`), la verifica fallisce e Peek non assegna alcun corriere: ricontrolla il codice o tocca «Cambia». Gli altri riferimenti CTT fuori dallo standard postale vengono letti tramite servizi universali di tracciamento, mentre i codici di 22 e 25 cifre passano a [CTT Express](carrier:ctt-express).

Peek mostra lo stato attuale, fino alle 20 scansioni più recenti posizionate in modo indicativo sulla mappa (centro operativo o punto di ritiro) e il codice numerico di stato di CTT; CTT non fornisce una data di consegna stimata. Gli orari portoghesi vengono letti sul fuso di Lisbona (o delle Azzorre dai CAP `9500`) e quelli delle filiali spagnole di CTT Express su Madrid o Canarie, convertiti poi nel fuso del tuo dispositivo (su peektracker.com e nell'app per iPhone compare in fondo alla cronologia: «Orari nel tuo fuso orario: …»). Se una spedizione `PT` è diretta in Italia o in Svizzera, Peek interroga anche [Poste Italiane](carrier:poste-italiane) o la [Posta Svizzera](carrier:swiss-post): finché la posta di destinazione registra solo il preavviso senza una scansione propria, la scheda mostra ad esempio «Annunciato a Poste Italiane, non ancora ricevuto», passando a «Consegna con Poste Italiane» appena compare una scansione altrettanto recente e mantenendo lo storico CTT (e viceversa verso «CTT Portugal» per i pacchi diretti in Portogallo).

Peek controlla CTT fino a ogni 10 minuti, o fino a ogni 2 minuti dalle 6:00 ora locale nel giorno in cui il pacco entra «In consegna» finché risponde il tracciamento diretto di CTT (ogni 15 minuti tramite servizi di tracciamento), e ogni ora dopo 48 ore senza novità o dopo 12 ore in «Etichetta creata» o «Preso in carico». Puoi scegliere le notifiche «Tutti gli aggiornamenti», «Solo importanti» o «Solo giorno di consegna».

## Domande frequenti sul tracking CTT

### Dove si trova il mio pacco CTT?

L'ultima riga su «Seguir objeto» o su Peek te lo dice: `Em entrega` indica la consegna in giornata, `No ponto de entrega` la giacenza in un punto di ritiro. Se un pacco `PT` viaggia verso l'Italia o la Svizzera, il tratto locale compare anche su [Poste Italiane](carrier:poste-italiane) o sulla [Posta Svizzera](carrier:swiss-post).

### CTT e CTT Express sono la stessa cosa?

Fanno parte dello stesso gruppo ma si tracciano separatamente: CTT gestisce posta e CTT Expresso in Portogallo su `ctt.pt`, mentre [CTT Express](carrier:ctt-express) è il corriere espresso spagnolo attivo in Spagna e Portogallo, tracciabile in spagnolo su `cttexpress.com`.

### Che cos'è un punto di ritiro CTT?

In Portogallo può essere un ufficio postale (*Loja CTT*), un negozio convenzionato (*Ponto CTT*), un locker automatico *Cacifo Locky* o un agente *Payshop*. Fuori dal Portogallo il ritiro avviene negli uffici o punti della posta nazionale di destinazione.

### Il servizio clienti CTT risponde in italiano o in inglese?

CTT non indica linee telefoniche in italiano né in inglese: il `+351 21 047 16 16` risponde nei giorni lavorativi dalle 8:30 alle 19:30 al costo di una chiamata internazionale verso il Portogallo, mentre il modulo su `ctt.pt` riceve richieste scritte dal lunedì al venerdì negli stessi orari.

:::sources
- [CTT: Results in Seguir objeto (inglese)](https://www.ctt.pt/ajuda/particulares/seguir-ou-alterar-entrega/seguir/resultados-que-podem-aparecer-na-pesquisa-do-seguir-objeto?language_id=1) – stati in inglese e `Item not found`
- [CTT: Resultados do Seguir objeto (portoghese)](https://www.ctt.pt/ajuda/particulares/seguir-ou-alterar-entrega/seguir/resultados-que-podem-aparecer-na-pesquisa-do-seguir-objeto) – significato degli stati in portoghese
- [CTT: Seguir objeto](https://www.ctt.pt/feapl_2/app/open/objectSearch/objectSearch.jspx?request_locale=pt) – ricerca spedizioni in portoghese e inglese
- [CTT: Como seguir uma entrega](https://www.ctt.pt/ajuda/particulares/seguir-ou-alterar-entrega/seguir/como-seguir-uma-entrega) – sito, App CTT e tracciamento internazionale
- [CTT: Encontrar o código de envio](https://www.ctt.pt/ajuda/empresas/seguir-ou-alterar-entrega/seguir/encontrar-o-codigo-de-envio) – codici `PT` e codici numerici di 22 e 25 cifre
- [CTT: Não tenho código de envio](https://www.ctt.pt/ajuda/particulares/seguir-ou-alterar-entrega/alterar/a-entrega-vem-a-caminho-mas-nao-tenho-codigo-de-envio-o-que-posso-fazer) – invii privi di codice
- [CTT: Entrega não conseguida](https://www.ctt.pt/ajuda/particulares/receber/problemas-na-entrega/entrega-nao-conseguida) – mancata consegna e resi non annullabili
- [CTT: O que precisa de levar consigo](https://www.ctt.pt/ajuda/particulares/receber/levantar-correspondencia-ou-encomendas-/o-que-precisa-de-levar-consigo) – documenti d'identità accettati al ritiro
- [CTT: Cacifos Locky](https://www.ctt.pt/ajuda/particulares/receber/cacifos-locky/como-levantar-uma-encomenda) – giacenza di 5 giorni, PIN e tempi fino a 48 ore
- [CTT: Prazo para levantar](https://www.ctt.pt/ajuda/particulares/receber/levantar-correspondencia-ou-encomendas-/prazo-para-levantar) – giorni lavorativi di giacenza e regole fuori dal Portogallo
- [CTT: Alterar uma entrega Expresso](https://www.ctt.pt/particulares/receber/seguir-ou-alterar-uma-entrega/alterar-uma-entrega-expresso) – codici `D` e costi di modifica (5,01 € + IVA)
- [CTT: Serviços cobrados](https://www.ctt.pt/ajuda/particulares/importacoes-e-desalfandegamento/taxas-e-servicos-cobrados/servicos-cobrados) – tariffe di sdoganamento CTT (7 € + IVA e 14 €)
- [CTT: Fim da isenção de direitos aduaneiros até 150 €](https://www.ctt.pt/particulares/receber/desalfandegar/fim-da-isencao-de-direitos-aduaneiros-em-compras-ate-150-euros-fora-da-uniao-europeia) – dazio di 3 € + IVA e tassa di 2 € + IVA per tipologia di prodotto (letto il 17 novembre 2026)
- [Commissione europea: Riforma doganale dell'UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_it) – dazio di 3 € dal 1° luglio 2026 e tassa di gestione di 2 € dal 1° novembre 2026
- [Commissione europea: Guidance on the €3 customs duty and Union handling fee (PDF, inglese)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – applicazione per tipologia di prodotto indipendentemente dal valore
- [UDSC: Ricevere lettere e pacchi](https://www.bazg.admin.ch/it/ricevere-lettere-e-pacchi) – IVA svizzera (8,1 % / 2,6 %) e franchigia d'imposta fino a 5 CHF (62 CHF / 193 CHF)
- [BAZG: Einfuhr in die Schweiz (tedesco)](https://www.bazg.admin.ch/bazg/de/home/information-private/interneteinkauf--post--und-kuriersendungen/einfuhr-in-die-schweiz.html) – regali tra privati esenti fino a 100 CHF
- [CTT: Prazo para desalfandegar](https://www.ctt.pt/ajuda/particulares/importacoes-e-desalfandegamento/prazo-de-desalfandegamento/prazo-para-desalfandegar-a-minha-encomenda) – termine di 20 giorni per sdoganare
- [CTT: Condições gerais de transporte de Encomendas Expresso](https://www.ctt.pt/transversais/condicoes-gerais-transporte-encomendas-expresso) – termini di reclamo Expresso (8 e 30 giorni)
- [CTT: Refugo Postal](https://www.ctt.pt/ajuda/particulares/receber/levantar-correspondencia-ou-encomendas-/recuperar-o-correio-que-esta-no-refugo-postal) – recupero pacchi tra 20 giorni e 3 mesi
- [CTT: Reclamações](https://www.ctt.pt/ajuda/particulares/apoio-ao-cliente/contactos/reclamacoes) – reclami postali (1 anno nazionale, 6 mesi internazionale)
- [CTT: Linhas de apoio](https://www.ctt.pt/ajuda/particulares/apoio-ao-cliente/contactos/linhas-de-apoio) – numeri e orari (letto il 17 novembre 2026)
- [CTT: Formulário de contacto](https://www.ctt.pt/ajuda/particulares/apoio-ao-cliente/contactos/formulario-de-contacto) – modulo di contatto (lunedì–venerdì, 8:30–19:30)
- [CTT: Alertas de phishing](https://www.ctt.pt/transversais/alertas-de-phishing) – avvisi su SMS, e-mail e siti falsi
- [CTT Express: Envíos a Portugal](https://www.cttexpress.com/envios/portugal) – due tentativi di consegna
- [CTT Express: Horarios de reparto](https://www.cttexpress.com/preguntas-frecuentes/horarios) – consegne lunedì–venerdì 9:00–19:00
- [CTT Express: Envío y recogida Collectt](https://www.cttexpress.com/red-collectt-express/envio-y-recogida) – 10 giorni nei punti Collectt, 5 giorni nei locker Locky
- [CTT Express: Cómo contactar](https://www.cttexpress.com/preguntas-frecuentes/como-contactar-con-ctt) – contatti in Spagna
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – riconoscimento codici, fusi orari e passaggi postali in Peek
:::
