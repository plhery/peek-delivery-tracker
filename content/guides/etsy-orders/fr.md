---
title: Suivi Etsy : où est ta commande et qui la livre
description: Pourquoi ta commande Etsy affiche encore « Non envoyé », quel transporteur la livre, à quoi ressemble son numéro de suivi et quand ouvrir un litige.
slug: suivi-commande-etsy
picture: Un colis ficelé et timbré, une camionnette postale et Pip qui file à toute allure.
published: 2026-10-17
updated: 2026-10-17
---

Etsy n'expédie rien lui-même : chaque boutique fabrique ou emballe ses commandes, les confie au transporteur de son choix et ajoute un numéro de suivi quand le service en a un. Ta page Achats te dit si la commande a quitté la boutique et quand elle doit arriver ; le numéro de suivi te dit où elle est maintenant. En retard ou introuvable ? Demande d'abord au vendeur, puis à Etsy.

## Pourquoi ma commande Etsy affiche encore « Non envoyé » ?

Parce que le vendeur ne l'a pas encore fabriquée, emballée ou marquée comme envoyée. Etsy calcule ta date de livraison estimée ainsi : « Temps de traitement + Temps de transit du transporteur = Date de livraison estimée ». Le temps de traitement, c'est le temps dont le vendeur a besoin pour créer l'article et le préparer avant de le remettre au transporteur. L'exemple d'Etsy : 3 à 5 jours de traitement et 2 à 4 jours de transit, soit une livraison dans 5 à 9 jours. Ton reçu indique aussi la date d'expédition à laquelle le vendeur s'est engagé.

Dans **Achats et avis** (appli : **Vous** → **Achats**), chaque commande affiche l'un de ces statuts :

| Statut | Ce qu'il veut dire |
| --- | --- |
| `Non envoyé` | Le vendeur n'a pas encore expédié la commande, ou ne l'a pas mise à jour sur Etsy |
| `Envoyé` | Le vendeur a terminé la commande sur Etsy |
| `Pré-transit` | Un numéro de suivi est ajouté, mais le transporteur n'a pas encore scanné le colis |
| `En transit` | Le transporteur l'a, il est en route vers toi |
| `Livré` | Il est arrivé, et tu peux laisser un avis |

`Envoyé` ne veut pas toujours dire qu'un transporteur a le colis. Un vendeur peut fixer une date d'envoi jusqu'à 3 jours à l'avance, et Etsy te prévient à cette date. Avec les seules étiquettes Asendia, Evri, FedEx, USPS (hors lettres et plis) ou UPS achetées sur Etsy, l'e-mail attend le premier scan du transporteur. Si la date estimée est passée et que la commande affiche toujours `Non envoyé`, Etsy dit que le vendeur a peut-être oublié de la marquer comme terminée, ou ne l'a pas encore expédiée : demande-lui via **Aide concernant une commande**.

Où trouver le numéro sur Etsy et dans l'e-mail d'expédition : [trouver ton numéro de suivi](guide:find-tracking-number).

## Quel transporteur livre une commande Etsy ?

Celui que le vendeur a choisi. Etsy ne vend des étiquettes d'expédition qu'aux vendeurs des États-Unis, du Canada et du Royaume-Uni ; les autres achètent leur affranchissement eux-mêmes, à la poste ou chez un transporteur. Pour les envois à l'étranger, Etsy cite les transporteurs courants dans chaque pays :

