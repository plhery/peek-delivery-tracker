---
title: Suivi GOFO Canada : numéros GFCA, statuts et retards
description: Suis un colis GOFO au Canada : format du numéro GFCA, signification des statuts dans Peek et démarches en cas de retard, d’absence ou de colis manquant.
slug: suivi-gofo-ca
published: 2026-11-23
updated: 2026-11-23
---

GOFO Canada est le réseau canadien de GOFO, un transporteur présent dans plusieurs pays qui utilise le même système de suivi que GOFO aux États-Unis. GOFO indique que son réseau canadien est actif à Toronto, avec Vancouver et d’autres zones en préparation. Ses numéros commencent par `GFCA` : saisis le tien sur la page canadienne de suivi de gofo.com ou colle-le dans le champ ci-dessus. Pas encore de numéro ? Consulte [comment trouver ton numéro de suivi](guide:find-tracking-number).

## Numéro de suivi GOFO Canada : à quoi il ressemble

Peek attribue un numéro à GOFO Canada lorsqu’il suit ce format, en majuscules ou minuscules, avec ou sans espaces :

| Format (inventé) | Ce que c’est |
| --- | --- |
| `GFCA48291736405829` | `GFCA` suivi de 14 chiffres : le numéro de lettre de voiture GOFO Canada. Peek interprète une lettre `O` saisie juste après `GFCA` comme un zéro. |

GOFO attribue aussi à chaque colis un numéro de suivi qui reprend soit la lettre de voiture `GFCA`, soit la référence interne de la boutique. Peek suit uniquement la lettre de voiture `GFCA` : c’est donc ce numéro qu’il faut coller.

**Numéros similaires.** Les autres réseaux GOFO ont chacun leur propre préfixe : `GFUS` et 14 chiffres, `GF` et 13 chiffres, `CR` et 12 chiffres ou `MGP` et 13 chiffres appartiennent à [GOFO Express](carrier:gofo) aux États-Unis ; `GFFR` et `CIFR` à [GOFO France](carrier:gofo-fr) ; `GFIT` et `CIIT` à [GOFO Italy](carrier:gofo-it) ; `GFNL` et `GFES` aux réseaux néerlandais et espagnol. Aucun de ces préfixes n’est un numéro GOFO Canada. Plus d’aide pour les distinguer : [formats des numéros de suivi](guide:tracking-number-formats).

## Statuts de suivi GOFO Canada expliqués

GOFO Canada reprend les codes de scan de GOFO US, et Peek les traduit avec la même table. Un colis au Canada passe généralement par ces étapes, dans l’ordre :

| Scan GOFO | Étape dans Peek | Ce que cela signifie pour toi |
| --- | --- | --- |
| `Shipping Label Created` | Étiquette créée | La boutique a créé l’étiquette. GOFO n’a pas encore le colis. |
| `Arrived at GOFO Regional Hub` | En transit | Arrivé dans un centre régional GOFO. |
| `Loaded on Vehicle` | En transit | Chargé pour le trajet entre sites GOFO, vers l’agence de livraison. |
| `Arrived at GOFO Delivery Station` | En transit | Arrivé à l’agence GOFO chargée de la livraison. GOFO indique une livraison généralement sous 1 à 2 jours calendaires. |
| `Out for Delivery` | En livraison | En tournée avec le livreur. |
| `Delivered` | Livré | Livré. |

Les codes étant partagés, un libellé vu sur les colis américains s’affiche de la même façon au Canada. `Delivery Exception, Business Closed` signale un échec de livraison, affiché comme « Tentative de livraison » ; la raison après la virgule varie. `Label Cancelled` signifie que l’étiquette a été annulée après sa création et qu’aucun colis ne circule sous ce numéro ; Peek affiche « Problème signalé ».

GOFO ajoute parfois son téléphone et son e-mail d’assistance à certains scans ; Peek retire cette mention, tout comme la page de suivi officielle de GOFO. Un scan de livraison apparaît simplement comme `Delivered` : Peek omet le texte que GOFO ajoute après la virgule.

