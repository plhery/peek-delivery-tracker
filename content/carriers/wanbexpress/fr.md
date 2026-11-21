---
title: Suivi WanbExpress : numéros WNB…YQ, statuts et livraison
description: Suivre un colis WanbExpress (Wanb Express) depuis la Chine : format WNB…YQ, signification des statuts, transporteur final et recours en cas de blocage.
slug: suivi-wanbexpress
published: 2026-11-21
updated: 2026-11-21
---

WanbExpress (aussi appelé Wanb Express, WanBang Express ou WANB) est un transporteur chinois qui achemine des colis à l'étranger, où un transporteur local assure la livraison. Si une boutique a expédié ta commande avec WanbExpress, le numéro commence par `WNB` et se termine par `YQ` : colle-le dans le champ ci-dessus. Le suivi propre à WanbExpress, tracking.wanbexpress.com, peut aussi indiquer le transporteur qui livre dans ton pays et le numéro qu'il attribue au colis.

## Numéro de suivi WanbExpress : à quoi il ressemble

Les numéros de bordereau WanbExpress se composent de `WNB`, deux lettres, dix chiffres et `YQ`, soit 17 caractères au total. Exemples avec des chiffres inventés :

| Numéro (inventé) | Ce que c'est |
| --- | --- |
| `WNBKG4829105736YQ` | `WNB`, deux lettres, dix chiffres et `YQ` : un numéro WanbExpress. |
| `WNBKG482910573YQ` | Seulement neuf chiffres : ne correspond pas au format WanbExpress. Vérifie qu'il ne manque pas un chiffre. |
| `JSEKG4829105736YQ` | Se termine aussi par `YQ`, mais commence par `JSE` : un numéro [JS Express](carrier:js-express). |
| `JXCKG4829105736YQ` | Commence par `JXC` : un numéro [Juxi Express](carrier:juxi-express). |

Plusieurs commissionnaires chinois terminent leurs bordereaux par `YQ`, chacun avec son propre préfixe : `WNB` identifie WanbExpress. Dans Peek, les espaces, tirets et minuscules ne changent rien.

**Où le trouver.** Le vendeur communique le numéro dans l'e-mail d'expédition ou sur la page de commande. S'il est introuvable, consulte [comment trouver ton numéro de suivi](guide:find-tracking-number).

**Un lien de suivi.** Un lien vers le suivi WanbExpress se terminant par `?trackingNumbers=` suivi d'un numéro ouvre ce colis sur le site de WanbExpress. Le numéro `WNB` correspond à la partie après le signe égal : copie-le dans Peek.

**Un second numéro.** Le suivi de WanbExpress peut aussi indiquer le numéro utilisé par le transporteur qui livre le colis. Ce numéro appartient à l'autre transporteur : tu peux le suivre sur son site ou l'ajouter dans Peek comme un colis distinct.

## Statuts de suivi WanbExpress expliqués

Les scans de WanbExpress sont en anglais, chacun avec une heure et parfois un lieu. Voici les intitulés recensés dans le code de Peek, dans l'ordre habituel :

| Ce qu'indique WanbExpress | Ce que cela signifie pour toi | Peek affiche |
| --- | --- | --- |
| `Shipping Order Created.`, `Parcel Data Received` | Le vendeur a déclaré l'envoi et transmis les données. WanbExpress n'a pas encore le colis. | « Étiquette créée » |
| `Parcel received at Shenzhen Sorting Centre.` | Le centre de tri de Shenzhen, en Chine, a reçu le colis : c'est le premier scan physique. | « Pris en charge » |
| `Parcel processed at Shenzhen Sorting Centre.`, `Parcel departure in Shenzhen Sorting Centre.` | Trié, puis parti de ce centre. | « En transit » |
| `A-Scan: shipment in international transit` | Scan de prise en charge relayé entre le traitement au centre de tri et son départ. | « En transit » |
| `Customs declaration completed` | Une déclaration en douane a été effectuée. Le libellé ne précise pas le pays et ne correspond pas au dédouanement à l'importation, qui apparaît comme `Custom clearance completed`. | « En transit » |
| `Customs inspection has not yet released` | Contrôle d'exportation au départ, entre la déclaration et le vol. | « En transit » |
| `Parcel departure in Transit Facility, Parcel Departure` | Le colis a quitté un centre de transit. | « En transit » |
| `Parcel is leaving Airport, Parcel Leaving Port` | Départ sur le vol vers le pays de destination. | « En transit » |
| `Parcel arrives at Airport, Parcel Arrival` | Arrivé dans ton pays ou à proximité. Le dédouanement suit. | « En transit » |
| `Custom clearance completed` | La douane d'importation a libéré le colis. | « En transit » |
| `Parcel received by in JFK01.` | Prise en charge enregistrée par WanbExpress pour le dernier tronçon, sans nommer le prestataire. | « En transit » |
| `Your package is being prepared by the sender and will be handed over to us soon`, `Your parcel will soon be handed over to us! It is being prepared by the sender.`, `Order information received. We're expecting your parcel to arrive with us.` | Avis d'un partenaire relayé sans code WanbExpress : le colis ne lui a pas encore été remis. | « Étiquette créée », ou une étape ultérieure si un scan a déjà fait avancer le colis |
| `In transit along Poste Italiane’s logistic network` | En acheminement dans le réseau du transporteur final, ici [Poste Italiane](carrier:poste-italiane). | « En transit » |
| `Parcel is out for delivery.` | En cours de tournée de livraison. | « En livraison » |
| `Consignee absent, Consignee could not be found.` | Le livreur partenaire n'a trouvé personne. | « Tentative de livraison » |
| `Placed in Parcelshop/Parcel locker` | Après le passage manqué, le partenaire a déposé le colis en point relais ou en consigne pour retrait. | « Prêt à être retiré » |
| `Parcel has been delivered.(Front door)` | Livré. La mention `(Front door)` précise l'endroit où le colis a été déposé. | « Livré » |

