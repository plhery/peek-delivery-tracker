---
title: Suivi SPX Express Singapour : colis Shopee et statuts
description: Suis un colis SPX Express (Shopee Xpress) à Singapour : formats SPXSG et SG, signification des statuts, GST à l'importation et recours.
slug: suivi-spx-express-singapore
published: 2026-11-22
updated: 2026-11-22
---

SPX Express Singapore, aussi appelée Shopee Xpress ou Shopee Express, livre des colis à Singapour. Elle achemine des commandes Shopee, y compris des colis de vendeurs en Chine qui transitent par le centre de tri Shopee à Hong Kong. Le suivi officiel de SPX se trouve sur spx.sg. Pour suivre un colis ici, colle son numéro dans le champ ci-dessus. Pour une commande Shopee, ouvre-la dans Shopee et copie le numéro de suivi depuis ses informations d'expédition.

## Numéro de suivi SPX Express Singapour : à quoi il ressemble

Peek reconnaît deux formats pour SPX Express Singapore (chiffres inventés) :

| Numéro (inventé) | À quoi il correspond |
| --- | --- |
| `SPXSG48291736482K` | SPXSG, 11 chiffres et un chiffre ou une lettre finale (17 caractères) : le numéro propre de SPX. Peek identifie immédiatement SPX Express Singapore. |
| `SG482917364829K` | SG, 12 chiffres et un chiffre ou une lettre finale (15 caractères) : le numéro Shopee associé par le suivi de SPX. Peek ne peut pas désigner SPX sur ce seul format (voir plus bas). |
| `SPXSG4829173648` | SPXSG suivi de seulement 10 chiffres : Peek ne le reconnaît pas. Vérifie s'il manque un chiffre. |
| `RA482917367SG` | Deux lettres, neuf chiffres et SG à la fin : un numéro Singapore Post, pas SPX. |

Les espaces et minuscules n'ont aucune importance quand tu colles un numéro dans Peek.

**Le numéro de commande n'est pas un numéro de suivi.** Si le numéro collé a la forme d'un numéro de commande Shopee, Peek affiche « On dirait un numéro de commande Shopee ». Copie plutôt le numéro de suivi dans les détails de la commande. Tu ne le trouves pas ? Consulte [comment trouver son numéro de suivi](guide:find-tracking-number).

## Les statuts de suivi SPX Express Singapour expliqués

Le suivi SPX, tel que Peek le lit, affiche chaque ligne en anglais. Voici les libellés connus du code de Peek, dans l'ordre habituel pour un colis venant de l'étranger :

| SPX affiche | Ce qu'il signifie pour toi | Peek affiche |
| --- | --- | --- |
| `[China]Sender is preparing to ship your parcel` | Le vendeur en Chine prépare le colis. SPX ne l'a pas encore reçu. | « Étiquette créée » |
| `[China] Parcel has arrived at Hong Kong sorting centre` | Le colis est arrivé au centre de tri Shopee de Hong Kong. | « En transit » |
| `[China] Parcel has departed from Hong Kong sorting centre` | Il a quitté ce centre de tri. | « En transit » |
| `[China] Parcel has been handed over to delivery service provider` | Il a été remis à un transporteur aérien chargé de la suite. | « En transit » |
| `[China] Parcel has cleared export customs` | Il a passé la douane d'exportation, pas encore celle de Singapour. | « En transit » |
| `Parcel has departed from the origin port: Hongkong` | Il a quitté Hong Kong. | « En transit » |
| `Parcel has arrived at the destination port Singapore` | Il est arrivé à Singapour : c'est une arrivée, pas un blocage douanier. | « En transit » |
| `Parcel has cleared import customs` | Les douanes d'importation de Singapour l'ont libéré. | « En transit » |
| `Parcel has been picked up by our local logistics partner for further sorting. You will be notified when the parcel is ready for delivery.` | Le colis est désormais dans le réseau local de Singapour pour être trié avant livraison. | « En transit » |
| `[Singapore] Parcel has arrived at sorting facility` | Il est dans un centre de tri à Singapour. | « En transit » |
| `[SPX Express] Parcel is out for delivery` | Il est en cours de livraison. | « En livraison » |
| `Parcel has been delivered` | Livré. | « Livré » |

**Autres étapes.** Le code de Peek reconnaît d'autres statuts SPX : colis collecté chez le vendeur ou déposé en point de dépôt (« Pris en charge »), échec de livraison (« Tentative de livraison ») et retour au vendeur (« Retourné à l’expéditeur », ou « Problème signalé » si le retour se bloque). Plus de détails dans [la signification des statuts de suivi](guide:tracking-statuses).

## Livraison SPX Express à Singapour

Après `[SPX Express] Parcel is out for delivery`, l'étape suivante devrait être `Parcel has been delivered`. Si SPX achemine le colis vers un point de retrait libre-service plutôt qu'à ta porte, Peek affiche « En livraison » pendant le trajet vers ce point.

