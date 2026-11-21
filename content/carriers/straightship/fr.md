---
title: Suivi Straightship : numéros STRTD, statuts et livraison
description: Suivez un colis Straightship au Canada : formats STRTD, STRCA et STSH, sens des statuts, tentative manquée, douane ASFC, commandes SHEIN et contact.
slug: suivi-colis-straightship
published: 2026-11-18
updated: 2026-11-20
---

Straightship (Straightship Solutions Limited) est une entreprise de logistique établie à Burnaby, en Colombie-Britannique (**Canada**), qui achemine des marchandises d’Asie vers l’Amérique du Nord et livre les colis à travers le Canada avec sa propre flotte de chauffeurs. Son site compte notamment SHEIN parmi ses partenaires. Si une commande en ligne est accompagnée d’un numéro commençant par `STRTD` ou `STRCA`, elle est prise en charge par Straightship ; un numéro commençant par `STSH` peut également lui appartenir. Suivez votre envoi sur [straightship.com](https://www.straightship.com/new/), dans l’encadré « Track Your Shipment », ou directement dans le champ ci-dessus. La boutique vous transmet ce numéro dans l’e-mail d’expédition ou sur la page de commande.

## Numéro de suivi Straightship : à quoi il ressemble

Straightship ne publie pas de règle officielle de numérotation. Les formats reconnus par Peek se présentent ainsi, avec des chiffres fictifs :

| Numéro | Format |
| --- | --- |
| `STRTD482910573` | `STRTD` suivi de 9 chiffres. |
| `STRTD4829105736491827` | `STRTD` suivi de 16 chiffres (des numéros à 17 chiffres existent aussi). |
| `STRCA4829105736498` | `STRCA` suivi de 13 chiffres. |
| `STSHYW48291057364` | `STSH`, deux lettres et 11 chiffres. |

Les minuscules et les espaces n’ont aucune incidence dans Peek. Lorsqu’un numéro est inconnu de Straightship, son suivi affiche `No results found!` : vérifiez alors le code dans l’e-mail de confirmation du vendeur. Vous ne trouvez pas le numéro ? Consultez [comment trouver votre numéro de suivi](guide:find-tracking-number) et, pour un achat SHEIN, [suivre une commande SHEIN](guide:shein-orders).

## Statuts de suivi Straightship expliqués

L’outil de suivi de Straightship est en anglais. Il présente un encadré récapitulatif contenant le champ « Package Status », au-dessus d’un historique indiquant l’heure, l’événement et la ville de chaque scan. Voici les messages habituels dans l’ordre où un colis les rencontre au **Canada** :

| Ce qu’affiche Straightship | Ce que cela signifie pour vous |
| --- | --- |
| `Package information is created in the system` | La boutique a créé l’étiquette. Straightship n’a pas encore le colis : il est toujours chez le vendeur ou en transit depuis l’Asie. |
| `Package Received with Origin Scan warehouse` | Premier scan de Straightship au Canada, avec la ville et la province. |
| `Package is received at a Straightship station` | Arrivée dans l’un des dépôts de Straightship (les anciens suivis affichent parfois `Arrived at Facility`). |
| `Package arrived at transit station` | Dans un dépôt de transit, en chemin vers votre dépôt local. |
| `Package is in transit to a Straightship station` | Sur la route entre deux dépôts, avant celui qui assurera la livraison. |
| `Loading` | Chargement au dépôt avant le départ en tournée (peut apparaître deux fois à quelques secondes d’intervalle). |
| `Package will be delivered before 9:00pm` | En livraison aujourd’hui : la remise ou une tentative suit le jour même. |
| `Attempted Delivery` | Le chauffeur s’est présenté mais n’a pas pu livrer le colis. |
| `Shipment delayed` | Retard signalé, sans motif précisé sur le suivi. |
| `Delivered` | Colis livré, avec la ville et la province (affiché « Livré » dans Peek). |
| `Package is being returned to shipper` | Le colis repart vers l’expéditeur. |

Deux messages d’échec précisent la marche à suivre : `An attempt was made to deliver your package-consignee closed (please e-mail SS@straightship.com to make arrangement)` signifie que le lieu de livraison était fermé ; la même phrase avec `unable to leave package at safe location` à la place de `consignee closed` indique que le chauffeur n’a trouvé aucun endroit sûr où déposer le colis. Dans les deux cas, Straightship demande d’écrire par courriel à `SS@straightship.com` pour convenir d’un nouveau passage.

Trois particularités prêtent souvent à confusion :

- **Le résumé prend du retard.** Le champ « Package Status » peut rester sur `ReadyForShipping` (ou `Ready For Shipping`) alors que le colis circule déjà d’un dépôt à l’autre. Fiez-vous à la ligne la plus récente de l’historique en dessous ; les valeurs suivantes du résumé sont `Shipped`, `Delivered` et `ReturnedToSender`.
- **« In progress » ne veut pas dire en tournée.** `Delivery is in progress` et `Your order is in Progress` apparaissent après une livraison qui n’a pas abouti, parfois plusieurs jours plus tard et avant un retour. Écrivez à Straightship si vous les voyez.
- **Les heures sont en UTC.** Une note sous l’historique précise que tous les horaires sont exprimés en temps universel coordonné (UTC) : un scan effectué en soirée au Canada porte donc déjà la date du lendemain en UTC.

Un colis marqué en retour peut encore être scanné dans un dépôt et remis en livraison : `Package is being returned to shipper` n’est pas toujours la dernière étape.

## Livraison Straightship au Canada : qui livre votre colis

Le suivi de Straightship porte sur le **Canada** (son titre sur la page de résultats est « Canada B2C Shipment Tracking »). Straightship indique que son propre réseau de livraison couvre plus de 95 % de la population canadienne et 90 % des codes postaux canadiens grâce à plus de 85 centres de livraison, avec une promesse de passage avant 21 h (`before 9:00pm`) le jour de la tournée. Au **Canada**, l’entreprise assure aussi le dernier kilomètre pour le logisticien chinois [JS Express](carrier:js-express) : le suivi de JS Express reprend les scans canadiens de Straightship et, lorsqu’un dossier JS Express contient un numéro `STRTD` ou `STRCA`, Peek interroge également Straightship.

Pour les envois importés au **Canada** depuis les États-Unis, Straightship propose sur sa page anglophone plusieurs options de dernier kilomètre — son propre réseau, [Postes Canada](carrier:canada-post), [Purolator](carrier:purolator) ou [UPS](carrier:ups) — tandis que sa page en chinois mentionne son réseau, Postes Canada, [FedEx](carrier:fedex) ou UPS. Straightship annonce sur ce service une livraison en 6 à 10 jours et un dédouanement en moins de 24 heures. Son outil de suivi ne montre toutefois que ses propres dépôts et ne mentionne aucun autre transporteur ni second numéro.

Au **Canada**, Straightship ne publie ni le nombre maximal de tentatives, ni s’il laisse un avis de passage papier, ni la durée de garde en dépôt :

- **Après une tentative infructueuse**, envoyez un courriel à `SS@straightship.com` en indiquant votre numéro de suivi, comme le demande la ligne du suivi.
- **Pour modifier votre adresse ou votre téléphone**, utilisez le formulaire de contact et sélectionnez « Address and/or contact details update ».
- **Preuve de livraison :** la page d’accueil indique que le suivi affiche le dernier statut ainsi qu’une photo de livraison lorsqu’elle est disponible (« POD image (Where available) »).

## Douane canadienne sur un colis Straightship venu d’Asie

Au **Canada**, Straightship effectue le dédouanement pour le compte des entreprises clientes, et sa page de services en chinois indique qu’il peut percevoir les taxes au Canada (`DDU`). Si l’on vous réclame un paiement pour un colis Straightship, vérifiez d’abord auprès du vendeur : la FAQ de SHEIN Canada confirme par exemple que toutes ses commandes à destination du **Canada** sont expédiées tous droits acquittés (`DDP`), sans frais de douane à payer par l’acheteur. D’autres plateformes de suivi peuvent afficher `Customs Released` avant le premier scan au Canada (ce que le suivi propre de Straightship n’affiche pas), ou encore `Customs Held For Examination` pendant un contrôle douanier et `Customs Rejected` — qui n’est pas toujours définitif : sur un envoi suivi par Peek, la douane a inspecté le colis puis l’a libéré deux jours plus tard. Peek affiche ces deux lignes sous « À la douane » ; si l’une persiste plusieurs jours, contactez le vendeur.

Au **Canada**, les droits et taxes dépendent de la valeur et du pays d’expédition selon les seuils de l’Agence des services frontaliers du Canada (ASFC) pour les envois par messagerie : rien n’est dû jusqu’à 20 $ CA pour un bien expédié de Chine ou de tout pays autre que les États-Unis et le Mexique (droits et taxes au-delà). Depuis les États-Unis ou le Mexique, aucune taxe ni aucun droit ne s’applique jusqu’à 40 $ CA, seules les taxes s’appliquent de 40,01 $ CA à 150 $ CA, et droits et taxes au-delà de 150 $ CA (hors marchandises exclues comme l’alcool ou le tabac).

Pour un colis acheminé par messagerie au **Canada**, l’ASFC autorise le transporteur agréé à déclarer les marchandises et à acquitter les droits et taxes en votre nom : selon l’ASFC, l’entreprise de messagerie communique avec vous pour organiser le paiement avant la livraison et retient le colis jusqu’au règlement, tandis que ses frais de service de dédouanement sont distincts des droits et taxes et échappent au contrôle de l’ASFC. L’ASFC rappelle également qu’elle ne demande jamais votre numéro d’assurance sociale (NAS) ni un numéro de carte de crédit par téléphone, message texte ou courriel : tout message de ce type est une arnaque. Plus de détails dans [colis bloqué en douane](guide:customs).

## Colis Straightship en retard, perdu ou retourné

**Qui peut agir.** Les clients de Straightship sont les vendeurs et plateformes qui expédient avec lui, et Straightship ne publie pas de procédure d’indemnisation pour les destinataires. Pour un remboursement ou un renvoi, adressez-vous à la boutique (pour SHEIN, voir [suivre une commande SHEIN](guide:shein-orders)).

**Bloqué avant le Canada.** `Package information is created in the system` sans rien après signifie que Straightship n’a pas encore reçu le colis au Canada ; il est peut-être encore en route depuis l’Asie. Demandez au vendeur la date d’expédition et consultez [suivre un colis depuis la Chine](guide:tracking-from-china).

**Plus de mise à jour au Canada.** Si rien ne bouge plusieurs jours après un scan en dépôt ou si `Shipment delayed` apparaît, utilisez le motif « Delivery Status Enquiry » sur le formulaire de contact de Straightship. Voir aussi [pourquoi le suivi ne bouge plus](guide:tracking-not-updating).

**Marqué livré, mais introuvable.** Cherchez d’abord la photo de livraison sur le suivi de Straightship (elle n’est pas systématique), puis vérifiez auprès des voisins, dans la salle de courrier de l’immeuble et autour de votre porte. Pour le **Canada**, SHEIN précise que, dans de rares cas, un colis peut apparaître comme livré jusqu’à 48 heures avant son arrivée effective ; passé ce délai, utilisez le motif « Delivered Order Not Received » sur le formulaire de Straightship et, sur une commande SHEIN, cliquez sur le bouton « Parcel Delivered, Not Received » dans « My orders » pour ouvrir une demande de remboursement. Voir [colis indiqué livré mais non reçu](guide:delivered-not-received).

**Retourné.** `Package is being returned to shipper` indique un retour vers le vendeur : contactez la boutique pour un remboursement ou une réexpédition, et consultez [colis retourné à l’expéditeur](guide:returned-to-sender).

## Numéro de téléphone et service client Straightship

Sur son site officiel, Straightship indique les coordonnées suivantes :

- **Téléphone au Canada :** `+1 (800) 452-3304`, du lundi au vendredi de 08 h 00 à 21 h 00 et le samedi de 08 h 00 à 16 h 00, heure de l’Est (`EST`) ; le site mentionne aussi `+31 800 0200244` pour les Pays-Bas (`NL`).
- **Courriel :** `SS@straightship.com`, l’adresse indiquée dans ses lignes de tentative de livraison.
- **Formulaire de contact :** sur [sa page de contact](https://www.straightship.com/new/contact.html), où le numéro de suivi est obligatoire. Straightship demande de prévoir 2 jours ouvrables pour obtenir une réponse.

Pour la commande elle-même, un retour ou un remboursement, contactez le vendeur.

## Suivre un colis Straightship avec Peek

[Peek](/) identifie immédiatement Straightship à partir d’un numéro `STRTD` ou `STRCA` aux formats ci-dessus, qu’aucun autre transporteur de son catalogue ne partage. Pour un numéro `STSH`, le format recoupe certaines références de [Colis Privé](carrier:colis-prive) : Peek interroge donc Straightship et le retient dès que le transporteur reconnaît le colis. Si Straightship n’est pas sélectionné, appuyez sur « Changer » et choisissez-le sous « Correspond à ce numéro ». Vous pouvez aussi coller un lien de suivi `straightship.com` (`/track-your-shipment/tracking-id/` suivi du numéro). Peek ne suit chez Straightship que ces formats connus : si vous choisissez manuellement Straightship pour un autre gabarit, Peek indique « Ce transporteur n’émet pas de numéros de suivi comme celui-ci. » — utilisez alors directement le site de Straightship.

Peek interroge le suivi officiel de Straightship et affiche le statut, l’historique avec la ville et la province de chaque scan ainsi que l’heure de livraison. Il lit les horaires comme de l’UTC, conformément à la mention du site, et les convertit dans le fuseau de votre appareil (« Heures dans ton fuseau horaire : … » sur peektracker.com et dans les apps iPhone et Android). `Package will be delivered before 9:00pm` est classé en « En livraison », les lignes d’échec en « Tentative de livraison », et `Delivery is in progress` en « En transit ». Peek n’affiche pas de date de livraison estimée et omet la référence client ainsi que la photo de livraison, consultables sur le site de Straightship. Comme Straightship ne nomme aucun transporteur tiers, Peek suit le numéro Straightship jusqu’à la remise finale.

Tant que vous gardez le colis ouvert, ou le jour de sa livraison avec les notifications activées, Peek vérifie jusqu’à toutes les 10 minutes — et jusqu’à toutes les 2 minutes à partir de 6 h, heure du Pacifique, le jour où Straightship scanne le colis en tournée (livraisons de soirée comprises), tant que le site de Straightship répond ; via les seuls services de suivi, Peek vérifie toutes les heures, toutes les 3 heures après un jour sans mouvement et jusqu’à toutes les 15 minutes le jour de livraison tant que le colis est ouvert ou que les notifications sont activées. Le reste du temps, ainsi qu’après deux jours sans nouvelle (12 heures sur « Étiquette créée » ou « Pris en charge »), Peek vérifie toutes les heures. Vous pouvez activer les alertes « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek ne peut contacter ni Straightship ni le vendeur pour vous.

## Questions fréquentes sur le suivi Straightship

### Straightship est-il un transporteur fiable ?

Oui. Straightship Solutions Limited est une société de logistique établie à Burnaby, en Colombie-Britannique (Canada). Son site cite SHEIN, Postes Canada, FedEx, COSCO et [YTO](carrier:yto) parmi ses partenaires, mentionne Walmart et Costco parmi les enseignes servies, et annonce plus de 85 centres de livraison au Canada ainsi que plus de 500 000 colis Amazon traités par jour.

### Que signifie « Package will be delivered before 9:00pm » ?

Votre colis est en tournée de livraison aujourd’hui au Canada. Le chauffeur le remettra ou enregistrera une tentative le jour même ; s’il ne peut pas le livrer, une ligne d’échec vous invitera à écrire à `SS@straightship.com`.

### Pourquoi Straightship affiche-t-il toujours « ReadyForShipping » ?

Le champ récapitulatif « Package Status » peut rester bloqué sur `ReadyForShipping` pendant que le colis circule déjà entre les dépôts de Straightship. Regardez toujours la ligne la plus récente de l’historique détaillé juste en dessous.

### Quel est le délai de livraison avec Straightship ?

Straightship ne publie pas de délai pour les colis expédiés d’Asie ; pour son service depuis les États-Unis vers le Canada, il annonce 6 à 10 jours. Fiez-vous au délai estimé par votre boutique lors de l’achat ; dès que `Package will be delivered before 9:00pm` s’affiche, le passage a lieu le jour même.

### Qui livre les colis Straightship au Canada ?

Les propres chauffeurs de Straightship au départ de ses dépôts canadiens d’après son outil de suivi, qui ne mentionne aucun autre transporteur. Pour les envois en provenance des États-Unis vers le Canada, ses pages commerciales citent aussi Postes Canada et UPS, ainsi que Purolator sur la version anglaise et FedEx sur la version chinoise.

:::sources
- [Straightship : Accueil (en anglais)](https://www.straightship.com/new/) – liaisons d’Asie vers l’Amérique du Nord, flotte propre, champ de suivi, photo POD, partenaires, téléphone, courriel et horaires EST
- [Straightship : About (en anglais)](https://www.straightship.com/new/about.html) – transport porte-à-porte d’Asie vers l’Amérique du Nord
- [Straightship : Services (en anglais)](https://www.straightship.com/new/services.html) – couverture de plus de 95 % de la population et 90 % des codes postaux au Canada, service États-Unis–Canada en 6 à 10 jours, dédouanement en 24 heures, options Postes Canada, Purolator et UPS
- [Straightship : 服务 (services, en chinois)](https://www.straightship.com/new/cn/services.html) – même service États-Unis–Canada mentionnant son réseau propre, Postes Canada, FedEx ou UPS
- [Straightship : Resources (en anglais)](https://www.straightship.com/new/resources.html) – plus de 85 centres de livraison au Canada, plus de 500 000 colis Amazon par jour, Walmart et Costco
- [Straightship : Contact (en anglais)](https://www.straightship.com/new/contact.html) – motifs du formulaire de contact, numéro de suivi obligatoire et délai de réponse de 2 jours ouvrables
- [Straightship : Privacy Policy (en anglais)](https://www.straightship.com/new/privacy.html) – raison sociale Straightship Solutions Limited et adresses à Burnaby (Colombie-Britannique)
- [ASFC : Importation de marchandises occasionnelles par messagerie](https://www.cbsa-asfc.gc.ca/import/courier/lvs-efv/prsn-fra.html) – déclaration des droits et taxes par les messageries au Canada et frais de dédouanement hors du contrôle de l’ASFC
- [ASFC : Protégez-vous contre la fraude](https://www.cbsa-asfc.gc.ca/security-securite/pyaf-pvcf-fra.html) – paiement organisé avant la livraison par la messagerie et absence de demande de NAS ou de carte bancaire par l’ASFC
- [ASFC : Seuils des expéditions de faible valeur](https://www.cbsa-asfc.gc.ca/services/cusma-aceum/lvs-efv-fra.html) – seuils par messagerie au Canada (20 $ CA hors États-Unis/Mexique, 40 $ CA et 150 $ CA depuis les États-Unis ou le Mexique)
- [SHEIN Canada : Are there any additional fee for customs? (en anglais)](https://ca.shein.com/faq/detail?id=2307) – commandes SHEIN vers le Canada livrées droits acquittés (DDP)
- [SHEIN Canada : My order is marked as delivered, but I haven't received it (en anglais)](https://ca.shein.com/faq/detail?id=2362) – statut livré jusqu’à 48 heures à l’avance et bouton Parcel Delivered, Not Received
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formats `STRTD`, `STRCA` et `STSH`, libellés des scans, mention UTC, valeurs du résumé, `Customs Released` et contrôles douaniers relayés, remise depuis JS Express et champs affichés dans Peek
:::