**Les scans du transporteur final.** Après le vol, le suivi de WanbExpress relaie les scans du transporteur local dans ses propres termes, le plus souvent sans code WanbExpress. Sur un colis à destination de l'Allemagne, l'historique montre [iMile](carrier:imile) à la livraison, chaque ligne étant précédée du nom du centre ou de la station entre crochets `【】` :

| Ce qu'indique iMile | Ce que cela signifie pour toi | Peek affiche |
| --- | --- | --- |
| `Received.` | Le centre iMile du pays de destination a reçu le colis. | « En transit » |
| `Arrived.` | Arrivé à l'agence de livraison qui dessert ta zone. | « En transit » |
| `Dispatch task assigned to DA.` | Un livreur (DA, delivery associate) est affecté à la tournée, parfois plusieurs heures avant le départ. | « En transit » |
| `Shipment out for delivery.` | En tournée de livraison. | « En livraison » |
| `<Route not completed>`, `Back To DS.` | La tournée s'est achevée sans livraison et le colis est revenu à l'agence (DS). | « Tentative de livraison » (seul après la tournée ; après une tentative déjà signalée, `Back To DS.` reste un simple avis, et après « En transit », il reste « En transit ») |
| `Scheduled to <2026-11-02> .` | Date prévue pour la prochaine tentative après retour à l'agence. | Toujours « Tentative de livraison » |

Les mouvements d'autres partenaires arrivent de la même manière. Sur des colis vers les États-Unis, `Parcel received by Transit Facility.` signale la réception par le partenaire final dans un centre de transit, `In Processing at Transit Facility.` son tri, `In Transit to Next Facility.` le trajet vers l'agence locale et `Parcel received by local Facility.` sa réception avant la tournée. `It is currently in transit to the next facility` et `The package is being transferred to the next facility` indiquent un transfert entre sites après le vol, et `Shipment arrived to sorting centre / Shipment in the sorting centre` l'entrée dans un centre de tri partenaire. Peek classe ces sept libellés en « En transit ». Sur un colis vers le Royaume-Uni, les lignes d'[InPost](carrier:inpost) peuvent apparaître avant même le scan du centre de tri de WanbExpress : `Your parcel is on its way to InPost. Please check back for updates` (« Étiquette créée »), puis `We have your parcel. Please check back for updates` (« Pris en charge »). `We'll apply your My Account preferences to this parcel delivery` est une note d'InPost rappelant que les préférences de compte s'appliquent, pas un scan : Peek la conserve dans l'historique sans changer d'étape. Il en va de même pour `Consignee Contacted`, un appel du livreur enregistré à la même minute qu'un passage sans réponse.

**Horaires.** Chaque scan comporte son décalage par rapport à UTC. Peek lit cet instant et affiche toutes les heures dans le fuseau horaire de ton appareil, du plus récent au plus ancien. Pour mieux comprendre les silences et les relais propres aux envois depuis la Chine, voir [suivre un colis depuis la Chine](guide:tracking-from-china).

## Qui livre les colis WanbExpress

Les scans de WanbExpress suivent le colis hors de Chine, à travers la douane, puis jusqu'au transporteur local qui le livre. Le suivi de WanbExpress peut afficher le nom de ce transporteur, son numéro de suivi et une photo de livraison.