## Colis GOFO Canada en retard, manqué ou introuvable

**Aucun mouvement.** Si seul `Shipping Label Created` apparaît, GOFO n’a pas encore reçu le colis : la boutique doit encore le remettre. Peek continue d’interroger GOFO et te le signale si quatre jours passent sans nouveau scan avant la mise en livraison. Plus d’explications dans [suivi qui ne se met plus à jour](guide:tracking-not-updating).

**Livraison manquée.** Une tentative échouée s’affiche comme « Tentative de livraison » avec le motif indiqué par GOFO. GOFO précise qu’un colis déjà parti en livraison fait généralement l’objet d’une nouvelle tentative sous 24 heures ; en cas de problème d’adresse (numéro d’appartement manquant, par exemple), il peut retourner à l’entrepôt local le temps de vérifier l’adresse. La suite dépend de GOFO et de la boutique : consulte [livraison manquée](guide:missed-delivery). Si un SMS te demande de payer pour reprogrammer une livraison GOFO, lis [faux SMS de livraison](guide:delivery-scam-texts) avant de cliquer.

**Marqué livré mais non reçu.** Peek indique l’heure du scan de livraison, mais n’affiche ni la photo de preuve de livraison ni les précisions ajoutées par GOFO après la virgule. La page officielle gofo.com/ca/track affiche la preuve de livraison, le plus souvent une photo de l’endroit où le colis a été déposé, après saisie du nom du destinataire utilisé pour la commande. Vérifie d’abord autour de ton domicile (boîte aux lettres, local courrier commun), auprès d’un voisin, d’un proche ou du concierge ou service de sécurité de l’immeuble. Si le colis reste introuvable, signale-le à GOFO (contacts ci-dessous) avec si possible une photo de l’extérieur de ton logement ou immeuble, et contacte la boutique avec ton numéro de commande et le numéro `GFCA`. Voir [colis marqué livré mais non reçu](guide:delivered-not-received).

**Retard ou perte.** Contacte la boutique qui a expédié la commande : elle détient tes informations de commande et d’envoi, et GOFO indique que l’expéditeur peut ouvrir une enquête auprès de GOFO pour toi. Voir [colis perdu](guide:lost-parcel). Si le colis repart chez l’expéditeur, consulte [retour à l’expéditeur](guide:returned-to-sender) : GOFO précise qu’un colis en retour ne peut pas être intercepté, il faut donc demander un renvoi ou un remboursement à la boutique.

Commande passée sur Temu ou Shein ? Consulte [commandes Temu](guide:temu-orders) et [commandes Shein](guide:shein-orders). Pour les frais d’importation, voir [douane](guide:customs).

## Service client GOFO Canada