| Vendeur au | Étiquettes vendues sur Etsy | Transporteurs cités par Etsy pour l'étranger |
| --- | --- | --- |
| États-Unis | USPS, [FedEx](carrier:fedex), [UPS](carrier:ups) (aux États-Unis seulement), Global Postal Shipping | [DHL Express](carrier:dhl-express), FedEx, UPS, USPS |
| Royaume-Uni | Royal Mail, Evri | DHL, Evri, Royal Mail, UPS |
| Canada | [Postes Canada](carrier:canada-post), Chit Chats | Postes Canada, Chit Chats, DHL, FedEx, UPS |
| France | Aucune | Colissimo, [Chronopost](carrier:chronopost), DHL, FedEx, [La Poste](carrier:la-poste), UPS |
| Belgique | Aucune | [bpost](carrier:bpost) |
| Suisse | Aucune | [La Poste suisse](carrier:swiss-post) |
| Chine, Hong Kong | Aucune | [Yanwen](carrier:yanwen), [YunExpress](carrier:yunexpress), [4PX](carrier:4px), et SF Express depuis la Chine |

Si le vendeur a utilisé un transporteur directement pris en charge par Etsy, un bouton **Suivre le colis** apparaît à côté de la commande. Sinon, le numéro n'est visible que dans **Voir le reçu**, et tu le suis sur le site du transporteur.

Depuis le 1er juillet 2026, Etsy ne vend temporairement plus d'étiquettes USPS vers la France, la Belgique et la Suisse, ni d'étiquettes Postes Canada vers la France et la Belgique, « en raison de nouveaux frais de traitement de l'UE qui concernent certains transporteurs », écrit Etsy. Les vendeurs américains passent alors par Global Postal Shipping ou achètent leur étiquette ailleurs. Les colis venus de Chine suivent leur propre chemin : voir [suivre un colis venant de Chine](guide:tracking-from-china).

## À quoi ressemble un numéro de suivi Etsy ?

C'est le numéro du transporteur, pas un numéro Etsy. Les postes utilisent le format postal international : 13 caractères qui restent les mêmes d'un pays à l'autre.

:::anatomy RR 00000000 5 US
- RR | Deux lettres pour le type d'envoi et le service
- 00000000 | Numéro de série, huit chiffres
- 5 | Chiffre de contrôle, calculé à partir du numéro de série
- US | Pays de la poste qui l'a attribué : les États-Unis
:::

Les formes que Peek connaît pour les transporteurs ci-dessus, avec des chiffres inventés :

| Le numéro ressemble à | En général |
| --- | --- |
| Deux lettres, neuf chiffres, puis `FR`, `BE`, `CH`, `US` ou `GB` | La Poste, bpost, La Poste suisse, USPS ou Royal Mail |
| `XR`, `XT`, `XU`, `XW`, `XY` ou `PZ`, neuf chiffres, deux lettres | Chronopost |
| Un chiffre, une lettre, puis 11 chiffres | Colissimo |
| `98` ou `99`, puis 16 chiffres | La Poste suisse |
| 18 ou 24 chiffres commençant par `3232` | bpost |
| 22 chiffres commençant par `91` à `95`, comme `9400111111111111111114` | USPS |
| `1Z` + 16 lettres et chiffres | UPS |
| 12 ou 15 chiffres | FedEx |

Tu ne sais pas lequel tu as ? Tape-le ici :

:::checker
:::

Tous les autres formats, transporteur par transporteur : [formats de numéros de suivi](guide:tracking-number-formats).

## Pourquoi mon suivi Etsy s'arrête ou change ?

En général pour l'une de ces raisons :

- **Le colis a passé une frontière.** Etsy prévient que « certains envois internationaux ou transfrontaliers n'afficheront pas de suivi une fois que la commande aura quitté le pays d'origine ». Le suivi devrait reprendre quand le colis arrive dans ton pays.
- **Ta poste a pris le relais.** Avec un numéro postal, la poste du vendeur envoie le colis à l'étranger et ta poste le livre. L'Union postale universelle n'autorise « qu'un seul identifiant S10 » par envoi : le même numéro marche donc en général aussi sur le site de ta poste.
- **Deux étiquettes, un seul trajet.** Avec Global Postal Shipping d'Etsy, un vendeur américain envoie le colis avec une étiquette USPS à Asendia, qui y colle l'étiquette internationale. Etsy réunit les deux parties en un seul historique dans Achats et avis, et ses e-mails indiquent « Tracked on Etsy ».
- **Le vendeur a ajouté ou corrigé le numéro tard.** Un vendeur peut modifier le numéro pendant 3 jours après avoir terminé la commande ; ensuite, il t'envoie le bon par message. Il peut aussi ajouter un colis plus tard, dont le numéro s'affiche à côté du premier.
- **Plusieurs commandes, un seul carton.** Si le vendeur regroupe plusieurs de tes commandes, elles ont toutes le même numéro.