**Ce que montre le code de Peek.** Sur un colis vers l'Allemagne, l'historique de WanbExpress montre iMile à la livraison, et un scan cite le réseau de Poste Italiane. Sur un colis vers le Royaume-Uni, les lignes d'InPost apparaissent. Sur son service de livraison à domicile en France, le code de Peek mentionne aussi un réétiquetage avec un numéro Cainiao Express (`DOFR…HD` ou `CNFR…HD`), et en Australie des relais de J&T Express Australia. Pour les États-Unis, l'Irlande ou le Canada, le code de Peek ne nomme aucun transporteur final particulier.

**Tentatives de livraison et retrait.** Quel que soit le transporteur final, ce sont ses règles qui s'appliquent : nombre de passages, avis de passage, lieu et durée de garde. Peek n'a trouvé aucune règle propre à WanbExpress sur ces points. Dès que tu connais le transporteur local et son numéro, consulte son propre suivi et contacte-le pour reprogrammer la livraison. Voir [livraison manquée](guide:missed-delivery).

## Colis WanbExpress bloqué ou introuvable

**Numéro introuvable.** Si le suivi de WanbExpress ne connaît pas le numéro, Peek affiche « En attente de WanbExpress » et continue d'interroger le suivi. Si WanbExpress ne le connaît toujours pas au bout de deux jours et qu'aucune autre source n'a répondu, Peek affiche « Après deux jours, le transporteur ne connaît toujours pas ce numéro. Vérifie-le, choisis un autre transporteur ou supprime le colis. », puis vérifie une fois par jour, et une fois par semaine une fois sept jours passés depuis l'ajout. Compare le numéro caractère par caractère avec le message du vendeur : `WNB`, deux lettres, dix chiffres, `YQ`. Si rien n'apparaît après quelques jours, demande au vendeur si le colis a bien été expédié.

**Bloqué sur `Shipping Order Created.` ou `Parcel Data Received`.** La boutique a réservé l'envoi, mais WanbExpress n'a pas encore scanné le colis. Demande au vendeur quand il l'a remis au transporteur.

**Bloqué après `Parcel arrives at Airport, Parcel Arrival`.** Le colis attend probablement le dédouanement à l'importation. Pour les envois entrant dans l'Union européenne, les achats en ligne provenant de pays tiers supportent un droit de douane temporaire de 3 € par article (par catégorie de produit) pour les envois d'une valeur allant jusqu'à 150 € depuis le 1er juillet 2026 et, depuis le 1er novembre 2026, une redevance de traitement de l'Union de 2 € par article, quelle que soit la valeur. Si des frais d'importation te sont demandés, voir [colis retenu à la douane](guide:customs) ; si la situation dure, préviens le vendeur. Plus d'informations dans [pourquoi le suivi ne se met plus à jour](guide:tracking-not-updating).

**Rien après `Custom clearance completed`.** Le transporteur local a pris le colis en charge ou va le recevoir, et son propre suivi peut avoir de l'avance sur celui de WanbExpress. Cherche son nom et son numéro sur tracking.wanbexpress.com, ou demande au vendeur qui livre le colis et sous quel numéro.

**Tentative de livraison.** Après un libellé comme `<Route not completed>`, c'est le transporteur final qui décide de la nouvelle tentative ou du dépôt en point relais. Contacte ce transporteur, pas WanbExpress.

**Affiché comme livré, mais rien reçu.** Vérifie si tracking.wanbexpress.com affiche une photo de livraison, puis contacte le transporteur final et le vendeur. Voir [colis livré mais non reçu](guide:delivered-not-received).

**Perdu ou retourné.** C'est le vendeur qui a confié le colis à WanbExpress : adresse-toi à lui, ou à la plateforme, pour un remboursement ou un renvoi. Voir [colis perdu](guide:lost-parcel) et [colis retourné à l'expéditeur](guide:returned-to-sender).

## Service client et contact WanbExpress

Peek n'a pas pu consulter le site web de WanbExpress et n'indique donc aucune coordonnée de contact pour WanbExpress sur cette page. Pour toute question sur ta commande, contacte le vendeur qui a réservé l'expédition, ou la place de marché si le vendeur ne répond pas. Pour la livraison finale, adresse-toi au transporteur local.

## Suivre un colis WanbExpress avec Peek

[Peek](/) reconnaît un numéro WanbExpress à sa forme (`WNB`, deux lettres, dix chiffres et `YQ`, qu'aucun autre transporteur de son catalogue n'utilise) et interroge le suivi de WanbExpress avec ce seul numéro. Aucun compte, code postal ni numéro de téléphone n'est nécessaire.

Le suivi de WanbExpress exigeant une vérification avant de répondre, Peek l'interroge par navigateur. Si le suivi de WanbExpress ne répond pas, Peek se tourne vers des services de suivi et affiche l'historique qu'ils détiennent, dans leurs propres termes ; cet historique peut être en retard sur celui de WanbExpress.

