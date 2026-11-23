---
title: Suivi CVC (Си Ви Си) : suivre un colis en Bulgarie
description: Suivre un colis CVC (Си Ви Си, e-CVC) en Bulgarie avec Peek : ajouter le numéro, choisir CVC, sens des statuts bulgares et que faire s’il bloque.
slug: suivi-cvc
published: 2026-11-23
updated: 2026-11-23
---

CVC est un transporteur en Bulgarie. Le catalogue de Peek le connaît également sous les noms Си Ви Си, Си Ви Си ЕООД et e-CVC. Pour suivre un colis CVC, colle un lien vers le suivi de CVC dans la case ci-dessus, ou colle son numéro et sélectionne CVC comme transporteur : Peek ne peut pas reconnaître un numéro CVC à sa seule forme. Le numéro provient de la boutique ou de la personne qui a expédié le colis. Le suivi propre de CVC se trouve sur my.e-cvc.bg/track, en bulgare : il demande le numéro de lettre de voiture et ne comporte qu’un bouton, « Провери » (Vérifier).

Les sites propres de CVC n’en disent guère plus : my.e-cvc.bg n’affiche que ce formulaire de suivi et une page de connexion, sans pages publiques sur les règles de livraison, les tarifs ou les contacts. Cette page explique donc ce que demande le suivi de CVC et comment Peek suit un colis CVC, sans donner de règles de livraison, de tarifs ni de coordonnées que CVC ne publie pas.

## Numéro de suivi CVC : à quoi il ressemble

Le suivi de CVC demande un numéro de lettre de voiture (*товарителница* en bulgare). Son champ n’accepte que des chiffres : il supprime les lettres et les espaces au fur et à mesure de la saisie et accepte au maximum 20 caractères. Un numéro CVC est donc composé de chiffres (jusqu’à 20), mais le catalogue de Peek n’associe aucun format spécifique à CVC : Peek ne peut pas identifier le numéro comme appartenant à CVC et CVC n’apparaît jamais sous « Correspond à ce numéro » lors de l’ajout. Cette page ne donne aucun exemple inventé, car le suivi de CVC ne montre pas à quoi ressemble un numéro complet.

| Ce que tu colles | Ce que fait Peek |
| --- | --- |
| Le numéro CVC seul | Il ne nomme pas CVC. Il peut lister d’autres transporteurs correspondant au format, ou aucun. Choisis CVC toi-même, comme expliqué dans « Suivre un colis CVC avec Peek » ci-dessous. |
| Un lien vers le suivi CVC : `my.e-cvc.bg/track?wb=` suivi du numéro | Peek extrait le numéro du lien et nomme CVC aussitôt (« Transporteur détecté »). |
| Un numéro que la boutique attribue à un autre transporteur | Suis-le chez ce transporteur : l’e-mail de la boutique reste le meilleur indice sur qui livre le colis. |

**Où le trouver.** Dans l’e-mail d’expédition de la boutique ou sur ta page de commande, parfois sous le nom de lettre de voiture (*waybill*). Pour un colis envoyé par un particulier, demande-le-lui. Aucun numéro ? Consulte [comment trouver un numéro de suivi](guide:find-tracking-number) et [formats de numéros de suivi](guide:tracking-number-formats) pour voir à quoi ressemblent les numéros d’autres transporteurs.

## Signification des statuts de suivi CVC dans Peek

Peek lit directement la page de suivi de CVC sur my.e-cvc.bg/track et affiche chaque ligne dans le texte bulgare de CVC, classée sous une étape Peek en français. Les principales mentions bulgares reconnues par Peek :

| Ce que dit CVC | Ce que montre Peek | Ce que cela signifie pour toi |
| --- | --- | --- |
| `Приета за събиране` | Étiquette créée | L’envoi est enregistré pour la collecte ; CVC n’a pas encore pris le colis en main. |
| `Отложено събиране за …` | Étiquette créée | La collecte du colis a été reportée à la date indiquée. |
| `Взета от куриер …` | Pris en charge | Un coursier CVC a récupéré le colis. |
| `Приета в хъб …`, `Транспорт към хъб …` | En transit | Le colis est arrivé dans un hub CVC ou est en route vers un hub. |
| `Приета за разнос` | En livraison | Le colis est pris en charge pour la tournée de livraison du jour. |
| `Доставена`, `Доставена на …` | Livré | Le colis a été livré. |