Pour une commande en retard, manquante ou endommagée, commence par la boutique avec ton numéro de commande et ton numéro `GFCA`. Pour un souci de livraison ou une question de suivi, GOFO indique ces canaux sur sa [page d’assistance canadienne](https://www.gofo.com/ca/customer-service) :

- **E-mail :** cs@mail.gofo.com
- **Téléphone (ligne destinataires) :** +1 (949) 688-6032, du lundi au vendredi de 9 h à 21 h, samedi et dimanche de 11 h à 19 h (heure de l’Est).
- **WhatsApp :** +1 (787) 469-5811, du lundi au vendredi de 9 h à 21 h, samedi et dimanche de 11 h à 20 h (heure de l’Est).
- **En ligne :** un formulaire de demande (avec numéro de lettre de voiture) et un chat, sur la même page.

## Suivre un colis GOFO Canada avec Peek

[Peek](/) reconnaît immédiatement un numéro GOFO Canada à sa forme : `GFCA` suivi de 14 chiffres, en majuscules ou minuscules, avec ou sans espaces. Un lien de suivi GOFO Canada sur gofo.com contenant `searchID=` et le numéro fonctionne aussi. Peek suit uniquement la lettre de voiture `GFCA`, pas la référence de la boutique.

Peek lit le suivi propre de GOFO Canada : le statut, chaque scan avec son heure ainsi que la ville et la province indiquées par GOFO, ces lieux sur une carte, l’heure de livraison et le pays de destination. Les scans gardent le libellé anglais de GOFO sans la mention d’assistance. Peek affiche la date de livraison estimée de GOFO tant que le colis n’est pas en livraison, livré, retourné ou en point de retrait, et omet les photos de livraison, les coordonnées GPS et le poids.

Tant que tu gardes le colis ouvert dans Peek, ou le jour de sa livraison avec les notifications activées, Peek vérifie jusqu’à toutes les 10 minutes — et toutes les 2 minutes à partir de 6 h (heure locale) le jour où GOFO scanne le colis en livraison, tant que le suivi propre de GOFO Canada répond. Après deux jours sans nouveauté (12 heures tant que le colis reste sur « Étiquette créée » ou « Pris en charge »), Peek vérifie toutes les heures, puis toutes les 6 heures après sept jours au statut « Étiquette créée ». Si un colis aux étapes « Étiquette créée », « Pris en charge » ou « En transit » reste quatre jours sans nouveau scan, Peek affiche « Aucune mise à jour depuis quatre jours » (ou « Aucune mise à jour depuis… » avec la date), « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger GOFO Canada. » et le lien « Demande de l’aide à GOFO Canada ». Peek peut t’alerter avec « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Il ne peut pas programmer de nouvelle livraison ni contacter GOFO ou la boutique à ta place.

## Questions sur le suivi GOFO Canada

### GOFO Canada est-il fiable ?

Corporations Canada répertorie GOFO EXPRESS CANADA INC. comme société fédérale active, numéro 1807362-7, dont le siège social est à Mississauga, en Ontario, prorogée sous la Loi canadienne sur les sociétés par actions en juillet 2026 depuis l’Ontario. GOFO exploite aussi des réseaux aux États-Unis, en France, en Italie, aux Pays-Bas et en Espagne, suivis séparément dans Peek.

### Que signifie « Loaded on Vehicle » ?

GOFO a chargé le colis pour le transport entre deux de ses sites, avant qu’il n’atteigne l’agence locale qui assure la livraison. L’étape suivante est généralement `Arrived at GOFO Delivery Station`, puis `Out for Delivery`.

### Pourquoi mon numéro de suivi GOFO ne fonctionne-t-il pas ?

Vérifie d’abord le préfixe : un numéro GOFO Canada comporte `GFCA` et 14 chiffres. Un code `GFUS`, `GFFR` ou `GFIT` relève des réseaux américain, français ou italien de GOFO, suivis sous leur propre nom dans Peek. Si un numéro `GFCA` ne donne rien, vérifie-le dans l’e-mail d’expédition puis contacte la boutique.

:::sources
- [Corporations Canada : GOFO EXPRESS CANADA INC.](https://ised-isde.canada.ca/cc/lgcy/fdrlCrpDtls.html?corpId=18073627) – société fédérale active 1807362-7, prorogée d’Ontario le 8 juillet 2026, siège social à Mississauga
- [Universal Parcel Scraper : GOFO Canada](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/gofo-ca) – format de numéro, liens, libellés et étapes partagés avec GOFO US, suppression du texte d’assistance, ce que Peek garde ou omet, lien d’aide GOFO Canada
- [GOFO Canada : Customer Service](https://www.gofo.com/ca/customer-service) – e-mail, ligne destinataires et horaires, WhatsApp, formulaire et chat ; preuve de livraison, où vérifier, nouvelle tentative sous 24 heures, 1 à 2 jours depuis l’agence, enquêtes via l’expéditeur, impossibilité d’intercepter un retour
- [GOFO Canada](https://www.gofo.com/ca) – réseau actif à Toronto, Vancouver et nouvelles zones en préparation
:::