:::journey
- shop | Créateur | Le fabrique et l'emballe : `Non envoyé` jusque-là
- label | Étiquette | `Pré-transit` : un numéro, pas encore de scan
- truck | Poste d'origine | Premiers scans dans le pays du vendeur
- plane | Vol | Le suivi se tait souvent ici
- customs | Ta douane | Contrôle, parfois avec des frais
- handover | Ta poste | Le livre, en général sous le même numéro
- home | Toi | `Livré`
:::

Combien de temps un silence peut durer : [pourquoi le suivi ne bouge plus](guide:tracking-not-updating).

### Pourquoi il n'y a aucun numéro de suivi ?

Tous les services n'en ont pas. Etsy le dit clairement : « Les différents modes d'expédition n'incluent pas tous des informations de suivi. » Pour les lettres et plis envoyés des États-Unis, les étiquettes d'Etsy ajoutent un suivi via Pitney Bowes, mais un pli trop rigide peut aussi ne pas avoir de suivi. Etsy exige un suivi des vendeurs américains, mais tout vendeur peut indiquer qu'une commande n'en a pas, en donnant une raison. Pas de numéro ? Écris au vendeur via **Aide concernant une commande** : il sait quel service il a utilisé et quand il a posté le colis.

## Vais-je payer la douane sur une commande Etsy venue de l'étranger ?

Etsy dit que l'acheteur est « en général responsable » des droits de douane, taxes et frais de dédouanement, le plus souvent dus à l'arrivée du colis. Dans l'UE, Etsy en prend une partie au moment du paiement :

| Commande | Ce qui s'applique |
| --- | --- |
| D'un pays de l'UE vers la France ou la Belgique | La TVA est comprise dans le prix, tu ne paies rien de plus |
| De hors UE, moins de 150 € | Etsy facture la TVA au paiement. S'y ajoutent 3 € de droits de douane par article, au paiement ou à la livraison |
| De hors UE, plus de 150 € | TVA et droits de douane peuvent être dus à la livraison, sauf si le vendeur a choisi un envoi avec taxes et droits prépayés |
| Vers la Suisse | Etsy ne donne pas de règle. Selon l'OFDF, la poste ou le transporteur se charge en général de la déclaration en douane. La TVA est de 8,1 %, ou 2,6 % pour certains biens ; les montants de taxe ou de droits inférieurs à 5 francs ne sont pas perçus |

Un colis en retard ou jamais reçu parce que tu as refusé ou pas payé les frais dus à la livraison n'est pas couvert par la protection sur les achats d'Etsy. Plus sur les frais et les délais : [colis bloqué en douane](guide:customs).

## Que faire si ta commande Etsy est en retard ou perdue ?

Dans cet ordre :

:::steps
- Vérifie les dates | La date de livraison estimée figure sur la commande. Etsy la recalcule si le vendeur expédie plus tôt.
- Suis-la chez le transporteur | Avec Suivre le colis, ou avec le numéro de Voir le reçu sur le site du transporteur.
- Demande au vendeur | Achats et avis, puis Aide concernant une commande. Le vendeur peut vérifier le statut, changer l'adresse ou te rembourser.
- Ouvre un litige | 48 heures après ta demande, une fois la date estimée passée. Pour une commande jamais arrivée : plus de 7 jours après la date estimée la plus tardive.
- Respecte le délai | Tu as jusqu'à 30 jours après la date de livraison estimée.
:::