Quand le suivi de CVC ne connaît pas le numéro, il répond `Пратката не е открита!` (envoi introuvable). Si le suivi propre de CVC ne répond pas, Peek peut aussi interroger des services de suivi comme ParcelsApp, Ship24 et 17TRACK et afficher l’historique du premier qui a le colis, classé dans les étapes habituelles de Peek (« Prêt à être retiré », « Tentative de livraison », « Retourné à l’expéditeur », « Problème signalé »).

Avant qu’une ligne n’apparaisse, Peek affiche « Recherche de mises à jour » pendant la vérification, puis « En attente de CVC ». Si deux vérifications d’affilée échouent, le colis reste sur « En attente de CVC » et sa page précise : « Impossible de joindre CVC pour l’instant. Peek continue d’essayer. » Plus de détails dans [signification des statuts de suivi](guide:tracking-statuses).

## Livraison CVC en Bulgarie

Les sites de CVC ne publient ni délais de livraison, ni nombre de tentatives, ni liste d’agences, ni règles de paiement à la livraison ou de durée de retrait : my.e-cvc.bg ne présente que son suivi et sa page de connexion. Demande à la boutique ou à l’expéditeur comment et quand CVC livre : ce sont eux qui ont réservé l’envoi. En cas de livraison manquée ou de colis en attente de retrait, voir [livraisons manquées](guide:missed-delivery) et [colis non retirés](guide:uncollected-parcel).

## Colis CVC bloqué, perdu ou renvoyé

**Rien pour l’instant.** Il est possible que CVC n’ait pas encore enregistré le numéro. Peek affiche alors « En attente de CVC » et continue de vérifier. S’il n’y a toujours aucune ligne deux jours après l’ajout, Peek le signale : « En attente de la première mise à jour du transporteur ». Vérifie le numéro dans l’e-mail de la boutique. Si rien n’apparaît après quelques jours, ouvre directement le suivi CVC via « Ouvrir le site CVC » sur la page du colis et demande à la boutique si le colis a bien été expédié.

**Aucune nouvelle depuis plusieurs jours.** Après quatre jours sans nouvelle étape, un colis resté sur « Étiquette créée », « Pris en charge » ou « En transit » affiche : « Aucune mise à jour depuis quatre jours ». Voir [pourquoi un suivi ne se met plus à jour](guide:tracking-not-updating).

**Livré, mais rien reçu.** Vérifie autour de la porte et auprès des voisins, puis contacte la boutique. Voir [livré mais non reçu](guide:delivered-not-received).

**Perdu ou endommagé.** Préviens d’abord la boutique : c’est elle qui a commandé la livraison et peut ouvrir une réclamation auprès de CVC ou te renvoyer un colis. Voir [colis perdus](guide:lost-parcel) et [colis endommagés](guide:damaged-parcel).

**Retourné.** « Retourné à l’expéditeur » signifie que le colis est revenu chez la boutique ou l’expéditeur. Demande un remboursement ou un nouvel envoi. Voir [retourné à l’expéditeur](guide:returned-to-sender).

## Contact et service client CVC

Les sites de CVC n’affichent aucun numéro de téléphone, aucune adresse e-mail et aucune adresse d’agence : my.e-cvc.bg ne contient que le formulaire de suivi et la connexion. Comme Peek ne publie que des coordonnées lues sur les pages officielles d’un transporteur, cette page n’en liste aucune. Adresse-toi d’abord à la boutique ou à l’expéditeur avec ton numéro de commande et ton numéro de suivi : ils ont réservé le transport et peuvent contacter CVC.

## Suivre un colis CVC avec Peek

[Peek](/) suit plus de 4 500 transporteurs, dont CVC, mais ne peut pas reconnaître un colis CVC à son numéro seul. Un lien vers le suivi de CVC identifie CVC aussitôt ; avec le numéro seul, choisis le transporteur toi-même :

1. Colle le numéro dans la case ci-dessus.
2. Appuie sur « Changer » à côté du transporteur et recherche CVC. « e-CVC » ou « Си Ви Си » fonctionnent aussi (avec « Aussi appelé e-CVC » ou « Aussi appelé Си Ви Си » sous le nom), tout comme « Bulgaria ».
3. Sélectionne CVC : la ligne indique alors « Choisi par toi ». Lance le suivi.

Sur un colis déjà ajouté, utilise « Changer de transporteur ». Pour CVC, Peek n’accepte qu’un numéro composé de 1 à 20 chiffres ; pour tout autre format, il indique que CVC « n’émet pas de numéros comme celui-ci ». Si Peek propose d’autres transporteurs sous « Correspond à ce numéro », ils ne font que partager la longueur du numéro. Avant ton choix, la ligne peut afficher « pas encore trouvé · Peek continue de vérifier », ce qui est normal pour un numéro récent.

