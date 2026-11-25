---
title: Quando arriva il mio pacco? La stima di consegna di Peek IA
description: Peek IA è la stima di Peek su quando arriverà il tuo pacco, accanto alla data del corriere. Quando compare, quanto è affidabile e come disattivarla.
slug: quando-arriva-il-mio-pacco
picture: Pip, il pacco con la faccia, sbircia un telefono dove la scheda di un pacco mostra, sotto la data del corriere, una piccola scintilla e «Peek IA entro le 16:00».
published: 2026-11-24
updated: 2026-11-25
---

Quando [Peek](/) ha un indizio forte su quando arriverà il tuo pacco, Peek IA lo dice accanto alla data del corriere, o al suo posto quando il corriere non ne dà una: «probabilmente entro le 16:00», «probabilmente oggi o domani», «forse prima» o «oggi è improbabile». È una stima di Peek, mai una promessa del corriere, e la maggior parte dei pacchi non ne mostra nessuna.

È attiva di default, con o senza account: su peektracker.com, nell'app per iPhone, iPad e Mac e nell'[app per Android](guide:android-app), da Google Play, dal repository F-Droid di Peek o da GitHub.

:::steps
- Guarda la tua lista | Sotto la data del corriere sulla scheda di un pacco, o accanto allo stato in «Prossimo»: «✦ Peek IA» e qualche parola, come «entro le 16:00».
- Apri il pacco | Una piccola scheda dice per esempio «✦ Peek IA probabilmente entro le 16:00», con una riga che spiega perché.
- Tocca (i) | Il pulsante (i) («Info su Peek IA») apre il pannello «Peek IA»: la stima, con sotto quello che dice il corriere stesso, come «DPD dice oggi».
- Leggi i dettagli | Spesso i giorni o gli orari probabili, colorati in base alla loro probabilità; poi «Perché», «Affidabilità» e l'interruttore «Mostra Peek IA».
:::

