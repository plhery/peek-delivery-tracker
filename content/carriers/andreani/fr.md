---
title: Suivi Andreani : numéros de colis et statuts en Argentine
description: Suivre un colis Andreani en Argentine : format des numéros à 15 chiffres, sens des statuts en espagnol et démarches si le suivi n’avance plus.
slug: suivi-andreani
published: 2026-11-23
updated: 2026-11-23
---

Andreani est un transporteur de colis en Argentine. Le catalogue de Peek le répertorie en Argentine et au Brésil, aussi sous les noms Correo Andreani et Grupo Logístico Andreani. Si tu as commandé en ligne, la boutique peut expédier ton colis avec Andreani : le numéro de suivi se trouve dans son e-mail d’expédition ou sur ta page de commande. Colle le numéro, ou un lien de suivi andreani.com, dans la case ci-dessus. Le suivi d’Andreani est sur andreani.com ; Peek interroge ce suivi et cette page explique chaque étape en français.

## Numéro de suivi Andreani : à quoi il ressemble

Les numéros Andreani que Peek connaît comptent 15 chiffres et commencent par `36000`, `31000` ou `30000`. Avec des chiffres inventés :

| Numéro (inventé) | Ce que c’est |
| --- | --- |
| `360004829103756` | `36000` suivi de 10 chiffres : la plage Andreani, mais une correspondance faible. [BRT](carrier:brt) et Yunda utilisent aussi 15 chiffres, donc Peek interroge à la fois Andreani et BRT. |
| `310004829103756` | `31000` suivi de 10 chiffres : également dans la plage Andreani. |
| `300004829103756` | `30000` suivi de 10 chiffres : également dans la plage Andreani. |
| `007394028156348` | 15 chiffres commençant autrement. Peek ne le relie pas à Andreani : [FedEx](carrier:fedex), [DPD France](carrier:dpd-fr), [Hermes](carrier:hermes-de), BRT et Yunda utilisent aussi 15 chiffres. |

Les espaces ne posent aucun problème lorsque tu colles un numéro dans Peek.

**Autres références.** Le suivi d’Andreani accepte aussi d’autres références : des numéros Andreani avec des lettres et les propres références des expéditeurs. Peek ne peut pas deviner à leur forme qu’ils appartiennent à Andreani : sélectionne Andreani manuellement (voir « Suivre un colis Andreani avec Peek » ci-dessous).

**Un lien de suivi.** Un lien andreani.com contenant `/envio/` suivi du numéro fonctionne aussi : Peek extrait le numéro du lien.

Tu ne trouves pas ton numéro ? Consulte [comment trouver un numéro de suivi](guide:find-tracking-number).

## Statuts de suivi Andreani expliqués

Le suivi d’Andreani regroupe ses lignes sous des en-têtes en espagnol. Les quatre que Peek connaît sont `Pendiente de ingreso` (en attente de réception), `Ingresado` (reçu), `En camino` (en route) et `Entregado` (livré). Chaque ligne est une courte phrase adressée au destinataire, mentionnant l’agence (`sucursal`) et la ville lorsqu’il y en a une. Voici les lignes d’un trajet habituel reconnues par Peek, dans l’ordre :

| Ce que dit Andreani | Ce que cela signifie pour toi | Statut Peek |
| --- | --- | --- |
| `Tu envío se encuentra pendiente de ingreso.` | L’expéditeur a annoncé le colis à Andreani, mais Andreani ne l’a pas encore reçu. | « Étiquette créée » |
| `¡Ya tenemos tu envío! El circuito comienza en C.a.b.a., Buenos Aires. Pronto lo enviaremos a la sucursal encargada de la entrega.` | Andreani a bien ton colis. Le trajet débute dans la ville et la province indiquées, puis le colis part vers l’agence chargée de la livraison. | « Pris en charge » |
| `Tu envío salió de la sucursal Monserrat (av Belgrano), en la localidad C.a.b.a. de la provincia Buenos Aires.` | Le colis a quitté l’agence indiquée, dans cette ville et cette province. | « En transit » |
| `Tu envío se encuentra en camino a la sucursal La Plata (calle 2).` | Il est en route vers l’agence nommée, celle qui assurera la livraison. | « En transit » |
| `La sucursal La Plata (calle 2) ya está procesando tu envío. ¡Pronto tendrás novedades!` | L’agence de livraison a reçu le colis et le traite. Des nouvelles arriveront bientôt. | « En transit » |
| `Hoy te visitaremos para entregarte tu envío.` | Andreani passe livrer ton colis aujourd’hui. | « En livraison » |
| `Ya entregamos tu envío. ¡Esperamos volver a verte pronto!` | Livré. | « Livré » |