Si tu laisses Peek détecter le transporteur, il consulte les services de suivi : si l’un d’eux montre le colis en mouvement et nomme CVC comme transporteur, Peek le classe automatiquement sous CVC.

**Ce que tu vois.** Le statut et chaque ligne de l’historique avec sa date et son heure, lues sur l’heure de Bulgarie. CVC ne fournit ni lieu de scan, ni date de livraison prévue, ni point relais, ni poids. Le bouton « Ouvrir le site CVC » ouvre directement ton numéro sur le suivi bulgare de CVC.

**Autre transporteur.** Durant les 30 premiers jours du colis, Peek interroge aussi les transporteurs dont le format correspond à ton numéro. Si l’un d’eux montre le colis en acheminement sous ce numéro, Peek le bascule chez lui avec la mention « Transporteur remplacé automatiquement (ancien : CVC) ».

**Fréquence.** Tant que le suivi de CVC répond, Peek vérifie le colis jusqu’à toutes les 10 minutes, et toutes les 2 minutes à partir de 6 h heure locale le jour où CVC le scanne en livraison (`Приета за разнос`). Après deux jours sans nouvelle (ou 12 heures en « Étiquette créée » ou « Pris en charge »), Peek vérifie chaque heure. Les notifications couvrent « Toutes les mises à jour », « Importantes uniquement » ou « Jour de livraison uniquement ».

Peek ne peut contacter ni CVC, ni la boutique, ni l’expéditeur à ta place, ni modifier la livraison ou déposer une réclamation.

## Questions sur le suivi CVC

### Comment suivre un colis CVC ?

Colle un lien vers le suivi CVC dans la case ci-dessus et Peek sélectionne CVC tout seul. Avec le numéro seul, appuie sur « Changer » et choisis CVC : Peek ne peut pas le deviner à sa forme. Sur un colis déjà ajouté, utilise « Changer de transporteur ». Le suivi officiel de CVC se trouve sur my.e-cvc.bg/track : saisis le numéro de lettre de voiture et clique sur « Провери ».

### À quoi ressemble un numéro de suivi CVC ?

C’est un numéro de lettre de voiture (*товарителница*) composé uniquement de chiffres : le suivi de CVC supprime les lettres et les espaces et accepte au plus 20 chiffres. Le catalogue de Peek n’associe aucun motif exclusif à CVC, il faut donc choisir CVC manuellement.

### e-CVC est-il la même chose que CVC ?

Oui. Le suivi de CVC sur my.e-cvc.bg s’appelle e-CVC.bg et porte le logo de CVC, et le catalogue de Peek répertorie e-CVC, Си Ви Си et Си Ви Си ЕООД comme autres noms de CVC. Rechercher l’un de ces noms dans la liste des transporteurs de Peek retrouve CVC.

### Pourquoi mon colis CVC affiche-t-il « En attente de CVC » ?

Sur peektracker.com, un colis classé sous CVC affiche ce message tant que le suivi de CVC (ou un service de suivi) n’a pas encore d’étape pour ce numéro, ou qu’il n’a pas pu être joint : sa page précise alors « Impossible de joindre CVC pour l’instant. Peek continue d’essayer. » Vérifie le numéro auprès de la boutique et consulte-le directement via « Ouvrir le site CVC ».

### Quel est le numéro de téléphone de CVC ?

Cette page n’en donne aucun : les sites de CVC ne publient pas de numéro de téléphone. Contacte la boutique qui a expédié le colis : c’est elle qui a commandé la livraison chez CVC.

:::sources
- [CVC : suivi e-CVC.bg](https://my.e-cvc.bg/track) – suivi en bulgare, « Потърсете товарителница », chiffres uniquement, 20 caractères maximum, « Провери », numéro après `wb=`, nom e-CVC.bg, logo CVC
- [CVC : connexion e-CVC.bg](https://my.e-cvc.bg/) – page de connexion uniquement, aucune page de livraison, de tarifs ou de contact
- [Universal Parcel Scraper : CVC](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/cvc) – noms et alias, Bulgarie, adresse du suivi, règle de lien, absence de motif numérique, lecture directe de my.e-cvc.bg/track et statuts bulgares
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – services de suivi de secours, correspondance du nom de transporteur avec CVC, lien vers le site du transporteur
:::