Peek n'a pas pu consulter le site web de SPX Express Singapore ; cette page n'indique donc ni horaires de livraison, ni nombre de tentatives, ni règles de retrait. Renseigne-toi auprès du vendeur, ou de Shopee si tu as commandé sur cette plateforme. Voir [livraison manquée](guide:missed-delivery) et [colis non retiré](guide:uncollected-parcel).

## Colis de l'étranger : GST et douane à Singapour

Règles de Singapore Customs pour les achats à l'étranger arrivant par avion ou par la poste :

- **GST au paiement.** Si chaque article coûte 400 S$ ou moins et que le vendeur ou la place de marché à l'étranger est enregistré à la GST, la taxe est déjà incluse dans le prix. Le vendeur transmet les informations de paiement au transporteur et la GST n'est pas facturée une seconde fois à l'importation. Le montant concerne uniquement les marchandises, hors port et assurance.
- **GST à l'importation.** Si le vendeur n'est pas enregistré à la GST et que la valeur CIF totale de l'envoi (marchandises, assurance et fret) dépasse 400 S$, la GST est perçue à l'entrée à Singapour. À 400 S$ ou moins, aucune GST n'est perçue à l'importation, sauf pour l'alcool et le tabac.
- **Facturé deux fois ?** Si tu as payé la GST à la commande et qu'on te la réclame de nouveau à l'importation, paie la GST d'importation pour recevoir ton colis, puis demande le remboursement au vendeur avec la preuve de paiement.

**Retenu en douane.** L'Immigration & Checkpoints Authority (ICA) peut retenir un colis contenant des articles soumis à droits, réglementés ou interdits, ou d'une valeur CIF totale supérieure à 400 S$ arrivé sans facture. L'ICA remet alors un reçu de dépôt en entrepôt au transporteur, qui doit t'indiquer la marche à suivre.

**Arnaques.** Singapore Customs précise qu'elle ne demande jamais de paiement de GST ni de données personnelles par SMS, e-mail ou messagerie. Voir [les faux SMS de livraison](guide:delivery-scam-texts) et [les colis retenus en douane](guide:customs).

## Colis SPX bloqué, perdu ou retourné

**Numéro pas encore trouvé.** Lorsque SPX ne connaît pas encore un numéro SPXSG, Peek affiche « En attente de SPX Express Singapore » et continue de vérifier. Le vendeur ne l'a peut-être pas encore remis. Vérifie aussi le numéro.

**Bloqué sur `[China]Sender is preparing to ship your parcel`.** Le vendeur n'a pas encore expédié le colis : demande-lui quand il partira.

**Aucune mise à jour depuis des jours.** Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Après quatre jours sans nouveau scan en transit (hors douane ou incident signalé), Peek affiche « Aucune mise à jour depuis… » et continue d'interroger SPX. Voir [le suivi qui ne se met plus à jour](guide:tracking-not-updating) et [suivre un colis depuis la Chine](guide:tracking-from-china).

**Indiqué livré mais introuvable.** Vérifie autour de ta porte et demande à ton foyer et tes voisins, puis contacte le vendeur ou Shopee. Voir [colis indiqué livré mais non reçu](guide:delivered-not-received).

**Perdu ou retourné.** C'est le vendeur qui a commandé la livraison : contacte-le (ou passe par Shopee) pour un remboursement ou un nouvel envoi. Voir [colis perdu](guide:lost-parcel) et [colis retourné à l'expéditeur](guide:returned-to-sender).

## Service client et contact SPX Express Singapour

Le site de SPX Express Singapore n'ayant pas pu être consulté directement, cette page ne publie aucun numéro de téléphone ni e-mail : Peek n'indique que les coordonnées lues sur les pages officielles d'un transporteur.

Pour une commande Shopee, passe par l'assistance Shopee. Pour un autre site marchand, contacte d'abord la boutique qui a réservé le transport. Singapore Customs ne peut pas aider : elle n'a pas accès au suivi des transporteurs et renvoie vers le transporteur.

## Suivre un colis SPX Express Singapour avec Peek

[Peek](/) suit un colis SPX avec le seul numéro : aucun compte, numéro de téléphone ni code postal requis.

- **Un numéro SPXSG.** Peek identifie immédiatement SPX Express Singapore (« Transporteur détecté ») et interroge directement le suivi de SPX. « Changer » permet de choisir un autre transporteur.
- **Un lien.** Colle un lien vers le suivi sur spx.sg contenant un numéro SPXSG (`/track?`, `/m/track?` ou `/detail/`), et Peek en extrait automatiquement le numéro.
- **Un numéro SG.** Peek ne peut pas garantir le transporteur sur ce format seul. Colle-le tout de même : Peek suit plus de 4 500 transporteurs. Pendant la recherche s'affiche « Vérification des services de suivi… ». Pour choisir SPX toi-même, appuie sur « Changer » : SPX Express Singapore apparaît sous « Correspond à ce numéro ». Mieux vaut utiliser le numéro SPXSG si tu l'as, car le suivi SPX ne retrouve souvent un colis Shopee qu'avec celui-ci.

