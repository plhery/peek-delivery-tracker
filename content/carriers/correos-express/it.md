---
title: Tracciamento Correos Express: numero, stati e contatti
description: Traccia un pacco Correos Express da Spagna o Portogallo: codice a 16 o 23 cifre, significato degli stati in spagnolo, ritiro Punto Correos e contatti.
slug: correos-express-tracciamento
published: 2026-11-17
updated: 2026-11-17
---

Correos Express (in passato Chronoexprés, ragione sociale *Correos Express Paquetería Urgente, S.A., S.M.E.*) è il corriere espresso del gruppo spagnolo Correos. Consegna in tutta la Spagna e in Portogallo e spedisce in Italia, Europa e nel mondo con i suoi servizi internazionali; non va confusa con Correos, l'operatore postale pubblico spagnolo. Per seguire il pacco inserisci il codice nella [pagina di tracciamento spagnola di Correos Express](https://s.correosexpress.com/) oppure incollalo qui sopra. Se ti manca il codice, chiedilo al venditore che ha registrato la spedizione.

## Correos o Correos Express: quale corriere ha il tuo pacco?

Correos gestisce il servizio postale universale spagnolo, mentre Correos Express cura le consegne espresse. Condividono uffici postali e punti *Punto Correos*, ma usano portali e codici diversi:

- **Solo cifre (16 o 23 cifre):** codice Correos Express (il formato a 16 cifre è condiviso anche con altri corrieri, vedi sotto). Incollalo nel riquadro qui sopra.
- **Lettere e cifre insieme:** spedizione Correos (poste spagnole), con codici da 13 caratteri terminanti in `ES`, `PR` + 15 cifre + `C`, oppure 16 o 23 caratteri che iniziano e finiscono per lettera. Usa il [portale in inglese di Correos](https://www.correos.es/es/en/tools/tracker/items).

## Numero di tracciamento Correos Express: codici a 16 e 23 cifre

Correos Express non pubblica la struttura dei codici e chiede solo il *número de envío*. Peek riconosce due formati numerici chiusi da cifra di controllo GS1 (esempi inventati):

| Codice di esempio | A chi appartiene e come lo gestisce Peek |
| --- | --- |
| `3230 0000 0000 0000` | **16 cifre** (controllo GS1 valido, non iniziante con sei zeri): Correos Express, ma anche DHL eCommerce, Canada Post, TNT ed [Evri](carrier:evri). Peek interroga tutti e cinque i corrieri al primo controllo. |
| `32300000000000001000007` | **23 cifre** (controllo GS1 valido): esclusivo di Correos Express, salvo `000` nelle posizioni 15–17 (Australia Post). |
| `RR100000003ES` | **Lettere e cifre** nel [formato postale internazionale](guide:tracking-number-formats): Correos, non Correos Express. |
| `0100/00000005` | **4 cifre, barra, 8 cifre:** [NACEX](carrier:nacex), altro corriere espresso iberico. |

Incolla **solo il numero** (spazi, punti o trattini vengono rimossi), non un link `s.correosexpress.com` né l'intera e-mail: Peek non sempre lo trova in un link o in un testo. Una cifra errata invalida il controllo GS1 ed esclude Correos Express dai candidati automatici a 16 cifre. Correos Express non indica il mittente dei propri SMS; vedi [come trovare il numero di tracciamento](guide:find-tracking-number).

Se appare *«Lo sentimos, no se ha encontrado ningún envío con el número indicado»* («nessuna spedizione trovata con il numero indicato»), verifica numero e corriere e attendi qualche ora per gli invii appena creati. Dopo 24–48 ore senza esito, contatta il mittente.

## Stati Correos Express: significato delle diciture in spagnolo

Il tracciamento esiste solo in spagnolo e nella [ricerca portoghese](https://s.correosexpress.com/SeguimientoSinCP/search-pt). Peek interroga il portale spagnolo e traduce questi stati:

| Stato Correos Express | Significato per la tua spedizione |
| --- | --- |
| `INFORMADO` | Spedizione registrata dal mittente, non ancora entrata negli impianti di Correos Express. |
| `ADMITIDO` | Pacco preso in carico e partito dalla filiale di origine (`ADMITIDO EN OFICINA DE CORREOS` se consegnato alle poste). |
| `EN RUTA A LOCALIDAD DE DESTINO` | In viaggio verso la filiale locale di recapito; di norma è attivo *Gestionar entrega*. |
| `EN DESTINO` | «A destinazione»: arrivato nella filiale di distribuzione locale. |
| `EN REPARTO` | In consegna con il corriere; opzioni di consegna flessibile non più modificabili. |
| `Su envío no ha podido ser entregado` | «La tua spedizione non ha potuto essere consegnata»: tentativo fallito. |
| `NUEVO REPARTO` | «Nuova consegna»: programmato un nuovo passaggio. |
| `Cambio de destino` | «Cambio di destinazione»: il mittente ha chiesto la consegna a un nuovo indirizzo. |
| `ESTACIONADO` | «In giacenza»: secondo le FAQ portoghesi, recapito non riuscito e pacco fermo nei locali Correos Express. |
| `EN ALMACÉN` / `EN GESTIÓN` | «In magazzino» / «In gestione»: fermo in filiale per gestire un imprevisto. |
| `ENTREGADO EN PUNTO DE CONVENIENCIA` | Consegnato al *Punto Correos*, ma **non ancora pronto** per il tuo ritiro. |
| `DISPONIBLE EN PUNTO DE CONVENIENCIA` | Pronto al ritiro nel punto convenzionato presentando il PIN. |
| `ENTREGADO` | Consegnato a te o a un delegato. |
| `DEVUELTO` | Restituito al mittente (anche per scadenza della giacenza al punto di ritiro). |

**Attenzione:** `ENTREGADO EN PUNTO DE CONVENIENCIA` indica solo lo scarico del corriere al negozio. Aspetta `DISPONIBLE EN PUNTO DE CONVENIENCIA` e il PIN prima di uscire. La guida spagnola definisce solo `INFORMADO`, `ADMITIDO`, `EN RUTA A LOCALIDAD DE DESTINO`, `EN REPARTO` ed `ENTREGADO` (`ESTACIONADO` è nelle FAQ portoghesi); gli altri stati seguono la traduzione letterale e la mappatura di Peek (vedi [cosa significano gli stati di tracciamento](guide:tracking-statuses)).

## Spedizioni verso l'Italia, modifiche e ritiro Punto Correos

**Dalla Spagna all'Italia, alla Svizzera e al mondo:** Correos Express offre *Internacional Estándar* (servizio monocollo porta a porta in Europa, tempi indicativi di 2–3 giorni lavorativi verso i principali Paesi UE) e *Internacional Express* (via aerea verso oltre 180 Paesi, 1–2 giorni lavorativi indicativi in Europa secondo destinazione, fino a due tentativi e tracciamento online). Sabati, domeniche e festività dei Paesi attraversati sono esclusi.

**In Spagna:** prima di `EN REPARTO`, se il servizio e il mittente lo consentono, **Gestionar entrega** permette di deviare il pacco a un *Punto Correos*, cambiare indirizzo o data. *Paq 24* e *Paq 14* includono fino a due tentativi gratuiti; dopo un passaggio a vuoto, [Solicita nueva entrega](https://s.correosexpress.com/SeguimientoSinCP/saveNewDelivery) consente di scegliere giorno (mattina o pomeriggio), ufficio Correos o punto di ritiro, e *Paq e-Commerce* può essere lasciato nel *Punto Correos* più vicino con disponibilità. In contrassegno (*contra reembolso*) Correos Express raccomanda l'importo esatto in contanti.

**In Portogallo:** dopo un'assenza il secondo tentativo è automatico e una notifica propone nuova data, altro indirizzo nello stesso distretto o *Ponto Correos*. Dopo **3 giorni lavorativi** senza reclamo dall'avviso, il pacco torna al mittente.

**Giacenza e PIN:**

- **Punto Correos (Spagna):** **7 giorni solari** dall'arrivo prima del reso automatico. Presenta il PIN ricevuto via SMS/e-mail o recuperalo con **Visualizar PIN** sul tracciamento dopo autenticazione.
- **Ufficio postale Correos (*Entrega en oficina de Correos*, Spagna):** su *Paq 24* e *Baleares Express* (escluso contrassegno), fino a **15 giorni solari** e tre SMS (arrivo, 5° e 10° giorno).
- **Portogallo (*Ponto Correos* e locker):** fino a **7 giorni** nei punti o **3 giorni** nei locker *CitiBox* e *CityPaq*; ritira chi presenta il PIN monouso.

## Dogana, territori speciali e truffe via SMS (*smishing*)

Tra Spagna peninsulare (o Baleari) e Italia i pacchi circolano nell'area IVA UE senza dogana. Formalità e tributi si applicano invece oltre un confine fiscale o doganale:

- **Svizzera:** IVA all'importazione dell'**8,1%** (**2,6%** ridotta). Secondo l'UDSC, tributi e dazi **sotto 5 CHF** non vengono riscossi (fino a 62 CHF all'8,1% o 193 CHF al 2,6%); oltre tale soglia si pagano IVA, eventuali dazi sul peso e spese del vettore (come [La Posta Svizzera](carrier:swiss-post)); vedi [dogana e dazi di importazione](guide:customs).
- **Canarie, Ceuta, Melilla, Andorra, Gibilterra, Azzorre, Madera e Paesi extra-UE:** Le spedizioni verso Canarie, Andorra, Ceuta, Melilla o Gibilterra richiedono fattura commerciale o proforma (più *declaración jurada* firmata per le Canarie) e possono comportare imposte e costi doganali esclusi dalla tariffa; anche la via aerea per Azzorre e Madera richiede documenti doganali, così come il Regno Unito da fine 2020 (*Internacional Express* cura lo sdoganamento extra-UE).

> **Attenzione ai falsi SMS (*smishing*):** nella pagina *Alerta de seguridad* Correos Express dichiara che **non chiede mai coordinate bancarie o credenziali del sito** e **non gestisce numerazioni a sovrapprezzo 807**; in caso di dubbio usa `www.correosexpress.es` o chiama il `900 122 333`. Nel marzo 2022 INCIBE ha segnalato SMS fraudolenti a nome Correos e Correos Express che simulavano oneri doganali o spese di consegna con link di pagamento falsi. Un SMS a nome di Correos Express che dice che il pacco non è stato consegnato per spese non pagate e ti chiede di aprire un link è una truffa: non aprirlo. Il contrassegno si paga al corriere alla consegna. Verifica il codice su `s.correosexpress.com` e, se hai inserito dati della carta su un sito sospetto, contatta subito la banca.

## Pacco in ritardo, non ricevuto o danneggiato

Il contratto lega Correos Express al mittente ed è ammesso un solo reclamo per spedizione; muoviti quindi con il venditore:

- **Nessun aggiornamento:** dopo **24–48 ore** senza nuovi eventi contatta il mittente; vedi [tracciamento fermo che non si aggiorna](guide:tracking-not-updating).
- **Tentativo segnato mentre eri in casa:** verifica i dettagli sul tracciamento (accesso o indirizzo incompleto) e riprogramma la consegna.
- **Stato `ENTREGADO` ma pacco assente:** in Spagna controlla i dettagli sul portale e avvisa il negozio; in Portogallo scrivi anche a `destinatarioscep@correosexpress.com`. Vedi [pacco segnato consegnato ma non ricevuto](guide:delivered-not-received).
- **Danni o ammanchi:** annota riserve scritte alla consegna se il danno è visibile. Per danni occulti valgono **7 giorni solari** in Spagna (e **21 giorni** per ritardo), oppure nell'internazionale **7 giorni su strada** e **14 giorni per via aerea** (in Portogallo **8 giorni** nelle condizioni generali e foto al mittente entro **7 giorni** secondo le FAQ).
- **Rifiuto o `DEVUELTO`:** Correos Express avvisa il mittente e attende istruzioni. Negli acquisti UE *Your Europe* ricorda che il venditore risponde fino alla consegna fisica (entro **30 giorni** salvo diverso accordo).

## Contatti e assistenza Correos Express

Assistenza e siti esistono solo in spagnolo e portoghese:

- **Spagna:** `900 122 333` (orari non pubblicati; gratuito in Spagna secondo la CNMC) e `913 277 020` (indicato sul tracciamento), oltre al [modulo del servizio clienti](https://www.correosexpress.es/es/atencion-al-cliente/contacto/atencion-al-cliente) e a `@Cex_responde` su X.
- **Portogallo:** `229 762 810` (tariffa fissa nazionale), lun–ven 08:30–19:00, ed e-mail destinatari `destinatarioscep@correosexpress.com`.

## Tracciare i pacchi Correos Express con Peek

Con un codice a 16 cifre [Peek](/) interroga insieme i cinque corrieri compatibili; un 23 cifre valido punta invece subito a Correos Express (esclusa la serie `000` di Australia Post).

Peek controlla fino a ogni 10 minuti, e fino a ogni 2 minuti dalle 06:00 locali nel giorno «In consegna» finché risponde il tracciamento diretto di Correos Express (tramite servizi di tracciamento, fino a ogni 15 minuti). Dopo due giorni senza novità — o dopo 12 ore su «Etichetta creata» o «Preso in carico» — controlla ogni ora. Puoi attivare avvisi per «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek mostra stato in italiano, consegna stimata in transito e ogni scansione su mappa. Peek esclude prova di consegna, recapiti e commenti di consegna e non contatta Correos Express per te.

## Domande frequenti sul tracciamento Correos Express

### Correos e Correos Express sono la stessa cosa?

No. Correos gestisce il servizio postale universale spagnolo; Correos Express (*Correos Express Paquetería Urgente, S.A., S.M.E.*) è la società del gruppo per i pacchi espressi. Condividono i *Punto Correos*, ma i codici di sole 16 o 23 cifre si tracciano su Correos Express, quelli alfanumerici come `RR100000003ES` su Correos.

### In quanto tempo e a che ora consegna Correos Express?

Nella penisola iberica dichiara 24 ore (*Paq 24*), tra Spagna e Portogallo continentale 24–48 ore e verso Ceuta, Melilla, Andorra e Gibilterra il secondo giorno lavorativo (opzione sabato *Entrega en sábados* su *Paq 24* peninsulare). *Paq 10* e *Paq 14* consegnano entro le 10:00 o le 14:00; in Europa conta indicativamente 2–3 giorni lavorativi (*Internacional Estándar*) o 1–2 giorni lavorativi (*Internacional Express*). L'ora stimata sul sito si basa sullo storico del tuo CAP e può variare.

### Perché leggo `ENTREGADO EN PUNTO DE CONVENIENCIA` ma non ho ancora il PIN?

Perché quel messaggio indica solo lo scarico del corriere al punto di ritiro. Attendi `DISPONIBLE EN PUNTO DE CONVENIENCIA`: riceverai allora il PIN via SMS/e-mail (o tramite **Visualizar PIN**) e avrai 7 giorni solari in Spagna per il ritiro.

### Posso contattare Correos Express in italiano o in inglese?

No. Portali, guide e assistenza sono solo in spagnolo e portoghese. Dall'Italia o dalla Svizzera conviene rivolgersi direttamente al servizio clienti del negozio online.

:::sources
- [Correos Express: Seguimiento de envíos](https://www.correosexpress.es/es/atencion-al-cliente/seguimiento-envio) – stati, attesa 24–48 h, fascia oraria
- [Correos Express: Gestiona tu entrega](https://www.correosexpress.es/es/atencion-al-cliente/seguimiento-envio/gestiona-tu-entrega) – Gestionar entrega, Paq e-Commerce
- [Correos Express: Punto Correos](https://www.correosexpress.es/es/atencion-al-cliente/seguimiento-envio/recogida-en-punto-correos) – PIN, Visualizar PIN, 7 giorni
- [Correos Express: Otras consultas](https://www.correosexpress.es/es/atencion-al-cliente/seguimiento-envio/otras-consultas-entrega) – contrassegno, contatti
- [Correos Express: Sigue tu envío](https://s.correosexpress.com/) – portale spagnolo, errore, 913 277 020
- [Correos Express: Solicita nueva entrega](https://s.correosexpress.com/SeguimientoSinCP/saveNewDelivery) – nuova consegna, ufficio, punto
- [Correos Express: Atención al Cliente](https://www.correosexpress.es/es/atencion-al-cliente/contacto/atencion-al-cliente) – modulo online
- [Correos Express: Términos y condiciones](https://www.correosexpress.es/es/atencion-al-cliente/tyc-transporte) – termini reclamo, rifiuto
- [Correos Express: Aviso Legal](https://www.correosexpress.es/es/legales/aviso-legal) – ragione sociale
- [Correos Express: Alerta de seguridad](https://www.correosexpress.es/es/legales/alerta-de-seguridad) – avviso anti-frode, 900 122 333
- [Correos Express: Home](https://www.correosexpress.es/) – Paq 10, Paq 14, Paq 24
- [Correos Express: Quiénes somos](https://www.correosexpress.es/es/sobre-correos-express/quienes-somos) – differenza con Correos
- [Correos Express: España y Portugal](https://www.correosexpress.es/es/atencion-al-cliente/envios-en-espana-y-portugal) – Portogallo, territori speciali, dogana
- [Correos Express: Envíos internacionales](https://www.correosexpress.es/es/atencion-al-cliente/envios-internacionales1) – Internacional Estándar, Regno Unito
- [Correos Express: Internacional Express](https://www.correosexpress.es/es/particulares/enviar/internacionales/internacional-express) – 180 Paesi, 1–2 giorni
- [Correos Express: Internacional Express Empresas](https://www.correosexpress.es/es/empresas/productos/internacional-express) – due tentativi, dogana extra-UE
- [Correos Express: Paq 24](https://www.correosexpress.es/es/particulares/enviar/peninsulares/paq-24) – due tentativi, sabato
- [Correos Express: Paq 14](https://www.correosexpress.es/es/particulares/enviar/peninsulares/paq-14) – entro le 14:00, due tentativi
- [Correos Express: Entrega en oficina](https://clientes.correosexpress.com/web/correosexpress/entrega-en-oficina-de-correos) – 15 giorni, 3 SMS
- [Correos Express: Rete Punto Correos](https://www.correosexpress.es/es/sobre-correos-express/sala-de-prensa/2025/grupo-correos-lanza-su-red-de-puntos-de-conveniencia-y-ampliara-) – rete del gruppo
- [Correos: Tracker](https://www.correos.es/es/en/tools/tracker/items) – tracciamento Correos
- [Correos Express PT: Siga seu envio](https://s.correosexpress.com/SeguimientoSinCP/search-pt) – portale portoghese
- [Correos Express PT: FAQ](https://www.correosexpress.pt/pt/apoio-ao-cliente/perguntas-frequentes) – ESTACIONADO, secondo tentativo, danni
- [Correos Express PT: Apoio ao Cliente](https://www.correosexpress.pt/pt/apoio-ao-cliente/contacto/apoio-cliente) – 229 762 810, orari, e-mail
- [Correos Express PT: Termos](https://www.correosexpress.pt/pt/apoio-ao-cliente/tec-transporte) – 3 giorni lavorativi, 8 giorni, PIN
- [Correos Express PT: Paq Ponto](https://www.correosexpress.pt/pt/empresa/enviar/peninsulares/paq-ponto) – 7 giorni punto, 3 giorni locker
- [CNMC: Numeri 800 e 900](https://blog.cnmc.es/2019/04/24/llamadas-a-numeros-800-y-900-gratis-para-el-consumidor-y-quien-paga/) – gratuità dei 900
- [INCIBE: Avviso smishing](https://www.incibe.es/ciudadania/avisos/en-circulacion-sms-fraudulentos-que-suplantan-correos-y-otras-empresas-de) – falsi SMS
- [Your Europe: Consegna](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_it.htm) – 30 giorni, rischio venditore
- [Your Europe: IVA UE](https://europa.eu/youreurope/citizens/consumers/shopping/vat/index_it.htm) – area IVA UE
- [UDSC: Lettere e pacchi](https://www.bazg.admin.ch/it/ricevere-lettere-e-pacchi) – IVA svizzera, franchigia 5 CHF
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – codici GS1 e lettura stati
:::
