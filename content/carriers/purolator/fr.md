---
title: Purolator suivi : NIC, statuts et livraison manquée
description: Suivre un colis Purolator : à quoi ressemble le NIC, ce que veulent dire les statuts, la cueillette après une livraison manquée et qui appeler.
slug: purolator-suivi
published: 2026-10-15
updated: 2026-10-27
---

Purolator est un transporteur canadien de colis et de fret, détenu à 91 % par Postes Canada. Pour suivre ton colis, entre son NIC dans l'outil de repérage de [purolator.com](https://www.purolator.com/fr/) ou colle-le dans le champ ci-dessus. Ce numéro vient de l'expéditeur : pour une commande en ligne, il est dans le courriel de confirmation d'expédition de la boutique.

## Numéro de suivi Purolator : le NIC et sa forme

Purolator appelle son numéro de suivi le NIC, pour numéro d'identification du colis (PIN sur son site en anglais). Chaque envoi en reçoit un, et c'est la référence à donner à Purolator pour toute question sur cet envoi. NIC, PIN, numéro de repérage ou numéro de suivi : c'est la même chose.

| Forme (inventée) | Ce que c'est |
| --- | --- |
| `123456789015` | 12 chiffres. Peek lit le dernier comme un chiffre de contrôle. |
| `ABC123456789` | 3 lettres et 9 chiffres. Le suivi de Purolator l'appelle aussi un NIC. |

L'outil de repérage accepte aussi une référence de suivi de 15 caractères au plus, si l'expéditeur en a créé une : un numéro de commande, un code, même une phrase. Elle fonctionne comme un NIC.

