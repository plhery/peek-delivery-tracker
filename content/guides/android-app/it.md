---
title: Peek per Android su Google Play, con il widget «Pacchi»
description: Peek per Android è su Google Play, e anche nel repository F-Droid di Peek e su GitHub. Senza account. Aggiungi il widget, condividi i link con Peek.
slug: app-tracking-pacchi-android
picture: Un telefono Android con il widget «Pacchi» nella schermata Home, e Pip, il pacco con la faccia, che sbircia da sopra il telefono.
published: 2026-11-19
updated: 2026-11-25
---

Peek per Android è su [Google Play](https://play.google.com/store/apps/details?id=com.plhery.peekdeliverytracker), e anche nel repository F-Droid di Peek e su GitHub. Richiede Android 12 o successivo ma nessun account, e aggiunge un widget «Pacchi» alla schermata Home e «Aggiungi a Peek» al menu di condivisione.

Segue i pacchi di oltre 4.500 corrieri: incolla un codice tracking, il link di un corriere o un'intera e-mail di spedizione, e [Peek](/) trova il corriere e mostra il viaggio su una mappa. È gratuito, senza acquisti in-app e senza pubblicità. Per iPhone, iPad o Mac, vedi [Peek per iPhone, iPad e Mac](guide:iphone-app).

## Peek è su Google Play?

Sì. Sul tuo telefono Android, apri [Peek su Google Play](https://play.google.com/store/apps/details?id=com.plhery.peekdeliverytracker) e installalo. Anche il pulsante «Installa Peek» di [peektracker.com/android](https://peektracker.com/android), «Peek per Android», porta lì, e la stessa pagina spiega come installarlo senza Google Play.

### Come installo Peek senza Google Play?

Aggiungi a mano il repository di Peek all'app F-Droid, oppure scarica l'app da GitHub. Peek non è nel catalogo principale di F-Droid.

:::steps
- Copia l'indirizzo | Su [peektracker.com/android](https://peektracker.com/android), sotto «Senza Google Play», tocca «Copia l’indirizzo».
- Aggiungi il repository | Nell'app F-Droid, vai in **Impostazioni** › **Repository** › **+**, poi incolla l'indirizzo per intero.
- Installa Peek | Tocca il nuovo repository per vederne le app, poi installa «Peek». F-Droid non installa nulla finché non lo scegli.
:::

Oppure tocca «Apri GitHub» nella stessa pagina: nella versione più recente delle [release di Peek su GitHub](https://github.com/plhery/peek-delivery-tracker/releases), tocca il file .apk, poi lascia che il browser lo installi. Lì le release di Peek sono contrassegnate come «Pre-release».

Questa versione è firmata da Peek, non da Google, quindi non può aggiornare una copia di Google Play, né il contrario: se hai Peek da Google Play, o una copia precedente dal repository o da GitHub, disinstallala prima. Gli aggiornamenti arrivano dal repository F-Droid di Peek.

### Che differenza c'è tra la versione Google Play e quella F-Droid?

| Cosa | Versione Google Play | Versione F-Droid e GitHub |
| --- | --- | --- |
| Servizi di Google | Firebase, ML Kit, Play Integrity | Nessuno |
| Scanner di codici a barre | Sì | No, e nessun permesso per la fotocamera |
| Notifiche | Tramite i servizi Google Play | Tramite un'app UnifiedPush come ntfy, oppure Peek controlla circa ogni 15 minuti |
| Firmata e aggiornata da | Google, tramite Google Play | Peek, tramite il suo repository F-Droid |

Per le notifiche di ciascuna versione, vedi [le notifiche di consegna](guide:parcel-notifications).

## Quale versione di Android serve a Peek?

Android 12 o successivo, in entrambe le versioni. Un dispositivo senza fotocamera posteriore può comunque installarlo, solo senza lo scanner.

Parla dieci lingue: inglese, tedesco, francese, italiano, spagnolo, portoghese, portoghese brasiliano, polacco, russo e cinese semplificato. Scegline una in «Impostazioni» › «Preferenze» › «Lingua» («Impostazioni» si apre con il pulsante dell'account in alto in «Consegne») o nell'impostazione della lingua per app di Android. Se la scegli nelle impostazioni di Peek, «Aggiungi a Peek», il nome del widget nel selettore e la scorciatoia «Aggiungi un pacco» restano nella lingua del telefono; le parole del widget stesso seguono quella di Peek.

## Posso usare Peek su Android senza un account?

Sì. Incolla un codice tracking nella prima schermata e Peek lo segue, elencato solo su quel telefono; funzionano anche il widget e la scorciatoia del prossimo pacco. «Consegne» mostra «Tutti i tuoi pacchi in un unico posto», con «Per ora, solo su questo dispositivo.» e un pulsante «Accedi».

Peek dimentica un pacco cercato 30 giorni dopo la consegna; l'app ne tiene allora l'ultima risposta, segnata «Non più aggiornato», finché non lo elimini. «Impostazioni» › «Su questo dispositivo» › «Dimentica tutti» li dimentica tutti in una volta. I backup del telefono stesso includono i pacchi che segue e le preferenze di Peek.

Un account aggiunge la stessa lista su ogni dispositivo e su peektracker.com, il passaporto e gli amici, le e-mail di consegna e «Aggiungi pacchi via e-mail» ([tracciare i pacchi dalle tue e-mail](guide:add-parcels-by-email)). Accedi con «Continua con Google» o «Accedi con l’e-mail»; su Android non c'è l'accesso con Apple. Neanche «Esplora la demo», nella schermata di accesso, richiede un account. [Usare Peek senza account](guide:account-or-guest) mette a confronto le due scelte.

## Come aggiungo il widget di Peek alla schermata Home?

Aggiungi «Pacchi», l'unico widget di Peek, dal selettore dei widget, dove si legge «Controlla a colpo d’occhio i pacchi in arrivo, il prossimo per primo.» I passaggi di Google:

:::steps
- Apri i widget | In una schermata Home, tieni premuto uno spazio vuoto, poi tocca **Widget**.
- Trova Peek | Tocca Peek per vedere il suo widget, «Pacchi».
- Posizionalo | Tieni premuto il widget, fallo scorrere dove vuoi e solleva il dito.
:::

Google avverte che «alcuni di questi passaggi funzionano soltanto su Android 14 e versioni successive».

«Pacchi» parte da quattro celle per due e si riduce fino a due per due. Elenca i pacchi in arrivo, poi quelli consegnati nell'ultimo giorno, nell'ordine di Peek. Ogni pacco apre la sua pagina; il resto del widget apre la tua lista. Viene ridisegnato almeno ogni 20 minuti, e i suoi giorni cambiano a mezzanotte anche con l'app chiusa. Quando [Peek IA](guide:peek-ai) ha una stima per un pacco, il widget aggiunge «✦ Peek IA» e qualche parola accanto alla data, dove c'è spazio; «Impostazioni» › «Preferenze» › «Peek IA» la disattiva.

| Il widget mostra | Cosa significa |
| --- | --- |
| «Nessun pacco in arrivo al momento» | Nessun pacco è in arrivo: «Aggiungi un numero o un link di tracciamento per iniziare.» |

«Pacchi» sta solo nella schermata Home: non c'è un widget per la schermata di blocco, né niente di simile alle Attività Live.

### Peek ha scorciatoie sulla sua icona?

Sì. Tieni premuta l'icona di Peek: trovi «Aggiungi un pacco», che apre la schermata omonima, e una scorciatoia con il nome del tuo prossimo pacco in arrivo, che lo apre. Per fissarne una alla schermata Home, tienila premuta e falla scorrere al suo posto. Su Android Peek non ha comandi vocali: queste scorciatoie fanno le veci delle azioni di Siri dell'iPhone.

## Come condivido un link di tracking con Peek su Android?

Condividilo e scegli «Aggiungi a Peek», che accetta qualsiasi testo o link condiviso.

:::steps
- Condividi | Condividi il codice tracking, il link o la pagina da qualsiasi app, poi scegli «Aggiungi a Peek».
- Salva | Nella scheda che si apre, tocca «Salva i dati di tracciamento». Dice «Salvato. Apri Peek entro 10 minuti per verificare e aggiungere il pacco.»
- Apri Peek | Entro quei 10 minuti, «Aggiungi un pacco» si apre con i dati già inseriti.
:::

I link ai pacchi di Peek (peektracker.com/p/…) si aprono nell'app una volta installata. Per mandare un pacco a qualcun altro, vedi [condividere il tracciamento di un pacco](guide:share-parcel).

### Peek può scansionare un codice a barre su Android?

Solo la versione Google Play. Tocca l'icona del codice a barre alla fine del campo di tracking (letta ad alta voce come «Scansiona codice a barre»), nella prima schermata o in «Aggiungi un pacco»; con la lista vuota c'è anche un pulsante «Scansiona codice a barre». «Scansiona un codice a barre di tracciamento» si apre con la fotocamera posteriore e segue il codice a barre più vicino al centro. Se neghi la fotocamera, lo scanner sparisce finché non la consenti nelle impostazioni di Android. Non sai quale numero è il codice tracking? Vedi [dove si trova il codice tracking](guide:find-tracking-number).

:::sources
- [Peek: Peek per Android](https://peektracker.com/android) – Google Play, Android 12, il repository F-Droid, GitHub, la firma
- [Google Play: Peek](https://play.google.com/store/apps/details?id=com.plhery.peekdeliverytracker) – la scheda dell'app
- [Peek: app Android (in inglese)](https://github.com/plhery/peek-delivery-tracker/blob/main/android/README.md) – le due versioni, contenuto e aggiornamento del widget, scorciatoie, chiavi di firma, versioni preliminari
- [Peek: scheda F-Droid (in inglese)](https://github.com/plhery/peek-delivery-tracker/blob/main/android/fdroid/com.plhery.peekdeliverytracker.yml) – nome, quali copie precedenti disinstallare
- [F-Droid: Come aggiungere un repo a F-Droid](https://f-droid.org/it/tutorials/add-repo/) – Impostazioni › Repository › +, poi la scelta delle app da installare
- [Peek: release su GitHub](https://github.com/plhery/peek-delivery-tracker/releases) – i file .apk, contrassegnati come Pre-release
- [Peek: testi in italiano](https://github.com/plhery/peek-delivery-tracker/blob/main/shared/locales/it.json) – tutte le etichette di Peek citate qui
- [Peek: manifest Android](https://github.com/plhery/peek-delivery-tracker/blob/main/android/app/src/main/AndroidManifest.xml) – «Aggiungi a Peek», link ai pacchi, fotocamera, backup
- [Peek: il widget «Pacchi»](https://github.com/plhery/peek-delivery-tracker/blob/main/android/app/src/main/res/xml/parcels_widget.xml) – solo schermata Home, le sue dimensioni
- [Peek: Peek IA nel widget](https://github.com/plhery/peek-delivery-tracker/blob/main/android/app/src/main/kotlin/com/plhery/peekdeliverytracker/widget/WidgetPeekAi.kt) – «✦ Peek IA» accanto alla data di un pacco, dove c'è spazio
- [Peek: Impostazioni](https://github.com/plhery/peek-delivery-tracker/blob/main/android/app/src/main/kotlin/com/plhery/peekdeliverytracker/ui/settings/SettingsSheet.kt) – «Dimentica tutti», «Lingua», niente Attività Live, «Peek IA»
- [Peek: lo scanner della versione Play](https://github.com/plhery/peek-delivery-tracker/blob/main/android/app/src/play/kotlin/com/plhery/peekdeliverytracker/platform/CameraLabelScanner.kt) – codici a barre, permesso della fotocamera
- [Peek: assistenza (in inglese)](https://peektracker.com/support.html) – nessun account necessario, «Dimentica tutti»
- [Guida di Android: Aggiungere app, scorciatoie e widget alle schermate Home](https://support.google.com/android/answer/9450271?hl=it) – aggiungere un widget, fissare una scorciatoia, Android 14
:::
