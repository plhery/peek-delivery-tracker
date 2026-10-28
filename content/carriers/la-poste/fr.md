---
title: La Poste / Colissimo : suivi de colis, numéro et statuts
description: Suivi Colissimo et La Poste : à quoi ressemble le numéro, ce que veulent dire les statuts, la livraison manquée, le point de retrait et qui contacter.
slug: suivi-la-poste-colissimo
published: 2026-10-13
updated: 2026-10-28
---

Colissimo, c'est le service de colis de La Poste. Pour suivre ton colis, colle son numéro, ou un lien de suivi laposte.fr, dans le champ ci-dessus. Le suivi de La Poste elle-même se trouve sur laposte.fr. Ce numéro te vient de l'expéditeur, en général dans l'e-mail d'expédition de la boutique.

## Numéro de suivi Colissimo : à quoi il ressemble

| Forme (inventée) | Ce que c'est |
| --- | --- |
| `6A12345678905` | 1 chiffre, 1 lettre et 11 chiffres : un Colissimo, une lettre suivie ou recommandée. Le dernier chiffre est une clé de contrôle. |
| `LA000000005FR` | 2 lettres, 9 chiffres et FR : un numéro postal international créé en France, pour un Colissimo ou une lettre suivie ou recommandée. Le chiffre avant FR est une clé de contrôle. |
| `87000000000000P` | 15 caractères commençant par 863 à 866, 870, 875, 880 ou 885 : un numéro Smart Data, celui que La Poste donne aux lettres suivies et recommandées. Le dernier caractère est une clé de contrôle ; le suivi de La Poste trouve aussi le numéro sans lui. |

Les numéros en …FR qui commencent par XU, XR, XT, XW, XY ou PZ sont des numéros [Chronopost](carrier:chronopost). Un numéro de 12 caractères fait plutôt penser à [Colis Privé](carrier:colis-prive). Un numéro de 13 ou 14 chiffres sans lettre peut aussi venir de DPD ou d'un autre transporteur : regarde le nom dans l'e-mail de la boutique. Plus de pistes dans [où trouver son numéro de suivi](guide:find-tracking-number).

## Suivi colis La Poste : ce que veulent dire les statuts

Peek affiche les scans de La Poste en français, mot pour mot, et donne au colis sa propre étape, d'après le dernier scan. Voici des formulations de La Poste, à peu près dans l'ordre où ton colis les rencontre, avec l'étape que Peek leur donne :

| La Poste écrit | Peek affiche | Ce que ça veut dire pour toi |
| --- | --- | --- |
| `Votre Colissimo va bientôt nous être confié ! Il est en cours de préparation chez votre expéditeur.` | Étiquette créée | L'expéditeur prépare le colis ; La Poste ne l'a pas encore. |
| `Votre colis a été déposé dans un point postal.` | Pris en charge | L'expéditeur l'a déposé dans un point postal. |
| `Votre colis a été déposé par l'expéditeur chez notre partenaire postal dans son pays d'origine.` | Pris en charge | Un colis venu de l'étranger, posté chez la poste de là-bas. |
| `Votre colis est en transit sur nos plateformes logistiques.` | En transit | Il passe d'un centre de tri à l'autre. |
| `Les formalités import/export sont en cours sur votre colis.` | À la douane | Il est en cours de dédouanement. |
| `Votre Colissimo est en cours de traitement sur le site de tri local.` | En transit | Il est au site de tri local. |
| `Votre colis est sur son site de distribution. Nous le préparons pour le mettre en livraison.` | En livraison | Il est trié dans la tournée du jour. |
| `Votre colis n'a pas pu vous être remis. Il sera mis en livraison demain (hors dimanche et jours fériés).` | Tentative de livraison | Pas livré ; il repart en livraison le lendemain, hors dimanche et jours fériés. |
| `Nous sommes passés mais nous n'avons pu vous remettre votre colis. Il va être acheminé vers votre point de retrait.` | Tentative de livraison | Passage manqué ; il part vers un point de retrait. |
| `Votre colis est disponible dans votre point de retrait pendant un délai de 15 jours calendaires.` | Prêt à être retiré | Il t'attend au point de retrait, jusqu'à la date limite indiquée. |
| `Votre colis est livré dans votre boite aux lettres.` | Livré | Livré dans ta boîte aux lettres. |
| `Votre colis vous a attendu dans votre point de retrait jusqu'à la date limite. Nous sommes contraints de le renvoyer à l'expéditeur.` | Problème signalé | Pas retiré à temps ; il repart chez l'expéditeur. |
| `Votre colis est livré à son expéditeur à la suite d'un retour.` | Retourné à l'expéditeur | Il est de retour chez l'expéditeur. |

Le plus trompeur, c'est `Votre colis est sur son site de distribution. Nous le préparons pour le mettre en livraison.` On croirait le colis encore coincé au dépôt. En fait, c'est le tri du matin avant la tournée : la livraison, ou un passage manqué, suit le même jour.

