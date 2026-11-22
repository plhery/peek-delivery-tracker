---
title: Suivi Russian Post (Почта России) : numéros et statuts
description: Suivre un colis Russian Post (Почта России) : formats RU et à 14 chiffres, traduction des statuts russes et suivi avec Peek.
slug: suivi-russian-post
published: 2026-11-22
updated: 2026-11-22
---

Russian Post (Почта России, Pochta Rossii) est le service postal de la Russie. Il achemine les colis envoyés depuis la Russie et ceux expédiés vers la Russie. Le numéro de suivi est fourni par l'expéditeur ou la boutique : colle-le dans le champ ci-dessus. Le site officiel de Russian Post est pochta.ru, et Peek interroge son suivi pour toi comme expliqué plus bas.

## Numéro de suivi Russian Post : à quoi il ressemble

Avec des chiffres inventés :

| Numéro (inventé) | Ce que c'est |
| --- | --- |
| `RR482910576RU` | Deux lettres, neuf chiffres et `RU` : le format postal international, terminé par le code de la Russie, avec une clé de contrôle en neuvième chiffre. Quand il commence par C, E, L ou R, Peek reconnaît tout de suite Russian Post. |
| `VV482910576RU` | Un autre numéro international en `RU` dont la première lettre n'est pas C, E, L ni R. Peek l'affiche comme « Unknown postal carrier » (service postal inconnu) : tu peux choisir Russian Post toi-même. |
| `80482910573625` | 14 chiffres commençant par `80`, avec une clé de contrôle en dernier chiffre. Peek le rapproche de Russian Post sans certitude : DPD, SEUR, BRT, Delhivery et d'autres transporteurs utilisent aussi ce format. |
| `80482910573620` | Le même format avec une mauvaise clé de contrôle. Peek ne propose pas Russian Post, seulement les autres transporteurs : s'il s'agit bien d'un numéro Russian Post, vérifie les chiffres. |
| `RR482910576CN` | Un numéro émis par une autre poste, ici [China Post](carrier:china-post). Peek le suit auprès de cette poste, y compris lorsqu'il voyage vers la Russie. |

Les espaces et les minuscules ne gênent pas quand tu colles un numéro dans Peek. Si tu ne trouves pas le tien, consulte [où trouver son numéro de suivi](guide:find-tracking-number) et [formats de numéros de suivi](guide:tracking-number-formats).

## Statuts de suivi Russian Post : ce qu'ils signifient

Russian Post rédige ses étapes en russe. Peek les lit telles qu'elles figurent dans le tableau ci-dessous sur le suivi de Qwintry, un réexpéditeur qui confie une partie de ses colis à Russian Post : pour un colis ajouté avec son numéro Qwintry, ou ajouté avec son numéro RU et rattaché à Qwintry (voir « Colis venant de Qwintry » plus bas). Sur ce suivi, chaque étape Russian Post indique une opération, comme `обработка` (traitement), suivie d'un détail et du bureau de poste à la fin. S'il n'y a pas de détail, c'est l'opération qui fait office d'étape. À peu près dans l'ordre où un colis les reçoit :

| Ce qu'affiche Russian Post | Ce que ça veut dire pour toi | Ce qu'affiche Peek |
| --- | --- | --- |
| `Партионный` | Russian Post a pris le colis en charge dans un lot avec d'autres (opération `приём`, réception). | « En transit » |
| `экспорт международной почты` | Export de courrier international : le colis quitte le pays où il se trouve. | « En transit » |
| `Прибыло на территорию РФ` | Le colis est arrivé en Russie. | « En transit » |
| `импорт международной почты` | Import de courrier international : le colis est pris en charge à son entrée en Russie. | « En transit » |
| `приём на таможню` | Remis à la douane. | « À la douane » |
| `Выпущено таможней` | Libéré par la douane : le colis reprend sa route. | « En transit » |
| `Покинуло место международного обмена` | Il a quitté le centre qui traite le courrier venant de l'étranger. | « En transit » |
| `Сортировка` | Tri en cours. | « En transit » |
| `Прибыло в сортировочный центр` | Arrivé dans un centre de tri. | « En transit » |
| `Покинуло сортировочный центр` | Parti d'un centre de tri. | « En transit » |
| `Прибыло в место вручения` | Arrivé au bureau de poste chargé de la livraison ou du retrait. | « En transit » |
| `Передано почтальону` | Remis au facteur pour livraison. | « En livraison » |
| `Временное отсутствие адресата` | Non livré : tu n'étais pas là. | « Tentative de livraison » |
| `Адресат заберет отправление сам` | Non livré, car tu viens retirer le colis toi-même. | « Tentative de livraison » |
| `Вручение адресату` | Remis au destinataire. | « Livré » |