Il widget «Pacchi» su [iPhone](guide:iphone-app) e Android la mostra dove c'è spazio, e gli [assistenti collegati](guide:ai-assistants) la ricevono come stima di Peek IA, non del corriere. Le [notifiche](guide:parcel-notifications), le e-mail di consegna, i passaporti degli amici e l'anteprima di un link condiviso non la includono. La [demo di Peek](https://peektracker.com/demo) ha pacchi di esempio con una stima.

## Quando mi arriva il pacco? Cosa può dirti Peek IA

Peek IA si pronuncia solo quando c'è uno dei cinque indizi qui sotto, ciascuno dei quali ci ha preso almeno 8 volte su 10 sui pacchi che Peek ha seguito.

| L'indizio | Peek IA dice | La sua riga di spiegazione, per esempio |
| --- | --- | --- |
| In consegna, nessun orario dal corriere | «probabilmente entro le 16:00» | «In consegna dalle 09:12. DPD non indica un orario.» |
| Il corriere dice oggi; nel pomeriggio non è ancora in consegna | «oggi è improbabile» | «DPD dice oggi, ma il pacco non è ancora in consegna.» |
| La data del corriere era ieri; scansionato nelle ultime 24 ore | «probabilmente oggi o domani» | «La data di DPD era ieri, ma il pacco è di nuovo in movimento.» |
| La data del corriere è tra qualche giorno; i pacchi simili di solito arrivano prima | «forse prima» | «DPD dice ven 27 nov. È in anticipo.» |
| Nessuna data dal corriere | «probabilmente entro gio 26 nov», «probabilmente questa settimana» e altre | «UPS non indica una data. È arrivato in dogana ieri.» |

DPD e UPS stanno per il tuo corriere. Su una scheda, «oggi è improbabile» si accorcia in «non oggi», o in «improbabile» accanto a una data del corriere che è oggi. Gli orari seguono la tua impostazione a 12 o 24 ore.

Ogni stima sparisce quando scade, anche da una pagina lasciata aperta: «oggi è improbabile» a mezzanotte, «probabilmente entro le 16:00» alle 16:00, «forse prima» alla data del corriere, e un giorno quando è passato.

### Perché il mio pacco non mostra nessuna stima?

Di solito perché Peek non ha un indizio forte. Peek IA tace anche:

- quando il pacco è consegnato, archiviato, in un punto di ritiro o in restituzione al mittente, o dopo un tentativo di consegna (vedi [mancata consegna](guide:missed-delivery));
- prima della sua prima vera scansione: un'etichetta da sola non basta;
- quando il corriere mette la consegna tra i suoi passaggi previsti, o indica per oggi un orario o una fascia non ancora passati;
- prima che il pacco sia in consegna, se Peek non l'ha controllato di recente (da 36 ore, tranne per «oggi è improbabile»);
- quando la data del corriere è passata da più di un giorno (vedi [quando preoccuparsi](guide:tracking-not-updating));
- per «forse prima» e le stime senza data del corriere, 45 giorni dopo la prima scansione, o 30 giorni dopo la più recente.

## La data di consegna del corriere è affidabile? «Forse prima» e «oggi è improbabile»

Non sempre, e Peek IA lo dice in due casi: «forse prima» quando i pacchi con scansioni simili a quelle del tuo di solito arrivano prima della data del corriere, e «oggi è improbabile» quando il corriere dice oggi ma nel pomeriggio il pacco non è ancora in consegna.

«Forse prima» può indicare il primo giorno probabile («forse prima, da domani», «forse prima, da gio 26 nov») e non compare mai quando il corriere dà un intervallo di giorni.

## Pacco in consegna ma non ancora arrivato: arriva ancora oggi?

Peek IA potrebbe dirtelo. «Probabilmente entro le 16:00» vuol dire che la maggior parte dei pacchi di quel corriere seguiti da Peek è arrivata entro quell'ora nel giorno di consegna; compare solo se il corriere non indica un orario e il pacco è partito prima. Tardi nella giornata, «oggi è improbabile» vuol dire che ne arrivano pochi così tardi: «Ancora in consegna a quest’ora. Con DPD, pochi arrivano così tardi.» Per alcuni corrieri non dice nulla mentre il pacco è in consegna, perché Peek non si fida delle loro scansioni «In consegna».

Gli orari abituali di ogni corriere, e perché un furgone può essere ancora in giro alle 20, sono in [a che ora consegna il corriere](guide:delivery-times); cosa significa lo stato, in [stati della spedizione](guide:tracking-statuses).

## Il corriere non dà una data di consegna: quanto ci vorrà?

Peek IA può dare un giorno o un periodo, quello di cui il suo modello è abbastanza sicuro: «probabilmente entro gio 26 nov», «probabilmente oggi o domani» (solo quando il corriere consegna domani), «probabilmente questa settimana» (entro domenica) o «probabilmente entro la fine della settimana prossima» (entro la domenica dopo).

Per i tempi abituali dalla Cina, vedi [tracciare un pacco dalla Cina](guide:tracking-from-china).

## Come prevede Peek IA i tempi di consegna, e quanto è affidabile?

Peek IA è un modello addestrato più tabelle di regole sul server di Peek, e mostra solo i tipi di stima che ci hanno preso almeno 8 volte su 10.

Il modello, un classificatore gradient boosting fatto di alberi decisionali, dà la probabilità che un pacco sia arrivato entro ciascuno dei prossimi giorni di consegna del corriere. Legge le scansioni e dove sono avvenute, il corriere, il servizio, il peso, il fuso orario e il paese di destinazione dichiarato, e se il pacco è stato cercato senza account. Le tabelle di regole contengono l'orario di consegna abituale di ogni corriere e quante volte ogni tipo di stima si è avverato.

Il server calcola la stima ogni volta che invia i dati del pacco al tuo telefono o al tuo browser, senza chiamare alcun servizio esterno; questi si limitano a mostrarla. Solo il server di Peek ha il modello: né il modello né il codice che lo addestra sono nel [codice open source di Peek](guide:developers). Non è un chatbot.

«Affidabilità» dice «Ci prende 8 volte su 10 con pacchi come questo.», o «Ci prende 8 volte su 10 a quest’ora.» per «oggi è improbabile». Il numero è 8 o 9: Peek IA non mostra nulla sotto 8 su 10, non dichiara mai più di 9 e arrotonda per difetto.

## Come si disattiva Peek IA?

Disattiva «Peek IA» in «Preferenze» nelle «Impostazioni» di Peek, oppure tocca (i) sulla scheda Peek IA di un pacco e disattiva «Mostra Peek IA».

:::steps
- Apri le Impostazioni | Sul sito, il pulsante dell'account (la tua iniziale), o senza account il pulsante a ingranaggio «Impostazioni». Nelle app, il pulsante Account in alto in «Consegne»; su Mac, la finestra «Impostazioni» di Peek, scheda «Generali».
- Trova Peek IA | «Peek IA» è l'ultima riga in «Preferenze», con il suggerimento «Quando Peek ha un indizio forte, ti dice quando pensa che arriverà il pacco.»
- Disattiva l'opzione | Peek IA sparisce dalle schede, da «Prossimo», dalle pagine dei pacchi e dai widget su quel dispositivo.
:::

Con un account, la scelta ti segue sugli altri dispositivi, e gli assistenti collegati smettono di ricevere la stima. Senza account, resta in quel browser o su quel dispositivo; [Usare Peek senza account](guide:account-or-guest) mette a confronto le due cose.

:::sources
- [Peek: Peek IA sul server](https://github.com/plhery/peek-delivery-tracker/blob/main/src/server/peekAi.ts) – i cinque indizi, 8 volte su 10, 36 ore, quando non dice nulla
- [Peek: le parole di Peek IA](https://github.com/plhery/peek-delivery-tracker/blob/main/src/lib/peekAi.ts) – le parole su schede e pagine, quando scade ogni stima, «Affidabilità»
- [Peek: la scheda e il pannello di Peek IA](https://github.com/plhery/peek-delivery-tracker/blob/main/src/components/PeekAi.tsx) – la scheda, il pannello della (i), «Mostra Peek IA»
- [Peek: il modello](https://github.com/plhery/peek-delivery-tracker/blob/main/src/server/peekAiModel.ts) – alberi decisionali, cosa legge, 45 e 30 giorni
- [Peek: architettura (in inglese)](https://github.com/plhery/peek-delivery-tracker/blob/main/docs/ARCHITECTURE.md) – come funziona Peek IA, dove non compare, la scelta dell'account
- [Peek: testi in italiano](https://github.com/plhery/peek-delivery-tracker/blob/main/shared/locales/it.json) – tutte le etichette di Peek citate qui
- [Peek: preferenze del sito](https://github.com/plhery/peek-delivery-tracker/blob/main/src/components/Preferences.tsx) – l'interruttore «Peek IA», che segue l'account
- [Peek: impostazioni per iPhone, iPad e Mac](https://github.com/plhery/peek-delivery-tracker/blob/main/ios/PeekDeliveryTracker/SettingsViews.swift) – «Peek IA» in «Preferenze», «Generali» su Mac
- [Peek: impostazioni Android](https://github.com/plhery/peek-delivery-tracker/blob/main/android/app/src/main/kotlin/com/plhery/peekdeliverytracker/ui/settings/SettingsSheet.kt) – «Peek IA» in «Preferenze»
- [Peek: gli strumenti degli assistenti](https://github.com/plhery/peek-delivery-tracker/blob/main/src/server/assistants/tools.ts) – la stima nella lista dei pacchi di un assistente
- [App Store: Peek](https://apps.apple.com/app/id6823615370) – Peek IA nelle note di versione dell'app
- [Peek: note di versione Android (in inglese)](https://github.com/plhery/peek-delivery-tracker/blob/main/fastlane/metadata/android/en-US/changelogs/21.txt) – Peek IA nell'app per Android
- [Peek: Peek per Android](https://peektracker.com/android) – l'app su Google Play, F-Droid e GitHub
- [Peek: demo](https://peektracker.com/demo) – pacchi di esempio con una stima
:::