Douze chiffres, c'est une longueur courante. DPD France, Mondial Relay, Colis Privé, BRT, FedEx et plusieurs transporteurs chinois ont aussi des numéros de 12 chiffres : vérifie quel transporteur nomme le courriel de la boutique. Un numéro de 16 chiffres n'est jamais un numéro Purolator (c'est souvent [Postes Canada](carrier:canada-post)), et 13 caractères qui finissent par `CA` sont un numéro de Postes Canada. Plus de détails dans [numéro de suivi : à quel transporteur appartient-il](guide:tracking-number-formats).

Un envoi peut aussi recevoir un nouveau NIC en route : l'historique affiche alors un scan avec le nouveau numéro.

## Statuts du suivi Purolator : ce qu'ils veulent dire

Sur purolator.com en français, l'outil de repérage affiche les scans en français, sous un statut résumé comme `Livré`. Sur le site en anglais, les mêmes scans sont en anglais : c'est cette version que Peek affiche. Voici les deux, dans l'ordre où un colis les rencontre.

| Sur purolator.com/fr | En anglais (dans Peek) | Ce que ça veut dire pour toi |
| --- | --- | --- |
| `L'expéditeur a créé l'étiquette` | `Shipper created a label` | L'expéditeur a fait l'étiquette. Purolator n'a pas encore le colis. |
| `Informations de l'étiquette transmises par voie électronique` | `Label information electronically submitted` | Pareil : les données de l'étiquette sont chez Purolator, le colis non. |
| `Cueilli par Purolator` | `Picked up by Purolator` | Purolator a pris le colis en charge. |
| `Arrivé au centre de tri` | `Arrived at sort facility` | Il est arrivé à un terminal Purolator ; le lieu indiqué est la ville et la province du terminal. |
| `A quitté le centre de tri` | `Departed sort facility` | Il repart vers le terminal suivant ou vers ton dépôt local. |
| `Dans un véhicule pour livraison` | `On vehicle for delivery` | Il est dans le camion aujourd'hui. |
| `Tentative de livraison - destinataire non disponible` | `Attempted delivery - receiver unavailable` | Le livreur est passé et personne n'a pu prendre le colis. |
| `Correction de l'adresse requise - code d'entrée manquant` | `Address correction required - missing entry code` | Le livreur n'a pas pu entrer dans ton immeuble. |
| `Disponible pour cueillette pendant 5 jours ouvrables à partir de la date d'arrivée au comptoir` | `Available for pickup for 5 business days from arrival date at the counter` | Il t'attend à un comptoir. |
| `Envoi livré` | `Shipment delivered` | Livré chez toi, ou récupéré par toi au comptoir. |

`Correction d'adresse requise - résolution en cours` veut dire que Purolator corrige l'adresse. Si `Livraison prévue pour le prochain jour ouvrable` suit, le colis a repris sa route.

D'autres scans ne sont donnés ici que dans leur version anglaise. `Delayed in transit due to missed connection` et `Delayed in transit due to rail delay` signalent un retard dans le réseau de Purolator lui-même (correspondance manquée, retard ferroviaire) ; le colis continue d'avancer. `Cleared customs` : la douane l'a libéré. `Shipment unavailable - En route to Purolator pickup location` : il est en route vers le comptoir qui va le garder, et tu ne peux pas encore le récupérer.

## Tentative de livraison Purolator, cueillette et signature

Quand personne n'est là, le suivi affiche d'habitude la tentative de livraison, puis `Disponible pour cueillette pendant 5 jours ouvrables à partir de la date d'arrivée au comptoir` dans la même ville, puis `Envoi livré` quand tu as récupéré le colis. Ce délai de 5 jours ouvrables est écrit dans le statut de Purolator lui-même : ne tarde pas.

Les comptoirs qui gardent les colis sont les Centres d'expédition Purolator, les agents d'expédition autorisés et les magasins PostNet. Le Guide des services de Purolator compte plus de 130 Centres d'expédition et plus de 550 agents autorisés au Canada ; son [outil de recherche](https://www.purolator.com/fr/expedition/trouver-un-centre-dexpedition) les trouve.

La signature dépend de ce que l'expéditeur a choisi :

- **Sans signature** : c'est la règle par défaut pour une livraison résidentielle. « Signature requise » est une option de l'expéditeur.
- **Signature d'adulte requise** : tu montres à la livraison une pièce d'identité avec photo émise par le gouvernement qui prouve ton âge. Sinon, le colis ne t'est pas remis et part à un Centre d'expédition ou chez un agent, où il te faut une pièce d'identité avec photo du gouvernement et une adresse valide. Ces colis ne sont jamais laissés à la porte.

L'expéditeur peut aussi choisir « À retenir pour cueillette » : le colis va directement dans un dépôt Purolator au Canada au lieu de ton adresse.

## Suivi Purolator qui ne bouge pas, colis en retard ou perdu

Aucun résultat, ça veut souvent dire que Purolator n'a pas encore scanné le colis : selon son aide, le suivi n'affiche des détails qu'une fois le colis reçu et scanné. Vérifie aussi les chiffres : un seul chiffre de travers suffit pour ne rien trouver.

Les retards apparaissent dans l'historique comme des scans à part (correspondance manquée, retard ferroviaire) ; l'ancienne aide de Purolator cite aussi la météo et les problèmes mécaniques. Le colis repart ensuite. Voir aussi [suivi de colis qui ne bouge plus](guide:tracking-not-updating).

Perdu, abîmé ou indiqué livré sans être là ? Contacte d'abord la boutique : c'est elle qui a le contrat avec Purolator, et elle peut t'expliquer comment se passe une réclamation.

Pour un achat aux États-Unis ou au Mexique livré par messagerie, l'Agence des services frontaliers du Canada (ASFC) prévoit une remise des droits de douane si la valeur est de 150 $ CA ou moins, et des taxes de vente et d'accise à 40 $ CA ou moins. Diviser une commande en plusieurs colis pour rester sous ces seuils n'est pas permis. C'est l'importateur qui paie les droits et taxes, et l'ASFC ne réglemente pas les frais de courtage : ils sont propres au transporteur. Plus de détails dans [colis bloqué en douane](guide:customs).

## Service à la clientèle Purolator : numéro de téléphone

Le numéro de Purolator au Canada, d'après son Guide des services, est le **1 888 SHIP-123 (1 888 744-7123)**. Garde ton NIC sous la main : Purolator le demande pour toute question sur un envoi.

Tu reçois un texto au sujet d'une tentative de livraison, avec un lien ? Ne clique pas : entre toi-même ton NIC dans l'outil de repérage de Purolator. Le gouvernement du Canada conseille de transférer les pourriels reçus par texto au 7726 (SPAM).

## Suivre un colis Purolator avec Peek

[Peek](/) reconnaît tout de suite Purolator pour 3 lettres et 9 chiffres. Un numéro de 12 chiffres est partagé : s'il commence par 0 à 6 et que son chiffre de contrôle est bon, Peek interroge cinq transporteurs en même temps, dont Purolator (en tête pour qui lit depuis le Canada ou les États-Unis), et retient celui qui connaît le numéro. Si des transporteurs de marques différentes le connaissent, ou aucun, choisis Purolator avec « Changer de transporteur ». Les liens de purolator.com qui contiennent `pin=`, `pins=` ou `searchValue=` fonctionnent aussi.

Peek affiche le statut, chaque scan avec sa ville, sa province et son pays sur une carte, le jour de livraison prévu, le pays de destination et, pour un envoi d'un seul colis, le poids. Peek lit le suivi de Purolator en anglais : les statuts restent dans l'anglais de Purolator, tels quels, même sur ta page en français. Le nom du destinataire est laissé de côté. Le comptoir qui garde un colis n'apparaît pas (seulement le statut `Available for pickup…`), et la livraison ne peut pas être modifiée depuis Peek.

Les heures sont celles des scans de Purolator, affichées dans le fuseau horaire de ton appareil. Pour un envoi transfrontalier, Purolator les envoie sans fuseau horaire : Peek les lit alors à l'heure de la ville du scan, et l'historique de Purolator reste affiché. Le site de Purolator répond aussi parfois par une vérification au lieu des données ; des services de suivi prennent alors le relais. Peek vérifie toutes les 10 minutes de 8 h à 22 h, heure suisse (environ de 2 h à 16 h HE et de 23 h à 13 h HP), toutes les 2 minutes pendant ces heures une fois le colis en livraison, toutes les une à deux heures le reste du temps, et peut t'envoyer des notifications à chaque scan, pour les étapes importantes ou avec l'option « Livraison seulement ».

## Questions sur le suivi Purolator

### Combien de temps Purolator garde-t-il un colis ?

5 jours ouvrables à partir de son arrivée au comptoir, comme le dit le statut de Purolator. Apporte une pièce d'identité avec photo émise par le gouvernement si le colis demande une signature d'adulte.

### Le suivi Purolator existe-t-il en français ?

Oui. Sur purolator.com en français, l'outil de repérage affiche les statuts en français, comme `Envoi livré` ou `Dans un véhicule pour livraison`.

### Le NIC Purolator, c'est le numéro de suivi ?

Oui. NIC est le nom que Purolator donne à son numéro de suivi, en général 12 chiffres ou 3 lettres et 9 chiffres.

### Le texto de tentative de livraison Purolator est-il une arnaque ?

Ne te fie pas au lien du texto : vérifie toi-même ton NIC dans l'outil de repérage de Purolator. S'il n'y a aucune tentative dans le suivi, transfère le texto au 7726.

### Peut-on suivre un colis Purolator sans le NIC ?

Seulement avec la référence de suivi de l'expéditeur, s'il en a créé une : l'outil de repérage l'accepte comme un NIC. Sinon, demande le NIC à la boutique.

:::sources
- [Purolator : Repérage (site en français)](https://www.purolator.com/fr/) – statuts en français, statut résumé, délai de 5 jours ouvrables (lus par son service de suivi)
- [Purolator : Guide des services de Purolator](https://web.purolator.com/app/guides/pdf/fr/Purolator_Service_Guide_French.pdf) – téléphone, Centres d'expédition et agents, À retenir pour cueillette, signatures, signature d'adulte
- [Purolator : Détails du suivi](https://eshiponline.purolator.com/ShipOnline/Help/Default/trackingdetails_F.htm) – aucun scan avant la prise en charge, nouveau NIC, retards dus à la météo
- [Purolator : Repérage - Par NIC / Par référence](https://eshiponline.purolator.com/ShipOnline/Help/Default/trackingRef_F.htm) – suivi par NIC, référence de 15 caractères
- [Purolator : Créer l'envoi - Confirmation de l'envoi](https://eshiponline.purolator.com/ShipOnline/Help/Default/shipstep3_F.htm) – définition du NIC
- [Purolator : Tracker](https://www.purolator.com/en/shipping/tracker) – statuts en anglais, formes du NIC
- [ASFC : Mémorandum D8-2-16](https://www.cbsa-asfc.gc.ca/publications/dm-md/d8/d8-2-16-fra.html) – seuils de 150 $ et 40 $ par messagerie, pas de fractionnement
- [ASFC : Guide d'importation, étape 4](https://www.cbsa-asfc.gc.ca/import/guide-4-fra.html) – l'importateur paie, frais de courtage non réglementés
- [Gouvernement du Canada : Mobile spam](https://ised-isde.canada.ca/site/canada-anti-spam-legislation/en/hung-mobile-spam) – transférer les pourriels par texto au 7726
- [Wikipédia : Purolator](https://fr.wikipedia.org/wiki/Purolator) – 91 % Postes Canada
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – ce que Peek lit et affiche
:::