Pour tout autre colis Russian Post, Peek interroge le suivi propre de Russian Post, qui répond en anglais : voir « Suivre un colis Russian Post avec Peek » ci-dessous.

## Comment Russian Post livre

Les étapes ci-dessus montrent le parcours en Russie : centres de tri, puis bureau de poste chargé de la remise. De là, un facteur peut apporter le colis (`Передано почтальону`). Si tu es absent ou si tu dois venir le chercher, l'étape le précise. Sur le suivi de Qwintry, Peek affiche la ville du bureau de poste comme lieu de l'étape. Si Peek est en russe, ou s'il ne parvient pas à situer le bureau, il garde le nom du bureau tel que Russian Post l'écrit, afin que tu saches lequel détient le colis.

Cette page n'indique ni délais de livraison, ni durées de garde, ni règles sur le nombre de passages : Peek n'a pas pu vérifier ces informations sur le site de Russian Post. Pour un colis qui attend en bureau de poste, vois [colis non récupéré](guide:uncollected-parcel) et [livraison manquée](guide:missed-delivery).

**Envoyé de Russie vers un autre pays.** Peek ne sait pas d'emblée quelle entreprise livre un colis Russian Post dans ton pays. Pour un numéro RU, il interroge aussi la poste du pays de destination indiqué par le suivi de Russian Post et peut y basculer le suivi (voir plus bas).

**Envoyé vers la Russie depuis un autre pays.** Peek suit le colis auprès du transporteur qui a émis le numéro, sur son propre suivi ou via des services de suivi. Si ce suivi désigne Russian Post pour la livraison, Peek interroge aussi le suivi de Russian Post et y transfère le colis dès qu'un scan y est au moins aussi récent que le dernier connu. Le seul fait que le colis soit destiné à la Russie ne suffit pas à le déplacer.

## Colis Russian Post bloqué, en retard ou non reçu

**Aucune étape pour l'instant.** Le suivi de Russian Post ou les services de suivi ne connaissent peut-être pas encore le numéro, ou l'expéditeur n'a pas encore déposé le colis. Peek continue de vérifier. Si rien n'apparaît après quelques jours, vérifie le numéro auprès de l'expéditeur. Plus de détails dans [suivi qui ne bouge plus](guide:tracking-not-updating).

**Bloqué en douane.** Si Peek indique « À la douane », le colis est entre les mains des douanes. Cette page ne donne ni règles douanières ni montants de frais, faute de page officielle vérifiable. Consulte [colis retenu en douane](guide:customs).

**Peek semble en retard.** Quand le suivi de Russian Post ne répond pas à Peek, un service de suivi prend le relais et peut avoir du retard sur le suivi officiel. Vérifie sur pochta.ru avant de t'inquiéter.

**Indiqué livré, mais rien reçu.** Si Peek affiche « Livré » alors que tu n'as rien reçu, renseigne-toi à ton bureau de poste et préviens l'expéditeur. Vois [colis livré mais non reçu](guide:delivered-not-received).

