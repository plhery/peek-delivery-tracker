---
title: Tracciamento Nova Poshta: numeri, stati e filiali
description: Traccia un pacco Nova Poshta (Nova Post): codice EW, significato degli stati, ritiro in filiale o locker, giorni di giacenza e contatti.
slug: tracciamento-nova-poshta
published: 2026-11-22
updated: 2026-11-22
---

Nova Poshta, indicata come «Nova Post» sul proprio sito in inglese, è un corriere espresso ucraino che consegna presso filiali, armadietti e indirizzi in tutta l'Ucraina, con filiali anche in Polonia e in altri paesi europei. Traccia un pacco con il numero di lettera di vettura espressa (EW) sulla [pagina di tracciamento di novaposhta.ua](https://novaposhta.ua/en/tracking/), nella sua app oppure incollandolo nel campo qui sopra. Il codice si trova in cima alla lettera di vettura, nell'app e nell'avviso di arrivo.

## Numero di tracciamento Nova Poshta: l'EW

Nova Poshta chiama questo numero «express waybill», EW (ЕН o ТТН in ucraino). L'SMS o la notifica push che annuncia l'arrivo riporta il numero della filiale e l'EW.

Nova Poshta non pubblica lunghezze o formati ufficiali. I numeri riconosciuti dal catalogo di Peek hanno questo aspetto:

| Formato (inventato) | Che cos'è |
| --- | --- |
| `20404829173648` | 14 cifre che iniziano per 204, 206, 207, 208, 590 o 595 |
| `51482917364829` | 14 cifre che iniziano per 21 o 51 |
| `SHPL4829173648` | Numero internazionale Nova Post: SH o TN, codice paese di due lettere e 10 cifre, talvolta dopo RS |

Codici simili che appartengono ad altri corrieri:

- **Termina con `UA`**, come `RR482917367UA`: è un codice postale internazionale di [Ukrposhta](carrier:ukrposhta), le poste statali, non di Nova Poshta.
- **13 cifre**: formato nazionale di Ukrposhta, non un EW.
- **Termina con `G`**, come `KL4829173MN48291G`: un codice Meest.
- **Altri numeri di 14 cifre** possono essere di DPD, SEUR, BRT e altri: controlla quale corriere indica l'SMS o l'e-mail.

Approfondisci in [formati dei numeri di tracciamento](guide:tracking-number-formats). Se il sito risponde «We have not found a parcel with this number», controlla le cifre: per lettere di vettura più vecchie di 90 giorni il sito non mostra nulla.

## Gli stati di tracciamento Nova Poshta spiegati

novaposhta.ua mostra il tracciamento in ucraino o in inglese, dal movimento più recente al più vecchio, ciascuno con data, ora e città. Le cifre nei testi indicano il numero della filiale.

| Stato di Nova Poshta | Cosa significa per te |
| --- | --- |
| `Registered and being prepared` | Il mittente ha creato la lettera di vettura. Nova Poshta non ha ancora il pacco. |
| `Accepted at branch 12` | Il mittente lo ha consegnato in quella filiale. |
| `Customs clearance in progress` | La dogana lo sta controllando; potrebbero essere dovuti dazi (vedi sotto). |
| `Delivery location has been changed` | Il pacco è stato reindirizzato, da te o dal mittente. |
| `The recipient has changed the delivery time` | La consegna con corriere è stata spostata a un altro orario; il pacco è ancora in viaggio. |
| `The courier has left for the recipient` | È in consegna al tuo indirizzo. |
| `Arrived at the branch 12` | Pronto per il ritiro in quella filiale. |
| `Received at branch 12` | Lo hai ritirato. |
| `Delivered at the address` | Il corriere lo ha consegnato. |
| `Return` | È stato disposto il reso al mittente. |
| `Returned` | È tornato alla filiale del mittente. |

## Filiale, locker o corriere: come ritirare il pacco

**In filiale**, mostra il codice a barre dell'app oppure comunica l'EW e conferma via telefono o documento d'identità. Il documento serve se il valore dichiarato supera 30.000 ₴ o se paghi più di 5.000 ₴. Puoi aprire il pacco davanti all'operatore.

**Giacenza.** Dal 1° ottobre 2026 la filiale custodisce il pacco gratis per 5 giorni dal giorno successivo all'arrivo, poi applica una tariffa giornaliera: 15 ₴ fino a 2 kg, 25 ₴ fino a 10 kg, 35 ₴ fino a 30 kg. Dopo 30 giorni risulta non ritirato.

**Gli armadietti** accettano fino a 20 kg. Apri il tuo con «Open the locker» nell'app, con Bluetooth e posizione attivi, entro 5 giorni; poi il pacco passa alla filiale più vicina. I punti di ritiro in negozi, farmacie e stazioni di servizio tengono i pacchi fino a 5 giorni prima di trasferirli alla filiale più vicina.