Depuis le suivi de WanbExpress, Peek affiche le statut, les 100 scans les plus récents avec leur lieu, ainsi que les pays d'origine et de destination indiqués sur le dossier. Lorsque ce dossier indique que le colis est en transit, en livraison ou livré, Peek suit ce statut même si le dernier scan en dit moins. Si le suivi de WanbExpress transmet un numéro de transporteur final et que son panneau dernier kilomètre identifie un transporteur du catalogue dont le format correspond à ce numéro, Peek peut afficher « Livraison par … » (ou « Annoncé à …, pas encore reçu ») et prendre le relais sur son suivi. Peek n'affiche pas de date de livraison prévue et ne reprend ni les photos de livraison ni l'adresse du destinataire ; consulte tracking.wanbexpress.com pour les voir, ou ajoute séparément le numéro du transporteur final dans Peek. Peek suit plus de 4 500 transporteurs.

Tant que tu gardes le colis ouvert à l'écran ou le jour de la livraison avec les notifications activées, et que le suivi de WanbExpress répond, Peek vérifie jusqu'à toutes les 10 minutes, et jusqu'à toutes les 5 minutes dès 6 h (heure du transporteur) le jour où le colis passe en livraison lorsque le contrôle passe par un navigateur ; sinon, toutes les heures. Lorsque ce sont des services de suivi qui répondent, Peek vérifie toutes les heures, et jusqu'à toutes les 15 minutes le jour de la livraison tant que le colis est ouvert ou que les notifications sont activées. Après deux jours sans nouvelle (12 heures si le colis reste sur « Étiquette créée » ou « Pris en charge »), Peek passe à une vérification par heure. Tu peux choisir d'être prévenu pour « Chaque scan », « Étapes importantes » ou « Livraison seulement ». L'option « Étapes importantes » (livraison, retrait, douane, tentatives échouées et retours) couvre la tournée, un passage manqué et la livraison, mais pas les libellés douaniers de WanbExpress, que Peek classe en « En transit ». Peek ne peut contacter ni WanbExpress ni le vendeur à ta place.

## Questions sur le suivi WanbExpress

### Qu'est-ce que WanbExpress ?

Un transporteur chinois, aussi connu sous les noms Wanb Express, WanBang Express ou WANB. Dans le code de Peek, ses scans démarrent dans un centre de tri à Shenzhen, suivent le vol et le passage en douane, puis se terminent par les scans du transporteur qui livre dans le pays de destination.

### Où suivre un colis WanbExpress ?

Dans le champ ci-dessus, ou sur le suivi de WanbExpress à l'adresse tracking.wanbexpress.com. Ce suivi applique un contrôle anti-robot et peut aussi indiquer le transporteur final. Saisis le numéro complet `WNB…YQ`.

### Que signifie « Customs declaration completed » sur le suivi WanbExpress ?

Qu'une déclaration en douane a été complétée pour ton colis. Le libellé ne précise pas de quel pays il s'agit et ne signifie pas que la douane d'importation a libéré le colis : cette étape apparaît sous `Custom clearance completed`. Peek affiche les deux en « En transit ».

### Qui livre les colis WanbExpress dans mon pays ?

Un transporteur local, que le suivi de WanbExpress peut nommer avec son propre numéro. Le code de Peek montre iMile sur un colis vers l'Allemagne, les lignes d'InPost vers le Royaume-Uni et un scan mentionnant le réseau de Poste Italiane, ainsi que des relais de J&T Express Australia ou de numéros `DOFR…HD`/`CNFR…HD` en France ; pour les autres pays, vérifie tracking.wanbexpress.com ou demande au vendeur.

### Qui contacter si mon colis WanbExpress est introuvable ou perdu ?

Le vendeur auprès duquel tu as commandé : c'est lui qui a confié le colis à WanbExpress. Peek n'ayant pas pu consulter le site de WanbExpress, aucune coordonnée directe de WanbExpress ne figure ici.

:::sources
- [Universal Parcel Scraper : WanbExpress](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/wanbexpress) – noms, format `WNB…YQ`, suivi par navigateur, libellés de scans et leurs étapes (y compris `Customs inspection has not yet released`, `A-Scan: shipment in international transit`, les relais iMile, InPost, Poste Italiane et partenaires américains), horodatages avec décalage UTC, identification du transporteur final (`TrackLastMileCarrier`), photos et adresse écartées, 100 derniers scans
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formats concurrents `YQ` (`JS Express`, `Juxi Express`), services de suivi de secours et bascule vers le transporteur final
- [Commission européenne : EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – droit de douane temporaire de 3 € par article jusqu'à 150 € depuis le 1er juillet 2026, redevance de traitement de l'Union de 2 € depuis le 1er novembre 2026
- [Commission européenne : Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – redevance de traitement de 2 € par article, quelle que soit la valeur
:::