Ajouté sans transporteur, un colis affiche « Aucun historique de suivi pour le moment » pendant que Peek interroge les services de suivi. Si l'un d'eux identifie SPX Express Singapore seul avec des étapes datées, Peek classe le colis sous SPX.

**Ce que Peek affiche.** Le statut, chaque ligne dans les mots de SPX, et le lieu des scans dans les entrepôts, ports et centres de tri. SPX fournit l'horaire exact de chaque scan, que Peek affiche dans ton fuseau horaire (« Heures dans ton fuseau horaire : »). Peek conserve aussi quelques étapes masquées sur la page SPX, comme le dédouanement à l'importation et un retour en cours. Il laisse de côté la localisation du vendeur, les adresses d'agences et le nom de la personne ayant pris le colis. SPX ne communiquant ni date prévue, ni poids, ni point de retrait, Peek n'en affiche pas.

**De Janio à SPX.** Lorsque le suivi d'un colis Janio désigne SPX avec un numéro SPX Singapore, la carte affiche « Livraison par SPX Express Singapore » et Peek interroge aussi SPX. Tant que SPX n'a reçu que l'annonce sans scan propre, la carte indique « Annoncé à SPX Express Singapore, pas encore reçu ». Dès que SPX publie un scan au moins aussi récent que le dernier de Janio, Peek suit le colis chez SPX tout en gardant l'historique Janio.

**Vérifications et notifications.** Peek interroge directement SPX jusqu'à toutes les 10 minutes, et jusqu'à toutes les 2 minutes à partir de 6 h (heure de Singapour) le jour où SPX scanne le colis en livraison, tant que le colis est ouvert dans Peek ou le jour de la livraison avec les notifications activées. Après deux jours sans nouvelle (12 heures tant que le colis reste sur « Étiquette créée » ou « Pris en charge »), Peek passe à une vérification par heure. Pour les notifications, choisis « Toutes les mises à jour », « Importantes uniquement » ou « Jour de livraison uniquement ». Après quatre jours sans scan en transit, hors douane ou incident signalé, Peek affiche « Aucune mise à jour depuis… », rappelle qu'« Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger SPX Express Singapore. » et propose « Demande de l’aide à SPX Express Singapore ». Peek ne peut pas contacter SPX, Shopee ou le vendeur à ta place.

## Questions fréquentes sur SPX Express Singapour

### SPX Express est-elle la même chose que Shopee Express ?

Oui. Le catalogue de Peek répertorie aussi SPX Express Singapore sous les noms Shopee Xpress Singapore et Shopee Express Singapore, et ce réseau achemine des commandes Shopee. SPX exploite des réseaux distincts dans d'autres pays, comme [SPX Express Brésil](carrier:spx-br) : un numéro SPXSG correspond au réseau de Singapour.

### Quelle est la différence entre un numéro SPXSG et un numéro SG ?

Un numéro SPXSG est le numéro propre de SPX pour le colis : Peek reconnaît aussitôt SPX. Un numéro SG est le numéro Shopee qui lui est associé. Comme le suivi SPX ne retrouve souvent un colis Shopee qu'avec le numéro SPXSG, utilise plutôt celui-ci.

### Que signifie « Parcel has been picked up by our local logistics partner » ?

Ton colis venu de l'étranger est arrivé à Singapour et a été remis au réseau local pour être trié. SPX indique que tu seras averti lorsqu'il sera prêt à être livré. Peek affiche « En transit ».

### Pourquoi mon suivi SPX ne se met-il plus à jour ?

Si la dernière ligne est `[China]Sender is preparing to ship your parcel`, le vendeur n'a pas encore expédié le colis. Par la suite, les colis restent souvent silencieux pendant les vols, en douane ou le week-end. Après quatre jours sans nouvelle en transit, Peek signale le colis. S'il reste bloqué, contacte le vendeur ou Shopee.

:::sources
- [Singapore Customs: GST on Low-Value Goods](https://www.customs.gov.sg/personal-shipment/buying-from-overseas/gst-on-low-value-goods/) – règle des 400 S$, GST au paiement ou à l'importation, double facturation, arnaques
- [Singapore Customs: Courier / Postal Procedures](https://www.customs.gov.sg/personal-shipment/buying-from-overseas/courier-postal-procedures/) – colis retenus par l'ICA, demander au transporteur pour le suivi
- [Universal Parcel Scraper: SPX Express Singapore](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/spx-sg) – noms, formats de numéros, liens de suivi, libellés et étapes
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – codes partagés de SPX, étapes masquées conservées, format Singapore Post, relais depuis Janio
:::