## Livraison Colissimo manquée et point de retrait

Après un passage manqué, le suivi peut annoncer la suite : une nouvelle livraison le lendemain, ou un point de retrait (voir les scans plus haut). Le scan qui dit que le colis t'y attend peut donner le délai, par exemple `Le délai de retrait est de 5 jours.` ou 15 jours calendaires. Peek affiche le bureau de poste, le relais ou la consigne qui le garde, avec son adresse. S'il n'est pas retiré à temps, il repart chez l'expéditeur. Pour modifier une livraison, passe par La Poste, sur laposte.fr ; Peek ne peut pas le faire. Plus d'infos dans [livraison manquée](guide:missed-delivery) et [colis non retiré](guide:uncollected-parcel).

## Colis Colissimo retardé, perdu, abîmé ou en douane

**Suivi immobile.** Lis [suivi de colis qui ne bouge plus](guide:tracking-not-updating), puis contacte l'expéditeur.

**Colis perdu ou abîmé.** Si tu as acheté dans une boutique, commence par elle. En France, selon Service-Public.fr, quand la livraison passe par le transporteur proposé par le vendeur, le vendeur est seul responsable : la perte ou l'avarie n'est à ta charge qu'une fois le colis physiquement entre tes mains. Sans date convenue, il doit livrer au plus tard 30 jours après la commande. Passé ce délai, demande-lui de livrer dans un délai supplémentaire raisonnable ; s'il ne livre toujours pas, tu peux annuler la commande, et il doit te rembourser sous 14 jours. Dans toute l'UE, selon Your Europe, le commerçant répond des dommages de l'expédition à la réception. Voir [colis perdu](guide:lost-parcel) et [colis abîmé](guide:damaged-parcel).

**Réclamation à La Poste.** Selon l'Arcep, le régulateur postal, la réclamation est gratuite, et l'expéditeur comme le destinataire peuvent en faire une, même si leurs droits ne sont pas tout à fait les mêmes. Passe par les canaux ci-dessous. Si la réponse ne te satisfait pas, saisis le service recours de La Poste par les mêmes canaux. Ensuite, ou sans réponse au bout de deux mois, tu peux saisir gratuitement le Médiateur du Groupe La Poste, dans un délai d'un an après ta première réclamation écrite.

**Colis venu hors de l'UE.** Il peut devoir la TVA et, pour une commande de 150 € ou moins, le droit de douane forfaitaire de 3 € par article appliqué par l'UE depuis le 1er juillet 2026 ; c'est le vendeur ou le transporteur qui le perçoit. La douane précise que ces taxes peuvent t'être demandées à la réception du colis par le transporteur, La Poste par exemple, qui peut y ajouter ses propres frais de dédouanement, sans lien avec ce que perçoit la douane. Plus de détails dans [colis bloqué en douane](guide:customs).

## Colissimo contact : téléphone et service client

Les canaux de La Poste, tels que les indiquent l'Arcep et l'annuaire de Service-Public.fr :

- **En France :** le 3631, du lundi au vendredi de 8 h 30 à 19 h et le samedi de 8 h 30 à 13 h. Le service est gratuit ; tu paies le prix d'un appel vers un fixe.
- **Depuis la Belgique, la Suisse ou ailleurs :** le +33 810 821 821, service clients international, du lundi au vendredi de 8 h à 19 h (heure de Paris).
- **En ligne :** sur le site de La Poste, laposte.fr.
- **Par courrier :** Service consommateurs, 99 999 La Poste. Ou dans un point de contact postal.

> Cybermalveillance.gouv.fr cite La Poste : elle « ne vous demandera jamais de payer pour retirer un colis par SMS ou par mail ». Au moindre doute, ne clique pas sur le lien : vérifie le vrai suivi sur laposte.fr ou dans Peek. Signale un hameçonnage au nom de La Poste ou de Colissimo à phishing@laposte.fr, et un SMS douteux au 33700 (gratuit). Plus d'infos dans [arnaque colis par SMS](guide:delivery-scam-texts).

## Suivre un Colissimo avec Peek

[Peek](/) reconnaît La Poste / Colissimo tout de suite pour les numéros Colissimo de 13 caractères dont la clé est valide, les numéros de 14 chiffres commençant par `870`, ainsi que les numéros Smart Data de 15 caractères et les numéros postaux en FR dont le caractère de contrôle est valide. Avec un autre code produit ou une clé fausse, il interroge La Poste seule ; pour 14 chiffres partagés avec DPD et d'autres, il interroge en même temps les transporteurs probables. Les liens laposte.fr avec `code=` ou `idShip=` marchent ; un lien colissimo.fr seulement si le numéro qu'il contient est reconnu tout de suite.

