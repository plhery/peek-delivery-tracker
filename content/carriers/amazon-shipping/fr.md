---
title: Amazon Shipping suivi : numéro de suivi et colis non reçu
description: Suivre un colis Amazon Shipping : à quoi ressemble le numéro de suivi, quel site le connaît et qui contacter si le colis n'arrive pas ou arrive abîmé.
slug: amazon-shipping-suivi
published: 2026-10-12
updated: 2026-10-27
---

Un colis Amazon Shipping se suit sans compte Amazon, avec son numéro de suivi, sur le site de suivi public d'Amazon Shipping, track.amazon.fr. Ce site ne montre que des colis qui ne viennent pas d'une commande passée sur Amazon même : une commande Amazon se suit plutôt dans « Vos commandes ».

## Numéro de suivi Amazon Shipping : à quoi il ressemble

Il n'existe pas de site de suivi Amazon Shipping belge ou suisse : pour les numéros `BE` et `CH`, Peek interroge track.amazon.fr. Voici les formes que Peek reconnaît, avec des chiffres inventés :

| Forme (inventée) | Ce que c'est |
| --- | --- |
| `FR0000000000` | FR et 10 chiffres. |
| `BE0000000000` | Un autre code pays européen et 10 chiffres (`BE`, `CH`, `DE`, `IT`, `ES`…). |
| `TBA123456789012` | `TBA`, `TBC` ou `TBM` et 12 chiffres. |

Le piège : Amazon Logistics, qui livre les commandes passées sur Amazon, utilise exactement les mêmes formes. Seul le site de suivi d'Amazon Shipping fait la différence : il connaît les colis Amazon Shipping et ne trouve pas les autres. S'il ne trouve rien et que tu as commandé sur Amazon, lis plutôt la page [suivi Amazon Logistics](carrier:amazon-logistics). Un numéro de commande comme `000-0000000-0000000` n'est pas un numéro de suivi. Pas de numéro ? C'est la boutique qui l'a : voir [où trouver son numéro de suivi](guide:find-tracking-number).

## Colis Amazon Shipping non reçu, perdu ou abîmé

Ton interlocuteur, c'est la boutique où tu as commandé. Dans l'UE, selon Your Europe, le vendeur est responsable de la livraison et de tout dommage causé à tes marchandises, de l'expédition à la réception.

**Livré mais pas reçu.** C'est au vendeur de prendre contact avec le service de livraison et de prouver que l'article a bien été livré. S'il ne peut pas, il doit t'envoyer un article identique ou te rembourser. La suite dans [colis livré mais non reçu](guide:delivered-not-received).

**Abîmé.** Le vendeur répond des dommages causés pendant le transport : il doit réparer ou remplacer l'article, ou te rembourser.

**Perdu ou en retard.** Sans date convenue, la commande doit être livrée dans les 30 jours. Si elle n'arrive pas, signale-le au vendeur et accorde-lui un délai supplémentaire raisonnable. S'il ne le respecte pas, tu peux annuler ta commande, et il doit te rembourser le plus rapidement possible. Si c'est seulement le suivi qui ne bouge plus, voir [suivi qui ne bouge plus](guide:tracking-not-updating).

## Amazon Shipping contact et service client

Pour un colis qui n'arrive pas ou arrive abîmé, ton interlocuteur est la boutique, comme expliqué plus haut.

> Amazon prévient : si un message parle d'un achat, ne réponds pas et ne clique sur aucun lien. Amazon ne te demandera jamais de payer par téléphone ou par e-mail, ni d'acheter une carte cadeau ; n'appelle pas un numéro reçu par SMS ou par e-mail, ou trouvé sur un moteur de recherche, et signale le message avec l'outil de signalement d'Amazon. Les autres signes d'un faux message sont dans [arnaque colis par SMS](guide:delivery-scam-texts).

## Suivre un colis Amazon Shipping avec Peek

Colle le numéro ou un lien track.amazon (`/tracking/` suivi du numéro) dans [Peek](/). Peek demande au site de suivi public d'Amazon s'il connaît le numéro. Si Amazon répond, tu vois « Le suivi public est disponible avec Amazon Shipping. Peek te tiendra au courant de cette livraison. » Sinon, c'est une livraison Amazon Logistics, qui se suit généralement dans Vos commandes. Si la vérification échoue, réessaie. Amazon Shipping n'est pas dans la liste « Changer de transporteur » : cette vérification est le seul chemin. Pas de code postal à donner.

Peek affiche le statut, l'historique avec la ville, la région et le pays de chaque scan, le jour de livraison prévu et l'heure de livraison. Il vérifie toutes les 10 minutes en journée (toutes les 2 minutes une fois le colis en livraison) et toutes les une à deux heures la nuit, place chaque scan sur une carte et peut t'envoyer des notifications quand le statut change. Il montre ses propres libellés dans ta langue plutôt que les phrases d'Amazon, sauf « Arrived at sorting center », « Arrived at delivery center » et « Departed facility », qui restent en anglais. Il laisse de côté le nom de la boutique, ton nom et ton adresse, la signature et la photo de livraison. Amazon est la seule source : quand Amazon ne fournit plus l'historique, Peek le dit et arrête de vérifier. Il ne suit pas le colis vers un autre transporteur.

## Questions sur le suivi Amazon Shipping

### Amazon Shipping, c'est quoi ?

Un colis Amazon Shipping se suit sur le site de suivi public d'Amazon, qui ne montre que des colis ne venant pas d'une commande passée sur Amazon même. Ta commande vient donc d'une autre boutique, que tu contactes en cas de problème.

### Quel est le délai de livraison d'Amazon Shipping ?

Quand le site de suivi d'Amazon donne un jour de livraison prévu, Peek l'affiche, sauf pour les numéros `TBA`, `TBC` et `TBM`. Sans date convenue avec la boutique, une commande doit être livrée dans les 30 jours dans l'UE, selon Your Europe.

### Qui contacter si mon colis Amazon Shipping n'arrive pas ?

La boutique où tu as commandé : dans l'UE, selon Your Europe, le vendeur est responsable de la livraison, et c'est à lui de prendre contact avec le service de livraison.

:::sources
- [Your Europe : Expédition et livraison](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_fr.htm) – responsabilité du commerçant de l'expédition à la réception, 30 jours, délai supplémentaire, annulation et remboursement
- [Your Europe : FAQ expédition et livraison](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/faq/index_fr.htm) – vendeur responsable de la livraison, preuve de livraison, article identique ou remboursement, dommages pendant le transport
- [About Amazon France : Six conseils contre les arnaques](https://www.aboutamazon.fr/actualites/nos-boutiques/six-conseils-pratiques-pour-se-proteger-des-tentatives-darnaques-par-usurpation-didentite) – messages sur un achat, paiement, carte cadeau, numéros à ne pas appeler, signalement
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – ce que Peek reconnaît et lit, quel site de suivi connaît quels numéros, pays du site de suivi européen, commandes Amazon visibles seulement dans le compte
:::