Avec le programme de protection sur les achats d'Etsy, une commande éligible est remboursée en entier si l'article n'arrive pas, arrive endommagé, ne correspond pas à la description, ou arrive plus de 7 jours après la date de livraison estimée la plus tardive indiquée à l'achat. Ta demande dans **Aide concernant une commande** affiche la date et l'heure à partir desquelles tu peux choisir **Ouvrir un litige**, et jusqu'à quand. Etsy ne peut traiter un remboursement que dans les 180 jours suivant l'achat.

Certains cas ne sont pas couverts : un retard dû à une grève du transporteur, à des conditions climatiques extrêmes, à une guerre ou à des troubles civils, des frais refusés à la porte et une commande déjà contestée auprès de ta banque. Tu as commandé en tant qu'invité ? Lie d'abord la commande à un compte Etsy. Si ton paiement est passé par PayPal hors Etsy Payments, ouvre plutôt un litige chez PayPal dans les 180 jours suivant l'achat.

### Il est indiqué « Livré », mais tu n'as rien reçu

Etsy conseille d'abord ces vérifications : demande aux personnes avec qui tu vis ou travailles, attends un jour si ce n'est pas urgent, contacte ton bureau de poste ou centre de distribution, et interroge tes voisins. Toujours rien ? Envoie une demande au vendeur via **Aide concernant une commande** : en général, c'est lui qui ouvre la réclamation auprès du transporteur pour toi. Ouvre ensuite un litige si le colis ne réapparaît pas. Plus d'infos dans [colis livré mais pas reçu](guide:delivered-not-received) et [colis perdu](guide:lost-parcel).

## Puis-je suivre une commande Etsy en dehors d'Etsy ?

Oui, avec le numéro du transporteur. Colle-le dans [Peek](/) : il reconnaît les numéros des postes et transporteurs ci-dessus. Pour un envoi postal parti à l'étranger, quand la poste d'origine indique le pays de destination sans nommer de partenaire, Peek peut proposer une recherche avec le même numéro auprès de la poste de ton pays : La Poste en France, La Poste suisse en Suisse. Il garde l'historique de la poste d'origine, et suit ta poste dès que son suivi montre de nouvelles étapes. Les démarches d'Etsy restent sur Etsy : Peek ne voit pas une commande `Non envoyé` et ne peut pas ouvrir de litige pour toi.