Les agences et villes de ce tableau ne sont que des exemples : tes lignes indiquent celles de ton propre trajet. `C.a.b.a.` désigne la Ville autonome de Buenos Aires (*Ciudad Autónoma de Buenos Aires*).

Peek affiche chaque ligne dans les mots espagnols d’Andreani, sous une étape Peek en français. Une ligne encore inconnue de Peek reprend l’étape de son en-tête : toute ligne sous `En camino` apparaît en « En transit ». Peek retire les numéros de téléphone et de pièce d’identité présents dans une ligne. Plus de détails dans [signification des statuts de suivi](guide:tracking-statuses).

## Comment Andreani livre en Argentine

Les lignes ci-dessus résument le parcours habituel :

1. **Prise en charge.** Andreani reçoit le colis au point de départ.
2. **Vers l’agence de livraison.** Il l’achemine vers l’agence responsable de la remise (`sucursal encargada de la entrega`). La ligne commençant par `Tu envío se encuentra en camino a la sucursal` la nomme.
3. **À l’agence de livraison.** La mention selon laquelle cette agence `ya está procesando tu envío` signifie que le colis y est arrivé.
4. **Jour de livraison.** `Hoy te visitaremos para entregarte tu envío.` indique qu’un livreur apporte le colis à l’adresse aujourd’hui.
5. **Livré.** `Ya entregamos tu envío.` clôt le trajet.

Peek n’a pas pu lire le site web d’Andreani : cette page ne donne donc ni délais de livraison, ni règles en cas d’absence, ni conditions ou durée de retrait en agence. Renseigne-toi auprès de la boutique ou d’Andreani. Voir aussi [livraisons manquées](guide:missed-delivery) et [colis non retirés](guide:uncollected-parcel).

## Suivi Andreani bloqué, colis perdu ou renvoyé

**Seulement `Pendiente de ingreso`.** L’expéditeur a créé l’envoi mais n’a pas encore remis le colis à Andreani. Peek affiche « Étiquette créée ». Demande à la boutique si la commande a bien été expédiée.

**Aucun résultat.** Il se peut que le suivi d’Andreani ne connaisse pas encore le numéro. Classé sous Andreani, le colis affiche « En attente de Andreani » et Peek continue d’interroger le transporteur. Si Andreani ne connaît toujours pas le numéro après deux jours, Peek te demande de le vérifier, de choisir un autre transporteur ou de supprimer le colis : vérifie-le auprès de la boutique. Voir [pourquoi un suivi ne se met plus à jour](guide:tracking-not-updating).

**Silencieux depuis plusieurs jours.** Quand la ligne la plus récente a quatre jours ou plus et que le colis reste sur « Étiquette créée », « Pris en charge » ou « En transit », Peek le signale avec le jour de cette ligne : « Aucune mise à jour depuis … ». Les colis restent souvent sans nouvelle le week-end. Si rien ne change, contacte la boutique.

**Livré, mais rien reçu.** La dernière ligne affiche `Ya entregamos tu envío. ¡Esperamos volver a verte pronto!`, mais tu n’as pas le colis. Renseigne-toi auprès des personnes présentes à l’adresse, des voisins et du gardien de l’immeuble, puis contacte la boutique. Voir [colis indiqué livré mais non reçu](guide:delivered-not-received).