**Perdu ou renvoyé.** Adresse-toi d'abord à l'expéditeur ou à la boutique : c'est eux qui ont commandé l'envoi. Consulte [colis perdu](guide:lost-parcel) et [colis retourné à l'expéditeur](guide:returned-to-sender).

## Service client et contact Russian Post

Peek n'ayant pas pu lire le site de Russian Post, cette page n'indique ni numéro de téléphone ni adresse e-mail : nous ne publions que des coordonnées vérifiées directement sur les pages du transporteur. Les pages d'aide de Russian Post se trouvent sur info.pochta.ru. Quand un colis n'avance plus, Peek affiche un lien vers la page d'aide de Russian Post sur le suivi sous l'intitulé « Demande de l’aide à Russian Post ».

Pour une commande en ligne, contacte d'abord la boutique : c'est elle qui a expédié le colis.

## Suivre un colis Russian Post avec Peek

Colle ton numéro dans [Peek](/) : Peek suit plus de 4 500 transporteurs. Selon la forme du numéro :

- **Numéro international en `RU` commençant par C, E, L ou R (clé de contrôle valide).** Peek reconnaît tout de suite Russian Post (« Transporteur détecté »).
- **Autre numéro international en `RU` (autre lettre initiale).** Peek l'affiche comme « Unknown postal carrier ». Appuie sur « Changer » (ou plus tard « Changer de transporteur ») et choisis Russian Post : taper Почта России le trouve aussi. Peek affiche alors Russian Post.
- **14 chiffres commençant par `80` (clé de contrôle valide).** Pendant l'ajout, Peek affiche « Vérification des services de suivi… » et demande au suivi de Russian Post, ainsi qu'à DPD et aux autres transporteurs compatibles, s'ils connaissent le colis. Si seul Russian Post l'a, Peek affiche « Russian Post a ce colis. » et le classe là. Sinon, appuie sur « Changer » : Russian Post apparaît sous « Correspond à ce numéro », avec les autres transporteurs compatibles, ceux de ton pays en premier. Sur un colis déjà ajouté, utilise « Changer de transporteur ».

Peek lit le suivi officiel de Russian Post, qui lui répond en anglais. Il affiche chaque étape dans les termes anglais de Russian Post, tels que `Sorting`, `Arrived at the local distribution center` ou `Forwarded to the postman` (« En livraison »), avec l'heure, la ville, le pays et le pays de destination. `Available for pickup at the Post Office` apparaît en « Prêt à être retiré » et `Handed over for temporary storage` en « Problème signalé ». Après `Returned to the sender` (également « Problème signalé »), le colis repart vers l'expéditeur et sa remise finale passe en « Retourné à l’expéditeur ». Russian Post ne fournit pas de date de livraison estimée. Tant que son suivi ne connaît pas le numéro, Peek affiche « En attente de Russian Post », ou « En attente du premier scan de Russian Post » pour un numéro en `80` ajouté sans transporteur. Quand le suivi de Russian Post ne répond pas à Peek, Peek interroge des services de suivi — ParcelsApp, Ship24 et 17TRACK dans cet ordre, puis le suivi de l'Union postale universelle en dernier pour un numéro RU — et affiche l'historique du premier qui connaît le colis, dans ses propres mots, ce qui peut être en retard sur pochta.ru. Un service qui ne connaissait pas le numéro est réinterrogé le lendemain.

Pour un numéro en `80` ajouté sans transporteur, ou un numéro RU affiché comme « Unknown postal carrier », Peek interroge aussi pendant les 30 premiers jours, avant les services de suivi, les transporteurs susceptibles d'avoir le colis (dont Russian Post, DPD et d'autres pour un numéro en `80`). Si l'un d'eux montre le colis en mouvement, Peek déplace le colis vers lui. Pour un numéro en `80` ajouté sans transporteur, si un service de suivi cite uniquement Russian Post avec des étapes datées, Peek classe le colis sous Russian Post automatiquement. Pour un numéro RU affiché comme « Unknown postal carrier », une simple mention par un service de suivi ne suffit pas : il faut que le suivi de Russian Post affiche le colis ou que tu choisisses Russian Post toi-même.

Peek ne propose pas de lien direct vers le suivi de Russian Post. Si un service de suivi a répondu, le lien sur la page du colis ouvre ce service, par exemple « Ouvrir le site ParcelsApp ». Pour consulter pochta.ru, ouvre le site toi-même.