:::sources
- [Etsy : Quel est le statut de ma commande ?](https://help.etsy.com/hc/fr/articles/115015521948-Quel-est-le-statut-de-ma-commande) – statuts, Suivre le colis, Voir le reçu, suivi à l'étranger, pas de suivi, conseils si rien n'arrive
- [Etsy : Qu'est-ce qu'une date de livraison estimée ?](https://help.etsy.com/hc/fr/articles/360020601674-Qu-est-ce-qu-une-date-de-livraison-estim%C3%A9e) – traitement plus transit, exemple, 7 jours pour une non-livraison
- [Etsy : La configuration des temps de traitement, profils de traitement et dates d'expédition prévues](https://help.etsy.com/hc/fr/articles/115015588087-La-configuration-des-temps-de-traitement-profils-de-traitement-et-dates-d-exp%C3%A9dition-pr%C3%A9vues) – temps de traitement, date d'expédition sur le reçu
- [Etsy : L'ajout d'un suivi et la finalisation d'une commande](https://help.etsy.com/hc/fr/articles/115015774228-L-ajout-d-un-suivi-et-la-finalisation-d-une-commande) – date d'envoi jusqu'à 3 jours à l'avance, notification au premier scan, suivi obligatoire aux États-Unis, 3 jours pour modifier, colis ajoutés, commandes regroupées
- [Etsy : L'achat d'étiquettes d'expédition sur Etsy](https://help.etsy.com/hc/fr/articles/360001967188-L-achat-d-%C3%A9tiquettes-d-exp%C3%A9dition-sur-Etsy) – transporteurs, vendeurs au Canada, au Royaume-Uni et aux États-Unis, destinations de l'UE suspendues
- [Etsy : FedEx Shipping Labels on Etsy (en anglais)](https://help.etsy.com/hc/en-us/articles/360000337447-FedEx-Shipping-Labels-on-Etsy) – vendeurs américains
- [Etsy : UPS Shipping Labels on Etsy (en anglais)](https://help.etsy.com/hc/en-us/articles/18309523715735-UPS-Shipping-Labels-on-Etsy) – vendeurs américains, envois intérieurs
- [Etsy : Royal Mail Postage Labels on Etsy (en anglais)](https://help.etsy.com/hc/en-gb/articles/30907634892311-Royal-Mail-Postage-Labels-on-Etsy) – vendeurs au Royaume-Uni
- [Etsy : Evri Postage Labels on Etsy (en anglais)](https://help.etsy.com/hc/en-gb/articles/360002035507-Evri-Postage-Labels-on-Etsy) – vendeurs au Royaume-Uni
- [Etsy : Chit Chats Shipping Labels on Etsy (en anglais)](https://help.etsy.com/hc/en-us/articles/44210045773719-Chit-Chats-Shipping-Labels-on-Etsy) – vendeurs au Canada
- [Etsy : Global Postal Shipping Labels on Etsy (en anglais)](https://help.etsy.com/hc/en-us/articles/360037382894-Global-Postal-Shipping-Labels-on-Etsy) – Asendia, deux étiquettes, Tracked on Etsy
- [Etsy : How to Ship First-Class Flats and Letters (en anglais)](https://help.etsy.com/hc/en-us/articles/360039466434-How-to-Ship-First-Class-Flats-and-Letters-with-Etsy-Shipping-Labels) – suivi via Pitney Bowes, plis rigides
- [Etsy : Informations concernant la gestion des envois à l'international](https://help.etsy.com/hc/fr/articles/360001987487-Informations-concernant-la-gestion-des-envois-%C3%A0-l-international) – transporteurs courants par pays
- [Etsy : Devrai-je payer des taxes ou des droits de douane sur ma commande ?](https://help.etsy.com/hc/fr/articles/115015691007-Devrai-je-payer-des-taxes-ou-des-droits-de-douane-sur-ma-commande) – règles de l'UE, 150 €, 3 € par article
- [OFDF : Réception de lettres et de paquets](https://www.bazg.admin.ch/fr/reception-de-lettres-et-paquets) – déclaration par la poste ou le transporteur, TVA de 8,1 % et 2,6 %, montants inférieurs à 5 francs
- [Etsy : Le Programme de protection sur les achats d'Etsy](https://help.etsy.com/hc/fr/articles/7471925990807-Le-Programme-de-protection-sur-les-achats-d-Etsy) – ce qui est couvert, frais de douane, grèves et météo, invités
- [Etsy : Comment obtenir de l'aide concernant une commande](https://help.etsy.com/hc/fr/articles/4402660818583-Comment-obtenir-de-l-aide-concernant-une-commande) – Aide concernant une commande, ce que le vendeur peut faire
- [Etsy : L'ouverture d'un litige](https://help.etsy.com/hc/fr/articles/5745586898199-L-ouverture-d-un-litige) – 48 heures, 30 jours, 180 jours, PayPal, contestations bancaires
- [Etsy : What if My Buyer's Package Gets Lost? (en anglais)](https://help.etsy.com/hc/en-us/articles/360050349953-What-if-My-Buyer-s-Package-Gets-Lost) – réclamation ouverte par le vendeur
- [UPU : norme S10 (en anglais)](https://www.upu.int/UPU/media/upu/files/postalSolutions/programmesAndServices/standards/S10-12.pdf) – le format à 13 caractères, un seul numéro par envoi
- [Universal Parcel Scraper : données transporteurs (en anglais)](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers) – formes de numéros, ce que Peek vérifie
:::