**Perdu ou endommagé.** Adresse-toi d’abord à la boutique : c’est elle qui a commandé le transport auprès d’Andreani. Voir [colis perdus](guide:lost-parcel) et [colis endommagés](guide:damaged-parcel).

**Renvoyé.** Le tableau des lignes Andreani dans Peek ne comporte pas encore de ligne de retour dédiée. Si le colis repart chez l’expéditeur, la ligne s’affiche quand même dans les mots d’Andreani. Demande à la boutique un remboursement ou un nouvel envoi. Voir [retourné à l’expéditeur](guide:returned-to-sender).

**La boutique ne répond pas.** Si une entreprise auprès de laquelle tu as acheté ne respecte pas ses engagements, tu peux déposer une réclamation auprès de *Defensa del Consumidor*, l’organisme argentin de protection des consommateurs. C’est gratuit et en ligne, via la *Ventanilla Federal Única de Defensa del Consumidor*. Il te faut ton DNI ou passeport, un numéro de téléphone ou une adresse e-mail pour recevoir la réponse, le nom et la ville de l’entreprise ainsi que les informations de base de l’achat. *Defensa del Consumidor* transmet la demande au bureau compétent et t’envoie un numéro de dossier par e-mail.

## Service client et contact Andreani

Peek n’ayant pas pu lire les pages web d’Andreani, cette page ne publie ni numéro de téléphone, ni adresse e-mail, ni horaires : Peek ne liste que les coordonnées relevées sur les pages officielles. Le site d’Andreani est andreani.com.

Pour une commande en ligne, contacte d’abord la boutique : c’est elle qui a réservé la livraison et dispose des détails de l’envoi.

## Suivre un colis Andreani avec Peek

Un numéro à 15 chiffres ne suffit pas à [Peek](/) pour savoir qu’il appartient à Andreani. Colle-le tout de même : Peek suit plus de 4 500 transporteurs.

**Un numéro commençant par `36000`, `31000` ou `30000`.** Peek interroge à la fois Andreani et BRT (des services de suivi et Ozon seulement si aucun des deux ne connaît le numéro) et affiche « Vérification des services de suivi… » pendant la recherche. Si Andreani connaît le colis, la ligne sous le numéro nomme Andreani et affiche « a ce colis », puis Peek le classe chez Andreani. Sinon, elle indique « pas encore trouvé · Peek continue de vérifier ». Ajoute-le quand même : la carte affiche « Probablement Andreani » et « En attente du premier scan de Andreani », et Peek continue d’interroger Andreani toutes les 6 heures après les premières heures si le colis est dans l’app iPhone ou Android, ou dans ton compte. Pour une recherche sans compte, Peek vérifie durant les 6 premières heures, puis lorsque tu ouvres le lien (au plus toutes les 6 heures, puis au plus une fois par jour après deux jours) et, si les notifications sont activées, une fois par jour jusqu’à 30 jours après la recherche. Après une semaine sans résultat, il propose de vérifier le numéro auprès de l’expéditeur ou de choisir un autre transporteur.

**Un lien andreani.com.** Colle le lien de suivi contenant `/envio/`. Peek affiche aussitôt Andreani (« Transporteur détecté ») et suit le numéro extrait du lien, y compris une référence avec des lettres.

**Tout autre numéro.** Choisis Andreani toi-même : appuie sur « Changer », puis cherche Andreani sous « Tous les transporteurs » ou avec la recherche. Pour un numéro commençant par `36000`, `31000` ou `30000`, il apparaît aussi sous « Correspond à ce numéro ». Sur un colis déjà ajouté, utilise « Changer de transporteur ». Pour Andreani, Peek accepte de 6 à 30 lettres et chiffres, avec au moins un chiffre ; sinon, la ligne indique qu’Andreani « n’émet pas de numéros comme celui-ci ».