**Colis venant de Qwintry.** Si Qwintry a envoyé ton colis vers la Russie, ajoute plutôt son numéro Qwintry : `QR` suivi de huit chiffres. Peek reconnaît Qwintry immédiatement et lit son suivi, qui continue d'afficher les étapes de Russian Post une fois le colis remis à la poste. Quand le suivi de Qwintry nomme Russian Post, le colis affiche « Livraison par Russian Post » avec le numéro RU de la poste, et Peek interroge aussi Russian Post : dès que Russian Post publie un scan au moins aussi récent que le dernier de Qwintry, Peek poursuit le suivi chez Russian Post tout en gardant l'historique de Qwintry. Tant que Russian Post n'a que l'annonce du colis sans scan propre, le colis affiche « Annoncé à Russian Post, pas encore reçu ». Le suivi de Qwintry accepte aussi le numéro RU d'un colis qu'il a remis à la poste : si tu n'as que ce numéro et que le suivi de Russian Post ne le trouve pas, appuie sur « Changer » et choisis Qwintry.

**La poste de ton pays.** Pour un numéro RU classé sous Russian Post, le suivi de Russian Post indique le pays de destination, et Peek interroge aussi la poste de ce pays avec le numéro RU s'il lit directement son suivi, comme pour [USPS](carrier:usps), [Royal Mail](carrier:royal-mail), An Post, [Canada Post](carrier:canada-post) et Australia Post. Pour un colis affiché comme « Unknown postal carrier », il suffit qu'un service de suivi nomme la poste chargée de la livraison avec son numéro, ou le pays de destination pour un numéro RU : Peek interroge cette poste de la même manière. Dès qu'elle publie un scan au moins aussi récent que le dernier connu, Peek y poursuit le suivi.

Quand tu gardes le colis ouvert à l'écran, ou le jour de la livraison avec les notifications activées, Peek vérifie le suivi de Russian Post jusqu'à toutes les 10 minutes — et jusqu'à toutes les 2 minutes à partir de 6 h (heure locale) le jour où le colis passe en livraison — tant que ce suivi répond ; via les services de suivi, c'est toutes les heures, toutes les 3 heures après une journée sans nouveau scan, et jusqu'à toutes les 15 minutes le jour où le colis est en livraison. Le reste du temps, ainsi qu'après deux jours sans nouvelle (12 heures tant que le colis reste en « Étiquette créée » ou « Pris en charge »), Peek vérifie toutes les heures. Les notifications sont réglables sur « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek ne peut pas contacter Russian Post ni l'expéditeur à ta place.

## Questions sur le suivi Russian Post

### Que veut dire Почта России ?

C'est le nom russe de Russian Post, aussi transcrit Pochta Rossii. Son site officiel est pochta.ru.

### À quoi ressemble un numéro de suivi Russian Post ?

Un numéro international compte deux lettres, neuf chiffres et `RU`, comme `RR482910576RU`. Peek rapproche aussi de Russian Post les numéros de 14 chiffres commençant par `80` dont la clé de contrôle est valide.

### Pourquoi les étapes dans Peek ne sont-elles pas identiques à pochta.ru ?

Peek interroge le suivi de Russian Post en anglais, et affiche donc ses libellés anglais pour la plupart des étapes, plutôt que le texte russe. Quand ce suivi ne répond pas à Peek, un service de suivi prend le relais avec ses propres libellés et peut avoir du retard. Peek affiche les libellés russes du tableau ci-dessus pour les colis suivis via le suivi de Qwintry.

### Peek peut-il suivre un colis envoyé vers la Russie depuis un autre pays ?

Oui. Colle le numéro donné par l'expéditeur : Peek le suit auprès du transporteur qui l'a émis et ne le bascule vers Russian Post que si ce suivi désigne Russian Post pour la livraison.

:::sources
- [Universal Parcel Scraper : Russian Post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/russian-post) – noms, formats `80` et `RU` avec clés de contrôle et correspondances, suivi lu par Peek et libellés anglais, lieux, pays de destination, étapes et page d'aide
- [Universal Parcel Scraper : Qwintry](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/qwintry) – libellés russes de Russian Post sur le suivi de Qwintry et leurs étapes, bureau de poste comme lieu, numéro `QR` et numéros `RU` acceptés
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – numéros postaux en `RU`, transporteurs interrogés en premier, ordre des services de suivi, bascule vers la poste du pays de destination ou vers Russian Post
:::