Peek lit le suivi de La Poste en français, avec l'heure exacte de chaque scan. Il affiche le pays de chaque scan sur une carte, la livraison prévue, le nom de la boutique, l'heure de livraison et, pendant que le colis attend, le bureau de poste, le relais ou la consigne avec son adresse. Il ne garde ni ton nom ni ton adresse. Il vérifie toutes les 10 minutes en journée, toutes les 2 minutes une fois le colis en livraison, toutes les une à deux heures la nuit, et t'envoie des notifications à chaque scan, aux étapes importantes ou le jour de livraison uniquement.

Un service Chronopost est suivi chez Chronopost, un partenaire nommé par La Poste est suivi chez lui, et un numéro postal envoyé à l'étranger peut être suivi chez la poste du pays, comme [La Poste suisse](carrier:swiss-post), une fois qu'elle montre une progression. Peek ne peut ni modifier une livraison, ni payer la douane.

## Questions sur le suivi Colissimo

### Combien de temps met un Colissimo ?

Selon La Poste Groupe, 41 % des Colissimo ont été livrés en J+1, le lendemain du dépôt, en 2025, et La Poste accélère le tri et la livraison pour en faire la norme. Elle ne donne pas de chiffre pour les autres. Peek affiche la livraison prévue quand La Poste en donne une.

### Colissimo livre-t-il le samedi ?

Oui. La Poste Groupe indique une distribution du courrier, de la presse et des colis 6 jours sur 7, et après un passage manqué, le suivi peut annoncer une nouvelle livraison le lendemain « hors dimanche et jours fériés ».

### Combien de temps un colis reste-t-il en point relais ?

Le suivi peut le dire quand le colis y arrive, par exemple `Le délai de retrait est de 5 jours.` ou 15 jours calendaires. Passé ce délai, il repart chez l'expéditeur.

### Pourquoi mon numéro Colissimo ne marche pas ?

Compare-le aux formes ci-dessus : un numéro en …FR qui commence par XU, XR, XT, XW, XY ou PZ est un numéro Chronopost. Une fois le colis sorti de France, le suivi de La Poste peut nommer l'entreprise qui le livre à l'étranger et donner son numéro ; Peek peut alors le suivre chez elle dès qu'elle montre une progression. Si le suivi est simplement figé, lis [suivi de colis qui ne bouge plus](guide:tracking-not-updating).

:::sources
- [La Poste Groupe : La branche Services-Courrier-Colis](https://www.lapostegroupe.com/fr/services-courrier-colis) – Colissimo, distribution 6 j/7, 41 % en J+1 en 2025
- [Arcep : Comment résoudre un litige avec un opérateur postal ?](https://www.arcep.fr/mes-demarches-et-services/consommateurs/fiches-pratiques/la-resolution-des-litiges-postaux.html) – réclamation gratuite, expéditeur ou destinataire, téléphones et horaires, site, adresse postale, point de contact postal, service recours, Médiateur
- [Le Médiateur du Groupe La Poste : Foire aux questions](https://mediateur.groupelaposte.com/pages/foire-aux-questions) – gratuit, double niveau de réclamation, deux mois, un an
- [Service-Public.fr : Service consommateur de La Poste](https://lannuaire.service-public.gouv.fr/centres-contact/R11647) – 3631, horaires et coût, service clients international, adresse postale
- [Service-Public.fr : Achat à distance : livraison du bien ou exécution de la prestation](https://www.service-public.gouv.fr/particuliers/vosdroits/F10037) – vendeur seul responsable, transfert des risques, 30 jours, délai supplémentaire, annulation, remboursement sous 14 jours
- [Your Europe : Expédition et livraison](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_fr.htm) – responsabilité du commerçant jusqu'à la réception, 3 € par article perçu par le vendeur ou le transporteur
- [Douane : Droit de douane forfaitaire de 3 euros sur les ventes à distance de biens importés](https://www.douane.gouv.fr/fiche/droit-de-douane-forfaitaire-de-3-euros-sur-les-ventes-distance-de-biens-importes) – 3 € par article jusqu'à 150 €, depuis le 1er juillet 2026
- [Douane : Vous achetez sur internet](https://www.douane.gouv.fr/demarche/vous-achetez-sur-internet) – taxes demandées à la réception par le transporteur (La Poste citée), ses frais de dédouanement
- [Cybermalveillance.gouv.fr : L'hameçonnage à la livraison de colis](https://www.cybermalveillance.gouv.fr/tous-nos-contenus/actualites/escroqueries-livraison-colis) – La Poste ne demande jamais de payer par SMS ou par mail, phishing@laposte.fr, 33700
- [Universal Parcel Scraper : La Poste](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/la-poste) – formes de numéro, clés de contrôle, formulations de La Poste et étapes de Peek, points de retrait, partenaires à l'étranger, ce que Peek lit
- [Universal Parcel Scraper : historique de suivi vérifié](https://github.com/plhery/universal-parcel-scraper/blob/main/testing/auditedTrackingHistory.json) – formulations de La Poste relevées sur de vrais colis
:::