**La consegna con corriere** aggiunge 60 ₴ per pacchi fino a 30 kg ed è attiva dalle 9:00 alle 21:00 nei giorni feriali, con orario speciale nel fine settimana. Persa? Riprogrammala o dirottala alla filiale più vicina; dopo tentativi falliti finisce comunque lì.

**Il reindirizzamento** verso un'altra filiale è gratuito finché il pacco non raggiunge la destinazione (60 ₴ verso un indirizzo); dopo l'arrivo costa come una nuova spedizione.

**In Polonia**, Nova Post consegna in filiale, in un Paczkomat [InPost](carrier:inpost), in un punto di ritiro o a domicilio. Il codice di apertura arriva via SMS ed e-mail. Il pacco attende nel Paczkomat 2 giorni di calendario, poi Nova Post lo sposta in una propria filiale e ti avvisa; per ritirare un pacco così trasferito, chiama il +48 573 504 069.

## Pacco Nova Poshta in ritardo, smarrito o danneggiato

Le pagine di Nova Poshta non spiegano cosa fare se il tracciamento si ferma; le FAQ polacche citano dati errati del destinatario e articoli vietati tra le cause di ritardo. Chiedi prima al mittente e consulta [tracciamento che non si aggiorna](guide:tracking-not-updating).

Per i pacchi in ingresso in Ucraina, Nova Poshta indica che il destinatario paga gli eventuali oneri e viene avvisato via SMS, Viber o e-mail: sopra i 150 € si applica un dazio del 10% sull'eccedenza più il 20% di IVA. Altri dettagli in [pacchi fermi in dogana](guide:customs).

Per smarrimento o danni in Ucraina, apri un reclamo nell'app o sulla pagina di tracciamento («Manage Parcel», poi «Submit a Report»), per telefono o in filiale, con codice fiscale o passaporto. Nova Poshta risponde entro 3 giorni lavorativi e rimborsa fino al valore dichiarato.

In Polonia il reclamo spetta al mittente; puoi reclamarlo solo se il pacco ti è stato consegnato o se il mittente ti cede il diritto. Le condizioni di Nova Post Polonia prevedono 12 mesi dalla spedizione (2 mesi per ammanco o danneggiamento di un pacco corriere).

## Servizio clienti e numero di telefono Nova Poshta

Recapiti riportati sui siti di Nova Poshta e Nova Post Polonia:

- **Ucraina, 24 ore su 24:** +38 098 450 0609, +38 050 450 0609, +38 093 450 0609 o +38 044 450 0609.
- **Contact centre:** 0 800 500 609, indicato nelle pagine FAQ e armadietti.
- **E-mail:** support_ua@novaposhta.ua, oltre a un bot Telegram di [novaposhta.ua](https://novaposhta.ua/).
- **Polonia:** +48 182 111 609, tutti i giorni dalle 8:00 alle 20:00 (ora polacca), oppure support@novapost.pl.

> Nova Poshta mette in guardia da false e-mail che sostengono che tu le debba denaro; non invia messaggi su «Reliable Purchase» né link a moduli che chiedono i dati della carta.

## Tracciare i pacchi Nova Poshta con Peek

Altri corrieri usano formati a 14 cifre, quindi [Peek](/) non indica mai Nova Poshta dal solo numero. Interroga invece i corrieri compatibili: Nova Poshta per prima se cerchi dall'Ucraina o dalla Moldavia o se il numero inizia con 595000, altrimenti altri corrieri, ma non Nova Poshta. Per un codice internazionale SH o TN (talvolta preceduto da RS), Nova Poshta è l'unico corriere interrogato. Se Nova Poshta lo trova, Peek mostra «Nova Poshta (Ukraine) ha questo pacco.». Altrimenti premi «Cambia» e scegli «Nova Poshta (Ukraine)». Funziona anche un link di tracciamento da novaposhta.ua o novapost.com. Copre le lettere di vettura ucraine, comprese quelle emesse nelle filiali Nova Post all'estero, e i codici internazionali SH e TN di Nova Post, ma non i riferimenti Nova Poshta Global.

Mostra i testi inglesi di Nova Poshta, la città di ogni scansione sulla mappa, l'arrivo previsto, il peso, le dimensioni per i pacchi singoli, città e paese di destinazione (esclusi i resi), la filiale o l'armadietto in cui attende e l'orario di consegna, scartando nomi, indirizzi completi e dati di pagamento. Gli orari vengono convertiti nel tuo fuso orario («Orari nel tuo fuso orario:»). Quando una spedizione verso l'estero indica il partner di consegna, [UPS](carrier:ups) o [GOFO Express](carrier:gofo), Peek può seguirla anche lì, adottando il ritmo di controllo del corriere che effettua la consegna.

Peek interroga direttamente Nova Poshta fino a ogni 10 minuti, o ogni 2 minuti dalle 6:00 (ora di Kyiv) del giorno in cui Nova Poshta scansiona il pacco in consegna, mentre il pacco è aperto in Peek o nel giorno di consegna con le notifiche attive; altrimenti ogni ora. Dopo due giorni senza novità (12 ore mentre il pacco mostra ancora «Etichetta creata» o «Preso in carico»), Peek controlla ogni ora. Per le notifiche puoi scegliere «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Dopo quattro giorni senza scansioni in transito, fuori da dogana o problemi segnalati, Peek mostra «Nessun aggiornamento da…», ricorda che «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a Nova Poshta (Ukraine).» e propone «Chiedi aiuto a Nova Poshta (Ukraine)».

## Domande frequenti sul tracciamento Nova Poshta

### Nova Poshta è affidabile?

Sì. Nova Poshta è stata fondata in Ucraina nel 2001, guida il gruppo NOVA e dichiara di consegnare circa 1,5 milioni di pacchi e spedizioni merci al giorno.

### Posso tracciare Nova Poshta in inglese?

Sì: la versione inglese di novaposhta.ua mostra ogni stato in inglese.

### Un'e-mail di consegna Nova Post può essere una truffa?

Sì, può esserlo. Nova Poshta precisa che le e-mail autentiche arrivano solo dai suoi domini ufficiali; non aprire link di mittenti sconosciuti.

### Per quanto tempo Nova Poshta tiene un pacco in filiale?

In Ucraina 5 giorni gratis dal giorno successivo all'arrivo, poi con tariffa giornaliera; dopo 30 giorni il pacco è considerato non ritirato.

:::sources
- [Nova Post: Track your parcel](https://novaposhta.ua/en/tracking/) – testi degli stati, messaggio non trovato, pulsante reclamo
- [Nova Post: FAQ](https://novaposhta.ua/en/more/common-questions) – numero EW, documento, promemoria giacenza, tentativi corriere, reclami, contact centre
- [Nova Post: Pick up at the branch](https://novaposhta.ua/en/receive/at-branch) – SMS di arrivo, ritiro, soglie documento, controllo
- [Nova Post: Parcel storage](https://novaposhta.ua/en/receive/additional-services/parcel-storage) – 5 giorni gratis, tariffe giornaliere, 30 giorni
- [Нова пошта: Нова пошта змінює термін безплатного зберігання відправлень](https://novaposhta.ua/news/nova-poshta-zminiuie-termin-bezplatnoho-zberihannia-posylok/) – passaggio da 7 a 5 giorni dal 1° ottobre 2026
- [Nova Post: Pick up at the parcel locker](https://novaposhta.ua/en/receive/at-parcel-locker) – peso massimo, app, 5 giorni
- [Nova Post: Receive at the pickup point](https://novaposhta.ua/en/receive/at-pickup-point) – punti di ritiro, giacenza
- [Nova Post: Receive at a certain address](https://novaposhta.ua/en/receive/at-address) – costo corriere, orari, consegna mancata
- [Nova Post: Forwarding](https://novaposhta.ua/en/receive/additional-services/redirection) – regole e costi di reindirizzamento
- [Nova Post: Customs clearance in Ukraine](https://novaposhta.ua/en/receive/international-delivery/customs-clearance) – chi paga, soglia 150 €, avvisi
- [Нова пошта: Правила надання послуг](https://static.novaposhta.ua/sitecard/misc/doc/Rules_for_providing_postal_services.pdf) – responsabilità
- [Нова пошта: home page](https://novaposhta.ua/) – telefoni nel footer, 24/7, e-mail, Telegram
- [Nova Post: Caution, cyber attack](https://novaposhta.ua/en/caution-cyber-attack) – false e-mail di pagamento
- [Нова пошта: Надійна покупка](https://novaposhta.ua/receive/additional-services/safe-purchase/) – avviso contro messaggi truffa
- [Nova Post Poland: Home](https://novapost.com/pl-pl/) – telefono, orari, e-mail
- [Nova Post Poland: Śledź przesyłkę](https://novapost.com/pl-pl/tracking/) – lingue, cause di ritardo
- [Nova Post Poland: Odbiór w urządzeniach Paczkomat](https://novapost.com/pl-pl/receive/at-parcel-locker/) – 2 giorni, telefono
- [Nova Post Poland: Regulamin](https://novapost.com/pl-pl/more/offer/) – 2 giorni in Paczkomat, reclami, scadenze
- [Nova Post Poland: O firmie](https://novapost.com/pl-pl/more/about) – fondazione, gruppo NOVA, volumi
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – dati letti da Peek e partner UPS e GOFO Express
:::