**Ce que montre Peek.** Peek lit directement le suivi d’Andreani, la même requête que la page officielle. Pour chaque étape, il affiche le texte espagnol d’Andreani, son étape (tableau ci-dessus), l’agence et l’heure. Les horaires d’Andreani suivent l’heure d’Argentine, qui n’a qu’un fuseau horaire et aucun changement d’heure, et Peek les convertit dans ton fuseau horaire. Le site d’Andreani affiche aussi le nom de l’expéditeur, le nom du chauffeur, des remarques, une position sur carte, une barre de progression et une estimation de livraison : Peek laisse tout cela de côté et n’affiche donc pas de date de livraison estimée.

**Quand Andreani ne répond pas.** Peek interroge alors des services de suivi et affiche leurs lignes dans leurs propres mots. Peek ne connaît aucun relais d’Andreani vers un autre transporteur et suit donc le colis chez Andreani jusqu’au bout.

**Vérifications et alertes.** Tant que le colis est ouvert dans Peek, ou le jour de sa livraison avec les notifications activées, Peek le vérifie jusqu’à toutes les 10 minutes, et toutes les 2 minutes à partir de 6 h, heure d’Argentine, le jour où Andreani le scanne en livraison, tant que le suivi d’Andreani répond. Un colis sans nouvelle depuis deux jours, ou depuis 12 heures en « Étiquette créée » ou « Pris en charge », est vérifié chaque heure, et après sept jours sans nouvelle en « Étiquette créée », toutes les 6 heures. Tu peux recevoir des alertes avec « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek ne peut contacter ni Andreani ni la boutique à ta place.

## Questions sur le suivi Andreani

### À quoi ressemble un numéro de suivi Andreani ?

15 chiffres commençant par `36000`, `31000` ou `30000`, par exemple `360004829103756`. Le suivi d’Andreani accepte aussi d’autres références. Colle le numéro ou le lien andreani.com dans Peek.

### Que signifie « Pendiente de ingreso » dans le suivi Andreani ?

En attente de prise en charge : l’expéditeur a annoncé le colis, mais Andreani ne l’a pas encore reçu. Peek affiche « Étiquette créée ». Si cela dure plusieurs jours, demande à la boutique si le colis a bien été envoyé.

### Que signifie « Hoy te visitaremos para entregarte tu envío. » ?

Andreani passe livrer ton colis aujourd’hui. Peek affiche « En livraison » et, tant que le colis est ouvert dans Peek ou que les notifications sont activées, vérifie plus fréquemment jusqu’à la confirmation de livraison.

### Pourquoi mon suivi Andreani ne se met-il plus à jour ?

La boutique n’a peut-être pas encore remis le colis, ou Andreani n’a pas de nouvelle étape à afficher, ce qui arrive souvent le week-end. Peek continue d’interroger Andreani et signale un colis sans nouvelle après quatre jours ; contacte alors la boutique.

### Comment suivre un colis Andreani en français ?

Colle le numéro dans Peek. Les lignes d’Andreani restent en espagnol, mais Peek place chacune sous une étape en français, et le tableau ci-dessus les traduit.

:::sources
- [Universal Parcel Scraper : Andreani](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/andreani) – noms et pays, format à 15 chiffres, autres références, liens andreani.com, interrogation du suivi, lignes et en-têtes espagnols et leurs étapes, retrait des numéros de téléphone et d’identité, heure d’Argentine, ce que le suivi affiche et ce que Peek ignore, pas de date de livraison
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formats à 15 chiffres d’autres transporteurs, transporteurs interrogés par Peek, services de suivi, pas de transfert pour Andreani
- [Argentina.gob.ar : Iniciar un reclamo ante Defensa del Consumidor](https://www.argentina.gob.ar/servicio/iniciar-un-reclamo-ante-defensa-del-consumidor) – réclamations contre une entreprise qui ne tient pas ses engagements, Ventanilla Federal Única, gratuit et en ligne, pièces nécessaires, numéro de réclamation par e-mail
:::
