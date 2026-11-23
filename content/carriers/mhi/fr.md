---
title: Suivi MHI : statuts Trackmail et transporteur final
description: Un numéro MHI reçu d’une boutique en ligne ? Où le suivre, signification des statuts Trackmail, qui livre dans ton pays et qui contacter en cas de blocage.
slug: suivi-mhi
published: 2026-11-23
updated: 2026-11-23
---

MHI (Mail Handling International) est une entreprise de Bristol qui expédie vers l’étranger les commandes de boutiques en ligne britanniques. Les boutiques envoient leurs colis à son centre de traitement de Bristol ; MHI les exporte puis remet chacun à un transporteur ou à une poste de ton pays, qui le livre sous son propre numéro. Suis le numéro MHI sur le site de suivi de MHI, [Trackmail](https://www.trackmail.co.uk/), ou dans le champ ci-dessus. Le numéro vient de la boutique : MHI attribue un code de suivi par colis à ses clients, cherche-le donc dans l’e-mail d’expédition ou le détail de ta commande.

## Numéro de suivi MHI : à quoi il ressemble

MHI ne publie aucun format destiné aux acheteurs. Voici les formats que prennent ses numéros, avec des chiffres inventés :

| Numéro | Ce que c’est |
| --- | --- |
| `MHI482W736405829173` | MHI, trois chiffres, puis W et 12 chiffres : MHI. |
| `MHI482WBB736405829` | MHI, trois chiffres, puis WBB et 9 chiffres : MHI. |
| `MHI-482-P736405829` | MHI, trois chiffres, puis P et 9 chiffres : MHI. Les étiquettes l’impriment avec des tirets. |
| `MHI482736405829173` | MHI, trois chiffres, puis 12 autres chiffres : MHI. |
| `RR482917367US` | Deux lettres, 9 chiffres, deux lettres : le numéro postal du même envoi, que Trackmail affiche pour certains envois non suivis. |
| Tout autre format | Le propre numéro du transporteur livreur, affiché sur Trackmail après « Tracking number: » pour un envoi suivi. |

Les trois chiffres après MHI identifient le compte de la boutique chez MHI. Saisis un numéro P sur Trackmail avec ses tirets, tel qu’imprimé : Trackmail ne le retrouve qu’avec eux. Un même colis peut donc avoir deux numéros, celui de MHI et celui du transporteur. Plus de détails dans [formats de numéros de suivi](guide:tracking-number-formats).

## Statuts de suivi MHI expliqués

Trackmail est en anglais. Au-dessus de l’historique, il indique le service choisi par la boutique : « The seller requested this item be shipped via the following service: », puis `TRACKED` ou `UNTRACKED`. En dessous viennent les événements, du plus récent au plus ancien, chacun avec date, heure et libellé, la plupart avec une catégorie et un lieu : le scan d’exportation de MHI, puis les scans du transporteur formulés par MHI. Dans l’ordre où un colis les traverse :

| Trackmail affiche | Ce que ça signifie pour toi |
| --- | --- |
| `Your order has been processed at the UK export facility. It is now on route to the receiver.` | MHI a traité le colis au Royaume-Uni et l’a expédié vers l’étranger. Sur un envoi non suivi, c’est souvent l’unique ligne. |
| `Shipment information sent to last mile service provider` | Le transporteur de ton pays a reçu les données du colis, pas encore le colis lui-même. |
| `Notification sent to recipient` | Le transporteur t’a prévenu que le colis est en route. |
| `Handed Over to Delivery Partner` | Le transporteur a pris le colis en charge. |
| `Arrived at sorting center` | Scan de dépôt sur le réseau du transporteur. |
| `Dispatched from sorting center` | Le colis a quitté ce dépôt. |
| `Assigned to Transport` | En cours de transport routier entre deux dépôts. |
| `Shipment at final delivery center` | Le dépôt chargé de la livraison a le colis. |
| `Delivery scheduled in the final delivery depot` | Le colis est prévu sur la tournée de livraison du jour. |
| `Delivery attempted` | La livraison a échoué : personne n’a réceptionné le colis. |
| `Arrived at Pick-up Point` | Il t’attend dans un point de retrait. |
| `Successfully delivered` | Livré ou retiré. |

Lis le texte, pas la catégorie qui l’accompagne. Trackmail classe `Shipment at final delivery center` et `Arrived at Pick-up Point` sous « Out for delivery », alors que le premier précède la tournée et que le second signifie qu’il faut aller le retirer. Il classe aussi `Delivery attempted` sous « Delivery », comme une vraie livraison. Une tournée et une tentative peuvent se répéter sur plusieurs jours.

Les heures sur Trackmail n’ont pas de fuseau horaire : le scan de MHI est à l’heure britannique, ceux du transporteur à son heure locale.

Pour un numéro inconnu, Trackmail affiche une seule ligne commençant par `No details found for Tracking Code, or Tracking Code not recognised.` et précise que si ta commande est récente, MHI n’a peut-être pas encore les données.

## Livraison MHI : qui apporte ton colis

MHI ne livre pas elle-même à l’étranger. Elle indique travailler avec plus de 100 transporteurs pour couvrir 252 pays et territoires. Dans son centre de Bristol, le code-barres de chaque colis est scanné et le système de MHI sélectionne le transporteur du dernier kilomètre selon le pays de destination. Les boutiques peuvent choisir un service suivi, non suivi ou express : qui te livre dépend donc de ton pays et de l’option choisie par le vendeur.

**Envois suivis.** Trackmail peut afficher le numéro du transporteur livreur après « Tracking number: », accompagné d’un lien « View carrier tracking » vers son site. Pour toute question, Trackmail t’invite à utiliser ce lien ou à contacter ton commerçant.

**Envois non suivis.** Trackmail indique que seul un suivi limité est disponible et n’affiche souvent que le scan d’exportation de MHI. Pour certains envois, il fournit un « local poster number » : en le donnant à ta poste locale ou à ton bureau de poste, indique Trackmail, ils pourront peut-être t’en dire plus. Essaie-le aussi sur le suivi de ta poste : [USPS](carrier:usps), [Postes Canada](carrier:canada-post), [Australia Post](carrier:australia-post) ou An Post.

Les avis de passage, tentatives, nouvelles présentations et points de retrait dépendent des règles du transporteur livreur : vérifie son suivi via « View carrier tracking », et consulte [livraisons manquées](guide:missed-delivery).

## Colis MHI bloqué, perdu ou retourné

**`No details found…`.** Vérifie le numéro et saisis un numéro P avec ses tirets. Si tu as commandé récemment, MHI n’a peut-être pas encore les informations : réessaie plus tard.

**Uniquement le scan d’exportation.** Sur un service non suivi, Trackmail peut s’arrêter là : utilise le « local poster number » auprès de ta poste s’il y en a un, ou demande à la boutique. Sur un envoi suivi, le transporteur ne l’a peut-être pas encore scanné ; une fois son numéro affiché, son propre suivi peut être plus à jour. Voir [pourquoi le suivi ne se met plus à jour](guide:tracking-not-updating).

**Livré mais absent.** C’est le transporteur final qui a effectué la livraison : vérifie son suivi via « View carrier tracking », puis contacte la boutique. Voir [colis indiqué livré mais non reçu](guide:delivered-not-received).

**Perdu ou endommagé.** Contacte la boutique. MHI travaille pour la boutique, ne publie pas de procédure de réclamation pour les destinataires, et Trackmail lui-même renvoie vers le commerçant. Voir [colis perdus](guide:lost-parcel).

**Douane.** MHI propose aux boutiques plusieurs régimes douaniers, dont DDP (Delivered Duty Paid) : savoir s’il reste quelque chose à payer dépend donc du choix du vendeur. Les frais éventuels sont détaillés dans [colis retenus en douane](guide:customs).

**Retourné.** Le délai de garde d’un colis non retiré avant son retour dépend du transporteur final : consulte son suivi. S’il repart, demande un remboursement ou un nouvel envoi à la boutique. Voir [retourné à l’expéditeur](guide:returned-to-sender).

## Numéro de téléphone et service client MHI

Coordonnées publiées par MHI :

- **Questions de suivi :** e-mail tracking@mhi.co, indiqué sur sa page eCommerce delivery et sur la page de contact de Nexae (sa plateforme d’expédition).
- **Téléphone :** +44 (0) 117 977 6655, un numéro britannique affiché avec son adresse de Bristol, 82 Feeder Road, BS2 0TQ. MHI ne publie pas d’horaires et ne précise pas traiter les questions de colis sur cette ligne.
- **Formulaire Enquiries :** réservé aux demandes générales. MHI précise que les demandes de suivi envoyées via ce formulaire ne sont ni reprises ni traitées.

Le conseil de MHI aux acheteurs : vérifier sur Trackmail ou contacter le vendeur ou la place de marché. La boutique est la cliente de MHI et peut la relancer ; pour une livraison manquée ou un retrait, adresse-toi au transporteur indiqué sur Trackmail.

## Suivre un colis MHI avec Peek

[Peek](/) reconnaît un numéro MHI à sa forme, dans les quatre séries ci-dessus, avec ou sans tirets. Les anciennes étiquettes ne comptant que deux chiffres après MHI, ou huit chiffres à la fin, ne sont pas recherchées : consulte-les sur Trackmail. Tu peux aussi coller un lien Trackmail contenant le numéro ; Peek le reconnaît comme MHI lorsque le numéro correspond au format MHI.

Peek lit Trackmail et affiche le statut ainsi que l’historique, avec le lieu de chaque scan sur une carte. Trackmail ne donne ni date de livraison prévue, ni poids, ni preuve de livraison, ni nom de signataire : Peek n’en obtient donc aucun de MHI, et il exclut ton adresse ainsi que le code postal présent dans le lien du transporteur.

Lorsque le lien de Trackmail renvoie vers un transporteur connu de Peek, la page du colis affiche « Livraison par » suivi de ce transporteur et montre son numéro. Tant que ce transporteur n’a que l’annonce du colis, sans scan propre, la page indique à la place « Annoncé à …, pas encore reçu ». Si Peek lit directement le suivi de ce transporteur, il l’interroge avec ce numéro et bascule dessus dès qu’il affiche une étape plus récente. Un transporteur inconnu de Peek n’est pas nommé : utilise « View carrier tracking » sur Trackmail. Pour un envoi non suivi comportant un « local poster number », Peek peut tester ce numéro auprès d’une poste nationale, en général celle du pays depuis lequel tu ajoutes le colis, et basculer de la même façon. Pas avec toutes les postes toutefois : un numéro `RR…` finissant par `AU` ajouté depuis l’Australie n’est pas transmis à Australia Post, vérifie-le donc directement sur le suivi d’Australia Post.

Tant que tu gardes le colis ouvert dans Peek, ou le jour de la livraison si les notifications sont activées, Peek vérifie jusqu’à toutes les 10 minutes, et à partir de 6 h (heure locale) le jour où le colis passe en livraison jusqu’à toutes les 2 minutes tant que Trackmail (ou le suivi direct du transporteur qui prend le relais) répond. Après deux jours sans nouvelle (12 heures tant que le colis reste à « Étiquette créée » ou « Pris en charge »), Peek passe à une vérification toutes les heures ; et s’il reste à « Étiquette créée » pendant sept jours, toutes les 6 heures.

Si un colis encore à « Étiquette créée », « Pris en charge » ou « En transit » reste quatre jours sans nouveau scan, Peek le signale par « Aucune mise à jour depuis quatre jours » (ou « Aucune mise à jour depuis … »), explique qu’« Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger MHI. » et propose « Demande de l’aide à MHI ». Tu peux activer les notifications « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek ne peut pas contacter MHI, le transporteur ni la boutique à ta place.

## Questions sur le suivi MHI

### Où est mon colis MHI ?

La ligne la plus récente sur Trackmail indique son étape, et les scans du transporteur indiquent une ville. Avant `Handed Over to Delivery Partner`, c’est MHI qui l’a ou il est en route vers ton pays ; après cela, c’est le transporteur final, et sur un envoi suivi son numéro apparaît sur Trackmail après « Tracking number: ».

### Que signifie « processed at the UK export facility » ?

MHI a traité le colis dans son centre d’exportation au Royaume-Uni et l’a expédié vers toi. C’est le scan propre de MHI, et sur un service non suivi c’est souvent le seul.

### Le site trackmail.co.uk est-il fiable ?

Oui. Trackmail est le site de suivi de MHI : le lien « Track my Parcel » sur le site officiel de MHI y mène directement. Companies House recense Mail Handling International Limited comme entreprise active, immatriculée en 1996 à l’adresse de Bristol indiquée par MHI.

### MHI a-t-elle un numéro de service client ?

Elle indique le +44 (0) 117 977 6655, mais pour le suivi elle demande d’écrire à tracking@mhi.co ou de contacter la boutique. Pour la livraison elle-même, c’est au transporteur nommé sur Trackmail qu’il faut s’adresser.

:::sources
- [MHI: Home](https://www.mhi.co/) – lien Track my Parcel vers Trackmail, demandes colis vers le vendeur, formulaire général non destiné au suivi, plus de 100 transporteurs, 252 pays et territoires, suivi, non suivi et express, DDP, adresse, téléphone
- [MHI: eCommerce Delivery](https://www.mhi.co/ecommerce-delivery) – centre de Bristol, choix du transporteur par pays, codes de suivi pour les boutiques, export, tracking@mhi.co
- [Nexae: Contact Us](https://nexae.co.uk/ContactUsHomepage) – tracking@mhi.co, Nexae appartient à MHI
- [MHI: Trackmail](https://www.trackmail.co.uk/) – site de suivi de MHI, en anglais, contact suivi
- [Companies House: Mail Handling International Limited](https://find-and-update.company-information.service.gov.uk/company/03241562) – société active, immatriculée le 23 août 1996, siège social au 82 Feeder Road, Bristol
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formats de numéros, libellés et catégories de Trackmail, ligne de service, lien transporteur, numéro postal local, texte de numéro inconnu, fuseaux horaires, ce que Peek affiche et quel transporteur ou quelle poste il interroge
:::
